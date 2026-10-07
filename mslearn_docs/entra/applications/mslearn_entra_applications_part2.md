# Microsoft Learn — Microsoft Entra / エンタープライズアプリ・プロビジョニング・アプリプロキシ (part 2)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 28

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/on-premises-ldap-connector-configure"} -->
## LDAP ディレクトリへの Microsoft Entra のプロビジョニング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure
- Service: entra-id / app-provisioning
- Article date: 2025-04-09
- Summary: このドキュメントでは、ユーザーを LDAP ディレクトリにプロビジョニングするために Microsoft Entra ID を構成する方法について説明します。

次のドキュメントには、ユーザーを Microsoft Entra ID から LDAP ディレクトリにプロビジョニングする方法を示す構成とチュートリアル情報があります。

このドキュメントでは、ユーザーを Microsoft Entra ID から LDAP ディレクトリに自動的にプロビジョニングおよびプロビジョニング解除するために実行する必要がある手順について説明します。 このドキュメントでは、LDAP ディレクトリの例として AD LDS にユーザーをプロビジョニングする方法を説明しますが、プロビジョニングは、サポート対象 (以降のセクションで説明) のあらゆる LDAP ディレクトリ サーバーに対して行うことができます。 このソリューションを使用した Active Directory Domain Services へのユーザーのプロビジョニングは、サポートされていません。

このサービスが実行する内容、仕組み、よく寄せられる質問の重要な詳細については、[Microsoft Entra ID による SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)および[オンプレミス アプリケーション プロビジョニング アーキテクチャ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture)に関する記事をご覧ください。 次のビデオでは、オンプレミス プロビジョニングの概要を紹介しています。

### LDAP ディレクトリにユーザーをプロビジョニングするための前提条件

#### オンプレミスの前提条件

- ディレクトリ サーバーを使用してユーザーにクエリを実行するアプリケーション。
- Active Directory Domain Services 以外のターゲット ディレクトリ。ユーザーを作成、更新、削除できます。 たとえば、Active Directory Lightweight Services (AD LDS) などです。 このディレクトリ インスタンスは、ユーザーを Microsoft Entra ID にプロビジョニングするのにも使用されるディレクトリにはしないでください。両方のシナリオが存在すると、Microsoft Entra Connect でループが作成される可能性があるためです。
- プロビジョニング エージェントをホストするための RAM が 3 GB 以上のコンピューター。 このコンピューターには、Windows Server 2016 以降のバージョンの Windows Server、ターゲット ディレクトリへの接続、および login.microsoftonline.com、[その他の Microsoft Online Services](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/urls-and-ip-address-ranges)、[Azure](https://learn.microsoft.com/ja-jp/azure/azure-portal/azure-portal-safelist-urls?tabs=public-cloud) ドメインへの送信接続が必要です。 1 つの例は、Azure IaaS でホストされているか、またはプロキシの背後にある Windows Server 2016 仮想マシンです。
- .NET Framework 4.7.2 をインストールする必要があります。
- 省略可能: 必須ではありませんが、[Windows Server 用の Microsoft Edge](https://www.microsoft.com/en-us/edge?r=1) をダウンロードし、Internet Explorer の代わりに使用することをお勧めします。

##### サポートされている LDAP ディレクトリ サーバー

コネクタは、さまざまな手法に基づいて LDAP サーバーを検出し、識別します。 コネクタでは、ルート DSE、ベンダー名、バージョンを使用し、スキーマを調べて LDAP サーバーに存在している既知の一意のオブジェクトと属性を見つけます。

- OpenLDAP
- Microsoft Active Directory ライトウェイト ディレクトリ サービス (AD LDS)
- 389 ディレクトリサーバー
- Apache ディレクトリ サーバー
- IBM Tivoli DS
- Isode ディレクトリ
- NetIQ eDirectory
- Novell eDirectory
- DJ を開く
- DSを開く
- Oracle (以前は Sun ONE) Directory Server Enterprise Edition
- RadiantOne 仮想ディレクトリサーバー (VDS)

詳細については、「[汎用 LDAP コネクタ リファレンス](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/reference/microsoft-identity-manager-2016-connector-genericldap)」を参照してください。

#### クラウドの要件

- Microsoft Entra ID P1 あるいは Premium P2 (または EMS E3 または E5) を使用する、Microsoft Entra テナント。

    この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 自分の要件に適したライセンスを探すには、「[一般提供されている Microsoft Entra ID の機能の比較](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)」を参照してください。
- プロビジョニング エージェントを構成するためのハイブリッド ID 管理者ロールと、Azure portal でプロビジョニングを構成するためのアプリケーション管理者またはクラウド アプリケーション管理者ロール。
- LDAP ディレクトリにプロビジョニングされる Microsoft Entra ユーザーには、ディレクトリ サーバー スキーマで必要となる各ユーザーに固有の属性が既に設定されている必要があります。 たとえば、POSIX ワークロードをサポートするために、ディレクトリ サーバーで各ユーザーがユーザー ID 番号として 10000 から 30000 までの一意の番号を持つ必要がある場合は、ユーザーの既存の属性からその番号を生成するか、Microsoft Entra スキーマを拡張し、LDAP ベースのアプリケーションのスコープ内のユーザーにその属性を設定する必要があります。 追加のディレクトリ拡張機能を作成する方法については、[Graph 拡張機能](https://learn.microsoft.com/ja-jp/graph/extensibility-overview?tabs=http#directory-azure-ad-extensions)に関する記事を参照してください。

#### 推奨事項と制限事項

次の箇条書きは、より多くの推奨事項と制限事項です。

- クラウド同期とオンプレミス アプリのプロビジョニングに同じエージェントを使用することは、推奨されません。 クラウド同期には別のエージェントを使用し、もう 1 つはオンプレミス アプリのプロビジョニングに使用をお勧めします。
- 現時点AD LDS、ユーザーをパスワードでプロビジョニングすることはできません。 そのため、AD LDS のパスワード ポリシーを無効にするか、ユーザーを無効状態でプロビジョニングする必要があります。
- 他のディレクトリ サーバーの場合、初期のランダムなパスワードを設定できますが、Microsoft Entra ユーザーのパスワードをディレクトリ サーバーにプロビジョニングすることはできません。
- Microsoft Entra ID から Active Directory Domains Services へのユーザーのプロビジョニングは、サポートされていません。
- LDAP から Microsoft Entra ID へのユーザーのプロビジョニングは、サポートされていません。
- ディレクトリ サーバーへのグループとユーザー メンバーシップのプロビジョニングは、サポートされていません。

### 実行プロファイルの選択

ディレクトリ サーバーと対話するようにコネクタの構成を作成する場合は、まず、コネクタがディレクトリのスキーマを読み取るように構成し、そのスキーマを Microsoft Entra ID のスキーマにマップしてから、実行プロファイルを介してコネクタが継続的に使用するアプローチを構成します。 構成する各実行プロファイルは、ディレクトリ サーバーからデータをインポートまたはエクスポートするためにコネクタが LDAP 要求を生成する方法を指定します。 コネクタを既存のディレクトリ サーバーにデプロイする前に、組織内のディレクトリ サーバー オペレーターと、そのディレクトリ サーバーで行なわれる操作のパターンについて話し合う必要があります。

- 構成が完了すると、プロビジョニング サービスが開始する際、**完全インポート**実行プロファイルで構成された相互作用が自動的に実行されます。 この実行プロファイルでは、コネクタは、LDAP 検索操作を使用して、ディレクトリからのユーザーのすべてのレコードを読み取ります。 この実行プロファイルは、後で Microsoft Entra ID がユーザーに変更を加える必要がある場合に、そのユーザーに対して新しいオブジェクトを作成するのではなく、ディレクトリ内のそのユーザーの既存のオブジェクトを更新するために必要になります。
- 新しいユーザーをアプリケーションに割り当てる場合や既存のユーザーを更新する場合など、Microsoft Entra ID で変更が行われるたびに、プロビジョニング サービスによって、**エクスポート**実行プロファイルで LDAP 相互のやり取りが実行されます。 **エクスポート**実行プロファイルでは、ディレクトリの内容を Microsoft Entra ID と同期させるために、Microsoft Entra ID によって LDAP 要求が発行され、ディレクトリ内のオブジェクトに対する追加、変更、削除、または名前の変更が行われます。
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
| 新しいユーザーに設定する属性 | 構成ウィザードの **[属性の選択]** ページと Azure portal の **[プロビジョニング]** ページの属性マッピング | AD LDS の場合は `msDS-UserAccountDisabled`、`userPrincipalName`、`displayName`。OpenLDAP の場合は `cn`、`gidNumber`、`homeDirectory`、`mail`、`objectClass`、`sn`、`uid`、`uidNumber`、`userPassword` |
| ディレクトリ サーバーに必要な名前付け階層 | Azure portal の **[プロビジョニング]** ページの属性マッピング | 新しく作成したユーザーの DN を、AD LDS の場合は `CN=CloudUsers,CN=App,DC=Contoso,DC=lab`、OpenLDAP の場合は `DC=Contoso,DC=lab` の直下に設定します |
| Microsoft Entra ID とディレクトリ サーバー間でユーザーを関連付けするための属性 | Azure portal の **[プロビジョニング]** ページの属性マッピング | AD LDS の場合、この例は最初の空のディレクトリ用であるため構成されていません。OpenLDAPの場合は、`mail` |
| ユーザーが Microsoft Entra ID でスコープから外れる場合のプロビジョニング解除の動作 | 構成ウィザードの **[プロビジョニング解除]** ページ | ディレクトリ サーバーからユーザーを削除します |

ディレクトリ サーバーのネットワーク アドレスは、ホスト名と TCP ポート番号 (通常はポート 389 または 636) です。 ディレクトリ サーバーが同じ Windows サーバー上のコネクタと併置されている場合またはネットワーク レベルのセキュリティを使用している場合を除き、コネクタからディレクトリ サーバーへのネットワーク接続は SSL または TLS を使用して保護する必要があります。 コネクタでは、ポート 389 でのディレクトリ サーバーへの接続と、セッション内での TLS の有効化に Start TLS を使用することがサポートされています。 このコネクタでは、LDAPS - LDAP over TLS でのポート 636 上のディレクトリ サーバーへの接続もサポートされています。

ディレクトリ サーバーに認証を行うためのコネクタの識別済みのアカウントが、ディレクトリ サーバーで既に構成されている必要があります。 このアカウントは通常、識別名で識別され、関連付けられたパスワードまたはクライアント証明書を持ちます。 接続されたディレクトリ内のオブジェクトに対してインポートおよびエクスポート操作を実行するには、そのディレクトリのアクセス制御モデル内で十分なアクセス許可がコネクタのアカウントになければなりません。 コネクタは、エクスポートするためには**書き込み**アクセス許可を、インポートするためには**読み取り**アクセス許可を備えている必要があります。 アクセス許可は、ターゲット ディレクトリ自体の管理エクスペリエンス内で構成されます。

ディレクトリ スキーマでは、ディレクトリ内の実際のエンティティを表すオブジェクト クラスと属性を指定します。 コネクタでは、`inetOrgPerson` などの構造型オブジェクト クラスで表されるユーザーと、省略可能な追加の補助オブジェクト クラスがサポートされます。 コネクタがディレクトリ サーバーにユーザーをプロビジョニングできるようにするには、Azure Portal の構成時に、Microsoft Entra スキーマからすべての必須属性へのマッピングを定義します。 これには、構造型オブジェクト クラスの必須属性、その構造型オブジェクト クラスのスーパークラス、補助オブジェクト クラスの必須属性が含まれます。 さらに、これらのクラスの省略可能な属性の一部へのマッピングも構成する場合もあります。 たとえば、OpenLDAP ディレクトリ サーバーでは、新しいユーザーが次の例のような属性を持つオブジェクトが必要になる場合があります。

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

ディレクトリ サーバーによって実装されるディレクトリ階層ルールでは、各ユーザーのオブジェクトがどのように相互に関連し、ディレクトリ内の既存のオブジェクトと関連するかを記述します。 ほとんどのデプロイでは、組織はディレクトリ サーバーにフラット階層を設定することを選択しました。そこでは、各ユーザーの各オブジェクトが共通のベース オブジェクトの直下に配置されています。 たとえば、ディレクトリ サーバー内の名前付けコンテキストのベース識別名が`dc=contoso,dc=com` である場合、新しいユーザーは `cn=alice,dc=contoso,dc=com` のような識別名を持つことになります。 ただし、一部の組織には、より複雑なディレクトリ階層がある場合があります。その場合は、コネクタの識別名マッピングを指定するときにそのルールを実装する必要があります。 たとえば、ディレクトリ サーバーでは、ユーザーが部門別の組織単位に属することが想定される場合があるため、新しいユーザーには `cn=alice,ou=London,dc=contoso,dc=com` のような識別名が付けられます。 コネクタでは組織単位の中間オブジェクトが作成されないため、ディレクトリ サーバー ルール階層で想定される中間オブジェクトは、ディレクトリ サーバーに既に存在している必要があります。

次に、Microsoft Entra ユーザーに対応するディレクトリ サーバー内のユーザーが既に存在するかどうかをコネクタが判断する方法に関するルールを定義する必要があります。 すべての LDAP ディレクトリには、ディレクトリ サーバー内のオブジェクトごとに一意の識別名がありますが、多くの場合、Microsoft Entra ID のユーザーには識別名が存在しません。 その代わり、組織のディレクトリ サーバー スキーマに、`mail` や `employeeId` などの異なる属性があり、それが Microsoft Entra ID のユーザーにも存在する場合があります。 その場合、コネクタが新しいユーザーをディレクトリ サーバーにプロビジョニングする際に、コネクタで、その属性の特定の値を持つユーザーがそのディレクトリに既に存在するかどうかを検索し、存在しない場合にのみディレクトリ サーバーに新しいユーザーを作成できます。

既存のユーザーを更新または削除するだけでなく、LDAP ディレクトリに新しいユーザーを作成するシナリオの場合は、そのディレクトリ サーバーを使用するアプリケーションが認証を処理する方法も決定する必要があります。 推奨される方法は、アプリケーションが SAML、OAuth、OpenID Connect などのフェデレーションまたは SSO プロトコルを使用して Microsoft Entra ID に対する認証を行い、属性についてはディレクトリ サーバーにのみ依存することです。 従来、LDAP ディレクトリは、パスワードを確認してユーザーを認証するためにアプリケーションで使用されていましたが、このユース ケースは、多要素認証やユーザーが既に認証されている場合には不可能です。 一部のアプリケーションでは、ユーザーの SSH 公開キーや証明書をディレクトリから照会することができますが、これはユーザーが既にそれらの形式の資格情報を持っている場合に適しています。 ただし、ディレクトリ サーバーに依存するアプリケーションが最新の認証プロトコルやより強力な資格情報をサポートしていない場合、Microsoft Entra ID はユーザーの Microsoft Entra パスワードのプロビジョニングをサポートしないため、ディレクトリに新しいユーザーを作成する際にアプリケーション固有のパスワードを設定する必要があります。

最後に、プロビジョニング解除の動作に同意する必要があります。 ディレクトリに既に存在するユーザーであっても新しいユーザーであっても、コネクタが構成されており、Microsoft Entra ID 内のユーザーとディレクトリ内のユーザーの間に Microsoft Entra ID でリンクが確立されている場合は、Microsoft Entra ID で Microsoft Entra ユーザーからディレクトリに属性の変更をプロビジョニングできます。 Microsoft Entra ID でアプリケーションに割り当てられているユーザーが削除された場合、Microsoft Entra ID は削除操作をディレクトリ サーバーに送信します。 また、ユーザーがアプリケーションを使用できるスコープから外れる場合に、Microsoft Entra ID にディレクトリ サーバー内のオブジェクトを更新させることもできます。 この動作は、ディレクトリ サーバーを使用するアプリケーションによって異なります。OpenLDAP などの多くのディレクトリに、ユーザーのアカウントが無効であることを示す既定の方法がない場合があるためです。

### LDAP ディレクトリを準備する

ディレクトリ サーバーがまだなく、この機能を試したい場合は、[Microsoft Entra ID からプロビジョニングするための Active Directory Lightweight Directory Services の準備](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-prepare-directory)に関するページに、テスト AD LDS 環境を作成する方法が示されています。 既に別のディレクトリ サーバーがデプロイされている場合は、その記事をスキップして、ECMA Connector Host のインストールと構成を続行できます。

### Microsoft Entra Connect のプロビジョニング エージェントをインストールして構成する

プロビジョニング エージェントを既にダウンロードし、別のオンプレミス アプリケーション用に構成している場合は、次のセクションに進んでください。

1. Azure portal にサインインします。
2. **[エンタープライズ アプリケーション]** に移動し、**[新規アプリケーション]** を選択します。
3. **オンプレミスの ECMA アプリ** アプリケーションを検索し、アプリに名前を付けて、**[作成]** を選択してテナントに追加します。
4. メニューから、アプリケーションの **[プロビジョニング]** ページに移動します。
5. **開始する**を選択します。
6. **[プロビジョニング]** ページで、モードを **[自動]** に変更します。

[Image: [自動] を選択しているスクリーンショット。]

1. **[オンプレミス接続]** で、**[ダウンロードしてインストール]** を選択し、**[条項に同意してダウンロード]** を選択します。

[Image: エージェントのダウンロード場所のスクリーンショット。]

1. Portal を開いたままにして、プロビジョニング エージェント インストーラーを開き、サービス使用条件に同意して、**[インストール]** を選択します。
2. Microsoft Entra プロビジョニング エージェント構成ウィザードが表示されるのを待ってから、**[次へ]** を選択します。
3. **[拡張機能を選択]** ステップで、**[オンプレミス アプリケーションのプロビジョニング]** を選択し、**[次へ]** を選択します。
4. プロビジョニング エージェントは、オペレーティング システムの Web ブラウザーを使用して、Microsoft Entra ID (および場合によっては組織の ID プロバイダー) に対する認証を行うためのポップアップ ウィンドウを表示します。 Windows Server でブラウザーとして Internet Explorer を使用している場合は、JavaScript を正しく実行できるように、ブラウザーの信頼済みサイト リストに Microsoft Web サイトを追加する必要がある場合があります。
5. 承認を求められたら、Microsoft Entra 管理者の資格情報を入力します。 ユーザーは、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)ロールを持っている必要があります。
6. **[Confirm]** を選択して設定を確認します。 インストールが成功したら、**[終了]** を選択し、プロビジョニング エージェント パッケージ インストーラーも閉じます。

### オンプレミスの ECMA アプリを構成する

1. Portal に戻り、**[オンプレミス接続]** セクションで、デプロイしたエージェントを選択し、**[エージェントの割り当て]** を選択します。

    [Image: エージェントを選択して割り当てる方法を示すスクリーンショット。]
2. このブラウザー ウィンドウを開いたままにして、構成ウィザードを使用して構成の次の手順を完了します。

### Microsoft Entra ECMA Connector Host の証明書を構成する

1. プロビジョニング エージェントがインストールされている Windows Server で、スタート メニューから Microsoft ECMA2Host 構成ウィザードを右クリックし、管理者として実行します。 ウィザードで必要な Windows イベント ログを作成するには、Windows 管理者として実行する必要があります。
2. このウィザードを初めて実行する場合は、ECMA Connector Host 構成の開始後に証明書の作成を求められます。 既定のポート **8585** のままにし、**[証明書の生成]** を選択して証明書を生成します。 自動生成された証明書は、信頼ルートの一部として自己署名されます。 SAN はホスト名と一致します。 [Image: 設定の構成を示すスクリーンショット。]
3. **保存**を選択します。

Note

新しい証明書を生成することを選択した場合は、証明書の有効期限を記録した後、構成ウィザードに戻り、有効期限が切れる前に証明書を生成し直すようにしてください。

### 汎用 LDAP コネクタを構成する

選択するオプションによっては、ウィザードの一部の画面を使用できない場合があり、情報も多少異なる可能性があります。 この構成例では、AD LDS の場合は **User** オブジェクト クラスを、OpenLDAP の場合は **inetOrgPerson** オブジェクト クラスを使用してユーザーをプロビジョニングします。 次の情報を使用して構成を進めます。

1. コネクタに対して Microsoft Entra ID を認証するために使用されるシークレット トークンを生成します。 これは、アプリケーションごとに最小 12 文字で、一意である必要があります。 シークレット ジェネレーターがまだない場合は、次のような PowerShell コマンドを使用して、ランダムな文字列の例を生成できます。

    ```powershell
    -join (((48..90) + (96..122)) * 16 | Get-Random -Count 16 | % {[char]$_})
    ```
2. まだ実行していない場合は、スタート メニューから Microsoft ECMA2Host 構成ウィザードを起動します。
3. **[新しいコネクタ]** を選択します。 [Image: 新しいコネクタの選択を示すスクリーンショット。]
4. **[プロパティ]** ページでは、画像の下にある表に指定される値をボックスに入力し、 **[次へ]** を選択します。 [Image: プロパティの入力を示すスクリーンショット。]

    | プロパティ | [値] |
    | --- | --- |
    | 名前 | コネクタに対して選択した名前。これは、環境内のすべてのコネクタで一意である必要があります。 たとえば、「 `LDAP` 」のように入力します。 |
    | AutoSync タイマー (分) | 120 |
    | シークレット トークン | ここにシークレット トークンを入力します。 12 文字以上である必要があります。 |
    | 拡張 DLL | 汎用 LDAP コネクタの場合、**Microsoft.IAM.Connector.GenericLdap.dll** を選択します。 |
5. **[接続]** ページで、ECMA Connector Host がディレクトリ サーバーと通信する方法を構成し、いくつかの構成オプションを設定します。 画像の下にある表に指定される値をボックスに入力し、[**次へ**] を選択します。 **[次へ]** を選択すると、コネクタによって、ディレクトリ サーバーへの構成のクエリが実行されます。 [Image: [接続性] ページを示すスクリーンショット。]

    | プロパティ | 説明 |
    | --- | --- |
    | Host | LDAP サーバーが配置されているホスト名。 このサンプルでは、ホスト名の例として `APP3` を使用します。 |
    | 港 / ポート | TCP ポート番号。 ディレクトリ サーバーが LDAP over SSL 用に構成されている場合は、ポート 636 を使用します。 `Start TLS` の場合、またはネットワーク レベルのセキュリティを使用している場合は、ポート 389 を使用します。 |
    | [接続タイムアウト] | 180 |
    | バインド | このプロパティでは、コネクタがディレクトリ サーバーに対して認証する方法を指定します。 `Basic` の設定、または `SSL` もしくは `TLS` の設定でクライアント証明書が構成されていない場合、コネクタは LDAP シンプル バインドを送信して、識別名とパスワードで認証します。 `SSL` または `TLS` の設定でクライアント証明書を指定すると、コネクタは LDAP SASL `EXTERNAL` バインドを送信して、クライアント証明書による認証を行います。 |
    | ユーザー名 | ECMA コネクタがディレクトリ サーバーに対して自身を認証する方法。 この AD LDS のサンプルでは、ユーザー名の例は `CN=svcAccount,CN=ServiceAccounts,CN=App,DC=contoso,DC=lab` で、OpenLDAP の場合は `cn=admin,dc=contoso,dc=lab` です。 |
    | パスワード | ECMA コネクタがディレクトリ サーバーに対して自身を認証するユーザーのパスワード。 |
    | 領域/ドメイン | この設定は、ユーザーの領域/ドメインを指定するために [バインド] オプションとして `Kerberos` を選択した場合にのみ必要です。 |
    | 証書 | このセクションの設定は、[バインド] オプションとして `SSL` または `TLS` を選択した場合にのみ使用されます。 |
    | 属性の別名 | RFC4522 構文を使用してスキーマに属性を定義する場合は、 [属性の別名] テキスト ボックスを使用します。 スキーマの検出中にこれらの属性を検出することはできないため、コネクタがそれらの属性を識別できるようにサポートが必要です。 たとえば、ディレクトリ サーバーが `userCertificate;binary` を発行せず、その属性をプロビジョニングする場合、userCertificate 属性をバイナリ属性として正しく識別するには、属性エイリアス ボックスに文字列「`userCertificate;binary`」を入力する必要があります。 スキーマにない特別な属性を必要としない場合は、空白のままにすることができます。 |
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
    | 見つかった必須機能 | コネクタでは、必須のコントロールがルート DSE に存在していることも確認されます。 該当するコントロールが一覧表示されていない場合は、警告が表示されます。 LDAP ディレクトリの中には、一部の機能しかルート DSE に一覧表示しないものがあります。このため、警告が出ている場合でも、コネクタは問題なく動作する場合があります。 |
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
    | アンカー | この属性の値は、ターゲット ディレクトリ内のオブジェクトごとに一意である必要があります。 Microsoft Entra プロビジョニング サービスでは、初期サイクル後、この属性を使用して ECMA コネクタ ホストに対してクエリを実行します。 AD LDS の場合は `ObjectGUID` を使用します。他のディレクトリ サーバーの場合は、以下の表を参照してください。 識別名は `-dn-` として選択される場合があることに注意してください。 OpenLDAP スキーマの `uid` 属性など、複数値の属性をアンカーとして使用することはできません。 |
    | クエリ属性 | AD LDS がディレクトリ サーバーまたは `objectGUID` である場合、この属性はアンカーと同じである必要があります (`_distinguishedName` など)。 |
    | DN | ターゲット オブジェクトの distinguishedName。 `-dn-` を保持します。 |
    | 自動生成 | 未確認 |

    使用する LDAP サーバーとアンカーの一覧を次の表に示します。

    | ディレクトリ | アンカー |
    | --- | --- |
    | Microsoft AD LDS および AD GC | objectGUID。 `ObjectGUID` をアンカーとして使用するには、エージェント バージョン 1.1.846.0 またはそれ以降を使用している必要があります。 |
    | 389 ディレクトリサーバー | dn |
    | Apache Directory | dn |
    | IBM Tivoli DS | dn |
    | Isode ディレクトリ | dn |
    | Novell/NetIQ eDirectory | GUID |
    | DJ/DS を開く | dn |
    | Open LDAP (オープンLDAPディレクトリサービス) | dn |
    | Oracle ODSEE | dn |
    | ラディアントワン VDS | dn |
    | Sun One Directory Server | dn |
13. ECMA ホストでは、ターゲット ディレクトリでサポートされる属性を検出します。 Microsoft Entra ID に公開する属性を選択できます。 プロビジョニングのために、これらの属性を Azure portal で構成できます。 **[属性の選択]** ページで、必須属性として必要な属性、または Microsoft Entra ID からプロビジョニングする属性をすべてドロップダウン リストに 1 つずつ追加します。 [Image: [属性の選択] ページを示すスクリーンショット。]**[属性]** ドロップダウン リストには、ターゲット ディレクトリで検出され、構成ウィザードの以前の *[属性の選択]* ページでは選択**されなかった**属性が示されます。

    `Treat as single value` チェック ボックスが `objectClass` 属性でオフになっていること、`userPassword` が設定されている場合は、`userPassword` の属性で選択不可かチェックされていることを確認します。

    inetOrgPerson スキーマで OpenLDAP を使用している場合は、次の属性の可視性を構成してください。

    | 属性 | 1 つの値として扱う |
    | --- | --- |
    | cn | 年 |
    | メール | 年 |
    | オブジェクトクラス |  |
    | sn | 年 |
    | ユーザーパスワード | 年 |

    POSIX スキーマで OpenLDAP を使用している場合は、次の属性の可視性を構成してください。

    | 属性 | 1 つの値として扱う |
    | --- | --- |
    | \_distinguishedName |  |
    | -dn- |  |
    | パスワードのエクスポート |  |
    | cn | 年 |
    | gidNumber |  |
    | ホームディレクトリ |  |
    | メール | 年 |
    | オブジェクトクラス |  |
    | sn | 年 |
    | uid (ユーザー識別子) | 年 |
    | UID番号 |  |
    | ユーザーパスワード | 年 |

    関連するすべての属性が追加されたら、**[次へ]** を選択します。
14. **[プロビジョニング解除]** ページでは、ユーザーがアプリケーションの範囲外になったときに Microsoft Entra ID にディレクトリからユーザーを削除させるかどうかを指定することができます。 その場合は、**[フローの無効化]** で **[削除]** を選択し、**[フローの削除]** で **[削除]** を選択します。 `Set attribute value` が選択されている場合は、前のページで選択した属性を [プロビジョニング解除] ページで選択することはできません。

Note

**[Set attribute value](Set 属性値)**を使用すると、ブール値のみが許可されることに注意してください。

1. **完了** を選択します。

### ECMA2Host サービスが実行されていて、ディレクトリ サーバーから読み込めることを確認する

次の手順に従って、コネクタ ホストが起動し、ディレクトリ サーバーからコネクタ ホストに既存のユーザーが読み込まれたことを確認します。

1. Microsoft Entra ECMA Connector Host を実行しているサーバーで、**[開始]** をクリックします。
2. **[実行]** を必要に応じて選択し、ボックスに「**services.msc**」と入力します。
3. **サービス** リストで、**Microsoft ECMA2Host** が確実に存在し、実行中であるようにします。 実行されていない場合は、**[開始]** を選択します。 [Image: サービスが稼働中であることを示すスクリーンショット。]
4. 最近サービスを開始し、ディレクトリ サーバーに多数のユーザー オブジェクトがある場合は、コネクタがディレクトリ サーバーとの接続を確立するまで数分待ってください。
5. Microsoft Entra ECMA Connector Host を実行しているサーバーで、PowerShell を起動します。
6. ECMA ホストがインストールされたフォルダー (`C:\Program Files\Microsoft ECMA2Host` など) に移動します。
7. サブディレクトリ `Troubleshooting` に移動します。
8. 以下の例で示すように、そのディレクトリでスクリプト `TestECMA2HostConnection.ps1` を実行し、コネクタ名と `ObjectTypePath` 値 `cache` を引数として指定します。 コネクタ ホストが TCP ポート 8585 でリッスンしていない場合は、`-Port` 引数の指定も必要になる場合があります。 メッセージが表示されたら、そのコネクタ用に構成されたシークレット トークンを入力します。

    ```
    PS C:\Program Files\Microsoft ECMA2Host\Troubleshooting> $cout = .\TestECMA2HostConnection.ps1 -ConnectorName LDAP -ObjectTypePath cache; $cout.length -gt 9
    Supply values for the following parameters:
    SecretToken: ************
    ```
9. スクリプトにエラーまたは警告メッセージが表示される場合は、サービスが実行されていることを確認し、コネクタ名とシークレット トークンが構成ウィザードで構成した値と一致していることを確認します。
10. スクリプトに出力 `False` が表示される場合、コネクタは既存のユーザーのソース ディレクトリ サーバー内のエントリを認識していません。 これが新しいディレクトリ サーバーのインストールである場合、この動作は予期されたものであり、次のセクションに進むことができます。
11. ただし、ディレクトリ サーバーに既に 1 人以上のユーザーが含まれているにもかかわらず、スクリプトは `False` を表示している場合、この状態はコネクタがディレクトリ サーバーから読み取れなかったことを示します。 プロビジョニングを試みた場合、Microsoft Entra ID は、そのソース ディレクトリ内のユーザーと Microsoft Entra ID のユーザーを正しくマッチングできない可能性があります。 コネクタ ホストが既存のディレクトリ サーバーからのオブジェクトの読み取りを完了するまで数分待ってから、スクリプトを再実行します。 出力が引き続き `False` である場合は、コネクタの構成とディレクトリ サーバーのアクセス許可によってコネクタが既存のユーザーを読み取れるようにしていることを確認します。

### Microsoft Entra ID からコネクタ ホストへの接続をテストする

1. ポータルでアプリケーションのプロビジョニングを構成していた Web ブラウザー ウィンドウに戻ります。

    Note

    ウィンドウがタイムアウトした場合は、エージェントを再度選択する必要があります。

    1. Azure portal にサインインします。
    2. **[エンタープライズ アプリケーション]** の **[On-premises ECMA app](オンプレミス ECMA アプリ)** アプリケーションに移動します。
    3. **[プロビジョニング]** をクリックします。
    4. **[概要]** が表示された場合は、モードを **[自動]** に変更し、**[オンプレミス接続]** セクションで、デプロイしたエージェントを選択し、**[エージェントの割り当て]** を選択して 10 分間待ちます。 それ以外の場合は、**[プロビジョニングの編集]** に移動します。
2. **[管理者資格情報]** セクションで、次の URL を入力します。 `connectorName` の部分を ECMA ホスト上のコネクタの名前 (`LDAP` など) に置き換えます。 ECMA ホストの証明機関の証明書を提供した場合は、`localhost` を ECMA ホストがインストールされているサーバーのホスト名に置き換えます。

    | プロパティ | [値] |
    | --- | --- |
    | テナントの URL | https://localhost:8585/ecma2host\_connectorName/scim |
3. コネクタの作成時に定義した**シークレット トークン**の値を入力します。

    Note

    アプリケーションにエージェントを割り当てたばかりの場合は、登録が完了するまで 10 分間お待ちください。 登録が完了するまで、接続テストは機能しません。 サーバーでプロビジョニング エージェントを再起動してエージェントの登録を強制的に完了すると、登録プロセスを高速化することができます。 サーバーに移動し、Windows 検索バーで**サービス**を検索し、**Microsoft Entra Connect プロビジョニング エージェント** サービスを特定し、サービスを右クリックして再起動します。
4. **[接続のテスト]** をクリックし、1 分待ちます。
5. 接続テストが成功し、指定された資格情報が認可されてプロビジョニングが有効になったことが示されたら、**[保存]** を選択します。[Image: エージェントのテストを示すスクリーンショット。]

### Microsoft Entra スキーマを拡張する (省略可能)

ディレクトリ サーバーで、ユーザーの既定の Microsoft Entra スキーマに含まれていない追加の属性が必要な場合は、プロビジョニング時に、定数から、または他の Microsoft Entra 属性から変換された式から、あるいは Microsoft Entra スキーマを拡張することで、これらの属性の値を指定するように構成できます。

OpenLDAP POSIX スキーマの場合の `uidNumber` のような属性があることがディレクトリ サーバーによってユーザーに要求され、その属性がユーザーの Microsoft Entra スキーマにまだ含まれておらず、ユーザーごとに一意である必要がある場合は、[式](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)を介してユーザーの他の属性からその属性を生成するか、[ディレクトリ拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping)を使用してその属性を拡張機能として追加する必要があります。

ユーザーが Active Directory Domain Services で生成され、そのディレクトリに属性がある場合は、Microsoft Entra Connect または Microsoft Entra Connect クラウド同期を使用することで、その属性を Active Directory Domain Services から Microsoft Entra ID に同期して、他のシステムへのプロビジョニングに使用できるように構成できます。

ユーザーが Microsoft Entra ID で生成された場合、ユーザーに格納する必要がある新しい属性ごとに、[ディレクトリ拡張機能を定義する](https://learn.microsoft.com/ja-jp/graph/extensibility-overview?tabs=http#define-the-directory-extension)必要があります。 次に、プロビジョニングする予定の [Microsoft Entra ユーザーを更新して](https://learn.microsoft.com/ja-jp/graph/extensibility-overview?tabs=http#update-or-delete-directory-extensions)、各ユーザーにこれらの属性の値を付与します。

### 属性マッピングを構成する

このセクションでは、Microsoft Entra ユーザーの属性と、ECMA ホスト構成ウィザードで以前に選択した属性との間のマッピングを構成します。 後でコネクタがディレクトリ サーバーにオブジェクトを作成すると、Microsoft Entra ユーザーの属性がコネクタを介してディレクトリ サーバーに送信され、その新しいオブジェクトの一部になります。

1. Microsoft Entra 管理センターの **[エンタープライズ アプリケーション]** で、**[オンプレミスの ECMA アプリ]** アプリケーションを選択し、**[プロビジョニング]** ページを選択します。
2. **[プロビジョニングの編集]** を選択します。
3. **[マッピング]** を展開し、**[Microsoft Entra ユーザーのプロビジョニング]** を選択します。 このアプリケーションの属性マッピングを初めて構成した場合、プレースホルダーのマッピングは 1 つだけ存在します。
4. ディレクトリ サーバーのスキーマが Microsoft Entra ID で使用可能であることを確認するには、**[詳細オプションの表示]** チェック ボックスをオンにし、**[ScimOnPremises の属性リストの編集]** を選択します。 構成ウィザードで選択したすべての属性が一覧表示されていることを確認します。 そうでない場合は、スキーマが更新されるまで数分待ってから、ナビゲーション行で **[属性マッピング]** を選択し、**[ScimOnPremises の属性リストの編集]** をもう一度選択してページを再読み込みします。 属性が一覧表示されたら、このページをキャンセルしてマッピングの一覧に戻ります。
5. ディレクトリ内のすべてのユーザーが一意の識別名を持っている必要があります。 属性マッピングを使用して、コネクタで識別名を構築する方法を指定できます。 **[新しいマッピングの追加]** を選択します。 以下の例に示された値を使用してマッピングを作成し、式内の識別名を、組織単位またはターゲット ディレクトリ内の他のコンテナーの識別名と一致するように変更します。

    - マッピングの種類: 式
    - 式 (AD LDS へのプロビジョニングの場合): `Join("", "CN=", Word([userPrincipalName], 1, "@"), ",CN=CloudUsers,CN=App,DC=Contoso,DC=lab")`
    - 式 (OpenLDAP へのプロビジョニングの場合): `Join("", "CN=", Word([userPrincipalName], 1, "@"), ",DC=Contoso,DC=lab")`
    - ターゲット属性: `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:-dn-`
    - このマッピングを適用する: オブジェクトの作成時のみ
6. ディレクトリ サーバーで `objectClass` 属性に複数の構造型オブジェクト クラス値または補助オブジェクト クラス値を指定する必要がある場合は、その属性にマッピングを追加します。 AD LDS へのプロビジョニングのこの例では `objectClass` のマッピングは必要ありませんが、他のディレクトリ サーバーや他のスキーマでは必要な場合があります。 `objectClass` のマッピングを追加するには、**[新しいマッピングの追加]** を選択します。 次の例に示された値を使用してマッピングを作成し、式のオブジェクト クラス名をターゲット ディレクトリ スキーマの名前と一致するように変更します。

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

    POSIX スキーマを使用する OpenLDAP の場合は、`gidNumber`、`homeDirectory`、`uid` および `uidNumber` の属性も指定する必要があります。 各ユーザーには、一意の `uid` と `uidNumber` が必要です。 通常、`homeDirectory` はユーザーの userID から派生した式で設定されます。 たとえば、ユーザーの `uid` がユーザー プリンシパル名から派生した式で生成される場合、そのユーザーのホーム ディレクトリの値は、ユーザー プリンシパル名から派生した同様の式で生成される可能性があります。 また、ユース ケースによっては、すべてのユーザーを同じグループに含めて、定数から `gidNumber` を割り当てる必要がある場合があります。

    | マッピングの種類 | ソース属性 | ターゲット属性 |
    | --- | --- | --- |
    | 表現 | `ToLower(Word([userPrincipalName], 1, "@"), )` | `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:uid` |
    | 直接 | (ディレクトリに固有の属性) | `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:uidNumber` |
    | 表現 | `Join("/", "/home", ToLower(Word([userPrincipalName], 1, "@"), ))` | `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:homeDirectory` |
    | 常時 | `10000` | `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:gidNumber` |
10. AD LDS 以外のディレクトリにプロビジョニングする場合は、ユーザーの初期ランダム パスワードを設定する `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:userPassword` にマッピングを追加します。 AD LDS の場合、**userPassword** 向けのマッピングはありません。
11. **保存**を選択します。

### アプリケーションにプロビジョニングされたユーザーが Microsoft Entra ID に必須の属性を持っていることを確認する

LDAP ディレクトリに既存のユーザー アカウントを持つユーザーがいる場合は、Microsoft Entra ユーザー表現に照合に必要な属性があることを確認する必要があります。

LDAP ディレクトリに新しいユーザーを作成する予定がある場合は、それらのユーザーの Microsoft Entra 表現に、ターゲット ディレクトリのユーザー スキーマに必要なソース属性があることを確認する必要があります。

[Microsoft Graph PowerShell コマンドレット](https://www.powershellgallery.com/packages/Microsoft.Graph)を使用すると、必要な属性についてのユーザー チェックを自動化できます。

たとえば、プロビジョニングでユーザーに `DisplayName`、`surname`、`extension_656b1c479a814b1789844e76b2f459c3_MyNewProperty` の 3 つの属性が必要だとします。 `Get-MgUser` コマンドレットを使用することで、各ユーザーを取得して、必要な属性が存在するかどうかを確認できます。 Graph v1.0 `Get-MgUser` コマンドレットでは、返すプロパティの 1 つとして要求で属性が指定されていない限り、既定ではユーザーのディレクトリ拡張属性は返されません。

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

    たとえば、Windows で AD LDS `csvde` プログラムを使用して次のコマンドを実行すると、ディレクトリ内のすべてのユーザーの `userPrincipalName` 属性を含む CSV ファイルが、現在のファイル システム ディレクトリ内に生成されます。

    ```powershell
    $out_filename = ".\users.csv"
    csvde -f $out_filename -l userPrincipalName,cn -r "(objectclass=person)"
    ```
3. 必要に応じて、ユーザーの一覧を含む CSV ファイルを [Microsoft Graph PowerShell コマンドレット](https://www.powershellgallery.com/packages/Microsoft.Graph)がインストールされているシステムに転送します。
4. これで、アプリケーションから取得されたすべてのユーザーの一覧が用意できたので、アプリケーションのデータ ストアのユーザーを Microsoft Entra ID 内のユーザーと照合します。 先に進む前に、[ソース システムとターゲット システム内のユーザーの照合](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes#matching-users-in-the-source-and-target--systems)に関する情報を確認します。

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
4. このコマンドを初めて使用する場合は、Microsoft Graph コマンド ライン ツールにこれらのアクセス許可を付与することに同意する必要があります。
5. アプリケーションのデータ ストアから取得したユーザーの一覧を、PowerShell セッションに読み込みます。 ユーザーの一覧の形式が CSV ファイルであった場合は、PowerShell コマンドレット `Import-Csv` を使用し、引数として前のセクションのファイルの名前を指定できます。

    たとえば、SAP Cloud Identity Services から取得したファイル名が *Users-exported-from-sap.csv* で、現在のディレクトリに保存されている場合は、次のコマンドを入力します。

    ```powershell
    $filename = ".\Users-exported-from-sap.csv"
    $dbusers = Import-Csv -Path $filename -Encoding UTF8
    ```

    別の例として、データベースまたはディレクトリを使用している場合、ファイル名が *users.csv* で、現在のディレクトリに保存されている場合は、次のコマンドを入力します:

    ```powershell
    $filename = ".\users.csv"
    $dbusers = Import-Csv -Path $filename -Encoding UTF8
    ```
6. Microsoft Entra ID 内のユーザーの属性に一致する *users.csv* ファイルの列を選択します。

    SAP Cloud Identity Services を使用している場合、既定のマッピングは、Microsoft Entra ID 属性が `userName` の SAP SCIM 属性 `userPrincipalName` です。

    ```powershell
    $db_match_column_name = "userName"
    $azuread_match_attr_name = "userPrincipalName"
    ```

    別の例として、データベースやディレクトリを使用している場合、`EMail` という列の値が Microsoft Entra の属性 `userPrincipalName` と同じ値であるデータベース内にユーザーがいる場合があります。

    ```powershell
    $db_match_column_name = "EMail"
    $azuread_match_attr_name = "userPrincipalName"
    ```
7. Microsoft Entra ID でこれらのユーザーの ID を取得します。

    次の PowerShell スクリプトでは、前に指定された `$dbusers`、`$db_match_column_name`、`$azuread_match_attr_name` の各値を使用します。 ここでは、Microsoft Entra ID にクエリを実行して、ソース ファイル内の各レコードに一致する値の属性を持つユーザーを見つけます。 ソース SAP Cloud Identity Services、データベース、またはディレクトリから取得したファイルに多数のユーザーが存在する場合、このスクリプトが完了するまでに数分かかる場合があります。 この値を持つ属性が Microsoft Entra ID に存在せず、`contains` などのフィルター式を使用する必要がある場合は、このスクリプトと手順 11 のスクリプトを、別のフィルター式を使用するようにカスタマイズする必要があります。

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
9. このスクリプトは、完了すると、データ ソースのいずれかのレコードが Microsoft Entra ID に見つからなかった場合はエラーを示します。 アプリケーションのデータ ストアのユーザーのレコードのうち Microsoft Entra ID 内のユーザーとして見つからなかったものがある場合は、一致しなかったレコードとその理由を調査する必要があります。

    たとえば、だれかの電子メール アドレスと userPrincipalName が Microsoft Entra ID で変更されたが、それに対応する `mail` プロパティがアプリケーションのデータ ソースで更新されていない可能性があります。 または、ユーザーは既に組織を離れているが、まだアプリケーションのデータ ソースに存在する可能性があります。 あるいは、アプリケーションのデータ ソースに、Microsoft Entra ID 内のどの特定のユーザーにも対応していないベンダーまたはスーパー管理者アカウントが存在する可能性もあります。
10. Microsoft Entra ID で見つけられなかった、またはアクティブでなくサインインできる状態でなかったユーザーが存在し、それらのユーザーのアクセスを確認したり、その属性を SAP Cloud Identity Services、データベース、またはディレクトリで更新したりしたい場合は、アプリケーションまたは照合ルールを更新するか、それらのユーザーに対応する Microsoft Entra ユーザーを更新または作成する必要があります。 どの変更を行うかの詳細については、「[Microsoft Entra ID のユーザーと一致しなかったアプリケーションのマッピングとユーザー アカウントを管理する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-application-unmatched-users)」を参照してください。

    Microsoft Entra ID でユーザーを作成するオプションを選択した場合、次のいずれかを使用してユーザーを一括で作成できます。

    - CSV ファイル (「[Microsoft Entra 管理センターでのユーザーの一括作成](https://learn.microsoft.com/ja-jp/entra/identity/users/users-bulk-add)」で説明されています)
    - [New-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/new-mguser?view=graph-powershell-1.0#examples&preserve-view=true) コマンドレット

    これらの新しいユーザーに、Microsoft Entra ID が後でアプリケーション内の既存のユーザーと一致するために必要な属性と、Microsoft Entra ID で必要な属性 (`userPrincipalName`、`mailNickname`、`displayName` を含む) が設定されていることを確認します。 `userPrincipalName` は、ディレクトリ内のすべてのユーザー間で一意である必要があります。

    たとえば、`EMail` という名前の列の値が Microsoft Entra のユーザー プリンシパル名として使用したい値であり、列 `Alias` の値に Microsoft Entra ID のメール ニックネームが含まれ、列 `Full name` の値にユーザーの表示名が含まれているデータベースに、ユーザーが格納されている場合が考えられます。

    ```powershell
    $db_display_name_column_name = "Full name"
    $db_user_principal_name_column_name = "Email"
    $db_mail_nickname_column_name = "Alias"
    ```

    そのような場合、このスクリプトを使用して、SAP Cloud Identity Services、データベース、またはディレクトリにある Microsoft Entra ID のユーザと一致しなかったユーザに対して Microsoft Entra ユーザを作成できます。 組織で必要な Microsoft Entra 属性をさらに追加するために、または `$azuread_match_attr_name` が `mailNickname` でも `userPrincipalName` でもない場合に、その Microsoft Entra 属性を指定するために、このスクリプトの変更が必要になる場合があることに注意してください。

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

これで Microsoft Entra ECMA Connector Host が Microsoft Entra ID と通信できるようになり、属性マッピングが構成されたので、プロビジョニングの範囲に含めるユーザーの構成に進むことができます。

重要

このセクションでは、ハイブリッド ID の管理者ロールを使用してサインインした場合、一度サインアウトし、少なくともアプリケーション管理者ロールを持つアカウントでサインインする必要があります。 ハイブリッド ID の管理者ロールには、ユーザーをアプリケーションに割り当てるためのアクセス許可がありません。

LDAP ディレクトリに既存のユーザーが存在する場合は、Microsoft Entra ID に、それらの既存のユーザー向けのアプリケーション ロールの割り当てを作成する必要があります。 `New-MgServicePrincipalAppRoleAssignedTo` を使用してアプリケーション ロールの割り当てを一括で作成する方法の詳細については、[Microsoft Entra ID でのアプリケーションの既存ユーザーの管理](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-existing-users)に関するページを参照してください。

それ以外の場合、LDAP ディレクトリが空の場合は、必要な属性を持ち、アプリケーションのディレクトリ サーバーにプロビジョニングされるテスト ユーザーを Microsoft Entra ID から選択します。

1. 選択したユーザーに、ディレクトリ サーバー スキーマの必須属性にマップされるすべてのプロパティがあることを確認します。
2. Azure portal で、 **[エンタープライズ アプリケーション]** を選択します。
3. **[オンプレミスの ECMA アプリ]** アプリケーションを選択します。
4. 左側のウィンドウの **[管理]** で、 **[ユーザーとグループ]** を選択します。
5. **[Add user/group](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/ユーザーまたはグループの追加)** を選択します。 [Image: ユーザーの追加を示すスクリーンショット。]
6. **[ユーザー]** で **[選択なし]** を選択します。 [Image: [選択なし] を示すスクリーンショット。]
7. 右側からユーザーを選択し、**[選択]** ボタンを選択します。[Image: ユーザーの選択を示すスクリーンショット。]
8. **[割り当て]** を選択します。 [Image: ユーザーの割り当てを示すスクリーンショット。]

### プロビジョニングをテストする

属性がマップされ、最初のユーザーが割り当てられたので、オンデマンド プロビジョニングをユーザーの 1 人を使用してテストできます。

1. Microsoft Entra ECMA Connector Host を実行しているサーバーで、**[開始]** をクリックします。
2. **「run」** と入力し、ボックスに **「services.msc」** と入力します。
3. **[サービス]** の一覧で、**Microsoft Entra プロビジョニング エージェント** サービスと **Microsoft ECMA2Host** サービスの両方が実行されていることを確認します。 そうでない場合は、 **[開始]** を選択します。
4. Azure portal で、 **[エンタープライズ アプリケーション]** を選択します。
5. **[オンプレミスの ECMA アプリ]** アプリケーションを選択します。
6. 左側で **プロビジョニング** を選択します。
7. **[Provision on demand] (オンデマンドでプロビジョニングする)** を選択します。
8. テスト ユーザーのものを検索し、 **[プロビジョニング]** を選択します。 [Image: オンデマンド プロビジョニングのテストを示すスクリーンショット。]
9. 数秒後に、**ターゲット システムでユーザーが正常に作成されました**というメッセージが表示され、ユーザー属性の一覧が表示されます。 そうではなくエラーが表示される場合は、「プロビジョニング エラーのトラブルシューティング」のセクションを参照してください。

### ユーザーのプロビジョニングを開始する

オンデマンド プロビジョニングのテストが成功したら、残りのユーザーを追加します。

1. Azure Portal で、アプリケーションを選択します。
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
4. 次の情報を入力し、[OK] をクリック **します**。
    - サーバー: APP3
    - ポートは 80 にします。
    - [SSL] ボックスにチェックを入れます[Image: ユーザーを確認するための Ldp 接続を示すスクリーンショット。]
5. 上部の [ **接続** ] で [ **バインド**] を選択します。
6. 既定値をそのまま使用し、**[Ok]** をクリックします。
7. 上部にある [**表示**と**ツリー** ] を選択します。
8. [BaseDN] に「 **CN = App, dc = contoso, dc = lab** 」と入力し、[ **OK]**をクリックします。
9. 左側の [DN] を展開し、[ **cn = CloudUsers, cn = App, dc = contoso, dc = lab**] をクリックします。 Microsoft Entra ID からプロビジョニングされたユーザーが表示されます。 [Image: ユーザーの Ldp バインドを示すスクリーンショット。]

OpenLDAP を確認する方法を次の手順で示します。

1. OpenLDAP があるシステムで、コマンド シェルを含むターミナル ウィンドウを開きます。
2. コマンド `ldapsearch -D "cn=admin,dc=contoso,dc=lab" -W -s sub -b dc=contoso,dc=lab -LLL (objectclass=inetOrgPerson)` を入力します。
3. 結果の LDIF に、Microsoft Entra ID からプロビジョニングされたユーザーが含まれていることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/on-premises-ldap-connector-linux"} -->
## Linux 認証用の LDAP ディレクトリへの Microsoft Entra プロビジョニング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-linux
- Service: entra-id / app-provisioning
- Article date: 2025-04-09
- Summary: このドキュメントでは、ユーザーがプラグ可能な認証を使用して Linux またはその他の POSIX システムにサインインできるように、ユーザーを LDAP ディレクトリにプロビジョニングするように Microsoft Entra ID を構成する方法について説明します。

次のドキュメントは、Linux システムへのアクセスを管理する方法を示すチュートリアルです。 Microsoft Entra は、その Linux システムによって信頼されているオンプレミスの LDAP ディレクトリにユーザーをプロビジョニングします。 これにより、ユーザーは、ユーザー認証のためにその LDAP ディレクトリに依存する Linux システムにログインできます。 ユーザーが Microsoft Entra ID から削除されると、Linux システムにログインできなくなります。

手記

この記事で説明するシナリオは、ユーザーの識別と認証に Name Services スイッチ (NSS) またはプラグ可能な認証モジュール (PAM) LDAP モジュールに既に依存している既存の Linux システムにのみ適用されます。 Azure または Azure Arc 対応の Linux VM は、代わりに Microsoft Entra 認証と統合する必要があります。 Microsoft Entra ID と OpenSSHを使用して Azure の Linux 仮想マシンにログインする 方法の説明に従って、Microsoft Entra ID と OpenSSH 証明書ベースの認証を使用して Linux VM に SSH 接続するためのコア認証プラットフォームと証明機関として Microsoft Entra ID を使用できるようになりました。

Linux 認証以外の LDAP ディレクトリへのユーザーのプロビジョニングに関連するその他のシナリオについては、LDAP ディレクトリにユーザーをプロビジョニングするように Microsoft Entra ID を構成する を参照してください。

### Linux 認証用の LDAP ディレクトリにユーザーをプロビジョニングするための前提条件

この記事では、LDAP サーバーがオンプレミス環境に既に存在し、1 つ以上の Linux または他の POSIX システムによってユーザー認証に使用されていることを前提としています。

[Image: Microsoft Entra ID から LDAP ディレクトリ サーバーへのオンプレミス プロビジョニングのアーキテクチャを示す図。]

#### オンプレミスの前提条件

- PAM または NSS モジュールを使用してディレクトリ サーバーに応答する Linux または他の POSIX サーバー。
- ユーザーを作成、更新、削除できる POSIX スキーマ (OpenLDAP など) をサポートする LDAP ディレクトリ サーバー。 サポートされているディレクトリ サーバーの詳細については、 [Generic LDAP コネクタリファレンスを参照してください](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/reference/microsoft-identity-manager-2016-connector-genericldap)。
- プロビジョニング エージェントをホストするための RAM が少なくとも 3 GB のコンピューター。 コンピューターには Windows Server 2016 以降のバージョンの Windows Server が必要です。 また、ターゲット ディレクトリ サーバーへの接続と、他の Microsoft Online Services と Azure ドメイン 、login.microsoftonline.com への送信接続も必要です。 たとえば、Azure IaaS またはプロキシの背後でホストされている Windows Server 2016 仮想マシンがあります。 .NET Framework 4.7.2 をそのサーバーにインストールする必要があります。
- 省略可能: 必須ではありませんが、Microsoft Edge for Windows Serverダウンロードし、Internet Explorer の代わりに使用することをお勧めします。

#### クラウドの要件

- Microsoft Entra ID P1 または Premium P2 (または EMS E3 または E5) を持つ Microsoft Entra テナント。

    この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 要件に適したライセンスを見つけるには、「 [Microsoft Entra ID の一般公開機能を比較](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)する」を参照してください。
- プロビジョニング エージェントを構成するためのハイブリッド ID 管理者ロール。
- Azure portal または Microsoft Entra 管理センターでプロビジョニングを構成するためのアプリケーション管理者またはクラウド アプリケーション管理者ロール。
- ディレクトリ サーバー スキーマでは、各 Microsoft Entra ユーザーの特定の属性を LDAP ディレクトリにプロビジョニングする必要があり、これらの属性は既に設定されている必要があります。 特に、各ユーザーには、ユーザー ID 番号として一意の番号が必要です。 プロビジョニング エージェントをデプロイしてユーザーをディレクトリに割り当てる前に、ユーザーの既存の属性からその番号を生成するか、Microsoft Entra スキーマを拡張する必要があります。 その後、スコープ内のユーザーにその属性を設定できます。 追加のディレクトリ拡張機能を作成する方法については、 [Graph 拡張機能](https://learn.microsoft.com/ja-jp/graph/extensibility-overview?tabs=http#directory-azure-ad-extensions) を参照してください。

#### その他の推奨事項と制限事項

次の箇条書きは、その他の推奨事項と制限事項です。

- クラウド同期とオンプレミスアプリのプロビジョニングに同じエージェントを使用することはお勧めしません。 クラウド同期には別のエージェントを使用し、1 つはオンプレミスアプリのプロビジョニングに使用することをお勧めします。
- 現在、AD LDS の場合、ユーザーはパスワードを使用してプロビジョニングできません。 そのため、AD LDS のパスワード ポリシーを無効にするか、無効な状態でユーザーをプロビジョニングする必要があります。
- 他のディレクトリ サーバーでは、初期ランダム パスワードを設定できますが、Microsoft Entra ユーザーのパスワードをディレクトリ サーバーにプロビジョニングすることはできません。
- LDAP から Microsoft Entra ID へのユーザーのプロビジョニングはサポートされていません。
- ディレクトリ サーバーへのグループとユーザー メンバーシップのプロビジョニングはサポートされていません。

### Microsoft Entra LDAP コネクタがディレクトリ サーバーと対話する方法を決定する

コネクタを既存のディレクトリ サーバーに展開する前に、ディレクトリ サーバーと統合する方法について、組織内のディレクトリ サーバー オペレーターと話し合う必要があります。 収集する情報には、次のものが含まれます。

- ディレクトリ サーバーに接続する方法のネットワーク情報。
- コネクタがディレクトリ サーバーに対して自身を認証する方法。
- ユーザーをモデル化するためにディレクトリ サーバーが選択したスキーマ。
- 名前付けコンテキストの基本識別名とディレクトリ階層ルール。
- ディレクトリ サーバー内のユーザーを Microsoft Entra ID のユーザーに関連付ける方法。
- ユーザーが Microsoft Entra ID でスコープ外になったときにはどうなるべきか。

このコネクタを展開するには、ディレクトリ サーバーの構成の変更と、Microsoft Entra ID の構成の変更が必要になる場合があります。 Microsoft Entra ID を運用環境のサード パーティのディレクトリ サーバーと統合する展開の場合は、お客様がディレクトリ サーバー ベンダー、またはこの統合のヘルプ、ガイダンス、およびサポートのために展開パートナーと協力することをお勧めします。 この記事では、OpenLDAP の次のサンプル値を使用します。

| 構成設定 | 値が設定されている場所 | 値の例 |
| --- | --- | --- |
| ディレクトリ サーバーのホスト名 | 構成ウィザード **の [接続]** ページ | `APP3` |
| ディレクトリ サーバーのポート番号 | 構成ウィザード **の [接続]** ページ | 636. LDAP over SSL または TLS (LDAPS) の場合は、ポート 636 を使用します。 `Start TLS`の場合は、ポート 389 を使用します。 |
| コネクタがディレクトリ サーバーに対して自身を識別するためのアカウント | 構成ウィザード **の [接続]** ページ | `cn=admin,dc=contoso,dc=lab` |
| コネクタがディレクトリ サーバーに対して自身を認証するためのパスワード | 構成ウィザード **の [接続]** ページ |  |
| ディレクトリ サーバー内のユーザーの構造オブジェクト クラス | 構成ウィザード **の [オブジェクトの種類]** ページ | `inetOrgPerson` |
| ディレクトリ サーバー内のユーザーの補助オブジェクト クラス | Azure portal **の [プロビジョニング** ] ページの属性マッピング | `posixAccount` と`shadowAccount` |
| 新しいユーザーに設定する属性 | 構成ウィザード **の [属性の選択]** ページと Azure portal **の [プロビジョニング** ] ページの属性マッピング | `cn`、 `gidNumber`、 `homeDirectory`、 `mail`、 `objectClass`、 `sn`、 `uid`、 `uidNumber`、 `userPassword` |
| ディレクトリ サーバーに必要な名前付け階層 | Azure portal **の [プロビジョニング** ] ページの属性マッピング | 新しく作成したユーザーの DN を `DC=Contoso,DC=lab` のすぐ下に設定します |
| Microsoft Entra ID とディレクトリ サーバー間でユーザーを関連付けするための属性 | Azure portal **の [プロビジョニング** ] ページの属性マッピング | `mail` |
| ユーザーが Microsoft Entra ID でスコープ外になったときのプロビジョニング解除動作 | 構成ウィザードページ **プロビジョニング解除** | ディレクトリ サーバーからユーザーを削除する |

ディレクトリ サーバーのネットワーク アドレスは、ホスト名と TCP ポート番号 (通常はポート 389 または 636) です。 ディレクトリ サーバーが同じ Windows Server 上のコネクタと併置されている場合、またはネットワーク レベルのセキュリティを使用している場合を除き、コネクタからディレクトリ サーバーへのネットワーク接続は SSL または TLS を使用して保護する必要があります。 コネクタは、ポート 389 上のディレクトリ サーバーへの接続と、セッション内での TLS の開始を使用した TLS の有効化をサポートしています。 このコネクタでは、LDAPS - LDAP over TLS のポート 636 上のディレクトリ サーバーへの接続もサポートしています。

コネクタがディレクトリ サーバーに対して認証を行うには、既にディレクトリ サーバーで構成されている識別済みのアカウントが必要です。 通常、このアカウントは識別名で識別され、パスワードまたはクライアント証明書が関連付けられています。 接続されたディレクトリ内のオブジェクトに対してインポート操作とエクスポート操作を実行するには、コネクタ アカウントにディレクトリのアクセス制御モデル内で十分なアクセス許可が必要です。 コネクタには、エクスポートできる **書き込み** アクセス許可と、インポート可能な **読み取り** アクセス許可が必要です。 アクセス許可の構成は、ターゲット ディレクトリ自体の管理エクスペリエンス内で実行されます。

ディレクトリ スキーマは、ディレクトリ内の実際のエンティティを表すオブジェクト クラスと属性を指定します。 コネクタは、`inetOrgPerson`などの構造オブジェクト クラスで表されるユーザーと、必要に応じて追加の補助オブジェクト クラスをサポートします。 コネクタがディレクトリ サーバーにユーザーをプロビジョニングできるようにするには、Azure portal での構成時に、Microsoft Entra スキーマからすべての必須属性へのマッピングを定義します。 これには、構造オブジェクト クラスの必須属性、その構造オブジェクト クラスのスーパークラス、および補助オブジェクト クラスの必須属性が含まれます。

また、これらのクラスの省略可能な属性の一部へのマッピングも構成する可能性があります。 Linux 認証をサポートする POSIX スキーマを持つ OpenLDAP ディレクトリ サーバーでは、新しいユーザーが次の例のような属性を持つオブジェクトが必要になる場合があります。

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

ディレクトリ サーバーによって実装されるディレクトリ階層ルールでは、各ユーザーのオブジェクトが相互に、およびディレクトリ内の既存のオブジェクトにどのように関連するかを記述します。 ほとんどの展開では、組織はディレクトリ サーバーにフラット階層を設定することを選択しました。この階層では、ユーザーの各オブジェクトが共通の基本オブジェクトのすぐ下に配置されます。 たとえば、ディレクトリ サーバー内の名前付けコンテキストの基本識別名が `dc=contoso,dc=com` 場合、新しいユーザーは `cn=alice,dc=contoso,dc=com`などの識別名を持つことになります。

ただし、より複雑なディレクトリ階層を持つ組織もあります。その場合は、コネクタの識別名マッピングを指定するときにルールを実装する必要があります。 たとえば、ディレクトリ サーバーでは、ユーザーが部門別の組織単位に属することが想定される場合があるため、新しいユーザーには `cn=alice,ou=London,dc=contoso,dc=com`などの識別名が付けられます。 コネクタは組織単位の中間オブジェクトを作成しないため、ディレクトリ サーバー ルール階層で想定される中間オブジェクトは、ディレクトリ サーバーに既に存在している必要があります。

次に、Microsoft Entra ユーザーに対応するディレクトリ サーバーにユーザーが既に存在するかどうかをコネクタが判断する方法の規則を定義する必要があります。 すべての LDAP ディレクトリには、ディレクトリ サーバー内のオブジェクトごとに一意の識別名が付けられますが、Microsoft Entra ID のユーザーには識別名が存在しないことがよくあります。 代わりに、組織は、ディレクトリ サーバー スキーマに異なる属性 (`mail` や `employeeId`など) を持つ場合があり、Microsoft Entra ID のユーザーにも存在します。 次に、コネクタが新しいユーザーをディレクトリ サーバーにプロビジョニングするときに、その属性の特定の値を持つユーザーがそのディレクトリに既に存在するかどうかを検索し、存在しない場合にのみディレクトリ サーバーに新しいユーザーを作成できます。

既存のユーザーを更新または削除するだけでなく、LDAP ディレクトリに新しいユーザーを作成するシナリオでは、そのディレクトリ サーバーを使用する Linux システムによる認証の処理方法も決定する必要があります。 一部のシステムでは、ディレクトリからユーザーの SSH 公開キーまたは証明書に対してクエリを実行できます。これは、ユーザーが既にこれらのフォームの資格情報を保持している場合に適している場合があります。 ただし、ディレクトリ サーバーに依存するアプリケーションが先進認証プロトコルまたはより強力な資格情報をサポートしていない場合は、Microsoft Entra ID はユーザーの Microsoft Entra パスワードのプロビジョニングをサポートしていないため、ディレクトリに新しいユーザーを作成するときにアプリケーション固有のパスワードを設定する必要があります。

最後に、プロビジョニング解除の動作に同意する必要があります。 コネクタが構成されていて、Microsoft Entra ID のユーザーとディレクトリ内のユーザー (ディレクトリに既に存在するユーザーまたは新しいユーザー) の間で Microsoft Entra ID がリンクされている場合、Microsoft Entra ID は Microsoft Entra ユーザーからの属性変更をディレクトリにプロビジョニングできます。

アプリケーションに割り当てられているユーザーが Microsoft Entra ID で削除された場合、Microsoft Entra ID は削除操作をディレクトリ サーバーに送信します。 また、ユーザーがアプリケーションを使用できない場合に、ディレクトリ サーバー内のオブジェクトを Microsoft Entra ID で更新することもできます。 この動作は、ディレクトリ サーバーを使用するアプリケーションによって異なります。OpenLDAP などの多くのディレクトリには、ユーザーのアカウントが非アクティブ化されていることを示す既定の方法がない場合があります。

### Microsoft Entra Connect プロビジョニング エージェントをインストールして構成する

1. Azure portal にサインインします。
2. エンタープライズ アプリケーション に移動し、[新しいアプリケーション] 選択します。
3. **オンプレミスの ECMA アプリ** アプリケーションを検索し、アプリに名前を付け、[**作成**] を選択してテナントに追加します。
4. メニューから、アプリケーションの **[プロビジョニング** ] ページに移動します。
5. [ **作業の開始] を選択します**。
6. [ **プロビジョニング** ] ページで、モードを **[自動**] に変更します。

[Image: [自動] を選択したスクリーンショット。]

1. [ **オンプレミス接続] で**、[ **ダウンロードとインストール**] を選択し、[ **同意する] を選択してダウンロードします**。

[Image: エージェントのダウンロード場所のスクリーンショット。]

1. ポータルを終了し、プロビジョニング エージェント インストーラーを実行し、サービス条件に同意して、[インストール] を選択 **します**。
2. Microsoft Entra プロビジョニング エージェント構成ウィザードを待ってから、[次 ] を選択します。
3. 拡張機能 の選択手順で、オンプレミスアプリケーションプロビジョニング選択し、次 を選択します。
4. プロビジョニング エージェントは、オペレーティング システムの Web ブラウザーを使用して、Microsoft Entra ID および組織の ID プロバイダーに対して認証するためのポップアップ ウィンドウを表示します。 Windows Server のブラウザーとして Internet Explorer を使用している場合は、JavaScript を正しく実行できるように、ブラウザーの信頼済みサイト一覧に Microsoft Web サイトを追加することが必要になる場合があります。
5. 承認を求められたら、Microsoft Entra 管理者の資格情報を入力します。 ユーザーには、少なくとも [ハイブリッド ID 管理者ロールが](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) 必要です。
6. [確認] を選択して設定を確定します。 インストールが正常に完了したら、終了 選択し、プロビジョニング エージェント パッケージ インストーラーを閉じてもかまいません。

### オンプレミスの ECMA アプリを構成する

1. ポータルに戻り、[ **オンプレミス接続** ] セクションで、デプロイしたエージェントを選択し、[ **エージェントの割り当て]** を選択します。

    [Image: エージェントを選択して割り当てる方法を示すスクリーンショット。]
2. 構成ウィザードを使用して次の構成手順を完了したら、このブラウザー ウィンドウを開いたままにしておきます。

### Microsoft Entra ECMA コネクタ ホスト証明書を構成する

1. プロビジョニング エージェントがインストールされている Windows Server で、スタート メニューから Microsoft ECMA2Host 構成ウィザードを右選択し、管理者として実行します。 ウィザードで必要な Windows イベント ログを作成するには、Windows 管理者として実行する必要があります。
2. ECMA コネクタ ホスト構成の開始後、ウィザードを初めて実行する場合は、証明書の作成を求められます。 既定のポート **8585** のままにし、[ **証明書の生成** ] を選択して証明書を生成します。 自動生成された証明書は、信頼されたルートの一部として自己署名されます。 SAN はホスト名と一致します。 [Image: 設定の構成を示すスクリーンショット。]
3. **[保存] を選択します**。

手記

新しい証明書の生成を選択した場合は、証明書の有効期限を記録して、構成ウィザードに戻り、有効期限が切れる前に証明書を生成し直すようにスケジュールしてください。

### 汎用 LDAP コネクタを構成する

選択したオプションによっては、一部のウィザード画面が使用できない場合があり、情報が若干異なる場合があります。 次の情報を使用して、設定の手順をガイドしてください。

1. コネクタに対する Microsoft Entra ID の認証に使用されるシークレット トークンを生成します。 アプリケーションごとに最小 12 文字で一意である必要があります。 シークレット ジェネレーターがまだない場合は、次のような PowerShell コマンドを使用して、ランダムな文字列の例を生成できます。

    ```powershell
    -join (((48..90) + (96..122)) * 16 | Get-Random -Count 16 | % {[char]$_})
    ```
2. まだ実行していない場合は、スタート メニューから Microsoft ECMA2Host 構成ウィザードを起動します。
3. [ **新しいコネクタ]** を選択します。 [Image: [新しいコネクタ] の選択を示すスクリーンショット。]
4. [プロパティ] ページで、画像の後にある表で指定した値をボックスに入力し、[次へ]選択します。 [Image: プロパティの入力を示すスクリーンショット。]

    | 財産 | 価値 |
    | --- | --- |
    | 名前 | コネクタに対して選択した名前。環境内のすべてのコネクタで一意である必要があります。 たとえば、`LDAP`します。 |
    | 自動同期タイマー (分) | 120 |
    | シークレット トークン | ここにシークレット トークンを入力します。 最小 12 文字にする必要があります。 |
    | 拡張 DLL | 汎用 LDAP コネクタの場合は、 **Microsoft.IAM.Connector.GenericLdap.dll**を選択します。 |
5. [ **接続** ] ページで、ECMA コネクタ ホストがディレクトリ サーバーと通信する方法を構成し、いくつかの構成オプションを設定します。 画像の後にある表で指定した値をボックスに入力し、[次へ]選択します。 [次 を選択すると、コネクタはディレクトリ サーバーにその構成を照会します。 [Image: [接続] ページを示すスクリーンショット。]

    | 財産 | 説明 |
    | --- | --- |
    | ホスト | LDAP サーバーが配置されているホスト名。 このサンプルでは、ホスト名の例として `APP3` を使用します。 |
    | ポート | TCP ポート番号。 ディレクトリ サーバーが SSL 経由で LDAP 用に構成されている場合は、ポート 636 を使用します。 `Start TLS`の場合、またはネットワーク レベルのセキュリティを使用している場合は、ポート 389 を使用します。 |
    | 接続タイムアウト | 180 |
    | バインド | このプロパティは、コネクタがディレクトリ サーバーに対して認証する方法を指定します。 `Basic` 設定を使用するか、`SSL` または `TLS` 設定を使用し、クライアント証明書が構成されていない場合、コネクタは LDAP 単純バインドを送信して、識別名とパスワードで認証します。 `SSL` または `TLS` 設定とクライアント証明書を指定すると、コネクタは LDAP SASL `EXTERNAL` バインドを送信して、クライアント証明書で認証します。 |
    | ユーザー名 | ECMA コネクタがディレクトリ サーバーに対して自身を認証する方法。 この例では、`cn=admin,dc=contoso,dc=lab` |
    | パスワード | ECMA コネクタがディレクトリ サーバーに対して自身を認証するユーザーのパスワード。 |
    | 領域/ドメイン | この設定は、ユーザーの領域/ドメインを指定するために、バインド オプションとして `Kerberos` を選択した場合にのみ必要です。 |
    | 証書 | このセクションの設定は、[バインド] オプションとして `SSL` または `TLS` を選択した場合にのみ使用されます。 |
    | 属性エイリアス | 属性エイリアス テキスト ボックスは、RFC4522構文を使用してスキーマで定義された属性に使用されます。 これらの属性はスキーマ検出中に見つけることができず、コネクタがそれらの属性を識別する際に支援が必要です。 たとえば、ディレクトリ サーバーが `userCertificate;binary` を発行せず、その属性をプロビジョニングする場合、userCertificate 属性をバイナリ属性として正しく識別するには、属性エイリアス ボックスに次の文字列を入力する必要があります:`userCertificate;binary`。 スキーマにない特別な属性を必要としない場合は、空白のままにすることができます。 |
    | 運用属性を含める | `Include operational attributes in schema` チェックボックスをオンにすると、ディレクトリ サーバーによって作成された属性も含まれます。 これには、オブジェクトが作成された日時や最終更新時刻などの属性が含まれます。 |
    | 拡張可能な属性を含める | 拡張オブジェクト (RFC4512/4.3) がディレクトリ サーバーで使用されている場合は、`Include extensible attributes in schema` チェック ボックスをオンにします。 このオプションを有効にすると、すべての属性をすべてのオブジェクトで使用できます。 このオプションを選択するとスキーマが非常に大きいため、接続されたディレクトリがこの機能を使用していない場合は、オプションを選択しないようにすることをお勧めします。 |
    | 手動アンカー選択を許可する | オフのままにします。 |

    手記

    接続しようとして問題が発生し、[ **グローバル** ] ページに進まない場合は、ディレクトリ サーバーのサービス アカウントが有効になっていることを確認してください。
6. [ **グローバル** ] ページで、必要に応じて差分変更ログの識別名と、追加の LDAP 機能を構成します。 このページには、LDAP サーバーによって提供される情報が事前に設定されています。 表示されている値を確認し、[次へ]選択します。

    | 財産 | 説明 |
    | --- | --- |
    | サポートされている SASL メカニズム | 上部のセクションには、SASL メカニズムの一覧など、サーバー自体によって提供される情報が表示されます。 |
    | サーバー証明書の詳細 | `SSL` または `TLS` が指定されている場合、ウィザードはディレクトリ サーバーから返された証明書を表示します。 発行者、件名、拇印が正しいディレクトリ サーバー用であることを確認します。 |
    | 必須機能が見つかりました | コネクタは、必須のコントロールがルート DSE に存在することも確認します。 これらのコントロールが一覧にない場合は、警告が表示されます。 一部の LDAP ディレクトリでは、ルート DSE 内のすべての機能が一覧表示されず、警告が存在する場合でも、コネクタが問題なく動作する可能性があります。 |
    | サポートされているコントロール | **サポートされているコントロールのチェック ボックスは、**特定の操作の動作を制御します |
    | 差分インポート | 変更ログ DN は、デルタ変更ログ ( **cn=changelog など)** によって使用される名前付けコンテキストです。 デルタ インポートを実行できるようにするには、この値を指定する必要があります。 差分インポートを実装する必要がない場合は、このフィールドを空白にすることができます。 |
    | パスワード属性 | ディレクトリ サーバーが別のパスワード属性またはパスワード ハッシュをサポートしている場合は、パスワードの変更先を指定できます。 |
    | パーティション名 | 追加のパーティションの一覧では、自動的に検出されない名前空間を追加できます。 たとえば、複数のサーバーが論理クラスターを構成し、すべて同時にインポートする必要がある場合に、この設定を使用できます。 Active Directory は 1 つのフォレスト内に複数のドメインを持つことができますが、すべてのドメインが 1 つのスキーマを共有する場合と同様に、このボックスに追加の名前空間を入力することで同じものをシミュレートできます。 各名前空間は、異なるサーバーからインポートでき、[ **パーティションと階層の構成** ] ページでさらに構成されます。 |
7. [パーティション ] ページで、既定の設定をそのまま使用して、[次へ] を選択します。
8. [ **実行プロファイル** ] ページで、[ **エクスポート** ] チェックボックスと [ **フル インポート** ] チェックボックスの両方が選択されていることを確認します。 次 を選択します。 [Image: [プロファイルの実行] ページを示すスクリーンショット。]

    | 財産 | 説明 |
    | --- | --- |
    | 輸出 | LDAP ディレクトリ サーバーにデータをエクスポートするプロファイルを実行します。 この実行プロファイルは必須です。 |
    | 完全インポート | 前に指定した LDAP ソースからすべてのデータをインポートするプロファイルを実行します。 この実行プロファイルは必須です。 |
    | デルタインポート | 最後の完全インポートまたは差分インポート以降の LDAP からの変更のみをインポートするプロファイルを実行します。 ディレクトリ サーバーが必要な要件を満たしていることを確認した場合にのみ、この実行プロファイルを有効にします。 詳細については、 [汎用 LDAP コネクタリファレンスを参照してください](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/reference/microsoft-identity-manager-2016-connector-genericldap)。 |
9. [ **エクスポート** ] ページで、既定値をそのまま使用し、[ **次へ**] を選択します。
10. [ **フル インポート** ] ページで、既定値はそのままにして、[ **次へ**] を選択します。
11. **DeltaImport** ページに存在する場合は、既定値をそのままにして、[**次へ**] を選択します。
12. [ **オブジェクトの種類]** ページで、ボックスに入力し、[ **次へ**] を選択します。

    | 財産 | 説明 |
    | --- | --- |
    | ターゲット オブジェクト | この値は、LDAP ディレクトリー・サーバー内のユーザーの構造オブジェクト・クラスです。 `inetOrgPerson`使用し、このフィールドに補助オブジェクト クラスを指定しないでください。 ディレクトリ サーバーに補助オブジェクト クラスが必要な場合は、Azure portal で属性マッピングを使用して構成されます。 |
    | 錨 | この属性の値は、ターゲット ディレクトリ内のオブジェクトごとに一意である必要があります。 Microsoft Entra プロビジョニング サービスは、最初のサイクルの後にこの属性を使用して ECMA コネクタ ホストにクエリを実行します。 通常は、`-dn-`として選択できる特定の識別名を使用します。 OpenLDAP スキーマの `uid` 属性など、複数値の属性をアンカーとして使用することはできません。 |
    | クエリ属性 | この属性は Anchor と同じである必要があります。 |
    | DN | ターゲット オブジェクトの識別名。 `-dn-`を維持します。 |
    | 自動生成 | 未確認 |
13. ECMA ホストは、ターゲット ディレクトリでサポートされている属性を検出します。 Microsoft Entra ID に公開する属性を選択できます。 これらの属性は、プロビジョニング用に Azure portal で構成できます。 [ **属性の選択** ] ページで、必須の属性として必要な属性、または Microsoft Entra ID からプロビジョニングする属性をドロップダウン リストに一度に 1 つずつ追加します。 [Image: [属性の選択] ページを示すスクリーンショット。]**[属性]** ドロップダウン リストには、ターゲット ディレクトリで検出され、構成ウィザードの [*属性の選択*] ページの以前の使用で選択**されなかった**属性が表示されます。

    `Treat as single value` 属性の `objectClass` チェックボックスがオフになっていることを確認してください。また、`userPassword` が設定されている場合は、`userPassword` 属性のチェックボックスが選択できないか、オンになっているかを確認してください。

    次の属性の可視性を構成します。

    | 属性 | 単一値として扱う |
    | --- | --- |
    | \_distinguishedName |  |
    | -dn- |  |
    | パスワードのエクスポート |  |
    | cn | Y |
    | ギドナンバー |  |
    | ホームディレクトリ |  |
    | メール | Y |
    | オブジェクトクラス |  |
    | エスエヌ | Y |
    | ユーザーID | Y |
    | UID番号 |  |
    | ユーザーパスワード | Y |

    関連するすべての属性が追加されたら、[ **次へ**] を選択します。
14. [プロビジョニング解除] ページで、アプリケーションの範囲外になったときに Microsoft Entra ID でユーザーをディレクトリから削除するかどうかを指定できます。 その場合は、[フロー無効にする] で [削除] を選択し、[フロー削除] で [削除] を選択します。 `Set attribute value` 選択した場合、前のページで選択した属性はプロビジョニング解除ページで選択できなくなります。

手記

**Set 属性値**を使用する場合は、ブール値のみが許可されることに注意してください。

1. **[完了] を選択します**。

### ECMA2Host サービスが実行されていて、ディレクトリ サーバーから読み取ることができることを確認する

次の手順に従って、コネクタ ホストが開始され、ディレクトリ サーバーから既存のユーザーが識別されたことを確認します。

1. Microsoft Entra ECMA コネクタ ホストを実行しているサーバーで、[開始] を選択 **します**。
2. 必要に応じて **[実行** ] を選択し、ボックスに **「services.msc** 」と入力します。
3. サービスの の一覧で、Microsoft ECMA2Hostが存在し、実行されていることを確認します。 実行されていない場合は、[ **スタート]** を選択します。 [Image: サービスが実行されていることを示すスクリーンショット。]
4. 最近サービスを開始し、ディレクトリ サーバーに多数のユーザー オブジェクトがある場合は、コネクタがディレクトリ サーバーとの接続を確立するまで数分待ちます。
5. Microsoft Entra ECMA コネクタ ホストを実行しているサーバーで、PowerShell を起動します。
6. ECMA ホストがインストールされたフォルダー (`C:\Program Files\Microsoft ECMA2Host`など) に変更します。
7. サブディレクトリ `Troubleshooting`に変更します。
8. 次に示すように、そのディレクトリ `TestECMA2HostConnection.ps1` スクリプトを実行し、コネクタ名と `ObjectTypePath` 値を引数として指定 `cache`。 コネクタ ホストが TCP ポート 8585 でリッスンしていない場合は、`-Port` 引数も指定する必要があります。 メッセージが表示されたら、そのコネクタ用に構成されたシークレット トークンを入力します。

    ```
    PS C:\Program Files\Microsoft ECMA2Host\Troubleshooting> $cout = .\TestECMA2HostConnection.ps1 -ConnectorName LDAP -ObjectTypePath cache; $cout.length -gt 9
    Supply values for the following parameters:
    SecretToken: ************
    ```
9. スクリプトにエラーまたは警告メッセージが表示される場合は、サービスが実行されていることを確認し、コネクタ名とシークレット トークンが構成ウィザードで構成した値と一致することを確認します。
10. スクリプトに出力 `False`が表示された場合、コネクタは既存のユーザーのソース ディレクトリ サーバーにエントリを表示していません。 これが新しいディレクトリ サーバーのインストールである場合は、この動作が想定されるため、次のセクションに進むことができます。
11. ただし、ディレクトリ サーバーに既に 1 人以上のユーザーが含まれているが、`False`スクリプトが表示されている場合、この状態はコネクタがディレクトリ サーバーから読み取ることができなかったことを示します。 プロビジョニングしようとすると、Microsoft Entra ID が、そのソース ディレクトリ内のユーザーと Microsoft Entra ID のユーザーと正しく一致しない可能性があります。 コネクタ ホストが既存のディレクトリ サーバーからのオブジェクトの読み取りを完了するまで数分待ってから、スクリプトを再実行します。 出力が引き続き `False`場合は、コネクタの構成を確認し、ディレクトリ サーバーのアクセス許可によってコネクタが既存のユーザーを読み取ることを許可します。

### Microsoft Entra ID からコネクタ ホストへの接続をテストする

1. ポータルでアプリケーション プロビジョニングを構成していた Web ブラウザー ウィンドウに戻ります。

    手記

    ウィンドウがタイムアウトした場合は、エージェントを再選択する必要があります。

    1. Azure portal にサインインします。
    2. Enterprise アプリケーション とオンプレミス ECMA アプリ アプリケーション に移動します。
    3. **プロビジョニング**を選択します。
    4. **[開始]** が表示されたら、モードを **[自動**] に変更し、[**オンプレミス接続**] セクションで、デプロイしたエージェントを選択し、[**エージェントの割り当て]** を選択して、10 分待ちます。 それ以外の場合は、[ **プロビジョニングの編集]** に移動します。
2. [ **管理者資格情報** ] セクションで、次の URL を入力します。 `connectorName` 部分を ECMA ホスト上のコネクタの名前 (`LDAP`など) に置き換えます。 ECMA ホストの証明機関から証明書を指定した場合は、`localhost` を ECMA ホストがインストールされているサーバーのホスト名に置き換えます。

    | 財産 | 価値 |
    | --- | --- |
    | テナント URL | https://localhost:8585/ecma2host\_connectorName/scim |
3. コネクタの作成時に定義した **シークレット トークン** の値を入力します。

    手記

    アプリケーションにエージェントを割り当てたばかりの場合は、登録が完了するまで 10 分待ってください。 接続テストは、登録が完了するまで機能しません。 サーバーでプロビジョニング エージェントを再起動してエージェントの登録を強制的に完了すると、登録プロセスが高速化されます。 サーバーに移動し、Windows 検索バーでサービスを検索し、Microsoft Entra Connect Provisioning Agent サービス を特定し、サービスを右選択して再起動します。
4. [テスト接続選択し、1 分間待ちます。
5. 接続テストが成功し、プロビジョニングを有効にするために指定された資格情報が承認されていることを示した後、[**Save**.] を選択します。[Image: エージェントのテストを示すスクリーンショット。]

### Microsoft Entra スキーマを拡張する

ディレクトリ サーバーで、ユーザーの既定の Microsoft Entra スキーマの一部ではない追加の属性が必要な場合は、プロビジョニング時に、定数から、他の Microsoft Entra 属性から変換された式から、または Microsoft Entra スキーマを拡張することによって、それらの属性の値を指定するように構成できます。

ディレクトリ サーバーで、ユーザーに OpenLDAP POSIX スキーマの `uidNumber` などの属性が必要であり、その属性がユーザーの Microsoft Entra スキーマの一部ではなく、ユーザーごとに一意である必要がある場合は、 [式](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)を使用してユーザーの他の属性からその属性を生成するか、 [ディレクトリ拡張機能機能](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping) を使用してその属性を拡張機能として追加する必要があります。

ユーザーが Active Directory Domain Services で作成され、そのディレクトリに属性がある場合は、Microsoft Entra Connect または Microsoft Entra Connect クラウド同期を使用できます。これにより、Active Directory Domain Services から Microsoft Entra ID に同期されるように属性が構成され、他のシステムへのプロビジョニングに使用できるようになります。

ユーザーが Microsoft Entra ID を使用している場合は、ユーザーに格納する新しい属性ごとに [ディレクトリ拡張機能を定義](https://learn.microsoft.com/ja-jp/graph/extensibility-overview?tabs=http#define-the-directory-extension) する必要があります。 次に、プロビジョニングする予定の [Microsoft Entra ユーザーを更新](https://learn.microsoft.com/ja-jp/graph/extensibility-overview?tabs=http#update-or-delete-directory-extensions) して、各ユーザーにこれらの属性の値を付与します。

### 属性マッピングを構成する

このセクションでは、Microsoft Entra ユーザーの属性と ECMA ホスト構成ウィザードで以前に選択した属性との間のマッピングを構成します。 後でコネクタがディレクトリ サーバーにオブジェクトを作成すると、Microsoft Entra ユーザー属性がコネクタを介してディレクトリ サーバーに送信され、その新しいオブジェクトの一部になります。

1. Microsoft Entra 管理センターの **[エンタープライズ アプリケーション**] で、 **オンプレミスの ECMA アプリ** アプリケーションを選択し、[プロビジョニング] ページ **を** 選択します。
2. [ **プロビジョニングの編集] を選択します**。
3. [ **マッピング]** を展開し、[ **Microsoft Entra ユーザーのプロビジョニング**] を選択します。 このアプリケーションの属性マッピングを初めて構成した場合、プレースホルダーとして存在するマッピングは 1 つだけです。
4. ディレクトリ サーバーのスキーマがMicrosoft Entra IDで使用可能であることを確認するには、[**詳細オプション]** ドロップダウンを選択し、[**ターゲット ユーザー属性の編集]** を選択します。 構成ウィザードで選択したすべての属性が一覧表示されていることを確認します。 そうでない場合は、スキーマが更新されるまで数分待ってから、ナビゲーション行で **[属性マッピング** ] を選択し、[ **ターゲット ユーザー属性の編集]** をもう一度選択してページを再読み込みします。 一覧に属性が表示されたら、このページからキャンセルしてマッピングの一覧に戻ります。
5. ディレクトリ内のすべてのユーザーは、一意の識別名を持っている必要があります。 属性マッピングを使用して、コネクタで識別名を構築する方法を指定できます。 [ **新しいマッピングの追加] を選択します**。 次の値を使用してマッピングを作成し、式内の識別名を、ターゲット ディレクトリ内の組織単位または他のコンテナーの識別名と一致するように変更します。

    - マッピングの種類: 式
    - 式: `Join("", "CN=", Word([userPrincipalName], 1, "@"), ",DC=Contoso,DC=lab")`
    - ターゲット属性: `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:-dn-`
    - このマッピングを適用する: オブジェクトの作成時のみ
6. ディレクトリ サーバーで、`objectClass` 属性で複数の構造オブジェクト クラス値または補助オブジェクト クラス値を指定する必要がある場合は、その属性にマッピングを追加します。 `objectClass`のマッピングを追加するには、[**新しいマッピングの追加]** を選択します。 次の値を使用してマッピングを作成し、式のオブジェクト クラス名をターゲット ディレクトリ スキーマの名前と一致するように変更します。

    - マッピングの種類: 式
    - POSIX スキーマをプロビジョニングする場合の式: `Split("inetOrgPerson,posixAccount,shadowAccount",",")`
    - ターゲット属性: `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:objectClass`
    - このマッピングを適用する: オブジェクトの作成時のみ
7. ディレクトリ サーバーの次の表の各マッピングについて、[新しいマッピングの追加]選択し、ソース属性とターゲット属性を指定します。 既存のユーザーを含む既存のディレクトリにプロビジョニングする場合は、共通する属性のマッピングを編集して、その属性に対してこの属性 を使用して Match オブジェクトを設定する必要があります。 属性マッピングの詳細については [、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes#understanding-attribute-mapping-properties)。

    POSIX スキーマを含む OpenLDAP の場合は、`gidNumber`、`homeDirectory`、`uid`、および `uidNumber` 属性も指定する必要があります。 各ユーザーには、一意の `uid` と一意の `uidNumber`が必要です。 通常、`homeDirectory` は、ユーザーの userID から派生した式によって設定されます。 たとえば、ユーザーの `uid` がユーザー プリンシパル名から派生した式によって生成される場合、そのユーザーのホーム ディレクトリの値は、ユーザー プリンシパル名からも派生した同様の式によって生成される可能性があります。 また、ユース ケースによっては、すべてのユーザーを同じグループに含めたい場合があるため、定数から `gidNumber` を割り当てます。

    | マッピングの種類 | ソース属性 | ターゲット属性 |
    | --- | --- | --- |
    | 直接 | `displayName` | `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:cn` |
    | 直接 | `surname` | `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:sn` |
    | 直接 | `userPrincipalName` | `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:mail` |
    | 表現 | `ToLower(Word([userPrincipalName], 1, "@"), )` | `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:uid` |
    | 直接 | (ディレクトリに固有の属性) | `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:uidNumber` |
    | 表現 | `Join("/", "/home", ToLower(Word([userPrincipalName], 1, "@"), ))` | `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:homeDirectory` |
    | 定数 | `10000` | `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:gidNumber` |
8. ユーザーの初期ランダム パスワードを設定するマッピングを `urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:userPassword` に追加します。
9. **[保存] を選択します**。

### ディレクトリにプロビジョニングするユーザーに必要な属性があることを確認する

LDAP ディレクトリに既存のユーザー アカウントを持つユーザーがいる場合は、Microsoft Entra ユーザー表現に照合に必要な属性があることを確認する必要があります。

LDAP ディレクトリに新しいユーザーを作成する場合は、それらのユーザーの Microsoft Entra 表現に、ターゲット ディレクトリのユーザー スキーマに必要なソース属性があることを確認する必要があります。 各ユーザーには、一意の `uid` と一意の `uidNumber`が必要です。

ユーザーが Active Directory Domain Services で作成され、そのディレクトリに属性がある場合は、Microsoft Entra Connect または Microsoft Entra Connect クラウド同期を使用して、その属性を Active Directory Domain Services から Microsoft Entra ID に同期して、他のシステムへのプロビジョニングに使用できるように構成できます。

ユーザーが Microsoft Entra ID で作成された場合、ユーザーに格納する必要がある新しい属性ごとに、 [ディレクトリ拡張機能を定義](https://learn.microsoft.com/ja-jp/graph/extensibility-overview?tabs=http#define-the-directory-extension)します。 次に、プロビジョニングする予定の [Microsoft Entra ユーザーを更新](https://learn.microsoft.com/ja-jp/graph/extensibility-overview?tabs=http#update-or-delete-directory-extensions) して、各ユーザーにこれらの属性の値を付与します。

#### PowerShell を使用したユーザーの確認

ユーザーが Microsoft Entra ID で更新されたら、 [Microsoft Graph PowerShell コマンドレット](https://www.powershellgallery.com/packages/Microsoft.Graph) を使用して、ユーザーが必要なすべての属性を持っているチェックを自動化できます。

たとえば、プロビジョニングで、ユーザーが `DisplayName`、`surname`、`extension_656b1c479a814b1789844e76b2f459c3_MyNewProperty`の3つの属性を持つ必要があるとします。 この 3 番目の属性は、`uidNumber`を格納するために使用されます。 `Get-MgUser` コマンドレットを使用して各ユーザーを取得し、必要な属性が存在するかどうかを確認できます。 Graph v1.0 `Get-MgUser` コマンドレットは、既定では、要求で返すプロパティの 1 つとして属性が指定されていない限り、ユーザーのディレクトリ拡張属性を返しません。

このセクションでは、Microsoft Graph PowerShell コマンドレット 使用して Microsoft Entra ID を操作する方法について説明します。

このシナリオで組織が初めてこれらのコマンドレットを使用するときは、テナントで Microsoft Graph PowerShell を使用できるようにするには、グローバル管理者ロールである必要があります。 後続の対話では、次のような低い特権ロールを使用できます。

- ユーザー管理者(新しいユーザーの作成が予想される場合)。
- アプリケーション ロールの割り当てを管理しているだけの場合は、アプリケーション [管理者または ID ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)。

1. PowerShell を開きます。
2. [Microsoft Graph PowerShell モジュール](https://www.powershellgallery.com/packages/Microsoft.Graph)がまだインストールされていない場合は、次のコマンドを使用して、`Microsoft.Graph.Users` モジュールなどをインストールします。

    ```powershell
    Install-Module Microsoft.Graph
    ```

    モジュールが既にインストールされている場合は、最新バージョンを使用していることを確認します。

    ```powershell
    Update-Module microsoft.graph.users,microsoft.graph.identity.governance,microsoft.graph.applications
    ```
3. Microsoft Entra ID に接続します。

    ```powershell
    $msg = Connect-MgGraph -ContextScope Process -Scopes "User.Read.All,Application.ReadWrite.All,AppRoleAssignment.ReadWrite.All,EntitlementManagement.ReadWrite.All"
    ```
4. ユーザーの一覧と確認する属性を作成します。

    ```powershell
    $userPrincipalNames = (
     "alice@contoso.com",
     "bob@contoso.com",
     "carol@contoso.com" )
    
    $requiredBaseAttributes = ("DisplayName","surname")
    $requiredExtensionAttributes = ("extension_656b1c479a814b1789844e76b2f459c3_MyNewProperty")
    ```
5. 各ユーザーのディレクトリに対してクエリを実行します。

    ```powershell
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

1. そのディレクトリ内のユーザーのうち、Linux 認証を使用するユーザーのスコープ内にあるユーザーを特定します。 この選択は、Linux システムの構成によって異なります。 一部の構成では、LDAP ディレクトリに存在するすべてのユーザーが有効なユーザーです。 その他の構成では、ユーザーが特定の属性を持っているか、そのディレクトリ内のグループのメンバーであることが必要になる場合があります。
2. LDAP ディレクトリから CSV ファイルにユーザーのサブセットを取得するコマンドを実行します。 出力に、Microsoft Entra ID との照合に使用されるユーザーの属性が含まれていることを確認します。 これらの属性の例としては、従業員 ID、アカウント名または `uid`、電子メール アドレスがあります。
3. 必要に応じて、ユーザーの一覧を含む CSV ファイルを、 [Microsoft Graph PowerShell コマンドレット](https://www.powershellgallery.com/packages/Microsoft.Graph) がインストールされたシステムに転送します。
4. ディレクトリから取得したすべてのユーザーの一覧が作成されたので、ディレクトリのユーザーを Microsoft Entra ID のユーザーと照合します。 先に進む前に、ソース システムとターゲット システムの一致するユーザー に関する情報を確認します。

#### Microsoft Entra ID でユーザーの ID を取得する

このセクションでは、Microsoft Graph PowerShell コマンドレット 使用して Microsoft Entra ID を操作する方法について説明します。

このシナリオで組織が初めてこれらのコマンドレットを使用するときは、テナントで Microsoft Graph PowerShell を使用できるようにするには、グローバル管理者ロールである必要があります。 後続の対話では、次のような低い特権ロールを使用できます。

- ユーザー管理者(新しいユーザーの作成が予想される場合)。
- アプリケーション ロールの割り当てを管理しているだけの場合は、アプリケーション [管理者または ID ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)。

1. PowerShell を開きます。
2. [Microsoft Graph PowerShell モジュール](https://www.powershellgallery.com/packages/Microsoft.Graph)がまだインストールされていない場合は、次のコマンドを使用して、`Microsoft.Graph.Users` モジュールなどをインストールします。

    ```powershell
    Install-Module Microsoft.Graph
    ```

    モジュールが既にインストールされている場合は、最新バージョンを使用していることを確認します。

    ```powershell
    Update-Module microsoft.graph.users,microsoft.graph.identity.governance,microsoft.graph.applications
    ```
3. Microsoft Entra ID に接続します。

    ```powershell
    $msg = Connect-MgGraph -ContextScope Process -Scopes "User.ReadWrite.All,Application.ReadWrite.All,AppRoleAssignment.ReadWrite.All,EntitlementManagement.ReadWrite.All"
    ```
4. このコマンドを初めて使用する場合は、Microsoft Graph コマンド ライン ツールにこれらのアクセス許可を付与することを許可する必要があります。
5. アプリケーションのデータ ストアから取得したユーザーの一覧を PowerShell セッションに読み取ります。 ユーザーの一覧が CSV ファイルに含まれている場合は、PowerShell コマンドレット `Import-Csv` を使用し、前のセクションのファイルの名前を引数として指定できます。

    たとえば、SAP Cloud Identity Services から取得したファイルの名前が *Users-exported-from-sap.csv* で、現在のディレクトリにある場合は、次のコマンドを入力します。

    ```powershell
    $filename = ".\Users-exported-from-sap.csv"
    $dbusers = Import-Csv -Path $filename -Encoding UTF8
    ```

    別の例として、データベースまたはディレクトリを使用している場合、ファイルの名前が *users.csv* で現在のディレクトリにある場合は、次のコマンドを入力します。

    ```powershell
    $filename = ".\users.csv"
    $dbusers = Import-Csv -Path $filename -Encoding UTF8
    ```
6. Microsoft Entra ID のユーザーの属性と一致する *users.csv* ファイルの列を選択します。

    SAP Cloud Identity Services を使用している場合、既定のマッピングは、Microsoft Entra ID 属性 `userName`を持つ SAP SCIM 属性 `userPrincipalName` です。

    ```powershell
    $db_match_column_name = "userName"
    $azuread_match_attr_name = "userPrincipalName"
    ```

    別の例として、データベースまたはディレクトリを使用している場合、`EMail`という名前の列の値がMicrosoft Entraの属性`userPrincipalName`と同じであるデータベース内のユーザーがいることがあります。

    ```powershell
    $db_match_column_name = "EMail"
    $azuread_match_attr_name = "userPrincipalName"
    ```
7. Microsoft Entra ID でこれらのユーザーの ID を取得します。

    次の PowerShell スクリプトでは、前に指定した `$dbusers`、`$db_match_column_name`、および `$azuread_match_attr_name` 値を使用します。 Microsoft Entra ID にクエリを実行して、ソース ファイル内の各レコードに対して一致する値を持つ属性を持つユーザーを見つけます。 ソース SAP Cloud Identity Services、データベース、またはディレクトリから取得したファイルに多数のユーザーが存在する場合、このスクリプトが完了するまでに数分かかる場合があります。 Microsoft Entra ID に値を持つ属性がなく、`contains` またはその他のフィルター式を使用する必要がある場合は、このスクリプトをカスタマイズし、以下の手順 11 で別のフィルター式を使用する必要があります。

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

    次の PowerShell スクリプトでは、見つからないレコードの数が表示されます。

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
9. スクリプトが完了すると、データ ソースのレコードが Microsoft Entra ID に存在しなかった場合にエラーが表示されます。 アプリケーションのデータ ストアのユーザーのすべてのレコードを Microsoft Entra ID のユーザーとして配置できない場合は、一致しなかったレコードとその理由を調査する必要があります。

    たとえば、アプリケーションのデータ ソースで対応する `mail` プロパティが更新されずに、Microsoft Entra ID で他のユーザーのメール アドレスと userPrincipalName が変更されている可能性があります。 または、ユーザーは既に組織を離れているが、アプリケーションのデータ ソースにまだ存在している可能性があります。 または、アプリケーションのデータ ソースにベンダーまたはスーパー管理者アカウントがあり、Microsoft Entra ID の特定のユーザーに対応していない場合があります。
10. Microsoft Entra ID に配置できなかったユーザー、またはアクティブでサインインできなかったユーザーがいて、SAP Cloud Identity Services、データベース、またはディレクトリでアクセス権を確認したり、属性を更新したりする場合は、アプリケーション、一致するルールを更新するか、Microsoft Entra ユーザーを更新または作成する必要があります。 行う変更の詳細については、Microsoft Entra IDのユーザーと一致しなかったアプリケーションのマッピングとユーザー アカウントの管理 に関するページを参照してください。

    Microsoft Entra ID でユーザーを作成するオプションを選択した場合は、次のいずれかを使用してユーザーを一括で作成できます。

    - [「Microsoft Entra 管理センターでユーザーを一括作成する」で](https://learn.microsoft.com/ja-jp/entra/identity/users/users-bulk-add)説明されている CSV ファイル
    - [New-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/new-mguser?view=graph-powershell-1.0#examples&preserve-view=true) コマンドレット

    これらの新しいユーザーには、後でアプリケーション内の既存のユーザーと一致させるために Microsoft Entra ID に必要な属性と、Microsoft Entra ID で必要な属性 (`userPrincipalName`、`mailNickname`、`displayName`など) が設定されていることを確認します。 `userPrincipalName` は、ディレクトリ内のすべてのユーザー間で一意である必要があります。

    たとえば、データベースに、`EMail` という名前の列の値が Microsoft Entra ユーザー プリンシパル名として使用する値、列 `Alias` の値に Microsoft Entra ID のメールニックネーム、列 `Full name` の値にユーザーの表示名が含まれているユーザーがいるとします。

    ```powershell
    $db_display_name_column_name = "Full name"
    $db_user_principal_name_column_name = "Email"
    $db_mail_nickname_column_name = "Alias"
    ```

    次に、このスクリプトを使用して、MICROSOFT Entra ID のユーザーと一致しなかった SAP Cloud Identity Services、データベース、またはディレクトリ内のユーザーに対して Microsoft Entra ユーザーを作成できます。 このスクリプトを変更して、組織で必要な Microsoft Entra 属性を追加する必要がある場合や、`$azuread_match_attr_name` が `mailNickname` でも `userPrincipalName`でもない場合は、その Microsoft Entra 属性を指定する必要があります。

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
11. 不足しているユーザーを Microsoft Entra ID に追加した後、手順 7 のスクリプトをもう一度実行します。 次に、手順 8. のスクリプトを実行します。 エラーが報告されていないことを確認します。

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

### Microsoft Entra ID でアプリケーションにユーザーを割り当てる

Microsoft Entra ECMA コネクタ ホストが Microsoft Entra ID と通信し、属性マッピングが構成されたので、プロビジョニングの対象となるユーザーの構成に進むことができます。

重要

ハイブリッド ID 管理者ロールを使用してサインインした場合は、少なくともこのセクションのアプリケーション管理者ロールを持つアカウントでサインアウトしてサインインする必要があります。 ハイブリッド ID 管理者ロールには、アプリケーションにユーザーを割り当てるアクセス許可がありません。

LDAP ディレクトリに既存のユーザーが存在する場合は、それらの既存のユーザーに対するアプリケーション ロールの割り当てを作成する必要があります。 を使用してアプリケーション ロールの割り当てを一括で作成する方法の詳細については、Microsoft Entra IDでアプリケーションの既存ユーザーを管理する を参照してください。

LDAP ディレクトリ内の既存のユーザーをまだ更新しない場合は、必要な属性を持ち、ディレクトリ サーバーにプロビジョニングされるテスト ユーザーを Microsoft Entra ID から選択します。

1. ユーザーが選択するプロパティがすべて、ディレクトリ サーバー スキーマの必要な属性にマップされていることを確認します。
2. Azure portal で、エンタープライズ アプリケーション選択します。
3. オンプレミスの ECMA アプリ アプリケーション を選択します。
4. 左側の [ **管理**] で、[ **ユーザーとグループ**] を選択します。
5. [ **ユーザー/グループの追加]** を選択します。 [Image: ユーザーの追加を示すスクリーンショット。]
6. [ **ユーザー**] で[ **選択なし**] を選択します。 [Image: [選択なし] を示すスクリーンショット。]
7. 右側からユーザーを選択し、[] ボタンを 選択します。「ユーザーを選択」と表示されるスクリーンショットを示します。
8. [ **割り当て**] を選択します。 [Image: ユーザーの割り当てを示すスクリーンショット。]

### プロビジョニングのテスト

属性がマップされ、最初のユーザーが割り当てられたので、いずれかのユーザーでオンデマンド プロビジョニングをテストできます。

1. Microsoft Entra ECMA コネクタ ホストを実行しているサーバーで、[開始] を選択 **します**。
2. 「 **run」** と入力し、ボックスに **「services.msc** 」と入力します。
3. **サービス**の一覧で、**Microsoft Entra Connect プロビジョニング エージェント** サービスと **Microsoft ECMA2Host** サービスの両方が実行されていることを確認します。 そうでない場合は、[ **スタート]** を選択します。
4. Azure portal で、エンタープライズ アプリケーション選択します。
5. オンプレミスの ECMA アプリ アプリケーション を選択します。
6. 左側の [ **プロビジョニング**] を選択します。
7. **オンデマンドでプロビジョン**を選択します。
8. いずれかのテスト ユーザーを検索し、[ **プロビジョニング**] を選択します。 [Image: オンデマンド プロビジョニングのテストを示すスクリーンショット。]
9. 数秒後、ターゲット システム でユーザーが正常に作成された メッセージが表示され、ユーザー属性の一覧が表示されます。 代わりにエラーが表示される場合は、 プロビジョニング エラーのトラブルシューティングを参照してください。

### ユーザーのプロビジョニングを開始する

オンデマンド プロビジョニングのテストが成功したら、残りのユーザーを追加します。 を使用してアプリケーション ロールの割り当てを一括で作成する方法の詳細については、Microsoft Entra IDでアプリケーションの既存ユーザーを管理する を参照してください。

1. Azure portal で、アプリケーションを選択します。
2. 左側の [ **管理**] で、[ **ユーザーとグループ**] を選択します。
3. すべてのユーザーがアプリケーション ロールに割り当てられていることを確認します。
4. プロビジョニング構成ページに戻ります。
5. スコープが割り当てられたユーザーとグループのみに設定されていることを確認し、プロビジョニングの状態を [オン] にして、[保存] 選択します。
6. プロビジョニングが開始されるまで数分待ってください。 最大で 40 分かかる場合があります。 次のセクションで説明するように、プロビジョニング ジョブが完了したら、

### プロビジョニング エラーのトラブルシューティング

エラーが表示された場合は、[プロビジョニング ログ表示] を選択します。 ログで、状態が [エラー]行を探し、その行を選択します。

エラー メッセージが **[ユーザーの作成に失敗しました**] の場合は、ディレクトリ スキーマの要件に照らして表示される属性を確認します。

詳細については、「 **トラブルシューティングと推奨事項** 」タブに変更してください。

トラブルシューティング エラー メッセージに objectClass 値が `invalid per syntax`されている場合は、`objectClass` 属性へのプロビジョニング属性マッピングに、ディレクトリ サーバーによって認識されるオブジェクト クラスの名前のみが含まれていることを確認します。

その他のエラーについては、 [オンプレミスアプリケーションプロビジョニングのトラブルシューティングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ecma-troubleshoot)参照してください。

このアプリケーションへのプロビジョニングを一時停止する場合は、プロビジョニング構成ページで、プロビジョニングの状態を オフに変更し、[保存] 選択します。 このアクションにより、プロビジョニング サービスが将来実行されなくなります。

### ユーザーが正常にプロビジョニングされたことを確認する

待機した後、ディレクトリ サーバーを調べて、ユーザーがプロビジョニングされていることを確認します。 ディレクトリ サーバーに対して実行するクエリは、ディレクトリ サーバーが提供するコマンドによって異なります。

次の手順は、Linux 上の OpenLDAP を確認する方法を示しています。

1. OpenLDAP を使用して、システム上のコマンド シェルを使用してターミナル ウィンドウを開きます。
2. コマンド `ldapsearch -D "cn=admin,dc=contoso,dc=lab" -W -s sub -b dc=contoso,dc=lab -LLL (objectclass=inetOrgPerson)` を入力します
3. 結果の LDIF に Microsoft Entra ID からプロビジョニングされたユーザーが含まれていることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/on-premises-ldap-connector-prepare-directory"} -->
## Active Directory Lightweight Directory Services への Microsoft Entra プロビジョニングの準備 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-prepare-directory
- Service: entra-id / app-provisioning
- Article date: 2025-04-09
- Summary: このドキュメントでは、LDAP ディレクトリの例として、Active Directory Lightweight Directory Services にユーザーをプロビジョニングするように Microsoft Entra ID を構成する方法について説明します。

以下のドキュメントでは、Active Directory Lightweight Directory Services (AD LDS) のインストールを準備する方法を示すチュートリアル情報を提供します。 これは、トラブルシューティング用の LDAP ディレクトリの例として、または [Microsoft Entra ID から LDAP ディレクトリにユーザーをプロビジョニングする方法](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure)を示すために使用できます。

### LDAP ディレクトリを準備する

ディレクトリ サーバーがまだない場合は、テスト AD LDS 環境の作成に役立つように次の情報が提供されます。 このセットアップでは、PowerShell と応答ファイルADAMInstall.exeを使用します。 このドキュメントでは、データ の詳細についてはAD LDS。 Microsoft Active Directory ライトウェイト ディレクトリ サービス ([AD LDS](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2012-r2-and-2012/hh831593%28v=ws.11%29))

AD LDS または別のディレクトリ サーバーが既にある場合は、このコンテンツをスキップして、ECMA コネクタ ホストをインストールおよび構成するための[チュートリアル: ECMA Connector Host 汎用 LDAP コネクタ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure)に関するページ進むことができます。

#### SSL 証明書、テスト ディレクトリを作成し、証明書をAD LDS。

付録 A の PowerShell スクリプト を使用します。スクリプトによって、次のアクションが実行されます。

1. LDAP コネクタが使用する自己署名証明書を作成します。
2. 機能インストール ログのディレクトリを作成します。
3. 個人用ストア内の証明書をディレクトリにエクスポートします。
4. ローカル コンピューターの信頼されたルートに証明書をインポートします。
5. 仮想マシンに AD LDS ロールをインストールします。

LDAP コネクタをテストする Windows Server 仮想マシンで、コンピューター名と一致するようにスクリプトを編集し、管理者特権で Windows PowerShell を使用してスクリプトを実行します。

#### のインスタンスを作成AD LDS

ロールがインストールされたので、AD LDS のインスタンスを作成する必要があります。 インスタンスを作成するには、提供されている次の応答ファイルを使用できます。 このファイルにより、UI を使用せずに、インスタンスが自動的にインストールされます。

付録 B の内容をメモ帳にコピーし、**answer.txt** という名前のファイルとして **"C:\Windows\ADAM"** に保存します。

次に、管理者特権でコマンド プロンプトを開き、下の実行可能を実行します。

```
C:\Windows\ADAM> ADAMInstall.exe /answer:answer.txt
```

#### AD LDS のコンテナーとサービス アカウントを作成する

付録 C の PowerShell スクリプトを使用します。スクリプトによって、次のアクションが実行されます。

1. LDAP コネクタで使用されるサービス アカウントのコンテナーを作成します。
2. クラウドユーザーのための、ユーザーが設定されるコンテナを作成します。
3. サービス アカウントを AD LDS で作成します。
4. サービス アカウントを有効にします。
5. サービス アカウントを AD LDS 管理者ロールに追加します。

Windows Server 仮想マシンでは、 を使用して LDAP コネクタで、管理者特権を持つ Windows PowerShellを使用してスクリプトを実行します。

#### SSL 証明書に NETWORK SERVICE の読み取りアクセス許可を付与します

SSL が機能するには、新しく作成された証明書に対するネットワーク サービスの読み取りアクセス許可を付与する必要があります。 アクセス許可を付与するには、次の手順を使用します。

1. **C:\ProgramData\Microsoft\Crypto\Keys** に移動します。
2. ここに配置されているシステム ファイルを右クリックします。 これは guid です。 このコンテナーには、証明書が格納されています。
3. プロパティの選択。
4. 上部で **[サンプル]** タブを選択します。
5. **[編集]** を選択します。
6. **追加**を選択します。
7. ボックスに「Network **Service」と入力し、[** 名前の確認 **] を選択します**。
8. 一覧から **NETWORK SERVICE** を選択し、**OK**を選択します。
9. **[OK]** を選択します。
10. ネットワーク サービス アカウントに読み取りと読み取りおよび実行のアクセス許可があることを確認して、**[適用]** と **[OK]** を選択します。

詳細については、 [AD LDS の SSL 要件を介した LDAP の構成を](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2008-r2-and-2008/cc725767%28v=ws.10%29)参照してください。

#### クライアントとの SSL 接続を確認AD LDS

証明書を構成し、ネットワーク サービス アカウントのアクセス許可を付与したので、接続をテストして、動作を確認します。

1. サーバー マネージャーを開き、AD LDS を選択します。
2. AD LDS のインスタンスを右クリックし、ポップアップから ldp.exe を選択します。 [Image: Ldp ツールの場所を示すスクリーンショット。]
3. ldp.exe の上部にある [**接続**] を選択し、 **Connect**] をクリックします。
4. 次の情報を入力し、**[OK]**を選択します。
    - サーバー: APP3
    - ポートは 80 にします。
    - [SSL] ボックスにチェックを入れます[Image: Ldp ツールの接続構成を示すスクリーンショット。]
5. 上部の [ **接続** ] で [ **バインド**] を選択します。
6. 既定値のままにして、**[OK]** を選択します。
7. これで、 インスタンスに正常にバインドされます。

#### ローカル パスワード ポリシーを無効にする

現時点では、LDAP コネクタは空のパスワードでユーザーをプロビジョニングします。 このプロビジョニングは、サーバー上のローカル パスワード ポリシーを満たしていないので、テスト目的で無効にします。 パスワードの複雑さを無効にするには、ドメインに参加していないサーバーで次の手順を使用します。

重要

パスワード同期はオンプレミスの LDAP プロビジョニングの機能ではないので、AD LDS をフェデレーション アプリケーションと共に使用する場合、AD DS と組み合わせて使用する場合、または AD LDS のインスタンスで既存のユーザーを更新する場合は、AD LDS を使用するように推奨しています。

1. サーバーで、**[スタート**]、**[実行**] を順に選択し、**[gpedit.msc**] を実行します。
2. **ローカル グループ ポリシー エディター**で、[コンピューターの構成] &gt; [Windows の設定] &gt; [セキュリティの設定] &gt; [アカウント ポリシー] &gt; [パスワード ポリシー] の順に移動します
3. 右側の [ **パスワードは、複雑さの要件を満たしている必要があり** ます] をダブルクリックし、[ **無効**] を選択します。 [Image: 複雑さの要件設定のスクリーンショット。]
4. [**適用**] を選択して、[**OK**] を選択します。
5. ローカル グループ ポリシー エディターを開きます。

次に、[Microsoft Entra ID から LDAP ディレクトリにユーザーをプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure)するためのガイダンスへ進み、プロビジョニング エージェントをダウンロードして構成します。

### 付録 A-PowerShell スクリプトをインストール AD LDS

次の PowerShell スクリプトを使用して、Active Directory ライトウェイト ディレクトリ サービスのインストールを自動化できます。 環境に合わせてスクリプトを編集する必要があります。特に、`APP3` をコンピューターのホスト名に変更します。

```powershell
# Filename: 1_SetupADLDS.ps1
# Description: Creates a certificate that will be used for SSL and installs Active Directory Lighetweight Directory Services.
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
$DNSName = 'APP3'
$CertLocation = 'cert:\LocalMachine\MY'
$logpath = "c:\" 
$dirname = "test"
$dirtype = "directory"
$featureLogPath = "c:\test\featurelog.txt" 

#Create a new self-signed certificate
New-SelfSignedCertificate -DnsName $DNSName -CertStoreLocation $CertLocation

#Create directory
New-Item -Path $logpath -Name $dirname -ItemType $dirtype

#Export the certificate from the local machine personal store
Get-ChildItem -Path cert:\LocalMachine\my | Export-Certificate -FilePath c:\test\allcerts.sst -Type SST

#Import the certificate in to the trusted root
Import-Certificate -FilePath "C:\test\allcerts.sst" -CertStoreLocation cert:\LocalMachine\Root

#Install AD LDS
start-job -Name addFeature -ScriptBlock { 
Add-WindowsFeature -Name "ADLDS" -IncludeAllSubFeature -IncludeManagementTools 
 } 
Wait-Job -Name addFeature 
Get-WindowsFeature | Where installed >>$featureLogPath

```

### 付録 B-応答ファイル

このファイルは、AD LDS のインスタンスを自動化および作成するために使用されます。 このファイルは、環境に合わせて編集します。特に、`APP3` をサーバーのホスト名に変更します。

重要

このスクリプトでは、AD LDS サービス アカウントにローカル管理者を使用します。 インストール中にパスワードの入力を求められます。

メンバーやスタンドアロン サーバーではなくドメイン コントローラーに AD LDS をインストールする場合、LocalLDAPPortToListenOn と LocalSSLPortToListonOn は、LDAP と LDAP over SSL 用の既知のポート以外に変更する必要があります。 たとえば、LocalLDAPPortToListenOn=51300 と LocalSSLPortToListenOn=51301 を使用します。

```
 [ADAMInstall]
 InstallType=Unique
 InstanceName=AD-APP-LDAP
 LocalLDAPPortToListenOn=389
 LocalSSLPortToListenOn=636
 NewApplicationPartitionToCreate=CN=App,DC=contoso,DC=lab
 DataFilesPath=C:\Program Files\Microsoft ADAM\AD-APP-LDAP\data
 LogFilesPath=C:\Program Files\Microsoft ADAM\AD-APP-LDAP\data
 ServiceAccount=APP3\Administrator
 ServicePassword=\*
 AddPermissionsToServiceAccount=Yes
 Administrator=APP3\Administrator
 ImportLDIFFiles="MS-User.LDF"
 SourceUserName=APP3\Administrator
 SourcePassword=Pa$$Word1
```

### 付録 C-PowerShell スクリプト AD LDS の設定

コンテナーとサービスアカウントを使用して AD LDS を設定する PowerShell スクリプト。

```powershell
# Filename: 2_PopulateADLDS.ps1
# Description: Populates our AD LDS environment with 2 containers and a service account

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
# Create service accounts container
New-ADObject -Name "ServiceAccounts" -Type "container" -Path "CN=App,DC=contoso,DC=lab" -Server "APP3:389"
Write-Output "Creating ServiceAccounts container"

# Create cloud users container
New-ADObject -Name "CloudUsers" -Type "container" -Path "CN=App,DC=contoso,DC=lab" -Server "APP3:389"
Write-Output "Creating CloudUsers container"

# Create a new service account
New-ADUser -name "svcAccountLDAP" -accountpassword (ConvertTo-SecureString -AsPlainText 'Pa$$1Word' -Force) -Displayname "LDAP Service Account" -server 'APP3:389' -path "CN=ServiceAccounts,CN=App,DC=contoso,DC=lab"
Write-Output "Creating service account"

# Enable the new service account
Enable-ADAccount -Identity "CN=svcAccountLDAP,CN=ServiceAccounts,CN=App,DC=contoso,DC=lab" -Server "APP3:389"
Write-Output "Enabling service account"

# Add the service account to the Administrators role
Get-ADGroup -Server "APP3:389" -SearchBase "CN=Administrators,CN=Roles,CN=App,DC=contoso,DC=lab" -Filter "name -like 'Administrators'" | Add-ADGroupMember -Members "CN=svcAccountLDAP,CN=ServiceAccounts,CN=App,DC=contoso,DC=lab"
Write-Output "Adding service account to Administrators role"

```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/on-premises-migrate-microsoft-identity-manager"} -->
## Microsoft Entra ECMA コネクタ ホストで使用するために Microsoft Identity Manager コネクタをエクスポートする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-migrate-microsoft-identity-manager
- Service: entra-id / app-provisioning
- Article date: 2025-04-09
- Summary: Microsoft Entra ECMA コネクタ ホストで使用するために MIM Sync からコネクタを作成およびエクスポートする方法を説明します。

Microsoft Identity Manager 同期サービス (MIM 同期) のインストールから、特定のコネクタの構成を Microsoft Entra ECMA コネクタ ホストにインポートできます。

### MIM Sync のコネクタ構成を作成する

このセクションは、コネクタを使用して MIM Sync を設定する場合の説明のために含まれています。 MIM 同期のインストールは初期構成にのみ使用され、Microsoft Entra ID からの継続的な同期には使用されません。 ECMA コネクタと MIM Sync が既に構成されている場合は、次のセクションに進みます。

1. Windows Server 2016 サーバーを準備します。これは、Microsoft Entra ECMA コネクタ ホストで実行するために使用されるサーバーとは異なります。 このホスト サーバーでは、SQL Server 2016 データベースが同じ場所にあるか、SQL Server 2016 データベースにネットワーク接続されている必要があります。 このサーバーを設定する 1 つの方法は、イメージ **Windows Server 2016 の SQL Server 2016 SP1 Standard** を使用して Azure 仮想マシンをデプロイすることです。 このサーバーは、セットアップ目的でのリモート デスクトップ アクセス以外ではインターネット接続を必要としません。
2. MIM Sync のインストール中に使用するアカウントを作成します。 これを、その Windows Server インスタンス上のローカル アカウントにすることが可能です。 ローカル アカウントを作成するために、 **[コントロール パネル]**&gt;**[ユーザー アカウント]** を開いて、**mimsync** というユーザー アカウントを追加します。
3. 前の手順で作成したアカウントをローカルの Administrators グループに追加します。
4. 以前に作成したアカウントに、サービスを実行する機能を与えます。 **[ローカル セキュリティ ポリシー]** を起動し、 **[ローカル ポリシー]**&gt;**[ユーザー権利の割り当て]**&gt;**[サービスとしてログオン]** を選択します。 前述のアカウントを追加します。
5. このホストに MIM Sync をインストールします。
6. MIM Sync のインストールが完了したら、一度サインアウトしてから再度サインインします。
7. MIM Sync と同じサーバーにコネクタをインストールします。説明のため、[Microsoft ダウンロード センター](https://www.microsoft.com/download/details.aspx?id=51495)からダウンロードできる Microsoft 提供の SQL または LDAP コネクタのうちの 1 つを使用します。
8. 同期サービスの UI を起動します。 **[管理エージェント]** を選択します。 **[作成]** を選択し、コネクタ管理エージェントを指定します。 ECMA ベースのコネクタ管理エージェントを選択してください。
9. コネクタに名前を付け、コネクタにデータをインポートおよびエクスポートするために必要なパラメーターを構成します。 コネクタが、ユーザーまたは人物オブジェクト型の単一値文字列属性をインポートおよびエクスポートできるよう構成してください。

### MIM Sync からコネクタ構成をエクスポートする

1. MIM Sync サーバー コンピューターで、まだ実行されていない場合は、同期サービスの UI を起動します。 **[管理エージェント]** を選択します。
2. コネクタを選択し、 **[管理エージェントのエクスポート]** を選択します。 XML ファイルと、コネクタの DLL および関連ソフトウェアを、ECMA コネクタ ホストを保持する Windows Server に保存します。

この時点で、MIM Sync サーバーは不要になります。

### コネクタ構成をインポートする

1. 「[SQL ベースのアプリケーションにユーザーをプロビジョニングする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-sql-connector-configure#3-install-and-configure-the-azure-ad-connect-provisioning-agent)」または [LDAP ディレクトリへのユーザーのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure#install-and-configure-the-azure-ad-connect-provisioning-agent)に関する記事を使用して、ECMA コネクタ ホストおよびプロビジョニング エージェントを Windows Server にインストールします。
2. Microsoft Entra ECMA コネクタ ホストにより実行されるアカウントで Windows Server にサインインします。
3. C:\Program Files\Microsoft ECMA2host\Service\ECMA ディレクトリに移動します。 そのディレクトリに既に 1 つ以上の DLL が存在していることを確認します。 これらの DLL は、Microsoft が提供するコネクタに対応しています。
4. コネクタの MA DLL とその前提条件 DLL を、Service ディレクトリの同じ ECMA サブディレクトリにコピーします。
5. C:\Program Files\Microsoft ECMA2Host\Wizard ディレクトリに移動します。 プログラム Microsoft.ECMA2Host.ConfigWizard.exe を実行して ECMA コネクタ ホストの構成を設定します。
6. コネクタの一覧が表示された新しいウィンドウが表示されます。 既定では、コネクタは存在しません。 **[新しいコネクタ]** を選択します。
7. 前に MIM Sync からエクスポートした管理エージェント XML ファイルを指定します。 「[SQL ベースのアプリケーションにユーザーをプロビジョニングする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-sql-connector-configure#6-create-a-generic-sql-connector)」または [LDAP ディレクトリへのユーザーのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure#configure-a-generic-ldap-connector)に関する記事の「コネクタを作成する」のセクションで、構成とスキーマ マッピングの手順に進みます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/on-premises-powershell-connector"} -->
## PowerShell を使用したアプリケーションに対する Microsoft Entra プロビジョニング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-powershell-connector
- Service: entra-id / app-provisioning
- Article date: 2025-04-09
- Summary: このドキュメントでは、Windows PowerShell ベースの API を提供する外部システムでユーザーをプロビジョニングするために、Microsoft Entra ID を構成する方法について説明します。

次のドキュメントでは、構成とチュートリアルの情報を提供します。 ここでは、汎用 PowerShell コネクタと拡張接続 (ECMA) コネクタ ホストを使用して、Microsoft Entra ID を Windows PowerShell ベースの API を提供する外部システムと統合する方法を示します。

詳細については、[Windows PowerShell コネクタのテクニカル リファレンスを参照してください](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/reference/microsoft-identity-manager-2016-connector-powershell)。

### PowerShell を使用したプロビジョニングの前提条件

これ以降のセクションでは、このチュートリアルの前提条件について詳しく説明します。

#### PowerShell セットアップ ファイルをダウンロードする

[GitHub リポジトリから PowerShell セットアップ ファイルをダウンロードします](https://github.com/microsoft/MIMPowerShellConnectors/tree/master/src/ECMA2HostCSV)。 セットアップ ファイルは、構成ファイル、入力ファイル、スキーマ ファイル、使用するスクリプトで構成されています。

#### オンプレミスの前提条件

コネクタは、ECMA Connector Host と Windows PowerShell の機能の間の橋渡しをします。 コネクタを使用する前に、コネクタをホストするサーバーに次のものがインストールされていることを確認してください。

- Windows Server 2016 以降のバージョン。
- プロビジョニング エージェントをホストするための 3 GB 以上の RAM。
- .NET Framework 4.7.2
- Windows PowerShell 2.0、3.0、4.0
- ホスティング サーバー、コネクタ、PowerShell スクリプトで操作するターゲット システムの間の接続。
- サーバーの実行ポリシーで、Windows PowerShell スクリプトの実行をコネクタに許可するように構成する必要があります。 コネクタが実行するスクリプトにデジタル署名がない場合を除き、コマンド`Set-ExecutionPolicy -ExecutionPolicy RemoteSigned`
- このコネクタを展開するには、1 つ以上の PowerShell スクリプトが必要です。 一部の Microsoft 製品では、このコネクタで使用するためのスクリプトが提供されていることがあり、それらのスクリプトのサポート説明文はその製品によって提供されます。 このコネクタで使用する独自のスクリプトを開発する場合は、これらのスクリプトを開発および管理するために [Extensible Connectivity Management Agent API](https://learn.microsoft.com/ja-jp/previous-versions/windows/desktop/forefront-2010/hh859557%28v=vs.100%29?redirectedfrom=MSDN) に精通している必要があります。 運用環境で独自のスクリプトを使用してサード パーティのシステムと統合する場合は、その統合に必要なヘルプ、ガイダンス、サポートを得るためにサード パーティ ベンダーまたはデプロイ パートナーと協力することをお勧めします。

#### クラウドの要件

- Microsoft Entra ID P1 あるいは Premium P2 (または EMS E3 または E5) を使用する、Microsoft Entra テナント。 この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 要件に適したライセンスを見つけるには、「 [Microsoft Entra ID の一般公開機能を比較](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)する」を参照してください。
- プロビジョニング エージェントを構成するためのハイブリッド ID 管理者ロールと、Azure portal でプロビジョニングを構成するためのアプリケーション管理者またはクラウド アプリケーション管理者ロール。
- プロビジョニング対象の Microsoft Entra ユーザーには、スキーマが必要とする属性が事前に設定されている必要があります。

### Microsoft Entra Connect プロビジョニング エージェント パッケージのダウンロード、インストール、構成

プロビジョニング エージェントをダウンロードしてあり、別のオンプレミス アプリケーション用に構成済みの場合は、次のセクションに進んでください。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)としてサインインします。
2. **Entra ID**&gt;**Entra Connect**&gt;**Cloud sync**&gt;**Agents** に移動します。

[Image: 新しい UX 画面のスクリーンショット。]

1. [ **オンプレミス エージェントのダウンロード**] を選択し、サービス条件を確認してから、[ **同意する] を**選択してダウンロードします。

    注

    オンプレミス アプリケーションのプロビジョニングと、Microsoft Entra Connect クラウド同期または人事主導のプロビジョニングには、異なるプロビジョニング エージェントを使用してください。 3 つのすべてのシナリオを同じエージェントで管理することはできません。
2. プロビジョニング エージェント インストーラーを開き、サービス条件に同意して、[インストール] を選択 **します**。
3. Microsoft Entra プロビジョニング エージェント構成ウィザードが開いたら、[ **拡張機能の選択** ] タブに進み、有効にする拡張機能の入力を求められたら **[オンプレミス アプリケーション プロビジョニング** ] を選択します。
4. プロビジョニング エージェントは、オペレーティング システムの Web ブラウザーを使用して、Microsoft Entra ID (および場合によっては組織の ID プロバイダー) に対する認証を行うためのポップアップ ウィンドウを表示します。 Windows Server 上のブラウザーとして Internet Explorer を使用している場合、JavaScript を正しく実行できるように、ブラウザーの信頼済みサイト リストに Microsoft Web サイトを追加することが必要な場合があります。
5. 承認を求められたら、Microsoft Entra 管理者の資格情報を入力します。 ユーザーには、少なくとも [ハイブリッド ID 管理者ロールが](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) 必要です。
6. [ **確認]** を選択して設定を確認します。 インストールが成功したら、[ **終了**] を選択し、プロビジョニング エージェント パッケージ インストーラーを閉じてもかまいません。

### オンプレミスの ECMA アプリを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。
3. [ **新しいアプリケーション]** を選択します。
4. **オンプレミスの ECMA アプリ** アプリケーションを検索し、アプリに名前を付け、[**作成**] を選択してテナントに追加します。
5. アプリケーションの **[プロビジョニング** ] ページに移動します。
6. [ **作業の開始] を選択します**。
7. [ **プロビジョニング** ] ページで、モードを **[自動**] に変更します。
8. [ **オンプレミス接続** ] セクションで、先ほどデプロイしたエージェントを選択し、[ **エージェントの割り当て]** を選択します。
9. このブラウザー ウィンドウを開いたままにして、構成ウィザードを使用して構成の次の手順を完了します。

### InputFile.txt と Schema.xml ファイルを所定の場所に配置する

このチュートリアルで PowerShell コネクタを作成する前に、InputFile.txt と Schema.xml ファイルを正しい場所にコピーする必要があります。 これらのファイルは、「 PowerShell セットアップ ファイルのダウンロード」セクションでダウンロードする必要があるファイルです。

| File | 位置 |
| --- | --- |
| InputFile.txt | `C:\Program Files\Microsoft ECMA2Host\Service\ECMA\MAData` |
| Schema.xml | `C:\Program Files\Microsoft ECMA2Host\Service\ECMA` |

### Microsoft Entra ECMA Connector Host の証明書を構成する

1. プロビジョニング エージェントがインストールされている Windows Server で、スタート メニューから **Microsoft ECMA2Host 構成ウィザード** を右選択し、管理者として実行します。 ウィザードで必要な Windows イベント ログを作成するには、Windows 管理者として実行する必要があります。
2. このウィザードを初めて実行する場合は、ECMA Connector Host 構成の開始後に証明書の作成を求められます。 既定のポート **8585** のままにし、[ **証明書の生成** ] を選択して証明書を生成します。 自動生成された証明書は、信頼ルートの一部として自己署名します。 証明書の SAN はホスト名と一致します。
3. **[保存] を選択します**。

### PowerShell コネクタを作成する

#### 全般画面

1. [スタート] メニューから Microsoft ECMA2Host 構成ウィザードを起動します。
2. 上部にある [ **インポート** ] を選択し、手順 1 で configuration.xml ファイルを選択します。
3. 新しいコネクタが作成され、赤で表示されます。 [ **編集] を選択します**。
4. コネクタに対して Microsoft Entra ID を認証するために使用するシークレット トークンを生成します。 これは、アプリケーションごとに最小 12 文字で、一意である必要があります。 シークレット ジェネレーターがまだない場合は、次のような PowerShell コマンドを使用して、ランダムな文字列の例を生成できます。

    ```powershell
    -join (((48..90) + (96..122)) * 16 | Get-Random -Count 16 | % {[char]$_})
    ```
5. [ **プロパティ** ] ページで、すべての情報を設定する必要があります。 この表は、参照用として掲載されています。 [ **次へ**] を選択します。

    | プロパティ | [値] |
    | --- | --- |
    | 名前 | コネクタに対して選択した名前。これは、環境内のすべてのコネクタで一意である必要があります。 たとえば、「 `PowerShell` 」のように入力します。 |
    | AutoSync タイマー (分) | 120 |
    | シークレット トークン | ここにシークレット トークンを入力します。 12 文字以上である必要があります。 |
    | 拡張 DLL | PowerShell コネクタの場合は、[ **Microsoft.IAM.Connector.PowerShell.dll**] を選択します。 |

[Image: 一般的な画面のスクリーンショット。]

#### 接続

[接続] タブでは、リモート システムに接続するための構成パラメーターを指定できます。 表に示されている情報を使用して、[接続] タブを構成します。

- [ **接続** ] ページで、すべての情報を設定する必要があります。 この表は、参照用として掲載されています。 [ **次へ**] を選択します。

[Image: 接続画面のスクリーンショット。]

| パラメーター | [値] | 目的 |
| --- | --- | --- |
| サーバー | &lt;空白&gt; | コネクタの接続先のサーバーの名前です。 |
| ドメイン | &lt;空白&gt; | コネクタの実行時に使用するために保存する資格情報のドメインです。 |
| ユーザー | &lt;空白&gt; | コネクタの実行時に使用するために保存する資格情報のユーザー名です。 |
| パスワード | &lt;空白&gt; | コネクタの実行時に使用するために保存する資格情報のパスワードです。 |
| コネクタ アカウントの偽装 | オフ | True の場合、同期サービスは指定された資格情報のコンテキストで Windows PowerShell スクリプトを実行します。 可能な場合は、 **偽装ではなく、$Credentials** パラメーターを各スクリプトに渡すことが推奨されます。 |
| 偽装時のユーザー プロファイルの読み込み | オフ | 偽装中に、コネクタの資格情報のユーザー プロファイルの読み込むように Windows に指示します。 偽装したユーザーが移動プロファイルを持っている場合、コネクタでローミング プロファイルが読み込まれません。 |
| 偽装時のログオンの種類 | 無し | 偽装中のログオンの種類 詳細については、 [dwLogonType](https://learn.microsoft.com/ja-jp/windows/win32/api/winbase/nf-winbase-logonusera#parameters) のドキュメントを参照してください。 |
| 署名済みスクリプトのみ | オフ | True の場合、Windows PowerShell コネクタは、各スクリプトに有効なデジタル署名があることを検証します。 False の場合、同期サービス サーバーの Windows PowerShell 実行ポリシーを「RemoteSigned」または「Unrestricted」にします。 |
| 共通モジュール スクリプト名 (拡張子付き) | xADSyncPSConnectorModule.psm1 | このコネクタでは、共有 Windows PowerShell モジュールを構成に保存できます。 コネクタで、スクリプトが実行されると、Windows PowerShell モジュールがファイル システムに抽出され、各スクリプトでインポートできるようになります。 |
| 共通モジュール スクリプト | [AD Sync PowerShell コネクタ モジュールコード](https://github.com/microsoft/MIMPowerShellConnectors/blob/master/src/ECMA2HostCSV/Scripts/CommonModule.psm1) を値として使用します。 このモジュールは、コネクタの実行時に ECMA2Host によって自動的に作成されます。 |  |
| 検証スクリプト | &lt;空白&gt; | 検証スクリプトは、管理者が指定したコネクタ構成パラメーターが有効であることを確認するために使用できるオプションの Windows PowerShell スクリプトです。 |
| スキーマ スクリプト | 値としての [GetSchema コード](https://github.com/microsoft/MIMPowerShellConnectors/blob/master/src/ECMA2HostCSV/Scripts/Schema%20Script.ps1)。 |  |
| 追加の構成パラメーター名 | FileName、Delimiter、Encoding | 標準的な構成設定に加え、コネクタのインスタンスに固有のカスタム構成設定を追加で定義できます。 追加パラメーターはコネクタ、パーティション、実行ステップのレベルで指定し、関連 Windows PowerShell スクリプトからアクセスできます。 |
| 追加の暗号化された構成パラメーター名 | &lt;空白&gt; |  |

#### 機能

[機能] タブでは、コネクタの動作と機能を定義します。 このタブで行った選択は、コネクタの作成時に変更できません。 表に示されている情報を使用して、[機能] タブを構成します。

- [ **機能** ] ページで、すべての情報を設定する必要があります。 この表は、参照用として掲載されています。 [ **次へ**] を選択します。

[Image: 機能画面のスクリーンショット。]

| パラメーター | [値] | 目的 |
| --- | --- | --- |
| 識別名の様式 | 無し | コネクタで識別名を利用できるかどうかと、利用できる場合、その様式を示します。 |
| エクスポートの種類 | ObjectReplace | エクスポート スクリプトに表示されるオブジェクトの種類を決定します。 |
| データの正規化 | 無し | スクリプトに提供する前にアンカー属性を正規化するように同期サービスに指示します。 |
| オブジェクト確認 | normal | これは無視されます。 |
| アンカーとして DN を使用する | オフ | 識別名の様式が LDAP に設定されている場合、コネクタ スペースのアンカー属性も識別名になります。 |
| 複数のコネクタの同時操作 | チェック済み | 選択した場合、複数の Windows PowerShell コネクタを同時に実行できます。 |
| メジャー グループ | オフ | 選択した場合、コネクタは複数のパーティションとパーティション検出に対応します。 |
| 階層 | オフ | 選択した場合、コネクタは LDAP 様式の階層構造に対応します。 |
| インポートの有効化 | チェック済み | 選択した場合、コネクタはインポート スクリプトでデータをインポートします。 |
| 差分インポートの有効化 | オフ | 選択した場合、コネクタはインポート スクリプトから差分を要求できます。 |
| エクスポートの有効化 | チェック済み | 選択した場合、コネクタはエクスポート スクリプトでデータをエクスポートします。 |
| 完全エクスポートの有効化 | チェック済み | サポートされていません。 これは無視されます。 |
| 最初のエクスポート パスに参照値なし | オフ | 選択した場合、参照属性は 2 番目のエクスポート パスでエクスポートされます。 |
| オブジェクトの名前変更の有効化 | オフ | 選択した場合、識別名を変更できます。 |
| 置換として削除/追加 | チェック済み | サポートされていません。 これは無視されます。 |
| 最初のパスのエクスポート パスワードの有効化 | オフ | サポートされていません。 これは無視されます。 |

#### グローバル パラメーター

[グローバル パラメーター] タブでは、コネクタによって実行される Windows PowerShell スクリプトを構成できます。 [接続] タブで定義されるカスタム構成設定のグローバル値を構成することもできます。表に示されている情報を使用して、[グローバル パラメーター] タブを構成します。

- [ **グローバル パラメーター]** ページで、すべての情報を入力する必要があります。 この表は、参照用として掲載されています。 [ **次へ**] を選択します。

[Image: グローバル画面のスクリーンショット。]

| パラメーター | [値] |
| --- | --- |
| パーティション スクリプト | &lt;空白&gt; |
| 階層スクリプト | &lt;空白&gt; |
| インポート開始スクリプト | &lt;空白&gt; |
| インポート スクリプト | [インポート スクリプトを値として貼り付ける](https://github.com/microsoft/MIMPowerShellConnectors/blob/master/src/ECMA2HostCSV/Scripts/Import%20Scripts.ps1) |
| インポート終了スクリプト | &lt;空白&gt; |
| エクスポート開始スクリプト | &lt;空白&gt; |
| エクスポート スクリプト | [インポート スクリプトを値として貼り付ける](https://github.com/microsoft/MIMPowerShellConnectors/blob/master/src/ECMA2HostCSV/Scripts/Export%20Script.ps1) |
| エクスポート終了スクリプト | &lt;空白&gt; |
| パスワード開始スクリプト | &lt;空白&gt; |
| パスワード拡張スクリプト | &lt;空白&gt; |
| パスワード終了スクリプト | &lt;空白&gt; |
| FileName\_Global | InputFile.txt |
| Delimiter\_Global | ; |
| Encoding\_Global | &lt;空白&gt; (既定値は UTF8) |

#### Partitions、Run Profiles、Export、FullImport

既定値をそのまま使用し、[ **次へ**] を選択します。

#### オブジェクトの型

表に示されている情報を使用して、[オブジェクト タイプ] タブを構成します。

- [ **オブジェクトの種類]** ページで、すべての情報を入力する必要があります。 この表は、参照用として掲載されています。 [ **次へ**] を選択します。

[Image: [オブジェクトの種類] 画面のスクリーンショット。]

| パラメーター | [値] |
| --- | --- |
| ターゲット オブジェクト | Person |
| アンカー | AzureObjectID |
| クエリ属性 | AzureObjectID |
| DN | AzureObjectID |

#### 属性を選択する

以下の属性が選択されていることを確認します。

- [ **属性の選択** ] ページで、すべての情報を設定する必要があります。 この表は、参照用として掲載されています。 [ **次へ**] を選択します。
- AzureObjectID
- IsActive
- DisplayName
- 社員ID
- Title
- UserName
- Email

[Image: 属性の選択画面のスクリーンショット。]

#### Deprovisioning

[プロビジョニング解除] ページでは、ユーザーがアプリケーションの範囲外になったときに Microsoft Entra ID にディレクトリからユーザーを削除させるかどうかを指定することができます。 その場合は、[フローの無効化] で [削除] を選択し、[フローの削除] で [削除] を選択します。 Set 属性値が選択されている場合は、前のページで選択した属性を [プロビジョニング解除] ページで選択することはできません。

- [ **プロビジョニング解除** ] ページで、すべての情報を入力する必要があります。 この表は、参照用として掲載されています。 [ **次へ**] を選択します。

[Image: プロビジョニング解除画面のスクリーンショット。]

### ECMA2Host サービスが実行されており PowerShell によってファイルから読み取れることを確認する

次の手順に従って、コネクタ ホストが起動し、ターゲット システムからの既存のユーザーを特定したことを確認します。

1. Microsoft Entra ECMA コネクタ ホストを実行しているサーバーで、[開始] を選択 **します**。
2. 必要に応じて **[実行** ] を選択し、ボックスに **「services.msc** 」と入力します。
3. **サービス**の一覧で、**Microsoft ECMA2Host** が存在し、実行されていることを確認します。 実行されていない場合は、[ **スタート]** を選択します。
4. Microsoft Entra ECMA Connector Host を実行しているサーバーで、PowerShell を起動します。
5. ECMA ホストがインストールされたフォルダー (`C:\Program Files\Microsoft ECMA2Host` など) に移動します。
6. サブディレクトリ `Troubleshooting` に移動します。
7. 次のように、ディレクトリでスクリプト `TestECMA2HostConnection.ps1` を実行し、引数としてコネクタ名と `ObjectTypePath` 値 `cache` を指定します。 コネクタ ホストが TCP ポート 8585 でリッスンしていない場合は、`-Port` 引数の指定も必要になる場合があります。 メッセージが表示されたら、そのコネクタ用に構成されたシークレット トークンを入力します。

    ```
    PS C:\Program Files\Microsoft ECMA2Host\Troubleshooting> $cout = .\TestECMA2HostConnection.ps1 -ConnectorName PowerShell -ObjectTypePath cache; $cout.length -gt 9
    Supply values for the following parameters:
    SecretToken: ************
    ```
8. スクリプトにエラーまたは警告メッセージが表示される場合は、サービスが実行されていることを確認し、コネクタ名とシークレット トークンが構成ウィザードで構成した値と一致していることを確認します。
9. スクリプトで出力 `False` が表示される場合、コネクタはソース ターゲット システム内の既存のユーザーのエントリを認識していません。 これが新しいターゲット システムのインストールである場合、この動作は予期されたものであり、次のセクションに進むことができます。
10. 一方、ターゲット システムに既に 1 人以上のユーザーが含まれるのに、スクリプトで `False` が表示された場合、この状態はコネクタがターゲット システムから読み取れなかったことを示しています。 プロビジョニングを試みた場合、Microsoft Entra ID は、そのソース ディレクトリ内のユーザーと Microsoft Entra ID のユーザーを正しくマッチングできない可能性があります。 コネクタ ホストが既存のターゲット システムからのオブジェクトの読み取りを完了するまで数分待ってから、スクリプトを再実行します。 出力が引き続き `False` である場合は、コネクタの構成とターゲット システムのアクセス許可で、コネクタによる既存ユーザーの読み取りが許可されていることを確認します。

### Microsoft Entra ID からコネクタ ホストへの接続をテストする

1. ポータルでアプリケーションのプロビジョニングを構成していた Web ブラウザー ウィンドウに戻ります。

    注

    ウィンドウがタイムアウトした場合は、エージェントを再選択する必要があります。

    1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
    2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。
    3. オンプレミスの **ECMA アプリ アプリケーションを** 選択します。
    4. **プロビジョニング**を選択します。
    5. **[開始]** が表示されたら、モードを **[自動**] に変更し、[**オンプレミス接続**] セクションで、デプロイしたエージェントを選択し、[**エージェントの割り当て]** を選択して、10 分待ちます。 それ以外の場合は、[ **プロビジョニングの編集]** に移動します。
2. [ **管理者資格情報** ] セクションで、次の URL を入力します。 `connectorName` の部分を ECMA ホスト上のコネクタの名前 (`PowerShell` など) に置き換えます。 ECMA ホストの証明機関の証明書を提供した場合は、`localhost` を ECMA ホストがインストールされているサーバーのホスト名に置き換えます。

    | プロパティ | [値] |
    | --- | --- |
    | テナントの URL | https://localhost:8585/ecma2host\_connectorName/scim |
3. コネクタの作成時に定義した **シークレット トークン** の値を入力します。

    注

    アプリケーションにエージェントを割り当てたばかりの場合は、登録が完了するまで 10 分間お待ちください。 登録が完了するまで、接続テストは機能しません。 サーバーでプロビジョニング エージェントを再起動してエージェントの登録を強制的に完了すると、登録プロセスを高速化することができます。 サーバーに移動し、Windows 検索バーでサービスを検索し、Microsoft Entra Connect Provisioning Agent サービス を特定し、サービスを右選択して再起動します。
4. [ **テスト接続]** を選択し、1 分待ちます。
5. 接続テストが成功し、プロビジョニングを有効にするために指定された資格情報が承認されていることを示したら、[ **保存]** を選択します。

### アプリケーションの接続を構成する

アプリケーション プロビジョニングを構成していた Web ブラウザー ウィンドウに戻ります。

注

ウィンドウがタイムアウトした場合は、エージェントを再選択する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。
3. オンプレミスの **ECMA アプリ アプリケーションを** 選択します。
4. **プロビジョニング**を選択します。
5. **[作業の開始**] が表示されたら、モードを **[自動**] に変更し、[**オンプレミス接続**] セクションで、デプロイしたエージェントを選択し、[**エージェントの割り当て]** を選択します。 それ以外の場合は、[ **プロビジョニングの編集]** に移動します。
6. [ **管理者資格情報** ] セクションで、次の URL を入力します。 `{connectorName}`部分を**、CSV** などの ECMA コネクタ ホスト上のコネクタの名前に置き換えます。 コネクタ名は大文字と小文字が区別され、ウィザードで構成したのと同じ大文字と小文字の区別にする必要があります。 `localhost` をマシンのホスト名に置き換えることもできます。

    | プロパティ | [値] |
    | --- | --- |
    | テナントの URL | `https://localhost:8585/ecma2host_CSV/scim` |
7. コネクタの作成時に定義した **シークレット トークン** の値を入力します。

    注

    アプリケーションにエージェントを割り当てたばかりの場合は、登録が完了するまで 10 分間お待ちください。 登録が完了するまで、接続テストは機能しません。 サーバーでプロビジョニング エージェントを再起動してエージェントの登録を強制的に完了すると、登録プロセスを高速化することができます。 サーバーに移動し、Windows 検索バーで **サービス** を検索し、 **Microsoft Entra Connect プロビジョニング エージェント サービスを**特定し、サービスを右クリックして再起動します。
8. [ **テスト接続]** を選択し、1 分待ちます。
9. 接続テストが成功し、プロビジョニングを有効にするために指定された資格情報が承認されていることを示したら、[ **保存]** を選択します。

### 属性マッピングの構成

次に、Microsoft Entra ID でのユーザーの表現と、オンプレミスの InputFile.txt でのユーザーの表現の間で、属性をマッピングする必要があります。

Azure portal を使用して、Microsoft Entra ユーザーの属性と、ECMA ホスト構成ウィザードで以前に選択した属性の間のマッピングを構成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。
3. オンプレミスの **ECMA アプリ アプリケーションを** 選択します。
4. **プロビジョニング**を選択します。
5. [ **プロビジョニングの編集]** を選択し、10 秒待ちます。
6. **[マッピング]** を展開し、**[Microsoft Entra ユーザーのプロビジョニング]** を選択します。 このアプリケーションの属性マッピングを初めて構成した場合、プレースホルダーのマッピングは 1 つだけ存在します。
7. スキーマがMicrosoft Entra IDで使用可能であることを確認するには、[**詳細オプション]** ドロップダウンを選択し、[**ターゲット ユーザー属性の編集]** を選択します。 構成ウィザードで選択したすべての属性が一覧表示されていることを確認します。 そうでない場合は、スキーマが更新されるまで数分待ってから、ページを再読み込みします。 属性が一覧表示されたら、このページをキャンセルしてマッピングの一覧に戻ります。
8. ここで、**userPrincipalName** PLACEHOLDER のマッピングを選択します。 このマッピングは、オンプレミス プロビジョニングを初めて構成するときに既定で追加されます。 値を以下に合わせて変更します。

    | マッピングの種類 | ソース属性 | ターゲット属性 |
    | --- | --- | --- |
    | 直接 | userPrincipalName | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:UserName |
9. [ **新しいマッピングの追加]** を選択し、マッピングごとに次の手順を繰り返します。
10. 次の表のマッピングごとにソース属性とターゲット属性を指定します。

    | マッピングの種類 | ソース属性 | ターゲット属性 |
    | --- | --- | --- |
    | 直接 | objectId | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:AzureObjectID |
    | 直接 | userPrincipalName | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:UserName |
    | 直接 | displayName | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:DisplayName |
    | 直接 | 従業員ID | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:EmployeeId |
    | 直接 | 職務タイトル | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:Title |
    | 直接 | メール | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:Email |
    | 表現 | Switch([IsSoftDeleted],,"False", "True", "True", "False") | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:IsActive |

    [Image: 属性マッピングのスクリーンショット。]
11. すべてのマッピングが追加されたら、[ **保存]** を選択します。

### アプリケーションにユーザーを割り当てる

これで Microsoft Entra ECMA Connector Host が Microsoft Entra ID と通信できるようになり、属性マッピングが構成されたので、プロビジョニングの範囲に含めるユーザーの構成に進むことができます。

重要

ハイブリッド ID 管理者ロールを使用してサインインした場合、このセクションでは、少なくともアプリケーション管理者ロールであるアカウントでサインアウトしてサインインする必要があります。 ハイブリッド ID の管理者ロールには、ユーザーをアプリケーションに割り当てるためのアクセス許可がありません。

InputFile.txt に既存のユーザーが存在する場合は、それらの既存のユーザー向けのアプリケーション ロールの割り当てを作成する必要があります。 アプリケーション ロールの割り当てを一括で作成する方法の詳細については、 [Microsoft Entra ID でのアプリケーションの既存ユーザーの管理](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-existing-users)に関するページを参照してください。

アプリケーションの既存のユーザーが存在しない場合は、アプリケーションに対してプロビジョニングされるテスト ユーザーを Microsoft Entra から選択します。

1. 選択したユーザーに、スキーマの必須属性にマップされるプロパティがすべて指定されていることを確認します。
2. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
3. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。
4. オンプレミスの **ECMA アプリ アプリケーションを** 選択します。
5. 左側の [ **管理**] で、[ **ユーザーとグループ**] を選択します。
6. [ **ユーザー/グループの追加]** を選択します。
7. [ **ユーザー**] で[ **選択なし**] を選択します。
8. 右側からユーザーを選択し、[選択] ボタンを **選択** します。
9. [ **割り当て**] を選択します。

### プロビジョニングをテストする

属性がマップされ、ユーザーが割り当てられたので、ユーザーの 1 人でオンデマンド プロビジョニングをテストできます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。
3. オンプレミスの **ECMA アプリ アプリケーションを** 選択します。
4. **プロビジョニング**を選択します。
5. **[Provision on demand] (オンデマンドでプロビジョニングする)** を選択します。
6. いずれかのテスト ユーザーを検索し、[ **プロビジョニング**] を選択します。
7. 数秒後、 **ターゲット システムでユーザーが正常に作成** されたというメッセージが表示され、ユーザー属性の一覧が表示されます。

### ユーザーのプロビジョニングを開始する

1. オンデマンド プロビジョニングが成功したら、プロビジョニングの構成ページに戻ります。 スコープが割り当てられたユーザーとグループのみに設定されていることを確認し、プロビジョニングを **オンに**して、[ **保存]** を選択します。
2. プロビジョニングが開始されるまで数分待機します。 最大 40 分かかる場合があります。 次のセクションで説明するように、プロビジョニング ジョブが完了したら、テストが完了したら、プロビジョニングの状態を **[オフ**] に変更し、[ **保存]** を選択できます。 これにより、プロビジョニング サービスは今後実行されなくなります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/on-premises-sap-connector-configure"} -->
## NetWeaver AS ABAP 7.0 以降を使用した SAP ERP Central Component (SAP ECC、旧称 SAP R/3) への Microsoft Entra プロビジョニング。 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-sap-connector-configure
- Service: entra-id / app-provisioning
- Article date: 2025-04-09
- Summary: このドキュメントでは、NetWeaver AS ABAP 7.0 以降を使用して、SAP ERP Central Component (SAP ECC、旧称 SAP R/3) にユーザーをプロビジョニングするように Microsoft Entra ID を構成する方法について説明します。

次のドキュメントには、NetWeaver 7.0 以降を使用して、Microsoft Entra ID から SAP ERP Central Component (SAP ECC、旧称 SAP R/3) にユーザーをプロビジョニングする方法を示す構成とチュートリアル情報があります。 他のバージョンの SAP R/3 を使用している場合でも、[Connectors for Microsoft Identity Manager 2016](https://www.microsoft.com/download/details.aspx?id=51495) のダウンロードで提供されているガイドを参照し、プロビジョニング用の独自のテンプレートを作成できます。

注

この記事では、Microsoft Entra プロビジョニング エージェントを介した SAP ECC へのプロビジョニングについてのみ説明します。 SAP S/4HANA On-Premise や他の SAP SaaS アプリケーションを含む SAP S/4HANA を使用している場合は、チュートリアルに従って、SAP Cloud Identity Services を使用してそれらのアプリケーションにプロビジョニングするように [SAP Cloud Identity Services を自動ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial) 用に構成します。 SAP 統合の詳細については、「[SAP アプリケーションへのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/sap)」を参照してください。

[Image: SAP ECC と SAP S/4HANA オンプレミスへのプロビジョニングの図。]

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
5. **[Get started](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/作業を開始する)** を選択します。
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

    | プロパティ | 価値 |
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
    | ホスト | SAP ECC SOAP エンドポイントのホスト名 (例: `vhcalnplci.dummy.nodomain` |
    | ポート | SAP ECC SOAP エンドポイント ポート (例: `8000` |
7. **[機能]** ページでは、下にある表に指定される値をボックスに入力し、**[次へ]** を選択します。

    | プロパティ | 価値 |
    | --- | --- |
    | 識別名の様式 | ジェネリック |
    | エクスポートの種類 | ObjectReplace |
    | データの正規化 | なし |
    | オブジェクト確認 | 標準 |
    | インポートの有効化 | チェック済み |
    | デルタ インポートの有効化 | オフ |
    | エクスポートの有効化 | チェック済み |
    | 完全エクスポートの有効化 | オフ |
    | 最初のパスのエクスポート パスワードを有効にする | チェック済み |
    | 最初のエクスポート パスに参照値なし | オフ |
    | オブジェクトの名前変更の有効化 | オフ |
    | 置換として削除/追加 | オフ |

注

Web サービス コネクタ テンプレート `sapecc.wsconfig` が Web サービス構成ツールで編集用に開かれている場合は、エラーが発生します。

1. **[グローバル]** ページでは、画像の下にある表に指定される値をボックスに入力し、**[次へ]** を選択します。

    | プロパティ | 価値 |
    | --- | --- |
    | クライアント認証タイプ (ClientCredentialType) | Basic |
    | ユーザー名 | SAP ECC テンプレートで使用される BAPI に対する呼び出しを行う権限を持つアカウントのユーザー名。 |
    | パスワード | 指定したユーザー名のパスワード。 |
    | 接続をテスト | テンプレートにテスト接続ワークフローが実装されていない場合はオフ |
2. **[パーティション]** ページで **[次へ]** を選択します。
3. **[プロファイルの実行]** ページで **[エクスポート]** チェックボックスを選択したままにします。 **[フル インポート]** チェックボックスを選択して、 **[次へ]** を選択します。 **エクスポート**実行プロファイルは、ECMA コネクタ ホストが Microsoft Entra ID から SAP ECC に変更を送信し、レコードの挿入、更新、削除を行う必要がある場合に使用されます。 **完全インポート**実行プロファイルは、ECMA コネクタ ホスト サービスの開始時に、SAP ECC の現在のコンテンツを読み取るために使用されます。

    | プロパティ | 価値 |
    | --- | --- |
    | エクスポート | データを SAP ECC インスタンスにエクスポートする実行プロファイル。 この実行プロファイルは必須です。 |
    | フル インポート | 前に指定した SAP ECC インスタンスからすべてのデータをインポートする実行プロファイル。 |
    | 差分インポート | 最後のフルまたは差分インポート以降の SAP ECC インスタンスからの変更のみをインポートする実行プロファイル。 |
4. **[オブジェクトの種類]** ページで、ボックスに入力し、 **[次へ]** を選択します。 個々のボックスのガイダンスについては、画像の下にある表を参照してください。

    - **アンカー**: この属性の値は、ターゲット システム内のオブジェクトごとに一意である必要があります。 Microsoft Entra プロビジョニング サービスでは、初期サイクル後、この属性を使用して ECMA コネクタ ホストに対してクエリを実行します。 この値は、Web サービス コネクタ テンプレートで定義されます。
    - **DN**: ほとんどの場合、Autogenerated オプションを選択する必要があります。 オフになっている場合は、`CN = anchorValue, Object = objectType` という形式で DN が格納されている Microsoft Entra ID の属性に、DN 属性がマップされるようにします。 アンカーと DN の詳細については、「[アンカー属性と識別名について](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture#about-anchor-attributes-and-distinguished-names)」を参照してください。

        | プロパティ | 価値 |
        | --- | --- |
        | ターゲット オブジェクト | `User` |
        | アンカー | `userName` |
        | DN | `userName` |
        | 自動生成 | チェック済み |
5. ECMA コネクタ ホストにより、SAP ECC がサポートする属性が検出されます。 次に、検出された属性のうち、Microsoft Entra ID に公開する属性を選択できます。 プロビジョニングのために、これらの属性を Microsoft Entra 管理センターで構成できます。 **[属性の選択]** ページで、ドロップダウン リストのすべての属性を 1 つずつ追加します。 **[属性]** ドロップダウン リストには、SAP ECC で検出され、以前の *[属性の選択]* ページでは選択**されなかった**属性が示されます。 関連するすべての属性が追加されたら、**[次へ]** を選択します。

    [Image: [属性の選択] ページを示すスクリーンショット。]
6. **[プロビジョニング解除]** ページの **[フローを無効化]** で **[削除]** を選択します。 前のページで選択した属性を [プロビジョニング解除] ページで選択することはできません。 **[完了]** を選択します。

注

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

    注

    ウィンドウがタイムアウトした場合は、エージェントを再選択する必要があります。

    1. Microsoft Entra 管理センターにサインインします。
    2. **[エンタープライズ アプリケーション]** の **[On-premises ECMA app](オンプレミス ECMA アプリ)** アプリケーションに移動します。
    3. **[プロビジョニング]** を選択します。
    4. **[作業の開始]** を選択して、モードを **[自動]** に変更し、**[オンプレミス接続]** セクションで、デプロイしたエージェントを選択し、**[エージェントの割り当て]** を選択します。 それ以外の場合は、**[プロビジョニングの編集]** に移動します。
2. **[管理者資格情報]** セクションで、次の URL を入力します。 `{connectorName}` の部分を ECMA コネクタ ホスト上のコネクタの名前 (**SAPECC7** など) に置き換えます。 コネクタ名は大文字と小文字が区別されるため、ウィザードで構成したのと同じ大文字と小文字の表記にする必要があります。 `localhost` をマシンのホスト名に置き換えることもできます。

    | プロパティ | 価値 |
    | --- | --- |
    | テナントの URL | `https://localhost:8585/ecma2host_SAPECC7/scim` |
3. コネクタの作成時に定義した**シークレット トークン**の値を入力します。

    注

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
5. **[Add user/group](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/ユーザーまたはグループの追加)** を選択します。

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
4. **[オンデマンドでプロビジョニングする]** を選択します。
5. テストユーザーの中から1人を探し、**プロビジョン**を選択してください。

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

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/on-premises-scim-provisioning"} -->
## SCIM 対応アプリへのオンプレミス アプリ プロビジョニングのMicrosoft Entra - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-scim-provisioning
- Service: entra-id / app-provisioning
- Article date: 2025-04-09
- Summary: この記事では、Microsoft Entra プロビジョニング サービスを使用して、SCIM が有効になっているオンプレミス アプリにユーザーをプロビジョニングする方法について説明します。

Microsoft Entra プロビジョニング サービスでは、ユーザーをクラウドまたはオンプレミスのアプリケーションに自動的にプロビジョニングするために使用できる [SCIM 2.0](https://techcommunity.microsoft.com/t5/security-compliance-and-identity/provisioning-with-scim-getting-started/ba-p/880010) クライアントがサポートされています。 この記事では、Microsoft Entra プロビジョニング サービスを使用して、SCIM が有効になっているオンプレミス アプリケーションにユーザーをプロビジョニングする方法について説明します。 SQL をデータ ストアとして使用する SCIM 以外のオンプレミス アプリケーションにユーザーをプロビジョニングする場合は、[Microsoft Entra ECMA Connector Host Generic SQL Connector チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/tutorial-ecma-sql-connector)を参照してください。 DropBox や Atlassian などのクラウド アプリにユーザーをプロビジョニングする場合は、アプリ固有のチュートリアルを確認 [してください](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)。

[Image: SCIM アーキテクチャを示す図。]

### [前提条件]

- Microsoft Entra ID P1 または Premium P2 (または EMS E3 または E5) を持つMicrosoft Entra テナント。 この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 要件に適したライセンスを見つけるには、[Microsoft Entra ID の一般公開機能を比較](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing) を参照してください。
- エージェントをインストールするための管理者ロール。 このタスクは 1 回限りの作業であり、少なくともハイブリッド ID 管理者であるAzure アカウントである必要があります。
- 管理者は、少なくともアプリケーション管理者、クラウド アプリケーション管理者、またはアクセス許可を持つカスタム ロールである必要があります。
- プロビジョニング エージェントをホストするための RAM が少なくとも 3 GB のコンピューター。 コンピューターにはWindows Server 2016以降のバージョンのWindows Serverがあり、ターゲット アプリケーションへの接続と、login.microsoftonline.com、他の Microsoft Online Services、およびAzure ドメインへの送信接続が必要です。 たとえば、Azure IaaS またはプロキシの背後でホストされているWindows Server 2016仮想マシンです。
- [SCIM](https://techcommunity.microsoft.com/t5/security-compliance-and-identity/provisioning-with-scim-getting-started/ba-p/880010) 実装が [Microsoft Entra SCIM 要件](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)を満たしていることを確認します。 Microsoft Entra IDでは、開発者が SCIM 実装のブートストラップに使用できるオープンソースの reference コードを提供します。詳細については、「Tutorial: Microsoft Entra ID。
- /schemas エンドポイントをサポートして、Azure ポータルで必要な構成を減らします。

### Microsoft Entra Connect プロビジョニング エージェントをインストールして構成する

1. 少なくとも [Microsoft Entra 管理センター](https://entra.microsoft.com)[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**に移動します。
3. **オンプレミスの SCIM アプリ** アプリケーションを検索し、アプリに名前を付け、[**作成**] を選択してテナントに追加します。
4. メニューから、アプリケーションの **[プロビジョニング** ] ページに移動します。
5. [ **作業の開始] を選択します**。
6. [ **プロビジョニング** ] ページで、モードを **[自動**] に変更します。

[Image: [自動] を選択したスクリーンショット。]

1. [ **オンプレミス接続] で**、[ **ダウンロードとインストール**] を選択し、[ **同意する] を選択してダウンロードします**。

[Image: エージェントのダウンロード場所のスクリーンショット。]

1. ポータルを終了し、プロビジョニング エージェント インストーラーを開き、サービス条件に同意して、[インストール] を選択 **します**。
2. Microsoft Entra プロビジョニング エージェント構成ウィザードを待機し、**Next** を選択します。
3. [ **拡張機能の選択** ] ステップで、[ **オンプレミス のアプリケーション プロビジョニング** ] を選択し、[ **次へ**] を選択します。
4. プロビジョニング エージェントは、オペレーティング システムの Web ブラウザーを使用して、Microsoft Entra IDに対して認証するためのポップアップ ウィンドウと、組織の ID プロバイダーを表示します。 Windows ServerでMicrosoft Edgeを使用している場合は、Microsoftサイトがブロックされていないことを確認します。 組織でレガシ ブラウザーのサポートが必要な場合は、IE モードで [Microsoft Edge](https://learn.microsoft.com/ja-jp/deployedge/edge-ie-mode) を使用し、信頼済みサイトの一覧にMicrosoftサイトを追加できます。
5. 承認を求められたら、Microsoft Entra管理者の資格情報を入力します。 ユーザーには、少なくとも [ハイブリッド ID 管理者ロールが](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) 必要です。
6. [ **確認]** を選択して設定を確認します。 インストールが成功したら、[ **終了**] を選択し、プロビジョニング エージェント パッケージ インストーラーを閉じてもかまいません。

### プロビジョニング エージェントを使用して接続を構成する

1. 少なくとも [Microsoft Entra 管理センター](https://entra.microsoft.com)[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**に移動します。
3. 前に作成したアプリケーションを検索します。
4. メニューから、アプリケーションの **[プロビジョニング** ] ページに移動します。
5. ポータルの **[オンプレミス接続** ] セクションで、デプロイしたエージェントを選択し、[ **エージェントの割り当て]** を選択します。

    [Image: エージェントを選択して割り当てる方法を示すスクリーンショット。]
6. プロビジョニング エージェント サービスを再起動するか、接続をテストする前に 10 分待ちます。
7. [ **テナント URL** ] フィールドに、アプリケーションの SCIM エンドポイントの URL を入力します。 例: `https://api.contoso.com/scim/`
8. SCIM エンドポイントに必要な OAuth ベアラー トークンを **[シークレット トークン** ] フィールドにコピーします。
9. SCIM エンドポイントへの接続を試みるには、**Test Connection** を選択して、Microsoft Entra ID を使用します。 試行が失敗すると、エラー情報が表示されます。
10. アプリケーションへの接続が成功したら、[ **保存]** を選択して管理者の資格情報を保存します。
11. 構成ウィザードを使用して次の構成手順を完了したら、このブラウザー ウィンドウを開いたままにしておきます。

### SCIM 対応アプリケーションへのプロビジョニング

エージェントがインストールされると、オンプレミスでそれ以上の構成は必要なくなり、すべてのプロビジョニング構成がポータルから管理されます。 SCIM 経由でプロビジョニングされるすべてのオンプレミス アプリケーションについて、次の手順を繰り返します。

1. アプリケーションに必要 [な属性マッピング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes) または [スコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) 規則を構成します。
2. ユーザーとグループをアプリケーションに [割り当てることで、スコープにユーザー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) を追加します。
3. [必要に応](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)じて少数のユーザーのプロビジョニングをテストします。
4. ユーザーをアプリケーションに割り当てることで、スコープにユーザーを追加します。
5. [ **プロビジョニング** ] ウィンドウに移動し、[ **プロビジョニングの開始**] を選択します。
6. [プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)を使用して監視します。

次のビデオでは、オンプレミス プロビジョニングの概要を紹介しています。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/on-premises-sql-connector-configure"} -->
## ECMA Connector Host を使用して SQL ベースのアプリケーションにユーザーをプロビジョニングする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-sql-connector-configure
- Service: entra-id / app-provisioning
- Article date: 2025-04-09
- Summary: ECMA Connector Host を使用して SQL ベースのアプリケーションにユーザーをプロビジョニングする

次のドキュメントでは、汎用 SQL コネクタと Extensible Connectivity (ECMA) ホストを SQL Server と使用する方法を示す構成およびチュートリアル情報を示します。

このドキュメントでは、ユーザーを Microsoft Entra ID から SQL データベースに自動的にプロビジョニングおよびプロビジョニング解除するために実行する必要がある手順について説明します。

このサービスが実行する内容、仕組み、よく寄せられる質問の重要な詳細については、[Microsoft Entra ID による SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)および[オンプレミス アプリケーション プロビジョニング アーキテクチャ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture)に関する記事を確認してください。

次のビデオでは、オンプレミス プロビジョニングの概要を紹介しています。

### SQL Database にプロビジョニングするための前提条件

#### オンプレミスの前提条件

アプリケーションは SQL データベースに依存します。このデータベースでは、ユーザーのレコードを作成、更新、および削除できます。 プロビジョニング エージェントを実行するコンピューターには、次のものが必要です。

- Windows Server 2016 以降のバージョン。
- ターゲット データベース システムへの接続、および login.microsoftonline.com、[その他の Microsoft Online Services](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/urls-and-ip-address-ranges)、[Azure](https://learn.microsoft.com/ja-jp/azure/azure-portal/azure-portal-safelist-urls) ドメインへの送信接続。 1 つの例は、Azure IaaS でホストされているか、またはプロキシの背後にある Windows Server 2016 仮想マシンです。
- 少なくとも 3 GB の RAM。
- .NET Framework 4.7.2
- SQL データベースの ODBC ドライバー。

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

適切なテーブルを持つデータベースがまだない場合は、デモンストレーション用に、Microsoft Entra ID による使用を許可したテーブルを作成する必要があります。 SQL Server を使用している場合は、「付録 A」に記載されている SQL スクリプトを実行します。このスクリプトは、1つのテーブル `Employees` を含む、CONTOSO という名前のサンプル データベースを作成します。 これは、ユーザーをプロビジョニングするデータベース テーブルです。

| [テーブル列] | Source |
| --- | --- |
| ContosoLogin | Microsoft Entra ユーザー プリンシパル名 |
| ファーストネーム | Microsoft Entra の指定された名前 |
| LastName | Microsoft Entra 姓 |
| Email | Exchange Online 電子メールアドレス |
| InternalGUID | データベース自体によって生成されます |
| AzureID | Microsoft Entra オブジェクト ID |
| textID | Microsoft Entra ID メール ニックネーム |

### Microsoft Entra SQL コネクタがデータベースとどのように対話するかを決定する

SQL インスタンスに、データベースのテーブル内のデータを更新する権限を持つユーザー アカウントが存在する必要があります。 SQL データベースが他のユーザーによって管理されている場合は、そのユーザーに連絡して、データベースに対する認証に Microsoft Entra ID で使用するアカウント名とパスワードを入手してください。 SQL インスタンスが別のコンピューターにインストールされている場合は、SQL データベースでエージェント コンピューター上の ODBC ドライバーからの受信接続を許可する必要もあります。

アプリケーションに既存のデータベースが既にある場合、Microsoft Entra ID がそのデータベースと対話する方法を決定する必要があります。これは、テーブルとビューを直接操作する方法、データベース内に既に存在するストアド プロシージャを使用する方法、クエリと更新用に指定する SQL ステートメントを使用する方法です。 この設定は、より複雑なアプリケーションが、そのデータベースに他の補助テーブルを持ち、何千人ものユーザーを持つテーブルのページングを必要としたり、暗号化、ハッシュ化、妥当性チェックなどの追加のデータ処理を実行するストアド プロシージャを Microsoft Entra ID が呼び出す必要があったりする可能性があるからです。

アプリケーションのデータベースと対話するようにコネクタの構成を作成する場合は、まず、コネクタ ホストがデータベースのスキーマを読み取る方法についてのアプローチを構成してから、次に実行プロファイルを介してコネクタが継続的に使用するアプローチを構成します。 各実行プロファイルでは、コネクタが SQL ステートメントを生成する方法を指定します。 実行プロファイルの選択と実行プロファイル内のメソッドは、データベース エンジンがサポートする内容とアプリケーションで必要とされるものによって異なります。

- 構成が完了すると、プロビジョニング サービスが開始する際、**完全インポート**実行プロファイルで構成された相互作用が自動的に実行されます。 この実行プロファイルでは、コネクタは、通常 **SELECT** ステートメントを使用して、アプリケーションのデータベースからのユーザーのすべてのレコードを読み取ります。 この実行プロファイルは、後で Microsoft Entra ID がユーザーに変更を加える必要がある場合に、そのユーザーに対して新しいレコードを作成するのではなく、データベース内のそのユーザーの既存のレコードを更新することを認識するために必要になります。
- 新しいユーザーをアプリケーションに割り当てる場合や既存のユーザーを更新する場合など、Microsoft Entra ID で変更が行われるたびに、プロビジョニング サービスは、SQL データベース相互作用によって構成された**エクスポート**実行プロファイルを実行します。 **エクスポート**実行プロファイルでは、データベースの内容を Microsoft Entra ID と同期させるために、Microsoft Entra ID が SQL ステートメントを発行してデータベースのレコードを挿入、更新、および削除します。
- データベースでサポートされている場合は、必要に応じて **差分インポート**実行プロファイルを構成することもできます。 この実行プロファイルでは、前回の完全インポート、または差分インポート以降に Microsoft Entra ID 以外のデータベースで行われた変更が Microsoft Entra ID によって読み取られます。 この実行プロファイルは、変更を読み取ることができるようにデータベースを構造化する必要があるため、省略可能です。

コネクタの各実行プロファイルの構成では、Microsoft Entra ID コネクタがテーブルまたはビューに対して独自の SQL ステートメントを生成するか、ストアド プロシージャを呼び出すか、または指定したカスタム SQL クエリを使用するかどうかを指定します。 通常は、コネクタ内のすべての実行プロファイルに同じ方法を使用します。

- 実行プロファイルのテーブルまたはビューメソッドを選択すると、Microsoft Entra コネクタによって、データベース内のテーブルまたはビューと対話するために必要な SQL ステートメント、*SELECT*、*INSERT*、*UPDATE*、および *DELETE* が生成されます。 この方法は、データベースに 1 つのテーブル、または既存の行数が少ない更新可能なビューがある場合に、最も簡単なアプローチです。
- ストアド プロシージャのメソッドを選択した場合、データベースには、ユーザーのページの読み取り、ユーザーの追加、ユーザーの更新、ユーザーの削除という 4 つのストアド プロシージャが必要になります。Microsoft Entra コネクタは、呼び出すストアド プロシージャの名前とパラメーターを使用して構成することになります。 この方法では、SQL データベースにより多くの構成が必要となります。通常は、アプリケーションで、ユーザーに対する変更ごとまたは大規模な結果セットをページングするため追加の処理が必要な場合にのみ必要になります。
- SQL クエリ メソッドを選択した場合は、実行プロファイルの実行時にコネクタが発行する特定の SQL ステートメントを入力します。 コネクタは、SQL ステートメントにコネクタが入力するパラメーター (インポート時の結果セットのページング、エクスポート中に作成される新しいユーザーの属性を設定するなど) を使用して構成します。

この記事では、`Employees`と**フル インポート**の実行プロファイルで、サンプル データベースのテーブル と対話するためにテーブル メソッドを使用する方法について説明します。 ストアド プロシージャまたは SQL クエリ メソッドの構成の詳細情報と具体的な要件については、「[汎用 SQL 構成ガイド](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/reference/microsoft-identity-manager-2016-connector-genericsql)」を参照してください。

### アプリケーションのデータベース スキーマで一意の識別子を選択する

ほとんどのアプリケーションは、アプリケーションのユーザーごとに一意の識別子を持ちます。 既存のデータベース テーブルにプロビジョニングする場合は、各ユーザーの値を持つそのテーブルのカラムを特定する必要があり、その値は一意であり、変更されることはありません。 この列は**アンカー**となり、Microsoft Entra ID が既存の行を識別して更新や削除ができるようにするために使用します。 アンカーの詳細については、「[アンカー属性と識別名について](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture#about-anchor-attributes-and-distinguished-names)」を参照してください。

アプリケーションのデータベースが既に存在していて、その中にユーザーが存在し、Microsoft Entra ID でそれらのユーザーを最新に保ちたい場合は、アプリケーションのデータベースと Microsoft Entra のスキーマの間で同じ各ユーザーの識別子を用意する必要があります。 たとえば、Microsoft Entra ID でアプリケーションにユーザーを割り当て、そのユーザーが既にそのデータベースに存在する場合、Microsoft Entra ID でそのユーザーに変更を加えると、新しい行が追加されるのではなく、そのユーザーの既存の行が更新されます。 Microsoft Entra ID は、そのユーザーのアプリケーションの内部識別子を保存しない可能性が高いので、データベースの**クエリ**に使用する別の列を選択することになります。 この列の値には、アプリケーションのスコープ内にある各ユーザーの Microsoft Entra ID に存在するユーザー プリンシパル名、メールアドレス、従業員 ID、またはその他の識別子を指定することができます。 アプリケーションが使用するユーザー識別子が、ユーザーの Microsoft Entra 表現に格納されている属性でない場合は、Microsoft Entra ユーザー スキーマを拡張して拡張属性を指定してデータベースからその属性を設定する必要はありません。 [PowerShell](https://learn.microsoft.com/ja-jp/powershell/azure/active-directory/using-extension-attributes-sample) を使用して Microsoft Entra スキーマを拡張し、拡張値を設定できます。

### Microsoft Entra ID 内の属性をデータベース スキーマにマップする

データベースに既に存在するユーザーであっても新しいユーザーであっても、Microsoft Entra ID 内のユーザーとデータベース内のレコードの間に Microsoft Entra ID でリンクが確立されている場合は、Microsoft Entra ID で Microsoft Entra ユーザーからデータベースに属性の変更をプロビジョニングできます。 一意の識別子に加え、データベースを調べて他に必要なプロパティがあるかどうかを特定します。 存在する場合は、データベースにプロビジョニングされるユーザーに、必要なプロパティにマップできる属性があることを確認します。

[プロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works#deprovisioning)の動作を構成することもできます。 Microsoft Entra ID でアプリケーションに割り当てられているユーザーが削除された場合、Microsoft Entra ID は削除操作をデータベースに送信します。 また、ユーザーがアプリケーションを使用できるスコープから外れる場合に、Microsoft Entra ID にデータベースを更新させることもできます。 ユーザーがアプリから割り当て解除されたか、Microsoft Entra ID で論理的に削除されたか、またはサインインがブロックされた場合は、Microsoft Entra ID が属性の変更を送信するように構成できます。 既存のデータベース テーブルにプロビジョニングする場合は、そのテーブルの列を **isSoftDeleted** にマップできます。 ユーザーがスコープから外れると、Microsoft Entra ID によってそのユーザーの値が **True** に設定されます。

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
5. **完了** を選択します。

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

    Note

    SQL コネクタでは、DSN 接続ファイルが UTF-8 でエンコードされることを想定しています。 ファイルが UTF-8 でエンコードされていない場合は、メモ帳を使用して UTF-8 エンコードでファイルを保存します。

### 3. Microsoft Entra Connect のプロビジョニング エージェントをインストールして構成する

プロビジョニング エージェントを既にダウンロードし、別のオンプレミス アプリケーション用に構成している場合は、次のセクションに進んでください。

1. Azure portal にサインインします。
2. **[エンタープライズ アプリケーション]** に移動し、**[新規アプリケーション]** を選択します。
3. **オンプレミスの ECMA アプリ** アプリケーションを検索し、アプリに名前を付けて、**[作成]** を選択してテナントに追加します。
4. メニューから、アプリケーションの **[プロビジョニング]** ページに移動します。
5. **開始する**を選択します。
6. **[プロビジョニング]** ページで、モードを **[自動]** に変更します。

[Image: [自動] を選択しているスクリーンショット。]

1. **[オンプレミス接続]** で、**[ダウンロードしてインストール]** を選択し、**[条項に同意してダウンロード]** を選択します。

[Image: エージェントのダウンロード場所のスクリーンショット。]

1. Portal を開いたままにして、プロビジョニング エージェント インストーラーを実行し、サービス使用条件に同意して、**[インストール]** を選択します。
2. Microsoft Entra プロビジョニング エージェント構成ウィザードが表示されるのを待ってから、**[次へ]** を選択します。
3. **[拡張機能を選択]** ステップで、**[オンプレミス アプリケーションのプロビジョニング]** を選択し、**[次へ]** を選択します。
4. プロビジョニング エージェントは、オペレーティング システムの Web ブラウザーを使用して、Microsoft Entra ID (および場合によっては組織の ID プロバイダー) に対する認証を行うためのポップアップ ウィンドウを表示します。 Windows Server でブラウザーとして Internet Explorer を使用している場合は、JavaScript を正しく実行できるように、ブラウザーの信頼済みサイト リストに Microsoft Web サイトを追加する必要がある場合があります。
5. 承認を求められたら、Microsoft Entra 管理者の資格情報を入力します。 ユーザーは、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)ロールを持っている必要があります。
6. **[Confirm]** を選択して設定を確認します。 インストールが成功したら、**[終了]** を選択し、プロビジョニング エージェント パッケージ インストーラーも閉じます。

### 4. オンプレミスの ECMA アプリを構成する

1. Portal に戻り、**[オンプレミス接続]** セクションで、デプロイしたエージェントを選択し、**[エージェントの割り当て]** を選択します。

    [Image: エージェントを選択して割り当てる方法を示すスクリーンショット。]
2. このブラウザー ウィンドウを開いたままにして、構成ウィザードを使用して構成の次の手順を完了します。

### 5. Microsoft Entra ECMA Connector Host の証明書を構成する

1. プロビジョニング エージェントがインストールされている Windows Server で、スタート メニューから **Microsoft ECMA2Host 構成ウィザード**を右クリックし、管理者として実行します。 ウィザードで必要な Windows イベント ログを作成するには、Windows 管理者として実行する必要があります。
2. このウィザードを初めて実行する場合は、ECMA Connector Host 構成の開始後に証明書の作成を求められます。 既定のポート **8585** のままにし、**[証明書の生成]** を選択して証明書を生成します。 自動生成された証明書は、信頼ルートの一部として自己署名されます。 証明書の SAN はホスト名と一致します。

    [Image: 設定の構成を示すスクリーンショット。]
3. **保存**を選択します。

Note

新しい証明書を生成することを選択した場合は、証明書の有効期限を記録した後、構成ウィザードに戻り、有効期限が切れる前に証明書を生成し直すようにしてください。

### 6. 汎用 SQL コネクタを作成する

このセクションでは、データベースのコネクタ構成を作成します。

#### 6.1 SQL 接続を構成する

汎用 SQL コネクタを作成するには、以下の手順に従ってください。

1. コネクタに対して Microsoft Entra ID を認証するために使用されるシークレット トークンを生成します。 これは、アプリケーションごとに最小 12 文字で、一意である必要があります。
2. まだ実行していない場合は、Windows スタート メニューから **Microsoft ECMA2Host 構成ウィザード**を起動します。
3. **[新しいコネクタ]** を選択します。
4. [**プロパティ]** ページで、次の表で指定した値をボックスに入力し、[次へ] を選択します。

    | プロパティ | [値] |
    | --- | --- |
    | 名前 | コネクタに対して選択した名前。これは、環境内のすべてのコネクタで一意である必要があります。 たとえば、SQL データベースが 1 つしかない場合は、`SQL` とします。 |
    | AutoSync タイマー (分) | 120 |
    | シークレット トークン | このコネクタ用に生成したシークレット トークンを入力します。 キーは 12 文字以上である必要があります。 |
    | 拡張 DLL | Generic SQL コネクタの場合、**Microsoft.IAM.Connector.GenericSql.dll** を選択します。 |
5. [**接続**] ページで、次の表で指定した値をボックスに入力し、[次 ] を選択します。

    | プロパティ | 説明 |
    | --- | --- |
    | DSN ファイル | SQL インスタンスに接続するために使用される、前の手順で作成したデータ ソース名ファイル。 |
    | ユーザー名 | SQL インスタンスのテーブルを更新する権限を持つアカウントのユーザー名。 ターゲット データベースが SQL Server であり、Windows 認証を使用している場合は、ユーザー名は、スタンドアロン サーバーの場合は hostname\sqladminaccount、ドメイン メンバー サーバーの場合は domain\sqladminaccount の形式にする必要があります。 他のデータベースの場合、ユーザー名はデータベース内のローカル アカウントになります。 |
    | パスワード | 指定したユーザー名のパスワード。 |
    | DN is Anchor (DN はアンカー) | お使いの環境でこれらの設定が必要であるとわかっている場合を除いて、 **[DN is Anchor](DN はアンカー)** および **[エクスポートの種類: オブジェクトの置換]** チェックボックスを選択しないでください。 |

#### 6.2 データベースからスキーマを取得する

資格情報を提供すると、ECMA コネクタ ホストはデータベースのスキーマを取得する準備が整います。 SQL 接続の構成を続けます。

1. **[スキーマ 1]** ページで、オブジェクトの種類の一覧を指定します。 このサンプルでは、オブジェクトの種類は `User` の 1 つです。 画像の下にある表に指定される値をボックスに入力し、[**次へ**] を選択します。

    [Image: [スキーマ 1] ページを示すスクリーンショット。]

    | プロパティ | [値] |
    | --- | --- |
    | オブジェクトの種類の検出方法 | 固定値 |
    | 固定値のリスト/テーブル/ビュー/SP | User |
2. **[次へ]** を選択すると、`User` オブジェクトの種類を構成するための次のページが自動的に表示されます。 [**スキーマ 2**] ページで、データベースでのユーザーの表示方法を指定します。 このサンプルでは、`Employees` という名前の 1 つの SQL テーブルです。 画像の下にある表に指定される値をボックスに入力し、[**次へ**] を選択します。

    [Image: [スキーマ 2] ページを示すスクリーンショット。]

    | プロパティ | [値] |
    | --- | --- |
    | ユーザー: 属性の検出 | テーブル |
    | ユーザー: テーブル/ビュー/SP | データベース内のテーブルの名前 (`Employees` など) |

    Note

    エラーが発生した場合は、データベース構成を調べて、**[接続]** ページで指定したユーザーがデータベースのスキーマへの読み取りアクセス権を持っていることを確認します。
3. **[次へ]** を選ぶと次のページが自動的に表示され、前に指定したテーブル (この例では `Employees` テーブル) で、ユーザーの `Anchor` および `DN` として使用する列を選択できます。 これらの列には、データベース内の一意の識別子が含まれます。 使用する列は同じ列でも異なる列でもかまいませんが、このデータベースに既に存在する行が、これらの列で必ず一意の値を持っているようにしてください。 **[スキーマ 3]** ページでは、画像の下にある表に指定される値をボックスに入力し、 **[次へ]** を選択します。

    [Image: [スキーマ 3] ページを示すスクリーンショット。]

    | プロパティ | 説明 |
    | --- | --- |
    | [:User] に [アンカー] を選択します | アンカーとして使用するデータベース テーブルの列 (`User:ContosoLogin` など) |
    | ユーザーの DN 属性を選択してください | DN 属性として使用するデータベースの列 (`AzureID` など) |
4. **[次へ]** を選択すると、`Employee` テーブルの各列のデータ型と、コネクタがそれらをインポート、またはエクスポートする必要があるかどうかを確認するための次のページが自動的に表示されます。 **[スキーマ 4]** ページでは、既定値のままにし、 **[次へ]** を選択します。

    [Image: [スキーマ 4] ページを示すスクリーンショット。]
5. **[グローバル]** ページで、ボックスに入力し、 **[次へ]** を選択します。 個々のボックスのガイダンスについては、画像の下にある表を参照してください。

    [Image: [グローバル] ページを示すスクリーンショット。]

    | プロパティ | 説明 |
    | --- | --- |
    | Delta Strategy (差分方式) | IBM DB2 の場合は、`None` を選択します |
    | ウォーター マーク クエリ | IBM DB2 の場合は、「`SELECT CURRENT TIMESTAMP FROM SYSIBM.SYSDUMMY1;`」と入力します |
    | データ ソースの日付と時刻の形式 | SQL Server の場合は `yyyy-MM-dd HH:mm:ss`、IBM DB2 の場合は `YYYY-MM-DD` |
6. **[パーティション]** ページで **[次へ]** を選択します。

    [Image: [パーティション] ページを示すスクリーンショット。]

#### 6.3 実行プロファイルを構成する

次に、**エクスポート**と**完全インポート**の実行プロファイルを構成します。 **エクスポート**実行プロファイルは、ECMA コネクタ ホストが Microsoft Entra ID からデータベースに変更を送信し、レコードの挿入、更新、削除を行う必要がある場合に使用されます。 **完全インポート**実行プロファイルは、ECMA コネクタ ホスト サービスの開始時に、データベースの現在のコンテンツを読み取るために使用されます。 このサンプルでは、ECMA コネクタ ホストが必要な SQL ステートメントを生成するように、両方の実行プロファイルでテーブル メソッドを使用します。

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

    - **アンカー**: この属性の値は、ターゲット データベース内のオブジェクトごとに一意である必要があります。 Microsoft Entra プロビジョニング サービスでは、初期サイクル後、この属性を使用して ECMA コネクタ ホストに対してクエリを実行します。 このアンカー列は、先に**スキーマ 3** のページで構成したアンカー値と同じである必要があります。
    - **クエリ属性**: この属性はアンカーと同じである必要があります。
    - **DN**: ほとんどの場合、**[自動生成]** オプションを選択する必要があります。 オフになっている場合は、`CN = anchorValue, Object = objectType` の形式で DN が格納されている Microsoft Entra ID の属性に、DN 属性がマップされるようにします。 アンカーと DN の詳細については、「[アンカー属性と識別名について](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture#about-anchor-attributes-and-distinguished-names)」を参照してください。

    | プロパティ | 説明 |
    | --- | --- |
    | ターゲット オブジェクト | User |
    | アンカー | [スキーマ 3] タブで構成されている列 (`ContosoLogin` など) |
    | クエリ属性 | アンカーと同じ列 (`ContosoLogin` など) |
    | DN | [スキーマ 3] タブで構成されているのと同じ列 (`ContosoLogin` など) |
    | 自動生成 | チェック済み |
2. ECMA コネクタ ホストにより、ターゲット データベースがサポートする属性が検出されます。 Microsoft Entra ID に公開する属性を選択できます。 プロビジョニングのために、これらの属性を Azure portal で構成できます。 **[属性の選択]** ページで、ドロップダウン リストのすべての属性を 1 つずつ追加します。

**[属性]** ドロップダウン リストには、ターゲット データベースで検出され、先ほどの *[属性の選択]* ページでは選択 "されなかった" 属性が示されます。 関連するすべての属性が追加されたら、**[次へ]** を選択します。

[Image: 属性ドロップダウン リストのスクリーンショット。]

1. **[プロビジョニング解除]** ページの **[フローを無効化]** で **[削除]** を選択します。 前のページで選択した属性を [プロビジョニング解除] ページで選択することはできません。 **完了** を選択します。

Note

**[Set attribute value](Set 属性値)**を使用すると、ブール値のみが許可されることに注意してください。

[Image: プロビジョニング解除のページを示すスクリーンショット。]

### 7. ECMA2Host サービスが実行されていることを確認する

1. Microsoft Entra ECMA Connector Host を実行しているサーバーで、**[開始]** をクリックします。
2. **「run」** と入力し、ボックスに **「services.msc」** と入力します。
3. **サービス** リストで、**Microsoft ECMA2Host** が確実に存在し、実行中であるようにします。 そうでない場合は、 **[開始]** を選択します。

    [Image: サービスが稼働中であることを示すスクリーンショット。]

新しいデータベース、または空でユーザーがいないデータベースに接続している場合は、次のセクションに進んでください。 それ以外の場合は、次の手順に従って、コネクタがデータベースから既存のユーザーを識別したことを確認してください。

1. 最近サービスを開始し、データベースに多数のユーザー オブジェクトがある場合は、コネクタがデータベースとの接続を確立するまで数分待ってください。

### 8. Azure portal でアプリケーション接続を構成する

1. アプリケーション プロビジョニングを構成していた Web ブラウザー ウィンドウに戻ります。

    Note

    ウィンドウがタイムアウトした場合は、エージェントを再選択する必要があります。

    1. Azure portal にサインインします。
    2. **[エンタープライズ アプリケーション]** の **[On-premises ECMA app](オンプレミス ECMA アプリ)** アプリケーションに移動します。
    3. **[プロビジョニング]** を選択します。
    4. **[作業の開始]** が表示された場合は、モードを **[自動]** に変更し、**[オンプレミス接続]** セクションで、デプロイしたエージェントを選択し、**[エージェントの割り当て]** を選択します。 それ以外の場合は、**[プロビジョニングの編集]** に移動します。
2. **[管理者資格情報]** セクションで、次の URL を入力します。 `{connectorName}` の部分を ECMA コネクタ ホスト上のコネクタの名前 (**SQL** など) に置き換えます。 コネクタ名は大文字と小文字が区別され、ウィザードで構成したのと同じ大文字と小文字の区別にする必要があります。 `localhost` をマシンのホスト名に置き換えることもできます。

    | プロパティ | [値] |
    | --- | --- |
    | テナントの URL | `https://localhost:8585/ecma2host_{connectorName}/scim` |
3. コネクタの作成時に定義した**シークレット トークン**の値を入力します。

    Note

    アプリケーションにエージェントを割り当てたばかりの場合は、登録が完了するまで 10 分間お待ちください。 登録が完了するまで、接続テストは機能しません。 サーバーでプロビジョニング エージェントを再起動してエージェントの登録を強制的に完了すると、登録プロセスを高速化することができます。 サーバーに移動し、Windows 検索バーで**サービス**を検索し、**Microsoft Entra Connect プロビジョニング エージェント サービス**を特定し、サービスを右クリックして再起動します。
4. **[接続のテスト]** をクリックし、1 分待ちます。

    [Image: エージェントの割り当てを示すスクリーンショット。]
5. 接続テストが成功し、指定された資格情報が認証されてプロビジョニングが有効になったことが示されたら、**[保存]** を選択します。

    [Image: エージェントのテストを示すスクリーンショット。]

### 9. 属性マッピングを構成する

次に、Microsoft Entra ID のユーザーの表現と、オンプレミス アプリケーションの SQL データベースのユーザーの表現との間で属性をマップする必要があります。

Azure portal を使用して、Microsoft Entra ユーザーの属性と、ECMA ホスト構成ウィザードで以前に選択した属性の間のマッピングを構成します。

1. Microsoft Entra スキーマに、データベースによって必要とされる属性が含まれていることを確認します。 ユーザーに `uidNumber` などの属性があることがデータベースによって要求され、その属性がユーザーの Microsoft Entra スキーマにまだ含まれていない場合は、[ディレクトリ拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping)を使用して、その属性を拡張機能として追加する必要があります。
2. Microsoft Entra 管理センターの **[エンタープライズ アプリケーション]** で、**[オンプレミスの ECMA アプリ]** アプリケーションを選択し、**[プロビジョニング]** ページを選択します。
3. **[プロビジョニングの編集]** を選択し、10 秒待ちます。
4. **[マッピング]** を展開し、**[Microsoft Entra ID ユーザーをプロビジョニング]** マッピングを選びます。 このアプリケーションの属性マッピングを初めて構成した場合、プレースホルダーのマッピングとして存在するのは、この 1 つのみです。

    [Image: ユーザーのプロビジョニングを示すスクリーンショット。]
5. データベースのスキーマが Microsoft Entra ID で使用可能であることを確認するには、**[詳細オプションの表示]** チェック ボックスをオンにし、**[ScimOnPremises の属性リストの編集]** を選択します。 構成ウィザードで選択したすべての属性が一覧表示されていることを確認します。 そうでない場合は、スキーマが更新されるまで数分待ってから、ページを再読み込みします。 属性が一覧表示されたら、このページを閉じてマッピング一覧に戻ります。
6. ここで、**userPrincipalNam** PLACEHOLDER のマッピングをクリックします。 このマッピングは、オンプレミス プロビジョニングを初めて構成するときに既定で追加されます。

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
    | 直接 | オブジェクトID | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:AzureID |
    | 直接 | メール | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:Email |
    | 直接 | givenName | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:FirstName |
    | 直接 | 名字 | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:LastName |
    | 直接 | メールニックネーム | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:textID |
3. マッピングをすべて追加したら、**[保存]** を選択します。

### 10. アプリケーションにユーザーを割り当てる

これで Microsoft Entra ECMA Connector Host が Microsoft Entra ID と通信できるようになり、属性マッピングが構成されたので、プロビジョニングの範囲に含めるユーザーの構成に進むことができます。

重要

ハイブリッド ID 管理者ロールを使用してサインインした場合は、少なくともこのセクションのアプリケーション管理者ロールを持つアカウントでサインアウトしてサインインする必要があります。 ハイブリッド ID の管理者ロールには、ユーザーをアプリケーションに割り当てるためのアクセス許可がありません。

SQL データベースに既存のユーザーが存在する場合は、それらの既存のユーザー向けのアプリケーション ロールの割り当てを作成する必要があります。 アプリケーション ロールの割り当てを一括で作成する方法の詳細については、[Microsoft Entra ID でのアプリケーションの既存ユーザーの管理](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-existing-users)に関するページを参照してください。

アプリケーションの既存のユーザーが存在しない場合は、アプリケーションに対してプロビジョニングされるテスト ユーザーを Microsoft Entra から選択します。

1. 選択したユーザーに、データベース スキーマの必須属性にマップされるすべてのプロパティがあることを確認します。
2. Azure portal で、 **[エンタープライズ アプリケーション]** を選択します。
3. **[オンプレミスの ECMA アプリ]** アプリケーションを選択します。
4. 左側のウィンドウの **[管理]** で、 **[ユーザーとグループ]** を選択します。
5. **[Add user/group](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/ユーザーまたはグループの追加)** を選択します。

    [Image: ユーザーの追加を示すスクリーンショット。]
6. **[ユーザー]** で **[選択なし]** を選択します。

    [Image: [選択なし] を示すスクリーンショット。]
7. 右側からユーザーを選択し、 **[選択]** ボタンを選択します。

    [Image: ユーザーの選択を示すスクリーンショット。]
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

詳細については、**[トラブルシューティングと推奨事項]** タブを開きます。ODBC ドライバーがメッセージを返した場合は、ここに表示される場合があります。 たとえば、メッセージ `ERROR [23000] [Microsoft][ODBC SQL Server Driver][SQL Server]Cannot insert the value NULL into column 'FirstName', table 'CONTOSO.dbo.Employees'; column does not allow nulls.` は ODBC ドライバーのエラーです。 この場合、`column does not allow nulls` は、データベース内の `FirstName` 列は必須ですが、プロビジョニングされるユーザーに `givenName` 属性がないため、ユーザーをプロビジョニングできなかったことを示している可能性があります。

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

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/on-premises-web-services-connector"} -->
## Web サービス コネクタを使用してアプリケーションに Microsoft Entra をプロビジョニングする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-web-services-connector
- Service: entra-id / app-provisioning
- Article date: 2025-04-09
- Summary: このドキュメントでは、Web サービス ベースの API を提供する外部システムでユーザーをプロビジョニングするために、Microsoft Entra ID を構成する方法について説明します。

次のドキュメントでは、汎用 Web サービス コネクタについて説明します。 Microsoft Entra ID ガバナンス では、 [SAP ECC](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-sap-connector-configure)、Oracle E-Business Suite、REST または SOAP API を公開する基幹業務アプリケーションなどのさまざまなアプリケーションへのアカウントのプロビジョニングがサポートされています。 これらのアプリケーションに接続するために MIM を以前デプロイしたことのあるお客様は、MIM 用に構築された同じ Web サービス コネクタを再利用しながら、軽量の Microsoft Entra プロビジョニング エージェントの使用に簡単に切り替えることができます。

### サポートされる機能

- アプリケーションでユーザーを作成して実行する。
- アクセスが不要になったユーザーをお使いのアプリケーションで削除する。
- Microsoft Entra ID とアプリケーション間でユーザー属性を同期させる。
- アプリケーションのスキーマを検出する。

Web サービス コネクタは次の機能を実装します。

- SOAP 検出: 管理者がターゲット Web サービスによって公開される WSDL パスを入力できるようにします。 検出では、アプリケーションでホストされている Web サービスのツリー構造と、その内部エンドポイントまたは操作、ならびに操作のメタデータの説明が生成されます。 実行できる検出操作の数に制限はありません (ステップ バイ ステップ)。 検出された操作は、後でデータソースに対してコネクタの操作 (インポート/エクスポートなど) を実装する操作のフローを構成するために使用されます。
- REST 検出: 管理者は、サービス エンドポイント、リソース パス、メソッド、パラメーターの詳細など、REST サービスの詳細を入力できます。 REST サービス情報は、`discovery.xml` プロジェクトの `wsconfig` ファイルに格納されます。 これらは、後で管理者がワークフロー内の REST Web サービス アクティビティを構成するときに使用されます。
- スキーマ構成: 管理者がスキーマを構成できるようにします。 スキーマ構成には、特定のアプリケーションのオブジェクトの種類と属性の一覧が含まれています。 管理者は、スキーマの一部となる属性を選択できます。
- 操作フローの構成: プロビジョニング対象のユーザーから Web サービス機能へのパラメータの割り当てを含む、公開された Web サービス操作機能を通じてオブジェクトの種類ごとにインポート操作とエクスポート操作の実装を構成するためのワークフロー デザイナー UI。

### プロビジョニングの前提条件

#### オンプレミスの前提条件

プロビジョニング エージェントを実行するコンピューターには、次のものが必要です。

- アプリケーションの REST または SOAP エンドポイントへの接続と、login.microsoftonline.com、 [他の Microsoft Online Services](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/urls-and-ip-address-ranges)、 [および Azure](https://learn.microsoft.com/ja-jp/azure/azure-portal/azure-portal-safelist-urls) ドメインへの送信接続。 1 つの例は、Azure IaaS でホストされているか、またはプロキシの背後にある Windows Server 2016 仮想マシンです。
- プロビジョニング エージェントをホストするための 3 GB 以上の RAM。
- .NET Framework 4.7.2
- Windows Server 2016 以降のバージョン。

プロビジョニングを構成する前に、次のことを確認してください。

- ユーザーの作成、更新、削除に必要な SOAP API または REST API をアプリケーションで公開します。

#### クラウドの要件

- Microsoft Entra ID P1 あるいは Premium P2 (または EMS E3 または E5) を使用する、Microsoft Entra テナント。

    この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 自分の要件に適したライセンスを探すには、「[一般提供されている Microsoft Entra ID の機能の比較](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)」を参照してください。
- プロビジョニング エージェントを構成するためのハイブリッド ID 管理者ロールと、Azure portal でプロビジョニングを構成するためのアプリケーション管理者またはクラウド アプリケーション管理者ロール。
- アプリケーションにプロビジョニングする Microsoft Entra ユーザーには、アプリケーションで必要なすべての属性が既に設定されている必要があります。

### Microsoft Entra Connect のプロビジョニング エージェントをインストールして構成する

1. Azure portal にサインインします。
2. **[エンタープライズ アプリケーション]** に移動し、**[新規アプリケーション]** を選択します。
3. **オンプレミスの ECMA アプリ** アプリケーションを検索し、アプリに名前を付けて、**[作成]** を選択してテナントに追加します。
4. メニューから、アプリケーションの **[プロビジョニング]** ページに移動します。
5. **開始する**を選択します。
6. **[プロビジョニング]** ページで、モードを **[自動]** に変更します。

[Image: [自動] を選択しているスクリーンショット。]

1. **[オンプレミス接続]** で、**[ダウンロードしてインストール]** を選択し、**[条項に同意してダウンロード]** を選択します。
2. ポータルを開いたままにして、プロビジョニング エージェント インストーラーを実行し、サービス使用条件に同意して、**[インストール]** を選択します。
3. Microsoft Entra プロビジョニング エージェント構成ウィザードが表示されるのを待ってから、**[次へ]** を選択します。
4. **[拡張機能を選択]** ステップで、**[オンプレミス アプリケーションのプロビジョニング]** を選択し、**[次へ]** を選択します。
5. プロビジョニング エージェントは、オペレーティング システムの Web ブラウザーを使用して、Microsoft Entra ID および組織の ID プロバイダーに対して認証するためのポップアップ ウィンドウを表示します。 Windows Server のブラウザーとして Internet Explorer を使用している場合は、JavaScript を正しく実行できるように、ブラウザーの信頼済みサイト一覧に Microsoft Web サイトを追加することが必要になる場合があります。
6. 承認を求められたら、Microsoft Entra 管理者の資格情報を入力します。 ユーザーは、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)ロールを持っている必要があります。
7. **[Confirm]** を選択して設定を確認します。 インストールが成功したら、**[終了]** を選択し、プロビジョニング エージェント パッケージ インストーラーも閉じます。

### オンプレミスの ECMA アプリを構成する

1. ポータルの **[オンプレミス接続]** セクションで、デプロイしたエージェントを選択し、**[エージェントの割り当て]** を選択します。

    [Image: エージェントを選択して割り当てる方法を示すスクリーンショット。]
2. このブラウザー ウィンドウを開いたままにして、構成ウィザードを使用して構成の次の手順を完了します。

### Microsoft Entra ECMA Connector Host の証明書を構成する

1. プロビジョニング エージェントがインストールされている Windows Server で、スタート メニューから microsoft ECMA2Host 構成ウィザードを右選択し、管理者として実行します。 ウィザードで必要な Windows イベント ログを作成するには、Windows 管理者として実行する必要があります。
2. ECMA コネクタ ホスト構成の開始後、ウィザードを初めて実行する場合は、証明書の作成を求められます。 既定のポート **8585** のままにし、**[証明書の生成]** を選択して証明書を生成します。 自動生成された証明書は、信頼されたルートの一部として自己署名されます。 証明書の SAN はホスト名と一致します。

    [Image: 設定の構成を示すスクリーンショット。]
3. **保存**を選択します。

### Web サービス コネクタ テンプレートを作成する

Web サービス コネクタ構成を作成する前に、Web サービス コネクタ テンプレートを作成し、特定の環境のニーズに合わせてテンプレートをカスタマイズする必要があります。 ServiceName、EndpointName、および OperationName が正しいことを確認してください。

サンプル テンプレートと、 [SAP ECC 7.0](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-sap-connector-configure) や Oracle E-Business Suite などの一般的なアプリケーションと統合する方法に関するガイダンスは、 [コネクタのダウンロード パッケージ](https://www.microsoft.com/download/details.aspx?id=51495)にあります。 [SOAP のワークフロー ガイド](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/reference/microsoft-identity-manager-2016-ma-ws-soap)を使用して、Web サービス構成ツールでデータ ソースの新しいプロジェクトを作成する方法を確認できます。

独自のアプリケーションの REST または SOAP API に接続するようにテンプレートを構成する方法の詳細については、MIM ドキュメント ライブラリの「[汎用 Web サービス コネクタの概要](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/reference/microsoft-identity-manager-2016-ma-ws)」を参照してください。

### 汎用 Web サービス コネクタを構成する

このセクションでは、アプリケーションのコネクタ構成を作成します。

#### プロビジョニング エージェントをアプリケーションに接続する

Microsoft Entra プロビジョニング エージェントをアプリケーションに接続するには、次の手順に従います。

1. Web サービス コネクタ テンプレート `.wsconfig` ファイルを `C:\Program Files\Microsoft ECMA2Host\Service\ECMA` フォルダーにコピーします。
2. コネクタに対する Microsoft Entra ID の認証に使用されるシークレット トークンを生成します。 これは、アプリケーションごとに最小 12 文字で、一意である必要があります。
3. まだ実行していない場合は、Windows スタート メニューから **Microsoft ECMA2Host 構成ウィザード**を起動します。
4. **[新しいコネクタ]** を選択します。

    [Image: 新しいコネクタの選択を示すスクリーンショット。]
5. **[プロパティ]** ページでは、画像の下にある表に指定される値をボックスに入力し、 **[次へ]** を選択します。

    [Image: プロパティの入力を示すスクリーンショット。]

    | プロパティ | [値] |
    | --- | --- |
    | 名前 | コネクタに対して選択した名前。これは、環境内のすべてのコネクタで一意である必要があります。 |
    | AutoSync タイマー (分) | 120 |
    | シークレット トークン | このコネクタ用に生成したシークレット トークンを入力します。 キーは 12 文字以上である必要があります。 |
    | 拡張 DLL | Web サービス コネクタの場合は、**Microsoft.IdentityManagement.MA.WebServices.dll** を選択します。 |
6. **[接続性]** ページでは、画像の下にある表に指定される値をボックスに入力し、 **[次へ]** を選択します。

    [Image: [接続性] ページを示すスクリーンショット。]

    | プロパティ | 説明 |
    | --- | --- |
    | Web サービス プロジェクト | Web サービス テンプレート名。 |
    | Host | アプリケーションの SOAP エンドポイントのホスト名 (例: vhcalnplci.dummy.nodomain) |
    | Port | アプリケーションの SOAP エンドポイントポート (8000など) |
7. [**機能**] ページで、次の表で指定した値をボックスに入力し、[**次へ**] を選択します。

    | プロパティ | [値] |
    | --- | --- |
    | 識別名の様式 | ジェネリック |
    | エクスポートの種類 | ObjectReplace |
    | データの正規化 | 無し |
    | オブジェクト確認 | normal |
    | インポートの有効化 | チェック済み |
    | 差分インポートの有効化 | オフ |
    | エクスポートの有効化 | チェック済み |
    | 完全エクスポートの有効化 | オフ |
    | 最初のパスのエクスポート パスワードの有効化 | チェック済み |
    | 最初のエクスポート パスに参照値なし | オフ |
    | オブジェクトの名前変更の有効化 | オフ |
    | 置換として削除/追加 | オフ |

注

Web サービス コネクタ テンプレートが Web サービス構成ツールで編集用に開かれている場合は、エラーが発生します。

1. **[グローバル]** ページのボックスに入力し、**[次へ]** を選択します。
2. **[パーティション]** ページで **[次へ]** を選択します。
3. **[プロファイルの実行]** ページで **[エクスポート]** チェックボックスを選択したままにします。 **[フル インポート]** チェックボックスを選択して、 **[次へ]** を選択します。 **エクスポート**実行プロファイルは、ECMA Connector ホストが Microsoft Entra ID からアプリケーションに変更を送信してレコードを挿入、更新、および削除する必要がある場合に使用されます。 **フル インポート** 実行プロファイルは、ECMA Connector ホスト サービスの開始時にアプリケーションの現在のコンテンツを読み取るために使用されます。

    | プロパティ | [値] |
    | --- | --- |
    | 輸出 | アプリケーションにデータをエクスポートする実行プロファイル この実行プロファイルは必須です。 |
    | フル インポート | アプリケーションからすべてのデータをインポートするプロファイルを実行します。 |
    | 差分インポート | 前回の完全インポートまたは差分インポート以降の変更のみをアプリケーションからインポートするプロファイルを実行します。 |
4. **[オブジェクトの種類]** ページで、ボックスに入力し、 **[次へ]** を選択します。 個々のボックスのガイダンスについては、画像の下にある表を参照してください。

    - **Anchor**: この属性の値は、ターゲット システム内のオブジェクトごとに一意である必要があります。 Microsoft Entra プロビジョニング サービスは、最初のサイクルの後にこの属性を使用して ECMA コネクタ ホストにクエリを実行します。 この値は、Web サービス コネクタ テンプレートで定義されます。
    - **DN**: ほとんどの場合、[自動生成] オプションを選択する必要があります。 選択解除されている場合、DN 属性が、CN = anchorValue, Object = objectType の形式で、DN を格納している Microsoft Entra ID 内の属性に確実にマップされるようにします。 アンカーと DN の詳細については、「[アンカー属性と識別名について](https://learn.microsoft.com/ja-jp/azure/active-directory/app-provisioning/on-premises-application-provisioning-architecture#about-anchor-attributes-and-distinguished-names)」を参照してください。

        | プロパティ | [値] |
        | --- | --- |
        | ターゲット オブジェクト | User |
        | アンカー | ユーザー名 |
        | DN | ユーザー名 |
        | 自動生成 | チェック済み |
5. ECMA コネクタ ホストで、アプリケーションでサポートされている属性が検出されます。 次に、検出された属性のうち、Microsoft Entra ID に公開する属性を選択できます。 プロビジョニングのために、これらの属性を Azure portal で構成できます。 **[属性の選択]** ページで、ドロップダウン リストのすべての属性を 1 つずつ追加します。 **[属性]** ドロップダウン リストには、ご使用のアプリケーションで検出され、以前の *[属性の選択]* ページでは選択**されなかった**属性が示されます。 関連するすべての属性が追加されたら、**[次へ] を選択**します。

    [Image: [属性の選択] ページを示すスクリーンショット。]
6. **[プロビジョニング解除]** ページの **[フローを無効化]** で **[削除]** を選択します。 前のページで選択した属性を [プロビジョニング解除] ページで選択することはできません。 **完了** を選択します。

注

**[Set attribute value](Set 属性値)**を使用すると、ブール値のみが許可されることに注意してください。

**[プロビジョニング解除ページ]** の [フローの無効化] で、**expirationTime** などのプロパティを使用してユーザー アカウントの状態を制御する場合は、[なし] を選択します。 [フローの削除] で、アプリケーションからユーザーを削除しない場合は [なし] を選択し、削除する場合は [削除] を選択します。 **完了** を選択します。

### ECMA2Host サービスが実行されていることを確認する

1. Microsoft Entra ECMA Connector Host を実行しているサーバーで、**[開始]** をクリックします。
2. **「run」** と入力し、ボックスに **「services.msc」** と入力します。
3. **サービス** リストで、**Microsoft ECMA2Host** が確実に存在し、実行中であるようにします。 そうでない場合は、 **[開始]** を選択します。

    [Image: サービスが稼働中であることを示すスクリーンショット。]
4. 最近サービスを開始し、アプリケーションに多数のユーザー オブジェクトがある場合は、コネクタがアプリケーションとの接続を確立し、最初の完全インポートを実行するまで数分待ちます。

### Azure portal でのアプリケーション接続の構成

1. アプリケーション プロビジョニングを構成していた Web ブラウザー ウィンドウに戻ります。

    注

    ウィンドウがタイムアウトした場合は、エージェントを再選択する必要があります。

    1. Azure portal にサインインします。
    2. **[エンタープライズ アプリケーション]** の **[On-premises ECMA app](オンプレミス ECMA アプリ)** アプリケーションに移動します。
    3. **[プロビジョニング]** を選択します。
    4. **[作業の開始]** を選択してからモードを **[自動]** に変更し、**[オンプレミス接続]** セクションで、デプロイしたエージェントを選択し、**[エージェントの割り当て]** を選択します。 それ以外の場合は、**[プロビジョニングの編集]** に移動します。
2. **[管理者資格情報]** セクションで、次の URL を入力します。 `{connectorName}` の部分を ECMA コネクタ ホスト上のコネクタの名前に置き換えます。 コネクタ名は大文字と小文字が区別され、ウィザードで構成したのと同じ大文字と小文字の区別にする必要があります。 `localhost` をマシンのホスト名に置き換えることもできます。

    | プロパティ | [値] |
    | --- | --- |
    | テナントの URL | `https://localhost:8585/ecma2host_APP1/scim` |
3. コネクタの作成時に定義した**シークレット トークン**の値を入力します。

    注

    アプリケーションにエージェントを割り当てたばかりの場合は、登録が完了するまで 10 分間お待ちください。 登録が完了するまで、接続テストは機能しません。 サーバーでプロビジョニング エージェントを再起動してエージェントの登録を強制的に完了すると、登録プロセスを高速化することができます。 サーバーに移動し、Windows 検索バーでサービスを検索し、Microsoft Entra Connect プロビジョニング エージェント サービスを特定し、サービスを右クリックして再起動します。
4. **[接続のテスト]** をクリックし、1 分待ちます。
5. 接続テストが成功し、指定された資格情報が認証されてプロビジョニングが有効になったことが示されたら、**[保存]** を選択します。

    [Image: エージェントのテストを示すスクリーンショット。]

### 属性マッピングの構成

次に、Microsoft Entra ID でのユーザーの表現とアプリケーション内のユーザーの表現の間で属性をマップします。

Azure portal を使用して、Microsoft Entra ユーザーの属性と、ECMA ホスト構成ウィザードで以前に選択した属性の間のマッピングを構成します。

1. Microsoft Entra スキーマに、アプリケーションに必要な属性が含まれていることを確認します。 ユーザーが属性を持っている必要があり、その属性がまだユーザーの Microsoft Entra スキーマの一部でない場合は、[ディレクトリ拡張機能機能](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping) を使用して、その属性を拡張機能として追加する必要があります。
2. Microsoft Entra 管理センターの **[エンタープライズ アプリケーション]** で、**[オンプレミスの ECMA アプリ]** アプリケーションを選択し、**[プロビジョニング]** ページを選択します。
3. **[プロビジョニングの編集]** を選択し、10 秒待ちます。
4. **[マッピング]** を展開し、**[Microsoft Entra ユーザーのプロビジョニング]** を選択します。 このアプリケーションの属性マッピングを初めて構成した場合、プレースホルダーのマッピングは 1 つだけ存在します。

    [Image: ユーザーのプロビジョニングを示すスクリーンショット。]
5. アプリケーションのスキーマがMicrosoft Entra IDで使用可能であることを確認するには、[**詳細オプション]** ドロップダウンを選択し、[**ターゲット ユーザー属性の編集]** を選択します。 構成ウィザードで選択したすべての属性が一覧表示されていることを確認します。 そうでない場合は、スキーマが更新されるまで数分待ってから、ページを再読み込みします。 属性が一覧表示されたら、このページをキャンセルしてマッピングの一覧に戻ります。
6. ここで、**userPrincipalName** PLACEHOLDER のマッピングを選択します。 このマッピングは、オンプレミス プロビジョニングを初めて構成するときに既定で追加されます。

[Image: プレースホルダーのスクリーンショット。]

 値を以下に合わせて変更します。

| マッピングの種類 | ソース属性 | ターゲット属性 |
| --- | --- | --- |
| 直接 | userPrincipalName | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:userName |

1. ここで **[新しいマッピングの追加]** を選択し、マッピングごとに次の手順を繰り返します。
2. アプリケーションに必要な各属性のソース属性とターゲット属性を指定します。 たとえば、次のように入力します。

    | Microsoft Entra 属性 | ScimOnPremises 属性 | 照合の優先順位 | このマッピングを適用する |
    | --- | --- | --- | --- |
    | ToUpper(Word([userPrincipalName], 1, "@"), ) | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:userName | 1 | オブジェクトの作成中のみ |
    | Redact("Pass@w0rd1") | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:export\_password |  | オブジェクトの作成中のみ |
    | 都市 | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:city |  | Always |
    | 会社名 | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:company |  | Always |
    | 部署 | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:department |  | Always |
    | メール | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:email |  | Always |
    | Switch([IsSoftDeleted], , "False", "9999-12-31", "True", "1990-01-01") | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:expirationTime |  | Always |
    | givenName | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:firstName |  | Always |
    | 名字 | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:lastName |  | Always |
    | 電話番号 | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:telephoneNumber |  | Always |
    | 職務タイトル | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:jobTitle |  | Always |
3. マッピングをすべて追加したら、**[保存]** を選択します。

### アプリケーションにユーザーを割り当てる

Microsoft Entra ECMA コネクタ ホストが Microsoft Entra ID と通信し、属性マッピングが構成されたので、プロビジョニングの対象となるユーザーの構成に進むことができます。

重要

このセクションでは、ハイブリッド ID の管理者ロールを使用してサインインした場合、一度サインアウトし、少なくともアプリケーション管理者ロールを持つアカウントでサインインする必要があります。 ハイブリッド ID の管理者ロールには、ユーザーをアプリケーションに割り当てるためのアクセス許可がありません。

ご使用のアプリケーションに既存のユーザーが存在する場合は、それらの既存のユーザー向けのアプリケーション ロールの割り当てを作成する必要があります。 アプリケーション ロールの割り当てを一括で作成する方法の詳細については、[Microsoft Entra ID でのアプリケーションの既存ユーザーの管理](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-existing-users)に関するページを参照してください。

アプリケーションの既存のユーザーが存在しない場合は、アプリケーションに対してプロビジョニングされるテスト ユーザーを Microsoft Entra から選択します。

1. 選択したユーザーが、アプリケーションの必要な属性にマップされるすべてのプロパティを持っていることを確認します。
2. Azure portal で、 **[エンタープライズ アプリケーション]** を選択します。
3. **[オンプレミスの ECMA アプリ]** アプリケーションを選択します。
4. 左側のウィンドウの **[管理]** で、 **[ユーザーとグループ]** を選択します。
5. **[Add user/group](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/ユーザーまたはグループの追加)** を選択します。

    [Image: ユーザーの追加を示すスクリーンショット。]
6. **[ユーザー]** で **[選択なし]** を選択します。

    [Image: [選択なし] を示すスクリーンショット。]
7. 右側からユーザーを選択し、 **[選択]** ボタンを選択します。

    [Image: ユーザーの選択を示すスクリーンショット。]
8. **[割り当て]** を選択します。

    [Image: ユーザーの割り当てを示すスクリーンショット。]

### プロビジョニングをテストする

属性がマップされ、ユーザーが割り当てられたので、ユーザーの 1 人でオンデマンド プロビジョニングをテストできます。

1. Azure portal で、 **[エンタープライズ アプリケーション]** を選択します。
2. **[オンプレミスの ECMA アプリ]** アプリケーションを選択します。
3. 左側で **プロビジョニング** を選択します。
4. **[Provision on demand] (オンデマンドでプロビジョニングする)** を選択します。
5. テスト ユーザーのものを検索し、 **[プロビジョニング]** を選択します。

    [Image: プロビジョニングのテストを示すスクリーンショット。]
6. 数秒後、ターゲット システム でユーザーが正常に作成 というメッセージが表示され、ユーザー属性の一覧が表示されます。

### ユーザーのプロビジョニングを開始する

1. オンデマンド プロビジョニングが成功したら、プロビジョニングの構成ページに戻ります。 スコープが割り当てられたユーザーとグループのみに確実に設定されているようにし、プロビジョニングを [**オン]** にして、 **[保存]** を選択します。

    [Image: プロビジョニングの開始を示すスクリーンショット。]
2. プロビジョニング サービスが開始されるまで最大 40 分待機します。 プロビジョニング ジョブが完了した後、テストが終了している場合は、次のセクションで説明されているように、プロビジョニングの状態を **[オフ]** に変更し、**[保存]** を選択できます。 これにより、プロビジョニング サービスは今後実行されなくなります。

### プロビジョニング エラーのトラブルシューティング

エラーが表示された場合は、**[プロビジョニング ログの表示]** を選択します。 [状態] が **[失敗]** になっている行をログで確認し、その行を選択します。

詳細については、**[トラブルシューティングと推奨事項]** タブに移動します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/partner-driven-integrations"} -->
## パートナー主導の統合を使用して、すべてのアプリケーションにアカウントをプロビジョニングします - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/partner-driven-integrations
- Service: entra-id / app-provisioning
- Article date: 2025-07-24
- Summary: パートナー主導の統合を使用して、すべてのアプリケーションにアカウントをプロビジョニングします。

Microsoft Entra プロビジョニング サービスを使用すると、[SaaS](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) と[オンプレミス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-scim-provisioning)の両方のアプリケーションにユーザーとグループをプロビジョニングできます。 次の 4 つの統合パスがあります。

**オプション 1 - Microsoft Entra アプリケーション ギャラリー:** Dropbox や Snowflake などの一般的なサード パーティ製アプリケーションは、Microsoft Entra アプリケーション ギャラリーを通じてお客様に提供されます。 新しいアプリケーションは、[アプリケーション ネットワーク ポータル](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing)を使用してギャラリーに簡単にオンボードできます。

**オプション 2 - アプリケーションに SCIM 準拠 API を実装:** 基幹業務アプリケーションが [SCIM](https://aka.ms/scimoverview) 標準をサポートしている場合は、[Microsoft Entra SCIM クライアント](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)と簡単に統合できます。

[Image: アプリケーションの SCIM 準拠 API の実装を示す図。]

**オプション 3 - Microsoft Graph を使用する:** 多くの新しいアプリケーションでは、Microsoft Graph を使用して、Microsoft Entra ID からユーザー、グループ、その他のリソースを取得します。 [SCIM と Graph](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/scim-graph-scenarios) を使用するシナリオの詳細を確認できます。

**オプション 4 - パートナー主導型のコネクタを使用する:** アプリケーションで SCIM がサポートされていない場合、パートナーは[カスタム ECMA コネクタ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-custom-connector)と SCIM ゲートウェイを構築し、Microsoft Entra ID を多数のアプリケーションと統合します。 **このドキュメントを活用して、パートナーは Microsoft Entra ID と互換性のある統合を証明し、顧客はそれらのパートナー主導の統合を検出できます。** カスタム ECMA コネクタと SCIM ゲートウェイは、サード パーティ ベンダーによって構築、保守、および所有されています。

[Image: Microsoft Entra SCIM クライアントとターゲット アプリケーションの間のゲートウェイを示す図。]

### 利用可能なパートナー主導の統合

次のアプリケーションの説明とリストは、パートナー自身によって提供されています。 サポートされているアプリケーションの一覧を使用して、連絡するパートナーを特定し、詳細情報を確認できます。

#### Aquera

##### Description

1,000 件以上の顧客のデプロイにより、Aquera は、Microsoft Active Directory (AD) と Microsoft Entra ID の HR プロセスと ID 統合を提供します。 Aquera は、従業員のライフサイクル全体を通じてユーザー アカウントのプロビジョニングとプロビジョニング解除を自動化して、就職者、異動者、退職者の自動化を可能にします。 Aquera の事前に構築された即使用可能な [HR Sync Connectors](https://azuremarketplace.microsoft.com/marketplace/apps/aquerainc1584125423571.hr-sync-for-microsoft-entra-id-azure-ad-by-aquera?tab=Overview) は、50 以上の HR/HCM システムに対応して、AD および Microsoft Entra ID への HR 主導のユーザーおよびアカウントの受信プロビジョニングを自動化します。 Microsoft Entra ID 用の Aquera SCIM Gateway は、記録用 HR システムを超えて、業界標準のクロスドメイン ID 管理システム (SCIM) を通じて、ギャラリー外のアプリ向けにユーザー、アカウント、およびグループ (使用可能な場合) のプロビジョニングとデプロビジョニングを行います。 2020 年以降、[Microsoft と Aquera は、Microsoft Entra アプリ ギャラリーにはない数百個のアプリに Microsoft Entra ID 接続を拡張してきました](https://techcommunity.microsoft.com/t5/microsoft-entra-blog/automate-user-provisioning-for-more-applications-with-our-new/ba-p/1751668)。

Aquera SCIM ゲートウェイは、Microsoft Entra ID と、B2B パートナー向けのプロビジョニング ターゲット アプリケーション、ディレクトリ、データベース、デバイス、ファイル、またはサード パーティ ID プロバイダー (IDP) ([Microsoft Entra アプリ ギャラリー](https://portal.azure.com/#view/Microsoft_AAD_IAM/AppGalleryBladeV2)に含まれていない) との間に、またはギャラリー アプリが自動プロビジョニングをサポートしていない場所に、すぐに使用できる接続を提供します。 Microsoft と Aquera は、ユーザー ID、ロール、エンタイトルメントが動的に変化するにつれて [ID 管理をスケーリングするように Microsoft Entra ID](https://www.microsoft.com/en-us/security/business/identity-access/microsoft-entra-lifecycle-management-software) を拡張します。 900 個を超える事前構築済みコネクタの Aquera カタログでは、REST、SQL、LDAP、SOAP、SCIM、および Microsoft Entra ID との互換性がない SCIM などの複数の統合方法がサポートされています。 さらに、これらのコネクタは、Web サービス API、管理コンソールの自動化、SDK、コード ライブラリ、ファイル、および [Microsoft API 駆動型インバウンド プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-concepts)をサポートします。

##### 連絡先の情報

- 会社の Web サイト: https://www.aquera.com
- Azure Marketplace の登録情報: [https://azuremarketplace.microsoft.com/en-us/marketplace/apps?search=aquera&page=1](https://azuremarketplace.microsoft.com/en-us/marketplace/apps?search=aquera&amp;page=1)
- AppSource Marketplace 登録情報: [https://appsource.microsoft.com/en-us/marketplace/apps?search=aquera&page=1](https://appsource.microsoft.com/en-us/marketplace/apps?search=aquera&amp;page=1)

##### サポートされている一般的なアプリケーション

- 人事情報システム アプリケーション: Oracle Cloud HCM、Dayforce、UKG Pro/Ready/Pro Workforce、ADP (すべて米国および国際バージョン)、Workday、SAP HR、Greenhouse、iCIMS、SuccessFactors、HiBob、BambooHR、Paylocity、Paycor、PeopleSoft、Cornerstone、Lever など、40 個を超える
- エンタープライズ リソース プランニング アプリケーション: Netsuite、Oracle Cloud ERP、Oracle EBS、SAP ERP Central Component (ECC)、SAP S/4HANA、Sage Intacct、PeopleSoft ERP
- 電子カルテ アプリケーション: Epic、Cerner、PointClickCare、MyAvatar、Homecare Homebase
- 学生情報システム: Ellucian Banner、PeopleSoft Campus Solutions
- データベース: Oracle、MySQL、SQLServer、MongoDB、PostgreSQL、AS/400 DB2、DB2、Snowflake、Redshift
- ディレクトリと IDP: AS/400、Resource Access Control Facility (RACF)、ACF2、TopSecret、OpenLDAP、IDP ディレクトリ

#### IDMWORKS

##### Description

ID およびアクセス管理とデータ センター管理を専門にしています。 Microsoft Entra プラットフォームは、メインフレーム システム (RACF、Top Secret、ACF2)、ミッドレンジ システム (AS400)、医療アプリケーション (EPIC、Cerner)、Linux および Unix サーバー、データベース、および多数のオンプレミスとクラウドのアプリケーションのユーザー ライフサイクル管理のために IDMWORKS IdentityForge (IDF) ゲートウェイと統合されます。 IdentityForge は、すべてのライフサイクル管理の信頼できるソースとして機能する、中央の標準化された統合エンジンと最新の ID ストアを提供します。 Microsoft Entra ID 用 IDF ゲートウェイは、メインフレーム システム (RACF、Top Secret、ACF2) や医療アプリケーション (EPIC、Cerner) などの Microsoft Entra コネクタ ポートフォリオで対応していないインポート ソースとプロビジョニング ターゲット システムに対してライフサイクル管理を提供します。 IDF ゲートウェイは、Microsoft Entra ID ライフサイクル管理 (LCM) を利用して、メインフレームと医療ソースのユーザー アカウント情報を継続的に同期し、ユーザー アカウントの作成、読み取りまたはインポート、更新、非アクティブ化、削除、グループ管理の実行などのアカウント プロビジョニング ライフサイクルのユース ケースを自動化します。

##### 連絡先情報

- 会社の Web サイト: https://www.idmworks.com/
- 連絡先情報: https://www.idmworks.com/contact/

##### サポートされている一般的なアプリケーション

メインフレーム、医療、ERP 統合の先進的プロバイダー。 詳しくは、https://www.idmworks.com/product/identityforge-connectors/ をご覧ください

- IBM RACF（リソース・アクセス・コントロール・ファシリティ）
- CA トップシークレット
- CA ACF2
- IBM i (AS/400)
- HP NonStop
- EPIC
- SAP ECC

#### KloudIdentity

##### Description

KloudIdentity は、オンプレミスの基幹業務 (LOB) アプリケーションのオンボード プロセスを簡素化し、Microsoft Entra ID を使用したユーザーとグループの送信プロビジョニングを容易にすることで、企業の運用を効率化します。 SCIM ゲートウェイとして機能し、SCIM に準拠していない LOB アプリケーションを Microsoft Entra ID とシームレスに統合できるようにします。 このアプリケーションは、ユニバーサル コネクタ スペースと呼ばれる独自の設計パラダイムに基づいています。これは、テンプレート駆動型アプローチを使用してさまざまな LOB アプリケーション用のコネクタを作成するための標準化されたプラットフォームを提供します。 この手法により、送信プロビジョニングのために SCIM に準拠していない LOB アプリケーションを Microsoft Entra ID にオンボードすることに伴うリード タイムとコストが大幅に削減されます。

LOB アプリケーションとの統合は、主に REST API を使用して行い、SOAP API と PowerShell スクリプトもサポートされます。 管理者は、ユーザー フレンドリな Web ポータルを使用して、属性マッピング テンプレートを簡単に構成できます。 グラフィカルな UI 駆動型属性マッピングに加えて、KloudIdentity はカスタマイズ可能なポリシーベースのアプローチを提供し、属性マッピングとペイロード生成の高度に調整されたシーケンスの構成を可能にします。

さらに、KloudIdentity には、複雑で高度な統合シナリオにも対応できる、標準コネクタ プラットフォームをカスタマイズするためのソフトウェア開発キット (SDK) が用意されています。 この SDK により、ユーザーは KloudIdentity フレームワーク内で安全かつ効率的にコード変更を行えるようになり、ユーザーとグループのプロビジョニングのために LOB アプリケーションをシームレスに統合できます。 SCIM ゲートウェイは、クラウドベースのソリューションとして、またはオンプレミスのデータ センター内にデプロイできます。従来のインターネット インフォメーション サービス (IIS) セットアップから Kubernetes の調整された環境まで、さまざまな展開オプションをサポートします

##### 連絡先情報

- 会社の Web サイト: https://www.kloudynet.com/
- Azure Marketplace の登録情報: https://azuremarketplace.microsoft.com/en-us/marketplace/consulting-services/kloudynettechnologiessdnbhd1588004273044.identity_implementation

##### サポートされている一般的なアプリケーション

KloudIdentity は、ユニバーサル コネクタ プラットフォームを提供します。そのため、すべての LOB アプリケーションを REST または SOAP API、PowerShell スクリプト、SDK アプローチと統合できます。

#### クォーラム システム ID DNA

##### Description

Identity DNA は、ネイティブの Microsoft Entra ID ガバナンス機能を補完および強化するために設計されたクォーラムの目的で構築されたプラットフォームです。 このサービスは、クロスドメイン ID 管理 (SCIM) ゲートウェイのシステムとして機能し、顧客の Azure 環境にデプロイされ、CICD パイプライン経由で管理されます。

Identity DNA プラットフォームは、複雑な統合シナリオを処理しながら、エンド ツー エンドの ID フローを強化し、より優れたユーザー エクスペリエンスを実現し、主要なビジネスと技術的な課題に対処できるように設計されています。

IDENTITY DNA プラットフォームは、クラウドまたはオンプレミスのデータ ソースへの事前に定義された多数の接続 (REST API、SOAP API、データベース、PowerShell、または CSV) を介して任意のアプリケーションに接続できます。

その後、ID DNA は、そのデータをクロスドメイン ID 管理 (SCIM) ペイロード用のシステムに変換し、それを Entra プロビジョニング サービスに配信します。 Identity DNA プラットフォームを使用すると、アプリケーション間のデータを双方向に同期できるため、さまざまなシステム ID を簡単に最新の状態に保つことができます。

##### 連絡先情報

- 会社の Web サイト: https://quorumsystems.com.au/
- AppSource の登録情報: Entra ユーザー ID プロビジョニング https://appsource.microsoft.com/en-us/marketplace/consulting-services/quorumsystemsptyltd1587621343059.entrauseridprovisioning

##### サポートされている一般的なアプリケーション

ID DNA は、REST または SOAP API、データベース、PowerShell スクリプト、または LDAP などの他のディレクトリ サービスを持つ任意の基幹業務アプリケーションと統合できます。

#### Traxion SCIM ゲートウェイ

##### Description

Traxion は、業界全体の標準化が ID とアクセス管理 (IAM) の未来において大きな役割を果たすと考えます。 私たちはベスト プラクティスと共通の標準を使用してお客様がセキュリティのゴールを達成できるようにお手伝いします。 クロスドメイン ID 管理システム (SCIM) も、そのような標準の 1 つです。

IGA システムをデプロイする企業は、ユーザー、グループ、その他のデータ エンティティを同期するためにアプリケーションを接続することの難しさという課題に直面しています。 アプリケーション統合を接続して維持するプロセスは簡単ではなく、時間とコストがかかることが既に実証されています。これは IAM システムをデプロイする企業だけでなく、アプリケーション ベンダーとインテグレーターにとっても同様です。 特に、SCIM 2.0 などの共通標準をサポートしていないアプリケーションを接続する場合は困難です。 Traxion の SCIM Gateway を使用するお客様は、標準化された統合の利点をリード タイムと全体的なコストの縮小という形で活用できます。

SCIM Gateway は使いやすく安全な標準化されたソリューションであり、ネイティブでは SCIM をサポートしないアプリケーションとの間でお客様の ID とアクセスに関する情報のすべてを同期させることができます。

統合には、次のものが含まれます。

- AFAS Profit
- Splunk
- ATS
- Oracle ERP
- SAPの成功要因
- Tempus リソース
- 汎用コネクタ (CSV、SQL)
- [パブリック SDK](https://www.nuget.org/packages/Traxion.Scim.Gateway.Connectors.Sdk) を使用して独自のコネクタを開発する

##### 連絡先情報

- 会社の Web サイト: https://www.traxion.com/products/iam-integration/scim-gateway/
- 連絡先情報: https://www.traxion.com/contact/

#### ユニファイソリューションズ

##### Description

UNIFY Solutions は、ID、アクセス、セキュリティ、ガバナンスのソリューションの先進的プロバイダーです。

##### 連絡先情報

- 会社の Web サイト: https://unifysolutions.net/identity/unifyconnect
- 連絡先情報: https://unifysolutions.net/contact/

##### サポートされている一般的なアプリケーション

- オーリオン人事・給与システム
- フロンティア ソフトウェア chris21
- TechnologyOne 人事
- アセンダー HCM
- Fusion5 EmpowerHR
- SAP ERP Human Capital Management

#### カスタム コネクタ

Microsoft Entra ID には、SCIM、SQL、LDAP、REST、SOAP、PowerShell を含むプロトコルとインターフェイスをサポートするアプリケーションにプロビジョニングするための接続性が含まれています。 これらのプロトコルとインターフェイスのいずれかをサポートしていないアプリケーションへの接続性のために、お客様と[パートナー](https://learn.microsoft.com/ja-jp/archive/technet-wiki/1589.fim-2010-mim-2016-management-agents-from-partners)には、Microsoft Identity Manager (MIM) 2016 と共に使用するためのカスタム Extensible Connectivity (ECMA) [コネクタ](https://learn.microsoft.com/ja-jp/previous-versions/windows/desktop/forefront-2010/hh859557%28v=vs.100%29)があります。 コミュニティ メンバーは、ブログ上およびパブリック ソース コード リポジトリ内でホストされる ECMA コネクタも構築しています。 これらの同じ ECMA2 コネクタを使用して、MIM 同期をデプロイする必要なく、Microsoft Entra プロビジョニング エージェントと Extensible Connectivity (ECMA) コネクタ ホストと共にアプリにプロビジョニングできます。詳細については、「[カスタム コネクタを使用したプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-custom-connector)」を参照してください。

MIM と共にカスタム コネクタを使用している場合は、[MIM コネクタ構成をエクスポート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-migrate-microsoft-identity-manager)し、Microsoft Entra にインポートできます。

### パートナー主導の統合をこのドキュメントに追加する方法

SCIM ゲートウェイを構築し、この一覧に追加する場合は、次の手順に従います。

1. Microsoft Entra SCIM の実装を理解するには、Microsoft Entra SCIM [のドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)を確認してください。
2. Microsoft Entra SCIM クライアントと SCIM ゲートウェイの間の互換性をテストします。
3. 次の [github リポジトリ](https://github.com/MicrosoftDocs/entra-docs/blob/main/docs/includes/application-integration-partners.md)に移動します。
4. 変更を開始するには、記事の上部にある鉛筆を選択します。
5. マークダウン言語を使用して記事を変更し、プル要求を作成します。 プル要求の説明を必ず記載してください。
6. リポジトリの管理者は、変更をレビューしてマージし、他のユーザーが変更を表示できるようにします。

### Guidelines

- 新しいパートナーはアルファベット順に追加します。
- エントリを 500 語に制限します。
- 顧客が詳細を調べられるように、連絡先情報を記載してください。
- 重複を回避するため、[Microsoft Entra アプリケーション ギャラリー](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)にすぐに使用できるプロビジョニング コネクタがまだないアプリケーションのみを含めてください。

### Disclaimer

独立系ソフトウェア ベンダーの場合: セクション 2 から 4 を除く Microsoft Entra アプリケーション ギャラリー使用条件が、このパートナー主導の統合カタログ ("統合カタログ") に適用されます。 "ギャラリー" への言及は "統合カタログ" と解釈され、"アプリ" への言及は "統合" と解釈されます。

これらの条件に同意しない場合は、統合カタログに登録するための統合を送信しないでください。 統合カタログに統合を送信する場合、お客様またはお客様が代表するエンティティ ("YOU" または "YOUR") がこれらの条件に同意することに同意するものとします。

Microsoft は、独自の裁量により、お客様の提案された統合を承諾または拒否する権利を有し、本統合カタログでアプリが提示、昇格、または掲載される方法を決定する権利を留保します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/plan-auto-user-provisioning"} -->
## Microsoft Entra ID の自動ユーザー プロビジョニングのデプロイを計画する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-auto-user-provisioning
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: Microsoft Entra ID の自動ユーザー プロビジョニングの計画と実行に関するガイダンス

多くの組織は、エンドユーザーの生産性に関して、ServiceNow、Zscaler、Slack などのサービスとしてのソフトウェア (SaaS) アプリケーションに依存しています。 従来、IT スタッフは、各 SaaS アプリケーションのユーザー ID を安全に管理するために、CSV ファイルをアップロードしたりカスタム スクリプトを使用したりするなどの手動のプロビジョニング方法に依存してきました。 これらのプロセスは、エラーが発生しやすく、安全でなく、管理が困難です。

Microsoft Entra の自動ユーザープロビジョニングでは、ビジネス ルールに基づいて SaaS アプリケーションでのユーザー ID の作成、保守、削除を安全に自動化することにより、このプロセスを簡素化します。 この自動化により、クラウドベースのソリューションへの依存を拡大するときに、クラウド専用環境とハイブリッド環境の両方で ID 管理システムを効果的に拡大することができます。

機能の理解を深めるために、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) する」を参照してください。

Microsoft Entra の自動ユーザー プロビジョニングでは、SaaS アプリケーションへのプロビジョニングに加え、多くのオンプレミスおよびプライベート クラウド アプリケーションへのプロビジョニングもサポートしています。 詳細については、 [Microsoft Entra のオンプレミス アプリケーション ID プロビジョニング アーキテクチャ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture)に関する説明を参照してください。

### Learn

ユーザー プロビジョニングによって、継続的な ID ガバナンスの基盤が作成され、信頼性の高い ID データに依存するビジネス プロセスの品質が向上します。

#### 主なベネフィット

自動ユーザー プロビジョニングを有効にすると、主に次のような利点があります。

- **生産性の向上**。 1 つのユーザー プロビジョニング管理インターフェイスで、複数の SaaS アプリケーションにわたってユーザー ID を管理できます。 このインターフェイスには、プロビジョニング ポリシーの単一のセットがあります。
- **リスクを管理します**。 ロールやアクセス権を定義する従業員の状態またはグループ メンバーシップに基づいて変更を自動化することで、セキュリティを強化できます。
- **コンプライアンスとガバナンスに対処します**。 Microsoft Entra ID では、すべてのユーザー プロビジョニング要求に対してネイティブ プロビジョニング ログがサポートされています。 要求は、ソース システムとターゲット システムの両方で実行されます。 プロビジョニング ログを使用すると、1 つの画面から、どのユーザーがアプリケーションにアクセスできるかを追跡できます。
- **コストを削減します**。 自動ユーザー プロビジョニングを使用すると、手動プロビジョニングに関連した非効率性や人的エラーを回避することでコストが削減されます。 これにより、独自開発のユーザー プロビジョニング ソリューション、スクリプト、およびプロビジョニング ログの必要性が軽減されます。

#### ライセンス

Microsoft Entra ID は、アプリケーション ギャラリー メニューに用意されているテンプレートを使用して、任意のアプリケーションのセルフサービス統合を提供します。 ライセンス要件の完全な一覧については、 [Microsoft Entra の価格に](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)関するページを参照してください。

##### アプリケーションのライセンス

自動的にプロビジョニングするアプリケーションの適切なライセンスが必要になります。 アプリケーションに割り当てられたユーザーがアプリケーション ロールに適したライセンスを持っているかどうかを、アプリケーション所有者と話し合ってください。 Microsoft Entra ID でロールに基づいて自動プロビジョニングを管理する場合、Microsoft Entra ID で割り当てられているロールをアプリケーション ライセンスと一致させる必要があります。 アプリケーションで所有されているライセンスが正しくないと、ユーザーのプロビジョニングまたは更新中にエラーが発生する可能性があります。

#### 用語

この記事では、次の用語を使用しています。

- CRUD 操作 - ユーザー アカウントに対して実行されるアクション: 作成、読み取り、更新、削除。
- シングル サインオン (SSO) - ユーザーが一度のサインオンですべての SSO 対応アプリケーションにアクセスできるようにする機能。 ユーザー プロビジョニングのコンテキストにおいて、SSO は、自動ユーザー プロビジョニングを使用するすべてのシステムにアクセスするための単一アカウントをユーザーが持つことの結果です。
- ソース システム - Microsoft Entra ID のプロビジョニング元であるユーザーのリポジトリ。 Microsoft Entra ID は、事前統合されているほとんどのプロビジョニング コネクタのソース システムです。 ただし、SAP、Workday、AWS などのクラウド アプリケーションにはいくつかの例外があります。 たとえば、「 [Workday から AD へのユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial)」を参照してください。
- ターゲット システム - Microsoft Entra ID のプロビジョニング先であるユーザーのリポジトリ。 ターゲット システムは、通常、ServiceNow、Zscaler、Slack などの SaaS アプリケーションです。 ターゲット システムは、AD などのオンプレミス システムにすることもできます。
- [クロスドメイン ID 管理 (SCIM) のシステム](https://aka.ms/scimoverview) - ユーザー プロビジョニングの自動化を可能にするオープン標準。 SCIM は、ID プロバイダーとサービス プロバイダーとの間で、ユーザー ID データをやりとりします。 Microsoft は、ID プロバイダーの一例です。 Salesforce は、サービス プロバイダーの一例です。 サービス プロバイダーはユーザー ID 情報を必要とし、ID プロバイダーはその必要を満たします。 SCIM は、ID プロバイダーとサービス プロバイダーが情報を送受信するために使用するメカニズムです。

#### トレーニング リソース

| リソース | リンクと説明 |
| --- | --- |
| オンデマンド ウェビナー | [Microsoft Entra ID を使用してエンタープライズ アプリケーションを管理する](https://info.microsoft.com/CO-AZUREPLAT-WBNR-FY18-03Mar-06-ManageYourEnterpriseApplicationsOption1-MCW0004438_02OnDemandRegistration-ForminBody.html)Microsoft Entra ID を利用してエンタープライズ SaaS アプリケーションへの SSO を実現する方法と、アクセスを制御するためのベスト プラクティスについて学習します。 |
| ビデオ | [Microsoft Entra IDでのユーザー プロビジョニングとは](https://youtu.be/_ZjARPpI6NI)[Microsoft Entra IDでユーザー プロビジョニングをデプロイする方法](https://youtu.be/pKzyts6kfrw)[Salesforce と Microsoft Entra ID の統合: ユーザー プロビジョニングを自動化する方法](https://youtu.be/MAy8s5WSe3A) |
| オンライン コース | SkillUp Online: [ID の管理](https://skillup.online/courses/course-v1:Microsoft+AZ-100.5+2018_T3/) Microsoft Entra ID を多くの SaaS アプリケーションと統合し、それらのアプリケーションへのユーザー アクセスをセキュリティで保護する方法について学習します。 |
| 書籍 | [Web アプリケーション用の Microsoft Entra ID を使用した先進認証 (開発者向けリファレンス) 第 1 版](https://www.amazon.com/Authentication-Directory-Applications-Developer-Reference/dp/0735696942/ref=sr_1_fkmr0_1?keywords=Azure+multifactor+authentication&amp;qid=1550168894&amp;s=gateway&amp;sr=8-1-fkmr0)。  ‎これは、これらの新しい環境向けに Active Directory 認証ソリューションを構築するための、信頼性の高い詳細なガイドです。 |
| チュートリアル | [SaaS アプリと Microsoft Entra ID を統合する方法に関するチュートリアルの一覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)を参照してください。 |
| FAQ | 自動ユーザー プロビジョニング[に関してよく寄せられる質問](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) |

#### ソリューションのアーキテクチャ

Microsoft Entra プロビジョニング サービスは、各アプリケーション ベンダーから提供されるユーザー管理 API エンドポイントに接続することによって、SaaS アプリや他のシステムにユーザーをプロビジョニングします。 これらのユーザー管理 API エンドポイントを使用すると、Microsoft Entra ID ではプログラムによってユーザーを作成、更新、削除できます。

##### ハイブリッド エンタープライズ向けの自動ユーザー プロビジョニング

この例では、ユーザーとグループは、オンプレミスのディレクトリに接続されている HR データベースに作成されます。 Microsoft Entra プロビジョニング サービスは、ターゲット SaaS アプリケーションへの自動ユーザー プロビジョニングの管理を行います。

[Image: ユーザー プロビジョニング]

**ワークフローの説明:**

1. ユーザー/グループは、オンプレミスの HR アプリケーション/システム (SAP など) で作成されます。
2. **Microsoft Entra Connect エージェント** は、ローカル AD から Microsoft Entra ID への ID (ユーザーとグループ) のスケジュールされた同期を実行します。
3. **Microsoft Entra プロビジョニング サービス** は、ソース システムとターゲット システムに対する [初期サイクル](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を開始します。
4. **Microsoft Entra プロビジョニング サービス** は、最初のサイクル以降に変更されたユーザーとグループについてソース システムに対してクエリを実行し、 [増分サイクル](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)で変更をプッシュします。

##### クラウド専用エンタープライズの自動ユーザー プロビジョニング

この例では、ユーザーの作成は Microsoft Entra ID で行われ、Microsoft Entra プロビジョニング サービスは、ターゲット (SaaS) アプリケーションへの自動ユーザー プロビジョニングを管理します。

[Image: オンプレミスの H R アプリケーションから Microsoft Entra プロビジョニング サービスを介してターゲット S A A S アプリケーションへのユーザー/グループ作成プロセスを示す図。]

**ワークフローの説明:**

1. ユーザーおよびグループが Microsoft Entra ID に作成されます。
2. **Microsoft Entra プロビジョニング サービス** は、ソース システムとターゲット システムに対する [初期サイクル](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を開始します。
3. **Microsoft Entra プロビジョニング サービスは、** 最初のサイクル以降に更新されたすべてのユーザーとグループについてソース システムに対してクエリを実行し、 [増分サイクルを実行します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)。

##### クラウド HR アプリケーションの自動ユーザー プロビジョニング

この例では、ユーザーとグループは、Workday や SuccessFactors などのクラウド人事アプリケーションで作成されます。 Microsoft Entra プロビジョニング サービスおよび Microsoft Entra Connect プロビジョニング エージェントが、クラウド人事アプリのテナントから AD にユーザー データをプロビジョニングします。 AD でアカウントが更新されると、Microsoft Entra Connect を介して Microsoft Entra ID と同期され、電子メール アドレスとユーザー名の属性をクラウド人事アプリのテナントに書き戻すことができます。

[Image: 図 2]

1. **人事チーム** は、クラウド人事アプリ テナントでトランザクションを実行します。
2. **Microsoft Entra プロビジョニング サービス** は、クラウド HR アプリ テナントからスケジュールされたサイクルを実行し、AD との同期のために処理する必要がある変更を識別します。
3. **Microsoft Entra プロビジョニング サービス** は、AD アカウントの作成/更新/有効化/無効化操作を含む要求ペイロードを使用して、Microsoft Entra Connect プロビジョニング エージェントを呼び出します。
4. **Microsoft Entra Connect プロビジョニング エージェント** は、サービス アカウントを使用して AD アカウント データを管理します。
5. **Microsoft Entra Connect** は差分同期を実行して、AD で更新プログラムをプルします。
6. **AD** 更新プログラムは Microsoft Entra ID と同期されます。
7. **Microsoft Entra プロビジョニング サービス** は、Microsoft Entra ID からクラウド HR アプリ テナントに電子メール属性とユーザー名を書き戻します。

### デプロイ プロジェクトを計画する

組織のニーズを考慮して、環境にユーザー プロビジョニングをデプロイするための戦略を決定します。

#### 適切な関係者を関わらせる

テクノロジ プロジェクトが失敗した場合、その原因は通常、影響、結果、および責任に対する想定の不一致です。 このような落とし穴を回避するには、 [適切な利害関係者を関与](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans) させ、利害関係者とそのプロジェクトの入力と説明責任を文書化することで、プロジェクトの利害関係者の役割が十分に理解されていることを確認します。

#### 連絡を計画する

コミュニケーションは、新しいサービスの成功に必要不可欠です。 ユーザーのエクスペリエンスについて、どのようにエクスペリエンスが変わるか、いつ変わる予定か、また問題が発生した場合にどのようにサポートを受けられるかについてユーザーに事前に伝えます。

#### パイロットを計画する

自動ユーザー プロビジョニングの初期構成は、運用環境のすべてのユーザーに拡張する前に、小規模なユーザー サブセットを含むテスト環境で行うことをお薦めします。 パイロットの実行については、[ベストプラクティス](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans#best-practices-for-a-pilot)に関する記事を参照してください。

##### パイロットのベスト プラクティス

パイロットを使用することにより、すべてのユーザーに対して機能をデプロイする前に、小規模なグループでテストすることができます。 テストの一部として、組織内の各ユース ケースが十分にテストされていることを確認します。

最初の段階では、テストを受けてフィードバックを提供できる IT、ユーザビリティ、およびその他の適切なユーザーを対象にします。 このフィードバックを使用して、ユーザーに伝える情報と指示をさらに発展させ、サポート スタッフが直面する可能性がある問題の種類に関する分析情報を提供します。

対象となるグループの範囲を広げて、ロールアウトをより大きなユーザー グループに拡大します。 グループのスコープを増やすには、 [動的メンバーシップ グループ](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership) を使用するか、ターゲット グループにユーザーを手動で追加します。

### アプリケーションの接続と管理を計画する

Microsoft Entra 管理センターを使用して、プロビジョニングをサポートするすべてのアプリケーションを表示および管理します。 [ポータルでのアプリの検索を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/configure-automatic-user-provisioning-portal)参照してください。

#### 使用するコネクタの種類を決定する

自動プロビジョニングを有効にし、構成するために実際に必要な手順は、アプリケーションによって異なります。 自動的にプロビジョニングするアプリケーションが [Microsoft Entra SaaS アプリ ギャラリー](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)に一覧表示されている場合は、 [アプリ固有の統合チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list) を選択して、事前に設定されたユーザー プロビジョニング コネクタを構成する必要があります。

そうでない場合は、次の手順に従ってください。

1. 事前に設定されたユーザー プロビジョニング コネクタの[要求を作成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing)します。 SCIM がサポートされている場合にお客様のアプリケーションを Microsoft のプラットフォームにオンボードするために、Microsoft のチームはお客様およびアプリケーション開発者と連携します。
2. アプリの [BYOA SCIM](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups) 汎用ユーザー プロビジョニング サポートを使用します。 事前統合されたプロビジョニング コネクタなしでアプリにユーザーをプロビジョニングするには、SCIM の使用が Microsoft Entra ID で必須です。
3. アプリケーションが BYOA SCIM コネクタを利用できる場合は、 [BYOA SCIM 統合チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups) を参照して、アプリケーションの BYOA SCIM コネクタを構成します。

詳細については、「[Microsoft Entra の自動ユーザー プロビジョニングで使用できるアプリケーションとシステム」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

#### アプリケーションへのアクセスを承認するための情報を収集する

自動ユーザー プロビジョニングの設定は、アプリケーションごとのプロセスです。 アプリケーションごとに、ターゲット システムのユーザー管理エンドポイントに接続するための [管理者資格情報](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/configure-automatic-user-provisioning-portal) を指定する必要があります。

この画像は、必要な管理者資格情報の 1 つのバージョンを示しています。

[Image: ユーザー アカウントのプロビジョニング設定を管理するためのプロビジョニング画面]

アプリケーションの中には、管理者のユーザー名とパスワードが必要なものもあれば、ベアラー トークンが必要なものもあります。

### ユーザーとグループのプロビジョニングを計画する

エンタープライズ アプリのユーザー プロビジョニングを有効にした場合、 [Microsoft Entra 管理センター](https://entra.microsoft.com) は属性マッピングを使用してその属性値を制御します。

#### 各 SaaS アプリの操作を決定する

各アプリケーションには、Microsoft Entra ID 内の属性にマップする必要がある一意のユーザーまたはグループの属性が含まれている場合があります。 アプリケーションは、使用可能な CRUD 操作のサブセットのみを持つことができます。

アプリケーションごとに、次の情報を文書化します。

- ターゲット システムのユーザー オブジェクトとグループ オブジェクトに対して実行される CRUD プロビジョニング操作。 たとえば、各 SaaS アプリのビジネス所有者は、可能なすべての操作を必要としない場合があります。
- ソース システムで使用可能な属性
- ターゲット システムで使用可能な属性
- システム間での属性のマッピング。

#### プロビジョニングするユーザーとグループを選択する

自動ユーザー プロビジョニングを実装する前に、アプリケーションにプロビジョニングするユーザーとグループを決定する必要があります。

- [スコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)使用して、アプリケーションにプロビジョニングされるユーザーを決定する属性ベースのルールを定義します。
- 次に、必要に応じて [ユーザーとグループの割り当てを](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal) 使用して、より多くのフィルター処理を行います。

#### ユーザーとグループの属性マッピングを定義する

自動ユーザー プロビジョニングを実装するには、アプリケーションに必要なユーザーとグループの属性を定義する必要があります。 Microsoft Entra ユーザー オブジェクトと各 SaaS アプリケーションのユーザー オブジェクトの間には、事前に構成された属性と [属性マッピング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/configure-automatic-user-provisioning-portal) のセットがあります。 すべての SaaS アプリでグループ属性が有効化されるわけではありません。

Microsoft Entra ID は、属性から属性への直接マッピング、定数値の提供、または [属性マッピングの式の記述](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)によってサポートされます。 この柔軟性により、ターゲット システムの属性に設定される内容を細かく制御できます。 [Microsoft Graph API](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/export-import-provisioning-configuration) と Graph Explorer を使用して、ユーザー プロビジョニング属性マッピングとスキーマを JSON ファイルにエクスポートし、Microsoft Entra ID にインポートし直すことができます。

詳細については、「 [Microsoft Entra ID での SaaS アプリケーションのユーザー プロビジョニング Attribute-Mappings のカスタマイズ」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)参照してください。

#### ユーザー プロビジョニングに関する特別な考慮事項

デプロイ後の問題を減らすために、次の点を考慮してください。

- ソース アプリケーションとターゲット アプリケーションの間でユーザー/グループ オブジェクトをマップするために使用される属性が回復性を備えていることを確認します。 属性が変更されても (ユーザーが社内の別の場所に移動した場合など)、ユーザーやグループが誤ってプロビジョニングされないようにする必要があります。
- アプリケーションには、ユーザー プロビジョニングが正しく機能するために満たす必要がある特定の制限や要件がある場合があります。 たとえば、Slack では、特定の属性の値が切り捨てられます。 各アプリケーションに固有の [自動ユーザー プロビジョニングのチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list) を参照してください。
- ソース システムとターゲット システム間のスキーマの整合性を確認します。 よくある問題としては、一致しない UPN やメールなどの属性があります。 たとえば、UPN は、Microsoft Entra ID では *john\_smith@contoso.com* と設定され、アプリでは *jsmith@contoso.com* になります。 詳細については、「 [ユーザーとグループのスキーマリファレンス」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)。

### テストとセキュリティを計画する

デプロイの各段階で、必ず、結果が予想どおりであることをテストし、プロビジョニング サイクルを監査するようにします。

#### テストを計画する

まず、アプリケーションのために自動ユーザー プロビジョニングを構成します。 次に、テスト ケースを実行して、ソリューションが組織の要件を満たしているかどうか確認します。

| シナリオ | 予想される結果 |
| --- | --- |
| ターゲット システムに割り当てられているグループにユーザーが追加される。 | ユーザー オブジェクトがターゲット システムでプロビジョニングされている。 ユーザーがターゲット システムにサインインして、目的のアクションを実行できる。 |
| ターゲット システムに割り当てられているグループからユーザーが削除される。 | ユーザー オブジェクトがターゲット システムでプロビジョニング解除される。ユーザーがターゲットシステムにサインインできない。 |
| Microsoft Entra ID で、ユーザー情報が任意の方法で更新される。 | 増分サイクル後、更新されたユーザー属性がターゲット システムで反映される。 |
| ユーザーがスコープ外である。 | ユーザー オブジェクトが無効化または削除される。 注: この動作は [Workdayプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions)でオーバーライドされます。 |

#### セキュリティを計画する

通常、デプロイの一環としてセキュリティ レビューが必要になります。 セキュリティ レビューが必要な場合は、サービスとしての ID の概要を示す多くの Microsoft Entra ID [のホワイトペーパー](https://www.microsoft.com/download/details.aspx?id=36391) を参照してください。

#### ロールバックを計画する

自動ユーザー プロビジョニングの実装が運用環境で期待どおりに機能しない場合は、以下のロールバック手順が、前の既知の正常な状態に戻すのに役立ちます。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)確認して、影響を受けるユーザーやグループに対して発生した不適切な操作を特定します。
2. プロビジョニング ログを使用して、影響を受けたユーザーまたはグループについて最新の既知の正常な状態を判別します。 また、ソース システム (Microsoft Entra ID または AD) も確認します。
3. アプリケーションの所有者と協力し、最新の既知の正常な状態の値を使用して、アプリケーションで直接影響を受けたユーザーやグループを更新します。

### 自動ユーザー プロビジョニング サービスをデプロイする

ソリューションの要件に合わせて手順を選択します。

#### 初回サイクルの準備をする

Microsoft Entra プロビジョニング サービスを初めて実行すると、ソース システムとターゲット システムに対する初回サイクルにより、各ターゲット システムのすべてのユーザー オブジェクトのスナップショットが作成されます。

アプリケーションに対して自動プロビジョニングを有効にする場合、初回サイクルには 20 分から数時間かかります。 この期間は、Microsoft Entra ディレクトリのサイズと、プロビジョニングの対象となるユーザーの数によって異なります。

プロビジョニング サービスは、初回サイクル後の両方のシステムの状態を保管し、後続の増分サイクルのパフォーマンスを改善します。

#### 自動ユーザー プロビジョニングの構成

[Microsoft Entra 管理センター](https://entra.microsoft.com)を使用して、それをサポートするアプリケーションの自動ユーザー アカウント プロビジョニングとプロビジョニング解除を管理します。 [「アプリケーションへの自動プロビジョニングを設定する方法](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」の手順に従ってください。

Microsoft Entra ユーザー プロビジョニング サービスは、 [Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/resources/synchronization-overview) を使用して構成および管理することもできます。

### 自動ユーザー プロビジョニングを管理する

これでデプロイが完了したので、ソリューションを管理する必要があります。

#### ユーザー プロビジョニング操作の正常性を監視する

[最初のサイクル](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)が成功すると、Microsoft Entra プロビジョニング サービスは、次のいずれかのイベントが発生するまで、各アプリケーションに固有の間隔で増分更新を無期限に実行します。

- サービスは手動で停止され、 [Microsoft Entra 管理センター](https://entra.microsoft.com)または適切な [Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/resources/synchronization-overview) コマンドを使用して、新しい初期サイクルがトリガーされます。
- 新しい初回サイクルによって、属性マッピングまたはスコープ フィルターの変更がトリガーされる。
- エラー率が高いためにプロビジョニング プロセスが検疫に移行し、4 週間より長く検疫にとどまり、自動的に無効になる。

これらのイベント、およびプロビジョニング サービスによって実行されるその他のすべてのアクティビティを確認するには、Microsoft Entra [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs?context=azure/active-directory/manage-apps/context/manage-apps-context)参照してください。

プロビジョニング サイクルにかかる時間を把握し、プロビジョニング ジョブの進行状況を監視するには、 [ユーザー プロビジョニングの状態を確認](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)します。

#### レポートから分析情報を得る

Microsoft Entra ID では、プロビジョニング ログとレポートを利用すると、組織のユーザー プロビジョニングの使用状況と運用の正常性に関する分析情報をさらに得ることができます。 ユーザーの分析情報の詳細については、「 [ユーザー プロビジョニングの状態を確認する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)」を参照してください。

管理者は、プロビジョニングの概要レポートを確認して、プロビジョニング ジョブの操作の正常性を監視する必要があります。 プロビジョニング サービスによって実行されたアクティビティはすべて、Microsoft Entra のプロビジョニング ログに記録されます。 [「チュートリアル: 自動ユーザー アカウント プロビジョニングに関するレポート」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)参照してください。

これらのレポートの所有権を引き受け、組織の要件が満たされる頻度で使用することをお勧めします。 Microsoft Entra ID では、ほとんどの監査データが 30 日間保持されます。

#### トラブルシューティング

プロビジョニング中に発生する可能性がある問題のトラブルシューティングについては、次のリンクを参照してください。

- [Microsoft Entra Gallery アプリケーションへのユーザー プロビジョニングの構成に関する問題](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/troubleshoot)
- [オンプレミスの Active Directory から Microsoft Entra ID に属性を同期してアプリケーションにプロビジョニングする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping)
- [Microsoft Entra Gallery アプリケーションへのユーザー プロビジョニングの構成中に管理者の資格情報を保存する際の問題](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)
- [Microsoft Entra Gallery アプリケーションにユーザーがプロビジョニングされていない](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-config-problem-no-users-provisioned)
- [Microsoft Entra Gallery アプリケーションにプロビジョニングされているユーザーのセットが間違っている](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)

#### 役に立つドキュメント

- [属性マッピングの式の記述](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)
- [Microsoft Entra 同期 API の概要](https://learn.microsoft.com/ja-jp/graph/api/resources/synchronization-overview)
- [スコープ外のユーザー アカウントの削除をスキップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions)
- [Microsoft Entra Connect プロビジョニング エージェント: バージョンのリリース履歴](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provisioning-agent-release-version-history)

##### リソース

- [製品に関するフィードバックを提供する](https://feedback.azure.com/d365community/forum/22920db1-ad25-ec11-b6e6-000d3a4f0789)
- [Microsoft Entra ID の新機能を最新の状態に保つ](https://azure.microsoft.com/updates/?product=active-directory)
- [Microsoft Q&A Microsoft Entra フォーラム](https://learn.microsoft.com/ja-jp/answers/tags/455/entra-id)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/plan-cloud-hr-provision"} -->
## Microsoft Entra のユーザー プロビジョニングに対するクラウド人事アプリケーションを計画する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision
- Service: entra-id / app-provisioning
- Article date: 2025-07-24
- Summary: この記事では、Workday や SuccessFactors などのクラウド人事システムを Microsoft Entra ID と統合するデプロイ プロセスについて説明します。 Microsoft Entra ID とクラウド人事システムを統合することで、完全な ID ライフサイクル管理システムが実現します。

従来、IT スタッフは、手動による社員の作成、更新、削除の方法に依存していました。 これらは、CSV ファイルのアップロードや、カスタム スクリプトなどの方法を使用して社員データを同期しています。 これらのプロビジョニング プロセスは、エラーが発生しやすく、安全でなく、管理が困難です。

従業員、ベンダー、または臨時従業員の ID ライフサイクルを管理するために、 [Microsoft Entra ユーザー プロビジョニング サービス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) は、クラウドベースの人事 (HR) アプリケーションとの統合を提供します。 アプリケーションの例としては、Workday や SuccessFactors などがあります。

Microsoft Entra ID は、この統合を利用して、次のクラウド人事アプリケーション (アプリ) プロセスを有効にします。

- **Active Directory へのユーザーのプロビジョニング:** クラウド人事アプリから選択したユーザー セットを 1 つ以上の Active Directory ドメインにプロビジョニングします。
- **クラウド専用ユーザーを Microsoft Entra ID にプロビジョニングします。** Active Directory を使用しないシナリオでは、クラウド人事アプリから Microsoft Entra ID にユーザーを直接プロビジョニングします。
- **クラウド人事アプリに書き戻します。** Microsoft Entra のメール アドレスとユーザー名属性をクラウド人事アプリに書き戻します。

次のビデオでは、人事主導のプロビジョニング統合の計画に関するガイダンスを提供します。

Note

このデプロイ計画は、Microsoft Entra のユーザー プロビジョニングを使用してクラウド人事アプリをデプロイする方法を示します。 サービスとしてのソフトウェア (SaaS) アプリに自動ユーザー プロビジョニングを展開する方法については、「 [自動ユーザー プロビジョニングの展開を計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-auto-user-provisioning)」を参照してください。

### 任意の HR システムからの API 駆動型プロビジョニング

[API 駆動型プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-concepts)では、*任意*のレコード システムの ID を Microsoft Entra ID に取り込むことができます。 *任意*の自動化ツールを使用して、レコードのシステムから従業員データを取得し、Microsoft Entra ID に取り込むことができます。 IT 管理者は、属性マッピングを使用してデータを処理および変換する方法を完全に制御できます。

### 有効な人事シナリオ

Microsoft Entra のユーザー プロビジョニング サービスを使用すると、次に示す人事ベースの ID ライフサイクル管理シナリオを自動化できます。

- **新入社員雇用:** クラウド人事アプリに従業員を追加すると、Active Directory と Microsoft Entra ID にユーザーが自動的に作成されます。 ユーザー アカウントの追加には、メール アドレスとユーザー名の属性をクラウド人事アプリに書き戻すオプションが含まれています。
- **従業員属性とプロファイルの更新:** クラウド人事アプリで名前、タイトル、マネージャーなどの従業員レコードが更新されると、Active Directory と Microsoft Entra ID でユーザー アカウントが自動的に更新されます。
- **従業員の退職:** 従業員がクラウド人事アプリで退職すると、Active Directory と Microsoft Entra ID でユーザー アカウントが自動的に無効になります。
- **従業員の再雇用:** 従業員がクラウド人事アプリで再雇用されると、古いアカウントを自動的に再アクティブ化したり、Active Directory と Microsoft Entra ID に再プロビジョニングしたりできます。

### この統合が最も適している組織

クラウド人事アプリと Microsoft Entra ユーザー プロビジョニングの統合は、次のような組織に最適です。

- クラウド人事のユーザーをプロビジョニングするための、事前構築済みのクラウドベースのソリューションを必要としている。
- クラウド人事アプリから Active Directory または Microsoft Entra に直接、ユーザーをプロビジョニングする必要がある。
- クラウド人事アプリから取得したデータを使用してユーザーをプロビジョニングする必要がある。
- 入社、異動、退職するユーザーの同期。 同期は、クラウド人事アプリで検出された変更情報のみに基づいて、1 つ以上の Active Directory フォレスト、ドメイン、OU の間で行われる。
- 電子メールに Microsoft 365 を使用する。

### Learn

ユーザー プロビジョニングによって、継続的な ID ガバナンスの基盤が作成されます。 これにより、正規の ID データに依存するビジネス プロセスの品質が向上します。

#### Terms

この記事では、次の用語を使用しています。

- **ソース システム**: Microsoft Entra ID がプロビジョニングするユーザーのリポジトリ。 例として、Workday や SuccessFactors などのクラウド人事アプリがあります。
- **ターゲット システム**: Microsoft Entra ID がプロビジョニングするユーザーのリポジトリ。 例として、Active Directory、Microsoft Entra ID、Microsoft 365、その他の SaaS アプリがあります。
- **参加者 -Movers-Leavers プロセス**: クラウド人事アプリをレコードのシステムとして使用して、新入社員、異動、退職に使用される用語。 プロセスが完了するのは、サービスによって必要な属性がターゲット システムに正常にプロビジョニングされた時点です。

#### 主な利点

ここで説明する人事ベースの IT プロビジョニング機能は、次に挙げるような大きなメリットをビジネスにもたらします。

- **生産性の向上:** ユーザー アカウントと Microsoft 365 ライセンスの割り当てを自動化し、キー グループへのアクセスを提供できるようになりました。 割り当ての自動化により、新しい社員は各自の業務ツールにすぐにアクセスできるため、生産性が向上します。
- **リスクの管理:** 従業員の状態またはグループ メンバーシップに基づいて変更を自動化し、セキュリティを強化します。 この自動化により、ユーザー ID とキー アプリへのアクセスが自動的に更新されます。 たとえば、ユーザーが異動したり退職したりしたときに、人事アプリの更新が自動的に流れます。
- **コンプライアンスとガバナンスに対処する:** Microsoft Entra ID は、ソース システムとターゲット システムの両方のアプリによって実行されるユーザー プロビジョニング要求のネイティブ プロビジョニング ログをサポートします。 監査によって、誰がアプリにアクセスできるかを 1 つの画面から追跡できます。
- **コストの管理:** 自動プロビジョニングでは、手動プロビジョニングに関連する非効率性と人的エラーを回避することで、コストが削減されます。 従来の陳腐化したプラットフォームを使用して、長らく構築されてきた、カスタム開発のユーザー プロビジョニング ソリューションを不要にします。

#### Licensing

Microsoft Entra ユーザー プロビジョニング統合にクラウド HR アプリを構成するには、有効な [Microsoft Entra ID P1 または P2 ライセンス](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing) と、Workday や SuccessFactors などのクラウド HR アプリのライセンスが必要です。

また、クラウド人事アプリをソースとして Active Directory または Microsoft Entra にプロビジョニングされるすべてのユーザーに対して、Microsoft Entra ID 以上の有効なサブスクリプション ライセンスが必要です。

プロビジョニング プロセスで [ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows) やその他の Microsoft Entra ID ガバナンス機能を使用するには、 [Microsoft Entra ID ガバナンス ライセンス](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)が必要です。

#### Prerequisites

- Connect プロビジョニング エージェントを構成するための[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)ロール。
- プロビジョニング アプリを構成するための[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)ロール。
- クラウド人事アプリのテストおよび運用インスタンス。
- クラウド人事アプリの管理者権限。システム統合ユーザーを作成したり、テスト目的でテスト用社員データを変更したりするために必要です。
- Active Directory へのユーザー プロビジョニングの場合、Microsoft Entra Connect プロビジョニング エージェントをホストするために、Windows Server 2016 以降を実行しているサーバーが必要です。 このサーバーは、Active Directory 管理層モデルに基づいた階層 0 のサーバーである必要があります。
- Active Directory と Microsoft Entra ID の間でユーザーを同期するための Microsoft [Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect)。

#### トレーニング リソース

| **Resources** | **リンクと説明** |
| --- | --- |
| Videos | [Microsoft Entra ID でのユーザー プロビジョニングとは](https://youtu.be/_ZjARPpI6NI) |
|  | [Microsoft Entra ID でユーザー プロビジョニングを展開する方法](https://youtu.be/pKzyts6kfrw) |
| Tutorials | [SaaS アプリと Microsoft Entra ID を統合する方法に関するチュートリアルの一覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list) |
|  | [チュートリアル: Workday を使用して自動ユーザー プロビジョニングを構成する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial) |
|  | [チュートリアル: SAP SuccessFactors を使用して自動ユーザー プロビジョニングを構成する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial) |
| FAQ | [自動ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning#what-applications-and-systems-can-i-use-with-azure-ad-automatic-user-provisioning) |
|  | [Workday から Microsoft Entra ID へのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#frequently-asked-questions-faq) |

#### ソリューション アーキテクチャ

次の例では、一般的なハイブリッド環境に向けた、エンド ツー エンドのユーザー プロビジョニング ソリューションのアーキテクチャについて説明し、次のものが含まれます。

- **正規の人事データ フロー - クラウド人事アプリから Active Directory へ。** このフローでは、クラウド人事アプリのテナントで人事イベント (入社/異動/退職プロセス) が開始されます。 Microsoft Entra プロビジョニング サービスおよび Microsoft Entra Connect プロビジョニング エージェントが、クラウド人事アプリのテナントから Active Directory にユーザー データをプロビジョニングします。 イベントによっては、Active Directory での作成、更新、有効化、および無効化操作が発生する可能性があります。
- **Microsoft Entra と同期し、オンプレミスの Active Directory からクラウド人事アプリに電子メールとユーザー名を書き戻します。** Active Directory でアカウントが更新されると、Microsoft Entra Connect 経由で Microsoft Entra ID と同期されます。 メール アドレスやユーザー名の属性は、クラウド人事アプリ テナントに書き戻すことができます。

[Image: ワークフロー図]

##### プロビジョニング プロセスの説明

次の主要な手順を図に示します。

1. **人事チーム** は、クラウド人事アプリ テナントでトランザクションを実行します。
2. **Microsoft Entra プロビジョニング サービス** は、クラウド HR アプリ テナントからスケジュールされたサイクルを実行し、Active Directory と同期するためのプロセスの変更を識別します。
3. **Microsoft Entra プロビジョニング サービス** は、Active Directory アカウントの作成、更新、有効化、無効化操作を含む要求ペイロードを使用して、Microsoft Entra Connect プロビジョニング エージェントを呼び出します。
4. **Microsoft Entra Connect プロビジョニング エージェント** は、サービス アカウントを使用して Active Directory アカウント データを管理します。
5. **Microsoft Entra Connect** は、差分 [同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis) を実行して Active Directory で更新プログラムをプルします。
6. **Active Directory の** 更新プログラムは、Microsoft Entra ID と同期されます。
7. **Microsoft Entra プロビジョニング サービス** は、Microsoft Entra ID からクラウド HR アプリ テナントに電子メール属性とユーザー名を書き戻します。

### デプロイ プロジェクトを計画する

お客様の環境でこのデプロイの戦略を決定するときは、お客様の組織のニーズを考慮してください。

#### 適切な関係者を関わらせる

テクノロジ プロジェクトが失敗した場合、その原因は通常、影響、結果、および責任に対する想定の不一致です。 このような落とし穴を回避するには、 [適切な利害関係者に関与していることを確認します](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans)。 また、プロジェクトの利害関係者のロールを十分に把握していることを確認します。 利害関係者とそのプロジェクト入力と責務を文書化します。

既存の人事ビジネス プロセスや、社員の ID および職務データの処理要件について意見できる、人事部門の代表者を含めます。

#### 連絡を計画する

コミュニケーションは、新しいサービスの成功に必要不可欠です。 エクスペリエンスの変化について、ユーザーに事前に通知します。 問題が発生した場合にサポートを受ける方法について説明します。

#### パイロットを計画する

人事ビジネス プロセスと ID ワークフローをクラウド人事アプリからターゲット システムに統合するには、ソリューションを運用環境にデプロイする前に、大量のデータ検証、データ変換、データ クレンジング、そして徹底的なテストが必要です。

運用環境のすべてのユーザーにスケールする前に、 [パイロット環境](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans#best-practices-for-a-pilot) で初期構成を実行します。

#### HR データ フローと属性マッピングを計画する

適切な HR レコードが Microsoft Entra ID (Entra ID) とオンプレミスの Active Directory (AD) のユーザーに確実にマップされるように、HR および IT の各チームと協力してデータの一貫性を確保し、データ クレンジング タスクを計画します。 作業を開始するためのベスト プラクティスの一覧を次に示します。

1. **一致する識別子の存在と一意性:** プロビジョニング サービスでは、一致する属性を使用して、人事システムのユーザー レコードを一意に識別し、AD/Entra ID の対応するユーザー アカウントとリンクします。 既定の一致する属性は、従業員 ID に基づいています。 完全同期を開始する前に、従業員 ID の値が Entra ID (クラウド専用ユーザーの場合) およびオンプレミスの AD (ハイブリッド ユーザーの場合) に設定されていること、ユーザーを一意に識別することを確認します。 完全同期を開始する前に、Active Directory の [employeeId 属性のインデックス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/index-employeeid-attribute-entra-id-to-active-directory) を作成するを参照してください。
2. **関連しなくなった HR レコードをスキップするには、スコープ フィルターを使用** します。人事システムには、おそらく1970年代まで遡る数年間の雇用データがあります。 一方、IT チームが関心を持つのは、現在アクティブな従業員の一覧と、稼働後に発生する退職レコードのみである可能性があります。 IT チームの観点から関連性がなくなった HR レコードを除外するには、HR チームと協力して、Microsoft Entra プロビジョニングのスコープ フィルターで使用できる HR レコードにフラグを追加します。
3. **ユーザー名の特殊文字の処理を計画します。** ユーザーの一意の `userPrincipalName` を作成するには、ワーカーの名と姓を使用するのが一般的です。 `userPrincipalName` でアクセント記号は使用できません。使用できる文字は A - Z、a - z、0 - 9、 ' です。 - \_ ! #^~。 [関数 NormalizeDiacritics](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#normalizediacritics) を使用してアクセント文字を処理し、適切な`userPrincipalName`を構築します。
4. **長い文字列の処理を計画します。** HR データに、Entra ID/オンプレミス AD 属性の設定に使用する HR フィールドに関連付けられている長い文字列値があるかどうかを確認します。 すべての Entra ID 属性には、最大文字列長があります。 Entra ID 属性にマップされた HR フィールドの値にさらに多くの文字が含まれている場合、属性の更新が失敗する可能性があります。 1 つのオプションは、属性マッピングを確認し、HR システムで長い文字列値を切り捨てたり更新したりする可能性があるかどうかを確認することです。 オプションでない場合は、 [Mid](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#mid) などの関数を使用して長い文字列を切り捨てるか、 [Switch](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#switch) などの関数を使用して長い値を短い値/省略形にマップすることができます。
5. **必須属性の null/空の値を処理します。** Entra ID/オンプレミス AD でアカウントを作成するときは、 `firstName`、 `lastName`、 `CN`、 `UPN` などの特定の属性を設定する必要があります。 そのような属性にマップされた対応する HR フィールドが null の場合、ユーザー作成操作は失敗します。 たとえば、AD の `CN` 属性を "表示名" にマップした場合、"表示名" がすべてのユーザーに対して設定されていない限り、エラーが発生します。 1 つのオプションは、このような必須の属性マッピングを確認し、対応するフィールドが HR に入力されていることを確認することです。 式マッピングで null 値をチェックするオプションを検討することもできます。 たとえば、表示名が空の場合は、名と姓を連結して表示名を作ります。

### クラウド人事プロビジョニング コネクタ アプリの選択

クラウド人事アプリから Active Directory への Microsoft Entra プロビジョニングを容易にするために、Microsoft Entra アプリ ギャラリーから複数のプロビジョニング コネクタ アプリを追加できます。

- **クラウド人事アプリから Active Directory へのユーザー プロビジョニング**: このプロビジョニング コネクタ アプリは、クラウド HR アプリから単一の Active Directory ドメインへのユーザー アカウント プロビジョニングを容易にします。 複数のドメインがある場合は、プロビジョニング先にする必要がある Active Directory ドメインごとに 1 つ、Microsoft Entra アプリ ギャラリーからこのアプリのインスタンスを追加できます。
- **クラウド人事アプリから Microsoft Entra へのユーザー プロビジョニング**: Microsoft Entra Connect は、オンプレミスの Active Directory ユーザーを Microsoft Entra ID に同期するために使用されるツールです。 クラウド人事アプリから Microsoft Entra へのユーザー プロビジョニングは、クラウドのみのユーザーをクラウド人事アプリから 1 つの Microsoft Entra テナントにプロビジョニングするために使用するコネクタです。
- **クラウド人事アプリの書き戻し**: このプロビジョニング コネクタ アプリは、Microsoft Entra ID からクラウド HR アプリへのユーザーの電子メール アドレスの書き戻しを容易にします。

たとえば、次の図は、Microsoft Entra アプリ ギャラリーで入手できる Workday コネクタ アプリの一覧です。

[Image: Microsoft Entra Admin Center アプリ ギャラリー]

#### 意思決定フロー チャート

以下の決定フロー チャートを使用して、お客様のシナリオに関連しているクラウド人事プロビジョニング アプリを特定します。

[Image: 意思決定フロー チャート]

### Microsoft Entra Connect プロビジョニング エージェントのデプロイ トポロジを設計する

クラウド人事アプリと Active Directory 間のプロビジョニング統合には、次の 4 つのコンポーネントが必要です。

- クラウド人事アプリのテナント
- プロビジョニング コネクタ アプリ
- Microsoft Entra Connect プロビジョニング エージェント
- Active Directory ドメイン

Microsoft Entra Connect プロビジョニング エージェントのデプロイ トポロジは、統合を計画しているクラウド人事アプリのテナントおよび Active Directory 子ドメインの数によって異なってきます。 複数の Active Directory ドメインがある場合は、Active Directory ドメインが連続しているか [不整合](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/plan/disjoint-namespace)であるかによって異なります。

決定に基づいて、次のいずれかのデプロイ シナリオを選択します。

- 1 つのクラウド人事アプリのテナント -&gt; 信頼されたフォレスト内の 1 つまたは複数の Active Directory 子ドメインがターゲット
- 1 つのクラウド人事アプリのテナント -&gt; 分離された Active Directory フォレスト内の複数の子ドメインがターゲット

#### 1 つのクラウド人事アプリのテナント -&gt; 信頼されたフォレスト内の 1 つまたは複数の Active Directory 子ドメインがターゲット

次の運用環境の構成をお勧めします。

| Requirement | Recommendation |
| --- | --- |
| デプロイする Microsoft Entra Connect プロビジョニング エージェントの数。 | 2 つ (高可用性とフェールオーバー)。 |
| 構成するプロビジョニング コネクタ アプリの数。 | 1 つの子ドメインあたり 1 つのアプリ。 |
| Microsoft Entra Connect プロビジョニング エージェントのサーバー ホスト。 | geo 配置された Active Directory ドメイン コントローラーへの見通し線を備えた Windows Server 2016。 Microsoft Entra Connect サービスと共存できます。 |

[Image: オンプレミス エージェントへのフロー]

#### 1 つのクラウド人事アプリのテナント -&gt; 分離された Active Directory フォレスト内の複数の子ドメインがターゲット

このシナリオでは、クラウド人事アプリから分離された Active Directory フォレスト内のドメインにユーザーをプロビジョニングします。

次の運用環境の構成をお勧めします。

| Requirement | Recommendation |
| --- | --- |
| オンプレミスにデプロイする Microsoft Entra Connect プロビジョニング エージェントの数 | 分離された Active Directory フォレストあたり 2 つ。 |
| 構成するプロビジョニング コネクタ アプリの数 | 1 つの子ドメインあたり 1 つのアプリ。 |
| Microsoft Entra Connect プロビジョニング エージェントのサーバー ホスト。 | geo 配置された Active Directory ドメイン コントローラーへの見通し線を備えた Windows Server 2016。 Microsoft Entra Connect サービスと共存できます。 |

[Image: 1 つのクラウド人事アプリのテナントと分離された Active Directory フォレスト]

#### Microsoft Entra Connect プロビジョニング エージェントの要件

クラウド人事アプリから Active Directory へのユーザー プロビジョニング ソリューションを使用するには、1 つ以上の Microsoft Entra Connect プロビジョニング エージェントのデプロイが必要です。 これらのエージェントは、Windows Server 2016 以上を実行するサーバーにデプロイする必要があります。 サーバーは、4 GB 以上の RAM と .NET 4.7.1 以降のランタイムを備えている必要があります。 ホスト サーバーが、ターゲット Active Directory ドメインへのネットワーク アクセスを持っていることを確認します。

オンプレミス環境を準備するために、Microsoft Entra Connect プロビジョニング エージェント構成ウィザードは、エージェントを Microsoft Entra テナントに登録し、 [ポートを開き](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-connectors)、 [URL へのアクセスを許可](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-connectors)し、 [送信 HTTPS プロキシ構成](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#how-do-i-configure-the-provisioning-agent-to-use-a-proxy-server-for-outbound-http-communication)をサポートします。

プロビジョニング エージェントは、Active Directory ドメインと通信するように [グローバル管理サービス アカウント (GMSA)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-prerequisites#group-managed-service-accounts) を構成します。

プロビジョニング要求を処理する必要のあるドメイン コント ローラーを選択できます。 地理的に分散されたドメイン コント ローラーが複数ある場合は、優先ドメイン コント ローラーと同じサイトにプロビジョニング エージェントをインストールします。 この配置により、エンド ツー エンドのソリューションの信頼性とパフォーマンスが向上します。

高可用性については、複数の Microsoft Entra Connect プロビジョニング エージェントをデプロイできます。 エージェントを登録して、同じオンプレミスの Active Directory ドメインのセットを処理します。

### HR プロビジョニング アプリのデプロイ トポロジを設計する

受信ユーザー プロビジョニング構成にかかわる Active Directory ドメイン数に応じて、次のいずれかのデプロイ トポロジを検討できます。 各トポロジ図では、デプロイ シナリオの例を使用して、構成の側面を強調しています。 実際のデプロイの要件によく似た例を使用して、ニーズを満たす構成を判断します。

#### デプロイ トポロジ 1: クラウド人事から 1 つのオンプレミスの Active Directory ドメインにすべてのユーザーをプロビジョニングする単一アプリ

デプロイ トポロジ 1 は、最も一般的なデプロイ トポロジです。 クラウド HR からすべてのユーザーを 1 つの AD ドメインにプロビジョニングする必要があり、すべてのユーザーに同じプロビジョニング ルールが適用される場合は、このトポロジを使用します。

[Image: Cloud HR から単一 AD ドメインにユーザーをプロビジョニングする 1 つのアプリのスクリーンショット]

**重要な構成の側面**

- 高可用性とフェールオーバーのために、2 つのプロビジョニング エージェント ノードを設定します。
- [プロビジョニング エージェント構成ウィザードを](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-install#install-the-agent)使用して、AD ドメインを Microsoft Entra テナントに登録します。
- プロビジョニング アプリを構成するときに、登録されているドメインのドロップダウンから AD ドメインを選択します。
- スコープ フィルターを使用している場合は、誤ってアカウントが非アクティブ化されないように、 [スコープ外の削除フラグ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions) を構成します。

#### デプロイ トポロジ 2: クラウド人事から 1 つのオンプレミスの Active Directory ドメインに個別のユーザー セットをプロビジョニングする個別のアプリ

このトポロジでは、ユーザーの種類 (従業員または請負業者)、ユーザーの所在地、またはユーザーの事業単位に基づいて、属性マッピングとプロビジョニング ロジックの異なるビジネス要件がサポートされます。 また、このトポロジを使用して、部門または国/リージョンに基づいて受信ユーザー プロビジョニングの管理と保守を委任することもできます。

[Image: Cloud HR から単一 AD ドメインにユーザーをプロビジョニングする個別のアプリのスクリーンショット]

**重要な構成の側面**

- 高可用性とフェールオーバーのために、2 つのプロビジョニング エージェント ノードを設定します。
- プロビジョニングする個別のユーザー セットごとに HR2AD プロビジョニング アプリを作成します。
- プロビジョニング アプリで [スコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) 使用して、各アプリを処理するユーザーを定義します。
- マネージャーの参照を個別のユーザー セット間で解決する必要があるシナリオでは、個別の HR2AD プロビジョニング アプリを作成します。 たとえば、請負業者は従業員であるマネージャーに報告します。 別のアプリを使用して *、マネージャー* 属性のみを更新します。 このアプリのスコープをすべてのユーザーに設定します。
- 誤ってアカウントが非アクティブ化されないように、 [スコープ外の削除フラグ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions) を構成します。

Note

AD ドメインがテストされておらず、AD で TEST OU コンテナーを使用している場合は、このトポロジを使用して、2 つの個別のアプリ *HR2AD (Prod)* と *HR2AD (テスト*) を作成できます。 *HR2AD (テスト)* アプリを使用して、属性マッピングの変更を *HR2AD (Prod)* アプリに昇格させる前にテストします。

#### デプロイ トポロジ 3: クラウド人事から複数のオンプレミスの Active Directory ドメインに個別のユーザー セットをプロビジョニングする個別のアプリ (ドメイン間の可視性なし)

トポロジ 3 を使用して、同じフォレストに属する複数の独立した子 AD ドメインを管理します。 マネージャーは常にユーザーと同じドメインに存在するようにしてください。 また、 *userPrincipalName*、 *samAccountName*、 *メール* などの属性の一意の ID 生成規則で、フォレスト全体の参照が必要ないことを確認します。 トポロジ 3 には、各プロビジョニング ジョブの管理をドメイン境界ごとに委任することができる柔軟性も備わっています。

たとえば、この図では、プロビジョニング アプリは、北米 (NA)、ヨーロッパ、中東およびアフリカ (EMEA)、アジア太平洋 (APAC) の各リージョンに設定されています。 場所に応じて、ユーザーはそれぞれの AD ドメインにプロビジョニングされます。 *EMEA 管理者が EMEA* リージョンに属するユーザーのプロビジョニング構成を個別に管理できるように、プロビジョニング アプリの委任された管理が可能です。

[Image: Cloud HR から複数の AD ドメインにユーザーをプロビジョニングする個別のアプリのスクリーンショット]

**重要な構成の側面**

- 高可用性とフェールオーバーのために、2 つのプロビジョニング エージェント ノードを設定します。
- [プロビジョニング エージェント構成ウィザードを](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-install#install-the-agent)使用して、すべての子 AD ドメインを Microsoft Entra テナントに登録します。
- ターゲット ドメインごとに個別の HR2AD プロビジョニング アプリを作成します。
- プロビジョニング アプリを構成するときに、使用可能な AD ドメインのドロップダウンからそれぞれの子 AD ドメインを選択します。
- プロビジョニング アプリで [スコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) 使用して、各アプリが処理するユーザーを定義します。
- 誤ってアカウントが非アクティブ化されないように、 [スコープ外の削除フラグ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions) を構成します。

#### デプロイ トポロジ 4: クラウド人事から複数のオンプレミスの Active Directory ドメインに個別のユーザー セットをプロビジョニングする個別のアプリ (ドメイン間の可視性あり)

トポロジ 4 を使用して、同じフォレストに属する複数の独立した子 AD ドメインを管理します。 ユーザーのマネージャーが別のドメインに存在する場合があります。 また、 *userPrincipalName*、 *samAccountName* 、 *メール* などの属性に対する一意の ID 生成規則には、フォレスト全体の検索が必要です。

たとえば、この図では、プロビジョニング アプリは、北米 (NA)、ヨーロッパ、中東およびアフリカ (EMEA)、アジア太平洋 (APAC) の各リージョンに設定されています。 場所に応じて、ユーザーはそれぞれの AD ドメインにプロビジョニングされます。 ドメイン間マネージャーの参照とフォレスト全体の検索は、プロビジョニング エージェントで参照の追跡を有効にすることで処理されます。

[Image: クロス ドメイン サポートを使用してクラウド HR から複数の AD ドメインにユーザーをプロビジョニングする別のアプリのスクリーンショット]

**重要な構成の側面**

- 高可用性とフェールオーバーのために、2 つのプロビジョニング エージェント ノードを設定します。
- プロビジョニング エージェントで[追跡を設定](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-manage-registry-options#configure-referral-chasing)します。
- [プロビジョニング エージェント構成ウィザードを](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-install#install-the-agent)使用して、親 AD ドメインとすべての子 AD ドメインを Microsoft Entra テナントに登録します。
- ターゲット ドメインごとに個別の HR2AD プロビジョニング アプリを作成します。
- それぞれのプロビジョニング アプリを構成するときに、使用可能な AD ドメインのドロップダウンから親 AD ドメインを選択します。 親ドメインを選択すると、 *userPrincipalName*、 *samAccountName* 、 *mail* などの属性の一意の値を生成しながら、フォレスト全体の参照が保証されます。
- *parentDistinguishedName* と式マッピングを使用して、適切な子ドメインと OU コンテナーにユーザーを動的に作成します。
- プロビジョニング アプリで [スコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) 使用して、各アプリが処理するユーザーを定義します。
- クロスドメイン マネージャーの参照を解決するには、 *マネージャー* 属性のみを更新するための個別の HR2AD プロビジョニング アプリを作成します。 このアプリのスコープをすべてのユーザーに設定します。
- 誤ってアカウントが非アクティブ化されないように、 [スコープ外の削除フラグ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions) を構成します。

#### デプロイ トポロジ 5: 1 つのアプリで、クラウド HR からすべてのユーザーを複数のオンプレミスの Active Directory ドメインにプロビジョニングする (ドメイン間の可視性あり)

1 つのプロビジョニング アプリを使用して、すべての親と子 AD ドメインに属するユーザーを管理する場合は、このトポロジを使用します。 プロビジョニング ルールがすべてのドメインで一貫性があり、プロビジョニング ジョブの委任管理の要件がない場合は、このトポロジをお勧めします。 このトポロジは、ドメイン間マネージャーの参照の解決をサポートしており、フォレスト全体の一意性の確認を実行できます。

たとえば、この図では、1 つのプロビジョニング アプリで、北米 (NA)、ヨーロッパ、中東およびアフリカ (EMEA)、アジア太平洋 (APAC) のリージョン別にグループ化された 3 つの異なる子ドメインに存在するユーザーを管理しています。 *parentDistinguishedName* の属性マッピングは、適切な子ドメインにユーザーを動的に作成するために使用されます。 ドメイン間マネージャーの参照とフォレスト全体の検索は、プロビジョニング エージェントで参照の追跡を有効にすることで処理されます。

[Image: クロス ドメイン サポートを使用してクラウド HR から複数の AD ドメインにユーザーをプロビジョニングする 1 つのアプリのスクリーンショット]

**重要な構成の側面**

- 高可用性とフェールオーバーのために、2 つのプロビジョニング エージェント ノードを設定します。
- プロビジョニング エージェントで[追跡を設定](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-manage-registry-options#configure-referral-chasing)します。
- [プロビジョニング エージェント構成ウィザードを](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-install#install-the-agent)使用して、親 AD ドメインとすべての子 AD ドメインを Microsoft Entra テナントに登録します。
- フォレスト全体に対して 1 つの HR2AD プロビジョニング アプリを作成します。
- プロビジョニング アプリを構成するときに、使用可能な AD ドメインのドロップダウンから親 AD ドメインを選択します。 親ドメインを選択すると、 *userPrincipalName*、 *samAccountName* 、 *mail* などの属性の一意の値を生成しながら、フォレスト全体の参照が保証されます。
- *parentDistinguishedName* と式マッピングを使用して、適切な子ドメインと OU コンテナーにユーザーを動的に作成します。
- スコープ フィルターを使用している場合は、誤ってアカウントが非アクティブ化されないように、 [スコープ外の削除フラグ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions) を構成します。

#### デプロイ トポロジ 6: 個別のアプリで、クラウド HR から個別のユーザーを接続解除されたオンプレミスの Active Directory フォレストにプロビジョニングする

IT インフラストラクチャが AD フォレストを接続解除または分離し、事業所属に基づいてユーザーを別のフォレストにプロビジョニングする必要がある場合は、このトポロジを使用します。 たとえば、子会社 *Contoso* に勤務するユーザーは *contoso.com* ドメインにプロビジョニングする必要があります。一方、子会社 *Fabrikam* に勤務するユーザーは *、fabrikam.com* ドメインにプロビジョニングする必要があります。

[Image: クラウド HR から切断された AD フォレストにユーザーをプロビジョニングする別のアプリのスクリーンショット]

**重要な構成の側面**

- 高可用性とフェールオーバーのために、フォレストごとに 1 つずつ、2 つの異なるプロビジョニング エージェント セットを設定します。
- フォレストごとに 1 つずつ、2 つの異なるプロビジョニング アプリを作成します。
- フォレスト内のクロスドメイン参照を解決する必要がある場合は、プロビジョニング エージェントで[リファラーチェイシング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-manage-registry-options#configure-referral-chasing)を有効にします。
- 接続解除されたフォレストごとに個別の HR2AD プロビジョニング アプリを作成します。
- それぞれのプロビジョニング アプリを構成するときに、使用可能な AD ドメイン名のドロップダウンから適切な親 AD ドメインを選択します。
- 誤ってアカウントが非アクティブ化されないように、 [スコープ外の削除フラグ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions) を構成します。

#### デプロイ トポロジ 7: 個別のアプリで、複数のクラウド HR から個別のユーザーを接続解除されたオンプレミスの Active Directory フォレストにプロビジョニングする

大規模な組織では、複数の人事システムを使用することも珍しくありません。 企業の M&A (合併と買収) のシナリオでは、オンプレミスの Active Directory を複数の人事ソースに接続する必要がある場合があります。 複数の人事ソースを持ち、これらの人事ソースから同じまたは異なるオンプレミスの Active Directory ドメインに ID データを送る場合は、このトポロジをお勧めします。

[Image: 複数のクラウド HR から切断された AD フォレストにユーザーをプロビジョニングする個別のアプリのスクリーンショット]

**重要な構成の側面**

- 高可用性とフェールオーバーのために、フォレストごとに 1 つずつ、2 つの異なるプロビジョニング エージェント セットを設定します。
- フォレスト内のクロスドメイン参照を解決する必要がある場合は、プロビジョニング エージェントで[リファラーチェイシング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-manage-registry-options#configure-referral-chasing)を有効にします。
- 各人事システムとオンプレミスの Active Directory の組み合わせごとに個別の HR2AD プロビジョニング アプリを作成します。
- それぞれのプロビジョニング アプリを構成するときに、使用可能な AD ドメイン名のドロップダウンから適切な親 AD ドメインを選択します。
- 誤ってアカウントが非アクティブ化されないように、 [スコープ外の削除フラグ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions) を構成します。

### スコープ フィルターと属性マッピングの計画

クラウド HR アプリから Active Directory または Microsoft Entra ID へのプロビジョニングを有効にすると、Microsoft Entra 管理センターは属性マッピングを通じて属性値を制御します。

#### スコープ フィルターの定義

[スコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)使用して、クラウド人事アプリから Active Directory または Microsoft Entra ID にプロビジョニングする必要があるユーザーを決定する属性ベースのルールを定義します。

入社プロセスを開始するときに、次の要件を収集します。

- クラウド人事アプリを社員と臨時社員両方のオンボードに使用しているか。
- クラウド人事アプリから Microsoft Entra へのユーザー プロビジョニングを使用して、社員と臨時社員の両方を管理することを計画しているか。
- クラウド人事アプリのユーザーの一部のみを対象に、クラウド人事アプリから Microsoft Entra へのユーザー プロビジョニングを実行することを計画しているか。 例として、社員のみが考えられます。

要件に応じて、属性マッピングを構成するときに、スコープ フィルターを使用して、Active Directoryへのプロビジョニングのスコープに含めるクラウド人事アプリ内のユーザーのセットを選択できます。 一般的に使用されるスコープ フィルターの詳細については、クラウド人事アプリのチュートリアルを参照してください。

#### 一致属性の決定

プロビジョニングでは、ソース システムとターゲット システムの間で既存のアカウントを一致させることができます。 クラウド人事アプリを Microsoft Entra プロビジョニング サービスと統合する場合は、 [属性マッピングを構成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/configure-automatic-user-provisioning-portal#mappings) して、クラウド人事アプリから Active Directory または Microsoft Entra ID にフローするユーザー データを決定できます。

入社プロセスを開始するときに、次の要件を収集します。

- 各ユーザーの識別に使われる、このクラウド人事アプリでの一意 ID は何か。
- ID のライフサイクルの観点から、再雇用をどのように処理するか。 再雇用時に古い社員 ID が保持されるか。
- 将来の日付の雇用を処理し、対象者の Active Directory アカウントを事前に作成するか。
- ID のライフサイクルの観点から、社員から臨時社員への (またはその逆の) 転換をどのように処理するか。
- 変換されたユーザーが、古い Active Directory アカウントを保持するか、それとも新しいアカウントを取得するか。

要件に応じて、Microsoft Entra ID では、定数値を指定するか、属性マッピングの式を記述することで、 [属性から属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)への直接マッピングがサポートされます。 この柔軟性により、ターゲット アプリの属性に設定される内容を完全に制御できます。 [Microsoft Graph API](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/export-import-provisioning-configuration) と Graph Explorer を使用して、ユーザー プロビジョニング属性マッピングとスキーマを JSON ファイルにエクスポートし、Microsoft Entra ID にインポートし直すことができます。

既定では、一意の従業員 ID を表すクラウド人事アプリの属性は、 *Active Directory の一意の属性にマップされた* 一致する属性として使用されます。たとえば、Workday アプリのシナリオでは、 **Workday** **WorkerID** 属性が Active Directory **employeeID** 属性にマップされます。

複数の一致属性を設定し、一致の優先順位を割り当てることができます。 一致の優先順位に従って評価が行われます。 1 件でも一致が見つかると、一致する属性の評価はそれ以上行われません。

既存 [の属性マッピングの](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes#understanding-attribute-mapping-types)変更や削除など、既定の属性マッピングをカスタマイズすることもできます。 また、ビジネスのニーズに合わせて新しい属性マッピングを作成できます。 詳細については、マップするカスタム属性の一覧については、クラウド人事アプリのチュートリアル ( [Workday](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#managing-your-configuration) など) を参照してください。

#### ユーザー アカウントの状態の決定

既定では、プロビジョニング コネクタ アプリは、人事のユーザー プロファイルの状態をユーザー アカウントの状態にマッピングします。 状態は、ユーザー アカウントを有効または無効にするかどうかを決定するために使用されます。

入社および退職プロセスを開始するときに、次の要件を収集します。

| Process | Requirements |
| --- | --- |
| **Joiners** | ID のライフサイクルの観点から、再雇用をどのように処理するか。 再雇用時に古い社員 ID が保持されるか。 |
|  | 将来の日付の雇用を処理し、対象者の Active Directory アカウントを事前に作成するか。 これらのアカウントが有効または無効どちらの状態で作成されるか。 |
|  | ID のライフサイクルの観点から、社員から臨時社員への (またはその逆の) 転換をどのように処理するか。 |
|  | 変換されたユーザーが、古い Active Directory アカウントを保持するか、それとも新しいアカウントを取得するか。 |
| **Leavers** | Active Directory において、社員と臨時社員で退職の処理方法が異なっているか。 |
|  | ユーザーの退職の処理にあたり、どのような発効日が考慮されるか。 |
|  | 社員および臨時社員の変換は、既存の Active Directory アカウントにどのように影響するか。 |
|  | Active Directory で取り消し 操作をどのように処理するか。 将来の日付の雇用が Active Directory で作成される場合、入社プロセスの中で取り消し操作を処理する必要があります。 |

要件に応じて、 [Microsoft Entra 式](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data) を使用してマッピング ロジックをカスタマイズし、データ ポイントの組み合わせに基づいて Active Directory アカウントを有効または無効にできます。

#### クラウド人事アプリを Active Directory ユーザー属性にマップする

クラウド人事アプリごとに、クラウド人事アプリから Active Directory への既定のマッピングが存在します。

入社、異動、退職プロセスを開始するときに、次の要件を収集します。

| Process | Requirements |
| --- | --- |
| **Joiners** | Active Directory アカウントの作成プロセスは手動、自動、一部自動のどれか。 |
|  | クラウド人事アプリから Active Directory にカスタム属性を引き継ぐことを計画しているか。 |
| **Movers** | クラウド人事アプリで異動操作が発生するたびに、どの属性を処理するか。 |
|  | ユーザーの更新時に特定の属性検証を実行するか。 はいの場合は、詳細を指定します。 |
| **Leavers** | Active Directory において、社員と臨時社員で退職の処理方法が異なっているか。 |
|  | ユーザーの退職の処理にあたり、どのような発効日が考慮されるか。 |
|  | 社員および臨時社員の変換は、既存の Active Directory アカウントにどのように影響するか。 |

要件に応じて、統合の目標を満たすようにマッピングを変更できます。 詳細については、マップするカスタム属性の一覧については、特定のクラウド人事アプリのチュートリアル ( [Workday](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#part-4-configure-attribute-mappings) など) を参照してください。

#### 一意の属性値の生成

CN、samAccountName、UPN などの属性には、一意の制約があります。 入社プロセスを開始する際に、一意の属性値を生成する必要がある場合があります。

Microsoft Entra ID 関数 [SelectUniqueValues](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#selectuniquevalue) は、各ルールを評価し、生成された値がターゲット システムで一意であることを確認します。 例については、「 [userPrincipalName (UPN) 属性の一意の値を生成する」](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#generate-unique-value-for-userprincipalname-upn-attribute)を参照してください。

Note

この機能は現在、Workday から Active Directory、SAP SuccessFactors から Active Directory のユーザー プロビジョニング、およびオンプレミスの Active Directory への API 駆動型プロビジョニングでのみサポートされています。 他のプロビジョニング アプリでの使用はサポートされていません。

#### Active Directory OU コンテナーの割り当てを構成する

事業単位、所在地、部門に基づいて Active Directory ユーザー アカウントをコンテナーに配置することは一般的な要件です。 異動プロセスを開始し、監督組織の変更があった場合、Active Directory 内で、ある OU から別の OU にユーザーを移動することが必要な場合があります。

[Switch()](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#switch) 関数を使用して OU 割り当てのビジネス ロジックを構成し、Active Directory 属性 **parentDistinguishedName にマップします**。

たとえば、HR 属性 **市区町村**に基づいて OU にユーザーを作成する場合は、次の式を使用できます。

`Switch([Municipality], "OU=Default,OU=Users,DC=contoso,DC=com", "Dallas", "OU=Dallas,OU=Users,DC=contoso,DC=com", "Austin", "OU=Austin,OU=Users,DC=contoso,DC=com", "Seattle", "OU=Seattle,OU=Users,DC=contoso,DC=com", "London", "OU=London,OU=Users,DC=contoso,DC=com")`

この式では、Municipality の値が Dallas、Austin、Seattle、または London の場合、ユーザー アカウントは対応する OU に作成されます。 一致するものがない場合、アカウントは既定の OU に作成されます。

### 新しいユーザー アカウントのパスワード交付の計画

入社プロセスの開始時に、新しいユーザー アカウントの一時パスワードを設定して交付する必要があります。 クラウド HR から Microsoft Entra へのユーザー プロビジョニングを使用すると、1 日目にユーザーの Microsoft Entra ID [セルフサービス パスワード リセット (SSPR)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr) 機能をロールアウトできます。

SSPR は、パスワードのリセットやアカウントのロック解除を IT 管理者がユーザーに許可するための簡単な手段です。 クラウド人事アプリから Active Directory に **Mobile Number** 属性をプロビジョニングし、Microsoft Entra ID と同期することができます。 **Mobile Number** 属性が Microsoft Entra ID に設定されたら、ユーザーのアカウントに対して SSPR を有効にすることができます。 その後、新しいユーザーは初日に認証に登録および検証された携帯電話番号を使用できます。 認証の連絡先情報を事前入力する方法の詳細については [、SSPR のドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-authenticationdata) を参照してください。

### 初期サイクルの計画

Microsoft Entra プロビジョニング サービスを初めて実行すると、クラウド HR アプリに対して [最初のサイクル](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works#initial-cycle) が実行され、クラウド HR アプリ内のすべてのユーザー オブジェクトのスナップショットが作成されます。 初期サイクルにかかる時間は、ソース システムに存在するユーザーの数に正比例します。 ユーザーが 10 万人を超えるようなクラウド人事アプリのテナントでは、初期サイクルに長い時間がかかることがあります。

**大規模なクラウド人事アプリ テナント (&gt;30,000 ユーザー) の場合は、** 初期サイクルを段階的な段階で実行します。 増分更新を開始するのは、異なるユーザー プロビジョニングのシナリオに応じて Active Directory で正しい属性が設定されていることを検証した後のみにしてください。 この順序に従ってください。

1. スコープ フィルターを設定して、限られたユーザーのセットに対してのみ初期サイクルを実行します。
2. 最初の実行のために選択したユーザーについて、Active Directory アカウントのプロビジョニングと属性値の設定を確認します。 結果が期待どおりである場合、スコープ フィルターを拡張してユーザーを徐々に増やし、2 回目の実行の結果を確認します。

テスト ユーザーの初期サイクルの結果に満足したら、 [増分更新](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works#incremental-cycles)を開始します。

### テストとセキュリティを計画する

デプロイは、初期パイロットからユーザー プロビジョニングを有効にするまでのステージで構成されます。 各段階で、期待される結果を得るためのテストが行われていることを確認します。 また、プロビジョニング サイクルを監査します。

#### テストを計画する

クラウド人事アプリから Microsoft Entra へのユーザー プロビジョニングを構成したら、テスト ケースを実行して、このソリューションが組織の要件を満たしているかどうか確認します。

| Scenarios | 予想される結果 |
| --- | --- |
| クラウド人事アプリで新しい社員が採用されている。 | - ユーザー アカウントが Active Directory にプロビジョニングされます。- ユーザーは Active Directory ドメインのアプリにログインして目的のアクションを実行できます。- Microsoft Entra Connect Sync が構成されている場合、Microsoft Entra ID にもユーザー アカウントが作成されます。 |
| クラウド人事アプリでユーザーの退職処理が行われる。 | - ユーザー アカウントが Active Directory で無効になります。- ユーザーは Active Directory によって保護されているエンタープライズ アプリにログインできません。 |
| クラウド人事アプリでユーザーの監督組織が更新される。 | 属性マッピングに基づいて、ユーザー アカウントが Active Directory 内のある OU から別の OU に移動します。 |
| 人事がクラウド人事アプリでユーザーの上司を更新する。 | 新しい上司の名前を反映するよう Active Directory の [上司] フィールドが更新されます。 |
| 社員を新しい役職に再雇用する人事 | 動作は、社員 ID の生成に関するクラウド人事アプリの構成内容によって異なります。 再雇用にあたって古い社員 ID を利用する場合、コネクタはユーザーの既存の Active Directory アカウントを有効化します。 再雇用にあたって新しい社員 ID を使用する場合、コネクタはユーザーの新しい Active Directory アカウントを作成します。 |
| 人事が社員を臨時社員に (またはその逆に) 転換する。 | 新しいペルソナ用に新しい Active Directory アカウントが作成され、古いアカウントは転換の発効日をもって無効になります。 |

以上の結果を用いて、また確定したスケジュールに基づいて、自動ユーザー プロビジョニングの実装を運用環境に移行する方法を決定します。

Tip

プライバシーおよびセキュリティ標準に準拠するために、運用データを使用してテスト環境を更新するときは、データ整理やデータ スクラビングなどの手法を用いて、機密な個人データを削除またはマスクします。

#### セキュリティを計画する

通常、新しいサービスのデプロイの一環としてセキュリティ レビューが必要になります。 セキュリティ レビューが必要な場合、または実施されていない場合は、サービスとしての ID の概要を示す多くの Microsoft Entra ID [ホワイト ペーパー](https://www.microsoft.com/download/details.aspx?id=36391) を参照してください。

#### ロールバックを計画する

クラウドの人事ユーザー プロビジョニングの実装は、運用環境では期待通りに動作しない可能性があります。 その場合、次のロールバック手順を使用すると、以前の正常な状態に戻すことができます。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning#provisioning-logs)確認して、影響を受けたユーザーまたはグループに対して実行された不適切な操作を確認します。 プロビジョニングの概要レポートとログの詳細については、「 クラウド人事アプリのユーザー プロビジョニングの管理」を参照してください。
2. 影響を受けるユーザーやグループの最後の既知の良好な状態は、プロビジョニング ログを調べるか、ターゲット システム (Microsoft Entra ID または Active Directory) を確認することで判別できます。
3. アプリの所有者と協力し、最新の既知の正常な状態の値を使用して、アプリで直接影響を受けたユーザーやグループを更新します。

### クラウド人事アプリのデプロイ

ソリューションの要件に合ったクラウド人事アプリを選択します。

**Workday**: Workday から Active Directory と Microsoft Entra ID に worker プロファイルをインポートするには、「 [チュートリアル: Workday を構成して自動ユーザー プロビジョニングを行](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#planning-your-deployment)う」を参照してください。 必要に応じて、メール アドレス、ユーザー名、電話番号を Workday に書き戻すことができます。

**SAP SuccessFactors**: SuccessFactors から Active Directory と Microsoft Entra ID に worker プロファイルをインポートするには、「 [チュートリアル: SAP SuccessFactors を構成して自動ユーザー プロビジョニングを行](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial)う」を参照してください。 必要に応じて、メール アドレスとユーザー名を SuccessFactors に書き戻すことができます。

### 構成の管理

Microsoft Entra ID では、プロビジョニング ログとレポートを利用すると、組織のユーザー プロビジョニングの使用状況と運用の正常性に関する分析情報をさらに得ることができます。

#### レポートとログから分析情報を得る

[最初のサイクル](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works#initial-cycle)が成功した後、Microsoft Entra プロビジョニング サービスは、次のいずれかのイベントが発生するまで、各アプリに固有のチュートリアルで定義されている間隔で、バックツーバックの増分更新を無期限に実行し続けます。

- サービスは手動で停止されます。 [Microsoft Entra 管理センター](https://entra.microsoft.com)または適切な Microsoft [Graph API](https://learn.microsoft.com/ja-jp/graph/api/resources/synchronization-overview) コマンドを使用して、新しい初期サイクルがトリガーされます。
- 属性マッピングまたはスコープ フィルターの変更によって、新しい初期サイクルがトリガーされます。
- エラー率が高いため、プロビジョニング プロセスは検査に入ります。 4 週間を超えると、検査は自動的に無効になります。

これらのイベントとプロビジョニング サービスによって実行されるその他のすべてのアクティビティを確認するには、 [ログを確認し、プロビジョニング アクティビティに関するレポートを取得する方法について説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)します。

##### Azure Monitor ログ

プロビジョニング サービスによって実行されたアクティビティはすべて、Microsoft Entra のプロビジョニング ログに記録されます。 Microsoft Entra プロビジョニング ログを Log Analytics ワークスペースにルーティングできます。これにより、データが Azure Monitor ログと Microsoft Entra ブックに送信され、そこでデータのクエリを実行してイベントを検索したり、傾向を分析したり、さまざまなデータ ソース間で相関関係を実行したりできるようになります。 この [ビデオ](https://youtu.be/MP5IaCTwkQg) では、実際のユーザー シナリオで Microsoft Entra ログに Azure Monitor ログを使用する利点について説明します。

Log Analytics と Microsoft Entra のブックを有効にするには、Log Analytics ワークスペースを構成する必要があります。 次に、診断設定を構成して、適切なエンドポイントにデータをルーティングします。 詳細については次を参照してください:

- [Log Analytics ワークスペースを構成する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/tutorial-configure-log-analytics-workspace)
- [Microsoft Entra アクティビティ ログを Azure Monitor ログと統合する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)
- [Microsoft Entra ワークブックを使用する方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-workbooks)
- [プロビジョニング インサイト ワークブック](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provisioning-workbook)

#### 個人データを管理する

Windows サーバーにインストールされた Microsoft Entra Connect プロビジョニング エージェントは、Windows イベント ログにログを作成しますが、このログには、クラウド人事アプリから Active Directory への属性マッピングの設定によっては、個人データが含まれる場合があります。 ユーザーのプライバシー義務を遵守するために、イベント ログを消去するように Windows のスケジュールされたタスクを設定し、48 時間を超えてデータが保持されないようにすることができます。

Microsoft Entra プロビジョニング サービスでは、レポートの生成、分析の実行、30 日を超えた分析情報の提供は行われません。これは、サービスが 30 日を超えてデータを格納、処理、または保持しないためです。

### Joiner-Mover-Leaver ライフサイクル ワークフローを管理する

人事主導のプロビジョニング プロセスを拡張して、新規採用、人事上の変更、退職に関連するビジネス プロセスとセキュリティ制御をさらに自動化できます。 [Microsoft Entra ID ガバナンス ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows)では、次のような JoinerMover-Leaver ワークフローを構成できます。

- 新規採用者が入社する日の "X" 日前に、マネージャーに電子メールを送信し、ユーザーをグループに追加し、初回ログインの一時アクセス パスを生成します。
- ユーザーの部署または役職またはグループ メンバーシップに変更がある場合は、カスタム タスクを起動します。
- 在籍最終日に、マネージャーにメールを送信し、グループとライセンスの割り当てからユーザーを削除します。
- 退職の "X" 日後、Microsoft Entra ID からユーザーを削除します。

#### Troubleshoot

プロビジョニング中に発生する可能性がある問題のトラブルシューティングについては、次の記事を参照してください。

- [Microsoft Entra Gallery アプリケーションへのユーザー プロビジョニングの構成に関する問題](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/troubleshoot)
- [オンプレミスの Active Directory から Microsoft Entra ID に属性を同期してアプリケーションにプロビジョニングする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping)
- [Microsoft Entra Gallery アプリケーションへのユーザー プロビジョニングの構成中に管理者の資格情報を保存する際の問題](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)
- [Microsoft Entra Gallery アプリケーションにユーザーがプロビジョニングされていない](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-config-problem-no-users-provisioned)
- [Microsoft Entra Gallery アプリケーションにプロビジョニングされているユーザーのセットが間違っている](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)
- [エージェントのトラブルシューティングのための Windows イベント ビューアーの設定](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#setting-up-windows-event-viewer-for-agent-troubleshooting)
- [サービスのトラブルシューティングのための Microsoft Entra 管理センターのプロビジョニング ログの設定](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#setting-up-microsoft-entra-admin-center-provisioning-logs-for-service-troubleshooting)
- [AD ユーザー アカウントの作成操作のログについて](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#understanding-logs-for-ad-user-account-create-operations)
- [Manager の更新操作のログについて](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#understanding-logs-for-manager-update-operations)
- [一般的に発生するエラーの解決](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#resolving-commonly-encountered-errors)

#### 次のステップ

- [属性マッピングの式の記述](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)
- [Microsoft Entra 同期 API の概要](https://learn.microsoft.com/ja-jp/graph/api/resources/synchronization-overview)
- [スコープ外のユーザー アカウントの削除をスキップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions)
- [Microsoft Entra Connect プロビジョニング エージェント: バージョンのリリース履歴](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provisioning-agent-release-version-history)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/plan-sap-user-source-and-target"} -->
## SAP ソース アプリケーションとターゲット アプリケーションを使用したユーザー プロビジョニングのために Microsoft Entra のデプロイを計画する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: SAP SuccessFactors やその他のソースの ID を Microsoft Entra ID に取り込み、SAP ECC、SAP S/4HANA、およびその他のアプリにアクセスできるようにする方法について説明します。

組織は、さまざまな Azure サービスや Microsoft 365 について Microsoft に依存しています。 SAP ソフトウェアとサービスは、組織の人事 (HR) やエンタープライズ リソース プランニング (ERP) などの重要な機能も提供する場合があります。 Microsoft Entra を使用して、従業員、請負業者、およびその他のユーザーの ID と、SAP および非 SAP アプリケーション全体のアクセスを調整できます。

このチュートリアルでは、Microsoft Entra 機能を使用して、SAP 人事ソースから送信されたそれらの ID のプロパティに基づいて SAP アプリケーション全体で ID を管理する方法について説明します。 このチュートリアルでは、次のことを前提としています:

- 組織には商用クラウドに Microsoft Entra テナントがあり、そのテナントの Microsoft Entra ID P1 以上のライセンスがあること。 (一部のステップでは、Microsoft Entra ID ガバナンス機能の使用例も示しています。)
- あなたがそのテナントの管理者であること。
- 組織には、SAP SuccessFactors や SAP HCM などのワーカーのレコード ソースのシステムがあります。
- 組織には SAP ERP Central Component (ECC)、SAP S/4HANA、その他の SAP アプリケーション、SAP BTP ベースのアプリケーションがあり、必要に応じて他の非 SAP アプリケーションがあります。
- SAP ECC 以外の SAP アプリケーションへのプロビジョニングとシングル サインオン (SSO) には、SAP Cloud Identity Services を使用しています。

### 概要

このチュートリアルでは、Microsoft Entra を、SAP SuccessFactors などの組織内の worker の一覧の権限のあるソースと接続する方法について説明します。 また、Microsoft Entra を使用してこれらの worker の ID を設定する方法についても説明します。 次に、Microsoft Entra を使用して、SAP ECC や SAP S/4HANA などの 1 つ以上の SAP アプリケーションにサインインするためのアクセス権を worker に提供する方法について説明します。

プロセスは以下のとおりです。

- **プラン**: 組織内のアプリケーションの ID とアクセスの要件を定義します。 Microsoft Entra ID と関連する Microsoft Online Services がこのシナリオの組織の前提条件を満たしていることを確認します。
- **展開**: 必要なユーザーを Microsoft Entra ID に取り込み、適切な資格情報を使用してそれらのユーザーを最新の状態に保つプロセスを作成します。 Microsoft Entra で必要なアクセス権を持つユーザーを割り当てます。 それらのアプリケーションへのユーザーとそのアクセス権をプロビジョニングし、それらのアプリケーションにサインインできるようにします。
- **監視**: ID フローを監視してエラーがないかを確認し、必要に応じてポリシーや操作を調整します。

プロセスが完了すると、個人は、Microsoft Entra ユーザー ID で使用する権限を持つ SAP および SAP 以外のアプリケーションにサインインできます。

次の図は、このチュートリアルで使用するトポロジの例を示したものです。 このトポロジでは、worker は SuccessFactors で表され、Windows Server Active Directory (Windows Server AD) ドメイン、Microsoft Entra、SAP ECC、SAP クラウド アプリケーションにアカウントがある必要があります。 このチュートリアルでは、Windows Server AD ドメインを持つ組織について説明します。 ただし、このチュートリアルでは Windows Server AD は必要ありません。

[Image: 関連する Microsoft および SAP テクノロジとその統合のエンドツーエンドの範囲を示す図。]

このチュートリアルでは、従業員や他の worker を表すユーザーの ID ライフサイクルに焦点を当てます。 ゲストの ID ライフサイクルと、ロールの割り当て、要求、レビューのアクセス ライフサイクルは、このチュートリアルでは扱いません。

### SAP ソースとターゲットとの統合を計画する

このセクションでは、組織内のアプリケーションの ID とアクセスの要件を定義します。 このセクションでは、SAP アプリケーションとの統合に必要な主な決定事項を重点的に取り上げます。 SAP 以外のプリケーションについては、[それらのアプリケーションへのアクセスを制御するための組織ポリシーを定義する](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-define)こともできます。

これまで SAP IDM を使ってきた場合は、ID 管理シナリオを SAP IDM から Microsoft Entra に移行できます。 詳しくは、「[ID 管理シナリオの SAP IDM から Microsoft Entra への移行](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/migrate-from-sap-idm)」をご覧ください。

#### アプリケーション オンボードのシーケンスと、アプリケーションと Microsoft Entra の統合方法を決定する

おそらく組織では、使用可能な統合シナリオのサブセットのために、一部のアプリケーションが Microsoft Entra と既に統合されているでしょう。 たとえば、SAP Cloud Identity Services と Microsoft Entra for SSO を統合して条件付きアクセスの利点を得たとしても、手動プロビジョニングとプロビジョニング解除に依存している可能性があります。 または、Microsoft Entra とまだ統合していない SAP ECC などのアプリケーションが組織内にある場合もあります。

1. **SSO とプロビジョニングのために、アプリケーションを Microsoft Entra と統合するための優先順位を確立します。** 通常、組織は最新のプロトコルをサポートするサービスとしてのソフトウェア (SaaS) アプリケーションとの統合を最初に行います。 SAP アプリケーションについては、SAP クラウド アプリケーションを持つ組織が、SAP Cloud Identity Services をミドルウェアとして使用して、SSO とプロビジョニングの統合から開始することをお勧めします。 ここでは、ユーザー プロビジョニングと SSO を Microsoft Entra に統合すると、複数の SAP アプリケーションにメリットがあります。
2. **各アプリケーションを Microsoft Entra と統合する方法を確認します。** アプリケーションが SAP S/4HANA などのSAP Cloud Identity Services プロビジョニング ターゲット システムの 1 つとして表示されている場合、または SAP BTP を使用している場合は、SAP Cloud Identity Services をミドルウェアとして使用して、SSO と Microsoft Entra からのプロビジョニングをアプリケーションにブリッジできます。 アプリケーションが SAP ECC の場合は、Microsoft Entra と SAP NetWeaver for SSO を直接統合し、プロビジョニングのために SAP ECC のビジネス アプリケーション プログラミング インターフェイス (BAPI) に統合できます。

    SAP 以外のアプリケーションの場合、「[Microsoft Entra ID とアプリケーションの統合](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-integrate#integrate-the-application-with-microsoft-entra-id-to-ensure-only-authorized-users-can-access-the-application)」の指示に従って、各アプリケーションの SSO とプロビジョニングでサポートされる統合テクノロジを特定します。
3. **各アプリケーションが提供するロールとアクセス許可を収集します。** 一部のアプリケーションには、1 つのロールしかありません。 たとえば、SAP Cloud Identity Services には、割り当てに使用できる**ユーザー** ロールが 1 つだけ含まれます。 SAP Cloud Identity Services では Microsoft Entra ID からグループを読み取ってアプリケーションの割り当てに使用できます。 他のアプリケーションでは、Microsoft Entra ID を通して管理される複数のロールが表示される場合があります。 通常、これらのアプリケーション ロールは、そのロールを持つユーザーがアプリ内で持つアクセスに大きな制約を与えます。

    また、他のアプリケーションは、グループ メンバーシップまたは要求に依存して、より詳細なロール チェックを行う場合もあります。 これらは、フェデレーション SSO プロトコルを使用して発行されたプロビジョニングまたは要求で、Microsoft Entra ID から各アプリケーションに提供できます。 また、セキュリティ グループ メンバーシップとして Active Directory に書き込むこともできます。

    注

    プロビジョニングをサポートする Microsoft Entra ID アプリケーション ギャラリーのアプリケーションを使用している場合、プロビジョニングが構成された後に、Microsoft Entra ID によって定義されたロールがアプリケーションにインポートされ、アプリケーションのロールを使用してアプリケーション マニフェストが自動的に更新される場合があります。
4. **Microsoft Entra ID で管理されるメンバーシップを持つロールとグループを選びます。** 多くの場合、コンプライアンスとリスク管理の要件に基づいて、組織は、機密情報への特権アクセスまたはアクセスを許可するアプリケーション ロールまたはグループに優先順位を付けます。 このチュートリアルでアクセスの割り当てを構成する手順は扱いませんが、すべてのメンバーがアプリケーションに確実にプロビジョニングされるように、関連するロールとグループを特定する必要がある場合があります。

#### アプリケーションへのアクセスに関するユーザーの前提条件とその他の制約について組織のポリシーを定義する

このセクションでは、各アプリケーションへのアクセスを決定するために使用する予定の組織ポリシーを決定します。

1. **アプリケーションへのアクセス権を付与される前に、ユーザーが満たす必要がある前提条件、標準があるかどうかを特定します。** コンプライアンス要件またはリスク管理計画がある組織には、機密性の高いアプリケーションまたはビジネス クリティカルなアプリケーションがあります。 Microsoft Entra ID と統合する前に、これらのアプリケーションにアクセスできるユーザーのアクセス ポリシーを既に文書化している可能性があります。 そうでない場合は、コンプライアンス チームやリスク管理チームなどの利害関係者と相談する必要があります。 アクセスの決定を自動化するために使用されるポリシーがシナリオに適していることを確認する必要があります。

    たとえば、通常の状況では、フルタイムの従業員や、特定の部門またはコスト センターの従業員のみが、特定の部門のアプリケーションへのアクセスを既定で許可される必要があります。 Azure AD Identity Governance で Microsoft Entra エンタイトルメント管理を使用している場合は、アクセス権を要求している他の部門のユーザーに対して 1 人以上の承認者を得るようにエンタイトルメント管理ポリシーを構成して、それらのユーザーが例外としてアクセスできるようにすることができます。

    一部の組織では、従業員によるアクセス要求に 2 つの承認段階がある場合があります。 最初の段階は、ユーザーのマネージャーを要求することです。 2 番目の段階は、アプリケーションに保持されているデータを担当するリソース所有者の 1 人に要求することです。
2. **アクセスを承認されたユーザーがアクセス権を得る期間と、そのアクセス権が取り消されるタイミングを決定します。** 多くのアプリケーションでは、組織に所属しなくなるまで、ユーザーは無期限にアクセスを保持します。 状況によっては、アクセスが特定のプロジェクトまたはマイルストーンに関連付けられていて、プロジェクトが終了すると、アクセス権が自動的に削除されることがあります。 または、ポリシーを介してアプリケーションを使用しているユーザーが少ない場合は、そのポリシーを介したすべてのユーザーのアクセスを四半期ごとまたは年ごとにレビューするように構成し、定期的に監視されるようにすることができます。 これらのシナリオには、Microsoft Entra ID Governance が必要です。
3. **組織が既に組織のロール モデルを使ってアクセスを管理している場合は、その組織のロール モデルを Microsoft Entra ID に導入することを計画してください。** ユーザーのプロパティ (役職や部署など) に基づいてアクセス権を割り当てる[組織ロール モデル](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-organizational-roles)が定義されている場合があります。 これらのプロセスにより、事前に決定されたプロジェクトの終了日がない場合でも、アクセスが不要になったときに最終的にユーザーがアクセス権を失うようにすることができます。

    既に組織ロールの定義がある場合は、Microsoft Entra ID Governance に[組織ロールの定義を移行](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-organizational-roles)できます。
4. **職務の分離に基づく制約があるかどうかを問い合わせる。** このチュートリアルでは、アプリケーションへの基本的なアクセス権をユーザーに提供するための ID ライフサイクルに焦点を当てます。 ただし、アプリケーションのオンボードを計画する場合は、適用する職務の分離に基づいて制約を特定できます。

    たとえば、Western Sales と Eastern Sales という 2 つのアプリ ロールを持つアプリケーションがあるとします。 ユーザーが販売区域ロールを一度に 1 つだけ持っていることを確認する必要があります。 アプリケーションに互換性のないアプリ ロールのペアの一覧を含めます。 その後、ユーザーが 1 つのロールを持っている場合、2 番目のロールを要求することはできません。 アクセス パッケージに関する Microsoft Entra エンタイトルメント管理の非互換性設定で、これらの制約を適用できます。
5. **各アプリケーションにアクセスするための適切な条件付きアクセス ポリシーを選択します。** アプリケーションを分析し、それらを同じユーザーに対して同じリソース要件があるアプリケーションのコレクションに整理することをお勧めします。

    フェデレーション SSO アプリケーションを Microsoft Entra ID と初めて統合するときは、制約を表す新しい条件付きアクセス ポリシーを作成することが必要になる場合があります。 多要素認証 (MFA) または場所ベースのアクセスの要件を、そのアプリケーションと後続のアプリケーションに適用する必要がある場合があります。 ユーザーが [使用条件](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-require-terms-of-use)に同意する必要がある条件付きアクセスを構成することもできます。

    条件付きアクセス ポリシーを定義する方法の詳細については、「 [条件付きアクセスの展開を計画する」を](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/plan-conditional-access)参照してください。
6. **条件の例外を処理する方法を決定します。** たとえば、通常、アプリケーションは指定された従業員のみが利用できますが、監査人またはベンダーが特定のプロジェクトに一時的にアクセスする必要がある場合があります。 または、出張中の従業員が、通常はブロックされている場所 (組織がその場所に存在しないため) からのアクセスを必要とする場合があります。

    このような状況で、Microsoft Entra ID ガバナンスを使用している場合、異なる段階、異なる制限期間、または異なる承認者を持つ承認のためのエンタイトルメント管理ポリシーを選択することもできます。 Microsoft Entra テナントでゲスト ユーザーとしてサインインしているベンダーには、マネージャーがいない可能性があります。 代わりに、組織のスポンサー、リソース所有者、またはセキュリティ責任者がアクセス要求を承認できます。

#### プロビジョニングと認証のトポロジを決定する

ユーザー プロビジョニングと SSO のために Microsoft Entra と統合するアプリケーションを決定したら、権限のあるレコード ソース システムから送信されたデータに基づいて、ユーザー ID とその属性をこれらのアプリケーションに提供する方法のデータ フローを決定します。

1. **各 ID とその属性に対して権限のあるソースを選択します。** このチュートリアルでは、SAP アプリケーションへのアクセスを必要とするユーザーについて、SuccessFactors が権限のあるレコード ソース システムであることを前提としています。 SuccessFactors から Microsoft Entra ID へのクラウド人事主導のユーザー プロビジョニングを構成するには、さまざまな側面をカバーする計画が必要です。 これらの要因には、一致する ID の決定、属性マッピングの定義、属性変換、スコープ フィルターなどがあります。

    これらのテーマに関する包括的なガイドラインについては、[クラウド人事デプロイ計画](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision)を参照してください。 サポートされるエンティティや処理の詳細、さまざまな人事シナリオに向けて統合をカスタマイズする方法については、「[SAP SuccessFactors の統合に関するリファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/sap-successfactors-integration-reference)」を参照してください。 SAP HCM を持ち、SuccessFactors がない組織のその他の統合オプションについては、「 [SAP 人事管理 (HCM) から Microsoft Entra ID へのユーザーのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hcm-microsoft-entra-identity-provisioning)」に記載されています。 他の ID に対して他の権威あるソースを持つ場合があり、一部の ID では、非常用アカウントや他の IT 管理者など、Microsoft Entra ID が権威あるソースであることがあります。
2. **Microsoft Entra ID に加えて、Windows Server AD にユーザーが存在するか、またはプロビジョニングする必要があるかを決定します。** Windows Server AD には、権威のある HR ソースである人事システムの作業者に対応する既存のユーザーがいる場合があります。 または、Lightweight Directory Access Protocol (LDAP) または Kerberos を介して Windows Server に依存するように SAP ECC またはその他のアプリケーションを構成した可能性があります。 このような状況では、ユーザーを Windows Server AD にプロビジョニングします。 その後、これらのユーザーは Microsoft Entra ID に同期されます。
3. **Microsoft Entra Connect プロビジョニング エージェントのデプロイ トポロジを設計する** SuccessFactors を使用していて、Windows Server AD がある場合は、プロビジョニング エージェントをインストールして、それらのドメインにユーザーをプロビジョニングする必要があります。 Microsoft Entra Connect プロビジョニング エージェントのデプロイ トポロジは、統合を計画しているクラウド人事アプリのテナントおよび Active Directory 子ドメインの数によって異なります。 複数の Active Directory ドメインがある場合、Active Directory ドメインが連続しているか[分離](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/plan/disjoint-namespace)しているかによっても異なります。 詳細については、「[Microsoft Entra Connect プロビジョニング エージェントのデプロイ トポロジを設計する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision#design-the-microsoft-entra-connect-provisioning-agent-deployment-topology)」を参照してください。
4. **Windows Server AD で複数のコンテナーを使用するかどうかを決定します。** SuccessFactors を使用していて、Windows Server AD を使用している場合は、各ドメインで、worker を表すユーザーがコンテナー階層に編成されているかどうかを判断する必要があります。 一部の組織では、従業員を表すすべてのユーザーを 1 つの`organizationalUnit`に作成できます。他の組織には複数のユーザーが含まれる場合があります。 詳細については、「[Active Directory OU コンテナーの割り当てを構成する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision#configure-active-directory-ou-container-assignment)」をご覧ください。
5. **Microsoft Entra ID を使用して SAP Cloud Identity Services にプロビジョニングする必要があるか、SAP Cloud Identity Services を使用して Microsoft Entra ID から読み取るかを決定します。** Microsoft Entra のプロビジョニング機能の詳細については、[Microsoft Entra ID での SAP Cloud Identity Services へのユーザーのプロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial)に関する記事をご覧ください。 SAP Cloud Identity Services には、Microsoft Entra ID からユーザーとグループを読み取る独自のコネクタもあります。 詳しくは、「[SAP Cloud Identity Services - Identity Provisioning - Microsoft Entra ID as a source system](https://help.sap.com/docs/identity-provisioning/identity-provisioning/microsoft-azure-active-directory)」を参照してください。
6. **ユーザーを SAP ECC にプロビジョニングする必要があるかどうかを決定します。** Microsoft Entra ID から SAP ECC (旧 SAP R/3) NetWeaver 7.0 以降にユーザーをプロビジョニングできます。 ほかのバージョンの SAP R/3 を使用している場合でも、[Connectors for Microsoft Identity Manager 2016](https://www.microsoft.com/download/details.aspx?id=51495) のダウンロードで提供されているガイドを参照し、プロビジョニング用の独自のテンプレートを作成できます。

#### Microsoft Entra ID を構成する前に、組織の前提条件が満たされていることを確認します

Microsoft Entra ID からビジネスに不可欠なアプリケーションへのアクセスをプロビジョニングするプロセスを開始する前に、Microsoft Entra 環境が適切に構成されていることを確認する必要があります。

1. **お使いの Microsoft Entra ID と Microsoft Online Services 環境で、アプリケーションの**[コンプライアンス要件](https://learn.microsoft.com/ja-jp/entra/standards/standards-overview)**に対する準備ができていることを確認します。**コンプライアンスは、Microsoft、クラウド サービス プロバイダーおよび組織の間での共同責任です。
2. **Microsoft Entra ID テナントが適切にライセンスされていることを確認します。** Microsoft Entra ID を使用してプロビジョニングを自動化するには、テナントに少なくとも [Microsoft Entra ID P1](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing) ライセンスが必要です。これは、ソースの人事アプリケーションから供給される worker やプロビジョニングされるメンバー (ゲスト以外) ユーザーが存在するためです。

    さらに、[ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows)やその他の Azure AD Identity Governance 機能 (プロビジョニング プロセスでの Microsoft Entra エンタイトルメント管理の自動割り当てポリシーなど) を使用するには、worker に対する [Azure AD Identity Governance](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals) ライセンス が必要 です。 これらのライセンスは、Azure AD Identity Governance または Azure AD Identity Governance Step Up for Microsoft Entra ID P2 のいずれかです。
3. **Microsoft Entra ID が、監査ログおよび必要に応じて他のログを Azure Monitor に既に送信していることを確認します。** Microsoft Entra では監査イベントが監査ログに保存されるのは最大 30 日間であるため、Azure Monitor は省略可能ですがアプリへのアクセスの管理に有用です。 監査データは、この既定の保持期間よりも長く保持できます。 詳細については、「[Microsoft Entra ID にレポート データが保存される期間](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-reports-data-retention)」を参照してください。

    また、履歴監査データに対して Azure Monitor ワークブック、カスタム クエリ、およびレポートを使用することもできます。 Microsoft Entra 管理センターの **Microsoft Entra ID** で **[Workbooks]** をクリックすることで、Microsoft Entra 構成をチェックしてこれが Azure Monitor を使用しているかどうかを確認できます。 この統合が構成されておらず、Azure サブスクリプションがあり、少なくともセキュリティ管理者である場合は、[Azure Monitor を使用するように Microsoft Entra ID を構成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logs-and-reporting)できます。
4. **許可されているユーザーのみが Microsoft Entra テナントの高い特権を持つ管理者ロールに含まれるようにします。** 少なくとも ID ガバナンス管理者、ユーザー管理者、アプリケーション管理者、クラウド アプリケーション管理者、または特権ロール管理者である管理者は、ユーザーとそのアプリケーション ロールの割り当てを変更できます。 それらのロールのメンバーシップが最近まだレビューされていない場合は、少なくとも特権ロール管理者であるユーザーが、[これらのディレクトリ ロールのアクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-create-roles-and-resource-roles-review)が開始されていることを確認する必要があります。

    また、Azure Monitor、Logic Apps、Microsoft Entra 構成の操作に必要なその他のリソースを保持するサブスクリプションの Azure ロールのユーザーが確認済みであることを確認する必要もあります。
5. **テナントが適切に分離されていることを確認します。** 組織がオンプレミスで Active Directory を使用していて、それらの AD ドメインが Microsoft Entra ID に接続されている場合は、クラウドでホストされるサービスに対する高い特権を使用する管理操作が、オンプレミス アカウントから分離されていることを確認する必要があります。 [オンプレミスの侵害から Microsoft 365 のクラウド環境を保護するように構成している](https://learn.microsoft.com/ja-jp/entra/architecture/protect-m365-from-on-premises-attacks)ことを確認します。
6. **セキュリティのベスト プラクティスに対して環境を評価します。** Microsoft Entra ID テナントをセキュリティで保護する方法を評価するには、「[すべての分離アーキテクチャに関するベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/architecture/secure-best-practices)」を確認します。
7. **トークンの有効期間とアプリケーションのセッション設定を文書化します。** このチュートリアルの最後では、SAP ECC または SAP Cloud Identity Services アプリケーションを Microsoft Entra for SSO に統合します。 継続的なアクセスを拒否されたユーザーが、フェデレーション アプリケーションを引き続き使用できる期間は、アプリケーション自体のセッションの有効期間と、アクセス トークンの有効期間によって決まります。 アプリケーション セッションの有効期間は、アプリケーション自体によって異なります。 アクセス トークンの有効期間の制御について詳しくは、「[構成可能なトークンの有効期間](https://learn.microsoft.com/ja-jp/entra/identity-platform/configurable-token-lifetimes)」を参照してください。

#### SAP Cloud Identity Services にアプリケーションに必要なスキーマ マッピングがあることを確認する

組織の各 SAP アプリケーションには、ユーザーがそれらのアプリケーションにプロビジョニングされるときに、ユーザーに特定の属性が設定されるという独自の要件がある場合があります。

SAP Cloud Identity Services を使用して SAP S/4HANA またはその他の SAP アプリケーションにプロビジョニングする場合、これらの属性を Microsoft Entra ID から SAP Cloud Identity Services 経由でそれらのアプリケーションに送信するためのマッピングが SAP Cloud Identity Services にあることを確認します。 SAP Cloud Identity Services を使用していない場合は、次のセクションに進んでください。

**SAP クラウド ディレクトリに、SAP クラウド アプリケーションに必要なユーザー スキーマがあることを確認します。** SAP Cloud Identity Services では、構成されている各ターゲット システムによって、SAP Cloud Identity Services に提供される ID のソースのデータ モデルからターゲットの要件への変換が追加されます。 ID のモデル化を計画する方法に対応するように、SAP Cloud Identity Services でこれらの変換を変更する必要がある場合があります (特に、複数のターゲット システムが構成されている場合)。 その後、Microsoft Entra が SAP Cloud Identity Services 経由で SAP クラウド アプリケーションに提供するために必要なスキーマを記録します。

#### レコード ソースのシステム内の作業者レコードと Microsoft Entra のユーザーの関係を定義する

アプリケーションに必要な各属性は、組織内のソースから生成される必要があります。 一部の属性には、定数または他の属性から変換される値が含まれる場合があります。 その他の値は、ユーザーのメール アドレスなど、Microsoft オンライン サービスによって割り当てられる場合があります。 ユーザーの名前、部門、その他の組織のプロパティなどの他の属性は、通常、権限のある人事レコード システムに由来します。 適切な HR レコードが Microsoft Entra ID (Entra ID) とオンプレミスの Active Directory (AD) のユーザーに確実にマップされるように、HR および IT の各チームと協力してデータの一貫性を確保し、データ クレンジング タスクを計画します。

1. **レコードシステムのソースの変更を、参加イベントと離脱イベントにマッピングする方法を決定します。** 作業者の参加と退出のプロセスを自動化する場合は、作業者の状態情報を Microsoft Entra の属性に関連付ける必要があります。 詳細については、「[ユーザー アカウントの状態の決定](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision#determine-user-account-status)」をご覧ください。
2. **人事ソースに、これらの SAP アプリケーションに必要なスキーマを提供できる作業者スキーマがあることを確認します。** SAP クラウドまたはオンプレミス アプリケーションの各必須属性が、Microsoft Entra や他の Microsoft オンライン サービスを起源としていない場合でも、SuccessFactors などのソースから利用可能なプロパティに追跡できることを確認します。 ソースに必要なスキーマがない場合、またはこれらの属性がアプリケーションへのアクセス権を付与される 1 つ以上の ID に設定されていない場合、または Microsoft Entra が読み取りを使用できない場合は、プロビジョニングを有効にする前に、これらのスキーマ要件に対処する必要があります。
3. **Microsoft Entra ID とレコードのシステム間の相関関係に使用されるスキーマを記録します。** SAP SuccessFactors の worker に対応する Windows Server AD または Microsoft Entra ID に既存のユーザーがいる場合があります。 これらのユーザーが Microsoft Entra ID ではなく他のプロセスによって作成されていない場合は、[SAP SuccessFactors 属性のリファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/sap-successfactors-attribute-reference)と Windows Server または Microsoft Entra ユーザー スキーマを参照して、SAP SuccessFactors の worker の一意識別子を含むユーザー オブジェクトの属性を選択してください。

    この属性には、worker に対応する各ユーザーに一意の値が存在する必要があります。これにより、Microsoft Entra のインバウンド プロビジョニングは worker に対して既に存在するユーザーを特定し、重複するユーザーが作成されないようにすることができます。 既定の一致する属性は、従業員 ID に基づいています。 HR ソースから worker をインポートする前に、完全同期を開始する際には、従業員 ID などのこの属性の値が Microsoft Entra ID (クラウド専用ユーザーの場合) と Windows Server AD (ハイブリッド ユーザーの場合) に設定されていることを確認する必要があります。その値によって各ユーザーが一意に識別されます。 詳細については、「[一致する属性を決定する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision#determine-matching-attributes)」および「[一意の属性値を生成する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision#generate-a-unique-attribute-value)」をご覧ください。
4. **関連しなくなった HR レコードをスキップするには、スコープ フィルターを選択します。** HR システムには、何年も従業員ではない従業員を含む、数年の雇用データがあります。 一方、IT チームが関心を持つのは、現在アクティブな従業員および新規採用者の一覧と、稼働後に発生する退職レコードのみである可能性があります。 IT チームの観点から関連性がなくなった HR レコードを除外するには、HR チームと協力して、Microsoft Entra プロビジョニングのスコープ フィルターで使用できる HR レコードにフラグを追加します。 詳細については、「[スコープ フィルターの定義](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision#define-scoping-filters)」をご覧ください。
5. **新しいユーザーのユーザー名を形成する可能性のある特殊文字の処理を計画します。** ユーザーの一意の`userPrincipalName`を作成するために、従業員の名と姓を使用するのが一般的な方法です。 Windows Server AD と Microsoft Entra ID では、`userPrincipalName` はアクセント文字を許可せず、次の文字のみを許可します`A - Z, a - z, 0 - 9, ' . - _ ! # ^ ~`。 関数 [NormalizeDiacritics](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#normalizediacritics) を使用してアクセント文字を処理し、適切な `userPrincipalName` を作成します。
6. **HR ソースから送信される長い文字列の処理を計画します。** Microsoft Entra ID および Windows Server AD 属性の設定に使用する HR フィールドに関連付けられた長い文字列値が、HR データにあるかどうかを確認します。 すべての Entra ID 属性には、最大文字列長があります。 Microsoft Entra ID 属性にマップされた HR フィールドの値にさらに多くの文字が含まれている場合、属性の更新が失敗する可能性があります。 1 つのオプションは、属性マッピングを確認し、HR システムで長い文字列値を切り捨てたり更新したりする可能性があるかどうかを確認することです。 それが選択肢にならない場合は、[Mid](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#mid) などの関数を使用して長い文字列を切り捨てるか、[Switch](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#switch) などの関数を使用して長い値を短い値または省略形にマップすることができます。
7. **必須属性で空になる可能性のある値に対応します。** Microsoft Entra ID または Windows Server AD でアカウントを作成するときは、`firstName`、`lastName`、`CN`、`UPN` などの特定の属性を設定する必要があります。 そのような属性にマップされた対応する HR フィールドが null の場合、ユーザー作成操作は失敗します。 たとえば、AD の `CN` 属性を "表示名" にマップした場合、"表示名" がすべてのユーザーに対して設定されていない限り、エラーが発生します。 1 つのオプションは、このような必須の属性マッピングを確認し、対応するフィールドが HR に入力されていることを確認することです。 式マッピングで null 値をチェックするオプションを検討することもできます。 たとえば、表示名が空の場合は、名と姓を連結して表示名を作ります。

#### SAP ECC に必要な BAPI が Microsoft Entra で使用できる準備ができていることを確認する

Microsoft Entra プロビジョニング エージェントと汎用 Web サービス コネクタは、SAP BAPI を含むオンプレミスの SAP ECC SOAP エンドポイントへの接続を提供します。

SAP ECC を使用せず、SAP クラウド サービスまたは SAP S/4HANA On-Premise にのみプロビジョニングする場合は、次のセクションに進んでください。

1. **プロビジョニングに必要な BAPI が公開されていることを確認します。** ユーザーの作成、更新、削除に必要な API を SAP ECC NetWeaver 7.51 で公開します。 [Microsoft Identity Manager 2016 コネクタ](https://www.microsoft.com/download/details.aspx?id=51495)という名前の`Deploying SAP NetWeaver AS ABAP 7.pdf`ファイルでは、必要な API を公開する方法について説明します。
2. **既存の SAP ユーザーが使用できるスキーマを記録します。** SAP ECC には、レコード ソースの権限のあるシステムの worker に対応する既存のユーザーが存在している可能性があります。 ただし、それらのユーザーが Microsoft Entra ID によって作成されなかった場合、そのユーザーに労働者用の一意の識別子として使えるフィールドを設定する必要があります。 このフィールドは、作業者に対応する各ユーザーに一意の値が存在する必要があります。 その後、Microsoft Entra プロビジョニングでは、worker に既に存在するユーザーを特定し、重複するユーザーの作成を回避できます。

    たとえば、SAP BAPI の `BAPI_USER_GETLIST` と `BAPI_USER_GETDETAIL` を使用している可能性があります。 ソースに関連付ける一意識別子として、`BAPI_USER_GETDETAIL` によって返されるフィールドの 1 つを選択する必要があります。 ソースの一意識別子に対応するフィールドがない場合は、別の一意識別子を使用する必要があります。 たとえば、その値が各 SAP ユーザーで一意であり、Microsoft Entra ID ユーザーにも存在する場合は、SAP フィールド `address.e_mail` を使用する必要がある場合があります。
3. **Microsoft Entra が SAP BAPI に供給するために必要なスキーマを記録します。** たとえば、SAP BAPI `BAPI_USER_CREATE1` を使用しているかもしれません。これは、ユーザーの作成に `ADDRESS`、`COMPANY`、`DEFAULTS`、`LOGONDATA`、`PASSWORD`、`SELF_REGISTER`、`USERNAME` のフィールドが必要です。 Microsoft Entra ID ユーザー スキーマから SAP ECC 要求へのマッピングを構成するときには、Microsoft ID ユーザー属性または定数をそれらの各フィールドにマップします。

#### エンド ツー エンドの属性フローと変換を文書化する

アプリケーションのスキーマ要件と、レコード ソースのシステムから使用可能な worker フィールドを特定しました。 次に、これらのフィールドが Microsoft Entra を経由して、必要に応じて Windows Server AD と SAP Cloud Identity Services を介してアプリケーションに流れる方法のパスを文書化します。

場合によっては、アプリケーションで必要な属性が、ソースから使用可能なデータ値に直接対応していない場合があります。 その後、これらの値をターゲット アプリケーションに提供する前に、値を変換する必要があります。

変換を適用できる処理段階がいくつかあります。

| 段階 | 考慮事項 | 詳細情報のためのリンク |
| --- | --- | --- |
| レコード自体のシステム内 | Microsoft Entra ID ライフサイクル管理は、レコード ソースのシステムから読み取る唯一のソリューションではない可能性があります。 Microsoft Entra にデータを公開する前にデータの正規化を実行すると、同様のデータを必要とする他のソリューションにも役立つ場合があります。 | レコードシステムのドキュメントをご覧ください |
| レコードのシステムから Microsoft Entra または Windows Server AD へのインバウンド プロビジョニング フロー内 | 1 つ以上の SuccessFactors 属性に基づいて、Windows Server AD ユーザー属性または Microsoft Entra ID ユーザー属性にカスタム値を書き込むことができます。 | [カスタマイズ用の関数を使用した式](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data) |
| Windows Server AD から Microsoft Entra ID に同期するとき | Windows Server AD に既にユーザーがいる場合、ユーザーが Microsoft Entra ID に取り込まれるときに、それらのユーザーの属性が変換される可能性があります。 | [Microsoft Entra Connect で同期規則をカスタマイズする方法](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-create-custom-sync-rule)と [Microsoft Entra クラウド同期で式ビルダーを使用する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-expression-builder) |
| Microsoft Entra ID から SAP Cloud Identity Services、SAP ECC、またはその他の SAP 以外のアプリケーションへのアウトバウンド プロビジョニング フロー内 | アプリケーションへのプロビジョニングを構成するときに指定できる属性マッピングの種類の 1 つは、Microsoft Entra ID の 1 つ以上の属性をターゲットの属性にマッピングする式です。 | [カスタマイズ用の関数を使用した式](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data) |
| アウトバウンドのフェデレーテッドSSOでは | 既定では、Microsoft ID プラットフォームにより、そのユーザーのユーザー名 (別名: ユーザー プリンシパル名) を値として持つ要求を含む SAML トークンがアプリケーションに対して発行され、これによってユーザーを一意に識別することができます。 SAML トークンには、ユーザーのメール アドレスまたは表示名を含む他の要求も含まれており、要求変換関数を使用できます。 | [SAML トークン クレームをカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)と [JSON ウェブ トークン クレームをカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity-platform/jwt-claims-customization) |
| SAP Cloud Identity Services で | SAP Cloud Identity Services では、構成されている各ターゲット システムによって、SAP Cloud Identity Services に提供される ID のソースのデータ モデルからターゲットの要件への変換が追加されます。 ID のモデル化を計画する方法に対応するように、SAP Cloud Identity Services でこれらの変換を変更する必要がある場合があります (特に、複数のターゲット システムが構成されている場合)。 この方法は、属性要件が SAP Cloud Identity Services に接続されている 1 つ以上の SAP アプリケーションに固有である場合に適している場合があります。 | [SAP Cloud Identity Services - 変換の管理](https://help.sap.com/docs/identity-provisioning/identity-provisioning/manage-transformations) |

#### 新しい認証資格情報の発行を準備する

1. **Windows Server AD を使用している場合、アプリケーションへのアクセスが必要で、以前に Windows Server AD ユーザー アカウントを持ったことがない worker に対して Windows Server AD 資格情報を発行することを計画してください。** 履歴的に、一部の組織では、ユーザーはアプリケーション リポジトリに直接プロビジョニングされていました。 Worker は、Microsoft Exchange メールボックスまたは Windows Server AD 統合アプリケーションへのアクセスが必要な場合にのみ、Windows Server AD ユーザー アカウントを受け取りました。

    このシナリオでは、Windows Server AD へのインバウンド プロビジョニングを構成する場合、Microsoft Entra は Windows Server AD ユーザー アカウントを持ったことのない worker と新しい worker の両方に対して Windows Server AD にユーザーを作成します。 ユーザーが Windows ドメインにサインインしている場合、ユーザーがパスワードだけではなく [Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/) に登録してより強力な認証を行うことをお勧めします。
2. **Windows Server AD を使用していない場合、アプリケーションへのアクセスが必要で、以前に Microsoft Entra ID ユーザー アカウントを持ったことがない worker に対して Microsoft Entra ID 資格情報を発行することを計画してください。** 最初に Windows Server AD にではなく、Microsoft Entra ID へのインバウンド プロビジョニングを構成する場合、Microsoft Entra は Microsoft Entra ID ユーザー アカウントを持ったことのない worker と新しい worker の両方に対して Microsoft Entra にユーザーを作成します。

ユーザーがパスワードを必要とする場合は、Microsoft Entra ID [セルフサービス パスワード リセット (SSPR)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr) 機能を展開して、ユーザーがパスワードをリセットできます。 **[Mobile Number]** (携帯電話番号) 属性をクラウド人事アプリからプロビジョニングできます。 **携帯電話番号**属性が Microsoft Entra ID に追加されたら、ユーザーのアカウントに対して SSPR を有効にできます。 その後、新しいユーザーは初日に認証に登録および検証された携帯電話番号を使用できます。 認証の連絡先情報を事前設定する方法の詳細については、[SSPR のドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-authenticationdata)を参照してください。

ユーザーがより強力な認証を使用する場合は、[一時アクセス パス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass)を有効にして、新しいユーザーの一時的なアクセス パスを生成できるようにします。

1. **ユーザーが Microsoft Entra MFA の準備ができていることを確認します。** フェデレーション経由で統合されたビジネスクリティカルなアプリケーションには、Microsoft Entra MFA を要求することをお勧めします。 これらのアプリケーションの場合、ポリシーでは、ユーザーがアプリケーションへのサインインを許可する Microsoft Entra ID の前に MFA 要件を満たす必要があります。 組織によっては、場所によるアクセスをブロックしたり、[登録済みのデバイスからアクセスするようにユーザーに要求](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-alt-all-users-compliant-hybrid-or-mfa)したりする場合もあります。

    認証、場所、デバイス、使用条件に必要な条件を含む適切なポリシーがまだない場合は、 [条件付きアクセスの展開にポリシーを追加します](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/plan-conditional-access)。
2. **新しい worker に対して一時アクセス パスを発行する準備をします。** Azure AD Identity Governance を利用していて、Microsoft Entra ID へのインバウンド プロビジョニングを構成している場合は、新しい worker に対して[一時アクセス パス](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#generate-temporary-access-pass-and-send-via-email-to-users-manager)を発行するようにライフサイクル ワークフローを構成することを計画します。

#### パイロットを計画する

人事ビジネス プロセスと ID ワークフローを人事ソースからターゲット システムに統合するには、ソリューションを運用環境にデプロイする前に、大量のデータ検証、データ変換、データ クレンジング、そしてエンドツーエンドのテストが必要です。

運用環境のすべてのユーザーに初期構成を適用する前に、[パイロット環境](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans#best-practices-for-a-pilot)でその構成を実行します。

### Microsoft Entra の統合のデプロイ

このセクションでは、次の作業を行います。

- 権限のあるソース システムから Microsoft Entra ID にユーザーを取り込みます。

    [Image: worker に関するデータの Microsoft Entra ID への取り込みに関連する Microsoft および SAP のテクノロジを示す図。]
- これらのユーザーを SAP Cloud Identity Services または SAP ECC にプロビジョニングして、SAP BTP 上の SAP アプリケーションまたはアプリケーションにサインインできるようにします。

    [Image: Microsoft Entra ID からの ID のプロビジョニングに関連する Microsoft および SAP のテクノロジを示す図。]

#### Windows Server AD ユーザー スキーマを更新する

Windows Server AD と Microsoft Entra ID にユーザーをプロビジョニングする場合、Windows Server AD 環境と関連する Microsoft Entra エージェントが、SAP アプリケーションに必要なスキーマを使用して Windows Server AD との間でユーザーを転送する準備ができていることを確認します。

Windows Server AD を使用しない場合は、次のセクションに進んでください。

1. **必要に応じて、Windows Server AD スキーマを拡張します。** Microsoft Entra に必要なユーザー属性と、Windows Server AD ユーザー スキーマにまだ含まれていないアプリケーションごとに、組み込みの Windows Server AD ユーザー拡張機能属性を選択する必要があります。 または、Windows Server AD スキーマを拡張して、Windows Server AD がその属性を保持する場所を持つ必要があります。 この要件には、作業者の入社日や退職日など、自動化に使用される属性も含まれます。

    たとえば、一部の組織では、`extensionAttribute1` や `extensionAttribute2` などの属性を使用して、これらのプロパティを保持する可能性があります。 組み込みの拡張属性を使用する場合、Windows Server AD の他の LDAP ベースのアプリケーション、または Microsoft Entra ID と統合されたアプリケーションによって、これらの属性がまだ使用されていないことを確認してください。 他の組織では、要件に固有の名前を持つ新しい Windows Server AD 属性 (`contosoWorkerId` など) を作成します。
2. **既存の Windows Server AD ユーザーが人事ソースとの相関関係に必要な属性を持っていることを確認します。** おそらく、worker に対応する既存のユーザーが Windows Server AD に存在している可能性があります。 それらのユーザーには、値が一意で、その労働者の公式な記録システムのプロパティに対応する属性が必要です。

    たとえば、一部の組織では、Windows Server AD で `employeeId` などの属性を使用します。 その属性を持たないユーザーが存在する場合は、後続の統合中に考慮されない可能性があります。 その後、プロビジョニングが自動化されると、Windows Server AD でユーザーが重複して作成されます。 ユーザーが退出しても、元のユーザーは更新または削除されません。 使用できるもの:

    - ドメインに参加しているコンピューター上の PowerShell パイプラインで、`Get-ADUser` コマンドを使用し、Active Directory コンテナー内のすべてのユーザーを取得します。
    - `where-object` のようなフィルターを持つ属性がないユーザーにフィルターを適用する `{$_.employeeId -eq $null}` コマンド。
    - 結果のユーザーを CSV ファイルにエクスポートする `export-csv` コマンド。

    属性を持たない従業員に対応するユーザーがいないことを確認してください。 存在する場合は、先に進む前に、Windows Server AD でそれらのユーザーを編集し、不足している属性を追加する必要があります。
3. **Microsoft Entra ID スキーマを拡張し、Windows Server AD スキーマから Microsoft Entra ID ユーザー スキーマへのマッピングを構成します。** Microsoft Entra Connect Sync を使用している場合は、「[Microsoft Entra Connect Sync: Directory 拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions)」の手順 を実行して、属性を使用して Microsoft Entra ID ユーザー スキーマを拡張します。 Windows Server AD の属性とそれらの属性の Microsoft Entra Connect Sync マッピングを構成します。

    Microsoft Entra クラウド同期を使用している場合は、[Microsoft Entra クラウド同期ディレクトリ拡張機能とカスタム属性マッピング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/custom-attribute-mapping)の手順を実行して、Microsoft Entra ID ユーザー スキーマを他の必要な属性で拡張します。 Windows Server AD の属性とそれらの属性の Microsoft Entra クラウド同期マッピングを構成します。 [ライフサイクル ワークフローに必要な属性](https://learn.microsoft.com/ja-jp/entra/id-governance/how-to-lifecycle-workflow-sync-attributes)を同期していることを確認します。
4. **Windows Server AD から Microsoft Entra ID への同期が終了するまで待ちます。** Windows Server AD からさらに多くの属性をプロビジョニングするようにマッピングを変更した場合、ユーザーに対するその変更が Windows Server AD から Microsoft Entra ID に変更されるまで待ち、ユーザーの Microsoft Entra ID 表現に Windows Server AD の属性の完全なセットが含まれるようにします。

    Microsoft Entra クラウド同期を使用している場合、Microsoft Entra クラウド同期を表すサービス プリンシパルの`steadyStateLastAchievedTime`を取得して、同期状態の  プロパティを監視できます。サービス プリンシパル ID がない場合、「[同期スキーマを表示する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/concept-attributes#view-the-synchronization-schema)」を参照してください。
5. **Windows Server AD で必要なコンテナーを作成します。** ドメイン内の組織単位にユーザーをプロビジョニングする場合は、プロビジョニング エージェントを有効にする前に、それらの `organizationalUnit` コンテナーが存在することを確認します。

#### Microsoft Entra ID のユーザー スキーマを更新する

Windows Server AD を使用している場合、Windows Server AD からのマッピングの構成の一環として、Microsoft Entra ID ユーザー スキーマを既に拡張しています。 この手順が完了したら、次のセクションに進みます。

Windows Server AD を使用していない場合、このセクションの手順に従って Microsoft Entra ID ユーザー スキーマを拡張します。

1. **Microsoft Entra スキーマの拡張機能を保持するアプリケーションを作成します。** Windows Server AD から同期されていないテナントの場合、スキーマの拡張機能は新しいアプリケーションの一部である必要があります。 まだ作成していない場合は、スキーマの拡張機能を表すアプリケーションを作成します。 このアプリケーションに、ユーザーは割り当てられません。
2. **レコードのシステムとの相関関係の属性を特定します。** おそらく、worker に対応する Microsoft Entra ID の既存のユーザーがいる可能性があります。 それらのユーザーには、値が一意で、その作業者の権限があるレコードソースシステムのプロパティに対応する属性が必要です。

    たとえば、一部の組織では、この目的のために新しい属性を持つよう Microsoft Entra ID ユーザー スキーマを拡張しています。 この目的で属性をまだ作成していない場合、次の手順でそれを属性として含めます。
3. **新しい属性のための Microsoft Entra ID ユーザー スキーマを拡張します。** Microsoft Entra ID ユーザー スキーマにまだ含まれていない SAP アプリケーションで必要な属性ごとにディレクトリ スキーマの拡張機能を作成します。 これらの属性により、Microsoft Entra はユーザーに関するより多くのデータを保存することができます。 [拡張属性を作成する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping#create-an-extension-attribute-in-a-tenant-with-cloud-only-users)ことで、スキーマを拡張できます。

#### Microsoft Entra ID のユーザーを人事ソースの worker レコードと関連付けることができるようにする

おそらく、worker に対応する Microsoft Entra ID の既存のユーザーがいる可能性があります。 その後、これらのユーザーには、値が一意であり、それらの労働者の正当な記録システムのプロパティに対応する属性が必要です。

たとえば、一部の組織では、この目的のために新しい属性を持つよう Microsoft Entra ID ユーザー スキーマを拡張する場合があります。 その属性を持たないユーザーが存在する場合は、後続の統合中に考慮されない可能性があります。 その後、プロビジョニングが自動化されると、Windows Server AD で重複するユーザーが作成されます。 ユーザーが退出しても、元のユーザーは更新または削除されません。

1. **Microsoft Entra ID からユーザーを取得します。 Microsoft Entra ID 内に既に存在するユーザーが労働者を表している場合、そのユーザーに関連付けられる属性を持っていることを確認します。** 通常、Microsoft Entra ID 上の一部のユーザーは、正式な記録システム内の従業員とは一致しません。 これらのユーザーには、非常用の緊急アクセス用アカウント、IT ベンダーのアカウント、ビジネス ゲストが含まれます。 残りのユーザーについては、関連付けに使用する一意の値を持つ属性がユーザーに既に存在する必要があります。

    関連付けのないユーザーがいれば、更新やプロビジョニング解除のタイミングを逸してしまう可能性があります。 Microsoft Entra では、重複するユーザーが作成される場合もあります。 たとえば、すべてのメンバー ユーザー (非常用アカウントを除く) に `employeeid` 属性があることが要件である場合、次のスクリプトのような PowerShell コマンド パイプラインを使用してそれらのユーザーを識別できます:

    ```powershell
    $u = get-mguser -all -property id,displayname,userprincipalname,usertype,employeeid | Where-Object {$_.UserType -ne 'Guest' -and $_.EmployeeId -eq $null}
    ```

#### ID ガバナンス機能の前提条件を設定する

Microsoft Entra ID ガバナンス機能 (Microsoft Entra エンタイトルメント管理や Microsoft Entra ライフサイクル ワークフローなど) が必要であることが特定された場合、これらの機能を展開してから、従業員をユーザーとして Microsoft Entra ID に登録します。

1. **必要に応じて、使用条件のドキュメントをアップロードしてください。** ユーザーがアプリケーションにアクセスする前に使用条件に同意する必要がある場合は、条件付きアクセス ポリシーに含まれるように [、使用条件ドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/terms-of-use) を作成してアップロードします。
2. **必要に応じて、カタログを作成します。** 既定では、管理者が最初に Microsoft Entra エンタイトルメント管理を操作すると、既定のカタログが自動的に作成されます。 ただし、管理対象アプリケーションのアクセス パッケージは、指定されたカタログに含まれている必要があります。 Microsoft Entra 管理センターでカタログを作成するには、「[カタログを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#create-a-catalog)」セクションの手順に従います。

    PowerShell を使用してカタログを作成するには、「[Microsoft Entra ID に対して認証する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create-app#authenticate-to-microsoft-entra-id)」と「[カタログを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create-app#create-a-catalog-in-microsoft-entra-entitlement-management)」のセクションの手順に従います。
3. **結合ワークフローを作成します。** Microsoft Entra ID へのインバウンド プロビジョニングを構成している場合、新しい従業員に対して[一時アクセス パス](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#generate-temporary-access-pass-and-send-via-email-to-users-manager)を発行するタスクを含むライフサイクル ワークフローのジョイナー ワークフローを構成します。
4. **サインインをブロックする退職者ワークフローを作成します。**Microsoft Entra ライフサイクル ワークフローで、ユーザーのサインインをブロックするタスクを含む退職者ワークフローを構成します。 このワークフローは、オンデマンドで実行できます。 スケジュールされた退職日を過ぎると worker のサインインがブロックされるようにレコードのソースからのインバウンド プロビジョニングを構成しなかった場合、それらの worker のスケジュールされた退職日に対して実行する退職者ワークフローを構成します。
5. **ユーザー アカウントを削除する退職者ワークフローを作成します。** 必要に応じて、ユーザーを削除するタスクを含む退職者ワークフローを構成します。 このワークフローは、worker のスケジュールされた退職日から 30 日や 90 日後など、ある期間実行するようにスケジュールします。

#### Microsoft Entra ID のユーザーを人事ソースから作業者レコードに接続する

このセクションでは、Microsoft Entra ID と SAP SuccessFactors を人事ソース システムのレコードとして統合する方法について説明します。

1. **サービス アカウントを使用してレコードのシステムを構成し、Microsoft Entra ID に適切なアクセス許可を付与します。** SAP SuccessFactors を使用している場合、「[統合のための SuccessFactors の構成](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-cloud-only-tutorial#configuring-successfactors-for-the-integration)」セクションの手順に従います。
2. **レコードのシステムから Windows Server AD または Microsoft Entra ID へのインバウンド マッピングを構成します。** SAP SuccessFactors を使用しており、Windows Server AD と Microsoft Entra ID にユーザーをプロビジョニングする場合、「[SuccessFactors から Active Directory へのユーザー プロビジョニングの構成](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial#configuring-user-provisioning-from-successfactors-to-active-directory)」のセクションの手順に従います。

    SAP SuccessFactors を使用しており、Windows Server AD にプロビジョニングしない場合、「[SuccessFactors から Microsoft Entra ID へのユーザー プロビジョニングの構成](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-cloud-only-tutorial#configuring-user-provisioning-from-successfactors-to-microsoft-entra-id)」のセクションの手順に従います。

    マッピングを構成するときは、関連付けに使用する Windows Server AD 属性または Microsoft Entra ID ユーザー属性に対して **[この属性を使用してオブジェクトを照合する]** を使用してマッピングを構成していることを確認します。 また、作業者の入社日と退社日に必要な属性と、人事ソースから生成された宛先アプリケーションで必要なすべての属性のマッピングも構成します。
3. **記録システムから初期の受信プロビジョニングを実行します。** SAP SuccessFactors を使用しており、Windows Server AD と Microsoft Entra ID にユーザーをプロビジョニングする場合、「[プロビジョニングの有効化と起動](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial#enable-and-launch-user-provisioning)」のセクションの手順に従います。 SAP SuccessFactors を使用しており、Windows Server AD にプロビジョニングしない場合、「[プロビジョニングの有効化と起動](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-cloud-only-tutorial#enable-and-launch-user-provisioning)」のセクションの手順に従います。 大規模なクラウド HR アプリ テナント (3 万人以上のユーザー）の場合、「&gt;」で説明されているように、最初のサイクルを段階的に実行します。
4. **レコードのシステムからの初期同期が終了するまで待ちます。** SAP SuccessFactors から Windows Server AD または Microsoft Entra ID に同期する場合、ディレクトリへの最初の同期が完了した後、Microsoft Entra は、Microsoft Entra 管理センターの SAP SuccessFactors アプリケーションの **[プロビジョニング]** タブで監査概要レポート を更新します。

[Image: プロビジョニングの進行状況バーを示すスクリーンショット。]
5. **Windows Server AD にプロビジョニングする場合、Windows Server AD で作成された新しいユーザーまたは Windows Server AD で更新されたユーザーが Windows Server AD から Microsoft Entra ID に同期されるまで待ちます。** Windows Server AD のユーザーが Microsoft Entra ID に変更を加えるまで待ち、ユーザーの Microsoft Entra ID 表現に Windows Server AD のユーザーとその属性の完全なセットが含まれるようにします。

    Microsoft Entra クラウド同期を使用している場合、クラウド同期を表すサービス プリンシパルの`steadyStateLastAchievedTime`を取得することで、同期状態の  を監視できます。サービス プリンシパル ID がない場合、「[同期スキーマを表示する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/concept-attributes#view-the-synchronization-schema)」を参照してください。
6. **ユーザーが Microsoft Entra ID にプロビジョニングされていたことを確認します。** この時点で、ユーザーは Microsoft Entra ID に存在し、ターゲット アプリケーションで必要な属性を持っている必要があります。 たとえば、ユーザーに `givenname`、`surname`、`employeeID` 属性が必要な場合があります。 特定の属性を持つユーザーの数、または属性が不足しているユーザーの数を表示するには、次のスクリプトのような PowerShell コマンドを使用できます。

    ```powershell
    $u = get-mguser -all -property id,displayname,userprincipalname,usertype,givenname,surname,employeeid
    $u2 = $u | where-object {$_.usertype -ne 'Guest' -and $_.employeeid -ne $null}
    $u2c = $u2.Count
    write-output "member users with employeeID attribute: $u2c"
    $u3 = $u| Where-Object {$_.UserType -ne 'Guest' -and ($_.EmployeeId -eq $null -or $_.GivenName -eq $null -or $_.Surname -eq $null)}
    $u3c = $u3.Count
    write-output "member users missing employeeID, givenname or surname attributes: $u3c"
    ```
7. **Microsoft Entra ID に予期しない、関連付けのないアカウントがないことを確認します。** 通常、Microsoft Entra ID 上の一部のユーザーは、正式な記録システム内の従業員とは一致しません。 これには、緊急時の管理アクセス用のブレークグラスアカウント、ITベンダーのアカウント、およびビジネスゲスト用のアカウントが含まれます。

    ただし、現在の従業員アカウントに似ているが、従業員レコードと同期されなかった Microsoft Entra の孤児アカウントである場合もあります。 孤立アカウントは、人事システムに参加しなくなった元従業員から発生する可能性があります。 また、一致するエラーから発生する可能性もあります。 または、名前を変更したユーザーや再雇用されたユーザーなど、データ品質の問題から発生する可能性があります。
8. **Microsoft Entra へのレコード フローのアップストリーム システムで、結合、更新、および脱退アクティビティを正しくテストします。** 詳細については、「[テストを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision#plan-testing)」をご覧ください。

#### アプリケーションへのユーザーとそのアクセス権をプロビジョニングし、それらのアプリケーションにサインインできるようにする

ユーザーが Microsoft Entra ID に存在するようになったので、次のセクションでは、ターゲット アプリケーションにユーザーをプロビジョニングします。

[Image: Microsoft Entra ID からの ID のプロビジョニングに関連する Microsoft および SAP のテクノロジを示す図。]

#### SAP Cloud Identity Services へのユーザーのプロビジョニング

このセクションの手順では、Microsoft Entra ID から SAP Cloud Identity Services へのプロビジョニングを構成します。 既定では、SAP Cloud Identity Services に対してユーザーのプロビジョニングとプロビジョニング解除を自動的に実行できるように Microsoft Entra ID をセットアップします。 その後、これらのユーザーは SAP Cloud Identity Services に対して認証を行い、SAP Cloud Identity Services と統合されている他の SAP ワークロードにアクセスできます。 SAP Cloud Identity Services では、そのローカル ID ディレクトリから他の SAP アプリケーションへ、[ターゲット システム](https://help.sap.com/docs/identity-provisioning/identity-provisioning/target-systems)としてのプロビジョニングがサポートされています。

または、Microsoft Entra ID から読み取るように SAP Cloud Identity Services を構成することもできます。 SAP Cloud Identity Services を使用して Microsoft Entra ID からユーザーと必要に応じてグループを読み取る場合、SAP Cloud Identity Services の構成方法に関する SAP ガイダンスに従ってください。 その後、次のセクションに進みます。

SAP Cloud Identity Services を使用していない場合は、次のセクションに進んでください。

1. [SAP Cloud Identity Services テナント](https://help.sap.com/docs/btp)**と SAP Cloud Identity Services での管理者アクセス許可を持つユーザー アカウントがあることを確認します。**
2. **プロビジョニング用に SAP Cloud Identity Services を設定します。** SAP Cloud Identity Services 管理コンソールにサインインし、「[プロビジョニング用に SAP Cloud Identity Services を設定する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial#set-up-sap-cloud-identity-services-for-provisioning)」セクションの手順に従います。
3. **ギャラリーから SAP Cloud Identity Services を追加し、SAP Cloud Identity Services への自動ユーザー プロビジョニングを構成します。** 「[ギャラリーから SAP Cloud Identity Services を追加する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial#add-sap-cloud-identity-services-from-the-gallery)」と「[SAP Cloud Identity Services に対する自動ユーザー プロビジョニングの構成](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial#configure-automatic-user-provisioning-to-sap-cloud-identity-services)」のセクションの手順に従います。
4. **Microsoft Entra ID から SAP Cloud Identity Services にテスト ユーザーをプロビジョニングします。** 「[Microsoft Entra ID から SAP Cloud Identity Services に新しいテスト ユーザーをプロビジョニングする](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial#provision-a-new-test-user-from-microsoft-entra-id-to-sap-cloud-identity-services)」セクションの手順に従って、プロビジョニング統合の準備ができていることを確認します。
5. **Microsoft Entra と SAP Cloud Identity Services の両方の既存のユーザーを関連付けることができることを確認します。** Microsoft Entra ID のユーザーと SAP Cloud Identity Services に既に存在するユーザーを比較するには、次のセクションの手順に従います:

    - [既存の SAP Cloud Identity Services ユーザーが必要な合致属性を持っていることを確認する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial#ensure-existing-sap-cloud-identity-services-users-have-the-necessary-matching-attributes)
    - [既存の Microsoft Entra ユーザーが必要な属性を持っていることを確認する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial#ensure-existing-microsoft-entra-users-have-the-necessary-attributes)
6. **Microsoft Entra ID で SAP Cloud Identity Services の既存のユーザーをアプリケーションに割り当てます。** 「[Microsoft Entra ID で SAP Cloud Identity Services アプリケーションにユーザーを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial#assign-users-to-the-sap-cloud-identity-services-application-in-microsoft-entra-id)」のセクションの手順に従います。 これらの手順では、次の操作を行う必要があります:

    - プロビジョニングが検疫されないように、その問題に対処します。
    - SAP Cloud Identity Services 内に存在し、Microsoft Entra ID 内のアプリケーションにまだ割り当てられていないユーザーを確認します。
    - 残りのユーザーを割り当てます。
    - 初期同期を監視します。
7. **Microsoft Entra ID から SAP Cloud Identity Services への同期を待ちます。** アプリケーションに割り当てられたすべてのユーザーがプロビジョニングされるまで待ちます。 初回サイクルには、20 分から数時間かかります。 タイミングは、Microsoft Entra ディレクトリのサイズと、プロビジョニングの対象となるユーザー数によって異なります。 SAP Cloud Identity Services を表すサービス プリンシパルの同期ジョブを取得することで、同期状態の`steadyStateLastAchievedTime`プロパティを監視できます。
8. **プロビジョニング エラーがないか確認します。**[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)または [Graph API](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-configuration-api#monitor-provisioning-events-using-the-provisioning-logs) 経由でプロビジョニング ログを確認します。 ログを状態 **[失敗]** でフィルター処理します。

    エラー コード`DuplicateTargetEntries`がある場合、このコードはプロビジョニング照合ルールのあいまいさを示しています。 各 Microsoft Entra ユーザーが 1 人のアプリケーション ユーザーと一致するようにするには、Microsoft Entra ユーザーまたは照合に使用されるマッピングを更新する必要があります。 次に、ログをアクション **[作成]** と状態 **[スキップ済み]** でフィルター処理します。

    ユーザーが `SkipReason`\* のコード`NotEffectivelyEntitled`でスキップされた場合、このログ イベントは、ユーザー アカウントの状態が**無効**であるため、Microsoft Entra ID のユーザー アカウントが一致しなかったことを示している可能性があります。
9. **SAP Cloud Identity Services のユーザーと Microsoft Entra ID のユーザーを比較します。** 「[既存の SAP Cloud Identity Services ユーザーが必要な合致属性を持っていることを確認する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial#ensure-existing-sap-cloud-identity-services-users-have-the-necessary-matching-attributes)」セクションの手順を繰り返して、SAP Cloud Identity Services からユーザーを再エクスポートします。 その後、エクスポートされたユーザーが SAP アプリケーションに必要なプロパティを持っていることを確認します。 PowerShell `where-object` コマンドを使用すると、`{$_.employeeId -eq $null}` などのフィルターによって、ユーザーの一覧を属性が不足しているユーザーだけにフィルター処理できます。
10. **Microsoft Entra から SAP Cloud Identity Services へのフェデレーション SSO を構成します。** SAP Cloud Identity Services の SAML ベースの SSO を有効にします。 「[SAP Cloud Identity Services のシングル サインオン チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hana-cloud-platform-identity-authentication-tutorial)」に記載されている手順に従います。
11. **SAP BTP アプリケーション ロールのグループを作成し、BTP ロール コレクションにグループを割り当てます。** BTP にアプリケーションがある場合は、Microsoft Entra セキュリティ グループを作成し、それらのグループ ID をアプリケーション ロールにマップできます。 詳細については、「[SAP BTP](https://community.sap.com/t5/technology-blogs-by-members/identity-and-access-management-with-microsoft-entra-part-i-managing-access/ba-p/13873276)へのアクセスの管理」を参照してください。
12. **アプリケーション Web エンドポイントを適切な条件付きアクセス ポリシーのスコープに取り込みます。** 同じガバナンス要件の対象となる別のアプリケーション用に作成された既存の条件付きアクセス ポリシーがある可能性があります。 その後、多数のポリシーを使用しないように、このアプリケーションにも適用するようにポリシーを更新できます。

    更新の完了後、期待どおりのポリシーが適用されていることを確認します。 [条件付きアクセスの what-if ツール](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/what-if-tool)を使用して、ユーザーに適用されるポリシーを確認できます。
13. **テスト ユーザーが SAP アプリケーションに接続できることを検証します。** Microsoft **マイ アプリ**を使用して、アプリケーションの SSO をテストできます。 テスト ユーザーが SAP Cloud Identity Services アプリケーションに割り当てられ、Microsoft Entra ID から SAP Cloud Identity Services にプロビジョニングされていることを確認します。 その後、そのユーザーとして Microsoft Entra にサインインし、`myapps.microsoft.com` に移動します。

    **マイ アプリ**で [SAP Cloud Identity Services] タイルをクリックすると、サービス プロバイダー (SP) モードで構成されている場合は、サインイン フローを開始するためのアプリケーション サインイン ページにリダイレクトされます。 ID プロバイダー (IDP) モードで構成されている場合、SSO を設定した SAP Cloud Identity Services に自動的にサインインします。

#### SAP ECC にユーザーをプロビジョニングする

Microsoft Entra ID のユーザーが作成されたので、オンプレミスの SAP ECC にユーザーをプロビジョニングできます。

SAP ECC を使用していない場合は、次のセクションに進みます。

1. **プロビジョニングを構成します。** 「[NetWeaver AS ABAP 7.0 以降を使用して、SAP ECC にユーザーをプロビジョニングするように Microsoft Entra ID を構成する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-sap-connector-configure)」の指示に従います。
2. **Microsoft Entra ID から SAP ECC への同期を待ちます。** SAP ECC アプリケーションに割り当てられたすべてのユーザーがプロビジョニングされるまで待ちます。 初回サイクルには、20 分から数時間かかります。 タイミングは、Microsoft Entra ディレクトリのサイズと、プロビジョニングの対象となるユーザー数によって異なります。 サービス プリンシパルの[同期ジョブ](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronization-list-jobs?view=graph-rest-1.0&preserve-view=true&tabs=powershell#example)を取得することで、同期状態の`steadyStateLastAchievedTime`を監視できます。
3. **プロビジョニング エラーがないか確認します。**[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)または [Graph API](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-configuration-api#monitor-provisioning-events-using-the-provisioning-logs) 経由でプロビジョニング ログを確認します。 ログを状態 **[失敗]** でフィルター処理します。

    エラー コード `DuplicateTargetEntries` でエラーが発生した場合、このログ イベントは、プロビジョニングの一致ルールのあいまいさを示します。 各 Microsoft Entra ユーザーが 1 人のアプリケーション ユーザーと一致するように、Microsoft Entra ユーザーまたは照合に使用されるマッピングを更新する必要があります。 次に、ログをアクション **[作成]** と状態 **[スキップ済み]** でフィルター処理します。

    ユーザーが `SkipReason` の `NotEffectivelyEntitled` コードでスキップされた場合、このコードは、ユーザー アカウントの状態が**無効**であるため、Microsoft Entra ID のユーザー アカウントが一致しなかったことを示している可能性があります。
4. **SAP ECC のユーザーと Microsoft Entra ID のユーザーを比較します。** SAP ECC にプロビジョニングするためのプロビジョニング エージェントをホストしている Windows Server で、`Microsoft ECMA2Host` Windows サービスを再起動します。 サービスが再起動すると、SAP ECC からユーザーの完全なインポートが実行されます。
5. **Microsoft Entra から SAP へのフェデレーション SSO を構成します。** SAP アプリケーションに対して SAML ベースの SSO を有効にします。 SAP NetWeaver を使用している場合、『[SAP NetWeaver と SSO のチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-netweaver-tutorial)』に記載されている手順に従います。
6. **アプリケーション Web エンドポイントを適切な条件付きアクセス ポリシーのスコープに取り込みます。** 同じガバナンス要件の対象となる別のアプリケーション用に作成された既存の条件付きアクセス ポリシーがある可能性があります。 その後、多数のポリシーを使用しないように、このアプリケーションにも適用するようにポリシーを更新できます。

    更新の完了後、期待どおりのポリシーが適用されていることを確認します。 [条件付きアクセスの what-if ツール](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/what-if-tool)を使用して、ユーザーに適用されるポリシーを確認できます。
7. **テスト ユーザーをプロビジョニングして SAP NetWeaver にサインインできることを検証します。** 「 [SSO のテスト](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-netweaver-tutorial#test-sso) 」セクションの手順に従って、条件付きアクセスの構成後にユーザーがサインインできることを確認します。

#### SuccessFactors とその他のアプリケーションへのプロビジョニングを構成する

Microsoft Entra ID から SAP SuccessFactors Employee Central に特定の属性 (仕事用メールなど) が書き込まれるように Microsoft Entra を構成できます。 詳細については、[Microsoft Entra ID での SAP SuccessFactors の書き戻しの構成](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-writeback-tutorial)に関する記事をご覧ください。

Microsoft Entra は、OpenID Connect、SAML、SCIM、SQL、LDAP、SOAP、REST などの[標準](https://learn.microsoft.com/ja-jp/entra/architecture/auth-sync-overview)を使用するアプリケーションなど、他の多くのアプリケーションにもプロビジョニングできます。 詳細については、「[すべてのアプリと Microsoft Entra ID との統合](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-integrate)」を参照してください。

#### Microsoft Entra で必要なアプリケーション アクセス権をユーザーに割り当てる

構成しているテナントが、SAP アプリケーション アクセス専用に構成された完全に分離されたテナントでない限り、テナント内のすべてのユーザーが SAP アプリケーションにアクセスすることの必要性はほとんどありません。 テナント内の SAP アプリケーションは、アプリケーションへのアプリケーション ロールの割り当てを持つユーザーのみがアプリケーションにプロビジョニングされ、Microsoft Entra ID からそのアプリケーションにサインインできるように構成されます。

アプリケーションに割り当てられているユーザーが Microsoft Entra ID で更新されると、それらの変更はそのアプリケーションに自動的にプロビジョニングされます。

SAP BTP にアプリケーションがある場合は、Microsoft Entra で管理されているグループを SAP Cloud Identity Services のそのアプリケーションのロールに割り当てることもできます。

Microsoft Entra ID ガバナンスがある場合は、Microsoft Entra ID で SAP Cloud Identity Services または SAP ECC、およびグループに対するアプリケーション ロールの割り当ての変更を自動化できます。 自動化を使用すると、ユーザーが組織に参加したり、ロールを離れたり変更したりするときに、割り当てを追加または削除できます。

1. **既存の割り当てを確認します。** 必要に応じて、[アプリケーション ロールの割り当て](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-application-preparation) またはグループ メンバーシップの 1 回限りのアクセス レビューを実行します。 このレビューが完了すると、アクセス レビューによって、不要になったグループの割り当てまたはメンバーが削除されます。
2. **アプリケーション ロールの割り当てを最新の状態に保つようにプロセスを構成します。** Microsoft Entra エンタイトルメント管理を使用していて、SAP アプリケーションがある場合は、「[PowerShell](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create-app) を使用して、SAP クラウド ID サービスまたは SAP ECC を表すアプリケーションへの割り当てを構成することで、1 つのロールを持つアプリケーションのエンタイトルメント管理でアクセス パッケージを作成する」を参照してください。 SAP BTP にアプリケーションがある場合は、エンタイトルメント管理を構成して、ユーザーを Microsoft Entra セキュリティ グループに割り当てることができます。 詳細については、「[SAP BTP](https://community.sap.com/t5/technology-blogs-by-members/identity-and-access-management-with-microsoft-entra-part-i-managing-access/ba-p/13873276)へのアクセスの管理」を参照してください。

    そのアクセス パッケージでは、ユーザーがアクセスを要求したときにアクセス権を割り当てるためのポリシーを設定できます。 割り当ては、[管理者が行う](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#directly-assign-an-identity)か、[ルールに基づいて自動的に行う](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy)か、[ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-scenarios#administrator-assign-employees-access-from-lifecycle-workflows)を通じて生成できます。

Azure AD Identity Governance がない場合は、[Microsoft Entra 管理センターで各ユーザーをアプリケーションに割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)ことができます。 PowerShell コマンドレット `New-MgServicePrincipalAppRoleAssignedTo` を使用して、個々のユーザーをアプリケーションに割り当てることができます。

#### 新しく作成された Microsoft Entra ユーザーまたは Windows Server AD ユーザーに資格情報を配布する

この時点で、すべてのユーザーが Microsoft Entra ID に存在し、関連する SAP アプリケーションにプロビジョニングされます。 このプロセス中に、Windows Server AD または Microsoft Entra ID に存在したことのない worker に対して作成されたすべてのユーザーには、新しい資格情報が必要です。

1. **Microsoft Entra インバウンド プロビジョニングで Windows Server AD でユーザーが作成されている場合、新しく作成されたユーザーに対して Windows Server AD の初期資格情報を配布します。**[Get-MgAuditLogProvisioning](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.reports/get-mgauditlogprovisioning) コマンドを使用して、Windows Server AD と Microsoft Entra の対話のイベントの一覧を取得できます。

    ドメインに参加しているコンピューターで [`Set-ADAccountPassword` コマンドを `-Reset` パラメーター](https://learn.microsoft.com/ja-jp/powershell/module/activedirectory/set-adaccountpassword#example-1-set-a-password-for-a-user-account-using-a-distinguished-name)と共に使用して、ユーザーの新しい Windows Server AD パスワードを設定できます。 次に、[`Set-ADUser` コマンドを `-ChangePasswordAtLogon` パラメーター](https://learn.microsoft.com/ja-jp/powershell/module/activedirectory/set-aduser)を使用して、ユーザーが次のサインイン時に新しいパスワードを選択するように要求します。
2. **Microsoft Entra インバウンド プロビジョニングで Microsoft Entra ID でユーザーが作成されている場合、新しく作成されたユーザーに対して Microsoft Entra ID の初期資格情報を配布します。**[Get-MgAuditLogDirectoryAudit](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.reports/get-mgauditlogdirectoryaudit) コマンドと `Get-MgAuditLogDirectoryAudit -Filter "category eq 'UserManagement' and activityDisplayName eq 'Add user' and result eq 'success' and activityDateTime+ge+2024-05-01" -all` などのパラメーターを共に使用して、新しく作成されたユーザーの一覧を取得できます。

    ユーザーに対して一時アクセス パスを生成するには、[New-MgUserAuthenticationTemporaryAccessPassMethod](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/new-mguserauthenticationtemporaryaccesspassmethod) と [Get-MgUserAuthenticationTemporaryAccessPassMethod](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/get-mguserauthenticationtemporaryaccesspassmethod?view=graph-powershell-1.0&preserve-view=true&viewFallbackFrom=graph-powershell-beta) コマンドを使用できます。これについては「[一時アクセス パスを作成する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass#create-a-temporary-access-pass)」で扱われています。
3. **ユーザーが MFA に登録されていることを確認します。** 「[MFA に登録されているユーザーに関する PowerShell のレポート](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-reporting#powershell-reporting-on-users-registered-for-mfa)」セクションにある PowerShell コマンドを実行して、MFA に登録されていないユーザーを特定できます。
4. **一時的なポリシーの除外を必要とするユーザーがいる場合は、定期的なアクセス レビューを作成します。** 場合によっては、すべての承認されたユーザーに対して条件付きアクセス ポリシーをすぐに適用できない場合があります。 たとえば、一部のユーザーは、適切な登録済みデバイスを持っていない場合があります。 条件付きアクセス ポリシーから 1 人以上のユーザーを除外し、アクセスを許可する必要がある場合 [は、条件付きアクセス ポリシーから除外されるユーザー](https://learn.microsoft.com/ja-jp/entra/id-governance/conditional-access-exclusion)のグループのアクセス レビューを構成します。

### ID フローを監視する

アプリケーションでインバウンド プロビジョニングとアウトバウンド プロビジョニングが構成されたので、Microsoft Entra の自動化を使用して、権限のあるレコード システムからターゲット アプリケーションへの継続的なプロビジョニングを監視できます。

#### インバウンド プロビジョニングの監視

プロビジョニング サービスによって実行されたアクティビティは、Microsoft Entra の[プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)に記録されます。 プロビジョニング ログには、Microsoft Entra 管理センターでアクセスできます。 プロビジョニング データは、ユーザー名か、ソース システムまたはターゲット システムの識別子に基づいて検索できます。 詳細については、「[プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)」を参照してください。

#### Windows Server AD での変更の監視

「[Windows Server 監査ポリシーの推奨事項](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/plan/security-best-practices/audit-policy-recommendations)」で説明されているように、"ユーザー アカウント管理" 成功監査イベントがすべてのドメイン コントローラーで有効になっており、分析のために収集されていることを確認します。

#### アプリケーション ロールの割り当てを監視する

[Azure Monitor に監査イベントを送信する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logs-and-reporting)ように Microsoft Entra ID を構成した場合、Azure Monitor ブックを使用して、ユーザーがアクセスを受け取った方法に関する分析情報を取得できます。

- Microsoft Entra エンタイトルメント管理を使用している場合、"アクセス パッケージ アクティビティ" という名前のブックに特定のアクセス パッケージに関連する各イベントが表示されます。

    [Image: アクセス パッケージ イベントを示すスクリーンショット。]
- アクセス パッケージの割り当てにより、アプリケーションのアプリケーション ロールの割り当ての変更が作成されなかったかどうかを確認するには、"**アプリケーション ロールの割り当てアクティビティ**" という名前のブックを選択します。 エンタイトルメント アクティビティを省略することを選択した場合は、エンタイトルメント管理によって作成されたのではないアプリケーション ロールに対する変更のみが表示されます。 たとえば、別の管理者がアプリケーション ロールにユーザーを直接割り当てた場合、行が表示されます。

    [Image: アプリ ロールの割り当てを示すスクリーンショット。]

#### アウトバウンドプロビジョニングを監視する

Microsoft Entra と統合されたアプリケーションごとに、**[同期の詳細]** セクションを 使用して進行状況を監視し、リンクをクリックしてプロビジョニング アクティビティ レポートに移動できます。 このレポートには、Microsoft Entra プロビジョニング サービスによってアプリケーションに対して実行されたすべてのアクションが記述されます。 Microsoft [Graph API](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-configuration-api#monitor-the-provisioning-job-status) を使用してプロビジョニング プロジェクトを監視することもできます。

Microsoft Entra プロビジョニング ログの読み方の詳細については、「[自動ユーザー アカウント プロビジョニングについてのレポート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)」を参照してください。

#### SSO の監視

アプリケーションへの過去 30 日間のサインインは、Microsoft Entra 管理センターの[サインイン レポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-in-log-activity-details)、または [Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/signin-list?view=graph-rest-1.0&tabs=http&preserve-view=true) で確認できます。 また、[Azure Monitor へのサインイン ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-log-monitoring-integration-options-considerations)を送信し、サインイン アクティビティを最大 2 年間アーカイブすることもできます。

#### Microsoft Entra ID ガバナンスでの割り当ての監視

Azure AD Identity Governance を使用している場合、Azure AD Identity Governance 機能を使用してアクセスする方法について報告できます。 次に例を示します。

- 管理者またはカタログ所有者は、Microsoft Entra 管理センター、Microsoft Graph、PowerShell などを使用して、[アクセス パッケージの割り当てを持つユーザーの一覧を取得](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments)できます。
- また、監査ログを Azure Monitor に送信し、[アクセス パッケージに対する変更](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logs-and-reporting#view-events-for-an-access-package)の履歴を、Microsoft Entra 管理センターまたは PowerShell 経由で表示することもできます。

これらおよびその他の ID ガバナンス シナリオの詳細については、[必要に応じて、エンタイトルメント管理ポリシーとアクセスを調整するために監視する](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-deploy#monitor-to-adjust-entitlement-management-policies-and-access-as-needed)方法をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/provision-custom-security-attributes"} -->
## HR ソースからカスタム セキュリティ属性をプロビジョニングする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-custom-security-attributes
- Service: entra-id / app-provisioning
- Article date: 2025-06-24
- Summary: HR ソースからカスタム セキュリティ属性をプロビジョニングする方法について説明します。

カスタム セキュリティ属性のプロビジョニングにより、お客様は Microsoft Entra 受信プロビジョニング機能を使用してカスタム セキュリティ属性を自動的に設定できます。 この機能を使用すると、人事システムなどの権限のあるソースからカスタム セキュリティ属性の値をソースできます。 カスタム セキュリティ属性のプロビジョニングでは、Workday、SAP SuccessFactors、および API 駆動型プロビジョニングを使用するその他の統合 HR システムのソースがサポートされます。 プロビジョニング ターゲットは、Microsoft Entra ID テナントです。

[Image: カスタム セキュリティ属性アーキテクチャの図。]

### カスタム セキュリティ属性

Microsoft Entra ID のカスタム セキュリティ属性は、Microsoft Entra オブジェクトを定義して割り当てることができるビジネス固有の属性 (キーと値のペア) です。 これらの属性は、情報の格納、オブジェクトの分類、特定の Azure リソースに対する微調整されたアクセス制御の適用に使用できます。 カスタム セキュリティ属性の詳細については、「[Microsoft Entra ID のカスタム セキュリティ属性とは」を参照してください。](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview).

### 前提 条件

カスタム セキュリティ属性をプロビジョニングするには、次の前提条件を満たす必要があります。

- 次のいずれかの受信プロビジョニング アプリを構成するための Microsoft Entra ID Premium P1 ライセンス。
    - [Workday から Microsoft Entra ID へのユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-cloud-only-tutorial)
    - [SAP SuccessFactors から Microsoft Entra ID へのユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-cloud-only-tutorial)
    - [API による Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app) へのプロビジョニング
- 属性マッピングプロセス中に検出するために、テナント内のカスタムセキュリティ属性をアクティブにしてください。 この機能を使用する前に、Microsoft Entra ID テナントに[カスタム セキュリティ属性セットを作成](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-add)する必要があります。 プロビジョニング サービスでは、 `String`、 `Integer`、 `Boolean`の種類のカスタム セキュリティ属性に対して、単一の自由形式と定義済みの値の設定がサポートされています。
- 受信プロビジョニング アプリの属性マッピングでカスタム セキュリティ属性を構成するには、[アプリケーション管理者](https://entra.microsoft.com) の Microsoft Entra ロールと [属性プロビジョニング管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)のロールが割り当てられているユーザーとして、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-provisioning-administrator)にサインインします。
    - プロビジョニング アプリ **作成して更新するには、アプリケーション管理者** が必要です。
    - **属性プロビジョニング管理者** は、プロビジョニング アプリの属性マッピング セクションでカスタム セキュリティ属性を追加または削除する必要があります。

### 既知の制限事項

- 複数値のカスタム セキュリティ属性のプロビジョニングはサポートされていません。
- 非アクティブ化されたカスタム セキュリティ属性のプロビジョニングはサポートされていません。
- [属性ログ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-log-reader) ロールでは、プロビジョニング ログのカスタム セキュリティ属性値を表示できません。

### カスタム セキュリティ属性を使用したプロビジョニング アプリの構成

開始する前に、次の [手順](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-add) に従って、Microsoft Entra ID テナントにカスタム セキュリティ属性を追加し、インバウンド プロビジョニング アプリでカスタム セキュリティ属性をマップします。

#### Microsoft Entra ID テナントでカスタム セキュリティ属性を定義する

[Microsoft Entra 管理センター](https://entra.microsoft.com)で、**Entra ID**&gt;**カスタム セキュリティ属性** からセキュリティ属性を追加するオプションにアクセスします。 このタスクを完了するには、少なくとも **属性定義管理者** ロールが必要です。

この例には、テナントに追加できるカスタム セキュリティ属性が含まれています。 `HRConfidentialData` 属性セットを使用し、次の属性を追加します。

- EEOStatus (String)
- FLSAStatus (String)
- PayGrade (文字列)
- PayScaleType (String)
- IsRehire (ブール値)
- EmployeeLevel (整数)

[Image: カスタム セキュリティアクティブ属性のスクリーンショット。]

#### 受信プロビジョニング アプリでカスタム セキュリティ属性をマップする

1. [アプリケーション管理者](https://entra.microsoft.com) と [属性プロビジョニング管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) ロールのアクセス許可の両方を持つユーザーとして、[Microsoft Entra 管理](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-provisioning-administrator) センターにサインインします。
2. **エンタープライズ アプリケーション**に移動し、受信プロビジョニング アプリを開きます。
3. **[プロビジョニング]** 画面を開きます。

    [Image: プロビジョニングの概要画面のスクリーンショット。]

    手記

    このガイダンスでは、Microsoft Entra ID への API 駆動型プロビジョニングの画面キャプチャを表示します。 Workday または SuccessFactors プロビジョニング アプリを使用している場合は、Workday と SuccessFactors に関連する属性と構成が表示されます。
4. **[プロビジョニングの編集]** を選択します。

    [Image: プロビジョニングの編集画面のスクリーンショット。]
5. **属性マッピング** を選択して、属性マッピング画面を開きます。

    [Image: 属性マッピング画面のスクリーンショット。]
6. 機密性の高い HR データを格納するソース属性を定義し、[ **詳細オプション]** ドロップダウンを選択して属性一覧を開きます。
7. API の [属性の編集] リスト 選択して、テストする属性を特定します。

    [Image: API の属性リストの編集画面のスクリーンショット。]

    - SCIM スキーマ名前空間 () を定義して、`urn:ietf:params:scim:schemas:extension:microsoft:entra:csa` API を使用してカスタム セキュリティ属性のプロビジョニングをテストします。 次の属性を必ず含めます。

        - `urn:ietf:params:scim:schemas:extension:microsoft:entra:csa:EEOStatus`
        - `urn:ietf:params:scim:schemas:extension:microsoft:entra:csa:FLSAStatus`
        - `urn:ietf:params:scim:schemas:extension:microsoft:entra:csa:PayGrade`
        - `urn:ietf:params:scim:schemas:extension:microsoft:entra:csa:PayScaleType`
        - `urn:ietf:params:scim:schemas:extension:microsoft:entra:csa:isRehire`
        - `urn:ietf:params:scim:schemas:extension:microsoft:entra:csa:EmployeeLevel`

    [Image: SCIM スキーマ名前空間オプションのスクリーンショット。]

    手記

    SCIM ペイロード内の機密 HR データを表すために、独自の SCIM スキーマ名前空間を定義できます。 `urn:ietf:params:scim:schemas:extension`で始まるかどうかを確認します。

    - HR ソースとして Workday または SuccessFactors を使用している場合は、API 式を使用して属性リストを更新して、格納する HR データをカスタム セキュリティ属性リストに取得します。
    - SuccessFactors から同じ HR データのセットを取得する場合は、次の API 式を使用します。

        - `$.employmentNav.results[0].jobInfoNav.results[0].eeoClass`
        - `$.employmentNav.results[0].jobInfoNav.results[0].flsaStatus`
        - `$.employmentNav.results[0].jobInfoNav.results[0].payGradeNav.name`
        - `$.employmentNav.results[0].jobInfoNav.results[0].payScaleType`

    [Image: 選択できる API 式のスクリーンショット。]
8. スキーマの変更を保存します。
9. **属性マッピング** 画面で、[**新しいマッピングを追加**] を選択します。

    [Image: [新しいマッピングの追加] オプションのスクリーンショット。]

    - カスタム セキュリティ属性は、`CustomSecurityAttributes.<AttributeSetName>_<AttributeName>`形式で表示されます。
10. 次のマッピングを追加し、変更を保存します。

    | API ソース属性 | Microsoft Entra ID ターゲット属性 |
    | --- | --- |
    | urn:ietf:params:scim:schemas:extension:microsoft:entra:csa:EEOStatus | CustomSecurityAttributes.HRConfidentialData\_EEOStatus |
    | urn:ietf:params:scim:schemas:extension:microsoft:entra:csa:FLSAStatus | カスタムセキュリティ属性.HR（人事）機密データ\_FLSAステータス |
    | urn:ietf:params:scim:schemas:extension:microsoft:entra:csa:PayGrade | カスタムセキュリティ属性.HR機密データ\_給与等級 |
    | urn:ietf:params:scim:schemas:extension:microsoft:entra:csa:PayScaleType | カスタムセキュリティ属性.HR機密データ\_給与尺度タイプ |
    | urn:ietf:params:scim:schemas:extension:microsoft:entra:csa:isRehire | CustomSecurityAttributes.HRConfidentialData\_IsRehire |
    | urn:ietf:params:scim:schemas:extension:microsoft:entra:csa:EmployeeLevel | CustomSecurityAttributes.HRConfidentialData\_EmployeeLevel |

### カスタム セキュリティ属性のプロビジョニングをテストする

HR ソース属性をカスタム セキュリティ属性にマップしたら、次のメソッドを使用して、カスタム セキュリティ属性データのフローをテストします。 選択する方法は、プロビジョニング アプリの種類によって異なります。

- ジョブで Workday または SuccessFactors をソースとして使用する場合は、[オンデマンド プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand) 機能を使用して、カスタム セキュリティ属性のデータ フローをテストします。
- ジョブで API 駆動型プロビジョニングを使用する場合は、SCIM 一括ペイロードをジョブの *bulkUpload* API エンドポイントに送信します。

#### SuccessFactors プロビジョニング アプリを使用してテストする

この例では、次に示すように、SAP SuccessFactors 属性がカスタム セキュリティ属性にマップされます。

[Image: SAP 属性マッピング オプションのスクリーンショット。]

1. SuccessFactors プロビジョニング ジョブを開き、**プロビジョン オンデマンド**を選択します。

[Image: Microsoft Entra ID の概要とオンデマンドプロビジョニングの選択のスクリーンショット。]

1. [**ユーザー** の選択] ボックスに、テストするユーザーの *personIdExternal* 属性を入力します。

    プロビジョニング ログには、設定したカスタム セキュリティ属性が表示されます。

    [Image: [変更された属性] 画面のスクリーンショット。]

    手記

    カスタム セキュリティ属性のソースとターゲットの値は、プロビジョニング ログで編集されます。
2. ユーザーの Microsoft Entra ID プロファイルカスタム セキュリティ属性の画面で、そのユーザーに設定された実際の値を表示できます。 このデータを表示するには、少なくとも 属性割り当て管理者 または属性割り当て閲覧者 ロール 必要があります。

    [Image: カスタム セキュリティ属性画面の [割り当てられた値] 列のスクリーンショット。]

#### API 駆動型プロビジョニング アプリを使用してテストする

1. カスタム セキュリティ属性の値を含む SCIM 一括要求ペイロードを作成します。

    [Image: SCIM 一括要求ペイロード コードのスクリーンショット。]

    - 完全な SCIM ペイロードにアクセスするには、SCIM ペイロードのサンプル参照してください。
2. プロビジョニング ジョブの概要ページから、*bulkUpload* API URL をコピーします。

    [Image: ペイロードのプロビジョニング API エンドポイントのスクリーンショット。]
3. [Graph エクスプローラー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-graph-explorer) または [cURL](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-curl-tutorial)を使用し、SCIM ペイロードを *bulkUpload* API エンドポイントにポストします。

    [Image: ペイロードの API 要求と応答のスクリーンショット。]

    - SCIM ペイロード形式でエラーがない場合は、**承認済み** 状態が表示されます。
    - 数分待ってから、API 駆動型プロビジョニング ジョブのプロビジョニング ログを確認します。
4. カスタム セキュリティ属性は、次の例のように表示されます。

    [Image: カスタム セキュリティ属性エントリのスクリーンショット。]

    手記

    カスタム セキュリティ属性のソースとターゲットの値は、プロビジョニング ログで編集されます。 ユーザーに設定された実際の値を表示するには、ユーザーの Microsoft Entra ID プロファイルに移動します。**カスタム セキュリティ属性** 画面でデータを表示します。 このデータを表示するには、少なくとも属性割り当て管理者ロールまたは属性割り当て閲覧者ロールが必要です。

    [Image: ユーザーの [カスタム セキュリティ属性] 画面のスクリーンショット。]

##### カスタム セキュリティ属性を使用した SCIM ペイロードのサンプル

この SCIM 一括要求のサンプルには、カスタム セキュリティ属性にマップできる拡張 `urn:ietf:params:scim:schemas:extension:microsoft:entra:csa` の下にカスタム フィールドが含まれています。

```json
{
    "schemas": ["urn:ietf:params:scim:api:messages:2.0:BulkRequest"],
    "Operations": [{
            "method": "POST",
            "bulkId": "897401c2-2de4-4b87-a97f-c02de3bcfc61",
            "path": "/Users",
            "data": {
                "schemas": ["urn:ietf:params:scim:schemas:core:2.0:User",
                    "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User",
                    "urn:ietf:params:scim:schemas:extension:microsoft:entra:csa"],
                "id": "2819c223-7f76-453a-919d-413861904646",
                "externalId": "701984",
                "userName": "bjensen@example.com",
                "name": {
                    "formatted": "Ms. Barbara J Jensen, III",
                    "familyName": "Jensen",
                    "givenName": "Barbara",
                    "middleName": "Jane",
                    "honorificPrefix": "Ms.",
                    "honorificSuffix": "III"
                },
                "displayName": "Babs Jensen",
                "nickName": "Babs",
                "emails": [{
                        "value": "bjensen@example.com",
                        "type": "work",
                        "primary": true
                    }
                ],
                "addresses": [{
                        "type": "work",
                        "streetAddress": "234300 Universal City Plaza",
                        "locality": "Hollywood",
                        "region": "CA",
                        "postalCode": "91608",
                        "country": "USA",
                        "formatted": "100 Universal City Plaza\nHollywood, CA 91608 USA",
                        "primary": true
                    }
                ],
                "phoneNumbers": [{
                        "value": "555-555-5555",
                        "type": "work"
                    }
                ],
                "userType": "Employee",
                "title": "Tour Guide",
                "preferredLanguage": "en-US",
                "locale": "en-US",
                "timezone": "America/Los_Angeles",
                "active": true,
                "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User": {
                    "employeeNumber": "701984",
                    "costCenter": "4130",
                    "organization": "Universal Studios",
                    "division": "Theme Park",
                    "department": "Tour Operations",
                    "manager": {
                        "value": "89607",
                        "$ref": "../Users/26118915-6090-4610-87e4-49d8ca9f808d",
                        "displayName": "John Smith"
                    }
                },
                "urn:ietf:params:scim:schemas:extension:microsoft:entra:csa": {
                    "EEOStatus":"Semi-skilled",
                    "FLSAStatus":"Non-exempt",
                    "PayGrade":"IC-Level5",
                    "PayScaleType":"Revenue-based",				"IsRehire": false,				"EmployeeLevel": 64					
                }
            }
        }, {
            "method": "POST",
            "bulkId": "897401c2-2de4-4b87-a97f-c02de3bcfc61",
            "path": "/Users",
            "data": {
                "schemas": ["urn:ietf:params:scim:schemas:core:2.0:User",
                    "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User",
                    "urn:ietf:params:scim:schemas:extension:microsoft:entra:csa" ],
                "id": "2819c223-7f76-453a-919d-413861904646",
                "externalId": "701985",
                "userName": "Kjensen@example.com",
                "name": {
                    "formatted": "Ms. Kathy J Jensen, III",
                    "familyName": "Jensen",
                    "givenName": "Kathy",
                    "middleName": "Jane",
                    "honorificPrefix": "Ms.",
                    "honorificSuffix": "III"
                },
                "displayName": "Kathy Jensen",
                "nickName": "Kathy",
                "emails": [{
                        "value": "kjensen@example.com",
                        "type": "work",
                        "primary": true
                    }
                ],
                "addresses": [{
                        "type": "work",
                        "streetAddress": "100 Oracle City Plaza",
                        "locality": "Hollywood",
                        "region": "CA",
                        "postalCode": "91618",
                        "country": "USA",
                        "formatted": "100 Oracle City Plaza\nHollywood, CA 91618 USA",
                        "primary": true
                    }
                ],
                "phoneNumbers": [{
                        "value": "555-555-5545",
                        "type": "work"
                    }
                ],
                "userType": "Employee",
                "title": "Tour Lead",
                "preferredLanguage": "en-US",
                "locale": "en-US",
                "timezone": "America/Los_Angeles",
                "active": true,
                "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User": {
                    "employeeNumber": "701984",
                    "costCenter": "4130",
                    "organization": "Universal Studios",
                    "division": "Theme Park",
                    "department": "Tour Operations",
                    "manager": {
                        "value": "89607",
                        "$ref": "../Users/26118915-6090-4610-87e4-49d8ca9f808d",
                        "displayName": "John Smith"
                    }
                },
                "urn:ietf:params:scim:schemas:extension:microsoft:entra:csa": {
                    "EEOStatus":"Skilled",
                    "FLSAStatus":"Exempt",
                    "PayGrade":"Manager-Level2",
                    "PayScaleType":"Profit-based",				"IsRehire": true,				"EmployeeLevel": 63
                }
                
            }
        }
    ],
    "failOnErrors": null
}
```

### ハイブリッド ユーザーのカスタム セキュリティ属性をプロビジョニングする

ハイブリッド ユーザーは、最初にオンプレミスの Active Directory で人事システムからプロビジョニングされた後、Entra Connect Sync または Cloud Sync を使用して Microsoft Entra ID に同期されます。カスタム セキュリティ属性はハイブリッド ユーザーに割り当てることができます。これらの属性は、ハイブリッド ユーザーの Microsoft Entra ID プロファイルにのみ存在します。

このセクションでは、ハイブリッド ユーザーのカスタム セキュリティ属性を自動プロビジョニングするためのプロビジョニング トポロジについて説明します。 信頼できる HR ソースとして Workday が使用されます。 ただし、SuccessFactors と API 主導のプロビジョニングでも、同じトポロジを使用できます。

たとえば、あなたは、ID のレコードの人事システムとして Workday を使用するとします。 Workday から提供されるハイブリッド ユーザーにカスタム セキュリティ属性を設定するには、次の 2 つのプロビジョニング アプリを構成します。

- **Workday からオンプレミス Active Directory へのプロビジョニング:** このプロビジョニング アプリは、オンプレミスの Active Directory でハイブリッド ユーザーを作成および更新します。 Workday からの通常の属性のみを処理します。
- **Workday から Microsoft Entra ID へのプロビジョニング:** このプロビジョニング アプリを**更新**操作のみを処理し、カスタム セキュリティ属性のみをターゲット属性として含むように属性マッピングを制限するように構成します。

このトポロジでは、エンドツーエンドのフローのしくみを次に示します。

[Image: ハイブリッド ユーザーに対するカスタム セキュリティ属性マッピングのしくみのフロー図。]

1. **Workday-to-AD プロビジョニング** アプリは、Workday からコア ユーザー プロファイルをインポートします。
2. アプリは、従業員 ID を一致する識別子として使用して、オンプレミスの Active Directory でユーザー アカウントを作成または更新します。
3. Microsoft Entra Connect Sync/Cloud Sync は、ユーザー プロファイルを Microsoft Entra ID に同期します。
4. Workday ライトバックを構成した場合、メールまたは電話番号の情報が Workday に書き戻されます。
5. **Workday から Microsoft Entra ID へのプロビジョニング** アプリは、更新のみを処理し、機密属性をカスタム セキュリティ属性として設定するように構成されています。 **[詳細オプション]** ドロップダウンの下の **[スキーマの編集]** を使用して、このシナリオに関係のない`accountEnabled`や`isSoftDeleted`などの既定の属性マッピングを削除します。

[Image: ハイブリッド ユーザーの属性マッピングのスクリーンショット。]

この構成では、オンプレミスの Active Directory から Microsoft Entra ID に同期されるハイブリッド ユーザーにカスタム セキュリティ属性が割り当てられます。

手記

上記の構成では、特定の順序で完了するために 3 つの異なる同期サイクルに依存しています。 ハイブリッド ユーザー プロファイルが Microsoft Entra ID で使用できない場合、**Workday-to-Microsoft Entra ID プロビジョニング** ジョブが実行されると、更新操作は失敗し、次の実行中に再試行されます。 **API 駆動型プロビジョニングから Microsoft Entra ID** アプリを使用している場合は、カスタム セキュリティ属性更新プログラムの実行タイミングをより適切に制御できます。

### カスタム セキュリティ属性のプロビジョニングに対する API アクセス許可

この機能では、次の新しいGraph APIアクセス許可が導入されています。 この機能を使用すると、サインインしているユーザーに代わって、カスタム セキュリティ属性マッピングを含むプロビジョニング アプリ スキーマに直接アクセスして変更できます。

1. **CustomSecAttributeProvisioning.ReadWrite.All**: このアクセス許可は、カスタム セキュリティ属性を含む属性マッピングを読み書きする機能を呼び出し元アプリに付与します。 カスタム セキュリティ属性マッピングを含むプロビジョニング アプリを編集するには、`Application.ReadWrite.OwnedBy`、`Synchronization.ReadWrite.All`、または `Application.ReadWrite.All` (最小から最高の特権) を持つこのアクセス許可が必要です。 このアクセス許可を使用すると、カスタム セキュリティ属性を含む完全なスキーマを取得し、カスタム セキュリティ属性を使用してスキーマを更新またはリセットできます。
2. **CustomSecAttributeProvisioning.Read.All**: このアクセス許可は、呼び出し元アプリに属性マッピングとカスタム セキュリティ属性を含むプロビジョニング ログを読み取る機能を付与します。 保護されたリソースのカスタム セキュリティ属性の名前と値を表示するには、`Synchronization.Read.All` または `Application.Read.All` (最小から最高の特権) を持つこのアクセス許可が必要です。

アプリに `CustomSecAttributeProvisioning.ReadWrite.All` アクセス許可または `CustomSecAttributeProvisioning.Read.All` アクセス許可がない場合、カスタム セキュリティ属性を含むプロビジョニング アプリにアクセスまたは変更することはできません。 代わりに、エラー メッセージまたは編集されたデータが表示されます。

### カスタム セキュリティ属性のプロビジョニングのトラブルシューティング

| 問題 | トラブルシューティングの手順 |
| --- | --- |
| カスタム セキュリティ属性が *ターゲット属性のマッピング ドロップダウン リスト* に表示されません。 | - カスタム セキュリティ属性をサポートするプロビジョニング アプリにカスタム セキュリティ属性を追加していることを確認します。  - ログインしているユーザーに、属性プロビジョニング管理者 (編集アクセス用) または属性プロビジョニング閲覧者 (ビュー アクセス用) ロールが割り当てられていることを確認します。 |
| プロビジョニング アプリ スキーマをリセットまたは更新すると、エラーが返されます。 `HTTP 403 Forbidden - InsufficientAccountPermission Provisioning schema has custom security attributes. The account does not have sufficient permissions to perform this operation.` | ログインしているユーザーに、属性プロビジョニング管理者ロールが割り当てられていることを確認します。 |
| 属性マッピングに存在するカスタム セキュリティ属性を削除できません。 | ログインしているユーザーに、属性プロビジョニング管理者ロールが割り当てられていることを確認します。 |
| 属性マッピング テーブルには、ソース属性とターゲット属性の下に文字列 `redacted` が表示される行があります。 | この動作は、ログインしているユーザーが [属性プロビジョニング管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-provisioning-administrator) または [属性プロビジョニング閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-provisioning-reader) のロールを持っていない場合、設計どおりのものです。 これらのロールのいずれかを割り当てると、カスタム セキュリティ属性マッピングが表示されます。 |
| エラー `The provisioning service does not support setting custom security attributes of type boolean and integer. Unable to set CSA attribute`返されました。 | プロビジョニング アプリ属性マッピングから整数/ブール型のカスタム セキュリティ属性を削除します。 |
| エラー `The provisioning service does not support setting custom security attributes that are deactivated. Unable to set CSA attribute <attribute name>`返されました。 | 非アクティブ化されたカスタム セキュリティ属性を更新しようとしました。 プロビジョニング アプリ属性マッピングから非アクティブ化されたカスタム セキュリティ属性を削除します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/provision-on-demand"} -->
## Microsoft Entra プロビジョニング サービスを使用してユーザーまたはグループをオンデマンドでプロビジョニングする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: Microsoft Entra ID でユーザーをオンデマンドでプロビジョニングする方法について説明します。

数秒でユーザーまたはグループをプロビジョニングするには、オンデマンド プロビジョニングを使用します。 この機能を使用すると、特に以下のことが可能です。

- 構成の問題を迅速にトラブルシューティングする。
- 定義した式を検証する。
- スコープ フィルターをテストする。

### オンデマンド プロビジョニングの使用方法

1. [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/overview-assign-app-owners)または[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference.md#application-administrator)として[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。

::: zone pivot="app-provisioning"

1. **Entra ID**&gt;**Enterprise アプリ**に移動し&gt;アプリケーションを選択します。
2. **プロビジョニング**を選択します。

::: zone-end

::: zone pivot="cross-tenant-synchronization"

1. **[Entra ID]**&gt;**[外部 ID]**&gt;**[テナント間同期]**&gt;**[構成]** に移動します

注

テナント間同期は、現在 [、外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/overview-customers-ciam)ではサポートされていません。

1. 構成を選択し、[ **プロビジョニング** の構成] ページに移動します。

::: zone-end

1. 管理者の資格情報を指定してプロビジョニングを構成します。
2. **[Provision on demand] (オンデマンドでプロビジョニングする)** を選択します。
3. 名、姓、表示名、ユーザー プリンシパル名、またはメール アドレスでユーザーを検索します。 または、グループを検索し、最大 5 人のユーザーを選択することもできます。

    注

    クラウド HR プロビジョニング アプリ (Workday/SuccessFactors から Active Directory/Microsoft Entra ID) の場合、入力値は異なります。 Workday のシナリオの場合は、Workday でのユーザーの "WorkerID" または "WID" を指定してください。 SuccessFactors のシナリオの場合は、SuccessFactors でのユーザーの "personIdExternal" を指定してください。
4. ページの下部にある [ **プロビジョニング** ] を選択します。

    [Image: ユーザーをオンデマンドでプロビジョニングするための Microsoft Entra 管理センター UI を示すスクリーンショット。]

### プロビジョニングの手順について

ユーザーをプロビジョニングするときには、オンデマンド プロビジョニング プロセスによって、プロビジョニング サービスで実行される手順の表示が試みられます。 ユーザーをプロビジョニングする手順は、通常は 5 つあります。 オンデマンド プロビジョニングを進める間に、以降のセクションで説明する手順のうち 1 つ以上が表示されます。

#### 手順 1:接続テスト

プロビジョニング サービスにより、"テスト ユーザー" に対する要求が作成され、ターゲット システムへのアクセスの承認が試みられます。 プロビジョニング サービスでは、サービスがプロビジョニング手順の続行を承認したことを示す応答が想定されています。 このステップは、失敗時にのみ表示されます。 手順が成功した場合は、オンデマンド プロビジョニング エクスペリエンスの間に表示されません。

##### トラブルシューティングのヒント

- ターゲット システムに対して、シークレット トークンやテナント URL などの有効な資格情報を指定したことを確認します。 必要な資格情報はアプリケーションによって異なります。 詳細な構成チュートリアルについては、チュートリアルの [一覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)を参照してください。
- ターゲット システムで、[ **属性マッピング** ] ウィンドウで定義されている一致する属性のフィルター処理がサポートされていることを確認します。 サポートされているフィルターを知るために、アプリケーション開発者によって提供される API ドキュメントを調べる必要が生じることがあります。
- クロスドメイン ID 管理システム (SCIM) アプリケーションの場合は、cURL などの REST API ツールを使用できます。 このようなツールを利用すると、承認要求に対して、Microsoft Entra プロビジョニング サービスで想定されている方法で確実にアプリケーションを応答させることができます。 [要求の例](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups#request-3)を見てみましょう。

#### 手順 2:ユーザーをインポートする

次に、プロビジョニング サービスにより、ソース システムからユーザーが取得されます。 サービスが取得するユーザー属性は、以下のために後で使用されます。

- ユーザーがプロビジョニングのスコープ内にあるかどうかを評価します。
- 既存のユーザーがいないかターゲット システムを調べます。
- ターゲット システムにどのユーザー属性をエクスポートするかを決定します。

##### 詳細の表示

[ **詳細の表示]** セクションには、ソース システムからインポートされたユーザーのプロパティ (Microsoft Entra ID など) が表示されます。

##### トラブルシューティングのヒント

- ソース システム内のユーザー オブジェクトに一致する属性がない場合、ユーザーのインポートは失敗する可能性があります。 このエラーを解決するには、以下のいずれかの方法を試してください。

    - 一致している属性の値を使用して、ユーザー オブジェクトを更新します。
    - プロビジョニングの構成で、一致する属性を変更します。
- インポートされた一覧に、予期した属性がない場合は、ソース システムのユーザー オブジェクトで、属性に値があることを確認します。 プロビジョニング サービスでは、現在、null 属性のプロビジョニングはサポートされていません。
- プロビジョニング構成の **[属性マッピング** ] ページに、想定される属性が含まれていることを確認します。

#### 手順 3:ユーザーがスコープ内にあるかどうかを確認する

次に、プロビジョニング サービスは、ユーザーがプロビジョニングの [スコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works#scoping) 内にあるかどうかを判断します。 サービスによって考慮されるのは以下のような側面です。

- ユーザーがアプリケーションに割り当てられているかどうか。
- スコープが [ **割り当てられた同期** ] または [ **すべて同期]** に設定されているかどうか。
- プロビジョニング構成で定義されているスコープ フィルター。

##### 詳細の表示

[ **詳細の表示]** セクションには、評価されたスコープ条件が表示されます。 以下のプロパティのうち、1 つまたは複数が表示される可能性があります。

- **ソース システムでアクティブ** は、ユーザーがプロパティ `IsActive` Microsoft Entra ID で **true** に設定されていることを示します。
- **アプリケーションに割り当てられる** と、ユーザーが Microsoft Entra ID でアプリケーションに割り当てられていることを示します。
- **スコープ同期の全体**は、スコープ設定によりテナント内のすべてのユーザーとグループが許可されていることを示しています。
- **ユーザーが必要なロールを持っている場合は** 、ユーザーがアプリケーションにプロビジョニングするために必要なロールを持っていることを示します。
- **スコープ フィルター** は、アプリケーションのスコープ フィルターを定義している場合にも表示されます。 フィルターは、{スコープ フィルターのタイトル} {スコープ フィルターの属性} {スコープ フィルターの演算子} {スコープ フィルターの値} の形式で表示されます。

##### トラブルシューティングのヒント

- 有効なスコープ ロールを定義済みであることを確認します。 たとえば、 [Greater_Than演算子](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts#create-a-scoping-filter) を整数以外の値で使用しないでください。
- ユーザーに必要なロールがない場合は、 [既定のアクセス ロールに割り当てられているユーザーをプロビジョニングするためのヒントを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-config-problem-no-users-provisioned#provisioning-users-assigned-to-the-default-access-role)確認します。

#### 手順 4:ソースとターゲット間でユーザーを照合する

この手順では、インポート手順で取得されたユーザーと、ターゲット システム内のユーザーの照合が、サービスによって試みられます。

##### 詳細の表示

[ **詳細の表示]** ページには、ターゲット システムで一致したユーザーのプロパティが表示されます。 コンテキスト ウィンドウは次のように変更されます。

- ターゲット システム内で一致するユーザーがなければ、プロパティは表示されません。
- ターゲット システム内で一致するユーザーが 1 人の場合は、そのユーザーのプロパティが表示されます。
- 複数のユーザーが一致する場合は、両方のユーザーのプロパティが表示されます。
- 複数の一致する属性が属性マッピングに含まれる場合、一致する属性それぞれが順次評価されて、その属性について一致したユーザーが表示されます。

##### トラブルシューティングのヒント

- プロビジョニング サービスで、ソース システム内のユーザーとターゲット内のユーザーを、一意に一致させることができない場合があります。 この問題は、一致する属性が一意のものであるようにすることで解決できます。
- 一致する属性として定義されている属性に基づくフィルター処理が、ターゲット システムでサポートされていることを確認してください。

#### 手順 5:アクションを実行する

最後に、プロビジョニング サービスにより、ユーザーの作成、更新、削除、スキップなどのアクションが実行されます。

次に、ユーザーのオンデマンド プロビジョニングが成功した後に表示される内容の例を示します。

[Image: ユーザーのオンデマンド プロビジョニングが成功したことを示すスクリーンショット。]

##### 詳細の表示

[ **詳細の表示]** セクションには、ターゲット システムで変更された属性が表示されます。 この表示は、プロビジョニング サービスのアクティビティの最終出力と、エクスポートされた属性を表しています。 この手順が失敗した場合、表示された属性は、プロビジョニング サービスが変更を試みた属性を表します。

##### トラブルシューティングのヒント

- 変更のエクスポートの失敗は、大きく異なる可能性があります。 一般的 [なエラーについては、プロビジョニング ログのドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-analyze-provisioning-logs#error-codes) を参照してください。
- オンデマンド プロビジョニングでは、グループまたはユーザーはアプリケーションに割り当てられないため、プロビジョニングできません。 オブジェクトがアプリケーションに割り当てられてから、その割り当てがオンデマンド プロビジョニングによって受け入れられるまでの間に、最大数分のレプリケーション遅延があります。 数分待ってからやり直す必要がある場合があります。

### よく寄せられる質問

- **オンデマンド プロビジョニングを使用するには、プロビジョニングをオフにする必要がありますか?** 有効期間の長いベアラー トークン、またはユーザー名とパスワードを承認に使用するアプリケーションでは、これ以降の手順は必要ありません。 承認のために OAuth を使用するアプリケーションでは、現在のところ、オンデマンド プロビジョニングを使用する前にプロビジョニング ジョブを停止する必要があります。 G Suite、Box、Slack などのアプリケーションは、このカテゴリに分類されます。 プロビジョニング ジョブを停止する必要なく、すべてのアプリケーションでオンデマンド プロビジョニングをサポートするための作業が進行中です。
- **オンデマンド プロビジョニングには、どのくらいの時間がかかりますか?** オンデマンド プロビジョニングの所要時間は、通常 30 秒未満です。

### 既知の制限

現時点では、オンデマンド プロビジョニングにはいくつかの既知の制限事項があります。 次に行う改善点をより適切に判断できるように、 [ご提案やフィードバック](https://aka.ms/appprovisioningfeaturerequest) を投稿してください。

::: zone pivot="app-provisioning"

注

以下の制限事項は、オンデマンド プロビジョニング機能に固有のものです。 アプリケーションがプロビジョニングのグループ、削除、またはその他の機能をサポートしているかどうかについては、そのアプリケーションのチュートリアルを確認してください。

- グループのオンデマンド プロビジョニングでは、一度に最大 5 人のメンバーの更新がサポートされます。 テナント間同期や Workday などのコネクタでは、グループのプロビジョニングはサポートされていないため、結果としてグループのオンデマンド プロビジョニングはサポートされません。
- オンデマンド プロビジョニング要求 API では、最大 5 人のメンバーがいるグループを一度に 1 つだけ受け入れることができます。

::: zone-end

::: zone pivot="cross-tenant-synchronization"

- テナント間同期では、グループのオンデマンド プロビジョニングはサポートされていません。

::: zone-end

- オンデマンド プロビジョニングでは、Microsoft Entra 管理センターを使用して一度に 1 人のユーザーをプロビジョニングできます。
- ターゲット テナント内でオンデマンド プロビジョニングを使用して以前に論理的に削除されたユーザーの復元は、サポートされません。 オンデマンド プロビジョニングを使用してユーザーを論理的に削除した後、そのユーザーを復元しようとすると、ユーザーが重複する可能性があります。
- ロールのオンデマンド プロビジョニングはサポートされていません。
- オンデマンド プロビジョニングでは、アプリケーションから割り当て解除されたユーザーの無効化をサポートしています。 ただし、Microsoft Entra ID から無効化または削除されたユーザーの無効化または削除はサポートしていません。 これらのユーザーは、ユーザーを検索するときに表示されません。
- オンデマンド プロビジョニングでは、アプリケーションに直接割り当てられていない、入れ子になったグループはサポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/provision-user-with-expression-builder"} -->
## 式ビルダーを使用してユーザーをプロビジョニングする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-user-with-expression-builder
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: Expression Builder を使用してユーザー プロビジョニングを簡略化し、重複するユーザーを処理し、シームレスな統合のためにユーザー属性を変換する方法について説明します。

Microsoft Entra ID を使用するエンタープライズ アプリケーションに重複するユーザーが存在する場合など、ユーザー プロビジョニングが困難な場合があります。 式ビルダー ツールを使用すると、このプロセスが簡略化され、ユーザー属性を柔軟に変換およびマップできます。

既定では、ユーザー プロビジョニングは UPN に基づいて行われます。 ただし、お客様が従来の方法を使用している場合があり、UPN は a.b@onmicrosoft.com や a\_b@onmicrosoft.comなどの形式にすることができます。

このような状況が発生した場合、アプリケーション側でユーザー プロビジョニングエラーが発生する可能性があります。 たとえば、GitHub Copilot (GHCP) のような一部のアプリケーションでは、ユーザーを a-b-test 形式でプロビジョニングします。ここで、"a"  UPN では名、**"b"** は 2 番目の名前、**テスト** は GHCP インスタンス名です。

ユーザーの a.b@onmicrosoft.com と a\_b@onmicrosoft.comがある場合、GHCP は 1 人のユーザーをプロビジョニングし、もう一方のユーザーは重複のために失敗します。

Expression Builder は、組織のニーズに基づいて属性変換とマッピングを有効にすることで、ユーザー プロビジョニングを簡略化します。 柔軟性と精度を提供しますが、運用環境での実装を成功させるには、適切なテストと検証が不可欠です。

### シナリオの例

次に示すのは、一意の属性マッピングとユーザー プロビジョニングの課題を処理するためのシナリオとサンプル式です。

#### シナリオ 1: 重複せずに一意のユーザー名を確保する

セキュリティのために従業員 ID  などの一意の属性が使用できない場合や制限されている場合、**オブジェクト ID** は信頼できるオプションとして機能します。 **オブジェクト ID** は、ユーザーごとにグローバルに一意であり、重複が発生しません。

手記

各アプリケーションには、ユーザー プロビジョニングの文字制限があります。 たとえば、GitHub の文字制限は 39 です。

**式:**

Append(Mid([displayName], 1, 15), Mid([objectId], 1, 8))

**説明:**

- ユーザーの表示名の最初の 15 文字を取得します。
- ユーザーの **オブジェクト ID**の最初の 8 文字を追加します。
- 2 つを組み合わせて一意の識別子を作成します。

**の利点:**

- オブジェクト ID **によって一意性**保証されます。
- 重複のリスクを軽減します。

#### シナリオ 2: 互換性のためにユーザー プリンシパル名 (UPN) を変更する

一部のシステムでは、適切な統合のために UPN を変更する必要があります。 たとえば、オブジェクト ID の一部を追加すると、一意性が確保されます。

**式:**

Append(Mid([objectId], 1, 2), Mid([userPrincipalName], 1, 50))

**説明:**

- **オブジェクト ID**から最初の 2 文字を抽出します。
- 既存の UPN にこれらの文字を追加します (最初の 50 文字に制限されます)。ほとんどのユーザーは通常、この制限に達する UPN を持っていませんが、一意性を得るために、**オブジェクト ID** から完全な UPN) に 2 文字を追加することを目的としています。

#### シナリオ 3: 特定のユーザーの条件付き変更

ユーザーのサブセットにカスタム変更を適用するには、IIF ステートメントを使用した条件付きロジックを使用できます。

**式:**

IIF([userPrincipalName] = "Aman.Gupta@xpl57.onmicrosoft.com", Append(Mid([objectId], 1, 2), [userPrincipalName]), IIF( [userPrincipalName] = "Aarti@xpl57.onmicrosoft.com", Append(Mid([objectId], 1, 2), [userPrincipalName]), IIF([userPrincipalName] = "AdeleV@xpl57.onmicrosoft.com", Append(Mid([objectId], 1, 2), [userPrincipalName]), [userPrincipalName]) ) )

この式は、UPN に基づいて特定のユーザーをチェックし、**オブジェクト ID** の最初の 2 文字を userPrincipalName に追加して、Aman.Gupta@xpl57.onmicrosoft.com、Aarti@xpl57.onmicrosoft.com、および AdeleV@xpl57.onmicrosoft.comにカスタム変更を適用します。

**テスト ケースの例:**

- 入力 UPN: Aman.Gupta@xpl57.onmicrosoft.com
- 予想される出力: 39Aman.Gupta@xpl57.onmicrosoft.com

**より高度な例を次に示します。**

IIF([userPrincipalName] = "Aman.Gupta@xpl57.onmicrosoft.com", Append([displayName], Append("", Mid([objectId], 1, 8)))、IIF([userPrincipalName] = "harjit@xpl57.onmicrosoft.com", Append([displayName], Append(", Mid([objectId], 1, 8))), IIF( [userPrincipalName] = "AdeleV@xpl57.onmicrosoft.com", Append([displayName], Append("\_", Mid([objectId], 1, 8))), [userPrincipalName] ) )

テスト用の **式:**

Append(Mid([objectId], 1, 8), [userPrincipalName])

このプロセスでは、UPN に基づいて特定のユーザーをチェックし、表示名に  オブジェクト ID の最初の 8 文字を追加することで、カスタム変更を適用します。 含まれるユーザーが多いほど、式が長くなり、複雑さが増します。 したがって、この方法は、ユーザーの小さなサブセットに最適です。

### 主な推奨事項

- **広範なテスト:** 非運用環境で常に式をテストして機能を検証します。
- **文字の制限:** 変換されたユーザー名がアプリケーションで指定された文字制限を超えないようにします (たとえば、GitHub には 39 文字の制限があります)。
- **式の制限事項:** 1 つの属性マッピング式でサポートされる最大長は **10,000 文字です**。
- **ドキュメント:** トラブルシューティングと今後の参照のために、式と変更の明確なドキュメントを保持します。

### テスト用のツールと手順

1. Entra ID または関連するプロビジョニング インターフェイスで式ビルダーを開きます。
2. サンプル ユーザー データでテスト式機能を使用します。
3. 出力を検証して、適切な変換を確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/provisioning-agent-release-version-history"} -->
## Microsoft Entra Connect プロビジョニング エージェント - バージョンリリース履歴 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provisioning-agent-release-version-history
- Service: entra-id / app-provisioning
- Article date: 2026-04-14
- Summary: この記事では、Microsoft Entra Connect プロビジョニング エージェントのすべてのリリースの一覧を示し、新機能と修正された問題について説明します。

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

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/provisioning-workbook"} -->
## プロビジョニング分析情報ブック - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provisioning-workbook
- Service: entra-id / app-provisioning
- Article date: 2025-04-09
- Summary: この記事では、プロビジョニングの Azure Monitor ブックについて説明します。

プロビジョニング ブックにより、データ分析のための柔軟なキャンバスが提供されます。 このブックには、さまざまなソースからのすべてのプロビジョニング ログがまとめられ、1 つの領域で分析情報を得ることができます。 ブックを使うと、Azure portal 内で高度なビジュアルのレポートを作成できます。 詳細については、[Microsoft Entra ブックの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-workbooks)に関するページを参照してください。

このブックは、プロビジョニングを使用してさまざまなデータ ソースからさまざまなデータ リポジトリにユーザーを同期するハイブリッド ID 管理者を対象としています。 管理者は、これを利用して同期の状態や詳細に関する分析情報を得ることができます。

このブックでは:

- すべてのプロビジョニング ソースからターゲットに同期されたユーザーとグループの同期の概要が提供されます
- プロビジョニング ログによって取り込まれた情報の集約された詳細なビューが提供されます。
- 特定のニーズに合わせてデータをカスタマイズできます

### プロビジョニング ログの有効化

Azure Monitoring と Log Analytics については既に理解している必要があります。 そうでない場合は、別のページでそれらについて学習してから、このページに戻ってアプリケーションのプロビジョニング ログについて学習してください。 Azure Monitoring の詳細については、「[Azure Monitor の概要](https://learn.microsoft.com/ja-jp/azure/azure-monitor/overview)」を参照してください。 Azure Monitor のログと Log Analytics の詳細については、[Azure Monitor のログ クエリの概要](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/log-query-overview)と[クラウド同期のトラブルシューティングのためのプロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-troubleshoot)に関する記事を参照してください。

### ソースおよびターゲット

ブックの上部にあるドロップダウンを使用して、ソース ID とターゲット ID を指定します。

これらのフィールドは、ID のソースとターゲットです。 表示される残りのフィルターは、ソースとターゲットの選択に基づきます。 検索がより詳細になるように、追加のフィールドを使用して検索の範囲を設定できます。 クエリのリファレンスとして、次の表を使用してください。

たとえば、クラウド同期ワークフローのデータを確認する場合、ソースは Active Directory になり、ターゲットは Microsoft Entra ID になります。

注

ソースとターゲットは必須です。 ソースとターゲットを選択しない場合、データは表示されません。

[Image: フィールドのスクリーンショット。]

| フィールド | 説明 |
| --- | --- |
| Source | プロビジョニングのソース リポジトリ |
| Target | プロビジョニングのターゲット リポジトリ |
| 時間の範囲 | 表示するプロビジョニング情報の範囲。 これは、4 時間から 90 日の間で、任意の値を指定できます。 また、カスタム値も設定できます。 |
| Status | "成功" や "スキップ済み" などのプロビジョニングの状態が表示されます。 |
| アクション | "削除" や "削除" など、実行されたプロビジョニング アクションが表示されます。 |
| アプリケーション名 | アプリケーション名でフィルター処理できます。 Active Directory の場合、ドメインでフィルター処理できます。 |
| ジョブ ID | 特定のジョブ ID をターゲットにすることができます。 |
| 同期の種類 | オブジェクトやパスワードなど、同期の種類でフィルター処理します。 |

注

[同期の概要]、[同期の詳細]、[Sync Details by grid] (グリッドごとの同期の詳細) のすべてのグラフとグリッドは、ソース、ターゲット、パラメーターの選択に基づいて変更されます。

### 同期の概要

同期の概要セクションには、組織の同期アクティビティの概要が表示されます。 たとえば、次のようなアクティビティです。

- 同期されたオブジェクトの種類別の合計
- アクション別のプロビジョニング イベント
- 状態別のプロビジョニング イベント
- 一意の同期数 (状態別)
- プロビジョニング成功率
- 上位のプロビジョニング エラー

[Image: 同期の概要のスクリーンショット。]

### 同期の詳細

同期の詳細タブでは、同期データをドリルインして詳細情報を取得できます。 この情報には、以下が含まれます。

- 状態別のオブジェクト同期
- アクション別の同期済みオブジェクト
- 同期ログの詳細

注

グリッドは上記のいずれかのフィルターでフィルター処理できますが、**[状態別の同期済みオブジェクト数]** と **[アクション]** でタイルをクリックすることもできます。

[Image: 同期の詳細のスクリーンショット。]

さらに同期ログをドリルインして追加情報を得ることができます。

注

ソース ID をクリックすると、詳細が表示され、同期されたオブジェクトに関する詳細情報が提供されます。

### Sync details by cycle (サイクル別の同期の詳細)

[sync details by cycle] (サイクル別の同期の詳細) タブを使用すると、同期データをより細かく確認できます。 この情報には、以下が含まれます。

- 状態別のオブジェクト同期
- アクション別の同期済みオブジェクト
- 同期ログの詳細

[Image: [synchronization details by cycle] (サイクル別の同期の詳細) タブのスクリーンショット。]

さらに同期ログをドリルインして追加情報を得ることができます。

注

グリッドは上記のいずれかのフィルターでフィルター処理できますが、**[状態別の同期済みオブジェクト数]** と **[アクション]** でタイルをクリックすることもできます。

### シングル ユーザー ビュー

[user provisioning view] (ユーザー プロビジョニング ビュー) タブでは、個々のユーザーの同期データを取得できます。

注

このセクションには、ソースとターゲットの使用は関係しません。

このセクションでは、時間範囲を入力し、特定のユーザーを選択して、ユーザーがプロビジョニングまたはプロビジョニング解除されたアプリケーションを確認します。

時間範囲を選択すると、その時間範囲内にイベントを発生させたユーザーがフィルターにより抽出されます。

特定のユーザーを対象にするために、そのユーザーについて次のいずれかのパラメーターを追加できます。

- UPN
- UserID

[Image: シングル ユーザー ビューのスクリーンショット。]

### 説明

**[同期の詳細]** ビューまたは **[Sync details by cycle] (サイクル別の同期の詳細)** ビューでソース ID をクリックすると、同期されたオブジェクトに関する追加情報を確認できます。

[Image: オブジェクトの詳細のスクリーンショット。]

### カスタム クエリ

カスタム クエリを作成し、Azure ダッシュボードにデータを表示することができます。 方法については、[Log Analytics データのダッシュボードの作成と共有](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/get-started-queries)に関するページを参照してください。 また、[Azure Monitor のログ クエリの概要](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/log-query-overview)に関するページも参照してください。

### カスタム アラート

Azure Monitor を使用すると、プロビジョニングに関連する主要なイベントに関する通知を受け取ることができるように、カスタム アラートを構成することができます。 たとえば、障害が急増した場合にアラートを受け取ることができます。 また、無効または削除が急増することもあります。 アラートが必要なもう 1 つの例として、プロビジョニングがないことがあります。これは、何かがうまく行っていないことを示します。

アラートについて詳しくは、[Azure Monitor のログ アラート](https://learn.microsoft.com/ja-jp/azure/azure-monitor/alerts/alerts-create-new-alert-rule)に関するページをご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/sap-successfactors-attribute-reference"} -->
## Microsoft Entra ID の SAP SuccessFactors 属性リファレンス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/sap-successfactors-attribute-reference
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: Microsoft Entra ID での SuccessFactors-HR ドリブン プロビジョニングでサポートされる SuccessFactors の属性について説明します。

この記事では、次の情報を確認できます。

- SuccessFactors エンティティと属性
- 既定の属性マッピング

### サポートされている SuccessFactors エンティティと属性

次の表は、次の 2 つのプロビジョニング アプリに既定で含まれる SuccessFactors 属性の一覧を示しています。

- [SuccessFactors から Active Directory ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial)
- [SuccessFactors から Microsoft Entra へのユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-cloud-only-tutorial)

追加の属性については、 [SAP SuccessFactors 統合リファレンスを参照](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/sap-successfactors-integration-reference#retrieving-more-attributes) してスキーマを拡張してください。

| # | SuccessFactors エンティティ | SuccessFactors 属性 | 操作の種類 |
| --- | --- | --- | --- |
| 1 | PerPerson | 外部ユーザーID | お読みください |
| 2 | PerPerson | personId | お読みください |
| 3 | PerPerson | perPersonUuid | お読みください |
| 4 | PerPersonal | ディスプレイ名 | お読みください |
| 5 | PerPersonal | ファーストネーム | お読みください |
| 6 | PerPersonal | ジェンダー | お読みください |
| 7 | PerPersonal | 苗字 | お読みください |
| 8 | PerPersonal | ミドルネーム | お読みください |
| 9 | PerPersonal | preferredName | お読みください |
| 10 | ユーザー | アドレスライン1 | お読みください |
| 11 | ユーザー | アドレスライン2 | お読みください |
| 12 | ユーザー | addressLIne3 | お読みください |
| 13 | ユーザー | ビジネス電話 | お読みください |
| 14 | ユーザー | cellPhone | お読みください |
| 15 | ユーザー | 都市 | お読みください |
| 16 | ユーザー | 国 | お読みください |
| 十七 | ユーザー | custom01 | お読みください |
| 18 | ユーザー | custom02 | お読みください |
| 19 | ユーザー | custom03 | お読みください |
| 20 | ユーザー | custom04 | お読みください |
| 21 (二十一) | ユーザー | custom05 | お読みください |
| 22 | ユーザー | custom06 | お読みください |
| 23 | ユーザー | custom07 | お読みください |
| 二十四 | ユーザー | custom08 | お読みください |
| 二十五 | ユーザー | custom09 | お読みください |
| 26 | ユーザー | custom10 | お読みください |
| 二十七 | ユーザー | custom11 | お読みください |
| 28 | ユーザー | custom12 | お読みください |
| 二十九 | ユーザー | custom13 | お読みください |
| 30 | ユーザー | custom14 | お読みください |
| 31 | ユーザー | empId | お読みください |
| 32 | ユーザー | 自宅電話 | お読みください |
| 33 | ユーザー | jobFamily | お読みください |
| 34 | ユーザー | ニックネーム | お読みください |
| 35 | ユーザー | 状態 | お読みください |
| 36 | ユーザー | タイムゾーン | お読みください |
| 37 | ユーザー | ユーザー名 | お読みください |
| 三十八 | ユーザー | 郵便番号 | お読みください |
| 39 | PerPhone | areaCode | お読みください |
| 40 | PerPhone | countryCode (国コード) | お読みください |
| 41 | PerPhone | 延長 | お読みください |
| 42 | PerPhone | 電話番号 | お読みください |
| 43 | PerPhone | 電話の種類 | お読みください |
| 44 | PerEmail | メールアドレス | 読み取り、書き込み |
| 45 | PerEmail | メールタイプ | お読みください |
| 46 | EmpEmployment | 最初に働いた日 | お読みください |
| 47 | EmpEmployment | lastDateWorked | お読みください |
| 48 | EmpEmployment | ユーザーID | お読みください |
| 49 | EmpEmployment | isContingentWorker | お読みください |
| 50 | EmpJob | countryOfCompany | お読みください |
| 51 | EmpJob | emplStatus | お読みください |
| 52 | EmpJob | 終了日 | お読みください |
| 53 | EmpJob | 開始日 | お読みください |
| 54 | EmpJob | 職務タイトル | お読みください |
| 55 | EmpJob | 立場 | お読みください |
| 65 | EmpJob | customString13 | お読みください |
| 56 | EmpJob | managerId | お読みください |
| 五十七 | EmpJob.BusinessUnit | businessUnit | お読みください |
| 58 | EmpJob.BusinessUnit | businessUnitId | お読みください |
| 59 | EmpJob.Company | 会社 | お読みください |
| 六十 | EmpJob.Company | 会社ID | お読みください |
| 61 | EmpJob.Company.CountryOfRegistration | twoCharCountryCode | お読みください |
| 62 | EmpJob.CostCenter | コストセンター | お読みください |
| 63 | EmpJob.CostCenter | costCenterId | お読みください |
| 64 | EmpJob.CostCenter | costCenterDescription | お読みください |
| 65 | EmpJob.Department | 部門 | お読みください |
| 66 | EmpJob.Department | departmentId | お読みください |
| 67 | EmpJob.Division | 除法 / 部門 / 分割 | お読みください |
| 68 | EmpJob.Division | divisionId | お読みください |
| 69 | EmpJob.JobCode | jobCode | お読みください |
| 70 | EmpJob.JobCode | jobCodeId | お読みください |
| 71 | EmpJob.Location | LocationName | お読みください |
| 72 | EmpJob.Location | officeLocationAddress | お読みください |
| 73 | EmpJob.Location | officeLocationCity | お読みください |
| 74 | EmpJob.Location | officeLocationCustomString4 | お読みください |
| 75 | EmpJob.Location | officeLocationZipCode | お読みください |
| 76 | EmpJob.PayGrade | payGrade | お読みください |
| 77 | EmpEmploymentTermination | activeEmploymentsCount | お読みください |
| 78 | EmpEmploymentTermination | latestTerminationDate | お読みください |

### 既定の属性マッピング

次の表は、上記の SuccessFactors 属性と Active Directory/Microsoft Entra 属性の間の既定の属性マッピングを示しています。 Microsoft Entra プロビジョニング アプリの [マッピング] ブレードで、この既定のマッピングを変更して、上記の一覧の属性を含めることができます。

| # | SuccessFactors エンティティ | SuccessFactors 属性 | 既定の属性マッピング | 処理の解説 |
| --- | --- | --- | --- | --- |
| 1 | PerPerson | 外部ユーザーID | 従業員ID | 一致する属性として使用されます |
| 2 | PerPerson | perPersonUuid | [マップされていません - ソース アンカーとして使用] | 初期同期中に、プロビジョニング サービスは personUuid を既存の objectGuid にリンクします。 |
| 3 | PerPersonal | ディスプレイ名 | ディスプレイ名 | NA |
| 4 | PerPersonal | ファーストネーム | givenName | NA |
| 5 | PerPersonal | 苗字 | エスエヌ | NA |
| 6 | ユーザー | アドレスライン1 | 住所 | NA |
| 7 | ユーザー | 都市 | l | NA |
| 8 | ユーザー | 国 | 会社 | NA |
| 9 | ユーザー | 状態 | 聖 | NA |
| 10 | ユーザー | ユーザー名 | samAccountName さん | NA |
| 11 | ユーザー | 郵便番号 | 郵便番号 | NA |
| 12 | PerEmail | メールアドレス | 郵便 | NA |
| 13 | EmpJob | 職務タイトル | タイトル | NA |
| 14 | EmpJob | managerId | マネージャー | NA |
| 15 | EmpJob.Company.CountryOfRegistration | twoCharCountryCode | c | NA |
| 16 | EmpJob.Department | 部門 | 部門 | NA |
| 十七 | EmpJob.Division | 除法 / 部門 / 分割 | 会社 | NA |
| 18 | EmpJob.Location | officeLocationAddress | 住所 | NA |
| 19 | EmpJob.Location | officeLocationZipCode | 郵便番号 | NA |
| 20 | EmpEmploymentTermination | activeEmploymentsCount | アカウント有効化 | activeEmploymentsCount=0 の場合は、アカウントを無効にします。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/sap-successfactors-integration-reference"} -->
## Microsoft Entra ID と SAP SuccessFactors の統合のリファレンス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/sap-successfactors-integration-reference
- Service: entra-id / app-provisioning
- Article date: 2026-08-20
- Summary: Microsoft Entra ID 向け SAP SuccessFactors-HR 駆動のプロビジョニングに関する技術的な詳細を掘り下げます。

[Microsoft Entra ユーザー プロビジョニング サービス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) は [、SAP SuccessFactors Employee Central](https://www.sap.com/products/hcm/employee-central-payroll.html) と統合され、ユーザーの ID ライフ サイクルを管理します。 Microsoft Entra ID では、次の 3 つの統合があらかじめ構築されています。

- [SuccessFactors からオンプレミス Active Directory へのユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial)
- [SuccessFactors から Microsoft Entra へのユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-cloud-only-tutorial)
- [SuccessFactors ライトバック](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-writeback-tutorial)

この記事では、統合のしくみと、さまざまな HR シナリオでプロビジョニング動作をカスタマイズする方法について説明します。

SAP SuccessFactors 受信プロビジョニングの場合、SAP SuccessFactors が null または空の値を返すときにマップされたターゲット属性をクリアするように、オプションの単一値ソース属性を構成できます。 構成と検証のガイダンスについては、「 [属性値のクリア (プレビュー)」](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/clear-attribute-values)を参照してください。

Microsoft Entra では、SuccessFactors へのシングル サインオンもサポートされています。 詳細については、 [Microsoft Entra シングル サインオン (SSO) と SuccessFactors の統合に関するページ](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/successfactors-tutorial)を参照してください。

### 接続の確立

Microsoft Entra プロビジョニング サービスでは、基本認証を使用して、Employee Central OData API エンドポイントへの接続が行われます。 SuccessFactors プロビジョニング アプリを設定する場合は、[*管理者資格情報*] セクションの *[テナント URL*] パラメーターを使用して[、API データ センターの URL を構成します](https://help.sap.com/docs/SAP_SUCCESSFACTORS_PLATFORM/d599f15995d348a1b45ba5603e2aba9b/af2b8d5437494b12be88fe374eba75b6.html)。

Microsoft Entra プロビジョニング サービスと SuccessFactors の間の接続をさらにセキュリティで保護するには、次のようにして、SuccessFactors の IP 許可リストに Microsoft Entra の IP 範囲を追加します。

1. Azure パブリック クラウドの [最新の IP 範囲](https://www.microsoft.com/download/details.aspx?id=56519) をダウンロードします。
2. そのファイルを開き、タグ `AzureActiveDirectory` を検索します。
3. 要素 *addressPrefixes* 内に一覧表示されているすべての IP アドレス範囲をコピーし、その範囲を使用して IP アドレス制限リストを作成します。
4. CIDR の値を IP 範囲に変換します。
5. SuccessFactors 管理ポータルにログインして、IP 範囲を許可リストに追加します。 SAP [サポート ノート 2253200](https://userapps.support.sap.com/sap/support/knowledge/2253200) を参照してください。 このツールで [IP 範囲を入力](https://answers.sap.com/questions/12882263/whitelisting-sap-cloud-platform-ip-address-range-i.html) できるようになりました。

### サポートされているエンティティ

SuccessFactors のすべてのユーザーについて、Microsoft Entra プロビジョニング サービスにより次のエンティティが取得されます。 各エンティティは、[*取得ルール*] 列で説明されているように、OData API *$expand* クエリ パラメーターを使用して拡張されます。 一部のエンティティは既定で展開されますが、一部のエンティティはマッピングに特定の属性が存在する場合にのみ展開されます。

| # | SuccessFactors エンティティ | OData ノード | 取得規則 |
| --- | --- | --- | --- |
| 1 | `PerPerson` | `*root node*` | Always (常に) |
| 2 | `PerPersonal` | `personalInfoNav` | Always (常に) |
| 3 | `PerPhone` | `phoneNav` | Always (常に) |
| 4 | `PerEmail` | `emailNav` | Always (常に) |
| 5 | `EmpEmployment` | `employmentNav` | Always (常に) |
| 6 | `User` | `employmentNav/userNav` | Always (常に) |
| 7 | `EmpJob` | `employmentNav/jobInfoNav` | Always (常に) |
| 8 | `EmpEmploymentTermination` | `activeEmploymentsCount` | Always (常に) |
| 9 | `User's manager` | `employmentNav/userNav/manager/empInfo` | Always (常に) |
| 10 | `FOCompany` | `employmentNav/jobInfoNav/companyNav` | `company` または `companyId` 属性がマップされている場合のみ |
| 11 | `FODepartment` | `employmentNav/jobInfoNav/departmentNav` | `department` または `departmentId` 属性がマップされている場合のみ |
| 12 | `FOBusinessUnit` | `employmentNav/jobInfoNav/businessUnitNav` | `businessUnit` または `businessUnitId` 属性がマップされている場合のみ |
| 13 | `FOCostCenter` | `employmentNav/jobInfoNav/costCenterNav` | `costCenter` または `costCenterId` 属性がマップされている場合のみ |
| 14 | `FODivision` | `employmentNav/jobInfoNav/divisionNav` | `division` または `divisionId` 属性がマップされている場合のみ |
| 15 | `FOJobCode` | `employmentNav/jobInfoNav/jobCodeNav` | `jobCode` または `jobCodeId` 属性がマップされている場合のみ |
| 16 | `FOPayGrade` | `employmentNav/jobInfoNav/payGradeNav` | `payGrade` 属性がマップされている場合のみ |
| 十七 | `FOLocation` | `employmentNav/jobInfoNav/locationNav` | `location` 属性がマップされている場合のみ |
| 18 | `FOCorporateAddressDEFLT` | `employmentNav/jobInfoNav/addressNavDEFLT` | マッピングに次の属性のいずれかが含まれている場合: `officeLocationAddress,  officeLocationCity, officeLocationZipCode` |
| 19 | `FOEventReason` | `employmentNav/jobInfoNav/eventReasonNav` | `eventReason` 属性がマップされている場合のみ |
| 20 | `EmpGlobalAssignment` | `employmentNav/empGlobalAssignmentNav` | `assignmentType` がマップされている場合のみ |
| 21 (二十一) | `EmploymentType Picklist` | `employmentNav/jobInfoNav/employmentTypeNav` | `employmentType` がマップされている場合のみ |
| 22 | `EmployeeClass Picklist` | `employmentNav/jobInfoNav/employeeClassNav` | `employeeClass` がマップされている場合のみ |
| 23 | `EmplStatus Picklist` | `employmentNav/jobInfoNav/emplStatusNav` | `emplStatus` がマップされている場合のみ |
| 二十四 | `AssignmentType Picklist` | `employmentNav/empGlobalAssignmentNav/assignmentTypeNav` | `assignmentType` がマップされている場合のみ |
| 二十五 | `Position` | `employmentNav/jobInfoNav/positionNav` | `positioNav` がマップされている場合のみ |
| 26 | `Manager User` | `employmentNav/jobInfoNav/managerUserNav` | `managerUserNav` がマップされている場合のみ |

### 完全同期のしくみ

属性マッピングに基づいて、完全同期の間に、Microsoft Entra プロビジョニング サービスによって、次の "GET" OData API クエリが送信されて、すべてのアクティブなワーカーと終了したワーカーの有効なデータがフェッチされます。

| パラメーター | 説明 |
| --- | --- |
| OData API ホスト | *テナント URL* に https を追加します。 例: `https://api4.successfactors.com` |
| OData API エンドポイント | `/odata/v2/PerPerson` |
| OData $format クエリ パラメーター | `json` |
| OData $filter クエリ パラメーター | `(personEmpTerminationInfoNav/activeEmploymentsCount ne null) and (lastModifiedDateTime le <CurrentExecutionTime>)` |
| OData $expand クエリ パラメーター | このパラメーター値は、マップされた属性によって異なります。 例: `employmentNav/userNav,employmentNav/jobInfoNav,personalInfoNav,personEmpTerminationInfoNav,phoneNav,emailNav,employmentNav/jobInfoNav/companyNav/countryOfRegistrationNav,employmentNav/jobInfoNav/divisionNav,employmentNav/jobInfoNav/departmentNav` |
| OData customPageSize クエリ パラメーター | `100` |

注意

完全な初期同期中に、SAP SuccessFactors からアクティブなワーカーと終了したワーカーの両方がフェッチされます。

SuccessFactors のユーザーごとに、プロビジョニング サービスにより、マッピングで定義されている一致する属性を使用して、ターゲット (Microsoft Entra ID とオンプレミスの Active Directory) でアカウントが検索されます。 たとえば、 *personIdExternal* が *employeeId* にマップされ、一致する属性として設定されている場合、プロビジョニング サービスは *personIdExternal 値を* 使用して *employeeId* フィルターを持つユーザーを検索します。 ユーザーの一致が見つかった場合は、ターゲット属性が更新されます。 一致するものが見つからない場合は、ターゲットに新しいエントリが作成されます。

特定の `personIdExternal` に対して OData API エンドポイントによって返されたデータを検証するには、API クエリの`SuccessFactorsAPIEndpoint`を自分の API データ センター サーバーの URL で更新し、cURLや Graph エクスプローラーなどのツールを使用してクエリを呼び出します。 「in」フィルターが機能しない場合は、「eq」フィルターを試してください。

```
https://[SuccessFactorsAPIEndpoint]/odata/v2/PerPerson?$format=json&
$filter=(personIdExternal in '[personIdExternalValue]')&
$expand=employmentNav/userNav,employmentNav/jobInfoNav,personalInfoNav,personEmpTerminationInfoNav,
phoneNav,phoneNav/phoneTypeNav,emailNav,employmentNav/jobInfoNav/businessUnitNav,employmentNav/jobInfoNav/companyNav,
employmentNav/jobInfoNav/companyNav/countryOfRegistrationNav,employmentNav/jobInfoNav/costCenterNav,
employmentNav/jobInfoNav/divisionNav,employmentNav/jobInfoNav/departmentNav,employmentNav/jobInfoNav/jobCodeNav,
employmentNav/jobInfoNav/locationNav,employmentNav/jobInfoNav/locationNav/addressNavDEFLT,employmentNav/jobInfoNav/payGradeNav,
employmentNav/empGlobalAssignmentNav,employmentNav/empGlobalAssignmentNav/assignmentTypeNav,employmentNav/jobInfoNav/emplStatusNav,
employmentNav/jobInfoNav/employmentTypeNav,employmentNav/jobInfoNav/employeeClassNav,employmentNav/jobInfoNav/eventReasonNav
```

### 増分同期のしくみ

完全同期の後、Microsoft Entra プロビジョニング サービスにより `LastExecutionTimestamp` が維持され、それを使用して増分変更を取得するためのデルタ クエリが作成されます。 各 SuccessFactors エンティティに含まれるタイムスタンプ属性 (`lastModifiedDateTime`、`startDate`、`endDate`、`latestTerminationDate` など) が評価され、変更が `LastExecutionTimestamp` と `CurrentExecutionTime` の間にあるかどうかが確認されます。 そうである場合、エントリの変更は有効であると見なされ、同期のために処理されます。

SuccessFactors に対して増分変更に関するクエリを実行するために Microsoft Entra ID で使用される OData API 要求テンプレートを次に示します。 要求テンプレートで変数 `SuccessFactorsAPIEndpoint`、`LastExecutionTimestamp` および `CurrentExecutionTime` を更新し、cURL や Graph エクスプローラーなどのツールを使用して、返されるデータを確認できます。 または、 OData API 監査ログを有効にすることで、SuccessFactors から実際の要求ペイロードを取得することもできます。

```
https://[SuccessFactorsAPIEndpoint]/odata/v2/PerPerson/$count?$format=json&$filter=(personEmpTerminationInfoNav/activeEmploymentsCount ne null) and
((lastModifiedDateTime ge datetimeoffset'<LastExecutionTimestamp>' and lastModifiedDateTime le datetimeoffset'<CurrentExecutionTime>') or
(personalInfoNav/startDate ge datetimeoffset'<LastExecutionTimestamp>' and personalInfoNav/startDate le datetimeoffset'<CurrentExecutionTime>') or
((personalInfoNav/lastModifiedDateTime ge datetimeoffset'<LastExecutionTimestamp>' and personalInfoNav/lastModifiedDateTime le datetimeoffset'<CurrentExecutionTime>') and (personalInfoNav/startDate le datetimeoffset'<CurrentExecutionTime>' and (personalInfoNav/endDate ge datetimeoffset'<CurrentExecutionTime>' or  personalInfoNav/endDate eq null))) or
(employmentNav/startDate ge datetimeoffset'<LastExecutionTimestamp>' and employmentNav/startDate le datetimeoffset'<CurrentExecutionTime>') or
((employmentNav/lastModifiedDateTime ge datetimeoffset'<LastExecutionTimestamp>' and employmentNav/lastModifiedDateTime le datetimeoffset'<CurrentExecutionTime>') and (employmentNav/startDate le datetimeoffset'<CurrentExecutionTime>' and (employmentNav/endDate ge datetimeoffset'<CurrentExecutionTime>' or employmentNav/endDate eq null))) 
(employmentNav/jobInfoNav/startDate ge datetimeoffset'<LastExecutionTimestamp>' and employmentNav/jobInfoNav/startDate le datetimeoffset'<CurrentExecutionTime>') or
((employmentNav/jobInfoNav/lastModifiedDateTime ge datetimeoffset'<LastExecutionTimestamp>' and employmentNav/jobInfoNav/lastModifiedDateTime le datetimeoffset'<CurrentExecutionTime>') and (employmentNav/jobInfoNav/startDate le datetimeoffset'<CurrentExecutionTime>' and (employmentNav/jobInfoNav/endDate ge datetimeoffset'<CurrentExecutionTime>' or employmentNav/jobInfoNav/endDate eq null))) or
(phoneNav/lastModifiedDateTime ge datetimeoffset'<LastExecutionTimestamp>' and phoneNav/lastModifiedDateTime le datetimeoffset'<CurrentExecutionTime>') or
(emailNav/lastModifiedDateTime ge datetimeoffset'<LastExecutionTimestamp>' and emailNav/lastModifiedDateTime le datetimeoffset'<CurrentExecutionTime>') or
(personEmpTerminationInfoNav/latestTerminationDate ge datetimeoffset'<previousDayDateStartTime24hrs>' and personEmpTerminationInfoNav/latestTerminationDate le datetimeoffset'<previousDayDateTime24hrs>') or
(employmentNav/userNav/lastModifiedDateTime ge datetimeoffset'<LastExecutionTimestamp>' and employmentNav/userNav/lastModifiedDateTime le datetimeoffset'<CurrentExecutionTime>'))
&$expand=employmentNav/userNav,employmentNav/jobInfoNav,personalInfoNav,personEmpTerminationInfoNav,phoneNav,emailNav,employmentNav/userNav/manager/empInfo,employmentNav/jobInfoNav/companyNav,employmentNav/jobInfoNav/departmentNav,employmentNav/jobInfoNav/locationNav,employmentNav/jobInfoNav/locationNav/addressNavDEFLT,employmentNav/jobInfoNav/locationNav/addressNavDEFLT/stateNav&customPageSize=100
```

### 採用前処理のしくみ

このセクションでは、SAP SuccessFactors コネクタで事前採用レコード (採用日/将来の開始日が設定されている従業員) がどのように処理されるかについて説明します。 たとえば、2023 年 6 月 1 日の開始日が設定された employeeId "1234" を持つ事前採用者が SuccessFactors Employee Central に存在するとします。 さらに、この事前採用レコードが Employee Central または Onboarding モジュールのいずれかで 2023 年 5 月 15 日に最初に作成されたとします。 プロビジョニング サービスが (完全同期または増分同期の一部として) 2023 年 5 月 15 日にこのレコードを最初に観察したとき、このレコードは引き続き事前採用の状態です。 このため、SuccessFactors では、そのユーザーに関連付けられているすべての属性 (例: userNav/username) をプロビジョニング サービスに送信しません。 `companyName`、`personIdExternal`、`firstname`、`lastname`、`startDate` などのユーザーに関する最小限のデータのみが使用できます。 事前採用者を正常に処理するには、次の前提条件を満たす必要があります。

1. `personIdExternal` 属性はプライマリ照合識別子 (結合プロパティ) として設定する必要があります。 別の属性 (例: userName) を結合プロパティとして構成した場合、プロビジョニング サービスでは事前採用者の情報を取得できません。
2. `startDate` 属性を使用でき、その JSONPath を `$.employmentNav.results[0].startDate` または `$.employmentNav.results[-1:].startDate` に設定する必要があります。
3. 事前採用レコードは、Employee Central で 'active' (t)、'inactive' (f)、または 'active\_external\_suite' (e) のいずれかの状態である必要があります。 これらの状態の詳細については、 [SAP サポート ノートの2736579](https://launchpad.support.sap.com/#/notes/0002736579)を参照してください。

注意

組織の履歴がない事前採用者の場合は、[0] と [-1:] インデックスの両方が `startDate` に対応します。 再雇用またはコンバージョンの事前採用者の場合、順序は確定的でははありません。これにより、特定の再雇用/コンバージョンの従業員が実際の開始日に処理される場合があります。 これは、コネクタの既知の制限事項です。

完全/増分同期またはオンデマンド プロビジョニング中に、プロビジョニング サービスで事前採用レコードが検出されると、"asOfDate" フィルターがユーザーの startDate (例: asOfDate=2023-06-01) に設定された次の OData クエリが SuccessFactors に送信されます。

```
https://[SuccessFactorsAPIEndpoint]/odata/v2/PerPerson?$format=json&$
filter=(personIdExternal in '1234' and employmentNav/userNav/status in 't','f','e')&asOfDate=2023-06-01&$
expand=employmentNav/userNav,employmentNav/jobInfoNav,personalInfoNav,personEmpTerminationInfoNav,phoneNav,emailNav,employmentNav/userNav/manager/empInfo,employmentNav/jobInfoNav/companyNav,employmentNav/jobInfoNav/costCenterNav,employmentNav/jobInfoNav/divisionNav,employmentNav/jobInfoNav/departmentNav,employmentNav/
```

事前採用者の処理に関する問題が発生している場合は、上の OData 要求形式を使用して、API エンドポイントを置き換える SuccessFactors インスタンスに対してクエリを実行します。`personIdExternal` と `asOfDate` をテスト シナリオに対応する値でフィルター処理します。

### 属性データの読み取り

Microsoft Entra プロビジョニング サービスにより SuccessFactors のクエリが行われ、JSON の結果セットが取得されます。 JSON の結果セットには、Employee Central に格納されている多くの属性が含まれています。 既定では、プロビジョニング スキーマは、これらの属性のサブセットのみを取得するように構成されています。

さらに属性を取得するには、次の手順に従います。

1. **エンタープライズ アプリケーション**&gt;**SuccessFactors App**&gt;**Provisioning**&gt;**Edit Provisioning**&gt;**Attribute Mapping** ページに移動します。
2. [ **詳細オプション] ドロップダウンを** 選択し、[ **ターゲット ユーザー属性の編集]** を選択します。

    注意

    **[SuccessFactors の属性リストの編集]** オプションが Microsoft Entra 管理センターに表示されない場合は、URL *https://portal.azure.com/?Microsoft_AAD_IAM_forceSchemaEditorEnabled=true*を使用してページにアクセスします。
3. このビューの **API 式** 列には、コネクタで使用される JSONPath 式が表示されます。

[Image: API 式]
4. 既存の JSONPath 値を編集するか、有効な JSONPath 式を使用して新しい属性をスキーマに追加することができます。

次のセクションでは、JSONPath 値の編集に関する一般的なシナリオの一覧を示します。

### さまざまな HR シナリオの処理

JSONPath は、XML 用の XPath に似た JSON 用のクエリ言語です。 XPath と同様に、JSONPath を使用すると、JSON ペイロードのデータの抽出とフィルター処理を行うことができます。

JSONPath 変換を使用すると、Microsoft Entra プロビジョニング アプリの動作をカスタマイズして、カスタム属性を取得し、再雇用、作業者変換、グローバル割り当てなどのシナリオを処理することができます。

このセクションでは、次の HR シナリオ用にプロビジョニング アプリをカスタマイズする方法について説明します。

- Microsoft EntraのプロビジョニングがSAP SuccessFactorsとどのように統合されるか
    - 接続性の確立
    - サポート対象
    - フルシンクの仕組み
    - インクリメンタル同期の仕組み
    - 採用前の処理の仕組み
    - 属性データの読み込み
    - さまざまな人事シナリオへの対応
        - その他の属性の取得
        - カスタム属性の取得
        - 雇用状態とアカウントの状態のマッピング
        - worker の変換と再雇用のシナリオの処理
        - 現在のアクティブな雇用記録の取得
        - グローバル割り当てシナリオの処理
        - 同時実行ジョブの処理シナリオ
        - 位置の詳細の取得
        - オンボード モジュールでのユーザーのプロビジョニング
        - SuccessFactors での OData API 監査ログの有効化
    - 書き込みシナリオ
        - 電話およびメールでの返信対応シナリオ
        - UserIDによる書き込みの有効化
        - 電話やメールの書き込み返しに関するサポートされていないシナリオ
    - 次のステップ

#### 追加属性の取得

既定の Microsoft Entra SuccessFactors プロビジョニング アプリ スキーマには [、90 以上の定義済み属性が付属しています](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/sap-successfactors-attribute-reference)。 プロビジョニング スキーマに SuccessFactors 属性をさらに追加するには、次の手順を使用します。

1. OData クエリを使用して、Employee Central から有効なテスト ユーザーのデータを取得します。

    ```
     https://[SuccessFactorsAPIEndpoint]/odata/v2/PerPerson?$format=json&
     $filter=(personIdExternal in '[personIdExternalValue]')&
     $expand=employmentNav/userNav,employmentNav/jobInfoNav,personalInfoNav,personEmpTerminationInfoNav,
     phoneNav,phoneNav/phoneTypeNav,emailNav,employmentNav/jobInfoNav/businessUnitNav,employmentNav/jobInfoNav/companyNav,
     employmentNav/jobInfoNav/companyNav/countryOfRegistrationNav,employmentNav/jobInfoNav/costCenterNav,
     employmentNav/jobInfoNav/divisionNav,employmentNav/jobInfoNav/departmentNav,employmentNav/jobInfoNav/jobCodeNav,
     employmentNav/jobInfoNav/locationNav,employmentNav/jobInfoNav/locationNav/addressNavDEFLT,employmentNav/jobInfoNav/payGradeNav,
     employmentNav/empGlobalAssignmentNav,employmentNav/empGlobalAssignmentNav/assignmentTypeNav,employmentNav/jobInfoNav/emplStatusNav,
     employmentNav/jobInfoNav/employmentTypeNav,employmentNav/jobInfoNav/employeeClassNav,employmentNav/jobInfoNav/eventReasonNav
    ```
2. 属性に関連付けられている Employee Central エンティティを確認します

    - 属性が *EmpEmployment* エンティティの一部である場合は、 *employmentNav* ノードで属性を探します。
    - 属性が *User* エンティティの一部である場合は、 *employmentNav/userNav* ノードで属性を探します。
    - 属性が *EmpJob* エンティティの一部である場合は、 *employmentNav/jobInfoNav* ノードで属性を探します。
3. 属性に関連付けられた JSON パスを作成し、この新しい属性を SuccessFactors 属性のリストに追加します。

    - 例 1: `okToRehire` エンティティの一部である `employmentNav` 属性を追加し、JSONPath `$.employmentNav.results[0].okToRehire` を使用するとします
    - 例 2: *userNav* エンティティの一部である*属性 timeZone* を追加し、JSONPath を使用するとします`$.employmentNav.results[0].userNav.timeZone`
    - 例 3: *jobInfoNav* エンティティの一部である属性 *flsaStatus* を追加し、JSONPath を使用するとします`$.employmentNav.results[0].jobInfoNav.results[0].flsaStatus`
4. スキーマを保存します。
5. プロビジョニングを再開します。

#### カスタム属性の取得

Microsoft Entra SuccessFactors プロビジョニング アプリでは、次のカスタム属性が既定で事前に定義されています。

- User (userNav) エンティティからの *custom01-custom15*
- *empNavCustomString1-empNavCustomString15 という EmpEmployment* (employmentNav) エンティティからの *customString1-customString15*
- *empJobNavCustomString1-empNavJobCustomString15 という EmpJobInfo* (jobInfoNav) エンティティからの *customString1-customString15*

たとえば、Employee Central インスタンスでは、*EmpJobInfo* の *customString35* 属性に場所の説明が格納されます。 この値を Active Directory *physicalDeliveryOfficeName* 属性にフローする必要があります。 このシナリオの属性マッピングを構成するには、次の手順を使用します。

1. SuccessFactors 属性リストを編集して、 *empJobNavCustomString35* という新しい属性を追加します。
2. この属性の JSONPath API 式を次のように設定します: `$.employmentNav.results[0].jobInfoNav.results[0].customString35`
3. Microsoft Entra 管理センターでマッピングの変更を保存して再読み込みします。
4. 属性マッピング ブレードで、 *empJobNavCustomString35* を *physicalDeliveryOfficeName* にマップします。
5. マッピングを保存します。

このシナリオの拡張:

- *User* エンティティから *custom35* 属性をマップする場合は、JSONPath を使用します。`$.employmentNav.results[0].userNav.custom35`
- *EmpEmployment* エンティティから *customString35* 属性をマップする場合は、JSONPath を使用します。`$.employmentNav.results[0].customString35`

#### 雇用状態をアカウントの状態にマッピング

既定で、Microsoft Entra SuccessFactors コネクタは、`activeEmploymentsCount` オブジェクトの `PersonEmpTerminationInfo` フィールドを使用してアカウントの状態を設定します。 この属性では、次のいずれかの問題が発生する可能性があります。

1. コネクタにより、雇用契約が終了する勤務最終日の 1 日前に、その従業員のアカウントが無効になることがあるという既知の問題があります。
2. `PersonEmpTerminationInfo` オブジェクトが null に設定されている場合、`personEmpTerminationInfoNav` オブジェクトが null に設定されたレコードはプロビジョニング エンジンによって除外されるため、終了時の AD アカウントの無効化は機能しません。

これらの問題のいずれかが発生している場合、または雇用状態をアカウントの状態にマッピングしたい場合は、`emplStatus` フィールドを展開し、`emplStatus.externalCode` フィールドにある雇用状態コードを使用するようにマッピングを更新できます。 [SAP サポート ノート 2505526](https://launchpad.support.sap.com/#/notes/2505526)に基づいて、プロビジョニング アプリで取得できる雇用状態コードの一覧を次に示します。

- A = Active (勤務中)
- D = Dormant (休職中)
- U = Unpaid Leave (無給休暇)
- P = Paid Leave (有給休暇)
- S = Suspended (出勤停止)
- F = Furlough (一時解雇)
- O = Discarded (解雇)
- R = Retired (定年退職)
- T = Terminated (雇用終了)

次の手順を使用して、これらのコードを取得するようにマッピングを更新します。

1. SuccessFactors プロビジョニング アプリの属性マッピング ページを開きます。
2. [ **詳細オプション]** ドロップダウンで、[ **ターゲット ユーザー属性の編集**] を選択します。
3. 属性 `emplStatus` を見つけ、JSONPath を `$.employmentNav.results[0].jobInfoNav.results[0].emplStatusNav.externalCode` に更新します。 この更新により、コネクタはテーブル内の雇用状態コードを取得できます。
4. 変更を保存します。
5. 属性マッピング ブレードで、アカウントの状態フラグの式マッピングを更新します。

    | プロビジョニング ジョブ | アカウントの状態の属性 | マッピング式 |
    | --- | --- | --- |
    | Active Directory ユーザー プロビジョニングに対する SuccessFactors | `accountDisabled` | `Switch([emplStatus], "True", "A", "False", "U", "False", "P", "False")` |
    | SuccessFactors から Microsoft Entra へのユーザー プロビジョニング | `accountEnabled` | `Switch([emplStatus], "False", "A", "True", "U", "True", "P", "True")` |
6. 変更を保存します。
7. [オンデマンドプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)を使用して構成をテストします。
8. 同期が期待どおりに動作することを確認したら、プロビジョニング ジョブを再起動します。

#### 作業者の転換と再雇用のシナリオの処理

**ワーカー変換シナリオについて:** 作業者変換は、既存の正社員を請負業者または請負業者に正社員に変換するプロセスです。 このシナリオでは、Employee Central によって、同じ *Person* エンティティの新しい *User* エンティティと共に新しい *EmpEmployment* エンティティが追加されます。 前の *EmpEmployment* エンティティの下に入れ子になった *User* エンティティが null に設定されています。

**再雇用シナリオについて:** SuccessFactors には、従業員の再雇用を処理するための 2 つのオプションがあります。

- オプション 1: Employee Central で新しい個人プロファイルを作成する
- オプション 2:Employee Central で既存の個人プロファイルを再利用する

HR プロセスでオプション 1 を使用する場合は、プロビジョニング スキーマを変更する必要はありません。 HR プロセスでオプション 2 を使用する場合、Employee Central は、同じ *Person* エンティティの新しい *User* エンティティと共に新しい *EmpEmployment* エンティティを追加します。

転換または再雇用が発生したときに新しい雇用データが表示されるように、両方のシナリオを処理することができます。 以下のステップを使用してプロビジョニング アプリのスキーマを一括更新します。

1. SuccessFactors プロビジョニング アプリの属性マッピング ページを開きます。
2. [ **詳細オプション] ドロップダウンを** 選択し、[ **スキーマの編集]** を選択してスキーマ エディターを開きます。

>
> [Image: スクリーンショットは、スキーマ エディターを開く [スキーマの確認] リンクを示しています。]
3. [ **ダウンロード** ] リンクをクリックして、編集する前にスキーマのコピーを保存します。

>
> [Image: スクリーンショットは、スキーマのコピーを保存するための [ダウンロード] を選択したスキーマ エディターを示しています。]
4. スキーマ エディターで Ctrl + H キーを押して、検索して置換コントロールを開きます。
5. 検索テキスト ボックスに値 `$.employmentNav.results[0]` をコピーして貼り付けます
6. 置換テキスト ボックスに値 `$.employmentNav.results[-1:]` をコピーして貼り付けます。 この JSONPath 式は、最新の *EmpEmployment* レコードを返します。

>
> [Image: 検索-置換-変換]
7. [すべて置換] をクリックしてスキーマを更新します。
8. スキーマを保存します。
9. 上のプロセスでは、すべての JSONPath 式が次のように更新されます。

    - 古い JSONPath: `$.employmentNav.results[0].jobInfoNav.results[0].departmentNav.name_localized`
    - 新しい JSONPath: `$.employmentNav.results[-1:].jobInfoNav.results[0].departmentNav.name_localized`
10. [オンデマンドプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)を使用して構成をテストします。
11. 同期が期待どおりに動作することを確認したら、プロビジョニング ジョブを再起動します。

注意

上記のアプローチは、SAP SuccessFactors が雇用オブジェクトを昇順で返す場合にのみ機能します。この場合、最新の雇用レコードは常に *employmentNav* 結果配列の最後のレコードになります。 複数の雇用レコードが返される順序は、SuccessFactors によって保証されません。 ある従業員に対応する複数の雇用レコードが SuccessFactors インスタンスにあり、アクティブな雇用レコードに関連付けられている属性を常に取得したい場合は、次のセクションで説明する手順を使用してください。

#### 現在のアクティブな雇用レコードの取得

`$.employmentNav.results[0]` または `$.employmentNav.results[-1:]` の JSONPath ルートを使用した雇用レコードのフェッチは、ほとんどのシナリオで機能し、構成をシンプルに保ちます。 ただし、SuccessFactors インスタンスの構成方法によっては、コネクタで常に最新のアクティブな雇用レコードがフェッチされるように、この構成を更新する必要がある場合があります。

このセクションでは、ユーザーの現在アクティブな雇用レコードを確実に取得するように JSONPath 設定を更新する方法について説明します。 また、従業員の転換と再雇用のシナリオについても扱います。

1. SuccessFactors プロビジョニング アプリの属性マッピング ページを開きます。
2. [ **詳細オプション] ドロップダウンを** 選択し、[ **スキーマの編集]** を選択してスキーマ エディターを開きます。
3. [ **ダウンロード** ] リンクをクリックして、編集する前にスキーマのコピーを保存します。
4. スキーマ エディターで Ctrl + H キーを押して、検索して置換コントロールを開きます。
5. 次の検索置換操作を実行します。 検索置換操作を実行するときは、先頭または末尾にスペースがないことを確認します。 `[-1:]` ではなく `[0]` インデックスを使用している場合は、それに応じて "検索する文字列" フィールドを更新します。

    | **検索する文字列** | **置換に使用する文字列** | **目的** |
    | --- | --- | --- |
    | `$.employmentNav.results[0].jobInfoNav.results[0].emplStatus` | `$.employmentNav..jobInfoNav..results[?(@.emplStatusNav.externalCode == 'A' || @.emplStatusNav.externalCode == 'U' || @.emplStatusNav.externalCode == 'P' )].emplStatusNav.externalCode` | この検索置換では、emplStatusNav OData オブジェクトを展開する機能を追加しています。 |
    | `$.employmentNav.results[0].jobInfoNav.results[0]` | `$.employmentNav..jobInfoNav..results[?(@.emplStatusNav.externalCode == 'A' || @.emplStatusNav.externalCode == 'U' || @.emplStatusNav.externalCode == 'P')]` | この検索置換では、アクティブな SuccessFactors EmpJobInfo レコードに関連付けられている属性を常に取得するようにコネクタに指示します。 SuccessFactors で、終了または非アクティブなレコードに関連付けられている属性は無視されます。 |
    | `$.employmentNav.results[0]` | `$.employmentNav..results[?(@.jobInfoNav..results[?(@.emplStatusNav.externalCode == 'A' || @.emplStatusNav.externalCode == 'U' || @.emplStatusNav.externalCode == 'P')])]` | この検索置換では、アクティブな SuccessFactors Employment レコードに関連付けられている属性を常に取得するようにコネクタに指示します。 SuccessFactors で、終了または非アクティブなレコードに関連付けられている属性は無視されます。 |
6. スキーマを保存します。
7. 上のプロセスでは、すべての JSONPath 式が更新されます。
8. 事前採用者処理を機能させるには、`startDate` 属性に関連付けられている JSONPath で `[0]` または `[-1:]` インデックスのいずれかを使用する必要があります。 [ **詳細オプション]** ドロップダウンで、[ **ターゲット ユーザー属性の編集**] を選択します。 属性 `startDate` を見つけて値 `$.employmentNav.results[-1:].startDate` に設定します。
9. スキーマを保存します。
10. 終了が期待どおりに処理されるようにするには、属性マッピング セクションで次のいずれかの設定を使用できます。

    | プロビジョニング ジョブ | アカウントの状態の属性 | アカウントの状態が "activeEmploymentsCount" に基づいている場合に使用する式 | アカウントの状態が "emplStatus" 値に基づいている場合に使用する式 |
    | --- | --- | --- | --- |
    | Active Directory ユーザー プロビジョニングに対する SuccessFactors | `accountDisabled` | `Switch([activeEmploymentsCount], "False", "0", "True")` | `Switch([emplStatus], "True", "A", "False", "U", "False", "P", "False")` |
    | SuccessFactors から Microsoft Entra へのユーザー プロビジョニング | `accountEnabled` | `Switch([activeEmploymentsCount], "True", "0", "False")` | `Switch([emplStatus], "False", "A", "True", "U", "True", "P", "True")` |
11. 変更を保存します。 1.
12. [オンデマンドプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)を使用して構成をテストします。
13. 同期が期待どおりに動作することを確認したら、プロビジョニング ジョブを再起動します。

#### グローバル割り当てシナリオの処理

Employee Central のユーザーがグローバル割り当てのために処理されると、SuccessFactors は新しい *EmpEmployment* エンティティを追加し、 *assignmentClass* を "GA" に設定します。 また、新しい *User* エンティティも作成されます。 したがって、ユーザーは次のようになります。

- 1 つの *EmpEmployment* + *assignmentClass* が "ST" に設定され、ホームの割*り当*てに対応するUser エンティティ
- *assignmentClass* が "GA" に設定されたグローバル割り当てに対応する別の + *User* エンティティ

標準割り当てとグローバル割り当てのユーザー プロファイルに属する属性を取得するには、次の手順を使用します。

1. SuccessFactors プロビジョニング アプリの属性マッピング ページを開きます。
2. [ **詳細オプション] ドロップダウンを** 選択し、[ **スキーマの編集]** を選択してスキーマ エディターを開きます。
3. [ **ダウンロード** ] リンクをクリックして、編集する前にスキーマのコピーを保存します。
4. スキーマ エディターで Ctrl + H キーを押して、検索して置換コントロールを開きます。
5. 検索テキスト ボックスに値 `$.employmentNav.results[0]` をコピーして貼り付けます
6. 置換テキスト ボックスに値 `$.employmentNav.results[?(@.assignmentClass == 'ST')]` をコピーして貼り付けます。 演算子を囲む空白に注意してください。これは、JSONPath 式を正常に処理するために重要です。
7. [すべて置換] をクリックしてスキーマを更新します。
8. スキーマを保存します。
9. 上のプロセスでは、すべての JSONPath 式が次のように更新されます。

    - 古い JSONPath: `$.employmentNav.results[0].jobInfoNav.results[0].departmentNav.name_localized`
    - 新しい JSONPath: `$.employmentNav.results[?(@.assignmentClass == 'ST')].jobInfoNav.results[0].departmentNav.name_localized`
10. アプリの属性マッピング ページを再読み込みします。
11. [ **詳細オプション] ドロップダウンを** 選択し、[ **ターゲット ユーザー属性の編集]** を選択します。
12. グローバル割り当てデータをフェッチする新しい属性を追加します。たとえば、グローバル割り当てプロファイルに関連付けられている部門名をフェッチする場合は、JSONPath 式をに設定して`$.employmentNav.results[?(@.assignmentClass == 'GA')].jobInfoNav.results[0].departmentNav.name_localized` を追加できます。
13. 両方の部署の値を Active Directory 属性にフローさせるか、または式マッピングを使用して値を選択的にフローさせることができます。 例: この式では、AD *department* 属性の値が *globalAssignmentDepartment* (存在する場合) に設定されます。それ以外の場合は、標準割り当てに関連付けられている *部門* に値が設定されます。

    - `IIF(IsPresent([globalAssignmentDepartment]),[globalAssignmentDepartment],[department])`
14. マッピングを保存します。
15. [オンデマンドプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)を使用して構成をテストします。
16. 同期が期待どおりに動作することを確認したら、プロビジョニング ジョブを再起動します。

#### 同時実行ジョブのシナリオの処理

Employee Central のユーザーが同時または複数のジョブを持つ場合、*assignmentClass* が "ST" に設定された 2 つの *EmpEmployment* エンティティと *User* エンティティがあります。 両方のジョブに属している属性をフェッチするには、次の手順を使用します。

1. SuccessFactors プロビジョニング アプリの属性マッピング ページを開きます。
2. [ **詳細オプション] ドロップダウンを** 選択し、[ **ターゲット ユーザー属性の編集]** を選択します。
3. たとえば、ジョブ 1 とジョブ 2 に関連付けられている部署をプルするとします。定義済みの属性 *部門* は、最初のジョブの department の値を既にフェッチしています。 *secondJobDepartment* という新しい属性を定義し、JSONPath 式を`$.employmentNav.results[1].jobInfoNav.results[0].departmentNav.name_localized`
4. 両方の部署の値を Active Directory 属性にフローさせるか、または式マッピングを使用して値を選択的にフローさせることができます。
5. マッピングを保存します。
6. [オンデマンドプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)を使用して構成をテストします。
7. 同期が期待どおりに動作することを確認したら、プロビジョニング ジョブを再起動します。

#### 位置の詳細の取得

SuccessFactors コネクタでは、位置オブジェクトの展開がサポートされています。 位置オブジェクトのジョブ レベルや位置名などの属性を特定の言語で展開または取得するには、下に示す JSONPath 式を使用します。

| 属性名 | JSONPath 式 |
| --- | --- |
| ポジションジョブレベル | $.employmentNav.results[0].jobInfoNav.results[0].positionNav.jobLevel |
| ポジション名FR | $.employmentNav.results[0].jobInfoNav.results[0].positionNav.externalName\_fr\_FR |
| ポジション名DE | $.employmentNav.results[0].jobInfoNav.results[0].positionNav.externalName\_de\_DE |

#### Onboarding モジュールでのユーザーのプロビジョニング

SAP SuccessFactors からオンプレミスの Active Directory および Microsoft Entra ID へのインバウンド ユーザー プロビジョニングで、SAP SuccessFactors Onboarding 2.0 モジュールに存在する事前採用者の事前プロビジョニングがサポートされるようになりました。 Microsoft Entra プロビジョニング サービスは、開始日が将来の日付である新しい採用プロファイルを検出すると、SAP SuccessFactors に対してクエリを実行し、状態コードが `active`、`inactive`、`active_external_suite` のいずれかである新規採用者を取得します。 状態コード `active_external_suite` は、SAP SuccessFactors Onboarding 2.0 モジュールに存在する事前採用者に対応しています。 これらのステータスコードの説明については、 [SAP サポートノート2736579](https://launchpad.support.sap.com/#/notes/0002736579)を参照してください。

プロビジョニング サービスの既定の動作では、Onboarding モジュールで事前採用者を処理します。

Onboarding モジュールでの事前採用者の処理を除外する場合、プロビジョニング ジョブの構成を次のように更新します。

1. SuccessFactors プロビジョニング アプリの属性マッピング ページを開きます。
2. [ **詳細オプション]** ドロップダウンで、[ **ターゲット ユーザー属性の編集** ] を選択して、 `userStatus`という新しい属性を追加します。
3. この属性の JSONPath API 式を次のように設定します: `$.employmentNav.results[0].userNav.status`
4. スキーマを保存して、属性マッピング ページに戻ります。
5. **スコープ フィルターを**選択し、スコープ フィルター `userStatus NOT EQUALS`を適用します。
6. マッピングを保存し、オンデマンドのプロビジョニングを使用してスコープ フィルターが機能することを検証します。

注意

コネクターは現在、オンボーディング中のユーザーの取得をinactive\_external\_suite対応していません。 これらのユーザー状態は、新入社員が最初にオンボーディングを開始しますが、後にオファーを取り消し、オンボーディングプロセスのキャンセルが必要になる場合に発生します。 このシナリオには回避策はありません。オンボーディングがキャンセルされたユーザーを非アクティブ化するには、アウトオブバンド処理が必要です。

#### SuccessFactors での OData API 監査ログの有効化

Microsoft Entra SuccessFactors コネクタは、SuccessFactors OData API を使用して変更を取得し、ユーザーをプロビジョニングします。 プロビジョニング サービスに関する問題が発生し、SuccessFactors から取得されたデータを確認したい場合は、SuccessFactors で OData API 監査ログを有効にできます。 監査ログから Microsoft Entra ID によって送信された要求ペイロードを取得します。 トラブルシューティングを行うには、cURL や Graph エクスプローラーなどのツールでこの要求ペイロードをコピーし、コネクタで使用されているのと同じ API ユーザーを使用するように設定し、SuccessFactors から必要な変更が返されるかどうかを確認します。

### 書き戻しのシナリオ

このセクションでは、さまざまな書き戻しのシナリオについて説明します。 SuccessFactors でのメールと電話番号の設定方法に基づいて構成方法を推奨します。

#### 電話とメールの書き戻しに対してサポートされるシナリオ

| # | シナリオの要件 | メールのプライマリフラグの値 | 勤務先の電話番号プライマリ フラグの値 | 携帯電話プライマリ フラグの値 | 勤務先の電話番号マッピング | 携帯電話マッピング |
| --- | --- | --- | --- | --- | --- | --- |
| 1 | \* プライマリとして勤務先メールだけを設定する。  \* 電話番号は設定しない。 | ほんとう | ほんとう | 偽り | [設定しない] | [設定しない] |
| 2 | \* SuccessFactors では、勤務先メールと勤務先電話がプライマリである  \* 常に、Microsoft Entra 電話番号を勤務先電話に送信し、モバイルを携帯電話に送信する。 | ほんとう | ほんとう | 偽り | 電話番号 | モバイル |
| 3 | \* SuccessFactors では、勤務先メールと携帯電話がプライマリである  \* 常に、Microsoft Entra 電話番号を勤務先電話に送信し、モバイルを携帯電話に送信する | ほんとう | 偽り | ほんとう | 電話番号 | モバイル |
| 4 | \* SuccessFactors では、勤務先メールがプライマリです。  \* Microsoft Entra ID で、勤務先の電話番号が存在するかどうかを確認し、存在する場合は、携帯電話番号も存在するかどうかを確認する。 携帯電話番号が存在しない場合にのみ、勤務先の電話番号をプライマリとしてマークします。 | ほんとう | 式マッピングを使用する: `IIF(IsPresent([telephoneNumber]), IIF(IsPresent([mobile]),"false", "true"), "false")` | 式マッピングを使用する: `IIF(IsPresent([mobile]),"false", "true")` | 電話番号 | モバイル |
| 5 | \* SuccessFactors では、勤務先メールと勤務先電話がプライマリである。  \* Microsoft Entra ID で、モバイルが使用可能な場合は、それを勤務先電話として設定し、それ以外の場合は telephoneNumber を使用する。 | ほんとう | ほんとう | 偽り | `IIF(IsPresent([mobile]), [mobile], [telephoneNumber])` | [設定しない] |

- 書き戻し属性マッピングに電話番号のマッピングがない場合は、メールのみが書き戻しに含まれます。
- Employee Central での新規雇用オンボードの間に、勤務先のメールと電話番号を使用できない場合があります。 オンボードの間に勤務先メールと勤務先電話をプライマリとして設定する必要がある場合は、新規雇用の作成の間に勤務先の電話とメールにダミーの値を設定できます。 しばらくすると、書き戻しアプリによって値が更新されます。

#### UserID での書き戻しの有効化

SuccessFactors 書き戻しアプリでユーザー オブジェクトの属性を更新するには、次のロジックを使用します。

- 最初の手順として、変更セットで *userId* 属性を検索します。 存在する場合は、SuccessFactors API の呼び出しを行うために "UserId" が使用されます。
- *userId* が見つからない場合、既定では *personIdExternal* 属性値が使用されます。

通常、SuccessFactors の *personIdExternal* 属性値は *userId* 属性値と一致します。 ただし、再雇用や作業者転換などのシナリオでは、SuccessFactors 内の従業員がアクティブと非アクティブの 2 つの雇用記録を持つ場合があります。 そのようなシナリオで、書き戻しによってアクティブなユーザー プロファイルが更新されるようにするには、SuccessFactors プロビジョニング アプリの構成を説明のように更新します。 この構成により、コネクタに表示される変更セットに *userId* が常に存在し、SuccessFactors API 呼び出しで使用されます。

1. Microsoft Entra ユーザー プロビジョニング アプリに対する SuccessFactors、またはオンプレミス AD ユーザープ ロビジョニング アプリに対する SuccessFactors を開きます。
2. Microsoft Entra ID の `extensionAttribute[1-15]` に、すべての作業者のアクティブな雇用記録の `userId` が常に保存されるようにします。 レコードは、SuccessFactors `userId` 属性を Microsoft Entra ID の `extensionAttribute[1-15]` にマップします。
[Image: 受信 UserID 属性マッピング]
3. JSONPath の設定に関するガイダンスについては、「 ワーカーの変換と再雇用のシナリオの処理」セクションを 参照して、アクティブな雇用レコードの *userId* 値が Microsoft Entra ID に流れるようにします。
4. マッピングを保存します。
5. プロビジョニング ジョブを実行して、 *userId* 値が Microsoft Entra ID に送られるようにします。
    注意

    SuccessFactors を使用してオンプレミスの Active Directory ユーザー プロビジョニングを行う場合は、オンプレミスの Active Directory から Microsoft Entra ID に *userId* 属性値を同期するように Microsoft Entra Connect を構成します。
6. Azure portal で SuccessFactors 書き戻しアプリを開きます。
7. userId 値を含む目的の *extensionAttribute* を SuccessFactors *userId* 属性にマップします。
[Image: 書き戻し UserID 属性マッピング]
8. マッピングを保存します。
9. [属性マッピング] -&gt; [詳細設定] -&gt; [Review Schema](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/スキーマの確認) に移動して、JSON スキーマ エディターを開きます。
10. バックアップとしてスキーマのコピーをダウンロードします。
11. スキーマ エディターで Ctrl + F キーを押して、userId のマッピングが含まれる JSON ノードを検索します。ここでは、ソース Microsoft Entra 属性にマップされます。
12. 次に示すように、flowBehavior 属性を "FlowWhenChanged" から "FlowAlways" に更新します。
[Image: マッピング フロー動作の更新]
13. マッピングを保存し、オンデマンド プロビジョニングで書き戻しのシナリオをテストします。

#### 電話とメールの書き戻しに対してサポートされないシナリオ

- Employee Central では、オンボードの間に、個人のメールと電話がプライマリとして設定されます。 書き戻しアプリでは、この設定を切り替えることができず、勤務先メールと勤務先電話をプライマリとして設定することはできません。
- Employee Central では、勤務先電話がプライマリとして設定されます。 書き戻しアプリではこれを変更できず、携帯電話をプライマリとして設定することはできません。
- 書き戻しアプリでは、現在のプライマリ フラグの設定を読み取ることはできず、書き込み操作に同じ値を使用することはできません。 属性マッピングで構成されているフラグの値が常に使用されます。
- 書き戻しコネクタを含む SuccessFactors プロビジョニング コネクタは、21Vianet (中国) が運営する Microsoft テナントではサポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/scim-support-in-entra-id"} -->
## Microsoft Entra ID での SCIM サポート - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/scim-support-in-entra-id
- Service: entra-id / app-provisioning
- Article date: 2026-03-31
- Summary: Microsoft Entra ID が、SaaS アプリケーションのプロビジョニング クライアントとして、また SCIM API を通じて SCIM サービス プロバイダーとして SCIM 標準をサポートする方法について説明します。

Microsoft Entra ID は、プロビジョニング シナリオに応じて、複数の方法で **クロスドメイン ID 管理 (SCIM) 2.0 標準のシステム** をサポートしています。 Microsoft Entra は次のように機能します。

- **SCIM クライアント**。Microsoft Entra からパートナー アプリケーションにユーザーとグループをプロビジョニングします。
- **SCIM サービス プロバイダー**。外部システムがユーザーとグループを Microsoft Entra に直接プロビジョニングできる SCIM API を公開します。

この記事では、Microsoft Entra ID での SCIM サポートの概要を説明し、シナリオに適用される機能とドキュメントを理解するのに役立ちます。

### Microsoft Entra ID における SCIM の概要

[Image: Microsoft Entra ID での SCIM サポート シナリオを示す図]

| シナリオ | Microsoft Entra が果たす役割 | 一般的なユース ケース |
| --- | --- | --- |
| SaaS アプリへのユーザーとグループのプロビジョニング | SCIM クライアント | ServiceNow、Zoom、Dropbox などのアプリケーションでアカウントを自動的に作成、更新、プロビジョニング解除する |
| ユーザーとグループを Microsoft Entra にプロビジョニングする | SCIM サービス プロバイダー | 人事システム、パートナー プラットフォーム、またはカスタム ID パイプラインのユーザーを Microsoft Entra ID に同期する |

### SCIM クライアントとしての Entra (アプリ のプロビジョニング)

Microsoft Entra ID には、 **SCIM クライアント**として機能する組み込みのプロビジョニング サービスが含まれています。 このモデルでは、Microsoft Entra ID は SCIM 準拠のエンドポイントを公開するアプリケーションに SCIM 要求を送信します。

#### 実行可能事項

SCIM クライアントとして機能する場合、Microsoft Entra ID は次のことができます。

- サード パーティ製アプリケーションでのユーザーのプロビジョニングとプロビジョニング解除
- ユーザー属性の同期を時間の経過と同時に維持する
- グループとグループ メンバーシップの作成と管理
- SCIM エンドポイントを公開するクラウドとオンプレミスの両方のアプリケーションをサポートする
- 属性マッピングを使用して条件付きロジックと変換を適用する

この機能は、カスタム同期コードを記述せずに SaaS アプリケーションのアクセス ライフサイクル管理を自動化するために、IT 管理者が一般的に使用します。

#### 詳細については、こちらをご覧ください

- [ユーザー プロビジョニング用の SCIM エンドポイントを開発する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)
- [アプリ固有のプロビジョニングのチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)
- [SCIM 2.0 のコンプライアンスに関する既知の問題](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-config-problem-scim-compatibility)

### SCIM サービス プロバイダーとしての Microsoft Entra (SCIM API)

Microsoft Entra ID は、SCIM **2.0 API を通じて SCIM** **サービス プロバイダー**としても機能します。 このモデルでは、HR システムなどの外部システムは、Microsoft Entra ID によって公開されている SCIM API エンドポイントを呼び出して、ID データを管理できます。

#### 実行可能事項

Microsoft Entra ID SCIM API を使用すると、次のことができます。

- Microsoft Entra でユーザーを作成、読み取り、更新、削除する
- セキュリティ グループと Microsoft 365 グループの作成と管理
- グループ メンバーシップの管理
- 標準の SCIM エンドポイントを使用して、サポートされているスキーマ、リソースの種類、およびサービス機能を検出する
- 既存の SCIM クライアント、コネクタ、およびオートメーション フレームワークを使用して統合する

この機能は **、標準ベースの ID ライフサイクルの自動化**用に設計されています。特に、顧客やパートナーが既に SCIM を使用して HR アプリ、ミドルウェア サービス、その他の ID およびアクセス プラットフォームなどのアプリケーションと統合している場合です。

#### 一般的なシナリオ

- 人事システムから Microsoft Entra ID へのユーザーの同期
- 別の ID プロバイダーから Microsoft Entra ID への ID の移行
- 複数のプラットフォームで 1 つの SCIM ベースのプロビジョニング パイプラインを使用する
- パートナーが業界標準のプロトコルを使用して顧客の Microsoft Entra ID テナントに ID をプロビジョニングできるようにする

#### 詳細については、こちらをご覧ください

- [Microsoft Entra SCIM 2.0 API の構成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/enable-scim-api)
- [Microsoft Entra ID SCIM API エンドポイント](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/entra-id-scim-api-reference)
- [Microsoft Entra ID SCIM API スキーマ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/entra-id-scim-api-schema-documentation)

### Microsoft Graph と SCIM: 使用するタイミング

Microsoft Entra ID では、ユーザーとグループを管理するための **Microsoft Graph API と SCIM API の両方** がサポートされています。 適切な選択は、統合のニーズによって異なります。

#### 次の必要がある場合は、Microsoft Graph を使用します。

- Microsoft ID、セキュリティ、および Microsoft 365 の機能の全幅にアクセスする
- 豊富なリレーションシップと高度な ID 機能を使用する
- Microsoft 中心の高度に統合されたアプリケーションを構築する
- ライフサイクル プロビジョニングを超える Microsoft 固有の概念を使用する

#### 次の場合は SCIM を使用します。

- **業界標準の SCIM 2.0 プロトコル**を使用して統合する
- 既存の SCIM クライアント、コネクタ、またはプロビジョニング フレームワークを再利用する
- 主に**ユーザーとグループのライフサイクル管理**に重点を置く
- 複数のプラットフォームと ID プロバイダー間で ID プロビジョニングを標準化する

多くのお客様とパートナーは、シナリオに応じて、ライフサイクル同期に SCIM と Microsoft Graph の **両方** のアプローチを使用し、Microsoft 固有の豊富な機能を使用しています。

### まとめ

Microsoft Entra ID は、SCIM を 2 つの補完的な役割でサポートすることで、柔軟な標準ベースの ID プロビジョニングを提供します。

- **SCIM クライアント**として、Microsoft Entra ID からビジネス アプリケーションへのプロビジョニングを有効にします。
- **SCIM サービス プロバイダーとして、SCIM** API を使用してビジネス アプリケーションから Microsoft Entra ID へのプロビジョニングを有効にします。

これらの機能は、Microsoft Graph と共に、アーキテクチャ、ツール、および長期的な ID 戦略に最適なモデルを使用して Microsoft Entra と統合する選択肢を顧客とパートナーに提供します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/scim-validator-tutorial"} -->
## チュートリアル - Microsoft Entra プロビジョニング サービスとの互換性について SCIM エンドポイントをテストします。 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/scim-validator-tutorial
- Service: entra-id / app-provisioning
- Article date: 2025-10-06
- Summary: このチュートリアルでは、Microsoft Entra SCIM Validator を使って、プロビジョニング サーバーが Azure SCIM クライアントと互換性があることを検証する方法について説明します。

このチュートリアルでは、Microsoft Entra SCIM Validator を使って、プロビジョニング サーバーが Azure SCIM クライアントと互換性があることを検証する方法について説明します。 このチュートリアルは、SCIM 互換サーバーを構築して、Microsoft Entra プロビジョニング サービスで ID を管理する必要がある開発者を対象としています。

注

Microsoft Entra SCIM 検証ツールを使用して、エンドポイントをビルド中にテストします。 Microsoft Entra アプリ ギャラリーのオンボーディングに必要な検証ではありません。 ユーザー プロビジョニング統合をギャラリーに発行するには、Azure Logic Apps検証テンプレートを実行し、結果を送信します。 詳細については、「[Microsoft Entra アプリ ギャラリーのユーザー プロビジョニングの検証](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/validate-user-provisioning-app-gallery)」を参照してください。

このチュートリアルでは、次の作業を行う方法について説明します。

- テスト方法を選択する
- テスト方法を構成する
- SCIM エンドポイントを検証する

### 前提条件

- アクティブなサブスクリプションを持つ Microsoft Entra アカウント。 [無料でアカウントを作成できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- SCIM 2.0 標準に準拠し、プロビジョニング サービス要件を満たす SCIM エンドポイント。 詳細については、「[チュートリアル: Microsoft Entra ID の SCIM エンドポイントのプロビジョニングを開発および計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)」を参照してください。

### テスト方法を選択する

最初の手順では、SCIM エンドポイントを検証するテスト方法を選択します。

1. Web ブラウザーを開き、SCIM Validator https://scimvalidator.microsoft.com/ に移動します。
2. 3 つのテスト オプションのいずれかを選択します。 既定の属性を使用する、スキーマを自動的に検出する、またはスキーマをアップロードすることができます。

    [Image: SCIM Validator のメイン ページのスクリーンショット。]

**既定の属性を使用する** - システムでは既定の属性が用意されており、ニーズに合わせてこれを変更します。

**スキーマを検出する** - エンドポイントで /Schemas がサポートされている場合、このオプションによって、サポートされている属性をツールで検出できます。 このオプションを使用すると、アプリのビルド時にアプリを更新するオーバーヘッドが軽減されるため、このオプションをお勧めします。

**Upload Microsoft Entra Schema (Microsoft Entra スキーマのアップロード)** - Microsoft Entra ID 上のサンプル アプリからダウンロードしたスキーマをアップロードします。

### テスト方法を構成する

テスト方法を選択したら、次の手順ではこれを構成します。

[Image: SCIM Validator 属性ページのスクリーンショット。]

1. 既定の属性オプションを使用している場合は、指定されたすべてのフィールドに入力します。
2. スキーマの検出オプションを使用している場合は、SCIM エンドポイントの URL とトークンを入力します。
3. スキーマをアップロードする場合は、アップロードする .json ファイルを選択します。 このオプションには、Microsoft Entra 管理センター上のサンプル アプリからエクスポートした .json ファイルを指定できます。 スキーマをエクスポートする方法については、「[方法: プロビジョニング構成をエクスポートし、既知の良好な状態にロールバックする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/export-import-provisioning-configuration#export-your-provisioning-configuration)」を参照してください。

注

"グループ属性" をテストするには、*[Enable Group Tests] (グループ テストを有効にする)* を選択してください。

1. 属性リストの末尾にある [属性の追加] オプションと、ページの右側にあるマイナス (-) 記号を使用して、ユーザーとグループの両方の種類のリスト属性を必要に応じて編集します。
2. ユーザー属性とグループ属性の両方の一覧から結合プロパティを選択します。

注

結合プロパティ (一致属性とも呼ばれます) は、ソースでユーザーとグループのリソースを一意に照会し、ターゲット システムで照合できる属性です。

### SCIM エンドポイントを検証する

最後に、エンドポイントをテストして検証する必要があります。

1. **[Test Schema] (スキーマのテスト)** を選択してテストを開始します。
2. 合格したテストと失敗したテストの概要を含む結果を確認します。
3. **[詳細の表示]** タブを選択し、問題を確認して修正します。
4. すべてのテストに合格するまで、スキーマのテストを続行します。

    [Image: SCIM Validator の結果ページのスクリーンショット。]

#### SCIM バリデーターが実行する検証内容を確認してください

**新しいユーザーの作成**

- POST /Users – 完全な JSON ペイロードを持つ新しいユーザーを作成します。
    - エンドポイントから HTTP 201 が返される
    - POST 応答に作成されたユーザー ID が含まれています
- GET /Users?filter={joiningProperty} eq "value" – 結合プロパティをフィルター処理して作成を検証します。
    - GET は作成されたユーザーを返します
    - GET から返される値は、POST 要求から渡された値と一致します (エンドポイントによって異なります)
- DELETE /Users - テスト ユーザーをクリーンアップします。 -ハード削除がサポートされている場合にのみ呼び出されます

**重複するユーザーの作成**

- POST /Users – 既存のユーザーと同じペイロード (同じ一意/結合属性を持つ) を使用してユーザーを作成しようとします。
    - 最初の作成要求で HTTP 201 を返す
    - 2 回目の作成要求で HTTP 409 を返す

**属性の追加**

- POST /Users - ユーザー リソースを作成します
    - HTTP 2xx の成功
- PATCH /Users/{id} – JSON パッチ ドキュメント (追加操作を含む) を使用して、追加の必須ではない属性を挿入します。
- GET /Users?filter={joiningProperty} eq "value" – 追加された属性を確認するユーザーを取得します。
    - ユーザーが返される
    - 挿入された属性がユーザーに存在するようになりました

**ユーザー属性の置換**

- POST /Users - ユーザー リソースを作成します
    - HTTP 2xx の成功
- PATCH /Users/{id} – JSON パッチ ドキュメント (置換操作を使用) を送信して、1 つ以上の属性を更新します。
- GET /Users?filter={joiningProperty} eq "value" – 更新された属性が正しく適用されていることを確認します。
    - ユーザーが返される
    - 更新された属性がユーザーに存在する

**結合プロパティの更新**

- POST /Users - ユーザー リソースを作成します
    - HTTP 2xx の成功
- PATCH /Users/{id} – JSON パッチ ドキュメントを使用して結合プロパティ (userName など) を更新します。
- GET /Users?filter={joiningProperty} eq "newValue" – 結合プロパティが更新されたことを確認します。
    - ユーザーに対して結合プロパティが更新されます

**アクティブな属性を False に更新する**

- POST /Users/ - スキーマに基づいてリソースを作成します
    - HTTP 2xx の成功
    - GET 要求で無効なユーザーを返す必要がある
- PATCH /Users/{id} – "active" 属性を false に設定する JSON パッチ ドキュメントを発行します。
    - HTTP 2xx の成功
- GET /Users?filter={joiningProperty} eq "value" – アクティブな属性が false になったことを確認するためにユーザーを取得します。
    - 返されたユーザー レコードは、ACTIVE=FALSE である必要があります。

**新しいグループの作成**

- POST /Groups – 完全な JSON ペイロードを持つ新しいグループを作成します。
    - エンドポイントから HTTP 201 が返される
    - POST 応答に作成されたグループ ID が含まれています
- GET /Group?filter={joiningProperty} eq "value" – 結合プロパティをフィルター処理して作成を検証します。
    - GET は作成されたグループを返します
    - GET から返される値は、POST 要求から渡された値と一致します (エンドポイントによって異なります)
- DELETE /Groups - テスト ユーザーをクリーンアップします。
    - ハード削除がサポートされている場合にのみ呼び出されます

**重複するグループを作成する**

- POST /Groups – 既存のグループと同じペイロード (同じ一意/結合属性を持つ) を使用してグループを作成しようとします。
    - 最初の作成要求で HTTP 201 を返す
    - 2 回目の作成要求で HTTP 409 を返す

**グループ属性の更新**

- POST /Groups - 属性を更新する新しいグループ リソースを作成します。
    - POST は HTTP 2xx を返します
- PATCH /Groups/{id} – 置換操作を使用して JSON パッチ ドキュメントを送信し、既存のグループの 1 つ以上の属性 (メンバーを除く) を更新します。
    - PATCH が成功を返す (HTTP 2xx)
- GET /Groups?filter={joiningProperty} eq "value" – グループの属性が正しく更新されたことを確認します。
    - GET は、修正プログラムが適用されたグループを返します
    - 返されたグループの属性は、PATCH 要求で変更された属性と一致します

**新しいグループ リソースを作成する**

- POST /Groups - メンバーを追加する新しいグループ リソースを作成します
    - POST は HTTP 2xx を返します
- POST /Users – グループ メンバーとして使用する新しいユーザー リソースを作成します。
    - POST は HTTP 2xx を返します
- PATCH /Groups/{id} – JSON パッチ ドキュメントを使用して、新しく作成されたユーザーの識別子をグループに追加します。
    - PATCH 成功を返します

### SCIM Validator での式の使用

SCIM Validator では、式を使用して属性に必要な値を生成できます。

#### 式を使用する方法

1. [属性] ページに移動します。
2. カスタマイズする属性の値の**列**に希望の式を入力します。
3. テストの実行

注

これらの式は、User 属性と Group 属性の両方で機能します。

#### 利用可能な式

次の表は、使用可能な式の一覧を示しています。

| **Expression** | **意味** | **例** | **結果** |
| --- | --- | --- | --- |
| generateRandomString {文字数のカウント} | 指定したアルファベット文字の数でランダムな文字列を生成する | {%generateRandomString 6%}@contoso.com | CXJHYP@contoso.com |
| generateRandomNumber {Count of Numbers} | 指定した桁数の乱数を生成する | {%generateRandomNumber 4%} | 8821 |
| 英数字を生成します {文字数} | 指定した文字数のアルファベットと数字の組み合わせでランダムな文字列を生成する | {%generateAlphaNumeric 7%} | 59Q2M9W |
| アルファベット、数字、特殊文字を生成する {文字数} | 指定した文字数に基づいて、アルファベット、数字、特殊文字が混在するランダム文字列を生成する | {%generateAlphaNumericWithSpecialCharacters 8%}試験 | D385N05’TEST |

式の前または後に値を追加して目的の結果を得ることができます。たとえば、**{% generateRandomString 6 %}@contoso.com** を userName 属性の値フィールドに追加すると、contoso.com ドメインを保持しながらすべてのテストで新しい userName 値が生成されます。

### リソースをクリーンアップする

テストで作成した Azure リソースが不要になった場合は、必ず削除してください。

### Microsoft Entra SCIM Validator に関する既知の問題

- 論理的な削除 (無効化) はまだサポートされていません。
- タイム ゾーン形式がランダムに生成されるため、この検証を試みるシステムではエラーが発生します。
- パッチ ユーザーの削除属性によって、特定のシステムの必須属性の削除が試行される場合があります。 このようなエラーは無視する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/skip-out-of-scope-deletions"} -->
## Microsoft Entra アプリケーション プロビジョニングでスコープ外のユーザーの削除をスキップする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: Microsoft Entra IDのスコープ外ユーザーのプロビジョニング解除の既定の動作をオーバーライドする方法について説明します。

既定では、Microsoft Entra プロビジョニング エンジンは、スコープ外のユーザーを論理的に削除または無効にします。 ただし、Workday to AD User Inbound Provisioning などの特定のシナリオでは、この動作が予期されていない場合があり、この既定の動作をオーバーライドしたいことがあります。

この記事では、Microsoft Graph API と Microsoft Graph API エクスプローラーを使用して、スコープ外のアカウントの処理を制御するフラグ ***SkipOutOfScopeDeletions*** を設定する方法について説明します。

- ***SkipOutOfScopeDeletions*** が 0 (false) に設定されている場合、スコープ外のアカウントはターゲットで無効になります。
- ***SkipOutOfScopeDeletions*** が 1 (true) に設定されている場合、スコープ外のアカウントはターゲットで無効になりません。 このフラグは、*プロビジョニング アプリ* レベルで設定され、Graph APIを使用して構成できます。

この構成は *Workday からActive Directoryへのユーザープロビジョニング* アプリにおいて広く使用されているため、次の手順では Workday アプリケーションのスクリーンショットを使用しています。 ただし、この構成は、ServiceNow、Salesforce、Dropbox などの *他のすべてのアプリ*でも使用できます。 この手順を正常に完了するには、まずアプリのアプリ プロビジョニングを設定する必要があります。 各アプリには独自の構成に関する記事があります。 たとえば、Workday アプリケーションを構成するには、「[Tutorial: ユーザー プロビジョニングをMicrosoft Entraするように Workday を構成する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-cloud-only-tutorial)を参照してください。 SkipOutOfScopeDeletions は、テナント間同期では機能しません。

### 手順 1:プロビジョニング アプリのサービス プリンシパル ID (オブジェクト ID) を取得します

1. 少なくとも [Microsoft Entra 管理センター](https://entra.microsoft.com)[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**に移動します。
3. アプリケーションを選択し、プロビジョニング アプリの [プロパティ] セクションに移動します。 この例では、Workday を使用しています。
4. *[オブジェクト ID*] フィールドの GUID 値をコピーします。 この値はアプリの **ServicePrincipalId** とも呼ばれ、Graph エクスプローラーの操作で使用されます。

### 手順 2: Microsoft Graph Explorer にサインインする

1. Microsoft Graph エクスプローラー
2. [MicrosoftでSign-In] ボタンを選択し、少なくとも [Application Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) ロールを持つユーザーとしてサインインします。

    [Image: Microsoft Graph Explorer サインインのスクリーンショット]
3. サインインに成功すると、左側のペインにユーザー アカウントの詳細が表示されます。
4. [ **アクセス許可の変更** ] タブを選択し、 `Synchronization.ReadWrite.All` アクセス許可に同意します。 このアクセス許可は、次の手順のGraph API クエリに必要です。

### 手順 3:既存のアプリの資格情報と接続の詳細を取得します

Microsoft Graph エクスプローラーで、[servicePrincipalId] を、Step 1 から抽出された ServicePrincipalId に置き換えて、次の GET クエリを実行します。

```http
   GET https://graph.microsoft.com/beta/servicePrincipals/[servicePrincipalId]/synchronization/secrets
```

[Image: GET ジョブ クエリのスクリーンショット。]

応答をテキスト ファイルにコピーします。 それは、示されている JSON テキストのようになります。デプロイに固有の値が黄色で強調表示されています。 緑色で強調表示されている行を末尾に追加し、青色で強調表示されている Workday 接続パスワードを更新します。

[Image: GET ジョブ応答のスクリーンショット。]

マッピングに追加する JSON ブロックを次に示します。

```json
{
  "key": "SkipOutOfScopeDeletions",
  "value": "True"
}
```

### 手順 4:SkipOutOfScopeDeletions フラグを使用して、シークレット エンドポイントを更新します

Graph エクスプローラーでコマンドを実行し、 ***SkipOutOfScopeDeletions*** フラグを使用してシークレット エンドポイントを更新します。

URL で、[servicePrincipalId] を**手順 1** で抽出した ServicePrincipalId と置き換えます。

```http
   PUT https://graph.microsoft.com/beta/servicePrincipals/[servicePrincipalId]/synchronization/secrets
```

手順 3. で更新したテキストを [要求本文] にコピーします。

[Image: PUT 要求のスクリーンショット。]

[クエリの実行] を選択します。

出力は "Success – Status Code 204 (成功 - 状態コード 204)" になるはずです。 エラーが発生した場合は、アカウントに ServicePrincipalEndpoint に対する読み取りまたは書き込みアクセス許可があることを確認する必要がある場合があります。 このアクセス許可を見つけるには、Graph エクスプローラーの [ *アクセス許可の変更* ] タブをクリックします。

[Image: PUT 応答のスクリーンショット。]

### 手順 5:スコープ外のユーザーが無効になっていないことを確認します

特定のユーザーをスキップするようにスコープ規則を更新することで、このフラグが予期される動作になることをテストできます。 この例では、新しいスコープ規則を追加することによって、ID 21173 の従業員 (以前はスコープ内) を除外しています。

[Image: [スコープ フィルターの追加] セクションを示すスクリーンショット。ユーザーの例が強調表示されています。]

次のプロビジョニング サイクルでは、Microsoft Entra プロビジョニング サービスによって、ユーザー 21173 がスコープ外であることが識別されます。 `SkipOutOfScopeDeletions` プロパティが有効になっている場合、そのユーザーの同期規則にメッセージが表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/successfactors-expression-mapping-functions-guide"} -->
## Microsoft Entra ID プロビジョニングの SuccessFactors 式マッピング関数 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/successfactors-expression-mapping-functions-guide
- Service: entra-id / app-provisioning
- Article date: 2025-08-12
- Summary: SuccessFactors を Microsoft Entra ID ユーザー プロビジョニングに構成するときによく使用される式マッピング関数の包括的なガイド。 これらの関数は、SuccessFactors からデータを変換してマップし、Microsoft Entra ID で適切なユーザー属性を作成するのに役立ちます。

この記事では、SuccessFactors を Microsoft Entra ID ユーザー プロビジョニングに構成するときによく使用される式マッピング関数に関する包括的なガイドを提供します。 これらの関数は、SuccessFactors からデータを変換してマップし、Microsoft Entra ID で適切なユーザー属性を作成するのに役立ちます。

### 目次

- 文字列操作関数
- 電子メール アドレスの生成
- アカウント管理ロジック
- 日付関数とアカウントの有効期限
- 組織単位 (OU) の割り当て
- 名前の処理と表示名
- SamAccountName の生成
- ProxyAddresses の構成
- 電話番号の処理
- 国と場所に基づくロジック
- 従業員の分類と臨時従業員
- 高度なシナリオ

### 文字列操作関数

#### 基本的な文字列操作

**シナリオ 1**: 8 文字の文字列を作成するために、先行ゼロで人物 ID を埋め込みます。

**ターゲット属性**: employeeId

```
Replace(Join("","00000000",[personIdExternal]), ,"(.*?)(?<id>.{0,8})$", ,"${id}", ,)
```

**例：**

- **入力値**: [personIdExternal] = "12345"
- **式の出力**: `00012345`

**シナリオ 2**: コスト センターを先頭にゼロで埋め込み、10 文字にする必要があります。

**ターゲット属性**: extensionAttribute1

```
Replace(Join("","0000000000",[costCenterId]), ,"(.*?)(?<id>.{0,10})$", ,"${id}", ,)
```

**例：**

- **入力値**: [costCenterId] = "567"
- **式の出力**: `0000000567`

**シナリオ 3**: 埋め込まれた従業員番号にプレフィックスを追加して従業員 ID を作成する必要がある。

**ターゲット属性**: employeeId

```
Append("05",Replace(Join("","000000",[employeeId]), ,"(.*?)(?<id>.{0,6})$", ,"${id}", ,))
```

**例：**

- **入力値**: [employeeId] = "789"
- **式の出力**: `05000789`

#### テキスト の大文字と小文字の変換

**シナリオ 1**: 名前を大文字から大文字に変換する必要があります (タイトル ケース)。

**ターゲット属性**: sn

```
Join("",Mid([lastName],1,1),ToLower(Mid([lastName],2,64)))
```

**例：**

- **入力値**: [lastName] = "JOHNSON"
- **式の出力**: `Johnson`

**シナリオ 2**: 大文字と小文字を適切に書式設定して表示名を作成する必要がある。

**ターゲット属性**: displayName

```
Join(", ", Join("",Mid([lastName],1,1),ToLower(Mid([lastName],2,64))), Join("",Mid([firstName],1,1),ToLower(Mid([firstName],2,64))))
```

**例：**

- **入力値**: [lastName] = "SMITH", [firstName] = "JOHN"
- **式の出力**: `Smith, John`

**シナリオ 3**: スペースをピリオドに置き換えて、スペースで名前を処理する必要があります。

**ターゲット属性**: givenName

```
Replace([firstName]," ", , ,".", , )
```

**例：**

- **入力値**: [firstName] = "Mary Ann"
- **式の出力**: `Mary.Ann`

### 電子メール アドレスの生成

#### 基本的な電子メールの生成

**シナリオ 1**: 会社名と姓を使用して、会社固有のドメインで電子メール アドレスを生成する必要があります。

**ターゲット属性**: mail

```
SelectUniqueValue(
    Switch([company], 
        Join("@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], [lastName]))), "contoso.com"),
        "Contoso", Join("@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], [lastName]))), "contoso.com"),
        "Fabrikam", Join("@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], [lastName]))), "fabrikam.com"),
        "Woodgrove", Join("@", NormalizeDiacritics(StripSpaces(Join(".", Mid([firstName],1,1), [lastName]))), "woodgrove.com")
    ),
    Switch([company], 
        Join("@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], Mid([middleName],1,1), [lastName]))), "contoso.com"), 
        "Contoso", Join("@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], Mid([middleName],1,1), [lastName]))), "contoso.com"), 
        "Fabrikam", Join("@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], Mid([middleName],1,1), [lastName]))), "fabrikam.com"), 
        "Woodgrove", Join("@", NormalizeDiacritics(StripSpaces(Join(".", Mid([firstName],1,1), Mid([middleName],1,1), [lastName]))), "woodgrove.com")
    )
)
```

**例：**

- **入力値**: [company] = "Contoso", [firstName] = "John", [lastName] = "Smith", [middleName] = "Michael"
- **式の出力**: `john.smith@contoso.com` (最初のオプションを使用した場合は `john.m.smith@contoso.com` )

#### 従業員グループベースの電子メールの生成

**シナリオ 1**: 従業員の分類 (永続的と一時的) に基づいて異なる電子メール形式を生成する必要がある。

**ターゲット属性**: userPrincipalName

```
StripSpaces(NormalizeDiacritics(Switch([custom06], 
    Join("", [firstName], ".", [lastName], "@ltts.com"), 
    "Temporary", Join("", [firstName], ".", [lastName], "_ext@ltts.com"), 
    "External", Join("", [firstName], ".", [lastName], "_ext@ltts.com"), 
    "Permanent", Join("", [firstName], ".", [lastName], "@ltts.com")
)))
```

**例：**

- **入力値**: [custom06] = "Temporary", [firstName] = "Sarah", [lastName] = "Wilson"
- **式の出力**: `sarah.wilson_ext@ltts.com`

#### 企業ドメインの検証

**シナリオ 1**: 特定の企業ドメインに属する電子メール アドレスのみをフローさせる必要がある。

**ターゲット属性**: mail

```
IgnoreFlowIfNullOrEmpty(IIF(InStr([emailAddress],"@contoso.com")=0,"",[emailAddress]))
```

**例：**

- **入力値**: [emailAddress] = "john.doe@contoso.com"
- **式の出力**: `john.doe@contoso.com`
- **代替入力**: [emailAddress] = "john.doe@external.com"
- **代替出力**: *(空 - 無視)*

#### 既存のメールの保存

**シナリオ 1**: 特定の部門では SuccessFactors からの既存のメールを使用するが、他の部門には新しいメールを生成する必要がある。

**ターゲット属性**: userPrincipalName

```
SelectUniqueValue(
    Switch([divisionId], 
        Join("@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], [lastName]))), "contoso.com"), 
        "8900", [email]
    ), 
    Join("1@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], [lastName]))), "contoso.com"), 
    Join("2@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], [lastName]))), "contoso.com")
)
```

**例：**

- **入力値**: [divisionId] = "8900"、[email] = "existing.user@company.com"、[firstName] = "Mike"、[lastName] = "Brown"
- **式の出力**: `existing.user@company.com`
- **代替入力**: [divisionId] = "1200"、[firstName] = "Mike"、[lastName] = "Brown"
- **代替出力**: `mike.brown@contoso.com`

### アカウント管理ロジック

#### 基本的なアカウントの状態

**シナリオ 1**: 雇用状態に基づいてアカウントを無効にする必要があるかどうかを判断する必要があります。

**ターゲット属性**: accountDisabled

```
Switch([emplStatus], "False", "741", "False", "749", "True", "746", "True")
```

**例：**

- **入力値**: [emplStatus] = "741"
- **式の出力**: `False` (アカウントが有効)
- **代替入力**: [emplStatus] = "746"
- **代替出力**: `True` (アカウントが無効)

#### アドレス一覧のロジックから非表示にする

**シナリオ 1**: 開始日と終了日に基づいて msExchHideFromAddressLists 属性を設定する必要があります。

**ターゲット属性**: msExchHideFromAddressLists

```
IIF(DateDiff("d", CDate(IIF(IsPresent([startDate]), [startDate], DateAdd("d", "10", Now()))), Now()) >= -1, IIF(DateDiff("d", Now(), CDate(IIF(IsPresent([endDate]), [endDate], DateAdd("d", "1", Now())))) >= 0, "FALSE", "TRUE"), "TRUE")
```

**例：**

- **入力値**: [startDate] = "2025-01-15", [endDate] = "2025-12-31" (現在の日付: 2025-07-30)
- **式の出力**: `FALSE` (アドレス一覧から非表示ではありません)
- **代替入力**: [startDate] = "2025-01-15", [endDate] = "2025-06-30"
- **代替出力**: `TRUE` (アドレス一覧から非表示)

#### 複雑な従業員の状態ロジック

**シナリオ 1**: ユーザー設定フィールドと採用日に基づいてアカウントを無効にする複雑な要件があります。

**ターゲット属性**: accountEnabled

```
Switch([empNavCustomString3], "True",
"62220", Switch([cust_hiredate], 
    IIF(DateDiff("d", CDate([cust_hiredate]), Now()) = 3, 
        Switch([cust_customDate67], 
            IIF(DateDiff("d", CDate([cust_customDate67]), Now())=0, "False", "True"),
            "", "False" 
        ), "True"
    ),
    "", "True"
))
```

**例：**

- **入力値**: [empNavCustomString3] = "62220", [cust\_hiredate] = "2025-07-27", [cust\_customDate67] = "2025-07-30" (現在の日付: 2025-07-30)
- **式の出力**: `False` (アカウントが有効)
- **代替入力**: [empNavCustomString3] = "62220", [cust\_hiredate] = "2025-07-27", [cust\_customDate67] = "2025-07-29"
- **代替出力**: `True` (アカウントが無効)

### 日付関数とアカウントの有効期限

#### 基本アカウントの有効期限

**シナリオ 1**: SuccessFactors の終了日に基づいてアカウントの有効期限を設定する必要があります。

**ターゲット属性**: accountExpires

```
Switch([endDate], 
    NumFromDate(Join("", FormatDateTime([endDate], "M/d/yyyy hh:mm:ss tt", "yyyy-MM-dd"), " 23:59:59-05:00")), 
    "", "9223372036854775807"
)
```

**例：**

- **入力値**: [endDate] = "12/31/2025 12:00:00 AM"
- **式の出力**: `133835135990000000` (2025-12-31 23:59:59-05:00 の数値表現)
- **代替入力**: [endDate] = "" (空)
- **代替出力**: `9223372036854775807` (無期限)

#### 雇用の種類に基づく有効期限

**シナリオ 1**: 雇用の種類 (永続的と請負業者) に基づいて異なるアカウントの有効期限ルールを設定する必要があります。

**ターゲット属性**: accountExpires

```
Switch([employmentType], 
    NumFromDate(Join("",FormatDateTime([endDate], ,"M/d/yyyy hh:mm:ss tt","yyyy-MM-dd")," 23:59:59-05:00")),
    "PM", NumFromDate(Join("",FormatDateTime(DateAdd("yyyy", 60, CDate([DOB])), ,"M/d/yyyy hh:mm:ss tt","yyyy-MM-dd")," 23:59:59-05:00")),
    "CON", NumFromDate(Join("",FormatDateTime([endDate], ,"M/d/yyyy hh:mm:ss tt","yyyy-MM-dd")," 23:59:59-05:00"))
)
```

**例：**

- **入力値**: [employmentType] = "PM", [DOB] = "1/15/1990 12:00:00 AM"
- **式の出力**: `158488415990000000` (2050-01-15 23:59:59-05:00、生後 60 年後の数値表現)
- **代替入力**: [employmentType] = "CON", [endDate] = "12/31/2025 12:00:00 AM"
- **代替出力**: `133835135990000000` (2025-12-31 23:59:59-05:00 の数値表現)

#### イベントの理由ベースのアカウントの有効期限

**シナリオ 1**: 特定の終了イベントと退職日に基づいてアカウントの有効期限を設定する必要があります。

**ターゲット属性**: accountExpires

```
Switch([event], 
    IIF(IsPresent([latestTerminationDate]), NumFromDate(Join("", FormatDateTime([latestTerminationDate], , "M/d/yyyy hh:mm:ss tt", "yyyy-MM-dd"), " 23:59:59-08:00")), "9223372036854775807"), 
    "SEVUNSATP", NumFromDate(Join("", FormatDateTime([severanceStartDate], , "M/d/yyyy hh:mm:ss tt", "yyyy-MM-dd"), " 23:59:59-08:00")), 
    "SEVPOSELIM", NumFromDate(Join("", FormatDateTime([severanceStartDate], , "M/d/yyyy hh:mm:ss tt", "yyyy-MM-dd"), " 23:59:59-08:00")), 
    "POSELIM", NumFromDate(Join("", FormatDateTime([severanceStartDate], , "M/d/yyyy hh:mm:ss tt", "yyyy-MM-dd"), " 23:59:59-08:00"))
)
```

**例：**

- **入力値**: [event] = "SEVUNSATP", [severanceStartDate] = "8/15/2025 12:00:00 AM"
- **式の出力**: `133877247990000000` (2025-08-15 23:59:59-08:00 の数値表現)
- **代替入力**: [event] = "REGULAR", [latestTerminationDate] = "9/30/2025 12:00:00 AM"
- **代替出力**: `133886207990000000` (2025-09-30 23:59:59-08:00 の数値表現)

#### 日付形式の処理

**シナリオ 1**: NumFromDate でエラーが発生する "12/31/9999" のような無効な日付を処理する必要があります。

**ターゲット属性**: accountExpires

```
Switch([endDate],
    NumFromDate(Join("", FormatDateTime([endDate], "M/d/yyyy hh:mm:ss tt", "yyyy-MM-dd"), "T23:59:59-04:00")),
    "12/31/9999 12:00:00 AM", NumFromDate("2099-12-31T23:59:59-04:00")
)
```

**例：**

- **入力値**: [endDate] = "12/31/9999 12:00:00 AM"
- **式の出力**: `441481535990000000` (2099-12-31T23:59:59-04:00 の数値表現)
- **代替入力**: [endDate] = "8/15/2025 12:00:00 AM"
- **代替出力**: `133877283990000000` (2025-08-15T23:59:59-04:00 の数値表現)

### 組織単位 (OU) の割り当て

#### 部門ベースの OU の割り当て

**シナリオ 1**: 部署に基づいて異なる OU にユーザーを配置する必要がある。

**ターゲット属性**: parentDistinguishedName

```
Switch([department], "OU=SuccessFactors,DC=contoso,DC=com", 
    "Engineering SG", "OU=Engineering,OU=SuccessFactors,DC=contoso,DC=com", 
    "Shared Services", "OU=Shared Services,OU=SuccessFactors,DC=contoso,DC=com", 
    "Retail - Finance", "OU=Retail Finance,OU=SuccessFactors,DC=contoso,DC=com", 
    "Information Technology BR", "OU=Information Technology,OU=SuccessFactors,DC=contoso,DC=com", 
    "Development", "OU=Development,OU=SuccessFactors,DC=contoso,DC=com"
)
```

**例：**

- **入力値**: [department] = "Engineering SG"
- **式の出力**: `OU=Engineering,OU=SuccessFactors,DC=contoso,DC=com`
- **代替入力**: [department] = "Marketing"
- **代替出力**: `OU=SuccessFactors,DC=contoso,DC=com` (既定)

#### 雇用状況に基づく OU の割り当て

**シナリオ 1**: 雇用状態と場所に基づいて、ユーザーを異なる OU に割り当てる必要があります。

**ターゲット属性**: parentDistinguishedName

```
Join("",
Switch([emplStatus], "OU=SFProvisoinngUsers", 
"741", Switch([empJobNavCustomString13],
    "OU=SFProvisoinngUsers",
    "LOC1016", "OU=Mysore",
    "LOC1019", "OU=Baroda",
    "LOC1015", Switch([departmentId],
        "OU=Bangalore",
        "DU1026","OU=IT,OU=Bangalore",
        "DU1025","OU=IT,OU=Bangalore"
        )
    ), 
"749", Switch([empJobNavCustomString13],
    "OU=O365-NoSYNC,OU=SFProvisoinngUsers",
    "LOC1016", "OU=O365-NoSYNC,OU=Mysore",
    "LOC1019", "OU=O365-NoSYNC,OU=Baroda",
    "LOC1015", "OU=O365-NoSYNC,OU=Bangalore"
    )
),
",DC=contoso,DC=com")
```

**例：**

- **入力値**: [emplStatus] = "741", [empJobNavCustomString13] = "LOC1015", [departmentId] = "DU1026"
- **式の出力**: `OU=IT,OU=Bangalore,DC=contoso,DC=com`
- **代替入力**: [emplStatus] = "749", [empJobNavCustomString13] = "LOC1016"
- **代替出力**: `OU=O365-NoSYNC,OU=Mysore,DC=contoso,DC=com`
- **代替入力**: [emplStatus] = "741", [empJobNavCustomString13] = "LOC9999" (定義されていません)
- **代替出力**: `OU=SFProvisoinngUsers,DC=contoso,DC=com` (既定)

#### 国ベースの OU の割り当て

**シナリオ 1**: 非アクティブなユーザーの OU が無効になっている国に基づいて、ユーザーを異なる OU に配置する必要がある。

**ターゲット属性**: parentDistinguishedName

```
Switch([activeEmploymentsCount],
    Switch([country], "OU=Accounts,DC=corp,DC=contoso,DC=com", 
        "Mexico", "OU=Mexico,OU=Accounts,DC=corp,DC=contoso,DC=com", 
        "Sweden", "OU=Sweden,OU=Accounts,DC=corp,DC=contoso,DC=com", 
        "Colombia", "OU=Internal Accounts,OU=Colombia,OU=Accounts,DC=corp,DC=contoso,DC=com", 
        "Brazil", "OU=Internal Accounts,OU=Brazil,OU=Accounts,DC=corp,DC=contoso,DC=com"
    ),
    "0", "OU=DisabledAccounts,OU=Accounts,DC=corp,DC=contoso,DC=com"
)
```

**例：**

- **入力値**: [activeEmploymentsCount] = "1", [country] = "Mexico"
- **式の出力**: `OU=Mexico,OU=Accounts,DC=corp,DC=contoso,DC=com`
- **代替入力**: [activeEmploymentsCount] = "0",[country] = "Sweden"
- **代替出力**: `OU=DisabledAccounts,OU=Accounts,DC=corp,DC=contoso,DC=com` (無効なユーザーは国に関係なく特別な OU に移動します)
- **代替入力**: [activeEmploymentsCount] = "1", [country] = "Germany"
- **代替出力**: `OU=Accounts,DC=corp,DC=contoso,DC=com` (特に定義されていない国の既定の OU)

### 名前の処理と表示名

#### 共通名 (CN) の生成

**シナリオ 1**: 重複のフォールバック オプションを使用して、一意の共通名を生成する必要があります。

**ターゲット属性**: cn

```
SelectUniqueValue(
    NormalizeDiacritics(Join(" ", [firstName], [lastName])),
    NormalizeDiacritics(Join(" ", [firstName], Mid([middleName],1,1), [lastName])),
    NormalizeDiacritics(Join(" ", [firstName], [middleName], [lastName]))
)
```

**例：**

- **入力値**: [firstName] = "ホセ"、[lastName] = "García"、[middleName] = "Antonio"
- **式の出力**: `Jose Garcia` (最初のオプションを使用する場合は `Jose A Garcia` 、最初の 2 つが取得された場合は `Jose Antonio Garcia` )

#### 優先名を持つ表示名

**シナリオ 1**: 使用可能な場合は優先名を使用する表示名を作成する必要があります。それ以外の場合は名にフォールバックします。

**ターゲット属性**: displayName

```
Join(", ", Join("", Mid([lastName], 1, 1), ToLower(Mid([lastName], 2, 64), )), Join("", Mid(Coalesce([preferredName], [firstName]), 1, 1), ToLower(Mid(Coalesce([preferredName], [firstName]), 2, 64), )))
```

**例：**

- **入力値**: [lastName] = "JOHNSON", [preferredName] = "Mike", [firstName] = "Michael"
- **式の出力**: `Johnson, Mike`
- **代替入力**: [lastName] = "SMITH", [preferredName] = "", [firstName] = "Robert"
- **代替出力**: `Smith, Robert`

#### 数値サフィックス付きの表示名

**シナリオ 1**: 重複する数値サフィックスを持つ一意の表示名を生成する必要がある。

**ターゲット属性**: displayName

```
SelectUniqueValue(
    Join("", NormalizeDiacritics(Join("", [lastName], ", ")), NormalizeDiacritics(Switch([preferredName], [preferredName], "", [firstName]))), 
    Join("", NormalizeDiacritics(Join("", [lastName], "2, ")), NormalizeDiacritics(Switch([preferredName], [preferredName], "", [firstName]))), 
    Join("", NormalizeDiacritics(Join("", [lastName], "3, ")), NormalizeDiacritics(Switch([preferredName], [preferredName], "", [firstName]))), 
    Join("", NormalizeDiacritics(Join("", [lastName], "4, ")), NormalizeDiacritics(Switch([preferredName], [preferredName], "", [firstName])))
)
```

**例：**

- **入力値**: [lastName] = "García", [preferredName] = "Mike", [firstName] = "Michael"
- **式の出力**: `Garcia, Mike` (最初のオプションを使用した場合は `Garcia2, Mike` など)
- **代替入力**: [lastName] = "Smith", [preferredName] = "", [firstName] = "John"
- **代替出力**: `Smith, John` (最初のオプションを使用した場合は `Smith2, John` など)

### SamAccountName の生成

#### samAccountName の基本的な生成

**シナリオ 1**: 適切な文字サニタイズを使用して、最初の最初と最後の名前を使用して samAccountName を作成する必要があります。

**ターゲット属性**: sAMAccountName

```
SelectUniqueValue(
    Replace(Mid(Replace(NormalizeDiacritics(StripSpaces(Join("", Mid([firstName],1,1), [lastName]))), , "([\\/\\\\\\[\\]\\:\\;\\|\\=\\,\\+\\*\\?\\<\\>])", , "", , ), 1, 20), , "(\\.)*$", , "", , ),
    Join("",Replace(Mid(Replace(NormalizeDiacritics(StripSpaces(Join("", Mid([firstName],1,1), [lastName]))), , "([\\/\\\\\\[\\]\\:\\;\\|\\=\\,\\+\\*\\?\\<\\>])", , "", , ), 1, 19), , "(\\.)*$", , "", , ),"1"),
    Join("",Replace(Mid(Replace(NormalizeDiacritics(StripSpaces(Join("", Mid([firstName],1,1), [lastName]))), , "([\\/\\\\\\[\\]\\:\\;\\|\\=\\,\\+\\*\\?\\<\\>])", , "", , ), 1, 19), , "(\\.)*$", , "", , ),"2")
)
```

**例：**

- **入力値**: [firstName] = "ホセ", [lastName] = "García-López"
- **式の出力**: `jgarcialopez` (最初のオプションを使用する場合は `jgarcialopez1` 、最初の 2 つが取得された場合は `jgarcialopez2` )

#### 名の長さが可変の SamAccountName

**シナリオ 1**: 名から文字数を増やして重複を処理する必要がある。

**ターゲット属性**: sAMAccountName

```
SelectUniqueValue(
    Replace(Mid(Replace(NormalizeDiacritics(StripSpaces(Join("", Mid([firstName],1,1), [lastName]))), , "([\\/\\\\\\[\\]\\:\\;\\|\\=\\,\\+\\*\\?\\<\\>])", , "", , ), 1, 20), , "(\\.)*$", , "", , ),
    Replace(Mid(Replace(NormalizeDiacritics(StripSpaces(Join("", Mid([firstName],1,2), [lastName]))), , "([\\/\\\\\\[\\]\\:\\;\\|\\=\\,\\+\\*\\?\\<\\>])", , "", , ), 1, 20), , "(\\.)*$", , "", , ),
    Replace(Mid(Replace(NormalizeDiacritics(StripSpaces(Join("", Mid([firstName],1,3), [lastName]))), , "([\\/\\\\\\[\\]\\:\\;\\|\\=\\,\\+\\*\\?\\<\\>])", , "", , ), 1, 20), , "(\\.)*$", , "", , )
)
```

**例：**

- **入力値**: [firstName] = "Christopher", [lastName] = "Anderson"
- **式の出力**: `canderson` (最初のオプションを使用する場合は `chanderson` 、最初の 2 つが取得された場合は `chranderson` )
- **代替入力**: [firstName] = "María", [lastName] = "Rodríguez-Santos"
- **代替出力**: `mrodriguezsantos` (最初のオプションを使用した場合は `marodriguezsantos` 、最初の 2 つが取得された場合は `marrodriguezsantos` )

#### 外部ユーザー名からの SamAccountName

**シナリオ 1**: 電子メール スタイルの外部ユーザー名からユーザー名部分を抽出する。

**ターゲット属性**: sAMAccountName

```
Replace(Mid(Replace(Replace([username],,"(?<id>.*)@(?<domain>.*)",,"${id}",,), , "([\\/\\\\\\[\\]\\:\\;\\|\\=\\,\\+\\*\\?\\<\\>])", , "", , ), 1, 20), , "(\\.)*$", , "", , )
```

**例：**

- **入力値**: [username] = "john.smith@external.com"
- **式の出力**: `johnsmith`

### ProxyAddresses の構成

#### proxyAddresses の基本的なセットアップ

**シナリオ 1**: プライマリ SMTP アドレスとセカンダリ SMTP アドレスを含む複数のプロキシ アドレスを設定する必要がある。

**ターゲット属性**: proxyAddresses

```
Split(
    Join(",", 
        Append("smtp:", Join("@", NormalizeDiacritics(StripSpaces(Join("", "A", [personIdExternal]))), "contoso.mail.onmicrosoft.com")), 
        Append("smtp:", Join("@", NormalizeDiacritics(StripSpaces(Join("", "A", [personIdExternal]))), "contoso.com")), 
        Append("SMTP:", Join("@", Join(".", StripSpaces([firstName]), StripSpaces([lastName])), "contoso.com"))
    ), 
    ","
)
```

**例：**

- **入力値**: [personIdExternal] = "12345", [firstName] = "John", [lastName] = "Smith"
- **式の出力**: `["smtp:A12345@contoso.mail.onmicrosoft.com", "smtp:A12345@contoso.com", "SMTP:John.Smith@contoso.com"]`

#### 除算ベースの proxyAddresses

**シナリオ 1**: 従業員部門に基づいて異なるプロキシ アドレス構成が必要です。

**ターゲット属性**: proxyAddresses

```
Split(
    Switch([divisionId], 
        Join(",", 
            Append("smtp:", Join("@", NormalizeDiacritics(StripSpaces(Join("", "A", [personIdExternal]))), "woodgrove.mail.onmicrosoft.com")), 
            Append("smtp:", Join("@", NormalizeDiacritics(StripSpaces(Join("", "A", [personIdExternal]))), "woodgrove.com")), 
            Append("SMTP:", Join("@", Join(".", StripSpaces([firstName]), StripSpaces([lastName])), "woodgrove.com"))
        ),			
        "EXEC", Join(",", 
            Append("smtp:", Join("@", Join(".", StripSpaces([firstName]), StripSpaces([lastName])), "contoso.com")), 
            Append("SMTP:", [email])
        )
    ),
    ","	
)
```

**例：**

- **入力値**: [divisionId] = "STANDARD", [personIdExternal] = "67890", [firstName] = "Sarah", [lastName] = "Johnson"
- **式の出力**: `["smtp:A67890@woodgrove.mail.onmicrosoft.com", "smtp:A67890@woodgrove.com", "SMTP:Sarah.Johnson@woodgrove.com"]`
- **代替入力**: [divisionId] = "EXEC", [firstName] = "Michael", [lastName] = "Brown", [email] = "mbrown@fabrikam.com"
- **代替出力**: `["smtp:Michael.Brown@contoso.com", "SMTP:mbrown@fabrikam.com"]`

#### 会社固有の proxyAddresses

**シナリオ 1**: 会社の所属に基づいてプロキシ アドレスを生成する必要がある。

**ターゲット属性**: proxyAddresses

```
SelectUniqueValue (
    Switch([company], 
        Append ("SMTP:", Join("@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], [lastName]))), "contoso.com")),
        "NGC", Append ("SMTP:", Join("@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], [lastName]))), "contoso.com")),
        "CNG", Append ("SMTP:", Join("@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], [lastName]))), "contoso.com")),
        "National Energy", Append ("SMTP:", Join("@", NormalizeDiacritics(StripSpaces(Join(".", Mid([firstName],1,1), [lastName]))), "fabrikam.com"))
    ),
    Switch([company], 
        Append ("SMTP:", Join("@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], Mid([middleName],1,1), [lastName]))), "contoso.com")), 
        "NGC", Append ("SMTP:", Join("@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], Mid([middleName],1,1), [lastName]))), "contoso.com")), 
        "CNG", Append ("SMTP:", Join("@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], Mid([middleName],1,1), [lastName]))), "contoso.com")), 
        "National Energy", Append ("SMTP:", Join("@", NormalizeDiacritics(StripSpaces(Join(".", Mid([firstName],1,1), Mid([middleName],1,1), [lastName]))), "fabrikam.com"))
    )
)
```

**例：**

- **入力値**: [company] = "NGC", [firstName] = "必要", [lastName] = "Williams", [middleName] = "James"
- **式の出力**: `SMTP:trinidad.williams@contoso.com` (最初のオプションを使用した場合は `SMTP:trinidad.j.williams@contoso.com` )
- **代替入力**: [company] = "National Energy",[firstName] = "Rebecca", [lastName] = "Thompson", [middleName] = "Marie"
- **代替出力**: `SMTP:r.thompson@fabrikam.com` (または最初のオプションを使用した場合は `SMTP:r.m.thompson@fabrikam.com` )
- **代替入力**: [company] = "Other Company", [firstName] = "David", [lastName] = "Jones", [middleName] = "Paul"
- **代替出力**: `SMTP:david.jones@contoso.com` (既定のドメイン、または最初のオプションを使用した場合は `SMTP:david.p.jones@contoso.com` )

### 電話番号の処理

#### 基本的な電話番号の抽出

**シナリオ 1**: 国番号を含まない電話番号部分を抽出する。

**ターゲット属性**: telephoneNumber

```
Replace(Replace([mobile], , "\\+(?<isdCode>\\d* )(?<phoneNumber>.*)", , "${phoneNumber}", , ), ,"[()\\s-]+", ,"", , )
```

**例：**

- **入力値**: [mobile] = "+1 (555) 123-4567"
- **式の出力**: `5551234567`

**シナリオ 2**: 電話番号から国コードのみを抽出する必要があります。

**ターゲット属性**: c

```
Replace(Replace([mobile], , "\\+(?<isdCode>\\d* )(?<phoneNumber>.*)", , "${isdCode}", , ), ,"[()\\s-]+", ,"", , )
```

**例：**

- **入力値**: [mobile] = "+44 20 7946 0958"
- **式の出力**: `44`

#### 電話番号の既定値

**シナリオ 1**: フィールドが空のときに、既定の電話番号を指定する必要があります。

**ターゲット属性**: telephoneNumber

```
IIF(IsNullOrEmpty([telephoneNumber]),"000-000-0000",[telephoneNumber])
Switch([mobile],[mobile],"","000-000-0000")
```

**例：**

- **入力値**: [telephoneNumber] = ""
- **式の出力**: `000-000-0000`
- **代替入力**: [mobile] = "555-123-4567"
- **代替出力**: `555-123-4567`

#### プライマリ電話のロジック

**シナリオ 1**: プライマリとしてマークする電話番号を決定する必要があります。

**ターゲット属性**: extensionAttribute2

```
Switch(Join("+",Switch([businessPhoneIsPrimary],[businessPhoneIsPrimary],"","other"),Switch([cellPhoneIsPrimary],[cellPhoneIsPrimary],"","other")), "no primary phone", 
"false+false", "no primary phone",
"true+false", "business phone is primary",
"false+true", "cell phone is primary",
"true+true", "business phone is primary, cell phone is primary",
"other+true", "cell phone is primary",
"other+false", "no primary phone",
"true+other", "business phone is primary",
"false+other", "no primary phone")
```

**例：**

- **入力値**: [businessPhoneIsPrimary] = "true", [cellPhoneIsPrimary] = "false"
- **式の出力**: `business phone is primary`
- **代替入力**: [businessPhoneIsPrimary] = "false", [cellPhoneIsPrimary] = "true"
- **代替出力**: `cell phone is primary`
- **代替入力**: [businessPhoneIsPrimary] = "", [cellPhoneIsPrimary] = "true"
- **代替出力**: `cell phone is primary`

### 国と場所に基づくロジック

#### 国コードマッピング

**シナリオ 1**: 国名を ISO 国コードにマップする必要がある。

**ターゲット属性**: c

```
Switch([country], ,
  "Trinidad and Tobago", "TT",
  "Barbados", "BB"
)
```

**例：**

- **入力値**: [country] = "トレニダーアンドトバゴ"
- **式の出力**: `TT`

**シナリオ 2**: 国を数値の国コードにマップする必要があります。

**ターゲット属性**: countryCode

```
Switch([country], ,
  "Trinidad and Tobago", "780",
  "Barbados", "52"
)
```

**例：**

- **入力値**: [country] = "バルバドス"
- **式の出力**: `52`

#### 場所ベースの電子メール ドメイン

**シナリオ 1**: 地理的な場所に基づいて異なる電子メール ドメインを割り当てる必要がある。

**ターゲット属性**: mail

```
SelectUniqueValue (
    Switch([country], 
        Join("@", NormalizeDiacritics(StripSpaces(Join("", [firstName], [lastName]))), "fabrikam.com"),
        "India", Join("@", NormalizeDiacritics(StripSpaces(Join("", [firstName], [lastName]))), "fabrikam.com"),
        "Netherlands", Join("@", NormalizeDiacritics(StripSpaces(Join("", [firstName], [lastName]))), "contoso.com")
    ),
    Switch([country], 
        Join("1@", NormalizeDiacritics(StripSpaces(Join("", [firstName], [lastName]))), "fabrikam.com"),
        "India", Join("1@", NormalizeDiacritics(StripSpaces(Join("", [firstName], [lastName]))), "fabrikam.com"),
        "Netherlands", Join("1@", NormalizeDiacritics(StripSpaces(Join("", [firstName], [lastName]))), "contoso.com")
    )
)
```

**例：**

- **入力値**: [country] = "India", [firstName] = "Raj", [lastName] = "Patel"
- **式の出力**: `raj.patel@fabrikam.com` (最初のオプションを使用した場合は `1raj.patel@fabrikam.com` )
- **代替入力**: [country] = "Netherlands", [firstName] = "Jan", [lastName] = "van der Berg"
- **代替出力**: `jan.vanderberg@contoso.com` (または最初のオプションを使用した場合は `1jan.vanderberg@contoso.com` )
- **代替入力**: [country] = "Germany",[firstName] = "Klaus", [lastName] = "Müller"
- **代替出力**: `klaus.muller@fabrikam.com` (既定のドメイン、または最初のオプションを使用した場合は `1klaus.muller@fabrikam.com` )

### 従業員の分類と臨時従業員

#### 雇用の種類の分類

**シナリオ 1**: 従業員の種類コードを読み取り可能な説明にマップする必要があります。

**ターゲット属性**: extensionAttribute3

```
Switch([employeeType],"Default-value",
    "31202","Employee",
    "31230","Contractor"
)
```

**例：**

- **入力値**: [employeeType] = "31202"
- **式の出力**: `Employee`
- **代替入力**: [employeeType] = "99999"
- **代替出力**: `Default-value`

#### 臨時作業者の識別

**シナリオ 1**: 特定のワーカーを識別し、別の方法で処理する必要があります。

**ターゲット属性**: extensionAttribute4

```
Switch([isContingentWorker], "N/A", "True", "Contractor", "False", "Employee")
```

**例：**

- **入力値**: [isContingentWorker] = "True"
- **式の出力**: `Contractor`

#### 個人用タイトル マッピング

**シナリオ 1**: 個人用タイトル コードを適切なタイトルにマップする必要がある。

```
IgnoreFlowIfNullOrEmpty(Switch([personalTitle], "", "4443", "Dr.", "4444", "Prof.", "4445", "Prof. Dr."))
```

**例：**

- **入力値**: [personalTitle] = "4443"
- **式の出力**: `Dr.`
- **代替入力**: [personalTitle] = ""
- **代替出力**: *(空 - 無視)*

### 高度なシナリオ

#### 条件付き部門の処理

**シナリオ 1**: 使用可能な場合はグローバル割り当て部門を使用し、それ以外の場合は通常の部門にフォールバックします。

**ターゲット属性**: department

```
Switch([globalAssignmentDepartment],[globalAssignmentDepartment],
"",[department])
```

**例：**

- **入力値**: [globalAssignmentDepartment] = "Global IT", [department] = "Local IT"
- **式の出力**: `Global IT`
- **代替入力**: [globalAssignmentDepartment] = "", [department] = "Sales"
- **代替出力**: `Sales`

#### 論理的な削除の電子メール処理

**シナリオ 1**: 書き戻しシナリオでユーザーが論理的に削除された場合は、電子メール アドレスを変更する必要があります。

**ターゲット属性**: mail

```
IIF([IsSoftDeleted]="True", Join("_",FormatDateTime(Now(), , "M/d/yyyy h:mm:ss tt", "yyyy-MM-dd"),[mail]), [mail])
```

**例：**

- **入力値**: [IsSoftDeleted] = "True",[mail] = "john.smith@company.com" (現在の日付: 2025-07-30)
- **式の出力**: `2025-07-30_john.smith@company.com`
- **代替入力**: [IsSoftDeleted] = "False",[mail] = "jane.doe@company.com"
- **代替出力**: `jane.doe@company.com`

#### 終了ロジックを使用した複雑な OU の割り当て

**シナリオ 1**: 特定の日数が経過した後、終了したユーザーを特別な OU に移動する必要がある。

**ターゲット属性**: parentDistinguishedName

```
IIF(DateDiff("d", Now(), CDate(Switch([latestTerminationDate], [latestTerminationDate], "", "9999-01-01"))) <= -14,
    "OU=DELETED,DC=company,DC=com",
    Switch([department], "OU=Default,DC=company,DC=com", 
        "Engineering", "OU=Engineering,DC=company,DC=com", 
        "Finance", "OU=Finance,DC=company,DC=com"
    )
)
```

**例：**

- **入力値**: [latestTerminationDate] = "2025-07-10"、[department] = "Engineering" (現在の日付: 2025-07-30)
- **式の出力**: `OU=DELETED,DC=company,DC=com` (14 日以上前に終了)
- **代替入力**: [latestTerminationDate] = "2025-07-25", [department] = "Finance"
- **代替出力**: `OU=Finance,DC=company,DC=com` (14 日以内に終了)

#### 従業員クラスを使用した複数会社の UPN 生成

**シナリオ 1**: 会社と従業員のクラス情報の両方に基づいて UPN を生成する必要があります。

**ターゲット属性**: userPrincipalName

```
SelectUniqueValue (
    Switch([employeeClass], 
        Join("@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], [lastName]))), "contoso.com"),
        "1916", Join("@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], [lastName]))), "contoso.com"),
        "1915", Join("@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], [lastName]))), "fabrikam.com"),
        "1917", Join("@", NormalizeDiacritics(StripSpaces(Join(".", Mid([firstName],1,1), [lastName]))), "woodgrove.com")
    ),
    Switch([employeeClass], 
        Join("01@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], [lastName]))), "contoso.com"),
        "1916", Join("01@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], [lastName]))), "contoso.com"),
        "1915", Join("01@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], [lastName]))), "fabrikam.com"),
        "1917", Join("01@", NormalizeDiacritics(StripSpaces(Join(".", Mid([firstName],1,1), [lastName]))), "woodgrove.com")
    )
)
```

**例：**

- **入力値**: [employeeClass] = "1915", [firstName] = "Anna", [lastName] = "Johnson"
- **式の出力**: `anna.johnson@fabrikam.com` (最初のオプションを使用した場合は `01anna.johnson@fabrikam.com` )

#### アポストロフィと特殊文字の処理

**シナリオ 1**: Azure AD Connect の互換性のために、電子メール アドレスからアポストロフィとダッシュを削除する必要があります。

**ターゲット属性**: mail

```
SelectUniqueValue(
  Switch ([divisionId], 
    Replace(Join("@", NormalizeDiacritics(StripSpaces(Join(".", Coalesce([preferredName], [firstName]), [lastName]))), "contoso.com"), , "['-]+", , "", , ), 
    "8900", [email]
  ), 
  Replace(Join("1@", NormalizeDiacritics(StripSpaces(Join(".", Coalesce([preferredName], [firstName]), [lastName]))), "contoso.com"), , "['-]+", , "", , ), 
  Replace(Join("2@", NormalizeDiacritics(StripSpaces(Join(".", Coalesce([preferredName], [firstName]), [lastName]))), "contoso.com"), , "['-]+", , "", , )
)
```

**例：**

- **入力値**: [divisionId] = "1200", [preferredName] = "Mary-Ann", [firstName] = "Mary", [lastName] = "O'Connor"
- **式の出力**: `maryann.oconnor@contoso.com` (アポストロフィとダッシュが削除されました)
- **代替入力**: [divisionId] = "8900"、[email] = "existing.user@company.com"
- **代替出力**: `existing.user@company.com`

#### SIP アドレスの生成

**シナリオ 1**: Skype for Business/Teams 統合用の SIP アドレスを生成する必要がある。

**ターゲット属性**: proxyAddresses

```
SelectUniqueValue( 
    Append("sip:", Join("@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], [lastName]))), "contoso.com")),
    Append("sip:", Join("01@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], [lastName]))), "contoso.com")),
    Append("sip:", Join("02@", NormalizeDiacritics(StripSpaces(Join(".", [firstName], [lastName]))), "contoso.com"))
)
```

**例：**

- **入力値**: [firstName] = "David", [lastName] = "Wilson"
- **式の出力**: `sip:david.wilson@contoso.com` (最初のオプションを使用した場合は `sip:01david.wilson@contoso.com` )
- **代替入力**: [firstName] = "María José", [lastName] = "González-Pérez"
- **代替出力**: `sip:mariajose.gonzalezperez@contoso.com` (最初のオプションを使用した場合は `sip:01mariajose.gonzalezperez@contoso.com` 、最初の 2 つが取得された場合は `sip:02mariajose.gonzalezperez@contoso.com` )

#### 書き戻しの条件付きロジック

**シナリオ 1**: 特定の日付条件に基づいて、条件付きでメール アドレスを書き戻す必要があります。

**ターゲット属性**: mailNickname

```
IgnoreFlowIfNullOrEmpty(IIF(DateDiff("d", Now(), CDate([extensionAttribute9])) <> 1, "", [mail]))
```

**例：**

- **入力値**: [extensionAttribute9] = "2025-07-31", [mail] = "user@company.com" (現在の日付: 2025-07-30)
- **式の出力**: *(日付の差が 1 日であるため、空 - 無視)*
- **代替入力**: [extensionAttribute9] = "2025-08-01", [mail] = "user@company.com"
- **代替出力**: `user@company.com`

**シナリオ 2**: ライトバック シナリオで不足している属性を処理する必要があります。

**ターゲット属性**: mailNickname

```
IgnoreFlowIfNullOrEmpty(IIF(IsPresent([extensionAttribute9]),IIF(DateDiff("d", Now(), CDate([extensionAttribute9])) <> 1, "", [mail]),"noemail@contoso.com"))
```

**例：**

- **入力値**: [extensionAttribute9] = "" (空)、[mail] = "user@company.com"
- **式の出力**: `noemail@contoso.com`
- **代替入力**: [extensionAttribute9] = "2025-08-01",[mail] = "user@company.com" (現在の日付: 2025-07-30)
- **代替出力**: `user@company.com`

### ベスト プラクティス

- 一意性を必要とするすべての属性 (UPN、samAccountName、電子メール) に **SelectUniqueValue を使用**します。
- `IsNullOrEmpty`、`IsPresent`、`Switch`、`Coalesce`などの関数を使用して**、null 値と空の値を処理**します。
- 名前を処理するときに **NormalizeDiacritics と StripSpaces を使用**して、システム間の互換性を確保します。
- 運用環境で実装する前に、JSONPath テスターで JSONPath **式を検証**します。
- 変換エラーを回避するには、SuccessFactors の日付フィールドを操作するときに**適切な日付書式を使用**します。
- 日付比較とアカウントの有効期限ロジックを使用する場合は、**タイム ゾーンを検討**してください。
- 条件付き属性フローと書き戻しシナリオ**では IgnoreFlowIfNullOrEmpty を使用**します。
- **複雑な Switch ステートメント** はデバッグが困難になる可能性があるため、十分にテストしてください。
- **ビジネス ロジック** を明確に文書化します。特に、複雑な雇用状態とアカウント管理ルールについて説明します。
- **正規表現パターンを慎重に使用** し、実装前にオンライン正規表現テスターで検証します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/troubleshoot"} -->
## Microsoft Entra ギャラリー アプリへのプロビジョニングのトラブルシューティングを行います。 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/troubleshoot
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: Microsoft Entra アプリケーション ギャラリーに既に一覧表示されているアプリケーションへのユーザー プロビジョニングを構成するときに発生する一般的な問題のトラブルシューティング方法。

アプリケーション プロビジョニングの構成のトラブルシューティングを行います。 詳細については、「 [自動ユーザー プロビジョニング」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

まず、アプリケーションのセットアップ チュートリアルを見つけることから始めます。 次に、手順に従って、プロビジョニング接続を作成するようにアプリと Microsoft Entra ID の両方を構成します。 チュートリアルの一覧については、「 [SaaS アプリと Microsoft Entra ID を統合する方法に関するチュートリアルの一覧」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)。

### プロビジョニングが機能しているかどうかを確認する

サービスが構成されると、サービスの操作に関するほとんどの分析情報を 2 つの場所から取得できます。

- **プロビジョニング ログ (プレビュー)** – [プロビジョニング ログには、プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs?context=azure/active-directory/manage-apps/context/manage-apps-context) サービスによって実行されたすべての操作が記録されます。 ログには、プロビジョニングのスコープ内にある割り当てられたユーザーに対する Microsoft Entra ID のクエリが含まれます。 システム間でユーザー オブジェクトを比較しながら、これらのユーザーが存在するかどうかを対象となるアプリで照会できます。 その後、比較に基づいて、対象のシステムのユーザー アカウントを追加または更新するか、無効にします。 Microsoft Entra 管理センターでプロビジョニング ログにアクセスするには、[**アクティビティ**] セクションで &gt;&gt;**Provisioning logs** を選択します。
- **現在の状態 –** 特定のアプリの最後のプロビジョニング実行の概要は、サービス設定の画面下部にある **Entra ID**&gt;**Enterprise apps**&gt;`[Application Name]`&gt;**Provisioning** セクションで確認できます。 [現在の状態] セクションには、プロビジョニング サイクルによってユーザー アカウントのプロビジョニングが開始されるかどうかが表示されます。 サイクルの進行状況を確認し、プロビジョニングされているユーザーとグループの数、作成されたロールの数を確認します。 エラーがある場合は、[プロビジョニング ログ] (~/identity/monitoring-health/concept-provisioning-logs.md?context=azure/active-directory/manage-apps/context/manage-apps-context) で詳細を確認できます。

### プロビジョニング サービスが開始されていないように見える

Microsoft Entra 管理センターの **[Entra ID** **Enterprise apps**&gt;&gt;`[Application Name]`] セクションで、[&gt; を **[オン**] に設定します。 ただし、以降の再読み込み後、ページに他の状態の詳細は表示されません。 サービスが実行されているが、初期サイクルが完了していない可能性があります。 **プロビジョニング ログを**調べて、サービスが実行している操作とエラーがあるかどうかを判断します。

注

初期サイクルには 20 分から数時間かかります。 この時間は、Microsoft Entra ディレクトリのサイズと、プロビジョニングの対象となるユーザーの数によって異なります。 プロビジョニング サービスでは、初期サイクル後の両方のシステムの状態を表す透かしが格納されるため、後続の同期は高速になります。 ウォーターマークにより、後続の同期のパフォーマンスが向上します。

### アプリの資格情報が機能していないため、構成を保存できない

Microsoft Entra ID には、プロビジョニングに有効な資格情報が必要です。 資格情報は、アプリによって提供されるユーザー管理 API に接続します。 資格情報が機能しない場合、または資格情報が何であるかがわからない場合は、アプリの設定に関するチュートリアルを確認してください。

### プロビジョニング ログには、ユーザーが割り当てられているにもかかわらずスキップされており、プロビジョニングされていないと記録されています。

ログ メッセージの拡張された詳細を読み、ユーザーがプロビジョニング ログでスキップされたと表示される理由を判断します。 一般的な理由と解決策は次のとおりです。

- **属性値に基づいてユーザーをフィルターで除外するスコープ フィルターが構成されています**。 詳細については、「[スコープ フィルターを使用した属性ベースのアプリケーション プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)」を参照してください。
- **ユーザーが "実質的に有効でない" 状態です。** Microsoft Entra ID に格納されているユーザー割り当てレコードに問題があります。 この問題を解決するには、アプリからユーザー (またはグループ) を割り当て解除し、再割り当てします。 詳細については、「 [エンタープライズ アプリにユーザーまたはグループを割り当てる」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)参照してください。
- **必須の属性が見つからないか、ユーザー用に設定されていません。** Microsoft Entra ID からアプリケーションにフローするユーザー (またはグループ) プロパティを定義する属性マッピングとワークフローを確認して構成します。 2 つのシステム間でユーザー/グループを一意に識別して照合するために使用する設定 `matching property` を確認します。 詳細については、 [ユーザー プロビジョニング属性マッピングのカスタマイズを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)参照してください。
- **グループの属性マッピング:** 一部のアプリケーションでサポートされている場合は、メンバーに加えて、グループ名とグループの詳細のプロビジョニング。 [プロビジョニング] タブに表示されるグループ オブジェクトの **マッピング** を使用して、この機能 **を** 有効または無効にします。プロビジョニング グループが有効になっている場合は、属性マッピングを確認して、適切なフィールドが `matching ID`に使用されていることを確認します。 フィールドは、表示名または電子メール エイリアスです。 照合プロパティが空であるか、または Microsoft Entra ID 内のグループ用に設定されていない場合、グループとそのメンバーはプロビジョニングされません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/troubleshoot-scim-api-errors"} -->
## Microsoft Entra ID SCIM API エラーのトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/troubleshoot-scim-api-errors
- Service: entra-id / app-provisioning
- Article date: 2026-03-31
- Summary: ユーザーとグループの管理のために Microsoft Entra ID SCIM API を呼び出すときに発生する一般的なエラーのトラブルシューティングを行います。

この記事は、ユーザーとグループの管理のために Microsoft Entra ID SCIM API を呼び出すときに発生する一般的な問題のトラブルシューティングに役立ちます。

### エラー: 401 – 認証トークンが無効です

#### Symptoms

SCIM API 要求は次を返します。

```
HTTP 401 Unauthorized
```

#### 原因

要求には、HTTP `Authorization` ヘッダーに有効なアクセス トークンが含まれていません。

#### Resolution

1. 要求に HTTP `Authorization` ヘッダーにベアラー トークンが含まれていることを確認します。
2. を使用してアクセス トークンを検証する https://jwt.ms/
3. 次のトークン要求を確認します。
    - `iat`
    - `exp`
4. 問題が解決しない場合:
    - 失敗した API 呼び出しの詳細を収集する
    - サポート チケットを作成する

### エラー: 403 – 禁止

#### Symptoms

SCIM API 要求は次を返します。

```
HTTP 403 Forbidden
```

#### 原因

SCIM API クライアントによって使用されるアプリの登録には、必要な API アクセス許可がないか、管理者の同意が付与されていません。

#### Resolution

1. SCIM API クライアントで使用されるアプリの登録を確認します。
2. Microsoft [Entra ID SCIM API リファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/entra-id-scim-api-reference) に記載されている Microsoft Graph API のアクセス許可が割り当てられていることを確認します。
3. 割り当てられたすべてのアクセス許可に対して **管理者の同意** が付与されていることを確認します。
4. 問題が解決しない場合:
    - API 要求の詳細を収集する
    - サポート チケットを作成する

### エラー: 404 – リソースが見つかりません

#### Symptoms

次の API 呼び出しが返されます。

```
HTTP 404 Not Found
```

- `GET /users/{id}`
- `GET /groups/{id}`

#### 原因

要求の `{id}` 値は、Microsoft Entra ID テナントの有効なユーザーまたはグループ オブジェクトに解決されません。

#### Resolution

1. `{id}`値が Microsoft Entra ID テナントに存在することを確認します。
2. 問題が解決しない場合:
    - API 要求の詳細を収集する
    - サポート チケットを作成する

### エラー: 400 – 要求が正しくありません

#### Symptoms

SCIM API 要求は次を返します。

```
HTTP 400 Bad Request
```

このエラーは、次の場合に発生する可能性があります。

- ユーザーの作成または更新操作 (`POST /Users` または `PATCH /Users`)
- グループの作成操作または更新操作 (`POST /Groups` または `PATCH /Groups`)
- ユーザーまたはグループ のフィルター クエリ (`GET`)

#### 原因

要求ペイロードまたはクエリ パラメーターが、SCIM API に対して定義されている制約を満たしていないか、無効な SCIM スキーマ属性を参照します。

#### Resolution

1. `POST`操作の場合:
    - 必要なすべての属性が要求ペイロードに含まれていることを確認します。
2. `PATCH`操作の場合:
    - 要求が SCIM API 制約に準拠していることを確認します。
3. `GET`操作の場合:
    - サポートされている API フィルターのみが使用されていることを確認します。
4. 要求属性が有効な Microsoft Entra SCIM スキーマ属性にマップされていることを確認します。
5. 修正を行って要求を再送信します。
6. 問題が解決しない場合:
    - API 要求の詳細を収集する
    - サポート チケットを作成する

### エラー: 400 – SCIM プロビジョニング API 機能が有効になっていません

#### Symptoms

SCIM API 要求は次を返します。

```http
HTTP 400 Bad Request
```

```json
{
  "schemas": [
    "urn:ietf:params:scim:api:messages:2.0:Error"
  ],
  "detail": "No 'scimapiconsumptions' resource found for TenantId: <tenant-id>. Please ensure 'SCIM Provisioning API' feature is enabled and only one 'scimapiconsumptions' resource exists.",
  "status": "400"
}
```

#### 原因

SCIM プロビジョニング API 機能が Microsoft Entra 管理センターで有効になっていないか、機能に必要な課金サブスクリプションがリンクされていません。 Azure リソースは、管理者が機能をオンにし、課金のために Azure サブスクリプションをリンクしたときに作成されます。 このリソースがないと、SCIM サービスはすべての API 呼び出しを拒否します。

#### Resolution

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **ID ガバナンス**&gt;**Dashboard** に移動します。
3. **[SCIM Provisioning API**] タイルで、[**作業の開始**] を選択します (以前に構成されている場合は**編集**)。
4. Azure サブスクリプションをリンクし、リソース グループを選択して、[ **有効にする**] を選択します。
5. この機能が有効になった後、SCIM API 要求を再試行します。

詳細な手順については、「 [SCIM プロビジョニング API を有効にする」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/enable-scim-api)参照してください。

### エラー: 500 – 内部サーバー エラー

#### Symptoms

SCIM API 要求は次を返します。

```
HTTP 500 Internal Server Error
```

#### 原因

SCIM API 要求の処理中に予期しないサーバー側エラーが発生しました。

#### Resolution

1. 失敗した API 呼び出しの詳細を収集します。
2. サポート チケットを作成します。

### エラー: 504 – ゲートウェイのタイムアウト

#### Symptoms

SCIM API 要求は次を返します。

```
HTTP 504 Gateway Timeout
```

#### 原因

SCIM サービスから断続的な 504 応答が返される場合があります。

#### Resolution

1. 失敗した API 呼び出しの詳細とエラーの時間枠を収集します。
2. 問題が解決しない場合は、サポート チケットを作成します。

### SCIM API エラー コード リファレンス

次の表に、Microsoft Entra ID SCIM API が返すことができるすべてのエラー コードを示します。 SCIM API 要求のトラブルシューティング時のエラーとその原因を特定するには、次の表を使用します。

注

すべての API エンドポイントにすべてのエラー コードが適用されるわけではありません。 たとえば、 `BadRequest` (400) の解釈は、API エンドポイントに関連付けられている要求ペイロードによって異なる場合があります。 エンドポイント固有の詳細については、 [Microsoft Entra ID SCIM API リファレンスを参照](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/entra-id-scim-api-reference) してください。

| エラー | 状態 | HTTP ステータス コード |
| --- | --- | --- |
| 不正なリクエスト | 要求ペイロードまたはクエリ パラメーターが SCIM API の制約を満たしていない、必要な属性がない、または無効な SCIM スキーマ属性が参照されている。 | 400 |
| 不正なリクエスト | SCIM プロビジョニング API 機能が有効になっていないか、課金サブスクリプションがリンクされていません。 エラーの詳細は、不足している Azure リソースを参照します。 | 400 |
| 不正なリクエスト | application/json の HTTP Accept ヘッダーがありません | 400 |
| 無効な認証トークン | アクセス トークンが空または無効です。 | 401 |
| 権限不足 | 操作を完了するためのアクセス許可が不十分です。 | 403 |
| リソースが見つかりません | 無効な URN または指定されたリソースがテナントに存在しません。 | 404 |
| InternalServerException | サービスが要求を処理できませんでした。 | 500 |
| ゲートウェイタイムアウト | SCIM サービスが予期した時間内に応答しませんでした。 | 504 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/tutorial-ecma-sql-connector"} -->
## SQL アプリケーションへの Microsoft Entra プロビジョニング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/tutorial-ecma-sql-connector
- Service: entra-id / app-provisioning
- Article date: 2025-04-09
- Summary: このチュートリアルでは、Microsoft Entra ID から SQL データベースにユーザーをプロビジョニングする方法について説明します。

このドキュメントでは、ユーザーを Microsoft Entra ID から SQL データベースに自動的にプロビジョニングおよびプロビジョニング解除するために実行する必要がある手順について説明します。

このサービスが実行する内容、仕組み、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」および「[オンプレミス アプリケーション プロビジョニング アーキテクチャ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture)」に関する記事を確認してください。

次のビデオでは、オンプレミス プロビジョニングの概要を紹介しています。

### SQL Database にプロビジョニングするための前提条件

#### オンプレミスの前提条件

アプリケーションは SQL データベースに依存します。このデータベースでは、ユーザーのレコードを作成、更新、および削除できます。 プロビジョニング エージェントを実行するコンピューターには、次のものが必要です。

- Windows Server 2016 以降のバージョン。
- ターゲット データベース システムへの接続、および login.microsoftonline.com、[その他の Microsoft Online Services](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/urls-and-ip-address-ranges)、[Azure](https://learn.microsoft.com/ja-jp/azure/azure-portal/azure-portal-safelist-urls) ドメインへの送信接続。 1 つの例は、Azure IaaS でホストされているか、またはプロキシの背後にある Windows Server 2016 仮想マシンです。
- 少なくとも 3 GB の RAM。
- .NET Framework 4.7.2
- SQL データベースの ODBC ドライバー。

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

適切なテーブルを持つデータベースがまだない場合は、デモンストレーション用に、Microsoft Entra ID による使用を許可したテーブルを作成する必要があります。 SQL Server を使用している場合は、「付録 A」に記載されている SQL スクリプトを実行します。このスクリプトは、1つのテーブル `Employees` を含む、CONTOSO という名前のサンプル データベースを作成します。 これは、ユーザーをプロビジョニングするデータベース テーブルです。

| [テーブル列] | Source |
| --- | --- |
| ContosoLogin | Microsoft Entra ユーザー プリンシパル名 |
| ファーストネーム | Microsoft Entra の指定された名前 |
| LastName | Microsoft Entra 姓 |
| Email | Exchange Online 電子メールアドレス |
| InternalGUID | データベース自体によって生成されます |
| AzureID | Microsoft Entra オブジェクト ID |
| textID | Microsoft Entra ID メール ニックネーム |

### Microsoft Entra SQL コネクタがデータベースとどのように対話するかを決定する

SQL インスタンスに、データベースのテーブル内のデータを更新する権限を持つユーザー アカウントが存在する必要があります。 SQL データベースが他のユーザーによって管理されている場合は、そのユーザーに連絡して、データベースに対する認証に Microsoft Entra ID で使用するアカウント名とパスワードを入手してください。 SQL インスタンスが別のコンピューターにインストールされている場合は、SQL データベースでエージェント コンピューター上の ODBC ドライバーからの受信接続を許可する必要もあります。

アプリケーションに既存のデータベースが既にある場合、Microsoft Entra ID がそのデータベースと対話する方法を決定する必要があります。これは、テーブルとビューを直接操作する方法、データベース内に既に存在するストアド プロシージャを使用する方法、クエリと更新用に指定する SQL ステートメントを使用する方法です。 この設定は、より複雑なアプリケーションが、そのデータベースに他の補助テーブルを持ち、何千人ものユーザーを持つテーブルのページングを必要としたり、暗号化、ハッシュ化、妥当性チェックなどの追加のデータ処理を実行するストアド プロシージャを Microsoft Entra ID が呼び出す必要があったりする可能性があるからです。

アプリケーションのデータベースと対話するようにコネクタの構成を作成する場合は、まず、コネクタ ホストがデータベースのスキーマを読み取る方法についてのアプローチを構成してから、次に実行プロファイルを介してコネクタが継続的に使用するアプローチを構成します。 各実行プロファイルでは、コネクタが SQL ステートメントを生成する方法を指定します。 実行プロファイルの選択と実行プロファイル内のメソッドは、データベース エンジンがサポートする内容とアプリケーションで必要とされるものによって異なります。

- 構成が完了すると、プロビジョニング サービスが開始する際、**完全インポート**実行プロファイルで構成された相互作用が自動的に実行されます。 この実行プロファイルでは、コネクタは、通常 **SELECT** ステートメントを使用して、アプリケーションのデータベースからのユーザーのすべてのレコードを読み取ります。 この実行プロファイルは、後で Microsoft Entra ID がユーザーに変更を加える必要がある場合に、そのユーザーに対して新しいレコードを作成するのではなく、データベース内のそのユーザーの既存のレコードを更新することを認識するために必要になります。
- 新しいユーザーをアプリケーションに割り当てる場合や既存のユーザーを更新する場合など、Microsoft Entra ID で変更が行われるたびに、プロビジョニング サービスは、SQL データベース相互作用によって構成された**エクスポート**実行プロファイルを実行します。 **エクスポート**実行プロファイルでは、データベースの内容を Microsoft Entra ID と同期させるために、Microsoft Entra ID が SQL ステートメントを発行してデータベースのレコードを挿入、更新、および削除します。
- データベースでサポートされている場合は、必要に応じて **差分インポート**実行プロファイルを構成することもできます。 この実行プロファイルでは、前回の完全インポート、または差分インポート以降に Microsoft Entra ID 以外のデータベースで行われた変更が Microsoft Entra ID によって読み取られます。 この実行プロファイルは、変更を読み取ることができるようにデータベースを構造化する必要があるため、省略可能です。

コネクタの各実行プロファイルの構成では、Microsoft Entra ID コネクタがテーブルまたはビューに対して独自の SQL ステートメントを生成するか、ストアド プロシージャを呼び出すか、または指定したカスタム SQL クエリを使用するかどうかを指定します。 通常は、コネクタ内のすべての実行プロファイルに同じ方法を使用します。

- 実行プロファイルのテーブルまたはビューメソッドを選択すると、Microsoft Entra コネクタによって、データベース内のテーブルまたはビューと対話するために必要な SQL ステートメント、*SELECT*、*INSERT*、*UPDATE*、および *DELETE* が生成されます。 この方法は、データベースに 1 つのテーブル、または既存の行数が少ない更新可能なビューがある場合に、最も簡単なアプローチです。
- ストアド プロシージャのメソッドを選択した場合、データベースには、ユーザーのページの読み取り、ユーザーの追加、ユーザーの更新、ユーザーの削除という 4 つのストアド プロシージャが必要になります。Microsoft Entra コネクタは、呼び出すストアド プロシージャの名前とパラメーターを使用して構成することになります。 この方法では、SQL データベースにより多くの構成が必要となります。通常は、アプリケーションで、ユーザーに対する変更ごとまたは大規模な結果セットをページングするため追加の処理が必要な場合にのみ必要になります。
- SQL クエリ メソッドを選択した場合は、実行プロファイルの実行時にコネクタが発行する特定の SQL ステートメントを入力します。 コネクタは、SQL ステートメントにコネクタが入力するパラメーター (インポート時の結果セットのページング、エクスポート中に作成される新しいユーザーの属性を設定するなど) を使用して構成します。

この記事では、`Employees`と**フル インポート**の実行プロファイルで、サンプル データベースのテーブル と対話するためにテーブル メソッドを使用する方法について説明します。 ストアド プロシージャまたは SQL クエリ メソッドの構成の詳細情報と具体的な要件については、「[汎用 SQL 構成ガイド](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/reference/microsoft-identity-manager-2016-connector-genericsql)」を参照してください。

### アプリケーションのデータベース スキーマで一意の識別子を選択する

ほとんどのアプリケーションは、アプリケーションのユーザーごとに一意の識別子を持ちます。 既存のデータベース テーブルにプロビジョニングする場合は、各ユーザーの値を持つそのテーブルのカラムを特定する必要があり、その値は一意であり、変更されることはありません。 この列は**アンカー**となり、Microsoft Entra ID が既存の行を識別して更新や削除ができるようにするために使用します。 アンカーの詳細については、「[アンカー属性と識別名について](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture#about-anchor-attributes-and-distinguished-names)」を参照してください。

アプリケーションのデータベースが既に存在していて、その中にユーザーが存在し、Microsoft Entra ID でそれらのユーザーを最新に保ちたい場合は、アプリケーションのデータベースと Microsoft Entra のスキーマの間で同じ各ユーザーの識別子を用意する必要があります。 たとえば、Microsoft Entra ID でアプリケーションにユーザーを割り当て、そのユーザーが既にそのデータベースに存在する場合、Microsoft Entra ID でそのユーザーに変更を加えると、新しい行が追加されるのではなく、そのユーザーの既存の行が更新されます。 Microsoft Entra ID は、そのユーザーのアプリケーションの内部識別子を保存しない可能性が高いので、データベースの**クエリ**に使用する別の列を選択することになります。 この列の値には、アプリケーションのスコープ内にある各ユーザーの Microsoft Entra ID に存在するユーザー プリンシパル名、メールアドレス、従業員 ID、またはその他の識別子を指定することができます。 アプリケーションが使用するユーザー識別子が、ユーザーの Microsoft Entra 表現に格納されている属性でない場合は、Microsoft Entra ユーザー スキーマを拡張して拡張属性を指定してデータベースからその属性を設定する必要はありません。 [PowerShell](https://learn.microsoft.com/ja-jp/powershell/azure/active-directory/using-extension-attributes-sample) を使用して Microsoft Entra スキーマを拡張し、拡張値を設定できます。

### Microsoft Entra ID 内の属性をデータベース スキーマにマップする

データベースに既に存在するユーザーであっても新しいユーザーであっても、Microsoft Entra ID 内のユーザーとデータベース内のレコードの間に Microsoft Entra ID でリンクが確立されている場合は、Microsoft Entra ID で Microsoft Entra ユーザーからデータベースに属性の変更をプロビジョニングできます。 一意の識別子に加え、データベースを調べて他に必要なプロパティがあるかどうかを特定します。 存在する場合は、データベースにプロビジョニングされるユーザーに、必要なプロパティにマップできる属性があることを確認します。

[プロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works#deprovisioning)の動作を構成することもできます。 Microsoft Entra ID でアプリケーションに割り当てられているユーザーが削除された場合、Microsoft Entra ID は削除操作をデータベースに送信します。 また、ユーザーがアプリケーションを使用できるスコープから外れる場合に、Microsoft Entra ID にデータベースを更新させることもできます。 ユーザーがアプリから割り当て解除されたか、Microsoft Entra ID で論理的に削除されたか、またはサインインがブロックされた場合は、Microsoft Entra ID が属性の変更を送信するように構成できます。 既存のデータベース テーブルにプロビジョニングする場合は、そのテーブルの列を **isSoftDeleted** にマップできます。 ユーザーがスコープから外れると、Microsoft Entra ID によってそのユーザーの値が **True** に設定されます。

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
5. **完了** を選択します。

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

    Note

    SQL コネクタでは、DSN 接続ファイルが UTF-8 でエンコードされることを想定しています。 ファイルが UTF-8 でエンコードされていない場合は、メモ帳を使用して UTF-8 エンコードでファイルを保存します。

### 3. Microsoft Entra Connect のプロビジョニング エージェントをインストールして構成する

プロビジョニング エージェントを既にダウンロードし、別のオンプレミス アプリケーション用に構成している場合は、次のセクションに進んでください。

1. Azure portal にサインインします。
2. **[エンタープライズ アプリケーション]** に移動し、**[新規アプリケーション]** を選択します。
3. **オンプレミスの ECMA アプリ** アプリケーションを検索し、アプリに名前を付けて、**[作成]** を選択してテナントに追加します。
4. メニューから、アプリケーションの **[プロビジョニング]** ページに移動します。
5. **開始する**を選択します。
6. **[プロビジョニング]** ページで、モードを **[自動]** に変更します。

[Image: [自動] を選択しているスクリーンショット。]

1. **[オンプレミス接続]** で、**[ダウンロードしてインストール]** を選択し、**[条項に同意してダウンロード]** を選択します。

[Image: エージェントのダウンロード場所のスクリーンショット。]

1. ポータルを開いたままにして、プロビジョニング エージェント インストーラーを実行し、サービス使用条件に同意して、**[インストール]** を選択します。
2. Microsoft Entra プロビジョニング エージェント構成ウィザードが表示されるのを待ってから、**[次へ]** を選択します。
3. **[拡張機能を選択]** ステップで、**[オンプレミス アプリケーションのプロビジョニング]** を選択し、**[次へ]** を選択します。
4. プロビジョニング エージェントは、オペレーティング システムの Web ブラウザーを使用して、Microsoft Entra ID (および場合によっては組織の ID プロバイダー) に対する認証を行うためのポップアップ ウィンドウを表示します。 Windows Server でブラウザーとして Internet Explorer を使用している場合は、JavaScript を正しく実行できるように、ブラウザーの信頼済みサイト リストに Microsoft Web サイトを追加する必要がある場合があります。
5. 承認を求められたら、Microsoft Entra 管理者の資格情報を入力します。 ユーザーは、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)ロールを持っている必要があります。
6. **[Confirm]** を選択して設定を確認します。 インストールが成功したら、**[終了]** を選択し、プロビジョニング エージェント パッケージ インストーラーも閉じます。

### 4. オンプレミスの ECMA アプリを構成する

1. Portal に戻り、**[オンプレミス接続]** セクションで、デプロイしたエージェントを選択し、**[エージェントの割り当て]** を選択します。

    [Image: エージェントを選択して割り当てる方法を示すスクリーンショット。]
2. このブラウザー ウィンドウを開いたままにして、構成ウィザードを使用して構成の次の手順を完了します。

### 5. Microsoft Entra ECMA Connector Host の証明書を構成する

1. プロビジョニング エージェントがインストールされている Windows Server で、スタート メニューから **Microsoft ECMA2Host 構成ウィザード**を右クリックし、管理者として実行します。 ウィザードで必要な Windows イベント ログを作成するには、Windows 管理者として実行する必要があります。
2. このウィザードを初めて実行する場合は、ECMA Connector Host 構成の開始後に証明書の作成を求められます。 既定のポート **8585** のままにし、**[証明書の生成]** を選択して証明書を生成します。 自動生成された証明書は、信頼ルートの一部として自己署名されます。 証明書の SAN はホスト名と一致します。

    [Image: 設定の構成を示すスクリーンショット。]
3. **保存**を選択します。

Note

新しい証明書を生成することを選択した場合は、証明書の有効期限を記録した後、構成ウィザードに戻り、有効期限が切れる前に証明書を生成し直すようにしてください。

### 6. 汎用 SQL コネクタを作成する

このセクションでは、データベースのコネクタ構成を作成します。

#### 6.1 SQL 接続を構成する

汎用 SQL コネクタを作成するには、以下の手順に従ってください。

1. コネクタに対して Microsoft Entra ID を認証するために使用されるシークレット トークンを生成します。 これは、アプリケーションごとに最小 12 文字で、一意である必要があります。
2. まだ実行していない場合は、Windows スタート メニューから **Microsoft ECMA2Host 構成ウィザード**を起動します。
3. **[新しいコネクタ]** を選択します。
4. [**プロパティ]** ページで、次の表で指定した値をボックスに入力し、[次へ] を選択します。

    | プロパティ | [値] |
    | --- | --- |
    | 名前 | コネクタに対して選択した名前。これは、環境内のすべてのコネクタで一意である必要があります。 たとえば、SQL データベースが 1 つしかない場合は、`SQL` とします。 |
    | AutoSync タイマー (分) | 120 |
    | シークレット トークン | このコネクタ用に生成したシークレット トークンを入力します。 キーは 12 文字以上である必要があります。 |
    | 拡張 DLL | Generic SQL コネクタの場合、**Microsoft.IAM.Connector.GenericSql.dll** を選択します。 |
5. [**接続**] ページで、次の表で指定した値をボックスに入力し、[次 ] を選択します。

    | プロパティ | 説明 |
    | --- | --- |
    | DSN ファイル | SQL インスタンスに接続するために使用される、前の手順で作成したデータ ソース名ファイル。 |
    | ユーザー名 | SQL インスタンスのテーブルを更新する権限を持つアカウントのユーザー名。 ターゲット データベースが SQL Server であり、Windows 認証を使用している場合は、ユーザー名は、スタンドアロン サーバーの場合は hostname\sqladminaccount、ドメイン メンバー サーバーの場合は domain\sqladminaccount の形式にする必要があります。 他のデータベースの場合、ユーザー名はデータベース内のローカル アカウントになります。 |
    | パスワード | 指定したユーザー名のパスワード。 |
    | DN is Anchor (DN はアンカー) | お使いの環境でこれらの設定が必要であるとわかっている場合を除いて、 **[DN is Anchor](DN はアンカー)** および **[エクスポートの種類: オブジェクトの置換]** チェックボックスを選択しないでください。 |

#### 6.2 データベースからスキーマを取得する

資格情報を提供すると、ECMA コネクタ ホストはデータベースのスキーマを取得する準備が整います。 SQL 接続の構成を続けます。

1. **[スキーマ 1]** ページで、オブジェクトの種類の一覧を指定します。 このサンプルでは、オブジェクトの種類は `User` の 1 つです。 画像の下にある表に指定される値をボックスに入力し、[**次へ**] を選択します。

    [Image: [スキーマ 1] ページを示すスクリーンショット。]

    | プロパティ | [値] |
    | --- | --- |
    | オブジェクトの種類の検出方法 | 固定値 |
    | 固定値のリスト/テーブル/ビュー/SP | User |
2. **[次へ]** を選択すると、`User` オブジェクトの種類を構成するための次のページが自動的に表示されます。 [**スキーマ 2**] ページで、データベースでのユーザーの表示方法を指定します。 このサンプルでは、`Employees` という名前の 1 つの SQL テーブルです。 画像の下にある表に指定される値をボックスに入力し、[**次へ**] を選択します。

    [Image: [スキーマ 2] ページを示すスクリーンショット。]

    | プロパティ | [値] |
    | --- | --- |
    | ユーザー: 属性の検出 | テーブル |
    | ユーザー: テーブル/ビュー/SP | データベース内のテーブルの名前 (`Employees` など) |

    Note

    エラーが発生した場合は、データベース構成を調べて、**[接続]** ページで指定したユーザーがデータベースのスキーマへの読み取りアクセス権を持っていることを確認します。
3. **[次へ]** を選ぶと次のページが自動的に表示され、前に指定したテーブル (この例では `Employees` テーブル) で、ユーザーの `Anchor` および `DN` として使用する列を選択できます。 これらの列には、データベース内の一意の識別子が含まれます。 使用する列は同じ列でも異なる列でもかまいませんが、このデータベースに既に存在する行が、これらの列で必ず一意の値を持っているようにしてください。 **[スキーマ 3]** ページでは、画像の下にある表に指定される値をボックスに入力し、 **[次へ]** を選択します。

    [Image: [スキーマ 3] ページを示すスクリーンショット。]

    | プロパティ | 説明 |
    | --- | --- |
    | [:User] に [アンカー] を選択します | アンカーとして使用するデータベース テーブルの列 (`User:ContosoLogin` など) |
    | ユーザーの DN 属性を選択してください | DN 属性として使用するデータベースの列 (`AzureID` など) |
4. **[次へ]** を選択すると、`Employee` テーブルの各列のデータ型と、コネクタがそれらをインポート、またはエクスポートする必要があるかどうかを確認するための次のページが自動的に表示されます。 **[スキーマ 4]** ページでは、既定値のままにし、 **[次へ]** を選択します。

    [Image: [スキーマ 4] ページを示すスクリーンショット。]
5. **[グローバル]** ページで、ボックスに入力し、 **[次へ]** を選択します。 個々のボックスのガイダンスについては、画像の下にある表を参照してください。

    [Image: [グローバル] ページを示すスクリーンショット。]

    | プロパティ | 説明 |
    | --- | --- |
    | Delta Strategy (差分方式) | IBM DB2 の場合は、`None` を選択します |
    | ウォーター マーク クエリ | IBM DB2 の場合は、「`SELECT CURRENT TIMESTAMP FROM SYSIBM.SYSDUMMY1;`」と入力します |
    | データ ソースの日付と時刻の形式 | SQL Server の場合は `yyyy-MM-dd HH:mm:ss`、IBM DB2 の場合は `YYYY-MM-DD` |
6. **[パーティション]** ページで **[次へ]** を選択します。

    [Image: [パーティション] ページを示すスクリーンショット。]

#### 6.3 実行プロファイルを構成する

次に、**エクスポート**と**完全インポート**の実行プロファイルを構成します。 **エクスポート**実行プロファイルは、ECMA コネクタ ホストが Microsoft Entra ID からデータベースに変更を送信し、レコードの挿入、更新、削除を行う必要がある場合に使用されます。 **完全インポート**実行プロファイルは、ECMA コネクタ ホスト サービスの開始時に、データベースの現在のコンテンツを読み取るために使用されます。 このサンプルでは、ECMA コネクタ ホストが必要な SQL ステートメントを生成するように、両方の実行プロファイルでテーブル メソッドを使用します。

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

    - **アンカー**: この属性の値は、ターゲット データベース内のオブジェクトごとに一意である必要があります。 Microsoft Entra プロビジョニング サービスでは、初期サイクル後、この属性を使用して ECMA コネクタ ホストに対してクエリを実行します。 このアンカー列は、先に**スキーマ 3** のページで構成したアンカー値と同じである必要があります。
    - **クエリ属性**: この属性はアンカーと同じである必要があります。
    - **DN**: ほとんどの場合、**[自動生成]** オプションを選択する必要があります。 オフになっている場合は、`CN = anchorValue, Object = objectType` の形式で DN が格納されている Microsoft Entra ID の属性に、DN 属性がマップされるようにします。 アンカーと DN の詳細については、「[アンカー属性と識別名について](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture#about-anchor-attributes-and-distinguished-names)」を参照してください。

    | プロパティ | 説明 |
    | --- | --- |
    | ターゲット オブジェクト | User |
    | アンカー | [スキーマ 3] タブで構成されている列 (`ContosoLogin` など) |
    | クエリ属性 | アンカーと同じ列 (`ContosoLogin` など) |
    | DN | [スキーマ 3] タブで構成されているのと同じ列 (`ContosoLogin` など) |
    | 自動生成 | チェック済み |
2. ECMA コネクタ ホストにより、ターゲット データベースがサポートする属性が検出されます。 Microsoft Entra ID に公開する属性を選択できます。 プロビジョニングのために、これらの属性を Azure portal で構成できます。 **[属性の選択]** ページで、ドロップダウン リストのすべての属性を 1 つずつ追加します。

**[属性]** ドロップダウン リストには、ターゲット データベースで検出され、先ほどの *[属性の選択]* ページでは選択 "されなかった" 属性が示されます。 関連するすべての属性が追加されたら、**[次へ]** を選択します。

[Image: 属性ドロップダウン リストのスクリーンショット。]

1. **[プロビジョニング解除]** ページの **[フローを無効化]** で **[削除]** を選択します。 前のページで選択した属性を [プロビジョニング解除] ページで選択することはできません。 **完了** を選択します。

Note

**[Set attribute value](Set 属性値)**を使用すると、ブール値のみが許可されることに注意してください。

[Image: プロビジョニング解除のページを示すスクリーンショット。]

### 7. ECMA2Host サービスが実行されていることを確認する

1. Microsoft Entra ECMA Connector Host を実行しているサーバーで、**[開始]** をクリックします。
2. **「run」** と入力し、ボックスに **「services.msc」** と入力します。
3. **サービス** リストで、**Microsoft ECMA2Host** が確実に存在し、実行中であるようにします。 そうでない場合は、 **[開始]** を選択します。

    [Image: サービスが稼働中であることを示すスクリーンショット。]

新しいデータベース、または空でユーザーがいないデータベースに接続している場合は、次のセクションに進んでください。 それ以外の場合は、次の手順に従って、コネクタがデータベースから既存のユーザーを識別したことを確認してください。

1. 最近サービスを開始し、データベースに多数のユーザー オブジェクトがある場合は、コネクタがデータベースとの接続を確立するまで数分待ってください。

### 8. Azure portal でアプリケーション接続を構成する

1. アプリケーション プロビジョニングを構成していた Web ブラウザー ウィンドウに戻ります。

    Note

    ウィンドウがタイムアウトした場合は、エージェントを再選択する必要があります。

    1. Azure portal にサインインします。
    2. **[エンタープライズ アプリケーション]** の **[On-premises ECMA app](オンプレミス ECMA アプリ)** アプリケーションに移動します。
    3. **[プロビジョニング]** を選択します。
    4. **[作業の開始]** が表示された場合は、モードを **[自動]** に変更し、**[オンプレミス接続]** セクションで、デプロイしたエージェントを選択し、**[エージェントの割り当て]** を選択します。 それ以外の場合は、**[プロビジョニングの編集]** に移動します。
2. **[管理者資格情報]** セクションで、次の URL を入力します。 `{connectorName}` の部分を ECMA コネクタ ホスト上のコネクタの名前 (**SQL** など) に置き換えます。 コネクタ名は大文字と小文字が区別され、ウィザードで構成したのと同じ大文字と小文字の区別にする必要があります。 `localhost` をマシンのホスト名に置き換えることもできます。

    | プロパティ | [値] |
    | --- | --- |
    | テナントの URL | `https://localhost:8585/ecma2host_{connectorName}/scim` |
3. コネクタの作成時に定義した**シークレット トークン**の値を入力します。

    Note

    アプリケーションにエージェントを割り当てたばかりの場合は、登録が完了するまで 10 分間お待ちください。 登録が完了するまで、接続テストは機能しません。 サーバーでプロビジョニング エージェントを再起動してエージェントの登録を強制的に完了すると、登録プロセスを高速化することができます。 サーバーに移動し、Windows 検索バーで**サービス**を検索し、**Microsoft Entra Connect プロビジョニング エージェント サービス**を特定し、サービスを右クリックして再起動します。
4. **[接続のテスト]** をクリックし、1 分待ちます。

    [Image: エージェントの割り当てを示すスクリーンショット。]
5. 接続テストが成功し、指定された資格情報が認証されてプロビジョニングが有効になったことが示されたら、**[保存]** を選択します。

    [Image: エージェントのテストを示すスクリーンショット。]

### 9. 属性マッピングを構成する

次に、Microsoft Entra ID のユーザーの表現と、オンプレミス アプリケーションの SQL データベースのユーザーの表現との間で属性をマップする必要があります。

Azure portal を使用して、Microsoft Entra ユーザーの属性と、ECMA ホスト構成ウィザードで以前に選択した属性の間のマッピングを構成します。

1. Microsoft Entra スキーマに、データベースによって必要とされる属性が含まれていることを確認します。 ユーザーに `uidNumber` などの属性があることがデータベースによって要求され、その属性がユーザーの Microsoft Entra スキーマにまだ含まれていない場合は、[ディレクトリ拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping)を使用して、その属性を拡張機能として追加する必要があります。
2. Microsoft Entra 管理センターの **[エンタープライズ アプリケーション]** で、**[オンプレミスの ECMA アプリ]** アプリケーションを選択し、**[プロビジョニング]** ページを選択します。
3. **[プロビジョニングの編集]** を選択し、10 秒待ちます。
4. **[マッピング]** を展開し、**[Microsoft Entra ID ユーザーをプロビジョニング]** マッピングを選びます。 このアプリケーションの属性マッピングを初めて構成した場合、プレースホルダーのマッピングとして存在するのは、この 1 つのみです。

    [Image: ユーザーのプロビジョニングを示すスクリーンショット。]
5. データベースのスキーマが Microsoft Entra ID で使用可能であることを確認するには、**[詳細オプションの表示]** チェック ボックスをオンにし、**[ScimOnPremises の属性リストの編集]** を選択します。 構成ウィザードで選択したすべての属性が一覧表示されていることを確認します。 そうでない場合は、スキーマが更新されるまで数分待ってから、ページを再読み込みします。 属性が一覧表示されたら、このページを閉じてマッピング一覧に戻ります。
6. ここで、**userPrincipalNam** PLACEHOLDER のマッピングをクリックします。 このマッピングは、オンプレミス プロビジョニングを初めて構成するときに既定で追加されます。

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
    | 直接 | オブジェクトID | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:AzureID |
    | 直接 | メール | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:Email |
    | 直接 | givenName | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:FirstName |
    | 直接 | 名字 | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:LastName |
    | 直接 | メールニックネーム | urn:ietf:params:scim:schemas:extension:ECMA2Host:2.0:User:textID |
3. マッピングをすべて追加したら、**[保存]** を選択します。

### 10. アプリケーションにユーザーを割り当てる

これで Microsoft Entra ECMA Connector Host が Microsoft Entra ID と通信できるようになり、属性マッピングが構成されたので、プロビジョニングの範囲に含めるユーザーの構成に進むことができます。

重要

ハイブリッド ID 管理者ロールを使用してサインインした場合は、少なくともこのセクションのアプリケーション管理者ロールを持つアカウントでサインアウトしてサインインする必要があります。 ハイブリッド ID の管理者ロールには、ユーザーをアプリケーションに割り当てるためのアクセス許可がありません。

SQL データベースに既存のユーザーが存在する場合は、それらの既存のユーザー向けのアプリケーション ロールの割り当てを作成する必要があります。 アプリケーション ロールの割り当てを一括で作成する方法の詳細については、[Microsoft Entra ID でのアプリケーションの既存ユーザーの管理](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-existing-users)に関するページを参照してください。

アプリケーションの既存のユーザーが存在しない場合は、アプリケーションに対してプロビジョニングされるテスト ユーザーを Microsoft Entra から選択します。

1. 選択したユーザーに、データベース スキーマの必須属性にマップされるすべてのプロパティがあることを確認します。
2. Azure portal で、 **[エンタープライズ アプリケーション]** を選択します。
3. **[オンプレミスの ECMA アプリ]** アプリケーションを選択します。
4. 左側のウィンドウの **[管理]** で、 **[ユーザーとグループ]** を選択します。
5. **[Add user/group](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/ユーザーまたはグループの追加)** を選択します。

    [Image: ユーザーの追加を示すスクリーンショット。]
6. **[ユーザー]** で **[選択なし]** を選択します。

    [Image: [選択なし] を示すスクリーンショット。]
7. 右側からユーザーを選択し、 **[選択]** ボタンを選択します。

    [Image: ユーザーの選択を示すスクリーンショット。]
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

詳細については、**[トラブルシューティングと推奨事項]** タブを開きます。ODBC ドライバーがメッセージを返した場合は、ここに表示される場合があります。 たとえば、メッセージ `ERROR [23000] [Microsoft][ODBC SQL Server Driver][SQL Server]Cannot insert the value NULL into column 'FirstName', table 'CONTOSO.dbo.Employees'; column does not allow nulls.` は ODBC ドライバーのエラーです。 この場合、`column does not allow nulls` は、データベース内の `FirstName` 列は必須ですが、プロビジョニングされるユーザーに `givenName` 属性がないため、ユーザーをプロビジョニングできなかったことを示している可能性があります。

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

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/use-scim-to-build-users-and-groups-endpoints"} -->
## Microsoft Entra ID からアプリにユーザーをプロビジョニングするための SCIM エンドポイントを構築する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-build-users-and-groups-endpoints
- Service: entra-id / app-provisioning
- Article date: 2026-04-27
- Summary: SCIM エンドポイントを開発し、SCIM API を AMicrosoft Entra ID と統合し、ユーザーとグループをクラウド アプリケーションへ自動でプロビジョニングする方法を学習します。

このチュートリアルでは、[Azure App Service](https://aka.ms/scimreferencecode) を使用して SCIM [参照コード](https://learn.microsoft.com/ja-jp/azure/app-service/)をデプロイする方法について説明します。 次に、cURL などのツールを使用するか、Microsoft Entra プロビジョニング サービスと統合してコードをテストします。 このチュートリアルは、SCIM の使用を開始したい開発者、または [SCIM エンドポイント](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)のテストに関心のある方を対象としています。

Important

このチュートリアルからリンクされている.NET参照コードは、.NET Core 3.1 をターゲットにしています。これは  Microsoft。 アップストリーム リポジトリは、アクティブなメンテナンス ([SCIM リファレンス コード README](https://github.com/AzureAD/SCIMReferenceCode#readme)) の保証なしでサンプルを "そのまま" 提供します。 現在サポートされている.NET ランタイムに対してサンプルをビルドまたは変更するには、ターゲット フレームワークと依存関係のアップグレードが必要になる場合があります。 既知の問題と機能要求については、 [アップストリーム リポジトリの未解決の問題を](https://github.com/AzureAD/SCIMReferenceCode/issues)参照してください。

このチュートリアルでは、以下の内容を学習します。

- SCIM エンドポイントを Azure にデプロイする。
- SCIM エンドポイントをテストする。

### SCIM エンドポイントを Azure にデプロイする

ここで説明する手順では、[Visual Studio 2019](https://visualstudio.microsoft.com/downloads/) および [Visual Studio Code](https://code.visualstudio.com/) を [Azure App Service](https://learn.microsoft.com/ja-jp/azure/app-service/) と使用して SCIM エンドポイントをサービスにデプロイします。 SCIM リファレンス コードは、ローカルで実行したり、オンプレミス サーバーでホストしたり、別の外部サービスにデプロイしたりできます。 SCIM エンドポイントのプロビジョニングについては、[SCIM エンドポイントのプロビジョニングの開発と計画に関するチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)を参照してください。

#### サンプル アプリを取得してデプロイする

GitHub から[リファレンス コード](https://github.com/AzureAD/SCIMReferenceCode)にアクセスし、 **[クローンまたはダウンロード]** を選択します。 **[デスクトップで開く]** を選択します。または、リンクをコピーし、Visual Studio を開き、 **[Clone or check out code](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/コードをクローンまたはチェックアウトする)** を選択し、コピーしたリンクを入力してローカル コピーを作成します。 パスの全文字列の長さが 260 文字以下となるフォルダーにファイルを保存します。

## [Visual Studio](#tab/visual-studio)
1. Visual Studio では、必ず、ホスティング リソースにアクセスできるアカウントにサインインしてください。
2. ソリューション エクスプローラーで、*Microsoft.SCIM.sln* を開き、*Microsoft.SCIM.WebHostSample* ファイルを右クリックします。 **[発行]** を選択します。

    [Image: サンプル ファイルを示すスクリーンショット。]

    注意

    このソリューションをローカルで実行するには、プロジェクトをダブルクリックし、 **[IIS Express]** を選択し、ローカル ホストの URL を使用する Web ページとしてプロジェクトを起動します。 詳細については、「[IIS Express の概要](https://learn.microsoft.com/ja-jp/iis/extensions/introduction-to-iis-express/iis-express-overview)」を参照してください。
3. **[プロファイルの作成]** を選択し、 **[App Service]** と **[新規作成]** が選択されていることを確認します。

    [Image: [発行] ウィンドウを示すスクリーンショット。]
4. ダイアログ オプションをステップ実行し、アプリの名前を任意の名前に変更します。 この名前は、アプリと SCIM エンドポイント URL の両方で使用されます。

    [Image: 新しいアプリ サービスの作成を示すスクリーンショット。]
5. 使用するリソース グループを選択し、 **[発行]** を選択します。

    [Image: 新しいアプリ サービスの発行を示すスクリーンショット。]

## [Visual Studio Code](#tab/visual-studio-code)
1. Visual Studio Code では、必ず、ホスティング リソースにアクセスできるアカウントにサインインしてください。
2. Visual Studio Code で、*Microsoft.SCIM.sln* ファイルを含むフォルダーを開きます。
3. Visual Studio Code 統合[ターミナル](https://code.visualstudio.com/docs/terminal/basics)を開き、[dotnet restore](https://learn.microsoft.com/ja-jp/nuget/consume-packages/install-use-packages-dotnet-cli#restore-packages) コマンドを実行します。 このコマンドは、プロジェクト ファイルに一覧表示されているパッケージを復元します。
4. ターミナルで、`cd Microsoft.SCIM.WebHostSample` コマンドを使用してディレクトリを変更します
5. アプリをローカルで実行するには、ターミナルで .NET CLI コマンドを実行します。 [dotnet run](https://learn.microsoft.com/ja-jp/dotnet/core/tools/dotnet-run) は[開発環境](https://learn.microsoft.com/ja-jp/aspnet/core/fundamentals/environments#set-environment-on-the-command-line)を使用して Microsoft.SCIM.WebHostSample プロジェクトを実行します。

    ```dotnetcli
    dotnet run --environment Development
    ```
6. インストールされていない場合は、[Azure App Service for Visual Studio Code](https://marketplace.visualstudio.com/items?itemName=ms-azuretools.vscode-azureappservice) 拡張機能を追加します。
7. Microsoft.SCIM.WebHostSample アプリを Azure アプリ Services にデプロイするには、[新しい App Services を作成](https://learn.microsoft.com/ja-jp/azure/app-service/quickstart-dotnetcore?tabs=net60&pivots=development-environment-vscode#2-publish-your-web-app)します。
8. Visual Studio Code ターミナルで、.NET CLI コマンドを実行します。 このコマンドは、bin/debug/publish ディレクトリにアプリのデプロイ可能な発行フォルダーを生成します。

    ```dotnetcli
    dotnet publish -c Debug
    ```
9. Visual Studio Code エクスプローラーで生成された**発行**フォルダーを右クリックし、[Web アプリへのデプロイ] を選択します。
10. 画面の上部にあるコマンド パレットに新しいワークフローが開きます。 アプリの発行先とする**サブスクリプション**を選択します。
11. 先ほど作成した **App Service** Web アプリを選択します。
12. Visual Studio Code に確認を求めるメッセージが表示されたら、**[デプロイ]** を選択します。 デプロイ プロセスには、しばらく時間がかかる場合があります。 プロセスが完了すると、右下隅に通知が表示され、デプロイされたアプリを参照するように求めるメッセージが表示されます。

---

#### App Service を構成する

**[Azure App Service]**&gt;**[構成]** でアプリケーションに移動し、 **[新しいアプリケーション設定]** を選択して、*Token\_\_TokenIssuer* 設定を `https://sts.windows.net/<tenant_id>/` の値で追加します。 `<tenant_id>` を Microsoft Entra テナント ID に置き換えます。

[Image: [アプリケーション設定] ウィンドウを示すスクリーンショット。]

[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups#integrate-your-scim-endpoint-with-the-azure-ad-provisioning-service)でエンタープライズ アプリケーションを使用してエンドポイントをテストする場合、2 つのオプションがあります。 環境を `Development` のままにして、トークンは `/scim/token` エンドポイントからテスト用に生成したものを指定するか、環境を `Production` に変更してトークン フィールドは空のままにすることができます。

これで完了です。 SCIM エンドポイントが発行されたので、Azure App Service の URL を使用して SCIM エンドポイントをテストできるようになりました。

### SCIM エンドポイントをテストする

SCIM エンドポイントへの要求には認可が必要です。 SCIM 標準には、複数のオプションがあります。 要求では、Cookie、基本認証、TLS クライアント認証、または [RFC 7644](https://tools.ietf.org/html/rfc7644#section-2) に規定されているメソッドのいずれかを使用できます。

ユーザー名とパスワードなどの安全でない方法は避けて、OAuth などのより安全な方法を優先してください。 Microsoft Entra ID では、有効期間が長いベアラー トークン (ギャラリーおよび非ギャラリー アプリケーション用) と、OAuth 認可付与 (ギャラリー アプリケーション用) がサポートされています。

注意

リポジトリで提供される認可方法はテスト専用です。 Microsoft Entra ID と統合する場合は、認可のガイダンスを参照できます。 [SCIM エンドポイントのプロビジョニングの計画](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)に関する記事を参照してください。

開発環境では、セキュリティ トークン検証の動作を制御するためのリファレンス コードなど、運用環境では安全でない機能が有効になります。 トークン検証コードでは自己署名セキュリティ トークンが使用され、署名キーは構成ファイルに保存されます。 **appsettings.Development.json** ファイルの *Token:IssuerSigningKey* パラメーターを参照してください。

```json
"Token": {
    "TokenAudience": "Microsoft.Security.Bearer",
    "TokenIssuer": "Microsoft.Security.Bearer",
    "IssuerSigningKey": "A1B2C3D4E5F6A1B2C3D4E5F6",
    "TokenLifetimeInMins": "120"
}
```

注意

エンドポイントに**GET**リクエストを送信すると、構成されたキーを使用してトークンが発行されます。 そのトークンを、後続の認可用のベアラー トークンとして使用できます。

既定のトークン検証コードは、Microsoft Entra トークンを使用するように構成されており、発行元のテナントは、**appsettings.json** ファイルの *Token:TokenIssuer* パラメーターを使用して構成する必要があります。

```json
"Token": {
    "TokenAudience": "8adf8e6e-67b2-4cf2-a259-e3dc5476c621",
    "TokenIssuer": "https://sts.windows.net/<tenant_id>/"
}
```
<!-- /MSL-PAGE -->
