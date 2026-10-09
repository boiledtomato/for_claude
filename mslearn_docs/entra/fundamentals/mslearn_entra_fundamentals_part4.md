# Microsoft Learn — Microsoft Entra / 基礎・アーキテクチャ・標準・その他 (part 4)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 41

---

<!-- MSL-PAGE {"url":"entra/fundamentals/custom-security-attributes-overview"} -->
## Microsoft Entra ID のカスタム セキュリティ属性とは - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview
- Service: entra / fundamentals
- Article date: 2024-10-28
- Summary: Microsoft Entra ID のカスタム セキュリティ属性について説明します。

Microsoft Entra ID のカスタム セキュリティ属性は、ビジネス固有の属性 (キーと値のペア) であり、Microsoft Entra オブジェクトに対して定義して割り当てることができます。 これらの属性を使用して、情報の保存、オブジェクトの分類、特定の Azure リソースに対するきめ細かいアクセス制御を行うことができます。 カスタム セキュリティ属性は、[Azure 属性ベースのアクセス制御 (ABAC)](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/conditions-overview) と共に使用できます。

### カスタム セキュリティ属性を使用する理由

カスタム セキュリティ属性を使用できるいくつかのシナリオを示します。

- ユーザー プロファイルを拡張する (時給を全従業員に追加するなど)。
- 管理者だけが従業員のプロファイルで時給属性を確認できるようにする。
- 数百または数千のアプリケーションを分類して、監査用にフィルター可能なインベントリを簡単に作成する。
- プロジェクトに属する Azure Storage BLOB へのアクセス許可をユーザーに付与する。

### カスタム セキュリティ属性でできること

カスタム セキュリティ属性には次の機能が含まれます。

- テナントのビジネス固有の情報 (属性) を定義する。
- ユーザーとアプリケーションに一連のカスタム セキュリティ属性を追加します。
- クエリとフィルターで、カスタム セキュリティ属性を使用して Microsoft Entra オブジェクトを管理します。
- 属性ガバナンスの提供により、アクセスできるユーザーを属性で判断する。

カスタム セキュリティ属性は、次の分野ではサポート**されていません**。

- [Microsoft Entra ドメイン サービス](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/overview)
- [SAML (Security Assertion Markup Language) トークン要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)
- [JSON Web トークン要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/security-tokens)

### カスタム セキュリティ属性の特徴

カスタム セキュリティ属性には次の機能が含まれます。

- テナント全体で利用可能
- 説明を含む
- さまざまなデータ型をサポート: ブール値、整数、文字列
- 単一の値または複数の値をサポート
- ユーザー定義による自由形式の値または定義済みの値をサポート
- オンプレミスの Active Directory からディレクトリ同期済みのユーザーにカスタム セキュリティ属性を割り当てる

次の例では、ユーザーに割り当てられているカスタム セキュリティ属性がいくつか示されています。 カスタム セキュリティ属性のデータ型はさまざまであり、単一の値、複数の値、自由形式の値、事前定義された値が使用されます。

[Image: ユーザーに割り当てられるカスタム セキュリティ属性のスクリーンショット。]

### カスタム セキュリティ属性をサポートするオブジェクト

次の Microsoft Entra オブジェクトに対してカスタム セキュリティ属性を追加できます。

- Microsoft Entra ユーザー
- Microsoft Entra エンタープライズ アプリケーション (サービス プリンシパル)

### カスタム セキュリティ属性と拡張機能の比較

拡張機能とカスタム セキュリティ属性はどちらも、Microsoft Entra ID と Microsoft 365 のオブジェクトを拡張するために使用できますが、これらが適しているカスタム データ シナリオは基本的に異なっています。 ここでは、カスタム セキュリティ属性と[拡張機能](https://learn.microsoft.com/ja-jp/graph/extensibility-overview)と比較して違いを説明します。

| 機能 | 拡張機能 | カスタム セキュリティ属性 |
| --- | --- | --- |
| Microsoft Entra ID と Microsoft 365 のオブジェクトを拡張する | あり | あり |
| サポート対象のオブジェクト | 拡張機能の種類によって異なる | ユーザーとサービス プリンシパル |
| 制限付きアクセス | その必要はありません。 オブジェクトを読み取るアクセス許可を持つすべてのユーザーは、拡張機能データを読み取ることができます。 | はい。 読み取りおよび書き込みアクセスは、別のアクセス許可とロールベースのアクセス制御 (RBAC) のセットによって制限されます。 |
| 使用する場合 | アプリケーションで使用されるデータを保存する  機密以外のデータを保存する | 機密データを保存する  承認シナリオに使用する |
| ライセンスの要件 | Microsoft Entra ID のすべてのエディションで利用可能 | Microsoft Entra ID のすべてのエディションで利用可能 |

拡張機能の使用の詳細については、「[拡張機能を使用してカスタム データをリソースに追加する](https://learn.microsoft.com/ja-jp/graph/extensibility-overview)」をご覧ください。

### カスタム セキュリティ属性を使用する手順

1. **アクセス許可を確認する**

    [属性定義管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-administrator)または[属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)のロールが割り当てられていることを確認します。 必要な場合は、[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)以上のロールを持つユーザーが、これらのロールを割り当てることができます。

    [Image: Microsoft Entra ID でカスタム セキュリティ属性を追加するためのアクセス許可を確認する作業を表す図。]
2. **属性セットを追加する**

    属性セットをグループに追加し、関連するカスタム セキュリティ属性を管理します。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-add)

    [Image: 複数の属性セットの追加を示す図。]
3. **属性セットを管理する**

    属性セット内のカスタム セキュリティ属性の読み取り、定義、割り当てができるユーザーを指定します。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-manage)

    [Image: 属性セットに対する属性定義管理者と属性割り当て管理者の割り当てを示す図。]
4. **属性を定義する**

    カスタム セキュリティ属性をディレクトリに追加します。 日付型 (ブール値、整数、または文字列) と、値が事前定義されているか自由形式か、単一か、複数かを指定できます。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-add)

    [Image: 委任された管理者がカスタム セキュリティ属性を定義する様子を示す図。]
5. **属性を割り当てる**

    ご使用のビジネス シナリオに合わせて、Microsoft Entra オブジェクトにカスタム セキュリティ属性を割り当てます。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/identity/users/users-custom-security-attributes)

    [Image: 委任された管理者が Microsoft Entra オブジェクトにカスタム セキュリティ属性を割り当てる作業を表す図。]
6. **属性を使用する**

    カスタム セキュリティ属性を使用するユーザーやアプリケーションをフィルター処理します。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/identity/users/users-custom-security-attributes)

    カスタム セキュリティ属性を使用する条件を Azure でのロールの割り当てに追加することで、詳細なアクセス制御が可能になります。 [詳細情報](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/conditions-custom-security-attributes)

### 用語

用語の一覧です。カスタム セキュリティ属性について理解を深めたいときは、適宜ここに戻って参照してください。

| 用語 | Definition |
| --- | --- |
| 属性の定義 | カスタム セキュリティ属性またはキーと値のペアのスキーマ。 たとえば、カスタム セキュリティ属性の名前、説明、データ型、定義済みの値などです。 |
| 属性セット | 関連するカスタム セキュリティ属性のコレクション。 属性セットを他のユーザーに委任して、カスタム セキュリティ属性の定義と割り当てを行うことができます。 |
| 属性名 | 属性セット内のカスタム セキュリティ属性の一意の名前。 属性セットと属性名の組み合わせによって、テナントの一意の属性が形成されます。 |
| 属性の割り当て | ユーザー、エンタープライズ アプリケーション (サービス プリンシパル) などの Microsoft Entra オブジェクトに対するカスタム セキュリティ属性の割り当て。 |
| 定義済みの値 | カスタム セキュリティ属性に使用できる値。 |

### カスタム セキュリティ属性のプロパティ

次の表に、属性セットおよびカスタム セキュリティ属性に指定できるプロパティを示します。 一部のプロパティは不変であり、後で変更することはできません。

| プロパティ | 必須 | 後で変更可能 | 説明 |
| --- | --- | --- | --- |
| 属性セット名 | ✅ |  | 属性セットの名前。 テナント内で一意である必要があります。 スペースや特殊文字を含めることはできません。 |
| 属性セットの説明 |  | ✅ | 属性セットの説明。 |
| 属性の最大数 |  | ✅ | 属性セットで定義できるカスタム セキュリティ属性の最大数。 既定値は `null` です。 指定されていない場合、管理者は 1 つのテナントにつき最大 500 のアクティブな属性を追加することができます。 |
| 属性セット | ✅ |  | 関連するカスタム セキュリティ属性のコレクション。 すべてのカスタム セキュリティ属性は、属性セットの一部である必要があります。 |
| 属性名 | ✅ |  | カスタム セキュリティ属性の名前。 属性セット内で一意である必要があります。 スペースや特殊文字を含めることはできません。 |
| 属性の説明 |  | ✅ | カスタム セキュリティ属性の説明。 |
| データ型 | ✅ |  | カスタム セキュリティ属性値のデータ型。 サポートされている型は、`Boolean`、`Integer`、`String` です。 |
| 複数の値の割り当てを許可する | ✅ |  | 複数の値をカスタム セキュリティ属性に割り当てることができるかどうかを示します。 データ型が `Boolean` に設定されている場合、[はい] に設定することはできません。 |
| 定義済みの値のみの割り当てを許可する | ✅ |  | 定義済みの値のみをカスタム セキュリティ属性に割り当てることができるかどうかを示します。 [いいえ] に設定すると、自由形式の値が許可されます。 後で [はい] から [いいえ] に変更できますが、[いいえ] から [はい] に変更することはできません。 データ型が `Boolean` に設定されている場合、[はい] に設定することはできません。 |
| 定義済みの値 |  |  | 選択されたデータ型のカスタム セキュリティ属性の定義済みの値。 定義済みの値は、後で追加することができます。 値にはスペースを含めることができますが、一部の特殊文字は使用できません。 |
| 定義済みの値はアクティブである |  | ✅ | 定義済みの値をアクティブ化するか非アクティブ化するかを指定します。 false に設定すると、サポートされているその他のディレクトリ オブジェクトに定義済みの値を割り当てることはできません。 |
| [属性はアクティブである] |  | ✅ | カスタム セキュリティ属性をアクティブ化するか非アクティブ化するかを指定します。 |

### 制限および制約

ここでは、カスタム セキュリティ属性の制限と制約について説明します。

| リソース | 制限 | 注記 |
| --- | --- | --- |
| テナントごとの属性の定義 | 5:00 | テナント内のアクティブな属性にのみ適用されます |
| テナントごとの属性セット | 5:00 |  |
| 属性セット名の長さ | 32 | Unicode 文字および大文字と小文字を区別する |
| 属性セットの説明の長さ | 128 | Unicode 文字 |
| 属性名の長さ | 32 | Unicode 文字および大文字と小文字を区別する |
| 属性の説明の長さ | 128 | Unicode 文字 |
| 定義済みの値 |  | Unicode 文字および大文字と小文字を区別する |
| 属性定義ごとの定義済みの値 | 100 |  |
| 属性値の長さ | 64 | Unicode 文字 |
| オブジェクトごとに割り当てられる属性値 | 50 | 値は、単一および複数値の属性に分散させることができます。例: それぞれ 10 個の値を持つ 5 つの属性、またはそれぞれ 1 個の値を持つ 50 個の属性 |
| 次のもので**使用できない**特殊文字:属性セット名属性名 | `<space> ` ~ ! @ # $ % ^ & * ( ) _ - + = { [ } ] \| \ : ; " ' < , > . ? /` | 属性セット名と属性名の先頭には数字を使用できません |
| 属性値で使用できる特殊文字 | すべての特殊文字 |  |
| BLOB インデックス タグで使用する場合に属性値に使用できる特殊文字 | `<space> + - . : = _ /` | [BLOB インデックス タグで属性値](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/conditions-custom-security-attributes)を使用する予定の場合、BLOB インデックス タグに使用できる特殊文字はこれらのみです。 詳細については、[BLOB インデックス タグの設定](https://learn.microsoft.com/ja-jp/azure/storage/blobs/storage-manage-find-blobs#setting-blob-index-tags)を参照してください。 |

### カスタム セキュリティ属性のロール

Microsoft Entra ID には、カスタム セキュリティ属性を処理するための組み込みロールが用意されています。 属性定義管理者のロールは、カスタム セキュリティ属性を管理するために必要な最小限のロールです。 属性割り当て管理者のロールは、ユーザーやアプリケーションなどの Microsoft Entra オブジェクトにカスタム セキュリティ属性値を割り当てるために必要な最小限のロールです。 これらのロールは、テナントのスコープまたは属性セットのスコープで割り当てることができます。

| 役割 | アクセス許可 |
| --- | --- |
| [属性定義閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-reader) | 属性セットを読み取るカスタム セキュリティ属性の定義を読み取る |
| [属性定義管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-administrator) | 属性セットのすべての側面を管理するカスタム セキュリティ属性定義のすべての側面を管理する |
| [属性割り当て閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-reader) | 属性セットを読み取るカスタム セキュリティ属性の定義を読み取るユーザーとサービス プリンシパルのカスタム セキュリティ属性のキーと値を読み取る |
| [属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator) | 属性セットを読み取るカスタム セキュリティ属性の定義を読み取るユーザーとサービス プリンシパルのカスタム セキュリティ属性のキーと値を読み取り更新する |
| [属性ログ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-log-reader) | カスタム セキュリティ属性の監査ログを読み取る |
| [属性ログ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-log-administrator) | カスタム セキュリティ属性の監査ログを読み取るカスタム セキュリティ属性の診断設定を構成する |

重要

既定では、[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)とその他の管理者ロールには、カスタム セキュリティ属性の読み取り、定義、割り当てを行う権限がありません。

### Microsoft Graph API

Microsoft Graph API を使用して、カスタム セキュリティ属性をプログラムで管理できます。 詳細については、「[Microsoft Graph API を使用したカスタム セキュリティ属性の概要](https://learn.microsoft.com/ja-jp/graph/api/resources/custom-security-attributes-overview)」を参照してください。

[Graph Explorer](https://learn.microsoft.com/ja-jp/graph/graph-explorer/graph-explorer-overview) などの API クライアントを使用して、カスタム セキュリティ属性の Microsoft Graph API をより簡単に試すことができます。

[Image: カスタム セキュリティ属性の Microsoft Graph API 呼び出しを示すスクリーンショット。]

### ライセンスの要件

この機能の使用は無料で、Azure サブスクリプションに含まれています。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/custom-security-attributes-troubleshoot"} -->
## Microsoft Entra ID のカスタム セキュリティ属性のトラブルシューティング - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-troubleshoot
- Service: entra / fundamentals
- Article date: 2024-11-27
- Summary: Microsoft Entra ID でのカスタム セキュリティ属性のトラブルシューティング方法について説明します。

### 現象 - [Add attribute set](https://learn.microsoft.com/ja-jp/entra/fundamentals/属性セットの追加) が無効になっている

[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインし、**[カスタム セキュリティ属性]**&gt;**[属性セットの追加]** オプションを選択しようとすると、無効になります。

[Image: Microsoft Entra 管理センターで無効になっている [属性セットの追加] オプションのスクリーンショット。]

**原因**

属性セットを追加するアクセス許可がありません。 属性セットとカスタム セキュリティ属性を追加するには、[属性定義管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-administrator)ロールが割り当てられている必要があります。

重要

既定では、[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)とその他の管理者ロールには、カスタム セキュリティ属性の読み取り、定義、割り当てを行う権限がありません。

**ソリューション**

テナント スコープまたは属性セット スコープのいずれかで[属性定義管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-administrator)ロールが割り当てられていることを確認します。 詳細については、「[Microsoft Entra ID でカスタム セキュリティ属性へのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-manage)」をご覧ください。

### 現象 - カスタム セキュリティ属性を割り当てようとするときにエラーが発生する

カスタム セキュリティ属性の割り当てを保存しようとすると、次のメッセージが表示されます。

```
Insufficient privileges to save custom security attributes
This account does not have the necessary admin privileges to change custom security attributes
```

**原因**

カスタム セキュリティ属性を割り当てるアクセス許可がありません。 カスタム セキュリティ属性を割り当てるには、[属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)ロールが割り当てられている必要があります。

重要

既定では、[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)とその他の管理者ロールには、カスタム セキュリティ属性の読み取り、定義、割り当てを行う権限がありません。

**ソリューション**

テナント スコープまたは属性セット スコープのいずれかで[属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)ロールが割り当てられていることを確認します。 詳細については、「[Microsoft Entra ID でカスタム セキュリティ属性へのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-manage)」をご覧ください。

### 現象 - ユーザーまたはアプリケーションのカスタム セキュリティ属性をフィルター処理できない

**原因 1**

カスタムセキュリティ属性をフィルターする権限がありません。 ユーザーまたはエンタープライズ アプリケーションのカスタム セキュリティ属性を読み取ってフィルター処理するには、[属性割り当て閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-reader)または[属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)ロールが割り当てられている必要があります。

重要

既定では、[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)とその他の管理者ロールには、カスタム セキュリティ属性の読み取り、定義、割り当てを行う権限がありません。

**解決策 1**

テナント スコープまたは属性セット スコープのいずれかで以下の Microsoft Entra 組み込みロールのいずれかが割り当てられていることを確認します。 詳細については、「[Microsoft Entra ID でカスタム セキュリティ属性へのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-manage)」をご覧ください。

- [属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)
- [属性割り当てリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-reader)

**原因 2**

属性割り当て閲覧者または属性割り当て管理者のロールが割り当てられていますが、属性セットへのアクセス権が割り当てられていません。

**解決策 2**

カスタム セキュリティ属性の管理は、テナント スコープまたは属性セット スコープで委任できます。 テナント スコープまたは属性セット スコープのいずれかで属性セットへのアクセス権が割り当てられているようにしてください。 詳細については、「[Microsoft Entra ID でカスタム セキュリティ属性へのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-manage)」をご覧ください。

**原因 3**

テナントに対して定義および割り当てられたカスタム セキュリティ属性はまだありません。

**解決策 3**

カスタム セキュリティ属性を追加し、ユーザーまたはエンタープライズ アプリケーションに割り当てます。 詳細については、「[Microsoft Entra ID でのカスタム セキュリティ属性定義の追加または非アクティブ化](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-add)」、「[ユーザーのカスタム セキュリティ属性の割り当て、更新、一覧表示、削除](https://learn.microsoft.com/ja-jp/entra/identity/users/users-custom-security-attributes)」、または「[アプリケーションのカスタム セキュリティ属性の割り当て、更新、一覧表示、削除](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/custom-security-attributes-apps)」を参照してください。

### 現象 - カスタム セキュリティ属性を削除できない

**原因**

カスタム セキュリティ属性定義のみをアクティブ化および非アクティブ化できます。 カスタム セキュリティ属性の削除はサポートされていません。 非アクティブ化された定義は、テナント全体で 500 個の定義の制限にカウントされません。

**ソリューション**

不要になったカスタム セキュリティ属性を非アクティブ化します。 詳細については、「[Microsoft Entra ID でのカスタム セキュリティ属性定義の追加または非アクティブ化](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-add)」を参照してください。

### 現象 - PIM を使用して属性セット スコープでロールの割り当てを追加できない

[Microsoft Entra Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) を使って適格な Microsoft Entra ロールの割り当てを追加しようとする場合、スコープを属性セットに設定することはできません。

**原因**

PIM では現在、属性セット スコープへの適格な Microsoft Entra ロール割り当ての追加がサポートされません。

### 症状 - この操作を完了するのに十分な特権がありません

[Graph エクスプローラー](https://learn.microsoft.com/ja-jp/graph/graph-explorer/graph-explorer-overview)を使用してカスタム セキュリティ属性の Microsoft Graph API を呼び出そうとすると、次のようなメッセージが表示されます。

```
Forbidden - 403. You need to consent to the permissions on the Modify permissions (Preview) tab
Authorization_RequestDenied
Insufficient privileges to complete the operation.
```

[Image: Graph エクスプローラーのスクリーンショット。十分な特権がないというエラー メッセージが表示されています。]

または、PowerShell コマンドを使用しようとすると、次のようなメッセージが表示されます。

```
Insufficient privileges to complete the operation.
Status: 403 (Forbidden)
ErrorCode: Authorization_RequestDenied
```

**原因 1**

Graph エクスプローラーを使用していて、API 呼び出しを行うために必要なカスタム セキュリティ属性のアクセス許可に同意していません。

**解決策 1**

[アクセス許可] パネルを開き、適切なカスタム セキュリティ属性のアクセス許可を選択して、**[同意]** を選択します。 表示される [アクセス許可の要求] ウィンドウで、要求されたアクセス許可を確認します。

[Image: Graph エクスプローラーの [アクセス許可] パネルのスクリーンショット。CustomSecAttributeDefinition が選択されています。]

**原因 2**

API 呼び出しを行うために必要なカスタム セキュリティ属性ロールが割り当てられていません。

重要

既定では、[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)とその他の管理者ロールには、カスタム セキュリティ属性の読み取り、定義、割り当てを行う権限がありません。

**解決策 2**

必要なカスタム セキュリティ属性ロールが割り当てられていることを確認します。 詳細については、「[Microsoft Entra ID でカスタム セキュリティ属性へのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-manage)」をご覧ください。

**原因 3**

`null`[または Update-MgServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/update-mguser) コマンドを使用して  に設定して、単一値のカスタム セキュリティ属性の割り当てを削除しようとしています。

**解決策 3**

代わりに、[Invoke-MgGraphRequest](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-invoke-mggraphrequest) コマンドを使用してください。 詳細については、「[ユーザーから単一値のカスタム セキュリティ属性の割り当てを削除する](https://learn.microsoft.com/ja-jp/entra/identity/users/users-custom-security-attributes#remove-a-single-valued-custom-security-attribute-assignment-from-a-user)」または「[アプリケーションからカスタム セキュリティ属性の割り当てを削除する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/custom-security-attributes-apps#remove-custom-security-attribute-assignments-from-applications-using-microsoft-graph-powershell)」を参照してください。

### 症状 - Request\_UnsupportedQuery エラー

カスタム セキュリティ属性の Microsoft Graph API を呼び出そうとすると、次のようなメッセージが表示されます。

```
Bad Request - 400
Request_UnsupportedQuery
Unsupported or invalid query filter clause specified for property '<AttributeSet>_<Attribute>' of resource 'CustomSecurityAttributeValue'.
```

**原因**

要求の形式が正しくありません。

**ソリューション**

必要に応じて、要求またはヘッダーに `ConsistencyLevel=eventual` を追加します。 要求が正しくルーティングされるように、`$count=true` を含めることも必要になる場合があります。 詳細については、「[例: Microsoft Graph API を使用したカスタム セキュリティ属性割り当ての割り当て、更新、一覧表示、削除](https://learn.microsoft.com/ja-jp/graph/custom-security-attributes-examples)」を参照してください。

[Image: ConsistencyLevel ヘッダーが追加された Graph エクスプローラーのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/data-operational-considerations"} -->
## データ運用面の考慮事項 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/data-operational-considerations
- Service: entra / fundamentals
- Article date: 2023-01-31
- Summary: ここでは、その方法について説明します。

この記事では、構成に関するデータ運用面の考慮事項について説明します。 使用状況データやオペレーターのセキュリティなど、Microsoft Entra ID に関連して、ログ ファイルやその他の機能がどのように機能するかについての情報があります。 Microsoft Entra チームによるデプロイと変更の定義方法に関するガイダンスに加えて、物理的なセキュリティに関する考慮事項について学習します。

### ログ ファイル

Microsoft Entra ID では、サービス内のアクションとイベントの監査、調査、デバッグのためにログ ファイルが生成されます。 ログ ファイルには、ユーザー、デバイス、Microsoft Entra の構成 (ポリシー、アプリ、グループなど) に関するデータが含まれている場合があります。 ログ ファイルが作成され、Microsoft Entra サービスが実行されているデータセンターの Azure Storage に格納されます。

ログ ファイルは、ローカル デバッグ、セキュリティ、使用状況の分析、システム稼働状況の監視、サービス全体の分析に使用されます。 これらのログは、米国大陸の Microsoft 所有データセンターにある Microsoft レポート 機械学習システムへのトランスポート層セキュリティ (TLS) 接続を介してコピーされます。

### 使用状況データ

使用状況データは、Microsoft Entra サービスによって生成されるメタデータであり、サービスの使用状況を示します。 このメタデータは、管理者向けレポートとユーザー向けレポートを生成するために使用されます。 Microsoft Entra エンジニアリング チームは、このメタデータを使ってシステムの使用状況を評価し、サービスを改善する機会を特定します。 通常、このデータはログ ファイルに書き込まれますが、場合によっては、サービスの監視およびレポート システムによって収集されます。

### オペレーターのセキュリティ

Microsoft の担当者、請負業者、ベンダー (システム管理者) による Microsoft Entra ID へのアクセスは、厳しく制限されています。 デプロイ、デバッグ、診断情報の収集、サービスの再起動などの日常的な作業に対するユーザーの介入は、可能な限り、自動化されたツールベースのプロセスに置き換えられます。

管理者アクセスは、資格のある一部のエンジニアに限定され、フィッシングに強い資格情報を使用して認証手続きを完了する必要があります。 システムアクセスや更新機能は、Microsoft Just-In-Time (JIT) 特権アクセス管理システムによって管理されるロールに割り当てられます。 システム管理者は JIT システムを使用して昇格を要求し、JIT システムはその要求を手動承認または自動承認にルーティングします。 承認されると、JIT によってアカウントが昇格されます。 昇格、承認、ロールへの昇格、ロールからの削除の要求は、将来のデバッグや調査のためにログに記録されます。

Microsoft の担当者は、社内用の分離された強力な認証 ID プラットフォームを使用する、セキュリティ保護されたアクセス ワークステーションからのみ操作を実行できます。 他の Microsoft ID システムへのアクセス権では、セキュリティ アクセス ワークステーションへのアクセスは許可されません。 ID プラットフォームは、他の Microsoft ID システムとは別に実行されています。

### 物理的なセキュリティ

Microsoft Entra サービスを構成するサーバーへの物理的なアクセス、および Microsoft Entra のバックエンド システムへのアクセスは、Azure の施設、敷地、物理的なセキュリティによって制限されています。 Microsoft Entra のお客様は物理的な資産や場所にアクセスできないため、論理的なロールベースのアクセス制御 (RBAC) ポリシー チェックを回避することはできません。 オペレーター アクセス権を持つ担当者には、メンテナンスのために承認されたワークフローを実行する権限があります。

詳細情報: [Azure の施設、敷地、および物理上のセキュリティ](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/physical-security)

### 変更制御のプロセス

データセンター間でサービスへの変更をロールアウトするために、Microsoft Entra チームはデプロイ環境のレイヤーを定義します。 変更レイヤーの適用は、厳密な終了基準によって制限されています。 変更をレイヤー間でロールアウトする時間は、運用チームによって定義されており、起こり得る影響に基づいて決められています。 通常、ロールアウトには 1 から 2 週間かかります。 セキュリティ修正プログラムやホット フィックスなどの重要な変更は、より迅速に展開できます。 デプロイ レイヤーに適用されたときに変更が終了基準を満たさない場合は、以前の安定した状態にロールバックされます。

### リソース

- [Microsoft サービス Trust ドキュメント](https://servicetrust.microsoft.com/Documents/TrustDocuments)
- [Microsoft Azure 信頼できるクラウド](https://azure.microsoft.com/explore/trusted-cloud/)
- [Microsoft 365 データの場所](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/o365-data-locations)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/data-protection-considerations"} -->
## データ保護に関する考慮事項 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/data-protection-considerations
- Service: entra / fundamentals
- Article date: 2023-01-31
- Summary: サービスが RBAC 承認レイヤー経由で Microsoft Entra オブジェクト データを格納および取得する方法について説明します。

次の図は、サービスがロールベースのアクセス制御 (RBAC) 承認レイヤー経由で Microsoft Entra オブジェクト データを格納および取得する方法を示しています。 このレイヤーは内部ディレクトリ データ アクセス層を呼び出して、ユーザーのデータ要求が許可されていることを確認します。

[Image: Microsoft Entra オブジェクト データを格納および取得するサービスの図。]

**Microsoft Entra の内部インターフェイス アクセス**: Microsoft 365 などの他の Microsoft サービスとのサービス間通信では、クライアント証明書を使用してサービスの呼び出し元を承認する Microsoft Entra ID インターフェイスを使用します。

**Microsoft Entra の外部インターフェイス アクセス**: Microsoft Entra 外部インターフェイスは、RBAC を使用してデータ漏えいを防ぐのに役立ちます。 ユーザーなどのセキュリティ プリンシパルが、Microsoft Entra ID インターフェイス経由で情報を読み取るアクセス要求を行うときは、セキュリティ トークンが要求に付随している必要があります。 トークンには、要求を行うプリンシパルに関するクレームが含まれています。

セキュリティ トークンは、Microsoft Entra 認証サービスによって発行されます。 承認システムでは、ユーザーの存在、有効状態、ロールに関する情報を使用して、ターゲット テナントへのアクセス要求を当該セッションの当該ユーザーに対して承認するかどうかを決定します。

**アプリケーション アクセス**: アプリケーションはユーザー コンテキストなしでアプリケーション プログラミング インターフェイス (API) にアクセスできるため、アクセス チェックには、ユーザーのアプリケーションに関する情報と、要求されたアクセスのスコープ (読み取り専用、読み取り/書き込みなど) が含まれます。 多くのアプリケーションでは、OpenID Connect または Open Authorization (OAuth) を使用して、ユーザーに代わってディレクトリにアクセスするためのトークンを取得します。 これらのアプリケーションには、ディレクトリへのアクセス権を明示的に付与する必要があり、これを行わない場合は、Microsoft Entra 認証サービスからトークンを受信せず、付与されているスコープからデータにアクセスします。

**監査**: アクセスは監査されます。 たとえば、ユーザーの作成やパスワードのリセットなどの承認されたアクションは監査証跡を作成し、それをテナント管理者がコンプライアンスへの取り組みや調査の管理に使用できます。 テナント管理者は、Microsoft Entra の監査 API を使用して監査レポートを生成できます。

詳細情報: [Microsoft Entra ID の監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)に関するページを参照してください

**テナントの分離**: Microsoft Entra マルチテナント環境でのセキュリティの適用は、次の 2 つの主な目標を達成するのに役立ちます。

- テナント間でのデータ漏えいとアクセスの防止: テナント 1 に属するデータを、テナント 1 による明示的な承認なしにテナント 2 のユーザーが取得することはできません。
- テナント間のリソース アクセスの分離: テナント 1 によって実行される操作は、テナント 2 のリソースへのアクセスには影響しません。

### テナントの分離

テナントの分離の概要を次の情報に示します。

- サービスは、RBAC ポリシーを使用してテナントをセキュリティで保護し、データの分離を確保します。
- テナントへのアクセスを有効にするには、プリンシパル (ユーザーやアプリケーションなど) が、コンテキストを取得するために Microsoft Entra ID に対して認証を行うことができ、テナントで定義されている明示的なアクセス許可を持っている必要があります。 テナントでプリンシパルが承認されない場合、結果のトークンはアクセス許可を伝達せず、RBAC システムはこのコンテキストでの要求を拒否します。
- RBAC により、テナントへのアクセスは、テナントで承認されたセキュリティ プリンシパルによって実行されるようになります。 テナント管理者が同じテナントにセキュリティ プリンシパル表現を作成するか (B2B コラボレーションを使用してゲスト ユーザー アカウントをプロビジョニングする場合など)、テナント管理者が別のテナントとの信頼関係を有効にするポリシーを作成すると、テナント間でのアクセスが可能になります。 たとえば、B2B 直接接続を有効にするクロステナント アクセス ポリシーなどです。 各テナントは分離境界です。あるテナント内の存在は、管理者が許可しない限り、別のテナント内の存在と同じにはなりません。
- 複数のテナントの Microsoft Entra データは、同じインフラストラクチャに格納されます。 データへのアクセスは RBAC 承認システムによって保護されるため、分離が保証されます。
- 顧客アプリケーションが、必要な認証なしに Microsoft Entra ID にアクセスすることはできません。 最初の接続ネゴシエーション プロセスの一部として資格情報が付随していない場合、要求は拒否されます。 この働きにより、近隣テナントによるテナントへの不正アクセスが防止されます。 フェデレーション信頼を使用して仲介されるのは、ユーザー資格情報のトークン、または Security Assertion Markup Language (SAML) トークンのみです。 そのため、アプリケーション所有者が構成した共有キーに基づいて、Microsoft Entra ID によって検証されます。
- コア ストアから実行できるアプリケーション コンポーネントはないため、1 つのテナントが近隣テナントの整合性を強制的に侵害することは不可能です。

### データのセキュリティ

**転送中の暗号化**: データのセキュリティを確保するために、Microsoft Entra ID のディレクトリ データは、スケール ユニット内のデータ センター間の転送中、署名および暗号化されています。 データの暗号化と非暗号化は、Microsoft Entra コア ストア層によって行われます。これは、関連付けられている Microsoft データセンターのセキュリティで保護されたサーバー ホスティング領域内に存在します。

顧客向け Web サービスは、トランスポート層セキュリティ (TLS) プロトコルを使用してセキュリティで保護されます。

**シークレット ストレージ**: Microsoft Entra サービスのバックエンドでは、暗号化を使用して、Microsoft 独自のテクノロジを使用した証明書、キー、資格情報、ハッシュなどの、サービス使用向けの機密性の高いデータを格納します。 使用されるストアは、サービス、操作、シークレットのスコープ (ユーザー全体かテナント全体)、およびその他の要件によって異なります。

これらのストアは、証明書の要求、更新、失効、破棄などの、確立された自動化とワークフローを介してセキュリティに重点を置いたグループによって運用されています。

これらのストア/ワークフロー/プロセスに関連するアクティビティ監査があり、永続的なアクセスはありません。 アクセスは要求と承認に基づいていて、時間が限られています。

保存時のシークレット暗号化の詳細については、次の表を参照してください。

**アルゴリズム**: 次の表に、Microsoft Entra コンポーネントで使用される最小暗号化アルゴリズムの一覧を示しています。 Microsoft はクラウド サービスとして、セキュリティの調査結果、内部セキュリティ レビュー、ハードウェアの進化に対する鍵の強さなどに基づき、暗号化を再評価して、改善を行っています。

| データ/シナリオ | 暗号アルゴリズム |
| --- | --- |
| パスワード ハッシュ同期クラウド アカウントのパスワード | ハッシュ: ハッシュ ベースのメッセージ認証コード (HMAC)-SHA256 @ 1,000 イテレーションを使用したパスワード キー派生関数 2 (PBKDF2) |
| データセンター間の転送中のディレクトリ | AES-256-CTS-HMAC-SHA1-96TLS\_ECDHE\_ECDSA\_WITH\_AES\_256\_GCM\_SHA384 |
| パススルー認証ユーザー資格情報フロー | RSA 2048 の公開/秘密キー ペア  詳細情報: [Microsoft Entra パススルー認証のセキュリティに関する詳細](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-security-deep-dive) |
| Microsoft Entra Connect を使用したセルフサービス パスワード リセット パスワード ライトバック: クラウドからオンプレミスへの通信 | RSA 2048 の公開/秘密キー ペアAES\_GCM (256 ビット キー、96 ビット IV サイズ) |
| セルフサービス パスワード リセット: セキュリティの質問への回答 | SHA256 |
| Microsoft Entra アプリケーションの SSL 証明書プロキシで公開されたアプリケーション | AES-GCM 256 ビット |
| ディスク レベルの暗号化 | XTS-AES 128 |
| [シームレス シングル サインオン (SSO)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-how-it-works) サービス アカウント パスワードサービスとしてのソフトウェア (SaaS) アプリケーションのプロビジョニング資格情報 | AES-CBC 128 ビット |
| Azure リソースのマネージド ID | AES-GCM 256 ビット |
| Microsoft Authenticator アプリ: Microsoft Entra ID へのパスワードレスのサインイン | 非対称 RSA キー 2048 ビット |
| Microsoft Authenticator アプリ: エンタープライズ アカウント メタデータのバックアップと復元 | AES-256 |
| Microsoft Entra のバックアップと回復のスナップショット | AES-256 |

### リソース

- [Microsoft Service Trust ドキュメント](https://servicetrust.microsoft.com/Documents/TrustDocuments)
- [Microsoft Azure トラスト センター](https://azure.microsoft.com/overview/trusted-cloud/)
- [Microsoft Entra ID での削除からの回復](https://learn.microsoft.com/ja-jp/entra/architecture/recover-from-deletions)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/data-residency"} -->
## Microsoft Entra ID とデータ所在地 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/data-residency
- Service: entra / fundamentals
- Article date: 2025-11-06
- Summary: 所在地データを使用してアクセスを管理し、モビリティ シナリオを実現し、組織をセキュリティで保護します。

Microsoft Entra ID は、クラウド内の ID およびアクセス データを保存および管理する、サービスとしての ID (IDaaS) ソリューションです。 データを使用して、クラウド サービスへのアクセスを有効化および管理し、モビリティ シナリオを実現し、組織をセキュリティで保護できます。 [テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-glossary#tenant)と呼ばれる Microsoft Entra ID サービスのインスタンスは、顧客がプロビジョニングして所有するディレクトリ オブジェクト データの分離されたセットです。

注

Microsoft Entra External ID は、顧客向けのアプリと顧客ディレクトリ データ用に作成された別のテナントにデータを格納および管理するためのオプションと柔軟性を備えた顧客 ID およびアクセス管理 (CIAM) ソリューションです。 このテナントは外部テナントと呼ばれます。 外部テナントを作成するときに、データ ストレージの地理的な場所 (管理ポータルで "国/リージョン" と表示) を選択するオプションがあります。 この記事で示すように、データの場所と利用できるリージョンは、Microsoft Entra ID とは異なる場合があることに注意してください。

### コア ストア

Core Store は、スケール ユニットに保存されているテナントで構成され、各スケール ユニットには複数のテナントが含まれます。 Microsoft Entra Core Store でのデータの更新または取得操作は、ユーザーのセキュリティ トークンに基づいて 1 つのテナントに関連付けられ、これによりテナントの分離が実現されます。 スケール ユニットは、地理的な場所に割り当てられます。 それぞれの地理的な場所では、2 つ以上の Azure リージョンを使用してデータを格納します。 各 Azure リージョンでは、Microsoft Entra アーキテクチャに記載されているように、スケールユニットのデータが回復性とパフォーマンスのために物理データセンター にレプリケートされます。

コアストアについての詳細は、「[Microsoft Entra コアストア スケールユニット](https://www.youtube.com/watch?v=OcKO44GtHh8)」をご覧ください。 Azure リージョンの詳細については、Azure の地域に関するページを参照してください。

Microsoft Entra ID は、次のクラウドで使用できます。

- パブリック
- 中国 (2)
- 米国政府 (2)

*(2) 現在、外部テナントでは使用できません。*

パブリック クラウドでは、テナントの作成時 (たとえば、Office 365 または Azure にサインアップしたり、Azure portal を使用してさらに Microsoft Entra インスタンスを作成したりする) 時点で、場所 (管理ポータルで "国/リージョン" と表示されます) を選択するように求められます。 Microsoft Entra ID は、選択内容を地理的ロケーションと、その中の 1 つのスケール ユニットにマップします。 テナントの場所を設定後に変更することはできません。

テナント作成中に選択された場所は、次のいずれかの地理的な場所にマップされます。

- オーストラリア (1)
- アジア/太平洋
- ヨーロッパ、中近東およびアフリカ (EMEA)
- 日本 (1)
- 北米
- 世界全域

*(1) Go-Local アドオンを使用する外部テナントで使用できます。*

Microsoft Entra ID は、ユーザービリティ、パフォーマンス、所在地、または地理的な場所に基づくその他の要件に基づいてコア ストア データを処理します。 Microsoft Entra ID は、次の条件に基づいて、スケール ユニットを使用してデータセンター間で各テナントをレプリケートします。

- 待ち時間やユーザーのサインイン時間を短縮するために、テナント所在地の場所に最も近いデータ センターに格納されている Microsoft Entra Core Store データ
- 1 つのデータセンターでの予期しない致命的なイベント中の可用性を確保するために、地理的に分離されたデータ センターに格納されている Microsoft Entra Core Store データ
- 特定の顧客および地理的な場所のためのデータ所在地やその他の要件への準拠

### Microsoft Entra のクラウド ソリューション モデル

次の表を使用して、インフラストラクチャ、データの場所、運用主権に基づいた Microsoft Entra クラウド ソリューション モデルを確認してください。

| Model | 場所 | データの場所 | 運用担当者 | このモデルでのテナントの配置 |
| --- | --- | --- | --- | --- |
| パブリックな地理的な場所 | オーストラリア (1)、北米、EMEA、日本 (1)、アジア/太平洋 | 保存時は、ターゲットの場所の中。 コンポーネント サービスまたは機能別の例外 (次のセクションに記載) | Microsoft が運用。 Microsoft データセンターの担当者はバックグラウンド チェックに合格する必要があります。 | サインアップ エクスペリエンスでテナントを作成します。 データ所在地の場所を選択します。 |
| 世界中のパブリックな場所 | 世界全域 | すべての場所 | Microsoft が運用。 Microsoft データセンターの担当者はバックグラウンド チェックに合格する必要があります。 | 公式のサポート チャネルを経由し、かつ Microsoft の裁量に従って実行可能なテナント作成。 |
| ソブリン クラウドまたは各国のクラウド | 米国政府 (2)、中国 (2) | 保存時は、ターゲットの場所の中。 例外。 | データカストディアンによって運営されています (3)。 担当者は要件に従ってスクリーニングされます。 | 各国のクラウド インスタンスごとに、サインアップ エクスペリエンスがあります。 |

**テーブル参照**:

- (1) これらの場所は、 Go-Local アドオンを使用する外部テナントで使用できます。
- (2) これらの場所は、現在、外部テナントでは使用できません。
- (3) **データカストディアン**: 米国政府機関向けクラウド内のデータセンターは、Microsoft によって運営されています。 中国では、Microsoft Entra ID は [21Vianet](https://learn.microsoft.com/ja-jp/microsoft-365/admin/services-in-china/services-in-china?redirectSourcePath=%252fen-us%252farticle%252fLearn-about-Office-365-operated-by-21Vianet-a8ab5061-3346-4da0-bb7c-5260822b53ae&view=o365-21vianet&viewFallbackFrom=o365-worldwide&preserve-view=true) とのパートナーシップを通じて運用されています。

詳細情報:

- [ヨーロッパの顧客のための Microsoft Entra ID での顧客データの格納と処理](https://learn.microsoft.com/ja-jp/entra/fundamentals/data-storage-eu)
- Microsoft Entra ID のオーストラリアおよびニュージーランドのお客様向けの顧客データ ストレージと、Microsoft Entra ID のオーストラリアおよびニュージーランドのお客様向けの ID データ ストレージ
- [Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/fundamentals/data-storage-japan) での日本のお客様向けの顧客データ ストレージ
- [Microsoft セキュリティ センター - データが配置されている場所](https://www.microsoft.com/en-us/trust-center/privacy/data-location)

### Microsoft Entra コンポーネント全体のデータ所在地

詳細情報: [Microsoft Entra 製品の概要](https://www.microsoft.com/cloud-platform/azure-active-directory-features)

注

Exchange Online や Skype for Business など、Microsoft Entra ID を超える他のサービスのサービス データの場所については、対応するサービス ドキュメントと [セキュリティ センター](https://www.microsoft.com/trust-center/privacy/data-location)を参照してください。

#### Microsoft Entra コンポーネントとデータ ストレージの場所

| Microsoft Entra コンポーネント | 説明 | データ ストレージの場所 |
| --- | --- | --- |
| Microsoft Entra 認証サービス | このサービスはステートレスです。 認証用のデータは、Microsoft Entra Core Store にあります。 ディレクトリ データはありません。 Microsoft Entra 認証サービスは、Azure Storage と、サービス インスタンスが実行されているデータセンターでログ データを生成します。 ユーザーが Microsoft Entra ID を使用して認証しようとすると、Microsoft Entra 論理リージョンに含まれている、地理的に最も近いデータセンター内のインスタンスにルーティングされます。 | 地理的な場所の中 |
| Microsoft Entra の ID およびアクセス管理 (IAM) サービス | **ユーザーと管理のエクスペリエンス**: Microsoft Entra 管理エクスペリエンスはステートレスであり、ディレクトリ データはありません。 ログと使用状況データが生成され、Azure テーブル ストレージに保存されます。 ユーザー エクスペリエンスは、Azure portal と似ています。 **ID 管理ビジネス ロジックとレポート サービス**: これらのサービスには、グループとユーザー用にローカルにキャッシュされたデータ ストレージがあります。 これらのサービスにより、ログと使用状況データが生成され、Azure テーブル ストレージ、Azure SQL、および Microsoft Elastic Search レポート サービスに送られます。 | 地理的な場所の中 |
| Microsoft Entra 多要素認証 | 多要素認証操作のデータ ストレージと保持の詳細については、「[Microsoft Entra 多要素認証のデータ所在地と顧客データ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-data-residency)」を参照してください。 Microsoft Entra 多要素認証では、ユーザー プリンシパル名 (UPN)、音声通話の電話番号、SMS チャレンジがログに記録されます。 モバイル アプリ モードへのチャレンジの場合、サービスは UPN と一意のデバイス トークンをログに記録します。 | 北米または地理的な場所 |
| Microsoft Entra Domain Services | 「[リージョン別の利用可能な製品](https://azure.microsoft.com/regions/services/)」で、Microsoft Entra Domain Services が公開されているリージョンを参照してください。 このサービスは、Azure テーブルにシステム メタデータをグローバルに保持し、個人データは含まれません。 | 地理的な場所の中 |
| Microsoft Entra Connect ヘルス | Microsoft Entra Connect Health は、Azure テーブル ストレージと BLOB ストレージにアラートとレポートを生成します。 | 地理的な場所の中 |
| Microsoft Entra 動的メンバーシップ グループ、Microsoft Entra セルフサービス グループ管理 | Azure テーブル ストレージには、動的メンバーシップ グループに関する規則の定義が保持されます。 | 地理的な場所の中 |
| Microsoft Entra アプリケーション プロキシ | Microsoft Entra アプリケーション プロキシは、テナント、コネクタ マシン、および構成データに関するメタデータを Azure SQL に保存します。 | 地理的な場所の中 |
| Microsoft Entra Connect での Microsoft Entra パスワード ライトバック | 初期構成時に、Microsoft Entra Connect は、Rivest-Shamir-Adleman (RSA) 暗号化システムを使用して非対称キー ペアを生成します。 次に、セルフサービス パスワード リセット (SSPR) クラウド サービスに公開キーを送信します。これにより、次の 2 つの操作が実行されます。1. Microsoft Entra Connect オンプレミス サービス用の 2 つの Azure Service Bus リレーを作成して、SSPR サービスと安全に通信する  2. Advanced Encryption Standard (AES) キー K1 を生成する  Azure Service Bus リレーの場所、対応するリスナー キー、AES キー (K1) のコピーが、応答で Microsoft Entra Connect に送られます。 今後の SSPR と Microsoft Entra Connect の間の通信は、新しい ServiceBus チャネル経由で行われ、SSL を使用して暗号化されます。  操作中に送信された新しいパスワード リセットは、クライアントによってオンボード中に生成された RSA 公開キーで暗号化されます。 それらは、Microsoft Entra Connect マシン上の秘密キーによって暗号化解除されるため、パイプライン サブシステムがプレーンテキスト パスワードにアクセスすることを防止できます。  AES キーはメッセージ ペイロード (暗号化されたパスワード、追加データ、メタデータ) を暗号化します。これにより、悪意のある ServiceBus 攻撃者が内部 ServiceBus チャネルへのフル アクセスを持っている場合でも、ペイロードの改ざんを防止できます。  パスワード ライトバックの場合、Microsoft Entra Connect にはキーとデータが必要です。  - リセット ペイロードを暗号化する AES キー (K1)、または ServiceBus パイプライン経由での SSPR サービスから Microsoft Entra Connect への変更要求  - リセットまたは変更要求ペイロードでパスワードを暗号化解除する非対称キー ペアからの秘密キー  - ServiceBus リスナー キー  AES キー (K1) と非対称キーペアは、少なくとも 180 日ごとにローテーションされます。これは、特定のオンボードまたはオフボード構成イベント中に変更できる期間です。 たとえば、顧客がパスワード ライトバックを無効にしてから再度有効にした場合などです。これは、サービスやメンテナンス中のコンポーネントのアップグレード時に発生することがあります。  Microsoft Entra Connect データベースに保存されているライトバック キーとデータは、データ保護アプリケーション プログラミング インターフェイス (DPAPI) (CALG\_AES\_256) によって暗号化されます。 その結果、マスター ADSync 暗号化キーが生成されます。これは、ADSync オンプレミス サービス アカウントのコンテキストで Windows Credential Vault に保存されます。 Windows Credential Vault では、サービス アカウントのパスワードが変更されると、シークレットの再暗号化が自動的に行われます。 サービス アカウントのパスワードをリセットすると、サービス アカウントの Windows Credential Vault 内のシークレットが無効になります。 新しいサービス アカウントを手動で変更すると、保存されているシークレットが無効になる可能性があります。  既定では、ADSync サービスは仮想サービス アカウントのコンテキストで実行されます。 アカウントは、インストール中に最小特権ドメイン サービス アカウント、マネージド サービス アカウント (Microsoft アカウント)、またはグループ管理サービス アカウント (gMSA) にカスタマイズされる場合があります。 仮想サービス アカウントとマネージド サービス アカウントにはパスワードの自動ローテーションが備わっていますが、カスタム プロビジョニングされたドメイン アカウントのパスワード ローテーションは顧客が管理します。 前述したように、パスワードをリセットすると、保存されているシークレットが失われます。 | 地理的な場所の中 |
| Microsoft Entra デバイス登録サービス | Microsoft Entra Device Registration Service では、コンピューターとデバイスのライフサイクル管理がディレクトリで行われます。これにより、デバイス状態の条件付きアクセスやモバイル デバイス管理などのシナリオが可能になります。 | 地理的な場所の中 |
| Microsoft Entra プロビジョニング | Microsoft Entra プロビジョニングでは、サービスとしてのソフトウェア (SaaS) アプリケーションなどのシステム内のユーザーを作成、削除、更新します。 これは、クラウド人事ソース (Workday など) からの Microsoft Entra ID およびオンプレミス Microsoft Windows Server Active Directory でのユーザー作成を管理します。 サービスは、その構成を Azure Cosmos DB インスタンスに保存します。ここには、保持しているユーザー ディレクトリのグループ メンバーシップ データが保存されます。 Azure Cosmos DB は、Microsoft Entra クラウド ソリューション モデルに従って、テナントと同じリージョン内の複数のデータセンターにデータベースをレプリケートしてデータを分離します。 レプリケーションにより、高可用性と複数の読み取りおよび書き込みのエンドポイントが作成されます。 Azure Cosmos DB にはデータベース情報に対する暗号化があり、暗号化キーは Microsof t用のシークレット ストレージに保存されます。 | 地理的な場所の中 |
| Microsoft Entra 企業間 (B2B) コラボレーション | Microsoft Entra B2B Collaboration にはディレクトリ データがありません。 他のテナントとの B2B 関係にあるユーザーおよびその他のディレクトリ オブジェクトは、ユーザー データが他のテナントにコピーされるため、データ所在地に影響を与える可能性があります。 | 地理的な場所の中 |
| Microsoft Entra ID Protection（マイクロソフト エントラ ID 保護） | Microsoft Entra ID Protection では、企業や業界のソースからの複数のシグナルを含むリアルタイムのユーザー ログイン データを使用して、異常なログインを検出する機械学習システムにフィードします。 個人データは、機械学習システムに渡される前に、リアルタイムのログイン データからスクラブされます。 残りのログイン データは、潜在的にリスクの高いユーザー名とログインを識別します。 分析後、データはMicrosoft レポート システムに送られます。 リスクのあるログインとユーザー名は、管理者向けのレポートに表示されます。 | 地理的な場所の中 |
| Azure リソースのマネージド ID | マネージド ID システムを使用する Azure リソースのマネージド ID は、資格情報を保存せずに Azure サービスに対する認証を行うことができます。 マネージド ID は、ユーザー名とパスワードを使用するのではなく、証明書を使用して Azure サービスに対する認証を行います。 このサービスは、発行する証明書を米国東部リージョンの Azure Cosmos DB に書き込みます。これは、必要に応じて別のリージョンにフェールオーバーします。 Azure Cosmos DB geo 冗長性は、グローバル データ レプリケーションによって発生します。 データベース レプリケーションでは、Microsoft Entra マネージド ID が実行される各リージョンに読み取り専用コピーを配置します。 詳細については、「[マネージド ID を使用して他のサービスにアクセスできる Azure サービス](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identities-status)」を参照してください。 Microsoft は、Microsoft Entra クラウド ソリューション モデル内の各 Azure Cosmos DB インスタンスを分離します。  仮想マシン (VM) ホストなどのリソース プロバイダーは、認証用の証明書と ID フローを他の Azure サービスと共に保存します。 このサービスは、Azure Cosmos DB にアクセスするためのマスター キーをデータセンター シークレット管理サービスに保存します。 Azure Key Vault には、マスター暗号化キーが保存されます。 | 地理的な場所の中 |
| Microsoft Entra のバックアップと回復のスナップショット | Microsoft は、特定のディレクトリ データのスナップショットを定期的に自動的に取得します。このスナップショットは、Microsoft が管理する Azure ストレージに格納され、テナントと同じリージョン内の複数のデータセンターにレプリケートされます。 レプリケーションにより、高可用性と複数の読み取りおよび書き込みのエンドポイントが作成されます。 スナップショットは、Microsoft マネージド キーを使用して、AES-256 暗号化アルゴリズムを使用して暗号化されます。 Microsoft Entra Backup and Recovery によって作成されたスナップショットを削除または変更するために、Microsoft Entra のアクセス許可またはロールを使用できません。 | 地理的な場所の中 |

### Go-Local アドオン

Go-Local アドオンは、Microsoft Entra External ID の機能です。これにより、特定の顧客は、国や地域など、選択した地理的な場所に保存時にデータを格納するように一部のサービスを構成できます。 この機能は、企業のポリシーとコンプライアンス要件を満たす方法です。 [外部テナントを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)するときに、データ ストレージの国またはリージョンを選択します。

Go-Local アドオンは有料のアドオンですが、オプションです。 使用する場合は、Microsoft Entra External ID Basic に加えて追加料金が発生します。 詳細については、 [Microsoft Entra External ID の価格](https://www.microsoft.com/en-us/security/pricing/microsoft-entra-external-id/)に関するページを参照してください。

現在、次の国/地域には、ローカル データの保存オプションがあります。

- オーストラリア
- 日本

### 関連リソース

Microsoft Cloud オファリングでのデータ所在地の詳細については、次の記事を参照してください。

- [Azure でのデータ所在地 | Microsoft Azure](https://azure.microsoft.com/explore/global-infrastructure/data-residency/#overview)
- [Microsoft 365 のデータの場所 - Microsoft 365 Enterprise](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/o365-data-locations?view=o365-worldwide&preserve-view=true)
- [Microsoft のプライバシー - お客様のデータの場所](https://www.microsoft.com/trust-center/privacy/data-location?rtc=1)
- PDF のダウンロード: [クラウドでのプライバシーに関する考慮事項](https://go.microsoft.com/fwlink/p/?LinkID=2051117&amp;clcid=0x409&amp;culture=en-us&amp;country=US)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/data-storage-australia"} -->
## オーストラリアおよびニュージーランドのお客様向けの ID データ ストレージ - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/data-storage-australia
- Service: entra / fundamentals
- Article date: 2025-03-05
- Summary: Microsoft Entra ID がオーストラリアとニュージーランドのお客様の ID 関連データを格納する場所について説明します。

### 概要

Microsoft Entra ID は、Microsoft 365 や Azure などの Microsoft サービスをサブスクライブするときに組織が提供するアドレスに基づいて選択された場所に ID データを格納します。 ID 顧客データの格納場所については、「データの場所」というタイトルの Microsoft セキュリティ センターのセクション [を参照してください](https://www.microsoft.com/trustcenter/privacy/where-your-data-is-located)。

手記

Microsoft Entra ID と統合されるサービスとアプリケーションは、Identity Customer Data にアクセスできます。 使用する各サービスとアプリケーションを評価します。 特定のサービスとアプリケーションが ID データを処理する方法と、それらが会社のデータ ストレージ要件を満たしているかどうかを判断します。

オーストラリアまたはニュージーランドで住所を指定したお客様の場合、Microsoft Entra ID はオーストラリアのデータセンター内でこれらのサービスの ID データを保持します。

- Microsoft Entra ディレクトリ管理
- 認証

他のすべての Microsoft Entra サービスは、顧客データをグローバル データセンターに格納します。

### Microsoft Entra 多要素認証

多要素認証では、ID 顧客データがグローバル データセンターに格納されます。 クラウドベースの Microsoft Entra 多要素認証と Azure 多要素認証サーバーによって収集および格納されるユーザー情報の詳細については、Microsoft Entra 多要素認証のユーザー データ収集 を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/data-storage-australia-newzealand"} -->
## オーストラリアとニュージーランドの顧客データの保存 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/data-storage-australia-newzealand
- Service: entra / fundamentals
- Article date: 2025-03-05
- Summary: Microsoft Entra ID によってオーストラリアとニュージーランド国内の顧客関連データがどこに保存されるかについて説明します。

### 概要

Microsoft 365 や Azure などの Microsoft サービスをサブスクライブしている場合、Microsoft Entra ID では、組織によって提供されたアドレスに基づいて選択された地理的な場所に、識別データが格納されます。 Microsoft Online Services には、Microsoft 365 と Azure が含まれます。

Microsoft Entra ID およびその他の Microsoft サービスのデータが配置される場所の詳細については、Microsoft トラスト センターの[お客様のデータの場所](https://www.microsoft.com/trust-center/privacy/data-location)に関するセクションを参照してください。

2020 年 2 月 26 日から、Microsoft では、オーストラリアまたはニュージーランドの請求先住所を含む新しいテナントの Microsoft Entra ID の顧客データを、オーストラリアのデータセンター内に格納しています。

また、一部の Microsoft Entra ID 機能では、オーストラリアでの顧客データの格納がまだサポートされていません。 ご利用のリージョンに固有の情報については、[Microsoft グローバル データセンター マップ](https://datacenters.microsoft.com/globe/explore)を参照してください。 たとえば、Microsoft Entra 多要素認証 (MFA) では、顧客データが米国に格納され、グローバルに処理されます。 詳細については、「[Microsoft Entra 多要素認証のデータ所在地と顧客データ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-data-residency)」を参照してください。

注

Microsoft Entra ID と統合される Microsoft の製品、サービス、およびサードパーティのアプリケーションは、顧客データにアクセスできます。 使用する製品、サービス、アプリケーションをそれぞれ評価して、その特定の製品、サービス、アプリケーションによる顧客データの処理方法を確認し、それらが会社のデータ ストレージ要件を満たしているかどうかを判断してください。 Microsoft サービスのデータ保存場所の詳細については、Microsoft トラスト センターの「[お客様のデータが保存されている場所](https://www.microsoft.com/trust-center/privacy/data-location)」セクションを参照してください。

### Azure ロールベースのアクセス制御 (Azure RBAC)

ロールの定義、ロールの割り当て、拒否の割り当てはグローバルに格納され、リソースを作成したリージョンに関係なく、リソースに確実にアクセスできます。 詳細については、[Azure ロールベースのアクセス制御 (RBAC)](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/overview#where-is-azure-rbac-data-stored)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/data-storage-eu"} -->
## Microsoft Entra ID でのヨーロッパのお客様向けの顧客データの保存と処理 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/data-storage-eu
- Service: entra / fundamentals
- Article date: 2026-04-27
- Summary: Microsoft Entra ID がヨーロッパのお客様の ID 関連データを格納する場所について説明します。

Microsoft Entra ID は、テナントの作成方法とプロビジョニング方法に基づいて、顧客データを地理的な場所に格納します。 次の一覧では、場所の定義方法に関する情報を示します。

- **Microsoft Entra 管理センターまたは Microsoft Entra API** - 顧客が定義済みの一覧から場所を選択します。
- **Dynamics 365 と Power Platform** - 顧客は、事前に定義された場所にテナントをプロビジョニングします。
- **EU Data Residency** - ヨーロッパで場所を提供したお客様の場合、Microsoft Entra ID は、この記事で後述する場合を除き、ほとんどの顧客データをヨーロッパに格納します。
- **EU データ境界** - [EU データ境界](https://learn.microsoft.com/ja-jp/privacy/eudb/eu-data-boundary-learn#eu-data-boundary-countries-and-datacenter-locations) (EU および EFTA のメンバー) 内の場所を提供したお客様の場合、Microsoft Entra ID は、この記事で後述する場合を除き、ほとんどの顧客データを EU データ境界に格納および処理します。
- **Microsoft 365** - 場所は、顧客が指定した請求先住所に基づいています。

次のセクションでは、EU データ所在地または EU データ境界のコミットメントを満たしていない顧客データに関する情報を提供します。

### 顧客データのサブセットを EU データ所在地と EU データ境界から一時的に転送するサービス

サービスの一部のコンポーネントに関しては、EU データ所在地と EU データ境界への含有作業が進行中ですが、この作業の完了は遅れています。 この記事の次のセクションでは、これらのサービスが現在、サービス運用の一環としてヨーロッパから転送される顧客データについて説明します。

**U データ所在地:**

- **顧客データエグレス** の理由 - 次のいずれかの理由により、一部のテナントが EU の場所の外部に格納されます。

    - テナントは、最初はヨーロッパにない国コードで作成され、後でテナントの国コードがヨーロッパの国コードに変更されました。 Microsoft Entra ディレクトリのデータの場所は、テナントの作成時に決定され、テナントの国コードが更新されたときに変更されません。 2019 年 3 月以降、Microsoft は、このような混乱を避けるために、テナントの国コードの更新をブロックしました。
    - 2013 年までアジア地域にマップされ、その後ヨーロッパにマップされた 13 の国コード (アゼルバイジャン、バーレーン、イスラエル、ヨルダン、カザフスタン、クウェート、レバノン、オマーン、パキスタン、カタール、サウジアラビア、Türkiye、UAE) があります。 この国コードから 2013 年 7 月より前に作成されたテナントは、ヨーロッパではなくアジアでプロビジョニングされます。
    - 2017年までアジア地域にマップされ、その後ヨーロッパにマップされた7つの国コード(アルメニア、ジョージア、イラク、キルギズスタン、タジキスタン、トルクメニスタン、ウズベキスタン)があります。 この国コードから 2017 年 2 月より前に作成されたテナントは、ヨーロッパではなくアジアでプロビジョニングされます。
- **ユーザーとデバイス アカウントのデータ、サービス構成 (アプリケーション、ポリシー、グループ)** 送信される顧客データの種類。
- **顧客データの保存場所** - 米国とアジア/太平洋。
- **顧客データ処理** - 静止位置と同じです。
- **サービス** - ディレクトリ コアストア

**EU データ境界:**

顧客データのサブセットを EU データ境界から一時的に転送する、EU データ境界 サービスからの Microsoft Entra の一時的な部分的な顧客データ転送の詳細を参照してください。

### 顧客データのサブセットを EU データ所在地と EU データ境界から永続的に転送するサービス

サービスの一部のコンポーネントは、サービスの機能を容易にするために設計されているため、EU データ所在地と EU データ境界から限られた量の顧客データを引き続き転送します。

**U データ所在地:**

[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/fundamentals/what-is-entra): IP アドレスまたは電話番号が不正なアクティビティで使用されていると判断されると、それらを使用するワークロードからのアクセスをブロックするためにグローバルに公開されます。

**EU データ境界:**

Microsoft Entra の EU データ境界 [サービスから永続的に顧客データの一部が EU データ境界](https://learn.microsoft.com/ja-jp/privacy/eudb/eu-data-boundary-permanent-partial-transfers#security-services)の外に転送されることについての詳細情報をご覧ください。

### その他の考慮事項

#### EU データ所在地と EU データ境界からデータを転送するオプションのサービス機能

**U データ所在地:**

一部のサービスでは、オプションの機能が提供されます。 場合によっては、サブスクリプションを使用する必要があります。 顧客管理者は、サービス アカウントに対してこれらの機能を有効または無効にすることを選択できます。 お客様のユーザーが使用できる場合、これらの機能により、この記事の次のセクションで説明するように、ヨーロッパからデータが転送されます。

- [マルチテナント管理](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/overview): 組織は、Microsoft Entra ID 内にマルチテナント組織を作成することを選択できます。 たとえば、顧客は B2B コンテキストでユーザーをテナントに招待できます。 顧客は、サービスとしてのマルチテナント ソフトウェア (SaaS) アプリケーションを作成できます。これにより、他のサード パーティのテナントは、サード パーティのテナントでアプリケーションをプロビジョニングできます。 顧客は、特定の状況で 1 つのテナントとして連携するように複数のテナントをリンクできます。 これには、マルチテナント組織 (MTO) の形成、テナントの同期、電子メール ドメインの共有が含まれます。 管理者によるマルチテナント コラボレーションの構成と使用は、EU データ所在地および EU データ境界外のテナントで発生する可能性があります。その結果、ユーザーとデバイスアカウントのデータ、使用状況データ、サービス構成 (アプリケーション、ポリシー、グループ) などの一部の顧客データが、共同作業テナントの場所に格納および処理されます。
- [アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy): アプリケーション プロキシを使用すると、外部 URL または内部アプリケーション ポータルを使用して、クラウドアプリケーションとオンプレミスアプリケーションの両方にアクセスできます。 顧客は、ユーザー アカウント データ、使用状況データ、アプリケーション構成データなど、顧客データが EU データ所在地と EU データ境界の外部に送信される高度なルーティング構成を選択できます。

**EU データ境界:**

EU データ境界から顧客データを転送するオプションのサービス機能の詳細 [EU データ境界](https://learn.microsoft.com/ja-jp/privacy/eudb/eu-data-boundary-transfers-for-optional-capabilities#microsoft-entra-id)から顧客データを転送するオプションのサービス機能を参照してください。

#### その他の EU データ境界オンライン サービス

Microsoft Entra ID と統合されるサービスとアプリケーションは、顧客データにアクセスできます。 各サービスとアプリケーションが顧客データを格納および処理する方法を確認し、それらが会社のデータ処理要件を満たしていることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/data-storage-japan"} -->
## 日本のお客様向けカスタマー データ ストレージ - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/data-storage-japan
- Service: entra / fundamentals
- Article date: 2024-01-03
- Summary: Microsoft Entra ID が日本のお客様の顧客関連データを格納する場所について説明します。

Microsoft Entra ID は、Microsoft Online サービスにサインアップしたときに指定した国/地域に基づいて、顧客データを地理的な場所に格納します。 Microsoft Online サービスには、Microsoft 365 と Azure が含まれます。

Microsoft Entra ID とその他の Microsoft サービスのデータの場所については、Microsoft セキュリティ センターの「[データの場所」セクション](https://www.microsoft.com/trust-center/privacy/data-location) 参照してください。

さらに、一部の Microsoft Entra 機能では、日本での顧客データの保存はまだサポートされていません。 たとえば、Microsoft Entra 多要素認証では、顧客データが米国に格納され、グローバルに処理されます。 詳細については、「[Microsoft Entra 多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-data-residency)のデータ所在地と顧客データ」を参照してください。

手記

Microsoft Entra ID と統合される Microsoft 製品、サービス、およびサード パーティ製アプリケーションは、顧客データにアクセスできます。 使用する各製品、サービス、アプリケーションを評価して、その特定の製品、サービス、アプリケーションによって顧客データがどのように処理されるか、およびそれらが会社のデータ ストレージ要件を満たしているかどうかを判断します。 Microsoft サービスのデータ所在地の詳細については、Microsoft セキュリティ センターの「[データの場所](https://www.microsoft.com/trust-center/privacy/data-location) セクション」を参照してください。

### Azure ロールベースのアクセス制御 (Azure RBAC)

ロールの定義、ロールの割り当て、拒否の割り当てはグローバルに保存され、リソースを作成したリージョンに関係なく、リソースに確実にアクセスできます。 詳細については、「[Azure ロールベースのアクセス制御 (RBAC) (Azure RBAC) とは」を参照してください。](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/overview#where-is-azure-rbac-data-stored).
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/entra-admin-center"} -->
## Microsoft Entra 管理センター - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/entra-admin-center
- Service: entra / fundamentals
- Article date: 2026-06-18
- Summary: Microsoft Entra 製品を構成および管理するための Microsoft Entra 管理センター インターフェイスの概要。

### 概要

[Microsoft Entra 管理センター](https://entra.microsoft.com/)は、一元化された場所で Microsoft Entra 製品を構成および管理するための統一された管理エクスペリエンスを提供する Web ベースのポータルです。 管理者は管理センターから、ユーザーとグループの管理、認証方法の構成、条件付きアクセス ポリシーの作成、ID セキュリティ体制の監視、組織全体のアクセスの管理を行うことができます。

管理センターには、左側のナビゲーション メニューからアクセスできる次の Microsoft Entra 製品領域がまとめされています。

- **Entra ID** — ユーザー、グループ、デバイス、アプリケーション、ロール、認証方法を管理します。
- **ID 保護** - リスク ポリシーとレポートを使用して ID ベースのリスクを監視し、対応します。
- **ID ガバナンス** - エンタイトルメント管理、アクセス レビュー、ライフサイクル ワークフローを使用してアクセス ライフサイクルを制御します。
- **検証済み ID** — 検証可能な資格情報を発行して管理します。
- **グローバルなセキュリティで保護されたアクセス** - プライベート アクセスとインターネット アクセスを使用して、アプリとリソースへのアクセスをセキュリティで保護します。

### Microsoft Entra 管理センターについて調べる

Microsoft Entra 管理センターは製品別に構成されています。 検索バーまたは左側のメニューから製品にアクセスします。 ページの上部にある検索バーを使用して、特定の設定、機能、またはドキュメントを見つけることもできます。

**ホーム** には、テナント、最近のアクティビティ、その他の便利なリソース (ショートカットやデプロイ ガイドなど) に関する概要情報が含まれています。 ホーム ページでは、次の機能にすばやくアクセスできます。

- **テナントの概要** - テナント名、ID、ライセンス情報を表示します。
- **推奨されるアクション** — テナント [の](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations) セキュリティと正常性の向上に役立つ、パーソナライズされた推奨事項。
- **展開ガイド** - Microsoft Entra 機能を展開するための詳細なガイダンス。
- **最近のアクティビティ** — 最近アクセスしたページや最近の変更にすばやくアクセスできます。

[Image: Microsoft Entra 管理センターの 概要ホーム ページのスクリーンショット。]

次のセクションでは、製品インターフェイスの大まかな概要と、機能の詳細を確認するためのリンクを示します。

#### 保守する

**Entra ID** を使用すると、管理者と開発者は、テナント、ユーザー、グループ、デバイス、アプリケーション、ロール、ライセンスなど、Microsoft [Entra ID](https://learn.microsoft.com/ja-jp/entra/fundamentals/what-is-entra) ソリューションと [Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-overview) ソリューションにアクセスできます。

[Image: Microsoft Entra 管理センターの [ID] メニューのスクリーンショット。]

Microsoft Entra ID ソリューションの構成と管理の詳細については、次のドキュメントを参照してください。

- [ユーザーとグループ](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-overview-user-model)
- [デバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)
- [エージェント](https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-microsoft-entra-agent-id)
- [エンタープライズ アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-application-management)
- [アプリの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/application-model)
- [ロールと管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview)
- [外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-overview)
- [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)
- [多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)
- [ID セキュア スコア](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-identity-secure-score)
- [認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)
- [パスワードのリセット](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-howitworks)
- [カスタム セキュリティ属性](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview)

#### ID 保護

**ID Protection** を使用すると、管理者と開発者は、保護ダッシュボード、リスクベースのアクセス ポリシー、危険なユーザー レポート、多要素認証、パスワード リセットなど、 [Microsoft Entra ID 保護](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) ソリューションにアクセスできます。

[Image: Microsoft Entra 管理センターの [保護] メニューのスクリーンショット。]

Microsoft Entra ID 保護ソリューションの構成と管理の詳細については、次のドキュメントをご覧ください。

- [ID 保護ダッシュボード](https://learn.microsoft.com/ja-jp/entra/id-protection/id-protection-dashboard)
- [リスクベースのアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-policies)
- [危険なユーザー](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)
- [危険なワークロードアイデンティティ](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-workload-identity-risk)

#### ID ガバナンス

**ID ガバナンス**により、管理者と開発者は、エンタイトルメント管理、アクセス レビュー、ライフサイクル ワークフローなど、[Microsoft Entra ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview) ソリューションにアクセスできます。

[Image: Microsoft Entra 管理センターの ID ガバナンス メニューのスクリーンショット。]

Microsoft Entra ID ガバナンス ソリューションの構成と管理の詳細については、次のドキュメントをご覧ください。

- [ID ガバナンス ダッシュボード](https://learn.microsoft.com/ja-jp/entra/id-governance/governance-dashboard)
- [エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)
- [アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)
- [Privileged Identity Management](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure)
- [ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows)
- [ライフサイクル ワークフローのカスタム タスク拡張機能](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-extensibility)

#### 検証済み ID

**[Verified ID]** を使用すると、管理者と開発者は、資格情報や組織の設定などを含む、[Microsoft Entra の確認済み ID](https://learn.microsoft.com/ja-jp/entra/verified-id/decentralized-identifier-overview) ソリューションにアクセスできます。

[Image: Microsoft Entra 管理センターの [確認済み ID] メニューのスクリーンショット。]

Microsoft Entra 確認済み ID ソリューションの構成と管理の詳細については、次のドキュメントをご覧ください。

- [資格情報](https://learn.microsoft.com/ja-jp/entra/verified-id/verifiable-credentials-configure-tenant-quick)

#### グローバルなセキュリティで保護されたアクセス

**[グローバル セキュア アクセス]** を使用すると、管理者と開発者は、グローバル セキュア アクセス ダッシュボード、クライアント、コネクタ、監視を含む [Microsoft Entra Private Access](https://learn.microsoft.com/ja-jp/entra/global-secure-access/overview-what-is-global-secure-access#microsoft-entra-private-access) および [Microsoft Entra Internet Access](https://learn.microsoft.com/ja-jp/entra/global-secure-access/overview-what-is-global-secure-access#microsoft-entra-internet-access) ソリューションにアクセスできます。

[Image: Microsoft Entra 管理センターのグローバル セキュア アクセス メニューのスクリーンショット。]

グローバル セキュア アクセス ソリューションの構成と管理の詳細については、次のドキュメントを参照してください。

- [グローバル セキュア アクセス ダッシュボード](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-traffic-dashboard)
- [グローバル セキュア アクセス クライアント](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-clients)
- [トラフィック転送](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-traffic-forwarding)
- [リモート ネットワーク](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-remote-network-connectivity)
- [ログと監視](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-global-secure-access-logs-monitoring)

### 一般的な管理タスク

次の表に、Microsoft Entra 管理センターから実行できる一般的な管理タスクと、各タスクの詳細なガイダンスへのリンクを示します。

| Task | 説明 | 詳細情報 |
| --- | --- | --- |
| ユーザーを作成または削除する | 新しいメンバーまたはゲストを組織に追加するか、既存のユーザーを削除します。 | [ユーザーの作成または削除](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users) |
| グループの管理 | アクセス管理とライセンスのためにユーザーを整理するためのグループを作成および管理します。 | [グループとグループ メンバーシップを管理する](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups) |
| ロールを割り当てる | 組み込みロールまたはカスタム ロールを使用して、管理責任を委任します。 | [ロールベースのアクセス制御の概要](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview) |
| アプリケーションの管理 | シングル サインオンと API アクセス用にアプリケーションを登録して構成します。 | [アプリケーション管理とは](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-application-management) |
| 条件付きアクセス ポリシーを作成する | ユーザー、デバイス、場所、リスクなどの条件に基づいてアクセス制御を定義します。 | [条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) |
| ID のセキュリティ スコアを確認する | テナントのセキュリティ体制を確認し、推奨事項に従って改善します。 | [ID セキュリティ スコアとは](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-identity-secure-score) |
| 多要素認証を設定する | ユーザーが複数の認証方法を使用して自分の ID を確認することを要求します。 | [しくみ: Microsoft Entra 多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks) |
| セルフサービス パスワード リセットを構成する | 管理者に連絡することなく、ユーザーが自分のパスワードをリセットできるようにします。 | [仕組み: Microsoft Entra のセルフサービス パスワード リセット](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-howitworks) |

### 助けが必要ですか？

**問題の診断と解決** には、一般的な問題を解決するためのトラブルシューティング リソースと、 **新しいサポート 要求**を開いてサポート チームに問い合わせるオプションが用意されています。

[Image: Microsoft Entra 管理センターの [問題の診断と解決] メニューのスクリーンショット。]

[Image: Microsoft Entra 管理センターの [詳細情報とサポート] メニューのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/faq"} -->
## Microsoft Entra、Microsoft Entra ID、および Azure に関するよくある質問 (FAQ) - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/faq
- Service: entra / fundamentals
- Article date: 2025-03-05
- Summary: Microsoft Entra、Microsoft Entra ID、Azure について、パスワード管理、およびアプリケーション アクセスなどに関する一般的な質問とその回答です。

Microsoft Entra ID は、クラウドベースの ID およびアクセス管理ソリューションです。 これは、クラウドで動作し、Microsoft 365、Dynamics 365、Microsoft Azure などのさまざまな Microsoft サービスに認証および認可サービスを提供するディレクトリと ID 管理サービスです。

詳細については、「[Microsoft Entra ID とは」を](https://learn.microsoft.com/ja-jp/entra/fundamentals/what-is-entra)参照してください。

### Microsoft Entra ID と Azure へのアクセスに関するヘルプ

#### Azure portal で Microsoft Entra ID 管理センターにアクセスしようとすると、“サブスクリプションが見つかりません” と表示されるのはなぜですか。

Microsoft Entra 管理センターまたは Azure ポータルにアクセスするには、各ユーザーは有効なサブスクリプションを使用したアクセス許可が必要です。 有料の Microsoft 365 または Microsoft Entra サブスクリプションをお持ちでない場合は、Microsoft Entra アカウント無料のライセンス認証を行うか、有料サブスクリプションを確立する必要があります。 Azure サブスクリプションは、有料であれ無料であれ、Microsoft Entra テナント との間に信頼関係があります。 すべてのサブスクリプションは、Microsoft Entra テナント (ディレクトリ) に依存して、セキュリティ プリンシパルとデバイスの認証と承認を行います。

詳細については、「 [Azure サブスクリプションと Microsoft Entra ID の関連付け方法」を](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-subscriptions-associated-directory)参照してください。

#### Microsoft Entra ID、Microsoft Azure、その他の Microsoft サービス (Microsoft 365 など) の間の関係を教えてください。

Microsoft Entra ID を使用すると、すべての Web サービスに共通の ID とアクセス機能が提供されます。 Microsoft サービス (Microsoft 365、Power Platform、Dynamics 365、その他の Microsoft 製品など) のいずれかを使用していれば、すべてのクラウド サービスのサインオンとアクセス管理を実現するために Microsoft Entra ID を使用していることになります。

Microsoft サービスを使用するように設定されているすべてのユーザーは、1 つ以上の Microsoft Entra インスタンスのユーザー アカウントとして定義され、これらのアカウントに Microsoft Entra ID へのアクセス権が付与されます。

詳細については、「[Microsoft Entra ID プランと価格」を](https://www.microsoft.com/security/business/microsoft-entra-pricing)参照してください。

Enterprise Mobility + Security (Microsoft Enterprise Mobility + Security) などの Microsoft Entra 有料サービスは、包括的なエンタープライズ規模の開発、管理、セキュリティ ソリューションを使用して、Microsoft 365 などの他の Microsoft サービスを補完します。

詳細については、「 [Microsoft Cloud](https://learn.microsoft.com/ja-jp/microsoft-cloud)」を参照してください。

#### 所有者と全体管理者の違いは何ですか?

既定では、Microsoft Entra または Azure サブスクリプションにサインアップしたユーザーには、Azure リソースの所有者ロールが割り当てられます。 所有者は、Microsoft アカウントと、Microsoft Entra または Azure サブスクリプションが関連付けられているディレクトリの職場または学校アカウントのどちらも使用できます。 このロールには、Azure Portal でのサービスの管理も許可されています。

他のユーザーが同じサブスクリプションを使用してサインインしてサービスにアクセスする必要がある場合は、適切な [組み込みロール](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles)を割り当てることができます。 詳細については、「 [Azure portal を使用して Azure ロールを割り当てる」を](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal)参照してください。

既定では、Microsoft Entra テナントを作成するユーザーには、 [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) ロールが自動的に割り当てられます。 ユーザーは、すべての Microsoft Entra ディレクトリ機能にアクセスできます。 Microsoft Entra ID には、ディレクトリおよび ID 関連の機能を管理するために異なる管理者ロールのセットがあります。 これらの管理者は、Azure portal のさまざまな機能にアクセスできます。 管理者のロールによって、ユーザーの作成または編集、他のユーザーへの管理者ロールの割り当て、ユーザー パスワードのリセット、ユーザー ライセンスの管理、ドメインの管理など、管理者が実行できる操作が決まります。

詳細については、「 [Microsoft Entra ID でユーザーを管理者ロールに割り当てる」および「Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-subscriptions-associated-directory)[での管理者ロールの割り当て」を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

#### Microsoft Entra ユーザー ライセンスの有効期限が切れる時期を示すレポートはありますか?

いいえ。 これは、現在利用できません。

#### ファイアウォールまたはプロキシ サーバーで Microsoft Entra 管理センターの URL を許可するにはどうすればよいですか?

ご利用のネットワークと Microsoft Entra 管理センターおよびそのサービスとの間の接続性を最適化するために、Microsoft Entra 管理センターの特定の URL を許可リストに追加することをお勧めします。 そうすることにより、ローカルまたはワイド エリア ネットワークとの間のパフォーマンスと接続性が向上します。 プロキシ サーバーやファイアウォールなどのデバイスがネットワーク管理者によってデプロイされていることは少なくありません。これらのデバイスによって、ユーザーのインターネット アクセスに対するセキュリティと制御を向上させることができます。 ユーザーを保護するように設計されたルールは、ビジネスに関連する正当なインターネットトラフィックをブロックまたは低速化する場合があります。 このトラフィックには、以下の URL で行われるユーザーと Microsoft Entra 管理センターとの間の通信が含まれます。

- \*.entra.microsoft.com
- \*.entra.microsoft.us
- \*.entra.microsoftonline.cn

詳細については、「 [Microsoft Entra アプリケーション プロキシを使用してリモート ユーザー用のオンプレミス アプリを発行する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)」を参照してください。 追加の URL を含める必要がある場合は、「 [ファイアウォールまたはプロキシ サーバーで Azure portal の URL を許可する」](https://learn.microsoft.com/ja-jp/azure/azure-portal/azure-portal-safelist-urls)の記事に記載されています。

### ハイブリッド Microsoft Entra ID に関するヘルプ

#### コラボレーターとして追加されたときにテナントから退出するにはどうすればよいですか?

通常、管理者に連絡することなく独自の判断で組織を脱退することができます。 ただし、場合によっては、このオプションを使用できないため、外部組織のアカウントを削除できるテナント管理者に問い合わせる必要があります。

詳細については、「 [組織を外部ユーザーとして脱退する」を](https://learn.microsoft.com/ja-jp/entra/external-id/leave-the-organization)参照してください。

#### オンプレミスのディレクトリを Microsoft Entra ID に接続するにはどうすればよいですか。

Microsoft Entra Connect を使用して、オンプレミスのディレクトリを Microsoft Entra ID に接続できます。

詳細については、「 [オンプレミス ID と Microsoft Entra ID の統合」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/whatis-hybrid-identity)。

#### オンプレミスのディレクトリとクラウド アプリケーションの間で SSO を設定するにはどうすればよいですか?

オンプレミスのディレクトリと Microsoft Entra ID の間でシングル サインオン (SSO) を設定するだけです。 Microsoft Entra ID を介してクラウド アプリケーションにアクセスしている限り、自動的にこのサービスによって、オンプレミスの資格情報を使ったユーザー認証が正しく行われます。

オンプレミスからの SSO は、Active Directory フェデレーション サービス (ADFS) などのフェデレーション ソリューションを使用するか、パスワード ハッシュ同期を構成すると、簡単に実装できます。どちらのオプションも Microsoft Entra Connect 構成ウィザードを使って簡単にデプロイできます。

詳細については、「 [オンプレミス ID と Microsoft Entra ID の統合」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/whatis-hybrid-identity)。

#### Microsoft Entra ID には、所属する組織のユーザーのためのセルフサービス ポータルが用意されていますか。

はい。Microsoft Entra ID は、ユーザーのセルフサービスアクセスとアプリケーション [アクセスのための Microsoft Entra ID アクセス パネル](https://myapps.microsoft.com) を提供します。 Microsoft 365 のお客様の場合は、 [Office 365 ポータル](https://portal.office.com)で同じ機能の多くを見つけることができます。

詳細については、「 [アクセス パネルの概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。

#### Microsoft Entra ID は、オンプレミスのインフラストラクチャの管理に役立ちますか。

はい。 Microsoft Entra ID P1 または P2 エディションでは、Microsoft Entra Connect Health が提供されます。 Microsoft Entra Connect Health では、オンプレミスの ID インフラストラクチャと同期サービスを監視、分析できます。

詳細については、「 [クラウドでのオンプレミスの ID インフラストラクチャと同期サービスの監視」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect)。

### パスワードの管理に関するヘルプ

#### パスワード同期を使わずに Microsoft Entra パスワード ライトバックを使用することはできますか?

(例えば、クラウドにパスワードを保存せずに、パスワード ライトバックで Microsoft Entra のセルフサービス パスワード リセット (SSPR) を使用できますか?)

このシナリオ例では、オンプレミスのパスワードを Microsoft Entra で追跡する必要はありません。 その理由は、ライトバックを有効にするために、Active Directory パスワードを Microsoft Entra ID に同期する必要がないからです。 フェデレーション環境では、Microsoft Entra シングル サインオン (SSO) は、ユーザーの認証のためにオンプレミスのディレクトリを利用します。

#### オンプレミスの Active Directory へのパスワード ライトバックにはどれくらいの時間がかかりますか?

パスワードの書き戻しはリアルタイムで処理されます。

詳細については、「 [パスワード管理の概要](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr)」を参照してください。

#### 管理者によって管理されるパスワードでパスワード ライトバックを使用できますか?

はい。パスワード ライトバックが有効な状態であれば、管理者によって実行されるパスワード操作は、オンプレミスの環境に書き戻されます。

パスワード関連の質問に対するその他の回答については、「 [パスワード管理に関してよく寄せられる質問](https://learn.microsoft.com/ja-jp/entra/identity/authentication/passwords-faq)」を参照してください。

#### パスワードを変更しようとするときに、Microsoft 365/Microsoft Entra の既存のパスワードが思い出せない場合は、どうすればいいですか。

いくつかのオプションがあります。 使用可能な場合はセルフサービス パスワード リセット (SSPR) を使用してください。 SSPR が動作するかどうかは、その構成方法によって決まります。 Microsoft Entra パスワードのリセットの詳細については、「 [パスワード リセット ポータルのしくみ」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-deployment)参照してください。

Microsoft 365 ユーザーの場合、「ユーザー パスワードのリセット」で説明されている手順を使用して、管理者は [パスワードをリセット](https://support.office.com/article/Admins-Reset-user-passwords-7A5D073B-7FAE-4AA5-8F96-9ECD041ABA9C?ui=en-US&amp;rs=en-US&amp;ad=US)できます。

Microsoft Entra アカウントの場合、管理者は、次のいずれかの方法を使用してパスワードをリセットできます。

- [Microsoft Entra ID を使用してユーザーのパスワードをリセットする](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-reset-password-azure-portal)
- [PowerShell を使用してパスワードをリセットする](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/reset-mguserauthenticationmethodpassword)

### セキュリティに関するヘルプ

#### 何回か試行が失敗した後、アカウントはロックされますか。または、より高度な戦略はありますか?

Microsoft Entra ID では、より高度な戦略を使用してアカウントをロックします。 これは、リクエストの IP と入力したパスワードに基づいています。 また、ロックアウト期間は、攻撃されている可能性に応じて長くなります。

#### 特定の (ありふれた) パスワードは拒否されるため、現在のディレクトリ内でのみ使用するパスワードにこれが適用されますか?

パスワードが拒否された場合、"このパスワードを使いすぎています" というメッセージが返されます。 これは、パスワードが、"Password"、"123456" など、一般的によく使用されるものであることを意味します。

#### 不審なソース (ボットネットなど) からのサインイン要求は B2C テナントでブロックされますか。それとも、Basic または Premium Edition のテナントが必要ですか?

要求をフィルター処理するゲートウェイによって、ボットネットからの保護を提供します。また、これは B2C テナントすべてに適用されます。

### アプリケーション アクセスに関するヘルプ

#### Microsoft Entra ID とその機能にあらかじめ統合されているアプリケーションの一覧はどこにありますか。

Microsoft Entra ID には、Microsoft、アプリケーション サービス プロバイダー、パートナーが提供する、2,600 個を超える事前統合されたアプリケーションがあります。 事前統合されたアプリケーションすべてで、シングル サインオン (SSO) がサポートされています。 SSO を使用すると、アプリへのアクセスに、所属する組織の資格情報を使うことができます。 また、一部のアプリケーションでは、プロビジョニングとプロビジョニング解除の自動化がサポートされています。

事前に統合されたアプリケーションの完全な一覧については、 [Azure Marketplace](https://azuremarketplace.microsoft.com/marketplace/apps/Microsoft.AzureActiveDirectory) を参照してください。

#### 必要なアプリケーションが Microsoft Entra マーケットプレースにない場合はどうすればよいですか。

Microsoft Entra ID P1 または P2 を使用すると、任意のアプリケーションを追加して構成できます。 アプリケーションの機能と必要性に応じて SSO と自動プロビジョニングを構成できます。

詳細については、「 [シングル サインオン SAML プロトコル](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol) 」および [「SCIM エンドポイントのプロビジョニングの開発と計画](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)」を参照してください。

#### Microsoft Entra ID を使用してアプリケーションにサインインする場合、どのような方法がありますか。

Microsoft Entra ID では、ユーザーがアプリケーションを表示してアクセスする方法が複数あります。たとえば、次の方法があります。

- [Microsoft Entra アクセス パネル](https://entra.microsoft.com/#home)
- Microsoft 365 アプリケーション起動プログラム
- フェデレーション アプリへの直接サインイン
- フェデレーション アプリ、パスワードベースのアプリ、または既存のアプリへのディープ リンク

詳細については、「 [アプリケーションのエンド ユーザー エクスペリエンス」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/end-user-experiences)参照してください。

#### Microsoft Entra ID でアプリケーションに対する認証とシングル サインオンを有効にする方法には、どのようなものがありますか。

Microsoft Entra ID では、SAML 2.0、OpenID Connect、OAuth 2.0、WS-Federation など、認証と承認に関する標準化されたプロトコルが多数サポートされています。 Microsoft Entra ID では、フォーム ベース認証しかサポートしていないアプリのために、パスワード保管と自動サインインの機能もサポートされています。

詳細については、「[Microsoft Entra ID でのアプリケーションの](https://learn.microsoft.com/ja-jp/entra/fundamentals/identity-fundamental-concepts)[ID の基礎](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)とシングル サインオン」を参照してください。

#### オンプレミスで実行しているアプリケーションを追加できますか?

[Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy) を使用すると、選択したオンプレミスの Web アプリケーションに簡単かつ安全にアクセスできます。 これらのアプリケーションには、Microsoft Entra ID でサービスとしてのソフトウェア (SaaS) アプリにアクセスするのと同じ方法でアクセスできます。 VPN を使用したり、ネットワーク インフラストラクチャを変更したりする必要はありません。

詳細については、 [オンプレミス アプリケーションへのセキュリティで保護されたリモート アクセスを提供する方法に関するページを](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy)参照してください。

#### 特定のアプリケーションにアクセスするユーザーに多要素認証を要求するにはどうすればよいですか?

[Microsoft Entra 条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)を使用すると、アプリケーションごとに一意のアクセス ポリシーを割り当てることができます。 ポリシーでは、多要素認証を常に要求することも、ユーザーがローカル ネットワークに接続されていない場合に要求することもできます。

詳細については、「 [Microsoft 365 および Microsoft Entra ID に接続されている他のアプリへのアクセスのセキュリティ保護」を](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)参照してください。

#### SaaS アプリへのユーザー プロビジョニングの自動化とは

Microsoft Entra ID を使用すると、多くの一般的なクラウド SaaS アプリでユーザー ID の作成、保守、削除を自動化できます。

詳細については、「 [Microsoft Entra ID でのアプリ プロビジョニングとは」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

#### Microsoft Entra ID で Secure LDAP 接続を設定できますか。

いいえ。 Microsoft Entra ID では、ライトウェイト ディレクトリ アクセス プロトコル (LDAP) や Secure LDAP は直接サポートされません。 ただし、Azure のネットワークを通じてネットワーク セキュリティ グループを適切に構成すれば、Microsoft Entra テナント上で Microsoft Entra Domain Services インスタンスを有効にして、LDAP 接続を実現できます。

詳細については、「 [Microsoft Entra Domain Services マネージド ドメインのセキュリティで保護された LDAP を構成する](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-ldaps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/five-steps-to-full-application-integration"} -->
## Microsoft Entra ID にアプリを統合する 5 つのステップ - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/five-steps-to-full-application-integration
- Service: entra / fundamentals
- Article date: 2023-03-01
- Summary: アプリ、検出、統合方法を追加して、アプリケーションを Microsoft Entra ID と統合する方法について学習します。

クラウドベースの ID およびアクセス管理 (IAM) サービスである Microsoft Entra ID とアプリケーションとの統合について説明します。 組織は Microsoft Entra ID を使用して、顧客、パートナー、従業員がセキュリティが確保された認証と認可によって、アプリケーションにアクセスできるように します。

Microsoft Entra ID を使用すると、条件付きアクセス、Microsoft Entra 多要素認証、シングル サインオン (SSO)、アプリケーション プロビジョニングなどの機能により、ID とアクセスの管理が容易になり、セキュリティも強化されます。

詳細情報:

- [条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)
- [しくみ: Microsoft Entra 多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)
- [Microsoft Entra シームレス シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso)
- [Microsoft Entra ID におけるアプリ プロビジョニングとは何ですか？](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)

会社が Microsoft 365 のサブスクリプションを行っている場合、おそらく Microsoft Entra ID が使用されているでしょう。 さらには、各種アプリケーションにも Microsoft Entra ID を使用できます。 アプリケーションの管理、アイデンティティ管理機能、ツール、およびポリシーをアプリケーションポートフォリオのために集中管理する場合。 そのメリットは、セキュリティの強化、コストの削減、生産性の向上、コンプライアンスの有効化を行う統合ソリューションです。 さらに、オンプレミス アプリへのリモート アクセスもあります。

詳細情報:

- [Microsoft 365 の ID インフラストラクチャをデプロイする](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/deploy-identity-solution-overview?view=o365-worldwide&preserve-view=true)
- [Microsoft Entra ID でのアプリケーション管理](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-application-management)

### 新しいアプリケーションでの Microsoft Entra ID の使用

企業が新しいアプリケーションを取得したら、それを Microsoft Entra テナントに追加します。 Microsoft Entra ID に新しいアプリを追加する会社のポリシーを策定します。

参照: [クイックスタート: エンタープライズ アプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)

Microsoft Entra ID には統合アプリケーションのギャラリーがあり、簡単に使用を始められるようになっています。 Microsoft Entra 組織にギャラリーのアプリを追加し (前のリンクを参照)、サービスとしてのソフトウェア (SaaS) の統合に関するチュートリアルを確認します。

参照: [SaaS アプリケーションの Microsoft Entra ID との統合に関するチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)

#### 統合に関するチュートリアル

次のチュートリアルで、一般的なツールを Microsoft Entra シングル サインオン (SSO) と統合する方法について確認してください。

- チュートリアル: [Microsoft Entra SSO と ServiceNow の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/servicenow-tutorial)
- チュートリアル: [Microsoft Entra SSO と Workday の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-tutorial)
- チュートリアル: [Microsoft Entra SSO と Salesforce の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/salesforce-tutorial)
- チュートリアル: [Microsoft Entra SSO と AWS Single-Account Access の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/amazon-web-service-tutorial)
- チュートリアル: [Microsoft Entra SSO と Slack の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/slack-tutorial)

#### ギャラリーにないアプリ

ギャラリーに表示されないアプリケーション (組織内のアプリケーション、ベンダーのサードパーティ製アプリケーションなど) を統合できます。 ギャラリーにアプリを公開する要求を送信します。 社内で開発するアプリの統合については、「[開発者が構築したアプリを統合する](https://learn.microsoft.com/ja-jp/entra/fundamentals/five-steps-to-full-application-integration#integrate-apps-your-developers-build)」を参照してください。

詳細情報:

- [クイックスタート: エンタープライズ アプリケーションを表示する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/view-applications-portal)
- [Microsoft Entra アプリケーション ギャラリーでのアプリケーション公開の要求を送信する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing)

### 既存のアプリケーションの使用状況を特定し、統合の優先順位を設定する

従業員が使用しているアプリケーションを検出し、アプリと Microsoft Entra ID との統合の優先順位を設定します。 Microsoft Defender for Cloud Apps Cloud Discovery ツールを使用して、IT チームが管理していないアプリを検出して管理します。 Microsoft Defender for Endpoint (旧称 Microsoft Defender for Endpoint) により、検出プロセスが簡素化され、拡張されます。

詳細情報:

- [Cloud Discovery の設定](https://learn.microsoft.com/ja-jp/defender-cloud-apps/set-up-cloud-discovery)
- [Microsoft Defender for Endpoint](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender-endpoint/microsoft-defender-endpoint?view=o365-worldwide&preserve-view=true)

さらに、Azure portal の Active Directory フェデレーション サービス (AD FS) を使用して、組織内の AD FS アプリを検出します。 アプリにサインインした一意のユーザーを検出し、統合の互換性に関する情報を確認します。

参照: [AD FS アプリケーションの検出とスコープ設定](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-discover-scope-apps)

#### アプリケーションの移行

環境内のアプリを検出したら、移行して統合するアプリの優先順位を設定します。 次の要因を検討してください。

- 最も頻繁に使用されるアプリ
- 最も危険なアプリ
- 使用停止されるため移行に含まれないアプリ
- オンプレミスに留まるアプリ

参照: [アプリケーションを Microsoft Entra ID に移行するためのリソース](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migration-resources)

### アプリと ID プロバイダーを統合する

検出中に、IT チームによって追跡されないアプリケーションがあり、脆弱性が発生する可能性があります。 一部のアプリケーションでは、AD FS を含む代替 ID ソリューションや他の ID プロバイダー (IdP) を使用します。 ID およびアクセス管理を統合することをお勧めします。 利点は次のとおりです。

- オンプレミスのユーザー設定、認証、IdP ライセンス料金を削減する
- 合理化された ID およびアクセス管理プロセスを使用して管理オーバーヘッドを削減する
- マイ アプリ ポータルでアプリケーションへのシングル サインオン (SSO) アクセスを有効にする
    - 参照: [マイ アプリ ポータルでコレクションを作成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/access-panel-collections)
- Microsoft Entra ID 保護と条件付きアクセスを使用してアプリ使用信号を増やし、最近追加されたアプリにメリットを拡張する
    - [ID 保護とは](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)
    - [条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)

#### アプリ所有者の認識

Microsoft Entra ID とのアプリ統合の管理には、アプリケーション所有者の認識と関心を助ける次の資料が役立ちます。 ブランディングを反映して資料を変更します。

次の情報をダウンロードできます。

- Zip ファイル: [1 ページにまとめた編集可能な Microsoft Entra アプリ統合](https://aka.ms/AppOnePager)
- Microsoft PowerPoint プレゼンテーション: [Microsoft Entra アプリケーション統合ガイドライン](https://aka.ms/AppGuideline)

#### Active Directory フェデレーション サービス

SaaS アプリ、基幹業務アプリ (LOB)、さらに Microsoft 365 および Microsoft Entra アプリで認証に AD FS を使用することについて評価します。

[Image: SaaS アプリ、基幹業務アプリ、さらに Microsoft 365 および Microsoft Entra アプリでの AD FS 認証の図。]

アプリケーション認証を Microsoft Entra ID に移行することで、前の図に示された構成を改善します。 アプリのサインオンを有効にし、マイ アプリ ポータルを使用してアプリケーションを検出しやすくします。

詳細情報:

- [アプリケーション認証を Microsoft Entra ID に移行する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-apps-stages)
- [マイ アプリ ポータルからアプリにサインインして開始する](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)

次の図で、Microsoft Entra ID によってシンプルになったアプリ認証を確認してください。

[Image: Microsoft Entra ID を使用したアプリ認証の図。]

Microsoft Entra ID が IdP になると、AD FS を使用停止できる場合があります。

[Image: オンプレミス アプリ、基幹業務 (LOB) アプリ、SaaS アプリ、Office 365 の各アプリと Microsoft Entra が統合した図。]

さまざまなクラウド ベースの IdP を使用するアプリを移行できます。 組織に、複数の ID アクセス管理 (IAM) ソリューションが存在する場合があります。 1 つの Microsoft Entra インフラストラクチャに移行することで、IAM ライセンスへの依存とインフラストラクチャ コストの削減が可能となります。 Microsoft Entra ID が Microsoft 365 ライセンスの購入に含まれていた場合、おそらく、別途 IAM ソリューションを購入する必要はありません。

### オンプレミスのアプリケーションを統合する

従来は、企業ネットワークへの接続中にアプリケーション セキュリティによってアクセスが有効になっていました。 ただし、組織は、場所に関係なく、顧客、パートナー、従業員にアプリへのアクセス権を付与します。 Microsoft Entra のアプリケーション プロキシ サービスは、オンプレミス アプリを Microsoft Entra ID に接続します。エッジ サーバーなど他のインフラストラクチャを必要としません。

参照: [Microsoft Entra アプリケーション プロキシを使用して、リモート ユーザーにオンプレミス アプリを公開する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)

アプリケーション プロキシ サービスがユーザー要求処理するようすを次の図に示します。

[Image: Microsoft Entra アプリケーション プロキシ サービスがユーザー要求を処理している図。]

参照: [チュートリアル: Microsoft Entra ID のアプリケーション プロキシを使用して、リモート アクセス対応のオンプレミス アプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)

さらに、F5 BIG-IP APM や Zscaler Private Access などのアプリケーション デリバリー コントローラーを Microsoft Entra ID に統合します。 メリットは、最新の認証と ID 管理、トラフィック管理、セキュリティ機能です。 このソリューションは、安全なハイブリッド アクセスと呼ばれています。

参照: [安全なハイブリッド アクセス: Microsoft Entra ID を使用してレガシ アプリを保護する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/secure-hybrid-access)

次のサービスについては、Microsoft Entra への統合に関するチュートリアルがあります。

- [チュートリアル: Microsoft Entra SSO と Akamai の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/akamai-tutorial)
- [チュートリアル: Microsoft Entra SSO と Citrix ADC Security Assertion Markup Language (SAML) Connector for Microsoft Entra ID の統合 (Kerberos ベースの認証)](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/citrix-netscaler-tutorial)
    - 旧称 Citrix NetScaler
- [F5 BIG-IP と Microsoft Entra ID を統合する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-integration)
- [チュートリアル: Microsoft Entra ID と Zscaler Private Access (ZPA) の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscalerprivateaccess-tutorial)

### 開発者が構築したアプリを統合する

開発者のアプリの場合は、認証と承認に Microsoft ID プラットフォームを使用します。 統合アプリケーションは、ポートフォリオ内の他のアプリと同様に登録および管理されます。

詳細情報:

- [Microsoft ID プラットフォームのドキュメント](https://learn.microsoft.com/ja-jp/entra/identity-platform/)
- [クイック スタート: Microsoft ID プラットフォームにアプリケーションを登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)

開発者は、内部と顧客向けのアプリにプラットフォームを使用できます。 たとえば、Microsoft Authentication Libraries (MSAL) を使用して、アプリにアクセスするための多要素認証とセキュリティを有効にします。

詳細情報:

- [Microsoft Authentication Library (MSAL) の概要](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview)
- [Microsoft ID プラットフォームのコード サンプル](https://learn.microsoft.com/ja-jp/entra/identity-platform/sample-v2-code)
- ビデオ: [開発者向け Microsoft ID プラットフォームの概要](https://www.youtube.com/watch?v=zjezqZPPOfc&amp;list=PLLasX02E8BPBxGouWlJV-u-XZWOc2RkiX) (33:54)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/frontline-worker-management"} -->
## 現場ワーカーの管理 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/frontline-worker-management
- Service: entra / fundamentals
- Article date: 2025-03-19
- Summary: マイ スタッフ ポータルを通じて提供される現場ワーカー管理機能について説明します。

現場ワーカーは、世界の労働人口の 80% 以上を占めています。 しかし、規模の大きさ、労働力の入れ替えの速さ、プロセスの断片化が原因で、多くの場合、要求の厳しい現場ワーカーの仕事をわずかでも簡単にするためのツールが不足しています。 現場ワーカーの管理により、現場の従業員にデジタル変革がもたらされます。 従業員には、マネージャー、現場担当者、運用、IT が含まれる場合があります。

現場ワーカーの管理は、次のアクティビティを簡単に実行できるようにすることで、現場の従業員を支援します。

- マイ スタッフを使用した、一般的な IT タスクの合理化
- 簡略化された認証による最前線労働者のスムーズなオンボーディング
- 共有デバイスのシームレスなプロビジョニングと現場ワーカーの安全なサインアウト

### マイ スタッフによる委任されたユーザー管理

マイ スタッフ ポータルの Microsoft Entra ID を使うと、ユーザー管理を委任できます。 現場のマネージャーは、[マイ スタッフ ポータル](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/my-staff-configure)を使用して、貴重な時間を節約し、リスクを軽減できます。 管理者が、店舗や工場の現場から直接パスワード リセットや電話管理を簡単にできるようにすると、ヘルプデスク、IT 担当者、または運用担当者を介してリクエストを転送しなくても、マネージャーは従業員にアクセスを許可できます。

[Image: マイ スタッフ ポータルでの委任されたユーザー管理のスクリーンショット。]

### 簡略化された認証によるオンボードの迅速化

現場担当者は、多くの場合、ツールや情報にすばやく簡単にアクセスする必要があります。 Microsoft Entra ID は、このニーズを満たすために簡単な認証で高速オンボードを提供します。 現場担当者は、SMS サインインまたは QR コード サインインを使用して、デバイスやアプリケーションに簡単にアクセスできます。

#### SMS 認証

マイ スタッフを使用すると、現場の管理者が自分のチーム メンバーの電話番号を [SMS サインイン](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-sms-signin)用に登録できます。 多くの業種では、現場ワーカーはローカルのユーザー名とパスワードの組み合わせを維持していますが、多くの場合、これは煩雑で、コストがかかり、間違いが発生しやすい方策です。 IT が SMS サインインを使用した認証を有効にすると、現場担当者は、電話番号と SMS 経由で送信されるワンタイム パスコード (OTP) のみを使用して、Microsoft Teamsやその他のアプリケーションの [シングル サインオン (SSO](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on) ) を使用してサインインできます。 シングル サインオンを使用すると、現場担当者のサインインが簡単で安全になり、最も必要なアプリにすばやくアクセスできます。

[Image: SMS サインインのスクリーンショット。]

#### QR コード認証 (プレビュー)

QR コード認証は、サインインを迅速かつコスト効率の高い方法で提供し、生産性を向上させ、現場担当者にシームレスなエクスペリエンスを提供します。 このメソッドでは、QR コードとユーザー定義の 8 桁の PIN を使用します。 QR コードと PIN を組み合わせて使用して、デバイスまたはアプリケーションにサインインします。

QR コードには、ユーザー プリンシパル名 (UPN)、テナント ID、および秘密鍵が含まれています。 PIN を設定すると、管理者によって割り当てられた既定の一時 PIN が置き換えられます。 PIN は QR コードでのみ機能し、UPN や電話番号などの他の識別子では機能しません。 PIN がないと QR コードを使用することもできません。

[Image: QR コードと PIN のスクリーンショット。]

QR コード認証方法は、従来の方法と比較して、現場担当者にとって 2 つの主な利点を提供します。

- サインインの高速化: QR コード認証を使用すると、ユーザー名とパスワードが不要になります。これにより、技術に精通していないユーザーやアクセシビリティの課題があるユーザーにメリットがあります。 QR コードをスキャンすると、ログイン時間が約 2 秒短縮され、作業者の生産性が向上します。 また、ユーザーがサインインのためにユーザー名を覚える必要はないので、忘れたユーザー名に関連する IT チケットも減少します。
- コスト効率: QR コードの印刷はハードウェア キーを提供するよりも安価であり、作業者は QR コードをバッジやウェアラブルにアタッチできます。 現場担当者は、多くの場合、一時的なポジションを保持し、戻らない可能性があり、コストの高いデバイスでの投資損失のリスクを軽減するため、組織はこの方法を好みます。

[QR コード認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-qr-code)の詳細と、組織[で有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-qr-code)方法について説明します。

### 現場担当者向けの共有デバイス

現場の管理者は、Managed Home Screen (MHS) アプリケーションを使用して、ワーカーが、Intune に登録された自分の Android 専用デバイスから特定のアプリケーション セットにアクセスできるようにすることもできます。 専用デバイスは、[Microsoft Entra 共有デバイス モード](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-shared-devices)で登録されます。 Microsoft Intune 管理センターでマルチアプリ キオスク モードで構成されている場合、MHS はデバイスの既定のホーム画面として自動的に起動され、 *唯一* のホーム画面としてエンド ユーザーに表示されます。 詳細については、[Android Enterprise 用に Microsoft Managed Home Screen アプリを構成する](https://learn.microsoft.com/ja-jp/mem/intune/apps/app-configuration-managed-home-screen-app)方法を参照してください。

#### 共有デバイスからの現場ワーカーの安全なサインアウト

多くの企業の現場ワーカーは、共有デバイスを使用して、在庫管理と販売トランザクションを行います。 デバイスを共有すると、それらを個別にプロビジョニングおよび追跡するために要する IT 担当者の負荷が軽減されます。 共有デバイスのサインアウトを使用すると、現場ワーカーは共有デバイスをハブに戻したり次のシフトのチームメイトに渡したりする前に、簡単にそのすべてのアプリから安全にサインアウトできます。 現場ワーカーは、Microsoft Teams を使用して、自分に割り当てられたタスクを表示できます。 ワーカーが共有デバイスからサインアウトすると、Intune と Microsoft Entra ID によってその会社のすべてのデータがクリアされるため、デバイスを安全に次の同僚に渡すことができます。 [Microsoft 認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/msal/objc/shared-devices-ios)を使用して、この機能をすべての基幹業務 [iOS](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-shared-devices) および [Android](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) アプリに統合することができます。

[Image: 共有デバイスのサインアウトのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/get-started-premium"} -->
## Premium エディションにサインアップする - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium
- Service: entra / fundamentals
- Article date: 2025-03-05
- Summary: Microsoft Entra ID P1 または P2 エディションのサインアップ方法について説明します。

### 概要

Microsoft Entra ID P1 または P2 エディションを購入し、Azure サブスクリプションに関連付けることができます。 新しい Azure サブスクリプションを作成する必要がある場合は、ライセンス プランと Microsoft Entra ID サービス アクセスをアクティブ化する必要もあります。 無料試用版の取得については、「[Microsoft Entra ID P2 試用版](https://signup.microsoft.com/get-started/signup?products=FAF849AB-BD30-42B2-856C-8F1EDC230CE9)」をご覧ください。

Microsoft Entra ID P1 または P2 にサインアップする前に、まず、使用する既存のサブスクリプションまたはプランを決定する必要があります。

- 既存の Azure または Microsoft 365 サブスクリプションを通じて。
- Enterprise Mobility + Security ライセンス プランを通じて。
- Microsoft ボリューム ライセンス プランを通じて。

### サインアップ オプション

以前に購入してアクティブ化した Microsoft Entra ID ライセンスを使用して Azure サブスクリプションを使用してサインアップすると、システムは同じディレクトリ内のライセンスを自動的にアクティブ化します。 そうでない場合は、やはりライセンス プランと Microsoft Entra ID サービス アクセスをアクティブにする必要があります。

#### 既存の Azure または Microsoft 365 サブスクリプションを使用してサインアップする

Azure または Microsoft 365 サブスクライバーは、Microsoft Entra ID P1 または P2 エディションをオンラインで購入できます。 詳細な手順については、「[ライセンスを購入または削除する](https://learn.microsoft.com/ja-jp/microsoft-365/commerce/licenses/buy-licenses?view=o365-worldwide&preserve-view=true)」を参照してください。

#### Enterprise Mobility + Security ライセンス プランを使用してサインアップする

Enterprise Mobility + Security は、Microsoft Entra ID P1 または P2、Azure Information Protection、および Microsoft Intune で構成されるスイートです。 Microsoft Enterprise Mobility + Security ライセンスを既にお持ちの場合は、次のいずれかのライセンス オプションを使って Microsoft Entra ID の使用を開始できます。

Microsoft Enterprise Mobility + Security の詳細については、[Enterprise Mobility + Security の Web サイト](https://www.microsoft.com/cloud-platform/enterprise-mobility-security)をご覧ください。

- [Microsoft 365 Developer](https://developer.microsoft.com/microsoft-365/dev-program) プログラムに参加し、資格があれば、無料の再生可能な Microsoft 365 E5 インスタント サンドボックスを入手します。
- [Enterprise Mobility + Security E5 ライセンスを購入します](https://signup.microsoft.com/Signup?OfferId=e6de2192-536a-4dc3-afdc-9e2602b6c790&amp;ali=1)。
- [Enterprise Mobility + Security E3 ライセンスを購入します](https://signup.microsoft.com/Signup?OfferId=4BBA281F-95E8-4136-8B0F-037D6062F54C&amp;ali=1)。

#### マイクロソフト ボリューム ライセンス プランを使用してサインアップする

マイクロソフト ボリューム ライセンス プランを利用して、取得したいライセンス数に基づいて、次の 2 つのプログラムのどちらかを使って Microsoft Entra ID P1 または P2 にサインアップできます。

- 250 以上のライセンスの場合、[Microsoft Enterprise Agreement](https://www.microsoft.com/en-us/licensing/licensing-programs/enterprise.aspx) に関するページを参照してください。
- 5 から 250 のライセンスの場合、[オープン ボリューム ライセンス](https://www.microsoft.com/en-us/licensing/licensing-programs/open-license.aspx)に関するページを参照してください。
- ボリューム ライセンス購入オプションの詳細については、「[How to purchase through Volume Licensing](https://www.microsoft.com/en-us/licensing/how-to-buy/how-to-buy.aspx)」(ボリューム ライセンスでの購入方法) を参照してください。

### 新しいライセンス プランをアクティブ化する

新しい Microsoft Entra ID ライセンス プランを使用してサインアップした場合は、購入後に送信された確認メールを使用して、所属する組織に対してそのプランをアクティブにする必要があります。

#### ライセンス プランをアクティブ化するには、次の手順を実行します。

1. サインアップした後で Microsoft から受け取った確認メールを開きます。

    [Image: サインインおよびサインアップ用のリンクを含む確認メールのスクリーンショット。]
2. **[サインイン]** または **[サインアップ]** を選択します。

    - **サインインします。** 既存のテナントがある場合は、このオプションを選んでから、既存の管理者アカウントを使ってサインインします。 ライセンスをアクティブ化するテナントのグローバル管理者である必要があります。
    - **[サインアップ] 。** **[アカウント プロファイルの作成]** ページを開き、ライセンス プラン用に新しい Microsoft Entra テナントを作成する場合は、このオプションを選びます。

終えると、テナントに対するライセンス プランのアクティブ化の確認を受け取ります。

### Microsoft Entra ID アクセスをアクティブにする

既存のサブスクリプションに新しい Microsoft Entra ID P1 または P2 ライセンスを追加する場合は、Microsoft Entra ID アクセスをアクティブにしておく必要があります。 それ以外の場合は、**ウェルカム メール**を受信した後で Microsoft Entra ID アクセスをアクティブにする必要があります。

購入済みのライセンスがディレクトリでプロビジョニングされた後、**ウェルカム メール**を受信します。 このメールで、Microsoft Entra ID P1 または P2 または Enterprise Mobility + Security のライセンスと機能の管理を開始できることを確認します。

ヒント

ウェルカム メールから Microsoft Entra ディレクトリ アクセスをアクティブにするまで、新しいテナントの Microsoft Entra ID にアクセスすることはできません。

#### Microsoft Entra ID アクセスをアクティブにするには

1. **ウェルカム メール**を開き、[**サインイン**] を選択します。
2. 正常にサインインした後は、モバイル デバイスを使用して 2 段階認証へ進みます。

通常、アクティブ化のプロセスにかかる時間は数分のみで、その後は、お使いの Microsoft Entra テナントを使用できるようになります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/how-subscriptions-associated-directory"} -->
## 既存の Azure サブスクリプションをテナントに追加する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/how-subscriptions-associated-directory
- Service: entra / fundamentals
- Article date: 2026-06-19
- Summary: 既存の Azure サブスクリプションを Microsoft Entra テナントに追加する方法について説明します。

### 概要

すべての Azure サブスクリプションには、Microsoft Entra テナントとの信頼関係があります。 サブスクリプションは、セキュリティ プリンシパルとデバイスの認証と承認を行うために、このテナント (ディレクトリ) に依存します。 サブスクリプションの有効期限が切れると、信頼されたインスタンスは残りますが、セキュリティ プリンシパルは Azure リソースへのアクセスを失います。 サブスクリプションは 1 つのディレクトリのみを信頼できますが、1 つの Microsoft Entra テナントは複数のサブスクリプションによって信頼される可能性があります。

サブスクリプションは、Azure リソースとAzure ロールの割り当てが管理されるスコープと考え、テナントはサインインとアクセスの受信に使用される ID を含むディレクトリと考えてください。 Azureロールは、サブスクリプション内のAzureリソースへのアクセスを制御します。 Microsoft Entraロールは、ユーザー、グループ、ドメインなどのディレクトリ リソースへのアクセスを制御します。 サブスクリプションのディレクトリを変更すると、Azureロールベースのアクセス制御 (Azure RBAC) の ID を提供するテナントが変更されますが、サブスクリプション所有者はテナントのグローバル管理者になりません。

既定では、Microsoft Entra テナントを作成するユーザーには、 [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) ロールが自動的に割り当てられます。 ただし、サブスクリプションの所有者がサブスクリプションを既存のテナントに参加させる場合、所有者はグローバル管理者ロールに割り当てられません。

ユーザーは認証 *ホーム* ディレクトリを 1 つだけ持つことができますが、ユーザーは複数のディレクトリにゲストとして参加できます。 Microsoft Entra ID では、各ユーザーのホーム ディレクトリとゲスト ディレクトリの両方を確認できます。

[Image: Azure サブスクリプションと Microsoft Entra ディレクトリの間の信頼関係を示すスクリーンショット。]

Important

サブスクリプションが別のディレクトリに関連付けられている場合、 [Azure ロールベースのアクセス制御](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal) を使用してロールが割り当てられているユーザーはアクセスできなくなります。 サービス管理者や共同管理者を含む従来のサブスクリプション管理者もアクセスできなくなります。

Azure Kubernetes Service (AKS) クラスターを別のサブスクリプションに移動するか、クラスター所有サブスクリプションを新しいテナントに移動すると、ロールの割り当てとサービス プリンシパルの権限が失われたため、クラスターの機能が失われます。 AKS の詳細については、 [Azure Kubernetes Service (AKS)](https://learn.microsoft.com/ja-jp/azure/aks/) に関するページを参照してください。

### [前提条件]

サブスクリプションを関連付けるか追加する前に、次の手順を実行します。

- サブスクリプションの関連付けまたは追加後に発生する次の変更の一覧と、影響を受ける可能性がある変更の一覧を確認します。

    - Azure RBAC を使用してロールを割り当てられたユーザーは、アクセス権を失います。
    - サービス管理者と Co-Administrators はアクセスできなくなります。
    - キー コンテナーがある場合はアクセスできなくなり、関連付けを行った後にそれらを修正する必要があります。
    - Virtual Machines や Logic Apps などのリソースのマネージド ID がある場合は、関連付け後に再度有効にするか再作成する必要があります。
    - Azure Stack が登録されている場合は、関連付け後に再登録する必要があります。

    詳細については、「 [Azure サブスクリプションを別の Microsoft Entra ディレクトリに転送する](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/transfer-subscription)」を参照してください。
- 次のアカウントを使用してサインインします。

    - サブスクリプションの [所有者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#owner) 役割の割り当てがあります。 所有者ロールを割り当てる方法については、「 [Azure portal を使用して Azure ロールを割り当てる」を](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal)参照してください。
    - 現在のディレクトリと新しいディレクトリの両方に存在します。 現在のディレクトリはサブスクリプションに関連付けられています。 新しいディレクトリをサブスクリプションに関連付けます。 別のディレクトリへのアクセスの詳細については、 [Azure portal での Microsoft Entra B2B コラボレーション ユーザーの追加に関するページを](https://learn.microsoft.com/ja-jp/entra/external-id/add-users-administrator)参照してください。
    - Azure クラウド サービス プロバイダー (CSP) サブスクリプション (MS-AZR-0145P、MS-AZR-0146P、MS-AZR-159P)、Microsoft Internal サブスクリプション (MS-AZR-0015P)、または Microsoft Azure for Students Starter サブスクリプション (MS-AZR-0144P) を使用していないことを確認します。

### サブスクリプションをディレクトリに関連付ける

既存のサブスクリプションを Microsoft Entra ID に関連付けるには、次の手順に従います。

1. サブスクリプションの[所有者](https://portal.azure.com)ロールの割り当てを使用して [Azure portal](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#owner) にサインインします。
2. **[サブスクリプション]** を参照します。
3. 使用するサブスクリプションの名前を選択します。
4. [ **ディレクトリの変更] を選択します**。

    [Image: [ディレクトリの変更] オプションが強調表示されている [サブスクリプション] ページを示すスクリーンショット。]
5. 表示される警告を確認し、[ **変更**] を選択します。

    [Image: サンプル ディレクトリと [変更] ボタンが強調表示された [ディレクトリの変更] ページを示すスクリーンショット。]

    サブスクリプションのディレクトリが変更されると、成功メッセージが表示されます。
6. サブスクリプション ページで [ **ディレクトリの切り替え** ] を選択して、新しいディレクトリに移動します。

    [Image: サンプル情報を含む [ディレクトリ スイッチャー] ページを示すスクリーンショット。]

    すべてが正しく表示されるまでに数時間かかることがあります。 時間がかかりすぎる場合は、 **グローバル サブスクリプション フィルター**を確認してください。 移動したサブスクリプションが非表示になっていないことを確認します。 新しいディレクトリを表示するには、Azure portal からサインアウトし、もう一度サインインすることが必要な場合があります。

サブスクリプション ディレクトリの変更はサービス レベルの操作であるため、サブスクリプションの課金所有権には影響しません。 元のディレクトリを削除するには、サブスクリプションの課金所有権を新しいアカウント管理者に譲渡する必要があります。課金所有権の譲渡の詳細については、「 [Azure サブスクリプションの所有権を別のアカウントに譲渡する](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/billing-subscription-transfer)」を参照してください。

### 関連付け後の手順

サブスクリプションを別のディレクトリに関連付けた後、操作を再開するには、次のタスクを実行する必要がある場合があります。

1. Azure リソースへのアクセスが必要なユーザー、グループ、またはサービス プリンシパルに対して、新しいディレクトリAzureロールを再割り当てします。 前のディレクトリからのロールの割り当ては転送されません。
2. キー コンテナーがある場合は、キー コンテナーのテナント ID を変更する必要があります。 詳細については、「[サブスクリプション移行後のキー コンテナー テナント ID の変更](https://learn.microsoft.com/ja-jp/azure/key-vault/general/move-subscription)」を参照してください。
3. リソースにシステム割り当てマネージド ID を使用した場合は、これらの ID を再度有効にする必要があります。 ユーザー割り当てマネージド ID を使用した場合は、これらの ID を再作成する必要があります。 マネージド ID を再度有効にするか再作成した後、それらの ID に割り当てられたアクセス許可を再確立する必要があります。 詳細については、「 [Azure リソースのマネージド ID とは」を](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)参照してください。
4. このサブスクリプションを使用して Azure Stack を登録した場合は、再登録する必要があります。 詳細については、「 [Azure Stack Hub を Azure に登録する」](https://learn.microsoft.com/ja-jp/azure-stack/operator/azure-stack-registration)を参照してください。

詳細については、「 [Azure サブスクリプションを別の Microsoft Entra ディレクトリに転送する](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/transfer-subscription)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/how-to-assign-roles-to-users"} -->
## Microsoft Entra のユーザー ロールを管理する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-assign-roles-to-users
- Service: entra / fundamentals
- Article date: 2026-06-19
- Summary: Microsoft Entra IDを使用してユーザー ロールを割り当て、更新する方法について説明します。

リソースを管理する機能は、必要なアクセス許可を提供するロールを割り当てることによって付与されます。 ロールは、個々のユーザーまたはグループに割り当てることができます。 [ゼロ トラストの基本原則](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/zero-trust)に合わせて、ロールを割り当てるときに Just-In-Time ポリシーと Just-Enough-Access ポリシーを使用します。

この記事では、Microsoft Entra 管理センターのユーザーにロールを直接割り当てる方法について説明します。

### 前提条件

ユーザーにロールを割り当てる前に、次のMicrosoft Learn の記事を確認してください。

- [Microsoft Entra ロールについて学習する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/concept-understand-roles)
- [ロールベースのアクセス制御について説明します](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/rbac-and-directory-admin-roles)
- [Azure組み込みロールを調べる](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)

Privileged Identity Management を使用するには、Microsoft Entra ID P2 または Microsoft Entra ID ガバナンス ライセンスが必要です。 ライセンスの詳細については、「[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)」をご覧ください。

### ロールを割り当てる

ユーザーにロールを直接割り当てる必要がある場合は、ユーザーを選択し、ロールを選択して、設定を調整します。 1 回限りのシナリオではユーザーにロールを直接割り当てる必要がある場合は、グループを使用して大規模なロールの割り当てを管理することを検討してください。 詳細については、「[グループを使用してロールの割り当てを管理する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept)」を参照してください。

対象となるロールはユーザーに割り当てられますが、ユーザー自身が Privileged Identity Management (PIM) を使用して Just-In-Time で昇格する必要があります。 PIM の使用方法の詳細については、[Privileged Identity Management](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/)を参照してください。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. ロールの割り当てを取得するユーザーを検索して選択します。

    [Image: ユーザーが強調表示されている [すべてのユーザー] リストのスクリーンショット。]
4. サイド メニューから **[割り当てられたロール** ] を選択し、[ **割り当ての追加]** を選択します。

    [Image: [割り当ての追加] が強調表示されている [割り当てられたロール] ページのスクリーンショット。]
5. ドロップダウン リストから割り当てるロールを選択し、[ **次へ** ] ボタンを選択します。
6. **割り当ての種類**を選択します。

    組織に Microsoft Entra ID P2、Microsoft Entra ID ガバナンス、またはMicrosoft Entra スイート ライセンスがある場合は、*ロールを有資格*または*アクティブ*として割り当てることができます。 組織に Free または Microsoft Entra ID P1 ライセンスがある場合は、*ロールをアクティブ*としてのみ割り当てることができます。

    [Image: ロールの割り当て設定のスクリーンショット。]
7. ロールが常にユーザーの昇格に**使用できるようにする**必要がある場合は、[*永続的に適格*] オプションを選択したままにします。

    このオプションをオフにした場合は、ロールの適格性の日付範囲を指定できます。
8. **割り当て**ボタンを選択してください。

    割り当てられたロールはユーザーの関連付けられたセクションに表示されるため、有資格ロールとアクティブ ロールは個別に一覧表示されます。

### ロールを更新する

ロールの割り当ての設定を変更できます。たとえば、アクティブなロールを有資格に変更できます。

1. **Entra ID**&gt;**Users** に移動します。
2. ロールを更新するユーザーを検索して選択します。
3. サイド メニューから **[割り当てられたロール** ] を選択し、[ **有資格の割り当て** ] または **[アクティブな割り当て**] を選択します。
4. 変更する必要があるロールの **[更新]** リンクを選択します。

    [Image: [削除] オプションと [更新] オプションが強調表示されている [割り当てられたロール] ページのスクリーンショット。]
5. 必要に応じて設定を変更し、[ **保存** ] ボタンを選択します。

    [Image: ロール メンバーシップ設定パネルのスクリーンショット。]

### ロールを削除する

選択したユーザーの **[管理ロール** ] ページからロールの割り当てを削除できます。

1. **Entra ID**&gt;**Users** に移動します。
2. ロールの割り当てを削除するユーザーを検索して選択します。
3. [ **割り当てられたロール** ] ページに移動し、削除する必要があるロールの [ **削除** ] リンクを選択します。 ポップアップ メッセージの変更を確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/how-to-create-delete-users"} -->
## Microsoft Entra IDでユーザーを作成、招待、削除する方法 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users
- Service: entra / fundamentals
- Article date: 2026-05-08
- Summary: IT 管理者が新しいユーザーを作成し、外部ゲストを招待し、Microsoft Entra IDで既存のユーザーを削除する手順。

Microsoft Entra ID では、テナントにいくつかの種類のユーザーを作成できます。これにより、組織のユーザーをより柔軟に管理することができます。

この記事では、従業員テナント内で新しいユーザーの作成、外部ゲストの招待、ユーザーの削除を行う方法について説明します。 また、 [Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/customers/overview-customers-ciam) シナリオの外部テナントにユーザーを作成する方法についても説明します。

Note

個人データの表示または削除の詳細については、GDPR サイトに [対する Windows データ主体の要求](https://learn.microsoft.com/ja-jp/microsoft-365/compliance/gdpr-dsr-windows) に関する Microsoft のガイダンスを確認してください。 GDPR に関する一般情報については、[Microsoft Trust Center の GDPR に関するセクション](https://www.microsoft.com/trust-center/privacy/gdpr-overview)および [Service Trust Portal の GDPR に関するセクション](https://servicetrust.microsoft.com/ViewPage/GDPRGetStarted)をご覧ください。

### ユーザーの種類

新しいユーザーを作成または招待する前に、少し時間をかけて、Microsoft Entra 従業員テナント内のユーザーの種類、認証方法、アクセス権を確認します。 たとえば、内部ゲスト、内部ユーザー、外部ゲストを作成する必要がありますか。 新しいユーザーにはゲストまたはメンバーの特権が必要でしょうか。

#### 従業員テナントのユーザー

Microsoft Entra 従業員テナントには、次のユーザーの種類があります。

- **内部メンバー**: これらのユーザーは、組織内の正社員である可能性が最も高いです。
- **内部ゲスト**: これらのユーザーはテナントにアカウントを持っていますが、ゲスト レベルの特権を持っています。 これらは B2B Collaboration が利用できるようになる前にテナント内で作成された可能性があります。
- **外部メンバー**: これらのユーザーは外部アカウントを使用して認証しますが、テナントへのメンバー アクセス権を持っています。 これらの種類のユーザーは、 [マルチテナント組織](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/overview#what-is-the-multitenant-organization-scenario)で一般的です。
- **外部ゲスト**: これらのユーザーは、外部メソッドを使用して認証し、ゲスト レベルの特権を持つテナントの真のゲストです。

内部ゲストと外部ゲストとメンバーの違いの詳細については、 [B2B コラボレーション プロパティ](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)を参照してください。

認証方法は、作成するユーザーの種類によって異なります。 内部ゲストと内部メンバーは、管理者によって管理できるご使用の Microsoft Entra テナント内に資格情報があります。 これらのユーザーは、自分のパスワードをリセットすることもできます。 外部メンバーは、自分のホームの Microsoft Entra テナントに対して認証を行います。ご使用の Microsoft Entra テナントでは、外部メンバーの Microsoft Entra テナントとのフェデレーション サインインを通じてユーザーが認証されます。 外部メンバーがパスワードを忘れた場合、そのメンバーの Microsoft Entra テナントの管理者がパスワードをリセットできます。 外部ゲストは、自分のアカウントが作成されるときにメールで受信するリンクを使って自分のパスワードを設定します。

作成する必要があるユーザーの種類を決定するときに、既定のユーザー アクセス許可を確認すると役立つこともあります。 詳細については、「既定の [ユーザーアクセス許可を設定](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions)する」を参照してください。

#### 外部テナントのユーザー

*外部*構成の Microsoft Entra テナントは、[Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/customers/overview-customers-ciam) シナリオ専用に使用されます。 外部テナントには、次のユーザーの種類を含めることができます。

- **内部ユーザー**: これらのユーザーは内部認証を行い、通常は外部テナントで [Microsoft Entra ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) が割り当てられている管理者です。
- **外部ユーザー**: これらのユーザーは、外部テナントに登録されているアプリのコンシューマーおよびビジネス ユーザーです。 [既定のユーザー特権](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-user-permissions)を持つローカル アカウントを持ち、ローカル アカウントまたは外部 ID プロバイダーを介して認証します。 新しい外部ユーザーを作成する方法を参照してください。
- **外部ゲスト**: これらのユーザーは、独自の外部資格情報を使用してサインインし、通常は外部テナントに割り当てられた [Microsoft Entra ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持つ管理者です。

詳細については、「外部テナントの [既定のユーザーアクセス許可](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-user-permissions) 」を参照してください。

### 前提条件

必要な最小特権のロールは、追加するユーザーの種類と、Microsoft Entra ロールを同時に割り当てる必要があるかどうかによって変わります。 可能な限り、最小限の特権ロールを使用する必要があります。

| Task | 役割 |
| --- | --- |
| 新しいユーザーを作成する | ユーザー管理者 |
| 外部ゲストを招待する | ゲスト招待者 |
| Microsoft Entra ロールを割り当てる | 特権ロール管理者 |

### 新しいユーザーを作成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。

    [Image: Microsoft Entra ID の [すべてのユーザー] ページのスクリーンショット。]
3. **[新しいユーザー]**&gt;**[新しいユーザーの作成]** を選択します。

    [Image: Microsoft Entra ID の [新しいユーザーの作成] メニューのスクリーンショット。]
4. **新しいユーザー** ページの残りのタブを完了させてください。

#### Basics

[ **基本** ] タブには、新しいユーザーを作成するために必要なコア フィールドが含まれています。 開始する前 [に、ユーザー名のプロパティに関するガイダンスを確認](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-user-profile-info#guidance-on-user-name-properties)してください。

- **ユーザー プリンシパル名**: 一意のユーザー名を入力し、@ 記号の後のメニューからドメインを選択します。 新しいドメインを作成する必要がある場合は、[ドメイン **が表示されません** ] を選択します。 詳細については、「 [カスタム ドメイン名の追加」を参照してください](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)。
- **メール ニックネーム**: 入力したユーザー プリンシパル名とは異なるメール ニックネームを入力する必要がある場合は、[ **ユーザー プリンシパル名から派生** ] オプションをオフにしてから、メールのニックネームを入力します。
- **表示名**: Chris Green や Chris A. Green など、ユーザーの名前を入力します。
- **パスワード**: ユーザーが最初のサインイン時に使用するパスワードを指定します。 [ **パスワードの自動生成** ] オプションをオフにして、別のパスワードを入力します。
- **アカウントが有効**: このオプションは既定でオンになっています。 これをオフにすると、新しいユーザーがサインインできなくなります。 この設定は、ユーザーの作成後に変更できます。 この設定は、レガシ作成ユーザー プロセスでの **サインインのブロック** と呼ばれます。

[ **確認と作成** ] ボタンを選択して新しいユーザーを作成するか、[ **次へ: プロパティ** ] を選択して次のセクションを完了します。

[Image: [Create new user Basics](https://learn.microsoft.com/ja-jp/entra/fundamentals/新しいユーザーの作成の基本) タブのスクリーンショット。]

#### プロパティ

指定できるユーザー プロパティには、5 つのカテゴリがあります。 これらのプロパティは、ユーザーの作成後に追加または更新できます。 これらの詳細を管理するには、 **Entra ID**&gt;**Users** に移動し、更新するユーザーを選択します。

- **同一性：** ユーザーの姓と名を入力します。 [ユーザーの種類] を [メンバー] または [ゲスト] に設定します。
- **ジョブ情報:** ユーザーの役職、部署、マネージャーなど、ジョブ関連の情報を追加します。
- **連絡先情報:** ユーザーに関連する連絡先情報を追加します。
- **保護者によるコントロール:** K-12 学区などの組織では、ユーザーの年齢グループを指定する必要がある場合があります。 "年少者" は12歳以下、"未成年" は 13 から 18歳、"大人" は 18 歳より上です。 年齢グループと親オプションによって提供される同意の組み合わせによって、法的年齢グループの分類が決まります。 法的年齢グループの分類により、ユーザーのアクセス権と権限が制限される場合があります。
- **設定：** ユーザーのグローバルな場所を指定します。

[ **確認と作成** ] ボタンを選択して新しいユーザーを作成するか、[ **次へ: 割り当て]** を選択して次のセクションを完了します。

#### Assignments

アカウントの作成時に、ユーザーを管理単位、グループ、または Microsoft Entra ロールに割り当てることができます。 ユーザーを最大 20 のグループまたはロールに割り当てることができます。 ユーザーは 1 つの管理単位にのみ割り当てることができます。 ユーザーの作成後に割り当てを追加できます。

**新しいユーザーにグループを割り当てるには**:

1. [ **+ グループの追加] を選択します**。
2. 表示されるメニューから、リストから最大 20 個のグループを選択し、[選択] ボタンを **選択** します。
3. **レビューと作成**ボタンを選択します。

    [Image: グループ割り当ての追加プロセスのスクリーンショット。]

**新しいユーザーにロールを割り当てるには**:

1. [ **+ ロールの追加] を選択します**。
2. 表示されるメニューから、一覧から最大 20 個のロールを選択し、[選択] ボタンを **選択** します。
3. **レビューと作成**ボタンを選択します。

**新しいユーザーに管理単位を追加するには**:

1. [ **+ 管理単位の追加]** を選択します。
2. 表示されたメニューから、一覧から 1 つの管理単位を選択し、[ **選択** ] ボタンを選択します。
3. **レビューと作成**ボタンを選択します。

#### 確認と作成

最後のタブでは、ユーザー作成プロセスからいくつかの重要な詳細がキャプチャされます。 詳細を確認し、すべてが適切な場合は **[作成** ] ボタンを選択します。

### 新しい外部ユーザーを作成する

Important

これらの手順は [Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/customers/overview-customers-ciam) テナントにのみ適用されます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. 外部テナントにサインインしていることを確認します。 上部のメニュー設定アイコンを使用して、[**ディレクトリ + サブスクリプション**] メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**Users** に移動します。
4. [**新しいユーザー**] を選択&gt;**新しい外部ユーザーを作成します**。

    [Image: Microsoft Entra ID の [Create new external user](https://learn.microsoft.com/ja-jp/entra/fundamentals/新しい外部ユーザーの作成) メニューのスクリーンショット。]
5. [ **新しいユーザーの作成** ] ページで、この記事で前述したように [ 基本 ] タブを完了しますが、次のバリエーションがあります。

    - ユーザー プリンシパル名とメール ニックネームの代わりに、サインイン用のユーザーのメール アドレスを指定します。 **[ID] の**横にある [**サインイン方法] で** 、[**電子メール**] を選択します。 [ **値]** に、ユーザーのメール アドレスを入力します。
    - ユーザーに複数のメールを追加するには、[ **追加** ] ボタンを選択します。
6. (省略可能)[ **次へ: プロパティ]** を選択します。 この記事で前述したように[ プロパティ ]タブを完了しますが、次のバリエーションに注意してください。

    - [ **ID** ] セクションの [ **ユーザーの種類** ] 設定は外部ユーザーには影響せず、既定の **メンバー** 設定のままにすることができます。
    - [ **承認情報** ] フィールドは、外部ユーザーには使用できません。
    - [ **ジョブ情報]** では、従業員とマネージャーに関連する情報は外部ユーザーには使用できません。
7. (省略可能) **[次へ: 割り当て] を選択します**。 この記事で前述したように [ 割り当て ] タブを完了しますが、[ **管理単位の追加]** オプションと **[ロールの追加]** オプションは外部ユーザーには使用できないことに注意してください。
8. [ **確認と作成** ] ボタンを選択して、新しいユーザーを作成します。

### 外部ユーザーを招待する

外部ゲスト ユーザーを招待するための全体的なプロセスは似ていますが、[ **基本** ] タブと電子メールの招待プロセスに関するいくつかの詳細を除きます。 外部ユーザーを管理単位に割り当てることはできません。

Note

この機能は、従業員と外部の両方のテナントに適用されますが、現在、外部テナントではプレビュー段階です。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. [ **新しいユーザー**&gt;**外部ユーザーを**招待する] を選択します。

    [Image: [外部ユーザーの招待] メニュー オプションのスクリーンショット。]
4. 次に示すように、[ **新しいユーザー** ] ページの残りのタブを完了します。

#### 外部ユーザーについての基本事項

このセクションでは、ゲストの *メール アドレス*を使用して、ゲストをテナントに招待します。 ドメイン アカウントでゲスト ユーザーを作成する必要がある場合は、 新しいユーザーの作成プロセス を使用しますが、[ **ユーザーの種類]** を **[ゲスト**] に変更します。

- **電子メール**: 招待するゲスト ユーザーのメール アドレスを入力します。
- **表示名: 表示**名を指定します。
- **招待メッセージ**: ゲストに簡単なメッセージをカスタマイズするには、[ **招待メッセージの送信** ] チェック ボックスをオンにします。 必要に応じて CC 受信者を指定します。

[Image: 外部ユーザーの招待の [基本] タブのスクリーンショット。]

#### ゲスト ユーザーの招待

招待メールを送信して外部ゲスト ユーザーを招待したとき、ユーザーの詳細から招待の状態をチェックできます。

1. **Entra ID**&gt;**Users** に移動します。
2. 招待されたゲスト ユーザーを選択します。
3. [ **マイ フィード** ] セクションで、 **B2B コラボレーション**タイルを見つけます。
    - 招待の状態が **PendingAcceptance** の場合は、[ **招待の再送信** ] リンクを選択して別のメールを送信します。
    - ユーザーの **プロパティ** を選択し、 **招待の状態**を表示することもできます。

[Image: 招待の状態オプションが強調表示されているユーザーの詳細のスクリーンショット。]

### 他のユーザーを追加する

Azure Active Directory B2C (Azure AD B2C) ディレクトリにコンシューマー アカウントを手動で作成することが必要になるシナリオも考えられます。 コンシューマー アカウントの作成の詳細については、「 [Azure AD B2C でのコンシューマー ユーザーの作成と削除](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/manage-users-portal)」を参照してください。

Important

2025 年 5 月 1 日より、Azure Active Directory B2C (Azure AD B2C) は新規のお客様が購入できなくなります。 詳細については、FAQ の [「Azure AD B2C を引き続き購入できますか?」を](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale) 参照してください。

Microsoft Entra ID (クラウド) と Windows Server Active Directory (オンプレミス) の両方の環境がある場合は、既存のユーザー アカウントのデータを同期することによって新しいユーザーを追加できます。 ハイブリッド環境とユーザーの詳細については、「 [オンプレミスのディレクトリを Microsoft Entra ID と統合する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/whatis-hybrid-identity)。

### ユーザーを削除する

[Microsoft Entra 管理センター](https://entra.microsoft.com)を使用して、既存のユーザーを削除できます。

- 組織内のユーザーを削除するには、少なくともユーザー管理者ロールの割り当てが必要です。
- 特権認証管理者ロールを持つユーザーは、他の管理者を含むすべてのユーザーを削除できます。
- ユーザー管理者は、管理者以外のユーザー、ヘルプデスク管理者、その他のユーザー管理者を削除できます。
- 詳細については、[Microsoft Entra ID での管理者ロールのアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関する記事を参照してください。

ユーザーを削除するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 削除するユーザーを検索して選択します。
4. [ **ユーザーの削除] を選択します**。

    [Image: ユーザーが選択され、[削除] ボタンが強調表示されている [すべてのユーザー] ページのスクリーンショット。]

ユーザーは削除され、[ **すべてのユーザー** ] ページに表示されなくなります。 ユーザーは、次の 30 日間、[ **削除されたユーザー** ] ページに表示され、その間に復元できます。 ユーザーの復元の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-restore) を使用して最近削除されたユーザーを復元または削除する」を参照してください。

ユーザーが削除されると、そのユーザーによって使用されていたライセンスは、他のユーザーが使用できるようになります。

Note

権限ソースが Windows Server Active Directory であるユーザーの ID、連絡先情報、または仕事情報を更新するには、Windows Server Active Directory を使用する必要があります。 更新を完了した後、次の同期サイクルが完了するのを待ってから変更を確認する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/how-to-customize-branding"} -->
## 組織のサインイン ページに会社のブランドを追加する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-customize-branding
- Service: entra / fundamentals
- Article date: 2026-08-18
- Summary: Microsoft Entra サインイン エクスペリエンスに組織のカスタム ブランド化を追加する方法について説明します。

ユーザーが企業イントラネットまたは Web ベースのアプリケーションに対して認証を行うと、Microsoft Entra ID によって ID およびアクセス管理 (IAM) サービスが提供されます。 これらすべてのエクスペリエンスに適用される会社のブランド化を追加して、ユーザーに一貫したサインイン エクスペリエンスを作成できます。

既定のサインイン エクスペリエンスは、テナントへのすべてのサインインに適用されるグローバルな外観です。 設定をカスタマイズする前に、既定の Microsoft ブランド化がサインイン ページに表示されます。 この既定のエクスペリエンスは、カスタムの背景画像または色、ファビコン、レイアウト、ヘッダー、フッターでカスタマイズできます。 カスタム CSS ファイルをアップロードすることもできます。

### 前提条件

カスタム ブランドを追加するには、次のいずれかのライセンスが必要です。

- [Microsoft Entra ID P1 または P2](https://www.microsoft.com/security/business/microsoft-entra-pricing)
- [Microsoft 365 Business Standard](https://www.microsoft.com/microsoft-365/business)
- [SharePoint (プラン 1)](https://www.microsoft.com/microsoft-365/sharepoint/compare-sharepoint-plans)

Microsoft Entra ID のワールドワイド インスタンスを使用している中国のお客様が、Microsoft Entra ID P1 または P2 エディションを使用できます。 中国の 21Vianet が運営する Azure サービスでは、Microsoft Entra ID P1 または P2 エディションは現在サポートされていません。

**組織ブランド管理者**ロールは、会社のブランドをカスタマイズするために必要な最小限のロールです。

### 開始する前に

**すべてのブランド要素は省略可能です。 既定の設定は、変更されていない場合は保持されます。** たとえば、バナー ロゴを指定し、背景画像を指定しなかった場合、サインイン ページには、そのロゴと、接続先のサイト (Microsoft 365 など) の既定の背景画像が表示されます。 さらに、サインイン ページのブランド情報は、個人用 Microsoft アカウントに継承されることはありません。 ユーザーまたはゲストが個人用 Microsoft アカウントを使用して認証した場合、サインイン ページには組織のブランド情報は反映されません。

**画像の要件とそのファイル サイズの要件はさまざまです。** Microsoft Entra 管理センターで会社のブランド化プロセスを確認し、必要な画像要件を収集することをお勧めします。 場合により、フォト エディターを使用して適切なサイズの画像を作成する必要があります。 すべての画像に推奨される画像タイプは PNG ですが、JPG も使用できます。

**サインイン エクスペリエンスでは、外部 URL はサポートされていません。** たとえば、内部ヘルプ デスクの外部 URL をフッターに追加すると、その URL は明示的に表示されますが、クリックすることはできません。 ユーザーは URL をコピーして直接移動する必要があります。

**Azure Active Directory B2C (Azure AD B2C) 企業のブランド化オプションは異なります。** 現在、Azure AD B2C のブランド化は、背景画像、バナー ロゴ、背景色のカスタマイズに限定されています。 詳細については、Azure AD B2C ドキュメントの [UI のカスタマイズ](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/customize-ui?pivots=b2c-user-flow#configure-company-branding) に関するページを参照してください。

重要

2025 年 5 月 1 日より、Azure Active Directory B2C (Azure AD B2C) は新規のお客様が購入できなくなります。 詳細については、FAQ の [「Azure AD B2C を引き続き購入できますか?」を](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale) 参照してください。

**Microsoft Entra の会社のブランド化で Microsoft Graph を使用します。** 会社のブランド化は、`/beta` エンドポイントと `organizationalBranding` リソースの種類で Microsoft Graph を使用して表示および管理できます。 詳細については、 [組織のブランド化 API のドキュメントを参照してください](https://learn.microsoft.com/ja-jp/graph/api/resources/organizationalbranding?view=graph-rest-beta&preserve-view=true)。

ブランド要素を、次の例に表示します。 テキストの説明は、画像の後に示しています。

[Image: 各会社のブランド化要素が強調表示されているサインイン ページのスクリーンショット。]

1. **Favicon**: ブラウザー タブの左側に表示される小さいアイコン。
2. **ヘッダー**: サインイン ページの上部にあるヘッダー ロゴの背後にあるスペース。
3. **ヘッダー ロゴ**: サインイン ページの左上隅に表示されるロゴ。
4. **背景画像**: サインイン ボックスの背後にあるスペース全体。
5. **ページの背景色**: サインイン ボックスの背後にあるスペース全体。
6. **バナー ロゴ**: サインイン ボックスの上部に表示されるロゴ
7. **サインイン ページのタイトル**: バナー ロゴの下に表示される大きなテキスト。
8. **サインイン ページの説明**: サインイン ページを説明するテキスト。
9. **ユーザー名のヒントとテキスト**: ユーザーが情報を入力する前に表示されるテキスト。
10. **セルフサービス パスワード リセット: パスワード リセット**のサインイン ページ テキストの下に追加できるリンク。
11. **サインイン ページのテキスト**: ユーザー名フィールドの下に追加できるテキスト。
12. **フッター リンク: プライバシーと Cookie**: プライバシー情報の右下隅に追加できるリンク。
13. **フッター: 利用規約**: 使用条件情報を追加できるページの右下隅のテキスト。
14. **フッター**: ページの下部にプライバシーと利用規約の情報を表示するスペース。
15. **テンプレート**: ページとサインイン ボックスのレイアウト。

### 会社のブランド化プロセスを操作する方法

1. [Microsoft Entra 管理センター](https://entra.microsoft.com) に [Organizational Branding Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#organizational-branding-administrator) としてサインインします。
2. **Entra ID**&gt;**Custom Branding** に移動します。

    - 現在、カスタマイズされたサインイン エクスペリエンスがある場合は、[ **編集]** ボタンを使用できます。

    [Image: サイド メニューと [構成] ボタンで [会社のブランド] が強調表示された [カスタム ブランド] ランディング ページのスクリーンショット。]

サインイン エクスペリエンス プロセスは、セクションにグループ化されます。 各セクションの最後で、[ **確認と作成** ] ボタンを選択して選択した内容を確認し、変更を送信するか、[ **次へ** ] ボタンをクリックして次のセクションに移動します。

[Image: [確認と作成] と [次へ: カスタム ブランドの構成] ページの下部にある [レイアウト] ボタンのスクリーンショット。]

#### 基本事項

- **Favicon**: Web ブラウザー タブに表示されるロゴの PNG または JPG を選択します。

    - 画像サイズ: 32 x 32 px
    - 最大ファイル サイズ: 5 KB

    [Image: Web ブラウザーのサンプル favicons のスクリーンショット。]
- **背景画像**: PNG または JPG を選択して、サインイン ページにメインイメージとして表示します。 この画像は、ウィンドウ サイズに応じて拡大縮小およびトリミングされますが、サインイン プロンプトによって部分的にブロックされる可能性があります。

    - 画像サイズ: 1920 x 1080 px
    - 最大ファイル サイズ: 300 KB
- **ページの背景色**: 接続が遅いために背景画像を読み込むことができない場合は、選択した背景色が代わりに表示されます。

#### レイアウト

- **ビジュアル テンプレート**: テンプレートまたはカスタム CSS ファイルを使用して、サインイン ページのレイアウトをカスタマイズします。

    - 全画面または部分的に表示される背景のテンプレートの 2 つのいずれかを選択します。 全画面表示の背景は背景画像を隠す可能性があるため、背景画像が重要な場合は、部分的な画面の背景を選択します。
    - **ヘッダー**と**フッター**のオプションの詳細は、プロセスの次の 2 つのセクションで設定されます。

    [Image: ブランドをカスタマイズするための [レイアウト] タブのスクリーンショット。]
- **カスタム CSS**: カスタム CSS ファイルをアップロードして、ページの Microsoft の既定のスタイルを置き換えます。

    - [CSS テンプレートをダウンロードします](https://download.microsoft.com/download/7/2/7/727f287a-125d-4368-a673-a785907ac5ab/custom-styles-template-013023.css)。
    - [CSS テンプレートのリファレンス ガイド](https://learn.microsoft.com/ja-jp/entra/fundamentals/reference-company-branding-css-template)を参照してください。

    重要

    2026 年 1 月 5 日以降に作成されたテナントには、Microsoft Entra IDの会社のブランド化に使用できるカスタム CSS がありません。 2026 年 7 月 21 日以降、カスタム CSS をまだ使用していない 2026 年 1 月 6 日より前に作成されたテナントは、カスタム CSS を構成できません。

    [Microsoft Secure Future Initiative](https://www.microsoft.com/trust-center/security/secure-future-initiative) と ID のセキュリティとフィッシングに対する耐性に重点を置くために、Microsoft Entra IDはカスタム CSS *レイアウトと配置プロパティ* (`position`、`margin`、`transform`、`overflow`など) のサポートを廃止しています。 後で、プロパティはグローバルに非推奨になり、機能を停止します。 最終的に、カスタム CSS は完全に廃止されます。 カスタム CSS でこれらのプロパティが使用されている場合は、構成から削除します。 非推奨のプロパティの完全な一覧と CSS を更新する手順については、 [CSS テンプレートリファレンスガイドを参照](https://learn.microsoft.com/ja-jp/entra/fundamentals/reference-company-branding-css-template#deprecation-of-custom-css-positioning-properties)してください。 詳細については、[ブランド化されたサインインのセキュリティを強化Microsoft Entra ID](https://techcommunity.microsoft.com/blog/microsoft-entra-blog/microsoft-entra-id-enhances-security-of-branded-sign-ins/4537471)ブログ記事を参照してください。

#### ヘッダー

ヘッダーを有効にしていない場合は、[ **レイアウト** ] セクションに移動し、[ **ヘッダーの表示**] を選択します。 有効にしたら、サインイン ページのヘッダーに表示する PNG または JPG を選択します。

- 画像サイズ: 245 x 36 ピクセル
- 最大ファイル サイズ: 10 KB

[Image: ヘッダーを有効にする必要があることを示すメッセージのスクリーンショット。]

#### フッター

フッターを有効にしていない場合は、[ **レイアウト** ] セクションに移動し、[ **フッターの表示**] を選択します。 有効にしたら、次の設定を調整します。

- **['プライバシーと Cookie'] を表示**: このオプションは既定で選択されており、[Microsoft の \['プライバシーと Cookie'\] リンク](https://privacy.microsoft.com/privacystatement)が表示されます。

    - 既定の Microsoft リンクを非表示にするには、このオプションをオフにします。
    - 必要に応じて、独自の **表示テキスト** と **URL を指定します**。 テキストとリンクは、プライバシーと Cookie に関連している必要はありません。
    - カスタム URL はテキストとして表示され、クリックできません。
- **[使用条件] を表示**する: このオプションは既定でも選択され、[Microsoft \[使用条件\] リンクが](https://www.microsoft.com/servicesagreement/)表示されます。

    - 既定の Microsoft リンクを非表示にするには、このオプションをオフにします。 必要に応じて、独自の **表示テキスト** と **URL を指定します**。
    - テキストとリンクは、使用条件に関連している必要はありません。

        重要

        既定の Microsoft の [使用条件] リンクは、条件付きアクセスの使用条件と同じではありません。 ここで使用条件を確認しても、これらの使用条件に同意したわけではありません。

    [Image: [フッター] セクションのブランディングをカスタマイズするスクリーンショット。]

#### サインイン フォーム

- **バナー ロゴ**: サインイン ページに表示するバナー サイズのロゴ (短くて幅が広い) の PNG または JPG イメージ ファイルを選択します。

    - 画像サイズ: 245 x 36 ピクセル
    - 最大ファイル サイズ: 50 KB
- **正方形のロゴ (淡色テーマ)**: 淡色テーマを使用しているブラウザーで使用するロゴの正方形の PNG または JPG イメージ ファイルを選択します。 このロゴは、Microsoft Entra Web インターフェイスと Windows で組織を表すために使用されます。

    - 画像サイズ: 240 x 240 px
    - 最大ファイル サイズ: 50 KB
- **正方形のロゴ (ダーク テーマ):濃色テーマ**を使用しているブラウザーで使用するロゴの正方形の PNG または JPG イメージ ファイルを選択します。 このロゴは、Microsoft Entra Web インターフェイスと Windows で組織を表すために使用されます。 ロゴが明るい背景および暗い背景に適している場合、暗いテーマのロゴを追加する必要はありません。

    - 画像サイズ: 240 x 240 px
    - 最大ファイル サイズ: 50 KB
- **ユーザー名ヒント テキスト**: サインイン ページのユーザー名入力フィールドにヒント テキストを入力します。 ゲストが同じサインイン ページを使用する場合、ここではヒント テキストを使用しないことをお勧めします。
- **サインイン ページのテキスト**: サインイン ページの下部に表示されるテキストを入力します。 このテキストを使用して、ヘルプ デスクの電話番号や法的声明などの追加情報を伝達できます。 このページは公開用であるため、ここでは機密情報を提供しないでください。 このテキストは Unicode にする必要があり、1,024 文字以下にする必要があります。

    新しい段落を開始するには、Enter キーを 2 回押します。 また、テキストの書式設定を変更して、太字、斜体、下線、またはクリック可能なリンクを含めることもできます。 テキストに書式設定を追加するには、次の構文を使用します。

    - ハイパーリンク: `[text](https://learn.microsoft.com/ja-jp/entra/fundamentals/link)`
    - 太字: `**text**` または `__text__`
    - 斜体: `*text*` または `_text_`
    - 下線: `++text++`

    重要

    サインイン ページのテキストに追加されたハイパーリンクは、デスクトップ アプリケーションやモバイル アプリケーションなどのネイティブ環境では、テキストとしてレンダリングされます。
- **セルフサービス パスワード リセット**:

    - セルフサービス パスワード リセット (SSPR) を表示する: SSPR を有効にするチェック ボックスをオンにします。
    - 共通 URL: ユーザーがパスワードをリセットする接続先 URL を入力します。 この URL は、ユーザー名とパスワードの収集画面にテキストとして表示され、クリックできません。
    - ユーザー名コレクションの表示テキスト: 既定のテキストを独自のカスタム ユーザー名コレクション テキストに置き換えます。
    - パスワード コレクションの表示テキスト: 既定のテキストを独自のカスタム パスワード コレクション テキストに置き換えます。

#### 確認

使用可能なすべてのオプションが 1 つのリストに表示されるため、カスタマイズした内容や既定の設定のままにした内容を確認できます。 完了したら、[ **作成** ] ボタンを選択します。

既定のサインイン エクスペリエンスが作成されたら、[ **編集** ] ボタンを選択して変更を加えます。 作成後に既定のサインイン エクスペリエンスを削除することはできませんが、すべてのカスタム設定を削除することはできます。

サインイン エクスペリエンスに変更が表示されるまでにかかる時間は、テナントの地理的な場所によって異なります。

### ブラウザー言語でサインイン エクスペリエンスをカスタマイズする

特定のブラウザー言語に対してブランド化要素をカスタマイズすることで、そのブラウザー言語を使用してサインインするユーザー向けにパーソナライズされたサインイン エクスペリエンスを作成できます。 このカスタマイズは、既定のブランド化に対して行われたすべての構成をオーバーライドします。 要素に変更を加えない場合は、既定の要素が表示されます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com) に [Organizational Branding Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#organizational-branding-administrator) としてサインインします。
2. **Entra ID**&gt;**Custom Branding** に移動します。
3. [ **ブラウザー言語の追加] を選択します**。

エクスペリエンスをカスタマイズするプロセスは 、既定のサインイン エクスペリエンス プロセスと同じですが、[ **基本** ] セクションのドロップダウン リストから言語を選択する必要があります。 既定のサインイン エクスペリエンスと同じ領域にカスタム テキストを追加することをお勧めします。

Microsoft Entra ID では、右から左に読むアラビア語やヘブライ語などの言語に対して右から左への機能がサポートされています。 レイアウトは、ユーザーのブラウザー設定に基づいて自動的に調整されます。

[Image: ヘブライ語でのサインイン エクスペリエンスのスクリーンショット。右から左へのレイアウトが示されています。]

### ユーザー エクスペリエンス

組織のテナント固有のアプリケーションで、サインイン ページをカスタマイズ場合には、考慮すべきシナリオがいくつかあります。

#### 既定の背景画像

サインイン ボックスの背後にある既定の背景画像は、今年後半に変更されます。 変更はイメージに対してのみ行われ、アクションは必要なく、機能には影響しません。 既定の背景画像は、サインイン エクスペリエンスを示すためにトレーニングとドキュメントに使用されることが多いことがわかります。 更新されたイメージを提供すると、ドキュメントを更新して、ユーザーに表示される正確なサインイン エクスペリエンスを示すことができます。 今後の変更の詳細については、 [Microsoft Entra のリリースとお知らせ](https://learn.microsoft.com/ja-jp/entra/fundamentals/whats-new)を参照してください。

[Image: 左側の現在の背景と右側の新しい画像のスクリーンショット。]

現在の背景画像は左側にあり、新しい背景画像は右側にあります。

#### サービスとしてのソフトウェア (SaaS) とマルチテナント アプリケーション

Microsoft、サービスとしてのソフトウェア (SaaS)、および https://myapps.microsoft.comや https://outlook.comなどのマルチテナント アプリケーションの場合、カスタマイズされたサインイン ページは、ユーザーが **電子メール** または **電話番号** を入力し、[ **次へ** ] ボタンを選択した後にのみ表示されます。

#### ホーム領域検出

一部の Microsoft アプリケーションでは、認証用 [のホーム領域検出](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/home-realm-discovery-policy) がサポートされています。 これらのシナリオでは、顧客が Microsoft Entra の一般的なサインイン ページにサインインすると、Microsoft Entra ID では顧客のユーザー名を使用して、サインインする場所を判別できます。

カスタム URL、`whr` クエリの文字列パラメーター、またはドメイン変数からアプリケーションにアクセスする顧客の場合には、メールアドレスまたは電話番号を追加した後だけでなく、初期サインイン画面で会社のブランド化が適用できます。 たとえば、`whr=contoso.com` がアプリのカスタム URL に表示されます。 ホーム領域検出とドメインのパラメーターが含まれていると、最初のサインイン手順で会社のブランド化がすぐに表示されます。 その他のドメイン ヒントを含めることができます。

次の例では、contoso.com を独自のテナント名または検証済みドメイン名に置き換えてください。

- Microsoft Outlook の場合 `https://outlook.com/contoso.com`
- Microsoft 365 の SharePoint の場合 `https://contoso.sharepoint.com`
- マイ アプリ ポータルの場合 `https://myapps.microsoft.com/?whr=contoso.com`
- セルフサービス パスワード リセット`https://passwordreset.microsoftonline.com/?whr=contoso.com`

#### B2B シナリオ

テナント間サインインを実行する B2B コラボレーション エンド ユーザーの場合、カスタム ブランド化が指定されていない場合でも、ホーム テナントのブランド化が表示されます。

次の例では、Woodgrove Groceries の会社のブランド化が、Woodgrove のロゴ、フォント、カスタム テキストで左側に表示されています。 右側の例では、ユーザーのホーム テナントの既定のブランド化が表示されます。 既定のブランド化では、Microsoft のロゴ、フォント、テキストが表示されます。

[Image: ブランド化されたサインイン エクスペリエンスと既定のサインイン エクスペリエンスの比較のスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/how-to-customize-branding-themes-apps"} -->
## ブランド化テーマを使用してアプリケーションのサインイン エクスペリエンスをカスタマイズする - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-customize-branding-themes-apps
- Service: entra / fundamentals
- Article date: 2026-08-18
- Summary: ブランド化テーマを作成し、Microsoft Entra IDでアプリケーションのサインイン エクスペリエンスに適用する方法について説明します。

テナント内のアプリケーションに固有の認証エクスペリエンスを作成できます。 各アプリケーションには、背景画像または色、favicon、レイアウト、ヘッダー、フッターを使用してカスタマイズできる独自のテーマを設定できます。 このカスタマイズは、既定のブランド化に対して行われたすべての構成をオーバーライドします。 要素に変更を加えない場合は、既定の要素が表示されます。

この記事では、テナント内の異なるアプリケーションに対して複数のブランド化テーマを作成する方法について説明します。

Important

外部テナントの場合、ブランド化テーマは一般提供されています。 Microsoft Entra ID テナントの場合、ブランド化テーマはプレビュー段階です。 この情報は、リリース前に大幅に変更される可能性があるプレリリース製品に関連しています。 Microsoft は、ここに記載されている情報に関して、明示または黙示を問わず、一切の保証を行いません。

### 前提条件

- 外部テナントの場合、ライセンス要件はありません。 Microsoft Entra ID テナントの場合は、[Microsoft Entra ID P1 または P2 ライセンス](https://www.microsoft.com/security/business/microsoft-entra-pricing)が必要です。
- 少なくとも [組織のブランド管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#organizational-branding-administrator) ロールを持っている。
- テーマを適用する [アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) のアプリケーション管理者ロールを少なくとも持っている。
- テナントに登録されているアプリケーション。 アプリケーションをまだ登録していない場合は、「アプリケーションの登録」 [を](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)参照してください。
- 追加する各イメージのファイル サイズ要件を確認します。 画像サイズが 245 x 36px、最大ファイル サイズが 10 KB の PNG、JPG、JPEG など、適切なサイズと書式設定された画像を作成する場合は、フォト エディターを使用します。

### ブランドテーマのプロパティ

ブランド化テーマを作成するときに、カスタマイズできるプロパティの一部を次に示します。

[Image: 既定の各ブランド化要素が強調表示されているサインイン ページのスクリーンショット。]

| 財産 | 説明 |
| --- | --- |
| ファビコン | ブラウザー タブの左側に表示される小さなアイコン。 |
| Header | サインイン ページ上部全体にわたる、ヘッダー ロゴの背後にある領域。 |
| ヘッダーロゴ | サインイン ページの左上隅に表示されるロゴ。 |
| 背景の画像 | サインイン ボックスの背後にあるスペース全体。 |
| ページの背景色 | サインイン ボックスの背後にあるスペース全体。 |
| バナー ロゴ | サインイン ボックスの上部に表示されるロゴ |
| サインイン ページのタイトル | バナー ロゴの下に表示される大きなテキスト。 |
| サインイン ページの説明 | サインイン ページを説明するテキスト。 |
| ユーザー名のヒントとテキスト | ユーザーが情報を入力する前に表示されるテキスト。 |
| サインイン表示メッセージ ボックス | ユーザー名フィールドの下に追加できるテキスト。 |
| フッター リンク: プライバシーと Cookie | プライバシー情報の右下隅に追加できるリンク。 |
| フッター: 使用条件 | 使用条件情報を追加できるページの右下隅のテキスト。 |
| フッター | ページの下部にあるプライバシーと利用規約の情報用のスペース。 |
| Template | ページとサインイン ボックスのレイアウト。 |

### ブランド化テーマのしくみ

ブランド化テーマは、ニュートラル ブランドと既定のブランド化に基づいて構築されます。

- **ブランドテーマ** - 複数のテーマを設定できる既定のブランドのカスタマイズ。
- **既定のブランド (会社のブランド)** - テナントのニュートラル ブランドのカスタマイズ。
- **ニュートラル ブランド** - テナントの初期ブランド化。

ブランド化テーマのしくみについて知っておくべき重要な点を次に示します。

- ブランド化テーマは特定のアプリケーションに適用できますが、既定のブランド化はテナント全体に適用されます。
- 既定のブランド化は、ブランド化テーマで定義されていないプロパティのフォールバックとして使用されます。
- ニュートラル ブランドは、既定のブランド化で定義されていないプロパティに使用されます。

### 制限および制約

ブランド化テーマの制限と制約の一部を次に示します。

- テナントごとに最大 5 つのブランドテーマを作成できます。
- ライブ プレビュー機能では、スタイルとレイアウトの変更がプレビューされ、[サインイン] ページのみが表示されます。 ライブ プレビューには、カスタム テキストのオーバーライドは含まれません。
- ブランド テーマ名に **既定のテーマ** を使用することはできません。 この名前は予約されています。
- カスタム テキストの変更は現在、サインイン ページのみに制限されています。
- バナー ロゴ プロパティを更新しない場合は、テナント名 (ニュートラル ブランド) の代わりに Microsoft ロゴが表示されます。

### 新しいテーマを作成する

1. [組織のブランド化管理者](https://entra.microsoft.com/)および[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#organizational-branding-administrator)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)にサインインします。
2. **Entra ID**&gt;**カスタムブランディング**を参照します。
3. [ **会社のブランド]** ページで、[ **ブランドテーマ** ] を選択し、[ **テーマ** ] タブを選択します。

    [Image: [会社のブランド] ページと [テーマ] タブのスクリーンショット。]
4. **新しいテーマを作成する**を選択します。
5. [ **基本** ] タブで、テーマの **名前** を入力します。

    [Image: テーマをアプリケーションに適用するための [テーマの作成] ページと [基本] タブのスクリーンショット。]
6. このテーマを使用するアプリケーションを選択するには、[テーマの **適用**] で [ **アプリケーションの追加]** を選択します。 (または 、後でアプリケーションを追加することもできます)。
7. [**レイアウト**] タブで、サインイン ページ上の Web ページ要素の配置を選択します。

    - **レイアウト テンプレート** – サインイン ウィンドウをページ上で中央揃えにするか、右揃えにするかを選択します。
    - **ヘッダー** – ページ ヘッダーに画像を表示するには、チェック ボックスをオンにして、表示する画像を参照します。 要件: 画像サイズが 245 x 36px で最大ファイル サイズが 10 KB の透過 PNG、JPG、または JPEG。
    - **フッター** – 公開されたプライバシーや Cookie、使用条件ステートメントへのリンクを含むページ フッターを表示するには、適切なチェック ボックスをオンにしてリンク テキストを入力し、コンテンツの URL を追加します。

    [Image: サインイン エクスペリエンスを指定する [テーマの作成] ページと [レイアウト] タブのスクリーンショット。]
8. [ **プレビュー** ] ボタンを選択すると、レイアウトに対する変更が表示されます。

    [Image: レイアウトをプレビューする [プレビュー] ボタンのスクリーンショット。]
9. [ **スタイル]** タブで、背景要素のいずれかを変更します。

    - **背景色** – 接続の待ち時間などにより、イメージを読み込めなかったときに背景イメージを置き換える色。
    - **背景画像** – サインイン ページに表示される大きな画像。 画像をアップロードすると、画像はブラウザー ウィンドウを埋めるようにサイズが調整され、トリミングされます。
    - **Favicon** – Web ブラウザー タブに表示されるアイコン。
    - **バナー ロゴ** – サインイン ページとユーザーのアクセス パネルに表示されます。
    - **正方形のロゴ (淡色テーマ)** – テナント内のユーザー アカウントを表します。
    - **Square ロゴ (ダークテーマ)** – 明るいテーマの四角形ロゴが暗い背景で見えにくい場合には、暗い背景で使用する別のロゴをアップロードできます。
    - **カスタム CSS** – 独自の CSS ファイルをアップロードして、既定の Microsoft のスタイルを独自のスタイルに置き換えます。色、フォント、テキスト サイズ、要素の位置、さまざまなデバイスと画面サイズの表示。 詳細については、 [CSS テンプレートリファレンスガイドを参照](https://learn.microsoft.com/ja-jp/entra/fundamentals/reference-company-branding-css-template)してください。

        Important

        [Microsoft Secure Future Initiative](https://www.microsoft.com/trust-center/security/secure-future-initiative) と ID のセキュリティとフィッシングに対する耐性に重点を置くために、Microsoft Entra IDはカスタム CSS レイアウトのサポートを廃止し、会社のブランド化に*プロパティを配置*します。 2026 年 7 月 21 日以降、カスタム CSS をまだ使用していない 2026 年 1 月 6 日より前に作成されたテナントは、カスタム CSS を構成できません。 後で、プロパティはグローバルに非推奨になり、機能を停止します。 最終的に、カスタム CSS は完全に廃止されます。 非推奨のプロパティの完全な一覧と CSS を更新する手順については、 [CSS テンプレートリファレンスガイドを参照](https://learn.microsoft.com/ja-jp/entra/fundamentals/reference-company-branding-css-template#deprecation-of-custom-css-positioning-properties)してください。 詳細については、[ブランド化されたサインインのセキュリティを強化Microsoft Entra ID](https://techcommunity.microsoft.com/blog/microsoft-entra-blog/microsoft-entra-id-enhances-security-of-branded-sign-ins/4537471)ブログ記事を参照してください。

    [Image: [テーマの作成] ページと [スタイル] タブの設定のスクリーンショット。]
10. **[プレビュー**] ボタンを選択して、スタイルの変更を確認します。

    [Image: カスタマイズされたサインイン エクスペリエンスのスクリーンショット。]
11. [**カスタム テキスト**] タブ**で、[サインイン**] ページなど、いずれかのページの **[既定**] リンクを選択します。

    - **サインイン** – ユーザーがサインインするための資格情報を入力するページ。
    - **サインアップ** – ユーザーが新しいアカウントを作成するページ。
    - **属性コレクション** – サインアップまたはプロファイルの編集中にユーザーが追加情報を提供するページ。
    - **1 回限りのコード** – ユーザーが確認のためにメールまたは電話に送信された 1 回限りのコードを入力するページ。

    [Image: [テーマの作成] ページと [カスタム テキスト] タブの設定のスクリーンショット。]
12. 選択したページのテキストをカスタマイズし、[ **追加**] を選択します。

    テーマのカスタム テキスト セットはローカライズされないため、ローカライズされた UX に影響を与える可能性があります。 すべてのテキストを完全にローカライズするには、テーマ言語ごとにカスタム テキストを設定します。

    [Image: サインイン ページの [カスタム テキストの編集] ページのスクリーンショット。]

    [サインイン] ページ **で** 、[ **メッセージ ボックスの表示]** テキストをカスタマイズできます。 このテキストは、Microsoft Entra ID サインイン ページの下部と、Windows の Microsoft Entra ID Join エクスペリエンスに表示されます。 このテキストを使用して、指示やヒントを伝えます。 誰でもサインイン ページを見ることができるので、機密情報はここに置かないでください。 最大 1024 文字です。 太字、斜体、下線、クリック可能なリンクを含むテキストの書式を設定するには、次の構文を使用します。

    - 太字: `**text**` または `__text__`
    - 斜体: `*text*` または `_text_`
    - 下線: `++text++`
    - ハイパーリンク: `[text](https://learn.microsoft.com/ja-jp/entra/fundamentals/link)`
13. [ **校閲** ] タブで、設定を確認します。
14. [ **作成]** を選択してテーマを作成します。

### アプリケーションにテーマを適用する

このセクションでは、テナント内のアプリケーションにテーマを適用します。

1. [ **ブランドテーマ** ] ページで、[ **テーマ** ] タブを選択します。
2. 作成したテーマを選択して概要を開きます。
3. [ **基本**] で、鉛筆の編集アイコンを選択します。

    [Image: テーマの編集ページの [概要] タブのスクリーンショット。]
4. [ **テーマの適用]** で、[ **編集]** を選択してアプリケーションの一覧を編集します。

    [Image: アプリケーションの一覧を編集するための [基本の編集] ページのスクリーンショット。]
5. アプリケーションを探すか、閲覧してください。 チェック ボックスを選択し、[選択] を **選択**します。

    [Image: アプリケーションを選択する [アプリケーションの追加] ページのスクリーンショット。]

### テーマの編集

このセクションでは、テーマを編集します。

1. [ **ブランドテーマ** ] ページで、[ **テーマ** ] タブを選択します。
2. 作成したテーマを選択して概要を開きます。
3. 編集するパーツの鉛筆編集アイコンを選択します。

### テーマに言語を追加する

このセクションでは、テーマに言語を追加します。

1. [ **ブランドテーマ** ] ページで、[ **テーマ** ] タブを選択します。
2. 作成したテーマを選択して概要を開きます。
3. [言語] タブ **で** 、[ **言語の追加** ] を選択してテーマの言語をカスタマイズします。

    言語をカスタマイズする手順は、ブランド化テーマのカスタマイズと似ています。

    [Image: 言語を追加する [言語] タブのスクリーンショット。]
4. [ **基本** ] タブで、言語を選択します。

    [Image: 言語を選択するための [言語の追加] ページと [基本] タブのスクリーンショット。]

    次の言語がサポートされています。

    - アラビア語 (サウジアラビア)
    - バスク語 (バスク)
    - ブルガリア語 (ブルガリア)
    - カタルニア語 (カタルニア)
    - 中国語 (中国)
    - 中国語 (香港特別行政区)
    - クロアチア語 (クロアチア)
    - チェコ語 (チェコ)
    - デンマーク語 (デンマーク)
    - オランダ語 (オランダ)
    - 英語 (米国)
    - エストニア語 (エストニア)
    - フィンランド語 (フィンランド)
    - フランス語 (フランス)
    - ガリシア語 (ガリシア)
    - ドイツ語 (ドイツ)
    - ギリシャ語 (ギリシャ)
    - ヘブライ語 (イスラエル)
    - ハンガリー語 (ハンガリー)
    - イタリア語 (イタリア)
    - 日本語 (日本)
    - カザフ語 (カザフスタン)
    - 韓国語 (韓国)
    - ラトビア語 (ラトビア)
    - リトアニア語 (リトアニア)
    - ノルウェー語 (ブークモール) (ノルウェー)
    - ポーランド語 (ポーランド)
    - ポルトガル語 (ブラジル)
    - ポルトガル語 (ポルトガル)
    - ルーマニア語 (ルーマニア)
    - ロシア語 (ロシア)
    - セルビア語 (ラテン、セルビア)
    - スロバキア語 (スロバキア)
    - スロベニア語 (スロベニア)
    - スペイン語 (スペイン)
    - スウェーデン語 (スウェーデン)
    - タイ語 (タイ)
    - トルコ語 (Türkiye)
    - ウクライナ語 (ウクライナ)
5. **[レイアウト**]、[**スタイル]**、[**ユーザー設定] の各テキスト** タブの要素をカスタマイズします。
6. [ **校閲** ] タブで、[ **追加** ] を選択して言語を追加します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/how-to-entra-self-service-support"} -->
## Microsoft Entra セルフサービス サポート - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-entra-self-service-support
- Service: entra / fundamentals
- Article date: 2026-03-09
- Summary: Microsoft Entra Self-Service サポートが AI とMicrosoft Graphデータを使用して製品ログを分析し、問題を解決し、IT 管理者ワークフローを強化する方法について説明します。

Microsoft Entra Self-Service サポートは、IT 管理者が ID とアクセスの問題のトラブルシューティングを行い、製品に関する質問に対する回答を得ることを可能にする、AI 主導の会話型サポート エクスペリエンスです。 この機能では、 https://learn.microsoft.com の Microsoft パブリック ドキュメントをナレッジ ベースとして使用します。

Self-Service サポートは、Microsoft Graph APIでアクセスされた製品ログを分析して、問題の根本原因を特定し、関連する解決ガイダンスを提供します。 アシスタントは、Microsoft Entraの顧客に無償で提供されます。 この記事では、Self-Service サポートの使用方法について説明します。

### 主要な機能

Self-Service サポートは、Microsoft Entra管理センターのユーザーが自然言語プロンプトを使用して対話できる会話型チャット インターフェイスを提供します。 チャット エクスペリエンスは、[ **問題の診断と解決** ] ページから開始できます。

Self-Service サポートでは、https://learn.microsoft.com のMicrosoft Graphデータとパブリック ドキュメントをナレッジ ベースとして使用して、障害を分析し、ガイド付きトラブルシューティングを通じて段階的な解決策を提案します。 Self-Service サポートとやり取りするときに、このナレッジ ベースを使用してコンテキスト、説明、ガイダンスを提供します。

問題が解決しない場合は、Microsoft サポートにシームレスにエスカレートできます。 アシスタントにはフィードバック ループが含まれており、評価とコメントを送信して、応答の品質を向上させることができます。

### 前提条件

Self-Service サポートは、 [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator) や [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)などのサポート チケットを作成および管理するためのアクセス許可を持つユーザーが利用できます。

タスクごとの最小特権ロールについては、ロールの完全な一覧を参照してください。

### Microsoft Entra サポート アシスタントを使用する方法

1. Microsoft Entra管理センターに少なくとも [Reports 閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **[問題の診断と解決]** に移動します。
3. **セルフサービスサポートを選択します**。

    [Image: Microsoft Entra サポート アシスタント インターフェイスのスクリーンショット。]
4. 事前構築済みのプロンプトのいずれかを選択するか、テキスト ボックスに自然言語の質問を入力します。
5. 一部の応答では、より多くの質問に回答したり、トラブルシューティングする特定のイベントを選択したりするオプションが提供されます。 さらにトラブルシューティングを行うオプションを選択します。

    [Image: イベント オプション ボタンが強調表示されたMicrosoft Entra サポート アシスタントのスクリーンショット。]
6. 引き続き説明を提供したり、トラブルシューティングのために特定のイベントを選択したりすると、Self-Service サポートは Microsoft のパブリック ドキュメントを使用して、コンテキスト、説明、ガイダンスを提供します。 適切なオプションを選択するか、より自然言語のプロンプトを入力して続行します。
7. 最初の質問の後、フォローアップの質問をしてトラブルシューティング プロセスを調整できます。 3 番目の回答 (2 つのフォローアップの質問の後) では、サポート要求を作成するオプションがウィンドウの上部に表示されます。

    [Image: サポートリクエストの作成オプションが強調表示されたMicrosoft Entraサポート アシスタントのスクリーンショット。]

#### 特別な考慮事項

Microsoft Entra サポート アシスタントを使用するときは、次の詳細に注意してください。

- Self-Service サポートの会話フローは動的で柔軟であるため、各エクスペリエンスは若干異なる場合があります。
- ウィンドウの上部にある **[新しいチャット** ] を選択して会話を更新します。
- 現時点では、Self-Service サポートでは、次の種類のシナリオに対する ID 関連のトラブルシューティングが提供されています。
    - 認証と多要素認証の失敗
    - デバイスの登録と同期に関する問題
    - Microsoft Entra Connect プロビジョニング エラー
    - 条件付きアクセスの構成ミス
    - アプリケーション SSO エラー
- Self-Service サポートはテナントでアクションを実行できません。ガイダンスのみが提供されます。
- ウィンドウの上部にある [ **従来のエクスペリエンスに切り替える** ] を選択して、AI 以外のレガシ検索エクスペリエンスに切り替えます。

### フィードバックの提供

各アシスタントの応答には、評価、コメント、または提案に関するフィードバック プロンプトが含まれています。 "thumbs up" ボタンまたは "thumbs down" ボタンを使用して、応答に関するフィードバックを提供します。 このフィードバックは重要であり、Self-Service サポートの精度を向上させるために使用されます。

Self-Service サポートは間違いを犯したり、不完全な情報を提供したりする可能性があります。 重要なアクションを常に確認し、必要に応じて Microsoft の公式ドキュメントを参照してください。 ​
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/how-to-find-tenant"} -->
## テナント ID を検索する方法 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-find-tenant
- Service: entra / fundamentals
- Article date: 2025-01-14
- Summary: 既存の Azure サブスクリプションの Microsoft Entra テナント ID を検索する方法について説明します。

### 概要

Azure サブスクリプションには、Microsoft Entra ID との信頼関係があります。 Microsoft Entra ID は、サブスクリプションのユーザー、サービス、およびデバイスを認証するために信頼されます。 各サブスクリプションにはテナント ID が関連付けられています。サブスクリプションのテナント ID を検索する方法はいくつかあります。

### Microsoft Entra 管理センターからテナント ID を検索する

次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[グローバル 閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader)としてサインインします。
2. **Entra ID**&gt;**Overview**&gt;**Properties** に移動します。

    [Image: Microsoft Entra ID - ID プロパティの概要のスクリーンショット。]
3. [ **テナント ID** ] セクションまで下にスクロールすると、ボックスにテナント ID が表示されます。

### Azure portal を使用してテナント ID を検索する

次の手順に従います。

1. [Azure portal](https://portal.azure.com) にサインインします。
2. **Microsoft Entra ID**&gt;**Properties** に移動します。
3. [ **テナント ID** ] セクションまで下にスクロールすると、ボックスにテナント ID が表示されます。

    [Image: Microsoft Entra ID - プロパティ - テナント ID - テナント ID フィールドのスクリーンショット。]

### PowerShell を使用してテナント ID を検索する

Azure PowerShell でテナント ID を検索するには、コマンドレット `Get-AzTenant`を使用します。

```azurepowershell
Connect-AzAccount
Get-AzTenant
```

詳細については、 [Get-AzTenant](https://learn.microsoft.com/ja-jp/powershell/module/az.accounts/get-aztenant) コマンドレットリファレンスを参照してください。

### CLI を使用してテナント ID を検索する

[Azure CLI](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli) または [Microsoft 365 CLI](https://github.com/pnp/cli-microsoft365) を使用してテナント ID を検索します。

Azure CLI の場合は、 `az login`、 `az account list`、または `az account tenant list`のいずれかのコマンドを使用します。 以下に含まれるすべてのコマンドは、各サブスクリプションの `tenantId` プロパティを返します。

```azurecli
az login
az account list
az account tenant list
```

詳細については、 [az login](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-login) コマンド リファレンス、 [az account](https://learn.microsoft.com/ja-jp/cli/azure/account) コマンド リファレンス、または [az account tenant](https://learn.microsoft.com/ja-jp/cli/azure/account/tenant) コマンド リファレンスを参照してください。

Microsoft 365 CLI の場合は、次の例に示すようにコマンドレット `tenant id` を使用します。

```cli
m365 tenant id get
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/how-to-get-support"} -->
## ヘルプを見つけて、Microsoft Entraのサポートを受ける - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-get-support
- Service: entra / fundamentals
- Article date: 2026-03-09
- Summary: ヘルプを取得する方法、コミュニティ サポートを検索する方法、Microsoft Entraのサポート 要求を開く方法に関する手順。

Microsoft のドキュメントと学習コンテンツは、品質のサポートとトラブルシューティングの情報を提供しますが、Microsoft のコンテンツで説明されていない問題がある場合は、Microsoft Entraのヘルプとサポートを受けるためにいくつかのオプションがあります。

この記事では、Microsoft コミュニティでサポートを得る方法と、Microsoft にサポート リクエストを送信する方法を説明します。

### Microsoft コミュニティで質問する

まずは、Microsoft コミュニティで質問してみてください。メンバーから回答を得られる場合があります。 これらのコミュニティでは、Microsoft の製品やサービスに関するサポートやフィードバックが得られるほか、一般的なディスカッションが行われています。 サポート リクエストを作成する前に、以下のリソースで回答や情報をご確認ください。

- it プロフェッショナルと開発者向けのハウツー情報、クイック スタート、コード サンプルについては、learn.microsoft.com の [テクニカル ドキュメント](https://learn.microsoft.com/ja-jp/entra/)を参照してください。
- 質問を投稿してMicrosoft Q&A Microsoft のエンジニア、Most Valuable Professional (MVP)、およびエキスパート コミュニティの他のメンバーから直接、ID に対する回答を取得し、質問にアクセスできます。
- [Microsoft テクニカル コミュニティ](https://techcommunity.microsoft.com/)の他の顧客や IT Pro パートナーとの共同作業、共有、学習。 コミュニティに参加して質問を投稿したり、自分の意見を送信してください。 お知らせ、ブログの投稿、エキスパートとの ask-me-anything (AMA) の対話などを通じて、最新情報を把握しておきましょう。
- [Azure Developer Program](https://developer.microsoft.com/azure) を使用して、完全に事前プロビジョニングされたサンドボックス サブスクリプションで独自の管理者およびプロトタイプ アプリとソリューションを作成します。

#### Microsoft Q&Aのベストプラクティス

Microsoft Q&A は、コミュニティ サポートのために Microsoft が推奨するソースです。 Q&A ホーム ページで、次のいずれかのタブを選択します。

- *質問*: Microsoft の技術的な質問と回答のメイン ページです。
- *タグ*: タグは、質問を他の類似した質問と分類するためのキーワードです。
- Help: よく寄せられる質問に対する回答の取得、一般的な問題のトラブルシューティング、Microsoft Q&A に関連する機能の検出を行います。

質問するには、Q&A ページの右上にある **[質問する]** ボタンを選択します。 また、[AI アシスト](https://aka.ms/learn-more-ai)を使用することで、質問への回答をより迅速に得ることができます。

質問をする際は、次の成功事例に従うことをお勧めします。

- 以前に投稿したソリューションが見つかる可能性があるため、最初に *[質問* ] ページと *[タグ* ] ページを表示して、製品およびサービス関連のキーワードを検索します。 フィルターを使用して検索結果を絞り込みます。
- ご質問は、Q&A サイトで表示されている言語で記入して送信してください。 これにより、専門家で構成されたコミュニティが、お客様の質問に対して正確で役立つ回答を提供するのに役立ちます。
- 質問を投稿するときはタグを使用してください。 最大 5 つのタグを選択して質問を説明できます。 Q&A に関するコミュニティの専門家の間で質問の発見可能性を高めるために、シナリオに最も密接に関連するタグを選択します。
- 問題のすべての詳細を **[質問の詳細** ] フィールドに含めます。 まず、本文で *1*つの質問をして、最高品質の回答を確保します。 次に、要求に次の詳細を含めます。
    - お客様が達成したい事柄の概要
    - 既に実行した手順
    - 関連するエラーメッセージ(任意)
    - お客様の状況または構成での固有の要素
    - その他の関連情報 (任意)

詳細については、「 [質の高い質問を書く際のヒント](https://learn.microsoft.com/ja-jp/answers/support/quality-question)」を参照してください。

### 問題の診断と解決

Microsoft Entra管理センターとAzure ポータルには、一般的な問題のトラブルシューティングに役立つツールが組み込まれています。 シングル サインオン、デバイス、およびサインイン用の診断ツールがあります。また、多くの一般的な問題に関するガイダンスも提供されています。

ナビゲーション メニューから [ **問題の診断と解決** ] を検索または選択します。

[Image: [問題の診断と解決] ページのスクリーンショット。]

一部の診断ツールでは、ツールを使用するために特定のロールが必要です。 たとえば、サインイン診断ツールを使用するには、少なくとも **課金管理者** である必要があります。 サポートまたは必要なアクセス許可を取得するには、ローカル管理者に問い合わせてください。

#### Microsoft Entra Self-Service サポートを使用する

Self-Service サポートは、ID とアクセスの問題のトラブルシューティングを行い、サポート 要求を作成する前に解決策を見つけるのに役立つ、AI を利用したツールです。 https://learn.microsoft.com および生成 AI の Microsoft ドキュメントを使用して、問題を分析し、関連するガイダンスを提供します。

1. [ **問題の診断と解決**] で、[ **Self-Service サポート**] を選択します。
2. チャット インターフェイスで問題を説明するか、事前構築済みのオプションのいずれかを選択します。
3. 必要に応じて、フォローアップの質問に進みます。
4. 3 番目のフォローアップの質問の後、問題が解決せず、サポートが必要な場合は、[ **サポート リクエストの作成** ] を選択します。

詳細については、「[Microsoft Entra Self-Service サポートの使用方法](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-entra-self-service-support)を参照してください。

#### サポート要求をオープンする

前述のリソースを使用しても回答が見つからない場合は、オンライン サポート リクエストを開くことができます。

オンライン サポート要求は、管理センター内のいくつかの場所から作成できます。

- [ **問題の診断と解決** ] ページから次の手順を実行します。

    [Image: ナビゲーション メニューが強調表示されている [問題の診断と解決] ページのスクリーンショット。]
- 左側のナビゲーション メニューから:

    [Image: 左側のナビゲーション メニューの [新しいサポート要求] ボタンのスクリーンショット。]
- ヘルプ プロンプトに従った後、ヘルプ アイコンから次の操作を行います。

    [Image: ヘルプ アイコンの [新しいサポート要求] ボタンのスクリーンショット。]

##### オンライン サポート要求を作成するためのヒント

- **1 つの問題に対してのみサポート リクエストを開く**
    - 問題の専門家であるサポート エンジニアに接続するよう努めます。
    - Microsoft Entraエンジニアリング チームは、サポートから生成されたインシデントに基づいて作業に優先順位を付けるので、多くの場合、サービスの改善に貢献しています。
- **可能な限り説明的で具体的である。**
    - 提供した情報に基づいてセルフヘルプ ソリューションが提示される場合があります。これは、サポート 要求を作成せずに問題を解決するのに役立つ場合があります。
    - 提供する詳細情報が多いほど、迅速にサポートできます。
- **診断情報は、サポート要求の一部として収集される場合があります。**
    - **[はい]** を選択すると、サポートは要求に関連付けられているサブスクリプションから[高度な診断情報](https://azure.microsoft.com/support/legal/support-diagnostic-information-collection/)を収集できます。
    - この情報を共有しない場合は、 **[いいえ]** を選択します。 収集する可能性があるファイルの種類の詳細については、「 [高度な診断情報ログ](https://learn.microsoft.com/ja-jp/azure/azure-portal/supportability/how-to-create-azure-support-request#advanced-diagnostic-information-logs)」を参照してください。
- **Microsoft の有料サブスクリプションと試用版サブスクリプションのサポートはオンラインと電話で利用できます**
    - サポートは、グローバルな技術、プリセール、課金、サブスクリプションの問題に対して提供されます。
    - 電話サポートとオンライン課金サポートは、より多くの言語で利用できます。
- [サポート オプションを確認し、シナリオに](https://azure.microsoft.com/support/plans)最適なプランを選択します。
- Microsoft のお客様は、Azure ポータルとMicrosoft Entra管理センターでサポート要求を作成および管理できます。

注

- 外部テナントでMicrosoft Entra External IDを使用している場合は、現在、外部テナントの技術的な問題に対してサポート要求機能を使用できません。 代わりに、**[新しいサポート リクエスト]** ページの **[フィードバックを送る]** リンクを使用してください。 または、Microsoft Entraワークフォース テナントに切り替えて、[サポート リクエストを開きます](https://entra.microsoft.com/#view/Microsoft_Azure_Support/NewSupportRequestV3Blade/callerName/ActiveDirectory/issueType/technical)。
- AZURE AD B2C を使用している場合は、まず、Azure サブスクリプションが関連付けられているMicrosoft Entra テナントに切り替えて、サポート チケットを開きます。 通常、これは、Azure サブスクリプションにサインアップしたときに作成された従業員テナントまたは既定のテナントです。 詳細については、Azure サブスクリプションが Microsoft Entraを参照してください。

#### Microsoft Entraでサポート リクエストを開くには:

サポート要求を開く手順は、高レベルのプロセスを表します。 実際の手順は、シナリオと選択した値によって異なります。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[サービス サポート管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#service-support-administrator)としてサインインします。
2. 新しいサポート リクエストを開きます。
3. プロンプトに従って、[問題の **説明** ] セクションを完了します。
4. 指定した情報に基づいて、ガイダンスまたはトラブルシューティングの手順については、「 **推奨されるソリューション**」セクションの情報を確認してください。
    - これらのソリューションは、Azureエンジニアや技術コンテンツ開発者によって作成され、最も一般的な問題を解決する必要があります。
    - それでも問題を解決できない場合は、**[次へ]** を選択してサポート リクエストの作成を続行してください。
5. サポートリクエストを適切なチームにルーティングするのに役立つ **詳細**セクションで、詳細な情報を提供してください。
    - 可能であれば、問題が発生した時期と、再現手順をお知らせください。
    - ログ ファイルや診断からの出力などのファイルをアップロードできます。 ファイルのアップロードの詳細については、「[ファイルのアップロードのガイドライン](https://learn.microsoft.com/ja-jp/azure/azure-portal/supportability/how-to-manage-azure-support-request#file-upload-guidelines)」を参照してください。
6. 必要なすべての情報の入力が完了したら、 **[次へ]** を選択します。
7. 指定したすべての詳細を確認し、[ **作成**] を選択します。

サポート エンジニアが、指定された方法を使用してお客様に連絡します。 初回の応答時間については、「[サポート内容と応答性](https://azure.microsoft.com/support/plans/response/)」を参照してください。

#### サポート リクエストを記入するためのその他のオプション

Azure サポート プランが既にある場合は、[サポートリクエストをこちら](https://portal.azure.com/#blade/Microsoft_Azure_Support/HelpAndSupportBlade/newsupportrequest)で開きます。

Azureのお客様でない場合は、[Microsoft Support for business](https://support.serviceshub.microsoft.com/supportforbusiness) でサポート リクエストを開くことができます。

### Microsoft Security Copilot

Microsoft Security Copilotは、AI と人間の専門知識を組み合わせて、お客様とチームがより迅速かつ効果的に脅威に対応できるように支援するプラットフォームです。 この強力な機能の機能は継続的に開発されており、現在いくつかの機能が利用できます。 これらの機能は、一部のトラブルシューティングおよびサポート シナリオにも使用できます。 詳細については、Microsoft EntraCopilot を参照してください>

### 管理センター Microsoft 365サポートを受ける

[Microsoft 365管理センター](https://admin.microsoft.com)でのMicrosoft Entraのサポートは、管理センターを通じて管理者に提供されます。 [ビジネス向けMicrosoft 365のサポートに関する記事](https://learn.microsoft.com/ja-jp/microsoft-365/admin/)を確認します。

### 最新情報を入手

状況は常に変化します。 次に示すのは、最新のリリースに関する更新プログラムと情報を提供するリソースです。

- [Microsoft Entra](https://learn.microsoft.com/ja-jp/entra/fundamentals/whats-new)の新機能: 最新のリリース ノート、既知の問題、バグ修正、非推奨の機能、今後の変更など、Microsoft Entraの新機能を確認します。
- [Microsoft Entra ID ブログ](https://techcommunity.microsoft.com/t5/azure-active-directory-identity/bg-p/Identity): Microsoft Entraに関するニュースと情報を取得します。
- [Azure更新プログラム](https://azure.microsoft.com/updates/?category=identity): 重要な製品の更新、ロードマップ、お知らせについて説明します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/how-to-manage-groups"} -->
## グループを管理する方法 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups
- Service: entra / fundamentals
- Article date: 2026-06-17
- Summary: メンバーシップや設定など、Microsoft Entra グループを作成および更新する方法について説明します。

Microsoft Entra グループは、制限されている可能性があるアプリやサービスなどのリソースに対して、アクセスとアクセス許可を全員同じにする必要があるユーザーを管理するために使われます。 個々のユーザーに特別なアクセス許可を追加するのではなく、グループを作成し、そのグループのすべてのメンバーにその特別なアクセス許可を適用します。

この記事では、1 つのリソースに 1 つのグループを追加し、ユーザーをそのグループのメンバーとして追加するという基本的なグループ シナリオについて説明します。 動的メンバーシップ グループやルールの作成などのより複雑なシナリオについては、 [Microsoft Entra ユーザー管理のドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/users/)を参照してください。

グループとメンバーを追加する前に、 [グループとメンバーシップの種類について学習](https://learn.microsoft.com/ja-jp/entra/fundamentals/concept-learn-about-groups) し、グループの作成時に使用するオプションを決定するのに役立ちます。

### 前提条件

Microsoft Entra でグループを管理するには、次の前提条件が必要です。

- グループ メンバーシップの設定を管理するには、**[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)**またはグループ**[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)**ロールが必要です。
- Azure サブスクリプション。 お持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。
- Microsoft Entra テナントへのアクセス。 詳細については、「 [新しいテナントの作成」を](https://learn.microsoft.com/ja-jp/entra/fundamentals/create-new-tenant)参照してください。

### 基本的なグループを作成してメンバーを追加する

Microsoft Entra 管理センターを使用し、基本的なグループを作成し、同時にメンバーを追加できます。 グループを作成するには、少なくとも **グループ管理者** ロールまたは **ユーザー管理者** ロールが割り当てられている必要があります。 グループを [管理するための適切な Microsoft Entra ロールを確認](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task#groups-least-privileged-roles)します。

Note

[テナント間の委任された管理](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/cross-tenant-delegated-administration) では、詳細な委任された管理者特権 (GDAP) が使用されます。 GDAP 管理者によるグループの作成は、ターゲット テナントが、部門や会社名などの呼び出し元ユーザーの属性を参照するグループ名前付けポリシープレフィックスまたはサフィックスを使用している場合はサポートされません。 ターゲット テナントの管理者アカウントを使用してグループを作成します。

基本グループを作成してメンバーを追加するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)としてサインインします。
2. **Entra ID**&gt;**Groups**&gt;**All groups** に移動します。
3. [ **新しいグループ]** を選択します。

    [Image: [新しいグループ] オプションが強調表示されている [Microsoft Entra グループ] ページのスクリーンショット。]
4. グループの **種類**を選択します。 グループの種類の詳細については、 [グループとメンバーシップの種類に関する](https://learn.microsoft.com/ja-jp/entra/fundamentals/concept-learn-about-groups) 記事を参照してください。

    - **Microsoft 365** グループの種類を選択すると、[**グループの電子メール アドレス**] オプションが有効になります。
5. **グループ名を入力します。**覚えておく名前を選び、グループにとって意味のある名前を選びます。 名前が既に使われているかどうかを判断するチェックが実行されます。 名前が既に使われている場合は、グループの名前を変更するように求められます。

    - グループの名前をスペースで始めることはできません。 名前をスペースで開始すると、グループ メンバーにロールの割り当てを追加するなどの手順のオプションとしてグループが表示されなくなります。
6. **グループの電子メール アドレス**: Microsoft 365 グループの種類でのみ使用できます。 メール アドレスを手動で入力するか、指定したグループ名から作成されたメール アドレスを使います。
7. **グループの説明。** グループに任意の説明を追加できます。
8. **Microsoft Entra ロールをグループに割り当てることができます** 設定を [はい] に切り替えると、このグループを使用してメンバーに Microsoft Entra ロールを割り当てることができます。

    - このオプションは、P1 または P2 ライセンスでのみ使用できます。
    - 少なくとも **特権ロール管理者** ロールを持っている必要があります。
    - このオプションを有効にすると、[メンバーシップの種類] として [ **割り当て済み** ] が自動的に選択されます。
    - グループの作成時にロールを追加する機能がこのプロセスに追加されます。
    - [ロール割り当て可能なグループの詳細を確認](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-create-eligible)します。
9. メンバーシップの種類を選択 **します。** メンバーシップの種類の詳細については、 [グループとメンバーシップの種類に関する記事を参照](https://learn.microsoft.com/ja-jp/entra/fundamentals/concept-learn-about-groups) してください。
10. 必要に応じて **所有者** または **メンバー** を追加します。 メンバーと所有者は、グループ作成後に追加できます。

    1. [ **所有者** ] または [ **メンバー]** の下にあるリンクを選択して、ディレクトリ内のすべてのユーザーの一覧を設定します。
    2. 一覧からユーザーを選択し、ウィンドウの下部にある **[選択** ] ボタンを選択します。

    [Image: グループの作成プロセス中にグループのメンバーを選択するスクリーンショット。]
11. **を選択して**を作成します。 グループが作成され、他の設定を管理できるようになりました。

#### グループ ウェルカム メールをオフにする

    新しい Microsoft 365 グループに追加されると、メンバーシップの種類に関係なく、すべてのユーザーにウェルカム通知が送信されます。 ユーザーまたはデバイスの属性が変更されると、組織内のすべての動的メンバーシップ グループのルールが、メンバーシップ変更の可能性のために処理されます。 追加されたユーザーは、ウェルカム通知も受け取ります。 [Exchange PowerShell](https://learn.microsoft.com/ja-jp/powershell/module/exchange/set-unifiedgroup) でこの動作を無効にすることができます。

### グループのメンバーまたは所有者を追加する

メンバーと所有者は、既存のグループから追加できます。 メンバーと所有者のプロセスは同じです。 メンバーと所有者を追加するには、 **グループ管理者** または **ユーザー管理者** ロールが必要です。

Note

一度に複数のメンバーを追加する必要がある場合は、 [一括メンバーの追加](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-bulk-import-members)オプションについて説明します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)としてサインインします。
2. **Entra ID**&gt;**Groups**&gt;**All groups** に移動します。
3. 管理する必要があるグループを選びます。
4. **[メンバー]** または [**所有者]** を選択します。

    [Image: [メンバーと所有者] メニュー オプションが強調表示されている [グループの概要] ページのスクリーンショット。]
5. **+ 追加**（メンバーまたは所有者）を選択します。
6. リストをスクロールするか、検索ボックスに名前を入力します。 一度に複数の名前を選択できます。 準備ができたら、[選択] ボタンを **選択** します。

    **[グループの概要]** ページが更新され、グループに追加されたメンバーの数が表示されます。

### グループのメンバーまたは所有者を削除する

メンバーと所有者は、既存のグループから削除できます。 メンバーと所有者のプロセスは同じです。 メンバーと所有者を削除するには、 **グループ管理者** または **ユーザー管理者** ロールが必要です。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)としてサインインします。
2. **Entra ID**&gt;**Groups**&gt;**All groups** に移動します。
3. 管理する必要があるグループを選びます。
4. **[メンバー]** または [**所有者]** を選択します。
5. リストの名前の横にあるチェック ボックスをオンにし、[ **削除** ] ボタンを選択します。

    [Image: 名前が選択され、[削除] ボタンが強調表示されているグループ メンバーのスクリーンショット。]

### グループ設定の編集

グループの名前、説明、またはメンバーシップの種類を編集できます。 グループの設定を編集するには、 **グループ管理者** または **ユーザー管理者** ロールが必要です。

グループ設定を編集するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)としてサインインします。
2. **Entra ID**&gt;**Groups**&gt;**All groups** に移動します。
3. リストをスクロールするか、検索ボックスにグループ名を入力します。 管理する必要があるグループを選びます。
4. サイド メニューから **[プロパティ]** を選択します。
5. 必要に応じて、 **次のような全般設定** 情報を更新します。

    - **[グループ名]:** 既存のグループ名を編集します。
    - **グループの説明。** 既存のグループの説明を編集します。
    - **[グループの種類]。** グループの種類が作成されると、変更することはできません。 **グループの種類**を変更するには、グループを削除し、新しいグループを作成する必要があります。
    - **[メンバーシップの種類]。** メンバーシップの種類を変更します。 **Microsoft Entra ロールを有効にした場合は、グループ オプションに割り当てることができます**。メンバーシップの種類を変更することはできません。 使用可能なメンバーシップの種類の詳細については、 [グループとメンバーシップの種類に関する記事を](https://learn.microsoft.com/ja-jp/entra/fundamentals/concept-learn-about-groups) 参照してください。
    - **オブジェクト ID** オブジェクト ID は変更できませんが、コピーして PowerShell コマンドでグループに対して使用できます。 PowerShell コマンドレットの使用方法の詳細については、 [グループ設定を構成するための Microsoft Entra コマンドレットを](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-settings-v2-cmdlets)参照してください。

### 別のグループにグループを追加する

セキュリティ グループの場合、既存のグループを別のグループに追加することができます (入れ子グループとも呼ばれます)。 グループ メンバーシップの種類に応じて、別のグループのメンバーとしてグループを追加できます。 入れ子になったグループは、メンバーシップスコープと条件付きアクセス スコープに使用できます。 入れ子になったグループは、親グループに割り当てられている共有リソースとアプリケーションにアクセスできません。

現在、次のことはサポートされていません。

- オンプレミスの Active Directory と同期されたグループへのグループの追加。
- Microsoft 365 グループへのセキュリティ グループの追加。
- セキュリティ グループまたはその他の Microsoft 365 グループへの Microsoft 365 グループの追加。
- 入れ子セキュリティ グループの共有リソースとアプリに割り当てられたメンバーシップ。
- 入れ子セキュリティ グループへのライセンスの適用。
- 入れ子構成での配布グループの追加
- メールが有効化されているセキュリティ グループのメンバーとしてのセキュリティ グループの追加。
- ロール割り当て可能なグループのメンバーとしてグループを追加する。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)としてサインインします。
2. **Entra ID**&gt;**Groups**&gt;**All groups** に移動します。
3. [ **すべてのグループ** ] ページで、別のグループのメンバーにするグループを検索して選択します。

    注

    自分のグループを他のグループのメンバーとして追加できるのは、一度に 1 つのみです。 ワイルドカード文字は、[ **グループの選択** ] 検索ボックスではサポートされていません。
4. [グループの概要] ページで、サイド メニューから [ **グループ メンバーシップ** ] を選択します。
5. [ **+ メンバーシップの追加] を選択します**。
6. 自分のグループをメンバーにするグループを見つけて **[選択]** を選びます。

    この演習では、"MDM policy - West" を "MDM policy - All org" グループに追加します。 "MDM - policy - West" グループには、"MDM policy - All org" グループと同じアクセス権があります。

    [Image: サイド メニューの [グループ メンバーシップ] と [メンバーシップの追加] オプションが強調表示されている別のグループのメンバーにグループを作成するスクリーンショット。]

    [MDM policy - West - グループ メンバーシップ] ページを表示して、グループとメンバーのリレーションシップを確認できるようになります。

    グループとメンバーのリレーションシップの詳細な表示については、親グループ名 (MDM policy - All org) を選び、[MDM policy - West] ページの詳細を確認します。

### 別のグループからグループを削除する

既存のセキュリティ グループを別のセキュリティ グループから削除できます。ただし、グループを削除すると、そのメンバーに継承されたアクセス権も削除されます。

1. [ **すべてのグループ** ] ページで、別のグループのメンバーとして削除する必要があるグループを検索して選択します。
2. [グループの概要] ページで、[ **グループ メンバーシップ**] を選択します。
3. [グループ メンバーシップ] ページから親 **グループを** 選択します。
4. [**を選択し、**を削除します。

    この演習では、"MDM policy - All org" グループから "MDM policy - West" を削除します。

    [Image: [メンバーシップの削除] オプションが強調表示されているメンバーとグループの詳細の両方を示す [グループ メンバーシップ] ページのスクリーンショット。]

### グループを削除する

グループはさまざまな理由で削除されますが、通常は次のために行います。

- 正しくない **[グループの種類** ] オプションを選択します。
- 間違って重複したグループを作成した。
- グループが不要になった。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)としてサインインします。
2. **Entra ID**&gt;**Groups**&gt;**All groups** に移動します。
3. 削除するグループを検索して選びます。
4. **を選択して、**を削除します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/how-to-manage-stay-signed-in-prompt"} -->
## Microsoft Entra ID で「サインインの状態を維持しますか?」のプロンプトを管理する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-stay-signed-in-prompt
- Service: entra / fundamentals
- Article date: 2026-04-03
- Summary: Microsoft Entra ユーザーの "サインインしたままにする" プロンプトのしくみと、Microsoft Entra ID で構成する方法について説明します。

ユーザーが正常にサインインすると、「サインイン状態を維持しますか？」というプロンプトが表示されます。 このプロセスは、**サインインしたままにする (KMSI)** として知られており、以前は [カスタマイズ ブランド設定](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-customize-branding) プロセスの一部でした。

この記事では、KMSI プロセスのしくみ、顧客に対してそれを有効にする方法、KMSI に関する問題のトラブルシューティング方法について説明します。

### 前提条件

"サインインしたままにする" (KMSI) オプションを構成するには、次のいずれかのライセンスが必要です。

- Microsoft Entra ID Free（無料）
- Office 365 (Office アプリの場合)
- Microsoft 365

[サインインしたままにする] を有効にするには、 **グローバル管理者** ロールが必要です。 プロンプトが表示されます。

### それはどのように機能するのでしょうか。

ユーザーが **"サインインしたままにしますか?" という**プロンプトに**対して [はい**] と答えた場合、永続的な認証 Cookie が設定されます。 KMSI を機能させるには、Cookie がセッションに格納されている必要があります。 KMSI は、ローカルに保存された Cookie では機能しません。 KMSI が有効になっていない場合、非永続的な Cookie が発行され、24 時間、またはブラウザーが閉じられるまで保持されます。

次の図は、KMSI プロンプトを使用したマネージド テナントとフェデレーション テナントのユーザー サインイン フローを示しています。 [ **サインインしたまま** にする] プロンプトが表示され、[はい] を選択すると、永続的な Cookie が設定されます。

[Image: マネージド テナントとフェデレーション テナントのユーザー サインイン フローを示す図。]

### 特別な注意事項

- [Don't show this again]\(もう一度表示しない\) チェック ボックスは、[サインインしたままにする] とは別に機能します。 フロー
- テナント用に構成されている認証要件によっては、このエクスペリエンスが適用されない場合があります。 一部の条件付きアクセス ポリシーと認証構成では、[サインインしたままにする] フローが表示されません。
- フローにはスマート ロジックが含まれているため、機械学習システムが共有デバイスからの危険度の高いサインインまたはサインインを検出した場合は、[サインイン **を維持しますか?** ] オプションは表示されません。 このシナリオは図に反映され、[サインインしたままにする] オプションが削除されます。
- フェデレーション テナントの場合、ユーザーがフェデレーション ID サービスで正常に認証された後に、プロンプトが表示されます。
- SharePoint Online と Office 2010 の一部の機能は、ユーザーがサインインしたままにすることを選択できるかどうかに依存します。 [ **サインインしたままにするオプションを表示する] オプション** をオフにすると、サインイン プロセス中に他の予期しないプロンプトがユーザーに表示されることがあります。

### "サインインの状態を維持しますか?" を有効にする プロンプト

KMSI 設定は **ユーザー設定**で管理されます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. **Entra ID**&gt;**Users**&gt;**User 設定**にアクセスします。
3. **[ユーザーのサインインを維持]** トグルを **はい** に設定します。

    [Image: [ユーザーのサインインを維持する] トグルが強調表示されている Microsoft Entra ID の [ユーザー設定] ページを示すスクリーンショット。]

### [サインインの状態を維持しますか?] のトラブルシューティング 問題

    ユーザーが **サインインしたまま** にするプロンプトに対してアクションを実行しないが、サインインの試行を中止すると、Microsoft Entra サインイン ログにサインイン ログ エントリが表示されます。 ユーザーに表示される「プロンプト」（操作の促し画面）は、「割り込み」と呼ばれます。

    [Image: [サインインしたままにするサンプル] のスクリーンショットプロンプト。]

    サインイン エラーの詳細については、 **サインイン ログ**を参照してください。 影響を受けたユーザーを一覧から選択し、[ **基本情報** ] セクションで次の詳細を見つけます。

    - **サインイン エラー コード**: 50140
    - **エラーの理由**: このエラーは、ユーザーがサインインしているときに "サインインしたままにする" 割り込みが原因で発生しました。

    ユーザー設定で [ **サインインしたまま** にする] オプションを **[いいえ** ] に設定することで、ユーザーに割り込みが表示されないようにすることができます。 この設定により、ディレクトリ内のすべてのユーザーに対して KMSI プロンプトが無効になります。

    条件付 [きアクセスで永続的なブラウザー セッション 制御を](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime) 使用して、ユーザーに KMSI プロンプトが表示されないようにすることもできます。 このオプションを使用すると、選択したユーザー グループに対して KMSI プロンプトを無効にできます。ディレクトリ内の他のすべてのユーザーのサインイン動作は影響を受けません。

    ユーザーにベネフィットがある場合にのみ KMSI プロンプトが表示されるようにするには、次のような場合に意図的に KMSI プロンプトが表示されないようにします。

    - ユーザーがシームレス SSO と統合 Windows 認証 (IWA) を使用してサインインしている
    - ユーザーが Active Directory フェデレーション サービス (AD FS) と IWA を使用してサインインしている
    - ユーザーがテナントのゲストである
    - ユーザーのリスク スコアが高い
    - ユーザーまたは管理者の同意フロー中にサインインが発生する
    - 永続的なブラウザー セッション制御が条件付きアクセス ポリシーで構成されている
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/how-to-manage-user-profile-info"} -->
## Microsoft Entra 管理センターでユーザー プロファイル情報を管理する方法 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-user-profile-info
- Service: entra / fundamentals
- Article date: 2025-03-25
- Summary: IT 管理者が Microsoft Entra ID でユーザーのプロファイルと設定を管理する方法について説明します。

### 概要

ユーザー プロファイルは、ユーザーの ID をデジタル表現したもので、可能であれば、ユーザーの表現方法を反映する必要があります。 ユーザー プロファイルは、Outlook、Teams、Windows など、Microsoft スイート内のいくつかの場所に表示されます。 これらのツール全体に表示される ID 情報は、ユーザー プロファイルから取得されます。

ユーザーのプロファイル情報と設定は、ディレクトリ内のすべてのユーザーに対して個別に管理できます。 これらの設定をまとめて見ると、アクセス許可、制限、およびその他の接続がどのように連携するかを確認できます。

この記事では、プロファイル画像や役職などのユーザー プロファイル情報を追加する方法について説明します。 また、ユーザーが LinkedIn アカウントに接続したり、Microsoft Entra 管理ポータルへのアクセスを制限したりできるようにすることもできます。 一部の設定は、 [Microsoft 365 での組織データ](https://learn.microsoft.com/ja-jp/viva/organizational-data)のアップロードなど、複数の領域で管理される場合があります。

### [前提条件]

最小限の特権の必要なロールは、追加するユーザーの種類と、Microsoft Entra ロールを同時に割り当てる必要があるかどうかによって異なります。 可能な限り、最小限の特権ロールを使用する必要があります。

| Task | Role |
| --- | --- |
| 新しいユーザーを作成する | ユーザー管理者 |
| 外部ゲストを招待する | ゲスト招待者 |
| Microsoft Entra ロールを割り当てる | 特権ロール管理者 |

### プロファイル情報を追加または変更する

新しいユーザーが作成されると、ユーザー プロファイルに追加される詳細はごくわずかです。 組織で詳細が必要な場合は、ユーザーの作成後に追加できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。

    [Image: Microsoft Entra ID の [すべてのユーザー] ページのスクリーンショット。]
3. ユーザーを選択します。
4. ユーザー プロファイルの詳細を編集するには、2 つの方法があります。 ページの上部から **[プロパティの編集]** を選択するか、[プロパティ] を選択 **します**。

    [Image: 編集オプションが強調表示されている、選択したユーザーの概要ページのスクリーンショット。]
5. 変更を行った後、[ **保存** ] ボタンを選択します。

[ **プロパティの編集]** オプションを選択した場合:

- プロパティの完全な一覧は、[ **すべて** ] カテゴリの編集モードで表示されます。
- カテゴリに基づいてプロパティを編集するには、ページの上部からカテゴリを選択します。
- ページの下部にある **[保存]** ボタンを選択して、変更を保存します。

[Image: 選択したユーザーの詳細をスクリーンショットで、詳細カテゴリと [保存] ボタンが強調表示されています。]

**[プロパティ**] タブ オプションを選択した場合:

- プロパティの完全な一覧が表示され、確認できます。
- プロパティを編集するには、カテゴリ見出しの横にある鉛筆アイコンを選択します。
- ページの下部にある **[保存]** ボタンを選択して、変更を保存します。

[Image: 編集オプションが強調表示されている [プロパティ] タブのスクリーンショット。]

#### プロファイル カテゴリ

編集できるプロファイルの詳細には、6 つのカテゴリがあります。

- **同一性：** ユーザーの ID 値を追加または更新します。 これらの値には、表示名、名と姓、およびユーザー プリンシパル名が含まれます。 ユーザー名を追加または編集する前に、ユーザー 名のガイダンス を確認してください。
- **ジョブ情報:** ユーザーの役職、部署、マネージャーなど、ジョブ関連の情報を追加します。
- **連絡先情報:** ユーザーに関連する連絡先情報を追加します。
- **保護者によるコントロール:** K-12 学区などの組織では、ユーザーの年齢グループを指定する必要がある場合があります。 "年少者" は12歳以下、"未成年" は 13 から 18歳、"大人" は 18 歳より上です。 年齢グループと親オプションによって提供される同意の組み合わせによって、法的年齢グループの分類が決まります。 法的年齢グループの分類により、ユーザーのアクセスと権限が制限される場合があります。
- **設定：** ユーザーが Microsoft Entra テナントにサインインできるかどうかを決定します。 ユーザーのグローバルな場所を指定することもできます。
- **オンプレミス:** Windows Server Active Directory から同期されたアカウントには、Microsoft Entra アカウントに適用できないその他の値が含まれます。

注

権限のソースが Windows Server Active Directory であるユーザーの場合は、Windows Server Active Directory を使用して ID、連絡先情報、またはジョブ情報を更新する必要があります。 更新を行った後、次の同期サイクルが完了するまで待ってから、変更を有効にする必要があります。 ただし、使用状況の場所などの Microsoft Entra ID 属性を更新する場合は、Microsoft Entra 管理センターで属性を直接更新できます。 ユーザー プリンシパル名、オンプレミスの不変 ID、アカウントの有効化など、オンプレミスから同期される一部の属性は、オンプレミスから管理されているユーザーの Microsoft Entra ID で更新できますが、これらの属性を Entra ID で管理することは、緊急時以外には推奨されません。

### ユーザー名のプロパティに関するガイダンス

Microsoft Entra ID には、完全名と部分名、部門、およびタイトルを指定するためのいくつかのフィールドが用意されています。 一般に、Microsoft Entra ID のすべての名前関連フィールドは、Microsoft アプリと Microsoft 以外のアプリが異なるコンテキストで使用する可能性があるユーザーに表示されるデータとして扱います。 Microsoft Entra ID の名前フィールドは、すべての Microsoft 製品を通じて反映できるユーザーの優先名を表す必要があります。

Microsoft 365 のアプリケーションでは、次のいずれかの定義に従ってユーザーの名前が表示されます。

- Microsoft Entra ID に格納されている名と姓、または
- Microsoft Entra ID に格納されている表示名

ユーザーの名前プロパティを追加および編集する場合は、次のヒントを使用します。

- 名と姓は、Microsoft 365 アプリケーションのプロファイル カードや会議の文字起こしなど、ユーザープロファイルに表示されるユーザーが期待する名前であることを確認します。
- ユーザーが表示する必要がある名前は、正式な ID ドキュメントで使用される法的名に対応していない可能性があります。
- **表示名**フィールドを使用して、組織の名前を表示するためのアプローチまたはポリシーに対応します。
- ユーザーの代替名として **表示名** フィールドを使用しないでください。この場合、名と姓が使用されている製品と、表示名が使用されている製品間でユーザーの表現が一貫性を失う可能性があります。

注

組織の人事システムからのデータは、HR 駆動型プロビジョニングを使用して Microsoft Entra ID に同期できます。 ユーザーの法的名を同期する前に、ユーザー名の ガイダンス を検討してください。 詳細については、「 [**HR 主導のプロビジョニングとは」を**](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/what-is-hr-driven-provisioning)参照してください。

#### ユーザー名の例

次の例を確認して、さまざまなコンテキストで名前プロパティがどのように使用されるかを理解します。 例では、Christopher Green という名前を使用し、彼は Chris Green と呼ばれています。

- 組織でニックネームがサポートされている場合は、[名] フィールドと [表示名] フィールドにこの情報を入力します。

    - 名: Chris
    - 姓: 緑
    - 表示名: Chris Green
- 組織で姓を最初に一覧表示する場合は、表示名を使用して、優先する順序で名前を表示します。

    - 名: Chris
    - 姓: 緑
    - 表示名: Green、Chris
- 姓と名が同じユーザーが 2 人いる場合は、中間のイニシャルを使用して区別します。

    - 名: Chris B.
    - 姓: 緑
    - ID: Chris B. Green
- ユーザーが正式な識別文書に表示される法的名以外の名前で行く場合は、組織の人事管理 (HCM) システムに法的名を格納し、Microsoft Entra ID に優先名を入力します。

    - 王志ハオは「ジョン・ワン」と呼ばれています。
    - 法的名が HCM に表示される
    - Microsoft Entra ID の名/ファーストネーム: John
    - Microsoft Entra ID の姓: 王

#### プロファイル画像を追加または編集する

ユーザーの概要ページで、ユーザーのサムネイルの右下隅にあるカメラ アイコンを選択します。 画像が追加されていない場合は、ユーザーのイニシャルがここに表示されます。 この画像は、Microsoft Entra ID とユーザーの個人用ページ (myapps.microsoft.com ページなど) に表示されます。

すべての変更がユーザーに対して保存されます。

注

ユーザーのプロファイル画像の更新で問題が発生した場合は、ユーザーがサインインできるように Office 365 Exchange Online Enterprise App が有効になっていることを確認してください。

### すべてのユーザーの設定を管理する

[ **ユーザー設定]** 領域では、すべてのユーザーに影響を与えるいくつかの設定を調整できます。 一部の設定は、このページからリンクされた別の領域で管理されます。 これらの設定には、全体管理者ロールが必要です。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. **Entra ID**&gt;**ユーザー**&gt;**ユーザー設定**に移動します。

    [Image: Microsoft Entra ユーザー設定オプションのスクリーンショット。]

次の設定は **、ユーザー設定**から管理できます。

- ユーザーが自分のアプリケーションを登録できるようにする
- 管理者以外が自分のテナントを作成できないようにする
    - 詳細については、「既定の[ユーザーアクセス許可](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#restrict-member-users-default-permissions)」を参照してください。
- ユーザーがセキュリティ グループを作成できるようにする
- ゲスト ユーザーのアクセス制限
    - ゲスト ユーザーはメンバーと同じアクセス権を持ちます (最も包括的)
    - ゲスト ユーザーは、ディレクトリ オブジェクトのプロパティとメンバーシップへのアクセスが制限されています
    - ゲスト ユーザー アクセスは、独自のディレクトリ オブジェクトのプロパティとメンバーシップに制限されます (最も制限が厳しい)
- Microsoft Entra 管理ポータルへのアクセスを制限する
- [ユーザーが職場または学校アカウントを LinkedIn に接続できるようにする](https://learn.microsoft.com/ja-jp/entra/identity/users/linkedin-user-consent)
- [\[サインインしたままにする\] プロンプトを有効にする](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-stay-signed-in-prompt)
- 外部コラボレーション設定を管理する
    - [ゲスト ユーザー アクセス](https://learn.microsoft.com/ja-jp/entra/identity/users/users-restrict-guest-permissions)
    - [ゲスト招待の設定](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure)
    - [外部ユーザーの退席設定](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-user-flow#enable-self-service-sign-up-for-your-tenant)
    - コラボレーションの制限

### 複数のユーザーを一度に編集する

この新しい Microsoft Entra 管理センターの更新プログラムでは、特定のユーザー属性を一括で編集できます。 [すべてのユーザー] ページでは、一度に最大 60 人のユーザーを選択し、一度に属性を更新できます。 ユーザーを一括で追加する方法については、「 [Microsoft Entra ID でユーザーを一括作成](https://learn.microsoft.com/ja-jp/entra/identity/users/users-bulk-add)する」を参照してください。

1. [ユーザー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)にサインインします。
2. **Entra ID**&gt;**Users** に移動します。

    [Image: Microsoft Entra のすべてのユーザー設定オプションのスクリーンショット。]
3. 編集するユーザーを選択します。 一度に最大 60 人のユーザーを編集できます。

    ページの上部にある **[編集]** を選択します。

    [Image: Microsoft Entra 管理センターの複数のユーザー設定オプションのスクリーンショット。]
4. ユーザーを更新するには、2 つの方法があります。 ページの上部から **[編集]** を選択するか、選択したユーザーを右クリックします。

[Image: Microsoft Entra 管理センターの複数のユーザー設定編集オプションのスクリーンショット。]

1. ドロップダウンで使用可能なユーザー管理アクションのいずれかを選択して、選択したユーザーに対して実行します。
2. [ **プロパティの編集]** アクションを選択すると、次のプロパティを編集できます。

- 市区町村
- 会社名
- 国または地域
- 部署
- 従業員の雇用日
- 従業員の種類
- 役職
- Office の場所
- 都道府県
- 番地
- 使用場所
- 郵便番号

注

他のプロパティを編集するには、特定のユーザー プロファイルにアクセスする必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/how-to-rename-azure-ad"} -->
## Azure Active Directory (Azure AD) の名前を変更する方法 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-rename-azure-ad
- Service: entra / fundamentals
- Article date: 2025-05-07
- Summary: Microsoft Entra ID の製品名とアイコンを使用するように、お客様と組織がドキュメントやコンテンツを更新する方法に関するベスト プラクティスとヒントについて説明します。

製品のマルチクラウド、マルチプラットフォーム機能をより適切に伝え、Microsoft Entra 製品ファミリの名前を統合するため、Azure Active Directory (Azure AD) の名前は Microsoft Entra ID に変更されました。

この記事では、新しい製品名とアイコンを使用してドキュメントやコンテンツを更新するお客様や組織のベスト プラクティスとサポートについて説明します。

### 前提条件

ドキュメントまたはコンテンツでインスタンスを Azure AD から Microsoft Entra ID に変更する前に、「[Azure AD の新しい名前](https://learn.microsoft.com/ja-jp/entra/fundamentals/new-name)」記事のガイダンスを理解して、次のことを行います。

- 製品名と変更を行った理由を理解する

- 新しい製品アイコンをダウンロードする

- 変更されていない名前の一覧を取得する

- よくある質問とその他の質問に対する回答を取得する

### コンテンツの更新プログラムの名前変更を評価してスコープを設定する

エクスペリエンスを監査して、Azure AD とそのアイコンへの参照を確認します。

1. **コンテンツをスキャン**して、Azure AD とその同義語への参照を識別します。 すべてのインスタンスの詳細な一覧をコンパイルします。

    - 次の用語を検索します: `Azure Active Directory (Azure AD)`、`Azure Active Directory`、`Azure AD`、`AAD`
    - Azure AD アイコン ([Image: Azure AD 製品アイコン][Image: Azure AD の代替製品のアイコン]) があるグラフィックスを検索して Microsoft Entra ID アイコン ([Image: Microsoft Entra ID 製品アイコン]) に置き換える

    Microsoft Entra ID アイコンは、[Microsoft Entra アーキテクチャ アイコン](https://learn.microsoft.com/ja-jp/entra/architecture/architecture-icons)からダウンロードできます
2. **リスト内の例外を特定します。**

    - 破壊的変更をしないでください。
    - 名前付けガイダンスの「[変更されない名前](https://learn.microsoft.com/ja-jp/entra/fundamentals/new-name#what-names-arent-changing)」セクションを確認し、どの Azure AD 用語が変更されていないかについて注意してください。
    - `Active Directory` のインスタンスを変更しないでください。 `Azure Active Directory` の名前だけが変更されており、`Active Directory` は変更されません。これは、別の製品である Windows Server Active Directory の短縮名です。
3. **将来の使用状況に基づいて評価し、優先順位を付けます**。 どのコンテンツを更新する必要があるかは、ユーザー向けか、組織内、対象ユーザー、お客様ベースで広く認知されているかに基づいて検討します。 エンド ユーザーへの公開が制限されている場合は、一部のコードまたはコンテンツを更新する必要がない場合があります。

    ビデオやブログなどの既存の日付付きコンテンツを将来の視聴者のために更新する価値があるかどうかを判断します。 古いコンテンツの名前を変更しなくてもかまいません。 エンドユーザーを支援するために、"Azure AD は ID Microsoft Entra になりました" などの免責事項を追加できます。

### テキストベースのコンテンツに "検索と置換" を使用する

関連するツールを使用して、組織のコンテンツとエクスペリエンスを更新します。

1. ほとんどすべての編集ツールは、ネイティブまたはプラグインを使用して、"検索と置換 (search and replace)" または "検索と置換 (find and replace)" 機能を提供します。任意のアプリを使用します。
2. [検索と置換] を使って、文字列 `Azure Active Directory (Azure AD)`、`Azure Active Directory`、`Azure AD`、`AAD` を検索します。
3. すべてのインスタンスを Microsoft Entra ID に置き換えないでください。
4. 各インスタンスが製品または製品の機能を参照しているかどうかを確認します。

    - 製品名としてのみ使われている Azure AD は、Microsoft Entra ID に置き換える必要があります。
    - Azure AD の機能が、Microsoft Entra の機能になります。 たとえば、"Azure AD 条件付きアクセス" は、"Microsoft Entra 条件付きアクセス" になります。

### カスタム コードを使用して一括編集を自動化する

次の条件を使って、`Azure Active Directory (Azure AD)`、`Azure Active Directory`、`Azure AD`、`AAD` のインスタンスに行う必要がある変更を判断します。

1. テキスト文字列が前の用語の名前付け辞書で見つかった場合は、新しい用語に変更します。
2. `Azure Active Directory (Azure AD)`、`Azure Active Directory`、`Azure AD`、または `AAD` の後に句読点がある場合は、それが製品名であるため、`Microsoft Entra ID` に置き換えます。
3. `Azure Active Directory (Azure AD)`、`Azure Active Directory`、`Azure AD`、または `AAD` の後に、`for`、`Premium`、`Plan`、`P1`、または `P2` が続く場合、それは SKU 名またはサービス プランを示しているため、`Microsoft Entra ID` に置き換えます。
4. 冠詞 (`a`、`an`、`the`) または所有格 (`your`、`your organization's`) が (`Azure Active Directory (Azure AD)`、`Azure Active Directory`、`Azure AD`、`AAD`) の前にある場合は、機能名であるため `Microsoft Entra` に置き換えます。 次に例を示します。

    1. "Azure AD テナント" は "Microsoft Entra テナント" になります
    2. "お客様の組織の Azure AD テナント" は "お客様の Microsoft Entra テナント" になります
5. `Azure Active Directory (Azure AD)`、`Azure Active Directory`、`Azure AD`、または `AAD` の後に前の手順に含まれない形容詞または名詞が続く場合は、機能名であるため `Microsoft Entra` に置き換えます。 たとえば、`Azure AD Conditional Access` は `Microsoft Entra Conditional Access` になり、`Azure AD tenant` は `Microsoft Entra tenant` になります。
6. それ以外の場合は、`Azure Active Directory (Azure AD)`、`Azure Active Directory`、`Azure AD`、または `AAD` を `Microsoft Entra ID` に置き換えます。

    カスタム ロジックをさらに使いやすくするには、「[更新された用語の用語集](https://learn.microsoft.com/ja-jp/entra/fundamentals/new-name#glossary-of-updated-terminology)」セクションを参照してください。

### グラフィックスとアイコンを更新する

グラフィックスとアイコンを更新するには、次の手順のようにします。

1. Azure AD アイコンを Microsoft Entra ID アイコンに置き換えます。
2. `Azure Active Directory (Azure AD)`、`Azure Active Directory`、`Azure AD`、または `AAD` を含むタイトルまたはテキストは、`Microsoft Entra ID` に置き換えます。

### 変更をお客様に伝える

顧客の移行を支援するため、更新したコンテンツまたはドキュメントに注を追加することをお勧めします。

1. 最初の 1 年間は、`Azure Active Directory is now Microsoft Entra ID` を追加するか、新しい名前の後に `formerly Azure Active Directory` を付けます。

### PowerShell スクリプトのサンプル

次の PowerShell スクリプトをベースラインとして使って、ドキュメントまたはコンテンツでの Azure AD の参照の名前を変更できます。 このコード サンプルでは次のことを行います。

- 指定されたフォルダーと入れ子になったすべてのフォルダー内で `.resx` ファイルをスキャンします。
- `Azure Active Directory (Azure AD)`、`Azure Active Directory`、`Azure AD`、`AAD` への参照を [Azure AD の新しい名前](https://learn.microsoft.com/ja-jp/entra/fundamentals/new-name)に従って正しい用語に置き換えることで、ファイルを編集します。

ニーズと更新する必要があるファイルのスコープに従って、ベースライン スクリプトを編集します。 エッジ ケースを考慮し、ソース ファイルでメッセージを定義した方法に従ってスクリプトを変更することが、必要な場合があります。 スクリプトは完全には自動化されません。 スクリプトをそのまま使用する場合は、出力を確認する必要があり、「[Azure AD の新しい名前](https://learn.microsoft.com/ja-jp/entra/fundamentals/new-name)」のガイダンスに従うために追加の調整が必要になることがあります。

```powershell
# Define the old and new terminology
$terminology = @(
    @{ Key = 'Azure AD External Identities'; Value = 'Microsoft Entra External ID' },
    @{ Key = 'Azure AD Identity Governance'; Value = 'Microsoft Entra ID Governance' },
    @{ Key = 'Azure AD Verifiable Credentials'; Value = 'Microsoft Entra Verified ID' },
    @{ Key = 'Azure AD Workload Identities'; Value = 'Microsoft Entra Workload ID' },
    @{ Key = 'Azure AD Domain Services'; Value = 'Microsoft Entra Domain Services' },
    @{ Key = 'Azure AD access token authentication'; Value = 'Microsoft Entra access token authentication' },
    @{ Key = 'Azure AD admin center'; Value = 'Microsoft Entra admin center' },
    @{ Key = 'Azure AD portal'; Value = 'Microsoft Entra admin center' },
    @{ Key = 'Azure AD application proxy'; Value = 'Microsoft Entra application proxy' },
    @{ Key = 'Azure AD authentication'; Value = 'Microsoft Entra authentication' },
    @{ Key = 'Azure AD Conditional Access'; Value = 'Microsoft Entra Conditional Access' },
    @{ Key = 'Azure AD cloud-only identities'; Value = 'Microsoft Entra cloud-only identities' },
    @{ Key = 'Azure AD Connect'; Value = 'Microsoft Entra Connect' },
    @{ Key = 'AD Connect'; Value = 'Microsoft Entra Connect' },
    @{ Key = 'AD Connect Sync'; Value = 'Microsoft Entra Connect Sync' },
    @{ Key = 'Azure AD Connect Sync'; Value = 'Microsoft Entra Connect Sync' },
    @{ Key = 'Azure AD domain'; Value = 'Microsoft Entra domain' },
    @{ Key = 'Azure AD domain'; Value = 'Microsoft Entra domain' },
    @{ Key = 'Azure AD Domain Services'; Value = 'Microsoft Entra Domain Services' },
    @{ Key = 'Azure AD Enterprise Applications'; Value = 'Microsoft Entra enterprise applications' },
    @{ Key = 'Azure AD federation services'; Value = 'Active Directory Federation Services' },
    @{ Key = 'Azure AD hybrid identities'; Value = 'Microsoft Entra hybrid identities' },
    @{ Key = 'Azure AD identities'; Value = 'Microsoft Entra identities' },
    @{ Key = 'Azure AD role'; Value = 'Microsoft Entra role' },
    @{ Key = 'Azure AD'; Value = 'Microsoft Entra ID' },
    @{ Key = 'AAD'; Value = 'ME-ID' },
    @{ Key = 'Azure AD auth'; Value = 'Microsoft Entra auth' },
    @{ Key = 'Azure AD-only auth'; Value = 'Microsoft Entra-only auth' },
    @{ Key = 'Azure AD object'; Value = 'Microsoft Entra object' },
    @{ Key = 'Azure AD identity'; Value = 'Microsoft Entra identity' },
    @{ Key = 'Azure AD schema'; Value = 'Microsoft Entra schema' },
    @{ Key = 'Azure AD seamless single sign-on'; Value = 'Microsoft Entra seamless single sign-on' },
    @{ Key = 'Azure AD self-service password reset'; Value = 'Microsoft Entra self-service password reset' },
    @{ Key = 'Azure AD SSPR'; Value = 'Microsoft Entra SSPR' },
    @{ Key = 'Azure AD domain'; Value = 'Microsoft Entra domain' },
    @{ Key = 'Azure AD group'; Value = 'Microsoft Entra group' },
    @{ Key = 'Azure AD login'; Value = 'Microsoft Entra login' },
    @{ Key = 'Azure AD managed'; Value = 'Microsoft Entra managed' },
    @{ Key = 'Azure AD managed identities'; Value = 'Managed identities for Azure resources' },
    @{ Key = 'Azure AD entitlement'; Value = 'Microsoft Entra entitlement' },
    @{ Key = 'Azure AD access review'; Value = 'Microsoft Entra access review' },
    @{ Key = 'Azure AD Identity Protection'; Value = 'Microsoft Entra ID Protection' },
    @{ Key = 'Azure AD pass-through'; Value = 'Microsoft Entra pass-through' },
    @{ Key = 'Azure AD password'; Value = 'Microsoft Entra password' },
    @{ Key = 'Azure AD Privileged Identity Management'; Value = 'Microsoft Entra Privileged Identity Management' },
    @{ Key = 'Azure AD registered'; Value = 'Microsoft Entra registered' },
    @{ Key = 'Azure AD reporting and monitoring'; Value = 'Microsoft Entra reporting and monitoring' },
    @{ Key = 'Azure AD enterprise app'; Value = 'Microsoft Entra enterprise app' },
    @{ Key = 'Azure AD cloud-only identities'; Value = 'Microsoft Entra cloud-only identities' },
    @{ Key = 'Azure AD Premium P1'; Value = 'Microsoft Entra ID P1' },
    @{ Key = 'AD Premium P1'; Value = 'Microsoft Entra ID P1' },
    @{ Key = 'Azure AD Premium P2'; Value = 'Microsoft Entra ID P2' },
    @{ Key = 'AD Premium P2'; Value = 'Microsoft Entra ID P2' },
    @{ Key = 'Azure AD F2'; Value = 'Microsoft Entra ID F2' },
    @{ Key = 'Azure AD Free'; Value = 'Microsoft Entra ID Free' },
    @{ Key = 'Azure AD for education'; Value = 'Microsoft Entra ID for education' },
    @{ Key = 'Azure AD work or school account'; Value = 'Microsoft Entra work or school account' },
    @{ Key = 'federated with Azure AD'; Value = 'federated with Microsoft Entra' },
    @{ Key = 'Hybrid Azure AD Join'; Value = 'Microsoft Entra hybrid join' },
    @{ Key = 'Azure Active Directory External Identities'; Value = 'Microsoft Entra External ID' },
    @{ Key = 'Azure Active Directory Identity Governance'; Value = 'Microsoft Entra ID Governance' },
    @{ Key = 'Azure Active Directory Verifiable Credentials'; Value = 'Microsoft Entra Verified ID' },
    @{ Key = 'Azure Active Directory Workload Identities'; Value = 'Microsoft Entra Workload ID' },
    @{ Key = 'Azure Active Directory Domain Services'; Value = 'Microsoft Entra Domain Services' },
    @{ Key = 'Azure Active Directory access token authentication'; Value = 'Microsoft Entra access token authentication' },
    @{ Key = 'Azure Active Directory admin center'; Value = 'Microsoft Entra admin center' },
    @{ Key = 'Azure Active Directory portal'; Value = 'Microsoft Entra admin center' },
    @{ Key = 'Azure Active Directory application proxy'; Value = 'Microsoft Entra application proxy' },
    @{ Key = 'Azure Active Directory authentication'; Value = 'Microsoft Entra authentication' },
    @{ Key = 'Azure Active Directory Conditional Access'; Value = 'Microsoft Entra Conditional Access' },
    @{ Key = 'Azure Active Directory cloud-only identities'; Value = 'Microsoft Entra cloud-only identities' },
    @{ Key = 'Azure Active Directory Connect'; Value = 'Microsoft Entra Connect' },
    @{ Key = 'Azure Active Directory Connect Sync'; Value = 'Microsoft Entra Connect Sync' },
    @{ Key = 'Azure Active Directory domain'; Value = 'Microsoft Entra domain' },
    @{ Key = 'Azure Active Directory domain'; Value = 'Microsoft Entra domain' },
    @{ Key = 'Azure Active Directory Domain Services'; Value = 'Microsoft Entra Domain Services' },
    @{ Key = 'Azure Active Directory Enterprise Applications'; Value = 'Microsoft Entra enterprise applications' },
    @{ Key = 'Azure Active Directory federation services'; Value = 'Active Directory Federation Services' },
    @{ Key = 'Azure Active Directory hybrid identities'; Value = 'Microsoft Entra hybrid identities' },
    @{ Key = 'Azure Active Directory identities'; Value = 'Microsoft Entra identities' },
    @{ Key = 'Azure Active Directory role'; Value = 'Microsoft Entra role' },
    @{ Key = 'Azure Active Directory'; Value = 'Microsoft Entra ID' },
    @{ Key = 'Azure Active Directory auth'; Value = 'Microsoft Entra auth' },
    @{ Key = 'Azure Active Directory-only auth'; Value = 'Microsoft Entra-only auth' },
    @{ Key = 'Azure Active Directory object'; Value = 'Microsoft Entra object' },
    @{ Key = 'Azure Active Directory identity'; Value = 'Microsoft Entra identity' },
    @{ Key = 'Azure Active Directory schema'; Value = 'Microsoft Entra schema' },
    @{ Key = 'Azure Active Directory seamless single sign-on'; Value = 'Microsoft Entra seamless single sign-on' },
    @{ Key = 'Azure Active Directory self-service password reset'; Value = 'Microsoft Entra self-service password reset' },
    @{ Key = 'Azure Active Directory SSPR'; Value = 'Microsoft Entra SSPR' },
    @{ Key = 'Azure Active Directory SSPR'; Value = 'Microsoft Entra SSPR' },
    @{ Key = 'Azure Active Directory domain'; Value = 'Microsoft Entra domain' },
    @{ Key = 'Azure Active Directory group'; Value = 'Microsoft Entra group' },
    @{ Key = 'Azure Active Directory login'; Value = 'Microsoft Entra login' },
    @{ Key = 'Azure Active Directory managed'; Value = 'Microsoft Entra managed' },
    @{ Key = 'Azure Active Directory entitlement'; Value = 'Microsoft Entra entitlement' },
    @{ Key = 'Azure Active Directory access review'; Value = 'Microsoft Entra access review' },
    @{ Key = 'Azure Active Directory Identity Protection'; Value = 'Microsoft Entra ID Protection' },
    @{ Key = 'Azure Active Directory pass-through'; Value = 'Microsoft Entra pass-through' },
    @{ Key = 'Azure Active Directory password'; Value = 'Microsoft Entra password' },
    @{ Key = 'Azure Active Directory Privileged Identity Management'; Value = 'Microsoft Entra Privileged Identity Management' },
    @{ Key = 'Azure Active Directory registered'; Value = 'Microsoft Entra registered' },
    @{ Key = 'Azure Active Directory reporting and monitoring'; Value = 'Microsoft Entra reporting and monitoring' },
    @{ Key = 'Azure Active Directory enterprise app'; Value = 'Microsoft Entra enterprise app' },
    @{ Key = 'Azure Active Directory cloud-only identities'; Value = 'Microsoft Entra cloud-only identities' },
    @{ Key = 'Azure Active Directory Premium P1'; Value = 'Microsoft Entra ID P1' },
    @{ Key = 'Azure Active Directory Premium P2'; Value = 'Microsoft Entra ID P2' },
    @{ Key = 'Azure Active Directory F2'; Value = 'Microsoft Entra ID F2' },
    @{ Key = 'Azure Active Directory Free'; Value = 'Microsoft Entra ID Free' },
    @{ Key = 'Azure Active Directory for education'; Value = 'Microsoft Entra ID for education' },
    @{ Key = 'Azure Active Directory work or school account'; Value = 'Microsoft Entra work or school account' },
    @{ Key = 'federated with Azure Active Directory'; Value = 'federated with Microsoft Entra' },
    @{ Key = 'Hybrid Azure Active Directory Join'; Value = 'Microsoft Entra hybrid join' },
    @{ Key = 'AAD External Identities'; Value = 'Microsoft Entra External ID' },
    @{ Key = 'AAD Identity Governance'; Value = 'Microsoft Entra ID Governance' },
    @{ Key = 'AAD Verifiable Credentials'; Value = 'Microsoft Entra Verified ID' },
    @{ Key = 'AAD Workload Identities'; Value = 'Microsoft Entra Workload ID' },
    @{ Key = 'AAD Domain Services'; Value = 'Microsoft Entra Domain Services' },
    @{ Key = 'AAD access token authentication'; Value = 'Microsoft Entra access token authentication' },
    @{ Key = 'AAD admin center'; Value = 'Microsoft Entra admin center' },
    @{ Key = 'AAD portal'; Value = 'Microsoft Entra admin center' },
    @{ Key = 'AAD application proxy'; Value = 'Microsoft Entra application proxy' },
    @{ Key = 'AAD authentication'; Value = 'Microsoft Entra authentication' },
    @{ Key = 'AAD Conditional Access'; Value = 'Microsoft Entra Conditional Access' },
    @{ Key = 'AAD cloud-only identities'; Value = 'Microsoft Entra cloud-only identities' },
    @{ Key = 'AAD Connect'; Value = 'Microsoft Entra Connect' },
    @{ Key = 'AAD Connect Sync'; Value = 'Microsoft Entra Connect Sync' },
    @{ Key = 'AAD domain'; Value = 'Microsoft Entra domain' },
    @{ Key = 'AAD domain'; Value = 'Microsoft Entra domain' },
    @{ Key = 'AAD Domain Services'; Value = 'Microsoft Entra Domain Services' },
    @{ Key = 'AAD Enterprise Applications'; Value = 'Microsoft Entra enterprise applications' },
    @{ Key = 'AAD federation services'; Value = 'Active Directory Federation Services' },
    @{ Key = 'AAD hybrid identities'; Value = 'Microsoft Entra hybrid identities' },
    @{ Key = 'AAD identities'; Value = 'Microsoft Entra identities' },
    @{ Key = 'AAD role'; Value = 'Microsoft Entra role' },
    @{ Key = 'AAD'; Value = 'Microsoft Entra ID' },
    @{ Key = 'AAD auth'; Value = 'Microsoft Entra auth' },
    @{ Key = 'AAD-only auth'; Value = 'Microsoft Entra-only auth' },
    @{ Key = 'AAD object'; Value = 'Microsoft Entra object' },
    @{ Key = 'AAD identity'; Value = 'Microsoft Entra identity' },
    @{ Key = 'AAD schema'; Value = 'Microsoft Entra schema' },
    @{ Key = 'AAD seamless single sign-on'; Value = 'Microsoft Entra seamless single sign-on' },
    @{ Key = 'AAD self-service password reset'; Value = 'Microsoft Entra self-service password reset' },
    @{ Key = 'AAD SSPR'; Value = 'Microsoft Entra SSPR' },
    @{ Key = 'AAD SSPR'; Value = 'Microsoft Entra SSPR' },
    @{ Key = 'AAD domain'; Value = 'Microsoft Entra domain' },
    @{ Key = 'AAD group'; Value = 'Microsoft Entra group' },
    @{ Key = 'AAD login'; Value = 'Microsoft Entra login' },
    @{ Key = 'AAD managed'; Value = 'Microsoft Entra managed' },
    @{ Key = 'AAD entitlement'; Value = 'Microsoft Entra entitlement' },
    @{ Key = 'AAD access review'; Value = 'Microsoft Entra access review' },
    @{ Key = 'AAD Identity Protection'; Value = 'Microsoft Entra ID Protection' },
    @{ Key = 'AAD pass-through'; Value = 'Microsoft Entra pass-through' },
    @{ Key = 'AAD password'; Value = 'Microsoft Entra password' },
    @{ Key = 'AAD Privileged Identity Management'; Value = 'Microsoft Entra Privileged Identity Management' },
    @{ Key = 'AAD registered'; Value = 'Microsoft Entra registered' },
    @{ Key = 'AAD reporting and monitoring'; Value = 'Microsoft Entra reporting and monitoring' },
    @{ Key = 'AAD enterprise app'; Value = 'Microsoft Entra enterprise app' },
    @{ Key = 'AAD cloud-only identities'; Value = 'Microsoft Entra cloud-only identities' },
    @{ Key = 'AAD Premium P1'; Value = 'Microsoft Entra ID P1' },
    @{ Key = 'AAD Premium P2'; Value = 'Microsoft Entra ID P2' },
    @{ Key = 'AAD F2'; Value = 'Microsoft Entra ID F2' },
    @{ Key = 'AAD Free'; Value = 'Microsoft Entra ID Free' },
    @{ Key = 'AAD for education'; Value = 'Microsoft Entra ID for education' },
    @{ Key = 'AAD work or school account'; Value = 'Microsoft Entra work or school account' },
    @{ Key = 'federated with AAD'; Value = 'federated with Microsoft Entra' },
    @{ Key = 'Hybrid AAD Join'; Value = 'Microsoft Entra hybrid join' }
)

$postTransforms = @(
    @{ Key = 'Microsoft Entra ID B2C'; Value = 'Azure AD B2C' },
    @{ Key = 'Microsoft Entra ID B2B'; Value = 'Microsoft Entra B2B' },
    @{ Key = 'ME-ID B2C'; Value = 'AAD B2C' },
    @{ Key = 'ME-ID B2B'; Value = 'Microsoft Entra B2B' },
    @{ Key = 'ME-IDSTS'; Value = 'AADSTS' },
    @{ Key = 'ME-ID Connect'; Value = 'Microsoft Entra Connect' }
    @{ Key = 'Microsoft Entra ID tenant'; Value = 'Microsoft Entra tenant' }
    @{ Key = 'Microsoft Entra ID organization'; Value = 'Microsoft Entra tenant' }
    @{ Key = 'Microsoft Entra ID account'; Value = 'Microsoft Entra account' }
    @{ Key = 'Microsoft Entra ID resources'; Value = 'Microsoft Entra resources' }
    @{ Key = 'Microsoft Entra ID admin'; Value = 'Microsoft Entra admin' }
    @{ Key = ' an Microsoft Entra'; Value = ' a Microsoft Entra' }
    @{ Key = '>An Microsoft Entra'; Value = '>A Microsoft Entra' }
    @{ Key = ' an ME-ID'; Value = ' a ME-ID' }
    @{ Key = '>An ME-ID'; Value = '>A ME-ID' }
    @{ Key = 'Microsoft Entra ID administration portal'; Value = 'Microsoft Entra administration portal' }
    @{ Key = 'Microsoft Entra ID Advanced Threat'; Value = 'Azure Advanced Threat' }
    @{ Key = 'Entra ID hybrid join'; Value = 'Entra hybrid join' }
    @{ Key = 'Microsoft Entra ID join'; Value = 'Microsoft Entra join' }
    @{ Key = 'ME-ID join'; Value = 'Microsoft Entra join' }
    @{ Key = 'Microsoft Entra ID service principal'; Value = 'Microsoft Entra service principal' }
    @{ Key = 'Download Microsoft Entra Connector'; Value = 'Download connector' }
    @{ Key = 'Microsoft Microsoft'; Value = 'Microsoft' }
)

# Sort the replacements by the length of the keys in descending order
$terminology = $terminology.GetEnumerator() | Sort-Object -Property { $_.Key.Length } -Descending
$postTransforms = $postTransforms.GetEnumerator() | Sort-Object -Property { $_.Key.Length } -Descending

# Get all resx files in the current directory and its subdirectories, ignoring .gitignored files.
Write-Host "Getting all resx files in the current directory and its subdirectories, ignoring .gitignored files."
$gitIgnoreFiles = Get-ChildItem -Path . -Filter .gitignore -Recurse
$targetFiles = Get-ChildItem -Path . -Include *.resx -Recurse

$filteredFiles = @()
foreach ($file in $targetFiles) {
    $ignoreFile = $gitIgnoreFiles | Where-Object { $_.DirectoryName -eq $file.DirectoryName }
    if ($ignoreFile) {
        $excludedPatterns = Get-Content $ignoreFile.FullName | Select-String -Pattern '^(?!#).*' | ForEach-Object { $_.Line }
        if ($excludedPatterns -notcontains $file.Name) {
            $filteredFiles += $file
        }
    }
    else {
        $filteredFiles += $file
    }
}

$scriptPath = $MyInvocation.MyCommand.Path
$filteredFiles = $filteredFiles | Where-Object { $_.FullName -ne $scriptPath }

# This command will get all the files with the extensions .resx in the current directory and its subdirectories, and then filter out those that match the patterns in the .gitignore file. The Resolve-Path cmdlet will find the full path of the .gitignore file, and the Get-Content cmdlet will read its content as a single string. The -notmatch operator will compare the full name of each file with the .gitignore content using regular expressions, and return only those that do not match.
Write-Host "Found $($filteredFiles.Count) files."

function Update-Terminology {
    param (
        [Parameter(Mandatory = $true)]
        [ref]$Content,
        [Parameter(Mandatory = $true)]
        [object[]]$Terminology
    )

    foreach ($item in $Terminology.GetEnumerator()) {
        $old = [regex]::Escape($item.Key)
        $new = $item.Value
        $toReplace = '(?<!(name=\"[^$]{1,100}|https?://aka.ms/[a-z0-9/-]{1,100}))' + $($old)

        # Replace the old terminology with the new one
        $Content.Value = $Content.Value -replace $toReplace, $new
    }
}

# Loop through each file
foreach ($file in $filteredFiles) {
    # Read the content of the file
    $content = Get-Content $file.FullName

    Write-Host "Processing $file"

    Update-Terminology -Content ([ref]$content) -Terminology $terminology
    Update-Terminology -Content ([ref]$content) -Terminology $postTransforms

    $newContent = $content -join "`n"
    if ($newContent -ne (Get-Content $file.FullName -Raw)) {
        Write-Host "Updating $file"
        # Write the updated content back to the file
        Set-Content -Path $file.FullName -Value $newContent
    }
}

```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/identity-fundamental-concepts"} -->
## ID とアクセスの管理 (IAM): 主要な概念と利点 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/identity-fundamental-concepts
- Service: entra / fundamentals
- Article date: 2026-06-18
- Summary: リソースを効果的にセキュリティで保護するために、認証、承認、ID プロバイダーなど、ID とアクセス管理 (IAM) の主要な概念について説明します。

この記事では、リソースを効果的にセキュリティで保護するために役立つ ID とアクセス管理 (IAM) の基本的な概念について説明します。

### ID とアクセス管理とは

ID とアクセス管理により、適切なユーザー、マシン、ソフトウェア コンポーネントが適切なリソースに適切なタイミングでアクセスできるようになります。 まず、ユーザー、マシン、またはソフトウェア コンポーネントは、自分が主張している本人または本物であることを証明します。 その後、ユーザー、コンピューター、またはソフトウェア コンポーネントは、特定のリソースへのアクセスを許可または拒否されます。

#### IAM の機能

IAM システムには、通常、次のコア機能が用意されています。

- **ID 管理: ID** 情報を作成、格納、および管理するプロセス。 ID プロバイダー (IdP) は、ユーザー ID の追跡と管理に使用されるソフトウェア ソリューション、およびそれらの ID に関連付けられているアクセス許可とアクセス レベルです。
- **ID フェデレーション**: 他の場所 (エンタープライズ ネットワーク内、インターネットまたはソーシャル ID プロバイダーなど) に既にパスワードを持っているユーザーがシステムにアクセスできるようにします。
- **ユーザーのプロビジョニングとプロビジョニング解除: どのユーザー**がどのリソースにアクセスできるかを指定したり、アクセス許可とアクセス レベルを割り当てたりするなど、ユーザー アカウントを作成および管理します。
- **ユーザーの認証: ユーザー**、コンピューター、またはソフトウェア コンポーネントが誰であるか、何であるかを確認します。
- **ユーザーの承認: 権限**のあるツールへのアクセスの正確なレベルと種類がユーザーに付与されていることを確認します。
- **アクセス制御**: どのリソースにアクセスできるかを決定するプロセス。 このプロセスには、ユーザー ロールとアクセス許可の定義と、認証と承認のメカニズムの設定が含まれます。 アクセス制御は、システムとデータへのアクセスを規制します。
- **レポートと監視**: コンプライアンスを確保し、セキュリティ リスクを評価するためのプラットフォーム アクション (サインイン時間、アクセスされたシステム、認証の種類など) に関するレポートを生成します。

### アイデンティティ

デジタル ID は、システム内の個人、ソフトウェア コンポーネント、コンピューター、資産、またはリソースを表す一意の識別子または属性のコレクションです。 識別子には次の値を使用できます。

- メール アドレス
- サインイン資格情報 (ユーザー名/パスワード)
- 銀行口座番号
- 政府発行の ID
- MAC アドレスまたは IP アドレス

ID は、リソースへのアクセスの認証と承認、通信の有効化、トランザクションの容易化、およびその他の目的への対応に使用されます。

ID は、次の 4 種類に分類されます。

- **人間の ID** は、従業員 (社内および現場担当者) や外部ユーザー (顧客、コンサルタント、ベンダー、パートナー) を含むユーザーを表します。
- **ワークロード ID** は、アプリケーション、サービス、スクリプト、コンテナーなどのソフトウェア ワークロードを表します。
- **デバイス ID は** 、デスクトップ コンピューター、携帯電話、IoT センサー、IoT マネージド デバイスなどのデバイスを表します。 人間の ID とは異なります。
- **エージェント ID は、** 自律的に、またはユーザーに代わって動作する AI エージェントを表します。 [Microsoft Entra エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-microsoft-entra-agent-id)は、エンタープライズ規模でこれらの ID を認証、承認、管理、保護するための専用の ID コンストラクトを提供します。

### 認証

認証では、ユーザー、ソフトウェア コンポーネント、またはハードウェア デバイスに対して、自分の身元を確認したり、誰であるか、何を主張しているのかを証明したりするために、資格情報を要求します。 認証には通常、ユーザー名とパスワード、指紋、証明書、ワンタイム パスコードなどの資格情報が必要です。 認証は *AuthN* と短縮される場合があります。

多要素認証 (MFA) は、ユーザーが自分の ID を確認するために複数の証拠を提供する必要があるセキュリティ対策です。 たとえば、次のようになります。

- パスワードなど、ユーザーが知っているもの。
- バッジのように、彼らが持っているもの。
- 生体認証 (指紋や顔など) のようなものです。

シングル サインオン (SSO) を使用すると、ユーザーは ID を 1 回認証し、後で同じ ID に依存するさまざまなリソースにアクセスするときにサイレント認証を行うことができます。 認証後、IAM システムは、ユーザーが使用できる他のリソースの ID 信頼のソースとして機能します。 複数の個別のターゲット システムにサインオンする必要がなくなります。

### 認証

承認により、ユーザー、コンピューター、またはソフトウェア コンポーネントに特定のリソースへのアクセス権が付与されていることが検証されます。 承認は *AuthZ* と短縮される場合があります。

### 認証と承認

認証と承認という用語は、多くの場合、ユーザーが単一のエクスペリエンスのように見えるため、同じ意味で使用されることがあります。 実際には、次の 2 つの個別のプロセスです。

- 認証は、ユーザー、マシン、またはソフトウェア コンポーネントの ID を証明します。
- 認可は、ユーザー、マシン、またはソフトウェア コンポーネントの特定のリソースへのアクセスを許可または拒否します。

[Image: 認証と承認を並べて示す図。]

認証と承認の概要を次に示します。

| 認証 | 認証 |
| --- | --- |
| 有効な資格情報を提供するエンティティのみにアクセスを許可するゲートキーパーと考えることができます。 | 適切なクリアランスを持つエンティティのみが特定の領域に入るようにするガードと考えることができます。 |
| ユーザー、コンピューター、またはソフトウェアが主張している本人または本物であることを確認します。 | ユーザー、コンピューター、またはソフトウェアが特定のリソースへのアクセスを許可されるかどうかを判断します。 |
| ユーザー、コンピューター、またはソフトウェアに検証可能な資格情報 (パスワード、生体認証識別子、証明書など) を要求します。 | ユーザー、コンピューター、またはソフトウェアが持つアクセス権のレベルを決定します。 |
| 承認の前に行われます。 | 承認が成功した後に行われます。 |
| 情報は ID トークンで転送されます。 | 情報はアクセス トークンで転送されます。 |
| 多くの場合、OpenID Connect (OIDC) プロトコル (OAuth 2.0 プロトコル上に構築) または SAML プロトコルを使用します。 | 多くの場合、OAuth 2.0 プロトコルを使用します。 |

詳細については、「[認証と承認](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-vs-authorization)」を参照してください。

#### 例

ホテルに宿泊しようとしているとします。 認証と承認は、ホテルの建物のセキュリティ システムと考えることができます。 ユーザーはホテルに滞在する人々であり、リソースはユーザーが使用する部屋またはエリアです。 ホテル スタッフは別のタイプのユーザーです。

ホテルに宿泊する場合は、まずフロントへ行って「認証プロセス」を開始します。身分証明書とクレジットカードを提示すると、受付がオンライン予約と照合します。 受付係が自分が誰であるかを確認した後、受付は割り当てられている部屋へのアクセス許可を付与します。 あなたはカードキーを受け取り、部屋に行くことができるようになります。

[Image: ホテルのカードキーを取得するために身分証明書を提示している人物を示す図。]

ホテルの客室やその他のエリアへのドアには、カードキー センサーが備えられています。 センサーの前でキーカードをスワイプすることは、"承認プロセス" です。キーカードを使用すると、ホテルの部屋やホテルのエクササイズルームなど、アクセスが許可されている部屋へのドアのみを開くことができます。 カードキーをスワイプしてホテルの他の客室に入ろうとした場合、アクセスは拒否されます。

演習室や特定のゲスト ルームへのアクセスなど、個々の [アクセス許可](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions?context=/active-directory/roles/context/ugr-context)は [ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/concept-understand-roles)に収集され、個々のユーザーに付与できます。 ホテルに滞在している間、ホテルパトロンの役割が与えられます。 ホテルのルーム サービスのスタッフには、ホテルのルーム サービスのロールが付与されます。 このロールにより、すべてのホテルの客室 (ただし、午前 11 時から午後 4 時までの間のみ)、ランドリー ルーム、各フロアの備品保管庫にアクセスできます。

[Image: ユーザーがカードキーを使用して部屋にアクセスする様子を示す図。]

### ID プロバイダー

ID プロバイダーは、ID 情報を作成、管理します。 認証、承認、監査サービスを提供します。

[Image: クラウド、ワークステーション、モバイル、データベースのアイコンで囲まれた ID アイコンを示す図。]

先進認証では、認証サービスを含むすべてのサービスが中央 ID プロバイダーによって提供されます。 ID プロバイダーは、サーバーでユーザーを認証するために使用される情報を一元的に格納および管理します。

中央 ID プロバイダーを使用すると、組織は認証ポリシーと承認ポリシーを確立し、ユーザーの動作を監視し、疑わしいアクティビティを特定し、悪意のある攻撃を減らすことができます。

[Microsoft Entra](https://learn.microsoft.com/ja-jp/entra/) は、クラウドベース ID プロバイダーの一例です。 その他の例としては、X、Google、Amazon、LinkedIn、GitHub があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/inaccessible-tenant"} -->
## アクセスできないテナントのトラブルシューティング - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/inaccessible-tenant
- Service: entra / fundamentals
- Article date: 2025-01-15
- Summary: テナントのブロックを解除する方法に関する手順。

### 概要

構成済みのテナントが使用されなくなった場合でも、組織のコストが発生する可能性があります。 非アクティブのためテナントにアクセスできないようにすると、不要な費用を削減できます。 この記事では、アクセスできないテナント、再アクティブ化、および管理者とアプリケーション開発者の両方のガイダンスを処理する方法について説明します。

テナントにアクセスしようとすると、次の例のようなメッセージが表示されます。

非アクティブのためテナントにアクセスできない場合は、エラーメッセージ `Error message: AADSTS5000225: This tenant has been blocked due to inactivity. To learn more about ...` が表示されることが予期されます。

[Image: 非アクティブのためにテナントのアクセスがブロックされたときのエラーを示すスクリーンショット。]

管理者は、テナントが非アクティブ状態になってから 20 日以内にテナントを再アクティブ化するよう要求できます。 この状態が 20 日を超えるテナントは削除されます。

テナントの目標と環境内のロールに応じて、適切な手順を実行します。

### 管理者

テナントを再アクティブ化する必要がある場合:

- Microsoft にお問い合わせください。 [グローバル サポートの電話番号](https://support.microsoft.com/topic/global-customer-service-phone-numbers-c0389ade-5640-e588-8b0e-28de8afeb3f2)を参照してください。
- 既存の自分のケースが処理中で、そのケースに関する決定の応答が返ってくるまでは、別のサポート要求を送信しないでください。

テナントを再アクティブ化する予定がない場合:

- テナントは、非アクティブのためアクセスできなくなった 20 日後に削除され、復旧できません。
- [Microsoft のデータ保護ポリシーを確認します](https://www.microsoft.com/trust-center/privacy/data-management#leave)。

### アプリケーションの所有者/開発者

- テナントが再アクティブ化されるまで、この非アクティブ化されたテナントに送信される認証要求の数を最小限に抑えます。
- 別のサポート要求を送信しない。 決定が行われたら、Microsoft から連絡があります。
- Microsoft の[データ保護ポリシー](https://www.microsoft.com/trust-center/privacy/data-management#leave)を確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/licensing"} -->
## Microsoft Entra ライセンス - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing
- Service: entra / fundamentals
- Article date: 2026-06-18
- Summary: この記事では、Microsoft Entra機能のライセンス要件について説明します。

### 概要

この記事では、Microsoft Entra 製品ファミリのライセンス オプションについて説明します。 これは、セキュリティの意思決定者、ID およびネットワーク アクセス管理者、および組織のMicrosoft Entra ソリューションを検討している IT プロフェッショナルを対象としています。

注

ライセンス割り当ての問題のトラブルシューティングを行っている場合は、「 [グループベースのライセンスの管理エラー」を](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/manage-group-licenses?view=o365-worldwide&preserve-view=true#manage-group-based-licensing-errors)参照してください。

### Microsoft Entra ライセンス オプション

Microsoft Entraは、ニーズに最も適したパッケージを選択できるいくつかのライセンス オプションで利用できます。

注

このページのライセンス オプションは包括的ではありません。 さまざまなオプションの詳細については、[Microsoft Entra価格ページ](https://www.microsoft.com/security/business/microsoft-entra-pricing)および[Microsoft 365 Enterpriseプランと価格に関するページ](https://www.microsoft.com/microsoft-365/enterprise/microsoft365-plans-and-pricing)で確認できます。

**Microsoft Entra ID無料**: Microsoft Azure、Microsoft 365などのMicrosoftクラウド サブスクリプションに含まれます。

**Microsoft Entra ID P1**: Microsoft Entra ID P1 はスタンドアロン製品として使用できます。 また、企業のお客様向けの次のオファーにも含まれています。

- Microsoft 365 E3、E5、E7
- Microsoft 365 F1、F3
- Enterprise Mobility + Security E3

Entra ID P1 は、中小企業向けのMicrosoft 365 Business Premiumにも含まれています。

**Microsoft Entra ID P2**: Microsoft Entra ID P2 はスタンドアロン製品として使用できます。 また、企業のお客様向けの次のオファーにも含まれています。

- Microsoft 365 E5、E7
- Microsoft Defender スイート (以前のMicrosoft 365 E5 Security)
- Microsoft Defender スイート FLW
- Microsoft Defender + FLW Purview スイート
- Enterprise Mobility + Security E5

Entra ID P2 は、Microsoft 365 Business Premium、Microsoft Defender、Purview Suites for Microsoft 365 Business Premium のMicrosoft Defender スイートにも含まれています中小企業向け。 詳細については、[中小企業向けのMicrosoft 365の価格に関するページを](https://www.microsoft.com/security/pricing/small-medium-business/security-add-on-plans)参照してください。

**Microsoft Entra スイート**: Microsoft Entra製品を組み合わせて、従業員のアクセスをセキュリティで保護します。 これにより、管理者は、最小限の特権accessを確保しながら、クラウドでもオンプレミスでも、どこからでもアプリやリソースに安全なaccessを提供できます。 Microsoft Entra ID P1 サブスクリプションまたは Microsoft Entra ID P1is を含むパッケージが必要です。 Microsoft Entra スイートはスタンドアロン プランとして使用するか、Microsoft 365 E7 に含まれています。 スイートには次の 5 つの製品が含まれています。

- マイクロソフト エントラ プライベート アクセス (Microsoft Entra Private Access)
- マイクロソフト エントラ インターネット アクセス
- Microsoft Entra ID ガバナンス
- Microsoft Entra ID 保護（マイクロソフト エントラ ID 保護）
- Microsoft Entra Verified ID (Premium 機能)

重要

ユーザーとグループのライセンスの割り当ては、Microsoft 365 管理 センターで管理されます。 ユーザーとグループにライセンスを割り当てる方法または割り当て解除する方法の詳細については、次の記事を参照してください: -  Microsoft 365 管理センター

### アプリのプロビジョニング

Microsoft Entra アプリケーション プロキシには、Microsoft Entra ID P1 または P2 ライセンスが必要です。 ライセンスの詳細については、「Microsoft Entra pricing.

### 認証

次の表に、さまざまなバージョンのMicrosoft Entra IDで認証に使用できる機能の一覧を示します。 ユーザー サインインのセキュリティ保護に必要なものを詳しく検討し、その要件を満たす方法を決定します。 たとえば、Microsoft Entra ID Free では多要素認証でセキュリティの既定値が提供されますが、認証プロンプトにはテキストや音声通話を含むMicrosoft Authenticatorのみを使用できます。 Authenticator がユーザーの個人のデバイスにインストールされていることを保証できない場合、この方法は制約を受けるかもしれません。

注

Microsoft 365 E7 にはMicrosoft Entra スイートが含まれており、この表に示されているすべての Microsoft Entra ID P2 認証機能が提供されます。

| 機能 | Microsoft Entra ID 無料 - セキュリティの既定値 (すべてのユーザーに対して有効) | Microsoft Entra ID無料 - グローバル管理者のみ | オフィス365 | Microsoft Entra ID P1 | Microsoft Entra ID P2 |
| --- | --- | --- | --- | --- | --- |
| MFA を使用Microsoft Entraテナント管理者アカウントを保護する | ✅ | ✅ (*Microsoft Entra グローバル管理者* アカウントのみ) | ✅ | ✅ | ✅ |
| モバイル アプリを 2 番目の要素にする | ✅ | ✅ | ✅ | ✅ | ✅ |
| 音声通話を 2 番目の要素にする |  |  | ✅ | ✅ | ✅ |
| SMS を 2 番目の要素にする |  | ✅ | ✅ | ✅ | ✅ |
| 検証方法の管理制御 |  | ✅ | ✅ | ✅ | ✅ |
| 不正アクセスのアラート |  |  |  | ✅ | ✅ |
| MFA レポート |  |  |  | ✅ | ✅ |
| 音声通話のカスタムあいさつ文 |  |  |  | ✅ | ✅ |
| 音声通話のカスタム発信元 ID |  |  |  | ✅ | ✅ |
| 信頼できる IP |  |  |  | ✅ | ✅ |
| 信頼済みデバイスの MFA の記憶 |  | ✅ | ✅ | ✅ | ✅ |
| オンプレミス アプリケーション用の MFA |  |  |  | ✅ | ✅ |
| 条件付きアクセス |  |  |  | ✅ | ✅ |
| リスクベースの条件付きアクセス |  |  |  |  | ✅ |
| セルフサービス パスワード リセット (SSPR) | ✅ | ✅ | ✅ | ✅ | ✅ |
| 書き戻しを使用した SSPR |  |  |  | ✅ | ✅ |

### マネージド ID

Azure リソースにマネージド ID を使用するためのライセンス要件はありません。 Azure リソースのマネージド ID は、Microsoft Entra認証をサポートするリソースに接続するときにアプリケーションが使用する自動的にマネージド ID を提供します。 マネージド ID を使用する利点の 1 つは、資格情報を管理する必要がなく、追加料金なしで使用できることです。 詳細については、「[Azure リソースのマネージド ID とは](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)」を参照>。

### Microsoft Entra エージェント ID

Microsoft Entra エージェント IDは、エージェント ID とエージェント ID ブループリントを作成および管理するためのプラットフォームを提供する、Microsoft Entra内の製品です。 エージェント ID は、すべてのMicrosoft Entraユーザーが使用できます。

[Microsoft Agent 365](https://learn.microsoft.com/ja-jp/microsoft-agent-365/overview) を使用すると、エージェントはMicrosoft 365サービスとエンタープライズ ワークフロー全体で動作できます。これには、ユーザーごとに **Microsoft Agent 365** ライセンスが必要です。 価格の詳細については、「[Microsoft Agent 365 のプランと価格](https://www.microsoft.com/microsoft-agent-365#plans-and-pricing)を参照してください。

Microsoft Entraセキュリティ機能をエージェントに拡張するには、Microsoft Agent 365 が必要です。 エージェント 365 は Microsoft 365 E7 に含まれており、Microsoft E5/A5/Business Premium (または Microsoft Defender スイート + Microsoft Purview スイート) のアドオンとして使用できます。 詳細については、 [最新の Agent 365 製品条項を参照してください](https://www.microsoft.com/licensing/terms/productoffering/Agent365/EAEAS#clause-2755-h3-1)。

### Microsoft Entra ID ガバナンス

次の表に、メンバー ユーザーのMicrosoft Entra ID ガバナンス機能のライセンス要件を示します。 Microsoft Entra スイートには、Microsoft Entra ID ガバナンスのすべての機能が含まれています。 Microsoft 365 E7 には、Entra スイートおよび Agent 365 を通じてのすべての ID ガバナンス機能も含まれています。 エンタイトルメント管理、Access レビュー、ライフサイクル ワークフローのライセンス情報とライセンス シナリオの例を次の表に示します。

#### ライセンス別の機能

次の表に、各ライセンスで使用できる ID ガバナンスに関連付けられている機能を示します。 その他の機能の詳細については、「[Microsoft Entra プランと価格](https://www.microsoft.com/security/business/microsoft-entra-pricing)を参照してください。 すべての機能がすべてのクラウドで利用できるわけではありません。Azure Governmentについては、[Microsoft Entra機能の可用性](https://learn.microsoft.com/ja-jp/entra/identity/authentication/feature-availability)に関する記事を参照してください。

| 機能 | Free | Microsoft Entra ID P1 | Microsoft Entra ID P2 | Microsoft Entra ID ガバナンス | Microsoft Entra スイート | Microsoft エージェント 365 |
| --- | --- | --- | --- | --- | --- | --- |
| **プロビジョニング** |  |  |  |  |  |  |
| [API 駆動型のプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-concepts) |  | ✅ | ✅ | ✅ | ✅ |  |
| [人事主導のプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/what-is-hr-driven-provisioning) |  | ✅ | ✅ | ✅ | ✅ |  |
| [アカウントの検出](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-to-account-discovery) |  |  |  | ✅ | ✅ |  |
| [SaaS アプリへの自動ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list) | ✅ | ✅ | ✅ | ✅ | ✅ |  |
| [SaaS アプリへの自動グループ プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list) |  | ✅ | ✅ | ✅ | ✅ |  |
| [オンプレミス アプリへの自動プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture) |  | ✅ | ✅ | ✅ | ✅ |  |
| [ユーザーのテナント間同期 (同じクラウド)](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure?pivots=cross-cloud-synchronization) |  | ✅ | ✅ | ✅ | ✅ |  |
| [グループのテナント間同期 (同じクラウド)](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure?pivots=cross-cloud-synchronization) |  |  |  | ✅ | ✅ |  |
| [クラウド間同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure?pivots=cross-cloud-synchronization) |  |  |  | ✅ | ✅ |  |
| **ライフサイクル ワークフロー (LCW)** | **無料** | **Microsoft Entra ID P1** | **Microsoft Entra ID P2** | **Microsoft Entra ID ガバナンス** | **Microsoft Entra スイート** | **Microsoft エージェント 365** |
| [ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows) |  |  |  | ✅ | ✅ |  |
| [LCW + カスタム拡張機能 (Logic Apps)](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-extensibility) |  |  |  | ✅ | ✅ |  |
| [LCW + エージェント スポンサーシップ タスク](https://learn.microsoft.com/ja-jp/entra/id-governance/agent-sponsor-tasks) |  |  |  |  |  | ✅ |
| **アクセスレビュー (AR)** | **無料** | **Microsoft Entra ID P1** | **Microsoft Entra ID P2** | **Microsoft Entra ID ガバナンス** | **Microsoft Entra スイート** | **Microsoft エージェント 365** |
| AR - 以前は Microsoft Entra ID P2 |  |  | ✅ | ✅ | ✅ |  |
| [AR - グループ用 PIM (プレビュー版)](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review-pim-for-groups) |  |  |  | ✅ | ✅ |  |
| [AR - レビューでアクティブなユーザーがいない非アクティブなユーザーを対象にしたレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review#scope) |  |  |  | ✅ | ✅ |  |
| [AR - レビュー対象をアクティブ ユーザーと非アクティブ ユーザーに限定し、非アクティブ ユーザーについてはレビュー担当者が判断を下せる支援機能を提供](https://learn.microsoft.com/ja-jp/entra/id-governance/review-recommendations-access-reviews#inactive-user-recommendations) |  |  | ✅ | ✅ | ✅ |  |
| [AR - 機械学習支援アクセスの認証とレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/review-recommendations-access-reviews#user-to-group-affiliation) |  |  |  | ✅ | ✅ |  |
| [AR - カタログアクセスレビュー (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/catalog-access-reviews) |  |  |  | ✅ | ✅ |  |
| [AR - カスタム データ提供リソース (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/custom-data-resource-access-reviews) |  |  |  | ✅ | ✅ |  |
| **エンタイトルメント管理 (EM)** | **無料** | **Microsoft Entra ID P1** | **Microsoft Entra ID P2** | **Microsoft Entra ID ガバナンス** | **Microsoft Entra スイート** | **Microsoft エージェント 365** |
| EM - 以前は Microsoft Entra ID P2 |  |  | ✅ | ✅ | ✅ |  |
| [EM - アクセスパッケージにユーザーを割り当てる](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#allow-users-service-principals-and-agent-identities-in-your-directory-to-request-the-access-package) |  |  | ✅ | ✅ | ✅ |  |
| [EM - アクセス パッケージに割り当てられたエージェントとサービス プリンシパル](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#allow-users-service-principals-and-agent-identities-in-your-directory-to-request-the-access-package) |  |  |  |  |  | ✅ |
| [EM - ユーザーは自分でaccessを要求します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview) |  |  | ✅ | ✅ | ✅ |  |
| [EM - 管理者がユーザーの割り当てを直接割り当てる (ゲストを含む)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#directly-assign-an-identity) |  |  | ✅ | ✅ | ✅ |  |
| [EM - 管理者がエージェントとサービス プリンシパルを直接割り当てる](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#directly-assign-any-identity) |  |  |  |  |  | ✅ |
| [EM - 管理者は、ディレクトリにまだ存在しないユーザーの電子メール アドレスを使用して、ユーザーを直接割り当てます](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#directly-assign-any-identity) |  |  |  | ✅ | ✅ |  |
| [EM - 従業員に代わって要求するマネージャー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-behalf) |  |  |  | ✅ | ✅ |  |
| [EM - 所有者とスポンサーがエージェントまたはサービス プリンシパルに代わってアクセスを要求する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-behalf#scenarios-for-requesting-on-behalf-of-agent-identities) |  |  |  |  |  | ✅ |
| **EM - サポートされているリソース** | **無料** | **Microsoft Entra ID P1** | **Microsoft Entra ID P2** | **Microsoft Entra ID ガバナンス** | **Microsoft Entra スイート** | **Microsoft エージェント 365** |
| [EM - access パッケージ内のグループとチーム](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-resources#add-a-group-or-team-resource-role) |  |  | ✅ | ✅ | ✅ |  |
| [EM - access パッケージの対象となるグループの所有権とメンバーシップ (グループ用 PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-eligible) |  |  |  | ✅ | ✅ |  |
| [EM - access パッケージ内のアプリケーション](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-resources#add-an-application-resource-role) |  |  | ✅ | ✅ | ✅ |  |
| [EM - アクセス パッケージ内のSharePointサイト](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-resources#add-a-sharepoint-site-resource-role) |  |  | ✅ | ✅ | ✅ |  |
| [EM - Microsoft Entra 役割 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-roles) |  |  |  | ✅ | ✅ |  |
| [EM - SAP Identity Access Governance (IAG) ビジネス ロール (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-sap-integration) |  |  |  | ✅ | ✅ |  |
| [EM - アクセス パッケージ内の API アクセス許可](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-resources#add-an-api-permission) |  |  |  |  |  | ✅ |
| **EM - 承認オプション** | **無料** | **Microsoft Entra ID P1** | **Microsoft Entra ID P2** | **Microsoft Entra ID ガバナンス** | **Microsoft Entra スイート** | **Microsoft エージェント 365** |
| [EM - アクションが実行されない場合は、代替承認者による複数ステージの承認](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-approval-policy) |  |  | ✅ | ✅ | ✅ | ✅ |
| [EM - 特定の承認者](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-approval-policy) |  |  | ✅ | ✅ | ✅ | ✅ |
| [EM - 承認者としてのマネージャー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-approval-policy) |  |  | ✅ | ✅ | ✅ |  |
| [EM - 承認者としての組織内部のスポンサー (担当者が所属する組織からの)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-approval-policy) |  |  | ✅ | ✅ | ✅ |  |
| [EM - 承認者としての外部スポンサー (担当者の接続された組織から)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-approval-policy) |  |  | ✅ | ✅ | ✅ |  |
| [EM - 承認者としてのスポンサー (割り当てられたユーザーのプロファイルから)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-approval-policy) |  |  |  | ✅ | ✅ |  |
| [EM - 承認者としてのエージェント スポンサー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-approval-policy) |  |  |  |  |  | ✅ |
| [EM - カスタム拡張機能を使用して承認要件を外部で決定する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-dynamic-approval) |  |  |  | ✅ | ✅ |  |
| [EM - 承認のために追加の要求者情報を収集します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-approval-policy#collect-additional-requestor-information-for-approval) |  |  | ✅ | ✅ | ✅ |  |
| **EM - ライフサイクル** | **無料** | **Microsoft Entra ID P1** | **Microsoft Entra ID P2** | **Microsoft Entra ID ガバナンス** | **Microsoft Entra スイート** | **Microsoft エージェント 365** |
| [EM - access パッケージの割り当ての有効期限](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-lifecycle-policy) |  |  | ✅ | ✅ | ✅ | ✅ |
| [EM - 外部ユーザーのライフサイクルを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users#manage-the-lifecycle-of-external-users) |  |  | ✅ | ✅ | ✅ |  |
| [EM - ゲストを管理対象としてマークします](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-manage-lifecycle) |  |  |  | ✅ | ✅ |  |
| **EM - その他の機能** | **無料** | **Microsoft Entra ID P1** | **Microsoft Entra ID P2** | **Microsoft Entra ID ガバナンス** | **Microsoft Entra スイート** | **Microsoft エージェント 365** |
| [EM - 職務の分離](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-incompatible) |  |  | ✅ | ✅ | ✅ |  |
| [EM - カスタム拡張機能 (Logic Apps)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logic-apps-integration) |  |  |  | ✅ | ✅ |  |
| [EM - 自動割り当てポリシー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy) |  |  |  | ✅ | ✅ |  |
| [EM - 検証済み ID の統合](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-verified-id-settings) |  |  |  | ✅ | ✅ |  |
| [EM - Microsoft Entra ID 保護 の統合](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-configure-id-protection-approvals) |  |  |  | ✅ | ✅ |  |
| [EM - Microsoft Purview インサイダー リスク管理統合](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-configure-insider-risk-management-approvals) |  |  |  | ✅ | ✅ |  |
| [EM - 条件付きアクセス範囲](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users#review-your-conditional-access-policies) |  |  | ✅ | ✅ | ✅ |  |
| **マイ アクセス** | **無料** | **Microsoft Entra ID P1** | **Microsoft Entra ID P2** | **Microsoft Entra ID ガバナンス** | **Microsoft Entra スイート** | **Microsoft エージェント 365** |
| [My Access ポータル](https://learn.microsoft.com/ja-jp/entra/id-governance/my-access-portal-overview) |  |  | ✅ | ✅ | ✅ | ✅ |
| [EM - マイ アクセス検索](https://learn.microsoft.com/ja-jp/entra/id-governance/my-access-portal-overview) |  |  | ✅ | ✅ | ✅ | ✅ |
| [EM - マイ アクセスで推奨されるアクセス パッケージ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-suggested-access-packages) |  |  |  | ✅ | ✅ |  |
| [EM - 要求者が \[マイアクセス (プレビュー)\] で承認者の詳細を表示できるかどうかを設定します](https://learn.microsoft.com/ja-jp/entra/id-governance/my-access-approver-details) |  |  |  | ✅ | ✅ |  |
| [EM - マイアクセスで承認を委任 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/my-access-portal-overview) |  |  |  | ✅ | ✅ |  |
| **Privileged Identity Management (PIM)** | **無料** | **Microsoft Entra ID P1** | **Microsoft Entra ID P2** | **Microsoft Entra ID ガバナンス** | **Microsoft Entra スイート** | **Microsoft エージェント 365** |
| [Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) |  |  | ✅ | ✅ | ✅ |  |
| [PIM グループ用](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/concept-pim-for-groups) |  |  | ✅ | ✅ | ✅ |  |
| [PIM 条件付きアクセス制御](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings#on-activation-require-microsoft-entra-conditional-access-authentication-context) |  |  | ✅ | ✅ | ✅ |  |
| [PIM - ロールアクティブ化のカスタム拡張機能 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/privileged-identity-management-custom-extensions) |  |  |  | ✅ | ✅ |  |
| **その他** | **無料** | **Microsoft Entra ID P1** | **Microsoft Entra ID P2** | **Microsoft Entra ID ガバナンス** | **Microsoft Entra スイート** | **Microsoft エージェント 365** |
| [ID ガバナンス ダッシュボード](https://learn.microsoft.com/ja-jp/entra/id-governance/governance-dashboard) |  | ✅ | ✅ | ✅ | ✅ |  |
| [分析情報とレポート - 非アクティブなゲスト アカウント](https://learn.microsoft.com/ja-jp/entra/identity/users/clean-up-stale-guest-accounts) |  |  |  | ✅ | ✅ |  |
| [Conditional Access - 使用条件の確認](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/terms-of-use) |  | ✅ | ✅ | ✅ | ✅ |  |

#### エンタイトルメント管理

この機能を使用するには、組織のメンバー ユーザーのMicrosoft Entra ID ガバナンス サブスクリプションが必要です。 この機能内の一部の機能は、Microsoft Entra ID P2 サブスクリプションで動作できます。 この機能内の一部の機能には、ゲスト課金が必要です。

##### ライセンスのシナリオ例

必要なライセンス数の決定に役立つライセンスのシナリオ例をいくつか以下に示します。

| シナリオ | 計算 | ライセンス数 |
| --- | --- | --- |
| Woodgrove Bank の ID ガバナンス管理者が初期カタログを作成します。 ポリシーの 1 つは、**すべての従業員** (2,000 人の従業員) が特定のaccess パッケージセットを要求できることを指定します。 150 人の従業員がアクセスパッケージを申請します。 | 2,000 人の従業員がアクセスパッケージを要求することができる | 二千 |
| Woodgrove Bank の ID ガバナンス管理者が初期カタログを作成します。 ** Sales 部門のメンバー全員** (350 人の従業員) に特定のaccess パッケージのセットにaccessを付与する自動割り当てポリシーを作成します。 access パッケージには 350 人の従業員が自動的に割り当てられます。 | 350 人の従業員にライセンスが必要です。 | 3:51 |

#### Accessのレビュー

この機能を使用するには、組織のメンバー ユーザーのMicrosoft Entra ID ガバナンス サブスクリプションが必要です。これには、アクセス権を確認している従業員やアクセス権をレビューしているすべての従業員が含まれます。 この機能内の一部の機能は、Microsoft Entra ID P2 サブスクリプションで動作する場合があります。 この機能内の一部の機能には、ゲスト課金が必要です。

##### ライセンスのシナリオ例

必要なライセンス数の決定に役立つライセンスのシナリオ例をいくつか以下に示します。

| シナリオ | 計算 | ライセンス数 |
| --- | --- | --- |
| 管理者は、75 人のメンバー ユーザーと 1 人のグループ所有者を持つグループ A のaccess レビューを作成し、グループ所有者をレビュー担当者として割り当てます。 | レビュー担当者としてのグループ所有者のライセンスが 1 つ、75 人のユーザーに対して 75 ライセンス。 | 76 |
| 管理者は、500 人のメンバー ユーザーと 3 人のグループ所有者を含むグループ B のaccess レビューを作成し、3 人のグループ所有者をレビュー担当者として割り当てます。 | ユーザーには 500 ライセンス、レビュー担当者としてグループ所有者ごとに 3 つのライセンス。 | 503 |
| 管理者は、500 人のメンバー ユーザーを含むグループ B のaccess レビューを作成します。 自己レビューにします。 | 自己レビュー担当者としての各ユーザー用に 500 ライセンス | 5:00 |
| 管理者は、50 人のメンバー ユーザーを含むグループ C のaccess レビューを作成します。 自己レビューにします。 | 自己レビュー担当者としての各ユーザー用に 50 ライセンス。 | 50 |
| 管理者は、6 人のメンバー ユーザーを含むグループ D のaccess レビューを作成します。 自己レビューにします。 | 自己レビュー担当者としての各ユーザー用に 6 ライセンス。 追加のライセンスは必要ありません。 | 6 |

#### ライフサイクル ワークフロー

ライフサイクル ワークフローのMicrosoft Entra ID ガバナンス ライセンスを使用すると、次のことができます。

- 合計 50 ワークフローまで作成、管理、削除できます。
- オンデマンドおよびスケジュールされたワークフロー実行をトリガーします。
- 既存のタスクを管理および構成して、ニーズに固有のワークフローを作成します。
- ワークフローで使用するカスタム タスク拡張機能を最大 100 個作成します。

この機能を使用するには、組織のメンバー ユーザーのMicrosoft Entra ID ガバナンス サブスクリプションが必要です。 この機能内の一部の機能には、ゲスト課金が必要です。

##### ライセンスのシナリオ例

| シナリオ | 計算 | ライセンス数 |
| --- | --- | --- |
| ライフサイクル ワークフロー管理者はワークフローを作成し、Marketing 部門の新入社員をマーケティング チーム グループに追加します。 このワークフローを使用して、250 人の新規採用メンバー ユーザーがマーケティング チーム グループに 1 回割り当てられます。 他の 150 人の新規採用メンバー ユーザーは、同じ年の後半にこのワークフローを介してマーケティング チーム グループに割り当てられます。 | ライフサイクル ワークフロー管理者に 1 ライセンス、ユーザーに 400 ライセンス。 | 401 |
| ライフサイクル ワークフロー管理者は、雇用最終日の前に従業員のグループに事前オフボードするためのワークフローを作成します。 事前オフボードされるユーザーの範囲は、一度に 40 人です。 ライセンスを取得したユーザー 40 名をオフボードしました。 これで、これらの 40 個のライセンスを再割り当てし、今年後半にさらに 10 個のライセンスを、オフボード前の 50 人のユーザーに割り当てることができます。 | ユーザーに 50 ライセンス、ライフサイクル ワークフロー管理者に 1 ライセンス。 | 51 |

### Microsoft Entra テナント ガバナンス

次の表は、各ライセンスで使用できるテナント ガバナンス機能を示しています。

注

Microsoft Entra P1 は、Microsoft 365 E3とMicrosoft 365 Business Premiumにも含まれています。 Microsoft Entra P2もMicrosoft 365 E5に含まれています。 Microsoft Entra ID ガバナンスは、Microsoft Entra スイートおよびMicrosoft 365 E7 にも含まれています。

#### 構成管理

| 機能 | Free | Microsoft Entra P1 | Microsoft Entra P2 | Microsoft Entra ID ガバナンス |
| --- | --- | --- | --- | --- |
| シングルテナント構成監視とドリフトレポート |  | ✅ テナントあたり 1 日あたり最大 30 台のモニターと 800 個の構成リソース | ✅ テナントあたり 1 日あたり最大 30 台のモニターと 800 個の構成リソース | ✅ 基本容量に加えて、ライセンスごとに 1 日あたり 10 個の追加の構成リソース |
| シングルテナント用設定スナップショット |  | ✅ テナントあたり 1 か月あたり最大 20,000 個のリソースと 12 個のアクティブなスナップショット ジョブ | ✅ テナントあたり 1 か月あたり最大 20,000 個のリソースと 12 個のアクティブなスナップショット ジョブ | ✅ 基本容量に加えて、ライセンスごとに 1 か月あたり 35 個の追加の構成リソース |

#### 関連テナント

| 機能 | Free | Microsoft Entra P1 | Microsoft Entra P2 | Microsoft Entra ID ガバナンス |
| --- | --- | --- | --- | --- |
| B2B コラボレーション、マルチテナント アプリ、共有課金アカウントを通じて関連テナントを検出する |  |  |  | ✅ |

#### ガバナンス関係

| 機能 | Free | Microsoft Entra P1 | Microsoft Entra P2 | Microsoft Entra ID ガバナンス |
| --- | --- | --- | --- | --- |
| テナント間の詳細な委任された管理者特権 (GDAP) とのガバナンス関係 |  | ✅ | ✅ | ✅ |
| カスタム マルチテナント アプリインジェクションとのガバナンス関係 |  |  |  | ✅ |

#### セキュリティで保護されたテナントの作成

| 機能 | Free | Microsoft Entra P1 | Microsoft Entra P2 | Microsoft Entra ID ガバナンス |
| --- | --- | --- | --- | --- |
| ガバナンス関係を通じて新しいテナントを作成するための方法 | ✅ | ✅ | ✅ | ✅ |

### Microsoft Entra Connect

この機能の使用は無料で、Azure サブスクリプションに含まれています。

### Microsoft Entra Connect ヘルス

この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 要件に適したライセンスを見つけるには、[Microsoft Entra ID の一般公開機能を比較](https://www.microsoft.com/security/business/microsoft-entra-pricing) を参照してください。

### Microsoft Entra の条件付きアクセス

この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 要件に適したライセンスを見つけるには、[Microsoft Entra ID の一般公開機能を比較](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing) を参照してください。

Microsoft Entra スイートには、すべてのMicrosoft Entra 条件付きアクセス機能が含まれています。 Microsoft 365 E7 には、Entra スイートを介した条件付きアクセス機能も含まれています。

Microsoft 365 Business Premium ライセンス条件付きアクセス機能にもアクセスできます。

リスクベースのポリシーでは、Microsoft Entra ID P2 機能である [Microsoft Entra ID 保護](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) へのアクセスが必要です。

エージェントの条件付きアクセスでは、Microsoft Entra エージェント IDを介してエージェントにポリシーを適用するには、Microsoft Agent [365 ライセンス](https://www.microsoft.com/microsoft-agent-365#plans-and-pricing)[が必要です](https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-microsoft-entra-agent-id#how-to-get-started)。 これには、次のいずれかのライセンス プラン (エージェント 365 とMicrosoft Entra スイートを含む **Microsoft 365 E7**) を使用して、ユーザー ID とエージェント ID のガバナンスを提供できます。 - **Microsoftエージェント 365** ライセンスは、少なくとも Microsoft Entra P1 または Microsoft 365 E3 とペアリングされています。

条件付きAccess ポリシーと対話できるその他の製品と機能には、それらの製品と機能に対する適切なライセンスが必要です。

条件付きAccessに必要なライセンスの有効期限が切れると、ポリシーは自動的に無効または削除されません。 これにより、お客様は、セキュリティ体制を急激に変更することなく、条件付きAccess ポリシーから移行できるようになります。 残りのポリシーは表示および削除できますが、更新できなくなります。

[セキュリティの既定値は](https://learn.microsoft.com/ja-jp/entra/fundamentals/security-defaults) 、ID 関連の攻撃から保護するのに役立ち、すべての顧客が利用できます。

### Microsoft Entra Domain Services

Microsoft Entra ドメイン サービスの料金は、テナント所有者が選択>SKU

### Microsoft 外部 ID

Microsoft Entra [External ID](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-overview) コア機能は、最初の 50,000 人の月間アクティブ ユーザーに無料で提供されます。 その他のライセンス情報については、 [外部 ID](https://aka.ms/ExternalIDPricing) に関する FAQ を参照してください。

### Microsoft Entra ID 保護（マイクロソフト エントラ ID 保護）

この機能を使用するには、Microsoft Entra ID P2 ライセンスが必要です。 要件に適したライセンスを見つけるには、[Microsoft Entraプランと価格](https://www.microsoft.com/en-us/security/business/microsoft-entra-pricing)を参照してください。

間もなく、エージェントの ID 保護では、Microsoft Entra エージェント IDを介してエージェントに保護を拡張するために、[Microsoft Agent 365 ライセンス](https://www.microsoft.com/microsoft-agent-365#plans-and-pricing)[が](https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-microsoft-entra-agent-id#how-to-get-started)必要になります。

| 機能 | 説明 | Microsoft Entra ID無料/Microsoft 365 Apps | Microsoft Entra ID P1 | Microsoft Entra ID P2 / Microsoft Entra スイート / Microsoft 365 E7 |
| --- | --- | --- | --- | --- |
| リスク ポリシー | サインイン ポリシーとユーザー リスク ポリシー (条件付きAccessを使用) | いいえ | いいえ | あり |
| セキュリティ レポート | 概要 | いいえ | いいえ | あり |
| セキュリティ レポート | 危険なユーザー | 限定的な情報。 リスクの程度が中から高のユーザーのみが表示されます。 詳細ドロアーやリスクの履歴は提供されません。 | 限定的な情報。 リスクの程度が中から高のユーザーのみが表示されます。 詳細ドロアーやリスクの履歴は提供されません。 | 完全アクセス |
| セキュリティ レポート | リスクの高いサインイン | 限定的な情報。 リスクの詳細やリスク レベルは表示されません。 | 限定的な情報。 リスクの詳細やリスク レベルは表示されません。 | 完全アクセス |
| セキュリティ レポート | リスク検出 | いいえ | 限定的な情報。 詳細ドロアーは提供されません。 | 完全アクセス |
| 通知 | 危険な状態のユーザーが検出されたアラート | いいえ | いいえ | あり |
| 通知 | 週間ダイジェスト | いいえ | いいえ | あり |
| MFA 登録ポリシー | MFA を要求する（条件付きアクセス経由） | いいえ | いいえ | あり |

### マイクロソフト エントラ インターネット アクセス

[Microsoft Entra Internet Access](https://learn.microsoft.com/ja-jp/entra/global-secure-access/overview-what-is-global-secure-access)は、単独で、またはMicrosoft Entra スイートの一部として使用できます。 Microsoft 365 E7 にも含まれています。

### Microsoft Entra の監視と正常性

必要なライセンスは、監視および正常性機能によって異なります。

| 機能 | Microsoft Entra ID フリー | Microsoft Entra ID P1 または P2/Microsoft Entra スイート |
| --- | --- | --- |
| 監査ログ | あり | あり |
| サインイン ログ | あり | あり |
| プロビジョニング ログ | いいえ | あり |
| カスタム セキュリティ属性 | あり | あり |
| 正常性 | いいえ | あり |
| Microsoft Graph のアクティビティ ログ | いいえ | あり |
| 使用状況と分析情報 | いいえ | あり |

### マイクロソフト エントラ プライベート アクセス (Microsoft Entra Private Access)

[Microsoft Entra Private Access](https://learn.microsoft.com/ja-jp/entra/global-secure-access/overview-what-is-global-secure-access)は、単独で、またはMicrosoft Entra スイートの一部として使用できます。 Microsoft 365 E7 にも含まれています。

### Microsoft Entra Privileged Identity Management

Microsoft Entra Privileged Identity Managementを使用するには、テナントに有効なライセンスが必要です。 この記事では、Privileged Identity Managementを使用するためのライセンス要件について説明します。 Privileged Identity Managementを使用するには、次のいずれかのライセンスが必要です。

#### PIM の有効なライセンス

PIM とそのすべての設定を使用するには、Microsoft Entra ID ガバナンス ライセンスまたは Microsoft Entra ID P2 ライセンスが必要です。 現在、アクセス レビューは、Microsoft Entra ID を持つサービス プリンシパル、Microsoft Entra ID P2 に関連付けられたリソース ロール、またはテナント内で Microsoft Entra ID ガバナンス エディションがアクティブなユーザーに対して範囲を設定することができます。

#### PIM に必要なライセンス

ディレクトリに、次のカテゴリのユーザーに対して Microsoft Entra ID P2 または Microsoft Entra ID ガバナンス ライセンスがあることを確認します。

- PIM を使用して管理されるMicrosoft Entra IDロールまたはAzure ロールに対する有資格割り当てまたは期限付き割り当てがあるユーザー
- グループの PIM のメンバーまたは所有者として、資格のある割り当てまたは期限付きの割り当てを持つユーザー
- PIM でアクティブ化要求を承認または却下できるユーザー
- アクセスレビューに割り当てられたユーザー
- アクセスレビューを実行するユーザー

#### PIM ライセンスのシナリオ例

必要なライセンス数の決定に役立つライセンスのシナリオ例をいくつか以下に示します。

| シナリオ | 計算 | ライセンス数 |
| --- | --- | --- |
| Woodgrove Bank には、部門ごとに 10 人の管理者と、PIM を構成および管理する 2 [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)がいます。 5 人の管理者を対象とします。 | 資格のある管理者用の 5 ライセンス | 5 |
| Graphic Design Institute には 25 人の管理者がいて、そのうちの 14 人は PIM で管理されています。 ロールのアクティブ化には承認が必要であり、組織にはアクティブ化を承認できるユーザーが 3 人います。 | 資格のあるロール用の 14 ライセンス + 3 承認者 | 十七 |
| Contoso には 50 人の管理者がいて、そのうちの 42 人は PIM で管理されています。 ロールのアクティブ化には承認が必要であり、組織にはアクティブ化を承認できるユーザーが 5 人います。 Contoso は、管理者ロールに割り当てられたユーザーの月単位のレビューも行い、レビュー担当者はユーザーのマネージャーであり、そのうち 6 人は PIM によって管理される管理者ロールに含まれていません。 | 資格のあるロール用の 42 ライセンス + 5 承認者 + 6 レビュー担当者 | 53 |

#### PIM ライセンスの有効期限が切れた場合

Microsoft Entra ID P2、Microsoft Entra ID ガバナンス、または試用版ライセンスの有効期限が切れると、Privileged Identity Management機能はディレクトリで使用できなくなります。

- Microsoft Entra ロールへの永続的なロールの割り当ては影響を受けません。
- Microsoft Entra 管理センターのPrivileged Identity Management サービス、および Privileged Identity Management のGraph API コマンドレットと PowerShell インターフェイスは、ユーザーが特権ロールをアクティブ化したり、特権アクセスを管理したり、特権ロールのアクセス レビューを実行したりできなくなります。
- ユーザーは特権ロールをアクティブ化できなくなるので、Microsoft Entra ロールの有資格ロールの割り当ては削除されます。
- Microsoft Entraロールに対する進行中のアクセス レビューはすべて終了し、Privileged Identity Managementの構成設定が削除されます。
- Privileged Identity Managementは、ロールの割り当ての変更に関する電子メールを送信しなくなりました。

### Microsoft Entra 確認済み ID

Microsoft Entra Verified IDは、無料Microsoft Entra IDを含む任意のMicrosoft Entra ID サブスクリプションに追加料金なしで含まれます。 Verified ID のコア機能は、組織の次の作業に役立ちます。

- 一意の ID 属性に対する組織の資格情報を検証および発行する。
- エンド ユーザーにデジタル資格情報の所有権を付与し、より高い可視性を提供する
- 組織のリスクを軽減し、監査プロセスを簡素化する
- Verified ID の資格情報を使用するユーザー中心のサーバーレス アプリを作成する。

Microsoft Entra Verified IDでは、アドオンとして利用できるプレミアム機能として Face Check も提供されます。 Face Check は、Microsoft Entra スイートの完全な機能として含まれています。

### Microsoft Entra ワークロード ID

Microsoft Entra [Workload ID](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identities-overview) では、Azureのアプリケーション ID とサービス プリンシパルがサポートされており、1 か月あたりのワークロード ID ごとにライセンスが必要です。

### マルチテナント組織

ソース テナント: この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 テナント間同期で同期される各ユーザーは、ホーム/ソース テナントに P1 ライセンスを持っている必要があります。 要件に適したライセンスを見つけるには、[Microsoft Entra ID プランと価格](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)をご覧ください。

ターゲット テナント内: テナント間同期は、Microsoft Entra 外部 ID の課金モデルに依存します。 外部 ID ライセンス モデルを理解するには、Microsoft Entra 外部 ID の MAU 課金モデルに関するページを参照してください。 また、自動再開を有効にするには、ターゲット テナントに少なくとも 1 つの Microsoft Entra ID P1 ライセンスが必要です。

マルチテナント組織のすべての機能は、Microsoft Entra スイートの一部として含まれています。

### ロールベースのアクセス制御

Microsoft Entra IDでの組み込みロールの使用は無料です。 カスタム ロールを使用するには、カスタム ロールの割り当てを持つすべてのユーザーに対して Microsoft Entra ID P1 ライセンスが必要です。 要件に適したライセンスについては、「[Free エディションと Premium エディションの一般提供機能](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)を参照してください。

#### 役割

#### 管理単位

管理単位を使用するには、管理単位のスコープに対してディレクトリ ロールが割り当てられている各管理単位管理者のMicrosoft Entra ID P1 ライセンスと、各管理単位メンバーのMicrosoft Entra ID無料ライセンスが必要です。 管理単位の作成は、Microsoft Entra ID無料ライセンスで利用できます。 動的メンバーシップ グループに対する[ルール](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership)を管理単位に使用している場合、各管理単位メンバーには Microsoft Entra ID P1 ライセンスが必要です。 要件に適したライセンスについては、「[Free エディションと Premium エディションの一般提供機能](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)を参照してください。

#### 制限付き管理の管理単位

制限付き管理単位には、各管理単位管理者に対して Microsoft Entra ID P1 ライセンスと、管理単位メンバー用の無料ライセンスMicrosoft Entra ID必要があります。 要件に適したライセンスについては、「[Free エディションと Premium エディションの一般提供機能](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)を参照してください。

### プレビュー段階の機能

現在プレビュー段階にある機能のライセンス情報は、該当する場合はここに含まれています。 プレビュー機能の詳細については、「[Microsoft Entra ID プレビュー機能](https://learn.microsoft.com/ja-jp/entra/fundamentals/whats-new)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/licensing-preview-info"} -->
## Microsoft Entra ID プレビュー プログラムに関する情報 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing-preview-info
- Service: entra / fundamentals
- Article date: 2024-12-16
- Summary: この記事では、Microsoft Entra ID プレビュー プログラムに参加するときに有効な用語について説明します。

Microsoft Entra ID には、カスタマー フィードバックを得る目的で Microsoft が提供するプレビュー、ベータ版、リリース前のその他の機能、サービス、ソフトウェア、リージョンが含まれることがあります ("プレビュー")。 プレビューは、プレビューに適用される条件の下で利用可能になります。プレビューは、[オンライン サービス](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)の Microsoft 製品の全体的な使用条件に概要があります。

Microsoft は、(将来実装される) 可能性のある新機能を評価し、理解する機会を Microsoft とお客様に与える目的でプレビューを段階的に展開することがあります。

注

プレビューのすべての機能が一般提供されるわけではありません。 慣例ではありませんが、プレビュー中に Microsoft が機能を取り消すことがあります。

フェーズには次が含まれます。

1. **プライベート プレビュー** – このフェーズでは、新しい概念や新しい機能を早い段階で試してみるよう、少数のお客様を招待します。 このフェーズには正式なサポートは含まれていません。
2. **パブリック プレビュー** – このフェーズでは、適切な Microsoft Entra ID ライセンスを持つすべてのお客様が新機能を評価できるようになります。 パブリック プレビューには、限られたカスタマー サポートが含まれる場合があり、通常のサービス レベル アグリーメントは適用されません。 Microsoft Entra 管理センターで公開される新機能については、プレビューの間に利用可能な新しいエクスペリエンスの注意を引く情報バナーがユーザー インターフェイスに表示されることを、お客様は期待できます。 お客様は、情報バナーをクリックして、プレビュー エクスペリエンスにオプトインします。
3. **一般提供 (GA)** – このフェーズでは、機能はライセンスを持つすべてのお客様が使用できるようになり、すべての Microsoft サポート チャネルを通じてサポートされます。 新しい機能が既存の機能に影響する場合、お客様またはお客様のユーザーが機能を使用する方法が変更される可能性があることに注意してください。

各 Microsoft Entra ID プレビュー プログラムには、さまざまなオプトイン要件と依存関係がある場合があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/new-name"} -->
## Azure Active Directory の新しい名称 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/new-name
- Service: entra / fundamentals
- Article date: 2025-05-07
- Summary: Microsoft Entra 製品ファミリの統合と、Azure Active Directory (Azure AD) から Microsoft Entra ID への名称変更について説明します。

Microsoft は、Azure Active Directory (Azure AD) を Microsoft Entra ID に名称変更します。その目的は、この製品が備えるマルチクラウドでマルチプラットフォームの機能性を伝えること、Windows Server Active Directory と混同されにくくすること、[Microsoft Entra](https://learn.microsoft.com/ja-jp/entra) 製品ファミリを統一することにあります。

### 使用やサービスの中断なし

現在 Azure AD をお使いの場合、または以前に組織で Azure AD をデプロイ済みである場合は、中断することなくサービスを使い続けることができます。 既存のすべてのデプロイ、構成、統合は、お客様によるアクションを必要とせずに、現在と同じように機能し続けます。

使い慣れた Azure AD 機能は引き続き使用できます。これらの機能には、Azure portal、Microsoft 365 管理センター、[Microsoft Entra 管理センター](https://entra.microsoft.com)を通じてアクセスできます。

すべての機能を引き続き製品で使用できます。 ライセンス、ご契約条件、サービス レベル アグリーメント、製品の認定、サポート、価格は変わりません。

切り替えをシームレスにするために、既存のすべてのログイン URL、API、PowerShell コマンドレット、Microsoft Authentication Library (MSAL) に変更はなく、開発者エクスペリエンスやツールも同様です。

サービス プランの表示名は、2023 年 10 月 1 日に変更されました。 Microsoft Entra ID Free、Microsoft Entra ID P1、Microsoft Entra ID P2 は、スタンドアロン オファーの新しい名称で、現在の Azure AD プランに含まれるすべての機能に変更はありません。 Microsoft Entra ID (旧称 Azure AD) は、Microsoft 365 E3 と Microsoft 365 E5 を含む Microsoft 365 ライセンス プランに引き続き含まれます。 価格と含まれる内容の詳細については、[価格と無料試用版のページ](https://aka.ms/PricingEntra)をご覧ください。

[Image: Azure AD と Azure AD External Identities の新しい名称を示す図。]

セルフサービス サポートについては、`Microsoft Entra` または `Azure Active Directory/Microsoft Entra ID` のトピック パスを探してください。

製品名とアイコンは変更され、機能は Azure AD ではなく Microsoft Entra としてブランド化されました。 お客様の独自のコンテンツやはエクスペリエンスで名称を Microsoft Entra ID に更新する場合は、「[方法: Azure AD の名前を変更する](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-rename-azure-ad)」をご覧ください。

### 名称の変更と例外

#### 製品名

Microsoft Entra ID は Azure AD の新しい名称です。 Azure Active Directory、Azure AD、AAD という名称は、Microsoft Entra ID に置き換えられます。

- Microsoft Entra は、ID およびネットワーク アクセス ソリューション製品ファミリの名前です。
- Microsoft Entra ID は、そのファミリ内の製品の 1 つです。
- 頭字語の使用は推奨されませんが、スペースの制限のために AAD を頭字語に置き換える必要がある場合は、ME-ID を使用してください。

#### ロゴ/アイコン

Azure AD 製品のアイコンは、Microsoft Entra ID 製品のアイコンに置き換えられます。

| **Azure AD 製品のアイコン** | **Microsoft Entra ID 製品のアイコン** |
| --- | --- |
| [Image: Azure AD 製品のアイコン][Image: 代替の Azure AD 製品のアイコン] | [Image: Microsoft Entra ID 製品のアイコン] |

Microsoft Entra ID のアイコンは、「[Microsoft Entra アーキテクチャのアイコン](https://learn.microsoft.com/ja-jp/entra/architecture/architecture-icons)」からダウンロードできます

#### 機能名

これまで "Azure Active Directory &lt;機能名&gt;" または "Azure AD &lt;機能名&gt;" と呼ばれていた機能またはサービスは、Microsoft Entra 製品ファミリ機能としてブランド化されます。 この変更は、名称の長さと複雑さを避けるため、また、多くの機能がすべての製品にわたって機能することから、当社のポートフォリオ全体で行われます。 例えば次が挙げられます。

- "Azure AD 条件付きアクセス" は "Microsoft Entra 条件付きアクセス" になりました
- "Azure AD シングル サインオン" は "Microsoft Entra シングル サインオン" になりました

詳細な一覧については、「更新された用語集」をご覧ください。

#### 変更されない名称は何ですか?

次の表に、名称変更の影響を受けない用語を一覧表示します。 Active Directory、開発者ツール、Azure AD B2C、非推奨または廃止された機能またはサービスの名称は変更されません。

| **正しい用語** | **詳細** |
| --- | --- |
| Active Directory• Windows Server Active Directory • Active Directory フェデレーション サービス (AD FS) • Active Directory Domain Services (AD DS) • Active Directory • 任意の Active Directory 機能 | 一般的に Active Directory として知られている Windows Server Active Directory と、Active Directory に関連する機能とサービスは、Microsoft Entra としてブランド化されません。 |
| 認証ライブラリ • Azure AD Authentication Library (ADAL) • Microsoft Authentication Library (MSAL) | Azure Active Directory Authentication Library (ADAL) は非推奨です。 ADAL を使用している既存のアプリは引き続き機能しますが、Microsoft では今後 ADAL のセキュリティ修正プログラムをリリースしません。 アプリケーションを Microsoft Authentication Library (MSAL) に移行することで、アプリのセキュリティを危険にさらさないようにする必要があります。 [Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) - Microsoft ID プラットフォームからのセキュリティ トークンを提供してユーザーを認証し、セキュリティで保護された Web API にアクセスすることで、Microsoft Graph、その他の Microsoft API、サードパーティの Web API、お客様独自の Web API への安全なアクセスを提供します。 |
| B2C（企業から消費者へ） • Azure Active Directory B2C • Azure AD B2C | [Azure Active Directory B2C](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c) の名称は変更されません。 Microsoft は、Azure AD B2C と外部 ID の次世代ソリューションである [Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/) のセキュリティ、可用性、信頼性に引き続き投資します。 2025 年 5 月 1 日より、Azure AD B2C は新規のお客様向けに購入できなくなります。 詳細については、FAQ で [Azure AD B2C を引き続き購入できますか](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale) ? を参照してください。 |
| Graph • Azure Active Directory Graph • Azure AD Graph • Microsoft Graph | Azure Active Directory (Azure AD) Graph は非推奨です。 今後 Azure AD Graph への投資は行われず、Azure AD Graph API には、セキュリティ関連の修正プログラム以外の SLA やメンテナンス コミットメントはありません。 新機能への投資は Microsoft Graph に対してのみ行われます。[Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/) - Microsoft Entra ID に格納されている組織、ユーザー、アプリケーションのデータに、プログラムによるアクセス権を付与します。 |
| PowerShell • Azure Active Directory PowerShell • Azure AD PowerShell • Microsoft Graph PowerShell | Azure AD PowerShell for Graph は、2024 年 3 月 30 日に非推奨となる予定です。 非推奨のプランの詳細については、非推奨に関する最新情報をご覧ください。 Azure AD と対話するための推奨モジュールである Microsoft Graph PowerShell に移行することをお勧めします。 [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview) - Microsoft Graph API の API ラッパーとして機能し、Microsoft Graph の API を持つすべての Microsoft Entra ID 機能を管理するのに役立ちます。 |
| アカウント • Microsoft アカウント • 職場または学校アカウント | エンド ユーザーのサインインとアカウントのエクスペリエンスについては、「[Microsoft アカウントでのサインイン: ブランド化ガイドライン](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-branding-in-apps)」にある職場と学校のアカウントに関するガイダンスに従ってください。 |
| Microsoft ID プラットフォーム | Microsoft ID プラットフォームには、すべての ID およびアクセス管理の開発者アセットが含まれます。 ユーザーや顧客が Microsoft ID またはソーシャル アカウントを使用してサインインできるアプリケーションを構築するのに役立つリソースが引き続き提供されます。 |
| • Azure AD Sync • DirSync | DirSync と Azure AD Sync はサポートされておらず、動作しません。 DirSync または Azure AD Sync をまだ使用している場合は、Microsoft Entra Connect にアップグレードして同期プロセスを再開する必要があります。 詳細については、「[Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-dirsync-upgrade-get-started)」をご覧ください。 |

### よく寄せられる質問

#### 名称はいつ変更されますか?

Microsoft エクスペリエンス全体での名称変更は、2023 年 8 月 15 日に開始されました。 SKU とサービス プランの表示名は、2023 年 10 月 1 日に変更されました。 Microsoft エクスペリエンスとパートナー エクスペリエンスでは、ほとんどの名称のテキスト文字列の変更が 2023 年末に完了しました。

#### なぜ名称が変更されるのですか?

すべてのユーザー向けに安全なアクセス エクスペリエンスを簡素化するという継続的な取り組みの一環として、統合され拡張された Microsoft Entra 製品ファミリを、より使いやすく、わかりやすくすることを目的として、Azure AD から Microsoft Entra ID への名称変更がデザインされています。

Microsoft Entra ID という名称は、製品が備えるマルチクラウドでマルチプラットフォームの機能性をより正確に表しており、オンプレミス ID ソリューション (Active Directory) と混同しにくくなります。また、Microsoft Entra ID とネットワーク アクセスのポートフォリオを拡大する中で、すべての ID を保護し、すべてのアクセス ポイントをセキュリティで保護するための、よりシンプルな方法を提供するための道筋となります。

#### Microsoft Entra とは

Microsoft Entra 製品ファミリは、すべての ID を保護し、あらゆる場所でネットワーク アクセスをセキュリティで保護するのに役立ちます。 拡大された製品ファミリには、次のものが含まれます。

| ID 管理とアクセス管理 | 新しい ID のカテゴリ | ネットワーク アクセス |
| --- | --- | --- |
| [Microsoft Entra ID (旧称 Azure AD)](https://learn.microsoft.com/ja-jp/entra/) | [Microsoft Entra 確認済み ID](https://learn.microsoft.com/ja-jp/entra/verified-id/) | [Microsoft Entra Internet Access](https://aka.ms/GlobalSecureAccessDocs) |
| [Microsoft Entra ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/) |  | [Microsoft Entra Private Access](https://aka.ms/GlobalSecureAccessDocs) |
| [Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/) | [Microsoft Entra ワークロード ID](https://learn.microsoft.com/ja-jp/entra/workload-id/) |  |

#### Microsoft Entra ID はどこで管理できますか?

Microsoft Entra ID とその他のすべての Microsoft Entra ソリューションは、[Microsoft Entra 管理センター](https://entra.microsoft.com)または [Azure portal](https://portal.azure.com) で管理できます。

#### サービス プランと SKU の表示名はどうなりますか?

ライセンス、価格、機能は変更されません。 表示名は、2023 年 10 月 1 日に次のように更新されました。

| **サービス プランの古い表示名** | **サービス プランの新しい表示名** |
| --- | --- |
| Azure Active Directory Free | Microsoft Entra ID Free（無料） |
| Azure Active Directory Premium P1 | Microsoft Entra ID P1 |
| Azure Active Directory Premium P2 | Microsoft Entra ID P2 |
| 教育のための Azure Active Directory | 教育機関向け Microsoft Entra ID |
| **製品 SKU の古い表示名** | **製品 SKU の新しい表示名** |
| Azure Active Directory Premium P1 | Microsoft Entra ID P1 |
| Azure Active Directory Premium P1 for students | Microsoft Entra ID P1 for students |
| 教職員向け Azure Active Directory Premium P1 | Microsoft Entra ID P1 for faculty |
| Azure Active Directory Premium P1 for government | Microsoft Entra ID P1 for government |
| Azure Active Directory Premium P2 | Microsoft Entra ID P2 |
| Azure Active Directory Premium P2 for students | Microsoft Entra ID P2 for students |
| 教職員向け Azure Active Directory Premium P2 | Microsoft Entra ID P2 for faculty |
| Azure Active Directory Premium P2 for government | Microsoft Entra ID P2 for government |
| Azure Active Directory F2 | Microsoft Entra ID F2 |

#### Azure AD はなくなるのですか?

いいえ。Azure AD という名前だけがなくなります。 機能は変わりません。

#### Microsoft は引き続きオンプレミスの ID 管理をサポートしますか?

多くの組織がこのソリューションに引き続き利用しているため、Microsoft は、オンプレミスの ID およびアクセス管理と、Azure やその他のクラウドへの接続のために、Windows Server Active Directory のサポートと強化を継続します。

Active Directory の名称は変更されません。 Azure Active Directory の新しい名称である Microsoft Entra ID に関して寄せられた広範なフィードバックに基づき、多くのお客様にとって、この名称変更は、オンプレミス (Active Directory) ソリューションとマルチクラウド ID (Microsoft Entra ID) ソリューションをより正確に区別するのに役立つでしょう。

#### アプリ ギャラリーや条件付きアクセスなどの Azure AD の機能はどうなりますか?

すべての機能は、名称を除いて変更されません。 お客様は、中断なく、すべての機能を引き続き使用できます。

機能の名称は Microsoft Entra に変わります。 例えば次が挙げられます。

- Azure AD テナント -&gt; Microsoft Entra テナント
- Azure AD アカウント -&gt; Microsoft Entra アカウント

その他の例については、「更新された用語集」をご覧ください。

#### ライセンスは変更されますか? 料金に変更はありますか?

その必要はありません。 価格、ご契約条件、サービス レベル アグリーメント (SLA) は変わりません。

#### Microsoft Entra ID は、Azure サブスクリプションで無料サービスとして利用できますか?

Azure、Microsoft 365、Dynamics 365、Teams、Intune のサブスクリプションの一部として Azure AD Free を使用されているお客様は、引き続き同じ機能にアクセスできます。 これは Microsoft Entra ID Free に名称が変更されました。 無料版は https://www.microsoft.com/security/business/microsoft-entra-pricing で入手できます。

#### Microsoft 365 または Azure AD for Office 365 では何が変わりますか?

Microsoft Entra ID (旧称 Azure AD) は、Microsoft 365 Enterprise および Business Premium オファー内で引き続き使用できます。 Office 365は、2022 年に Microsoft 365 に名前が変更されました。 Azure AD 内の Office 365 アプリ向けの固有の機能 (会社のブランド化やセルフサービス サインイン アクティビティ検索など) は、Microsoft Entra ID Free を使用されているすべてのお客様が使用できるようになりました。

#### Microsoft 365 E3 では何が変わりますか?

Microsoft 365 E3 で使用できる ID の機能に変更はありません。 Microsoft 365 E3 には、Microsoft Entra ID P1 (旧称 Azure AD Premium P1) が含まれています。

#### Microsoft 365 E5 では何が変わりますか?

Microsoft 365 E5 のお客様は、既存の機能に加えて、トークン保護、GPS ベースの位置情報に基づく条件付きアクセス、最も機密性の高いアクションのためのステップアップ認証など、新しい ID 保護機能にもアクセスできるようになります。 Microsoft 365 E5 には、Microsoft Entra ID P2 (旧称 Azure AD Premium P2) が含まれています。

#### ID 開発者と DevOps エクスペリエンスでは何が変わりますか?

ID 開発者と DevOps エクスペリエンスの名称は変更されません。 切り替えをシームレスにするために、既存のすべてのログイン URL、API、PowerShell コマンドレット、Microsoft Authentication Library (MSAL) に変更はなく、開発者エクスペリエンスやツールも同様です。

多くの技術的コンポーネントは、お客様からはほとんど認識されない (サインイン URL など) か、API のように通常はブランド化されないものです。

Microsoft ID プラットフォームには、すべての ID およびアクセス管理の開発者アセットが含まれます。 ユーザーや顧客が Microsoft ID またはソーシャル アカウントを使用してサインインできるアプリケーションを構築するのに役立つリソースが引き続き提供されます。

変更されない名称の詳細な一覧については、「変更されない名前は何ですか?」をご覧ください。

#### PowerShell コマンドレットの名称は変更されますか?

その必要はありません。 現在、Microsoft は、ID タスクを管理するための次の 2 つの PowerShell モジュールを提供しています。Azure AD PowerShell モジュール (2024 年 3 月に非推奨になる予定) と、Microsoft Graph PowerShell モジュールです。

Azure AD PowerShell for Graph モジュールでは、`AzureAD` は、ほぼすべてのコマンドレットの名前に入っています。 これらは変更されません。公式の製品名が Microsoft Entra ID になった今でも、これらの同じコマンドレットを引き続き使用できます。

Microsoft Graph PowerShell コマンドレットは、Azure AD としてブランド化されていません。 Azure AD PowerShell から、将来的に Microsoft Entra ID を操作するための推奨モジュールとなる Microsoft Graph PowerShell への移行を計画することをお勧めします。

#### 顧客はいつどのようにして通知を受け取りますか?

名称変更は、2023 年 7 月 11 日に公開されました。

バナー、アラート、メッセージ センターの投稿により、名称変更がユーザーに通知されました。 この変更は、Azure、Microsoft 365、Microsoft Entra 管理センターなどのポータルと、Microsoft Learn のテナントの概要ページにも表示されました。

#### コンテンツまたはアプリで Azure AD の名称を使用している場合はどうなりますか?

名称変更に関する情報を広め、お客様独自のエクスペリエンスにも実装するようお願いします。 コンテンツ作成者、IT 管理者または ID セキュリティ管理者向けの内部ドキュメントの作成者、Azure AD 対応アプリの開発者、独立系ソフトウェア ベンダー、または Microsoft パートナーである場合、「[方法: Azure AD の名前を変更する](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-rename-azure-ad)」で説明されている名前付けのガイダンスを使用して、コンテンツと製品エクスペリエンスでの名前を変更できます。

### 更新された用語集

ID およびネットワーク アクセス製品の機能は、個々の製品名ではなく、製品ファミリである Microsoft Entra に属します。

Microsoft Entra への帰属を機能で示す必要はありません。 話の内容が概念なのか特定の製品の機能なのかを明確にするために必要な場合や、Microsoft Entra 機能と競合する機能を比較する場合にのみ使用します。

正式な製品名のみが大文字で表記され、さらに条件付きアクセスとマイ \* アプリも大文字で表記されます。

| **カテゴリ** | **古い用語** | **2023 年 7 月時点の正しい名前** |
| --- | --- | --- |
| **Microsoft Entra 製品ファミリ** | Microsoft Azure Active Directory Azure Active Directory Azure Active Directory (Azure AD) Azure AD AAD | マイクロソフト エントラ ID (2 番目の使用: Microsoft Entra ID が推奨されます。Entra ID は、スペースが本当に限られている場合にのみ慎重に使用する必要があります) 頭字語の使用は推奨されませんが、スペースの制限のために AAD を頭字語に置き換える必要がある場合は、ME-ID を使用してください。 |
|  | Azure Active Directory 外部アイデンティティ Azure AD 外部 ID | Microsoft Entra 外部 ID (2 番目の使用: 外部 ID) |
|  | Azure Active Directory Identity Governance Azure AD Identity Governance Microsoft Entra Identity Governance | Microsoft Entra ID ガバナンス (2 番目の使用: ID ガバナンス) |
|  | *を新しい* にする | Microsoft Entra Internet Access (2 番目の使用: Internet Access) |
|  | *を新しい* にする | Microsoft Entra Private Access (2 番目の使用: Private Access) |
|  | Azure Active Directory 検証可能な資格情報 Azure AD 検証可能な資格情報 | Microsoft Entra 確認済み ID (2 番目の使用: 確認済み ID) |
|  | Azure Active Directory ワークロード ID Azure AD ワークロード ID | Microsoft Entra ワークロード ID (2 番目の使用: ワークロード ID) |
|  | Azure Active Directory Domain Services Azure AD Domain Services | Microsoft Entra Domain Services (2 番目の使用: Domain Services) |
| **Microsoft Entra ID の SKU** | Azure Active Directory Premium P1 | Microsoft Entra ID P1 |
|  | 教職員向け Azure Active Directory Premium P1 | Microsoft Entra ID P1 for faculty |
|  | Azure Active Directory Premium P1 for students | Microsoft Entra ID P1 for students |
|  | Azure Active Directory Premium P1 for government | Microsoft Entra ID P1 for government |
|  | Azure Active Directory Premium P2 | Microsoft Entra ID P2 |
|  | 教職員向け Azure Active Directory Premium P2 | Microsoft Entra ID P2 for faculty |
|  | Azure Active Directory Premium P2 for students | Microsoft Entra ID P2 for students |
|  | Azure Active Directory Premium P2 for government | Microsoft Entra ID P2 for government |
|  | Azure Active Directory Premium F2 | Microsoft Entra ID F2 |
| **Microsoft Entra ID のサービス プラン** | Azure Active Directory Free | Microsoft Entra ID Free（無料） |
|  | Azure Active Directory Premium P1 | Microsoft Entra ID P1 |
|  | Azure Active Directory Premium P2 | Microsoft Entra ID P2 |
|  | 教育のための Azure Active Directory | 教育機関向け Microsoft Entra ID |
| **機能と特徴** | Azure AD アクセス トークン認証 Azure Active Directory アクセス トークン認証 | Microsoft Entra アクセス トークン認証 |
|  | Azure AD アカウント Azure Active Directory アカウント | Microsoft Entra アカウント この用語は、IT 管理者と開発者のみ使用できます。 エンド ユーザーは、職場または学校アカウントで認証します。 |
|  | Azure AD アクティビティ ログ | Microsoft Entra アクティビティ ログ |
|  | Azure AD 管理者 Azure Active Directory の管理者 | Microsoft Entra 管理者 |
|  | Azure AD 管理センター Azure Active Directory 管理センター | Microsoft Entra 管理センターに置き換え、entra.microsoft.com にリンクを更新します |
|  | Azure AD アプリケーション プロキシ Azure Active Directory アプリケーション プロキシ | Microsoft Entra アプリケーション プロキシ |
|  | Azure AD 監査ログ | Microsoft Entra 監査ログ |
|  | Azure AD 認証 Azure AD ID による認証 Azure AD による認証 Azure AD に対する認証 | Microsoft Entra 認証 Microsoft Entra ID による認証 Microsoft Entra による認証 Microsoft Entra に対する認証 この用語は、管理者のみ使用できます。 エンド ユーザーは、職場または学校アカウントで認証します。 |
|  | Azure AD B2B Azure Active Directory B2B | Microsoft Entra B2B（ビジネス向け） |
|  | Azure AD の組み込みロール Azure Active Directory 組み込みロール | Microsoft Entra 組み込みロール |
|  | Azure AD 条件付きアクセス Azure Active Directory の条件付きアクセス | Microsoft Entra 条件付きアクセス (2 番目の使用: 条件付きアクセス) |
|  | Azure AD クラウド専用 ID Azure Active Directory クラウド専用 ID | Microsoft Entra クラウド専用 ID |
|  | Azure AD Connect Azure Active Directory Connect | Microsoft Entra Connect |
|  | Azure AD Connect 同期 Azure Active Directory Connect Sync | Microsoft Entra Connect Sync |
|  | Azure AD コネクタ Azure Active Directory コネクタ | Microsoft Entra コネクタ |
|  | Azure AD ドメイン Azure Active Directory ドメイン | Microsoft Entra ドメイン |
|  | Azure AD Domain Services Azure Active Directory Domain Services | Microsoft Entra Domain Services |
|  | Azure AD エンタープライズ アプリケーション Azure Active Directory エンタープライズ アプリケーション | Microsoft Entra エンタープライズ アプリケーション |
|  | Azure AD フェデレーション サービス Azure Active Directory フェデレーション サービス | Active Directory フェデレーション サービス |
|  | Azure AD グループ Azure Active Directory グループ | Microsoft Entra グループ |
|  | Azure AD ハイブリッド ID Azure Active Directory ハイブリッド ID | Microsoft Entra ハイブリッド ID |
|  | Azure AD ID Azure Active Directory ID | Microsoft Entra ID |
|  | Azure AD Identity Protection Azure Active Directory Identity Protection | Microsoft Entra ID Protection（マイクロソフト エントラ ID 保護） |
|  | Azure AD 統合認証 Azure Active Directory 統合認証 | Microsoft Entra 統合認証 |
|  | Azure AD 参加 Azure AD 参加済み Azure Active Directory 参加 Azure Active Directory 参加済み | Microsoft Entra Join Microsoft Entra 参加済み |
|  | Azure AD ライセンス Azure Active Directory ライセンス | Microsoft Entra ID ライセンスまたは Microsoft Entra ID 用ライセンス |
|  | Azure AD ログイン Azure Active Directory ログイン | Microsoft Entra ログイン |
|  | Azure AD マネージド ID Azure Active Directory マネージド ID | Azure リソースのマネージド ID |
|  | Azure AD Multifactor Authentication (MFA) Azure Active Directory Multifactor Authentication (MFA) | Microsoft Entra 多要素認証 (MFA) (2 番目の使用: MFA) |
|  | Azure AD OAuth および OpenID Connect Azure Active Directory OAuth および OpenID Connect | Microsoft Entra ID OAuth および OpenID Connect |
|  | Azure AD オブジェクト Azure Active Directory オブジェクト | Microsoft Entra オブジェクト |
|  | Azure Active Directory 専用認証 Azure AD のみの認証 | Microsoft Entra 専用認証 |
|  | Azure AD パススルー認証 (PTA) Azure Active Directory パススルー認証 (PTA) | Microsoft Entra パススルー認証 |
|  | Azure AD パスワード認証 Azure Active Directory パスワード認証 | Microsoft Entra パスワード認証 |
|  | Azure AD パスワード ハッシュ同期 (PHS) Azure Active Directory パスワード ハッシュ同期 (PHS) | Microsoft Entra パスワード ハッシュ同期 |
|  | Azure AD のパスワード保護 Azure Active Directory パスワード保護 | Microsoft Entra パスワード保護 |
|  | Azure AD Premium Azure Active Directory Premium | Microsoft Entra ID P1 または P2 |
|  | Azure AD プリンシパル ID Azure Active Directory プリンシパル ID | Microsoft Entra プリンシパル ID |
|  | Azure AD Privileged Identity Management (PIM) Azure Active Directory Privileged Identity Management (PIM) | Microsoft Entra Privileged Identity Management (PIM) |
|  | Azure AD 登録済み Azure Active Directory 登録済み | Microsoft Entra 登録済み |
|  | Azure AD レポートと監視 Azure Active Directory レポートと監視 | Microsoft Entra レポートと監視 |
|  | Azure AD ロール Azure Active Directory ロール | Microsoft Entra ロール |
|  | Azure AD のスキーマ Azure Active Directory スキーマ | Microsoft Entra スキーマ |
|  | Azure AD シームレス シングル サインオン (SSO) Azure Active Directory シームレス シングル サインオン (SSO) | Microsoft Entra シームレス シングル サインオン (SSO) (2 番目の使用: SSO) |
|  | Azure AD のセルフサービス パスワード リセット (SSPR) Azure Active Directory のセルフサービス パスワード リセット (SSPR) | Microsoft Entra セルフサービス パスワード リセット (SSPR) |
|  | Azure AD のサービス プリンシパル Azure Active Directory サービス プリンシパル | Microsoft Entra サービス プリンシパル |
|  | Azure AD テナント Azure Active Directory テナント | Microsoft Entra テナント |
|  | Azure AD でユーザーを作成する Azure Active Directory でユーザーを作成する | Microsoft Entra でユーザーを作成する |
|  | Azure AD とのフェデレーション Azure Active Directory とのフェデレーション | Microsoft Entra ID とのフェデレーション |
|  | Hybrid Azure AD Join Hybrid Azure AD 参加済み | Microsoft Entra ハイブリッド参加 Microsoft Entra ハイブリッド参加済み |
|  | Azure SQL 用の Azure AD でのマネージド ID | Azure SQL 用の Microsoft Entra でのマネージド ID |
| **頭字語の使用** | AAD | ME-ID これは製品の正式な略称ではありませんが、コード内や絶対的な最短の形式が必要な場合に使用できます。 |

### 改定履歴

| 日付 | 変更の説明 |
| --- | --- |
| 2024 年 3 月 5 日 | 日付を含む記述の軽微な更新。 |
| 2023 年 10 月 12 日 | • ライセンス プランの提供状況に関する更新された記述。 • 3 つの用語を用語集に追加: "Azure AD コネクタ"、"Azure AD ライセンス"、"Azure AD Premium" |
| 2023 年 9 月 15 日 | Azure AD の名称変更に関するハウツー記事のリンクを追加、Azure AD B2C の説明を更新、Azure AD の名称変更の理由の詳細を追加。 |
| 2023 年 8 月 29 日 | • 用語集で "Azure AD アクティビティ ログ" のエントリを修正し、別の種類のアクティビティ ログである "Azure AD 監査ログ" と分けました。 • 「変更されない名前は何ですか」セクションに Azure AD Sync と DirSync を追加しました。 |
| 2023 年 8 月 18 日 | • 新しいセクション「更新された用語集」を記事に含めるように更新しました。このセクションには、古い用語と新しい用語が含まれます。• 情報を更新、Microsoft Entra ID のアイコンの使用方法へのリンクを追加、一部のセクションで表現を更新。 |
| 2023 年 7 月 11 日 | [Microsoft Entra のモーメントと関連するお知らせ](https://www.microsoft.com/security/blog/2023/07/11/microsoft-entra-expands-into-security-service-edge-and-azure-ad-becomes-microsoft-entra-id/?culture=en-us&amp;country=us)の一環として、元のガイダンスを公開しました。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/properties-area"} -->
## 組織のプライバシー情報を追加する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/properties-area
- Service: entra / fundamentals
- Article date: 2025-04-30
- Summary: 組織のプライバシー情報、プライバシー連絡先、技術連絡先をディレクトリに追加します。

### 概要

この記事では、管理者が Microsoft Entra 管理センターを使用してプライバシー関連の情報を組織のディレクトリに追加する方法について説明します。

社内の従業員と外部ゲストがポリシーを確認できるように、グローバル プライバシー連絡先と組織のプライバシーに関する声明の両方を追加します。 各ビジネスは独自のプライバシーステートメントを作成して調整するため、弁護士に問い合わせてください。

注

個人データの表示または削除の詳細については、GDPR サイトに [対する Windows データ主体の要求](https://learn.microsoft.com/ja-jp/microsoft-365/compliance/gdpr-dsr-windows) に関する Microsoft のガイダンスを確認してください。 GDPR に関する一般的な情報については、 [Microsoft セキュリティ センターの GDPR セクション](https://www.microsoft.com/trust-center/privacy/gdpr-overview) と [Service Trust ポータルの GDPR セクションを参照](https://servicetrust.microsoft.com/ViewPage/GDPRGetStarted)してください。

### プライバシー情報を追加する

プライバシーと技術情報は、Microsoft Entra 管理センターの **[プロパティ** ] 領域で確認できます。

#### プロパティ領域にアクセスし、プライバシー情報を追加するには

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[課金管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#billing-administrator)としてサインインします。
2. **Entra ID**&gt;**Overview**&gt;**Properties** に移動します。

    [Image: プライバシー情報領域が強調表示されているプロパティ領域を示すスクリーンショット。]
3. ユーザーのプライバシー情報を追加します。

- **技術的な連絡先。** 組織内のテクニカル サポートに問い合わせるユーザーのメール アドレスを入力します。
- **グローバル プライバシー連絡窓口** 個人データのプライバシーに関する問い合わせ先のメール アドレスを入力します。 このユーザーは、Microsoft Entra サービスに関連するデータ侵害がある場合に Microsoft が問い合わせることもできます。 ここに記載されているユーザーがいない場合は、Microsoft からグローバル管理者に問い合わせてください。 Microsoft 365 関連のプライバシー インシデント通知については、 [Microsoft 365 メッセージ センターの FAQ](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/message-center?preserve-view=true&view=o365-worldwide#frequently-asked-questions) を参照してください。
- **プライバシーに関する声明の URL。** 組織が内部ゲストと外部ゲストの両方のデータ プライバシーを処理する方法を説明する組織のドキュメントへのリンクを入力します。

    Important

    自分のプライバシーに関する声明またはプライバシーに関する連絡先を含めない場合、外部のゲストには、**レビューのアクセス許可**ボックスに、**&lt;*あなたの組織名*&gt;がレビュー用の条件リンクを提供していないことを**示すテキストが表示されます。 たとえば、ゲスト ユーザーは、B2B コラボレーションを通じて組織にアクセスするための招待を受け取ると、このメッセージを表示します。

    [Image: メッセージが表示された [B2B コラボレーション レビューのアクセス許可] ボックスを示すスクリーンショット。]

1. **[Accept](https://learn.microsoft.com/ja-jp/entra/fundamentals/承認)** を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/quarantine-unsanctioned-tenants"} -->
## 承認されていないテナントを検疫する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/quarantine-unsanctioned-tenants
- Service: entra / fundamentals
- Article date: 2025-04-14
- Summary: Microsoft Entra 機能を使用して、承認されていないテナントを分離します。 未承認のテナントを検疫し、セキュリティを強化する手順に従います。

### 概要

Von Bedeutung

Microsoft [Cloud Footprint に](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/discover-cloud-footprint) 関する FAQ を確認して、組織のテナントのインベントリを確認した後にのみ、この記事を参照してください。 この記事では、プライマリ テナント内で使用して、検出されたテナントの一覧で承認されていない疑いのあるテナントを検疫するために使用できる、特定の既存の Microsoft Entra 機能について説明します。

### テナントを検疫するとはどういう意味ですか?

検疫には、既存の Microsoft Entra 機能を使用して、承認されていない疑いのあるテナントを分離する必要があります。 これにより、環境内で管理制御できないテナントへの公開から存在するセキュリティ リスクが直ちに軽減されます。 分離することで、テナントと相手のテナントの間に摩擦が生じ、スクリーム テストとして機能します。 こうした摩擦が生じると、問題のあるテナントの管理者が支援を求めて連絡してくるため、これらのテナントとの関係の正当性を確認したり、これらのテナントの管理権を取り戻したりする必要性が生じます。 誰も連絡を取っていない場合は、テナントを無期限に検疫状態のままにすることができます。

### テナントを検疫する必要がある場合

あなたは、"Contoso.com" のプライマリ テナントを持つ会社 "Contoso" の IT 管理者です。中央の Contoso テナントのデータをセキュリティで保護するには、テナントへの特権アクセス権を持つユーザーとアプリケーションが、これらのリソースを適切にセキュリティで保護するテナント内にあることを確認する必要があります。 同様に、テナントがアクセス許可を持つ外部テナントが既知であり、セキュリティで保護されたプラクティスに従っていることを確認します。 Contoso をセキュリティで保護するには、プライマリ テナントとの受信または送信の関係を持つすべてのテナントを検索する必要があります。 [Microsoft Cloud Footprint に](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/discover-cloud-footprint)関する FAQ に従った後、会社に属している可能性がある、または属していない可能性のあるテナントをいくつか特定しました。 シナリオの目的で、これらのテナント ContosoTest.com と ContosoDemo.com を呼び出してみましょう。 これらのテナントのグローバル管理者が誰であるかがわからないため、従業員が管理している可能性があり、組織のセキュリティ ポリシーに準拠していないことを心配します。 これにより、管理されていない環境が維持される場合、セキュリティ上の大きなリスクが伴います。 ContosoTest.com と ContosoDemo.com を直接制御できないため、Contoso.com テナントの設定のみを変更できます。 これらのテナントへの公開による潜在的な脆弱性を最小限に抑えるために、検疫する必要があります。 ただし、行った変更を簡単に元に戻し、プロセスで重要なシステムが意図せず影響を受けないことを確認することが重要です。 検疫後、テナントと疑わしいテナントの間に十分な摩擦が生じ、テナントの管理者がヘルプデスクに連絡するように促します。

[Image: 環境内で検出された承認されていないテナントを示す図。]

ContosoTest.com テナントの管理者から連絡があります。 この時点で、テナントが従業員によって作成されたことを確認し、テナント内の管理者としてあなたが追加されるべきであると判断して、制御を回復する必要があります。 ContosoTest.com テナントを検疫しなくなりました。 ただし、ContosoDemo.com テナントの管理者から連絡がないため、テナントを検疫済みの状態のままにしておきます。

[Image: 検疫が承認されていないテナントの概要を示す図]

### Microsoft Entra 機能を使用して、疑わしいテナントを検疫するにはどうすればよいですか?

#### 外部 ID のテナント間アクセス設定によるユーザー サインインをブロックする

**ライセンスが必要: Microsoft Entra ID P1**

**疑わしいテナントに対するアクション**:

Microsoft Entra 組織は、外部 ID を持つテナント間アクセスを使用して、他の外部 Entra 組織のユーザーがリソースにアクセスできる範囲と、組織のユーザーが他の外部 Entra 組織にアクセスできる範囲を指定できます。 これらのポリシーを使用すると、他のテナントとのコラボレーションを中断することなく、疑わしいテナントでの受信または送信ログインの試行を制限できます。 管理者は [、組織を追加](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration?source=recommendations#add-an-organization) し、カスタマイズされた設定を構成して、疑わしいテナントの [受信](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration#modify-inbound-access-settings) と [送信](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration#modify-outbound-access-settings) のユーザー サインインをブロックできます。

**セキュリティ選定のデザイン**:

管理者は、既定の [設定を構成](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration?source=recommendations#configure-default-settings) して、疑わしいテナントの外部ユーザーからのすべての受信サインイン試行をブロックできます。 同様に、自社テナントのユーザーによる、不審なテナントへのアウトバウンド サインインをすべてブロックすることも可能です。 その後、[組織を追加](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration?source=recommendations#add-an-organization)し、指定したテナントからの[インバウンド](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration#modify-inbound-access-settings)および指定したテナントへの[アウトバウンド](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration#modify-outbound-access-settings)のみを許可するように設定を構成できます。 これらの設定を使用すると、既定でテナントをセキュリティで保護し、信頼されたテナントとの B2B コラボレーションのみを許可できます。

テナント間アクセス設定の管理の詳細については、以下を参照してください。

- [テナント間アクセスの概要](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)。
- [テナント間アクセス設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration?source=recommendations)。

#### グローバル セキュリティで保護されたアクセスとユニバーサル テナントの制限を使用してユーザーのサインインをブロックする

**ライセンスが必要**: Microsoft Entra ID P1

**疑わしいテナントに対するアクション**: テナント制限 v2 (TRv2) とグローバル セキュア アクセス (GSA) は、すべての管理対象デバイスとネットワークにわたって、承認されていないテナントまたは疑わしいテナントへの認証を効果的に防止します。 管理者は、 [カスタム TRv2 構成を使用して、ユーザーが特定の疑いのあるテナントにサインインしてアクセスできないように](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-restrictions-v2#step-2-configure-tenant-restrictions-v2-for-specific-partners)するポリシーを作成できます。 その後、GSA の一部として [Universal Tenant Restrictions v2](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-universal-tenant-restrictions) を使用して作成したポリシーを適用し、他の既存のテナントの認証を中断することなく、認証プレーン保護 (およびMicrosoft Graphのデータ プレーン保護) を提供できます。

**セキュリティ選定のデザイン**:

管理者は、 [既定の制限を構成](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-restrictions-v2#step-1-configure-default-tenant-restrictions) し、ユーザーが [特定の組織にサインインしてアクセスできるように](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-restrictions-v2#step-2-configure-tenant-restrictions-v2-for-specific-partners)することができます。Microsoft Entra ID は、GSA の一部として [ユニバーサル テナント制限 v2](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-universal-tenant-restrictions) を使用してポリシーを適用すると、他のすべてのテナントへの認証を禁止します。 監査モードで TRv2 を有効にし、GSA で TRv2 ポリシーを適用すると、外部テナントへのアクセス試行を含むすべてのアクティビティが表示されます。

TRv2 と GSA の使用の詳細については、次を参照してください。

- [Global Secure Access とは](https://learn.microsoft.com/ja-jp/entra/global-secure-access/overview-what-is-global-secure-access)
- [グローバルなセキュリティで保護されたアクセスとユニバーサル テナントの制限](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-universal-tenant-restrictions)
- [テナント制限の構成 - Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-restrictions-v2)

#### マルチテナント アプリケーションとサービス プリンシパルのアクセス許可を取り消す

**ライセンスが必要**: Microsoft Entra ID P1

**疑わしいテナントに対するアクション**:

Microsoft Entra を使用すると、お客様は、アプリが登録されたテナントが疑わしいテナントと見なされるサード パーティのマルチテナント アプリの受信アプリケーション アクセスを制限できます。 アクセスを制限するには、管理者は、疑わしいテナントに登録されているアプリケーションに対応する正しいサービス プリンシパルを見つける必要があります。 サービス プリンシパル オブジェクトの appOwnerOrganizationId プロパティには、アプリケーションが登録された tenantId が一覧表示されます。 これらのサービス プリンシパルをキャプチャするには、MSGraph API を使用してプログラムを使用する必要があります。

MSGraph: 要求ヘッダー: { ConsistencyLevel: eventual }

```http

GET https://graph.microsoft.com/v1.0/servicePrincipals?$count=true&$filter=appOwnerOrganizationId eq {tenantId}
```

適切なサービス プリンシパルを見つけたら、 [アプリケーションに付与されたアクセス許可を確認して取り消](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-application-permissions?pivots=ms-graph) すか、 [サービス プリンシパルを完全に削除](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/delete-application-portal?pivots=ms-graph) することができます。 サービス プリンシパルの削除は、 [最大 30 日間の復元可能なアクション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/delete-recover-faq#how-do-i-restore-deleted-applications-or-service-principals-)です。

マルチテナント アプリとサービス プリンシパルの詳細については、次を参照してください。

- [Microsoft Entra ID のアプリとサービス プリンシパル](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)

### 疑わしいテナントでプロビジョニングされたサブスクリプションを取り消す

**ライセンスが必要**: なし。Microsoft の課金アカウントを持つすべての有料ユーザーが利用できます

**疑わしいテナントに対するアクション**:

課金アカウントの関係に基づいてテナントを特定したが、そのサブスクリプションサービスがどのテナントに提供されているのかを認識できない場合は、次のリソースを使用してください。 取り消された Azure サブスクリプションと Microsoft 365 サブスクリプションは、完全に削除されるまでの猶予期間 ([取り消しから 30 ~ 90 日後](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/cancel-azure-subscription)) に再アクティブ化できます。 必要に応じて、サブスクリプションの取り消しと削除に関する [サポート](https://support.microsoft.com/topic/customer-service-phone-numbers-c0389ade-5640-e588-8b0e-28de8afeb3f2) にお問い合わせください。

- Azure の取り消しによる検疫の詳細については、「Azure [サブスクリプションの取り消しと削除」を参照してください](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/cancel-azure-subscription)。
- Microsoft 365 をキャンセルして検疫する方法の詳細については、Microsoft [365 管理センターで Microsoft ビジネス サブスクリプションをキャンセル](https://learn.microsoft.com/ja-jp/microsoft-365/commerce/subscriptions/cancel-your-subscription)するを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/reference-company-branding-css-template"} -->
## 会社のブランドをカスタマイズするための CSS リファレンス ガイド - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/reference-company-branding-css-template
- Service: entra / fundamentals
- Article date: 2026-08-18
- Summary: Microsoft Entra サインイン ページの会社のブランドをカスタマイズするための CSS テンプレート セレクターのリファレンス ガイド。

ユーザー サインイン プロセス用に会社のブランドを構成すると、Id およびアクセス管理サービスとして Microsoft Entra ID を使用するアプリケーションでシームレスなエクスペリエンスが提供されます。 会社のブランド化 [プロセスをカスタマイズする](https://download.microsoft.com/download/7/2/7/727f287a-125d-4368-a673-a785907ac5ab/custom-styles-template-013023.css) の一環として、[CSS テンプレート](https://learn.microsoft.com/ja-jp/entra/fundamentals/reference-company-branding-css-template) を使用している場合は、この CSS リファレンス ガイドを使用します。

Important

2026 年 1 月 5 日以降に作成されたテナントには、Microsoft Entra IDの会社のブランド化に使用できるカスタム CSS がありません。 2026 年 7 月 21 日以降、カスタム CSS をまだ使用していない 2026 年 1 月 6 日より前に作成されたテナントは、カスタム CSS を構成できません。

Microsoft Entra 外部 IDテナントは影響を受けません。

### カスタム CSS レイアウトと配置プロパティの廃止

[Microsoft Secure Future Initiative](https://www.microsoft.com/trust-center/security/secure-future-initiative) と ID のセキュリティとフィッシングに対する耐性に重点を置くために、Microsoft Entra IDはカスタム CSS レイアウトのサポートを廃止し、会社のブランド化に*プロパティを配置*します。 これらのプロパティは、要素がサインイン ページに表示される場所と、要素の階層化、サイズ設定、および表示方法を制御します。 たとえば、ページ コンテンツの移動、重なり合わせ、サイズ変更、非表示を切り替えることができます。 削除すると、サインイン ページ レイアウトの一貫性が保たれ、Microsoft Entra IDで予測できます。

この変更は、カスタム CSS 機能を完全に廃止するための最初のステップです。 Microsoft、事前通知、推奨される移行パス、およびその廃止前の将来の高度なカスタマイズ オプションを提供する計画です。

詳細については、[ブランド化されたサインインのセキュリティを強化Microsoft Entra ID](https://techcommunity.microsoft.com/blog/microsoft-entra-blog/microsoft-entra-id-enhances-security-of-branded-sign-ins/4537471)ブログ記事を参照してください。

#### Timeline

- **2026 年 7 月 21** 日以降: カスタム CSS をまだ使用していない 2026 年 1 月 6 日より前に作成されたテナントは、カスタム CSS を構成できません。
- **後で**:影響を受けるレイアウトと配置のプロパティは、グローバルに非推奨になります。 非推奨になった後、これらのプロパティはブロックされ、機能しなくなります。
- **最終的に**:カスタム CSS は完全に廃止されます。 Microsoftは、推奨される移行パスを含む、提供終了前のガイダンスをさらに共有します。

#### 影響を受ける人

この変更は、Microsoft Entra ID テナントが非推奨のレイアウトと配置プロパティをカスタム CSS で使用している組織に影響します。 Microsoft Entra 外部 IDテナントは影響を受けません。 2026 年 7 月 21 日以降、カスタム CSS をまだ使用していない 2026 年 1 月 6 日より前に作成されたテナントは、カスタム CSS を構成できません。

Microsoftは、影響を受けるお客様に事前に直接通知します。

#### 非推奨のプロパティ

カスタム CSS で次のいずれかのプロパティを使用している場合は、構成から削除します。 これらのプロパティには、サポートされている移行や置換はありません。

- `position` ( `top`、 `right`、 `bottom`、 `left`、 `z-index`を含む)
- `margin` ( `margin-top`、 `margin-bottom`、 `margin-left`、 `margin-right`を含む)
- `transform`
- `opacity`
- `overflow`
- `filter`
- `pointer-events`
- `clip-path`
- `mix-blend-mode`
- `translate`
- `-webkit-transform`
- `-moz-transform`
- `-ms-transform`
- `-o-transform`
- `scale`
- `rotate`
- `perspective`
- `display`
- `visibility`
- `inset`
- `zoom`
- `offset`
- `offset-path`
- `offset-distance`
- `margin-block`
- `margin-block-start`
- `margin-block-end`
- `margin-inline`
- `margin-inline-start`
- `margin-inline-end`
- `order`
- `grid-area`
- `grid-column`
- `grid-column-start`
- `grid-column-end`
- `grid-row`
- `grid-row-start`
- `grid-row-end`
- `isolation`
- `overflow-x`
- `overflow-y`
- `overflow-block`
- `overflow-inline`
- `content-visibility`
- `clip`
- `mask`
- `mask-image`
- `-webkit-mask`
- `-webkit-mask-image`

#### カスタム CSS を確認して更新する

現在の CSS ファイルを調べるには、https://entra.microsoft.comのMicrosoft Entra 管理センターの **[カスタム ブランド]** ページで [**編集]** を選択します。 [ **レイアウト** ] タブの [ **カスタム CSS**] で、[ **ダウンロード**] を選択します。 ダウンロードしたファイルで、「非推奨のプロパティ」に記載されているプロパティを確認 します。

会社のブランド化のローカライズをすべて確認するには、Microsoft Graphを使用して現在の会社のブランド化構成をエクスポートし、テナント ブランドインスペクター ツールで確認します。 グローバル管理者またはブランド管理者アカウントが必要です。

1. [Microsoft Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)に移動し、テナントにサインインします。
2. テナント ID がわからない場合は、次の URL に `GET` 要求を送信し、応答から `id` 値をコピーします。

    ```http
    https://graph.microsoft.com/v1.0/organization
    ```
3. 次の URL に `GET` 要求を送信して、構成されているすべての会社ブランドのローカライズを返します。 `<tenant-id>` を実際のテナント ID に置き換えます。

    ```http
    https://graph.microsoft.com/v1.0/organization/<tenant-id>/branding/localizations
    ```
4. 応答をコピーするか、JSON ファイルにエクスポートします。
5. [テナント ブランドインスペクター ツール](https://entra-branding-tools.github.io/tenant-branding-inspector/)に移動します。
6. コピーした応答を貼り付けるか、エクスポートした JSON ファイルをツールにアップロードします。 このツールでは、各ロケールと、使用されている非推奨のプロパティが一覧表示されます。

非推奨のプロパティを削除するには、ダウンロードした CSS ファイルを編集し、[Microsoft Entra 管理センター](https://entra.microsoft.com)の **[会社のブランド]** ページにアップロードして、変更を保存します。 運用環境を更新する前に視覚的な影響を検証するには、まずテスト テナントに変更を適用します。

### HTML セレクター

次の CSS スタイルは、ページ全体の既定の本文およびリンク スタイルになります。 他のリンクまたはテキストに適用するスタイルは、これらのセレクターよりも優先されます。

- `body` - ページ全体のスタイル
- リンクのスタイル:
    - `a, a:link` - すべてのリンク
    - `a:hover` - マウスがリンクの上にある場合
    - `a:focus` - リンクにフォーカスがあるとき
    - `a:focus:hover` - リンクにフォーカス *があり、マウスがリンクの上* 場合
    - `a:active` - リンクがクリックされたとき

### Microsoft Entra CSS セレクター

次の CSS セレクターを使用して、サインイン エクスペリエンスの詳細を構成します。

注

内部ナビゲーション リンクをカスタマイズするには、新しいカスタム CSS セレクター ( `.ext-link`) を使用します。

- `.ext-background-image` - 既定のライトボックス テンプレートに背景イメージを含むコンテナー
- `.ext-header` - コンテナーの上部にあるヘッダー
- `.ext-header-logo` - コンテナーの上部にあるヘッダー ロゴ

    [Image: .ext-header と .ext-header-logo 領域が強調表示されているサインイン画面のスクリーンショット。]
- `.ext-middle` - サインイン ボックスを中央に、水平方向を中央に揃える全画面表示の背景のスタイル
- `.ext-vertical-split-main-section` - サインイン ボックスと背景の両方を含む垂直分割テンプレートの部分画面背景のコンテナーのスタイル (このスタイルは、Active Directory フェデレーション サービス (ADFS) テンプレートとも呼ばれます)。
- `.ext-vertical-split-background-image-container` - 垂直分割/ADFS テンプレートのサインイン ボックスの背景
- `.ext-sign-in-box` - サインイン ボックス コンテナー
- `.ext-title` - タイトル テキスト

    [Image: サインイン ボックスのスクリーンショット。ボックスの一部が .ext-sign-in-box セレクターでスタイル設定されています。]
- `.ext-subtitle` - 字幕テキスト
- リンクのスタイル:

    - `.ext-link` - 内部ナビゲーション リンク
- 主ボタンのスタイル:

    - `.ext-button.ext-primary` - プライマリ ボタンの既定のスタイル
    - `.ext-button.ext-primary:hover` - マウスがボタンの上にある場合
    - `.ext-button.ext-primary:focus` - ボタンにフォーカスがあるとき
    - `.ext-button.ext-primary:focus:hover` - ボタンにフォーカスが *し、マウスがボタンの上にある* 場合
    - `.ext-button.ext-primary:active` - ボタンがクリックされたとき

    [Image: プライマリ - 次へ - ボタンが強調表示されているサインイン ボックスのスクリーンショット。]
- セカンダリ ボタンのスタイル:

    - `.ext-button.ext-secondary` - セカンダリ ボタン
    - `.ext-button.ext-secondary:hover` - マウスがボタンの上にある場合
    - `.ext-button.ext-secondary:focus` - ボタンにフォーカスがあるとき
    - `.ext-button.ext-secondary:focus:hover` - ボタンにフォーカスが *し、マウスがボタンの上にある* 場合
    - `.ext-button.ext-secondary:active` - ボタンがクリックされたとき

    [Image: [サインイン オプション] ステップのサインイン ボックスのスクリーンショット。セカンダリ - [戻る] ボタンが強調表示されています。]
- `.ext-error` - エラー テキスト

    [Image: エラー テキストが強調表示されているサインイン ボックスのスクリーンショット。]
- テキスト ボックスのスタイル:

    - `.ext-input.ext-text-box` - テキスト ボックス
    - `.ext-input.ext-text-box.ext-has-error` - テキスト ボックスに関連付けられている検証エラーがある場合
    - `.ext-input.ext-text-box:hover` - マウスがテキスト ボックスの上にある場合
    - `.ext-input.ext-text-box:focus` - テキスト ボックスにフォーカスがある場合
    - `.ext-input.ext-text-box:focus:hover` - テキスト ボックスにフォーカス *があり、マウスがテキスト ボックスの上* 場合

    [Image: サンプル テキストが強調表示されたテキスト ボックスを含むサインイン ボックスのスクリーンショット。]
- `.ext-boilerplate-text` - サインイン ボックスの下部にあるカスタム メッセージ テキスト
- `.ext-promoted-fed-cred-box` - [サインイン オプション] テキスト ボックス

    [Image: オプションの定型テキスト領域が強調表示されているサインイン ボックスのスクリーンショット。]
- フッターのスタイル:

    - `.ext-footer` - ページ下部のフッター領域
    - `.ext-footer-links` - ページ下部のフッターのリンク領域
    - `.ext-footer-item` - ページ下部のフッターにアイテム ([使用条件] や [プライバシー & Cookie] など) をリンクする
    - `.ext-debug-item` - ページの下部にあるフッターのデバッグの詳細の省略記号
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/security-defaults"} -->
## Microsoft Entra ID のセキュリティの既定値を構成する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/security-defaults
- Service: entra / fundamentals
- Article date: 2025-07-21
- Summary: Microsoft Entra ID セキュリティの既定値を有効にして、構成済みの MFA 要件とレガシ認証保護を使用して組織のセキュリティ体制を強化します。

セキュリティの既定値群を使用すると、今日の環境で一般的なパスワード スプレー、リプレイ、フィッシングなどの ID 関連の攻撃から組織を簡単に保護できます。

セキュリティの管理は難しい場合があるため、Microsoft では、すべてのユーザーが事前構成されたセキュリティの既定値群を使用できるようにしています。 Microsoft の知見によれば、これらの一般的な ID 関連攻撃の 99.9% 以上は、多要素認証を使ってレガシ認証をブロックすることで阻止されます。 Microsoft の目標は、すべての組織が追加の費用なしで少なくとも基本レベルのセキュリティを確実に有効にできるようにすることです。

これらの基本的なコントロールには、次のものが含まれます:

- すべてのユーザーに対して、多要素認証への登録を必須にする
- 管理者に多要素認証の実行を要求する
- 必要に応じてユーザーに多要素認証の実行を要求する
- レガシ認証プロトコルをブロックする
- デバイス コード フローのブロック
- Azure portal へのアクセスなどの特権が必要な作業を保護する

### 対象者は誰ですか？

- セキュリティ体制を向上させたいが、どこから始めればいいのかわからない組織。
- Microsoft Entra ID ライセンスの Free レベルを使用している組織。

#### 条件付きアクセスを利用すべき人々

- Microsoft Entra ID P1 または P2 ライセンスを所有している組織の場合、セキュリティの既定値群が適切でない可能性があります。
- 組織に複雑なセキュリティ要件がある場合は、[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policy-common)を検討する必要があります。

### セキュリティの既定値群の有効化

2019 年 10 月 22 日以降に作成されたテナントの場合、セキュリティの既定値群がテナントで有効になっている可能性があります。 すべてのユーザーを保護するために、セキュリティの既定値群は、作成時にすべての新しいテナントにロールアウトされます。

既定では、すべての新しいテナントでセキュリティの既定値が有効になっていますが、保護が適用されるまでに 24 時間の猶予期間があります。 これにより、顧客は MFA が必要になる前にテナントにアクセスしてプロビジョニングできます。

組織を保護するために、Microsoft は常に Microsoft アカウント サービスのセキュリティの向上に取り組んでいます。 この保護の一環として、お客様は次の場合にセキュリティの既定値の自動有効化について定期的に通知されます:

- 条件付きアクセス ポリシーがない
- Premium ライセンスを持っていない
- レガシ認証クライアントを積極的に使用していない

この設定を有効にすると、組織内のすべてのユーザーが多要素認証に登録する必要があります。 混乱を避けるため、受信したメールを参照してください。また、有効にした後にセキュリティ規制値群を無効にすることもできます。

ディレクトリでセキュリティの既定値群を構成するには、少なくともご自身に[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)のロールが割り当てられている必要があります。

既定では、Microsoft Entra テナントを作成するユーザーには、 [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) ロールが自動的に割り当てられます。

次のようにして、セキュリティの既定値群を有効にします。

1. [条件付きアクセス管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)にサインインします。
2. **Entra ID**&gt;**Overview**&gt;**Properties** に移動します。
3. **[セキュリティの既定値群の管理]** を選択します。
4. **[セキュリティの既定値群]** を **[有効]** に設定します。
5. **[保存]** を選択します。

[Image: セキュリティの既定値群を有効にするためのトグルがある Microsoft Entra 管理センターのスクリーンショット]

#### アクティブなトークンの取り消し

セキュリティの既定値を有効にする一環として、管理者は既存のすべてのトークンを取り消して、すべてのユーザーに多要素認証の登録を要求する必要があります。 この失効イベントにより、以前に認証されたユーザーが多要素認証の認証と登録を強制されます。 このタスクは、Microsoft Graph PowerShell SDKの [Revoke-MgUserSignInSession](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users.actions/revoke-mgusersigninsession) コマンドレットを使用して実行できます。

### 適用されたセキュリティ ポリシー

#### すべてのユーザーに対して Microsoft Entra 多要素認証への登録を必須にする

注

2024 年 7 月 29 日以降、新しいテナントと既存のテナントでは、ユーザーが MFA に登録するための 14 日間の猶予期間が削除されます。 MFA は 99.2% を超える ID ベースの攻撃をブロックできるため、14 日間の期間中にアカウント侵害のリスクを軽減するために、この変更を行いました。

ユーザーがサインインして、多要素認証の実行を求められると、Microsoft Authenticator アプリに入力する番号を示す画面が表示されます。 この対策は、ユーザーが MFA 疲労攻撃に陥るのを防ぐうえで役立ちます。

[Image: 入力する番号が表示された [サインイン要求の承認] ウィンドウの例を示したスクリーンショット。]

#### 管理者に多要素認証の実行を要求する

管理者は、環境に対するより多くのアクセス権を持ちます。 これらの高い特権を持つアカウントの能力のために、特別な注意を払って扱ってください。 特権アカウントの保護を強化するための一般的な方法の 1 つは、サインイン時に、より強力な形式のアカウント検証を必須にすること (多要素認証を必須にするなど) です。

ヒント

管理者向けの推奨事項:

- 認証方法に登録できるように、セキュリティの既定値を有効にした後は、すべての管理者がサインインするようにしてください。
- 管理者の MFA の回数を大幅に減らすために、管理タスクと標準の生産性タスク用に個別のアカウントを用意してください。

次の管理者の役割では、登録が完了した後、サインインのたびに多要素認証を実行する必要があります。

- グローバル管理者
- アプリケーション管理者
- 認証管理者
- 請求管理者
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

- 認証ポリシー管理者
- アイデンティティガバナンス管理者

*セキュリティの既定値を無効にした場合は[、Microsoft管理の条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/managed-policies#upgrade-from-security-defaults)としても使用できます。*

#### 必要に応じてユーザーに多要素認証の実行を要求する

認証の追加のレイヤーが必要なアカウントは管理者アカウントだけであると考えがちです。 管理者は、機密情報への広範なアクセス権を持ち、サブスクリプション全体の設定に変更を加えることができます。 しかし、多くの場合、攻撃者はエンド ユーザーをターゲットにします。

これらの攻撃者は、アクセス権を取得した後、元のアカウント所有者の代わりに機密性の高い情報へのアクセスを要求できます。 ディレクトリ全体をダウンロードして、組織全体に対してフィッシング攻撃を実行することさえできます。

すべてのユーザーを対象にした保護を向上させるための一般的な方法の 1 つは、全員に多要素認証を要求するなど、より強力な形式のアカウント検証を要求することです。 ユーザーが登録を完了すると、必要に応じて他の認証を求められるようになります。 Microsoft では、場所、デバイス、役割、タスクなどの要素に基づいて、ユーザーに多要素認証を求めるタイミングが決定されます。 この機能は、SaaS アプリケーションを含め、登録されているすべてのアプリケーションを保護します。

注

[B2B 直接接続](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-direct-connect-overview)ユーザーの場合、リソース テナントで有効になっているセキュリティの既定値からの多要素認証要件を満たす必要があります。これには、ホーム テナント内の直接接続ユーザーによる多要素認証の登録が含まれます。

*セキュリティの既定値を無効にした場合は[、Microsoft管理の条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/managed-policies#upgrade-from-security-defaults)としても使用できます。*

#### レガシ認証プロトコルをブロックする

ユーザーがクラウド アプリに簡単にアクセスできるように、レガシ認証を含め、さまざまな認証プロトコルがサポートされています。 "*レガシ認証*" は、以下のものによって行われる認証要求を指す用語です。

- 先進認証を使用していないクライアント (Office 2010 クライアントなど)
- IMAP、SMTP、POP3 などの古いメール プロトコルを使用しているクライアント

現在、危険にさらそうとするサインイン試行はほとんどレガシ認証から来ています。 レガシ認証では、多要素認証がサポートされていません。 ディレクトリで多要素認証 ポリシーが有効になっている場合でも、攻撃者は、古いプロトコルを使用して認証を受け、多要素認証をバイパスすることができます。

テナントでセキュリティ デフォルトが有効になった後は、古いプロトコルによるすべての認証要求がブロックされます。 セキュリティ既定値は、Exchange Active Sync 基本認証をブロックします。

警告

セキュリティの既定値群を有効にする前に、管理者が古い認証プロトコルを使用していないことを確認してください。 詳細については、[レガシ認証から移行する方法](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-block-legacy-authentication)に関するページを参照してください。

- [Microsoft 365 を使用して電子メールを送信するように多機能機器またはアプリケーションを設定する方法](https://learn.microsoft.com/ja-jp/exchange/mail-flow-best-practices/how-to-set-up-a-multifunction-device-or-application-to-send-email-using-microsoft-365-or-office-365)

*セキュリティの既定値を無効にした場合は[、Microsoft管理の条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/managed-policies#upgrade-from-security-defaults)としても使用できます。*

#### デバイス コード フローをブロックする

デバイス コード フローは、ユーザーが、ブラウザーやキーボードのないデバイスなど、入力機能が制限されているデバイスまたはアプリケーションにサインインできるようにする認証フローです。 攻撃者は、ユーザーをだまして別のデバイスにコードを入力することで、フィッシング攻撃のデバイス コード フローを悪用する可能性があります。

テナントでセキュリティの既定値を有効にすると、デバイス コード フローを使用する認証要求がブロックされます。 セキュリティの既定値が有効になっている間、デバイス コード フローに依存するアプリケーションまたはデバイスはサインインを完了できません。 組織で詳細な制御と例外が必要な場合は、 [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policy-common)を検討する必要があります。

注

2026 年 7 月 1 日以降、新しい Microsoft Entra テナントはすべて、セキュリティの既定値の一部としてデバイス コード フローをブロックします。 セキュリティの既定値が有効になっている間、デバイス コード フローに依存するアプリケーションまたはデバイスはサインインを完了できません。

#### Azure portal へのアクセスなどの特権が必要な作業を保護する

組織では、Azure Resource Manager API によって管理される、次のようなさまざまな Azure サービスを使用します。

- Azure portal
- Microsoft Entra 管理センター
- Azure PowerShell
- Azure CLI

Azure Resource Manager を使用してご自身のサービスを管理する操作は、高い権限が与えられているアクションです。 Azure Resource Manager では、サービス設定、サブスクリプションの課金など、テナント全体の構成を変更できます。 単一要素認証は、フィッシング、パスワード スプレーなどのさまざまな攻撃に対して脆弱です。

Azure Resource Manager にアクセスして構成を更新しようとするユーザーの ID を検証することが重要です。 アクセスを許可する前に、追加の認証を要求して ID を検証します。

テナントでセキュリティの既定値群を有効にした後、次のサービスにアクセスするすべてのユーザーが多要素認証を完了する必要があります。

- Azure portal
- Microsoft Entra 管理センター
- Azure PowerShell
- Azure CLI

このポリシーは、Azure Resource Manager サービスにアクセスしようとしているユーザーであれば、管理者であるかユーザーであるかに関係なく全員に適用されます。 このポリシーは、サブスクリプション、VM、ストレージ アカウントなどへのアクセスなど、Azure Resource Manager API に適用されます。 このポリシーには、Microsoft Entra ID も Microsoft Graph も含まれません。

注

2017 より前に作成された Exchange Online テナントでは、既定で先進認証が無効になっています。 これらのテナントを通じて認証を行うときにログイン ループの可能性を回避するために、[先進認証を有効にする](https://learn.microsoft.com/ja-jp/exchange/clients-and-mobile-in-exchange-online/enable-or-disable-modern-authentication-in-exchange-online)必要があります。

注

Microsoft Entra Connect/Microsoft Entra Cloud Sync 同期アカウント (または "ディレクトリ同期アカウント" ロールに割り当てられているセキュリティ プリンシパル) は、セキュリティの既定値から除外され、多要素認証の登録または実行を求められることはありません。 組織は、他の目的でこのアカウントを使用しないでください。

*セキュリティの既定値を無効にした場合は[、Microsoft管理の条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/managed-policies#upgrade-from-security-defaults)としても使用できます。*

### デプロイに関する考慮事項

#### ユーザーの準備

予定されている変更、登録要件、必要なユーザー操作について、ユーザーに通知することが重要です。 お客様のユーザーに新しいエクスペリエンスに向けて準備をさせ、ロールアウトの確実な成功を支援するために、[通信テンプレート](https://aka.ms/mfatemplates)と[ユーザー ドキュメント](https://support.microsoft.com/account-billing/set-up-security-info-from-a-sign-in-page-28180870-c256-4ebf-8bd7-5335571bf9a8)が用意されています。 ユーザーを https://myprofile.microsoft.com に誘導し、そのページの **[セキュリティ情報]** リンクを選択して登録してもらいます。

#### 認証方法

セキュリティの既定値群のユーザーは、[通知を使用する Microsoft Authenticator アプリ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-authenticator-app)を使って多要素認証に登録し、多要素認証を使用する必要があります。 ユーザーは、Microsoft Authenticator アプリからの確認コードを使用できますが、通知オプションを使用した場合にのみ登録できます。 ユーザーは、 [OATH TOTP](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-oath-tokens) を使用して Microsoft 以外のアプリケーションを使用してコードを生成することもできます。

警告

セキュリティの既定値を使用している場合は、組織のメソッドを無効にしないでください。 方法を無効にすると、テナントからロックアウトされる可能性があります。 **MFA サービス設定ポータル**で、 [\[ユーザーが使用できる方法\]](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-getstarted#choose-authentication-methods-for-mfa) をすべて有効のままにしておきます。

#### B2B ユーザー

ディレクトリにアクセスするすべての [B2B ゲスト](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) ユーザーまたは [B2B 直接接続](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-direct-connect-overview)ユーザーは、組織のユーザーと同じように扱われます。

#### 無効化された MFA の状態

組織が以前にユーザーごとの多要素認証を使用していた場合、多要素認証の状態のページを確認したときに、ユーザーが **[有効]** または **[強制]** 状態になっていなくても問題ありません。 セキュリティの既定値群または条件付きアクセスをベースとする多要素認証を使用しているユーザーの場合は、**[無効]** が適切な状態です。

### セキュリティの既定値群を無効にする

セキュリティの既定値群を置き換える条件付きアクセス ポリシーを実装する組織では、セキュリティの既定値群を無効にする必要があります。

ディレクトリでセキュリティの既定値を無効にします。

1. [条件付きアクセス管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)にサインインします。
2. **Entra ID**&gt;**Overview**&gt;**Properties** に移動します。
3. **[セキュリティの既定値群の管理]** を選択します。
4. **[セキュリティの既定値群]** を **[Disabled (not recommended)] (無効 (非推奨))** に設定します。
5. **[保存]** を選択します。

#### セキュリティの既定値群から条件付きアクセスに移行する

セキュリティの既定値群は、セキュリティ態勢を開始するための適切なベースラインですが、多くの組織が必要とするカスタマイズに対応していません。 条件付きアクセス ポリシーは、より複雑な組織が必要とするさまざまなカスタマイズを提供します。

| - | セキュリティのデフォルト設定 | 条件付きアクセス |
| --- | --- | --- |
| **必要なライセンス** | なし | Microsoft Entra ID P1 以上 |
| **カスタマイズ** | カスタマイズなし (オンまたはオフ) | フル カスタマイズが可能 |
| **によって有効化される** | Microsoft または管理者 | Microsoft または管理者 |
| **複雑性** | 簡単に使用できる | 要件に基づいてフル カスタマイズが可能 |

条件付きアクセスの機能をテストしたい組織は、[無料試用版にサインアップ](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)して開始できます。

管理者がセキュリティの既定値を無効にした後、組織は直ちに条件付きアクセス ポリシーを有効にして組織を保護する必要があります。 [Microsoft管理された条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/managed-policies#upgrade-from-security-defaults)は、従来の認証のブロック、Azure管理に MFA を要求する、管理者に MFA を要求する、すべてのユーザーに MFA を要求する、同じ保護を維持するために使用できます。 組織はこれらのポリシーを確認し、 [条件付きアクセス テンプレートのセキュリティで保護された基盤カテゴリ](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policy-common?tabs=secure-foundation#template-categories)からさらに多くのポリシーを有効にすることを検討する必要があります。 Microsoft Entra ID 保護 を含む Microsoft Entra ID P2 ライセンスを持つ組織は、この一覧を展開して、[ユーザー リスクベースとサインイン リスクベースのポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)を含めて態勢をさらに強化できます。

Microsoft は、組織が[全体管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)ロールが永続的に割り当てられる 2 つのクラウド専用緊急アクセス アカウントを作成することを推奨しています。 このようなアカウントは高い特権を持っており、特定のユーザーには割り当てられません。 アカウントは、通常のアカウントを使用できない緊急時、またはすべての管理者が誤ってロックアウトされるような「ブレイクグラス」（緊急時用）シナリオに限定されます。これらのアカウントは、[緊急アクセス アカウントに関する推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)に従って作成する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/try-microsoft-entra-suite"} -->
## Microsoft Entra Suite を無料で試す - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/try-microsoft-entra-suite
- Service: entra / fundamentals
- Article date: 2025-04-02
- Summary: Microsoft Entra Suite の試用版を最大限に活用してください。 主要な生産性とセキュリティ機能のいくつかを試してみてください。

### 概要

Microsoft Entra Suite 試用版ユーザー ガイドへようこそ。 [Microsoft Entra](https://learn.microsoft.com/ja-jp/entra/fundamentals/what-is-entra) の堅牢で包括的な機能を見つけて、無料試用版を最大限に活用してください。

ヒント

この試用版ユーザー ガイドをブラウザーのお気に入りに保存します。 試用版ユーザー ガイド内のリンクによってこの場所から移動した場合は、このガイドに戻って続行する方が簡単です。

### Microsoft Entra スイートとは

[Microsoft Entra スイート](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing) は、統合されたゼロ トラスト ユーザー アクセスを提供し、従業員がクラウド アプリケーションやオンプレミス アプリケーションに安全にアクセスできるようにするソリューションです。 このスイートを使用すると、企業の境界の内外のパブリック ネットワークとプライベート ネットワーク間で最小限の特権アクセスを提供できます。 Microsoft Entra スイートは、ネットワーク アクセス、ID 保護、ガバナンス、および ID 検証ソリューションを組み合わせることで、ID とネットワーク制御全体に条件付きアクセスを拡張し、悪意のあるコンテンツを除外し、最小限の権限アクセスを確保して、従業員がオフィス内かリモートかに関係なく、シンプルで一貫したユーザー エクスペリエンスを実現します。

### 試用ライセンスの前提条件

- Microsoft Entra ID P1
- Microsoft Entra ID P1 または Microsoft Entra ID P2 を含むパッケージ (ME3 や ME5 など)

Microsoft Entra スイート の試用を開始するか購入するときの最初のステップは、どのライセンス オプションが組織に最適かを判断することです。 Microsoft Entra ID P2/E5 のお客様には特別価格が適用されます。 価格およびプランの詳細については、「[Microsoft Entra プランと価格](https://www.microsoft.com/security/business/microsoft-entra-pricing)」をご覧ください。

### Microsoft Entra スイート 試用版には何が含まれていますか?

Microsoft Entra スイート には次の製品が含まれています:

[**Microsoft Entra Private Access**](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-private-access): 従来の VPN のリスクと運用の複雑さを排除し、ユーザーの生産性を向上させます。 あらゆるデバイスやグローバル ネットワークから、オンプレミス、クラウド間、そしてその間のあらゆる場所にあるプライベート アプリにリモート ユーザーを迅速かつ安全に接続します。

[**Microsoft Entra Internet Access**](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-internet-access): ID 中心の Secure Web Gateway (SWG) を使用して、インターネットの脅威、悪意のあるネットワーク トラフィック、安全でないコンテンツや非準拠のコンテンツから組織を保護しながら、すべてのインターネット、SaaS、Microsoft 365 アプリとリソースへのグローバル アクセスを保護します。

[**Microsoft Entra ID ガバナンス**](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview): IT 環境全体でユーザー ID、アクセス権、権限を管理し、適切なアクセス制御を確保し、リスクを軽減し、規制要件への準拠を維持します。

[**Microsoft Entra ID Protection**](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection): さまざまなソースからの統合リスク スコアに基づいてユーザーとサインインのパターンを分析することにより、ID の乗っ取りをリアルタイムでブロックします。 フィッシング、感染したデバイス、漏洩した資格情報などの ID ベースの攻撃から保護します。

[**Microsoft Entra Verified ID**](https://learn.microsoft.com/ja-jp/entra/verified-id/decentralized-identifier-overview): 安全な検証方法でユーザーを検証し、ユーザー オンボーディングなどの安全な ID 認証シナリオ、機密リソースやアカウント回復プロセスへの安全なアクセスを確保します。

### Microsoft Entra スイートの製品ガイド

Microsoft Entra Suite 試用版を最大限に活用するには、次のハウツー ガイドを確認して、組織のより安全な環境を確保してください。

このセクションでは、次のハウツー ガイドについて説明します。

- 手順 1: ID 保護を展開する: リスクのあるユーザーの識別と保護を強化するためにセキュリティ制御を展開します。
- 手順 2: アクセス レビューを実施する: 企業内で適切なシステム アクセスを確保するためにアクセス レビューを実施します。
- 手順 3: インターネットへの安全なアクセス: 安全な Web ゲートウェイを使用してインターネット トラフィックを保護します。
- 手順 4: プライベート アクセス ゲートウェイを有効にする: コストのかかる VPN システムをクイック アクセスに置き換えます。
- 手順 5: ワークフロー ポータルを使用して顧客をオンボードする: ライフサイクル ワークフローを使用して従業員のオンボーディングを自動化します。

[Image: Microsoft Entra Suite ゼロ トラスト戦略の手順のスクリーンショット。]

次のセクションでは、各製品について説明するプロセス手順について説明します。 これらの各手順は、各手順の最後にあるリンクを選択してアクセスできる個別のハウツー ガイドに完全に記載されています。

#### 手順 1: Microsoft Entra ID Protection をデプロイする

Microsoft Entra ID Protection では、ID ベースのリスクを検出して報告し、管理者がこれらのリスクを調査および修復して、組織の安全とセキュリティを維持できるようにします。 リスク データは条件付きアクセスなどのツールにさらに入力し、アクセスに関する意思決定を行うか、セキュリティ情報イベント管理 (SIEM) ツールに入力し、詳細な解析や調査を行うことができます。

1. 既存のレポートの確認
2. 条件付きアクセス リスク ポリシーの計画
3. ポリシーを構成する
4. 監視と継続的な運用ニーズ

完全なハウツー ガイドについては、「[Microsoft Entra ID 保護 デプロイの計画](https://learn.microsoft.com/ja-jp/entra/id-protection/how-to-deploy-identity-protection)」をご覧ください。

#### 手順 2: アクセス レビューを実施する

Microsoft Entra アクセス レビューは、Microsoft Entra ID ガバナンス機能であり、組織がリソース アクセス ライフサイクルを管理することで企業のセキュリティを維持するのに役立ちます。 その他の機能には、エンタイトルメント管理、Privileged Identity Management (PIM)、ライフサイクル ワークフロー、プロビジョニング、使用条件などがあります。

1. アクセス パッケージ、グループ、アプリケーションのアクセス レビューを計画する
2. Microsoft Entra ID と Azure リソースの役割レビューを計画する
3. アクセスレビューの展開
4. アクセス レビュー API の使用
5. アクセスレビューの監視

完全な攻略ガイドについては、「[Microsoft Entra アクセス レビューの展開を計画する](https://learn.microsoft.com/ja-jp/entra/id-governance/deploy-access-reviews)」をご覧ください。

#### 手順 3: インターネットへのアクセスをセキュリティで保護する

エンタープライズ ユーザーとマネージド デバイスを悪意のあるインターネット トラフィックやマルウェア感染から保護する Microsoft Entra Internet Access は、すべての会社にとって重要です。 セキュア Web ゲートウェイ機能を使って、Microsoft Entra 条件付きアクセスと統合することで、Web カテゴリと完全修飾ドメイン名 (FQDN) に基づいてトラフィックをブロックします。

1. Microsoft Entra Internet Access のデプロイとテスト
2. サービス経由でルーティングされるすべてのインターネット トラフィックに適用されるベースライン ポリシーを作成する
3. カテゴリに基づいてグループが Web サイトにアクセスできないようにする
4. FQDN に基づいてグループが Web サイトにアクセスできないようにする
5. ブロックされた Web サイトへのアクセスをユーザーに許可する

完全な攻略ガイドについては、「[Microsoft Entra Internet Access のデプロイ ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/gsa-deployment-guide-internet-access)」をご覧ください。

#### 手順 4: プライベート アクセス ゲートウェイを有効にする

Microsoft Entra Private Access を使うと、ネットワークと ID のアクセス制御を統合して、任意の場所、デバイス、または ID から任意のアプリやリソースへのアクセスをセキュリティで保護できます。 従業員、ビジネス パートナー、デジタル ワークロードにアクセス ポリシー管理を実現して調整します。

1. Microsoft Entra Private Access の展開およびテスト
2. Microsoft Entra 条件付きアクセスを適用する
3. 複数のユーザーによる複数のアプリへのアクセスを制御する

完全な攻略ガイドについては、「[Microsoft Entra Private Access のデプロイ ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/gsa-deployment-guide-private-access)」をご覧ください。

#### 手順 5: ワークフロー ポータルを使用して顧客をオンボードする

Microsoft Entra 管理ポータルを使用すると、HR プロビジョニング プロセスを通じてライフサイクル ワークフローを使用して雇用前タスクを自動化できます。 プロビジョニングでは、特定の条件に基づいて、対象のシステムに ID を作成します。 プロビジョニング解除では、条件を満たさなくなった ID を対象のシステムから削除します。 これらのプロセスは、ID ライフサイクル管理の一部です。

1. 雇用前テンプレートを使ってワークフローを作成する
2. ワークフローを実行する
3. タスクとワークフローの状態を調べる
4. ワークフローのスケジュールを有効にする

完全な攻略ガイドについては、「[Microsoft Entra を使用して従業員のオンボーディング タスクを自動化する](https://learn.microsoft.com/ja-jp/entra/id-governance/tutorial-onboard-custom-workflow-portal)」をご覧ください。

### Microsoft Entra Suite の試用版を使用する顧客シナリオ

次の展開シナリオでは、5 つのすべての Microsoft Entra Suite 製品を組み合わせてテストする方法に関する詳細なガイダンスを提供します。 このセクションの各シナリオには、各シナリオの最後にあるリンクを選択してアクセスできる個別の手順が含まれています。

試用版を最大限に活用するため、まず次のユーザー シナリオについて説明します。

- シナリオ 1: すべてのアプリにアクセスしてユーザー オンボーディングとライフサイクルを自動化する
- シナリオ 2: アプリごとに MFA を使用してオンプレミス アプリへのリモート アクセスを最新化する
- シナリオ 3: ビジネス ニーズに基づいてインターネット アクセスをセキュリティで保護する

Microsoft Entra Suite の試用期間中は、より優れた一緒のセキュリティ戦略を活用してください。 自動化されたユーザー のオンボードとライフサイクル管理を実装し、多要素認証 (MFA) を使用して従来の VPN からオンプレミス リソースに最新化し、ビジネス ルールに基づいてインターネット アクセスをセキュリティで保護します。

次の表に、各シナリオで 5 つの Microsoft Entra Suite 製品のうちどれを取り上げるかを示します。

| 顧客のシナリオ | Microsoft Entra プライベートアクセス | Microsoft Entra インターネットアクセス | Microsoft Entra ID ガバナンス | Microsoft Entra ID Protection（マイクロソフト エントラ ID 保護） | Microsoft Entra 確認済み ID |
| --- | --- | --- | --- | --- | --- |
| 1 - すべてのアプリにアクセスしてユーザー オンボーディングとライフサイクルを自動化する |  | 含む | 含む | 含む | 含む |
| 2 - アプリごとに MFA を使用してオンプレミス リソースへの従来の VPN を最新化する |  | 含む | 含む | 含む |  |
| 3 - ビジネス ルールに基づいてインターネット アクセスをセキュリティで保護する | 含む |  | 含む | 含む |  |

#### シナリオ 1: すべてのアプリにアクセスしてユーザー オンボーディングとライフサイクルを自動化する

従業員とゲストのオンボード、アイデンティティ、アクセスのライフサイクルガバナンスのシナリオは、以下の目標を説明しています。

- リモートの従業員が必要なアプリやリソースに安全かつシームレスにアクセスできるようにする。
- 関連するアプリやリソースへのアクセスを外部ユーザーに提供して共同作業する。

この段階的なガイダンスは、Microsoft Entra Verified ID、Microsoft Entra ID ガバナンス、Microsoft Entra ID Protection、Microsoft Entra 条件付きアクセスに焦点を当てています。 詳細については、「[Microsoft Entra の展開シナリオ - 従業員とゲストのライフサイクル](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-scenario-workforce-guest)」をご覧ください。

#### シナリオ 2: アプリごとに MFA を使用してオンプレミス アプリへのリモート アクセスを最新化する

「アプリごとに MFA を使用してオンプレミス アプリへのリモート アクセスを最新化する」シナリオでは、以下の目標について説明します。

- 既存の VPN を、Secure Access Service Edge (SASE) への移行に役立つスケーラブルなクラウドベースのソリューションにアップグレードします。
- ビジネス アプリケーションのアクセスが企業ネットワーク接続に依存する問題を解決します。

この段階的なガイダンスは、Microsoft Entra Private Access、Microsoft Entra ID Protection、Microsoft Entra ID ガバナンスに焦点を当てています。 詳細については、「[Microsoft Entra の展開シナリオ - リモート アクセスを最新化する](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-scenario-remote-access)」をご覧ください。

#### シナリオ 3: ビジネス ニーズに基づいてインターネット アクセスをセキュリティで保護する

「ビジネス ニーズに基づいてインターネット アクセスをセキュリティで保護する」シナリオでは、以下の目標について説明します。

- Microsoft Entra Internet Access の制御を使用して、既存の厳格な既定のインターネット アクセス ポリシーを強化します。
- [マイ アクセス] で禁止されているサイトへのアクセスをユーザーが要求できるようにします。 承認プロセスでは、アクセス権を付与するグループにユーザーを追加します。 たとえば、マーケティング部署にはソーシャル ネットワーク サイトへのアクセス、インシデントを調査中のセキュリティ部署にはリスクの高いインターネット接続先へのアクセスなどです。

この段階的なガイダンスは、Microsoft Entra Internet Access、Microsoft Entra ID ガバナンス、Microsoft Entra 条件付きアクセス、グローバル セキュア アクセスに焦点を当てています。 詳細については、「[Microsoft Entra の展開シナリオ - インターネット アクセスをセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-scenario-internet-access)」をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/users-default-permissions"} -->
## 既定のユーザー アクセス許可 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions
- Service: entra / fundamentals
- Article date: 2026-06-18
- Summary: Microsoft Entra ID で使用できる既定のユーザー アクセス許可を比較し、アクセスを制限する方法について説明します。

### 概要

Microsoft Entra ID では、すべてのユーザーに既定のアクセス許可のセットが付与されます。 ユーザーのアクセスは、ユーザーの種類、 [ロールの割り当て](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-subscriptions-associated-directory)、および個々のオブジェクトの所有権で構成されます。

この記事では、これらの既定のアクセス許可について説明し、メンバーとゲスト ユーザーの既定値を比較します。 既定のユーザー アクセス許可は、Microsoft Entra ID のユーザー設定でのみ変更できます。

### メンバーとゲスト ユーザー

既定のアクセス許可のセットは、ユーザーがテナントのネイティブ メンバー (メンバー ユーザー) かどうか、それとも企業間 (B2B) コラボレーション ゲスト (ゲスト ユーザー) としての別のディレクトリからのユーザーかどうかによって異なります。 ゲスト ユーザーの追加の詳細については、「 [Microsoft Entra B2B コラボレーションとは」](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)を参照してください。 既定のアクセス許可の権限を次に示します。

- *メンバー ユーザー* は、アプリケーションの登録、自分のプロフィール写真と携帯電話番号の管理、自分のパスワードの変更、B2B ゲストの招待を行うことができます。 また、すべてのディレクトリ情報を読むこともできます (いくつか例外があります)。
- *ゲスト ユーザー* には、ディレクトリのアクセス許可が制限されています。 自分のプロファイルの管理や自分のパスワードの変更、他のユーザー、グループ、アプリに関する情報の取得を行うことができる。 ただし、すべてのディレクトリ情報は読み取れません。

    たとえば、ゲスト ユーザーは、ユーザー、グループ、およびその他のディレクトリ オブジェクトすべてが含まれる一覧を列挙できません。 ゲストを管理者ロールに追加することができ、追加すると、読み取りと書き込みのすべてのアクセス許可が付与されます。 ゲスト招待設定で許可されている場合、ゲストは他 [のゲストを招待](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure#to-configure-guest-invite-settings) することもできます。

Note

Intune では独自の RBAC システムを使用してデバイス管理機能へのアクセスを管理するため、制限されたゲスト ユーザーは適切なアクセス許可を持つ Intune ポータルにアクセスできます。

### メンバーとゲストの既定のアクセス許可を比較する

| **Area** | **メンバー ユーザーのアクセス許可** | **既定のゲスト ユーザーのアクセス許可** | **制限されたゲスト ユーザーのアクセス許可** |
| --- | --- | --- | --- |
| ユーザーと連絡先 | - すべてのユーザーと連絡先の一覧を列挙する<br>- ユーザーと連絡先のすべてのパブリック プロパティを読み取る<br>- ゲストを招待する<br>- 自分のパスワードを変更する<br>- 自分の携帯電話番号を管理する<br>- 独自の写真を管理する<br>- 自分の更新トークンを無効にする | - 独自のプロパティを読み取る<br>- 他のユーザーと連絡先の表示名、メール アドレス、サインイン名、写真、ユーザー プリンシパル名、ユーザーの種類の各プロパティを読み取る<br>- ゲスト招待設定で許可されている場合は、他のゲストを招待する<br>- 自分のパスワードを変更する<br>- 別のユーザーをオブジェクト ID で検索する (許可されている場合)<br>- 他のユーザーの上司と直属の部下の情報を読み取る | - 独自のプロパティを読み取る<br>- ゲスト招待設定で許可されている場合は、他のゲストを招待する<br>- 自分のパスワードを変更する<br>- 自分の携帯電話番号を管理する |
| Groups | - セキュリティ グループを作成する<br>- Microsoft 365 グループを作成する<br>- すべてのグループの一覧を列挙する<br>- グループのすべてのプロパティを読み取る<br>- 非表示でないグループのメンバーシップを読み取る<br>- 参加しているグループで非表示にされている Microsoft 365 グループのメンバーシップを読み取る<br>- ユーザーが所有するグループのプロパティ、所有権、メンバーシップを管理する<br>- 所有するグループにゲストを追加する<br>- グループ メンバーシップの設定を管理する<br>- 所有するグループを削除する<br>- 所有する Microsoft 365 グループを復元する | - アクティブおよび対象となるメンバーシップと所有権（グループに参加していない場合も含む）を含む、非表示でないグループのプロパティを読み取る<br>- 参加しているグループで非表示にされている Microsoft 365 グループのメンバーシップを読み取る<br>- 表示名またはオブジェクト ID でグループを検索する (許可されている場合) | - 参加しているグループのオブジェクト ID を読み取る<br>- 一部の Microsoft 365 アプリで参加しているグループのメンバーシップと所有権を読み取る (許可されている場合) |
| Applications | - 新しいアプリケーションを登録 (作成) する<br>- すべてのアプリケーションの一覧を列挙する<br>- 登録済みアプリケーションとエンタープライズ アプリケーションのプロパティを読み取る<br>- 所有するアプリケーションのプロパティ、割り当て、資格情報を管理する<br>- ユーザーのアプリケーション パスワードを作成または削除する<br>- 所有するアプリケーションを削除する<br>- 所有するアプリケーションを復元する<br>- アプリケーションに付与されるアクセス許可を一覧表示する | - 登録済みアプリケーションとエンタープライズ アプリケーションのプロパティを読み取る<br>- アプリケーションに付与されるアクセス許可を一覧表示する | - 登録済みアプリケーションとエンタープライズ アプリケーションのプロパティを読み取る<br>- アプリケーションに付与されるアクセス許可を一覧表示する |
| エージェント | - すべてのブループリント、ブループリント プリンシパル、およびエージェント ID の一覧を列挙する<br>- ブループリント、ブループリント プリンシパル、およびエージェント ID のプロパティの読み取り<br>- 所有しているブループリント、ブループリント プリンシパル、およびエージェント ID のプロパティ、割り当て、資格情報を管理する<br>- 所有するブループリント、ブループリントプリンシパル、およびエージェント識別子を削除する<br>- ブループリント、ブループリント プリンシパル、およびエージェント ID のスポンサーがスポンサーとしてリストされている場合は、そのスポンサーを削除して更新します<br>- ブループリント プリンシパルとエージェント ID に付与されるアクセス許可を一覧表示する<br>- ブループリントの所有者である場合、ブループリント プリンシパルおよびエージェント ID を作成する<br>- ブループリント プリンシパルの所有者である場合、エージェント ID を作成する | なし | なし |
| Devices | - すべてのデバイスの一覧を列挙する<br>- デバイスのすべてのプロパティを読み取る<br>- 所有するデバイスのすべてのプロパティを管理する | アクセス許可なし | アクセス許可なし |
| Organization | - 会社のすべての情報を読み取る<br>- すべてのドメインを読み取る<br>- 証明書ベースの認証の構成を読み取る<br>- すべてのパートナー契約を読み取る<br>- マルチテナント組織の基本的な詳細とアクティブなテナントを読み取る<br>- 呼び出し元アプリに Directory.Read.All または Directory.ReadWrite.All の委任されたアクセス許可が付与されている場合は、テナント全体のディレクトリ設定 (Microsoft Graph の directorySetting オブジェクトと groupSetting オブジェクト) を読み取ります | - 会社の表示名を読み取る<br>- すべてのドメインを読み取る<br>- 証明書ベースの認証の構成を読み取る | - 会社の表示名を読み取る<br>- すべてのドメインを読み取る |
| ロールとスコープ | - すべての管理者ロールとメンバーシップを読み取る<br>- 管理単位のすべてのプロパティとメンバーシップを読み取る | アクセス許可なし | アクセス許可なし |
| Subscriptions | - すべてのライセンス サブスクリプションを読み取る<br>- サービス プランのメンバーシップを有効にする | アクセス許可なし | アクセス許可なし |
| Policies | - ポリシーのすべてのプロパティを読み取る<br>- 所有するポリシーのすべてのプロパティを管理する | アクセス許可なし | アクセス許可なし |
| 使用条件 | ユーザーが同意した利用規約を読み取る。 | ユーザーが同意した利用規約を読み取る。 | ユーザーが同意した利用規約を読み取る。 |

Note

テナント全体のディレクトリ設定の読み取りなど、メンバー ユーザーに一覧表示されている一部の読み取りアクセス許可は、呼び出し元アプリに Directory.Read.All などの適切な委任されたアクセス許可が付与されている場合にのみ、Microsoft Graph経由でアクセスできます。 ユーザーのロールとアプリの委任されたアクセス許可の両方で、操作を許可する必要があります。

### メンバー ユーザーの既定のアクセス許可を制限する

ユーザーの既定のアクセス許可に制限を追加できます。

メンバー ユーザーの既定のアクセス許可は、次の方法で制限できます。

Caution

**[Microsoft Entra 管理ポータルへのアクセスを制限する**] 設定では、一般的にアクセスされる一連の管理センター ページへのアクセスが制限されます。 **セキュリティ対策ではありません**。 設定の詳細については、次の表を参照してください。

| Permission | 設定の説明 |
| --- | --- |
| **アプリケーションを登録する** | このオプションを **[いいえ** ] に設定すると、ユーザーはアプリケーションの登録を作成できなくなります。 その場合、特定のユーザーをアプリケーション開発者ロールに追加することで、そのユーザーにこの権限を付与できます。 |
| **ユーザーが職場または学校アカウントを LinkedIn に接続できるようにする** | このオプションを **[いいえ** ] に設定すると、ユーザーは職場または学校アカウントを LinkedIn アカウントに接続できなくなります。 詳細については、 [LinkedIn アカウント接続のデータ共有と同意](https://learn.microsoft.com/ja-jp/entra/identity/users/linkedin-user-consent)に関するページを参照してください。 |
| **セキュリティ グループを作成する** | このオプションを **[いいえ]** に設定すると、ユーザーはセキュリティ グループを作成できなくなります。 少なくともユーザー管理者ロールが割り当てられているユーザーは、引き続きセキュリティ グループを作成できます。 方法については、 [グループ設定を構成するための Microsoft Entra コマンドレットを](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-settings-cmdlets)参照してください。 |
| **Microsoft 365 グループを作成する** | このオプションを **[いいえ** ] に設定すると、ユーザーは Microsoft 365 グループを作成できなくなります。 このオプションを **[一部** ] に設定すると、一連のユーザーが Microsoft 365 グループを作成できます。 少なくとも [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) ロールが割り当てられているすべてのユーザーは、引き続き Microsoft 365 グループを作成できます。 方法については、 [グループ設定を構成するための Microsoft Entra コマンドレットを](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-settings-cmdlets)参照してください。 |
| **Microsoft Entra 管理ポータルへのアクセスを制限する** | **このスイッチは何をしますか?**このオプションを **[いいえ** ] に設定すると、管理者以外のユーザーは Microsoft Entra 管理センターにサインインできます。 このオプションを **[はい** ] に設定すると、カジュアルな閲覧に摩擦のレイヤーが追加されます。 この設定では、管理者以外が Microsoft Entra 管理センターと Azure portal で頻繁にアクセスする一連のページ (ホーム、テナントの概要、ユーザーリストなど) を読み込むのを制限します。 グループを所有する管理者以外は、Microsoft Entra 管理センターまたは Azure portal を使用してこれらのリソースを管理することはできません。 ユーザーが直接 (ディープ) リンクを持っている場合、管理センターのほとんどのページに到達できます。 <br>**行われないことは何ですか?** PowerShell、Microsoft Graph API、または Visual Studio などの他のツールを使用して、Microsoft Entra データへのプログラムによるアクセスを **ブロックすることはありません** 。 カスタム ロールを含む管理ロールを持つユーザー **には適用されません** 。管理センターへのすべてのアクセスを **妨げるわけではありません** 。 多くの領域は、引き続き代替パスを介して到達可能です。 <br><br>**このスイッチを使用する必要がある場合**この設定は、管理者以外のユーザーが Microsoft Entra 管理センターを何気なく開くのを妨げる摩擦のレイヤーを追加する場合に使用します。 これは、不要な探索を減らすのに役立ちますが、ユーザーが他の方法でリソースにアクセスしたり管理したりすることを妨げません。 <br><br>**このスイッチを使用すべきでないのはいつですか?**セキュリティ コントロールとしてこの設定に依存しないでください。 より強力に適用するには、Windows Azure サービス管理 API [Windows Azure サービス管理](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#windows-azure-service-management-api) API を対象とする条件付きアクセス ポリシーを使用して、Azure 管理エンドポイントへの管理者以外のアクセスをブロックします。<br><br>**特定の管理者以外のユーザーにのみ Microsoft Entra 管理ポータルを使用する権限を付与するにはどうすればよいですか?** スイッチを **[はい**] に設定し、それらのユーザーに **、グローバル閲覧者** などのロールや、適切なアクセス許可を付与する別のロールを割り当てます。 <br><br>**アクセスをより効果的に制限したいですか?**条件付きアクセスを使用して、Windows Azure サービス管理 API をターゲットにします。 これにより、Microsoft Entra 管理センターを含むすべての Azure ベースの管理エクスペリエンスへのアクセスをより広範に制御できます。 |
| **管理者以外のユーザーによるテナントの作成を制限する** | ユーザーは、Microsoft Entra ID および Microsoft Entra 管理ポータルの [テナントの管理] でテナントを作成できます。 テナントの作成は、監査ログに DirectoryManagement カテゴリおよび Create Company アクティビティとして記録されます。 既定では、Microsoft Entra テナントを作成するユーザーには、 [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) ロールが自動的に割り当てられます。 新しく作成されたテナントは設定や構成を継承しません。 <br>**このスイッチは何をしますか?** このオプションを **[はい** ] に設定すると、Microsoft Entra テナントの作成は、少なくとも [テナント作成者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#tenant-creator) ロールが割り当てられているすべてのユーザーに制限されます。 このオプションを **[いいえ** ] に設定すると、管理者以外のユーザーは Microsoft Entra テナントを作成できます。 テナントの作成は引き続き監査ログに記録されます。 <br><br>**管理者以外の特定のユーザーにのみ新しいテナントを作成する権限を付与するにはどうすればよいですか?** このオプションを [はい] に設定し、 [テナント作成者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#tenant-creator) ロールを割り当てます。 |
| **所有デバイスの BitLocker キーの回復をユーザーに制限する** | この設定は、Microsoft Entra 管理センターの [デバイスの設定] にあります。 このオプションを **[はい** ] に設定すると、ユーザーは所有デバイスの BitLocker キーをセルフサービスで回復できなくなります。 ユーザーが BitLocker キーを取得するには、組織のヘルプ デスクに問い合わせる必要があります。 このオプションを  に設定すると、ユーザーは BitLocker キーを回復できます。 |
| **他のユーザーを読む** | この設定は Microsoft Graph と PowerShell でのみ使用できます。 このフラグを `$false` に設定すると、管理者以外のすべてのユーザーはディレクトリからユーザー情報を読み取ることができなくなります。 このフラグを使用すると、Microsoft Teams などの他の Microsoft サービスのユーザー情報を読み取ることができなくなることがあります。<br>この設定は特別な状況を対象としているため、フラグを `$false` に設定することはお勧めしません。 この設定を構成するには、[AllowedToReadOtherUsers](https://learn.microsoft.com/ja-jp/graph/api/authorizationpolicy-update?tabs=http#request-3) プロパティを設定して、Microsoft Graph を使用して [authorizationPolicy](https://learn.microsoft.com/ja-jp/graph/api/resources/defaultuserrolepermissions#json-representation) を更新します。 |

次のスクリーンショットに、[ **管理者以外のユーザーによるテナントの作成を制限** する] オプションが表示されています。

[Image: 非管理者によるテナントの作成を制限するオプションを示すスクリーンショット。]

### ゲスト ユーザーの既定のアクセス許可を制限する

ゲスト ユーザーの既定のアクセス許可は、次の方法で制限できます。

Note

**ゲスト ユーザーのアクセス制限**設定は**ゲスト ユーザーの権限が制限される**設定に置き換えられました。 この機能の使用に関するガイダンスについては、「 [Microsoft Entra ID でゲスト アクセス許可を制限](https://learn.microsoft.com/ja-jp/entra/identity/users/users-restrict-guest-permissions)する」を参照してください。

| Permission | 設定の説明 |
| --- | --- |
| **ゲスト ユーザーのアクセス制限** | このオプションを **ゲスト ユーザーに設定すると、メンバーと同じアクセス権を持つ** すべてのメンバー ユーザーのアクセス許可がゲスト ユーザーに既定で付与されます。<br>このオプションを **ゲスト ユーザー アクセスに設定すると、独自のディレクトリ オブジェクトのプロパティとメンバーシップに制限** され、既定ではゲスト アクセスは独自のユーザー プロファイルのみに制限されます。 ユーザー プリンシパル名、オブジェクト ID、または表示名で検索する場合でも、他のユーザーへのアクセスは許可されなくなりました。 グループ メンバーシップを含むグループ情報へのアクセスも許可されなくなりました。<br><br>この設定では、Microsoft Teams など、一部の Microsoft 365 サービスの参加しているグループへのアクセスは禁止されません。 詳細については、[Microsoft Teams ゲスト アクセス](https://learn.microsoft.com/ja-jp/MicrosoftTeams/guest-access)を参照してください。<br><br>このアクセス許可の設定に関係なく、ゲスト ユーザーを管理者の役割に追加できます。 |
| **ゲストは招待できます** | このオプションを **[はい** ] に設定すると、ゲストは他のゲストを招待できます。 詳細については、「 [外部コラボレーション設定の構成」を](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure)参照してください。 |

### オブジェクトの所有権

#### アプリケーション登録所有者のアクセス許可

ユーザーがアプリケーションを登録すると、そのユーザーはアプリケーションの所有者として自動的に追加されます。 所有者は、名前やアプリが要求するアクセス許可など、アプリケーションのメタデータを管理できます。 また、シングル サインオン (SSO) の構成やユーザーの割り当てなど、アプリケーションのテナント固有の構成も管理できます。

所有者は、他の所有者を追加または削除することもできます。 アプリケーション管理者ロール以上が割り当てられているユーザーとは異なり、所有者は、自分が所有するアプリケーションのみを管理できます。

#### エンタープライズ アプリケーション所有者のアクセス許可

ユーザーがエンタープライズ アプリケーションを追加すると、そのユーザーは自動的に所有者として追加されます。 所有者は、SSO の構成、プロビジョニング、ユーザーの割り当てなど、アプリケーションのテナント固有の構成を管理できます。

所有者は、他の所有者を追加または削除することもできます。 アプリケーション管理者ロール以上が割り当てられているユーザーとは異なり、所有者は、自分が所有するアプリケーションのみを管理できます。

#### グループ所有者のアクセス許可

グループを作成したユーザーは、そのグループの所有者として自動的に追加されます。 所有者は、名前などのグループのプロパティおよびグループ メンバーシップを管理できます。

所有者は、他の所有者を追加または削除することもできます。 少なくとも [グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) ロールが割り当てられているユーザーとは異なり、所有者は自分が所有するグループのみを管理でき、グループのメンバーシップの種類が **割り当てられている**場合にのみグループ メンバーを追加または削除できます。

グループ所有者を割り当てるには、「グループ [の所有者の管理」を](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups)参照してください。

特権アクセス管理 (PIM) を使用してグループをロールの割り当ての対象にするには、「 [Microsoft Entra グループを使用してロールの割り当てを管理する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept)」を参照してください。

#### 所有権限

次の表では、メンバー ユーザーが所有するオブジェクトに対して持つ Microsoft Entra ID の特定のアクセス許可について説明します。 ユーザーは、自分が所有するオブジェクトに対してのみこれらのアクセス許可を持っています。

##### 所有しているアプリケーションの登録

ユーザーは、所有するアプリケーションの登録で次のアクションを実行できます。

| **Action** | **Description** |
| --- | --- |
| microsoft.directory/applications/audience/update | Microsoft Entra ID の `applications.audience` プロパティを更新します。 |
| microsoft.directory/applications/authentication/update | Microsoft Entra ID の `applications.authentication` プロパティを更新します。 |
| microsoft.directory/applications/basic/update | Microsoft Entra ID でアプリケーションの基本プロパティを更新します。 |
| microsoft.directory/applications/credentials/update | Microsoft Entra ID の `applications.credentials` プロパティを更新します。 |
| microsoft.directory/applications/delete | Microsoft Entra ID でアプリケーションを削除します。 |
| microsoft.directory/applications/owners/update | Microsoft Entra ID の `applications.owners` プロパティを更新します。 |
| microsoft.directory/applications/permissions/update | Microsoft Entra ID の `applications.permissions` プロパティを更新します。 |
| microsoft.directory/applications/policies/update | Microsoft Entra ID の `applications.policies` プロパティを更新します。 |
| microsoft.directory/applications/restore | Microsoft Entra ID でアプリケーションを復元します。 |

##### 所有するエンタープライズ アプリケーション

ユーザーは、所有するエンタープライズ アプリケーションで次のアクションを実行できます。 エンタープライズ アプリケーションは、サービス プリンシパル、1 つまたは複数のアプリケーション ポリシー、および場合によってはサービス プリンシパルと同じテナント内のアプリケーション オブジェクトで構成されます。

| **Action** | **Description** |
| --- | --- |
| microsoft.directory/auditLogs/allProperties/read | Microsoft Entra ID で監査ログのすべてのプロパティ (特権プロパティを含む) を読み取ります。 |
| microsoft.directory/policies/basic/update | Microsoft Entra ID でポリシーの基本プロパティを更新します。 |
| microsoft.directory/policies/delete | Microsoft Entra ID でポリシーを削除します。 |
| microsoft.directory/policies/owners/update | Microsoft Entra ID の `policies.owners` プロパティを更新します。 |
| microsoft.directory/servicePrincipals/appRoleAssignedTo/update | Microsoft Entra ID の `servicePrincipals.appRoleAssignedTo` プロパティを更新します。 |
| microsoft.directory/servicePrincipals/appRoleAssignments/update | Microsoft Entra ID の `users.appRoleAssignments` プロパティを更新します。 |
| microsoft.directory/servicePrincipals/audience/update | Microsoft Entra ID の `servicePrincipals.audience` プロパティを更新します。 |
| microsoft.directory/servicePrincipals/authentication/update | Microsoft Entra ID の `servicePrincipals.authentication` プロパティを更新します。 |
| microsoft.directory/servicePrincipals/basic/update | Microsoft Entra ID でサービス プリンシパルの基本プロパティを更新します。 |
| microsoft.directory/servicePrincipals/credentials/update | Microsoft Entra ID の `servicePrincipals.credentials` プロパティを更新します。 |
| microsoft.directory/servicePrincipals/delete | Microsoft Entra ID のサービス プリンシパルを削除します。 |
| microsoft.directory/servicePrincipals/owners/update | Microsoft Entra ID の `servicePrincipals.owners` プロパティを更新します。 |
| microsoft.directory/servicePrincipals/permissions/update | Microsoft Entra ID の `servicePrincipals.permissions` プロパティを更新します。 |
| microsoft.directory/servicePrincipals/policies/update | Microsoft Entra ID の `servicePrincipals.policies` プロパティを更新します。 |
| microsoft.directory/signInReports/allProperties/read | Microsoft Entra ID のサインイン レポートのすべてのプロパティ (特権プロパティを含む) を読み取ります。 |
| microsoft.directory/servicePrincipals/synchronizationCredentials/manage | アプリケーション プロビジョニングのシークレットと資格情報を管理する |
| microsoft.directory/servicePrincipals/synchronizationJobs/manage | アプリケーション プロビジョニングの同期ジョブを開始、再開、および一時停止する |
| microsoft.directory/servicePrincipals/synchronizationSchema/manage | アプリケーション プロビジョニングの同期ジョブとスキーマを作成、管理する |
| microsoft.directory/servicePrincipals/synchronization/standard/read | サービス プリンシパルに関連付けられているプロビジョニング設定を読み取る |

##### 所有されているデバイス

ユーザーは、所有するデバイスで次のアクションを実行できます。

| **Action** | **Description** |
| --- | --- |
| microsoft.directory/devices/bitLockerRecoveryKeys/read | Microsoft Entra ID の `devices.bitLockerRecoveryKeys` プロパティを読み取ります。 |
| microsoft.directory/devices/disable | Microsoft Entra ID でデバイスを無効化します。 |

##### 所有しているグループ

ユーザーは、所有するグループで次のアクションを実行できます。

Note

動的メンバーシップ グループの所有者が動的メンバーシップ グループの規則を編集するには、グループ管理者、Intune 管理者、またはユーザー管理者のロールを持っている必要があります。 詳細については、「 [Microsoft Entra ID で動的メンバーシップ グループを作成または更新](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule)する」を参照してください。

| **Action** | **Description** |
| --- | --- |
| microsoft.directory/groups/appRoleAssignments/update | Microsoft Entra ID の `groups.appRoleAssignments` プロパティを更新します。 |
| microsoft.directory/groups/basic/update | Microsoft Entra ID のグループの基本プロパティを更新します。 |
| microsoft.directory/groups/delete | Microsoft Entra ID のグループを削除します。 |
| microsoft.directory/groups/members/update | Microsoft Entra ID の `groups.members` プロパティを更新します。 |
| microsoft.directory/groups/owners/update | Microsoft Entra ID の `groups.owners` プロパティを更新します。 |
| microsoft.directory/groups/restore | Microsoft Entra ID のグループを復元します。 |
| microsoft.directory/groups/settings/update | Microsoft Entra ID の `groups.settings` プロパティを更新します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/users-reset-password-azure-portal"} -->
## ユーザーのパスワードのリセット - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/users-reset-password-azure-portal
- Service: entra / fundamentals
- Article date: 2025-08-27
- Summary: Microsoft Entra ID を使用してユーザーのパスワードをリセットする方法について説明します。

### 概要

管理者は、ユーザーがパスワードを忘れた場合、ユーザーがロックアウトされた場合、またはユーザーがパスワードを受け取らなかった場合に、ユーザーのパスワードをリセットできます。

注

管理者ではなく、職場または学校のパスワードをリセットする方法の手順が必要な場合は、「 [職場または学校のパスワードをリセット](https://support.microsoft.com/account-billing/reset-your-work-or-school-password-using-security-info-23dde81f-08bb-4776-ba72-e6b72b9dda9e)する」を参照してください。

テナントがユーザーのホーム ディレクトリでない限り、パスワードをリセットすることはできません。 つまり、ユーザーが別の組織のアカウント、Microsoft アカウント、または Google アカウントを使用して組織にサインインしている場合は、パスワードをリセットすることもできません。

ユーザーが Windows Server Active Directory としての権限のソースを持っている場合は、パスワード ライトバックを有効にし、ユーザー ドメインが管理されている場合にのみ、パスワードをリセットできます。 フェデレーション ドメインのユーザー パスワードの変更はサポートされていません。 この場合は、オンプレミスの Active Directory のユーザー パスワードを変更します。

ユーザーが外部 Microsoft Entra ID として権限のソースを持っている場合、パスワードをリセットすることはできません。 パスワードをリセットできるのは、そのテナントのユーザーまたは管理者だけです。

### [前提条件]

ユーザーのパスワードをリセットするには、少なくとも [パスワード管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#password-administrator) ロールが必要です。 その他のロールでは、限られたユーザー セットのパスワードをリセットできます。 詳細については、「 [パスワードをリセットできるユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-reset-passwords)」を参照してください。

### パスワードをリセットするには

次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[パスワード管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#password-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. リセットが必要なユーザーを選択し、[ **パスワードのリセット**] を選択します。

    [パスワードの**リセット**] オプションが表示された **[Alain Charon - Profile**] ページが表示されます。

    [Image: [パスワードのリセット] オプションが強調表示されている [ユーザーのプロファイル] ページのスクリーンショット。]
4. [ **パスワードのリセット** ] ページで、[ **パスワードのリセット**] を選択します。

    注

    Microsoft Entra ID を使用している場合、Microsoft Entra ID はユーザーの一時パスワードを自動生成します。 オンプレミスで Active Directory を使用する場合は、ユーザーのパスワードを作成します。
5. パスワードをコピーし、ユーザーに渡します。 ユーザーは、次のサインイン プロセス中にパスワードを変更する必要があります。

注

一時パスワードの有効期限は切れません。 次回ユーザーがサインインしても、一時パスワードが生成されてからの経過時間に関係なく、パスワードは引き続き機能します。

Important

管理者がユーザーのパスワードをリセットできない場合、Microsoft Entra Connect サーバーのアプリケーション イベント ログにエラー コード hr=80231367 がある場合は、Active Directory でユーザーの属性を確認します。 **AdminCount** 属性が 1 に設定されている場合、管理者はユーザーのパスワードをリセットできなくなります。 管理者がユーザーのパスワードをリセットするには、 **AdminCount** 属性を 0 に設定する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/users-restore"} -->
## 最近削除されたユーザーを復元または完全に削除する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/users-restore
- Service: entra / fundamentals
- Article date: 2026-06-18
- Summary: 復元可能なユーザーを表示する方法、削除したユーザーを復元する方法、または Microsoft Entra ID を持つユーザーを完全に削除する方法。

### 概要

ユーザーを削除した後、アカウントは 30 日間中断状態のままになります。 その 30 日間の期間中、ユーザー アカウントとそのすべてのプロパティを復元できます。

その 30 日間の期間が経過すると、完全な削除プロセスが自動的に開始され、停止できません。 この期間中、ソフト削除されたユーザーの管理はブロックされます。 この制限は、オンプレミスのハイブリッド シナリオのテナント同期サイクル中に、一致を介して論理的に削除されたユーザーを復元する場合にも適用されます。

Microsoft Entra 管理センターを使用して、復元可能なユーザーの表示、削除されたユーザーの復元、またはユーザーの完全な削除を行うことができます。

Important

セキュリティ上の理由から、Microsoft Entra ID でオンプレミス環境から同期されたユーザーを削除できます。 ただし、Microsoft Entra ID は、同期されたユーザーの権限のソースではありません。 ユーザーがまだオンプレミスディレクトリに存在する場合、同期エンジンは次の同期サイクル中にユーザーを復元する可能性があります。 ユーザーが完全に削除されると、ユーザーも Microsoft サポートも復元できません。

### [前提条件]

ユーザーを復元して完全に削除するには、少なくとも次のロールが必要です。

### 復元可能なユーザーを表示する

30 日以内に削除されたすべてのユーザーを表示できます。 これらのユーザーは復元できます。

#### 復元可能なユーザーを表示するには

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動し、[**削除されたユーザー**] を選択します。

    **[削除されたユーザー**] が表示されない場合は、Microsoft Entra 管理センター検索ボックスを使用して[**削除されたユーザー**]を検索して選択します。
3. 復元できるユーザーの一覧を確認します。

    [Image: [ユーザー - 削除済みユーザー] ページのスクリーンショット。復元可能なユーザーが表示されています。]

### 最近削除されたユーザーを復元する

ユーザー アカウントが組織から削除されると、アカウントは中断状態になります。 アカウントの組織情報はすべて保持されます。 ユーザーを復元すると、この組織情報も復元されます。

注

ユーザーが復元されると、削除時にユーザーに割り当てられたライセンスも、使用可能なライセンスがない場合でも復元されます。 購入したライセンスよりも多くのライセンスを消費している場合、組織はライセンスの使用に関して一時的にコンプライアンス違反になる可能性があります。

#### ユーザーを復元するには

1. [ **削除されたユーザー** ] ページで、使用可能なユーザーの 1 つを検索して選択します。 たとえば、 *Mary Parker* などです。
2. [ **ユーザーの復元**] を選択します。

    [Image: [ユーザー - 削除済みユーザー] ページのスクリーンショット。[ユーザーの復元] オプションが強調表示されています。]

### ユーザーを完全に削除する

30 日間の自動削除を待たずに、組織からユーザーを完全に削除できます。 完全に削除されたユーザーは、Microsoft カスタマー サポートを含む誰も復元できません。

注

誤ってユーザーを完全に削除した場合は、新しいユーザーを作成し、以前のすべての情報を手動で入力する必要があります。 新しいユーザーの作成の詳細については、「ユーザーの [追加または削除](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users)」を参照してください。

#### ユーザーを完全に削除するには

1. [ **削除されたユーザー** ] ページで、使用可能なユーザーの 1 つを検索して選択します。 たとえば、 *Rae Huff* などです。
2. **完全に削除** を選択します。

    [Image: [ユーザー - 削除済みユーザー] ページのスクリーンショット。[ユーザーの削除] オプションが強調表示されています。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/what-is-entra"} -->
## Microsoft Entraとは - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/what-is-entra
- Service: entra / fundamentals
- Article date: 2026-06-18
- Summary: 作業を開始するためのリンクを含む、Microsoft Entra製品ファミリの概要。

Microsoft Entraは、組織が[ゼロ トラスト](https://learn.microsoft.com/ja-jp/security/zero-trust/zero-trust-overview)セキュリティ戦略を実装するのに役立つ ID およびネットワーク アクセス製品のファミリです。 Microsoft Entraを使用して、ID の検証、アクセス条件の検証、アクセス許可の確認、接続チャネルの暗号化、環境全体の侵害の監視を行います。 Microsoft Entra[Security Copilot](https://learn.microsoft.com/ja-jp/entra/security-copilot/security-copilot-in-entra) と統合して、ID のリスクを調査し、AI を使用してアクセスの問題をトラブルシューティングするのに役立ちます。

### Microsoft Entra製品ファミリ

Microsoft Entra製品ファミリは、ID、アクセス、ガバナンス、セキュリティにまたがっています。 クラウド環境全体で従業員、顧客、パートナー、ワークロード、AI エージェントに対するセキュリティで保護されたエンド ツー エンド アクセスが対象となります。

#### ゼロ トラストアクセス制御を確立する

##### Microsoft Entra ID

[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/fundamentals/what-is-entra) は、Microsoft Entraの基本製品です。 これは、ユーザー、デバイス、アプリ、リソースに対する認証、ポリシーの適用、保護を提供するクラウドベースの ID およびアクセス管理サービスです。 すべての新しいMicrosoft Entra ディレクトリには、`contoso.onmicrosoft.com` などの初期ドメイン名が含まれます。 組織のカスタム ドメイン名を追加することもできます。

Microsoft 365、Azure、または Dynamics CRM Online のサブスクライバーであれば、既に Microsoft Entra ID を使用しています。すべてのテナントは自動的に Microsoft Entra テナントです。 統合クラウド アプリへのアクセスの管理をすぐに開始できます。

##### Microsoft Entra Domain Services

[Microsoft Entra Domain Services](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/overview) は、グループ ポリシー、LDAP、Kerberos/NTLM 認証などのマネージド ドメイン サービスを提供します。 これは、最新の認証方法を使用できないクラウド内のレガシ アプリケーション向けに設計されています。

>
> **Scenario:** Kerberos 認証を必要とするサービスを持つ組織は、Microsoftがコア サービス コンポーネントを展開して管理するマネージド ドメインを作成できます。

#### 従業員のアクセスをセキュリティで保護する

##### マイクロソフト エントラ プライベート アクセス (Microsoft Entra Private Access)

[Microsoft Entra Private Access](https://learn.microsoft.com/ja-jp/entra/global-secure-access/overview-what-is-global-secure-access#microsoft-entra-private-access) は、企業ネットワークやマルチクラウド環境を含むすべてのプライベート アプリとリソースへのアクセスをセキュリティで保護します。 リモート ユーザーは、VPN なしで、任意のデバイスとネットワークから内部リソースに接続できます。

**たとえば**、従業員は自宅やカフェで働きながら、企業のネットワーク プリンターに安全にアクセスできます。

##### マイクロソフト エントラ インターネット アクセス

[Microsoft Entra Internet Access](https://learn.microsoft.com/ja-jp/entra/global-secure-access/overview-what-is-global-secure-access#microsoft-entra-internet-access) は、SaaS アプリやMicrosoft 365アプリやリソースなど、すべてのインターネット リソースへのアクセスをセキュリティで保護します。

>
> **シナリオ：** Web コンテンツ フィルタリングを有効にして、コンテンツ カテゴリとドメイン名に基づいて Web サイトへのアクセスを制御します。

##### Microsoft Entra ID ガバナンス

[Microsoft Entra ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview) は、アクセス要求、割り当て、レビューを自動化することで、ID とアクセス許可の管理を簡略化します。 ID ライフサイクル管理を通じて重要な資産を保護することもできます。

**たとえば**、管理者は、新しい従業員にユーザー アカウント、グループ、ライセンスを自動的に割り当て、従業員が退職したときにそれらの割り当てを削除できます。

##### Microsoft Entra ID 保護（マイクロソフト エントラ ID 保護）

[Microsoft Entra ID 保護](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)は、ID ベースのリスクを検出して報告します。 管理者は、 [リスクベースの条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-policies)などのツールを使用して、リスクを調査し、自動的に修復できます。

>
> **シナリオ：** サインイン リスク レベルが中または高の場合に多要素認証を必要とするリスクベースの条件付きアクセス ポリシーを作成します。

##### Microsoft Entra 確認済み ID

[Microsoft Entra Verified ID](https://learn.microsoft.com/ja-jp/entra/verified-id/decentralized-identifier-overview) は、open [decentralized Identity (DID) 標準](https://learn.microsoft.com/ja-jp/entra/verified-id/verifiable-credentials-standards)に基づく資格情報検証サービスです。 組織は、個人のデバイスに資格情報を保存し、必要に応じて提示するユーザーに、検証可能な資格情報 (情報の有効性を証明するデジタル署名) を発行できます。

**たとえば**、最近の大学の卒業生は、DID にデジタル卒業証明書を発行するように大学に依頼し、発行者、発行時間、ステータスを個別に確認できる潜在的な雇用主に提示することができます。

#### 顧客とパートナーのアクセスをセキュリティで保護する

##### Microsoft Entra 外部 ID

[Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-overview) では、外部 ID がビジネス リソースやコンシューマー アプリに安全にアクセスできます。 内部アプリでビジネス パートナーやゲストと共同作業を行う場合や、コンシューマー向けアプリケーションで顧客 ID とアクセス管理 (CIAM) を管理するための安全な方法が提供されます。

>
> **シナリオ：** ユーザーが Google または Facebook のワンタイム パスコードまたはソーシャル アカウントを使用して Web アプリケーションにサインインするためのセルフサービス登録を設定します。

#### 任意のクラウドでアクセスをセキュリティで保護する

##### Microsoft Entra ワークロード ID

[Microsoft Entra ワークロード ID](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identities-overview) は、認証ポリシーと承認ポリシーを必要とするアプリケーション、サービス、コンテナーなど、ワークロード ID の ID およびアクセス管理ソリューションです。 これにより、組織はアダプティブ ポリシーとカスタム セキュリティ属性を使用してリソースへのアクセスをセキュリティで保護できます。

**たとえば**、GitHub Actionsでは、ソフトウェア開発ワークフローを自動化、カスタマイズ、実行するために、Azure サブスクリプションにアクセスするためのワークロード ID が必要です。

#### AI エージェントへのセキュリティ保護されたアクセス

##### Microsoft Entra エージェント ID

[Microsoft Entra エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-microsoft-entra-agent-id) は、Microsoft Entra機能を AI エージェントに拡張する ID およびセキュリティ フレームワークです。 組織が支援型、自律型、およびユーザーに似たエージェントを展開する際に、Entra エージェント IDは、エンタープライズ規模でこれらの非人間的な ID を認証、承認、管理、保護するための専用の ID コンストラクトを提供します。

>
> **シナリオ：** 組織は、ユーザーに代わって企業データにアクセスする AI エージェントをデプロイします。 Entra エージェント IDは、各エージェントに管理 ID を提供し、最小特権アクセスを適用し、エージェントのアクションの監査証跡を維持します。

### 環境を準備する

Microsoft Entraをデプロイする前に、セキュリティのベスト プラクティスと標準に従ってインフラストラクチャとプロセスを構成します。 次の記事では、アーキテクチャ、デプロイ、運用に関するガイダンスを提供します。

- [アーキテクチャ](https://learn.microsoft.com/ja-jp/entra/architecture/architecture)
- [デプロイ計画](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans)
- [操作リファレンス](https://learn.microsoft.com/ja-jp/entra/architecture/ops-guide-intro)
- [操作ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-introduction)
- [推奨されるセキュリティ構成](https://learn.microsoft.com/ja-jp/entra/fundamentals/configure-security)

#### Microsoft Entra 機能のライセンス

Microsoft Entraの機能には、複数の方法でライセンスが付与されます。 これらのライセンスには、Microsoft Entra ID Free、Microsoft Entra ID P1、Microsoft Entra ID P2、Microsoft Entra スイート、Microsoft Entra 外部 ID、Microsoft Entra ワークロード ID 、Microsoft Entra ID ガバナンス、およびその他のスタンドアロン製品。 Microsoft Entraは、[Microsoft 365](https://www.microsoft.com/microsoft-365/enterprise/microsoft365-plans-and-pricing) や [Enterprise Mobility + Security](https://www.microsoft.com/microsoft-365/enterprise-mobility-security/compare-plans-and-pricing) などのライセンスの一部でもあります。 ライセンスと使用可能なオプションの詳細については、[Microsoft Entra ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing)または [Microsoft Entra 価格に関するページ](https://www.microsoft.com/security/business/microsoft-entra-pricing)を参照してください。

### Microsoft Entraを使用した管理と開発

管理者は、Microsoft Entra 管理センター と Microsoft Graph API を使用して、ID およびネットワーク アクセス リソースを管理できます。 開発者は、Microsoft ID プラットフォーム を使用して ID 対応アプリケーションを構築できます。

#### Microsoft Entra 管理センター

[Microsoft Entra 管理センター](https://entra.microsoft.com/) は、1 つのインターフェイスからMicrosoft Entra製品を構成および管理するための Web ベースのポータルです。

詳細については、 Microsoft Entra 管理センターを参照してください。

#### Microsoft Graph API

[Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/overview) は、ライセンスの展開やユーザー ライフサイクル管理などの管理タスクを自動化します。

詳細については、[Microsoft Graph を使用した Microsoft Entra の管理](https://learn.microsoft.com/ja-jp/graph/api/resources/identity-network-access-overview)をご参照ください。

#### Microsoft ID プラットフォーム（Microsoft アイデンティティプラットフォーム）

[Microsoft ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-overview) を使用すると、開発者はオープンソース ライブラリと標準準拠の認証サービスを使用して、Web、デスクトップ、モバイル アプリケーションの認証エクスペリエンスを構築できます。

開発を開始するには、「[概要](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-overview#getting-started)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/whats-new"} -->
## Microsoft Entraリリースとアナウンス - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/whats-new
- Service: entra / fundamentals
- Article date: 2026-06-26
- Summary: 最新のリリース ノート、既知の問題、バグ修正、非推奨の機能、今後の変更など、Microsoft Entraの新機能について説明します。

この記事では、過去 6 か月間 (毎月更新) の製品ファミリMicrosoft Entra全体の最新リリースと変更のお知らせについて説明します。 6 か月以上前の情報をお探しの場合は、「 Microsoft Entraを参照してください。

>
> この URL `https://learn.microsoft.com/api/search/rss?search=%22Release+notes+-+Azure+Active+Directory%22&locale=en-us` を [Image: RSS フィード リーダー アイコン] フィード リーダーにコピーして貼り付けることで、更新のためにこのページを再訪するタイミングについて通知を受け取ります。

### 2026 年 6 月

#### 一般提供 - 外部ユーザーを Access パッケージに直接割り当てることができます

**種類:** 新機能**サービス カテゴリ:** エンタイトルメント管理**製品の機能:** ID ガバナンス

この機能を使用すると、エンタイトルメント管理管理者は、ディレクトリに含まれていない外部ユーザーを、ユーザーの電子メールを使用してアクセス パッケージに直接割り当てることができます。 ユーザーはゲスト ユーザーとしてテナントに招待され、(Entra IDガバナンスが構成されている限り) 管理されます。

#### 一般提供 - Microsoft Entra Kerberos キーローテーションにより、受信信頼紹介フローの信頼性が向上しました

**種類：** 機能の変更**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

Microsoft Entra Kerberos キーローテーションの一般提供により、特に受信信頼紹介フローを使用する環境の信頼性が向上しました。 以前は、紹介チケットがセカンダリ キーで暗号化されている場合、Kerberos キーのローテーション中に認証エラーが発生する可能性がありました。 この更新プログラムは、プライマリキーとセカンダリ Kerberos キーの両方で復号化を試みる検証ロジックを強化し、キーのロールオーバー操作中の回復性を向上させ、ローテーション イベント中の認証の中断を減らします。 詳細については、[Kerberos の Kerberos サーバー キーのローテーションMicrosoft Entra](https://learn.microsoft.com/ja-jp/entra/identity/authentication/kerberos-server-key-rotation)参照してください。

#### 一般提供 - Entra 登録を使用した Windows クライアントの BYOD サポート

**種類:** 新機能**サービス カテゴリ:** Byod**製品の機能:** ネットワークアクセス

Entra 登録済みデバイスを使用した Windows クライアントに対する Bring Your Own Device (BYOD) のサポートが一般公開されました。 **ユーザーとパートナー**が自分のデバイスから企業リソースにアクセスできるようになりました。 管理者は、内部**ゲスト** ユーザーを含む、内部アカウントを持つユーザーに**プライベート アプリケーション** トラフィック プロファイルを割り当てることができます。 これにより、Windows デバイスをドメインに参加させる前の要件が削除されます。 詳細については、「 [Bring Your Own Device」を参照してください](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-bring-your-own-device)。

#### 一般提供 - Authenticator アプリでの脱獄/ルート検出

**種類:** 新機能**サービス カテゴリ:** Microsoft Authenticator アプリ**製品の機能:** ID のセキュリティと保護

Microsoft Authenticator Authenticator アプリの職場または学校アカウントMicrosoft脱獄/ルート検出が導入されました。 ルート化/脱獄されたデバイスを持つユーザーは、Authenticator アプリでの職場または学校アカウントの追加/使用がブロックされます。 Authenticator で職場または学校アカウントを引き続き使用するには、準拠しているデバイスに移動する必要があります。 この機能は既定でセキュリティで保護されており、管理者の構成や制御は必要ありません。

#### パブリック プレビュー - エージェントのユーザー アカウントの拡張条件付きアクセス保護

**種類:** 新機能**Service category:** 条件付きアクセス**製品の機能:** ID のセキュリティと保護

条件付きアクセスでは、ユーザー アカウントを持つ AI エージェントをセキュリティで保護するためのより広範な制御が提供されるようになりました。 管理者は次のことをできます。

- 個々のエージェントを含めたり除外したり、**カスタム セキュリティ属性**を使用してエージェントを動的にグループ化したりすることで、より**高い精度でエージェントのユーザー アカウントをターゲット**にします。
- **エージェント リスクに** 基づいて条件付きアクセス ポリシーを適用することで、危険な **エージェント** アクティビティから保護します。
- エージェントの**Windows 365**を含め、マネージド エンドポイントで実行されているエージェントに**準拠しているデバイスを要求**し、エージェントが組織のコンプライアンス要件を満たすデバイスからのみ動作できるようにする。
- **デバイス プラットフォームを適用し、デバイスをフィルター処理し、** エンドポイントで実行されているエージェントに準拠したネットワーク条件を適用し、デバイスの状態と信頼できるネットワークの場所に基づいてポリシーを有効にします。

これらの機能は、使い慣れた条件付きアクセス ポリシー エクスペリエンスを活用しながら、エージェントのユーザー アカウントにゼロ トラスト保護を拡張します。 詳細については、「 [条件付きアクセス ポリシーのターゲット エージェント ID」を](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-target-agent-identities)参照してください。

#### 一般提供 - 従業員テナントのドメインレス SAML IdP フェデレーション

**種類:** 新機能**サービス カテゴリ:** B2B**製品の機能:** B2B/B2C

SAML ID プロバイダーとのドメインレス SAML フェデレーションを使用すると、外部ユーザーは、メール ドメインに関係なく、IdP で管理される資格情報を使用してアプリまたは従業員リソースに対して認証を行うことができます。 ドメインレス フェデレーションを使用すると、サインインまたは招待の利用中に、ユーザーの電子メールドメインと構成済みの IdP ドメインの間でドメインマッチングを行う必要がなくなります。 詳細については、「[SAML/WS-Fed ID プロバイダーの追加 - Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation#domainless-saml-idp-federation-preview)」を参照してください。

#### 一般提供 - US Gov クラウドのMicrosoft Entra ID用 SCIM 2.0 API

**種類:** 新機能**サービス カテゴリ:** プロビジョニング**製品の機能:** ID ライフサイクル管理

SCIM 2.0 API が US Gov クラウドで一般公開され、顧客、開発者、パートナーに、クロスドメイン ID 管理システム (SCIM) 2.0 仕様を使用してMicrosoft Entraでユーザーとグループを管理するための標準ベースのオプションが提供されるようになりました。 詳細については、「[Microsoft Entra ID で SCIM プロビジョニング API を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/enable-scim-api)」を参照してください。

#### 一般提供 - Microsoft Entraバックアップと復旧が利用可能になりました

**種類:** 新機能**サービス カテゴリ:** Entra のバックアップと回復**製品の機能:** Entra のバックアップと回復

Microsoft Entraバックアップと回復は、偶発的な変更や悪意のある更新後にテナントを復元するのに役立つ組み込みのソリューションです。 既定では、重要なディレクトリ オブジェクト (ユーザー、グループ、アプリケーション、サービス プリンシパル、マネージド ID、条件付きアクセス ポリシー、名前付き場所、エージェント ID、認証および承認ポリシーなど) が自動的にバックアップされるため、管理者はそれらを以前に既知の正常な状態にすばやく復元できます。

プレビューでは、Entra Backup and Recovery は、テナントでサポートされているディレクトリ オブジェクトの毎日のバックアップを自動的に取得します。 テナントに P1 または P2 ライセンスMicrosoft Entra IDがある場合は、毎日 1 回のバックアップが作成され、7 日間保持されます。 管理者は、使用可能なスナップショットを表示したり、相違レポートを生成して変更内容を把握したり、復旧ジョブを実行してオブジェクトを以前の状態に復元したりできます。

これにより、信頼性の高い組み込みのセーフティ ネットが組織に提供され、自信を持って復旧し、ダウンタイムを最小限に抑え、偶発的な変更、構成ミス、セキュリティ侵害からテナントを保護できます。 詳細については、「[Microsoft Entra Backup and Recovery の概要 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/backup/overview)を参照してください。

#### 今後の変更 - iOS でのデバイス バインド認証アプリのパスキーの復元エクスペリエンスの向上

**種類:** 変更の計画**サービス カテゴリ:** Microsoft Authenticator アプリ**製品の機能:** ID のセキュリティと保護

何もする必要はありません。

何が変わるのですか? 2026 年 8 月以降、ユーザーには、iOS ユーザー向けのデバイス バインド Authenticator アプリのパスキーの復元エクスペリエンスが向上します。 管理者またはユーザーの操作は必要ありません。

この更新プログラムでは、次の操作を行います。

- Authenticator アプリに対して iCloud と iCloud キーチェーンのバックアップが既に有効になっており、古いデバイスでデバイスバインドの Authenticator パスキーを持っているユーザーは、この改善されたエクスペリエンスの恩恵を自動的に受けます。
- 新しい iOS デバイスで Authenticator アプリを復元すると、復元フローが更新されます。

この変更により、ユーザーは古いデバイスへのアクセスに基づいて適切な回復パスに移動し、デバイス間のパスキー認証を理解し、新しい電話にスムーズに移行できるようにすることで、復元エクスペリエンスを効率化します。

この更新プログラムは、iOS デバイスにのみ適用されます。 Android のサポートが続きます。

Authenticator アプリでのバックアップと復元の変更の[詳細について説明](https://support.microsoft.com/account-billing/back-up-account-credentials-in-microsoft-authenticator-bb939936-7a8d-4e88-bc43-49bc1a700a40)します。

#### パブリック プレビュー - Microsoft Defender での SOC ID 応答用の新しい組み込み Entra ロール

**種類:** 新機能**サービス カテゴリ:** RBAC**製品の機能:** ID のセキュリティと保護

6 月 8 日から、セキュリティ チームが最小特権アクセス モデルを使用してMicrosoft Defenderから開始された ID 封じ込めアクションを実行する方法をさらに改善するために、Microsoft Entra (**SOC Identity Responder**) に新しい組み込みロールが導入されます。

以前は、これらのアクションを実行するには、SOC アナリストが複数の高い特権 Entra ロールを保持するか、ID 管理者に依存する必要があります。これにより、アクティブな調査中に遅延が発生しました。

この更新プログラムにより、 **SOC アナリストには、ID 応答アクション用に専用のロールを割り当てることができ**、広範なディレクトリ管理特権を付与することなく、ユーザーの無効化、セッションの取り消し、パスワードリセットの強制などの主要なアクションを実行できます。

このロールへのアクセスは、柔軟な割り当てモデルをサポートします。これには、グループ メンバーシップと委任された所有権を通じてアクセス許可を管理するための **ロール割り当て可能な** グループが含まれます。 **Privileged Identity Management (PIM)** とのオプションの統合により、Just-In-Time アクティブ化とガバナンス制御の強化が可能になります。

これらのアクションは引き続きMicrosoft Entraによって適用および監査されるため、既存の RBAC およびコンプライアンス制御との一貫性が確保されます。

#### パブリック プレビュー - AD グループの適用による AD グループへの未承認の変更を防止する

**種類:** 新機能**サービス カテゴリ:** プロビジョニング**製品の機能:** Entra Cloud Sync

AD へのグループ プロビジョニングを利用しているお客様の場合、この機能により、AD グループに対する変更は、Microsoft Entraで管理されているものと一貫性を保ちます。 このプレビューでは、特定の AD グループを指定して、それらのグループに対する変更を Entra プロビジョニング サービス経由でのみ行えるようにすることができます。 Entra の外部で行われた変更はブロックされ、発生する前の誤差を防ぎ、Entra IDグループと AD グループ間のアラインメントを維持します。

詳細情報: [AD グループの適用に関するドキュメント](https://aka.ms/ADEnforcementDocumentation)。

### 2026 年 5 月

#### パブリック プレビュー - Microsoft Entra デバイス オブジェクトの論理的な削除を有効にする

**種類:** 新機能**サービスカテゴリ:** デバイスアクセス管理**製品の機能:** Entra のバックアップと回復

プレビューで利用可能になった Device Soft Delete を使用すると、管理者はデバイス オブジェクトを完全に削除するのではなく、回復可能な状態に移動することで、デバイス オブジェクトを安全に削除できます。 この機能により、組織は、デバイス ID や関連するセキュリティ成果物などの重要なデータを保持しながら、定義された保持期間内にデバイスを復元できます。 この機能は、Microsoft Entra参加済み、登録済み、ハイブリッド参加済みデバイスをサポートし、デバイスのライフサイクル管理を改善しながら、誤って削除されるリスクを軽減するのに役立ちます。

#### 一般提供 - NetBiosName 解決テストの情報が提供されるようになりました

**種類：** 機能の変更**サービス カテゴリ:** Entra Connect**製品の機能:** Entra Connect

AD DS 正常性監視エージェントの "NetBIOS Name Sysvol Connectivity resolution" テストは、アラート テストから情報テストに *再分類* されました。 今後、このテストが失敗した場合、アラートが生成されたり、修復アクションが必要になることはなくなります。 代わりに、テストはバックグラウンドで実行され、情報についてのみ結果がログに記録されます。

**変更された内容**

**NetBIOS 名 Sysvol 接続テストが*情報専用*になりました**。 以前は、このテストが失敗したとき (たとえば、ドメイン コントローラーがその **SYSVOL 共有**にアクセスするために **NetBIOS 名**を解決できなかった場合)、Connect Health で**アラートがトリガーされ**、アクションを求めるメッセージが表示されていました。 これで、**このテストでエラーが発生した場合、**Microsoft Entra Connect Health でアラートは発生しません。

**この変更を行った理由**

**NetBIOS は、最新のActive Directory環境では重要ではないレガシ ネットワーク プロトコルです。** 多くの組織では、日常的な操作で NetBIOS の名前解決に依存しなくなりました。 **このテストを情報として再分類すると、アラート フィードのノイズが減り、ID インフラストラクチャにとって本当に重要な問題に集中できます。** 要するに、Connect Health アラートで *重要な問題* が強調表示され、重要でない条件にフラグを設定するのではなく、実際の問題に優先順位を付けるのに役立つ必要があります。

#### 今後の変更 - Microsoft Entra Connect Sync 構成の変更に対する管理者承認の強化

**種類：** 機能の変更**サービス カテゴリ:** Entra Connect**製品の機能:** Entra Connect

構成の変更に対する対話型の管理者承認を導入することで、Microsoft Entra Connect Sync のセキュリティ体制を強化しています。 この更新プログラムでは、承認された管理者がサインインし、同期設定の変更を明示的に承認し、構成の更新が意図的であり、適切なユーザーによって行われていることを確認する必要があります。

**変更点**

- **同期構成の変更に対する対話型管理者承認:** 今後、同期構成設定の変更 (機能の有効化や無効化など) には、承認されたクラウド管理者による対話型認証が必要になります。 Entra Connect ウィザードまたは PowerShell のどちらを使用している場合でも、アクションを完了するには、確認済みの管理者サインインが必要です。 これにより、同期関連のすべての構成変更の承認モデルが強化されます。
- **管理者主導の構成の一貫性の向上:** クラウド管理者によって行われた構成の決定が一貫して尊重されるように、同期動作を調整しています。 クラウドは同期機能の状態の信頼できるソースとして機能し、管理者は意図した構成が維持されるという信頼を高めます。
- **更新された管理パス:** Entra Connect のすべての管理インターフェイスには、必要に応じ、委任された管理者認証が組み込まれます。 Specifically:
- **Entra Connect ウィザードのフロー:** インストールと構成ウィザードでは、同期構成の変更に委任された管理者トークンが使用され、より安全な承認フローが提供されます。
- **PowerShell コマンドレット:** PowerShell ベースの同期設定の管理で、構成の変更を完了するための対話型管理者サインインが求められるようになりました。 管理者の資格情報を指定できるセッションで、これらのコマンドを実行してください。
- **アンインストール動作:** Entra Connect Sync をアンインストールし、テナントをクラウドのみの同期に変換するなど、クラウド側の変更を行う場合、アンインストール プロセスでは、クラウド テナントの設定を変更する前に管理者認証が必要になります。

**何が変わっていないか**

- 同期機能とエンド ユーザー エクスペリエンスは変更されません。 機能が有効または無効になっている場合、すべてが引き続き期待どおりに動作します。
- 管理者が同期機能を有効または無効にする方法に変更はありません。は、これらのアクションで対話型認証が必要になった場合に限られます。

この変更の Microsoft Entra Connect Sync .msi インストール ファイルは、Microsoft Entra管理センターの [Microsoft Entra Connect](https://entra.microsoft.com/#view/Microsoft_AAD_Connect_Provisioning/AADConnectMenuBlade/%7E/GetStarted) でのみ使用できます。

利用可能なバージョンの詳細については、 [バージョン履歴ページ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history) を参照してください。

#### パブリック プレビュー - SAP SuccessFactors プロビジョニング統合のワークロード ID ベースの認証

**種類:** 新機能**サービス カテゴリ:** プロビジョニング**製品の機能:** Entra IDへのインバウンド

Microsoft Entraでは、SAP SuccessFactors プロビジョニング用のワークロード ID ベースの認証が導入されています。 この新機能により、Microsoft Entra プロビジョニング サービスは、静的な資格情報 (ユーザー名とパスワード) ではなく、Entra ワークロード ID と有効期間の短いトークンを使用して SAP SuccessFactors に対して認証できます。

この変更は、 [2026 年 11 月までに SuccessFactors API の基本認証を廃止](https://help.sap.com/docs/successfactors-release-information/8e0d540f96474717bbf18df51e54e522/fcc05a902b4140e585d968c2fe4a96bc.html)する SAP の計画に備えて、より安全な認証モデルに移行するのに役立ちます。

変更点

- お客様は、既存のプロビジョニング構成を基本認証からワークロード ID ベースの認証に、プロビジョニング エクスペリエンスの更新された接続設定を通じて直接切り替えることができます。構成を再作成または再起動する必要はありません。
- この方法では、有効期間の長い資格情報を格納する必要がなくなります。また、SAP Cloud Identity Services を介して Entra と SAP SuccessFactors の間で標準ベースの認証方法を使用します。 この機能は、次のプロビジョニング シナリオに適用されます。
- [ユーザー プロビジョニングをActive Directoryする SAP SuccessFactors](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial)
- [ユーザー プロビジョニングをMicrosoft Entra IDする SAP SuccessFactors](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-cloud-only-tutorial)
- [SAP SuccessFactors ライトバック (SuccessFactors へのエントラ)](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-writeback-tutorial)

これがあなたに何を意味するか

- 上記の SAP SuccessFactors プロビジョニング統合のいずれかに対して基本認証を現在使用している場合は、統合が中断されないようにするために、2026 年 11 月より前にワークロード ID ベースの認証にアップグレードする必要があります。
- すぐに対処する必要はありませんが、直前の中断を避けるために、移行を早期に計画することをお勧めします。 新しい方法では、次の方法によってセキュリティが向上します。
- 保存されているパスワードの削除
- 有効期間の短い検証可能なトークンの使用
- SAP でサポートされている認証モデルに合わせる

推奨されるアクション

- テナントで使用可能になったら、新しい認証オプションを評価する
- ワークロード ID ベースの認証への既存のプロビジョニング ジョブの移行を計画してテストする
- 基本認証を参照する内部ドキュメントまたは運用プロセスを更新する

追加情報

詳細な構成ガイダンスと詳細な手順については、 https://aka.ms/EntraSAPSFConnectivityGuideを参照してください。

#### パブリック プレビュー - Microsoft Entra セキュリティ グループの秘密度ラベル

**種類:** 新機能**サービス カテゴリ:** グループ管理**製品の機能:** プラットフォーム

Microsoft Entra IDでは、パブリック プレビューでの Entra クラウド セキュリティ グループへのMicrosoft Purview秘密度ラベルの適用がサポートされるようになりました。

管理者は、ラベルを使用して、現在Microsoft 365 グループに適用されるものと同じラベルとポリシーを使用して、ゲスト アクセスなどのセキュリティ グループ設定を管理できます。

ラベルはMicrosoft Purviewで管理でき、Microsoft Entra管理センター、Azure ポータル、およびMicrosoft Graphを使用して適用できます。 詳細については、「[秘密度ラベルをMicrosoft Entraセキュリティ グループに割り当てる (プレビュー)」](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-sensitivity-labels)を参照してください。

#### 一般提供 - アカウント検出

**型：** 一般提供**サービス カテゴリ:** プロビジョニング**製品機能:** サード パーティ統合

接続されたアプリケーションのアカウント検出が、Microsoft Entra ID ガバナンスで一般提供されるようになりました。 この機能により、管理者は、孤立したアカウントを含め、接続されているアプリケーション内に存在するすべてのアカウントを可視化できます。

プロビジョニング エクスペリエンスから直接検出レポートを生成することで、組織は、Microsoft Entraでエンタープライズ アプリケーションに割り当てられていない接続されたアプリケーションのアカウントを識別し、アプリケーションのオンボードを簡略化できます。

この機能には、Microsoft Entra ID ガバナンスまたはMicrosoft Entra スイートライセンスが必要です。 詳細については、https://aka.ms/accountDiscoveryDocumentation を参照してください。

#### 一般提供 - テナント グループ間の同期

**型：** 一般提供**サービス カテゴリ:** プロビジョニング**製品の機能:** ID ライフサイクル管理

クロス テナント グループ同期を使用すると、組織はMicrosoft Entraテナント間でセキュリティ グループを同期できます。 この機能により、ソース テナントのグループ メンバーシップを一元的に管理しながら、それらのグループを 1 つ以上のターゲット テナントで使用できるようになり、テナント間のコラボレーションが簡素化され、重複するグループの管理に関連する管理オーバーヘッドが軽減されます。

クロス テナント グループ同期を使用すると、組織は既存のテナント間同期構成を拡張して、グループを含めることができます。共有アプリケーション アクセス、リソース承認、テナント間の一貫したグループベースのアクセス制御などのシナリオをサポートします。 管理者は、この機能にオプトインし、属性マッピングとクロス テナント アクセス ポリシーを構成して、ターゲット テナントへのグループ同期を有効にすることができます。 テナント間グループ同期を使用するには、Microsoft Entra ID ガバナンス ライセンスが必要です。 テナント間ユーザー同期機能の既存のライセンス要件は変更されません。 [https://learn.microsoft.com/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)。

#### 一般提供 - 最新化されたマイ アカウント ページ

**種類：** 機能の変更**サービス カテゴリ:** 最新化されたマイ アカウント ページ**製品の機能:** エンド ユーザー エクスペリエンス

マイ アカウント ポータル (myaccount.microsoft.com) で再設計された 3 ページの一般提供が予定されており、エンド ユーザーがより簡単かつ明確にアカウントを管理できるように最新化されたエクスペリエンスが提供されます。

再設計された [デバイス] ページには、ユーザーが自分の登録済みデバイスを簡単に表示および管理できる最新のレイアウトが用意されています。 BitLocker 回復キーが目立つように表示され、キーを取得するために IT ヘルプデスクに問い合わせる必要が減ります。

新しい [個人情報] ページでは、ユーザーは、言語と地域の設定と共にプロファイル情報を一元的に表示できるため、1 か所で個人情報を簡単に確認および更新できます。

再設計された [組織] ページでは、最新化されたエクスペリエンスが提供され、ユーザーが正常に組織を離れることができなかった長年の問題が解決されます。

可用性: これらのページは、2026 年 6 月末までにすべてのMicrosoft Entra IDのお客様に一般公開されます。 管理者の操作は必要ありません。ユーザーには、更新されたエクスペリエンスが自動的に表示されます。

#### 一般提供 - Microsoft Entra ID登録キャンペーンでのパスキーのサポート

**型：** 一般提供**サービス カテゴリ:** MFA**製品の機能:** ID のセキュリティと保護

Microsoft登録キャンペーンでは、認証方法として Passkeys (FIDO2) がサポートされるようになりました。 管理者は、サインイン時にパスキーを登録するようにユーザーを微調整する登録キャンペーンを構成し、組織が大規模にパスキーの導入を推進できるようにします。 この最初のロールアウト エクスペリエンスは、制限のないパスキー プロファイルに含まれるユーザー向けに最適化されています。

#### パブリック プレビュー - ライフサイクル ワークフローでのユーザー属性値の設定またはクリアを自動化する

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

ライフサイクル ワークフローでユーザー属性の更新タスクを導入し、既存の属性変更トリガー機能を拡張し、組み込みの顧客対応の方法で、ワークフロー内で属性の更新 (設定またはクリア値) を直接自動化することに興奮しています。 セキュリティで保護され、一貫性があり、監査可能なエクスペリエンスにより、組織は手動作業を減らし、ガバナンスを向上させ、ID の自動化をより確実にスケーリングできます。

#### 一般提供 - システム優先認証がMicrosoft Entra IDの第 1 要素に拡張されました

**型：** 一般提供**サービス カテゴリ:** MFA**製品の機能:** ID のセキュリティと保護

システム優先認証を拡張して、(2 番目の要素に加えて) Microsoftマネージド構成の**最初の要素**に適用します。 この変更により、システムはユーザーに登録された資格情報を評価し、サインイン フローの各ステップで最高ランクの認証方法を選択します。

その結果、強力でフィッシングに強い資格情報 (パスキーなど) を持つユーザーは、 **パスワードを使用せずに**サインインし、セキュリティとユーザー エクスペリエンスの両方を向上させる可能性があります。

この動作は、システム優先認証が第 1 要素認証と第 2 要素認証の両方を対象とするMicrosoftマネージド状態にのみ適用されます。 ロールアウトは現在進行中であり、6 月末までにすべてのMicrosoftマネージド テナントに完全にデプロイされます。

#### 一般提供 - Microsoft Entra 外部 IDのハイ スケール互換性 (HSC) モード

**型：** 一般提供**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** B2B/B2C

高スケール互換性 (HSC) モードを使用すると、**組織は既存のユーザー ディレクトリを保持しながら、Microsoft Entra 外部 IDに移行できます**。 これは、AZURE AD B2C から移行する、確立された大規模な顧客 ID プラットフォーム向けに設計されています。

HSC モードを使用すると、既存のユーザーの継続性を維持しながら **、外部 ID でアプリケーションを再構築** し **、シームレスで段階的な移行を大規模に**サポートできます。 一部の高度なカスタマイズ機能は、このモードでは制限されており、進化し続けます。 詳細については、「 [外部 ID ハイ スケール互換性 (HSC) モードを有効にする」を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/enable-external-id-high-scale-compatibility-mode)参照してください。

#### Microsoft Entra IDのパスキー (FIDO2) 用に拡張されたポリシー ストレージ

**種類：** 機能の変更**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

認証方法ポリシーのパスキー (FIDO2) ポリシー サイズの制限を、専用の 20 KB の割り当てに増やしました。

以前は、すべての認証方法で 20 KB のポリシー サイズ制限が 1 つ共有されました。 この更新プログラムでは、パスキー (FIDO2) ポリシー専用の 20 KB の制限が割り当てられるようになりました。残りの認証方法では、引き続き既存の制限が使用されます。

この変更は、テナントがポリシー サイズの全体的な制限に近づくシナリオに対処するのに役立ちます。これにより、パスキー プロファイルの構成がブロックされる可能性があります。 パスキー ポリシー ストレージを分離することで、組織はパスキーをより簡単に導入し、高度なターゲット設定シナリオを構成できます。

さらに、テナントあたりのパスキー プロファイルの最大数が 3 から 10 に増加しました。

#### パブリック プレビュー - Azure ロールの割り当てをエンタイトルメント管理を使用して管理できるようになりました

**種類:** 新機能**サービス カテゴリ:** エンタイトルメント管理**製品の機能:** ID ガバナンス

アクセス パッケージを使用して、管理グループ、サブスクリプション、およびリソース グループ の各レベルで、Azure ロールに対する有資格割り当てとアクティブな割り当てを直接管理できるようになりました。 これにより、ロールの割り当ては、アプリ、グループなどと同じ要求、承認、ライフサイクル ガバナンス モデルに取り込まれます。これにより、最小限の特権と Just-In-Time アクセスに合わせて調整しながら、Azure リソースへのアクセスを大規模に管理しやすくなります。

#### 一般提供 - ライフサイクル ワークフローを使用してエージェント ID スポンサー プランのライフサイクルを管理する

**型：** 一般提供**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

エージェント ID を管理する最も重要な部分の 1 つは、エージェント ID のリソースへのアクセスが最新であることを確認するために、委任された人間のユーザーが常に割り当てられていることを確認することです。 スポンサーが組織を離れる場合、エージェント ID のスポンサーシップは自動的にマネージャーに転送されます。 スポンサーシップが移転された場合、エージェント ID のアクセスとライフサイクルを管理すべき責任を負う人間のユーザーが常に存在します。 Microsoft Entra ID ガバナンス機能は、組織内でこのプロセスを効率化するのに役立ちます。 ライフサイクル ワークフローには、スポンサープランの変更が迫っていることを共同スポンサーやスポンサーのマネージャーに通知する複数のタスクが含まれます。 エージェント ID スポンサーのワークフローの設定に関するガイドについては、「 [ライフサイクル ワークフローのエージェント ID スポンサー タスク」を](https://learn.microsoft.com/ja-jp/entra/id-governance/agent-sponsor-tasks)参照してください。

### 2026 年 4 月

#### 一般提供 - Microsoft Entra エージェント ID プラットフォーム

**型：** 一般提供**サービス カテゴリ:** 他**製品の機能:** ID のセキュリティと保護

Microsoft Entra エージェント ID プラットフォームが一般公開されました。 エージェント ID プラットフォームは、エンタープライズ環境で動作する AI エージェント専用に構築された ID と承認フレームワークを提供します。 これにより、開発者は、OAuth 2.0、MCP、A2A などの標準プロトコルを使用して、エンタープライズ レベルの認証、承認、ガバナンスを使用してエージェント ID を作成および管理できます。

詳細については、「[Microsoft Entra エージェント IDとは」](https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-microsoft-entra-agent-id)を参照してください。

#### パブリック プレビュー - アカウント検出

**型：** パブリック プレビュー**サービス カテゴリ:** プロビジョニング**製品機能:** サード パーティ統合

Microsoft Entra ID ガバナンスでは、パブリック プレビューで接続されているアプリケーションのアカウント検出がサポートされるようになりました。 この機能により、管理者は、孤立したアカウントを含め、接続されているアプリケーション内に存在するすべてのアカウントを可視化できます。

プロビジョニング エクスペリエンスから直接検出レポートを生成することで、組織は、Microsoft Entraでエンタープライズ アプリケーションに割り当てられていない接続されたアプリケーションのアカウントを識別し、アプリケーションのオンボードを簡略化できます。

この機能には、Microsoft Entra ID ガバナンスまたはMicrosoft Entra スイートライセンスが必要です。 詳細情報: https://aka.ms/accountDiscoveryDocumentation

#### パブリック プレビュー - 外部 ID (EEID) を使用したMicrosoft Entra IDフェデレーション

**型：** パブリック プレビュー**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品機能:** サード パーティ統合

外部 ID (EEID) を使用したMicrosoft Entra IDフェデレーションを使用すると、組織はユーザーが既存の従業員Entra ID ID を使用して顧客向けアプリケーションにサインインできるようになります。 標準ベースのフェデレーションを利用することで、ユーザーはホーム テナントで認証を行い、外部 ID テナントでホストされているアプリケーションはEntra IDからの信頼された ID アサーションに依存します。 このアプローチにより、重複するアカウントの必要性が軽減され、サインイン エクスペリエンスが合理化され、組織は従業員と顧客のシナリオ全体で一貫したセキュリティ制御を拡張できます。 詳細については、「[OpenID Connect ID プロバイダーとして Microsoft Entra ID テナントを追加する (プレビュー)](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-entra-id-federation-customers)」を参照してください。

#### パブリック プレビュー - Microsoft Entra テナントのブランド化テーマを使用したアプリベースのブランド化

**型：** パブリック プレビュー**サービス カテゴリ:** ユーザー エクスペリエンスと管理**製品の機能:** ユーザー認証

Microsoft Entraテナントでは、お客様はすべてのアプリに適用される、テナント全体でカスタマイズされた単一のブランド化エクスペリエンスを作成できます。 お客様が特定のアプリケーションに対して異なるブランド化エクスペリエンスを作成できるように、ブランド化の "テーマ" の概念を導入しています。

#### 今後の変更 - Microsoft Entra Connect Sync から Microsoft Entra Cloud Sync に移行する

**種類:** 変更の計画**サービス カテゴリ:** Entra Connect**製品の機能:** Entra Connect

組織が ID セキュリティを強化し、ゼロ トラスト戦略を進める中で、多くの組織はハイブリッド ID を管理するためのよりシンプルで信頼性の高い方法を探しています。 これらのニーズをサポートするために、[Microsoft Entra Connect Sync](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect-v2) からクラウドネイティブ [Microsoft Entra クラウド同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync)への移行を開始しています。これにより、オンプレミスの複雑さを軽減しながら、セキュリティ、信頼性、日常の管理性が向上します。

このシフトは、より安全で回復性があり、運用が容易な同期エクスペリエンスを提供するクラウドマネージド ID の未来に向けた重要なステップです。 継続的な最新化の取り組みの一環として、Microsoftの戦略は引き続き、より強力なセキュリティ、信頼性の向上、およびよりシンプルな ID 操作を提供します。

##### 次は何ですか

2026 年 7 月から、M365 メッセージ センター、Entra Connect Health、および個々の移行タイムラインに関する対象メールを通じて、お客様への通知を開始します。 移行は段階的にロールアウトされ、割り当てられた移行ウィンドウが開始されると、各組織に直接連絡します。 この段階的なアプローチにより、すべての顧客に合わせたガイダンスとサポートを確実に提供できます。

- **初期フェーズ:** 最初のウェーブでは、Entra Cloud Sync が ID 同期のすべてのニーズを既に満たしているテナントに焦点を当てます。 組織が高度な機能に依存している場合、またはディレクトリが大きい場合、最初の対象グループには含 ***まれません*** 。 Entra Cloud Sync の現在の機能で完全にサポートされている簡単な構成を使用するお客様の早期移行に優先順位を付けます。
- **後続のフェーズ:** Entra Cloud Sync の機能の拡張に合わせて、後のグループに段階的に通知し、Entra Cloud Sync で同等のサポートを利用できるようになったら、それらのグループが正常に移行できることを確認します

Microsoft は、Entra Cloud Sync への移行に関するツールとドキュメントを提供することで、お客様をサポートすることを約束します。

##### 何が変わっているのか

割り当てられた移行期間が組織に通知されると、Entra Cloud Sync への移行を開始するのに役立つ詳細なガイダンスとリソースが表示されます。この期間中:

- 現在の構成を確認し、準備状況を評価し、Cloud Sync の機能について理解します。
- スムーズな移行をサポートするために、移行ツールと詳細なドキュメントにアクセスできます。
- 永続的な変更が行われる前に、Entra Cloud Sync で同期環境を移動してテストします。

Entra Cloud Sync への移行が正常に完了したら、

- Entra Cloud Sync は、Active DirectoryとEntra IDの間の ID 同期機能の主要なメカニズムであり、Entra Connect ツールの ID 同期機能を置き換えます。

##### 何が変わっていないか

クラウド同期に移行すると、接続同期構成ウィザードでの移行後も、クラウド リソースへのアクセスにオンプレミスの資格情報を使用できるようにするハイブリッド認証機能が引き続き使用できるようになります。

**今すぐ準備を始める**

移行を開始するための手順を実行することをお勧めします。 [Entra Cloud Sync](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync) について理解を深め、専用リソースを確認してスムーズな移行を実現できます。

- [クラウド同期の詳細 – しくみ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/concept-how-it-works)
- [ステップ バイ ステップの移行ガイダンス](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/migrate-azure-ad-connect-to-cloud-sync)
- 移行シナリオ:
- [同期されたActive Directory フォレストのクラウド同期をMicrosoft Entraに移行します](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-pilot-aadc-aadccp)
- [Microsoft Entra Connect Sync Group Writeback v2 を Microsoft Entra Cloud Sync に移行する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/migrate-group-writeback)
- [Microsoft Entra Cloud Sync と Microsoft Entra Connect Sync の機能の比較](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/connect-to-cloud-sync-decision-guide#comparison-between-microsoft-entra-connect-and-cloud-sync)

Microsoft Entraでは、既存の Connect Sync デプロイの運用を継続しながら、ユーザーとグループの管理をクラウドに移行できるソース オブ オーソリティ (SOA) 機能がサポートされています。 これらの機能は、環境を簡素化し、オンプレミスインフラストラクチャへの長期的な依存関係を減らし、将来の移行の準備を改善するのに役立ちます。 これが ID 戦略と一致する場合は、次のリソースが役立つ可能性があります。

- SOA 計画のための IT アーキテクト ガイダンス: https://aka.ms/SOAITArchitectsGuidance
- [ユーザー SOA](https://aka.ms/UserSOAdocs): ハイブリッド共存を維持しながら、Entra IDでユーザーを直接管理する
- [グループ SOA](https://aka.ms/GroupSOAdocs): 必要に応じてオンプレミスに影響を与えるクラウドマネージド グループ

これはクラウド同期に移行するための前提条件ではありませんが、自分のペースで準備する機会を提供します。

今後の更新については、このページをご覧ください。

#### 変更の計画 - 先進認証を使用するように SCIM プロビジョニング アプリケーションを更新する

**種類:** 変更の計画**サービス カテゴリ:** プロビジョニング**製品の機能:** SaaS アプリケーションへの送信

##### 変更される内容

- OAuth 2.0 認証コードの付与を使用する SCIM プロビジョニング アプリケーションは、OAuth 2.0 クライアント資格情報やワークロード ID フェデレーションなどの最新の認証方法をサポートするように更新されます。
- 既存のプロビジョニング ジョブは自動的に切り替わります。 お客様は、新しい方法を使用できるようになった後にジョブの構成を更新する必要があります。
- 最新のメソッドをサポートできない少数のアプリケーションは、Microsoft Entra アプリ ギャラリーから廃止される可能性があります。

##### これが変わったとき

この変更は今後数か月にわたってロールアウトされ、タイミングはアプリケーションによって異なります。 影響を受けるアプリケーション、顧客の期限、サポート ドキュメントは、毎月の新機能に関する記事とMicrosoft 365 メッセージ センターを通じて共有します。

##### これが変更される理由

この更新プログラムは、古い認証パターンから離れてMicrosoft Entraプロビジョニング統合のセキュリティを強化します。 最新の方法は、サービス間のシナリオに適しており、共有シークレットをローテーションする必要など、資格情報管理のオーバーヘッドを軽減できます。

##### お客様が必要とするアクション

- OAuth 2.0 承認コードの付与を使用する既存のプロビジョニング ジョブを特定します。
- 影響を受けるアプリケーションと、更新された認証方法の可用性に関するお知らせを確認します。
- アプリケーションで先進認証方法がサポートされている場合は、プロビジョニング ジョブの構成を更新してテストします。
- アプリケーションが廃止された場合は、サポートされている代替手段に移行することを計画します。

##### 最新情報を入手

今後のお知らせ、移行ガイダンス、期限、ドキュメントについては、月次の新しい記事とMicrosoft 365 メッセージ センターを監視してください。

#### パブリック プレビュー - サインイン API での$countフィルター処理

**型：** パブリック プレビュー**サービス カテゴリ:** MS Graph**製品の機能:** 監視とレポート

サインイン API 要求で$countを使用できるようになりました。これにより、お客様は API 要求でカウント計算を直接実行できます。 詳細については、「[クエリ パラメーターを使用してMicrosoft Graph応答をカスタマイズする](https://learn.microsoft.com/ja-jp/graph/query-parameters)」を参照してください。

#### 変更の計画 - SAP SuccessFactors プロビジョニング統合の基本認証からワークロード ID ベースの認証に切り替える

**種類:** 変更の計画**サービス カテゴリ:** プロビジョニング**製品の機能:** Entra IDへのインバウンド

Microsoft Entraでは、SAP SuccessFactors プロビジョニング用のワークロード ID ベースの認証が導入されています。 この新機能により、Microsoft Entra プロビジョニング サービスは、静的な資格情報 (ユーザー名とパスワード) ではなく、Entra ワークロード ID と有効期間の短いトークンを使用して SAP SuccessFactors に対して認証できます。

この変更は、 [2026 年 11 月までに SuccessFactors API の基本認証を廃止](https://help.sap.com/docs/successfactors-release-information/8e0d540f96474717bbf18df51e54e522/fcc05a902b4140e585d968c2fe4a96bc.html)する SAP の計画に備えて、より安全な認証モデルに移行するのに役立ちます。

##### 何が変わっているのか

- 基本認証ではなく Entra ワークロード ID ベースの認証を使用するために、SAP SuccessFactors プロビジョニング アプリで 2026 年 5 月から新しい認証オプションを使用できるようになります。
- お客様は、既存のプロビジョニング構成を基本認証からワークロード ID ベースの認証に、プロビジョニング エクスペリエンスの更新された接続設定を通じて直接切り替えることができます。構成を再作成または再起動する必要はありません。
- この方法では、有効期間の長い資格情報を格納する必要がなくなります。また、SAP Cloud Identity Services を介して Entra と SAP SuccessFactors の間で標準ベースの認証方法を使用します。
- この機能は、次のプロビジョニング シナリオに適用されます。
- [ユーザー プロビジョニングをActive Directoryする SAP SuccessFactors](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial)
- [ユーザー プロビジョニングをMicrosoft Entra IDする SAP SuccessFactors](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-cloud-only-tutorial)
- [SAP SuccessFactors ライトバック (SuccessFactors へのエントラ)](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-writeback-tutorial)

##### これがあなたに何を意味するか

- 上記の SAP SuccessFactors プロビジョニング統合のいずれかに対して基本認証を現在使用している場合は、統合が中断されないようにするために、2026 年 11 月より前にワークロード ID ベースの認証にアップグレードする必要があります。
- すぐに対処する必要はありませんが、直前の中断を避けるために、移行を早期に計画することをお勧めします。

新しい方法では、次の方法によってセキュリティが向上します。

- 保存されているパスワードの削除
- 有効期間の短い検証可能なトークンの使用
- SAP でサポートされている認証モデルに合わせる

##### 推奨されるアクション

- テナントで使用可能になったら、新しい認証オプションを評価する
- ワークロード ID ベースの認証への既存のプロビジョニング ジョブの移行を計画してテストする
- 基本認証を参照する内部ドキュメントまたは運用プロセスを更新する

##### 追加情報

詳細な構成ガイダンスと詳細な手順については、Microsoft Learn ドキュメントで公開します。

#### 一般提供 - アカウント無効化ロジックをカスタマイズするために Workday 終了データをプリフェッチする

**型：** 一般提供**サービス カテゴリ:** プロビジョニング**製品の機能:** Entra IDへのインバウンド

この Workday コネクタ更新プログラムは、APAC および ANZ リージョンのワーカーに対して観察された終了処理の遅延を解決します。 管理者は、終了先読み設定を有効にしてデータをプリフェッチし、Microsoft Entra IDおよびオンプレミスの Active Directoryのアカウントのプロビジョニング解除ロジックを調整できるようになりました。 詳細については、以下を参照してください。 https://aka.ms/WorkdayTerminationLookaheadDoc

#### 一般提供 - iOS および CBA での証明書ベース認証 (CBA) のサポートを第 2 要素としてMicrosoft Entra

**型：** 一般提供**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

Microsoft Entra Certificate-Based認証 (CBA) が iOS で一般提供されるようになりました。 ネイティブ iOS サインインでは、不要なパスワードと MFA プロンプトが回避され、サポートされている 2 番目の要素として CBA が有効になり、システム優先 MFA メソッドとして優先順位を付けることができるようになりました。 ユーザーは、テナント ポリシーに基づいて、必要に応じて別の許可される MFA 方法を選択できます。 [Apple デバイスでの証明書ベースの認証のMicrosoft Entraに関する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-mobile-ios)詳細

#### 一般提供 - Microsoft Identity Manager (MIM) 2016 Service Pack 3 (SP3)

**型：** 一般提供**サービス カテゴリ:** Microsoft Identity Manager**製品の機能:** ID ガバナンス

Microsoft Identity Manager (MIM) 2016 Service Pack 3 (SP3) が利用可能になりました。 SP3 では、安定性とサポート性に重点を置き、現在のプラットフォーム コンポーネント (SQL Server、SharePoint、Exchange) との互換性を最新化し、同期サービスの追加の展開オプションを追加しますAzure SQL Databaseマネージド ID 認証を使用して、ハイブリッド ID 環境の運用リスクを軽減できます。

修正された問題と、この更新プログラムに追加された機能強化は次のとおりです。

##### MIM 同期サービス

- SQL Server 2022 サポート: SQL Server 2022 とのインストールと接続の完全なサポート。
- Azure SQLサポート: MIM Sync では、システム割り当てマネージド ID とユーザー割り当てマネージド ID の両方で認証がサポートされているAzure SQL Databaseを使用できるようになりました。

##### MIM サービスとポータル

- SQL Server 2022 および Exchange Server Subscription Edition (SE) のサポート: 最新の SQL およびExchange リリースとの統合とデータベースの互換性が更新されました。
- SharePoint サブスクリプション エディション (SE) のサポート: MIM ポータルを SharePoint SE にデプロイできるようになりました。
- System Center Service Manager Data Warehouse (DW) 2022 サポート: 最新の SCSM DW とのレポートと監査の統合を有効にします。
- Active Directory フェデレーション サービス (AD FS) (AD FS) シングル Sign-On (SSO): クレーム ベース認証のサポートが導入され、エンド ユーザーは統合認証ではなく AD FS 経由でサインインWindows

##### ダウンロードとアップグレードの情報

- ライセンスに基づいて、次のインストーラー パッケージをダウンロードできます。[ライセンスとダウンロードMicrosoft Identity Manager |Microsoft Learn](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-licensing#obtaining-windows-installer-packages)
- SP3 では、新しいアップグレード プロセスが導入されています。 記載されている手順に注意して従ってください: [SP2 から SP3 に Microsoft Identity Manager 2016 をアップグレードする |Microsoft Learn](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/microsoft-identity-manager-2016-upgrade-from-service-pack-2-to-service-pack-3)

#### 一般提供 - AP 要求元として、アクセス パッケージの所有者が許可している承認者がマイ アクセスで確認できます。

**型：** 一般提供**サービス カテゴリ:** エンタイトルメント管理**製品の機能:** エンタイトルメント管理

5 月に、要求者は、保留中のアクセス パッケージ要求の承認者の名前と電子メール アドレスをマイ アクセス ポータルで直接確認できます。これは一般提供になります。 この機能により、透明性が向上し、要求者と承認者間のコミュニケーションが効率化されます。 テナント レベルでは、すべてのメンバー (ゲスト以外) に対して承認者の可視性が既定で有効になり、Microsoft Entra管理センターのエンタイトルメント管理設定を使用して制御できます。 access パッケージ レベルでは、管理者とaccess パッケージ所有者は、承認者の可視性を構成し、access パッケージ ポリシーの詳細要求設定でテナント レベルの設定をオーバーライドすることを選択できます。 詳細については、「 [保留中の要求の承認者情報を表示する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-access#view-approver-information-for-pending-requests)」を参照してください。

#### 一般提供 - システム優先 MFA メソッドの 3 番目のオプションとしての Entra CBA

**型：** 一般提供**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

一般提供 - iOS プラットフォームでの既知の問題により、iOS の 2 番目の要素として Entra 証明書ベースの認証 (CBA) メソッドが許可されず、 [FAQ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-system-preferred-multifactor-authentication#faq) に記載されているシステム優先 MFA リストの最後の場所に CBA が移動されました。

ネイティブ iOS アプリで証明書を使用してサインインする際のユーザー エクスペリエンスが向上しました。これは、既知のすべての問題に対処して不要なパスワードと MFA プロンプトを削除することで実現しました。 この機能拡張により、iOS の 2 番目の要素として CBA をサポートし、システム優先 MFA メソッドで CBA を 3 位に移行できます。

#### 一般提供 - GSA iOS クライアントのサポート

**型：** 一般提供**サービス カテゴリ:** iOS クライアント**製品の機能:** ネットワークアクセス

iOS Global Secure Access (GSA) クライアントの一般提供についてお知らせします。 iOS および iPadOS のグローバル セキュリティで保護されたアクセス クライアントには、新しいエージェントのインストールは必要ありません。 既存の Microsoft Defender for Endpoint (MDE) を利用して、Microsoft 365、インターネット アクセス、プライベート アクセスのために Microsoft SSE 経由でトラフィックをルーティングします。

#### 一般提供 - Entra CBA 証明機関 (CA) のスコープ

**型：** 一般提供**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

Microsoft Entraでの Entra CBA 証明機関 (CA) スコープを使用すると、テナント管理者は、特定の証明機関 (CA) の使用を定義されたユーザー グループに制限できます。 この機能は、承認されたユーザーのみが特定の CA によって発行された証明書を使用して認証できるようにすることで、証明書ベースの認証 (CBA) のセキュリティと管理性を強化します。 証明機関 [(CA) のスコープ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-technical-deep-dive#certificate-authority-ca-scoping)の詳細

#### 一般提供 - ファイルの種類に基づくネットワーク コンテンツ フィルタリング

**型：** 一般提供**サービス カテゴリ:** インターネット Access**製品の機能:** ネットワークアクセス

グローバル セキュリティで保護されたアクセスでは、ファイルの種類に基づくネットワーク ベースのコンテンツ フィルター処理がサポートされます。 これにより、ネットワーク経由で GenAI および SaaS アプリへのファイル転送を監視および制御して、コンテンツの不正な流出を防ぐことができます。 詳細については、「 [ネットワーク ファイルのコンテンツをフィルター処理するコンテンツ ポリシーを作成する](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-network-content-filtering)」を参照してください。

#### 一般提供 - リモート ネットワーク用 GSA クラウド ファイアウォール

**型：** 一般提供**サービス カテゴリ:** インターネット Access**製品の機能:** ネットワークアクセス

お客様は、GSA クラウド ファイアウォールを使用して、ブランチ オフィスから GSA リモート ネットワーク機能を介して取得したすべてのインターネット トラフィックに対して、管理者が構成可能な 5 タプル (ソース IP、宛先 IP、プロトコル、送信元ポート、宛先ポート) ベースのフィルター処理を適用できます。 詳細については、「 [グローバル セキュア アクセス クラウド ファイアウォールの構成](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-cloud-firewall)」を参照してください。

#### 一般提供 – アプリケーション用 SDK を使用したブラウザー委任 (Web ビュー) フローによる Entra 外部 ID ネイティブ認証でのソーシャル ID プロバイダーの有効化

**型：** 一般提供**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** 開発者エクスペリエンス

ネイティブ認証を使用して Entra External ID のアプリケーションの安全なサインインとサインアップ エクスペリエンスを構築します。また、開発者向け SDK を使用したブラウザー委任 (Web ビュー) 認証を通じて、Google、Facebook、Apple などのソーシャル ID プロバイダーのサポートを利用できます。 詳細については、「[Microsoft Entra 外部 IDでのネイティブ認証](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication)」を参照してください。

#### 一般提供 - PIM のアクティブ化ごとに MFA などの条件付きアクセス ポリシーを適用する

**型：** 一般提供**サービスカテゴリ:** 特権ID管理**製品機能:** 特権アイデンティティ管理

ロールのアクティブ化のための条件付きアクセスを使用して再認証を構成するための一般提供Microsoft Entra Privileged Identity Management機能。 詳細については、「[アクティブ化時に認証コンテキストMicrosoft Entra 条件付きアクセス要求](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-role-settings#on-activation-require-microsoft-entra-conditional-access-authentication-context)する」を参照してください。

#### 一般提供 - ライセンスの使用

**型：** 一般提供**サービス カテゴリ:** レポーティング**製品の機能:** 監視とレポート

Microsoft Entra 管理センターの [**ライセンスの使用状況]** ページは、テナント全体の機能の使用状況を可視化することで、お客様が Entra ライセンスを最適化するのに役立ちます。 所有している P1、P2、Suite ライセンスEntra ID数と、各ライセンスの種類にマップされた条件付きアクセスやリスクベースの条件付きアクセスなどの主要な機能の使用状況が表示されます。 また、過去 6 か月間の使用状況の傾向を確認することもできます。 このビューでは、ライセンスフットプリント、Entra から派生する価値、およびテナント内の潜在的な過剰使用リスクをより明確に理解できます。 詳細については、「[Microsoft Entra ライセンスの使用状況に関する分析情報](https://learn.microsoft.com/ja-jp/entra/fundamentals/concept-license-usage-insights)」を参照してください。

#### 一般提供 - Microsoft Entra CBA の発行者ヒント

**型：** 一般提供**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

発行者ヒントは現在一般提供されており、ユーザーが信頼され、組織に対して有効な証明書のみを選択するように求められることで、Entra 証明書ベース認証 (CBA) のサインイン エクスペリエンスを向上させることができます。 これにより、混乱を減らし、サインイン エラーを最小限に抑え、特に複数の証明書がインストールされているデバイスでの証明書の選択を合理化します。 発行者のヒントは、証明書の発行方法や管理方法を変更することなく、セキュリティと使いやすさの両方を強化するように設計されています。 詳細については、「 [発行者のヒント](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-technical-deep-dive#issuer-hints)」を参照してください。

#### 一般提供 - 構成可能なトークンの有効期間ポリシー

**型：** 一般提供**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** プラットフォーム

構成可能なトークン有効期間ポリシーが、Microsoft Entra IDで一般提供されるようになりました。 この機能を使用すると、管理者は、トークンの有効期間ポリシーを作成してアプリケーションとサービス プリンシパルに割り当てることで、Microsoft ID プラットフォームによって発行されるアクセス トークン、ID トークン、SAML トークンの有効期間をカスタマイズできます。

構成可能なトークン有効期間ポリシーを使用すると、セキュリティと使いやすさの要件を満たすようにトークン期間を調整できます。たとえば、機密性の高いアプリケーションのアクセス トークンの有効期間を短縮したり、実行時間の長い自動化シナリオに合わせて拡張したりできます。 詳細については、「[Microsoft ID プラットフォームでの構成可能なトークンの有効期間」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/configurable-token-lifetimes)。

### 2026 年 3 月

#### 変更の計画 – エージェント レジストリを Microsoft Agent 365 に統合する

**種類:** 変更の計画**サービス カテゴリ:** 他**製品の機能:** ディレクトリ

Microsoft では、テナント内のすべてのエージェントの監視、管理、セキュリティ保護を容易にするために、エージェント管理エクスペリエンスを統合しています。 エージェント 365 は、統一されたカタログ、一貫性のある可視性、および簡素化された管理を提供する、単一の信頼できるソースになります。

**変更点**

- [Entra 管理センター](https://entra.microsoft.com/)の [エージェント レジストリ] ブレードと [エージェント コレクション] ブレードは、2026 年 5 月 1 日に廃止されます。
- 管理者による操作は必要ありません。 エージェントの機能と管理は影響を受けません。 引き続き、[Microsoft 365 管理センター (MAC)](https://admin.microsoft.com/Adminportal/Home#/homepage)内のすべてのエージェント ビューでエージェント インベントリにアクセスできます。

**この変更により、次の操作が行われます。**

- エージェント 365 は、エージェントの統合レジストリおよびコントロール プレーンになります。
- Microsoft Entraは、エージェント ID を通じて ID 基盤を提供し続けます。
- 既存の [registry Graph API](https://learn.microsoft.com/ja-jp/graph/api/resources/agentregistry) は非推奨となり、エージェント 365 を搭載した新しい API に置き換えられます。 現在の API 経由で登録されたエージェントは、再登録する必要があります。 廃止日と新しいレジストリ Graph APIの可用性について詳しく説明します。
- すべてのエージェント アクセスおよびガバナンス機能は、エージェント ID とエージェント 365 を通じて完全に利用できます。

詳細については、「[Agent Registry convergence with Microsoft Agent 365](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-registry-convergence)を参照してください。

#### パブリック プレビュー - Microsoft Entraバックアップと回復が利用可能になりました

**型：** パブリック プレビュー**サービス カテゴリ:** Entra のバックアップと回復**製品の機能:** Entra のバックアップと回復

Microsoft Entraバックアップと回復は、偶発的な変更や悪意のある更新後にテナントを復元するのに役立つ組み込みのソリューションです。 既定では、重要なディレクトリ オブジェクト (ユーザー、グループ、アプリケーション、サービス プリンシパル、マネージド ID、条件付きアクセス ポリシー、名前付き場所、エージェント ID、認証および承認ポリシーなど) が自動的にバックアップされるため、管理者はそれらを以前に既知の良好な状態にすばやく復元できます。

プレビューでは、Entra Backup and Recovery は、テナントでサポートされているディレクトリ オブジェクトの毎日のバックアップを自動的に取得します。 テナントに P1 または P2 ライセンスMicrosoft Entra IDがある場合は、毎日 1 つのバックアップが作成され、5 日間保持されます。 管理者は、使用可能なスナップショットを表示したり、相違レポートを生成して変更内容を把握したり、復旧ジョブを実行してオブジェクトを以前の状態に復元したりできます。

これにより、信頼性の高い組み込みのセーフティ ネットが組織に提供され、自信を持って回復し、ダウンタイムを最小限に抑え、偶発的な変更、構成ミス、セキュリティ侵害からテナントを保護できます。 詳細については、「[Microsoft Entra Backup and Recovery の概要 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/backup/overview)を参照してください。

#### パブリック プレビュー - Entra Kerberos を使用した Entra ハイブリッド参加

**型：** パブリック プレビュー**サービスカテゴリ:** デバイス登録および管理**製品の機能:** デバイス ライフサイクル管理

この新機能により、Windows < デバイスはプロビジョニング時>C0>Hybrid Entra にすぐに参加できるようになります、Entra Connect 同期を待機したり、AD FS を要求したりすることはありません。 Entra Kerberos を活用することで、お客様はハイブリッド ID アーキテクチャを最新化しながら、インフラストラクチャの複雑さとレガシ フェデレーション コンポーネントへの依存を軽減できます。 詳細については、「Microsoft Entra Microsoft Entra Kerberos (プレビュー)を参照してください。

#### 一般提供 - Microsoft Entra IDでの同期されたパスキー

**型：** 一般提供**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

Microsoft Entra IDでは、一般提供の認証方法として同期されたパスキーがサポートされるようになりました。 同期されたパスキーは FIDO2 ベースの資格情報であり、組み込みまたはサードパーティのパスキー プロバイダーに格納でき、ユーザーのデバイスで使用できます。 管理者は、認証方法ポリシーのパスキー プロファイルを使用して、同期されたパスキーとデバイス バインド パスキーの使用を管理できます。 既存のパスキー構成は、同じEntra ID認証ポリシーとレポート サーフェイスを使用して管理できます。 詳細については、「 Microsoft Entra IDを参照してください。

#### 一般提供 - Microsoft Entra ID用 SCIM 2.0 API

**型：** 一般提供**サービス カテゴリ:** プロビジョニング**製品の機能:** ID ライフサイクル管理

SCIM 2.0 API は、クロスドメイン ID 管理システム (SCIM) 2.0 仕様を使用して、Microsoft Entraでユーザーとグループを管理するための標準ベースのオプションを顧客、開発者、パートナーに提供します。 詳細については、「[ENable Microsoft Entra SCIM 2.0 API](https://aka.ms/EnableEntraSCIMAPI)を参照してください。

#### パブリック プレビュー - テナント間セキュリティ グループの同期

**型：** パブリック プレビュー**サービス カテゴリ:** プロビジョニング**製品の機能:** コラボレーション

クロステナント グループ同期が導入されました。これは、組織がMicrosoft Entraテナント間でセキュリティ グループを同期できるようにする新機能です。 この機能により、ソース テナントのグループ メンバーシップを一元的に管理しながら、それらのグループを 1 つ以上のターゲット テナントで使用できるようになり、テナント間のコラボレーションが簡素化され、重複するグループの管理に関連する管理オーバーヘッドが軽減されます。

テナント間のグループ同期を使用すると、組織は既存のテナント間同期構成を拡張して、グループを含めることができます。共有アプリケーション アクセス、リソース承認、テナント間の一貫したグループベースのアクセス制御などのシナリオをサポートします。 管理者は、この機能にオプトインし、属性マッピングとクロステナント アクセス ポリシーを構成して、ターゲット テナントへのグループ同期を有効にすることができます。 テナント間グループ同期を使用するには、Microsoft Entra ID ガバナンス ライセンスが必要です。 テナント間ユーザー同期機能の既存のライセンス要件は変更されません。 詳細については、「 [テナント間同期とは」](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)を参照してください。

#### パブリック プレビュー - WindowsでのパスキーのMicrosoft Entra

**型：** パブリック プレビュー**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

Windowsのパスキー Microsoft Entraパブリック プレビューで使用できるようになりました。 この機能を使用すると、ユーザーはデバイスバインドのパスキーをローカル Windows Hello コンテナーに直接登録し、それらを使用して、Windows Hello生体認証または PIN を使用してMicrosoft Entra IDにサインインできます。

Windowsの Entra パスキーは標準の FIDO2 資格情報として動作し、デバイスを参加または登録Microsoft Entra必要とせずに Entra 認証フローに使用できます。 パブリック プレビュー中、この機能はオプトインであり、パスキー プロバイダーとしてWindows Helloを許可するには、パスキー プロファイルを使用した明示的な構成が必要です。 詳細については、「 Microsoft Entra ID

#### 一般提供 - Microsoft Entra IDのパスキー プロファイル

**型：** 一般提供**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

Microsoft Entra IDのパスキー プロファイルが一般公開されました。 パスキー プロファイルは、管理者が異なる要件を持つ複数のプロファイルを定義し、特定のユーザー グループを対象とできるようにすることで、パスキー (FIDO2) 認証を管理するための構造化された方法を提供します。

各プロファイルでは、許可されるパスキーの種類、構成証明の要件、認証の制限を指定できます。これにより、管理者と標準ユーザーなどのシナリオで区別されたポリシーを有効にすることができます。 以前にパスキーを構成したテナントの場合、既存の設定は既定のパスキー プロファイルに移行されます。 詳細については、「 Microsoft Entra IDを参照してください。

#### パブリック プレビュー - テナント のガバナンス関係

**型：** パブリック プレビュー**サービス カテゴリ:** テナント ガバナンス**製品の機能:** テナント ガバナンス

この機能を使用すると、管理者はテナント ガバナンス関係を要求して受け入れ、管理テナントに対するアクセス権と管理制御を管理者に付与できます。 詳細については、「[Microsoft Entra テナント ガバナンスのドキュメント (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/overview)を参照してください。

#### パブリック プレビュー - 関連テナント

**型：** パブリック プレビュー**サービス カテゴリ:** テナント ガバナンス**製品の機能:** テナント ガバナンス

この機能を使用すると、管理者は、B2B アクティビティまたは共有課金情報によって自分に接続されている関連テナントを検出できます。 管理者は、この情報を使用して、テナント のガバナンス関係を要求および確立したり、潜在的なリスクを検疫したりすることができます。 詳細については、「[Microsoft Entra テナント ガバナンスのドキュメント (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/overview)を参照してください。

#### パブリック プレビュー - テナント構成管理管理ポータルのエクスペリエンス

**型：** パブリック プレビュー**サービス カテゴリ:** テナント ガバナンス**製品の機能:** テナント ガバナンス

Entra 管理センターを使用して、Entra テナント ガバナンスのテナント構成管理機能を管理できるようになりました。 このエクスペリエンスを使用すると、次のことができます。

- さまざまなMicrosoft サービスにわたってテナント内のリソースの目的の状態を定義し、それらのリソースの実際の状態を継続的に監視できるモニターを作成および更新します。
- 監視結果のレポートと、定義したモニターを実行するときに構成管理サービスによって識別される構成ドリフトの詳細を確認します。
- アプリのアクセス許可または Entra ロールを割り当てることで、テナント内のリソースを監視するための構成管理サービスのアクセス許可を管理します。

詳細については、[テナント構成管理に関するドキュメントを参照してください](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/configuration-management)。

#### 一般提供 - Phish-Resistant MFA 資格情報を使用した認証に対する Linux の Microsoft 単一 Sign-On のサポート

**型：** 一般提供**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** SSO

このリリースで提供される主な機能強化は次のとおりです。

- 証明書 (PRMFA) で CBA/YubiKey を使用した認証を有効にします
- Intune インストールの一部としてJavaランタイムへの依存関係を削除します
- EntraId に対する認証時のパフォーマンスと信頼性の向上
- Entra 登録の代わりに Entra Join を使用してデバイスの信頼を提供します
- 認証要求の安定性とパフォーマンスの向上

詳細については、「[Linux のシングル サインオンMicrosoft](https://learn.microsoft.com/ja-jp/entra/identity/devices/sso-linux)を参照してください。

#### パブリック プレビュー - セキュリティで保護されたアドオン テナントの作成

**型：** パブリック プレビュー**サービス カテゴリ:** テナント ガバナンス**製品の機能:** テナント ガバナンス

アクセス許可を持つユーザーは、ホーム テナントによって所有および管理されるアドオン テナントを作成できるようになりました。 ガバナンスは、テナントのガバナンス関係を通じて確立され、管理者に GDAP を介したアクセスと制御を許可します。 詳細については、「[Microsoft Entra テナント ガバナンスのドキュメント (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/overview)を参照してください。

#### パブリック プレビュー - 条件付きアクセス最適化エージェントを使用したパスキー導入キャンペーン

**型：** パブリック プレビュー**Service category:** 条件付きアクセス**製品の機能:** ID のセキュリティと保護

**条件付きアクセスの最適化エージェントでは、パブリック プレビューでのパスキー導入キャンペーンがサポートされるようになりました。これにより、組織は構造化された自動化された方法でフィッシングに強い認証を展開できます。**

この機能を使用すると、エージェントはユーザーとデバイスの準備状況を評価し、推奨される展開計画を生成し、必要な手順をユーザーに案内し、ユーザーの準備ができたら条件付きアクセス ポリシーを自動的に適用できます。 キャンペーンは、前提条件が満たされると継続的に進行し、大規模なパスキーロールアウトの手動作業を減らします。

パスキー導入キャンペーンは、Microsoft Entra 管理センターから直接管理され、現在、特権管理者ロールを対象とします。 エージェントは、最初にレポート専用モードで条件付きアクセス ポリシーを作成し、管理者が適用前に影響を監視できるようにします。 詳細については、「[条件付きアクセス最適化エージェントを使用してパスキー導入キャンペーンをデプロイする (プレビュー)](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization-passkeys)」を参照してください。

#### パブリック プレビュー - 条件付きアクセス エージェントを使用した段階的ロールアウト

**型：** パブリック プレビュー**Service category:** 条件付きアクセス**製品の機能:** ID のセキュリティと保護

**条件付きアクセスの最適化エージェント**を使用して、レポートのみの条件付きアクセス ポリシー*を*段階的に安全にロールアウトできるようになりました。 プロセスを開始すると、エージェントはサインイン データを分析して、リスクの低い段階的な展開計画を推奨します。小規模なユーザー グループから始まり、徐々に拡張されるため、ポリシーを確実にオンにして、ユーザーへの影響を最小限に抑えることができます。 詳細については、「 [条件付きアクセス最適化エージェントの段階的ロールアウト](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization-phased-rollout)」を参照してください。

#### 一般提供 - マイ グループでの新しい M365 グループ作成エクスペリエンス

**型：** 一般提供**サービス カテゴリ:** グループ管理**製品の機能:** エンド ユーザー エクスペリエンス

**[My Groups](https://myaccount.microsoft.com/groups)** でのMicrosoft 365グループ作成エクスペリエンスが改善され、グループ所有者は最初からより詳細に制御および明確になります。 更新されたエクスペリエンスでは、作成時にキー グループ、電子メール、およびセキュリティ設定を構成できるため、後で管理者のヘルプを追加しなくても、グループが期待した方法で動作します。

この更新プログラムでは、次のことができます。

- グループの使用ガイドライン、電子メール エイリアス、秘密度ラベルを設定する
- ウェルカム メールの送信、会話へのメンバーのサブスクライブ、グループ メールボックスと予定表の表示などのExchange設定を構成Outlook
- グループに電子メールを送信できるユーザーを制御し、グローバル アドレス一覧からグループを非表示にし、外部の送信者を許可またはブロックする
- 必要に応じてセキュリティ グループ機能を有効にする

この合理化されたセルフサービス エクスペリエンスは、1 日目から適切な既定値とポリシーを使用してグループを作成するのに役立ちます。 3 月末までにすべてのテナントにロールアウトしています。

#### 一般提供 - Microsoft Entra Connect Health で TLS 1.2 が適用されるようになりました

**型：** 一般提供**サービス カテゴリ:** Entra Connect**製品の機能:** Entra Connect

Entra Connect Health 用の TLS 1.2 への完全な移行が完了し、セキュリティ強化の一環として従来の TLS 1.1 参照が削除されました。 正常性エージェントが最新であり、送信接続に TLS 1.2 を使用するようにサーバーが構成されていることを確認します。

**これが重要な理由** TLS 1.1 は、セキュリティの脆弱性により非推奨とされます。 この変更は、エージェント間の通信を保護し、最新のコンプライアンスの期待に合わせて調整するのに役立ちます。

**実行する必要がある操作** Entra Connect Health エージェントが最新であり、送信接続に TLS 1.2 を使用するようにサーバーが構成されていることを確認します。

- 環境内で [TLS 1.2](https://learn.microsoft.com/ja-jp/troubleshoot/entra/entra-id/ad-dmn-services/enable-support-tls-environment) サポートを有効にする

#### 一般提供 - Microsoft Entra 外部 IDでの Just-In-Time パスワード移行

**型：** 一般提供**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** B2B/B2C

Just-in-Time パスワード移行は、Microsoft Entra 外部 ID.

お客様は、最初のサインイン時にユーザー パスワードを安全に移行できるため、ユーザーはパスワードの強制的なリセットなしで既存の資格情報を引き続き使用できます。 これにより、移行のリスクと運用上のオーバーヘッドを軽減しながら、Azure AD B2C やその他の ID プロバイダーからの移行をスムーズに行えます。

#### 一般提供 - Entra 外部 ID ネイティブ認証での電子メールおよび SMS OTP MFA の有効化

**型：** 一般提供**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** 開発者エクスペリエンス

ネイティブ認証を使用して Entra External ID でアプリケーションの安全なサインインとサインアップ エクスペリエンスを構築します。電子メールと SMS OTP MFA は開発者向けの [SDK と API を](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication)通じて利用できます。

#### 一般提供 - テナント構成管理 API

**型：** 一般提供**サービス カテゴリ:** テナント ガバナンス**製品の機能:** テナント ガバナンス

テナント構成管理 API を使用すると、組織はテナントの現在の構成設定のスナップショットを JSON 形式で取得し、ドリフトを継続的に監視することで構成設定を適用できます。

詳細については、「 [テナント構成管理 API の概要」を](https://learn.microsoft.com/ja-jp/graph/unified-tenant-configuration-management-concept-overview)参照してください。

#### 一般提供 – 認証方法ポリシー更新監査ログの読みやすさの向上

**型：** 一般提供**サービス カテゴリ:** 認証 (ログイン)**製品の機能:** ユーザー認証

2026 年 4 月から、読みやすくわかりやすくするために、認証方法ポリシーの更新と認証方法のポリシー リセット監査ログ アクティビティが更新されました。 以前は、少数の設定のみが変更された場合でも、監査ログには、古い値と新しい値の両方に完全な認証方法ポリシー ペイロードが含まれていました。 この更新プログラムでは、監査ログ エントリは、変更された特定のプロパティと、対応する古い値と新しい値のみを表示するようになりました。

登録キャンペーンやシステム優先認証などのポリシー全体の更新には、引き続きポリシー ペイロード全体が含まれる場合があります。 アクティビティ名とトリガー イベントは変更されません。 この更新プログラムは書式設定にのみ影響し、ポリシーの動作は変更されません。 詳細については、「 [コア ディレクトリ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-audit-activities#core-directory)」を参照してください。

### 2026 年 2 月

#### 一般提供 - ライフサイクル ワークフロー属性変更トリガーでの拡張属性のサポート

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

ライフサイクル ワークフローの属性変更トリガーで追加の属性の種類がサポートされるようになりました。これにより、組織の変更をより広範に検出できるようになりました。 以前は、このトリガーはコア属性のセットに制限されていました。 この更新プログラムでは、次のいずれかの属性が変更されたときに応答するようにワークフローを構成できます。

- カスタム セキュリティ属性
- ディレクトリ拡張属性
- EmployeeOrgData 属性
- オンプレミス属性 1 ~ 15

この機能強化により、管理者はカスタム属性または拡張属性に基づいてムーバー イベントのライフサイクル プロセスを自動化する柔軟性が向上し、複雑な組織構造とハイブリッド環境のガバナンスが向上します。 詳細については、「 [ライフサイクル ワークフローでカスタム属性トリガーを使用する](https://learn.microsoft.com/ja-jp/entra/id-governance/workflow-custom-triggers)」を参照してください。

#### 一般提供 - ライフサイクル ワークフローでの委任されたワークフロー管理

**種類:** 新機能**サービス カテゴリ:** ライフサイクル ワークフロー**製品の機能:** ID ガバナンス

ライフサイクル ワークフローを管理単位 (AU) で管理できるようになりました。これにより、組織はワークフローをセグメント化し、特定の管理者に管理を委任できます。 この機能強化により、承認された管理者のみが、スコープに関連するワークフローを表示、構成、実行できるようになります。 お客様は、ワークフローを AU に関連付け、スコープ付きアクセス許可を委任された管理者に割り当て、ワークフローが定義されたスコープ内のユーザーにのみ影響を与えるようにすることができます。 詳細については、「 [委任されたワークフロー管理](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-delegate-workflow)」を参照してください。

#### 一般提供 - Microsoft Entra 外部 IDのデバイス承認付与フロー

**種類:** 新機能**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** B2B/B2C

Microsoft Entra ID (ワークフォース テナント) と同様に、Microsoft Entra 外部 ID (外部テナント) でデバイス承認付与フローがサポートされるようになりました。これにより、ユーザーはスマート テレビ、IoT デバイス、プリンターなどの入力に制約のあるデバイスにサインインできます。 詳細については、「 [OAuth 2.0 デバイス承認の付与](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-device-code)」を参照してください。

#### 一般提供 - ユーザー名/エイリアスを使用したサインイン

**種類:** 新機能**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** B2B/B2C

Microsoft Entra 外部 ID (EEID) では、ローカルの電子メールとパスワードで認証するユーザーは、代替サインイン識別子としてユーザー名 (エイリアス) を使用してサインインできるようになりました。 このエイリアスは、顧客またはメンバー ID、保険番号、フリークエント チラシ番号、または選択したユーザー名を表すことができます。 エイリアスは、ユーザーから収集したり、セルフサービス サインアップ中に割り当てたり、Microsoft Graph APIまたはMicrosoft Entra 管理センターを使用してユーザーの作成時またはユーザーの更新時に割り当てたりすることができます。 詳細については、「 [エイリアスを使用したサインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias)」を参照してください。

#### 今後の変更 - Microsoft Entraロールを持つユーザーのハードマッチをブロックするMicrosoft Entra接続セキュリティ更新プログラム

**種類:** 変更の計画**サービス カテゴリ:** Entra Connect**製品の機能:** Entra Connect

** Microsoft Entra Connect Sync と Cloud Sync でハードマッチングが行われますか?**

Microsoft Entra Connect または Cloud Sync がActive Directoryから新しいオブジェクトを追加すると、Microsoft Entra ID サービスは、受信オブジェクトの sourceAnchor 値を既存のクラウド マネージド オブジェクトの OnPremisesImmutableId 属性に対して検索することで、受信オブジェクトと Entra オブジェクトの照合を試みます。Microsoft Entra ID。 一致するものがある場合、Microsoft Entra Connect または Cloud Sync はそのオブジェクトのソースまたは機関 (SoA) を引き継ぎ、"ハードマッチ" と呼ばれる受信Active Directory オブジェクトのプロパティで更新します。

Microsoft Entra ID環境のセキュリティ体制を強化するために、特定の種類のハードマッチ操作を既定で制限する変更が導入されています。

**変更点**

**2026 年 6 月 1** 日より、Microsoft Entra ID は、Entra Connect Sync または Cloud Sync による、Active Directory から Microsoft [Entra ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)を保持する既存のクラウド管理の Entra ID ユーザー オブジェクトへの新しいユーザー オブジェクトのハードマッチングの試行をブロックします。

**これは、次のことを意味**します。

- クラウド マネージド ユーザーに既に [onPremisesImmutableId (sourceAnchor)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-design-concepts#sourceanchor) が設定されていて、Microsoft Entra ロールが割り当てられている場合、Microsoft Entra Connect Sync または Cloud Sync は、Active Directoryから受信ユーザー オブジェクトとハードマッチングすることで、そのユーザーの権限のソースを引き継ぐことができるようになります。
- このセーフガードにより、攻撃者は、Active Directory内のユーザー オブジェクトの属性を操作することで、Entra で特権を持つクラウドマネージド ユーザーを引き継ぐことができます。

**何が変わっていないか**

- Microsoft Entra ロールのないクラウド ユーザーのハード マッチ操作は影響を受けません。
- [ソフト マッチ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-existing-tenant?source=recommendations#hard-match-vs-soft-match) 動作は影響を受けません。
- 以前に一致したオブジェクトのActive DirectoryからEntra IDへの継続的な同期は影響を受けません。

**顧客のアクションが必要**

2026 年 6 月 1 日以降にハード マッチ エラーが発生した場合は、軽減策の手順に [関するドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-sync-errors#existing-admin-role-conflict) を参照してください。

#### 一般提供 - 外部 MFA は一般公開

**種類:** 新機能**サービス カテゴリ:** MFA**製品の機能:** ユーザー認証

Microsoft Entra IDの外部認証方法が、新しい名前 (外部多要素認証 (外部 MFA) で一般提供されるようになりました。 この機能により、組織は優先 MFA プロバイダーを引き続き使用しながら、多要素認証の要件を満たすことができます。 Microsoft Entra IDは ID コントロール プレーンのままであり、リアルタイムの条件付きアクセスの適用やサインイン リスク評価など、すべてのサインインに対して完全なポリシー評価とアクセスの決定を実行します。 詳細については、「 [外部 MFA を有効にする方法」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-external-method-manage)参照してください。

#### 一般提供 - Microsoft Entra 外部 IDでサポートされているカスタム禁止パスワード リスト

**種類:** 新機能**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** B2B/B2C

EEID 管理者は、既にサポートされている [グローバル禁止パスワード リスト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad#global-banned-password-list) に加えて、パスワードの作成時とリセット時にブロックする特定の文字列を追加できるようになりました。 詳細については、「 [パスワード保護 - カスタム禁止パスワードリスト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad#custom-banned-password-list)」を参照してください。

#### 今後の変更 – Authenticator アプリでの脱獄/ルート検出

**種類:** 新機能**サービス カテゴリ:** Microsoft Authenticator アプリ**製品の機能:** ID のセキュリティと保護

2026 年 2 月以降、Microsoft Authenticatorは Authenticator アプリのMicrosoft Entra資格情報の脱獄/ルート検出を導入します。 ロールアウトは警告モードからブロック モード→進行します。 Authenticator でMicrosoft Entra アカウントを引き続き使用するには、準拠しているデバイスに移行する必要があります。

#### パブリック プレビュー - Microsoft Entra登録を使用したWindows クライアントの BYOD サポート

**種類:** 新機能**サービス カテゴリ:** BYOD のサポート**製品の機能:** ネットワークアクセス

Microsoft Entra登録済みデバイスを使用したWindowsに対する Bring Your Own Device (BYOD) のサポートがパブリック プレビューで利用できるようになりました。 ユーザーとパートナーは、自分のデバイスから企業リソースをaccessできます。 管理者は、プライベート アプリケーション トラフィック プロファイルを内部ゲスト ユーザーを含む内部アカウントに割り当てることができます。 詳細については、「[Bring Your Own Device (Preview)](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-bring-your-own-device)を参照してください。

#### 一般提供 - カスタム ブロック ページ

**種類:** 新機能**サービス カテゴリ:** インターネット Access**製品の機能:** ネットワークアクセス

GSA でユーザーが危険な、NSFW、または承認されていないサイトまたはアプリにアクセスできないようにポリシーを構成すると、Microsoft Entra Internet Accessブランド化された明確な HTML エラー メッセージが表示されます。 多くの管理者から、会社のスタイル ガイドに合わせたテキスト、会社の使用条件に関するドキュメントへの吹き出し、IT ワークフローへのハイパーリンクなどを使用して、そのエクスペリエンスのカスタマイズを開始したいとお聞きしました。

グローバル セキュア Accessでは、インターネット Access用にカスタマイズされたブロック ページが提供されるようになりました。 Microsoft Graph APIでは、管理者は次のことができます。

- GSA ブロック ページのテナント全体の本文テキストを構成します。
- 制限付きマークダウンを介してハイパーリンクを追加して、利用規約、ServiceNow/IT チケット サービス、または MyAccess for ID ガバナンス ワークフロー統合を参照します。

詳細については、「[グローバルセキュアAccessブロックページをカスタマイズする方法](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-customize-block-page)を参照してください。

#### 一般提供 - Microsoft Entra Connect Sync で Windows Server 2025 がサポートされるようになりました

**種類:** 新機能**サービス カテゴリ:** Entra Connect**製品の機能:** Entra Connect

Microsoft Entra Connect Sync では、[Windows Server 2025](https://www.microsoft.com/evalcenter/download-windows-server-2025) が正式にサポートされるようになりました。 つまり、Microsoft Entra Connect Sync を 2025 年Windows Server実行しているサーバーに確実にインストールして実行できるため、ハイブリッド ID 環境で最新のWindows Server拡張機能を最大限に活用できます。

 この更新プログラムにより、組織は ID 同期サーバーを 2025 年Windows Serverにためらうことなくアップグレードできます。 Windows Server 2025 では、セキュリティ、パフォーマンス、柔軟性を向上させる高度な機能が提供され、エンジニアリング チームはこのプラットフォームで Microsoft Entra Connect Sync を徹底的に検証しました。 多くのお客様は、セキュリティの強化、パフォーマンスの向上、および管理機能の向上を活用するために、Windows Server 2025 を採用することを熱望しています。 正式なサポートが導入されたので、信頼性の高い完全にサポートされているハイブリッド ID ソリューションを維持しながら、これらの機能強化の恩恵を受けることができます。

Microsoft Entra Connect Sync .msi インストール ファイルは、[Microsoft Entra Connect](https://entra.microsoft.com/#view/Microsoft_AAD_Connect_Provisioning/AADConnectMenuBlade/%7E/GetStarted) のMicrosoft Entra 管理センターでのみ使用できます。 利用可能なバージョンの詳細については、 [バージョン履歴ページ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history) を参照してください。

** Cloud Sync:** Microsoft Entra Cloud Sync への移行は、クラウドから動作し、お客様がオンラインで同期設定を設定および管理できる同期クライアントです。 Microsoft ではクラウド同期の使用をお勧めしています。クラウド同期を通じて同期エクスペリエンスを向上する新機能が導入されているためです。クラウド同期がご自分に適した選択肢であれば、それを選択すると、将来移行しなくて済むようになります。 [サポートされている同期シナリオの比較](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/common-scenarios)を使用して、Cloud Sync が適切な同期クライアントであるかどうかを確認します。

#### パブリック プレビュー - マイ アカウントの新しいエンド ユーザー のホーム ページ

**種類:** 新機能**サービス カテゴリ:** マイ プロファイル/アカウント**製品の機能:** エンド ユーザー エクスペリエンス

`https://myaccount.microsoft.com`のホームページが更新され、タスクに重点を置いたエクスペリエンスが提供されています。 ユーザーには、期限切れのグループの更新、accessパッケージ要求の承認、ホームページでの MFA の直接設定などの保留中のアクションが表示されます。 アプリ、グループ、access パッケージ、サインインの詳細へのクイック リンクが見つけやすくなりました。 この変更は、アカウント管理を効率化し、ユーザーがaccessとセキュリティのタスクを管理できるように設計されています。

#### 一般提供 - 21Vianet が運営するMicrosoft Azureで利用可能な Microsoft Entra プロビジョニング サービス

**種類:** 新機能**サービス カテゴリ:** プロビジョニング**製品の機能:** SaaS アプリケーションへの送信

Microsoft Entra プロビジョニング サービスは、21Vianet/China クラウドで、API 駆動型プロビジョニング、クラウド同期、中国テナント間のクロステナント同期、非ギャラリー/カスタム アプリケーションの SCIM プロビジョニング、オンプレミス アプリ プロビジョニング (ECMA) のシナリオで使用できます。 Workday、SuccessFactors、AWS などの特定のギャラリー コネクタは、環境にオンボードされません。 詳細については、「 [ギャラリー アプリケーションでは、米国政府または 21Vianet (中国) クラウドでのプロビジョニングはサポートされていません](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/known-issues#gallery-application-doesnt-support-provisioning-in-us-government-or-21vianet-china-clouds)」を参照してください。

#### 一般提供 - My Access で以前に承認されたaccess パッケージの割り当てを取り消す

**種類:** 新機能**サービス カテゴリ:** エンタイトルメント管理**製品の機能:** ID ガバナンス

3 月末までにMicrosoft Entra ID ガバナンス承認者は、承認が既に付与された後、アクセス パッケージへのアクセスを取り消すことができます。 これにより、承認者は、変更、間違い、または更新されたビジネス ニーズに対応するための制御が強化されます。 この更新プログラムを使用すると、承認者は、事前の承認の決定を元に戻し、要求者のaccessをaccess パッケージに直ちに削除できます。 複数の承認者が同じ承認者グループに属している場合でも、最初に要求を承認した承認者のみが取り消すことができます。 詳細については、「 [要求の取り消し」を](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-approve#revoke-a-request)参照してください。

### 2026 年 1 月

#### 一般提供 - 同期されたオンプレミス AD ユーザーの機関のソースをクラウド ユーザーに変換する機能が利用可能になりました

**種類:** 新機能**サービス カテゴリ:** [ユーザー管理]**製品機能:** Microsoft Entra Cloud Sync

Microsoft Entra IDに対するオブジェクト レベルのソース オブ オーソリティ (SOA) 切り替えの一般提供についてお知らせします。 この機能を使用すると、管理者は個々のユーザーを Active Directory (AD) と同期してから、Microsoft Entra ID内のクラウド管理アカウントに移行できます。 これらのユーザーは AD 同期に関連付けられなくなり、ネイティブ クラウド ユーザーと同様に動作するため、柔軟性と制御性が向上します。 この機能により、組織は AD への依存を徐々に減らし、クラウドへの移行を簡素化しながら、ユーザーや日常業務の中断を最小限に抑えることができます。 Microsoft Entra Connect Sync と Cloud Sync の両方がこの SOA スイッチを完全にサポートしているため、スムーズな移行プロセスが保証されます。

詳細については、「 [クラウド優先の体制を採用する: ユーザーの機関ソース (SOA) をクラウドに転送する」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/user-source-of-authority-overview)を参照してください。

#### 一般提供 - ゲスト課金メーターの適用Microsoft Entra ID ガバナンス

**種類:** 新機能**サービス カテゴリ:** エンタイトルメント管理、ライフサイクル ワークフロー**製品の機能:** エンタイトルメント管理、ライフサイクル ワークフロー

Microsoft Entra ID ガバナンスゲスト課金メーターの適用がエンタイトルメント管理とライフサイクル ワークフローに対して有効になりました (アクセス レビューは CY26 Q1 の後半で適用されます)。 従業員テナントのゲスト ユーザーに対して Entra ID Governance Premium 機能を使用し続けるためには、有効なAzure サブスクリプションをリンクしてゲスト アドオンのMicrosoft Entra ID ガバナンスをアクティブ化する必要があります。 サブスクリプションがリンクされていない場合、新しいゲスト スコープのガバナンス構成 (たとえば、特定のaccess パッケージ ポリシー、access レビュー、ライフサイクル ワークフロー) の作成または更新が制限され、課金が構成されるまでゲスト固有のガバナンス アクションが失敗する可能性があります。

詳細については、「ゲスト ユーザーの[Microsoft Entra ID ガバナンスライセンス](https://learn.microsoft.com/ja-jp/entra/id-governance/microsoft-entra-id-governance-licensing-for-guest-users)を参照してください。

#### 一般提供 - Microsoft Entra 外部 IDのクライアント資格情報

**種類:** 新機能**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** B2B/B2C

Entra External ID でのクライアント資格情報の一般提供についてお知らせします。 OAuth 2.0 クライアント資格情報付与フローでは、Web サービス (機密クライアント) が、別の Web サービスを呼び出すときにユーザーを偽装するのではなく、独自の資格情報を使用して認証を行うことができます。 アクセス許可は、管理者によってアプリケーション自体に直接付与されます。

課金: Microsoft Entra 外部 IDに対してマシン間 (M2M) 認証を構成する場合は、[M2M Premium アドオン](https://www.microsoft.com/security/pricing/microsoft-entra-external-id/)を使用する必要があります。 組織のプレミアム アドオンの使用ポリシーを確認して、コストへの影響を理解し、実装が内部ガバナンスとライセンスのガイドラインに準拠していることを確認します。 詳細については、「[Microsoft ID プラットフォーム と OAuth 2.0 クライアント資格情報フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)を参照してください。

#### 一般提供 - Entra 外部 ID のブランド化テーマを使用したアプリベースのブランド化

**種類:** 新機能**サービスカテゴリ：** B2C - コンシューマーアイデンティティ管理**製品の機能:** B2B/B2C

Entra External ID (EEID) では、お客様はすべてのアプリに適用される、テナント全体でカスタマイズされた単一のブランド化エクスペリエンスを作成できます。 お客様が特定のアプリケーションに対して異なるブランド化エクスペリエンスを作成できるように、ブランド化の "テーマ" の概念を導入しています。 新しいライブ プレビュー機能は、保存する前に変更をすばやく視覚化するのにも役立ちます。 詳細については、「 [ブランド化テーマを使用してアプリケーションのサインイン エクスペリエンスをカスタマイズ](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-customize-branding-themes-apps)する」を参照してください。

#### 一般提供 - アラートと監視のためのサービス プリンシパル作成監査ログ

**種類:** 新機能**サービス カテゴリ:** 監査**製品の機能:** 監視とレポート

新しい監査ログのプロパティにより、管理者は、サービス プリンシパルが作成された理由と、だれが、何をトリガーしたかを簡単に理解できるようになりました。 ログには、プロビジョニング メカニズム、Just-In-Time 作成を有効にした特定の SKU またはサービス プラン、アプリ登録のホーム テナントがsurfaceされるようになりました。 これにより、管理者は、Microsoftドリブン プロビジョニングとテナント主導のアクティビティをすばやく区別し、新しく作成されたサービス プリンシパルに対するアラートと調査を合理化できます。 詳細については、以下を参照してください。

- [テナントでサービス プリンシパルが作成された理由を理解する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/understand-service-principal-creation-with-new-audit-log-properties)
- [Microsoft Entra プロビジョニング ログをダウンロードして分析する方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-analyze-provisioning-logs)

#### 一般提供 - Entra 外部 ID のセッション制御の条件付きAccess ポリシー

**種類:** 新機能**Service category:** 条件付きアクセス**製品の機能:** B2B/B2C

EEID 管理者は、条件付きAccessで永続的なブラウザー セッションとサインイン頻度を構成できます。 詳細については、「[Conditional Access: セッション制御を効果的に管理する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-session)を参照してください。

#### 一般提供 - ドメイン コントローラーの Entra プライベート Access

**種類:** 新機能**サービス カテゴリ:** プライベート アクセス**製品の機能:** ネットワークアクセス

ID の脅威からドメイン コントローラーを保護しながら、オンプレミスのアプリケーション (ローカルからローカルへのaccessなど) からアクセスした場合に、MFA をオンプレミス アプリケーションに取り込みます。 Kerberos 認証にドメイン コントローラーを使用するプライベート アプリに対してセキュリティで保護されたaccessを有効にします。 詳細については、「Active Directory ドメイン コントローラーの[構成Microsoft Entra Private Access](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-domain-controllers)を参照してください。

#### 一般提供 - リソースの除外を使用 *して、すべてのリソース* ポリシーの適用を強化しました

**種類：** 機能の変更**Service category:** 条件付きアクセス**製品機能:** アクセス制御

Microsoft Entra 条件付きアクセスでは、リソースの除外を含むすべてのリソースを対象とするポリシーを、狭い認証フローのセットで適用する方法を強化しています。 この変更後、クライアント アプリケーションが [OIDC](https://learn.microsoft.com/ja-jp/entra/identity-platform/scopes-oidc#openid-connect-scopes) または [固有のディレクトリ スコープ](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#legacy-conditional-access-behavior-when-an-all-resources-policy-has-a-resource-exclusion)のみを要求するユーザー サインインでは、1 つ以上のリソース除外を持つすべてのリソースを対象とする条件付きアクセス ポリシー、または AD Graph Azure明示的にターゲットとするポリシーが適用されます。 これにより、クライアント アプリケーションによって要求されたスコープ セットに関係なく、ポリシーが一貫して適用されます。 詳細については、「[すべてのリソース ポリシーにリソース除外がある場合の新しい条件付きAccess動作](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#new-conditional-access-behavior-when-an-all-resources-policy-has-a-resource-exclusion)を参照してください。
<!-- /MSL-PAGE -->
