# Microsoft Learn — Microsoft Entra / Microsoft Entra Domain Services (part 1)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 63

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services"} -->
## Microsoft Entra Domain Services のドキュメント - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services
- Service: entra-id / domain-services
- Article date: 2023-09-23
- Summary: Microsoft Entra Domain Services を使用して、Kerberos または NTLM 認証をアプリケーションに提供する方法、または Azure VM をマネージド ドメインに参加させる方法について説明します。

Microsoft Entra Domain Services を使用して、Kerberos または NTLM 認証をアプリケーションに提供する方法、または Azure VM をマネージド ドメインに参加させる方法について説明します。

### Microsoft Entra Domain Services について

#### 概要

- [Microsoft Entra Domain Services とは](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/overview)
- [ID ソリューションの比較](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/compare-identity-solutions)

#### 概念

- [同期のしくみ](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/synchronization)
- [FAQ](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/faqs)

### 作業の開始

#### デプロイ

- [マネージド ドメインを作成する](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)

#### チュートリアル

- [Windows Server VM のドメイン参加](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-windows-vm)
- [管理ツールをインストールする](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-management-vm)

### 構成

#### チュートリアル

- [Secure LDAP の構成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-ldaps)

#### 攻略ガイド

- [パスワード ハッシュ同期を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-password-hash-sync)
- [組織単位 (OU) を作成する](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/create-ou)
- [Kerberos の制約付き委任を構成する](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/deploy-kcd)
- [セキュリティで保護されたマネージド ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/secure-your-domain)

### 取り締まる

#### 攻略ガイド

- [グループ ポリシーの管理](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/manage-group-policy)
- [DNS の管理](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/manage-dns)
- [正常性状態を確認する](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/check-health)
- [電子メール通知を構成する](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/notifications)
- [セキュリティ監査を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/security-audit-events)

### ドメイン参加 VM

#### チュートリアル

- [Windows Server](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-windows-vm)

#### 攻略ガイド

- [Ubuntu Server](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-ubuntu-linux-vm)
- [Red Hat Enterprise Linux](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-rhel-linux-vm)
- [SUSE Linux Enterprise](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-suse-linux-vm)

### トラブルシューティング

#### 攻略ガイド

- [一般的な問題](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/troubleshoot)
- [一般的なアラート メッセージ](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/troubleshoot-alerts)
- [ネットワーク セキュリティ グループの問題](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/alert-nsg)
- [セキュリティで保護された LDAP の問題](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/alert-ldaps)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/administration-concepts"} -->
## Microsoft Entra Domain Services の管理の概念 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/administration-concepts
- Service: entra-id / domain-services
- Article date: 2026-06-22
- Summary: Microsoft Entra Domain Services のマネージド ドメインとユーザー アカウントとパスワードの動作を管理する方法について説明します

Microsoft Entra Domain Services のマネージド ドメインを作成して実行するときに、従来のオンプレミス AD DS 環境と比べていくつかの動作の違いがあります。 Domain Services でも自己管理型のドメインと同じ管理ツールを使用しますが、ドメイン コントローラー (DC) に直接アクセスすることはできません。 また、ユーザー アカウントの作成元よって、パスワード ポリシーとパスワード ハッシュの動作に違いがあります。

この概念に関する記事では、マネージド ドメインの管理方法と、ユーザー アカウントの作成方法に応じたさまざまな動作について説明します。

### ドメインの管理

マネージド ドメインは、DNS 名前空間および対応するディレクトリです。 マネージド ドメインでは、ユーザーとグループ、資格情報、ポリシーなどのすべてのリソースを含むドメイン コントローラー (DC) は、マネージド サービスの一部です。 冗長性を確保するために、マネージド ドメインの一部として 2 つの DC が作成されます。 これらの DC にサインインして管理タスクを実行することはできません。 代わりに、マネージド ドメインに参加している管理仮想マシン (VM) を作成し、通常の AD DS 管理ツールをインストールします。 たとえば、DNS やグループ ポリシー オブジェクトなどの Active Directory 管理センターまたは Microsoft 管理コンソール (MMC) スナップインを使用できます。

### ユーザー アカウントの作成

ユーザー アカウントは、複数の方法でマネージド ドメインに作成できます。 ほとんどのユーザー アカウントは Microsoft Entra ID から同期されます。これには、オンプレミスの AD DS 環境から同期されたユーザー アカウントも含まれます。 マネージド ドメインにアカウントを手動で直接作成することもできます。 初期パスワードの同期やパスワード ポリシーなどの一部の機能は、ユーザー アカウントの作成方法と作成場所に応じて異なる動作をします。

- ユーザー アカウントは Microsoft Entra ID から同期できます。 Microsoft Entra Connect を使用して、オンプレミスの AD DS 環境から同期された Microsoft Entra ID に直接作成できます。
- ユーザー アカウントはマネージド ドメインに手動で作成でき、Microsoft Entra IDには存在しません。 マネージド ドメインでのみ実行されるアプリケーションのサービス アカウントを作成する必要がある場合は、マネージド ドメインに手動で作成できます。 同期はMicrosoft Entra IDからのみ一方向であるため、マネージド ドメインで作成したユーザー アカウントはMicrosoft Entra IDに同期されません。

### パスワード ポリシー

Domain Services には、アカウントのロックアウト、パスワードの最大有効期間、パスワードの複雑さなどの設定を定義する既定のパスワード ポリシーが含まれています。 アカウントのロックアウト ポリシーなどの設定は、前のセクションで説明したように、ユーザーの作成方法に関係なく、マネージド ドメイン内のすべてのユーザーに適用されます。 パスワードの最小長やパスワードの複雑さなどの一部の設定は、マネージド ドメインに直接作成されたユーザーにのみ適用されます。

独自のカスタム パスワード ポリシーを作成して、マネージド ドメインの既定のポリシーを上書きすることができます。 これらのカスタム ポリシーは、必要に応じて特定のユーザー グループに適用できます。

ユーザーの作成元で異なるパスワード ポリシーの適用方法の詳細については、「[マネージド ドメインに関するパスワードとアカウントのロックアウト ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/password-policy)」を参照してください。

### パスワード ハッシュ

Domain Services でマネージド ドメインのユーザーを認証するためには、NT LAN Manager (NTLM) 認証および Kerberos 認証に適した形式のパスワード ハッシュが必要となります。 Microsoft Entra IDでは、テナントの Domain Services を有効にするまで、NTLM または Kerberos 認証で必要な形式でパスワード ハッシュが生成または格納されることはありません。 また、セキュリティ上の理由から、クリアテキスト形式のパスワード資格情報が Microsoft Entra ID に保存されることもありません。 そのため、Microsoft Entra ID では、ユーザーの既存の資格情報に基づいて、これらの NTLM やKerberos のパスワード ハッシュを自動的に生成することはできません。

クラウド専用ユーザー アカウントの場合、ユーザーはマネージド ドメインを使用する前に各自のパスワードを変更する必要があります。 このパスワード変更プロセスによって、Kerberos 認証と NTLM 認証に使用されるパスワード ハッシュが Microsoft Entra ID に生成されて保存されます。 パスワードが変更されるまで、アカウントは Microsoft Entra ID から Domain Services に同期されません。

Microsoft Entra Connect を使用してオンプレミスの AD DS 環境から同期されたユーザーについては、[パスワード ハッシュの同期を有効にします](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-password-hash-sync#enable-synchronization-of-password-hashes)。

重要

Microsoft Entra Connect では、Microsoft Entra テナントに対して Domain Services を有効にしたときに、レガシ パスワード ハッシュのみが同期されます。 Microsoft Entra Connect を使用してオンプレミス AD DS 環境と Microsoft Entra ID の同期しか行わない場合、レガシ パスワード ハッシュは使用されません。

レガシ アプリケーションで NTLM 認証またはライトウェイト ディレクトリ アクセス プロトコル (LDAP) の単純バインドを使用しない場合は、Domain Services の NTLM パスワード ハッシュ同期を無効にすることをお勧めします。 詳しくは、「[弱い暗号スイートと NTLM 資格情報ハッシュの同期を無効にする](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/secure-your-domain)」をご覧ください。

適切に構成されれば、使用可能なパスワード ハッシュがマネージド ドメインに保存されます。 マネージド ドメインを削除した場合、その時点で保存されていたパスワード ハッシュがあればすべて削除されます。 別のマネージド ドメインを後から作成した場合、Microsoft Entra ID にある同期済みの資格情報は再利用できません。パスワード ハッシュを再度保存するには、パスワード ハッシュ同期を再構成する必要があります。 以前はドメインに参加していた VM またはユーザーはすぐに認証することはできません。Microsoft Entra ID は、新しいマネージド ドメインにパスワード ハッシュを生成して格納する必要があります。 詳細については、[Domain Services と Microsoft Entra Connect のパスワード ハッシュ同期プロセス](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/how-to-connect-password-hash-synchronization#password-hash-sync-process-for-azure-ad-domain-services)に関する記事を参照してください。

重要

Microsoft Entra Connect は、オンプレミスの AD DS 環境との同期のためにのみインストールおよび構成する必要があります。 Microsoft Entra ID と同期するためのマネージド ドメインでの Microsoft Entra Connect のインストールはサポートされていません。

### フォレストと信頼

"*フォレスト*" は、Active Directory Domain Services (AD DS) で 1 つまたは複数の "*ドメイン*" をグループ化するために使われる論理構造です。 ドメインには、ユーザーまたはグループのオブジェクトが格納され、認証サービスが提供されます。

Domain Services では、フォレストにはドメインが 1 つだけ含まれます。 多くの場合、オンプレミスの AD DS フォレストには多数のドメインが含まれます。 大規模な組織では、特に合併や買収の後に、複数のオンプレミス フォレストが存在し、各フォレストにそれぞれ複数のドメインが含まれている場合があります。

既定では、マネージド ドメインは、オンプレミスの AD DS 環境で作成されたすべてのユーザー アカウントを含め、Microsoft Entra ID からすべてのオブジェクトを同期します。 ドメインに参加している VM へのサインインなどのために、マネージド ドメインに対してユーザー アカウントを直接認証できます。 この方法が機能するのは、パスワード ハッシュの同期が可能で、ユーザーがスマート カード認証などの専用サインイン方法を使用していない場合です。

Domain Services では、別のドメインとのフォレスト*信頼*を作成して、ユーザーがリソースにアクセスできるようにすることもできます。 必要なアクセス要件に応じて、一方向または双方向のフォレスト信頼関係を作成できます。

| 信頼の方向 | ユーザー アクセス |
| --- | --- |
| 双方向 | マネージド ドメインとオンプレミス ドメインの両方のユーザーが、どちらのドメイン内のリソースにもアクセスできるようにします。 |
| 一方向送信 | オンプレミス ドメインのユーザーがマネージド ドメイン内のリソースにアクセスできるようにしますが、その逆は許可されません。 |
| 一方向着信 | マネージド ドメインのユーザーがオンプレミス ドメイン内のリソースにアクセスできるようにします。 |

### Domain Services の SKU

Domain Services では、使用可能なパフォーマンスと機能は、在庫保管単位 (SKU) に基づいています。 マネージド ドメインを作成するときに SKU を選択し、マネージド ドメインの作成後にビジネス要件の変更に応じて SKU を切り替えることができます。 次の表に、使用可能な SKU とそれらの違いを示します。

| SKU | 推奨されるオブジェクト数 | 推奨される認証ボリューム | バックアップ頻度 |
| --- | --- | --- | --- |
| Standard | 最大 25,000 個のオブジェクト | 最大 3,000 認証/時間 | 5 日ごと |
| Enterprise | 25,001 – 100,000 オブジェクト | 3,001～10,000 認証/時 | 3 日ごと |
| Premium | 100,001 – 500,000 オブジェクト | 1時間あたり10,001～70,000件の認証 | 毎日;バックアップは 7 日間保持され、3 つ目のバックアップは 30 日ごとに保持されます |

注

示されている値は、強制された制限ではなく、推奨される容量ガイダンスです。 SKU レベルが上がるにつれて、マネージド ドメインに割り当てられるコンピューティング リソースが増え、パフォーマンス、同期操作、認証スループットが向上します。 ワークロードのサイズ、認証ボリューム、パフォーマンス要件、回復目標に基づいて SKU を選択します。

これらの Domain Services SKU の前は、マネージド ドメイン内のオブジェクト (ユーザー アカウントとコンピューター アカウント) の数に基づく課金モデルが使用されていました。 マネージド ドメイン内のオブジェクトの数に基づく可変価格はなくなりました。

詳細については、[Domain Services の価格ページ](https://azure.microsoft.com/pricing/details/active-directory-ds/)を参照してください。

#### マネージド ドメインのパフォーマンス

ドメインのパフォーマンスは、アプリケーションに対する認証の実装方法によって異なります。 コンピューティング リソースを増やすと、クエリの応答時間が短縮され、同期時間が短縮される場合があります。 SKU レベルが上がるにつれて、マネージド ドメインで使用できるコンピューティング リソースが増えます。 アプリケーションのパフォーマンスを監視し、必要なリソースを計画してください。

ビジネスまたはアプリケーションの需要が変化し、マネージド ドメインのコンピューティング能力が必要な場合は、別の SKU に切り替えることができます。

#### バックアップ頻度

バックアップの頻度によって、マネージド ドメインのスナップショットが取得される頻度が決まります。 バックアップは、Microsoft Entra プラットフォームによって管理される自動化されたプロセスです。 マネージド ドメインに問題がある場合は、Microsoft Entraサポートがバックアップからの復元に役立ちます。 同期はMicrosoft Entra ID*から* 1 つの方法しか発生しないため、マネージド ドメイン内の問題は、Microsoft Entra IDやオンプレミスの AD DS の環境と機能には影響しません。

SKU レベルが増加するにつれて、それらのバックアップ スナップショットの頻度が増加します。 ビジネス要件と目標復旧時点 (RPO) を確認して、マネージド ドメインで必要なバックアップの頻度を決定します。 ビジネス要件やアプリケーション要件が変更され、頻繁にバックアップが必要になった場合は、別の SKU に切り替えることができます。

Domain Services には、さまざまな種類の問題の復旧期間に関する次のガイダンスが用意されています。

- 目標復旧ポイント (RPO) は、インシデントによってデータまたはトランザクションが失われる可能性がある最大期間です。
- 回復時刻の目標 (RTO) は、インシデント後にサービス レベルが運用に戻る前に発生する対象期間です。

| 問題 | RPO | RTO |
| --- | --- | --- |
| Domain Services ドメイン コントローラー、依存サービス、ドメインを侵害した悪用、またはドメイン コントローラーの復元を必要とするその他のインシデントに対するデータの損失または破損によって発生する問題。 | イベントが発生する 5 日前 | テナントのサイズに応じて、2 時間から 4 日 |
| Microsoft サポート ドメイン診断によって識別される問題。 | ゼロ (0 分) | テナントのサイズに応じて、2 時間から 4 日 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/alert-ldaps"} -->
## Microsoft Entra Domain Services でセキュリティで保護された LDAP アラートを解決する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/alert-ldaps
- Service: entra-id / domain-services
- Article date: 2025-01-21
- Summary: Microsoft Entra Domain Services の Secure LDAP を使用して一般的なアラートのトラブルシューティングと解決を行う方法について説明します。

ライトウェイト ディレクトリ アクセス プロトコル (LDAP) を使用して Microsoft Entra Domain Services と通信するアプリケーションおよびサービスは、[Secure LDAP を使用するように構成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-ldaps)できます。 セキュリティで保護された LDAP が正しく機能するためには、適切な証明書と必要なネットワーク ポートを開く必要があります。

この記事は、Domain Services のセキュリティで保護された LDAP アクセスに関する一般的なアラートを理解し、解決するのに役立ちます。

### AADDS101: Secure LDAP ネットワーク構成

#### アラート メッセージ

*マネージド ドメインに対して、インターネット経由の Secure LDAP が有効になっています。 ただし、ポート 636 へのアクセスは、ネットワーク セキュリティ グループを使用してロックダウンされません。 これにより、マネージド ドメイン上のユーザー アカウントがパスワードブルート フォース攻撃にさらされる可能性があります。*

#### 解決策

Secure LDAP を有効にする場合は、受信 LDAPS アクセスを特定の IP アドレスに制限する追加の規則を作成することをお勧めします。 これらの規則は、ブルート フォース攻撃からマネージド ドメインを保護します。 セキュリティで保護された LDAP の TCP ポート 636 アクセスを制限するようにネットワーク セキュリティ グループを更新するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、**ネットワーク セキュリティ グループ**を検索して選択します。
2. マネージド ドメインに関連付けられているネットワーク セキュリティ グループ (*AADDS-contoso.com-NSG*など) を選択し、受信セキュリティ規則 **選択**
3. [**+** の追加] を選択して、TCP ポート 636 の規則を作成します。 必要に応じて、ウィンドウ **[詳細設定]** を選択してルールを作成します。
4. **ソース**で、ドロップダウンメニューから *IPアドレス* を選択します。 セキュリティで保護された LDAP トラフィックへのアクセスを許可するソース IP アドレスを入力します。
5. *宛先*として **任意の** を選択し、*宛先ポート範囲*に「**636**」と入力します。
6. **[プロトコル]** を *[TCP]* に設定し、**[アクション]** を *[許可]* に設定します。
7. ルールの優先順位を指定し、*RestrictLDAPS*などの名前を入力します。
8. 準備ができたら、**[** の追加] を選択してルールを作成します。

マネージド ドメインの正常性は、2 時間以内に自動的に更新され、アラートが削除されます。

ヒント

ドメイン サービスをスムーズに実行するために必要なルールは、TCP ポート 636 だけではありません。 詳細については、[Domain Services ネットワーク セキュリティ グループと必要なポート](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/network-considerations#network-security-groups-and-required-ports)を参照してください。

### AADDS502: Secure LDAP 証明書の有効期限が切れています

#### アラート メッセージ

*マネージド ドメインのセキュリティで保護された LDAP 証明書は 、[日付] に期限切れになります。*

#### 解決策

[Secure LDAP 用の証明書を作成する](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-ldaps#create-a-certificate-for-secure-ldap)手順を実行して、交換用の Secure LDAP 証明書を作成します。 交換証明書を Domain Services に適用し、Secure LDAP を使用して接続するすべてのクライアントに証明書を配布します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/alert-nsg"} -->
## Microsoft Entra Domain Services のネットワーク セキュリティ グループのアラートを解決する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/alert-nsg
- Service: entra-id / domain-services
- Article date: 2025-01-21
- Summary: Microsoft Entra Domain Services のネットワーク セキュリティ グループ構成アラートをトラブルシューティングして解決する方法について説明します

アプリケーションやサービスが Microsoft Entra Domain Services のマネージド ドメインと正しく通信できるようにするには、特定のネットワーク ポートを開いて、トラフィックを通過できるようにする必要があります。 Azure では、ネットワーク セキュリティ グループを使用してトラフィックのフローを制御します。 Domain Services マネージド ドメインの正常性状態は、必要なネットワーク セキュリティ グループ規則が適用されていない場合にアラートを表示します。

この記事は、ネットワーク セキュリティ グループの構成に関する問題の一般的なアラートを理解し、解決するのに役立ちます。

### アラート AADDS104: ネットワーク エラー

#### アラート メッセージ

*"このマネージド ドメインのドメイン コントローラーに到達できません。 これは、仮想ネットワークに構成されているネットワーク セキュリティ グループ (NSG) がマネージド ドメインへのアクセスをブロックしている場合に発生する可能性があります。 もう 1 つの考えられる理由は、インターネットからの着信トラフィックをブロックするユーザー定義ルートがある場合です。*

無効なネットワーク セキュリティ グループ規則は、Domain Services のネットワーク エラーの最も一般的な原因です。 仮想ネットワーク用のネットワーク セキュリティ グループは、特定のポートおよびプロトコルへのアクセスを許可する必要があります。 これらのポートがブロックされている場合、Azure プラットフォームはマネージド ドメインの監視および更新を行うことができません。 Microsoft Entra ディレクトリと Domain Services 間の同期にも影響します。 サービスが中断されないように、既定のポートを開いたままにするようにしてください。

### 既定セキュリティ規則

マネージド ドメインのネットワーク セキュリティ グループには、次の既定のインバウンドとアウトバウンドのセキュリティ規則が適用されます。 これらの規則は Domain Services のセキュリティを維持し、Azure プラットフォームがマネージド ドメインを監視、管理、および更新できるようにします。

#### インバウンドのセキュリティ規則

| 優先順位 | 名前 | ポート | プロトコル | Source | 宛先 | アクション |
| --- | --- | --- | --- | --- | --- | --- |
| 301 | AllowPSRemoting | 5986 | TCP | AzureActiveDirectoryDomainServices | [任意] | 許可する |
| 201 | AllowRD | 3389 | TCP | CorpNetSaw | [任意] | 許可^1^ |
| 65000 | AllVnetInBound | [任意] | [任意] | 仮想ネットワーク | 仮想ネットワーク | 許可する |
| 65001 | AllowAzureLoadBalancerInBound | [任意] | [任意] | AzureLoadBalancer | [任意] | 許可する |
| 65500 | DenyAllInBound | [任意] | [任意] | [任意] | [任意] | 拒否 |

^1^デバッグ時に任意で許可できますが、不要な場合は既定設定を拒否に変更してください。 高度なトラブルシューティングが必要な場合は、そのルールを許可してください。

注

[セキュリティで保護された LDAP を構成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-ldaps)する場合は、受信トラフィックを許可する規則もあります。 この規則は、正しい LDAPS 通信に必要です。

#### アウトバウンドのセキュリティ規則

| 優先順位 | 名前 | ポート | プロトコル | Source | 宛先 | アクション |
| --- | --- | --- | --- | --- | --- | --- |
| 65000 | AllVnetOutBound | [任意] | [任意] | 仮想ネットワーク | 仮想ネットワーク | 許可する |
| 65001 | AllowAzureLoadBalancerOutBound | [任意] | [任意] | [任意] | インターネット | 許可する |
| 65500 | DenyAllOutBound | [任意] | [任意] | [任意] | [任意] | 拒否 |

注

Domain Services には、仮想ネットワークからの無制限の送信アクセスが必要です。 仮想ネットワークの送信アクセスを制限する他の規則を作成することはお勧めしません。

### 既存のセキュリティ規則を確認および編集する

既存のセキュリティ規則を確認し、既定のポートが開いていることを確実にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、**[ネットワーク セキュリティ グループ]** を検索して選択します。
2. マネージド ドメインに関連付けられているネットワーク セキュリティ グループ (*AADDS-contoso.com-NSG* など) を選択します。
3. **[概要]** ページに、既存のインバウンドとアウトバウンドのセキュリティ規則が表示されます。

    インバウンド規則とアウトバウンド規則を確認し、前のセクションにある必要な規則リストと比較します。 必要に応じて、必要なトラフィックをブロックするカスタム規則を選択して削除します。 必要な規則のいずれかが不足している場合は、次のセクションで規則を追加します。

    必要なトラフィックを許可するために規則を追加または削除した後、マネージド ドメインの正常性が 2 時間以内に自動的に更新され、アラートが削除されます。

#### セキュリティ規則を追加する

不足しているセキュリティ規則を追加するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、**[ネットワーク セキュリティ グループ]** を検索して選択します。
2. マネージド ドメインに関連付けられているネットワーク セキュリティ グループ (*AADDS-contoso.com-NSG* など) を選択します。
3. 左側のパネルの **[設定]** で、追加する必要がある規則に応じて、[ *受信セキュリティ規則* ] または [ *送信セキュリティ規則* ] を選択します。
4. **[追加]** を選択し、ポート、プロトコル、方向などに基づいて必要なルールを作成します。 準備ができたら **[OK]** を選択します。

セキュリティ規則が追加されて一覧に表示されるまでにしばらく時間がかかります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/alert-service-principal"} -->
## Microsoft Entra Domain Services のサービス プリンシパル アラートを解決する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/alert-service-principal
- Service: entra-id / domain-services
- Article date: 2025-01-21
- Summary: Microsoft Entra Domain Services のサービス プリンシパル構成アラートのトラブルシューティングを行う方法について説明します

[サービス プリンシパル](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/app-objects-and-service-principals)は、Azure プラットフォームが Microsoft Entra Domain Services マネージド ドメインの管理、更新、維持を行うために使用するアプリケーションです。 サービス プリンシパルが削除されると、マネージド ドメインの機能に影響します。

この記事は、サービス プリンシパルに関連した構成アラートのトラブルシューティングと解決に役立ちます。

### アラート AADDS102: Service principal not found (サービス プリンシパルが見つかりません)

#### アラート メッセージ

*Microsoft Entra Domain Services が正常に機能するために必要なサービス プリンシパルが Microsoft Entra ディレクトリから削除されました。 この構成は、マネージド ドメインに対する監視、管理、修正のプログラム適用、および同期を行うための Microsoft の能力に影響します。"*

必要なサービス プリンシパルが削除された場合、Azure プラットフォームは自動化された管理タスクを実行できません。 マネージド ドメインで、更新プログラムの適用およびバックアップの実行が正しく行われない可能性があります。

#### 不足しているサービス プリンシパルを確認する

不足していて再作成が必要なサービス プリンシパルを確認するには、次の手順を行います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、**[エンタープライズ アプリケーション]** を検索して選択します。 *[アプリケーションの種類]* ドロップダウン メニューの **[すべてのアプリケーション]** を選択し、 **[適用]** を選択します。
2. 次のアプリケーション ID をそれぞれ検索します。 Azure Global の場合は、AppId 値 `2565bd9d-da50-47d4-8b85-4c97f669dc36` を検索します。 他の Azure クラウドの場合は、AppId 値 `6ba9a5d4-8456-4118-b521-9c5ca10cdf84` を検索します。 既存のアプリケーションが見つからない場合は、*解決策*の手順に従ってサービス プリンシパルを作成するか、名前空間を再登録します。

    | アプリケーション ID | 解決方法 |
    | --- | --- |
    | 2565bd9d-da50-47d4-8b85-4c97f669dc36 | 不足しているサービス プリンシパルを再作成する |
    | 443155a6-77f3-45e3-882b-22b3a8d431fb | `Microsoft.AAD` 名前空間を再登録する |
    | abba844e-bc0e-44b0-947a-dc74e5d09022 | `Microsoft.AAD` 名前空間を再登録する |
    | d87dcbc6-a371-462e-88e3-28ad15ec4e64 | `Microsoft.AAD` 名前空間を再登録する |

#### 不足しているサービス プリンシパルを再作成する

アプリケーション ID *2565bd9d-da50-47d4-8b85-4c97f669dc36* が Azure Global の Microsoft Entra ディレクトリにない場合は、Microsoft Graph PowerShell を使って以下の手順を実行します。 他の Azure クラウドの場合は、AppId 値 *6ba9a5d4-8456-4118-b521-9c5ca10cdf84* を使用します。 詳細については、[Microsoft Graph PowerShell SDK のインストール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)に関する記事を参照してください。

1. 必要に応じて、次のように Microsoft Graph PowerShell モジュールをインストールし、インポートします。

    ```powershell
    Install-Module Microsoft.Graph -Scope CurrentUser
    ```
2. 次に、[New-MgServicePrincipal][/powershell/module/microsoft.graph.applications/new-mgserviceprincipal] コマンドレットを使ってサービス プリンシパルを再作成します。

    ```powershell
    New-MgServicePrincipal -AppId "2565bd9d-da50-47d4-8b85-4c97f669dc36"
    ```

マネージド ドメインの正常性が 2 時間以内に自動的に更新され、アラートが削除されます。

#### Microsoft Entra 名前空間を再登録する

アプリケーション ID `443155a6-77f3-45e3-882b-22b3a8d431fb`、`abba844e-bc0e-44b0-947a-dc74e5d09022`、`d87dcbc6-a371-462e-88e3-28ad15ec4e64` のいずれかが Microsoft Entra ディレクトリにない場合は、以下の手順を実行して `Microsoft.AAD` リソース プロバイダーを Azure サブスクリプションに対して再登録します。

1. [Azure portal](https://portal.azure.com) で、**[サブスクリプション]** を検索して選択します。
2. マネージド ドメインに関連付けられているサブスクリプションを選択します。
3. 左側のナビゲーションから **[設定]** を展開してから、**[リソース プロバイダー]** を選択します。
4. `Microsoft.AAD` を検索し、**[再登録]** を選択します。

マネージド ドメインの正常性が 2 時間以内に自動的に更新され、アラートが削除されます。

### アラート AADDS105: Password synchronization application is out of date (パスワード同期アプリケーションの期限が切れています)

#### アラート メッセージ

*アプリケーション ID "d87dcbc6-a371-462e-88e3-28ad15ec4e64" のサービス プリンシパルは削除されましたが、その後再作成されました。 再作成により、マネージド ドメインの提供に必要な Microsoft Entra Domain Services リソースに整合性のないアクセス許可が残ります。 Synchronization of passwords on your managed domain could be affected. (マネージド ドメインでのパスワードの同期が影響を受ける可能性があります。)*

Domain Services は、Microsoft Entra ID からユーザー アカウントと資格情報を自動的に同期します。 このプロセスに使用される Microsoft Entra アプリケーションに問題がある場合、Domain Services と Microsoft Entra ID の間の資格情報の同期は失敗します。

#### 解決方法

資格情報の同期に使われる Microsoft Entra アプリケーションを再作成するには、Microsoft Graph PowerShell を使って以下の手順を実行します。 詳細については、[Microsoft Graph PowerShell SDK のインストール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)に関する記事を参照してください。

1. 必要に応じて、次のように Microsoft Graph PowerShell モジュールをインストールし、インポートします。

    ```powershell
    Install-Module Microsoft.Graph -Scope CurrentUser
    ```
2. それから、次の PowerShell コマンドレットを使用して古いアプリケーションとオブジェクトを削除します。

    ```powershell
    Install-Module Microsoft.Graph -Scope CurrentUser
    Connect-MgGraph -Scopes "Application.ReadWrite.All"
    $app = Get-MgApplication -Filter "DisplayName eq 'Azure AD Domain Services Sync'"
    Remove-MgApplication -ApplicationId $app.Id   
    ```

両方のアプリケーションを削除した後、Azure プラットフォームによってそれらが自動的に再作成され、パスワード同期の再開が試行されます。 マネージド ドメインの正常性が 2 時間以内に自動的に更新され、アラートが削除されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/change-sku"} -->
## Microsoft Entra Domain Services の SKU を変更する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/change-sku
- Service: entra-id / domain-services
- Article date: 2025-01-21
- Summary: ビジネス要件が変更された場合の Microsoft Entra Domain Services マネージド ドメインの SKU レベルの方法について説明します

Microsoft Entra Domain Services では、使用可能なパフォーマンスと機能は SKU の種類に基づいています。 これらの機能の違いには、バックアップの頻度や、一方向の外向きフォレスト信頼の最大数が含まれます。

マネージド ドメインを作成するときに SKU を選択し、マネージド ドメインのデプロイ後にビジネス ニーズが変化したときに SKU を上下に切り替えることができます。 ビジネス要件の変更には、より頻繁なバックアップの必要性や、追加のフォレスト信頼の作成が含まれる場合があります。 さまざまな SKU の制限と価格の詳細については、 [Domain Services SKU の概念と Domain Services](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/administration-concepts#azure-ad-ds-skus) の [価格](https://azure.microsoft.com/pricing/details/active-directory-ds/) に関するページを参照してください。

この記事では、 [Microsoft Entra 管理センター](https://entra.microsoft.com)を使用して、既存の Domain Services マネージド ドメインの SKU を変更する方法について説明します。

### 開始する前に

この記事を完了するには、以下のリソースと特権が必要です。

- 有効な Azure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、 [アカウントを作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、 [Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization) するか、 [Azure サブスクリプションをアカウントに関連付けます](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)。
- Microsoft Entra Domain Services の管理されたドメインが有効化されており、Microsoft Entra テナント内で設定されている。
    - 必要に応じて、チュートリアルを完了して [マネージド ドメインを作成して構成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)。

### SKU の変更の制限事項

マネージド ドメインがデプロイされた後で、SKU を上下に変更できます。 ただし、 *Premium* SKU と *Enterprise* SKU では、作成できる信頼の数に制限が定義されています。 現在構成している SKU よりも上限が低い SKU に変更することはできません。

たとえば、 *Premium* SKU で 7 つの信頼を作成した場合、 *Enterprise* SKU に変更することはできません。 *Enterprise* SKU では、最大 5 つの信頼がサポートされます。

これらの制限の詳細については、「 [Domain Services SKU の機能と制限](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/administration-concepts#azure-ad-ds-skus)」を参照してください。

### 新しい SKU を選択する

[Microsoft Entra 管理センター](https://entra.microsoft.com)を使用してマネージド ドメインの SKU を変更するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、**Microsoft Entra Domain Services** を検索して選択します。 aaddscontoso.com など、一覧からマネージド ドメイン *を*選択します。
2. [ドメイン サービス] ページの左側にあるメニューで、[ **設定] &gt; SKU** を選択します。

    [Image: Microsoft Entra 管理センターで、Domain Services マネージド ドメインの SKU メニュー オプションを選択します]
3. ドロップダウン メニューから、マネージド ドメインに必要な SKU を選択します。 リソース フォレストがある場合、フォレストの信頼は *Enterprise* SKU 以上でのみ使用できるため、*Standard* SKU を選択することはできません。

    ドロップダウン メニューから目的の SKU を選択し、[ **保存]** を選択します。

    [Image: Microsoft Entra 管理センターのドロップダウン メニューから必要な SKU を選択します]

SKU の種類を変更するには、1 ~ 2 分かかる場合があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/check-health"} -->
## Microsoft Entra Domain Services の正常性を確認する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/check-health
- Service: entra-id / domain-services
- Article date: 2025-01-21
- Summary: Microsoft Entra Domain Services マネージド ドメインの正常性を調べる方法と、ステータス メッセージを理解する方法について説明します。

Microsoft Entra Domain Services では、マネージド ドメインを正常かつ最新の状態に保つために、いくつかのバックグラウンド タスクが実行されます。 これらのタスクには、バックアップの取得、セキュリティ更新プログラムの適用、および Microsoft Entra ID からのデータの同期が含まれます。 Domain Services マネージド ドメインに問題がある場合、これらのタスクは正常に完了しないことがあります。 問題を確認して解決するには、Microsoft Entra 管理センターを使って、マネージド ドメインの正常性状態を調べることができます。

この記事では、Domain Services の正常性状態を表示し、表示されている情報またはアラートを理解する方法を示します。

### 正常性状態を表示する

マネージド ドメインの正常性状態は、Microsoft Entra 管理センターを使うと表示されます。 最後のバックアップ時刻と Microsoft Entra ID との同期に関する情報が、マネージド ドメインの正常性に関する問題を示すアラートと共に表示されます。 マネージド ドメインの正常性状態を表示するには、次の手順を完了します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. **[Microsoft Entra Domain Services]** を検索して選択します。
3. 目的のマネージド ドメインを選択します (例: *aaddscontoso.com*)。
4. Domain Services リソース ウィンドウの左側で、**[正常性]** を選択します。 次のスクリーンショット例は、正常なマネージド ドメインと、最後のバックアップおよび Microsoft Entra 同期の状態を示しています。

    [Image: Microsoft Entra Domain Services の状態を示す、正常性ページの概要]

正常性ページの "*最終評価日時*" タイムスタンプは、マネージド ドメインが最後に確認された日時を示します。 マネージド ドメインの正常性は、1 時間ごとに評価されます。 マネージド ドメインに変更を加えた場合は、次の評価サイクルで更新された正常性状態が表示されるまで待ちます。

右上の状態は、マネージド ドメインの全体的な正常性を示しています。 この状態には、ドメイン上の既存のアラートがすべて反映されます。 次の表で、使用可能な状態インジケーターについて詳しく説明します。

| ステータス | アイコン | 説明 |
| --- | --- | --- |
| 実行中 | [Image: 実行中を示す緑色のチェックマーク] | マネージド ドメインは正しく実行されており、重大なアラートや警告アラートはありません。 ドメインでは、情報アラートが発生している場合があります。 |
| 要注意 (警告) | [Image: 警告を示す黄色の感嘆符] | マネージド ドメインに重大なアラートはありませんが、対処する必要がある 1 つ以上の警告アラートがあります。 |
| 要注意 (重大) | [Image: 重大を示す赤色の感嘆符] | マネージド ドメインには、対処する必要がある重大なアラートが 1 つ以上あります。 警告や情報アラートが発生している場合もあります。 |
| デプロイ中 | [Image: デプロイ中を示す青色の円形の矢印] | マネージド ドメインはデプロイ中です。 |

### モニターとアラートについて

マネージド ドメインの正常性状態では、"*モニター*" と "*アラート*" という 2 種類の情報が示されます。 モニターでは、主要なバックグラウンド タスクが完了した時刻が示されます。 アラートでは、マネージド ドメインの安定性を向上させるための情報や提案が提供されます。

#### モニター

モニターは、定期的に確認されるマネージド ドメインの領域です。 マネージド ドメインのアクティブなアラートがある場合は、いずれかのモニターによって問題が報告されることがあります。 Domain Services には現在、次の領域のモニターがあります。

- バックアップ
- Microsoft Entra ID との同期

##### バックアップ モニター

バックアップ モニターは、マネージド ドメインの定期的な自動バックアップが正常に実行されていることを確認します。 次の表で、使用可能なバックアップ モニターの状態について詳しく説明します。

| 詳細の値 | 説明 |
| --- | --- |
| バックアップされていません | 新しいマネージド ドメインの場合、この状態は正常です。 最初のバックアップは、マネージド ドメインがデプロイされてから 24 時間後に作成されるはずです。 この状態が続く場合は、[Azure サポート リクエストを開きます](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-to-get-support)。 |
| 1 ～ 14 日前に最後のバックアップが行われました | この時間範囲は、バックアップ モニターの予期される状態です。 定期的な自動バックアップは、この期間内に行われるはずです。 |
| 最後のバックアップは 14 日より以前に行われました。 | 期間が 2 週間を超えている場合は、定期的な自動バックアップに問題があることを示しています。 重大なアクティブ アラートがあると、マネージド ドメインがバックアップされない場合があります。 マネージド ドメインのアクティブなアラートをすべて解決します。 その後、バックアップ モニターで最新のバックアップを報告するために状態が更新されない場合は、[Azure サポート リクエストを開きます](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-to-get-support)。 |

##### Microsoft Entra ID との同期モニター

マネージド ドメインは、AMicrosoft Entra ID と定期的に同期されます。 ユーザーとグループ オブジェクトの数、および前回の同期以降に Microsoft Entra ディレクトリに加えられた変更の数は、同期にかかる時間に影響します。 マネージド ドメインが最後に同期されたのが 3 日以上前の場合は、アクティブなアラートを確認して解決します。 アクティブなアラートに対処した後、同期モニターで最新の同期を表示するために状態が更新されない場合は、[Azure サポート リクエストを開きます](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-to-get-support)。

#### 警告

サービスを正しく実行するために対処する必要がある、マネージド ドメインでの問題に対してアラートが生成されます。 アラートごとに問題の説明と、問題を解決するための具体的な手順を概説する URL が示されます。 考えられるアラートとその解決策の詳細については、[アラートのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/troubleshoot-alerts)に関するページを参照してください。

正常性状態のアラートは、次の重大度レベルに分類されます。

- **重大なアラート**は、マネージド ドメインに深刻な影響を与える問題です。 これらのアラートは直ちに対処する必要があります。 Azure プラットフォームでは、問題が解決されるまで、マネージド ドメインの監視、管理、修正プログラムの適用、および同期を行うことはできません。
- **警告アラート**では、問題が解決しない場合にマネージド ドメインの操作に影響する可能性がある問題を通知します。 これらのアラートでは、マネージド ドメインをセキュリティで保護するための推奨事項も提供されます。
- **情報アラート**は、マネージド ドメインに悪影響を与えない通知です。 情報アラートで、マネージド ドメインで何が起こっているかについて洞察できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/compare-identity-solutions"} -->
## Microsoft ディレクトリ ベースのサービスを比較する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/compare-identity-solutions
- Service: entra-id / domain-services
- Article date: 2025-06-30
- Summary: この概要では、Active Directory Domain Services、Microsoft Entra ID、および Microsoft Entra Domain Services のさまざまな ID オファリングを比較します。

アプリケーション、サービス、またはデバイスに中央 ID へのアクセスを提供するには、Azure で Active Directory ベースのサービスを使用する 3 つの一般的な方法があります。 ID ソリューションでのこの選択により、組織のニーズに最適なディレクトリを柔軟に使用できます。 たとえば、モバイル デバイスを実行するクラウドのみのユーザーを主に管理している場合、独自の Active Directory Domain Services (AD DS) ID ソリューションを構築して実行しても意味がない場合があります。 代わりに、Microsoft Entra ID のみを使用できます。

3 つの Active Directory ベースの ID ソリューションは共通の名前とテクノロジを共有しますが、さまざまな顧客の要求を満たすサービスを提供するように設計されています。 大まかに言えば、これらの ID ソリューションと機能セットは次のとおりです。

- **Active Directory Domain Services (AD DS)**- ID と認証、コンピューター オブジェクト管理、グループ ポリシー、信頼などの主要な機能を提供するエンタープライズ対応のライトウェイト ディレクトリ アクセス プロトコル (LDAP) サーバー。
    - AD DS は、オンプレミスの IT 環境を使用する多くの組織で中心的なコンポーネントであり、主要なユーザー アカウント認証機能とコンピューター管理機能が提供されます。
    - 詳細については、 [Windows Server ドキュメントの Active Directory Domain Services の概要を参照してください](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/get-started/virtual-dc/active-directory-domain-services-overview)。
- **Microsoft Entra ID**- クラウドベースの ID とモバイル デバイス管理。Microsoft 365、Microsoft Entra 管理センター、SaaS アプリケーションなどのリソースのユーザー アカウントと認証サービスを提供します。
    - Microsoft Entra ID をオンプレミスの AD DS 環境と同期して、クラウドでネイティブに動作するユーザーに 1 つの ID を提供できます。
    - Microsoft Entra ID の詳細については、「[Microsoft Entra ID とは](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/whatis)」を参照してください。
- **Microsoft Entra Domain Services**- ドメイン参加、グループ ポリシー、LDAP、Kerberos/NTLM 認証など、完全に互換性のある従来の AD DS 機能のサブセットを持つマネージド ドメイン サービスを提供します。
    - Domain Services は Microsoft Entra ID と統合され、それ自体がオンプレミスの AD DS 環境と同期できます。 この機能により、リフトアンドシフト戦略の一環として Azure で実行される従来の Web アプリケーションに、中央 ID ユース ケースが拡張されます。
    - Microsoft Entra ID とオンプレミスとの同期の詳細については、「 [マネージド ドメインでのオブジェクトと資格情報の同期方法](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/synchronization)」を参照してください。

この概要記事では、組織のニーズに応じて、これらの ID ソリューションを連携させたり、個別に使用したりする方法を比較して比較します。

### Domain Services とセルフマネージド AD DS

Kerberos や NTLM などの従来の認証メカニズムにアクセスする必要があるアプリケーションとサービスがある場合、クラウドで Active Directory Domain Services を提供するには、次の 2 つの方法があります。

- Microsoft Entra Domain Services を使用して作成する *マネージド* ドメイン。 Microsoft は、必要なリソースを作成および管理します。
- 仮想マシン (VM)、Windows Server ゲスト OS、Active Directory Domain Services (AD DS) などの従来のリソースを使用して作成および構成する *セルフマネージド* ドメイン。 その後、これらのリソースを引き続き管理します。

Domain Services では、コア サービス コンポーネントが *マネージド* ドメイン エクスペリエンスとして Microsoft によってデプロイおよび保守されます。 VM、Windows Server OS、ドメイン コントローラー (DC) などのコンポーネントの AD DS インフラストラクチャの展開、管理、修正プログラムの適用、セキュリティ保護は行いません。

Domain Services では、従来のセルフマネージド AD DS 環境に対して機能のサブセットが小さくなり、設計と管理の複雑さの一部が軽減されます。 たとえば、設計と保守のための AD フォレスト、ドメイン、サイト、レプリケーション リンクはありません。 それでも [Domain Services とオンプレミス環境の間にフォレストの信頼を作成する](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-forest-trust)ことができます。

クラウドで実行され、Kerberos や NTLM などの従来の認証メカニズムにアクセスする必要があるアプリケーションとサービスの場合、Domain Services は管理オーバーヘッドを最小限に抑えてマネージド ドメイン エクスペリエンスを提供します。 詳細については、「 [Domain Services のユーザー アカウント、パスワード、管理の管理の概念](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/administration-concepts)」を参照してください。

自己管理 AD DS 環境をデプロイして実行する場合は、関連するすべてのインフラストラクチャとディレクトリ コンポーネントを維持する必要があります。 セルフマネージド AD DS 環境では追加のメンテナンス オーバーヘッドが発生しますが、スキーマの拡張やフォレストの信頼の作成などの追加のタスクを実行できます。

クラウド内のアプリケーションとサービスに ID を提供する自己管理 AD DS 環境の一般的なデプロイ モデルには、次のようなものがあります。

- **スタンドアロンクラウド専用 AD DS** - Azure VM はドメイン コントローラーとして構成され、別のクラウド専用 AD DS 環境が作成されます。 この AD DS 環境は、オンプレミスの AD DS 環境と統合されません。 クラウドでの VM のサインインと管理には、別の資格情報セットが使用されます。
- **オンプレミス ドメインを Azure に拡張する**- Azure 仮想ネットワークは、VPN/ExpressRoute 接続を使用してオンプレミス ネットワークに接続します。 Azure VM はこの Azure 仮想ネットワークに接続します。これにより、オンプレミスの AD DS 環境にドメイン参加できます。
    - 別の方法として、Azure VM を作成し、オンプレミスの AD DS ドメインからレプリカ ドメイン コントローラーとして昇格させる方法があります。 これらのドメイン コントローラーは、VPN/ExpressRoute 接続を介してオンプレミスの AD DS 環境にレプリケートします。 オンプレミスの AD DS ドメインは、実質的に Azure に拡張されます。

次の表は、組織に必要な機能の一部と、マネージド ドメインと自己管理 AD DS ドメインの違いを示しています。

| **特徴** | **マネージド ドメイン** | **セルフマネージド AD DS** |
| --- | --- | --- |
| **マネージド サービス** | **✓** | **✕** |
| **セキュリティで保護されたデプロイ** | **✓** | 管理者がデプロイをセキュリティで保護する |
| **DNS サーバー** | **✓** (マネージド サービス) | **✓** |
| **ドメインまたはエンタープライズ管理者特権** | **✕** | **✓** |
| **ドメイン参加** | **✓** | **✓** |
| **NTLM と Kerberos を使用したドメイン認証** | **✓** | **✓** |
| **Kerberos の制約付き委任** | リソースベース | リソースベースとアカウントベース |
| **カスタム OU 構造** | **✓** | **✓** |
| **グループ ポリシー** | **✓** | **✓** |
| **スキーマ拡張機能** | **✕** | **✓** |
| **AD ドメイン / フォレスト トラスト** | **✓** (エンタープライズ SKU が必要) | **✓** |
| **Secure LDAP (LDAPS)** | **✓** | **✓** |
| **LDAP 読み取り** | **✓** | **✓** |
| **LDAP 書き込み** | **✓** (マネージド ドメイン内) | **✓** |
| **地理的に分散されたデプロイ** | **✓** | **✓** |

### Domain Services と Microsoft Entra ID

Microsoft Entra ID を使用すると、組織で使用されるデバイスの ID を管理し、それらのデバイスから企業リソースへのアクセスを制御できます。 ユーザーは、個人のデバイス (BRING Your Own (BYO) モデル) を Microsoft Entra ID に登録することもできます。この ID は、デバイスに ID を提供します。 ユーザーが Microsoft Entra ID にサインインし、デバイスを使用してセキュリティで保護されたリソースにアクセスすると、Microsoft Entra ID によってデバイスが認証されます。 デバイスは、Microsoft Intune などのモバイル デバイス管理 (MDM) ソフトウェアを使用して管理できます。 この管理機能を使用すると、機密性の高いリソースへのアクセスを管理対象デバイスとポリシー準拠デバイスに制限できます。

従来のコンピューターとノート PC も Microsoft Entra ID に参加できます。 このメカニズムには、ユーザーが会社の資格情報を使用してデバイスにサインインできるようにするなど、個人のデバイスを Microsoft Entra ID に登録するのと同じ利点があります。

Microsoft Entra 参加済みデバイスには、次の利点があります。

- Microsoft Entra ID によってセキュリティ保護されたアプリケーションへのシングル サインオン (SSO)。
- デバイス間でのユーザー設定のエンタープライズ ポリシー準拠ローミング。
- 企業の資格情報を使用したビジネス向け Windows ストアへのアクセス。
- Windows Hello for Business。
- 会社のポリシーに準拠しているデバイスからのアプリとリソースへのアクセスを制限しました。

デバイスは、オンプレミスの AD DS 環境を含むハイブリッド展開の有無に関係なく、Microsoft Entra ID に参加できます。 次の表は、一般的なデバイス所有権モデルと、通常ドメインに参加する方法の概要を示しています。

| **デバイスの種類** | **デバイス プラットフォーム** | **メカニズム** |
| --- | --- | --- |
| 個人用デバイス | Windows 10、iOS、Android、macOS | Microsoft Entra 登録済み |
| オンプレミスの AD DS に参加していない組織所有のデバイス | Windows 10 | Microsoft Entra 参加済み |
| オンプレミスの AD DS に参加している組織所有のデバイス | Windows 10 | Microsoft Entra ハイブリッド参加済み |

Microsoft Entra 参加済みまたは登録済みデバイスでは、最新の OAuth/OpenID Connect ベースのプロトコルを使用してユーザー認証が行われます。 これらのプロトコルはインターネット経由で動作するように設計されているため、ユーザーがどこからでも企業リソースにアクセスするモバイル シナリオに最適です。

Domain Services に参加しているデバイスでは、アプリケーションで認証に Kerberos プロトコルと NTLM プロトコルを使用できるため、リフトアンドシフト戦略の一環として Azure VM 上で実行するように移行されたレガシ アプリケーションをサポートできます。 次の表は、デバイスがどのように表され、ディレクトリに対して自身を認証できるかの違いを示しています。

| **特徴** | **Microsoft Entra 参加** | **ドメインサービスに参加済み** |
| --- | --- | --- |
| デバイスの制御 | マイクロソフト エントラ ID | ドメイン サービスの管理対象ドメイン |
| ディレクトリ内の表現 | Microsoft Entra ディレクトリ内のデバイス オブジェクト | Domain Services マネージド ドメイン内のコンピューター オブジェクト |
| 認証 | OAuth/OpenID Connect ベースのプロトコル | Kerberos プロトコルと NTLM プロトコル |
| 管理 | Intune などのモバイル デバイス管理 (MDM) ソフトウェア | グループ ポリシー |
| ネットワーキング | インターネット経由で動作する | マネージド ドメインがデプロイされている仮想ネットワークに接続されているか、ピアリングされている必要があります |
| 最適な対象 | エンドユーザーのモバイル デバイスまたはデスクトップ デバイス | Azure にデプロイされたサーバー VM |

オンプレミスの AD DS と Microsoft Entra ID が AD FS を使用したフェデレーション認証用に構成されている場合、Azure DS で使用できる (現在または有効な) パスワード ハッシュはありません。 Fed 認証が実装される前に作成された Microsoft Entra ユーザー アカウントには古いパスワード ハッシュが含まれる可能性がありますが、これはオンプレミスのパスワードのハッシュと一致しない可能性があります。 その結果、Domain Services はユーザーの資格情報を検証できません
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/concepts-custom-attributes"} -->
## Microsoft Entra Domain Services のカスタム属性を作成および管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/concepts-custom-attributes
- Service: entra-id / domain-services
- Article date: 2025-03-07
- Summary: Domain Services マネージド ドメインでカスタム属性を作成および管理する方法について説明します。

多くの場合、さまざまな理由で、企業はレガシ アプリのコードを変更できません。 たとえば、アプリではカスタム従業員 ID などのカスタム属性を使用し、LDAP 操作にその属性を使用できます。

Microsoft Entra IDでは、[extensions](https://learn.microsoft.com/ja-jp/graph/extensibility-overview) を使用したリソースへのカスタム データの追加がサポートされています。 Microsoft Entra Domain Services では、Microsoft Entra IDから次の種類の拡張機能を同期できるため、Domain Services でカスタム属性に依存するアプリを使用することもできます。

- [onPremisesExtensionAttributes](https://learn.microsoft.com/ja-jp/graph/extensibility-overview?tabs=http#extension-attributes) は、拡張ユーザー文字列属性を格納できる 15 個の属性のセットです。
- [ディレクトリ拡張機能](https://learn.microsoft.com/ja-jp/graph/extensibility-overview?tabs=http#directory-azure-ad-extensions) を使用すると、テナント内のアプリケーションへの登録を通じて、厳密に型指定された属性を持つ特定のディレクトリ オブジェクト (ユーザーやグループなど) のスキーマ拡張を行えます。

どちらの種類の拡張機能も、オンプレミスで管理されているユーザーに対して Microsoft Entra Connect を使用するか、クラウド専用ユーザーの API をMicrosoft Graphして構成できます。

注

同期では、次の種類の拡張機能はサポートされていません。

- Microsoft Entra IDのカスタム セキュリティ属性
- Microsoft Graph スキーマ拡張機能
- Microsoft Graph拡張機能を開く

### 要求事項

カスタム属性でサポートされる最小 SKU は、Enterprise SKU です。 Standard を使用する場合は、マネージド ドメインを Enterprise または Premium に [アップグレード](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/change-sku) する必要があります。 詳細については、「[Microsoft Entra Domain Pricing](https://azure.microsoft.com/pricing/details/active-directory-ds/)」を参照してください。

### カスタム属性のしくみ

マネージド ドメインを作成したら、[**設定]** の下**にある [カスタム属性**] をクリックして属性の同期を有効にします。 [ **保存]** をクリックして変更を確定します。

[Image: カスタム属性を有効にする方法のスクリーンショット。]

### 定義済みの属性同期を有効にする

**OnPremisesExtensionAttributes** をクリックして、属性 extensionAttribute1-15 ([Exchange カスタム属性](https://learn.microsoft.com/ja-jp/graph/api/resources/onpremisesextensionattributes)とも呼ばれます) を同期します。

### Microsoft Entra でディレクトリ拡張属性を同期する

これらは、Microsoft Entra テナントで定義されている拡張ユーザーまたはグループ属性です。

**[+ 追加]** を選択して、同期するカスタム属性を選択します。 一覧には、テナントで使用可能な拡張機能のプロパティが表示されます。 検索バーを使用してリストをフィルター処理できます。

[Image: ディレクトリ拡張機能の属性を追加する方法のスクリーンショット。]

探しているディレクトリ拡張機能が表示されない場合は、拡張機能に関連付けられているアプリケーション appId を入力し、[ **検索** ] をクリックして、そのアプリケーションで定義されている拡張機能のプロパティのみを読み込みます。 この検索は、複数のアプリケーションがテナント内に多数の拡張機能を定義する場合に役立ちます。

注

Microsoft Entra Connect によって同期されたディレクトリ拡張機能を表示する場合は、**Enterprise App** をクリックし、**Tenant Schema Extension App** のアプリケーション ID を探します。 詳細については、「[Microsoft Entra Connect Sync: Directory 拡張機能](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/how-to-connect-sync-feature-directory-extensions#configuration-changes-in-azure-ad-made-by-the-wizard)」を参照してください。

[ **選択**] をクリックし、[ **保存]** をクリックして変更を確定します。

[Image: ディレクトリ拡張機能の属性を保存する方法のスクリーンショット。]

Domain Services バックフィルでは、同期されたユーザーやグループのすべてにオンボードされたカスタム属性値が補完されます。 Microsoft Entra IDのディレクトリ拡張機能を含むオブジェクトには、カスタム属性値が徐々に設定されます。 バックフィル同期プロセス中、Microsoft Entra IDの増分変更は一時停止され、同期時間はテナントのサイズによって異なります。

バックフィルの状態を確認するには、**Domain Services Health** をクリックし、**synchronization with Microsoft Entra ID** monitor にオンボード後 1 時間以内に更新されたタイムスタンプがあることを確認します。 更新が完了すると、バックフィルが完了します。

### Active Directory Domain Servicesの予約済み属性

次の属性は、Windows ServerのActive Directory Domain Services用に予約されています。 Microsoft Entra Domain Services には使用できません。

| 名前 | 特性 |
| --- | --- |
| アカウントが無効になっています | アカウントが無効化されました |
| AzureAdMailNickname | msDS-AzureADMailNickname |
| AadObjectId | msDS-aadObjectId |
| 市区町村 | l |
| CommonName | cn |
| Company | company |
| Country | co |
| Department | 部署 |
| 説明 | 説明 |
| DisplayName | displayName |
| DistinguishedName | distinguishedName |
| 社員ID | 従業員ID |
| エクスチェンジエクステンションズ | extensionAttribute |
| ExchangeExtension1 | extensionAttribute1 |
| ExchangeExtension2 | extensionAttribute2 |
| ExchangeExtension3 | extensionAttribute3 |
| ExchangeExtension4 | extensionAttribute4 |
| ExchangeExtension5 | extensionAttribute5 |
| ExchangeExtension6 | extensionAttribute6 |
| ExchangeExtension7 | extensionAttribute7 |
| ExchangeExtension8 | extensionAttribute8 |
| ExchangeExtension9 | extensionAttribute9 |
| ExchangeExtension10 | extensionAttribute10 |
| ExchangeExtension11 | extensionAttribute11 |
| ExchangeExtension12 | extensionAttribute12 |
| ExchangeExtension13 | extensionAttribute13 |
| ExchangeExtension14 | extensionAttribute14 |
| ExchangeExtension15 | extensionAttribute15 |
| ファクシミリ電話番号 | ファクシミリ電話番号 |
| GenerationSeq | msDS-generationSeq |
| GivenName | givenName |
| グループタイプ | groupType |
| リンクシーク | msDS-linkSeq |
| 郵便 | メール |
| マネージャー | マネージャー |
| Member | メンバー |
| MemberOf | memberOf |
| モバイル | モバイル |
| ObjectClass | オブジェクトクラス |
| ObjectGloballyUniqueIdentifier | objectGUID（オブジェクトGUID） |
| Pager | ポケットベル |
| PhysicalDeliveryOfficeName | physicalDeliveryOfficeName |
| 郵便番号 | 郵便番号 |
| PreferredLanguage | 優先言語 |
| プロキシアドレス | プロキシアドレス |
| PasswordLastSet | pwdLastSet |
| SamAccountName（SAMアカウント名） | sAMAccountName |
| セキュリティ記述子 | nTSecurityDescriptor |
| シドヒストリー | sIDHistory |
| 状態 | st |
| StreetAddress | streetAddress |
| 名字 | sn |
| 追加の認証情報 | 追加の資格情報 |
| 電話番号 | 電話番号 |
| タイトル | タイトル |
| UnicodePwd | unicodePwd（ユニコードパスワード） |
| ユーザーアカウント制御 | ユーザーアカウント制御 |
| ユーザープリンシパルネーム | userPrincipalName |
| EscrowType | msDS-escrowType |
| EscrowOperation | msDS-escrowOperation |
| SourceAadObjectId | msDS-aadObjectId |
| TargetAadObjectId | msDS-targetAadObjectId |
| AadGraphLink | msDS-aadGraphLink |
| AadGraphDQLink | msDS-aadlink |
| EscrowCount | msDS-escrowCount |
| FirstSteadyStateTime | msDS-初期定常状態時間 |
| 最後の安定状態時刻 | msDS-lastSteadyStateTime |
| 隔離開始時刻 | msDS-quarantineStartTime |
| QuarantineSyncWaitPeriod | msDS-quarantineSyncWaitPeriod |
| SingleSyncRequests | msDS-singleSyncRequests |
| 文字列値 | msDS-stringValues |
| SyncRequestStatus | msDS-syncRequestStatus |
| SyncStatus | msDS-syncStatus |
| WhenChanged | whenChanged |
| 削除されたオブジェクト番号 | msDS-deletedObjectNumber |
| CustomAttributeState | msDS-customAttribute-state |
| カスタム属性タイプ (CustomAttributeType) | msDS-customAttribute-type |
| LegacyAadObjectId | msDS-AzureADObjectId |
| 名前 | 名前 |
| Revision | revision |
| AdminDisplayName | adminDisplayName |
| AdminDescription | 管理者説明 |
| LdapDisplayName | lDAPDisplayName |
| AttributeId | attributeId |
| 属性構文 | attributeSyntax |
| OmSyntax | omSyntax |
| IsSingleValued | isSingleValued |
| 含まれている可能性があります | 含まれる可能性があります |
| SchemaUpdateNow | schemaUpdateNow |
| IsDefunct | isDefunct |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/concepts-forest-trust"} -->
## Microsoft Entra Domain Services の信頼のしくみ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/concepts-forest-trust
- Service: entra-id / domain-services
- Article date: 2025-06-30
- Summary: Active Directory でのドメインとフォレストの信頼関係のしくみと、それらがフォレスト間認証のために Microsoft Entra Domain Services にどのように適用されるかについて説明します。

Active Directory Domain Services (AD DS) は、ドメインとフォレストの信頼関係を通じて、複数のドメインまたはフォレスト間のセキュリティを提供します。 認証が信頼を超えて行われる前に、Windows はまず、ユーザー、コンピューター、またはサービスによって要求されているドメインが、要求先アカウントのドメインと信頼関係を持っているかどうかを確認する必要があります。

この信頼関係を確認するために、Windows セキュリティ システムは、要求を受信するサーバーのドメイン コントローラー (DC) と要求先アカウントのドメイン内の DC の間の信頼パスを計算します。

AD DS と Windows 分散セキュリティ モデルによって提供されるアクセス制御メカニズムは、ドメインとフォレストの信頼を操作するための環境を提供します。 これらの信頼を適切に機能させるには、すべてのリソースまたはコンピューターに、その場所にあるドメイン内の DC への直接信頼パスが必要です。

Net Logon サービスは、信頼されたドメイン機関への認証済みのリモート プロシージャ コール (RPC) 接続を使用して信頼パスを実装します。 セキュリティで保護されたチャネルは、ドメイン間の信頼関係を通じて他の AD DS ドメインにも拡張されます。 このセキュリティで保護されたチャネルは、ユーザーとグループのセキュリティ識別子 (SID) を含むセキュリティ情報を取得して検証するために使用されます。

手記

Domain Services では、双方向の信頼の最新プレビューや、受信または送信の一方向の信頼など、フォレストの複数の信頼方向をサポートしています。

ドメイン サービスへの信頼の適用方法の概要については、「[フォレストの概念と機能](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-forest-trust)を参照してください。

Domain Services で信頼を利用開始するには、フォレストの信頼 [を使用するマネージド ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance-advanced)を作成します。

### 信頼関係の流れ

信頼に対するセキュリティで保護された通信のフローによって、信頼の弾力性が決まります。 信頼を作成または構成する方法によって、通信がフォレスト内またはフォレスト間でどの程度まで拡張されるかが決まります。

信頼の方向によって、信頼に対する通信のフローが決まります。 信頼は一方向または双方向で、推移的または非推移的にすることができます。

次の図は、*ツリー 1* および *ツリー 2 のすべてのドメイン* 既定で推移的な信頼関係を持っていることを示しています。 その結果、*ツリー 1* のユーザーは、*ツリー 2* のドメイン内のリソースにアクセスでき、*ツリー 2* のユーザーは、リソースで適切なアクセス許可が割り当てられている *ツリー 1*のリソースにアクセスできます。

[Image: 2 つのフォレスト間の信頼関係の図]

#### 一方向と双方向の信頼

リソースへのアクセスを可能にする信頼関係は、一方向でも双方向でもかまいません。

一方向の信頼は、2 つのドメイン間で作成される一方向の認証パスです。 *ドメイン A* とドメイン B 間の一方向の信頼では、*ドメイン A* のユーザーは、*ドメイン B*内のリソースにアクセスできます。ただし、*ドメイン B* のユーザーは、*ドメイン A*内のリソースにアクセスできません。

一部の一方向の信頼は、作成される信頼の種類に応じて、非推移的または推移的のいずれかになります。

双方向の信頼では、*ドメイン A* はドメイン B  信頼し、*ドメイン B* はドメイン A 信頼します。この構成は、2 つのドメイン間で双方向に認証要求を渡すことができることを意味します。 作成される信頼の種類によっては、一部の双方向リレーションシップが非推移的または推移的になる場合があります。

オンプレミスの AD DS フォレスト内のすべてのドメイン信頼は、双方向の推移的な信頼です。 新しい子ドメインが作成されると、新しい子ドメインと親ドメインの間に双方向の推移的な信頼が自動的に作成されます。

#### 推移的信頼と非推移的信頼

推移性は、信頼が形成された 2 つのドメインの外部で拡張できるかどうかを決定します。

- 推移的な信頼を使用して、他のドメインとの信頼関係を拡張できます。
- 非推移的な信頼を使用して、他のドメインとの信頼関係を拒否できます。

フォレストに新しいドメインを作成するたびに、新しいドメインとその親ドメインの間に双方向の推移的な信頼関係が自動的に作成されます。 子ドメインが新しいドメインに追加された場合、信頼パスはドメイン階層を通過し、新しいドメインとその親ドメインの間で作成された初期信頼パスを拡張します。 推移的な信頼関係は、形成されたドメイン ツリーを介して上方向に流れ、ドメイン ツリー内のすべてのドメイン間に推移的な信頼を作成します。

認証要求はこれらの信頼パスに従うので、フォレスト内の任意のドメインのアカウントは、フォレスト内の他のドメインによって認証できます。 シングル サインイン プロセスでは、適切なアクセス許可を持つアカウントは、フォレスト内の任意のドメイン内のリソースにアクセスできます。

### フォレストの信頼

フォレストの信頼は、セグメント化された AD DS インフラストラクチャを管理し、複数のフォレストにまたがるリソースやその他のオブジェクトへのアクセスをサポートするのに役立ちます。 フォレストの信頼は、サービス プロバイダー、合併または買収を受けている企業、共同ビジネス エクストラネット、管理自律性のソリューションを求めている企業に役立ちます。

フォレストの信頼を使用すると、2 つの異なるフォレストをリンクして、一方向または双方向の推移的な信頼関係を形成できます。 フォレストの信頼により、管理者は 2 つの AD DS フォレストを 1 つの信頼関係に接続して、フォレスト全体でシームレスな認証と承認のエクスペリエンスを提供できます。

フォレストの信頼は、あるフォレストのフォレスト ルート ドメインと別のフォレストのフォレスト ルート ドメインの間でのみ作成できます。 フォレストの信頼は 2 つのフォレスト間でのみ作成でき、3 番目のフォレストに暗黙的に拡張することはできません。 この動作は、フォレスト *Forest 1* と *Forest 2*の間にフォレスト信頼が作成され、さらに *Forest 2* と *Forest 3*の間に別のフォレスト信頼が作成された場合でも、*Forest 1* は *Forest 3*との間で暗黙の信頼関係を持たないことを意味します。

次の図は、1 つの組織の 3 つの AD DS フォレスト間の 2 つの個別のフォレストの信頼関係を示しています。

[Image: 1 つの組織内のフォレストの信頼関係の図]

この構成例では、次のアクセスが提供されます。

- *フォレスト 2* のユーザーは、*フォレスト 1* または *フォレスト 3* 内の任意のドメイン内のリソースにアクセスできます。
- *フォレスト 3* のユーザーは、*フォレスト 2* 内の任意のドメイン内のリソースにアクセスできます
- *フォレスト 1* のユーザーは、*フォレスト 2* 内の任意のドメイン内のリソースにアクセスできます。

この構成では、*フォレスト 1* のユーザーは、*フォレスト 3* 内のリソースにアクセスすることはできません。その逆も可能です。 *フォレスト 1* と *フォレスト 3* の両方のユーザーがリソースを共有できるようにするには、2 つのフォレスト間に双方向の推移的信頼を作成する必要があります。

2 つのフォレスト間に一方向のフォレスト信頼が作成された場合、信頼されたフォレストのメンバーは、信頼するフォレスト内にあるリソースにアクセスできます。 ただし、信頼は一方向でのみ動作します。

たとえば、一方向のフォレストの信頼が*フォレスト 1* (信頼される側のフォレスト) と*フォレスト 2* (信頼する側のフォレスト) の間に作成された場合、次のようになります。

- *フォレスト 1* のメンバーは、*フォレスト 2*にあるリソースにアクセスできます。
- *フォレスト 2* のメンバーは、同じ信頼を使用して、*フォレスト 1* にあるリソースにアクセスできません。

重要

Microsoft Entra Domain Services では、フォレスト トラストの多方向をサポートしています。

#### フォレストの信頼の要件

フォレスト トラストを作成する前に、適切なドメイン ネーム システム (DNS) インフラストラクチャが整っていることを確認する必要があります。 フォレストの信頼は、次のいずれかの DNS 構成が使用可能な場合にのみ作成できます。

- 単一のルート DNS サーバーは、両方のフォレスト DNS 名前空間のルート DNS サーバーです。ルート ゾーンには各 DNS 名前空間の委任が含まれており、すべての DNS サーバーのルート ヒントにはルート DNS サーバーが含まれます。
- 共有ルート DNS サーバーがなく、各フォレスト DNS 名前空間のルート DNS サーバーが DNS 名前空間ごとに DNS 条件付きフォワーダーを使用して、他の名前空間の名前に対するクエリをルーティングする場合。

    重要

    信頼を持つ Microsoft Entra Domain Services フォレストでは、この DNS 構成を使用する必要があります。 フォレスト DNS 名前空間以外の DNS 名前空間をホストすることは、Microsoft Entra Domain Services の機能ではありません。 条件付きフォワーダーが適切な構成です。
- 共有ルート DNS サーバーがなく、各フォレスト DNS 名前空間内のルート DNS サーバーは、各 DNS 名前空間で構成された DNS セカンダリ ゾーンを使用して、他の名前空間の名前に対するクエリをルーティングします。

AD DS でフォレストの信頼を作成するには、Domain Admins グループ (フォレスト ルート ドメイン内) または Active Directory の Enterprise Admins グループのメンバーである必要があります。 各信頼には、両方のフォレストの管理者が認識する必要があるパスワードが割り当てられます。 両方のフォレストの Enterprise Admins のメンバーは、両方のフォレストで信頼を一度に作成できます。このシナリオでは、暗号化によってランダムなパスワードが自動的に生成され、両方のフォレストに書き込まれます。

管理されているドメイン フォレストは、オンプレミス フォレストへの最大 5 つの一方向の外向きフォレスト トラストをサポートします。 Microsoft Entra Domain Services の送信フォレストの信頼は、Microsoft Entra 管理センターで作成されます。 受信フォレストの信頼は、オンプレミスの Active Directory で前述の特権を持つユーザーが構成する必要があります。

### 信頼プロセスと相互作用

ドメイン間トランザクションとフォレスト間トランザクションの多くは、さまざまなタスクを完了するためにドメインまたはフォレストの信頼に依存します。 このセクションでは、リソースが信頼を超えてアクセスされ、認証の紹介が評価される場合に発生するプロセスと相互作用について説明します。

#### 認証紹介処理の概要

認証要求がドメインに参照される場合、そのドメイン内のドメイン コントローラーは、要求が発行されるドメインとのトラスト関係が存在するかどうかを判断する必要があります。 信頼の方向と、信頼が推移的か非推移的かも、ドメイン内のリソースにアクセスするためにユーザーを認証する前に決定する必要があります。 信頼されたドメイン間で発生する認証プロセスは、使用中の認証プロトコルによって異なります。 Kerberos V5 プロトコルと NTLM プロトコルは、ドメインへの認証の紹介を異なる方法で処理します。

#### Kerberos V5 の紹介処理

Kerberos V5 認証プロトコルは、クライアント認証と承認情報のドメイン コントローラー上の Net Logon サービスに依存します。 Kerberos プロトコルは、セッション チケット用のオンライン キー配布センター (KDC) と Active Directory アカウント ストアに接続します。

Kerberos プロトコルでは、クロス領域チケット付与サービス (TGS) に対する信頼を使用し、セキュリティで保護されたチャネル全体で特権属性証明書 (PAC) を検証します。 Kerberos プロトコルは、MIT Kerberos 領域などの Windows ブランド以外のオペレーティング システム Kerberos 領域でのみクロス領域認証を実行し、Net Logon サービスと対話する必要はありません。

クライアントが認証に Kerberos V5 を使用する場合は、アカウント ドメインのドメイン コントローラーからターゲット ドメイン内のサーバーにチケットを要求します。 Kerberos KDC は、クライアントとサーバーの間の信頼された仲介者として機能し、2 人が相互に認証できるようにするセッション キーを提供します。 ターゲット ドメインが現在のドメインと異なる場合、KDC は論理プロセスに従って、認証要求を参照できるかどうかを判断します。

1. 現在のドメインは、要求されているサーバーのドメインによって直接信頼されていますか?

    - "はい" の場合は、要求されたドメインへの紹介状をクライアントに送信します。
    - いいえの場合は、次の手順に進みます。
2. 現在のドメインと信頼パス上の次のドメインの間に推移的な信頼関係がありますか?

    - "はい" の場合は、クライアントに信頼パス上の次のドメインに紹介を送信します。
    - いいえの場合は、クライアントにサインイン拒否メッセージを送信します。

#### NTLM 紹介処理

NTLM 認証プロトコルは、クライアント認証と承認情報のドメイン コントローラー上の Net Logon サービスに依存します。 このプロトコルは、Kerberos 認証を使用しないクライアントを認証します。 NTLM では、信頼を使用してドメイン間で認証要求を渡します。

クライアントが認証に NTLM を使用する場合、認証の最初の要求はクライアントからターゲット ドメイン内のリソース サーバーに直接送信されます。 このサーバーは、クライアントが応答するチャレンジを作成します。 その後、サーバーはユーザーの応答をコンピューター アカウント ドメインのドメイン コントローラーに送信します。 このドメイン コントローラーは、セキュリティ アカウント データベースに対してユーザー アカウントをチェックします。

アカウントがデータベースに存在しない場合、ドメイン コントローラーは、次のロジックを使用して、パススルー認証を実行するか、要求を転送するか、要求を拒否するかを決定します。

1. 現在のドメインは、ユーザーのドメインと直接の信頼関係を持っていますか?

    - "はい" の場合、ドメイン コントローラーは、パススルー認証のために、クライアントの資格情報をユーザーのドメイン内のドメイン コントローラーに送信します。
    - いいえの場合は、次の手順に進みます。
2. 現在のドメインは、ユーザーのドメインと推移的な信頼関係を持っていますか?

    - "はい" の場合は、信頼パス内の次のドメインに認証要求を渡します。 このドメイン コントローラーは、ユーザーの資格情報を独自のセキュリティ アカウント データベースに対して確認することで、プロセスを繰り返します。
    - いいえの場合は、ログオン拒否メッセージをクライアントに送信します。

#### フォレストの信頼を介した認証要求の Kerberos ベースの処理

フォレスト信頼によって 2 つのフォレストが接続されている場合、Kerberos V5 または NTLM プロトコルを使用して行われた認証要求をフォレスト間でルーティングして、両方のフォレスト内のリソースへのアクセスを提供できます。

フォレストの信頼が最初に確立されると、各フォレストはパートナー フォレスト内のすべての信頼された名前空間を収集し、その情報を 信頼されたドメイン オブジェクトに格納します。 信頼された名前空間には、ドメイン ツリー名、ユーザー プリンシパル名 (UPN) サフィックス、サービス プリンシパル名 (SPN) サフィックス、および他のフォレストで使用されるセキュリティ ID (SID) 名前空間が含まれます。 TDO オブジェクトはグローバル カタログにレプリケートされます。

手記

信頼の代替 UPN サフィックスはサポートされていません。オンプレミス ドメインで Domain Services と同じ UPN サフィックスが使用されている場合、サインインには **sAMAccountName** を使用する必要があります。

認証プロトコルがフォレストの信頼パスに従う前に、リソース コンピューターのサービス プリンシパル名 (SPN) を他のフォレスト内の場所に解決する必要があります。 SPN には、次のいずれかの名前を指定できます。

- ホストの DNS 名。
- ドメインの DNS 名。
- サービス接続ポイント オブジェクトの識別名。

あるフォレスト内のワークステーションが別のフォレスト内のリソース コンピューター上のデータにアクセスしようとすると、Kerberos 認証プロセスは、リソース コンピューターの SPN へのサービス チケットのドメイン コントローラーに接続します。 ドメイン コントローラーがグローバル カタログに対してクエリを実行し、SPN がドメイン コントローラーと同じフォレスト内にないことを確認すると、ドメイン コントローラーは親ドメインの紹介をワークステーションに送り返します。 その時点で、ワークステーションは親ドメインにサービス チケットのクエリを実行し、リソースが配置されているドメインに到達するまで紹介チェーンのフォローを続けます。

次の図と手順では、Windows を実行しているコンピューターが別のフォレストにあるコンピューターからリソースにアクセスしようとしたときに使用される Kerberos 認証プロセスの詳細な説明を示します。

[Image: フォレストの信頼を介した Kerberos プロセスの図]

1. *User1* は、*europe.tailspintoys.com* ドメインの資格情報を使用して、*Workstation1* にサインインします。 その後、ユーザーは、*usa.wingtiptoys.com* フォレストにある *FileServer1* 上の共有リソースにアクセスしようとします。
2. *Workstation1* は、ドメイン内のドメイン コントローラーである *ChildDC1*上の Kerberos KDC に接続し、*FileServer1* SPN に対するサービス チケットを要求します。
3. *ChildDC1* では、ドメイン データベースに SPN が見つからないので、グローバル カタログに対してクエリを実行して、*tailspintoys.com* フォレスト内のドメインにこの SPN が含まれているかどうかを確認します。 グローバル カタログは独自のフォレストに制限されているため、SPN は見つかりません。

    その後、グローバルカタログは、そのフォレストの中で確立されているフォレストトラストに関する情報をデータベースで確認します。 見つかった場合は、フォレスト信頼信頼ドメイン オブジェクト (TDO) にリストされている名前サフィックスとターゲット SPN のサフィックスを比較して一致を見つけます。 一致が見つかると、グローバル カタログは、*ChildDC1*にルーティング ヒントを返します。

    ルーティング ヒントは、認証要求を宛先フォレストに直接送信するのに役立ちます。 ヒントは、ローカル ドメイン コントローラーやグローバル カタログなど、従来のすべての認証チャネルが SPN を見つけられなかった場合にのみ使用されます。
4. *ChildDC1* は、親ドメインの紹介を *Workstation1*に送り返します。
5. *Workstation1* は、*wingtiptoys.com* フォレストのフォレスト ルート ドメインにあるドメイン コントローラー (*ForestRootDC2*) への参照について、*ForestRootDC1* (Workstation1 の親ドメイン) のドメイン コントローラーに問い合わせます。
6. *Workstation1* は、要求されたサービスに対するサービス チケットについて、*wingtiptoys.com* フォレストの *ForestRootDC2* に問い合わせます。
7. *ForestRootDC2*、グローバル カタログに接続して SPN を検索し、グローバル カタログが SPN の一致を見つけて、*ForestRootDC2*に送り返します。
8. つづいて、*ForestRootDC2* が、*usa.wingtiptoys.com* への参照を *Workstation1* に送り返します。
9. *Workstation1* は *ChildDC2* の KDC に問い合わせ、*User1* のチケットをネゴシエートして *FileServer1* にアクセスできるようにします。
10. *Workstation1* がサービス チケットを取得すると、サービス チケットが *FileServer1*に送信されます。これにより、User1 *のセキュリティ資格情報*読み取られ、それに応じてアクセス トークンが構築されます。

### 信頼されたドメイン オブジェクト

ドメイン内の *System* コンテナーに格納されている信頼されたドメイン オブジェクト (TDO) は、組織内の各ドメインまたはフォレストの信頼を表します。

#### TDO の内容

TDO に含まれる情報は、TDO がドメイン信頼によって作成されたか、フォレスト信頼によって作成されたかによって異なります。

ドメイン信頼が作成されると、DNS ドメイン名、ドメイン SID、信頼の種類、信頼推移性、逆数ドメイン名などの属性が TDO で表されます。 フォレスト信頼 TDO には、パートナー フォレストからのすべての信頼された名前空間を識別するための追加の属性が格納されます。 これらの属性には、ドメイン ツリー名、ユーザー プリンシパル名 (UPN) サフィックス、サービス プリンシパル名 (SPN) サフィックス、およびセキュリティ ID (SID) 名前空間が含まれます。

信頼は TTO として Active Directory に格納されるため、フォレスト内のすべてのドメインには、フォレスト全体に存在する信頼関係に関する知識があります。 同様に、フォレストの信頼を通じて複数のフォレストが結合されている場合、各フォレストのフォレスト ルート ドメインには、信頼されたフォレスト内のすべてのドメイン全体に存在する信頼関係に関する知識があります。

#### TDO パスワードの変更

信頼関係の両方のドメインは、Active Directory の TDO オブジェクトに格納されるパスワードを共有します。 アカウントのメンテナンス プロセスの一環として、信頼するドメイン コントローラーによって TDO に格納されているパスワードが 30 日ごとに変更されます。 双方向の信頼はすべて、実際には反対方向に向かう 2 つの一方向の信頼であるため、このプロセスは双方向の信頼に対して 2 回発生します。

信頼には、信頼する側と信頼される側があります。 信頼できる側では、書き込み可能なドメイン コントローラーをプロセスに使用できます。 信頼側では、PDC エミュレーターがパスワードの変更を実行します。

パスワードを変更するために、ドメイン コントローラーは次のプロセスを完了します。

1. 信頼するドメインのプライマリ ドメイン コントローラー (PDC) エミュレーターは、新しいパスワードを作成します。 信頼されたドメイン内のドメイン コントローラーは、パスワードの変更を開始しません。 これは常に、信頼するドメイン PDC エミュレーターによって開始されます。
2. 信頼ドメインの PDC エミュレーターは、TDO オブジェクトの *OldPassword* フィールドを現在の *NewPassword* フィールドに設定します。
3. 信頼するドメインの PDC エミュレーターは、TDO オブジェクトの *NewPassword* フィールドを新しいパスワードに設定します。 以前のパスワードのコピーを保持すると、信頼されたドメイン内のドメイン コントローラーが変更を受信できなかった場合、または新しい信頼パスワードを使用する要求が行われる前に変更がレプリケートされない場合に、古いパスワードに戻す可能性があります。
4. 信頼するドメインの PDC エミュレーターは、信頼されたドメイン内のドメイン コントローラーにリモート呼び出しを行い、信頼アカウントのパスワードを新しいパスワードに設定するように要求します。
5. 信頼されたドメインのドメイン コントローラーは、信頼パスワードを新しいパスワードに変更します。
6. 信頼の両側で、更新プログラムはドメイン内の他のドメイン コントローラーにレプリケートされます。 信頼するドメインでは、変更によって、信頼されたドメイン オブジェクトの緊急のレプリケーションがトリガーされます。

両方のドメイン コントローラーでパスワードが変更されました。 通常のレプリケーションでは、TDO オブジェクトがドメイン内の他のドメイン コントローラーに分散されます。 ただし、信頼するドメイン内のドメイン コントローラーが、信頼されたドメイン内のドメイン コントローラーを正常に更新せずにパスワードを変更する可能性があります。 このシナリオは、パスワードの変更を処理するために必要なセキュリティで保護されたチャネルを確立できなかったために発生する可能性があります。 また、プロセス中のある時点で、信頼されたドメイン内のドメイン コントローラーが使用できず、更新されたパスワードを受け取らない可能性もあります。

パスワードの変更が正常に通信されない状況に対処するために、信頼するドメインのドメイン コントローラーは、新しいパスワードを使用して正常に認証 (セキュリティで保護されたチャネルを設定) しない限り、新しいパスワードを変更しません。 この動作により、古いパスワードと新しいパスワードの両方が信頼ドメインの TDO オブジェクトに保持されます。

パスワードを使用した認証が成功するまで、パスワードの変更は完了しません。 古い保存されたパスワードは、信頼されたドメイン内のドメイン コントローラーが新しいパスワードを受け取るまで、セキュリティで保護されたチャネルで使用できるため、サービスが中断されません。

パスワードが無効であるために新しいパスワードを使用した認証が失敗した場合、信頼するドメイン コントローラーは古いパスワードを使用して認証を試みます。 古いパスワードで正常に認証されると、15 分以内にパスワード変更プロセスが再開されます。

信頼パスワードの更新は、信頼の両側のドメイン コントローラーに 30 日以内にレプリケートする必要があります。 信頼パスワードが 30 日後に変更され、ドメイン コントローラーに N-2 パスワードしかない場合は、信頼側からの信頼を使用できないため、信頼された側にセキュリティで保護されたチャネルを作成できません。

### 信頼によって使用されるネットワーク ポート

信頼はさまざまなネットワーク境界に展開する必要があるため、1 つ以上のファイアウォールにまたがる必要がある場合があります。 この場合は、ファイアウォール経由で信頼トラフィックをトンネリングするか、ファイアウォール内の特定のポートを開いてトラフィックの通過を許可することができます。

重要

Active Directory Domain Services では、Active Directory RPC トラフィックを特定のポートに制限することはできません。

信頼を使用するために開く必要があるポートの詳細については、「 [AD DS 信頼のファイアウォール設定を構成する](https://learn.microsoft.com/ja-jp/troubleshoot/windows-server/active-directory/config-firewall-for-ad-domains-and-trusts)」を参照してください。 ドメイン サービスとの信頼を持つドメイン内のすべてのドメイン コントローラーで、これらのポートを開く必要があります。

### サポート サービスとツール

信頼と認証をサポートするために、いくつかの追加機能と管理ツールが使用されます。

#### ネットログオン

Net Logon サービスは、Windows ベースのコンピューターから DC へのセキュリティで保護されたチャネルを維持します。 また、次の信頼関連プロセスでも使用されます。

- 信頼のセットアップと管理 - Net Logon は、LSA プロセスと TDO とやり取りすることで、信頼パスワードの維持、信頼情報の収集、信頼の検証に役立ちます。

    フォレストの信頼の場合、信頼情報にはフォレスト信頼情報 (*FTInfo*) レコードが含まれます。このレコードには、信頼されたフォレストが管理するように要求する名前空間のセットが含まれ、各要求が信頼するフォレストによって信頼されているかどうかを示すフィールドで注釈が付けられます。
- 認証 – セキュリティで保護されたチャネル経由でユーザー資格情報をドメイン コントローラーに提供し、ユーザーのドメイン SID とユーザー権限を返します。
- ドメイン コントローラーの場所 – ドメイン内またはドメイン間でドメイン コントローラーを検索または検索するのに役立ちます。
- パススルー検証 – 他のドメインのユーザーの資格情報は、Net Logon によって処理されます。 信頼するドメインがユーザーの ID を確認する必要がある場合、ユーザーの資格情報は Net Logon 経由で検証のために信頼されたドメインに渡されます。
- 特権属性証明書 (PAC) 検証 – 認証に Kerberos プロトコルを使用するサーバーがサービス チケット内の PAC を検証する必要がある場合、セキュリティで保護されたチャネル全体で PAC をドメイン コントローラーに送信して検証します。

#### ローカル セキュリティ機関

ローカル セキュリティ機関 (LSA) は、システム上のローカル セキュリティのすべての側面に関する情報を保持する保護されたサブシステムです。 まとめてローカル セキュリティ ポリシーと呼ばれる LSA は、名前と識別子間の変換のためのさまざまなサービスを提供します。

LSA セキュリティ サブシステムは、オブジェクトへのアクセスの検証、ユーザー特権の確認、監査メッセージの生成を行うために、カーネル モードとユーザー モードの両方でサービスを提供します。 LSA は、信頼されたドメインまたは信頼されていないドメイン内のサービスによって提示されるすべてのセッション チケットの有効性を確認する役割を担います。

#### 管理ツール

管理者は、*Active Directory ドメインと信頼*、*Netdom*、および *Nltest* を使用して、信頼を公開、作成、削除、または変更できます。

- *Active Directory ドメインと信頼関係* は、ドメイン信頼、ドメインおよびフォレストの機能レベル、ユーザープリンシパル名のサフィックスを管理するために使用される Microsoft 管理コンソール (MMC) です。
- *Netdom* と *Nltest* コマンド ライン ツールを使用して、信頼を検索、表示、作成、および管理できます。 これらのツールは、ドメイン コントローラー上の LSA 機関と直接通信します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/concepts-replica-sets"} -->
## Microsoft Entra Domain Services のレプリカ セットの概念 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/concepts-replica-sets
- Service: entra-id / domain-services
- Article date: 2025-01-21
- Summary: Microsoft Entra Domain Services のレプリカ セットと、これが ID サービスを必要とするアプリケーションにどのようにして冗長性を提供するかについて説明します。

Microsoft Entra Domain Services のマネージド ドメインを作成するときは、一意の名前空間を定義します。 この名前空間はドメイン名 (*contosocloud.com* など) であり、2 つのドメイン コントローラー (DC) はその後、選択した Azure リージョンにデプロイされます。 この DC のデプロイは、レプリカ セットと呼ばれます。

マネージド ドメインを拡張して、Microsoft Entra テナントごとに複数のレプリカ セットを使用できます。 レプリカ セットは、Domain Services がサポートされている任意の Azure リージョンの、ピアリングされた任意の仮想ネットワークに追加できます。 異なる Azure リージョンにレプリカ セットを追加することで、1 つの Azure リージョンがオフラインになった場合に、レガシ アプリケーションの地理的なディザスター リカバリーが実現されます。

注

レプリカ セットでは、1 つの Azure テナント内に複数の一意のマネージド ドメインをデプロイすることはできません。 各レプリカ セットには、同じデータが含まれます。

### レプリカ セットのしくみ

*contosocloud.com* などのマネージド ドメインを作成すると、初期レプリカ セットが作成されます。 追加のレプリカ セットは同じ名前空間と構成を共有します。 構成、ユーザー ID と資格情報、グループ、グループ ポリシー オブジェクト、コンピューター オブジェクト、およびその他の変更を含む Domain Services への変更は、AD DS レプリケーションを使用して、マネージド ドメイン内のすべてのレプリカ セットに適用されます。

仮想ネットワーク内に各レプリカ セットを作成します。 各仮想ネットワークは、マネージド ドメインのレプリカ セットをホストする他のすべての仮想ネットワークにピアリングされている必要があります。 この構成によって、ディレクトリ レプリケーションをサポートするメッシュ ネットワーク トポロジが作成されます。 各レプリカ セットが異なる仮想サブネットにある場合、仮想ネットワークでは複数のレプリカ セットをサポートできます。

すべてのレプリカ セットは同じ Active Directory サイトに配置されます。 その結果、すべての変更は、迅速な収束のためにサイト内レプリケーションを使用して伝搬されます。

Important

Microsoft Entra Domain Services のレプリカ セットでは、レプリカ セットをホストしているすべての仮想ネットワーク間でのネットワーク接続が必要です。 レプリカ セットは 1 つのActive Directory サイト内にデプロイされ、ディレクトリ レプリケーションをサポートするために完全にメッシュ化された仮想ネットワーク トポロジに依存します。 レプリカ セット仮想ネットワーク間の直接接続を提供しないネットワーク アーキテクチャでは、Microsoft Entra Domain Servicesに依存するアプリケーションとサービスの認証、ドメイン コントローラー通信、Netlogon 関連の問題が発生する可能性があります。 複数のリージョンにワークロードをデプロイする前に、レプリカセットのネットワーク要件を確認します。

注

個別のサイトを定義し、レプリカ セット間のレプリケーション設定を定義することはできません。

次の図は、2 つのレプリカ セットを持つマネージド ドメインを示しています。 最初のレプリカ セットは、ドメイン名前空間を使用して作成されます。 その後、2 番目のレプリカ セットが作成されます。

[Image: 2 つのレプリカ セットを持つマネージド ドメインの例の図]

注

レプリカ セットが構成されているリージョンでは、レプリカ セットによって認証サービスの可用性が保証されます。 リージョンの障害が発生した場合にアプリケーションで地理的な冗長性を確保するには、マネージド ドメインに依存しているアプリケーション プラットフォームも、もう一方のリージョンに存在する必要があります。

アプリケーションが機能するために必要な他のサービス (Azure VM や Azure アプリ Services など) の回復性は、レプリカ セットによって提供されません。 他のアプリケーション コンポーネントの可用性設計では、アプリケーションを構成するサービスの回復性機能を考慮する必要があります。

次の例では、3 つのレプリカ セットを持つマネージド ドメインを示します。これにより、回復性が向上し、認証サービスの可用性が保証されます。 どちらの例でも、アプリケーション ワークロードは、マネージド ドメインのレプリカ セットと同じリージョンに存在します。

[Image: 3 つのレプリカ セットを持つマネージド ドメインの例の図]

### デプロイに関する考慮事項

マネージド ドメインの既定の SKU は *Enterprise* SKU で、複数のレプリカ セットをサポートします。 *Standard* SKU に変更した場合に追加のレプリカ セットを作成するには、マネージド ドメインを [Enterprise](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/change-sku) または *Premium* に*アップグレード*します。

サポートされるレプリカ セットの最大数は、マネージド ドメインを作成したときに作成された最初のレプリカを含めて 5 つです。

各レプリカ セットに対する課金は、ドメイン構成 SKU に基づきます。 たとえば、*Enterprise* SKU を使用し、3 つのレプリカ セットがあるマネージド ドメインがある場合、3 つのレプリカ セットごとに、1 時間単位でサブスクリプションに請求されます。

### よく寄せられる質問

#### マネージド ドメインとは異なるサブスクリプションにレプリカ セットを作成できますか?

いいえ。 レプリカ セットは、マネージド ドメインと同じサブスクリプションに含まれている必要があります。

#### レプリカ セットはいくつ作成できますか?

最大 5 つのレプリカ セットを作成できます。つまり、マネージド ドメインの初期レプリカ セットに加えて、4 つのレプリカ セットを追加できます。

#### ユーザーとグループの情報はレプリカ セットにどのように同期されますか?

すべてのレプリカ セットは、メッシュ仮想ネットワーク ピアリングを使用して相互に接続されます。 1 つのレプリカ セットが、Microsoft Entra ID からユーザーとグループの更新を受信します。 その後、これらの変更は、ピアリングされたネットワーク上のサイト内 AD DS レプリケーションを使用して、他のレプリカ セットにレプリケートされます。

オンプレミスの AD DS と同様に、オフラインの状態が長引くと、レプリケーションが中断される可能性があります。 ピアリングされた仮想ネットワークは推移的ではないため、レプリカ セットの設計要件には、完全にメッシュ化されたネットワーク トポロジが必要です。

#### レプリカ セットを作成した後にマネージド ドメインに変更を加えるにはどうしたらよいですか?

マネージド ドメイン内の変更は、以前と同じように機能します。 [マネージド ドメインに参加している RSAT ツールを使用して、管理 VM を作成して使用](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-management-vm)します。 マネージド ドメインには、必要な数の管理 VM を参加させることができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/create-forest-trust-powershell"} -->
## Azure PowerShell を使用して Microsoft Entra Domain Services フォレスト トラストを作成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/create-forest-trust-powershell
- Service: entra-id / domain-services
- Article date: 2025-06-30
- Summary: この記事では、Azure PowerShell を使用して、オンプレミスの Active Directory Domain Services 環境に対する Microsoft Entra Domain Services フォレストの信頼を作成して構成する方法について説明します。

組織は、ハイブリッド環境で ID を管理する場合や、合併または買収を計画する場合に、ユーザーのコラボレーションを向上させるための信頼を作成することがよくあります。 Microsoft Entra Domain Services では、マネージド ドメインから別のドメインへの一方向の送信信頼が常にサポートされています。 現在プレビュー段階では、一方向の受信信頼または双方向の信頼を作成することもできます。

たとえば、パスワード ハッシュを同期できない環境や、スマート カードを使用して排他的にサインインしてパスワードを知らないユーザーがいる環境では、Microsoft Entra Domain Services から 1 つ以上のオンプレミス AD DS 環境への一方向の送信信頼を作成できます。 この信頼関係により、ユーザー、アプリケーション、およびコンピューターは、Domain Services マネージド ドメインからオンプレミス ドメインに対して認証を行うことができます。 この場合、オンプレミスのパスワード ハッシュは同期されません。

[Image: Domain Services からオンプレミスの AD DS へのフォレストの信頼の図]

この記事では、次の方法について説明します。

- Azure PowerShell を使用して Domain Services フォレストを作成する
- Azure PowerShell を使用してマネージド ドメインで一方向の外部フォレスト トラストを作成する
- マネージド ドメイン接続をサポートするようにオンプレミスの AD DS 環境で DNS を構成する
- オンプレミスの AD DS 環境で一方向の受信フォレストの信頼を作成する
- 認証とリソース アクセスの信頼関係をテストして検証する

Azure サブスクリプションをお持ちでない場合は、開始する前にアカウント [を作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。

重要

マネージド ドメイン フォレストは現在、Azure HDInsight または Azure Files をサポートしていません。 既定のマネージド ドメイン フォレストでは、これらの追加サービスの両方がサポートされます。

### 前提 条件

この記事を完了するには、次のリソースと特権が必要です。

- アクティブな Azure サブスクリプション。

    - Azure サブスクリプションをお持ちでない場合は、アカウント [を作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- サブスクリプションに関連付けられている Microsoft Entra テナント。オンプレミスディレクトリまたはクラウド専用ディレクトリと同期されます。

    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付けます](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)。
- Azure PowerShell をインストールして構成します。

    - 必要に応じて、指示に従って Azure PowerShell モジュール [インストールし、Azure サブスクリプション](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に接続します。
    - [Connect-AzAccount](https://learn.microsoft.com/ja-jp/powershell/module/az.accounts/connect-azaccount) コマンドレットを使用して、Azure サブスクリプションにサインインしていることを確認します。
- MS Graph PowerShell をインストールして構成します。

    - 必要に応じて、指示に従って MS Graph PowerShell モジュール [インストールし、Microsoft Entra ID](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands)に接続します。
    - [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands) コマンドレットを使用して、Microsoft Entra テナントにサインインしていることを確認します。
- ドメイン サービスを有効にするには、テナントの Microsoft Entra ロールとして [アプリケーション管理者](https://learn.microsoft.com/ja-jp/azure/active-directory/roles/permissions-reference#application-administrator) と [グループ管理者](https://learn.microsoft.com/ja-jp/azure/active-directory/roles/permissions-reference#groups-administrator) が必要です。
- 必須の Domain Services リソースを作成するには、[ドメインサービス共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#contributor) Azure ロールが必要です。

### Microsoft Entra 管理センターにサインインする

この記事では、Microsoft Entra 管理センターを使用して、マネージド ドメインを通じてアウトバウンド フォレスト トラストを作成および構成します。 まず、Microsoft Entra 管理センター にサインインします。

### デプロイ プロセス

これは、マネージド ドメイン フォレストとオンプレミスの AD DS との信頼関係を作成するためのマルチパート プロセスです。 次の大まかな手順では、信頼できるハイブリッド環境を構築します。

1. マネージド ドメイン サービス プリンシパルを作成します。
2. マネージド ドメイン フォレストを作成します。
3. サイト間 VPN または Express Route を使用してハイブリッド ネットワーク接続を作成します。
4. 信頼関係のマネージド ドメイン側を作成します。
5. オンプレミス環境の AD DS 側の信頼関係を作成します。

開始する前に、[ネットワークに関する考慮事項、フォレストの名前付け、DNS の要件](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-forest-trust#networking-considerations)理解していることを確認してください。 デプロイ後にマネージド ドメイン フォレスト名を変更することはできません。

### Microsoft Entra サービス プリンシパルを作成する

Domain Services には、Microsoft Entra ID からのデータを同期するサービス プリンシパルが必要です。 マネージド ドメイン フォレストを作成する前に、このプリンシパルを Microsoft Entra テナントに作成する必要があります。

Domain Services が通信でき、かつ自身を認証できるように Microsoft Entra サービス プリンシパルを作成します。 特定のアプリケーション ID は、名前が *ドメイン コントローラー サービス* で、ID が *6ba9a5d4-8456-4118-b521-9c5ca10cdf84*です。 このアプリケーション ID は変更しないでください。

[New-MgServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/new-mgserviceprincipal) コマンドレットを使用して、Microsoft Entra サービス プリンシパルを作成します。

```powershell
New-MgServicePrincipal
```

### マネージド ドメインを作成する

マネージド ドメインを作成するには、`New-AaddsResourceForest` スクリプトを使用します。 このスクリプトは、マネージド ドメインをサポートする幅広いコマンド セットの一部です。 これらは、[PowerShell ギャラリー](https://www.powershellgallery.com/)から入手できます。 Microsoft Entra エンジニアリング チームによってデジタル署名されています。

1. まず、[New-AzResourceGroup](https://learn.microsoft.com/ja-jp/powershell/module/az.resources/new-azresourcegroup) コマンドレットを使用してリソース グループを作成します。 次の例では、リソース グループは myResourceGroup  名前が付けられ、*westus* リージョンに作成されます。 独自の名前と目的のリージョンを使用します。

    ```azurepowershell
    New-AzResourceGroup `
      -Name "myResourceGroup" `
      -Location "WestUS"
    ```
2. `New-AaddsResourceForest` コマンドレットを使用して、[PowerShell ギャラリー](https://www.powershellgallery.com/) から  スクリプトをインストールします。

    ```powershell
    Install-Script -Name New-AaddsResourceForest
    ```
3. `New-AaddsResourceForest` スクリプトに必要な次のパラメーターを確認します。 前提条件となっている **Azure PowerShell** モジュールと **Microsoft Graph PowerShell** モジュールがあることも確認します。 アプリケーションとオンプレミスの接続を提供するための仮想ネットワーク要件が計画されていることを確認します。

    | 名前 | スクリプト パラメーター | 説明 |
    | --- | --- | --- |
    | 予約 | *-azureSubscriptionId* | Domain Services の課金に使用されるサブスクリプション ID。 [Get-AzureRMSubscription](https://learn.microsoft.com/ja-jp/powershell/module/az.accounts/get-azsubscription) コマンドレットを使用して、サブスクリプションの一覧を取得できます。 |
    | リソース グループ | *-aaddsResourceGroupName* | マネージド ドメインと関連付けられているリソースのリソース グループの名前。 |
    | 場所 | *-aaddsLocation* | マネージド ドメインをホストする Azure リージョン。 使用可能なリージョンについては、Domain Services でサポートされているリージョン [参照してください。](https://azure.microsoft.com/global-infrastructure/services/?products=active-directory-ds&amp;regions=all) |
    | Domain Services 管理者 | *-aaddsAdminUser* | 最初のマネージドドメイン管理者のユーザープリンシパルネーム。 このアカウントは、Microsoft Entra ID の既存のクラウド ユーザー アカウントである必要があります。 ユーザーとスクリプトを実行しているユーザーは、*AAD DC Administrators* グループに追加されます。 |
    | Domain Services のドメイン名 | *-aaddsDomainName* | マネージド ドメインの FQDNは、フォレスト名の選び方についての以前のガイダンスに基づいています。 |

    `New-AaddsResourceForest` スクリプトでは、これらのリソースがまだ存在しない場合は、Azure 仮想ネットワークと Domain Services サブネットを作成できます。 指定された場合、スクリプトはオプションでワークロードサブネットを作成できます。

    | 名前 | スクリプト パラメーター | 説明 |
    | --- | --- | --- |
    | 仮想ネットワーク名 | *-aaddsVnetName* | マネージド ドメインの仮想ネットワークの名前。 |
    | アドレス空間 | *-aaddsVnetCIDRAddressSpace* | CIDR 表記の仮想ネットワークのアドレス範囲 (仮想ネットワークを作成する場合)。 |
    | Domain Services サブネット名 | *-aaddsSubnetName* | マネージド ドメインをホストする仮想ネットワーク *aaddsVnetName* のサブネットの名前。 独自の VM とワークロードをこのサブネットにデプロイしないでください。 |
    | Domain Services のアドレス範囲 | *-aaddsSubnetCIDRAddressRange* | ドメイン サービス インスタンスの CIDR 表記のサブネット アドレス範囲 (*192.168.1.0/24*など)。 アドレス範囲は、仮想ネットワークのアドレス範囲に含まれている必要があり、他のサブネットとは異なります。 |
    | ワークロード サブネット名 (省略可能) | *-workloadSubnetName* | 独自のアプリケーション ワークロード用に作成する仮想ネットワーク *aaddsVnetName* のサブネットの省略可能な名前。 VM とアプリケーションは、代わりにピアリングされた Azure 仮想ネットワークに接続することもできます。 |
    | ワークロードのアドレス範囲 (省略可能) | *-ワークロードサブネットCIDRアドレス範囲* | *192.168.2.0/24*など、アプリケーション ワークロードの CIDR 表記の省略可能なサブネット アドレス範囲。 アドレス範囲は、仮想ネットワークのアドレス範囲に含まれている必要があり、他のサブネットとは異なります。 |
4. 次に、`New-AaddsResourceForest` スクリプトを使用してマネージド ドメイン フォレストを作成します。 次の例では、*addscontoso.com* という名前のフォレストを作成し、ワークロード サブネットを作成します。 独自のパラメーター名と IP アドレス範囲、または既存の仮想ネットワークを指定します。

    ```powershell
    New-AaddsResourceForest `
        -azureSubscriptionId <subscriptionId> `
        -aaddsResourceGroupName "myResourceGroup" `
        -aaddsLocation "WestUS" `
        -aaddsAdminUser "contosoadmin@contoso.com" `
        -aaddsDomainName "aaddscontoso.com" `
        -aaddsVnetName "myVnet" `
        -aaddsVnetCIDRAddressSpace "192.168.0.0/16" `
        -aaddsSubnetName "AzureADDS" `
        -aaddsSubnetCIDRAddressRange "192.168.1.0/24" `
        -workloadSubnetName "myWorkloads" `
        -workloadSubnetCIDRAddressRange "192.168.2.0/24"
    ```

    マネージド ドメイン フォレストとサポート リソースの作成にはかなりの時間がかかります。 スクリプトの完了を許可します。 次のセクションに進み、Microsoft Entra フォレストがバックグラウンドでプロビジョニングされている間に、オンプレミスのネットワーク接続を構成します。

### ネットワーク設定の構成と検証

マネージド ドメインのデプロイが続行されたら、オンプレミスのデータセンターへのハイブリッド ネットワーク接続を構成して検証します。 また、定期的なメンテナンスのためにマネージド ドメインで使用する管理 VM も必要です。 ハイブリッド接続の一部が環境内に既に存在している場合や、チーム内の他のユーザーと連携して接続を構成する必要がある場合があります。

開始する前に、[ネットワークに関する考慮事項と推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-forest-trust#networking-considerations)理解していることを確認してください。

1. Azure VPN または Azure ExpressRoute 接続を使用して、オンプレミス ネットワークから Azure へのハイブリッド接続を作成します。 ハイブリッド ネットワーク構成は、このドキュメントの範囲外であり、環境内に既に存在している可能性があります。 特定のシナリオの詳細については、次の記事を参照してください。

    - [Azure サイト間 VPN](https://learn.microsoft.com/ja-jp/azure/vpn-gateway/vpn-gateway-about-vpngateways)に関する記事。
    - [Azure ExpressRoute の概要](https://learn.microsoft.com/ja-jp/azure/expressroute/expressroute-introduction)

    重要

    マネージド ドメインの仮想ネットワークへの直接接続を作成する場合は、別のゲートウェイ サブネットを使用します。 マネージド ドメインのサブネットにゲートウェイを作成しないでください。
2. マネージド ドメインを管理するには、管理 VM を作成し、それをマネージド ドメインに参加させ、必要な AD DS 管理ツールをインストールします。

    マネージド ドメインの展開中に、Windows Server VM [を作成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-windows-vm)、必要な管理ツールをインストール [、コア AD DS 管理ツールをインストール](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-management-vm)。 ドメインが正常にデプロイされた後、次のいずれかの手順が実行されるまで、管理 VM をマネージド ドメインに参加させるのを待ちます。
3. オンプレミス ネットワークと Azure 仮想ネットワークの間のネットワーク接続を検証します。

    - たとえば、オンプレミスのドメイン コントローラーが、`ping` またはリモート デスクトップを使用してマネージド VM に接続できることを確認します。
    - `ping`などのユーティリティを使用して、管理 VM がオンプレミスのドメイン コントローラーに再度接続できることを確認します。
4. Microsoft Entra 管理センターで、Microsoft Entra Domain Services 検索して選択します。 使用するマネージド ドメイン (*aaddscontoso.com* など) を選択し、状態のレポートが **[Running](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/実行中)** になるまで待ちます。

    実行中に、Azure 仮想ネットワーク [の DNS 設定を更新](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance#update-dns-settings-for-the-azure-virtual-network) し、Domain Services [のユーザー アカウントを有効にして、マネージド ドメインの構成を完了](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance#enable-user-accounts-for-azure-ad-ds)。
5. 概要ページに表示される DNS アドレスをメモしておきます。 これらのアドレスは、次のセクションで信頼関係のオンプレミス Active Directory 側を構成するときに必要です。
6. 新しい DNS 設定を受け取るために管理 VM を再起動し、その後、VM をマネージド ドメイン [に参加させてください](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-windows-vm#join-the-vm-to-the-managed-domain)。
7. 管理 VM がマネージド ドメインに参加したら、リモート デスクトップを使用してもう一度接続します。

    コマンド プロンプトで、`nslookup` とマネージド ドメイン名を使用して、フォレストの名前解決を検証します。

    ```console
    nslookup aaddscontoso.com
    ```

    このコマンドは、フォレストの 2 つの IP アドレスを返す必要があります。

### フォレストの信頼を作成する

フォレストの信頼には、マネージド ドメインでの一方向の送信フォレストの信頼と、オンプレミスの AD DS フォレストでの一方向の受信フォレスト信頼の 2 つの部分があります。 この信頼関係の両側を手動で作成します。 両方の側が作成されると、ユーザーとリソースはフォレストの信頼を使用して正常に認証できます。 マネージド ドメインでは、オンプレミスのフォレストに対する一方向の送信フォレストの信頼が最大 5 つサポートされます。

#### 信頼関係のマネージド ドメイン側を作成する

`Add-AaddsResourceForestTrust` スクリプトを使用して、信頼関係のマネージド ドメイン側を作成します。 まず、`Add-AaddsResourceForestTrust` コマンドレットを使用して、[PowerShell ギャラリー](https://www.powershellgallery.com/) から  スクリプトをインストールします。

```powershell
Install-Script -Name Add-AaddsResourceForestTrust
```

次に、スクリプトに次の情報を指定します。

| 名前 | スクリプト パラメーター | 説明 |
| --- | --- | --- |
| Domain Services のドメイン名 | *-ManagedDomainFqdn* | マネージド ドメインの FQDN (*aaddscontoso.com* など) |
| オンプレミスの AD DS ドメイン名 | *-TrustFqdn* | 信頼済みフォレストの FQDN (*onprem.contoso.com* など) |
| 信頼済みフレンドリーネーム | *-TrustFriendlyName* | 信頼関係のフレンドリ名。 |
| オンプレミスの AD DS DNS IP アドレス | *-TrustDnsIPs* | 一覧表示されている信頼されたドメインの DNS サーバー IPv4 アドレスのコンマ区切りの一覧。 |
| パスワードを信頼する | *-TrustPassword* | 信頼関係の複雑なパスワード。 このパスワードは、オンプレミスの AD DS で一方向の受信信頼を作成するときにも入力されます。 |
| 認証情報 | *-認証情報* | Azure に対する認証に使用される資格情報。 ユーザーは、*AAD DC Administrators グループ*に含まれている必要があります。 指定しない場合、スクリプトは認証を求めます。 |

次の例では、*myAzureADDSTrust* という名前の信頼関係を *onprem.contoso.com*に作成します。 独自のパラメーター名とパスワードを使用します。

```azurepowershell
Add-AaddsResourceForestTrust `
    -ManagedDomainFqdn "aaddscontoso.com" `
    -TrustFqdn "onprem.contoso.com" `
    -TrustFriendlyName "myAzureADDSTrust" `
    -TrustDnsIPs "10.0.1.10,10.0.1.11" `
    -TrustPassword <complexPassword>
```

重要

信頼パスワードを覚えておいてください。 信頼のオンプレミス側を作成するときは、同じパスワードを使用する必要があります。

### オンプレミス ドメインで DNS を構成する

オンプレミス環境からマネージド ドメインを正しく解決するには、フォワーダーを既存の DNS サーバーに追加することが必要な場合があります。 マネージド ドメインと通信するようにオンプレミス環境を構成していない場合は、オンプレミス AD DS ドメインの管理ワークステーションから次の手順を実行します。

1. **[スタート] | [管理ツール] | [DNS]** の順に選択します。
2. *myAD01*などの DNS サーバーを右選択し、[プロパティ] **選択**
3. **[フォワーダー]** 、 **[編集]** の順に選択して、他のフォワーダーを追加します。
4. *10.0.1.4* や *10.0.1.5*など、マネージド ドメインの IP アドレスを追加します。
5. ローカル コマンド プロンプトで、マネージド ドメイン名 **nslookup** を使用して名前解決を検証します。 たとえば、`Nslookup aaddscontoso.com` はマネージド ドメインの 2 つの IP アドレスを返す必要があります。

### オンプレミス ドメインに受信フォレストの信頼を作成する

オンプレミスの AD DS ドメインには、マネージド ドメインに対する受信フォレストの信頼が必要です。 この信頼は、オンプレミスの AD DS ドメインに手動で作成する必要があります。Microsoft Entra 管理センターから作成することはできません。

オンプレミスの AD DS ドメインで受信信頼を構成するには、オンプレミス AD DS ドメインの管理ワークステーションから次の手順を実行します。

1. **[スタート] | [管理ツール] | [Active Directory Active Directory ドメインと信頼関係]** の順に選択します。
2. *onprem.contoso.com*などのドメインを右選択し、[プロパティ]  選択します
3. **[信頼]** タブ、 **[新しい信頼]** の順に選択します。
4. マネージド ドメインの名前 (*aaddscontoso.com*など) を入力し、[次へ] **選択**
5. **フォレストの信頼**を作成するオプションを選択して、**一方向: 受信**の信頼を作成します。
6. **の信頼を作成することを選択します。このドメインは**だけです。 次の手順では、マネージド ドメインの Microsoft Entra 管理センターで信頼を作成します。
7. フォレスト全体 **認証**を使用することを選択し、信頼パスワードを入力して確認します。 この同じパスワードは、次のセクションの Microsoft Entra 管理センターにも入力されます。
8. 既定のオプションを使用して次のいくつかのウィンドウをステップ実行し、オプションの **[確認しない]** を選択します。 マネージド ドメインに委任された管理者アカウントに必要なアクセス許可がないため、信頼関係を検証できません。 この動作は仕様です。
9. **[完了]** を選択します。

### リソース認証を検証する

次の一般的なシナリオでは、フォレストの信頼がユーザーとリソースへのアクセスを正しく認証することを検証できます。

- Domain Services フォレストからのオンプレミスのユーザー認証
- オンプレミス ユーザーとして Domain Services フォレスト内のリソースにアクセスする
    - ファイルとプリンターの共有 を有効にする
    - セキュリティ グループを作成し、メンバーを追加
    - フォレスト間アクセス 用のファイル共有を作成する
    - リソース に対するフォレスト間認証を検証する

#### Domain Services フォレストからのオンプレミス ユーザー認証

Windows Server 仮想マシンがマネージド ドメイン リソース ドメインに参加している必要があります。 この仮想マシンを使用して、オンプレミスユーザーが仮想マシンで認証できることをテストします。

1. リモート デスクトップとマネージド ドメイン管理者の資格情報を使用して、マネージド ドメインに参加している Windows Server VM に接続します。 ネットワーク レベル認証 (NLA) エラーが発生した場合は、使用したユーザー アカウントがドメイン ユーザー アカウントではないことを確認します。

    アドバイス

    Microsoft Entra Domain Services に参加している VM に安全に接続するには、サポートされている Azure リージョンで [Azure Bastion Host Service](https://learn.microsoft.com/ja-jp/azure/bastion/bastion-overview) を使用できます。
2. コマンド プロンプトを開き、`whoami` コマンドを使用して、現在認証されているユーザーの識別名を表示します。

    ```console
    whoami /fqdn
    ```
3. `runas` コマンドを使用して、オンプレミス ドメインのユーザーとして認証します。 次のコマンドでは、`userUpn@trusteddomain.com` を、信頼されたオンプレミス ドメインのユーザーの UPN に置き換えます。 コマンドによって、ユーザーのパスワードの入力が求められます。

    ```console
    Runas /u:userUpn@trusteddomain.com cmd.exe
    ```
4. 認証が成功すると、新しいコマンド プロンプトが開きます。 新しいコマンド プロンプトのタイトルには、`running as userUpn@trusteddomain.com`が含まれています。
5. 新しいコマンド プロンプトで `whoami /fqdn` を使用して、認証されたユーザーの識別名をオンプレミスの Active Directory から表示します。

#### オンプレミス ユーザーとして Domain Services のリソースにアクセスする

マネージド ドメインに参加している Windows Server VM を使用して、ユーザーがオンプレミス ドメインのユーザーを使用してオンプレミス ドメイン内のコンピューターから認証するときに、フォレストでホストされているリソースにアクセスできるシナリオをテストできます。 次の例では、さまざまな一般的なシナリオを作成してテストする方法を示します。

##### ファイルとプリンターの共有を有効にする

1. リモート デスクトップとマネージド ドメイン管理者の資格情報を使用して、マネージド ドメインに参加している Windows Server VM に接続します。 ネットワーク レベル認証 (NLA) エラーが発生した場合は、使用したユーザー アカウントがドメイン ユーザー アカウントではないことを確認します。

    アドバイス

    Microsoft Entra Domain Services に参加している VM に安全に接続するには、サポートされている Azure リージョンで [Azure Bastion Host Service](https://learn.microsoft.com/ja-jp/azure/bastion/bastion-overview) を使用できます。
2. **Windows 設定**を開き、「**ネットワークと共有センター**」を検索して選択します。
3. **[共有の詳細設定の変更]** のオプションを選択します。
4. **ドメイン プロファイル**で、[**ファイルとプリンターの共有を有効にする**] を選択し、[**変更を保存**] をします。
5. **ネットワークと共有センターの**を閉じます。

##### セキュリティ グループを作成してメンバーを追加する

1. **Active Directory ユーザーとコンピューター**を開きます。
2. ドメイン名を右クリックし、[新しい ] を選択し、[組織単位] 選択します。
3. [名前] ボックスに「LocalObjects 」と入力し、[OK] 選択します。
4. ナビゲーション ウィンドウ **LocalObjects** を選択して右クリックします。 **を選択して、新しい** を選び、その後 **グループ**を選択します。
5. 「*FileServerAccess*」を**グループ名** ボックスに入力します。 [**グループ スコープ**] で、[**ドメイン ローカル**] を選択し、[**OK**] を選択します。
6. コンテンツ ウィンドウで **FileServerAccess**をダブルクリックします。 **[メンバー]** 、 **[追加]** 、 **[場所]** の順に選択します。
7. オンプレミスの Active Directory を [**の場所]** ビューから選択し、[OK] を選択します。
8. *[選択するオブジェクト名を入力してください]* ボックスに「**Domain Users**」と入力します。 **[名前の確認]**を選択し、オンプレミスの Active Directory の資格情報を入力して、[OK] を選択します。

    手記

    信頼関係は 1 つの方法に過ぎないため、資格情報を指定する必要があります。 つまり、マネージド ドメインのユーザーは、リソースにアクセスしたり、信頼された (オンプレミス) ドメイン内のユーザーまたはグループを検索したりできません。
9. オンプレミスの Active Directory の **Domain Users** グループは、**FileServerAccess** グループのメンバーである必要があります。 [OK]  選択してグループを保存し、ウィンドウを閉じます。

##### フォレスト間アクセス用のファイル共有を作成する

1. マネージド ドメインに参加している Windows Server VM で、フォルダーを作成し、*CrossForestShare*などの名前を指定します。
2. フォルダーを右クリックし、[プロパティ] 選択します。
3. [**セキュリティ**] タブを選択し、[編集] を選択します。
4. *[CrossForestShare のアクセス許可]* ダイアログ ボックスで **[追加]** を選択します。
5. *[選択するオブジェクト名を入力してください]* に、「**FileServerAccess**」と入力し、 **[OK]** を選択します。
6. *グループまたはユーザー名* の一覧から **FileServerAccess** を選択します。 **[FileServerAccess のアクセス許可]** 一覧で、 *[変更]* アクセス許可と **[書き込み]** アクセス許可に対して **[許可]** を選択し、 **[OK]** を選択します。
7. [**共有**] タブを選択し、[**高度な共有…**] を選択します。
8. [フォルダー **共有**] を選択し、[ファイル共有の覚えやすい名前を **共有名** に入力します。例: *CrossForestShare*]。
9. **[アクセス許可]** を選択します。 **[Permissions for Everyone](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/全員のアクセス許可)** 一覧で、 **[変更]** アクセス許可に対して **[許可]** を選択します。
10. **[OK]** を 2 回選択して、 **[閉じる]** を選択します。

##### リソースに対するフォレスト間認証を検証する

1. オンプレミスの Active Directory のユーザー アカウントを使用して、オンプレミスの Active Directory に参加している Windows コンピューターにサインインします。
2. **Windows Explorer** 使用して、完全修飾ホスト名と共有 (`\\fs1.aaddscontoso.com\CrossforestShare` など) を使用して作成した共有に接続します。
3. 書き込みアクセス許可を検証するには、フォルダーを右クリックして、**[新しい**] を選択し、次に **[テキスト ドキュメント**] を選択します。 既定の名前 **[新しいテキスト ドキュメント]** を使用します。

    書き込みアクセス許可が正しく設定されている場合は、新しいテキスト ドキュメントが作成されます。 次の手順では、必要に応じてファイルを開き、編集し、削除します。
4. 読み取りアクセス許可を検証するには、新しいテキスト ドキュメント 開きます。
5. 変更アクセス許可を検証するには、ファイルにテキストを追加して、**メモ帳を**閉じます。 変更を保存するように求められたら、[**「保存」]**を選択します。
6. 削除アクセス許可を検証するには、 **[新しいテキスト ドキュメント]** を右クリックし、 **[削除]** を選択します。 [はい]  選択して、ファイルの削除を確認します。

### 送信フォレストの信頼を更新または削除する

マネージド ドメインから既存の一方向の送信フォレストを更新する必要がある場合は、`Get-AaddsResourceForestTrusts` スクリプトと `Set-AaddsResourceForestTrust` スクリプトを使用できます。 これらのスクリプトは、フォレストの信頼フレンドリ名または信頼パスワードを更新するシナリオで役立ちます。 マネージド ドメインから一方向の送信信頼を削除するには、`Remove-AaddsResourceForestTrust` スクリプトを使用できます。 関連付けられているオンプレミス AD DS フォレスト内の一方向の受信フォレストの信頼を手動で削除する必要があります。

#### フォレストの信頼を更新する

通常の操作では、マネージド ドメインとオンプレミス フォレストは、それ自体の間で通常のパスワード更新プロセスをネゴシエートします。 これは、通常の AD DS 信頼関係セキュリティ プロセスの一部です。 信頼関係で問題が発生し、既知のパスワードに手動でリセットする場合を除き、信頼パスワードを手動でローテーションする必要はありません。 詳細については、信頼されたドメイン オブジェクトのパスワード変更 を参照してください。

次の手順の例では、送信信頼パスワードを手動でリセットする必要がある場合に、既存の信頼関係を更新する方法を示します。

1. `Get-AaddsResourceForestTrusts` コマンドレットを使用して、`Set-AaddsResourceForestTrust` から  スクリプトと  スクリプトをインストールします。

    ```powershell
    Install-Script -Name Get-AaddsResourceForestTrusts,Set-AaddsResourceForestTrust
    ```
2. 既存の信頼を更新する前に、まず、`Get-AaddsResourceForestTrusts` スクリプトを使用して信頼リソースを取得します。 次の例では、既存の信頼が existingTrust という名前のオブジェクトに割り当てられます。 更新する独自のマネージド ドメイン フォレスト名とオンプレミス フォレスト名を指定します。

    ```powershell
    $existingTrust = Get-AaddsResourceForestTrust `
        -ManagedDomainFqdn "aaddscontoso.com" `
        -TrustFqdn "onprem.contoso.com" `
        -TrustFriendlyName "myAzureADDSTrust"
    ```
3. 既存の信頼パスワードを更新するには、`Set-AaddsResourceForestTrust` スクリプトを使用します。 前の手順の既存の信頼オブジェクトを指定し、新しい信頼関係のパスワードを指定します。 PowerShell ではパスワードの複雑さが強制されないため、環境に安全なパスワードを生成して使用してください。

    ```powershell
    Set-AaddsResourceForestTrust `
        -Trust $existingTrust `
        -TrustPassword <newComplexPassword>
    ```

#### フォレストの信頼を削除する

マネージド ドメインからオンプレミスの AD DS フォレストへの一方向の送信フォレスト信頼が不要になった場合は、それを削除できます。 信頼を削除する前に、オンプレミスの AD DS フォレストに対して認証する必要があるアプリケーションやサービスがないことを確認します。 オンプレミスの AD DS フォレストでも、一方向の受信信頼を手動で削除する必要があります。

1. `Remove-AaddsResourceForestTrust` コマンドレットを使用して、[PowerShell ギャラリー](https://www.powershellgallery.com/) から  スクリプトをインストールします。

    ```powershell
    Install-Script -Name Remove-AaddsResourceForestTrust
    ```
2. 次に、`Remove-AaddsResourceForestTrust` スクリプトを使用してフォレストトラストを削除します。 次の例では、マネージド ドメイン フォレスト *aaddscontoso.com* とオンプレミス フォレスト *onprem.contoso.com* の間の信頼 *myAzureADDSTrust* が削除されます。 削除する独自のマネージド ドメイン フォレスト名とオンプレミス フォレスト名を指定します。

    ```powershell
    Remove-AaddsResourceForestTrust `
        -ManagedDomainFqdn "aaddscontoso.com" `
        -TrustFqdn "onprem.contoso.com" `
        -TrustFriendlyName "myAzureADDSTrust"
    ```

オンプレミスの AD DS フォレストから一方向の受信信頼を削除するには、オンプレミスの AD DS フォレストにアクセスできる管理コンピューターに接続し、次の手順を実行します。

1. **[スタート] | [管理ツール] | [Active Directory Active Directory ドメインと信頼関係]** の順に選択します。
2. *onprem.contoso.com*などのドメインを右選択し、[プロパティ] 選択します。
3. **[信頼]** タブを選択し、マネージド ドメイン フォレストからの既存の受信の信頼を選択します。
4. **の「**を削除」を選択して、受信トラスを削除したいことを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/create-gmsa"} -->
## Microsoft Entra Domain Services のグループ管理サービス アカウント - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/create-gmsa
- Service: entra-id / domain-services
- Article date: 2025-01-21
- Summary: Microsoft Entra Domain Services マネージド ドメインで使用するグループ管理サービス アカウント (gMSA) を作成する方法について説明します

多くの場合、アプリケーションとサービスには、他のリソースで自身を認証するための ID が必要です。 たとえば、Web サービスはデータベース サービスで認証する必要がある場合があります。 アプリケーションまたはサービスに Web サーバー ファームなどの複数のインスタンスがある場合、それらのリソースの ID を手動で作成して構成すると、時間がかかります。

代わりに、グループ管理サービス アカウント (gMSA) を Microsoft Entra Domain Services マネージド ドメインに作成できます。 Windows OS は gMSA の資格情報を自動的に管理します。これにより、大規模なリソース グループの管理が簡略化されます。

この記事では、Azure PowerShell を使用してマネージド ドメインに gMSA を作成する方法について説明します。

### 開始する前に

この記事を完了するには、以下のリソースと特権が必要です。

- 有効な Azure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付け](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)ます。
- Microsoft Entra テナント内の Microsoft Entra Domain Services マネージド ドメインが有効化および構成されています。
    - 必要に応じて、[Microsoft Entra Domain Services マネージド ドメインを作成して構成する](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)チュートリアルを完了します。
- Domain Services マネージド ドメインに参加している Windows Server 管理 VM。
    - 必要に応じて、チュートリアルを完了して [管理 VM を作成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-management-vm)。

### マネージド サービス アカウントの概要

スタンドアロンマネージド サービス アカウント (sMSA) は、パスワードが自動的に管理されるドメイン アカウントです。 この方法により、サービス プリンシパル名 (SPN) の管理が簡略化され、他の管理者に委任された管理が可能になります。 アカウントの資格情報を手動で作成してローテーションする必要はありません。

グループ管理サービス アカウント (gMSA) では、同じ管理が簡略化されますが、ドメイン内の複数のサーバーに対して行われます。 gMSA を使用すると、サーバー ファームでホストされているサービスのすべてのインスタンスが、相互認証プロトコルを機能させるために同じサービス プリンシパルを使用できます。 gMSA をサービス プリンシパルとして使用すると、Windows オペレーティング システムは管理者に依存するのではなく、アカウントのパスワードを再び管理します。

詳細については、 [グループ管理サービス アカウント (gMSA) の概要](https://learn.microsoft.com/ja-jp/windows-server/security/group-managed-service-accounts/group-managed-service-accounts-overview)に関するページを参照してください。

### Domain Services でのサービス アカウントの使用

マネージド ドメインは Microsoft によってロックダウンおよび管理されるため、サービス アカウントを使用する際にはいくつかの考慮事項があります。

- マネージド ドメインのカスタム組織単位 (OU) にサービス アカウントを作成します。
    - 組み込みの *AADDC ユーザーまたは AADDC* コンピューター OU にサービス アカウント *を* 作成することはできません。
    - 代わりに、マネージド ドメインに [カスタム OU を作成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/create-ou) し、そのカスタム OU にサービス アカウントを作成します。
- キー配布サービス (KDS) ルート キーが事前に作成されています。
    - KDS ルート キーは、gMSA のパスワードを生成および取得するために使用されます。 Domain Services では、KDS ルートが自動的に作成されます。
    - 別の KDS ルート キーを作成したり、既定の KDS ルート キーを表示したりする権限がありません。

### gMSA を作成する

まず、 [New-ADOrganizationalUnit](https://learn.microsoft.com/ja-jp/powershell/module/activedirectory/new-adorganizationalunit) コマンドレットを使用してカスタム OU を作成します。 カスタム OU の作成と管理の詳細については、「 [Domain Services のカスタム OU」を](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/create-ou)参照してください。

ヒント

これらの手順を完了して gMSA を作成するには、 [管理 VM を使用します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-management-vm)。 この管理 VM には、必要な AD PowerShell コマンドレットとマネージド ドメインへの接続が既に必要です。

次の例では、*aaddscontoso.com* という名前のマネージド ドメインに *myNewOU* という名前のカスタム OU を作成します。 独自の OU とマネージド ドメイン名を使用します。

```powershell
New-ADOrganizationalUnit -Name "myNewOU" -Path "DC=aaddscontoso,DC=COM"
```

[New-ADServiceAccount](https://learn.microsoft.com/ja-jp/powershell/module/activedirectory/new-adserviceaccount) コマンドレットを使用して gMSA を作成します。 次のパラメーター例が定義されています。

- **-Name** が *WebFarmSvc* に設定されている
- **-Path** パラメーターは、前の手順で作成した gMSA のカスタム OU を指定します。
- DNS エントリとサービス プリンシパル名は*、WebFarmSvc.aaddscontoso.com* に設定されます
- *AADDSCONTOSO-SERVER$* のプリンシパルは、パスワードを取得して ID を使用できます。

独自の名前とドメイン名を指定します。

```powershell
New-ADServiceAccount -Name WebFarmSvc `
    -DNSHostName WebFarmSvc.aaddscontoso.com `
    -Path "OU=MYNEWOU,DC=aaddscontoso,DC=com" `
    -KerberosEncryptionType AES128, AES256 `
    -ManagedPasswordIntervalInDays 30 `
    -ServicePrincipalNames http/WebFarmSvc.aaddscontoso.com/aaddscontoso.com, `
        http/WebFarmSvc.aaddscontoso.com/aaddscontoso, `
        http/WebFarmSvc/aaddscontoso.com, `
        http/WebFarmSvc/aaddscontoso `
    -PrincipalsAllowedToRetrieveManagedPassword AADDSCONTOSO-SERVER$
```

必要に応じて、gMSA を使用するようにアプリケーションとサービスを構成できるようになりました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/create-ou"} -->
## Microsoft Entra Domain Services で組織単位 (OU) を作成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/create-ou
- Service: entra-id / domain-services
- Article date: 2025-01-21
- Summary: Microsoft Entra Domain Services マネージド ドメインでカスタム組織単位 (OU) を作成および管理する方法について説明します。

Active Directory Domain Services (AD DS) マネージド ドメインの組織単位 (OU) を使用すると、ユーザー アカウント、サービス アカウント、コンピューター アカウントなどのオブジェクトを論理的にグループ化できます。 その後、特定の OU に管理者を割り当て、グループ ポリシーを適用して対象の構成設定を適用できます。

Domain Services マネージド ドメインには、次の 2 つの組み込み OU が含まれます。

- *AADDC コンピューター* - マネージド ドメインに参加しているすべてのコンピューターのコンピューター オブジェクトが含まれます。
- *AADDC ユーザー* - Microsoft Entra テナントから同期されたユーザーとグループが含まれます。

Domain Services を使用するワークロードを作成して実行する際に、アプリケーションが自身を認証するためのサービス アカウントを作成することが必要になる場合があります。 これらのサービス アカウントを整理するには、多くの場合、マネージド ドメインにカスタム OU を作成し、その OU 内にサービス アカウントを作成します。

ハイブリッド環境では、オンプレミスの AD DS 環境で作成された OU はマネージド ドメインに同期されません。 マネージド ドメインでは、フラットな OU 構造が使用されます。 階層的な OU 構造を構成した場合でも、異なるオンプレミスドメインまたはフォレストから同期されているにもかかわらず、すべてのユーザー アカウントとグループは *AADDC Users* コンテナーに格納されます。

この記事では、マネージド ドメインに OU を作成する方法について説明します。

### 開始する前に

この記事を完了するには、以下のリソースと特権が必要です。

- 有効な Azure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付け](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)ます。
- Microsoft Entra テナント内の Microsoft Entra Domain Services マネージド ドメインが有効化および構成されています。
    - 必要に応じて、[Microsoft Entra Domain Services マネージド ドメインを作成して構成する](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)チュートリアルを完了します。
- Domain Services マネージド ドメインに参加している Windows Server 管理 VM。
    - 必要に応じて、チュートリアルを完了して [管理 VM を作成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-management-vm)。
- *Microsoft Entra テナントの Microsoft Entra DC 管理者*グループのメンバーであるユーザー アカウント。

### カスタム OU の考慮事項と制限事項

マネージド ドメインでカスタム OU を作成すると、ユーザー管理とグループ ポリシーの適用に対する管理の柔軟性が向上します。 オンプレミスの AD DS 環境と比較して、マネージド ドメインでカスタム OU 構造を作成および管理する際には、いくつかの制限事項と考慮事項があります。

- カスタム OU を作成するには、ユーザーが *AAD DC Administrators* グループのメンバーである必要があります。
- カスタム OU を作成するユーザーには、その OU に対する管理特権 (フル コントロール) が付与され、リソース所有者になります。
    - 既定では、 *AAD DC Administrators* グループもカスタム OU を完全に制御できます。
- *AADDC ユーザー*の既定の OU が作成され、Microsoft Entra テナントから同期されたすべてのユーザー アカウントが含まれます。
    - *AADDC Users* OU から作成したカスタム OU にユーザーまたはグループを移動することはできません。 カスタム OU に移動できるのは、マネージド ドメインで作成されたユーザー アカウントまたはリソースだけです。
- カスタム OU で作成したユーザー アカウント、グループ、サービス アカウント、およびコンピューター オブジェクトは、Microsoft Entra テナントでは使用できません。
    - これらのオブジェクトは、Microsoft Graph API または Microsoft Entra UI を使用して表示されません。これらはマネージド ドメインでのみ使用できます。

### カスタム OU を作成する

カスタム OU を作成するには、ドメインに参加している VM から Active Directory 管理ツールを使用します。 Active Directory 管理センターを使用すると、OU を含むマネージド ドメイン内のリソースを表示、編集、作成できます。

注

マネージド ドメインにカスタム OU を作成するには、 *AAD DC Administrators* グループのメンバーであるユーザー アカウントにサインインする必要があります。

1. 管理 VM にサインインします。 Microsoft Entra 管理センターを使用して接続する手順については、「 [Windows Server VM への接続](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-windows-vm#connect-to-the-windows-server-vm)」を参照してください。
2. [スタート] 画面で、[管理ツール] 選択します。 使用可能な管理ツールの一覧は、 [管理 VM を作成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-management-vm)するためのチュートリアルにインストールされています。
3. OU を作成して管理するには、管理ツールの一覧から **Active Directory 管理センター** を選択します。
4. 左側のウィンドウで、マネージド ドメイン ( *aaddscontoso.com* など) を選択します。 既存の OU とリソースの一覧が表示されます。

    [Image: Active Directory 管理センターでマネージド ドメインを選択する]
5. **[タスク**] ウィンドウが Active Directory 管理センターの右側に表示されます。 aaddscontoso.com *などのドメイン*で、[ **新しい &gt; 組織単位**] を選択します。

    [Image: Active Directory 管理センターで新しい OU を作成するオプションを選択します]
6. [**組織単位の作成**] ダイアログで、**MyCustomOu** などの新しい OU の*名前*を指定します。 *サービス アカウントのカスタム OU など、OU の簡単な説明を入力します*。 必要に応じて、OU の **[管理対象** ] フィールドを設定することもできます。 カスタム OU を作成するには、[ **OK] を選択します**。

    [Image: Active Directory 管理センターからカスタム OU を作成する]
7. Active Directory 管理センターに戻ると、カスタム OU が一覧表示され、使用できるようになります。

    [Image: Active Directory 管理センターで使用できるカスタム OU]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/csp"} -->
## クラウド ソリューション プロバイダー向けの Microsoft Entra Domain Services - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/csp
- Service: entra-id / domain-services
- Article date: 2025-01-21
- Summary: Azure クラウド ソリューション プロバイダー向けの Microsoft Entra Domain Services マネージド ドメインを有効にして管理する方法について説明します

Azure クラウド ソリューション プロバイダー (CSP) は Microsoft パートナー向けのプログラムであり、さまざまな Microsoft クラウド サービスのライセンス チャネルを提供します。 Azure CSP により、パートナーは販売の管理、課金関係の所有、技術および課金のサポートの提供が可能になり、顧客の単一窓口になることができます。 さらに、Azure CSP では、セルフサービス ポータルや付随する API など、ツールの完全なセットも提供しています。 これらのツールにより、CSP パートナーは Azure リソースを簡単にプロビジョニングおよび管理し、顧客とそのサブスクリプションに対して課金を行うことができます。

[パートナー センター ポータル](https://learn.microsoft.com/ja-jp/partner-center/azure-plan-lp)は、すべての Azure CSP パートナー向けのエントリ ポイントであり、豊富な顧客管理機能や自動処理などを提供します。 Azure CSP パートナーは、Web ベースの UI を使用するか、PowerShell と各種 API 呼び出しを使用して、パートナー センターの機能を使用できます。

次の図は、CSP モデルのしくみを大まかに示しています。 ここでは、Contoso には Microsoft Entra テナントがあります。 Contoso は、Azure CSP サブスクリプションにリソースをデプロイして管理する CSP と提携しています。 また、Contoso に直接課金される通常の Azure (Direct) サブスクリプションを持つこともできます。

[Image: CSP モデルの概要]

CSP パートナーのテナントには、*管理*エージェント、*ヘルプ デスク* エージェント、*販売*エージェントの 3 つの特殊なエージェント グループが含まれています。

*管理*エージェント グループは、Contoso の Microsoft Entra テナント内のテナント管理者ロールに割り当てられています。 その結果、CSP パートナーの管理エージェント グループに属するユーザーには、Contoso の Microsoft Entra テナント内のテナント管理者特権が割り当てられます。

CSP パートナーが Contoso の Azure CSP サブスクリプションをプロビジョニングすると、管理エージェント グループがそのサブスクリプションの所有者ロールに割り当てられます。 これにより、CSP パートナーの管理エージェントは、仮想マシン、仮想ネットワーク、Microsoft Entra Domain Services などの Azure リソースを Contoso に代わってプロビジョニングするために必要な特権を持ちます。

詳細については、「[Azure CSP overview (Azure CSP の概要)](https://learn.microsoft.com/ja-jp/partner-center/azure-plan-lp)」をご覧ください。

### Azure CSP サブスクリプションで Domain Services を使用する利点

Microsoft Entra Domain Services では、Windows Server Active Directory Domain Services と完全に互換性のあるマネージド ドメイン サービス (ドメイン参加、グループ ポリシー、LDAP、Kerberos または NTLM 認証など) が提供されます。 数十年にわたり、これらの機能を使用して AD に対して機能する多数のアプリケーションが構築されてきました。 多くの独立系ソフトウェア ベンダー (ISV) が、顧客の構内でアプリケーションを構築し、展開しています。 これらのアプリケーションは、多くの場合はそれらがデプロイされているさまざまな環境へのアクセスを必要とするため、サポートすることが困難です。 Azure CSP サブスクリプションは、Azure のスケールと柔軟性を備えたよりシンプルな代替手段となります。

Domain Services は、Azure CSP サブスクリプションをサポートしています。 顧客の Microsoft Entra テナントに関連付けられている Azure CSP サブスクリプションにアプリケーションをデプロイできます。 その結果、従業員 (サポート スタッフ) は組織の会社の資格情報を使用して、アプリケーションがデプロイされている VM を管理したり、それらの VM に対応したりできます。

また、顧客の Microsoft Entra テナントに Domain Services マネージド ドメインをデプロイすることもできます。 その後、アプリケーションは顧客のマネージド ドメインに接続されます。 Kerberos/NTLM、LDAP、または [System.DirectoryServices API](https://learn.microsoft.com/ja-jp/dotnet/api/system.directoryservices) に依存するアプリケーション内の機能は、顧客のドメインに対してシームレスに動作します。 エンド カスタマーは、アプリケーションがデプロイされているインフラストラクチャの管理を気にすることなく、そのアプリケーションをサービスとして使用するというメリットが得られます。

Domain Services も含め、そのサブスクリプションで使用した Azure リソースに対するすべての課金がチャージ バックされます。 販売、課金、テクニカル サポートなどに関して、顧客との関係の完全な制御を維持できます。 Azure CSP プラットフォームの柔軟性により、小規模のサポート エージェント チームが、アプリケーションのインスタンスをデプロイしている多数の顧客にサービスを提供できます。

### Domain Services の CSP デプロイ モデル

Azure CSP サブスクリプションで Domain Services を使用する場合、2 つの方法があります。 セキュリティとシンプルさに関する顧客の考慮事項に基づいて適切な方法を選択してください。

#### 直接デプロイ モデル

このデプロイ モデルでは、Domain Services は、Azure CSP サブスクリプションに属する仮想ネットワーク内で有効になります。 CSP パートナーの管理エージェントには、次の特権があります。

この機能を管理するには、[全体管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)が必要です。

この機能には、Azure CSP サブスクリプションのサブスクリプション所有者権限が必要です。

[Image: 直接デプロイ モデル]

このデプロイ モデルでは、CSP プロバイダーの管理エージェントが顧客の ID を管理できます。 これらの管理エージェントは、新しいユーザーまたはグループのプロビジョニングや、顧客の Microsoft Entra テナント内でのアプリケーションの追加などのタスクを実行できます。

このデプロイ モデルは、専任の ID 管理者がいないか、または代わりに CSP パートナーに ID を管理してほしいと考えている小規模な組織に適している可能性があります。

#### ピアリング デプロイ モデル

このデプロイ モデルでは、Domain Services は、顧客に属する仮想ネットワーク内で有効になります。つまり、顧客が支払いを行うダイレクト Azure サブスクリプションです。 CSP パートナーは、顧客の CSP サブスクリプションに属する仮想ネットワーク内にアプリケーションをデプロイできます。 仮想ネットワークは、Azure 仮想ネットワーク ピアリングを使用して接続できます。

このデプロイでは、CSP パートナーによって Azure CSP サブスクリプションにデプロイされたワークロードまたはアプリケーションは、顧客のダイレクト Azure サブスクリプションにプロビジョニングされた顧客のマネージド ドメインに接続できます。

[Image: ピアリング デプロイ モデル]

このデプロイ モデルでは権限が分離されるので、CSP パートナーのヘルプデスク エージェントが Azure サブスクリプションを管理し、サブスクリプションにリソースをデプロイして管理できます。 ただし、CSP パートナーのヘルプデスク エージェントに、顧客の Microsoft Entra ディレクトリの高い特権ロールは必要ありません。 顧客の ID 管理者が組織の ID を引き続き管理できます。

このデプロイ モデルは、ISV がオンプレミスのアプリケーションの (顧客の Microsoft Entra ID にも接続する必要のある) ホストされたバージョンを提供するシナリオに適している可能性があります。

### CSP サブスクリプションで Domain Services を管理する

Azure CSP サブスクリプションでマネージド ドメインを管理する場合、次の重要な考慮事項が適用されます。

- **CSP 管理エージェントは、各自の資格情報を使用してマネージド ドメインをプロビジョニングできる:** Domain Services は、Azure CSP サブスクリプションをサポートしています。 CSP パートナーの管理エージェント グループに属するユーザーは、新しいマネージド ドメインをプロビジョニングできます。
- **CSP は、PowerShell を使用して顧客の新しいマネージド ドメインの作成をスクリプト化できる:** 詳細については、「[PowerShell を使用して Domain Services を有効にする方法](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/powershell-create-instance)」を参照してください。
- **CSP 管理エージェントは、その資格情報を使用してマネージド ドメインで継続的な管理タスクを実行することができない。** CSP 管理者ユーザーは、その資格情報を使用してマネージド ドメイン内で日常的な管理タスクを実行することができません。 これらのユーザーは顧客の Microsoft Entra テナントの外部にいるため、顧客の Microsoft Entra テナント内ではその資格情報を使用できません。 Domain Services は、これらのユーザーの Kerberos/NTLM パスワード ハッシュにアクセスできないため、マネージド ドメインではユーザーを認証できません。

    警告

    マネージド ドメインで継続的な管理タスクを実行するには、顧客のディレクトリ内にユーザー アカウントを作成する必要があります。

    CSP 管理者ユーザーの資格情報を使用してマネージド ドメインにサインインすることはできません。 これを行うには、顧客の Microsoft Entra テナントに属するユーザー アカウントの資格情報を使用します。 これらの資格情報は、VM のマネージド ドメインへの参加、DNS の管理、グループ ポリシーの管理などのタスクに必要です。
- **継続的な管理のために作成されたユーザー アカウントを *AAD DC Administrators* グループに追加する必要がある。***AAD DC Administrators* グループには、マネージド ドメインで特定の委任された管理タスクを実行する特権があります。 これらのタスクには、DNS の構成、組織単位の作成、グループ ポリシーの管理などが含まれます。

    CSP パートナーがマネージド ドメインでこれらのタスクを実行するには、顧客の Microsoft Entra テナント内にユーザー アカウントを作成する必要があります。 このアカウントの資格情報は、CSP パートナーの管理エージェントと共有する必要があります。 また、このユーザー アカウントを使用してマネージド ドメインでの構成タスクを実行できるようにするには、このユーザー アカウントを *AAD DC Administrators* グループに追加する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/delete"} -->
## Microsoft Entra Domain Services を削除する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/delete
- Service: entra-id / domain-services
- Article date: 2025-01-21
- Summary: Microsoft Entra Domain Services マネージド ドメインを無効化 (削除) する方法について説明します。

Microsoft Entra Domain Services マネージド ドメインが不要になったら、削除できます。 Domain Services マネージド ドメインをオフにしたり、一時的に無効にしたりすることはできません。 マネージド ドメインを削除しても、Microsoft Entra テナントが削除されるなどの影響はありません。

この記事では、Microsoft Entra 管理センターを使ってマネージド ドメインを削除する方法について説明します。

警告

**削除は永続的であり、元に戻すことはできません。** マネージド ドメインを削除すると、次の処理が行われます。

- マネージド ドメインのドメイン コント ローラーはプロビジョニング解除され、仮想ネットワークから削除されます。
- マネージド ドメイン上のデータは完全に削除されます。 このデータには、作成したカスタム OU、GPO、カスタム DNS レコード、サービス プリンシパル、GMSA などが含まれます。
- マネージド ドメインに参加しているコンピューターでは、ドメインとの信頼関係が失われ、ドメインへの参加を解消する必要があります。
- 企業の AD 資格情報を使用してこれらのコンピューターにサインインすることはできません。 代わりに、コンピューターのローカル管理者の資格情報を使用する必要があります。

### マネージド ドメインを削除する

マネージド ドメインを削除するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. **[Microsoft Entra Domain Services]** を検索して選択します。
3. マネージド ドメインの名前 (*aaddscontoso.com* など) を選択します。
4. **[概要]** ページで **[削除]** を選択します。 削除を確定するには、マネージド ドメインのドメイン名を再度入力し、 **[削除]** を選択します。

マネージド ドメインを削除するには、15 から 20 分以上かかることがあります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/deploy-azure-app-proxy"} -->
## Microsoft Entra Domain Services 用に Microsoft Entra アプリケーション プロキシをデプロイする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/deploy-azure-app-proxy
- Service: entra-id / domain-services
- Article date: 2025-01-21
- Summary: Microsoft Entra Domain Services マネージド ドメインに Microsoft Entra アプリケーション プロキシをデプロイして構成することにより、リモート ワーカーに対して内部アプリケーションへのセキュリティで保護されたアクセスを提供する方法について説明します

Microsoft Entra Domain Services を使用して、オンプレミスで実行されているレガシ アプリケーションを Azure にリフトアンドシフトすることができます。 その後、Microsoft Entra アプリケーション プロキシにより、インターネット経由でアクセスできるように、Domain Services マネージド ドメインの一部として内部アプリケーションを安全に公開して、リモート ワーカーをサポートできます。

Microsoft Entra アプリケーション プロキシを使用したことがなく、詳細を確認したい場合は、[内部アプリケーションに安全なリモート アクセスを提供する方法](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/deploy-azure-app-proxy)に関する記事を参照してください。

この記事では、Microsoft Entra プライベート ネットワーク コネクタを作成および構成して、マネージド ドメイン内のアプリケーションへの安全なアクセスを提供する方法について説明します。

### 開始する前に

この記事を完了するには、以下のリソースと特権が必要です。

- 有効な Azure サブスクリプション
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付け](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)ます。
    - Microsoft Entra アプリケーション プロキシを使用するには、**Microsoft Entra ID P1 または P2 のライセンス**が必要です。
- Microsoft Entra テナントで有効にされていて、構成されている Microsoft Entra Domain Services マネージド ドメイン。
    - 必要に応じて、[Microsoft Entra Domain Services のマネージド ドメインを作成して構成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)。

### ドメインに参加している Windows VM を作成する

環境内で実行されているアプリケーションにトラフィックをルーティングするために、Microsoft Entra プライベート ネットワーク コネクタ コンポーネントをインストールします。 この Microsoft Entra プライベート ネットワーク コネクタは、マネージド ドメインに参加している Windows Server 仮想マシン (VM) にインストールする必要があります。 アプリケーションによっては、それぞれにコネクタがインストールされている複数のサーバーをデプロイできます。 このデプロイ オプションにより、可用性が向上し、負荷の高い認証を処理できます。

Microsoft Entra プライベート ネットワーク コネクタを実行する VM は、マネージド ドメインと同じであるか、ピアリングされている仮想ネットワーク上に存在する必要があります。 アプリケーション プロキシを使用して発行するアプリケーションをホストする VM も、同じ Azure 仮想ネットワークにデプロイする必要があります。

Microsoft Entra プライベート ネットワーク コネクタ用の VM を作成するために、次の手順を実行します。

1. [カスタム OU を作成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/create-ou)。 このカスタム OU を管理する権限を、マネージド ドメイン内のユーザーに委任できます。 Microsoft Entra アプリケーション プロキシ用の VM とアプリケーションを実行する VM は、既定の *Microsoft Entra DC Computers* OU ではなく、カスタム OU の一部である必要があります。
2. [仮想マシンをドメインに参加させます](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-windows-vm)。Microsoft Entra プライベート ネットワーク コネクタを実行しているものと、アプリケーションを実行しているものの両方をマネージド ドメインに参加させます。 前の手順のカスタム OU 内に、これらのコンピューター アカウントを作成します。

### Microsoft Entra プライベート ネットワーク コネクタをダウンロードする

次の手順を実行して、Microsoft Entra プライベート ネットワーク コネクタをダウンロードします。 ダウンロードしたセットアップ ファイルは、次のセクションでアプリケーション プロキシ VM にコピーします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **[エンタープライズ アプリケーション]** を選択します。
3. 左側のメニューから **[アプリケーション プロキシ]** を選択します。 最初のコネクタを作成してアプリケーション プロキシを有効にするには、**コネクタをダウンロードする**ためのリンクを選びます。
4. ダウンロード ページでライセンス条項とプライバシー アグリーメントに同意し、**[使用条件に同意してダウンロードする]** を選択します。

    [Image: Microsoft Entra プライベート ネットワーク コネクタをダウンロードする]

### Microsoft Entra プライベート ネットワーク コネクタをインストールして登録する

VM を Microsoft Entra プライベート ネットワーク コネクタとして使用する準備ができたら、Microsoft Entra 管理センターからダウンロードしたセットアップ ファイルをコピーして実行します。

1. Microsoft Entra プライベート ネットワーク コネクタのセットアップ ファイルを VM にコピーします。
2. *MicrosoftEntraPrivateNetworkConnectorInstaller.exe* などのセットアップ ファイルを実行します。 ソフトウェア ライセンス条項に同意します。
3. インストール時に、Microsoft Entra ディレクトリのアプリケーション プロキシにコネクタを登録するように求められます。

    注

    コネクタの登録に使用するグローバル管理者アカウントは、アプリケーション プロキシ サービスを有効にしたのと同じディレクトリに属している必要があります。

    たとえば、Microsoft Entra ドメインが *contoso.com* の場合、アカウントは `admin@contoso.com` か、そのドメインの別の有効なエイリアスである必要があります。

    - コネクタをインストールする VM で [Internet Explorer セキュリティ強化の構成] がオンになっていると、登録画面がブロックされることがあります。 アクセスを許可するには、エラー メッセージの指示に従うか、インストールプロセス中に Internet Explorer のセキュリティ強化をオフにします。
    - コネクタの登録が失敗する場合は、[アプリケーション プロキシのトラブルシューティング](https://learn.microsoft.com/ja-jp/azure/active-directory/app-proxy/application-proxy-troubleshoot)に関する記事をご覧ください。
4. セットアップの最後に、送信プロキシを使用している環境に対してメモが表示されます。 送信プロキシ経由で動作するように Microsoft Entra プライベート ネットワーク コネクタを構成するには、提供されたスクリプト (`C:\Program Files\Microsoft Entra private network connector\ConfigureOutBoundProxy.ps1` など) を実行します。
5. Microsoft Entra 管理センターのアプリケーション プロキシ ページで、次の例に示すように、新しいコネクタが "アクティブ" の状態で一覧の中に表示されます。

    [Image: Microsoft Entra 管理センターにアクティブと表示されている新しい Microsoft Entra プライベート ネットワーク コネクタ]

注

Microsoft Entra アプリケーション プロキシを介して認証を行うアプリケーションの高可用性を実現するために、複数の VM にコネクタをインストールできます。 前のセクションと同じ手順を繰り返して、マネージド ドメインに参加している他のサーバーにコネクタをインストールします。

### リソースベースの Kerberos の制約付き委任を有効にする

統合 Windows 認証 (IWA) を使用するアプリケーションへのシングル サインオンを使用するには、ユーザーの権限を借用して代理でトークンを送受信するためのアクセス許可を Microsoft Entra プライベート ネットワーク コネクタに付与します。 これらのアクセス許可を付与するには、コネクタに対してマネージド ドメインのリソースにアクセスするための Kerberos の制約付き委任 (KCD) を構成します。 マネージド ドメインのドメイン管理者特権がないため、マネージド ドメインに従来のアカウントレベルの KCD を構成することはできません。 代わりに、リソースベースの KCD を使用します。

詳細については、「[Microsoft Entra Domain Services で Kerberos の制約付き委任 (KCD) を構成する](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/deploy-kcd)」を参照してください。

注

次の PowerShell コマンドレットを実行するには、Microsoft Entra テナントの "Microsoft Entra DC 管理者" グループのメンバーであるユーザー アカウントにサインインする必要があります。

プライベート ネットワーク コネクタ VM とアプリケーション VM 用のコンピューター アカウントは、リソースベースの KCD を構成するアクセス許可を持つカスタム OU 内にある必要があります。 組み込みの *Microsoft Entra DC Computers* コンテナー内にあるコンピューター アカウントに対して、リソースベースの KCD を構成することはできません。

[Get-ADComputer](https://learn.microsoft.com/ja-jp/powershell/module/activedirectory/get-adcomputer) を使用して、Microsoft Entra プライベート ネットワーク コネクタがインストールされているコンピューターの設定を取得します。 ドメインに参加している管理 VM で、"*Microsoft Entra DC 管理者*" グループのメンバーであるユーザー アカウントとしてログインし、次のコマンドレットを実行します。

次の例では、*appproxy.aaddscontoso.com* という名前のコンピューター アカウントに関する情報を取得します。 前の手順で構成した Microsoft Entra アプリケーション プロキシ VM に独自のコンピューター名を指定します。

```powershell
$ImpersonatingAccount = Get-ADComputer -Identity appproxy.aaddscontoso.com
```

Microsoft Entra アプリケーション プロキシの背後にあるアプリを実行するアプリケーション サーバーごとに [Set-ADComputer](https://learn.microsoft.com/ja-jp/powershell/module/activedirectory/set-adcomputer) PowerShell コマンドレットを使用して、リソースベースの KCD を構成します。 次の例では、Microsoft Entra プライベート ネットワーク コネクタに *appserver.aaddscontoso.com* コンピューターを使用するためのアクセス許可が付与されます。

```powershell
Set-ADComputer appserver.aaddscontoso.com -PrincipalsAllowedToDelegateToAccount $ImpersonatingAccount
```

複数の Microsoft Entra プライベート ネットワーク コネクタをデプロイする場合は、コネクタ インスタンスごとにリソースベースの KCD を構成する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/deploy-kcd"} -->
## Microsoft Entra Domain Services での Kerberos 制約付き委任の使用 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/deploy-kcd
- Service: entra-id / domain-services
- Article date: 2025-01-21
- Summary: Microsoft Entra Domain Services マネージド ドメインでリソース ベースの Kerberos 制約付き委任 (KCD) を有効にする方法について説明します。

アプリケーションを実行する際に、それらのアプリケーションが別のユーザーのコンテキストでリソースにアクセスする必要がある場合があります。 Active Directory Domain Services (AD DS) では、このユース ケースを可能にする *Kerberos 委任* と呼ばれるメカニズムがサポートされています。 その後、Kerberos *の制約付き* 委任 (KCD) はこのメカニズムに基づいて、ユーザーのコンテキストでアクセスできる特定のリソースを定義します。

Microsoft Entra Domain Services マネージド ドメインは、従来のオンプレミスの AD DS 環境よりも安全にロックダウンされるため、より安全な *リソース ベース* の KCD を使用します。

この記事では、Domain Services マネージド ドメインでリソースベースの Kerberos 制約付き委任を構成する方法について説明します。

### [前提条件]

この記事を完了するには、以下のリソースが必要です。

- 有効な Azure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付け](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)ます。
- Microsoft Entra テナント内の Microsoft Entra Domain Services マネージド ドメインが有効化および構成されています。
    - 必要に応じて、[Microsoft Entra Domain Services のマネージド ドメインを作成して構成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)。
- Domain Services マネージド ドメインに参加している Windows Server 管理 VM。
    - 必要に応じて、チュートリアルを完了して [Windows Server VM を作成し、それをマネージド ドメインに参加](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-windows-vm) させ、 [AD DS 管理ツールをインストール](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-management-vm)します。
- *Microsoft Entra テナントの Microsoft Entra DC 管理者*グループのメンバーであるユーザー アカウント。

### Kerberos の制約付き委任の概要

Kerberos 委任を使用すると、あるアカウントが別のアカウントを偽装してリソースにアクセスできるようになります。 たとえば、バックエンド Web コンポーネントにアクセスする Web アプリケーションは、バックエンド接続を行うときに、それ自体を別のユーザー アカウントとして偽装できます。 Kerberos の委任は、偽装する側のアカウントがどのリソースにアクセスできるかの制限がないため、安全ではありません。

Kerberos *の制約付き* 委任 (KCD) は、別の ID を偽装するときに、指定されたサーバーまたはアプリケーションが接続できるサービスまたはリソースを制限します。 従来の KCD では、サービスのドメイン アカウントを構成するためにドメイン管理者特権が必要であり、1 つのドメインで実行するようにアカウントが制限されます。

従来の KCD にもいくつかの問題があります。 たとえば、以前のオペレーティング システムでは、サービス管理者は、所有しているリソース サービスに委任されたフロントエンド サービスを把握するのに役立つ方法がありませんでした。 リソース サービスに委任できるフロントエンド サービスは、潜在的な攻撃ポイントでした。 リソース サービスに委任するように構成されたフロントエンド サービスをホストしているサーバーが侵害された場合、リソース サービスも侵害される可能性があります。

マネージド ドメインでは、ドメイン管理者特権がありません。 その結果、従来のアカウント ベースの KCD をマネージド ドメインで構成することはできません。 代わりにリソース ベースの KCD を使用できます。これは、より安全です。

#### リソース ベースの KCD

Windows Server 2012 以降では、サービス管理者は、サービスの制約付き委任を構成できます。 このモデルは、リソース ベースの KCD と呼ばれます。 この方法では、バックエンド サービス管理者は、特定のフロントエンド サービスによる KCD の使用を許可または拒否できます。

リソースベースの KCD は PowerShell を使用して構成します。 偽装する側のアカウントがコンピューター アカウントであるか、ユーザー アカウントまたはサービス アカウントであるかに応じて、[Set-ADComputer](https://learn.microsoft.com/ja-jp/powershell/module/activedirectory/set-adcomputer) コマンドレットまたは [Set-ADUser](https://learn.microsoft.com/ja-jp/powershell/module/activedirectory/set-aduser) コマンドレットを使用します。

### コンピューター アカウントのリソース ベースの KCD を構成する

このシナリオでは、 *contoso-webapp.aaddscontoso.com* という名前のコンピューターで実行される Web アプリがあるとします。

Web アプリは、ドメイン ユーザーのコンテキストで *contoso-api.aaddscontoso.com* という名前のコンピューター上で実行される Web API にアクセスする必要があります。

このシナリオを構成するには、次の手順を実行します。

1. [カスタム OU を作成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/create-ou)。 このカスタム OU を管理する権限を、マネージド ドメイン内のユーザーに委任できます。
2. [仮想マシン (](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-windows-vm)Web アプリを実行する仮想マシンと Web API を実行する仮想マシンの両方) をマネージド ドメインにドメイン参加させます。 前の手順のカスタム OU 内に、これらのコンピューター アカウントを作成します。

    注

    Web アプリと Web API のコンピューター アカウントは、リソース ベースの KCD を構成するアクセス許可を持つカスタム OU 内に存在する必要があります。 組み込みの *Microsoft Entra DC Computers* コンテナー内にあるコンピューター アカウントに対して、リソースベースの KCD を構成することはできません。
3. 最後に、 [Set-ADComputer](https://learn.microsoft.com/ja-jp/powershell/module/activedirectory/set-adcomputer) PowerShell コマンドレットを使用してリソース ベースの KCD を構成します。

    ドメインに参加している管理 VM で、*Microsoft Entra DC 管理者*グループのメンバーであるユーザー アカウントとしてログインし、次のコマンドレットを実行します。 必要に応じて、独自のコンピューター名を指定します。

    ```powershell
    $ImpersonatingAccount = Get-ADComputer -Identity contoso-webapp.aaddscontoso.com
    Set-ADComputer contoso-api.aaddscontoso.com -PrincipalsAllowedToDelegateToAccount $ImpersonatingAccount
    ```

### ユーザー アカウントのリソース ベースの KCD を構成する

このシナリオでは、 *appsvc* という名前のサービス アカウントとして実行される Web アプリがあるとします。 Web アプリは、ドメイン ユーザーのコンテキストで *backendsvc* という名前のサービス アカウントとして実行される Web API にアクセスする必要があります。 このシナリオを構成するには、次の手順を実行します。

1. [カスタム OU を作成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/create-ou)。 このカスタム OU を管理する権限を、マネージド ドメイン内のユーザーに委任できます。
2. バックエンド Web API/リソースを実行する[仮想マシン](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-windows-vm)をマネージド ドメインにドメイン参加させます。 カスタム OU 内にコンピューター アカウントを作成します。
3. カスタム OU 内で Web アプリを実行するために使用するサービス アカウント ( *appsvc* など) を作成します。

    注

    ここでも、Web API VM のコンピューター アカウントと Web アプリのサービス アカウントは、リソース ベースの KCD を構成するアクセス許可を持つカスタム OU 内に存在する必要があります。 組み込みの *Microsoft Entra DC Computers または Microsoft Entra DC* Users コンテナー内のアカウントに対してリソース ベースの KCD *を* 構成することはできません。 これは、Microsoft Entra ID から同期されたユーザー アカウントを使用してリソース ベースの KCD を設定できないことも意味します。 Domain Services で特別に作成されたサービス アカウントを作成して使用する必要があります。
4. 最後に、 [Set-ADUser](https://learn.microsoft.com/ja-jp/powershell/module/activedirectory/set-aduser) PowerShell コマンドレットを使用してリソース ベースの KCD を構成します。

    ドメインに参加している管理 VM で、*Microsoft Entra DC 管理者*グループのメンバーであるユーザー アカウントとしてログインし、次のコマンドレットを実行します。 必要に応じて、独自のサービス名を指定します。

    ```powershell
    $ImpersonatingAccount = Get-ADUser -Identity appsvc
    Set-ADUser backendsvc -PrincipalsAllowedToDelegateToAccount $ImpersonatingAccount
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/deploy-sp-profile-sync"} -->
## Domain Services で SharePoint ユーザー プロファイル サービスを有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/deploy-sp-profile-sync
- Service: entra-id / domain-services
- Article date: 2025-01-21
- Summary: SharePoint Server のプロファイル同期をサポートするように Microsoft Entra Domain Services マネージド ドメインを構成する方法について説明します

SharePoint Server には、ユーザー プロファイルを同期するサービスが含まれています。 この機能を使用すると、ユーザー プロファイルを一元的な場所に格納し、複数の SharePoint サイトとファーム間でアクセスできます。 SharePoint Server ユーザー プロファイル サービスを構成するには、Microsoft Entra Domain Services マネージド ドメインで適切なアクセス許可を付与する必要があります。 詳細については、「 [SharePoint Server でのユーザー プロファイルの同期](https://learn.microsoft.com/ja-jp/SharePoint/administration/user-profile-service-administration)」を参照してください。

この記事では、SharePoint Server ユーザー プロファイル同期サービスを許可するように Domain Services を構成する方法について説明します。

### 開始する前に

この記事を完了するには、以下のリソースと特権が必要です。

- 有効な Azure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付け](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)ます。
- Microsoft Entra テナント内の Microsoft Entra Domain Services マネージド ドメインが有効化および構成されています。
    - 必要に応じて、[Microsoft Entra Domain Services マネージド ドメインを作成して構成する](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)チュートリアルを完了します。
- Domain Services マネージド ドメインに参加している Windows Server 管理 VM。
    - 必要に応じて、チュートリアルを完了して [管理 VM を作成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-management-vm)。
- *Microsoft Entra テナントの Microsoft Entra DC 管理者*グループのメンバーであるユーザー アカウント。
- ユーザー プロファイル同期サービスの SharePoint サービス アカウント名。 *プロファイル同期アカウント*の詳細については、「[SharePoint Server の管理アカウントとサービス アカウントの計画](https://learn.microsoft.com/ja-jp/sharepoint/security-for-sharepoint-server/plan-for-administrative-and-service-accounts)」を参照してください。 SharePoint サーバーの全体管理 Web サイトから *プロファイル同期アカウント* 名を取得するには、[ **アプリケーション管理**&gt;**管理サービス アプリケーション**&gt;**User Profile Service アプリケーション**] をクリックします。 詳細については、「 [SharePoint Server で SharePoint Active Directory インポートを使用してプロファイル同期を構成](https://learn.microsoft.com/ja-jp/SharePoint/administration/configure-profile-synchronization-by-using-sharepoint-active-directory-import)する」を参照してください。

### サービス アカウントの概要

マネージド ドメインでは、 *Microsoft Entra DC サービス アカウント* という名前のセキュリティ グループが *ユーザー* 組織単位 (OU) の一部として存在します。 このセキュリティ グループのメンバーには、次の特権が委任されます。

- ルート DSE に対する**ディレクトリ変更**特権をレプリケートします。
- **構成**の名前付けコンテキスト ( コンテナー) に対する`cn=configuration`特権をレプリケートします。

*Microsoft Entra DC サービス アカウント* セキュリティ グループは、組み込みのグループ *Pre-Windows 2000 互換アクセス*のメンバーでもあります。

このセキュリティ グループに追加すると、SharePoint Server ユーザー プロファイル同期サービスのサービス アカウントに、正常に動作するために必要な特権が付与されます。

### SharePoint Server ユーザー プロファイル同期のサポートを有効にする

SharePoint Server のサービス アカウントには、ディレクトリに変更をレプリケートし、SharePoint Server ユーザー プロファイルの同期を正しく機能させるために十分な特権が必要です。 これらの特権を提供するには、SharePoint ユーザー プロファイルの同期に使用されるサービス アカウントを *Microsoft Entra DC サービス アカウント* グループに追加します。

Domain Services 管理 VM から、次の手順を実行します。

注

マネージド ドメインのグループ メンバーシップを編集するには、 *AAD DC Administrators* グループのメンバーであるユーザー アカウントにサインインしている必要があります。

1. [スタート] 画面で、[管理ツール] 選択します。 使用可能な管理ツールの一覧は、 [管理 VM を作成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-management-vm)するためのチュートリアルにインストールされています。
2. グループ メンバーシップを管理するには、管理ツールの一覧から **Active Directory 管理センター** を選択します。
3. 左側のウィンドウで、マネージド ドメイン ( *aaddscontoso.com* など) を選択します。 既存の OU とリソースの一覧が表示されます。
4. **ユーザー** OU を選択し、*Microsoft Entra DC サービス アカウント セキュリティ グループを*選択します。
5. [ **メンバー]** を選択し、[ **追加...**] を選択します。
6. SharePoint サービス アカウントの名前を入力し、[ **OK]** を選択します。 次の例では、SharePoint サービス アカウントの名前は *spadmin です*。

    [Image: SharePoint サービス アカウントを Microsoft Entra DC サービス アカウント セキュリティ グループに追加する]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/faqs"} -->
## Microsoft Entra Domain Services に関してよくあるご質問 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/faqs
- Service: entra-id / domain-services
- Article date: 2025-01-21
- Summary: Microsoft Entra Domain Services の構成、管理、可用性に関してよくあるいくつかの質問について読み、理解してください

このページでは、Microsoft Entra Domain Services に関してよくあるご質問にお答えします。

### 構成

#### 1 つの Microsoft Entra ディレクトリに対して複数のマネージド ドメインを作成できますか?

不正解です。 Microsoft Entra Domain Services によって保守されるマネージド ドメインは、1 つの Microsoft Entra ディレクトリに対して 1 つだけ作成できます。

#### クラシック仮想ネットワークで Microsoft Entra Domain Services を有効にできますか?

従来の仮想ネットワークはサポートされていません。

詳細については、[公式の非推奨に関する通知](https://azure.microsoft.com/updates/we-are-retiring-azure-ad-domain-services-classic-vnet-support-on-march-1-2023/)を参照してください。

#### Azure Resource Manager 仮想ネットワークでは Microsoft Entra Domain Services を有効にできますか?

はい。 Microsoft Entra Domain Services は、Azure Resource Manager 仮想ネットワークで有効にできます。 マネージド ドメインを作成するときに、クラシック Azure 仮想ネットワークは使用できなくなりました。

#### Azure CSP (クラウド ソリューション プロバイダー) サブスクリプションで Microsoft Entra Domain Services を有効にできますか?

はい。 詳しくは、[Azure CSP サブスクリプションで Microsoft Entra Domain Services を有効にする方法](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/csp)に関する記事をご覧ください。

#### Microsoft Entra Domain Services をサブスクリプション内の複数の仮想ネットワークで利用できますか?

サービス自体は、このシナリオを直接サポートしていません。 マネージド ドメインは一度に 1 つの仮想ネットワークでのみ利用できます。 ただし、複数の仮想ネットワーク間で接続を構成して、Microsoft Entra Domain Services を他の仮想ネットワークに公開することはできます。 詳細については、[VPN ゲートウェイを使用して Azure の仮想ネットワークを接続する方法](https://learn.microsoft.com/ja-jp/azure/vpn-gateway/vpn-gateway-howto-vnet-vnet-portal-classic)または[仮想ネットワーク ピアリング](https://learn.microsoft.com/ja-jp/azure/virtual-network/virtual-network-peering-overview)に関する記事を参照してください。

#### Microsoft Entra Domain Services のマネージド ドメインにドメイン コントローラーを追加することはできますか?

不正解です。 Microsoft Entra Domain Services によって提供されるドメインは、マネージド ドメインです。 このドメインに対してドメイン コントローラーをプロビジョニング、構成、管理する必要はありません。 これらの管理アクティビティは、Microsoft からサービスとして提供されています。 そのため、マネージド ドメインにドメイン コントローラー (読み取り/書き込みまたは読み取り専用) をさらに追加することはできません。

#### Azure リージョンがオフラインになった場合、アプリケーションの回復のためにマネージド ドメインを別の Azure リージョンに拡張できますか?

はい。 同じ名前空間および構成をマネージド ドメインと共有するレプリカ セットを作成できます。 レプリカ セットは、Domain Services がサポートされている任意の Azure リージョンの、ピアリングされた任意の仮想ネットワークに追加できます。 詳細については、「[Microsoft Entra Domain サービスのレプリカ セットの概念と機能](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/concepts-replica-sets)」を参照してください。

#### フェデレーション Microsoft Entra ディレクトリで、パスワード ハッシュ同期なしで Microsoft Entra Domain Services を有効にできますか?

不正解です。 NTLM または Kerberos を使ってユーザーの認証を行うには、Microsoft Entra Domain Services でユーザー アカウントのパスワード ハッシュにアクセスする必要があります。 フェデレーション ディレクトリでは、パスワード ハッシュは Microsoft Entra ディレクトリに格納されません。 したがって、Microsoft Entra Domain Services は、このような Microsoft Entra ディレクトリでは機能しません。

ただし、パスワード ハッシュ同期に Microsoft Entra Connect を使っている場合は、パスワード ハッシュ値が Microsoft Entra ID に保存されるため、Azure AD Domain Services を使用できます。

#### PowerShell を使用して Microsoft Entra Domain Services を有効にできますか?

はい。 詳しくは、[PowerShell を使って Microsoft Entra Domain Services を有効にする方法](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/powershell-create-instance)に関する記事をご覧ください。

#### Resource Manager テンプレートを使用して Microsoft Entra Domain Services を有効にできますか?

はい。Resource Manager テンプレートを使って Microsoft Entra Domain Services マネージド ドメインを作成できます。 テンプレートを配置する前に、Microsoft Entra 管理センターまたは PowerShell を使って、管理用のサービス プリンシパルと Microsoft Entra グループを作成する必要があります。 Microsoft Entra 管理センターで Microsoft Entra Domain Services マネージド ドメインを作成するときは、他のデプロイで使うためにテンプレートをエクスポートするオプションもあります。 詳しくは、[Azure Resource Manager テンプレートを使用した Domain Services マネージド ドメインの作成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/template-create-instance)に関する記事をご覧ください。

#### 自分のディレクトリに招待したゲスト ユーザーは Microsoft Entra Domain Services を使用できますか?

不正解です。 [Microsoft Entra B2B](https://learn.microsoft.com/ja-jp/azure/active-directory/external-identities/what-is-b2b) 招待プロセスを使ってご自分の Microsoft Entra ディレクトリに招待したゲスト ユーザーは、ご自分の Microsoft Entra Domain Services マネージド ドメインに同期されます。 ただし、これらのユーザーのパスワードがご自分の Microsoft Entra ディレクトリに格納されることはありません。 そのため、Microsoft Entra Domain Services では、これらのユーザーの NTLM と Kerberos のハッシュをご自分のマネージド ドメインに同期することはできません。 このようなユーザーは、サインインすることも、マネージド ドメインにコンピューターを参加させることもできません。

#### Domain Services とオンプレミス フォレストの間に、双方向のフォレストの信頼を作成できますか?

はい。双方向の信頼を作成できます。 また、一方向の送信信頼または一方向の受信信頼を作成して、ユーザーの認証とアクセスのさまざまなシナリオをサポートすることもできます。 詳細については、「[フォレスト信頼の作成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-forest-trust)」を参照してください。

#### Domain Services で、オンプレミスの子ドメインとの外部信頼の作成はサポートされていますか?

現在、Domain Services でサポートされているのはフォレストの信頼のみであり、外部ドメインの信頼はサポートされていません。

#### マネージド ドメインを移動できますか。

Domain Services マネージド ドメインを作成した後、それを別のサブスクリプション、リソース グループ、またはリージョンに移動することはできません。 リージョンを変更するには、移行先のリージョンに新しいレプリカ セットをデプロイすることが考えられます。 完了したら、不要なリージョンのレプリカ セットを削除します。 残りの設定の回避策として、PowerShell または Microsoft Entra 管理センターを使用して[マネージド ドメインを削除](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/delete-aadds)し、必要な設定で再作成することができます。 マネージド ドメインの再作成中は、復元操作を実行できません。

#### Microsoft Entra Domain Services の既存のドメインの名前を変更できますか?

不正解です。 Microsoft Entra Domain Services のマネージド ドメインを作成した後で、DNS ドメイン名を変更することはできません。 マネージド ドメインを作成するときは、DNS ドメイン名を慎重に選択してください。 DNS ドメイン名を選ぶときの考慮事項については、[Microsoft Entra Domain Services のマネージド ドメインの作成と構成に関するチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance#create-a-managed-domain)の記事をご覧ください。

#### Microsoft Entra Domain Services には高可用性オプションが含まれていますか?

はい。 Microsoft Entra Domain Services の各マネージド ドメインには、2 つのドメイン コントローラーが含まれています。 これらのドメイン コントローラーについては、お客様が管理または接続することはありません。これらはマネージド サービスの一部です。 Availability Zones をサポートするリージョンに Microsoft Entra Domain Services をデプロイすると、ドメイン コントローラーはゾーン間に分散されます。 Availability Zones をサポートしていないリージョンの場合、ドメイン コントローラーは可用性セット間に分散されます。 この分散に対する構成オプションや管理制御はありません。 詳細については、「[Azure の仮想マシンの可用性オプション](https://learn.microsoft.com/ja-jp/azure/virtual-machines/availability)」を参照してください。

### 管理と操作

#### リモート デスクトップを使用してマネージド ドメインのドメイン コントローラーに接続できますか。

不正解です。 リモート デスクトップを使用してマネージド ドメインのドメイン コントローラーに接続する権限はありません。 "Microsoft Entra DC 管理者" グループのメンバーは、Active Directory 管理センター (ADAC) や AD PowerShell などの AD 管理ツールを使って、マネージド ドメインを管理できます。 これらのツールは、"*リモート サーバー管理ツール*" 機能を使用して、マネージド ドメインに参加している Windows サーバーにインストールされます。 詳しくは、「[Microsoft Entra Domain Services のマネージド ドメインを構成および管理するための管理 VM を作成する](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-management-vm)」をご覧ください。

#### Microsoft Entra Domain Services を有効にしました。 このドメインに参加しているドメイン コンピューターでは、どのユーザー アカウントを使用できますか。

マネージド ドメインの一部であるすべてのユーザー アカウントを VM に参加させることができます。 "Microsoft Entra DC 管理者" グループのメンバーは、マネージド ドメインに参加しているマシンへのリモート デスクトップ アクセスを許可されています。

#### ドメインに参加できるマシンの数に対するクォータはありますか?

Domain Services にドメイン参加済みのマシン用のクォータがありません。

#### マネージド ドメインに参加している仮想マシン (VM) の時刻はどのように同期されますか?

非常に正確な時刻の実現のために、Azure 上で実行される VM は Azure ホストと同期されます。 オンプレミスで実行される Azure 以外の VM では、ドメインに参加している VM と同様に、外部 NTP タイム ソースと同期するように Windows タイム サービスを構成する必要があります。 詳細については、「[Azure で Active Directory Windows Virtual Machines の時間メカニズムを構成する](https://learn.microsoft.com/ja-jp/azure/virtual-machines/windows/external-ntpsource-configuration)」を参照してください。

#### Microsoft Entra Domain Services によって提供されるマネージド ドメインに対するドメイン管理者特権はありますか?

不正解です。 マネージド ドメインの管理特権は付与されません。 "*ドメイン管理者*" 特権と "*エンタープライズ管理者*" 特権は、ドメイン内では使用できません。 また、オンプレミスの Active Directory 内のドメイン管理者グループまたはエンタープライズ管理者グループのメンバーにも、マネージド ドメインのドメイン/エンタープライズ管理者特権は付与されません。

#### マネージド ドメインで LDAP または他の AD 管理ツールを使用してグループ メンバーシップを変更できますか。

Microsoft Entra ID から Microsoft Entra Domain Services に同期されるユーザーとグループは、元のソースが Microsoft Entra ID であるため変更できません。 これには、**AADDC Users** マネージド組織単位からカスタム組織単位へのユーザーまたはグループの移動が含まれます。 マネージド ドメインがソースのユーザーまたはグループは変更できます。

#### マネージド ドメイン内の DHCP サーバーを承認できますか?

不正解です。 マネージド ドメインでは使用できない DHCP サーバーを承認するためにドメイン管理者メンバーシップが必要です。

#### Microsoft Entra ディレクトリに対して行った変更がマネージド ドメインに反映されるまで、どのくらいの時間がかかりますか?

Microsoft Entra UI または PowerShell を使って Microsoft Entra ディレクトリで行った変更は、マネージド ドメインに自動的に同期されます。 この同期プロセスはバック グラウンドで実行されます。 この同期ですべてのオブジェクトの変更を完了するための期間は定義されていません。

#### Microsoft Entra Domain Services によって提供されるマネージド ドメインのスキーマを拡張できますか?

不正解です。 スキーマは、Microsoft がマネージド ドメインを管理することで管理されます。 Microsoft Entra Domain Services では、スキーマの拡張はサポートされていません。

#### マネージド ドメインの DNS レコードを変更または追加できますか。

はい。 "Microsoft Entra DC 管理者" グループのメンバーには、マネージド ドメインの DNS レコードを変更するための "DNS 管理者" 特権が付与されています。 これらのユーザーは、マネージド ドメインに参加している Windows Server を実行しているマシン上で DNS マネージャー コンソールを使用して、DNS を管理できます。 DNS マネージャー コンソールを使用するには、"*リモート サーバー管理ツール*" オプション機能の一部である "*DNS サーバー ツール*" をサーバーにインストールします。 詳しくは、[Microsoft Entra Domain Services のマネージド ドメインでの DNS の管理](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/manage-dns)に関する記事をご覧ください。

#### マネージド ドメインのパスワード有効期間ポリシーとはどのようなものですか。

Microsoft Entra Domain Services のマネージド ドメインでのパスワードの既定の有効期間は 90 日です。 このパスワード有効期間は、Microsoft Entra ID で構成されているパスワード有効期間と同期されません。 そのため、ユーザーのパスワードが、マネージド ドメインでは期限が切れても、Microsoft Entra ID ではまだ有効である場合があります。 このような場合、ユーザーは Microsoft Entra ID のパスワードを変更する必要があり、新しいパスワードはマネージド ドメインに同期されます。 マネージド ドメインでパスワードの既定の有効期間を変更する場合は、[カスタム パスワード ポリシーを作成して構成する](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/password-policy)ことができます。

さらに、*DisablePasswordExpiration* の Microsoft Entra パスワード ポリシーはマネージド ドメインに同期されます。 *DisablePasswordExpiration* が Microsoft Entra ID のユーザーに適用されると、マネージド ドメインの同期されたユーザーの *UserAccountControl* の値に *DONT\_EXPIRE\_PASSWORD* が適用されます。

ユーザーが Microsoft Entra でパスワードをリセットすると、*forceChangePasswordNextSignIn=True* 属性が適用されます。 マネージド ドメインは、この属性を Microsoft Entra ID から同期します。 マネージド ドメインで、Microsoft Entra ID から同期されたユーザーに *forceChangePasswordNextSignIn* が設定されていることが検出されると、マネージド ドメインの *pwdLastSet* 属性は *0* に設定され、これによって、現在設定されているパスワードが無効になります。

#### Microsoft Entra Domain Services では、AD アカウントのロックアウト保護は提供されますか?

はい。 管理対象ドメインに、2 分以内に無効なパスワードの試行が5回行われると、ユーザーのアカウントは、30 分ロックアウトされます。 30 分後、ユーザー アカウントは、自動的にロック解除されます。 マネージド ドメインで無効なパスワードが試されても、Microsoft Entra ID のユーザー アカウントはロックアウトされません。 ユーザー アカウントは、Microsoft Entra Domain Services のマネージド ドメイン内でのみロックアウトされます。 詳細については、「[マネージド ドメインに関するパスワードとアカウントのロックアウト ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/password-policy)」を参照してください。

#### Microsoft Entra Domain Services 内で分散ファイル システムとレプリケーションを構成できますか?

不正解です。 Microsoft Entra Domain Services を使用する場合、分散ファイル システム (DFS) とレプリケーションは使用できません。

#### Windows の更新プログラムは Microsoft Entra Domain Services でどのように適用されますか?

マネージド ドメインのドメイン コントローラーにより、必須の Windows 更新プログラムが自動的に適用されます。 ユーザー側では何も構成したり、管理したりする必要がありません。 Windows 更新プログラムへの送信トラフィックをブロックするネットワーク セキュリティ グループ規則を作成しないようにしてください。 独自の VM がマネージド ドメインに参加している場合、OS やアプリケーションに必須の更新プログラムがある場合、それを構成したり、適用したりするのはユーザー側の責任となります。

#### 送信ネットワーク セキュリティ グループ (NSG) の AzureUpdateDelivery タグおよび AzureFrontDoor.FirstParty タグを削除する必要はありますか?

AzureUpdateDelivery タグと AzureFrontDoor.FirstParty タグが非推奨となり、Microsoft Entra Domain Services が WindowsUpdate を個別に管理するため、これらのタグは必要ありません。 NSG 調整は、非推奨のタグの有無にかかわらず不要です。

#### Microsoft Entra Domain Services はどこに顧客データを格納しますか?

Microsoft Entra Domain Services では、顧客データが保存されます。 既定で顧客データは、サービス インスタンスがデプロイされているリージョン内にとどまります。 お客様が他のリージョンにデータを保存するためには、レプリカ セットを使用できます。

#### マネージド ドメインの一部であるドメイン コントローラーで、パッチの適用はどのように実行されますか?

パッチは、利用可能になるとすぐにインストールされます (毎月の第 2 火曜日)。 これらは、火曜日から、利用可能になる週に段階的にインストールされます。

#### ドメイン コントローラーで名前が変更される理由

ドメイン コントローラーのメンテナンス中に、名前が変更される可能性があります。 この種類の変更に関する問題を回避するには、アプリケーションや他のドメイン リソースでハードコーディングされたドメイン コントローラーの名前を使用せず、ドメインの FQDN を使用することをお勧めします。 これにより、ドメイン コントローラーの名前が何であっても、名前の変更後に何も再構成する必要はありません。

#### マネージド ドメイン内の KRBTGT アカウントのパスワードは定期的にロールオーバーされますか? その場合、頻度はどれくらいですか?

マネージド ドメイン内の KRBTGT アカウントのパスワードは、7 日ごとにロールオーバーされます。

### 課金と可用性

#### Microsoft Entra Domain Services は有料サービスですか?

はい。 詳細については、 [価格に関するページ](https://azure.microsoft.com/pricing/details/active-directory-ds/)を参照してください。

#### サービスの無料試用版はありますか。

Microsoft Entra Domain Services は Azure の無料試用版に含まれています。 [1 か月間の無料試用版](https://azure.microsoft.com/pricing/free-trial/)にサインアップできます。

#### Microsoft Entra Domain Services のマネージド ドメインを一時停止できますか?

不正解です。 Microsoft Entra Domain Services のマネージド ドメインを有効にすると、マネージド ドメインを削除するまで、選択した仮想ネットワーク内でサービスを利用できます。 サービスを一時停止する方法はありません。 課金は、マネージド ドメインを削除するまで、1 時間ごとに続行されます。

#### DR イベント時に Microsoft Entra Domain Services を別のリージョンにフェールオーバーできますか?

はい。マネージド ドメインの地理的な回復性を実現するため、Domain Services をサポートする任意の Azure リージョン内のピアリングされた仮想ネットワークに対して、別の[レプリカ セット](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-replica-set)を作成できます。 レプリカ セットとマネージド ドメインとで、同じ名前空間および構成が共有されます。

#### Enterprise Mobility Suite (EMS) の一部として Microsoft Entra Domain Services を入手できますか? Microsoft Entra Domain Services を使用するには、Microsoft Entra ID P1 または P2 が必要ですか?

不正解です。 Microsoft Entra Domain Services は従量課金制の Azure サービスであり、EMS の一部ではありません。 Microsoft Entra Domain Services は、Microsoft Entra ID のすべてのエディション (Free および Premium) で使用できます。 使用量に応じて、時間単位で課金されます。

#### マネージド ドメインの下に子ドメインを作成できますか。

不正解です。 Microsoft Entra Domain Services の設計は単一ドメインの単一フォレストであり、子ドメインを作成することはできません。

#### サービスを利用できる Azure リージョンはどれですか。

Microsoft Entra Domain Services を利用できる Azure リージョンの一覧については、[リージョン別の Azure サービス](https://azure.microsoft.com/regions/#services/)に関するページをご覧ください。

### トラブルシューティング

Azure ADドメイン サービスを 構成するか、または管理する際に生じる、一般的な問題の解決策については、｢[トラブルシューティングガイド](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/troubleshoot)｣を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/feature-availability"} -->
## Azure Government での Microsoft Entra Domain Services 機能の可用性 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/feature-availability
- Service: entra-id / domain-services
- Article date: 2025-01-21
- Summary: Azure Government で使用できる Domain Services 機能について説明します。

次の表に、Azure Government での Microsoft Entra Domain Services 機能の可用性を示します。

| 特徴 | 可用性 |
| --- | --- |
| LDAPS の構成 | ✅ |
| 信頼を作成する | ✅ |
| レプリカ セットを作成する | ✅ |
| ユーザーとグループの同期を設定し、その範囲を構成する | ✅ |
| パスワード ハッシュ同期の構成 | ✅ |
| パスワードとアカウントのロックアウト ポリシーの構成 | ✅ |
| グループ ポリシーの管理 | ✅ |
| DNS の管理 | ✅ |
| 電子メール通知 | ✅ |
| Kerberos の制約付き委任を構成する | ✅ |
| 監査と Azure Monitor ワークブック テンプレート | ✅ |
| Windows VM のドメイン参加 | ✅ |
| Linux VM のドメイン参加 | ✅ |
| Microsoft Entra アプリケーション プロキシをデプロイする | ✅ |
| SharePoint のプロファイル同期を有効にする | ✅ |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/fleet-metrics"} -->
## Microsoft Entra Domain Services のフリート メトリックを確認する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/fleet-metrics
- Service: entra-id / domain-services
- Article date: 2024-12-02
- Summary: Microsoft Entra Domain Services マネージド ドメインのフリート メトリックを確認する方法について説明します。

管理者は、Azure Monitor メトリックを使用して Microsoft Entra Domain Services のスコープを構成し、サービスのパフォーマンスに関する分析情報を得ることができます。 Domain Services のメトリックには、次の 2 つの場所からアクセスできます。

- Azure Monitor メトリックで、**[新しいグラフ]**&gt;**[スコープを選択]** をクリックし、Domain Services インスタンスを選択します。

    [Image: Screenshot of how to select Domain Services for fleet metrics.]
- Domain Services の **[監視]** で、**[メトリック]** をクリックします。

    [Image: Screenshot of how to select Domain Services as scope in Azure Monitor Metrics.]

    次のスクリーンショットは、合計プロセッサ時間と LDAP 検索の組み合わせメトリックを選択する方法を示しています。

    [Image: Screenshot of combined metrics in Azure Monitor Metrics.]

    Domain Services インスタンスのフリートのメトリックを表示することもできます。

    [Image: Screenshot of how to select a Domain Services instance as the scope for fleet metrics.]

    次のスクリーンショットは、ロール インスタンス別の合計プロセッサ時間、DNS クエリ、LDAP 検索の合計メトリックを示しています。

    [Image: Screenshot of combined metrics for a Domain Services instance.]

### メトリックの定義と説明

メトリックを選択すると、データ収集の詳細を確認できます。

[Image: Screenshot of fleet metric descriptions.]

次の表では、Domain Services で使用できるメトリックについて説明します。

| メトリック | 説明 |
| --- | --- |
| DNS - 受信したクエリの総数/秒 | DNS サーバーが 1 秒間に受信したクエリの平均数。 これは、ドメイン コントローラーからのパフォーマンス カウンター データによるものであり、ロール インスタンスによってフィルター処理または分割できます。 |
| 送信された応答の総数/秒 | DNS サーバーが 1 秒間に送信した応答の平均数。 これは、ドメイン コントローラーからのパフォーマンス カウンター データによるものであり、ロール インスタンスによってフィルター処理または分割できます。 |
| NTDS - 成功した LDAP バインド/秒 | NTDS オブジェクトに対して成功した LDAP バインドの 1 秒あたりの数。 これは、ドメイン コントローラーからのパフォーマンス カウンター データによるものであり、ロール インスタンスによってフィルター処理または分割できます。 |
| % Committed Bytes In Use | Memory\\Committed Bytes の Memory\\Commit Limit に対する割合です。 コミット メモリとは、ディスクに書く込む必要がある場合にページング ファイルに領域が予約されている使用中の物理メモリを指します。 Commit Limit はページング ファイルのサイズにより決定されます。 ページング ファイルが拡張されると、Commit Limit も増え、割合は低くなります。 このカウンターは、平均値ではなく現在の値のみをパーセントで表示します。 これは、ドメイン コントローラーからのパフォーマンス カウンター データによるものであり、ロール インスタンスによってフィルター処理または分割できます。 |
| 合計プロセッサ時間 | プロセッサが非アイドルのスレッドを実行するための経過時間の割合です。 プロセッサがアイドル状態のスレッドの実行に費した時間の割合を測定し、その値を 100% から減算することによって計算されます。 (各プロセッサには、実行するスレッドが他にない場合にサイクルを消費するアイドル スレッドがあります)。 このカウンターはプロセッサの処理状況のプライマリ インジケーターで、サンプリング間隔で監視されたビジー時間の平均割合をパーセントで表示します。 プロセッサがアイドル状態であるかどうかのアカウンティング計算は、システム クロックの内部サンプリング間隔 (10 ミリ秒) で実行されます。 現在の高速プロセッサでは、プロセッサがシステム クロックのサンプリング間隔の間でスレッドの処理に多くの時間を費やしている可能性があるため、プロセッサ時間 (lsass) の割合は実際より低く見積もられる可能性があります。 ワークロード ベースのタイマー アプリケーションは、サンプルが取得された直後にタイマーが通知されるため、測定が不正確になる可能性が高いアプリケーションの 1 種です。 これは、ドメイン コントローラーからのパフォーマンス カウンター データによるものであり、ロール インスタンスによってフィルター処理または分割できます。 |
| Kerberos 認証 | クライアントがチケットを使用してこのコンピューターに対する認証を行う 1 秒あたりの回数。 これは、ドメイン コントローラーからのパフォーマンス カウンター データによるものであり、ロール インスタンスによってフィルター処理または分割できます。 |
| NTLM 認証 | このドメイン コントローラーの Active Directory またはこのメンバー サーバー上のローカル アカウントに対して処理された NTLM 認証の 1 秒あたりの数。 これは、ドメイン コントローラーからのパフォーマンス カウンター データによるものであり、ロール インスタンスによってフィルター処理または分割できます。 |
| プロセッサ時間の割合 (DNS) | すべての DSN プロセス スレッドで命令を実行するためにプロセッサを使用した経過時間の割合。 命令はコンピューターでの実行の基本単位、スレッドは命令を実行するオブジェクト、プロセスはプログラムの実行時に作成されるオブジェクトです。 この数には、ハードウェア割り込みとトラップ状態に対処するために実行されたコードが含まれています。 これは、ドメイン コントローラーからのパフォーマンス カウンター データによるものであり、ロール インスタンスによってフィルター処理または分割できます。 |
| プロセッサ時間 (lsass) の割合 | すべての Isass プロセス スレッドで命令を実行するためにプロセッサを使用した経過時間の割合。 命令はコンピューターでの実行の基本単位、スレッドは命令を実行するオブジェクト、プロセスはプログラムの実行時に作成されるオブジェクトです。 この数には、ハードウェア割り込みとトラップ状態に対処するために実行されたコードが含まれています。 これは、ドメイン コントローラーからのパフォーマンス カウンター データによるものであり、ロール インスタンスによってフィルター処理または分割できます。 |
| NTDS - LDAP 検索数/秒 | NTDS オブジェクトの 1 秒あたりの平均検索数。 これは、ドメイン コントローラーからのパフォーマンス カウンター データによるものであり、ロール インスタンスによってフィルター処理または分割できます。 |

### Azure Monitor アラート

Domain Services のメトリック アラートを構成して、潜在的な問題について通知を受け取ることができます。 メトリック アラートは、Azure Monitor のアラートの 1 つの種類です。 他の種類のアラートの詳細については、「[Azure Monitor アラートとは](https://learn.microsoft.com/ja-jp/azure/azure-monitor/alerts/alerts-overview)」を参照してください。

Azure Monitor アラートを表示して管理するには、ユーザーに [Azure Monitor ロール](https://learn.microsoft.com/ja-jp/azure/azure-monitor/roles-permissions-security)を割り当てる必要があります。

Azure Monitor または Domain Services のメトリックで、**[新しいアラート]** をクリックし、スコープとして Domain Services インスタンスを構成します。 次に、使用可能なシグナルの一覧から測定するメトリックを選択します。

[Image: Screenshot of available alerts.]

次のスクリーンショットは、**合計プロセッサ時間**のしきい値を持つメトリック アラートを定義する方法を示しています。

[Image: Screenshot of defining a threshold.]

また、次のように電子メール、SMS、音声通話などのアラート通知を構成することもできます。

[Image: Screenshot of how to configure an alert notification.]

次のスクリーンショットは、**合計プロセッサ時間**に対してトリガーされるメトリック アラートを示しています。

[Image: Screenshot of alert trigger.]

この場合、次のようにアラートのアクティブ化後に電子メール通知が送信されます。

[Image: Screenshot of alert trigger details.]

次のようにアラートの非アクティブ化後に別の電子メール通知が送信されます。

[Image: Screenshot of alert resolution.]

### 複数のリソースの選択

投票によって複数のリソース選択を有効にして、リソースの種類間でデータを関連付けることができます。

[Image: Screenshot of feature upvote.]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/group-policy"} -->
## Microsoft Entra Domain Servicesのバックアップからグループ ポリシー オブジェクトを復元する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/group-policy
- Service: entra-id / domain-services
- Article date: 2025-10-07
- Summary: Microsoft Entra Domain Servicesマネージド ドメイン内のグループ ポリシー オブジェクトを復元する方法について説明します。

グループ ポリシー オブジェクト (GPO) は、Windows Active Directory ドメイン環境内でのコンピューター システムとユーザー アカウントの動作を定義するポリシー設定のコレクションです。 GPO は、Windows ネットワーク全体で一元化された構成管理、セキュリティの適用、管理制御の主要なメカニズムとして機能します。

### バックアップ機能の概要

グループ ポリシー バックアップ機能は、サービスを強化するパブリック プレビューの機能です。 Active Directory Domain Servicesのグループ ポリシー オブジェクト (GPO) のバックアップが自動的に作成および管理されます。 この機能は、重要なグループ ポリシーの定期的なバックアップを維持することで、ビジネス継続性とディザスター リカバリーを保証するのに役立ちます。

### ファイル システムの構造

#### バックアップの場所

- **ドメイン コントローラー**: `\\<PDC-Server>`
- **ネットワーク共有**: `GPOBackupsShare$` (非表示の共有)
- **完全なパス**: `\\<PDC-Server>\GPOBackupsShare$\GPO\Backups`

#### ディレクトリ構造

```
(omitted for brevity)\GPO\Backups

├── MMddyyyyHHmm\          # Timestamp folder (e.g., 092520251430)

│   ├── {GUID-1}\          # Individual GPO backup folder

│   ├── {GUID-2}\          # Individual GPO backup folder

│   └── ...

├── MMddyyyyHHmm\          # Previous backup

│   └── ...

└── ...
```

### 機能

##### ネットワーク共有の作成

次の特性を持つ暗号化された SMB (サーバー メッセージ ブロック) 共有を作成します。

- **共有名**: `GPOBackupsShare$` (非表示)
- **暗号化**: セキュリティが有効
- **アクセス許可**:
    - **フル アクセス**: ドメイン管理者
    - **読み取りアクセス**: AAD (Azure Active Directory) DC 管理者

### セキュリティに関する考慮事項

#### Permissions

- **フォルダーのアクセス許可**:

    - Domain Admins: 完全な管理権限
    - AAD DC 管理者: 読み取り権限
- **共有アクセス許可**:

    - ネットワーク アクセス用の暗号化された SMB 共有
    - 隠された共有 (`$` サフィックス) によるセキュリティを不透明化で強化する

#### Access Control

バックアップの場所とネットワーク共有は、承認された管理者のみがバックアップ データにアクセスできるように、適切なActive Directoryセキュリティ グループで構成されます。 ACL モデルは、グループ ポリシー管理コンソール (GPMC) で使用されるアクセス許可に合わせて調整され、既存の GPO 管理プラクティスとの整合性が維持されます。

### 使用例

このセクションでは、ドメインに参加しているコンピューターの管理者が、この機能によって作成されたグループ ポリシー オブジェクト (GPO) バックアップを検出、アクセス、復元する方法について説明します。 GUI (GPMC) ワークフローと PowerShell ワークフローの両方が提供されます。

#### [前提条件]

- 少なくとも 1 つの書き込み可能なドメイン コントローラー (理想的には PDC (プライマリ ドメイン コントローラー) エミュレーター) へのネットワーク接続があります。
- アカウントは、GPO の読み取り (AAD DC 管理者) または変更 (ドメイン管理者) 権限を持つグループのメンバーです。
- RSAT (リモート サーバー管理ツール) グループ ポリシー管理コンソール (GPMC) がインストールされている (GUI 復元用)。
- PowerShell `GroupPolicy` モジュールを使用できます (既定では RSAT/ドメイン コントローラーに付属)。

#### バックアップの確認

```powershell
# Check backup location
Get-ChildItem "\\<PDC-Server>\GPOBackupsShare$\GPO\Backups"
```

#### PDC エミュレーターの決定 (必要な場合)

コードは PDC で共有の解決と公開を試みますが、次のいずれかを使用して PDC エミュレーターを明示的に検出できます。

```powershell
Get-ADDomain | Select-Object PDCEmulator
```

または (レガシ/AD モジュールなし):

```
nltest /dsgetdc:<yourDomainFQDN> /pdc
```

UNC (汎用名前付け規則) パスの短いホスト名 (最初のドットの左側) を取得します。

#### バックアップ共有へのアクセス (ドメイン参加済みワークステーション)

1. Win + R キーを押し、UNC パスを入力します。

```powershell
"\\<PDC-Server>\GPOBackupsShare$"
```

1. (省略可能) ドライブ文字をマップします。

```powershell
New-PSDrive -Name GPOBK -PSProvider FileSystem -Root "\\<PDC-Server>\GPOBackupsShare$" -Persist
```

1. タイムスタンプ フォルダーを参照する ( `MMddyyyyHHmm`形式)。 各サブフォルダーには、バックアップされた GPO ごとに GUID という名前のフォルダーが含まれています。

#### バックアップ フォルダー レイアウトの要約

```powershell
\\<PDC-Server>\GPOBackupsShare$\<TimestampFolder>\{GPO-GUID}\
  +-- backup.xml          (metadata, if present)
  +-- GPO.tmf / Gpt.ini / Machine / User  (typical structural contents)
  +-- (Optional manifest files depending on API)
```

注

正確なファイル セットはプロバイダーの実装によって異なる場合がありますが、GPO ごとの GUID フォルダーがキー ID です。

#### 正しいバックアップの識別

GUID をわかりやすい GPO 名に関連付けることができます。

```powershell
Get-GPO -All | Where-Object Id -eq '{GUID-HERE}' | Select DisplayName, Id
```

または、GUID フォルダー間で名前で検索します ( `backup.xml` または `gpreport.xml` が存在する場合)。

```powershell
Get-ChildItem "\\<PDC-Server>\GPOBackupsShare$" -Directory -Recurse -Depth 2 | Where-Object { Test-Path (Join-Path $\_.FullName 'backup.xml') } |
  ForEach-Object {
    [xml]$meta = Get-Content (Join-Path $\_.FullName 'backup.xml') -ErrorAction SilentlyContinue

    if ($meta.BackupInformation.GPOName) {
      [pscustomobject]@{ Folder=$\_.FullName; GPOName=$meta.BackupInformation.GPOName; GPOId=$meta.BackupInformation.GPOID }
    }
  }
```

メタデータがない場合は、運用環境 (`Get-GPO -All`) の GPO GUID に依存します。

#### GUI を使用した GPO の復元 (GPMC)

1. "グループ ポリシー管理" (GPMC.msc) を起動します。
2. 左側のツリーで、 **グループ ポリシー オブジェクト** コンテナー (またはインプレース復元を実行する場合は個々の GPO) を右クリックします。
3. **バックアップを管理...**を選択します。
4. [ **参照** ] をクリックし、タイムスタンプ フォルダーのパスを選択します。

```powershell
\\<PDC-Server>\GPOBackupsShare$\<TimestampFolder>
```

1. 検出可能なバックアップが一覧に表示されます。 ターゲット GPO バックアップを選択します。
2. 次のいずれかを決定します。

    - **復元**: 既存の GPO (一致する GUID) をその場で上書きします。
    - **復元先...**: *別* の GPO に復元できます (既存のターゲットを選択します)。
    - **コピー** (使用可能な場合): バックアップから新しい GPO を作成します (GUID の変更。リンクは手動で再確立する必要があります)。
3. 操作を確認します。 結果ウィンドウで成功/失敗を確認します。
4. 必要に応じて、セキュリティ フィルター/WMI (Windows Management Instrumentation) フィルターを再リンクまたは検証します (以下を参照)。

##### 復元後の検証

- 実行: ターゲット ワークステーションで `gpresult /h report.html` して、ポリシー アプリケーションを確認します。
- GPMC で **GPO の状態** を使用して、ユーザーとコンピューターの両方の部分が有効になっていることを確認します。
- WMI フィルターの関連付けを検証します (WMI フィルターは、未加工のファイル レベルのバックアップ内に必ずしも埋め込まれていないので、再関連付けが必要になる場合があります)。

#### PowerShell を使用した GPO の復元

`GroupPolicy` モジュールでは、さまざまなシナリオで`Restore-GPO`、`Import-GPO`、および`New-GPO`を提供します。

##### 1. インプレース復元 (同じ GUID)

```powershell
$timestampFolder = '092520251430'              # Example

$pdc = (Get-ADDomain).PDCEmulator.Split('.')[0]

$backupRoot = "\\<PDC-Server>\GPOBackupsShare$\$timestampFolder"

# List available backups in that timestamp folder
Get-GPOBackup -Path $backupRoot | Format-Table DisplayName, Id, CreationTime

# Restore specific GPO by name (must already exist in domain)
Restore-GPO -Name 'My Application Baseline' -Path $backupRoot -Confirm:$false
```

##### 2. 元の GPO が削除されたときに復元する

元の GPO (GUID) がなくなった場合は、次の 2 つのオプションがあります。

1. GUID がわかっていて、それを保持したい場合にのみ、元の GUID で再作成します。

```powershell
  $backup = Get-GPOBackup -Path $backupRoot | Where-Object { $\_.DisplayName -eq 'My Application Baseline' }
  
  Restore-GPO -Guid $backup.Id -Path $backupRoot -CreateIfNeeded
```

1. オプション B – 新しい GPO を作成し、設定をインポートします。

```powershell
  $newGpo = New-GPO -Name 'My Application Baseline (Restored)'
  
  Import-GPO -TargetName $newGpo.DisplayName -BackupId $backup.Id -Path $backupRoot -CreateIfNeeded
```

##### 3. [GUID のみによるバックアップ] を選択する

```powershell
$gpoGuid = '{12345678-90AB-CDEF-1234-567890ABCDEF}'

Restore-GPO -Guid $gpoGuid -Path $backupRoot -Confirm:$false
```

##### 4. バックアップを新しい GPO にコピーする (フォレンジックの元のファイルを保持する)

```powershell
$backup = Get-GPOBackup -Path $backupRoot | Where-Object DisplayName -eq 'Legacy GPO'

$copy = New-GPO -Name "Recovered - $($backup.DisplayName)"

Import-GPO -BackupId $backup.Id -TargetName $copy.DisplayName -Path $backupRoot
```

##### 5. クロスドメイン/ラボインポート

タイムスタンプ フォルダー全体をターゲット ドメインの管理ワークステーションにコピーし (構造を保持)、次を実行します。

```powershell
Get-GPOBackup -Path 'C:\Temp\GPOBackups\092520251430' | ForEach-Object {

  $existing = Get-GPO -All | Where-Object Id -eq $\_.Id -ErrorAction SilentlyContinue

  if ($existing) {
    Restore-GPO -Guid $\_.Id -Path 'C:\Temp\GPOBackups\092520251430' -Confirm:$false
  } else {
    New-GPO -Name $\_.DisplayName | Out-Null

    Import-GPO -BackupId $\_.Id -TargetName $\_.DisplayName -Path 'C:\Temp\GPOBackups\092520251430'
  }
}
```

注

GPO 内のドメイン固有のセキュリティ プリンシパル (委任 ACL、基本設定のグループ SID (セキュリティ識別子) がクロスドメイン インポート後に確認されていることを確認します。

#### リンクされたオブジェクトと依存関係の処理

未加工の GPO コンテンツの復元は自動的 *には行われません* 。

- GPO を OU に再リンクします (リンクはインプレース リストア用に保持されます。新しい GPO には手動リンクが必要です)。
- WMI フィルターを再作成します (存在する必要があります。紛失した場合は再割り当てします)。
- ドメイン SID が異なる場合 (クロスドメイン シナリオで) セキュリティ フィルターを再構築します。

##### 再リンクの例

```powershell
New-GPLink -Name 'My Application Baseline (Restored)' -Target 'OU=Workstations,DC=contoso,DC=com' -Enforced:$false
```

##### WMI フィルターを再関連付け

```powershell
Set-GPWmiFilter -Guid '{RESTORED-GPO-GUID}' -WmiFilter (Get-GPWmiFilter -All | Where-Object Name -eq 'Win11Only')
```

#### 検証とレポート

設定を確認するには、HTML レポートを生成します。

```powershell
Get-GPO -Name 'My Application Baseline' | Get-GPOReport -ReportType Html -Path .\BaselineReport.html

Start-Process .\BaselineReport.html
```

クライアントにポリシーの結果セット (RSoP) の更新と検査を強制します。

```powershell
Invoke-GPUpdate -Computer 'CLIENT01' -RandomDelayInMinutes 0
```

#### ロールバック戦略

復元された GPO で問題が発生した場合:

1. 別の (以前の) タイムスタンプ フォルダーを使用し、そのバックアップで `Restore-GPO` を再実行します。
2. または、調査中に GPO を無効にします (ユーザーとコンピューターの構成の両方を [無効] に設定します)。
3. 簡単なロールバック パスを確保するには、少なくとも 2 つの最新のタイムスタンプ フォルダーを維持します。

#### 一般的な復元の落とし穴

| 問題点 | 原因 | 解決策 |
| --- | --- | --- |
| `Get-GPOBackup` 何も返しません | パスの深さが間違っています (タイムスタンプ フォルダーではなくルートを指す) | 特定のタイムスタンプフォルダーに `-Path` を指定し、上位レベルの親フォルダーは避けてください。 |
| 共有に対する`Access denied` | グループ メンバーシップが見つからないか、ファイアウォールがブロックされている | ドメイン管理者/AAD DC 管理者のメンバーシップを確認します。SMB 受信規則を確認する |
| インポート後に GPO リンクが見つからない | Import-GPO を使用して新しい GPO を作成しました。 | で手動でリンクを再作成する `New-GPLink` |
| WMI フィルターがありません | 含まれていない/再作成されていない | GPMC でフィルターを再作成し、再割り当てする |
| セキュリティ フィルター処理が無効 | SID の不一致 (クロスドメイン) | ターゲット ドメインから読み取られたグループ |

#### 手動クリーンアップ

```powershell
# The feature handles cleanup automatically, but for manual operations:
Get-ChildItem "\\<PDC-Server>\GPOBackupsShare$\GPO\Backups" | Where-Object { $\_.CreationTime -lt (Get-Date).AddDays(-7) } | Remove-Item -Recurse -Force
```

#### 最小限のエンド ツー エンド PowerShell の例

```powershell
# Variables

$pdc = (Get-ADDomain).PDCEmulator.Split('.')[0]

$latestTimestamp = Get-ChildItem "\\<PDC-Server>\GPOBackupsShare$" -Directory | Sort-Object Name -Descending | Select-Object -First 1 -ExpandProperty Name

$backupPath = "\\<PDC-Server>\GPOBackupsShare$\$latestTimestamp"

$gpoName = 'Baseline Workstation Policy'

# Inspect backups
Get-GPOBackup -Path $backupPath | Where-Object DisplayName -eq $gpoName

# Restore (in-place)
Restore-GPO -Name $gpoName -Path $backupPath -Confirm:$false

# Report
Get-GPO -Name $gpoName | Get-GPOReport -ReportType Html -Path .\Restored.html

Start-Process .\Restored.html
```

これらの手順を使用すると、管理者は、定期的な復旧、ラボへの移行、緊急ロールバックのいずれを実行する場合でも、GPO バックアップを確実に識別、取得、復元できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/how-to-data-retrieval"} -->
## Microsoft Entra Domain Services からデータを取得するための手順 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/how-to-data-retrieval
- Service: entra-id / domain-services
- Article date: 2025-02-05
- Summary: Microsoft Entra Domain Services からデータを取得する方法について説明します。

このドキュメントでは、Microsoft Entra Domain Services からデータを取得する方法について説明します。

注

この記事は、デバイスまたはサービスから個人データを削除する手順について説明しており、GDPR の下で義務を果たすために使用できます。 GDPR に関する一般的な情報については、 [Microsoft セキュリティ センターの GDPR セクション](https://www.microsoft.com/trust-center/privacy/gdpr-overview) と [Service Trust ポータルの GDPR セクションを参照](https://servicetrust.microsoft.com/ViewPage/GDPRGetStarted)してください。

### Microsoft Entra ID を使用して、ユーザー オブジェクトの作成、読み取り、更新、削除を行う

Microsoft Entra 管理センターで、または Graph PowerShell または Graph API を使用して、ユーザーを作成できます。 また、ユーザーの読み取り、更新、削除を行うこともできます。 次のセクションでは、Microsoft Entra 管理センターでこれらの操作を行う方法について説明します。

#### ユーザーの作成、読み取り、または更新

Microsoft Entra 管理センターを使用して、新しいユーザーを作成できます。 新しいユーザーを追加するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/azure/active-directory/roles/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動し、[**新しいユーザー**] を選択します。

    [Image: [ユーザー] を使用してユーザーを追加する - Microsoft Entra ID のすべてのユーザー]
3. [ **ユーザー** ] ページで、このユーザーの情報を入力します。

    - **名前**。 必須。 新しいユーザーの名前と苗字。 たとえば、 *Mary Parker* などです。
    - **ユーザー名**。 必須。 新しいユーザーのユーザー名です。 たとえば、`mary@contoso.com` のようにします。
    - **グループ**。 必要に応じて、1 つ以上の既存のグループにユーザーを追加できます。
    - **ディレクトリ ロール**: ユーザーに Microsoft Entra 管理アクセス許可が必要な場合は、Microsoft Entra ロールに追加できます。
    - **ジョブ情報**: ユーザーに関する詳細情報は、ここで追加できます。
4. [パスワード] ボックスに入力された自動生成された **パスワード** をコピーします。 このパスワードは初回のサインインのためにユーザーに渡す必要があります。
5. **作成**を選択します。

ユーザーが作成され、Microsoft Entra 組織に追加されます。

ユーザーを読み取ったり更新したりするには、 *Mary Parker* などのユーザーを検索して選択します。 任意のプロパティを変更し、[ **保存**] をクリックします。

#### ユーザーを削除する

ユーザーを削除するには、次の手順に従います。

1. Microsoft Entra テナントから削除するユーザーを検索して選択します。 たとえば、 *Mary Parker* などです。
2. [ **ユーザーの削除] を選択します**。

    [Image: ユーザー - [ユーザーの削除] が強調表示されているすべてのユーザー ページ]

ユーザーは削除され、[ **ユーザー - すべてのユーザー** ] ページに表示されなくなります。 ユーザーは、次の 30 日間、[ **削除されたユーザー** ] ページに表示され、その間に復元できます。

ユーザーが削除されると、そのユーザーによって使用されていたライセンスは、他のユーザーが使用できるようになります。

### RSAT ツールを使用して Microsoft Entra Domain Services マネージド ドメインに接続し、ユーザーを表示する

*AAD DC Administrators* グループのメンバーであるユーザー アカウントを使用して、管理ワークステーションにサインインします。 次の手順では、 [リモート サーバー管理ツール (RSAT)](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-management-vm#install-active-directory-administrative-tools) をインストールする必要があります。

1. **[スタート**] メニューの **[Windows 管理ツール**] を選択します。 Active Directory 管理ツールが一覧表示されます。

    [Image: サーバーにインストールされている管理ツールの一覧]
2. **Active Directory 管理センターを選択します**。
3. マネージド ドメインを探索するには、左側のウィンドウでドメイン名 ( *aaddscontoso* など) を選択します。 *AADDC コンピューター*と *AADDC ユーザー*という名前の 2 つのコンテナーが一覧の一番上にあります。

    [Image: マネージド ドメインの使用可能なコンテナー部分を一覧表示する]
4. マネージド ドメインに属しているユーザーとグループを表示するには、 **AADDC Users** コンテナーを選択します。 このコンテナーには、Microsoft Entra テナントのユーザー アカウントとグループが一覧表示されます。

    次の出力例では、 *Contoso Admin* という名前のユーザー アカウントと *AAD DC 管理者* のグループがこのコンテナーに表示されます。

    [Image: Active Directory 管理センターで Microsoft Entra Domain Services ドメイン ユーザーの一覧を表示する]
5. マネージド ドメインに参加しているコンピューターを表示するには、 **AADDC Computers** コンテナーを選択します。 *myVM* などの現在の仮想マシンのエントリが一覧表示されます。 マネージド ドメインに参加しているすべてのデバイスのコンピューター アカウントは、この *AADDC Computers* コンテナーに格納されます。

管理ツールの一部としてインストールされている *Windows PowerShell 用 Active Directory モジュール*を使用して、マネージド ドメインの一般的なアクションを管理することもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/join-coreos-linux-vm"} -->
## CoreOS VM を Microsoft Entra Domain Services に参加させる - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-coreos-linux-vm
- Service: entra-id / domain-services
- Article date: 2025-02-05
- Summary: CoreOS 仮想マシンを構成して Microsoft Entra Domain Services マネージド ドメインに参加させる方法について説明します。

ユーザーが 1 つの資格情報セットを使用して Azure の仮想マシン (VM) にサインインできるようにするには、VM を Microsoft Entra Domain Services マネージド ドメインに参加させることができます。 VM を Domain Services マネージド ドメインに参加させると、ドメインのユーザー アカウントと資格情報を使用して、サーバーのサインインと管理を行うことができます。 マネージド ドメインからのグループ メンバーシップも適用され、VM 上のファイルまたはサービスへのアクセスを制御できます。

この記事では、CoreOS VM をマネージド ドメインに参加させる方法について説明します。

### [前提条件]

このチュートリアルを完了するには、以下のリソースと特権が必要です。

- 有効な Azure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付け](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)ます。
- Microsoft Entra テナント内の Microsoft Entra Domain Services マネージド ドメインが有効化および構成されています。
    - 必要であれば、1 つ目のチュートリアルで [Microsoft Entra Domain Services のマネージド ドメインを作成して構成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)します。
- マネージド ドメインの一部であるユーザー アカウント。
- 名前の切り詰めによって生じる Active Directory での競合を防ぐため、最大 15 文字の一意の Linux VM 名。

### CoreOS Linux VM を作成して接続する

Azure に既存の CoreOS Linux VM がある場合は、SSH を使用して接続し、次の手順に進んで VM の構成を開始します。

CoreOS Linux VM を作成する必要がある場合、またはこの記事で使用するテスト VM を作成する場合は、次のいずれかの方法を使用できます。

- [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/quick-create-portal)
- [Azure CLI](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/quick-create-cli)
- [Azure PowerShell](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/quick-create-powershell)

VM を作成するときは、仮想ネットワークの設定に注意して、VM がマネージド ドメインと通信できることを確認します。

- Microsoft Entra Domain Services を有効にしたのと同じ仮想ネットワークまたはピアリングされた仮想ネットワークに VM をデプロイします。
- MICROSOFT Entra Domain Services マネージド ドメインとは異なるサブネットに VM をデプロイします。

VM がデプロイされたら、手順に従って SSH を使用して VM に接続します。

### hosts ファイルを構成する

VM ホスト名がマネージド ドメイン用に正しく構成されていることを確認するには、*/etc/hosts* ファイルを編集し、ホスト名を設定します。

```console
sudo vi /etc/hosts
```

*hosts* ファイルで、*localhost* アドレスを更新します。 次に例を示します。

- *aaddscontoso.com* は、マネージド ドメインの DNS ドメイン名です。
- *coreos* は、マネージド ドメインに参加している CoreOS VM のホスト名です。

これらの名前を独自の値に更新してください。

```console
127.0.0.1 coreos coreos.aaddscontoso.com
```

完了したら、エディターの  コマンドを使用して `:wq` ファイルを保存して終了します。

### SSSD サービスを構成する

*/etc/sssd/sssd.conf* SSSD 構成を更新します。

```console
sudo vi /etc/sssd/sssd.conf
```

次のパラメーターに独自のマネージド ドメイン名を指定します。

- *domains* (すべて大文字)
- *[domain/AADDSCONTOSO]* (AADDSCONTOSO はすべて大文字)
- *ldap\_uri*
- *ldap\_search\_base*
- *krb5\_server*
- *krb5\_realm* (すべて大文字)

```console
[sssd]
config_file_version = 2
services = nss, pam
domains = AADDSCONTOSO.COM

[domain/AADDSCONTOSO]
id_provider = ad
auth_provider = ad
chpass_provider = ad

ldap_uri = ldap://aaddscontoso.com
ldap_search_base = dc=aaddscontoso,dc=com
ldap_schema = rfc2307bis
ldap_sasl_mech = GSSAPI
ldap_user_object_class = user
ldap_group_object_class = group
ldap_user_home_directory = unixHomeDirectory
ldap_user_principal = userPrincipalName
ldap_account_expire_policy = ad
ldap_force_upper_case_realm = true
fallback_homedir = /home/%d/%u

krb5_server = aaddscontoso.com
krb5_realm = AADDSCONTOSO.COM
```

### VM をマネージド ドメインに参加させる

SSSD 構成ファイルが更新された状態で、仮想マシンをマネージド ドメインに参加させます。

1. まず、 `adcli info` コマンドを使用して、マネージド ドメインに関する情報を確認します。 次の例では、ドメイン *AADDSCONTOSO.COM* の情報を取得します。 独自のマネージド ドメイン名を、すべて大文字で指定します。

    ```console
    sudo adcli info AADDSCONTOSO.COM
    ```

    `adcli info` コマンドでマネージド ドメインが見つからない場合は、次のトラブルシューティング手順を確認してください。

    - ドメインが VM から到達可能であることを確認します。 肯定的な応答が返されるかどうかを確認するには、`ping aaddscontoso.com` を試してください。
    - VM が、マネージド ドメインが使用可能な同じ仮想ネットワークまたはピアリングされた仮想ネットワークにデプロイされていることを確認します。
    - 仮想ネットワークの DNS サーバー設定が、マネージド ドメインのドメイン コントローラーを指すよう更新されていることを確認します。
2. 次に、 `adcli join` コマンドを使用して VM をマネージド ドメインに参加させます。 マネージド ドメインの一部であるユーザーを指定します。 必要に応じて、[Microsoft Entra ID のグループにユーザー アカウントを追加します](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-to-manage-groups)。

    ここでも、マネージド ドメイン名はすべて大文字で入力する必要があります。 次の例では、`contosoadmin@aaddscontoso.com` という名前のアカウントを使用して Kerberos を初期化します。 マネージド ドメインの一部である独自のユーザー アカウントを入力します。

    ```console
    sudo adcli join -D AADDSCONTOSO.COM -U contosoadmin@AADDSCONTOSO.COM -K /etc/krb5.keytab -H coreos.aaddscontoso.com -N coreos
    ```

    vm がマネージド ドメインに正常に参加している場合、 `adcli join` コマンドは情報を返しません。
3. ドメイン参加構成を適用するには、SSSD サービスを開始します。

    ```console
    sudo systemctl start sssd.service
    ```

### ドメイン アカウントを使用して VM にサインインする

VM がマネージド ドメインに正常に参加していることを確認するには、ドメイン ユーザー アカウントを使用して新しい SSH 接続を開始します。 ホーム ディレクトリが作成され、ドメインのグループ メンバーシップが適用されていることを確認します。

1. コンソールから新しい SSH 接続を作成します。 `ssh -l`などの`contosoadmin@aaddscontoso.com` コマンドを使用してマネージド ドメインに属するドメイン アカウントを使用し、VM のアドレス (*coreos.aaddscontoso.com* など) を入力します。 Azure Cloud Shell を使用する場合は、内部 DNS 名ではなく、VM のパブリック IP アドレスを使用します。

    ```console
    ssh -l contosoadmin@AADDSCONTOSO.com coreos.aaddscontoso.com
    ```
2. 次に、グループ メンバーシップが正しく解決されていることを確認します。

    ```console
    id
    ```

    マネージド ドメインのグループ メンバーシップが表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/join-rhel-linux-vm"} -->
## RHEL VM を Microsoft Entra Domain Services に参加させる - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-rhel-linux-vm
- Service: entra-id / domain-services
- Article date: 2025-02-13
- Summary: Red Hat Enterprise Linux 仮想マシンを構成して Microsoft Entra Domain Services マネージド ドメインに参加させる方法について説明します。

ユーザーが 1 つの資格情報セットを使用して Azure の仮想マシン (VM) にサインインできるようにするには、VM を Microsoft Entra Domain Services マネージド ドメインに参加させることができます。 VM を Domain Services マネージド ドメインに参加させると、ドメインのユーザー アカウントと資格情報を使用して、サーバーのサインインと管理を行うことができます。 マネージド ドメインからのグループ メンバーシップも適用され、VM 上のファイルまたはサービスへのアクセスを制御できます。

この記事では、Red Hat Enterprise Linux (RHEL) VM をマネージド ドメインに参加させる方法について説明します。

### 前提 条件

このチュートリアルを完了するには、次のリソースと特権が必要です。

- アクティブな Azure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、 [アカウントを作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- サブスクリプションに関連付けられている Microsoft Entra テナント。オンプレミスディレクトリまたはクラウド専用ディレクトリと同期されます。
    - 必要に応じて、 [Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization) するか、 [Azure サブスクリプションをアカウントに関連付けます](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)。
- Microsoft Entra Domain Services の管理ドメインが有効化され、Microsoft Entra テナントで構成済みです。
    - 必要に応じて、最初のチュートリアルでは [Microsoft Entra Domain Services マネージド ドメインを作成して構成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)。
- マネージド ドメインの一部であるユーザー アカウント。
- Active Directory で競合を引き起こす可能性のある名前の切り捨てを回避するために、最大 15 文字の一意の Linux VM 名。

### RHEL Linux VM を作成して接続する

Azure に既存の RHEL Linux VM がある場合は、SSH を使用して接続し、次の手順に進んで VM の構成を開始します。

RHEL Linux VM を作成する必要がある場合、またはこの記事で使用するテスト VM を作成する場合は、次のいずれかの方法を使用できます。

- [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/quick-create-portal)
- [Azure CLI](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/quick-create-cli)
- [Azure PowerShell](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/quick-create-powershell)

VM を作成するときは、仮想ネットワークの設定に注意して、VM がマネージド ドメインと通信できることを確認します。

- Microsoft Entra Domain Services を有効にしたのと同じ仮想ネットワークまたはピアリングされた仮想ネットワークに VM をデプロイします。
- MICROSOFT Entra Domain Services マネージド ドメインとは異なるサブネットに VM をデプロイします。

VM がデプロイされたら、手順に従って SSH を使用して VM に接続します。

### hosts ファイルを構成する

VM ホスト名がマネージド ドメイン用に正しく構成されていることを確認するには、 */etc/hosts* ファイルを編集し、ホスト名を設定します。

```bash
sudo vi /etc/hosts
```

*hosts* ファイルで、*localhost* アドレスを更新します。 次の例では、

- *aaddscontoso.com* は、マネージド ドメインの DNS ドメイン名です。
- *rhel* は、マネージド ドメインに参加している RHEL VM のホスト名です。

次の名前をあなた自身の値で更新します。

```config
127.0.0.1 rhel rhel.aaddscontoso.com
```

完了したら、エディターの  コマンドを使用して `:wq` ファイルを保存して終了します。

## [レル 6](#tab/rhel)
大事な

Red Hat Enterprise Linux 6.X と Oracle Linux 6.x は既に EOL であることを考慮してください。 RHEL 6.10 には、[2024 年 6 月 6 日に終了](https://www.redhat.com/en/resources/els-datasheet)した [ELS サポート](https://access.redhat.com/product-life-cycles/?product=Red%20Hat%20Enterprise%20Linux,OpenShift%20Container%20Platform%204)が用意されています。

### 必要なパッケージをインストールする

VM をマネージド ドメインに参加させるために、いくつかの追加パッケージが必要です。 これらのパッケージをインストールして構成するには、`yum`を使用してドメイン参加ツールを更新してインストールします。

```bash
sudo yum install adcli sssd authconfig krb5-workstation
```

### VM をマネージド ドメインに参加させる

必要なパッケージが VM にインストールされたら、VM をマネージド ドメインに参加させます。

1. `adcli info` コマンドを使用してマネージド ドメインを検出します。 次の例では、領域 *AADDSCONTOSO.COM* を検出します。 大文字で独自の管理されたドメイン名を指定してください。

    ```bash
    sudo adcli info aaddscontoso.com
    ```

    `adcli info` コマンドでマネージド ドメインが見つからない場合は、次のトラブルシューティング手順を確認してください。

    - ドメインが VM から到達可能であることを確認します。 肯定的な応答が返されるかどうかを確認するには、`ping aaddscontoso.com` を試してください。
    - VM が、マネージド ドメインが使用可能な同じ仮想ネットワークまたはピアリングされた仮想ネットワークにデプロイされていることを確認します。
    - 仮想ネットワークの DNS サーバー設定が、マネージド ドメインのドメイン コントローラーを指すよう更新されていることを確認します。
2. 最初に、 `adcli join` コマンドを使用してドメインに参加します。 このコマンドでは、マシンを認証するための keytab も作成されます。 マネージド ドメインの一部であるユーザー アカウントを使用します。

    ```bash
    sudo adcli join aaddscontoso.com -U contosoadmin
    ```
3. 次に、`/ect/krb5.conf` を構成し、`/etc/sssd/sssd.conf` Active Directory ドメインを使用する `aaddscontoso.com` ファイルを作成します。 `AADDSCONTOSO.COM` が独自のドメイン名に置き換えられたことを確認します。

    エディターで `/etc/krb5.conf` ファイルを開きます。

    ```bash
    sudo vi /etc/krb5.conf
    ```

    次のサンプルに一致するように `krb5.conf` ファイルを更新します。

    ```config
    [logging]
     default = FILE:/var/log/krb5libs.log
     kdc = FILE:/var/log/krb5kdc.log
     admin_server = FILE:/var/log/kadmind.log
    
    [libdefaults]
     default_realm = AADDSCONTOSO.COM
     dns_lookup_realm = true
     dns_lookup_kdc = true
     ticket_lifetime = 24h
     renew_lifetime = 7d
     forwardable = true
    
    [realms]
     AADDSCONTOSO.COM = {
     kdc = AADDSCONTOSO.COM
     admin_server = AADDSCONTOSO.COM
     }
    
    [domain_realm]
     .AADDSCONTOSO.COM = AADDSCONTOSO.COM
     AADDSCONTOSO.COM = AADDSCONTOSO.COM
    ```

    `/etc/sssd/sssd.conf` ファイルを作成します。

    ```bash
    sudo vi /etc/sssd/sssd.conf
    ```

    次のサンプルに一致するように `sssd.conf` ファイルを更新します。

    ```config
    [sssd]
     services = nss, pam, ssh, autofs
     config_file_version = 2
     domains = AADDSCONTOSO.COM
    
    [domain/AADDSCONTOSO.COM]
    
     id_provider = ad
    ```
4. `/etc/sssd/sssd.conf` アクセス許可が 600 であり、ルート ユーザーによって所有されていることを確認します。

    ```bash
    sudo chmod 600 /etc/sssd/sssd.conf
    sudo chown root:root /etc/sssd/sssd.conf
    ```
5. `authconfig` を使用して、AD Linux 統合について VM に指示します。

    ```bash
    sudo authconfig --enablesssd --enablesssd auth --update
    ```
6. sssd サービスを開始して有効にします。

    ```bash
    sudo service sssd start
    sudo chkconfig sssd on
    ```

VM がドメイン参加プロセスを正常に完了できない場合は、VM のネットワーク セキュリティ グループが、TCP + UDP ポート 464 でマネージド ドメインの仮想ネットワーク サブネットへの送信 Kerberos トラフィックを許可していることを確認します。

次に、`getent` を使用してユーザー AD 情報に対してクエリを実行できるかどうかを確認します

```bash
sudo getent passwd contosoadmin
```

### SSH のパスワード認証を許可する

既定では、ユーザーは SSH 公開キーベースの認証を使用してのみ VM にサインインできます。 パスワードベースの認証が失敗します。 VM をマネージド ドメインに参加させる場合、これらのドメイン アカウントではパスワードベースの認証を使用する必要があります。 次のように、パスワードベースの認証を許可するように SSH 構成を更新します。

1. エディターで *sshd\_conf* ファイルを開きます。

    ```bash
    sudo vi /etc/ssh/sshd_config
    ```
2. *PasswordAuthentication* の行を *yes* に更新します。

    ```config
    PasswordAuthentication yes
    ```

    完了したら、エディターの  コマンドを使用して、sshd\_conf ファイルを保存して終了します。
3. 変更を適用し、ユーザーがパスワードを使用してサインインできるようにするには、RHEL ディストリビューション バージョンの SSH サービスを再起動します。

    ```bash
    sudo service sshd restart
    ```

## [RHEL 7/RHEL 8/RHEL 9](#tab/rhel7)
### 必要なパッケージをインストールする

VM をマネージド ドメインに参加させるために、いくつかの追加パッケージが必要です。 これらのパッケージをインストールして構成するには、`yum`を使用してドメイン参加ツールを更新してインストールします。

```bash
sudo yum install realmd sssd krb5-workstation krb5-libs oddjob oddjob-mkhomedir samba-common-tools
```

### VM をマネージド ドメインに参加させる

必要なパッケージが VM にインストールされたら、VM をマネージド ドメインに参加させます。 ここでも、RHEL ディストリビューション のバージョンに適した手順を使用します。

1. `realm discover` コマンドを使用してマネージド ドメインを検出します。 次の例では、領域 *AADDSCONTOSO.COM* を検出します。 大文字で独自の管理されたドメイン名を指定してください。

    ```bash
    sudo realm discover AADDSCONTOSO.COM
    ```

    `realm discover` コマンドでマネージド ドメインが見つからない場合は、次のトラブルシューティング手順を確認してください。

    - ドメインが VM から到達可能であることを確認します。 肯定的な応答が返されるかどうかを確認するには、`ping aaddscontoso.com` を試してください。
    - VM が、マネージド ドメインが使用可能な同じ仮想ネットワークまたはピアリングされた仮想ネットワークにデプロイされていることを確認します。
    - 仮想ネットワークの DNS サーバー設定が、マネージド ドメインのドメイン コントローラーを指すよう更新されていることを確認します。
2. 次に、`kinit` コマンドを使用して Kerberos を初期化します。 マネージド ドメインの一部であるユーザーを指定します。 必要に応じて、 [Microsoft Entra ID のグループにユーザー アカウントを追加します](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-to-manage-groups)。

    ここでも、マネージド ドメイン名はすべて大文字で入力する必要があります。 次の例では、`contosoadmin@aaddscontoso.com` という名前のアカウントを使用して Kerberos を初期化します。 マネージド ドメインの一部である独自のユーザー アカウントを入力します。

    ```bash
    sudo kinit contosoadmin@AADDSCONTOSO.COM
    ```
3. 最後に、`realm join` コマンドを使用して、VM をマネージド ドメインに参加させます。 `kinit`など、前の `contosoadmin@AADDSCONTOSO.COM` コマンドで指定したマネージド ドメインの一部であるのと同じユーザー アカウントを使用します。

    ```bash
    sudo realm join --verbose AADDSCONTOSO.COM -U 'contosoadmin@AADDSCONTOSO.COM'
    ```

VM をマネージド ドメインに参加させるのにしばらく時間がかかります。 次の出力例は、マネージド ドメインに正常に参加した VM を示しています。

```output
Successfully enrolled machine in realm
```

### SSH のパスワード認証を許可する

既定では、ユーザーは SSH 公開キーベースの認証を使用してのみ VM にサインインできます。 パスワードベースの認証が失敗します。 VM をマネージド ドメインに参加させる場合、これらのドメイン アカウントではパスワードベースの認証を使用する必要があります。 次のように、パスワードベースの認証を許可するように SSH 構成を更新します。

1. エディターで *sshd\_conf* ファイルを開きます。

    ```bash
    sudo vi /etc/ssh/sshd_config
    ```
2. *PasswordAuthentication* の行を *yes* に更新します。

    ```bash
    PasswordAuthentication yes
    ```

    完了したら、エディターの  コマンドを使用して、sshd\_conf ファイルを保存して終了します。
3. 変更を適用し、ユーザーがパスワードを使用してサインインできるようにするには、SSH サービスを再起動します。

    ```bash
    sudo systemctl restart sshd
    ```

---

### "AAD DC Administrators" グループに sudo 特権を付与する

RHEL VM で *AAD DC Administrators* グループの管理者特権のメンバーに付与するには、 */etc/sudoers* にエントリを追加します。 追加すると、 *AAD DC Administrators* グループのメンバーは、RHEL VM で `sudo` コマンドを使用できます。

1. 編集のために *sudoers* ファイルを開きます。

    ```bash
    sudo visudo
    ```
2. */etc/sudoers* ファイルの末尾に次のエントリを追加します。 *AAD DC Administrators* グループには名前に空白が含まれているため、グループ名に円記号エスケープ文字を含めます。 aaddscontoso.com *など、*独自のドメイン名を追加します。

    ```config
    # Add 'AAD DC Administrators' group members as admins.
    %AAD\ DC\ Administrators@aaddscontoso.com ALL=(ALL) NOPASSWD:ALL
    ```

    完了したら、エディターの `:wq` コマンドを使用してエディターを保存して終了します。

### ドメイン アカウントを使用して VM にサインインする

VM がマネージド ドメインに正常に参加したことを確認するには、ドメイン ユーザー アカウントを使用して新しい SSH 接続を開始します。 ホーム ディレクトリが作成され、ドメインのグループ メンバーシップが適用されていることを確認します。

1. コンソールから新しい SSH 接続を作成します。 `ssh -l`などの `contosoadmin@aaddscontoso.com` コマンドを使用してマネージド ドメインに属するドメイン アカウントを使用し、VM のアドレス (*rhel.aaddscontoso.com* など) を入力します。 Azure Cloud Shell を使用する場合は、内部 DNS 名ではなく、VM のパブリック IP アドレスを使用します。

    ```bash
    ssh -l contosoadmin@AADDSCONTOSO.com rhel.aaddscontoso.com
    ```
2. VM に正常に接続したら、ホーム ディレクトリが正しく初期化されたことを確認します。

    ```bash
    pwd
    ```

    ユーザー アカウントと一致する独自のディレクトリを持つ */home* ディレクトリに存在する必要があります。
3. 次に、グループ メンバーシップが正しく解決されていることを確認します。

    ```bash
    id
    ```

    マネージド ドメインのグループ メンバーシップが表示されます。
4. *AAD DC Administrators* グループのメンバーとして VM にサインインした場合は、`sudo` コマンドを正しく使用できることを確認します。

    ```bash
    sudo yum update
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/join-suse-linux-vm"} -->
## SLE VM を Microsoft Entra Domain Services に参加させる - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-suse-linux-vm
- Service: entra-id / domain-services
- Article date: 2025-03-18
- Summary: SUSE Linux Enterprise 仮想マシンを構成して Microsoft Entra Domain Services のマネージド ドメインに参加させる方法について学習します。

ユーザーが 1 セットの資格情報を使用して Azure の仮想マシン (VM) にサインインできるようにするには、Microsoft Entra Domain Services のマネージド ドメインに VM を参加させます。 VM を Domain Services のマネージド ドメインに参加させると、ドメインのユーザー アカウントと資格情報を使用して、サーバーにサインインして管理することができます。 マネージド ドメインのグループ メンバーシップも適用され、VM 上のファイルまたはサービスへのアクセスを制御できるようになります。

この記事では、SUSE Linux Enterprise (SLE) VM をマネージド ドメインに参加させる方法について説明します。

### 前提条件

このチュートリアルを完了するには、以下のリソースと特権が必要です。

- 有効な Azure サブスクリプション
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付け](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)ます。
- Microsoft Entra テナント内の Microsoft Entra Domain Services マネージド ドメインが有効化および構成されています。
    - 必要であれば、1 つ目のチュートリアルで [Microsoft Entra Domain Services のマネージド ドメインを作成して構成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)します。
- マネージド ドメインの一部であるユーザー アカウント。
- 名前の切り詰めによって生じる Active Directory での競合を防ぐため、最大 15 文字の一意の Linux VM 名。

### SLE Linux VM を作成してそれに接続する

Azure に SLE Linux VM が既にある場合は、SSH を使用してそれに接続した後、次の手順に進んで VM の構成を開始します。

SLE Linux VM を作成する必要がある場合、またはこの記事で使用するためのテスト VM を作成する場合は、次のいずれかの方法を使用できます。

- [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/quick-create-portal)
- [Azure CLI](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/quick-create-cli)
- [Azure PowerShell](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/quick-create-powershell)

VM を作成するときは、VM がマネージド ドメインと通信できるように、仮想ネットワークの設定に注意してください。

- Microsoft Entra Domain Services を有効にしたのと同じ仮想ネットワーク、またはピアリングされた仮想ネットワークに、VM をデプロイします。
- VM を Microsoft Entra Domain Services のマネージド ドメインとは異なるサブネットにデプロイします。

VM をデプロイした後、SSH を使用して VM に接続する手順に従います。

### hosts ファイルを構成する

マネージド ドメインに対して VM ホスト名が正しく構成されていることを確認するには、 */etc/hosts* ファイルを編集して、ホスト名を設定します。

```bash
sudo vi /etc/hosts
```

*hosts* ファイルで、*localhost* アドレスを更新します。 次の例では

- *aaddscontoso.com* は、マネージド ドメインの DNS ドメイン名です。
- *linux-q2gr* は、マネージド ドメインに参加している SLE VM のホスト名です。

自分の値でこれらの名前を更新してください。

```config
127.0.0.1 linux-q2gr linux-q2gr.aaddscontoso.com
```

終わったら、*hosts* ファイルを保存して終了するために、エディターの`:wq` コマンドを使用します。

### SSSD を使用して VM をマネージド ドメインに参加させる

**SSSD** と YaST の *User Logon Management* モジュールを使用してマネージド ドメインに参加するには、次の手順を行います。

1. YaST の *User Logon Management*モジュールをインストールします。

    ```bash
    sudo zypper install yast2-auth-client
    ```
2. YaST を開きます。
3. 後で DNS 自動検出を正常に実行するには、マネージド ドメインの IP アドレス ("*Active Directory サーバー*") をクライアントのネーム サーバーとして構成します。

    YaST で、**[システム] &gt; [ネットワーク設定]** の順に選択します。
4. *ホスト名/DNS* タブを選択し、ネーム サーバー 1 *のテキスト ボックスにマネージド ドメインの 1 つ以上の IP アドレス*入力します。 これらの IP アドレスは、ご利用のマネージド ドメインの Microsoft Entra 管理センターで *[プロパティ]* ウィンドウに表示されます (例: *10.0.2.4*、*10.0.2.5*)。

    ご自分のマネージド ドメインの IP アドレスを追加し、 **[OK]** を選択します。
5. YaST のメイン ウィンドウで、 *[ネットワーク サービス]*&gt;*[User Logon Management]* の順に選択します。

    次のスクリーンショット例で示すように、このモジュールでは、コンピューターのさまざまなネットワーク プロパティと現在使用されている認証方法を示す概要が表示されます。

    [Image: YaST の [User Login Management] ウィンドウのスクリーンショット例]

    編集を開始するには、 **[設定の変更]** を選択します。

VM をマネージド ドメインに参加させるには、次の手順を行います。

1. ダイアログ ボックスで、 **[ドメインの追加]** を選択します。
2. 正しい "*ドメイン名*" (例: *aaddscontoso.com*) を指定し、データの識別と認証に使用するサービスを指定します。 両方について、 *[Microsoft Active Directory]* を選択します。

    *[ドメインを有効にする]* オプションが選択されていることを確認します。
3. 準備ができたら **[OK]** を選択します。
4. 次のダイアログで既定の設定を受け入れ、 **[OK]** を選択します。
5. VM では、必要に応じて追加のソフトウェアがインストールされ、マネージド ドメインが使用可能かどうかが確認されます。

    すべてが正しい場合、次の例のダイアログが表示されます。このダイアログは、VM がマネージド ドメインを検出したことを示しますが、あなたはまだ登録されていません（*まだ登録*）。

    [Image: YaST の Active Directory 登録ウィンドウのスクリーンショット例]
6. ダイアログで、マネージド ドメインに参加するユーザーの *[ユーザー名]* と "*パスワード*" を指定します。 必要に応じて、[Microsoft Entra ID のグループにユーザー アカウントを追加します](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-to-manage-groups)。

    現在のドメインが Samba に対して有効であることを確認するには、 *[Overwrite Samba configuration to work with this AD]\(この AD で動作するように Samba の構成を上書きする\)* をアクティブ化します。
7. 登録するには、 **[OK]** を選択します。
8. 正常に登録されたことを確認するメッセージが表示されます。 完了するには、 **[OK]** を選択します。

VM をマネージド ドメインに登録した後、次のスクリーンショット例で示すように、 *[ドメイン ユーザー ログオンの管理]* を使用してクライアントを構成します。

[Image: YaST の [ドメイン ユーザー ログオンの管理] ウィンドウのスクリーンショット]

1. マネージド ドメインによって提供されたデータを使用したサインインを許可するには、 *[ドメイン ユーザー ログオンを許可する]* をオンにします。
2. 必要に応じて、 *[ドメイン データ ソースを有効にする]* で、ご利用の環境に必要な追加のデータ ソースをオンにします。 これらのオプションとしては、**sudo**の使用を許可するユーザーや使用可能なネットワーク ドライブなどがあります。
3. マネージド ドメイン内のユーザーが VM でホーム ディレクトリを持つことを許可するには、 *[ホーム ディレクトリを作成する]* ボックスをオンにします。
4. サイド バーで、 **[サービス オプション]、[名前] スイッチ**、 *[拡張オプション]* の順に選択します。 そのウィンドウで、 *[fallback\_homedir]* または *[override\_homedir]* を選択し、 **[追加]** を選択します。
5. ホーム ディレクトリの場所の値を指定します。 ホーム ディレクトリを作成するには、*/home/USER\_NAME*の形式に従い、*/home/%u*を使用します。 変数の可能性に関する詳細は、sssd.conf の man ページ (`man 5 sssd.conf`)、"override\_homedir" セクション  を参照してください。
6. **[OK]** を選択します。
7. 変更を保存するには、 **[OK]** を選択します。 その後、ここで表示される値が正しいことを確認します。 ダイアログを終了するには、 **[キャンセル]** を選択します。
8. SSSD と Winbind を同時に実行する予定があれば (SSSD を使用して参加するが、Samba ファイル サーバーを実行する場合など)、smb.conf で Samba オプションの *kerberos method* を *secrets and keytab* に設定する必要があります。 さらに、sssd.conf で SSSD オプション *ad\_update\_samba\_machine\_account\_password* を *true* に設定する必要もあります。 これらのオプションは、システムのキータブが同期しなくなるのを防ぎます。

### Winbind を使用して VM をマネージド ドメインに参加させる

**winbind** と YaST の *Windows Domain Membership* モジュールを使用してマネージド ドメインに参加するには、次の手順を行います。

1. YaST で、**[ネットワーク サービス] &gt; [Windows Domain Membership]** の順に選択します。
2. *[Windows Domain Membership]* 画面の *[ドメインまたはワークグループ]* で、参加するドメインを入力します。 マネージド ドメイン名 (例: *aaddscontoso.com*) を入力します。

    [Image: YaST の [Windows Domain Membership] ウィンドウのスクリーンショット例]
3. Linux 認証に SMB ソースを使用するには、 *[Linux 認証に SMB 情報を使用する]* オプションをオンにします。
4. VM 上にマネージド ドメイン ユーザーのローカル ホーム ディレクトリを自動的に作成するには、 *[ログイン時にホーム ディレクトリを作成する]* オプションをオンにします。
5. マネージド ドメインが一時的に使用できない場合でもドメイン ユーザーがサインインするのを許可するには、 *[オフライン認証]* オプションをオンにします。
6. Samba ユーザーおよびグループの UID と GID の範囲を変更するには、 *[エキスパート設定]* を選択します。
7. *[NTP 構成]* を選択して、マネージド ドメインのネットワーク タイム プロトコル (NTP) 時刻の同期を構成します。 マネージド ドメインの IP アドレスを入力します。 これらの IP アドレスは、ご利用のマネージド ドメインの Microsoft Entra 管理センターで *[プロパティ]* ウィンドウに表示されます (例: *10.0.2.4*、*10.0.2.5*)。
8. **[OK]** を選択し、入力を要求されたら、ドメイン参加を確認します。
9. マネージド ドメインの管理者パスワードを入力し、 **[OK]** を選択します。

    [Image: SLE VM をマネージド ドメインに参加させる場合の認証ダイアログのスクリーンショット例]

マネージド ドメインに参加すると、デスクトップまたはコンソールのディスプレイ マネージャーを使用してワークステーションからサインインできます。

### YaST コマンド ライン インターフェイスから Winbind を使用して VM をマネージド ドメインに参加させる

**winbind** と *YaST コマンド ライン インターフェイス*を使用してマネージド ドメインに参加するには、管理者の`user`値と`password`値を追加し、組織の他のパラメーターを更新します。

```bash
sudo yast samba-client joindomain domain=aaddscontoso.com machine=<(optional) machine account>
```

### ターミナルから Winbind を使用して VM をマネージド ドメインに参加させる

**winbind** と *`samba net` コマンド*を使用してマネージド ドメインに参加させるには:

1. kerberos クライアントと samba-winbind をインストールする:

    ```bash
    sudo zypper in krb5-client samba-winbind
    ```
2. 構成ファイルを編集する:

    - `/etc/samba/smb.conf`

        ```config
        [global]
            workgroup = AADDSCONTOSO
            usershare allow guests = NO #disallow guests from sharing
            idmap config * : backend = tdb
            idmap config * : range = 1000000-1999999
            idmap config AADDSCONTOSO : backend = rid
            idmap config AADDSCONTOSO : range = 5000000-5999999
            kerberos method = secrets and keytab
            realm = AADDSCONTOSO.COM
            security = ADS
            template homedir = /home/%D/%U
            template shell = /bin/bash
            winbind offline logon = yes
            winbind refresh tickets = yes
        ```
    - `/etc/krb5.conf`

        ```config
        [libdefaults]
            default_realm = AADDSCONTOSO.COM
            clockskew = 300
        [realms]
            AADDSCONTOSO.COM = {
                kdc = PDC.AADDSCONTOSO.COM
                default_domain = AADDSCONTOSO.COM
                admin_server = PDC.AADDSCONTOSO.COM
            }
        [domain_realm]
            .aaddscontoso.com = AADDSCONTOSO.COM
        [appdefaults]
            pam = {
                ticket_lifetime = 1d
                renew_lifetime = 1d
                forwardable = true
                proxiable = false
                minimum_uid = 1
            }
        ```
    - /etc/security/pam\_winbind.conf

        ```config
        [global]
            cached_login = yes
            krb5_auth = yes
            krb5_ccache_type = FILE
            warn_pwd_expire = 14
        ```
    - /etc/nsswitch.conf

        ```config
        passwd: compat winbind
        group: compat winbind
        ```
3. Microsoft Entra ID と Linux の日時が同期していることを確認します。これを行うには、Microsoft Entra サーバーを NTP サービスに追加します。

    1. `/etc/ntp.conf` に次の行を追加します。

        ```config
        server aaddscontoso.com
        ```
    2. NTP サービスを再起動する:

        ```bash
        sudo systemctl restart ntpd
        ```
4. ドメインに参加する:

    ```bash
    sudo net ads join -U Administrator%Mypassword
    ```
5. Linux のプラグ可能な認証モジュール (PAM) でログイン ソースとして winbind を有効にします。

    ```bash
    config pam-config --add --winbind
    ```
6. ユーザーがログインできるように、ホーム ディレクトリの自動作成を有効にします。

    ```bash
    sudo pam-config -a --mkhomedir
    ```
7. winbind サービスを開始し、有効にします。

    ```bash
    sudo systemctl enable winbind
    sudo systemctl start winbind
    ```

### SSH のパスワード認証を許可する

既定では、ユーザーは SSH 公開キーベースの認証を使用することによってのみ、VM にサインインできます。 パスワードベースの認証は失敗します。 VM をマネージド ドメインに参加させるときは、これらのドメイン アカウントでパスワードベースの認証を使用する必要があります。 次のようにして、パスワードベースの認証を許可するように SSH の構成を更新します。

1. エディターで *sshd\_conf* ファイルを開きます。

    ```bash
    sudo vi /etc/ssh/sshd_config
    ```
2. *PasswordAuthentication* の行を *yes* に更新します。

    ```config
    PasswordAuthentication yes
    ```

    終わったら、エディターの  コマンドを使用して、`:wq` ファイルを保存して終了します。
3. 変更を適用し、ユーザーがパスワードを使用してサインインできるようにするには、SSH サービスを再起動します。

    ```bash
    sudo systemctl restart sshd
    ```

### "AAD DC Administrators" グループに sudo 特権を付与する

*AAD DC Administrators* グループのメンバーに SLE VM での管理特権を付与するには、 */etc/sudoers* にエントリを追加します。 追加した後、*AAD DC Administrators* グループのメンバーは、SLE VM で `sudo` コマンドを使用できるようになります。

1. *sudoers* ファイルを編集用に開きます。

    ```bash
    sudo visudo
    ```
2. */etc/sudoers* ファイルの最後に、次のエントリを追加します。 *AAD DC Administrators* グループの名前に空白が含まれているため、グループ名に円記号のエスケープ文字を含めます。 独自のドメイン名 (*aaddscontoso.com* など) を追加します。

    ```config
    # Add 'AAD DC Administrators' group members as admins.
    %AAD\ DC\ Administrators@aaddscontoso.com ALL=(ALL) NOPASSWD:ALL
    ```

    終わったら、エディターの `:wq` コマンドを使用してエディターを保存して終了します。

### ドメイン アカウントを使用して VM にサインインする

VM がマネージド ドメインに正常に参加したことを確認するには、ドメイン ユーザー アカウントを使用して新しい SSH 接続を開始します。 ホーム ディレクトリが作成され、ドメインのグループ メンバーシップが適用されていることを確認します。

1. コンソールから新しい SSH 接続を作成します。 `ssh -l` コマンドを使用して、マネージド ドメインに属しているドメイン アカウントを使用し (例: `contosoadmin@aaddscontoso.com`)、VM のアドレス (例: *linux-q2gr.aaddscontoso.com*) を入力します。 Azure Cloud Shell を使用する場合は、内部 DNS 名ではなく、VM のパブリック IP アドレスを使用します。

    ```bash
    sudo ssh -l contosoadmin@AADDSCONTOSO.com linux-q2gr.aaddscontoso.com
    ```
2. VM に正常に接続したら、ホーム ディレクトリが正しく初期化されていることを確認します。

    ```bash
    sudo pwd
    ```

    ユーザー アカウントと一致する独自のディレクトリの */home* ディレクトリにいる必要があります。
3. 次に、グループ メンバーシップが正しく解決されていることを確認します。

    ```bash
    sudo id
    ```

    マネージド ドメインからのグループ メンバーシップが表示される必要があります。
4. *AAD DC Administrators* グループのメンバーとして VM にサインインした場合は、`sudo` コマンドを正しく使用できることを確認します。

    ```bash
    sudo zypper update
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/join-ubuntu-linux-vm"} -->
## Ubuntu VM を Microsoft Entra Domain Services に参加させる - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-ubuntu-linux-vm
- Service: entra-id / domain-services
- Article date: 2025-02-05
- Summary: Ubuntu Linux 仮想マシンを構成して Microsoft Entra Domain Services マネージド ドメインに参加させる方法について説明します。

ユーザーが 1 つの資格情報セットを使用して Azure の仮想マシン (VM) にサインインできるようにするには、VM を Microsoft Entra Domain Services マネージド ドメインに参加させることができます。 VM を Domain Services マネージド ドメインに参加させると、ドメインのユーザー アカウントと資格情報を使用して、サーバーのサインインと管理を行うことができます。 マネージド ドメインからのグループ メンバーシップも適用され、VM 上のファイルまたはサービスへのアクセスを制御できます。

この記事では、Ubuntu Linux VM をマネージド ドメインに参加させる方法について説明します。

### 前提 条件

このチュートリアルを完了するには、次のリソースと特権が必要です。

- アクティブな Azure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、アカウント [を作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- サブスクリプションに関連付けられている Microsoft Entra テナント。オンプレミスディレクトリまたはクラウド専用ディレクトリと同期されます。
    - 必要に応じて、[Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization) を作成するか、[Azure サブスクリプションをアカウント](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)に関連付けます。
- Microsoft Entra Domain Services のマネージド ドメインが有効化され、Microsoft Entra テナント内で設定されています。
    - 必要に応じて、最初のチュートリアル [では、Microsoft Entra Domain Services マネージド ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)を作成して構成します。
- マネージド ドメインの一部であるユーザー アカウント。 ユーザーの SAMAccountName 属性が自動生成されていないことを確認します。 Microsoft Entra テナント内の複数のユーザー アカウントに同じ mailNickname 属性がある場合、各ユーザーの SAMAccountName 属性が自動生成されます。 詳細については、「[Microsoft Entra Domain Services マネージド ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/synchronization)でのオブジェクトと資格情報の同期方法」を参照してください。
- Active Directory で競合を引き起こす可能性のある名前の切り捨てを回避するために、最大 15 文字の一意の Linux VM 名。

### Ubuntu Linux VM を作成して接続する

Azure に既存の Ubuntu Linux VM がある場合は、SSH を使用して接続し、次の手順に進み、VM の構成開始します。

Ubuntu Linux VM を作成する必要がある場合、またはこの記事で使用するテスト VM を作成する場合は、次のいずれかの方法を使用できます。

- [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/quick-create-portal)
- [Azure CLI](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/quick-create-cli)
- [Azure PowerShell](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/quick-create-powershell)

VM を作成するときは、仮想ネットワークの設定に注意して、VM がマネージド ドメインと通信できることを確認します。

- Microsoft Entra Domain Services を有効にしたのと同じ仮想ネットワークまたはピアリングされた仮想ネットワークに VM をデプロイします。
- MICROSOFT Entra Domain Services マネージド ドメインとは異なるサブネットに VM をデプロイします。

VM がデプロイされたら、手順に従って SSH を使用して VM に接続します。

### hosts ファイルを構成する

VM ホスト名がマネージド ドメイン用に正しく構成されていることを確認するには、*/etc/hosts* ファイルを編集し、ホスト名を設定します。

```bash
sudo vi /etc/hosts
```

*hosts* ファイルで、*localhost* アドレスを更新します。 次の例では、

- *aaddscontoso.com* は、マネージド ドメインの DNS ドメイン名です。
- *ubuntu* は、マネージド ドメインに参加している Ubuntu VM のホスト名です。

これらの名前を独自の値に更新してください。

```config
127.0.0.1 ubuntu.aaddscontoso.com ubuntu
```

完了したら、エディターの  コマンドを使用して、`:wq` ファイルを保存して終了します。

### 必要なパッケージをインストールする

VM をマネージド ドメインに参加させるために、いくつかの追加パッケージが必要です。 これらのパッケージをインストールして構成するには、`apt-get` を使用してドメイン参加ツールを更新してインストールします

Kerberos のインストールの間に、*krb5-user* パッケージでは、領域名をすべて大文字で入力するように求められます。 たとえば、マネージド ドメインの名前が *aaddscontoso.com*されている場合は、領域として「*AADDSCONTOSO.COM*」と入力します。 インストールでは、`[realm]` 構成ファイルに `[domain_realm]` セクションと  セクションを書き込みます。 領域はすべて大文字で指定します。

```bash
sudo apt-get update
sudo apt-get install krb5-user samba sssd sssd-tools libnss-sss libpam-sss ntp ntpdate realmd adcli
```

### ネットワーク タイム プロトコル (NTP) の構成

ドメイン通信が正しく機能するためには、Ubuntu VM の日時がマネージド ドメインと同期する必要があります。 マネージド ドメインの NTP ホスト名を */etc/ntp.conf* ファイルに追加します。

1. エディターで *ntp.conf* ファイルを開きます。

    ```bash
    sudo vi /etc/ntp.conf
    ```
2. *ntp.conf* ファイルで、マネージド ドメインの DNS 名を追加する行を作成します。 次の例では、*aaddscontoso.com* のエントリが追加されています。 独自の DNS 名を使用します。

    ```config
    server aaddscontoso.com
    ```

    完了したら、エディターの  コマンドを使用して、`:wq` ファイルを保存して終了します。
3. VM がマネージド ドメインと同期されていることを確認するには、次の手順が必要です。

    - NTP サーバーを停止する
    - マネージド ドメインからの日付と時刻を更新する
    - NTP サービスを開始する

    これらの手順を完了するには、次のコマンドを実行します。 `ntpdate` コマンドで独自の DNS 名を使用します。

    ```bash
    sudo systemctl stop ntp
    sudo ntpdate aaddscontoso.com
    sudo systemctl start ntp
    ```

### VM をマネージド ドメインに参加させる

必要なパッケージが VM にインストールされ、NTP が構成されたので、VM をマネージド ドメインに参加させます。

1. `realm discover` コマンドを使用してマネージド ドメインを検出します。 次の例では、領域 *AADDSCONTOSO.COM*を検出します。 独自のマネージド ドメイン名を、すべて大文字で指定します。

    ```bash
    sudo realm discover AADDSCONTOSO.COM
    ```

    `realm discover` コマンドでマネージド ドメインが見つからない場合は、次のトラブルシューティング手順を確認してください。

    - ドメインが VM から到達可能であることを確認します。 肯定的な応答が返されるかどうかを確認するには、`ping aaddscontoso.com` を試してください。
    - VM が、マネージド ドメインが使用可能な同じ仮想ネットワークまたはピアリングされた仮想ネットワークにデプロイされていることを確認します。
    - 仮想ネットワークの DNS サーバー設定が、マネージド ドメインのドメイン コントローラーを指すよう更新されていることを確認します。
2. 次に、`kinit` コマンドを使用して Kerberos を初期化します。 マネージド ドメインの一部であるユーザーを指定します。 必要に応じて、[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-to-manage-groups)のグループにユーザー アカウントを追加します。

    ここでも、マネージド ドメイン名はすべて大文字で入力する必要があります。 次の例では、`contosoadmin@aaddscontoso.com` という名前のアカウントを使用して Kerberos を初期化します。 マネージド ドメインの一部である独自のユーザー アカウントを入力します。

    ```bash
    sudo kinit -V contosoadmin@AADDSCONTOSO.COM
    ```
3. 最後に、`realm join` コマンドを使用して、VM をマネージド ドメインに参加させます。 `kinit`など、前の `contosoadmin@AADDSCONTOSO.COM` コマンドで指定したマネージド ドメインの一部であるのと同じユーザー アカウントを使用します。

    ```bash
    sudo realm join --verbose AADDSCONTOSO.COM -U 'contosoadmin@AADDSCONTOSO.COM' --install=/
    ```

VM をマネージド ドメインに参加させるのにしばらく時間がかかります。 次の出力例は、VM がマネージド ドメインに正常に参加したことを示しています。

```output
Successfully enrolled machine in realm
```

VM がドメイン参加プロセスを正常に完了できない場合は、VM のネットワーク セキュリティ グループが、TCP + UDP ポート 464 でマネージド ドメインの仮想ネットワーク サブネットへの送信 Kerberos トラフィックを許可していることを確認します。

指定されていない GSS の失敗 *エラーを受け取った場合には、マイナー コードが追加の情報を提供します (Kerberos データベースにサーバーが見つかりません)*。この場合、/etc/krb5.conf  ファイルを開き、`[libdefaults]` セクションに次のコードを追加して、再試行してください。

```config
rdns=false
```

### SSSD 構成を更新する

前の手順でインストールしたパッケージの 1 つは、System Security Services Daemon (SSSD) 用でした。 ユーザーがドメイン資格情報を使用して VM にサインインしようとすると、SSSD は要求を認証プロバイダーに中継します。 このシナリオでは、SSSD は Domain Services を使用して要求を認証します。

1. エディターで *sssd.conf* ファイルを開きます。

    ```bash
    sudo vi /etc/sssd/sssd.conf
    ```
2. 次のように、*use\_fully\_qualified\_names* という行をコメントアウトします。

    ```config
    # use_fully_qualified_names = True
    ```

    完了したら、エディターの  コマンドを使用して、`:wq` ファイルを保存して終了します。
3. 変更を適用するには、SSSD サービスを再起動します。

    ```bash
    sudo systemctl restart sssd
    ```

### ユーザー アカウントとグループの設定を構成する

VM がマネージド ドメインに参加し、認証用に構成されている場合、完了するユーザー構成オプションがいくつかあります。 これらの構成の変更には、パスワードベースの認証を許可することや、ドメイン ユーザーが最初にサインインするときにローカル VM にホーム ディレクトリを自動的に作成することが含まれます。

#### SSH のパスワード認証を許可する

既定では、ユーザーは SSH 公開キーベースの認証を使用してのみ VM にサインインできます。 パスワードベースの認証が失敗します。 VM をマネージド ドメインに参加させる場合、これらのドメイン アカウントではパスワードベースの認証を使用する必要があります。 次のように、パスワードベースの認証を許可するように SSH 構成を更新します。

手記

Ubuntu Marketplace イメージには、通常、/etc/ssh/sshd\_config.d の下にいくつかの構成オプションが設定されています。これには、50-cloud-init.conf ファイルの passwordAuthentication  含まれます。そのため、次の手順で設定されたものが上書きされないように、そのファイルも必ず更新してください。

1. エディターで *sshd\_conf* ファイルを開きます。

    ```bash
    sudo vi /etc/ssh/sshd_config
    ```
2. *PasswordAuthentication* の行を「*yes*」に更新します。

    ```config
    PasswordAuthentication yes
    ```

    完了したら、エディターの  コマンドを使用して、`:wq` ファイルを保存して終了します。
3. 変更を適用し、ユーザーがパスワードを使用してサインインできるようにするには、SSH サービスを再起動します。

    ```bash
    sudo systemctl restart ssh
    ```

#### ホーム ディレクトリの自動作成を構成する

ユーザーが最初にサインインしたときにホーム ディレクトリの自動作成を有効にするには、次の手順を実行します。

1. エディターで `/etc/pam.d/common-session` ファイルを開きます。

    ```bash
    sudo vi /etc/pam.d/common-session
    ```
2. このファイルの行 `session optional pam_sss.so`の下に次の行を追加します。

    ```config
    session required pam_mkhomedir.so skel=/etc/skel/ umask=0077
    ```

    完了したら、エディターの  コマンドを使用して、`:wq` ファイルを保存して終了します。

#### "AAD DC Administrators" グループに sudo 特権を付与する

Ubuntu VM で *AAD DC Administrators* グループの管理者特権のメンバーに付与するには、*/etc/sudoers*にエントリを追加します。 追加すると、*AAD DC Administrators* グループのメンバーは、Ubuntu VM で `sudo` コマンドを使用できます。

1. 編集のために *sudoers* ファイルを開きます。

    ```bash
    sudo visudo
    ```
2. /etc/sudoers *ファイルの末尾に、* 次のエントリを追加してください。

    ```config
    # Add 'AAD DC Administrators' group members as admins.
    %AAD\ DC\ Administrators ALL=(ALL) NOPASSWD:ALL
    ```

    完了したら、`Ctrl-X` コマンドを使用してエディターを保存して終了します。

### ドメイン アカウントを使用して VM にサインインする

VM がマネージド ドメインに正常に参加したことを確認するには、ドメイン ユーザー アカウントを使用して新しい SSH 接続を開始します。 ホーム ディレクトリが作成され、ドメインのグループ メンバーシップが適用されていることを確認します。

1. コンソールから新しい SSH 接続を作成します。 `ssh -l` などの `contosoadmin@aaddscontoso.com` コマンドを使用してマネージド ドメインに属するドメイン アカウントを使用し、VM のアドレス (*ubuntu.aaddscontoso.com*など) を入力します。 Azure Cloud Shell を使用する場合は、内部 DNS 名ではなく、VM のパブリック IP アドレスを使用します。

    ```bash
    sudo ssh -l contosoadmin@AADDSCONTOSO.com ubuntu.aaddscontoso.com
    ```
2. VM に正常に接続したら、ホーム ディレクトリが正しく初期化されたことを確認します。

    ```bash
    sudo pwd
    ```

    ユーザー アカウントと一致する独自のディレクトリを持つ */home* ディレクトリに存在する必要があります。
3. 次に、グループ メンバーシップが正しく解決されていることを確認します。

    ```bash
    sudo id
    ```

    マネージド ドメインのグループ メンバーシップが表示されます。
4. *AAD DC Administrators* グループのメンバーとして VM にサインインした場合は、`sudo` コマンドを正しく使用できることを確認します。

    ```bash
    sudo apt-get update
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/join-windows-vm"} -->
## Microsoft Entra Domain Services のマネージド ドメインに Windows Server VM を参加させる - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-windows-vm
- Service: entra-id / domain-services
- Article date: 2025-02-05
- Summary: このチュートリアルでは、Microsoft Entra Domain Services マネージド ドメインに Windows Server 仮想マシンを参加させる方法を学習します。

Microsoft Entra Domain Services では、Windows Server Active Directory と完全に互換性のあるマネージド ドメイン サービス (ドメイン参加、グループ ポリシー、LDAP、Kerberos または NTLM 認証など) が提供されます。 Domain Services マネージド ドメインを使用すると、ドメイン参加の機能と管理を Azure の仮想マシン (VM) に提供することができます。 このチュートリアルでは、Windows Server VM を作成した後、それをマネージド ドメインに参加させる方法を示します。

このチュートリアルでは、以下の内容を学習します。

- Windows Server VM を作成する
- Windows Server VM を Azure 仮想ネットワークに接続する
- VM をマネージド ドメインに参加させる

Azure サブスクリプションをお持ちでない場合は、始める前に[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### 前提条件

このチュートリアルを完了するには、以下のリソースが必要です。

- 有効な Azure サブスクリプション
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付け](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)ます。
- Microsoft Entra テナントで有効にされていて、構成されている Microsoft Entra Domain Services マネージド ドメイン。
    - 必要に応じて、[Microsoft Entra Domain Services のマネージド ドメインを作成して構成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)。
- マネージド ドメインの一部であるユーザー アカウント。
    - アカウントがマネージド ドメインにサインインできるように、Microsoft Entra Connect のパスワード ハッシュの同期またはセルフサービス パスワード リセットが実行されていることを確認します。
- Domain Services 仮想ネットワークにデプロイされた Azure Bastion ホスト。
    - 必要に応じて [Azure Bastion ホストを作成](https://learn.microsoft.com/ja-jp/azure/bastion/tutorial-create-host-portal)してください。

ドメインに参加させる VM が既にある場合は、VM をマネージド ドメインに参加させる方法についてのセクションに進んでください。

### Azure portal にサインインする

このチュートリアルでは、Azure portal を使用して、マネージド ドメインに参加する Windows Server VM を作成します。 開始するには、まず [Azure portal](https://portal.azure.com) にサインインします。

### Windows Server 仮想マシンを作成する

コンピューターをマネージド ドメインに参加させる方法を確認するため、Windows Server VM を作成しましょう。 この VM は、マネージド ドメインへの接続を提供する Azure 仮想ネットワークに接続されます。 マネージド ドメインに参加するプロセスは、通常のオンプレミスの Active Directory Domain Services ドメインに参加する場合と同じです。

ドメインに参加させる VM が既にある場合は、VM をマネージド ドメインに参加させる方法についてのセクションに進んでください。

1. Azure portal のメニューまたは **ホーム** ページから、[ **リソースの作成**] を選択します。
2. **[仮想マシン]** の下にある **[作成する]** をクリックします。
3. **[基本]** ウィンドウで、仮想マシンのコア設定を構成します。 他のオプションには既定値をそのまま使用します。

    | パラメーター | 推奨値 |
    | --- | --- |
    | リソースグループ | リソース グループを選択または作成します (*myResourceGroup* など) |
    | 仮想マシン名 | VM の名前を入力します (*myVM* など) |
    | リージョン | VM を作成するリージョンを選択します ("*米国東部*" など) |
    | Image | Windows Server のバージョンを選択する |
    | ユーザー名 | VM 上に作成するローカル管理者アカウントのユーザー名を入力します (*azureuser* など) |
    | パスワード | VM 上に作成するローカル管理者の安全なパスワードを入力し、確認します。 ドメイン ユーザー アカウントの資格情報は指定しないでください。 [Windows LAPS](https://learn.microsoft.com/ja-jp/windows-server/identity/laps/laps-overview) はサポートされていません。 |
4. 既定では、Azure に作成された VM にインターネットから RDP を使用してアクセスすることができます。 RDP が有効になっていると、自動サインイン攻撃が発生する可能性があります。これにより、サインインの試行が複数回連続して失敗するため、*admin* や *administrator* などの一般的な名前を持つアカウントが無効になる場合があります。

    RDP は、必要な場合にのみ有効にし、承認された IP 範囲のセットに限定する必要があります。 この構成により、VM のセキュリティが向上し、攻撃される可能性がある領域が減少します。 または、TLS を介した Microsoft Entra 管理センター経由のアクセスのみを許可する Azure Bastion ホストを作成して使用することもできます。 このチュートリアルの次の手順では、Azure Bastion ホストを使用して安全に VM に接続します。

    **[パブリック受信ポート]** で *[なし]* を選択します。
5. 完了したら、 **[次へ: ディスク]** を選択します。
6. **[OS ディスクの種類]** のドロップダウン メニューから *[Standard SSD]* を選択し、 **[次へ: ネットワーク]** を選択します。
7. マネージド ドメインがデプロイされているサブネットと通信できる Azure 仮想ネットワーク サブネットに、VM を接続する必要があります。 マネージド ドメインを専用のサブネットにデプロイすることをお勧めします。 マネージド ドメインと同じサブネットに VM をデプロイしないでください。

    VM をデプロイして適切な仮想ネットワーク サブネットに接続するには、主に次の 2 つの方法があります。

    - マネージド ドメインがデプロイされているのと同じ仮想ネットワークで、サブネットを作成するか、既存のサブネットを選択します。
    - [Azure 仮想ネットワーク ピアリング](https://learn.microsoft.com/ja-jp/azure/virtual-network/virtual-network-peering-overview)を使用して接続されている Azure 仮想ネットワーク内のサブネットを選択します。

    マネージド ドメインのサブネットに接続されていない仮想ネットワーク サブネットを選択した場合、その VM をマネージド ドメインに参加させることはできません。 このチュートリアルでは、Azure 仮想ネットワークに新しいサブネットを作成します。

    [**ネットワーク**] ウィンドウで、マネージド ドメインがデプロイされている仮想ネットワーク (*aadds-vnet* など) を選択します
8. この例では、マネージド ドメインが接続されている既存の *aadds-subnet* が示されています。 このサブネットには VM を接続しないでください。 VM 用のサブネットを作成するには、 **[サブネット構成の管理]** を選択します。

    [Image: サブネット構成の管理を選択する]
9. 仮想ネットワーク ウィンドウの左側のメニューで、 **[アドレス空間]** を選択します。 この仮想ネットワークは、既定のサブネットで使用される単一のアドレス空間 *10.0.2.0/24* で作成されています。 その他のサブネット ("*ワークロード*" や Azure Bastion 用など) も既に存在している場合があります。

    この仮想ネットワークにさらに IP アドレス範囲を追加します。 このアドレス範囲のサイズと実際に使用する IP アドレス範囲は、既にデプロイされている他のネットワーク リソースによって異なります。 この IP アドレス範囲は、Azure またはオンプレミスの環境における既存のアドレス範囲と重複することはできません。 サブネットにデプロイする予定の VM の数に対して十分な大きさの IP アドレス範囲を設定するようにしてください。

    次の例では、追加の IP アドレス範囲 *10.0.5.0/24* が追加されています。 準備ができたら、 **[保存]** を選択します。

    [Image: 追加の仮想ネットワーク IP アドレス範囲を追加する]
10. 次に、仮想ネットワーク ウィンドウの左側のメニューで、 **[サブネット]** を選択し、 **[+ サブネット]** を選択してサブネットを追加します。
11. **[+ サブネット]** を選択し、サブネットの名前を入力します (例: *management*)。 **[アドレス範囲 (CIDR ブロック)]** を指定します (例: *10.0.5.0/24*)。 この IP アドレス範囲が、他の既存の Azure またはオンプレミスのアドレス範囲と重複していないことを確認します。 他のオプションは既定値のままにして、 **[OK]** を選択します。

    [Image: サブネット構成を作成する]
12. サブネットの作成には数秒かかります。 作成されたら、 *[X]* を選択してサブネット ウィンドウを閉じます。
13. **[ネットワーク]** ウィンドウに戻って VM を作成し、ドロップダウン メニューから作成したサブネットを選択します (例: *management*)。 ここでも、適切なサブネットを選択します。マネージド ドメインと同じサブネットには VM をデプロイしないでください。
14. **[パブリック IP]** には、ドロップダウン メニューから *[なし]* を選択します。 このチュートリアルでは、Azure Bastion を使用して "management" に接続するため、VM にパブリック IP アドレスを割り当てる必要はありません。
15. 他のオプションは既定値のままにして、 **[管理]** を選択します。
16. **[ブート診断]** を *[オフ]* に設定します。 他のオプションは既定値のままにして、 **[確認と作成]** を選択します。
17. VM の設定を確認して、 **[作成]** を選択します。

VM の作成には数分かかります。 Microsoft Entra 管理センターには、デプロイの状態が表示されます。 VM の準備ができたら、 **[リソースに移動]** を選択します。

[Image: 作成が成功したら VM リソースに移動する]

### Windows Server VM に接続する

VM に対して安全に接続するために、Azure Bastion ホストを使用します。 Azure Bastion では、仮想ネットワークにマネージド ホストがデプロイされ、VM への Web ベースの RDP 接続または SSH 接続は、そのマネージド ホストによって提供されます。 VM にパブリック IP アドレスは不要であり、外部のリモート トラフィック向けにネットワーク セキュリティ グループの規則を開放する必要もありません。 Web ブラウザーから Microsoft Entra 管理センターを使用して VM に接続します。 必要に応じて [Azure Bastion ホストを作成](https://learn.microsoft.com/ja-jp/azure/bastion/tutorial-create-host-portal)してください。

要塞ホストを使用して VM に接続するには、次の手順を実行します。

1. VM の **[概要]** ペインで **[接続]** を選択し、 **[Bastion]** を選択します。

    [Image: Bastion を使用して Windows 仮想マシンに接続する]
2. 前のセクションで指定した VM の資格情報を入力し、 **[接続]** を選択します。

    [Image: Bastion ホストを通して接続する]

必要に応じて、ポップアップの表示を Web ブラウザーに許可して、Bastion 接続を表示します。 VM への接続には数秒かかります。

### VM をマネージド ドメインに参加させる

VM を作成し、Azure Bastion を使用して Web ベースの RDP 接続を確立したので、Windows Server 仮想マシンをマネージド ドメインに参加させましょう。 このプロセスは、通常のオンプレミスの Active Directory Domain Services ドメインに接続しているコンピューターと同じです。

1. VM にサインインしたときに**サーバー マネージャー**が既定で開かない場合は、 **[スタート]** メニューを選択し、 **[サーバー マネージャー]** を選択します。
2. **[サーバー マネージャー]** ウィンドウの左側のウィンドウで、 **[ローカル サーバー]** を選択します。 右側のウィンドウの **[プロパティ]** で、 **[ワークグループ]** を選択します。

    [Image: VM でサーバー マネージャーを開き、ワークグループのプロパティを編集する]
3. **[システム プロパティ]** ウィンドウで **[変更]** を選択し、マネージド ドメインに参加させます。

    [Image: ワークグループまたはドメイン プロパティの変更を選択する]
4. **[ドメイン]** ボックスでマネージド ドメインの名前を指定し (例: *aaddscontoso.com*)、 **[OK]** を選択します。

    [Image: 参加するマネージド ドメインを指定する]
5. ドメインの資格情報を入力してドメインに参加します。 マネージド ドメインの一部であるユーザーの資格情報を指定します。 アカウントはマネージド ドメインまたは Microsoft Entra テナントの一部である必要があります。Microsoft Entra テナントに関連付けられている外部ディレクトリのアカウントが、ドメイン参加プロセス中に正しく認証を行うことはできません。

    アカウントの資格情報は、次のいずれかの方法で指定できます。

    - **UPN 形式** (推奨) - Microsoft Entra ID で構成したように、ユーザー アカウントのユーザー プリンシパル名 (UPN) サフィックスを入力します。 たとえば、ユーザー *contosoadmin* の UPN サフィックスは `contosoadmin@aaddscontoso.onmicrosoft.com` になります。 *SAMAccountName*形式ではなく UPN 形式を使用するとドメインに確実にサインインできる一般的なユースケースが 2 つあります。
        - ユーザーの UPN プレフィックスが長い場合 (例: *deehasareallylongname*)、*SAMAccountName* が自動生成される場合があります。
        - Microsoft Entra テナントで複数のユーザーが同じ UPN プレフィックスを使用していると (例: *dee*)、*SAMAccountName* 形式が自動生成される場合があります。
    - **SAMAccountName 形式** - *SAMAccountName* 形式でアカウント名を入力します。 たとえば、ユーザー *contosoadmin* の *SAMAccountName* は `AADDSCONTOSO\contosoadmin` になります。
6. マネージド ドメインへの参加には数秒かかります。 完了すると、ドメインへの参加を歓迎する次のようなメッセージが表示されます。

    [Image: ドメインへようこそ]

    **[OK]** を選択して続行します。
7. マネージド ドメインに参加させるプロセスを完了するには、VM を再起動します。

ヒント

PowerShell の [Add-Computer](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.management/add-computer) コマンドレットを使用して、VM をドメインに参加させることができます。 次の例では、*AADDSCONTOSO* ドメインに参加した後、VM を再起動しています。 メッセージが表示されたら、マネージド ドメインの一部であるユーザーの資格情報を入力します。

`Add-Computer -DomainName AADDSCONTOSO -Restart`

VM に接続したり手動で接続を構成したりすることなく VM をドメインに参加させるには、[Set-AzVmAdDomainExtension](https://learn.microsoft.com/ja-jp/powershell/module/az.compute/set-azvmaddomainextension) Azure PowerShell コマンドレットを使用できます。

Windows Server VM が再起動すると、マネージド ドメインで適用されているすべてのポリシーが VM にプッシュされます。 また、適切なドメイン資格情報を使用して Windows Server VM にサインインすることもできます。

### リソースをクリーンアップする

次のチュートリアルでは、この Windows Server VM を使用して、マネージド ドメインを管理できる管理ツールをインストールします。 このチュートリアル シリーズを続けない場合は、次のクリーンアップ手順を確認して、VM を削除します。 そうでない場合は、次のチュートリアルを続けます。

#### マネージド ドメインへの VM の参加を解除する

マネージド ドメインから VM を削除するには、もう一度、VM をドメインに参加させるための手順を実行します。 このとき、マネージド ドメインに参加させる代わりに、WORKGROUP (既定の "*WORKGROUP*" など) に参加させることを選択します。 VM が再起動すると、コンピューター オブジェクトがマネージド ドメインから削除されます。

ドメインへの参加を解除せずに VM を削除すると、孤立したコンピューター オブジェクトが Domain Services に残されます。

#### VM の削除

この Windows Server VM をもう使わない場合は、次の手順に従って VM を削除します。

1. 左側のメニューから、 **[リソース グループ]** を選択します
2. お使いのリソース グループを選択します (例: *myResourceGroup*)。
3. お使いの VM を選択し (例: *myVM*)、 **[削除]** を選択します。 **[はい]** を選択して、リソースの削除を確定します。 VM の削除には数分かかります。
4. VM が削除されたら、*myVM-* プレフィックスが付いている OS ディスク、ネットワーク インターフェイス カード、その他のすべてのリソースを選択して削除します。

### ドメイン参加の問題のトラブルシューティング

通常のオンプレミス コンピューターを Active Directory Domain Services ドメインに参加させる場合と同じ方法で、Windows Server VM もマネージド ドメインに正常に参加させることができるはずです。 Windows Server VM をマネージド ドメインに参加させることができない場合は、接続または資格情報に関連する問題があることを示しています。 マネージド ドメインに正常に参加させるには、次のトラブルシューティングのセクションを確認してください。

#### 接続に関する問題

ドメインに参加するための資格情報を要求するプロンプトが表示されない場合は、接続に問題があります。 仮想ネットワーク上のマネージド ドメインに VM が到達できません。

以下の各トラブルシューティング手順を実行した後、Windows Server VM をマネージド ドメインに再度参加させてください。

- Domain Services が有効になっているのと同じ仮想ネットワークに VM が接続されていること、または VM にピアリングされたネットワーク接続があることを確認します。
- マネージド ドメインの DNS ドメイン名に対して ping を実行します (例: `ping aaddscontoso.com`)。
    - ping 要求が失敗する場合は、マネージド ドメインの IP アドレスに対して ping を実行します (例: `ping 10.0.0.4`)。 環境の IP アドレスは、Azure リソースの一覧からマネージド ドメインを選択すると、 *[プロパティ]* ページに表示されます。
    - ドメインではなく IP アドレスを ping できた場合、DNS が正しく構成されていない可能性があります。 マネージド ドメインの IP アドレスが仮想ネットワークの DNS サーバーとして構成されていることを確認します。
- `ipconfig /flushdns` コマンドを使用して、仮想マシンの DNS リゾルバー キャッシュをフラッシュしてみます。

#### 資格情報に関連した問題

ドメインに参加するための資格情報を要求するプロンプトが表示されても、資格情報を入力した後でエラーが発生する場合は、VM はマネージド ドメインに接続できます。 指定した資格情報では、VM をマネージド ドメインに参加させることはできません。

以下の各トラブルシューティング手順を実行した後、Windows Server VM をマネージド ドメインに再度参加させてください。

- 指定するユーザー アカウントがマネージド ドメインに属していることを確認します。
- アカウントがマネージド ドメインまたは Microsoft Entra テナントの一部であることを確認してください。 Microsoft Entra テナントに関連付けられている外部ディレクトリのアカウントが、ドメイン参加プロセス中に正しく認証を行うことはできません。
- UPN 形式を使用して資格情報を指定します (例: `contosoadmin@aaddscontoso.onmicrosoft.com`)。 たくさんのユーザーがテナントで同じ UPN プレフィックスを使用している場合、または UPN プレフィックスが最大文字数を超えている場合は、アカウントの *SAMAccountName* が自動生成される可能性があります。 そのような場合、アカウントの *SAMAccountName* 形式が、想定されている形式やオンプレミス ドメインで使用されている形式と異なる可能性があります。
- マネージド ドメインとの[パスワード同期を有効にしている](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)ことを確認します。 この構成手順を行わないと、サインインの試行を正しく認証するために必要なパスワード ハッシュがマネージド ドメインに存在しなくなります。
- パスワード同期が完了するまで待ちます。 ユーザー アカウントのパスワードが変更されると、Microsoft Entra ID からの自動バックグラウンド同期によって、Domain Services 内のパスワードが更新されます。 ドメインへの参加にパスワードを使用できるようになるまでに、しばらく時間がかかります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/join-windows-vm-template"} -->
## テンプレートを使用して Windows VM を Microsoft Entra Domain Services に参加させる - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-windows-vm-template
- Service: entra-id / domain-services
- Article date: 2025-02-05
- Summary: Azure Resource Manager テンプレートを使用して、新規または既存の Windows Server VM を Microsoft Entra Domain Services マネージド ドメインに参加させる方法について説明します。

Azure 仮想マシン (VM) のデプロイと構成を自動化するには、Resource Manager テンプレートを使用できます。 これらのテンプレートを使用すると、毎回一貫したデプロイを作成できます。 拡張機能をテンプレートに含め、デプロイの一部として VM を自動的に構成することもできます。 1 つの便利な拡張機能は、VM をドメインに参加させます。これは、Microsoft Entra Domain Services のマネージド ドメインで使用できます。

この記事では、Resource Manager テンプレートを使用して、Windows Server VM を作成して Domain Services マネージド ドメインに参加させる方法について説明します。 また、既存の Windows Server VM を Domain Services ドメインに参加させる方法についても説明します。

### [前提条件]

このチュートリアルを完了するには、以下のリソースと特権が必要です。

- 有効な Azure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付け](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)ます。
- Microsoft Entra テナント内の Microsoft Entra Domain Services マネージド ドメインが有効化および構成されています。
    - 必要であれば、1 つ目のチュートリアルで [Microsoft Entra Domain Services のマネージド ドメインを作成して構成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)します。
- *AAD DC Administrators* グループの一部であるユーザー アカウント。

### Azure Resource Manager テンプレートの概要

Resource Manager テンプレートを使用すると、コードで Azure インフラストラクチャを定義できます。 必要なリソース、ネットワーク接続、または VM の構成はすべてテンプレートで定義できます。 これらのテンプレートは、毎回一貫性のある再現可能なデプロイを作成し、変更を加える際にバージョン管理できます。 詳細については、「 [Azure Resource Manager テンプレートの概要](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/templates/overview)」を参照してください。

各リソースは、JavaScript Object Notation (JSON) を使用してテンプレートで定義されます。 次の JSON 例では、 *Microsoft.Compute/virtualMachines/extensions* リソースの種類を使用して、Active Directory ドメイン参加拡張機能をインストールします。 デプロイ時に指定されたパラメーターを使用します。 拡張機能がデプロイされると、VM は指定されたマネージド ドメインに参加します。

```json
 {
      "apiVersion": "2015-06-15",
      "type": "Microsoft.Compute/virtualMachines/extensions",
      "name": "[concat(parameters('dnsLabelPrefix'),'/joindomain')]",
      "location": "[parameters('location')]",
      "dependsOn": [
        "[concat('Microsoft.Compute/virtualMachines/', parameters('dnsLabelPrefix'))]"
      ],
      "properties": {
        "publisher": "Microsoft.Compute",
        "type": "JsonADDomainExtension",
        "typeHandlerVersion": "1.3",
        "autoUpgradeMinorVersion": true,
        "settings": {
          "Name": "[parameters('domainToJoin')]",
          "OUPath": "[parameters('ouPath')]",
          "User": "[concat(parameters('domainToJoin'), '\\', parameters('domainUsername'))]",
          "Restart": "true",
          "Options": "[parameters('domainJoinOptions')]"
        },
        "protectedSettings": {
          "Password": "[parameters('domainPassword')]"
        }
      }
    }
```

この VM 拡張機能は、同じテンプレートに VM を作成しない場合でもデプロイできます。 この記事の例では、次の両方の方法を示します。

- Windows Server VM を作成してマネージド ドメインに参加させる
- 既存の Windows Server VM をマネージド ドメインに参加させる

### Windows Server VM を作成してマネージド ドメインに参加させる

Windows Server VM が必要な場合は、Resource Manager テンプレートを使用して VM を作成して構成できます。 VM がデプロイされると、VM をマネージド ドメインに参加させる拡張機能がインストールされます。 マネージド ドメインに参加する VM が既にある場合は、「 既存の Windows Server VM をマネージド ドメインに参加させる」に進みます。

Windows Server VM を作成し、それをマネージド ドメインに参加させるには、次の手順を実行します。

1. [クイック スタート テンプレート](https://azure.microsoft.com/resources/templates/vm-domain-join/)を参照します。 **Azure にデプロイするオプションを選択します**。
2. [ **カスタム デプロイ** ] ページで、次の情報を入力して、Windows Server VM を作成し、マネージド ドメインに参加させます。

    | 設定 | 価値 |
    | --- | --- |
    | サブスクリプション | Microsoft Entra Domain Services を有効にしたのと同じ Azure サブスクリプションを選択します。 |
    | リソースグループ | VM のリソース グループを選択します。 |
    | ロケーション | VM の場所を選択します。 |
    | 既存の VNET 名 | vm を接続する既存の仮想ネットワークの名前 ( *myVnet* など)。 |
    | 既存のサブネット名 | 既存の仮想ネットワーク サブネットの名前 (例: *ワークロード*)。 |
    | DNS ラベル プレフィックス | vm に使用する DNS 名 ( *myvm* など) を入力します。 |
    | VM サイズ | *Standard\_DS2\_v2*などの VM サイズを指定します。 |
    | 参加するドメイン | マネージド ドメインの DNS 名 ( *aaddscontoso.com* など)。 |
    | ドメイン ユーザー名 | vm をマネージド ドメインに参加させるために使用するマネージド ドメイン内のユーザー アカウント ( `contosoadmin@aaddscontoso.com`など)。 このアカウントは、マネージド ドメインの一部である必要があります。 |
    | ドメイン パスワード | 前の設定で指定したユーザー アカウントのパスワード。 |
    | オプションの OU パス | VM を追加するカスタム OU。 このパラメーターの値を指定しない場合、VM は既定の *Microsoft Entra DC Computers* OU に追加されます。 |
    | VM 管理者ユーザー名 | VM に作成するローカル管理者アカウントを指定します。 |
    | VM 管理者パスワード | VM のローカル管理者パスワードを指定します。 強力なローカル管理者パスワードを作成して、パスワードブルートフォース攻撃から保護します。 |
3. 使用条件を確認し、 **上記の使用条件に同意するボックスを**オンにします。 準備ができたら、[ **購入** ] を選択して VM を作成し、マネージド ドメインに参加させます。

Warnung

**パスワードは慎重に処理してください。** テンプレート パラメーター ファイルは、マネージド ドメインの一部であるユーザー アカウントのパスワードを要求します。 このファイルに値を手動で入力し、ファイル共有やその他の共有場所でアクセスできるようにしないでください。

デプロイが正常に完了するまで数分かかります。 完了すると、Windows VM が作成され、マネージド ドメインに参加します。 VM は、ドメイン アカウントを使用して管理またはサインインできます。

### 既存の Windows Server VM をマネージド ドメインに参加させる

マネージド ドメインに参加する既存の VM または VM のグループがある場合は、Resource Manager テンプレートを使用して VM 拡張機能をデプロイできます。

既存の Windows Server VM をマネージド ドメインに参加させるには、次の手順を実行します。

1. [クイック スタート テンプレート](https://azure.microsoft.com/resources/templates/vm-domain-join-existing/)を参照します。 **Azure にデプロイするオプションを選択します**。
2. [ **カスタム デプロイ** ] ページで、次の情報を入力して VM をマネージド ドメインに参加させます。

    | 設定 | 価値 |
    | --- | --- |
    | サブスクリプション | Microsoft Entra Domain Services を有効にしたのと同じ Azure サブスクリプションを選択します。 |
    | リソースグループ | 既存の VM を含むリソース グループを選択します。 |
    | ロケーション | 既存の VM の場所を選択します。 |
    | VM の一覧 | *myVM1、myVM2* など、マネージド ドメインに参加する既存の VM のコンマ区切りの一覧を入力します。 |
    | ドメイン参加ユーザー名 | vm をマネージド ドメインに参加させるために使用するマネージド ドメイン内のユーザー アカウント ( `contosoadmin@aaddscontoso.com`など)。 このアカウントは、マネージド ドメインの一部である必要があります。 |
    | ドメイン参加ユーザー パスワード | 前の設定で指定したユーザー アカウントのパスワード。 |
    | オプションの OU パス | VM を追加するカスタム OU。 このパラメーターの値を指定しない場合、VM は既定の *Microsoft Entra DC Computers* OU に追加されます。 |
3. 使用条件を確認し、 **上記の使用条件に同意するボックスを**オンにします。 準備ができたら、[ **購入** ] を選択して VM をマネージド ドメインに参加させます。

Warnung

**パスワードは慎重に処理してください。** テンプレート パラメーター ファイルは、マネージド ドメインの一部であるユーザー アカウントのパスワードを要求します。 このファイルに値を手動で入力し、ファイル共有やその他の共有場所でアクセスできるようにしないでください。

デプロイが正常に完了するまでに少し時間がかかります。 完了すると、指定した Windows VM がマネージド ドメインに参加し、ドメイン アカウントを使用して管理またはサインインできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/manage-dns"} -->
## Microsoft Entra Domain Services の DNS を管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/manage-dns
- Service: entra-id / domain-services
- Article date: 2025-02-05
- Summary: DNS サーバー ツールをインストールして DNS を管理し、Microsoft Entra Domain Services マネージド ドメインの条件付きフォワーダーを作成する方法について説明します。

Microsoft Entra Domain Services に含まれるドメイン ネーム システム (DNS) サーバーによって、マネージド ドメインの名前が解決されます。 この DNS サーバーには、サービスの実行を可能にする重要なコンポーネントに対する組み込みの DNS レコードと更新プログラムが含まれます。

独自のアプリケーションやサービスを実行するとき、ドメインに参加していないコンピューターの DNS レコードを作成したり、ロード バランサーの仮想 IP アドレスを構成したり、外部 DNS フォワーダーを設定したりすることが必要になる場合があります。 *AAD DC Administrators* グループに属しているユーザーには、Domain Services マネージド ドメインに対する DNS 管理特権が付与され、カスタム DNS レコードを作成および編集できます。

ハイブリッド環境では、オンプレミスの AD DS 環境など、他の DNS 名前空間内で構成されている DNS ゾーンとレコードは、マネージド ドメインに同期されません。 他の DNS 名前空間で名前付きリソースを解決するには、環境内の既存の DNS サーバーを指す条件付きフォワーダーを作成して使用します。

Domain Services は、通常の操作中に複数の Azure エンドポイントと通信します。 file.core.windows.net や blob.core.windows.net などのゾーンをリダイレクトすると、Domain Services はサポートできない状態になります。

windowsazure.com または core.windows.net に関連する DNS ゾーンのリダイレクトを控える。 DNS リダイレクトが必要な場合は、ゾーンではなく個々のホスト名にリダイレクトを制限します。 たとえば、file.core.windows.net の代わりに server1.file.core.windows.net を使用します。

注

[ルート ヒントの使用] オプションを設定してサーバー レベルの DNS フォワーダーを 168.63.129.16 以外に有効または変更することはサポートされておらず、Entra Domain Services マネージド ドメインに問題が発生します。 テナントの構成がサポートされない可能性があるため、これらの設定は変更しないでください。 サポートされている状態に DNS を構成できない場合は、Microsoft サポートにお問い合わせください。

この記事では、DNS サーバー ツールをインストールし、DNS コンソールを使用してレコードを管理し、Domain Services で条件付きフォワーダーを作成する方法について説明します。

### 開始する前に

この記事を完了するには、以下のリソースと特権が必要です。

- 有効な Azure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、 [アカウントを作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、 [Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization) するか、 [Azure サブスクリプションをアカウントに関連付けます](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)。
- Microsoft Entra テナント内の Microsoft Entra Domain Services マネージド ドメインが有効化および構成されています。
    - 必要に応じて、チュートリアルを完了して [、Microsoft Entra Domain Services マネージド ドメインを作成して構成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)。
- Domain Services 仮想ネットワークから他の DNS 名前空間がホストされている場所への接続。
    - この接続は、 [Azure ExpressRoute](https://learn.microsoft.com/ja-jp/azure/expressroute/expressroute-introduction) または [Azure VPN Gateway](https://learn.microsoft.com/ja-jp/azure/vpn-gateway/vpn-gateway-about-vpngateways) 接続で提供できます。
- マネージド ドメインに参加している Windows Server 管理 VM。
    - 必要に応じて、チュートリアルを完了して [Windows Server VM を作成し、マネージド ドメインに参加させます](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-windows-vm)。
- *Microsoft Entra テナントの Microsoft Entra DC 管理者*グループのメンバーであるユーザー アカウント。

### DNS サーバー ツールのインストール

マネージド ドメインで DNS レコードを作成および変更するには、DNS サーバー ツールをインストールする必要があります。 これらのツールは、Windows Server の機能としてインストールできます。 Windows クライアントに管理ツールをインストールする方法の詳細については、「リモート サーバー管理ツール (RSAT) インストールする」を参照してください。

1. 管理 VM にサインインします。 Microsoft Entra 管理センターを使用して接続する手順については、「 [Windows Server VM への接続](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-windows-vm#connect-to-the-windows-server-vm)」を参照してください。
2. VM にサインインするときに **サーバー マネージャー** が既定で開かない場合は、[ **スタート** ] メニューを選択し、[ **サーバー マネージャー**] を選択します。
3. *[サーバー マネージャー*] ウィンドウの **[ダッシュボード**] ウィンドウで、[**役割と機能の追加]** を選択します。
4. 役割**と機能の追加ウィザード**の [*開始する前*に] ページで、[**次へ**] を選択します。
5. *[インストールの種類*] で、[**役割ベースまたは機能ベースのインストール**] オプションをオンのままにして、[**次へ**] を選択します。
6. [ **サーバーの選択]** ページで、サーバー プールから現在の VM ( *myvm.aaddscontoso.com* など) を選択し、[ **次へ**] を選択します。
7. [ **サーバーの役割]** ページで、[ **次へ**] をクリックします。
8. [ **機能** ] ページで、[ **リモート サーバー管理ツール** ] ノードを展開し、[ **役割管理ツール** ] ノードを展開します。 役割管理ツールの一覧から **[DNS サーバー** ツール] 機能を選択します。

    [Image: 使用可能なロール管理ツールの一覧から DNS サーバー ツールをインストールすることを選択します]
9. [ **確認** ] ページで、[ **インストール**] を選択します。 DNS サーバー ツールのインストールには 1 ~ 2 分かかる場合があります。
10. 機能のインストールが完了したら、[ **閉じる** ] を選択して **役割と機能の追加** ウィザードを終了します。

### DNS 管理コンソールを開いて DNS を管理する

DNS サーバー ツールをインストールすると、マネージド ドメイン上の DNS レコードを管理できます。

注

マネージド ドメインで DNS を管理するには、 *AAD DC Administrators* グループのメンバーであるユーザー アカウントにサインインしている必要があります。

1. [スタート] 画面で、[管理ツール] 選択します。 前のセクションでインストールした **DNS** を含む、使用可能な管理ツールの一覧が表示されます。 **DNS** を選択して DNS 管理コンソールを起動します。
2. [ **DNS サーバーへの接続** ] ダイアログで、 **次のコンピューター**を選択し、マネージド ドメインの DNS ドメイン名 ( *aaddscontoso.com* など) を入力します。

    [Image: DNS コンソールでマネージド ドメインに接続する]
3. DNS コンソールは、指定されたマネージド ドメインに接続します。 **[前方参照ゾーン**] または **[逆引き参照ゾーン] を**展開して、必要な DNS エントリを作成するか、必要に応じて既存のレコードを編集します。

    [Image: DNS コンソール - ドメインの管理]

警告

DNS サーバー ツールを使用してレコードを管理する場合は、Domain Services で使用される組み込みの DNS レコードを削除または変更しないようにしてください。 組み込みの DNS レコードには、ドメイン DNS レコード、ネーム サーバー レコード、および DC の場所に使用されるその他のレコードが含まれます。 これらのレコードを変更すると、仮想ネットワーク上でドメイン サービスが中断されます。

### 条件付きフォワーダーを作成する

Domain Services DNS ゾーンには、マネージド ドメイン自体のゾーンとレコードのみを含める必要があります。 他の DNS 名前空間の名前付きリソースを解決するために、マネージド ドメインに追加のゾーンを作成しないでください。 代わりに、マネージド ドメインの条件付きフォワーダーを使用して、それらのリソースのアドレスを解決するためにどこに移動するか DNS サーバーに指示します。

条件付きフォワーダーは、クエリを転送する DNS ドメイン ( *contoso.com* など) を定義できる DNS サーバーの構成オプションです。 ローカル DNS サーバーがそのドメイン内のレコードのクエリを解決する代わりに、DNS クエリはそのドメイン用に構成された DNS に転送されます。 この構成では、これらのリソースを反映するために、マネージド ドメインに重複するレコードを含む DNS ゾーンをローカルに作成しないため、正しい DNS レコードが確実に返されます。

マネージド ドメインに条件付きフォワーダーを作成するには、次の手順を実行します。

1. *aaddscontoso.com* など、DNS ゾーンを選択します。
2. [**条件付きフォワーダー**] を選択し、右クリックして [**新しい条件付きフォワーダー**] を選択します。
3. 他の **DNS ドメイン** ( *contoso.com* など) を入力し、次の例に示すように、その名前空間の DNS サーバーの IP アドレスを入力します。

    [Image: DNS サーバーの条件付きフォワーダーを追加して構成する]
4. **[この条件付きフォワーダーを Active Directory に保存**する] チェック ボックスをオンにし、次のようにレプリケートし、次の例に示すように、*このドメイン内のすべての DNS サーバー*のオプションを選択します。

    [Image: DNS コンソール - このドメイン内のすべての DNS サーバーを選択する]

    重要

    条件付きフォワーダーが*ドメイン*ではなく*フォレスト*に格納されている場合、条件付きフォワーダーは失敗します。
5. 条件付きフォワーダーを作成するには、[ **OK] を選択します**。

マネージド ドメインに接続されている VM から他の名前空間内のリソースの名前解決が正しく解決されるようになりました。 条件付きフォワーダーで構成された DNS ドメインのクエリは、関連する DNS サーバーに渡されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/manage-group-policy"} -->
## Microsoft Entra Domain Services でグループ ポリシーを作成および管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/manage-group-policy
- Service: entra-id / domain-services
- Article date: 2025-02-25
- Summary: 組み込みのグループ ポリシー オブジェクト (GPO) を編集し、Microsoft Entra Domain Services マネージド ドメインに独自のカスタム ポリシーを作成する方法について説明します。

Microsoft Entra Domain Services のユーザー オブジェクトとコンピューター オブジェクトの設定は、多くの場合、グループ ポリシー オブジェクト (GPO) を使用して管理されます。 Domain Services には、*AADDC Users* および *AADDC Computers* コンテナー用の組み込みの GPO が含まれています。 これらの組み込みの GPO をカスタマイズして、環境に合わせてグループ ポリシーを構成できます。 *AAD DC Administrators* グループのメンバーは、Domain Services ドメインのグループ ポリシー管理特権を持ち、カスタム GPO と組織単位 (OU) を作成することもできます。 グループ ポリシーの概要としくみの詳細については、「グループ ポリシーの概要 参照してください。

ハイブリッド環境では、オンプレミスの AD DS 環境で構成されたグループ ポリシーは Domain Services に同期されません。 Domain Services でユーザーまたはコンピューターの構成設定を定義するには、既定の GPO のいずれかを編集するか、カスタム GPO を作成します。

この記事では、グループ ポリシー管理ツールをインストールし、組み込みの GPO を編集してカスタム GPO を作成する方法について説明します。 GPO に変更を加えた後に、GPO をバックアップすることをお勧めします。 GPO のバックアップと復元の方法の詳細については、「グループ ポリシー オブジェクトのバックアップ、復元、移行、コピー を参照してください。

Azure 内のマシンやハイブリッド接続 など、サーバー管理戦略に関心がある場合は、Azure Policy [の](https://learn.microsoft.com/ja-jp/azure/governance/policy/overview)[ゲスト構成](https://learn.microsoft.com/ja-jp/azure/governance/machine-configuration/overview) 機能読み取りを検討してください。

### 前提条件

この記事を完了するには、次のリソースと特権が必要です。

- アクティブな Azure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、アカウント [を作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- サブスクリプションに関連付けられている Microsoft Entra テナント。オンプレミスディレクトリまたはクラウド専用ディレクトリと同期されます。
    - 必要に応じて、Microsoft Entra テナント  作成するか、Azure サブスクリプションをアカウント [に関連付ける](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)します。
- Microsoft Entra Domain Services マネージド ドメインが有効になっており、Microsoft Entra テナントで構成されている。
    - 必要に応じて、Microsoft Entra Domain Services マネージド ドメイン [を作成して構成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)チュートリアルを完了します。
- Domain Services マネージド ドメインに参加している Windows Server 管理 VM。
    - 必要に応じて、チュートリアルを完了 [Windows Server VM を作成し、マネージド ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-windows-vm)に参加させます。
- Microsoft Entra テナントの *AAD DC Administrators* グループのメンバーであるユーザー アカウント。

手記

グループ ポリシー管理用テンプレートを使用するには、新しいテンプレートを管理ワークステーションにコピーします。 *.admx* ファイルを `%SYSTEMROOT%\PolicyDefinitions` にコピーし、ロケール固有の *.adml* ファイルを `%SYSTEMROOT%\PolicyDefinitions\[Language-CountryRegion]`にコピーします。ここで、`Language-CountryRegion`*.adml* ファイルの言語とリージョンと一致します。

たとえば、*.adml* ファイルの英語、米国バージョンを `\en-us` フォルダーにコピーします。

### グループ ポリシー管理ツールのインストール

グループ ポリシー オブジェクト (GPO) を作成して構成するには、グループ ポリシー管理ツールをインストールする必要があります。 これらのツールは、Windows Server の機能としてインストールできます。 Windows クライアントに管理ツールをインストールする方法の詳細については、「リモート サーバー管理ツール (RSAT) インストールする」を参照してください。

1. 管理 VM にサインインします。 Microsoft Entra 管理センターを使用して接続する手順については、「[Windows Server VM](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-windows-vm#connect-to-the-windows-server-vm)に接続する」を参照してください。
2. **サーバー マネージャー** は、VM にサインインするときに既定で開きます。 そうでない場合は、**の [スタート]** メニューの [サーバー マネージャー 選択します。
3. *Server Manager* ウィンドウの [**ダッシュボード**] ウィンドウで、[役割と機能の追加] 選択します。
4. **役割と機能の追加ウィザードの**の [*を開始する前に*] ページで、[次へ] 選択します。
5. [*インストールの種類]*で、[ロールベースまたは機能ベースのインストール **の**] オプションをオンのままにし、[次 ] を選択します。
6. [**サーバーの選択]** ページで、サーバー プールから現在の VM (*myvm.aaddscontoso.com*など) を選択し、[次へ 選択します。
7. [**サーバーの役割**] ページで、[次 ] をクリックします。
8. [**機能]** ページで、**グループ ポリシー管理の** 機能を選択します。

    [Image: [機能] ページから [グループ ポリシー管理] をインストール]
9. [**確認**] ページで、[インストール] を選択します。 グループ ポリシー管理ツールのインストールには、1 ~ 2 分かかる場合があります。
10. 機能のインストールが完了したら、**[** を閉じる] を選択して、**役割と機能の追加** ウィザードを終了します。

### グループ ポリシー管理コンソールを開き、オブジェクトを編集する

マネージド ドメイン内のユーザーとコンピューターには、既定のグループ ポリシー オブジェクト (GPO) が存在します。 前のセクションからインストールしたグループ ポリシー管理機能を使用して、既存の GPO を表示および編集しましょう。 次のセクションでは、カスタム GPO を作成します。

手記

マネージド ドメインでグループ ポリシーを管理するには、*AAD DC Administrators* グループのメンバーであるユーザー アカウントにサインインしている必要があります。

1. [スタート] 画面で、[管理ツール] 選択します。 前のセクションでインストールしたグループ ポリシー管理  含む、使用可能な管理ツールの一覧が表示されます。
2. グループ ポリシー管理コンソール (GPMC) を開くには、グループ ポリシー管理 選択します。

    [Image: グループ ポリシー管理コンソールが開き、グループ ポリシー オブジェクトを編集]

マネージド ドメインには、2 つの組み込みのグループ ポリシー オブジェクト (GPO) があります。1 つは *AADDC Computers* コンテナー用、1 つは *AADDC Users* コンテナー用です。 これらの GPO をカスタマイズして、マネージド ドメイン内で必要に応じてグループ ポリシーを構成できます。

1. **グループ ポリシー管理** コンソールで、**フォレスト: aaddscontoso.com** ノードを展開します。 次に、**ドメイン** ノードを展開します。

    *AADDC Computers* と *AADDC Users*用の 2 つの組み込みコンテナーが存在します。 これらの各コンテナーには、既定の GPO が適用されています。

    [Image: 既定の 'AADDC Computers' コンテナーと 'AADDC Users' コンテナーに適用される組み込みの GPO]
2. これらの組み込みの GPO は、マネージド ドメインで特定のグループ ポリシーを構成するようにカスタマイズできます。 *AADDC Computers GPO*など、GPO のいずれかを右クリックし、[編集] **選択します。**.

    [Image: 組み込みの GPO] のいずれかを [編集] するオプションを選択します
3. グループ ポリシー管理エディター ツールが開き、*アカウント ポリシー*など、GPO をカスタマイズできます。

    [Image: グループ ポリシー管理エディターのスクリーンショット。]

    完了したら、[ファイル **&gt; 保存** を選択してポリシーを保存します。 既定では、コンピューターは 90 分ごとにグループ ポリシーを更新し、行った変更を適用します。

### カスタム グループ ポリシー オブジェクトを作成する

同様のポリシー設定をグループ化するには、多くの場合、1 つの既定の GPO で必要なすべての設定を適用するのではなく、追加の GPO を作成します。 Domain Services を使用すると、独自のカスタム グループ ポリシー オブジェクトを作成またはインポートし、それらをカスタム OU にリンクできます。 最初にカスタム OU を作成する必要がある場合は、マネージド ドメイン カスタム OU を作成する方法に関するページを参照してください。

1. **グループ ポリシー管理** コンソールで、myCustomOU *などのカスタム組織単位 (OU)*選択します。 OU を右クリックし、[このドメインに GPO を作成 **選択し、ここにリンクします。..**:

    [Image: グループ ポリシー管理コンソールでカスタム GPO を作成]
2. [マイ カスタム GPO *] など、新しい GPO の名前*指定し、[OK] 選択します。 必要に応じて、このカスタム GPO を既存の GPO と一連のポリシー オプションに基づいて作成できます。

    [Image: 新しいカスタム GPO] の名前を指定する
3. カスタム GPO が作成され、カスタム OU にリンクされます。 ポリシー設定を構成するには、カスタム GPO を右クリックし、[編集] **選択します。**:

    [Image: カスタム GPO] を [編集] するオプションを選択します
4. **グループ ポリシー管理エディター** が開き、GPO をカスタマイズできます。

    [Image: 必要に応じて GPO をカスタマイズして設定を構成]

    完了したら、[ファイル **&gt; 保存** を選択してポリシーを保存します。 既定では、コンピューターは 90 分ごとにグループ ポリシーを更新し、行った変更を適用します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/mismatched-tenant-error"} -->
## Microsoft Entra Domain Services での一致しないディレクトリ エラーの修正 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/mismatched-tenant-error
- Service: entra-id / domain-services
- Article date: 2025-02-05
- Summary: ディレクトリの不一致エラーの意味と、Microsoft Entra Domain Services で解決する方法について説明します

Microsoft Entra Domain Services マネージド ドメインでテナント エラーが一致しない場合は、解決されるまでマネージド ドメインを管理できません。 このエラーは、基になる Azure 仮想ネットワークが別の Microsoft Entra ディレクトリに移動された場合に発生します。

この記事では、エラーが発生する理由とその解決方法について説明します。

### このエラーの原因

ディレクトリ の不一致エラーは、Domain Services マネージド ドメインと仮想ネットワークが 2 つの異なる Microsoft Entra テナントに属している場合に発生します。 たとえば、Contoso の Microsoft Entra テナントで実行される *aaddscontoso.com* というマネージド ドメインがあるとします。 ただし、マネージド ドメイン用の Azure 仮想ネットワークは、Fabrikam Microsoft Entra テナントの一部です。

Azure ロールベースのアクセス制御 (Azure RBAC) は、リソースへのアクセスを制限するために使用されます。 Microsoft Entra テナントで Domain Services を有効にすると、資格情報ハッシュがマネージド ドメインに同期されます。 この操作では、Microsoft Entra ディレクトリのテナント管理者である必要があり、資格情報へのアクセスを制御する必要があります。

Azure 仮想ネットワークにリソースをデプロイし、トラフィックを制御するには、マネージド ドメインをデプロイする仮想ネットワークに対する管理特権が必要です。

Azure RBAC が一貫して機能し、Domain Services が使用するすべてのリソースへの安全なアクセスを行うには、マネージド ドメインと仮想ネットワークが同じ Microsoft Entra テナントに属している必要があります。

デプロイには次の規則が適用されます。

- Microsoft Entra ディレクトリには、複数の Azure サブスクリプションが含まれる場合があります。
- Azure サブスクリプションには、仮想ネットワークなどの複数のリソースが含まれる場合があります。
- Microsoft Entra ディレクトリでは、1 つのマネージド ドメインが有効になります。
- マネージド ドメインは、同じ Microsoft Entra テナント内の任意の Azure サブスクリプションに属する仮想ネットワークで有効にすることができます。

#### 有効な構成

次のデプロイ シナリオの例では、Contoso Microsoft Entra テナントで Contoso マネージド ドメインが有効になっています。 マネージド ドメインは、Contoso Microsoft Entra テナントが所有する Azure サブスクリプションに属する仮想ネットワークにデプロイされます。

マネージド ドメインと仮想ネットワークの両方が、同じ Microsoft Entra テナントに属しています。 この構成例は有効であり、完全にサポートされています。

[Image: 同じ Microsoft Entra テナントのマネージド ドメインと仮想ネットワークの一部を使用した、有効な Domain Services テナント構成]

#### テナント構成の不一致

このデプロイ シナリオの例では、Contoso のマネージド ドメインが Contoso Microsoft Entra テナントで有効になっています。 ただし、マネージド ドメインは、Fabrikam Microsoft Entra テナントが所有する Azure サブスクリプションに属する仮想ネットワークにデプロイされます。

マネージド ドメインと仮想ネットワークは、2 つの異なる Microsoft Entra テナントに属しています。 この構成例はテナントの不一致であり、サポートされていません。 仮想ネットワークは、マネージド ドメインと同じ Microsoft Entra テナントに移動する必要があります。

[Image: テナント設定の不一致]

### テナントの不一致エラーを解決する

次の 2 つのオプションは、ディレクトリの不一致エラーを解決します。

- まず、[既存の Microsoft Entra ディレクトリからマネージド ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/delete-aadds) を削除します。 次 [、使用する仮想ネットワークと同じ Microsoft Entra ディレクトリに、代替のマネージド ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance) を作成します。 準備ができたら、以前に削除されたドメインに参加していたすべてのマシンを、再作成されたマネージド ドメインに参加させます。
- [仮想ネットワークを含む Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/billing-subscription-transfer) をマネージド ドメインと同じ Microsoft Entra ディレクトリに移動します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/network-considerations"} -->
## Microsoft Entra Domain Services のネットワーク計画と接続 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/network-considerations
- Service: entra-id / domain-services
- Article date: 2025-02-05
- Summary: Microsoft Entra Domain Services を実行するときの仮想ネットワーク設計の考慮事項と接続に使用されるリソースについて説明します。

Microsoft Entra Domain Services から、他のアプリケーションおよびワークロードに認証および管理サービスが提供されます。 ネットワーク接続は重要なコンポーネントです。 仮想ネットワークリソースが正しく構成されていないと、アプリケーションとワークロードは、Domain Services によって提供される機能と通信して使用することができません。 仮想ネットワークの要件を計画し、Domain Services が必要に応じてアプリケーションとワークロードにサービスを提供できることを確認します。

この記事では、Domain Services をサポートするための Azure 仮想ネットワークの設計の考慮事項と要件について説明します。

### Azure 仮想ネットワークの設計

ネットワーク接続を提供し、アプリケーションとサービスが Domain Services マネージド ドメインに対して認証できるようにするには、Azure 仮想ネットワークとサブネットを使用します。 マネージド ドメインを独自の仮想ネットワークにデプロイするのが理想的です。

同じ仮想ネットワーク内に別のアプリケーション サブネットを追加して、管理 VM または軽量なアプリケーション ワークロードをホストすることができます。 Domain Services 仮想ネットワークとピアリングされた大規模または複雑なアプリケーション ワークロードの場合は、通常、別の仮想ネットワークが最適な設計です。

以下のセクションに記載されている仮想ネットワークとサブネットの要件を満たしている場合は、その他の設計の選択肢が有効です。

Domain Services の仮想ネットワークを設計する際には、次の考慮事項が適用されます。

- Domain Services は、仮想ネットワークと同じ Azure リージョンにデプロイする必要があります。
    - 現時点では、Microsoft Entra テナントごとにデプロイできるマネージド ドメインは 1 つのみです。 マネージド ドメインは、1 つのリージョンにデプロイされます。 仮想ネットワークは、必ず [Domain Services をサポートするリージョン](https://azure.microsoft.com/global-infrastructure/services/?products=active-directory-ds&amp;regions=all)で作成または選択します。
- 他の Azure リージョンとアプリケーション ワークロードをホストする仮想ネットワークの距離を考慮します。
    - 待機時間を最小限に抑えるには、マネージド ドメインの仮想ネットワーク サブネットの近く、または同じリージョンにコア アプリケーションを保持します。 Azure 仮想ネットワーク間には、仮想ネットワーク ピアリングまたは仮想プライベート ネットワーク (VPN) 接続を使用できます。 以下のセクションでは、これらの接続オプションについて説明します。
- 仮想ネットワークは、マネージド ドメインが提供するサービス以外の DNS サービスに依存することはできません。
    - Domain Services は独自の DNS サービスを提供しています。 これらの DNS サービス アドレスを使用するように仮想ネットワークを構成する必要があります。 追加の名前空間の名前解決は、条件付きフォワーダーを使用して実現できます。
    - カスタム DNS サーバーの設定を使用して、VM などの他の DNS サーバーからのクエリを送信することはできません。 仮想ネットワーク内のリソースでは、マネージド ドメインから提供される DNS サービスを使用する必要があります。

重要

サービスを有効にした後、Domain Services を別の仮想ネットワークに移行することはできません。

マネージド ドメインは、Azure 仮想ネットワーク内のサブネットに接続します。 次の点を考慮して、Domain Services 用にこのサブネットを設計します。

- マネージド ドメインは、独自のサブネットにデプロイする必要があります。 仮想ネットワーク ピアリングで既存のサブネット、ゲートウェイ サブネット、またはリモート ゲートウェイの設定を使用することはサポートされていません。
- マネージド ドメインのデプロイ時に、ネットワーク セキュリティ グループが作成されます。 このネットワーク セキュリティ グループには、サービス通信を正しく行うために必要な規則が含まれています。

    - 独自のカスタム規則を持つ既存のネットワーク セキュリティ グループを作成または使用しないでください。
- マネージド ドメインには、3 ～ 5 個の IP アドレスが必要です。 サブネットの IP アドレス範囲でこの数のアドレスを提供できることを確認してください。

    - 使用可能な IP アドレスを制限すると、マネージド ドメインで 2 つのドメイン コントローラーを維持できなくなる可能性があります。

    注

    次の問題のため、仮想ネットワークとそのサブネットにはパブリック IP アドレスを使用しないでください。

    - **IP アドレスの不足**: IPv4 パブリック IP アドレスは限られており、その需要は利用可能な供給を超えることがよくあります。 また、IP がパブリック エンドポイントと重複する可能性があります。
    - **セキュリティ リスク**: 仮想ネットワークにパブリック IP を使用すると、デバイスがインターネットに直接公開されるため、不正アクセスや潜在的な攻撃のリスクが高まります。 適切なセキュリティ対策を講じないと、デバイスがさまざまな脅威に対して脆弱になる可能性があります。
    - **複雑さ**: パブリック IP を使用した仮想ネットワークの管理は、外部 IP 範囲を処理し、ネットワークの適切なセグメンテーションとセキュリティを確保する必要があるため、プライベート IP を使用する場合よりも複雑になる可能性があります。

    プライベート IP アドレスを使用することを強くお勧めします。 パブリック IP を使用する場合は、選択したパブリック範囲内で選択した IP の所有者/専用ユーザーであることを確認してください。

次の図の例は、マネージド ドメインに独自のサブネットがあり、外部接続用にゲートウェイ サブネットがあり、アプリケーション ワークロードが仮想ネットワーク内の接続されたサブネットにある有効な設計を示しています。

[Image: 推奨されるサブネットの設計]

### Domain Services 仮想ネットワークへの接続

前のセクションで説明したように、マネージド ドメインは、Azure の 1 つの仮想ネットワークにのみ作成できます。また、Microsoft Entra テナントごとに作成できるマネージド ドメインは 1 つのみです。 このアーキテクチャに基づいて、アプリケーション ワークロードをホストする 1 つ以上の仮想ネットワークをマネージド ドメインの仮想ネットワークに接続することが必要になる場合があります。

次のいずれかの方法を使用して、他の Azure 仮想ネットワークでホストされているアプリケーション ワークロードを接続できます。

- 仮想ネットワーク ピアリング
- 仮想プライベート ネットワーク (VPN)

#### 仮想ネットワーク ピアリング

仮想ネットワーク ピアリングは、2 つの仮想ネットワークを Azure バックボーン ネットワークを介して接続するメカニズムです。これにより、仮想マシン (VM) などのリソースが、プライベート IP アドレスを使って相互に直接通信することができます。 仮想ネットワーク ピアリングでは、同じ Azure リージョン内の VNet を接続するリージョン ピアリングと、さまざまな Azure リージョン間で VNet を接続するグローバル仮想ネットワーク ピアリングの両方をサポートしています。 この柔軟性により、アプリケーション ワークロードを含むマネージド ドメインを、地理的な場所に関係なく複数の仮想ネットワークにわたってデプロイできます。

[Image: ピアリングを使用した仮想ネットワーク接続]

詳細については、[Azure 仮想ネットワークのピアリングの概要](https://learn.microsoft.com/ja-jp/azure/virtual-network/virtual-network-peering-overview)に関するページを参照してください。

#### 仮想プライベート ネットワーク (VPN)

仮想ネットワークをオンプレミス サイトの場所に構成する場合と同じ方法で、仮想ネットワークを別の仮想ネットワークに (VNet 間) 接続することができます。 どちらの接続でも、VPN ゲートウェイを使用して、IPsec/IKE を使用してセキュリティで保護されたトンネルを作成します。 この接続モデルを使用すると、マネージド ドメインを Azure 仮想ネットワークにデプロイし、オンプレミスの場所または他のクラウドに接続することができます。

[Image: VPN Gateway を使用した仮想ネットワーク接続]

仮想プライベート ネットワークの使用方法の詳細については、「[Microsoft Entra 管理センターを使用して VNet 間 VPN ゲートウェイ接続を構成する](https://learn.microsoft.com/ja-jp/azure/vpn-gateway/vpn-gateway-howto-vnet-vnet-resource-manager-portal)」を参照してください。

### 仮想ネットワークを接続するときの名前解決

マネージド ドメインの仮想ネットワークに接続される仮想ネットワークには、通常、独自の DNS 設定があります。 仮想ネットワークに接続しても、接続中の仮想ネットワークがマネージド ドメインから提供されるサービスを解決するための名前解決は自動的に構成されません。 アプリケーション ワークロードがマネージド ドメインを見つけられるように、接続している仮想ネットワークの名前解決を構成する必要があります。

名前解決を有効にするには、接続している仮想ネットワークをサポートする DNS サーバー上で条件付き DNS フォワーダーを使用するか、マネージド ドメインの仮想ネットワークと同じ DNS IP アドレスを使用します。

### Domain Services によって使用されるネットワーク リソース

マネージド ドメインでは、デプロイ時にいくつかのネットワーク リソースが作成されます。 これらのリソースは、マネージド ドメインの正常な運用と管理のために必要であり、手動で構成することはできません。

Domain Services によって使用されるネットワーク リソースをロックしないでください。 ネットワーク リソースがロックされている場合は、そのリソースを削除できません。 その場合にドメイン コントローラーを再構築する必要があるとき、IP アドレスの異なる新しいネットワーク リソースを作成する必要があります。

| Azure リソース | 説明 |
| --- | --- |
| ネットワーク インターフェイス カード | Domain Services は、Windows Server 上で Azure VM として実行されている 2 つのドメイン コントローラー (DC) 上でマネージド ドメインをホストします。 各 VM には、仮想ネットワークのサブネットに接続する仮想ネットワーク インターフェイスがあります。 |
| 動的標準パブリック IP アドレス | Domain Services は、Standard SKU のパブリック IP アドレスを使用して同期および管理サービスと通信します。 パブリック IP アドレスの詳細については、「[Azure における IP アドレスの種類と割り当て方法](https://learn.microsoft.com/ja-jp/azure/virtual-network/ip-services/public-ip-addresses)」を参照してください。 |
| Azure Standard Load Balancer | Domain Services では、ネットワーク アドレス変換 (NAT) および負荷分散 (セキュリティで保護された LDAP と共に使用する場合) に Standard SKU のロード バランサーを使用します。 Azure Load Balancer の詳細については、[Azure Load Balancer の概要](https://learn.microsoft.com/ja-jp/azure/load-balancer/load-balancer-overview)に関する記事を参照してください。 |
| ネットワーク アドレス変換 (NAT) 規則 | Domain Services は、安全な PowerShell リモート処理のために、ロード バランサーで 2 つのインバウンド NAT 規則を作成して使用します。 Standard SKU ロード バランサーが使用されている場合は、アウトバウンド NAT 規則もあります。 Basic SKU ロードバランサーでは、アウトバウンド NAT 規則は必要ありません。 |
| 負荷分散規則 | マネージド ドメインが TCP ポート 636 上のセキュリティで保護された LDAP 用に構成されている場合、トラフィックを分散する 3 つの規則がロード バランサーに対して作成され、使用されます。 |

警告

ロード バランサーやルールの手動での構成など、Domain Services によって作成されたネットワーク リソースは削除または変更しないでください。 ネットワーク リソースのいずれかを削除または変更すると、Domain Services サービスの停止が発生することがあります。

### ネットワーク セキュリティ グループと必要なポート

[ネットワーク セキュリティ グループ (NSG)](https://learn.microsoft.com/ja-jp/azure/virtual-network/network-security-groups-overview) には、Azure 仮想ネットワーク内のネットワーク トラフィックを許可または拒否するルールの一覧が含まれています。 マネージド ドメインを展開すると、サービスが認証および管理機能を提供できるようにする一連の規則を含むネットワーク セキュリティ グループが作成されます。 この既定のネットワーク セキュリティ グループは、マネージド ドメインがデプロイされる仮想ネットワーク サブネットに関連付けられています。

次のセクションでは、ネットワーク セキュリティ グループと、受信ポートおよび送信ポートの要件について説明します。

#### 受信接続

マネージド ドメインで認証と管理サービスを提供するには、次のネットワーク セキュリティ グループの受信規則が必要です。 マネージド ドメインの仮想ネットワーク サブネットのネットワーク セキュリティ グループ規則は編集も削除もしないでください。

| Source | 発信元サービス タグ | 送信ポート範囲 | 宛先 | Service | 宛先ポート範囲 | プロトコル | アクション | 必須 | 目的 |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| サービス タグ | AzureActiveDirectoryDomainServices | \* | [任意] | WinRM | 5986 | TCP | 許可する | あり | ドメインの管理。 |
| サービス タグ | CorpNetSaw | \* | [任意] | RDP | 3389 | TCP | 許可する | オプション | サポートのためのデバッグ |

Microsoft Entra 管理センターを使用して **CorpNetSaw** サービス タグを使用できないことに注意してください。また、**PowerShell** を使用して [CorpNetSaw](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/powershell-create-instance#create-a-network-security-group) のネットワーク セキュリティ グループ規則を追加する必要があります。

Domain Services は、既定のセキュリティ規則である AllowVnetInBound と AllowAzureLoadBalancerInBound にも依存しています。

[Image: ネットワーク セキュリティ グループ規則のスクリーンショット。]

AllowVnetInBound 規則により、VNet 内のすべてのトラフィックが許可され、DC で適切に通信およびレプリケートできるほか、ドメイン参加やその他のドメイン サービスをドメイン メンバーに許可できます。 Windows の必須のポート範囲の詳細については、「[Windows のサービス概要およびネットワーク ポート要件](https://learn.microsoft.com/ja-jp/troubleshoot/windows-server/networking/service-overview-and-network-port-requirements)」を参照してください。

サービスがロードバランスを介して適切に通信して DC を管理できるように AllowAzureLoadBalancerInBound 規則も必須となります。 このネットワーク セキュリティ グループは Domain Services を保護し、マネージド ドメインが正しく機能するために必要です。 このネットワーク セキュリティ グループを削除しないでください。 これがないと、ロード バランサーは正常に機能しません。

必要に応じて、[Azure PowerShell を使用して必要なネットワーク セキュリティ グループとルールを作成する](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/powershell-create-instance#create-a-network-security-group)ことができます。

警告

正しく構成されていないネットワーク セキュリティ グループまたはユーザー定義のルート テーブルを、マネージド ドメインが展開されているサブネットに関連付けると、Microsoft のドメインのサービスと管理の機能が中断する可能性があります。 Microsoft Entra テナントとマネージド ドメインの間の同期も中断されます。 同期、修正プログラムの適用、または管理を中断する可能性のあるサポートされていない構成を回避するために、リストされているすべての要件に従います。

Secure LDAP を使用する場合は、必要な TCP ポート 636 の規則を適宜追加することで、外部トラフィックを許可することができます。 この規則を追加しても、ネットワーク セキュリティ グループの規則がサポート対象外の状態に設定されることはありません。 詳細については、「[インターネット経由での Secure LDAP アクセスをロック ダウンする](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-ldaps#lock-down-secure-ldap-access-over-the-internet)」を参照してください。

Azure SLA は、不適切に構成されたネットワーク セキュリティ グループまたはユーザー定義のルート テーブルによって更新または管理がブロックされているデプロイには適用されません。 ネットワーク構成が壊れていると、セキュリティ パッチの適用が妨げられる可能性もあります。

#### 送信接続

送信接続の場合、**AllowVnetOutbound** および **AllowInternetOutBound** を維持するか、次の表にリストされている ServiceTag を使用して送信トラフィックを制限できます。 [ログ分析](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/logs-data-export) を使用する場合は、**EventHub** を送信先に追加します。

優先度が高い他の NSG が送信接続を拒否していないことをご確認ください。 送信接続が拒否された場合、レプリカ セット間のレプリケーションは機能しません。

| 送信ポート番号 | プロトコル | Source | 宛先 | アクション | 必須 | 目的 |
| --- | --- | --- | --- | --- | --- | --- |
| 443 | TCP | [任意] | AzureActiveDirectoryDomainServices | 許可する | あり | Microsoft Entra Domain Services 管理サービスとの通信。 |
| 443 | TCP | [任意] | AzureMonitor | 許可する | あり | 仮想マシンの監視。 |
| 443 | TCP | [任意] | Storage | 許可する | あり | Azure Storage との通信。 |
| 443 | TCP | [任意] | Azure Active Directory | 許可する | あり | Microsoft Entra ID との通信。 |
| 443 | TCP | [任意] | GuestAndHybridManagement | 許可する | あり | セキュリティ パッチの自動管理。 |

注

AzureUpdateDelivery タグと AzureFrontDoor.FirstParty タグは、2024 年 7 月 1 日の時点で非推奨となっています。 Microsoft Entra Domain Services が WindowsUpdate を個別に管理するため、これらのタグは必要ありません。 NSG 調整は、非推奨のタグの有無にかかわらず不要です。

#### ポート 5986 - PowerShell リモート処理を使用した管理

- PowerShell のリモート処理を使用してマネージド ドメインの管理タスクを実行するために使用されます。
- このポートにアクセスできない場合、マネージド ドメインの更新、構成、バックアップおよび監視は行えません。
- このポートへの受信アクセスを *AzureActiveDirectoryDomainServices* サービス タグに制限できます。

#### ポート 3389 - リモート デスクトップを使用した管理

- マネージド ドメインのドメイン コントローラーへのリモート デスクトップ接続に使用されます。このポートは変更したり、別のポートにカプセル化したりすることはできません。
- 既定のネットワーク セキュリティ グループの規則では、*CorpNetSaw*サービス タグを使用してトラフィックがさらに制限されます。
    - このサービス タグでは、Microsoft 企業ネットワーク上のセキュリティで保護されたアクセス ワークステーションのみが、マネージド ドメインへのリモート デスクトップを使用できます。
    - アクセスは、管理やトラブルシューティングのシナリオなど、業務上の正当な理由でのみ許可されます。
- この規則を *[拒否]* に設定し、必要な場合にのみ *[許可]* に設定することができます。 ほとんどの管理タスクと監視タスクは、PowerShell リモート処理を使用して実行されます。 RDP は、Microsoft が高度なトラブルシューティングのためにマネージド ドメインへのリモート接続が必要になるような頻度の低いイベントでのみ使用されます。

このネットワーク セキュリティ グループの規則を編集しようとすると、ポータルから *CorpNetSaw* サービス タグを手動で選択することはできません。 *CorpNetSaw* サービス タグを使用する規則を手動で構成するには、Azure PowerShell または Azure CLI を使用する必要があります。

たとえば、次のスクリプトを使用して、RDP を許可する規則を作成できます。

```powershell
Get-AzNetworkSecurityGroup -Name "nsg-name" -ResourceGroupName "resource-group-name" | Add-AzNetworkSecurityRuleConfig -Name "new-rule-name" -Access "Allow" -Protocol "TCP" -Direction "Inbound" -Priority "priority-number" -SourceAddressPrefix "CorpNetSaw" -SourcePortRange "*" -DestinationPortRange "3389" -DestinationAddressPrefix "*" | Set-AzNetworkSecurityGroup
```

#### その他のポート - セカンダリ コントローラーとの同期、バックアップ

| クライアント ポート | サーバー ポート | Service |
| --- | --- | --- |
| 1024-65535/TCP | 135/TCP | RPC エンドポイント マッパー |
| 1024-65535/TCP | 1024-65535/TCP | LSA、SAM、NetLogon 用の RPC |
| 1024-65535/TCP/UDP | 389/TCP/UDP | LDAP |
| 1024-65535/TCP | 636/TCP | LDAP SSL（LDAPのSSL暗号化） |
| 1024-65535/TCP | 3268/TCP | LDAP グローバルカタログ (GC) |
| 1024-65535/TCP | 3269/TCP | LDAP GC SSL |
| 53,1024-65535/TCP/UDP | 53/TCP/UDP | DNS |
| 1024-65535/TCP/UDP | 88/TCP/UDP | Kerberos |
| 1024-65535/TCP | 445/TCP | SMB |
| 1024-65535/TCP | 1024-65535/TCP | FRS RPC |

- ファイアウォール規則またはネットワーク セキュリティ ポリシーを構成する場合は、セカンダリ コントローラーとの同期のために他のポートを検討することが重要です。
- これらのポートにトラフィック制限が実装されている場合は、サービスのコントローラーによって使用される IP アドレスまたはアドレス指定範囲の間のトラフィックを拒否しないことが不可欠です。
- **コントローラー間でこれらのポートを介した通信をブロックすると、レプリケーションとデータ同期が正しく機能しなくなります。** これにより、バックアップ プロセスでエラーが発生します。
- サービスの整合性と可用性を保証するために、セキュリティ ポリシーでこの内部通信が明示的に許可されていることを確認します。

詳細については、「 [Active Directory ドメインと信頼のファイアウォールを構成する方法](https://learn.microsoft.com/ja-jp/troubleshoot/windows-server/active-directory/config-firewall-for-ad-domains-and-trusts)」を参照してください。

### ユーザー定義のルート

ユーザー定義ルートは、既定では作成されず、Domain Services が正しく機能するためには必要ありません。 ルート テーブルを使用する必要がある場合は、*0.0.0.0* ルートに変更を加えないようにしてください。 このルートを変更すると、Domain Services が中断され、マネージド ドメインがサポートされない状態になります。

また、それぞれの Azure サービス タグに含まれる IP アドレスからの受信トラフィックをマネージド ドメインのサブネットにルーティングする必要があります。 サービス タグとそれに関連付けられている IP アドレスの詳細については、「[Azure の IP 範囲とサービス タグ - パブリック クラウド](https://www.microsoft.com/en-us/download/details.aspx?id=56519)」を参照してください。

注意事項

これらの Azure データセンターの IP 範囲は、予告なしに変わる可能性があります。 最新の IP アドレスを取得していることを検証するプロセスを用意してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/notifications"} -->
## Microsoft Entra Domain Services のメール通知 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/notifications
- Service: entra-id / domain-services
- Article date: 2025-02-05
- Summary: Microsoft Entra Domain Services のマネージド ドメインでの問題について警告するメール通知を構成する方法について説明します

Microsoft Entra Domain Services マネージド ドメインの正常性は Azure プラットフォームによって監視されています。 マネージド ドメインのアラートがある場合、Microsoft Entra 管理センターの正常性状態ページに表示されます。 適切なタイミングで問題に応答するために、Domain Services マネージド ドメインで問題が検出されたらすぐに正常性アラートでレポートするようにメール通知を構成できます。

この記事では、マネージド ドメイン用にメール通知受信者を構成する方法について説明します。

### メール通知の概要

マネージド ドメインでの問題を警告するために、メール通知を構成できます。 これらのメール通知では、アラートが存在するマネージド ドメインのほか、検出時刻や Microsoft Entra 管理センターの正常性ページへのリンクが提供されます。 提供されているトラブルシューティングのアドバイスに従って、問題を解決できます。

次のメール通知の例は、マネージド ドメインで重大な警告またはアラートが生成されたことを示しています。

[Image: メール通知の例]

警告

メッセージ内のリンクをクリックする前に、メールが確認済みの Microsoft 送信者から送られてきたものであることを必ず確認してください。 メール通知は、常に `azure-noreply@microsoft.com` アドレスから送信されます。

#### メール通知が送信される理由

Domain Services は、マネージド ドメインに関する重要な更新情報についてメール通知を送信します。 これらの通知は、サービスに影響があり、すぐに対処する必要がある、緊急の問題に限られます。 それぞれのメール通知は、マネージド ドメイン上のアラートによってトリガーされます。 また、アラートは Microsoft Entra 管理センターにも表示され、[Domain Services の正常性ページ](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/check-health)で確認できます。

Domain Services から、広告、更新プログラム、または営業目的のメールが送信されることはありません。

#### メール通知が送信されるタイミング

通知は、マネージド ドメインで[新しいアラート](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/troubleshoot-alerts)が検出されると、直ちに送信されます。 アラートが解決されない場合は、4 日に 1 回、新たな通知がリマインダーとして送信されます。

#### メール通知の宛先

Domain Services のメール受信者のリストは、マネージド ドメインを管理および変更できるユーザーで構成する必要があります。 このメール リストは、すべてのアラートや問題に対する "最初のレスポンダー" であると考えてください。

メール通知の受信者を 5 人まで追加することができます。 電子メールによる通知の受信者を 6 人以上にしたい場合は、配布リストを作成し、代わりにそれを通知リストに追加します。

また、Microsoft Entra ディレクトリの高い特権ロール所有者と *AAD DC Administrators* グループのすべてのメンバーがメール通知を受信するようにすることもできます。 Domain Services から通知を送信できるメール アドレスは最大 100 件です (高い特権ロール所有者と AAD DC Administrators のリストを含む)。

### メール通知を構成する

メール通知の既存の受信者を確認したり、受信者を追加したりするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. **[Microsoft Entra Domain Services]** を検索して選択します。
3. 目的のマネージド ドメインを選択します (例: *aaddscontoso.com*)。
4. Domain Services リソース ウィンドウの左側で、**[通知設定]** を選択します。 メール通知の既存の受信者が表示されます。
5. メールの受信者を追加するには、[その他の受信者] テーブルにメール アドレスを入力します。
6. 終了したら、最上部のナビゲーションで **[保存]** を選択します。

警告

通知設定を変更すると、自分の通知設定だけでなく、マネージド ドメイン全体の通知設定が更新されます。

### よく寄せられる質問

#### アラートのメール通知を受信しましたが、Microsoft Entra 管理センターにログオンしてもアラートが見当たりません。 何が起きましたか?

アラートが解決されると、アラートは Microsoft Entra 管理センターからクリアされます。 最も可能性が高い理由として、マネージド ドメインのアラートがメール通知を受け取った他の受信者によって解決されたか、Azure プラットフォームによって自動的に解決されたことが考えられます。

#### 通知設定を編集できないのはなぜですか?

Microsoft Entra 管理センターの通知設定ページにアクセスできない場合は、マネージド ドメインを編集するアクセス許可がありません。 高い権限を持つ管理者に連絡して Domain Services のリソースを編集するためのアクセス許可を取得するか、受信者リストから削除してもらう必要があります。

#### メール アドレスを指定したにもかかわらず、電子メールによる通知が届かないようです。 なぜですか?

通知が迷惑メール フォルダーに入っていないことを確認し、`azure-noreply@microsoft.com` を送信者として許可してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/overview"} -->
## Microsoft Entra Domain Services の概要 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/overview
- Service: entra-id / domain-services
- Article date: 2026-02-16
- Summary: この概要では、Microsoft Entra Domain Services が提供する内容と、それを組織内で使用して、クラウド内のアプリケーションとサービスに ID サービスを提供する方法について説明します。

Microsoft Entra Domain Services は、ドメイン参加、グループ ポリシー、ライトウェイト ディレクトリ アクセス プロトコル (LDAP)、Kerberos/NTLM 認証などのマネージド ドメイン サービスを提供します。 クラウドでドメイン コントローラー (DC) のデプロイ、管理、修正プログラムの適用を行わなくても、これらのドメイン サービスを使用することができます。

Domain Services を使用して、Active Directory Domain Services (AD DS) プロトコルに依存し、先進認証を使用するように簡単に変更できないレガシ アプリケーションをサポートします。 Azureのマネージド ドメインに対してこれらのアプリケーションを実行することで、Azureでオンプレミスの AD DS インフラストラクチャを拡張または運用することなく、AD 依存ワークロードをクラウドにリフト アンド シフトできます。

Domain Services は、ユーザー、グループ、および資格情報の権限のソースとして機能するMicrosoft Entra IDと統合されます。 この統合により、ユーザーは既存の ID を使用してマネージド ドメインに接続されているアプリケーションとサービスにサインインでき、Microsoft Entra IDで ID 管理を一元化できます。

Microsoft Entra Domain Services の詳細については、短いビデオをご覧ください。

### Microsoft Entra Domain Services を使用する場合

Domain Services は、AD DS プロトコルに依存するアプリケーションまたはワークロードを実行する組織が、Microsoft Entra IDを使用して ID とアクセスの管理を最新化するのに役立ちます。

次のシナリオでは、Domain Services の使用を検討する必要があります。

- **リフト アンド シフトレガシ アプリケーション:** LDAP、Kerberos、NTLM、グループ ポリシー、またはドメイン参加に依存するアプリケーションがオンプレミスで実行されている。 これらのワークロードをクラウドに移行するが、OIDC や OAuth などの先進認証をサポートするように書き換えないようにする必要がある。
- **オンプレミスのフットプリントを削減する:** 組織は、ワークロードをクラウドに移行し、オンプレミスのハードウェアを廃止したいと考えています。 オンプレミスの AD DS への認証トラフィックのみにサイト間 VPN 接続を維持する代わりに、Azureでマネージド ドメインを使用できます。
- ** AD 最小化戦略を実装する:** Microsoft Entra IDがプライマリ ID プロバイダーであるクラウドファースト戦略を採用しています。 Domain Services では、残りのレガシ AD DS 依存ワークロードがサポートされており、古い AD DS アーティファクトをAzureに拡張する必要がなくなります。
- **レガシ ワークロードの分散:** 新しいアプリケーションでMicrosoft Entra IDを直接使用しながら、AD DS に依存するワークロードをAzureに維持する必要があります。
- **クラウドのみのレガシ サポート:** オンプレミスの AD DS がないクラウドネイティブの組織ですが、LDAP または従来のドメイン参加を必要とする特定のサードパーティ アプリケーションがあります。 Domain Services は、完全な AD DS インフラストラクチャの構築を強制することなく、このギャップを埋めます。

Domain Services は、on-premises Active Directoryの一般的な代替機能やクラウドネイティブ ID サービスの代わりとしてではなく、レガシ認証を必要とするAzureホスト型ワークロードの移行機能として使用できます。

これらのシナリオの詳細については、[Microsoft Entra Domain Services のユース ケースとシナリオ](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/scenarios)を参照してください。

### クラウド体験でのドメイン サービスのMicrosoft Entra

Entra Domain Services は、"AD 最小化" という目標を達成するのに役立ちます。従来の ID インフラストラクチャへの依存を減らし、Entra ID などの最新のクラウドベースの ID を優先します。

Entra Domain Services は、この体験のブリッジとして機能します。

- **最新のアプリ:** 新しいクラウドネイティブ アプリでは、Entra ID を使用する必要があります。
- **Legacy apps:** Entra Domain Services を使用してAzureで簡単に最新化できない古いアプリを実行します。

この方法により、Azure Virtual Machines (IaaS) に AD DC を手動でデプロイおよび管理する際の複雑さとセキュリティの負担が回避されます。 Domain Services を使用すると、Id 管理を Entra ID で一元化したまま、レガシ アプリに必要な互換性を確保できます。

Microsoft の推奨されるアプローチの詳細については、 [クラウド変換の体制](https://learn.microsoft.com/ja-jp/entra/architecture/road-to-the-cloud-posture)に関するページを参照してください。

### Microsoft Entra Domain Services のしくみ

Domain Services マネージド ドメインを作成するときは、dscontoso.com などの一意の名前空間 *を*定義します。 2 つのWindows Server ドメイン コントローラーが、レプリカ セットの一部として選択したAzure リージョンにデプロイされます。

これらのドメイン コントローラーは、Microsoft によって完全に管理されています。 構成、修正プログラムの適用、監視を行う必要はありません。 プラットフォームは、可用性、バックアップ、保存時の暗号化を処理します。

マネージド ドメインは、Microsoft Entra IDから一方向の同期を実行するように構成されます。 Microsoft Entra IDのユーザー、グループ、資格情報はマネージド ドメインで使用できるようになり、Azureのアプリケーション、サービス、仮想マシンは、ドメイン参加、グループ ポリシー、LDAP、Kerberos/NTLM 認証などの使い慣れた AD DS 機能を使用できます。

ハイブリッド環境では、オンプレミスの AD DS からの ID 情報がMicrosoft Entra IDに同期され、マネージド ドメインで使用できるようになります。

Domain Services は、クラウド専用のMicrosoft Entra テナントと、オンプレミスの AD DS 環境と同期されたテナントの両方で機能します。 どちらのシナリオでも、同じマネージド ドメイン機能のセットを使用できます。

また、複数のレプリカ セットを Azure リージョンにデプロイして可用性を向上させ、レガシ アプリケーションのディザスター リカバリー シナリオをサポートすることもできます。 詳細については、 [マネージド ドメインのレプリカ セットの概念と機能に関する説明を](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/concepts-replica-sets)参照してください。

### Microsoft Entra Domain Services の機能と利点

クラウド内のアプリケーションと VM に ID サービスを提供するために、Domain Services は、ドメイン参加、Secure LDAP (LDAPS)、グループ ポリシー、DNS 管理、LDAP バインドと読み取りサポートなどの操作に対して、従来の AD DS 環境と完全に互換性があります。 LDAP 書き込みサポートは、マネージド ドメインで作成されたオブジェクトに対して使用できますが、Microsoft Entra IDから同期されたリソースには使用できません。

Domain Services の次の機能により、展開と管理の操作が簡略化されます。

- **単純な展開エクスペリエンス:** ドメイン サービスは、Microsoft Entra管理センターの 1 つのウィザードを使用して、Microsoft Entra テナントに対して有効になります。
- ** Microsoft Entra ID:**ユーザー アカウント、グループ メンバーシップ、および資格情報を使用した統合は、Microsoft Entra IDから自動的に使用できます。 Microsoft Entra テナントまたはオンプレミスの AD DS 環境からの属性に対する新しいユーザー、グループ、または変更は、Domain Services に自動的に同期されます。
    - Microsoft Entra IDにリンクされている外部ディレクトリのアカウントは、Domain Services では使用できません。 これらの外部ディレクトリでは資格情報を使用できないため、マネージド ドメインに同期することはできません。
- **会社の資格情報/パスワードを使用する:** Domain Services のユーザーのパスワードは、Microsoft Entra テナントのパスワードと同じです。 ユーザーは、会社の資格情報を使用して、コンピューターのドメイン参加、対話形式またはリモート デスクトップ経由でのサインイン、マネージド ドメインに対する認証を行うことができます。
- ** NTLM および Kerberos 認証のサポート:** NTLM および Kerberos 認証のサポートにより、Windows統合認証に依存するアプリケーションを展開できます。
- **組み込みの高可用性:**Domain Services には、マネージド ドメインの高可用性を提供する複数のドメイン コントローラーが含まれています。 この高可用性により、サービスのアップタイムと障害に対する回復性が保証されます。
    - [Azure Availability Zones](https://learn.microsoft.com/ja-jp/azure/reliability/availability-zones-overview) をサポートするリージョンでは、これらのドメイン コントローラーもゾーン間で分散され、回復性が向上します。
- **ディザスター リカバリー:**[レプリカ セット](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/concepts-replica-sets)を使用して、地理的なディザスター リカバリーを提供します。

マネージド ドメインはスタンドアロン ドメインであり、オンプレミス ドメインの拡張機能ではありません。 必要に応じて、ハイブリッド アクセス シナリオをサポートするように、オンプレミスの AD DS 環境で [フォレストの信頼を構成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/concepts-forest-trust) できます。

ID オプションの詳細について学ぶには、[Domain Services と Microsoft Entra ID、Azure VM 上の AD DS、オンプレミスの AD DS を比較してください。](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/compare-identity-solutions)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/password-policy"} -->
## Microsoft Entra Domain Services でパスワード ポリシーを作成して使用する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/password-policy
- Service: entra-id / domain-services
- Article date: 2025-02-05
- Summary: 詳細なパスワード ポリシーを使用して、Domain Services マネージド ドメインのアカウント パスワードをセキュリティで保護および制御する方法と理由について説明します。

Microsoft Entra Domain Services でユーザー セキュリティを管理するには、アカウントのロックアウト設定またはパスワードの最小長と複雑さを制御するきめ細かいパスワード ポリシーを定義できます。 既定のきめ細かいパスワード ポリシーが作成され、Domain Services マネージド ドメイン内のすべてのユーザーに適用されます。 きめ細かい制御を提供し、特定のビジネスまたはコンプライアンスのニーズを満たすために、追加のポリシーを作成して特定のユーザーまたはグループに適用できます。

この記事では、Active Directory 管理センターを使用して Domain Services できめ細かいパスワード ポリシーを作成および構成する方法について説明します。

注

パスワード ポリシーは、Resource Manager デプロイ モデルを使用して作成されたマネージド ドメインでのみ使用できます。

### 開始する前に

この記事を完了するには、以下のリソースと特権が必要です。

- 有効な Azure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付け](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)ます。
- Microsoft Entra テナント内の Microsoft Entra Domain Services マネージド ドメインが有効化および構成されています。
    - 必要に応じて、[Microsoft Entra Domain Services マネージド ドメインを作成して構成する](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)チュートリアルを完了します。
    - マネージド ドメインは、Resource Manager デプロイ モデルを使用して作成されている必要があります。
- マネージド ドメインに参加している Windows Server 管理 VM。
    - 必要に応じて、チュートリアルを完了して [管理 VM を作成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-management-vm)。
- *Microsoft Entra テナントの Microsoft Entra DC 管理者*グループのメンバーであるユーザー アカウント。

### 既定のパスワード ポリシー設定

きめ細かいパスワード ポリシー (FGP) を使用すると、ドメイン内の異なるユーザーにパスワードとアカウントロックアウト ポリシーに特定の制限を適用できます。 たとえば、特権アカウントをセキュリティで保護するには、通常の非特権アカウントよりも厳密なアカウント ロックアウト設定を適用できます。 マネージド ドメイン内に複数の FGPP を作成し、ユーザーに適用する優先順位を指定できます。

パスワード ポリシーと Active Directory 管理センターの使用の詳細については、次の記事を参照してください。

- [きめ細かいパスワード ポリシーについて説明します](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2008-R2-and-2008/cc770394%28v=ws.10%29)
- [AD 管理センターを使用してきめ細かいパスワード ポリシーを構成する](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/get-started/adac/introduction-to-active-directory-administrative-center-enhancements--level-100-#fine_grained_pswd_policy_mgmt)

ポリシーはマネージド ドメインのグループ関連付けを通じて配布され、行った変更は次のユーザー サインイン時に適用されます。 ポリシーを変更しても、既にロックアウトされているユーザー アカウントのロックが解除されることはありません。

パスワード ポリシーの動作は、適用されるユーザー アカウントの作成方法によって少し異なります。 Domain Services でユーザー アカウントを作成する方法は 2 つあります。

- ユーザー アカウントは Microsoft Entra ID から同期できます。 これには、Azure で直接作成されたクラウド専用ユーザー アカウントと、Microsoft Entra Connect を使用してオンプレミスの AD DS 環境から同期されるハイブリッド ユーザー アカウントが含まれます。
    - Domain Services のユーザー アカウントの大部分は、Microsoft Entra ID からの同期プロセスによって作成されます。
- ユーザー アカウントはマネージド ドメインに手動で作成することができ、Microsoft Entra ID には存在しません。

すべてのユーザーは、作成方法に関係なく、Domain Services の既定のパスワード ポリシーによって次のアカウント ロックアウト ポリシーが適用されます。

- **アカウント ロックアウト期間:** 30
- **許可されたログオン試行の失敗回数:** 5
- **失敗したログオン試行回数のリセット :** 2 分後
- **パスワードの最長有効期間:** 90 日

これらの既定の設定では、2 分以内に 5 つの無効なパスワードが使用された場合、ユーザー アカウントは 30 分間ロックアウトされます。 アカウントは 30 分後に自動的にロック解除されます。

アカウントロックアウトは、マネージド ドメイン内でのみ発生します。 ユーザー アカウントは Domain Services でのみロックアウトされ、マネージド ドメインに対するサインイン試行が失敗した場合にのみロックアウトされます。 Microsoft Entra ID またはオンプレミスから同期されたユーザー アカウントは、ソース ディレクトリではロックアウトされず、Domain Services でのみロックアウトされます。

90 日を超えるパスワードの最大有効期間を指定する Microsoft Entra パスワード ポリシーがある場合、そのパスワードの有効期間は Domain Services の既定のポリシーに適用されます。 Domain Services で別のパスワードの有効期間を定義するように、カスタム パスワード ポリシーを構成できます。 Domain Services パスワード ポリシーで構成されているパスワードの最大有効期間が、Microsoft Entra ID またはオンプレミスの AD DS 環境よりも短い場合は注意してください。 そのシナリオでは、ユーザーのパスワードは、Microsoft Entra ID またはオンプレミスの AD DS 環境の変更を求めるメッセージが表示される前に、Domain Services で有効期限が切れる可能性があります。

マネージド ドメインで手動で作成されたユーザー アカウントの場合は、既定のポリシーからも次の追加のパスワード設定が適用されます。 これらの設定は、ユーザーが Domain Services でパスワードを直接更新できないため、Microsoft Entra ID から同期されたユーザー アカウントには適用されません。

- **パスワードの最小長 (文字数):** 7
- **パスワードは複雑さの要件を満たす必要があります**

既定のパスワード ポリシーでは、アカウントのロックアウトまたはパスワードの設定を変更することはできません。 代わりに、 *AAD DC Administrators* グループのメンバーは、カスタム パスワード ポリシーを作成し、次のセクションに示すように、既定の組み込みポリシーをオーバーライド (優先) するように構成できます。

### カスタム パスワード ポリシーを作成する

Azure でアプリケーションをビルドして実行するときは、カスタム パスワード ポリシーを構成できます。 たとえば、別のアカウント ロックアウト ポリシー設定を設定するポリシーを作成できます。

カスタム パスワード ポリシーは、マネージド ドメイン内のグループに適用されます。 この構成は、既定のポリシーを効果的にオーバーライドします。

カスタム パスワード ポリシーを作成するには、ドメインに参加している VM から Active Directory 管理ツールを使用します。 Active Directory 管理センターを使用すると、OU を含むマネージド ドメイン内のリソースを表示、編集、作成できます。

注

マネージド ドメインでカスタム パスワード ポリシーを作成するには、 *AAD DC Administrators* グループのメンバーであるユーザー アカウントにサインインしている必要があります。

1. [スタート] 画面で、[管理ツール] 選択します。 使用可能な管理ツールの一覧は、 [管理 VM を作成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-management-vm)するためのチュートリアルにインストールされています。
2. OU を作成して管理するには、管理ツールの一覧から **Active Directory 管理センター** を選択します。
3. 左側のウィンドウで、マネージド ドメイン ( *aaddscontoso.com* など) を選択します。
4. **システム** コンテナーを開き、**パスワード設定コンテナー**を開きます。

    マネージド ドメインの組み込みのパスワード ポリシーが表示されます。 この組み込みポリシーは変更できません。 代わりに、既定のポリシーをオーバーライドするカスタム パスワード ポリシーを作成します。

    [Image: Active Directory 管理センターでパスワード ポリシーを作成する]
5. 右側の **[タスク** ] パネルで、[ **新しい &gt; パスワード設定]** を選択します。
6. [ **パスワード設定の作成]** ダイアログで、 *MyCustomFGPP* などのポリシーの名前を入力します。
7. 複数のパスワード ポリシーが存在する場合、優先順位が最も高いポリシーがユーザーに適用されます。 数値が小さい方が優先度が高くなります。 既定のパスワード ポリシーの優先度は *200 です*。

    カスタム パスワード ポリシーの優先順位を設定して、 *既定値 (1* など) をオーバーライドします。
8. 必要に応じて、他のパスワード ポリシー設定を編集します。 アカウントロックアウト設定はすべてのユーザーに適用されますが、Microsoft Entra 自体ではなく、マネージド ドメイン内でのみ有効になります。

    [Image: カスタムのきめ細かいパスワード ポリシーを作成する]
9. [ **誤って削除されないように保護する**] をオフにします。 このオプションを選択すると、FGPP を保存できません。
10. [ **直接適用対象** ] セクションで、[ **追加** ] ボタンを選択します。 [ **ユーザーまたはグループの選択** ] ダイアログで、[ **場所** ] ボタンを選択します。

    [Image: パスワード ポリシーを適用するユーザーとグループを選択します。]
11. [ **場所** ] ダイアログで、ドメイン名 ( *aaddscontoso.com* など) を展開し、 **AADDC ユーザー**などの OU を選択します。 適用するユーザーのグループを含むカスタム OU がある場合は、その OU を選択します。

    [Image: グループが属する OU を選択します]
12. ポリシーを適用するユーザーまたはグループの名前を入力します。 [ **名前の確認]** を選択してアカウントを検証します。

    [Image: FGPP を適用するグループを検索して選択する]
13. [ **OK] を** クリックしてカスタム パスワード ポリシーを保存します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/policy-reference"} -->
## Microsoft Entra Domain Services 用の組み込みポリシー定義 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/policy-reference
- Service: entra-id / domain-services
- Article date: 2023-09-19
- Summary: Microsoft Entra Domain Services 用の Azure Policy 組み込みポリシー定義の一覧を表示します。 これらの組み込みポリシー定義は、Azure リソースを管理するための一般的な方法を示します。

このページは、Microsoft Entra Domain Services 用の [Azure Policy](https://learn.microsoft.com/ja-jp/azure/governance/policy/overview) 組み込みポリシー定義の索引です。 他のサービス用の Azure Policy 組み込みについては、[Azure Policy 組み込み定義](https://learn.microsoft.com/ja-jp/azure/governance/policy/samples/built-in-policies)に関するページをご覧ください。

各組み込みポリシー定義の名前は、Microsoft Entra 管理センターのポリシー定義にリンクしています。 **[バージョン]** 列のリンクを使用すると、[Azure Policy GitHub リポジトリ](https://github.com/Azure/azure-policy)のソースを表示できます。

### Microsoft Entra Domain Services

| 名前(Azure portal) | 説明 | 効果 | バージョン(GitHub) |
| --- | --- | --- | --- |
| [Microsoft Entra Domain Services マネージド ドメインで TLS 1.2 専用モードを使用する必要がある](https://portal.azure.com/#blade/Microsoft_Azure_Policy/PolicyDetailBlade/definitionId/%2Fproviders%2FMicrosoft.Authorization%2FpolicyDefinitions%2F3aa87b5a-7813-4b57-8a43-42dd9df5aaa7) | マネージド ドメインに対して TLS 1.2 専用モードを使用します。 既定では、Microsoft Entra Domain Services で、NTLM v1 や TLS v1 などの暗号が使用できます。 これらの暗号は、一部のレガシ アプリケーションで必要になる場合がありますが、脆弱と見なされており、不要であれば無効にできます。 TLS 1.2 専用モードが有効になっている場合、TLS 1.2 を使用せずに要求を行うクライアントはすべて失敗します。 「[Microsoft Entra Domain Services マネージド ドメインの強化](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/secure-your-domain)」で詳細を確認してください。 | Audit、Deny、Disabled | [1.1.0](https://github.com/Azure/azure-policy/blob/master/built-in-policies/policyDefinitions/Azure%20Active%20Directory/AADDomainServices_TLS_Audit.json) |
| [Microsoft Entra ID はプライベート リンクを使用して Azure サービスにアクセスする必要がある](https://learn.microsoft.com/ja-jp/training/modules/design-implement-private-access-to-azure-services/)。 | Azure Private Link を使用すると、接続元または接続先にパブリック IP アドレスを使用せずに、仮想ネットワークを Azure サービスに接続できます。 Private Link プラットフォームでは、Azure のバックボーン ネットワークを介してコンシューマーとサービスの間の接続が処理されます。 プライベート エンドポイントを Microsoft Entra ID にマッピングすることにより、データ漏えいのリスクを軽減することができます。 詳細については、https://aka.ms/privateLinkforAzureADDocs を参照してください。 インターネットやその他のサービス (M365) にアクセスできない状態で、分離された VNET から Azure サービスに対してのみ使用する必要があります。 | AuditIfNotExists、Disabled | [1.0.0](https://github.com/Azure/azure-policy/blob/master/built-in-policies/policyDefinitions/Azure%20Active%20Directory/PrivateLinkForAzureAD_PrivateLink_AINE.json) |
| [プライベート エンドポイントを使用して Microsoft Entra ID 用 Private Link を構成する](https://ms.portal.azure.com/#view/Microsoft_Azure_Network/PrivateLinkCenterBlade/%7E/overview.Authorization%2FpolicyDefinitions%2Fb923afcf-4c3a-4ed6-8386-1ff64b68de47) | プライベート エンドポイントを使用すると、接続元または接続先にパブリック IP アドレスを使用せずに、仮想ネットワークを Azure サービスに接続できます。 プライベート エンドポイントを Microsoft Entra ID にマッピングすることにより、データ漏えいのリスクを軽減することができます。 詳細については、https://aka.ms/privateLinkforAzureADDocs を参照してください。 インターネットやその他のサービス (M365) にアクセスできない状態で、分離された VNET から Azure サービスに対してのみ使用する必要があります。 | DeployIfNotExists、Disabled | [1.0.0](https://github.com/Azure/azure-policy/blob/master/built-in-policies/policyDefinitions/Azure%20Active%20Directory/PrivateLinkForAzureAD_PrivateEndpoint_DINE.json) |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/powershell-create-instance"} -->
## PowerShell を使用して Microsoft Entra Domain Services を有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/powershell-create-instance
- Service: entra-id / domain-services
- Article date: 2025-02-05
- Summary: Microsoft Graph PowerShell と Azure PowerShell を使って Microsoft Entra Domain Services を構成し、有効にする方法について説明します。

Microsoft Entra Domain Services では、Windows Server Active Directory と完全に互換性のあるマネージド ドメイン サービス (ドメイン参加、グループ ポリシー、LDAP、Kerberos または NTLM 認証など) が提供されます。 ドメイン コントローラーのデプロイ、管理、パッチの適用を自分で行わなくても、これらのドメイン サービスを使用することができます。 Domain Services は、既存の Microsoft Entra テナントと統合されます。 この統合により、ユーザーは、各自の会社の資格情報を使用してサインインすることができます。また管理者は、既存のグループとユーザー アカウントを使用してリソースへのアクセスをセキュリティで保護することができます。

この記事では、PowerShell を使用して Domain Services を有効にする方法について説明します。

注意

Azure を操作するには、Azure Az PowerShell モジュールを使用することをお勧めします。 作業を開始するには、[Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に関する記事を参照してください。 Az PowerShell モジュールに移行する方法については、「[AzureRM から Az への Azure PowerShell の移行](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)」を参照してください。

### 前提条件

この記事を完了するには、以下のリソースが必要です。

- Azure PowerShell のインストールおよび構成。

    - 必要であれば、手順に従って、[Azure PowerShell モジュールをインストールし、Azure サブスクリプションに接続](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)します。
    - 必ず [Connect-AzAccount](https://learn.microsoft.com/ja-jp/powershell/module/az.accounts/connect-azaccount) コマンドレットを使用して Azure サブスクリプションにサインインしてください。
- MS Graph PowerShell をインストールして構成します。

    - 必要であれば、指示に従って [MS Graph PowerShell モジュールをインストールし、Microsoft Entra ID に接続します](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)。
        - 必ず [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph) コマンドレットを使用して Microsoft Entra テナントにサインインしてください。
- この機能を管理するには、[全体管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)が必要です。
- この機能には、Azure サブスクリプションの*共同作成者* 権限が必要です。

    重要

    **Az.ADDomainServices** PowerShell モジュールがプレビュー段階にある間は、`Install-Module` コマンドレットを使用して、これを個別にインストールする必要があります。

    ```azurepowershell
    Install-Module -Name Az.ADDomainServices
    ```

### 必要な Microsoft Entra リソースを作成する

Domain Services を使用するには、認証と通信を行うためのサービス プリンシパルと、マネージド ドメインで管理アクセス許可を持つユーザーを定義するための Microsoft Entra グループが必要です。

まず、*Domain Controller Services* という名前の特定のアプリケーション ID を使用して、Microsoft Entra サービス プリンシパルを作成します。 ID 値は、グローバル Azure の場合は *2565bd9d-da50-47d4-8b85-4c97f669dc36*、他の Azure クラウドの場合は *6ba9a5d4-8456-4118-b521-9c5ca10cdf84* です。 このアプリケーション ID は変更しないでください。

[New-MgServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/new-mgserviceprincipal) コマンドレットを使用して Microsoft Entra サービス プリンシパルを作成します。

```powershell
New-MgServicePrincipal -AppId "2565bd9d-da50-47d4-8b85-4c97f669dc36"
```

次に、*AAD DC Administrators* という名前の Microsoft Entra グループを作成します。 このグループに追加されたユーザーには、マネージド ドメインで管理タスクを実行するためのアクセス許可が付与されます。

まず、*Get-MgGroup* コマンドレットを使用して、[AAD DC Administrators](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/get-mggroup) グループ オブジェクト ID を取得します。 *AAD DC Administrators* グループが存在しない場合は、[New-MgGroup](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/new-mggroup) コマンドレットを使用して作成します。

```powershell
# First, retrieve the object ID of the 'AAD DC Administrators' group.
$GroupObject = Get-MgGroup `
  -Filter "DisplayName eq 'AAD DC Administrators'"

# If the group doesn't exist, create it
if (!$GroupObject) {
  $GroupObject = New-MgGroup -DisplayName "AAD DC Administrators" `
    -Description "Delegated group to administer Microsoft Entra Domain Services" `
    -SecurityEnabled:$true `
    -MailEnabled:$false `
    -MailNickName "AADDCAdministrators"
  } else {
  Write-Output "Admin group already exists."
}
```

*AAD DC Administrators* グループを作成したら、[Get-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/get-mguser) コマンドレットを使用して目的のユーザーのオブジェクト ID を取得し、[New-MgGroupMember](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/new-mggroupmemberbyref) コマンドレットを使用してそのユーザーをグループに追加します。

次の例では、UPN が `admin@contoso.onmicrosoft.com` のアカウントのユーザー オブジェクト ID です。 このユーザー アカウントを、*AAD DC Administrators* グループに追加するユーザーの UPN に置き換えます。

```powershell
# Retrieve the object ID of the user you'd like to add to the group.
$UserObjectId = Get-MgUser `
  -Filter "UserPrincipalName eq 'admin@contoso.onmicrosoft.com'" | `
  Select-Object Id

# Add the user to the 'AAD DC Administrators' group.
New-MgGroupMember -GroupId $GroupObject.Id -DirectoryObjectId $UserObjectId.Id
```

### ネットワーク リソースを作成する

まず、[Register-AzResourceProvider](https://learn.microsoft.com/ja-jp/powershell/module/az.resources/register-azresourceprovider) コマンドレットを使用して、Microsoft Entra Domain Services リソース プロバイダーを登録します。

```azurepowershell
Register-AzResourceProvider -ProviderNamespace Microsoft.AAD
```

次に、[New-AzResourceGroup](https://learn.microsoft.com/ja-jp/powershell/module/az.resources/new-azresourcegroup) コマンドレットを使用してリソース グループを作成します。 次の例では、リソース グループは *myResourceGroup* という名前が付けられ、*westus* リージョンに作成されます。 独自の名前と希望するリージョンを使用します。

```azurepowershell
$ResourceGroupName = "myResourceGroup"
$AzureLocation = "westus"

# Create the resource group.
New-AzResourceGroup `
  -Name $ResourceGroupName `
  -Location $AzureLocation
```

Microsoft Entra Domain Services の仮想ネットワークとサブネットを作成します。 *DomainServices* 用と *Workloads* 用の 2 つのサブネットが作成されます。 Domain Services は、専用の *DomainServices* サブネットにデプロイされます。 このサブネットには、他のアプリケーションやワークロードをデプロイしないでください。 残りの VM には、別個の *Workloads* または他のサブネットを使用します。

[New-AzVirtualNetworkSubnetConfig](https://learn.microsoft.com/ja-jp/powershell/module/az.network/new-azvirtualnetworksubnetconfig) コマンドレットを使用してサブネットを作成し、次に [New-AzVirtualNetwork](https://learn.microsoft.com/ja-jp/powershell/module/az.network/new-azvirtualnetwork) コマンドレットを使用して仮想ネットワークを作成します。

```azurepowershell
$VnetName = "myVnet"

# Create the dedicated subnet for Microsoft Entra Domain Services.
$SubnetName = "DomainServices"
$AaddsSubnet = New-AzVirtualNetworkSubnetConfig `
  -Name $SubnetName `
  -AddressPrefix 10.0.0.0/24

# Create an additional subnet for your own VM workloads
$WorkloadSubnet = New-AzVirtualNetworkSubnetConfig `
  -Name Workloads `
  -AddressPrefix 10.0.1.0/24

# Create the virtual network in which you will enable Microsoft Entra Domain Services.
$Vnet= New-AzVirtualNetwork `
  -ResourceGroupName $ResourceGroupName `
  -Location westus `
  -Name $VnetName `
  -AddressPrefix 10.0.0.0/16 `
  -Subnet $AaddsSubnet,$WorkloadSubnet
```

#### ネットワーク セキュリティ グループの作成

Domain Services には、マネージド ドメインに必要なポートをセキュリティで保護し、その他すべての受信トラフィックをブロックするネットワーク セキュリティ グループが必要です。 [ネットワーク セキュリティ グループ (NSG)](https://learn.microsoft.com/ja-jp/azure/virtual-network/network-security-groups-overview) には、Azure 仮想ネットワーク内のトラフィックへのネットワーク トラフィックを許可または拒否するルールの一覧が含まれています。 Domain Services では、ネットワーク セキュリティ グループは、マネージド ドメインへのアクセスをロックダウンするための追加の保護レイヤーとして機能します。 必要なポートを確認するには、「[ネットワーク セキュリティ グループと必要なポート](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/network-considerations#network-security-groups-and-required-ports)」を参照してください。

次の PowerShell コマンドレットでは、[New-AzNetworkSecurityRuleConfig](https://learn.microsoft.com/ja-jp/powershell/module/az.network/new-aznetworksecurityruleconfig) を使用してルールを作成し、[New-AzNetworkSecurityGroup](https://learn.microsoft.com/ja-jp/powershell/module/az.network/new-aznetworksecuritygroup) を使用してネットワーク セキュリティ グループを作成します。 ネットワーク セキュリティ グループとルールは、[Set-AzVirtualNetworkSubnetConfig](https://learn.microsoft.com/ja-jp/powershell/module/az.network/set-azvirtualnetworksubnetconfig) コマンドレットを使用して仮想ネットワーク サブネットに関連付けられます。

```azurepowershell
$NSGName = "dsNSG"

# Create a rule to allow inbound TCP port 3389 traffic from Microsoft secure access workstations for troubleshooting
$nsg201 = New-AzNetworkSecurityRuleConfig -Name AllowRD `
    -Access Allow `
    -Protocol Tcp `
    -Direction Inbound `
    -Priority 201 `
    -SourceAddressPrefix CorpNetSaw `
    -SourcePortRange * `
    -DestinationAddressPrefix * `
    -DestinationPortRange 3389

# Create a rule to allow TCP port 5986 traffic for PowerShell remote management
$nsg301 = New-AzNetworkSecurityRuleConfig -Name AllowPSRemoting `
    -Access Allow `
    -Protocol Tcp `
    -Direction Inbound `
    -Priority 301 `
    -SourceAddressPrefix AzureActiveDirectoryDomainServices `
    -SourcePortRange * `
    -DestinationAddressPrefix * `
    -DestinationPortRange 5986

# Create the network security group and rules
$nsg = New-AzNetworkSecurityGroup -Name $NSGName `
    -ResourceGroupName $ResourceGroupName `
    -Location $AzureLocation `
    -SecurityRules $nsg201,$nsg301

# Get the existing virtual network resource objects and information
$vnet = Get-AzVirtualNetwork -Name $VnetName -ResourceGroupName $ResourceGroupName
$subnet = Get-AzVirtualNetworkSubnetConfig -VirtualNetwork $vnet -Name $SubnetName
$addressPrefix = $subnet.AddressPrefix

# Associate the network security group with the virtual network subnet
Set-AzVirtualNetworkSubnetConfig -Name $SubnetName `
    -VirtualNetwork $vnet `
    -AddressPrefix $addressPrefix `
    -NetworkSecurityGroup $nsg
$vnet | Set-AzVirtualNetwork
```

### マネージド ドメインの作成

次に、マネージド ドメインを作成しましょう。 お使いの Azure サブスクリプション ID を設定し、マネージド ドメインの名前 (*dscontoso.com* など) を指定します。 お使いのサブスクリプション ID は、[Get-AzSubscription](https://learn.microsoft.com/ja-jp/powershell/module/az.accounts/get-azsubscription) コマンドレットを使用して取得できます。

Availability Zones がサポートされているリージョンを選択すると、Domain Services リソースが、冗長性のために複数のゾーンに分散されます。

Availability Zones は、Azure リージョン内の一意の物理的な場所です。 それぞれのゾーンは、独立した電源、冷却手段、ネットワークを備えた 1 つまたは複数のデータセンターで構成されています。 回復性を確保するため、有効になっているリージョンにはいずれも最低 3 つのゾーンが別個に存在しています。

Domain Services を複数のゾーンに分散するために、ご自身で構成するものは何もありません。 Azure プラットフォームでは、ゾーンへのリソース分散が自動的に処理されます。 詳細情報および利用可能なリージョンについては、「[Azure の Availability Zones の概要](https://learn.microsoft.com/ja-jp/azure/reliability/availability-zones-overview)」を参照してください。

```azurepowershell
$AzureSubscriptionId = "YOUR_AZURE_SUBSCRIPTION_ID"
$ManagedDomainName = "dscontoso.com"

# Enable Microsoft Entra Domain Services for the directory.
$replicaSetParams = @{
  Location = $AzureLocation
  SubnetId = "/subscriptions/$AzureSubscriptionId/resourceGroups/$ResourceGroupName/providers/Microsoft.Network/virtualNetworks/$VnetName/subnets/DomainServices"
}
$replicaSet = New-AzADDomainServiceReplicaSetObject @replicaSetParams

$domainServiceParams = @{
  Name = $ManagedDomainName
  ResourceGroupName = $ResourceGroupName
  DomainName = $ManagedDomainName
  ReplicaSet = $replicaSet
}
New-AzADDomainService @domainServiceParams
```

リソースが作成され、PowerShell プロンプトに制御が戻るまで数分かかります。 マネージド ドメインは引き続きバックグラウンドでプロビジョニングされ、デプロイが完了するまでには最大 1 時間かかる場合があります。 Microsoft Entra 管理センターでは、お使いのマネージド ドメインの **[概要]** ページに、このデプロイ ステージ全体の現在の状態が表示されます。

マネージド ドメインがプロビジョニングを完了したことが Microsoft Entra 管理センターに表示されたら、次のタスクを完了する必要があります。

- 仮想マシンがマネージド ドメインを検出してドメイン参加または認証を行うことができるように、仮想ネットワークの DNS 設定を更新します。
    - DNS を構成するには、ポータルでマネージド ドメインを選択します。 **[概要]** ウィンドウで、これらの DNS 設定を自動的に構成するように求められます。
- [Domain Services とのパスワード同期を有効にして](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance#enable-user-accounts-for-azure-ad-ds)、エンド ユーザーが会社の資格情報を使用してマネージド ドメインにサインインできるようにします。

### 完全な PowerShell スクリプト

次の完全な PowerShell スクリプトは、この記事に示されているすべてのタスクを結合します。 スクリプトをコピーし、`.ps1` 拡張子付きのファイルに保存します。 Azure Global の場合は、AppId 値 *2565bd9d-da50-47d4-8b85-4c97f669dc36* を使用します。 他の Azure クラウドの場合は、AppId 値 *6ba9a5d4-8456-4118-b521-9c5ca10cdf84* を使用します。 ローカルの PowerShell コンソール、または [Azure Cloud Shell](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/configure-app-multi-instancing) でスクリプトを実行します。

この機能を管理するには、[全体管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)が必要です。

この機能には、Azure サブスクリプションの*共同作成者* 権限が必要です。

```azurepowershell
# Change the following values to match your deployment.
$AaddsAdminUserUpn = "admin@contoso.onmicrosoft.com"
$ResourceGroupName = "myResourceGroup"
$VnetName = "myVnet"
$AzureLocation = "westus"
$AzureSubscriptionId = "YOUR_AZURE_SUBSCRIPTION_ID"
$ManagedDomainName = "dscontoso.com"

# Connect to your Microsoft Entra directory.
Connect-MgGraph -Scopes "Application.ReadWrite.All","Directory.ReadWrite.All"

# Login to your Azure subscription.
Connect-AzAccount

# Create the service principal for Microsoft Entra Domain Services.
New-MgServicePrincipal -AppId "2565bd9d-da50-47d4-8b85-4c97f669dc36"

# First, retrieve the object of the 'AAD DC Administrators' group.
$GroupObject = Get-MgGroup `
  -Filter "DisplayName eq 'AAD DC Administrators'"

# Create the delegated administration group for Microsoft Entra Domain Services if it doesn't already exist.
if (!$GroupObject) {
  $GroupObject = New-MgGroup -DisplayName "AAD DC Administrators" `
    -Description "Delegated group to administer Microsoft Entra Domain Services" `
    -SecurityEnabled:$true `
    -MailEnabled:$false `
    -MailNickName "AADDCAdministrators"
  } else {
  Write-Output "Admin group already exists."
}

# Now, retrieve the object ID of the user you'd like to add to the group.
$UserObjectId = Get-MgUser `
  -Filter "UserPrincipalName eq '$AaddsAdminUserUpn'" | `
  Select-Object Id

# Add the user to the 'AAD DC Administrators' group.
New-MgGroupMember -GroupId $GroupObject.Id -DirectoryObjectId $UserObjectId.Id

# Register the resource provider for Microsoft Entra Domain Services with Resource Manager.
Register-AzResourceProvider -ProviderNamespace Microsoft.AAD

# Create the resource group.
New-AzResourceGroup `
  -Name $ResourceGroupName `
  -Location $AzureLocation

# Create the dedicated subnet for Microsoft Entra Domain Services.
$SubnetName = "DomainServices"
$AaddsSubnet = New-AzVirtualNetworkSubnetConfig `
  -Name DomainServices `
  -AddressPrefix 10.0.0.0/24

$WorkloadSubnet = New-AzVirtualNetworkSubnetConfig `
  -Name Workloads `
  -AddressPrefix 10.0.1.0/24

# Create the virtual network in which you will enable Microsoft Entra Domain Services.
$Vnet=New-AzVirtualNetwork `
  -ResourceGroupName $ResourceGroupName `
  -Location $AzureLocation `
  -Name $VnetName `
  -AddressPrefix 10.0.0.0/16 `
  -Subnet $AaddsSubnet,$WorkloadSubnet

$NSGName = "dsNSG"

# Create a rule to allow inbound TCP port 3389 traffic from Microsoft secure access workstations for troubleshooting
$nsg201 = New-AzNetworkSecurityRuleConfig -Name AllowRD `
    -Access Allow `
    -Protocol Tcp `
    -Direction Inbound `
    -Priority 201 `
    -SourceAddressPrefix CorpNetSaw `
    -SourcePortRange * `
    -DestinationAddressPrefix * `
    -DestinationPortRange 3389

# Create a rule to allow TCP port 5986 traffic for PowerShell remote management
$nsg301 = New-AzNetworkSecurityRuleConfig -Name AllowPSRemoting `
    -Access Allow `
    -Protocol Tcp `
    -Direction Inbound `
    -Priority 301 `
    -SourceAddressPrefix AzureActiveDirectoryDomainServices `
    -SourcePortRange * `
    -DestinationAddressPrefix * `
    -DestinationPortRange 5986

# Create the network security group and rules
$nsg = New-AzNetworkSecurityGroup -Name $NSGName `
    -ResourceGroupName $ResourceGroupName `
    -Location $AzureLocation `
    -SecurityRules $nsg201,$nsg301

# Get the existing virtual network resource objects and information
$vnet = Get-AzVirtualNetwork -Name $VnetName -ResourceGroupName $ResourceGroupName
$subnet = Get-AzVirtualNetworkSubnetConfig -VirtualNetwork $vnet -Name $SubnetName
$addressPrefix = $subnet.AddressPrefix

# Associate the network security group with the virtual network subnet
Set-AzVirtualNetworkSubnetConfig -Name $SubnetName `
    -VirtualNetwork $vnet `
    -AddressPrefix $addressPrefix `
    -NetworkSecurityGroup $nsg
$vnet | Set-AzVirtualNetwork

# Enable Microsoft Entra Domain Services for the directory.
$replicaSetParams = @{
  Location = $AzureLocation
  SubnetId = "/subscriptions/$AzureSubscriptionId/resourceGroups/$ResourceGroupName/providers/Microsoft.Network/virtualNetworks/$VnetName/subnets/DomainServices"
}
$replicaSet = New-AzADDomainServiceReplicaSetObject @replicaSetParams

$domainServiceParams = @{
  Name = $ManagedDomainName
  ResourceGroupName = $ResourceGroupName
  DomainName = $ManagedDomainName
  ReplicaSet = $replicaSet
}
New-AzADDomainService @domainServiceParams
```

リソースが作成され、PowerShell プロンプトに制御が戻るまで数分かかります。 マネージド ドメインは引き続きバックグラウンドでプロビジョニングされ、デプロイが完了するまでには最大 1 時間かかる場合があります。 Microsoft Entra 管理センターでは、お使いのマネージド ドメインの **[概要]** ページに、このデプロイ ステージ全体の現在の状態が表示されます。

マネージド ドメインがプロビジョニングを完了したことが Microsoft Entra 管理センターに表示されたら、次のタスクを完了する必要があります。

- 仮想マシンがマネージド ドメインを検出してドメイン参加または認証を行うことができるように、仮想ネットワークの DNS 設定を更新します。
    - DNS を構成するには、ポータルでマネージド ドメインを選択します。 **[概要]** ウィンドウで、これらの DNS 設定を自動的に構成するように求められます。
- [Domain Services とのパスワード同期を有効にして](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance#enable-user-accounts-for-azure-ad-ds)、エンド ユーザーが会社の資格情報を使用してマネージド ドメインにサインインできるようにします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/powershell-scoped-synchronization"} -->
## PowerShell を使用した Microsoft Entra Domain Services の範囲指定された同期 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/powershell-scoped-synchronization
- Service: entra-id / domain-services
- Article date: 2025-05-22
- Summary: Microsoft Graph PowerShell を使って、Microsoft Entra ID から Microsoft Entra Domain Services マネージド ドメインへの範囲指定された同期を構成する方法について説明します

Microsoft Entra Domain Services は、認証サービスを提供するために Microsoft Entra ID からユーザーとグループを同期します。 ハイブリッド環境では、最初にオンプレミスの Active Directory Domain Services (AD DS) 環境のユーザーとグループが Microsoft Entra Connect を使用して Microsoft Entra ID に同期された後、Domain Services に同期されます。

既定では、Microsoft Entra ディレクトリのすべてのユーザーとグループが Domain Services マネージド ドメインに同期されます。 特定のニーズがある場合は、代わりに、定義したユーザー セットのみを同期することを選択できます。

この記事では、範囲指定された同期を使用するマネージド ドメインを作成した後、MS Graph PowerShell を使用して、範囲指定されたユーザー セットを変更したり、無効にしたりする方法について説明します。 [Microsoft Entra 管理センターを使用して、これらの手順を完了](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/scoped-synchronization)することもできます。

### 開始する前に

この記事を完了するには、以下のリソースと特権が必要です。

- 有効な Azure サブスクリプション
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付け](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)ます。
- Microsoft Entra テナントで有効にされていて、構成されている Microsoft Entra Domain Services マネージド ドメイン。
    - 必要に応じて、[Microsoft Entra Domain Services マネージド ドメインを作成して構成する](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)チュートリアルを完了します。
- Domain Services の同期スコープを変更するには、テナントで[アプリケーション管理者](https://learn.microsoft.com/ja-jp/azure/active-directory/roles/permissions-reference#application-administrator)と[グループ管理者](https://learn.microsoft.com/ja-jp/azure/active-directory/roles/permissions-reference#groups-administrator)の Microsoft Entra ロールが必要です。

### 範囲指定された同期の概要

既定では、Microsoft Entra ディレクトリのすべてのユーザーとグループがマネージド ドメインに同期されます。 マネージド ドメインにアクセスする必要のあるユーザーが少数しかいない場合は、それらのユーザー アカウントのみを同期することができます。 この範囲指定された同期はグループベースです。 グループベースの範囲指定された同期を構成した場合、指定したグループに属するユーザー アカウントのみがマネージド ドメインに同期されます。 入れ子になったグループは同期されません。選択した特定のグループのみが同期されます。

同期スコープは、マネージド ドメインを作成する前または後に変更できます。 同期のスコープは、アプリケーション識別子 2565bd9d-da50-47d4-8b85-4c97f669dc36 を使用してサービス プリンシパルによって定義されます。 スコープが失われないようにするには、サービス プリンシパルを削除または変更しないでください。 誤って削除された場合、同期スコープを復旧できません。

同期スコープを変更する場合は、次の点にご注意ください。

- 完全同期が行われます。
- マネージド ドメインで不要になったオブジェクトは削除されます。 新しいオブジェクトは、マネージド ドメインに作成されます。

同期プロセスの詳細については、[Microsoft Entra Domain Services での同期の理解](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/synchronization)に関する記事を参照してください。

### 範囲指定された同期に関する PowerShell スクリプト

範囲指定された同期を PowerShell を使用して構成するには、まず、次のスクリプトを `Select-GroupsToSync.ps1` という名前のファイルに保存します。

このスクリプトでは、Microsoft Entra ID から選択されたグループを同期するように Domain Services を構成します。 指定されたグループに属しているユーザー アカウントはすべて、マネージド ドメインに同期されます。

このスクリプトは、この記事の追加の手順で使用します。

```powershell
param (
    [Parameter(Position = 0)]
    [String[]]$groupsToAdd
)

Connect-MgGraph -Scopes "Directory.Read.All","AppRoleAssignment.ReadWrite.All"
$sp = Get-MgServicePrincipal -Filter "AppId eq '2565bd9d-da50-47d4-8b85-4c97f669dc36'"
$role = $sp.AppRoles | where-object -FilterScript {$_.DisplayName -eq "User"}

Write-Output "`n****************************************************************************"

Write-Output "Total group-assignments need to be added: $($groupsToAdd.Count)"
$newGroupIds = New-Object 'System.Collections.Generic.HashSet[string]'
foreach ($groupName in $groupsToAdd)
{
    try
    {
        $group = Get-MgGroup -Filter "DisplayName eq '$groupName'"
        $newGroupIds.Add($group.Id)

        Write-Output "Group-Name: $groupName, Id: $($group.Id)"
    }
    catch
    {
        Write-Error "Failed to find group: $groupName. Exception: $($_.Exception)."
    }
}

Write-Output "****************************************************************************`n"
Write-Output "`n****************************************************************************"

$currentAssignments = Get-MgServicePrincipalAppRoleAssignedTo -ServicePrincipalId $sp.Id -All:$true
Write-Output "Total current group-assignments: $($currentAssignments.Count), SP-ObjectId: $($sp.Id)"

$currAssignedObjectIds = New-Object 'System.Collections.Generic.HashSet[string]'
foreach ($assignment in $currentAssignments)
{
    Write-Output "Assignment-ObjectId: $($assignment.PrincipalId)"

    if ($newGroupIds.Contains($assignment.PrincipalId) -eq $false)
    {
        Write-Output "This assignment is not needed anymore. Removing it! Assignment-ObjectId: $($assignment.PrincipalId)"
        Remove-MgServicePrincipalAppRoleAssignment -ServicePrincipalId $sp.Id -AppRoleAssignmentId $assignment.Id
    }
    else
    {
        $currAssignedObjectIds.Add($assignment.PrincipalId)
    }
}

Write-Output "****************************************************************************`n"
Write-Output "`n****************************************************************************"

foreach ($id in $newGroupIds)
{
    try
    {
        if ($currAssignedObjectIds.Contains($id) -eq $false)
        {
            Write-Output "Adding new group-assignment. Role-Id: $($role.Id), Group-Object-Id: $id, ResourceId: $($sp.Id)"
            $appRoleAssignment = @{
                "principalId"= $Id
                "resourceId"= $sp.Id
                "appRoleId"= $role.Id
            }
            New-MgServicePrincipalAppRoleAssignment -ServicePrincipalId $sp.Id -BodyParameter $appRoleAssignment 
        }
        else
        {
            Write-Output "Group-ObjectId: $id is already assigned."
        }
    }
    catch
    {
        Write-Error "Exception occurred assigning Object-ID: $id. Exception: $($_.Exception)."
    }
}

Write-Output "****************************************************************************`n"
```

### 範囲指定された同期を有効にする

マネージド ドメインのグループベースの範囲指定された同期を有効にするには、次の手順を実行します。

1. 最初に Domain Services リソースに *"filteredSync" = "Enabled"* を設定してから、マネージド ドメインを更新します。

    この機能を管理するには、[全体管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)が必要です。

    [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands) コマンドレットを使って Microsoft Entra テナントにサインインします。

    ```powershell
    # Connect to your Entra ID tenant
    Connect-MgGraph -Scopes "Application.ReadWrite.All","Group.ReadWrite.All"
    
    # Retrieve the Microsoft Entra DS resource.
    $DomainServicesResource = Get-AzResource -ResourceType "Microsoft.AAD/DomainServices"
    
    # Enable group-based scoped synchronization.
    $enableScopedSync = @{"filteredSync" = "Enabled"}
    
    # Update the Microsoft Entra DS resource
    Set-AzResource -Id $DomainServicesResource.ResourceId -Properties $enableScopedSync
    ```
2. 次に、マネージド ドメインにユーザーを同期するグループの一覧を指定します。

    `Select-GroupsToSync.ps1` スクリプトを実行し、同期するグループの一覧を指定します。次の例で、同期するグループは *GroupName1* と *GroupName2* です。

    警告

    範囲指定された同期用のグループの一覧には、*AAD DC Administrators* グループを含める必要があります。 このグループを含めないと、マネージド ドメインは使用できません。

    ```powershell
    .\Select-GroupsToSync.ps1 -groupsToAdd @("AAD DC Administrators", "GroupName1", "GroupName2")
    ```

同期のスコープを変更すると、マネージド ドメインですべてのデータが再同期されます。 マネージド ドメインで不要になったオブジェクトは削除されます。また、再同期が完了するまでには時間がかかる場合があります。

### 範囲指定された同期を変更する

マネージド ドメインにユーザーを同期するグループの一覧を変更するには、`Select-GroupsToSync.ps1` スクリプトを実行し、同期する新しいグループの一覧を指定します。

次の例では、同期対象のグループに *GroupName2* が含まれなくなり、*GroupName3* が含まれるようになりました。

警告

範囲指定された同期用のグループの一覧には、*AAD DC Administrators* グループを含める必要があります。 このグループを含めないと、マネージド ドメインは使用できません。

この機能を管理するには、[全体管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)が必要です。

[Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands) コマンドレットを使って Microsoft Entra テナントにサインインします。

```powershell
.\Select-GroupsToSync.ps1 -groupsToAdd @("AAD DC Administrators", "GroupName1", "GroupName3")
```

同期のスコープを変更すると、マネージド ドメインですべてのデータが再同期されます。 マネージド ドメインで不要になったオブジェクトは削除されます。また、再同期が完了するまでには時間がかかる場合があります。

### 範囲指定された同期を無効にする

マネージド ドメインのグループベースの範囲指定された同期を無効にするには、Domain Services リソース上で *"filteredSync" = "Disabled"* を設定してから、マネージド ドメインを更新します。 完了すると、すべてのユーザーとグループが Microsoft Entra ID から同期されるように設定されます。

この機能を管理するには、[全体管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)が必要です。

[Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands) コマンドレットを使って Microsoft Entra テナントにサインインします。

```powershell
# Connect to your Entra ID tenant
Connect-MgGraph -Scopes "Application.ReadWrite.All","Group.ReadWrite.All"

# Retrieve the Microsoft Entra DS resource.
$DomainServicesResource = Get-AzResource -ResourceType "Microsoft.AAD/DomainServices"

# Disable group-based scoped synchronization.
$disableScopedSync = @{"filteredSync" = "Disabled"}

# Update the Microsoft Entra DS resource
Set-AzResource -Id $DomainServicesResource.ResourceId -Properties $disableScopedSync
```

同期のスコープを変更すると、マネージド ドメインですべてのデータが再同期されます。 マネージド ドメインで不要になったオブジェクトは削除されます。また、再同期が完了するまでには時間がかかる場合があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/rc4-deprecation-advance-dependency-test"} -->
## MICROSOFT ENTRA DOMAIN SERVICESでの RC4 廃止の事前依存関係テスト - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/rc4-deprecation-advance-dependency-test
- Service: entra-id / domain-services
- Article date: 2026-07-01
- Summary: RC4 の非推奨化の事前依存関係テストとは何か、いつ実行されるか、およびMicrosoft Entra Domain Servicesで準備して復旧する方法について説明します。

Microsoftは、[CVE-2026-20833](https://www.cve.org/CVERecord?id=CVE-2026-20833) に関連するセキュリティ強化の一環として、Microsoft Entra Domain Servicesの Kerberos の RC4 暗号化を非推奨にしています。 RC4 が完全に無効になる前に顧客の準備状況を評価するために、Domain Services チームは、マネージド ドメイン コントローラーでの RC4 暗号化の制御された時間制限付き無効化という *事前依存関係テスト*を実行します。 テストではアクティブな RC4 依存関係が表示されるため、強制が永続的になる前に修復できます。

この記事では、事前依存関係テストとは何か、実行時、およびその準備と復旧の方法について説明します。

### 事前依存関係テストのしくみ

テスト中、チームは一時的に RC4 をリージョン内のドメイン コントローラーの強制モード (AES 専用暗号化) に移行します。 ワークロードが Kerberos 認証または LDAP バインドの RC4 に依存している場合、テストの開始後に認証エラーが発生する可能性があります。 **2026 年 7 月 13 日の週から、RC4 はすべてのリージョンで完全に無効になります。**

### スケジュール

テストは、南北アメリカ、ヨーロッパ、Asia-Pacific、中国の 3 つのウェーブで、各リージョンの現地時刻の 10:00 に開始されます。 ワークロードが影響を受ける場合は、この記事で後述する回復手順を使用して自己修復します。

#### Wave 1 — アメリカ地域 (2026 年 7 月 6 日(月))

| PaaS リージョン | ローカル タイムゾーン | ローカル開始時刻 | UTC 開始時刻 |
| --- | --- | --- | --- |
| 米国中西部 | MT (UTC-6) | 10:00 MDT | 16:00 UTC |
| 米国西部 | PT (UTC-7) | 10:00 PDT | 17:00 UTC |
| 米国東部 | ET (UTC-4) | 10:00 EDT | 14:00 UTC |
| US Gov バージニア | ET (UTC-4) | 10:00 EDT | 14:00 UTC |
| US Gov アリゾナ | MT (UTC-6) | 10:00 MDT | 16:00 UTC |
| US Nat East | ET (UTC-4) | 10:00 EDT | 14:00 UTC |
| US Nat West | PT (UTC-7) | 10:00 PDT | 17:00 UTC |
| US Sec East | ET (UTC-4) | 10:00 EDT | 14:00 UTC |
| US Sec West | PT (UTC-7) | 10:00 PDT | 17:00 UTC |

#### Wave 2 — ヨーロッパ (2026 年 7 月 7 日(火)

| PaaS リージョン | ローカル タイムゾーン | ローカル開始時刻 | UTC 開始時刻 |
| --- | --- | --- | --- |
| 西ヨーロッパ (オランダ) | CEST (UTC+2) | 10:00 CEST | 08:00 UTC |
| 北ヨーロッパ (アイルランド) | IST (UTC+1) | 10:00 IST | 09:00 UTC |
| BLEU フランス中部 (パリ) | CEST (UTC+2) | 10:00 CEST | 08:00 UTC |
| BLEU France South (マルセイユ) | CEST (UTC+2) | 10:00 CEST | 08:00 UTC |
| デロス ドイツ中部 (フランクフルト) | CEST (UTC+2) | 10:00 CEST | 08:00 UTC |
| デロス ドイツ北部 (ベルリン) | CEST (UTC+2) | 10:00 CEST | 08:00 UTC |

#### Wave 3 — Asia-Pacific と中国 (2026 年 7 月 8 日(水)

| PaaS リージョン | ローカル タイムゾーン | ローカル開始時刻 | UTC 開始時刻 |
| --- | --- | --- | --- |
| 東南アジア (シンガポール) | SGT (UTC+8) | 10:00 SGT | 02:00 UTC |
| 東日本 (東京) | JST (UTC+9) | 10:00 JST | 01:00 UTC |
| 西日本 (大阪) | JST (UTC+9) | 10:00 JST | 01:00 UTC |
| 中国北部 2 (北京) | CST (UTC+8) | 10:00 CST | 02:00 UTC |
| 中国北部 3 (河北省) | CST (UTC+8) | 10:00 CST | 02:00 UTC |
| 中国東部 2 (上海) | CST (UTC+8) | 10:00 CST | 02:00 UTC |

### テストの準備

リージョンのテスト ウィンドウの前に、次の手順を実行します。

1. 「Microsoft Entra Domain Servicesのセキュリティ[監査と DNS 監査を有効にする」に従って、マネージド ドメインのセキュリティ監査を有効にします](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/security-audit-events)。
2. [サンプル クエリ 7](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/security-audit-events#sample-query-7) を使用して、環境内の Kerberos RC4 チケット発行を識別します。
3. 影響を受けるワークロードを AES 暗号化に移行します。

### 影響を受けた場合は回復する

テスト中にワークロードで認証エラーが発生した場合、 *AAD DC Administrators* グループのメンバーは、セルフサービス RC4 構成手順を使用して [RC4](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/troubleshoot-alerts#self-service-rc4-configuration) を直ちに復元できます。 `rc4DefaultDisablementPhase`を `1` (監査モード) に設定し、`defaultDomainSupportedEncTypes`を `60` に設定します。 変更は約 10 分以内に適用されます。

セルフサービスの手順でワークロードが復元されない場合にのみ、Azure サポート要求を提出します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/reference-domain-services-tls-enforcement"} -->
## Microsoft Entra Domain Services のトランスポート層セキュリティ (TLS) 1.2 の適用に移行する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/reference-domain-services-tls-enforcement
- Service: entra-id / domain-services
- Article date: 2025-09-03
- Summary: Microsoft Entra Domain Services マネージド ドメインに TLS 1.2 を適用する方法について説明します。

Microsoft は、2023 年 11 月 10 日に伝達された TLS バージョン 1.0 と 1.1 を無効にすることで、セキュリティを強化しています。 TLS 1.0 および TLS 1.1 バージョンの Microsoft の実装には脆弱性は知られていませんが、TLS 1.2 以降のバージョンでは、完全な前方秘密や強力な暗号スイートなど、セキュリティ機能が強化されています。 この変更は、顧客データの保護に役立ち、業界標準への準拠を保証します。

Microsoft Entra Domain Services では TLS バージョン 1.0 と 1.1 がサポートされていますが、既定では無効になっています。 Domain Services では、 **TLS 1.2 のみのモード**を無効にする機能が削除されました。 **TLS 1.2 のみモード**を無効にしたお客様は、このモードを有効にすることができます。

Azure portal または PowerShell を使用して **、TLS 1.2 のみのモードを有効にすることができます**。

### [前提条件]

[TLS 1.2 のみのモード](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)などのセキュリティ設定を変更するには、Microsoft Entra ID の[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)ロールと**グループ管理者**ロールが必要です。

### 非推奨の TLS バージョンを使用するアプリケーションを特定する

**TLS 1.2 のみのモード**を有効にする前に、引き続き TLS 1.0 または 1.1 を使用するアプリケーションを特定し、それらを更新するか、TLS 1.2 をサポートする代替手段に置き換える必要があります。 影響を受ける予定のアプリの詳細については、 [Windows での TLS 1.0 および TLS 1.1 の廃止に](https://learn.microsoft.com/ja-jp/windows/win32/secauthn/tls-10-11-deprecation-in-windows)関する情報を参照してください。

## [Azure Portal](#tab/portal)
1. [アプリケーション管理者](https://entra.microsoft.com)およびグループ[管理者として Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)にサインイン[します](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)。
2. **Domain Services** を検索し、Domain Services インスタンスを選択します。
3. [ **セキュリティ設定] を選択します**。
4. **TLS 1.2 のみのモード**が **[無効**] に設定されている場合、インスタンスは TLS バージョン 1.0 と 1.1 を有効にします。 **[TLS 1.2 のみモード]** を **[有効]** に設定し、[**保存**] をクリックします。

    ドメイン セキュリティ更新プログラムが適用されるため、この変更が完了するまでに約 10 分かかる場合があります。

    [Image: Domain Services の TLS 1.2 のみモードを有効にする方法を示すスクリーンショット。]

注

2026 年 6 月 30 日まで、[ **無効]** を選択すると、失敗する可能性のあるアプリを更新または置換するときに、レガシ TLS トラフィックを一時的に許可できます。 [ **有効にする]** をもう一度選択して、準拠を維持します。

## [PowerShell](#tab/powershell)
1. Az.ADDomainServices モジュールをインストールします。

    ```powershell
    Install-Module -Name Az.ADDomainServices
    ```
2. Azure サブスクリプションに接続します。

    ```powershell
    Connect-AzAccount -Subscription aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e
    ```
3. 次の 2 つのコマンドを実行して、 **TLS 1.2 Only Mode** の値を更新します。

    ```powershell
    $domainService = Get-AzADDomainService
    ```

    ```powershell
    Update-AzADDomainService -Name $domainService.Name -ResourceGroupName $domainService.ResourceGroupName -DomainSecuritySettingTlsV1 Disabled
    ```

    ドメイン セキュリティ更新プログラムが適用されるため、このコマンドの完了には約 10 分かかる場合があります。

### トラブルシューティング

- 一部のアプリでは、TLS ハンドシェイクが失敗したときにログまたはエラー メッセージが提供されます。 アプリケーション レベルの診断を使用して、サポートされていないプロトコルに関連するエラーを検索します。
- 2026 年 6 月 30 日までは、次の PowerShell の例を変更して、アプリの更新または置換中にレガシ TLS トラフィックを一時的に許可することができます。

    ```powershell
    Update-AzADDomainService -Name $domainService.Name -ResourceGroupName $domainService.ResourceGroupName -DomainSecuritySettingTlsV1 Enabled
    ```
- トラブルシューティングの詳細については、 [Azure サポート要求を作成](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-get-support)できます。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/scenarios"} -->
## Microsoft Entra Domain Services の一般的な展開シナリオ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/scenarios
- Service: entra-id / domain-services
- Article date: 2025-02-05
- Summary: Microsoft Entra Domain Services が価値を提供し、ビジネス ニーズを満たすための一般的なシナリオとユース ケースについて説明します。

Microsoft Entra Domain Services は、ドメイン参加、グループ ポリシー、ライトウェイト ディレクトリ アクセス プロトコル (LDAP)、Kerberos/NTLM 認証などのマネージド ドメイン サービスを提供します。 Microsoft Entra Domain Services は既存の Microsoft Entra テナントと統合されているため、ユーザーは既存の資格情報を使用してサインインできます。 クラウドにドメイン コントローラーをデプロイ、管理、修正する必要なく、これらのドメイン サービスを使用します。これにより、オンプレミス リソースの Azure へのリフト アンド シフトがスムーズになります。

この記事では、Microsoft Entra Domain Services が価値を提供し、それらのニーズを満たす一般的なビジネス シナリオについて説明します。

### クラウドで ID ソリューションを提供する一般的な方法

既存のワークロードをクラウドに移行する場合、ディレクトリ対応アプリケーションでは、オンプレミスの AD DS ディレクトリへの読み取りまたは書き込みアクセスに LDAP を使用できます。 Windows Server で実行されるアプリケーションは、通常、ドメインに参加している仮想マシン (VM) にデプロイされるため、グループ ポリシーを使用して安全に管理できます。 エンド ユーザーを認証するために、アプリケーションは Kerberos や NTLM 認証などの Windows 統合認証に依存する場合もあります。

IT 管理者は、多くの場合、次のいずれかのソリューションを使用して、Azure で実行されるアプリケーションに ID サービスを提供します。

- Azure で実行されるワークロードとオンプレミスの AD DS 環境間のサイト間 VPN 接続を構成します。
    - その後、オンプレミスのドメイン コントローラーは、VPN 接続を介して認証を提供します。
- Azure 仮想マシン (VM) を使用してレプリカ ドメイン コントローラーを作成し、オンプレミスから AD DS ドメイン/フォレストを拡張します。
    - Azure VM で実行されるドメイン コントローラーは、認証を提供し、オンプレミスの AD DS 環境間でディレクトリ情報をレプリケートします。
- Azure VM 上で実行されるドメイン コントローラーを使用して、Azure にスタンドアロン AD DS 環境をデプロイします。
    - Azure VM で実行されるドメイン コントローラーは認証を提供しますが、オンプレミスの AD DS 環境からレプリケートされるディレクトリ情報はありません。

これらの方法では、オンプレミス ディレクトリへの VPN 接続により、アプリケーションは一時的なネットワークの障害や停止に対して脆弱になります。 Azure で VM を使用してドメイン コントローラーをデプロイする場合、IT チームは VM を管理し、セキュリティ保護、修正プログラムの適用、監視、バックアップ、トラブルシューティングを行う必要があります。

Microsoft Entra Domain Services には、オンプレミスの AD DS 環境への VPN 接続を作成したり、Azure で VM を実行および管理して ID サービスを提供したりする必要がある代替手段が用意されています。 Microsoft Entra Domain Services は、マネージド サービスとして、ハイブリッド環境とクラウドのみの環境の両方に統合 ID ソリューションを作成する複雑さを軽減します。

### ハイブリッド組織向けの Microsoft Entra Domain Services

多くの組織では、クラウドとオンプレミスの両方のアプリケーション ワークロードを含むハイブリッド インフラストラクチャを実行しています。 リフト アンド シフト戦略の一環として Azure に移行されたレガシ アプリケーションでは、従来の LDAP 接続を使用して ID 情報を提供できます。 このハイブリッド インフラストラクチャをサポートするために、オンプレミスの AD DS 環境からの ID 情報を Microsoft Entra テナントに同期できます。 Microsoft Entra Domain Services は、オンプレミスのディレクトリ サービスへのアプリケーション接続を構成および管理する必要なく、ID ソースを Azure でこれらのレガシ アプリケーションに提供します。

オンプレミスと Azure の両方のリソースを実行するハイブリッド組織である Litware Corporation の例を見てみましょう。

[Image: オンプレミス同期を含むハイブリッド組織向けの Microsoft Entra Domain Services]

- ドメイン サービスを必要とするアプリケーションとサーバーのワークロードは、Azure の仮想ネットワークにデプロイされます。
    - これには、リフト アンド シフト戦略の一環として Azure に移行されたレガシ アプリケーションが含まれる場合があります。
- オンプレミス ディレクトリから Microsoft Entra テナントに ID 情報を同期するために、Litware Corporation は [Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/whatis-azure-ad-connect)をデプロイします。
    - 同期される ID 情報には、ユーザー アカウントとグループ メンバーシップが含まれます。
- Litware の IT チームは、Microsoft Entra テナントまたはピアリングされた仮想ネットワークで Microsoft Entra Domain Services を有効にします。
- Azure 仮想ネットワークにデプロイされたアプリケーションと VM では、ドメイン参加、LDAP 読み取り、LDAP バインド、NTLM および Kerberos 認証、グループ ポリシーなどの Microsoft Entra Domain Services 機能を使用できます。

Von Bedeutung

Microsoft Entra Connect は、オンプレミスの AD DS 環境との同期のためにのみインストールおよび構成する必要があります。 マネージド ドメインに Microsoft Entra Connect をインストールしてオブジェクトを Microsoft Entra ID に同期することはサポートされていません。

### クラウド専用組織向けの Microsoft Entra Domain Services

クラウド専用の Microsoft Entra テナントには、オンプレミスの ID ソースがありません。 たとえば、ユーザー アカウントとグループ メンバーシップは、Microsoft Entra ID で直接作成および管理されます。

次に、クラウド専用組織である Contoso がアイデンティティ用に Microsoft Entra ID を使用する例を見てみましょう。 すべてのユーザー ID、その資格情報、およびグループ メンバーシップは、Microsoft Entra ID で作成および管理されます。 オンプレミス ディレクトリから ID 情報を同期するための Microsoft Entra Connect の追加構成はありません。

[Image: オンプレミス同期のないクラウド専用組織向けの Microsoft Entra Domain Services]

- ドメイン サービスを必要とするアプリケーションとサーバーのワークロードは、Azure の仮想ネットワークにデプロイされます。
- Contoso の IT チームは、Microsoft Entra テナント (ピアリングされた仮想ネットワーク) に対して Microsoft Entra Domain Services を有効にします。
- Azure 仮想ネットワークにデプロイされたアプリケーションと VM では、ドメイン参加、LDAP 読み取り、LDAP バインド、NTLM および Kerberos 認証、グループ ポリシーなどの Microsoft Entra Domain Services 機能を使用できます。

### Azure 仮想マシンの安全な管理

1 つの AD 資格情報セットを使用できるように、Azure 仮想マシン (VM) を Microsoft Entra Domain Services マネージド ドメインに参加させることができます。 この方法を使用すると、各 VM 上のローカル管理者アカウントの管理や、環境間での個別のアカウントおよびパスワードの管理など、資格情報の管理に関する問題が軽減されます。

マネージド ドメインに参加している VM は、グループ ポリシーを使用して管理およびセキュリティ保護することもできます。 必要なセキュリティ ベースラインを VM に適用して、企業のセキュリティ ガイドラインに従ってそれらをロックダウンできます。 たとえば、グループ ポリシー管理機能を使用して、VM で起動できるアプリケーションの種類を制限できます。

[Image: Azure 仮想マシンの合理化された管理]

一般的なシナリオ例を見てみましょう。 サーバーやその他のインフラストラクチャが終了すると、Contoso は現在オンプレミスでホストされているアプリケーションをクラウドに移行したいと考えています。 現在の IT 標準では、企業アプリケーションをホストするサーバーをドメインに参加させ、グループ ポリシーを使用して管理する必要があることを義務付けています。

Contoso の IT 管理者は、ユーザーが会社の資格情報を使用してサインインできるため、管理を容易にするために、Azure にデプロイされた VM をドメインに参加することを好みます。 ドメインに参加している場合、グループ ポリシー オブジェクト (GPO) を使用して、必要なセキュリティ ベースラインに準拠するように VM を構成することもできます。 Contoso は、Azure で独自のドメイン コントローラーをデプロイ、監視、管理することを好まないでしょう。

Microsoft Entra Domain Services は、このユース ケースに最適です。 マネージド ドメインを使用すると、VM にドメイン参加し、1 つの資格情報セットを使用して、グループ ポリシーを適用できます。 また、マネージド ドメインであるため、ドメイン コントローラーを自分で構成して保守する必要はありません。

#### デプロイに関する注意事項

このユース ケースの例には、次のデプロイに関する考慮事項が適用されます。

- マネージド ドメインでは、既定で単一のフラットな組織単位 (OU) 構造が使用されます。 ドメインに参加している VM はすべて、単一の OU 内にあります。 必要に応じて、 [カスタム OU を](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/create-ou)作成できます。
- Microsoft Entra Domain Services では、ユーザーおよびコンピューターのコンテナーごとに組み込みの GPO が使用されます。 追加の制御のために、 [カスタム GPO を作成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/manage-group-policy) し、カスタム OU をターゲットにすることができます。
- Microsoft Entra Domain Services では、ベース AD コンピューター オブジェクト スキーマがサポートされています。 コンピューター オブジェクトのスキーマを拡張することはできません。

### LDAP バインド認証を使用するオンプレミス アプリケーションの移行とシフト

サンプル シナリオとして、Contoso には、何年も前に ISV から購入されたオンプレミス アプリケーションがあります。 アプリケーションは現在 ISV によってメンテナンス モードであり、アプリケーションに変更を要求することは非常にコストがかかります。 このアプリケーションには、Web フォームを使用してユーザー資格情報を収集し、オンプレミスの AD DS 環境への LDAP バインドを実行してユーザーを認証する Web ベースのフロントエンドがあります。

[Image: LDAP バインド]

Contoso は、このアプリケーションを Azure に移行したいと考えます。 アプリケーションは引き続き as-is動作し、変更は必要ありません。 さらに、ユーザーは、既存の企業資格情報を使用して、追加のトレーニングなしで認証できる必要があります。 アプリケーションが実行されているエンド ユーザーには透過的である必要があります。

このシナリオでは、Microsoft Entra Domain Services を使用すると、アプリケーションは認証プロセスの一部として LDAP バインドを実行できます。 構成またはユーザー エクスペリエンスに何ら変更を加えることなく、従来のオンプレミス アプリケーションを Azure にリフトアンドシフトし、そのアプリケーションで引き続きユーザーをシームレスに認証することができます。

#### デプロイに関する注意事項

このユース ケースの例には、次のデプロイに関する考慮事項が適用されます。

- アプリケーションがディレクトリを変更/書き込む必要がないことを確認します。 マネージド ドメインへの LDAP 書き込みアクセスはサポートされていません。
- マネージド ドメインに対してパスワードを直接変更することはできません。 エンド ユーザーは、 [Microsoft Entra のセルフサービス パスワード変更メカニズム](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/overview-authentication#self-service-password-reset) を使用するか、オンプレミス ディレクトリに対してパスワードを変更できます。 これらの変更は自動的に同期され、マネージド ドメインで使用できるようになります。

### LDAP 読み取りを使用してディレクトリにアクセスするオンプレミス アプリケーションのリフト アンド シフト

前のシナリオ例と同様に、Contoso には、ほぼ 10 年前に開発されたオンプレミスの基幹業務 (LOB) アプリケーションがあるとします。 このアプリケーションはディレクトリ対応であり、LDAP を使用して AD DS からユーザーに関する情報/属性を読み取るために設計されています。 アプリケーションが属性を変更したり、ディレクトリに書き込んだりすることはありません。

Contoso は、このアプリケーションを Azure に移行し、現在このアプリケーションをホストしている古くなったオンプレミス ハードウェアを廃止したいと考えています。 REST ベースの Microsoft Graph API などの最新のディレクトリ API を使用するようにアプリケーションを書き換えることはできません。 リフト アンド シフト オプションは、コードの変更やアプリケーションの書き換えを行わずに、クラウドで実行するようにアプリケーションを移行できる場合に必要です。

このシナリオを支援するために、Microsoft Entra Domain Services を使用すると、アプリケーションはマネージド ドメインに対して LDAP 読み取りを実行して、必要な属性情報を取得できます。 アプリケーションを書き直す必要がないため、Azure へのリフトアンドシフトを実施することにより、アプリの実行場所の変更に気づくことなくユーザーが引き続きアプリを使用できるようにすることができます。

#### デプロイに関する注意事項

このユース ケースの例には、次のデプロイに関する考慮事項が適用されます。

- アプリケーションがディレクトリを変更/書き込む必要がないことを確認します。 マネージド ドメインへの LDAP 書き込みアクセスはサポートされていません。
- アプリケーションにカスタム/拡張 Active Directory スキーマが必要ないことを確認します。 Microsoft Entra Domain Services では、スキーマの拡張はサポートされていません。

### オンプレミスのサービスまたはデーモン アプリケーションを Azure に移行する

一部のアプリケーションには複数の層が含まれていますが、そのうち 1 つの層では、データベースなどのバックエンド層に対して認証された呼び出しを実行する必要があります。 これらのシナリオでは、AD サービス アカウントが一般的に使用されます。 アプリケーションを Azure にリフトアンドシフトする場合、Microsoft Entra Domain Services を利用すれば、サービス アカウントを引き続き同じ方法で使用できます。 オンプレミスディレクトリから Microsoft Entra ID に同期されるのと同じサービス アカウントを使用するか、カスタム OU を作成してから、その OU に別のサービス アカウントを作成することができます。 どちらの方法でも、アプリケーションは引き続き同じ方法で機能し、他の層およびサービスに対して認証された呼び出しを行います。

[Image: WIA を使用したサービス アカウント]

このシナリオ例では、Contoso には、Web フロントエンド、SQL サーバー、バックエンド FTP サーバーを含むカスタムビルドのソフトウェア コンテナー アプリケーションがあります。 サービス アカウントを使用した Windows 統合認証は、WEB フロントエンドを FTP サーバーに対して認証します。 Web フロントエンドは、サービス アカウントとして実行するように設定されています。 バックエンド サーバーは、Web フロントエンドのサービス アカウントからのアクセスを承認するように構成されています。 Contoso は、このアプリケーションを Azure に移行するために、独自のドメイン コントローラー VM をクラウドにデプロイして管理することを望んでいません。

このシナリオでは、Web フロントエンド、SQL サーバー、および FTP サーバーをホストしているサーバーを Azure VM に移行し、マネージド ドメインに参加させることができます。 その後、VM は、アプリの認証目的でオンプレミス ディレクトリ内の同じサービス アカウントを使用できます。これは、Microsoft Entra Connect を使用して Microsoft Entra ID を介して同期されます。

#### デプロイに関する注意事項

このユース ケースの例には、次のデプロイに関する考慮事項が適用されます。

- アプリケーションで認証にユーザー名とパスワードが使用されていることを確認します。 証明書またはスマートカードベースの認証は、Microsoft Entra Domain Services ではサポートされていません。
- マネージド ドメインに対してパスワードを直接変更することはできません。 エンド ユーザーは、 [Microsoft Entra のセルフサービス パスワード変更メカニズム](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/overview-authentication#self-service-password-reset) を使用するか、オンプレミス ディレクトリに対してパスワードを変更できます。 これらの変更は自動的に同期され、マネージド ドメインで使用できるようになります。

### Azure での Windows Server リモート デスクトップ サービスのデプロイ

Microsoft Entra Domain Services を使用して、Azure にデプロイされたリモート デスクトップ サーバーにマネージド ドメイン サービスを提供できます。

この展開シナリオの詳細については、 [Microsoft Entra Domain Services と RDS 展開を統合する方法を参照してください](https://learn.microsoft.com/ja-jp/windows-server/remote/remote-desktop-services/rds-azure-adds)。

### ドメイン参加済み HDInsight クラスター

Apache Ranger が有効になっているマネージド ドメインに参加している Azure HDInsight クラスターを設定できます。 Apache Ranger を使用して Hive ポリシーを作成して適用し、データ サイエンティストなどのユーザーが Excel や Tableau などの ODBC ベースのツールを使用して Hive に接続できるようにします。 引き続き、HBase、Spark、Storm などの他のワークロードをドメイン参加済み HDInsight に追加する作業を続けます。

このデプロイ シナリオの詳細については、[ドメイン参加済み HDInsight クラスターを構成する方法](https://learn.microsoft.com/ja-jp/azure/hdinsight/domain-joined/apache-domain-joined-configure-using-azure-adds)を参照してください
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/scoped-synchronization"} -->
## Microsoft Entra Domain Services の範囲指定された同期 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/scoped-synchronization
- Service: entra-id / domain-services
- Article date: 2025-02-05
- Summary: Microsoft Entra 管理センターを使って、Microsoft Entra ID から Microsoft Entra Domain Services マネージド ドメインへの範囲指定された同期を構成する方法について説明します

Microsoft Entra Domain Services は、認証サービスを提供するため、Microsoft Entra ID からユーザーとグループを同期します。 ハイブリッド環境では、最初にオンプレミスの Active Directory Domain Services (AD DS) 環境のユーザーとグループが Microsoft Entra Connect を使用して Microsoft Entra ID に同期された後、Domain Services マネージド ドメインに同期されます。

既定では、Microsoft Entra ディレクトリのすべてのユーザーとグループがマネージド ドメインに同期されます。 一部のユーザーのみが Domain Services を使用する必要がある場合は、代わりにユーザーのグループのみを同期することを選択できます。 同期のフィルターは、オンプレミス、クラウドのみ、またはその両方のグループに対して実行できます。

この記事では、Microsoft Entra 管理センターを使って、範囲を指定した同期を構成した後、範囲を指定したユーザー セットを変更したり、無効にしたりする方法について説明します。 [これらの手順は、PowerShell を使用して行う](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/powershell-scoped-synchronization)こともできます。

[Image: グループ フィルター オプションのスクリーンショット。]

### 開始する前に

この記事を完了するには、以下のリソースと特権が必要です。

- 有効な Azure サブスクリプション
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付け](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)ます。
- Microsoft Entra Domain Services のマネージド ドメインが、Microsoft Entra テナントで有効にされ、構成されています。
    - 必要に応じて、[Microsoft Entra Domain Services マネージド ドメインを作成して構成する](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)チュートリアルを完了します。
- Domain Services の同期スコープを変更するには、テナントで[アプリケーション管理者](https://learn.microsoft.com/ja-jp/azure/active-directory/roles/permissions-reference#application-administrator)と[グループ管理者](https://learn.microsoft.com/ja-jp/azure/active-directory/roles/permissions-reference#groups-administrator)の Microsoft Entra ロールが必要です。

### 範囲指定された同期の概要

既定では、Microsoft Entra ディレクトリのすべてのユーザーとグループがマネージド ドメインに同期されます。 Microsoft Entra ID で作成されたユーザー アカウントのみに同期のスコープを設定することも、すべてのユーザーを同期することもできます。

マネージド ドメインにアクセスする必要があるユーザーのグループが少数の場合は、**[Filter by group entitlement](グループ エンタイトルメントでフィルター)** を選択して、それらのグループのみを同期できます。 この範囲指定された同期はグループベースのみです。 グループベースの範囲指定された同期を構成した場合、指定したグループに属するユーザー アカウントのみがマネージド ドメインに同期されます。 入れ子になったグループは同期されません。指定したグループのみが同期されます。

同期スコープは、マネージド ドメインを作成する前または後に変更できます。 同期のスコープは、アプリケーション識別子 `2565bd9d-da50-47d4-8b85-4c97f669dc36` を持つサービス プリンシパルによって定義されます。 スコープが失われないようにするには、サービス プリンシパルを削除または変更しないでください。 誤って削除された場合、同期スコープを復旧できません。

同期スコープを変更する場合は、次の点にご注意ください。

- 完全同期が行われます。
- マネージド ドメインで不要になったオブジェクトは削除されます。 新しいオブジェクトは、マネージド ドメインに作成されます。

同期プロセスの詳細については、[Microsoft Entra Domain Services での同期の理解](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/synchronization)に関する記事を参照してください。

### 範囲指定された同期を有効にする

Microsoft Entra 管理センターで範囲を指定した同期を有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、**Microsoft Entra Domain Services** を検索して選びます。 目的のマネージド ドメインを選択します (例: *aaddscontoso.com* )。
2. 左側のメニューで、 **[同期]** を選択します。
3. *[同期スコープ]* で、**[すべて]** または **[クラウドのみ]** を選択します。
4. 選択したグループ用に同期をフィルター処理するには、**[選択したグループの表示]** をクリックし、クラウドのみのグループ、オンプレミスのグループ、またはその両方のいずれを同期するかを選択します。 たとえば、次のスクリーンショットは、Microsoft Entra ID で作成された 3 つのグループのみを同期する方法を示しています。 これらのグループに属するユーザーのアカウントのみが Domain Services に同期されます。

    [Image: クラウドのみのグループでのフィルターを示すスクリーンショット。]
5. グループを追加するには、**[グループの追加]** をクリックし、追加するグループを検索して選択します。
6. すべての変更が行われたら、 **[同期スコープを保存します]** を選択します。

同期のスコープを変更すると、マネージド ドメインですべてのデータが再同期されます。 マネージド ドメインで不要になったオブジェクトは削除されます。また、再同期が完了するまでにはしばらく時間がかかる場合があります。

### 範囲指定された同期を変更する

マネージド ドメインにユーザーが同期されるグループの一覧を変更するには、次の手順を行います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、**Microsoft Entra Domain Services** を検索して選びます。 目的のマネージド ドメインを選択します (例: *aaddscontoso.com* )。
2. 左側のメニューで、 **[同期]** を選択します。
3. グループを追加するには、上部にある **[+ グループの追加]** を選択し、追加するグループを選択します。
4. 同期スコープからグループを削除するには、現在同期されているグループの一覧からそのグループを選択し、 **[グループの削除]** を選択します。
5. すべての変更が行われたら、 **[同期スコープを保存します]** を選択します。

同期のスコープを変更すると、マネージド ドメインですべてのデータが再同期されます。 マネージド ドメインで不要になったオブジェクトは削除されます。また、再同期が完了するまでにはしばらく時間がかかる場合があります。

### 範囲指定された同期を無効にする

マネージド ドメインのグループベースの範囲指定された同期を無効にするには、次の手順を行います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、**Microsoft Entra Domain Services** を検索して選びます。 目的のマネージド ドメインを選択します (例: *aaddscontoso.com* )。
2. 左側のメニューで、 **[同期]** を選択します。
3. **[Show selected groups](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/選択したグループを表示する)** のチェック ボックスをオフにし、**[同期スコープを保存します]** をクリックします。

同期のスコープを変更すると、マネージド ドメインですべてのデータが再同期されます。 マネージド ドメインで不要になったオブジェクトは削除されます。また、再同期が完了するまでにはしばらく時間がかかる場合があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/secure-remote-vm-access"} -->
## Microsoft Entra Domain Services でリモート VM アクセスをセキュリティで保護する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/secure-remote-vm-access
- Service: entra-id / domain-services
- Article date: 2025-02-05
- Summary: Microsoft Entra Domain Services マネージド ドメインでリモート デスクトップ サービスがデプロイされているネットワーク ポリシー サーバー (NPS) と Microsoft Entra 多要素認証を使用して、VM へのリモートアクセスをセキュリティで保護する方法について学習します。

Microsoft Entra Domain Services マネージド ドメインで実行される仮想マシン (VM) へのリモート アクセスをセキュリティで保護するには、リモート デスクトップ サービス (RDS) とネットワーク ポリシー サーバー (NPS) を使用できます。 Domain Services は、RDS 環境経由でアクセスを要求するときにユーザーを認証します。 セキュリティを強化するために、Microsoft Entra 多要素認証を統合して、サインイン イベント時に別の認証プロンプトを表示できます。 Microsoft Entra 多要素認証は、NPS の拡張機能を使用してこの機能を提供します。

重要

Domain Services マネージド ドメイン内の VM に安全に接続するには、仮想ネットワーク内でプロビジョニングするフル プラットフォーム マネージド PaaS サービスである Azure Bastion を使用することをお勧めします。 要塞ホストは、SSL 経由で Azure portal に直接、安全かつシームレスなリモート デスクトップ プロトコル (RDP) 接続を VM に提供します。 要塞ホスト経由で接続する場合、VM にはパブリック IP アドレスは必要ありません。また、ネットワーク セキュリティ グループを使用して、TCP ポート 3389 で RDP へのアクセスを公開する必要もありません。

サポートされているすべてのリージョンで Azure Bastion を使用することを強くお勧めします。 Azure Bastion を利用できないリージョンでは、Azure Bastion を利用できるようになるまで、この記事で説明されている手順に従ってください。 Domain Services ではすべての受信 RDP トラフィックが許可されるため、参加している VM にパブリック IP アドレスを割り当てる際は注意してください。

詳細については、[Azure Bastion](https://learn.microsoft.com/ja-jp/azure/bastion/bastion-overview) に関するページを参照してください。

この記事では、Domain Services で RDS を構成し、必要に応じて Microsoft Entra 多要素認証 NPS 拡張機能を使用する方法について説明します。

[Image: リモート デスクトップ サービス (RDS) の概要]

### 前提条件

この記事を完了するには、以下のリソースが必要です。

- 有効な Azure サブスクリプション
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付け](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)ます。
- Microsoft Entra テナントで有効化され、構成されている Microsoft Entra Domain Services のマネージド ドメイン。
    - 必要に応じて、[Microsoft Entra Domain Services のマネージド ドメインを作成して構成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)。
- Microsoft Entra Domain Services 仮想ネットワーク内に作成された*ワークロード*サブネット。
    - 必要に応じて、[Microsoft Entra Domain Services マネージド ドメイン用の仮想ネットワークを構成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-networking)。
- Microsoft Entra テナントの *AAD DC 管理者*グループのメンバーであるユーザー アカウント。

### リモート デスクトップ環境のデプロイと構成

開始するには、Windows Server 2016 または Windows Server 2019 を実行する Azure VM を少なくとも 2 つ以上作成します。 リモート デスクトップ (RD) 環境の冗長性と高可用性を実現するために、後でホストを追加して負荷分散することができます。

推奨される RDS デプロイには、次の 2 つの VM が含まれます。

- *RDGVM01* - RD 接続ブローカー サーバー、RD Web アクセス サーバー、および RD ゲートウェイ サーバーを実行します。
- *RDSHVM01* - RD セッション ホスト サーバーを実行します。

VM が Domain Services 仮想ネットワークの*ワークロード* サブネットにデプロイされていることを確認してから、VM をマネージド ドメインに参加させます。 詳細については、[Windows Server VM を作成してマネージド ドメインに参加させる方法](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-windows-vm)についての記事を参照してください。

RD 環境をデプロイするには、いくつかの手順が必要です。 既存の RD デプロイ ガイドは、特に変更することなくマネージド ドメインで使用できます。

1. *contosoadmin* などの *AAD DC 管理者*グループの一部であるアカウントを使用して、RD 環境用に作成された VM にサインインします。
2. RDS を作成および構成するには、既存の[リモート デスクトップ環境のデプロイ ガイド](https://learn.microsoft.com/ja-jp/windows-server/remote/remote-desktop-services/rds-deploy-infrastructure)を参照してください。 必要に応じて、Azure VM 全体に RD サーバー コンポーネントを配布します。
    - Domain Services に固有 - RD ライセンスを構成するときは、デプロイ ガイドに記載されているように、**[ユーザー単位]** ではなく、**[Per Device] (デバイス単位)** モードに設定します。
3. Web ブラウザーを使用してアクセスを提供する場合は、[ユーザーのリモート デスクトップ Web クライアントを設定します](https://learn.microsoft.com/ja-jp/windows-server/remote/remote-desktop-services/clients/remote-desktop-web-client-admin)。

RD をマネージド ドメインにデプロイすると、オンプレミスの AD DS ドメインの場合と同様に、サービスを管理および使用できます。

### NPS と Microsoft Entra 多要素認証 NPS 拡張機能を展開して構成する

ユーザー サインイン エクスペリエンスのセキュリティを強化するには、必要に応じて RD 環境を Microsoft Entra 多要素認証と統合できます。 この構成では、サインイン時にユーザー ID を確認するための別のプロンプトが表示されます。

この機能を提供するために、Microsoft Entra 多要素認証 NPS 拡張機能と共に、ネットワーク ポリシー サーバー (NPS) が環境にインストールされます。 この拡張機能はMicrosoft Entra ID と統合され、多要素認証プロンプトの状態を要求して返すことができます。

[Microsoft Entra 多要素認証を使用するには、ユーザーを登録する](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/howto-mfa-nps-extension#register-users-for-mfa)必要があります。他の Microsoft Entra ID ライセンスが必要な場合があります。 詳細については、「[Microsoft Entra のプランと価格](https://www.microsoft.com/security/business/microsoft-entra-pricing)」をご覧ください。

Microsoft Entra 多要素認証をリモート デスクトップ環境に統合するには、NPS サーバーを作成し、拡張機能をインストールします。

1. Domain Services 仮想ネットワーク内の*ワークロード* サブネットに接続されている、別の Windows Server 2016 または 2019 VM (*NPSVM01* など) を作成します。 VM をマネージド ドメインに参加させます。
2. *contosoadmin* などの *AAD DC 管理者*グループの一部であるアカウントとして、NPS VM にサインインします。
3. **サーバー マネージャー**で、 **[役割と機能の追加]** を選択し、 *[ネットワーク ポリシーとアクセス サービス]* ロールをインストールします。
4. RAS および IAS サーバー グループのフル コントロール アクセス許可を **AAD DC 管理者**グループに委任します。 この手順は、NPS または RADIUS サーバーのセットアップに必要です。
5. [Microsoft Entra 多要素認証 NPS 拡張機能をインストールして構成](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/howto-mfa-nps-extension)するには、既存のハウツー記事を参照してください。

NPS サーバーと Microsoft Entra 多要素認証 NPS 拡張機能をインストールしたら、次のセクションを完了して RD 環境で使用できるように構成します。

### リモート デスクトップ ゲートウェイと Microsoft Entra 多要素認証の統合

Microsoft Entra 多要素認証 NPS 拡張機能を統合するには、[ネットワーク ポリシー サーバー (NPS) 拡張機能と Microsoft Entra ID を使用してリモート デスクトップ ゲートウェイ インフラストラクチャを統合する方法](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/howto-mfa-nps-extension-rdg)に関する既存のハウツー記事を使用してください。

マネージド ドメインと統合するには、次の構成オプションが必要です。

1. [Active Directory に NPS サーバーを登録](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/howto-mfa-nps-extension-rdg#register-server-in-active-directory)しないでください。 マネージド ドメインでは、この手順は失敗します。
2. [ネットワーク ポリシーを構成した手順 4](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/howto-mfa-nps-extension-rdg#configure-network-policy) で、 **[ユーザー アカウントのダイヤルイン プロパティを無視する]** チェック ボックスもオンにします。
3. NPS サーバーと Microsoft Entra 多要素認証 NPS 拡張機能に Windows Server 2019 以降を使用する場合は、次のコマンドを実行して、NPS サーバーが正常に通信できるようにセキュリティで保護されたチャネルを更新します。

    ```powershell
    sc sidtype IAS unrestricted
    ```

ユーザーは、Microsoft Authenticator アプリでのテキスト メッセージやプロンプトなど、サインイン時に認証要素の入力を求められるようになりました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/secure-your-domain"} -->
## セキュリティで保護されたMicrosoft Entra Domain Services - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/secure-your-domain
- Service: entra-id / domain-services
- Article date: 2025-03-14
- Summary: Microsoft Entra Domain Servicesマネージド ドメインの脆弱な暗号、古いプロトコル、NTLM パスワード ハッシュ同期を無効にする方法について説明します。

既定では、Microsoft Entra Domain Servicesでは NTLM v1 や TLS v1 などの暗号を使用できます。 これらの暗号は一部のレガシ アプリケーションに必要な場合がありますが、弱いと見なされ、不要な場合は無効にする必要があります。 Microsoft Entra Connect を使用してオンプレミスのハイブリッド接続を使用している場合は、NTLM パスワード ハッシュの同期を無効にすることもできます。

この記事では、次のような設定を使用してマネージド ドメインを強化する方法について説明します。

- NTLM v1 および TLS v1 暗号を無効にする
- NTLM パスワード ハッシュ同期を無効にする
- Kerberos RC4 暗号化を無効にする (今後廃止予定のプロセスにおいて)
- Kerberos 防御を有効にする
- LDAP 署名
- LDAP チャネル バインディング

### 前提条件

この記事を完了するには、以下のリソースが必要です。

- アクティブなAzure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- サブスクリプションに関連付けられているMicrosoft Entra テナント。オンプレミスのディレクトリまたはクラウド専用ディレクトリと同期されます。
    - 必要に応じて、[Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)または[アカウントにAzureサブスクリプションを関連付けます](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)。
- Microsoft Entra テナントで有効化され、構成された Microsoft Entra Domain Services 管理ドメイン。
    - 必要に応じて、[Microsoft Entra Domain Servicesマネージド ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)を作成して構成します。
    - マネージド ドメインのセキュリティ設定を変更するには、テナントの[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)と[グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)のMicrosoft Entraロールが必要です。
    - マネージド ドメインのセキュリティ設定を変更するには、[Domain Services 共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#domain-services-contributor)Azureロールが必要です。

### セキュリティ設定を使用してドメインを強化する

1. [Azure ポータル](https://portal.azure.com)にサインインします。
2. **Microsoft Entra Domain Services**を検索して選択します。
3. 目的のマネージド ドメインを選択します (例: *aaddscontoso.com* )。
4. 左側で、**セキュリティの設定**を選択します。
5. 次の設定に対して **[有効]** または **[無効]** をクリックします。

    - **TLS 1.2 専用モード**
    - **NTLM v1 認証**
    - **NTLM パスワード同期**
    - **Kerberos RC4 暗号化**
    - **Kerberos 防御**
    - **LDAP 署名**
    - **LDAP チャネル バインディング**

    [Image: 脆弱な暗号と NTLM パスワード ハッシュ同期を無効するセキュリティの設定のスクリーンショット]

### Kerberos RC4 暗号化を無効にする

Kerberos の RC4 暗号化は廃止の道を切り開きます。 [CVE-2026-20833](https://www.cve.org/CVERecord?id=CVE-2026-20833) に関連する Windows のセキュリティ更新プログラムにより、既定の Kerberos KDC の動作が AES 優先に移行され、段階的に RC4 の使用が削減されます。 RC4 に明示的に依存しているワークロード、デバイス、またはサービスがない限り、マネージド ドメインのセキュリティ設定から RC4 を無効にする必要があります。

#### 非推奨のタイムライン

| Phase | 日付 | Behavior |
| --- | --- | --- |
| 初期デプロイ | 2026 年 1 月 13 日 | 更新プログラムでは、監査シグナルと準備コントロールが導入されます。 |
| 強制 (手動ロールバックが可能) | 2026 年 4 月 | 既定の Kerberos KDC 動作は、AES 優先に移行します。 RC4 に依存するシナリオは、明示的に構成されていない限り、失敗を開始できます。 |
| 強制 (最終) | 2026 年 7 月 | 更新により、ロールバックのサポートが削除され、強制が有効な状態が維持されます。 |

詳細については、 [CVE-2026-20833 に関連するサービス アカウント チケット発行の変更に対する RC4 の Kerberos KDC の使用を管理する方法](https://support.microsoft.com/en-us/topic/how-to-manage-kerberos-kdc-usage-of-rc4-for-service-account-ticket-issuance-changes-related-to-cve-2026-20833-1ebcda33-720a-4da8-93c1-b0496e1910dc)を参照してください。

#### Azure ポータルで RC4 をオフにする

1. [Azure ポータル](https://portal.azure.com)にサインインします。
2. **Microsoft Entra Domain Services**を検索して選択します。
3. 目的のマネージド ドメインを選択します (例: *aaddscontoso.com* )。
4. 左側で、**セキュリティの設定**を選択します。
5. **Kerberos RC4 暗号化**を**無効**に設定します。
6. **保存**を選びます。

#### PowerShell で RC4 をオフにする

PowerShell を使用して RC4 を無効にするには、 `KerberosRc4Encryption` プロパティを `Disabled` に設定します。

```powershell
$securitySettings = @{"DomainSecuritySettings"=@{"KerberosRc4Encryption"="Disabled"}}
Set-AzResource -Id $DomainServicesResource.ResourceId -Properties $securitySettings -ApiVersion "2021-03-01" -Verbose -Force
```

Warning

RC4 を無効にする前に、ワークロード、デバイス、またはサービス アカウントが RC4 暗号化に依存していないことを確認します。 セキュリティ [監査](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/security-audit-events) を有効にし、Kerberos チケット イベント ID **4768 と 4769** を監視することで、 **RC4** の依存関係を識別できます。 RC4 を一時的に必要とするワークロードの場合は、**CVE-2026-20833 のサポート記事**に記載されているように、影響を受けるサービス アカウント [msDS-SupportedEncryptionTypes](https://support.microsoft.com/en-us/topic/how-to-manage-kerberos-kdc-usage-of-rc4-for-service-account-ticket-issuance-changes-related-to-cve-2026-20833-1ebcda33-720a-4da8-93c1-b0496e1910dc) の値を RC4 を含むように構成します。

### TLS 1.2 の使用に対する Azure Policy のコンプライアンスを割り当てる

**Security 設定**に加えて、Microsoft Azure Policy には、TLS 1.2 の使用を強制する **Compliance** 設定があります。 ポリシーは、割り当てられるまで影響を受けません。 ポリシーが割り当てられると、 **[コンプライアンス]** に表示されます。

- 割り当てが **[監査]** の場合、Domain Services インスタンスが準拠すればコンプライアンスが報告されます。
- 割り当てが **[拒否]**の場合、TLS 1.2 が必要ない場合、コンプライアンスにより Domain Services インスタンスが作成されず、TLS 1.2 が必要になるまで Domain Services インスタンスを更新できません。

[Image: コンプライアンス設定のスクリーンショット]

### NTLM の失敗を監査する

NTLM パスワード同期を無効にするとセキュリティが向上しますが、多くのアプリケーションとサービスは、それを使用しないで動作するようには設計されていません。 たとえば、DNS サーバー管理や RDP など、リソースに IP アドレスを使用して接続しようとすると、アクセスが拒否されて失敗します。 NTLM パスワード同期を無効にし、アプリケーションまたはサービスが想定どおりに動作していない場合は、 **[ログオン/ログオフ]**&gt;**[ログオンの監査]** イベント カテゴリのセキュリティ監査を有効にすることで、NTLM 認証の失敗を確認できます。この場合、NTLM は、イベントの詳細で**認証パッケージ**として指定されます。 詳細については、[Microsoft Entra Domain Services のセキュリティ監査を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/security-audit-events)を参照してください。

### PowerShell を使用してドメインを強化する

必要に応じて、[インストールし、Azure PowerShell](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)を構成します。 [Connect-AzAccount](https://learn.microsoft.com/ja-jp/powershell/module/Az.Accounts/Connect-AzAccount) コマンドレットを使用して、Azure サブスクリプションにサインインしていることを確認します。

また、必要に応じて、[Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)をインストールします。 [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph) コマンドレットを使用して、Microsoft Entra テナントにサインインしていることを確認します。

脆弱な暗号スイートと NTLM 資格情報ハッシュ同期を無効にするには、Azure アカウントにサインインし、[Get-AzResource](https://learn.microsoft.com/ja-jp/powershell/module/az.resources/get-azresource) コマンドレットを使用して Domain Services リソースを取得します。

ヒント

[Get-AzResource](https://learn.microsoft.com/ja-jp/powershell/module/az.resources/get-azresource) コマンドを使用してエラーが発生した場合*Microsoft。AAD/DomainServices* リソースは存在しません。[すべてのAzureサブスクリプションと管理グループ](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/elevate-access-global-admin)を管理するためのアクセス権を付与します。

```powershell
Login-AzAccount

$DomainServicesResource = Get-AzResource -ResourceType "Microsoft.AAD/DomainServices"
```

次に、*DomainSecuritySettings* を定義して、以下のセキュリティ オプションを構成します。

1. NTLM v1 のサポートを無効にする。
2. オンプレミスの AD からの NTLM パスワード ハッシュの同期を無効にする。
3. TLS v1 を無効にする。
4. Kerberos RC4 暗号化を無効にします。
5. Kerberos 防御を有効にします。

重要

Domain Services マネージド ドメインで NTLM パスワード ハッシュ同期を無効にしている場合、ユーザーおよびサービス アカウントは LDAP 簡易バインドを実行できません。 LDAP 簡易バインドを実行する必要がある場合は、次のコマンドで、 *"SyncNtlmPasswords"="Disabled";* セキュリティ構成オプションを設定しないでください。

```powershell
$securitySettings = @{"DomainSecuritySettings"=@{"NtlmV1"="Disabled";"SyncNtlmPasswords"="Disabled";"TlsV1"="Disabled";"KerberosRc4Encryption"="Disabled";"KerberosArmoring"="Enabled"}}
```

最後に、[Set-AzResource](https://learn.microsoft.com/ja-jp/powershell/module/az.resources/set-azresource) コマンドレットを使用して、定義済みのセキュリティ設定をマネージド ドメインに適用します。 最初の手順の Domain Services リソースと、前の手順のセキュリティ設定を指定します。

```powershell
Set-AzResource -Id $DomainServicesResource.ResourceId -Properties $securitySettings -ApiVersion "2021-03-01" -Verbose -Force
```

セキュリティ設定がマネージド ドメインに適用されるまでに、しばらく時間がかかります。

重要

NTLM を無効にした後、Microsoft Entra Connect で完全なパスワード ハッシュ同期を実行して、マネージド ドメインからすべてのパスワード ハッシュを削除します。 NTLM を無効にしてもパスワード ハッシュ同期を強制しない場合は、ユーザー アカウントの NTLM パスワード ハッシュが削除されるのは、次回のパスワード変更時のみになります。 この動作により、認証方法として NTLM が使用されるシステムにキャッシュされた資格情報があれば、ユーザーは引き続きサインインすることができます。

NTLM パスワード ハッシュが Kerberos パスワード ハッシュと異なっていると、NTLM へのフォールバックは機能しません。 VM にマネージド ドメイン コントローラーへの接続がある場合は、キャッシュされた資格情報も機能しなくなります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/security-account-name"} -->
## SAM アカウント名 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/security-account-name
- Service: entra / fundamentals
- Article date: 2026-08-18
- Summary: Entra Domain Services の SAM アカウント名のサポート

セキュリティ アカウント マネージャー アカウント名 (sAMAccountName) と Microsoft Entra Domain Services の同期のサポートが強化され、パブリック プレビューが開始されました。

この機能を使用すると、Microsoft Entra IDの onPremisesSamAccountName 属性から sAMAccountName を設定できます。 既存のマネージド ドメインでは、新しい同期動作を有効にすることができます。 この機能を有効にする必要があります。

有効にすると、既存のハイブリッド ユーザーが同期中に更新されます。onPremisesSamAccountName 値を持たないクラウドのみのユーザーは、引き続き mailNickname ベースの生成を使用します。

### sAMAccountName 同期が必要なのはいつですか?

セキュリティ アカウント マネージャー アカウント名 (sAMAccountName) 属性は、ユーザーのレガシ ログオン名をActive Directory Domain Servicesに格納し、Microsoft Entra Domain Servicesのパブリック プレビューでの拡張同期によってサポートされるようになりました。 レガシ認証を使用するアプリケーションでは、通常、この値は一意であり、20 文字以内で、サポートされていない特殊文字が含まれる必要があります。

強化された sAMAccountName 同期により、ハイブリッド ID を持つ組織は、Microsoft Entra IDの onPremisesSamAccountName から sAMAccountName を設定できます。 これにより、アカウント名とオンプレミスの Active Directoryの整合性が維持され、既存の sAMAccountName 値に依存するアプリケーションやスクリプトとの互換性が向上します。

#### Prerequisites

- Microsoft Entra Domain Services の管理ドメイン。
- Enterprise または Premium SKU。 この機能は Standard SKU では使用できません。
- オンプレミスの Active Directoryから同期されたユーザーには、Microsoft Entra IDに onPremisesSamAccountName が設定されている必要があります。
- 設定を変更するには、管理者がアプリケーション管理者ロールとグループ管理者ロールの両方を持っている必要があります。

#### sAMAccountName 同期のしくみ

##### 既存のマネージド ドメイン

既存のマネージド ドメインでは、管理者がオンプレミスからの sAMAccountName 同期を有効にするまで、現在の動作が引き続き使用されます。

- **無効に設定**: Microsoft Entra Domain Servicesは、mailNickname またはユーザー プリンシパル名プレフィックスから sAMAccountName を生成します。 従来の制約を満たすために必要な場合は、切り捨てと重複除去が適用されます。
- **設定が有効**: Microsoft Entra Domain Services は、ハイブリッド ユーザーについて、Microsoft Entra ID の onPremisesSamAccountName から sAMAccountName を取得します。 既存のハイブリッド ユーザーは、同期中に更新されます。

onPremisesSamAccountName 値を持たないクラウドのみのユーザーは、引き続き mailNickname ベースの sAMAccountName 生成を使用します。

##### 新しいマネージド ドメイン

新しいマネージド ドメインの展開では、拡張同期が既定で有効になります。 ハイブリッド ユーザーの sAMAccountName 値は、Microsoft Entra IDの onPremisesSamAccountName から取得されます。 新しいドメインの展開では、samaccountName を有効にする必要があります。既定では無効になっています。

#### 拡張同期を使用する場合

組織で次のいずれかに該当する場合は、強化された同期を有効にすることを検討してください。

- ハイブリッド ID を使用し、オンプレミスの Active DirectoryとMicrosoft Entra Domain Servicesで同じ sAMAccountName 値が必要です。
- 特定の sAMAccountName 値に依存するアプリケーション、スクリプト、または認証ワークフローを実行します。
- ドメインに依存するワークロードをAzureに移行しており、既存の ID 形式を保持したいと考えています。
- アカウント名の生成、切り捨て、重複、または不一致によって発生する認証またはアプリケーション エラーが発生します。

#### 利点

- **アプリケーションの互換性の向上:** アプリケーションでは、ID 関連のコードを変更することなく、確立された sAMAccountName 値を引き続き使用できます。
- **ID の誤差の減少:** アカウント名は、オンプレミス環境とマネージド ドメイン環境間で一貫性が保たれます。
- **移行の簡略化:**ワークロードがAzureに移行すると、既存の ID 形式を保持できます。
- **認証の問題が少なくなります。** 管理者は、mailNickname またはユーザー プリンシパル名プレフィックスから sAMAccountName を生成することによって発生する不一致を回避できます。

#### 設定を有効にする前に

この設定を有効にすると、既存のハイブリッド ユーザーの sAMAccountName ソースが変更されます。

有効にする前に、次の手順を実行します。

- Microsoft Entra IDで onPremisesSamAccountName が正しく設定されていることを確認します。
- sAMAccountName を使用するアプリケーション、スクリプト、スケジュールされたタスク、アクセス制御の構成を確認します。
- 現在生成されている sAMAccountName 値に依存する可能性がある統合を特定します。
- 同期が完了した後に認証とアプリケーション アクセスを検証することを計画します。

オンプレミスからの sAMAccountName 同期を有効にする

1. アプリケーション管理者ロールとグループ管理者ロールの両方に割り当てられたアカウントを使用して、Microsoft Entra 管理センターにサインインします。
2. Microsoft Entra Domain Servicesを検索して選択します。
3. マネージド ドメインを選択します。
4. 左側のナビゲーションで、[セキュリティ設定] を選択します。
5. オンプレミスからの sAMAccountName 同期の場合は、[有効] を選択します。
6. 変更を 保存 します。

設定を有効にすると、Microsoft Entra Domain Servicesは既存のハイブリッド ユーザーの sAMAccountName を onPremisesSamAccountName から同期します。 そのソース属性を持たないクラウドのみのユーザーは、引き続き既存の生成された動作を使用します。

#### 構成を検証する

1. onPremisesSamAccountName 値が既知である代表的なハイブリッド ユーザーを選択します。
2. Microsoft Entra Domain Servicesの sAMAccountName 値がMicrosoft Entra IDのソース値と一致することを確認します。
3. sAMAccountName に依存するアプリケーションとスクリプトの認証をテストします。

ユーザーが予期した値を受け取らない場合は、onPremisesSamAccountName がMicrosoft Entra IDに設定されていることを確認し、拡張同期設定が有効になっていることを確認します。 セキュリティ アカウント マネージャー アカウント名 (sAMAccountName) と Microsoft Entra Domain Services の同期のサポートが強化され、パブリック プレビューが開始されました。

この機能を使用すると、Microsoft Entra IDの onPremisesSamAccountName 属性から sAMAccountName を設定できます。 既存のマネージド ドメインでは新しい同期動作を有効にできますが、新しいマネージド ドメインでは既定で有効になっています。

有効にすると、既存のハイブリッド ユーザーが同期中に更新されます。onPremisesSamAccountName 値を持たないクラウドのみのユーザーは、引き続き mailNickname ベースの生成を使用します。

### セキュリティ アカウント マネージャー アカウント名

セキュリティ アカウント マネージャー アカウント名 (sAMAccountName) 属性は、ユーザーのレガシ ログオン名をActive Directory Domain Servicesに格納し、Microsoft Entra Domain Servicesのパブリック プレビューでの拡張同期によってサポートされるようになりました。 レガシ認証を使用するアプリケーションでは、通常、この値は一意であり、20 文字以内で、サポートされていない特殊文字が含まれる必要があります。

強化された sAMAccountName 同期により、ハイブリッド ID を持つ組織は、Microsoft Entra IDの onPremisesSamAccountName から sAMAccountName を設定できます。 これにより、アカウント名とオンプレミスの Active Directoryの整合性が維持され、既存の sAMAccountName 値に依存するアプリケーションやスクリプトとの互換性が向上します。

#### Prerequisites

- Microsoft Entra Domain Services の管理ドメイン。
- Enterprise または Premium SKU。 この機能は Standard SKU では使用できません。
- オンプレミスの Active Directoryから同期されたユーザーには、Microsoft Entra IDに onPremisesSamAccountName が設定されている必要があります。
- 設定を変更するには、管理者がアプリケーション管理者ロールとグループ管理者ロールの両方を持っている必要があります。

#### sAMAccountName 同期のしくみ

##### 既存のマネージド ドメイン

既存のマネージド ドメインでは、管理者がオンプレミスからの sAMAccountName 同期を有効にするまで、現在の動作が引き続き使用されます。

- **無効に設定**: Microsoft Entra Domain Servicesは、mailNickname またはユーザー プリンシパル名プレフィックスから sAMAccountName を生成します。 従来の制約を満たすために必要な場合は、切り捨てと重複除去が適用されます。
- **設定が有効**: Microsoft Entra Domain Services は、ハイブリッド ユーザーについて、Microsoft Entra ID の onPremisesSamAccountName から sAMAccountName を取得します。 既存のハイブリッド ユーザーは、同期中に更新されます。

onPremisesSamAccountName 値を持たないクラウドのみのユーザーは、引き続き mailNickname ベースの sAMAccountName 生成を使用します。

##### 新しいマネージド ドメイン

新しいマネージド ドメインの展開では、拡張同期が既定で有効になります。 ハイブリッド ユーザーの sAMAccountName 値は、Microsoft Entra IDの onPremisesSamAccountName から取得されるため、この動作を変更することはできません。

#### 拡張同期を使用する場合

組織で次のいずれかに該当する場合は、強化された同期を有効にすることを検討してください。

- ハイブリッド ID を使用し、オンプレミスの Active DirectoryとMicrosoft Entra Domain Servicesで同じ sAMAccountName 値が必要です。
- 特定の sAMAccountName 値に依存するアプリケーション、スクリプト、または認証ワークフローを実行します。
- ドメインに依存するワークロードをAzureに移行しており、既存の ID 形式を保持したいと考えています。
- アカウント名の生成、切り捨て、重複、または不一致によって発生する認証またはアプリケーション エラーが発生します。

#### メリット

- **アプリケーションの互換性の向上:** アプリケーションでは、ID 関連のコードを変更することなく、確立された sAMAccountName 値を引き続き使用できます。
- **ID の誤差の減少:** アカウント名は、オンプレミス環境とマネージド ドメイン環境間で一貫性が保たれます。
- **移行の簡略化:**ワークロードがAzureに移行すると、既存の ID 形式を保持できます。
- **認証の問題が少なくなります。** 管理者は、mailNickname またはユーザー プリンシパル名プレフィックスから sAMAccountName を生成することによって発生する不一致を回避できます。

#### 設定を有効にする前に

この設定を有効にすると、既存のハイブリッド ユーザーの sAMAccountName ソースが変更されます。

有効にする前に、次の手順を実行します。

- Microsoft Entra IDで onPremisesSamAccountName が正しく設定されていることを確認します。
- sAMAccountName を使用するアプリケーション、スクリプト、スケジュールされたタスク、アクセス制御の構成を確認します。
- 現在生成されている sAMAccountName 値に依存する可能性がある統合を特定します。
- 同期が完了した後に認証とアプリケーション アクセスを検証することを計画します。

オンプレミスからの sAMAccountName 同期を有効にする

1. アプリケーション管理者ロールとグループ管理者ロールの両方に割り当てられたアカウントを使用して、Microsoft Entra 管理センターにサインインします。
2. Microsoft Entra Domain Servicesを検索して選択します。
3. マネージド ドメインを選択します。
4. 左側のナビゲーションで、[セキュリティ設定] を選択します。
5. オンプレミスからの sAMAccountName 同期の場合は、[有効] を選択します。
6. 変更を 保存 します。

設定を有効にすると、Microsoft Entra Domain Servicesは既存のハイブリッド ユーザーの sAMAccountName を onPremisesSamAccountName から同期します。 そのソース属性を持たないクラウドのみのユーザーは、引き続き既存の生成された動作を使用します。

#### 構成を検証する

1. onPremisesSamAccountName 値が既知である代表的なハイブリッド ユーザーを選択します。
2. Microsoft Entra Domain Servicesの sAMAccountName 値がMicrosoft Entra IDのソース値と一致することを確認します。
3. sAMAccountName に依存するアプリケーションとスクリプトの認証をテストします。
4. ユーザーが予期した値を受け取らない場合は、onPremisesSamAccountName がMicrosoft Entra IDに設定されていることを確認し、拡張同期設定が有効になっていることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/security-audit-events"} -->
## Microsoft Entra Domain Servicesのセキュリティ監査と DNS 監査を有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/security-audit-events
- Service: entra-id / domain-services
- Article date: 2025-02-19
- Summary: セキュリティ監査を有効にして、Microsoft Entra Domain Servicesの分析とアラートのイベントのログ記録を一元化する方法について説明します

Azure は、Microsoft Entra Domain Services のセキュリティおよび DNS 監査イベントを対象リソースにストリーミングできます。 これらのリソースには、Azure Storage、Azure Log Analytics ワークスペース、またはAzure Event Hubsが含まれます。 セキュリティ監査イベントを有効にすると、Domain Services は、選択されたカテゴリのすべての監査対象イベントを対象のリソースに送信します。

イベントをAzureストレージにアーカイブし、Azure Event Hubsを使用してセキュリティ情報およびイベント管理 (SIEM) ソフトウェア (または同等の) にイベントをストリーミングしたり、独自の分析を行ったり、Microsoft Entra 管理センターのAzure Log Analyticsワークスペースを使用したりできます。

### セキュリティ監査先

Domain Services セキュリティ監査のターゲット リソースとして、Azure Storage、Azure Event Hubs、またはAzure Log Analyticsワークスペースを使用できます。 これらの出力先は、組み合わせて使用できます。 たとえば、セキュリティ監査イベントをアーカイブするためにAzure Storageを使用できますが、Azure Log Analytics ワークスペースを使用して、短期的に情報を分析してレポートすることができます。

次の表は、対象となるリソースの種類ごとのシナリオの概要を示しています。

重要

Domain Services のセキュリティ監査を有効にする前に、ターゲット リソースを作成する必要があります。 これらのリソースは、Microsoft Entra 管理センター、Azure PowerShell、またはAzure CLIを使用して作成できます。

| ターゲット リソース | シナリオ |
| --- | --- |
| Azure Storage | 主なニーズがセキュリティ監査イベントをアーカイブ用に格納することである場合は、このターゲットを使用してください。 他のターゲットをアーカイブの目的で使用することも可能ですが、これらのターゲットはアーカイブ以外の追加機能を提供しています。 Domain Services セキュリティ監査イベントを有効にする前に、最初に [Azure Storage アカウントを作成します](https://learn.microsoft.com/ja-jp/azure/storage/common/storage-account-create)。 |
| Azure Event Hubs | 主なニーズがデータ分析ソフトウェアやセキュリティ情報およびイベント管理 (SIEM) ソフトウェアなどの追加ソフトウェアとセキュリティ監査イベントを共有することである場合は、このターゲットを使用してください。Domain Services セキュリティ監査イベントを有効にする前に、 Microsoft Entra 管理センター |
| Azure Log Analytics ワークスペース | このターゲットは、Microsoft Entra 管理センターからのセキュリティで保護された監査を直接分析して確認することが主なニーズである場合に使用する必要があります。Domain Services のセキュリティ監査イベントを有効にする前に、[Microsoft Entra 管理センターで Log Analytics ワークスペースを作成します。](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/quick-create-workspace) |

### Microsoft Entra 管理センターを使用してセキュリティ監査イベントを有効にする

Microsoft Entra 管理センターを使用して Domain Services セキュリティ監査イベントを有効にするには、次の手順を実行します。

重要

Domain Services のセキュリティ監査は、さかのぼって適用されません。 過去のイベントを取得したり再生したりすることはできません。 Domain Services では、セキュリティ監査を有効にした後に発生するイベントのみ送信できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. **Microsoft Entra Domain Services**を検索して選択します。 目的のマネージド ドメインを選択します (例: *aaddscontoso.com* )。
3. Domain Services ウィンドウで、左側にある **[診断設定]** を選択します。
4. 診断は、既定では構成されていません。 作業を開始するには、 **[診断設定の追加]** を選択します。

    [Image: Microsoft Entra Domain Services] の診断設定を追加する
5. 診断構成の名前を入力します (例: *aadds-auditing*)。

    目的のセキュリティまたは DNS の監査の宛先のチェックボックスをオンにします。 Log Analytics ワークスペース、Azure Storage アカウント、Azure イベント ハブ、パートナー ソリューションから選択できます。 これらの宛先リソースは、Azure サブスクリプションに既に存在している必要があります。 このウィザードでは、出力先リソースを作成できません。

    - **Azure Log アナリティクス ワークスペース**
        - [**Send to Log Analytics** を選択し、監査イベントの格納に使用する **Subscription** と **Log Analytics Workspace** を選択します。
    - **Azure Storage**
        - **[ストレージ アカウントへのアーカイブ]** を選択し、 **[構成]** を選択します。
        - 監査イベントのアーカイブに使用する **[サブスクリプション]** と **[ストレージ アカウント]** を選択します。
        - 準備ができたら、 **[OK]** を選択します。
    - **Azure イベント ハブ**
        - **[イベント ハブへのストリーム]** を選択し、 **[構成]** をクリックします。
        - **[サブスクリプション]** および **[イベント ハブ名前空間]** を選択します。 必要に応じて、 **[イベント ハブ名]** を選択してから、 **[イベント ハブ ポリシー名]** を選択します。
        - 準備ができたら、 **[OK]** を選択します。
    - **パートナー ソリューション**
        - **[Send to partner solution] (パートナー ソリューションへの送信)** を選択してから、監査イベントの保存に使用する **[サブスクリプション]** と **[宛先]** を選択します。
6. 特定のターゲット リソースに含めるログのカテゴリを選択します。 監査イベントをAzure Storage アカウントに送信する場合は、データを保持する日数を定義するアイテム保持ポリシーを構成することもできます。 既定の設定である *0* では、すべてのデータが保持され、一定期間後にイベントがローテーションされることはありません。

    1 つの構成内の各ターゲット リソースについて、さまざまなログ カテゴリを選択することができます。 この機能を使用すると、Log Analyticsに保持するログ カテゴリや、アーカイブするログ カテゴリなどを選択できます。
7. 完了したら、 **[保存]** を選択して変更をコミットします。 構成が保存されるとすぐに、ターゲット リソースが Domain Services の監査イベントの受信を開始します。

### Azure PowerShellを使用してセキュリティおよび DNS 監査イベントを有効にする

Azure PowerShellを使用して Domain Services のセキュリティイベントと DNS 監査イベントを有効にするには、次の手順を実行します。 必要に応じて、まず[Azure PowerShell モジュールをインストールし、Azure サブスクリプション](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に接続します。

重要

Domain Services の監査は、さかのぼって適用されません。 過去のイベントを取得したり再生したりすることはできません。 Domain Services では、監査を有効にした後に発生するイベントのみ送信できます。

1. [Connect-AzAccount](https://learn.microsoft.com/ja-jp/powershell/module/Az.Accounts/Connect-AzAccount) コマンドレットを使用して、Azure サブスクリプションに対して認証します。 メッセージが表示されたら、アカウント資格情報を入力します。

    ```azurepowershell
    Connect-AzAccount
    ```
2. 監査イベントのターゲット リソースを作成します。

    - **Azure Log Analytic ワークスペース** - [Azure PowerShell](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/powershell-workspace-configuration) を使用してLog Analytics ワークスペースを作成します。
    - Azure storage Azure PowerShell
    - **Azure イベント ハブ** - [Azure PowerShell](https://learn.microsoft.com/ja-jp/azure/event-hubs/event-hubs-quickstart-powershell) を使用してイベント ハブを作成します。 また、[New-AzEventHubAuthorizationRule](https://learn.microsoft.com/ja-jp/powershell/module/az.eventhub/new-azeventhubauthorizationrule) コマンドレットを使用して、イベント ハブ*名前空間*に Domain Services のアクセス許可を付与する承認規則を作成しなければならない場合もあります。 承認規則には、**管理**、**リッスン**、および**送信**権限を含める必要があります。

        重要

        イベント ハブ自体ではなく、イベント ハブ名前空間に承認規則を設定していることを確認します。
3. [Get AzResource](https://learn.microsoft.com/ja-jp/powershell/module/az.resources/get-azresource) コマンドレットを使用して、Domain Services 管理対象ドメインのリソース ID を取得します。 *$aadds.ResourceId* という名前の変数を作成して、値を保持します。

    ```azurepowershell
    $aadds = Get-AzResource -name aaddsDomainName
    ```
4. [Set-AzDiagnosticSetting](https://learn.microsoft.com/ja-jp/powershell/module/az.monitor/set-azdiagnosticsetting) コマンドレットを使用して、Microsoft Entra Domain Services の監査イベントのためにターゲットリソースとしてAzure診断設定を構成します。 次の例で、変数 *$aadds.ResourceId* は前の手順から使用しているものです。

    - **Azure storage** - *storageAccountId* をストレージ アカウント名に置き換えます。

        ```powershell
        Set-AzDiagnosticSetting `
            -ResourceId $aadds.ResourceId `
            -StorageAccountId storageAccountId `
            -Enabled $true
        ```
    - **Azure イベント ハブ** - *eventHubName* をイベント ハブの名前に置き換え、*eventHubRuleId* を承認規則 ID に置き換えます。

        ```powershell
        Set-AzDiagnosticSetting -ResourceId $aadds.ResourceId `
            -EventHubName eventHubName `
            -EventHubAuthorizationRuleId eventHubRuleId `
            -Enabled $true
        ```
    - **Azure Log Analytic ワークスペース** - *workspaceId* をLog Analytics ワークスペースの ID に置き換えます。

        ```powershell
        Set-AzureRmDiagnosticSetting -ResourceId $aadds.ResourceId `
            -WorkspaceID workspaceId `
            -Enabled $true
        ```

### Azure Monitorを使用してセキュリティイベントと DNS 監査イベントのクエリと表示を行う

Log Analytic ワークスペースを使用すると、Azure Monitorと Kusto クエリ言語を使用して、セキュリティおよび DNS 監査イベントを表示および分析できます。 このクエリ言語は、読みやすい構文で強力な分析機能を提供する読み取り専用の用途向けに設計されています。 Kusto クエリ言語の使用を開始する方法の詳細については、次の記事を参照してください。

- [Azure Monitor ドキュメント](https://learn.microsoft.com/ja-jp/azure/azure-monitor/)
- [Azure Monitor の Log Analytics を開始する](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/log-analytics-tutorial)
- Azure Monitor
- [Log Analytics データのダッシュボードを作成して共有します](https://learn.microsoft.com/ja-jp/azure/azure-monitor/visualize/tutorial-logs-dashboards)

次のサンプル クエリを使用して、Domain Services からの監査イベントの分析を開始できます。

#### サンプル クエリ 1

直近 7 日間のすべてのアカウント ロックアウト イベントを表示します。

```Kusto
AADDomainServicesAccountManagement
| where TimeGenerated >= ago(7d)
| where OperationName has "4740"
```

#### サンプル クエリ 2

2020 年 6 月 3 日午前 9 時から 2020 年 6 月 10 日午前 0 時までのすべてのアカウント ロックアウト イベント (*4740*) を、日時の昇順で並び替えて表示します。

```Kusto
AADDomainServicesAccountManagement
| where TimeGenerated >= datetime(2020-06-03 09:00) and TimeGenerated <= datetime(2020-06-10)
| where OperationName has "4740"
| sort by TimeGenerated asc
```

#### サンプル クエリ 3

今から 7 日前の user 名のアカウントのサインイン イベントを表示します。

```Kusto
AADDomainServicesAccountLogon
| where TimeGenerated >= ago(7d)
| where "user" == tolower(extract("Logon Account:\t(.+[0-9A-Za-z])",1,tostring(ResultDescription)))
```

#### サンプル クエリ 4

7 日前の時点から、user という名前のアカウントが間違ったパスワード (*0xC0000006a*) を使用してサインインしようとしたイベントを表示します。

```Kusto
AADDomainServicesAccountLogon
| where TimeGenerated >= ago(7d)
| where "user" == tolower(extract("Logon Account:\t(.+[0-9A-Za-z])",1,tostring(ResultDescription)))
| where "0xc000006a" == tolower(extract("Error Code:\t(.+[0-9A-Fa-f])",1,tostring(ResultDescription)))
```

#### サンプル クエリ 5

今から 7 日前までの user という名前のアカウントのサインイン イベントで、ロックアウトされたアカウントにサインインしようとしたもの (*0xC0000234*) を表示します。

```Kusto
AADDomainServicesAccountLogon
| where TimeGenerated >= ago(7d)
| where "user" == tolower(extract("Logon Account:\t(.+[0-9A-Za-z])",1,tostring(ResultDescription)))
| where "0xc0000234" == tolower(extract("Error Code:\t(.+[0-9A-Fa-f])",1,tostring(ResultDescription)))
```

#### サンプル クエリ 6

今から 7 日前までに、すべてのロックアウトされたユーザーに対してサインインしようとしたアカウント サインイン イベントの数を表示します。

```Kusto
AADDomainServicesAccountLogon
| where TimeGenerated >= ago(7d)
| where "0xc0000234" == tolower(extract("Error Code:\t(.+[0-9A-Fa-f])",1,tostring(ResultDescription)))
| summarize count()
```

#### サンプル クエリ 7

過去 7 日間に RC4 暗号化を使用したすべての Kerberos チケット付与 (イベント ID 4768) イベントとサービス チケット (イベント ID 4769) イベントを表示して、RC4 にまだ依存しているワークロードとサービス アカウントを特定します。

```Kusto
let EncryptionTypeFromHex = (hex_value: int) {
    case(
        hex_value == 0x1, "DES-CRC",
        hex_value == 0x3, "DES-MD5",
        hex_value == 0x11, "AES128-SHA96",
        hex_value == 0x12, "AES256-SHA96",
        hex_value == 0x13, "AES128-SHA256",
        hex_value == 0x14, "AES256-SHA384",
        hex_value == 0x17, "RC4",
        "Unknown"
    )
};
AADDomainServicesAccountLogon
| where TimeGenerated >= ago(7d)
| where OperationName has "4768" or OperationName has "4769"
| parse ResultDescription with * "Ticket Encryption Type:\t" EncryptionType "\n" *
| where EncryptionType has "0x17"
| parse ResultDescription with * "Account Name:\t\t" AccountName "\n" *
| parse ResultDescription with * "Service Name:\t\t" ServiceName "\n" *
| extend EncryptionTypeName = EncryptionTypeFromHex(toint(EncryptionType))
| project TimeGenerated, OperationName, AccountName, ServiceName, EncryptionType, EncryptionTypeName
| summarize Count = count() by AccountName, ServiceName, EncryptionType, EncryptionTypeName
| order by Count desc
```

Tip

暗号化の種類 `0x17` は RC4-HMAC を示します。 マネージド ドメインの [セキュリティ設定](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/secure-your-domain)で RC4 を無効にする前に、このクエリを使用して RC4 の依存関係を特定します。 RC4 の非推奨タイムラインの詳細については、 [CVE-2026-20833](https://www.cve.org/CVERecord?id=CVE-2026-20833) を参照してください。

### セキュリティと DNS のイベント カテゴリを監査する

Domain Services セキュリティと DNS の監査は、従来の AD DS ドメイン コントローラーの従来の監査に沿っています。 ハイブリッド環境では、既存の監査パターンを再利用できるため、イベントを分析するときに同じロジックを使用することができます。 トラブルシューティングまたは分析が必要なシナリオに応じて、さまざまな監査イベントのカテゴリを対象にする必要があります。

次の監査イベントのカテゴリを利用できます。

| 監査のカテゴリ名 | 説明 |
| --- | --- |
| アカウント ログオン | ドメイン コントローラーまたはローカルのセキュリティ アカウント マネージャー (SAM) アカウントのデータの認証に対する試行を監査します。-ログオンおよびログオフ ポリシーの設定とイベントは、特定のコンピューターへのアクセス試行を追跡します。 このカテゴリの設定とイベントは、使用されるアカウントのデータベースに注目します。 このカテゴリには、次のサブカテゴリが含まれます。 - [資格情報の監査と検証](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-credential-validation) - [Kerberos 認証サービスの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-kerberos-authentication-service) - [Kerberos サービス チケット操作の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-kerberos-service-ticket-operations) - [その他のログオン/ログオフ イベントの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-other-logonlogoff-events) |
| アカウント管理 | ユーザーおよびコンピューター アカウントとグループの変更を監査します。 このカテゴリには、次のサブカテゴリが含まれます。 - [アプリケーション グループ管理の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-application-group-management) - [コンピューター アカウント管理の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-computer-account-management) - [配布グループ管理の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-distribution-group-management) - [その他のアカウント管理の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-other-account-management-events) - [セキュリティ グループ管理の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-security-group-management) - [ユーザー アカウント管理の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-user-account-management) |
| DNS サーバー | DNS 環境の変更を監査します。 このカテゴリには、次のサブカテゴリが含まれます。 - [DNSServerAuditsDynamicUpdates (プレビュー)](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2012-R2-and-2012/dn800669%28v=ws.11%29#audit-and-analytic-event-logging) - [DNSServerAuditsGeneral (プレビュー)](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2012-R2-and-2012/dn800669%28v=ws.11%29#audit-and-analytic-event-logging) |
| 詳細追跡 | そのコンピューター上の個々のアプリケーションとユーザーのアクティビティを監査し、コンピューターの使用方法について理解します。 このカテゴリには、次のサブカテゴリが含まれます。 - [DPAPI アクティビティの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-dpapi-activity) - [PNP アクティビティの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-pnp-activity) - [プロセス作成の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-process-creation) - [監査プロセスの終了](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-process-termination) - [RPC イベントの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-rpc-events) |
| ディレクトリ サービス アクセス | Active Directory Domain Services (AD DS) 内のオブジェクトへのアクセスと変更の試行を監査します。 これらの監査イベントは、ドメイン コントローラーにのみ記録されます。 このカテゴリには、次のサブカテゴリが含まれます。 - [詳細なディレクトリ サービスのレプリケーションの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-detailed-directory-service-replication) - [ディレクトリ サービスへのアクセスの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-directory-service-access) - [ディレクトリ サービス変更の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-directory-service-changes) - [ディレクトリ サービスのレプリケーションの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-directory-service-replication) |
| ログオン ログオフ | 対話的またはネットワーク経由でのコンピューターへのログオンの試行を監査します。 これらのイベントは、ユーザー アクティビティを追跡し、ネットワーク リソースに対する攻撃の可能性を識別するために役立ちます。 このカテゴリには、次のサブカテゴリが含まれます。 - [アカウントのロックアウトの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-account-lockout) - [ユーザー/デバイスの要求の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-user-device-claims) - [IPsec 拡張モードの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-ipsec-extended-mode) - [グループ メンバーシップの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-group-membership) - [IPsec メイン モードの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-ipsec-main-mode) - [IPsec クイック モードの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-ipsec-quick-mode) - [ログオフの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-logoff) - [ログオンの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-logon) - [ネットワーク ポリシー サーバーの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-network-policy-server) - [その他のログオン/ログオフ イベントの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-other-logonlogoff-events) - [特別なログオンの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-special-logon) |
| オブジェクト アクセス | ネットワークまたはコンピューター上の特定のオブジェクトまたはオブジェクトの型へのアクセスの試行を監査します。 このカテゴリには、次のサブカテゴリが含まれます。 - [監査アプリケーションが生成されました](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-application-generated) - [監査認証サービス](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-certification-services) - [詳細なファイル共有の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-detailed-file-share) - [ファイル共有の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-file-share) - [ファイル システムの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-file-system) - [フィルタリング プラットフォーム接続の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-filtering-platform-connection) - [フィルタリング プラットフォームのパケット破棄の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-filtering-platform-packet-drop) - [ハンドル操作の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-handle-manipulation) - [カーネル オブジェクトの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-kernel-object) - [その他のオブジェクト アクセス イベントの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-other-object-access-events) - [レジストリの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-registry) - [リムーバブル記憶域の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-removable-storage) - [SAM の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-sam) - [集約型アクセス ポリシー ステージングの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-central-access-policy-staging) |
| ポリシーの変更 | ローカル システムまたはネットワークの重要なセキュリティ ポリシーの変更を監査します。 通常、ポリシーは管理者がネットワーク リソースをセキュリティで保護するために設定します。 これらのポリシーの変更や変更の試行を監視することは、ネットワークのセキュリティ管理において重要な側面となる場合があります。 このカテゴリには、次のサブカテゴリが含まれます。 - [監査ポリシー変更の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-audit-policy-change) - [認証ポリシー変更の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-authentication-policy-change) - [承認ポリシー変更の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-authorization-policy-change) - [フィルタリング プラットフォームのポリシー変更の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-filtering-platform-policy-change) - [MPSSVC ルールレベルのポリシー変更の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-mpssvc-rule-level-policy-change) - [その他のポリシー変更の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-other-policy-change-events) |
| 特権の使用 | 1 つまたは複数のシステムで特定のアクセス許可の使用を監査します。 このカテゴリには、次のサブカテゴリが含まれます。 - [機密性が高くない特権の使用の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-non-sensitive-privilege-use) - [機密性が高い特権の使用の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-sensitive-privilege-use) - [その他の特権の使用イベントの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-other-privilege-use-events) |
| システム | 他のカテゴリに含まれておらず、セキュリティに影響を及ぼす可能性があるコンピューターへのシステム レベルの変更を監査します。 このカテゴリには、次のサブカテゴリが含まれます。 - [IPsec ドライバーの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-ipsec-driver) - [その他のシステム イベントの監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-other-system-events) - [セキュリティ状態の変更の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-security-state-change) - [セキュリティ システム拡張機能の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-security-system-extension) - [システムの整合性の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-system-integrity) |

### カテゴリごとのイベント ID

Domain Services セキュリティと DNS の監査は、特定のアクションによって監査可能なイベントがトリガーされた際に、次のイベント ID を記録します。

| イベント カテゴリ名 | イベント識別子 |
| --- | --- |
| アカウント ログオン セキュリティ | 4767、4774、4775、4776、4777 |
| ケルベロスチケット (認証チケット) | 4768, 4769 |
| アカウント管理セキュリティ | 4720、4722、4723、4724、4725、4726、4727、4728、4729、4730、4731、4732、4733、4734、4735、4737、4738、4740、4741、4742、4743、4754、4755、4756、4757、4758、4764、4765、4766、4780、4781、4782、4793、4798、4799、5376、5377 |
| 詳細の追跡セキュリティ | なし |
| DNS サーバー | 513-523、525-531、533-537、540-582 |
| DS アクセス セキュリティ | 5136、5137、5138、5139、5141 |
| ログオン ログオフ セキュリティ | 4624、4625、4634、4647、4648、4672、4675、4964 |
| オブジェクト アクセス セキュリティ | なし |
| ポリシー変更に関連するセキュリティ | 4670、4703、4704、4705、4706、4707、4713、4715、4716、4717、4718、4719、4739、4864、4865、4866、4867、4904、4906、4911、4912 |
| 権限利用のセキュリティ | 4985 |
| システム セキュリティ | 4612、4621 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/suspension"} -->
## Microsoft Entra Domain Services の中断されたドメイン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/suspension
- Service: entra-id / domain-services
- Article date: 2025-02-19
- Summary: Microsoft Entra Domain Services マネージド ドメインのさまざまな正常性状態と、中断されたドメインの復元方法について説明します。

Microsoft Entra Domain Services が長期間にわたりマネージド ドメインにサービスを提供できない場合、マネージド ドメインは中断済み状態にされます。 マネージド ドメインが中断状態のままである場合、自動的に削除されます。 Domain Services マネージド ドメインを正常な状態に保ち、中断を回避するには、できるだけ早くアラートを解決します。

この記事では、マネージド ドメインが中断される理由、および中断されたドメインを復旧する方法について説明します。

### マネージド ドメインの状態の概要

マネージド ドメインのライフサイクルを通じて、その正常性を示すさまざまな状態があります。 マネージド ドメインで問題が報告された場合は、根本的な原因を迅速に解決して、機能低下が続く状態を防ぎます。

[Image: The progression of states that a managed domain takes towards suspension]

マネージド ドメインは、次のいずれかの状態になります。

- 実行中
- 要注意
- 中断済み
- 削除済み

### 実行中の状態

正しく構成され、問題がないマネージド ドメインは、"*実行中*" 状態です。 これは、マネージド ドメインの望ましい状態です。

#### ウィザードの内容

- Azure プラットフォームでは、マネージド ドメインの正常性を定期的に監視できます。
- マネージド ドメインのドメイン コントローラーには、修正プログラムと更新プログラムが定期的に適用されます。
- Microsoft Entra ID からの変更が、マネージド ドメインに定期的に同期されます。
- マネージド ドメインのバックアップが定期的に取得されます。

### 要注意の状態

解決する必要がある 1 つ以上の問題があるマネージド ドメインは、"*要注意*" 状態です。 マネージド ドメインの正常性ページには、アラートが一覧表示され、問題が発生している場所が示されます。

一部のアラートは一時的なものであり、Azure プラットフォームによって自動的に解決されます。 その他のアラートについては、提供される解決手順に従うことで問題を解決できます。 重要なアラートがある場合は、さらなるトラブルシューティングの支援のために、[Azure サポート リクエストを開いて](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-to-get-support)ください。

アラートの一例として、制限されたネットワーク セキュリティ グループがある場合が挙げられます。 この構成では、Azure プラットフォームはマネージド ドメインの更新および監視を行えない可能性があります。 アラートが生成され、状態が "*要注意*" に変わります。

詳細については、[マネージド ドメインでのアラートのトラブルシューティング方法](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/troubleshoot-alerts)に関するページを参照してください。

#### ウィザードの内容

マネージド ドメインが "*要注意*" 状態である場合、Azure プラットフォームでは監視、パッチ適用、更新、データの定期的なバックアップを行うことができない場合があります。 場合によっては (無効なネットワーク構成の場合など)、マネージド ドメインのドメイン コントローラーに到達できないことがあります。

- アラートが解決されるまで、マネージド ドメインは異常な状態であり、継続的な正常性の監視が停止する場合があります。
- マネージド ドメインのドメイン コントローラーに、修正プログラムや更新プログラムを適用できません。
- Microsoft Entra ID からの変更が、マネージド ドメインに同期されない場合があります。
- マネージド ドメインのバックアップを作成できない場合があります。
- マネージド ドメインに影響を与えている重要でないアラートを解決すると、正常性は "*実行中*" 状態に戻ります。
- Azure プラットフォームがドメイン コントローラーに到達できない構成の問題に対しては、重大アラートがトリガーされます。 このような重大アラートが 15 日以内に解決されない場合、マネージド ドメインは "*中断済み*" 状態になります。

### 中断済みの状態

マネージド ドメインは、次のいずれかの理由により、 **[中断済み]** 状態になります。

- 重要なアラートは 15 日以内に解決されません。 重要なアラートは、Domain Services で必要なリソースへのアクセスを妨げる誤った構成によって発生する可能性があります。例: アラート [AADDS104: ネットワーク エラー](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/alert-nsg)。
- Azure サブスクリプションに請求の問題がある場合、または Azure サブスクリプションの有効期限が切れた場合。

Azure プラットフォームがドメインの管理、監視、パッチ適用、バックアップを行うことができない場合、マネージド ドメインは中断されます。 マネージド ドメインは 15 日間 "*中断済み*" 状態のままになります。 マネージド ドメインへのアクセスを維持するには、重大アラートを直ちに解決してください。

#### ウィザードの内容

マネージド ドメインが "*中断済み*" 状態である場合、次の動作が発生します。

- マネージド ドメインのドメイン コントローラーはプロビジョニング解除され、仮想ネットワーク内で到達できなくなります。
- インターネット経由でのマネージド ドメインへの Secure LDAP アクセスは、有効になっている場合、動作しなくなります。
- マネージド ドメインに対する認証、ドメイン参加済み VM へのログオン、または LDAP/LDAPS 経由での接続の際に失敗が発生します。
- マネージド ドメインのバックアップは行われなくなります。
- Microsoft Entra ID との同期は停止します。

#### マネージド ドメインが中断されているかどうかを確認する方法

Microsoft Entra 管理センターの Domain Services の正常性ページに、ドメインが中断されていることを示す[アラート](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/troubleshoot-alerts)が表示されます。 ドメインの状態も "*中断済み*" と表示されます。

#### 中断されているドメインを復元する

"*中断済み*" 状態であるマネージド ドメインの正常性を復元するには、次の手順を行います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、**Domain Services** を検索して選びます。
2. 一覧からマネージド ドメイン (*aaddscontoso.com* など) を選択し、 **[正常性]** を選択します。
3. 中断の原因によって *AADDS503* や *AADDS504* などのアラートを選択します。
4. アラートで提供される解決リンクを選択し、手順に従ってそれを解決します。

マネージド ドメインは、任意のバックアップから復元できます。 バックアップ後に発生した変更は復元されません。 最後のバックアップの日付は、マネージド ドメインの **[正常性]** ページに表示されます。 マネージド ドメインのバックアップは、最大 30 日間保存されます。 30 日より古いバックアップは削除されます。

マネージド ドメインが "*中断済み*" 状態であるときにアラートを解決したら、正常な状態に戻すために [Azure サポート リクエストを開いてください](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-to-get-support)。 作成から 30 日未満のバックアップがあれば、Azure サポートはマネージド ドメインを復元できます。

### 削除済みの状態

15 日間 "*中断済み*" 状態のままだったマネージド ドメインは削除されます。 このプロセスは回復できません。

#### ウィザードの内容

マネージド ドメインが "*削除済み*" 状態になると、次の動作が行われます。

- マネージド ドメインのすべてのリソースとバックアップが削除されます。
- マネージド ドメインは復元できません。 Domain Services を再利用するには、代替のマネージド ドメインを作成する必要があります。
- 削除されたマネージド ドメインに対しては課金されません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/synchronization"} -->
## Microsoft Entra Domain Services での同期のしくみ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/synchronization
- Service: entra-id / domain-services
- Article date: 2025-02-19
- Summary: Microsoft Entra ID またはオンプレミス環境から Microsoft Entra Domain Services マネージド ドメインへの同期プロセスのしくみについて説明します。

Microsoft Entra Domain Services マネージド ドメイン内のオブジェクトと資格情報は、ドメイン内でローカルに作成することも、Microsoft Entra テナントから同期することもできます。 Domain Services を初めて展開すると、一方向の自動同期が構成され、Microsoft Entra ID からオブジェクトのレプリケートが開始されます。 この一方向同期はバックグラウンドで引き続き実行され、Microsoft Entra ID からの変更が Domain Services マネージド ドメインに反映されることで、常に最新の状態を維持します。 Domain Services から Microsoft Entra ID への同期は行われません。

ハイブリッド環境では、オンプレミスの AD DS ドメインのオブジェクトと資格情報を、Microsoft Entra Connect を使用して Microsoft Entra ID に同期できます。 これらのオブジェクトが Microsoft Entra ID に正常に同期されると、自動バックグラウンド同期により、マネージド ドメインを使用するアプリケーションでそれらのオブジェクトと資格情報を使用できるようになります。

次の図は、Domain Services、Microsoft Entra ID、およびオプションのオンプレミス AD DS 環境間の同期のしくみを示しています。

[Image: Microsoft Entra Domain Services マネージド ドメインの同期の概要]

### Microsoft Entra ID から Domain Services への同期

ユーザー アカウント、グループ メンバーシップ、および資格情報ハッシュは、Microsoft Entra ID から Domain Services に 1 つの方法で同期されます。 この同期プロセスは自動的に行われます。 この同期プロセスを構成、監視、または管理する必要はありません。 初期同期には、Microsoft Entra ディレクトリ内のオブジェクトの数によっては、数時間から数日かかる場合があります。 初期同期が完了すると、パスワードや属性の変更など、Microsoft Entra ID で行われた変更が Domain Services に自動的に同期されます。

ユーザーが Microsoft Entra ID で作成されると、Microsoft Entra ID でパスワードを変更するまで、ユーザーは Domain Services に同期されません。 このパスワード変更プロセスにより、Kerberos および NTLM 認証のパスワード ハッシュが生成され、Microsoft Entra ID に格納されます。 Domain Services でユーザーを正常に認証するには、パスワード ハッシュが必要です。

同期プロセスは設計上、1方向です。 Domain Services から Microsoft Entra ID への変更の逆同期はありません。 マネージド ドメインは、作成できるカスタム OU を除き、主に読み取り専用です。 マネージド ドメイン内のユーザー属性、ユーザー パスワード、またはグループ メンバーシップを変更することはできません。

### スコープ付き同期とグループ フィルター

同期のスコープは、クラウドで発生したユーザー アカウントのみに設定できます。 その同期スコープ内で、特定のグループまたはユーザーをフィルター処理できます。 クラウドのみのグループ、オンプレミス のグループ、またはその両方を選択できます。 スコープ同期を構成する方法の詳細については、「スコープ付き同期の [構成」](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/scoped-synchronization)を参照してください。

[Image: グループ フィルター オプションのスクリーンショット。]

### 属性の同期と Domain Services へのマッピング

次の表に、一般的な属性と、それらが Domain Services に同期される方法を示します。

| Domain Services の属性 | 情報源 | 注記 |
| --- | --- | --- |
| UPN | Microsoft Entra テナントでのユーザーの *UPN* 属性 | Microsoft Entra テナントの UPN 属性は、そのままドメイン サービスに同期されます。 マネージド ドメインにサインインする最も信頼性の高い方法は、UPN を使用する方法です。 |
| SAMAccountName | Microsoft Entra テナント内のユーザーの *mailNickname* 属性、または自動生成されたもの | *SAMAccountName* 属性は、Microsoft Entra テナントの *mailNickname* 属性から取得されます。 複数のユーザー アカウントに同じ *mailNickname* 属性がある場合、 *SAMAccountName* は自動生成されます。 ユーザーの *mailNickname* または *UPN* プレフィックスが 20 文字を超える場合、 *SAMAccountName* 属性の 20 文字の制限を満たすように *SAMAccountName* が自動生成されます。 |
| パスワード | Microsoft Entra テナントからのユーザーのパスワード | NTLM または Kerberos 認証に必要な従来のパスワード ハッシュは、Microsoft Entra テナントから同期されます。 Microsoft Entra テナントが Microsoft Entra Connect を使用してハイブリッド同期用に構成されている場合、これらのパスワード ハッシュはオンプレミスの AD DS 環境から取得されます。 |
| プライマリ ユーザー/グループ SID | 自動生成 | ユーザー/グループ アカウントのプライマリ SID は Domain Services で自動生成されます。 この属性は、オンプレミス AD DS 環境のオブジェクトのプライマリ ユーザー/グループ SID と一致しません。 この不一致は、マネージド ドメインにオンプレミスの AD DS ドメインとは異なる SID 名前空間があるためです。 |
| ユーザーとグループの SID 履歴 | オンプレミスのプライマリ ユーザーとグループ SID | Domain Services のユーザーとグループ *の SidHistory* 属性は、オンプレミス AD DS 環境の対応するプライマリ ユーザーまたはグループ SID と一致するように設定されます。 この機能は、リソースを再 ACL する必要がないので、オンプレミス アプリケーションの Domain Services へのリフト アンド シフトを容易にするのに役立ちます。 |

注

セキュリティ アカウント マネージャー アカウント名 (sAMAccountName) とMicrosoft Entra Domain Servicesの同期のサポートが強化され、[パブリック プレビュー](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/sam-account-name/sam-account-name)が開始されました。

ヒント

**UPN 形式を使用してマネージド ドメインにサインイン**する 属性は、マネージド ドメイン内の一部のユーザー アカウントに対して自動生成される場合があります。 ユーザーの自動生成 *された SAMAccountName* は UPN プレフィックスとは異なる場合があるため、常に信頼できるサインイン方法とは限りません。

たとえば、複数のユーザーが同じ *mailNickname* 属性を持っているか、ユーザーの UPN プレフィックスが過度に長い場合、これらのユーザーの *SAMAccountName* が自動生成される可能性があります。 マネージド ドメインに確実にサインインするには、`driley@aaddscontoso.com` などの UPN 形式を使用します。

#### ユーザー アカウントの属性マッピング

次の表は、Microsoft Entra ID のユーザー オブジェクトの特定の属性が Domain Services の対応する属性にどのように同期されるかを示しています。

| Microsoft Entra ID のユーザー属性 | Domain Services のユーザー属性 |
| --- | --- |
| accountEnabled | userAccountControl（アカウントを無効にするビット ACCOUNT\_DISABLED を設定または解除します） |
| city | l |
| 会社名 | 会社名 |
| country | co |
| 部署 | 部署 |
| displayName | displayName |
| 従業員ID | 従業員ID |
| ファクシミリ電話番号 | ファクシミリ電話番号 |
| givenName | givenName |
| 職務タイトル | タイトル |
| メール | メール |
| メールニックネーム | msDS-AzureADMailNickname |
| メールニックネーム | SAMAccountName (自動生成される場合があります) |
| マネージャー | マネージャー |
| モバイル | モバイル |
| objectid | msDS-aadObjectId |
| オンプレミスセキュリティ識別子 | sidHistory |
| パスワードポリシー | userAccountControl (DONT\_EXPIRE\_PASSWORD ビットを設定またはクリアします) |
| physicalDeliveryOfficeName | physicalDeliveryOfficeName |
| 郵便番号 | 郵便番号 |
| 優先言語 | 優先言語 |
| プロキシアドレス | プロキシアドレス |
| 状態 | st |
| streetAddress | streetAddress |
| 名字 | sn |
| 電話番号 | 電話番号 |
| userPrincipalName | userPrincipalName |

#### グループの属性マッピング

次の表は、Microsoft Entra ID のグループ オブジェクトの特定の属性が Domain Services の対応する属性にどのように同期されるかを示しています。

| Microsoft Entra ID のグループ属性 | Domain Services のグループ属性 |
| --- | --- |
| displayName | displayName |
| displayName | SAMAccountName (自動生成される場合があります) |
| メール | メール |
| メールニックネーム | msDS-AzureADMailNickname |
| objectid | msDS-AzureADObjectId |
| オンプレミスセキュリティ識別子 | sidHistory |
| プロキシアドレス | プロキシアドレス |
| セキュリティ有効 | groupType |

### オンプレミスの AD DS から Microsoft Entra ID および Domain Services への同期

Microsoft Entra Connect は、ユーザー アカウント、グループ メンバーシップ、および資格情報ハッシュをオンプレミスの AD DS 環境から Microsoft Entra ID に同期するために使用されます。 UPN やオンプレミスのセキュリティ識別子 (SID) などのユーザー アカウントの属性が同期されます。 Domain Services を使用してサインインするために、NTLM および Kerberos 認証に必要な従来のパスワード ハッシュも Microsoft Entra ID に同期されます。

Von Bedeutung

Microsoft Entra Connect は、オンプレミスの AD DS 環境との同期のためにのみインストールおよび構成する必要があります。 マネージド ドメインに Microsoft Entra Connect をインストールしてオブジェクトを Microsoft Entra ID に同期することはサポートされていません。

ライトバックを構成すると、Microsoft Entra ID からの変更がオンプレミスの AD DS 環境に同期されます。 たとえば、ユーザーが Microsoft Entra セルフサービス パスワード管理を使用してパスワードを変更した場合、パスワードはオンプレミスの AD DS 環境で更新されます。

注

Microsoft Entra Connect の最新バージョンを常に使用して、既知のすべてのバグに対する修正を確実に行います。

#### 複数フォレストのオンプレミス環境からの同期

多くの組織には、複数のフォレストを含む非常に複雑なオンプレミス AD DS 環境があります。 Microsoft Entra Connect では、複数フォレスト環境から Microsoft Entra ID へのユーザー、グループ、資格情報ハッシュの同期がサポートされています。

Microsoft Entra ID には、はるかにシンプルでフラットな名前空間があります。 ユーザーが Microsoft Entra ID によってセキュリティ保護されたアプリケーションに確実にアクセスできるようにするには、異なるフォレスト内のユーザー アカウント間で UPN の競合を解決します。 マネージド ドメインでは、Microsoft Entra ID と同様に、フラットな OU 構造が使用されます。 階層型 OU 構造をオンプレミスで構成している場合でも、異なるオンプレミス ドメインまたはフォレストから同期されているにもかかわらず、すべてのユーザー アカウントとグループは *AADDC Users* コンテナーに格納されます。 マネージド ドメインは、階層的な OU 構造をフラット化します。

前述のように、Domain Services から Microsoft Entra ID への同期はありません。 Domain Services で [カスタム組織単位 (OU) を作成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/create-ou) し、そのカスタム OU 内にユーザー、グループ、またはサービス アカウントを作成できます。 カスタム OU で作成されたオブジェクトは、いずれも Microsoft Entra ID に同期されません。 これらのオブジェクトはマネージド ドメイン内でのみ使用でき、Microsoft Graph PowerShell コマンドレット、Microsoft Graph API、または Microsoft Entra 管理センターを使用して表示されることはありません。

### Domain Services に同期されないもの

次のオブジェクトまたは属性は、オンプレミスの AD DS 環境から Microsoft Entra ID または Domain Services に同期されません。

- **除外される属性:** Microsoft Entra Connect を使用して、特定の属性をオンプレミスの AD DS 環境から Microsoft Entra ID に同期しないようにすることができます。 これらの除外された属性は、Domain Services では使用できません。
- **グループ ポリシー:** オンプレミスの AD DS 環境で構成されたグループ ポリシーは、Domain Services に同期されません。
- **Sysvol フォルダー:** オンプレミスの AD DS 環境の *Sysvol* フォルダーの内容は、Domain Services に同期されません。
- **コンピューター オブジェクト:** オンプレミスの AD DS 環境に参加しているコンピューターのコンピューター オブジェクトは、Domain Services に同期されません。 これらのコンピューターはマネージド ドメインと信頼関係がなく、オンプレミスの AD DS 環境にのみ属します。 Domain Services では、マネージド ドメインに明示的にドメイン参加しているコンピューターのコンピューター オブジェクトのみが表示されます。
- **ユーザーとグループの SidHistory 属性:** オンプレミスの AD DS 環境のプライマリ ユーザーおよびプライマリ グループ SID は、Domain Services に同期されます。 ただし、ユーザーとグループ *の既存の SidHistory* 属性は、オンプレミスの AD DS 環境から Domain Services に同期されません。
- **組織単位 (OU) の構造:** オンプレミスの AD DS 環境で定義されている組織単位は、Domain Services と同期されません。 Domain Services には、ユーザー用とコンピューター用の 2 つの組み込み OU があります。 マネージド ドメインには、フラットな OU 構造があります。 [マネージド ドメインにカスタム OU を作成することを](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/create-ou)選択できます。

### パスワード ハッシュ同期とセキュリティに関する考慮事項

Domain Services を有効にする場合は、NTLM 認証と Kerberos 認証のレガシ パスワード ハッシュが必要です。 Microsoft Entra ID にはクリア テキスト パスワードが格納されないため、既存のユーザー アカウントに対してこれらのハッシュを自動的に生成することはできません。 NTLM および Kerberos 互換のパスワード ハッシュは、常に暗号化された方法で Microsoft Entra ID に格納されます。

暗号化キーは、各 Microsoft Entra テナントに固有です。 これらのハッシュは、ドメイン サービスのみが暗号化解除キーにアクセスできるように暗号化されます。 Microsoft Entra ID の他のサービスまたはコンポーネントは、暗号化解除キーにアクセスできなくなります。

その後、従来のパスワード ハッシュは、Microsoft Entra ID からマネージド ドメインのドメイン コントローラーに同期されます。 Domain Services のこれらのマネージド ドメイン コントローラーのディスクは、保存時に暗号化されます。 これらのパスワード ハッシュは、オンプレミスの AD DS 環境でのパスワードの格納方法とセキュリティ保護方法と同様に、これらのドメイン コントローラーに格納され、セキュリティで保護されます。

クラウドのみの Microsoft Entra 環境では、必要なパスワード ハッシュを生成して Microsoft Entra ID に格納するために、ユーザーはパスワードを [リセットまたは変更する必要があります](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance#enable-user-accounts-for-azure-ad-ds) 。 Microsoft Entra Domain Services を有効にした後に Microsoft Entra ID で作成されたクラウド ユーザー アカウントの場合、パスワード ハッシュが生成され、NTLM と Kerberos 互換の形式で格納されます。 すべてのクラウド ユーザー アカウントは、Domain Services に同期する前にパスワードを変更する必要があります。

Microsoft Entra Connect を使用してオンプレミスの AD DS 環境から同期されるハイブリッド ユーザー アカウントの場合は、 [NTLM と Kerberos 互換の形式でパスワード ハッシュを同期するように Microsoft Entra Connect を構成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-password-hash-sync)する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/template-create-instance"} -->
## テンプレートを使用して Azure DS Domain Services を有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/template-create-instance
- Service: entra-id / domain-services
- Article date: 2025-02-19
- Summary: Azure Resource Manager テンプレートを使用して Microsoft Entra Domain Services を構成して有効にする方法について説明します。

Microsoft Entra Domain Services では、Windows Server Active Directory と完全に互換性のあるマネージド ドメイン サービス (ドメイン参加、グループ ポリシー、LDAP、Kerberos または NTLM 認証など) が提供されます。 ドメイン コントローラーのデプロイ、管理、パッチの適用を自分で行わなくても、これらのドメイン サービスを使用することができます。 Domain Services は、既存の Microsoft Entra テナントと統合されます。 この統合により、ユーザーは、各自の会社の資格情報を使用してサインインすることができます。また管理者は、既存のグループとユーザー アカウントを使用してリソースへのアクセスをセキュリティで保護することができます。

この記事では、Azure Resource Manager テンプレートを使用してマネージド ドメインを作成する方法について説明します。 サポート リソースは、Azure PowerShell を使用して作成されます。

### 前提条件

この記事を完了するには、以下のリソースが必要です。

- Azure PowerShell のインストールおよび構成。
    - 必要であれば、手順に従って、[Azure PowerShell モジュールをインストールし、Azure サブスクリプションに接続](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)します。
    - 必ず [Connect-AzAccount](https://learn.microsoft.com/ja-jp/powershell/module/Az.Accounts/Connect-AzAccount) コマンドレットを使用して Azure サブスクリプションにサインインしてください。
- MS Graph PowerShell をインストールして構成します。
    - 必要であれば、指示に従って [MS Graph PowerShell モジュールをインストールし、Microsoft Entra ID に接続します](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands?view=graph-powershell-1.0&preserve-view=true)。
    - 必ず [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands?view=graph-powershell-1.0&preserve-view=true) コマンドレットを使用して Microsoft Entra テナントにサインインしてください。
- Domain Services を有効にするには、テナントで[アプリケーション管理者](https://learn.microsoft.com/ja-jp/azure/active-directory/roles/permissions-reference#application-administrator)と[グループ管理者](https://learn.microsoft.com/ja-jp/azure/active-directory/roles/permissions-reference#groups-administrator)の Microsoft Entra ロールが必要です。
- 必須の Domain Services リソースを作成するには、ドメインサービス共同作成者の Azure ロールが必要です。

### DNS の名前付けに関する要件

Domain Services マネージド ドメインを作成する際は、DNS 名を指定します。 この DNS 名を選ぶ際のいくつかの考慮事項を次に示します。

- **組み込みドメイン名:** 既定では、ディレクトリの組み込みドメイン名が使用されます ( *.onmicrosoft.com* サフィックス)。 マネージド ドメインに対するインターネット経由での Secure LDAP アクセスを有効にしたい場合、デジタル証明書を作成して、この既定のドメインとの接続をセキュリティで保護することはできません。 *.onmicrosoft.com* ドメインを所有するのは Microsoft であるため、証明機関 (CA) からは証明書が発行されません。
- **カスタム ドメイン名:** 最も一般的な方法は、カスタム ドメイン名を指定することです。一般に、貴社が既に所有していて、なおかつルーティング可能なものを指定します。 ルーティング可能なカスタム ドメインを使用すれば、ご利用のアプリケーションをサポートするために必要なトラフィックを正しく送信することができます。
- **ルーティング不可能なドメイン サフィックス:** 一般に、ルーティング不可能なドメイン名サフィックス (*contoso.local* など) は避けることをお勧めします。 *.local* サフィックスはルーティングできないため、DNS 解決で問題の原因となることがあります。

ヒント

カスタム ドメイン名を作成する場合は、既存の DNS 名前空間に注意してください。 Azure およびオンプレミスの既存の DNS 名前空間とは別のドメイン名を使用することをお勧めします。

たとえば、*contoso.com* の既存の DNS 名前空間がある場合、*aaddscontoso.com* というカスタム ドメイン名を使用してマネージド ドメインを作成します。 Secure LDAP を使用する必要がある場合は、このカスタム ドメイン名を登録して所有し、必要な証明書を生成する必要があります。

場合によっては、環境内の他のサービス用に追加で DNS レコードを作成したり、環境内に既に存在する 2 つの DNS 名前空間の間に条件付き DNS フォワーダーを作成したりする必要があります。 たとえば、ルート DNS 名を使用するサイトをホストする Web サーバーを実行する場合、名前の競合が発生して、追加の DNS エントリが必要になる可能性があります。

このサンプルと操作方法に関する記事では、簡略な例として *aaddscontoso.com* というカスタム ドメインを使用しています。 すべてのコマンドで、独自のドメイン名を指定してください。

DNS 名には、次の制限も適用されます。

- **ドメインのプレフィックスの制限:** プレフィックスが 15 文字より長いマネージド ドメインを作成できません。 指定するドメイン名のプレフィックス (たとえば、ドメイン名 *aaddscontoso.com* の *aaddscontoso* など) は、15 文字以内に収める必要があります。
- **ネットワーク名の競合:**マネージド ドメインの DNS ドメイン名は、まだ仮想ネットワークに存在していない必要があります。 具体的には、名前の競合につながる次のシナリオをチェックしてください。
    - 同じ DNS ドメイン名の Active Directory ドメインが Azure 仮想ネットワーク上に既にあるかどうか。
    - マネージド ドメインを有効にする仮想ネットワークに、オンプレミス ネットワークとの VPN 接続があるかどうか。 このシナリオでは、オンプレミス ネットワークに同じ DNS ドメイン名のドメインがないことを確認します。
    - その名前の付いた Azure クラウド サービスが Azure 仮想ネットワーク上に既にあるかどうか。

### 必要な Microsoft Entra リソースを作成する

Domain Services には、サービス プリンシパルと Microsoft Entra グループが必要です。 これらのリソースにより、マネージド ドメインはデータを同期し、マネージド ドメインで管理アクセス許可を持つユーザーを定義できます。

まず、[Register-AzResourceProvider](https://learn.microsoft.com/ja-jp/powershell/module/Az.Resources/Register-AzResourceProvider) コマンドレットを使用して、Microsoft Entra Domain Services リソース プロバイダーを登録します。

```powershell
Register-AzResourceProvider -ProviderNamespace Microsoft.AAD
```

Domain Services が通信し、自身を認証するようにするには、[New-MgServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/new-mgserviceprincipal) コマンドレットを使用して Microsoft Entra サービス プリンシパルを作成します。 Azure Global では、ID *2565bd9d-da50-47d4-8b85-4c97f669dc36* を持つ *Domain Controller Services* という名前の特定のアプリケーション ID が使用されます。 他の Azure クラウドの場合は、AppId 値 *6ba9a5d4-8456-4118-b521-9c5ca10cdf84* を検索します。

```powershell
New-MgServicePrincipal
```

次に、*New-MgGroup* コマンドレットを使用して [AAD DC Administrators](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/new-mggroup) という名前の Microsoft Entra グループを作成します。 このグループに追加されたユーザーには、マネージド ドメインで管理タスクを実行するためのアクセス許可が付与されます。

```powershell
New-MgGroup -DisplayName "AAD DC Administrators" `
  -Description "Delegated group to administer Microsoft Entra Domain Services" `
  -SecurityEnabled:$true -MailEnabled:$false `
  -MailNickName "AADDCAdministrators"
```

*AAD DC Administrators グループを*作成したら、New-MgGroupMemberByRef[コマンドレットを使用して](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/new-mggroupmemberbyref)ユーザーをグループに追加します。 まず、*Get-MgGroup* コマンドレットを使用して [AAD DC Administrators](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/get-mggroup) グループのオブジェクト ID を取得し、次に [Get-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/get-mguser) コマンドレットを使用して目的のユーザーのオブジェクト ID を取得します。

次の例では、UPN が `admin@contoso.onmicrosoft.com` のアカウントのユーザー オブジェクト ID です。 このユーザー アカウントを、*AAD DC Administrators* グループに追加するユーザーの UPN に置き換えます。

```powershell
# First, retrieve the object ID of the newly created 'AAD DC Administrators' group.
$GroupObjectId = Get-MgGroup `
  -Filter "DisplayName eq 'AAD DC Administrators'" | `
  Select-Object Id

# Now, retrieve the object ID of the user you'd like to add to the group.
$UserObjectId = Get-MgUser `
  -Filter "UserPrincipalName eq 'admin@contoso.onmicrosoft.com'" | `
  Select-Object Id

# Add the user to the 'AAD DC Administrators' group.
New-MgGroupMember -GroupId $GroupObjectId.Id -DirectoryObjectId $UserObjectId.Id
```

最後に、[New-AzResourceGroup](https://learn.microsoft.com/ja-jp/powershell/module/Az.Resources/New-AzResourceGroup) コマンドレットを使用してリソース グループを作成します。 次の例では、リソース グループは *myResourceGroup* という名前が付けられ、*westus* リージョンに作成されます。 独自の名前と希望するリージョンを使用します。

```powershell
New-AzResourceGroup `
  -Name "myResourceGroup" `
  -Location "WestUS"
```

Availability Zones がサポートされているリージョンを選択すると、Domain Services リソースが、冗長性を高めるために複数のゾーンに分散されます。 Availability Zones は、Azure リージョン内の一意の物理的な場所です。 それぞれのゾーンは、独立した電源、冷却手段、ネットワークを備えた 1 つまたは複数のデータセンターで構成されています。 回復性を確保するため、有効になっているリージョンにはいずれも最低 3 つのゾーンが別個に存在しています。

Domain Services を複数のゾーンに分散するために、ご自身で構成するものは何もありません。 Azure プラットフォームでは、ゾーンへのリソース分散が自動的に処理されます。 詳細情報および利用可能なリージョンについては、「[Azure の Availability Zones の概要](https://learn.microsoft.com/ja-jp/azure/reliability/availability-zones-overview)」を参照してください。

### Domain Services のリソース定義

Resource Manager リソース定義の一部として、次の構成パラメーターが必要です。

| パラメーター | 値 |
| --- | --- |
| ドメイン名 | マネージド ドメインの DNS ドメイン名。プレフィックスの名前付けや競合に関する前のポイントを考慮に入れてください。 |
| filtered同期 | Domain Services では、Microsoft Entra ID に存在する "すべて" のユーザーとグループを同期できるほか、特定のグループのみを "範囲指定" して同期することもできます。 範囲指定された同期の詳細については、[Microsoft Entra Domain Services の範囲指定された同期](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/scoped-synchronization)に関するページを参照してください。 |
| notificationSettings (通知設定) | マネージド ドメインで生成されたアラートがある場合は、電子メールによる通知を送信できます。Microsoft Entra ID テナントの高い特権を持つ管理者と *AAD DC Administrators* グループのメンバーは、これらの通知に *対して有効* にすることができます。 必要に応じて、注意が必要なアラートがある場合に通知用に他の受信者を追加できます。 |
| ドメイン構成タイプ | 既定では、マネージド ドメインは "*ユーザー*" フォレストとして作成されます。 このタイプのフォレストでは、オンプレミスの AD DS 環境で作成されたユーザー アカウントも含め、Microsoft Entra ID 内のすべてのオブジェクトが同期されます。 ユーザー フォレストを作成するために *domainConfiguration* 値を指定する必要はありません。 "リソース" フォレストでは、Microsoft Entra ID に直接作成されたユーザーとグループだけが同期されます。 リソース フォレストを作成するには、この値を *ResourceTrusting* に設定します。"リソース" フォレストを使用する理由や、オンプレミスの AD DS ドメインを使用してフォレストの信頼を作成する方法など、"リソース" フォレストの詳細については、*Domain Services リソース フォレストの概要*に関するページを参照してください。 |

次の圧縮されたパラメーターの定義は、これらの値がどのように宣言されるかを示しています。 *aaddscontoso.com* という名前のユーザー フォレストは、マネージド ドメインに同期されている Microsoft Entra ID のすべてのユーザーで作成されます。

```json
"parameters": {
    "domainName": {
        "value": "aaddscontoso.com"
    },
    "filteredSync": {
        "value": "Disabled"
    },
    "notificationSettings": {
        "value": {
            "notifyGlobalAdmins": "Enabled",
            "notifyDcAdmins": "Enabled",
            "additionalRecipients": []
        }
    },
    [...]
}
```

その後、次の圧縮された Resource Manager テンプレート リソースの種類を使用して、マネージド ドメインが定義および作成されます。 Azure 仮想ネットワークとサブネットが既に存在するか、または Resource Manager テンプレートの一部として作成される必要があります。 マネージド ドメインは、このサブネットに接続されます。

```json
"resources": [
    {
        "apiVersion": "2017-06-01",
        "type": "Microsoft.AAD/DomainServices",
        "name": "[parameters('domainName')]",
        "location": "[parameters('location')]",
        "dependsOn": [
            "[concat('Microsoft.Network/virtualNetworks/', parameters('vnetName'))]"
        ],
        "properties": {
            "domainName": "[parameters('domainName')]",
            "subnetId": "[concat('/subscriptions/', subscription().subscriptionId, '/resourceGroups/', resourceGroup().name, '/providers/Microsoft.Network/virtualNetworks/', parameters('vnetName'), '/subnets/', parameters('subnetName'))]",
            "filteredSync": "[parameters('filteredSync')]",
            "notificationSettings": "[parameters('notificationSettings')]"
        }
    },
    [...]
]
```

次のセクションに示すように、これらのパラメーターとリソースの種類は、マネージド ドメインをデプロイするためのより広範囲の Resource Manager テンプレートの一部として使用できます。

### サンプル テンプレートを使用してマネージド ドメインを作成する

次の完全な Resource Manager サンプル テンプレートでは、マネージド ドメインとサポートする仮想ネットワーク、サブネット、ネットワーク セキュリティ グループの規則が作成されます。 ネットワーク セキュリティ グループの規則は、マネージド ドメインをセキュリティで保護し、トラフィックを正常に送受信できることを確認するために必要です。 *aaddscontoso.com* の DNS 名を持つユーザー フォレストが作成され、すべてのユーザーが Microsoft Entra ID から同期されます。

```json
{
    "$schema": "http://schema.management.azure.com/schemas/2015-01-01/deploymentTemplate.json#",
    "contentVersion": "1.0.0.0",
    "parameters": {
        "apiVersion": {
            "value": "2017-06-01"
        },
        "domainConfigurationType": {
            "value": "FullySynced"
        },
        "domainName": {
            "value": "aaddscontoso.com"
        },
        "filteredSync": {
            "value": "Disabled"
        },
        "location": {
            "value": "westus"
        },
        "notificationSettings": {
            "value": {
                "notifyGlobalAdmins": "Enabled",
                "notifyDcAdmins": "Enabled",
                "additionalRecipients": []
            }
        },
        "subnetName": {
            "value": "aadds-subnet"
        },
        "vnetName": {
            "value": "aadds-vnet"
        },
        "vnetAddressPrefixes": {
            "value": [
                "10.1.0.0/24"
            ]
        },
        "subnetAddressPrefix": {
            "value": "10.1.0.0/24"
        },
        "nsgName": {
            "value": "aadds-nsg"
        }
    },
    "resources": [
        {
            "apiVersion": "2017-06-01",
            "type": "Microsoft.AAD/DomainServices",
            "name": "[parameters('domainName')]",
            "location": "[parameters('location')]",
            "dependsOn": [
                "[concat('Microsoft.Network/virtualNetworks/', parameters('vnetName'))]"
            ],
            "properties": {
                "domainName": "[parameters('domainName')]",
                "subnetId": "[concat('/subscriptions/', subscription().subscriptionId, '/resourceGroups/', resourceGroup().name, '/providers/Microsoft.Network/virtualNetworks/', parameters('vnetName'), '/subnets/', parameters('subnetName'))]",
                "filteredSync": "[parameters('filteredSync')]",
                "domainConfigurationType": "[parameters('domainConfigurationType')]",
                "notificationSettings": "[parameters('notificationSettings')]"
            }
        },
        {
            "type": "Microsoft.Network/NetworkSecurityGroups",
            "name": "[parameters('nsgName')]",
            "location": "[parameters('location')]",
            "properties": {
                "securityRules": [
                    {
                        "name": "AllowSyncWithAzureAD",
                        "properties": {
                            "access": "Allow",
                            "priority": 101,
                            "direction": "Inbound",
                            "protocol": "Tcp",
                            "sourceAddressPrefix": "AzureActiveDirectoryDomainServices",
                            "sourcePortRange": "*",
                            "destinationAddressPrefix": "*",
                            "destinationPortRange": "443"
                        }
                    },
                    {
                        "name": "AllowPSRemoting",
                        "properties": {
                            "access": "Allow",
                            "priority": 301,
                            "direction": "Inbound",
                            "protocol": "Tcp",
                            "sourceAddressPrefix": "AzureActiveDirectoryDomainServices",
                            "sourcePortRange": "*",
                            "destinationAddressPrefix": "*",
                            "destinationPortRange": "5986"
                        }
                    },
                    {
                        "name": "AllowRD",
                        "properties": {
                            "access": "Allow",
                            "priority": 201,
                            "direction": "Inbound",
                            "protocol": "Tcp",
                            "sourceAddressPrefix": "CorpNetSaw",
                            "sourcePortRange": "*",
                            "destinationAddressPrefix": "*",
                            "destinationPortRange": "3389"
                        }
                    }
                ]
            },
            "apiVersion": "2018-04-01"
        },
        {
            "type": "Microsoft.Network/virtualNetworks",
            "name": "[parameters('vnetName')]",
            "location": "[parameters('location')]",
            "apiVersion": "2018-04-01",
            "dependsOn": [
                "[concat('Microsoft.Network/NetworkSecurityGroups/', parameters('nsgName'))]"
            ],
            "properties": {
                "addressSpace": {
                    "addressPrefixes": "[parameters('vnetAddressPrefixes')]"
                },
                "subnets": [
                    {
                        "name": "[parameters('subnetName')]",
                        "properties": {
                            "addressPrefix": "[parameters('subnetAddressPrefix')]",
                            "networkSecurityGroup": {
                                "id": "[concat('/subscriptions/', subscription().subscriptionId, '/resourceGroups/', resourceGroup().name, '/providers/Microsoft.Network/NetworkSecurityGroups/', parameters('nsgName'))]"
                            }
                        }
                    }
                ]
            }
        }
    ],
    "outputs": {}
}
```

このテンプレートは、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/templates/deploy-portal)、[Azure PowerShell](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/templates/deploy-powershell)、CI/CD パイプラインなどの、推奨されるデプロイ方法を使ってデプロイできます。 次の例では、[New-AzResourceGroupDeployment](https://learn.microsoft.com/ja-jp/powershell/module/az.resources/new-azresourcegroupdeployment) コマンドレットを使用します。 独自のリソース グループ名とテンプレート ファイル名を指定します。

```powershell
New-AzResourceGroupDeployment -ResourceGroupName "myResourceGroup" -TemplateFile <path-to-template>
```

リソースが作成され、PowerShell プロンプトに制御が戻るまで数分かかります。 マネージド ドメインは引き続きバックグラウンドでプロビジョニングされ、デプロイが完了するまでには最大 1 時間かかる場合があります。 Microsoft Entra 管理センターでは、お使いのマネージド ドメインの **[概要]** ページに、このデプロイ ステージ全体の現在の状態が表示されます。

マネージド ドメインによるプロビジョニングが完了したことが Microsoft Entra 管理センターに表示されたら、次のタスクを完了する必要があります。

- 仮想マシンがマネージド ドメインを検出してドメイン参加または認証を行うことができるように、仮想ネットワークの DNS 設定を更新します。
    - DNS を構成するには、ポータルでマネージド ドメインを選択します。 **[概要]** ウィンドウで、これらの DNS 設定を自動的に構成するように求められます。
- [Domain Services とのパスワード同期を有効にして](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance#enable-user-accounts-for-azure-ad-ds)、エンド ユーザーが会社の資格情報を使用してマネージド ドメインにサインインできるようにします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/troubleshoot"} -->
## Microsoft Entra Domain Services のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/troubleshoot
- Service: entra-id / domain-services
- Article date: 2025-02-19
- Summary: Microsoft Entra Domain Services を作成または管理するときに発生する一般的なエラーのトラブルシューティング方法について説明します

アプリケーションの ID と認証の中心部分として、Microsoft Entra Domain Services では問題が発生することがあります。 問題が発生した場合には、一般的なエラーメッセージと、それに関連したトラブルシューティング手順を利用して、問題を解決し再び正常に動作させることができます。 また、追加のトラブルシューティング支援を求めて、いつでも [Azure サポート リクエストを開く](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-to-get-support)ことができます。

この記事では、Domain Services の一般的な問題のトラブルシューティング手順について説明します。

### Microsoft Entra ディレクトリに対して Microsoft Entra Domain Services を有効にすることはできません

Domain Services の有効化で問題が発生した場合は、次の一般的なエラーと手順を確認して解決してください。

| **サンプル エラー メッセージ** | **解決策** |
| --- | --- |
| *aaddscontoso.com 名は、このネットワークで既に使用されています。 使用されていない名前を指定します。* | [仮想ネットワークでのドメイン名の競合](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/troubleshoot#domain-name-conflict) |
| *この Microsoft Entra テナントで Domain Services を有効にできませんでした。 このサービスには、Microsoft Entra Domain Services Sync というアプリケーションに対する適切なアクセス許可がありません。"Microsoft Entra Domain Services Sync" という名前のアプリケーションを削除し、Microsoft Entra テナントに対して Domain Services を有効にしてみてください。* | [Domain Services に Microsoft Entra Domain Services Sync アプリケーションに対する適切なアクセス許可がありません](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/troubleshoot#inadequate-permissions) |
| *この Microsoft Entra テナントで Domain Services を有効にできませんでした。 Microsoft Entra テナントの Domain Services アプリケーションには、Domain Services を有効にするために必要なアクセス許可がありません。 アプリケーション識別子 d87dcbc6-a371-462e-88e3-28ad15ec4e64 のアプリケーションを削除し、Microsoft Entra テナントの Domain Services を有効にしてみてください。* | [Domain Services アプリケーションが Microsoft Entra テナントで正しく構成されていない](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/troubleshoot#invalid-configuration) |
| *この Microsoft Entra テナントで Domain Services を有効にできませんでした。 Microsoft Entra アプリケーションは、Microsoft Entra テナントで無効になっています。 アプリケーション識別子 00000002-0000-0000-c000-0000000000000 を使用してアプリケーションを有効にし、Microsoft Entra テナントの Domain Services を有効にしてみてください。* | [Microsoft Entra テナントで Microsoft Graph アプリケーションが無効になっている](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/troubleshoot#microsoft-graph-disabled) |

#### ドメイン名の競合

**エラー メッセージ**

*aaddscontoso.com 名は、このネットワークで既に使用されています。 使用されていない名前を指定します。*

**解決策**

同じドメイン名またはピアリングされた仮想ネットワーク上に既存の AD DS 環境がないことを確認します。 たとえば、Azure VM 上で実行される *aaddscontoso.com* という名前の AD DS ドメインがあるとします。 仮想ネットワーク上で同じドメイン名 *の aaddscontoso.com* を持つ Domain Services マネージド ドメインを有効にしようとすると、要求された操作は失敗します。

このエラーは、仮想ネットワーク上のドメイン名の名前の競合が原因です。 DNS 参照では、要求されたドメイン名に対して既存の AD DS 環境が応答するかどうかを確認します。 このエラーを解決するには、別の名前を使用してマネージド ドメインを設定するか、既存の AD DS ドメインのプロビジョニングを解除してから、もう一度 Domain Services を有効にしてみてください。

#### アクセス許可が不十分

**エラー メッセージ**

*この Microsoft Entra テナントで Domain Services を有効にできませんでした。 このサービスには、Microsoft Entra Domain Services Sync というアプリケーションに対する適切なアクセス許可がありません。"Microsoft Entra Domain Services Sync" という名前のアプリケーションを削除し、Microsoft Entra テナントに対して Domain Services を有効にしてみてください。*

**解決策**

*Microsoft Entra ディレクトリに Microsoft Entra Domain Services Sync* という名前のアプリケーションがあるかどうかを確認します。 このアプリケーションが存在する場合は、そのアプリケーションを削除してから、ドメイン サービスを有効にしてください。 既存のアプリケーションを確認し、必要に応じて削除するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、左側のナビゲーション メニューから **Microsoft Entra ID を**選択します。
2. **[エンタープライズ アプリケーション] を選択します**。 [ *アプリケーションの* 種類] ドロップダウン メニューから [すべての **アプリケーション** ] を選択し、[ **適用**] を選択します。
3. 検索ボックスに「 *Microsoft Entra Domain Services Sync」と入力します*。アプリケーションが存在する場合は、アプリケーションを選択して [削除] を選択 **します**。
4. アプリケーションを削除したら、Domain Services をもう一度有効にしてみてください。

#### 構成が無効です

**エラー メッセージ**

*この Microsoft Entra テナントで Domain Services を有効にできませんでした。 Microsoft Entra テナントの Domain Services アプリケーションには、Domain Services を有効にするために必要なアクセス許可がありません。 アプリケーション識別子 d87dcbc6-a371-462e-88e3-28ad15ec4e64 のアプリケーションを削除し、Microsoft Entra テナントの Domain Services を有効にしてみてください。*

**解決策**

Microsoft Entra ディレクトリに*、アプリケーション識別子が d87dcbc6-a371-462e-88e3-28ad15ec4e64* の *AzureActiveDirectoryDomainControllerServices* という名前の既存のアプリケーションがあるかどうかを確認します。 このアプリケーションが存在する場合は、アプリケーションを削除してから、もう一度試して Domain Services を有効にします。

次の PowerShell スクリプトを使用して、既存のアプリケーション インスタンスを検索し、必要に応じて削除します。

```powershell
$InformationPreference = "Continue"
$WarningPreference = "Continue"

$aadDsSp = Get-MgServicePrincipal -Filter "AppId eq 'd87dcbc6-a371-462e-88e3-28ad15ec4e64'" -ErrorAction Ignore
if ($aadDsSp -ne $null)
{
    Write-Information "Found Azure AD Domain Services application. Deleting it ..."
    Remove-MgServicePrincipal -ServicePrincipalId $aadDsSp.Id
    Write-Information "Deleted the Azure AD Domain Services application."
}

$identifierUri = "https://sync.aaddc.activedirectory.windowsazure.com"
$appFilter = "IdentifierUris eq '" + $identifierUri + "'"
$app = Get-MgApplication -Filter $appFilter
if ($app -ne $null)
{
    Write-Information "Found Azure AD Domain Services Sync application. Deleting it ..."
    Remove-MgApplication -ApplicationId  $app.Id
    Write-Information "Deleted the Azure AD Domain Services Sync application."
}

$spFilter = "ServicePrincipalNames eq '" + $identifierUri + "'"
$sp = Get-MgServicePrincipal -Filter $spFilter
if ($sp -ne $null)
{
    Write-Information "Found Azure AD Domain Services Sync service principal. Deleting it ..."
    Remove-MgServicePrincipal -ObjectId $sp.Id
    Write-Information "Deleted the Azure AD Domain Services Sync service principal."
}
```

#### Microsoft Graph が無効になっている

**エラー メッセージ**

*この Microsoft Entra テナントで Domain Services を有効にできませんでした。 Microsoft Entra アプリケーションは、Microsoft Entra テナントで無効になっています。 アプリケーション識別子 00000002-0000-0000-c000-0000000000000 を使用してアプリケーションを有効にし、Microsoft Entra テナントの Domain Services を有効にしてみてください。*

**解決策**

識別子 *00000002-0000-0000-c000-0000000000000* を持つアプリケーションを無効にしたかどうかを確認します。 このアプリケーションは Microsoft Entra アプリケーションであり、Microsoft Entra テナントへの Graph API アクセスを提供します。 Microsoft Entra テナントを同期するには、このアプリケーションを有効にする必要があります。

このアプリケーションの状態を確認し、必要に応じて有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、**エンタープライズ アプリケーション**を検索して選択します。
2. [ *アプリケーションの* 種類] ドロップダウン メニューから [すべての **アプリケーション** ] を選択し、[ **適用**] を選択します。
3. 検索ボックスに、「 *000000002-0000-0000-c000-0000000000000*」と入力します。 アプリケーションを選択し、[プロパティ] を選択 **します**。
4. **[ユーザーのサインインを有効にする]** が *[いいえ*] に設定されている場合は、値を *[はい*] に設定し、[保存] を選択**します**。
5. アプリケーションを有効にしたら、Domain Services をもう一度有効にしてみてください。

### ユーザーが Microsoft Entra Domain Services のマネージド ドメインにサインインできない

Microsoft Entra テナント内の 1 人以上のユーザーがマネージド ドメインにサインインできない場合は、次のトラブルシューティング手順を実行します。

- **資格情報の形式** - UPN 形式を使用して、 `dee@aaddscontoso.onmicrosoft.com`などの資格情報を指定してみてください。 ドメイン サービスで資格情報を指定するには、UPN 形式をお勧めします。 この UPN が Microsoft Entra ID で正しく構成されていることを確認します。

    *AADDSCONTOSO\driley* などのアカウントの *SAMAccountName* は、テナントに同じ UPN プレフィックスを持つ複数のユーザーがいる場合、または UPN プレフィックスが過度に長い場合に自動生成される可能性があります。 そのため、アカウントの *SAMAccountName* 形式は、オンプレミス ドメインで想定される形式や使用する形式とは異なる場合があります。
- **パスワード同期** - [Microsoft Entra Connect を使用して](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance#enable-user-accounts-for-azure-ad-ds)[、クラウド専用ユーザー](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-password-hash-sync)またはハイブリッド環境に対してパスワード同期を有効にしていることを確認します。

    - **ハイブリッド同期アカウント:** 影響を受けるユーザー アカウントがオンプレミスのディレクトリから同期されている場合は、次の領域を確認します。

        - [Microsoft Entra Connect の最新の推奨リリース](https://www.microsoft.com/download/details.aspx?id=47594)をデプロイまたは更新しました。
        - [完全同期を実行](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-password-hash-sync)するように Microsoft Entra Connect を構成しました。
        - ディレクトリのサイズによっては、ユーザー アカウントと資格情報ハッシュがマネージド ドメインで使用できるようになるまで時間がかかる場合があります。 マネージド ドメインに対して認証を試みる前に、十分な時間待機してください。
        - 前の手順を確認した後も問題が解決しない場合は、 *Azure AD 同期サービス*を再起動してみてください。 Microsoft Entra Connect サーバーからコマンド プロンプトを開き、次のコマンドを実行します。

            ```console
            net stop 'Microsoft Azure AD Sync'
            net start 'Microsoft Azure AD Sync'
            ```
    - **クラウド専用アカウント**: 影響を受けるユーザー アカウントがクラウド専用ユーザー アカウントの場合 [は、Domain Services を有効にした後にユーザーがパスワードを変更](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance#enable-user-accounts-for-azure-ad-ds)したことを確認します。 このパスワード リセットにより、マネージド ドメインに必要な資格情報ハッシュが生成されます。
- **ユーザー アカウントがアクティブであることを確認**します。既定では、マネージド ドメインで 2 分以内に無効なパスワードが 5 回試行されると、ユーザー アカウントが 30 分間ロックアウトされます。 アカウントがロックアウトされている間、ユーザーはサインインできません。30 分後、ユーザー アカウントは自動的にロック解除されます。

    - マネージド ドメインで無効なパスワードが試されても、Microsoft Entra ID のユーザー アカウントはロックアウトされません。 ユーザー アカウントは、マネージド ドメイン内でのみロックアウトされます。 Microsoft Entra ID ではなく*、管理 VM* を使用して[、Active Directory 管理コンソール (ADAC)](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-management-vm) のユーザー アカウントの状態を確認します。
    - また、きめ [細かいパスワード ポリシーを構成して、既定の](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/password-policy) ロックアウトのしきい値と期間を変更することもできます。
- **外部アカウント** - 影響を受けるユーザー アカウントが Microsoft Entra テナントの外部アカウントではないことを確認します。 外部アカウントの例としては、 `dee@live.com` などの Microsoft アカウントや、外部の Microsoft Entra ディレクトリのユーザー アカウントなどがあります。 Domain Services は外部ユーザー アカウントの資格情報を格納しないため、マネージド ドメインにサインインできません。

### マネージド ドメインに 1 つ以上のアラートがある

マネージド ドメインにアクティブなアラートがある場合、認証プロセスが正常に動作しなくなる可能性があります。

アクティブなアラートがあるかどうかを確認するには、 [マネージド ドメインの正常性状態を確認します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/check-health)。 アラートが表示された場合は、 [トラブルシューティングを行って解決します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/troubleshoot-alerts)。

### Microsoft Entra テナントから削除されたユーザーは、マネージド ドメインから削除されません

Microsoft Entra ID は、ユーザー オブジェクトが誤って削除されないように保護します。 Microsoft Entra テナントからユーザー アカウントを削除すると、対応するユーザー オブジェクトがごみ箱に移動されます。 この削除操作がマネージド ドメインに同期されると、Domain Services にごみ箱がないため、対応するユーザー アカウントが削除されます。

ユーザー アカウントがテナントで復元された場合、Domain Services は、変更をマネージド ドメインに同期するときに、アカウントのすべてのリンクをフェッチします。 マネージド ドメインのユーザー アカウントは、新しいグローバル一意識別子 (GUID) とセキュリティ ID (SID) を取得します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/troubleshoot-account-lockout"} -->
## Microsoft Entra Domain Services のアカウント ロックアウト問題を解決する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/troubleshoot-account-lockout
- Service: entra-id / domain-services
- Article date: 2024-12-03
- Summary: Microsoft Entra Domain Services でユーザー アカウントのロックアウトを引き起こす一般的な問題を解決する方法について説明します。

悪意のあるサインイン試行が繰り返し行われるのを防ぐために、AMicrosoft Entra Domain Services のマネージド ドメインでは、定義されたしきい値を超えたときにアカウントがロックされます。 このアカウント ロックアウトは、サインイン攻撃がなくても偶発的に起こることがあります。 たとえば、ユーザーが間違ったパスワードを繰り返し入力したり、サービスで古いパスワードの使用が試行されたりしたとき、アカウントはロックアウトされます。

このトラブルシューティング記事では、アカウント ロックアウトが発生する理由、ロックアウト動作を設定する方法、ロックアウト問題を解決する目的でセキュリティ監査を確認する方法について説明します。

### アカウント ロックアウトとは何ですか。

サインインの試行失敗に対して定義されているしきい値に到達すると、Domain Services マネージド ドメインのユーザー アカウントはロックアウトされます。 サインインが総当たり式に繰り返し試行される場合、自動化されたデジタル攻撃の可能性があります。このアカウント ロックアウト動作はユーザーをそのような攻撃から守るように設計されています。

**既定では、2 分間で 5 回パスワードの入力に失敗すると、アカウントは 30 分間ロックアウトされます。**

アカウント ロックアウトの既定のしきい値は、細かい設定が可能なパスワード ポリシーを利用して構成されます。 特定の一連の要件がある場合、アカウント ロックアウトの既定のしきい値をオーバーライドできます。 ただし、アカウントのロックアウト数を減らす目的でしきい値の上限を上げることはお勧めしません。 まず、アカウント ロックアウト動作の原因を解決してください。

#### 細かい設定が可能なパスワード ポリシー

細かい設定が可能なパスワード ポリシー (FGPP) を使用すると、パスワードおよびアカウント ロックアウトのポリシーに対して、ドメイン内の異なるユーザーに特定の制限を適用できます。 FGPP は、マネージド ドメイン内のユーザーにのみ影響します。 Microsoft Entra ID からマネージド ドメインに同期されたクラウド ユーザーとドメイン ユーザーは、マネージド ドメイン内のパスワード ポリシーの影響のみを受けます。 Microsoft Entra ID またはオンプレミスのディレクトリ内のアカウントは影響を受けません。

ポリシーは、マネージド ドメイン内のグループの関連付けを通じて配布されます。すべての変更が、ユーザーの次回サインイン時に適用されます。 ポリシーを変更しても、既にロックアウトされているユーザー アカウントのロックが解除されることはありません。

細かい設定が可能なパスワード ポリシーと、Domain Services で直接作成されたユーザーと Microsoft Entra ID から同期されたユーザーの違いの詳細については、「[パスワードとアカウントのロックアウト ポリシーの構成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/password-policy)」を参照してください。

### アカウント ロックアウトの一般的な理由

アカウントがロックアウトされる最も一般的な理由には、悪意のある意図や要因を除いて、次のようなシナリオが含まれます。

- **ユーザーが自分自身をロックアウトした。**
    - 最近パスワードを変更した後、以前のパスワードを使用しませんでしたか。 ユーザーが古いパスワードをうっかり再試行すると、2 分間で 5 回失敗という既定のポリシーがアカウント ロックアウトの原因となることがあります。
- **古いパスワードが指定されたアプリケーションまたはサービスが存在する。**
    - アカウントがアプリケーションまたはサービスによって使用されている場合、そのようなリソースで、古いパスワードによるサインインが繰り返し試されることがあります。 この動作により、アカウントのロックアウトが引き起こされることがあります。
    - 複数の異なるアプリケーションまたはサービスをまたぐアカウントの使用は最小限に留め、資格情報の使用場所を記録してください。 アカウントのパスワードが変更された場合、関連付けられているアプリケーションまたはサービスを適宜更新してください。
- **別の環境でパスワードが変更されたが、新しいパスワードがまだ同期されていない。**
    - オンプレミスの AD DS 環境など、マネージド ドメインの外部でアカウントのパスワードが変更された場合、そのパスワード変更が Microsoft Entra ID 経由でマネージド ドメインに同期するまで数分かかることがあります。
    - そのパスワード同期プロセスが完了する前にマネージド ドメイン内のリソースにサインインしようとすると、アカウントがロックアウトされます。

### セキュリティ監査によるアカウント ロックアウトのトラブルシューティング

アカウント ロックアウトがいつ発生し、どこでロックアウトが発生するかトラブルシューティングするには、[Domain Services 向けセキュリティ監査を有効にします](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/security-audit-events)。 監査イベントのキャプチャは、この機能を有効にした時点からに限られます。 理想的には、トラブルシューティングするアカウント ロックアウト問題が発生する*前に*セキュリティ監査を有効にしてください。 ユーザー アカウントでロックアウト問題が繰り返される場合、それが次に発生するときのための準備としてセキュリティ監査を有効にできます。

セキュリティ監査を有効にすると、次のサンプル クエリから、*アカウント ロックアウト イベント* コード *4740* をレビューする方法が示されます。

直近 7 日間のすべてのアカウント ロックアウト イベントを表示します。

```Kusto
AADDomainServicesAccountManagement
| where TimeGenerated >= ago(7d)
| where OperationName has "4740"
```

*driley* という名前のアカウントを対象に、直近 7 日間のすべてのアカウント ロックアウト イベントを表示します。

```Kusto
AADDomainServicesAccountLogon
| where TimeGenerated >= ago(7d)
| where OperationName has "4740"
| where "driley" == tolower(extract("Logon Account:\t(.+[0-9A-Za-z])",1,tostring(ResultDescription)))
```

2020 年 6 月 26 日午前 9 時から 2020 年 7 月 1 日午前 0 時までのすべてのアカウント ロックアウト イベントを、日時の昇順で並び替えて表示します。

```Kusto
AADDomainServicesAccountManagement
| where TimeGenerated >= datetime(2020-06-26 09:00) and TimeGenerated <= datetime(2020-07-01)
| where OperationName has "4740"
| sort by TimeGenerated asc
```

4776 および 4740 の "ソース ワークステーション:" のイベント詳細が空の場合があります。 これは、他のデバイスからのネットワーク ログオンで不正なパスワードが発生したためです。

たとえば、RADIUS サーバーでは認証を Domain Services に転送できます。

03/04 19:07:29 [LOGON] [10752] contoso: SamLogon: Transitive Network logon of contoso\Nagappan.Veerappan from (via LOB11-RADIUS) Entered

03/04 19:07:29 [LOGON] [10752] contoso: SamLogon: Transitive Network logon of contoso\Nagappan.Veerappan from (via LOB11-RADIUS) Returns 0xC000006A

03/04 19:07:35 [LOGON] [10753] contoso: SamLogon: Transitive Network logon of contoso\Nagappan.Veerappan from (via LOB11-RADIUS) Entered

03/04 19:07:35 [LOGON] [10753] contoso: SamLogon: Transitive Network logon of contoso\Nagappan.Veerappan from (via LOB11-RADIUS) Returns 0xC000006A

バックエンドへの NSG 内にある DC に対する RDP を有効にして、診断キャプチャ (netlogon) を構成します。 要件の詳細については、「[受信セキュリティ規則](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/alert-nsg#inbound-security-rules)」を参照してください。

既定の NSG を既に変更している場合は、「[ポート 3389 - リモート デスクトップを使用した管理](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/network-considerations#port-3389---management-using-remote-desktop)」に従います。

任意のサーバーで Netlogon ログを有効にするには、「[Netlogon サービスのデバッグ ログを有効にする](https://learn.microsoft.com/ja-jp/troubleshoot/windows-client/windows-security/enable-debug-logging-netlogon-service)」に従います。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/troubleshoot-alerts"} -->
## Microsoft Entra Domain Servicesの一般的なアラートと解決策 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/troubleshoot-alerts
- Service: entra-id / domain-services
- Article date: 2025-02-19
- Summary: Microsoft Entra Domain Servicesの正常性状態の一部として生成される一般的なアラートを解決する方法について説明します

アプリケーションの ID と認証の中心的な部分として、Microsoft Entra Domain Servicesに問題が発生することがあります。 問題が発生した場合、動作の再開に役立ついくつかの一般的なアラートと、関連するトラブルシューティングの手順があります。 いつでも、[Azure サポートリクエスト](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-to-get-support)を開くことで、トラブルシューティングの助けを得ることもできます。

この記事では、Domain Services での一般的なアラートのトラブルシューティング情報を示します。

### AADDS100: ディレクトリが見つかりません

#### アラート メッセージ

*マネージド ドメインに関連付けられているMicrosoft Entra ディレクトリが削除されている可能性があります。 マネージド ドメインはもうサポートされる構成に含まれていません。 Microsoftは、マネージド ドメインの監視、管理、修正プログラムの適用、同期を行うことはできません。*

#### 解決方法

Azure サブスクリプションを新しいMicrosoft Entra ディレクトリに移動すると、通常、このエラーが発生します。 さらに、Domain Services に関連付けられている古いMicrosoft Entra ディレクトリを削除すると、このエラーも発生します。

このエラーは回復できません。 アラートを解決するには、[既存のマネージド ドメインを削除](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/delete-aadds)し、新しいディレクトリ内に再作成します。 マネージド ドメインの削除で問題が発生した場合は、[Azure サポートリクエスト](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-to-get-support)を開始して、トラブルシューティングの支援を受けてください。

### AADDS101: AZURE AD B2C がこのディレクトリで実行されています

Von Bedeutung

2025 年 5 月 1 日より、Azure Active Directory B2C (Azure AD B2C) は新規のお客様が購入できなくなります。 詳細については、「[AZURE AD B2C は引き続き購入できますか?](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale)に関する FAQ を参照してください。

#### アラート メッセージ

Microsoft Entra Domain ServicesをAzure AD B2C Directory.

#### 解決方法

Domain Services は、Microsoft Entra ディレクトリと自動的に同期します。 Microsoft Entra ディレクトリが B2C 用に構成されている場合、Domain Services を展開して同期することはできません。

Domain Services を使用するには、次の手順に従って、Azure以外の AD B2C ディレクトリにマネージド ドメインを再作成する必要があります。

1. [既存のMicrosoft Entra ディレクトリからマネージド ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/delete-aadds)を削除します。
2. AZURE AD B2C ディレクトリではない新しいMicrosoft Entra ディレクトリを作成します。
3. [代替のマネージド ドメインを作成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)。

マネージド ドメインの正常性が 2 時間以内に自動的に更新され、アラートが削除されます。

### AADDS103:アドレスがパブリック IP 範囲内である

#### アラート メッセージ

*Microsoft Entra Domain Servicesを有効にした仮想ネットワークの IP アドレス範囲がパブリック IP 範囲内にあります。 Microsoft Entra Domain Servicesは、プライベート IP アドレス範囲を持つ仮想ネットワークで有効にする必要があります。 この構成は、マネージド ドメインの監視、管理、修正プログラムの適用、同期を行うMicrosoftの機能に影響します。*

#### 解決方法

開始する前に、[プライベート IP v4 アドレス空間](https://en.wikipedia.org/wiki/Private_network#Private_IPv4_address_spaces)について理解しておく必要があります。

仮想ネットワーク内では、VM はサブネット用に構成されているのと同じ IP アドレス範囲のリソースをAzure要求できます。 サブネットのパブリック IP アドレス範囲を構成すると、仮想ネットワーク内でルーティングされた要求が、目的の Web リソースに届かない場合があります。 この構成により、Domain Services で予期しないエラーが発生する可能性があります。

注

仮想ネットワークに構成されているインターネットの IP アドレス範囲を保有している場合は、このアラートを無視できます。 ただし、この構成ではMicrosoft Entra Domain Services[SLA](https://azure.microsoft.com/support/legal/sla/active-directory-ds/v1_0/)にコミットできません。予期しないエラーが発生する可能性があるためです。

このアラートを解決するには、既存のマネージド ドメインを削除し、プライベート IP アドレス範囲を持つ仮想ネットワーク内に再作成します。 マネージド ドメインが使用できず、OU やサービス アカウントなどのカスタム リソースが失われるので、このプロセスは中断されます。

1. ディレクトリから[マネージド ドメインを削除](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/delete-aadds)します。
2. 仮想ネットワークの IP アドレス範囲を更新するには、Microsoft Entra 管理センターで *Virtual network* を検索して選択します。 誤ってパブリック IP アドレス範囲が設定されている Domain Services の仮想ネットワークを選択します。
3. **[設定]** で、 *[アドレス空間]* を選択します。
4. 既存のアドレス範囲を選択し、それを編集するかアドレス範囲を追加することでアドレス範囲を更新します。 新しい IP アドレス範囲がプライベート IP 範囲にあることを確認します。 準備ができたら、変更を**保存**します。
5. 左側のナビゲーション メニューで、 **[サブネット]** を選択します。
6. 編集するサブネットを選択するか、追加のサブネットを作成します。
7. プライベート IP アドレス範囲を更新または指定してから、変更を**保存**します。
8. [代替のマネージド ドメインを作成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)。 必ず、プライベート IP アドレス範囲で更新された仮想ネットワーク サブネットを選択してください。

マネージド ドメインの正常性が 2 時間以内に自動的に更新され、アラートが削除されます。

### AADDS106: Azure サブスクリプションが見つかりません

#### アラート メッセージ

*マネージド ドメインに関連付けられているAzure サブスクリプションが削除されました。 Microsoft Entra Domain Servicesは、アクティブなサブスクリプションが正常に機能し続ける必要があります。*

#### 解決方法

Domain Services にはアクティブなサブスクリプションが必要です。別のサブスクリプションに移動することはできません。 マネージド ドメインが関連付けられたAzure サブスクリプションが削除された場合は、Azure サブスクリプションとマネージド ドメインを再作成する必要があります。

1. [Azure サブスクリプションを作成します](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/create-subscription)。
2. [既存のMicrosoft Entra ディレクトリからマネージド ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/delete-aadds)を削除します。
3. [代替のマネージド ドメインを作成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)。

### AADDS107: Azure サブスクリプションが無効になっている

#### アラート メッセージ

*マネージド ドメインに関連付けられているAzure サブスクリプションがアクティブではありません。 Microsoft Entra Domain Servicesは、アクティブなサブスクリプションが正常に機能し続ける必要があります。*

#### 解決方法

Domain Services にはアクティブなサブスクリプションが必要です。 マネージド ドメインが関連付けられたAzure サブスクリプションがアクティブでない場合は、サブスクリプションを更新して再アクティブ化する必要があります。

1. [Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/subscription-disabled)を更新します。
2. サブスクリプションが更新されたら、Domain Services 通知を使用して、マネージド ドメインを再び有効にすることができます。

マネージド ドメインが再度有効にされると、マネージド ドメインの正常性が 2 時間以内に自動的に更新され、アラートが削除されます。

### AADDS108:サブスクリプションのディレクトリが移動した

#### アラート メッセージ

*Microsoft Entra Domain Servicesによって使用されるサブスクリプションが別のディレクトリに移動されました。 Microsoft Entra Domain Servicesが正常に機能するには、同じディレクトリにアクティブなサブスクリプションが必要です。*

#### 解決方法

Domain Services にはアクティブなサブスクリプションが必要です。別のサブスクリプションに移動することはできません。 マネージド ドメインが関連付けられたAzure サブスクリプションを移動する場合は、次の 2 つのオプションがあります。

- サブスクリプションを前のディレクトリに戻すか、
- 既存のディレクトリから[マネージド ドメインを削除](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/delete-aadds)し、[選択したサブスクリプションに置き換えるマネージド ドメインを作成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)。

### AADDS109:マネージド ドメインのリソースが見つからない

#### アラート メッセージ

*ご利用のマネージド ドメインに使用されているリソースが削除されています。 このリソースは、Microsoft Entra Domain Servicesが正常に機能するために必要です。*

#### 解決方法

Domain Services では、パブリック IP アドレス、仮想ネットワーク インターフェイス、ロード バランサーなど、適切に機能するために追加のリソースが作成されます。 これらのリソースのいずれかが削除されると、マネージド ドメインはサポートされない状態になり、ドメインが管理されなくなります。 これらのリソースの詳細については、「[Domain Services によって使用されるネットワーク リソース](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/network-considerations#network-resources-used-by-azure-ad-ds)」を参照してください。

このアラートは、これらの必要なリソースのいずれかが削除されたときに生成されます。 リソースが 4 時間以内に削除された場合、Azure プラットフォームで削除されたリソースを自動的に再作成できる可能性があります。 次の手順では、リソースの削除の正常性状態とタイムスタンプを確認する方法について説明します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、**Domain Services** を検索して選択します。 目的のマネージド ドメインを選択します (例: *aaddscontoso.com* )。
2. 左側のナビゲーションで、 **[正常性]** を選択します。
3. 正常性ページで、ID *AADDS109* のアラートを選択します。
4. アラートには、最初に検出されたときのタイムスタンプがあります。 そのタイムスタンプが 4 時間未満の場合、Azure プラットフォームはリソースを自動的に再作成し、アラートを単独で解決できる可能性があります。

    さまざまな理由から、アラートは 4 時間より古い場合があります。 その場合は、[マネージド ドメインを削除](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/delete-aadds)してから[代替のマネージド ドメインを作成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)して直ちに修正するか、インスタンスを修正するためのサポート リクエストを開くことができます。 問題の性質によっては、バックアップからの復元がサポートに必要な場合があります。

### AADDS110:マネージド ドメインに関連付けられているサブネットが満杯

#### アラート メッセージ

*Microsoft Entra Domain Servicesのデプロイ用に選択されたサブネットがいっぱいになり、作成する必要がある追加のドメイン コントローラーの領域がありません*

#### 解決方法

Domain Services の仮想ネットワーク サブネットには、自動的に作成されたリソースのための十分な IP アドレスが必要です。 この IP アドレス空間には、メンテナンス イベントがある場合に代替リソースを作成する必要性があります。 使用可能な IP アドレスが不足するリスクを最小限に抑えるために、独自の VM などの追加リソースをマネージド ドメインと同じ仮想ネットワーク サブネットにデプロイしないでください。

このエラーは回復できません。 アラートを解決するには、[既存のマネージド ドメインを削除](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/delete-aadds)し、新たに作成します。 マネージド ドメインの削除で問題が発生した場合は、[Azure サポート要求](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-to-get-support)を開き、詳細を確認してください。

### AADDS111: サービス プリンシパルは認可されていません

#### アラート メッセージ

*Microsoft Entra Domain Services がドメインをサービスするために使用するサービス プリンシパルは、Azure サブスクリプション内のリソースを管理する権限がありません。 The service principal needs to gain permissions to service your managed domain. (このサービス プリンシパルは、マネージド ドメインにサービスを提供するためのアクセス許可を取得する必要があります。)*

#### 解決方法

自動的に生成されるサービス プリンシパルの中には、マネージド ドメインのリソースの管理と作成に使用されるものがあります。 これらのいずれかのサービス プリンシパルのアクセス許可が変更されると、ドメインではリソースを正しく管理できません。 次の手順では、サービス プリンシパルに対するアクセス許可を理解し、付与する方法について説明します。

1. [Azureロールベースのアクセス制御と、Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal)内のアプリケーションへのアクセスを許可する方法について説明します。
2. ID *abba844e-bc0e-44b0-947a-dc74e5d09022* のサービス プリンシパルが持っているアクセス権を確認し、以前に拒否されたアクセス権を付与します。

### AADDS112:マネージド ドメインに十分な数の IP アドレスがない

#### アラート メッセージ

*このドメインの仮想ネットワークのサブネットに十分な IP アドレスがない可能性があることを確認しました。 Microsoft Entra Domain Services、有効になっているサブネット内に少なくとも 2 つの使用可能な IP アドレスが必要です。 サブネット内に少なくとも 3 - 5 個のスペア IP アドレスを用意することをお勧めします。 他の仮想マシンがサブネット内にデプロイされ、使用可能な数の IP アドレスがすべて使用されたか、サブネット内の使用可能 IP アドレスの数に制限がある場合にこの状況が発生した可能性があります。*

#### 解決方法

Domain Services の仮想ネットワーク サブネットには、自動的に作成されたリソースのための十分な IP アドレスが必要です。 この IP アドレス空間には、メンテナンス イベントがある場合に代替リソースを作成する必要性があります。 使用可能な IP アドレスが不足するリスクを最小限に抑えるために、独自の VM などの追加リソースをマネージド ドメインと同じ仮想ネットワーク サブネットにデプロイしないでください。

このアラートを解決するには、既存のマネージド ドメインを削除し、十分大きい IP アドレス範囲を持つ仮想ネットワーク内に再作成します。 このプロセスは、マネージド ドメインが使用できず、OU やサービス アカウントのような作成したカスタム リソースが失われるため、破壊的です。

1. ディレクトリから[マネージド ドメインを削除](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/delete-aadds)します。
2. 仮想ネットワークの IP アドレス範囲を更新するには、Microsoft Entra 管理センターで *Virtual network* を検索して選択します。 IP アドレス範囲が小さいマネージド ドメインの仮想ネットワークを選択します。
3. **[設定]** で、 *[アドレス空間]* を選択します。
4. 既存のアドレス範囲を選択し、それを編集するかアドレス範囲を追加することでアドレス範囲を更新します。 新しい IP アドレス範囲がマネージド ドメインのサブネット範囲にとって十分な大きさであることを確認してください。 準備ができたら、変更を**保存**します。
5. 左側のナビゲーション メニューで、 **[サブネット]** を選択します。
6. 編集するサブネットを選択するか、追加のサブネットを作成します。
7. 十分大きい IP アドレス範囲を更新または指定してから、変更を**保存**します。
8. [代替のマネージド ドメインを作成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)。 必ず、十分な IP アドレス範囲で更新された仮想ネットワーク サブネットを選択してください。

マネージド ドメインの正常性が 2 時間以内に自動的に更新され、アラートが削除されます。

### AADDS113:リソースが復旧できない

#### アラート メッセージ

*Microsoft Entra Domain Servicesによって使用されたリソースが予期しない状態で検出され、復旧できません*

#### 解決方法

Domain Services では、パブリック IP アドレス、仮想ネットワーク インターフェイス、ロード バランサーなど、適切に機能するために追加のリソースが作成されます。 これらのリソースのいずれかが変更されると、マネージド ドメインはサポートされない状態になり、管理できなくなります。 これらのリソースの詳細については、「[Domain Services によって使用されるネットワーク リソース](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/network-considerations#network-resources-used-by-azure-ad-ds)」を参照してください。

このアラートは、これらの必要なリソースのいずれかが変更されたとき、Domain Services によって自動的に復旧できない場合に生成されます。 アラートを解決するには、[インスタンスを修正するためにAzure サポート要求](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-to-get-support)を開きます。

### AADDS114:サブネットが無効

#### アラート メッセージ

*Microsoft Entra Domain Servicesのデプロイ用に選択されたサブネットは無効であり、使用できません*

#### 解決方法

このエラーは回復できません。 アラートを解決するには、[既存のマネージド ドメインを削除](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/delete-aadds)し、新たに作成します。 マネージド ドメインの削除で問題が発生した場合は、[Azure サポート要求](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-to-get-support)を開き、詳細を確認してください。

### AADDS115:リソースがロックされている

#### アラート メッセージ

*ターゲット スコープがロックされているため、マネージド ドメインで使用されている 1 つ以上のネットワーク リソースを操作できません。*

#### 解決方法

リソース ロックをAzureリソースに適用して、変更や削除を防ぐことができます。 Domain Services はマネージド サービスであるため、Azure プラットフォームには構成を変更する機能が必要です。 一部の Domain Services コンポーネントにリソース ロックが適用されている場合、Azure プラットフォームは管理タスクを実行できません。

Domain Services コンポーネントのリソース ロックを確認して削除するには、次の手順を実行します。

1. 仮想ネットワーク、ネットワーク インターフェイス、パブリック IP アドレスなど、リソース グループ内のマネージド ドメインのネットワーク コンポーネントごとに、Microsoft Entra 管理センターの操作ログを確認します。 これらの操作ログには、操作が失敗した理由とリソース ロックが適用されている場所が示されている必要があります。
2. ロックが適用されているリソースを選択し、 **[ロック]** の下でロックを選択して削除します。

### AADDS116:リソースが使用できない

#### アラート メッセージ

*ポリシー制限により、マネージド ドメインで使用されている 1 つ以上のネットワーク リソースを操作できません。*

#### 解決方法

ポリシーは、許可される構成アクションを制御するAzureリソースとリソース グループに適用されます。 Domain Services はマネージド サービスであるため、Azure プラットフォームには構成を変更する機能が必要です。 一部の Domain Services コンポーネントにポリシーが適用されている場合、Azure プラットフォームは管理タスクを実行できない可能性があります。

Domain Services コンポーネントに適用されているポリシーを確認して更新するには、次の手順を実行します。

1. 仮想ネットワーク、NIC、パブリック IP アドレスなど、リソース グループ内のマネージド ドメインのネットワーク コンポーネントごとに、Microsoft Entra 管理センターの操作ログを確認します。 これらの操作ログには、操作が失敗した理由と制限の厳しいポリシーが適用されている場所が示されています。
2. ポリシーが適用されているリソースを選択し、 **[ポリシー]** から、制限が緩和されるように、ポリシーを選択して編集します。

### AADDS120: マネージド ドメインで、1 つ以上のカスタム属性のオンボード時にエラーが発生しました

#### アラート メッセージ

*次のMicrosoft Entra拡張機能プロパティは、同期のカスタム属性として正常にオンボードされていません。 This may happen if a property conflicts with the built-in schema: [extensions] (これは、プロパティが組み込みスキーマと競合する場合に発生する可能性があります: [拡張機能])*

#### 解決方法

警告

カスタム属性の LDAPName は、既存の AD 組み込みスキーマ属性と競合する場合はオンボードできないため、エラーが発生します。 シナリオがブロックされている場合は、Microsoft サポートにお問い合わせください。 詳細については、[カスタム属性のオンボード](https://aka.ms/aadds-customattr)に関する記事を参照してください。

[Domain Services Health](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/check-health) アラートを確認し、どのMicrosoft Entra拡張機能プロパティが正常にオンボードできなかったかを確認します。 **[カスタム属性]** ページに移動して、拡張機能の想定されている Domain Services LDAPName を見つけます。 LDAPName が別の AD スキーマ属性と競合していないこと、または許可されている組み込み AD 属性の 1 つであることを確認します。

次に、これらの手順に従って、**[カスタム属性]** ページでカスタム属性のオンボードを再試行します。

1. 失敗した属性を選択し、[ **削除** して **保存]** を選択します。
2. 正常性アラートが削除されるまで待つか、対応する属性がドメイン参加済みの VM の **AADDSCustomAttributes**OU から削除されたことを確認します。
    - **注:** 対応する属性が 1 日以内に **AADDSCustomAttributes**OU から削除されない場合:
        1. **Azure AD Domain Services Sync** マニフェストの *addIns*セクションに対応する属性が含まれていないことを確認します。
            - **[ポータル] &gt; [アプリの登録] &gt; [すべてのアプリケーション] &gt; [Azure AD Domain Services Sync] &gt; 左側のブレード &gt; [マニフェスト]** の順に選択します
        2. AADDS DC 管理者は、**addIns** セクションに対応する属性が含まれていない**場合**、対応する属性を *AADDSCustomAttributes* OU から手動で削除できます。 アラートは、手動削除から 2 時間以内にクリアする必要があります。
3. [ **追加]** を選択し、必要な属性をもう一度選択し、[ **保存]** を選択します。

オンボードが成功すると、Domain Services は同期されたユーザーとグループにオンボードされたカスタム属性値を埋めます。 カスタム属性値は、テナントのサイズに応じて、徐々に表示されていきます。 バックフィルの状態を確認するには、[Domain Services Health](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/check-health) に移動し、**synchronization with Microsoft Entra ID** monitor timestamp が過去 1 時間以内に更新されたことを確認します。

### AADDS122: グループ ポリシー オブジェクトの競合が検出されました (プレビュー)

#### アラート メッセージ

*グループ ポリシー オブジェクトの競合が検出されました。 ドメイン コントローラーの GPO 設定を確認してください。*

#### 解決方法

ドメイン GPO の設定を確認し、問題のあるエントリを修正します。 GPO を固定できない場合は、「バックアップから GPO を復元する」のバックアップから [復元](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/group-policy)する手順が提供されます。

解決がない場合、7 日ごとに GPO の競合が発生した場合、4 つのアラートが送信されます。 30 日後、システムは残っている問題を自動的に修正し、アラートはクリアされます。

警告

自動解決により、GPO の観点からデータが失われる可能性がありますが、ドメインを正常な状態に保つには必要です。 データが失われるのを防ぐために、アラートが時間内に修正されていることを確認します。

### AADDS123: サービス チケットの発行で Kerberos RC4 の使用が検出されました

#### アラート メッセージ

Microsoft Entra Domain Services は、[CVE-2026-20833](https://www.cve.org/CVERecord?id=CVE-2026-20833) に関連するセキュリティ適用を妨げる可能性のあるサービス チケットの発行における Kerberos RC4 の使用を検出しました。

#### 解決方法

CVE-2026-20833 に関連するセキュリティ更新プログラムWindows、Kerberos KDC の動作を AES 優先の既定値に移行し、RC4 の使用を減らします。 ワークロードがまだ RC4 に依存している場合は、強制が有効になっているときに認証エラーが発生する可能性があります。

このアラートを解決するには、次の手順を実行します。

1. セキュリティ設定に従って、マネージド ドメインのセキュリティ設定から RC4 [を](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/secure-your-domain)オフにします。 RC4 に明示的に依存しているワークロード、デバイス、またはサービスがない限り、次の手順に進む必要はありません。
2. マネージド ドメインのセキュリティ イベントを有効にするには、[Microsoft Entra Domain Services](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/security-audit-events) のセキュリティ監査と DNS 監査を有効にします。 [サンプル クエリ 7](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/security-audit-events#sample-query-7) を使用して、RC4 の依存関係を特定します。
3. Kerberos チケット イベント ID **4768** と **4769** を監視して、適用をブロックする RC4 の依存関係を特定します。
4. 一時的に RC4 を必要とする、Microsoft Entra Connect 経由でオンプレミスから同期されるサービス アカウントの場合は、影響を受けるサービス アカウントの **msDS-SupportedEncryptionTypes** の値を明示的に構成します。オンプレミスの Active Directory に RC4 を含めるによって、[サービス アカウント チケット発行の変更に対する CVE-2026-20833 に関連する Kerberos KDC の RC4 使用の管理方法](https://support.microsoft.com/en-us/topic/how-to-manage-kerberos-kdc-usage-of-rc4-for-service-account-ticket-issuance-changes-related-to-cve-2026-20833-1ebcda33-720a-4da8-93c1-b0496e1910dc)に記載されています。 更新された属性は、Microsoft Entra Connect を介してマネージド ドメインに同期されます。
5. セキュリティ設定を使用してドメイン全体の Kerberos [セキュリティ](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/secure-your-domain) を強化し、他の軽減策がない限り、広範な RC4 の有効化を回避します。
6. アラートが削除されるまで、Kerberos 関連のシステム イベントと Domain Services の正常性の監視を続行します。

マネージド ドメインの正常性は 2 時間以内に自動的に更新され、RC4 の依存関係が修復されるとアラートが削除されます。

#### セルフサービス RC4 設定

*AAD DC Administrators* グループのメンバーは、マネージド ドメインで RC4 の非推奨設定を直接構成できます。 構成ファイルは、各ドメイン コントローラー上の暗号化された SMB 共有でホストされます。

##### 構成ファイルにアクセスする

次の UNC パスを使用して、ドメイン コントローラーの非表示の SMB 共有に接続します。

```
\\<domain-controller-name>\CustomerExecutionScripts$
```

`<domain-controller-name>`をマネージド ドメインのドメイン コントローラーのホスト名に置き換えます。 共有には SMB 暗号化と許可が必要です。

- **Domain Admins** への*フル アクセス*
- **AAD DC 管理者**への*アクセスを変更*する

共有には、次のファイルが含まれています。

| ファイル | Description |
| --- | --- |
| `rc4-configuration.json` | 顧客が編集可能な構成ファイル。 RC4 設定を変更するには、このファイルを編集します。 |
| `rc4-status.json` | 自動生成された状態ファイル。 このファイルを編集しないでください。 適用された変更を確認するには、それを確認します。 |
| `README.txt` | 使用手順とリファレンス。 |

##### 使用方法

1. ドメイン コントローラー上の `CustomerExecutionScripts$` 共有に接続します。
2. 目的の設定で `rc4-configuration.json` を編集します。
3. ファイルを保存します。
4. 次の評価サイクル (約 10 分) 待ちます。
5. 適用された変更の確認については、 `rc4-status.json` を確認してください。

注

`rc4-configuration.json`、`rc4-status.json`、または`README.txt`を削除したり、名前を変更したりしないでください。 サービスはこれらのファイルに依存します。

##### 構成オプション

| Setting | 価値 | Description |
| --- | --- | --- |
| `rc4DefaultDisablementPhase` | `0` | 変更なし。 RC4 は完全に許可されており、ログ記録はありません。 |
| `rc4DefaultDisablementPhase` | `1` | 監査モード。 RC4 が使用されているときに警告をログに記録しますが、引き続き許可します。 |
| `rc4DefaultDisablementPhase` | `2` | 強制モード。 AES 対応アカウントの RC4 をブロックします。 |
| `defaultDomainSupportedEncTypes` | `24` | AES のみ (最も安全、2026 年 4 月の強制の既定値)。 |
| `defaultDomainSupportedEncTypes` | `36` | RC4 + AES256 (Windows以外のレガシ相互運用に推奨)。 |
| `defaultDomainSupportedEncTypes` | `56` | セッション キーのみを使用する AES (セキュリティで保護され、最新のクライアントでは 24 より優先されます)。 |
| `defaultDomainSupportedEncTypes` | `60` | セッション キーを持つ AES + RC4 (移行に推奨)。 |
| `serviceAccountsRequiringRc4` | 文字列の配列 | `sAMAccountName` 属性に RC4 サポートを追加する必要があるサービス アカウントの `msDS-SupportedEncryptionTypes` 値の一覧。 |

表に記載されている値のみが受け入れられます。 無効な値は拒否され、検証エラー `rc4-status.json` 表示されます。

##### 構成の例

監査モードから開始し、適用する前に RC4 の使用状況を特定します (推奨される最初の手順):

```json
{
    "version": 1,
    "rc4DefaultDisablementPhase": 1,
    "defaultDomainSupportedEncTypes": 60,
    "serviceAccountsRequiringRc4": []
}
```

特定のサービス アカウントの例外を使用して強制モードに移行します。

```json
{
    "version": 1,
    "rc4DefaultDisablementPhase": 2,
    "defaultDomainSupportedEncTypes": 24,
    "serviceAccountsRequiringRc4": ["svc-legacy-app", "svc-old-service"]
}
```

##### 適用された変更を確認する

評価サイクルが完了したら、同じ共有の `rc4-status.json` を確認して、変更が適用されていることを確認します。 各設定には、 `status` 値が表示されます。

| 地位 | Description |
| --- | --- |
| `InSync` | 必要な値と実際の値が一致します。 変更は必要ありません。 |
| `Applied` | 目的の値に一致するように変更が適用されました。 |
| `Drift` | 修復を実行する前に、必要な値と実際の値の間に違いがあります。 |

`rc4-status.json`のサービス アカウントの状態:

| 地位 | Description |
| --- | --- |
| `Updated` | RC4 フラグがアカウントに正常に追加されました。 |
| `AlreadyCompliant` | アカウントには RC4 フラグが既に設定されています。 変更は必要ありません。 |
| `NotFound` | アカウント `sAMAccountName` がディレクトリに見つかりませんでした。 |
| `Failed` | アカウントの更新中にエラーが発生しました。 詳細については、 `errors` 配列を参照してください。 |

状態ファイルに `configurationValid` が `false` されている場合、構成ファイルには構文または検証エラーがあります。 詳細については`errors`配列を確認し、`rc4-configuration.json`を修正します。

##### ステータスの例

同期中のすべての設定。修復は必要ありません。

```json
{
    "lastEvaluated": "2026-05-04T14:30:00Z",
    "lastRemediated": "2026-05-04T14:30:00Z",
    "configurationValid": true,
    "currentState": {
        "rc4DefaultDisablementPhase": {
            "desired": 1,
            "actual": 1,
            "status": "InSync"
        },
        "defaultDomainSupportedEncTypes": {
            "desired": 60,
            "actual": 60,
            "status": "InSync"
        },
        "serviceAccounts": []
    },
    "errors": []
}
```

サービス アカウントの更新に適用される変更:

```json
{
    "lastEvaluated": "2026-05-04T14:30:00Z",
    "lastRemediated": "2026-05-04T14:30:00Z",
    "configurationValid": true,
    "currentState": {
        "rc4DefaultDisablementPhase": {
            "desired": 2,
            "actual": 1,
            "status": "Applied"
        },
        "defaultDomainSupportedEncTypes": {
            "desired": 24,
            "actual": 60,
            "status": "Applied"
        },
        "serviceAccounts": [
            {
                "account": "svc-legacy-app",
                "status": "Updated",
                "previousEncTypes": 24,
                "currentEncTypes": 28
            },
            {
                "account": "svc-old-service",
                "status": "AlreadyCompliant",
                "previousEncTypes": 28,
                "currentEncTypes": 28
            }
        ]
    },
    "errors": []
}
```

サービス アカウント エラーによる部分的なエラー:

```json
{
    "lastEvaluated": "2026-05-04T14:30:00Z",
    "lastRemediated": "2026-05-04T14:30:00Z",
    "configurationValid": true,
    "currentState": {
        "rc4DefaultDisablementPhase": {
            "desired": 2,
            "actual": 2,
            "status": "InSync"
        },
        "defaultDomainSupportedEncTypes": {
            "desired": 24,
            "actual": 24,
            "status": "InSync"
        },
        "serviceAccounts": [
            {
                "account": "svc-legacy-app",
                "status": "Updated",
                "previousEncTypes": 24,
                "currentEncTypes": 28
            },
            {
                "account": "svc-missing",
                "status": "NotFound",
                "previousEncTypes": null,
                "currentEncTypes": null
            },
            {
                "account": "svc-broken",
                "status": "Failed",
                "previousEncTypes": null,
                "currentEncTypes": null
            }
        ]
    },
    "errors": [
        "Failed to update service account 'svc-broken': Access is denied."
    ]
}
```

Von Bedeutung

この構成は、マネージド ドメイン内の各ドメイン コントローラー (DC) に個別に適用されます。 レジストリ値が変更されると、KDC サービスの再起動が自動的に実行されます。 すべての DC で KDC の同時再起動 (Kerberos 認証の一時的な停止を引き起こす) を回避するには、DC 間でロールアウトする領域を確保します。 ドメインに複数のレプリカ セットがある場合は、各 DC に構成が適用されていることを確認します。 各 DC の `rc4-status.json` を監視して、すべてのコントローラーが新しい設定を正常に適用したことを確認します。

警告

CVE-2026-20833 のWindows更新プログラムでは、RC4 の強化が段階的に適用されます。 完全に適用される前に、修復を計画して完了します。

- **2026 年 1 月 13 日 - 初期デプロイ フェーズ:** 更新プログラムでは、監査シグナルと準備コントロールが導入されます。
- **2026 年 4 月 - 強制フェーズ (手動ロールバックが利用可能):** 既定の Kerberos KDC 動作は AES 優先に移行し、RC4 に依存するシナリオは、明示的に構成しない限り失敗を開始できます。
- **2026 年 7 月 - 実施フェーズ (最終):** 更新により、ロールバックのサポートが削除され、強制が有効な状態が維持されます。

RC4 が完全に無効になる前に、Domain Services チームは、RC4 の依存関係を特定して修復するのに役立つ、制御された事前依存関係テストを実行します。 スケジュールと準備方法の詳細については、 [RC4 非推奨の事前依存関係テスト](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/rc4-deprecation-advance-dependency-test)に関するページを参照してください。

### AADDS500:同期がしばらく行われていない

#### アラート メッセージ

*マネージド ドメインは、[日付] に最後に Microsoft Entra ID と同期されました。 ユーザーがマネージド ドメインにサインインできない場合や、グループ メンバーシップが Azure AD と同期していない可能性があります。*

#### 解決方法

[Domain Services の正常性をチェック](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/check-health)して、マネージド ドメインの構成の問題を示しているアラートがないか確認します。 ネットワーク構成に関する問題により、Microsoft Entra IDからの同期がブロックされる可能性があります。 構成の問題を示しているアラートを解決できる場合は、2 時間待機してから、同期が正常に完了したかどうかを再度確認してください。

一般的に、次のような理由により、マネージド ドメインで同期が停止します。

- 必要なネットワーク接続がブロックされている。 Azure仮想ネットワークで問題と必要な内容を確認する方法の詳細については、「[ネットワーク セキュリティ グループ](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/alert-nsg)と Domain Services の[network 要件](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/network-considerations)を参照してください。
- マネージド ドメインがデプロイされたときに、パスワード同期がセットアップされなかったか、正常に完了しなかった。 [クラウド専用ユーザー](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance#enable-user-accounts-for-azure-ad-ds)または[オンプレミスのハイブリッド ユーザー](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-password-hash-sync)のパスワード同期を設定できます。

### AADDS501:バックアップがしばらく行われていない

#### アラート メッセージ

"*マネージド ドメインが最後にバックアップされたのは [date] です。* "

#### 解決方法

[Domain Services の正常性をチェック](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/check-health)して、マネージド ドメインの構成の問題を示しているアラートがないか確認します。 ネットワーク構成に関する問題により、Azure プラットフォームが正常にバックアップを作成できない可能性があります。 構成の問題を示しているアラートを解決できる場合は、2 時間待機してから、同期が正常に完了したかどうかを再度確認してください。

### AADDS503: 無効化されたサブスクリプションが原因の中断

#### アラート メッセージ

*ドメインに関連付けられているAzure サブスクリプションがアクティブでないため、マネージド ドメインは一時停止されています。*

#### 解決方法

警告

マネージド ドメインは長時間中断されると、削除される危険性があります。 中断の理由をできるだけ早く解決してください。 詳細については、[Domain Services の中断状態の理解](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/suspension)に関する記事を参照してください。

Domain Services にはアクティブなサブスクリプションが必要です。 マネージド ドメインが関連付けられたAzure サブスクリプションがアクティブでない場合は、サブスクリプションを更新して再アクティブ化する必要があります。

1. [Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/subscription-disabled)を更新します。
2. サブスクリプションが更新されたら、Domain Services 通知を使用して、マネージド ドメインを再び有効にすることができます。

マネージド ドメインが再度有効にされると、マネージド ドメインの正常性が 2 時間以内に自動的に更新され、アラートが削除されます。

### AADDS504:無効な構成が原因の中断

#### アラート メッセージ

*"マネージド ドメインは、無効な構成により中断されます。 サービスは、マネージド ドメインのドメイン コントローラーの管理、パッチ適用、または更新を長い間行うことができませんでした。"*

#### 解決方法

警告

マネージド ドメインは長時間中断されると、削除される危険性があります。 中断の理由をできるだけ早く解決してください。 詳細については、[Domain Services の中断状態の理解](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/suspension)に関する記事を参照してください。

[Domain Services の正常性をチェック](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/check-health)して、マネージド ドメインの構成の問題を示しているアラートがないか確認します。 構成の問題を示しているアラートを解決できる場合は、2 時間待機してから、同期が完了したかどうかを再度確認してください。 準備ができたら、[Azure サポート要求](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-to-get-support)を開き、マネージド ドメインを再度有効にします。

### AADDS600: 30日間未解決のヘルスアラート

#### 警告メッセージ

*Microsoftは、未解決の正常性アラート [ID] のため、このマネージド ドメインのドメイン コントローラーを管理できません。 これにより、これらのドメイン コントローラーの重要なセキュリティ更新プログラムがブロックされます。 アラートの手順に従って問題を解決してください。 30 日以内にこの問題を解決できなかった場合、マネージド ドメインが中断されます。*

#### 解決方法

警告

マネージド ドメインは長時間中断されると、削除される危険性があります。 中断の理由をできるだけ早く解決してください。 詳細については、[Domain Services の中断状態の理解](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/suspension)に関する記事を参照してください。

[Domain Services の正常性をチェック](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/check-health)して、マネージド ドメインの構成の問題を示しているアラートがないか確認します。 構成の問題を示しているアラートを解決できる場合は、6 時間待機してから、アラートが解決したかどうかを再度確認してください。 [サポートが必要な場合は、Azure サポート要求](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-to-get-support)を開きます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/troubleshoot-domain-join"} -->
## Microsoft Entra Domain Services でのドメイン参加のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/troubleshoot-domain-join
- Service: entra-id / domain-services
- Article date: 2025-02-19
- Summary: VM にドメイン参加するか、アプリケーションを Microsoft Entra Domain Services に接続しようとしてマネージド ドメインに対して接続または認証ができないときの、一般的な問題のトラブルシューティングについて説明します。

仮想マシン (VM) に参加しようとするか、アプリケーションを Microsoft Entra Domain Services マネージド ドメインに接続しようとすると、これを行うことができないというエラーが表示されることがあります。 ドメイン参加の問題のトラブルシューティングを行うには、次のようにどこでイシューが発生しているかを確認してください。

- 認証プロンプトが表示されない場合は、VM またはアプリケーションが Domain Services マネージド ドメインに接続できません。
    - ドメイン参加に関する接続イシューのトラブルシューティングを行います。
- 認証中にエラーが発生した場合は、マネージド ドメインに接続することができます。
    - ドメイン参加中の資格情報関連のイシューのトラブルシューティングを行います。

### ドメイン参加の接続性のイシュー

VM がマネージド ドメインを見つけられない場合は、通常、ネットワーク接続または構成のイシューが発生しています。 以下のトラブルシューティングのステップを確認してイシューを見つけ、解決します。

1. マネージド ドメインと同じ、またはピアリングされた仮想ネットワークに VM が接続されていることを確認します。 それ以外の場合、VM は参加するためのドメインを見つけて接続することができません。
    - VM が同じ仮想ネットワークに接続されていない場合は、トラフィックが正常に流れるように、仮想ネットワーク ピアリングまたは VPN 接続が *[Active]\(有効\)* または *[Connected]\(接続済み\)* になっていることを確認します。
2. マネージド ドメインのドメイン名を使用して、ドメインの ping を試行してください (例: `ping aaddscontoso.com`)。
    - ping 応答が失敗した場合は、マネージド ドメインの、ポータルの概要ページに表示されるドメインの IP アドレスに対して ping を実行します (`ping 10.0.0.4` など)。
    - ドメインではなく IP アドレスを ping できた場合、DNS が正しく構成されていない可能性があります。 [マネージド ドメインの DNS サーバーを仮想ネットワークの DNS サーバーとして構成した](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance#update-dns-settings-for-the-azure-virtual-network)ことを確認してください。
3. 仮想マシンの DNS リゾルバー キャッシュのフラッシュを試行します (`ipconfig /flushdns` など)。

#### ネットワーク セキュリティ グループ (NSG) の構成

マネージド ドメインを作成すると、ドメイン操作を成功させるための適切な規則を含むネットワーク セキュリティ グループも作成されます。 追加のネットワーク セキュリティ グループの規則を編集または作成した場合、Domain Services が接続と認証のサービスを提供するために必要なポートが誤ってブロックされる可能性があります。 これらのネットワーク セキュリティ グループの規則により、パスワード同期が完了しない、ユーザーがサインインできないといったイシューや、ドメイン参加のイシューが発生する可能性があります。

接続のイシューが解決しない場合は、次のトラブルシューティングのステップを確認してください。

1. Azure portal でマネージド ドメインの正常性状態を確認します。 *AADDS001* のアラートが発生している場合は、ネットワーク セキュリティ グループの規則によってアクセスがブロックされています。
2. [必要なポートとネットワーク セキュリティ グループの規則](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/network-considerations#network-security-groups-and-required-ports)を確認します。 接続している VM または仮想ネットワークに適用されているネットワーク セキュリティ グループの規則が、これらのネットワーク ポートをブロックしていないことを確認してください。
3. ネットワーク セキュリティ グループの構成のイシューが解決されると、正常性のページの *AADDS001* アラートが 2 時間ほどで消えます。 ネットワーク接続を利用できるようになったので、もう一度 VM にドメイン参加してみてください。

### ドメイン参加中の資格情報関連のイシュー

マネージド ドメインに参加するための資格情報を要求するダイアログ ボックスが表示された場合、VM は Azure 仮想ネットワークを使用してドメインに接続できます。 ドメイン参加プロセスは、ドメインへの認証または資格情報を使用したドメイン参加プロセスを完了するための承認に失敗します。

資格情報に関連するイシューのトラブルシューティングを行うには、次のトラブルシューティングのステップを確認してください。

1. UPN 形式を使用して資格情報を指定します (例: `dee@contoso.onmicrosoft.com`)。 この UPN が Microsoft Entra ID で正しく構成されていることを確認してください。
    - 複数のユーザーがテナントで同じ UPN プレフィックスを使用していたり、使用する UPN プレフィックスが最大文字数を超えている場合は、アカウントの *SAMAccountName* が自動生成されることがあります。 そのため、お使いのアカウントの *SAMAccountName* 形式が想定した形式やオンプレミス ドメインで使用する形式と異なる可能性があります。
2. マネージド ドメインの一部であるユーザー アカウントの資格情報を使用して、マネージド ドメインへの VM の参加を試みます。
3. [パスワード同期が有効になっている](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance#enable-user-accounts-for-azure-ad-ds)ことを確認し、最初のパスワード同期が完了するまで十分な時間待機します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/troubleshoot-sign-in"} -->
## Microsoft Entra Domain Services でのサインインに関する問題のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/troubleshoot-sign-in
- Service: entra-id / domain-services
- Article date: 2025-02-19
- Summary: Microsoft Entra Domain Services でのユーザー サインインに関する一般的な問題とエラーのトラブルシューティング方法について説明します。

Microsoft Entra Domain Services マネージド ドメインにサインインできないユーザー アカウントの最も一般的な理由には、次のシナリオがあります。

- アカウントはまだ Domain Services に同期されていません。
- Domain Services には、アカウントのサインインを許可するパスワード ハッシュがありません。
- アカウントがロックアウトされています。

ヒント

Domain Services は、Microsoft Entra テナントの外部にあるアカウントの資格情報で同期できません。 外部ユーザーは Domain Services マネージド ドメインにサインインできません。

### アカウントがまだ Domain Services に同期されていない

ディレクトリのサイズによっては、ユーザー アカウントと資格情報ハッシュがマネージド ドメインで使用できるようになるまで時間がかかる場合があります。 大規模なディレクトリの場合、Microsoft Entra ID からのこの最初の一方向同期には数時間、最大で 1 日または 2 日かかる場合があります。 認証を再試行する前に、十分に長く待っていることを確認します。

Microsoft Entra Connect ユーザーがオンプレミスのディレクトリ データを Microsoft Entra ID に同期するハイブリッド環境の場合は、最新バージョンの Microsoft Entra Connect を実行し、 [Domain Services を有効にした後で完全同期を実行するように Microsoft Entra Connect を構成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-password-hash-sync)していることを確認してください。 Domain Services を無効にしてから再度有効にする場合は、次の手順をもう一度実行する必要があります。

Microsoft Entra Connect 経由で同期していないアカウントで問題が引き続き発生する場合は、Azure AD 同期サービスを再起動します。 Microsoft Entra Connect がインストールされているコンピューターからコマンド プロンプト ウィンドウを開き、次のコマンドを実行します。

```console
net stop 'Microsoft Azure AD Sync'
net start 'Microsoft Azure AD Sync'
```

### Domain Services にパスワード ハッシュがありません

Microsoft Entra ID は、テナントの Domain Services を有効にするまで、NTLM または Kerberos 認証に必要な形式でパスワード ハッシュを生成または格納しません。 また、セキュリティ上の理由から、クリアテキスト形式のパスワード資格情報が Microsoft Entra ID に保存されることもありません。 そのため、Microsoft Entra ID では、ユーザーの既存の資格情報に基づいて、これらの NTLM やKerberos のパスワード ハッシュを自動的に生成することはできません。

#### オンプレミス同期を使用するハイブリッド環境

Microsoft Entra Connect を使用してオンプレミスの AD DS 環境から同期するハイブリッド環境では、必要な NTLM または Kerberos パスワード ハッシュをローカルで生成し、Microsoft Entra ID に同期できます。 マネージド ドメインを作成したら、 [Microsoft Entra Domain Services へのパスワード ハッシュ同期を有効にします](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-password-hash-sync)。 このパスワード ハッシュ同期手順を完了しないと、マネージド ドメインを使用してアカウントにサインインすることはできません。 Domain Services を無効にしてから再度有効にする場合は、これらの手順をもう一度実行する必要があります。

詳細については、「 [Domain Services でのパスワード ハッシュ同期のしくみ」を](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/how-to-connect-password-hash-synchronization#password-hash-sync-process-for-azure-ad-domain-services)参照してください。

#### オンプレミス同期のないクラウドのみの環境

オンプレミス同期がないマネージド ドメイン(Microsoft Entra ID のアカウントのみ)も、必要な NTLM または Kerberos パスワード ハッシュを生成する必要があります。 クラウド専用アカウントがサインインできない場合、Domain Services を有効にした後、アカウントのパスワード変更プロセスが正常に完了しましたか?

- **いいえ。パスワードは変更されていません。**
    - [アカウントのパスワードを変更](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance#enable-user-accounts-for-azure-ad-ds) して必要なパスワード ハッシュを生成し、もう一度サインインする前に 15 分待ちます。
    - Domain Services を無効にしてから再度有効にした場合は、各アカウントの手順をもう一度実行してパスワードを変更し、必要なパスワード ハッシュを生成する必要があります。
- **はい、パスワードが変更されました。**
    - 形式ではなく、`driley@aaddscontoso.com` などの  形式を使用してサインインしてみてください。
    - UPN プレフィックスが過度に長い、またはマネージド ドメイン上の別のユーザーと同じユーザーに対して *、SAMAccountName* が自動的に生成される場合があります。 *UPN* 形式は、Microsoft Entra テナント内で一意であることが保証されます。

### アカウントがロックアウトされている

サインイン試行の失敗に対する定義済みのしきい値が満たされると、マネージド ドメイン内のユーザー アカウントがロックアウトされます。 このアカウント ロックアウトの動作は、自動デジタル攻撃を示す可能性のあるブルート フォース サインイン試行の繰り返しからユーザーを保護するように設計されています。

既定では、2 分間に無効なパスワード試行が 5 回行われると、アカウントは 30 分間ロックアウトされます。

アカウント ロックアウトの問題を解決する方法の詳細については、「 [Domain Services でのアカウント ロックアウトの問題のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/troubleshoot-account-lockout)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/tshoot-ldaps"} -->
## Microsoft Entra Domain Services での Secure LDAP のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tshoot-ldaps
- Service: entra-id / domain-services
- Article date: 2025-02-19
- Summary: Microsoft Entra Domain Services のマネージド ドメインに対する Secure LDAP (LDAPS) をトラブルシューティングする方法について説明します

ライトウェイト ディレクトリ アクセス プロトコル (LDAP) を使用して Microsoft Entra Domain Services と通信するアプリケーションおよびサービスは、[Secure LDAP を使用するように構成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-ldaps)できます。 Secure LDAP が正しく機能するためには、適切な証明書と必要なネットワーク ポートが開いている必要があります。

この記事は、Microsoft Entra Domain Services での Secure LDAP アクセスに関する問題のトラブルシューティングに役立ちます。

### 一般的な接続に関する問題

Secure LDAP を使用した Microsoft Entra Domain Services マネージド ドメインへの接続で問題がある場合は、次のトラブルシューティングの手順を確認してください。 トラブルシューティングの各手順を実行した後、マネージド ドメインに再度接続を試みてください。

- Secure LDAP 証明書の発行者チェーンは、クライアントで信頼されている必要があります。 信頼を確立するために、クライアント上の信頼されたルート証明書ストアにルート証明機関 (CA) を追加できます。
    - [クライアント コンピューターに証明書をエクスポートして適用していること](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-ldaps#export-a-certificate-for-client-computers)を確認します。
- マネージド ドメインの Secure LDAP 証明書の *Subject* (サブジェクト) 属性または *Subject Alternative Names*(サブジェクトの別名) 属性に、DNS 名が含まれていることを確認します。
    - [Secure LDAP 証明書の要件](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-ldaps#create-a-certificate-for-secure-ldap)を確認し、必要に応じて代替証明書を作成します。
- LDAP クライアント (*ldp.exe*など) が、IP アドレスではなく、DNS 名を使用して Secure LDAP エンドポイントに接続していることを確認します。
    - マネージド ドメインに適用される証明書には、サービスの IP アドレスは含まれず、DNS 名のみが含まれます。
- LDAP クライアントの接続先の DNS 名を確認します。 この DNS 名は、マネージド ドメイン上の Secure LDAP に対するパブリック IP アドレスに解決される必要があります。
    - DNS 名が内部 IP アドレスに解決される場合は、外部 IP アドレスに解決されるように DNS レコードを更新します。
- 外部接続の場合、ネットワーク セキュリティ グループに、インターネットから TCP ポート 636 へのトラフィックを許可する規則が含まれている必要があります。
    - 仮想ネットワークに直接接続するリソースから Secure LDAP を使用して マネージド ドメインには接続できるが、外部接続ができない場合は、[Secure LDAP トラフィックを許可するネットワーク セキュリティ グループ規則を作成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-ldaps#lock-down-secure-ldap-access-over-the-internet)してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/tutorial-configure-ldaps"} -->
## チュートリアル - Microsoft Entra Domain Services 用に LDAPS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-ldaps
- Service: entra-id / domain-services
- Article date: 2025-02-19
- Summary: このチュートリアルでは、Microsoft Entra Domain Services のマネージド ドメイン用に、セキュリティで保護されたライトウェイト ディレクトリ アクセス プロトコル (LDAPS) を構成する方法について説明します。

Microsoft Entra Domain Services のマネージド ドメインとの通信には、ライトウェイト ディレクトリ アクセス プロトコル (LDAP) が使用されます。 既定では、LDAP トラフィックが暗号化されておらず、そのことが多くの環境にとってセキュリティ上の懸念事項となっています。

Microsoft Entra Domain Services を使用すれば、セキュリティで保護されたライトウェイト ディレクトリ アクセス プロトコル (LDAPS) を使用するようにマネージド ドメインを構成できます。 Secure LDAP を使用した場合、トラフィックが暗号化されます。 Secure LDAP は、LDAP over Secure Sockets Layer (SSL) や LDAP over Transport Layer Security (TLS) とも呼ばれます。

このチュートリアルでは、ドメイン サービス マネージド ドメイン用に LDAPS を構成する方法について説明します。

このチュートリアルでは、次の作業を行う方法について説明します。

- Microsoft Entra Domain Services で使用するデジタル証明書を作成する
- Microsoft Entra Domain Services で Secure LDAP を有効にする
- パブリック インターネット経由で使用するための Secure LDAP を構成する
- マネージド ドメイン用に Secure LDAP のバインドとテストを行う

Azure サブスクリプションをお持ちでない場合は、始める前に[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### 前提条件

このチュートリアルを完了するには、以下のリソースと特権が必要です。

- 有効な Azure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付けます](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)。
- Microsoft Entra テナントで有効にされていて、構成されている Microsoft Entra Domain Services マネージド ドメイン。
    - 必要に応じて、[Microsoft Entra Domain Services – マネージド ドメインを作成して構成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)。
- ご利用のコンピューターにインストールされた *LDP.exe*ツール。
    - 必要に応じて、[Active Directory Domain Services と LDAP](https://learn.microsoft.com/ja-jp/windows-server/remote/remote-server-administration-tools) 用に*リモート サーバー管理ツール (RSAT)* をインストールしてください。
- 安全な LDAP を有効にするには、[アプリケーション管理者](https://learn.microsoft.com/ja-jp/azure/active-directory/roles/permissions-reference#application-administrator)および[グループ管理者](https://learn.microsoft.com/ja-jp/azure/active-directory/roles/permissions-reference#groups-administrator)の Microsoft Entra のロールがテナント内に必要となります。

### Microsoft Entra 管理センターにサインインします。

このチュートリアルでは、Microsoft Entra 管理センターを使用して、マネージド ドメイン用に Secure LDAP を構成します。 作業開始するには、まず [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。

### Secure LDAP 用の証明書を作成する

Secure LDAP を使用するには、デジタル証明書を使用して通信を暗号化します。 このデジタル証明書はマネージド ドメインに適用されます。*LDP.exe* などのツールがデータを照会する際には、このデジタル証明書によって、暗号化された安全な通信を使用することができます。 マネージド ドメインへの Secure LDAP アクセスに使用する証明書は 2 とおりの方法で作成できます。

- 公的証明機関 (CA) またはエンタープライズ CA からの証明書。
    - 公的 CA から証明書を取得している組織は、その公的 CA から Secure LDAP 証明書を取得します。 組織内でエンタープライズ CA を使用している場合は、そのエンタープライズ CA から Secure LDAP 証明書を取得します。
    - 公的 CA が正しく機能するのは、マネージド ドメインでカスタム DNS 名を使用している場合だけです。 マネージド ドメインの DNS ドメイン名の末尾が *.onmicrosoft.com* である場合、この既定のドメインに対する接続をセキュリティで保護するためのデジタル証明書は作成できません。 *.onmicrosoft.com* ドメインを所有するのは Microsoft であるため、公的 CA からは証明書が発行されません。 このシナリオでは、自己署名証明書を作成し、その証明書を使用して Secure LDAP を構成します。
- 自分で作成する自己署名証明書。
    - この方法はテスト目的に適しています。このチュートリアルでも、この方法を紹介しています。

要求または作成する証明書は、次の要件を満たしている必要があります。 無効な証明書で Secure LDAP を有効にした場合、マネージド ドメインで問題が発生します。

- **信頼された発行者** - 証明書は、セキュリティで保護された LDAP を使用してマネージド ドメインに接続するコンピューターによって信頼された機関から発行される必要があります。 この機関は、これらのコンピューターによって信頼された公的 CA またはエンタープライズ CA が該当します。
- **有効期間** - 証明書は少なくとも、今後 3 ～ 6 か月間有効である必要があります。 証明書の有効期限が切れると、マネージド ドメインへのセキュリティで保護された LDAP のアクセスが切断されます。
- **サブジェクト名** - 証明書のサブジェクト名は、マネージド ドメインである必要があります。 たとえば、ドメインが *aaddscontoso.com* という名前である場合、証明書のサブジェクト名は \* *.aaddscontoso.com*である必要があります。
    - Secure LDAP が Domain Services で正常に動作するように、証明書の DNS 名またはサブジェクト代替名がワイルドカード証明書であることが必要です。 ドメイン コントローラーにはランダムな名前が使用されます。サービスの可用性を確保するために、ドメイン コントローラーは追加したり削除したりすることができます。
- **キー使用法** - 証明書は、"*デジタル署名*" および "*キーの暗号化*" に対して構成される必要があります。
- **証明書の目的** - 証明書は、TLS サーバー認証に対して有効である必要があります。

OpenSSL、Keytool、MakeCert、[New-SelfSignedCertificate](https://learn.microsoft.com/ja-jp/powershell/module/pki/new-selfsignedcertificate) コマンドレットなど、自己署名証明書の作成に使用できるツールがいくつかあります。

このチュートリアルでは、[New-SelfSignedCertificate](https://learn.microsoft.com/ja-jp/powershell/module/pki/new-selfsignedcertificate) コマンドレットを使用して Secure LDAP 用の自己署名証明書を作成してみましょう。

**管理者**として PowerShell ウィンドウを開き、次のコマンドを実行します。 *$dnsName* 変数は、実際のマネージド ドメインで使用されている DNS 名に置き換えてください (例: *aaddscontoso.com*)。

```powershell
# Define your own DNS name used by your managed domain
$dnsName="aaddscontoso.com"

# Get the current date to set a one-year expiration
$lifetime=Get-Date

# Create a self-signed certificate for use with Azure AD DS
New-SelfSignedCertificate -Subject *.$dnsName `
  -NotAfter $lifetime.AddDays(365) -KeyUsage DigitalSignature, KeyEncipherment `
  -Type SSLServerAuthentication -DnsName *.$dnsName, $dnsName
```

次の出力例は、証明書が正しく生成されてローカル証明書ストア (*LocalMachine\MY*) に格納されたことを示しています。

```output
PS C:\WINDOWS\system32> New-SelfSignedCertificate -Subject *.$dnsName `
>>   -NotAfter $lifetime.AddDays(365) -KeyUsage DigitalSignature, KeyEncipherment `
>>   -Type SSLServerAuthentication -DnsName *.$dnsName, $dnsName.com

   PSParentPath: Microsoft.PowerShell.Security\Certificate::LocalMachine\MY

Thumbprint                                Subject
----------                                -------
959BD1531A1E674EB09E13BD8534B2C76A45B3E6  CN=aaddscontoso.com
```

### 必要な証明書を把握してエクスポートする

Secure LDAP を使用するために、ネットワーク トラフィックは、公開鍵基盤 (PKI) を使用して暗号化されます。

- マネージド ドメインには**秘密**キーが適用されます。
    - Secure LDAP トラフィックの "*暗号化を解除する*" には、この秘密キーが使用されます。 秘密キーの適用先はマネージド ドメインに限定する必要があります。クライアント コンピューターに広く秘密キーを配布しないでください。
    - 秘密キーを含んだ証明書では、 *.PFX* ファイル形式が使用されます。
    - 証明書をエクスポートするときは、*TripleDES-SHA1* 暗号化アルゴリズムを指定する必要があります。 これは .pfx ファイルにのみ適用され、証明書自体で使用されるアルゴリズムには影響しません。 *TripleDES-SHA1* オプションは、Windows Server 2016 以降でのみ使用できます。
- クライアント コンピューターには**公開**キーが適用されます。
    - この公開キーは、Secure LDAP トラフィックの "*暗号化*" に使用されます。 公開キーは、クライアント コンピューターに配布することができます。
    - 秘密キーを含まない証明書には、 *.CER* ファイル形式が使用されます。

これら 2 つのキー ("*秘密*" キーと "*公開*" キー) によって、適切なコンピューター間に相互通信が確実に限定されます。 公的 CA またはエンタープライズ CA を使用した場合は、秘密キーを含んだ証明書が発行され、その秘密キーをマネージド ドメインに適用することができます。 クライアント コンピューターはあらかじめ公開キーを把握し、信頼しておく必要があります。

このチュートリアルでは、秘密キーを含む自己署名証明書を作成したので、適切な秘密および公開コンポーネントをエクスポートする必要があります。

#### Microsoft Entra Domain Services の証明書をエクスポートする

前の手順で作成したデジタル証明書をマネージド ドメインに対して使用するには、秘密キーを含んだ *.PFX* 証明書ファイルに証明書をエクスポートする必要があります。

1. *[ファイル名を指定して実行]* ダイアログを開くために、**Windows** + **R** キーを選択します。
2. **[ファイル名を指定して実行]** ダイアログに「*mmc*」と入力し、 **[OK]** を選択して Microsoft 管理コンソール (MMC) を開きます。
3. 次に、 **[ユーザー アカウント制御]** プロンプトで **[はい]** を選択し、管理者として MMC を起動します。
4. **[ファイル]** メニューで **[スナップインの追加と削除]** を選択します。
5. **証明書スナップイン** ウィザードで **[コンピューター アカウント]** を選択し、 **[次へ]** を選択します。
6. **[コンピューターの選択]** ページで **[ローカル コンピューター (このコンソールを実行しているコンピューター)]** を選択し、 **[完了]** 選択します。
7. **[スナップインの追加と削除]** ダイアログで **[OK]** を選択し、証明書スナップインを MMC に追加します。
8. MMC のウィンドウで **[コンソール ルート]** を展開します。 **[証明書 (ローカル コンピューター)]** を選択し、 **[個人]** ノード、 **[証明書]** ノードの順に展開します。

    [Image: Microsoft 管理コンソールで個人証明書ストアを開く]
9. 前の手順で作成した自己署名証明書が表示されます (例: *aaddscontoso.com*)。 この証明書を右クリックし、**[すべてのタスク] &gt; [エクスポート]** の順に選択します。

    [Image: Microsoft 管理コンソールで証明書をエクスポートする]
10. **証明書のエクスポート ウィザード**で **[次へ]** を選択します。
11. 証明書の秘密キーをエクスポートする必要があります。 エクスポートした証明書に秘密キーが含まれていない場合、マネージド ドメインに対して Secure LDAP を有効にする操作は失敗します。

    **[秘密キーのエクスポート]** ページで **[はい、秘密キーをエクスポートします]** を選択し、 **[次へ]** を選択します。
12. マネージド ドメインでサポートされるのは、秘密キーを含んだ *.PFX* 証明書ファイル形式のみです。 秘密キーを含まない *.CER* 証明書ファイル形式として証明書をエクスポートすることは避けてください。

    **[エクスポート ファイルの形式]** ページで、エクスポートする証明書のファイル形式として **[Personal Information Exchange - PKCS #12 (.PFX)]** を選択します。 *[証明のパスにある証明書を可能であればすべて含む]* チェック ボックスをオンにします。

    [Image: PKCS 12 (.PFX) ファイル形式で証明書をエクスポートするオプションを選択する]
13. この証明書は、データの暗号化を解除する目的で使用されるため、慎重にアクセスを制御する必要があります。 パスワードを使用して証明書の使用を保護することができます。 正しいパスワードがなければ、サービスに証明書を適用することはできません。

    **.PFX** 証明書ファイルを保護するには、 **[セキュリティ]** ページで *[パスワード]* のオプションを選択します。 暗号化アルゴリズムは、*TripleDES-SHA1* である必要があります。 パスワードの入力と確認入力を行って、 **[次へ]** を選択します。 このパスワードは、次のセクションでマネージド ドメインに対して Secure LDAP を有効にする際に使用します。

    [PowerShell の export-pfxcertificate コマンドレット](https://learn.microsoft.com/ja-jp/powershell/module/pki/export-pfxcertificate)を使用してエクスポートする場合は、TripleDES\_SHA1 を使用して *-CryptoAlgorithmOption* フラグを渡す必要があります。

    [Image: パスワードを暗号化する方法を示すスクリーンショット]
14. **[エクスポートするファイル]** ページで、`C:\Users\<account-name>\azure-ad-ds.pfx` など、ファイル名と証明書のエクスポート先を指定します。 *.PFX* ファイルのパスワードと場所をメモしておきます。この情報は次の手順で必要になります。
15. 確認ページで **[完了]** を選択すると、証明書が *.PFX* 証明書ファイルにエクスポートされます。 証明書が正しくエクスポートされると確認ダイアログが表示されます。
16. MMC は、次のセクションで使用するため、開いたままにしておきます。

#### クライアント コンピューター用の証明書をエクスポートする

LDAPS を使用してマネージド ドメインに正常に接続できるようにするには、クライアント コンピューターで Secure LDAP 証明書の発行者を信頼する必要があります。 Domain Services によって暗号化解除されるデータをクライアント コンピューターが正しく暗号化するには証明書が必要となります。 公的 CA を使用した場合、それらの証明書の発行者をコンピューターが自動的に信頼し、また、対応する証明書がコンピューターに存在するはずです。

このチュートリアルでは自己署名証明書を使用しており、先行する手順では、秘密キーを含んだ証明書を生成しました。 自己署名証明書をエクスポートし、クライアント コンピューター上の信頼された証明書ストアにインストールしましょう。

1. MMC の [証明書 (ローカル コンピューター)] &gt; [個人] &gt; [証明書] ストアに戻ります。 前の手順で作成した自己署名証明書が表示されます (例: *aaddscontoso.com*)。 この証明書を右クリックし、**[すべてのタスク] &gt; [エクスポート]** の順に選択します。
2. **証明書のエクスポート ウィザード**で **[次へ]** を選択します。
3. クライアントに秘密キーは不要であるため、 **[秘密キーのエクスポート]** ページで **[いいえ、秘密キーをエクスポートしません]** を選択し、 **[次へ]** を選択します。
4. **[エクスポート ファイルの形式]** ページで、エクスポートした証明書のファイル形式として **[Base 64 encoded X.509 (.CER)]** を選択します。

    [Image: Base 64 encoded X.509 (.CER) ファイル形式で証明書をエクスポートするオプションを選択する]
5. **[エクスポートするファイル]** ページで、`C:\Users\<account-name>\azure-ad-ds-client.cer` など、ファイル名と証明書のエクスポート先を指定します。
6. 確認ページで **[完了]** を選択すると、証明書が *.CER* 証明書ファイルにエクスポートされます。 証明書が正しくエクスポートされると確認ダイアログが表示されます。

これで、マネージド ドメインに対する Secure LDAP 接続を信頼する必要があるクライアント コンピューターに対し、 *.CER* 証明書ファイルを配布する準備ができました。 ローカル コンピューターに証明書をインストールしてみましょう。

1. エクスプローラーを開いて、*.CER* 証明書ファイルの保存先を参照します (例: `C:\Users\<account-name>\azure-ad-ds-client.cer`)。
2. *.CER* 証明書ファイルを右クリックし、 **[証明書のインストール]** を選択します。
3. **証明書のインポート ウィザード**で、 *[ローカル コンピューター]* を証明書の保存先として選択し、 **[次へ]** を選択します。

    [Image: ローカル コンピューター ストアに証明書をインポートするオプションを選択する]
4. 確認を求められたら、 **[はい]** を選択して、コンピューターに変更を許可します。
5. **[証明書の種類に基づいて、自動的に証明書ストアを選択する]** を選択し、 **[次へ]** を選択します。
6. 確認ページで **[完了]** を選択すると、 *.CER* 証明書がインポートされます。 証明書が正しくインポートされると確認ダイアログが表示されます。

### Microsoft Entra Domain Services で Secure LDAP を有効にする

秘密キーを含んだデジタル証明書の作成とエクスポートが完了し、接続を信頼するようにクライアント コンピューターを設定したら、マネージド ドメインに対して Secure LDAP を有効にします。 マネージド ドメインに対して Secure LDAP を有効にするには、次の構成手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)の *[リソースの検索]* ボックスに「**domain services**」と入力します。 検索結果から **[Microsoft Entra Domain Services]** を選択します。
2. 目的のマネージド ドメインを選択します (例: *aaddscontoso.com* )。
3. [Microsoft Entra Domain Services] ウィンドウの左側で、**[Secure LDAP]** を選択します。
4. 既定では、セキュリティで保護された LDAP を利用してマネージド ドメインにアクセスする機能は無効になっています。 **[Secure LDAP]** を **[有効にする]** に切り替えます。
5. 既定では、インターネット経由でのマネージド ドメインへの Secure LDAP アクセスが無効になっています。 パブリック Secure LDAP アクセスを有効にすると、インターネットを介したパスワードのブルート フォース攻撃を受けやすくなります。 次の手順では、ネットワーク セキュリティ グループを構成して、必要な送信元 IP アドレス範囲のみにアクセスをロック ダウンします。

    **[インターネット経由での Secure LDAP アクセスを許可]** を **[有効にする]** に切り替えます。
6. **[Secure LDAP 証明書が入った .PFX ファイル]** の横にあるフォルダー アイコンを選択します。 *.PFX* ファイルのパスを参照し、前の手順で作成した、秘密キーを含んだ証明書を選択します。

    重要

    証明書の要件に関するセクションで既に述べたように、既定の *.onmicrosoft.com* ドメインでは、公的 CA からの証明書を使用できません。 *.onmicrosoft.com* ドメインを所有するのは Microsoft であるため、公的 CA からは証明書が発行されません。

    証明書が適切な形式になっていることを確認してください。 そのようになっていないと、Secure LDAP を有効にしたときに、Azure プラットフォームから証明書の検証エラーが生成されます。
7. 前の手順で証明書を **.PFX** ファイルにエクスポートするときに設定したパスワード (*PFX ファイルの暗号化を解除するためのパスワード*) を入力します。
8. **[保存]** を選択すると、Secure LDAP が有効になります。

    [Image: Microsoft Entra 管理センターでマネージド ドメインに対して Secure LDAP を有効にする]

マネージド ドメインに対して Secure LDAP が構成されていることを示す通知が表示されます。 この処理が完了するまで、マネージド ドメインに関する他の設定を変更することはできません。

マネージド ドメインに対して Secure LDAP を有効にするには、数分かかります。 指定した Secure LDAP 証明書が所定の基準と合致しない場合、マネージド ドメインに対して Secure LDAP を有効にする操作は失敗します。

エラーの一般的な原因としては、ドメイン名が正しくない、証明書の暗号化アルゴリズムが *TripleDES-SHA1* ではない、または証明書が間もなく期限切れになるか、既に期限切れになっている、などがあります。 有効なパラメーターで証明書を再作成し、その更新済みの証明書を使用して、Secure LDAP を有効にしてください。

### 期限切れが近づいている証明書を更新する

1. 交換用の Secure LDAP 証明書を作成するには、「Secure LDAP 用の証明書を作成する」の手順を実行してください。
2. 交換用の証明書を Domain Services に適用するには、Microsoft Entra 管理センターの **[Microsoft Entra Domain Services]** の左側のメニューで、**[Secure LDAP]** を選択し、**[証明書を変更]** を選択します。
3. Secure LDAP を使用して接続するすべてのクライアントに証明書を配布します。

### インターネット経由での Secure LDAP アクセスをロック ダウンする

マネージド ドメインに対するインターネット経由での Secure LDAP アクセスを有効にすると、セキュリティ上の脅威が形成されます。 マネージド ドメインには、インターネットから TCP ポート 636 で到達できます。 マネージド ドメインへのアクセスは、ご利用の環境で使用されている特定の既知の IP アドレスに制限することをお勧めします。 Secure LDAP へのアクセスは、Azure ネットワーク セキュリティ グループの規則を使用して制限できます。

指定した IP アドレスのセットから TCP ポート 636 経由で入ってくる Secure LDAP アクセスを許可する規則を作成しましょう。 それよりも優先度が低く設定された既定の *DenyAll* 規則が、インターネットから入ってくる他のすべてのトラフィックに適用されるので、Secure LDAP を使用してマネージド ドメインに到達できるのは、指定したアドレスのみとなります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、*[リソース グループ]* を検索して選択します。
2. リソース グループ (*myResourceGroup* など) を選択し、ネットワーク セキュリティ グループ (*aaads-nsg* など) を選択します。
3. インバウンドとアウトバウンドの既存のセキュリティ規則が一覧表示されます。 ネットワーク セキュリティ グループ ウィンドウの左側で、**[設定] &gt; [受信セキュリティ規則]** の順に選択します。
4. **[追加]** を選択し、*TCP* ポート *636* を許可する規則を作成します。 セキュリティを強化するためには、"*IP アドレス*" をソースに選択したうえで、貴社の有効な IP アドレスまたはその範囲を指定します。

    | 設定 | [値] |
    | --- | --- |
    | Source | IP アドレス |
    | ソース IP アドレス/CIDR 範囲 | 実際の環境の有効な IP アドレスまたはその範囲 |
    | 送信ポート範囲 | \* |
    | 宛先 | 任意 |
    | 宛先ポート範囲 | 636 |
    | プロトコル | TCP |
    | アクション | 許可する |
    | 優先順位 | 401 |
    | 名前 | AllowLDAPS |
5. 準備ができたら、 **[追加]** を選択して規則を保存し、適用します。

    [Image: インターネット経由で Secure LDAPS アクセスするネットワーク セキュリティ グループの規則を作成する]

### 外部アクセスのための DNS ゾーンを構成する

インターネット経由での Secure LDAP アクセスを有効にしたら、クライアント コンピューターがこのマネージド ドメインを見つけられるよう DNS ゾーンを更新します。 マネージド ドメインの *[プロパティ]* タブに **[Secure LDAP 外部 IP アドレス]** が一覧表示されます。

[Image: マネージド ドメインに使用される Secure LDAP の外部 IP アドレスを Microsoft Entra 管理センターで確認する]

外部 DNS プロバイダーを構成して、この外部 IP アドレスに解決するホスト レコードを作成します (例: *ldaps*)。 まず、お使いのマシンのローカルでテストするために、Windows の hosts ファイルにエントリを作成します。 ローカル コンピューター上で hosts ファイルを正しく編集するには、管理者として [メモ帳] を開き、ファイル  を開きます。`C:\Windows\System32\drivers\etc\hosts`

外部 DNS プロバイダーまたはローカルの hosts ファイルで、次のように DNS エントリを指定すると、`ldaps.aaddscontoso.com` 宛てのトラフィックが `168.62.205.103` という外部 IP アドレスに解決されます。

```
168.62.205.103    ldaps.aaddscontoso.com
```

### マネージド ドメインに対するクエリをテストする

マネージド ドメインに接続してバインドし、LDAP を検索するためには、*LDP.exe* ツールを使用します。 このツールは、リモート サーバー管理ツール (RSAT) パッケージに付属しています。 詳細については、[リモート サーバー管理ツールのインストール](https://learn.microsoft.com/ja-jp/windows-server/remote/remote-server-administration-tools)に関するページを参照してください。

1. *LDP.exe* を開いてマネージド ドメインに接続します。 **[接続]** を選択した後、 **[接続]** を選択します。
2. 前の手順で作成したマネージド ドメインの Secure LDAP DNS ドメイン名を入力します (例: *ldaps.aaddscontoso.com*)。 Secure LDAP を使用するには、 **[ポート]** を *636* に設定し、 **[SSL]** のチェック ボックスをオンにします。
3. **[OK]** を選択して、マネージド ドメインに接続します。

次に、マネージド ドメインにバインドします。 マネージド ドメインで NTLM パスワードハッシュ同期を無効にしている場合、ユーザー (およびサービス アカウント) は LDAP 簡易バインドを実行できません。 NTLM パスワード ハッシュ同期の無効化の詳細については、[マネージド ドメインをセキュリティで保護する方法](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/secure-your-domain)に関するページを参照してください。

1. **[接続]** メニュー オプションを選択し、 **[バインド]** を選択します。
2. マネージド ドメインに属しているユーザー アカウントの資格情報を指定します。 ユーザー アカウントのパスワードを入力し、ご利用のドメインを入力します (例: *aaddscontoso.com*)。
3. **[バインドの種類]** には、 *[資格情報によるバインド]* のオプションを選択します。
4. **[OK]** を選択してマネージド ドメインにバインドします。

マネージド ドメインに格納されているオブジェクトを確認するには、次の手順に従います。

1. **[表示]** メニュー オプションを選択し、 **[ツリー]** を選択します。
2. *[BaseDN]* フィールドは空白のままにして、 **[OK]** を選択します。
3. コンテナー (例: *AADDC Users*) を選択し、右クリックして **[検索]** を選択します。
4. 事前に設定されたフィールドはそのままにして、 **[実行]** を選択します。 次の出力例に示すように、クエリの結果が右側のウィンドウに表示されます。

    [Image: LDP.exe を使用してマネージド ドメインからオブジェクトを検索する]

特定のコンテナーに対して直接クエリを実行するには、**[表示] &gt; [ツリー]** メニューから、**OU=AADDC Users,DC=AADDSCONTOSO,DC=COM** や *OU=AADDC Computers,DC=AADDSCONTOSO,DC=COM* などの *BaseDN* を指定します。 クエリの書式設定と作成の方法の詳細については、[LDAP クエリの基礎](https://learn.microsoft.com/ja-jp/windows/win32/ad/creating-a-query-filter)に関するページを参照してください。

Note

自己署名証明書を使用する場合は、LDAPS 用に信頼されたルート証明機関に追加した自己署名証明書が LDP.exe で機能することを確認します。

### リソースをクリーンアップする

このチュートリアルで、接続をテストするために、お使いのコンピューターのローカル hosts ファイルに DNS エントリを追加した場合、そのエントリを削除して、実際の DNS ゾーンの正規のレコードを追加してください。 ローカル hosts ファイルのエントリを削除するには、次の手順を実行します。

1. ローカル コンピューターで、管理者として "*メモ帳*" を開きます。
2. ファイル `C:\Windows\System32\drivers\etc\hosts` を参照して開きます。
3. 追加したレコードの行 (例: `168.62.205.103    ldaps.aaddscontoso.com`) を削除します。

### トラブルシューティング

LDAP.exe が接続できないというエラーが表示される場合は、接続を確立するためのさまざまな側面に対処してみてください。

1. ドメイン コントローラーの構成
2. クライアントの構成
3. ネットワーク
4. TLS セッションの確立

証明書のサブジェクト名を照合するために、DC は (Microsoft Entra ドメイン名ではなく) Domain Services ドメイン名を使用して、証明書ストアで証明書を検索します。 たとえばスペルミスなどがあると、DC は適切な証明書を選択できなくなります。

クライアントは、指定された名前を使用して TLS 接続の確立を試みます。 トラフィックは、経路の端から端まで通過する必要があります。 DC は、サーバー認証証明書の公開キーを送信します。証明書には、正しい使用法が記載されている必要があります。また、サブジェクト名で署名されている名前は、そのサーバーが接続先の DNS 名であることをクライアントから信頼されるように矛盾のない (つまり、ワイルドカードが機能し、スペルミスがない) ものである必要があります。この場合、クライアントは発行者を信頼する必要があります。 イベント ビューアーのシステム ログで、そのチェーンに問題があるかどうかを確認し、ソースが Schannel であるイベントをフィルター処理できます。 これらの要素が整うと、セッション キーが形成されます。

詳細については、[TLS ハンドシェイク](https://learn.microsoft.com/ja-jp/windows/win32/secauthn/tls-handshake-protocol)に関する記事をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/tutorial-configure-networking"} -->
## チュートリアル - Microsoft Entra Domain Services の仮想ネットワークを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-networking
- Service: entra-id / domain-services
- Article date: 2025-02-19
- Summary: このチュートリアルでは、Microsoft Entra 管理センターを使用して、Microsoft Entra Domain Services マネージド ドメインの Azure 仮想ネットワーク サブネットまたはネットワーク ピアリングを作成して構成する方法について説明します。

ユーザーとアプリケーションへの接続を提供するために、Microsoft Entra Domain Services マネージド ドメインが Azure 仮想ネットワーク サブネットにデプロイされます。 この仮想ネットワーク サブネットは、Azure プラットフォームによって提供されるマネージド ドメイン リソースにのみ使用する必要があります。

独自の VM とアプリケーションを作成するときは、同じ仮想ネットワーク サブネットにデプロイしないでください。 代わりに、アプリケーションを作成して、別の仮想ネットワーク サブネット、または Domain Services 仮想ネットワークにピアリングされた別の仮想ネットワークにデプロイする必要があります。

このチュートリアルでは、専用の仮想ネットワーク サブネットを作成して構成する方法、または別のネットワークを Domain Services マネージド ドメインの仮想ネットワークとピアリングする方法について説明します。

このチュートリアルでは、以下の内容を学習します。

- Domain Services へのドメイン参加済みリソースの仮想ネットワーク接続オプションについて
- Domain Services 仮想ネットワークに IP アドレス範囲と追加のサブネットを作成する
- Domain Services とは別のネットワークへの仮想ネットワーク ピアリングを構成する

Azure サブスクリプションをお持ちでない場合は、開始 [する前にアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

このチュートリアルを完了するには、以下のリソースと特権が必要です。

- 有効な Azure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付け](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)ます。
- Domain Services を有効にするには、テナントに [アプリケーション管理者](https://learn.microsoft.com/ja-jp/azure/active-directory/roles/permissions-reference#application-administrator) と [グループ管理者](https://learn.microsoft.com/ja-jp/azure/active-directory/roles/permissions-reference#groups-administrator) の Microsoft Entra ロールが必要です。
- 必要な Domain Services リソースを作成するには、Domain Services 共同作成者の Azure ロールが必要です。
- Microsoft Entra テナント内の Microsoft Entra Domain Services マネージド ドメインが有効化および構成されています。
    - 必要であれば、1 つ目のチュートリアルで [Microsoft Entra Domain Services のマネージド ドメインを作成して構成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)します。

### Microsoft Entra 管理センターにサインインする

このチュートリアルでは、Microsoft Entra 管理センターを使用してマネージド ドメインを作成して構成します。 まず、Microsoft Entra 管理センター にサインインします。

### アプリケーション ワークロードの接続オプション

前のチュートリアルでは、仮想ネットワークの既定の構成オプションを使用するマネージド ドメインが作成されました。 これらの既定のオプションでは、Azure 仮想ネットワークと仮想ネットワーク サブネットが作成されました。 マネージド ドメイン サービスを提供する Domain Services ドメイン コントローラーは、この仮想ネットワーク サブネットに接続されます。

マネージド ドメインを使用する必要がある VM を作成して実行するときは、ネットワーク接続を提供する必要があります。 このネットワーク接続は、次のいずれかの方法で提供できます。

- マネージド ドメインの仮想ネットワークに追加の仮想ネットワーク サブネットを作成します。 この追加のサブネットは、VM を作成して接続する場所です。
    - VM は同じ仮想ネットワークの一部であるため、名前解決を自動的に実行し、Domain Services ドメイン コントローラーと通信できます。
- マネージド ドメインの仮想ネットワークから 1 つ以上の個別の仮想ネットワークへの Azure 仮想ネットワーク ピアリングを構成します。 これらの個別の仮想ネットワークは、VM を作成して接続する場所です。
    - 仮想ネットワーク ピアリングを構成する場合は、名前解決を使用して Domain Services ドメイン コントローラーに戻す DNS 設定も構成する必要があります。

通常、これらのネットワーク接続オプションの 1 つだけを使用します。 多くの場合、選択は、個別の Azure リソースを管理する方法にかかっています。

- Domain Services と接続された VM を 1 つのリソース グループとして管理する場合は、VM 用の追加の仮想ネットワーク サブネットを作成できます。
- Domain Services と接続されている VM の管理を分離する場合は、仮想ネットワーク ピアリングを使用できます。
    - また、仮想ネットワーク ピアリングを使用して、既存の仮想ネットワークに接続されている Azure 環境内の既存の VM に接続を提供することもできます。

このチュートリアルでは、これらの仮想ネットワーク接続オプションを 1 つだけ構成する必要があります。

仮想ネットワークを計画および構成する方法の詳細については、 [Microsoft Entra Domain Services のネットワークに関する考慮事項を](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/network-considerations)参照してください。

### 仮想ネットワーク サブネットを作成する

既定では、マネージド ドメインで作成された Azure 仮想ネットワークには、1 つの仮想ネットワーク サブネットが含まれます。 この仮想ネットワーク サブネットは、マネージド ドメイン サービスを提供するために Azure プラットフォームでのみ使用する必要があります。 この Azure 仮想ネットワークで独自の VM を作成して使用するには、追加のサブネットを作成します。

VM とアプリケーション ワークロード用の仮想ネットワーク サブネットを作成するには、次の手順を実行します。

1. Microsoft Entra 管理センターで、 *myResourceGroup* などのマネージド ドメインのリソース グループを選択します。 リソースの一覧から、 *aadds-vnet* などの既定の仮想ネットワークを選択します。
2. 仮想ネットワーク ウィンドウの左側のメニューで、 **[アドレス空間]** を選択します。 この仮想ネットワークは、既定のサブネットで使用される単一のアドレス空間 *10.0.2.0/24* で作成されています。

    この仮想ネットワークにさらに IP アドレス範囲を追加します。 このアドレス範囲のサイズと実際に使用する IP アドレス範囲は、既にデプロイされている他のネットワーク リソースによって異なります。 この IP アドレス範囲は、Azure またはオンプレミスの環境における既存のアドレス範囲と重複することはできません。 サブネットにデプロイする予定の VM の数に対して十分な大きさの IP アドレス範囲を設定するようにしてください。

    次の例では、 *10.0.3.0/24* の IP アドレス範囲が追加されています。 準備ができたら、**[保存]** を選択します。

    [Image: Microsoft Entra 管理センターに仮想ネットワーク IP アドレス範囲を追加する]
3. 次に、仮想ネットワーク ウィンドウの左側のメニューで、 **[サブネット]** を選択し、 **[+ サブネット]** を選択してサブネットを追加します。
4. サブネットの名前を入力してください。例えば、*workloads* のように。 前の手順で仮想ネットワーク用に構成された IP **アドレス範囲** のサブセットを使用する場合は、必要に応じてアドレス範囲を更新します。 ここでは、ネットワーク セキュリティ グループ、ルート テーブル、サービス エンドポイントなどのオプションは既定値のままにします。

    次の例では、*10.0.3.0/24* IP アドレス範囲を使用する*ワークロード*という名前のサブネットが作成されます。

    [Image: Microsoft Entra 管理センターに仮想ネットワーク サブネットを追加する]
5. 準備ができたら **[OK]** を選択します。 仮想ネットワーク サブネットの作成には少し時間がかかります。

マネージド ドメインを使用する必要がある VM を作成するときは、必ずこの仮想ネットワーク サブネットを選択してください。 既定の *aadds-subnet* に VM を作成しないでください。 別の仮想ネットワークを選択した場合、仮想ネットワーク ピアリングを構成しない限り、マネージド ドメインに到達するためのネットワーク接続と DNS 解決はありません。

### 仮想ネットワーク ピアリングを構成する

VM 用の既存の Azure 仮想ネットワークがある場合や、マネージド ドメイン仮想ネットワークを分離しておく必要がある場合があります。 マネージド ドメインを使用するには、他の仮想ネットワーク内の VM が Domain Services ドメイン コントローラーと通信する方法が必要です。 この接続は、Azure 仮想ネットワーク ピアリングを使用して提供できます。

Azure 仮想ネットワーク ピアリングでは、仮想プライベート ネットワーク (VPN) デバイスを必要とせずに、2 つの仮想ネットワークが一緒に接続されます。 ネットワーク ピアリングを使用すると、仮想ネットワークをすばやく接続し、Azure 環境全体のトラフィック フローを定義できます。

ピアリングの詳細については、 [Azure 仮想ネットワーク ピアリングの概要に関するページを](https://learn.microsoft.com/ja-jp/azure/virtual-network/virtual-network-peering-overview)参照してください。

仮想ネットワークをマネージド ドメイン仮想ネットワークとピアリングするには、次の手順を実行します。

1. *aadds-vnet* という名前のマネージド ドメイン用に作成された既定の仮想ネットワークを選択します。
2. 仮想ネットワーク ウィンドウの左側のメニューで、[ **ピアリング**] を選択します。
3. ピアリングを作成するには、[ **+ 追加]** を選択します。 次の例では、既定の *aadds-vnet* は *myVnet* という名前の仮想ネットワークにピアリングされます。 独自の値を使用して、次の設定を構成します。

    - **aadds-vnet からリモート仮想ネットワークへのピアリングの名前**: *aadds-vnet-to-myvnet* などの 2 つのネットワークのわかりやすい識別子
    - **仮想ネットワークのデプロイの種類**: *Resource Manager*
    - **サブスクリプション**: ピアリングする仮想ネットワークのサブスクリプション (*Azure* など)
    - **仮想ネットワーク**: ピアリングする仮想ネットワーク (*myVnet* など)
    - **myVnet から aadds-vnet へのピアリングの名前**: 2 つのネットワークのわかりやすい識別子 (*myvnet から aadds-vnet* など)

    [Image: Microsoft Entra 管理センターで仮想ネットワーク ピアリングを構成する]

    環境に固有の要件がない限り、仮想ネットワーク アクセスまたは転送トラフィックの他の既定値のままにして、[ **OK]** を選択します。
4. Domain Services 仮想ネットワークと選択した仮想ネットワークの両方でピアリングを作成するには、少し時間がかかります。 準備ができたら、次の例に示すように、 **ピアリングの状態** は *接続済*みと報告します。

    [Image: Microsoft Entra 管理センターでピアリングされたネットワークに正常に接続されました]

ピアリングされた仮想ネットワーク内の VM がマネージド ドメインを使用できるようにするには、正しい名前解決を許可するように DNS サーバーを構成します。

#### ピアリングされた仮想ネットワークで DNS サーバーを構成する

ピアリングされた仮想ネットワーク内の VM とアプリケーションがマネージド ドメインと正常に通信するには、DNS 設定を更新する必要があります。 Domain Services ドメイン コントローラーの IP アドレスは、ピアリングされた仮想ネットワーク上の DNS サーバーとして構成する必要があります。 ピアリングされた仮想ネットワークの DNS サーバーとしてドメイン コントローラーを構成するには、次の 2 つの方法があります。

- Domain Services ドメイン コントローラーを使用するように Azure 仮想ネットワーク DNS サーバーを構成します。
- 条件付き DNS 転送を使用してマネージド ドメインにクエリを送信するように、ピアリングされた仮想ネットワークで使用されている既存の DNS サーバーを構成します。 これらの手順は、使用中の既存の DNS サーバーによって異なります。

このチュートリアルでは、すべてのクエリを Domain Services ドメイン コントローラーに送信するように Azure 仮想ネットワーク DNS サーバーを構成します。

1. Microsoft Entra 管理センターで、ピアリングされた仮想ネットワークのリソース グループ ( *myResourceGroup* など) を選択します。 リソースの一覧から、 *myVnet* などのピアリングされた仮想ネットワークを選択します。
2. 仮想ネットワーク ウィンドウの左側のメニューで、 **DNS サーバー**を選択します。
3. 既定では、仮想ネットワークでは、組み込みの Azure が提供する DNS サーバーが使用されます。 **カスタム** DNS サーバーの使用を選択します。 Domain Services ドメイン コントローラーの IP アドレス (通常は *10.0.2.4* と *10.0.2.5*) を入力します。 ポータルのマネージド ドメインの **[概要** ] ウィンドウで、これらの IP アドレスを確認します。

    [Image: Domain Services ドメイン コントローラーを使用するように仮想ネットワーク DNS サーバーを構成する]
4. 準備ができたら、**[保存]** を選択します。 仮想ネットワークの DNS サーバーを更新するには、しばらく時間がかかります。
5. 更新された DNS 設定を VM に適用するには、ピアリングされた仮想ネットワークに接続されている VM を再起動します。

マネージド ドメインを使用する必要がある VM を作成するときは、必ずこのピアリングされた仮想ネットワークを選択してください。 別の仮想ネットワークを選択した場合、マネージド ドメインに到達するためのネットワーク接続と DNS 解決はありません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/tutorial-configure-password-hash-sync"} -->
## Microsoft Entra Domain Services のパスワード ハッシュ同期を有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-password-hash-sync
- Service: entra-id / domain-services
- Article date: 2025-02-19
- Summary: このチュートリアルでは、Microsoft Entra Connect を使用して Microsoft Entra Domain Services マネージド ドメインへのパスワード ハッシュ同期を有効にする方法について説明します。

ハイブリッド環境では、Microsoft Entra Connect を使用してオンプレミスの Active Directory Domain Services (AD DS) 環境と同期するように Microsoft Entra テナントを構成できます。 既定では、Microsoft Entra Connect は、Microsoft Entra Domain Services に必要な従来の NT LAN Manager (NTLM) と Kerberos パスワード ハッシュを同期しません。

オンプレミスの AD DS 環境から同期されたアカウントで Domain Services を使用するには、NTLM および Kerberos 認証に必要なパスワード ハッシュを同期するように Microsoft Entra Connect を構成する必要があります。 Microsoft Entra Connect の構成後は、オンプレミスのアカウント作成またはパスワード変更イベントによって従来のパスワード ハッシュも Microsoft Entra ID に同期されます。

オンプレミスの AD DS 環境がないクラウド専用アカウントを使用する場合は、これらの手順を実行する必要はありません。

このチュートリアルでは、次の情報を学習します。

- 従来の NTLM と Kerberos のパスワード ハッシュが必要な理由
- Microsoft Entra Connect のレガシ パスワード ハッシュ同期を構成する方法

Azure サブスクリプションをお持ちでない場合は、開始 [する前にアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

このチュートリアルを完了するには、以下のリソースが必要です。

- 有効な Azure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- Microsoft Entra Connect を使用してオンプレミスディレクトリと同期されるサブスクリプションに関連付けられている Microsoft Entra テナント。
    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付け](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)ます。
    - 必要に応じて、 [パスワード ハッシュ同期のために Microsoft Entra Connect を有効にします](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/how-to-connect-install-express)。
- Microsoft Entra テナント内の Microsoft Entra Domain Services マネージド ドメインが有効化および構成されています。
    - 必要に応じて、[Microsoft Entra Domain Services のマネージド ドメインを作成して構成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)。

### Microsoft Entra Connect を使用したパスワード ハッシュ同期

Microsoft Entra Connect は、ユーザー アカウントやグループなどのオブジェクトをオンプレミスの AD DS 環境から Microsoft Entra テナントに同期するために使用されます。 このプロセスの一環として、パスワード ハッシュ同期により、アカウントはオンプレミスの AD DS 環境と Microsoft Entra ID で同じパスワードを使用できるようになります。

マネージド ドメインでユーザーを認証するには、Domain Services には NTLM および Kerberos 認証に適した形式のパスワード ハッシュが必要です。 Microsoft Entra ID では、テナントに対して Domain Services を有効にするまで、NTLM または Kerberos 認証に必要な形式のパスワード ハッシュは格納されません。 また、セキュリティ上の理由から、クリアテキスト形式のパスワード資格情報が Microsoft Entra ID に保存されることもありません。 そのため、Microsoft Entra ID では、ユーザーの既存の資格情報に基づいて、これらの NTLM やKerberos のパスワード ハッシュを自動的に生成することはできません。

Microsoft Entra Connect は、Domain Services に必要な NTLM または Kerberos パスワード ハッシュを同期するように構成できます。 [パスワード ハッシュ同期のために Microsoft Entra Connect を有効にする](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/how-to-connect-install-express)手順が完了していることを確認します。 Microsoft Entra Connect の既存のインスタンスがある場合は、NTLM と Kerberos の従来のパスワード ハッシュを同期できるように [、最新バージョンをダウンロードして更新](https://www.microsoft.com/download/details.aspx?id=47594) します。 この機能は、Microsoft Entra Connect の初期リリースや従来の DirSync ツールでは使用できません。 Microsoft Entra Connect バージョン *1.1.614.0* 以降が必要です。

Von Bedeutung

Microsoft Entra Connect は、オンプレミスの AD DS 環境との同期のためにのみインストールおよび構成する必要があります。 オブジェクトを Microsoft Entra ID に同期させるために、Domain Services マネージド ドメインに Microsoft Entra Connect をインストールすることはサポートされていません。

### パスワード ハッシュの同期を有効にする

Microsoft Entra Connect がインストールされ、Microsoft Entra ID と同期するように構成されたので、NTLM と Kerberos のレガシ パスワード ハッシュ同期を構成します。 PowerShell スクリプトを使用して、必要な設定を構成し、Microsoft Entra ID への完全なパスワード同期を開始します。 Microsoft Entra Connect のパスワード ハッシュ同期プロセスが完了すると、ユーザーは従来の NTLM または Kerberos パスワード ハッシュを使用する Domain Services を使用してアプリケーションにサインインできます。

1. Microsoft Entra Connect がインストールされているコンピューターで、[スタート] メニューから **Microsoft Entra Connect &gt; 同期サービス**を開きます。
2. [コネクタ] タブ **を** 選択します。オンプレミスの AD DS 環境と Microsoft Entra ID の間の同期を確立するために使用される接続情報が一覧表示されます。

    **種類**は、Microsoft *Entra コネクタの Windows Microsoft Entra ID (Microsoft)* またはオンプレミス AD DS コネクタの *Active Directory Domain Services* のいずれかを示します。 次の手順で PowerShell スクリプトで使用するコネクタ名を書き留めます。

    [Image: Sync Service Manager でコネクタ名を一覧表示する]

    この例のスクリーンショットでは、次のコネクタが使用されています。

    - Microsoft Entra コネクタの名前は *contoso.onmicrosoft.com - Microsoft Entra ID*
    - オンプレミスの AD DS コネクタの名前は *onprem.contoso.com*
3. 次の PowerShell スクリプトをコピーし、Microsoft Entra Connect がインストールされているコンピューターに貼り付けます。 このスクリプトは、従来のパスワード ハッシュを含む完全なパスワード同期をトリガーします。 前の手順のコネクタ名を使用して、 `$azureadConnector` 変数と `$adConnector` 変数を更新します。

    オンプレミス アカウントの NTLM と Kerberos パスワード ハッシュを Microsoft Entra ID に同期するには、各 AD フォレストでこのスクリプトを実行します。

    ```powershell
    # Define the Azure AD Connect connector names and import the required PowerShell module
    $azureadConnector = "<CASE SENSITIVE AZURE AD CONNECTOR NAME>"
    $adConnector = "<CASE SENSITIVE AD DS CONNECTOR NAME>"
    
    Import-Module "C:\Program Files\Microsoft Azure AD Sync\Bin\ADSync\ADSync.psd1"
    Import-Module "C:\Program Files\Microsoft Azure Active Directory Connect\AdSyncConfig\AdSyncConfig.psm1"
    
    # Create a new ForceFullPasswordSync configuration parameter object then
    # update the existing connector with this new configuration
    $c = Get-ADSyncConnector -Name $adConnector
    $p = New-Object Microsoft.IdentityManagement.PowerShell.ObjectModel.ConfigurationParameter "Microsoft.Synchronize.ForceFullPasswordSync", String, ConnectorGlobal, $null, $null, $null
    $p.Value = 1
    $c.GlobalParameters.Remove($p.Name)
    $c.GlobalParameters.Add($p)
    $c = Add-ADSyncConnector -Connector $c
    
    # Disable and re-enable Azure AD Connect to force a full password synchronization
    Set-ADSyncAADPasswordSyncConfiguration -SourceConnector $adConnector -TargetConnector $azureadConnector -Enable $false
    Set-ADSyncAADPasswordSyncConfiguration -SourceConnector $adConnector -TargetConnector $azureadConnector -Enable $true
    ```

    アカウントとグループの数の観点からディレクトリのサイズによっては、従来のパスワード ハッシュと Microsoft Entra ID の同期に時間がかかる場合があります。 その後、パスワードは Microsoft Entra ID に同期された後、マネージド ドメインに同期されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/tutorial-create-forest-trust"} -->
## チュートリアル - Microsoft Entra Domain Services でフォレスト トラストを設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-forest-trust
- Service: entra-id / domain-services
- Article date: 2025-06-30
- Summary: Microsoft Entra Domain Services の Microsoft Entra 管理センターでオンプレミスの AD DS ドメインへの一方向の送信フォレストを作成する方法について説明します

Microsoft Entra Domain Services とオンプレミスの AD DS 環境の間にフォレスト信頼を構築できます。 フォレストの信頼関係により、ユーザー、アプリケーション、およびコンピューターは、Domain Services マネージド ドメインからオンプレミス ドメインに対して、またはその逆の認証を行うことができます。 フォレスト トラストは、次のようなシナリオでユーザーがリソースにアクセスするのに役立ちます。

- パスワード ハッシュを同期できない環境、またはユーザーがスマート カードを使用して排他的にサインインし、自分のパスワードを知らない環境。
- オンプレミス ドメインへのアクセスを必要とするハイブリッド シナリオ。

ユーザーがリソースにアクセスする必要がある方法に応じて、フォレストの信頼を作成するときに考えられる 3 つの方法から選択できます。 Domain Services では、フォレスト トラストのみがサポートされます。 オンプレミスの子ドメインへの外部信頼はサポートされていません。

| 信頼方向 | ユーザー アクセス |
| --- | --- |
| 双方向 | マネージド ドメインとオンプレミス ドメインの両方のユーザーが、どちらのドメイン内のリソースにもアクセスできるようにします。 |
| 一方向送信 | オンプレミス ドメインのユーザーがマネージド ドメイン内のリソースにアクセスできるようにしますが、その逆は許可されません。 |
| 一方向着信 | マネージド ドメインのユーザーがオンプレミス ドメイン内のリソースにアクセスできるようにします。 |

[Image: Domain Services とオンプレミス ドメインの間のフォレスト信頼関係の図。]

このチュートリアルでは、以下の内容を学習します。

- Domain Services 接続をサポートするようにオンプレミスの AD DS ドメインで DNS を構成する
- マネージド ドメインとオンプレミス ドメインの間に双方向のフォレスト信頼を作成する
- 認証とリソース アクセスのフォレストの信頼関係をテストして検証する

Azure サブスクリプションをお持ちでない場合は、開始 [する前にアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

このチュートリアルを完了するには、以下のリソースと特権が必要です。

- 有効な Azure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付け](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)ます。
- カスタム DNS ドメイン名と有効な SSL 証明書を使用して構成された Domain Services マネージド ドメイン。
    - 必要に応じて、[Microsoft Entra Domain Services のマネージド ドメインを作成して構成します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance-advanced)。
- VPN または ExpressRoute 接続経由でマネージド ドメインから到達可能なオンプレミスの Active Directory ドメイン。
- テナントで Domain Services インスタンスを変更するためには、[アプリケーション管理者](https://learn.microsoft.com/ja-jp/azure/active-directory/roles/permissions-reference#application-administrator)および[グループ管理者](https://learn.microsoft.com/ja-jp/azure/active-directory/roles/permissions-reference#groups-administrator)のMicrosoft Entra ロールが必要です。
- 信頼関係を作成して検証するアクセス許可を持つオンプレミス ドメインのドメイン管理者アカウント。

Von Bedeutung

マネージド ドメインには、少なくとも *Enterprise* SKU を使用する必要があります。 必要に応じて、 [マネージド ドメインの SKU を変更します](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/change-sku)。

### Microsoft Entra 管理センターにサインインする

このチュートリアルでは、Microsoft Entra 管理センターを使用して、Domain Services から送信されるフォレスト信頼を作成し、設定します。 まず、Microsoft Entra 管理センター にサインインします。

### ネットワークに関する考慮事項

Domain Services フォレストをホストする仮想ネットワークには、オンプレミスの Active Directory への VPN または ExpressRoute 接続が必要です。 アプリケーションとサービスには、Domain Services フォレストをホストする仮想ネットワークへのネットワーク接続も必要です。 Domain Services フォレストへのネットワーク接続は常にオンで、安定している必要があります。そうしないと、ユーザーがリソースの認証やアクセスに失敗する可能性があります。

Domain Services でフォレストの信頼を構成する前に、Azure とオンプレミス環境の間のネットワークが次の要件を満たしていることを確認してください。

- 信頼を作成して使用するために必要なトラフィックがファイアウォール ポートとドメイン コントローラーで許可されていることを確認します。 信頼を使用するために開く必要があるポートの詳細については、「 [AD DS 信頼のファイアウォール設定を構成する](https://learn.microsoft.com/ja-jp/troubleshoot/windows-server/active-directory/config-firewall-for-ad-domains-and-trusts)」を参照してください。 ドメイン サービスとの信頼を持つドメイン内のすべてのドメイン コントローラーで、これらのポートを開く必要があります。
- プライベート IP アドレスを使用します。 動的 IP アドレスの割り当てで DHCP に依存しないでください。
- 仮想ネットワークのピアリングとルーティングが Azure とオンプレミスの間で正常に通信できるように、IP アドレス空間が重複しないようにします。
- Azure 仮想ネットワークには、 [Azure サイト間 (S2S) VPN](https://learn.microsoft.com/ja-jp/azure/vpn-gateway/vpn-gateway-about-vpngateways) または [ExpressRoute](https://learn.microsoft.com/ja-jp/azure/expressroute/expressroute-introduction) 接続を構成するためのゲートウェイ サブネットが必要です。
- シナリオをサポートするのに十分な IP アドレスを持つサブネットを作成します。
- Domain Services に独自のサブネットがあることを確認します。この仮想ネットワーク サブネットをアプリケーション VM やサービスと共有しないでください。
- ピアリングされた仮想ネットワークは推移的ではありません。
    - オンプレミスの AD DS 環境に対する Domain Services フォレストの信頼を使用するすべての仮想ネットワーク間に、Azure 仮想ネットワーク ピアリングを作成する必要があります。
- オンプレミスの Active Directory フォレストへの継続的なネットワーク接続を提供します。 オンデマンド接続は使用しないでください。
- Domain Services フォレスト名とオンプレミスの Active Directory フォレスト名の間に継続的な DNS 名前解決があることを確認します。

### オンプレミス ドメインで DNS を構成する

オンプレミス環境からマネージド ドメインを正しく解決するには、フォワーダーを既存の DNS サーバーに追加することが必要な場合があります。 マネージド ドメインと通信するようにオンプレミス環境を構成するには、オンプレミス AD DS ドメインの管理ワークステーションから次の手順を実行します。

1. **[Start**&gt;**Administrative Tools**&gt;**DNS**] を選択します。
2. *aaddscontoso.com* など、DNS ゾーンを選択します。
3. [**条件付きフォワーダー**] を選択し、右クリックして [**新しい条件付きフォワーダー**] を選択します。
4. 他の **DNS ドメイン** ( *contoso.com* など) を入力し、次の例に示すように、その名前空間の DNS サーバーの IP アドレスを入力します。

    [Image: DNS サーバーの条件付きフォワーダーを追加して構成する方法のスクリーンショット。]
5. **[この条件付きフォワーダーを Active Directory に保存**する] チェック ボックスをオンにし、次のようにレプリケートし、次の例に示すように、*このドメイン内のすべての DNS サーバー*のオプションを選択します。

    [Image: このドメイン内のすべての DNS サーバーを選択する方法のスクリーンショット。]

    Von Bedeutung

    条件付きフォワーダーが*ドメイン*ではなく*フォレスト*に格納されている場合、条件付きフォワーダーは失敗します。
6. 条件付きフォワーダーを作成するには、[ **OK] を選択します**。

### オンプレミス環境で双方向のフォレスト トラストを作成する

オンプレミスの AD DS ドメインには、マネージド ドメイン用に両方向のフォレスト トラストが必要です。 この信頼は、オンプレミスの AD DS ドメインに手動で作成する必要があります。Microsoft Entra 管理センターから作成することはできません。

オンプレミスの AD DS ドメインで双方向の信頼を構成するには、オンプレミス AD DS ドメインの管理ワークステーションからドメイン管理者として次の手順を実行します。

1. **[スタート**&gt;**管理者ツール**&gt;**Active Directory のドメインと信頼**を選択します。
2. *onprem.contoso.com* など、ドメインを右クリックし、[**プロパティ**] を選択します。
3. [ **信頼** ] タブ、次に [ **新しい信頼**] を選択します。
4. ドメイン サービスのドメイン名の名前 ( *aaddscontoso.com* など) を入力し、[ **次へ**] を選択します。
5. **フォレスト信頼**を作成するオプションを選択し、その後**双方向信頼**を作成します。
6. **の信頼を作成することを選択します。このドメインは**だけです。 次の手順では、マネージド ドメインの Microsoft Entra 管理センターで信頼を作成します。
7. フォレスト全体 **認証**を使用することを選択し、信頼パスワードを入力して確認します。 この同じパスワードは、次のセクションの Microsoft Entra 管理センターにも入力されます。
8. 既定のオプションを使用して次のいくつかのウィンドウをステップ実行し、オプションの **[確認しない]** を選択します。
9. **完了** を選択します。

フォレスト トラストが環境に不要となった場合、ドメイン管理者として次の手順を実行し、オンプレミス ドメインから削除します。

1. **[スタート**&gt;**管理者ツール**&gt;**Active Directory のドメインと信頼**を選択します。
2. *onprem.contoso.com* など、ドメインを右クリックし、[**プロパティ**] を選択します。
3. [ **信頼** ] タブを選択し、 **このドメインを信頼するドメイン (受信信頼)** を選択し、削除する信頼をクリックして、[ **削除**] をクリックします。
4. [信頼] タブの [ **このドメインによって信頼されているドメイン (送信信頼)]** で、削除する信頼をクリックし、[削除] をクリックします。
5. [いいえ] をクリックし **、ローカル ドメインからのみ信頼を削除**します。

### Domain Services で双方向フォレスト信頼を作成する

Microsoft Entra 管理センターでマネージド ドメインの双方向信頼を作成するには、次の手順を実行します。

1. Microsoft Entra 管理センターで、 **Microsoft Entra Domain Services** を検索して選択し、マネージド ドメイン ( *aaddscontoso.com* など) を選択します。
2. マネージド ドメインの左側にあるメニューから [ **信頼**] を選択し、[+ 信頼の **追加]** を選択します。
3. 信頼の方向として **[双方向** ] を選択します。
4. 信頼を識別するための表示名を入力し、その後、オンプレミスの信頼されたフォレストの DNS 名を入力してください（例: *onprem.contoso.com*）。
5. 前のセクションでオンプレミスの AD DS ドメインの受信フォレスト信頼を構成するために使用したのと同じ信頼パスワードを指定します。
6. *10.1.1.4 や 10.1.1.5* など、オンプレミスの AD DS ドメインに少なくとも *2* つの DNS サーバーを提供します。
7. 準備ができたら、送信フォレストの信頼を**保存**します。

    [Image: Microsoft Entra 管理センターで送信フォレスト信頼を設定する際のスクリーンショット。]

環境でフォレスト信頼が不要になった場合は、次の手順を実行して、Domain Services から削除します。

1. Microsoft Entra 管理センターで、 **Microsoft Entra Domain Services** を検索して選択し、マネージド ドメイン ( *aaddscontoso.com* など) を選択します。
2. マネージド ドメインの左側にあるメニューから [信頼] を選択し、 **信頼**を選択して、[ **削除**] をクリックします。
3. フォレストの信頼を構成するために使用したのと同じ信頼パスワードを指定し、[ **OK]** をクリックします。

### 信頼の設定を検証する

双方向または一方向の受信信頼を作成したら、Active Directory ドメインと信頼コンソールまたは nltest コマンド ライン ツールを使用して、受信信頼 (オンプレミス ドメインからの送信信頼) を確認できます。

#### Active Directory ドメインと信頼を使用して信頼を確認する

信頼関係を作成して検証するアクセス許可を持つアカウントを使用して、オンプレミスの AD DS ドメイン コントローラーから次の手順を実行します。

1. **[スタート**&gt;**管理者ツール**&gt;**Active Directory のドメインと信頼**を選択します。
2. ドメインを右クリックし、[ **プロパティ**] を選択します。
3. [信頼] タブ **を** 選択します。
4. **このドメインが信頼するドメイン (送信信頼)**で、作成した信頼を選択します。
5. **[プロパティ]** を選択します。
6. **[検証]** を選択します。
7. **「いいえ、受信した信頼を検証しない」を選択します**。
8. [ **OK] をクリックします**。

信頼が正しく構成されている場合は、次のメッセージが表示されます。

`The outgoing trust has been validated. It is in place and active.`

#### nltest を使用して信頼を確認する

nltest コマンド ライン ツールを使用して、信頼を確認することもできます。 信頼関係を作成して検証するアクセス許可を持つアカウントを使用して、オンプレミスの AD DS ドメイン コントローラーから次の手順を実行します。

1. オンプレミスのドメイン コントローラーまたは管理ワークステーションで管理者特権のコマンド プロンプトを開きます。
2. 次のコマンドを実行します。

    ```
    nltest /sc_verify:<TrustedDomain>
    ```

     &lt;TrustedDomain&gt; を Microsoft Entra Domain Services ドメインの名前に置き換えます。 信頼が有効な場合、コマンドは成功メッセージを返します。

    [Image: 信頼の作成に関する成功メッセージのスクリーンショット。]

### リソース アクセスの検証

次の一般的なシナリオでは、フォレストの信頼がユーザーとリソースへのアクセスを正しく認証することを検証できます。

- Domain Services フォレストからのオンプレミスのユーザー認証
- オンプレミス ユーザーを使用して Domain Services フォレスト内のリソースにアクセスする
    - ファイルとプリンターの共有 を有効にする
    - セキュリティ グループを作成し、メンバーを追加
    - フォレスト間アクセス 用のファイル共有を作成する
    - リソース に対するフォレスト間認証を検証する

#### Domain Services フォレストからのオンプレミス ユーザー認証

Windows Server 仮想マシンがマネージド ドメインに参加している必要があります。 この仮想マシンを使用して、オンプレミスユーザーが仮想マシンで認証できることをテストします。 必要に応じて、 [Windows VM を作成し、マネージド ドメインに参加させます](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/join-windows-vm)。

1. [Azure Bastion](https://learn.microsoft.com/ja-jp/azure/bastion/bastion-overview) と Domain Services 管理者の資格情報を使用して、Domain Services フォレストに参加している Windows Server VM に接続します。
2. コマンド プロンプトを開き、`whoami` コマンドを使用して、現在認証されているユーザーの識別名を表示します。

    ```console
    whoami /fqdn
    ```
3. `runas` コマンドを使用して、オンプレミス ドメインのユーザーとして認証します。 次のコマンドでは、`userUpn@trusteddomain.com` を、信頼されたオンプレミス ドメインのユーザーの UPN に置き換えます。 コマンドによって、ユーザーのパスワードの入力が求められます。

    ```console
    Runas /u:userUpn@trusteddomain.com cmd.exe
    ```
4. 認証が成功すると、新しいコマンド プロンプトが開きます。 新しいコマンド プロンプトのタイトルには、`running as userUpn@trusteddomain.com`が含まれています。
5. 新しいコマンド プロンプトで `whoami /fqdn` を使用して、認証されたユーザーの識別名をオンプレミスの Active Directory から表示します。

#### オンプレミス ユーザーを使用して Domain Services フォレスト内のリソースにアクセスする

Domain Services フォレストに参加している Windows Server VM から、シナリオをテストできます。 たとえば、オンプレミス ドメインにサインインしたユーザーがマネージド ドメイン内のリソースにアクセスできるかどうかをテストできます。 次の例では、一般的なテスト シナリオについて説明します。

##### ファイルとプリンターの共有を有効にする

1. [Azure Bastion](https://learn.microsoft.com/ja-jp/azure/bastion/bastion-overview) と Domain Services 管理者の資格情報を使用して、Domain Services フォレストに参加している Windows Server VM に接続します。
2. **Windows の設定を**開きます。
3. **[ネットワークと共有センター**] を検索して選択します。
4. **[共有の詳細設定の変更]** のオプションを選択します。
5. **ドメイン プロファイル**で、[**ファイルとプリンターの共有を有効にする**] を選択し、[**変更を保存**] をします。
6. **ネットワークと共有センターの**を閉じます。

##### セキュリティ グループを作成してメンバーを追加する

1. **[Active Directory ユーザーとコンピューター]** を開きます。
2. ドメイン名を右クリックし、[新しい ] を選択し、[組織単位] 選択します。
3. [名前] ボックスに「LocalObjects 」と入力し、[OK] 選択します。
4. ナビゲーション ウィンドウ **LocalObjects** を選択して右クリックします。 **を選択して、新しい** を選び、その後 **グループ**を選択します。
5. 「*FileServerAccess*」を**グループ名** ボックスに入力します。 [**グループ スコープ**] で、[**ドメイン ローカル**] を選択し、[**OK**] を選択します。
6. コンテンツ ウィンドウで **FileServerAccess**をダブルクリックします。 **[メンバー]** 、 **[追加]** 、 **[場所]** の順に選択します。
7. オンプレミスの Active Directory を [**の場所]** ビューから選択し、[OK] を選択します。
8. *[選択するオブジェクト名を入力してください]* ボックスに「**Domain Users**」と入力します。 **[名前の確認]**を選択し、オンプレミスの Active Directory の資格情報を入力して、[OK] を選択します。

    注

    信頼関係は 1 つの方法に過ぎないため、資格情報を指定する必要があります。 つまり、Domain Services マネージド ドメインのユーザーは、リソースにアクセスしたり、信頼された (オンプレミス) ドメイン内のユーザーまたはグループを検索したりできません。
9. オンプレミスの Active Directory の **Domain Users** グループは、**FileServerAccess** グループのメンバーである必要があります。 [OK]  選択してグループを保存し、ウィンドウを閉じます。

##### フォレスト間アクセス用のファイル共有を作成する

1. Domain Services フォレストに参加している Windows Server VM で、フォルダーを作成し、 *CrossForestShare などの名前を指定*します。
2. フォルダーを右クリックし、[プロパティ] 選択します。
3. [**セキュリティ**] タブを選択し、[編集] を選択します。
4. *[CrossForestShare のアクセス許可]* ダイアログ ボックスで **[追加]** を選択します。
5. *[選択するオブジェクト名を入力してください]* に、「**FileServerAccess**」と入力し、 **[OK]** を選択します。
6. *グループまたはユーザー名* の一覧から **FileServerAccess** を選択します。 **[FileServerAccess のアクセス許可]** 一覧で、 *[変更]* アクセス許可と **[書き込み]** アクセス許可に対して **[許可]** を選択し、 **[OK]** を選択します。
7. [ **共有** ] タブを選択し、[ **高度な共有...**] を選択します。
8. [フォルダー **共有**] を選択し、[ファイル共有の覚えやすい名前を **共有名** に入力します。例: *CrossForestShare*]。
9. **[アクセス許可]** を選択します。 **[Permissions for Everyone](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/全員のアクセス許可)** 一覧で、 **[変更]** アクセス許可に対して **[許可]** を選択します。
10. **[OK]** を 2 回選択して、 **[閉じる]** を選択します。

##### リソースに対するフォレスト間認証を検証する

1. オンプレミスの Active Directory のユーザー アカウントを使用して、オンプレミスの Active Directory に参加している Windows コンピューターにサインインします。
2. **Windows Explorer** 使用して、完全修飾ホスト名と共有 (`\\fs1.aaddscontoso.com\CrossforestShare` など) を使用して作成した共有に接続します。
3. 書き込みアクセス許可を検証するには、フォルダーを右クリックして、**[新しい**] を選択し、次に **[テキスト ドキュメント**] を選択します。 既定の名前 **[新しいテキスト ドキュメント]** を使用します。

    書き込みアクセス許可が正しく設定されている場合は、新しいテキスト ドキュメントが作成されます。 必要に応じてファイルを開き、編集、削除するには、次の手順を実行します。
4. 読み取りアクセス許可を検証するには、新しいテキスト ドキュメント 開きます。
5. 変更アクセス許可を検証するには、ファイルにテキストを追加して、**メモ帳を**閉じます。 変更を保存するように求められたら、[**「保存」]**を選択します。
6. 削除アクセス許可を検証するには、 **[新しいテキスト ドキュメント]** を右クリックし、 **[削除]** を選択します。 [はい]  選択して、ファイルの削除を確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/tutorial-create-instance"} -->
## チュートリアル - Microsoft Entra Domain Services マネージド ドメインを作成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance
- Service: entra-id / domain-services
- Article date: 2025-02-19
- Summary: このチュートリアルでは、Microsoft Entra 管理センターを使用して Microsoft Entra Domain Services マネージド ドメインを作成および構成する方法について説明します。

Microsoft Entra Domain Services では、Windows Server Active Directory と完全に互換性のあるマネージド ドメイン サービス (ドメイン参加、グループ ポリシー、LDAP、Kerberos または NTLM 認証など) が提供されます。 ドメイン コントローラーのデプロイ、管理、パッチの適用を自分で行わなくても、これらのドメイン サービスを使用することができます。 Domain Services は、既存の Microsoft Entra テナントと統合されます。 この統合により、ユーザーは、各自の会社の資格情報を使用してサインインすることができます。また管理者は、既存のグループとユーザー アカウントを使用してリソースへのアクセスをセキュリティで保護することができます。

ネットワークと同期の既定の構成オプションを使用してマネージド ドメインを作成することも、 [これらの設定を手動で定義](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance-advanced)することもできます。 このチュートリアルでは、既定のオプションを使用して、Microsoft Entra 管理センターを使用して Domain Services マネージド ドメインを作成および構成する方法について説明します。

このチュートリアルでは、以下の内容を学習します。

- マネージド ドメインの DNS 要件について
- マネージド ドメインを作成する
- パスワード ハッシュ同期を有効にする

Azure サブスクリプションをお持ちでない場合は、開始 [する前にアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

このチュートリアルを完了するには、以下のリソースと特権が必要です。

- 有効な Azure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付け](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)ます。
- Domain Services を有効にするには、テナントに [アプリケーション管理者](https://learn.microsoft.com/ja-jp/azure/active-directory/roles/permissions-reference#application-administrator) と [グループ管理者](https://learn.microsoft.com/ja-jp/azure/active-directory/roles/permissions-reference#groups-administrator) の Microsoft Entra ロールが必要です。
- 必要な Domain Services リソースを作成するには、 [Domain Services 共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#domain-services-contributor) の Azure ロールが必要です。
- ストレージなどの必要なインフラストラクチャに対してクエリを実行できる DNS サーバーを備えた仮想ネットワーク。 一般的なインターネット クエリを実行できない DNS サーバーは、マネージド ドメインを作成する機能をブロックする可能性があります。

Domain Services では必須ではありませんが、Microsoft Entra テナントの [セルフサービス パスワード リセット (SSPR) を構成](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/tutorial-enable-sspr) することをお勧めします。 ユーザーは SSPR なしでパスワードを変更できますが、パスワードを忘れてリセットする必要がある場合は、SSPR が役立ちます。

Von Bedeutung

マネージド ドメインは、作成後に別のサブスクリプション、リソース グループ、またはリージョンに移動することはできません。 マネージド ドメインをデプロイするときは、最も適切なサブスクリプション、リソース グループ、リージョンを選択するように注意してください。

### Microsoft Entra 管理センターにサインインする

このチュートリアルでは、Microsoft Entra 管理センターを使用してマネージド ドメインを作成して構成します。 まず、Microsoft Entra 管理センター にサインインします。

### マネージド ドメインを作成する

**Microsoft Entra Domain Services の有効化**ウィザードを起動するには、次の手順を実行します。

1. Microsoft Entra 管理センター メニューまたは **ホーム** ページで *Domain Services* を検索し、 **Microsoft Entra Domain Services** を選択します。
2. Microsoft Entra Domain Services ページで、 **Microsoft Entra Domain Services の作成**を選択します。

    [Image: マネージド ドメインを作成する方法のスクリーンショット。]
3. マネージド ドメインを作成する Azure **サブスクリプション** を選択します。
4. マネージド ドメインが属する **リソース グループ** を選択します。 [ **新規作成** ] を選択するか、既存のリソース グループを選択します。

マネージド ドメインを作成するときは、DNS 名を指定します。 この DNS 名を選択する際には、いくつかの考慮事項があります。

- **組み込みのドメイン名:** 既定では、ディレクトリの組み込みドメイン名 ( *.onmicrosoft.com* サフィックス) が使用されます。 インターネット経由でマネージド ドメインへのセキュリティで保護された LDAP アクセスを有効にする場合、この既定のドメインとの接続をセキュリティで保護するデジタル証明書を作成することはできません。 Microsoft は *.onmicrosoft.com* ドメインを所有しているため、証明機関 (CA) は証明書を発行しません。
- **カスタム ドメイン名:** 最も一般的な方法は、カスタム ドメイン名 (通常は既に所有していてルーティング可能な名前) を指定することです。 ルーティング可能なカスタム ドメインを使用すれば、ご利用のアプリケーションをサポートするために必要なトラフィックを正しく送信することができます。
- **ルーティング不可能なドメイン サフィックス:** 一般に、 *contoso.local* などのルーティング不可能なドメイン名サフィックスは使用しないことをお勧めします。 *.local* サフィックスはルーティング可能ではなく、DNS 解決の問題を引き起こす可能性があります。

ヒント

カスタム ドメイン名を作成する場合は、既存の DNS 名前空間に注意してください。 サポートされていますが、既存の Azure またはオンプレミスの DNS 名前空間とは別のドメイン名を使用することもできます。

たとえば、 *contoso.com の既存*の DNS 名前空間がある場合は、dscontoso.com のカスタム ドメイン名を持つマネージド ドメインを作成 *します*。 Secure LDAP を使用する必要がある場合は、必要な証明書を生成するために、このカスタム ドメイン名を登録して所有する必要があります。

環境内の他のサービスに対して追加の DNS レコードを作成したり、環境内の既存の DNS 名前空間間に条件付き DNS フォワーダーを作成したりする必要がある場合があります。 たとえば、ルート DNS 名を使用してサイトをホストする Web サーバーを実行すると、追加の DNS エントリを必要とする名前付けの競合が発生する可能性があります。

これらのチュートリアルとハウツー記事では、 *dscontoso.com* のカスタム ドメインを簡単な例として使用します。 すべてのコマンドで、独自のドメイン名を指定します。

次の DNS 名の制限も適用されます。

- **ドメイン プレフィックスの制限:** プレフィックスが 15 文字を超えるマネージド ドメインを作成することはできません。 指定したドメイン名のプレフィックス (*dscontoso.com* ドメイン名の *dscontoso* など) には、15 文字以下を含める必要があります。
- **ネットワーク名の競合:**マネージド ドメインの DNS ドメイン名が仮想ネットワークにまだ存在しないようにする必要があります。 具体的には、名前の競合につながる次のシナリオを確認します。
    - Azure 仮想ネットワーク上に同じ DNS ドメイン名を持つ Active Directory ドメインが既にある場合。
    - マネージド ドメインを有効にする予定の仮想ネットワークに、オンプレミス ネットワークとの VPN 接続がある場合。 このシナリオでは、オンプレミス ネットワークに同じ DNS ドメイン名を持つドメインがないことを確認します。
    - Azure 仮想ネットワーク上にその名前を持つ既存の Azure クラウド サービスがある場合。

Microsoft Entra 管理センターの *[基本* ] ウィンドウのフィールドに入力して、マネージド ドメインを作成します。

1. 前の点を考慮して、マネージド ドメインの **DNS ドメイン名** を入力します。
2. マネージド ドメインを作成する Azure **リージョン** を選択します。 Azure Availability Zones をサポートするリージョンを選択すると、追加の冗長性のために Domain Services リソースがゾーン間で分散されます。

    ヒント

    Availability Zones は、Azure リージョン内の一意の物理的な場所です。 それぞれのゾーンは、独立した電源、冷却手段、ネットワークを備えた 1 つまたは複数のデータセンターで構成されています。 回復性を確保するため、有効になっているリージョンにはいずれも最低 3 つのゾーンが別個に存在しています。

    Domain Services を複数のゾーンに分散するために、ご自身で構成するものは何もありません。 Azure プラットフォームでは、ゾーンへのリソース分散が自動的に処理されます。 詳細情報および利用可能なリージョンについては、「[Azure の Availability Zones の概要](https://learn.microsoft.com/ja-jp/azure/reliability/availability-zones-overview)」を参照してください。
3. **SKU** によって、パフォーマンスとバックアップの頻度が決まります。 ビジネスの需要や要件が変更された場合は、マネージド ドメインの作成後に SKU を変更できます。 詳細については、「 [Domain Services SKU の概念](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/administration-concepts#azure-ad-ds-skus)」を参照してください。

    このチュートリアルでは、 *Standard* SKU を選択します。 [ *基本* ] ウィンドウは次のスクリーンショットのようになります。

    [Image: マネージド ドメインの [基本構成] ページのスクリーンショット。]

マネージド ドメインをすばやく作成するには、[ **確認と作成** ] を選択して、追加の既定の構成オプションを受け入れます。 この作成オプションを選択すると、次の既定値が構成されます。

- 既定では、*10.0.1.0/24* の IP アドレス範囲を使用する *ds-vnet* という名前の仮想ネットワークを作成します。
- *10.0.1.0/24* の IP アドレス範囲を使用して*、ds-subnet* という名前のサブネットを作成します。
- Microsoft Entra ID *のすべての* ユーザーをマネージド ドメインに同期します。

注

次の問題のため、仮想ネットワークとそのサブネットにパブリック IP アドレスを使用しないでください。

- **IP アドレスの不足**: IPv4 パブリック IP アドレスは制限されており、その需要は多くの場合、使用可能な供給を超えています。 また、パブリック エンドポイントと重複する IP が存在する可能性もあります。
- **セキュリティ リスク**: 仮想ネットワークにパブリック IP を使用すると、デバイスがインターネットに直接公開され、不正アクセスや潜在的な攻撃のリスクが高まります。 適切なセキュリティ対策がないと、デバイスがさまざまな脅威に対して脆弱になる可能性があります。
- **複雑さ**: パブリック IP を使用した仮想ネットワークの管理は、外部 IP 範囲を処理し、適切なネットワークのセグメント化とセキュリティを確保する必要があるため、プライベート IP を使用するよりも複雑になる可能性があります。

プライベート IP アドレスを使用することを強くお勧めします。 パブリック IP を使用する場合は、選択したパブリック範囲内の選択した IP の所有者または専用ユーザーであることを確認します。

[ **確認と作成** ] を選択して、これらの既定の構成オプションをそのまま使用します。

### マネージド ドメインをデプロイする

ウィザードの **[概要** ] ページで、マネージド ドメインの構成設定を確認します。 ウィザードの任意の手順に戻って変更を加えることができます。 これらの構成オプションを使用して一貫した方法でマネージド ドメインを別の Microsoft Entra テナントに再デプロイするには、 **自動化用のテンプレートをダウンロード**することもできます。

1. マネージド ドメインを作成するには、[ **作成**] を選択します。 ドメイン サービス管理が作成されると、DNS 名や仮想ネットワークなどの特定の構成オプションを変更できないことを示すメモが表示されます。 続行するには、 **[OK]** を選択します。

    [Image: マネージド ドメインの構成オプションのスクリーンショット。]
2. マネージド ドメインをプロビジョニングするプロセスには、最大 1 時間かかる場合があります。 Domain Services のデプロイの進行状況を示す通知がポータルに表示されます。
3. マネージド ドメインが完全にプロビジョニングされると、[ **概要** ] タブにドメインの状態が *[実行中*] と表示されます。 仮想ネットワークやネットワーク リソース グループなどのリソースへのリンクのデプロイの **詳細** を展開します。

    [Image: マネージド ドメインのデプロイの詳細のスクリーンショット。]

Von Bedeutung

マネージド ドメインは、Microsoft Entra ディレクトリに関連付けられています。 プロビジョニング プロセス中に、Domain Services は、Domain *Controller Services* と *AzureActiveDirectoryDomainControllerServices* という名前の 2 つのエンタープライズ アプリケーションを Microsoft Entra ディレクトリに作成します。 これらのエンタープライズ アプリケーションは、マネージド ドメインにサービスを提供するために必要です。 これらのアプリケーションは削除しないでください。

### Azure 仮想ネットワークの DNS 設定を更新する

Domain Services が正常にデプロイされたら、接続されている他の VM とアプリケーションがマネージド ドメインを使用できるように仮想ネットワークを構成します。 この接続を提供するには、マネージド ドメインがデプロイされている 2 つの IP アドレスを指す仮想ネットワークの DNS サーバー設定を更新します。

1. マネージド ドメインの [ **概要** ] タブには、 **いくつかの必須の構成手順**が表示されます。 最初の構成手順では、仮想ネットワークの DNS サーバー設定を更新します。 DNS 設定が正しく構成されると、この手順は表示されなくなります。

    一覧表示されているアドレスは、仮想ネットワークで使用するドメイン コントローラーです。 この例では、これらのアドレスは *10.0.1.4* と *10.0.1.5 です*。 これらの IP アドレスは、後で **[プロパティ** ] タブで確認できます。

    [Image: マネージド ドメインの [概要] ページのスクリーンショット。]
2. 仮想ネットワークの DNS サーバー設定を更新するには、[ **構成** ] ボタンを選択します。 DNS 設定は、仮想ネットワークに対して自動的に構成されます。

ヒント

前の手順で既存の仮想ネットワークを選択した場合、ネットワークに接続されているすべての VM は再起動後にのみ新しい DNS 設定を取得します。 Microsoft Entra 管理センター、Microsoft Graph PowerShell、または Azure CLI を使用して VM を再起動できます。

### Domain Services のユーザー アカウントを有効にする

マネージド ドメインでユーザーを認証するには、ドメイン サービスには、NT LAN Manager (NTLM) および Kerberos 認証に適した形式のパスワード ハッシュが必要です。 Microsoft Entra ID は、テナントの Domain Services を有効にするまで、NTLM または Kerberos 認証に必要な形式でパスワード ハッシュを生成または格納しません。 また、セキュリティ上の理由から、クリアテキスト形式のパスワード資格情報が Microsoft Entra ID に保存されることもありません。 そのため、Microsoft Entra ID では、ユーザーの既存の資格情報に基づいて、これらの NTLM やKerberos のパスワード ハッシュを自動的に生成することはできません。

注

適切に構成されると、使用可能なパスワード ハッシュがマネージド ドメインに格納されます。 マネージド ドメインを削除すると、その時点で格納されているパスワード ハッシュも削除されます。

後でマネージド ドメインを作成する場合、Microsoft Entra ID の同期された資格情報を再利用することはできません。パスワード ハッシュの同期を再構成して、パスワード ハッシュをもう一度格納する必要があります。 既にドメイン参加済みの VM またはユーザーがすぐに認証を行うことはできません。Microsoft Entra ID が、新しいマネージド ドメインにパスワード ハッシュを生成して保存する必要があります。

[Microsoft Entra Cloud 同期は Domain Services ではサポートされていません](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/cloud-sync/what-is-cloud-sync#comparison-between-azure-ad-connect-and-cloud-sync)。 ドメインに参加している VM にアクセスできるようにするには、オンプレミス ユーザーを Microsoft Entra Connect 同期を使用して同期する必要があります。 接続同期は、Windows Server 2022、Windows Server 2019、または Windows Server 2016 にインストールする必要があります。 詳細については、「 [Domain Services と Microsoft Entra Connect のパスワード ハッシュ同期プロセス](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/how-to-connect-password-hash-synchronization#password-hash-sync-process-for-azure-ad-domain-services)」を参照してください。

Microsoft Entra ID に作成されたユーザー アカウントがクラウド専用のアカウントであるか、オンプレミス ディレクトリとの間で Microsoft Entra Connect を使って同期されたアカウントであるかによって、パスワード ハッシュの生成と保存の手順は異なります。

クラウド専用ユーザー アカウントは、Microsoft Entra 管理センターまたは PowerShell を使用して Microsoft Entra ディレクトリに作成されたアカウントです。 そのようなユーザー アカウントは、オンプレミス ディレクトリとの間で同期されません。

>
> このチュートリアルでは、基本的なクラウド専用ユーザー アカウントを操作してみましょう。 Microsoft Entra Connect を使用するために必要な追加の手順の詳細については、「 [オンプレミス AD からマネージド ドメインに同期されたユーザー アカウントのパスワード ハッシュを同期する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-password-hash-sync)。

ヒント

Microsoft Entra ディレクトリにクラウドのみのユーザーと同期されたユーザーの組み合わせがある場合は、両方の手順を完了する必要があります。

クラウド専用ユーザー アカウントの場合、ユーザーは Domain Services を使用する前にパスワードを変更する必要があります。 このパスワード変更プロセスにより、Kerberos および NTLM 認証のパスワード ハッシュが生成され、Microsoft Entra ID に格納されます。 パスワードが変更されるまで、アカウントは Microsoft Entra ID から Domain Services に同期されません。 Domain Services を使用する必要があるテナント内のすべてのクラウド ユーザーのパスワードを期限切れにするか、次のサインイン時にパスワードの変更を強制するか、クラウド ユーザーにパスワードを手動で変更するように指示します。 このチュートリアルでは、ユーザー パスワードを手動で変更してみましょう。

ユーザーが自分のパスワードをリセットする前に、Microsoft Entra テナントを [セルフサービス パスワード リセット用に構成](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/tutorial-enable-sspr)する必要があります。

クラウド専用ユーザーのパスワードを変更するには、ユーザーが次の手順を実行する必要があります。

1. https://myapps.microsoft.comの Microsoft Entra ID アクセス パネル ページに移動します。
2. 右上隅で自分の名前を選択し、ドロップダウン メニューから [ **プロファイル** ] を選択します。

    [Image: プロファイルを選択する方法のスクリーンショット。]
3. [ **プロファイル** ] ページで、[ **パスワードの変更**] を選択します。
4. [ **パスワードの変更** ] ページで、既存の (古い) パスワードを入力し、新しいパスワードを入力して確認します。
5. **送信**を選択します。

新しいパスワードが Domain Services で使用できるようにパスワードを変更し、マネージド ドメインに参加しているコンピューターに正常にサインインするには、数分かかります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/domain-services/tutorial-create-instance-advanced"} -->
## チュートリアル - カスタマイズされた Microsoft Entra Domain Services マネージド ドメインを作成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance-advanced
- Service: entra-id / domain-services
- Article date: 2025-02-19
- Summary: このチュートリアルでは、カスタマイズした Microsoft Entra Domain Services マネージド ドメインを作成して構成し、Microsoft Entra 管理センターを使用して高度な構成オプションを指定する方法について説明します。

Microsoft Entra Domain Services では、Windows Server Active Directory と完全に互換性のあるマネージド ドメイン サービス (ドメイン参加、グループ ポリシー、LDAP、Kerberos または NTLM 認証など) が提供されます。 ドメイン コントローラーのデプロイ、管理、パッチの適用を自分で行わなくても、これらのドメイン サービスを使用することができます。 Domain Services は、既存の Microsoft Entra テナントと統合されます。 この統合により、ユーザーは、各自の会社の資格情報を使用してサインインすることができます。また管理者は、既存のグループとユーザー アカウントを使用してリソースへのアクセスをセキュリティで保護することができます。

ネットワークと同期 [の既定の構成オプションを使用してマネージド ドメインを作成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance) することも、これらの設定を手動で定義することもできます。 このチュートリアルでは、Microsoft Entra 管理センターを使用して Domain Services マネージド ドメインを作成および構成するための高度な構成オプションを定義する方法について説明します。

このチュートリアルでは、以下の内容を学習します。

- マネージド ドメインの DNS と仮想ネットワークの設定を構成する
- マネージド ドメインを作成する
- 管理ユーザーをドメイン管理に追加する
- パスワード ハッシュ同期を有効にする

Azure サブスクリプションをお持ちでない場合は、開始 [する前にアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

このチュートリアルを完了するには、以下のリソースと特権が必要です。

- 有効な Azure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、 [アカウントを作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- ご利用のサブスクリプションに関連付けられた Microsoft Entra テナント (オンプレミス ディレクトリまたはクラウド専用ディレクトリと同期されていること)。
    - 必要に応じて、 [Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/sign-up-organization) するか、 [Azure サブスクリプションをアカウントに関連付けます](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-subscriptions-associated-directory)。
- Domain Services を有効にするには、テナントに [アプリケーション管理者](https://learn.microsoft.com/ja-jp/azure/active-directory/roles/permissions-reference#application-administrator) と [グループ管理者](https://learn.microsoft.com/ja-jp/azure/active-directory/roles/permissions-reference#groups-administrator) の Microsoft Entra ロールが必要です。
- 必要な Domain Services リソースを作成するには、 [Domain Services 共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#domain-services-contributor) の Azure ロールが必要です。

Domain Services では必須ではありませんが、Microsoft Entra テナントの [セルフサービス パスワード リセット (SSPR) を構成](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/tutorial-enable-sspr) することをお勧めします。 ユーザーは SSPR なしでパスワードを変更できますが、パスワードを忘れてリセットする必要がある場合は、SSPR が役立ちます。

重要

マネージド ドメインを作成した後、別のサブスクリプション、リソース グループ、またはリージョンに移動することはできません。 マネージド ドメインをデプロイするときは、最も適切なサブスクリプション、リソース グループ、リージョンを選択するように注意してください。

### Microsoft Entra 管理センターにサインインする

このチュートリアルでは、Microsoft Entra 管理センターを使用してマネージド ドメインを作成して構成します。 まず、Microsoft Entra 管理センター にサインインします。

### マネージド ドメインを作成し、基本設定を構成する

**Microsoft Entra Domain Services の有効化**ウィザードを起動するには、次の手順を実行します。

1. Microsoft Entra 管理センター メニューまたは **ホーム** ページで、[ **リソースの作成**] を選択します。
2. 検索バーに *「Domain Services* 」と入力し、検索候補から *Microsoft Entra Domain Services* を選択します。
3. Microsoft Entra Domain Services ページで、[ **作成**] を選択します。 **Microsoft Entra Domain Services の有効化**ウィザードが起動します。
4. マネージド ドメインを作成する Azure **サブスクリプション** を選択します。
5. マネージド ドメインが属する **リソース グループ** を選択します。 [ **新規作成** ] を選択するか、既存のリソース グループを選択します。

マネージド ドメインを作成するときは、DNS 名を指定します。 この DNS 名を選択する際には、いくつかの考慮事項があります。

- **組み込みのドメイン名:** 既定では、ディレクトリの組み込みドメイン名 ( *.onmicrosoft.com* サフィックス) が使用されます。 インターネット経由でマネージド ドメインへのセキュリティで保護された LDAP アクセスを有効にする場合、この既定のドメインとの接続をセキュリティで保護するデジタル証明書を作成することはできません。 Microsoft は *.onmicrosoft.com* ドメインを所有しているため、証明機関 (CA) は証明書を発行しません。
- **カスタム ドメイン名:** 最も一般的な方法は、カスタム ドメイン名 (通常は既に所有していてルーティング可能な名前) を指定することです。 ルーティング可能なカスタム ドメインを使用すれば、ご利用のアプリケーションをサポートするために必要なトラフィックを正しく送信することができます。
- **ルーティング不可能なドメイン サフィックス:** 一般に、 *contoso.local* などのルーティング不可能なドメイン名サフィックスは使用しないことをお勧めします。 *.local* サフィックスはルーティング可能ではなく、DNS 解決の問題を引き起こす可能性があります。

ヒント

カスタム ドメイン名を作成する場合は、既存の DNS 名前空間に注意してください。 既存の Azure またはオンプレミスの DNS 名前空間とは別のドメイン名を使用することをお勧めします。

たとえば、 *contoso.com の既存*の DNS 名前空間がある場合は、 *aaddscontoso.com のカスタム* ドメイン名を持つマネージド ドメインを作成します。 Secure LDAP を使用する必要がある場合は、必要な証明書を生成するために、このカスタム ドメイン名を登録して所有する必要があります。

環境内の他のサービスに対して追加の DNS レコードを作成したり、環境内の既存の DNS 名前空間間に条件付き DNS フォワーダーを作成したりする必要がある場合があります。 たとえば、ルート DNS 名を使用してサイトをホストする Web サーバーを実行すると、追加の DNS エントリを必要とする名前付けの競合が発生する可能性があります。

これらのチュートリアルとハウツー記事では、 *aaddscontoso.com* のカスタム ドメインを簡単な例として使用します。 すべてのコマンドで、独自のドメイン名を指定します。

次の DNS 名の制限も適用されます。

- **ドメイン プレフィックスの制限:** プレフィックスが 15 文字を超えるマネージド ドメインを作成することはできません。 指定したドメイン名のプレフィックス (*aaddscontoso.com* ドメイン名の *aaddscontoso* など) には、15 文字以下を含める必要があります。
- **ネットワーク名の競合:**マネージド ドメインの DNS ドメイン名が仮想ネットワークにまだ存在しないようにする必要があります。 具体的には、名前の競合につながる次のシナリオを確認します。
    - Azure 仮想ネットワーク上に同じ DNS ドメイン名を持つ Active Directory ドメインが既にある場合。
    - マネージド ドメインを有効にする予定の仮想ネットワークに、オンプレミス ネットワークとの VPN 接続がある場合。 このシナリオでは、オンプレミス ネットワークに同じ DNS ドメイン名を持つドメインがないことを確認します。
    - Azure 仮想ネットワーク上にその名前を持つ既存の Azure クラウド サービスがある場合。

マネージド ドメインを作成するには、Microsoft Entra 管理センターの [ *基本* ] ウィンドウのフィールドに入力します。

1. 前の点を考慮して、マネージド ドメインの **DNS ドメイン名** を入力します。
2. マネージド ドメインを作成する Azure **の場所** を選択します。 可用性ゾーンをサポートするリージョンを選択した場合、追加の冗長性のために Domain Services リソースがゾーン間で分散されます。

    ヒント

    Availability Zones は、Azure リージョン内の一意の物理的な場所です。 それぞれのゾーンは、独立した電源、冷却手段、ネットワークを備えた 1 つまたは複数のデータセンターで構成されています。 回復性を確保するため、有効になっているリージョンにはいずれも最低 3 つのゾーンが別個に存在しています。

    Domain Services を複数のゾーンに分散するために、ご自身で構成するものは何もありません。 Azure プラットフォームでは、ゾーンへのリソース分散が自動的に処理されます。 詳細とリージョンの可用性については、「[Azure の Availability Zones とは」](https://learn.microsoft.com/ja-jp/azure/reliability/availability-zones-overview)を参照してください。
3. **SKU** によって、パフォーマンスとバックアップの頻度が決まります。 ビジネスの需要や要件が変化した場合は、マネージド ドメインの作成後に SKU を変更できます。 詳細については、「 [Domain Services SKU の概念](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/administration-concepts#azure-ad-ds-skus)」を参照してください。

    このチュートリアルでは、 *Standard* SKU を選択します。
4. *フォレスト*は、1 つ以上のドメインをグループ化するために Active Directory Domain Services によって使用される論理コンストラクトです。

    [Image: Microsoft Entra Domain Services マネージド ドメインの基本設定を構成する]
5. 追加のオプションを手動で構成するには、[ **次へ] - [ネットワーク**] を選択します。 それ以外の場合は、[ **確認と作成** ] を選択して既定の構成オプションを受け入れてから、「 マネージド ドメインをデプロイする」セクションに進みます。 この作成オプションを選択すると、次の既定値が構成されます。

    - *10.0.1.0/24* の IP アドレス範囲を使用する *aadds-vnet* という名前の仮想ネットワークを作成します。
    - *10.0.1.0/24* の IP アドレス範囲を使用して*、aadds-subnet* という名前のサブネットを作成します。
    - Microsoft Entra ID *のすべての* ユーザーをマネージド ドメインに同期します。

### 仮想ネットワークを作成して構成する

接続を提供するには、Azure 仮想ネットワークと専用サブネットが必要です。 Domain Services は、この仮想ネットワーク サブネットで有効になっています。 このチュートリアルでは仮想ネットワークを作成しますが、代わりに既存の仮想ネットワークを使用することもできます。 どちらの方法でも、Domain Services で使用する専用サブネットを作成する必要があります。

ヒント

Microsoft Entra Domain Services デプロイ IP は、それが存在する VNET の DNS リゾルバーとして使用する必要があるため、別の DNS サービスを使用し、Microsoft Entra Domain Services 自体で条件付きフォワーダーを構成する場合は、専用の Azure 仮想ネットワークをお勧めします。

この専用仮想ネットワーク サブネットの考慮事項には、次の領域があります。

- Domain Services リソースをサポートするには、サブネットのアドレス範囲に少なくとも 3 ~ 5 個の使用可能な IP アドレスが必要です。
- Domain Services をデプロイするための *ゲートウェイ* サブネットを選択しないでください。 Domain Services を *ゲートウェイ* サブネットにデプロイすることはサポートされていません。
- 他の仮想マシンをサブネットにデプロイしないでください。 アプリケーションと VM は、多くの場合、ネットワーク セキュリティ グループを使用して接続をセキュリティで保護します。 これらのワークロードを別のサブネットで実行すると、マネージド ドメインへの接続を中断することなく、これらのネットワーク セキュリティ グループを適用できます。

仮想ネットワークを計画および構成する方法の詳細については、 [Microsoft Entra Domain Services のネットワークに関する考慮事項を](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/network-considerations)参照してください。

*[ネットワーク*] ウィンドウのフィールドに次のように入力します。

1. [ **ネットワーク** ] ページで、ドメイン サービスをデプロイする仮想ネットワークをドロップダウン メニューから選択するか、[ **新規作成**] を選択します。

    1. 仮想ネットワークを作成する場合は、 *myVnet* などの仮想ネットワークの名前を入力し、アドレス範囲 ( *10.0.1.0/24* など) を指定します。
    2. *DomainServices* などの明確な名前を持つ専用サブネットを作成します。 *10.0.1.0/24* などのアドレス範囲を指定します。

    [Image: Microsoft Entra Domain Services で使用する仮想ネットワークとサブネットを作成する]

    プライベート IP アドレス範囲内のアドレス範囲を選択してください。 所有していない IP アドレス範囲がパブリック アドレス空間にある場合、Domain Services 内でエラーが発生します。
2. *DomainServices* などの仮想ネットワーク サブネットを選択します。
3. 準備ができたら、[ **次へ] - [管理**] を選択します。

### 管理グループを構成する

ドメイン サービス ドメインの管理には *、AAD DC Administrators* という名前の特別な管理グループが使用されます。 このグループのメンバーには、マネージド ドメインにドメイン参加している VM に対する管理アクセス許可が付与されます。 ドメインに参加している VM では、このグループはローカル管理者グループに追加されます。 このグループのメンバーは、リモート デスクトップを使用して、ドメインに参加している VM にリモート接続することもできます。

重要

Domain Services を使用するマネージド ドメインに対する *ドメイン管理者* または *エンタープライズ管理者* のアクセス許可がありません。 サービスはこれらのアクセス許可を予約し、テナント内のユーザーがアクセスできないようにします。

代わりに、 *AAD DC Administrators* グループを使用すると、いくつかの特権操作を実行できます。 これらの操作には、ドメインに参加している VM の管理グループへの属、グループ ポリシーの構成が含まれます。

ウィザードによって、Microsoft Entra ディレクトリに *AAD DC Administrators* グループが自動的に作成されます。 Microsoft Entra ディレクトリにこの名前の既存のグループがある場合、ウィザードはこのグループを選択します。 必要に応じて、展開プロセス中にこの *AAD DC Administrators* グループにユーザーを追加することもできます。 これらの手順は、後で完了できます。

1. この *AAD DC Administrators* グループにユーザーを追加するには、[ **グループ メンバーシップの管理**] を選択します。

    [Image: AAD DC Administrators グループのグループ メンバーシップを構成する]
2. [ **メンバーの追加** ] ボタンを選択し、Microsoft Entra ディレクトリからユーザーを検索して選択します。 たとえば、自分のアカウントを検索し、 *AAD DC Administrators* グループに追加します。
3. 必要に応じて、注意が必要なマネージド ドメインにアラートがある場合は、通知の受信者を変更または追加します。
4. 準備ができたら、[ **次へ] - [同期**] を選択します。

### 同期の構成

Domain Services を使用すると、Microsoft Entra ID で使用できる *すべての* ユーザーとグループを同期したり、特定のグループのみの *スコープ* 同期を同期したりできます。 今すぐ、またはマネージド ドメインがデプロイされたら、同期スコープを変更できます。 詳細については、「 [Microsoft Entra Domain Services スコープの同期」を](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/scoped-synchronization)参照してください。

1. このチュートリアルでは、 **すべての** ユーザーとグループを同期することを選択します。 この同期の選択が既定のオプションです。

    [Image: Microsoft Entra ID からユーザーとグループの完全同期を実行する]
2. [ **確認と作成**] を選択します。

### マネージド ドメインをデプロイする

ウィザードの **[概要** ] ページで、マネージド ドメインの構成設定を確認します。 ウィザードの任意の手順に戻って変更を加えることができます。 これらの構成オプションを使用して一貫した方法でマネージド ドメインを別の Microsoft Entra テナントに再デプロイするには、 **自動化用のテンプレートをダウンロード**することもできます。

1. マネージド ドメインを作成するには、[ **作成**] を選択します。 ドメイン サービス管理が作成された後は、DNS 名や仮想ネットワークなどの特定の構成オプションを変更できないことを示すメモが表示されます。 続行するには、[ **OK] を選択します**。
2. マネージド ドメインをプロビジョニングするプロセスには、最大 1 時間かかる場合があります。 Domain Services のデプロイの進行状況を示す通知がポータルに表示されます。 通知を選択すると、デプロイの詳細な進行状況が表示されます。

    [Image: 進行中の展開の Microsoft Entra 管理センターでの通知]
3. *myResourceGroup* などのリソース グループを選択し、*aaddscontoso.com などの Azure* リソースの一覧からマネージド ドメインを選択します。 [ **概要** ] タブには、マネージド ドメインが現在 *デプロイ中*であることが表示されます。 マネージド ドメインは、完全にプロビジョニングされるまで構成できません。

    [Image: プロビジョニング状態中の Domain Services の状態]
4. マネージド ドメインが完全にプロビジョニングされると、[ **概要** ] タブにドメインの状態が *[実行中*] と表示されます。

    [Image: 正常にプロビジョニングされた Domain Services の状態]

重要

マネージド ドメインは、Microsoft Entra テナントに関連付けられています。 プロビジョニング プロセス中に、Domain Services は、Domain *Controller Services* と *AzureActiveDirectoryDomainControllerServices* という名前の 2 つのエンタープライズ アプリケーションを Microsoft Entra テナントに作成します。 これらのエンタープライズ アプリケーションは、マネージド ドメインにサービスを提供するために必要です。 これらのアプリケーションは削除しないでください。

### Azure 仮想ネットワークの DNS 設定を更新する

Domain Services が正常にデプロイされたら、接続されている他の VM とアプリケーションがマネージド ドメインを使用できるように仮想ネットワークを構成します。 この接続を提供するには、マネージド ドメインがデプロイされている 2 つの IP アドレスを指す仮想ネットワークの DNS サーバー設定を更新します。

1. マネージド ドメインの [ **概要** ] タブには、 **いくつかの必須の構成手順**が表示されます。 最初の構成手順では、仮想ネットワークの DNS サーバー設定を更新します。 DNS 設定が正しく構成されると、この手順は表示されなくなります。

    一覧表示されているアドレスは、仮想ネットワークで使用するドメイン コントローラーです。 この例では、これらのアドレスは *10.0.1.4* と *10.0.1.5 です*。 これらの IP アドレスは、後で **[プロパティ** ] タブで確認できます。

    [Image: Microsoft Entra Domain Services IP アドレスを使用して仮想ネットワークの DNS 設定を構成する]
2. 仮想ネットワークの DNS サーバー設定を更新するには、[ **構成** ] ボタンを選択します。 DNS 設定は、仮想ネットワークに対して自動的に構成されます。

ヒント

前の手順で既存の仮想ネットワークを選択した場合、ネットワークに接続されているすべての VM は再起動後にのみ新しい DNS 設定を取得します。 Microsoft Entra 管理センター、Microsoft [Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview)、または Azure CLI を使用して VM を再起動できます。

### Domain Services のユーザー アカウントを有効にする

マネージド ドメインでユーザーを認証するには、ドメイン サービスには、NT LAN Manager (NTLM) および Kerberos 認証に適した形式のパスワード ハッシュが必要です。 Microsoft Entra ID は、テナントの Domain Services を有効にするまで、NTLM または Kerberos 認証に必要な形式でパスワード ハッシュを生成または格納しません。 また、セキュリティ上の理由から、クリアテキスト形式のパスワード資格情報が Microsoft Entra ID に保存されることもありません。 そのため、Microsoft Entra ID では、ユーザーの既存の資格情報に基づいて、これらの NTLM やKerberos のパスワード ハッシュを自動的に生成することはできません。

注

適切に構成されると、使用可能なパスワード ハッシュがマネージド ドメインに格納されます。 マネージド ドメインを削除すると、その時点で格納されているパスワード ハッシュも削除されます。

後でマネージド ドメインを作成する場合、Microsoft Entra ID の同期された資格情報を再利用することはできません。パスワード ハッシュの同期を再構成して、パスワード ハッシュをもう一度格納する必要があります。 以前はドメインに参加していた VM またはユーザーはすぐに認証することはできません。Microsoft Entra ID は、新しいマネージド ドメインにパスワード ハッシュを生成して格納する必要があります。

詳細については、「 [Domain Services と Microsoft Entra Connect のパスワード ハッシュ同期プロセス](https://learn.microsoft.com/ja-jp/azure/active-directory/hybrid/connect/how-to-connect-password-hash-synchronization#password-hash-sync-process-for-azure-ad-domain-services)」を参照してください。

これらのパスワード ハッシュを生成して格納する手順は、次の 2 種類のユーザー アカウントで異なります。

- Microsoft Entra ID で作成されたクラウド専用ユーザー アカウント。
- Microsoft Entra Connect を使用してオンプレミスディレクトリから同期されるユーザー アカウント。

クラウド専用ユーザー アカウントは、Microsoft Entra 管理センターまたは Microsoft Graph PowerShell コマンドレットを使用して Microsoft Entra ディレクトリに作成されたアカウントです。 そのようなユーザー アカウントは、オンプレミス ディレクトリとの間で同期されません。

このチュートリアルでは、基本的なクラウド専用ユーザー アカウントを操作してみましょう。 Microsoft Entra Connect を使用するために必要な追加の手順の詳細については、「 [オンプレミス AD からマネージド ドメインに同期されたユーザー アカウントのパスワード ハッシュを同期する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-password-hash-sync)。

ヒント

Microsoft Entra テナントにクラウド専用ユーザーとオンプレミス AD のユーザーの組み合わせがある場合は、両方の手順を実行する必要があります。

クラウド専用ユーザー アカウントの場合、ユーザーは Domain Services を使用する前にパスワードを変更する必要があります。 このパスワード変更プロセスにより、Kerberos および NTLM 認証のパスワード ハッシュが生成され、Microsoft Entra ID に格納されます。 パスワードが変更されるまで、アカウントは Microsoft Entra ID から Domain Services に同期されません。 Domain Services を使用する必要があるテナント内のすべてのクラウド ユーザーのパスワードを期限切れにするか、次のサインイン時にパスワードの変更を強制するか、クラウド ユーザーにパスワードを手動で変更するように指示します。 このチュートリアルでは、ユーザー パスワードを手動で変更してみましょう。

ユーザーが自分のパスワードをリセットする前に、Microsoft Entra テナントを [セルフサービス パスワード リセット用に構成](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/tutorial-enable-sspr)する必要があります。

クラウド専用ユーザーのパスワードを変更するには、ユーザーが次の手順を実行する必要があります。

1. https://myapps.microsoft.comの Microsoft Entra ID アクセス パネル ページに移動します。
2. 右上隅で自分の名前を選択し、ドロップダウン メニューから [ **プロファイル** ] を選択します。

    [Image: プロファイルの選択]
3. [ **プロファイル** ] ページで、[ **パスワードの変更**] を選択します。
4. [ **パスワードの変更** ] ページで、既存の (古い) パスワードを入力し、新しいパスワードを入力して確認します。
5. [ **送信] を選択します**。

新しいパスワードが Domain Services で使用できるようにパスワードを変更し、マネージド ドメインに参加しているコンピューターに正常にサインインするには、数分かかります。
<!-- /MSL-PAGE -->
