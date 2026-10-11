# Microsoft Learn — Microsoft Entra / エンタープライズアプリ・プロビジョニング・アプリプロキシ (part 3)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 59

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/use-scim-to-provision-users-and-groups"} -->
## チュートリアル - Microsoft Entra IDからアプリにユーザー プロビジョニングするための SCIM エンドポイントを開発する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups
- Service: entra-id / app-provisioning
- Article date: 2026-09-15
- Summary: クロスドメイン ID 管理システム (SCIM) では、自動ユーザー プロビジョニングが標準化されます。 このチュートリアルでは、SCIM エンドポイントを開発し、SCIM API をMicrosoft Entra IDと統合し、クラウド アプリケーションへのユーザーとグループのプロビジョニングの自動化を開始する方法について説明します。

アプリケーション開発者は、システム for Cross-Domain Identity Management (SCIM) ユーザー管理 API を使用して、アプリケーションとMicrosoft Entra ID間のユーザーとグループの自動プロビジョニングを有効にすることができます。 この記事では、SCIM エンドポイントを構築し、Microsoft Entra プロビジョニング サービスと統合する方法について説明します。 SCIM 仕様では、プロビジョニングのための共通のユーザー スキーマが提供されます。 SAML や OpenID Connect などのフェデレーション標準とともに使用した場合、SCIM では エンドツーエンドの標準ベースのアクセス管理用ソリューションが管理者に提供されます。

[Image: Microsoft Entra IDからアプリへのプロビジョニングにSCIMを使用する]

SCIM 2.0 は、 `/Users` エンドポイントや `/Groups` エンドポイントなど、コア リソース エンドポイントを定義する標準化されたプロトコルです。 オブジェクトの作成、更新、および削除を行う共通の REST API エンドポイントを使用します。 SCIM は、グループ名、ユーザー名、名、姓、メール アドレスなどの一般的な属性の定義済みのスキーマで構成されています。

SCIM 2.0 REST API を提供するアプリでは、独自のユーザー管理 API を使用する煩わしさを軽減するか、なくすことができます。 たとえば、準拠している SCIM クライアントは、JSON オブジェクトの HTTP POST を `/Users` エンドポイントに送信して新しいユーザー エントリを作成する方法を認識しています。 同じ基本的なアクションに対して若干異なる API を必要とするのではなく、SCIM 標準に準拠しているアプリでは、既存のクライアント、ツール、およびコードをすぐに利用できます。

SCIM 2.0 (RFC [7642、7643、7644](https://tools.ietf.org/html/rfc7642)[) で](https://tools.ietf.org/html/rfc7643)定義されている管理用の標準ユーザー オブジェクト スキーマと rest API を使用すると、ID プロバイダーとアプリを相互に簡単に統合できます。 SCIM エンドポイントを構築するアプリケーション開発者は、カスタム作業を行わなくても、SCIM 準拠の任意のクライアントと統合できます。

アプリケーションへのプロビジョニングを自動化するには、Microsoft Entra プロビジョニング サービスによってアクセスされる SCIM エンドポイントを構築して統合する必要があります。 アプリケーションへのユーザーとグループのプロビジョニングを開始するには、次の手順を使用します。

1. ユーザーとグループのスキーマを設計する - アプリケーションのオブジェクトと属性を特定して、Microsoft Entra SCIM 実装でサポートされているユーザーおよびグループ スキーマへのマッピング方法を決定します。
2. MICROSOFT ENTRA SCIM 実装 - SCIM プロトコル要求の処理と応答をモデル化するために、Microsoft Entra プロビジョニング サービスがどのように実装されているかを理解します。
3. SCIM エンドポイントの構築 - Microsoft Entra プロビジョニング サービスと統合するには、エンドポイントが SCIM 2.0 互換である必要があります。 オプションとして、Microsoft共通言語インフラストラクチャ (CLI) ライブラリとコード サンプルを使用して、エンドポイントを構築します。 これらのサンプルは、参照とテストのみを目的としています。実稼働アプリの依存関係として使用することはお勧めしません。
4. SCIM エンドポイントをMicrosoft Entra プロビジョニング サービスに統合します。 Microsoft Entra IDでは、SCIM 2.0 を実装する複数のサード パーティ製アプリケーションがサポートされています。 これらのアプリのいずれかを使用すると、ユーザーとグループのプロビジョニングとプロビジョニング解除の両方をすばやく自動化できます。
5. [省略可能] アプリケーションを Microsoft Entra アプリケーション ギャラリーに発行する - 顧客がアプリケーションを簡単に検出し、プロビジョニングを簡単に構成できるようにします。

[Image: SCIM エンドポイントを Microsoft Entra ID と統合するために必要な手順を示すダイアグラム]

### ユーザーとグループのスキーマを設計する

アプリケーションはそれぞれ、ユーザーまたはグループを作成するためのさまざまな属性を必要とします。 アプリケーションで必要なオブジェクト (ユーザー、グループ) および属性 (名前、マネージャー、役職など) を特定して、統合を開始します。

SCIM 標準では、ユーザーとグループを管理するためのスキーマが定義されています。

**コア** ユーザー スキーマに必要な属性は 3 つだけです (他のすべての属性は省略可能です)。

- `id` (サービス プロバイダーが定義した識別子)
- `userName` (ユーザーの一意識別子。通常、Microsoft Entra ユーザー プリンシパル名にマップされます)
- `meta`、サービス プロバイダーによって管理される *読み取り専用* メタデータ

SCIM 標準では、 **コア** ユーザー スキーマに加えて、アプリケーションのニーズに合わせてユーザー スキーマを拡張するためのモデルを持つ **エンタープライズ** ユーザー拡張機能を定義します。

たとえば、アプリケーションでユーザーの電子メールとユーザーのマネージャーの両方が必要な場合は、 **コア** スキーマを使用してユーザーの電子メールを収集し、 **エンタープライズ** ユーザー スキーマを使用してユーザーのマネージャーを収集します。

スキーマを設計するには、次の手順に従います。

1. アプリケーションに必要な属性を一覧表示し、認証に必要な属性 (loginName や電子メールなど) として分類します。 属性は、ユーザーのライフサイクル (状態やアクティブなど) と、アプリケーションの動作に必要なその他すべての属性 (マネージャー、タグなど) を管理するために必要です。
2. **属性がコア** ユーザー スキーマまたは**エンタープライズ** ユーザー スキーマで既に定義されているかどうかを確認します。 定義されていない場合は、欠けている属性をカバーする拡張機能をユーザー スキーマに対して定義する必要があります。 ユーザー `tag` をプロビジョニングできるようにする、ユーザーに対する拡張機能についての例を参照してください。
3. SCIM 属性を Microsoft Entra ID のユーザー属性にマップします。 SCIM エンドポイントで定義した属性の 1 つに、Microsoft Entra ユーザー スキーマに明確な対応するものがない場合は、テナント管理者にスキーマの拡張を案内するか、`tags` プロパティの例に示すように拡張属性を使用します。

次の表に、必要な属性の例を示します。

| アプリの必須属性と例 | マッピングされた SCIM 属性 | マップされたMicrosoft Entra属性 |
| --- | --- | --- |
| ログイン名 | ユーザー名 | userPrincipalName |
| ファーストネーム | name.givenName | givenName |
| lastName | name.familyName | surName |
| ワークメール | emails[type eq "work"].value | 郵便 |
| マネージャー | マネージャー | マネージャー |
| タグ | `urn:ietf:params:scim:schemas:extension:CustomExtensionName:2.0:User:tag` | extensionAttribute1 |
| 状態 | 活動中 | isSoftDeleted (ユーザーに格納されない計算値) |

次の JSON ペイロードは、SCIM スキーマの例を示しています。

```json
{
     "schemas": ["urn:ietf:params:scim:schemas:core:2.0:User",
      "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User",
      "urn:ietf:params:scim:schemas:extension:CustomExtensionName:2.0:User"],
     "userName":"bjensen@testuser.com",
     "id": "48af03ac28ad4fb88478",
     "externalId":"bjensen",
     "name":{
       "familyName":"Jensen",
       "givenName":"Barbara"
     },
     "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User": {
     "manager": "123456"
   },
     "urn:ietf:params:scim:schemas:extension:CustomExtensionName:2.0:User": {
     "tag": "701984",
   },
   "meta": {
     "resourceType": "User",
     "created": "2010-01-23T04:56:22Z",
     "lastModified": "2011-05-13T04:42:34Z",
     "version": "W\/\"3694e05e9dff591\"",
     "location":
 "https://example.com/v2/Users/00aa00aa-bb11-cc22-dd33-44ee44ee44ee"
   }
}   
```

注

アプリケーションに必要な属性に加えて、JSON の表現には、必要な `id`、`externalId`、`meta` の各属性も含まれています。

`/User` と `/Group` を分類して、Microsoft Entra IDの既定のユーザー属性を SCIM RFC にマップするのに役立ちます。「[カスタマイズ属性がMicrosoft Entra IDと SCIM エンドポイントの間でマップされる方法](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を参照してください。

次の表に、ユーザー属性の例を示します。

| Microsoft Entra ユーザー | `urn:ietf:params:scim:schemas:extension:enterprise:2.0:User` |
| --- | --- |
| ソフト削除済みかどうか | 活動中 |
| 部署 | `urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department` |
| displayName | displayName |
| 従業員ID | `urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber` |
| ファクシミリ電話番号 | phoneNumbers[type eq "fax"].value |
| givenName | name.givenName |
| 職務タイトル | タイトル |
| メール | emails[type eq "work"].value |
| メールニックネーム | externalId |
| マネージャー | `urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager` |
| モバイル | phoneNumbers[type eq "mobile"].value |
| 郵便番号 | addresses[type eq "work"].postalCode |
| プロキシ-アドレス | emails[type eq "other"].Value |
| physical-Delivery-OfficeName | addresses[type eq "other"].Formatted |
| streetAddress | addresses[type eq "work"].streetAddress |
| 名字 | name.familyName |
| 電話番号 | phoneNumbers[type eq "work"].value |
| ユーザープリンシパル名 | ユーザー名 |

次の表に、グループ属性の例を示します。

| Microsoft Entra グループ | `urn:ietf:params:scim:schemas:core:2.0:Group` |
| --- | --- |
| displayName | displayName |
| members | members |
| オブジェクトID | externalId |

注

ユーザーとグループの両方、またはここに示されているすべての属性をサポートする必要はありません。これは、Microsoft Entra ID内の属性が SCIM プロトコルのプロパティにどのようにマップされるかについての参照にすぎません。

SCIM RFC では複数のエンドポイントが定義されています。 `/User` エンドポイントで開始し、そこから展開できます。 次の表に、いくつかの SCIM エンドポイントを示します。

| エンドポイント | 説明 |
| --- | --- |
| /利用者 | ユーザー オブジェクトに対して CRUD 操作が実行されます。 |
| /群 | グループ オブジェクトに対して CRUD 操作が実行されます。 |
| /Schemas | 各クライアントおよびサービス プロバイダーによってサポートされている属性のセットは、異なる場合があります。 たとえば、あるサービス プロバイダーに `name`、`title`、`emails` が含まれ、別のサービス プロバイダーに `name`、`title`、`phoneNumbers` が使用されることがあります。 スキーマ エンドポイントにより、サポートされている属性を検出できます。 |
| /Bulk | 一括操作を使用すると、1 回の操作でリソース オブジェクトの大きなコレクションに対する操作を実行できます (たとえば、大きなグループのメンバーシップの更新)。 現在、SCIM /Bulk はサポートされていませんが、これはパフォーマンスの向上に役立つ将来のサポートを目的としています。 |
| /サービスプロバイダコンフィグ | サポートされているリソースや認証方法など、サポートされている SCIM 標準の機能についての詳細が提供されます。 |
| /リソースタイプ | 各リソースに関するメタデータを指定します。 |

注

カスタム属性をサポートする場合やスキーマが頻繁に変更される場合は、`/Schemas` エンドポイントを使用してください。クライアントで最新のスキーマを自動的に取得できます。 グループをサポートするには、`/Bulk` エンドポイントを使用します。 現在、/Bulk エンドポイントはサポートされていませんが、これはパフォーマンスの向上に役立つ将来のサポートを目的としています。

### Microsoft Entra SCIM の実装について理解する

Microsoft Entra プロビジョニング サービスは、SCIM 2.0 ユーザー管理 API をサポートするように設計されています。

重要

Microsoft Entra SCIM 実装の動作は、2018 年 12 月 18 日に最後に更新されました。 変更された内容については、Microsoft Entra ユーザー プロビジョニング サービスの [SCIM 2.0 プロトコル準拠](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-config-problem-scim-compatibility)を参照してください。

SCIM 2.0 プロトコル仕様の中で、アプリケーションは次の要件をサポートする必要があるとされています。

| 必要条件 | 参考 (SCIM プロトコル) |
| --- | --- |
| ユーザーを (必要に応じてグループも) 作成する | [セクション 3.3](https://tools.ietf.org/html/rfc7644#section-3.3) |
| PATCH 要求でユーザーまたはグループを変更する | [セクション 3.5.2](https://tools.ietf.org/html/rfc7644#section-3.5.2). サポートにより確実に、パフォーマンスの高い方法でグループとユーザーがプロビジョニングされます。 |
| 以前に作成したユーザーまたはグループの既知のリソースを取得する | [セクション 3.4.1](https://tools.ietf.org/html/rfc7644#section-3.4.1) |
| ユーザーまたはグループを照会する | [セクション 3.4.2](https://tools.ietf.org/html/rfc7644#section-3.4.2). 既定では、ユーザーの取得には `id`、ユーザーのクエリには `username` と `externalId`、グループのクエリには `displayName` が使用されます。 |
| グループ リソースの照会時に、フィルター excludedAttributes=members を使用する | セクション [3.4.2.2](https://www.rfc-editor.org/rfc/rfc7644#section-3.4.2.2) |
| ユーザーの一覧表示とページ番号付けをサポートする | [セクション 3.4.2.4](https://datatracker.ietf.org/doc/html/rfc7644#section-3.4.2.4). |
| ユーザー `active=false` のソフト削除とユーザー `active=true` の復元を行う | ユーザーがアクティブかどうかに関係なく、要求でユーザー オブジェクトが返される必要があります。 ユーザーが返されないのは、アプリケーションから完全削除されているときです。 |
| /Schemas エンドポイントをサポートする | [セクション 7](https://tools.ietf.org/html/rfc7643#page-30) スキーマ検出エンドポイントは、さらに多くの属性を検出するために使用されます。 |
| アプリケーションへのMicrosoft Entra IDの認証と承認のために、1 つのベアラー トークンを受け入れます。 |  |

SCIM エンドポイントを実装する場合は、一般的なガイドラインを使用して、Microsoft Entra IDとの互換性を確保します。

#### 全般:

- `id` は、すべてのリソースの必須のプロパティです。 リソースを返すすべての応答において、メンバーが 0 の `ListResponse` を除き、各リソースにこのプロパティが含まれるようにする必要があります。
- 送信された値は、送信時と同じ形式で格納する必要があります。 無効な値は、わかりやすいアクション可能なエラー メッセージを付けて拒否する必要があります。 データの変換は、Microsoft Entra IDからのデータと SCIM アプリケーションに格納されているデータの間で行われるべきではありません。 (例: (55555555555 として送信された電話番号は、+5 (555) 555-5555 として保存または返すことはできません)
- **PATCH** 応答にリソース全体を含める必要はありません。
- SCIM の構造要素、特に **PATCH**`op` 操作値については、大文字と小文字の区別は不要です。[セクション 3.5.2](https://tools.ietf.org/html/rfc7644#section-3.5.2) で定義されています。 Microsoft Entra IDは、`op` の値を **Add**、**Replace**、および **Remove** として出力します。
- Microsoft Entra IDは、エンドポイントと資格情報が有効であることを確認するために、ランダムなユーザーとグループをフェッチする要求を行います。 これは、 **テスト接続** フローの一部としても行われます。
- SCIM エンドポイントで HTTPS をサポートします。
- カスタムの複合属性と複数値属性はサポートされていますが、このような場合にデータをプルする複雑なデータ構造はMicrosoft Entra IDあまりありません。 名前/値属性は簡単に にマップできますが、3 つ以上のサブ属性を持つ複雑な属性へのデータのフローはサポートされていません。
- 複数値を持つ複合属性の "type" サブ属性の値は一意である必要があります。 たとえば、サブタイプが "work" の 2 つの異なるメール アドレスを使用することはできません。
- すべての応答のヘッダーは、content-Type: application/scim+json である必要があります

#### リソースの取得:

- クエリ/フィルター要求への応答は常に `ListResponse` である必要があります。
- Microsoft Entra専用では、次の演算子を使用します: `eq`、`and`
- リソースを照会できる属性は、アプリケーションで一致する属性として設定する必要があります。 [ユーザー プロビジョニング属性マッピングのカスタマイズを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)参照してください。

#### /ユーザー：

- エンタイトルメント属性はサポートされていません。
- ユーザーの一意性のために考慮される属性は、フィルタクエリの一部として使用できる必要があります。 (たとえば、userName と emails[type eq "work"] の両方に対してユーザーの一意性が評価される場合、フィルターを持つ /Users に対する GET では、 *userName eq "user@contoso.com"* クエリと *emails[type eq "work"].value eq "user@contoso.com"* クエリの両方を許可する必要があります。

#### /グループ：

- グループは省略可能ですが、SCIM 実装で **PATCH** 要求がサポートされている場合にのみサポートされます。
- Microsoft Entra IDおよび SCIM アプリケーションと一致させるには、'displayName' 値の一意性がグループに必要です。 一意性は SCIM プロトコルの要件ではありませんが、SCIM エンドポイントとMicrosoft Entra IDを統合するための要件です。

#### /スキーマ (スキーマ検出)

- 要求/応答のサンプル
- スキーマ検出は、特定のギャラリー アプリケーションで使用されています。 スキーマ検出は、既存のギャラリー SCIM アプリケーションのスキーマに属性を追加するための唯一の手段です。 スキーマ検出は、現在、ギャラリー以外のカスタム SCIM アプリケーションではサポートされていません。
- 値が存在しない場合は、null 値を送信しないでください。
- プロパティ値はキャメル ケース (例: readWrite) にする必要があります。
- リスト応答を必ず返さなければなりません。
- Microsoft Entra プロビジョニング サービスは、プロビジョニング構成を保存するときに /schemas 要求を行います。 この要求は、プロビジョニングの編集ページを開いたときにも行われます。 その他の検出された属性は、[対象の属性] リストにある属性マッピングでお客様に示されます。 スキーマ検出では、対象の属性が新たに追加されるだけです。 属性は削除されません。

#### ユーザーのプロビジョニングとプロビジョニング解除

次の図は、アプリケーションの ID ストア内のユーザーのライフサイクルを管理するために SCIM エンドポイントに送信Microsoft Entra IDメッセージを示しています。

[Image: ユーザーのプロビジョニング解除シーケンスを示す図。]

#### グループのプロビジョニングとデプロビジョニング

グループのプロビジョニングとプロビジョニング解除はオプションです。 実装して有効化されると、以下の図は、アプリケーションの ID ストアでグループのライフサイクルを管理するために、SCIM エンドポイントにMicrosoft Entra IDが送信するメッセージを示しています。 それらのメッセージは、次の 2 つの点でユーザーに関するメッセージと異なっています。

- グループを取得する要求では、要求に対する応答の中で提示されるリソースから、members 属性が除外されるように指定しています。
- 参照属性に特定の値があるかどうかを判別する要求は、members 属性に関する要求になります。

次の図は、グループのプロビジョニング解除シーケンスを示しています。

[Image: グループのプロビジョニング解除シーケンスを示す図。]

#### SCIM プロトコル要求と応答

この記事では、Microsoft Entra プロビジョニング サービスによって出力される SCIM 要求の例と、予想される応答の例を示します。 最良の結果を得るには、このような要求をこの形式で処理し、想定される応答を出力するよう、アプリをコーディングしてください。

重要

Microsoft Entra ユーザー プロビジョニング サービスが例で説明した操作をいつどのように出力するかを理解するには、「[プロビジョニングのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works#provisioning-cycles-initial-and-incremental)の[プロビジョニング サイクル: 初期と増分](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works)のセクションを参照してください。

ユーザー操作

- ユーザーの作成 (要求 / 応答)
- ユーザー取得 (リクエスト / 応答)
- クエリでユーザーを取得 する (Request / Response)
- クエリでユーザーを取得する - ゼロの結果 (Request / Response)
- ユーザーの更新 [複数値のプロパティ] (Request / Response)
- ユーザーの更新 [単一値プロパティ] (Request / Response)
- ユーザーの無効化 (要求 / Response)
- ユーザーの削除 (要求 / Response)

グループ操作

- グループの作成 (要求 / Response)
- グループの取得 (要求 / Response)
- DisplayName によるグループ化の取得 (Request / Response)
- グループの更新 [メンバー以外の属性] (Request / Response)
- グループの更新 [メンバーの追加] (要求 / Response)
- グループの更新 [メンバーの削除] (Request / Response)
- グループの削除 (要求 / Response)

スキーマ検出

- スキーマの検出 (Request / Response)

#### ユーザー操作

- `userName` または `emails[type eq "work"]` 属性を使用してユーザーにクエリを実行します。

##### ユーザーを作成

###### リクエスト

*POST /Users*

```json
{
    "schemas": [
        "urn:ietf:params:scim:schemas:core:2.0:User",
        "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User"],
    "externalId": "0a21f0f2-8d2a-4f8e-bf98-7363c4aed4ef",
    "userName": "Test_User_00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
    "active": true,
    "emails": [{
        "primary": true,
        "type": "work",
        "value": "Test_User_11bb11bb-cc22-dd33-ee44-55ff55ff55ff@testuser.com"
    }],
    "meta": {
        "resourceType": "User"
    },
    "name": {
        "formatted": "givenName familyName",
        "familyName": "familyName",
        "givenName": "givenName"
    },
    "roles": []
}
```

###### [応答]

*HTTP/1.1 201 Created*

```json
{
    "schemas": ["urn:ietf:params:scim:schemas:core:2.0:User"],
    "id": "48af03ac28ad4fb88478",
    "externalId": "0a21f0f2-8d2a-4f8e-bf98-7363c4aed4ef",
    "meta": {
        "resourceType": "User",
        "created": "2018-03-27T19:59:26.000Z",
        "lastModified": "2018-03-27T19:59:26.000Z"
    },
    "userName": "Test_User_00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
    "name": {
        "formatted": "givenName familyName",
        "familyName": "familyName",
        "givenName": "givenName",
    },
    "active": true,
    "emails": [{
        "value": "Test_User_11bb11bb-cc22-dd33-ee44-55ff55ff55ff@testuser.com",
        "type": "work",
        "primary": true
    }]
}
```

###### SCIM バリデーターによって行われる検証

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

##### ユーザーの取得

###### リクエスト

*/Users/5d48a0a8e9f04aa38008を取得してください*

###### 応答 (ユーザー検出)

*HTTP/1.1 200 OK*

```json
{
    "schemas": ["urn:ietf:params:scim:schemas:core:2.0:User"],
    "id": "5d48a0a8e9f04aa38008",
    "externalId": "58342554-38d6-4ec8-948c-50044d0a33fd",
    "meta": {
        "resourceType": "User",
        "created": "2018-03-27T19:59:26.000Z",
        "lastModified": "2018-03-27T19:59:26.000Z"
    },
    "userName": "Test_User_00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
    "name": {
        "formatted": "givenName familyName",
        "familyName": "familyName",
        "givenName": "givenName",
    },
    "active": true,
    "emails": [{
        "value": "Test_User_11bb11bb-cc22-dd33-ee44-55ff55ff55ff@testuser.com",
        "type": "work",
        "primary": true
    }]
}
```

###### リクエスト

*/Users/5171a35d82074e068ce2を取得してください*

###### 応答 (ユーザーが見つかりません。詳細は必要なく、状態のみです)。

*HTTP/1.1 404 が見つかりません*

```json
{
    "schemas": [
        "urn:ietf:params:scim:api:messages:2.0:Error"
    ],
    "status": "404",
    "detail": "Resource 23B51B0E5D7AE9110A49411D@7cca31655d49f3640a494224 not found"
}
```

##### クエリによるユーザーの取得

###### リクエスト

*GET /Users?filter=userName eq "Test\_User\_00aa00aa-bb11-cc22-dd33-44ee44ee44ee"*

###### [応答]

*HTTP/1.1 200 OK*

```json
{
    "schemas": ["urn:ietf:params:scim:api:messages:2.0:ListResponse"],
    "totalResults": 1,
    "Resources": [{
        "schemas": ["urn:ietf:params:scim:schemas:core:2.0:User"],
        "id": "2441309d85324e7793ae",
        "externalId": "7fce0092-d52e-4f76-b727-3955bd72c939",
        "meta": {
            "resourceType": "User",
            "created": "2018-03-27T19:59:26.000Z",
            "lastModified": "2018-03-27T19:59:26.000Z"
            
        },
        "userName": "Test_User_00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
        "name": {
            "familyName": "familyName",
            "givenName": "givenName"
        },
        "active": true,
        "emails": [{
            "value": "Test_User_11bb11bb-cc22-dd33-ee44-55ff55ff55ff@testuser.com",
            "type": "work",
            "primary": true
        }]
    }],
    "startIndex": 1,
    "itemsPerPage": 20
}
```

##### クエリによるユーザーの取得 - 0 件の結果

###### リクエスト

*GET /Users?filter=userName eq "存在しないユーザー"*

###### [応答]

*HTTP/1.1 200 OK*

```json
{
    "schemas": ["urn:ietf:params:scim:api:messages:2.0:ListResponse"],
    "totalResults": 0,
    "Resources": [],
    "startIndex": 1,
    "itemsPerPage": 20
}
```

##### ユーザーを更新 [複数値のプロパティ]

###### リクエスト

*パッチ /Users/6764549bef60420686bc HTTP/1.1*

```json
{
    "schemas": ["urn:ietf:params:scim:api:messages:2.0:PatchOp"],
    "Operations": [
            {
            "op": "Replace",
            "path": "emails[type eq \"work\"].value",
            "value": "updatedEmail@microsoft.com"
            },
            {
            "op": "Replace",
            "path": "name.familyName",
            "value": "updatedFamilyName"
            }
    ]
}
```

###### [応答]

*HTTP/1.1 200 OK*

```json
{
    "schemas": ["urn:ietf:params:scim:schemas:core:2.0:User"],
    "id": "6764549bef60420686bc",
    "externalId": "6c75de36-30fa-4d2d-a196-6bdcdb6b6539",
    "meta": {
        "resourceType": "User",
        "created": "2018-03-27T19:59:26.000Z",
        "lastModified": "2018-03-27T19:59:26.000Z"
    },
    "userName": "Test_User_00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
    "name": {
        "formatted": "givenName updatedFamilyName",
        "familyName": "updatedFamilyName",
        "givenName": "givenName"
    },
    "active": true,
    "emails": [{
        "value": "updatedEmail@microsoft.com",
        "type": "work",
        "primary": true
    }]
}
```

##### ユーザーを更新する [単一値プロパティ]

###### リクエスト

*パッチ /Users/5171a35d82074e068ce2 HTTP/1.1*

```json
{
    "schemas": ["urn:ietf:params:scim:api:messages:2.0:PatchOp"],
    "Operations": [{
        "op": "Replace",
        "path": "userName",
        "value": "5b50642d-79fc-4410-9e90-4c077cdd1a59@testuser.com"
    }]
}
```

###### [応答]

*HTTP/1.1 200 OK*

```json
{
    "schemas": ["urn:ietf:params:scim:schemas:core:2.0:User"],
    "id": "5171a35d82074e068ce2",
    "externalId": "aa1eca08-7179-4eeb-a0be-a519f7e5cd1a",
    "meta": {
        "resourceType": "User",
        "created": "2018-03-27T19:59:26.000Z",
        "lastModified": "2018-03-27T19:59:26.000Z"
        
    },
    "userName": "5b50642d-79fc-4410-9e90-4c077cdd1a59@testuser.com",
    "name": {
        "formatted": "givenName familyName",
        "familyName": "familyName",
        "givenName": "givenName",
    },
    "active": true,
    "emails": [{
        "value": "Test_User_11bb11bb-cc22-dd33-ee44-55ff55ff55ff@testuser.com",
        "type": "work",
        "primary": true
    }]
}
```

###### SCIM バリデーターによって行われる検証

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

#### ユーザーの無効化

###### リクエスト

*パッチ /Users/5171a35d82074e068ce2 HTTP/1.1*

```json
{
    "Operations": [
        {
            "op": "Replace",
            "path": "active",
            "value": false
        }
    ],
    "schemas": [
        "urn:ietf:params:scim:api:messages:2.0:PatchOp"
    ]
}
```

###### [応答]

```json
{
    "schemas": [
        "urn:ietf:params:scim:schemas:core:2.0:User"
    ],
    "id": "CEC50F275D83C4530A495FCF@834d0e1e5d8235f90a495fda",
    "userName": "deanruiz@testuser.com",
    "name": {
        "familyName": "Harris",
        "givenName": "Larry"
    },
    "active": false,
    "emails": [
        {
            "value": "gloversuzanne@testuser.com",
            "type": "work",
            "primary": true
        }
    ],
    "addresses": [
        {
            "country": "ML",
            "type": "work",
            "primary": true
        }
    ],
    "meta": {
        "resourceType": "Users",
        "location": "/scim/5171a35d82074e068ce2/Users/CEC50F265D83B4530B495FCF@5171a35d82074e068ce2"
    }
}
```

###### SCIM バリデーターによって行われる検証

**アクティブな属性を False に更新する**

- POST /Users/ - スキーマに基づいてリソースを作成します
    - HTTP 2xx の成功
    - GET 要求で無効なユーザーを返す必要がある
- PATCH /Users/{id} – "active" 属性を false に設定する JSON パッチ ドキュメントを発行します。
    - HTTP 2xx の成功
- GET /Users?filter={joiningProperty} eq "value" – アクティブな属性が false になったことを確認するためにユーザーを取得します。
    - 返されたユーザー レコードは、ACTIVE=FALSE である必要があります。

##### ユーザーの削除

###### リクエスト

*/Users/5171a35d82074e068ce2 HTTP/1.1 を削除します。*

###### [応答]

*HTTP/1.1 204 コンテンツなし*

#### グループ操作

- グループは、空のメンバーのリストで作成されます。
- `displayName` 属性を使用してグループのクエリを実行します。
- グループ PATCH 要求に更新すると、応答に *HTTP 204 No Content* が生成されます。 全メンバーのリストを含めて本文を返すことはお勧めできません。
- グループの全メンバーを返すことをサポートする必要はありません。

##### グループの作成

###### リクエスト

*POST /グループ HTTP/1.1*

```json
{
    "schemas": ["urn:ietf:params:scim:schemas:core:2.0:Group", "http://schemas.microsoft.com/2006/11/ResourceManagement/ADSCIM/2.0/Group"],
    "externalId": "8aa1a0c0-c4c3-4bc0-b4a5-2ef676900159",
    "displayName": "displayName",
    "meta": {
        "resourceType": "Group"
    }
}
```

###### [応答]

*HTTP/1.1 201 Created*

```json
{
    "schemas": ["urn:ietf:params:scim:schemas:core:2.0:Group"],
    "id": "927fa2c08dcb4a7fae9e",
    "externalId": "8aa1a0c0-c4c3-4bc0-b4a5-2ef676900159",
    "meta": {
        "resourceType": "Group",
        "created": "2018-03-27T19:59:26.000Z",
        "lastModified": "2018-03-27T19:59:26.000Z"
        
    },
    "displayName": "displayName",
    "members": []
}
```

###### SCIM バリデーターによって行われる検証

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

##### グループの取得

###### リクエスト

*GET /Groups/40734ae655284ad3abcc?excludedAttributes=members HTTP/1.1*

###### [応答]

*HTTP/1.1 200 OK*

```json
{
    "schemas": ["urn:ietf:params:scim:schemas:core:2.0:Group"],
    "id": "40734ae655284ad3abcc",
    "externalId": "60f1bb27-2e1e-402d-bcc4-ec999564a194",
    "meta": {
        "resourceType": "Group",
        "created": "2018-03-27T19:59:26.000Z",
        "lastModified": "2018-03-27T19:59:26.000Z"
    },
    "displayName": "displayName",
}
```

##### displayName でのグループの取得

###### リクエスト

*GET /Groups?excludedAttributes=members>filter=displayName eq "displayName" HTTP/1.1*

###### [応答]

*HTTP/1.1 200 OK*

```json
{
    "schemas": ["urn:ietf:params:scim:api:messages:2.0:ListResponse"],
    "totalResults": 1,
    "Resources": [{
        "schemas": ["urn:ietf:params:scim:schemas:core:2.0:Group"],
        "id": "8c601452cc934a9ebef9",
        "externalId": "0db508eb-91e2-46e4-809c-30dcbda0c685",
        "meta": {
            "resourceType": "Group",
            "created": "2018-03-27T22:02:32.000Z",
            "lastModified": "2018-03-27T22:02:32.000Z",
            
        },
        "displayName": "displayName",
    }],
    "startIndex": 1,
    "itemsPerPage": 20
}
```

##### グループの更新 [非メンバー属性]

###### リクエスト

*パッチ /Groups/fa2ce26709934589afc5 HTTP/1.1*

```json
{
    "schemas": ["urn:ietf:params:scim:api:messages:2.0:PatchOp"],
    "Operations": [{
        "op": "Replace",
        "path": "displayName",
        "value": "1879db59-3bdf-4490-ad68-ab880a269474updatedDisplayName"
    }]
}
```

###### [応答]

*HTTP/1.1 204 コンテンツなし*

###### SCIM バリデーターによって行われる検証

**グループ属性の更新**

- POST /Groups - 属性を更新する新しいグループ リソースを作成します。
    - POST は HTTP 2xx を返します
- PATCH /Groups/{id} – 置換操作を使用して JSON パッチ ドキュメントを送信し、既存のグループの 1 つ以上の属性 (メンバーを除く) を更新します。
    - PATCH が成功を返す (HTTP 2xx)
- GET /Groups?filter={joiningProperty} eq "value" – グループの属性が正しく更新されたことを確認します。
    - GET は、修正プログラムが適用されたグループを返します
    - 返されたグループの属性は、PATCH 要求で変更された属性と一致します

#### グループの更新 [メンバーの追加]

###### リクエスト

*パッチ /groups/a99962b9f99d4c4fac67 HTTP/1.1*

```json
{
    "schemas": ["urn:ietf:params:scim:api:messages:2.0:PatchOp"],
    "Operations": [{
        "op": "Add",
        "path": "members",
        "value": [{
            "$ref": null,
            "value": "f648f8d5ea4e4cd38e9c"
        }]
    }]
}
```

###### [応答]

*HTTP/1.1 204 コンテンツなし*

###### SCIM バリデーターによって行われる検証

**新しいグループ リソースを作成する**

- POST /Groups - メンバーを追加する新しいグループ リソースを作成します
    - POST は HTTP 2xx を返します
- POST /Users – グループ メンバーとして使用する新しいユーザー リソースを作成します。
    - POST は HTTP 2xx を返します
- PATCH /Groups/{id} – JSON パッチ ドキュメントを使用して、新しく作成されたユーザーの識別子をグループに追加します。
    - PATCH 成功を返します

##### グループの更新 [メンバーの削除]

###### リクエスト

*パッチ /groups/a99962b9f99d4c4fac67 HTTP/1.1*

```json
{
    "schemas": ["urn:ietf:params:scim:api:messages:2.0:PatchOp"],
    "Operations": [{
        "op": "Remove",
        "path": "members",
        "value": [{
            "$ref": null,
            "value": "f648f8d5ea4e4cd38e9c"
        }]
    }]
}
```

###### [応答]

*HTTP/1.1 204 コンテンツなし*

##### グループの削除

###### リクエスト

*/Groups/cdb1ce18f65944079d37 HTTP/1.1 を削除します。*

###### [応答]

*HTTP/1.1 204 コンテンツなし*

#### スキーマ検出

##### スキーマの検出

###### リクエスト

*GET /スキーマ*

###### [応答]

*HTTP/1.1 200 OK*

```json
{
    "schemas": [
        "urn:ietf:params:scim:api:messages:2.0:ListResponse"
    ],
    "itemsPerPage": 50,
    "startIndex": 1,
    "totalResults": 3,
    "Resources": [
  {
    "schemas": ["urn:ietf:params:scim:schemas:core:2.0:Schema"],
    "id" : "urn:ietf:params:scim:schemas:core:2.0:User",
    "name" : "User",
    "description" : "User Account",
    "attributes" : [
      {
        "name" : "userName",
        "type" : "string",
        "multiValued" : false,
        "description" : "Unique identifier for the User, typically
used by the user to directly authenticate to the service provider.
Each User MUST include a non-empty userName value.  This identifier
MUST be unique across the service provider's entire set of Users.
REQUIRED.",
        "required" : true,
        "caseExact" : false,
        "mutability" : "readWrite",
        "returned" : "default",
        "uniqueness" : "server"
      },                
    ],
    "meta" : {
      "resourceType" : "Schema",
      "location" :
        "/v2/Schemas/urn:ietf:params:scim:schemas:core:2.0:User"
    }
  },
  {
    "schemas": ["urn:ietf:params:scim:schemas:core:2.0:Schema"],
    "id" : "urn:ietf:params:scim:schemas:core:2.0:Group",
    "name" : "Group",
    "description" : "Group",
    "attributes" : [
      {
        "name" : "displayName",
        "type" : "string",
        "multiValued" : false,
        "description" : "A human-readable name for the Group.
REQUIRED.",
        "required" : false,
        "caseExact" : false,
        "mutability" : "readWrite",
        "returned" : "default",
        "uniqueness" : "none"
      },
    ],
    "meta" : {
      "resourceType" : "Schema",
      "location" :
        "/v2/Schemas/urn:ietf:params:scim:schemas:core:2.0:Group"
    }
  },
  {
    "schemas": ["urn:ietf:params:scim:schemas:core:2.0:Schema"],
    "id" : "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User",
    "name" : "EnterpriseUser",
    "description" : "Enterprise User",
    "attributes" : [
      {
        "name" : "employeeNumber",
        "type" : "string",
        "multiValued" : false,
        "description" : "Numeric or alphanumeric identifier assigned
to a person, typically based on order of hire or association with an
organization.",
        "required" : false,
        "caseExact" : false,
        "mutability" : "readWrite",
        "returned" : "default",
        "uniqueness" : "none"
      },
    ],
    "meta" : {
      "resourceType" : "Schema",
      "location" :
"/v2/Schemas/urn:ietf:params:scim:schemas:extension:enterprise:2.0:User"
    }
  }
]
}
```

#### セキュリティ要件

**TLS プロトコルのバージョン**

許容されるプロトコル のバージョンは TLS 1.2 のみです。 他の SSL/TLS バージョンは許可されていません。

- RSA キーは、2,048 ビット以上である必要があります。
- ECC キーは 256 ビット以上で、承認された楕円曲線を使用して生成されている必要があります

**キーの長さ**

すべてのサービスでは、十分な長さの暗号化キーを使用して生成された X.509 証明書が使用されている必要があります。

**暗号スイート**

すべてのサービスは、以下の暗号スイートを、例に示す厳密な順序で使用するように構成する必要があります。 RSA 証明書だけがある場合は、ECDSA 暗号スイートをインストールしても何の影響もありません。

TLS 1.2「暗号スイート」の最低基準:

- TLS\_ECDHE\_ECDSA\_WITH\_AES\_128\_GCM\_SHA256
- TLS\_ECDHE\_ECDSA\_WITH\_AES\_256\_GCM\_SHA384
- TLS\_ECDHE\_RSA\_WITH\_AES\_128\_GCM\_SHA256
- TLS\_ECDHE\_RSA\_WITH\_AES\_256\_GCM\_SHA384
- TLS\_ECDHE\_ECDSA\_WITH\_AES\_128\_CBC\_SHA256
- TLS\_ECDHE\_ECDSA\_WITH\_AES\_256\_CBC\_SHA384
- TLS\_ECDHE\_RSA\_WITH\_AES\_128\_CBC\_SHA256
- TLS\_ECDHE\_RSA\_WITH\_AES\_256\_CBC\_SHA384

#### IP 範囲

Microsoft Entra プロビジョニング サービスは現在、[here](https://www.microsoft.com/download/details.aspx?id=56519) に記載されているMicrosoft Entra IDの IP 範囲で動作します。 `AzureActiveDirectory` タグの下に一覧表示されている IP 範囲を追加して、Microsoft Entra プロビジョニング サービスからのトラフィックをアプリケーションに許可できます。 計算されたアドレスのために、IP 範囲リストを注意深く確認する必要があります。 '40.126.25.32' などのアドレスは、IP 範囲の一覧では '40.126.0.0/18' として表されることがあります。 次の [API](https://learn.microsoft.com/ja-jp/rest/api/virtualnetwork/service-tags/list) を使用して、IP 範囲の一覧をプログラムで取得することもできます。

Microsoft Entra IDでは、プライベート ネットワーク (オンプレミス、Azureでホスト、AWS でホストなど) 内のアプリケーションへの接続を提供するエージェント ベースのソリューションもサポートされています。 お客様は、プライベート ネットワーク内のサーバーに、受信ポートを開かずにMicrosoft Entra IDへの接続を提供する軽量エージェントをデプロイできます。 詳細については [、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-scim-provisioning)。

### SCIM エンドポイントを構築する

スキーマを設計し、Microsoft Entra SCIM 実装を理解したので、SCIM エンドポイントの開発を開始できます。 最初から始めて実装を完全に独自に構築するのではなく、SCIM コミュニティによって公開された多くのオープンソース SCIM ライブラリに依存できます。

例を含む SCIM エンドポイントを構築する方法のガイダンスについては、「 [サンプル SCIM エンドポイントの開発」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-build-users-and-groups-endpoints)参照してください。

オープンソース .NET Core [reference コードの例](https://aka.ms/SCIMReferenceCode)Microsoft Entra プロビジョニング チームによって発行されたものは、開発をすぐに開始できるリソースの 1 つです。 SCIM エンドポイントを構築し、 提供されたサンプル要求/応答を実行してテストします。

注

参照コードは、SCIM エンドポイントのビルドを開始するためのものであり、"現状のまま" 提供されます。コードのビルドと保守に役立つため、コミュニティからの貢献が歓迎されます。

ソリューションは、*Microsoft の 2 つのプロジェクトで構成されます。SCIM* および *Microsoft。Scim。WebHostSample*。

*Microsoft。SCIM* プロジェクトは、SCIM 仕様に準拠する Web サービスのコンポーネントを定義するライブラリです。 インターフェース *Microsoft.SCIM.IProvider* を宣言し、要求はプロバイダーのメソッドへの呼び出しに変換されます。これは、アイデンティティ・ストアで動作するようにプログラムされます。

[Image: 内訳:プロバイダーのメソッドへの呼び出しに変換された要求]

*Microsoft。Scim。WebHostSample* プロジェクトは、*Empty* テンプレートに基づく ASP.NET Core Web アプリケーションです。 これにより、サンプル コードをスタンドアロンとしてデプロイし、コンテナーまたはインターネット インフォメーション サービス内でホストできます。 また、*Microsoft.SCIM.IProvider* インターフェイスを実装し、クラスをサンプルIDストアとしてメモリ内に保持します。

```csharp
public class Startup
{
    ...
    public IMonitor MonitoringBehavior { get; set; }
    public IProvider ProviderBehavior { get; set; }

    public Startup(IWebHostEnvironment env, IConfiguration configuration)
    {
        ...
        this.MonitoringBehavior = new ConsoleMonitor();
        this.ProviderBehavior = new InMemoryProvider();
    }
    ...
```

#### カスタム SCIM エンドポイントの構築

SCIM エンドポイントには、HTTP アドレスと、ルート証明機関が次のいずれかの名前であるサーバー認証証明書が設定されている必要があります。

- CNNIC
- コモド
- サイバートラスト
- DigiCert
- ジオトラスト
- グローバルサイン
- Go Daddy
- ベリサイン
- WoSign(ウォサイン)
- DST Root CA X3

.NET Core SDK には、開発中に使用される HTTPS 開発証明書が含まれています。 この証明書は、最初の実行エクスペリエンスの一環としてインストールされます。 ASP.NET Core Web アプリケーションの実行方法に応じて、別のポートをリッスンします。

- Microsoft.SCIM.WebHostSample です。 `https://localhost:5001`
- IIS エクスプレス: `https://localhost:44359`

ASP.NET Core の HTTPS についての詳細情報は、次のリンクを使用してください: [ASP.NET Core での HTTPS の強制](https://learn.microsoft.com/ja-jp/aspnet/core/security/enforcing-ssl)

#### エンドポイント認証の処理

Microsoft Entraプロビジョニング サービスからの要求には、OAuth 2.0 ベアラー トークンが含まれます。 承認サーバーがベアラー トークンを発行します。 Microsoft Entra IDは、信頼された承認サーバーの例です。 次のいずれかのトークンを使用するように Microsoft Entra プロビジョニング サービスを構成します。

- 有効期間が長いベアラー トークン SCIM エンドポイントで、Microsoft Entra ID以外の発行者からの OAuth ベアラー トークンが必要な場合は、必要な OAuth ベアラー トークンを省略可能な **Secret Token** フィールドにコピーします。 開発環境では、`/scim/token` エンドポイントからのテスト トークンを使用できます。 運用環境ではテスト トークンを使用しないでください。
- Microsoft Entra ベアラー トークン **Secret Token** フィールドが空白の場合、Microsoft Entra IDには、各要求でMicrosoft Entra IDから発行された OAuth ベアラー トークンが含まれます。 Microsoft Entra ID を ID プロバイダーとして使用するアプリは、Microsoft Entra ID によって発行されたトークンを検証できます。

    - 要求を受け取るアプリケーションは、予想されるMicrosoft Entraテナントに対応するトークン発行者がMicrosoft Entra IDであることを検証する必要があります。
    - トークンの発行者は、`iss` 要求によって識別されます。 たとえば、`"iss":"https://sts.windows.net/aaaabbbb-0000-cccc-1111-dddd2222eeee/"` のようにします。 この例では、要求値のベース アドレスである `https://sts.windows.net` は発行者としてMicrosoft Entra IDを識別しますが、相対アドレス セグメントである *aaaabbbb-0000-cccc-1111-dddd2222eeee* は、トークンが発行されたMicrosoft Entra テナントの一意の識別子です。
    - トークンの対象ユーザーは、ギャラリー内のアプリケーションのアプリケーション **ID です** 。 単一のテナントに登録されているアプリケーションは、同じ `iss` 要求を SCIM 要求と共に受信します。 すべてのカスタム アプリのアプリケーション ID は *8adf8e6e-67b2-4cf2-a259-e3dc5476c621* です。 Microsoft Entra IDによって生成されるトークンは、テストにのみ使用する必要があります。 運用環境では使用しないでください。

サンプルコードでは、リクエストはMicrosoft.AspNetCore.Authentication.JwtBearerパッケージを使用して認証されます。 次のコードでは、指定したテナントに対してMicrosoft Entra IDによって発行されたベアラー トークンを使用して、サービスのエンドポイントへの要求が認証されるように強制します。

```csharp
public void ConfigureServices(IServiceCollection services)
{
    if (_env.IsDevelopment())
    {
        ...
    }
    else
    {
        services.AddAuthentication(options =>
        {
            options.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
            options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
        })
            .AddJwtBearer(options =>
            {
                options.Authority = " https://sts.windows.net/aaaabbbb-0000-cccc-1111-dddd2222eeee/";
                options.Audience = "8adf8e6e-67b2-4cf2-a259-e3dc5476c621";
                ...
            });
    }
    ...
}

public void Configure(IApplicationBuilder app)
{
    ...
    app.UseAuthentication();
    app.UseAuthorization();
    ...
}
```

このサンプル コードでは、ASP.NET Core環境を使用して、開発段階で認証オプションを変更し、自己署名トークンの使用を有効にします。

ASP.NET Core内の複数の環境の詳細については、「 ASP.NET Coreを参照してください。

次のコードでは、あらゆるサービスのエンドポイントに対する要求が、カスタム キーを使用して署名されたベアラー トークンを使用して認証されるようになります:

```csharp
public void ConfigureServices(IServiceCollection services)
{
    if (_env.IsDevelopment())
    {
        services.AddAuthentication(options =>
        {
            options.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
            options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
        })
        .AddJwtBearer(options =>
        {
            options.TokenValidationParameters =
                new TokenValidationParameters
                {
                    ValidateIssuer = false,
                    ValidateAudience = false,
                    ValidateLifetime = false,
                    ValidateIssuerSigningKey = false,
                    ValidIssuer = "Microsoft.Security.Bearer",
                    ValidAudience = "Microsoft.Security.Bearer",
                    IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes("A1B2C3D4E5F6A1B2C3D4E5F6"))
                };
        });
    }
...
```

トークン コントローラーに GET 要求を送信して有効なベアラー トークンを取得します。 *GenerateJSONWebToken* メソッドは、開発用に構成されたパラメーターと一致するトークンを作成します。

```csharp
private string GenerateJSONWebToken()
{
    // Create token key
    SymmetricSecurityKey securityKey =
        new SymmetricSecurityKey(Encoding.UTF8.GetBytes("A1B2C3D4E5F6A1B2C3D4E5F6"));
    SigningCredentials credentials =
        new SigningCredentials(securityKey, SecurityAlgorithms.HmacSha256);

    // Set token expiration
    DateTime startTime = DateTime.UtcNow;
    DateTime expiryTime = startTime.AddMinutes(120);

    // Generate the token
    JwtSecurityToken token =
        new JwtSecurityToken(
            "Microsoft.Security.Bearer",
            "Microsoft.Security.Bearer",
            null,
            notBefore: startTime,
            expires: expiryTime,
            signingCredentials: credentials);

    string result = new JwtSecurityTokenHandler().WriteToken(token);
    return result;
}
```

#### ユーザーのプロビジョニングとプロビジョニング解除の処理

***例 1.一致するユーザーのサービスに対してクエリを実行する***

Microsoft Entra ID、Microsoft Entra IDのユーザーの mailNickname 属性値と一致する `externalId` 属性値を持つユーザーに対してサービスに対してクエリを実行します。 このクエリは、この例のようなハイパーテキスト転送プロトコル (HTTP) 要求として表されます。ここで、jyoung は、Microsoft Entra IDのユーザーの mailNickname のサンプルです。

注

これは一例です。 すべてのユーザーに mailNickname 属性があるわけではありません。また、ユーザーが持つ値はディレクトリ内で一意ではない場合もあります。 また、照合に使用される属性 (この場合は `externalId`) は、[Microsoft Entra 属性マッピング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)で構成できます。

```
GET https://.../scim/Users?filter=externalId eq jyoung HTTP/1.1
 Authorization: Bearer ...
```

このサンプル コードでは、要求はサービスのプロバイダーの QueryAsync メソッドへの呼び出しに変換されます。 このメソッドのシグネチャを次に示します。

```csharp
// System.Threading.Tasks.Tasks is defined in mscorlib.dll.  
// Microsoft.SCIM.IRequest is defined in 
// Microsoft.SCIM.Service.  
// Microsoft.SCIM.Resource is defined in 
// Microsoft.SCIM.Schemas.  
// Microsoft.SCIM.IQueryParameters is defined in 
// Microsoft.SCIM.Protocol.  

Task<Resource[]> QueryAsync(IRequest<IQueryParameters> request);
```

`externalId` 属性に特定の値を持つユーザーのサンプル クエリでは、QueryAsync メソッドに渡される引数の値は次のようになります。

- parameters.AlternateFilters.Count:1
- parameters.AlternateFilters.ElementAt(0).AttributePath: "externalId"
- parameters.AlternateFilters.ElementAt(0).ComparisonOperator:ComparisonOperator.Equals
- parameters.AlternateFilter.ElementAt(0).ComparisonValue: "jyoung"

***例 2.ユーザーをプロビジョニングする***

ユーザーの mailNickname 属性値に一致する `externalId` 属性値を持つユーザーの SCIM エンドポイントに対するクエリに対する応答でユーザーが返されない場合、Microsoft Entra IDは、Microsoft Entra IDのユーザーに対応するユーザーをサービスがプロビジョニングするように要求します。 このような要求の例を次に示します。

```http
POST https://.../scim/Users HTTP/1.1
Authorization: Bearer ...
Content-type: application/scim+json
{
   "schemas":
   [
     "urn:ietf:params:scim:schemas:core:2.0:User",
     "urn:ietf:params:scim:schemas:extension:enterprise:2.0User"],
   "externalId":"jyoung",
   "userName":"jyoung@testuser.com",
   "active":true,
   "addresses":null,
   "displayName":"Joy Young",
   "emails": [
     {
       "type":"work",
       "value":"jyoung@Contoso.com",
       "primary":true}],
   "meta": {
     "resourceType":"User"},
    "name":{
     "familyName":"Young",
     "givenName":"Joy"},
   "phoneNumbers":null,
   "preferredLanguage":null,
   "title":null,
   "department":null,
   "manager":null}
```

このサンプル コードでは、要求はサービスのプロバイダーの CreateAsync メソッドへの呼び出しに変換されます。 このメソッドのシグネチャを次に示します。

```csharp
// System.Threading.Tasks.Tasks is defined in mscorlib.dll.  
// Microsoft.SCIM.IRequest is defined in 
// Microsoft.SCIM.Service.  
// Microsoft.SCIM.Resource is defined in 
// Microsoft.SCIM.Schemas.  

Task<Resource> CreateAsync(IRequest<Resource> request);
```

ユーザー プロビジョニングの要求では、リソース引数の値は `Microsoft.SCIM.Core2EnterpriseUser` クラスのインスタンスです。 このクラスは、`Microsoft.SCIM.Schemas` ライブラリで定義されています。 ユーザーのプロビジョニング要求が成功した場合、メソッドの実装は、`Microsoft.SCIM.Core2EnterpriseUser` クラスのインスタンスを返す必要があります。 `Identifier` プロパティの値に新たにプロビジョニングされたユーザーの一意識別子が設定されます。

***例 3.ユーザーの現在の状態を照会する***

Microsoft Entra IDは、次のような要求を使用して、指定されたユーザーの現在の状態をサービスに要求します。

```
GET ~/scim/Users/a0a0a0a0-bbbb-cccc-dddd-e1e1e1e1e1e1 HTTP/1.1
Authorization: Bearer ...
```

このサンプル コードでは、要求はサービスのプロバイダーの RetrieveAsync メソッドへの呼び出しに変換されます。 このメソッドのシグネチャを次に示します。

```csharp
// System.Threading.Tasks.Tasks is defined in mscorlib.dll.  
// Microsoft.SCIM.IRequest is defined in 
// Microsoft.SCIM.Service.  
// Microsoft.SCIM.Resource and 
// Microsoft.SCIM.IResourceRetrievalParameters 
// are defined in Microsoft.SCIM.Schemas 

Task<Resource> RetrieveAsync(IRequest<IResourceRetrievalParameters> request);
```

ユーザーの現在の状態を取得する要求の例では、parameters 引数の値として指定されたオブジェクトのプロパティ値は次のようになります。

- 識別子: "a0a0a0a0-bbbb-cccc-dddd-e1e1e1e1e1e1"
- スキーマ識別子: `urn:ietf:params:scim:schemas:extension:enterprise:2.0:User`

***例 4.更新する参照属性の値を照会する***

Microsoft Entra ID更新する前に、ID ストア内の現在の属性値を確認します。 ただし、ユーザーに対して最初に確認されるのはマネージャー属性のみです。 ユーザー オブジェクトの マネージャー 属性に特定の値があるかどうかを判別する要求の例を次に示します。このサンプル コードでは、要求はサービスのプロバイダーの QueryAsync メソッドへの呼び出しに変換されます。 parameters 引数の値として指定されたオブジェクトのプロパティ値は次のようになります。

- parameters.AlternateFilters.Count:2
- parameters.AlternateFilters.ElementAt(x).AttributePath:"ID"
- parameters.AlternateFilters.ElementAt(x).ComparisonOperator:ComparisonOperator.Equals
- parameters.AlternateFilter.ElementAt(x).ComparisonValue: "a0a0a0a0-bbbb-cccc-dddd-e1e1e1e1e1e1"
- parameters.AlternateFilters.ElementAt(y).AttributePath: "manager"
- parameters.AlternateFilters.ElementAt(y).ComparisonOperator:ComparisonOperator.Equals
- parameters.AlternateFilter.ElementAt(y).ComparisonValue: "00aa00aa-bb11-cc22-dd33-44ee44ee44ee"
- parameters.RequestedAttributePaths.ElementAt(0):"ID"
- parameters.SchemaIdentifier: `urn:ietf:params:scim:schemas:extension:enterprise:2.0:User`

インデックス x の値として `0` を指定し、インデックス y の値として `1` を指定することができます。 または、x の値をとして `1` を指定し、y の値として `0` を指定することもできます。 これはフィルター クエリ パラメーターの式の順序によって異なります。

***例 5: Microsoft Entra ID から SCIM エンドポイントに対するユーザー更新のリクエスト***

ユーザーを更新するために、Microsoft Entra IDから SCIM エンドポイントへの要求の例を次に示します。

```http
PATCH ~/scim/Users/a0a0a0a0-bbbb-cccc-dddd-e1e1e1e1e1e1 HTTP/1.1
Authorization: Bearer ...
Content-type: application/scim+json
{
    "schemas": 
    [
      "urn:ietf:params:scim:api:messages:2.0:PatchOp"],
    "Operations":
    [
      {
        "op":"Add",
        "path":"manager",
        "value":
          [
            {
              "$ref":"http://.../scim/Users/00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
              "value":"00aa00aa-bb11-cc22-dd33-44ee44ee44ee"}]}]}
```

このサンプル コードでは、要求はサービスのプロバイダーの UpdateAsync メソッドへの呼び出しに変換されます。 このメソッドのシグネチャを次に示します。

```csharp
// System.Threading.Tasks.Tasks and 
// System.Collections.Generic.IReadOnlyCollection<T>  // are defined in mscorlib.dll.  
// Microsoft.SCIM.IRequest is defined in
// Microsoft.SCIM.Service.
// Microsoft.SCIM.IPatch, 
// is defined in Microsoft.SCIM.Protocol. 

Task UpdateAsync(IRequest<IPatch> request);
```

ユーザーを更新する要求の例では、patch 引数の値として指定されたオブジェクトのプロパティ値は次のようになります。

| 引数 | 価値 |
| --- | --- |
| `ResourceIdentifier.Identifier` | "a0a0a0a0-bbbb-cccc-dddd-e1e1e1e1e1e1" |
| `ResourceIdentifier.SchemaIdentifier` | `urn:ietf:params:scim:schemas:extension:enterprise:2.0:User` |
| `(PatchRequest as PatchRequest2).Operations.Count` | 1 |
| `(PatchRequest as PatchRequest2).Operations.ElementAt(0).OperationName` | `OperationName.Add` |
| `(PatchRequest as PatchRequest2).Operations.ElementAt(0).Path.AttributePath` | マネージャー |
| `(PatchRequest as PatchRequest2).Operations.ElementAt(0).Value.Count` | 1 |
| `(PatchRequest as PatchRequest2).Operations.ElementAt(0).Value.ElementAt(0).Reference` | `http://.../scim/Users/00aa00aa-bb11-cc22-dd33-44ee44ee44ee` |
| `(PatchRequest as PatchRequest2).Operations.ElementAt(0).Value.ElementAt(0).Value` | 00aa00aa-bb11-cc22-dd33-44ee44ee44ee |

***例 6.ユーザーのプロビジョニング解除***

SCIM エンドポイントによってフロントされた ID ストアからユーザーをプロビジョニング解除するには、Microsoft Entra ID が次のような要求を送信します:

```http
DELETE ~/scim/Users/a0a0a0a0-bbbb-cccc-dddd-e1e1e1e1e1e1 HTTP/1.1
Authorization: Bearer ...
```

このサンプル コードでは、要求はサービスのプロバイダーの DeleteAsync メソッドへの呼び出しに変換されます。 このメソッドのシグネチャを次に示します。

```csharp
// System.Threading.Tasks.Tasks is defined in mscorlib.dll.  
// Microsoft.SCIM.IRequest is defined in 
// Microsoft.SCIM.Service.  
// Microsoft.SCIM.IResourceIdentifier, 
// is defined in Microsoft.SCIM.Protocol. 

Task DeleteAsync(IRequest<IResourceIdentifier> request);
```

ユーザーのプロビジョニングを解除する要求の例では、resourceIdentifier 引数の値として指定されたオブジェクトのプロパティ値は次のようになります。

- ResourceIdentifier.Identifier: "a0a0a0a0-bbbb-cccc-dddd-e1e1e1e1e1e1"
- ResourceIdentifier.SchemaIdentifier: `urn:ietf:params:scim:schemas:extension:enterprise:2.0:User`

### SCIM エンドポイントを Microsoft Entra プロビジョニング サービスと統合する

Microsoft Entra IDは、割り当てられたユーザーとグループを、[SCIM 2.0 プロトコル](https://tools.ietf.org/html/rfc7644)の特定のプロファイルを実装するアプリケーションに自動的にプロビジョニングするように構成できます。 プロファイルの詳細については、Microsoft Entra SCIM 実装に関する記事を参照してください。

これらの要件との互換性に関する記述については、アプリケーション プロバイダー、またはアプリケーション プロバイダーのドキュメントを確認してください。

重要

Microsoft Entra SCIM 実装は、Microsoft Entra IDとターゲット アプリケーションの間でユーザーの同期を絶えず維持するように設計された、Microsoft Entra ユーザー プロビジョニング サービスの上に構築され、非常に具体的な標準操作のセットを実装します。 これらの動作を理解して、Microsoft Entra プロビジョニング サービスの動作を理解することが重要です。 詳細については、「 [プロビジョニングサイクル: プロビジョニングのしくみ」の「初期および増分](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works#provisioning-cycles-initial-and-incremental) 」セクション [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works)参照してください。

#### 作業の開始

この記事で説明する SCIM プロファイルをサポートするアプリケーションは、Microsoft Entra アプリケーション ギャラリーの "ギャラリー以外のアプリケーション" 機能を使用して、Microsoft Entra IDに接続できます。 接続されると、Microsoft Entra IDは同期プロセスを実行します。 このプロセスは 40 分ごとに実行されます。 このプロセスでは、割り当て済みのユーザーとグループをアプリケーションの SCIM エンドポイントに照会し、割り当ての詳細に従ってユーザーとグループを作成または変更します。

**SCIM をサポートするアプリケーションを接続するには:**

1. 少なくとも [Microsoft Entra 管理センター](https://entra.microsoft.com)[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**に移動します。
3. ギャラリーから追加されたアプリを含む、構成済みのすべてのアプリの一覧が表示されます。
4. [ **+ 新しいアプリケーション**&gt;**+ 独自のアプリケーションを作成する**] を選択します。
5. アプリケーションの名前を入力し、[*ギャラリーに見つからない他のアプリケーションを統合*する] オプションを選択し、[ **追加]** を選択してアプリ オブジェクトを作成します。 新しいアプリがエンタープライズ アプリケーションの一覧に追加され、そのアプリの管理画面が開きます。

    次のスクリーンショットは、Microsoft Entra アプリケーション ギャラリーを示しています。

    [Image: Microsoft Entra アプリケーション ギャラリーが画面に表示されます。]
6. アプリの管理画面で、左側のパネルで [ **プロビジョニング** ] を選択します。
7. [ **+ 新しい構成**] を選択します。
8. SCIM エンドポイントで、Microsoft Entra ID以外の発行者からの oauth2ClientCredentialsGrant が必要な場合は、次の手順を実行します。

    [Image: Oauth2 の新しい構成を示すスクリーンショット。]

    1. [ **テナント URL** ] フィールドに、アプリケーションの SCIM エンドポイントの URL を入力します。 例: `https://api.contoso.com/scim/`。
    2. **OAuth トークン エンドポイントで、トークン エンドポイント**の値を入力します。
    3. アプリケーションから取得した **クライアント ID** と **クライアント シークレット** を入力します。
    4. [ **資格情報の送信方法**] で、トークン要求でクライアント識別子とクライアント シークレットを送信する場所を選択します。 ヘッダーは、それらを HTTP Basic認証ヘッダー（client\_secret\_basic）として送信します。 Body は、要求本文 (client\_secret\_post) でフォーム パラメーターとして送信します。 両方とも、要求のヘッダーと本文の両方で送信されます。
    5. アクセス トークンを取得するときにトークン要求に含める **OAuth 2.0 スコープ** を指定します。
9. SCIM エンドポイントへの接続を試みるには、**Test Connection** を選択して、Microsoft Entra ID を使用します。 試行に失敗した場合は、エラー情報が表示されます。

    注

    **Test Connection** は、Microsoft Entra構成で選択された一致するプロパティとしてランダムな GUID を使用して、存在しないユーザーの SCIM エンドポイントに対してクエリを実行します。 想定される適切な応答は、HTTP 200 OK と空の SCIM ListResponse メッセージです。
10. アプリケーションへの接続が成功した場合は、[ **作成** ] を選択してプロビジョニング ジョブを作成します。
11. 割り当てられたユーザーとグループのみを同期する場合 (推奨)、[ **ユーザーとグループ** ] タブを選択します。次に、同期するユーザーまたはグループを割り当てます。この手順は、割り当てられたユーザーとグループのみを同期するようにスコープが設定されている場合に、プロビジョニングを機能させるために必要です。 ユーザーまたはグループが割り当てられていない場合は、プロビジョニングする必要はありません。 ユーザーとグループを割り当てる方法の詳細な手順については、「 [アプリケーションにユーザーとグループを割り当てる」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)参照してください。

    重要

    Microsoft Entra プロビジョニング サービスは、アプリケーションに割り当てユーザーとグループのみをプロビジョニングします (スコープが 割り当てられたユーザーとグループのみを同期)。 ユーザーまたはグループを割り当てない場合、プロビジョニングは行われません。 プロビジョニングを開始する前に、必ずこの手順を完了してください。
12. 左側のパネルで **[属性マッピング** ] を選択します。 属性マッピングには、ユーザー オブジェクト用とグループ オブジェクト用の 2 つの選択可能なセットがあります。 それぞれを選択して、Microsoft Entra IDからアプリに同期される属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のためにアプリ内のユーザーとグループを照合するために使用されます。 [ **保存] を** 選択して変更をコミットします。

>
> 必要に応じて [グループ] マッピングを無効にすることで、グループ オブジェクトの同期を無効にできます。
13. 左側のパネルで、**[オンデマンドでプロビジョニング]** を選択します。 プロビジョニングのスコープ内にあるユーザーを検索し、オンデマンドでプロビジョニングします。 他のユーザーとプロビジョニングのテストを繰り返します。
14. 構成が完了したら、左側のパネルで **[概要** ] を選択します。
15. **[プロパティ] を選択します**。
16. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 [ **適用** ] をクリックして変更を保存します。
17. **プロビジョニングの開始** を選択して、Microsoft Entra プロビジョニング サービスを開始します。

最初のサイクルが開始されたら、左側のパネルで **[プロビジョニング ログ** ] を選択して進行状況を監視し、アプリでプロビジョニング サービスによって実行されたすべてのアクションが表示されます。 Microsoft Entra プロビジョニング ログを読み取る方法の詳細については、「[自動ユーザー アカウント プロビジョニングに関するレポート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)を参照してください。

注

初期サイクルは、サービスが実行されている限り約 40 分ごとに実行される以降の同期よりも、実行に時間がかかります。

### Microsoft Entra アプリケーション ギャラリーにアプリケーションを発行する

複数のテナントで使用されるアプリケーションを構築している場合は、Microsoft Entra アプリケーション ギャラリーでアプリケーションを使用できるようにして、組織がそれを検出してプロビジョニングを構成できるようにします。

発行はセルフサービス プロセスです。 Microsoft Entra プロビジョニング サービスに対して独自の SCIM 統合を検証し、ギャラリーの申請で結果を送信します。 開始するには、「 [アプリを検証して発行するための前提条件」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing)。

#### ギャラリーの利用開始要件

SCIM エンドポイントは、送信する前に一連の API、認証、および発行元の要件を満たしている必要があります。 現在の一覧については、「[Microsoft Entra アプリ ギャラリーのユーザー プロビジョニング要件](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/app-gallery-user-provisioning-requirements)」を参照してください。

エンドポイントがこれらの要件を満たしたら、それを検証し、結果を送信します。 手順については、「[Microsoft Entra アプリ ギャラリーのユーザー プロビジョニングの検証](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/validate-user-provisioning-app-gallery)」を参照してください。

このセクションの残りの部分では、これらの要件を満たすためにエンドポイントに実装する認証方法について説明します。

#### アプリケーション ギャラリーでプロビジョニング コネクタを認可する

SCIM 仕様では、SCIM 固有の認証と認可のスキームは定義されておらず、既存の業界標準の使用に依存しています。

| 承認方法 | 長所 | 短所 | サポート |
| --- | --- | --- | --- |
| ユーザー名とパスワード (Microsoft Entra IDでは推奨またはサポートされていません) | 簡単に実装できます | 不安定 - [あなたのパスワードに意味はありません](https://techcommunity.microsoft.com/t5/microsoft-entra-azure-ad-blog/your-pa-word-doesn-t-matter/ba-p/731984) | 新しいギャラリー アプリまたはギャラリー外のアプリではサポートされません。 |
| 有効期間が長いベアラー トークン | 有効期間が長いトークンでは、ユーザーが存在している必要はありません。 プロビジョニングを設定する場合、管理者はこれらを簡単に使用できます。 | 有効期間が長いトークンは、メールのようにセキュリティで保護されていない方法を使用しないと、管理者と共有するのが困難です。 | 既存のアプリとギャラリー以外のアプリでサポートされます。 新しいアプリではサポートされていません。 |
| OAuth 2.0 クライアント資格情報の付与 | アクセス トークンの有効期間はパスワードよりも短く、有効期間が長いベアラー トークンにはない自動更新メカニズムがあります。 プロビジョニングは自動化することができ、ユーザーの介入なしに新しいトークンを自動的に要求することができます。 パブリッシャーは、アプリの種類に関係なく、クライアント資格情報を使用して SCIM ジョブを構成できます。 各顧客は、SCIM API に対して認証するために、独自のクライアント ID とクライアント シークレットを指定する必要があります。 |  | ギャラリー アプリとギャラリー以外のアプリでサポートされます。 |
| ワークロード ID フェデレーション | プロビジョニング構成にはシークレットは格納されません。 Microsoft Entra IDは、有効期間の短い署名付き JWT アサーション (OAuth 2.0 JWT ベアラー プロファイル [RFC 7523](https://datatracker.ietf.org/doc/html/rfc7523)) を提示して認証を行います。これにより、シークレットのストレージとローテーションの負担が軽減され、資格情報漏えいのリスクが軽減されます。 | ISV は、Entra によって発行された JWT の JWKS ベースの検証と、SCIM エンドポイントのアクセス トークンを発行するトークン エンドポイントをサポートする必要があります。 | ギャラリー アプリとギャラリー以外のアプリでサポートされます。 |

注

OAuth 承認コードの付与は廃止されます。 新しいコネクタでは OAuth 承認コードの付与がサポートされていないため、既存のコネクタでは OAuth 2.0 クライアント資格情報の付与を使用する必要があります。

注

Microsoft Entra プロビジョニング構成のカスタム アプリ UI では、トークン フィールドを空白のままにすることはお勧めしません。 生成されたトークンは、主にテスト目的で使用できます。

その他の認証方法と承認方法については、 [UserVoice](https://aka.ms/appprovisioningfeaturerequest) でお知らせください。

#### OAuth 2.0 クライアント資格情報の付与フロー

プロビジョニング サービスでは、 [OAuth 2.0 クライアント資格情報の付与がサポートされています](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)。 お客様は、プロビジョニングを構成するときに次の情報を提供するため、管理者エクスペリエンスで各値を使用できるようにします。

- **テナント URL:** これは、サービス プロバイダーによって提供される SCIM API エンドポイント URL です。 たとえば、 `https://example.test/scim`のようなものです。
- **トークン エンドポイント:** これは OAuth2 トークン URL です。 これは、プロビジョニング サービスがクライアント資格情報をアクセス トークンと交換するために使用するエンドポイントです。 たとえば、 `https://example.test/oauth2/token`のようなものです。
- **クライアント識別子:** これは、サービス プロバイダーによってアプリケーションに割り当てられた一意の識別子です。
- **クライアント シークレット:** これは、API に必要なアクセス許可を持つ有効期間の長いトークンです。 これは、アクセス トークンを要求するときにアプリケーションを認証するために使用されます。

OAuth 2.0 クライアント資格情報付与フローを使用する場合は、プロビジョニング インスタンスを設定するときに各顧客が独自のクライアント ID とクライアント シークレットを送信するモデルをサポートする必要があります。 1 つのアプリ全体のクライアント ID とクライアント シークレットのペアはサポートされていません。

##### クライアント資格情報を設定する方法

1. 少なくとも [Microsoft Entra 管理センター](https://entra.microsoft.com)[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**に移動します。
3. アプリケーションを選択し、[ **プロビジョニング**] に移動します。
4. **[管理者資格情報&gt;認証方法**] で、[**OAuth2 クライアント資格情報の付与**] を選択します。
5. 残りのフィールドの情報を入力します。
    - **テナント URL**
    - **トークン エンドポイント**
    - **クライアント識別子**
    - **クライアント シークレット**。
6. **[接続テスト]**をクリックします。

その他の認証方法と承認方法については、 [UserVoice](https://aka.ms/appprovisioningfeaturerequest) でお知らせください。

#### ワークロード ID フェデレーション

プロビジョニング サービスは、ワークロード ID フェデレーション (WIF) をサポートしています。 WIF では、有効期間が長いシークレットはプロビジョニング構成に格納されません。 代わりに、Microsoft Entra IDは、OAuth 2.0 JWT ベアラー プロファイル ([RFC 7523](https://datatracker.ietf.org/doc/html/rfc7523)) を使用してトークン エンドポイントに有効期間の短い署名付き JWT アサーションを提示し、SCIM エンドポイントの呼び出しに使用されるアクセス トークンを受け取ります。 信頼関係のしくみの詳細については、 [SCIM プロビジョニング のワークロード ID フェデレーション ガイドを](https://github.com/AzureAD/SCIMReferenceCode/blob/master/Workload-Identity-Federation-for-SCIM-Provisioning.md)参照してください。 WIF をサポートするには、サービスがMicrosoft Entra IDによって発行されたトークンを信頼し、Microsoft発行された JWKS に対して検証する必要があります。

WIF を構成すると、トークン エンドポイントがアサーションを検証できるように、Microsoft Entra IDは次の値を (ISV の) ポータルにコピーします。

- **発行者 (iss):** 顧客テナント v2.0 の発行者。たとえば `https://login.microsoftonline.com/<TenantID>/v2.0`。
- **JWKS URL:** アサーションの検証に使用される公開署名鍵（たとえば `https://login.microsoftonline.com/<TenantID>/discovery/v2.0/keys`）。
- **サブジェクト (sub):** Sync Fabric ワークロード ID のファースト パーティ アプリ オブジェクト ID。
- **オーディエンス (aud):** ワークロード ID アプリケーション ID URI。たとえば `api://{WorkloadIdentity_appid}/.default`。

次に、管理者がMicrosoft Entra IDの接続ページにコピーする次の値を指定します。

- **クライアント識別子:** お客様側におけるこの連携固有の一意の識別子です。
- **トークン エンドポイント:** Entra によって発行されたアサーションを SCIM エンドポイントへのアクセス トークンと交換するためにプロビジョニング サービスが使用するエンドポイント。
- **SCIM URL:** ユーザーとグループのプロビジョニングに使用されるエンドポイント。

ワークロード ID フェデレーションを使用する場合は、プロビジョニング インスタンスを設定するときに、各顧客が独自の信頼関係 (ワークロード ID) を確立する必要があります。 アプリ全体で共通の資格情報はサポートされていません。

##### ワークロード ID フェデレーションを設定する方法

1. 少なくとも [Microsoft Entra 管理センター](https://entra.microsoft.com)[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**に移動します。
3. アプリケーションを選択し、[ **プロビジョニング**] に移動します。
4. **[管理者資格情報&gt;認証方法**] で、[**ワークロード ID フェデレーション] を**選択します。
5. [ **ワークロード ID の選択] を選択**し、新しいワークロード ID を登録するか、ISV で既に構成されている既存のワークロード ID を再利用します。
6. 残りのフィールドの情報を入力します。
    - **クライアント識別子**
    - **トークン エンドポイント**
    - **SCIM URL**
7. **[接続テスト]**をクリックします。

完全な WIF 認証フロー、トークン要求、ISV 実装要件については、 [SCIM プロビジョニング のワークロード ID フェデレーション ガイドを](https://github.com/AzureAD/SCIMReferenceCode/blob/master/Workload-Identity-Federation-for-SCIM-Provisioning.md)参照してください。

#### ギャラリーの市場投入開始チェックリスト

共同統合の認識と需要を促進するために、既存のドキュメントを更新し、お使いのマーケティング チャネルにおける統合を拡充することをお勧めします。 Launch をサポートするために、次のチェックリストを完了することをお勧めします。

- 販売チームとカスタマー サポート チームが統合の機能を認識し、準備し、それについて説明できるようにします。 チームに概要を伝え、FAQ を提供し、統合を販売資料に含めます。
- 共同統合、利点、および開始方法について説明するブログ投稿やプレス リリースを作成します。 [例: Imprivata および Microsoft Entra プレスリリース](https://www.imprivata.com/company/press/imprivata-introduces-iam-cloud-platform-healthcare-supported-microsoft)
- X、Facebook、LinkedInなどのソーシャル メディアを活用して、顧客への統合を促進します。 投稿をリツイートできるように、必ず @Microsoft Entra ID を含めるようにしてください。 [例: Imprivata X Post](https://x.com/azuread/status/1123964502909779968)
- マーケティングのページや Web サイト (統合ページ、パートナーのページ、価格ページなど) を作成または更新し、共同統合を利用できる時期を含めてください。 [例: Pingboard 統合ページ](https://pingboard.com/org-chart-for)、 [Smartsheet 統合ページ](https://www.smartsheet.com/marketplace/apps/directory-integrations)、 [Monday.com 価格ページ](https://monday.com/pricing/)
- 顧客がどのように開始できるかに関するヘルプ センターの記事またはテクニカル ドキュメントを作成します。 [例: Envoy + Microsoft Entra 統合。](https://envoy.help/en/articles/3453335-microsoft-azure-active-directory-integration)
- 顧客とのコミュニケーション (月次のニュースレター、メールによるキャンペーン、製品のリリース ノート) を通じて、新しい統合を顧客に通知します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/user-provisioning"} -->
## Microsoft Entra IDでの自動アプリ ユーザー プロビジョニングとは - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning
- Service: entra-id / app-provisioning
- Article date: 2026-04-01
- Summary: Microsoft Entra IDを使用して、複数のサード パーティ製アプリケーション間でユーザー アカウントを自動的にプロビジョニング、プロビジョニング解除、および継続的に更新する方法の概要について説明します。

Microsoft Entra IDでは、*app provisioning* という用語は、アプリケーションのユーザー ID とロールを自動的に作成することを指します。

[Image: プロビジョニング シナリオを示す図。]

Microsoft Entraアプリケーション プロビジョニングとは、ユーザーがアクセスする必要があるアプリケーションでユーザー ID とロールを自動的に作成することを指します。 自動プロビジョニングには、ユーザー ID の作成に加えて、状態または役割が変化したときのユーザー ID のメンテナンスおよび削除が含まれます。 一般的なシナリオとしては、[Dropbox](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dropboxforbusiness-provisioning-tutorial)、[Salesforce](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/salesforce-provisioning-tutorial)、[ServiceNow](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/servicenow-provisioning-tutorial) などの SaaS アプリケーションへのMicrosoft Entra ユーザーのプロビジョニングなどがあります。

Microsoft Entra IDでは、ファイアウォールを開かなくても、オンプレミスまたは仮想マシンでホストされているアプリケーションへのユーザーのプロビジョニングもサポートされます。 下の表は、サポートされているコネクタへのプロトコルのマッピングを示しています。

| プロトコル | コネクタ |
| --- | --- |
| SCIM | [SCIM - SaaS](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)[SCIM - オンプレミス/プライベート ネットワークの場合](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-scim-provisioning) |
| LDAP | [LDAP](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure) |
| SQL | [SQL](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/tutorial-ecma-sql-connector) |
| REST | [Web サービス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-web-services-connector) |
| SOAP | [Web サービス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-web-services-connector) |
| フラット ファイル | [PowerShell](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-powershell-connector) |
| カスタム | [カスタム ECMA コネクタ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-custom-connector)[パートナーによって構築されたコネクタとゲートウェイ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/partner-driven-integrations) |

- **プロビジョニングを自動化する:** 新しいユーザーがチームまたは組織に加わるときに、適切なシステムで新しいアカウントを自動的に作成できます。
- **プロビジョニング解除を自動化する**: ユーザーがチームまたは組織を離れるときに、適切なシステムでアカウントを自動的に非アクティブ化できます。
- **システム間でデータを同期する**: ディレクトリまたは人事システムでの変更に基づいて、アプリとシステムの ID を最新の状態に維持します。
- **アカウントの検出:**ローカル アカウントや孤立したアカウントなど、アプリケーション内の[既存](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-to-account-discovery)のユーザーを検出します。
- **グループをプロビジョニングする**: グループをサポートするアプリケーションにグループをプロビジョニングします。
- **アクセスの管理**: アプリケーションでプロビジョニングされたユーザーを監視および監査します。
- **ブラウン フィールドのシナリオでシームレスにデプロイする**: ターゲット システムにユーザーが既に存在する場合でも、システム間で既存の ID を一致させ、簡単に統合できるようにします。
- **リッチなカスタマイズを使用する**: ソース システムからターゲット システムに送られる必要があるユーザー データが定義された、カスタマイズ可能な属性マッピングを利用します。
- **重要なイベントのアラートを取得する**: プロビジョニング サービスは、重要なイベントのアラートを提供し、ビジネス ニーズに合わせてカスタム アラートを定義できるLog Analytics統合を可能にします。

### SCIM とは

プロビジョニングとプロビジョニング解除の自動化を助けるため、アプリでは独自のユーザー API とグループ API を公開します。 すべてのアプリが同じアクションを実行しようとするため、複数のアプリでのユーザー管理は困難です。 たとえば、ユーザーの作成または更新、グループへのユーザーの追加、ユーザーのプロビジョニング解除などです。 多くの場合、開発者がこれらのアクションを実装する方法は若干異なります。 使用するエンドポイント パスが異なったり、ユーザー情報を指定する方法が異なったり、情報の各要素を表すスキーマが異なったりします。

これらの課題に対処するため、クロスドメイン ID 管理システム (SCIM) 仕様では、アプリへの、アプリからの、およびアプリ内での移動に対し、共通のユーザー スキーマが提供されています。 SCIM は、プロビジョニングに対する事実上の標準になりつつあり、SAML (Security Assertions Markup Language) や OpenID Connect (OIDC) などのフェデレーション標準と一緒に使用すると、エンドツーエンドの標準ベースのアクセス管理用ソリューションが管理者に提供されます。

アプリケーションに対するユーザーとグループのプロビジョニングおよびプロビジョニング解除を自動化するための SCIM エンドポイントの開発について詳しくは、「[SCIM エンドポイントの構築とユーザー プロビジョニングの構成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)」をご覧ください。 多くのアプリケーションは、Microsoft Entra IDと直接統合されます。 たとえば、Slack、Azure Databricks、Snowflake などがあります。 これらのアプリの場合は、開発者向けドキュメントをスキップし、[Tutorials で提供されているチュートリアルを使用して、SaaS アプリケーションを Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list) と統合します。

### 手動プロビジョニングと自動プロビジョニングの比較

Microsoft Entra ギャラリー内のアプリケーションは、次の 2 つのプロビジョニング モードのいずれかをサポートします。

- **Manual** プロビジョニングは、アプリに自動の Microsoft Entra プロビジョニング コネクタがまだ備わっていないことを意味します。 それらは手動で作成する必要があります。 たとえば、アプリの管理ポータルにユーザーを直接追加したり、ユーザー アカウントの詳細を含むスプレッドシートをアップロードしたりすることでこれを行います。 アプリから提供されるドキュメントを確認するか、アプリの開発者に問い合わせて、どのようなメカニズムが使用できるか判断してください。
- **Automatic** は、このアプリケーションでMicrosoft Entra プロビジョニング コネクタが使用可能であることを意味します。 そのアプリケーションのプロビジョニングの設定に固有の設定チュートリアルに従ってください。 [Tutorials で SaaS アプリケーションを Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list) と統合するためのアプリのチュートリアルを見つけます。

アプリケーションでサポートされているプロビジョニング モードは、アプリケーションをエンタープライズ アプリに追加した後の **[プロビジョニング]** タブにも表示されます。

### 自動プロビジョニングの利点

最新の組織で使用されるアプリケーションの数は増え続けています。 IT 管理者は、大規模なアクセス管理を管理する必要があります。 シングル サインオン (SSO) のために SAML や OIDC などの標準を使用しますが、アクセスのためにはユーザーをアプリにプロビジョニングする必要もあります。 プロビジョニングとは、すべてのユーザー アカウントを手動で作成したり、毎週 CSV ファイルをアップロードしたりすることを意味すると思われることもあります。 これらのプロセスは時間がかかり、コストがかかり、エラーが発生しやすくなります。 プロセスを合理化するために、SAML Just-In-Time (JIT) を使用してプロビジョニングを自動化します。 ユーザーが組織から退職するか、役割の変更のために特定のアプリにアクセスする必要がなくなるときに、同じプロセスを使用してプロビジョニングを解除します。

自動プロビジョニングを使用する一般的な動機には、次のようなものがあります。

- プロビジョニング プロセスの効率と正確さを最大限に高める。
- 独自に開発したプロビジョニング ソリューションやプロビジョニング スクリプトのホスティングとメンテナンスに伴うコストを抑える。
- ユーザーが組織を離れるときに、主要な SaaS アプリからその ID をすぐに削除することで、組織のセキュリティを高める。
- 多くのユーザーを特定の SaaS アプリまたは SaaS システムに簡単にインポートする。
- アプリにサインインできるプロビジョニング対象ユーザーを、1 つのポリシー セットで決定する。

Microsoft Entraユーザー プロビジョニングは、これらの課題に対処するのに役立ちます。 お客様がユーザー プロビジョニングMicrosoft Entra使用している方法の詳細については、[ASOS のケース スタディ](https://techcommunity.microsoft.com/blog/identity/asos-better-protects-its-data-with-azure-ad-automated-user-provisioning/827846)を参照してください。 次のビデオでは、Microsoft Entra IDでのユーザー プロビジョニングの概要について説明します。

### Microsoft Entra の自動ユーザー プロビジョニングで使用できるアプリケーションとシステムは何ですか？

Microsoft Entraは、多くの一般的な SaaS アプリと人事システムの事前に設定されたサポートと、[SCIM 2.0 標準](https://techcommunity.microsoft.com/blog/microsoftsecurityandcompliance/provisioning-with-scim-%e2%80%93-getting-started/880010)の特定の部分を実装するアプリの汎用サポートを備えています。

- Preintegrated アプリケーション (ギャラリー SaaS アプリ): saaS アプリケーションを Microsoft Entra ID と統合するために、Microsoft Entra IDが事前に統合されたプロビジョニング コネクタをサポートしているすべてのアプリケーションを Tutorials で見つけることができます。 ギャラリーに一覧表示されている事前統合アプリケーションでは、通常、プロビジョニングに SCIM 2.0 ベースのユーザー管理 API が使用されています。

    [Image: DropBox、Salesforce、その他のロゴを示す画像。]

    プロビジョニング用の新しいアプリケーションをリクエストするには、「[Microsoft Entra アプリケーション ギャラリーにアプリケーションを発行するための申請を行う](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing)」を参照してください。 ユーザー プロビジョニング要求の場合、アプリケーションには SCIM 準拠のエンドポイントが必要です。 アプリをプラットフォームに迅速にオンボードできるように、アプリケーションのベンダーに SCIM 標準に準拠することを要求してください。
- **SCIM 2.0 をサポートするアプリケーション**: SCIM 2.0 ベースのユーザー管理 API を実装するアプリケーションを汎用的に接続する方法については、「[SCIM エンドポイントの構築とユーザー プロビジョニングの構成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)」を参照してください。
- **既存のディレクトリまたはデータベースを使用するアプリケーション、またはプロビジョニング インターフェイスを提供するアプリケーション**: [LDAP](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure) ディレクトリにプロビジョニングする方法、[SQL](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/tutorial-ecma-sql-connector) データベース、[REST または SOAP](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-web-services-connector) インターフェイスの使用、または [PowerShell](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-powershell-connector)、[カスタム ECMA コネクタ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-custom-connector)、または[パートナーによって構築されたコネクタとゲートウェイ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/partner-driven-integrations)を経由してアクセスする方法については、チュートリアルを参照してください。
- **SAML を使用して Just-In-Time プロビジョニングをサポートするアプリケーション**。

### アプリケーションへの自動プロビジョニングを設定する方法

ギャラリーに一覧表示されている事前統合されたアプリケーションについては、既存のステップ バイ ステップ ガイダンスを使用して自動プロビジョニングを設定します。SaaS アプリケーションを Microsoft Entra ID と統合するためのTutorials に関するページを参照してください。 次のビデオでは、SalesForce の自動ユーザー プロビジョニングの設定方法が示されます。

SCIM 2.0 をサポートする他のアプリケーションについては、「[SCIM エンドポイントの構築とユーザー プロビジョニングの構成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)」の手順に従ってください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping"} -->
## マッピングのために Microsoft Entra ID に属性を同期する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: Microsoft Entra ID と SaaS アプリでのユーザー プロビジョニングを構成する際は、ディレクトリの拡張機能を使用して、既定では同期されないソース属性を追加します。

Microsoft Entra ID のユーザー アカウントを [SaaS アプリ](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)またはオンプレミス アプリケーションにプロビジョニングするときは、ユーザー プロファイルを作成するために必要なすべてのデータ (属性) が Microsoft Entra ID に含まれている必要があります。 ユーザー プロビジョニングのために属性マッピングをカスタマイズしているときに、マップ対象の属性が Microsoft Entra ID の **[ソース属性]** の一覧に表示されないことがあります。 この記事では、欠落している属性を追加する方法について説明します。

### 拡張機能を追加する必要がある場所を特定する

アプリケーションに必要な不足している属性の追加は、ユーザー アカウントが存在する場所と Microsoft Entra ID への取り込み方法に応じて、オンプレミスの Active Directory または Microsoft Entra ID で開始されます。

まず、Microsoft Entra テナント内のどのユーザーがアプリケーションへのアクセスを必要とし、したがってアプリケーションにプロビジョニングされる範囲に含まれるかを特定します。

次に、属性のソースと、それらのユーザーを Microsoft Entra ID に取り込む方法のトポロジを決定します。

| 属性のソース | トポロジ | 必要な手順 |
| --- | --- | --- |
| HR システム | 人事システムのワーカーは、ユーザーとして Microsoft Entra ID にプロビジョニングされます。 | Microsoft Entra ID で拡張属性を作成します。HR 受信マッピングを更新して、HR システムの Microsoft Entra ID ユーザーに拡張機能属性を設定します。 |
| HR システム | 人事システムのワーカーは、ユーザーとして Windows Server AD にプロビジョニングされます。 Microsoft Entra Connect クラウド同期は、それらを Microsoft Entra ID に同期します。 | 必要に応じて、AD スキーマを拡張します。クラウド同期を使用して、Microsoft Entra ID で拡張機能属性を作成します。HR 受信マッピングを更新して、HR システムから AD ユーザーの拡張機能属性を設定します。 |
| HR システム | 人事システムのワーカーは、ユーザーとして Windows Server AD にプロビジョニングされます。 Microsoft Entra Connect は、それらを Microsoft Entra ID に同期します。 | 必要に応じて、AD スキーマを拡張します。Microsoft Entra Connect を使用して、Microsoft Entra ID に拡張属性を作成します。HR 受信マッピングを更新して、HR システムから AD ユーザーの拡張機能属性を設定します。 |

組織のユーザーが既にオンプレミスの Active Directory に存在する場合、または Active Directory でユーザーを作成している場合は、Active Directory から Microsoft Entra ID にユーザーを同期する必要があります。 [Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect) または [Microsoft Entra Connect クラウド同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync)を使用して、ユーザーと属性を同期できます。

1. 必要な属性が AD DS スキーマ `User` オブジェクト クラスの一部であるかどうかをオンプレミスの Active Directory ドメイン管理者に確認し、そうでない場合は、それらのユーザーがアカウントを持つドメインで [Active Directory Domain Services スキーマを拡張](https://learn.microsoft.com/ja-jp/windows/win32/ad/how-to-extend-the-schema)します。
2. [Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect) または Microsoft Entra Connect クラウド同期を構成し、Active Directory から Microsoft Entra ID に拡張属性を持つユーザーを同期するようにします。 どちらのソリューションも、特定の属性を Microsoft Entra ID に自動的に同期しますが、すべての属性を同期するわけではありません。 さらに、既定で同期される一部の属性 (`sAMAccountName` など) が、Graph API を使用して公開されない可能性があります。 このような場合は、Microsoft Entra Connect ディレクトリ拡張機能を使って、Microsoft Entra ID に属性を同期するか、Microsoft Entra Connect クラウド同期を使うことができます。これにより、属性は Microsoft Graph API と Microsoft Entra プロビジョニング サービスに表示されるようになります。
3. オンプレミスの Active Directory 内のユーザーが必要な属性をまだ持っていない場合は、それらのユーザーを Active Directory で更新する必要があります。 この更新は、[Workday](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial) または [SAP SuccessFactors](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial) からプロパティを読み取るか、別の HR システムを使用している場合は、[受信 HR API](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-concepts) を使用するかのいずれかで実行できます。
4. Microsoft Entra Connect または Microsoft Entra Connect クラウド同期を待って、Active Directory スキーマと Active Directory ユーザーで行った更新を Microsoft Entra ID に同期します。

あるいは、オンプレミスの Active Directory でアプリケーションへのアクセスを必要とするユーザーが作成されていない場合は、アプリケーションへのプロビジョニングを構成する前に、Microsoft Entra ID で PowerShell または Microsoft Graph を使ってスキーマ拡張機能を作成する必要があります。

以降のセクションでは、クラウド専用ユーザーを含むテナントと、Active Directory ユーザーを含むテナントの拡張属性を作成する方法の概要について説明します。

### クラウド専用ユーザーを含むテナントで拡張属性を作成する

Microsoft Graph と PowerShell を使用して、Microsoft Entra ID 内のユーザーのユーザー スキーマを拡張できます。 これは、その属性を必要とするユーザーがいて、オンプレミスの Active Directory に由来するユーザーやオンプレミスの Active Directory から同期されていないユーザーがいる場合に必要です。 (Active Directory が存在する場合は、後述の Microsoft Entra Connect ディレクトリ拡張機能を使用して属性を Microsoft Entra ID に同期する方法に関するセクションを引き続きお読みください)。

スキーマ拡張機能が作成されると、これらの拡張属性は、ほとんどの場合、次回 Microsoft Entra 管理センターのプロビジョニング ページにアクセスしたときに自動的に検出されます。

サービス プリンシパルが 1,000 を超える場合は、ソース属性の一覧に拡張子が欠落していることがあります。 作成した属性が自動的に表示されない場合は、その属性が作成されたことを確認し、それをスキーマに手動で追加します。 作成されたことを確認するには、Microsoft Graph と [Graph Explorer](https://learn.microsoft.com/ja-jp/graph/graph-explorer/graph-explorer-overview) を使用します。 スキーマに手動で追加するには、「[サポートされている属性一覧の編集](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes#editing-the-list-of-supported-attributes)」を参照してください。

#### Microsoft Graph を使用してクラウド専用ユーザーの拡張属性を作成する

[Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/overview) を使用して、Microsoft Entra ユーザーのスキーマを拡張できます。

まず、テナント内のアプリを一覧表示して、作業中のアプリの ID を取得します。 詳細については、「[extensionProperties を一覧表示する](https://learn.microsoft.com/ja-jp/graph/api/application-list-extensionproperty)」を参照してください。

```json
GET https://graph.microsoft.com/v1.0/applications
```

次に、拡張属性を作成します。 下の **ID** プロパティを、前の手順で取得した **ID** に置き換えます。 "appId" ではなく **"ID"** 属性を使用する必要があります。 詳細については、[extensionProperty の作成]/graph/api/application-post-extensionproperty) を参照してください。

```json
POST https://graph.microsoft.com/v1.0/applications/{id}/extensionProperties
Content-type: application/json

{
    "name": "extensionName",
    "dataType": "string",
    "targetObjects": [
      "User"
    ]
}
```

前の要求で、`extension_appID_extensionName` という形式の拡張属性が作成されました。 これで、この拡張属性を使用してユーザーを更新できます。 詳細については、「[ユーザーの更新](https://learn.microsoft.com/ja-jp/graph/api/user-update)」を参照してください。

```json
PATCH https://graph.microsoft.com/v1.0/users/{id}
Content-type: application/json

{
  "extension_inputAppId_extensionName": "extensionValue"
}
```

最後に、ユーザーの属性を確認します。 詳細については、「[ユーザーの取得](https://learn.microsoft.com/ja-jp/graph/api/user-get)」を参照してください。 Graph v1.0 は、属性が返すべきプロパティの 1 つとして要求内で指定されていない限り、既定ではユーザーのディレクトリ拡張属性のいずれも返しません。

```json
GET https://graph.microsoft.com/v1.0/users/{id}?$select=displayName,extension_inputAppId_extensionName
```

#### PowerShell を使用してクラウドのみのユーザーの拡張機能属性を作成する

PowerShell を使用してカスタム拡張機能を作成できます。

```PowerShell
#Connect to your Entra tenant
Connect-Entra -Scopes 'Application.ReadWrite.All'

#Create an application (you can instead use an existing application if you would like)
$App = New-EntraApplication -DisplayName "test app name" -IdentifierUris https://testapp

#Create a service principal
New-EntraServicePrincipal -AppId $App.AppId

#Create an extension property
New-EntraApplicationExtensionProperty -ApplicationId $App.ObjectId -Name "TestAttributeName" -DataType "String" -TargetObjects "User"
```

必要に応じて、クラウドのみのユーザーに拡張機能プロパティを設定できることをテストできます。

```PowerShell
#List users in your tenant to determine the objectid for your user
Get-EntraUser

#Set a value for the extension property on the user. Replace the objectid with the ID of the user and the extension name with the value from the previous step
Set-EntraUserExtension -ObjectId aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb -ExtensionName "extension_6552753978624005a48638a778921fan3_TestAttributeName"

#Verify that the attribute was added correctly.
Get-EntraUser -ObjectId aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb | Select -ExpandProperty ExtensionProperty
```

### クラウド同期を使用して拡張属性を作成する

Active Directory にユーザーがいて、Microsoft Entra Connect クラウド同期を使用している場合、クラウド同期では、新しいマッピングを追加するときに、オンプレミスの Active Directory で拡張機能が自動的に検出されます。 Microsoft Entra Connect 同期を使用している場合は、次の「Connect を使用して拡張属性を作成する」セクションに進みます。

これらの属性を自動検出し、Microsoft Entra ID への対応するマッピングを設定するには、以下の手順を使用します。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;、**Entra Connect**&gt;、そして**Cloud 同期**にアクセスします。
3. 拡張属性とマッピングを追加する構成を選択します。
4. **[属性の管理]** で、**[クリックしてマッピングを編集します]** を選択します。
5. **[属性マッピングの追加]** を選択します。 属性は自動的に検出されます。
6. 新しい属性は、**[ソース属性]** のドロップダウンで使用できます。
7. 目的のマッピングの種類を入力し、**[適用]** をクリックします。

詳細については、[Microsoft Entra Connect クラウド同期でのカスタム属性のマッピング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/custom-attribute-mapping)に関するページを参照してください。

### Microsoft Entra Connect を使用して拡張属性を作成する

アプリケーションにアクセスするユーザーがオンプレミスの Active Directory で作成された場合は、それらのユーザーの属性を Active Directory から Microsoft Entra ID に同期する必要があります。 Microsoft Entra Connect を使用する場合は、アプリケーションへのプロビジョニングを構成する前に、次のタスクを実行する必要があります。

1. 必要な属性が AD DS スキーマ `User` オブジェクト クラスの一部であるかどうかをオンプレミスの Active Directory ドメイン管理者に確認し、そうでない場合は、それらのユーザーがアカウントを持つドメインで [Active Directory Domain Services スキーマを拡張](https://learn.microsoft.com/ja-jp/windows/win32/ad/how-to-extend-the-schema)します。
2. Microsoft Entra Connect ウィザードを開き、[タスク] を選択し、**[同期オプションのカスタマイズ]** を選びます。
3. [ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)としてサインインします。
4. **[オプション機能]** ページで **[ディレクトリ拡張属性の同期]** を選択します。
5. Microsoft Entra ID に拡張する属性を選択します。

    注

    **[使用可能な属性]** での検索は、大文字と小文字が区別されます。
6. Microsoft Entra Connect ウィザードを終了し、完全同期サイクルの実行を許可します。 サイクルが完了したら、スキーマが拡張され、オンプレミスの AD と Microsoft Entra ID の間で新しい値が同期されます。

注

オンプレミスの AD から参照属性 (**managedby** や **DN/DistinguishedName** など) をプロビジョニングする機能は、現在サポートされていません。 [ユーザーの声](https://feedback.azure.com/d365community/forum/22920db1-ad25-ec11-b6e6-000d3a4f0789)に関するページで、この機能の要求をお送りください。

### 新しい属性の設定と使用

Microsoft Entra 管理センターで、シングル サインオンまたは Microsoft Entra ID からアプリケーションへのプロビジョニングの[ユーザー属性マッピングを編集](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)しているときに、**ソース属性**の一覧に、`<attributename> (extension_<appID>_<attributename>)` 形式で追加された属性が含まれるようになりました。ここで、appID はテナント内のプレースホルダー アプリケーションの識別子です。 属性を選択し、プロビジョニングのためにターゲット アプリケーションにマップします。

[Image: Microsoft Entra Connect ウィザードの [ディレクトリ拡張機能] 選択ページ]

次に、アプリケーションへのプロビジョニングを有効にする前に、アプリケーションに割り当てられているユーザーに必要な属性を設定する必要があります。 属性が Active Directory で生成されていない場合、ユーザーを一括で設定する方法は 5 つあります。

- プロパティが HR システムで生成され、その HR システムから Active Directory のユーザーとしてワーカーをプロビジョニングする場合は、[Workday](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial)、[SAP SuccessFactors](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial) を使用してマッピングを構成します。または別の HR システムを使用している場合は、[受信 HR API](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-concepts) を使用して Active Directory 属性へのマッピングを構成します。 次に、Microsoft Entra Connect または Microsoft Entra Connect クラウド同期を待って、Active Directory スキーマと Active Directory ユーザーで行った更新を Microsoft Entra ID に同期します。
- プロパティが HR システムで生成され、Active Directory を使用していない場合は、[Workday](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-cloud-only-tutorial)、[SAP SuccessFactors](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-cloud-only-tutorial)、またはその他から[受信 API](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-concepts) を介して Microsoft Entra ユーザー属性へのマッピングを構成できます。
- プロパティがもう一方のオンプレミス システムで作成された場合は、Microsoft Entra ユーザーを作成または更新するように [Microsoft Graph 用の MIM コネクタ](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-2016-connector-graph)を構成できます。
- これらのプロパティがユーザー自身で作成された場合は、[エンタイトルメント管理カタログ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#add-resource-attributes-in-the-catalog)に属性の要件を含めることによって、アプリケーションへのアクセス権が要求されたときに、その属性の値を指定するようユーザーに依頼できます。
- その他のすべての状況では、カスタム アプリケーションが [Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/extensibility-overview?tabs=http#update-or-delete-directory-extension-properties) API を使用してユーザーを更新できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/what-is-hr-driven-provisioning"} -->
## Microsoft Entra ID を使った人事主導のプロビジョニングとは - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/what-is-hr-driven-provisioning
- Service: entra-id / app-provisioning
- Article date: 2025-04-09
- Summary: 人事主導のプロビジョニングについて概説します。

[Image: 人事のプロビジョニング]

人事主導のプロビジョニングは、人事システムに基づいてデジタル ID を作成するプロセスです。 人事システムは、新しく作成されるデジタル ID の Start of Authority として、多くの場合、さまざまなプロビジョニング プロセスの基となります。 たとえば、新しい従業員が入社した場合、それらの従業員が人事システムに作成されます。 この作成がトリガーとなって、Active Directory にユーザー アカウントがプロビジョニングされ、その後、そのアカウントが Microsoft Entra Connect によって Microsoft Entra ID にプロビジョニングされます。

組織には、SAP HCM などのオンプレミスの人事システム、SAP SuccessFactors や Workday などのクラウドベースの人事システム、またはその両方が混在している場合があります。 これまで、人事主導のプロビジョニング用のオンプレミス人事システムとの統合では、SAP Identity Management (SAP IDM) や Microsoft Identity Manager (MIM) などのオンプレミスの人事ツールを利用して Active Directory にユーザーを作成していました。 また、Microsoft Entra をオンプレミスの人事システムと共に使用して、Active Directory でユーザーを作成および更新する、または Active Directory を持たない環境に対して、Microsoft Entra ID でユーザーを作成および更新することもできます。

### オンプレミス ベースの人事プロビジョニング

オンプレミス ベースの人事プロビジョニングは、ローカル人事システムと、新しいデジタル ID をプロビジョニングする手段とを使用して行われます。

人事システムは、さまざまなパッケージやソフトウェア バンドルの形で提供され、SQL サーバーや、他のシステムとデータを交換するためのファイルが使用されることもあります。

SAP の人材管理 (HCM) を使用しており、SAP SuccessFactors を持っているお客様は、SAP Integration Suite を使用して SAP HCM と SAP SuccessFactors の間で雇用者のリストを同期することで、ID を Microsoft Entra ID に取り込むことができます。 そこから、ID を Microsoft Entra ID に直接取り込んだり、Active Directory Domain Services にプロビジョニングしたりできます。

[Image: SAP HR 統合の図。]

Microsoft Entra の [API 主導の受信プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-concepts)を使用すると、オンプレミスの人事システムと統合することもできます。 フラット ファイル、CSV ファイル、SQL ステージング テーブルは、エンタープライズ統合シナリオでよく使用されます。 従業員、請負業者、ベンダーの情報は、これらの形式のいずれかに定期的にエクスポートされ、自動化ツールが使用してこのデータをエンタープライズ ID ディレクトリと同期します。 パートナーは、カスタム HR コネクタを構築することで、レコード システムから Microsoft Entra ID へのデータ フローに関するさまざまな統合要件を満たすことができます。 API 主導のインバウンド プロビジョニングでは、上記のすべてのシナリオにおいて統合が簡素化されます。これは、Microsoft Entra プロビジョニング サービスが、ID プロファイル比較の実行、IT 管理者が設定したスコープ ロジックへのデータ同期の制限、Microsoft Entra 管理センターで管理されるルールベースの属性フローと変換の実行を引き継ぐためです。

[Image: API ワークフローのシナリオを示す図。]

オンプレミスの人事システムで新しい ID が作成されたときに、Microsoft Identity Manager を使用してプロビジョニングをトリガーすることもできます。 MIM を使用すると、オンプレミスの人事システムから Active Directory または Microsoft Entra ID にユーザーをプロビジョニングすることができます。 Microsoft Identity Manager とそのサポート対象システムについては、[Microsoft Identity Manager](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-2016) のドキュメントを参照してください。

[Image: SAP HR と MIM 統合の図。]

### Microsoft Entra のユーザー プロビジョニングに対するクラウド人事アプリケーション

従来、IT スタッフは、手動による社員の作成、更新、削除の方法に依存していました。 これらは、CSV ファイルのアップロードや、カスタム スクリプトなどの方法を使用して社員データを同期しています。 これらのプロビジョニング プロセスは、エラーが発生しやすく、安全でなく、管理が困難です。

従業員、ベンダー、臨時労働者の ID ライフサイクルを管理するために、[Microsoft Entra ユーザー プロビジョニング サービス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)は、クラウドベースの人事 (HR) アプリケーションとの統合を提供しています。 アプリケーションの例としては、Workday や SuccessFactors などがあります。

Microsoft Entra ID は、この統合を利用して、クラウド人事アプリケーション (アプリ) の次のワークフローを有効にします。

- **Active Directory へのユーザーのプロビジョニング:** クラウド人事アプリから選択したユーザーのセットを、1 つ以上の Active Directory ドメインにプロビジョニングします。
- **クラウドのみのユーザーの Microsoft Entra ID へのプロビジョニング:** Active Directory が使用されていないシナリオでは、クラウド人事アプリから Microsoft Entra ID にユーザーを直接プロビジョニングします。
- **クラウド人事アプリへの書き戻し:** メール アドレスやユーザー名の属性を Microsoft Entra からクラウド人事アプリに書き戻します。

### 有効な人事シナリオ

Microsoft Entra ユーザー プロビジョニング サービスを使用すると、次に示す人事ベースの ID ライフサイクル管理シナリオを自動化できます。

- **新しい社員の雇用:** 新しい社員がクラウド人事アプリに追加されると、Active Directory と Microsoft Entra ID でユーザー アカウントが自動的に作成され、必要に応じて、メール アドレスとユーザー名の属性がクラウド人事アプリに書き戻されます。
- **従業員の属性とプロファイルの更新:** クラウド人事アプリで名前、役職、上司などの従業員レコードが更新されると、そのユーザー アカウントが Active Directory と Microsoft Entra ID で自動的に更新されます。
- **従業員の退職:** クラウド人事アプリで従業員が退職すると、そのユーザー アカウントが Active Directory および Microsoft Entra ID で自動的に無効になります。
- **従業員の再雇用:** クラウド人事アプリで従業員が再雇用されると、その古いアカウントが自動的に再アクティブ化されるか、Active Directory および Microsoft Entra ID に再プロビジョニングされます。

### この統合が最も適している組織

クラウド人事アプリの Microsoft Entra ユーザー プロビジョニングとの統合は、次のような組織に最適です。

- クラウド人事のユーザーをプロビジョニングするための、事前構築済みのクラウドベースのソリューションを必要としている。
- クラウド人事アプリから Active Directory または Microsoft Entra ID に直接、ユーザーをプロビジョニングする必要がある。
- クラウド人事アプリから取得したデータを使用してユーザーをプロビジョニングする必要がある。
- クラウド人事アプリで検出された変更情報のみに基づいて、ユーザーの入社、異動、退職を 1 つ以上の Active Directory フォレスト、ドメイン、または OU と同期する必要がある。
- メールに Office 365 を使用している。

#### 主な利点

ここで説明する人事ベースの IT プロビジョニング機能は、次に挙げるような大きなメリットをビジネスにもたらします。

- **生産性の向上:** ユーザー アカウントや Office 365 ライセンスの割り当てを自動化し、重要なグループへのアクセスを提供できるようになります。 割り当ての自動化により、新しい社員は各自の業務ツールにすぐにアクセスできるため、生産性が向上します。
- **リスク管理:** クラウド人事アプリとデータをやり取りし、社員の状態またはグループ メンバーシップに基づいて変更を自動化することで、セキュリティを強化できます。 変更を自動化すると、ユーザーが異動または退職したときに、ユーザーの ID と重要なアプリへのアクセスが自動的に更新されるようになります。
- **コンプライアンスとガバナンスへの対応:** Microsoft Entra ID は、ソースとターゲットの両方のシステムのアプリによって実行されるユーザー プロビジョニング要求のネイティブ監査ログをサポートします。 監査によって、誰がアプリにアクセスできるかを 1 つの画面から追跡できます。
- **コスト管理:** 自動プロビジョニングを使用すると、手動プロビジョニングに関連した非効率性や人的エラーを回避することでコストが削減されます。 従来の陳腐化したプラットフォームを使用して、長らく構築されてきた、カスタム開発のユーザー プロビジョニング ソリューションを不要にします。

### Joiner-Mover-Leaver ライフサイクル ワークフローの管理

人事主導のプロビジョニング プロセスを拡張して、新規採用、人事上の変更、退職に関連するビジネス プロセスとセキュリティ制御をさらに自動化できます。 [Microsoft Entra ID Governance ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows)では、次のような Joiner-Mover-Leaver ワークフローを構成できます。

- 新規採用者が入社する日の "X" 日前に、マネージャーに電子メールを送信し、ユーザーをグループに追加し、初回ログインの一時的なアクセス パスを生成します。
- ユーザーの部署または役職またはグループ メンバーシップに変更がある場合は、カスタム タスクを起動します。
- 在籍最終日に、マネージャーに電子メールを送信し、グループとライセンスの割り当てからユーザーを削除します。
- 終了の "X" 日後、Microsoft Entra ID からユーザーを削除します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/whats-new-docs"} -->
## Azure Active Directory アプリケーション プロビジョニングの新機能 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/whats-new-docs
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: Azure Active Directory アプリケーション プロビジョニングの新規および更新済みのドキュメント。

Azure Active Directory アプリケーション のプロビジョニングに関するドキュメントの新機能へようこそ。 この記事では、追加された新しいドキュメントと、過去 3 か月間に大幅な更新が行われたドキュメントを示します。 プロビジョニング サービスの新機能については、「 [Azure Active Directory の新機能](https://learn.microsoft.com/ja-jp/entra/fundamentals/whats-new)」を参照してください。

### 2022 年 3 月

#### 更新された記事

- [Azure Active Directory プロビジョニングと SAP SuccessFactors の統合方法](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/sap-successfactors-integration-reference)
- [Azure Active Directory プロビジョニングと Workday の統合方法](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/workday-integration-reference)
- [チュートリアル: Azure Active Directory でサンプル SCIM エンドポイントを開発する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-build-users-and-groups-endpoints)
- [Azure Active Directory でスコープ外のユーザー アカウントの削除をスキップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions)
- [Azure Active Directory アプリケーションのプロビジョニング: 新機能](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/whats-new-docs)

### 2022 年 2 月

#### 更新された記事

- [Azure Active Directory での属性マッピングの式の記述に関するリファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)

### 2022 年 1 月

#### 更新された記事

- [Azure Active Directory プロビジョニングと SAP SuccessFactors の統合方法](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/sap-successfactors-integration-reference)
- [Azure Active Directory での属性マッピングの式の記述に関するリファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)

### 2021 年 12 月

#### 更新された記事

- [Azure Active Directory でのアプリケーション プロビジョニングのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works)

### 2021 年 11 月

#### 新しい記事

- [HR 属性の取得に関する問題のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-attribute-retrieval-issues)
- [HR マネージャーの更新に関する問題のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-manager-update-issues)
- [HR ユーザーの更新に関する問題のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-user-update-issues)
- [HR 書き戻しの問題をトラブルシューティングする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-writeback-issues)
- [HR ユーザー作成に関する問題のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-user-creation-issues)

#### 更新された記事

- [HR 属性の取得に関する問題のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-attribute-retrieval-issues)
- [HR マネージャーの更新に関する問題のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-manager-update-issues)
- [HR ユーザーの更新に関する問題のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-user-update-issues)
- [HR 書き戻しの問題をトラブルシューティングする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-writeback-issues)
- [Azure Active Directory での属性マッピングの式の記述に関するリファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)

### 2021 年 10 月

#### 新しい記事

- [ユーザーを LDAP ディレクトリにプロビジョニングするための Azure AD の構成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure)

### 2021 年 9 月

#### 新しい記事

- [Azure AD プロビジョニング サービスで誤削除防止を有効にする (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/accidental-deletions)

#### 更新された記事

- [Azure Active Directory でのアプリケーション プロビジョニングのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works)

### 2021 年 8 月

#### 更新された記事

- [Azure Active Directory での属性マッピングの式の記述に関するリファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)
- [Azure AD ユーザー プロビジョニング サービスの SCIM 2.0 プロトコル コンプライアンスに関する既知の問題と解決策](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-config-problem-scim-compatibility)
- [チュートリアル - Azure Active Directory で SaaS アプリケーションのユーザー プロビジョニング属性マッピングをカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)

### 2021 年 7 月

#### 更新された記事

- [Azure Active Directory での属性マッピングの式の記述に関するリファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)
- [Microsoft Graph API を使用してプロビジョニングを構成する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-configuration-api)
- [Azure Active Directory での自動ユーザー プロビジョニングのデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-auto-user-provisioning)
- [Azure Active Directory ユーザー プロビジョニングへのクラウド HR アプリケーションを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision)
- [Azure Active Directory でのアプリケーション プロビジョニングに関する既知の問題](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/known-issues)
- [Azure AD ECMA Connector Host 汎用 SQL コネクタのチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/tutorial-ecma-sql-connector)
- [Azure Active Directory でのアプリ プロビジョニングとは](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)
- [ECMA コネクタ ホストに関する問題のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ecma-troubleshoot)
- [Azure AD ECMA コネクタ ホストで使用する Microsoft Identity Manager コネクタをエクスポートする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-migrate-microsoft-identity-manager)
- [SCIM 対応アプリへの Azure AD オンプレミス アプリケーション プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-scim-provisioning)
- [Azure AD ECMA コネクタ ホストの汎用 SQL コネクタ構成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-sql-connector-configure)

### 2021 年 6 月

#### 新しい記事

- [Microsoft Graph API を使用してプロビジョニングを構成する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-configuration-api)
- [Application Provisioning の式ビルダーのしくみを理解する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/expression-builder)

#### 更新された記事

- [Azure Active Directory でのアプリケーション プロビジョニングのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works)
- [Azure Active Directory ユーザー プロビジョニングへのクラウド HR アプリケーションを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision)
- [Azure Active Directory プロビジョニングと Workday の統合方法](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/workday-integration-reference)

### 2021 年 5 月

#### 更新された記事

- [チュートリアル: Azure Active Directory でサンプル SCIM エンドポイントを開発する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-build-users-and-groups-endpoints)
- [チュートリアル: Azure Active Directory での SCIM エンドポイントのプロビジョニングの開発と計画](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)
- [Azure Active Directory アプリケーション プロビジョニングの拡張機能属性の同期](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping)
- [Azure Active Directory での SaaS アプリの自動ユーザー プロビジョニングとは](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)
- [Azure Active Directory の Workday 属性リファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/workday-attribute-reference)
- [Azure Active Directory プロビジョニングと Workday の統合方法](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/workday-integration-reference)
- [Azure Active Directory でマルチテナント アプリケーションの自動ユーザー プロビジョニングを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/isv-automatic-provisioning-multi-tenant-apps)
- [Azure Active Directory でのアプリケーション プロビジョニングに関する既知の問題](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/known-issues)
- [Azure Active Directory での自動ユーザー プロビジョニングのデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-auto-user-provisioning)
- [Azure Active Directory ユーザー プロビジョニングへのクラウド HR アプリケーションを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision)
- [Azure Active Directory でのオンデマンド プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)
- [Azure Active Directory Connect プロビジョニング エージェント: バージョンのリリース履歴](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provisioning-agent-release-version-history)
- [Azure Active Directory の SAP SuccessFactors 属性リファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/sap-successfactors-attribute-reference)
- [Azure Active Directory プロビジョニングと SAP SuccessFactors の統合方法](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/sap-successfactors-integration-reference)
- [SCIM と Microsoft Graph を組み合わせて使用してユーザーをプロビジョニングし、必要なデータでアプリケーションを強化する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/scim-graph-scenarios)
- [Azure Active Directory でスコープ外のユーザー アカウントの削除をスキップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions)
- [ユーザーがプロビジョニングされていない](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-config-problem-no-users-provisioned)
- [Azure AD ユーザー プロビジョニング サービスの SCIM 2.0 プロトコル コンプライアンスに関する既知の問題と解決策](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-config-problem-scim-compatibility)
- [Azure AD ギャラリー アプリケーションへのユーザー プロビジョニングの構成に関する問題](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/troubleshoot)
- [プロビジョニングと Azure Monitor ログの統合方法を理解する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-log-analytics)
- [検疫状態のアプリケーションのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)
- [ユーザー プロビジョニングの状態を確認する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)
- [チュートリアル: 自動ユーザー アカウント プロビジョニングに関するレポート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)
- [Azure Portal でエンタープライズ アプリのユーザー アカウント プロビジョニングを管理する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/configure-automatic-user-provisioning-portal)
- [チュートリアル - Azure Active Directory で SaaS アプリケーションのユーザー プロビジョニング属性マッピングをカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)
- [スコープ フィルターを使用した属性ベースのアプリケーション プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)
- [方法: プロビジョニング構成をエクスポートし、既知の正常な状態にロールバックする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/export-import-provisioning-configuration)
- [Azure Active Directory での属性マッピングの式の記述に関するリファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)
- [Azure Active Directory でのアプリケーション プロビジョニングのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works)

### 2021 年 4 月

#### 更新された記事

- [アプリプロビジョニング用の拡張機能属性の同期](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping)

### 2021 年 3 月

#### 更新された記事

- [アプリプロビジョニング用の拡張機能属性の同期](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping)
- [検疫状態のアプリケーションのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)
- [Azure Portal でエンタープライズ アプリのユーザー アカウント プロビジョニングを管理する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/configure-automatic-user-provisioning-portal)
- [Azure AD での属性マッピングの式の記述に関するリファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)
- [チュートリアル: SCIM エンドポイントのサンプルを開発する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-build-users-and-groups-endpoints)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/workday-attribute-reference"} -->
## Microsoft Entra ID の Workday 属性リファレンス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/workday-attribute-reference
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: Microsoft Entra ID で XPATH クエリを使って Workday からフェッチできる属性について説明します。

このセクションでは、XPATH クエリを使用して Workday からフェッチできる属性の一覧を示します。 使用する予定の Workday Web サービス API バージョンに基づいて、該当するセクションを参照してください。

### Workday Web Services (WWS) API v21.1 の XPATH 値

下の表は、Workday 受信プロビジョニング アプリ コネクタに付属する Workday 属性とそれに対応する XPATH 式を一覧にしたものです。 *接続 URL にバージョン情報が指定されていない場合、またはバージョンが v21.1 に設定されている場合*は、これらの XPATH 値が使用されます。

[Image: Workday バージョン情報なしのスクリーンショット]

| # | Workday 属性名 | Workday XPATH API 式 |
| --- | --- | --- |
| 1 | アクティブ | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Active/text() |
| 2 | AddressLine2Data | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Address\_Line\_Data[@wd:Type='ADDRESS\_LINE\_2']/text() |
| 3 | AddressLine3Data | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Address\_Line\_Data[@wd:Type='ADDRESS\_LINE\_3']/text() |
| 4 | AddressLine4Data | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Address\_Line\_Data[@wd:Type='ADDRESS\_LINE\_4']/text() |
| 5 | AddressLine5Data | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Address\_Line\_Data[@wd:Type='ADDRESS\_LINE\_5']/text() |
| 6 | AddressLine6Data | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Address\_Line\_Data[@wd:Type='ADDRESS\_LINE\_6']/text() |
| 7 | AddressLine7Data | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Address\_Line\_Data[@wd:Type='ADDRESS\_LINE\_7']/text() |
| 8 | AddressLine8Data | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Address\_Line\_Data[@wd:Type='ADDRESS\_LINE\_8']/text() |
| 9 | AddressLine9Data | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Address\_Line\_Data[@wd:Type='ADDRESS\_LINE\_9']/text() |
| 10 | AddressLineData | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Address\_Line\_Data/text() |
| 11 | BusinessTitle | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Title/text() |
| 12 | [会社] | wd:Worker/wd:Worker\_Data/wd:Organization\_Data/wd:Worker\_Organization\_Data[translate(string(wd:Organization\_Data/wd:Organization\_Type\_Reference/wd:ID[@wd:type='Organization\_Type\_ID ']),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='COMPANY']/wd:Organization\_Reference/@wd:Descriptor |
| 13 | ContingentWorkerID | wd:Worker/wd:Worker\_Reference/wd:ID[@wd:type='Contingent\_Worker\_ID']/text() |
| 14 | CountryReference | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Reference/wd:ID[@wd:type='ISO\_3166-1\_Alpha-3\_Code']/text() |
| 15 | カントリーリファレンスフレンドリー | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Reference/@wd:Descriptor |
| 16 | 国参照数値 | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Reference/wd:ID[@wd:type='ISO\_3166-1\_Numeric-3\_Code']/text() |
| 十七 | 国リファレンス2文字 | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Reference/wd:ID[@wd:type='ISO\_3166-1\_Alpha-2\_Code']/text() |
| 18 | 国地域リファレンス | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Region\_Reference/@wd:Descriptor |
| 19 | メールアドレス | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Contact\_Data/wd:Email\_Address\_Data[translate(string(wd:Usage\_Data/wd:Type\_Data/wd:Type\_Reference/@wd:Descriptor),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='WORK']/wd:Email\_Address/text(text) |
| 20 | 従業員ID | wd:Worker/wd:Worker\_Reference/wd:ID[@wd:type='Employee\_ID']/text() |
| 21 (二十一) | FacilityLocation | wd:Worker/wd:Worker\_Data/wd:Organization\_Data/wd:Worker\_Organization\_Data[translate(string(wd:Organization\_Data/wd:Organization\_Type\_Reference/wd:ID[@wd:type='Organization\_Type\_ID ']),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='FACILITY']/wd:Organization\_Reference/@wd:Descriptor |
| 22 | ファクス | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Contact\_Data/wd:Phone\_Data[translate(string(wd:Phone\_Device\_Type\_Reference/@wd:Descriptor),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')=='FAX' and translate(string(wd:Usage\_Data/wd:Type\_Data/wd:Type\_Reference/@wd:Descriptor),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='WORK']/@wd:Formatted\_Phone |
| 23 | ファーストネーム | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Name\_Data/wd:Legal\_Name\_Data/wd:Name\_Detail\_Data/wd:First\_Name/text() |
| 二十四 | JobClassificationID | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Job\_Classification\_Summary\_Data/wd:Job\_Classification\_Reference/wd:ID[@wd:type='Job\_Classification\_Reference\_ID']/text() |
| 二十五 | JobFamilyID | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Job\_Profile\_Summary\_Data/wd:Job\_Family\_Reference/wd:ID[@wd:type='Job\_Family\_ID']/text() |
| 26 | 苗字 | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Name\_Data/wd:Legal\_Name\_Data/wd:Name\_Detail\_Data/wd:Last\_Name/text() |
| 二十七 | LeaveAbsenceType | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Leave\_Status\_Data[wd:On\_Leave='1']/wd:Leave\_of\_Absence\_Type\_Reference/wd:ID[@wd:type='Leave\_of\_Absence\_Type\_ID']/text() |
| 28 | LocalReference | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Local\_Reference/wd:ID[@wd:type='Locale\_ID']/text() |
| 二十九 | LocationIdentifier | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Location\_Reference/wd:ID[@wd:type='Location\_ID']/text() |
| 30 | ManagerReference | wd:Worker/wd:Worker\_Data/wd:Management\_Chain\_Data/wd:Worker\_Supervisory\_Management\_Chain\_Data[position()=1]/wd:Management\_Chain\_Data[last()=position()]/wd:Manager\_Reference/wd:ID[@wd:type='WID']/text() |
| 31 | ミドルネーム | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Name\_Data/wd:Legal\_Name\_Data/wd:Name\_Detail\_Data/wd:Middle\_Name/text() |
| 32 | モバイル | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Contact\_Data/wd:Phone\_Data[translate(string(wd:Phone\_Device\_Type\_Reference/@wd:Descriptor),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')=='MOBILE' and translate(string(wd:Usage\_Data/wd:Type\_Data/wd:Type\_Reference/@wd:Descriptor),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='WORK']/@wd:Formatted\_Phone |
| 33 | 市町村 | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Municipality/text() |
| 34 | PositionID | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Position\_ID/text() |
| 35 | PositionTitle | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Position\_Title/text() |
| 36 | 郵便番号 | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Postal\_Code/text() |
| 37 | 優先使用名 | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Name\_Data/wd:Preferred\_Name\_Data/wd:Name\_Detail\_Data/wd:First\_Name/text() |
| 三十八 | 希望の苗字 | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Name\_Data/wd:Preferred\_Name\_Data/wd:Name\_Detail\_Data/wd:Last\_Name/text() |
| 39 | PreferredMiddleName | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Name\_Data/wd:Preferred\_Name\_Data/wd:Name\_Detail\_Data/wd:Middle\_Name/text() |
| 40 | PreferredNameData | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Name\_Data/wd:Preferred\_Name\_Data/wd:Name\_Detail\_Data/@wd:Formatted\_Name |
| 41 | PrimaryWorkTelephone | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Contact\_Data/wd:Phone\_Data[wd:Usage\_Data/wd:Type\_Data/@wd:Primary='1' and translate(string(wd:Usage\_Data/wd:Type\_Data/wd:Type\_Reference/@wd:Descriptor),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='WORK']/@wd:Formatted\_Phone |
| 42 | StatusAcademisTenureDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Academic\_Tenure\_Date/text() |
| 43 | StatusActiveStatusDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Active\_Status\_Date/text() |
| 44 | StatusBenefitsServiceDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Benefits\_Service\_Date/text() |
| 45 | StatusCompanyServiceDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Company\_Service\_Date/text() |
| 46 | StatusContinuousFirstDayOfWork | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:First\_Day\_of\_Work/text() |
| 47 | StatusContinuousServiceDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Continuous\_Service\_Date/text() |
| 48 | StatusDateEnteredWorkforce | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Date\_Entered\_Workforce/text() |
| 49 | StatusDaysUnemployed | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Days\_Unemployed/text() |
| 50 | StatusEndEmploymentDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:End\_Employment\_Date/text() |
| 51 | StatusExpectedRetirementDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Expected\_Retirement\_Date/text() |
| 52 | StatusHireDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Hire\_Date/text() |
| 53 | StatusMonthsContinuousPriorEmployment | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Months\_Continuous\_Prior\_Employment/text() |
| 54 | StatusNotEligibleForHire | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Not\_Eligible\_For\_Hire/text() |
| 55 | StatusNotEligibleForRehire | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Not\_Eligible\_for\_Rehire/text() |
| 56 | StatusOriginalHireDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Original\_Hire\_Date/text() |
| 五十七 | StatusProbationEndDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Probation\_End\_Date/text() |
| 58 | StatusProbationStartDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Probation\_Start\_Date/text() |
| 59 | StatusRegrettableTermination | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Regrettable\_Termination/text() |
| 六十 | StatusRehire | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Rehire/text() |
| 61 | StatusResignationDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Resignation\_Date/text() |
| 62 | StatusRetired | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Retired/text() |
| 63 | StatusRetirementDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Retirement\_Date/text() |
| 64 | StatusRetirementEligibilityDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Retirement\_Eligibility\_Date/text() |
| 65 | StatusSeniorityDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Seniority\_Date/text() |
| 66 | StatusSeveranceDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Severance\_Date/text() |
| 67 | StatusTerminated | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Terminated/text() |
| 68 | ステータス終了日 | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Termination\_Date/text() |
| 69 | StatusTerminationInvoluntary | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Termination\_Involuntary/text() |
| 70 | StatusTerminationLastDayOfWork | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Termination\_Last\_Day\_of\_Work/text() |
| 71 | StatusTimeOffServiceDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Time\_Off\_Service\_Date/text() |
| 72 | StatusVestingDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Vesting\_Date/text() |
| 73 | 監督組織 | wd:Worker/wd:Worker\_Data/wd:Organization\_Data/wd:Worker\_Organization\_Data/wd:Organization\_Data[translate(string(wd:Organization\_Type\_Reference/wd:ID[@wd:type='Organization\_Type\_ID ']),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='SUPERVISORY']/wd:Organization\_Name/text() |
| 74 | 電話番号 | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Contact\_Data/wd:Phone\_Data[translate(string(wd:Phone\_Device\_Type\_Reference/@wd:Descriptor),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')=='TELEPHONE' and translate(string(wd:Usage\_Data/wd:Type\_Data/wd:Type\_Reference/@wd:Descriptor),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='WORK']/@wd:Formatted\_Phone |
| 75 | TransactionLogData | wd:Worker/wd:Worker\_Data/wd:Transaction\_Log\_Entry\_Data/wd:Transaction\_Log\_Entry |
| 76 | UserID | wd:Worker/wd:Worker\_Data/wd:User\_ID/text() |
| 77 | WID | wd:Worker/wd:Worker\_Reference/wd:ID[@wd:type='WID']/text() |
| 78 | WorkerID | wd:Worker/wd:Worker\_Data/wd:Worker\_ID/text() |
| 79 | WorkerType | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Worker\_Type\_Reference/@wd:Descriptor |
| 80 | WorkSpaceReference | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Work\_Space\_\_Reference/@wd:Descriptor |

### Workday Web Services (WWS) API v30 以降の XPATH 値

次に示すように、接続 URL で WWS API v30.0 以上を使用している場合:

[Image: Workday バージョン情報のスクリーンショット]

この場合、プロビジョニング ジョブを有効にする前に、 の &gt; を更新して、表の値を使用するようにしてください。

追加の XPATH を構成するには、チュートリアルの[「構成の確認](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#managing-your-configuration)」を参照してください。

| # | Workday 属性名 | Workday XPATH API 式 |
| --- | --- | --- |
| 1 | アクティブ | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Active/text() |
| 2 | AddressLine2Data | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Address\_Line\_Data[@wd:Type='ADDRESS\_LINE\_2']/text() |
| 3 | AddressLine3Data | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Address\_Line\_Data[@wd:Type='ADDRESS\_LINE\_3']/text() |
| 4 | AddressLine4Data | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Address\_Line\_Data[@wd:Type='ADDRESS\_LINE\_4']/text() |
| 5 | AddressLine5Data | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Address\_Line\_Data[@wd:Type='ADDRESS\_LINE\_5']/text() |
| 6 | AddressLine6Data | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Address\_Line\_Data[@wd:Type='ADDRESS\_LINE\_6']/text() |
| 7 | AddressLine7Data | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Address\_Line\_Data[@wd:Type='ADDRESS\_LINE\_7']/text() |
| 8 | AddressLine8Data | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Address\_Line\_Data[@wd:Type='ADDRESS\_LINE\_8']/text() |
| 9 | AddressLine9Data | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Address\_Line\_Data[@wd:Type='ADDRESS\_LINE\_9']/text() |
| 10 | AddressLineData | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Address\_Line\_Data/text() |
| 11 | BusinessTitle | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Business\_Title/text() |
| 12 | [会社] | wd:Worker/wd:Worker\_Data/wd:Organization\_Data/wd:Worker\_Organization\_Data[translate(string(wd:Organization\_Data/wd:Organization\_Type\_Reference/wd:ID[@wd:type='Organization\_Type\_ID']]),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='COMPANY']/wd:Organization\_Data/wd:Organization\_Name/text() |
| 13 | ContingentWorkerID | wd:Worker/wd:Worker\_Reference/wd:ID[@wd:type='Contingent\_Worker\_ID']/text() |
| 14 | CountryReference | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Reference/wd:ID[@wd:type='ISO\_3166-1\_Alpha-3\_Code']/text() |
| 15 | カントリーリファレンスフレンドリー | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Reference/@wd:Descriptor |
| 16 | 国参照数値 | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Reference/wd:ID[@wd:type='ISO\_3166-1\_Numeric-3\_Code']/text() |
| 十七 | 国リファレンス2文字 | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Reference/wd:ID[@wd:type='ISO\_3166-1\_Alpha-2\_Code']/text() |
| 18 | 国地域リファレンス | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Region\_Descriptor/text() |
| 19 | メールアドレス | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Contact\_Data/wd:Email\_Address\_Data[wd:Usage\_Data/@wd:Public='1' and string(wd::Email\_Address\_Data Usage\_Data/wd:Type\_Data/wd:Type\_Reference/wd:ID[@wd:type='Communication\_Usage\_Type\_ID'])='WORK']/wd:Email\_Address/text() |
| 20 | 従業員ID | wd:Worker/wd:Worker\_Reference/wd:ID[@wd:type='Employee\_ID']/text() |
| 21 (二十一) | FacilityLocation | wd:Worker/wd:Worker\_Data/wd:Organization\_Data/wd:Worker\_Organization\_Data/wd:Organization\_Data[translate(string(wd:Organization\_Type\_Reference/wd:ID[@wd:type='Organization\_Type\_ID ']),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='LOCATION\_HIERARCHY']/wd:Organization\_Name/text() |
| 22 | ファクス | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Contact\_Data/wd:Phone\_Data[wd:Usage\_Data/@wd:Public='1' and string(wd:Usage\_Data/wd:Type\_Data/wd:Phone\_Data Type\_Reference/wd:ID[@wd:type='Communication\_Usage\_Type\_ID'])='WORK' and string(wd:Phone\_Device\_Type\_Reference/wd:ID[@wd:type='Phone\_Device\_Type\_ID'])='Fax']/@wd:Workday\_Traditional\_Formatted\_Phone |
| 23 | ファーストネーム | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Name\_Data/wd:Legal\_Name\_Data/wd:Name\_Detail\_Data/wd:First\_Name/text() |
| 二十四 | JobClassificationID | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Job\_Classification\_Summary\_Data/wd:Job\_Classification\_Reference/wd:ID[@wd:type='Job\_Classification\_Reference\_ID']/text() |
| 二十五 | JobFamilyID | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Job\_Profile\_Summary\_Data/wd:Job\_Family\_Reference/wd:ID[@wd:type='Job\_Family\_ID']/text() |
| 26 | 苗字 | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Name\_Data/wd:Legal\_Name\_Data/wd:Name\_Detail\_Data/wd:Last\_Name/text() |
| 二十七 | LeaveAbsenceType | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Leave\_Status\_Data[wd:On\_Leave='1']/wd:Leave\_of\_Absence\_Type\_Reference/wd:ID[@wd:type='Leave\_of\_Absence\_Type\_ID']/text() |
| 28 | LocalReference | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Local\_Reference/wd:ID[@wd:type='Locale\_ID']/text() |
| 二十九 | LocationIdentifier | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Location\_Reference/wd:ID[@wd:type='Location\_ID']/text() |
| 30 | ManagerReference | wd:Worker/wd:Worker\_Data/wd:Management\_Chain\_Data/wd:Worker\_Supervisory\_Management\_Chain\_Data[position()=1]/wd:Management\_Chain\_Data[last()=position()]/wd:Manager\_Reference/wd:ID[@wd:type='WID']/text() |
| 31 | ミドルネーム | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Name\_Data/wd:Legal\_Name\_Data/wd:Name\_Detail\_Data/wd:Middle\_Name/text() |
| 32 | モバイル | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Contact\_Data/wd:Phone\_Data[wd:Usage\_Data/@wd:Public='1' and string(wd:Usage\_Data/wd:Type\_Data/wd:Phone\_Data Type\_Reference/wd:ID[@wd:type='Communication\_Usage\_Type\_ID'])='WORK' and string(wd:Phone\_Device\_Type\_Reference/wd:ID[@wd:type='Phone\_Device\_Type\_ID'])='Mobile']/@wd:Workday\_Traditional\_Formatted\_Phone |
| 33 | 市町村 | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Municipality/text() |
| 34 | PositionID | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Position\_ID/text() |
| 35 | PositionTitle | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Position\_Title/text() |
| 36 | 郵便番号 | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Postal\_Code/text() |
| 37 | 優先使用名 | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Name\_Data/wd:Preferred\_Name\_Data/wd:Name\_Detail\_Data/wd:First\_Name/text() |
| 三十八 | 希望の苗字 | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Name\_Data/wd:Preferred\_Name\_Data/wd:Name\_Detail\_Data/wd:Last\_Name/text() |
| 39 | PreferredMiddleName | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Name\_Data/wd:Preferred\_Name\_Data/wd:Name\_Detail\_Data/wd:Middle\_Name/text() |
| 40 | PreferredNameData | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Name\_Data/wd:Preferred\_Name\_Data/wd:Name\_Detail\_Data/@wd:Formatted\_Name |
| 41 | PrimaryWorkTelephone | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Contact\_Data/wd:Phone\_Data[wd:Usage\_Data/@wd:Public='1' and string(wd:Usage\_Data/wd:Type\_Data/wd:Type\_Reference/wd:ID[@wd:type='Communication\_Usage\_Type\_ID'])='WORK' and string(wd:Phone\_Device\_Type\_Reference/wd:ID[@wd:type='Phone\_Device\_Type\_ID'])='Landline']/@wd:Workday\_Traditional\_Formatted\_Phone |
| 42 | StatusAcademisTenureDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Academic\_Tenure\_Date/text() |
| 43 | StatusActiveStatusDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Active\_Status\_Date/text() |
| 44 | StatusBenefitsServiceDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Benefits\_Service\_Date/text() |
| 45 | StatusCompanyServiceDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Company\_Service\_Date/text() |
| 46 | StatusContinuousFirstDayOfWork | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:First\_Day\_of\_Work/text() |
| 47 | StatusContinuousServiceDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Continuous\_Service\_Date/text() |
| 48 | StatusDateEnteredWorkforce | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Date\_Entered\_Workforce/text() |
| 49 | StatusDaysUnemployed | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Days\_Unemployed/text() |
| 50 | StatusEndEmploymentDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:End\_Employment\_Date/text() |
| 51 | StatusExpectedRetirementDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Expected\_Retirement\_Date/text() |
| 52 | StatusHireDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Hire\_Date/text() |
| 53 | StatusMonthsContinuousPriorEmployment | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Months\_Continuous\_Prior\_Employment/text() |
| 54 | StatusNotEligibleForHire | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Not\_Eligible\_For\_Hire/text() |
| 55 | StatusNotEligibleForRehire | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Not\_Eligible\_for\_Rehire/text() |
| 56 | StatusOriginalHireDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Original\_Hire\_Date/text() |
| 五十七 | StatusProbationEndDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Probation\_End\_Date/text() |
| 58 | StatusProbationStartDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Probation\_Start\_Date/text() |
| 59 | StatusRegrettableTermination | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Regrettable\_Termination/text() |
| 六十 | StatusRehire | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Rehire/text() |
| 61 | StatusResignationDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Resignation\_Date/text() |
| 62 | StatusRetired | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Retired/text() |
| 63 | StatusRetirementDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Retirement\_Date/text() |
| 64 | StatusRetirementEligibilityDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Retirement\_Eligibility\_Date/text() |
| 65 | StatusSeniorityDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Seniority\_Date/text() |
| 66 | StatusSeveranceDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Severance\_Date/text() |
| 67 | StatusTerminated | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Terminated/text() |
| 68 | ステータス終了日 | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Termination\_Date/text() |
| 69 | StatusTerminationInvoluntary | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Termination\_Involuntary/text() |
| 70 | StatusTerminationLastDayOfWork | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Termination\_Last\_Day\_of\_Work/text() |
| 71 | StatusTimeOffServiceDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Time\_Off\_Service\_Date/text() |
| 72 | StatusVestingDate | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Vesting\_Date/text() |
| 73 | 監督組織 | wd:Worker/wd:Worker\_Data/wd:Organization\_Data/wd:Worker\_Organization\_Data/wd:Organization\_Data[translate(string(wd:Organization\_Type\_Reference/wd:ID[@wd:type='Organization\_Type\_ID ']),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='SUPERVISORY']/wd:Organization\_Name/text() |
| 74 | 電話番号 | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Contact\_Data/wd:Phone\_Data[wd:Usage\_Data/@wd:Public='1' and string(wd:Usage\_Data/wd:Type\_Data/wd:Type\_Reference/wd:ID[@wd:type='Communication\_Usage\_Type\_ID'])='WORK' and string(wd:Phone\_Device\_Type\_Reference/wd:ID[@wd:type='Phone\_Device\_Type\_ID'])='Landline']/@wd:Workday\_Traditional\_Formatted\_Phone |
| 75 | TransactionLogData | wd:Worker/wd:Worker\_Data/wd:Transaction\_Log\_Entry\_Data/wd:Transaction\_Log\_Entry |
| 76 | UserID | wd:Worker/wd:Worker\_Data/wd:User\_ID/text() |
| 77 | WID | wd:Worker/wd:Worker\_Reference/wd:ID[@wd:type='WID']/text() |
| 78 | WorkerID | wd:Worker/wd:Worker\_Data/wd:Worker\_ID/text() |
| 79 | WorkerType | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Worker\_Type\_Reference[wd:ID/@wd:type="Contingent\_Worker\_Type\_ID" または wd:ID/@wd:type="Employee\_Type\_ID"]/@wd:Descriptor |
| 80 | WorkSpaceReference | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Work\_Space\_\_Reference/@wd:Descriptor |

### カスタム XPATH 値

次の表に、Workday から Active Directory または Microsoft Entra ID にワーカーをプロビジョニングするときに、一般的に使われるその他のカスタム XPATH API 式の一覧を示します。 ここに記載されている XPATH API 式を、使用している Workday のバージョンでテストして、[チュートリアル:「構成の確認](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#managing-your-configuration)」を参照してください。

この統合を実装する顧客の利益のために XPATH テーブルに属性を追加するには、以下にコメントを残すか、[直接記事に投稿](https://learn.microsoft.com/ja-jp/contribute/)してください。

| # | Workday 属性名 | Workday API バージョン | Workday XPATH API 式 |
| --- | --- | --- | --- |
| 1 | ユニバーサル ID | v30.0 以降 | wd:Worker/wd:Worker\_Data/wd:Universal\_ID/text() |
| 2 | [ユーザー名] | v30.0 以降 | wd:Worker/wd:Worker\_Data/wd:User\_Account\_Data/wd:User\_Name/text() |
| 3 | 管理レベル ID | v30.0 以降 | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Job\_Data[@wd:Primary\_Job=1]/wd:Position\_Data/wd:Job\_Profile\_Summary\_Data/wd:Management\_Level\_Reference/wd:ID[@wd:type="Management\_Level\_ID"]/text() |
| 4 | Rescinded の採用 | v30.0 以降 | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Worker\_Status\_Data/wd:Hire\_Rescinded/text() |
| 5 | 割り当てられたプロビジョニング グループ | v21.1 以降 | wd:Worker/wd:Worker\_Data/wd:Account\_Provisioning\_Data/wd:Provisioning\_Group\_Assignment\_Data[wd:Status='Assigned']/wd:Provisioning\_Group/text() |

### サポートされている XPATH 関数

XPATH API 式の作成時に使用できる [Microsoft .NET XPATH ライブラリ](https://learn.microsoft.com/ja-jp/previous-versions/dotnet/netframework-4.0/ms256138%28v=vs.100%29)でサポートされている XPATH 関数の一覧を次に示します。

- 名前
- 最後
- 立場
- ひも
- サブスト リング
- concat
- substring-after
- starts-with
- string-length
- 含む
- 翻訳
- normalize-space
- substring-before
- ブーリアン
- ほんとう
- ない
- 偽り
- 数値
- 天井
- Sum
- 丸い
- 床
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/workday-expression-mapping-functions-guide"} -->
## Microsoft Entra ID プロビジョニングの Workday 式マッピング関数 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/workday-expression-mapping-functions-guide
- Service: entra-id / app-provisioning
- Article date: 2025-08-12
- Summary: Workday をオンプレミスの Active Directory/Microsoft Entra ID ユーザー プロビジョニングに構成するときによく使用される式マッピング関数に関する包括的なガイド。 これらの関数は、Workday からデータを変換してマップし、Microsoft Entra ID で適切なユーザー属性を作成するのに役立ちます。

この記事では、Workday をオンプレミスの Active Directory/Microsoft Entra ID ユーザー プロビジョニングに構成するときによく使用される式マッピング関数に関する包括的なガイドを提供します。 これらの関数は、Workday からデータを変換してマップし、Microsoft Entra ID で適切なユーザー属性を作成するのに役立ちます。

### 目次

- 文字列操作関数
- 電子メール アドレスの生成
- 電話番号の処理
- Active Directory のアカウント状態ロジック
- Microsoft Entra ID のアカウント状態ロジック
- 日付関数
- 組織単位 (OU) の割り当て
- ランダム ID の生成
- 名前の処理

### 文字列操作関数

#### 基本的な文字列操作

**シナリオ 1**: スペース、角かっこ、ダッシュを削除して、Workday からの電話番号の値をクリーンアップする必要があります。

**ターゲット属性**: telephoneNumber、mobile

```
Replace([PrimaryWorkTelephone], , "[()\\s-]+", , "", , )
Replace([Mobile], , "[()\\s-]+", , "", , )
```

**例：**

- **入力値**: [PrimaryWorkTelephone] = "+1 (555) 123-4567"
- **式の出力**: `+15551234567`

**シナリオ 2**: "FirstName LastName" を含む `PreferredNameData` フィールドから姓を抽出する必要があります。

**ターゲット属性**: オンプレミスの Active Directory の`sn`、Microsoft Entra ID の`surname`

```
Replace([PreferredNameData], , "(?<firstName>[a-zA-Z]+ )(?<lastName>[a-zA-Z]+)", ,"${lastName}", ,)
```

**例：**

- **入力値**: [PreferredNameData] = "John Smith"
- **式の出力**: `Smith`

**シナリオ 3**: オンプレミスの Active Directory または Microsoft Entra ID の従業員 ID と照合する前に、ワーカー ID から先頭のゼロを削除する必要があります。

**ターゲット属性**: employeeId

```
Replace([WorkerID], , "(?<leadingZeros>^0+)(?<actualValue>[a-zA-Z0-9]+)", , "${actualValue}", ,)
```

**例：**

- **入力値**: [WorkerID] = "00012345"
- **式の出力**: `12345`

**シナリオ 4**: `HereditarySuffix` 属性には、国の ISO コードでタグ付けされたサフィックス情報が含まれており、サフィックス情報のみを抽出して姓に追加する必要があります。

**ターゲット属性**: オンプレミスの Active Directory の`sn`、Microsoft Entra ID の`surname`

```
Join(" ",Replace([HereditarySuffix], ,"(?<CountryISOCode>.*)_(?<suffix1>.*)_(?<suffix2>.*)[0-9]", ,"${suffix1} ${suffix2}", , ),[PreferredLastName])
```

**例：**

- **入力値**: [SshySuffix] = "NLD\_Van\_der3", [PreferredLastName] = "Hof"
- **式の出力**: `Van der Hof`

#### テキスト の大文字と小文字の変換

**シナリオ 1**: テキストを適切なケースに変換し、アポストロフィを正しく処理する必要があります (たとえば、"st john's hospital" は "St John's Hospital" になります)。

**ターゲット属性**: company

```
Replace(PCase("st john's hospital"),"'S", , ,"'s", , )
```

**例：**

- **入力値**: 静的テキスト "st john's hospital"
- **式の出力**: `St John's Hospital`

**シナリオ 2**: 名と姓から小文字のユーザー名を作成する必要があります。

**ターゲット属性**: mailNickname

```
ToLower(NormalizeDiacritics(StripSpaces(Join(".", [PreferredFirstName], [PreferredLastName]))))
```

**例：**

- **入力値**: [PreferredFirstName] = "ホセ", [PreferredLastName] = "García-López"
- **式の出力**: `jose.garcia-lopez`

#### 国固有の名前付けロジック

**シナリオ 1**: ユーザーの国に基づいて異なる名前付け規則を適用する必要があります (たとえば、特定の国の "Last, First" など)。

**ターゲット属性**: displayName、cn

```
Switch([CountryReferenceTwoLetter], 
    Join(" ", [PreferredFirstName], [PreferredLastName]),  
    "HU", Join(",", [PreferredLastName], [PreferredFirstName]), 
    "JP", Join(",", [PreferredLastName], [PreferredFirstName]), 
    "KR", Join(",", [PreferredLastName], [PreferredFirstName])
)
```

**例：**

- **入力値**: [CountryReferenceTwoLetter] = "JP", [PreferredFirstName] = "Hiroshi", [PreferredLastName] = "Tanaka"
- **式の出力**: `Tanaka,Hiroshi` (日本語の名前付け規則)
- **代替入力**: [CountryReferenceTwoLetter] = "US", [PreferredFirstName] = "John", [PreferredLastName] = "Smith"
- **代替出力**: `John Smith` (既定の西洋の名前付け規則)

### 電子メール アドレスの生成

#### 基本的な電子メールの生成

**シナリオ 1**: 名と姓を結合し、スペースと特殊文字を削除し、ドメインを追加して、電子メール アドレスを作成する必要があります。

**ターゲット属性**: mail

```
Join("@", NormalizeDiacritics(StripSpaces(Join(".", [PreferredFirstName], [PreferredLastName]))), "contoso.com")
```

**例：**

- **入力値**: [PreferredFirstName] = "María", [PreferredLastName] = "ホセ ゴンザレス"
- **式の出力**: `maria.josegonzalez@contoso.com`

**シナリオ 2**: 電子メール アドレスを生成するときに、名前に引用符やコンマなどの特殊文字を処理する必要があります。

**ターゲット属性**: userPrincipalName

```
SelectUniqueValue(
    Join("@", Replace(NormalizeDiacritics(StripSpaces(Join(".", [PreferredFirstName], [PreferredLastName]))), , "[\"',]+", , "", , ), "contoso.com"), 
    Join("@", Replace(NormalizeDiacritics(StripSpaces(Join(".", Mid([PreferredFirstName], "1", "1"), [PreferredLastName]))), , "[\"',]+", , "", , ), "contoso.com"), 
    Join("@", Replace(NormalizeDiacritics(StripSpaces(Join(".", Mid([PreferredFirstName], "1", "2"), [PreferredLastName]))), , "[\"',]+", , "", , ), "contoso.com")
)
```

**例：**

- **入力値**: [PreferredFirstName] = "Mary-Ann", [PreferredLastName] = "O'Connor"
- **式の出力**: `maryann.oconnor@contoso.com` (最初のオプションを使用する場合は `m.oconnor@contoso.com` 、最初の 2 つが取得された場合は `ma.oconnor@contoso.com` )

#### 会社固有の電子メール ドメイン

**シナリオ 1**: 複数の会社があり、会社に基づいて異なるドメイン サフィックスを持つ電子メール アドレスを生成する必要があります。

**ターゲット属性**: mail

```
Switch([Company], 
    Join("@", NormalizeDiacritics(StripSpaces(Join(".", [FirstName], [LastName]))), "contoso.com"),
    "Contoso", Join("@", NormalizeDiacritics(StripSpaces(Join(".", [FirstName], [LastName]))), "contoso.com"),
    "Fabrikam", Join("@", NormalizeDiacritics(StripSpaces(Join(".", [FirstName], [LastName]))), "fabrikam.com"),
    "Woodgrove", Join("@", NormalizeDiacritics(StripSpaces(Join(".", Mid([FirstName],1,1), [LastName]))), "woodgrove.com")
)
```

**例：**

- **入力値**: [Company] = "Fabrikam"、[FirstName] = "John"、[LastName] = "Smith"
- **式の出力**: `john.smith@fabrikam.com`
- **代替入力**: [Company] = "Woodgrove"、[FirstName] = "Sarah"、[LastName] = "Johnson"
- **代替出力**: `s.johnson@woodgrove.com`

#### ProxyAddresses の構成

**シナリオ 1**: プライマリ SMTP アドレスとセカンダリ SMTP アドレスを含む、Exchange の複数のプロキシ アドレスを設定する必要があります。

**ターゲット属性**: proxyAddresses

```
Split(
    Join(",",
        Append("smtp:", Join("@", NormalizeDiacritics(StripSpaces(Join(".", [PreferredFirstName], [PreferredLastName]))), "contoso.mail.onmicrosoft.com")),
        Append("smtp:", Join("@", NormalizeDiacritics(StripSpaces(Join(".", [PreferredFirstName], [PreferredLastName]))), "contoso.onmicrosoft.com")),
        Append("SMTP:", Join("@", NormalizeDiacritics(StripSpaces(Join(".", [PreferredFirstName], [PreferredLastName]))), "contoso.com"))
    ), ","
)
```

**例：**

- **入力値**: [PreferredFirstName] = "Michael", [PreferredLastName] = "Brown"
- **式の出力**: `["smtp:michael.brown@contoso.mail.onmicrosoft.com", "smtp:michael.brown@contoso.onmicrosoft.com", "SMTP:michael.brown@contoso.com"]`

### 電話番号の処理

これらの式マッピングは、Workday ライトバック アプリケーションで使用できます。

#### 国際電話番号の解析

**シナリオ 1**: 国際形式の電話番号 (+1 737-626-8331) があり、国番号なしで電話番号だけを抽出する必要があります。

**ターゲット属性**: telephoneNumber

```
Replace(Replace([telephoneNumber], , "\\+(?<isdCode>\\d* )(?<phoneNumber>.*)", , "${phoneNumber}", , ), ,"[()\\s-]+", ,"", , )
```

**例：**

- **入力値**: [telephoneNumber] = "+1 737-626-8331"
- **式の出力**: `7376268331`

**シナリオ 2**: 電話番号から国コードを抽出して、ディレクトリの目的で国を決定する必要があります。

**ターゲット属性**: c

```
Switch(Replace([telephoneNumber], , "\\+(?<isdCode>\\d* )(?<phoneNumber>.*)", , "${isdCode}", , ), "USA",
  "1", "USA",
  "44", "GBR", 
  "49", "DEU"
)
```

**例：**

- **入力値**: [telephoneNumber] = "+44 20 7946 0958"
- **式の出力**: `GBR`
- **代替入力**: [telephoneNumber] = "+1 555-123-4567"
- **代替出力**: `USA`

**シナリオ 3**: Microsoft Entra ID で生成および設定Microsoft Teams電話番号を書き戻す必要があります (例: +4926180001111)。 この電話番号では、CountryCode と実際の電話番号の間にスペースがありません。 次の正規表現解析メカニズムを使用して、組織に関連する国コードを抽出し、それを使用して Workday `CountryCodeName`を設定できます。

**ターゲット属性**: CountryCodeName

```
Switch(Replace([telephoneNumber], , "\+(?<isdCode>49|44|43|1|352|91|31|32|55|237|420|45|20|212|216|234|263|27|30|33|34|351|352|36|372|380|381|383|39|40|41|421|46|47|48|58|60|7|90|91|92|94|961|971|98|995)(?<phoneNumber>.*)", , "${isdCode}", , ), , "43", "AUT", "32", "BEL", "1", "USA", "420", "CZE", "45", "DNK", "372", "EST", "33", "FRA", "49", "GER", "30", "GRC", "36", "HUN", "91", "IND", "39", "ITA", "352", "LUX", "31", "NLD", "47", "NOR", "48", "POL", "40", "ROU", "421", "SVK", "27", "ZAF", "34", "ESP", "46", "SWE", "41", "CHE", "90", "TUR")

```

**例：**

- **入力値**: [telephoneNumber] = "+493012345678"
- **式の出力**: `GER`
- **代替入力**: [telephoneNumber] = "+919876543210"
- **代替出力**: `IND`
- **代替入力**: [telephoneNumber] = "+15551234567"
- **代替出力**: `USA`

#### さまざまなシステムの電話番号の書式設定

**シナリオ 1**: 内線番号を含む電話番号を処理する必要があります (例: "+1 (206) 291-8163 x8125")。

**ターゲット属性**: telephoneNumber

```
Replace(Replace([telephoneNumber], , "\\+(?<isdCode>\\d* )(?<phoneNumber>.* )[x](?<extension>.*)", , "${phoneNumber}", , ), ,"[()\\s-]+", ,"", , )
```

**例：**

- **入力値**: [telephoneNumber] = "+1 (206) 291-8163 x8125"
- **式の出力**: `2062918163`

**シナリオ 2**: 電話番号から内線番号のみを抽出する必要がある。

**ターゲット属性**: extensionAttribute1

```
Replace([telephoneNumber], , "\\+(?<isdCode>\\d* )(?<phoneNumber>.* )[x](?<extension>.*)", , "${extension}", , )
```

**例：**

- **入力値**: [telephoneNumber] = "+1 (206) 291-8163 x8125"
- **式の出力**: `8125`

### Active Directory のアカウント状態ロジック

このセクションの式は、"Workday to on-premises Active Directory ユーザー プロビジョニング アプリ" の一部である `accountDisabled` 属性に適用されます。 "Workday to Microsoft Entra ID ユーザー プロビジョニング アプリ" の一部である `accountEnabled` 属性の設定については、「 Microsoft Entra ID のアカウントステータスロジック」セクションを参照してください。

#### 基本的なアカウントの状態管理

**シナリオ 1**: Workday でアクティブでないユーザーに対して、オンプレミスの Active Directory アカウントを無効にする必要がある。

**ターゲット属性**: accountDisabled

```
Switch([Active], , "1", "False", "0", "True")
```

**例：**

- **入力値**: [アクティブ] = "1"
- **式の出力**: `False` (アカウントが有効)
- **代替入力**: [アクティブ] = "0"
- **代替出力**: `True` (アカウントが無効)

#### 再処理

**シナリオ 1**: **シナリオ 1** が必要です。採用日以降にのみアカウントを有効にする必要がある再雇用シナリオを処理する必要があります。

**ターゲット属性**: accountDisabled

```
Switch([Active], , 
"1", IIF([StatusRehire]=1, IIF(DateDiff("d", Now(), CDate([StatusHireDate])) >= 0, "False", "True"), "False"), 
"0", "True")
```

**例：**

- **入力値**: [Active] = "1", [StatusRehire] = "1", [StatusHireDate] = "2025-08-15" (現在の日付: 2025-07-30)
- **式の出力**: `True` (採用日までアカウントが無効)
- **代替入力**: [Active] = "1"、[StatusRehire] = "1"、[StatusHireDate] = "2025-07-15"
- **代替出力**: `False` (採用日に達したアカウントが有効)

#### アカウントの事前雇用の作成

**シナリオ 1**: 将来の採用者のアカウントを作成し、開始日の 14 日前まで無効のままにします。

**ターゲット属性**: accountDisabled

```
Switch([Active], , "1", IIF(DateDiff("d", Now(), CDate([StatusHireDate])) <= 14, "False", "True"), "0", "True")
```

**例：**

- **入力値**: [Active] = "1",[StatusHireDate] = "2025-08-10" (現在の日付: 2025-07-30)
- **式の出力**: `False` (採用日が 14 日以内に有効になっているアカウント)
- **代替入力**: [Active] = "1"、[StatusHireDate] = "2025-09-15"
- **代替出力**: `True` (雇用日が 14 日を超える場合はアカウントが無効になります)

#### 採用の取り消しの処理

**シナリオ 1**: `accountDisabled` 属性を設定するときに、採用の取り消しシナリオを処理する必要があります。 次のロジックを実装する必要があります。

- Workday で Terminated = 1 の場合、accountDisabled = True
- Workday で Rescinded = 1 の場合、accountDisabled = True
- 稼働日にアクティブ =1 の場合は、次の操作を行います。
    - もし
        - HireDate は 7 日以上後に accountDisabled = True (アカウントを無効にする)
        - HireDate は &lt;= 7 日後、accountDisabled = False (アカウントを有効にする) です
- Active = 0 の場合、accountDisabled = True

**ターゲット属性**: accountDisabled

```
Switch([StatusTerminated], "False", "1", "True", "0",
  Switch([StatusHireRescinded], "False", "1", "True", "0",
     Switch([Active], "False", 
         "1", IIF(DateDiff("d", Now(), CDate(IIF(IsNullOrEmpty([StatusHireDate]), "9999-01-01", [StatusHireDate]))) < 7, "False", "True"), 
         "0", "True"
         )
    )
)
```

**例：**

- **入力値**: [Terminated] = "1", [Active] = "1", [StatusHireDate] = "2025-08-15"
- **式の出力**: `True` (終了によりアカウントが無効)
- **代替入力**: [Terminated] = "0"、[Rescinded] = "1"、[Active] = "1"
- **代替出力**: `True` (取り消しによりアカウントが無効)
- **代替入力**: [Terminated] = "0", [Rescinded] = "0", [Active] = "1", [StatusHireDate] = "2025-08-15" (現在の日付: 2025-07-30)
- **代替出力**: `True` (雇用日が 7 日を超える場合はアカウントが無効になります)
- **代替入力**: [Terminated] = "0", [Rescinded] = "0", [Active] = "1", [StatusHireDate] = "2025-08-05"
- **代替出力**: `False` (採用日が 7 日以内に有効になっているアカウント)

### Microsoft Entra ID のアカウント状態ロジック

このセクションの式は、"Workday to Microsoft Entra ID ユーザー プロビジョニング アプリ" の一部である `accountEnabled` 属性に適用されます。 "Workday からオンプレミス Active Directory ユーザー プロビジョニング アプリ" の一部である `accountDisabled` 属性の設定については、「 Active Directory のアカウント状態ロジック」セクションを参照してください。

#### 基本的なアカウントの状態管理

**シナリオ 1**: Workday でアクティブでないユーザーに対して Microsoft Entra ID アカウントを無効にする必要がある。

**ターゲット属性**: accountEnabled

```
Switch([Active], , "1", "True", "0", "False")
```

**例：**

- **入力値**: [アクティブ] = "1"
- **式の出力**: `True` (アカウントが有効)
- **代替入力**: [アクティブ] = "0"
- **代替出力**: `False` (アカウントが無効)

#### 再処理

**シナリオ 1**: 採用日以降にのみアカウントを有効にする必要がある再雇用シナリオを処理する必要があります。

**ターゲット属性**: accountEnabled

```
Switch([Active], , 
"1", IIF([StatusRehire]=1, IIF(DateDiff("d", Now(), CDate([StatusHireDate])) >= 0, "True", "False"), "True"), 
"0", "False")
```

**例：**

- **入力値**: [Active] = "1", [StatusRehire] = "1", [StatusHireDate] = "2025-08-15" (現在の日付: 2025-07-30)
- **式の出力**: `False` (採用日までアカウントが無効)
- **代替入力**: [Active] = "1"、[StatusRehire] = "1"、[StatusHireDate] = "2025-07-15"
- **代替出力**: `True` (採用日に達したアカウントが有効)

#### アカウントの事前雇用の作成

**シナリオ 1**: 将来の採用者のアカウントを作成し、開始日の 14 日前まで無効のままにします。

**ターゲット属性**: accountEnabled

```
Switch([Active], , "1", IIF(DateDiff("d", Now(), CDate([StatusHireDate])) <= 14, "True", "False"), "0", "False")
```

**例：**

- **入力値**: [Active] = "1",[StatusHireDate] = "2025-08-10" (現在の日付: 2025-07-30)
- **式の出力**: `True` (採用日が 14 日以内に有効になっているアカウント)
- **代替入力**: [Active] = "1"、[StatusHireDate] = "2025-09-15"
- **代替出力**: `False` (雇用日が 14 日を超える場合はアカウントが無効になります)

### 日付関数

#### 日付の書式設定と変換

**シナリオ 1**: accountExpires 属性の Workday コントラクト終了日を Active Directory 形式に変換して、契約終了日にアカウントの有効期限が切れる必要があります。

**ターゲット属性**: accountExpires

```
NumFromDate(Join("", FormatDateTime([ContractEndDate], ,"yyyy-MM-ddzzz", "yyyy-MM-dd"), "T23:59:59-08:00"))
```

**例：**

- **入力値**: [StatusHireDate] = "2025-12-31"
- **式の出力**: `133835135990000000` (2025-12-31T23:59:59-07:00 の数値表現)

**シナリオ 2**: 採用日から 5 年後にアカウントの有効期限を設定する必要があります。

**ターゲット属性**: accountExpires

```
NumFromDate(Join("",FormatDateTime(DateAdd("yyyy", 5, CDate([StatusHireDate])), , "yyyy-MM-dd", "yyyy-MM-dd")," 23:59:59-05:00"))
```

**例：**

- **入力値**: [StatusHireDate] = "2025-01-15"
- **式の出力**: `139418879990000000` (2030-01-15 23:59:59-05:00 の数値表現)

#### 条件付き日付ベースのロジック

**シナリオ 1**: 従業員が作業を開始した場合 (採用日が過ぎた) 場合にのみ、部門情報をフローさせる必要があります。

**ターゲット属性**: department

```
IIF(DateDiff("d", Now(), CDate([StatusHireDate])) >= 0, [Department], IgnoreAttributeFlow)
```

**例：**

- **入力値**: [StatusHireDate] = "2025-07-15"、[Department] = "Engineering" (現在の日付: 2025-07-30)
- **式の出力**: `Engineering` (採用日が経過しました)
- **代替入力**: [StatusHireDate] = "2025-08-15", [Department] = "Marketing"
- **代替出力**: `IgnoreAttributeFlow` (採用日は将来)

**シナリオ 2**: 雇用日が 14 日以内の場合にのみ、ユーザー オブジェクトを作成する必要があります。

**ターゲット属性**: objectFilter

```
IIF(DateDiff("d", Now(), CDate([StatusHireDate])) <= 14, "False", IgnoreObjectFlow)
```

**例：**

- **入力値**: [StatusHireDate] = "2025-08-10" (現在の日付: 2025-07-30)
- **式の出力**: `False` (雇用日が 14 日以内である場合にユーザー オブジェクトを作成する)
- **代替入力**: [StatusHireDate] = "2025-09-15"
- **代替出力**: `IgnoreObjectFlow` (雇用日が 14 日を超えるので、ユーザー オブジェクトを作成しないでください)

### 組織単位 (OU) の割り当て

#### 単純な OU の割り当て

**シナリオ 1**: ユーザーを都市に基づいて異なる OU に配置する必要がある。

**ターゲット属性**: parentDistinguishedName

```
Switch([City], "OU=Default,OU=Users,DC=contoso,DC=com", 
"Dallas", "OU=Dallas,OU=Users,DC=contoso,DC=com", 
"Austin", "OU=Austin,OU=Users,DC=contoso,DC=com", 
"Seattle", "OU=Seattle,OU=Users,DC=contoso,DC=com", 
"London", "OU=London,OU=Users,DC=contoso,DC=com" 
)
```

**例：**

- **入力値**: [City] = "Seattle"
- **式の出力**: `OU=Seattle,OU=Users,DC=contoso,DC=com`
- **代替入力**: [City] = "Chicago"
- **代替出力**: `OU=Default,OU=Users,DC=contoso,DC=com` (未指定の都市の既定値)

#### 複雑な OU 構造

**シナリオ 1**: 部門、コスト センター、国に基づいて複雑な OU 構造を作成する必要があります。

**ターゲット属性**: parentDistinguishedName

```
Join("", 
    Switch([SupervisoryOrganization],"",
        "Engineering", "OU=Engineering,",
        "Shared Services", "OU=Shared Services,",
        "Information Technology", "OU=Information Technology,",
        "Development", "OU=Development,"
        ),
    Switch([CostCenter],"",
        "Finance and Info. Mgmt.","OU=Finance and Information Management,",
        "Modern Workplace","OU=Modern Workplace,",
        "Green Energy","OU=Green Energy,"
        ),
    Switch([CountryReferenceTwoLetter],"",
        "US","OU=USA,",
        "UK","OU=UK,",
        "IN","OU=IN,"
        ),
    "OU=Users,DC=contoso,DC=com"
)
```

**例：**

- **入力値**: [SupervisoryOrganization] = "Engineering", [CostCenter] = "Modern Workplace", [CountryReferenceTwoLetter] = "US"
- **式の出力**: `OU=Engineering,OU=Modern Workplace,OU=USA,OU=Users,DC=contoso,DC=com`
- **代替入力**: [SupervisoryOrganization] = "Sales"、[CostCenter] = "Marketing"、[CountryReferenceTwoLetter] = "UK"
- **代替出力**: `OU=Users,DC=contoso,DC=com` (値が一致しない場合の既定値)

#### 終了したユーザー OU の割り当て

**シナリオ 1**: 終了日時に、終了したユーザーを特別な OU に移動する必要があります。

**ターゲット属性**: parentDistinguishedName

```
IIF(DateDiff("d", Now(),CDate(Switch([StatusTerminationLastDayOfWork],[StatusTerminationLastDayOfWork],
        "","9999-12-31"
        ))
    ) <= 0, 
    "OU=Leavers,OU=Users,DC=contoso,DC=com", 
    Switch([City], "OU=Default,OU=Users,DC=contoso,DC=com", 
        "Dallas", "OU=Dallas,OU=Users,DC=contoso,DC=com", 
        "Austin", "OU=Austin,OU=Users,DC=contoso,DC=com", 
        "Seattle", "OU=Seattle,OU=Users,DC=contoso,DC=com", 
        "London", "OU=London,OU=Users,DC=contoso,DC=com" 
    ) 
)
```

**例：**

- **入力値**: [StatusTerminationLastDayOfWork] = "2025-07-25", [City] = "Seattle" (現在の日付: 2025-07-30)
- **式の出力**: `OU=Leavers,OU=Users,DC=contoso,DC=com` (終了日が経過すると Leavers OU に移動)
- **代替入力**: [StatusTerminationLastDayOfWork] = "2025-08-15", [City] = "Dallas"
- **代替出力**: `OU=Dallas,OU=Users,DC=contoso,DC=com` (終了日が将来であるため、通常の OU に残ります)

### ランダム ID の生成

#### GUID ベースのランダム生成

**シナリオ 1**: 数字を含まないランダムな 5 文字の文字列を生成する必要があります。

**ターゲット属性**: extensionAttribute15

```
SelectUniqueValue (
   Replace(Mid(ConvertToBase64(Guid()), 1, 5 ), ,"[0-9]", ,"A", , ),
   Replace(Mid(ConvertToBase64(Guid()), 1, 5 ), ,"[0-9]", ,"B", , ),
   Replace(Mid(ConvertToBase64(Guid()), 1, 5 ), ,"[0-9]", ,"C", , )
)
```

**例：**

- **入力値**: 生成された GUID が Base64 に変換されました (例: "mV8dXr...")
- **式の出力**: `mVAdX` (最初のオプションを使用した場合は、'A' または 'B'/'C' に置き換えられた数字)

**シナリオ 2**: "D" で始まり、その後に 4 文字の英字で始まるランダムな文字列を生成する必要がある。

**ターゲット属性**: extensionAttribute14

```
SelectUniqueValue(
    ToUpper(Replace(ConvertToBase64(Guid()), , "(.*?)(?<id>[a-zA-Z]{4})(.*)", , "D${id}", ,)),
    ToUpper(Replace(ConvertToBase64(Guid()), , "(.*?)(?<id>[a-zA-Z]{4})(.*)", , "D${id}", ,)),
    ToUpper(Replace(ConvertToBase64(Guid()), , "(.*?)(?<id>[a-zA-Z]{4})(.*)", , "D${id}", ,))
)
```

**例：**

- **入力値**: 生成された GUID が、アルファベット順 "mVdX" を含む Base64 に変換されました
- **式の出力**: `DMVDX` (または最初のオプションを使用した場合は異なる)

#### 数値 ID の生成

**シナリオ 1**: GUID からランダムな 4 桁の数字を生成する必要があります。

**ターゲット属性**: extensionAttribute13

```
SelectUniqueValue(
    Replace(Replace(Guid(), ,"-", ,"", , ), ,"(.*?)(?<id>[0-9]{4})(.*)", , "D${id}", ,),
    Replace(Replace(Guid(), ,"-", ,"", , ), ,"(.*?)(?<id>[0-9]{4})(.*)", , "D${id}", ,),
    Replace(Replace(Guid(), ,"-", ,"", , ), ,"(.*?)(?<id>[0-9]{4})(.*)", , "D${id}", ,)
)
```

**例：**

- **入力値**: "a1b2c3d4-e5f6-7890-1234-567890abcdef" のような生成された GUID
- **式の出力**: `D7890` (最初に一致する 4 桁のシーケンスに応じて、 `D1234`、 `D5678`など)

### 名前の処理

#### 表示名の生成

**シナリオ 1**: "Last, First" 形式で表示名を作成します。

**ターゲット属性**: displayName

```
Join(", ", [PreferredLastName], [PreferredFirstName])
```

**例：**

- **入力値**: [PreferredLastName] = "Smith", [PreferredFirstName] = "John"
- **式の出力**: `Smith, John`

**シナリオ 2**: 中央の初期 ID と従業員 ID を含む表示名を作成する必要があります。

**ターゲット属性**: displayName

```
Join("", [PreferredLastName], ",", [PreferredFirstName], " ", Mid([PreferredMiddleName],1,1), "-", [WorkerID])
```

**例：**

- **入力値**: [PreferredLastName] = "Johnson", [PreferredFirstName] = "Sarah", [PreferredMiddleName] = "Elizabeth", [WorkerID] = "12345"
- **式の出力**: `Johnson,Sarah E-12345`

#### 一意性を持つ共通名 (CN) の生成

**シナリオ 1**: 重複のフォールバック オプションを使用して、一意の共通名を生成する必要があります。

**ターゲット属性**: cn

```
SelectUniqueValue(
    NormalizeDiacritics(Join(" ", [PreferredFirstName], [PreferredLastName])),
    NormalizeDiacritics(Join(" ", [PreferredFirstName], Mid([PreferredMiddleName],1,1), [PreferredLastName])),
    NormalizeDiacritics(Join(" ", [PreferredFirstName], [PreferredMiddleName], [PreferredLastName]))
)
```

**例：**

- **入力値**: [PreferredFirstName] = "ホセ", [PreferredLastName] = "García", [PreferredMiddleName] = "Antonio"
- **式の出力**: `Jose Garcia` (最初のオプションを使用する場合は `Jose A Garcia` 、最初の 2 つが取得された場合は `Jose Antonio Garcia` )

#### SamAccountName の生成

**シナリオ 1**: 最初の最初の名前と姓を使用して、重複する数字のサフィックスを持つ 20 文字の samAccountName を作成する必要があります。

**ターゲット属性**: sAMAccountName

```
SelectUniqueValue(
    Replace(Mid(Replace(NormalizeDiacritics(StripSpaces(Join("", Mid([FirstName],1,1), [LastName]))), , "([\\/\\\\\\[\\]\\:\\;\\|\\=\\,\\+\\*\\?\\<\\>])", , "", , ), 1, 20), , "(\\.)*$", , "", , ),
    Join("",Replace(Mid(Replace(NormalizeDiacritics(StripSpaces(Join("", Mid([FirstName],1,1), [LastName]))), , "([\\/\\\\\\[\\]\\:\\;\\|\\=\\,\\+\\*\\?\\<\\>])", , "", , ), 1, 19), , "(\\.)*$", , "", , ),"1"),
    Join("",Replace(Mid(Replace(NormalizeDiacritics(StripSpaces(Join("", Mid([FirstName],1,1), [LastName]))), , "([\\/\\\\\\[\\]\\:\\;\\|\\=\\,\\+\\*\\?\\<\\>])", , "", , ), 1, 19), , "(\\.)*$", , "", , ),"2")
)
```

**例：**

- **入力値**: [FirstName] = "María José", [LastName] = "González-López"
- **式の出力**: `mgonzalezlopez` (最初のオプションを使用する場合は `mgonzalezlopez1` 、最初の 2 つが取得された場合は `mgonzalezlopez2` )

### 高度なシナリオ

#### アドレス一覧のロジックから非表示にする

このセクションでは、ブール値属性の `msExchHideFromAddressLists`を設定する方法について説明します。 ブール値属性を設定するには、すべての大文字 "TRUE" または "FALSE" を使用します。 その他の値を使用すると、 `HybridSynchronizationActiveDirectoryInvalidParameter` エラーが発生します。

**シナリオ 1**: Workday ユーザーのアクティブなアカウントの状態に基づいて `msExchHideFromAddressLists` を設定する必要があります。

**ターゲット属性**: msExchHideFromAddressLists

```
Switch([Active], , "1", "FALSE", "0", "TRUE")
```

**例：**

- **入力値**: [アクティブ] = "0"
- **式の出力**: `TRUE` (ユーザーが Workday で非アクティブであるため、アドレス一覧から非表示)
- **代替入力**: [アクティブ] = "1"
- **代替出力**: `FALSE` (ユーザーが Workday でアクティブになっているとアドレス一覧に表示)

**シナリオ 2**: 作業者の雇用日に基づいて `msExchHideFromAddressLists` を設定します。 採用日の後にのみ、Exchange アドレス一覧にユーザーを表示します。

**ターゲット属性**: msExchHideFromAddressLists

```
IIF(DateDiff("d", Now(), CDate([StatusHireDate])) >= 0, "TRUE", "FALSE")
```

**例：**

- **入力値**: [StatusHireDate] = "2025-07-31" (現在の日付: 2025-07-30)
- **式の出力**: `TRUE` (雇用日が将来の住所一覧から非表示になります)
- **代替入力**: [StatusHireDate] = "2025-07-31" (現在の日付: 2025-08-01)
- **代替出力**: `FALSE` (採用日が過去の場合は住所一覧に表示)

#### 複数値属性の設定

**シナリオ 1**: Active Directory で msExchPoliciesExcluded 属性に複数の値を設定する必要があります。

**ターゲット属性**: msExchPoliciesExcluded

```
Split(
    Join(",","a8cccada-a108-47ae-bf9a-f130499aa4cb","{26491cfc-9e50-4857-861b-0cb8df22b5d7}"),
    ","
)
```

**例：**

- **入力値**: 静的 GUID 値
- **式の出力**: `["a8cccada-a108-47ae-bf9a-f130499aa4cb", "{26491cfc-9e50-4857-861b-0cb8df22b5d7}"]`

#### 書き戻しのシナリオ

**シナリオ 1**: 従業員の雇用日が過ぎた場合にのみ、ユーザー名を Workday に書き戻す必要があります。

**ターゲット属性**: ユーザー名

```
IgnoreFlowIfNullOrEmpty(IIF(DateDiff("d", Now(), CDate([employeeHireDate])) > 0, "", [userPrincipalName]))
```

**例：**

- **入力値**: [employeeHireDate] = "2025-07-15", [userPrincipalName] = "user@contoso.com" (現在の日付: 2025-07-30)
- **式の出力**: `user@contoso.com` (採用日が経過し、ユーザー名が書き戻されました)
- **代替入力**: [employeeHireDate] = "2025-08-15", [userPrincipalName] = "future@contoso.com"
- **代替出力**: *(空 - 無視、採用日は将来)*

### ベスト プラクティス

- 一意性を必要とする属性 (UPN、samAccountName、電子メールなど) には、**常に SelectUniqueValue を使用**します。
- `IsNullOrEmpty`、`IsPresent`、`Switch` ステートメントなどの関数を使用して**、null 値と空の値を処理**します。
- 互換性を確保するために、特殊文字で名前を処理する場合は **NormalizeDiacritics を使用**します。
- 異なるタイム ゾーンと日付形式が結果に影響を与える可能性があり、日付**ロジックを十分にテスト**します。
- 空の値の属性の更新をスキップする場合は、**IgnoreFlowIfNullOrEmpty を使用**します。
- 読みやすくするために**、入れ子になった IIF ステートメントの代わりに Switch を使用することを検討してください**。
- **実装する前に、必ず** オンライン正規表現テスターで正規表現を検証してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/workday-integration-reference"} -->
## Microsoft Entra ID と Workday の統合のリファレンス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/workday-integration-reference
- Service: entra-id / app-provisioning
- Article date: 2026-08-20
- Summary: Microsoft Entra ID での Workday による人事主導のプロビジョニングに関する技術的な詳細

[Microsoft Entra ユーザー プロビジョニング サービスは](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)[Workday HCM](https://www.workday.com) と統合され、ユーザーの ID ライフ サイクルを管理します。 Microsoft Entra ID では、次の 3 つの統合があらかじめ構築されています。

- [Workday からオンプレミスの Active Directory ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial)
- [Workday から Microsoft Entra へのユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-cloud-only-tutorial)
- [Workday の書き戻し](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-writeback-tutorial)

この記事では、統合のしくみと、さまざまな HR シナリオでプロビジョニング動作をカスタマイズする方法について説明します。

Workday 受信プロビジョニングの場合、Workday が null または空の値を返したときにマップされたターゲット属性をクリアするように、オプションの単一値ソース属性を構成できます。 構成と検証のガイダンスについては、「 [属性値のクリア (プレビュー)」](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/clear-attribute-values)を参照してください。

### 接続の確立

#### Microsoft Entra エンドポイントへの Workday API アクセスを制限する

Microsoft Entra プロビジョニング サービスでは、基本認証を使用して Workday Web サービス API エンドポイントに接続します。

Microsoft Entra プロビジョニング サービスと Workday の間の接続をさらにセキュリティで保護するため、指定された統合システム ユーザーが許可された Microsoft Entra の IP 範囲からのみ Workday API にアクセスするように、アクセスを制限できます。 Workday 管理者と協力しながら、Workday テナントで次の構成を完了します。

1. Azure パブリック クラウドの [最新の IP 範囲](https://www.microsoft.com/download/details.aspx?id=56519) をダウンロードします。
2. そのファイルを開き、タグ `AzureActiveDirectory` を検索します。
3. 要素 *addressPrefixes* 内に一覧表示されているすべての IP アドレス範囲をコピーし、その範囲を使用して IP アドレス一覧を作成します。
4. Workday 管理ポータルにサインインします。
5. **[IP 範囲の管理]** タスクにアクセスして、Azure データ センター用の新しい IP 範囲を作成します。 IP 範囲 (CIDR 表記を使用) をコンマ区切りのリストとして指定します。
6. **認証ポリシーの管理**タスクにアクセスして、新しい認証ポリシーを作成します。 認証ポリシーで、認証許可リストを使用して、Microsoft Entra の IP 範囲と、この IP 範囲からのアクセスを許可するセキュリティ グループを指定します。 変更を保存します。
7. [ **すべての保留中の認証ポリシー変更のアクティブ化]** タスクにアクセスして、変更を確認します。

#### 制約付きセキュリティ グループを使用して Workday のワーカー データへのアクセスを制限する

[Workday 統合システム ユーザーを構成](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#configure-integration-system-user-in-workday)するための既定の手順では、Workday テナント内のすべてのユーザーを取得するためのアクセス権が付与されます。 特定の統合シナリオでは、アクセスを制限することができます。 たとえば、`Get_Workers` API 呼び出しからは、特定の監督組織内のユーザーのみを返します。

Workday 管理者と協力して制約付きの統合システム セキュリティ グループを構成することで、アクセスを制限することができます。 詳細については、「Workday ドキュメントの概念: [Get Workers SOAP Web Service Guidelines](https://resourcecenter.workday.com/en-us/signin.html?fromURI=https://signin.resourcecenter.workday.com/app/workdayciam_aembetadoc2_1/exkd1j067lBdQMGYl4x7/sso/saml) (*この記事では Workday コミュニティ アクセスが必要*)」のコンテキスト セキュリティGet\_Workersセクションを参照してください。

このように制約付きの ISSG (統合システム セキュリティ グループ) を使用してアクセスを制限する戦略は、次のシナリオで役立ちます。

- **段階的ロールアウト シナリオ**: 大規模な Workday テナントがあり、Workday から Microsoft Entra ID への自動プロビジョニングの段階的なロールアウトを実行する予定です。 このシナリオでは、Microsoft Entra ID スコープ フィルターを使用して現在のフェーズのスコープに含まれないユーザーを除外するのではなく、スコープ内のワーカーだけが Microsoft Entra ID に表示されるように制約付きの ISSG を構成することをお勧めします。
- **複数のプロビジョニング ジョブシナリオ**: 大規模な Workday テナントと、それぞれ異なる部署/部門/会社をサポートする複数の AD ドメインがあります。 このトポロジをサポートするには、複数の Workday から Microsoft Entra へのプロビジョニング ジョブを実行し、各ジョブで特定のワーカー セットをプロビジョニングします。 このシナリオでは、Microsoft Entra ID スコープ フィルターを使用してワーカー データを除外するのではなく、関連するワーカー データだけが Microsoft Entra ID に表示されるように制約付き ISSG を構成することをお勧めします。

#### Workday テスト接続クエリ

Workday への接続をテストするために、Microsoft Entra ID は次の *Get\_Workers* Workday Web サービス要求を送信します。

```xml
<!-- Test connection query tries to retrieve one record from the first page -->
<!-- Replace version with Workday Web Services version present in your connection URL -->
<!-- Replace timestamps with the UTC time corresponding to the test connection event -->
<Get_Workers_Request p1:version="v21.1" xmlns:p1="urn:com.workday/bsvc" xmlns="urn:com.workday/bsvc">
  <p1:Request_Criteria>
    <p1:Transaction_Log_Criteria_Data>
      <p1:Transaction_Date_Range_Data>
        <p1:Updated_From>2021-01-19T02:28:50.1491022Z</p1:Updated_From>
        <p1:Updated_Through>2021-01-19T02:28:50.1491022Z</p1:Updated_Through>
      </p1:Transaction_Date_Range_Data>
    </p1:Transaction_Log_Criteria_Data>
    <p1:Exclude_Employees>true</p1:Exclude_Employees>
    <p1:Exclude_Contingent_Workers>true</p1:Exclude_Contingent_Workers>
    <p1:Exclude_Inactive_Workers>true</p1:Exclude_Inactive_Workers>
  </p1:Request_Criteria>
  <p1:Response_Filter>
    <p1:As_Of_Effective_Date>2021-01-19T02:28:50.1491022Z</p1:As_Of_Effective_Date>
    <p1:As_Of_Entry_DateTime>2021-01-19T02:28:50.1491022Z</p1:As_Of_Entry_DateTime>
    <p1:Page>1</p1:Page>
    <p1:Count>1</p1:Count>
  </p1:Response_Filter>
  <p1:Response_Group>
    <p1:Include_Reference>1</p1:Include_Reference>
    <p1:Include_Personal_Information>1</p1:Include_Personal_Information>
  </p1:Response_Group>
</Get_Workers_Request>
```

### 完全同期のしくみ

Workday ドリブン プロビジョニングのコンテキストでの**完全同期**とは、Workday からすべての ID をフェッチし、各 worker オブジェクトに適用するプロビジョニング規則を決定するプロセスを指します。 完全同期は、初めてプロビジョニングを有効にした場合、および Microsoft Entra 管理センターまたは Graph API を使用して *プロビジョニングを再開* したときにも発生します。

Microsoft Entra ID は、ワーカー データを取得するために Workday Web サービス要求 *Get\_Workers* 次の要求を送信します。 このクエリでは、Workday トランザクション ログから、完全同期の実行に対応する時点の有効日が指定されたすべてのワーカー エントリが検索されます。

```xml
<!-- Workday full sync query -->
<!-- Replace version with Workday Web Services version present in your connection URL -->
<!-- Replace timestamps with the UTC time corresponding to full sync run -->
<!-- Count specifies the number of records to return in each page -->
<!-- Response_Group flags derived from provisioning attribute mapping -->

<Get_Workers_Request p1:version="v21.1" xmlns:p1="urn:com.workday/bsvc" xmlns="urn:com.workday/bsvc">
  <p1:Request_Criteria>
    <p1:Transaction_Log_Criteria_Data>
      <p1:Transaction_Type_References>
        <p1:Transaction_Type_Reference>
          <p1:ID p1:type="Business_Process_Type">Hire Employee</p1:ID>
        </p1:Transaction_Type_Reference>
        <p1:Transaction_Type_Reference>
          <p1:ID p1:type="Business_Process_Type">Contract Contingent Worker</p1:ID>
        </p1:Transaction_Type_Reference>
      </p1:Transaction_Type_References>
    </p1:Transaction_Log_Criteria_Data>
  </p1:Request_Criteria>
  <p1:Response_Filter>
    <p1:As_Of_Effective_Date>2021-01-19T02:29:16.0094202Z</p1:As_Of_Effective_Date>
    <p1:As_Of_Entry_DateTime>2021-01-19T02:29:16.0094202Z</p1:As_Of_Entry_DateTime>
    <p1:Count>30</p1:Count>
  </p1:Response_Filter>
  <p1:Response_Group>
    <p1:Include_Reference>1</p1:Include_Reference>
    <p1:Include_Personal_Information>1</p1:Include_Personal_Information>
    <p1:Include_Employment_Information>1</p1:Include_Employment_Information>
    <p1:Include_Organizations>1</p1:Include_Organizations>
    <p1:Exclude_Organization_Support_Role_Data>1</p1:Exclude_Organization_Support_Role_Data>
    <p1:Exclude_Location_Hierarchies>1</p1:Exclude_Location_Hierarchies>
    <p1:Exclude_Cost_Center_Hierarchies>1</p1:Exclude_Cost_Center_Hierarchies>
    <p1:Exclude_Company_Hierarchies>1</p1:Exclude_Company_Hierarchies>
    <p1:Exclude_Matrix_Organizations>1</p1:Exclude_Matrix_Organizations>
    <p1:Exclude_Pay_Groups>1</p1:Exclude_Pay_Groups>
    <p1:Exclude_Regions>1</p1:Exclude_Regions>
    <p1:Exclude_Region_Hierarchies>1</p1:Exclude_Region_Hierarchies>
    <p1:Exclude_Funds>1</p1:Exclude_Funds>
    <p1:Exclude_Fund_Hierarchies>1</p1:Exclude_Fund_Hierarchies>
    <p1:Exclude_Grants>1</p1:Exclude_Grants>
    <p1:Exclude_Grant_Hierarchies>1</p1:Exclude_Grant_Hierarchies>
    <p1:Exclude_Business_Units>1</p1:Exclude_Business_Units>
    <p1:Exclude_Business_Unit_Hierarchies>1</p1:Exclude_Business_Unit_Hierarchies>
    <p1:Exclude_Programs>1</p1:Exclude_Programs>
    <p1:Exclude_Program_Hierarchies>1</p1:Exclude_Program_Hierarchies>
    <p1:Exclude_Gifts>1</p1:Exclude_Gifts>
    <p1:Exclude_Gift_Hierarchies>1</p1:Exclude_Gift_Hierarchies>
    <p1:Include_Management_Chain_Data>1</p1:Include_Management_Chain_Data>
    <p1:Include_Transaction_Log_Data>1</p1:Include_Transaction_Log_Data>
    <p1:Include_Additional_Jobs>1</p1:Include_Additional_Jobs>
  </p1:Response_Group>
</Get_Workers_Request>
```

*Response\_Group* ノードは、Workday からフェッチするワーカー属性を指定するために使用されます。 *Response\_Group* ノードの各フラグの説明については、Workday [Get_Workers API のドキュメントを参照してください](https://community.workday.com/sites/default/files/file-hosting/productionapi/Human_Resources/v35.2/Get_Workers.html#Worker_Response_GroupType)。

*Response\_Group* ノードで指定された特定のフラグ値は、Workday Microsoft Entra プロビジョニング アプリケーションで構成された属性に基づいて計算されます。 フラグ値の設定に使用する条件については、 *サポートされているエンティティ* に関するセクションを参照してください。

上記のクエリに対する Workday からの *Get\_Workers* 応答には、worker レコードの数とページ数が含まれます。

```xml
  <wd:Response_Results>
    <wd:Total_Results>509</wd:Total_Results>
    <wd:Total_Pages>17</wd:Total_Pages>
    <wd:Page_Results>30</wd:Page_Results>
    <wd:Page>1</wd:Page>
  </wd:Response_Results>
```

結果セットの次のページを取得するために、次の *Get\_Workers* クエリでは *、Response\_Filter*のパラメーターとしてページ番号を指定します。

```xml
  <p1:Response_Filter>
    <p1:As_Of_Effective_Date>2021-01-19T02:29:16.0094202Z</p1:As_Of_Effective_Date>
    <p1:As_Of_Entry_DateTime>2021-01-19T02:29:16.0094202Z</p1:As_Of_Entry_DateTime>
    <p1:Page>2</p1:Page>
    <p1:Count>30</p1:Count>
  </p1:Response_Filter>
```

完全同期中に、Microsoft Entra プロビジョニング サービスによって各ページが処理され、すべての有効なワーカーが反復処理されます。Workday からインポートされたワーカー エントリごとに、次のことが行われます。

- [XPATH 式](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/workday-attribute-reference)は、Workday から属性値を取得するために適用されます。
- 属性マッピングと照合ルールが適用されます。
- サービスによって、ターゲット (Microsoft Entra ID/Active Directory) で実行する操作が決まります。

処理が完了すると、完全同期の開始に関連付けられているタイムスタンプがウォーターマークとして保存されます。 このウォーターマークは、増分同期サイクルの開始点として使用されます。

### 増分同期のしくみ

完全同期の後、Microsoft Entra プロビジョニング サービスによって `LastExecutionTimestamp` が保持され、それを使用して増分変更を取得するためのデルタ クエリが作成されます。 増分同期中は、次の種類のクエリが Microsoft Entra ID から Workday に送信されます。

- 手動更新のクエリ
- 有効な日付の更新と終了のクエリ
- 将来の日付の採用者のクエリ

#### 手動更新に対するクエリ

次 *のGet\_Workers* は、前回の実行から現在の実行時間の間に発生した手動更新のクエリを要求します。

```xml
<!-- Workday incremental sync query for manual updates -->
<!-- Replace version with Workday Web Services version present in your connection URL -->
<!-- Replace timestamps with the UTC time corresponding to last execution and current execution time -->
<!-- Count specifies the number of records to return in each page -->
<!-- Response_Group flags derived from provisioning attribute mapping -->

<Get_Workers_Request p1:version="v21.1" xmlns:p1="urn:com.workday/bsvc" xmlns="urn:com.workday/bsvc">
  <p1:Request_Criteria>
    <p1:Transaction_Log_Criteria_Data>
      <p1:Transaction_Date_Range_Data>
        <p1:Updated_From>2021-01-19T02:29:16.0094202Z</p1:Updated_From>
        <p1:Updated_Through>2021-01-19T02:49:06.290136Z</p1:Updated_Through>
      </p1:Transaction_Date_Range_Data>
    </p1:Transaction_Log_Criteria_Data>
  </p1:Request_Criteria>
  <p1:Response_Filter>
    <p1:As_Of_Effective_Date>2021-01-19T02:49:06.290136Z</p1:As_Of_Effective_Date>
    <p1:As_Of_Entry_DateTime>2021-01-19T02:49:06.290136Z</p1:As_Of_Entry_DateTime>
    <p1:Count>30</p1:Count>
  </p1:Response_Filter>
  <p1:Response_Group>
    <p1:Include_Reference>1</p1:Include_Reference>
    <p1:Include_Personal_Information>1</p1:Include_Personal_Information>
    <p1:Include_Employment_Information>1</p1:Include_Employment_Information>
    <p1:Include_Organizations>1</p1:Include_Organizations>
    <p1:Exclude_Organization_Support_Role_Data>1</p1:Exclude_Organization_Support_Role_Data>
    <p1:Exclude_Location_Hierarchies>1</p1:Exclude_Location_Hierarchies>
    <p1:Exclude_Cost_Center_Hierarchies>1</p1:Exclude_Cost_Center_Hierarchies>
    <p1:Exclude_Company_Hierarchies>1</p1:Exclude_Company_Hierarchies>
    <p1:Exclude_Matrix_Organizations>1</p1:Exclude_Matrix_Organizations>
    <p1:Exclude_Pay_Groups>1</p1:Exclude_Pay_Groups>
    <p1:Exclude_Regions>1</p1:Exclude_Regions>
    <p1:Exclude_Region_Hierarchies>1</p1:Exclude_Region_Hierarchies>
    <p1:Exclude_Funds>1</p1:Exclude_Funds>
    <p1:Exclude_Fund_Hierarchies>1</p1:Exclude_Fund_Hierarchies>
    <p1:Exclude_Grants>1</p1:Exclude_Grants>
    <p1:Exclude_Grant_Hierarchies>1</p1:Exclude_Grant_Hierarchies>
    <p1:Exclude_Business_Units>1</p1:Exclude_Business_Units>
    <p1:Exclude_Business_Unit_Hierarchies>1</p1:Exclude_Business_Unit_Hierarchies>
    <p1:Exclude_Programs>1</p1:Exclude_Programs>
    <p1:Exclude_Program_Hierarchies>1</p1:Exclude_Program_Hierarchies>
    <p1:Exclude_Gifts>1</p1:Exclude_Gifts>
    <p1:Exclude_Gift_Hierarchies>1</p1:Exclude_Gift_Hierarchies>
    <p1:Include_Management_Chain_Data>1</p1:Include_Management_Chain_Data>
    <p1:Include_Additional_Jobs>1</p1:Include_Additional_Jobs>
  </p1:Response_Group>
</Get_Workers_Request>
```

#### 有効日が指定された更新と終了に対するクエリ

次 *のGet\_Workers* は、最後の実行から現在の実行時間の間に発生した有効な日付の更新に対するクエリを要求します。

```xml
<!-- Workday incremental sync query for effective-dated updates -->
<!-- Replace version with Workday Web Services version present in your connection URL -->
<!-- Replace timestamps with the UTC time corresponding to last execution and current execution time -->
<!-- Count specifies the number of records to return in each page -->
<!-- Response_Group flags derived from provisioning attribute mapping -->

<Get_Workers_Request p1:version="v21.1" xmlns:p1="urn:com.workday/bsvc" xmlns="urn:com.workday/bsvc">
  <p1:Request_Criteria>
    <p1:Transaction_Log_Criteria_Data>
      <p1:Transaction_Date_Range_Data>
        <p1:Effective_From>2021-01-19T02:29:16.0094202Z</p1:Effective_From>
        <p1:Effective_Through>2021-01-19T02:49:06.290136Z</p1:Effective_Through>
      </p1:Transaction_Date_Range_Data>
    </p1:Transaction_Log_Criteria_Data>
  </p1:Request_Criteria>
  <p1:Response_Filter>
    <p1:As_Of_Effective_Date>2021-01-19T02:49:06.290136Z</p1:As_Of_Effective_Date>
    <p1:As_Of_Entry_DateTime>2021-01-19T02:49:06.290136Z</p1:As_Of_Entry_DateTime>
    <p1:Page>1</p1:Page>
    <p1:Count>30</p1:Count>
  </p1:Response_Filter>
  <p1:Response_Group>
    <p1:Include_Reference>1</p1:Include_Reference>
    <p1:Include_Personal_Information>1</p1:Include_Personal_Information>
    <p1:Include_Employment_Information>1</p1:Include_Employment_Information>
    <p1:Include_Organizations>1</p1:Include_Organizations>
    <p1:Exclude_Organization_Support_Role_Data>1</p1:Exclude_Organization_Support_Role_Data>
    <p1:Exclude_Location_Hierarchies>1</p1:Exclude_Location_Hierarchies>
    <p1:Exclude_Cost_Center_Hierarchies>1</p1:Exclude_Cost_Center_Hierarchies>
    <p1:Exclude_Company_Hierarchies>1</p1:Exclude_Company_Hierarchies>
    <p1:Exclude_Matrix_Organizations>1</p1:Exclude_Matrix_Organizations>
    <p1:Exclude_Pay_Groups>1</p1:Exclude_Pay_Groups>
    <p1:Exclude_Regions>1</p1:Exclude_Regions>
    <p1:Exclude_Region_Hierarchies>1</p1:Exclude_Region_Hierarchies>
    <p1:Exclude_Funds>1</p1:Exclude_Funds>
    <p1:Exclude_Fund_Hierarchies>1</p1:Exclude_Fund_Hierarchies>
    <p1:Exclude_Grants>1</p1:Exclude_Grants>
    <p1:Exclude_Grant_Hierarchies>1</p1:Exclude_Grant_Hierarchies>
    <p1:Exclude_Business_Units>1</p1:Exclude_Business_Units>
    <p1:Exclude_Business_Unit_Hierarchies>1</p1:Exclude_Business_Unit_Hierarchies>
    <p1:Exclude_Programs>1</p1:Exclude_Programs>
    <p1:Exclude_Program_Hierarchies>1</p1:Exclude_Program_Hierarchies>
    <p1:Exclude_Gifts>1</p1:Exclude_Gifts>
    <p1:Exclude_Gift_Hierarchies>1</p1:Exclude_Gift_Hierarchies>
    <p1:Include_Management_Chain_Data>1</p1:Include_Management_Chain_Data>
    <p1:Include_Additional_Jobs>1</p1:Include_Additional_Jobs>
  </p1:Response_Group>
</Get_Workers_Request>
```

#### 将来の日付の雇用に対するクエリ

上記のクエリのいずれかが将来の日付の採用を返す場合は、次の *Get\_Workers* 要求を使用して、将来の日付の新入社員に関する情報を取得します。 新規採用者の *WID* 属性を使用して検索が実行され、有効日が採用日時に設定されます。

注意

Workday での将来の日付の雇用は、[アクティブ] フィールドが "0" に設定され、採用日に "1" に変更されます。 コネクタは仕様上、採用日に有効な将来の雇用情報に対するクエリを実行します。そのため、[アクティブ] フィールドを "1" に設定した将来の雇用のワーカー プロファイルが常に取得されます。 これにより、すべての正しい情報が事前に指定された将来の雇用の Microsoft Entra プロファイルをあらかじめ設定することができます。 将来の採用者のために Microsoft Entra アカウントの有効化を遅らせる場合は、変換関数 [DateDiff](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#datediff) を使用します。

```xml
<!-- Workday incremental sync query to get new hire data effective as on hire date/first day of work -->
<!-- Replace version with Workday Web Services version present in your connection URL -->
<!-- Replace timestamps hire date/first day of work -->
<!-- Count specifies the number of records to return in each page -->
<!-- Response_Group flags derived from provisioning attribute mapping -->

<Get_Workers_Request p1:version="v21.1" xmlns:p1="urn:com.workday/bsvc" xmlns="urn:com.workday/bsvc">
  <p1:Request_References>
    <p1:Worker_Reference>
      <p1:ID p1:type="WID">7bf6322f1ea101fd0b4433077f09cb04</p1:ID>
    </p1:Worker_Reference>
  </p1:Request_References>
  <p1:Response_Filter>
    <p1:As_Of_Effective_Date>2021-02-01T08:00:00+00:00</p1:As_Of_Effective_Date>
    <p1:As_Of_Entry_DateTime>2021-02-01T08:00:00+00:00</p1:As_Of_Entry_DateTime>
    <p1:Count>30</p1:Count>
  </p1:Response_Filter>
  <p1:Response_Group>
    <p1:Include_Reference>1</p1:Include_Reference>
    <p1:Include_Personal_Information>1</p1:Include_Personal_Information>
    <p1:Include_Employment_Information>1</p1:Include_Employment_Information>
    <p1:Include_Organizations>1</p1:Include_Organizations>
    <p1:Exclude_Organization_Support_Role_Data>1</p1:Exclude_Organization_Support_Role_Data>
    <p1:Exclude_Location_Hierarchies>1</p1:Exclude_Location_Hierarchies>
    <p1:Exclude_Cost_Center_Hierarchies>1</p1:Exclude_Cost_Center_Hierarchies>
    <p1:Exclude_Company_Hierarchies>1</p1:Exclude_Company_Hierarchies>
    <p1:Exclude_Matrix_Organizations>1</p1:Exclude_Matrix_Organizations>
    <p1:Exclude_Pay_Groups>1</p1:Exclude_Pay_Groups>
    <p1:Exclude_Regions>1</p1:Exclude_Regions>
    <p1:Exclude_Region_Hierarchies>1</p1:Exclude_Region_Hierarchies>
    <p1:Exclude_Funds>1</p1:Exclude_Funds>
    <p1:Exclude_Fund_Hierarchies>1</p1:Exclude_Fund_Hierarchies>
    <p1:Exclude_Grants>1</p1:Exclude_Grants>
    <p1:Exclude_Grant_Hierarchies>1</p1:Exclude_Grant_Hierarchies>
    <p1:Exclude_Business_Units>1</p1:Exclude_Business_Units>
    <p1:Exclude_Business_Unit_Hierarchies>1</p1:Exclude_Business_Unit_Hierarchies>
    <p1:Exclude_Programs>1</p1:Exclude_Programs>
    <p1:Exclude_Program_Hierarchies>1</p1:Exclude_Program_Hierarchies>
    <p1:Exclude_Gifts>1</p1:Exclude_Gifts>
    <p1:Exclude_Gift_Hierarchies>1</p1:Exclude_Gift_Hierarchies>
    <p1:Include_Management_Chain_Data>1</p1:Include_Management_Chain_Data>
    <p1:Include_Additional_Jobs>1</p1:Include_Additional_Jobs>
  </p1:Response_Group>
</Get_Workers_Request>
```

### ワーカー データ属性の取得

*Get\_Workers* API は、ワーカーに関連付けられているさまざまなデータ セットを返すことができます。 プロビジョニング スキーマで構成されている [XPATH API 式](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/workday-attribute-reference) に応じて、Microsoft Entra プロビジョニング サービスによって、Workday から取得するデータ セットが決定されます。 したがって、 *Response\_Group* フラグは *、Get\_Workers* 要求で設定されます。

表に、特定のデータ セットを取得するために使用するマッピング構成に関するガイダンスを示します。

| # | Workday エンティティ | 既定で含まれる | 既定以外のエンティティをフェッチするためにマッピングで指定する XPATH パターン |
| --- | --- | --- | --- |
| 1 | `Personal Data` | はい | `wd:Worker_Data/wd:Personal_Data` |
| 2 | `Employment Data` | はい | `wd:Worker_Data/wd:Employment_Data` |
| 3 | `Additional Job Data` | はい | `wd:Worker_Data/wd:Employment_Data/wd:Worker_Job_Data[@wd:Primary_Job=0]` |
| 4 | `Organization Data` | はい | `wd:Worker_Data/wd:Organization_Data` |
| 5 | `Management Chain Data` | はい | `wd:Worker_Data/wd:Management_Chain_Data` |
| 6 | `Supervisory Organization` | はい | `SUPERVISORY` |
| 7 | `Company` | はい | `COMPANY` |
| 8 | `Business Unit` | いいえ | `BUSINESS_UNIT` |
| 9 | `Business Unit Hierarchy` | いいえ | `BUSINESS_UNIT_HIERARCHY` |
| 10 | `Company Hierarchy` | いいえ | `COMPANY_HIERARCHY` |
| 11 | `Cost Center` | いいえ | `COST_CENTER` |
| 12 | `Cost Center Hierarchy` | いいえ | `COST_CENTER_HIERARCHY` |
| 13 | `Fund` | いいえ | `FUND` |
| 14 | `Fund Hierarchy` | いいえ | `FUND_HIERARCHY` |
| 15 | `Gift` | いいえ | `GIFT` |
| 16 | `Gift Hierarchy` | いいえ | `GIFT_HIERARCHY` |
| 十七 | `Grant` | いいえ | `GRANT` |
| 18 | `Grant Hierarchy` | いいえ | `GRANT_HIERARCHY` |
| 19 | `Business Site Hierarchy` | いいえ | `BUSINESS_SITE_HIERARCHY` |
| 20 | `Matrix Organization` | いいえ | `MATRIX` |
| 21 (二十一) | `Pay Group` | いいえ | `PAY_GROUP` |
| 22 | `Programs` | いいえ | `PROGRAMS` |
| 23 | `Program Hierarchy` | いいえ | `PROGRAM_HIERARCHY` |
| 二十四 | `Region` | いいえ | `REGION_HIERARCHY` |
| 二十五 | `Location Hierarchy` | いいえ | `LOCATION_HIERARCHY` |
| 26 | `Account Provisioning Data` | いいえ | `wd:Worker_Data/wd:Account_Provisioning_Data` |
| 二十七 | `Background Check Data` | いいえ | `wd:Worker_Data/wd:Background_Check_Data` |
| 28 | `Benefit Eligibility Data` | いいえ | `wd:Worker_Data/wd:Benefit_Eligibility_Data` |
| 二十九 | `Benefit Enrollment Data` | いいえ | `wd:Worker_Data/wd:Benefit_Enrollment_Data` |
| 30 | `Career Data` | いいえ | `wd:Worker_Data/wd:Career_Data` |
| 31 | `Compensation Data` | いいえ | `wd:Worker_Data/wd:Compensation_Data` |
| 32 | `Contingent Worker Tax Authority Data` | いいえ | `wd:Worker_Data/wd:Contingent_Worker_Tax_Authority_Form_Type_Data` |
| 33 | `Development Item Data` | いいえ | `wd:Worker_Data/wd:Development_Item_Data` |
| 34 | `Employee Contracts Data` | いいえ | `wd:Worker_Data/wd:Employee_Contracts_Data` |
| 35 | `Employee Review Data` | いいえ | `wd:Worker_Data/wd:Employee_Review_Data` |
| 36 | `Feedback Received Data` | いいえ | `wd:Worker_Data/wd:Feedback_Received_Data` |
| 37 | `Worker Goal Data` | いいえ | `wd:Worker_Data/wd:Worker_Goal_Data` |
| 三十八 | `Photo Data` | いいえ | `wd:Worker_Data/wd:Photo_Data` |
| 39 | `Qualification Data` | いいえ | `wd:Worker_Data/wd:Qualification_Data` |
| 40 | `Related Persons Data` | いいえ | `wd:Worker_Data/wd:Related_Persons_Data` |
| 41 | `Role Data` | いいえ | `wd:Worker_Data/wd:Role_Data` |
| 42 | `Skill Data` | いいえ | `wd:Worker_Data/wd:Skill_Data` |
| 43 | `Succession Profile Data` | いいえ | `wd:Worker_Data/wd:Succession_Profile_Data` |
| 44 | `Talent Assessment Data` | いいえ | `wd:Worker_Data/wd:Talent_Assessment_Data` |
| 45 | `User Account Data` | いいえ | `wd:Worker_Data/wd:User_Account_Data` |
| 46 | `Worker Document Data` | いいえ | `wd:Worker_Data/wd:Worker_Document_Data` |

注意

表に示されている各 Workday エンティティは、Workday の **ドメイン セキュリティ ポリシー** によって保護されます。 適切な XPATH を設定した後にエンティティに関連付けられている属性を取得できない場合は、Workday 管理者に問い合わせて、プロビジョニング アプリに関連付けられている統合システム ユーザーに適切なドメイン セキュリティ ポリシーが構成されていることを確認してください。 たとえば、*スキル データ*を取得するには、Workday ドメイン*の Worker Data: Skills and Experience* でアクセス権を*取得*する必要があります。

次に、Workday 統合を拡張して特定の要件を満たす方法の例をいくつか示します。

#### 例 1: コストセンターと支払いグループの情報を取得する

Workday から次のデータ セットを取得し、それらをプロビジョニング ルールで使用するとします。

- コスト センター
- コスト センターの階層
- 支払いグループ

上記のデータ セットは、既定では含まれていません。 これらのデータ セットを取得するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**を参照します。
3. Workday から Active Directory/Microsoft Entra ユーザー プロビジョニング アプリケーションを選びます。
4. [ **プロビジョニング] を選択します**。
5. マッピングを編集し、[詳細設定] セクションで Workday 属性の一覧を開きます。
6. 次の属性定義を追加し、それらを "必須" としてマークします。 これらの属性は、Active Directory または Microsoft Entra ID のどの属性にもマップされません。 これらは、コスト センター、コスト センターの階層、支払いグループの各情報を取得するようにコネクタに指示するシグナルとして機能します。

    | 属性名 | XPATH API 式 |
    | --- | --- |
    | CostCenterHierarchyフラグ | wd:ワーカー/wd:Worker\_Data/wd:Organization\_Data/wd:Worker\_Organization\_Data[wd:Organization\_Data/wd:Organization\_Type\_Reference/wd:ID[@wd:type='Organization\_Type\_ID']='COST\_CENTER\_HIERARCHY']/wd:Organization\_Reference/@wd:ディスクリプタ |
    | コストセンターフラグ | wd:ワーカー/wd:Worker\_Data/wd:Organization\_Data/wd:Worker\_Organization\_Data[wd:Organization\_Data/wd:Organization\_Type\_Reference/wd:ID[@wd:type='Organization\_Type\_ID']='COST\_CENTER']/wd:Organization\_Data/wd:Organization\_Code/text() |
    | ペイグループフラグ | wd:ワーカー/wd:Worker\_Data/wd:Organization\_Data/wd:Worker\_Organization\_Data[wd:Organization\_Data/wd:Organization\_Type\_Reference/wd:ID[@wd:type='Organization\_Type\_ID']='PAY\_GROUP']/wd:Organization\_Data/wd:Organization\_Reference\_ID/text() |
7. *Get\_Workers*応答で Cost Center と Pay Group のデータ セットを使用できるようになったら、XPATH 値を使用して、コスト センター名、コスト センター コード、および支払グループを取得できます。

    | 属性名 | XPATH API 式 |
    | --- | --- |
    | コストセンター名 | wd:Worker/wd:Worker\_Data/wd:Organization\_Data/wd:Worker\_Organization\_Data/wd:Organization\_Data[wd:Organization\_Type\_Reference/@wd:Descriptor='コストセンター']/wd:Organization\_Name/text() |
    | コストセンターコード | wd:Worker/wd:Worker\_Data/wd:Organization\_Data/wd:Worker\_Organization\_Data/wd:Organization\_Data[wd:Organization\_Type\_Reference/@wd:Descriptor='コストセンター']/wd:Organization\_Code/text() |
    | ペイグループ | wd:労働者/wd:Worker\_Data/wd:Organization\_Data/wd:Worker\_Organization\_Data/wd:Organization\_Data[wd:Organization\_Type\_Reference/@wd:descriptor='Pay Group']/wd:Organization\_Name/text() |

#### 例 2: 資格データとスキル データを取得する

あるユーザーに関連付けられている認定資格を取得するとします。 この情報は、 *修飾データ* セットの一部として使用できます。 このデータ セットを *Get\_Workers* 応答の一部として取得するには、次の XPATH を使用します。

`wd:Worker/wd:Worker_Data/wd:Qualification_Data/wd:Certification/wd:Certification_Data/wd:Issuer/text()`

#### 例 3: プロビジョニング グループの割り当てを取得する

たとえば、worker に割り当てられた *プロビジョニング グループを* 取得するとします。 この情報は、 *アカウント プロビジョニング データ* セットの一部として使用できます。 このデータを取得するには、 *Get\_Workers* 応答の一部として、次の XPATH を使用します。

`wd:Worker/wd:Worker_Data/wd:Account_Provisioning_Data/wd:Provisioning_Group_Assignment_Data[wd:Status='Assigned']/wd:Provisioning_Group/text()`

### さまざまな HR シナリオの処理

このセクションでは、次の HR シナリオ用にプロビジョニング アプリをカスタマイズする方法について説明します。

- ワーカー変換のサポート
- 国際ジョブの割り当てとセカンダリ ジョブの詳細の取得

#### ワーカーの転換のサポート

このセクションでは、ワーカーが常勤従業員 (FTE) から臨時職員 (CW) に、またはその逆に転換するシナリオに対する Microsoft Entra プロビジョニング サービスのサポートについて説明します。 Workday でのワーカー転換の処理方法によっては、考慮すべき実装の側面が異なる場合があります。

- シナリオ 1: FTE から CW への逆の変換、またはその逆
- シナリオ 2: 現在 CW/FTE として雇用されているワーカー、現在 FTE/CW に変更
- シナリオ 3: CW/FTE として使用されているワーカーが終了し、大幅なギャップの後に FTE/CW として再結合する
- シナリオ 4: ワーカーがアクティブ CW/FTE の場合の将来日付変換

##### シナリオ 1: FTE から CW への過去の日付の転換、またはその逆

人事チームは、有効なビジネス上の理由から、Workday でワーカー転換トランザクションの日付をさかのぼることができます。 たとえば、給与処理、予算コンプライアンス、法的要件、福利厚生管理などがあります。 このシナリオでのプロビジョニングの処理方法を示す例を次に示します。

- 2023 年 1 月 15 日、Jane Doe は臨時職員として雇用されています。 人事部は Jane にフルタイムのポジションを提案します。
- Jane の契約条件を変更するには、現在の月の開始に合わせてトランザクションの日付をさかのぼる必要があります。 人事部は、2023 年 1 月 15 日に、過去の日付のワーカー転換トランザクション Workday を開始します。有効日は 2023 年 1 月 1 日です。 現在、Workday では、Jane に対して 2 つのワーカー プロファイルがあります。 CW プロファイルは非アクティブですが、FTE プロファイルはアクティブです。
- Microsoft Entra プロビジョニング サービスでは、2023 年 1 月 15 日に Workday トランザクション ログでこの変更を検出します。 サービスでは、次の同期サイクルで新しい FTE プロファイルの属性を自動的にプロビジョニングします。
- このシナリオを処理するために、プロビジョニング アプリの構成に変更は必要ありません。

##### シナリオ 2: 現在 CW/FTE として採用されているワーカーを今日 FTE/CW に変更する

このシナリオは上記のシナリオに似ていますが、トランザクションの日付をさかのぼる代わりに、人事部は直ちに有効なワーカー転換を実行します。 Microsoft Entra プロビジョニング サービスでは、Workday トランザクション ログでこの変更を検出します。 次の同期サイクルでは、アクティブな FTE プロファイルに関連付けられている属性がサービスによって自動的にプロビジョニングされます。 このシナリオを処理するために、プロビジョニング アプリの構成に変更は必要ありません。

##### シナリオ 3: CW/FTE として採用されていたワーカーが解雇され、大幅なギャップの後に FTE/CW として再び入社する

労働者が会社で臨時職員として働き始め、退職してから、数か月後に常勤従業員として再び入社することは一般的です。 このシナリオでのプロビジョニングの処理方法を示す例を次に示します。

- 2023 年 1 月 1 日、John Smith は臨時職員として働き始めます。 John の *WorkerID* (照合属性) に関連付けられている AD アカウントがないため、プロビジョニング サービスは新しい AD アカウントを作成し、John の臨時ワーカー *WID (WorkdayID)* を John の AD アカウントにリンクします。
- John の契約は、2023 年 1 月 31 日に終了します。 1 月 31 日の終了後に実行されるプロビジョニング サイクルでは、John の AD アカウントが無効になります。
- John は別の役職に応募し、2023 年 5 月 1 日に常勤従業員として再入社することに決定しました。 人事部は、2023 年 4 月 15 日に事前採用従業員として John の情報を入力します。 現在、Workday では、John に対して 2 つのワーカー プロファイルがあります。 CW プロファイルは非アクティブですが、FTE プロファイルはアクティブです。 2 つのレコードの *WorkerID* は同じですが、 *WID は*異なります。
- 4 月 15 日、増分サイクル中に、Microsoft Entra プロビジョニング サービスによって、AD アカウントの所有権がアクティブなワーカー プロファイルに自動的に転送されます。 この場合、AD アカウントから臨時職員プロファイルのリンクが解除され、John のアクティブな正社員ワーカー プロファイルと John の AD アカウントの間に新しいリンクが確立されます。
- このシナリオを処理するために、プロビジョニング アプリの構成に変更は必要ありません。

##### シナリオ 4: ワーカーがアクティブな CW/FTE である場合の将来の日付の転換

場合によっては、人事部が将来の日付のワーカー転換トランザクションを開始するときに、ワーカーがアクティブな臨時職員である場合があります。 このシナリオでプロビジョニングを処理する方法と、このシナリオをサポートするために必要な構成の変更を示す例を次に示します。

- 2023 年 1 月 1 日、John Smith は臨時職員として働き始めます。 John の *WorkerID* (照合属性) に関連付けられている AD アカウントがないため、プロビジョニング サービスは新しい AD アカウントを作成し、John の臨時ワーカー *WID (WorkdayID)* を John の AD アカウントにリンクします。
- 1 月 15 日、人事部は、2023 年 2 月 1 日に John を臨時職員から常勤従業員に転換するトランザクションを開始します。
- Microsoft Entra プロビジョニング サービスでは、将来の日付の雇用を自動的に処理するため、1 月 15 日に John の新しい常勤従業員のワーカー プロファイルを処理し、AD の John のプロファイルを、まだ臨時職員であるにもかかわらず、常勤従業員の詳細で更新します。
- この動作を回避し、John の FTE の詳細が 2023 年 2 月 1 日にプロビジョニングされるようにするには、次の構成変更を実行します。

    **構成の変更**

    1. Workday 管理者と協力しながら、"Future-dated conversions" という名前のプロビジョニング グループを作成します。
    2. Workday にロジックを実装して、将来の日付の転換を含む従業員/臨時職員レコードをこのプロビジョニング グループに追加します。
    3. Microsoft Entra プロビジョニング アプリを更新して、このプロビジョニング グループを読み取ります。 プロビジョニング グループを取得する方法については、こちらの手順を参照してください
    4. Microsoft Entra ID で [スコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) 作成し、このプロビジョニング グループの一部であるワーカー プロファイルを除外します。
    5. Workday で、転換日が有効になったときに Workday のプロビジョニング グループから関連する従業員/臨時職員レコードを削除するようにロジックを実装します。
    6. この構成では、既存の従業員/臨時職員レコードは引き続き有効になり、プロビジョニングの変更は転換日にのみ行われます。

注意

最初の完全同期中に、以前の非アクティブなワーカー プロファイルに関連付けられている属性値が、転換があったワーカーの AD アカウントにフローする動作が発生することがあります。 これは一時的なもので、完全同期が進行すると、最終的にはアクティブなワーカー プロファイルの属性値によって上書きされます。 完全同期が完了し、プロビジョニング ジョブが安定した状態になると、増分同期中にアクティブなワーカー プロファイルが常に選択されます。

#### 国際的なジョブの割り当てとセカンダリ ジョブの詳細の取得

既定で Workday コネクタによって取得されるのは、worker のプライマリ ジョブに関連付けられた属性です。 また、このコネクタでは、国際的なジョブの割り当てまたはセカンダリ ジョブに関連付けられた "`Additional Job Data`" を取得することもサポートされています。

国際的なジョブの割り当てに関連付けられた属性を取得するには、次の手順に従ってください。

1. Workday Web サービス API バージョン 30.0 以降を使用する Workday 接続 URL を設定します。 それに応じて、Workday プロビジョニング アプリで [正しい XPATH 値](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/workday-attribute-reference#xpath-values-for-workday-web-services-wws-api-v30) を設定します。
2. `@wd:Primary_Job=0` ノードのセレクター `Worker_Job_Data`を使用して、適切な属性を取得します。
    - **例 1:**`SecondaryBusinessTitle`を取得するには、XPATH を使用します。`wd:Worker/wd:Worker_Data/wd:Employment_Data/wd:Worker_Job_Data[@wd:Primary_Job=0]/wd:Position_Data/wd:Business_Title/text()`
    - **例 2:**`SecondaryBusinessLocation`を取得するには、XPATH を使用します。`wd:Worker/wd:Worker_Data/wd:Employment_Data/wd:Worker_Job_Data[@wd:Primary_Job=0]/wd:Position_Data/wd:Business_Site_Summary_Data/wd:Location_Reference/@wd:Descriptor`

### 既知の制限事項

このセクションでは、お客様が Workday 統合で経験する可能性がある現在の既知の制限事項を示します。

1. コネクタでは、 [Workday 計算フィールドの取得は](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-attribute-retrieval-issues#issue-fetching-workday-calculated-fields)サポートされていません。
2. コネクタでは、Workday からの写真の同期はサポートされていません。
3. コネクタは、 [作業の最終日が期限のワーカーの高度な取得を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-user-update-issues#provisioning-last-day-of-work-field-from-workday)サポートしていません。
4. このコネクタは、21Vianet (中国) が運営する Microsoft テナントではサポートされていません。
5. 増分同期中に、アジア太平洋およびオーストラリア/ニュージーランドリージョンにある作業者の [終了イベントの処理に遅延](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/hr-user-update-issues#workday-termination-processing-delay) が生じる可能性があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-provisioning/workday-retrieve-pronoun-information"} -->
## Workday から代名詞情報を取得する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/workday-retrieve-pronoun-information
- Service: entra-id / app-provisioning
- Article date: 2025-03-04
- Summary: Workday から代名詞情報を取得する方法について説明します

この記事では、次の 2 つの人事主導のプロビジョニング アプリをカスタマイズして、Workday から代名詞情報を取得する方法について説明します。

- [Workday からオンプレミスの Active Directory ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial)
- [Workday から Microsoft Entra へのユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-cloud-only-tutorial)

### Workday の代名詞情報について

Workday では、Workday 2021 R1 リリースで作業者が作業者プロファイルに [代名詞情報を表示](https://community.workday.com/node/731178) する機能が導入されました。 Workday Web Services (WWS) API 呼び出しを使用して代名詞データをフェッチする機能は、Workday 2022 [R1 リリースGet_Workers API バージョン 38.1](https://community.workday.com/sites/default/files/file-hosting/productionapi/Human_Resources/v38.1/Get_Workers.html) で導入されました。

注意

この記事の特定の Workday コミュニティ ノートとドキュメントへのリンクには、Workday コミュニティ アカウントの資格情報が必要です。 必要なアクセス権を取得するには、Workday 管理者またはパートナーに確認してください。

### Workday で代名詞データを有効にする

このセクションでは、Workday で代名詞データを有効にするために必要な手順について説明します。 示されている手順の完了に Workday 管理者を参加させることをお勧めします。

1. 代名詞の表示と共有の基本設定が Workday ガイドラインに従って有効になっていることを確認します。 次の Workday ドキュメントを参照してください。

    - [手順: 作業者プロファイルに表示する性別代名詞を設定する * 人事管理 * 閲覧者 * 管理者ガイド (workday.com)](https://doc.workday.com/r/gJQvxHUyQOZv_31Vknf%7E3w/7gZPvVfbRhLiPissprv6lQ)
    - [手順: パブリック プロファイルの基本設定を設定する * 人事管理 * 閲覧者 * 管理者ガイド (workday.com)](https://doc.workday.com/r/gJQvxHUyQOZv_31Vknf%7E3w/FuENV1VTRTHWo_h93KIjJA)
2. Workday **Maintain Pronouns** タスクを使用して、Workday テナントで優先代名詞データ (HE/HIM、SHE/HER、THEY/THEM) を定義します。
3. Workday **のローカライズ設定の管理タスク -&gt; 個人情報** 領域を使用して、さまざまな国/地域の代名詞データをアクティブ化します。
4. Microsoft Entra 統合で使用する Workday 統合システム セキュリティ グループを選択します。 [セキュリティ グループのドメインアクセス許可を](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#configuring-domain-security-policy-permissions)更新して、Workday ドメイン **レポート: パブリック プロファイル**に対する GET アクセス権を持ちます。

[Image: Workday でセットアップするアクセス許可のスクリーンショット。]
5. 保留中のセキュリティ ポリシーの変更をアクティブ化します。
6. テストのために Workday テナント内のワーカーを選択します。 **[個人情報の編集]** タスクを使用して、この作業者の代名詞情報を設定します。 ワーカーがパブリック プロファイルの基本設定ですべてのユーザーに代名詞の表示を有効にしていることを確認します。

[Image: 代名詞表示オプションを有効にするスクリーンショット。]
7. Workday Studio を使用して、Workday Microsoft Entra 統合システム ユーザー Get\_Workers使用して、テスト ユーザーに対して [API バージョン 38.1](https://community.workday.com/sites/default/files/file-hosting/productionapi/Human_Resources/v38.1/Get_Workers.html) を呼び出します。 SOAP 要求ヘッダーで、オプション Include\_Reference\_Descriptors\_In\_Response を指定します。

    ```
      <bsvc:Workday_Common_Header>
         <bsvc:Include_Reference_Descriptors_In_Response>true</bsvc:Include_Reference_Descriptors_In_Response>
      </bsvc:Workday_Common_Header> 
    ```
8. Get\_Workers 応答で、代名詞情報を表示します。

[Image: Workday Get Workers API 応答のスクリーンショット。]

注意

*Get\_Workers*応答で代名詞データを取得できない場合は、Workday ドメインのセキュリティアクセス許可のトラブルシューティングを行います。 統合セキュリティ グループに、代名詞データへのアクセスを許可するセグメント化されたセキュリティ グループへのアクセス許可があることを確認します。

*Get\_Workers*応答で代名詞データが使用可能であることを確認したら、Microsoft Entra プロビジョニング アプリの構成を更新する次の手順に進みます。

Workday から代名詞を取得するために、Workday Web Services の v38.1 を使用して Workday にクエリを実行するように Microsoft Entra プロビジョニング アプリを更新します。 運用環境で変更を実装する前に、まずテスト/サンドボックス環境でこの構成をテストすることをお勧めします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**を参照します。
3. Workday から Active Directory/Microsoft Entra ユーザー プロビジョニング アプリケーションを選択し、[ **プロビジョニング** ] に移動します。
4. [ **管理者資格情報** ] セクションで、次のように **テナント URL を** 更新して、Workday Web サービス バージョン v38.1 を含めます。

[Image: Workday バージョンの Microsoft Entra Admin Center プロビジョニング アプリのスクリーンショット。]
5. **[属性マッピング**] ページを開きます。 [ **詳細オプション] ドロップダウンを** 選択し、[ **ターゲット ユーザー属性の編集]** を選択します。
6. 既定の WWS API バージョン v21.1 を使用するようにプロビジョニング アプリが構成されている場合は、 [この記事を参照して、各属性の XPATHs を確認して更新](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/workday-attribute-reference#xpath-values-for-workday-web-services-wws-api-v30)します。
7. XPATH を使用 **して PreferredPronoun** という新しい属性を追加する

    `/wd:Worker/wd:Worker_Data/wd:Personal_Data/wd:Personal_Information_Data/wd:Pronoun_Reference/@wd:Descriptor`
8. 変更を保存します。
9. 新しい属性マッピングを追加して、Workday 属性 **PreferredPronoun を** AD/Microsoft Entra ID の任意の属性にフローできるようになりました。
10. 代名詞情報を表示名の一部として組み込む場合は、次の式を使用するように displayName 属性の属性マッピングを更新できます。

    `Switch([PreferredPronoun], Join("", [PreferredNameData], " (", [PreferredPronoun], ")"), "", [PreferredNameData])`
11. ワーカー*のアーロン ホール*が Workday の代名詞情報を `HE/HIM` として設定している場合、上記の式では、Microsoft Entra ID の表示名が *Aaron Hall (HE/HIM)* として設定されます。
12. 変更を保存します。
13. オンデマンド プロビジョニングを使用して 1 人のユーザーの構成をテストします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy"} -->
## アプリケーション プロキシのドキュメンテーション - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy
- Service: entra-id / app-proxy
- Article date: 2025-02-21
- Summary: Microsoft Entra アプリケーション プロキシを使用すると、プライベート アプリケーションに対し、セキュリティで保護されたリモート アクセスとクラウド規模のセキュリティが提供されます。

Microsoft Entra アプリケーション プロキシを使用すると、プライベート アプリケーションに対し、セキュリティで保護されたリモート アクセスとクラウド規模のセキュリティが提供されます。

### アプリケーション プロキシについて

#### 概要

- [アプリケーション プロキシとは](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)

### 始めましょう

#### クイックスタート

- [Microsoft Entra ID のアプリケーション プロキシを使用してリモート アクセスするためのオンプレミス アプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)
- [Microsoft Entra プライベート ネットワーク コネクタについて](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors)

### シングル サインオンについて

#### Architecture

- [アプリケーション プロキシ アプリケーションに対するシングル サインオンを構成する方法](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-sso)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/app-proxy-protect-ndes"} -->
## ネットワーク デバイス登録サービス (NDES) サーバーで Microsoft Entra アプリケーション プロキシを使用する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/app-proxy-protect-ndes
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Microsoft Entra アプリケーション プロキシを使用してモバイル デバイスの NDES 証明書登録をセキュリティで保護する。 コネクタのセットアップと証明書要求の検証が含まれます。

### 概要

Microsoft Entra アプリケーション プロキシを使用してネットワーク デバイス登録サービス (NDES) を保護する方法について説明します。

### NDES サーバーへのコネクタのインストールおよび登録

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. 右上隅で自分のユーザー名を選択します。 アプリケーション プロキシを使うディレクトリにサインインしていることを確認します。 ディレクトリを変更する必要がある場合は、[ **ディレクトリの切り替え** ] を選択し、アプリケーション プロキシを使用するディレクトリを選択します。
3. **Entra ID**&gt;**Enterprise apps**&gt;**Application プロキシ**にアクセスします。
4. [ **コネクタ サービスのダウンロード**] を選択します。 [Image: コネクタ サービスをダウンロードして、サービス利用規約を確認します。]
5. サービス利用規約を読みます。 準備ができたら、[ **同意する] と [ダウンロード**] を選択します。
6. Microsoft Entra プライベート ネットワーク コネクタのセットアップ ファイルを NDES サーバーにコピーします。

>
> 企業ネットワーク内の、NDES にアクセスできる任意のサーバーにコネクタをインストールします。 NDES サーバー自体にインストールする必要はありません。
7. *MicrosoftEntraPrivateNetworkConnectorInstaller.exe*などのセットアップ ファイルを実行します。 ソフトウェア ライセンス条項に同意します。
8. インストール時に、Microsoft Entra ディレクトリのアプリケーション プロキシにコネクタを登録するように求められます。 Microsoft Entra ディレクトリのアプリケーション管理者の資格情報を指定します。 Microsoft Entra アプリケーション管理者の資格情報は、多くの場合、ポータルの Azure 資格情報とは異なります。

    注

    コネクタの登録に使用するアプリケーション管理者ロールを持つアカウントは、アプリケーション プロキシ サービスを有効にするのと同じディレクトリに属している必要があります。

    たとえば、Microsoft Entra ドメインが *contoso.com* されている場合、アプリケーション管理者はそのドメインで `admin@contoso.com` または別の有効なエイリアスである必要があります。

    コネクタをインストールするサーバーで [Internet Explorer セキュリティ強化の構成] がオンになっていると、登録画面がブロックされることがあります。 アクセスを許可するには、エラー メッセージの指示に従うか、インストールプロセス中に Internet Explorer のセキュリティ強化をオフにします。

    コネクタの登録に失敗した場合は、「 [アプリケーション プロキシのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-troubleshoot)」を参照してください。
9. セットアップの最後に、送信プロキシを使用している環境に対してメモが表示されます。 送信プロキシ経由で動作するように Microsoft Entra プライベート ネットワーク コネクタを構成するには、提供されたスクリプト (`C:\Program Files\Microsoft Entra private network connector\ConfigureOutBoundProxy.ps1` など) を実行します。
10. Microsoft Entra 管理センターのアプリケーション プロキシ ページで、この例に示すように、新しいコネクタが "アクティブ" の状態で一覧の中に表示されます。[Image: Microsoft Entra 管理センターでアクティブとして表示される新しい Microsoft Entra プライベート ネットワーク コネクタ。]

    注

    Microsoft Entra アプリケーション プロキシを介して認証を行うアプリケーションの高可用性を実現するために、複数の VM にコネクタをインストールできます。 前のセクションと同じ手順を繰り返して、Microsoft Entra Domain Services マネージド ドメインに参加している他のサーバーにコネクタをインストールします。
11. 正常にインストールされたら、Microsoft Entra 管理センターに戻ります。
12. **[エンタープライズ アプリケーション] を選択します**。 [Image: 適切な利害関係者が関与していることを確認します。]
13. **[+ 新しいアプリケーション**] を選択し、[**オンプレミス アプリケーション**] を選択します。
14. [ **独自のオンプレミス アプリケーションの追加]** で、フィールドを構成します。

    **名前**: アプリケーションの名前を入力します。

    **内部 URL**: コネクタをインストールした NDES サーバーの内部 URL/FQDN を入力します。

    **事前認証**: **[パススルー**] を選択します。 事前認証の形式を使用することはできません。 証明書要求に使用されるプロトコルである Simple Certificate Enrollment Protocol (SCEP) には、このようなオプションはありません。

    指定した **外部 URL を** クリップボードにコピーします。
15. **[+追加]** を選択してアプリケーションを保存します。
16. ステップ 15 でコピーしたリンクをブラウザーに貼り付けて、Microsoft Entra アプリケーション プロキシを介して NDES サーバーにアクセスできるかどうかをテストします。 インターネット インフォメーション サービス (IIS) の既定のウェルカム ページが表示されます。
17. 最後のテストとして、前の手順で貼り付けた既存の URL に *mscep.dll* パスを追加します。 `https://scep-test93635307549127448334.msappproxy.net/certsrv/mscep/mscep.dll`
18. **HTTP エラー 403 – 禁止**された応答が表示されます。
19. デバイスに (Microsoft Intune 経由で) 提供された NDES URL を変更します。 この変更は、Microsoft Configuration Manager または Microsoft Intune 管理センターのいずれかで行うことができます。

    - Configuration Manager の場合は、証明書登録ポイントに移動して、URL を調整します。 この URL は、デバイスがアクセスし、チャレンジを提示するためのものです。
    - Intune スタンドアロンの場合は、SCEP ポリシーを編集するか新規に作成し、新しい URL を追加します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-add-on-premises-application"} -->
## Microsoft Entra ID のアプリケーション プロキシを使用してリモート アクセスするためのオンプレミス アプリケーションを追加する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application
- Service: entra-id / app-proxy
- Article date: 2026-03-10
- Summary: アプリケーション プロキシ用に環境を準備し、Microsoft Entra テナントにオンプレミス アプリケーションを追加する方法について説明します。

### 概要

Microsoft Entra ID のアプリケーション プロキシ サービスを使うと、ユーザーは Microsoft Entra アカウントでサインインして、オンプレミスのアプリケーションにアクセスできます。 アプリケーション プロキシの詳細については、「アプリケーション [プロキシとは」](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)を参照してください。 このチュートリアルでは、アプリケーション プロキシで使用できるように環境を準備します。 環境の準備ができたら、Microsoft Entra 管理センターを使用して、オンプレミス アプリケーションをテナントに追加します。

[Image: アプリケーション プロキシの概要図。]

このチュートリアルでは、次の作業を行いました。

- Windows サーバーにコネクタをインストールして確認し、アプリケーション プロキシに登録します。
- Microsoft Entra テナントにオンプレミス アプリケーションを追加します。
- User.Read アクセス許可の管理者の同意を付与する
- テスト ユーザーが Microsoft Entra アカウントを使用してアプリケーションにサインインできることを確認します。

Important

2026 年 6 月 30 日より、Microsoft Entra アプリケーション プロキシは、新しいアプリケーション プロキシ エンタープライズ アプリケーションを作成するときに**、User.Read** の委任されたアクセス許可に対する管理者の同意を自動的に付与しなくなりました。 アプリケーションの作成後に、このアクセス許可を明示的に付与する必要があります。

この変更は、 **新しく作成された** アプリケーション プロキシ アプリケーションにのみ適用されます。 既存のアプリケーションは影響を受けません。

### 前提条件

Microsoft Entra ID にオンプレミス アプリケーションを追加するには、次のものが必要です。

- [Microsoft Entra ID P1 または P2 サブスクリプション](https://azure.microsoft.com/pricing/details/active-directory)。
- アプリケーション管理者アカウント。
- オンプレミスディレクトリを使用して同期されたユーザー ID のセット、または Microsoft Entra テナントで直接作成された ID。 ID 同期によって、アプリケーション プロキシが発行したアプリケーションへのアクセスをユーザーに付与する前に、Microsoft Entra ID でユーザーを事前に認証できます。 同期により、シングル サインオン (SSO) を実行するために必要なユーザー識別子情報を提供することもできます。
- Microsoft Entra でのアプリケーション管理に関する理解。 詳細については、「 [Microsoft Entra でエンタープライズ アプリケーションを表示する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/view-applications-portal)」を参照してください。
- シングル サインオン (SSO) の理解。 詳細については、「 [シングル サインオンについて」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### Microsoft Entra プライベート ネットワーク コネクタをインストールして検証する

アプリケーション プロキシは、Microsoft Entra Private Access と同じコネクタを使用します。 コネクタは Microsoft Entra プライベート ネットワーク コネクタと呼ばれます。 コネクタをインストールして確認する方法については、「コネクタ [を構成する方法](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-connectors)」を参照してください。

### 一般的な注釈

Microsoft Entra アプリケーション プロキシ エンドポイントのパブリック ドメイン ネーム システム (DNS) レコードは、A レコードを指すチェーンされた CNAME レコードです。 レコードをこのような方法で設定しておくと、フォールト トレランスと柔軟性が確保されます。 Microsoft Entra プライベート ネットワーク コネクタは、常にドメイン サフィックス `*.msappproxy.net` または `*.servicebus.windows.net` を持つホスト名にアクセスします。 ただし、名前解決の際に、CNAME レコードには、異なるホスト名やサフィックスが付いた DNS レコードが含まれる場合があります。

この違いにより、デバイス (セットアップに応じてコネクタ サーバー、ファイアウォール、または送信プロキシ) がチェーン内のすべてのレコードを解決し、解決された IP アドレスへの接続を許可できることを確認する必要があります。 チェーン内の DNS レコードは随時変更される可能性があるため、使用可能な DNS レコードの固定リストはありません。

コネクタを異なるリージョンにインストールする場合は、各コネクタ グループで最も近いアプリケーション プロキシ クラウド サービス リージョンを選び、トラフィックを最適化する必要があります。 詳細については、 [Microsoft Entra アプリケーション プロキシを使用したトラフィック フローの最適化](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-network-topology)に関するページを参照してください。

お客様の組織がプロキシ サーバーを使ってインターネットに接続する場合は、それらをアプリケーション プロキシ用に構成する必要があります。 詳細については、「 [既存のオンプレミス プロキシ サーバーの操作」を](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-connectors-with-proxy-servers)参照してください。

### Microsoft Entra ID にオンプレミス アプリを追加する

Microsoft Entra ID にオンプレミス アプリケーションを追加します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
3. [ **新しいアプリケーション]** を選択します。
4. [ **オンプレミス アプリケーションの追加] ボタンを** 選択します。このボタンは、[ **オンプレミス アプリケーション** ] セクションのページの約半分下に表示されます。 または、ページの上部にある [ **独自のアプリケーションの作成** ] を選択し、[ **オンプレミス アプリケーションへのセキュリティで保護されたリモート アクセス用にアプリケーション プロキシを構成**する] を選択することもできます。
5. [ **独自のオンプレミス アプリケーションの追加** ] セクションで、アプリケーションに関する次の情報を指定します。

    | フィールド | 内容 |
    | --- | --- |
    | **名前** | [マイ アプリ] と Microsoft Entra 管理センターに表示されるアプリケーションの名前。 |
    | **メンテナンス モード** | メンテナンス モードを有効にし、すべてのユーザーによるアプリケーションへのアクセスを一時的に無効にする場合に選択します。 |
    | **内部 URL** | プライベート ネットワークの内部からアプリケーションにアクセスするための URL。 バックエンド サーバー上の特定のパスを指定して発行できます。この場合、サーバーのそれ以外のパスは発行されません。 この方法では、同じサーバー上の複数のサイトを別々のアプリとして発行し、それぞれに独自の名前とアクセス規則を付与することができます。パスを発行する場合は、アプリケーションに必要な画像、スクリプト、スタイル シートが、すべてそのパスに含まれていることを確認してください。 たとえば、アプリが `https://yourapp/app` にあり、 `https://yourapp/media` にあるイメージを使用している場合、 `https://yourapp/` をパスとして発行する必要があります。 この内部 URL は、ユーザーに表示されるランディング ページである必要はありません。 詳細については、「 [公開済みアプリのカスタム ホーム ページを設定する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-custom-home-page)参照してください。 |
    | **外部 URL** | ユーザーがネットワークの外部からアプリにアクセスするためのアドレス。 既定のアプリケーション プロキシ ドメインを使用しない場合は、 [Microsoft Entra アプリケーション プロキシのカスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)についてお読みください。 |
    | **事前認証** | ユーザーにアプリケーションへのアクセス権を付与する前にアプリケーション プロキシがユーザーを認証する方法。**Microsoft Entra ID** - アプリケーション プロキシは、ディレクトリとアプリケーションのアクセス許可を認証する Microsoft Entra ID でサインインするようにユーザーをリダイレクトします。 条件付きアクセスや多要素認証などの Microsoft Entra セキュリティ機能を利用できるように、**これを既定のオプション**のままにします。 Microsoft Defender for Cloud Apps を使用してアプリケーションを監視するために、Microsoft **Entra ID** が必要です。**パススルー** - ユーザーは、アプリケーションにアクセスするために Microsoft Entra ID に対して認証する必要はありません。 ただし、バックエンドで認証要件を設定できます。 |
    | **コネクタ グループ** | コネクタは、アプリケーションへのリモート アクセスを処理します。コネクタ グループを使用して、コネクタとアプリをリージョン、ネットワーク、または目的別に整理できます。 コネクタ グループをまだ作成していない場合、アプリは **Default** に割り当てられます。アプリケーションで接続に Websocket を使用する場合は、グループ内のすべてのコネクタがバージョン 1.5.612.0 以降である必要があります。 |
6. 必要に応じて、 **追加設定を**構成します。 ほとんどのアプリケーションでは、これらの設定は既定の状態のままにしてください。

    | フィールド | 内容 |
    | --- | --- |
    | **バックエンド アプリケーションのタイムアウト** | アプリケーションの認証と接続が遅い場合にのみ、この値を **Long** に設定します。 既定では、バックエンド アプリケーションのタイムアウトの長さは 85 秒です。 設定が長すぎると、バックエンドのタイムアウトが 180 秒に増加します。 |
    | **HTTP-Only Cookie を使用する** | HTTP 応答ヘッダーに HTTPOnly フラグを含めるように、アプリケーション プロキシ Cookie を選択してください。 リモート デスクトップ サービスを使用する場合、このオプションを未選択のままにします。 |
    | **永続的な Cookie を使用する** | このオプションを未選択のままにします。 この設定は、プロセス間で Cookie を共有できないアプリケーションにのみ使用してください。 Cookie 設定の詳細については、「 [Microsoft Entra ID でオンプレミス アプリケーションにアクセスするための Cookie 設定」を](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-cookie-settings)参照してください。 |
    | **ヘッダー内の URL を変換する** | 認証要求でアプリケーションで元のホスト ヘッダーが必要な場合を除き、オプションは選択したままにします。 |
    | **アプリケーション本文で URL を変換する** | HTML リンクが他のオンプレミス アプリケーションにハード コーディングされており、カスタム ドメインを使わない場合を除き、このオプションはオフのままにします。 詳細については、「 [アプリケーション プロキシを使用した変換のリンク](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-hard-coded-link-translation)」を参照してください。このアプリケーションを Microsoft Defender for Cloud Apps を使用して監視する場合に選択します。 詳細については、「 [Microsoft Defender for Cloud Apps と Microsoft Entra ID を使用したリアルタイム アプリケーション アクセス監視の構成](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-integrate-with-microsoft-cloud-application-security)」を参照してください。 |
    | **バックエンド TLS 証明書の検証** | アプリケーションのバックエンド トランスポート層セキュリティ (TLS) 証明書の検証を有効にする場合に選択します。 |
7. **追加**を選択します。

### 管理者の同意を付与する

新しいアプリケーション プロキシ アプリケーションを作成した後、Microsoft Entra 管理センターまたは Microsoft Graph PowerShell を使用して、**User.Read** の委任されたアクセス許可に管理者の同意を付与します。

## [Microsoft Entra 管理センター](#tab/microsoft-entra-admin-center)
1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Identity**&gt;**Applications**&gt;**Enterprise アプリケーション** に移動します。
3. 新しく作成したアプリケーション プロキシ アプリケーションを選択します。
4. 左側のナビゲーションで [ **アクセス許可** ] を選択します。
5. **[お使いのテナント] に管理者の同意を付与する** を選択します。
6. アクセス許可を確認し、[ **同意する**] を選択します。

## [Microsoft Graph PowerShell](#tab/microsoft-graph-powershell)
```powershell
# Connect with the required scope
Connect-MgGraph -Scopes "Application.ReadWrite.All", "DelegatedPermissionGrant.ReadWrite.All"

# Define variables
$appObjectId = "<your-enterprise-app-object-id>"
$microsoftGraphId = "00000003-0000-0000-c000-000000000000"  # Microsoft Graph
$userReadPermissionId = "e1fe6dd8-ba31-4d61-89e7-88639da4683d"  # User.Read

# Get the service principal for your app
$sp = Get-MgServicePrincipal -Filter "id eq '$appObjectId'"

# Get the Microsoft Graph service principal
$graphSp = Get-MgServicePrincipal -Filter "appId eq '$microsoftGraphId'"

# Create the delegated permission grant
New-MgOauth2PermissionGrant -ClientId $sp.Id `
    -ConsentType "AllPrincipals" `
    -ResourceId $graphSp.Id `
    -Scope "User.Read"
```

---

#### アクセス許可が付与されたことを確認する

1. Microsoft Entra 管理センターで、エンタープライズ アプリケーションに移動します。
2. **[アクセス許可]** を選択します。
3. **User.Read** が **管理者の同意** の下に表示され、[種類] が **委任**、[付与元] が **管理者の同意** になっていることを確認します。

### アプリケーションをテストする

アプリケーションが正しく追加されたことをテストする準備ができました。 次の手順で、アプリケーションにユーザー アカウントを追加し、サインインしてみます。

#### テスト用のユーザーを追加する

アプリケーションにユーザーを追加する前に、企業ネットワーク内からアプリケーションにアクセスするためのアクセス許可がユーザー アカウントに既にあることを確認します。

テスト ユーザーを追加するには:

1. **[エンタープライズ アプリケーション**] を選択し、テストするアプリケーションを選択します。
2. [ **はじめに**] を選択し、[ **テスト用にユーザーを割り当てる**] を選択します。
3. [ **ユーザーとグループ**] で、[ **ユーザーの追加]** を選択します。
4. [ **割り当ての追加]** で、[ **ユーザーとグループ**] を選択します。 [ **ユーザーとグループ** ] セクションが表示されます。
5. 追加したいアカウントを選択します。
6. [ **選択] を選択**し、[ **割り当て**] を選択します。

#### サインオンをテストする

アプリケーションに対する認証をテストするには:

1. テストするアプリケーションから、[ **アプリケーション プロキシ**] を選択します。
2. ページの上部にある [ **テスト アプリケーション** ] を選択してアプリケーションでテストを実行し、構成の問題がないか確認します。
3. まず、アプリケーションを起動して、アプリケーションへのサインインをテストします。 次に、診断レポートをダウンロードして、検出された問題の解決ガイダンスを確認します。

トラブルシューティングについては、 [アプリケーション プロキシの問題とエラー メッセージのトラブルシューティングを](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-troubleshoot)参照してください。

### リソースをクリーンアップする

完了したら、このチュートリアルで作成したすべてのリソースを削除します。

### トラブルシューティング

一般的な問題とそのトラブルシューティング方法について説明します。

#### アプリケーションを作成する/URL を設定する

エラーの詳細で、アプリケーションの修正方法に関する情報と提案を確認します。 ほとんどのエラー メッセージには、推奨される解決方法が記載されています。 一般的なエラーを避けるために、以下の点を確認してください。

- アプリケーション プロキシ アプリケーションを作成するアクセス許可を持った管理者であること
- 内部 URL が一意であること。
- 外部 URL が一意であること。
- URL が http または https から始まり、"/" で終わっていること。
- URL がドメイン名であって IP アドレスではないこと。

アプリケーションを作成する際、エラー メッセージは右上隅に表示されます。 通知アイコンを選択してエラー メッセージを表示することもできます。

#### カスタム ドメイン用の証明書のアップロード

カスタム ドメインを使用すると、外部 URL のドメインを指定できます。 カスタム ドメインを使用するには、そのドメインの証明書をアップロードする必要があります。 カスタム ドメインと証明書の使用については、「 [Microsoft Entra アプリケーション プロキシでのカスタム ドメインの操作](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)」を参照してください。

証明書のアップロード中に問題が発生した場合は、関連するエラー メッセージをポータルで探し、証明書の問題についての詳しい情報がないかどうかを調べてください。 証明書に関してよく発生する問題の例を次に示します。

- 証明書の有効期限切れ
- 証明書が自己署名されている
- 証明書の秘密キーがない

証明書をアップロードしようとすると、右上隅にエラー メッセージが表示されます。 通知アイコンを選択してエラー メッセージを表示することもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-application-gateway-waf"} -->
## Application Gateway WAF を使用してアプリケーションを保護する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-application-gateway-waf
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Microsoft Entra アプリケーション プロキシを使用して発行されたアプリの Web アプリケーション ファイアウォール (WAF) 保護を追加する方法。

### 概要

Microsoft Entra アプリケーション プロキシを使用して発行されたアプリの Web アプリケーション ファイアウォール (WAF) 保護を追加します。

Web アプリケーション ファイアウォールの詳細については、「Azure Application [Gateway の Azure Web アプリケーション ファイアウォールとは」](https://learn.microsoft.com/ja-jp/azure/web-application-firewall/ag/ag-overview)を参照してください。

### 展開の手順

この記事では、Application Gateway 上の Azure WAF で Microsoft Entra アプリケーション プロキシを使用して、インターネット上の Web アプリケーションを安全に公開する手順について説明します。

[Image: Azure WAF と Application Gateway を介してインターネットから Microsoft Entra アプリケーション プロキシと内部アプリケーション サーバーへの Web アプリケーション トラフィック フローを示すアーキテクチャ図。]

#### 内部アプリケーションにトラフィックを送信するように Azure Application Gateway を構成する

この記事では、Application Gateway 構成の一部の手順を省略しています。 Application Gateway の作成と構成の詳細なガイドについては、「[クイック スタート: Azure Application Gateway を使用した Web トラフィックのダイレクト - Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/azure/application-gateway/quick-create-portal)」を参照してください。

#### 1. プライベートに接続する HTTPS リスナーを作成する

ユーザーが企業ネットワークに接続したときに Web アプリケーションにプライベートにアクセスできるようにリスナーを作成します。

[Image: 企業ネットワーク ユーザーのプライベート アクセス設定を示す Application Gateway リスナー構成ページ。]

#### 2. Web サーバーを使用してバックエンド プールを作成する

この例では、バックエンド サーバーにインターネット インフォメーション サービス (IIS) がインストールされています。

[Image: IIS Web サーバーを示す Application Gateway バックエンド プールの構成。]

#### 3. バックエンド設定を作成する

バックエンド設定は、要求がバックエンド プール サーバーに到達する方法を決定します。

[Image: 要求ルーティング パラメーターを示す Application Gateway バックエンド設定の構成ページ。]

#### 4. リスナー、バックエンド プール、および前の手順で作成したバックエンド設定を結び付け、ルーティング規則を作成する

[Image: リスナーの選択手順を示す Application Gateway ルーティング規則の構成ページ。][Image: バックエンド プールとバックエンド設定の選択を示す Application Gateway ルーティング規則の構成ページ。]

#### 5. Application Gateway で WAF を有効にし、防止モードに設定する

[Image: [防止モード] が選択された状態で WAF が有効になっていることを示す Application Gateway WAF 構成ページ。]

### Microsoft Entra ID でアプリケーション プロキシを介してリモートアクセスされるようにアプリケーションを構成する

コネクタ VM、Application Gateway、バックエンド サーバーの両方が、Azure の同じ仮想ネットワークにデプロイされます。 このセットアップは、オンプレミスにデプロイされたアプリケーションとコネクタにも適用されます。

アプリケーションを Microsoft Entra ID のアプリケーション プロキシに追加する方法の詳細なガイドについては、「[チュートリアル: Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)のアプリケーション プロキシを介してリモート アクセスするためのオンプレミス アプリケーションを追加する」を参照してください。 プライベート ネットワーク コネクタに関するパフォーマンスに関する考慮事項の詳細については、「[Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-network-topology)を使用したトラフィック フローの最適化」を参照してください。

[Image: ポート 443 アクセスの内部 URL と外部 URL が一致する Microsoft Entra アプリケーション プロキシ構成。]

この例では、同じ URL が内部 URL と外部 URL として構成されています。 リモート クライアントは、アプリケーション プロキシを介して、ポート 443 でインターネット経由でアプリケーションにアクセスします。 企業ネットワークに接続されているクライアントは、アプリケーションにプライベートにアクセスします。 アクセスは、ポート 443 上の Application Gateway を介して直接行われます。 アプリケーション プロキシでカスタム ドメインを構成する詳細な手順については、「[Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)を使用してカスタム ドメインを構成する」を参照してください。

[Azure プライベート ドメイン ネーム システム (DNS) ゾーン](https://learn.microsoft.com/ja-jp/azure/dns/private-dns-getstarted-portal) は、A レコードで作成されます。 A レコードは、Application Gateway のプライベート フロントエンド IP アドレスに `www.fabrikam.one` をポイントします。 このレコードにより、コネクタ VM が Application Gateway に要求を送信することが保証されます。

### アプリケーションをテストする

[のテスト用にユーザーを追加](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application#add-a-user-for-testing)後、`https://www.fabrikam.one`にアクセスしてアプリケーションをテストできます。 ユーザーは Microsoft Entra ID で認証するように求められます。認証が成功すると、アプリケーションにアクセスします。

[Image: ユーザーに認証を求める Microsoft Entra ID サインイン ページ。][Image: 認証後のアプリケーション アクセスの成功を示すブラウザー。]

### 攻撃をシミュレートする

WAF が悪意のある要求をブロックしているかどうかをテストするには、基本的な SQL インジェクション署名を使用して攻撃をシミュレートできます。 (例: https://www.fabrikam.one/api/sqlquery?query=x%22%20or%201%3D1%20--&quot)。

[Image: WAF による SQL インジェクションの試行をブロックする HTTP 403 Forbidden エラーを示すブラウザー。]

HTTP 403 応答は、WAF が要求をブロックしたことを確認します。

Application Gateway [ファイアウォールのログ](https://learn.microsoft.com/ja-jp/azure/application-gateway/application-gateway-diagnostics#firewall-log) は、要求の詳細およびWAFがそれをブロックしている理由を提供します。

[Image: ブロックされた要求の詳細と SQL インジェクション規則違反を示す Application Gateway ファイアウォールのログ エントリ。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-azure-front-door"} -->
## Azure Front Door を使用して Microsoft Entra アプリケーション プロキシで地理的パフォーマンスを向上させる - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-azure-front-door
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Azure Front Door を使用してグローバル接続シナリオのパフォーマンスを最適化し、Microsoft Entra アプリケーション プロキシを使用して geo アクセラレーションを実現します。

### 概要

この記事では、待機時間の短縮とパフォーマンスの向上を実現するために、Azure Front Door を使用して Microsoft Entra アプリケーション プロキシを構成する方法について説明します。

### Azure Front Doorとは何ですか？

Azure Front Door は、クラウドまたはオンプレミスのインフラストラクチャから任意の場所のユーザーに、低待機時間で高スループットのコンテンツを大規模に提供します。 非常にスケーラブルな Microsoft プライベート グローバル ネットワーク上に構築された統合プラットフォームを使用して、静的で動的なコンテンツ配信を高速化します。 Azure Front Door の詳細については、「 [Azure Front Door とは」](https://learn.microsoft.com/ja-jp/azure/frontdoor/front-door-overview)を参照してください。

### デプロイメントの手順

Azure Front Door で Microsoft Entra アプリケーション プロキシを使用して、インターネット上の Web アプリケーションを安全に公開します。 この図では、デプロイの参照アーキテクチャを表します。

[Image: デプロイ アーキテクチャの図。]

### [前提条件]

- フロントドアサービス – スタンダード層またはクラシック層。
- 1 つのリージョンに存在するアプリ。
- アプリケーションに使用するカスタム ドメイン。
- アプリケーション プロキシ サブスクリプション ライセンスは、Microsoft Entra ID P1 または P2 で入手できます。 ライセンス オプションと機能の詳細については、 [Microsoft Entra の価格](https://www.microsoft.com/security/business/microsoft-entra-pricing)を参照してください。

### アプリケーション プロキシを構成する

Front Door のアプリケーション プロキシを構成します。

1. アプリケーション インスタンスの場所のコネクタをインストールします。 たとえば、 **米国西部などです**。 コネクタ グループをコネクタの適切なリージョンに割り当てます。 たとえば、 **北米**です。
2. アプリケーション プロキシを使用してアプリケーション インスタンスを設定します。
    - 内部 URL を内部ネットワーク上のアドレス (たとえば、 `contoso.org`) に設定します。
    - 外部 URL を、ユーザーがインターネットからアクセスするドメイン アドレスに設定します。 カスタム ドメインを構成する必要があります。 たとえば、`contoso.org` のようにします。 カスタム ドメインの構成の詳細については、「 [Microsoft Entra アプリケーション プロキシのカスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)」を参照してください。
    - アプリケーションを適切なコネクタ グループに割り当てます。 たとえば、 **北米**です。
    - アプリケーション プロキシによって生成された URL を記録します。 たとえば、`contoso.msappproxy.net` のようにします。
    - ドメイン ネーム システム (DNS) で CNAME エントリを構成します。 外部 URL を Azure Front Door エンドポイントにポイントします。 たとえば `contoso.org` を `contoso.msappproxy.net`にします。
3. Azure Front Door サービスで、アプリケーション プロキシによってアプリケーション用に生成された URL をバックエンド プールのバックエンドとして使用します。 たとえば、`contoso.msappproxy.net` のようにします。

次の表に、アプリケーション プロキシ構成のサンプルを示します。 サンプル シナリオでは、外部 URL としてサンプル アプリケーション ドメイン `www.contoso.org` を使用します。

| - | コンフィギュレーション | 追加情報 |
| --- | --- | --- |
| **内部 URL** | `nam.contoso.com` |  |
| **外部 URL** | `contoso.org` | インターネットからアクセスするためのカスタム ドメインを構成します。 |
| **コネクタ グループ** | `North America` | パフォーマンスを最適化するには、アプリケーション インスタンスに最も近い地理的な場所にあるコネクタ グループを選択します。 |

### Azure Front Doorの構成

Azure Front Door は、Standard、Premium、Classic など、さまざまなレベルで提供されています。 ユーザー設定に基づいてレベルを選択します。 レベルの比較の詳細については、 [Azure Front Door レベルの比較](https://learn.microsoft.com/ja-jp/azure/frontdoor/standard-premium/tier-comparison)に関するページを参照してください。

構成手順では、これらの定義を参照します。

- **エンドポイント名 - エンドポイント** のグローバルに一意の名前。 この設定を使用して、カスタム ドメインもオンボードします。 たとえば、front door エンドポイント名 `contoso-nam` は、エンドポイント ホスト名 `contoso-nam.azurefd.net` を生成し、カスタム ドメイン ホスト名 `contoso.org`使用します。
- **オリジンサーバー** - 設定はアプリケーションサーバーを指します。 Azure Front Door は、クライアント要求を配信元にルーティングします。 ルーティングは、指定した種類、ポート、優先度、および重みに基づいています。
- **配信元の種類** - 追加するリソースの種類。 Front Door では、 *App Service*、 *クラウド サービス*、またはストレージからのアプリケーション バックエンドの自動検出がサポート *されています*。 Azure 内の別のリソース、または Azure 以外のバックエンドの場合は、[ *カスタム* ホスト] を選択します。 たとえば、アプリケーション プロキシ サービスのバックエンドの *カスタム* ホストを選択します。
- **配信元ホスト名** - この設定は、バックエンドの配信元ホスト名を表します。 たとえば、`contoso.msappproxy.net` のようにします。
- **配信元ホスト ヘッダー** - この設定は、要求ごとにバックエンドに送信されるホスト ヘッダー値を表します。 たとえば、`contoso.org` のようにします。 詳細については、「[配信元と配信元グループ - Azure Front Door](https://learn.microsoft.com/ja-jp/azure/frontdoor/origin?pivots=front-door-standard-premium#origin-host-header)」を参照してください。

1. Front Door (Standard) 構成を作成します。
    - で Front Door の既定のドメインを生成するための`azurefd.net`を追加します。 たとえば、 `contoso-nam` はエンドポイントホスト名 `contoso-nam.azurefd.net`を生成します。
    - バックエンドリソースの*配信元タイプ*を追加します。 アプリケーション プロキシ リソースには **Custom** を使用します。
    - バックエンド ホスト名を表す *Origin ホスト名* を追加します。 たとえば、`contoso.msappproxy.net` のようにします。
    - 任意: Front Doorが静的コンテンツをキャッシュするように、ルーティングルールのキャッシュ機能を有効にします。
2. デプロイが完了し、Front Door Service の準備ができているかどうかを確認します。
3. Front Door サービスにわかりやすいドメイン ホスト名 URL を指定します。 Front Door のドメイン ホスト名を指すアプリケーション プロキシ外部 URL の CNAME レコードを作成します。 Front Door のドメイン ホスト名によって Front Door サービスが生成されます。 たとえば、 `contoso.org` は `contoso.azurefd.net`をポイントします。 カスタム ドメインの詳細については、「カスタム [ドメインを追加する方法 - Azure Front Door](https://learn.microsoft.com/ja-jp/azure/frontdoor/standard-premium/how-to-add-custom-domain)」を参照してください。
4. Front Door サービス ダッシュボードで Front Door Manager に移動し、カスタム ホスト名を持つドメインを追加します。 たとえば、`contoso.org` のようにします。
5. Front Door サービス ダッシュボードで配信元グループに移動します。 配信元の名前を選択し、配信元ホスト ヘッダーがバックエンドのドメインと一致すると検証します。 この例の配信元ホスト ヘッダーは `contoso.org`。

| - | コンフィギュレーション | 追加情報 |
| --- | --- | --- |
| **エンドポイント名** | • エンドポイント名: `contoso-nam` • フロント ドアで生成されたホスト名: `contoso-nam.azurefd.net` • カスタム ドメイン ホスト名: `contoso.org` | カスタム ドメイン ホスト名が必要です。 |
| **配信元のホスト名** | `contoso.msappproxy.net` | アプリケーション プロキシによってアプリ用に生成された URL が必要です。 |
| **コネクタ グループ** | `North America` | パフォーマンスを最適化するために、アプリケーション インスタンスの最も近い場所にあるコネクタ グループを選択します。 |

[Image: Azure Front Door 構成 1 のスクリーンショット。]

[Image: Azure Front Door Configuration 2 のスクリーンショット。]

[Image: Azure Front Door Configuration 3 のスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-back-end-kerberos-constrained-delegation-how-to"} -->
## Kerberos の制約付き委任のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-back-end-kerberos-constrained-delegation-how-to
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: Microsoft Entra アプリケーション プロキシで Kerberos の制約付き委任 (KCD) 構成のトラブルシューティングを行う方法について説明します。

### 概要

シングル サインオン (SSO) の方法は、アプリケーションによって異なります。 Microsoft Entra アプリケーション プロキシは、既定で Kerberos の制約付き委任 (KCD) を提供します。 アプリケーション プロキシでは、ユーザーは Kerberos を使用してプライベート アプリケーションに対して認証を行います。

この記事では、KCD 構成で最も一般的な問題をトラブルシューティングする方法について説明します。 これには、より複雑な実装に対して実行できる診断手順が含まれています。

この記事は以下を前提としています。

- Microsoft Entra アプリケーション プロキシがデプロイされ、KCD 以外のアプリケーションに一般的にアクセスできます。

    詳細については、「 [アプリケーション プロキシの概要](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)」を参照してください。
- 発行されたアプリケーションは、インターネット インフォメーション サービス (IIS) と Kerberos の Microsoft 実装に基づいています。
- サーバーホストとアプリケーション ホストは、単一の Microsoft Entra ドメイン内にあります。

    ドメイン間およびフォレスト間のシナリオの詳細については、ホワイト ペーパー「 [アプリケーション プロキシを使用した Kerberos の制約付き委任](https://aka.ms/KCDPaper)について」を参照してください。
- アプリケーションは、事前認証が有効になっている Microsoft Entra テナントで発行されます。 ユーザーは、フォーム ベースの認証を使用して認証する必要があります。

    リッチ クライアント認証のシナリオについては、この記事では説明しません。

### 考慮事項

次の一覧では、KCD の構成と Microsoft Entra アプリケーション プロキシでの使用に関する基本的な考慮事項について説明します。

- 基本的な構成ミスや一般的な間違いは、ほとんどの問題を引き起こします。 トラブルシューティングを開始する前に、「 [アプリケーション プロキシで KCD SSO を使用](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-sso-with-kcd)する」のすべての前提条件を確認してください。
- コネクタ ホストは、特定のローカル サイト ドメイン コントローラー (DC) とのみ通信するように制限されません。 変更される可能性があるため、使用する DC を確認します。
- クロスドメイン シナリオは、コネクタ ホストをローカル ネットワーク境界の外部にある可能性のある DC に誘導する紹介に依存します。 このような場合は、他の各ドメインを表す DC にトラフィックを送信することが同様に重要です。 そうしないと、委任は失敗します。
- コア リモート プロシージャ コール (RPC) トラフィックとの干渉による、コネクタ ホストとドメイン コントローラー (DC) 間のアクティブな侵入防止システム (IPS) または侵入検出システム (IDS) デバイスの回避。
- 単純なシナリオで委任をテストします。 シナリオで導入する変数が多いほど、より複雑な構成とトラブルシューティングが行われます。 時間を節約するには、テストを 1 つのコネクタに制限します。 問題が解決したら、コネクタを追加します。
- 環境要因は、問題の原因に寄与する可能性があります。 これらの要因を回避するには、テスト中に可能な限りアーキテクチャを最小限に抑えます。 たとえば、内部ファイアウォールアクセス制御リスト (ACL) が正しく構成されていないのが一般的です。 可能であれば、コネクタからすべてのトラフィックを DC およびバックエンド アプリケーションに直接送信します。
- コネクタを配置する最適な場所は、ターゲットにできるだけ近い場所です。 コネクタをテストするときにインラインに配置されるファイアウォールは、不要な複雑さを増し、調査を長引く可能性があります。
- KCD の問題を示す内容 いくつかの一般的なエラーは、KCD SSO が失敗していることを示しています。 問題の最初の兆候がブラウザーに表示されます。

    次の両方のスクリーンショットは、SSO エラーの同じ現象を示しています。アプリケーションへのユーザー アクセスが拒否されています。

    [Image: 正しくない KCD 構成エラーの例を示すスクリーンショット。エラー [不正な Kerberos の制約付き委任] が強調表示されています。]

    [Image: アクセス許可がないために承認が失敗した例を示すスクリーンショット。]

### トラブルシューティング

KCD の問題のトラブルシューティングは、3 つの段階で行うことができます。 KCD プロセスのこれらの部分を次の順序で確認します。

- クライアントの事前認証
- 委任サービス
- ターゲット アプリケーション

#### クライアントの事前認証

*クライアント事前認証* とは、ブラウザーを介してアプリケーションで認証を行う外部ユーザーのことです。 KCD SSO を機能させるには、Microsoft Entra ID に対する事前認証を行う機能が必要です。

最初にクライアントの事前認証をテストし、問題を解決します。 事前認証ステージは、KCD または公開されたアプリケーションには関係ありません。 Microsoft Entra ID にサブジェクト アカウントが存在することを確認することで、不一致を簡単に修正できます。 アプリケーションが使用できないかブロックされていないことを確認します。 通常、ブラウザーでのエラー応答は、原因を説明するのに十分な説明です。

#### 委任サービス

Kerberos 委任サービスは、Kerberos キー配布センター (KDC) から Kerberos サービス チケットを取得するプライベート ネットワーク コネクタです。 アプリ ユーザーは、チケットを使用してアプリケーションで認証を行います。

クライアントと Azure フロントエンド間の外部通信は、制約付き委任には影響しません。 これらの通信により、KCD が動作することだけが保証されます。 アプリケーション プロキシ サービスには、Kerberos チケットを取得する有効なユーザー ID が与えられます。 この ID がないと、KCD は発生できず、SSO は失敗します。

ブラウザーのエラー メッセージは、サインインが失敗する理由に関する有用な情報を提供します。 アプリケーションのサインインへの応答で、 `TransactionID` と `Timestamp` の値を記録します。 この情報は、動作をアプリケーション プロキシ イベント ログに表示されるイベントに関連付けるのに役立ちます。

[Image: Kerberos の制約付き委任構成エラー メッセージを示すスクリーンショット。]

イベント ログ内の対応するエントリは、イベント ID 13019 または 12027 です。 コネクタ イベント ログを表示するには、 **Applications and Services Logs**&gt;**Microsoft**&gt;**Microsoft Entra private network**&gt;**Connector**&gt;**Admin** に移動します。

制約付き委任の問題をトラブルシューティングするには:

1. アプリケーション アドレスの内部ドメイン ネーム システム (DNS) で、CNAME レコードではなく A レコードを使用します。
2. コネクタ ホストが、ターゲット アカウントのサービス プリンシパル名 (SPN) に委任するアクセス許可で構成されていることを確認します。 [ **任意の認証プロトコルを使用する** ] が選択されていることを確認します。 詳細については、 [SSO の構成](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-sso-with-kcd)に関するページを参照してください。
3. Microsoft Entra ID に SPN のインスタンスが 1 つだけ存在することを確認します。 1 つの SPN を確認するには、任意のドメイン メンバー ホストのコマンド プロンプトで、 `setspn -x`を実行します。
4. [発行された Kerberos トークンの最大サイズ](https://learn.microsoft.com/ja-jp/archive/blogs/askds/maxtokensize-and-windows-8-and-windows-server-2012)を制限するドメイン ポリシーが適用されていることを確認します。 トークン サイズが設定された最大値を超えると、ポリシーによってコネクタがトークンを取得できなくなります。
5. コネクタ ホストとドメイン KCD の間の交換をキャプチャするネットワーク トレースを実行することは、問題の詳細を取得するための次の最適な手順です。 詳細については、 [Microsoft Entra アプリケーション プロキシのトラブルシューティング](https://aka.ms/proxytshootpaper)に関するホワイト ペーパーを参照してください。

チケットが正常に動作している場合、アプリケーションが 401 エラーを返したために認証に失敗したことを示すイベントがログに表示される可能性があります。 このイベントは、ターゲット アプリケーションがチケットを拒否したことを示します。 トラブルシューティングの次の段階に進みます。

#### アプリケーション

ターゲット アプリケーションは、コネクタによって提供される Kerberos チケットを処理します。 この段階では、コネクタは、バックエンドへの最初のアプリケーション要求のヘッダーとして Kerberos サービス チケットを含めます。

アプリケーションの問題をトラブルシューティングするには:

1. アプリケーションにアクセスできることを確認します。 Azure portal で定義されている内部 URL を使用して、コネクタ ホスト上のブラウザーから直接サインインします。 サインインが成功すると、アプリケーションにアクセスできます。
2. ブラウザーとアプリケーションが認証に Kerberos を使用しているかどうかを確認します。 コネクタ ホストから、Internet Explorerの DevTools (**F12** を押します) または Fiddler を使用して、内部 URL を介してアプリケーションにアクセスします。 アプリケーションの応答で、Web 承認ヘッダーで "Negotiate" または "Kerberos" を探します。

    アプリケーションに対するブラウザーの応答には、 `YII`で始まる Kerberos BLOB が含まれています。Kerberos が実行されていることを確認します。 これに対し、Microsoft NT LAN Manager (NTLM) からの応答は常に `TlRMTVNTUAAB`で始まります。 Base64 からデコードされると、この応答は NTLM セキュリティ サポート プロバイダー (NTLMSSP) を読み取ります。 BLOB が `TlRMTVNTUAAB` で始まる場合、Kerberos は使用できません。 そうでない場合は、Kerberos を使用できる可能性があります。

    注

    Fiddler を使用する場合は、IIS のアプリケーション構成で拡張保護を一時的に無効にする必要があります。

    [Image: ブラウザーのネットワーク検査ダイアログを示すスクリーンショット。]

    このスクリーンショットの BLOB は、 `TIRMTVNTUAAB`で始まらない。 そのため、この例では Kerberos を使用でき、Kerberos BLOB は `YII` で始まらないのです。
3. IIS サイトで、プロバイダーの一覧から NTLM を一時的に削除します。 コネクタ ホスト上の Internet Explorer から直接アプリにアクセスします。 NTLM はプロバイダーの一覧に表示されなくなったため、Kerberos を使用してのみアプリケーションにアクセスできます。 アクセスに失敗した場合は、アプリケーションの構成に関する問題が示されます。 アプリケーションが Kerberos 認証を処理していません。
4. Kerberos を使用できない場合は、IIS でアプリケーションの認証設定を確認します。 **ネゴシエート**が一番上に表示され、NTLM がそのすぐ下にあることを確認します。 **Not Negotiate**、**Kerberos、Negotiate**、または **PKU2U** が表示される場合は、Kerberos が機能している場合にのみ続行します。

    [Image: Windows 認証プロバイダーを示すスクリーンショット。]
5. Kerberos と NTLM が設定された状態で、ポータルでアプリケーションの事前認証を一時的に無効にします。 外部 URL を使用して、ブラウザーでアプリケーションにアクセスしてみてください。 認証を求められます。 前の手順で使用したのと同じアカウントを使用します。 認証とサインインができない場合は、KCD ではなくバックエンド アプリケーションに問題があります。
6. ポータルで事前認証を再度有効にします。 外部 URL を介してアプリケーションに接続を試みることで、Azure 経由で認証します。 SSO が失敗すると、ブラウザーに "禁止" というエラー メッセージが表示され、ログにイベント ID 13022 が表示されます。

    `Microsoft Entra private network connector can't authenticate the user because the backend server responds to Kerberos authentication attempts with an HTTP 401 error.`

    [Image: HTTP 401 禁止エラー メッセージを示すスクリーンショット。]
7. IIS アプリケーションを確認します。 構成されているアプリケーション プールと SPN が、Microsoft Entra ID で同じアカウントを使用するように構成されていることを確認します。 IIS で、次のスクリーンショットに示すようにフォルダーに移動します。

    [Image: IIS アプリケーション構成ダイアログを示すスクリーンショット。]

    ID を確認し、このアカウントが SPN で構成されていることを確認します。 たとえば、コマンド プロンプトで `setspn –q http/spn.contoso.com` を実行します。

    [Image: SetSPN コマンド ウィンドウを示すスクリーンショット。]
8. ポータルで、定義されている SPN をアプリケーション設定と照合します。 アプリケーションのアプリ プールで、ターゲットの Microsoft Entra アカウントに設定されているのと同じ SPN が使用されていることを確認します。
9. IIS で、アプリケーションの **[構成エディター]** オプションを選択します。 **system.webServer/security/authentication/windowsAuthentication** に移動します。 **UseAppPoolCredentials** の値が True であることを確認**します**。

    [Image: IIS 構成アプリ プールの資格情報オプションを示すスクリーンショット。]

    必要に応じて、値を **True** に変更します。 次のコマンドを実行して、キャッシュされたすべての Kerberos チケットをバックエンド サーバーから削除します。

    ```powershell
    Get-WmiObject Win32_LogonSession | Where-Object {$_.AuthenticationPackage -ne 'NTLM'} | ForEach-Object {klist.exe purge -li ([Convert]::ToString($_.LogonId, 16))}
    ```
10. カーネル モードが有効になっている場合、Kerberos 操作が向上します。 ただし、要求されたサービスのチケットも、マシン アカウントを使用して復号化する必要があります。 このアカウントは *、ローカル システム*とも呼ばれます。 アプリケーションがファーム内の複数のサーバーでホストされている場合に KCD を中断するには、この値を **True** に設定します。
11. もう 1 つのチェックとして、拡張保護を無効にします。 一部のテスト シナリオでは、特定の構成で KCD が有効になっていると、拡張保護が KCD を壊しました。 このような場合、アプリケーションは既定の Web サイトのサブフォルダーとして発行されました。 このアプリケーションは、匿名認証専用に構成されています。 すべてのダイアログは非アクティブであり、使用可能な選択は行われません。これは、子オブジェクトがアクティブな設定を継承しないことを示唆しています。 テストすることをお勧めしますが、可能であれば、この値を **必ず有効** に復元してください。

    この追加のチェックにより、発行されたアプリケーションを使用する体制が整います。 委任するように構成されたコネクタをさらに生成できます。 詳細については、 [Microsoft Entra アプリケーション プロキシのトラブルシューティング](https://aka.ms/proxytshootpaper)に関する詳細な技術チュートリアルを参照してください。

それでもアプリケーション認証の問題を解決できない場合は、ポータルで直接サポート チケットを作成します。

### その他のシナリオ

Microsoft Entra アプリケーション プロキシは、アプリケーションに要求を送信する前に Kerberos チケットを要求します。 一部のアプリケーションでは、この認証方法がサポートされていません。 これらのアプリケーションは、より従来の認証手順に対応するように設定されています。 最初の要求は匿名です。これにより、アプリケーションは 401 エラーによってサポートされる認証の種類で応答できます。 [SSO の Kerberos 制約付き委任](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-sso-with-kcd)に関するページで説明されている手順を完了することで、この種類の Kerberos ネゴシエーションを設定できます。

マルチホップ認証は、アプリケーションが階層化されている場合に一般的に使用されます。 レベルには、バックエンドとフロントエンドが含まれます。 どちらの層でも認証が必要です。 たとえば、SQL Server Reporting Services を使用して階層化されたアプリケーションを作成できます。 詳細については、「 [Web 登録プロキシ ページの Kerberos の制約付き委任を構成する方法](https://learn.microsoft.com/ja-jp/troubleshoot/windows-server/identity/configure-kerberos-constrained-delegation)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-conceptual-connectors"} -->
## Microsoft Entra アプリケーション プロキシ コネクタについて - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-conceptual-connectors
- Service: entra-id / app-proxy
- Article date: 2026-03-15
- Summary: アプリケーション プロキシで使用される Microsoft Entra プライベート ネットワーク コネクタについて説明します。

### 概要

アプリケーション プロキシは、Microsoft Entra プライベート アクセス (Microsoft Entra プライベート ネットワーク コネクタ) と同じコネクタを使用します。 詳細については、「 [Microsoft Entra プライベート ネットワーク コネクタ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-configure-complex-application"} -->
## Microsoft Entra アプリケーション プロキシの複雑なアプリケーション - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-complex-application
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Microsoft Entra アプリケーション プロキシの複雑なアプリケーションについて説明します。

### 概要

アプリケーションは、多くの場合、複数の個々の Web アプリケーションで構成されます。 このような状況では、URL 内で異なるドメイン サフィックスまたは異なるポートまたはパスが使用されます。 個々の Web アプリケーション インスタンスは、個別の Microsoft Entra アプリケーション プロキシ アプリで発行する必要があります。 このような状況では、次の問題が発生する可能性があります。

- **事前認証:** クライアントは、Microsoft Entra アプリケーション プロキシ アプリごとにアクセス トークンまたは Cookie を個別に取得する必要があります。 複数の取得により、サインイン時に `microsoftonline.com`へのリダイレクトが増えます。
- **クロスオリジン リソース共有 (CORS):** CORS 呼び出しは、 `OPTIONS` メソッドを使用して、呼び出し元の Web アプリと対象の Web アプリの間の URL のアクセスを検証するために使用されます。 Microsoft Entra アプリケーション プロキシ クラウド サービスは、これらの呼び出しをブロックします。 要求に認証情報を含めることができないため、ブロックが発生します。
- **アプリ管理が不十分:** プライベート アプリにアクセスできるように複数のエンタープライズ アプリが作成され、アプリ管理エクスペリエンスに摩擦が加わります。

次の図は、複雑なアプリケーション ドメイン構造の例を示しています。

[Image: プライマリ アプリケーションとセカンダリ アプリケーション間のリソース共有を示す複雑なアプリケーションのドメイン構造の図。]

[Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)を使用すると、さまざまなドメインにまたがる複数の URL で構成される複雑なアプリケーション発行を使用して、これらの課題に対処できます。

[Image: 複数のアプリケーション セグメント定義を持つ複雑なアプリケーションの図。]

複雑なアプリには複数のアプリ セグメントがあります。 各アプリ セグメントには、内部 URL と外部 URL があります。 1 つの条件付きアクセス ポリシーがアプリに関連付けられています。 外部 URL へのアクセスは、同じポリシー セットを使用した事前認証で機能します。 これらのポリシーは、すべてのアプリ セグメントに適用されます。

複雑なアプリには、いくつかの利点があります。

- ユーザー認証
- CORS の問題の軽減
- 内部 URL 内の異なるドメイン サフィックスまたは異なるポートまたはパスへのアクセス

この記事では、ご利用の環境でワイルドカード アプリケーションの発行を構成する方法について説明します。

### 複雑なアプリケーションのアプリケーション セグメントの特性

複雑なアプリケーションのアプリケーション セグメントには、次の特性があります。

- アプリケーション セグメントは、ワイルドカード アプリケーションでのみ構成されます。
- 外部 URL と代替 URL は、それぞれアプリケーションのワイルドカード外部 URL ドメインと代替 URL ドメインに一致する必要があります。
- アプリケーション セグメント URL (内部と外部) は、複雑なアプリケーション間で一意性を維持する必要があります。
- CORS ルール (省略可能) は、アプリケーション セグメントごとに構成できます。
- アクセスは、複雑なアプリケーションに対して定義されたアプリケーション セグメントにのみ付与されます。
    注

    すべてのアプリケーション セグメントを削除すると、複雑なアプリケーションはワイルドカード アプリケーションのように動作し、指定されたドメインの有効な URL にアクセスできます。
- 内部 URL は、アプリケーション セグメントと通常のアプリケーションの両方として定義できます。
    注

    通常のアプリケーションは、複雑なアプリ (ワイルドカード アプリケーション) よりも常に優先されます。

### [前提条件]

次の必要条件を完了します。

- アプリケーション プロキシを有効にし、アプリケーションに直接接続できるコネクタをインストールします。 オンプレミス環境の準備、コネクタのインストールと登録、コネクタのテストを行う方法については、 [アプリケーション プロキシを介したリモート アクセス用](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application) のオンプレミス アプリケーションの追加に関するチュートリアルを参照してください。

### 複雑なアプリケーションのアプリケーション セグメントを構成する

注

[Microsoft Entra ID P1 または P2 サブスクリプション](https://www.microsoft.com/security/business/microsoft-entra-pricing)では、複雑な分散アプリケーションごとに 2 つのアプリケーション セグメントがサポートされています。

アプリケーション セグメントを含むアプリケーション プロキシを介して複雑な分散アプリを発行するには:

1. [ワイルドカード アプリケーションを作成します。](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-wildcard#create-a-wildcard-application)
2. アプリケーション プロキシの基本設定ページで、[ **アプリケーション セグメントの追加]** を選択します。

    [Image: アプリケーション セグメントを追加するためのリンクのスクリーンショット。]
3. [アプリケーション セグメントの管理と構成] ページで、[ **+ アプリ セグメントの追加]** を選択します。

    [Image: [アプリケーション セグメントの管理と構成] ページのスクリーンショット。]
4. **内部 URL を入力します**。
5. **[外部 URL]** ボックスの一覧からカスタム ドメインを選択します。
6. CORS ルールを追加します (省略可能)。 詳細については、「 [CORS 規則の構成」を](https://learn.microsoft.com/ja-jp/graph/api/resources/corsconfiguration_v2?view=graph-rest-beta&preserve-view=true)参照してください。
7. **を選択して**を作成します。

    [Image: アプリケーション セグメントコンテキストペインの追加または編集のスクリーンショット。]
8. アプリケーションにユーザーを割り当てます。

アプリケーション セグメントを編集または更新するには、[アプリケーション セグメントの管理と構成] ページの一覧からアプリケーション セグメントを選択します。 必要に応じて、アプリケーション セグメントのカスタム ドメインの証明書をアップロードし、ドメイン ネーム システム (DNS) レコードを更新します。

### シングル サインオン (SSO) の構成

注

統合 Windows 認証 (IWA) でのシングル サインオンでは、ワイルドカード サービス プリンシパル名 (SPN) はサポートされていません。 たとえば、 `http/*.contoso.com` などのワイルドカードでは、すべてのセグメントに対して `http/app.contoso.com` など、単一の構成済み SPN が使用されます。

### DNS レコードを更新する

Important

アプリケーション セグメントを編集するときにポータル UI に表示される CNAME の手順は、このセクションの手順とは異なる場合があります。 複雑な (ワイルドカード) アプリケーションの場合は、ポータルに表示される汎用の`tenant.runtime.msappproxy.net` エンドポイントではなく、`.msappproxy.net`をポイントして、ここで説明する CNAME 構成を常に使用します。

カスタム ドメインを使用する場合は、外部 URL の CNAME レコードを含む DNS エントリを作成します。 たとえば、 `*.adventure-works.com` アプリケーション プロキシ エンドポイントの外部 URL をポイントします。 ワイルドカード アプリケーションの場合は、CNAME レコードをテナント ランタイム エンドポイント ( `<yourAADTenantId>.tenant.runtime.msappproxy.net`) にポイントします。

または、個々のアプリケーション セグメントごとに CNAME レコードを持つ専用 DNS エントリを次のように作成できます。

>
> `External URL of the application segment` &gt; `<yourAADTenantId>.tenant.runtime.msappproxy.net`

さらに、同じ DNS ゾーンにアプリケーション ID の CNAME レコードを追加する必要があります。

>
> `<yourAppId>` &gt; `<yourAADTenantId>.tenant.runtime.msappproxy.net`

複合アプリに割り当てられているコネクタ グループが既定のコネクタ グループのリージョンにない場合は、DNS エントリで次のいずれかのドメイン サフィックスを使用する必要があります。

| コネクタの割り当て済みリージョン | 外部リンク |
| --- | --- |
| アジア | `<yourAADTenantId>.asia.tenant.runtime.msappproxy.net` |
| オーストラリア | `<yourAADTenantId>.aus.tenant.runtime.msappproxy.net` |
| ヨーロッパ | `<yourAADTenantId>.eur.tenant.runtime.msappproxy.net` |
| 北米 | `<yourAADTenantId>.nam.tenant.runtime.msappproxy.net` |

アプリケーション プロキシの詳細な手順については、「[チュートリアル: Microsoft Entra ID のアプリケーション プロキシを使用してリモート アクセスするためのオンプレミス アプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-configure-connectors-with-proxy-servers"} -->
## 既存のオンプレミス プロキシ サーバーおよび Microsoft Entra ID と連携する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-connectors-with-proxy-servers
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: 送信プロキシ サーバーを使用して Microsoft Entra プライベート ネットワーク コネクタを構成します。 プロキシのバイパス、プロキシ経由のルーティング、コネクタとバックエンド アプリ間のプロキシの配置について説明します。

### 概要

送信プロキシ サーバーを使用するように Microsoft Entra プライベート ネットワーク コネクタを構成します。 この記事では、ネットワーク環境にプロキシ サーバーが既に存在することを前提としています。

この記事では、次の主なデプロイ シナリオについて説明します。

- オンプレミスの送信プロキシをバイパスするようにコネクタを構成する。
- 送信プロキシを使用して Microsoft Entra アプリケーション プロキシにアクセスにするようにコネクタを構成する。
- コネクタとバックエンド アプリケーション間のプロキシを使用して構成する。

コネクタのしくみの詳細については、「 [Microsoft Entra プライベート ネットワーク コネクタ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors)」を参照してください。

### 送信プロキシをバイパス

コネクタには、送信要求を行う基になる OS コンポーネントがあります。 これらのコンポーネントは、Web プロキシ自動発見 (WPAD) を使って自動的にネットワーク上のプロキシ サーバーの特定を試みます。

この OS コンポーネントは、`wpad.domainsuffix` のドメイン ネーム システム (DNS) 検索を実行して、プロキシ サーバーの検出を試みます。 この検索が DNS で解決すると、`wpad.dat` のインターネット プロトコル (IP) アドレスに対して HTTP 要求が作成されます。 この要求が、環境におけるプロキシ構成スクリプトになります。 コネクタは、このスクリプトを使用して送信プロキシ サーバーを選択します。 ただし、プロキシでさらに構成設定が必要なため、コネクタ トラフィックは引き続き失敗する可能性があります。

オンプレミスのプロキシをバイパスして Microsoft Entra アプリケーション プロキシ サービスへの直接接続を使用するように、コネクタを構成できます。 必要な構成が少ないため、直接接続がお勧めです。 ただし、一部のネットワーク ポリシーでは、トラフィックがローカル プロキシ サーバーを経由する必要があります。

コネクタの送信プロキシの使用を無効にするには、`C:\Program Files\Microsoft Entra private network connector\MicrosoftEntraPrivateNetworkConnectorService.exe.config` ファイルを編集し、コード サンプルに示す `system.net` セクションを追加します。

```xml
<?xml version="1.0" encoding="utf-8" ?>
<configuration>
  <system.net>
    <defaultProxy enabled="false"></defaultProxy>
  </system.net>
  <runtime>
    <gcServer enabled="true"/>
  </runtime>
  <appSettings>
    <add key="TraceFilename" value="MicrosoftEntraPrivateNetworkConnector.log" />
  </appSettings>
</configuration>
```

Connector Updater サービスもプロキシをバイパスするようにするには、`MicrosoftEntraPrivateNetworkConnectorUpdaterService.exe.config` ファイルに同じような変更を加えます。 このファイルは `C:\Program Files\Microsoft Entra private network connector Updater` にあります。

既定の `.config` ファイルに戻す必要がある場合に備えて、元のファイルのコピーを作成するようにしてください。

### 送信プロキシ サーバーの使用

一部の環境では、すべての送信トラフィックが例外なく送信プロキシを経由する必要がある場合があります。 そのような場合は、プロキシのバイパスは選択肢にありません。

次の図に示すように、コネクタのトラフィックが送信プロキシを経由するように構成できます。

[Image: Microsoft Entra アプリケーション プロキシへの送信プロキシを経由するようにコネクタ トラフィックを構成する。]

送信トラフィックしかないため、ファイアウォール経由で受信アクセスを構成する必要はありません。

注

アプリケーション プロキシは、他のプロキシに対する認証をサポートしていません。 コネクタ/アップデータのネットワーク サービス アカウントは、認証を求められることなく、プロキシに接続できる必要があります。

#### 手順 1:送信プロキシを経由するようにコネクタと関連サービスを構成する

WPAD が環境内で有効になっており、適切に構成されている場合、コネクタは送信プロキシ サーバーを自動的に検出し、それを使用しようとします。 一方で、送信プロキシを経由するようにコネクタを明示的に構成することができます。

これを行うには、`C:\Program Files\Microsoft Entra private network connector\MicrosoftEntraPrivateNetworkConnectorService.exe.config` ファイルを編集し、コード サンプルに示す `system.net` セクションを追加します。 `proxyserver:8080` を変更して、ローカル プロキシ サーバーの名前または IP アドレスとポートを反映させます。 IP アドレスを使用している場合でも、値にはプレフィックス `http://` を付ける必要があります。

```xml
<?xml version="1.0" encoding="utf-8" ?>
<configuration>
  <system.net>  
    <defaultProxy>   
      <proxy proxyaddress="http://proxyserver:8080" bypassonlocal="True" usesystemdefault="True"/>   
    </defaultProxy>  
  </system.net>
  <runtime>
    <gcServer enabled="true"/>
  </runtime>
  <appSettings>
    <add key="TraceFilename" value="MicrosoftEntraPrivateNetworkConnector.log" />
  </appSettings>
</configuration>
```

次に、`C:\Program Files\Microsoft Entra private network connector Updater\MicrosoftEntraPrivateNetworkConnectorUpdaterService.exe.config` ファイルに同様の変更を加えて、プロキシを使用するように Connector Updater サービスを構成します。

注

**defaultProxy** が MicrosoftEntraPrivateNetworkConnectorService.exe.config で (既定で) 構成されていない場合、コネクタ サービスでは、`%SystemRoot%\Microsoft.NET\Framework64\v4.0.30319\Config\machine.config` での使用のために **defaultProxy** 構成が評価されます。同じことが、Connector Updater サービス (MicrosoftEntraPrivateNetworkConnectorUpdaterService.exe.config) にも当てはまります。

#### 手順 2:コネクタと関連サービスからのトラフィックが経由して流れることを許可するようにプロキシを構成する

送信プロキシで考慮すべき点は次の 4 つです。

- プロキシ送信規則
- プロキシの認証
- プロキシ ポート
- トランスポート層セキュリティ (TLS) 検査

##### プロキシ送信規則

次の URL へのアクセスを許可します。

| URL | 港 / ポート | 用途 |
| --- | --- | --- |
| \*.msappproxy.net\*.servicebus.windows.net | 443/HTTPS | コネクタとアプリケーション プロキシ クラウド サービスの間の通信。 |
| crl3.digicert.comcrl4.digicert.comocsp.digicert.comcrl.microsoft.comoneocsp.microsoft.comocsp.msocsp.com | 80/HTTP | コネクタは、これらの URL を使用して証明書を検証します。 |
| login.windows.netsecure.aadcdn.microsoftonline-p.com\*.microsoftonline.com\*.microsoftonline-p.com\*.msauth.net\*.msauthimages.net\*.msecnd.net\*.msftauth.net\*.msftauthimages.net\*.phonefactor.netenterpriseregistration.windows.netmanagement.azure.compolicykeyservice.dc.ad.msft.netctldl.windowsupdate.com | 443/HTTPS | コネクタでは、登録プロセス中にこれらの URL が使用されます。 |
| ctldl.windowsupdate.comwww.microsoft.com/pkiops | 80/HTTP | コネクタでは、登録プロセス中にこれらの URL が使用されます。 |

ファイアウォールまたはプロキシで DNS 許可リストを構成できる場合は、`*.msappproxy.net` および `*.servicebus.windows.net` への接続を許可できます。

完全修飾ドメイン名 (FQDN) による接続を許可することはできず、代わりに IP 範囲を指定する必要がある場合は、これらのオプションを使用します。

- すべてのアクセス先に対するコネクタの送信アクセスを許可する。
- Azure データセンターの全 IP アドレス範囲に対するコネクタの送信アクセスを許可する。 Azure データセンターの IP 範囲の一覧を使用するうえでの課題は、それらが毎週更新されることにあります。 アクセス規則が適宜更新されるようにプロセスを整備する必要があります。 IP アドレスのサブセットのみを使用すると、構成が分断されます。 最新の Azure データ センター IP 範囲は https://download.microsoft.com にダウンロードされます。 検索語「`Azure IP Ranges and Service Tags`」を使用します。 必ず関連するクラウドを選択してください。 たとえば、パブリック クラウドの IP 範囲は、「`Azure IP Ranges and Service Tags – Public Cloud`」を検索すると見つかります。 米国政府のクラウドは、「`Azure IP Ranges and Service Tags – US Government Cloud`」を検索すると見つかります。

##### プロキシの認証

プロキシの認証は現在サポートされていません。 現在の推奨事項は、コネクタがインターネットの宛先に匿名でアクセスできるようにすることです。

##### プロキシ ポート

コネクタは、CONNECT メソッドを使用して TLS ベースの送信接続を確立します。 このメソッドにより、送信プロキシを経由するトンネルが設定されます。 プロキシ サーバーが、ポート 443 と 80 へのトンネリングを許可するように構成します。

注

Service Bus を HTTPS 経由で実行する場合は、ポート 443 が使用されます。 ただし、既定では、Service Bus は直接伝送制御プロトコル (TCP) 接続を試みて、直接接続に失敗した場合にのみ HTTPS に戻ってきます。

##### TLS インスペクション

コネクタのトラフィックに問題が生じるため、コネクタのトラフィックには TLS インスペクションを使用しないでください。 コネクタは証明書を使用してアプリケーション プロキシ サービスに対する認証を行いますが、その証明書が TLS インスペクションの間に失われることがあります。

### コネクタとバックエンド アプリケーション間のプロキシを使用して構成する

一部の環境では、バックエンド アプリケーションへの通信での転送プロキシの使用は、特別な要件になります。 フォワード プロキシを有効にするには、次の手順に従います。

#### 手順 1:必要なレジストリ値をサーバーに追加する

1. デフォルトのプロキシの使用を有効にするには、 にあるコネクタ構成レジストリ キーにレジストリ値 `UseDefaultProxyForBackendRequests = 1``HKEY_LOCAL_MACHINE\Software\Microsoft\Microsoft Entra private network connector` を追加します。

#### 手順 2:netsh コマンドを使用してプロキシ サーバーを手動で構成する

1. グループ ポリシー `Make proxy settings per-machine`を有効にします。 グループ ポリシーは `Computer Configuration\Policies\Administrative Templates\Windows Components\Internet Explorer` にあります。 ユーザーごとにポリシーを設定するのではなく、グループ ポリシーを設定する必要があります。
2. サーバーで `gpupdate /force` を実行します。 または、グループ ポリシーが更新されたことを確認するには、サーバーを再起動します。
3. 管理者権限で管理者特権でのコマンド プロンプトを起動し、`control inetcpl.cpl` を入力します。
4. 必要なプロキシ設定を構成します。

この設定によって、コネクタは Azure との通信とバックエンド アプリケーションとの通信に同じ転送プロキシを使用するようになります。 フォワード プロキシを変更するには、ファイル `MicrosoftEntraPrivateNetworkConnectorService.exe.config` を変更します。 転送プロキシの構成については、「*送信プロキシをバイパスする*」および「*送信プロキシ サーバーを使用する*」のセクションで説明します。

注

オペレーティング システムでは、さまざまな方法でインターネット プロキシを構成できます。 `NETSH WINHTTP` によって構成されたプロキシ設定 (確認するには `NETSH WINHTTP SHOW PROXY` を実行します) は、手順 2 で構成したプロキシ設定をオーバーライドします。

コネクタ アップデータ サービスはマシン プロキシを使用します。 この設定は `MicrosoftEntraPrivateNetworkConnectorUpdaterService.exe.config` ファイルにあります。

### コネクタのプロキシの問題とサービスの接続の問題のトラブルシューティング

これですべてのトラフィックがプロキシを経由します。 問題が生じた場合は、次のトラブルシューティング情報が役に立ちます。

コネクタの接続の問題を特定してトラブルシューティングを行う最善の方法は、コネクタ サービスの開始時にネットワーク キャプチャを実行することです。 ネットワーク トレースをキャプチャおよびフィルター処理する際に役立つヒントを簡単に紹介します。

任意の監視ツールを使用することができます。 ここでは、Microsoft Message Analyzer を使用しています。

注

[Microsoft Message Analyzer (MMA) は廃止](https://learn.microsoft.com/ja-jp/openspecs/blog/ms-winintbloglp/dd98b93c-0a75-4eb0-b92e-e760c502394f)され、2019 年 11 月 25 日に microsoft.com サイトからダウンロード パッケージが削除されました。 現時点では、開発における Microsoft Message Analyzer の Microsoft 代替製品はありません。 同様の機能については、Wireshark などの Microsoft 以外のパートナーのネットワーク プロトコル アナライザー ツールを使用することを検討してください。

以下に示すのは Message Analyzer の例ですが、原則はどの分析ツールでも同じです。

#### コネクタ トラフィックのキャプチャを実行する

最初のトラブルシューティングでは、次の手順を実行します。

1. `services.msc` から、Microsoft Entra プライベート ネットワーク コネクタ サービスを停止します。

    [Image: services.msc の Microsoft Entra プライベート ネットワーク コネクタ サービス。]
2. *Message Analyzer* を管理者として実行します。
3. **[Start Local Trace]\(ローカル トレースの開始\)** を選択します。
4. Microsoft Entra プライベート ネットワーク コネクタ サービスを開始します。
5. ネットワーク キャプチャを停止します。

    [Image: [ネットワーク キャプチャの停止] ボタンを示すスクリーンショット。]

#### コネクタのトラフィックが送信プロキシをバイパスするかどうかを確認する

コネクタがアプリケーション プロキシ サービスに直接接続するよう構成している場合、ポート 443 での `SynRetransmit` の応答は、ネットワークまたはファイアウォールの問題が発生していることを示しています。

Message Analyzer フィルターを使用して、失敗した伝送制御プロトコル (TCP) 接続試行を識別します。 フィルターのボックスに `property.TCPSynRetransmit` と入力し、 **[Apply]\(適用\)** を選択します。

同期 (SYN) パケットは、TCP 接続を確立するために最初に送信されるパケットです。 このパケットにより応答が返されない場合は、SYN パケットの送信が再試行されます。 フィルターを使用すると、再送信されたすべての SYN パケットが表示されます。 次に、これらの SYN パケットがコネクタ関連のトラフィックに対応するかどうかを調べます。

#### コネクタのトラフィックが送信プロキシを使用しているかどうかを確認する

プライベート ネットワーク コネクタ トラフィックがプロキシ サーバーを経由するように構成した場合は、プロキシへの失敗した `https` 接続を探します。

メッセージ アナライザー フィルターを使用して、プロキシへの失敗した HTTPS 接続試行を識別します。 メッセージ アナライザー フィルターに「`(https.Request or https.Response) and tcp.port==8080`」と入力し、`8080` をご使用のプロキシ サービス ポートに置き換えます。 **[Apply]\(適用\)** を選択してフィルター結果を確認します。

前述のフィルターにより、プロキシ ポートとの間の HTTPS 要求と応答のみが表示されます。 プロキシ サーバーとの通信を示す CONNECT 要求を調べます。 成功すると、HTTP OK (200) の応答が表示されます。

他の応答コード (407 や 502 など) が表示される場合は、プロキシが認証を必要としているか、何らかの理由でトラフィックを許可しないことを示しています。 その場合は、プロキシ サーバー サポート チームのサポートを得てください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-configure-cookie-settings"} -->
## アプリケーション プロキシ Cookie の設定 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-cookie-settings
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Microsoft Entra ID は、アクセス Cookie とセッション Cookie を使用して、アプリケーション プロキシを介してオンプレミスのアプリケーションにアクセスします。 この記事では、Cookie の設定を使用して構成する方法について説明します。

### 概要

Microsoft Entra ID は、アクセス Cookie とセッション Cookie を使用して、アプリケーション プロキシを介してオンプレミスのアプリケーションにアクセスします。 アプリケーション プロキシ Cookie の設定を構成する方法について説明します。

### Cookie 設定の概要

[アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy) では、次のアクセスとセッション Cookie の設定が使用されます。

| Cookie 設定 | 既定値 | 説明 | 推奨事項 |
| --- | --- | --- | --- |
| [HTTP 専用 Cookie を使用する] | **いいえ** | **はい** 。アプリケーション プロキシが HTTP 応答ヘッダーに HTTPOnly フラグを含めることができます。 このフラグを使うと、クライアント側スクリプト (CSS) による Cookie のコピーや変更の防止など、セキュリティに関する追加のベネフィットが提供されます。HTTP-Only 設定を使用できるようになる前に、アプリケーション プロキシは、セキュリティで保護されたトランスポート層セキュリティ (TLS) チャネルを介して Cookie を暗号化して送信し、変更から保護していました。 | 追加のセキュリティ上の利点があるため、[ **はい** ] を使用します。セッション Cookie へのアクセスが必要なクライアントまたはユーザー エージェントには **、No** を使用します。 たとえば、アプリケーション プロキシを介してリモート デスクトップ ゲートウェイ サーバーに接続するリモート デスクトップ プロトコル (RDP) または Microsoft ターミナル サービス クライアント (MSTSC) には **No** を使用します。 |
| [Use Secure Cookie] (セキュリティで保護された Cookie を使用する) | **はい** | **はい** 。アプリケーション プロキシは、HTTP 応答ヘッダーに Secure フラグを含めることができます。 セキュリティで保護された Cookie は、HTTPS などの TLS でセキュリティ保護されたチャネル経由で Cookie を送信することによってセキュリティを拡張します。 TLS を使うと、Cookie はクリアテキストで送信されなくなります。 | 追加のセキュリティ上の利点があるため、[ **はい** ] を使用します。 |
| [Use Persistent Cookie] (永続的な Cookie を使用する) | **いいえ** | **[はい** ] を使用すると、アプリケーション プロキシは、Web ブラウザーが閉じられたときにアクセス Cookie の有効期限が切れないように設定できます。 この永続性は、アクセス トークンの期限が切れるか、またはユーザーが永続的な Cookie を手動で削除するまで持続します。 | ユーザーの認証を維持することに関連するセキュリティ リスクがあるため、 **No を** 使用します。プロセス間で Cookie を共有できない古いアプリケーションの場合にのみ、[ **はい] を** 使用します。 永続的な Cookie を使用するのではなく、プロセス間での Cookie の共有を処理するようにアプリケーションを更新する方が適切です。 たとえば、永続的な Cookie は、ユーザーが SharePoint サイトからエクスプローラー ビューで Office ドキュメントを開けるようにするために必要になることがあります。 永続的な Cookie がない場合、ブラウザー、エクスプローラー プロセス、および Office プロセスの間でアクセス Cookie が共有されていないと、この操作は失敗する可能性があります。 |

### SameSite Cookie

[SameSite](https://web.dev/articles/samesite-cookies-explained) 属性を指定しない Cookie は、**SameSite=Lax** に設定されているかのように扱われます。 `SameSite` 属性では、同一サイト コンテキストに Cookie を制限する方法を宣言します。 `Lax` に設定すると、Cookie は同一サイト要求またはトップレベル ナビゲーションにのみ送信されます。 ただし、アプリケーション プロキシでは、セッション中にユーザーがサインインし続けるために、これらの Cookie をサード パーティのコンテキストで保持する必要があります。 この要件のため、次の更新が行われました。

- **SameSite** 属性を **None** に設定すると、アプリケーション プロキシ セッション Cookie がサードパーティのコンテキストで送信されます。
- [ **セキュリティで保護された Cookie を使用** する] 設定を既定として [ **はい** ] を使用するように設定します。 Chrome では、 `Secure` フラグを使用しない Cookie が拒否されます。 この既定の設定は、アプリケーション プロキシを介して発行されたすべての既存のアプリケーションに適用されます。 アプリケーション プロキシのアクセス Cookie は Secure に設定され、HTTPS 経由でのみ送信されます。 このセキュリティで保護された Cookie の既定値は、セッション Cookie にのみ適用されます。

さらに、バックエンド アプリケーションにサード パーティ コンテキストを必要とする Cookie がある場合は、`SameSite=None` を使うようにアプリケーションを変更して、明示的にオプトインする必要があります。 アプリケーション プロキシは、`Set-Cookie` ヘッダーをその URL に変換して、その設定を適用します。

### Microsoft Entra 管理センターで Cookie 設定を設定する

Microsoft Entra 管理センターを使用して Cookie 設定を設定するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**アプリケーションプロキシ**に移動します。
3. [ **追加設定] で**、Cookie の設定を **[はい** ] または **[いいえ]** に設定します。
4. [ **保存] を** 選択して変更を適用します。

### PowerShell を使用して現在の Cookie 設定を表示する

アプリケーションの現在の Cookie 設定を確認するには、次の PowerShell コマンドを使用します。

```powershell
Get-MgBetaApplication -ApplicationId <Id> | FL *
```

### PowerShell を使用して Cookie 設定を設定する

次の PowerShell コマンドでは、 `<Id>` はアプリケーションの **ID です** 。

**Http-Only クッキー**

```powershell
Set-EntraBetaApplicationProxyApplication -ApplicationId <Id> -IsHttpOnlyCookieEnabled $true 
Set-EntraBetaApplicationProxyApplication -ApplicationId <Id> -IsHttpOnlyCookieEnabled $false
```

**セキュリティで保護された Cookie**

```powershell
Set-EntraBetaApplicationProxyApplication -ApplicationId <Id> -IsSecureCookieEnabled $true 
Set-EntraBetaApplicationProxyApplication -ApplicationId <Id> -IsSecureCookieEnabled $false
```

**永続的な Cookie**

```powershell
Set-EntraBetaApplicationProxyApplication -ApplicationId <Id> -IsPersistentCookieEnabled $true 
Set-EntraBetaApplicationProxyApplication -ApplicationId <Id> -IsPersistentCookieEnabled $false
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-configure-custom-home-page"} -->
## Microsoft Entra アプリケーション プロキシを使用した発行済みアプリのカスタム ホーム ページ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-custom-home-page
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Microsoft Entra アプリケーション プロキシを介して発行されたアプリケーションのカスタム ホーム ページ URL を設定して、ユーザーがサインイン後に正しい内部ページに移動するようにします。

### 概要

この記事では、ユーザーをカスタム ホーム ページに移動させるようにアプリを構成する方法について説明します。 アプリケーション プロキシを使用してアプリを発行するときに内部 URL を設定しますが、それが、最初にユーザーに対して表示すべきページでない場合があります。 ユーザーがアプリにアクセスするときに正しいページを取得するように、カスタム ホーム ページを設定します。 ユーザーは、Microsoft Entra My Apps または Microsoft 365 アプリ起動ツールからアプリにアクセスするかどうかに関係なく、設定したカスタム ホーム ページを表示します。

ユーザーがアプリを起動すると、アプリは既定でルート ドメイン URL にリダイレクトします。 ランディング ページは通常、ホーム ページの URL として機能します。 Microsoft Entra PowerShell モジュールを使用して、アプリ ユーザーがアプリ内の特定のページに移動する場合に、カスタム ホーム ページ URL を定義します。

ご自分の会社がカスタム ホーム ページを設定する理由を説明する 1 つのシナリオを次に示します。

- あなたの企業ネットワーク内では、ユーザーが `https://ExpenseApp/login/login.aspx` に移動して、お使いのアプリにサインインしてアクセスします。
- フォルダー構造の最上位に、アプリケーション プロキシがアクセスする必要がある他のアセット (イメージなど) があるため、内部 URL として `https://ExpenseApp` を使用してアプリを発行します。
- 既定の外部 URL は `https://ExpenseApp-contoso.msappproxy.net`であり、外部ユーザーはサインイン ページに誘導されません。
- 代わりに `https://ExpenseApp-contoso.msappproxy.net/login/login.aspx` をホーム ページ URL として設定します。それにより、外部ユーザーに対しては最初にサインインページが表示されます。

注意

公開されたアプリへのアクセス権をユーザーに付与すると、アプリは [マイ アプリ](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510) と [Microsoft 365 アプリ起動ツール](https://www.microsoft.com/microsoft-365/blog/2016/09/27/introducing-the-new-office-365-app-launcher/)に表示されます。

### 開始する前に

ホーム ページの URL を設定する前に、次の要件に留意してください。

- 指定するパスは、ルート ドメイン URL のサブドメインのパスでなければなりません。

    たとえば、ルート ドメイン URL が `https://apps.contoso.com/app1/`されている場合、構成するホーム ページの URL は `https://apps.contoso.com/app1/` で始まる必要があります。
- 発行されたアプリに変更を加えると、ホーム ページの URL の値がリセットされる可能性があります。 今後アプリを更新する場合は、再確認し、必要に応じてホーム ページの URL を更新します。

ホーム ページの URL は、Microsoft Entra 管理センターまたは PowerShell を使用して設定することができます。

### Microsoft Entra 管理センターでホーム ページを変更する

Microsoft Entra 管理センターからご自分のアプリのホーム ページ URL を変更するには、次の手順に従ってください。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. 右上隅で自分のユーザー名を選択します。 アプリケーション プロキシを使うディレクトリにサインインしていることを確認します。 ディレクトリを変更する必要がある場合は、[ **ディレクトリの切り替え** ] を選択し、アプリケーション プロキシを使用するディレクトリを選択します。
3. **Entra ID**&gt;**アプリ登録**に移動します。 登録済みのアプリの一覧が表示されます。
4. 一覧からアプリを選択します。 登録済みのアプリの詳細を示すページが表示されます。
5. [ **管理**] で [ **ブランド]** を選択します。
6. **ホーム ページの URL を**新しいパスで更新します。
7. **[保存] を選択します**。 [Image: [ホーム ページの URL] フィールドが強調表示されている登録済みアプリの [ブランド化とプロパティ] ページのスクリーンショット。]

### PowerShell でホーム ページを変更する

PowerShell を使用してアプリのホーム ページを構成するには、次を実行する必要があります。

1. Microsoft Entra PowerShell モジュールをインストールします。
2. アプリの ObjectId 値を見つけます。
3. PowerShell のコマンドを使用してアプリのホーム ページの URL を更新します。

#### Microsoft Entra PowerShell モジュールをインストールする

PowerShell を使用してカスタム ホーム ページ URL を定義する前に、Microsoft Entra PowerShell モジュールをインストールします。 Graph API エンドポイントを使用する [PowerShell ギャラリー](https://www.powershellgallery.com/packages/Microsoft.Graph.Entra)からパッケージをダウンロードできます。

このパッケージをインストールするには、次の手順を実行します。

1. 標準の PowerShell ウィンドウを開き、次のコマンドを実行します。

    ```powershell
    Install-Module -Name Microsoft.Graph
    ```

    管理者権限なしでコマンドを実行している場合は、`-Scope CurrentUser` オプションを使用してください。
2. インストール中に **Y** を選択して、NuGet.org から 2 つのパッケージをインストールします。両方のパッケージが必要です。

#### アプリの ObjectId を見つける

表示名またはホーム ページでアプリを検索して、アプリの ObjectId を取得します。

1. 同じ PowerShell ウィンドウで、Microsoft Entra モジュールをインポートします。

    ```powershell
    Import-Module -Name Microsoft.Graph
    ```
2. テナント管理者として Microsoft Entra モジュールにサインインします。

    ```powershell
    Connect-Entra -Scopes 'Application.Read.All'
    ```
3. アプリを見つけてください。 この例では、PowerShell を使用して、`SharePoint` の表示名でアプリを検索して ObjectId を検出しています。

    ```powershell
    Get-EntraApplication | Where-Object { $_.DisplayName -eq "SharePoint" } | Format-List DisplayName, IdentifierUris, ObjectId
    ```

    次のような結果が表示されます。 次のセクションで使用するために ObjectId GUID をコピーします。

    ```console
    DisplayName       : SharePoint
    IdentifierUris    : https://sharepoint-iddemo.msappproxy.net/
    ObjectId          : aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb
    ```

    または、すべてのアプリの一覧をプルし、特定の表示名またはホーム ページでアプリの一覧を検索し、アプリを見つけたらアプリの ObjectId をコピーすることもできます。

    ```powershell
    Get-EntraApplication | Format-List DisplayName, IdentifierUris, ObjectId
    ```

#### ホーム ページの URL を更新する

ホーム ページの URL を作成し、その値でアプリを更新します。 同じ PowerShell ウィンドウを引き続き使用します。または、新しい PowerShell ウィンドウを使用する場合は、`Connect-Entra` を使用してもう一度 Microsoft Entra モジュールにサインインしてください。 その後、次の手順に従います。

1. コピーした `ObjectId` 値を保持する変数を作成します。

    ```powershell
    $objguid = "<object id>"
    ```
2. 次のコマンドを実行して、正しいアプリを持っていることを確認します。 出力は、前のセクションで確認した出力と同じである必要があります (アプリの ObjectId を検索します)。

    ```powershell
    Get-EntraApplication -ObjectId $objguid | Format-List DisplayName, IdentifierUris, ObjectId
    ```
3. ホーム ページの URL を目的の値に設定します。 この値は、発行済みアプリのサブドメイン パスである必要があります。 たとえば、ホーム ページの URL を `https://sharepoint-iddemo.msappproxy.net/` から `https://sharepoint-iddemo.msappproxy.net/hybrid/` に変更すると、アプリ ユーザーはカスタム ホーム ページに直接移動します。

    次のコマンドを実行します。

    ```powershell
    Set-EntraApplication -ObjectId $objguid -IdentifierUris 'https://sharepoint-iddemo.msappproxy.net/hybrid/'
    ```
4. 変更が成功したことを確認するには、手順 2 からの次のコマンドをもう一度実行します。

    ```powershell
    Get-EntraApplication -ObjectId $objguid | Format-List DisplayName, IdentifierUris, ObjectId
    ```

    この例では、出力は次のように表示されます。

    ```console
    DisplayName       : SharePoint
    IdentifierUris    : https://sharepoint-iddemo.msappproxy.net/hybrid/
    ObjectId          : bbbbbbbb-1111-2222-3333-cccccccccccc
    ```
5. アプリを再起動して、想定どおりにホーム ページが最初の画面として表示されることを確認します。

注意

アプリを変更すると、ホーム ページの URL がリセットされる可能性があります。 ホーム ページの URL がリセットされた場合は、このセクションの手順を繰り返して設定し直します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-configure-for-claims-aware-applications"} -->
## クレーム対応アプリ - Microsoft Entra アプリケーション プロキシ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-for-claims-aware-applications
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: ユーザーによるセキュリティで保護されたリモート アクセスのために Active Directory フェデレーション サービス要求を受け入れるオンプレミスの ASP.NET アプリケーションを発行する方法。

### 概要

[要求に対応するアプリ](https://learn.microsoft.com/ja-jp/previous-versions/windows/desktop/legacy/bb736227%28v=vs.85%29)は、セキュリティ トークン サービス (STS) へのリダイレクトを実行します。 STS は、トークンと引き換えにユーザーの資格情報を要求し、アプリケーションにユーザーをリダイレクトします。 アプリケーション プロキシがこれらのリダイレクトを操作できるようにするには、いくつかの方法があります。 この記事の手順に従って、要求に対応するアプリのデプロイを構成します。

### [前提条件]

クレーム対応アプリがリダイレクトする STS は、オンプレミス ネットワークの外部で使用できる必要があります。 プロキシを介して、または外部接続を許可して公開します。

### アプリケーションを公開する

1. アプリケーション プロキシを使用したアプリケーションの発行に関するページの手順に従って [、アプリケーションを発行します](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)。
2. ポータルのアプリケーション ページに移動し、 **[シングル サインオン]** を選択します。
3. **[事前認証方法]** として **[Microsoft Entra ID]** を選択した場合は、**[内部認証方法]** として **[Microsoft Entra シングル サインオンが無効]** を選択してください。 **[事前認証方法]** として **[パススルー]** を選択した場合は、何も変更する必要はありません。

### Active Directory フェデレーション サービス (AD FS)の構成

要求に対応するアプリの Active Directory フェデレーション サービスは、2 つの方法のいずれかで構成できます。 1 つはカスタム ドメインを使用する方法で、 もう 1 つは WS-Federation を使用する方法です。

#### 方法 1: カスタム ドメイン

アプリケーションのすべての内部 URL が完全修飾ドメイン名 (FQDN) になっている場合は、アプリケーションの[カスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)を構成することができます。 このカスタム ドメインを使用して、内部 URL と同じ外部 URL を作成できます。 外部 URL が内部 URL と一致すると、ユーザーがオンプレミスかリモートかに関わらず、STS リダイレクト機能が作動します。

#### オプション 2: WS-Federation

1. Active Directory フェデレーション サービスの管理コンソールを開きます。
2. **信頼できるパーティ**に移動し、アプリケーション プロキシを用いて公開するアプリを右クリックして、[**プロパティ**] を選択します。

    [Image: 信頼パーティ トラストでアプリ名を右クリックします。]
3. **[エンドポイント]** タブの **[エンドポイントの種類]** で、 **[WS-Federation]** を選択します。
4. [ **信頼できる URL**] で、アプリケーション プロキシに入力した URL を **[外部 URL** ] に入力し、[ **OK] を選択します**。

    [Image: エンドポイントを追加する - 信頼できる URL の値を設定します。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-configure-hard-coded-link-translation"} -->
## Microsoft Entra アプリケーション プロキシのリンクと URL を変換する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-hard-coded-link-translation
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Microsoft Entra アプリケーション プロキシを介して発行されたオンプレミス アプリケーションのハードコーディングされた内部 URL を変換します。 Edge 統合、ブラウザー拡張機能、リンク変換の設定について説明します。

### 概要

Microsoft Entra アプリケーション プロキシを使うと、リモートのユーザーまたは自分のデバイスを使っているユーザーがオンプレミスのアプリを使用できるようになります。 ただし、一部のアプリは、HTML に埋め込まれたローカル リンクと共に開発されました。 現時点では、アプリをリモートで使用しているときには、それらのリンクは正常に機能しません。 互いを指す複数のオンプレミス アプリケーションがある場合、ユーザーは、オフィスにいないときにリンクが動作し続ける必要があります。

お客様の企業ネットワークの内でも外でも、リンクを同じように機能させるのに最適な方法は、アプリの外部 URL をその内部 URL と一致するように構成することです。 外部 URL に、規定値のアプリケーション プロキシ ドメインではなく、お客様の企業ドメイン名が含まれるように構成するには、[カスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)を使用します。

テナントでカスタム ドメインを使用できない場合、この機能を提供するオプションが他にもいくつかあります。 ほかのオプションはすべて、カスタム ドメインとの互換性も相互の互換性もあるため、カスタム ドメインやその他のソリューションを構成できます。

注

リンク変換は、JavaScript を介して生成されたハードコーディングされた内部 URL ではサポートされていません。

**オプション 1: Microsoft Edge を使用する** – このソリューションは、Microsoft Edge ブラウザーを使用してアプリケーションにアクセスすることをユーザーに推奨または要求することを計画している場合にのみ適用されます。 発行されたすべての URL が処理されます。

**オプション 2: MyApps 拡張機能を使用する** – このソリューションは、クライアント側のブラウザー拡張機能をインストールすることをユーザーに要求しますが、すべての公開されている URL を処理し、ほとんどの一般的なブラウザーと連携します。

**オプション 3: リンク変換設定を使用する** – このオプションは、ユーザーからは見えない管理者側の設定です。 ただし、HTML および CSS 内のみの URL が処理されます。

これら 3 つの機能は、ユーザーの居場所にかかわらず、リンクを機能させ続けます。 内部エンドポイントまたはポートを直接指すアプリがある場合は、公開されている外部アプリケーション プロキシ URL にこれらの内部 URL をマップできます。

注

最後のオプションはテナント専用です。いかなる理由であっても、カスタム ドメインのアプリに同じ内部および外部 URL を含めることはできません。 この機能を有効にする前に、[Microsoft Entra アプリケーション プロキシでカスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)が機能するかどうかをご確認ください。

または、リンク変換を構成したいアプリケーションが SharePoint の場合は、「[SharePoint 2013 の代替アクセス マッピングを構成する](https://learn.microsoft.com/ja-jp/SharePoint/administration/configure-alternate-access-mappings)」で、リンクをマッピングするための別の方法をご覧ください。

#### オプション 1: Microsoft Edge 統合

Microsoft Edge を使用して、アプリケーションとコンテンツの保護を強化することができます。 このソリューションを使用するには、Microsoft Edge を使用してアプリケーションにアクセスすることをユーザーに要求または推奨する必要があります。 アプリケーション プロキシで公開されているすべての内部 URL は Microsoft Edge によって認識され、対応する外部 URL にリダイレクトされます。 リダイレクトにより、ハードコーディングされた内部 URL が確実に機能します。 ユーザーがブラウザーにアクセスし、内部 URL を直接入力すると、ユーザーがリモートであっても機能します。

このオプションの構成方法など、詳細については、 [Microsoft Edge for iOS および Android と Microsoft Intune のドキュメントを使用した Web アクセスの管理](https://learn.microsoft.com/ja-jp/mem/intune/apps/manage-microsoft-edge) に関するページを参照してください。

#### オプション 2:MyApps ブラウザー拡張機能

MyApps ブラウザー拡張機能を使用すると、アプリケーション プロキシで公開されているすべての内部 URL は拡張機能によって認識され、対応する外部 URL にリダイレクトされます。 リダイレクトにより、ハードコーディングされたすべての内部 URL が確実に機能します。 ユーザーがブラウザーのアドレス バーにアクセスし、内部 URL を直接入力すると、ユーザーがリモートであっても機能します。

この機能を使用するには、ユーザーが拡張機能をダウンロードしてログインしている必要があります。 管理者またはユーザーに必要なその他の構成はありません。

このオプションの構成方法を含めた詳細については、[MyApps Browser 拡張機能](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510#download-and-install-the-my-apps-secure-sign-in-extension)のドキュメントをご覧ください。

注

MyApps ブラウザー拡張機能では、ワイルドカード URL のリンク変換はサポートされていません。

#### オプション 3:リンク変換設定

アプリケーション プロキシ サービスは、公開されている内部リンクを HTML と CSS を介して検索し、リンク変換が有効になっている場合はそれらを変換します。 リンク変換により、中断されない環境が提供されます。 より高パフォーマンスな環境を提供するため、MyApps Browser 拡張機能の使用はリンク変換設定よりも優先されます。

注

オプション 2 または 3 を使用している場合は、これらの設定のうち 1 つだけを一度に有効にする必要があります。

### リンク変換の仕組み

認証後に、プロキシ サーバーがユーザーにアプリケーション データを渡すと、アプリケーション プロキシによってアプリケーションでハードコーディングされたリンクがスキャンされて、それぞれが公開されている外部 URL に置き換えられます。

アプリケーション プロキシでは、アプリケーションが UTF-8 でエンコードされていると想定されています。 そうなっていない場合は、HTTP 応答ヘッダーでエンコードの種類を `Content-Type:text/html;charset=utf-8` のように指定してください。

#### 影響を受けるリンク

リンク変換機能は、アプリの本文のコード タグ内にあるリンクだけを検索します。 アプリケーション プロキシには、ヘッダー内の Cookie や URL を変換するための別の機能があります。

オンプレミスのアプリケーションの内部リンクには 2 つの一般的な種類があります。

- **ローカルファイル構造内の共有リソースを指す相対内部リンク**。 これらのリンクは、アプリケーション プロキシ経由で公開されるアプリで自動的に動作し、リンク変換の有無にかかわらず機能し続けます。
- ハードコードされた内部リンクによる、 のような他のオンプレミスアプリや、 のような公開ファイルへのリンク。 リンク変換機能は、ハードコードされた内部リンクで機能し、リモート ユーザーが経由する必要がある外部 URL をポイントするように変更します。

アプリケーション プロキシがリンク変換をサポートする HTML コード タグの属性の完全な一覧には、以下のものが含まれます。

- `a (href)`
- `audio (src)`
- `base (href)`
- `button (formaction)`
- `div (data-background, style, data-src)`
- `embed (src)`
- `form (action)`
- `frame (src)`
- `head (profile)`
- `html (manifest)`
- `iframe (longdesc, src)`
- `img (longdesc, src)`
- `input (formaction, src, value)`
- `link (href)`
- `menuitem (icon)`
- `meta (content)`
- `object (archive, data, codebase)`
- `script (src)`
- `source (src)`
- `track (src)`
- `video (src, poster)`

さらに、CSS 内では URL 属性も変換されます。

#### アプリが相互にリンクする方法

リンク変換は、アプリケーションごとに有効化されるため、アプリ レベルでユーザー エクスペリエンスを制御できます。 変換するアプリ "*への*" リンクではなく、そのアプリ "*から*" のリンクが必要な場合に、アプリのリンク変換をオンにします。

たとえば、すべてが相互にリンクする、アプリケーション プロキシ経由で公開された 3 つのアプリケーション (福利厚生、経費、出張) があるとします。 アプリケーション プロキシ経由で公開されていないフィードバックという 4 番目のアプリがあります。

福利厚生アプリのリンク変換を有効にすると、経費と出張へのリンクがそれらのアプリの外部 URL にリダイレクトされます。 ただし、外部 URL がないため、フィードバックへのリンクはリダイレクトされません。 経費と出張のアプリに対してはリンク変換が有効になっていないため、この 2 つのアプリから福利厚生へのリンクは機能しません。

[Image: リンク変換が有効になっている場合の特典から他のアプリへのリンク。]

#### 翻訳されていないリンクはどれですか？

パフォーマンスとセキュリティを向上させるために、一部のリンクは変換されません。

- コード タグ内にないリンク。
- HTML や CSS の中にないリンク。
- URL エンコード形式のリンク。
- 他のプログラムから開かれた内部リンク。 メールまたはインスタント メッセージで送信されたか、他のドキュメントに含まれているリンクは変換されません。 ユーザーは、外部 URL に移動するために知っておく必要があります。

この 2 つのシナリオのいずれかをサポートする必要がある場合は、リンク変換ではなく同じ内部および外部 URL を使用します。

### リンク変換の有効化

リンク変換の使用開始は、ボタンをクリックするのと同じくらい簡単です。

1. [アプリケーション管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**すべてのアプリケーション**に移動します。
3. 管理するアプリを選択します。
4. **[Translate URLs in application body](アプリケーション本文の URL を変換する)** を **[はい]** にします。

    [Image: アプリケーション本文の [URL の変換] が [はい] に設定されている [アプリケーション プロキシ設定] ページ。]
5. **[保存]** を選択して変更を保存します。

これで、ユーザーがこのアプリケーションにアクセスすると、テナントのアプリケーション プロキシ経由で公開されている内部 URL がプロキシによってスキャンされます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-configure-native-client-application"} -->
## ネイティブ クライアント アプリを公開する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-native-client-application
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Microsoft Entra アプリケーション プロキシを使用してネイティブ クライアント アプリケーションを発行し、オンプレミスの API とリソースへの安全なリモート アクセスを提供します。

### 概要

Microsoft Entra アプリケーション プロキシは、Web アプリを公開するために使用されます。 Microsoft Authentication Library (MSAL) で構成されたネイティブ クライアント アプリケーションの公開にも、このプロキシを使用できます。 クライアント アプリケーションはデバイスにインストールされる点が Web アプリとは異なります。Web アプリはブラウザーからアクセスされます。

ネイティブ クライアント アプリケーションをサポートするために、アプリケーション プロキシは、Microsoft Entra ID によって発行され、ヘッダーで送信されたトークンを受け入れます。 アプリケーション プロキシ サービスでは、ユーザーに対する認証が行われます。 このソリューションでは、認証のためにアプリケーションのトークンは使われません。

[Image: エンド ユーザー、Microsoft Entra ID、および発行されたアプリケーション間の関係を示す図。]

ネイティブ アプリケーションを発行するには、認証の処理が行われ、多数のクライアント環境がサポートされている、Microsoft Authentication Library を使います。 アプリケーション プロキシは、 [サインインしているユーザー シナリオに代わって Web API を呼び出すデスクトップ アプリに](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-flows-app-scenarios#desktop-app-that-calls-a-web-api-on-behalf-of-a-signed-in-user) 適合します。

この記事では、アプリケーション プロキシと Microsoft Authentication Library (MSAL) を使用してネイティブ アプリケーションを公開するための 4 つの手順を説明します。

### 手順 1:プロキシ アプリケーションを発行する

他のアプリケーションと同様にプロキシ アプリケーションを発行し、アプリケーションにアクセスするユーザーを割り当てます。 詳細については、「 [アプリケーション プロキシを使用してアプリケーションを発行する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)」を参照してください。

### 手順 2:ネイティブ アプリケーションを登録する

ここでは、Microsoft Entra ID でアプリケーションを登録する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. 右上隅で自分のユーザー名を選択します。 アプリケーション プロキシを使うディレクトリにサインインしていることを確認します。 ディレクトリを変更する必要がある場合は、[ **ディレクトリの切り替え** ] を選択し、アプリケーション プロキシを使用するディレクトリを選択します。
3. **[Entra ID]**&gt;**[アプリの登録]** に移動します。 すべてのアプリ登録の一覧が表示されます。
4. [ **新しい登録**] を選択します。 [ **アプリケーションの登録]** ページが表示されます。

    [Image: Microsoft Entra 管理センターのアプリ登録作成ページを示すスクリーンショット。]
5. [ **名前** ] 見出しで、アプリケーションのユーザー向けの表示名を指定します。
6. [ **サポートされているアカウントの種類** ] 見出しで、次のガイドラインを使用してアクセス レベルを選択します。

    - 組織の内部にあるアカウントのみを対象にするには、 **この組織ディレクトリ内の [アカウントのみ**] を選択します。
    - 企業または教育機関のお客様のみを対象にするには、 **任意の組織のディレクトリで [アカウント]** を選択します。
    - 最も幅広い Microsoft ID のセットを対象にするには、 **任意の組織のディレクトリ内のアカウントと個人用の Microsoft アカウントを選択します**。
7. [ **リダイレクト URI**] で[ **パブリック クライアント (モバイルとデスクトップ)]**を選択し、アプリケーションのリダイレクト URI `https://login.microsoftonline.com/common/oauth2/nativeclient` を入力します。
8. **Microsoft プラットフォーム ポリシー**を選択して読み取り、[**登録**] を選択します。 新しいアプリケーション登録の概要ページが作成されて、表示されます。

新しいアプリケーション登録の作成の詳細については、「 [アプリケーションと Microsoft Entra ID の統合」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)参照してください。

### 手順 3:プロキシ アプリケーションへのアクセスを許可する

ネイティブ アプリケーションは登録されています。 プロキシ アプリケーションへのアクセス権を付与してください。

1. 新しいアプリケーション登録ページのサイドバーで、 **API のアクセス許可**を選択します。 新しいアプリケーション登録の **API アクセス許可** ページが表示されます。
2. [ **アクセス許可の追加] を選択します**。 [ **API のアクセス許可の要求]** ページが表示されます。
3. [ **API の選択** ] 設定で、 **組織が使用する API を**選択します。 API が公開されているディレクトリ内のアプリケーションを含む一覧が表示されます。
4. 検索ボックスに入力するか、スクロールして 「手順 1: プロキシ アプリケーションを発行する」で公開したプロキシ アプリケーションを見つけ、プロキシ アプリケーションを選択します。
5. [ **アプリケーションに必要なアクセス許可の種類]** 見出しで、アクセス許可の種類を選択します。 ネイティブ アプリケーションがサインインユーザーとしてプロキシ アプリケーション API にアクセスする必要がある場合は、[ **委任されたアクセス許可**] を選択します。
6. [ **アクセス許可の選択** ] 見出しで、目的のアクセス許可を選択し、[ **アクセス許可の追加]** を選択します。 ネイティブ アプリケーションの **API アクセス許可** ページに、追加したプロキシ アプリケーションとアクセス許可 API が表示されるようになりました。

### 手順 4:Microsoft Authentication Library をコードに追加する (.NET C# サンプル)

Microsoft Authentication Library (MSAL) の認証コンテキストのネイティブ アプリケーション コードを編集して、次のテキストを含めます。

```
// Acquire access token from Microsoft Entra ID for proxy application
IPublicClientApplication clientApp = PublicClientApplicationBuilder
.Create(<App ID of the Native app>)
.WithDefaultRedirectUri() // will automatically use the default Uri for native app
.WithAuthority("https://login.microsoftonline.com/{<Tenant ID>}")
.Build();

AuthenticationResult authResult = null;
var accounts = await clientApp.GetAccountsAsync();
IAccount account = accounts.FirstOrDefault();

IEnumerable<string> scopes = new string[] {"<Scope>"};

try
 {
    authResult = await clientApp.AcquireTokenSilent(scopes, account).ExecuteAsync();
 }
    catch (MsalUiRequiredException ex)
 {
     authResult = await clientApp.AcquireTokenInteractive(scopes).ExecuteAsync();                
 }

if (authResult != null)
 {
  //Use the Access Token to access the Proxy Application

  HttpClient httpClient = new HttpClient();
  httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", authResult.AccessToken);
  HttpResponseMessage response = await httpClient.GetAsync("<Proxy App Url>");
 }
```

サンプル コードで必要な情報は、次のように Microsoft Entra 管理センターで見つかります。

| 必要な情報 | Microsoft Entra 管理センターで見つける方法 |
| --- | --- |
| &lt;テナント ID&gt; | **Entra ID**&gt;**概要**&gt;**プロパティ** |
| &lt;ネイティブ アプリのアプリ ID&gt; | **アプリケーションの登録**&gt;*ネイティブ アプリケーション*&gt;**概要**&gt;**アプリケーション ID** |
| &lt;スコープ&gt; | **アプリケーションの登録**&gt;*ネイティブ アプリケーション*&gt;**API のアクセス許可**&gt; 右側にキャプションuser\_impersonationが表示されたパネル &gt; アクセス許可 API ( **user\_impersonation** ) を選択します。 &gt; スコープは、編集ボックスの URL です。 |
| &lt;プロキシ アプリの URL&gt; | API への外部 URL とパス |

これらのパラメーターで MSAL コードを編集した後は、会社のネットワーク外部のユーザーであっても、ネイティブ クライアント アプリケーションに対する認証を行えるようになります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-configure-single-sign-on-password-vaulting"} -->
## アプリケーション プロキシを使用したシングル サインオンでのパスワードの保管 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-single-sign-on-password-vaulting
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Microsoft Entra 管理センターで Microsoft Entra アプリケーション プロキシを使用してオンプレミス アプリケーションへのシングルサインオンを有効にします。

### 概要

Microsoft Entra アプリケーション プロキシを使用してオンプレミス アプリケーションを発行して、遠隔地の従業員がオンプレミス アプリケーションに安全にアクセスできるようにすることで生産性を上げることができます。 Microsoft Entra 管理センターで、これらのアプリにシングル サインオン (SSO) を設定することもできます。 ユーザーは、Microsoft Entra ID で認証されれば、もう一度サインインしなくても、エンタープライズ アプリケーションにアクセスできるようになります。

アプリケーション プロキシは、さまざまな[シングル サインオン モード](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/plan-sso-deployment#choosing-a-single-sign-on-method)をサポートします。 パスワード ベースのサインオンは、ユーザー名とパスワードの組み合わせを使って認証を行うアプリケーションを対象としています。 Microsoft Entra ID はサインイン情報を格納し、ユーザーがアプリケーションにリモートでアクセスしたときにサインイン情報をアプリケーションに自動的に提供します。

### 前提条件

この記事では、アプリを公開し、アプリケーション プロキシを使ってテストする必要があります。 詳細については、「 [Microsoft Entra アプリケーション プロキシを使用してアプリケーションを発行する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)」を参照してください。

### アプリケーションのパスワード保管の設定

1. [アプリケーション管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**すべてのアプリケーション**に移動します。
3. 一覧から、SSO を設定するアプリを選択します。
4. **[アプリケーション プロキシ]** を選びます。
5. **[Pre Authentication type]\(事前認証の種類\)** を **[パススルー]** に変更し、**[保存]** を選択します。 後で **Microsoft Entra ID** タイプに戻すことができます。
6. **[シングル サインオン]** を選択します。
7. SSO モードとして、**[パスワード ベースのサインオン]** を選択します。
8. [サインオン URL] に、ユーザーが企業ネットワークの外部からアプリにサインインするためのユーザー名とパスワードを入力するページの URL を入力します。 このページは、アプリケーション プロキシを使ってアプリを発行したときに作成した外部 URL にする必要があります。

    [Image: URL エントリを含むパスワードベースのサインオン構成を示すスクリーンショット。]
9. **[保存]** を選択します。
10. **[アプリケーション プロキシ]** を選びます。
11. **[事前認証の種類]** を **[Microsoft Entra ID]** に変更し、**[保存]** を選択します。
12. **[Users and Groups]** を選択します。
13. アプリケーションにユーザーを割り当てます。
14. [ **ユーザーの追加] を選択します**。
15. ユーザーの資格情報を事前に定義する場合は、ユーザー名の前にあるチェック ボックスをオンにし、**[資格情報の更新]** を選びます。
16. **Entra ID**&gt;**アプリケーションの登録**&gt;**すべてのアプリケーション**に移動します。
17. 一覧から、[Password SSO]\(パスワード SSO\) を使用して構成したアプリを選択します。
18. **[ブランド]** を選択します。
19. [パスワード SSO] ページで **[サインオン URL]** を使って **[ホームページ URL]** を更新し、**[保存]** を選びます。

### アプリをテストする

[マイ アプリ] ポータルに移動します。 自分の資格情報 (または設定したアクセス権を持つテスト アカウントの資格情報) でサインインします。 サインインしたら、アプリのアイコンを選択します。 [マイ アプリ] ポータルを開くと、マイ アプリのセキュリティで保護されたサインイン ブラウザー拡張機能のインストールがトリガーされる場合があります。 資格情報が事前に定義されている場合、アプリへの認証は自動的に行われます。 それ以外の場合は、初めてユーザー名またはパスワードを指定する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-configure-single-sign-on-with-headers"} -->
## Microsoft Entra アプリケーション プロキシを使用したオンプレミス アプリのヘッダー ベースのシングル サインオン (SSO) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-single-sign-on-with-headers
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Microsoft Entra アプリケーション プロキシを介して発行されたオンプレミス アプリケーションのヘッダー ベースのシングル サインオンを構成します。 ユーザー ID 属性を HTTP ヘッダーとして渡します。

### 概要

Microsoft Entra アプリケーション プロキシは、認証にヘッダーを使用するアプリケーションへのシングル サインオン (SSO) アクセスをネイティブにサポートします。 アプリケーションに必要なヘッダー値は、Microsoft Entra ID で構成します。 ヘッダー値は、アプリケーション プロキシ経由でアプリケーションに送信されます。 アプリケーション プロキシでヘッダー ベースの認証にネイティブ サポートを使用する利点は次のとおりです。

- **オンプレミス アプリへのリモート アクセスを簡素化する** - アプリケーション プロキシにより、既存のリモート アクセス アーキテクチャが簡略化されます。 これらのアプリへの仮想プライベート ネットワーク (VPN) アクセスを置き換えます。 認証用のオンプレミス ID ソリューションへの依存関係を削除します。 ユーザーのエクスペリエンスを合理化し、企業アプリケーションを使用しても何の変化も気付かない。 ユーザーは、任意のデバイスのどこからでも作業できます。
- **追加のソフトウェアやアプリへの変更はありません** - 既存のプライベート ネットワーク コネクタを使用します。 追加のソフトウェアは必要ありません。
- **使用できる属性と変換の範囲が広い** - 使用できるすべてのヘッダー値は、Microsoft Entra ID によって発行される標準の要求に基づいています。 [Security Assertion Markup Language (SAML) または OpenID Connect (OIDC) アプリケーションの要求を構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization#attributes)するために使用できるすべての属性と変換は、ヘッダー値としても使用できます。

### [前提条件]

アプリケーション プロキシを有効にし、アプリケーションに直接ネットワーク アクセスできるコネクタをインストールします。 詳細については、 [アプリケーション プロキシを使用したリモート アクセス用のオンプレミス アプリケーションの追加に関するページ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)を参照してください。

### サポートされている機能

次の表に、ヘッダーベースの認証アプリケーションに必要な一般的な機能を示します。

| 要件 | 説明 |
| --- | --- |
| フェデレーテッドSSO | 事前認証モードでは、すべてのアプリケーションが Microsoft Entra 認証で保護され、ユーザーはシングル サインオンを使用します。 |
| [リモート アクセス] | アプリケーション プロキシは、アプリへのリモート アクセスを提供します。 ユーザーは、外部の Uniform Resource Locator (URL) を使用して、任意の Web ブラウザーでインターネットからアプリケーションにアクセスします。 アプリケーション プロキシは、一般的な企業アクセスを目的としたものではありません。 一般的な企業アクセスについては、「[Microsoft Entra Private Access](https://learn.microsoft.com/ja-jp/entra/global-secure-access)」を参照してください。 |
| ヘッダーベースの統合 | アプリケーション プロキシは、Microsoft Entra ID との SSO 統合を処理し、ID またはその他のアプリケーション データを HTTP ヘッダーとしてアプリケーションに渡します。 |
| アプリケーション認証 | 一般的なポリシーは、アクセスするアプリケーション、ユーザーのグループ メンバーシップ、およびその他のポリシーに基づいて指定されます。 Microsoft Entra ID では、ポリシーは[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)を使用して実装されます。 アプリケーション認可ポリシーは、最初の認証要求にのみ適用されます。 |
| ステップアップ認証 | ポリシーは、たとえば機密性の高いリソースにアクセスするために、認証を強制的に追加するように定義されています。 |
| きめ細かな認可 | URL レベルでのアクセス制御を提供します。 追加されたポリシーは、アクセス対象の URL に基づいて適用できます。 アプリ用に構成された内部 URL は、ポリシーが適用されるアプリのスコープを定義します。 最もきめ細かなパスに対して構成されているポリシーが適用されます。 |

注

この記事では、アプリケーション プロキシを使用したヘッダー ベースの認証アプリケーションと Microsoft Entra ID の間の接続について説明します。これは推奨されるパターンです。 別の方法として、PingAccess と Microsoft Entra ID を使用してヘッダーベースの認証を有効にする統合パターンがあります。 詳細については、「 [アプリケーション プロキシと PingAccess を使用したシングル サインオンのヘッダー ベースの認証](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-ping-access-publishing-guide)」を参照してください。

### 動作方法

[Image: ヘッダーベースのシングル サインオンがアプリケーション プロキシでどのように機能するか。]

1. 管理者は、Microsoft Entra 管理センターでアプリケーションに必要な属性マッピングをカスタマイズします。
2. アプリケーション プロキシは、ユーザーが Microsoft Entra ID を使用して認証されることを保証します。
3. アプリケーション プロキシ クラウド サービスは、必要な属性を認識します。 そのためサービスでは、認証時に受信した ID トークンから、対応する要求がフェッチされます。 その後、サービスは、コネクタへの要求の一部として、値を必要な HTTP ヘッダーに変換します。
4. その後、要求がコネクタに渡され、バックエンド アプリケーションに渡されます。
5. アプリケーションでは、ヘッダーを受信し、必要に応じてこれらのヘッダーを使用できます。

### アプリケーション プロキシを使用してアプリケーションを発行する

1. アプリケーション プロキシを使用したアプリケーションの発行に関するページの手順に従って [、アプリケーションを発行します](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)。

    - 内部 URL 値によって、アプリケーションのスコープが決まります。 アプリケーションのルート パスで内部 URL 値を構成すると、ルートの下にあるすべてのサブ パスが同じヘッダーとアプリケーション構成を受け取ります。
    - 新しいアプリケーションを作成し、構成したアプリケーションよりもきめ細かなパスに対して、異なるヘッダー構成またはユーザー割り当てを設定します。 新しいアプリケーションでは、必要な特定のパスを含む内部 URL を構成してから、この URL に必要な特定のヘッダーを構成します。 アプリケーション プロキシは、構成設定をアプリケーションの最も細かいパス セットに常に一致させます。
2. **事前認証**方法として **[Microsoft Entra ID]** を選択します。
3. **[ユーザーとグループ]** に移動し、適切なユーザーとグループを割り当てることで、テスト ユーザーを割り当てます。
4. ブラウザーを開き、アプリケーション プロキシ設定から **外部 URL** に移動します。
5. アプリケーションに接続できることを確認します。 接続できる場合でも、ヘッダーが構成されていないため、まだアプリにはアクセスできません。

### シングル サインオンの設定

ヘッダー ベースのアプリケーションのシングル サインオンを開始する前に、プライベート ネットワーク コネクタをインストールします。 コネクタはターゲット アプリケーションにアクセスできる必要があります。 詳細については、「 [チュートリアル: Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)」を参照してください。

1. アプリケーションがエンタープライズ アプリケーションの一覧に表示されたら、それを選択して **[シングル サインオン]** を選択します。
2. シングル サインオン モードを **[ヘッダーベース]** に設定します。
3. **基本構成**では、**Microsoft Entra ID** が既定として選択されています。
4. [ **ヘッダー]** で、編集鉛筆を選択して、アプリケーションに送信するヘッダーを構成します。
5. **[新しいヘッダーの追加]** を選択します。 ヘッダーの名前を指定し、[ **属性** ] または [ **変換]**を選択し、アプリケーションで必要なヘッダーをドロップダウンから選択します。
    - 使用可能な属性の一覧の詳細については、「要求の [カスタマイズ - 属性](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization#attributes)」を参照してください。
    - 使用可能な変換の一覧の詳細については、「要求の [カスタマイズ - 要求変換](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization#claim-transformations)」を参照してください。
    - **グループ ヘッダー**を追加することもできます。 グループの構成の詳細については、「 [アプリケーションのグループ要求を構成する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-group-claims#add-group-claims-to-tokens-for-saml-applications-using-sso-configuration)」を参照してください。
6. **保存** を選択します。

### アプリをテストする

アプリケーションが実行され、使用可能になりました。 アプリをテストするには:

1. 新しいブラウザーまたはプライベート ブラウザー ウィンドウを開いて、以前にキャッシュされたヘッダーをクリアします。
2. 外部 URL に移動します。 この設定は、アプリケーション プロキシ設定の **外部 URL** として一覧表示されます。
3. サインインには、アプリに割り当てたテスト アカウントを使用します。
4. SSO を使用してアプリケーションを読み込んでサインインできることを確認します。

### 考慮事項

- アプリケーション プロキシは、プライベート クラウド上のオンプレミス アプリまたはアプリへのリモート アクセスを提供します。 アプリケーション プロキシは、目的のアプリケーションと同じネットワーク内から送信されるトラフィックには推奨されません。
- **ヘッダーベースの認証アプリケーションへのアクセスは、コネクタまたはその他の許可されたヘッダーベースの認証ソリューションからのトラフィックのみに制限する必要があります**。 アクセス制限は、通常、アプリケーション サーバーのファイアウォールまたは IP 制限を使用して実行されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-debug-apps"} -->
## アプリケーション プロキシの問題をデバッグする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-debug-apps
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: Microsoft Entra アプリケーション プロキシを構成するときに発生する問題のデバッグについて説明します。

### 概要

この記事では、Microsoft Entra アプリケーション プロキシに関する問題のトラブルシューティングを行う方法について説明します。 フローチャートを使用して、オンプレミス Web アプリケーションのリモート アクセスの問題を修正します。

### 開始する前に

まず、コネクタを確認します。 [プライベート ネットワーク コネクタのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/global-secure-access/troubleshoot-connectors)の方法について説明します。

### アプリケーションの問題のフローチャート

このフローチャートは、Microsoft Entra アプリケーション プロキシに関する一般的な問題のデバッグと修正に役立ちます。

フローチャートの後の表には、各ステップの詳細が含まれています。

[Image: Microsoft Entra アプリケーション プロキシの問題のアプリケーションのデバッグに役立つフローチャートの図。]

| ステップ | 目標 | アクション |
| --- | --- | --- |
| 1 | サインインしてユーザー関連のエラーを確認する | ブラウザーを開き、ユーザー名とパスワードを使用してアプリにサインインします。 [この会社のアプリにアクセスできない](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-sign-in-bad-gateway-timeout-error)などのエラーを確認します。 |
| 2 | ユーザーのアクセス許可を確認し、アプリのアクセスをテストする | ユーザー アカウントに、企業ネットワーク内からのアプリのアクセス許可があることを確認します。 次に、「アプリケーションのテスト」の手順に従って、アプリへのサインイン [をテストします](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application#test-the-application)。 サインインの問題が解決しない場合は、[ [サインイン エラーのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs?context=azure/active-directory/manage-apps/context/manage-apps-context)] をオンにします。 |
| 3 | 正しいアプリケーション プロキシ構成を確認する | ブラウザーを開き、アプリを使用します。 エラーがすぐに表示される場合は、アプリケーション プロキシが正しく設定されているかどうかを確認します。 特定のエラー メッセージの詳細については、 [アプリケーション プロキシの問題とエラー メッセージのトラブルシューティングを](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-troubleshoot)参照してください。 |
| 4 | カスタム ドメインのセットアップが正しいことを確認するか、エラーのトラブルシューティングを行う | ページが表示されない場合は、カスタム ドメインが正しく設定されているかどうかを確認します。 [「カスタム ドメインの操作」](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)の情報を確認します。ページが読み込まず、エラー メッセージが表示される場合は、「アプリケーション プロキシの問題とエラー メッセージのトラブルシューティング」の情報を使用して [エラーのトラブルシューティングを行います](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-troubleshoot)。エラー メッセージが表示されるまでに 20 秒以上かかる場合は、接続の問題が発生している可能性があります。 [プライベート ネットワーク コネクタのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/global-secure-access/troubleshoot-connectors)の手順に従います。 |
| 5 | プロキシとコネクタ間の接続の問題をデバッグする | 問題が解決しない場合は、コネクタのデバッグを試してください。 [プライベート ネットワーク コネクタのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/global-secure-access/troubleshoot-connectors)で説明されている手順を完了します。 |
| 6 | すべてのリソースを発行し、発行の問題を解決する | 発行パスに、アプリケーションに必要なすべてのイメージ、スクリプト、スタイル シートが含まれていることを確認します。 詳細については、「[オンプレミス アプリを Microsoft Entra ID に追加する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)」を参照してください。発行の問題のトラブルシューティングには、ブラウザーの開発者ツール (Internet Explorer または Microsoft Edge の F12 ツール) を使用します。 「 [アプリケーション」ページが正しく表示されない](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-troubleshoot)を参照してください。ページ上のリンクの壊れたリンクを修正するためのオプション [を確認しても機能しません](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-page-links-broken-problem)。 |
| 7 | ネットワーク待ち時間を最小限に抑える | ページの読み込みが遅い場合は、「待ち時間を短縮するための考慮事項」でネットワーク待機時間 [を短縮する方法を確認してください](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-network-topology#considerations-for-reducing-latency)。 |
| 8 | その他のトラブルシューティング リソースにアクセスする | 問題が解決しない場合は、 [アプリケーション プロキシのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-troubleshoot)に関するその他の記事を確認してください。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-faq"} -->
## Microsoft Entra アプリケーション プロキシに関してよく寄せられる質問 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-faq
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: Microsoft Entra アプリケーション プロキシを使用して内部のオンプレミス アプリケーションをリモート ユーザーに公開することに関するよく寄せられる質問 (FAQ) に対する回答を紹介します。

このページには、Microsoft Entra アプリケーション プロキシに関してよく寄せられる質問への回答が記載されています。

### 全般

#### Microsoft Entra 管理センター内の \*\*[アプリの登録]\*\* ページからアプリケーション プロキシ アプリを変更できますか?

いいえ。次の構成項目はアプリ プロキシによって使用されており、変更および削除しないでください。

- [パブリック クライアント フローを許可する] を有効または無効にする。
- フェデレーション資格情報。
- API 使用権限。 [アプリの登録] ページで上記の構成項目のいずれかを変更すると、Microsoft Entra アプリケーション プロキシの事前認証が破棄されます。

#### アプリケーション プロキシ アプリのクライアント シークレット (CWAP\_AuthSecret) の有効期限が近づいているか、既に期限切れになっています。 どうしたらいいでしょう。

アプリケーション プロキシ アプリは、CWAP シークレットに依存しなくなりました。 Microsoft Entra ID 事前認証を使用するアプリでは、代わりにフェデレーション ID 資格情報 (FIC) が使用されるようになりました。 FIC のセキュリティが強化され、有効期限が切れず、メンテナンスが大幅に少なくなります。 パススルー事前認証で構成されたアプリケーション、またはアクセス トークンを使用するアプリケーションでは、FIC が以前に作成された場合でも、フェデレーション ID 資格情報は必要ありません。

アプリケーション プロキシ アプリのアプリ登録で既存の CWAP シークレットを無視するか、古いシークレットまたは期限切れのシークレットをクリーンアップしても問題ありません。 フェデレーション ID 資格情報の詳細については、「 [Microsoft Entra ID のフェデレーション ID 資格情報の概要」を](https://learn.microsoft.com/ja-jp/graph/api/resources/federatedidentitycredentials-overview)参照してください。

#### Microsoft Entra 管理センター内の [アプリの登録] ページからアプリケーション プロキシ アプリを削除できますか?

いいえ。アプリケーション プロキシ アプリは、Microsoft Entra 管理センターの**エンタープライズ アプリケーション**領域から削除する必要があります。 Microsoft Entra 管理センターの **[アプリの登録]** 領域からアプリケーション プロキシ アプリを削除すると、問題が発生する可能性があります。

#### Microsoft Entra アプリケーション プロキシを使用するにはどのライセンスが必要ですか。

Microsoft Entra アプリケーション プロキシを使用するには、Microsoft Entra ID P1 または P2 ライセンスが必要です。 ライセンスの詳細については、「[Microsoft Entra の価格](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)」を参照してください

#### ライセンスの有効期限が切れた場合、自分のテナントの Microsoft Entra アプリケーション プロキシはどうなりますか。

ライセンスの有効期限が切れると、アプリケーション プロキシは自動的に無効になります。 アプリケーションの情報は、最大 1 年間保存されます。

#### [アプリケーション プロキシの有効化] ボタンが淡色表示されているのはなぜですか。

少なくとも Microsoft Entra ID P1 または P2 ライセンスと、Microsoft Entra プライベート ネットワーク コネクタがインストールされていることを確認してください。 最初のコネクタを正常にインストールすると、Microsoft Entra アプリケーション プロキシ サービスが自動的に有効になります。

### コネクタの構成

#### アプリケーション プロキシは、Microsoft Entra Private Access と同じコネクタを使用しますか?

はい。Microsoft Entra アプリケーション プロキシと Microsoft Entra Private Access の両方が、Microsoft Entra プライベート ネットワーク コネクタを使用します。 コネクタの詳細については、「 [Microsoft Entra プライベート ネットワーク コネクタ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors)」を参照してください。 コネクタの構成のトラブルシューティングを行うには、「[コネクタのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/global-secure-access/troubleshoot-connectors)」を参照してください。

#### ConnectorTroubleshooter を開始できません

ConnectorTroubleshooterLauncher.exe は非推奨となり、機能しなくなりました。 現時点では、代替は使用できません。

#### Microsoft Entra Private Network Connector/Updater Services のログオン ユーザーを変更できますか?

コネクタまたはアップデーター サービスのログオン ユーザーの変更はサポートされていません。

正しい構成:

Microsoft Entra Private Network Connector: *Network Service* Microsoft Entra Private Network Connector Updater: *NT Authority\System*

### アプリケーションの構成

#### アプリケーション プロキシ アプリをデプロイするときに、クライアント シークレットを管理またはローテーションする必要がありますか?

No. アプリケーション プロキシ アプリがクライアント シークレット (CWAP\_AuthSecret) に依存しなくなりました。 Microsoft Entra ID 事前認証を使用するアプリでは、代わりにフェデレーション ID 資格情報 (FIC) が使用されるようになりました。 FIC はより安全であり、期限切れにはなりません。手動でのローテーションやメンテナンスは必要ありません。 パススルー事前認証で構成されたアプリケーション、またはアクセス トークンを使用するアプリケーションでは、フェデレーション ID 資格情報は必要ありません。 アプリ登録の既存の CWAP シークレットは無視して、古いシークレットまたは期限切れのシークレットを削除しても問題ありません。 詳細については、「 [Microsoft Entra ID のフェデレーション ID 資格情報の概要」を](https://learn.microsoft.com/ja-jp/graph/api/resources/federatedidentitycredentials-overview)参照してください。

#### 外部 URL で "[tenant name].onmicrosoft.com" または "[tenant name].mail.onmicrosoft.com" のドメイン サフィックスを使用できますか?

これらのサフィックスはサフィックス リストに表示されていますが、使用しないでください。 これらのドメイン サフィックスは、Microsoft Entra アプリケーション プロキシで使用するためのものではありません。 これらのドメイン サフィックスを使用すると、作成した Microsoft Entra アプリケーション プロキシ アプリケーションは機能しません。 標準ドメイン サフィックス `msappproxy.net` または[カスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)のいずれかを使用できます。

#### アプリケーション プロキシはソブリン クラウドとリージョン クラウドをサポートしていますか?

Microsoft Entra ID のアプリケーション プロキシ サービスを使うと、ユーザーは Microsoft Entra アカウントでサインインして、オンプレミスのアプリケーションにアクセスできます。 異なるリージョンにコネクタをインストールしていた場合は、各コネクタ グループで使用する、最も近いアプリケーション プロキシ クラウド サービスのリージョンを選択することで、トラフィックを最適化できます。「[Microsoft Entra アプリケーション プロキシを使用してトラフィック フローを最適化する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)」を参照してください。

#### 証明書が無効か、パスワードが間違っている可能性があるというエラーが返されます。

SSL 証明書のアップロード後に、ポータルに「証明書が無効であるか、パスワードが間違っている可能性がある」というメッセージが表示されます。

このエラーの問題解決に使用できるヒントを、次にいくつか示します。

- 証明書に問題がないことを確認します。 それをお使いのローカル コンピューターにインストールします。 問題が発生しなかった場合、その証明書には問題はありません。
- パスワードに特殊文字が含まれていないことを確認します。 パスワードには 0 から 9、A から Z、a から z の文字のみが含まれている必要があります。
- 証明書が Microsoft ソフトウェア キー格納プロバイダーで作成されている場合は、RSA アルゴリズムが使用されている必要があります。

#### 既定値と「長い」バックエンド タイムアウトの長さはどれくらいですか。 タイムアウトは延長できますか。

既定の長さは 85 秒です。 「長い」設定は 180 秒です。 タイムアウト制限を延長することはできません。

#### サービス プリンシパルで、PowerShell または Microsoft Graph API を使用してアプリケーション プロキシを管理できますか。

いいえ。現在これはサポートされていません。

#### Microsoft Entra アプリケーション プロキシを使用している、または使用したいと考えています。 記事「Microsoft 365 で onmicrosoft.com フォールバック ドメインを追加して置き換える」で提示されているように、Microsoft 365 のテナントの "onmicrosoft.com" フォールバック ドメインを置き換えることはできますか?

いいえ。元のフォールバック ドメインを使用する必要があります。

質問で言及されている記事: [Microsoft 365 で onmicrosoft.com フォールバック ドメインを追加して置き換える](https://learn.microsoft.com/ja-jp/microsoft-365/admin/setup/add-or-replace-your-onmicrosoftcom-domain?view=o365-worldwide)

#### アプリケーションで読み込むランディング ページを変更する方法を教えてください。

[アプリケーションの登録] ページで、ホームページの URL を任意のランディング ページの外部 URL に変更できます。 アプリケーションがマイ アプリまたは Office 365 ポータルから起動されると、指定したページが読み込まれます。 構成手順については、「[Microsoft Entra アプリケーション プロキシを使用して、発行されたアプリのカスタム ホーム ページを設定する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-custom-home-page)」を参照してください

#### URL に "#" (ハッシュタグ) 文字が含まれる場合は常に、公開済みのアプリケーションにアクセスしようとすると、切り捨てられた URL にリダイレクトされるのはなぜですか。

Microsoft Entra の事前認証が構成されており、アプリケーションに初めてアクセスしようとしたときにアプリケーションの URL に "#" 文字が含まれている場合、認証のために Microsoft Entra ID (login.microsoftonline.com) にリダイレクトされます。 認証が完了すると、"#" 文字の前の URL 部分にリダイレクトされ、"#" の後に来るものはすべて無視または削除されるようです。 たとえば、URL が `https://www.contoso.com/#/home/index.html`されている場合、Microsoft Entra 認証が完了すると、ユーザーは `https://www.contoso.com/`にリダイレクトされます。 この動作は仕様であり、ブラウザーによる "#" 文字の処理方法が原因です。

考えられる解決策/代替手段:

- `https://www.contoso.com` から `https://contoso.com/#/home/index.html` へのリダイレクトを設定します。 ユーザーは最初に `https://www.contoso.com` にアクセスする必要があります。
- 最初のアクセス試行に使用される URL には、エンコードされた形式の "#" 文字 (%23) を含める必要があります。 公開されているサーバーがこれを受け付けない可能性があります。
- パススルーの事前認証の種類を構成します (推奨されません)。

#### 公開できるのは IIS ベースのアプリケーションのみですか。 Windows 以外の Web サーバーで実行されている Web アプリケーションを公開することはできますか。 コネクタを IIS がインストールされているサーバーにインストールする必要はありますか。

いいえ。公開済みのアプリケーションには IIS の必要条件はありません。 Windows Server 以外のサーバーで実行されている Web アプリケーションを公開できます。 ただし、Web サーバーでネゴシエート (Kerberos 認証) がサポートされているかどうかによって、Windows 以外のサーバーで事前認証を使用できない場合があります。 コネクタがインストールされているサーバーでは、IIS は必要ありません。

#### HSTS ヘッダーを追加するようにアプリケーション プロキシを構成できますか。

アプリケーション プロキシでは、HTTP の Strict-Transport-Security ヘッダーは HTTPS 応答に自動的に追加されませんが、発行されたアプリケーションによって送信された元の応答にヘッダーが含まれる場合は保持されます。 この機能を有効にする設定の提供はロードマップにあります。

#### 外部 URL でカスタム ポート番号を使用できますか。

いいえ。プロトコル `http` が外部 URL で構成されている場合、Microsoft Entra アプリケーション プロキシ エンドポイントは、ポート TCP 80 で着信要求を受け入れ、プロトコル `https` が構成されている場合は、ポート TCP 443 で受け入れます。

#### 内部 URL でカスタム ポート番号を使用できますか。

はい。ポートを含む内部 URL はたとえば `http://app.contoso.local:8888/`、`https://app.contoso.local:8080/`、`https://app.contoso.local:8081/test/` のようになります。

#### 外部 URL と内部 URL が異なる場合は、どのような課題がありますか。

公開された Web アプリケーションによって送信される一部の応答には、ハードコードされた URL が含まれる場合があります。 この場合、リンク変換ソリューションを使用することで、クライアントで常に正しい URL が使用されるようにする必要があります。 リンク変換ソリューションは複雑になることがあり、一部のシナリオで機能しない可能性があります。 詳細については、「 [Microsoft Entra アプリケーション プロキシで公開されたアプリのハードコードされたリンクをリダイレクト](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-hard-coded-link-translation)する」を参照してください。

ベスト プラクティスとしては、外部 URL と内部 URL を同じにすることをお勧めします。 外部 URL と内部 URL で `protocol://hostname:port/path/` が同一であれば、両方の URL は同一であると見なされます。

これは、[カスタムドメイン](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)機能を使用して実現できます。

例 :

同じ場合:

```
External URL: https://app1.contoso.com/test/
Internal URL: https://app1.contoso.com/test/
```

同じでない場合:

```
External URL: https://app1.contoso.com/test/
Internal URL: http://app1.contoso.com/test/

External URL: https://app1.contoso.com/test/
Internal URL: https://app1.contoso.com:8080/test/

External URL: https://app1.msappproxy.net/test/
Internal URL: https://app1.contoso.com:/test/
```

内部 URL に標準以外のポート (TCP 80/443 以外) が含まれている場合、外部 URL と内部 URL を同一にすることはできません。

シナリオによっては、Web アプリの構成で変更を行う必要があります。

### 統合 Windows 認証

#### Kerberos の制約付き委任 (KCD) の設定時に PrincipalsAllowedToDelegateToAccount メソッドを使用する必要があるのはどのような場合ですか。

PrincipalsAllowedToDelegateToAccount メソッドは、コネクタ サーバーが Web アプリケーション サービス アカウントとは異なるドメインにある場合に使用します。 このメソッドでは、リソースベースの制約付き委任を使用する必要があります。 コネクタサーバーと Web アプリケーション サービス アカウントが同じドメインにある場合は、[Active Directory ユーザーとコンピューター] を使用して、各コネクタ コンピューター アカウントで委任設定を構成し、ターゲット SPN に委任することができます。

コネクタ サーバーと Web アプリケーション サービス アカウントが異なるドメインにある場合は、リソース ベースの委任が使用されます。 委任されたアクセス許可は、ターゲット Web サーバーと Web アプリケーション サービス アカウントで構成されます。 この制約付き委任方法は、比較的新しいものです。 この方法は、リソース (Web サービス) 所有者が、委任できるコンピューターとサービス アカウントを制御できるようにすることで、ドメイン間の委任に対応した Windows Server 2012 で導入されました。 この構成を支援する UI はないため、PowerShell を使用する必要があります。 詳細については、「[アプリケーション プロキシを使った Kerberos の制約付き委任](https://aka.ms/kcdpaper)」に関するホワイトペーパーを参照してください。

#### NTLM 認証は Microsoft Entra アプリケーション プロキシと連携していますか。

NTLM 認証は、事前認証またはシングル サインオンの方法として使用できません。 NTLM 認証は、クライアントと発行された Web アプリケーションの間で直接ネゴシエートできる場合にのみ使用できます。 NTLM 認証を使用すると、通常、ブラウザーにサインイン プロンプトが表示されます。

#### B2B IWA シングル サインオンのシナリオで、サインイン ID "オンプレミスのユーザー プリンシパル名" または "オンプレミスの SAM アカウント名" を使用できますか?

いいえ。Microsoft Entra ID のゲスト ユーザーには、前述のサインイン ID で必要な属性がないため、これは機能しません。

この場合、"ユーザー プリンシパル名" への代替処理が発生します。 B2B シナリオの詳細については、「 [Microsoft Entra ID の B2B ユーザーにオンプレミス アプリケーションへのアクセス権を付与する」を参照してください](https://learn.microsoft.com/ja-jp/entra/external-id/hybrid-cloud-to-on-premises)。

### パススルー事前認証

#### パススルー事前認証で公開されたアプリケーションに対して条件付きアクセス ポリシーを使用できますか。

条件付きアクセス ポリシーは、Microsoft Entra ID で正常に認証されたユーザーにのみ適用されます。 パススルー事前認証では Microsoft Entra 認証がトリガーされないため、条件付きアクセス ポリシーを適用することはできません。 パススルー事前認証では、オンプレミス サーバーに MFA ポリシーを実装するか (可能な場合)、または Microsoft Entra アプリケーション プロキシで Microsoft Entra ID 事前認証を有効にする必要があります。

#### クライアント証明書の認証要件が設定されている Web アプリケーションを公開できますか。

いいえ。TLS トラフィックはアプリケーション プロキシによって終了されるため、このシナリオはサポートされていません。

### リモート デスクトップ ゲートウェイ 公開

#### Microsoft Entra アプリケーション プロキシを使用してリモート デスクトップ ゲートウェイを公開するにはどうすればよいですか。

「[Microsoft Entra アプリケーション プロキシを使用しでリモート デスクトップを発行する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-integrate-with-remote-desktop-services)」を参照してください。

#### リモート デスクトップ ゲートウェイの公開シナリオで Kerberos の制約付き委任 (シングル サインオン - Windows 統合認証) は使用できますか?

いいえ、このシナリオはサポートされていません。

#### ユーザーが Internet Explorer 11 を使用しておらず、事前認証のシナリオを使用できません。 これは想定される動作ですか。

はい、そうです。 事前認証のシナリオでは ActiveX コントロールが必要ですが、これはサード パーティのブラウザーではサポートされていません。

#### リモート デスクトップ Web クライアント (HTML5) はサポートされていますか。

はい。このシナリオは現在プレビュー段階です。 「[Microsoft Entra アプリケーション プロキシを使用しでリモート デスクトップを発行する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-integrate-with-remote-desktop-services)」を参照してください。

#### 事前認証のシナリオを構成した後で、ユーザーは認証を 2 回 (1 回目は Microsoft Entra サインイン フォームで、2 回目は RDWeb サインイン フォームで) 行う必要があることがわかりました。 これは想定される動作ですか。 サインインの回数を 1 回に減らすにはどうすればよいですか。

はい、想定される動作です。 ユーザーが Microsoft Entra 参加済みコンピューターを使用している場合、そのユーザーは自動的に Microsoft Entra ID にサインインします。 ユーザーが資格情報を提供する必要があるのは、RDWeb サインイン フォームのみです。

#### Microsoft Entra の事前認証シナリオでは、リモート デスクトップ Web クライアント ポータルの [設定] の下にある [リソースの起動方法] オプションの [RDP ファイルをダウンロードする] を使用できますか?

このオプションを利用すると、ユーザーは rdp ファイルをダウンロードし、別の RDP クライアントでそれを利用できます (リモート デスクトップ Web Client の外で)。 一般に、別の RDP クライアント (Microsoft リモート デスクトップ クライアントなど) では事前承認をネイティブに処理できません。 そのため、このシナリオは機能しません。

### SharePoint の公開

#### Microsoft Entra アプリケーション プロキシを使用して SharePoint を公開するにはどうすればよいですか。

「[Microsoft Entra アプリケーション プロキシによる SharePoint へのリモート アクセスの有効化](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-integrate-with-sharepoint-server)」を参照してください。

#### SharePoint モバイル アプリ (iOS/Android) を使用して、公開された SharePoint Server にアクセスできますか。

[SharePoint モバイルアプリ](https://learn.microsoft.com/ja-jp/sharepoint/administration/supporting-the-sharepoint-mobile-apps-online-and-on-premises)では、現在、Microsoft Entra 事前承認はサポートされていません。

### Active Directory フェデレーション サービス (AD FS) の公開

#### Microsoft Entra アプリケーション プロキシを AD FS プロキシ (Web アプリケーション プロキシなど) として使用できますか。

いいえ。Microsoft Entra アプリケーション プロキシは Microsoft Entra ID で動作するように設計されているため、AD FS プロキシとして機能するための要件を満たしていません。

#### Microsoft Entra アプリケーション プロキシを使用して、AD FS エンドポイント (/adfs/portal/updatepassword/ など) を発行できますか。

いいえ、サポートされていません。

### WebSocket

#### Microsoft Entra アプリケーション プロキシでは WebSocket プロトコルはサポートされますか。

WebSocket プロトコル (QlikSense やリモート デスクトップ Web クライアント (HTML5) など) を使用するアプリケーションがサポートされるようになりました。 既知の制限事項には、次のようなものがあります。

- アプリケーション プロキシでは、WebSocket 接続を開く際に、サーバー応答に設定されている Cookie を破棄します。
- WebSocket 要求に SSO は適用されません。
- Windows Admin Center (WAC) の機能 (イベント ログ、PowerShell、リモート デスクトップ サービス) は、Microsoft Entra アプリケーション プロキシ経由では動作しません。

WebSocket アプリケーションには一意の公開要件は存在しないので、他のすべてのアプリケーション プロキシ アプリケーションと同じ方法で[公開](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)できます。

### リンク変換

#### リンク変換の使用はパフォーマンスに影響しますか。

はい。 リンク変換の使用はパフォーマンスに影響します。 アプリケーション プロキシ サービスは、アプリケーションでハードコードされたリンクをスキャンし、ユーザーに公開する前にそれぞれ公開されている外部 URL で置換します。

最適なパフォーマンスを得るには、[カスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)を構成して、同じ内部 URL と外部 URL を使用することをお勧めします。 カスタム ドメインを使用できない場合は、マイ アプリによるセキュリティで保護されたサインイン拡張機能またはモバイルの Microsoft Edge ブラウザーを使用することで、リンク変換のパフォーマンスを向上させることができます。 「[Microsoft Entra アプリケーション プロキシを使用して公開されたアプリのハードコーディングされたリンクをリダイレクトする](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-hard-coded-link-translation)」を参照してください。

### ワイルドカード

#### ワイルドカードを使用して、共通のカスタム ドメイン名と異なるプロトコル (HTTP 用と HTTPS 用それぞれ 1 つ) を持つ 2 つのアプリケーションを公開するにはどうすればよいですか。

このシナリオは直接はサポートされていません。 このシナリオで使用できるオプションは次のとおりです。

1. ワイルドカードを使用して HTTP と HTTPS の両方の URL を個別のアプリケーションとして公開し、それぞれに異なるカスタム ドメインを指定します。 この構成は、外部 URL が異なるため機能します。
2. ワイルドカード アプリケーションを使用して HTTPS URL を公開します。 次のアプリケーション プロキシの PowerShell コマンドレットを使用して、HTTP アプリケーションを個別に公開します。

    - [アプリケーション プロキシ アプリケーションの管理](https://learn.microsoft.com/ja-jp/powershell/module/azuread/?preserve-view=true#application_proxy_application_management)
    - [プライベート ネットワーク コネクタの管理](https://learn.microsoft.com/ja-jp/powershell/module/azuread/?preserve-view=true#application_proxy_connector_management)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-high-availability-load-balancing"} -->
## Microsoft Entra アプリケーション プロキシでの高可用性と負荷分散 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-high-availability-load-balancing
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: アプリケーション プロキシの展開でのトラフィック分散のしくみ。 コネクタのパフォーマンスを最適化する方法、およびバックエンド サーバーに対して負荷分散を使用する方法に関するヒントが含まれます。

### 概要

この記事では、アプリケーション プロキシの展開でのトラフィック分散のしくみについて説明します。 ユーザーとコネクタ間でのトラフィックの分散方法と、コネクタのパフォーマンスを最適化するためのヒントと、複数のバックエンド サーバー間の負荷分散に関する推奨事項について説明します。

### コネクタ間のトラフィック分散

コネクタでは、高可用性の原則に基づいて接続が確立されます。 トラフィックがコネクタ間に均等に分散される保証はなく、セッション アフィニティはありません。 そうではなく、使用状況は変化し、要求はアプリケーション プロキシ サービス インスタンスにランダムに送信されます。 結果として、トラフィックは通常、コネクタ全体に均等に分散されます。 次の図と手順では、ユーザーとコネクタの間の接続がどのように確立されるかを示します。

[Image: ユーザーとコネクタの間の接続を示す図]

1. クライアント デバイスのユーザーが、アプリケーション プロキシ経由で公開されているオンプレミスのアプリケーションにアクセスを試みます。
2. 要求は Azure Load Balancer を経由し、要求を受け取る必要があるアプリケーション プロキシ サービス インスタンスが決定されます。 リージョン内のすべてのトラフィックに対する要求を受け入れるために使用できるインスタンスが数十あります。 この方法により、トラフィックをサービス インスタンス間に均等に分散させることができます。
3. 要求は [Service Bus](https://learn.microsoft.com/ja-jp/azure/service-bus-messaging/) に送信されます。
4. Service Bus により、使用可能なコネクタにシグナルされます。 その後、コネクタによって Service Bus から要求が取得されます。
    - ステップ 2 で、要求は異なるアプリケーション プロキシ サービス インスタンスに送られるため、接続が異なるコネクタで処理される可能性がいっそう高くなります。 その結果、グループ内のコネクタはほぼ均等に使用されます。
5. コネクタによって、アプリケーションのバックエンド サーバーに要求が渡されます。 その後、アプリケーションにより応答がコネクタに返送されます。
6. コネクタでは、要求元のサービス インスタンスへの送信接続を開くことで、応答が完了されます。 その後、この接続はすぐに閉じられます。 既定では、各コネクタの同時送信接続の数は 200 に制限されています。
7. その後、応答がサービス インスタンスからクライアントに返されます。
8. 同じ接続からの後続の要求では、この手順が繰り返されます。

多くの場合、負荷がかかっているときは、アプリケーションに多くのリソースがあり、複数の接続が開かれています。 接続ごとに、サービス インスタンスへの割り当て手順が実行されます。 接続がコネクタとペアリングされていない場合は、新しい使用可能なコネクタが選択されます。

### コネクタの高可用性のベスト プラクティス

- 高可用性のためにコネクタ間にトラフィックが分散される方法により、コネクタ グループには常に少なくとも 2 つのコネクタが必要です。 コネクタ間に追加のバッファーを提供するため、3 つのコネクタが推奨されます。 必要なコネクタの正しい数を確認するには、容量計画のドキュメントに従います。
- 単一障害点を回避するには、異なる送信接続にコネクタを配置します。 複数のコネクタで同じ送信接続を使った場合、接続に関するネットワークの問題により、それを使うすべてのコネクタが影響を受けます。
- 運用アプリケーションに接続されているときは、コネクタを強制的に再起動しないようにします。 そのようにすると、コネクタ間のトラフィックの分散に悪影響が及ぶ可能性があります。 コネクタを再起動すると、より多くのコネクタが使用できなくなり、残りの使用可能なコネクタに強制的に接続されます。 結果として、最初のコネクタの使用が不均一になります。
- コネクタと Azure の間の送信トランスポート層セキュリティ (TLS) 通信では、すべての形式のインライン検査を行わないようにします。 この種のインライン検査では、通信フローが低下します。
- コネクタで自動更新が実行されていることを確認します。 プライベート ネットワーク コネクタアップデーター サービスが実行されている場合、コネクタは自動的に更新され、最新のアップグレードを受け取ります。 サーバーにコネクタ アップデーター サービスが見つからない場合は、コネクタを再インストールして更新プログラムを取得する必要があります。

### コネクタとバックエンド アプリケーション サーバーの間のトラフィック フロー

高可用性が要因であるもう 1 つの重要な部分は、コネクタとバックエンド サーバーの間の接続です。 アプリケーションが Microsoft Entra アプリケーション プロキシを介して発行されると、ユーザーからアプリケーションへのトラフィックは次の 3 つのホップを通過します。

1. ユーザーは、Azure 上の Microsoft Entra アプリケーション プロキシ サービスのパブリック エンドポイントに接続します。 クライアントの発信元クライアント IP アドレス (パブリック) と、アプリケーション プロキシ エンドポイントの IP アドレスとの間で、接続が確立されます。
2. プライベート ネットワーク コネクタは、アプリケーション プロキシ サービスからクライアントの HTTP 要求をプルします。
3. プライベート ネットワーク コネクタがターゲット アプリケーションに接続されます。 コネクタでは、接続を確立するために独自の IP アドレスが使われます。

[Image: アプリケーション プロキシ経由でアプリケーションに接続しているユーザーの図]

#### X-Forwarded-For ヘッダー フィールドに関する考慮事項

監査や負荷分散などの状況では、外部クライアントの送信元 IP アドレスをオンプレミス環境と共有することが要件です。 この要件に対処するために、Microsoft Entra プライベート ネットワーク コネクタは、送信元クライアント IP アドレス (パブリック) を持つ X-Forwarded-For ヘッダー フィールドを HTTP 要求に追加します。 適切なネットワーク デバイス (ロード バランサー、ファイアウォール)、Web サーバー、またはバックエンド アプリケーションでは、その情報を読み取って使用できます。

### 複数のアプリケーション サーバー間での負荷分散のベスト プラクティス

負荷分散は、アプリケーション プロキシ アプリケーションに割り当てられているコネクタ グループに 2 つ以上のコネクタがあり、バックエンド Web アプリケーションを複数のサーバーで実行している場合に重要です。

#### シナリオ 1: バックエンド アプリケーションでセッション永続化が必要ない

最も簡単なシナリオでは、バックエンド Web アプリケーションにおいてセッション持続性 (セッション永続化) が必要ありません。 バックエンド アプリケーション インスタンスは、サーバー ファーム内のユーザー要求を処理します。 レイヤー 4 のロード バランサーを使用し、アフィニティなしで構成することができます。 一部のオプションには、Microsoft ネットワーク負荷分散と Azure Load Balancer、または別のベンダーのロード バランサーが含まれます。 または、ラウンドロビン ドメイン ネーム システム (DNS) 戦略を構成します。

#### シナリオ 2: バックエンド アプリケーションでセッション永続化が必要である

このシナリオでは、認証されたセッションの間にバックエンド Web アプリケーションでセッション持続性 (セッション永続化) が必要です。 バックエンド アプリケーション インスタンスは、サーバー ファーム内の同じサーバーで処理されるユーザー要求を処理します。 クライアントは普通、アプリケーション プロキシ サービスに複数の接続を確立するため、このシナリオはいっそう複雑になる可能性があります。 異なる接続での要求は、ファーム内の異なるコネクタおよびサーバーに到着する場合があります。 各コネクタではこの通信に対して独自の IP アドレスが使われるため、ロード バランサーではコネクタの IP アドレスに基づくセッションの持続性を保証できません。 ソース IP アフィニティも使用できません。 シナリオ 2 のいくつかのオプションを次に示します。

- オプション 1: ロードバランサーによって設定されたセッション Cookie を基にして、セッションの永続化を実現します。 このオプションは、バックエンド サーバー間に負荷をいっそう均等に分散できるため推奨されます。 この機能を備えたレイヤー 7 ロード バランサーが必要であり、HTTP トラフィックを処理して、TLS 接続を終了することができます。 Azure Application Gateway (セッション アフィニティ) または別のベンダーのロード バランサーを使用できます。
- オプション 2:セッションの永続化のベースとして、X-Forwarded-For ヘッダー フィールドを使用します。 このオプションでは、この機能を備えたレイヤー 7 ロード バランサーが必要であり、HTTP トラフィックを処理して、TLS 接続を終了することができます。
- オプション 3:セッションの永続化が必要ないようにバックエンド アプリケーションを構成します。

バックエンド アプリケーションの負荷分散の要件については、お使いのソフトウェア ベンダーのドキュメントをご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-integrate-with-logic-apps"} -->
## Microsoft Entra アプリケーション プロキシを使用して Azure Logic Apps とオンプレミス API を安全に統合する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-integrate-with-logic-apps
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Microsoft Entra アプリケーション プロキシを使用すると、クラウドネイティブロジックアプリはオンプレミスの API に安全にアクセスしてワークロードをブリッジできます。

### 概要

Azure Logic Apps は、コードなしの環境でマネージド ワークフローを簡単に作成できるようにするサービスであり、さまざまな外部サービスやシステムと統合できます。 ワークフローにより、データ統合、データ処理、イベントドリブン シナリオなど、幅広いビジネス プロセスを自動化できます。 Logic Apps は、他のパブリック サービスおよびクラウドベースのサービスと統合されています。 または、オンプレミスのアプリケーションとサービスで Logic Apps を使用します。 プライベート ネットワーク統合では、ポート転送や従来のリバース プロキシを使用してサービスをパブリックに公開することはありません。

この記事では、Microsoft Entra アプリケーション プロキシ ソリューションを使用してロジック アプリへの安全なアクセスを提供し、不要なアクターから内部アプリケーションを保護するために必要な手順について説明します。 プロセスと最終的な結果は、 [Microsoft Entra アプリケーション プロキシを使用したオンプレミス API へのアクセス](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-secure-api-access) に似ています。ロジック アプリ内から API を利用することに特別な注意が払われます。

次の図は、Azure Logic Apps からアクセスするためにオンプレミス API を発行する従来の方法を示しています。 この方法では、API サービスに対して受信伝送制御プロトコル (TCP) ポート 80 または 443 を開く必要があります。

[Image: ロジック アプリから API への直接接続。]

次の図は、Microsoft Entra アプリケーション プロキシを使用してどのように、受信ポートを開くことなく Logic Apps (またはその他の Azure Cloud サービス) で使用する API を安全に公開するかを示しています。

[Image: Azure アプリケーション プロキシ経由のロジック アプリから API への接続。]

Microsoft Entra アプリケーション プロキシと関連コネクタを使用すると、ネットワーク セキュリティ インフラストラクチャの構成を増やすことなく、オンプレミス サービスへの安全な承認と統合が容易になります。

### 前提条件

このチュートリアルを実行するには、次が必要です。

- アプリを作成して登録できるアカウントを持つ、Azure ディレクトリへの管理者アクセス権
- 使用中のテナントにおけるロジック アプリ共同作成者役割（またはそれ以上）
- デプロイされた Azure プライベート ネットワーク コネクタと、「[オンプレミス アプリの追加 - Microsoft Entra ID でのアプリケーション プロキシの追加](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)」で詳しく説明されているように構成されたアプリケーション

注

ユーザーの権利を付与し、サインオンをテストすることをお勧めしますが、このガイドでは必要ありません。

### アプリケーション アクセスの構成

新しいエンタープライズ アプリケーションが作成されると、一致するアプリ登録も作成されます。 アプリ登録を使用すると、証明書、シークレット、またはフェデレーション資格情報を使用して、セキュリティで保護された、プログラムによるアクセスを構成できます。 ロジック アプリと統合するには、クライアント シークレット キーを構成し、API のアクセス許可を構成します。

1. [アプリケーション管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)にサインインします。
2. **Entra ID**&gt;**アプリ登録**に移動します。
3. *[アプリの登録]* ウィンドウから、**[すべてのアプリケーション]** タブ オプションを選択します。
4. デプロイされたアプリケーション プロキシ アプリケーションに一致する名前のアプリケーションに移動します。 たとえば、*サンプル アプリ 1* をエンタープライズ アプリケーションとしてデプロイした場合は、**サンプル アプリ 1** という登録項目を選びます。

    注

    関連付けられているアプリケーションが削除されるか、見つからない場合は自動的に作成されない可能性があります。 登録は、**[New Registration] (新規登録)** ボタンを使用して作成できます。
5. *サンプル アプリ 1* の詳細ページで、*[アプリケーション (クライアント) ID]* および *[ディレクトリ (テナント) ID]* フィールドを記録します。
6. ナビゲーション ウィンドウから **[API のアクセス許可]** メニュー項目を選択します。

    [Image: Microsoft Entra アプリ登録 API の [アクセス許可] メニュー項目。]

    1. **[アクセス許可の追加]** ボタンを選択します。

        1. **[所属する組織で使用している API]** タブを選択します。
        2. アプリを名前 (例: *サンプル アプリ 1*) で検索し、その項目を選択します。
        3. *[委任されたアクセス許可]* が**選択**されていることを確認し、**[user\_impersonation]** のチェック ボックスを*オン*にします。
        4. **アクセス許可の追加** を選択します。
    2. 構成されたアクセス許可が表示されていることを確認します。

        [Image: Microsoft Entra アプリ登録 API のアクセス許可の詳細。]
7. ナビゲーション ウィンドウから **[証明書とシークレット]** メニュー項目を選択します。

    [Image: Microsoft Entra アプリ登録証明書とシークレット メニュー項目。]

    1. **[クライアント シークレット]** タブ項目を選択します。
    2. **[New client secret]\(新しいクライアント シークレット\)** ボタンを選択します。
    3. *クライアントシークレットの追加*ポップアップから。

        1. **[説明]** と必要な有効期限を入力します。
        2. **[追加]** を選択します。
    4. 新しいクライアント シークレットが表示されていることを確認します。
    5. 新しく作成したシークレットの [値] の **[コピー]** ボタンを選びます。 後で使用するためにこのシークレットを安全に保存します。この値は 1 回だけ表示されます。

### ロジック アプリを構成する

1. Logic App から **デザイナー** ビューを開きます。
2. 目的のトリガーを選択します (プロンプトが表示された場合)。
3. 新しいステップを追加し、**HTTP** 操作を選択します。

    [Image: Azure ロジック アプリ トリガー オプション ペイン。]

    1. *[メソッド]*: 内部 API に送信する目的の HTTP メソッドを選択します。
    2. *URI*: Microsoft Entra ID に登録されているアプリケーションの*パブリック*完全修飾ドメイン名 (FQDN) と、API アクセスに必要な他の URI (例: *sampleapp1.msappproxy.net/api/1/status*) を入力します。

        注

        API の特定の値は、内部アプリケーションによって異なります。
    3. *[ヘッダー]*: 内部 API に送信する、目的のヘッダーを入力します。
    4. *[クエリ]*: 内部 API に送信する、目的のクエリを入力します。
    5. *[本体]*: 内部 API に送信する、目的の本文の内容を入力します。
    6. *[Cookie]*: 内部 API に送信する、目的の Cookie を入力します。
    7. *[新しいパラメーターの追加]* を選び、*[認証]* をオンにします。
    8. *[認証の種類]* の中から *[Microsoft Entra ID OAuth]* を選択します。
    9. 認証では、次の詳細を入力します。

        1. *[権限]*: 「*https://login.windows.net*」と入力します。
        2. *[テナント]*: 「**アプリケーション アクセスの構成**」でメモしたディレクトリ (テナント) ID を入力します。
        3. *[対象ユーザー]*: Microsoft Entra ID に登録されているアプリケーションの*パブリック* FQDN (例: *sampleapp1.msappproxy.net*) を入力します。
        4. *[クライアント ID]*: 「**アプリケーション アクセスの構成**」でメモしたアプリケーション (クライアント) ID を入力します。
        5. *[資格情報の種類]*: **シークレット**。
        6. *[シークレット]*: 「**アプリケーション アクセスの構成**」でメモしたシークレット値を入力します。
4. ロジック アプリを保存し、トリガーでテストします。

### 注意事項

認証/承認を必要とする API では、この方法を使用する際は特別な処理が必要です。 Microsoft Entra ID OAuth はアクセスに使用されているため、送信された要求には、内部 API で使用できない *承認* フィールドが既に含まれています (シングル サインオンが構成されていない場合)。 回避策として、一部のアプリケーションには、*Authorization* ヘッダー以外の方法を使用する認証または承認が用意されています。 たとえば、GitLab では *PRIVATE-TOKEN* というタイトルのヘッダーを使用でき、Atlassian JIRA では後の要求で使用できる Cookie を要求できます。

ロジック アプリの HTTP アクションにはクリア テキスト値が表示されます。 ただし、アプリ登録シークレット キーを Azure Key Vault に格納し、安全に取得および使用できるようにしてください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-integrate-with-microsoft-cloud-application-security"} -->
## アプリケーション プロキシを使用してオンプレミス アプリを Defender for Cloud Apps と統合する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-integrate-with-microsoft-cloud-application-security
- Service: entra-id / app-proxy
- Article date: 2026-05-26
- Summary: Microsoft Defender for Cloud Appsを、Microsoft Entra アプリケーション プロキシを介して発行されたオンプレミス アプリと統合して、ユーザー セッションを監視および制御します。

### 概要

Microsoft Entra ID でオンプレミス アプリケーションを使用してリアルタイム監視を行うには、Microsoft Defender for Cloud Apps を使用します。 Defender for Cloud Apps は、アプリの条件付きアクセス制御を使用して、条件付きアクセス ポリシーに基づいてセッションをリアルタイムで監視および制御します。 これらのポリシーを、Microsoft Entra ID でアプリケーション プロキシを使用するオンプレミス アプリケーションに適用します。

Defender for Cloud Apps で作成するポリシーの例を次に示します。

- アンマネージド デバイスの機密ドキュメントのダウンロードをブロックまたは保護する。
- 高リスク ユーザーがアプリケーションにサインオンするタイミングを監視し、セッション内からそのユーザーのアクションを記録する。 この情報を使用して、ユーザーの動作を分析することで、セッション ポリシーを適用する方法を判断します。
- クライアント証明書またはデバイスのコンプライアンスを使用して、アンマネージド デバイスからの特定のアプリケーションへのアクセスをブロックする。
- 非企業ネットワークからのユーザー セッションを制限します。 ご自身の企業ネットワークの外からアプリケーションにアクセスするユーザーには、制限付きアクセスを付与できます。 たとえば、この制限付きアクセスによって、ユーザーによる機密ドキュメントのダウンロードをブロックできます。

詳細については、「[条件付きアクセス アプリ制御を使用してアプリMicrosoft Defender for Cloud Apps保護する](https://learn.microsoft.com/ja-jp/defender-cloud-apps/proxy-intro-aad)」を参照してください。

### Prerequisites

- EMS E5 ライセンス、または Microsoft Entra ID P1 と Defender for Cloud Apps スタンドアロン。
- オンプレミス アプリケーションでは、Kerberos の制約付き委任 (KCD) を使用する必要があります。
- アプリケーション プロキシを使用するように Microsoft Entra ID を構成します。 アプリケーション プロキシの構成には、環境の準備とプライベート ネットワーク コネクタのインストールが含まれます。 チュートリアルについては、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application) のアプリケーション プロキシを介したリモート アクセス用のオンプレミス アプリケーションの追加」を参照してください。

### Microsoft Entra ID にオンプレミス アプリケーションを追加する

Microsoft Entra ID にオンプレミス アプリケーションを追加します。 クイックスタートについては、「[Microsoft Entra ID にオンプレミス アプリを追加する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)」を参照してください。 アプリケーションを追加するときは、[オンプレミス アプリケーションの追加] ページで 2 つの設定 **を** 必ず設定して、Defender for Cloud Apps で動作するようにします。

- **事前認証**: 「**Microsoft Entra ID**」を入力します。
- **[Translate URLs in Application Body](アプリケーション本文の URL を変換する)** : **[はい]** を選択します。

### オンプレミスのアプリケーションをテストする

ご自身のアプリケーションを Microsoft Entra ID に追加したら、「[アプリケーションをテストする](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application#test-the-application)」の手順を使ってテストするユーザーを追加し、サインオンをテストします。

### アプリの条件付きアクセス制御をデプロイする

条件付きアクセスのアプリケーション制御を使用してご自身のアプリケーションを構成するには、[Microsoft Entra アプリのアプリケーションの条件付きアクセス制御のデプロイ](https://learn.microsoft.com/ja-jp/defender-cloud-apps/proxy-deployment-aad)に関する記事を参照してください。

### アプリの条件付きアクセス制御をテストする

アプリケーションの条件付きアクセス制御を使って Microsoft Entra アプリケーションのデプロイをテストするには、[Microsoft Entra アプリのデプロイのテスト](https://learn.microsoft.com/ja-jp/defender-cloud-apps/proxy-deployment-aad)に関する記事の手順に従ってください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-integrate-with-power-bi"} -->
## Microsoft Entra アプリケーション プロキシを使用して Power BI へのリモート アクセスを有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-integrate-with-power-bi
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Power BI モバイル アプリ ユーザーが Microsoft Entra アプリケーション プロキシを使用して、企業ネットワークの外部から Power BI Report Server と SQL Server Reporting Services にアクセスできるようにします。

### 概要

この記事では、Microsoft Entra アプリケーション プロキシを使用して、Power BI モバイル アプリが Power BI Report Server (PBIRS) および SQL Server Reporting Services (SSRS) 2016 以降に接続できるようにする方法について説明します。 この統合により、企業ネットワークから離れているユーザーは、Power BI モバイル アプリから Power BI レポートにアクセスし、Microsoft Entra 認証によって保護できます。 この保護には、条件付きアクセスや多要素認証などの [セキュリティ上の利点](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-security#security-benefits) が含まれます。

### [前提条件]

- 環境内に Reporting Services をデプロイします。
- [Microsoft Entra アプリケーション プロキシを](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)有効にします。
- 可能な場合は、Power BI に同じ内部ドメインと外部ドメインを使用します。 カスタム ドメインの詳細については、「 [アプリケーション プロキシでのカスタム ドメインの操作](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)」を参照してください。

### 手順 1: Kerberos の制約付き委任 (KCD) を構成する

Windows 認証を使用するオンプレミス アプリケーションでは、Kerberos 認証プロトコルと Kerberos 制約付き委任 (KCD) と呼ばれる機能を使用してシングル サインオン (SSO) を実現できます。 プライベート ネットワーク コネクタは、ユーザーが Windows に直接サインインしていない場合でも、KCD を使用してユーザーの Windows トークンを取得します。 KCD の詳細については、「[Kerberos の制約付き委任の概要](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2012-R2-and-2012/jj553400%28v=ws.11%29)」および「[アプリケーション プロキシを使用したアプリへのシングルサインオンのための Kerberos 制約付き委任](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-sso-with-kcd)」を参照してください。

Reporting Services 側で構成する必要はあまりありません。 適切な Kerberos 認証を実行するには、有効なサービス プリンシパル名 (SPN) が必要です。 `Negotiate`認証のために Reporting Services サーバーを有効にします。

#### サービス プリンシパル名 (SPN) を構成する

SPN は、Kerberos 認証を使用するサービスの一意の識別子です。 レポート サーバーには適切な HTTP SPN が必要です。 レポート サーバーに適切なサービス プリンシパル名 (SPN) を構成する方法については、「レポート サーバーの [サービス プリンシパル名 (SPN) を登録](https://learn.microsoft.com/ja-jp/sql/reporting-services/report-server/register-a-service-principal-name-spn-for-a-report-server)する」を参照してください。 `Setspn` オプションを指定して `-L` コマンドを実行して、SPN が追加されたことを確認します。 コマンドの詳細については、「 [Setspn](https://learn.microsoft.com/ja-jp/windows-server/administration/windows-commands/setspn)」を参照してください。

#### ネゴシエート認証を有効にする

レポート サーバーで Kerberos 認証を使用できるようにするには、レポート サーバーの認証の種類を RSWindowsNegotiate に構成します。 rsreportserver.config ファイルを使用して、この設定を構成します。

```xml
<AuthenticationTypes>
    <RSWindowsNegotiate />
    <RSWindowsKerberos />
    <RSWindowsNTLM />
</AuthenticationTypes>
```

詳細については、「 [Reporting Services 構成ファイルの変更](https://learn.microsoft.com/ja-jp/sql/reporting-services/report-server/modify-a-reporting-services-configuration-file-rsreportserver-config) 」および「 [レポート サーバーでの Windows 認証の構成](https://learn.microsoft.com/ja-jp/sql/reporting-services/security/configure-windows-authentication-on-the-report-server)」を参照してください。

#### Reporting Services アプリケーション プール アカウントに追加された SPN への委任に対してコネクタが信頼されていることを確認する

Microsoft Entra アプリケーション プロキシ サービスが Reporting Services アプリケーション プール アカウントにユーザー ID を委任できるように KCD を構成します。 Microsoft Entra ID 認証ユーザーの Kerberos チケットを取得するようにプライベート ネットワーク コネクタを構成します。 サーバーは、Reporting Services アプリケーションにコンテキストを渡します。

KCD を構成するには、コネクタ マシンごとに次の手順を繰り返します。

1. ドメイン管理者としてドメイン コントローラーにサインインし、 **Active Directory ユーザーとコンピューター**を開きます。
2. コネクタが実行されているコンピューターを見つけます。
3. ダブルクリックしてコンピューターを選択し、[ **委任** ] タブを選択します。
4. 委任設定を [ **指定されたサービスへの委任に対してのみこのコンピューターを信頼する] に設定します**。 次に、[ **任意の認証プロトコルを使用する**] を選択します。
5. [ **追加]** を選択し、[ **ユーザーまたはコンピューター]** を選択します。
6. Reporting Services 用に設定したサービス アカウントを入力します。
7. [ **OK] を選択します**。 変更を保存するには、もう一度 **[OK] を選択します** 。

詳細については、「 [アプリケーション プロキシを使用してアプリにシングル サインオンするための Kerberos の制約付き委任](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-sso-with-kcd)」を参照してください。

### 手順 2: Microsoft Entra アプリケーション プロキシを使用して Reporting Services を発行する

これで、Microsoft Entra アプリケーション プロキシを構成する準備ができました。

1. 次の設定を使用して、アプリケーション プロキシを使用して Reporting Services を発行します。 アプリケーション プロキシを使用してアプリケーションを発行する方法の詳細な手順については、「 [Microsoft Entra アプリケーション プロキシを使用したアプリケーションの発行](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)」を参照してください。

    - **内部 URL**: 企業ネットワークでコネクタがアクセスできるレポート サーバーの URL を入力します。 コネクタがインストールされているサーバーからこの URL に到達できることを確認します。 ベスト プラクティスは、アプリケーション プロキシを介して発行されたサブパスの問題を回避するために、 `https://servername/` などの最上位ドメインを使用することです。 たとえば、`https://servername/`や`https://servername/reports/`ではなく、`https://servername/reportserver/`を使用します。

        注

        レポート サーバーへのセキュリティで保護された HTTPS 接続を使用します。 セキュリティで保護された接続の構成の詳細については、「 [ネイティブ モードのレポート サーバーでセキュリティで保護された接続を構成する](https://learn.microsoft.com/ja-jp/sql/reporting-services/security/configure-ssl-connections-on-a-native-mode-report-server)」を参照してください。
    - **外部 URL**: Power BI モバイル アプリが接続するパブリック URL を入力します。 たとえば、カスタム ドメインが使用されている場合は `https://reports.contoso.com` のようになります。 カスタム ドメインを使用するには、ドメインの証明書をアップロードし、ドメイン ネーム システム (DNS) レコードをアプリケーションの既定の `msappproxy.net` ドメインにポイントします。 詳細な手順については、「 [Microsoft Entra アプリケーション プロキシでのカスタム ドメインの操作](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)」を参照してください。
    - **事前認証方法**: Microsoft Entra ID。
2. アプリが発行されたら、次の手順でシングル サインオン設定を構成します。

    a. ポータルのアプリケーション ページで、[ **シングル サインオン**] を選択します。

    b。 **シングル サインオン モードの**場合は、[**統合 Windows 認証**] を選択します。

    c. **内部アプリケーション SPN** を、前に設定した値に設定します。

    d. ユーザーの代わりに使用するコネクタの **委任されたログイン ID を** 選択します。 詳細については、「 [さまざまなオンプレミス ID とクラウド ID の操作」を](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-sso-with-kcd#working-with-different-on-premises-and-cloud-identities)参照してください。

    e. [ **保存] を** 選択して変更を保存します。

アプリケーションの設定を完了するには、 **[ユーザーとグループ]** セクションに移動し、このアプリケーションにアクセスするユーザーを割り当てます。

### 手順 3: アプリケーションの応答 Uniform Resource Identifier (URI) を変更する

手順 2 で自動的に作成されたアプリケーション登録を構成します。

1. Microsoft Entra ID **の [概要** ] ページで、[ **アプリの登録**] を選択します。
2. [ **すべてのアプリケーション** ] タブで、手順 2 で作成したアプリケーションを検索します。
3. アプリケーションを選択し、[ **認証**] を選択します。
4. プラットフォームのリダイレクト URI を追加します。

    **iOS** で Power BI Mobile 用にアプリを構成する場合は、`Public Client (Mobile & Desktop)`の種類のリダイレクト Uniform Resource Identifier (URI) を追加します。

    - `msauth://code/mspbi-adal%3a%2f%2fcom.microsoft.powerbimobile`
    - `msauth://code/mspbi-adalms%3a%2f%2fcom.microsoft.powerbimobilems`
    - `mspbi-adal://com.microsoft.powerbimobile`
    - `mspbi-adalms://com.microsoft.powerbimobilems`

    **Android** で Power BI Mobile 用にアプリを構成する場合は、`Public Client (Mobile & Desktop)`の種類のリダイレクト Uniform Resource Identifier (URI) を追加します。

    - `urn:ietf:wg:oauth:2.0:oob`
    - `mspbi-adal://com.microsoft.powerbimobile`
    - `msauth://com.microsoft.powerbim/g79ekQEgXBL5foHfTlO2TPawrbI%3D`
    - `msauth://com.microsoft.powerbim/izba1HXNWrSmQ7ZvMXgqeZPtNEU%3D`

    重要

    アプリケーションが正常に動作するためには、リダイレクト URI を追加する必要があります。 Power BI Mobile iOS と Android の両方用にアプリを構成する場合は、iOS 用に構成されたリダイレクト URI の一覧にパブリック クライアント (モバイルとデスクトップ) の種類のリダイレクト URI を追加します: `urn:ietf:wg:oauth:2.0:oob`。

### 手順 4: Power BI モバイル アプリから接続する

1. Power BI モバイル アプリで、Reporting Services インスタンスに接続します。 アプリケーション プロキシを使用して発行したアプリケーションの **外部 URL を** 入力します。

    [Image: 外部 URL を含む Power BI モバイル アプリを示すスクリーンショット。]
2. [ **接続**] を選択します。 Microsoft Entra サインイン ページが読み込まれます。
3. ユーザーの有効な資格情報を入力し、[ **サインイン**] を選択します。 Reporting Services サーバーの要素が表示されます。

### 手順 5: マネージド デバイスの Intune ポリシーを構成する (省略可能)

Microsoft Intune を使用して、会社の従業員が使用するクライアント アプリを管理できます。 Intune には、データ暗号化やアクセス要件などの機能が用意されています。 Intune ポリシーを使用して Power BI モバイル アプリケーションを有効にします。

1. **[Entra ID]**&gt;**[アプリの登録]** に移動します。
2. ネイティブ クライアント アプリケーションを登録するときに、手順 3 で構成したアプリケーションを選択します。
3. アプリケーションのページで、[ **API のアクセス許可**] を選択します。
4. [ **アクセス許可の追加] を選択します**。
5. **組織が使用する API で**、*Microsoft Mobile Application Management* を検索して選択します。
6. **DeviceManagementManagedApps.ReadWrite** アクセス許可をアプリケーションに追加します。
7. [ **管理者の同意を付与** する] を選択して、アプリケーションへのアクセス許可を付与します。
8. [「アプリ保護ポリシーを作成して割り当てる方法](https://learn.microsoft.com/ja-jp/mem/intune/apps/app-protection-policies)」を参照して、必要な Intune ポリシーを構成します。

### トラブルシューティング

数分以上レポートを読み込もうとした後にアプリケーションからエラー ページが返された場合は、タイムアウト設定の変更が必要になることがあります。 既定では、アプリケーション プロキシは、要求に応答するのに最大 85 秒かかるアプリケーションをサポートします。 この設定を 180 秒に長くするには、アプリケーションのアプリケーション プロキシ設定ページで、バックエンドのタイムアウトを **[長い** ] に選択します。 高速で信頼性の高いレポートを作成する方法に関するヒントについては、「 [Power BI レポートのベスト プラクティス」](https://learn.microsoft.com/ja-jp/power-bi/guidance/power-bi-optimization)を参照してください。

Microsoft Entra アプリケーション プロキシを使用して Power BI モバイル アプリがオンプレミスの Power BI Report Server に接続できるようにすることは、承認されたクライアント アプリとして Microsoft Power BI アプリを必要とする条件付きアクセス ポリシーではサポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-integrate-with-remote-desktop-services"} -->
## Microsoft Entra アプリケーション プロキシを使用したリモート デスクトップの公開 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-integrate-with-remote-desktop-services
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: リモート デスクトップと RemoteApps への外部アクセスをセキュリティで保護するために、Microsoft Entra アプリケーション プロキシを介してリモート デスクトップ サービス (RDS) の展開を発行します。

### 概要

リモート デスクトップ サービスと Microsoft Entra アプリケーション プロキシが連携し、企業ネットワークの外にいるユーザーの生産性を向上させます。

この記事の対象読者は次のとおりです。

- リモート デスクトップ サービスを介してオンプレミス アプリケーションを発行することで、エンド ユーザーにさらに多くのアプリケーションを提供する現在のアプリケーション プロキシのお客様。
- Microsoft Entra アプリケーション プロキシを使用してデプロイでの攻撃対象領域を小さく抑えたいと考えている、現在リモート デスクトップ サービスを使用しているお客様。 このシナリオでは、2 段階検証と条件付きアクセス制御のセットを RDS に付与します。

### アプリケーション プロキシが標準の RDS 展開にどのように適合するか

RDS の標準デプロイには、Windows Server で実行されるさまざまなリモート デスクトップ ロール サービスが含まれています。 [リモート デスクトップ サービス アーキテクチャ](https://learn.microsoft.com/ja-jp/windows-server/remote/remote-desktop-services/Desktop-hosting-logical-architecture)には、複数の展開オプションがあります。 他の RDS 展開オプションとは異なり、 [Microsoft Entra アプリケーション プロキシを使用した RDS 展開](https://learn.microsoft.com/ja-jp/windows-server/remote/remote-desktop-services/Desktop-hosting-logical-architecture) (次の図を参照) には、コネクタ サービスを実行しているサーバーからの永続的な送信接続があります。 その他のデプロイでは、ロード バランサーを介した着信接続が開いたままになります。

[Image: RDS VM とパブリック インターネットの間のアプリケーション プロキシの配置方法を示す図。]

RDS 展開では、リモート デスクトップ (RD) Web ロールと RD ゲートウェイの役割は、インターネットに接続するコンピューターで実行されます。 これらのエンドポイントは次の理由で公開されています。

- RD Web は、アクセスできるさまざまなオンプレミスのアプリケーションとデスクトップにサインインして表示するためのパブリック エンドポイントをユーザーに提供します。 リソースを選択すると、OS 上のネイティブ アプリを使用してリモート デスクトップ プロトコル (RDP) 接続が作成されます。
- ユーザーが RDP 接続を起動すると、RD ゲートウェイが関与するようになります。 RD ゲートウェイは、インターネット経由で送信される暗号化された RDP トラフィックを処理し、ユーザーが接続しているオンプレミスのサーバーに渡します。 このシナリオでは、RD ゲートウェイの受信トラフィックは Microsoft Entra アプリケーション プロキシからのものです。

ヒント

詳細については、 [Azure Resource Manager と Azure Marketplace を使用して RDS をシームレスにデプロイする方法](https://learn.microsoft.com/ja-jp/windows-server/remote/remote-desktop-services/rds-in-azure)を参照してください。

### 要求事項

- RD Web と RD ゲートウェイの両方のエンドポイントが同じコンピューター上にあり、ルートが共通である必要があります。 RD Web と RD ゲートウェイは、2 つのアプリケーション間でシングル サインオン エクスペリエンスを実現できるように、アプリケーション プロキシを使用して単一のアプリケーションとして発行されます。
- [RDS を展開](https://learn.microsoft.com/ja-jp/windows-server/remote/remote-desktop-services/rds-in-azure)し、 [アプリケーション プロキシを有効にします](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)。 アプリケーション プロキシを有効にし、必要なポートと URL を開き、サーバーでトランスポート層セキュリティ (TLS) 1.2 を有効にします。 開く必要があるポートとその他の詳細については、「 [チュートリアル: Microsoft Entra ID のアプリケーション プロキシを使用してリモート アクセス用のオンプレミス アプリケーションを追加](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)する」を参照してください。
- エンド ユーザーは、RD Web または RD Web クライアントに接続するために、互換性のあるブラウザーを使用する必要があります。 詳細については、「 クライアント構成のサポート」を参照してください。
- RD Web を発行する場合は、可能であれば、同じ内部および外部の完全修飾ドメイン名 (FQDN) を使用します。 内部および外部の完全修飾ドメイン名 (FQDN) が異なる場合は、クライアントが無効なリンクを受信しないように、要求ヘッダー変換を無効にします。
- RD Web クライアントを使用している場合は、同じ内部および外部 FQDN を使用 *する必要があります* 。 内部 FQDN と外部 FQDN が異なる場合は、RD Web クライアント経由で RemoteApp 接続を確立するときに Websocket エラーが発生します。
- Internet Explorer で RD Web を使用している場合は、RDS ActiveX アドオンを有効にする必要があります。
- RD Web クライアントを使用している場合は、アプリケーション プロキシ [コネクタ バージョン 1.5.1975 以降](https://learn.microsoft.com/ja-jp/entra/global-secure-access/reference-version-history)を使用する必要があります。
- Microsoft Entra 事前認証フローの場合、ユーザーは **RemoteApp とデスクトップ** ウィンドウで公開されたリソースにのみ接続できます。 ユーザーは、[リモート PC に接続] ウィンドウを使用してデスクトップ **に接続** できません。
- Windows Server 2019 を使用している場合は、HTTP2 プロトコルを無効にする必要があります。 詳細については、「 [チュートリアル: Microsoft Entra ID でアプリケーション プロキシを使用してリモート アクセスするためのオンプレミス アプリケーションを追加](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)する」を参照してください。

### RDS とアプリケーション プロキシの共同シナリオをデプロイする

RDS と Microsoft Entra アプリケーション プロキシを自分の環境用に設定した後、2 つのソリューションを結合する手順に従ってください。 これらの手順では、2 つの Web 向け RDS エンドポイント (RD Web および RD ゲートウェイ) をアプリケーションとして発行し、RDS 上のトラフィックをアプリケーション プロキシ経由で送信する手順について説明します。

#### RD ホスト エンドポイントを発行する

1. [値を使用して新しいアプリケーション プロキシ アプリケーションを発行](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application) します。

    - [内部 URL]: `https://<rdhost>.com/`。 `<rdhost>` は、RD Web と RD ゲートウェイが共有する共通のルートです。
    - 外部 URL:このフィールドは、アプリケーションの名前に基づいて自動的に設定されますが、変更することもできます。 ユーザーが RDS にアクセスすると、この URL に移動します。
    - 事前認証方法: Microsoft Entra ID。
    - URL ヘッダーを変換する: いいえ。
    - HTTP-Only Cookie を使用する: いいえ。
2. 発行した RD アプリケーションにユーザーを割り当てます。 すべてのユーザーが RDS へのアクセス権を持っていることもご確認ください。
3. アプリケーションのシングル サインオン方法は **、Microsoft Entra のシングル サインオンを無効のままにします**。

    注

    ユーザーは、Microsoft Entra ID に対して 1 回と、RD Web に対して 1 回認証を求められますが、RD ゲートウェイに対してはシングル サインオンを使用できます。
4. **[Entra ID]**&gt;**[アプリの登録]** に移動します。 一覧からアプリを選択します。
5. [ **管理**] で [ **ブランド]** を選択します。
6. RD Web エンドポイントを指す **[ホーム ページの URL** ] フィールドを更新します ( `https://<rdhost>.com/RDWeb`など)。

#### RDS トラフィックをアプリケーション プロキシに転送する

RDS デプロイに管理者として接続し、デプロイの RD ゲートウェイ サーバーの名前を変更します。 この構成により、Microsoft Entra アプリケーション プロキシ サービスを介して接続が行われるようになります。

1. RD 接続ブローカーのロールを実行している RDS サーバーに接続します。
2. **サーバー マネージャーを起動します**。
3. 左側のウィンドウから **[リモート デスクトップ サービス** ] を選択します。
4. [ **概要**] を選択します。
5. [デプロイの概要] セクションで、ドロップダウン メニューを選択し、[ **配置プロパティの編集]** を選択します。
6. [RD ゲートウェイ] タブで、[ **サーバー名** ] フィールドを、アプリケーション プロキシの RD ホスト エンドポイントに設定した外部 URL に変更します。
7. [ **ログオン方法** ] フィールドを **[パスワード認証**] に変更します。

    [Image: RDS の [展開のプロパティ] 画面を示すスクリーンショット。]
8. 各コレクションに対してこのコマンドを実行します。 * &lt;yourcollectionname&gt;*と*&lt;proxyfrontendurl&gt;*を独自の情報に置き換えます。 このコマンドにより、RD Web と RD ゲートウェイの間でシングル サインオンが有効になり、パフォーマンスが最適化されます。

    ```powershell
    Set-RDSessionCollectionConfiguration -CollectionName "<yourcollectionname>" -CustomRdpProperty "pre-authentication server address:s:<proxyfrontendurl>`nrequire pre-authentication:i:1"
    ```

    **例えば：**

    ```powershell
    Set-RDSessionCollectionConfiguration -CollectionName "QuickSessionCollection" -CustomRdpProperty "pre-authentication server address:s:https://remotedesktoptest-aadapdemo.msappproxy.net/`nrequire pre-authentication:i:1"
    ```

    注

    コマンドでは、``nrequire` でバッククォートを使用しています。
9. カスタム RDP プロパティの変更を確認し、このコレクションの RDWeb からダウンロードされた RDP ファイルの内容を表示するには、次のコマンドを実行します。

    ```powershell
    (get-wmiobject -Namespace root\cimv2\terminalservices -Class Win32_RDCentralPublishedRemoteDesktop).RDPFileContents
    ```

リモート デスクトップが構成されたので、Microsoft Entra アプリケーション プロキシが RDS のインターネットに接続するコンポーネントとして引き継ぎます。 RD Web および RD ゲートウェイ マシン上の他のパブリック インターネット接続エンドポイントを削除します。

#### RD Web クライアントを有効にする

ユーザーが RD Web クライアントを使用する場合は、「 [ユーザーのリモート デスクトップ Web クライアントを設定する](https://learn.microsoft.com/ja-jp/windows-server/remote/remote-desktop-services/clients/remote-desktop-web-client-admin)」の手順に従います。

リモート デスクトップ Web クライアントは、組織のリモート デスクトップ インフラストラクチャへのアクセスを提供します。 Microsoft Edge、Google Chrome、Safari、Mozilla Firefox (v55.0 以降) などの HTML5 互換 Web ブラウザーが必要です。

### シナリオをテストする

Windows 7 または 10 のコンピューターで Internet Explorer を使用してシナリオをテストします。

1. 設定した外部 URL に移動するか、 [MyApps パネル](https://myapps.microsoft.com)でアプリケーションを見つけます。
2. Microsoft Entra ID に対して認証を行います。 アプリケーションに割り当てたアカウントを使用します。
3. RD Web に対する認証。
4. RDS 認証が成功すると、目的のデスクトップまたはアプリケーションを選び、作業を開始できます。

### その他のクライアント構成のサポート

この記事で説明する構成は、RD Web または RD Web クライアントによる RDS へのアクセスを対象としています。 ただし、必要に応じて、その他のオペレーティング システムまたはブラウザーを使用することもできます。 異なる点は、使用する認証方法です。

| 認証方法 | サポートされているクライアント構成 |
| --- | --- |
| 事前認証 | RD Web - Windows 7/10/11 ([`Microsoft Edge Chromium IE mode`](https://learn.microsoft.com/ja-jp/deployedge/edge-ie-mode) + RDS ActiveX アドオンを使用) |
| 事前認証 | RD Web クライアント: Microsoft Edge、Internet Explorer 11、Google Chrome、Safari、Mozilla Firefox (v55.0 以降) などの HTML5 互換 Web ブラウザー |
| パススルー | Microsoft リモート デスクトップ アプリケーションをサポートするその他の任意のオペレーティング システム |

注

`Microsoft Edge Chromium IE` モードは、マイ アプリ ポータルを使用してリモート デスクトップ アプリにアクセスする場合に必要です。

事前認証フローは、パススルー フローよりも多くのセキュリティ上の利点を提供します。 事前認証を使用すると、オンプレミス リソースのシングル サインオン、条件付きアクセス、2 段階認証などの Microsoft Entra 認証機能を使用できます。 また、認証されたトラフィックのみが、ネットワークに到達できます。

この記事に記載された手順を 2 か所変更するだけで、パススルー認証を使用できます。

1. RD ホスト エンドポイントの発行 手順 1 で、事前認証方法を **パススルー**に設定します。
2. RDS トラフィックをアプリケーション プロキシに直接送信する場合は、手順 8 を完全にスキップします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-integrate-with-sharepoint-server"} -->
## SharePoint へのリモート アクセスの有効化 - Microsoft Entra アプリケーション プロキシ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-integrate-with-sharepoint-server
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: シングル サインオンに Kerberos の制約付き委任を使用して、オンプレミスの SharePoint Server へのセキュリティで保護された外部アクセスを有効にするように Microsoft Entra アプリケーション プロキシを構成します。

### 概要

この手順ガイドでは、オンプレミスの SharePoint ファームと Microsoft Entra アプリケーション プロキシを統合する方法について説明します。

### [前提条件]

構成を実行するには、次のリソースが必要です。

- SharePoint 2016 ファーム以降。
- アプリケーション プロキシを含むプランを持つ Microsoft Entra テナント。 [Microsoft Entra IDプランと価格](https://www.microsoft.com/security/business/microsoft-entra-pricing)の詳細については、こちらを参照してください。
- オンプレミスの SharePoint ファームから Office ファイルを適切に起動するための Microsoft Office Web Apps Server ファーム。
- Microsoft Entra テナント内の[確認済みカスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)。
- オンプレミスの Active Directory デプロイは Microsoft Entra Connect と同期され、ユーザーは [Azure にサインイン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-user-signin)できます。
- 企業ドメイン内のマシンにインストールされ、実行されているプライベート ネットワーク コネクタ。

アプリケーション プロキシを使用して SharePoint を構成するには、次の 2 つの URL が必要です。

- エンドユーザーが確認でき、Microsoft Entra ID で設定された外部 URL。 この URL ではカスタム ドメインを使用できます。 詳しくは、[Microsoft Entra アプリケーション プロキシでのカスタム ドメインの使用](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)に関するページを参照してください。
- 内部 URL。企業ドメイン内でのみ認識され、直接使用されることはありません。

重要

リンクが正しくマップされるようにするには、内部 URL について次の推奨事項に従います。

- HTTPS を使用する。
- カスタム ポートを使用しない。
- エイリアス (`A` レコード) ではなく、SharePoint Web フロントエンド (WFE) (またはロード バランサー) を指すホスト (`CName` レコード) を企業ドメイン ネーム システム (DNS) に作成します。

この記事では、次の値を使用しています。

- 内部 URL: `https://sharepoint`。
- 外部 URL: `https://spsites-demo1984.msappproxy.net/`。
- SharePoint Web アプリケーションのアプリケーション プール アカウント: `Contoso\spapppool`。

### 手順 1: アプリケーション プロキシを使用する Microsoft Entra ID でアプリケーションを構成する

この手順では、アプリケーション プロキシを使用するアプリケーションを Microsoft Entra テナントに作成します。 外部 URL を設定し、内部 URL を指定します。どちらも、後で SharePoint において使用します。

1. 次の設定でアプリを作成します。 詳細な手順については、[Microsoft Entra アプリケーション プロキシを使用したアプリケーションの発行](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)に関するページを参照してください。

    - **内部 URL**: 後で SharePoint で設定した SharePoint 内部 URL ( `https://sharepoint`など)。
    - **事前認証**: `Microsoft Entra ID`。
    - **ヘッダー内の URL を翻訳**する: `No`。
    - **アプリケーション本文で URL を翻訳**する: `No`。

    [Image: [名前]、[内部 URL]、[外部 URL]、[事前認証] の各フィールドが Microsoft Entra ID に設定されている Microsoft Entra アプリケーション プロキシ設定のスクリーンショット。]
2. アプリを発行したら、次の手順に従ってシングル サインオン設定を構成します。

    1. ポータルのアプリケーション ページで、[ **シングル サインオン**] を選択します。
    2. **シングル サインオン モードの**場合は、[**統合 Windows 認証**] を選択します。
    3. **内部アプリケーション サービス プリンシパル名 (SPN)** を、前に設定した値に設定します。 この例では、値は `HTTP/sharepoint` です。
    4. **[委任されたログイン ID]** で、お使いの Active Directory フォレストの構成に最も適したオプションを選択します。 たとえば、フォレストに 1 つの Active Directory ドメインがある場合は、(次のスクリーンショットに示すように) **オンプレミスの SAM アカウント名** を選択します。 ただし、ユーザーが SharePoint およびプライベート ネットワーク コネクタ サーバーと同じドメインにない場合は、 **オンプレミスのユーザー プリンシパル名** (スクリーンショットには表示されません) を選択します。

    [Image: SPN が HTTP/SharePoint に設定され、委任されたログイン ID がオンプレミスの SAM アカウント名に設定されていることを示す [統合 Windows 認証の構成] ダイアログのスクリーンショット。]
3. アプリケーションの設定を完了し、[ **ユーザーとグループ** ] セクションに移動し、このアプリケーションにアクセスするユーザーを割り当てます。

### ステップ 2: SharePoint Web アプリケーションを構成する

Microsoft Entra アプリケーション プロキシで正しく動作するように、Kerberos と適切な代替アクセス マッピングを使用して SharePoint Web アプリケーションを構成する必要があります。 次の 2 つのオプションがあります。

- 新しい Web アプリケーションを作成し、 **既定** のゾーンのみを使用します。 SharePoint で最適なエクスペリエンスを得るための既定のゾーンを使用します。 たとえば、SharePoint によって生成される電子メール アラート内のリンクは **、既定** のゾーンを指します。
- 既存の Web アプリケーションを拡張して、既定以外のゾーンで Kerberos を構成します。

重要

使用するゾーンに関係なく、SharePoint Web アプリケーションのアプリケーション プール アカウントは、Kerberos が正常に動作するためのドメイン アカウントである必要があります。

#### SharePoint Web アプリケーションを作成する

- このスクリプトは、 **既定** のゾーンを使用して新しい Web アプリケーションを作成する例を示しています。 既定のゾーンを使用することをお勧めします。

    1. **SharePoint 管理シェル**を起動し、スクリプトを実行します。

        ```powershell
        # This script creates a web application and configures the Default zone with the internal/external URL needed to work with Microsoft Entra application proxy
        # Edit variables below to fit your environment. Note that the managed account must exist and it must be a domain account
        $internalUrl = "https://sharepoint"
        $externalUrl = "https://spsites-demo1984.msappproxy.net/"
        $applicationPoolManagedAccount = "Contoso\spapppool"
        
        $winAp = New-SPAuthenticationProvider -UseWindowsIntegratedAuthentication -DisableKerberos:$false
        $wa = New-SPWebApplication -Name "SharePoint - AAD Proxy" -Port 443 -SecureSocketsLayer -URL $externalUrl -ApplicationPool "SharePoint - AAD Proxy" -ApplicationPoolAccount (Get-SPManagedAccount $applicationPoolManagedAccount) -AuthenticationProvider $winAp
        New-SPAlternateURL -Url $internalUrl -WebApplication $wa -Zone Default -Internal
        ```
    2. **[SharePoint サーバーの全体管理]** サイトを開きます。
    3. **[システム設定]** で、**[代替アクセス マッピングの構成]** を選択します。 **[代替アクセス マッピング コレクション]** ボックスが開きます。
    4. 新しい Web アプリケーションでディスプレイをフィルター処理します。

        [Image: 既定のゾーンの SharePoint Web アプリケーションの内部 URL とパブリック URL を示す [代替アクセス マッピング] ページのスクリーンショット。]
- 既存の Web アプリケーションを新しいゾーンに拡張する場合。

    1. SharePoint 管理シェルを起動し、次のスクリプトを実行します。

        ```powershell
        # This script extends an existing web application to Internet zone with the internal/external URL needed to work with Microsoft Entra application proxy
        # Edit variables below to fit your environment
        $webAppUrl = "http://spsites/"
        $internalUrl = "https://sharepoint"
        $externalUrl = "https://spsites-demo1984.msappproxy.net/"
        
        $winAp = New-SPAuthenticationProvider -UseWindowsIntegratedAuthentication -DisableKerberos:$false
        $wa = Get-SPWebApplication $webAppUrl
        New-SPWebApplicationExtension -Name "SharePoint - AAD Proxy" -Identity $wa -SecureSocketsLayer -Zone Extranet -Url $externalUrl -AuthenticationProvider $winAp
        New-SPAlternateURL -Url $internalUrl -WebApplication $wa -Zone Extranet -Internal
        ```

    **[SharePoint サーバーの全体管理]** サイトを開きます。

    1. **[システム設定]** で、**[代替アクセス マッピングの構成]** を選択します。 **[代替アクセス マッピング コレクション]** ボックスが開きます。
    2. 拡張した Web アプリケーションでディスプレイをフィルター処理します。

        [Image: SharePoint Web アプリケーションが選択されている既定のゾーンとエクストラネット ゾーンの内部 URL とパブリック URL を示す [代替アクセス マッピング] ページのスクリーンショット。]

#### SharePoint Web アプリケーションがドメイン アカウントで実行されていることを確認する

SharePoint Web アプリケーションのアプリケーション プールが実行されているアカウントを特定し、それがドメイン アカウントであることを確認するには、次の手順を実行します。

1. **[SharePoint サーバーの全体管理]** サイトを開きます。
2. **[セキュリティ]** に移動し、**[サービス アカウントの構成]** を選択します。
3. **Web アプリケーション プール - あなたのWebアプリケーション名** を選択します。

    [Image: Web アプリケーション プール - SharePoint 80 とドメイン アカウントのドロップダウンを含むサービス アカウントの選択ダイアログのスクリーンショット。]
4. 次の手順で使用するため、 **このコンポーネントのアカウントを選択** するとドメイン アカウントが返されることを確認し、覚えておいてください。

#### エクストラネット ゾーンの IIS サイトに対して HTTPS 証明書が構成されていることを確認します

内部 URL は HTTPS プロトコル (`https://SharePoint/`) を使用するため、インターネット インフォメーション サービス (IIS) サイトで証明書を設定する必要があります。

1. Windows PowerShell コンソールを開きます。
2. 次のスクリプトを実行して自己署名証明書を生成し、コンピューターの `MY store`に追加します。

    ```powershell
    # Replace "SharePoint" with the actual hostname of the Internal URL of your Microsoft Entra proxy application
    New-SelfSignedCertificate -DnsName "SharePoint" -CertStoreLocation "cert:\LocalMachine\My"
    ```

    重要

    自己署名証明書はテスト目的にのみ適しています。 運用環境では、代わりに証明機関によって発行された証明書を使用します。
3. インターネット インフォメーション サービス マネージャー コンソールを開きます。
4. ツリー ビューでサーバーを展開し、**[サイト]** を展開し、**[SharePoint - Microsoft Entra ID Proxy]** サイトを選択して **[バインド]** を選択します。
5. **https バインド**を選択して、**[編集]** を選択します。
6. [トランスポート層セキュリティ (TLS) 証明書] フィールドで、[ **SharePoint** 証明書] を選択し、[ **OK] を選択します**。

これで、Microsoft Entra アプリケーション プロキシ経由で外部から SharePoint サイトにアクセスできるようになりました。

### ステップ 3: Kerberos の制約付き委任 (KCD) を構成する

ユーザーは最初に Microsoft Entra ID で認証し、次に Microsoft Entra プライベート ネットワーク コネクタを介して Kerberos を使用して SharePoint に対して認証を行います。 Microsoft Entra ユーザーに代わってコネクタで Kerberos トークンを取得できるようにするには、プロトコルの切り替えで Kerberos の制約付き委任 (KCD) を構成する必要があります。 KCD の詳細については、「 [Kerberos の制約付き委任の概要](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2012-R2-and-2012/jj553400%28v=ws.11%29)」を参照してください。

#### SharePoint サービス アカウントのサービス プリンシパル名 (SPN) を設定する

この記事では、内部 URL が `https://sharepoint` のため、サービス プリンシパル名 (SPN) は `HTTP/sharepoint` です。 これらの値を、環境に対応する値に置き換えます。 SharePoint アプリケーション プール アカウント `HTTP/sharepoint` に SPN `Contoso\spapppool` を登録するには、ドメインの管理者としてコマンド プロンプトから次のコマンドを実行します。

`setspn -S HTTP/sharepoint Contoso\spapppool`

`Setspn` コマンドでは、追加前にその SPN が検索されます。 SPN が既に存在する場合は、**"Duplicate SPN Value" (重複する SPN 値)** エラーが発生します。 既存の SPN を削除します。 `Setspn` オプションを指定して `-L` コマンドを実行して、SPN が正常に追加されたことを確認します。 コマンドの詳細については、「 [Setspn](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2012-R2-and-2012/cc731241%28v=ws.11%29)」を参照してください。

#### SharePoint アプリケーション プール アカウントに追加した SPN への委任について、コネクタが信頼されていることを確認します

KCD を構成して、Microsoft Entra アプリケーション プロキシ サービスがユーザー ID を SharePoint アプリケーション プール アカウントに委任できるようにします。 プライベート ネットワーク コネクタで、Microsoft Entra ID で認証されたユーザーの Kerberos チケットを取得できるようにすることで、KCD を構成します。 その後、そのサーバーは、コンテキストを対象アプリケーション (この場合は SharePoint) に渡します。

KCD を構成するには、コネクタ コンピューターごとに以下の手順を実行します。

1. ドメイン管理者としてドメイン コントローラーにサインインし、[Active Directory ユーザーとコンピューター] を開きます。
2. Microsoft Entra プライベート ネットワーク コネクタを実行しているコンピューターを見つけます。 この例では、SharePoint Server を実行しているコンピューターです。
3. そのコンピューターをダブルクリックし、 **[委任]** タブを選択します。
4. 委任オプションが **[指定されたサービスへの委任でのみこのコンピューターを信頼する]** に設定されていることを確認します。 次に、[ **任意の認証プロトコルを使用する**] を選択します。
5. **[追加]** ボタンを選択し、**[ユーザーまたはコンピューター]** を選択して、SharePoint アプリケーション プール アカウントを特定します。 たとえば、 `Contoso\spapppool`と指定します。
6. SPN の一覧で、前にサービス アカウント用に作成したサービス アカウントを選択します。
7. **[OK]** を選択してから、もう一度 **[OK]** を選択して変更を保存します。

    [Image: [指定したサービスへの委任にのみこのコンピューターを信頼する] と [任意の認証プロトコルを使用する] が選択されている [委任] タブのスクリーンショット。]

これで、外部 URL を使用して SharePoint にサインインし、Azure で認証を行うことができるようになりました。

### サインイン エラーのトラブルシューティング

サイトへのサインインが機能していない場合は、コネクタ ログで問題に関する詳細情報を取得できます。 コネクタを実行しているマシンからイベント ビューアーを開き、 **アプリケーションとサービス ログ**&gt;**Microsoft**&gt;**Microsoft Entra プライベート ネットワーク**&gt;**Connector** に移動し、 **管理者** ログを調べます。

### BadGateway Kerberos エラーのトラブルシューティング

**BadGateway Incorrect Kerberos** エラーが表示された場合は、管理者として、ドメイン コントローラーの Windows PowerShell で次のコマンドを実行します。`[servername]`と`[serviceaccount]`を実際の環境の値に置き換えます。

1. `$connector = Get-ADComputer -Identity "[servername]$"`
2. `Set-ADUser -Identity "[serviceaccount]" -PrincipalsAllowedToDelegateToAccount $connector`

[Image: Kerberos の制約付き委任構成が正しくないため、]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-integrate-with-sharepoint-server-saml"} -->
## Microsoft Entra アプリケーション プロキシを使用してオンプレミスの SharePoint ファームを発行する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-integrate-with-sharepoint-server-saml
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: オンプレミスの SharePoint Server への外部アクセスをセキュリティで保護するために、SAML ベースの認証を使用して Microsoft Entra アプリケーション プロキシを構成します。

### 概要

このステップ バイ ステップ ガイドでは、Microsoft Entra アプリケーション プロキシを使用して [Microsoft Entra 統合オンプレミス SharePoint (SAML)](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sharepoint-on-premises-tutorial) へのアクセスをセキュリティで保護する方法について説明します。ここでは、組織内のユーザー (Microsoft Entra ID、B2B) がインターネット経由で SharePoint に接続します。

注

Microsoft Entra アプリケーション プロキシを初めて使用する場合は、「 [Microsoft Entra アプリケーション プロキシを使用したオンプレミス アプリケーションへのリモート アクセス](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)」を参照してください。

このセットアップには、主に次の 3 つの利点があります。

- Microsoft Entra アプリケーション プロキシにより、認証されたトラフィックが内部ネットワークと SharePoint に確実に到達します。
- ユーザーは、VPN を使用せずに通常どおり SharePoint のサイトにアクセスできます。
- Microsoft Entra アプリケーション プロキシ レベルでユーザー割り当てによってアクセスを制御でき、条件付きアクセスや多要素認証 (MFA) などの Microsoft Entra 機能を使用してセキュリティを強化できます。

このプロセスには、2 つのエンタープライズ アプリケーションが必要です。 1 つは、SharePoint オンプレミス インスタンスで、これをギャラリーから管理対象 SaaS アプリのリストに発行します。 2 つ目は、最初のエンタープライズ ギャラリー アプリケーションを発行するために使用するオンプレミス アプリケーション (ギャラリー以外のアプリケーション) です。

### [前提条件]

- SharePoint 2013 以降のファーム。 SharePoint ファームは、[Microsoft Entra ID と統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sharepoint-on-premises-tutorial)されている必要があります。
- アプリケーション プロキシを含むプランを持つ Microsoft Entra テナント。 [Microsoft Entra IDプランと価格](https://www.microsoft.com/security/business/microsoft-entra-pricing)の詳細については、こちらを参照してください。
- オンプレミスの SharePoint ファームから Office ファイルを適切に起動するための Microsoft Office Web Apps Server ファーム。
- Microsoft Entra テナント内の[確認済みカスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)。 検証済みのドメインは、SharePoint の URL サフィックスと一致している必要があります。
- トランスポート層セキュリティ (TLS) 証明書が必要です。 詳細については、[カスタム ドメインでの発行](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)に関するページを参照してください。
- 企業ドメイン内のマシンにインストールされ、実行されているプライベート ネットワーク コネクタ。

一覧には、その他の前提条件が含まれています。

- オンプレミスの Active Directory ユーザーは Microsoft Entra Connect と同期する必要があり、 [Azure にサインイン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-user-signin)するように構成する必要があります。
- クラウド専用および B2B ゲスト ユーザーには、 [Microsoft Entra 管理センターのオンプレミスの SharePoint へのゲスト アカウントへのアクセス権を付与する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sharepoint-on-premises-tutorial#manage-guest-users-access)必要があります。

### 手順 1: オンプレミスの SharePoint と Microsoft Entra ID を統合する

1. SharePoint オンプレミス アプリケーションを構成します。 詳細については、[チュートリアル: Microsoft Entra シングル サインオンと SharePoint オンプレミスの統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sharepoint-on-premises-tutorial)に関する記事を参照してください。
2. 内部ネットワークからオンプレミスの SharePoint にアクセスし、内部でアクセス可能であることを確認します。

### 手順 2: アプリケーション プロキシを使用して SharePoint オンプレミス アプリケーションを発行する

この手順では、アプリケーション プロキシを使用するアプリケーションを Microsoft Entra テナントに作成します。 外部 URL を設定し、内部 URL を指定します。どちらも、後で SharePoint において使用します。

注

内部 URL と外部 URL は、手順 1 の SAML ベースのアプリケーション構成の**サインオン URL** と一致している必要があります。

[Image: サインオン URL の値。]

1. カスタム ドメインを使って、新しい Microsoft Entra アプリケーション プロキシ アプリケーションを作成します。 詳細な手順については、「[Microsoft Entra アプリケーション プロキシのカスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)」を参照してください。

    - 内部 URL: 'https://portal.contoso.com/ '
    - 外部 URL: 'https://portal.contoso.com/ '
    - 事前認証: Microsoft Entra ID
    - [Translate URLs in Headers]\(ヘッダーの URL を変換する\) : [いいえ]
    - [Translate URLs in Application Body]\(アプリケーション本文の URL を変換する\): [いいえ]

        [Image: アプリの作成に使用するオプション。]
2. オンプレミスの SharePoint ギャラリー アプリケーションに割り当てたのと [同じグループ](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sharepoint-on-premises-tutorial#grant-permissions-to-a-security-group) を割り当てます。
3. 最後に、 **[プロパティ]** セクションにアクセスし、 **[ユーザーに表示しますか?]** を **[いいえ]** に設定します。 このオプションを選択すると、最初のアプリケーションのアイコンのみがマイ アプリ ポータル (https://myapplications.microsoft.com) に表示されます。

    [Image: 「ユーザーに表示する」オプションを設定します。]

### 手順 3: アプリケーションをテストする

外部ネットワーク上のコンピューターからブラウザーを使用して、発行の手順で構成したリンクに移動します。 設定したテスト アカウントでサインインできることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-integrate-with-tableau"} -->
## Microsoft Entra アプリケーション プロキシと Tableau - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-integrate-with-tableau
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Microsoft Entra アプリケーション プロキシを使用して Tableau Server を発行し、事前認証と条件付きアクセスを使用してセキュリティで保護されたリモート アクセスを提供します。

### 概要

Microsoft と Tableau の連携により、アプリケーション プロキシを使用して Tableau デプロイに対してリモート アクセスを提供できるようになりました。

### 前提条件

- Tableau を設定してください。
- [プライベート ネットワーク コネクタ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)をインストールします。

### Tableau に対してアプリケーション プロキシを有効化する

アプリケーション プロキシは、Tableau が適切に動作するために必要な OAuth 2.0 の付与フローをサポートしています。 つまり、このアプリケーションを有効にするために必要な特別な手順はなくなりました。ただし、以下の発行手順に従って構成を行うことが必要です。

### Microsoft Entra でアプリケーションを発行する

Tableau を発行するには、Microsoft Entra 管理センターでアプリケーションを発行する必要があります。

- 手順 1 から 8 については、アプリケーション プロキシのチュートリアルで詳しく説明されています。 詳細については、「 [Microsoft Entra アプリケーション プロキシを使用してアプリケーションを発行する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)」を参照してください。
- アプリケーション プロキシ フィールドの Tableau 値を検索する方法については、Tableau のドキュメントを参照してください。

**アプリを発行するには**:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。
3. ページの上部にある **[新しいアプリケーション** ] を選択します。
4. **[オンプレミス アプリケーション] を選択します**。
5. 新しいアプリに関する情報を必須フィールドに入力します。
    - **内部 URL**: このアプリケーションには、Tableau URL 自体である内部 URL が必要です。 たとえば、「 `https://adventure-works.tableau.com` 」のように入力します。
    - **事前認証方法**: Microsoft Entra ID (推奨されますが必須ではありません)。
6. ページの上部にある [ **追加]** を選択します。 アプリケーションが追加されて、クイック スタート メニューが表示されます。
7. クイック スタート メニューで、[ **テスト用にユーザーを割り当てる**] を選択し、アプリケーションに少なくとも 1 人のユーザーを追加します。 このテスト アカウントでオンプレミスのアプリケーションにアクセスできることを確認します。
8. [ **割り当て]** を選択して、テスト ユーザーの割り当てを保存します。
9. (省略可能)アプリ管理ページで、[ **シングル サインオン**] を選択します。 ドロップダウン メニューから [ **統合 Windows 認証** ] を選択し、Tableau の構成に基づいて必須フィールドに入力します。 **[保存] を選択します**。

### テスト

これでアプリケーションをテストする準備ができました。 Tableau を発行するために使用した外部 URL にアクセスし、割り当てられているユーザーとして、両方のアプリケーションにサインインします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-integrate-with-teams"} -->
## Teams で Microsoft Entra アプリケーション プロキシ アプリにアクセスする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-integrate-with-teams
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Microsoft Entra アプリケーション プロキシを使用して、Microsoft Teams を通してオンプレミス アプリケーションにアクセスします。

### 概要

Microsoft Entra アプリケーション プロキシを使用すると、どこにいてもオンプレミス アプリケーションへのシングル サインオンが可能となります。 また、Microsoft Teams を使用すると共同作業が効率化します。 この 2 つを統合することで、あらゆる状況下でチームの生産性を向上させることができます。

ユーザーは[タブを使用](https://support.microsoft.com/office/use-a-tab-in-a-channel-or-chat-in-microsoft-teams-83d0514f-2134-4db5-80f2-e9b43e111d57)して、Teams のチャネルにクラウド アプリを追加することができますが、使用する SharePoint サイトや計画ツールがオンプレミスでホストされている場合は、 アプリケーション プロキシがソリューションです。 リモートからアプリにアクセスするために常に使用するのと同じ外部 URL を使用して、アプリケーション プロキシを介して公開されたアプリをチャネルに追加できます。 また、アプリケーション プロキシは Microsoft Entra ID を使用して認証されるため、ユーザーはシングル サインオン エクスペリエンスを利用できます。

### プライベート ネットワーク コネクタをインストールしてアプリを発行する

まだ行っていない場合は、 [テナントのアプリケーション プロキシを構成し、コネクタをインストールします](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)。 次に、リモート アクセス用にオンプレミス アプリケーションを公開します。 Teams にアプリを追加するために使用するため、外部 URL を書き留めます。

既にアプリケーションを公開していて、その外部 URL を覚えていない場合は、[Microsoft Entra 管理センター](https://entra.microsoft.com)で検索できます。 サインインした後、**Entra ID**&gt;の**エンタープライズ アプリ**&gt;に移動し、目的のアプリを選択して&gt;**アプリケーション プロキシ**を選択します。

### Teams へのアプリの追加

Teams チャネルに直接タブとして追加することをユーザーに知らせます。 このアプリは、チーム内のすべてのユーザーが使用できます。

1. アプリを追加する Teams チャネルに移動し、 **+** を選択してタブを追加します。
2. タブのオプションから **[Web サイト]** を選択します。
3. タブに名前を付け、URL をアプリケーション プロキシの外部 URL に設定します。

チームのいずれかのメンバーがタブを追加すると、そのタブはチャネル内の全ユーザーに表示されます。 アプリへのアクセス権を持つすべてのユーザーが、Microsoft Teams の資格情報を使ったシングル サインオンでアクセスできます。 アプリへのアクセス権を持たないすべてのユーザーは、Teams でそのタブを見ることができますが、オンプレミス アプリとアプリの Microsoft Entra 管理センター公開バージョンへのアクセス権が付与されるまで、ブロックされます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-integrate-with-traffic-manager"} -->
## アプリケーション プロキシに独自の Traffic Manager ソリューションを追加する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-integrate-with-traffic-manager
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Microsoft Entra アプリケーション プロキシと Azure Traffic Manager を組み合わせて、複数のコネクタ グループ間で地理的な負荷分散と高可用性を実現します。

### 概要

この記事では、Microsoft Entra アプリケーション プロキシと Azure Traffic Manager を構成する方法について説明します。

アプリケーション プロキシの geo ルーティング機能を使用すると、コネクタ グループが使用するアプリケーション プロキシのリージョンを最適化できます。 この機能は、選択した Traffic Manager ソリューションと組み合わせることができます。 この結合により、ユーザーの場所に基づいて完全に動的に地理情報を認識するソリューションが使用可能になります。 お好みの Traffic Manager ソリューションの豊富なルール セットのロックが解除され、アプリケーション プロキシを使用して保護するのに役立つアプリへのトラフィックのルーティング方法に優先順位が付けられます。 この組み合わせにより、ユーザーは 1 つの URL を使用して、最も近いアプリのインスタンスにアクセスできます。

[Image: Traffic Manager とアプリケーション プロキシの統合を示す図。]

### 前提条件

- トラフィック マネージャー ソリューション。
- 異なるリージョンに存在するアプリ。 geo ルーティングは、アプリと併置されるコネクタ グループごとに有効になります。
- アプリごとに使用するカスタム ドメイン。

### アプリケーション プロキシを構成する

トラフィック マネージャーを使用するには、アプリケーション プロキシを構成する必要があります。 次の構成手順では、次の URL 定義を参照してください。

- **リージョン URL**: 各アプリのアプリケーション プロキシ エンドポイント。 たとえば、`nam.contoso.com` と`india.contoso.com` です。
- **代替 URL**: Traffic Manager ソリューション用に構成された URL。 たとえば、`contoso.com` のようにします。

Traffic Manager のアプリケーション プロキシを構成するには:

1. アプリ インスタンスを含む場所ごとにコネクタをインストールします。 コネクタ グループごとに、geo ルーティング機能を使用して、コネクタをそれぞれのリージョンに割り当てます。
2. アプリケーション プロキシを使用してアプリ インスタンスを設定します。

    1. アプリごとに、カスタム ドメインをアップロードします。 アップロードされた証明書のサブジェクト代替名 (SAN) URL として使用するために、アプリ用の別の URL を含めます。
    2. 各アプリをそれぞれのコネクタ グループに割り当てます。
    3. ユーザー セッション全体で代替 URL を維持する場合は、各アプリを登録し、URL を応答 URL として追加します。 この手順は省略可能です。
3. Traffic Manager ソリューションで、各アプリ用に作成したアプリケーション プロキシのリージョン URL をエンドポイントとして追加します。
4. Traffic Manager ソリューションの負荷分散規則を標準ライセンスで構成します。
5. Traffic Manager ソリューションにわかりやすい URL を提供するには、代替 URL を Traffic Manager ソリューションのエンドポイントにポイントする `CNAME` レコードを作成します。
6. Microsoft Graph API を使用してアプリの代替 URL を構成し、`alternateUrl``onPremisesPublishing`の プロパティを更新します。 `alternateUrl` プロパティは、Microsoft Entra 管理センターでは使用できません。 Graph API を使用して構成する必要があります。 詳細については、「アプリケーションの [更新](https://learn.microsoft.com/ja-jp/graph/api/application-update)」を参照してください。

    次の例は、 `alternateUrl`設定の要求本文を示しています。

    ```http
    PATCH https://graph.microsoft.com/beta/applications/{id}
    Content-Type: application/json
    
    {
      "onPremisesPublishing": {
        "alternateUrl": "https://www.contoso.com"
      }
    }
    ```

    注

    `onPremisesPublishing` プロパティは、他のアプリケーション プロパティと同じ要求では更新できません。
7. 代替 URL をユーザー セッション全体で維持する場合は、同じ`useAlternateUrlForTranslationAndRedirect` オブジェクトに`true`するように `onPremisesPublishing` フラグを設定します。

    ```http
    PATCH https://graph.microsoft.com/beta/applications/{id}
    Content-Type: application/json
    
    {
      "onPremisesPublishing": {
        "alternateUrl": "https://www.contoso.com",
        "useAlternateUrlForTranslationAndRedirect": true
      }
    }
    ```

### アプリケーション プロキシ構成の例

次の表に、アプリケーション プロキシの構成例を示します。 この構成では、代替 URL としてサンプル アプリ ドメイン `www.contoso.com` が使用されます。

| - | North America (北米) ベースのアプリ | India (インド) ベースのアプリ | 追加情報 |
| --- | --- | --- | --- |
| **内部 URL** | `contoso.com` | `contoso.com` | アプリが異なるリージョンでホストされている場合、各アプリに対して同じ内部 URL を使用できます。 |
| **外部 URL** | `nam.contoso.com` | `india.contoso.com` | 各アプリのカスタム ドメインを構成します。 |
| **カスタム ドメイン証明書** | ドメイン ネーム システム (DNS): `nam.contoso.com` サン： `www.contoso.com` | Dns： `india.contoso.com` サン： `www.contoso.com` | アプリごとにアップロードする証明書で、SAN 値を代替 URL に設定します。 代替 URL は、すべてのユーザーがアプリへのアクセスに使用する URL です。 |
| **コネクタ グループ** | 北米地理グループ | India Geo Group (インド Geo グループ) | geo ルーティング機能を使用して、各アプリを正しいコネクタ グループに割り当てられるようにします。 |
| **リダイレクト** | (省略可能) 代替 URL のリダイレクトを維持するには、アプリのアプリケーション登録を追加します。 | (省略可能) 代替 URL のリダイレクトを維持するには、アプリのアプリケーション登録を追加します。 | この手順は、すべてのリダイレクトに対して代替 URL `www.contoso.com` が維持される場合に必要です。 |
| **応答 URL** | `www.contoso.com` | `www.contoso.com` |  |

### Traffic Manager の構成

Traffic Manager を構成するには、次の手順に従います。

1. 任意のルーティング規則を使用してトラフィック マネージャー プロファイルを作成します。
2. Traffic Manager で、北米エンドポイント ( `nam.contoso.com`) を追加します。
3. インドのエンドポイントを追加します: `india.contoso.com`。
4. アプリ プロキシ エンドポイントを追加します。
5. `CNAME``www.contoso.com` レコードを追加して Traffic Manager ソリューションの URL を指します。 たとえば、 `contoso.trafficmanager.net`を使用します。 代替 URL が Traffic Manager ソリューションを指すようになりました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-network-topology"} -->
## Microsoft Entra アプリケーション プロキシのネットワーク トポロジに関する注意事項 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-network-topology
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Microsoft Entra アプリケーション プロキシのトラフィック フローとコネクタの配置を最適化します。 ExpressRoute や複数リージョンのデプロイを含む帯域幅、待機時間、ネットワーク パターンについて説明します。

### 概要

Microsoft Entra アプリケーション プロキシを使うときのトラフィック フローとネットワーク トポロジに関する考慮事項を最適化する方法について説明します。

### トラフィック フロー

アプリケーションが Microsoft Entra アプリケーション プロキシを通じて発行される場合、ユーザーからアプリケーションへのトラフィックはすべて、次の 3 つの接続を経由して送信されます。

1. ユーザーは、Azure 上の Microsoft Entra アプリケーション プロキシ サービスのパブリック エンドポイントに接続する
2. プライベート ネットワーク コネクタがアプリケーション プロキシ サービスに接続する (アウトバウンド)
3. プライベート ネットワーク コネクタがターゲット アプリケーションに接続する

[Image: ユーザーから対象アプリケーションへのトラフィック フローを示す図。]

### 最も近いアプリケーション プロキシ クラウド サービスを使用するようにコネクタ グループを最適化する

Microsoft Entra テナントにサインアップすると、テナントのリージョンは、選んだリージョンに設定されます。 **既定の**アプリケーション プロキシ クラウド サービス インスタンスは、Microsoft Entra テナントと同じ、または最も近いリージョンを使います。

たとえば、Microsoft Entra テナントのリージョンが英国の場合、 **既定** では、ヨーロッパのデータ センターでサービス インスタンスを使用するようにすべてのプライベート ネットワーク コネクタが割り当てられます。 ユーザーが公開アプリケーションにアクセスする場合、トラフィックはこの場所のアプリケーション プロキシ クラウド サービス インスタンスを経由して送信されます。

既定のリージョンとは異なるリージョンにコネクタがインストールされている場合、これらのアプリケーションにアクセスするパフォーマンスを向上させるために、コネクタ グループが最適化されているリージョンを変更すると有益な場合があります。 コネクタ グループにリージョンを指定すると、指定されたリージョンのアプリケーション プロキシ クラウド サービスに接続します。

トラフィック フローを最適化し、コネクタ グループへの待機時間を短縮するには、コネクタ グループを最も近いリージョンに割り当てます。 リージョンを割り当てるには、次の操作を行います。

重要

この機能を使用するには、バージョン 1.5.1975.0 以降のコネクタが使用されている必要があります。

1. [アプリケーション管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)にサインインします。
2. 右上隅で自分のユーザー名を選択します。 アプリケーション プロキシを使うディレクトリにサインインしていることを確認します。 ディレクトリを変更する必要がある場合は、**[ディレクトリの切り替え]** を選び、アプリケーション プロキシを使うディレクトリを選びます。
3. **Entra ID**&gt;**エンタープライズアプリ**&gt;**アプリケーションプロキシ**に移動します。
4. **[新しいコネクタ グループ]** を選び、コネクタ グループの **[名前]** を指定します。
5. **[詳細設定]** で、[特定のリージョン用に最適化] のドロップダウンを選び、コネクタに最も近いリージョンを選んで **[保存]** を選びます。

    [Image: 新しいコネクタ グループを構成する。]
6. コネクタ グループに割り当てるコネクタを選びます。

    コネクタ グループにコネクタを移動できるのは、既定のリージョンを使用するコネクタ グループにそれが属している場合のみです。 **デフォルトの**コネクタ グループ内のコネクタから始めます。 次に、それを適切なコネクタ グループに移動します。

    コネクタ グループのリージョンを変更できるのは、コネクタ グループに割り当てられているコネクタおよびアプリが**ない**場合のみです。
7. コネクタ グループをアプリケーションに割り当てます。 トラフィックは、最適化されたコネクタ グループのリージョン内のアプリケーション プロキシ クラウド サービスに送信されます。

### 待機時間を削減するための考慮事項

プロキシ ソリューションでは、ネットワーク接続に必ず待ち時間が発生します。 リモート アクセス ソリューションとして選択したプロキシまたは VPN ソリューションに関係なく、ソリューションには、企業ネットワーク内部に接続できるようにする一連のサーバーが必ず含まれます。

多くの組織では、サーバーのエンドポイントが境界ネットワーク内に含まれています。 しかし、Microsoft Entra アプリケーション プロキシでは、コネクタが企業ネットワーク上に配置されていてもトラフィックはクラウド内のプロキシ サービスを経由して送信されます。 このため、境界ネットワークは必要ありません。

以下のセクションで、待機時間をさらに削減するための推奨事項を示します。

#### コネクタの配置

アプリケーション プロキシでは、テナントの場所に基づいてインスタンスの場所が自動的に選択されます。 ただし、コネクタのインストール場所は自分で決定できます。これにより、ネットワーク トラフィックの待機時間特性を定義できます。

アプリケーション プロキシ サービスを設定する場合は、次の質問について考えてください。

- アプリケーションの場所はどこか。
- アプリケーションにアクセスする大半のユーザーの場所はどこか。
- アプリケーション プロキシ インスタンスの場所はどこか。
- Azure ExpressRoute や同様の VPN など、Microsoft データ センターへの専用ネットワーク接続が既に設定されていますか?

コネクタは、Microsoft Entra ID とアプリケーションの両方と通信する必要があります。 手順 2 と 3 は、トラフィック フロー図の通信を表しています。 コネクタの配置は、これら 2 つの接続の待機時間に影響します。 コネクタの配置を評価する際は、以下の点に留意してください。

- コネクタと Kerberos 制約付き委任 (KCD) のデータ センターの間の "見通し線" を確認します。 さらに、コネクタ サーバーはドメインに参加している必要があります。
- コネクタはアプリケーションのできるだけ近くにインストールします。

#### 待機時間を最小限に抑えるための一般的なアプローチ

各ネットワーク接続を最適化することで、エンドツーエンド トラフィックの待機時間を最小限に抑えます。

- ホップの両端間の距離を短くします。
- 経由する適切なネットワークを選びます。 たとえば、パブリック インターネットではなく、プライベート ネットワークを経由した方が、専用リンクのため高速である可能性があります。

Microsoft と自社ネットワークの間に専用 VPN または ExpressRoute リンクを使うことを検討してください。

### 最適化戦略に焦点を合わせる

ユーザーとアプリケーション プロキシ サービス間の接続を制御するために実行できることはほとんどありません。 ユーザーは、ホーム ネットワーク、コーヒー ショップ、または別のリージョンからアプリにアクセスします。 代わりに、アプリケーション プロキシ サービスからアプリへのプライベート ネットワーク コネクタへの接続を最適化できます。 環境内に次のパターンを組み込むことを検討してください。

#### パターン 1: アプリケーションの近くへのコネクタの配置

顧客のネットワーク内の対象アプリケーションの近くにコネクタを配置します。 この構成では、コネクタとアプリケーションが閉じているため、トポロジ図の手順 3 が最小限に抑えられます。

コネクタがドメイン コントローラーを見通す必要がある場合は、このパターンが得策です。 ほとんどの場合、このパターンは適切に機能するため、ほとんどのお客様はこのパターンを使用します。 このパターンを パターン 2 と組み合わせて、サービスとコネクタの間のトラフィックを最適化することもできます。

#### パターン 2: Microsoft ピアリングを使用した ExpressRoute の活用

Microsoft ピアリングを使用して ExpressRoute が設定されている場合、アプリケーション プロキシとコネクタ間のトラフィック用により高速な ExpressRoute 接続を利用できます。 この場合でも、コネクタはネットワーク上のアプリケーションに近い場所に配置します。

#### パターン 3: プライベート ピアリングを使用した ExpressRoute の活用

Azure と企業ネットワークとの間に、プライベート ピアリングを使用して専用の VPN または ExpressRoute を設定済みの場合、別の選択肢があります。 この構成では、通常、Azure の仮想ネットワークは企業ネットワークの拡張機能と見なされます。 そのため、Azure データセンターにコネクタをインストールする一方で、コネクタからアプリへの接続について厳しい待ち時間の要件を満たすことができます。

専用接続を介して送信されるため、待機時間は損なわれません。 また、コネクタが Microsoft Entra テナントの場所に近い Azure データセンターでインストールされるため、アプリケーション プロキシ サービスからコネクタへの接続待ち時間も改善されます。

[Image: Azure データ センター内にインストールされたコネクタを示す図]

#### その他のアプローチ

この記事ではコネクタの配置を中心に取り上げていますが、アプリケーションの配置を変更して待ち時間特性を改善することもできます。

ホスト環境にネットワークを移動する組織が増えています。 この移動により、組織は、企業ネットワークの一部でもあり、ドメイン内のままであるホスト環境にアプリを配置できます。 この場合、 パターン 1、 パターン 2、パターン 3 を新しいアプリケーションの場所に適用できます。 このオプションを検討している場合は、「[Microsoft Entra Domain Services](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/overview)」を参照してください。

さらに、別々の場所やネットワーク内にある対象アプリケーションに接続するコネクタを、[コネクタ グループ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connector-groups)を使用して整理することを検討してください。

### 一般的なユースケースの図

このセクションでは、いくつかの一般的なシナリオについて説明します。 Microsoft Entra テナント (およびそれに伴うプロキシ サービス エンドポイント) が米国 (US) 内にあると仮定します。 これらのユース ケースで説明されている考慮事項は、世界各地の他のリージョンにも当てはまります。

これらのシナリオでは、各接続を "ホップ" と呼び、簡単に説明できるように番号を付けます。

- **ホップ 1**: ユーザーからアプリケーション プロキシ サービスへ
- **ホップ 2**: アプリケーション プロキシ サービスからプライベート ネットワーク コネクタへ
- **ホップ 3**: プライベート ネットワーク コネクタからターゲット アプリケーションへ

#### ユースケース 1

**シナリオ:** アプリは米国内の組織のネットワークにあり、ユーザーは同じリージョン内に存在している。 Azure データセンターと企業ネットワークの間に ExpressRoute または VPN は存在しません。

**推薦：** 前のセクションで説明した パターン 1 に従います。 待ち時間を改善するには、必要に応じて ExpressRoute の使用を検討してください。

アプリの近くにコネクタを配置することによって、ホップ 3 を最適化します。 通常、KCD 操作を実行するために、アプリとデータセンターが直接見通せる位置にコネクタが設置されます。

[Image: ユーザー、プロキシ、コネクタ、アプリケーションがすべて米国内に存在することを示す図。]

#### ユースケース 2

**シナリオ:** アプリは米国内の組織のネットワークにあり、ユーザーは世界中に分散している。 Azure データセンターと企業ネットワークの間に ExpressRoute または VPN は存在しません。

**推薦：** 前のセクションで説明した パターン 1 に従います。

繰り返しますが、一般的なパターンはホップ 3 の最適化です。これにより、コネクタはアプリの近くに配置されます。 ホップ 3 は、すべて同じリージョン内にある場合、通常、コストは高くありません。 ただし、世界各地のユーザーが米国内のアプリケーション プロキシ インスタンスにアクセスするため、ユーザーの場所によってはホップ 1 のコストが高くなる可能性があります。 どのプロキシ ソリューションも、グローバルに分散しているユーザーに関して類似した特性を持つことは注目に値します。

[Image: ユーザーは世界中に分散していますが、それ以外はすべて米国に存在します]

#### ユースケース 3

**シナリオ:** アプリは米国内の組織のネットワークにある。 Microsoft ピアリングを使用した ExpressRoute が、Azure と企業ネットワークの間に存在します。

**推薦：** 前のセクションで説明した パターン 1 と パターン 2 に従います。

最初に、コネクタを可能な限りアプリケーションの近くに配置します。 これで、ホップ 2 には ExpressRoute が自動的に使用されます。

ExpressRoute リンクで Microsoft ピアリングを使用している場合、プロキシとコネクタの間のトラフィックはそのリンク経由で送信されます。 ホップ 2 には最適な待機時間が使われます。

[Image: プロキシとコネクタ間の ExpressRoute を示す図]

#### ユース ケース 4

**シナリオ:** アプリは米国内の組織のネットワークにある。 プライベート ピアリングを使用した ExpressRoute が、Azure と企業ネットワークの間に存在します。

**推薦：** 前のセクションで説明した パターン 3 に従います。

ExpressRoute プライベート ピアリング経由で企業ネットワークに接続されている Azure データセンターにコネクタを配置します。

コネクタは、Azure データセンターに配置できます。 コネクタは引き続きプライベート ネットワークを介してアプリケーションとデータセンターに接続できるため、ホップ 3 は最適化されたままになります。 加えて、ホップ 2 はさらに最適化されます。

[Image: Azure データセンターのコネクタ、コネクタとアプリの間の ExpressRoute]

#### ユース ケース 5

**シナリオ:** このアプリはヨーロッパの組織のネットワークにあり、既定のテナント リージョンは米国で、ほとんどのユーザーはヨーロッパにいます。

**推奨事項:** コネクタをアプリケーションの近くに配置します。 ヨーロッパのアプリケーション プロキシ サービス インスタンスを使うようにコネクタ グループを更新します。 手順については、「[最も近いアプリケーション プロキシ クラウド サービスを使用するようにコネクタ グループを最適化する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-network-topology#optimize-connector-groups-to-use-closest-application-proxy-cloud-service)」を参照してください。

ヨーロッパのユーザーは、同じリージョンのアプリケーション プロキシ インスタンスにアクセスしているため、ホップ 1 のコストは高くありません。 ホップ 3 は最適化されています。 ExpressRoute を使用して ホップ 2 を最適化することを検討してください。

#### ユース ケース 6

**シナリオ:** このアプリはヨーロッパの組織のネットワークにあり、既定のテナント リージョンは米国で、ほとんどのユーザーは米国にいます。

**推奨事項:** コネクタをアプリケーションの近くに配置します。 ヨーロッパのアプリケーション プロキシ サービス インスタンスを使うようにコネクタ グループを更新します。 手順については、「[最も近いアプリケーション プロキシ クラウド サービスを使用するようにコネクタ グループを最適化する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-network-topology#optimize-connector-groups-to-use-closest-application-proxy-cloud-service)」を参照してください。 米国のすべてのユーザーがヨーロッパのアプリケーション プロキシ インスタンスにアクセスする必要があるため、ホップ 1 はコストが高くなる可能性があります。

このような状況では、もう 1 つのバリエーションを使用することも検討できます。 組織内のほとんどのユーザーが米国内に存在する場合、ネットワークが米国まで拡張される可能性があります。 コネクタを米国内に配置し、コネクタ グループについては既定の米国リージョンをそのまま使用し、ヨーロッパ内のアプリケーションには専用の社内ネットワーク回線を使用します。 これにより、ホップ 2 と 3 が最適化されます。

[Image: ユーザー、プロキシ、コネクタは米国内に、アプリケーションはヨーロッパ内に存在することを示す図。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-page-links-broken-problem"} -->
## アプリケーション内の壊れたリンク - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-page-links-broken-problem
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: Microsoft Entra ID と統合されているアプリケーション プロキシ アプリでのリンクの破損に関する問題のトラブルシューティングを行います。

### 概要

この記事では、Microsoft Entra アプリケーション プロキシ アプリケーションと解決オプションで壊れたリンクが発生する理由について説明します。

アプリケーション プロキシ アプリを発行した後、既定では、アプリで動作するリンクは、発行されたルート URL にある宛先へのリンクのみです。

アプリ内のリンクが機能しない場合、リンクが発行されたルート URL の外部にある宛先に移動する可能性があります。

*アプリのリンクが壊れた原因は何ですか?* アプリ ユーザーがアプリケーション内のリンクを選択すると、アプリケーション プロキシは、URL を同じアプリケーション内の内部 URL または外部で使用可能な URL として解決しようとします。 リンクが同じアプリケーションにない内部 URL を指している場合、リンクはこれらのバケットのいずれにも収まりません。 結果は "見つかりません" というエラーになります。

### 壊れたリンクを解決する

この問題を解決するには、3 つのオプションがあります。 これらの選択肢は、複雑さを増す一覧に示されています。

1. 内部 URL が、アプリケーションに関連するすべてのリンクを含むルートであることを確認します。 ルートによって、すべてのリンクが同じアプリケーション内で公開されたコンテンツとして処理されます。

    内部 URL を変更しても、ユーザーのランディング ページを変更しない場合は、ホーム ページの URL を以前に公開された内部 URL に変更します。 **Microsoft Entra ID**&gt;**App Registrations** に移動し、アプリケーションの**ブランドを**選択します。 [ブランド化] セクションで、[ **ホーム ページの URL] を** 元の発行済みランディング ページ URL に設定します。

    重要

    この変更を行うには、ユーザーが Microsoft Entra ID のアプリケーション オブジェクトを変更するアクセス許可を持っている必要があります。 ユーザーには [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-app-roles#assign-built-in-application-administrator-roles) ロールが割り当てられている必要があります。
2. アプリケーションで完全修飾ドメイン名 (FQDN) を使用している場合は、[カスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)を使用してアプリケーションを発行します。 カスタム ドメイン機能を使用する場合は、内部と外部の両方で同じ URL を使用できます。

    このオプションを使用すると、内部 URL へのアプリケーション リンクも外部で認識されるため、アプリケーション内のリンクにアプリケーション プロキシを介して外部からアクセスできるようになります。 すべてのリンクが引き続き、発行済みのアプリケーションに属している必要があります。 ただし、このオプションでは、各リンクが同じアプリケーションに属している必要はなく、複数のアプリケーションに属することができます。
3. どちらのオプションも実現できない場合は、インライン リンク変換を設定するための複数のオプションがあります。 これらのオプションには、Intune Managed Browser、マイ アプリ拡張機能、またはアプリケーションのリンク変換設定の使用が含まれます。

    これらの各オプションとその有効化方法の詳細については、「 [Microsoft Entra アプリケーション プロキシで公開されたアプリのハードコードされたリンクをリダイレクト](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-hard-coded-link-translation)する」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-ping-access-publishing-guide"} -->
## Microsoft Entra アプリケーション プロキシ用の PingAccess を使用したヘッダーベースの認証 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-ping-access-publishing-guide
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: PingAccess と Microsoft Entra アプリケーション プロキシを使用したヘッダーベース認証をサポートします。

### 概要

Microsoft は PingAccess と協力して、より多くのアクセス アプリケーションを提供しています。 PingAccess では、統合された[ヘッダーベースのシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-single-sign-on-with-headers)を超える別のオプションが提供されます。

### PingAccess for Microsoft Entra ID とは

PingAccess for Microsoft Entra ID を使用すると、認証にヘッダーを使用するアプリケーションへのアクセスとシングル サイン オン (SSO) をユーザーが利用できるようになります。 アプリケーション プロキシはこれらのアプリを他のアプリと同様に扱い、Microsoft Entra ID を使ってアクセスを認証した後、コネクタ サービスを通してトラフィックを渡します。 PingAccess はアプリケーションの前に位置し、アクセス トークンを Microsoft Entra ID からヘッダーに変換します。 その後、アプリケーションは、読み取り可能な形式で認証を受信します。

会社のアプリケーションを使用するためにサインインするユーザーからは、違いは認識されません。 アプリケーションは従来どおり、どこからでも任意のデバイスでも使用できます。 プライベート ネットワーク コネクタは、認証の種類に関係なく、すべてのアプリにリモート トラフィックを誘導します。そのため負荷分散も引き続き自動的に行われます。

### 利用方法

PingAccess と Microsoft Entra ID のライセンスが必要です。 ただし、Microsoft Entra ID P1 または P2 サブスクリプションには、最大 20 のアプリケーションをカバーする PingAccess 基本ライセンスが含まれています。 20 を超えるヘッダーベース アプリケーションを発行する必要がある場合は、PingAccess から追加のライセンスを購入できます。

詳細については、[Microsoft Entra のエディション](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing)に関する記事をご覧ください。

### Microsoft Entra でアプリケーションを発行する

この記事では、アプリケーションを初めて発行するための手順について説明します。 この記事では、アプリケーション プロキシと PingAccess の両方に関するガイダンスを提供します。

注

手順の一部は、Ping ID サイトに存在します。

#### プライベート ネットワーク コネクタをインストールする

プライベート ネットワーク コネクタは、遠隔にいる従業員からのトラフィックを発行済みアプリケーションに誘導する Windows Server サービスです。 インストール手順の詳細については、「[チュートリアル: Microsoft Entra ID のアプリケーション プロキシを使用してリモート アクセスするためのオンプレミス アプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)」をご覧ください。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。このとき、[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)以上のアクセス許可が必要です。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**アプリケーションプロキシ**に移動します。
3. **[コネクタ サービスのダウンロード]** を選択します。
4. インストール手順に従います。

コネクタをダウンロードすると、ディレクトリに対してアプリケーション プロキシが自動的に有効になります。有効にならなかった場合は、**[アプリケーション プロキシの有効化]** を選択することができます。

#### アプリケーション プロキシを使用して Microsoft Entra ID にアプリケーションを追加する

Microsoft Entra ID にアプリケーションを追加するには、2 つの手順があります。 最初に、アプリケーション プロキシを使用してアプリケーションを発行する必要があります。 次に、アプリケーションに関する情報を収集する必要があります。この情報は PingAccess の手順で必要になります。

##### アプリケーションの発行

最初に、アプリケーションを発行します。 このアクションには、次が含まれます。

- Microsoft Entra ID へのオンプレミス アプリケーションの追加。
- アプリケーションをテストするためのユーザーの割り当てと、ヘッダーベースのシングル サインオンの選択。
- アプリケーションのリダイレクト URL の設定。
- ユーザーおよび他のアプリケーションに対する、オンプレミス アプリケーションを使用するためのアクセス許可の付与。

独自のオンプレミス アプリケーションを発行するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にアプリケーション管理者としてサインインします。
2. **[エンタープライズ アプリケーション]**&gt;**[新しいアプリケーション]**&gt;**[オンプレミスのアプリケーションを追加する]** に移動します。 **[独自のオンプレミスのアプリケーションの追加]** ページが表示されます。

    [Image: 名前、内部 URL、および外部 URL 設定のフィールドを含むアプリケーション構成フォーム。]
3. 新しいアプリケーションに関する情報を必須フィールドに入力します。 設定にはガイダンスを使用します。

    注

    この手順の詳細な手順については、「[Microsoft Entra ID にオンプレミス アプリを追加する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)」をご覧ください。

    1. **内部 URL** : 通常は、社内ネットワークでのアプリのサインイン ページへの URL を指定します。 このシナリオでは、コネクタは PingAccess プロキシをアプリケーションの最初のページとして処理する必要があります。 次の形式を使用します。`https://<host name of your PingAccess server>:<port>` ポートは既定では 3000 ですが、PingAccess で設定できます。

        警告

        この種類のシングル サインオンの場合、内部 URL には `https` を使用する必要があり、`http` は使用できません。 また、アプリケーション プロキシで区別できるように、2 つのアプリケーションで同じ内部 URL を指定しないでください。
    2. **事前認証方法**: **Microsoft Entra ID** を選択します。
    3. **ヘッダーの URL を変換する**: **[いいえ]** を選択します。

    注

    最初のアプリケーションでは、PingAccess 構成を変更した場合、ポート 3000 を使用して開始し、戻ってこの設定を更新します。 後続のアプリケーションの場合、ポートは PingAccess で構成されたリスナーと一致する必要があります。
4. **追加**を選択します。 新しいアプリケーションの概要ページが表示されます。

次に、アプリケーション テストのユーザーを割り当て、ヘッダーベースのシングル サインオンを選択します。

1. アプリケーションのサイドバーから、**[ユーザーとグループ]**&gt;**[ユーザーの追加]**&gt;**[ユーザーとグループ] (選択された&lt;数&gt;)** の順に選択します。 選択するためのユーザーとグループの一覧が表示されます。

    [Image: アプリケーションのユーザーとグループの割り当てページ。]
2. アプリケーション テストのユーザーを選択し、 **[選択]** を選択します。 テスト アカウントがオンプレミス アプリケーションにアクセスできることを確認します。
3. **割り当て**を選択します。
4. アプリケーションのサイドバーから、**[シングル サインオン]**&gt;**[ヘッダーベース]** を選択します。

    ヒント

    ヘッダーベースのシングル サインオンを初めて使用するときに PingAccess をインストールします。 Microsoft Entra サブスクリプションが PingAccess のインストールに自動的に関連付けられていることを確認するには、シングル サインオン ページのリンクを使用して PingAccess をダウンロードします。 今すぐダウンロード サイトを開くことも、後でこのページに戻ることもできます。

    [Image: PingAccess ダウンロード リンクを含むヘッダー ベースのサインオン構成ページ。]
5. **保存**を選択します。

続いてリダイレクト URL が外部 URL に設定されてことを確認しています。

1. **Entra ID**&gt;**App 登録**に移動し、アプリケーションを選択します。
2. **[リダイレクト URI]** の横にあるリンクを選択します。 このリンクには、Web クライアントとパブリック クライアントのリダイレクト Uniform Resource Identifier (URI) セットアップの量が表示されます。 ** &lt;[アプリケーション名&gt; - 認証]** ページが表示されます。
3. 以前にアプリケーションに割り当てた外部 URL が **[リダイレクト URI]** の一覧にあるかどうかを確認します。 ない場合は、**Web** のリダイレクト URI の種類を使用して外部 URL を追加し、 **[保存]** を選択します。

外部 URL に加えて、外部 URL 上の Microsoft Entra ID の承認エンドポイントを [リダイレクト URI] の一覧に追加する必要があります。

`https://*.msappproxy.net/pa/oidc/cb``https://*.msappproxy.net/`

最後に、ユーザーに `read` アクセス権を、他のアプリケーションに `read/write` アクセス権を付与するように、オンプレミス アプリケーションを設定します。

1. アプリケーションの **[アプリの登録]** サイドバーから、**[API のアクセス許可]**&gt;**[アクセス許可の追加]**&gt;**[Microsoft API]**&gt;**[Microsoft Graph]** の順に選択します。 Microsoft Graph へのアクセス許可を含む、**Microsoft Graph** 向け **[API アクセス許可の要求]** ページが表示されます。

    [Image: Microsoft Graph API の選択に対する [API アクセス許可の要求] ページ。]
2. **[委任されたアクセス許可]**&gt;**[ユーザー]**&gt;**[User.Read]** の順に選択します。
3. **[アプリケーションのアクセス許可]**&gt;**[アプリケーション]**&gt;**[Application.ReadWrite.All]** の順に選択します。
4. **[アクセス許可の追加]** を選択します.
5. **[API のアクセス許可]** ページで、**[&lt;ディレクトリ名&gt; に管理者の同意を与えます]** を選択します。

##### PingAccess の手順で使う情報の収集

3 つのグローバル一意識別子 (GUID) を収集します。 これらの GUID を使用して、PingAccess を使用してアプリケーションを設定します。

| Microsoft Entra フィールドの名前 | PingAccess フィールドの名前 | データ形式 |
| --- | --- | --- |
| **アプリケーション (クライアント) ID** | **クライアント ID** | GUID |
| **ディレクトリ (テナント) ID** | **発行者** | GUID |
| `PingAccess key` | **クライアント シークレット** | ランダムな文字列 |

この情報を収集します。

1. **Entra ID**&gt;**App 登録**に移動し、アプリケーションを選択します。
2. **[アプリケーション (クライアント) ID]** 値の横で **[クリップボードにコピー]** アイコンを選択し、それをコピーして保存します。 この値は、後から PingAccess のクライアント ID として指定します。
3. **[ディレクトリ (テナント) ID]** 値の横で **[クリップボードにコピー]** も選択し、それをコピーして保存します。 この値は、後から PingAccess の発行者として指定します。
4. アプリケーションの **[アプリの登録]** のサイドバーから、**[証明書とシークレット]**&gt;**[新しいクライアント シークレット]** の順に選択します。 **[クライアント シークレットの追加]** ページが表示されます。

    [Image: 有効期限の設定を含むクライアント シークレット作成フォーム。]
5. **[説明]** で `PingAccess key` を入力します。
6. **[有効期限]** の下で、PingAccess キーを設定する方法を選択します: **[1 年]** 、 **[2 年]** 、または **[無期限]** 。
7. **追加**を選択します。 クライアント シークレットのテーブルに PingAccess キーが、**VALUE** フィールドに自動入力されるランダム文字列で表示されます。
8. PingAccess キーの **VALUE** フィールドの横で **[クリップボードにコピー]** アイコンを選択し、それをコピーして保存します。 この値は、後から PingAccess のクライアント シークレットとして指定します。

**`acceptMappedClaims` フィールドを更新します。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。このとき、[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)以上のアクセス許可が必要です。
2. 右上隅で自分のユーザー名を選択します。 アプリケーション プロキシを使用するディレクトリにサインインしていることを確認します。 ディレクトリを変更する必要がある場合は、**[ディレクトリの切り替え]** を選択し、アプリケーション プロキシを使用するディレクトリを選択します。
3. **Entra ID**&gt;**App 登録**に移動し、アプリケーションを選択します。
4. アプリケーションの **[アプリの登録]** ページのサイドバーから、 **[マニフェスト]** を選択します。 アプリケーションの登録のマニフェスト JSON コードが表示されます。
5. `acceptMappedClaims` フィールドを検索し、値を `True` に変更します。
6. **保存**を選択します。

#### 省略可能な要求の使用 (省略可能)

省略可能な要求を使用すると、すべてのユーザーとテナントが持つ、標準だが既定では含まれない要求を追加できます。 アプリケーション マニフェストを変更して、アプリケーションの省略可能な要求を構成することができます。 詳細については、[Microsoft Entra アプリケーション マニフェストの概要に関する記事](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-app-manifest)をご覧ください。

PingAccess が消費する access\_token に、メール アドレスを含める例:

```json
    "optionalClaims": {
        "idToken": [],
        "accessToken": [
            {
                "name": "email",
                "source": null,
                "essential": false,
                "additionalProperties": []
            }
        ],
        "saml2Token": []
    },
```

#### 要求のマッピング ポリシーの使用 (省略可能)

要求マッピングを使用すると、Active Directory フェデレーション サービス (ADFS) またはユーザー オブジェクトをサポートするカスタム要求をさらに追加することで、古いオンプレミス アプリをクラウドに移行できます。 詳細については、[要求のカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-claims-mapping-policy-type#claims-customization-using-a-policy)に関する記事をご覧ください。

カスタム要求を使用して、アプリケーションにさらにフィールドを含めるには、 [カスタム要求マッピング ポリシーの作成とアプリケーションへの割り当て](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)に関する記事をご覧ください。

注

カスタム要求を使用するには、カスタム ポリシーも定義し、アプリケーションに割り当てる必要があります。 このポリシーには、すべての必要なカスタム属性を含めます。

PowerShell または Microsoft Graph から、ポリシーの定義と割り当てを行うことができます。 PowerShell で実行する場合は、最初に `New-AzureADPolicy` を使用してから、 `Add-AzureADServicePrincipalPolicy`を使用してアプリケーションに割り当てる必要があります。 詳細については、「[要求のマッピング ポリシーの割り当て](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)」を参照してください。

注

Azure AD および MSOnline PowerShell モジュールは、2024 年 3 月 30 日の時点で非推奨となります。 詳細については、[非推奨に関する更新情報](https://techcommunity.microsoft.com/t5/microsoft-entra-blog/important-azure-ad-graph-retirement-and-powershell-module/ba-p/3848270)を参照してください。 この日以降、これらのモジュールのサポートは、Microsoft Graph PowerShell SDK への移行支援とセキュリティ修正プログラムに限定されます。 非推奨のモジュールは、2025 年 3 月 30 日まで引き続き機能します。

Microsoft Entra ID (旧称 Azure AD) と対話するには、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview) に移行することをお勧めします。 移行に関する一般的な質問については、移行に関する [FAQ](https://learn.microsoft.com/ja-jp/powershell/azure/active-directory/migration-faq) を参照してください。 *注:* MSOnline のバージョン 1.0.x では、2024 年 6 月 30 日以降に中断が発生する可能性があります。

例:

```powershell
$pol = New-AzureADPolicy -Definition @('{"ClaimsMappingPolicy":{"Version":1,"IncludeBasicClaimSet":"true", "ClaimsSchema": [{"Source":"user","ID":"employeeid","JwtClaimType":"employeeid"}]}}') -DisplayName "AdditionalClaims" -Type "ClaimsMappingPolicy"

Add-AzureADServicePrincipalPolicy -Id "<<The object Id of the Enterprise Application you published in the previous step, which requires this claim>>" -RefObjectId $pol.Id
```

#### PingAccess でカスタム要求を使用できるようにする

PingAccess でカスタム要求を使用できるようにすることは省略可能ですが、アプリケーションでさらに要求を使用すると予想される場合は必須です。

次の手順で PingAccess を構成すると、作成する Web セッション ([設定] -&gt; [アクセス] -&gt; [Web セッション]) では、**[Request Profile](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/プロファイルの要求)** を選択解除し、**[Refresh User Attributes](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/ユーザー属性の更新)** を **[いいえ]** に設定する必要があります。

### PingAccess をダウンロードしてアプリケーションを構成する

このシナリオの PingAccess に関連した領域の詳しい手順については、Ping Identity のドキュメントを参照してください。

Microsoft Entra ID OpenID Connect (OIDC) 接続を作成するには、Microsoft Entra 管理センターからコピーした**ディレクトリ (テナント) ID** 値でトークン プロバイダーを設定します。 PingAccess で Web セッションを作成します。 `Application (client) ID` 値と `PingAccess key` 値を使用します。 ID のマッピングを設定し、仮想ホスト、サイト、アプリケーションを作成します。

#### アプリケーションのテスト

これでアプリケーションは稼働中になります。 正しく動作するか確認するために、ブラウザーを起動し、Microsoft Entra でアプリケーションを発行したときに作成した外部 URL にアクセスします。 サインインには、アプリケーションに割り当てたテスト アカウントを使用します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-powershell-samples"} -->
## Microsoft Entra アプリケーション プロキシの PowerShell サンプル - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-powershell-samples
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: Microsoft Entra アプリケーション プロキシ用の PowerShell サンプルを使用して、ディレクトリ内のアプリケーション プロキシ アプリとコネクタに関する情報を取得し、ユーザーとグループをアプリに割り当て、証明書情報を取得します。

### 概要

次の表に、Microsoft Entra アプリケーション プロキシの PowerShell スクリプトの例へのリンクを示します。 これらのサンプルには、特に明記されていない限り、 [Microsoft Graph Beta PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) 2.10 以降が必要です。

これらのサンプルで使用されるコマンドレットの詳細については、 [アプリケーション プロキシ アプリケーションの管理](https://learn.microsoft.com/ja-jp/powershell/module/azuread/#application_proxy_application_management) と [プライベート ネットワーク コネクタの管理](https://learn.microsoft.com/ja-jp/powershell/module/azuread/#application_proxy_connector_management)に関するセクションを参照してください。

| リンク | 説明 |
| --- | --- |
| **アプリケーション プロキシ アプリ** |  |
| [すべてのアプリケーション プロキシ アプリの基本情報を一覧表示する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-all-app-proxy-apps-basic) | ディレクトリ内のすべてのアプリケーション プロキシ アプリに関する基本情報 (AppId、DisplayName、ObjId) を一覧表示します。 |
| [すべてのアプリケーション プロキシ アプリの拡張情報を一覧表示する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-all-app-proxy-apps-extended) | ディレクトリ内のすべてのアプリケーション プロキシ アプリに関する拡張情報 (AppId、DisplayName、ExternalUrl、InternalUrl、ExternalAuthenticationType) を一覧表示します。 |
| [コネクタ グループ別にすべてのアプリケーション プロキシ アプリを一覧表示する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-all-app-proxy-apps-by-connector-group) | ディレクトリ内のすべてのアプリケーション プロキシ アプリと、アプリが割り当てられているコネクタ グループに関する情報を一覧表示します。 |
| [トークンの有効期間ポリシーを使用してすべてのアプリケーション プロキシ アプリを取得する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-all-app-proxy-apps-with-policy) | トークンの有効期間ポリシーとその詳細を使用して、ディレクトリ内のすべてのアプリケーション プロキシ アプリを一覧表示します。 |
| **コネクタ グループ** |  |
| [ディレクトリ内のすべてのコネクタ グループとコネクタを取得する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-all-connectors) | ディレクトリ内のすべてのコネクタ グループとコネクタを一覧表示します。 |
| [コネクタ グループに割り当てられているすべてのアプリを別のコネクタ グループに移動する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-move-all-apps-to-connector-group) | コネクタ グループに現在割り当てられているすべてのアプリケーションを別のコネクタ グループに移動します。 |
| **割り当てられたユーザーとグループ** |  |
| [アプリケーション プロキシ アプリケーションに割り当てられているユーザーとグループを表示する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-display-users-group-of-app) | 特定のアプリケーション プロキシ アプリケーションに割り当てられているユーザーとグループを一覧表示します。 |
| [アプリケーションにユーザーを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-assign-user-to-app) | 特定のユーザーをアプリケーションに割り当てます。 |
| [アプリケーションにグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-assign-group-to-app) | 特定のグループをアプリケーションに割り当てます。 |
| **外部 URL の構成** |  |
| [既定のドメインを使用してすべてのアプリケーション プロキシ アプリを取得する (.msappproxy.net)](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-all-default-domain-apps) | 既定のドメイン (.msappproxy.net) を使用するすべてのアプリケーション プロキシ アプリケーションを一覧表示します。 |
| [ワイルドカード発行を使用してすべてのアプリケーション プロキシ アプリを取得する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-all-wildcard-apps) | ワイルドカード発行を使用して、すべてのアプリケーション プロキシ アプリを一覧表示します。 |
| **カスタム ドメインの構成** |  |
| [カスタム ドメインと証明書情報を使用して、すべてのアプリケーション プロキシ アプリを取得する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-all-custom-domains-and-certs) | カスタム ドメインを使用しているすべてのアプリケーション プロキシ アプリと、カスタム ドメインに関連付けられている証明書情報を一覧表示します。 |
| [証明書がアップロードされていない状態で公開されたすべての Microsoft Entra ID プロキシ アプリケーション アプリを取得する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-all-custom-domain-no-cert) | カスタム ドメインを使用しているが、有効な TLS/SSL 証明書がアップロードされていないすべてのアプリケーション プロキシ アプリを一覧表示します。 |
| [同一の証明書を使用して公開されたすべての Microsoft Entra ID プロキシ アプリケーション アプリを取得する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-custom-domain-identical-cert) | 同じ証明書で発行されたすべての Microsoft Entra ID プロキシ アプリケーション アプリを一覧表示します。 |
| [同じ証明書で公開されているすべての Microsoft Entra ID プロキシ アプリケーション アプリを取得し、置き換えます](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-custom-domain-replace-cert) | 同じ証明書で発行された Microsoft Entra ID プロキシ アプリケーション アプリの場合、証明書を一括で置き換えることができます。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-qlik"} -->
## Microsoft Entra アプリケーション プロキシと Qlik Sense - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-qlik
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Microsoft Entra アプリケーション プロキシと Qlik Sense を統合します。

### 概要

Microsoft と Qlik Sense は協力して、Microsoft Entra アプリケーション プロキシを使用したリモート アクセスを提供しました。

### 前提条件

- [Qlik Sense](https://community.qlik.com/docs/DOC-19822) を設定します。
- [プライベート ネットワーク コネクタをインストールします](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)。

### Microsoft Entra でアプリケーションを発行する

Qlik Sense を発行するには、2 つのアプリケーションを Azure に発行します。

#### アプリケーション 1: Qlik Sense Hub

Microsoft Entra でアプリケーションを発行します。 手順 1 から 8 のより詳細なチュートリアルについては、「 [Microsoft Entra アプリケーション プロキシを使用してアプリケーションを発行する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)」を参照してください。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。
3. ページの上部にある **[新しいアプリケーション** ] を選択します。
4. **[オンプレミス アプリケーション] を選択します**。
5. 新しいアプリに関する情報を必須フィールドに入力します。
    - **内部 URL**: このアプリケーションには、Qlik Sense URL 自体である内部 URL が必要です。 たとえば、「 `https//demo.qlikemm.com:4244` 」のように入力します。
    - **事前認証方法**: Microsoft Entra ID (推奨されますが必須ではありません)。
6. ページの下部にある [ **追加]** を選択します。 アプリケーションが追加されて、クイック スタート メニューが表示されます。
7. クイック スタート メニューで、[ **テスト用にユーザーを割り当てる**] を選択し、アプリケーションに少なくとも 1 人のユーザーを追加します。 このテスト アカウントがオンプレミス アプリケーションにアクセスできることを確認します。
8. [ **割り当て]** を選択して、テスト ユーザーの割り当てを保存します。
9. (オプション) アプリの管理ページで [シングル サインオン] を選択します。 ドロップダウン メニューから **[Kerberos の制約付き委任** ] を選択し、Qlik Sense の構成に基づいて必須フィールドに入力します。 **[保存] を選択します**。

#### アプリケーション 2: Qlik Sense 仮想プロキシ

次の例外を除き、アプリケーション 1 の場合と同じ手順に従います。

**手順 5** (必須フィールド): 内部 URL は、アプリケーションで使用される認証ポートを含む Qlik Sense URL である必要があります。 既定値は HTTPS の **場合は 4244** 、Qlik Sense リリースの **場合は 2018 年 4** 月より前の HTTP の場合は 4248 です。 2018 年 4 月以降の Qlik Sense リリースの既定値は、HTTPS の **場合は 443** 、HTTP の **場合は 80** です。 たとえば、「 `https//demo.qlik.com:4244` 」のように入力します。

**手順 8** (シングル サインオン): シングル サインオンを設定しないでください。 **シングル サインオン** オプションは無効のままにします。

### テスト

これでアプリケーションをテストする準備ができました。 アプリケーション 1 で Qlik Sense を発行するために使用した外部 URL にアクセスし、割り当てられているユーザーとして、両方のアプリケーションにサインインします。

### 関連情報

アプリケーション プロキシを使用した Qlik Sense の公開の詳細については、次の Qlik コミュニティ記事を参照してください。

- [Kerberos の制約付き委任と Qlik Sense を使用した統合 Windows 認証での Microsoft Entra ID](https://community.qlik.com/docs/DOC-20183)
- [Qlik Sense と Microsoft Entra アプリケーション プロキシの統合](https://community.qlik.com/t5/Technology-Partners-Ecosystem/Azure-AD-Application-Proxy/ta-p/1528396)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-remove-personal-data"} -->
## 個人データの削除 - Microsoft Entra アプリケーション プロキシ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-remove-personal-data
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Microsoft Entra アプリケーション プロキシのデバイスにインストールされているコネクタから個人データを削除します。

### 概要

Microsoft Entra アプリケーション プロキシでは、デバイスにコネクタをインストールする必要があります。つまり、デバイスに個人データが存在する可能性があります。 この記事では、個人データを削除してプライバシーを向上させる方法について説明します。

### 個人データの場所

アプリケーション プロキシは、次の種類のログに個人データを書き込む可能性があります。

- コネクタ イベント ログ
- Windows イベント ログ

### Windows イベント ログから個人データを削除する

Windows イベント ログのデータ保持を構成する方法については、Windows イベント ログ設定のドキュメントを参照してください。 Windows イベント ログの詳細については、「 [Windows イベント ログの使用](https://learn.microsoft.com/ja-jp/windows/win32/wes/using-windows-event-log)」を参照してください。

注

個人データの表示または削除の詳細については、GDPR サイトに [対する Windows データ主体の要求](https://learn.microsoft.com/ja-jp/microsoft-365/compliance/gdpr-dsr-windows) に関する Microsoft のガイダンスを確認してください。 GDPR に関する一般情報については、[Microsoft Trust Center の GDPR に関するセクション](https://www.microsoft.com/trust-center/privacy/gdpr-overview)および [Service Trust Portal の GDPR に関するセクション](https://servicetrust.microsoft.com/ViewPage/GDPRGetStarted)をご覧ください。

### コネクタ イベント ログから個人データを削除する

アプリケーション プロキシ ログに個人データが含まれていないことを確認するには、次のいずれかを実行します。

- 必要に応じてデータを削除または表示する
- ログ記録をオフにする

以下のセクションを参照して、コネクタ イベント ログから個人データを削除します。 コネクタがインストールされているすべてのデバイスで、削除プロセスを実行する必要があります。

注

この記事は、デバイスまたはサービスから個人データを削除する手順について説明しており、GDPR の下で義務を果たすために使用できます。 GDPR に関する一般情報については、[Microsoft Trust Center の GDPR に関するセクション](https://www.microsoft.com/trust-center/privacy/gdpr-overview)および [Service Trust Portal の GDPR に関するセクション](https://servicetrust.microsoft.com/ViewPage/GDPRGetStarted)をご覧ください。

#### 特定のデータを表示またはエクスポートする

特定のデータを表示またはエクスポートするには、各コネクタ イベント ログで関連エントリを検索します。 ログは、`C:\ProgramData\Microsoft\Microsoft AAD private network connector\Trace` にあります。

ログはテキスト ファイルであるため、[findstr](https://learn.microsoft.com/ja-jp/windows-server/administration/windows-commands/findstr) を使用してユーザーに関連するテキスト エントリを検索できます。

個人データを見つけるには、ログ ファイルで ユーザー ID を検索します。

Kerberos の制約付き委任を使用するアプリケーションによってログに記録された個人データを見つけるには、ユーザー名の種類の次の構成要素を検索します。

- オンプレミスのユーザー プリンシパル名
- ユーザー プリンシパル名のユーザー名部分
- オンプレミスユーザーのユーザープリンシパル名に含まれるユーザー名
- オンプレミスのセキュリティ アカウント マネージャー (SAM) のアカウント名

#### 特定のデータを削除する

特定のデータを削除するには、次の手順に従います。

1. 新しいログ ファイルを生成します。 Microsoft Entra プライベート ネットワーク コネクタ サービスを再起動します。 新しいログ ファイルを生成すると、古いログ ファイルを削除または変更できるようになります。
2. 前述の特定のデータを表示またはエクスポートするプロセスに従って、削除する必要がある情報を見つけます。 すべてのコネクタ ログで検索します。
3. 関連するログ ファイルを削除するか、個人データが含まれたフィールドを選択して削除します。 不要になった場合は、古いログ ファイルをすべて削除することもできます。

#### コネクタ ログをオフにする

コネクタ ログに個人データが含まれていないことを確認する 1 つのオプションは、ログの生成をオフにすることです。 コネクタ ログの生成を停止するには、`C:\Program Files\Microsoft Entra private network connector\MicrosoftEntraPrivateNetworkConnectorService.exe.config` から、次の強調表示されている行を削除します。

[Image: 削除するコードが強調表示されたコード スニペットを示すスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-secure-api-access"} -->
## Microsoft Entra アプリケーション プロキシを使用してオンプレミスのアプリケーション プログラミング インターフェイス (API) にアクセスする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-secure-api-access
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Microsoft Entra アプリケーション プロキシを使用すると、プライベート クラウドまたはオンプレミスでホストされたアプリケーション プログラミング インターフェイス (API) への安全なアクセスを提供できます。

### 概要

ビジネス ロジックがプライベート アプリケーション プログラミング インターフェイス (API) 内に存在することがよくあります。 この API は、オンプレミスまたはプライベート クラウドで稼働します。 Android、iOS、Mac、または Windows のネイティブ アプリは、データを使用したりユーザー対話を提供したりするために、この API エンドポイントと対話する必要があります。 Microsoft Entra アプリケーション プロキシと [Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries) を使用すると、ネイティブ アプリがプライベート クラウド API に安全にアクセスできます。 Microsoft Entra アプリケーション プロキシは、ファイアウォール ポートを開いてアプリ層で認証と承認を制御するよりも高速かつ安全なソリューションです。

ヒント

"オンプレミス" という用語は、物理サーバーが会社のオフィスのオンプレミスに置かれていた時代にさかのぼった従来の用語です。 現在、多くのセルフホステッド ワークロードは、データセンター内の仮想マシンで実行されています。 オンプレミスとプライベート クラウドの用語は同じ意味で使用されます。

この記事では、ネイティブ アプリがアクセスできる Web API サービスをホストするための Microsoft Entra アプリケーション プロキシ ソリューションを設定する手順を説明します。

次の図は、オンプレミス API を公開するための従来の方法を示したものです。 このアプローチでは、受信ポート 80 と 443 を開く必要があります。

[Image: インターネットに対して開かれた受信ポート 80 と 443 を備えた従来の API 発行アーキテクチャを示す図。]

次の図は、Microsoft Entra アプリケーション プロキシを使用して、受信ポートを開かずに API を安全に公開する方法を示しています:

[Image: 受信ポートを必要とせず、セキュリティで保護された API 発行用の Microsoft Entra アプリケーション プロキシ アーキテクチャを示す図。]

Microsoft Entra アプリケーション プロキシはこのソリューションのバックボーンを形成し、API アクセスのパブリック エンドポイントとして機能し、認証と承認を提供します。 [Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries) ライブラリを使用することで、さまざまなプラットフォームから API にアクセスできます。

Microsoft Entra アプリケーション プロキシの認証と承認は Microsoft Entra ID に基づいて構築されているため、Microsoft Entra 条件付きアクセスを使用して、信頼されたデバイスのみがアプリケーション プロキシを介して発行された API にアクセスできるようにします。 デスクトップには Microsoft Entra の参加または Microsoft Entra ハイブリッド参加を使用し、デバイスには Intune Managed を使用します。 また、Microsoft Entra 多要素認証などの Microsoft Entra ID P1 または P2 機能と、[Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) の機械学習に基づくセキュリティを利用することもできます。

### 前提条件

このチュートリアルを実行するには、次のものが必要です。

- アプリを作成して登録できるアカウントを持つ、Azure ディレクトリへの管理者アクセス権
- [Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) のサンプル Web API とネイティブ クライアント アプリ

### アプリケーション プロキシ経由で API を公開する

アプリケーション プロキシを介してイントラネットの外部で API を公開するには、Web アプリを公開する場合と同じパターンに従ってください。 詳細については、「[チュートリアル: Microsoft Entra ID のアプリケーション プロキシを使用してリモート アクセスするためのオンプレミス アプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)」を参照してください。

アプリケーション プロキシ経由で SecretAPI Web API を公開するには、次の手順を実施します。

1. ローカル コンピューターまたはイントラネット上で、サンプルの SecretAPI プロジェクトを ASP.NET Web アプリとしてビルドして公開します。 Web アプリにローカルでアクセスできることを確認します。
2. [アプリケーション管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)にサインインします。
3. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
4. **[エンタープライズ アプリケーション - すべてのアプリケーション]** ページの上部で、 **[新しいアプリケーション]** を選択します。
5. **[Microsoft Entra ギャラリーの参照]** ページで、**[オンプレミスのアプリケーション]** セクションを探し、**[オンプレミスのアプリケーションの追加]** を選択します。 **[独自のオンプレミスのアプリケーションの追加]** ページが表示されます。
6. プライベート ネットワーク コネクタをインストールしていない場合は、インストールするように求められます。 **[プライベート ネットワーク コネクタのダウンロード]** を選択して、コネクタをダウンロードしてインストールします。
7. **[独自のオンプレミスのアプリケーションの追加]** ページで情報を追加します。

    1. **[名前]** の横に *SecretAPI* と入力します。
    2. **[内部 URL]** の横に、イントラネット内から API へのアクセスに使用する URL を入力します。
    3. **[事前認証]** が **[Microsoft Entra ID**] に設定されていることを確認します。
    4. **[作成]** を選択して、アプリが作成されるまで待ちます。

    [Image: SecretAPI アプリのエンタープライズ アプリケーション作成ダイアログ。]
8. **[エンタープライズ アプリケーション - すべてのアプリケーション]** ページで、 **[SecretAPI]** アプリを選択します。
9. **[SecretAPI - 概要]** ページで、左側のナビゲーションから **[プロパティ]** を選択します。
10. エンド ユーザーが **[MyApps]** パネルで API を利用できないようにするために、 **[プロパティ]** ページの下部にある **[Visible to users](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/ユーザーに表示する)** を **[いいえ]** に設定し、 **[保存]** を選択します。

    [Image: Microsoft Entra 管理センターのプロパティ ページのスクリーンショットで、「ユーザーに表示」トグルが表示されています。]

これで、Web API が Microsoft Entra アプリケーション プロキシ経由で公開されました。 次に、アプリにアクセスできるユーザーを追加します。

1. **[SecretAPI - 概要]** ページで、左側のナビゲーションから **[ユーザーとグループ]** を選択します。
2. [ **ユーザーとグループ** ] ページで、[ **ユーザー/グループの追加]** を選択します。
3. **[割り当ての追加]** ページで **[ユーザーとグループ]** を選択します。
4. **[ユーザーとグループ]** ページで、少なくとも自分自身を含め、アプリにアクセスできるユーザーを検索して選択します。 すべてのユーザーを選択した後、 **[選択]** を選択します。

    [Image: 割り当てられたユーザーを示す Microsoft Entra 管理センターの [ユーザーとグループ] ページのスクリーンショット。]
5. [ **割り当ての追加** ] ページで、[ **割り当て**] を選択します。

注意

統合 Windows 認証を使用する API には [、Kerberos の制約付き委任構成が](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-sso-with-kcd)必要な場合があります。

### ネイティブ アプリを登録して API へのアクセス権を付与する

ネイティブ アプリは、特定のプラットフォームまたはデバイスで使用するために開発されたプログラムです。 ネイティブ アプリが API に接続してアクセスできるようにするには、Microsoft Entra ID に登録する必要があります。 次の手順では、ネイティブ アプリを登録し、アプリケーション プロキシ経由で公開した Web API へのアクセス権を付与する方法を示します。

AppProxyNativeAppSample ネイティブ アプリを登録するには、次のようにします。

1. [アプリケーション管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)にサインインします。
2. **Entra ID**&gt;**アプリ登録**に移動します。
3. **[新規登録]** を選択します。
4. **[アプリケーションの登録]** ページで情報を入力します。

    1. **[名前]** に *AppProxyNativeAppSample* と入力します。
    2. **[サポートされているアカウントの種類]** で、**[この組織ディレクトリのみに含まれるアカウント (Contoso のみ - シングル テナント)]** を選択します。
    3. [ **リダイレクト URL**] の下のドロップダウンで、[ **パブリック クライアント/ネイティブ (モバイルとデスクトップ)]**を選択し、「 *`https://login.microsoftonline.com/common/oauth2/nativeclient`*」と入力します。
    4. **[登録]** を選択し、アプリが正常に登録されるまで待ちます。

        [Image: AppProxyNativeAppSample 構成を示すアプリ登録フォーム。]

これで、`AppProxyNativeAppSample` アプリが Microsoft Entra ID に登録されました。 ネイティブ アプリに `SecretAPI` Web API へのアクセス権を付与します。

1. **[アプリの登録**] ページで、**AppProxyNativeAppSample** アプリを選択します。
2. **[AppProxyNativeAppSample]** ページで、左側のナビゲーションから **[API のアクセス許可]** を選択します。
3. **[API のアクセス許可]** ページで、 **[アクセス許可の追加]** を選択します。
4. 最初の **[API アクセス許可の要求]** ページで、 **[所属する組織で使用している API]** タブを選択し、 **[SecretAPI]** を検索して選択します。
5. 次の **[API アクセス許可の要求]** ページで、 **[user\_impersonation]** の横のチェック ボックスをオンにし、 **[アクセス許可の追加]** を選択します。
6. **[API のアクセス許可]** ページに戻り、 **[Contoso に管理者の同意を与えます]** を選択して、他のユーザーがアプリに個別に同意しなくても済むようにします。

### ネイティブ アプリ コードを構成する

最後の手順では、ネイティブ アプリを構成します。 コードは、`Form1.cs` サンプル アプリの `NativeClient` ファイルに追加する必要があります。 このコードは [MSAL ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries) を使用してトークンを取得します。 トークンは API 呼び出しを要求し、要求のヘッダーにアタッチします。 トークンはベアラー証明書としてアタッチされます。 MSAL の詳細については、「[MSAL をプロジェクトに追加する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-windows-desktop#add-msal-to-your-project)」と [MSAL への参照の追加](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-windows-desktop#add-the-code-to-initialize-msal)に関する記事を参照してください。

1. *Form1.cs* で、名前空間 `using Microsoft.Identity.Client;` をコードに追加します。
2. Microsoft Authentication Library (MSAL) の認証コンテキストでネイティブ アプリケーション コードを編集して、この[カスタマイズされたコード サンプル](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-native-client-application#step-4-add-the-microsoft-authentication-library-to-your-code-net-c-sample)を含めます。

ネイティブ アプリを構成して、Microsoft Entra ID に接続し、アプリケーション プロキシを使用して API を呼び出します。 次に、`App.config` の `NativeClient sample app` ファイル内のプレースホルダー値を Microsoft Entra ID の値で更新します。

1. **[ディレクトリ (テナント) ID]** を `<add key="ida:Tenant" value="" />` フィールドに貼り付けます。 この値 (GUID) は、いずれかのアプリの **[概要]** ページから検索してコピーすることができます。
2. AppProxyNativeAppSample の **[アプリケーション (クライアント) ID]** を `<add key="ida:ClientId" value="" />` フィールドに貼り付けます。 この値 (GUID) は AppProxyNativeAppSample の **[概要]** ページから検索してコピーできます。左ナビゲーションの **[管理]** の下にあります。
3. AppProxyNativeAppSample の **[リダイレクト URI]** を `<add key="ida:RedirectUri" value="" />` フィールドに貼り付けます。 この値 (URI) は AppProxyNativeAppSample の **[認証]** ページから検索してコピーできます。左ナビゲーションの **[管理]** の下にあります。 MSAL はメソッド `PublicClientApplicationBuilder.WithDefaultRedirectUri()` を使用して推奨される応答の Uniform Resource Identifier (URI) を挿入するため、この手順は省略可能です。
4. SecretAPI の **[アプリケーション ID の URI]** を `<add key="todo:TodoListResourceId" value="" />` フィールドに貼り付けます。 値は `todo:TodoListBaseAddress` と同じです。 URI 値は **API の公開** ページに `SecretAPI` アプリケーションで見つけられます。 左側のナビゲーションの **[管理]** を見てください。
5. SecretAPI の **[ホーム ページ URL]** を `<add key="todo:TodoListBaseAddress" value="" />` フィールドに貼り付けます。 この値 (URL) は左のナビゲーションの **[管理]** の下にある、SecretAPI の **[ブランド化とプロパティ]** ページから検索してコピーできます。

注意

ソリューションでエラー `invalid Resx file`がビルドおよび報告されない場合は、ソリューション エクスプローラーで **[プロパティ**] を展開し、[ *Resources.resx*] を右クリックして、[コードの **表示**] を選択します。 コメント行 121 から 123。

パラメーターを構成した後は、ネイティブ アプリをビルドして実行します。 **[サインイン]** ボタンを選択すると、アプリでサインインが行われ、SecretAPI に正常に接続されたことを確認する成功画面が表示されます。

[Image: [OK] ボタンでシークレット API が成功したことを示す成功メッセージ。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-security"} -->
## Microsoft Entra アプリケーション プロキシのセキュリティに関する注意事項 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-security
- Service: entra-id / app-proxy
- Article date: 2026-03-10
- Summary: Microsoft Entra アプリケーション プロキシを使用するためのセキュリティに関する考慮事項とアーキテクチャについて説明します。

### 概要

この記事では、Microsoft Entra アプリケーション プロキシを使用する際にユーザーとアプリケーションを安全に保つために動作するコンポーネントについて説明します。

次の図は、Microsoft Entra ID によってオンプレミスのアプリケーションへの安全なリモート アクセスが実現されるしくみを示しています。

[Image: Microsoft Entra アプリケーション プロキシ経由のセキュリティで保護されたリモート アクセスの図]

### セキュリティ上の利点

Microsoft Entra アプリケーション プロキシには、多くのセキュリティ上の利点があります。 利点は次のとおりです。

- 認証済みのアクセス
- 条件付きアクセス
- トラフィックの終了
- すべてのアウトバウンドアクセス
- クラウド規模の分析と機械学習
- サービスとしてのリモート アクセス
- Microsoft による分散型サービス拒否 (DDoS) からの保護サービス

#### 認証済みのアクセス

Microsoft Entra 事前認証を使用する場合は、認証済みの接続のみがネットワークにアクセスできます。

Microsoft Entra アプリケーション プロキシは、すべての認証に Microsoft Entra セキュリティ トークン サービス (STS) に依存します。 認証された ID のみがバックエンド アプリケーションにアクセスできるため、事前認証によって多数の匿名攻撃がブロックされます。

事前認証方法としてパススルーを選択した場合、この利点は得られません。

#### 条件付きアクセス

ネットワークへの接続が確立される前に、多数のポリシー制御を適用できます。

[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps)を使用すると、ユーザーがアプリケーションにアクセスするときの制限を定義できます。 場所、認証の強度、およびユーザーのリスク プロファイルに基づいてサインインを制限するポリシーを作成できます。

条件付きアクセスを使用して多要素認証ポリシーを構成し、ユーザー認証に別のセキュリティレイヤーを追加することもできます。 アプリケーションは、Microsoft Entra 条件付きアクセスを介して Microsoft Defender for Cloud Apps にルーティングして、 [アクセス](https://learn.microsoft.com/ja-jp/defender-cloud-apps/access-policy-aad) ポリシーと [セッション](https://learn.microsoft.com/ja-jp/defender-cloud-apps/session-policy-aad) ポリシーを通じてリアルタイムの監視と制御を提供することもできます。

#### トラフィックの終了

すべてのトラフィックがクラウドで終了します。

Microsoft Entra アプリケーション プロキシはリバース プロキシであるため、バックエンド アプリケーションへのトラフィックはすべて、このサービスで終了します。 サービスはバックエンド サーバーとのセッションのみを再確立します。つまり、バックエンド サーバーは直接 HTTP トラフィックに公開されません。 この構成は、標的型攻撃からより適切に保護されることを意味します。

#### すべてのアクセスが外向き

企業ネットワークへの受信接続を開く必要がありません。

プライベート ネットワーク コネクタは、Microsoft Entra アプリケーション プロキシ サービスへの送信接続のみを使用します。 受信接続用のファイアウォール ポートを開く必要はありません。 従来のプロキシでは、境界ネットワーク ( *非武装地帯 (DMZ)* または *スクリーン サブネット*とも呼ばれます) が必要であり、ネットワーク エッジで認証されていない接続を許可します。 アプリケーション プロキシでは、すべての接続が送信用で、セキュリティで保護されたチャネルで発生するため、境界ネットワークは不要です。

コネクタのメンテナンスについて詳しくは、「[Microsoft Entra プライベート ネットワーク コネクタについて](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-conceptual-connectors)」を参照してください。

#### クラウド規模の分析と機械学習

最先端のセキュリティ保護を得ることができます。

アプリケーション プロキシは Microsoft Entra ID の一部であるため、[Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) に加え、Microsoft Security Response Center と Digital Crimes Unit のデータを活用できます。 侵害されたアカウントを事前に特定し、リスクの高いサインインからの保護を提供します。多くの要因によって、どのサインイン試行が高リスクであるかを決定します。 これらの要因には、感染したデバイス、ネットワークの匿名化、非定型またはありそうもない場所が含まれます。

これらのレポートとイベントの多くは、お客様のセキュリティ情報およびイベント管理 (SIEM) システムとの統合を可能にする API を通じて既に使用可能です。

#### サービスとしてのリモート アクセス

オンプレミス サーバーの保守やパッチの適用について心配する必要がありません。

パッチの適用されていないソフトウェアは、依然として多数の攻撃の的となっています。 Microsoft Entra アプリケーション プロキシは Microsoft によるインターネット規模のサービスであるため、常に最新のセキュリティ パッチとアップグレードを取得できます。

Microsoft Entra アプリケーション プロキシによって公開されるアプリケーションのセキュリティを向上させるために、アプリケーション プロキシは Web クローラー ロボットによるアプリケーションのインデックス作成とアーカイブをブロックします。 Web クローラー ロボットが公開されたアプリのロボット設定を取得しようとするたびに、アプリケーション プロキシは `User-agent: * Disallow: /` が含まれる robots.txt ファイルで応答します。

##### Microsoft による分散型サービス拒否 (DDoS) からの保護サービス

アプリケーション プロキシ経由で公開されたアプリケーションは、分散型サービス拒否 (DDoS) 攻撃から保護されます。 Microsoft はすべてのデータセンターでこの保護を自動的に有効化します。 Microsoft による DDoS からの保護サービスによって、トラフィックを常時監視し、一般的なネットワーク レベル攻撃をリアルタイムで軽減できます。

### 内部構造

Microsoft Entra アプリケーション プロキシは、以下の 2 つで構成されています:

- クラウドベースのサービス: このサービスは Microsoft クラウドで実行され、外部クライアントとユーザーが接続する場所です。
- [オンプレミス コネクタ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-conceptual-connectors): オンプレミス コンポーネントであるコネクタは、Microsoft Entra アプリケーション プロキシ サービスからの要求をリッスンし、内部アプリケーションへの接続を処理します。

コネクタとアプリケーション プロキシ サービスの間でフローが確立されるのは以下の場合です。

- コネクタが最初に設定されます。
- コネクタはアプリケーション プロキシ サービスから構成情報を取得します。
- 公開されたアプリケーションにユーザーがアクセスする。

注意

すべての通信はトランスポート層セキュリティ (TLS) 経由で行われ、常にコネクタからアプリケーション プロキシ サービスに送信されます。 このサービスは送信専用です。

コネクタはクライアント証明書を使用して、ほぼすべての呼び出しに対してアプリケーション プロキシ サービスを認証します。 このプロセスの唯一の例外は、クライアント証明書が確立される最初のセットアップ手順です。

#### コネクタのインストール

コネクタの初回セットアップ時に、次のフロー イベントが発生します。

1. コネクタは、インストール中にサービスに登録します。 インストーラーは、Microsoft Entra 管理者の資格情報を入力するようにユーザーに求めます。 この認証から取得されたトークンが、Microsoft Entra アプリケーション プロキシ サービスに提示されます。
2. アプリケーション プロキシ サービスがトークンを評価します。 ユーザーが少なくともテナントのアプリケーション管理者であるかどうかを確認します。 ユーザーがそうでない場合、プロセスは終了します。
3. コネクタはクライアント証明書要求を生成し、トークンと共にアプリケーション プロキシ サービスに渡します。 次に、サービスがトークンを検証し、クライアント証明書要求に署名します。
4. コネクタは、アプリケーション プロキシ サービスとのその後の通信の際にこのクライアント証明書を使用します。
5. コネクタは、クライアント証明書を使用してサービスからシステム構成データの初期プルを実行し、要求を受け入れる準備ができています。

#### 構成の設定の更新

アプリケーション プロキシ サービスが構成の設定を更新するたびに、次のフロー イベントが発生します。

1. コネクタはクライアント証明書を使用して、アプリケーション プロキシ サービス内の構成のエンドポイントに接続します。
2. クライアント証明書が検証されます。
3. アプリケーション プロキシ サービスはコネクタに構成データ (そのコネクタが含まれている必要があるコネクタ グループなど) を返します。
4. 現在の証明書が生成されてから 180 日以上経っている場合は、コネクタが新しい証明書要求を生成します。

#### 発行済みアプリケーションへのアクセス

発行されたアプリケーションにユーザーがアクセスすると、アプリケーション プロキシ サービスとプライベート ネットワーク コネクタとの間で次のイベントが発生します。

1. サービスは、アプリのユーザーを認証します。
2. サービスは、コネクタ キューに要求を配置します。
3. コネクタはキューからの要求を処理します。
4. コネクタは応答を待機します。
5. サービスは、ユーザーにデータをストリーミングします。

これらの各手順の詳細について、この後説明します。

##### 1.サービスは、アプリケーションのユーザーを認証します。

アプリケーションで事前認証方法としてパススルーを使用する場合は、このセクションの手順をスキップします。

Microsoft Entra ID は、アプリケーションが Microsoft Entra ID で事前認証するように構成されている場合に、ユーザーを STS にリダイレクトして認証します。 次の手順が発生します。

1. アプリケーション プロキシが、条件付きアクセス ポリシーの要件を確認します。 この手順により、ユーザーがアプリケーションに割り当てられます。 2 段階検証が必要な場合は、認証シーケンスでユーザーに第 2 認証方式が要求されます。
2. Microsoft Entra STS はアプリケーションに署名済みトークンを発行し、ユーザーをアプリケーション プロキシ サービスにリダイレクトします。
3. アプリケーション プロキシは、トークンが正しいアプリケーションに発行され、署名済みで、有効であることを確認します。
4. アプリケーション プロキシは暗号化された認証 Cookie を設定して、アプリケーションへの認証が成功したことを示します。 Cookie には、Microsoft Entra ID のトークンに基づく有効期限のタイムスタンプが含まれます。 また、この Cookie には認証の対象となるユーザー名も含まれます。 この Cookie は、アプリケーション プロキシ サービスのみで認識される秘密キーを使用して暗号化されます。
5. アプリケーション プロキシは、ユーザーを最初に要求された URL にリダイレクトします。

事前認証手順のいずれかの部分が失敗した場合、ユーザーの要求は拒否され、問題の原因を示すメッセージがユーザーに表示されます。

##### 2.サービスがコネクタのキューに要求を配置する

コネクタは、アプリケーション プロキシ サービスへの送信接続を開いたままにします。 要求を受信すると、サービスは開いている接続のいずれかでキューに要求を配置し、コネクタが処理できるようにします。

この要求には、要求ヘッダー、暗号化された Cookie のデータ、要求を作成したユーザー、要求 ID が含まれます。 要求と共に暗号化された Cookie のデータが送信されますが、認証 Cookie 自体は送信されません。

##### 3.コネクタがキューの要求を処理する

アプリケーション プロキシは、要求に基づいて次のアクションのいずれかを実行します。

- 要求が単純な操作 (本文にデータがない RESTful API `GET` 要求など) の場合、コネクタがターゲットの内部リソースに接続した後、応答のために待機します。
- 関連付けられたデータが本文に含まれている要求 (RESTful API `POST` 操作など) の場合、コネクタはアプリケーション プロキシ インスタンスへのクライアント証明書を使用して、送信接続を行います。 この接続は、データを要求し、内部リソースへの接続を開くために確立されます。 アプリケーション プロキシ サービスはコネクタからの要求を受信すると、ユーザーからのコンテンツの受け入れを開始し、データをコネクタに転送します。 そしてコネクタが内部リソースにデータを転送します。

##### 4.コネクタが応答のために待機する

バックエンドへのすべてのコンテンツの要求および送信が完了すると、コネクタは応答を待機します。

コネクタは応答を受け取ると、アプリケーション プロキシ サービスへの送信接続を行うことで、ヘッダーの詳細情報を返し、返されるデータのストリーミングを開始します。

##### 5.サービスがユーザーにデータをストリーミングする

この時点で、アプリケーションの何らかの処理が発生します。 たとえば、アプリケーション プロキシはヘッダーまたは URL を変換します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-sign-in-bad-gateway-timeout-error"} -->
## "企業アプリにアクセスできません" というエラーを解決する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-sign-in-bad-gateway-timeout-error
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: Microsoft Entra アプリケーション プロキシ アプリケーションに関する一般的なアクセスの問題を解決する方法について説明します。

### 概要

この記事は、Microsoft Entra アプリケーション プロキシ アプリケーションの `This corporate app can't be accessed` エラーに関する一般的な問題のトラブルシューティングに役立ちます。

このエラーが表示されたら、エラー ページで状態コードを見つけます。

- **ゲートウェイのタイムアウト**: アプリケーション プロキシ サービスがコネクタに到達できません。 このエラーは、通常、コネクタの割り当て、コネクタ、またはコネクタのネットワーク規則に問題があることを示しています。
- **無効なゲートウェイ**: コネクタがバックエンド アプリケーションに到達できません。 このエラーは、アプリケーションの構成ミスを示している可能性があります。
- **禁止**: ユーザーはアプリケーションにアクセスする権限がありません。 このエラーは、ユーザーが Microsoft Entra ID でアプリケーションに割り当てられていない場合に発生する可能性があります。 このエラーは、ユーザーがバックエンドでアプリケーションにアクセスするためのアクセス許可を持っていない場合にも発生する可能性があります。

エラー コードを見つけるには、エラー メッセージの左下隅にある [ `Status Code`] の下にあるテキストを確認します。

[Image: ゲートウェイのタイムアウト エラーの例を示すスクリーンショット。]

### "ゲートウェイ タイムアウト" エラー

*ゲートウェイタイムアウト*エラーは、サービスがコネクタに到達しようとしたときに発生し、設定されているタイムアウト期間内に失敗します。 このエラーは、動作するコネクタがないコネクタ グループにアプリケーションが割り当てられている場合に表示されます。 このエラーは、必要なポートが開いていない場合にも表示されます。

### "無効なゲートウェイ" エラー

*無効なゲートウェイ* エラーは、コネクタがバックエンド アプリケーションに到達できないことを示します。 このエラーの原因となる一般的な間違いは次のとおりです。

- 入力ミスまたは間違いが内部 URL にあります。
- アプリケーションのルートは発行されません。 たとえば、 `http://expenses/reimbursement` を発行しているが、 `http://expenses`にアクセスしようとしています。
- Kerberos の制約付き委任 (KCD) 構成に問題があります。
- バックエンド アプリケーションに問題があります。

### "許可されていない" というエラー

*禁止された*エラーが表示された場合、ユーザーはアプリケーションに割り当てられません。 このエラーは、Microsoft Entra ID またはバックエンド アプリケーションのいずれかである可能性があります。

Azure でアプリケーションにユーザーを割り当てる方法の詳細については、「 [構成のテスト」を](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application#test-the-application)参照してください。

### アプリケーションの内部 URL を確認する

簡単な最初の手順として、 **エンタープライズ アプリケーション**&gt;**アプリケーション プロキシ**を使用してアプリケーションを開き、内部 URL を再確認します。 アプリケーションの内部 URL が、オンプレミス ネットワークから使用される URL であることを確認します。 URL が正しくない場合は、修正して変更を保存します。

### アプリケーションが作業コネクタ グループに割り当てられていることを確認する

アプリケーションが作業コネクタ グループに割り当てられていることを確認します。

詳細については、「[チュートリアル: Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)のアプリケーション プロキシを介してリモート アクセスするためのオンプレミス アプリケーションを追加する」を参照してください。

### 必要なすべてのポートが開かれていることを確認する

必要なすべてのポートが開かれていることを確認します。 必要なポートについては、「[チュートリアル: Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)のアプリケーション プロキシを介してリモート アクセスするためのオンプレミス アプリケーションを追加する」の「ポートを開く」セクションを参照してください。 必要なすべてのポートが開いている場合は、次のセクションに進みます。

### その他のコネクタ エラーを確認する

コネクタ自体の問題またはエラーを探します。 一般的なエラーの詳細については、「 [アプリケーション プロキシのトラブルシューティング」を](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-troubleshoot)参照してください。

コネクタ ログを直接確認して、エラーを特定します。 エラー メッセージの多くは、修正に関する特定の推奨事項を共有します。 ログを表示するには、 [プライベート ネットワーク コネクタを表示します](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors)。

### 一般的なソリューション

統合 Windows 認証 (IWA) を使用するようにアプリケーションが構成されている場合は、シングル サインオン (SSO) なしでアプリケーションをテストします。 SSO を使用せずにアプリケーションを確認するには、 **Enterprise Applications** を使用してアプリケーションを開き、[ **シングル サインオン** ] メニューに移動します。 ドロップダウンを **統合 Windows 認証** から、Microsoft Entra シングル サインオンが無効になっている に変更します。

次に、ブラウザーを開き、もう一度試してアプリケーションにアクセスします。 認証を求めるメッセージが表示され、アプリケーションにサインインできるようになります。 認証できる場合、問題は SSO を有効にする KCD 構成にあります。

エラーが解決しない場合は、コネクタがインストールされているコンピューターを使用してブラウザーを開き、アプリケーションの内部 URL にアクセスしてみてください。 アプリケーションに到達できない場合は、そのマシンが接続できない理由を確認するか、接続できるサーバー上のコネクタを使用します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-troubleshoot"} -->
## アプリケーション プロキシのトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-troubleshoot
- Service: entra-id / app-proxy
- Article date: 2026-03-10
- Summary: Microsoft Entra アプリケーション プロキシに関する一般的なエラーと構成の問題をトラブルシューティングする方法について説明します。

### 概要

Microsoft Entra アプリケーション プロキシの問題とエラー メッセージをトラブルシューティングするには、次の手順を使用します。

### 開始する前に

まず、コネクタを確認します。 プライベート ネットワーク コネクタをデバッグする方法については、「プライベート ネットワーク コネクタの [トラブルシューティング](https://learn.microsoft.com/ja-jp/entra/global-secure-access/troubleshoot-connectors)」を参照してください。 それでもアプリケーションへの接続に問題がある場合は、この記事に戻ってアプリケーションのトラブルシューティングを行ってください。

アプリケーションへのアクセス中または発行中にエラーが発生した場合は、次の手順を使用して、Microsoft Entra アプリケーション プロキシが正しく動作することを確認します。

- Windows サービス コンソールを開きます。 **Microsoft Entra プライベート ネットワーク コネクタ** サービスが有効で実行されていることを確認します。 アプリケーション プロキシ サービスのプロパティ ページを確認します。

    [Image: Microsoft Entra プライベート ネットワーク コネクタの状態が [実行中] であることを示すスクリーンショット。]
- イベント ビューアーを開きます。 **Applications and Services Logs**&gt;**Microsoft**&gt;**Microsoft Entra private network**&gt;**Connector**&gt;**Admin** に移動し、プライベート ネットワーク コネクタ イベントを確認します。
- 詳細なログを確認します。 [プライベート ネットワーク コネクタ セッション ログを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-conceptual-connectors)方法について説明します。

### アプリケーション構成エラー

一般的な構成の問題と推奨される解決策については、次のセクションを参照してください。

#### アプリが正しくレンダリングされない

アプリケーションが正しくレンダリングされないか、正しく機能しませんが、特定のエラー メッセージは表示されません。

この問題は、アプリの発行に使用したパスの外側に存在するコンテンツがアプリケーションに必要な場合に発生します。

たとえば、 `https://yourapp/app`パスを発行しても、アプリケーションが `https://yourapp/media`にあるイメージを参照している場合、イメージはアプリケーションに表示されません。

参照されているすべてのコンテンツとファイルを含める必要がある最上位レベルのパスを使用して、アプリケーションを発行してください。 この例では、そのレベルは `https://yourapp/`。

不足しているリソースが問題の原因であることを確認します。

1. Fiddler やブラウザー開発者ツールなどのネットワーク トラッカーを開きます (Microsoft Edge で F12 キーを押します)。
2. ページを読み込みます。
3. HTTP 404 エラーを探します。

404 エラーは、ページが見つからないことを示し、それらを公開する必要があることを示します。

#### アプリの読み込みに時間がかかりすぎる

アプリケーションは動作するが、読み込みに時間がかかる場合があります。

ネットワーク トポロジを変更して、アプリケーションの速度を向上させます。 [ネットワークに関する考慮事項を確認します](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-network-topology)。

#### 1 つのアプリケーションとして発行できない

同じアプリケーション内のすべてのリソースを発行できない場合は、複数のアプリケーションを発行し、それらの間のリンクを設定します。 このシナリオでは、 [カスタム ドメインの使用を検討してください](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)。 ただし、このソリューションでは、ドメインの証明書を所有し、アプリケーションで完全修飾ドメイン名 (FQDN) を使用する必要があります。 その他のオプションについては、 [壊れたリンクのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-page-links-broken-problem)を行います。

#### アプリの接続に関する問題

原因と推奨される解決策については、 [アプリケーション プロキシ アプリケーション用に開くポートを](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)参照してください。

#### 管理ポータルでの構成の問題

原因と推奨される解決策については、 [アプリケーション プロキシ アプリケーションでのシングル サインオンに関するセクションを](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-sso)参照してください。

#### バックエンド認証の問題

原因と推奨される解決策については、次の記事を参照してください。

- [Kerberos の制約付き委任のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-back-end-kerberos-constrained-delegation-how-to)
- [アプリケーション プロキシと PingAccess を使用したシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-ping-access-publishing-guide)

#### アプリケーションにサインインできない

`This corporate app can’t be accessed`エラーが表示され、アプリケーションにサインインできない場合は、構成エラーが発生している可能性があります。 推奨される解決策については、「 [無効なゲートウェイ タイムアウト エラーのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-sign-in-bad-gateway-timeout-error)」を参照してください。

#### プライベート ネットワーク コネクタのエラー

原因と推奨される解決策については、「 [プライベート ネットワーク コネクタのインストール」を](https://learn.microsoft.com/ja-jp/entra/global-secure-access/troubleshoot-connectors)参照してください。

### Kerberos のエラー

次の表では、Kerberos のセットアップと構成における一般的なエラーとその解決方法について説明します。

| エラー | 説明と手順 |
| --- | --- |
| `Failed to retrieve the current execution policy for running PowerShell scripts.` | コネクタのインストールに失敗した場合は、PowerShell 実行ポリシーが無効になっていないことを確認してください。1.グループ ポリシー エディターを開きます。2. **コンピューターの構成**&gt;**管理者テンプレート**&gt;**Windows コンポーネント**&gt;**Windows PowerShell** に移動し、[ **スクリプトの実行を有効にする**] をダブルクリックします。3.実行ポリシーは、 **[未構成]** または **[有効]** に設定できます。 ポリシーが **[有効]** に設定されている場合は、[ **オプション]** で実行ポリシーが [ **ローカル スクリプトとリモート署名済みスクリプトを許可** する] または **[すべてのスクリプトを許可する]** に設定されていることを確認します。 |
| `12008 - Microsoft Entra exceeded the maximum number of permitted Kerberos authentication attempts to the backend server.` | このエラーは、Microsoft Entra ID とバックエンド アプリケーション サーバーの構成が正しくないか、両方のコンピューターで時刻と日付の構成に問題があることを示します。 バックエンド サーバーは、Microsoft Entra ID によって作成された Kerberos チケットを拒否しました。  Microsoft Entra ID とバックエンド アプリケーション サーバーが正しく構成されていることを確認します。 Microsoft Entra ID とバックエンド アプリケーション サーバーの時刻と日付の構成が同期されていることを確認します。 |
| `13016 - Microsoft Entra ID cannot retrieve a Kerberos ticket on behalf of the user because there's no UPN in the edge token or in the access cookie.` | セキュリティ トークン サービス (STS) の構成に問題があります。 STS のユーザー プリンシパル名 (UPN) 要求構成を修正します。 |
| `13019 - Microsoft Entra ID cannot retrieve a Kerberos ticket on behalf of the user because of the following general API error.` | このイベントは、Microsoft Entra ID とドメイン コントローラー サーバーの間の正しくない構成、または両方のコンピューターでの時刻と日付の構成に関する問題を示します。 ドメイン コントローラーが、Microsoft Entra ID によって作成された Kerberos チケットを拒否しました。  Microsoft Entra ID とバックエンド アプリケーション サーバー 、特にサービス プリンシパル名 (SPN) の構成が正しく構成されていることを確認します。 ドメイン コントローラーが Microsoft Entra ID との信頼を確立していることを確認します。 どちらも同じドメインを使用する必要があります。 Microsoft Entra ID とドメイン コントローラーの時刻と日付の構成が同期されていることを確認します。 |
| `13020 - Microsoft Entra ID cannot retrieve a Kerberos ticket on behalf of the user because the back-end server SPN is not defined.` | このイベントは、Microsoft Entra ID とドメイン コントローラー サーバーの間の正しくない構成、または両方のコンピューターでの時刻と日付の構成に関する問題を示します。 ドメイン コントローラーが、Microsoft Entra ID によって作成された Kerberos チケットを拒否しました。  Microsoft Entra ID とバックエンド アプリケーション サーバー (特に SPN 構成) が正しく構成されていることを確認します。 ドメイン コントローラーが Microsoft Entra ID との信頼を確立していることを確認します。 どちらも同じドメインを使用する必要があります。 Microsoft Entra ID とドメイン コントローラーの時刻と日付の構成が同期されていることを確認します。 |
| `13022 - Microsoft Entra ID cannot authenticate the user because the back-end server responds to Kerberos authentication attempts with an HTTP 401 error.` | このイベントは、Microsoft Entra ID とバックエンド アプリケーション サーバーの間の正しくない構成、または両方のコンピューターでの時刻と日付の構成に関する問題を示します。 バックエンド サーバーは、Microsoft Entra ID によって作成された Kerberos チケットを拒否しました。  Microsoft Entra ID とバックエンド アプリケーション サーバーが正しく構成されていることを確認します。 Microsoft Entra ID とバックエンド アプリケーション サーバーの時刻と日付の構成が同期されていることを確認します。  詳細については、「 [アプリケーション プロキシの Kerberos の制約付き委任構成のトラブルシューティング」を](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-back-end-kerberos-constrained-delegation-how-to)参照してください。 |

### アプリ のユーザー エラー

次の表は、アプリ ユーザーがアプリケーション プロキシ アプリにアクセスしようとしたときに発生する可能性があるエラーを示しています。

| エラー | 説明と手順 |
| --- | --- |
| `The website cannot display the page.` | 発行した統合 Windows 認証 (IWA) アプリにアクセスしようとすると、ユーザーにエラーが表示されます。 アプリケーションに対して定義されている SPN が正しくありません。  アプリケーションの SPN が正しいことを確認します。 |
| `The website cannot display the page.` | 発行した Outlook Web アプリケーション (OWA) アプリにアクセスしようとすると、ユーザーにエラーが表示されます。 問題の結果は次のとおりです。 - アプリケーションに対して定義された SPN が正しくありません。 アプリケーションの SPN が正しいことを確認します。- アプリケーションにアクセスしようとしたユーザーが、サインインに会社のアカウントではなく Microsoft アカウントを使用しているか、ユーザーがゲスト ユーザーです。 発行されたアプリケーションのドメインと一致する企業アカウントを使用してユーザーがサインインしていることを確認します。 Microsoft アカウントのユーザーとゲストは IWA アプリケーションにアクセスできません。- アプリケーションにアクセスしようとしたユーザーが、オンプレミス構成のアプリケーションに対して適切に定義されていません。 ユーザーがバックエンド アプリケーションにアクセスするために必要なオンプレミスのアクセス許可を持っていることを確認します。 |
| `This corporate app can’t be accessed. You are not authorized to access the application. Authorization failed. Make sure to assign the user with access to the application.` | このエラーは、ユーザーが Microsoft アカウントまたはゲスト ユーザーを使用してアプリにアクセスしようとしたときに発生します。 Microsoft アカウントのユーザーとゲストは IWA アプリケーションにアクセスできません。 ユーザーがアプリのドメインに一致する企業アカウントでサインインしていることを確認します。この問題を解決するには、[ **アプリケーション** ] タブの [ **ユーザーとグループ**] に移動し、ユーザーまたはグループをアプリに割り当てます。 |
| `This corporate app can’t be accessed right now. Please try again later… The connector timed out.` | アプリケーションにアクセスしようとしたユーザーが、オンプレミス構成のアプリケーションに対して適切に定義されていません。 ユーザーがバックエンド アプリケーションにアクセスするために必要なオンプレミスのアクセス許可を持っていることを確認します。 |
| `This corporate app can’t be accessed. You are not authorized to access the application. Authorization failed. Make sure that the user has a license for Microsoft Entra ID P1 or P2.` | サブスクライバーの管理者によって Premium ライセンスが明示的に割り当てられない場合、発行したアプリにアクセスしようとすると、ユーザーにエラーが表示されます。 サブスクライバーの [Microsoft Entra ID **ライセンス** ] タブで、ユーザーまたはユーザー グループに Premium ライセンスが割り当てられていることを確認します。 |
| `A server with the specified host name could not be found.` | 発行したアプリにアクセスしようとして、アプリケーションのカスタム ドメインが正しく構成されていない場合、ユーザーにエラーが表示されます。  ドメインの証明書を確認し、ドメイン ネーム システム (DNS) レコードを正しく構成します。  詳細については、「 [Microsoft Entra アプリケーション プロキシでのカスタム ドメインの操作](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)」を参照してください。 |
| `Forbidden: This corporate app can't be accessed OR The user could not be authorized. Make sure the user is defined in your on-premises AD and that the user has access to the app in your on-premises AD.` | この問題は、認証情報へのアクセスに関する問題である可能性があります。 詳細については、「 https://support.microsoft.com/help/331951/some-applications-and-apis-require-access-to-authorization-information」を参照してください。  このエラーを解決するには、プライベート ネットワーク コネクタ マシン アカウントを Windows 承認アクセス グループの組み込みドメイン グループに追加します。 |
| `InternalServerError: This corporate app can’t be accessed right now. Please try again later… ConnectorError:Unauthorized.` | コネクタでは、クライアント証明書を使用して、Microsoft Entra アプリケーション プロキシ クラウド サービス エンドポイントへの送信接続をセキュリティで保護します。 このエラーは、クライアント証明書がエンドポイントに到達できない場合に発生します。 たとえば、ネットワーク デバイスがトランスポート層セキュリティ (TLS) 検査を実行したり、TLS 接続を切断したりしたときに発生することがあります。  このエラーを解決するには、送信 TLS 通信のインライン検査と終了を避けてください。 Microsoft Entra プライベート ネットワーク コネクタと Microsoft Entra アプリケーション プロキシ クラウド サービスの間で検査と終了を行う必要はありません。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-understand-cors-issues"} -->
## クロスオリジン リソース共有の問題を解決する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-understand-cors-issues
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: Microsoft Entra アプリケーション プロキシでクロスオリジン リソース共有 (CORS) の問題を特定して解決する方法について説明します。

### 概要

[クロスオリジン リソース共有 (CORS)](https://www.w3.org/TR/cors/) は、Microsoft Entra アプリケーション プロキシを介して発行するアプリと API に課題を提示する可能性があります。 この記事では、Microsoft Entra アプリケーション プロキシでの CORS の問題と解決策について説明します。

通常、ブラウザーのセキュリティにより、Web ページが別のドメインに要求を行うのを防ぐことができます。 この制限は、*同一オリジン ポリシー*と呼ばれます。 このポリシーは、悪意のあるサイトが別のサイトから機密データを読み取らないようにします。 ただし、他のサイトから Web API を呼び出したほうが良い場合もあります。 CORS は、一部のクロスオリジン要求を許可し、他の要求を拒否するようにサーバーに指示する W3C 標準です。

### CORS の問題を特定する

次の例のように、同じスキーム、ホスト、およびポート ([コメント要求 (RFC) 6454)](https://tools.ietf.org/html/rfc6454) がある場合、2 つの URL の配信元は同じです。

- `http://contoso.com/foo.html`
- `http://contoso.com/bar.html`

これらの URL の配信元は、前の 2 つの URL とは異なります。

- `http://contoso.net`: 異なるドメイン
- `http://contoso.com:9000/foo.html`: 異なるポート
- `https://contoso.com/foo.html`: 異なるスキーム
- `http://www.contoso.com/foo.html`: 異なるサブドメイン

同一オリジン ポリシーは、正しいアクセス制御ヘッダーを使用する場合を除き、他のオリジンからのリソースにアプリがアクセスするのを防ぎます。 CORS ヘッダーが存在しないか正しくない場合、クロス オリジン要求は失敗します。

CORS の問題はブラウザーのデバッグ ツールを使用して識別できます。

1. ブラウザーを開き、Web アプリに移動します。
2. **F12** キーを選択して、DevTools でデバッグ コンソールを開きます。
3. トランザクションを再現し、コンソール メッセージを確認します。 CORS 違反があると、オリジンに関するコンソール エラーが発生します。

次のスクリーンショットで、[**Try It**]\(試してみる\) ボタンを選択すると、`https://corswebclient-contoso.msappproxy.net` ヘッダーに`Access-Control-Allow-Origin`が見つからなかったという CORS エラー メッセージが発生しました。

[Image: CORS の問題の例を示すスクリーンショット。]

### アプリケーション プロキシでの CORS の課題

次の例は、Microsoft Entra アプリケーション プロキシの CORS の一般的なシナリオを示しています。 内部サーバーは、*CORSWebService* Web API コントローラーと、*CORSWebService* を呼び出す *CORSWebClient* をホストします。 *CORSWebClient* から *CORSWebService* への非同期 JavaScript および XML (AJAX) 要求が行われます。

[Image: オンプレミスの同一オリジンリクエストを示すスクリーンショット。]

*CORSWebClient* アプリはオンプレミスで動作しますが、Microsoft Entra アプリケーション プロキシを介して発行すると失敗するか、エラーが表示されます。 *CORSWebClient* と *CORSWebService* が個別のアプリとして発行されている場合、異なるドメインでホストされます。 異なるドメインは *CORSWebClient* から *CORSWebService* クロスオリジンへの AJAX 要求を行い、失敗します。

[Image: アプリケーション プロキシ CORS 要求を示すスクリーンショット。]

### アプリケーション プロキシの CORS に関する問題の解決策

上記の CORS の問題は、いくつかの方法で解決できます。

#### オプション 1: カスタム ドメインの設定

Microsoft Entra アプリケーション プロキシ [カスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain) を使用して、アプリの配信元、コード、またはヘッダーに変更を加えることなく、同じ配信元から発行します。

#### オプション 2:親ディレクトリを公開する

両方のアプリの親ディレクトリを公開します。 この解決策は、Web サーバー上にアプリが 2 つしかない場合は特に有効です。 各アプリを別々に公開する代わりに、共通の親ディレクトリを公開してオリジンを同じにすることができます。

次の例は、 *CORSWebClient* アプリの Microsoft Entra アプリケーション プロキシ ページを示しています。 内部 URL が `contoso.com/CORSWebClient` に設定されている場合、アプリはクロスオリジンであるため、 `contoso.com/CORSWebService` ディレクトリに対して正常な要求を行うことはできません。

[Image: アプリを個別に発行する方法を示すスクリーンショット。]

代わりに、親ディレクトリを発行する **内部 URL** の値を設定します。これには、 `CORSWebClient` ディレクトリと `CORSWebService` ディレクトリの両方が含まれます。

[Image: 親ディレクトリの発行を示すスクリーンショット。]

結果として得られるアプリの URL は、CORS の問題を効果的に解決します。

- `https://corswebclient-contoso.msappproxy.net/CORSWebService`
- `https://corswebclient-contoso.msappproxy.net/CORSWebClient`

#### オプション 3:HTTP ヘッダーを更新する

配信元要求と一致するには、Web サービスにカスタム HTTP 応答ヘッダーを追加します。 インターネット インフォメーション サービス (IIS) で実行されている Web サイトでは、IIS マネージャーを使用してヘッダーを変更します。

[Image: IIS マネージャーでのカスタム応答ヘッダーの追加を示すスクリーンショット。]

この変更では、コードを変更する必要はありません。 Fiddler のトレースでそれを確認できます。

```http
**Post the Header Addition**\
HTTP/1.1 200 OK\
Cache-Control: no-cache\
Pragma: no-cache\
Content-Type: text/plain; charset=utf-8\
Expires: -1\
Vary: Accept-Encoding\
Server: Microsoft-IIS/8.5 Microsoft-HTTPAPI/2.0\
**Access-Control-Allow-Origin: https://corswebclient-contoso.msappproxy.net**\
X-AspNet-Version: 4.0.30319\
X-Powered-By: ASP.NET\
Content-Length: 17
```

#### オプション 4: アプリケーションを変更する

`Access-Control-Allow-Origin` ヘッダーを適切な値で追加することで、CORS をサポートするようにアプリケーションを変更できます。 ヘッダーを追加する方法は、アプリケーションのコード言語によって異なります。 コードを変更するには、最も労力が必要です。

#### オプション 5:アクセス トークンの有効期間を延長する

一部の CORS の問題は解決できません。 たとえば、アプリケーションは認証のために `login.microsoftonline.com` にリダイレクトされ、アクセス トークンの有効期限が切れます。 このとき、CORS の呼び出しは失敗します。 このシナリオの回避策は、ユーザーのセッション中に期限が切れるのを防ぐために、アクセス トークンの有効期間を延長することです。 詳細については、「[Microsoft Entra ID での構成可能なトークンの有効期間](https://learn.microsoft.com/ja-jp/entra/identity-platform/configurable-token-lifetimes)」を参照してください。

#### オプション 6: 複雑なアプリケーション

プレフライト (`OPTIONS`) 要求が使用される複数の個々の Web アプリケーションを含むアプリケーションの場合は、複雑なアプリケーション機能を使用してアプリを発行できます。 詳細については、「 [Microsoft Entra アプリケーション プロキシの複雑なアプリケーションについて」](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-complex-application)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/application-proxy-wildcard"} -->
## Microsoft Entra アプリケーション プロキシ内のワイルドカード アプリケーション - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-wildcard
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Microsoft Entra アプリケーション プロキシでワイルドカード URL パターンを使用して、複数のオンプレミス アプリケーションを一度に発行および管理します。

### 概要

Microsoft Entra ID では、大量のオンプレミス アプリケーションを構成すると、すぐに管理できなくなる可能性があります。また、それらのアプリケーションの多数で同じ設定が必要な場合に構成エラーが発生する不要なリスクが生じます。 [Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)では、ワイルドカード アプリケーション発行を使用して多数のアプリケーションを同時に発行および管理し、この問題を解決できます。 ソリューションには次のものが用意されています。

- 管理オーバーヘッドの簡素化
- 潜在的な構成エラーの数を減らしました
- その他のリソースへのアクセスをセキュリティで保護する

この記事では、ご利用の環境でワイルドカード アプリケーション発行を構成するために必要な情報を提供します。

### ワイルドカード アプリケーションの作成

構成が同じアプリケーションのグループがある場合、ワイルドカード (\*) アプリケーションを作成できます。 ワイルドカード アプリケーションの候補として考えられるのは、次の設定を共有しているアプリケーションです。

- それらへのアクセス権を持つユーザーのグループ
- シングル サインオン (SSO) メソッド
- アクセス プロトコル (http、https)

内部 URL と外部 URL の両方が次の形式の場合は、ワイルドカードを使用してアプリケーションを発行できます。

>
> http(s)://\*.&lt;ドメイン&gt;

(例: `http(s)://*.adventure-works.com`)。

内部 URL と外部 URL は異なるドメインを使用できますが、ベスト プラクティスとして同じである必要があります。 アプリケーションの発行時、いずれかの URL にワイルドカードがないとエラーが表示されます。

ワイルドカード アプリケーションの作成は、他のすべてのアプリケーションで使用できるのと同じ[アプリケーション発行フロー](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)に基づきます。 唯一の違いは、URL にワイルドカードを含めることだけです。場合によっては SSO 構成にもワイルドカードを含めます。

### 前提条件

開始するには、要件が満たされていることを確認します。

#### カスタム ドメイン

[カスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)は他のすべてのアプリケーションでは省略可能ですが、ワイルドカード アプリケーションの前提条件です。 カスタム ドメインの作成には以下が必要です。

1. Azure 内で確認済みドメインを作成する。
2. Personal Information Exchange (PFX) 形式のトランスポート層セキュリティ (TLS) 証明書をアプリケーション プロキシにアップロードします。

作成する予定のアプリケーションに一致するワイルドカード証明書を使用することを検討する必要があります。

セキュリティ上の理由から、ワイルドカードは、外部 URL にカスタム ドメインを使用するアプリケーションでのみサポートされます。

#### ドメイン ネーム システム (DNS) の更新

カスタム ドメインを使用する場合、アプリケーション プロキシ エンドポイントの外部 URL を指す外部 URL 用の CNAME レコードで DNS エントリ (例: `*.adventure-works.com`) を作成します。 ワイルドカード アプリケーションでは、CNAME レコードが関連する外部 URL を指す必要があります。

>
> `<yourAADTenantId>.tenant.runtime.msappproxy.net`

CNAME が正しく構成されていることを確認します。ターゲット エンドポイントの 1 つ (たとえば、) で `expenses.adventure-works.com` を使用できます。 応答には、既に述べたエイリアス (`<yourAADTenantId>.tenant.runtime.msappproxy.net`) が含まれます。

#### 既定のリージョン以外のアプリケーション プロキシ クラウド サービス リージョンに割り当てられているコネクタ グループを使用する

既定のテナント リージョンとは異なるリージョンにコネクタがインストールされている場合は、これらのアプリケーションへのアクセスのパフォーマンスを向上させるために、コネクタ グループが最適化されているリージョンを変更することをお勧めします。 詳細については、「 [最も近いアプリケーション プロキシ クラウド サービスを使用するようにコネクタ グループを最適化する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-network-topology#optimize-connector-groups-to-use-closest-application-proxy-cloud-service)」を参照してください。

ワイルドカード アプリケーションに割り当てられているコネクタ グループが既定の **リージョンとは異なるリージョンを**使用している場合は、リージョン固有の外部 URL を指す CNAME レコードを更新する必要があります。 次の表を使用して、関連する URL を決定します。

| コネクタの割り当て済みリージョン | 外部 URL |
| --- | --- |
| アジア | `<yourAADTenantId>.asia.tenant.runtime.msappproxy.net` |
| オーストラリア | `<yourAADTenantId>.aus.tenant.runtime.msappproxy.net` |
| ヨーロッパ | `<yourAADTenantId>.eur.tenant.runtime.msappproxy.net` |
| 北米 | `<yourAADTenantId>.nam.tenant.runtime.msappproxy.net` |

### ワイルドカード アプリケーションに関する考慮事項

ワイルドカード アプリケーションに対して考慮する必要があるいくつかの考慮事項を次に示します。

#### 許容される形式

ワイルドカード アプリケーションでは、**内部 URL** は `http(s)://*.<domain>` の形式である必要があります。

[Image: 内部 URL の場合は、http(s)://*.&lt; の形式を使用しますdomain&gt;。]

**外部 URL** を構成する際は、`https://*.<custom domain>` の形式を使用する必要があります。

[Image: 外部 URL の場合は、https://*.&lt; 形式を使用しますカスタム ドメイン&gt;。]

ワイルドカード、複数のワイルドカード、またはその他の正規表現文字列の他の位置はサポートされておらず、エラーの原因になっています。

#### ワイルドカードからのアプリケーションの除外

次の方法で、ワイルドカード アプリケーションからアプリケーションを除外できます

- 例外アプリケーションを通常のアプリケーションとして発行する
- DNS 設定を通じて、特定のアプリケーションに関してのみワイルドカードを有効にする

アプリケーションを通常のアプリケーションとして発行することは、ワイルドカードからアプリケーションを除外する方法として推奨されます。 除外されるアプリケーションをワイルドカード アプリケーションの前に発行する必要があります。これにより、最初から例外が適用されます。 最も具体的なアプリケーションが常に優先されます。 `budgets.finance.adventure-works.com` として発行されたアプリケーションは、アプリケーション `*.finance.adventure-works.com`よりも優先され、アプリケーション `*.adventure-works.com`よりも優先されます。

DNS 管理を通じて、特定のアプリケーションに対してのみ機能するようワイルドカードを制限することもできます。 ベスト プラクティスとして、ワイルドカードを含み、構成した外部 URL の形式と一致する CNAME エントリを作成する必要があります。 ただし、代わりに特定のアプリケーション URL でワイルドカードを指すこともできます。 たとえば、`*.adventure-works.com`ではなく、`hr.adventure-works.com`、`expenses.adventure-works.com`、`travel.adventure-works.com individually`を`00001111-aaaa-2222-bbbb-3333cccc4444.tenant.runtime.msappproxy.net`に向けます。

このオプションを使用する場合、同じ場所を指している、値 `AppId.domain` の別の CNAME エントリ (たとえば `00001111-aaaa-2222-bbbb-3333cccc4444.adventure-works.com`) も必要です。 ワイルドカード アプリケーションのアプリケーション プロパティ ページで **AppId** を見つけることができます。

#### MyApps パネルのホームページ URL の設定

[MyApps パネル](https://myapps.microsoft.com)では、ワイルドカード アプリケーションが単一のタイルで表されます。 既定では、このタイルは非表示です。 タイルを表示してユーザーを特定のページに到達させるには:

1. [ホームページ URL の設定](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-custom-home-page)に関するガイドラインに従います。
2. アプリケーション プロパティ ページで **[Show Application](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/アプリケーションの表示)** を **true** に設定します。

#### Kerberos の制約付き委任に関する考慮事項

[SSO メソッドとして kerberos 制約付き委任 (KCD)](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-sso-with-kcd) を使用するアプリケーションの場合、SSO メソッドに一覧表示されているサービス プリンシパル名 (SPN) にはワイルドカードが必要です。 たとえば、SPN は `HTTP/*.adventure-works.com` などになります。 さらに、バックエンド サーバーで個別の SPN を構成する必要があります (例: `HTTP/expenses.adventure-works.com and HTTP/travel.adventure-works.com`)。

### シナリオ 1:一般的なワイルドカード アプリケーション

このシナリオでは、発行したい異なる 3 つのアプリケーションがあります。

- `expenses.adventure-works.com`
- `hr.adventure-works.com`
- `travel.adventure-works.com`

全ての3つのアプリケーション:

- 全ユーザーによって使用されます
- "*統合 Windows 認証*" を使用します
- プロパティが同じです

「[Microsoft Entra アプリケーション プロキシを使用してアプリケーションを発行する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)」で説明されている手順を使用して、ワイルドカード アプリケーションを発行できます。 このシナリオは以下を前提とします。

- ID が `aaaabbbb-0000-cccc-1111-dddd2222eeee` のテナント
- `adventure-works.com`と呼ばれる検証済みドメイン。
- **CNAME** エントリ`*.adventure-works.com`が`00001111-aaaa-2222-bbbb-3333cccc4444.tenant.runtime.msappproxy.net`を指しています。

[文書化されている手順](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)に従って、新しいアプリケーション プロキシ アプリケーションをテナントに作成します。 この例では、ワイルドカードが次のフィールド内にあります。

- 内部 URL:

    [Image: 内部 URL フィールドにワイルドカードを示すスクリーンショット。]
- 外部 URL:

    [Image: 外部 URL フィールドにワイルドカードを示すスクリーンショット。]
- 内部アプリケーション SPN:

    [Image: SPN 構成フィールドにワイルドカードを示すスクリーンショット。]

ワイルドカード アプリケーションを発行することで、使い慣れている URL (たとえば、 `travel.adventure-works.com`) に移動して、3 つのアプリケーションにアクセスできるようになりました。

この構成では次の構造が実装されます。

[Image: アプリケーション プロキシ経由でルーティングする特定の URL を持つ finance.adventure-works.com を示す図。]

| 色 | 説明 |
| --- | --- |
| 青 | アプリケーションが明示的に公開され、Microsoft Entra 管理センターに表示されます。 |
| グレー | 親アプリケーション経由でアクセスできるアプリケーション。 |

### シナリオ 2: 一般的なワイルドカード アプリケーション (例外あり)

3 つの一般的なアプリケーションに加えて、 `finance.adventure-works.com`、財務部門のみがアクセスできる別のアプリケーションがあります。 現在のアプリケーション構造では、ワイルドカード アプリケーションを通じてすべての従業員が財務アプリケーションにアクセスできます。 変更を行うには、より制限の厳しいアクセス許可を持つ別のアプリケーションとして Finance を構成することで、ワイルドカードからアプリケーションを除外します。

`finance.adventure-works.com` が (アプリケーション用のアプリケーション プロキシ ページで指定された) 特定のアプリケーション エンドポイントを指す CNAME レコードを用意する必要があります。 このシナリオでは、 `finance.adventure-works.com` は `https://finance-awcycles.msappproxy.net/`を指します。

[文書化された手順](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)に従って、シナリオには次の設定が必要です。

- **内部 URL** で、ワイルドカードの代わりに **finance** を設定します。

    [Image: 例: 内部 URL にワイルドカードの代わりに finance を設定します。]
- **外部 URL** で、ワイルドカードの代わりに **finance** を設定します。

    [Image: 例: 外部 URL にワイルドカードではなく finance を設定します。]
- 内部アプリケーション SPN で、ワイルドカードの代わりに **finance** を設定します。

    [Image: 例: SPN 構成でワイルドカードの代わりに finance を設定します。]

この構成では次のシナリオが実装されます。

[Image: 1 つの外部 URL パターンを共有する 3 つのアプリケーションを含むワイルドカード アプリケーション ルーティングの図。]

`finance.adventure-works.com` は固有の URL です。 URL `*.adventure-works.com` は固有ではありません。 固有の URL の方が優先されます。 `finance.adventure-works.com` に移動するユーザーには、財務リソースのアプリケーションで指定されたエクスペリエンスが表示されます。 `finance.adventure-works.com`にアクセスできるのは、財務部門の従業員だけです。

財務用に発行された複数のアプリケーションがあり、`finance.adventure-works.com` が確認済みドメインとしてある場合、別のワイルドカード アプリケーション `*.finance.adventure-works.com` を発行できます。 ドメインは汎用 `*.adventure-works.com`よりも具体的であるため、ユーザーが財務ドメイン内のアプリケーションにアクセスした場合に優先されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/concept-continuous-access-evaluation"} -->
## アプリケーション プロキシの継続的アクセス評価 (CAE) について説明します - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/concept-continuous-access-evaluation
- Service: entra-id / app-proxy
- Article date: 2026-04-01
- Summary: 継続的アクセス評価 (CAE) がアプリケーション プロキシと連携して、オンプレミス アプリケーションにほぼリアルタイムのアクセス ポリシーを適用する方法について説明します。

継続的アクセス評価 (CAE) は、ポリシーの変更とユーザー リスクに基づいてリアルタイムのアクセス制御を提供するセキュリティ機能です。 CAE は、セッションの有効性を継続的に評価することで、ほぼリアルタイムでアクセス ポリシーを適用します。 ポリシーの変更、ユーザー リスクの更新、またはその他の重要なセキュリティ イベントが発生すると、CAE はアクセス トークンと更新トークンを取り消し、ユーザー アクセスが常に最新のセキュリティ要件に準拠していることを確認します。

従来の CAE とは異なり、各ワークロードで特別なライブラリを採用する必要があり、ファースト パーティのアプリケーションに限定されます。CAE for Application Proxy では、アプリケーションを CAE 対応にする必要なく、アプリケーション プロキシを介して発行されたオンプレミス アプリケーションにこれらの利点が拡張されます。

### CAE がアクセスを保護する方法

CAE for Application Proxy は重要な ID およびセキュリティ イベントに応答するため、管理者はアクセスを迅速に制限できます。 次のイベントは、ほぼリアルタイムで適用をトリガーします。

- ユーザーの終了またはパスワードの変更/リセット: CAE は、ユーザー セッションと更新トークンを取り消して、継続的なアクセスを防ぎます。
- ネットワークの場所の変更: CAE は、ユーザーのネットワーク コンテキストが変更されたときに条件付きアクセスの場所ポリシーを再評価し、適用します。
- 信頼されたネットワークの外部にあるコンピューターへのトークン エクスポート: CAE では、トークンの使用をブロックしたり、信頼されていないマシンからトークンを使用する場合に追加の制御を必要としたりするために、条件付きアクセスの場所ポリシーが適用されます。

### CAE for Application Proxy のしくみを理解する

アプリケーション プロキシに対して CAE が有効になっている場合、要求と適用のフローは次のように続行されます。

1. ユーザーは、アプリケーション プロキシを使用してオンプレミス アプリケーションへのアクセスを要求します。
2. Microsoft Entra ID が要求を認証します。
3. 認証が成功すると、ユーザーは通常 60 ~ 90 分間有効なアクセス トークンを受け取ります。
4. CAE は、ポリシーの変更、リスクシグナル、失効イベントについてアクティブなセッションを継続的に評価します。
5. トリガーが発生した場合 (パスワード リセット、アカウントの無効化、昇格されたリスクなど)、CAE はセッションを取り消し、ユーザーに再度サインインを要求します。

このワークフローにより、アプリケーションの変更を必要とせずに、公開されたアプリケーションに対してほぼリアルタイムでアクセス ポリシーを適用できます。

### CAE 再認証をトリガーする Microsoft Entra ID シグナル

アプリケーション プロキシは、次のイベントについて、Microsoft Entra ID からほぼリアルタイムでシグナルを受信します。

- ユーザー アカウントが削除または無効になっています。
- ユーザーのパスワードが変更またはリセットされます。
- ユーザーに対して多要素認証 (MFA) が有効になっています。
- 管理者は、ユーザーのすべての更新トークンを取り消します。

アプリケーション プロキシは、これらのシグナルのいずれかを受信すると、ユーザーに再認証を求めます。 再認証が成功すると、ユーザーはアプリケーション プロキシを介して発行されたリソースへのアクセスを回復します。

### アプリケーション プロキシの CAE を無効にする

#### 特定のアプリケーション プロキシ アプリケーションの CAE を無効にする

アプリケーション プロキシの CAE は、すべてのアプリケーション プロキシ アプリケーションで既定で有効になっています。 個々のアプリケーションに対して CAE を無効にするには、Microsoft Graph または Microsoft Entra 管理センターを使用します。

個々のアプリケーション プロキシ アプリケーションの CAE を無効にするには、Microsoft Graph でアプリケーションの `onPremisesPublishing` 設定を更新します。

次の PATCH 要求を送信します。

```http
PATCH https://graph.microsoft.com/beta/applications/{objectId}/onPremisesPublishing
Content-Type: application/json

{
    "isContinuousAccessEvaluationEnabled": false
}
```

要求が成功すると、次の応答が返されます。

```http
HTTP/1.1 204 No Content
```

Microsoft Graph を呼び出すアカウントまたはアプリに、アプリケーションを更新するアクセス許可があることを確認します (たとえば、 `Application.ReadWrite.All`)。

Microsoft Entra 管理センターでアプリケーション プロキシの CAE を無効にすることもできます。

[Image: Microsoft Entra 管理センターの [独自のオンプレミス アプリケーションの追加] ページの [詳細設定] タブの [継続的アクセス評価を無効にする] チェック ボックスのスクリーンショット。]

#### テナント全体の CAE を無効にする

Microsoft Entra ID 条件付きアクセスは、テナント全体の CAE 動作を制御します。 既定では、CAE はサポートするすべてのアプリケーションで有効になっています。 すべてのサービスで CAE を無効にするには、条件付きアクセス構成を更新します。 手順については、「 [CAE を有効または無効にする」を](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation#enable-or-disable-cae)参照してください。

注

CAE は、条件付きアクセスで厳密な適用を有効にしてアプリケーション プロキシ アプリケーションに適用しない限り、日和見的です。 厳密でないポリシーを使用すると、アプリケーション プロキシは再認証時に通常のアクセス トークンの発行にフォールバックできます。 このフォールバック動作のため、テナント全体の CAE を無効にする必要はほとんどありません。

### 既知の制限

既知の問題と制限事項の詳細については、 [アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-faq)に関する FAQ を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/conceptual-deployment-plan"} -->
## Microsoft Entra アプリケーション プロキシのデプロイを計画する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/conceptual-deployment-plan
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: 組織内でアプリケーション プロキシのデプロイを計画するためのエンド ツー エンド ガイド

### 概要

Microsoft Entra アプリケーション プロキシは、オンプレミスのアプリケーションのための安全でコスト効率の高いリモート アクセス ソリューションです。 "Cloud First" 組織は、最新のプロトコルをまだ使用できない従来のオンプレミス アプリケーションへのアクセスを管理するための即時移行パスを提供します。 概要については、「 [アプリケーション プロキシとは」](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)を参照してください。

リモート ユーザーが内部リソースにアクセスできるようにするには、アプリケーション プロキシをお勧めします。 このようなリモート アクセスのユース ケースでは、アプリケーション プロキシを使うと、VPN またはリバース プロキシは必要なくなります。 これは、企業ネットワーク上にいるユーザーを対象としたものではありません。 イントラネット上のアプリケーション プロキシにアクセスするユーザーには、パフォーマンスの問題が発生する可能性があります。

この記事には、Microsoft Entra アプリケーション プロキシの計画、操作、管理を行うのに必要なリソースが含まれています。

### 実装の計画

次のセクションでは、効率的なデプロイ エクスペリエンスを実現するために設定する主要な計画要素の概要を示します。

#### 前提条件

実装を開始する前に次の前提条件を満たしている必要があります。 [オンプレミス アプリケーションを追加するためのチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)では、これらの前提条件を含め、環境の設定に関する詳細を確認できます。

- **コネクタ**:コネクタは、以下の上にデプロイできる軽量エージェントです。

    - オンプレミスの物理ハードウェア
    - ハイパーバイザー ソリューション内でホストされている仮想マシン (VM)
    - アプリケーション プロキシ サービスへの送信接続を有効にする、Azure でホストされている VM。
- 詳細な概要については、 [Microsoft Entra プライベート ネットワーク コネクタ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors) を参照してください。

    - コネクタをインストールする前に [、トランスポート層セキュリティ (TLS) 1.2](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application) に対してコネクタ マシンを有効にする必要があります。
    - 可能であれば、 [バックエンド Web アプリケーション サーバーと同じネットワークとセグメントにコネクタをデプロイします](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-network-topology)。 アプリケーションの検出を完了した後でコネクタをデプロイすることをお勧めします。
    - 高可用性とスケールを提供するには、各コネクタ グループに少なくとも 2 つのコネクタが必要です。 3 つのコネクタを持つことは、任意の時点でマシンにサービスを提供するために最適です。 コネクタの [容量テーブル](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors#specifications-and-sizing-requirements) を確認して、コネクタのマシンの種類を決定します。
- **ネットワーク アクセス設定**: Microsoft Entra プライベート ネットワーク コネクタは [、HTTPS (伝送制御プロトコル (TCP) ポート 443) と HTTP (TCP ポート 80) を介して Azure に接続](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)します。

    - コネクタの TLS トラフィックの終了はサポートされておらず、コネクタがそれぞれの Microsoft Entra アプリケーション プロキシ エンドポイントでセキュリティで保護されたチャネルを確立するのを防ぎます。
    - コネクタと Azure の間の送信 TLS 通信に対してすべての形式のインライン検査を行わないでください。 コネクタとバックエンド アプリケーション間の内部検査は可能ですが、ユーザー エクスペリエンスを低下させることがあるためお勧めしません。
    - コネクタ自体の負荷分散はサポートされていないか、必要ありません。

#### Microsoft Entra アプリケーション プロキシを構成する前の重要な考慮事項

Microsoft Entra アプリケーション プロキシを構成して実装するには、次のコア要件を満たす必要があります。

- **Azure オンボード**: アプリケーション プロキシをデプロイする前に、ユーザー ID をオンプレミス ディレクトリから同期するか、Microsoft Entra テナント内で直接作成する必要があります。 ID 同期を使用すると、Microsoft Entra ID を使用して、アプリケーション プロキシが発行したアプリケーションへのアクセスをユーザーに許可する前に事前認証を行い、シングル サインオン (SSO) を実行するために必要なユーザー識別子情報を取得できます。
- **条件付きアクセスの要件**: イントラネット アクセスにアプリケーション プロキシを使用することはお勧めしません。ユーザーに影響を与える待機時間が長くなるためです。 インターネットからのリモート アクセスには、事前認証ポリシーと条件付きアクセス ポリシーでアプリケーション プロキシを使用します。 イントラネットの使用のために条件付きアクセスを提供するアプローチは、アプリケーションが Microsoft Entra ID を使用して直接認証を行えるようにアプリケーションを最新化することです。 詳細については、「 [アプリケーションを Microsoft Entra ID に移行するためのリソース」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migration-resources)参照してください。
- **サービスの制限**: 個々のテナントによるリソースの過剰な消費から保護するために、アプリケーションとテナントごとに調整制限が設定されています。 これらの制限を確認するには、 [Microsoft Entra サービスの制限と制限](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-service-limits-restrictions)を参照してください。 これらの調整制限は、一般的な使用ボリュームを超えるベンチマークに基づいており、ほとんどのデプロイに十分なバッファーを提供します。
- **パブリック証明書**: カスタム ドメイン名を使用している場合は、TLS 証明書を調達する必要があります。 組織の要件によっては、証明書の取得に時間がかかる場合があり、できるだけ早くプロセスを開始することをお勧めします。 Microsoft Entra アプリケーション プロキシは、標準、 [ワイルドカード](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-wildcard)、または SAN ベースの証明書をサポートしています。 詳細については、「 [Microsoft Entra アプリケーション プロキシを使用してカスタム ドメインを構成する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)参照してください。
- **ドメイン要件**: シングル サインオンに Kerberos 制約付き委任 (KCD) を使用するには、コネクタ サーバーとアプリケーション サーバーの両方がドメインに参加していて、同じドメインまたは信頼されたドメインに存在することを確認します。 コネクタ サービスはローカル システム アカウントで実行され、カスタム ID を使用しないでください。 詳細については、 [シングル サインオンの KCD を](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-sso-with-kcd)参照してください。
- **URL の DNS レコード**

    - アプリケーション プロキシでカスタム ドメインを使用する前に、パブリック ドメイン ネーム システム (DNS) で CNAME レコードを作成し、クライアントがカスタム定義の外部 URL を定義済みのアプリケーション プロキシ アドレスに解決できるようにする必要があります。 カスタム ドメインを使用するアプリケーションの CNAME レコードを作成しないと、リモート ユーザーがアプリケーションに接続できなくなります。 CNAME レコードを追加するために必要な手順は DNS プロバイダーごとに異なる可能性があるため、[Microsoft Entra 管理センターを使用して DNS レコードおよびレコード セットを管理する](https://learn.microsoft.com/ja-jp/azure/dns/dns-operations-recordsets-portal)方法を確認してください。
    - 同様に、コネクタ ホストは、発行されるアプリケーションの内部 URL を解決できる必要があります。
- **管理者権限とロール**

    - **コネクタのインストール**では、インストール先の Windows サーバーに対するローカル管理者権限が必要です。 コネクタ インスタンスを Microsoft Entra テナントに対する認証と登録を行うためには、最低限 "アプリケーション管理者" ロールも必要となります。
    - **アプリケーションの発行と管理**には、"*アプリケーション管理者*" ロールが必要です。 アプリケーション管理者は、登録、SSO 設定、ユーザー、グループの割り当てとライセンス、アプリケーション プロキシ設定、同意など、ディレクトリ内のすべてのアプリケーションを管理できます。 条件付きアクセスの管理権限は付与されません。 *クラウド アプリケーション管理者*ロールには、アプリケーション プロキシ設定の管理が許可されない点を除き、アプリケーション管理者のすべての機能があります。
- **ライセンス**: アプリケーション プロキシは、Microsoft Entra ID P1 または P2 サブスクリプションを通じて利用できます。 ライセンス オプションと機能の完全な一覧については、[Microsoft Entra の価格ページ](https://www.microsoft.com/security/business/microsoft-entra-pricing)を参照してください。

#### 移行用のアプリケーションを検出する

以下の情報を収集して、アプリケーション プロキシ経由で発行されているすべてのスコープ内アプリケーションのインベントリをまとめます。

| 情報の種類 | 収集する情報 |
| --- | --- |
| サービスの種類 | 次に例を示します。SharePoint、SAP、CRM、カスタム Web アプリケーション、API |
| アプリケーション プラットフォーム | 例: Windows インターネット インフォメーション サービス (IIS)、Apache on Linux、Tomcat、NGINX |
| ドメイン メンバーシップ | Web サーバーの完全修飾ドメイン名 (FQDN) |
| アプリケーションの場所 | インフラストラクチャ内で Web サーバーまたはファームがある場所 |
| 内部アクセス | 内部でアプリケーションにアクセスするときに使用される正確な URL。  ファームでどの種類の負荷分散が使用されているか。  アプリケーションがそれ自体以外のソースのコンテンツを描画するかどうか。 アプリケーションが WebSocket で動作するかどうかを決定します。 |
| 外部アクセス | 外部からアプリケーションが既に公開されている可能性があるベンダー ソリューション。  外部アクセス用に使用する URL です。 SharePoint の場合は、SharePoint 代替アクセス マッピングの [ガイダンスに従って代替アクセス マッピングが](https://learn.microsoft.com/ja-jp/SharePoint/administration/configure-alternate-access-mappings)構成されていることを確認します。 そうでない場合は、外部 URL を定義する必要があります。 |
| 公開証明書 | カスタム ドメインを使用している場合は、対応するサブジェクト名の証明書を取得します。 証明書が存在する場合は、シリアル番号と取得可能な場所を書き留めます。 |
| 認証の種類 | Basic、Windows 統合認証、フォーム ベース、ヘッダー ベース、要求など、アプリケーションでサポートされる認証の種類。 特定のドメイン アカウントで実行するようにアプリケーションが構成されている場合は、サービス アカウントの完全修飾ドメイン名 (FQDN) をメモします。 SAML ベースの場合は、識別子と応答 URL。  ヘッダーベースの場合は、ベンダー ソリューションと認証の種類を処理するための特定の要件。 |
| コネクタ グループ名 | バックエンド アプリケーションにコンジットと SSO を提供するように指定されたコネクタのグループの論理名。 |
| ユーザー/グループのアクセス | アプリケーションへの外部アクセスが許可されているユーザーまたはユーザー グループ。 |
| その他の要件 | アプリケーションの発行に組み込む必要があるリモート アクセスまたはセキュリティ要件に注意してください。 |

[アプリケーション インベントリ スプレッドシート](https://aka.ms/appdiscovery)をダウンロードして、アプリのインベントリを作成できます。

#### 組織の要件を定義する

以下に示すのは、組織のビジネス要件を定義する必要がある区分です。 各領域には、要件の例が含まれています。

**アクセス**

- ドメイン参加または Microsoft Entra 参加デバイスを持つリモート ユーザーは、シームレス シングル サインオン (SSO) を使用して発行済みプリケーションに安全にアクセスできます。
- 承認された個人デバイスを持つリモート ユーザーは、MFA に登録され、認証方法として携帯電話に Microsoft Authenticator アプリを登録していれば、公開されたアプリケーションに安全にアクセスできます。

**ガバナンス**

- 管理者は、アプリケーション プロキシを介して発行されたアプリケーションに対するユーザー割り当てのライフサイクルを定義して監視できます。

**セキュリティ**

- グループ メンバーシップで、または個別にアプリケーションに割り当てられたユーザーのみが、それらのアプリケーションにアクセスできます。

**パフォーマンス**

- 内部ネットワークからアプリケーションにアクセスする場合と比べて、アプリケーションのパフォーマンスが低下しません。

**ユーザー エクスペリエンス**

- ユーザーは、任意のデバイス プラットフォームで使い慣れた会社の URL を使用することにより、アプリケーションにアクセスする方法を認識します。

**監査**

- 管理者は、ユーザー アクセス アクティビティを監査できます。

#### パイロットのベスト プラクティス

シングル サインオン (SSO) を使用して 1 つのアプリケーションにリモート アクセスを完全に委託するために必要な時間と労力を決定します。 このためには、初期段階の発見、公開、および全体的なテストを検討するパイロットプロジェクトを実施します。 統合 Windows 認証 (IWA) 用に既に構成されている単純な IIS ベースの Web アプリケーションを使用すると、セットアップでリモート アクセスと SSO を正常にパイロットするために最小限の労力が必要であるため、ベースラインを確立するのに役立ちます。

設計要素を以下のようにすると、パイロット実装の成功率を運用環境テナントで直接向上させることができます。

**コネクタの管理**:

コネクタは、オンプレミス コンジットをアプリケーションに提供するために重要な役割を果たします。 **既定**コネクタ グループの使用が、運用環境で動作させる前の発行済みアプリケーションの最初のパイロット テストに適しています。 この後、テストが成功したアプリケーションを運用環境のコネクタ グループに移すことができます。

**アプリケーション管理**:

従業員は、使い慣れた関連性の高い外部 URL を覚えている可能性が最も高いです。 定義済みの msappproxy.net または onmicrosoft.com サフィックスを使用してアプリケーションを発行することは避けてください。 代わりに、*intranet.&lt;customers\_domain&gt;.com* のように、使い慣れた最上位レベルの確認済みドメインの前に論理的なホスト名を指定します。

起動アイコンを Azure MyApps ポータルでは表示せず、パイロット アプリケーションのアイコンの表示をパイロット グループのみに制限します。 運用環境の準備ができたら、アプリを対象ユーザー向けにスコープ設定できます。同じ運用前テナント内か、運用テナントにアプリケーションを発行することで実施できます。

**シングル サインオンの設定**:一部の SSO 設定には、設定に時間がかかる固有の依存関係があるため、依存関係を事前に対処しておくことで、変更制御の遅延を回避します。 このプロセスには、Kerberos 制約付き委任 (KCD) を使用して SSO を実行するためのドメイン参加コネクタ ホストと、その他の時間のかかるアクティビティの処理が含まれます。

**コネクタ ホストとターゲット アプリケーションの間の TLS**:セキュリティは非常に重要であるため、コネクタ ホストとターゲット アプリケーション間の TLS は常に使用する必要があります。 Web アプリケーションでフォームベース認証 (FBA) が構成されている場合は、ユーザー資格情報が効率よくクリア テキストで送信されるため特に当てはまります。

**段階的な実装と手順ごとのテスト** アプリケーションの発行後に基本的な機能テストを実施して、すべてのユーザーとビジネス要件が満たされていることを確認します。

事前認証が無効になっている Web アプリケーションへの一般的なアクセスをテストして検証します。 成功した場合は、事前認証を有効にして、ユーザーとグループを割り当てます。 次に、アクセスをテストして検証します。 次に、アプリケーションの SSO メソッドを追加し、もう一度テストしてアクセスを検証します。 最後に、必要に応じて条件付きアクセスと MFA ポリシーを適用します。 アクセスをテストおよび検証します。

**トラブルシューティング ツール**: コネクタ ホスト上のブラウザーから発行されたアプリケーションへのアクセスを直接確認して、トラブルシューティングを開始します。 アプリケーションが期待どおりに動作することを確認します。 単一コネクタの使用や SSO の無効化など、問題を分離するためにセットアップを簡略化します。 Telerik の Fiddler などのツールは、iOS や Android などのモバイル プラットフォームを含むトラフィックをトレースすることで、アクセスやコンテンツの問題をデバッグするのに役立ちます。 詳細については、[トラブルシューティング ガイド](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-troubleshoot)を参照してください。

### ソリューションを実装する

#### アプリケーション プロキシをデプロイする

アプリケーション プロキシをデプロイする手順については、 [リモート アクセス用のオンプレミス アプリケーションを追加するためのチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)で説明します。 インストールが正常に終了しない場合は、ポータルで **[アプリケーション プロキシのトラブルシューティング]** を選ぶか、[アプリケーション プロキシ エージェント コネクタのインストールに関する問題](https://learn.microsoft.com/ja-jp/entra/global-secure-access/troubleshoot-connectors)のトラブルシューティング ガイドを使ってください。

#### アプリケーション プロキシを介してアプリケーションを発行する

アプリケーションの発行では、すべての前提条件を満たしていること、およびアプリケーション プロキシ ページに登録済みでアクティブなコネクタがいくつか表示されていることを前提としています。

[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview) を使用してアプリケーションを発行することもできます。

アプリケーションを発行するときに従うベスト プラクティス:

- **コネクタ グループの使用**: それぞれのアプリケーションを発行するために指定されたコネクタ グループを割り当てます。 高可用性とスケールを提供するには、各コネクタ グループに少なくとも 2 つのコネクタが必要です。 任意の時点でマシンにサービスを提供する必要がある場合は、3 つのコネクタが最適です。 さらに、[Microsoft Entra プライベート ネットワーク コネクタ グループについて](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connector-groups)に関する記事を参照して、コネクタ グループを使用してネットワークまたは場所によってコネクタをセグメント化する方法を確認してください。
- **バックエンド アプリケーションのタイムアウトの設定**: この設定は、アプリケーションでクライアント トランザクションの処理に 75 秒以上かかる場合に役立ちます。 たとえば、クライアントがデータベースのフロントエンドとして機能する Web アプリケーションにクエリを送信する場合などです。 フロントエンドはクエリをバックエンド データベース サーバーに送信し、応答を待機しますが、応答を受信するまでに、会話のクライアント側はタイムアウトします。タイムアウトを Long に設定すると、長いトランザクションが完了するまでに 180 秒かかります。
- **適切な Cookie の種類を使用する**

    - **HTTP-Only Cookie**: アプリケーション プロキシを set-cookie HTTP 応答ヘッダーに HTTPOnly フラグを含めることで、追加のセキュリティを提供します。 この設定は、クロスサイト スクリプティング (XSS) などの悪用を軽減するのに役立ちます。 セッション Cookie へのアクセスが必要なクライアント/ユーザー エージェントの場合は、[ **いいえ** ] に設定したままにします。 たとえば、アプリケーション プロキシ経由で発行されたリモート デスクトップ ゲートウェイに接続する RDP/MTSC クライアントです。
    - **セキュリティで保護された Cookie**: Secure 属性を使用して Cookie が設定されている場合、ユーザー エージェント (クライアント側アプリ) は、要求が TLS で保護されたチャネル経由で送信される場合にのみ、HTTP 要求に Cookie を含めます。 この設定は、クリア テキスト チャネルで Cookie が侵害されるリスクを軽減するのに役立ちます。そのため、有効にする必要があります。
    - **永続 Cookie**: アプリケーション プロキシのセッション Cookie を、期限切れになるか削除されるまで有効にしておくことで、ブラウザーをクローズした後でも永続化できます。 Office などのリッチ アプリケーションが、発行された Web アプリケーション内のドキュメントにアクセスし、ユーザーが認証用に再指定されないシナリオに使用されます。 ただし、永続的な Cookie を使用すると、他の補正制御で使用されていない場合、最終的にサービスが不正アクセスのリスクにさらされる可能性があるため、注意して有効にしてください。 この設定は、プロセス間で Cookie を共有できない古いアプリケーションにのみ使用する必要があります。 設定を使用する代わりに、プロセス間での Cookie の共有を処理するようにアプリケーションを更新することをお勧めします。
- **ヘッダー内の URL を変換**する: 組織のパブリック名前空間 (分割 DNS とも呼ばれます) と一致するように内部 DNS を構成できないシナリオの設定を有効にします。 アプリケーションがクライアント要求で元のホスト ヘッダーを必要とする場合を除き、値は **[はい**] に設定したままにします。 代わりに、コネクタが、実際のトラフィックのルーティングに内部 URL の FQDN を使用し、ホストヘッダーとして外部 URL の FQDN を使用するようにします。 ほとんどの場合、リモートからアクセスしてもアプリケーションが通常どおり機能できるようになりますが、ユーザーは内外のURLが一致するという利点を享受できなくなります。
- **アプリケーション本文で URL を翻訳**する: そのアプリからのリンクをクライアントへの応答で翻訳する場合は、アプリのアプリケーション本文のリンク変換を有効にします。 この関数を有効にすると、アプリケーション プロキシが HTML および CSS 応答で見つけたすべての内部リンクをクライアントに変換するためのベスト エフォート試行が提供されます。 これは、ハードコーディングされた絶対リンクまたは NetBIOS shortname リンクをコンテンツに含むアプリ、または他のオンプレミス アプリケーションにリンクするコンテンツを含むアプリを発行する場合に便利です。

発行済みアプリが他の発行済みアプリにリンクするシナリオでは、各アプリケーションのリンクの変換を有効にして、各アプリ レベルでのユーザー エクスペリエンスを制御できるようにします。

たとえば、すべてが相互にリンクする、アプリケーション プロキシ経由で公開された 3 つのアプリケーション (福利厚生、経費、出張) に加えて、アプリケーション プロキシ経由で公開されていないフィードバックという 4 番目のアプリがあるとします。

[Image: リンク変換によって、公開されたアプリケーション コンテンツ内の内部 URL が外部 URL に変換される方法を示す図。] 特典アプリでリンク変換が有効になっている場合、Expenses アプリと Travel アプリへのリンクが外部 URL にリダイレクトされ、外部ユーザーがアクセスできるようになります。 ただし、これらのアプリでもリンク変換が有効になっていない限り、Expenses と Travel から Benefits へのリンクは機能しません。 フィードバック アプリのリンクは、外部 URL がないためリダイレクトされず、外部ユーザーが特典アプリを介してアクセスできなくなります。 詳細については、 [リンク変換とリダイレクトのオプションを](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-hard-coded-link-translation)参照してください。

#### アプリケーションにアクセスする

シナリオとスケーラビリティの要件に最も適したアプローチを選択して、アプリケーション プロキシが発行したリソースへのアクセスを管理します。 一般的な方法としては、Microsoft Entra Connect を介したオンプレミス グループの同期、ユーザー属性に基づく Microsoft Entra ID での動的グループの作成、リソース所有者が管理するセルフサービス グループの有効化、またはこれらの戦略の組み合わせなどがあります。 リンクされたリソースを調べて、各方法の利点を理解します。

アプリケーションへのユーザー アクセスを割り当てる最も簡単な方法は、発行されたアプリケーションの左側のウィンドウから **[ユーザーとグループ** ] に移動し、グループまたは個人を直接割り当てることです。

[Image: アプリケーション アクセス割り当ての [ユーザーの追加] ボタンが表示されている [ユーザーとグループ] ページ。]

また、ユーザーが現在メンバーになっていないグループを割り当て、セルフサービス オプションを構成することで、ユーザーがアプリケーションにセルフサービスでアクセスできるようにすることもできます。

[Image: アプリケーションのプロパティの [セルフサービス アプリケーション アクセス設定] ダイアログ。]

有効にした場合、ユーザーは MyApps ポータルにサインインしてアクセスを要求します。 これらは自動的に承認され、セルフサービス グループに追加されるか、指定された承認者からの承認が必要です。

ゲスト ユーザーを、[Microsoft Entra B2B を通じてアプリケーション プロキシ経由で発行された内部アプリケーションにアクセスするように招待する](https://learn.microsoft.com/ja-jp/entra/external-id/add-users-information-worker)こともできます。

通常は匿名でアクセス可能で、認証を必要としないオンプレミス アプリケーションの場合は、アプリケーションの **プロパティ**にあるオプションを無効にすることをお勧めします。

[Image: ユーザー割り当てが必要な切り替えを示すアプリケーションのプロパティ。]

[いいえ] に設定したままにすると、ユーザーはアクセス許可なしで Microsoft Entra アプリケーション プロキシ経由でオンプレミス アプリケーションにアクセスできます。そのため、注意して使用してください。

アプリケーションがいったん発行されると、外部 URL をブラウザーに入力するか [https://myapps.microsoft.com](https://myapps.microsoft.com/) のアイコンを使用してアクセスできます。

#### 事前認証を有効にする

外部 URL を介してアクセスして、アプリケーション プロキシ経由でアプリケーションにアクセス可能であることを確認します。

1. **Entra ID**&gt;**Enterprise アプリ**&gt;**すべてのアプリケーション**を参照し、管理するアプリを選択します。
2. **[アプリケーション プロキシ]** を選びます。
3. **[事前認証]** フィールドで、ドロップダウン リストを使用して **[Microsoft Entra ID]** を選択し、**[保存]** を選択します。 事前認証が有効になっている場合、Microsoft Entra ID は最初にユーザーを認証します。 シングル サインオンが設定されている場合、バックエンド アプリケーションはアクセス権を付与する前にユーザーも確認します。 事前認証モードをパススルーから Microsoft Entra ID に切り替えると、外部 URL が HTTPS で保護され、最初に HTTP を使用するすべてのアプリケーションが HTTPS 経由で動作するようになります。

#### シングル サインオンの有効化

SSO では、ユーザーが Microsoft Entra ID を使用して 1 回サインインできるようにすることで、ユーザー エクスペリエンスとセキュリティが向上します。 事前認証後、プライベート ネットワーク コネクタはユーザーのオンプレミス アプリケーションにサインインし、ユーザーが直接サインインしたかのように表示されます。

**パススルー** オプションを選択すると、ユーザーは Microsoft Entra ID に対して認証する必要は一切なく、発行済みアプリケーションにアクセスできます。

SSO を有効にするには、アプリケーションで Microsoft Entra ID を使用してユーザーを事前認証する必要があります。 事前認証がないと、SSO オプションは使用できません。

「[Microsoft Entra ID 内のアプリケーションへのシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)」を読むと、アプリケーションを構成するときに最適な SSO の手法の選択に役立ちます。

#### 他の種類のアプリケーションを操作する

Microsoft Entra アプリケーション プロキシは、 [Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-overview) を使用して構築されたアプリケーションをサポートします。 クライアント要求ヘッダーで Microsoft Entra ID トークンを使用してネイティブ クライアント アプリを処理し、ユーザーを事前認証します。

アプリケーション プロキシの使用可能な構成について説明します。 [ネイティブ クライアント アプリとモバイル クライアント アプリの発行](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-native-client-application)と[クレーム ベース アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-for-claims-aware-applications)を読みます。

#### 条件付きアクセスによるセキュリティの強化

アプリケーションのセキュリティでは、オンプレミスおよびクラウドでの複雑な脅威から保護して対応できる、一連の高度なセキュリティ機能が必要です。 条件付きアクセス ポリシーを使用して、場所、リスク、デバイスの種類、デバイスコンプライアンスなどの多くの条件に基づいてアプリケーションへのアクセスを制御します。 展開する可能性があるポリシーの例については、「[条件付きアクセス テンプレート](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policy-common)」を参照してください。

Microsoft Entra アプリケーション プロキシをサポートするために以下の機能を使用できます。

- ユーザーおよびロケーションベースの条件付きアクセス:[ロケーションベースの条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network)では、地理的な場所や IP アドレスに基づいてユーザー アクセスを制限することで機密データを保護します。
- デバイスベースの条件付きアクセス:[デバイスベースの条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)では、登録され承認された準拠デバイスのみが会社のデータにアクセスできます。
- アプリケーションベースの条件付きアクセス:会社のネットワークに接続していないときでも、仕事を中断するわけにはいきません。 [企業クラウドおよびオンプレミス アプリへのアクセスを保護](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-device-compliance)し、条件付きアクセスによる制御を管理します。
- リスクベースの条件付きアクセス:[リスクベースの条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)を使用し、悪意のあるハッカーからデータを保護しましょう。これらのポリシーは、オンプレミスかクラウドかにかかわらず、すべてのアプリとすべてのユーザーに適用できます。
- Microsoft Entra マイ アプリ: アプリケーション プロキシ サービスがデプロイされ、アプリケーションが安全に発行されて、ユーザーにすべてのアプリケーションを検出してアクセスするための単純なハブを提供します。 [マイ アプリ](https://aka.ms/AccessPanelDPDownload)を使用すると、ユーザーが新しいアプリやグループへのアクセスを要求したり、他のユーザーに代わってこれらのリソースへのアクセスを管理したりする機能などのセルフサービス機能を使用して生産性が向上します。

### 実装を管理する

#### 必要なロール

Microsoft は、Microsoft Entra ID を使用して必要なタスクを実行するための最小限の特権の付与の原則を提唱しています。 [使用可能なさまざまな Microsoft Entra ロールを確認](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) し、各ペルソナのニーズに対応するための適切なロールを選択します。 一部のロールは、デプロイの完了後に一時的に適用して削除する必要があります。

| ビジネス ロール | ビジネス タスク | Microsoft Entra ロール |
| --- | --- | --- |
| ヘルプ デスク管理者 | パスワードのリセット、更新トークンの無効化、サービスの正常性の確認などの基本的なユーザー サポート タスクを処理します。 | ヘルプデスク管理者 |
| ID 管理者 | Microsoft Entra サインイン レポートおよび監査ログを読み、アプリケーション プロキシ関連の問題をデバッグします。 | セキュリティ閲覧者 |
| アプリケーションの所有者 | エンタープライズ アプリケーション、アプリケーション登録、アプリケーション プロキシの設定の全側面を作成して管理します。 | アプリケーション管理者 |
| インフラストラクチャ管理者 | 証明書の更新所有者 | アプリケーション管理者 |

セキュリティで保護された情報またはリソースへのアクセス権を持つユーザーの数を最小限に抑えることで、悪意のあるアクターが不正アクセスを取得したり、承認されたユーザーが機密性の高いリソースに誤って影響を与えたりする可能性を減らすことができます。

管理アクセスを効果的に管理し、適切な監査を確保するには、 [Privileged Identity Management](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) で Just-In-Time (JIT) アクセスを使用します。 この方法では、必要な場合にのみ、Azure リソースと Microsoft Entra ID へのオンデマンド特権アクセスが提供されます。

#### レポートと監視

Microsoft Entra ID は、 [監査ログとレポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs?context=azure/active-directory/manage-apps/context/manage-apps-context)を使用して、組織のアプリケーションの使用状況と運用の正常性に関する分析情報を提供します。 アプリケーション プロキシを使用すると、Microsoft Entra 管理センターと Windows イベント ログからコネクタを簡単に監視できます。

##### アプリケーションの監査ログ

これらのログは、アプリケーション プロキシとデバイスで構成されたアプリケーションへのサインインと、アプリケーションにアクセスするユーザーに関する詳細情報を提供します。 [監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs?context=azure/active-directory/manage-apps/context/manage-apps-context)は Microsoft Entra 管理センター内にあり、エクスポート用は [Audit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit) にあります。 また、[使用状況と分析情報のレポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-usage-insights-report?context=azure/active-directory/manage-apps/context/manage-apps-context)もアプリケーションで利用できます。

##### プライベート ネットワーク コネクタの監視

コネクタとサービスは、高可用性のすべてのタスクに対処します。 Microsoft Entra 管理センターのアプリケーション プロキシのページから、コネクタの状態を監視できます。 コネクタのメンテナンスの詳細については、「 [Microsoft Entra プライベート ネットワーク コネクタ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors#maintenance)」を参照してください。

##### Windows イベント ログおよびパフォーマンス カウンター

コネクタには管理者ログとセッション ログがあります。 管理者ログには主要なイベントとそのエラーが含まれます。 セッション ログには、すべてのトランザクションとその処理の詳細が含まれます。 ログとカウンターは、Windows イベント ログにあります。 詳細については、「 [Microsoft Entra プライベート ネットワーク コネクタ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors)」を参照してください。 チュートリアルに従って [、Azure Monitor でイベント ログ データ ソースを構成します](https://learn.microsoft.com/ja-jp/azure/azure-monitor/agents/data-sources-windows-events)。

#### トラブルシューティングのガイドと手順

一般的な問題や、それらの問題を解決するためにエラー メッセージを[トラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-troubleshoot)する方法について学びます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/conceptual-sso-apps"} -->
## Microsoft Entra アプリケーション プロキシを使用したオンプレミス アプリに対する Security Assertion Markup Language (SAML) シングル サインオン (SSO) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/conceptual-sso-apps
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Microsoft Entra アプリケーション プロキシを介して発行されたオンプレミス アプリケーションの SAML ベースのシングル サインオンを構成します。 SAML 要求マッピングとリモート アクセス構成について説明します。

### 概要

Security Assertion Markup Language (SAML) 認証でセキュリティ保護されたオンプレミス アプリケーションにシングル サインオン (SSO) を提供します。 アプリケーション プロキシを使用して SAML ベースの SSO アプリケーションへのリモート アクセスを提供します。 SAML によるシングル サインオンでは、ユーザーの Microsoft Entra アカウントを使用して、Microsoft Entra がアプリケーションに対して認証を行います。 Microsoft Entra ID は、接続プロトコルを通してアプリケーションにシングル サインオンの情報を伝達します。 また、SAML 要求で定義するルールに基づいて、ユーザーを特定のアプリケーション ロールにマップできます。 SAML SSO に加えてアプリケーション プロキシを有効にすると、ユーザーには、アプリケーションへの外部アクセスと、シームレスな SSO エクスペリエンスが提供されます。

アプリケーションでは、**Microsoft Entra ID** によって発行された SAML トークンを使用できる必要があります。 この構成は、オンプレミスの ID プロバイダーを使用するアプリケーションには適用されません。 これらのシナリオについては、アプリケーションを [Microsoft Entra ID に移行するためのリソースを確認してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migration-resources)。

アプリケーション プロキシを使う SAML SSO は、SAML トークンの暗号化機能でも動作します。 詳細については、｢[Microsoft Entra の SAML トークン暗号化を構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/howto-saml-token-encryption)」を参照してください。

このプロトコル図は、サービス プロバイダーによって開始される (SP によって開始される) フローと ID プロバイダーによって開始される (IdP によって開始される) フローの両方に対するシングル サインオン シーケンスを示しています。 アプリケーション プロキシは、オンプレミス アプリケーションとの間で SAML 要求と応答をキャッシュすることによって、SAML SSO と連携します。

[Image: 図には、S P によって開始されるシングル サインオンのアプリケーション、アプリケーション プロキシ、クライアント、および Microsoft Entra ID の相互作用が示されています。]

[Image: 図には、I d P によって開始されるシングル サインオンのアプリケーション、アプリケーション プロキシ、クライアント、および Microsoft Entra ID の相互作用が示されています。]

### アプリケーションの作成と SAML SSO の設定

1. Microsoft Entra 管理センターで、**[Microsoft Entra ID] &gt; [エンタープライズ アプリケーション]** の順に選び、**[新しいアプリケーション]** を選びます。
2. 新しいアプリケーションの表示名を入力します。 **ギャラリーに見つからない他のアプリケーションを統合するを**選択し、[**作成**] を選択します。
3. アプリの **[概要]** ページで、 **[シングル サインオン]** を選択します。
4. シングル サインオンの方法として、 **[SAML]** を選択します。
5. まず、企業ネットワーク上で動作するように SAML SSO を設定します。 アプリケーションの SAML ベースの認証を [構成するには、「SAML ベースのシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol) を構成する」の基本的な SAML 構成セクションを参照してください。
6. アプリケーションに少なくとも 1 人のユーザーを追加し、テスト アカウントにアプリケーションへのアクセス権があることを確認します。 企業ネットワークに接続しているときに、テスト アカウントを使用して、アプリケーションに対してシングル サインオンが機能することを確認します。

    注意

    アプリケーション プロキシを設定した後、戻って SAML **応答 URL** を更新します。

### アプリケーション プロキシを使ってオンプレミスのアプリケーションを発行する

オンプレミスのアプリケーションに SSO を提供する前に、アプリケーション プロキシを有効にして、コネクタをインストールします。 詳細については、 [オンプレミス環境の準備、コネクタのインストールと登録、コネクタのテストを行う方法を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)。 コネクタを設定したら、次の手順に従って、アプリケーション プロキシを使用して新しいアプリケーションを発行します。

1. Microsoft Entra 管理センターでアプリケーションを開いた状態で、**[アプリケーション プロキシ]** を選びます。 アプリケーションの **[内部 URL]** を指定します。 カスタム ドメインを使用している場合は、アプリケーションのトランスポート層セキュリティ (TLS) 証明書もアップロードする必要があります。

    注意

    ベスト プラクティスとして、最適化されたユーザー エクスペリエンスのため、可能な限り、カスタム ドメインを使用します。 詳しくは、「[Microsoft Entra アプリケーション プロキシでのカスタム ドメインの使用](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)」を参照してください。
2. アプリケーションの**事前認証**方法として **[Microsoft Entra ID]** を選択します。
3. アプリケーションの**外部 URL** をコピーします。 SAML 構成を完了するには、この URL が必要です。
4. テスト アカウントを使って、**外部 URL** によってアプリケーションを開き、アプリケーション プロキシが正しく設定されていることを確認します。 問題がある場合は、「[アプリケーション プロキシの問題とエラー メッセージのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-troubleshoot)」をご覧ください。

### SAML 構成の更新

1. Microsoft Entra 管理センターでアプリケーションを開いた状態で、**[シングル サインオン]** を選びます。
2. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成]** 見出しに移動し、その **編集** アイコン (鉛筆) を選択します。 アプリケーション プロキシ内で構成した**外部 URL** が、**[識別子]**、**[応答 URL]**、**[ログアウト URL]** の各フィールドに入力されていることを確認します。 これらの URL は、アプリケーション プロキシが正常に動作するために必要です。
3. 前に構成した **応答 URL を** 編集して、アプリケーション プロキシ経由でインターネット上でそのドメインに到達できるようにします。 たとえば、**外部 URL** が `https://contosotravel-f128.msappproxy.net` で、元の**応答 URL** が `https://contosotravel.com/acs` の場合、元の**応答 URL** を `https://contosotravel-f128.msappproxy.net/acs` に更新する必要があります。
4. 更新された**応答 URL** の横にあるチェックボックスをオンにして、既定値としてマークします。

    - 既定値として必須の**応答URL**をマークした後、内部URLを使用して以前に構成した**応答URL**を削除することもできます。
    - SP によって開始されたフローの場合、バックエンド アプリケーションで認証トークンを受け取るための適切な**応答 URL** または Assertion Consumer Service URL が指定されていることを確認します。

    注意

    バックエンド アプリケーションで**応答 URL** が内部 URL である必要がある場合は、[カスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)を使用して内部と外部の URL を一致させるか、マイ アプリのセキュリティで保護されたサインイン拡張機能をユーザーのデバイスにインストールする必要があります。 この拡張機能は、適切なアプリケーション プロキシ サービスに自動的にリダイレクトされます。 拡張機能のインストールについては、「[マイ アプリによるセキュリティで保護されたサインイン拡張機能](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510#download-and-install-the-my-apps-secure-sign-in-extension)」をご覧ください。

### アプリをテストする

アプリが稼働しています。 アプリをテストするには:

1. ブラウザーを開き、アプリを発行したときに作成した**外部 URL** に移動します。
2. サインインには、アプリに割り当てたテスト アカウントを使用します。 アプリケーションを読み込み、アプリケーションにシングル サインオンできるようになります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/how-to-configure-custom-domain"} -->
## Microsoft Entra アプリケーション プロキシのカスタム ドメイン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: 独自のドメイン名を使用するように、Microsoft Entra アプリケーション プロキシでカスタム ドメインを構成および管理します。

### 概要

Microsoft Entra アプリケーション プロキシ経由でアプリケーションを公開する場合、ユーザー用の外部 URL を作成します。 この URL には、既定のドメインである *`yourtenant.msappproxy.net`* が与えられます。 たとえば、*Contoso* という名前のテナントの *Expenses* という名前のアプリを公開する場合、外部 URL は *`https://expenses-contoso.msappproxy.net`* です。 *`msappproxy.net`* の代わりに独自のドメイン名を使用する場合は、アプリケーションのカスタム ドメインを構成できます。

### カスタム ドメインの利点

可能な場合は常に、アプリのカスタム ドメインを設定することをお勧めします。 カスタム ドメインを使用するいくつかの理由として、次のものがあります。

- アプリ間のリンクが企業ネットワークの外部でも機能します。 カスタム ドメインがないと、アプリでアプリケーション プロキシの外部のターゲットへの内部リンクをハードコーディングし、そのリンクを外部から解決できない場合、それらのリンクは中断されます。 内部 URL と外部 URL が同じであれば、この問題は回避されます。 カスタム ドメインを使用できない場合は、この問題に対処するための他の方法について「[Microsoft Entra アプリケーション プロキシで公開されているアプリのハードコードされたリンクをリダイレクトする](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-hard-coded-link-translation)」を参照してください。
- ユーザーはネットワークの内部と外部の両方から同じ URL でアプリにアクセスするため、ユーザーのエクスペリエンスが簡単になります。 異なる内部 URL と外部 URL を覚えたり、自分の現在の場所を追跡したりする必要はありません。
- ブランドを制御し、必要な URL を作成することができます。 ユーザーは *`msappproxy.net`* の代わりに、なじみのある名前を参照または使用できるため、カスタム ドメインはユーザーの信頼を構築するために役立ちます。
- 一部の構成は、カスタム ドメインでのみ機能します。 たとえば、Security Assertion Markup Language (SAML) を使用するアプリにはカスタム ドメインが必要です。 ACTIVE Directory フェデレーション サービス (AD FS) を使用しているが、WS-Federation を使用できない場合は SAML を使用します。 詳細については、[アプリケーション プロキシでの要求に対応するアプリの使用](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-for-claims-aware-applications)に関するページを参照してください。

内部 URL と外部 URL を一致させることができない場合、カスタム ドメインの使用はそれほど重要ではありません。 ただし、引き続き他の利点を利用できます。

### DNS の構成オプション

ドメイン ネーム システム (DNS) 構成を設定するには、要件に応じていくつかのオプションがあります。

#### 内部 URL と外部 URL は同じですが、動作は異なります

内部ユーザーがアプリケーション プロキシ経由で転送されないようにしたい場合は、*スプリット ブレイン DNS* を設定できます。 分割 DNS インフラストラクチャでは、ホストの場所に基づいて名前解決が転送されます。 内部ホストを内部ドメイン ネーム サーバーに、外部ホストを外部ドメイン ネーム サーバーに転送します。

[Image: 内部ホストがオンプレミス サーバーにルーティングされ、外部ホストがアプリケーション プロキシ経由でルーティングされるスプリットブレイン DNS アーキテクチャを示す図。]

#### 異なる内部 URL と外部 URL

内部 URL と外部 URL が異なる場合は、スプリットブレイン動作を構成しないでください。 URL によってユーザー ルーティングが決まります。 この場合は、外部 DNS のみを変更し、外部 URL をアプリケーション プロキシ エンドポイントにルーティングします。

外部 URL のカスタム ドメインを選択すると、外部 DNS プロバイダーに追加する必要のある CNAME エントリが情報バーに表示されます。 この情報は、アプリの **[アプリケーション プロキシ]** ページに移動することによっていつでも表示できます。

### カスタム ドメインを設定して使用する

カスタム ドメインを使用するようにオンプレミス アプリを構成するには、検証済みの Microsoft Entra カスタム ドメイン、カスタム ドメインの個人情報交換 (PFX) 証明書、および構成するオンプレミス アプリが必要です。

重要

カスタム ドメインを *`msappproxy.net`* ドメインにリダイレクトする DNS レコードを維持する責任があります。 アプリケーションまたはテナントを後から削除する場合は、使わなくなった DNS レコードが悪用されないよう、それに関連付けられた アプリケーション プロキシ の DNS レコードを削除します。

#### カスタム ドメインを作成して検証する

カスタム ドメインを作成して検証するには:

1. [アプリケーション管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)にサインインします。
2. **Entra ID**&gt;**ドメイン名**を参照します。
3. **[カスタム ドメインの追加]** を選択します。
4. カスタム ドメイン名を入力し、 **[ドメインの追加]** を選択します。
5. ドメイン ページで、ドメインの TXT レコード情報をコピーします。
6. ドメイン レジストラーに移動し、コピーされた DNS 情報に基づいて、ドメインの新しい TXT レコードを作成します。
7. ドメインを登録した後、Microsoft Entra ID のドメインのページで **[確認]** を選びます。 ドメインの状態が **確認されたら**、アプリケーション プロキシを含むすべての Microsoft Entra 構成でドメインを使用できます。

詳細な手順については、[Microsoft Entra 管理センターを使ったカスタム ドメイン名の追加](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)に関する記事を参照してください。

#### カスタム ドメインを使用するようにアプリを構成する

カスタム ドメインを使用してアプリケーション プロキシ経由でアプリを公開するには:

1. 新しいアプリの場合は、Microsoft Entra 管理センターで、 **Entra ID**&gt;**Enterprise アプリケーション**&gt;**アプリケーション プロキシ**を参照します。
2. **[新しいアプリケーション]** を選択します。 **[オンプレミスのアプリケーション]** セクションで、 **[オンプレミスのアプリケーションの追加]** を選択します。

    既に **[エンタープライズ アプリケーション]** にあるアプリの場合は、そのアプリを一覧から選択してから、左側のナビゲーションの **[アプリケーション プロキシ]** を選択します。
3. 独自のオンプレミス アプリケーションを追加する場合は、[アプリケーション プロキシの設定] ページで、**名前**を入力します。
4. [ **内部 URL** ] フィールドに、アプリの内部 URL を入力します。
5. [ **外部 URL** ] フィールドで、一覧をドロップダウンし、使用するカスタム ドメインを選択します。
6. **[追加]** を選択します。

    [Image: カスタム ドメイン フィールドと証明書の設定を示すアプリケーション プロキシの構成ページ。]
7. ドメインに既に証明書が存在する場合は、 **[証明書]** フィールドにその証明書の情報が表示されます。 それ以外の場合は、 **[証明書]** フィールドを選択します。

    [Image: アプリケーション プロキシ設定のアップロード オプションを含む証明書フィールド。]
8. **[SSL 証明書]** ページで、PFX 証明書ファイルを参照して選択します。 証明書のパスワードを入力し、 **[証明書のアップロード]** を選択します。 証明書の詳細については、「カスタム ドメインの証明書」のセクションを参照してください。 証明書が有効でない場合、またはパスワードに問題がある場合は、エラー メッセージが表示されます。 [アプリケーション プロキシに関してよく寄せられる質問](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-faq)のページに、試すことができるトラブルシューティング手順がいくつかあります。

    [Image: 証明書ファイルの選択とパスワードのフィールドを含む SSL 証明書のアップロード ページ。]

    ヒント

    カスタム ドメインの証明書は 1 回だけアップロードする必要があります。 その後、他のアプリのカスタム ドメインを使用すると、アップロードされた証明書が自動的に適用されます。
9. 証明書を追加した場合は、 **[アプリケーション プロキシ]** ページで **[保存]** を選択します。
10. **[アプリケーション プロキシ]** ページの情報バーで、DNS ゾーンに追加する必要のある CNAME エントリをメモします。

    [Image: DNS ゾーンの構成に必要な CNAME エントリを表示する情報バー。]
11. [Microsoft Entra 管理センターを使った DNS レコードとレコード セットの管理](https://learn.microsoft.com/ja-jp/azure/dns/dns-operations-recordsets-portal)に関する記事の手順に従って、Azure DNS で新しい外部 URL を *`msappproxy.net`* ドメインにリダイレクトする DNS レコードを追加します。 別の DNS プロバイダーが使用されている場合は、ベンダーに連絡して手順を確認してください。

    重要

    *`msappproxy.net`* ドメインを指す CNAME レコードが適切に使用されていることを確認します。 レコードは静的ではなく、サービスの回復性に影響を与える可能性があるため、レコードを IP アドレスまたはサーバー DNS 名にポイントしないでください。

    注

    この手順で作成する CNAME レコードは、アプリケーションの外部 URL ホスト名 (たとえば、 `expenses.contoso.com`) 用です。これは、手順 6 でドメイン検証中に作成した TXT レコード ( `contoso.com` など) とは異なります。 これらのレコードは DNS 名が異なるため、互いに競合したり [、RFC 1912](https://www.ietf.org/rfc/rfc1912.txt) と競合したりすることはありません。これにより、CNAME が同じ名前の他のレコード型と共存することが禁止されます。
12. DNS レコードが正しく構成されていることを確認するには、nslookup コマンドを使用して、外部 URL に到達可能であり、 *`msappproxy.net`* ドメインがエイリアスとして表示されていることを確認します。

これで、アプリケーションはカスタム ドメインを使用するように設定されました。 テストやリリースの前に、このアプリケーションに必ずユーザーを割り当ててください。

アプリのドメインを変更するには、そのアプリの **[アプリケーション プロキシ]** ページの **[外部 URL]** で、ドロップダウン リストから別のドメインを選択します。 必要に応じて、更新されたドメインの証明書をアップロードし、DNS レコードを更新します。 必要なカスタム ドメインが **[外部 URL]** のドロップダウン リストに表示されない場合は、そのドメインが検証されていない可能性があります。

アプリケーション プロキシの詳細な手順については、「[チュートリアル: Microsoft Entra ID のアプリケーション プロキシを使用してリモート アクセスするためのオンプレミス アプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)」を参照してください。

### カスタム ドメインの証明書

証明書によって、カスタム ドメインのセキュリティで保護されたトランスポート層セキュリティ (TLS) 接続が作成されます。

#### 証明書の形式

PFX 証明書を使用して、必要なすべての中間証明書が含まれていることを確認する必要があります。 証明書には秘密キーが含まれている必要があります。

Subject Alternative Name (SAN) などの最も一般的な署名方法がサポートされています。

ワイルドカード証明書は、そのワイルドカードが外部 URL に一致する限り使用できます。 [ワイルドカード アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-wildcard)にはワイルドカード証明書を使用する必要があります。 証明書を使用してサブドメインにもアクセスする場合は、同じ証明書内にサブジェクトの別名としてサブドメイン ワイルドカードを追加する必要があります。 たとえば、サブジェクトの別名として  を追加しない限り、*\*.adventure-works.com* の証明書は *`*.apps.adventure-works.com`* に対して機能しません。

クライアント デバイスに証明書チェーンがインストールされている場合は、独自の公開キー インフラストラクチャ (PKI) によって発行された証明書を使用できます。 Microsoft Intune では、これらの証明書をマネージド デバイスにデプロイできます。 マネージド デバイス以外の場合は、これらの証明書を手動でインストールする必要があります。

プライベート ルート CA もクライアント マシンにプッシュする必要があるため、プライベート ルート証明機関 (CA) の使用は避けてください。これにより、多くの課題が発生する可能性があります。

#### 証明書の管理

すべての証明書は、個々のアプリケーションのページを通して管理されます。 **[証明書]** フィールドにアクセスするには、アプリケーションの **[アプリケーション プロキシ]** ページに移動します。

証明書をアップロードする場合、 **新しい** アプリは証明書を使用するように構成されている限り、証明書を使用します。 ただし、証明書をアップロードしたときに既にあったアプリについては、その証明書をもう一度アップロードする必要があります。

証明書の期限が切れると、別の証明書をアップロードするよう指示する警告が表示されます。 証明書が失効した場合、アプリにアクセスするときにユーザーにセキュリティ警告が表示されることがあります。 アプリの証明書を更新するには、アプリの **[アプリケーション プロキシ]** ページに移動し、 **[証明書]** を選択して新しい証明書をアップロードします。 他のアプリによって使用されていない古い証明書は自動的に削除されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/how-to-configure-sso"} -->
## アプリケーション プロキシを使用したオンプレミス アプリでのシングル サインオンについて説明します - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-sso
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Microsoft Entra アプリケーション プロキシを介して発行されたオンプレミス アプリのシングル サインオンを構成します。 パスワードベース、SAML、Kerberos、ヘッダーベースの SSO オプションについて説明します。

### 概要

シングル サインオン (SSO) を使用すると、ユーザーは複数回認証することなくアプリケーションにアクセスできます。 認証はクラウドで Microsoft Entra ID に対して行われ、サービスまたはコネクタはユーザーを偽装して、アプリケーションからの追加の認証チャレンジを完了します。

### シングル サインオンを構成する方法

SSO を構成するには、まず、アプリケーションが Microsoft Entra ID を使用して事前認証用に構成されていることを確認します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. 右上隅で自分のユーザー名を選択します。 アプリケーション プロキシを使うディレクトリにサインインしていることを確認します。 ディレクトリを変更する必要がある場合は、[ **ディレクトリの切り替え** ] を選択し、アプリケーション プロキシを使用するディレクトリを選択します。
3. **Entra ID**&gt;**Enterprise apps**&gt;**Application プロキシ**まで移動します。

**[事前認証**] フィールドを探し、設定されていることを確認します。

事前認証方法の詳細については、「 [Microsoft Entra ID でアプリケーション プロキシを使用してリモート アクセスするためのオンプレミス アプリケーションを追加](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)する」の手順 4 を参照してください。

[Image: Microsoft Entra 管理センターの事前認証方法を示すスクリーンショット。]

### アプリケーション プロキシ アプリケーションのシングル サインオン モードを構成する

具体的なシングル サインオンの種類を構成します。 サインオン方法は、バックエンド アプリケーションで使用される認証の種類に基づいて分類されます。 アプリケーション プロキシ アプリケーションでは、次の 4 種類のサインオンがサポートされています。

- **パスワードベースのサインオン:** パスワード ベースのサインオンは、ユーザー名フィールドとパスワード フィールドを使用してサインインする任意のアプリケーションに使用できます。 構成手順については、「 [Microsoft Entra ギャラリー アプリケーションのパスワード シングル サインオンの構成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-password-single-sign-on-non-gallery-applications)」を参照してください。
- **統合 Windows 認証:** 統合 Windows 認証 (IWA) を使用するアプリケーションの場合、Kerberos の制約付き委任 (KCD) を使用してシングル サインオンが有効になります。 この方法では、Active Directory でユーザーを偽装し、ユーザーに代わってトークンを送受信するためのアクセス許可が、プライベート ネットワーク コネクタに付与されます。 KCD の構成の詳細については、 [KCD でのシングル サインオンに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-sso-with-kcd)参照してください。
- **ヘッダーベースのサインオン:** ヘッダーベースのサインオンでは、HTTP ヘッダーを使用したシングル サインオン機能が提供されます。 詳細については、「 [ヘッダーベースのシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-single-sign-on-with-headers)」を参照してください。
- **SAML シングル サインオン:** Security Assertion Markup Language (SAML) シングル サインオンでは、Microsoft Entra ID はユーザーの Microsoft Entra アカウントを使用してアプリケーションに対して認証を行います。 Microsoft Entra ID は、接続プロトコルを介してサインイン情報をアプリケーションに伝達します。 SAML ベースのシングル サインオンでは、SAML 要求で定義するルールに基づいて、ユーザーを特定のアプリケーション ロールにマップできます。 SAML シングル サインオンの設定の詳細については、 [アプリケーション プロキシでのシングル サインオンの SAML](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/conceptual-sso-apps) に関するセクションを参照してください。

これらのオプションを見つけるには、 **エンタープライズ アプリ**でアプリケーションに移動し、左側のメニューの **[シングル サインオン** ] ページを開きます。 アプリケーションが古いポータルで作成されている場合は、これらのオプションがすべて表示されない可能性があります。

このページには、もう 1 つのサインオン オプション ( **リンクされたサインオン**) も表示されます。 アプリケーション プロキシでは、このオプションがサポートされます。 ただし、このオプションによって、アプリケーションにシングル サインオンは追加されません。 つまり、アプリケーションには、Active Directory フェデレーション サービスなどの別のサービスを使用して、既にシングル サインオンが実装されている可能性があります。

このオプションを使用すると、管理者は、ユーザーがアプリケーションにアクセスするときに最初にアクセスするアプリケーションへのリンクを作成できます。 たとえば、Active Directory フェデレーション サービス 2.0 を使用してユーザーを認証するように構成されたアプリケーションでは、 **リンクされたサインオン** オプションを使用して、[ **マイ アプリ]** ページにリンクを作成できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/how-to-configure-sso-with-kcd"} -->
## アプリケーション プロキシを使用してアプリにシングル サインオン (SSO) を実現する Kerberos 制約付き委任 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-sso-with-kcd
- Service: entra-id / app-proxy
- Article date: 2026-03-25
- Summary: Microsoft Entra アプリケーション プロキシで Kerberos 制約付き委任 (KCD) を使用して、オンプレミス アプリケーションの Kerberos ベースの SSO を構成します。

### 概要

統合 Windows 認証でセキュリティ保護されているアプリケーション プロキシを使って公開されているオンプレミスのアプリケーションにシングル サインオンを提供できます。 これらのアプリケーションにアクセスするには Kerberos チケットが必要です。 アプリケーション プロキシは、Kerberos の制約付き委任 (KCD) を使って、これらのアプリケーションをサポートします。

シングル サインオン (SSO) の詳細については、[シングル サインオンの概要](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)に関するページを参照してください。

統合 Windows 認証 (IWA) を使用してアプリケーションへのシングル サインオンができるようにするには、ユーザーの権限を借用できるように Active Directory でプライベート ネットワーク コネクタにアクセス許可を与えます。 コネクタはこのアクセス許可を利用し、ユーザーの代理としてトークンを送受信します。

### KCD を使ったシングル サインオンのしくみ

この図では、ユーザーが IWA を使用するオンプレミス アプリケーションにアクセスしようとしたときのフローについて説明します。

[Image: Microsoft Entra 認証フローの図。]

1. ユーザーは、オンプレミスのアプリケーションにアプリケーション プロキシをとおしてアクセスするための URL を入力します。
2. この要求がアプリケーション プロキシによって Microsoft Entra 認証サービスにリダイレクトされて、事前認証が行われます。 この時点で、Microsoft Entra ID の認証および承認のポリシーのうち、該当するものが適用されます (たとえば多要素認証)。 ユーザーの正当性が確認された場合、Microsoft Entra ID はトークンを作成し、ユーザーに送信します。
3. ユーザーは、このトークンをアプリケーション プロキシに渡します。
4. アプリケーション プロキシはトークンを検証し、そこからユーザー プリンシパル名 (UPN) を取得した後、コネクタは、デュアル認証されたセキュリティで保護されたチャネルを介して UPN とサービス プリンシパル名 (SPN) をプルします。
5. コネクタは、オンプレミス AD との Kerberos 制約付き委任 (KCD) ネゴシエーションを実行し、ユーザーを偽装して Kerberos トークンをアプリケーションに取得します。
6. Active Directory は、アプリケーションの Kerberos トークンをコネクタに送信します。
7. コネクタは、AD から受信した Kerberos トークンを使用して、元の要求をアプリケーション サーバーに送信します。
8. アプリケーションはコネクタに応答を送信し、その後アプリケーション プロキシ サービスに返され、最後にユーザーに返されます。

### 前提条件

IWA アプリケーションでのシングル サインオンに着手する前に、ご使用の環境が次の設定と構成に対応していることを確認してください。

- 対象のアプリ (SharePoint Web アプリなど) が統合 Windows 認証を使用するように設定されていること。 詳細については、「[Kerberos 認証のサポートを有効にする](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2008-R2-and-2008/dd759186%28v=ws.11%29)」を参照してください。SharePoint の場合は、「[SharePoint 2013 で Kerberos 認証を計画する](https://learn.microsoft.com/ja-jp/SharePoint/security-for-sharepoint-server/kerberos-authentication-planning)」を参照してください。
- すべてのアプリには[サービス プリンシパル名](https://learn.microsoft.com/ja-jp/windows/win32/ad/service-principal-names)があります。
- コネクタを実行しているサーバーとアプリを実行しているサーバーは、ドメインに参加しており、同じドメインまたは信頼するドメインの一部です。 ドメインへの参加の詳細については、「 [コンピューターをドメインに参加させる](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2012-R2-and-2012/dd807102%28v=ws.11%29)」を参照してください。
- コネクタ サーバーがユーザーの `TokenGroupsGlobalAndUniversal` 属性を読み取ることができることを確認します。 セキュリティ強化によって、このアクセスが制限される場合があります。 *Windows 承認アクセス* グループにコネクタ サーバーを追加して、コネクタ サーバーを有効にします。

#### Active Directory を構成する

Active Directory の構成は、プライベート ネットワーク コネクタとアプリケーション サーバーが同じドメイン内にあるかどうかによって異なります。

##### 同じドメインにあるコネクタとアプリケーション サーバー

1. Active Directory で、 **[ツール]**&gt;**[ユーザーとコンピューター]** の順に移動します。
2. コネクタを実行しているサーバーを選択します。
3. 右クリックし、 **[プロパティ]**&gt;**[委任]** を選択します。
4. **[指定されたサービスへの委任でのみこのコンピューターを信頼する]** をクリックします。
5. **[任意の認証プロトコルを使う]** を選択します。
6. **このアカウントが委任された資格情報を提示できるサービス**で、アプリケーション サーバーの SPN ID の値を追加します。 この設定により、プライベート ネットワーク コネクタは、一覧で定義されているアプリケーションに対して AD のユーザーを偽装できます。

    [Image: [委任] タブの [Connector-SVR プロパティ] ウィンドウを示すスクリーンショット。]

##### 異なるドメインにあるコネクタとアプリケーション サーバー

1. ドメイン間の KCD を使用するための前提条件の一覧については、「 [ドメイン間の Kerberos の制約付き委任](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2012-R2-and-2012/hh831477%28v=ws.11%29)」を参照してください。
2. アプリケーション プロキシ (コネクタ) からの Kerberos 認証委任を有効にするには、Web アプリケーションのサービス アカウント (`PrincipalsAllowedToDelegateTo`) の `webserviceaccount` プロパティを使用します。 アプリケーション サーバーは `webserviceaccount`で実行され、委任サーバーは `connectorcomputeraccount`。 `webserviceaccount`と同じドメイン内のドメイン コントローラー (Windows Server 2012 R2 以降) で次のコマンドを実行します。 両方のアカウントにフラット名 (UPN 以外) を使用します。

    `webserviceaccount` がコンピューター アカウントの場合は、次のコマンドを使用します。

    ```powershell
    $connector= Get-ADComputer -Identity connectorcomputeraccount -server dc.connectordomain.com
    
    Set-ADComputer -Identity webserviceaccount -PrincipalsAllowedToDelegateToAccount $connector
    
    Get-ADComputer webserviceaccount -Properties PrincipalsAllowedToDelegateToAccount
    ```

    `webserviceaccount` がユーザー アカウントの場合は、次のコマンドを使用します。

    ```powershell
    $connector= Get-ADComputer -Identity connectorcomputeraccount -server dc.connectordomain.com
    
    Set-ADUser -Identity webserviceaccount -PrincipalsAllowedToDelegateToAccount $connector
    
    Get-ADUser webserviceaccount -Properties PrincipalsAllowedToDelegateToAccount
    ```

### シングル サインオンの設定

1. アプリケーション プロキシを使用したアプリケーションの発行に関するページの手順に従って [、アプリケーションを発行します](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)。 **事前認証方法**として、必ず **[Microsoft Entra ID]** を選んでください。
2. アプリケーションがエンタープライズ アプリケーションの一覧に表示されたら、それを選択し、[ **シングル サインオン**] を選択します。
3. シングル サインオン モードを **[統合 Windows 認証]** に設定します。
4. アプリケーション サーバーの **[内部アプリケーション SPN]** を入力します。 この例では、公開されたアプリケーションの SPN は、`http/www.contoso.com` です。 SPN は、コネクタが委任された資格情報を提示できるサービスの一覧に含まれている必要があります。
5. ユーザーの代わりに使うコネクタに**委任されたログイン ID** を選択します。 詳細については、「さまざまなオンプレミス ID とクラウド ID の操作」を参照してください。

    [Image: 統合 Windows 認証の構成設定を示すスクリーンショット。]

### Windows 以外のアプリの SSO

Microsoft Entra アプリケーション プロキシの Kerberos 委任フローは、Microsoft Entra ID がクラウドでユーザーを認証すると開始されます。 要求がオンプレミスに到着すると、Microsoft Entra プライベート ネットワーク コネクタは、ローカル Active Directory と対話することで、ユーザーに代わって Kerberos チケットを発行します。 このプロセスは Kerberos 制約付き委任 (KCD) と呼ばれます。

次のフェーズで、要求がこの Kerberos チケットを使用してバックエンド アプリケーションに送信されます。

このような要求で Kerberos チケットを送信する方法を定義するメカニズムはいくつかあります。 Windows 以外のほとんどのサーバーでは、SPNEGO トークンの形式で受信することが想定されています。 このメカニズムは Microsoft Entra アプリケーション プロキシでサポートされていますが、既定では無効になっています。 コネクタは SPNEGO または標準の Kerberos トークン用に構成できますが、両方を構成することはできません。

SPNEGO 用にコネクタ マシンを構成する場合は、そのコネクタ グループ内の他のすべてのコネクタも SPNEGO で構成されていることを確認します。 標準の Kerberos トークンを必要とするアプリケーションは、SPNEGO 用に構成されていない他のコネクタを介してルーティングする必要があります。 一部の Web アプリケーションでは、構成を変更せずに両方の形式を使用できます。

SPNEGO を有効にするには:

1. 管理者として実行しているコマンド プロンプトを開きます。
2. SPNEGO が必要なコネクタ サーバーで次のコマンドを実行します。

    ```
    REG ADD "HKLM\SOFTWARE\Microsoft\Microsoft Entra private network connector" /v UseSpnegoAuthentication /t REG_DWORD /d 1
    net stop WAPCSvc & net start WAPCSvc
    ```

Windows 以外のアプリでは、通常、ドメインのメール アドレスの代わりにユーザー名または SAM アカウント名が使用されます。 このような状況がアプリケーションに適用される場合は、クラウド ID をアプリケーション ID に接続するように委任されたサインイン ID フィールドを構成する必要があります。

### さまざまなオンプレミス ID とクラウド ID の操作

アプリケーション プロキシは、ユーザーがクラウドとオンプレミスで同じ ID を持っていることを前提としています。 ただし、一部の組織では、会社のポリシーまたはアプリケーションの要件により、サインインに代替 ID を使用する必要があります。 各アプリケーションの **委任されたログイン ID を** 構成することで、シングル サインオンに対して KCD を有効にできます。 この設定では、シングル サインオンに使用する ID を指定します。

この機能を使用すると、組織は、ユーザーが異なるユーザー名とパスワードを管理しなくても、クラウドからオンプレミスのアプリへの SSO を有効にすることができます。 一般的なシナリオは、次のとおりです。

- 1 つのクラウド ドメイン (joe@us.contoso.com など) で複数の内部ドメイン (joe@eu.contoso.com、joe@contoso.comなど) を使用する。
- クラウドで有効なドメイン名を使用しているときに、ルーティング不可能な内部ドメイン名 (たとえば、 joe@contoso.usa) を持つ。
- 内部ドメイン名 (joe など) なしで動作します。
- オンプレミスとクラウドのユーザーに異なるエイリアスを割り当てる (たとえば、 joe-johns@contoso.com と joej@contoso.com)。

アプリケーション プロキシでは、Kerberos チケットの取得に使用する ID を選択できます。 この設定はアプリケーションごとに構成され、nonemail 形式または代替サインイン方法を必要とするシステムをサポートします。

[Image: [委任されたログイン ID] ドロップダウン オプションを示すスクリーンショット。]

委任されたサインイン ID が使用されている場合、組織内のすべてのドメインまたはフォレストで値が一意ではない可能性があります。 異なる 2 つのコネクタ グループを使用してこれらのアプリケーションを 2 回発行することで、この問題を回避できます。 アプリケーションごとにユーザー対象ユーザーが異なるため、そのコネクタを別のドメインに参加させることができます。

サインイン ID **にオンプレミスの SAM アカウント名** を使用する場合は、コネクタをホストしているコンピューターを、ユーザー アカウントが配置されているドメインに追加する必要があります。

#### ID が異なる場合の SSO の構成

1. Microsoft Entra Connect の設定を、メイン ID がメール アドレス (メール) になるように構成します。 構成は、同期設定の **[ユーザー プリンシパル名** ] フィールドを変更することで、カスタマイズ プロセスの一環として行われます。 これらの設定は、ユーザーが Microsoft 365、Windows コンピューター、および Microsoft Entra ID を ID ストアとして使う他のアプリケーションにサインインする方法も決定します。[Image: ユーザーを識別するための [ユーザー プリンシパル名] ドロップダウン。]
2. 変更するアプリケーションの [アプリケーションの構成] 設定で、使用する **[委任されたログイン ID]** を選択します。

    - ユーザー プリンシパル名 (`joe@contoso.com` など)
    - 代替ユーザー プリンシパル名 (`joed@contoso.local` など)
    - ユーザー プリンシパル名のユーザー名部分 (`joe` など)
    - 代替ユーザー プリンシパル名のユーザー名部分 (`joed` など)
    - オンプレミスのソフトウェア アセット管理アカウント名 (ドメイン コントローラーの構成に依存)

#### ID が異なる場合の SSO のトラブルシューティング

バックエンド アプリケーションが予期しない HTTP 応答で応答する場合は、コネクタ コンピューターのアプリケーション プロキシ セッション イベント ログでイベント 24029 を確認してトラブルシューティングを開始します。 イベントの詳細の `user` フィールドには、委任に使用される ID が表示されます。 セッション ログを有効にするには、イベント ビューアーに移動し、[ **表示** ] メニューを開き、[ **分析ログとデバッグ ログの表示**] を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/overview-what-is-app-proxy"} -->
## Microsoft Entra アプリケーション プロキシを使用してオンプレミス アプリを発行する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy
- Service: entra-id / app-proxy
- Article date: 2026-03-10
- Summary: Microsoft Entra アプリケーション プロキシのアーキテクチャ、コネクタ、認証方法、セキュリティ上の利点について説明します。

### 概要

Microsoft Entra アプリケーション プロキシは、リモート ユーザー向けのオンプレミス Web アプリケーションへの安全なリモート アクセスを提供します。 Microsoft Entra ID へのシングル サインオン (SSO) の後、ユーザーは外部 URL または内部アプリケーション ポータルを使用してクラウドアプリケーションとオンプレミス アプリケーションの両方にアクセスできます。 たとえば、アプリケーション プロキシは、リモート デスクトップ、SharePoint、Teams、Tableau、Qlik、基幹業務 (LOB) アプリケーションへのリモート アクセスと SSO を提供できます。

Microsoft Entra アプリケーション プロキシとは:

- **簡単な使い方**。 ユーザーは、Microsoft Entra ID と統合された Microsoft 365 やその他のサービスとしてのソフトウェア (SaaS) アプリにアクセスするのと同じ方法で、オンプレミス アプリケーションにアクセスできます。 アプリケーション プロキシを使うために、アプリケーションを変更または更新する必要はありません。
- **セキュリティ保護**。 オンプレミス アプリケーションでは、Microsoft Entra の承認制御とセキュリティ分析を使用できます。 たとえば、オンプレミス アプリケーションでは、Microsoft Entra 条件付きアクセスと多要素認証を使用できます。 アプリケーション プロキシでは、ファイアウォールを通過する受信接続を開く必要はありません。
- **高いコスト効率**。 通常、オンプレミス ソリューションでは、境界ネットワーク (非武装地帯または DMZ とも呼ばれます)、エッジ サーバー、またはその他の複雑なインフラストラクチャを設定して維持する必要があります。 アプリケーション プロキシはクラウドで実行されます。 これを使用するには、ネットワーク インフラストラクチャを変更したり、オンプレミス環境に追加のアプライアンスをインストールしたりする必要はありません。

ヒント

Microsoft Entra ID が既にある場合は、それを 1 つのコントロール プレーンとして使用して、オンプレミス アプリケーションへのシームレスで安全なアクセスを許可できます。

ハイブリッド共存シナリオでアプリケーション プロキシを使用する例を次に示します。

- 境界ネットワークなしで、オンプレミスの Web アプリを簡略化された方法で外部に発行します。
- クラウドとオンプレミスのデバイス、リソース、アプリ間で SSO をサポートします。
- クラウドとオンプレミスのアプリの多要素認証をサポートします。
- Microsoft クラウドのセキュリティでクラウド機能をすばやく使用できます。
- ユーザー アカウント管理を一元化します。
- ID とセキュリティの一元管理。
- グループ メンバーシップに基づいて、アプリケーションへのユーザー アクセスを自動的に追加または削除します。

この記事では、アプリケーション プロキシがクラウドの機能とセキュリティをオンプレミスの Web アプリケーションに提供する方法について説明し、サポートされているアーキテクチャとトポロジについて説明します。

### 過去と現在のリモート アクセス

以前は、リモート ユーザーによるアクセスを容易にしながら内部リソースを攻撃者から保護するためのコントロール プレーンがすべて境界ネットワークにありました。 ただし、境界ネットワークにデプロイされ、外部クライアントが企業リソースへのアクセスに使用する VPN およびリバース プロキシ ソリューションは、クラウドの世界には適していません。 通常、次のようになります。

- ハードウェア コストを増やします。
- 修正プログラムの適用やポートの監視など、継続的なセキュリティ メンテナンスが必要です。
- エッジでユーザーを認証します。
- 境界ネットワーク内の Web サーバーに対してユーザーを認証します。
- リモート アクセスを維持するには、VPN クライアント ソフトウェアの配布と構成が必要です。
- 境界ネットワーク内のドメイン参加済みサーバーを維持します。これは、外部攻撃に対して脆弱になる可能性があります。

今日のデジタルワークプレースでは、ユーザーは複数のデバイスを使用してどこからでもアプリにアクセスし、作業を行います。 定数係数はユーザー ID です。

Microsoft Entra ID を使用して、ネットワークに入るユーザーと情報を制御できます。 Microsoft Entra アプリケーション プロキシは、最新の認証およびクラウドベースのテクノロジ (SaaS アプリケーションや ID プロバイダーなど) と統合されます。 この統合により、ユーザーはどこからでもアプリケーションにアクセスできます。

[Image: Microsoft Entra ID に接続されているアプリを示す図。]

アプリケーション プロキシは、今日のデジタル ワークプレースに適しているだけでなく、VPN やリバース プロキシ ソリューションよりも安全です。 実装も簡単です。 リモート ユーザーは、Microsoft および Microsoft Entra ID と統合された他の SaaS アプリにアクセスするのと同じ方法で、オンプレミス のアプリケーションにアクセスできます。 アプリケーション プロキシを使うために、アプリケーションを変更または更新する必要はありません。 アプリケーション プロキシでは、ファイアウォール経由で受信接続を開く必要はありません。

セキュリティ コントロール プレーンとして [Microsoft Entra ID 管理](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/identity-management-overview) を使用して、ネットワークのセキュリティ保護を開始できます。 この ID ベースのモデルには、次のコンポーネントが含まれています。

- ユーザーおよびユーザーに関連する情報を追跡する ID プロバイダー。
- 企業リソースにアクセスできるデバイスの一覧を保持するデバイス ディレクトリ。 このディレクトリには、対応するデバイス情報 (デバイスの種類や整合性など) が含まれています。
- ポリシー評価サービス。管理者が設定したセキュリティ ポリシーをユーザーとデバイスが満たしているかどうかを確認します。
- 組織のリソースへのアクセスを許可または拒否する権限。

Microsoft Entra ID は、オンプレミスおよびクラウドで発行された Web アプリにアクセスするユーザーを追跡します。 これらのアプリを管理するための中心的なポイントが提供されます。

セキュリティを強化するには、Microsoft Entra 条件付きアクセスを有効にします。 この機能により、認証とアクセスの条件を設定することで、適切なユーザーのみがアプリケーションにアクセスできるようになります。

注

Microsoft Entra アプリケーション プロキシは、内部リソースにアクセスするリモート ユーザーの VPN またはリバース プロキシを置き換えます。 企業ネットワーク上の内部ユーザー向けに設計されていません。 内部ユーザーがアプリケーション プロキシを不必要に使用すると、予期しないパフォーマンスの問題が発生する可能性があります。

### アプリケーション プロキシの動作の概要

アプリケーション プロキシには、 *アプリケーション プロキシ サービス* と *プライベート ネットワーク コネクタ*の両方が含まれます。 アプリケーション プロキシ サービスはクラウドで実行され、プライベート ネットワーク コネクタはオンプレミス サーバー上の軽量エージェントとして動作します。 Microsoft Entra ID は ID プロバイダーとして機能します。

次の図は、Microsoft Entra ID、アプリケーション プロキシ サービス、プライベート ネットワーク コネクタが連携して、シームレスな SSO エクスペリエンスでユーザーがオンプレミスの Web アプリケーションにアクセスできるようにする方法を示しています。

[Image: Microsoft Entra アプリケーション プロキシでのシングル サインオンの 6 つの手順を示す図。]

1. ユーザーはエンドポイントを介してアプリケーションにアクセスし、Microsoft Entra サインイン ページにリダイレクトされます。
2. サインインに成功すると、Microsoft Entra ID ではトークンをユーザーのクライアント デバイスに送信します。
3. クライアントからは、トークンをアプリケーション プロキシ サービスに送信します。 サービスでは、トークンからユーザー プリンシパル名 (UPN) とセキュリティ プリンシパル名 (SPN) を取得します。 その後、アプリケーション プロキシ サービスがコネクタに要求を送信します。
4. コネクタは、ユーザーに代わって必要な SSO 認証を実行します。
5. コネクタはオンプレミスのアプリケーションに要求を送信します。
6. コネクタとアプリケーション プロキシ サービスは、応答をユーザーに送信します。

注

ほとんどの Microsoft Entra ハイブリッド エージェントと同様に、プライベート ネットワーク コネクタでは、ファイアウォールを通して受信接続を開く必要はありません。 手順 3 のユーザー トラフィックは、アプリケーション プロキシ サービスで終了します。 残りの通信は、お使いのプライベート ネットワーク内にあるプライベート ネットワーク コネクタが行います。

| コンポーネント | 説明 |
| --- | --- |
| エンドポイント | エンドポイントは URL または [ユーザー ポータル](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/end-user-experiences)です。 ユーザーは、外部 URL を使用して、ネットワークの外部からアプリケーションにアクセスできます。 ネットワーク内のユーザーは、URL またはユーザー ポータルを使用してアプリケーションにアクセスできます。 ユーザーは、これらのエンドポイントの 1 つに移動すると、Microsoft Entra ID で認証され、コネクタを介してオンプレミスのアプリケーションにルーティングされます。 |
| Microsoft Entra ID | Microsoft Entra ID は、クラウドに格納されているテナント ディレクトリを使用して認証を実行します。 |
| アプリケーション プロキシ サービス | アプリケーション プロキシ サービスは、Microsoft Entra ID の一部としてクラウドで実行されます。 これは、ユーザーからプライベート ネットワーク コネクタにサインオン トークンを渡します。 サービスは要求でアクセス可能なヘッダーを転送し、(プロトコルに従って) ヘッダーをクライアント IP アドレスに設定します。 プロキシへの受信要求にそのヘッダーが既に含まれている場合、クライアント IP アドレスは、ヘッダーの値であるコンマ区切りリストの末尾に追加されます。 |
| プライベート ネットワーク コネクタ | コネクタは、ネットワーク内の Windows Server 上で実行される軽量エージェントです。 コネクタでは、クラウドのアプリケーション プロキシ サービスとオンプレミス アプリケーションの間の通信を管理します。 コネクタは、アプリケーション プロキシ サービスからの要求をリッスンし、アプリケーションへの接続を処理します。 コネクタは送信接続のみを使用するため、インターネットに接続するネットワークで受信ポートを開く必要はありません。 Microsoft Entra Private Access では、同じコネクタが使用されます。 コネクタはステートレスであり、必要に応じてクラウドから情報をプルします。 負荷分散と認証方法など、コネクタの詳細については、 [Microsoft Entra プライベート ネットワーク コネクタ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors)を参照してください。 |
| Active Directory | Active Directory はオンプレミスで実行され、ドメイン アカウントの認証を行います。 SSO が構成されると、コネクタは Active Directory と通信して、必要な追加認証を実行します。 |
| オンプレミスのアプリケーション | 最後に、ユーザーはオンプレミスのアプリケーションにアクセスできます。 |

アプリケーション プロキシ サービスは、Microsoft Entra 管理センターで構成します。 このサービスを使用して、組織内の内部アプリケーション サーバー URL に接続する外部パブリック HTTP/HTTPS URL エンドポイントを Azure に発行できます。 これらのオンプレミス Web アプリは、SSO をサポートするために Microsoft Entra ID と統合できます。 その後、ユーザーは、Microsoft 365 やその他の SaaS アプリにアクセスするのと同じ方法で、オンプレミスの Web アプリにアクセスできます。

認証後、外部ユーザーは、デスクトップまたは iOS デバイスから表示 URL または [マイ アプリ](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510) を使用して、オンプレミスの Web アプリにアクセスできます。 たとえば、アプリケーション プロキシは、リモート デスクトップ、SharePoint サイト、Tableau、Qlik、Outlook on the web、LOB アプリケーションへのリモート アクセスと SSO を提供できます。

[Image: Microsoft Entra アプリケーション プロキシ アーキテクチャの図。]

#### 認証

SSO 用にアプリケーションを構成するには、いくつかの方法があります。 選択する方法は、アプリケーションが使用する認証によって異なります。 アプリケーション プロキシでは、次の種類のアプリケーションがサポートされています。

- ネイティブ認証プロトコルとして [統合 Windows 認証 (IWA)](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-sso-with-kcd) を使用する Web アプリケーション。 IWA の場合、プライベート ネットワーク コネクタは Kerberos 制約付き委任 (KCD) を使用して Kerberos アプリケーションに対してユーザーを認証します。
- フォーム ベースまたは [ヘッダー](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-single-sign-on-with-headers) ベースのアクセスを使用する Web アプリケーション。 ヘッダーベースの認証では、Microsoft 以外のパートナー サービスである PingAccess を使用して、ヘッダーに依存するアプリケーションの認証を処理します。
- さまざまなデバイス上のリッチ アプリケーションに公開する Web API。
- [リモート デスクトップ ゲートウェイ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-integrate-with-remote-desktop-services)の背後でホストされているアプリケーション。
- Microsoft Authentication Library (MSAL) と統合されたリッチ クライアント アプリ。

アプリケーション プロキシでは、特定の構成シナリオで Microsoft 以外のパートナーとの認証プロトコルもサポートされています。

- [パスワード ベースの認証](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-single-sign-on-password-vaulting)。 この認証方法では、ユーザーが初めてアクセスする際にユーザー名とパスワードを使用してアプリケーションにサインインします。 最初のサインイン後、Microsoft Entra ID はユーザー名とパスワードをアプリケーションに提供します。 このシナリオでは、Microsoft Entra ID が認証を処理します。
- [セキュリティ アサーション マークアップ言語 (SAML) 認証](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/conceptual-sso-apps)。 SAML ベースの SSO は、SAML 2.0 または WS-Federation プロトコルを使用するアプリケーションでサポートされています。 SAML SSO を使用すると、Microsoft Entra ID はユーザーの Microsoft Entra アカウントを使用してアプリケーションに対して認証を行います。

サポートされている方法の詳細については、「 [シングル サインオン オプション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/plan-sso-deployment#choosing-a-single-sign-on-method)」を参照してください。

#### セキュリティ上の利点

アプリケーション プロキシと Microsoft Entra が提供するリモート アクセス ソリューションでは、いくつかのセキュリティ上の利点がサポートされています。

- **認証済みのアクセス**。 アプリケーション プロキシは [事前認証](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-security#authenticated-access) を使用して、認証された接続のみがネットワークに到達するようにします。 事前認証で構成されたアプリケーションに対して有効なトークンがないすべてのトラフィックをブロックします。 このアプローチでは、検証済みの ID のみがバックエンド アプリケーションにアクセスできるようにすることで、標的型攻撃を大幅に削減できます。
- **条件付きアクセス**。 ネットワークへの接続が確立される前に、より豊富なポリシー制御を適用できます。 条件付きアクセスを使用すると、バックエンド アプリケーションへのアクセスを許可するトラフィックに対する制限を定義できます。 場所、認証の強度、ユーザーのリスク プロファイルに基づいてサインインを制限するポリシーを作成します。

    セキュリティを強化するために、条件付きアクセスは Microsoft Defender for Cloud Apps とも統合されます。 この統合により、条件付きアクセス ポリシーに基づいて [リアルタイム監視](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-integrate-with-microsoft-cloud-application-security) 用のオンプレミス アプリケーションを構成できます。
- **トラフィックの終了**。 バックエンド アプリケーションへのすべてのトラフィックは、バックエンド サーバーとのセッションの再確立中に、クラウド内のアプリケーション プロキシ サービスで終了されます。 この接続戦略は、バックエンド サーバーが直接 HTTP トラフィックに公開されていないことを意味します。 ファイアウォールが攻撃を受けないため、対象となるサービス拒否 (DoS) 攻撃からより適切に保護されます。
- **送信専用アクセス**。 プライベート ネットワーク コネクタは、ポート 80 と 443 経由でクラウド内のアプリケーション プロキシ サービスへの送信接続のみを使用します。 受信接続がない場合、境界ネットワーク内の受信接続またはコンポーネントのファイアウォール ポートを開く必要はありません。 すべての送信接続は、セキュリティで保護されたチャネル経由で行われます。
- **セキュリティ分析と機械学習に基づくインテリジェンス**。 アプリケーション プロキシは Microsoft Entra ID の一部であるため、 [Microsoft Entra ID 保護 を](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)使用できます。 Microsoft Entra ID 保護 は、機械学習のセキュリティ インテリジェンスと、Microsoft [のデジタル犯罪ユニット](https://news.microsoft.com/stories/cybercrime/index.html) と [Microsoft セキュリティ対応センター](https://www.microsoft.com/msrc) からのデータ フィードを組み合わせて、侵害されたアカウントを事前に特定します。 [Premium P2 ライセンスが必要です](https://www.microsoft.com/security/business/microsoft-entra-pricing)。

    Microsoft Entra ID 保護 は、リスクの高いサインインからのリアルタイム保護を提供します。これは、感染したデバイスからのアクセス試行、ネットワークの匿名化、セッションのリスク プロファイルを向上させる非定型でありそうもない場所からのアクセス試行などの要因を考慮します。 このリスク プロファイルは、リアルタイム保護に使用されます。 これらのレポートとイベントの多くは、お客様のセキュリティ情報およびイベント管理 (SIEM) システムとの統合を可能にする API を通じて既に使用可能です。
- **サービスとしてのリモート アクセス**。 リモート アクセスを可能にするために、オンプレミス サーバーの保守やパッチの適用について心配する必要がありません。 アプリケーション プロキシは、インターネット規模の Microsoft が管理するサービスであるため、常に最新のセキュリティ パッチとアップグレードを入手できます。

    パッチの適用されていないソフトウェアは、依然として多数の攻撃の的となっています。 サイバーセキュリティおよびインフラストラクチャ セキュリティ機関 (CISA) によると、 [標的型攻撃の 85% は予防可能です](https://www.cisa.gov/news-events/alerts/2015/04/29/top-30-targeted-high-risk-vulnerabilities)。 このサービス モデルでは、エッジ サーバーを管理する負担を軽減し、必要に応じてパッチを適用する必要がなくなります。

#### クラウドへのロードマップ

アプリケーション プロキシを実装するもう 1 つの大きなメリットは、Microsoft Entra ID がオンプレミス環境に拡張される点です。 アプリケーション プロキシの実装は、組織とアプリをクラウドに移行する上で重要なステップになります。 クラウドに移行してオンプレミスの認証から解放されることで、オンプレミスのフットプリントを削減し、Microsoft Entra の ID 管理機能をコントロール プレーンとして使用することができます。

既存のアプリケーションに対する更新が最小限またはまったくない場合は、SSO、多要素認証、集中管理などのクラウド機能にアクセスできます。 アプリケーション プロキシに必要なコンポーネントのインストールは簡単です。 また、クラウドに移行することで、高可用性やディザスター リカバリーなどの最新の Microsoft Entra の機能、更新プログラム、機能にアクセスできます。

アプリを Microsoft Entra ID に移行する方法の詳細については、「 [アプリケーションを Microsoft Entra ID に移行するためのリソース」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migration-resources)参照してください。

### アーキテクチャ

次の図は、一般に、Microsoft Entra 認証サービスとアプリケーション プロキシが連携してオンプレミス アプリケーションに SSO を提供する方法を示しています。

[Image: Microsoft Entra アプリケーション プロキシの認証フローを示す図。]

1. ユーザーはエンドポイントを介してアプリケーションにアクセスし、Microsoft Entra サインイン ページにリダイレクトされます。 条件付きアクセス ポリシーは、特定の条件をチェックして、組織のセキュリティ要件に準拠していることを確認します。
2. Microsoft Entra ID は、ユーザーのクライアント デバイスにトークンを送信します。
3. クライアントは、トークンをアプリケーション プロキシ サービスに送信し、トークンから UPN と SPN を抽出します。
4. アプリケーション プロキシ サービスは、 [プライベート ネットワーク コネクタ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors)に要求を転送します。
5. コネクタは、必要な追加の認証 (*認証方法に基づいて省略可能)* を処理し、アプリケーション サーバーの内部エンドポイントを取得して、オンプレミス アプリケーションに要求を送信します。
6. アプリケーション サーバーが応答し、コネクタが応答をアプリケーション プロキシ サービスに返します。
7. アプリケーション プロキシ サービスは、ユーザーに応答を配信します。

すべての通信はトランスポート層セキュリティ (TLS) 経由で行われ、常にコネクタからアプリケーション プロキシ サービスに送信されます。 コネクタではクライアント証明書を使用して、すべての呼び出しに対してアプリケーション プロキシ サービスを認証します。 接続のセキュリティの唯一の例外は、クライアント証明書が確立される最初のセットアップ手順です。

詳細については、「 [Microsoft Entra アプリケーション プロキシを使用してアプリにリモートでアクセスするためのセキュリティに関する考慮事項](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-security#under-the-hood)」を参照してください。

### その他のユース ケース

この記事では、アプリケーション プロキシを使用してオンプレミス アプリを外部に発行し、すべてのクラウドアプリとオンプレミス アプリの SSO を有効にすることに重点を置いています。 ただし、アプリケーション プロキシでは、次のようなその他のユース ケースもサポートされています。

- **REST API の安全な発行**。 アプリケーション プロキシを使用して、オンプレミスまたはクラウドでホストされる API のパブリック エンドポイントを作成します。 受信ポートを開かずに認証と承認を制御します。 詳細については、「 [ネイティブ クライアント アプリケーションがプロキシ アプリケーションと対話できるようにする](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-native-client-application) 」および「 [Microsoft Entra ID を使用した OAuth 2.0 承認を使用した Azure API Management での API の保護」を](https://learn.microsoft.com/ja-jp/azure/api-management/api-management-howto-protect-backend-with-aad)参照してください。
- **リモート デスクトップ サービス (RDS) を使用してアプリケーションを発行**します。 標準の RDS デプロイでは、開いている受信接続が必要です。 ただし、[アプリケーション プロキシを使用した RDS デプロイ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-integrate-with-remote-desktop-services)には、コネクタ サービスを実行しているサーバーからの永続的な送信接続があります。 これにより、RDS を介してオンプレミス アプリケーションを発行することで、ユーザーにさらに多くのアプリケーションを提供できます。 RDS に対する多要素認証と条件付きアクセス制御の限られたセットを使用して、展開の攻撃対象領域を減らすこともできます。
- **WebSocket 経由で接続するアプリケーションを発行**します。 [Qlik Sense](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-qlik) でのサポートはプレビュー段階です。
- **ネイティブ クライアント アプリケーションからプロキシ アプリケーションを操作できるようにする**。 Microsoft Entra アプリケーション プロキシを使用して Web アプリを発行できますが、MSAL で構成されている [ネイティブ クライアント アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-native-client-application) を発行することもできます。 クライアント アプリケーションはデバイスにインストールされますが、Web アプリにはブラウザー経由でアクセスされます。

### まとめ

組織は、作業とツールの急速な変化に適応します。 従業員は自分のデバイスを使用し、SaaS アプリケーションに依存します。 データは、従来の境界をはるかに超えて、オンプレミスとクラウドの環境間で移動するようになりました。 このシフトにより、生産性とコラボレーションが向上しますが、機密データの保護も困難になります。

Microsoft Entra アプリケーション プロキシは、サービスとしてのリモート アクセスを提供することで、オンプレミスのフットプリントを削減します。 ハイブリッド セットアップで Microsoft Entra ID を既に使用してユーザーを管理している場合でも、クラウド体験を開始する予定の場合でも、アプリケーション プロキシはリモート アクセスを簡素化し、セキュリティを強化します。

組織では、アプリケーション プロキシを使用して、次の利点を活用できます。

- 従来の VPN やその他のオンプレミス Web 発行ソリューションや境界ネットワーク アプローチを維持するオーバーヘッドをかけずに、オンプレミス アプリを外部で発行します。
- Microsoft 365、その他の SaaS アプリ、オンプレミス アプリケーションなど、すべてのアプリケーションに対して SSO を有効にします。
- Microsoft Entra ID が不正アクセスの防止に役立つクラウド規模のセキュリティを提供します。
- ユーザー アカウント管理を一元化します。
- 自動更新を受け取り、最新のセキュリティ パッチが適用されていることを確認します。
- SAML SSO のサポートやアプリケーション Cookie のより詳細な管理など、リリースされた新機能にアクセスします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/scripts/powershell-assign-group-to-app"} -->
## PowerShell の例 - Microsoft Entra アプリケーション プロキシ アプリにグループを割り当てる - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-assign-group-to-app
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: Microsoft Entra アプリケーション プロキシ アプリケーションにグループを割り当てる PowerShell の例。

### 概要

PowerShell スクリプトの例では、Microsoft Entra アプリケーション プロキシ アプリケーションに特定のグループを割り当てます。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)をお持ちでない場合は、開始する前に [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。

注

Azure Az PowerShell モジュールを使用して Azure と対話することをお勧めします。 作業を開始するには、[Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に関する記事を参照してください。 Az PowerShell モジュールに移行する方法については、「 [Azure PowerShell を AzureRM から Az に移行する」](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)を参照してください。

このサンプルには、 [Microsoft Graph Beta PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) 2.10 以降が必要です。

### サンプル スクリプト

```powershell
#  This sample script assigns a group to a specific Microsoft Entra application proxy application.
#
#  Tip: You can identify the parameters by using the following PS commands:
#    ServicePrincipalObjectId - Get-MgBetaServicePrincipal -Filter "DisplayName eq '<displayname of the app>'" 
#    GroupObjectId - Get-MgBetaGroup -ConsistencyLevel eventual -Count userCount -Search '"DisplayName:<name of the group>"'"
#
# Version 1.0
#
# This script requires PowerShell 5.1 (x64) or beyond and one of the following modules:
#
# Microsoft.Graph.Beta ver 2.10 or newer
#
# Before you begin:
#    
#    Required Microsoft Entra role at least Application Administrator
#    or appropriate custom permissions as documented https://learn.microsoft.com/azure/active-directory/roles/custom-enterprise-app-permissions
#
# 

param(
[parameter(Mandatory=$true)]
[string] $ServicePrincipalObjectId = "null",
[parameter(Mandatory=$true)]
[string] $GroupObjectId = "null"
)

$servicePrincipalObjectId = $ServicePrincipalObjectId
$groupObjectId = $GroupObjectId

If (($servicePrincipalObjectId -eq "null") -or ($groupObjectId -eq "null")) {

    Write-Host "Parameter is missing." -BackgroundColor "Black" -ForegroundColor "Green"
    Write-Host " "
    Write-Host ".\assign-group-to-app.ps1 -ServicePrincipalObjectId <ObjectId of the Microsoft Entra application proxy application service principal> -UserObjectId <ObjectId of the User>" -BackgroundColor "Black" -ForegroundColor "Green"
    Write-Host " "
    Write-Host "Hints:" -BackgroundColor "Black" -ForegroundColor "Green"
    Write-Host "You can easily identify the parameters by using the following PS commands:" -BackgroundColor "Black" -ForegroundColor "Green"
    Write-Host " "
    Write-Host "ServicePrincipalObjectId - Get-MgBetaServicePrincipal -Filter "DisplayName eq '<displayname of the app>'" " -BackgroundColor "Black" -ForegroundColor "Green"
    Write-Host "UserObjectId - Get-MgBetaGroup -ConsistencyLevel eventual -Count userCount -Search '"DisplayName:<name of the group>"'" -BackgroundColor "Black" -ForegroundColor "Green"

    Exit
}

Import-Module Microsoft.Graph.Beta.Applications

Connect-MgGraph -Scope Directory.ReadWrite.All -NoWelcome

New-MgBetaGroupAppRoleAssignment -GroupId $groupObjectId -PrincipalId $groupObjectId -ResourceId $servicePrincipalObjectId -AppRoleId "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb"

Write-Host ("")
Write-Host ("Finished.") -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host ("")
Write-Host "To disconnect from Microsoft Graph, please use the Disconnect-MgGraph cmdlet." 
```

### スクリプトの説明

| コマンド | 注記 |
| --- | --- |
| [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.authentication/connect-mggraph) | Microsoft Graph に接続する |
| [New-MgBetaGroupAppRoleAssignment](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/new-mgbetagroupapproleassignment) | アプリ ロールをグループに割り当てます |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/scripts/powershell-assign-user-to-app"} -->
## PowerShell の例 - Microsoft Entra アプリケーション プロキシ アプリにユーザーを割り当てる - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-assign-user-to-app
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: Microsoft Entra アプリケーション プロキシ アプリケーションにユーザーを割り当てる PowerShell の例。

### 概要

PowerShell スクリプトの例では、特定の Microsoft Entra アプリケーション プロキシ アプリケーションにユーザーを割り当てます。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)をお持ちでない場合は、開始する前に [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。

注

Azure Az PowerShell モジュールを使用して Azure と対話することをお勧めします。 作業を開始するには、[Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に関する記事を参照してください。 Az PowerShell モジュールに移行する方法については、「 [Azure PowerShell を AzureRM から Az に移行する」](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)を参照してください。

このサンプルには、 [Microsoft Graph Beta PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) 2.10 以降が必要です。

### サンプル スクリプト

```powershell
#  This sample script assigns a user to a specific Microsoft Entra application proxy application.
#
#  Tip: You can identify the parameters by using the following PS commands:
#    ServicePrincipalObjectId - Get-MgBetaServicePrincipal -Filter "DisplayName eq '<displayname of the app>'" 
#    UserObjectId - Get-MgBetaUser -ConsistencyLevel eventual -Count userCount -Search '"DisplayName:<name of the user>"'"
#
# Version 1.0
#
# This script requires PowerShell 5.1 (x64) or beyond and one of the following modules:
#
# Microsoft.Graph.Beta ver 2.10 or newer
#
# Before you begin:
#    
#    Required Microsoft Entra role at least Application Administrator
#    or appropriate custom permissions as documented https://learn.microsoft.com/azure/active-directory/roles/custom-enterprise-app-permissions
#
# 

param(
[parameter(Mandatory=$true)]
[string] $ServicePrincipalObjectId = "null",
[parameter(Mandatory=$true)]
[string] $UserObjectId = "null"
)

$servicePrincipalObjectId = $ServicePrincipalObjectId
$userObjectId = $UserObjectId

If (($servicePrincipalObjectId -eq "null") -or ($userObjectId -eq "null")) {

    Write-Host "Parameter is missing." -BackgroundColor "Black" -ForegroundColor "Green"
    Write-Host " "
    Write-Host ".\assign-user-to-app.ps1 -ServicePrincipalObjectId <ObjectId of the Microsoft Entra application proxy application service principal> -UserObjectId <ObjectId of the User>" -BackgroundColor "Black" -ForegroundColor "Green"
    Write-Host " "
    Write-Host "Hints:" -BackgroundColor "Black" -ForegroundColor "Green"
    Write-Host "You can easily identify the parameters by using the following PS commands:" -BackgroundColor "Black" -ForegroundColor "Green"
    Write-Host " "
    Write-Host "ServicePrincipalObjectId - Get-MgBetaServicePrincipal -Filter "DisplayName eq '<displayname of the app>'" " -BackgroundColor "Black" -ForegroundColor "Green"
    Write-Host "UserObjectId - Get-MgBetaUser -ConsistencyLevel eventual -Count userCount -Search '"DisplayName:<name of the user>"'" -BackgroundColor "Black" -ForegroundColor "Green"

    Exit
}

Import-Module Microsoft.Graph.Beta.Applications

Connect-MgGraph -Scope Directory.ReadWrite.All -NoWelcome

New-MgBetaUserAppRoleAssignment -UserId $userObjectId -PrincipalId $userObjectId -ResourceId $servicePrincipalObjectId -AppRoleId "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb"

Write-Host ("")
Write-Host ("Finished.") -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host ("")
Write-Host "To disconnect from Microsoft Graph, please use the Disconnect-MgGraph cmdlet."

```

### スクリプトの説明

| コマンド | 注記 |
| --- | --- |
| [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.authentication/connect-mggraph) | Microsoft Graph に接続する |
| [New-MgBetaUserAppRoleAssignment](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/new-mgbetauserapproleassignment) | ユーザーにアプリ ロールを割り当てます |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/scripts/powershell-display-users-group-of-app"} -->
## PowerShell サンプル - Microsoft Entra アプリケーション プロキシ アプリのユーザーとグループを一覧表示する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-display-users-group-of-app
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: 特定の Microsoft Entra アプリケーション プロキシ アプリケーションに割り当てられているすべてのユーザーとグループを一覧表示する PowerShell の例。

### 概要

PowerShell スクリプトの例では、特定の Microsoft Entra アプリケーション プロキシ アプリケーションに割り当てられているユーザーとグループを一覧表示します。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)をお持ちでない場合は、開始する前に [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。

注

Azure Az PowerShell モジュールを使用して Azure と対話することをお勧めします。 作業を開始するには、[Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に関する記事を参照してください。 Az PowerShell モジュールに移行する方法については、「 [Azure PowerShell を AzureRM から Az に移行する」](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)を参照してください。

このサンプルには、 [Microsoft Graph Beta PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) 2.10 以降が必要です。

### サンプル スクリプト

```powershell
# This sample script displays users and groups assigned to the specified Microsoft Entra application proxy application.
#
# .\display-users-group-of-an-app.ps1 -ObjectId <ObjectId of the service principal> (Enterprise App)
#
# Version 1.0
#
# This script requires PowerShell 5.1 (x64) or beyond and one of the following modules:
#
# Microsoft.Graph.Beta ver 2.10 or newer
#
# Before you begin:
#    
#    Required Microsoft Entra role at least Application Administrator
#    or appropriate custom permissions as documented https://learn.microsoft.com/azure/active-directory/roles/custom-enterprise-app-permissions
#
# 

param(
[parameter(Mandatory=$true)]
[string] $ObjectId = "null"
)

$aadapServPrincObjId=$ObjectId

If ($aadapServPrincObjId -eq "null") {

    Write-Host "Parameter is missing." -BackgroundColor "Black" -ForegroundColor "Green"
    Write-Host " "
    Write-Host ".\display-users-group-of-an-app.ps1 -ObjectId <ObjectId of the service principal (Enterprise App)>" -BackgroundColor "Black" -ForegroundColor "Green"
    Write-Host " "

    Exit
}

Import-Module Microsoft.Graph.Beta.Applications

Connect-MgGraph -Scope Directory.Read.All -NoWelcome

Write-Host "Reading users. This operation might take longer..." -BackgroundColor "Black" -ForegroundColor "Green"

$users= Get-MgBetaUser -Top 1000000

Write-Host "Reading groups. This operation might take longer..." -BackgroundColor "Black" -ForegroundColor "Green"

$groups = Get-MgBetaGroup -Top 1000000 

try {$app = Get-MgBetaServicePrincipalById -Id $aadapServPrincObjId}

catch {

    Write-Host "Possibly the ObjetId is incorrect." -BackgroundColor "Black" -ForegroundColor "Red"
    Write-Host " "

    Exit
}

Write-Host ("Application: " + $app.DisplayName + "(ServicePrinc. ObjID:" + $aadapServPrincObjId + ")")
Write-Host ("")
Write-Host ("Assigned (directly and through group membership) users:")
Write-Host ("")

$number=0

foreach ($item in $users) {

   $listOfAssignments = Get-MgBetaUserAppRoleAssignment -UserId $item.Id

   $assigned = $false

   foreach ($item2 in $listOfAssignments) { if ($item2.ResourceId -eq $aadapServPrincObjId) { $assigned = $true } }

     If ($assigned -eq $true) {
        Write-Host ("DisplayName: " + $item.DisplayName + " UPN: " + $item.UserPrincipalName + " ObjectID: " + $item.Id)
        $number = $number + 1
     }
}

Write-Host ("")
Write-Host ("Number of (directly and through group membership) users: " + $number)
Write-Host ("")
Write-Host ("")
Write-Host ("Assigned groups:")
Write-Host ("")

$number=0

foreach ($item in $groups) {

   $listOfAssignments = Get-MgBetaGroupAppRoleAssignment -GroupId $item.Id

   $assigned = $false

   foreach ($item2 in $listOfAssignments) { If ($item2.ResourceID -eq $aadapServPrincObjId) { $assigned = $true } }

   If ($assigned -eq $true) {
        Write-Host ("DisplayName: " + $item.DisplayName + " ObjectID: " + $item.Id)
        $number=$number+1
   }
}

Write-Host ("")
Write-Host ("Number of assigned groups: " + $number)
Write-Host ("")

Write-Host ("")
Write-Host ("Finished.") -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host ("") 
Write-Host "To disconnect from Microsoft Graph, please use the Disconnect-MgGraph cmdlet."
```

### スクリプトの説明

| コマンド | 注記 |
| --- | --- |
| [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.authentication/connect-mggraph) | Microsoft Graph に接続する |
| [Get-MgBetaServicePrincipalById](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/get-mgbetaserviceprincipalbyid) (このコマンドは、特定のサービスプリンシパルをIDで取得します) | ID でサービス プリンシパルを取得します。 |
| [Get-MgBetaUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.users/get-mgbetauser) | ユーザーを取得します。 |
| [Get-MgBetaGroup](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.groups/get-mgbetagroup) | グループを取得します。 |
| [Get-MgBetaUserAppRoleAssignment](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/get-mgbetagroupapproleassignment) | アプリケーション ロールの割り当てを取得します。 |
<!-- /MSL-PAGE -->
