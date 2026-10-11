# Microsoft Learn — Microsoft Entra / 開発者向け (Identity Platform・MSAL・Microsoft.Identity.Web) (part 6)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 52

---

<!-- MSL-PAGE {"url":"entra/identity-platform/single-and-multi-tenant-apps"} -->
## Microsoft Entra ID のシングル アプリとマルチテナント アプリ - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/single-and-multi-tenant-apps
- Service: identity-platform
- Article date: 2025-03-13
- Summary: Microsoft Entra ID でのシングルテナント アプリとマルチテナント アプリの機能と違いについて説明します。

Microsoft Entra ID は、ユーザーやアプリなどのオブジェクトを、*テナント*と呼ばれるグループに整理します。 テナントを使用すると、管理者は組織内のユーザーと、組織が所有するアプリに対してポリシーを設定して、セキュリティポリシーと運用ポリシーを満たせるようにすることができます。

### 誰があなたのアプリにサインインできますか？

アプリの開発に関しては、開発者は、アプリの登録時にアプリをシングルテナントまたはマルチテナントに構成することを選択できます。

- シングルテナント アプリは、登録されているテナント (ホーム テナントとも呼ばれます) でのみ使用できます。
- マルチテナント アプリは、ホーム テナントと他のテナントの両方のユーザーが利用できます。

アプリケーションを登録するときは、次のように対象ユーザーを設定することで、アプリケーションをシングルテナントまたはマルチテナントに構成できます。

| 聴衆 | シングル/マルチテナント | サインインできるユーザー |
| --- | --- | --- |
| このディレクトリ内のアカウントのみ | シングル テナント | ディレクトリ内のすべてのユーザー アカウントとゲスト アカウントは、アプリケーションまたは API を使用できます。対象ユーザーが組織の内部にある場合は、このオプションを使用します。  第三者が自社アプリのためにあなた自身のアプリ登録を作成するように求める場合に使用します。 |
| 任意の Microsoft Entra ディレクトリ内のアカウント | マルチテナント | Microsoft の職場または学校アカウントを持つすべてのユーザーとゲストは、アプリケーションまたは API を使用できます。 これには、Microsoft 365 を使用する学校や企業が含まれます。対象ユーザーがビジネスまたは教育機関の顧客である場合は、このオプションを使用します。 |
| Microsoft Entra ディレクトリ内のアカウントと個人の Microsoft アカウント (Skype、Xbox、Outlook.com など) | マルチテナント | 職場または学校、または個人の Microsoft アカウントを持つすべてのユーザーは、アプリケーションまたは API を使用できます。 これには、Microsoft 365 を使用する学校や企業、および Xbox や Skype などのサービスへのサインインに使用される個人アカウントが含まれます。このオプションを使用して、最も幅広い Microsoft アカウントのセットを対象とします。 |

### マルチテナント アプリのベスト プラクティス

IT 管理者がテナントで設定できるさまざまなポリシーの数のため、優れたマルチテナント アプリの構築は困難な場合があります。 マルチテナント アプリを構築する場合は、次のベスト プラクティスに従います。

- 条件付きアクセス ポリシーを構成したテナント [で、アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-conditional-access-dev-guide)をテストします。
- 最小限のユーザー アクセスの原則に従って、アプリが実際に必要とするアクセス許可のみを要求するようにします。
- アプリの一部として公開するすべてのアクセス許可の適切な名前と説明を指定します。 これにより、ユーザーと管理者は、アプリの API を使用しようとしたときに同意する内容を把握できます。 詳細については、[アクセス許可ガイドのベスト プラクティスに関するセクション](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)を参照してください。

手記

マルチテナント アプリケーションは、同じ国内クラウド インスタンスにデプロイできますが、Azure National Cloud にデプロイすることはできません。 例：

- 商用テナントで作成されたマルチテナント アプリケーションは、他の商用テナントに追加できます。
- Azure Government テナントで作成されたマルチテナント アプリケーションは、他の Azure Government テナントに追加できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/single-sign-on-saml-protocol"} -->
## シングル サインオン SAML (Security Assertion Markup Language) プロトコル - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol
- Service: identity-platform
- Article date: 2024-04-08
- Summary: この記事では、Microsoft Entra ID でのシングル サインオン (SSO) SAML プロトコルについて説明します。

この記事では、Microsoft Entra ID がシングル サインオン (SSO) に対してサポートする SAML 2.0 (Security Assertion Markup Language) 認証要求と応答について説明します。

次のプロトコル図では、シングル サインオン シーケンスについて説明します。 クラウド サービス (サービス プロバイダー) は、HTTP リダイレクト バインドを使用して、`AuthnRequest` (認証要求) 要素を Microsoft Entra ID (ID プロバイダー) に渡します。 その後、Microsoft Entra ID は HTTP ポスト バインディングを使用して、`Response` 要素をクラウド サービスに投稿します。

[Image: シングル Sign-On (SSO) ワークフローのスクリーンショット。]

手記

この記事では、シングル サインオンに SAML を使用する方法について説明します。 シングル サインオンを処理するその他の方法 (OpenID Connect または統合 Windows 認証の使用など) の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)のアプリケーションへのシングル サインオン」を参照してください。

### AuthnRequest

ユーザー認証を要求するために、クラウド サービスは microsoft Entra ID に `AuthnRequest` 要素を送信します。 サンプルの SAML 2.0 `AuthnRequest` は、次の例のようになります。

```xml
<samlp:AuthnRequest
  xmlns="urn:oasis:names:tc:SAML:2.0:metadata"
  ID="C2dE3fH4iJ5kL6mN7oP8qR9sT0uV1w"
  Version="2.0" IssueInstant="2013-03-18T03:28:54.1839884Z"
  xmlns:samlp="urn:oasis:names:tc:SAML:2.0:protocol">
  <Issuer xmlns="urn:oasis:names:tc:SAML:2.0:assertion">https://www.contoso.com</Issuer>
</samlp:AuthnRequest>
```

| パラメーター | タイプ | 形容 |
| --- | --- | --- |
| `ID` | 必須 | Microsoft Entra ID では、この属性を使用して、返された応答の `InResponseTo` 属性を設定します。 ID は数値で始まってはいけません。そのため、一般的な方法は、GUID の文字列表現の先頭に "ID" のような文字列を付加することです。 たとえば、`id6c1c178c166d486687be4aaf5e482730` は有効な ID です。 |
| `Version` | 必須 | このパラメーターは `2.0`に設定する必要があります。 |
| `IssueInstant` | 必須 | これは、UTC 値と [ラウンドトリップ形式 ("o")](https://learn.microsoft.com/ja-jp/dotnet/standard/base-types/standard-date-and-time-format-strings)を持つ DateTime 文字列です。 Microsoft Entra ID は、この型の DateTime 値を受け取りますが、値の評価や使用は行いません。 |
| `AssertionConsumerServiceURL` | 随意 | 指定した場合、このパラメーターは Microsoft Entra ID のクラウド サービスの `RedirectUri` と一致する必要があります。 ENtra ID は、SAML 要求に存在する場合は ACS URL を受け入れられます。 |
| `ForceAuthn` | 随意 | これはブール値です。 true の場合は、ユーザーが Microsoft Entra ID を持つ有効なセッションを持っている場合でも、ユーザーは強制的に再認証を強制されることを意味します。 |
| `IsPassive` | 随意 | これは、Microsoft Entra ID が存在する場合にセッション Cookie を使用して、ユーザーの操作なしでユーザーをサイレント認証する必要があるかどうかを指定するブール値です。 これが true の場合、Microsoft Entra ID はセッション Cookie を使用してユーザーの認証を試みます。 |

、、 など、他のすべての  属性は、無視 。

Microsoft Entra ID は、`Conditions`内の `AuthnRequest` 要素も無視します。

#### 発行者

`Issuer` の `AuthnRequest` 要素は、Microsoft Entra ID のクラウド サービスの **ServicePrincipalNames** のいずれかと正確に一致する必要があります。 通常、これは、アプリケーションの登録時に指定された **アプリ ID URI** に設定されます。

`Issuer` 要素を含む SAML 抜粋は、次の例のようになります。

```xml
<Issuer xmlns="urn:oasis:names:tc:SAML:2.0:assertion">https://www.contoso.com</Issuer>
```

#### NameIDPolicy

この要素は、応答で特定の名前 ID 形式を要求し、Microsoft Entra ID に送信 `AuthnRequest` 要素では省略可能です。

`NameIdPolicy` 要素は次の例のようになります。

```xml
<NameIDPolicy Format="urn:oasis:names:tc:SAML:2.0:nameid-format:persistent"/>
```

`NameIDPolicy` が指定されている場合は、省略可能な `Format` 属性を含めることができます。 `Format` 属性には、次のいずれかの値のみを指定できます。その他の値を指定すると、エラーが発生します。

- `urn:oasis:names:tc:SAML:2.0:nameid-format:persistent`: Microsoft Entra ID は、`NameID` 要求をペアワイズ識別子として発行します。
- `urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress`: Microsoft Entra ID は、電子メール アドレス形式で `NameID` 要求を発行します。
- `urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified`: この値により、Microsoft Entra ID が要求形式を選択できます。 Microsoft Entra ID は、`NameID` 要求をペアワイズ識別子として発行します。
- `urn:oasis:names:tc:SAML:2.0:nameid-format:transient`: Microsoft Entra ID は、現在の SSO 操作に固有のランダムに生成された値として、 `NameID` 要求を発行します。 これは、値が一時的であり、認証ユーザーを識別するために使用できないことを意味します。

`SPNameQualifier` が指定されている場合、Microsoft Entra ID には応答に同じ `SPNameQualifier` が含まれます。

Microsoft Entra ID は、`AllowCreate` 属性を無視します。

#### RequestedAuthnContext

`RequestedAuthnContext` 要素は、必要な認証方法を指定します。 Microsoft Entra ID に送信 `AuthnRequest` 要素では省略可能です。

手記

`RequestedAuthnContext` が SAML 要求に含まれている場合は、`Comparison` 要素を `exact`に設定する必要があります。

Microsoft Entra ID では、次の `AuthnContextClassRef` 値がサポートされています。

| 認証方法 | 認証コンテキスト クラス URI |
| --- | --- |
| Kerberos | urn:oasis:names:tc:SAML:2.0:ac:classes:Kerberos |
| ユーザー名とパスワード | urn:oasis:names:tc:SAML:2.0:ac:classes:Password (SAML 2.0のパスワードクラス) |
| PGP 公開キー インフラストラクチャ | urn:oasis:names:tc:SAML:2.0:ac:classes:PGP |
| リモート パスワードをセキュリティで保護する | urn:oasis:names:tc:SAML:2.0:ac:classes:SecureRemotePassword |
| XML デジタル署名 | urn:oasis:names:tc:SAML:2.0:ac:classes:XMLDSig |
| 単純な公開キー インフラストラクチャ | urn:oasis:names:tc:SAML:2.0:ac:classes:SPKI |
| スマートカード | urn:oasis:names:tc:SAML:2.0:ac:classes:Smartcard |
| 囲まれた秘密キーと PIN を含むスマートカード | urn:oasis:names:tc:SAML:2.0:ac:classes:SmartcardPKI |
| トランスポート層セキュリティ (TLS) クライアント | urn:oasis:names:tc:SAML:2.0:ac:classes:TLSClient |
| 未指定 | urn:oasis:names:tc:SAML:2.0:ac:classes:Unspecified |
| X.509 証明書 | urn:oasis:names:tc:SAML:2.0:ac:classes:X509 |
| 統合 Windows 認証 | urn:federation:authentication:windows |

#### スコープ

id プロバイダーの一覧を含む `Scoping` 要素は、Microsoft Entra ID に送信される要素 `AuthnRequest` 省略可能です。

指定した場合は、サポートされていないため、`ProxyCount` 属性、`IDPListOption`、または `RequesterID` 要素は含めないでください。

#### 署名

`Signature` 要素の `AuthnRequest` 要素は省略可能です。 Microsoft Entra ID は、署名された認証要求の要件を適用するように構成できます。 有効にすると、署名された認証要求のみが受け入れられます。それ以外の場合、要求者の検証は、登録済みの Assertion Consumer Service URL にのみ応答することによって提供されます。

#### 件名

`Subject` 要素は含めないでください。 Microsoft Entra ID では、`AuthnRequest` でのサブジェクトの指定はサポートされておらず、指定された場合はエラーが返されます。

代わりに、パラメーター値としてサブジェクトの NameID を使用して、シングル サインオン URL に `login_hint` パラメーターを HTTP 要求に追加することで、サブジェクトを指定できます。

### 応答

要求されたサインオンが正常に完了すると、Microsoft Entra ID はクラウド サービスに応答を投稿します。 サインオン試行が成功した場合の応答は、次の例のようになります。

```xml
<samlp:Response ID="_a4958bfd-e107-4e67-b06d-0d85ade2e76a" Version="2.0" IssueInstant="2013-03-18T07:38:15.144Z" Destination="https://contoso.com/identity/inboundsso.aspx" InResponseTo="C2dE3fH4iJ5kL6mN7oP8qR9sT0uV1w" xmlns:samlp="urn:oasis:names:tc:SAML:2.0:protocol">
  <Issuer xmlns="urn:oasis:names:tc:SAML:2.0:assertion"> https://login.microsoftonline.com/aaaabbbb-0000-cccc-1111-dddd2222eeee/</Issuer>
  <SignatureValue xmlns:ds="https://www.w3.org/2000/09/xmldsig#">
    ...
  </SignatureValue>
  <samlp:Status>
    <samlp:StatusCode Value="urn:oasis:names:tc:SAML:2.0:status:Success" />
  </samlp:Status>
  <Assertion ID="_bf9c623d-cc20-407a-9a59-c2d0aee84d12" IssueInstant="2013-03-18T07:38:15.144Z" Version="2.0" xmlns="urn:oasis:names:tc:SAML:2.0:assertion">
    <Issuer>https://login.microsoftonline.com/aaaabbbb-0000-cccc-1111-dddd2222eeee/</Issuer>
    <SignatureValue xmlns:ds="https://www.w3.org/2000/09/xmldsig#">
      ...
    </SignatureValue>
    <Subject>
      <NameID>Uz2Pqz1X7pxe4XLWxV9KJQ+n59d573SepSAkuYKSde8=</NameID>
      <SubjectConfirmation Method="urn:oasis:names:tc:SAML:2.0:cm:bearer">
        <SubjectConfirmationData InResponseTo="id758d0ef385634593a77bdf7e632984b6" NotOnOrAfter="2013-03-18T07:43:15.144Z" Recipient="https://contoso.com/identity/inboundsso.aspx" />
      </SubjectConfirmation>
    </Subject>
    <Conditions NotBefore="2013-03-18T07:38:15.128Z" NotOnOrAfter="2013-03-18T08:48:15.128Z">
      <AudienceRestriction>
        <Audience>https://www.contoso.com</Audience>
      </AudienceRestriction>
    </Conditions>
    <AttributeStatement>
      <Attribute Name="http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name">
        <AttributeValue>testuser@contoso.com</AttributeValue>
      </Attribute>
      <Attribute Name="http://schemas.microsoft.com/identity/claims/objectidentifier">
        <AttributeValue>3F2504E0-4F89-11D3-9A0C-0305E82C3301</AttributeValue>
      </Attribute>
      <Attribute Name="http://schemas.microsoft.com/claims/authnmethodsreferences">    <AttributeValue>http://schemas.microsoft.com/ws/2008/06/identity/authenticationmethod/password</AttributeValue>
        <AttributeValue>http://schemas.microsoft.com/claims/multipleauthn</AttributeValue>
      </Attribute>
      ...
    </AttributeStatement>
    <AuthnStatement AuthnInstant="2013-03-18T07:33:56.000Z" SessionIndex="_bf9c623d-cc20-407a-9a59-c2d0aee84d12">
      <AuthnContext>
        <AuthnContextClassRef>urn:oasis:names:tc:SAML:2.0:ac:classes:Password</AuthnContextClassRef>
      </AuthnContext>
    </AuthnStatement>
  </Assertion>
</samlp:Response>
```

#### 応答

`Response` 要素には、承認要求の結果が含まれます。 Microsoft Entra ID は、`ID` 要素の `Version`、`IssueInstant`、および `Response` 値を設定します。 また、次の属性も設定します。

- `Destination`: サインオンが正常に完了すると、サービス プロバイダー (クラウド サービス) の `RedirectUri` に設定されます。
- `InResponseTo`: 応答を開始した `ID` 要素の `AuthnRequest` 属性に設定されます。

#### 発行者

Microsoft Entra ID は、`Issuer` 要素を `https://sts.windows.net/<TenantIDGUID>/` に設定します。ここで、`<TenantIDGUID>` は Microsoft Entra テナントのテナント ID です。

たとえば、Issuer 要素を持つ応答は、次の例のようになります。

```xml
<Issuer xmlns="urn:oasis:names:tc:SAML:2.0:assertion"> https://sts.windows.net/aaaabbbb-0000-cccc-1111-dddd2222eeee/</Issuer>
```

#### 地位

`Status` 要素は、サインオンの成功または失敗を伝えます。 `StatusCode` 要素が含まれます。この要素には、要求の状態を表すコードまたは入れ子になったコードのセットが含まれます。 また、`StatusMessage` 要素も含まれます。これには、サインオン プロセス中に生成されるカスタム エラー メッセージが含まれます。

次の例は、失敗したサインオン試行に対する SAML 応答です。

```xml
<samlp:Response ID="_f0961a83-d071-4be5-a18c-9ae7b22987a4" Version="2.0" IssueInstant="2013-03-18T08:49:24.405Z" InResponseTo="iddce91f96e56747b5ace6d2e2aa9d4f8c" xmlns:samlp="urn:oasis:names:tc:SAML:2.0:protocol">
  <Issuer xmlns="urn:oasis:names:tc:SAML:2.0:assertion">https://sts.windows.net/aaaabbbb-0000-cccc-1111-dddd2222eeee/</Issuer>
  <samlp:Status>
    <samlp:StatusCode Value="urn:oasis:names:tc:SAML:2.0:status:Requester">
      <samlp:StatusCode Value="urn:oasis:names:tc:SAML:2.0:status:RequestUnsupported" />
    </samlp:StatusCode>
    <samlp:StatusMessage>AADSTS75006: An error occurred while processing a SAML2 Authentication request. AADSTS90011: The SAML authentication request property 'NameIdentifierPolicy/SPNameQualifier' is not supported.
    Trace ID: 0000aaaa-11bb-cccc-dd22-eeeeee333333
    Timestamp: 2013-03-18 08:49:24Z</samlp:StatusMessage>
    </samlp:Status>
</samlp:Response>
```

#### 主張

Microsoft Entra ID は、`ID`、`IssueInstant`、および `Version`に加えて、応答の `Assertion` 要素に次の要素を設定します。

##### 発行者

これは、`https://sts.windows.net/<TenantIDGUID>/` が Microsoft Entra テナントのテナント ID である `<TenantIDGUID>`に設定されます。

```xml
<Issuer>https://sts.windows.net/aaaabbbb-0000-cccc-1111-dddd2222eeee/</Issuer>
```

##### 署名

Microsoft Entra ID は、サインオンが成功した場合に応答してアサーションに署名します。 `Signature` 要素には、クラウド サービスがアサーションの整合性を検証するためにソースを認証するために使用できるデジタル署名が含まれています。

このデジタル署名を生成するために、Microsoft Entra ID はメタデータ ドキュメントの `IDPSSODescriptor` 要素の署名キーを使用します。

```xml
<SignatureValue xmlns:ds="https://www.w3.org/2000/09/xmldsig#">
  digital_signature_here
</SignatureValue>
```

##### 件名

これは、アサーション内のステートメントの対象となる原則を指定します。 これには、認証されたユーザーを表す `NameID` 要素が含まれています。 `NameID` 値は、トークンの対象ユーザーであるサービス プロバイダーにのみ送信されるターゲット識別子です。 永続的です。取り消すことはできますが、再割り当てされることはありません。 また、ユーザーについて何も明らかにせず、属性クエリの識別子として使用できないという点でも不透明です。

`Method` 要素の `SubjectConfirmation` 属性は常に `urn:oasis:names:tc:SAML:2.0:cm:bearer`に設定されます。

```xml
<Subject>
  <NameID>Uz2Pqz1X7pxe4XLWxV9KJQ+n59d573SepSAkuYKSde8=</NameID>
  <SubjectConfirmation Method="urn:oasis:names:tc:SAML:2.0:cm:bearer">
    <SubjectConfirmationData InResponseTo="id758d0ef385634593a77bdf7e632984b6" NotOnOrAfter="2013-03-18T07:43:15.144Z" Recipient="https://contoso.com/identity/inboundsso.aspx" />
  </SubjectConfirmation>
</Subject>
```

##### 条件

この要素は、SAML アサーションの許容される使用を定義する条件を指定します。

```xml
<Conditions NotBefore="2013-03-18T07:38:15.128Z" NotOnOrAfter="2013-03-18T08:48:15.128Z">
  <AudienceRestriction>
    <Audience>https://www.contoso.com</Audience>
  </AudienceRestriction>
</Conditions>
```

`NotBefore` 属性と `NotOnOrAfter` 属性は、アサーションが有効な間隔を指定します。

- `NotBefore` 属性の値は、`IssueInstant` 要素の属性の値より少し遅い (1 秒未満) `Assertion`。 Microsoft Entra ID は、それ自体とクラウド サービス (サービス プロバイダー) の時間差を考慮せず、現時点ではバッファーを追加しません。
- `NotOnOrAfter` 属性の値は、`NotBefore` 属性の値より 70 分後です。

##### 聴衆

これには、対象ユーザーを識別する URI が含まれています。 Microsoft Entra ID は、この要素の値を、サインオンを開始した `Issuer` の `AuthnRequest` 要素の値に設定します。 `Audience` 値を評価するには、アプリケーションの登録時に指定された `App ID URI` の値を使用します。

```xml
<AudienceRestriction>
  <Audience>https://www.contoso.com</Audience>
</AudienceRestriction>
```

`Issuer` の値と同様に、`Audience` 値は、Microsoft Entra ID のクラウド サービスを表すサービス プリンシパル名のいずれかと正確に一致する必要があります。 ただし、`Issuer`要素の値が URI 値でない場合、応答の`Audience`値は、`Issuer`でプレフィックスが付いた`spn:`値になります。

##### AttributeStatement

これには、サブジェクトまたはユーザーに関する要求が含まれます。 次の抜粋には、`AttributeStatement` 要素のサンプルが含まれています。 省略記号は、要素に複数の属性と属性値を含めることができることを示します。

```xml
<AttributeStatement>
  <Attribute Name="http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name">
    <AttributeValue>testuser@contoso.com</AttributeValue>
  </Attribute>
  <Attribute Name="http://schemas.microsoft.com/identity/claims/objectidentifier">
    <AttributeValue>3F2504E0-4F89-11D3-9A0C-0305E82C3301</AttributeValue>
  </Attribute>
  ...
</AttributeStatement>
```

- **名前要求** - `Name` 属性 (`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name`) の値は、認証されたユーザーのユーザー プリンシパル名 (`testuser@managedtenant.com`など) です。
- **ObjectIdentifier 要求** - `ObjectIdentifier` 属性 (`http://schemas.microsoft.com/identity/claims/objectidentifier`) の値は、Microsoft Entra ID で認証されたユーザーを表すディレクトリ オブジェクトの `ObjectId` です。 `ObjectId` は、変更できないグローバルに一意であり、認証されたユーザーの安全な識別子を再利用します。

##### AuthnStatement

この要素は、アサーションサブジェクトが特定の時点で特定の手段によって認証されたことをアサートします。

- `AuthnInstant` 属性は、ユーザーが Microsoft Entra ID で認証された時刻を指定します。
- `AuthnContext` 要素は、ユーザーの認証に使用される認証コンテキストを指定します。

```xml
<AuthnStatement AuthnInstant="2013-03-18T07:33:56.000Z" SessionIndex="_bf9c623d-cc20-407a-9a59-c2d0aee84d12">
  <AuthnContext>
    <AuthnContextClassRef>urn:oasis:names:tc:SAML:2.0:ac:classes:Password</AuthnContextClassRef>
  </AuthnContext>
</AuthnStatement>
```

`AuthnContextClassRef`値には、ユーザーが認証に使用した方法が反映されます。 ユーザーが複数の方法で認証した場合、最も強力な方法が `AuthnContextClassRef`に反映されます。 次の表に、認証方法ごとに送信Microsoft Entra ID`AuthnContextClassRef`クラス名を示します。 完全な値は `urn:oasis:names:tc:SAML:2.0:ac:classes:<className>`。

| Microsoft Entra の認証方法 | `AuthnContextClassRef`クラス名 | 形容 |
| --- | --- | --- |
| パスワード | `Password` | ユーザーがユーザー名とパスワードで認証されました。 |
| Microsoft Authenticator プッシュ | `MobileOneFactorUnregistered`、または別の要素も完了したときに`MobileTwoFactorContract` | Microsoft Authenticatorでのプッシュ通知の承認。 メソッドが MFA に貢献すると、2 要素クラスが送信されます。 |
| TOTP のMicrosoft Authenticator | `TimeSyncToken` | Microsoft Authenticatorによって生成された時間ベースのワンタイム パスコード (TOTP)。 |
| ハードウェア OATH トークン | `TimeSyncToken` | ハードウェア OATH トークンによって生成される時間ベースのワンタイム パスコード。 |
| 電話によるサインイン (パスワードレス認証) | `MobileTwoFactorContract` | Microsoft Authenticatorで承認されたパスワードなしの電話によるサインイン。 |
| SMS | `MobileOneFactorUnregistered`、または別の要素も完了したときに`MobileTwoFactorContract` | テキスト メッセージによって配信されるワンタイム パスコード。 |
| 通話 | `Telephony`、または別の要素も完了したときに`MobileTwoFactorContract` | 音声通話による承認。 |
| Email | `MobileOneFactorUnregistered`、または別の要素も完了したときに`MobileTwoFactorContract` | 電子メールで配信されるワンタイム パスコード。 |
| FIDO2 セキュリティ キー (フィッシングに強い MFA) | `SmartcardPKI` | FIDO2 セキュリティ キー。秘密キーと PIN を持つスマートカードベースの証明書として報告されます。 |
| Passkey - デバイス バインド (フィッシングに強い MFA) | `SmartcardPKI` | デバイス バインドパスキー。 |
| Passkey - 同期済み (フィッシング詐欺に強い MFA) | `SoftwarePKI` | 同期されたパスキー。ソフトウェア ベースの PKI 資格情報として報告されます。 |
| Windows Hello for Business (フィッシング詐欺に強い MFA) | `SmartcardPKI` | Windows Hello for Business。 |
| 証明書ベースの認証 (多要素 CBA のフィッシングに対する耐性のある MFA) | `SmartcardPKI`MFA として使用する場合。単一要素 CBA の`X509` | 証明書ベースの認証 (CBA)。 |
| 一時アクセス パス (TAP) | `Unspecified` | 一時的なアクセス パス。 |
| Windows統合認証 (Kerberos) | `Kerberos` | 統合認証をWindowsします。 |
| デバイス ベースの X509 認証 | `X509` | デバイス上の証明書がデバイスの ID を証明する |

##### authnmethodreferences

この要素は、アサーションサブジェクトが特定の時点で特定の手段によって認証されたことをアサートします。 これは、アプリケーションがパスワードを使用するか、MFA や Passkeys などのより強力な認証方法を使用して認証を行ったことをアプリケーションが使用して確認するための要求セクションで使用できます。

- `authnmethodsreferences`属性は、ユーザーが Microsoft Entra ID を使用して認証する方法を指定します。
- `http://schemas.microsoft.com/ws/2008/06/identity/authenticationmethod/password`要求値は、ユーザーが Entra ID を使用してユーザー名とパスワードの認証を行ったことを指定します。
- `http://schemas.microsoft.com/claims/multipleauthn`要求値は、ユーザーがユーザー名とパスワードを実行し、MFA の結果として複数要素認証を実行したことを示します。

```xml
  <Attribute Name="http://schemas.microsoft.com/claims/authnmethodsreferences">
              <AttributeValue>http://schemas.microsoft.com/ws/2008/06/identity/authenticationmethod/password</AttributeValue>
              <AttributeValue>http://schemas.microsoft.com/claims/multipleauthn</AttributeValue>
  </Attribute>

```

次の表に、認証方法ごとに送信Microsoft Entra ID`authnmethodsreferences`値を示します。 各値は、`http://schemas.microsoft.com/ws/2008/06/identity/authenticationmethod/<value>`として出力される`multipleauthn`を除き、`http://schemas.microsoft.com/claims/multipleauthn`名前空間の下に出力されます。 `multipleauthn`値は、ユーザーが多要素認証 (MFA) を完了した場合にのみ含まれます。

| Microsoft Entra の認証方法 | `authnmethodsreferences`値 | 形容 |
| --- | --- | --- |
| パスワード | `password` | ユーザーがユーザー名とパスワードで認証されました。 |
| Microsoft Authenticator プッシュ | `rsa`に加えて、MFA が別の要素で完了した場合の`multipleauthn` | Microsoft Authenticatorでのプッシュ通知の承認。 |
| TOTP のMicrosoft Authenticator | `otp`に加えて、MFA が別の要素で完了した場合の`multipleauthn` | Microsoft Authenticatorからの時間ベースのワンタイム パスコード (TOTP)。 |
| ハードウェア OATH トークン | `otp`に加えて、MFA が別の要素で完了した場合の`multipleauthn` | ハードウェア OATH トークンからのワンタイム パスコード。 |
| 電話によるサインイン (パスワードレス認証) | `swk`、`multipleauthn` | パスワードなしの電話によるサインイン。ソフトウェア キーとして報告されます。 |
| SMS | `otp`に加えて、MFA が別の要素で完了した場合の`multipleauthn` | テキスト メッセージによって配信されるワンタイム パスコード。 |
| 通話 | `otp`に加えて、MFA が別の要素で完了した場合の`multipleauthn` | 音声通話による承認。 |
| Email | `otp`に加えて、MFA が別の要素で完了した場合の`multipleauthn` | 電子メールで配信されるワンタイム パスコード。 |
| FIDO2 セキュリティ キー (フィッシングに強い MFA) | `fido`、`multipleauthn` | FIDO2 セキュリティ キー。 |
| Passkey - デバイス バインド (フィッシングに強い MFA) | `fido`、`multipleauthn` | デバイス バインドパスキー。 |
| Passkey - 同期済み (フィッシング詐欺に強い MFA) | `fido`、`multipleauthn` | 同期されたパスキー。 |
| Windows Hello for Business (フィッシング詐欺に強い MFA) | `hwk`、`multipleauthn` | Windows Hello for Business、ハードウェア バインド キーとして報告されます。 |
| 証明書ベースの認証 (多要素 CBA のフィッシングに対する耐性のある MFA) | `x509`、 `multipleauthn` (多要素 CBA の既定値、または単一要素 CBA の別の要素で MFA が完了した場合) | 証明書ベースの認証。 |
| 一時アクセス パス (TAP) | `otp`、`multipleauthn` | 一時的なアクセス パス。 |
| Windows統合認証 (Kerberos) | `wia` | 統合認証をWindowsします。 |
| デバイス ベースの X509 認証 | `x509` | デバイス上の証明書がデバイスの ID を証明する |

Microsoftには、単一要素 Certificate-Based 認証 (CBA) とデバイス ベースの X.509 認証の両方の`amr`要求に`x509`が含まれています。 ただし、x509 だけが存在しても、フィッシングに強い MFA (PRMFA) とは見なされません。 PRMFA 要件を満たすために、ユーザーは追加の MFA 要素を完了する必要もあります。これは、認証コンテキストの他の認証方法インジケーターによって反映されます。

手記

`amr`要求は Salesforce アプリケーションに対して既定で送信されるため、これらのアプリの構成変更は必要ありません。 他のすべての SAML アプリケーションの場合、アプリケーション管理者は、AMR 要求を要求するために、オプションの `amr` 要求と `include_granular_amr` 追加プロパティをアプリ登録に追加する必要があります。 `multipleauthn`と`mfa`の値は、ユーザーが MFA を完了したときにのみ出力されます。

手記

Microsoft Entra IDは、SAML と OIDC v2.0 の両方のトークンの`otp`を置き換えるより詳細な AMR 値をロールアウトしています。MICROSOFT AUTHENTICATOR TOTP は`totp`を送信し、ハードウェア OATH トークンは`hotp`を送信し、SMS は`sms`を送信し、電話呼び出しは`tel`を送信し、電子メール OTP は`emailotp`を送信し、一時アクセス パスは`tap`を送信します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/single-sign-out-saml-protocol"} -->
## Azure シングル サインアウト SAML プロトコル - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-out-saml-protocol
- Service: identity-platform
- Article date: 2024-04-10
- Summary: この記事では、Microsoft Entra ID の単一 Sign-Out SAML プロトコルについて説明します

Microsoft Entra ID では、SAML 2.0 Web ブラウザーのシングル サインアウト プロファイルがサポートされています。 シングル サインアウトを正しく機能させるには、アプリケーションの登録時に、アプリケーションの **LogoutURL** を Microsoft Entra ID で明示的に登録する必要があります。

アプリが [Azure アプリ ギャラリーに追加された](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing) 場合、この値は既定で設定できます。 それ以外の場合は、Microsoft Entra テナントにアプリを追加するユーザーが値を決定して設定する必要があります。 Microsoft Entra ID では、ログアウト後に **LogoutURL** を使用してユーザーをリダイレクトします。Microsoft Entra ID は、HTTP POST バインドではなく、リダイレクト バインド (HTTP GET) をサポートしています。

次の図は、Microsoft Entra シングル サインアウト プロセスのワークフローを示しています。

[Image: Microsoft Entra シングル サインアウト ワークフローのスクリーンショット。]

### LogoutRequest

クラウド サービスは、セッションが終了したことを示す `LogoutRequest` メッセージを Microsoft Entra ID に送信します。 次の抜粋は、 `LogoutRequest` 要素のサンプルを示しています。

```
<samlp:LogoutRequest xmlns="urn:oasis:names:tc:SAML:2.0:metadata" ID="idaa6ebe6839094fe4abc4ebd5281ec780" Version="2.0" IssueInstant="2013-03-28T07:10:49.6004822Z" xmlns:samlp="urn:oasis:names:tc:SAML:2.0:protocol">
  <Issuer xmlns="urn:oasis:names:tc:SAML:2.0:assertion">https://www.workaad.com</Issuer>
  <NameID xmlns="urn:oasis:names:tc:SAML:2.0:assertion"> Uz2Pqz1X7pxe4XLWxV9KJQ+n59d573SepSAkuYKSde8=</NameID>
</samlp:LogoutRequest>
```

Microsoft Entra ID に送信される `LogoutRequest` 要素には、次の属性が必要です。

- `ID` - サインアウト要求を識別します。 `ID`の値は数値で始めることはできません。 一般的な方法は、GUID の文字列表現に **ID を** 追加することです。
- `Version` - この要素の値を **2.0** に設定します。 この値は必須です。
- `IssueInstant`- これは、世界協定時刻 (UTC) 値と`DateTime`持つ文字列です。 Microsoft Entra ID はこの型の値を受け取りますが、適用されません。

[SAML 2.0 コア仕様のセクション 3.7](http://docs.oasis-open.org/security/saml/v2.0/saml-core-2.0-os.pdf) では、アプリケーション以外にセッションに複数の参加者 (他のアプリケーション) が存在する可能性があります。 他の参加者の 1 人が Microsoft ID プラットフォーム (セッション機関) に`LogoutRequest`を送信すると、最初の`LogoutRequest`を送信した参加者を除くすべてのセッション参加者に`LogoutRequest`が送信されます。 別の参加者が同時にサインアウトを開始した場合、最初に Microsoft ID プラットフォームに到達する `LogoutRequest` を確認する競合が発生します。 そのため、アプリケーションは常に `LogoutRequest`を処理するように準備する必要があります。

#### 発行者

`Issuer`の`LogoutRequest`要素は、Microsoft Entra ID のクラウド サービスの **ServicePrincipalNames** のいずれかと正確に一致する必要があります。 通常、これは、アプリケーションの登録時に指定された **アプリ ID URI** に設定されます。

#### NameID

`NameID`要素の値は、サインアウトするユーザーの`NameID`と正確に一致する必要があります。

注

SAML ログアウト要求中、 `NameID` 値は Microsoft Entra ID では考慮されません。 1 人のユーザー セッションがアクティブな場合、Microsoft Entra ID によってそのセッションが自動的に選択され、SAML ログアウトが続行されます。 複数のユーザー セッションがアクティブな場合、Microsoft Entra ID はユーザー選択のアクティブなセッションを列挙します。 ユーザーを選択すると、SAML ログアウトが続行されます。

### LogoutResponse

Microsoft Entra ID は、`LogoutResponse`要素に応答して`LogoutRequest`を送信します。 次の抜粋は、サンプルの `LogoutResponse`を示しています。

```
<samlp:LogoutResponse ID="_f0961a83-d071-4be5-a18c-9ae7b22987a4" Version="2.0" IssueInstant="2013-03-18T08:49:24.405Z" InResponseTo="iddce91f96e56747b5ace6d2e2aa9d4f8c" xmlns:samlp="urn:oasis:names:tc:SAML:2.0:protocol">
  <Issuer xmlns="urn:oasis:names:tc:SAML:2.0:assertion">https://login.microsoftonline.com/aaaabbbb-0000-cccc-1111-dddd2222eeee/</Issuer>
  <samlp:Status>
    <samlp:StatusCode Value="urn:oasis:names:tc:SAML:2.0:status:Success" />
  </samlp:Status>
</samlp:LogoutResponse>

```

Microsoft Entra ID は、`ID` 要素の `Version`、`IssueInstant`、および `LogoutResponse` 値を設定します。 また、応答を引き出した`InResponseTo`の`ID`属性の値に`LogoutRequest`要素を設定します。

#### 発行者

Microsoft Entra ID は、`https://login.microsoftonline.com/<TenantIdGUID>/`TenantIdGUID&lt; が Microsoft Entra テナントのテナント ID である&gt;にこの値を設定します。

発行者要素を正しく識別するには、サンプル LogoutResponse に示すように `https://login.microsoftonline.com/<TenantIdGUID>/` 値を使用します。 この URL 形式は、Microsoft Entra テナントを発行者として識別し、応答の発行を担当する機関を表します。

#### ステータス

Microsoft Entra ID は、`StatusCode` 要素の`Status`要素を使用して、サインアウトの成功または失敗を示します。サインアウト試行が失敗した場合、`StatusCode`要素にはカスタム エラー メッセージを含めることもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/support-fido2-authentication"} -->
## 開発したアプリでFIDO2キーを使用したパスワードレス認証をサポート - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/support-fido2-authentication
- Service: identity-platform
- Article date: 2021-01-29
- Summary: このデプロイメント・ガイドでは、開発するアプリケーションでFIDO2セキュリティ・キーを使用したパスワードレス認証をサポートする方法について説明します

これらの構成とベスト プラクティスは、アプリケーションのユーザーが [FIDO2 パスワードレス認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2) を利用できないようにする一般的なシナリオを回避するのに役立ちます。

### 一般的なベスト プラクティス

#### ドメイン ヒント

ドメイン ヒントを使用して [ホーム領域の検出](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-authentication-for-federated-users-portal)をバイパスしないでください。 この機能は、サインインをより効率化することを目的としていますが、フェデレーション ID プロバイダーがパスワードレス認証をサポートしていない場合があります。

#### 特定の資格情報の要求

SAML を使用している場合は、 [RequestedAuthnContext 要素を使用して](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol#requestedauthncontext)パスワードが必要であることを指定しないでください。

RequestedAuthnContext 要素はオプションであるため、この問題を解決するために、SAML 認証リクエストから削除できます。 この要素を使用すると、多要素認証などの他の認証オプションが正しく機能しなくなる可能性があるため、これは一般的なベスト プラクティスです。

#### 最近使用した認証方法を使用する

ユーザーが最後に使用したサインイン方法が最初に表示されます。 これにより、ユーザーが最初に提示されたオプションを使用する必要があると信じている場合に混乱を招く可能性があります。 ただし、以下に示すように「その他のサインイン方法」を選択することで、別のオプションを選択できます。

[Image: ユーザーが認証方法を変更できるボタンを強調表示するユーザー認証エクスペリエンスの画像。]

### プラットフォーム固有のベストプラクティス

#### ウィンドウズ

認証を実装するための推奨オプションは、次の順序で行います。

- Microsoft Authentication Library (MSAL) を使用している .NET デスクトップ アプリケーションでは、Windows 認証マネージャー (WAM) を使用する必要があります。 この統合とその利点は、 [GitHub に記載されています](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/wiki/wam)。
- [WebView2](https://learn.microsoft.com/ja-jp/microsoft-edge/webview2/) を使用して、埋め込みブラウザーで FIDO2 をサポートします。
- システムブラウザを使用します。 デスクトップ プラットフォーム用の MSAL ライブラリでは、既定でこの方法が使用されます。 FIDO2 ブラウザーの互換性に関するページを参照して、使用するブラウザーが FIDO2 認証をサポートしていることを確認できます。

#### Android

FIDO2 は、 [承認ユーザー エージェント](https://learn.microsoft.com/ja-jp/entra/msal/android/msal-configuration#authorization_user_agent) またはブローカー統合として BROWSER と共に MSAL を使用する Android アプリでサポートされています。 ブローカーは、Microsoft Authenticator、ポータル サイト、または Android の Windows アプリへのリンクで出荷されます。

MSAL を使用していない場合でも、認証にはシステムの Web ブラウザーを使用する必要があります。 SSO や条件付きアクセスなどの機能は、システムの Web ブラウザーによって提供される共有 Web サーフェスに依存します。

#### iOS と macOS

FIDO2 は、ASWebAuthenticationSession またはブローカー統合で MSAL を使用する iOS アプリでサポートされています。 ブローカーは、iOS の Microsoft Authenticator と macOS の Microsoft Intune ポータル サイトで出荷されます。

ネットワークプロキシが、Appleによる関連付けられたドメイン検証をブロックしていないことを確認してください。 FIDO2認証を成功させるには、Appleの関連ドメイン検証が必要であり、そのためには特定のAppleドメインをネットワークプロキシから除外する必要があります。 詳しくは、 [エンタープライズネットワークでApple製品を使用する](https://support.apple.com/HT210060)を参照してください。

MSAL を使用していない場合でも、認証にはシステムの Web ブラウザーを使用する必要があります。 SSO や条件付きアクセスなどの機能は、システムの Web ブラウザーによって提供される共有 Web サーフェスに依存します。 詳細については、「 [Web サービスによるユーザーの認証 |Apple 開発者向けドキュメント](https://developer.apple.com/documentation/authenticationservices/authenticating_a_user_through_a_web_service)。

#### Web アプリとシングルページ アプリ

Webブラウザで実行されるアプリケーションでFIDO2パスワードレス認証を使用できるかどうかは、ブラウザとプラットフォームの組み合わせによって異なります。 [FIDO2 互換性マトリクス](https://learn.microsoft.com/ja-jp/entra/identity/authentication/fido2-compatibility)を参照して、ユーザーが遭遇する組み合わせがサポートされているかどうかを確認できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/supported-accounts-validation"} -->
## サポートされているアカウントの種類別の検証の相違点 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/supported-accounts-validation
- Service: identity-platform
- Article date: 2026-09-25
- Summary: Microsoft ID プラットフォームにアプリを登録する際にサポートされる、アカウントの種類のさまざまなプロパティの検証の違いについて説明します。

開発者用 Microsoft ID プラットフォームにアプリケーションを登録するとき、アプリケーションでサポートしているアカウントの種類を選択するよう求められます。 登録プロセス中に、**[サポートされているアカウントの種類]** の **[選択に関するヘルプ]** リンクを参照できます。 このプロパティで選択した値は、他のアプリ オブジェクトのプロパティに影響します。

アプリケーションが登録されたら、アプリケーションでサポートするアカウントの種類をいつでも確認または変更できます。 アプリケーションの **[管理]** ペインで、**[マニフェスト]** を検索し、`signInAudience` 値を見つけます。 さまざまなアカウントの種類と対応する `signInAudience` を次の表に示します。

| サポートされているアカウントの種類 (アプリケーションの登録) | `signInAudience`(マニフェスト) |
| --- | --- |
| この組織ディレクトリのみに含まれるアカウント (シングル テナント) | `AzureADMyOrg` |
| 任意の組織ディレクトリ (Microsoft Entra ディレクトリ - マルチテナント) 内のアカウント | `AzureADMultipleOrgs` |
| 任意の組織のディレクトリ (Microsoft Entra ディレクトリ - マルチテナント) 内のアカウントと、個人用の Microsoft アカウント (Skype、Xbox など) | `AzureADandPersonalMicrosoftAccount` |
| 個人用 Microsoft アカウントのみ | `PersonalMicrosoftAccount` |

このプロパティを変更する場合は、最初に他のプロパティの変更が必要になることがあります。

### 検証の相違点

サポートされているさまざまな種類のアカウントで異なるプロパティの検証方法の違いについては、次の表を参照してください。

| プロパティ | `AzureADMyOrg` | `AzureADMultipleOrgs` | `AzureADandPersonalMicrosoftAccount`および`PersonalMicrosoftAccount` |
| --- | --- | --- | --- |
| アプリケーション ID URI (`identifierURIs`) | テナント内で一意であること `urn://`スキームはサポートされています  ワイルドカードはサポートしていません  クエリ文字列とフラグメントはサポートされています  最大長は 255 文字です  IdentifierURI の数に制限はありません\* | グローバルに一意であること `urn://`スキームはサポートされています  ワイルドカードはサポートしていません  クエリ文字列とフラグメントはサポートされています  最大長は 255 文字です  IdentifierURI の数に制限はありません\* | グローバルに一意であること `urn://` スキームはサポートしていません  ワイルドカード、フラグメント、クエリ文字列はサポートしていません  最大長は 120 文字です  IdentifierURI の最大数は 50 です |
| 各国のクラウド | サポートされています | サポートされています | サポートされていません |
| 証明書 (`keyCredentials`) | 対称署名キー | 対称署名キー | 暗号化と非対称署名キー |
| クライアント シークレット (`passwordCredentials`) | 制限なし\* | 制限なし\* | クライアント シークレットの最大数は 2 です |
| リダイレクト URI (`replyURLs`) | 詳細については、[リダイレクト URI および応答 URL に関する制約と制限](https://learn.microsoft.com/ja-jp/entra/identity-platform/reply-url)を参照してください。 |  |  |
| API のアクセス許可 (`requiredResourceAccess`) | API (リソース アプリ) の数は合計 50 個以下で、その内、他のテナントからの API は 10 個以下です。 すべての API 全体で、アクセス許可の合計は 400 個以下です。 | API (リソース アプリ) の数は合計 50 個以下で、その内、他のテナントからの API は 10 個以下です。 すべての API 全体で、アクセス許可の合計は 400 個以下です。 | API (リソース アプリ) の数は合計 50 個以下で、その内、他のテナントからの API は 10 個以下です。 すべての API 全体で、アクセス許可の合計は 200 個以下です。 リソース (例: Microsoft Graph) あたりのアクセス許可数は、最大 30 個です。 |
| この API で定義されるスコープ (`oauth2Permissions`) | スコープ名の最大長は 120 文字です  アプリ ロールと共有されるアクセス許可定義の既定の制限は 700 です。 「 [アプリ ロールの制限」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-role-limits)参照してください。 | スコープ名の最大長は 120 文字です  アプリ ロールと共有されるアクセス許可定義の既定の制限は 700 です。 「 [アプリ ロールの制限」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-role-limits)参照してください。 | スコープ名の最大長は 40 文字です  最大 100 個のスコープが定義され、共有 [アプリロールの制限](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-role-limits)にも従います。 |
| 承認されたクライアント アプリケーション (`preAuthorizedApplications`) | 設定制限なし\* | 設定制限なし\* | 合計の最大は 500 です  定義されるクライアント アプリの最大数は 100 です  クライアントあたりの定義されるスコープは最大 30 です |
| appRoles | をサポートするようになりました 公開されている委任されたアクセス許可スコープと共有される、700 個のアクセス許可定義の既定の制限。 「 [アプリ ロールの制限」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-role-limits)参照してください。 | をサポートするようになりました 公開されている委任されたアクセス許可スコープと共有される、700 個のアクセス許可定義の既定の制限。 「 [アプリ ロールの制限」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-role-limits)参照してください。 | `PersonalMicrosoftAccount`: サポートされていません`AzureADandPersonalMicrosoftAccount`: 共有 [アプリロールの制限](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-role-limits)に従ってサポートされます。  アプリ ロールは、実行時にアプリケーションのコンシューマー (MSA) ユーザーに対してサポートされていません |
| フロントチャネル ログアウト URL | `https://localhost` は許可 `http` スキームは使用できません  最大長は 255 文字です | `https://localhost` は許可 `http` スキームは使用できません  最大長は 255 文字です | `https://localhost` は使用でき、`http://localhost` は使用できません `http` スキームは使用できません  最大長は 255 文字です |
| 表示名 | 最大長は 120 文字です | 最大長は 120 文字です | 最大長は 90 文字です |

\* アプリケーション マニフェストのコレクション プロパティ全体で 1,200 エントリの集計制限があります。 個々のコレクションの制限も適用されます。 [マニフェストの制限を](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-microsoft-graph-app-manifest#manifest-limits)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/test-automate-integration-testing"} -->
## 自動統合テストの実行 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/test-automate-integration-testing
- Service: identity-platform
- Article date: 2025-06-11
- Summary: Microsoft ID プラットフォームによって保護されている API に対して、自動統合テストをユーザーとして実行する方法について説明します。 対話型サインイン プロンプトの UI を自動化する代わりに、リソース所有者のパスワード資格情報付与 (ROPC) 認証フローを使用してユーザーとしてサインインします。

開発者は、開発したアプリに対して自動統合テストを実行する必要があります。 自動化された統合テストで、Microsoft ID プラットフォーム (または [Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/) などの他の保護された API) によって保護された API を呼び出すことは困難です。 Microsoft Entra ID では、多くの場合、対話型ユーザー サインイン プロンプトが必要になりますが、これは自動化するのが困難です。 この記事では、 [リソース所有者パスワード資格情報付与 (ROPC)](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth-ropc) と呼ばれる非対話型フローを使用して、テストのためにユーザーを自動的にサインインさせる方法について説明します。

自動統合テストを準備するには、テスト ユーザーを作成し、アプリの登録を作成して構成します。また、テナントに対していくつかの構成変更を加える可能性があります。 これらの手順の一部では、管理特権が必要です。 自動化された統合テストを安全かつ効果的に実行できるように、管理者である[別のテスト テナントを作成](https://learn.microsoft.com/ja-jp/entra/identity-platform/test-setup-environment)します。

警告

運用環境では ROPC フローを使用 *しないことを* お勧めします。 [承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)など、より信頼できる方法を使用します。 ほとんどの運用シナリオでは、より安全な代替手段を利用でき、推奨されます。 ROPC フローでは、アプリケーションに非常に高い信頼が必要であり、他の認証フローには存在しないリスクが伴います。 このフローは、 [別のテスト テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/test-setup-environment)のテスト目的でのみ使用し、テスト ユーザーでのみ使用する必要があります。

重要

- Microsoft ID プラットフォームでは、Microsoft Entra テナント内での ROPC をサポートしています。個人アカウントは対象外です。 そのため、テナント固有のエンドポイント (`https://login.microsoftonline.com/{TenantId_or_Name}`) または `organizations` エンドポイントを使用する必要があります。
- Microsoft Entra テナントに招待された個人アカウントでは、ROPC を使用できません。
- パスワードがないアカウントは ROPC でサインインできません。つまり、SMS サインイン、FIDO、および Authenticator アプリなどの機能は、そのフローでは動作しません。
- ユーザーが [多要素認証 (MFA)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks) を使用してアプリケーションにサインインする必要がある場合は、代わりにブロックされます。
- [ROPC は、ハイブリッド ID フェデレーション](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-fed) シナリオ (オンプレミス アカウントの認証に使用される Microsoft Entra ID や Active Directory フェデレーション サービス (AD FS) など) ではサポートされていません。 ユーザーがフルページでオンプレミスの ID プロバイダーにリダイレクトされた場合、Microsoft Entra ID は、その ID プロバイダーに対してユーザー名とパスワードをテストできません。 ただし、[ROPC ではパススルー認証](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta)がサポートされています。
- ハイブリッド ID フェデレーション シナリオの例外は次のようになります。 *AllowCloudPasswordValidation* を TRUE に設定したホーム領域検出ポリシーでは、オンプレミスのパスワードがクラウドに同期されるときに、フェデレーション ユーザーに対して ROPC フローを機能させることができます。 詳細については、「 [レガシ アプリケーションのフェデレーション ユーザーの直接 ROPC 認証を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/home-realm-discovery-policy#enable-direct-ropc-authentication-of-federated-users-for-legacy-applications)」を参照してください。

### 別のテスト テナントを作成する

運用環境では ROPC 認証フローの使用は危険であるため、アプリケーションをテストする [別のテナントを作成](https://learn.microsoft.com/ja-jp/entra/identity-platform/test-setup-environment#set-up-a-test-environment-in-a-separate-tenant) します。 既存のテスト テナントを使用できますが、次の手順の一部では管理特権が必要になるため、テナントの管理者である必要があります。

### キー コンテナーを作成して構成する

テストユーザー名とパスワードを [シークレット](https://learn.microsoft.com/ja-jp/azure/key-vault/secrets/about-secrets) として Azure Key Vault に安全に格納することをお勧めします。 後でテストを実行すると、テストはセキュリティ プリンシパルのコンテキストで実行されます。 セキュリティ プリンシパルは、テストをローカルで (たとえば、Visual Studio または Visual Studio Code で) 実行している場合は、Microsoft Entra ユーザーです。テストを Azure Pipelines または別の Azure リソースで実行している場合は、サービス プリンシパルまたはマネージド ID です。 テスト ランナーがキー コンテナーからテスト ユーザー名とパスワードを取得できるようにするには、セキュリティ プリンシパルに **シークレットの読み取** りと **一覧表示** のアクセス許可が必要です。 詳細については、「 [Azure Key Vault での認証」](https://learn.microsoft.com/ja-jp/azure/key-vault/general/authentication)を参照してください。

1. まだキー コンテナーがない場合は、[新しいキー コンテナーを作成します](https://learn.microsoft.com/ja-jp/azure/key-vault/general/quick-create-portal)。
2. この記事の後半のテスト例で使用される **コンテナー URI** プロパティ値 ( `https://<your-unique-keyvault-name>.vault.azure.net/` と同様) をメモしておきます。
3. テストを実行するセキュリティ プリンシパルの[アクセス ポリシーを割り当てます](https://learn.microsoft.com/ja-jp/azure/key-vault/general/assign-access-policy)。 ユーザー、サービス プリンシパル、またはマネージド ID に、キー コンテナー内のシークレットの **取得** と **一覧表示** のアクセス許可を付与します。

### テスト ユーザーの作成

テスト用のテナントにいくつかのテスト ユーザーを作成します。 テスト ユーザーは実際の人間ではないため、複雑なパスワードを割り当て、これらのパスワードを [シークレット](https://learn.microsoft.com/ja-jp/azure/key-vault/secrets/about-secrets) として Azure Key Vault に安全に格納することをお勧めします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. [ **新しいユーザー** ] を選択し、ディレクトリに 1 つ以上のテスト ユーザー アカウントを作成します。
4. この記事の後半のテストの例では、単一のテスト ユーザーを使用します。 テスト [ユーザー名とパスワードを](https://learn.microsoft.com/ja-jp/azure/key-vault/secrets/quick-create-portal)、前に作成したキー コンテナーにシークレットとして追加します。 "TestUserName" という名前のシークレットとしてユーザー名を、"TestPassword" という名前のシークレットとしてパスワードを追加します。

### アプリの登録を作成して構成する

テスト中に API を呼び出すときにクライアント アプリとして機能するアプリケーションを登録します。 これは、運用環境に既に存在する可能性があるアプリケーションと同じアプリケーションにすることはできません。 テスト目的でのみ使用する別のアプリを用意する必要があります。

#### アプリケーションを登録する

アプリの登録を作成する。 [アプリ登録のクイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#register-an-application)の手順に従って、アプリケーション **(クライアント) ID を**書き留めることができます。この ID は、この記事の後半のテスト例で使用します。

#### パブリック クライアント フローに対してアプリを有効にする

ROPC はパブリック クライアント フローであるため、パブリック クライアント フローに対してアプリを有効にする必要があります。 [Microsoft Entra 管理センター](https://entra.microsoft.com)でアプリの登録から、**認証**&gt;**高度な設定**&gt;**パブリック クライアント フローを許可する**に移動します。 トグルを **[はい**] に設定します。

#### テスト中に使用するアクセス許可に同意する

ROPC は対話型フローではないので、実行時に同意する同意画面は表示されません。 トークンの取得時にエラーが発生しないように、アクセス許可を事前に確認します。

アプリにアクセス許可を追加します。 アプリに機密性の高いアクセス許可や高い特権のアクセス許可を追加しないでください。テスト シナリオは、Microsoft Entra ID との統合に関する基本的な統合シナリオにスコープを設定することをお勧めします。

[Microsoft Entra 管理センター](https://entra.microsoft.com)でアプリの登録から、[**API のアクセス許可] に移動します**&gt;**アクセス許可を追加します**。 使用している API を呼び出すために必要なアクセス許可を追加します。 この記事の以降のテストの例では、`https://graph.microsoft.com/User.Read` と `https://graph.microsoft.com/User.ReadBasic.All` のアクセス許可を使用します。

アクセス許可が追加されたら、アクセス許可に同意する必要があります。 アクセス許可に同意する方法は、テスト アプリがアプリの登録と同じテナントにあるかどうかと、テナントの管理者であるかどうかによって異なります。

##### アプリとアプリの登録が同じテナントにあり、管理者である場合

アプリを登録したのと同じテナントでアプリのテストを計画していて、そのテナントの管理者である場合は、 [Microsoft Entra 管理センター](https://entra.microsoft.com)からのアクセス許可に同意できます。 Azure portal でアプリの登録で、[**API のアクセス許可]** に移動し、[アクセス許可の&lt;] ボタンの横にある **[&gt;your\_tenant\_nameの管理者の同意を付与**する] ボタンを選択し、[**はい**] を選択して確認します。

##### アプリとアプリの登録が異なるテナントにあるか、管理者でない場合

アプリを登録したのと同じテナントでアプリのテストを計画していない場合、またはテナントの管理者でない場合は、 [Microsoft Entra 管理センター](https://entra.microsoft.com)からのアクセス許可に同意できません。 ただし、Web ブラウザーでサインイン プロンプトをトリガーすると、一部のアクセス許可に同意できます。

[Microsoft Entra 管理センター](https://entra.microsoft.com)に移動し、**Entra ID**&gt;**App 登録**に移動します&gt; 一覧からアプリケーションを選択します。 &gt; **認証**&gt;**プラットフォーム構成**&gt;**プラットフォームの追加**&gt;Web に移動**します**。 リダイレクト URI "https://localhost&quot" を追加し、**構成** を選択します。

管理者以外のユーザーが Azure portal を通じて事前承認を行う方法はないため、ブラウザーで次のリクエストを送信してください。 ログイン画面が表示されたら、前の手順で作成したテスト アカウントでサインインします。 プロンプトで表示されたアクセス許可に同意します。 呼び出す API や使用するテスト ユーザーごとに、この手順を繰り返す必要がある場合があります。

```HTTP
// Line breaks for legibility only

https://login.microsoftonline.com/{tenant}/oauth2/v2.0/authorize?
client_id={your_client_ID}
&response_type=code
&redirect_uri=https://localhost
&response_mode=query
&scope={resource_you_want_to_call}/.default
&state=12345
```

*{tenant} を*テナント ID に、*{your\_client\_ID} を*アプリケーションのクライアント ID に置き換え、*{resource\_you\_want\_to\_call} を*識別子 URI (例: "https://graph.microsoft.com")アクセスしようとしている API のアプリ ID。

### MFA ポリシーからテスト アプリとユーザーを除外する

テナントには、Microsoft が推奨するように、 [すべてのユーザーに多要素認証 (MFA) を必要とする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength)条件付きアクセス ポリシーがある可能性があります。 MFA は ROPC では機能しないため、テスト アプリケーションとテスト ユーザーをこの要件から除外する必要があります。

ユーザー アカウントを除外するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Conditional Access**&gt;**Policies** に移動します。
3. MFA を必要とする条件付きアクセス ポリシーを選択します。
4. **[ユーザーまたはワークロード ID] を選択します**。
5. [ **除外** ] タブを選択し、[ **ユーザーとグループ** ] チェック ボックスをオンにします。
6. [除外するユーザーの選択] で除外する **ユーザー アカウントを選択します**。
7. [選択] ボタンを **選択** し、[ **保存] を選択します**。

テスト アプリケーションを除外するには:

1. **[ポリシー]** で、MFA を必要とする条件付きアクセス ポリシーを選択します。
2. **[クラウド アプリ] または [アクション]** を選択します。
3. [ **除外** ] タブを選択し、[ **除外されたクラウド アプリを選択**] を選択します。
4. [除外するクラウド アプリの選択] で除外する **アプリを選択します**。
5. [選択] ボタンを **選択** し、[ **保存] を選択します**。

### アプリケーション テストの作成

設定が完了したので、自動テストを作成できます。 次のテストについて示します。

1. .NET サンプル コードでは、 [Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) と [xUnit](https://xunit.net/) (一般的なテスト フレームワーク) を使用します。
2. JavaScript サンプル コードでは、 [Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) と [Playwright](https://playwright.dev/) (一般的なテスト フレームワーク) を使用します。

## [。網](#tab/dotnet)
#### appsettings.json ファイルを設定する

前に作成したテスト アプリのクライアント ID、必要なスコープ、キー コンテナー URI をテスト プロジェクトの *appsettings.json* ファイルに追加します。

```json
{
  "Authentication": {
    "AzureCloudInstance": "AzurePublic", //Will be different for different Azure clouds, like US Gov
    "AadAuthorityAudience": "AzureAdMultipleOrgs",
    "ClientId": <your_client_ID>
  },

  "WebAPI": {
    "Scopes": [
      //For this Microsoft Graph example.  Your value(s) will be different depending on the API you're calling
      "https://graph.microsoft.com/User.Read",
      //For this Microsoft Graph example.  Your value(s) will be different depending on the API you're calling
      "https://graph.microsoft.com/User.ReadBasic.All"
    ]
  },

  "KeyVault": {
    "KeyVaultUri": "https://<your-unique-keyvault-name>.vault.azure.net//"
  }
}
```

#### すべてのテスト クラスで使用するようにクライアントを設定する

[SecretClient()](https://learn.microsoft.com/ja-jp/dotnet/api/azure.security.keyvault.secrets.secretclient) を使用して、Azure Key Vault からテスト ユーザー名とパスワード のシークレットを取得します。 このコードでは、Key Vault がスロットルされている場合の再試行にはエクスポネンシャル バックオフが使用されています。

[DefaultAzureCredential()](https://learn.microsoft.com/ja-jp/dotnet/api/azure.identity.defaultazurecredential) は、環境変数またはマネージド ID によって構成されたサービス プリンシパルからアクセス トークンを取得することによって Azure Key Vault で認証されます (コードがマネージド ID を持つ Azure リソースで実行されている場合)。 コードがローカルで実行されている場合、`DefaultAzureCredential` はローカル ユーザーの資格情報を使用します。 詳細については、 [Azure ID クライアント ライブラリ](https://learn.microsoft.com/ja-jp/dotnet/api/overview/azure/identity-readme#defaultazurecredential) のコンテンツを参照してください。

Microsoft Authentication Library (MSAL) を使用して、ROPC フローを使用して認証し、アクセス トークンを取得します。 アクセス トークンは、HTTP 要求でベアラー トークンとして渡されます。

```csharp
using Xunit;
using System.Threading.Tasks;
using Microsoft.Identity.Client;
using System.Security;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using Microsoft.Extensions.Configuration;
using Azure.Identity;
using Azure.Security.KeyVault.Secrets;
using Azure.Core;
using System;

public class ClientFixture : IAsyncLifetime
{
    public HttpClient httpClient;

    public async Task InitializeAsync()
    {
        var builder = new ConfigurationBuilder().AddJsonFile("<path-to-json-file>");

        IConfigurationRoot Configuration = builder.Build();

        var PublicClientApplicationOptions = new PublicClientApplicationOptions();
        Configuration.Bind("Authentication", PublicClientApplicationOptions);
        var app = PublicClientApplicationBuilder.CreateWithApplicationOptions(PublicClientApplicationOptions)
            .Build();

        SecretClientOptions options = new SecretClientOptions()
        {
            Retry =
                {
                    Delay= TimeSpan.FromSeconds(2),
                    MaxDelay = TimeSpan.FromSeconds(16),
                    MaxRetries = 5,
                    Mode = RetryMode.Exponential
                 }
        };

        string keyVaultUri = Configuration.GetValue<string>("KeyVault:KeyVaultUri");
        var client = new SecretClient(new Uri(keyVaultUri), new DefaultAzureCredential(), options);

        KeyVaultSecret userNameSecret = client.GetSecret("TestUserName");
        KeyVaultSecret passwordSecret = client.GetSecret("TestPassword");

        string password = passwordSecret.Value;
        string username = userNameSecret.Value;
        string[] scopes = Configuration.GetSection("WebAPI:Scopes").Get<string[]>();
        SecureString securePassword = new NetworkCredential("", password).SecurePassword;

        AuthenticationResult result = null;
        httpClient = new HttpClient();

        try
        {
            result = await app.AcquireTokenByUsernamePassword(scopes, username, securePassword)
                .ExecuteAsync();
        }
        catch (MsalException) { }

        string accessToken = result.AccessToken;
        httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("bearer", accessToken);
    }

    public Task DisposeAsync() => Task.CompletedTask;
}
```

#### テスト クラスでの使用

次の例は、Microsoft Graph を呼び出すテストです。 このテストを、独自のアプリケーションまたは API でテストする内容に置き換えます。

```csharp
public class ApiTests : IClassFixture<ClientFixture>
{
    ClientFixture clientFixture;

    public ApiTests(ClientFixture clientFixture)
    {
        this.clientFixture = clientFixture;
    }

    [Fact]
    public async Task GetRequestTest()
    {
        var testClient = clientFixture.httpClient;
        HttpResponseMessage response = await testClient.GetAsync("https://graph.microsoft.com/v1.0/me");
        var responseCode = response.StatusCode.ToString();
        Assert.Equal("OK", responseCode);
    }
}
```

## [JavaScript](#tab/JavaScript)
#### authConfig.json ファイルを設定する

前に作成したテスト アプリのクライアント ID とテナント ID、キー コンテナー URI、シークレット名をテスト プロジェクトの authConfig.js ファイルに追加します。

```javascript
export const msalConfig = {
    auth: {
        clientId: 'Enter_the_Application_Id_Here',
        authority: 'https://login.microsoftonline.com/Enter_the_Tenant_Id_Here',
    },
};

export const keyVaultConfig = {
    keyVaultUri: 'https://<your-unique-keyvault-name>.vault.azure.net',
    secretName: 'Enter_the_Secret_Name',
};
```

#### MSAL.js を初期化し、Key Vault からユーザー資格情報をフェッチする

[Configuration](https://learn.microsoft.com/ja-jp/javascript/api/%40azure/msal-node/publicclientapplication) オブジェクトを使用して [PublicClientApplication](https://learn.microsoft.com/ja-jp/javascript/api/%40azure/msal-node/publicclientapplication#@azure-msal-node-publicclientapplication-constructor) をインスタンス化して、MSAL.js 認証コンテキストを初期化します。最低限必要な構成プロパティは、アプリケーションの`clientID`です。

[SecretClient()](https://learn.microsoft.com/ja-jp/javascript/api/%40azure/keyvault-secrets/secretclient) を使用して、Azure Key Vault からテスト ユーザー名とパスワード のシークレットを取得します。

[DefaultAzureCredential()](https://learn.microsoft.com/ja-jp/javascript/api/@azure/identity/defaultazurecredential) は、環境変数またはマネージド ID によって構成されたサービス プリンシパルからアクセス トークンを取得することによって Azure Key Vault で認証されます (コードがマネージド ID を持つ Azure リソースで実行されている場合)。 コードがローカルで実行されている場合、`DefaultAzureCredential` はローカル ユーザーの資格情報を使用します。 詳細については、 [Azure ID クライアント ライブラリ](https://learn.microsoft.com/ja-jp/javascript/api/%40azure/identity/defaultazurecredential) のコンテンツを参照してください。

Microsoft Authentication Library (MSAL) を使用して、ROPC フローを使用して認証し、アクセス トークンを取得します。 アクセス トークンは、HTTP 要求でベアラー トークンとして渡されます。

```javascript
import { test, expect } from '@playwright/test';
import { DefaultAzureCredential } from '@azure/identity';
import { SecretClient } from '@azure/keyvault-secrets';
import { PublicClientApplication, CacheKVStore } from '@azure/msal-node';
import { msalConfig, keyVaultConfig } from '../authConfig';

let tokenCache;
const KVUri = keyVaultConfig.keyVaultUri;
const secretName = keyVaultConfig.secretName;

async function getCredentials() {
    try {
        const credential = new DefaultAzureCredential();
        const secretClient = new SecretClient(KVUri, credential);
        const secret = await secretClient.getSecret(keyVaultConfig.secretName);
        const password = secret.value;
        return [secretName, password];
    } catch (error) {
        console.log(error);
    }
}

test.beforeAll(async () => {
    const pca = new PublicClientApplication(msalConfig);
    const [username, password] = await getCredentials();
    const usernamePasswordRequest = {
        scopes: ['user.read', 'User.ReadBasic.All'],
        username: username,
        password: password,
    };
    await pca.acquireTokenByUsernamePassword(usernamePasswordRequest);
    tokenCache = pca.getTokenCache().getKVStore();
});
```

#### テスト スイートを実行する

同じファイルに、次に示すようにテストを追加します。

```javascript
/**
 * Stores the token in the session storage and reloads the page
 */
async function setSessionStorage(page, tokens) {
    const cacheKeys = Object.keys(tokens);
    for (let key of cacheKeys) {
        const value = JSON.stringify(tokenCache[key]);
        await page.context().addInitScript(
            (arr) => {
                window.sessionStorage.setItem(arr[0], arr[1]);
            },
            [key, value]
        );
    }
    await page.reload();
}

test.describe('Testing Authentication with MSAL.js ', () => {
    test('Test user has signed in successfully', async ({ page }) => {
        await page.goto('http://localhost:<port>/');
        let signInButton = page.getByRole('button', { name: /Sign In/i });
        let signOutButton = page.getByRole('button', { name: /Sign Out/i });
        let welcomeDev = page.getByTestId('WelcomeMessage');
        expect(await signInButton.count()).toBeGreaterThan(0);
        expect(await signOutButton.count()).toBeLessThanOrEqual(0);
        expect(await welcomeDev.innerHTML()).toEqual('Please sign-in to see your profile and read your mails');
        await setSessionStorage(page, tokenCache);
        expect(await signInButton.count()).toBeLessThanOrEqual(0);
        expect(await signOutButton.count()).toBeGreaterThan(0);
        expect(await welcomeDev.innerHTML()).toContain(`Welcome`);
    });
});

```

詳細については、次のコード サンプル [MSAL.js テスト例](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-browser-samples/TestingSample)を確認してください。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/test-setup-environment"} -->
## アプリのテスト環境を設定する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/test-setup-environment
- Service: identity-platform
- Article date: 2025-01-24
- Summary: Microsoft ID プラットフォームと統合されたアプリケーションをテストできるように、Microsoft Entra テスト環境を設定する方法について説明します。 テストに別のテナントが必要かどうか、または実稼働テナントを使用できるかどうかを評価します。

開発、テスト、運用のライフサイクルを通じてアプリを移動するには、Microsoft Entra テスト環境を設定します。 アプリ開発の初期段階で Microsoft Entra テスト環境を使用し、永続的なテスト環境として長期的に使用できます。

### 専用のテスト テナントまたは運用環境の Microsoft Entra テナントを選択する

テストに専用のテスト テナントと運用テナントのどちらを使用するかを決定する必要があります。

運用テナントを使用すると、いくつかのテストの側面を簡略化できますが、特に高い特権のシナリオでは、テスト リソースと運用リソースの間で適切な分離が必要です。

次の場合は、運用テナントを使用しないでください。

- アプリには、管理者の同意が必要なアプリ専用のアクセス許可など、テナント全体の一意の設定が必要です。
- テナント メンバーによるテスト リソースへの承認されていないアクセスを危険にさらすことはできません。
- 運用環境は、構成の変更によって中断される可能性があります。
- 運用テナントでユーザーを作成したり、データをテストしたりすることはできません。
- 運用テナントには、必須の多要素認証など、認証中にユーザーの操作を必要とするポリシーがあります。 このような場合、統合テストに自動サインインを使用することはできません。
- 非本番リソースを追加することで [、サービスまたはスロットリングの上限](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-service-limits-restrictions) を超える可能性があります。

これらの制限が適用される場合は、 別のテナントにテスト環境を設定します。

そうでない場合は、 実稼働テナントでテスト環境を設定できます。 運用テナントの特権ロールを持つユーザー (クラウド アプリケーション管理者など) は、いつでもリソースにアクセスして構成を変更できます。 テスト リソースまたは構成へのアクセスを禁止するには、そのデータを別のテナントに配置します。

### 別のテナントにテスト環境を設定する

テスト環境を別のテナントに設定すると、運用環境がテスト中に行われた変更や構成の影響を受けないようにすることができます。 テスト テナントを設定し、それをユーザーに設定し、運用テナントに一致するポリシーで構成する必要があります。

#### テスト テナントを取得する

まだ専用のテスト テナントがない場合は、Microsoft 365 開発者プログラムを使用して無料で作成することも、自分で手動で作成することもできます。

## [Microsoft 365 開発者プログラム](#tab/microsoft-365-developer-program)
Microsoft [365 開発者プログラム](https://learn.microsoft.com/ja-jp/office/developer-program/microsoft-365-developer-program)に参加することをお勧めします。 このプログラムは無料で、テスト ユーザー アカウントとサンプル データ パックをテナントに自動的に追加できます。

1. [Microsoft 365 Developer Program](https://developer.microsoft.com/en-us/microsoft-365/dev-program) ページを開き、[**今すぐ参加**] を選択します。
2. 新しい Microsoft アカウントでサインインするか、既存の (職場) アカウントを使用します。
3. サインアップ ページで地域を選択し、会社名を入力し、プログラムの使用条件に同意してから、[次へ] 選択します。
4. **[サブスクリプションをセットアップ]**を選択します。 新しいテナントを作成するリージョンを指定し、ユーザー名とドメインを作成して、パスワードを入力します。 新しいテナントが作成され、テナントの最初の管理者が追加されます。
5. 新しいテナントの管理者アカウントを保護するために必要なセキュリティ情報を入力します。 これにより、アカウントの多要素認証が設定されます。

## [テナントを手動で作成する](#tab/create-tenant-manually)
[テナントは手動で作成](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)できます。テナントは作成時に空であり、テスト データを使用して構成する必要があります。

---

#### テナントにユーザーを設定する

便宜上、自分と開発チームの他のメンバーをテナントのゲスト ユーザーに招待できます。 これにより、テスト テナントに個別のゲスト オブジェクトが作成されますが、企業アカウントとテスト アカウントの資格情報のセットを 1 つだけ管理する必要があることを意味します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション開発者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. **新しいユーザー**&gt;**外部ユーザーを招待**して、職場のアカウントのメールアドレスを招待します。
4. アプリケーションの開発やテストのチームの他のメンバーに対して繰り返します。

テスト テナントでテスト ユーザーを作成することもできます。 Microsoft 365 サンプル パックのいずれかを使用した場合は、テナントにテスト ユーザーが既に存在している可能性があります。 そうではない場合は、テナント管理者として自分で作成できます。

1. **Entra ID**&gt;**Users** に移動します。
2. [**新しいユーザー**] を選択&gt;**新しいユーザーを作成**し、ディレクトリに新しいテスト ユーザーを作成します。

#### Microsoft Entra サブスクリプションを取得する (省略可能)

アプリケーションで Microsoft Entra ID P1 または P2 の機能を完全にテストする場合は、 [Premium P1 または Premium P2 ライセンス](https://azure.microsoft.com/pricing/details/active-directory/)にテナントをサインアップする必要があります。

Microsoft 365 Developer プログラムを使用してサインアップした場合、テスト テナントには Microsoft Entra ID P2 ライセンスが付属しています。 そうでない場合でも、 [Microsoft Entra ID P1 または P2 の 1 か月間の無料試用版を](https://azure.microsoft.com/trial/get-started-active-directory/)有効にできます。

#### アプリの登録を作成して構成する

テスト環境で使用するアプリ登録を作成する必要があります。 これは、テスト環境と運用環境の間のセキュリティ分離を維持するために、最終的な運用アプリの登録とは別の登録である必要があります。 アプリケーションの構成方法は、ビルドするアプリの種類によって異なります。 詳細については、 [アプリケーションの登録を](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)参照してください。

#### テナントにポリシーを設定する

1 つの組織 (多くの場合、シングル テナントと呼ばれます) が主にアプリを使用し、運用テナントにアクセスできる場合は、運用テナントの設定をレプリケートして、アプリの動作に可能な限り影響を与えることで、運用環境で予期しないエラーが発生する可能性を減らします。

##### 条件付きアクセス ポリシー

条件付きアクセス ポリシーをレプリケートすると、運用環境に移行するときに予期しないアクセスのブロックを確実に発生させず、アプリケーションで受信する可能性があるエラーを適切に処理できます。

運用テナントの条件付きアクセス ポリシーの表示は、 [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)が実行する必要がある場合があります。

1. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**条件付きアクセス** に移動します。
2. テナント内のポリシーの一覧を表示し、最初のポリシーを選択します。
3. **クラウド アプリまたはアクション**に移動します。
4. ポリシーがアプリの選択したグループにのみ適用される場合は、次のポリシーに進みます。 そうではない場合は、運用環境に移行するときにアプリにも適用される可能性があります。 ポリシーをテスト テナントにコピーする必要があります。

新しいタブまたはブラウザー セッションで、少なくとも[条件付きアクセス管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)にサインインし、テスト テナントにアクセスします。

1. **Entra ID**&gt;**Conditional Access** に移動します。
2. [**新しいポリシーの作成]** を選択する
3. 前の手順によって識別された実稼働テナント ポリシーから設定をコピーします。

##### アクセス許可付与ポリシー

アクセス許可付与ポリシーをレプリケートすると、運用環境に移行するときに、管理者の同意を求める予期しないプロンプトが表示されることはありません。

**Entra ID**&gt;**Enterprise アプリ**&gt;**同意と権限**&gt;**ユーザー同意**の設定に移動します。 そこで設定をテスト テナントにコピーします。

##### トークンの有効期間ポリシー

トークンの有効期間ポリシーをレプリケートすると、アプリケーションに発行されたトークンが、運用環境で予期せず期限切れになることはありません。

トークンの有効期間ポリシーは、現在は PowerShell を使用してのみ管理できます。 [構成可能なトークンの有効期間](https://learn.microsoft.com/ja-jp/entra/identity-platform/configurable-token-lifetimes)について説明し、運用組織全体に適用されるトークンの有効期間ポリシーを特定する方法について説明します。 これらのポリシーをテスト テナントにコピーします。

### 実稼働テナントでテスト環境を設定する

運用環境のテナントでテスト アプリを安全に制限できる場合は、テスト目的でテナントを構成できます。

#### アプリの登録を作成して構成する

テスト環境で使用するアプリ登録を作成する必要があります。 これは、テスト環境と運用環境の間のセキュリティ分離を維持するために、最終的な運用アプリの登録とは別の登録である必要があります。 アプリケーションの構成方法は、ビルドするアプリの種類によって異なります。 詳細については、「 [Microsoft Entra ID にアプリケーションを登録する」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)参照してください。

#### テスト ユーザーを作成する

シナリオのテスト中に使用するテスト データが関連付けられているテスト ユーザーを作成する必要があります。 この手順は、管理者が実行する必要がある場合があります。

1. **Entra ID**&gt;**Users** に移動します。
2. [**新しいユーザー**] を選択&gt;**新しいユーザーを作成**し、ディレクトリに新しいテスト ユーザーを作成します。

#### テスト ユーザーをグループに追加する (省略可能)

便宜上、これらのすべてのユーザーをグループに割り当てることができます。これにより、他の割り当て操作が簡単になります。

1. **Entra ID**&gt;**Groups**&gt;**All groups** に移動します。
2. [ **新しいグループ]** を選択します。
3. グループの種類として **[セキュリティ** ] または **[Microsoft 365** ] を選択します。
4. グループに名前を付けます。
5. 前の手順で作成したテスト ユーザーを追加します。

#### テスト アプリケーションを特定のユーザーに制限する

ユーザー割り当てを使用して、テスト アプリケーションの使用が許可されるテナント内のユーザーを、特定のユーザーまたはグループに制限することができます。 アプリの 登録を使用してアプリを作成すると、アプリの表現も **Enterprise アプリケーション** で作成されました。 **エンタープライズ アプリケーション**の設定を使用して、テナントでアプリケーションを使用できるユーザーを制限します。

重要

アプリが [マルチテナント アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-supported-account-types)の場合、この操作では、他のテナントのユーザーがアプリにサインインして使用するのを制限しません。 ユーザー割り当てが構成されているテナントのユーザーのみが制限されます。

テナント内の特定のユーザーにアプリを制限する詳細な手順については、アプリを一 [連のユーザーに制限する方法に関する](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-restrict-your-app-to-a-set-of-users)ページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/troubleshoot-publisher-verification"} -->
## 発行者の確認に関するトラブルシューティング - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/troubleshoot-publisher-verification
- Service: identity-platform
- Article date: 2024-07-19
- Summary: Microsoft Graph API を呼び出すことによって、Microsoft ID プラットフォームの発行者確認のトラブルシューティングを行う方法について説明します。

プロセスを完了できない、エラー メッセージが表示される、または[発行元の確認](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview)で予期しない動作が発生する場合は、次の手順に従って問題を解決してください。

1. [要件](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview#requirements)を調べて、すべてが満たされていることを確認します。
2. 指示に従って、[アプリを発行者確認済み](https://learn.microsoft.com/ja-jp/entra/identity-platform/mark-app-as-publisher-verified)としてマークし、すべての手順が正常に実行されていることを確認します。
3. 一般的な問題の一覧を確認します。
4. Graph エクスプローラーを使用して要求を再現し、さらに情報を収集して、UI の問題を除外します。

### 一般的な問題

発行元の確認プロセス中に発生する可能性がある一般的な問題を次に示します。

- **クラウド パートナー プログラム ID (Partner One ID) がわかりません、または誰がアカウントの第一連絡先担当者かわかりません。**

    1. [\[Cloud Partner Program enrollment\] (クラウド パートナー プログラムの登録) ページ](https://partner.microsoft.com/dashboard/account/v3/enrollment/joinnow/basicpartnernetwork/new)に移動します。
    2. 組織のプライマリ Microsoft Entra テナントにユーザー アカウントでサインインします
    3. クラウド パートナー プログラム アカウントが既に存在する場合は、このアカウントが認識されてユーザーはこのアカウントに追加されます。
    4. [\[パートナー プロファイル\] ページ](https://partner.microsoft.com/dashboard/account/v3/overview)に移動すると、Partner One ID とアカウントの第一連絡先担当者が表示されます。
- **誰が Microsoft Entra グローバル管理者 (社内管理者またはテナント管理者とも呼ばれます) かわかりません。どうすれば見つけることができますか。 アプリケーション管理者またはクラウド アプリケーション管理者はどうですか。**

    1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
    2. **Entra ID**&gt;**役割と管理者**に移動します。
    3. 目的の管理者ロールを選択します。
    4. そのロールが割り当てられたユーザーの一覧が表示されます。
- **自分の CPP アカウントの管理者がわかりません**[CPP のユーザー管理ページ](https://partner.microsoft.com/dashboard/account/v3/usermanagement)にアクセスし、ユーザー一覧をフィルター処理して、さまざまな管理者ロールのユーザーを確認します。
- **Partner One ID が無効である、またはアクセス権を持っていない、というエラーが表示されます。**修復のガイダンスに従ってください。
- **Microsoft Entra 管理センターにサインインしても、登録したアプリが表示されません。 なぜですか?** アプリの登録は、このテナントの別のユーザー アカウント、個人またはコンシューマー アカウント、または別のテナントを使用して作成された可能性があります。 アプリの登録が作成されたテナントで、正しいアカウントを使用してサインインしていることを確認します。
- **多要素認証に関連するエラーが発生します。 どうすればよいですか。**[多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-licensing)が有効になっており、かつサインインに使用しているユーザーとこのシナリオで**必須**になっていることを確認します。 たとえば、MFA には次のような条件があります。

    - サインインに使用しているユーザーには常に必要です。
    - [Azure の管理に必要です](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-old-require-mfa-azure-mgmt)。
    - サインインに使用している[この種類の管理者には必要です](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-old-require-mfa-admin)。

### Microsoft Graph API の呼び出しを行う

問題が発生しても、UI に表示されている内容に基づいてその理由を把握できない場合は、Microsoft Graph の呼び出しを使用して、アプリ登録ポータルで実行できる操作と同じ操作を実行することで、さらにトラブルシューティングを行うと役に立つことがあります。

これらの要求を行う最も簡単な方法は、[Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)を使用することです。 PowerShell などの他のオプションを検討して [Web 要求を呼び出す](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.utility/invoke-webrequest)こともできます。

Microsoft Graph を使用して、アプリの確認済み発行者を設定および設定解除し、これらの操作のいずれかを実行した後で結果を確認できます。 結果は、アプリの登録に対応する[アプリケーション](https://learn.microsoft.com/ja-jp/graph/api/resources/application) オブジェクトと、そのアプリからインスタンス化されている任意の[サービス プリンシパル](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceprincipal)のどちらでも確認できます。 それらのオブジェクト間の関係の詳細については、次を参照してください: 「[Microsoft Entra ID のアプリケーション オブジェクトとサービス プリンシパル オブジェクト](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)」。

いくつかの便利な要求の例を次に示します。

#### 確認済み発行者を設定する

リクエスト

```
POST /applications/00001111-aaaa-2222-bbbb-3333cccc4444/setVerifiedPublisher 

{ 

    "verifiedPublisherId": "12345678" 

} 
```

回答

```
204 No Content 
```

注

*verifiedPublisherID* が Partner One ID です。

#### 確認済み発行者を設定解除する

要求:

```
POST /applications/00001111-aaaa-2222-bbbb-3333cccc4444/unsetVerifiedPublisher 
```

回答

```
204 No Content 
```

#### アプリケーションから確認済み発行者の情報を取得する

```
GET https://graph.microsoft.com/v1.0/applications/00001111-aaaa-2222-bbbb-3333cccc4444 

HTTP/1.1 200 OK 

{ 
    "id": "00001111-aaaa-2222-bbbb-3333cccc4444", 

    ... 

    "verifiedPublisher" : { 
        "displayName": "myexamplePublisher", 
        "verifiedPublisherId": "12345678", 
        "addedDateTime": "2019-12-10T00:00:00" 
    } 
} 
```

#### サービス プリンシパルから確認済み発行者の情報を取得する

```
GET https://graph.microsoft.com/v1.0/servicePrincipals/11112222-bbbb-3333-cccc-4444dddd5555

HTTP/1.1 200 OK 

{ 
    "id": "11112222-bbbb-3333-cccc-4444dddd5555", 

    ... 

    "verifiedPublisher" : { 
        "displayName": "myexamplePublisher", 
        "verifiedPublisherId": "12345678", 
        "addedDateTime": "2019-12-10T00:00:00" 
    } 
} 
```

### エラー リファレンス

次の一覧は、Microsoft Graph でトラブルシューティングを行っている、またはアプリ登録ポータル内でプロセスを実行している際に、発生する可能性があるエラー コードを示しています。

#### MPNアカウントが見つからないか、アクセス権がありません

指定した Partner One ID (`MPNID`) が存在しないか、それに対するアクセス権がありません。 有効な Partner One ID を指定してから、やり直してください。

最も一般的な原因は、サインインしているユーザーが、パートナー センター内での CPP アカウントの適切なロールのメンバーではないことです。 対象となるロールの一覧については、「[必要条件](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview#requirements)」を参照してください。詳細については、「一般的な問題」を参照してください。 このエラーは、テナント (アプリが登録されている) が CPP アカウントに追加されていない、または無効な Partner One ID が原因である可能性もあります。

**修復手順**

1. 自分の[パートナー プロファイル](https://partner.microsoft.com/pcv/accountsettings/connectedpartnerprofile)に移動し、次のことを確認します。

    - Partner One ID は正常です。
    - エラーまたは "保留中のアクション" が表示されず、会社プロファイルとパートナー情報の両方の検証状態が "承認済み" または "成功" と表示される。
2. [CPP テナント管理ページ](https://partner.microsoft.com/dashboard/account/v3/tenantmanagement)に移動し、アプリが登録されているテナント、およびサインインに使用しているユーザー アカウントのテナントが、関連付けられているテナントの一覧に含まれることを確認します。 別のテナントを追加するには、[マルチテナントアカウントの手順](https://learn.microsoft.com/ja-jp/partner-center/multi-tenant-account)に従います。 追加するテナントのすべてのグローバル管理者には、パートナー センター アカウントに対するグローバル管理者特権が許可されます。
3. [CPP ユーザー管理ページ](https://partner.microsoft.com/pcv/users)に移動し、サインインに使用しているユーザーが、グローバル管理者、MPN 管理者、またはアカウント管理者のいずれかであることを確認します。パートナー センターでロールにユーザーを追加するには、[ユーザー アカウントの作成およびアクセス許可の設定](https://learn.microsoft.com/ja-jp/partner-center/create-user-accounts-and-set-permissions)の手順に従います。

#### MPNGlobalAccountNotFound

指定した Partner One ID (`MPNID`) が無効です。 有効な Partner One ID を指定してから、やり直してください。

パートナーの場所アカウント (PLA) に対応する Partner One ID が指定されている場合に最もよく発生します。 パートナー グローバル アカウントのみがサポートされています。 詳しくは、[パートナー センターのアカウントの構造](https://learn.microsoft.com/ja-jp/partner-center/account-structure)に関する記事をご覧ください。

**修復手順**

1. [パートナー プロファイル](https://partner.microsoft.com/pcv/accountsettings/connectedpartnerprofile)&gt;** [識別子] ブレード **&gt;**[Microsoft Cloud Partner Program] タブ**に移動します。
2. PartnerGlobal 型のパートナー ID を使用します。

#### MPNAccountInvalid (無効なアカウント)

指定した Partner One ID (`MPNID`) が無効です。 有効な Partner One ID を指定してから、やり直してください。

間違った Partner One ID が指定されていることが原因で最もよく発生します。

**修復手順**

1. [パートナー プロファイル](https://partner.microsoft.com/pcv/accountsettings/connectedpartnerprofile)&gt;** [識別子] ブレード **&gt;**[Microsoft Cloud Partner Program] タブ**に移動します。
2. PartnerGlobal 型のパートナー ID を使用します。

#### MPNアカウントが審査されていません

指定した Partner One ID (`MPNID`) は、審査プロセスを完了していません。 パートナー センターでこのプロセスを完了してから、操作をやり直してください。

CPP アカウントが[検証](https://learn.microsoft.com/ja-jp/partner-center/verification-responses)プロセスを完了していない場合に最もよく発生します。

**修復手順**

1. [パートナー プロファイル](https://partner.microsoft.com/pcv/accountsettings/connectedpartnerprofile)に移動し、エラーまたは**保留中のアクション**が表示されておらず、会社プロファイルとパートナー情報の両方の検証状態が**承認済み**または**成功**と表示されていることを確認します。
2. そうでない場合は、パートナー センターで保留中のアクション アイテムを表示し、[こちら](https://learn.microsoft.com/ja-jp/partner-center/verification-responses)でトラブルシューティングを行います。

#### 関連付けられているMPNアカウントに発行者IDがありません

指定した Partner One ID (`MPNID`) が無効です。 有効な Partner One ID を指定してから、やり直してください。

間違った Partner One ID が指定されていることが原因で最もよく発生します。

**修復手順**

1. [パートナー プロファイル](https://partner.microsoft.com/pcv/accountsettings/connectedpartnerprofile)&gt;** [識別子] ブレード **&gt;**[Microsoft Cloud Partner Program] タブ**に移動します。
2. PartnerGlobal 型のパートナー ID を使用します。

#### MPNIdは関連付けられたMPNアカウントと一致しません

指定した Partner One ID (`MPNID`) が無効です。 有効な Partner One ID を指定してから、やり直してください。

間違った Partner One ID が指定されていることが原因で最もよく発生します。

**修復手順**

1. [パートナー プロファイル](https://partner.microsoft.com/pcv/accountsettings/connectedpartnerprofile)&gt;** [識別子] ブレード **&gt;**[Microsoft Cloud Partner Program] タブ**に移動します。
2. PartnerGlobal 型のパートナー ID を使用します。

#### アプリケーション見つかりません

ターゲット アプリケーション (`AppId`) が見つかりません。 有効なアプリケーション ID を指定して、もう一度やり直してください。

Graph API 経由で検証が実行され、指定されたアプリケーションの ID が正しくない場合に最もよく発生します。

**修復手順**

1. AppId や ClientId ではなくアプリケーションの Object ID を指定する必要があります。 **こちら**のアプリケーション プロパティの一覧で [ID](https://learn.microsoft.com/ja-jp/graph/api/resources/application) を参照してください。
2. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
3. **Entra ID**&gt;**アプリ登録**に移動します。
4. アプリの登録を見つけてオブジェクト ID を確認します。

#### アプリケーションオブジェクトが無効です

ターゲット アプリケーションのオブジェクト ID が無効です。 有効な ID を指定して、やり直してください。

Graph API 経由で検証が実行され、指定されたアプリケーションの ID が存在しない場合に最もよく発生します。

**修復手順**

1. AppId や ClientId ではなくアプリケーションの Object ID を指定する必要があります。 **こちら**のアプリケーション プロパティの一覧で [ID](https://learn.microsoft.com/ja-jp/graph/api/resources/application) を参照してください。
2. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
3. **Entra ID**&gt;**アプリ登録**に移動します。
4. アプリの登録を見つけてオブジェクト ID を確認します。

#### B2CTenantNotAllowed (個人向けテナントは許可されていません)

この機能は、Azure AD B2C テナントではサポートされていません。

#### メール確認済みのテナントは許可されていません

この機能は、メールで確認されたテナントではサポートされていません。

#### アプリケーションに発行者ドメインがありません。

ターゲット アプリケーション (`AppId`) には、パブリッシャー ドメインが設定されている必要があります。 パブリッシャー ドメインを設定してから、やり直してください。

アプリで[パブリッシャー ドメイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-configure-publisher-domain)が構成されていない場合に発生します。

**修復手順**[こちら](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-configure-publisher-domain)の指示に従ってパブリッシャー ドメインを設定します。

#### 発行者ドメイン不一致

ターゲット アプリケーションのパブリッシャー ドメイン (`publisherDomain`) がパートナー センターでメール検証を実行するために使用されたドメイン (`pcDomain`) と一致しないか、検証が行われていません。 これらのドメインが一致し、検証されていることを確認してから、やり直してください。

アプリの[パブリッシャー ドメイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-configure-publisher-domain)と Microsoft Entra テナントに追加された[カスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)のいずれもがパートナー センターでメール検証を実行するために使用されたドメインと一致しないか、検証が行われていない場合に発生します。

許可されるドメインまたはサブドメインの一致の一覧については、「[要件](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview)」を参照してください。

**修復手順**

1. [パートナー プロファイル](https://partner.microsoft.com/pcv/accountsettings/connectedpartnerprofile)に移動し、主要連絡先としてリストされているメール アドレスを表示します
2. パートナー センターでメール アドレスの検証を実行するために使用されるドメインは、主要連絡先のメール アドレスの "@" の後の部分です
3. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
4. **Entra ID**&gt;**アプリ登録**&gt;**ブランディングおよびプロパティ**に移動します。
5. **[パブリッシャー ドメインの更新]** を選択し、**[新しいドメインの確認]** の指示に従います。
6. パートナー センターでメール アドレスの検証を実行するために使用するドメインを新しいドメインとして追加します。

#### パブリッシャーの検証権限がありません

アプリケーション (&lt;`AppId`) の検証済みパブリッシャー プロパティを設定することを認可されていません。

サインインしているユーザーが Microsoft Entra ID の CPP アカウントの適切なロールのメンバーになっていないことが原因で最もよく発生します。対象となるロールの一覧については、「[必要条件](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview#requirements)」をご覧ください。また、詳しくは、「一般的な問題」をご覧ください。

**修復手順**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**役割と管理者**に移動します。
3. 自分に十分なアクセス許可がある場合は、必要な管理者ロールを選択して **[割り当ての追加]** を選択します。
4. 自分に十分なアクセス許可がない場合は、管理者ロールを持つユーザーに支援を依頼します。

#### MPNIdWasNotProvided（MPN ID が提供されていません）

要求本文で Partner One ID が指定されていないか、または要求の内容の種類が "application/json" ではありませんでした。

Graph API 経由で検証が実行され、Partner One ID が要求で指定されない場合に最もよく発生します。

**修復手順**

1. [パートナー プロファイル](https://partner.microsoft.com/pcv/accountsettings/connectedpartnerprofile)&gt;** [識別子] ブレード **&gt;**[Microsoft Cloud Partner Program] タブ**に移動します。
2. 要求では PartnerGlobal 型のパートナー ID を使用します。

#### MSAサポート対象外

この機能は、Microsoft コンシューマー アカウントではサポートされていません。 Microsoft Entra ユーザーによって Microsoft Entra ID に登録されたアプリケーションのみがサポートされます。

コンシューマー アカウントがアプリの登録に使用されている場合に発生します (Hotmail、Messenger、OneDrive、MSN、Xbox Live、または Microsoft 365)。

#### 操作が必要

検証済みのパブリッシャーをアプリに追加しようとする前に多要素認証 (MFA) が有効化され実行されていない場合に発生します。 詳しくは、「一般的な問題」をご覧ください。 注:確認済み発行者を追加するときは、同じセッションで MFA を行う必要があります。 MFA が有効になっていても、そのセッションで行う必要がない場合、要求は失敗します。

"管理者によって構成が変更されたか、新しい場所に移動したため、続行するには多要素認証を使用する必要があります" というエラー メッセージが表示されます。

**修復手順**

1. [多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-licensing)が有効になっており、かつサインインに使用しているユーザーとこのシナリオで**必須**になっていることを確認します。
2. 発行者の確認を再試行します

#### ユーザーは出版社を追加できません

エラー: "確認済みパブリッシャーをこのアプリケーションに追加できません。 管理者にお問い合わせください"。

確認済み発行者を追加する要求が行われると、多数のシグナルを使用してセキュリティ リスク評価が実行されます。 ユーザー リスクの状態が ‘AtRisk’ であると判断された場合は、上記のエラーが返されます。 ユーザー リスクを調査し、リスクを修復するための適切な手順を実行します(以下のガイダンス)。

**修復手順**

>
> [リスクの調査](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)
>
> [リスクの修復/ユーザーのブロック解除](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock)
>
> [自己修復ガイダンス](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock)
>
> セルフサービス パスワード リセット (SSPR): 組織で SSPR が許可されている場合は、aka.ms/sspr を使用して、修復のためにパスワードをリセットします。 強力なパスワードを選択してください。脆弱なパスワードを選択しても、リスク状態をリセットできない場合があります。

注

修復後にリスク状態を更新する時間を置いてから、やり直してください。

#### 発行者を追加できません

エラー: "確認済みパブリッシャーをこのアプリケーションに追加できません。 Please contact your administrator for assistance. (管理者にお問い合わせください。)"

確認済み発行者を追加する要求が行われると、多数のシグナルを使用してセキュリティ リスク評価が実行されます。 要求が危険であると判断された場合は、上記のエラーが返されます。 セキュリティ上の理由から、Microsoft は、要求が危険かどうかを判断するために使用される具体的な基準を公開していません。

**修復手順**

>
> "リスクあり"という評価が正しくないと信じられる場合は、次の日に確認要求をもう一度送信してみてください。 リスク状態が更新されるまで、しばらくかかる場合があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/troubleshoot-required-resource-access-limits"} -->
## 構成されたアクセス許可の制限のトラブルシューティング - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/troubleshoot-required-resource-access-limits
- Service: identity-platform
- Article date: 2024-04-10
- Summary: 一部のアプリが構成されたアクセス許可の制限を超える可能性がある理由と、この問題に対処する方法について説明します。

アプリケーション オブジェクトの `RequiredResourceAccess` コレクション (RRA) には、アプリが既定の同意要求に必要とするすべての構成済み API アクセス許可が含まれています。 このコレクションには、アプリでサポートされる ID の種類に応じて、さまざまな制限があります。 サポートされているアカウントの種類の制限の詳細については、「サポートされているアカウントの種類 [による検証の違い](https://learn.microsoft.com/ja-jp/entra/identity-platform/supported-accounts-validation)」を参照してください。

最大アクセス許可の制限は 2022 年 5 月に更新されました。そのため、一部のアプリでは、現在許可されているよりも多くのアクセス許可が RRA に含まれる場合があります。 さらに、アクセス許可の構成後にサポートされているアカウントの種類を変更するアプリは、新しい設定の制限を超える可能性があります。 アプリが構成されたアクセス許可の制限を超えると、 `RequiredResourceAccess` コレクション内のアクセス許可の数が制限の下に戻されるまで、新しいアクセス許可を追加できません。

このドキュメントでは、この問題を解決するための追加情報とトラブルシューティング手順について説明します。

### アプリが `RequiredResourceAccess` の制限を超えたタイミングを特定する

一般に、400 を超えるアクセス許可を持つすべてのアプリケーションが構成制限を超えています。 また、個人の Microsoft アカウント (MSA) のサインインをサポートしている場合、アプリには下限が適用される場合があります。 アクセス許可の制限を超えたアプリは、Azure portal でアクセス許可を追加しようとすると、次のエラーを受け取ります。

>
> `Failed to save permissions for <AppName>. This configuration exceeds the global application object limit. Remove some items and retry your request.`

### 解決手順

アプリケーションが不要になった場合、最初に考慮する必要があるオプションは、アプリの登録を完全に削除することです。 ( [最近削除されたアプリケーションは](https://learn.microsoft.com/ja-jp/entra/architecture/recover-from-deletions#applications-and-service-principals)、後でまだ必要であることが判明した場合に備えて復元できます)。

アプリケーションが必要な場合や不明な場合は、次の手順を実行すると、この問題を解決できます。

1. **重複するアクセス許可を削除します。** 場合によっては、同じアクセス許可が複数回表示されます。 必要なアクセス許可を確認し、2 回以上表示されているアクセス許可を削除します。 この記事の 「その他のリソース 」セクションで、関連する PowerShell スクリプトを参照してください。
2. **未使用のアクセス許可を削除します。** アプリケーションに必要なアクセス許可を確認し、アプリケーションまたはサービスの動作と比較します。 アプリの登録で構成されているが、アプリケーションまたはサービスに必要のないアクセス許可を削除します。 アクセス許可を確認する方法の詳細については、「[アプリケーションのアクセス許可を確認](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-application-permissions)する」を参照してください。
3. **冗長なアクセス許可を削除します。** Microsoft Graph を含む多くの API では、他の特権アクセス許可が含まれている場合、一部のアクセス許可は必要ありません。 たとえば、アプリケーションに User.ReadWrite.All (すべてのユーザーの読み取り、作成、更新) がある場合、Microsoft Graph アクセス許可 User.Read.All (すべてのユーザーの読み取り) は必要ありません。 Microsoft Graph のアクセス許可の詳細については、 [Microsoft Graph のアクセス許可リファレンスを参照してください](https://learn.microsoft.com/ja-jp/graph/permissions-reference)。

### よく寄せられる質問 (FAQ)

#### *Microsoft が総アクセス許可の制限を変更した理由*

この制限は、次の 2 つの理由で重要です。

- 同意時に付与できるよりも多くのアクセス許可を必要とするようにアプリが構成されないようにするため。
- アプリ登録の合計サイズを、基になるストレージ プラットフォームの安定性とパフォーマンスに必要な制限内に保持するため。

#### *何もしない場合はどうなるでしょうか。*

アプリがアクセス許可の合計制限を超えた場合、アプリケーションに必要なアクセス許可の合計数を増やすことができなくなります。

#### *制限によって、アプリケーションに付与できるアクセス許可の数は変わりますか?*

いいえ。 この制限は、アプリ登録で構成された要求された API アクセス許可の一覧にのみ影響します。 これは、アプリケーションに付与されているアクセス許可の一覧とは異なります。

必要な API アクセス許可の一覧にリストされていない場合でも、委任されたアクセス許可はアプリケーションによって動的に要求できます。 委任されたアクセス許可とアプリ ロール (アプリケーションのアクセス許可) の両方を、Microsoft Graph API または Microsoft Graph PowerShell を使用して直接付与することもできます。

#### *アプリケーションの制限は引き上げられますか?*

いいえ。個々のアプリケーションまたは組織に対して制限を引き上げることはできません。

#### *必要な API アクセス許可の一覧に他の制限はありますか?*

はい。 制限は、アプリでサポートされているアカウントの種類によって異なる場合があります。 サインイン用の個人用 Microsoft アカウント (Outlook.com、Hotmail.com、Xbox Live など) をサポートするアプリには、通常、下限があります。 詳細については、 [サポートされているアカウントの種類による検証の違い](https://learn.microsoft.com/ja-jp/entra/identity-platform/supported-accounts-validation) を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-cli-app-node-sign-in-prepare-app"} -->
## チュートリアル: 認証用の Node.js CLI アプリを作成する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-cli-app-node-sign-in-prepare-app
- Service: identity-platform
- Article date: 2025-04-16
- Summary: 外部テナントでユーザーがサインインする Node.js CLI アプリを構築する方法について説明します

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルは、Node.js コマンド ライン インターフェイス (CLI) アプリの構築と、Microsoft Entra 管理センターを使用した認証の準備を行うシリーズのパート 1 です。 構築するクライアント アプリケーションでは、セキュリティで保護されたユーザー認証用に、Proof Key for Code Exchange (PKCE) を使用した [OAuth 2.0 認可コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)が使用されます。

このチュートリアルでは、次の操作を行います。

- 新しい Node.js アプリケーション プロジェクトを作成する
- アプリの依存関係をインストールする
- MSAL 構成オブジェクトを作成する

### 前提条件

- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **モバイル アプリケーションとデスクトップ アプリケーション**のプラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost`
- Microsoft Entra 管理センターでアプリをユーザー フローに関連付けます。 このユーザー フローは、複数のアプリケーションで使用できます。 詳細については、「 [外部テナント内のアプリのセルフサービス サインアップ ユーザー フローを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers) 」および「 [ユーザー フローにアプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)」を参照してください。
- React アプリケーションをサポートする統合開発環境 (IDE) であればどれでも使用できますが、このチュートリアルでは [Visual Studio Code](https://visualstudio.microsoft.com/downloads/) を使用します。
- [Node.js](https://nodejs.org)。

### パブリック クライアント フローを有効にする

アプリをパブリック クライアントとして識別するには、次の手順に従います。

1. **[管理]** で、 **[認証]** を選択します。
2. **[詳細設定]** で、**[パブリック クライアント フローを許可する]** に対して **[はい]** を選択します。
3. **[保存]** を選択して変更を保存します。

### 新しい Node.js アプリケーション プロジェクトを作成する

ここでは、新しい Node.js CLI アプリを一から構築します。 完成したコード サンプルを学習用に使用したい場合は、GitHub から[サンプル Node.js CLI アプリケーション](https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial/archive/refs/heads/main.zip)をダウンロードしてください。

Node.js CLI アプリケーションを一から構築するには、次の手順に従います。

1. アプリケーションをホストするフォルダーを作成し、名前を付けます (*ciam-sign-in-node-cli-app* など)。
2. ターミナルでプロジェクト ディレクトリ (`cd ciam-sign-in-node-cli-app` など) に移動し、`npm init` を使用してプロジェクトを初期化します。 これにより、すべての npm パッケージへの参照が含まれる *package.json* ファイルがプロジェクト フォルダーに作成されます。
3. プロジェクトのルート ディレクトリで、*authConfig.js* と *index.js* という名前の 2 つのファイルを作成します。 *authConfig.js* ファイルには認証構成パラメーターが含まれ、*index.js* にはアプリの認証ロジックが保持されます。

ファイルを作成すると、次のプロジェクト構造が出来上がっているはずです。

```
ciam-sign-in-node-cli-app/
   ├── authConfig.js
   └── index.js
   └── package.json
```

### アプリの依存関係をインストールする

ビルドするアプリケーションでは、MSAL Node を使用してユーザーをサインインさせます。 MSAL Node パッケージを依存関係としてプロジェクトにインストールするには、プロジェクト ディレクトリでターミナルを開き、次のコマンドを実行します。

```powershell
npm install @azure/msal-node   
```

また `open` パッケージもインストールします。これにより、Node.js アプリは Web ブラウザーで URL を開くことができます。

```powershell
npm install open
```

### MSAL 構成オブジェクトを作成する

コード エディターで、MSAL オブジェクト構成パラメーターを保持する *authConfig.js* を開き、次のコードを追加します。

```javascript

const { LogLevel } = require('@azure/msal-node');

const msalConfig = {
    auth: {
        clientId: 'Enter_the_Application_Id_Here', 
        authority: `https://Enter_the_Tenant_Subdomain_Here.ciamlogin.com/`, 
    },
    system: {
        loggerOptions: {
            loggerCallback(loglevel, message, containsPii) {
                // console.log(message);
            },
            piiLoggingEnabled: false,
            logLevel: LogLevel.Verbose,
        },
    },
};
```

`msalConfig` オブジェクトには、認証フローの動作をカスタマイズするために使用できる一連の構成オプションが含まれています。 この構成オブジェクトは、作成時にパブリック クライアント アプリケーションのインスタンスに渡されます。 *authConfig.js* ファイルで、次のプレースホルダーを見つけます。

- `Enter_the_Application_Id_Here` を、前に登録したアプリのアプリケーション (クライアント) ID に置き換えます。
- `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナントのドメイン名がない場合は、[テナントの詳細を読み取る](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)方法を確認してください。

構成オブジェクトには、次の 2 つのオプションを含む `LoggerOptions` も追加します。

- `loggerCallback` - MSAL ステートメントのログ記録を処理するコールバック関数
- `piiLoggingEnabled` - true に設定すると、個人を特定できる情報 (PII) のログ記録を有効にする構成オプション。 このアプリでは、このオプションを false に設定します。

`msalConfig` オブジェクトを作成した後、アプリケーションで必要なスコープを含む `loginRequest` オブジェクトを追加します。 スコープでは、アプリケーションがユーザー リソースに対して持つアクセス レベルを定義します。 サンプル スニペットのスコープ配列には値はありませんが、既定では、MSAL は OIDC スコープ (openid、プロファイル、メール) を任意のログイン要求に追加します。 ユーザーは、サインイン中にこれらのスコープに同意するように求められます。 `loginRequest` オブジェクトを作成するには、*authConfig.js* に次のコードを追加します。

```javascript
const loginRequest = {
    scopes: [],
};
```

*authcConfig.js* で `msalConfig` オブジェクトと `loginRequest` オブジェクトをエクスポートし、次のコードを追加すると必要に応じてアクセスできるようになります。

```javascript
module.exports = {
    msalConfig: msalConfig,
    loginRequest: loginRequest,
};
```

カスタム ドメインを使用して、認証 URL を完全にブランド化します。 ユーザーの視点から見ると、認証プロセスの間、ユーザーは *ciamlogin.com* ドメイン名にリダイレクトされず、あなたのドメインにとどまります。

カスタム ドメインを使用するには、以下の手順を実行します。

1. 「[外部テナント内のアプリに対するカスタム URL ドメインの有効化](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-url-domain)」の手順を使用し、外部テナントに対してカスタム URL ドメインを有効にします。
2. *authConfig.js* ファイルで、`auth` オブジェクトを見つけて、次のようにします。

    1. `authority` プロパティの値を *https://Enter\_the\_Custom\_Domain\_Here/Enter\_the\_Tenant\_ID\_Here* に更新します。 `Enter_the_Custom_Domain_Here` を実際のカスタム URL ドメインに、`Enter_the_Tenant_ID_Here` を実際のテナント ID に置き換えます。 テナント ID がわからない場合は、[テナントの詳細を読み取る](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)方法を確認してください。
    2. `knownAuthorities` という値を持つ  プロパティを追加します。

*authConfig.js* ファイルを変更した後、カスタム URL ドメインが *login.contoso.com* で、テナント ID が *aaaabbbb-0000-cccc-1111-dddd2222eeee* であるとすると、ファイルは次のスニペットのようになるはずです。

```JavaScript
//...
const msalConfig = {
    auth: {
        authority: process.env.AUTHORITY || 'https://login.contoso.com/aaaabbbb-0000-cccc-1111-dddd2222eeee', 
        knownAuthorities: ["login.contoso.com"],
        //Other properties
    },
    //...
};
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-cli-app-node-sign-in-sign-out"} -->
## チュートリアル: Node.js CLI アプリケーションでユーザーを認証する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-cli-app-node-sign-in-sign-out
- Service: identity-platform
- Article date: 2025-04-16
- Summary: 外部テナントに登録されている Node.js CLI アプリケーションでユーザーを認証する方法について説明します

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルは、Node.js コマンド ライン インターフェイス (CLI) アプリの構築と、Microsoft Entra 管理センターを使用した認証の準備を行うチュートリアル シリーズの最後の部分です。 [このシリーズのパート 1](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-cli-app-node-sign-in-prepare-app) では、Node.js CLI アプリケーションを構築し、認証用に準備しました。 このチュートリアルでは、作成した Node.js CLI アプリケーションでユーザーを認証する方法について説明します。

- 必要なモジュールと MSAL 構成をインポートする
- パブリック クライアント アプリケーションのインスタンスを作成する
- ユーザー操作用のブラウザー ウィンドウを起動する
- トークン要求オブジェクトを作成する
- トークンを取得する関数を定義する
- 認証フローの開始
- Node.js CLI アプリケーションを構成する
- Node.js CLI アプリケーションを実行してテストする

### MSAL と構成をインポートする

ビルドする CLI アプリケーションでは、Node 用の Microsoft Authentication Library を使用してユーザーを認証します。 `MSAL Node` パッケージと前のチュートリアルで定義した `msalConfig` 構成および `loginRequest` 構成をインポートするには、次のコードを *index.js* に追加します。

```javascript
const { PublicClientApplication, InteractionRequiredAuthError } = require('@azure/msal-node');
const open = require('open');
const { msalConfig, loginRequest } = require('./authConfig');
```

このコード スニペットでは、`PublicClientApplication` パッケージから `InteractionRequiredAuthError` クラスと `@azure/msal-node` クラスをインポートします。 また、アプリがユーザー操作のためにブラウザー ウィンドウを開くことができるようにする `open` パッケージもインポートします。

### PublicClientApplication オブジェクトのインスタンスを作成する

MSAL Node を使用するには、まず [`PublicClientApplication`](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/publicclientapplication) オブジェクトを使用して `msalConfig` オブジェクトのインスタンスを作成する必要があります。 初期化された `PublicClientApplication` オブジェクトは、ユーザーの認証とアクセス トークンの取得に使用されます。

*index.js* で、次のコードを追加してパブリック クライアント アプリケーションを初期化します。

```javascript
const pca = new PublicClientApplication(msalConfig);
```

### ユーザー操作用のブラウザー ウィンドウを起動する

ユーザーがサインインしてアプリに必要なスコープに同意するようにするには、次に示すように、ユーザー操作用にブラウザー ウィンドウを開く `openBrowser` 関数を定義します。

```javascript
const openBrowser = async (url) => {
    open(url);
};
```

### トークン要求オブジェクトを作成する

次に、次のコードを `tokenRequest` に追加し、`loginRequest` のプロパティ (*authConfig.js* からインポート) と `openBrowser` 関数を組み合わせて  オブジェクトを作成します。

```javascript
const tokenRequest = {
    ...loginRequest,
    openBrowser,
    successTemplate: '<h1>Successfully signed in!</h1> <p>You can close this window now.</p>',
    errorTemplate:
        '<h1>Oops! Something went wrong</h1> <p>Navigate back to the Electron application and check the console for more information.</p>',
};
```

`successTemplate`と `errorTemplate` は、認証後にメッセージを表示するために使用される HTML テンプレートです。

### トークンを取得する関数を定義する

アプリケーションがユーザーのアクセス トークンを取得するために使用する `acquireToken` 関数を作成するには、次のコードを *index.js*に追加します。

```javascript
const acquireToken = async () => {
    const accounts = await pca.getTokenCache().getAllAccounts();
    if (accounts.length === 1) {
        // Try to acquire token silently for the single account.
        // If silent acquisition fails, use interactive authentication.
        const silentRequest = {
            account: accounts[0],
        };
        return pca.acquireTokenSilent(silentRequest).catch((e) => {
            if (e instanceof InteractionRequiredAuthError) {
                return pca.acquireTokenInteractive(tokenRequest);
            }
        });
    } else if (accounts.length > 1) {
        // Multiple accounts found. Prompt the user to select an account.
        accounts.forEach((account) => {
            console.log(account.username);
        });
        return Promise.reject('Multiple accounts found. Please select an account to use.');
    } else {
        // No account found. Use interactive authentication.
        return pca.acquireTokenInteractive(tokenRequest);
    }
};
```

このコード スニペットでは、`acquireToken` 関数は 1 つのアカウントのアクセス トークンを自動的に取得しようとします。失敗した場合は対話型認証を使用します。 複数のアカウントが見つかった場合はユーザーにアカウントの選択を求め、アカウントが見つからない場合は対話型認証を開始して、ユーザーにサインインと同意を求めます。

### 認証フローの開始

最後に、次のコードを `acquireToken()`に追加して、認証フローを開始する  関数を呼び出します。

```javascript
acquireToken()
    .then((response) => {
        console.log(response);
    })
    .catch((e) => {
        console.error(e);
        process.exit(1);
    });
```

`acquireToken()` の呼び出しが成功すると、アクセス トークンを含む応答がコンソールにログされます。 認証中にエラーが発生すると、エラーがログされ、プロセスがエラー コードで終了します。

### サンプル Node.js CLI アプリケーションを実行してテストする

Node.js CLI アプリケーションを実行してテストするには、*authConfig.js* ファイル内のプレースホルダー値が Microsoft Entra アプリ登録の詳細に置き換えられていることを確認します。 これで、次の手順に従ってビルドしたアプリケーションをテストできます。

1. ご利用のターミナルで、次のコマンドを実行します。

    ```powershell
    cd 1-Authentication\6-sign-in-node-cli-app\App
    npm start
    ```
2. ブラウザーが自動的に開き、次のようなページが表示されるはずです。

    [Image: Node CLI アプリケーションのサインイン ページのスクリーンショット。]
3. サインイン ページで、**[メール アドレス]** を入力します。 アカウントをお持ちでない場合は、**[アカウントをお持ちではない場合、作成できます]** を選択します。これで、サインアップ フローが開始されます。
4. サインアップ オプションを選択した場合は、メール、ワンタイム パスコード、新しいパスワード、その他のアカウントの詳細を入力すると、サインアップ フロー全体が完了します。 サインアップ フローを完了してサインインすると、次のスクリーンショットのようなページが表示されます。

    [Image: Node CLI アプリケーションのサインイン済みユーザーを示すスクリーンショット。]
5. ターミナルに戻り、Microsoft Entra によって返される ID トークン要求を含む認証情報を確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-custom-authentication-extension-account-recovery"} -->
## アカウント回復要求の検証用のカスタム認証拡張機能を作成する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-custom-authentication-extension-account-recovery
- Service: identity-platform
- Article date: 2026-04-22
- Summary: Azure関数と REST API を使用して、Microsoft Entra アカウントの復旧中に検証済み ID 要求を検証するカスタム認証拡張機能を設定する方法について説明します。

この記事では、Microsoft Entraアカウントの復旧中に検証済みIDの要求を検証するカスタム認証拡張機能を設定する方法について説明します。 アカウント回復フローでは、イベント リスナーを使用して要求検証プロセスを拡張できます。

- **OnVerifiedIdClaimValidation** イベントは、ユーザーがアカウントの回復を開始し、検証済み ID 要求を提示するときに発生します。 権限のあるデータ ソース (人事システム、アプリケーション データベース、従業員レコード システムなど) に対する要求の検証や、合格または失敗の決定の返しなどのアクションを追加できます。

カスタム認証拡張機能を作成するだけでなく、イベントに対して実行するワークフロー アクションを定義する REST API を作成する必要があります。 この記事では、サンプル リポジトリからデプロイされた C# Azure関数の使用を簡単に開始する方法について説明します。 Azure Functionsでは、最初に仮想マシン (VM) を作成したり、Web アプリケーションを発行したりする必要なく、サーバーレス環境でコードを実行できます。

### 前提条件

- Azure サブスクリプション。 既存のAzure アカウントがない場合は、[無料試用版](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)にサインアップします。
- [account 回復が有効になっているMicrosoft Entra ID テナント](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-account-recovery-enable)。
- 少なくとも [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) のロールと [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) のロール。

### どのように機能するのか

アカウントの回復中に、すべての認証方法を失ったユーザーは、ID を再確立する必要があります。 カスタム認証拡張機能は、このフローに要求検証ステップを追加します。

[Image: アカウントの回復フローを示すアーキテクチャ図: ユーザーが回復を開始します。 イベント リスナーをトリガー Microsoft Entra ID、カスタム認証拡張機能は REST API エンドポイント (Logic Apps または Azure Functions) を呼び出します。これにより、外部システムに対して検証が行われ、応答が処理され、TAP コードが表示されます。]

`OnVerifiedIdClaimValidation` イベントは、復旧パイプラインの事前証明フックです。 Microsoft Entra ID が復旧を進める前に、カスタム検証ロジック (HR の照会、外部データベース チェック、パートナー信頼の検証) をプラグインできます。

### 手順 1: カスタム認証拡張機能 REST API を作成する (Azure関数アプリ)

この手順では、カスタム認証拡張機能の REST API として機能するAzure関数アプリをデプロイします。 この関数は、アカウントの復旧中にMicrosoft Entra IDから検証済み ID 要求を受け取り、権限のあるデータ ソースに対して検証します。

サンプル関数は、ワンクリックデプロイとして使用できます。 Application Insights とストレージ アカウントを使用して、従量課金プランに Azure Function App (.NET 10 の分離ワーカー モデル) をデプロイします。

#### 1.1 Azure関数をデプロイする

1. [Azure] ボタンを選択します。
2. デプロイ パラメーターを入力します。

    | パラメーター | Description |
    | --- | --- |
    | **関数アプリ名** | Function App のグローバルに一意の名前 (たとえば、 `contoso-recovery-claims`)。 |
    | **リポジトリ URL** | GitHub リポジトリの URL が事前に入力されています。 |
    | **ブランチ** | `main` |
    | **ストレージ アカウントの種類** | `Standard_LRS` (既定値)。 |
    | **場所** | ユーザーの近くのAzureリージョンを選択します。 |

    ここでは省略可能なパラメーターを空白のままにします。次の手順で構成します。
3. [ **確認と作成**]、[ **作成**] の順に選択します。
4. デプロイが完了するまで待ちます (通常は 2 ~ 3 分)。

Note

ARM テンプレートは、 **従量課金プラン** (Y1/動的レベル) に Function App をデプロイします。 スケーリングは完全に自動化されます。Azureは、受信要求ボリュームに基づいてインスタンスを追加および削除します。 テストまたは開発の場合は、コールド スタートを回避するために **Premium プラン** (EP1 以降) に切り替えることができます。 プランを変更するには、Function App &gt;**App Service プラン**&gt;**Change App Service プラン**に移動&gt;目的のレベルでプランを選択または作成します。

#### 1.2 関数の URL を取得する

1. デプロイが完了したら、Function App リソースに移動します。
2. [ **関数**&gt;**CustomClaimMatching**&gt;**関数の URL を取得** を選択します。
3. URL をコピーします。 `https://<your-function-app>.azurewebsites.net/api/CustomClaimMatching` のように表示されます。

#### 1.3 テスト データ ソースを構成する

テスト用に、この関数は、データ ソースとして読み取りアクセス権を持つ任意の Web サーバーでホストされているExcel ファイルをサポートします。 運用環境では、HR API プロバイダーに切り替えることができます (運用 への切り替え: HR API プロバイダーの使用を参照)。

1. リポジトリの [SampleData フォルダー](https://github.com/Azure-Samples/active-directory-verifiable-credentials-dotnet/tree/main/7-AccountRecovery-ClaimsMatching/SampleData)からサンプル Excel ファイルをダウンロードするか、次の列を使用して独自のファイルを作成します。

    | 社員ID | UPN | firstName | lastName | fullName | dateOfBirth | ドキュメントタイプ | documentId | 文書有効期限 |
    | --- | --- | --- | --- | --- | --- | --- | --- | --- |
    | E001 | user@contoso.com | John | Doe | John Doe | 1990-01-15 | Passport | AB123456 | 2028-01-15 |

    Tip

    新しい要求を追加するには、要求名をヘッダーとして持つ列を追加します。 この関数は、要求からの要求キーと列ヘッダーを動的に照合します。コードの変更は必要ありません。

    Note

    既定では、関数アプリは **ドキュメント番号** (`documentId`) 要求のみを検証します。 `firstName`、`dateOfBirth`、`employeeId` などの追加の要求を照合するには、Azure関数コードで検証ロジックを更新します。
2. Web サーバー、ファイル共有、またはクラウド ストレージ サービス (Azure Blob Storage、SharePoint、HTTP アクセス可能な場所など) にファイルをアップロードします。
3. ファイルの直接ダウンロード URL を取得します。 対話型サインインなしで URL にアクセスできる必要があります。
4. Azure ポータルで、Function App の&gt;**設定**&gt;**環境変数**に移動します。 デプロイ テンプレートでは、これらの変数が既定値で事前に作成されます。 次のことを確認して更新します。

    | 名前 | アクション | 価値 |
    | --- | --- | --- |
    | `ClaimsValidator__Provider` | **確認** ( `excel`として事前に作成済み) | `excel` |
    | `Excel__ShareUrl` | **更新**（空のまま事前に作成された） | Excel ファイルの直接ダウンロード URL。 |
    | `Excel__SheetName` | **確認** ( `Sheet1`として事前に作成済み) | `Sheet1` (またはワークシート名)。 |
5. **[適用]** を選んでから、**[確認]** を選びます。

### 手順 2: カスタム認証拡張機能を作成して登録する

この手順では、Azure関数の呼び出しに使用Microsoft Entra IDカスタム認証拡張機能を登録します。 カスタム認証拡張機能には、REST API エンドポイント、REST API から解析する要求検証アクション、および REST API に対する認証方法に関する情報が含まれています。

Note

最大 100 個のカスタム拡張機能ポリシーを使用できます。 ただし、**OnVerifiedIdClaimValidation** イベントの種類では、テナントごとに **1 つの**カスタム認証拡張機能のみが許可されます。

## [Azure Portal](#tab/azure-portal)
1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)および[認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Custom 認証拡張機能**に移動します。
3. [ **カスタム拡張機能の作成] を選択します**。
4. **[基本**] で **OnVerifiedIdClaimValidation** イベントを選択し、[**次へ**] を選択します。
5. **エンドポイント構成**で、次のプロパティを入力します。

    - **名前：** カスタム認証拡張機能の名前。 たとえば、「 `Account Recovery Claims Validation` 」のように入力します。
    - **ターゲット URL:** Azure Function の関数 URL。 例えば： `https://<your-function-app>.azurewebsites.net/api/CustomClaimMatching`
    - **説明：** 拡張機能の説明。 たとえば、「 `Validates VID claims against HR data during account recovery` 」のように入力します。
6. **次へ**を選択します。
7. **API 認証**で、[**新しいアプリ登録の作成**] オプションを選択して、関数アプリを表すアプリ登録を作成します。
8. アプリに名前を付けます (例: `Azure Functions authentication events API`)。
9. **次へ**を選択します。
10. [ **作成]** を選択すると、カスタム認証拡張機能と関連付けられているアプリケーションの登録が作成されます。

## [Microsoft Graph](#tab/microsoft-graph)
#### アプリケーションを登録する

1. カスタム認証拡張機能を管理するテナントがホーム テナントであるアカウントを使用して [Graph Explorer](https://aka.ms/ge) にサインインします。 このアカウントには、テナントでアプリケーション登録を作成および管理するための特権が必要です。
2. 次の要求を実行します。

    ```http
    POST https://graph.microsoft.com/v1.0/applications
    Content-type: application/json
    
    {
        "displayName": "authenticationeventsAPI"
    }
    ```
3. 応答から、 **id** と **appId** の値を記録します。 これらの値は、この記事の後半でそれぞれ `{authenticationeventsAPI_ObjectId}` と `{authenticationeventsAPI_AppId}` として参照されます。

#### authenticationeventsAPI アプリ登録のためのサービスプリンシパルを作成する

Graph エクスプローラーで、次の要求を実行します。 `{authenticationeventsAPI_AppId}`を、前の手順で記録した **appId** 値に置き換えます。

```http
POST https://graph.microsoft.com/v1.0/servicePrincipals
Content-type: application/json

{
    "appId": "{authenticationeventsAPI_AppId}"
}
```

#### アプリ ID URI、アクセス トークンのバージョン、必要なリソース アクセスを設定する

新しく作成したアプリケーションを更新して、アプリケーション ID URI の値、アクセス トークンのバージョン、必要なリソース アクセスを設定します。

Graph エクスプローラーで、次の要求を実行します。

- `identifierUris` プロパティでアプリケーション ID URI 値を設定します。 `{Function_Url_Hostname}`を、Azure関数 URL のホスト名 (例: `contoso-recovery-claims.azurewebsites.net`) に置き換えます。
- `{authenticationeventsAPI_AppId}`を、前に記録した **appId に**置き換えます。
- `{authenticationeventsAPI_ObjectId}`は、前に記録した **ID** に置き換えます。

`identifierUris`値の例は`api://contoso-recovery-claims.azurewebsites.net/aaaabbbb-0000-cccc-1111-dddd2222eeee`。 この値は、この記事の後半で `{functionApp_IdentifierUri}`の代わりに使用する際に書き留めます。

```http
PATCH https://graph.microsoft.com/v1.0/applications/{authenticationeventsAPI_ObjectId}
Content-type: application/json

{
    "identifierUris": [
        "api://{Function_Url_Hostname}/{authenticationeventsAPI_AppId}"
    ],
    "api": {
        "requestedAccessTokenVersion": 2,
        "acceptMappedClaims": null,
        "knownClientApplications": [],
        "oauth2PermissionScopes": [],
        "preAuthorizedApplications": []
    },
    "requiredResourceAccess": [
        {
            "resourceAppId": "00000003-0000-0000-c000-000000000000",
            "resourceAccess": [
                {
                    "id": "214e810f-fda8-4fd7-a475-29461495eb00",
                    "type": "Role"
                }
            ]
        }
    ]
}
```

#### カスタム認証拡張機能を登録する

次に、カスタム認証拡張機能を登録します。 Azure関数のアプリ登録と、Azure関数エンドポイント `{Function_Url}`に関連付けます。

1. Graph エクスプローラーで、次の要求を実行します。 `{Function_Url}`をAzure関数アプリの URL (たとえば、`https://contoso-recovery-claims.azurewebsites.net/api/CustomClaimMatching`) に置き換えます。 `{functionApp_IdentifierUri}`を、前の手順で設定した`identifierUris`値に置き換えます。

    - 委任された `CustomAuthenticationExtension.ReadWrite.All` 権限が必要です。

    ```http
    POST https://graph.microsoft.com/v1.0/identity/customAuthenticationExtensions
    Content-type: application/json
    
    {
        "@odata.type": "#microsoft.graph.onVerifiedIdClaimValidationCustomExtension",
        "displayName": "Account Recovery Claims Validation",
        "description": "Validates VID claims against HR data during account recovery",
        "endpointConfiguration": {
            "@odata.type": "#microsoft.graph.httpRequestEndpoint",
            "targetUrl": "{Function_Url}"
        },
        "authenticationConfiguration": {
            "@odata.type": "#microsoft.graph.azureAdTokenAuthentication",
            "resourceId": "{functionApp_IdentifierUri}"
        },
        "clientConfiguration": {
            "timeoutInMilliseconds": 2000,
            "maximumRetries": 1
        }
    }
    ```
2. 作成されたカスタム認証拡張機能の **ID** 値を記録します。 この記事の後半では、 `{customExtensionObjectId}`として使用します。

---

#### 2.1 管理者の同意を付与する

カスタム認証拡張機能が作成された後は、登録済みアプリに管理者の同意を付与します。これにより、カスタム認証拡張機能が API に対する認証を行えるようになります。

1. **Entra ID**&gt;**Enterprise apps**&gt;**Custom 認証拡張機能**に移動します。
2. 一覧からカスタム認証拡張機能を選択します。
3. [ **概要** ] タブで、[ **アクセス許可の付与** ] ボタンを選択して、登録済みのアプリに管理者の同意を与えます。 カスタム認証拡張機能では、`client_credentials` を使用して、`Receive custom authentication extension HTTP requests` アクセス許可を使用してAzure関数アプリに対する認証を行います。 **[Accept](https://learn.microsoft.com/ja-jp/entra/identity-platform/承認)** を選択します。

### 手順 3: カスタム認証拡張機能をアカウント回復ポリシーに追加する

次に、カスタム認証拡張機能を ID 検証プロファイルのアカウント検証設定に関連付け、復旧フロー中に呼び出されるようにします。

## [Azure Portal](#tab/azure-portal)
1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)および[認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator)としてサインインします。
2. **Entra ID**&gt;**アカウント回復**&gt;**本人確認プロファイル**に移動します。
3. 更新する ID 検証プロファイルを選択します (または、新しく作成します)。
4. **[アカウントの検証**] ステップで、[**追加の要求の検証**] で **[有効]** を **[オン]** に切り替えます。
5. [ **選択した拡張機能** ] ドロップダウンで、手順 2 で作成したカスタム認証拡張機能 (`Account Recovery Claims Validation`) を選択します。

    [Image: カスタム認証拡張機能が選択された ID 検証プロファイルを示すスクリーンショット。]
6. [ **確認して最終処理**]、[ **保存]** の順に選択します。

## [Microsoft Graph](#tab/microsoft-graph)
カスタム認証拡張機能をアカウント回復フローにバインドする認証イベント リスナーを作成します。

1. カスタム認証拡張機能を管理するテナントがホーム テナントであるアカウントを使用して [Graph Explorer](https://aka.ms/ge) にサインインします。
2. 次の要求を実行します。 `{customExtensionObjectId}`を、手順 2 で記録したカスタム認証拡張機能の **ID** に置き換えます。

    - 委任された `EventListener.ReadWrite.All` 権限が必要です。

    ```http
    POST https://graph.microsoft.com/v1.0/identity/authenticationEventListeners
    Content-type: application/json
    
    {
        "@odata.type": "#microsoft.graph.onVerifiedIdClaimValidationListener",
        "handler": {
            "@odata.type": "#microsoft.graph.onVerifiedIdClaimValidationCustomExtensionHandler",
            "customExtension": {
                "id": "{customExtensionObjectId}"
            }
        }
    }
    ```
3. 応答の **ID** 値を記録します。 これは、アカウント回復イベント リスナーの `{eventListenerId}` です。

---

### 手順 4: 関数をテストする (認証を追加する前に)

認証を構成する前に (手順 5) 、関数の要求照合ロジックが直接呼び出されて機能することを確認します。 この時点で、関数には認証が構成されていない必要があります。EasyAuth ID プロバイダーは構成されておらず、 `EntraId__TenantId` / `EntraId__ClientId` 環境変数は空である必要があります。

#### 4.1 関数を直接テストする

Warnung

この手順では、認証が構成されていない状態で関数をテストします。 これは開発とテスト専用です。 運用環境では、認証なしで関数を公開しないでください。 運用環境にデプロイする前に、手順 5 を完了して認証を構成します。

Note

認証 ID プロバイダーが構成されていない場合でも、Azure Functionsには `Authorization: Bearer` ヘッダーが必要です。 ローカル テスト中に任意の値 (たとえば、 `Bearer test`) を渡すことができます。 運用環境では、Microsoft Entra IDは有効なトークンを自動的に提供します。

1. ターミナルを開き、次のコマンドを実行します。

    ```powershell
    $body = @'
    {
        "type": "microsoft.graph.authenticationEvent.verifiedIdClaimValidation",
        "source": "/tenants/<tenant-guid>/applications/<app-id>",
        "data": {
            "@odata.type": "microsoft.graph.onVerifiedIdClaimValidationCalloutData",
            "tenantId": "<tenant-guid>",
            "authenticationContext": {
                "correlationId": "00000000-0000-0000-0000-000000000001",
                "user": {
                    "userPrincipalName": "user@contoso.com"
                }
            },
            "verifiedIdClaimsContext": {
                "additionalInfo": {
                    "employeeId": "E001"
                },
                "claims": {
                    "firstName": "John",
                    "lastName": "Doe",
                    "dateOfBirth": "1990-01-15"
                }
            }
        }
    }
    '@
    
    (Invoke-WebRequest -Method Post `
      -Uri "https://<your-function-app>.azurewebsites.net/api/CustomClaimMatching" `
      -ContentType "application/json" `
      -Headers @{ Authorization = "Bearer test" } `
      -Body $body).Content
    ```
2. 応答を確認します。

    **成功した一致:**

    ```json
    {
        "data": {
            "@odata.type": "microsoft.graph.onVerifiedIdClaimValidationResponseData",
            "actions": [
                {
                    "@odata.type": "microsoft.graph.verifiedIdClaimValidation.pass"
                }
            ]
        }
    }
    ```

    **一致しませんでした**（一致しなかったクレームを返します）:

    ```json
    {
        "data": {
            "@odata.type": "microsoft.graph.onVerifiedIdClaimValidationResponseData",
            "actions": [
                {
                    "@odata.type": "microsoft.graph.verifiedIdClaimValidation.failed",
                    "failedClaims": ["dateOfBirth"]
                }
            ]
        }
    }
    ```

### 手順 5: Azure関数を保護する

カスタム認証拡張機能Microsoft Entra、サーバー間フローを使用して、HTTP `Authorization` ヘッダーで Azure 関数に送信されるアクセス トークンを取得します。 特に運用環境で関数をAzureに発行する場合は、承認ヘッダーで送信されたトークンを検証する必要があります。

Azure関数を保護するには、次の手順に従って、受信トークンを検証するためのMicrosoft Entra認証を *Azure Functions 認証イベント API* アプリケーションの登録と統合します。

#### 5.1 Azure関数に ID プロバイダーを追加する

1. [Azure ポータル](https://portal.azure.com)にサインインします。
2. 以前にデプロイした Function App に移動して選択します。
3. 左側のメニューで [ **認証** ] を選択します。
4. [ **ID プロバイダーの追加] を選択します**。
5. ID プロバイダーとして **Microsoft** を選択します。
6. [ **テナントの選択**] で、[ **Workforce configuration (current tenant)]\(ワークフォース構成 (現在のテナント)\)** を選択します。
7. **App registration** で、**このディレクトリ内の既存のアプリ登録を選択**を選択し、手順 2 で作成した *Azure Functions 認証イベント API* アプリの登録を選択します。
8. [ **クライアント シークレットの有効期限**] で、有効期限を選択します。
9. [ **サポートされているアカウントの種類**] で、[ **現在のテナント - シングル テナント**] を選択します。
10. **追加のチェック**の下で次の手順を実行します。
    - **Client アプリケーション要件:** **特定のクライアント アプリケーションからの要求を許可**を選択し、`99045fe1-7639-4a75-9d4a-577b6ca3810f` を追加します (これは、Microsoft Entra のカスタム認証拡張機能のファーストパーティアプリ ID です)。
    - **ID 要件:** [ **任意の ID からの要求を許可する] を選択します**。
    - **テナントの要件:** [ **特定のテナントからの要求を許可する** ] を選択し、従業員テナント ID を入力します。
11. [ **認証されていない要求**] で、[ **HTTP 401 Unauthorized: APIに推奨]** を選択します。
12. **Issuer URL** に「`https://login.microsoftonline.com/{tenantId}/v2.0`」と入力します。ここで、`{tenantId}` はMicrosoft Entra テナントのテナント ID です。
13. **[トークン ストア**] オプションの選択を解除します。
14. **Add** を選択して、Azure関数に認証を追加します。

#### 5.2 OpenID Connect ID プロバイダーを使用する

Azure関数が、カスタム認証拡張機能が登録されているテナントとは異なるテナントでホストされている場合は、代わりに次の手順に従います。

1. [Azure ポータル](https://portal.azure.com)にサインインし、Function App に移動します。
2. 左側のメニューで [ **認証** ] を選択します。
3. [ **ID プロバイダーの追加] を選択します**。
4. ID プロバイダーとして **OpenID Connect** を選択します。
5. `Contoso Microsoft Entra ID` などの名前を指定します。
6. **Metadata エントリ**で、**Document URL** に次の URL を入力し、`{tenantId}`をMicrosoft Entraテナント ID に置き換えます。

    ```
    https://login.microsoftonline.com/{tenantId}/v2.0/.well-known/openid-configuration
    ```
7. **App registration** で、手順 2 で作成した*Azure Functions認証イベント API* アプリ登録のアプリケーション ID (クライアント ID) を入力します。
8. Microsoft Entra 管理センターで、Azure Functions 認証イベント API アプリ登録の証明書 & シークレットクライアント シークレットに移動し、新しいクライアント シークレットを作成します。 シークレット値をコピーします。
9. Azure関数に戻り、**Client シークレット**を入力します。
10. **[トークン ストア**] オプションの選択を解除します。
11. OpenID Connect ID プロバイダーを追加するには、[ **追加]** を選択します。

### 手順 6: エンドツーエンドの復旧フローをテストする

1. Microsoft Entra 管理センターにサインインし、カスタム認証拡張機能がアクティブであり、ID 検証プロファイルに割り当てられているかどうかを確認します (手順 3)。
2. プライベート ブラウザー ウィンドウで、 https://myaccount.microsoft.com に移動し、アカウントの回復を開始します。
3. ID 証明の手順 (顔チェック、検証済み ID プレゼンテーション) を完了します。
4. `OnVerifiedIdClaimValidation` イベントが発生し、Azure関数が呼び出されます。 この関数は、データ ソースに対する要求を検証し、成功または失敗を返します。
5. **Application Insights** で&gt;関数が呼び出され、要求が検証されたことを**確認**します。

    ```kusto
    traces
    | where message has "claims" or message has "validation"
    | order by timestamp desc
    | take 20
    ```

### 運用環境に切り替える: HR API プロバイダーを使用する

運用環境では、Excel プロバイダーから、組織の HR REST エンドポイントを呼び出す HR API プロバイダーに切り替えます。

1. 関数アプリの **環境変数**で、次を更新します。

    | 名前 | 価値 |
    | --- | --- |
    | `ClaimsValidator__Provider` | `hrapi` |
    | `HrApi__BaseUrl` | HR API のベース URL (たとえば、 `https://hr.contoso.com/api`)。 |
    | `HrApi__AuthMode` | `apikey` または `oauth`。 |
    | `HrApi__ApiKey` | API キー (認証モード `apikey` 使用している場合)。 |
    | `HrApi__OAuthScope` | OAuth スコープ (認証モード `oauth` 使用している場合、たとえば、 `api://hr-api-app-id/.default`)。 |
2. `oauth`認証モードを使用する場合、関数は Function App のシステム割り当てマネージド ID を使用します。 マネージド ID に、HR API のアプリ登録に対する適切なアプリ ロールを付与します。
3. HR API では、次のコントラクトを実装する必要があります。

    **要求：**`POST {BaseUrl}/validate`

    ```json
    {
        "upn": "user@contoso.com",
        "employeeId": "E001",
        "claims": {
            "firstName": "John",
            "lastName": "Doe",
            "dateOfBirth": "1990-01-15"
        }
    }
    ```

    **応答 (パス):**

    ```json
    {
        "result": "pass"
    }
    ```

    **応答 (失敗):**

    ```json
    {
        "result": "fail",
        "failedClaims": ["dateOfBirth"]
    }
    ```

### Troubleshoot

| 症状 | 原因 | Resolution |
| --- | --- | --- |
| 関数が返す `401` | ベアラー トークンの検証に失敗したか、ID プロバイダーが構成されていません。 | Function App Authentication (手順 5) で ID プロバイダーが追加され、クライアント ID がアプリの登録と一致するかどうかを確認します。 |
| 関数は `200` を返しますが、復旧フローは失敗します | 応答スキーマの不一致。 | 関数が応答で正しい `@odata.type` 値を返すかどうかを確認します。 |
| 要求は常に検証に失敗する | データ ソースが一致しません。 | Excel列ヘッダーまたは HR API 応答が要求キーと正確に一致するかどうかを確認します (大文字と小文字は区別されません)。 |
| 復旧中に呼び出されない関数 | イベント リスナーが構成されていません。 | カスタム認証拡張機能がアカウント回復ポリシーに割り当てられているかどうかを確認します (手順 3)。 |
| Excelでデータが読み込まれない | 共有 URL が無効または期限切れです。 | Excel ファイルを再共有し、環境変数で `Excel__ShareUrl` を更新します。 |
| 管理者の同意が付与されていません | 拡張機能呼び出しのアクセス許可エラー。 | カスタム認証拡張機能 &gt;**Overview**&gt;**アクセス権を付与**&gt;**承認** に移動します。 |

トラブルシューティングのガイダンスの詳細については、「 [カスタム認証拡張機能 API のトラブルシューティング」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-troubleshoot)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-daemon-node-call-api-build-app"} -->
## チュートリアル: Node.js デーモン アプリケーションから Web API を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-daemon-node-call-api-build-app
- Service: identity-platform
- Article date: 2024-08-27
- Summary: Node.js クライアント デーモン アプリを準備し、Web API を呼び出すためのアクセス トークンを取得するように構成する方法について説明します。

このチュートリアルでは、 [Open Authorization (OAuth) 2.0 クライアント資格情報付与フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)を使用して Node.js デーモン クライアント アプリを準備し、Web API を呼び出すためのアクセス トークンを取得するように構成する方法について説明します。 [Microsoft Authentication Library (MSAL) for Node](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node) を使用して Node.js アプリケーションを構築し、アプリへの承認の追加を簡略化します。

このチュートリアルでは、

- Web API のアプリ ロールを構成する
- デーモン アプリにアクセス許可を付与する
- Visual Studio Code で Node.js アプリを作成し、依存関係をインストールします。
- Node.js アプリが Web API を呼び出すためのアクセス トークンを取得できるようにします。

### 前提条件

- [組織のディレクトリと個人用](https://entra.microsoft.com)の Microsoft アカウントのアカウント用に構成された、*Microsoft Entra 管理センター*に新しいクライアント アプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
    - ディレクトリ (テナント) ドメイン名 ( *contoso.onmicrosoft.com* や *contoso.com* など)。
- クライアント アプリの登録にクライアント シークレットを追加します。 運用アプリではクライアント シークレットを使用**しないでください**。 代わりに、証明書またはフェデレーション資格情報を使用してください。 詳細については、「 [アプリケーションに資格情報を追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials?tabs=client-secret)。
- 実行中で要求を受け入れる準備ができている保護された Web API。 Web API が HTTPS 経由で次のエンドポイントを公開していることを確認します。
    - `GET /api/todolist`: すべての todo を取得します。
    - `POST /api/todolist` としてTODOを追加。
- [Node.js](https://nodejs.org)。
- React アプリケーションをサポートする統合開発環境 (IDE) であればどれでも使用できますが、このチュートリアルでは [Visual Studio Code](https://visualstudio.microsoft.com/downloads/) を使用します。

### アプリ ロールを構成する

API は、クライアント アプリがアクセス トークンをそれ自体として取得するために、アプリケーションに対して少なくとも 1 つのアプリ ロール ( [アプリケーションアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)とも呼ばれます) を発行する必要があります。 アプリケーションのアクセス許可は、クライアント アプリケーションが自身として正常に認証できるようにして、ユーザーをサインインさせる必要がないようにする場合に、API が発行するアクセス許可の種類です。 アプリケーションのアクセス許可を発行するには、次の手順に従います。

1. **[アプリの登録]** ページから、作成したアプリケーション (*ciam-ToDoList-api* など) を選択して、その **[概要]** ページを開きます。
2. **[管理]** で、**[アプリ ロール]** を選択します。
3. **[アプリ ロールの作成]** を選択し、次の値を入力し、**[適用]** を選択して変更を保存します:

    | プロパティ | 価値 |
    | --- | --- |
    | 表示名 | *ToDoList.Read.All* |
    | Allowed member types (許可されるメンバーの種類) | **アプリケーション** |
    | 価値 | *ToDoList.Read.All* |
    | 説明 | *'ToDoListApi' を使用して、アプリがすべてのユーザーの ToDo リストを読むことができるようにする* |
    | このアプリ ロールを有効にしますか? | オンのままにする |
4. もう一度 **[アプリ ロールの作成]** を選択し、2 番目のアプリ ロールに次の値を入力し、**[適用]** を選択して変更を保存します:

    | プロパティ | 価値 |
    | --- | --- |
    | 表示名 | *ToDoList.ReadWrite.All* |
    | Allowed member types (許可されるメンバーの種類) | **アプリケーション** |
    | 価値 | *ToDoList.ReadWrite.All* |
    | 説明 | *'ToDoListApi' を使用して、アプリがすべてのユーザーの ToDo リストを読み書きできるようにする* |
    | このアプリ ロールを有効にしますか? | オンのままにする |

### idtyp トークン クレームを構成する

**idtyp** の省略可能な要求を追加すると、Web API がトークンが **アプリ** トークンなのか **アプリ + ユーザー** トークンなのかを判断するのに役立ちます。 **scp** と**ロール**要求の組み合わせを同じ目的で使用できますが、**idtyp** 要求を使用すると、アプリ トークンとアプリ + ユーザー トークンを区別する最も簡単な方法です。 たとえば、トークンがアプリ専用トークンの場合、この要求の値は *app* です。

### デーモン アプリに API のアクセス許可を付与する

1. **[アプリの登録]** ページから、作成したアプリケーション (*ciam-client-app* など) を選択します。
2. [**管理**] で **API 許可**を選択します。
3. **[構成されたアクセス許可]** の下で **[アクセス許可の追加]** を選択します。
4. **[所属する組織で使用している API]** タブを選択します。
5. API の一覧で、API (*ciam-ToDoList-api* など) を選択します。
6. **[アプリケーションのアクセス許可]** オプションを選択します。 このアプリは、ユーザーの代理としてではなくそれ自体がサインインするものであるため、このオプションを選びます。
7. アクセス許可の一覧から、**TodoList.Read.All と ToDoList.ReadWrite.All** を選択します (必要に応じて検索ボックスを使用してください)。
8. **[アクセス許可の追加]** ボタンを選択します。
9. この時点で、アクセス許可が正しく割り当てられました。 ただし、デーモン アプリはユーザーが対話することを許可しないため、ユーザー自身がこれらのアクセス許可に同意することはできません。 この問題に対処するには、管理者が次のように、テナント内のすべてのユーザーに代わってこれらのアクセス許可に同意する必要があります。

    1. **[&lt;テナント名&gt; に管理者の同意を与えます]** を選択してから、**[はい]** を選択します。
    2. **[最新の情報に更新]** を選択し、両方のアクセス許可の &lt; に "**&gt;テナント名 に付与されました**" と表示されていることを確認します。

### Node.js デーモン プロジェクトを作成する

`ciam-call-api-node-daemon`など、Node.js デーモン アプリケーションをホストするフォルダーを作成します。

1. ターミナルで、`cd ciam-call-api-node-daemon`などの Node デーモン アプリ フォルダーにディレクトリを変更し、`npm init -y`実行します。 このコマンドは、Node.js プロジェクトの既定の package.json ファイルを作成します。 このコマンドは、Node.js プロジェクトの既定の `package.json` ファイルを作成します。
2. 次のプロジェクト構造を実現するために、追加のフォルダーとファイルを作成します。

    ```text
        ciam-call-api-node-daemon/
        ├── auth.js
        └── authConfig.js
        └── fetch.js
        └── index.js 
        └── package.json
    ```

### アプリの依存関係をインストールする

ターミナルで、次のコマンドを実行して、`axios`、`yargs`、`@azure/msal-node` パッケージをインストールします。

```console
npm install axios yargs @azure/msal-node   
```

### MSAL 構成オブジェクトを作成する

コード エディターでファイル *authConfig.js* 開き、次のコードを追加します。

```javascript
require('dotenv').config();

/**
 * Configuration object to be passed to MSAL instance on creation.
 * For a full list of MSAL Node configuration parameters, visit:
 * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/configuration.md
 */    
const msalConfig = {
    auth: {
        clientId: process.env.CLIENT_ID || 'Enter_the_Application_Id_Here', // 'Application (client) ID' of app registration in Azure portal - this value is a GUID
        authority: process.env.AUTHORITY || 'https://Enter_the_Tenant_Subdomain_Here.ciamlogin.com/', // Replace "Enter_the_Tenant_Subdomain_Here" with your tenant subdomain
        clientSecret: process.env.CLIENT_SECRET || 'Enter_the_Client_Secret_Here', // Client secret generated from the app 
    },
    system: {
        loggerOptions: {
            loggerCallback(loglevel, message, containsPii) {
                console.log(message);
            },
            piiLoggingEnabled: false,
            logLevel: 'Info',
        },
    },
};    
const protectedResources = {
    apiToDoList: {
        endpoint: process.env.API_ENDPOINT || 'https://localhost:44351/api/todolist',
        scopes: [process.env.SCOPES || 'api://Enter_the_Web_Api_Application_Id_Here'],
    },
};

module.exports = {
    msalConfig,
    protectedResources,
};
```

`msalConfig` オブジェクトには、承認フローの動作をカスタマイズするために使用する一連の構成オプションが含まれています。

*authConfig.js* ファイルで、次の値を置き換えます。

- `Enter_the_Application_Id_Here` を、前の手順で登録したクライアント デーモン アプリのアプリケーション (クライアント) ID に置き換えます。
- `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。
- 前にコピーしたクライアント デーモン アプリのシークレット値を使用して `Enter_the_Client_Secret_Here` します。
- 前にコピーした Web API アプリのアプリケーション (クライアント) ID に `Enter_the_Web_Api_Application_Id_Here` を置き換えます。

`scopes`変数の`protectedResources` プロパティは、前提条件の一部として登録した Web API のリソース識別子 (アプリケーション ID URI) であることに注意してください。 完全なスコープ URI は、`api://Enter_the_Web_Api_Application_Id_Here/.default`のようになります。

### アクセス トークンを取得する

コード エディターでファイル *auth.js* 開き、次のコードを追加します。

```javascript
const msal = require('@azure/msal-node');
const { msalConfig, protectedResources } = require('./authConfig');
/**
 * With client credentials flows permissions need to be granted in the portal by a tenant administrator.
 * The scope is always in the format '<resource-appId-uri>/.default'. For more, visit:
 * https://docs.microsoft.com/azure/active-directory/develop/v2-oauth2-client-creds-grant-flow
 */
const tokenRequest = {
    scopes: [`${protectedResources.apiToDoList.scopes}/.default`],
};

const apiConfig = {
    uri: protectedResources.apiToDoList.endpoint,
};

/**
 * Initialize a confidential client application. For more info, visit:
 * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/initialize-confidential-client-application.md
 */
const cca = new msal.ConfidentialClientApplication(msalConfig);
/**
 * Acquires token with client credentials.
 * @param {object} tokenRequest
 */
async function getToken(tokenRequest) {
    return await cca.acquireTokenByClientCredential(tokenRequest);
}

module.exports = {
    apiConfig: apiConfig,
    tokenRequest: tokenRequest,
    getToken: getToken,
};
```

コード内:

- `tokenRequest` と `apiConfig` オブジェクトを準備します。 `tokenRequest` には、アクセス トークンを要求するスコープが含まれています。 スコープは `api://Enter_the_Web_Api_Application_Id_Here/.default`に似ています。 `apiConfig` オブジェクトには、Web API へのエンドポイントが含まれています。 OAuth 2.0 クライアント資格情報フロー の詳細を確認します。
- `msalConfig` オブジェクトを [ConfidentialClientApplication](https://learn.microsoft.com/ja-jp/javascript/api/%40azure/msal-node/confidentialclientapplication#constructors) クラスのコンストラクターに渡すことによって、機密クライアント インスタンスを作成します。

    ```javascript
    const cca = new msal.ConfidentialClientApplication(msalConfig);
    ```
- 次に、[acquireTokenByClientCredential](https://learn.microsoft.com/ja-jp/javascript/api/%40azure/msal-node/confidentialclientapplication#@azure-msal-node-confidentialclientapplication-acquiretokenbyclientcredential) 関数を使用してアクセス トークンを取得します。 このロジックは、`getToken` 関数に実装します。

    ```javascript
    cca.acquireTokenByClientCredential(tokenRequest);
    ```

アクセス トークンを取得したら、API の呼び出しに進むことができます。

### API を呼び出す

コード エディターでファイル *fetch.js* 開き、次のコードを追加します。

```javascript
const axios = require('axios');

/**
 * Calls the endpoint with authorization bearer token.
 * @param {string} endpoint
 * @param {string} accessToken 
 */
async function callApi(endpoint, accessToken) {

    const options = {
        headers: {
            Authorization: `Bearer ${accessToken}`
        }
    };

    console.log('request made to web API at: ' + new Date().toString());

    try {
        const response = await axios.get(endpoint, options);
        return response.data;
    } catch (error) {
        console.log(error)
        return error;
    }
};

module.exports = {
    callApi: callApi
};
```

このコードでは、アクセス トークンをベアラー トークンとして要求 `Authorization` ヘッダーに渡すことによって、Web API を呼び出します。

```javascript
 Authorization: `Bearer ${accessToken}`
```

前の手順「アクセス トークンを取得する」で取得したアクセス トークンを使用します。

Web API は要求を受け取ると評価し、それがアプリケーション要求であると判断します。 アクセス トークンが有効な場合、Web API は要求されたデータを返します。 それ以外の場合、API は `401 Unauthorized` HTTP エラーを返します。

### デーモン アプリの最終処理

コード エディターでファイル *index.js* 開き、次のコードを追加します。

```javascript
#!/usr/bin/env node

// read in env settings

require('dotenv').config();

const yargs = require('yargs');
const fetch = require('./fetch');
const auth = require('./auth');

const options = yargs
    .usage('Usage: --op <operation_name>')
    .option('op', { alias: 'operation', describe: 'operation name', type: 'string', demandOption: true })
    .argv;

async function main() {
    console.log(`You have selected: ${options.op}`);

    switch (yargs.argv['op']) {
        case 'getToDos':
            try {
                const authResponse = await auth.getToken(auth.tokenRequest);
                const todos = await fetch.callApi(auth.apiConfig.uri, authResponse.accessToken);                
            } catch (error) {
                console.log(error);
            }

            break;
        default:
            console.log('Select an operation first');
            break;
    }
};

main();
```

このコードは、アプリへのエントリ ポイントです。 Node.js アプリの [yargs JavaScript](https://www.npmjs.com/package/yargs) コマンドライン引数解析ライブラリを使用して、アクセス トークンを対話形式でフェッチしてから API を呼び出します。 前に定義した `getToken` 関数と `callApi` 関数を使用します。

```javascript
const authResponse = await auth.getToken(auth.tokenRequest);
const todos = await fetch.callApi(auth.apiConfig.uri, authResponse.accessToken);                
```

### デーモン アプリと API の実行とテスト

この時点で、クライアント デーモン アプリと Web API をテストする準備ができました。

1. [ASP.NET Web API のセキュリティ](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-api-dotnet-core-build-app)保護に関するチュートリアルで学習した手順を使用して、Web API を開始します。 これで、Web API でクライアント要求を処理する準備ができました。 `44351` ファイルで指定されているポート  で Web API を実行しない場合は、正しい Web API のポート番号を使用するように *authConfig.js* ファイルを更新してください。
2. ターミナルで、デーモン Node.js アプリ (`ciam-call-api-node-daemon`など) が含まれているプロジェクト フォルダーにいることを確認し、次のコマンドを実行します。

    ```console
    node . --op getToDos
    ```

デーモン アプリと Web API が正常に実行された場合は、次の JSON 配列と同様に、Web API エンドポイント `todos` 変数によって返されるデータがコンソール ウィンドウに表示されます。

```json
{
    id: 1,
    owner: '3e8....-db63-43a2-a767-5d7db...',
    description: 'Pick up grocery'
},
{
    id: 2,
    owner: 'c3cc....-c4ec-4531-a197-cb919ed.....',
    description: 'Finish invoice report'
},
{
    id: 3,
    owner: 'a35e....-3b8a-4632-8c4f-ffb840d.....',
    description: 'Water plants'
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-desktop-app-maui-sign-in-prepare-app"} -->
## チュートリアル: .NET MAUI シェル アプリを作成し、MSAL SDK を追加し、イメージ リソースを含める - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-desktop-app-maui-sign-in-prepare-app
- Service: identity-platform
- Article date: 2025-04-16
- Summary: このチュートリアルでは、.NET MAUI シェル アプリを作成し、MSALClient ヘルパーを使用して MSAL SDK のサポートを追加し、イメージ リソースを含める方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルは、.NET マルチプラットフォーム アプリ UI (.NET MAUI) シェル アプリを作成する方法を示すシリーズのパート 1 です。 このチュートリアルでは、.NET MAUI シェル アプリを作成し、カスタム Microsoft Authentication Library (MSAL) クライアント ヘルパーを追加して MSAL SDK を初期化し、必要なライブラリをインストールし、イメージ リソースを含める方法について説明します。

このチュートリアルでは、次の操作を行います。

- .NET MAUI シェル アプリを作成します。
- MSAL ヘルパー クラスを使用して MSAL SDK のサポートを追加します。
- 必要なパッケージをインストールします。
- イメージ リソースを追加します。

### 前提条件

- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- Microsoft Entra 管理センターでアプリをユーザー フローに関連付けます。 このユーザー フローは、複数のアプリケーションで使用できます。 詳細については、「 [外部テナント内のアプリのセルフサービス サインアップ ユーザー フローを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers) 」および「 [ユーザー フローにアプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)」を参照してください。
- [.NET 7.0 SDK (英語)](https://dotnet.microsoft.com/download/dotnet/7.0)
- MAUI ワークロードがインストールされている [Visual Studio 2022](https://aka.ms/vsdownloads):
    - [Windows 向けのステップ](https://learn.microsoft.com/ja-jp/dotnet/maui/get-started/installation?tabs=vswin)
    - [macOS 向けの手順](https://learn.microsoft.com/ja-jp/dotnet/maui/get-started/installation?tabs=vsmac)

### .NET MAUI アプリを作成する

1. Visual Studio 2022 のスタート ウィンドウで、[新しいプロジェクト作成] を選択します。
2. [**新しいプロジェクト** の作成] ウィンドウで、[すべてのプロジェクトの種類] ドロップダウンリストから [**MAUI**] を選択し、[**.NET MAUI App** テンプレート] を選択して、[**次へ**] を選択します。
3. **[新しいプロジェクトの構成]** ウィンドウで、**[プロジェクト名]** を *SignInMaui* に設定する必要があります。 **[ソリューション名]** を *sign-in-maui* に更新し、**[次へ]** を選択します。
4. [ **追加情報** ] ウィンドウで 、[.NET 7.0] を選択し、[ **作成**] を選択します。

プロジェクトが作成され、その依存関係が復元されるまで待ちます。

### MSAL ヘルパー クラスを使用して MSAL SDK のサポートを追加する

MSAL クライアントを使用すると、開発者は外部テナントからセキュリティ トークンを取得して、セキュリティで保護された Web API を認証してアクセスできます。 このセクションでは、MSALClient を構成するファイルをダウンロードします。

次のファイルをコンピューターのフォルダーにダウンロードします。

- [AzureAdConfig.cs](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/blob/main/1-Authentication/2-sign-in-maui/MSALClient/AzureAdConfig.cs) - このファイルは、アプリ構成ファイルから Microsoft Entra アプリの一意識別子を取得して設定します。
- [DownStreamApiConfig.cs](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/blob/main/1-Authentication/2-sign-in-maui/MSALClient/DownStreamApiConfig.cs) - このファイルは、Microsoft Graph 呼び出しのスコープを取得および設定します。
- [DownstreamApiHelper.cs](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/blob/main/1-Authentication/2-sign-in-maui/MSALClient/DownstreamApiHelper.cs) - このファイルは、ダウンストリーム API を呼び出すときに発生する例外を処理します。
- [Exception.cs](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/blob/main/1-Authentication/2-sign-in-maui/MSALClient/Exception.cs) - このファイルには、例外のスローと処理に関連するいくつかの拡張メソッドが用意されています。
- [IdentityLogger.cs](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/blob/main/1-Authentication/2-sign-in-maui/MSALClient/IdentityLogger.cs) - このファイル ハンドルは、MSAL.NET ログの使用方法を示します。
- [MSALClientHelper.cs](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/blob/main/1-Authentication/2-sign-in-maui/MSALClient/MSALClientHelper.cs) - このファイルには、MSAL SDK を初期化するメソッドが含まれています。
- [PlatformConfig.cs](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/blob/main/1-Authentication/2-sign-in-maui/MSALClient/PlatformConfig.cs) - このファイルには、特定のプラットフォームを処理するメソッドが含まれています。 たとえば、Windows です。
- [PublicClientSingleton.cs](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/blob/main/1-Authentication/2-sign-in-maui/MSALClient/PublicClientSingleton.cs) - このファイルには、MSALClient と関連するクラスをラップしてプラットフォームの静的初期化モデルをサポートするシングルトン実装が含まれています。
- [WindowsHelper.cs](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/blob/main/1-Authentication/2-sign-in-maui/MSALClient/WindowsHelper.cs) - このファイルには、ウィンドウ ハンドルを取得するメソッドが含まれています。

重要

MSALClient ファイルのダウンロードはスキップしないでください。このチュートリアルを完了する必要があります。

#### Visual Studio を使用して MSALClient ファイルを移動する

1. ソリューション エクスプローラー ウィンドウで、SignInMaui プロジェクトを右クリックし、[新しいフォルダー追加] 選択します。 MSALClientフォルダーに名前を付けます。
2. MSALClient フォルダー を右クリックし、[追加 既存の項目...] を選択。
3. 先ほどダウンロードした MSALClient ファイルを含むフォルダーに移動します。
4. すべての MSALClient ファイルを選択し、[**追加**] を選択します。

### 必要なパッケージをインストールする

次のパッケージをインストールする必要があります。

- `Microsoft.Identity.Client` - このパッケージには、Microsoft Authentication Library for .NET (MSAL.NET) のバイナリが含まれています。
- `Microsoft.Extensions.Configuration.Json` - このパッケージには、Microsoft.Extensions.Configuration の JSON 構成プロバイダーの実装が含まれています。
- `Microsoft.Extensions.Configuration.Binder` - このパッケージには、Microsoft.Extensions.Configuration の構成プロバイダー内のデータにオブジェクトをバインドする機能が含まれています。
- `Microsoft.Extensions.Configuration.Abstractions` - このパッケージには、キーと値のペアベースの構成の抽象化が含まれています。
- `Microsoft.Identity.Client.Extensions.Msal` - このパッケージには、.NET 用 Microsoft Authentication Library (MSAL.NET) の拡張機能が含まれています。

#### NuGet パッケージ マネージャー

**NuGet パッケージ マネージャー** を使用して、Visual Studio に *Microsoft.Identity.Client* パッケージをインストールするには、次の手順に従います。

1. [**ツール]**&gt;**[NuGet パッケージ マネージャー]**&gt;**[ソリューションの NuGet パッケージの管理] を選択します。**
2. **[参照]** タブで、*Microsoft.Identity.Client* を検索します。
3. 一覧から **[Microsoft.Identity.Client]** を選択します。
4. **[プロジェクト]** リスト ペインで **[SignInMaui]** を選択します。
5. **[インストール]** を選択します。
6. インストールの確認を求めるメッセージが表示されたら、[OK]選択します。

プロセスを繰り返して、残りの必要なパッケージをインストールします。

### イメージ リソースを追加する

このセクションでは、アプリで使用するイメージをダウンロードして、ユーザーが操作する方法を強化します。

次の画像をダウンロードします。

- [アイコン: Microsoft Entra ID](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/blob/main/1-Authentication/2-sign-in-maui/Resources/Images/azure_active_directory.png) - この画像はメイン ページのアイコンとして使用されます。

#### Visual Studio を使用してイメージを移動する

1. Visual Studio の **ソリューション エクスプローラー** ウィンドウで、**Resources** フォルダーを展開すると、**Images** フォルダーが表示されます。
2. **[画像]** を右クリックし、**[追加]**&gt;**[既存の項目]** の順に選択します。
3. ダウンロードしたイメージを含むフォルダーに移動します。
4. ファイルの種類フィルターを **[画像ファイル]** に変更します。
5. ダウンロードしたイメージを選択します。
6. [**] を選択し、[**] を追加します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-desktop-app-maui-sign-in-sign-out"} -->
## チュートリアル: 外部テナントを使用して .NET MAUI アプリでユーザーをサインインさせる - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-desktop-app-maui-sign-in-sign-out
- Service: identity-platform
- Article date: 2025-04-16
- Summary: このチュートリアルでは、.NET マルチプラットフォーム アプリ UI (.NET MAUI) シェルにサインインとサインアウト コードを追加する方法と、Windows プラットフォームでアプリを実行する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルは、.NET マルチプラットフォーム アプリ UI (.NET MAUI) シェルを構築する方法を示すシリーズの最後の部分であり、サインインとサインアウトのコードを追加し、そのアプリを Windows プラットフォームで実行する方法について説明します。 [このシリーズのパート 1](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-desktop-app-maui-sign-in-prepare-app) では、.NET MAUI シェル アプリを作成し、MSAL ヘルパー クラスを介しての MSAL SDK サポートの追加、必要なライブラリのインストール、画像リソースの追加を行いました。 この最後の手順では、サインインとサインアウトのコードを .NET MAUI シェルに追加し、そのアプリを Windows プラットフォームで実行する方法を示します。

このチュートリアルでは、次の操作を行います。

- サインインとサインアウト コードを追加します。
- アプリ シェルを変更します。
- プラットフォーム固有のコードを追加します。
- アプリ設定を追加します。
- .NET MAUI シェル アプリを実行してテストします。

### 前提条件

- [チュートリアル: .NET MAUI アプリを作成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-desktop-app-maui-sign-in-prepare-app)

### サインインとサインアウト コードを追加する

.NET MAUI アプリのユーザー インターフェイス (UI) は、各ターゲット プラットフォームのネイティブ コントロールにマップされるオブジェクトで構成されています。 .NET MAUI アプリの UI の作成に使用される主なコントロール グループは、ページ、レイアウト、ビューです。

#### メイン ビュー ページを追加する

次の手順では、`main view` が定義されるようにコードを組みます。

1. プロジェクトから *MainPage.xaml* と *MainPage.xaml.cs* を削除します。これらはもう必要ありません。 **[ソリューション エクスプローラー]** ペインで、**[MainPage.xaml]** のエントリを見つけて、右クリックして **[削除]** を選択します。
2. **[SignInMaui]** プロジェクトで右クリックし、**[追加]**&gt;**[新しいフォルダー]** を選択します。 フォルダーの名前を **[Views]** にします。
3. **[Views]** で右クリックします。
4. **[追加]**&gt;**[新しい項目...]** を選択します。
5. テンプレートの一覧で **[.NET MAUI]** を選択します。
6. **[.NET MAUI ContentPage (XAML)]** テンプレートを選択します。 ファイルの名前を **[MainView.xaml]** にします。
7. [**] を選択し、[**] を追加します。
8. *MainView.xaml* ファイルが新しいドキュメント タブで開き、ページの UI を表すすべての XAML マークアップが表示されます。 XAML マークアップを次のマークアップで置き換えます。

1. ファイルを保存します。

    以下でページに配置された XAML コントロールの主要なパーツを理解しましょう。

    - `<ContentPage>` は MainView クラスのルート オブジェクトです。
    - `<VerticalStackLayout>` は ContentPage の子オブジェクトです。 このレイアウト コントロールは、子要素を垂直方向に順番に配置します。
    - `<Image>` は画像を表示します。この場合は、前にダウンロードした *azure*active\_directory.png\_ を使用しています。
    - `<Label>` コントロールはテキストを表示します。
    - `<Button>` はユーザーが押すことができ、これによって `Clicked` イベントが発生します。 `Clicked` イベントに応じてコードを実行できます。
    - `Clicked="OnSignInClicked"` ボタンの `Clicked` イベントは `OnSignInClicked` イベント ハンドラーに割り当てられ、これは分離コード ファイルで定義されます。 このコードを次の手順で作成します。

##### OnSignInClicked イベントを処理する

次の手順は、ボタンの `Clicked` イベントのコードを追加することです。

1. Visual Studio の **[ソリューション エクスプローラー]** ペインで、**[MainView.xaml]** ファイルを展開して、分離コード ファイル **[MainView.xaml.cs]** を表示します。 **[MainView.xaml.cs]** を開き、ファイルの内容を次のコードで置き換えます。

`MainView` クラスは、アプリのメイン ビューを表示するコンテンツ ページです。 コンストラクターでは、キャッシュされたユーザー アカウントが見つからない場合、`MSALClientHelper` インスタンスからの `PublicClientSingleton` を使用してキャッシュされたユーザー アカウントを取得しサインイン ボタンを有効にします。

サインイン ボタンがクリックされると、`AcquireTokenSilentAsync` メソッドを呼び出してトークンをサイレントで取得し、`claimsview` メソッドを使用して `Shell.Current.GoToAsync` ページに遷移します。 さらに、`OnBackButtonPressed` メソッドは true を返すようにオーバーライドされるので、このビューの戻るボタンは無効になります。

#### 要求ビュー ページを追加する

次の手順では、`ClaimsView` ページが定義されるようにコードを組みます。 ページには、ID トークンで見つかったユーザーの要求が表示されます。

1. Visual Studio の **[ソリューション エクスプローラー]** ペインで、**[Views]** を右クリックします。
2. **[追加]**&gt;**[新しい項目...]** を選択します。
3. テンプレートの一覧で **[.NET MAUI]** を選択します。
4. **[.NET MAUI ContentPage (XAML)]** テンプレートを選択します。 ファイルの名前を **[ClaimsView.xaml]** にします。
5. [**] を選択し、[**] を追加します。
6. *ClaimsView.xaml* ファイルが新しいドキュメント タブで開き、ページの UI を表すすべての XAML マークアップが表示されます。 XAML マークアップを次のマークアップで置き換えます。

この XAML マークアップ コードは、.NET MAUI アプリの要求ビューの UI レイアウトを表します。 まず、`ContentPage` をタイトルで定義し、戻るボタンの動作を無効にします。

`VerticalStackLayout` 内には、静的テキストを表示するいくつかの `Label` 要素があり、その後に `ListView` という名前の `Claims` が続き、ID トークンで見つかった要求を表示するための `IdTokenClaims` というコレクションにバインドされます。 各要求は、`ViewCell` 内で `DataTemplate` を使用してレンダリングされ、グリッド内で中央揃えの `Label` として表示されます。

最後に、レイアウトの下部の中央に `Sign Out` ボタンがあり、クリックされると `SignOutButton_Clicked` イベント ハンドラーがトリガーされます。

##### ClaimsView データを処理する

次の手順は、`ClaimsView` データを処理するためのコードを追加することです。

1. Visual Studio の **[ソリューション エクスプローラー]** ペインで、**[ClaimsView.xaml]** ファイルを展開して、分離コード ファイル **[ClaimsView.xaml.cs]** を表示します。 **[ClaimsView.xaml.cs]** を開き、ファイルの内容を次のコードで置き換えます。

*ClaimsView.xaml.cs* コードは、.NET MAUI アプリのクレームビューのコードビハインドを表します。 まず、必要な名前空間をインポートし、`ClaimsView` を拡張する `ContentPage` クラスを定義します。 `IdTokenClaims` プロパティは文字列の Enumerable であり、最初はクレームが見つからないことを示す 1 つの文字列に設定されています。

`ClaimsView` コンストラクターは、バインディング コンテキストを現在のインスタンスに設定し、ビュー コンポーネントを初期化し、`SetViewDataAsync` メソッドを非同期的に呼び出します。 `SetViewDataAsync` メソッドは、トークンのサイレントな取得を試み、認証結果から要求を取得し、それらを `IdTokenClaims` という名前の `ListView` に表示するために `Claims` プロパティを設定します。 認証にユーザー操作が必要であることを示す `MsalUiRequiredException` が発生した場合、アプリは要求ビューに遷移します。

`OnBackButtonPressed` メソッドは、戻るボタンの動作を常に true を返すようにオーバーライドして、ユーザーがこのビューから戻る動作をできないようにします。 `SignOutButton_Clicked` イベント ハンドラーは、`PublicClientSingleton` インスタンスを使用してユーザーをサインアウトし、完了すると `main view` に遷移します。

### アプリ シェルを変更する

`AppShell` クラスは、アプリのビジュアル階層、つまりアプリの UI の作成に使用される XAML マークアップを定義します。 `AppShell` が `Views` について知ることができるように、これを更新します。

1. `AppShell.xaml` ペインで  ファイルをダブルクリックして、XAML エディターを開きます。 XAML マークアップを次のコードで置き換えます。

    ```xaml
    <?xml version="1.0" encoding="UTF-8" ?>
    <Shell
        x:Class="SignInMaui.AppShell"
        xmlns="http://schemas.microsoft.com/dotnet/2021/maui"
        xmlns:x="http://schemas.microsoft.com/winfx/2009/xaml"
        xmlns:pages="clr-namespace:SignInMaui.Pages"
        Shell.FlyoutBehavior="Disabled">
    
        <ShellContent
            Route="signin"
            ContentTemplate="{DataTemplate pages:SignInPage}" />
    
        <ShellContent
            Route="claims"
            ContentTemplate="{DataTemplate pages:ClaimsPage}" />
    
    </Shell>
    ```

    XAML コードは、ポップアップ動作を無効にするクラスを`AppShell`定義し、メインコンテンツを、 クラスを`ShellContent`指すタイトル`Home`とコンテンツ テンプレートを持つ要素に`MainView`設定します。
2. Visual Studio の **[ソリューション エクスプローラー]** ペインで、**[AppShell.xaml]** ファイルを展開して、分離コード ファイル **[AppShell.xaml.cs]** を表示します。 **[AppShell.xaml.cs]** を開き、ファイルの内容を次のコードで置き換えます。

    ```csharp
    // Copyright (c) Microsoft Corporation. All rights reserved.
    // Licensed under the MIT License.
    
    namespace SignInMaui;
    
    public partial class AppShell : Shell
    {
        public AppShell()
        {
            InitializeComponent();
        }
    }
    ```

    `AppShell.xaml.cs` ファイルを更新して、`MainView` と `ClaimsView` に必要なルート登録を含めます。 `InitializeComponent()` メソッドを呼び出すことで、`AppShell` クラスの初期化が確実に行われるようにします。 `RegisterRoute()` メソッドは、`mainview` と `claimsview` ルートをそれぞれのビューの種類 `MainView` と `ClaimsView` に関連付けます。

### プラットフォーム固有のコードを追加する

.NET MAUI アプリ プロジェクトには *Platforms* フォルダーが含まれており、各子フォルダーは、.NET MAUI がターゲットにできるプラットフォームを表しています。 既定の Application クラスを補完するアプリケーション固有の動作を提供するには、`Platforms/Windows/App.xaml.cs` を変更します。

ファイルの内容を次のコードで置き換えます。

```csharp
using Microsoft.UI.Xaml;

// To learn more about WinUI, the WinUI project structure,
// and more about our project templates, see: http://aka.ms/winui-project-info.

namespace SignInMaui.WinUI;

/// <summary>
/// Provides application-specific behavior to supplement the default Application class.
/// </summary>
public partial class App : MauiWinUIApplication
{
    /// <summary>
    /// Initializes the singleton application object.  This is the first line of authored code
    /// executed, and as such is the logical equivalent of main() or WinMain().
    /// </summary>
    public App()
    {
        this.InitializeComponent();
    }

    protected override MauiApp CreateMauiApp() => MauiProgram.CreateMauiApp();
}
```

コードでは、アプリケーションのリダイレクト URI を構成し、MSAL を初期化してから、アプリケーションの親ウィンドウを設定します。 さらに、`OnLaunched` メソッドを オーバーライドして起動イベントを処理し、親ウィンドウ ハンドルを取得します。

### アプリ設定を追加する

設定を使用すると、アプリの動作を構成するデータをコードから分離できるため、アプリを再構築せずに動作を変更できます。 `MauiAppBuilder` は、.NET MAUI アプリで設定を構成するための `ConfigurationManager` を提供します。 `appsettings.json` ファイルを `EmbeddedResource` として追加しましょう。

`appsettings.json` を作成するには、次の手順に従います。

1. Visual Studio の **[ソリューション エクスプローラー]** ペインで、**[SignInMaui]** プロジェクトで右クリック&gt;**[追加]**&gt;**[新しい項目...]**。
2. **[Web]**&gt;**[JavaScript JSON 構成ファイル]** を選択します。 そのファイルに `appsettings.json` という名前を付けます。
3. [**] を選択し、[**] を追加します。
4. **[appsettings.json]** を選択します
5. **[プロパティ]** ペインで、**[ビルド アクション]** を **[埋め込みリソース]** に設定します。
6. **[プロパティ]** ペインで、**[出力ディレクトリにコピー]** を **[常にコピー]** に設定します。
7. `appsettings.json` ファイルの内容を次のコードで置き換えます。

    ```json
    {
      "AzureAd": {
        "Authority": "https://Enter_the_Tenant_Subdomain_Here.ciamlogin.com/",
        "ClientId": "Enter_the_Application_Id_Here"
      },
      "DownstreamApi": {
        "Scopes": [
          "openid",
          "offline_access"
        ]
      }
    }
    ```
8. `appsettings.json` 内でプレースホルダーを見つけます。

    1. `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。
    2. `Enter_the_Application_Id_Here` を、前に登録したアプリのアプリケーション (クライアント) ID に置き換えます。

#### カスタム URL ドメインを使用する (省略可能)

カスタム ドメインを使用して、認証 URL を完全にブランド化します。 ユーザーの視点から見ると、認証プロセスの間、ユーザーは *ciamlogin.com* ドメイン名にリダイレクトされず、あなたのドメインにとどまります。

カスタム ドメインを使用するには、次の手順に従います。

1. 「[外部テナント内のアプリに対するカスタム URL ドメインの有効化](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-url-domain)」の手順を使用し、外部テナントに対してカスタム URL ドメインを有効にします。
2. *appsettings.json* ファイルを開きます。

    1. `Authority` プロパティの値を *https://Enter\_the\_Custom\_Domain\_Here/Enter\_the\_Tenant\_ID\_Here* に更新します。 `Enter_the_Custom_Domain_Here` を実際のカスタム URL ドメインに、`Enter_the_Tenant_ID_Here` を実際のテナント ID に置き換えます。 テナント ID がわからない場合は、[テナントの詳細を読み取る](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)方法を確認してください。
    2. `knownAuthorities` という値を持つ  プロパティを追加します。

カスタム URL ドメインが *login.contoso.com*、テナント ID が *aaaabbbb-0000-cccc-1111-dddd2222eeee* の場合、*appsettings.json* ファイルに変更を加えた後には、ファイルは次のスニペットのようになるはずです。

```json
{
  "AzureAd": {
    "Authority": "https://login.contoso.com/aaaabbbb-0000-cccc-1111-dddd2222eeee",
    "ClientId": "Enter_the_Application_Id_Here",
    "CacheFileName": "msal_cache.txt",
    "CacheDir": "C:/temp",
    "KnownAuthorities": ["login.contoso.com"]
  },
  "DownstreamApi": {
    "Scopes": "openid offline_access"
  }
}
```

### .NET MAUI デスクトップ アプリを実行してテストする

.NET MAUI アプリは、複数のオペレーティング システムとデバイス上で実行できるように設計されています。 どのターゲットでアプリをテストしてデバッグしたいかを選択する必要があります。

Visual Studio ツール バーの **[デバッグ ターゲット]** を、デバッグしてテストしたいデバイスに設定します。 次の手順は、**[デバッグ ターゲット]** を *Windows* に設定する方法を示しています。

1. **[デバッグ ターゲット]** ドロップダウン リストを選択します。
2. **フレームワーク**を選択します
3. **[net7.0-windows...]** を選択します

*F5* キーを押すか、Visual Studio の上部にある "再生ボタン" を選択してアプリを実行します。

1. これでサンプルの .NET MAUI デスクトップ アプリケーションをテストできるようになりました。 アプリケーションを実行すると、デスクトップ アプリケーション ウィンドウが自動的に表示されます。

    [Image: デスクトップ アプリケーションの [サインイン] ボタンのスクリーンショット]
2. 表示されたデスクトップ ウィンドウで、**[サインイン]** ボタンを選択します。 ブラウザー ウィンドウが開き、サインインが求められます。

    [Image: デスクトップ アプリケーションで資格情報を入力するためのユーザー プロンプトのスクリーンショット。]

    サインイン プロセス中に、さまざまなアクセス許可を付与するように求められます (アプリケーションがデータにアクセスできるようにします)。 サインインと同意が成功すると、アプリケーション画面にメイン ページが表示されます。

    [Image: サインインした後に表示されるデスクトップ アプリケーションのメイン ページのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-desktop-maui-role-based-access-control"} -->
## チュートリアル:Microsoft ID プラットフォームを使用して .NET MAUI デスクトップ アプリでロールベースのアクセス制御を使用する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-desktop-maui-role-based-access-control
- Service: identity-platform
- Article date: 2023-07-17
- Summary: このチュートリアルでは、アプリ ロールを .NET マルチプラットフォーム アプリ UI (.NET MAUI) シェルに追加し、ID トークンで受け取る方法について説明します。

**適用対象**: [Image: 灰色の X 記号がある白い円。] 従業員テナント [Image: 白いチェック マーク記号がある緑の円。] 外部テナント ([詳細情報](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、マルチプラットフォーム アプリ UI (.NET MAUI) to.NET アプリ ロールを追加し、ID トークンで受け取る方法について説明します。

このチュートリアルでは、次の操作を行います。

- ID トークン内のロールにアクセスします。

### 前提条件

- [チュートリアル: .NET MAUI シェル アプリでユーザーをサインインする](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-desktop-app-maui-sign-in-sign-out)
- [アプリケーションにロールベースのアクセス制御 (RBAC) を使用する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-use-app-roles-customers)

### .NET MAUI でグループとロールの要求を受信する

顧客のテナントを構成したら、クライアント アプリでロールとグループの要求を取得できます。 ロールとグループの要求は、どちらも ID トークンとアクセス トークンに存在します。 アクセス トークンは、クライアントによって取得された Web API でのみ検証されます。 クライアントはアクセス トークンを検証しないでください。

.NET MAUI は、クライアント側で承認を実装するために、ID トークン内のアプリ ロール要求を確認する必要があります。

このチュートリアル シリーズでは、 データを処理するを開発した .NET MAUI アプリを作成しました。 このファイルでは、ID トークンの内容を調べます。 役割の要求の値は、次のコード スニペットで確認されます。

ロールクレームにアクセスするには、コードスニペットを次のように修正します。

```csharp
var idToken = PublicClientSingleton.Instance.MSALClientHelper.AuthResult.IdToken;
var handler = new JwtSecurityTokenHandler();
var token = handler.ReadJwtToken(idToken);
// Get the role claim value
var roleClaim = token.Claims.FirstOrDefault(c => c.Type == "roles")?.Value;

if (!string.IsNullOrEmpty(roleClaim))
{
    // If the role claim exists, add it to the IdTokenClaims
    IdTokenClaims = new List<string> { roleClaim };
}
else
{
    // If the role claim doesn't exist, add a message indicating that no role claim was found
    IdTokenClaims = new List<string> { "No role claim found in ID token" };
}

Claims.ItemsSource = IdTokenClaims;
```

注

ID トークンを読み取るために、 `System.IdentityModel.Tokens.Jwt` パッケージをインストールする必要があります。

ユーザーを複数のロールに割り当てる場合、ロール文字列には、 `Orders.Manager, Store.Manager,...`など、コンマで区切られたすべてのロールが含まれます。 次の条件を処理するようにアプリケーションをビルドしてください。

- トークンに役割情報要求が含まれていない
- ユーザーはどの役割にも割り当てられていない
- ユーザーを複数のロールに割り当てると、ロール要求に複数の値が含まれる

アプリのアプリ ロールを定義するときは、それらのロールの承認ロジックを実装する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-desktop-wpf-dotnet-sign-in-build-app"} -->
## チュートリアル: WPF デスクトップ アプリケーションのユーザーを認証する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-desktop-wpf-dotnet-sign-in-build-app
- Service: identity-platform
- Article date: 2025-04-16
- Summary: WPF デスクトップ アプリにユーザーをサインインおよびサインアウトさせる方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、Windows プレゼンテーション フォーム (WPF) デスクトップ アプリをビルドし、Microsoft Entra 管理センターを使用して認証用に準備する方法について説明します。

このチュートリアルでは、次の操作を行います。

- WPF デスクトップ アプリを構成して、そのアプリの登録情報を使用する。
- ユーザーをサインインさせ、ユーザーの代わりにトークンを取得するデスクトップ アプリをビルドします。

### 前提条件

- [組織のディレクトリと個人用](https://entra.microsoft.com)の Microsoft アカウントのアカウント用に構成された Microsoft *Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
    - ディレクトリ (テナント) ドメイン名 ( *contoso.onmicrosoft.com* や *contoso.com* など)。
- **モバイル アプリケーションとデスクトップ アプリケーション**のプラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `https://login.microsoftonline.com/common/oauth2/nativeclient`
- Microsoft Entra 管理センターでアプリをユーザー フローに関連付けます。 このユーザー フローは、複数のアプリケーションで使用できます。 詳細については、「 [外部テナント内のアプリのセルフサービス サインアップ ユーザー フローを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers) 」および「 [ユーザー フローにアプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)」を参照してください。
- [.NET 7.0 SDK](https://dotnet.microsoft.com/download/dotnet/7.0) 以降。
- React アプリケーションをサポートする統合開発環境 (IDE) であればどれでも使用できますが、このチュートリアルでは [Visual Studio Code](https://visualstudio.microsoft.com/downloads/) を使用します。

### WPF デスクトップ アプリケーションを作成する

1. ターミナルを開き、プロジェクトを公開するフォルダーに移動します。
2. WPF デスクトップ アプリを初期化し、そのルート フォルダーに移動します。

    ```dotnetcli
    dotnet new wpf --language "C#" --name sign-in-dotnet-wpf
    cd sign-in-dotnet-wpf
    ```

### パッケージをインストールする

アプリ設定ファイルのキーと値のペアからの構成データの読み取りに役立つ構成プロバイダーをインストールします。 これらの構成の抽象化は、構成値を .NET オブジェクトのインスタンスにバインドできることです。

```dotnetcli
dotnet add package Microsoft.Extensions.Configuration
dotnet add package Microsoft.Extensions.Configuration.Json
dotnet add package Microsoft.Extensions.Configuration.Binder
```

トークンを取得するために必要なすべての主要なコンポーネントを含む Microsoft Authentication Library (MSAL) をインストールします。 また、デスクトップ認証ブローカーとの対話操作を処理する MSAL ブローカー ライブラリもインストールします。

```dotnetcli
dotnet add package Microsoft.Identity.Client
dotnet add package Microsoft.Identity.Client.Broker
```

### 登録構成を追加する appsettings.json ファイルを作成する

1. アプリのルート フォルダーに *appsettings.json* ファイルを作成します。
2. アプリの登録の詳細を *appsettings.json* ファイルに追加します。

    ```json
    {
        "AzureAd": {
            "Authority": "https://<Enter_the_Tenant_Subdomain_Here>.ciamlogin.com/",
            "ClientId": "<Enter_the_Application_Id_Here>"
        }
    }
    ```

    - `Enter_the_Tenant_Subdomain_Here` を、ディレクトリ (テナント) サブドメインに置き換えます。
    - `Enter_the_Application_Id_Here` を、前に登録したアプリのアプリケーション (クライアント) ID に置き換えます。
3. アプリ設定ファイルを作成したら、アプリ設定ファイルから構成を読み取るのに役立つ *AzureAdConfig.cs* という名前の別のファイルを作成します。 アプリのルート フォルダーで *AzureAdConfig.cs* ファイルを作成します。
4. *AzureAdConfig.js* ファイルで、`ClientId` プロパティと `Authority` プロパティのゲッターとセッターを定義します。 次のコードを追加します。

    ```csharp
    namespace sign_in_dotnet_wpf
    {
        public class AzureAdConfig
        {
            public string Authority { get; set; }
            public string ClientId { get; set; }
        }
    }
    ```

#### カスタム URL ドメインを使用する (省略可能)

カスタム ドメインを使用して、認証 URL を完全にブランド化します。 ユーザーの視点から見ると、認証プロセスの間、ユーザーは *ciamlogin.com* ドメイン名にリダイレクトされず、あなたのドメインにとどまります。

カスタム ドメインを使用するには、次の手順に従います。

1. 「[外部テナント内のアプリに対するカスタム URL ドメインの有効化](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-url-domain)」の手順を使用し、外部テナントに対してカスタム URL ドメインを有効にします。
2. *appsettings.json* ファイルを開きます。

    1. `Authority` プロパティの値を *https://Enter\_the\_Custom\_Domain\_Here/Enter\_the\_Tenant\_ID\_Here* に更新します。 `Enter_the_Custom_Domain_Here` を実際のカスタム URL ドメインに、`Enter_the_Tenant_ID_Here` を実際のテナント ID に置き換えます。 テナント ID がわからない場合は、[テナントの詳細を読み取る](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)方法を確認してください。
    2. `knownAuthorities` という値を持つ  プロパティを追加します。

カスタム URL ドメインが *login.contoso.com*、テナント ID が *aaaabbbb-0000-cccc-1111-dddd2222eeee* の場合、*appsettings.json* ファイルに変更を加えた後には、ファイルは次のスニペットのようになるはずです。

```json
{
    "AzureAd": {
        "Authority": "https://login.contoso.com/aaaabbbb-0000-cccc-1111-dddd2222eeee",
        "ClientId": "Enter_the_Application_Id_Here",
        "KnownAuthorities": ["login.contoso.com"]
    }
}
```

### プロジェクト ファイルを変更する

1. アプリのルート フォルダーにある *sign-in-dotnet-wpf.csproj* ファイルに移動します。
2. このファイルでは、次の 2 つの手順を実行します。

    1. *sign-in-dotnet-wpf.csproj* ファイルを変更して、プロジェクトのコンパイル時に *appsettings.json* ファイルを出力ディレクトリにコピーするようにアプリに指示します。 以下のコードを *sign-in-dotnet-wpf.csproj* ファイルに追加します。
    2. トークン キャッシュ ヘルパー クラスに表示されるように、ターゲット フレームワークをターゲット *windows10.0.19041.0* ビルドに設定することで、トークン キャッシュからキャッシュされたトークンを読み取るのに役立ちます。

    ```xml
    <Project Sdk="Microsoft.NET.Sdk">
    
        ...
    
        <!-- Set target framework to target windows10.0.19041.0 build -->
        <PropertyGroup>
            <OutputType>WinExe</OutputType>
            <TargetFramework>net7.0-windows10.0.19041.0</TargetFramework> <!-- target framework -->
            <RootNamespace>sign_in_dotnet_wpf</RootNamespace>
            <Nullable>enable</Nullable>
            <UseWPF>true</UseWPF>
        </PropertyGroup>
    
        <!-- Copy appsettings.json file to output folder. -->
        <ItemGroup>
            <None Remove="appsettings.json" />
        </ItemGroup>
    
        <ItemGroup>
            <EmbeddedResource Include="appsettings.json">
                <CopyToOutputDirectory>Always</CopyToOutputDirectory>
            </EmbeddedResource>
        </ItemGroup>
    </Project>
    ```

### トークン キャッシュ ヘルパー クラスを作成する

トークン キャッシュを初期化するトークン キャッシュ ヘルパー クラスを作成します。 アプリケーションは、新しいトークンの取得を試みる前に、キャッシュからトークンの読み取りを試みます。 キャッシュでトークンが見つからない場合、アプリケーションは新しいトークンを取得します。 サインアウトすると、すべてのアカウントと対応するすべてのアクセス トークンのキャッシュがクリアされます。

1. アプリのルート フォルダーで *TokenCacheHelper.cs* ファイルを作成します。
2. *TokenCacheHelper.cs* ファイルを開きます。 ファイルにパッケージと名前空間を追加します。 次の手順では、関連するロジックを `TokenCacheHelper` クラスに追加して、このファイルにコード ロジックを設定します。

    ```csharp
    using System.IO;
    using System.Security.Cryptography;
    using Microsoft.Identity.Client;
    
    namespace sign_in_dotnet_wpf
    {
        static class TokenCacheHelper{}
    }
    ```
3. キャッシュ ファイルパスを定義する `TokenCacheHelper` クラスにコンストラクターを追加します。 パッケージ化されたデスクトップ アプリ (MSIX パッケージ、デスクトップ ブリッジとも呼ばれます) の場合、実行中のアセンブリ フォルダーは読み取り専用です。 その場合は、パッケージ化されたアプリのアプリごとの読み取り/書き込みフォルダーである `Windows.Storage.ApplicationData.Current.LocalCacheFolder.Path + "\msalcache.bin"` を使用する必要があります。

    ```csharp
    namespace sign_in_dotnet_wpf
    {
        static class TokenCacheHelper
        {
            static TokenCacheHelper()
            {
                try
                {
                    CacheFilePath = Path.Combine(Windows.Storage.ApplicationData.Current.LocalCacheFolder.Path, ".msalcache.bin3");
                }
                catch (System.InvalidOperationException)
                {
                    CacheFilePath = System.Reflection.Assembly.GetExecutingAssembly().Location + ".msalcache.bin3";
                }
            }
            public static string CacheFilePath { get; private set; }
            private static readonly object FileLock = new object();
        }
    }
    
    ```
4. トークン キャッシュのシリアル化を処理するコードを追加します。 `ITokenCache` インターフェイスは、キャッシュ操作へのパブリック アクセスを実装します。 `ITokenCache` インターフェイスにはキャッシュ シリアル化イベントをサブスクライブするメソッドが含まれますが、インターフェイス `ITokenCacheSerializer` はキャッシュ シリアル化イベントで使用する必要があるメソッドを公開し、キャッシュをシリアル化/逆シリアル化します。 `TokenCacheNotificationArgs` には、キャッシュにアクセスする `Microsoft.Identity.Client` (MSAL) 呼び出しで使用されるパラメーターが含まれています。 `ITokenCacheSerializer` インターフェイスは `TokenCacheNotificationArgs` コールバックで使用できます。

    以下のコードを `TokenCacheHelper` クラスに追加します。

    ```csharp
        static class TokenCacheHelper
        {
            static TokenCacheHelper()
            {...}
            public static string CacheFilePath { get; private set; }
            private static readonly object FileLock = new object();
    
            public static void BeforeAccessNotification(TokenCacheNotificationArgs args)
            {
                lock (FileLock)
                {
                    args.TokenCache.DeserializeMsalV3(File.Exists(CacheFilePath)
                            ? ProtectedData.Unprotect(File.ReadAllBytes(CacheFilePath),
                                                     null,
                                                     DataProtectionScope.CurrentUser)
                            : null);
                }
            }
    
            public static void AfterAccessNotification(TokenCacheNotificationArgs args)
            {
                if (args.HasStateChanged)
                {
                    lock (FileLock)
                    {
                        File.WriteAllBytes(CacheFilePath,
                                           ProtectedData.Protect(args.TokenCache.SerializeMsalV3(),
                                                                 null,
                                                                 DataProtectionScope.CurrentUser)
                                          );
                    }
                }
            }
        }
    
        internal static void EnableSerialization(ITokenCache tokenCache)
        {
            tokenCache.SetBeforeAccess(BeforeAccessNotification);
            tokenCache.SetAfterAccess(AfterAccessNotification);
        }
    ```

    `BeforeAccessNotification` メソッドでは、ファイル システムからキャッシュを読み取り、キャッシュが空でない場合は逆シリアル化して読み込みます。 `AfterAccessNotification` メソッドは、`Microsoft.Identity.Client` (MSAL) がキャッシュにアクセスした後に呼び出されます。 キャッシュが変更された場合は、キャッシュをシリアル化し、変更をキャッシュに保持します。

    `EnableSerialization` には、`ITokenCache.SetBeforeAccess()` メソッドと `ITokenCache.SetAfterAccess()` メソッドが含まれます。

    - `ITokenCache.SetBeforeAccess()` は、ライブラリ メソッドがキャッシュにアクセスする前に通知を受け取るデリゲートを設定します。 これにより、`TokenCacheNotificationArgs` で指定されたアプリケーションとアカウントのキャッシュ エントリを逆シリアル化するためのオプションがデリゲートに提供されます。
    - `ITokenCache.SetAfterAccess()` は、ライブラリ メソッドがキャッシュにアクセスした後に通知を受け取るデリゲートを設定します。 これにより、`TokenCacheNotificationArgs` で指定されたアプリケーションとアカウントのキャッシュ エントリをシリアル化するためのオプションがデリゲートに提供されます。

### WPF デスクトップ アプリ UI を作成する

*MainWindow.xaml* ファイルを変更して、アプリの UI 要素を追加します。 アプリのルート フォルダーにある *MainWindow.xaml* ファイルを開き、`<Grid></Grid>` コントロール セクションで次のコードを追加します。

```xaml
    <StackPanel Background="Azure">
        <StackPanel Orientation="Horizontal" HorizontalAlignment="Right">
            <Button x:Name="SignInButton" Content="Sign-In" HorizontalAlignment="Right" Padding="5" Click="SignInButton_Click" Margin="5" FontFamily="Segoe Ui"/>
            <Button x:Name="SignOutButton" Content="Sign-Out" HorizontalAlignment="Right" Padding="5" Click="SignOutButton_Click" Margin="5" Visibility="Collapsed" FontFamily="Segoe Ui"/>
        </StackPanel>
        <Label Content="Authentication Result" Margin="0,0,0,-5" FontFamily="Segoe Ui" />
        <TextBox x:Name="ResultText" TextWrapping="Wrap" MinHeight="120" Margin="5" FontFamily="Segoe Ui"/>
        <Label Content="Token Info" Margin="0,0,0,-5" FontFamily="Segoe Ui" />
        <TextBox x:Name="TokenInfoText" TextWrapping="Wrap" MinHeight="70" Margin="5" FontFamily="Segoe Ui"/>
    </StackPanel>
```

このコードでは、主要な UI 要素を追加します。 UI 要素の機能を処理するメソッドとオブジェクトは、次の手順で作成する *MainWindow.xaml.cs* ファイルで定義されます。

- ユーザーをサインインさせるボタン。 `SignInButton_Click` メソッドは、ユーザーがこのボタンを選択したときに呼び出されます。
- ユーザーをサインアウトさせるボタン。 `SignOutButton_Click` メソッドは、ユーザーがこのボタンを選択したときに呼び出されます。
- ユーザーがサインインを試みた後の認証結果の詳細を表示するテキスト ボックス。 ここに表示される情報は、`ResultText` オブジェクトによって返されます。
- ユーザーが正常にサインインした後にトークンの詳細を表示するテキスト ボックス。 ここに表示される情報は、`TokenInfoText` オブジェクトによって返されます。

### MainWindow.xaml.cs ファイルにコードを追加する

*MainWindow.xaml.cs* ファイルには、*MainWindow.xaml* ファイル内の UI 要素の動作のランタイム ロジックを提供するコードが含まれています。

1. アプリのルート フォルダーにある *MainWindow.xaml.cs* ファイルを開きます。
2. ファイルに次のコードを追加してパッケージをインポートし、作成するメソッド向けのプレースホルダーを定義します。

    ```csharp
    using Microsoft.Identity.Client;
    using System;
    using System.Linq;
    using System.Windows;
    using System.Windows.Interop;
    
    namespace sign_in_dotnet_wpf
    {
        public partial class MainWindow : Window
        {
            string[] scopes = new string[] { };
    
            public MainWindow()
            {
                InitializeComponent();
            }
    
            private async void SignInButton_Click(object sender, RoutedEventArgs e){...}
    
            private async void SignOutButton_Click(object sender, RoutedEventArgs e){...}
    
            private void DisplayBasicTokenInfo(AuthenticationResult authResult){...}
        }
    }
    ```
3. `SignInButton_Click` メソッドに次のコードを追加します。 このメソッドは、ユーザーが **[サインイン]** ボタンを選択した場合に呼び出されます。

    ```csharp
    private async void SignInButton_Click(object sender, RoutedEventArgs e)
    {
        AuthenticationResult authResult = null;
        var app = App.PublicClientApp;
    
        ResultText.Text = string.Empty;
        TokenInfoText.Text = string.Empty;
    
        IAccount firstAccount;
    
        var accounts = await app.GetAccountsAsync();
        firstAccount = accounts.FirstOrDefault();
    
        try
        {
            authResult = await app.AcquireTokenSilent(scopes, firstAccount)
                    .ExecuteAsync();
        }
        catch (MsalUiRequiredException ex)
        {
            try
            {
                authResult = await app.AcquireTokenInteractive(scopes)
                    .WithAccount(firstAccount)
                    .WithParentActivityOrWindow(new WindowInteropHelper(this).Handle) 
                    .WithPrompt(Prompt.SelectAccount)
                    .ExecuteAsync();
            }
            catch (MsalException msalex)
            {
                ResultText.Text = $"Error Acquiring Token:{System.Environment.NewLine}{msalex}";
            }
            catch (Exception ex)
            {
                ResultText.Text = $"Error Acquiring Token Silently:{System.Environment.NewLine}{ex}";
                return;
            }
    
            if (authResult != null)
            {
                ResultText.Text = "Sign in was successful.";
                DisplayBasicTokenInfo(authResult);
                this.SignInButton.Visibility = Visibility.Collapsed;
                this.SignOutButton.Visibility = Visibility.Visible;
            }
        }
    }
    ```

    `GetAccountsAsync()` は、アプリのユーザー トークン キャッシュで使用可能なすべてのアカウントを返します。 `IAccount` インターフェイスは 1 つのアカウントに関する情報を表します。

    トークンを取得するために、アプリは `AcquireTokenSilent` メソッドを使用してトークンを警告なしで取得し、受け入れ可能なトークンがキャッシュ内にあるかどうかを確認します。 `AcquireTokenSilent` メソッドは、たとえば、ユーザーがサインアウトしたために失敗する可能性があります。MSAL は、対話型アクションを要求することで問題を解決できることを検出すると、`MsalUiRequiredException`例外をスローします。 この例外により、アプリは対話操作でトークンを取得します。

    `AcquireTokenInteractive` メソッドを呼び出すと、ユーザーにサインインを求めるウィンドウが表示されます。 通常、アプリは、ユーザーの初回認証が必要な場合に、対話操作でユーザーにサインインを求めます。 また、トークンを取得する無言の操作が行われた場合にも、ユーザーはサインインする必要があります。 `AcquireTokenInteractive` が初回実行されると、`AcquireTokenSilent` はトークンの取得に使用する通常のメソッドになります
4. `SignOutButton_Click` メソッドに次のコードを追加します。 このメソッドは、ユーザーが **[サインアウト]** ボタンを選択した場合に呼び出されます。

    ```csharp
    private async void SignOutButton_Click(object sender, RoutedEventArgs e)
    {
        var accounts = await App.PublicClientApp.GetAccountsAsync();
        if (accounts.Any())
        {
            try
            {
                await App.PublicClientApp.RemoveAsync(accounts.FirstOrDefault());
                this.ResultText.Text = "User has signed-out";
                this.TokenInfoText.Text = string.Empty;
                this.SignInButton.Visibility = Visibility.Visible;
                this.SignOutButton.Visibility = Visibility.Collapsed;
            }
            catch (MsalException ex)
            {
                ResultText.Text = $"Error signing-out user: {ex.Message}";
            }
        }
    }
    ```

    `SignOutButton_Click` メソッドは、すべてのアクセス トークンと対応するすべてのアカウントのキャッシュをクリアします。 次回ユーザーがサインインしようとすると、対話操作でサインインする必要があります。
5. `DisplayBasicTokenInfo` メソッドに次のコードを追加します。 このメソッドは、トークンに関する基本情報を表示します。

    ```csharp
    private void DisplayBasicTokenInfo(AuthenticationResult authResult)
    {
        TokenInfoText.Text = "";
        if (authResult != null)
        {
            TokenInfoText.Text += $"Username: {authResult.Account.Username}" + Environment.NewLine;
            TokenInfoText.Text += $"{authResult.Account.HomeAccountId}" + Environment.NewLine;
        }
    }
    ```

### App.xaml.cs ファイルにコードを追加する

*App.xaml* は、アプリ全体で使用されるリソースを宣言するファイルです。 これは、アプリのエントリ ポイントです。 *App.xaml.cs* は、*App.xaml* の分離コード ファイルです。 *App.xaml.cs* では、アプリケーションの開始ウィンドウも定義されます。

アプリのルート フォルダーにある *App.xaml.cs* ファイルを開き、次のコードを追加します。

```csharp
using System.Windows;
using System.Reflection;
using Microsoft.Identity.Client;
using Microsoft.Identity.Client.Broker;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Configuration.Json;

namespace sign_in_dotnet_wpf
{
    public partial class App : Application
    {
        static App()
        {
            CreateApplication();
        }

        public static void CreateApplication()
        {
            var assembly = Assembly.GetExecutingAssembly();
            using var stream = assembly.GetManifestResourceStream("sign_in_dotnet_wpf.appsettings.json");
            AppConfiguration = new ConfigurationBuilder()
                .AddJsonStream(stream)
                .Build();

            AzureAdConfig azureADConfig = AppConfiguration.GetSection("AzureAd").Get<AzureAdConfig>();

            var builder = PublicClientApplicationBuilder.Create(azureADConfig.ClientId)
                .WithAuthority(azureADConfig.Authority)
                .WithDefaultRedirectUri();

            _clientApp = builder.Build();
            TokenCacheHelper.EnableSerialization(_clientApp.UserTokenCache);
        }

        private static IPublicClientApplication _clientApp;
        private static IConfiguration AppConfiguration;
        public static IPublicClientApplication PublicClientApp { get { return _clientApp; } }
    }
}
```

この手順では、*appsettings.json* ファイルを読み込みます。 構成ビルダーは、*appsettings.json* ファイルで定義されているアプリ構成を読み取るのに役立ちます。 また、WPF アプリはデスクトップ アプリであるため、パブリック クライアント アプリとして定義します。 `TokenCacheHelper.EnableSerialization` メソッドを使用することで、トークン キャッシュをシリアル化できます。

### アプリを実行する

アプリを実行し、サインインしてアプリケーションをテストする

1. ターミナルで、WPF アプリのルート フォルダーに移動し、ターミナルでコマンド `dotnet run` を実行してアプリを実行します。
2. サンプルを起動すると、**サインイン** ボタンを含むウィンドウが表示されます。 **[サインイン]** ボタンを選択します。

    [Image: WPF デスクトップ アプリケーションのサインイン画面のスクリーンショット。]
3. サインイン ページで、アカウントのメール アドレスを入力します。 アカウントをお持ちでない場合は、**[アカウントをお持ちではない場合、作成できます]** を選択します。これで、サインアップ フローが開始されます。 このフローに従って、新しいアカウントを作成してサインインします。
4. サインインすると、正常なサインインと、取得したトークンに保存されているユーザー アカウントに関する基本情報を表示する画面が表示されます。 サインイン画面の *[トークン情報* ] セクションに基本情報が表示されます
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-dotnet-daemon-call-api"} -->
## チュートリアル: .NET デーモン アプリから保護された Web API を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-dotnet-daemon-call-api
- Service: identity-platform
- Article date: 2025-02-24
- Summary: .NET クライアント デーモン アプリから保護された Web API を呼び出す方法について説明します。 このチュートリアルでは、Microsoft Graph API を呼び出します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、保護された Web API を .NET デーモン アプリから呼び出す方法について説明します。 クライアント デーモン アプリが独自の ID を使用してアクセス トークンを取得できるようにするには、Web API を呼び出します。 ここでは、保護された Microsoft Graph エンドポイントを呼び出します。

このチュートリアルでは、

- デーモン アプリがアプリ登録の詳細を使用するように構成します。 アプリに Microsoft Graph API の *User.Read.All* アクセス許可を付与していることを確認します。
- 独自の代わりにトークンを取得し、保護された Web API を呼び出すデーモン アプリを構築します。

### 前提条件

- [.NET](https://dotnet.microsoft.com/download) このチュートリアルでは、.NET 9.0 を使用します。
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコードエディター。
- テナント内の[アプリ登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。 アプリの登録の詳細から次の情報があることを確認します。
    - 登録したクライアント Web アプリの*アプリケーション (クライアント) ID*。
    - Web アプリを登録した *ディレクトリ (テナント) ID* 。
    - 作成した Web アプリの*クライアント シークレット*の値。

### .NET デーモン アプリを作成する

1. ターミナルを開き、プロジェクトを公開するフォルダーに移動します。
2. .NET コンソール アプリを初期化し、そのルート フォルダーに移動します。

    ```dotnetcli
    dotnet new console -n DotnetDaemon
    cd DotnetDaemon
    ```

### パッケージをインストールする

`Microsoft.Identity.Web`パッケージと`Microsoft.Identity.Web.DownstreamApi` パッケージをインストールします。

```dotnetcli
dotnet add package Microsoft.Identity.Web
dotnet add package Microsoft.Identity.Web.DownstreamApi
```

`Microsoft.Identity.Web` では、ASP.NET Core、認証ミドルウェア、および .NET 用 Microsoft Authentication Library (MSAL) の間の接着が提供されるため、認証と承認の機能をアプリに簡単に追加できます。 `Microsoft.Identity.Web.DownstreamApi` は、ダウンストリーム API の呼び出しに使用されるインターフェイスを提供します。

### appsettings.json ファイルを作成して登録構成を追加する

1. アプリのルート フォルダーに *appsettings.json* ファイルを作成します。
2. アプリの登録の詳細を *appsettings.json* ファイルに追加します。

    ```json
    {
        "AzureAd": {
            // "Authority": "", you can use this for customer tenants in place of Instance and TenantId values
            "Instance": "https://login.microsoftonline.com/",
            "TenantId": "Enter_the_Tenant_ID_Here",
            "ClientId": "Enter_the_Application_ID_Here",
            "ClientCredentials": [
                {
                    "SourceType": "ClientSecret",
                    "ClientSecret": "Enter_the_Client_Secret_Here"
                }
            ]
        },
        "DownstreamApi": {
            "BaseUrl": "https://graph.microsoft.com",
            "RelativePath": "/v1.0/users/",
            "RequestAppToken": true,
            "Scopes": [
                "https://graph.microsoft.com/.default"
            ]
        }
    }
    ```

    次の値は固有の値に置き換えてください。

    | 価値 | 説明 |
    | --- | --- |
    | *Enter\_the\_Application\_ID\_Here* | 登録したクライアント デーモン アプリのアプリケーション (クライアント) ID。 |
    | *Enter\_the\_Client\_Secret\_Here* | 作成したデーモン アプリのシークレット値。 |
    | *Enter\_the\_Tenant\_ID\_Here* | アプリが登録されているディレクトリ/テナントのテナント ID。 |

    注

    外部テナントに登録されているアプリの場合は、 *Authority* を使用し、 *Instance* と *TenantId の*両方を削除できます。

    `"Authority": "https://<Enter_the_Tenant_Subdomain_Here>.ciamlogin.com/"`。 *ここで、Enter\_the\_Tenant\_Subdomain\_Here*はテナントのサブドメインです。
3. *appsettings.json* ファイルをプロジェクト ファイルに追加します。 プロジェクト ファイルは、プロジェクト内 *の .csproj* ファイルです。 これは、ファイルを出力ディレクトリにコピーする必要があるためです。

    ```xml
    <ItemGroup>
        <None Update="appsettings.json">
            <CopyToOutputDirectory>PreserveNewest</CopyToOutputDirectory>
        </None>
    </ItemGroup>
    ```

### アクセス トークンを取得する

1. コード エディターでprogram.cs ファイルを開き、その内容を削除します。
2. ファイルにパッケージを追加します。

    ```csharp
    using Microsoft.Extensions.DependencyInjection;
    using Microsoft.Identity.Abstractions;
    using Microsoft.Identity.Web;
    ```
3. トークン取得インスタンスを作成します。 トークン取得インスタンスをビルドするには、`GetDefaultInstance` パッケージの`TokenAcquirerFactory` クラスの`Microsoft.Identity.Web` メソッドを使用します。 既定では、インスタンスはアプリと同じフォルダーに存在する場合、 *appsettings.json* ファイルを読み取ります。 `GetDefaultInstance` サービス コレクションにサービスを追加することもできます。

    次のコード行をprogram.cs ファイル *に* 追加します。

    ```csharp
    var tokenAcquirerFactory = TokenAcquirerFactory.GetDefaultInstance();
    ```
4. 構成から読み取るアプリケーション オプションを構成し、 `DownstreamApi` サービスを追加します。 `DownstreamApi` サービスは、ダウンストリーム API の呼び出しに使用されるインターフェイスを提供します。 構成オブジェクトでは、このサービス *DownstreamAPI* を呼び出します。 デーモン アプリは、appsettings.jsonの *DownstreamApi* セクションからダウンストリーム API 構成 * を *読み取ります。 既定では、メモリ内トークン キャッシュが取得されます。

    *program.cs* ファイルに次のコード スニペットを追加します。

    ```csharp
    const string ServiceName = "DownstreamApi";
    
    tokenAcquirerFactory.Services.AddDownstreamApi(ServiceName,
        tokenAcquirerFactory.Configuration.GetSection("DownstreamApi"));
    ```

    呼び出すダウンストリーム API は Microsoft Graph です。 このチュートリアルでは、 `DownstreamApi` サービスを使用します。 Microsoft Graph SDK を使用することもできます。
5. トークン取得機能を構築します。 これにより、追加したすべてのサービスが構成され、サービス プロバイダーが返されます。 このサービス プロバイダーを使用して、追加する API リソースにアクセスします。 この場合、アクセスするダウンストリーム サービスとして追加する API リソースは 1 つだけです。

    *program.cs* ファイルに次のコード スニペットを追加します。

    ```csharp
    var serviceProvider = tokenAcquirerFactory.Build();
    ```

### Web API を呼び出す

1. `IDownstreamApi` インターフェイスを使用して、保護された Web API を呼び出すコードを追加します。 このチュートリアルでは、Microsoft Graph API エンドポイントを呼び出します。
2. program.cs ファイルに次のコード *を* 追加します。

    ```csharp
    try
    {
        IDownstreamApi downstreamApi = serviceProvider.GetRequiredService<IDownstreamApi>();
    
        var response = await downstreamApi.GetForAppAsync<HttpResponseMessage>("DownstreamApi");
        var content = await response.Content.ReadAsStringAsync();
        var statusCode = response.StatusCode;
    
        Console.WriteLine($"Response status code: {statusCode}");
    
        if (!content.Any())
        {
            Console.WriteLine("There are no users to display.");
            return;
        }
    
        Console.WriteLine(content);
    }
    catch (Exception ex) { Console.WriteLine("We could not retrieve the user's list: " + $"{ex}"); }
    ```

    このコードは、 *appsettings.json* ファイルで定義したエンドポイントを呼び出します。 エンドポイントの呼び出しには、`GetForAppAsync` インターフェイスの `IDownstreamApi` メソッドが使用されます。 アプリは、それ自体に代わって呼び出しを行います。 このメソッドは、 `HttpResponseMessage` オブジェクトを返します。 応答は文字列として読み取られ、コンソールに表示されます。

### クライアント デーモン アプリを実行する

デーモン アプリのルート フォルダーに移動し、次のコマンドを実行します。

```dotnetcli
dotnet run
```

問題がなければ、ターミナルに *応答状態コード[OK]* が表示されます。 ユーザーが存在する場合、ユーザーはターミナルに一覧表示されます。それ以外の場合 *は、表示するユーザーがいない*というメッセージが表示されます。

エラーが発生した場合は、ターミナルにエラー メッセージが表示されます。

#### トラブルシューティング

エラーが発生した場合は、

- *appsettings.json* ファイルに追加した登録の詳細を確認します。
- *appsettings.json* ファイルをプロジェクト ファイルに追加したことを確認します。
- アプリのアクセス許可が正しく構成されていることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-mobile-android-device-shared-mode"} -->
## チュートリアル: Android デバイスに Shared Device Mode のサポートを追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-android-device-shared-mode
- Service: identity-platform / workforce
- Article date: 2024-08-30
- Summary: このチュートリアルでは、Microsoft Authenticator アプリまたは Intune を使用して Android デバイスに Shared Device Mode のサポートを追加する方法について説明します

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、Microsoft Authenticator アプリ、または Microsoft Intune などのモバイル デバイス管理 (MDM) ツールを使用して、Android デバイスに Shared Device Mode のサポートを追加する方法について説明します。 従業員は、SDM でサポートされているすべてのアプリのシングル サインオン (SSO) のために 1 回サインインし、サインアウトして、以前のデータへのアクセス権のない次のユーザーにデバイスを準備します。

このチュートリアルでは、次の操作を行います。

- Microsoft Intune を使用したゼロタッチ セットアップ
- ゼロタッチ セットアップがサポートされているサード パーティ製 MDM
- Microsoft Authenticator アプリを使用した手動セットアップ

### 前提条件

- アクティブなサブスクリプションが含まれる Azure アカウント。 お持ちでない場合は、[無料のアカウントを作成してください](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- Android OS バージョン 8.0 以降を実行している Android デバイス。 出荷時の設定にリセットするか、すべての SDM 対応アプリを含む Microsoft アプリをアンインストールすることで、デバイスが初期状態になっていることを確認してください。
- [Microsoft Authenticator アプリ](https://play.google.com/store/apps/details/Microsoft_Authenticator?id=com.azure.authenticator&amp;hl=en_NZ)の最新バージョンがデバイスにインストールされている。
- MDM を使用してセットアップする場合、デバイスは、Microsoft Intune などの Shared Device Mode をサポートする MDM によって管理する必要があります。

### Intune を使用したゼロタッチ セットアップ

Microsoft Intune では、Microsoft Entra Shared Device Mode (SDM) のデバイスに対するゼロタッチ プロビジョニングがサポートされています。つまり、現場担当者の最小限の操作で、デバイスを設定し、Intune に登録できます。

Microsoft Intune を MDM として使用するときに Shared Device Mode でデバイスを設定するには、最初に共有デバイスを Intune に登録し、SDM が有効になっている Authenticator アプリをインストールします。 Microsoft Intune を使用して SDM を設定する方法について詳しくは、「[Android Enterprise 専用デバイスの Intune 登録を設定する](https://learn.microsoft.com/ja-jp/mem/intune/enrollment/android-kiosk-enroll)」を参照してください。

登録が完了したら、デバイスをオンにして、標準の Android デバイス セットアップを開始します。このセットアップにより、Microsoft Entra ID を使用してデバイスの登録が自動的にトリガーされ、使用できる状態になります。

### ゼロタッチ セットアップがサポートされているサード パーティ製 MDM

次のサード パーティ製モバイル デバイス管理 (MDM) ツールは、Microsoft Entra Shared Device Mode をサポートしています。

- [VMware Workspace ONE](https://docs.omnissa.com/bundle/UEMSharedDevicesVSaaS/page/UEMSharedDeviceConditionalAccess.html) - VMware では条件付きアクセス機能がサポートされていますが、現在、共有デバイス モードでのグローバル サインインとグローバル サインアウトはサポートされていません。
- [SOTI MobiControl](https://soti.net/resources/blog/2023/soti-mobicontrol-supports-microsoft-shared-device-mode/)

注

MDM が Shared Device Mode でのデバイスの設定をサポートしていない場合は、MDM プロバイダーに連絡して、この機能のサポートをリクエストしてください。 また、MDM が Shared Device Mode をサポートしていない場合、デバイスを手動で共有デバイス モードにしてテストできます。

### Microsoft Authenticator アプリを使用した手動セットアップ

Microsoft Authenticator アプリを使用して手動セットアップを完了するには、クラウド デバイス管理者のアカウントが必要です。 セットアップ プロセスを完了するには、次の手順を実行します。

1. Authenticator アプリを起動し、メイン アカウント ページに移動します。次のように、**[アカウントの追加]** オプションが表示されます。

    [Image: Microsoft Authenticator アプリの [Add Account] オプションのスクリーンショット。]
2. 右側のメニュー バーを使用して、 **[設定]** ペインに移動します。 **[職場または学校アカウント]** の下にある **[デバイスの登録]** を選択します。

    [Image: Microsoft Authenticator アプリの [Settings] のスクリーンショット。]
3. **[Device Registration]** を選択すると、デバイスの連絡先へのアクセスを承認するように求められます。 これは、デバイスで Android のアカウントが統合されているためです。 **[許可]** を選びます。

    [Image: Microsoft Authenticator アプリのアクセス許可を確認するウィンドウのスクリーンショット。]
4. **[または共有デバイスとして登録する]** に組織のメールアドレスを入力します。 次に、**[共有デバイスとして登録]** ボタンを選択し、資格情報を入力します。

    [Image: アプリに表示される Microsoft Authenticator の [Device registration] ウィンドウのスクリーンショット。]

    [Image: Microsoft サインイン ページのスクリーンショット。]
5. これでデバイスが共有モードになりました。

    [Image: Shared Device Mode が有効になっていることを示す Microsoft Authenticator アプリのスクリーンショット。]

デバイスでのサインインとサインアウトは、すべてグローバルになります。つまり、デバイス上の MSAL および Microsoft Authenticator と統合されているすべてのアプリにサインインとサインアウトが適用されます。 これで、共有デバイス モード機能が使用されるデバイスにアプリケーションをデプロイできるようになりました。

### 共有デバイスを表示する

デバイスを共有モードで設定すると、そのデバイスは組織に認識され、組織のテナントで追跡されるようになります。 共有デバイスを表示するには、**[結合の種類]** を参照します。

[Image: ゼロタッチで登録された共有デバイスを示す Microsoft Entra ウィンドウのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-mobile-app-android-call-web-api"} -->
## Microsoft ID プラットフォームを使用して Android アプリで保護された Web API を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-android-call-web-api
- Service: identity-platform
- Article date: 2025-01-27
- Summary: このチュートリアルでは、認証のために Android アプリで保護された Web API を呼び出す方法に関するステップ バイ ステップ ガイドを提供します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

これは、Microsoft Entra External ID を使用して保護された Web API を呼び出す方法について説明するチュートリアル シリーズの 3 番目のチュートリアルです。

このチュートリアルでは、次の操作を行います。

- 保護された Web API を呼び出す

### 前提条件

## [Workforce テナントの構成](#tab/android-workforce)
- [チュートリアル: Microsoft ID プラットフォームを使用して Android アプリにサインインを追加する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-android-sign-in-sign-out)

## [外部テナントの構成](#tab/android-external)
- [チュートリアル: Microsoft ID プラットフォームを使用して Android アプリにサインインを追加する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-android-sign-in-sign-out)
- 少なくとも 1 つのスコープ (委任されたアクセス許可) と 1 つのアプリ ロール (アプリケーション アクセス許可) を発行する API 登録 (*ToDoList.Read* など)。 まだ行っていない場合は、 [サンプルの Android モバイル アプリで API を呼び出して](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-android-call-api) 、コア Web API ASP.NET 機能を保護する手順に従ってください。 次の手順を完了していることを確認します。

    - Web API アプリケーションを登録する
    - API スコープを構成する
    - アプリ ロールを構成する
    - オプションクレームの設定
    - サンプル Web API を複製またはダウンロードする
    - サンプル Web API の構成と実行

---

### 保護された Web API を呼び出す

## [Workforce テナントの構成](#tab/android-workforce)
1. **app**&gt;**src**&gt;**main**&gt;**java**&gt;**com.example(アプリ名)** 内で。 次の Android フラグメントを作成します。

    - *MSGraphRequestWrapper (英語)*
2. *MSGraphRequestWrapper.java* を開き、コードを次のコード スニペットに置き換えて、MSAL によって提供されたトークンを使用して Microsoft Graph API を呼び出します。

    ```java
     package com.azuresamples.msalandroidapp;
    
     import android.content.Context;
     import android.util.Log;
    
     import androidx.annotation.NonNull;
    
     import com.android.volley.DefaultRetryPolicy;
     import com.android.volley.Request;
     import com.android.volley.RequestQueue;
     import com.android.volley.Response;
     import com.android.volley.toolbox.JsonObjectRequest;
     import com.android.volley.toolbox.Volley;
    
     import org.json.JSONObject;
    
     import java.util.HashMap;
     import java.util.Map;
    
     public class MSGraphRequestWrapper {
         private static final String TAG = MSGraphRequestWrapper.class.getSimpleName();
    
         // See: https://docs.microsoft.com/en-us/graph/deployments#microsoft-graph-and-graph-explorer-service-root-endpoints
         public static final String MS_GRAPH_ROOT_ENDPOINT = "https://graph.microsoft.com/";
    
         /**
          * Use Volley to make an HTTP request with
          * 1) a given MSGraph resource URL
          * 2) an access token
          * to obtain MSGraph data.
          **/
         public static void callGraphAPIUsingVolley(@NonNull final Context context,
                                                    @NonNull final String graphResourceUrl,
                                                    @NonNull final String accessToken,
                                                    @NonNull final Response.Listener<JSONObject> responseListener,
                                                    @NonNull final Response.ErrorListener errorListener) {
             Log.d(TAG, "Starting volley request to graph");
    
             /* Make sure we have a token to send to graph */
             if (accessToken == null || accessToken.length() == 0) {
                 return;
             }
    
             RequestQueue queue = Volley.newRequestQueue(context);
             JSONObject parameters = new JSONObject();
    
             try {
                 parameters.put("key", "value");
             } catch (Exception e) {
                 Log.d(TAG, "Failed to put parameters: " + e.toString());
             }
    
             JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, graphResourceUrl,
                     parameters, responseListener, errorListener) {
                 @Override
                 public Map<String, String> getHeaders() {
                     Map<String, String> headers = new HashMap<>();
                     headers.put("Authorization", "Bearer " + accessToken);
                     return headers;
                 }
             };
    
             Log.d(TAG, "Adding HTTP GET to Queue, Request: " + request.toString());
    
             request.setRetryPolicy(new DefaultRetryPolicy(
                     3000,
                     DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                     DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
             queue.add(request);
         }
     }
    ```

## [外部テナントの構成](#tab/android-external)
1. Android アプリケーションから Web API を呼び出して外部データまたはサービスにアクセスするには、まず、 `MainActivity` クラスにコンパニオン オブジェクトを作成します。 コンパニオン オブジェクトには、次のコードを含める必要があります。

    ```kotlin
    companion object {
        private const val WEB_API_BASE_URL = "" // Developers should set the respective URL of their web API here
        private const val scopes = "" // Developers should append the respective scopes of their web API.
    }
    ```

    コンパニオン オブジェクトは、開発者が Web API の URL を設定する `WEB_API_BASE_URL` と、開発者が Web API のそれぞれの`scopes`を追加する`scopes`という 2 つのプライベート定数を定義します。
2. Web API にアクセスするプロセスを処理するには、次のコードを使用します。

    ```kotlin
    private fun accessWebApi() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.txtLog.text = ""
            try {
                if (WEB_API_BASE_URL.isBlank()) {
                    Toast.makeText(this@MainActivity, getString(R.string.message_web_base_url), Toast.LENGTH_LONG).show()
                    return@launch
                }
                val apiResponse = withContext(Dispatchers.IO) {
                    ApiClient.performGetApiRequest(WEB_API_BASE_URL, accessToken)
                }
                binding.txtLog.text = getString(R.string.log_web_api_response)  + apiResponse.toString()
            } catch (exception: Exception) {
                Log.d(TAG, "Exception while accessing web API: $exception")
    
                binding.txtLog.text = getString(R.string.exception_web_api) + exception
            }
        }
    }
    ```

    このコードは、メイン ディスパッチャーでコルーチンを起動します。 まず、テキスト ログをクリアします。 次に、Web API のベース URL が空白かどうかを確認します。その場合は、トースト メッセージが表示され、返されます。 次に、バックグラウンド スレッドで指定されたアクセス トークンを使用して、Web API に対する GET 要求を実行します。

    API 応答を受信すると、応答コンテンツでテキスト ログが更新されます。 このプロセス中に例外が発生した場合は、例外がログに記録され、対応するエラー メッセージでテキスト ログが更新されます。

    コールバックを指定するコードでは、 `performGetApiRequest()`という関数を使用します。 関数には次のコードが必要です。

    ```kotlin
    object ApiClient {
        private val client = OkHttpClient()
    
        fun performGetApiRequest(WEB_API_BASE_URL: String, accessToken: String?): Response {
            val fullUrl = "$WEB_API_BASE_URL/api/todolist"
    
            val requestBuilder = Request.Builder()
                    .url(fullUrl)
                    .addHeader("Authorization", "Bearer $accessToken")
                    .get()
    
            val request = requestBuilder.build()
    
            client.newCall(request).execute().use { response -> return response }
        }
    }
    ```

    このコードにより、Web API への `GET` 要求の作成が容易になります。 main メソッドは `performGetApiRequest()`であり、Web API のベース URL とアクセス トークンをパラメーターとして受け取ります。 このメソッド内では、ベース URL に `/api/todolist` を追加して完全な URL を構築します。 次に、アクセス トークンを含む承認ヘッダーを含む、適切なヘッダーを含む HTTP 要求を構築します。

    最後に、OkHttp の `newCall()` メソッドを使用して同期的に要求を実行し、応答を返します。 `ApiClient` オブジェクトは、HTTP 要求を処理する`OkHttpClient`のインスタンスを保持します。 `OkHttpClient`を使用するには、依存関係`implementation 'com.squareup.okhttp3:okhttp:4.9.0'`を Android Gradle ファイルに追加する必要があります。

    必ず import ステートメントを含めます。 Android Studio では、自動的に import ステートメントが含められます。

---

### アプリをテストする

アプリをビルドし、テスト デバイスまたはエミュレーターに展開します。 サインインして Microsoft Entra ID のトークンを取得できる必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-mobile-app-android-prepare-app"} -->
## Microsoft ID プラットフォームを使用して Android アプリでユーザーをサインインさせる - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-android-prepare-app
- Service: identity-platform
- Article date: 2025-01-27
- Summary: 外部テナントまたはワークフォース テナントの従業員によって、ユーザーを顧客向けアプリにサインインする Android アプリ プロジェクトを設定します

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、Android アプリに Microsoft Authentication Library (MSAL) for Android を追加する方法について説明します。 MSAL を使用すると、Android アプリケーションは Microsoft Entra を使用してユーザーを認証できます。

このチュートリアルでは、次のことを行います。

- MSAL 依存関係を追加する
- 構成を追加する
- MSAL SDK インスタンスを作成する

### [前提条件]

## [従業員テナント](#tab/workforce-tenant)
- 従業員テナント。 [既定のディレクトリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)を使用するか、新しいテナントを設定できます。
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- Android プロジェクト。 Android プロジェクトがない場合は、作成します。

## [外部テナント](#tab/external-tenant)
- 外部テナント。 作成するには、次の方法から選択します。
    - [Microsoft Entra 外部 ID 拡張機能](https://aka.ms/ciamvscode/samples/marketplace)を使用して、Visual Studio Code で外部テナントを直接設定します。 *(おすすめ)*
    - [Microsoft Entra 管理センターで新しい外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal) を作成します。
- [組織のディレクトリと個人用](https://entra.microsoft.com)の Microsoft アカウントのアカウント用に構成された Microsoft *Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID

---

### リダイレクト URI を追加する

アプリの登録で特定のリダイレクト URI を構成して、ダウンロードしたコード サンプルとの互換性を確保する必要があります。 これらの URI は、ユーザーが正常にサインインした後にアプリをリダイレクトするために不可欠です。

## [従業員テナント](#tab/workforce-tenant)
1. [**管理**] で、**認証**&gt;**プラットフォームの追加**&gt;Android を選択**します**。
2. 上記でダウンロードしたサンプルタイプに基づいて、プロジェクトのパッケージ名を入力します。

    - Javaサンプル - `com.azuresamples.msalandroidapp`
    - Kotlin サンプル - `com.azuresamples.msalandroidkotlinapp`
3. [**Android アプリの構成**] ウィンドウの [**署名ハッシュ**] セクションで、[**開発署名ハッシュの生成**] を選択し、KeyTool コマンドをコマンド ラインにコピーします。

    - KeyTool.exe は、Java Development Kit (JDK) の一部としてインストールされます。 KeyTool コマンドを実行するには、OpenSSL ツールもインストールする必要があります。 詳細については、[キーの生成に関する Android のドキュメント](https://developer.android.com/studio/publish/app-signing#generate-key)を参照してください。
4. KeyTool によって生成された **署名ハッシュ** を入力します。
5. **[構成]** を選択し、後でアプリを構成するときに入力できるように、**[Android の構成]** ペインに表示される **[MSAL 構成]** を保存しておきます。
6. **完了**を選択します。

## [外部テナント](#tab/external-tenant)
アプリの登録にアプリの種類を指定するには、次の手順に従います。

1. **[管理]** で、 **[認証]** を選択します。
2. **[プラットフォーム構成**] ページで、[**プラットフォームの追加**] を選択し、[**iOS/macOS**] オプションを選択します。
3. プロジェクトのバンドル ID を入力します。 [サンプル コード](https://github.com/Azure-Samples/ms-identity-ciam-browser-delegated-ios-sample.git)をダウンロードした場合、この値は `com.microsoft.identitysample.ciam.MSALiOS` です。
4. [**構成**] を選択し、**iOS/macOS 構成**ウィンドウに表示される **MSAL 構成**を保存して、後でアプリを構成するときに入力できるようにします。
5. **完了**を選択します。

#### パブリック クライアント フローを有効にする

アプリをパブリック クライアントとして識別するには、次の手順に従います。

1. **[管理]** で、 **[認証]** を選択します。
2. **[詳細設定]** で、**[パブリック クライアント フローを許可する]** に対して **[はい]** を選択します。
3. **[保存]** を選択して変更を保存します。

---

### MSAL の依存関係と関連するライブラリをプロジェクトに追加する

Android プロジェクトに MSAL 依存関係を追加するには、次の手順に従います。

1. Android Studio でプロジェクトを開くか、新しいプロジェクトを作成します。
2. アプリケーションの `build.gradle` を開き、次の依存関係を追加します。

    ```gradle
    allprojects {
    repositories {
        //Needed for com.microsoft.device.display:display-mask library
        maven {
            url 'https://pkgs.dev.azure.com/MicrosoftDeviceSDK/DuoSDK-Public/_packaging/Duo-SDK-Feed/maven/v1'
            name 'Duo-SDK-Feed'
        }
        mavenCentral()
        google()
        }
    }
    //...
    
    dependencies { 
        implementation 'com.microsoft.identity.client:msal:5.+'
        //...
    }
    ```

    `build.gradle`構成では、リポジトリはプロジェクトの依存関係に対して定義されます。 これには、Azure DevOps の `com.microsoft.device.display:display-mask` ライブラリの Maven リポジトリ URL が含まれています。 さらに、MavenCentralとGoogleリポジトリを利用します。 dependencies セクションでは、MSAL バージョン 5 の実装と、場合によっては他の依存関係を指定します。
3. Android Studio で、[ **File**&gt;**Sync Project with Gradle Files**] を選択します。

### 構成を追加する

JSON 構成設定を使用して、アプリケーション (クライアント) ID などの必要なテナント識別子を MSAL SDK に渡します。

構成ファイルを作成するには、次の手順に従います。

## [Workforce テナントの構成](#tab/android-workforce)
1. Android Studio のプロジェクト ウィンドウで、**app\src\main\res** に移動します。
2. **res** を右クリックして、**[New](https://learn.microsoft.com/ja-jp/entra/identity-platform/新規)**&gt;**[Directory](https://learn.microsoft.com/ja-jp/entra/identity-platform/ディレクトリ)** を選択します。 新しいディレクトリの名前に「`raw`」と入力し、**[OK]** を選択します。
3. **app**&gt;**src**&gt;**main**&gt;**res**&gt;**raw** で、`auth_config_single_account.json` という名前の新しい JSON ファイルを作成し、先ほど保存した MSAL 構成を貼り付けます。

    リダイレクト URI の下に、以下を貼り付けます。

    ```json
      "account_mode" : "SINGLE",
    ```

    構成ファイルは次の例のようになります。

    ```json
    {
      "client_id": "00001111-aaaa-bbbb-3333-cccc4444",
      "authorization_user_agent": "WEBVIEW",
      "redirect_uri": "msauth://com.azuresamples.msalandroidapp/00001111%cccc4444%3D",
      "broker_redirect_uri_registered": true,
      "account_mode": "SINGLE",
      "authorities": [
        {
          "type": "AAD",
          "audience": {
            "type": "AzureADandPersonalMicrosoftAccount",
            "tenant_id": "common"
          }
        }
      ]
    }
    ```

    このチュートリアルでは単一アカウント モードでアプリを構成する方法のみを紹介しているため、詳細については、[単一アカウント モードと複数アカウント モード](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-multi-account)に関する記事および[アプリの構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-configuration)に関する記事を参照してください
4. 'WEBVIEW' を使用することをお勧めします。 アプリで "authorization\_user\_agent" を 'BROWSER' として構成する場合は、次の更新を行う必要があります。 a) auth\_config\_single\_account.jsonを "authorization\_user\_agent": "Browser" で更新します。 b) AndroidManifest.xml を更新します。 アプリで **app**&gt;**src**&gt;**main**&gt;**AndroidManifest.xml** に移動し、`BrowserTabActivity` アクティビティを `<application>` 要素の子として追加します。 このエントリにより、Microsoft Entra ID は認証の完了後にアプリケーションにコールバックできます。

    ```xml
    <!--Intent filter to capture System Browser or Authenticator calling back to our app after sign-in-->
    <activity
        android:name="com.microsoft.identity.client.BrowserTabActivity"
        android:exported="true">
        <intent-filter>
            <action android:name="android.intent.action.VIEW" />
            <category android:name="android.intent.category.DEFAULT" />
            <category android:name="android.intent.category.BROWSABLE" />
            <data android:scheme="msauth"
                android:host="Enter_the_Package_Name"
                android:path="/Enter_the_Signature_Hash" />
        </intent-filter>
    </activity>
    ```

    - **パッケージ名**を使用して `android:host=.` 値を置き換えます。 `com.azuresamples.msalandroidapp` のようになります。
    - **署名ハッシュ**を使用して `android:path=` 値を置き換えます。 署名ハッシュの先頭に `/` があることを確認します。 `/aB1cD2eF3gH4+iJ5kL6-mN7oP8q=` のようになります。

    これらの値は、アプリの登録の [認証] ブレードでも確認できます。

## [外部テナントの構成](#tab/android-external)
1. Android Studio のプロジェクト ウィンドウで、*app\src\main\res* に移動します。
2. **res** を右クリックし、**新規**&gt;Directory を選択**します**。 新しいディレクトリの名前に「`raw`」と入力し、**[OK]** を選択します。
3. *app\src\main\r\raw* で、`auth_config_ciam_auth.json`という名前の新しい JSON ファイルを作成します。
4. `auth_config_ciam_auth.json` ファイルに、次の MSAL 構成を追加します。

    ```json
    {
      "client_id" : "Enter_the_Application_Id_Here",
      "authorization_user_agent" : "DEFAULT",
      "redirect_uri" : "Enter_the_Redirect_Uri_Here",
      "account_mode" : "SINGLE",
      "authorities" : [
        {
          "type": "CIAM",
          "authority_url": "https://Enter_the_Tenant_Subdomain_Here.ciamlogin.com/Enter_the_Tenant_Subdomain_Here.onmicrosoft.com/"
        }
      ]
    }
    ```

    JSON 設定ファイルは、Android アプリケーションのさまざまな設定を指定します。 これには、クライアント ID、認証ユーザー エージェント、リダイレクト URI、およびアカウント モードが含まれます。 さらに、認証の機関を定義し、タイプと権限の URL を指定します。

    次のプレースホルダーを、Microsoft Entra 管理センターから取得したテナント値に置き換えます。

    - `Enter_the_Application_Id_Here` を、前に登録したアプリの**アプリケーション (クライアント) ID** に置き換えます。
    - `Enter_the_Redirect_Uri_Here` をクリックし、プラットフォームのリダイレクト URL を追加したときに以前にダウンロードした Microsoft Authentication Library (MSAL) 構成ファイルの *redirect\_uri* の値に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント サブドメインがわからない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)る方法について説明します。
5. */app/src/main/AndroidManifest.xml* ファイルを開きます。
6. *AndroidManifest.xml*で、次のデータ仕様をインテントフィルタに追加します。

    ```xml
    <data
        android:host="ENTER_YOUR_PROJECT_PACKAGE_NAME_HERE"
        android:path="/ENTER_YOUR_SIGNATURE_HASH_HERE"
        android:scheme="msauth" />
    ```

    プレースホルダーを見つけてください。

    - `ENTER_YOUR_PROJECT_PACKAGE_NAME_HERE` をクリックし、Android のプロジェクト パッケージ名に置き換えます。
    - `ENTER_YOUR_SIGNATURE_HASH_HERE` をクリックし、プラットフォームのリダイレクトURLを追加したときに生成した署名ハッシュに置き換えます。

#### カスタム URL ドメインを使用する (省略可能)

カスタム ドメインを使用して、認証 URL を完全にブランド化します。 ユーザーの視点から見ると、認証プロセスの間、ユーザーは *ciamlogin.com* ドメイン名にリダイレクトされず、あなたのドメインにとどまります。

カスタム ドメインを使用するには、以下の手順を実行します。

1. 「[外部テナント内のアプリに対するカスタム URL ドメインの有効化](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-url-domain)」の手順を使用し、外部テナントに対してカスタム URL ドメインを有効にします。
2. *auth\_config\_ciam\_auth.json* ファイルを開きます。

    1. `authority_url` プロパティの値を *https://Enter\_the\_Custom\_Domain\_Here/Enter\_the\_Tenant\_ID\_Here* に更新します。 `Enter_the_Custom_Domain_Here` を実際のカスタム URL ドメインに、`Enter_the_Tenant_ID_Here` を実際のテナント ID に置き換えます。 テナント ID がわからない場合は、[テナントの詳細を読み取る](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)方法を確認してください。
    2. `knownAuthorities` という値を持つ  プロパティを追加します。

*auth\_config\_ciam\_auth.json* ファイルに変更を加えた後、カスタム URL ドメインが *login.contoso.com* で、テナント ID が *aaaabbbb-0000-cccc-1111-dddd2222eeee* の場合、ファイルは次のスニペットのようになります。

```json
{
    "client_id" : "Enter_the_Application_Id_Here",
    "authorization_user_agent" : "DEFAULT",
    "redirect_uri" : "Enter_the_Redirect_Uri_Here",
    "account_mode" : "SINGLE",
    "authorities" : [
    {
        "type": "CIAM",
        "authority_url": "https://login.contoso.com/aaaabbbb-0000-cccc-1111-dddd2222eeee",
        "knownAuthorities": ["login.contoso.com"]
    }
    ]
}
```

---

### MSAL SDK インスタンスを作成する

MSAL SDK インスタンスを初期化するには、次のコードを使用します。

## [Workforce テナントの構成](#tab/android-workforce)
```java
PublicClientApplication.createSingleAccountPublicClientApplication(
    getContext(),
    R.raw.auth_config_single_account,
    new IPublicClientApplication.ISingleAccountApplicationCreatedListener() {
        @Override
        public void onCreated(ISingleAccountPublicClientApplication application) {
            // Initialize the single account application instance
            mSingleAccountApp = application;
            loadAccount();
        }

        @Override
        public void onError(MsalException exception) {
            // Handle any errors that occur during initialization
            displayError(exception);
        }
    }
);
```

このコードは、設定ファイル auth\_config\_single\_account.jsonを使用して、単一アカウントの公開クライアント アプリケーションを作成します。 アプリケーションが正常に作成されると、インスタンスが `mSingleAccountApp` に割り当てられ、 `loadAccount()` メソッドが呼び出されます。 作成中にエラーが発生した場合は、displayError(exception) メソッドを呼び出してエラーを処理します。

## [外部テナントの構成](#tab/android-external)
```kotlin
private suspend fun initClient(): ISingleAccountPublicClientApplication = withContext(Dispatchers.IO) {
    return@withContext PublicClientApplication.createSingleAccountPublicClientApplication(
        this@MainActivity,
        R.raw.auth_config_ciam_auth
    )
}
```

このコードは、単一アカウントの公開クライアント アプリケーションを非同期的に初期化します。 これは、提供された認証設定ファイルを使用し、I/O ディスパッチャで実行されます。

---

必ず import ステートメントを含めます。 Android Studio では、自動的に import ステートメントが含められます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-mobile-app-android-sign-in-sign-out"} -->
## Microsoft ID プラットフォームを使用して Android アプリにサインインを追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-android-sign-in-sign-out
- Service: identity-platform
- Article date: 2025-01-27
- Summary: Microsoft ID プラットフォームを使用して、外部テナントまたは従業員テナントを使用して Android アプリにサインインを追加する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、Android アプリにサインインとサインアウトのロジックを追加します。 このコードを使用すると、外部テナントまたは従業員テナントの従業員で、顧客向けアプリにユーザーをサインインさせることができます。

このチュートリアルは、3 部構成のチュートリアル シリーズのパート 2 です。

このチュートリアルでは、次の操作を行います。

- サインインとサインアウトのロジックを追加する

### [前提条件]

- [チュートリアル: Microsoft ID プラットフォームを使用してユーザーをサインインするように Android アプリを設定する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-android-prepare-app)

### ユーザーのサインインとサインアウト

## [Workforce テナントの構成](#tab/android-workforce)
#### 必要なフラグメントを作成して更新する

1. **app**&gt;**src**&gt;**main**&gt;**java**&gt;**com.example(あなたのアプリ名)。** 次の Android フラグメントを作成します。

    - *OnFragmentInteractionListener*
    - *SingleAccountModeフラグメント*
2. *OnFragmentInteractionListener.java*開き、コードを次のコード スニペットに置き換えて、さまざまなフラグメント間の通信を可能にします。

    ```java
     package com.azuresamples.msalandroidapp;
    
     /**
      * This interface must be implemented by activities that contain this
      * fragment to allow an interaction in this fragment to be communicated
      * to the activity and potentially other fragments contained in that
      * activity.
      * <p>
      * See the Android Training lesson <a href=
      * "http://developer.android.com/training/basics/fragments/communicating.html"
      * >Communicating with Other Fragments</a> for more information.
      */
     public interface OnFragmentInteractionListener {
     }
    ```
3. *SingleAccountModeFragment.java*開き、コードを次のコード スニペットに置き換えて、単一アカウント アプリケーションを初期化し、ユーザー アカウントを読み込み、Microsoft Graph API を呼び出すトークンを取得します。

    ```java
     package com.azuresamples.msalandroidapp;
    
     import android.os.Bundle;
    
     import androidx.annotation.NonNull;
     import androidx.annotation.Nullable;
     import androidx.fragment.app.Fragment;
    
     import android.util.Log;
     import android.view.LayoutInflater;
     import android.view.View;
     import android.view.ViewGroup;
     import android.widget.Button;
     import android.widget.TextView;
     import android.widget.Toast;
    
     import com.android.volley.Response;
     import com.android.volley.VolleyError;
     import com.microsoft.identity.client.AuthenticationCallback;
     import com.microsoft.identity.client.IAccount;
     import com.microsoft.identity.client.IAuthenticationResult;
     import com.microsoft.identity.client.IPublicClientApplication;
     import com.microsoft.identity.client.ISingleAccountPublicClientApplication;
     import com.microsoft.identity.client.PublicClientApplication;
     import com.microsoft.identity.client.SilentAuthenticationCallback;
     import com.microsoft.identity.client.exception.MsalClientException;
     import com.microsoft.identity.client.exception.MsalException;
     import com.microsoft.identity.client.exception.MsalServiceException;
     import com.microsoft.identity.client.exception.MsalUiRequiredException;
    
     import org.json.JSONObject;
    
     /**
      * Implementation sample for 'Single account' mode.
      * <p>
      * If your app only supports one account being signed-in at a time, this is for you.
      * This requires "account_mode" to be set as "SINGLE" in the configuration file.
      * (Please see res/raw/auth_config_single_account.json for more info).
      * <p>
      * Please note that switching mode (between 'single' and 'multiple' might cause a loss of data.
      */
     public class SingleAccountModeFragment extends Fragment {
         private static final String TAG = SingleAccountModeFragment.class.getSimpleName();
    
         /* UI & Debugging Variables */
         Button signInButton;
         Button signOutButton;
         Button callGraphApiInteractiveButton;
         Button callGraphApiSilentButton;
         TextView scopeTextView;
         TextView graphResourceTextView;
         TextView logTextView;
         TextView currentUserTextView;
         TextView deviceModeTextView;
    
         /* Azure AD Variables */
         private ISingleAccountPublicClientApplication mSingleAccountApp;
         private IAccount mAccount;
    
         @Override
         public View onCreateView(LayoutInflater inflater,
                                  ViewGroup container,
                                  Bundle savedInstanceState) {
             // Inflate the layout for this fragment
             final View view = inflater.inflate(R.layout.fragment_single_account_mode, container, false);
             initializeUI(view);
    
             // Creates a PublicClientApplication object with res/raw/auth_config_single_account.json
             PublicClientApplication.createSingleAccountPublicClientApplication(getContext(),
                     R.raw.auth_config_single_account,
                     new IPublicClientApplication.ISingleAccountApplicationCreatedListener() {
                         @Override
                         public void onCreated(ISingleAccountPublicClientApplication application) {
                             /**
                              * This test app assumes that the app is only going to support one account.
                              * This requires "account_mode" : "SINGLE" in the config json file.
                              **/
                             mSingleAccountApp = application;
                             loadAccount();
                         }
    
                         @Override
                         public void onError(MsalException exception) {
                             displayError(exception);
                         }
                     });
    
             return view;
         }
    
         /**
          * Initializes UI variables and callbacks.
          */
         private void initializeUI(@NonNull final View view) {
             signInButton = view.findViewById(R.id.btn_signIn);
             signOutButton = view.findViewById(R.id.btn_removeAccount);
             callGraphApiInteractiveButton = view.findViewById(R.id.btn_callGraphInteractively);
             callGraphApiSilentButton = view.findViewById(R.id.btn_callGraphSilently);
             scopeTextView = view.findViewById(R.id.scope);
             graphResourceTextView = view.findViewById(R.id.msgraph_url);
             logTextView = view.findViewById(R.id.txt_log);
             currentUserTextView = view.findViewById(R.id.current_user);
             deviceModeTextView = view.findViewById(R.id.device_mode);
    
             final String defaultGraphResourceUrl = MSGraphRequestWrapper.MS_GRAPH_ROOT_ENDPOINT + "v1.0/me";
             graphResourceTextView.setText(defaultGraphResourceUrl);
    
             signInButton.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     if (mSingleAccountApp == null) {
                         return;
                     }
    
                     mSingleAccountApp.signIn(getActivity(), null, getScopes(), getAuthInteractiveCallback());
                 }
             });
    
             signOutButton.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     if (mSingleAccountApp == null) {
                         return;
                     }
    
                     /**
                      * Removes the signed-in account and cached tokens from this app (or device, if the device is in shared mode).
                      */
                     mSingleAccountApp.signOut(new ISingleAccountPublicClientApplication.SignOutCallback() {
                         @Override
                         public void onSignOut() {
                             mAccount = null;
                             updateUI();
                             showToastOnSignOut();
                         }
    
                         @Override
                         public void onError(@NonNull MsalException exception) {
                             displayError(exception);
                         }
                     });
                 }
             });
    
             callGraphApiInteractiveButton.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     if (mSingleAccountApp == null) {
                         return;
                     }
    
                     /**
                      * If acquireTokenSilent() returns an error that requires an interaction (MsalUiRequiredException),
                      * invoke acquireToken() to have the user resolve the interrupt interactively.
                      *
                      * Some example scenarios are
                      *  - password change
                      *  - the resource you're acquiring a token for has a stricter set of requirement than your Single Sign-On refresh token.
                      *  - you're introducing a new scope which the user has never consented for.
                      */
                     mSingleAccountApp.acquireToken(getActivity(), getScopes(), getAuthInteractiveCallback());
                 }
             });
    
             callGraphApiSilentButton.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if (mSingleAccountApp == null) {
                         return;
                     }
    
                     /**
                      * Once you've signed the user in,
                      * you can perform acquireTokenSilent to obtain resources without interrupting the user.
                      */
                     mSingleAccountApp.acquireTokenSilentAsync(getScopes(), mAccount.getAuthority(), getAuthSilentCallback());
                 }
             });
    
         }
    
         @Override
         public void onResume() {
             super.onResume();
    
             /**
              * The account may have been removed from the device (if broker is in use).
              *
              * In shared device mode, the account might be signed in/out by other apps while this app is not in focus.
              * Therefore, we want to update the account state by invoking loadAccount() here.
              */
             loadAccount();
         }
    
         /**
          * Extracts a scope array from a text field,
          * i.e. from "User.Read User.ReadWrite" to ["user.read", "user.readwrite"]
          */
         private String[] getScopes() {
             return scopeTextView.getText().toString().toLowerCase().split(" ");
         }
    
         /**
          * Load the currently signed-in account, if there's any.
          */
         private void loadAccount() {
             if (mSingleAccountApp == null) {
                 return;
             }
    
             mSingleAccountApp.getCurrentAccountAsync(new ISingleAccountPublicClientApplication.CurrentAccountCallback() {
                 @Override
                 public void onAccountLoaded(@Nullable IAccount activeAccount) {
                     // You can use the account data to update your UI or your app database.
                     mAccount = activeAccount;
                     updateUI();
                 }
    
                 @Override
                 public void onAccountChanged(@Nullable IAccount priorAccount, @Nullable IAccount currentAccount) {
                     if (currentAccount == null) {
                         // Perform a cleanup task as the signed-in account changed.
                         showToastOnSignOut();
                     }
                 }
    
                 @Override
                 public void onError(@NonNull MsalException exception) {
                     displayError(exception);
                 }
             });
         }
    
         /**
          * Callback used in for silent acquireToken calls.
          */
         private SilentAuthenticationCallback getAuthSilentCallback() {
             return new SilentAuthenticationCallback() {
    
                 @Override
                 public void onSuccess(IAuthenticationResult authenticationResult) {
                     Log.d(TAG, "Successfully authenticated");
    
                     /* Successfully got a token, use it to call a protected resource - MSGraph */
                     callGraphAPI(authenticationResult);
                 }
    
                 @Override
                 public void onError(MsalException exception) {
                     /* Failed to acquireToken */
                     Log.d(TAG, "Authentication failed: " + exception.toString());
                     displayError(exception);
    
                     if (exception instanceof MsalClientException) {
                         /* Exception inside MSAL, more info inside MsalError.java */
                     } else if (exception instanceof MsalServiceException) {
                         /* Exception when communicating with the STS, likely config issue */
                     } else if (exception instanceof MsalUiRequiredException) {
                         /* Tokens expired or no session, retry with interactive */
                     }
                 }
             };
         }
    
         /**
          * Callback used for interactive request.
          * If succeeds we use the access token to call the Microsoft Graph.
          * Does not check cache.
          */
         private AuthenticationCallback getAuthInteractiveCallback() {
             return new AuthenticationCallback() {
    
                 @Override
                 public void onSuccess(IAuthenticationResult authenticationResult) {
                     /* Successfully got a token, use it to call a protected resource - MSGraph */
                     Log.d(TAG, "Successfully authenticated");
                     Log.d(TAG, "ID Token: " + authenticationResult.getAccount().getClaims().get("id_token"));
    
                     /* Update account */
                     mAccount = authenticationResult.getAccount();
                     updateUI();
    
                     /* call graph */
                     callGraphAPI(authenticationResult);
                 }
    
                 @Override
                 public void onError(MsalException exception) {
                     /* Failed to acquireToken */
                     Log.d(TAG, "Authentication failed: " + exception.toString());
                     displayError(exception);
    
                     if (exception instanceof MsalClientException) {
                         /* Exception inside MSAL, more info inside MsalError.java */
                     } else if (exception instanceof MsalServiceException) {
                         /* Exception when communicating with the STS, likely config issue */
                     }
                 }
    
                 @Override
                 public void onCancel() {
                     /* User canceled the authentication */
                     Log.d(TAG, "User cancelled login.");
                 }
             };
         }
    
         /**
          * Make an HTTP request to obtain MSGraph data
          */
         private void callGraphAPI(final IAuthenticationResult authenticationResult) {
             MSGraphRequestWrapper.callGraphAPIUsingVolley(
                     getContext(),
                     graphResourceTextView.getText().toString(),
                     authenticationResult.getAccessToken(),
                     new Response.Listener<JSONObject>() {
                         @Override
                         public void onResponse(JSONObject response) {
                             /* Successfully called graph, process data and send to UI */
                             Log.d(TAG, "Response: " + response.toString());
                             displayGraphResult(response);
                         }
                     },
                     new Response.ErrorListener() {
                         @Override
                         public void onErrorResponse(VolleyError error) {
                             Log.d(TAG, "Error: " + error.toString());
                             displayError(error);
                         }
                     });
         }
    
         //
         // Helper methods manage UI updates
         // ================================
         // displayGraphResult() - Display the graph response
         // displayError() - Display the graph response
         // updateSignedInUI() - Updates UI when the user is signed in
         // updateSignedOutUI() - Updates UI when app sign out succeeds
         //
    
         /**
          * Display the graph response
          */
         private void displayGraphResult(@NonNull final JSONObject graphResponse) {
             logTextView.setText(graphResponse.toString());
         }
    
         /**
          * Display the error message
          */
         private void displayError(@NonNull final Exception exception) {
             logTextView.setText(exception.toString());
         }
    
         /**
          * Updates UI based on the current account.
          */
         private void updateUI() {
             if (mAccount != null) {
                 signInButton.setEnabled(false);
                 signOutButton.setEnabled(true);
                 callGraphApiInteractiveButton.setEnabled(true);
                 callGraphApiSilentButton.setEnabled(true);
                 currentUserTextView.setText(mAccount.getUsername());
             } else {
                 signInButton.setEnabled(true);
                 signOutButton.setEnabled(false);
                 callGraphApiInteractiveButton.setEnabled(false);
                 callGraphApiSilentButton.setEnabled(false);
                 currentUserTextView.setText("None");
             }
    
             deviceModeTextView.setText(mSingleAccountApp.isSharedDevice() ? "Shared" : "Non-shared");
         }
    
         /**
          * Updates UI when app sign out succeeds
          */
         private void showToastOnSignOut() {
             final String signOutText = "Signed Out.";
             currentUserTextView.setText("");
             Toast.makeText(getContext(), signOutText, Toast.LENGTH_SHORT)
                     .show();
         }
     }
    ```
4. UI を管理 (UI コンポーネントの追加) するには、 *MainActivity.java* 開き、コードを次のコード スニペットに置き換えます。

    ```java
     package com.azuresamples.msalandroidapp;
    
     import android.os.Bundle;
    
     import androidx.annotation.NonNull;
     import androidx.appcompat.app.ActionBarDrawerToggle;
     import androidx.appcompat.app.AppCompatActivity;
     import androidx.appcompat.widget.Toolbar;
     import androidx.constraintlayout.widget.ConstraintLayout;
     import androidx.core.view.GravityCompat;
    
     import android.view.MenuItem;
     import android.view.View;
    
     import androidx.drawerlayout.widget.DrawerLayout;
     import androidx.fragment.app.Fragment;
     import androidx.fragment.app.FragmentTransaction;

     import com.google.android.material.navigation.NavigationView;
    
     public class MainActivity extends AppCompatActivity
             implements NavigationView.OnNavigationItemSelectedListener,
             OnFragmentInteractionListener{
    
         enum AppFragment {
             SingleAccount
         }
    
         private AppFragment mCurrentFragment;
    
         private ConstraintLayout mContentMain;
    
         @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.activity_main);
    
             mContentMain = findViewById(R.id.content_main);
    
             Toolbar toolbar = findViewById(R.id.toolbar);
             setSupportActionBar(toolbar);
             DrawerLayout drawer = findViewById(R.id.drawer_layout);
             NavigationView navigationView = findViewById(R.id.nav_view);
             ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                     this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
             drawer.addDrawerListener(toggle);
             toggle.syncState();
             navigationView.setNavigationItemSelectedListener(this);
    
             //Set default fragment
             navigationView.setCheckedItem(R.id.nav_single_account);
             setCurrentFragment(AppFragment.SingleAccount);
         }
    
         @Override
         public boolean onNavigationItemSelected(final MenuItem item) {
             final DrawerLayout drawer = findViewById(R.id.drawer_layout);
             drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
                 @Override
                 public void onDrawerSlide(@NonNull View drawerView, float slideOffset) { }
    
                 @Override
                 public void onDrawerOpened(@NonNull View drawerView) { }
    
                 @Override
                 public void onDrawerClosed(@NonNull View drawerView) {
                     // Handle navigation view item clicks here.
                     int id = item.getItemId();
    
                     if (id == R.id.nav_single_account) {
                         setCurrentFragment(AppFragment.SingleAccount);
                     }

                     drawer.removeDrawerListener(this);
                 }
    
                 @Override
                 public void onDrawerStateChanged(int newState) { }
             });
    
             drawer.closeDrawer(GravityCompat.START);
             return true;
         }
    
         private void setCurrentFragment(final AppFragment newFragment){
             if (newFragment == mCurrentFragment) {
                 return;
             }
    
             mCurrentFragment = newFragment;
             setHeaderString(mCurrentFragment);
             displayFragment(mCurrentFragment);
         }
    
         private void setHeaderString(final AppFragment fragment){
             switch (fragment) {
                 case SingleAccount:
                     getSupportActionBar().setTitle("Single Account Mode");
                     return;
    
             }
         }
    
         private void displayFragment(final AppFragment fragment){
             switch (fragment) {
                 case SingleAccount:
                     attachFragment(new com.azuresamples.msalandroidapp.SingleAccountModeFragment());
                     return;
    
             }
         }
    
         private void attachFragment(final Fragment fragment) {
             getSupportFragmentManager()
                     .beginTransaction()
                     .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                     .replace(mContentMain.getId(),fragment)
                     .commit();
         }
     }
    ```

注

Android プロジェクト のパッケージ名と一致するようにパッケージ名を更新してください。

#### レイアウト

レイアウトは、UI コンポーネントの配置を指定して、ユーザー インターフェイスの視覚的構造と外観を定義するファイルです。 XML で記述されています。 このチュートリアルで UI をモデル化する場合は、次の XML サンプルが提供されます。

1. **app**&gt;**src**&gt;**main**&gt;**res**&gt;**layout**&gt;**activity\_main.xml** 内で。 **activity\_main.xml** の内容を次のコード スニペットに置き換えて、ボタンとテキスト ボックスを表示します。

    ```xml
     <?xml version="1.0" encoding="utf-8"?>
     <androidx.drawerlayout.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
         xmlns:app="http://schemas.android.com/apk/res-auto"
         xmlns:tools="http://schemas.android.com/tools"
         android:id="@+id/drawer_layout"
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         android:fitsSystemWindows="true"
         tools:openDrawer="start">
    
         <include
             layout="@layout/app_bar_main"
             android:layout_width="match_parent"
             android:layout_height="match_parent" />
    
         <com.google.android.material.navigation.NavigationView
             android:id="@+id/nav_view"
             android:layout_width="wrap_content"
             android:layout_height="match_parent"
             android:layout_gravity="start"
             android:fitsSystemWindows="true"
             app:headerLayout="@layout/nav_header_main"
             app:menu="@menu/activity_main_drawer" />
    
     </androidx.drawerlayout.widget.DrawerLayout>
    ```
2. **app**&gt;**src**&gt;**main**&gt;**res**&gt;**layout**&gt;**app\_bar\_main.xml** 内で。 フォルダーに **app\_bar\_main.xml** がない場合は、次のコード スニペットを作成して追加します。

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <androidx.coordinatorlayout.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">
    
        <com.google.android.material.appbar.AppBarLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:theme="@style/AppTheme.AppBarOverlay">
    
            <androidx.appcompat.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                android:background="?attr/colorPrimary"
                app:popupTheme="@style/AppTheme.PopupOverlay" />
    
        </com.google.android.material.appbar.AppBarLayout>
    
        <include layout="@layout/content_main" />
    
    </androidx.coordinatorlayout.widget.CoordinatorLayout>
    ```
3. **app**&gt;**src**&gt;**main**&gt;**res**&gt;**layout**&gt;**content\_main.xml** 内で。 フォルダーに **content\_main.xml** がない場合は、次のコード スニペットを作成して追加します。

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/content_main"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior"
        tools:context=".MainActivity"
        tools:showIn="@layout/app_bar_main">
    
    </androidx.constraintlayout.widget.ConstraintLayout>
    ```
4. **app**&gt;**src**&gt;**main**&gt;**res**&gt;**layout**&gt;**fragment\_m\_s\_graph\_request\_wrapper.xml** 内で。 フォルダーに **fragment\_m\_s\_graph\_request\_wrapper.xml** がない場合は、次のコード スニペットを作成して追加します。

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MSGraphRequestWrapper">
    
        <!-- TODO: Update blank fragment layout -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:text="@string/hello_blank_fragment" />
    
    </FrameLayout>
    ```
5. **app**&gt;**src**&gt;**main**&gt;**res**&gt;**layout**&gt;**fragment\_on\_interaction\_listener.xml** 内で。 フォルダーに **fragment\_on\_interaction\_listener.xml** がない場合は、次のコード スニペットを作成して追加します。

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".OnFragmentInteractionListener">
    
        <!-- TODO: Update blank fragment layout -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:text="@string/hello_blank_fragment" />
    
    </FrameLayout>
    ```
6. **app**&gt;**src**&gt;**main**&gt;**res**&gt;**layout**&gt;**fragment\_single\_account\_mode.xml** 内で。 フォルダーに **fragment\_single\_account\_mode.xml** がない場合は、次のコード スニペットを作成して追加します。

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".SingleAccountModeFragment">
    
        <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical"
            tools:context=".SingleAccountModeFragment">
    
            <LinearLayout
                android:id="@+id/activity_main"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="vertical"
                android:paddingLeft="@dimen/activity_horizontal_margin"
                android:paddingRight="@dimen/activity_horizontal_margin"
                android:paddingBottom="@dimen/activity_vertical_margin">
    
                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:orientation="horizontal"
                    android:paddingTop="5dp"
                    android:paddingBottom="5dp"
                    android:weightSum="10">
    
                    <TextView
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_weight="3"
                        android:layout_gravity="center_vertical"
                        android:textStyle="bold"
                        android:text="Scope" />
    
                    <LinearLayout
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:orientation="vertical"
                        android:layout_weight="7">
    
                        <EditText
                            android:id="@+id/scope"
                            android:layout_height="wrap_content"
                            android:layout_width="match_parent"
                            android:text="user.read"
                            android:textSize="12sp" />
    
                        <TextView
                            android:layout_height="wrap_content"
                            android:layout_width="match_parent"
                            android:paddingLeft="5dp"
                            android:text="Type in scopes delimited by space"
                            android:textSize="10sp"  />
    
                    </LinearLayout>
                </LinearLayout>
    
                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:orientation="horizontal"
                    android:paddingTop="5dp"
                    android:paddingBottom="5dp"
                    android:weightSum="10">
    
                    <TextView
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_weight="3"
                        android:layout_gravity="center_vertical"
                        android:textStyle="bold"
                        android:text="MSGraph Resource URL" />
    
                    <LinearLayout
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:orientation="vertical"
                        android:layout_weight="7">
    
                        <EditText
                            android:id="@+id/msgraph_url"
                            android:layout_height="wrap_content"
                            android:layout_width="match_parent"
                            android:textSize="12sp" />
                    </LinearLayout>
                </LinearLayout>
    
                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:orientation="horizontal"
                    android:paddingTop="5dp"
                    android:paddingBottom="5dp"
                    android:weightSum="10">
    
                    <TextView
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_weight="3"
                        android:textStyle="bold"
                        android:text="Signed-in user" />
    
                    <TextView
                        android:id="@+id/current_user"
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:paddingLeft="5dp"
                        android:layout_weight="7"
                        android:text="None" />
                </LinearLayout>
    
                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:orientation="horizontal"
                    android:paddingTop="5dp"
                    android:paddingBottom="5dp"
                    android:weightSum="10">
    
                    <TextView
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_weight="3"
                        android:textStyle="bold"
                        android:text="Device mode" />
    
                    <TextView
                        android:id="@+id/device_mode"
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:paddingLeft="5dp"
                        android:layout_weight="7"
                        android:text="None" />
                </LinearLayout>
    
                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:orientation="horizontal"
                    android:paddingTop="5dp"
                    android:paddingBottom="5dp"
                    android:weightSum="10">
    
                    <Button
                        android:id="@+id/btn_signIn"
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_weight="5"
                        android:gravity="center"
                        android:text="Sign In"/>
    
                    <Button
                        android:id="@+id/btn_removeAccount"
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_weight="5"
                        android:gravity="center"
                        android:text="Sign Out"
                        android:enabled="false"/>
                </LinearLayout>

                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:gravity="center"
                    android:orientation="horizontal">
    
                    <Button
                        android:id="@+id/btn_callGraphInteractively"
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_weight="5"
                        android:text="Get Graph Data Interactively"
                        android:enabled="false"/>
    
                    <Button
                        android:id="@+id/btn_callGraphSilently"
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_weight="5"
                        android:text="Get Graph Data Silently"
                        android:enabled="false"/>
                </LinearLayout>

                <TextView
                    android:id="@+id/txt_log"
                    android:layout_width="match_parent"
                    android:layout_height="0dp"
                    android:layout_marginTop="20dp"
                    android:layout_weight="0.8"
                    android:text="Output goes here..." />
    
            </LinearLayout>
        </LinearLayout>
    
    </FrameLayout>
    ```
7. **app**&gt;**src**&gt;**main**&gt;**res**&gt;**layout**&gt;**nav\_header\_main.xml** 内で。 フォルダーに **nav\_header\_main.xml** がない場合は、次のコード スニペットを作成して追加します。

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="@dimen/nav_header_height"
        android:background="@drawable/side_nav_bar"
        android:gravity="bottom"
        android:orientation="vertical"
        android:paddingLeft="@dimen/activity_horizontal_margin"
        android:paddingTop="@dimen/activity_vertical_margin"
        android:paddingRight="@dimen/activity_horizontal_margin"
        android:paddingBottom="@dimen/activity_vertical_margin"
        android:theme="@style/ThemeOverlay.AppCompat.Dark">
    
        <ImageView
            android:id="@+id/imageView"
            android:layout_width="66dp"
            android:layout_height="72dp"
            android:contentDescription="@string/nav_header_desc"
            android:paddingTop="@dimen/nav_header_vertical_spacing"
            app:srcCompat="@drawable/microsoft_logo" />
    
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:paddingTop="@dimen/nav_header_vertical_spacing"
            android:text="Azure Samples"
            android:textAppearance="@style/TextAppearance.AppCompat.Body1" />
    
        <TextView
            android:id="@+id/textView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="MSAL Android" />
    
    </LinearLayout>
    
    ```
8. **app**&gt;**src**&gt;**main**&gt;**res**&gt;**menu**&gt;**activity\_main\_drawer.xml** 内で。 フォルダーに **activity\_main\_drawer.xml** がない場合は、次のコード スニペットを作成して追加します。

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <menu xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        tools:showIn="navigation_view">
        <group android:checkableBehavior="single">
            <item
                android:id="@+id/nav_single_account"
                android:icon="@drawable/ic_single_account_24dp"
                android:title="Single Account Mode" />
    
        </group>
    </menu>
    ```
9. **app**&gt;**src**&gt;**main**&gt;**res**&gt;**values**&gt;**dimens.xml** 内で。 **dimens.xml** の内容を次のコード スニペットに置き換えます。

    ```xml
    <resources>
        <dimen name="fab_margin">16dp</dimen>
        <dimen name="activity_horizontal_margin">16dp</dimen>
        <dimen name="activity_vertical_margin">16dp</dimen>
        <dimen name="nav_header_height">176dp</dimen>
        <dimen name="nav_header_vertical_spacing">8dp</dimen>
    </resources>
    ```
10. **app**&gt;**src**&gt;**main**&gt;**res**&gt;**values**&gt;**colors.xml** 内で。 **colors.xml** の内容を次のコード スニペットに置き換えます。

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <color name="purple_200">#FFBB86FC</color>
        <color name="purple_500">#FF6200EE</color>
        <color name="purple_700">#FF3700B3</color>
        <color name="teal_200">#FF03DAC5</color>
        <color name="teal_700">#FF018786</color>
        <color name="black">#FF000000</color>
        <color name="white">#FFFFFFFF</color>
        <color name="colorPrimary">#008577</color>
        <color name="colorPrimaryDark">#00574B</color>
        <color name="colorAccent">#D81B60</color>
    </resources>
    ```
11. **app**&gt;**src**&gt;**main**&gt;**res**&gt;**values**&gt;**strings.xml** 内で。 **strings.xml** の内容を次のコード スニペットに置き換えます。

    ```xml
    <resources>
        <string name="app_name">MSALAndroidapp</string>
        <string name="action_settings">Settings</string>
        <!-- Strings used for fragments for navigation -->
        <string name="first_fragment_label">First Fragment</string>
        <string name="second_fragment_label">Second Fragment</string>
        <string name="nav_header_desc">Navigation header</string>
        <string name="navigation_drawer_open">Open navigation drawer</string>
        <string name="navigation_drawer_close">Close navigation drawer</string>
        <string name="next">Next</string>
        <string name="previous">Previous</string>
    
        <string name="hello_first_fragment">Hello first fragment</string>
        <string name="hello_second_fragment">Hello second fragment. Arg: %1$s</string>
        <!-- TODO: Remove or change this placeholder text -->
        <string name="hello_blank_fragment">Hello blank fragment</string>
    </resources>
    ```
12. **app**&gt;**src**&gt;**main**&gt;**res**&gt;**values**&gt;**styles.xml** 内で。 フォルダーに **styles.xml** がない場合は、次のコード スニペットを作成して追加します。

    ```xml
    <resources>
    
    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>
    
    <style name="AppTheme.NoActionBar">
        <item name="windowActionBar">false</item>
        <item name="windowNoTitle">true</item>
    </style>
    
    <style name="AppTheme.AppBarOverlay" parent="ThemeOverlay.AppCompat.Dark.ActionBar" />
    
    <style name="AppTheme.PopupOverlay" parent="ThemeOverlay.AppCompat.Light" />
    
    </resources>
    ```
13. **app**&gt;**src**&gt;**main**&gt;**res**&gt;**values**&gt;**themes.xml** 内で。 **themes.xml** の内容を次のコード スニペットに置き換えます。

    ```xml
    <resources xmlns:tools="http://schemas.android.com/tools">
        <!-- Base application theme. -->
        <style name="Theme.MSALAndroidapp" parent="Theme.MaterialComponents.DayNight.DarkActionBar">
            <!-- Primary brand color. -->
            <item name="colorPrimary">@color/purple_500</item>
            <item name="colorPrimaryVariant">@color/purple_700</item>
            <item name="colorOnPrimary">@color/white</item>
            <!-- Secondary brand color. -->
            <item name="colorSecondary">@color/teal_200</item>
            <item name="colorSecondaryVariant">@color/teal_700</item>
            <item name="colorOnSecondary">@color/black</item>
            <!-- Status bar color. -->
            <item name="android:statusBarColor" tools:targetApi="21">?attr/colorPrimaryVariant</item>
            <!-- Customize your theme here. -->
        </style>
    
        <style name="Theme.MSALAndroidapp.NoActionBar">
            <item name="windowActionBar">false</item>
            <item name="windowNoTitle">true</item>
        </style>
    
        <style name="Theme.MSALAndroidapp.AppBarOverlay" parent="ThemeOverlay.AppCompat.Dark.ActionBar" />
    
        <style name="Theme.MSALAndroidapp.PopupOverlay" parent="ThemeOverlay.AppCompat.Light" />
    </resources>
    ```
14. **app**&gt;**src**&gt;**main**&gt;**res**&gt;**drawable**&gt;**ic\_single\_account\_24dp.xml** 内で。 フォルダーに **ic\_single\_account\_24dp.xml** がない場合は、次のコード スニペットを作成して追加します。

    ```xml
    <vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="24dp"
    android:height="24dp"
    android:viewportWidth="24.0"
    android:viewportHeight="24.0">
    <path
        android:fillColor="#FF000000"
        android:pathData="M12,12c2.21,0 4,-1.79 4,-4s-1.79,-4 -4,-4 -4,1.79 -4,4 1.79,4 4,4zM12,14c-2.67,0 -8,1.34 -8,4v2h16v-2c0,-2.66 -5.33,-4 -8,-4z"/>
    </vector>
    ```
15. **app**&gt;**src**&gt;**main**&gt;**res**&gt;**drawable**&gt;**side\_nav\_bar.xml** 内で。 フォルダーに **side\_nav\_bar.xml** がない場合は、次のコード スニペットを作成して追加します。

    ```xml
    <shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <gradient
        android:angle="135"
        android:centerColor="#009688"
        android:endColor="#00695C"
        android:startColor="#4DB6AC"
        android:type="linear" />
    </shape>
    ```
16. **app**&gt;**src**&gt;**main**&gt;**res**&gt;**drawable** 内で。 フォルダーに、`microsoft_logo.png` という名前の png Microsoft ロゴを追加します。

UI を XML で宣言すると、アプリの表示を、その動作を制御するコードから分離できます。 Android レイアウトの詳細については、[「レイアウト」](https://developer.android.com/develop/ui/views/layout/declaring-layout)を参照してください。

## [外部テナントの構成](#tab/android-external)
### ユーザーのサインイン

Android 用 Microsoft Authentication Library (MSAL) を使用してユーザーをサインインするための主なオプションとして、対話形式またはサイレントモードでのトークンの取得の 2 つがあります。

1. 対話形式でユーザーをサインインさせるには、次のコードを使用します。

    ```kotlin
        private fun acquireTokenInteractively() {
        binding.txtLog.text = ""
    
        if (account != null) {
            Toast.makeText(this, "An account is already signed in.", Toast.LENGTH_SHORT).show()
            return
        }
    
        /* Extracts a scope array from text, i.e. from "User.Read User.ReadWrite" to ["user.read", "user.readwrite"] */
        val scopes = scopes.lowercase().split(" ")
        val parameters = AcquireTokenParameters.Builder()
            .startAuthorizationFromActivity(this@MainActivity)
            .withScopes(scopes)
            .withCallback(getAuthInteractiveCallback())
            .build()
    
        authClient.acquireToken(parameters)
    }
    ```

    このコードは、ANDROID 用 MSAL を使用して対話形式でトークンを取得するプロセスを開始します。 最初にテキスト ログ フィールドがクリアされます。 その後、サインインしているアカウントが既に存在するかどうかを確認します。存在する場合は、アカウントが既にサインインしていることを示すトースト メッセージが表示され、返されます。

    次に、テキスト入力からスコープを抽出し、それらを小文字に変換してから配列に分割します。 これらのスコープを使用して、現在のアクティビティからの承認プロセスの開始やコールバックの指定など、トークンを取得するためのパラメーターを構築します。 最後に、構築されたパラメーターを使用して認証クライアントで `acquireToken()` を呼び出し、トークン取得プロセスを開始します。

    コールバックを指定するコードでは、 `getAuthInteractiveCallback()`という関数を使用します。 関数には次のコードが必要です。

    ```kotlin
    private fun getAuthInteractiveCallback(): AuthenticationCallback {
        return object : AuthenticationCallback {
    
            override fun onSuccess(authenticationResult: IAuthenticationResult) {
                /* Successfully got a token, use it to call a protected resource - Web API */
                Log.d(TAG, "Successfully authenticated")
                Log.d(TAG, "ID Token: " + authenticationResult.account.claims?.get("id_token"))
                Log.d(TAG, "Claims: " + authenticationResult.account.claims
    
                /* Reload account asynchronously to get the up-to-date list. */
                CoroutineScope(Dispatchers.Main).launch {
                    accessToken = authenticationResult.accessToken
                    getAccount()
    
                    binding.txtLog.text = getString(R.string.log_token_interactive) +  accessToken
                }
            }
    
            override fun onError(exception: MsalException) {
                /* Failed to acquireToken */
                Log.d(TAG, "Authentication failed: $exception")
    
                accessToken = null
                binding.txtLog.text = getString(R.string.exception_authentication) + exception
    
                if (exception is MsalClientException) {
                    /* Exception inside MSAL, more info inside MsalError.java */
                } else if (exception is MsalServiceException) {
                    /* Exception when communicating with the STS, likely config issue */
                }
            }
    
            override fun onCancel() {
                /* User canceled the authentication */
                Log.d(TAG, "User cancelled login.");
            }
        }
    }
    ```

    コード スニペットは、`getAuthInteractiveCallback`のインスタンスを返す関数`AuthenticationCallback`を定義します。 この関数内に、 `AuthenticationCallback` インターフェイスを実装する匿名クラスが作成されます。

    認証が成功すると (`onSuccess`)、成功した認証がログに記録され、ID トークンと要求が取得され、 `CoroutineScope`を使用してアクセス トークンが非同期的に更新され、新しいアクセス トークンで UI が更新されます。 コードは、 `authenticationResult` から ID トークンを取得し、ログに記録します。 トークン内の要求には、ユーザーの名前、電子メール、その他のプロファイル情報など、ユーザーに関する情報が含まれます。 `authenticationResult.account.claims`にアクセスすることで、現在のアカウントに関連付けられている要求を取得できます。

    認証エラー (`onError`) がある場合は、エラーをログに記録し、アクセス トークンをクリアし、エラー メッセージで UI を更新し、 `MsalClientException` と `MsalServiceException`のより具体的な処理を提供します。 ユーザーが認証 (`onCancel`) を取り消すと、取り消しがログに記録されます。

    必ず import ステートメントを含めます。 Android Studio では、自動的に import ステートメントが含められます。
2. ユーザーをサイレント モードでサインインするには、次のコードを使用します。

    ```kotlin
        private fun acquireTokenSilently() {
        binding.txtLog.text = ""
    
        if (account == null) {
            Toast.makeText(this, "No account available", Toast.LENGTH_SHORT).show()
            return
        }
    
        /* Extracts a scope array from text, i.e. from "User.Read User.ReadWrite" to ["user.read", "user.readwrite"] */
        val scopes = scopes.lowercase().split(" ")
        val parameters = AcquireTokenSilentParameters.Builder()
            .forAccount(account)
            .fromAuthority(account!!.authority)
            .withScopes(scopes)
            .forceRefresh(false)
            .withCallback(getAuthSilentCallback())
            .build()
    
        authClient.acquireTokenSilentAsync(parameters)
    }
    ```

    コードは、トークンをサイレント モードで取得するプロセスを開始します。 最初にテキスト ログをクリアします。 次に、使用可能なアカウントがあるかどうかを確認します。そうでない場合は、これを示すトースト メッセージが表示され、終了します。 次に、テキスト入力からスコープを抽出し、小文字に変換して、それらを配列に分割します。

    これらのスコープを使用して、トークンをサイレントモードで取得するためのパラメーターを構築し、アカウント、機関、スコープ、コールバックを指定します。 最後に、構築されたパラメーターを使用して認証クライアントで `acquireTokenSilentAsync()` を非同期的にトリガーし、サイレント トークン取得プロセスを開始します。

    コールバックを指定するコードでは、 `getAuthSilentCallback()`という関数を使用します。 関数には次のコードが必要です。

    ```kotlin
    private fun getAuthSilentCallback(): SilentAuthenticationCallback {
        return object : SilentAuthenticationCallback {
            override fun onSuccess(authenticationResult: IAuthenticationResult?) {
                Log.d(TAG, "Successfully authenticated")
    
                /* Display Access Token */
                accessToken = authenticationResult?.accessToken
                binding.txtLog.text = getString(R.string.log_token_silent) + accessToken
            }
    
            override fun onError(exception: MsalException?) {
                /* Failed to acquireToken */
                Log.d(TAG, "Authentication failed: $exception")
    
                accessToken = null
                binding.txtLog.text = getString(R.string.exception_authentication) + exception
    
                when (exception) {
                    is MsalClientException -> {
                        /* Exception inside MSAL, more info inside MsalError.java */
                    }
                    is MsalServiceException -> {
                        /* Exception when communicating with the STS, likely config issue */
                    }
                    is MsalUiRequiredException -> {
                        /* Tokens expired or no session, retry with interactive */
                    }
                }
            }
    
        }
    }
    ```

    このコードでは、サイレント認証のコールバックを定義します。 `SilentAuthenticationCallback` インターフェイスを実装し、2 つのメソッドをオーバーライドします。 `onSuccess`メソッドでは、認証が成功したことをログに記録し、アクセス トークンを表示します。

    `onError`メソッドでは、認証エラーをログに記録し、`MsalClientException`や`MsalServiceException`などのさまざまな種類の例外を処理し、必要に応じて対話型認証を使用して再試行することを提案します。

    必ず import ステートメントを含めます。 Android Studio では、自動的に import ステートメントが含められます。

### サインアウト

Android 用 MSAL を使用して Android (Kotlin) アプリからユーザーをサインアウトするには、次のコードを使用します。

```kotlin
private fun removeAccount() {
    binding.userName.text = ""
    binding.txtLog.text = ""

    authClient.signOut(signOutCallback())
}
```

コードは、アプリケーションからアカウントを削除します。 表示されたユーザー名とテキスト ログが消去されます。 次に、認証クライアントを使用してサインアウト プロセスをトリガーし、サインアウト操作の完了を処理するサインアウト コールバックを指定します。

コールバックを指定するコードでは、 `signOutCallback()`という関数を使用します。 関数には次のコードが必要です。

```kotlin
private fun signOutCallback(): ISingleAccountPublicClientApplication.SignOutCallback {
    return object : ISingleAccountPublicClientApplication.SignOutCallback {
        override fun onSignOut() {
            account = null
            updateUI(account)
        }

        override fun onError(exception: MsalException) {
            binding.txtLog.text = getString(R.string.exception_remove_account) + exception
        }
    }
}
```

このコードでは、パブリック クライアント アプリケーション内の 1 つのアカウントのサインアウト コールバックを定義します。 `ISingleAccountPublicClientApplication.SignOutCallback` インターフェイスを実装し、2 つのメソッドをオーバーライドします。

`onSignOut`メソッドでは、現在のアカウントが null 化され、それに応じてユーザー インターフェイスが更新されます。 `onError`メソッドでは、サインアウト プロセス中に発生したエラーをログに記録し、対応する例外メッセージでテキスト ログを更新します。

必ず import ステートメントを含めます。 Android Studio では、自動的に import ステートメントが含められます。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-mobile-app-ios-swift-prepare-app"} -->
## 認証用に iOS (Swift) アプリを準備する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-ios-swift-prepare-app
- Service: identity-platform
- Article date: 2024-06-27
- Summary: このチュートリアルでは、認証用に iOS (Swift) アプリを準備する方法に関するステップ バイ ステップ ガイドを提供します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

::: zone pivot="workforce"

これは、iOS および macOS 用の Microsoft Authentication Library (MSAL) を iOS Swift アプリに追加する方法を示すチュートリアル シリーズの 2 番目のチュートリアルです。

開始する前に、このページの上部にある **[テナントの種類** の選択] セレクターを使用して、テナントの種類を選択します。 Microsoft Entra ID には、 [従業員](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#workforce-tenants) と [外部](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#external-tenants)の 2 つのテナント構成が用意されています。 従業員テナントの構成は、従業員、内部アプリ、およびその他の組織リソースを対象としています。 外部テナントは、顧客向けのアプリ用です。

このチュートリアルでは、次の操作を行います。

- MSAL フレームワークを iOS (Swift) アプリに追加します。
- SDK インスタンスを作成します。
- Xcode プロジェクトの設定を構成します。

### [前提条件]

- Microsoft [Entra 管理センター](https://entra.microsoft.com)に新しいクライアント Web アプリを登録します。 *これは、組織のディレクトリと個人用の Microsoft アカウントのアカウント*用に構成されています。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- [Xcode](https://developer.apple.com/xcode/resources/)。
- iOS (Swift) プロジェクト。

### プラットフォーム リダイレクト URL を追加する

アプリの登録にアプリの種類を指定するには、次の手順に従います。

1. **[管理]** で、 **[認証]**&gt;**[プラットフォームの追加]**&gt;**[iOS/macOS]** の順に選択します。
2. プロジェクトのバンドル ID を入力します。 コード サンプルをダウンロードすると、バンドル ID は `com.microsoft.identitysample.MSALiOS` になります。 独自のプロジェクトを作成している場合は、Xcode でそのプロジェクトを選択し、 **[全般]** タブを開きます。 **[ID]** セクションにバンドル ID が表示されます。
3. **[構成]** をクリックし、後でアプリを構成するときに入力できるように、 **[MSAL の構成]** ページに表示される **[MSAL 構成]** を保存しておきます。
4. **完了**を選択します。

### iOS (Swift) アプリに MSAL フレームワークを追加する

次のいずれかの方法を選択して、アプリに MSAL ライブラリをインストールします。

#### ココアポッド

1. [CocoaPods](https://cocoapods.org/) を使用している場合は、まずプロジェクトの `MSAL` ファイルと同じフォルダーに *podfile* という名前の空のファイルを作成してから、 をインストールします。 *Podfile* に次のコードを追加して保存します:

    ```
    use_frameworks!
    
    target '<your-target-here>' do
       pod 'MSAL'
    end
    ```
2. `<your-target-here>` を自分のプロジェクト名に置き換えます。
3. ターミナル ウィンドウで、作成した *podfile* を含むフォルダーに移動し、`pod install` を実行して MSAL ライブラリをインストールします。
4. Xcode を閉じて `<your project name>.xcworkspace` を開き、Xcode にプロジェクトを再度読み込みます。

#### カルタゴ

[Carthage](https://github.com/Carthage/Carthage) を使用している場合は、`MSAL` に追加することで  をインストールします。

```
github "AzureAD/microsoft-authentication-library-for-objc" "master"
```

ターミナル ウィンドウから、更新された *Cartfile* と同じディレクトリで次のコマンドを実行して、Carthage でのプロジェクトの依存関係を更新します。

iOSの場合:

```bash
carthage update --platform iOS
```

macOSの場合:

```bash
carthage update --platform macOS
```

#### 手動

Git サブモジュールを使用するか、最新のリリースをチェックアウトして、アプリケーションでフレームワークとして使用することもできます。

### アプリの登録を追加する

次に、アプリ登録をコードに追加します。

最初に、*ViewController.swift* ファイルの先頭に次の import ステートメントを追加し、*AppDelegate.swift* または *SceneDelegate.swift* を追加します:

```swift
import MSAL
```

次に、*ViewController.swift*で`viewDidLoad()`の前に次のコードを追加します。

```swift
// Update the below to your client ID. The below is for running the demo only
let kClientID = "Your_Application_Id_Here"
let kGraphEndpoint = "https://graph.microsoft.com/" // the Microsoft Graph endpoint
let kAuthority = "https://login.microsoftonline.com/common" // this authority allows a personal Microsoft account and a work or school account in any organization's Azure AD tenant to sign in

let kScopes: [String] = ["user.read"] // request permission to read the profile of the signed-in user

var accessToken = String()
var applicationContext : MSALPublicClientApplication?
var webViewParameters : MSALWebviewParameters?
var currentAccount: MSALAccount?
```

変更する値は、`kClientID` に割り当てる[アプリケーション ID](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-glossary#application-client-id) の値だけです。 この値は、このチュートリアルの最初のアプリケーションを登録する手順で保存した、MSAL 構成データの一部です。

### SDK インスタンスを作成する

プロジェクトで MSAL インスタンスを作成するには、次の手順に従います。

`ViewController` メソッドを `initMSAL` クラスに追加します:

```swift
    func initMSAL() throws {

        guard let authorityURL = URL(string: kAuthority) else {
            self.updateLogging(text: "Unable to create authority URL")
            return
        }

        let authority = try MSALAADAuthority(url: authorityURL)

        let msalConfiguration = MSALPublicClientApplicationConfig(clientId: kClientID, redirectUri: nil, authority: authority)
        self.applicationContext = try MSALPublicClientApplication(configuration: msalConfiguration)
        self.initWebViewParams()
    }
```

まだ `ViewController` クラス内にあり、`initMSAL` メソッドの後に `initWebViewParams` メソッドを追加します:

#### iOS コード:

```swift
func initWebViewParams() {
        self.webViewParameters = MSALWebviewParameters(authPresentationViewController: self)
    }
```

#### macOS コード:

```swift
func initWebViewParams() {
        self.webViewParameters = MSALWebviewParameters()
    }
```

### Xcode プロジェクト設定の構成

新しいキーチェーン グループをプロジェクトの **[Signing & Capabilities]\(署名と機能\)** に追加します。 このキーチェーン グループは、iOS の場合は `com.microsoft.adalcache`、macOS の場合は `com.microsoft.identity.universalstorage` にする必要があります。

[Image: キーチェーン グループの設定方法を表示している Xcode UI。]

#### iOS のみ: URL スキームを構成する

この手順では、サインイン後にユーザーがアプリにリダイレクトできるように、`CFBundleURLSchemes` を登録します。 なお、`LSApplicationQueriesSchemes` では、アプリでの Microsoft Authenticator の使用も許可されます。

Xcode で、*Info.plist* をソース コード ファイルとして開き、`<dict>` セクション内に以下を追加します。 `[BUNDLE_ID]` を、前に使用した値に置き換えます。 コードをダウンロードした場合、バンドル識別子は `com.microsoft.identitysample.MSALiOS` です。 独自のプロジェクトを作成している場合は、Xcode でそのプロジェクトを選択し、 **[全般]** タブを開きます。 **[ID]** セクションにバンドル ID が表示されます。

```xml
<key>CFBundleURLTypes</key>
<array>
    <dict>
        <key>CFBundleURLSchemes</key>
        <array>
            <string>msauth.[BUNDLE_ID]</string>
        </array>
    </dict>
</array>
<key>LSApplicationQueriesSchemes</key>
<array>
    <string>msauthv2</string>
    <string>msauthv3</string>
</array>
```

#### macOS のみ: App Sandboxを構成する

1. [Xcode プロジェクトの設定] &gt;**[機能] タブ **&gt;** [App Sandbox]** に移動します。
2. **[発信接続 (クライアント)]** チェックボックスをオンにします。

::: zone-end

::: zone pivot="external"

これは、iOS および macOS 用の Microsoft Authentication Library (MSAL) を iOS Swift アプリに追加する方法を示すチュートリアル シリーズの 2 番目のチュートリアルです。

開始する前に、このページの上部にある **[テナントの種類** の選択] セレクターを使用して、テナントの種類を選択します。 Microsoft Entra ID には、 [従業員](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#workforce-tenants) と [外部](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#external-tenants)の 2 つのテナント構成が用意されています。 従業員テナントの構成は、従業員、内部アプリ、およびその他の組織リソースを対象としています。 外部テナントは、顧客向けのアプリ用です。

このチュートリアルでは、次の操作を行います。

- MSAL フレームワークを iOS (Swift) アプリに追加します。
- SDK インスタンスを作成します。

### [前提条件]

- Microsoft [Entra 管理センター](https://entra.microsoft.com)に新しいクライアント Web アプリを登録します。 *これは、組織のディレクトリと個人用の Microsoft アカウントのアカウント*用に構成されています。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- [Xcode](https://developer.apple.com/xcode/resources/)。
- iOS (Swift) プロジェクト。

### プラットフォーム リダイレクト URL を追加する

アプリの登録にアプリの種類を指定するには、次の手順に従います。

1. **[管理]** で、 **[認証]**&gt;**[プラットフォームの追加]**&gt;**[iOS/macOS]** の順に選択します。
2. プロジェクトのバンドル ID を入力します。 コード サンプルをダウンロードすると、バンドル ID は `com.microsoft.identitysample.MSALiOS` になります。 独自のプロジェクトを作成している場合は、Xcode でそのプロジェクトを選択し、 **[全般]** タブを開きます。 **[ID]** セクションにバンドル ID が表示されます。
3. **[構成]** をクリックし、後でアプリを構成するときに入力できるように、 **[MSAL の構成]** ページに表示される **[MSAL 構成]** を保存しておきます。
4. **完了**を選択します。

### パブリック クライアント フローを有効にする

アプリをパブリック クライアントとして識別するには、次の手順に従います。

1. **[管理]** で、 **[認証]** を選択します。
2. **[詳細設定]** で、**[パブリック クライアント フローを許可する]** に対して **[はい]** を選択します。
3. **[保存]** を選択して変更を保存します。

### iOS (Swift) アプリに MSAL フレームワークを追加する

MSAL 認証 SDK は、標準の OAuth2 と OpenID Connect を使用してアプリに認証を統合するために使用されます。 これにより、Microsoft ID を使用してユーザーまたはアプリにサインインできます。 iOS (Swift) プロジェクトに MSAL を追加するには、次の手順に従います。

1. Xcode で iOS プロジェクトを開きます。
2. [**ファイル**] メニューから [**パッケージの依存関係の追加...**] を選択します。
3. パッケージ URL として「`https://github.com/AzureAD/microsoft-authentication-library-for-objc`」と入力し、[**パッケージの追加]** を選択します

#### バンドル識別子を更新する

Apple エコシステムでは、バンドル識別子はアプリケーションの一意の識別子です。 プロジェクトのバンドル識別子を更新するには、次の手順に従います。

1. プロジェクトの設定を開きます。 **アイデンティティ** セクションに **バンドル ID** を入力してください。
2. **Info.plist** を右クリックし、 **[形式を指定して開く]**&gt;**[ソース コード]** を選択します。
3. dict ルート ノードの下の `Enter_the_bundle_Id_Here` を、ポータルで使用した ***Bundle Id*** に置き換えます。 文字列の `msauth.` プレフィックスに注目します。

    ```xml
    <key>CFBundleURLTypes</key>
    <array>
       <dict>
          <key>CFBundleURLSchemes</key>
          <array>
             <string>msauth.Enter_the_Bundle_Id_Here</string>
          </array>
       </dict>
    </array>
    ```

### SDK インスタンスを作成する

プロジェクトで MSAL インスタンスを作成するには、次の手順に従います。

1. `import MSAL` クラスの上部に`ViewController`を追加して、MSAL ライブラリをビュー コントローラーにインポートします。
2. `applicationContext`関数の直前に次のコードを追加して、`viewDidLoad()`メンバー変数を ViewController クラスに追加します。

    ```swift
    var applicationContext : MSALPublicClientApplication?
    var webViewParameters : MSALWebviewParameters?
    ```

    このコードでは、`applicationContext`のインスタンスを格納する `MSALPublicClientApplication` と、`webViewParameters`のインスタンスを格納する `MSALWebviewParameters` の 2 つの変数を宣言します。 `MSALPublicClientApplication` は、パブリック クライアント アプリケーションを処理するために MSAL によって提供されるクラスです。 `MSALWebviewParameters`は、認証プロセス中に使用される Web ビューを構成するためのパラメーターを定義する MSAL によって提供されるクラスです。
3. ビュー `viewDidLoad()` 関数に次のコードを追加します。

    ```swift
     do {
            try self.initMSAL()
        } catch let error {
            self.updateLogging(text: "Unable to create Application Context \(error)")
        }
    ```

    このコードは、プロセス中に発生したエラーを処理して、MSAL の初期化を試みます。 エラーが発生した場合は、エラーの詳細でログ記録を更新します。
4. MSAL を初期化する `initMSAL()` 関数を作成する次のコードを追加します。

    ```swift
        func initMSAL() throws {
    
        guard let authorityURL = URL(string: Configuration.kAuthority) else {
            self.updateLogging(text: "Unable to create authority URL")
            return
        }
    
        let authority = try MSALCIAMAuthority(url: authorityURL)
    
        let msalConfiguration = MSALPublicClientApplicationConfig(clientId: Configuration.kClientID,
                                                                  redirectUri: Configuration.kRedirectUri,
                                                                  authority: authority)
        self.applicationContext = try MSALPublicClientApplication(configuration: msalConfiguration)
    }
    ```

    このコードは、iOS 用の MSAL を初期化します。 最初に、指定された *Configuration.kAuthority* 文字列を使用して、機関の URL の作成を試みます。 成功した場合は、その URL に基づいて MSAL 機関オブジェクトが作成されます。 次に、指定されたクライアント ID、リダイレクト URI、および機関を使用して `MSALPublicClientApplication` を構成します。 すべての構成が正しく設定されている場合は、構成された `MSALPublicClientApplication`を使用してアプリケーション コンテキストが初期化されます。 プロセス中にエラーが発生した場合は、エラーをスローします。
5. *Configuration.swift* ファイルを作成し、次の構成を追加します。

    ```swift
    import Foundation
    
    @objcMembers
    class Configuration {
        static let kTenantSubdomain = "Enter_the_Tenant_Subdomain_Here"
    
        // Update the below to your client ID you received in the portal.
        static let kClientID = "Enter_the_Application_Id_Here"
        static let kRedirectUri = "Enter_the_Redirect_URI_Here"
        static let kProtectedAPIEndpoint = "Enter_the_Protected_API_Full_URL_Here"
        static let kScopes = ["Enter_the_Protected_API_Scopes_Here"]
    
        static let kAuthority = "https://\(kTenantSubdomain).ciamlogin.com"
    
    }
    ```

    この Swift 構成コードは、 `Configuration` という名前のクラスを定義し、 `@objcMembers`でマークされます。 これには、認証のセットアップに関連するさまざまな構成パラメーターの静的定数が含まれています。 これらのパラメーターには、 *テナント サブドメイン*、 *クライアント ID*、 *リダイレクト URI*、 *保護された API エンドポイント*、 *スコープが含まれます*。 これらの構成定数は、アプリケーションのセットアップに固有の適切な値で更新する必要があります。

    プレースホルダーを見つけてください。

    - `Enter_the_Application_Id_Here` を、前に登録したアプリの**アプリケーション (クライアント) ID** に置き換えます。
    - `Enter_the_Redirect_URI_Here` プラットフォーム リダイレクト URL を追加したときにダウンロードした MSAL 構成ファイルの *kRedirectUri* の値に置き換えます。
    - `Enter_the_Protected_API_Scopes_Here` を先ほど記録したスコープに置換します。 スコープを記録していない場合は、このスコープ リストを空のままにすることができます。
    - `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント サブドメインがわからない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)る方法について説明します。

#### カスタム URL ドメインを使用する (省略可能)

カスタム ドメインを使用して、認証 URL を完全にブランド化します。 ユーザーの視点から見ると、認証プロセスの間、ユーザーは *ciamlogin.com* ドメイン名にリダイレクトされず、あなたのドメインにとどまります。

カスタム ドメインを使用するには、以下の手順を実行します。

1. 「[外部テナント内のアプリに対するカスタム URL ドメインの有効化](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-url-domain)」の手順を使用し、外部テナントに対してカスタム URL ドメインを有効にします。
2. *Configuration.swift* ファイルを開きます。

    1. `kAuthority` プロパティの値を *https://Enter\_the\_Custom\_Domain\_Here/Enter\_the\_Tenant\_ID\_Here* に更新します。 `Enter_the_Custom_Domain_Here` を実際のカスタム URL ドメインに、`Enter_the_Tenant_ID_Here` を実際のテナント ID に置き換えます。 テナント ID がわからない場合は、[テナントの詳細を読み取る](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)方法を確認してください。

*Configuration.swift* ファイルに変更を加えた後、カスタム URL ドメインが *login.contoso.com* され、テナント ID が *aaaabbbb-0000-cccc-1111-dddd222eeee* の場合、ファイルは次のスニペットのようになります。

```swift
    import Foundation

    @objcMembers
    class Configuration {
        static let kTenantSubdomain = "login.contoso.com"
        
        // Update the below to your client ID you received in the portal.
        static let kClientID = "Enter_the_Application_Id_Here"
        static let kRedirectUri = "Enter_the_Redirect_URI_Here"
        static let kProtectedAPIEndpoint = "Enter_the_Protected_API_Full_URL_Here"
        static let kScopes = ["Enter_the_Protected_API_Scopes_Here"]
        
        static let kAuthority = "https://\(kTenantSubdomain)/aaaabbbb-0000-cccc-1111-dddd2222eeee"
    
    }
```

### 次のステップ

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-mobile-app-ios-swift-sign-in"} -->
## iOS (Swift) アプリでユーザーをサインインさせて認証する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-ios-swift-sign-in
- Service: identity-platform
- Article date: 2024-05-09
- Summary: チュートリアルでは、iOS(Swift)アプリでユーザーをサインインさせて認証する方法について、ステップバイステップで説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

::: zone pivot="workforce"

これは、Microsoft Entra ID を使用してユーザーをサインインさせる方法を説明するチュートリアル シリーズの 3 番目のチュートリアルです。

開始する前に、このページの上部にある **[テナントの種類** の選択] セレクターを使用して、テナントの種類を選択します。 Microsoft Entra ID には、 [従業員](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#workforce-tenants) と [外部](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#external-tenants)の 2 つのテナント構成が用意されています。 従業員テナントの構成は、従業員、内部アプリ、およびその他の組織リソースを対象としています。 外部テナントは、顧客向けのアプリ用です。

このチュートリアルでは、次の操作を行います。

- ユーザーをサインインします。
- ユーザーをログアウトします。
- アプリの UI の作成

### [前提条件]

- [チュートリアル: 認証用に iOS (Swift) アプリを準備する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-ios-swift-prepare-app)

### ユーザーのサインイン

iOS 用 Microsoft Authentication Library (MSAL) を使用してユーザーをサインインさせるには、主に 2 つのオプションがあります: 対話形式でトークンを取得するか、サイレントで取得します。

1. 対話形式でユーザーをサインインさせるには、次のコードを使用します。

    ```swift
    func acquireTokenInteractively() {
    
        guard let applicationContext = self.applicationContext else { return }
        guard let webViewParameters = self.webViewParameters else { return }
    
        // #1
        let parameters = MSALInteractiveTokenParameters(scopes: kScopes, webviewParameters: webViewParameters)
        parameters.promptType = .selectAccount
    
        // #2
        applicationContext.acquireToken(with: parameters) { (result, error) in
    
            // #3
            if let error = error {
    
                self.updateLogging(text: "Could not acquire token: \(error)")
                return
            }
    
            guard let result = result else {
    
                self.updateLogging(text: "Could not acquire token: No result returned")
                return
            }
    
            // #4
            self.accessToken = result.accessToken
            self.updateLogging(text: "Access token is \(self.accessToken)")
            self.updateCurrentAccount(account: result.account)
            self.getContentWithToken()
        }
    }
    ```

    `promptType` の `MSALInteractiveTokenParameters` プロパティによって、認証および同意プロンプトの動作が構成されます。 次の値がサポートされています。

    - `.promptIfNecessary` (既定値) - 必要な場合にのみ、ユーザーに対してメッセージが表示されます。 SSO のエクスペリエンスは、Web ビュー内に Cookie があるかどうかと、アカウントの種類によって決まります。 複数のユーザーがサインインしている場合は、アカウントの選択エクスペリエンスが表示されます。 "*これは既定の動作です*"。
    - `.selectAccount` - ユーザーが指定されていない場合、認証 Web ビューには、現在サインインしているアカウントの一覧が表示され、ユーザーを選択できます。
    - `.login` - Web ビューで認証を行うことをユーザーに求めます。 この値を指定した場合、一度にサインインできるアカウントは 1 つだけです。
    - `.consent` - 要求の現在のスコープ セットに同意することをユーザーに求めます。
2. ユーザーをサイレント モードでサインインするには、次のコードを使用します。

    ```swift
    
        func acquireTokenSilently(_ account : MSALAccount!) {
    
            guard let applicationContext = self.applicationContext else { return }
    
            /**
    
             Acquire a token for an existing account silently
    
             - forScopes:           Permissions you want included in the access token received
             in the result in the completionBlock. Not all scopes are
             guaranteed to be included in the access token returned.
             - account:             An account object that we retrieved from the application object before that the
             authentication flow will be locked down to.
             - completionBlock:     The completion block that will be called when the authentication
             flow completes, or encounters an error.
             */
    
            let parameters = MSALSilentTokenParameters(scopes: kScopes, account: account)
    
            applicationContext.acquireTokenSilent(with: parameters) { (result, error) in
    
                if let error = error {
    
                    let nsError = error as NSError
    
                    // interactionRequired means we need to ask the user to sign-in. This usually happens
                    // when the user's Refresh Token is expired or if the user has changed their password
                    // among other possible reasons.
    
                    if (nsError.domain == MSALErrorDomain) {
    
                        if (nsError.code == MSALError.interactionRequired.rawValue) {
    
                            DispatchQueue.main.async {
                                self.acquireTokenInteractively()
                            }
                            return
                        }
                    }
    
                    self.updateLogging(text: "Could not acquire token silently: \(error)")
                    return
                }
    
                guard let result = result else {
    
                    self.updateLogging(text: "Could not acquire token: No result returned")
                    return
                }
    
                self.accessToken = result.accessToken
                self.updateLogging(text: "Refreshed Access token is \(self.accessToken)")
                self.updateSignOutButton(enabled: true)
                self.getContentWithToken()
            }
        }
    ```

    `acquireTokenSilently` メソッドでは、既存の MSAL アカウントのアクセス トークンをサイレントに取得しようとします。 `applicationContext`を使用して、指定されたスコープでトークンをリクエストします。 エラーが発生した場合は、ユーザーの操作が必要かどうかを確認し、必要であれば、対話型トークンの取得を開始します。 成功すると、アクセス トークンを更新し、結果をログに記録し、サインアウト ボタンを有効にし、トークンを使用してコンテンツを取得します。

#### サインイン コールバックを処理する (iOS のみ)

*AppDelegate.swift* ファイルを開きます。 サインイン後のコールバックを処理するには、次のように `MSALPublicClientApplication.handleMSALResponse` クラスに `appDelegate` を追加します。

```swift
// Inside AppDelegate...
func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool {

        return MSALPublicClientApplication.handleMSALResponse(url, sourceApplication: options[UIApplication.OpenURLOptionsKey.sourceApplication] as? String)
}

```

**Xcode 11 を使用している場合は**、代わりに MSAL コールバックを *SceneDelegate.swift* に配置する必要があります。 以前の iOS との互換性を保持するために UISceneDelegate と UIApplicationDelegate の両方をサポートしている場合は、MSAL コールバックを両方のファイルに配置する必要があります。

```swift
func scene(_ scene: UIScene, openURLContexts URLContexts: Set<UIOpenURLContext>) {

        guard let urlContext = URLContexts.first else {
            return
        }

        let url = urlContext.url
        let sourceApp = urlContext.options.sourceApplication

        MSALPublicClientApplication.handleMSALResponse(url, sourceApplication: sourceApp)
    }
```

### ユーザーをサインアウトする

Von Bedeutung

MSAL でサインアウトすると、ユーザーに関する既知の情報がすべてアプリケーションから削除されるほか、デバイス構成で許可されている場合、デバイスのアクティブなセッションも削除されます。 必要に応じて、ブラウザーからユーザーをサインアウトさせることもできます。

サインアウト機能を追加するには、`ViewController` クラスの内部に次のコードを追加します。

```swift
@objc func signOut(_ sender: AnyObject) {

        guard let applicationContext = self.applicationContext else { return }

        guard let account = self.currentAccount else { return }

        do {

            /**
             Removes all tokens from the cache for this application for the provided account

             - account:    The account to remove from the cache
             */

            let signoutParameters = MSALSignoutParameters(webviewParameters: self.webViewParameters!)
            signoutParameters.signoutFromBrowser = false // set this to true if you also want to signout from browser or webview

            applicationContext.signout(with: account, signoutParameters: signoutParameters, completionBlock: {(success, error) in

                if let error = error {
                    self.updateLogging(text: "Couldn't sign out account with error: \(error)")
                    return
                }

                self.updateLogging(text: "Sign out completed successfully")
                self.accessToken = ""
                self.updateCurrentAccount(account: nil)
            })

        }
    }
```

### アプリの UI の作成

ここで、次のコードを `ViewController` クラスに追加して、Microsoft Graph API を呼び出すボタン、サインアウトするボタン、および出力を表示するためのテキスト ビューを含む UI を作成します:

#### iOS ユーザーインターフェース

```swift
var loggingText: UITextView!
var signOutButton: UIButton!
var callGraphButton: UIButton!
var usernameLabel: UILabel!

func initUI() {

    usernameLabel = UILabel()
    usernameLabel.translatesAutoresizingMaskIntoConstraints = false
    usernameLabel.text = ""
    usernameLabel.textColor = .darkGray
    usernameLabel.textAlignment = .right

    self.view.addSubview(usernameLabel)

    usernameLabel.topAnchor.constraint(equalTo: view.topAnchor, constant: 50.0).isActive = true
    usernameLabel.rightAnchor.constraint(equalTo: view.rightAnchor, constant: -10.0).isActive = true
    usernameLabel.widthAnchor.constraint(equalToConstant: 300.0).isActive = true
    usernameLabel.heightAnchor.constraint(equalToConstant: 50.0).isActive = true

    // Add call Graph button
    callGraphButton  = UIButton()
    callGraphButton.translatesAutoresizingMaskIntoConstraints = false
    callGraphButton.setTitle("Call Microsoft Graph API", for: .normal)
    callGraphButton.setTitleColor(.blue, for: .normal)
    callGraphButton.addTarget(self, action: #selector(callGraphAPI(_:)), for: .touchUpInside)
    self.view.addSubview(callGraphButton)

    callGraphButton.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
    callGraphButton.topAnchor.constraint(equalTo: view.topAnchor, constant: 120.0).isActive = true
    callGraphButton.widthAnchor.constraint(equalToConstant: 300.0).isActive = true
    callGraphButton.heightAnchor.constraint(equalToConstant: 50.0).isActive = true

    // Add sign out button
    signOutButton = UIButton()
    signOutButton.translatesAutoresizingMaskIntoConstraints = false
    signOutButton.setTitle("Sign Out", for: .normal)
    signOutButton.setTitleColor(.blue, for: .normal)
    signOutButton.setTitleColor(.gray, for: .disabled)
    signOutButton.addTarget(self, action: #selector(signOut(_:)), for: .touchUpInside)
    self.view.addSubview(signOutButton)

    signOutButton.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
    signOutButton.topAnchor.constraint(equalTo: callGraphButton.bottomAnchor, constant: 10.0).isActive = true
    signOutButton.widthAnchor.constraint(equalToConstant: 150.0).isActive = true
    signOutButton.heightAnchor.constraint(equalToConstant: 50.0).isActive = true

    let deviceModeButton = UIButton()
    deviceModeButton.translatesAutoresizingMaskIntoConstraints = false
    deviceModeButton.setTitle("Get device info", for: .normal);
    deviceModeButton.setTitleColor(.blue, for: .normal);
    deviceModeButton.addTarget(self, action: #selector(getDeviceMode(_:)), for: .touchUpInside)
    self.view.addSubview(deviceModeButton)

    deviceModeButton.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
    deviceModeButton.topAnchor.constraint(equalTo: signOutButton.bottomAnchor, constant: 10.0).isActive = true
    deviceModeButton.widthAnchor.constraint(equalToConstant: 150.0).isActive = true
    deviceModeButton.heightAnchor.constraint(equalToConstant: 50.0).isActive = true

    // Add logging textfield
    loggingText = UITextView()
    loggingText.isUserInteractionEnabled = false
    loggingText.translatesAutoresizingMaskIntoConstraints = false

    self.view.addSubview(loggingText)

    loggingText.topAnchor.constraint(equalTo: deviceModeButton.bottomAnchor, constant: 10.0).isActive = true
    loggingText.leftAnchor.constraint(equalTo: self.view.leftAnchor, constant: 10.0).isActive = true
    loggingText.rightAnchor.constraint(equalTo: self.view.rightAnchor, constant: -10.0).isActive = true
    loggingText.bottomAnchor.constraint(equalTo: self.view.bottomAnchor, constant: 10.0).isActive = true
}

func platformViewDidLoadSetup() {

    NotificationCenter.default.addObserver(self,
                        selector: #selector(appCameToForeGround(notification:)),
                        name: UIApplication.willEnterForegroundNotification,
                        object: nil)

}

@objc func appCameToForeGround(notification: Notification) {
    self.loadCurrentAccount()
}

```

#### macOSのUI

```swift

var callGraphButton: NSButton!
var loggingText: NSTextView!
var signOutButton: NSButton!

var usernameLabel: NSTextField!

func initUI() {

    usernameLabel = NSTextField()
    usernameLabel.translatesAutoresizingMaskIntoConstraints = false
    usernameLabel.stringValue = ""
    usernameLabel.isEditable = false
    usernameLabel.isBezeled = false
    self.view.addSubview(usernameLabel)

    usernameLabel.topAnchor.constraint(equalTo: view.topAnchor, constant: 30.0).isActive = true
    usernameLabel.rightAnchor.constraint(equalTo: view.rightAnchor, constant: -10.0).isActive = true

    // Add call Graph button
    callGraphButton  = NSButton()
    callGraphButton.translatesAutoresizingMaskIntoConstraints = false
    callGraphButton.title = "Call Microsoft Graph API"
    callGraphButton.target = self
    callGraphButton.action = #selector(callGraphAPI(_:))
    callGraphButton.bezelStyle = .rounded
    self.view.addSubview(callGraphButton)

    callGraphButton.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
    callGraphButton.topAnchor.constraint(equalTo: view.topAnchor, constant: 50.0).isActive = true
    callGraphButton.heightAnchor.constraint(equalToConstant: 34.0).isActive = true

    // Add sign out button
    signOutButton = NSButton()
    signOutButton.translatesAutoresizingMaskIntoConstraints = false
    signOutButton.title = "Sign Out"
    signOutButton.target = self
    signOutButton.action = #selector(signOut(_:))
    signOutButton.bezelStyle = .texturedRounded
    self.view.addSubview(signOutButton)

    signOutButton.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
    signOutButton.topAnchor.constraint(equalTo: callGraphButton.bottomAnchor, constant: 10.0).isActive = true
    signOutButton.heightAnchor.constraint(equalToConstant: 34.0).isActive = true
    signOutButton.isEnabled = false

    // Add logging textfield
    loggingText = NSTextView()
    loggingText.translatesAutoresizingMaskIntoConstraints = false

    self.view.addSubview(loggingText)

    loggingText.topAnchor.constraint(equalTo: signOutButton.bottomAnchor, constant: 10.0).isActive = true
    loggingText.leftAnchor.constraint(equalTo: self.view.leftAnchor, constant: 10.0).isActive = true
    loggingText.rightAnchor.constraint(equalTo: self.view.rightAnchor, constant: -10.0).isActive = true
    loggingText.bottomAnchor.constraint(equalTo: self.view.bottomAnchor, constant: -10.0).isActive = true
    loggingText.widthAnchor.constraint(equalToConstant: 500.0).isActive = true
    loggingText.heightAnchor.constraint(equalToConstant: 300.0).isActive = true
}

func platformViewDidLoadSetup() {}

```

次に、`ViewController` クラス内でも、`viewDidLoad()` メソッドを次のように置き換えます。

```swift
    override func viewDidLoad() {

        super.viewDidLoad()

        initUI()

        do {
            try self.initMSAL()
        } catch let error {
            self.updateLogging(text: "Unable to create Application Context \(error)")
        }

        self.loadCurrentAccount()
        self.platformViewDidLoadSetup()
    }
```

::: zone-end

::: zone pivot="external"

これは、Microsoft Entra ID を使用してユーザーをサインインさせる方法を説明するチュートリアル シリーズの 3 番目のチュートリアルです。

開始する前に、このページの上部にある **[テナントの種類** の選択] セレクターを使用して、テナントの種類を選択します。 Microsoft Entra ID には、 [従業員](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#workforce-tenants) と [外部](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#external-tenants)の 2 つのテナント構成が用意されています。 従業員テナントの構成は、従業員、内部アプリ、およびその他の組織リソースを対象としています。 外部テナントは、顧客向けのアプリ用です。

このチュートリアルでは、次の操作を行います。

- ユーザーをサインインします。
- ユーザーをログアウトします。

### [前提条件]

- [チュートリアル: 認証用に iOS (Swift) アプリを準備する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-ios-swift-prepare-app)

### ユーザーのサインイン

iOS 用 Microsoft Authentication Library (MSAL) を使用してユーザーをサインインさせるには、主に 2 つのオプションがあります: 対話形式でトークンを取得するか、サイレントで取得します。

1. 対話形式でユーザーをサインインさせるには、次のコードを使用します。

    ```swift
    acquireTokenInteractively() {
        guard let applicationContext = self.applicationContext else { return }
        guard let webViewParameters = self.webViewParameters else { return }
    
        updateLogging(text: "Acquiring token interactively...")
    
        let parameters = MSALInteractiveTokenParameters(scopes: Configuration.kScopes, webviewParameters: webViewParameters)
        parameters.promptType = .selectAccount
    
        applicationContext.acquireToken(with: parameters) { (result, error) in
    
            if let error = error {
    
                self.updateLogging(text: "Could not acquire token: \(error)")
                return
            }
    
            guard let result = result else {
    
                self.updateLogging(text: "Could not acquire token: No result returned")
                return
            }
    
            self.accessToken = result.accessToken
            self.updateLogging(text: "Access token is \(self.accessToken)")
            self.updateCurrentAccount(account: result.account)
        }
    }
    ```

    このコードでは、最初に、アプリケーション・コンテキスト・パラメーターと Web ビュー・パラメーターが使用可能かどうかを確認します。 次に、ログを更新して、トークンを対話形式で取得していることを示します。 次に、対話型トークン取得のパラメーターを設定し、スコープと Web ビュー パラメーターを指定します。 また、アカウントを選択するためのプロンプトタイプも設定します。

    その後、定義されたパラメータを使用して、アプリケーションコンテキストで `acquireToken` メソッドを呼び出します。 完了ハンドラでは、エラーがないかチェックします。 エラーが発生した場合は、エラーメッセージでログを更新します。 成功した場合は、結果からアクセストークンを取得し、トークンでログを更新し、現在のアカウントを更新します。

    アプリがアクセス トークンを取得したら、現在のアカウントに関連付けられている要求を取得できます。 これを行うには、次のコード スニペットを使用します。

    ```swift
    let claims = result.account.accountClaims
    let preferredUsername = claims?["preferred_username"] as? String
    ```

    このコードは、`accountClaims` オブジェクトの `result.account` プロパティにアクセスして、アカウントから請求を読み取ります。 次に、要求ディクショナリから "preferred\_username" 要求の値を取得し、それを `preferredUsername` 変数に割り当てます。
2. ユーザーをサイレント モードでサインインするには、次のコードを使用します。

    ```swift
    func acquireTokenSilently() {
        self.loadCurrentAccount { (account) in
    
            guard let currentAccount = account else {
    
                self.updateLogging(text: "No token found, try to acquire a token interactively first")
                return
            }
    
            self.acquireTokenSilently(currentAccount)
        }
    }
    ```

    このコードは、トークンを取得するプロセスをサイレントに開始します。 最初に現在のアカウントの読み込みを試みます。 現在のアカウントが見つかった場合、そのアカウントを使用してトークンをサイレントに取得します。 現在のアカウントが見つからない場合は、トークンが見つからないことを示すログを更新し、最初に対話形式でトークンを取得することを提案します。

    上記のコードでは、 `loadCurrentAccount` と `acquireTokenSilently` の 2 つの関数を呼び出しています。 `loadCurrentAccount` 関数には、次のコードが必要です。

    ```swift
    func loadCurrentAccount(completion: AccountCompletion? = nil) {
    
        guard let applicationContext = self.applicationContext else { return }
    
        let msalParameters = MSALParameters()
        msalParameters.completionBlockQueue = DispatchQueue.main
    
        // Note that this sample showcases an app that signs in a single account at a time
        applicationContext.getCurrentAccount(with: msalParameters, completionBlock: { (currentAccount, previousAccount, error) in
    
            if let error = error {
                self.updateLogging(text: "Couldn't query current account with error: \(error)")
                return
            }
    
            if let currentAccount = currentAccount {
    
                self.updateCurrentAccount(account: currentAccount)
                self.acquireTokenSilently(currentAccount)
    
                if let completion = completion {
                    completion(self.currentAccount)
                }
    
                return
            }
    
            // If testing with Microsoft's shared device mode, see the account that has been signed out from another app. More details here:
            // https://docs.microsoft.com/azure/active-directory/develop/msal-ios-shared-devices
            if let previousAccount = previousAccount {
    
                self.updateLogging(text: "The account with username \(String(describing: previousAccount.username)) has been signed out.")
    
            } else {
    
                self.updateLogging(text: "")
            }
    
            self.accessToken = ""
            self.updateCurrentAccount(account: nil)
    
            if let completion = completion {
                completion(nil)
            }
        })
    }
    ```

    このコードでは、iOS 用の MSAL を使用して現在のアカウントを読み込みます。 エラーをチェックし、それに応じてログを更新します。 現在のアカウントが見つかった場合は、それを更新し、サイレントにトークンの取得を試みます。 以前のアカウントが存在する場合は、サインアウトがログに記録されます。アカウントが見つからない場合は、アクセストークンをクリアします。 最後に、完了ブロックが指定されている場合は実行します。

    `acquireTokenSilently` 関数には、次のコードが含まれている必要があります。

    ```swift
    func acquireTokenSilently(_ account : MSALAccount) {
        guard let applicationContext = self.applicationContext else { return }
    
        /**
    
         Acquire a token for an existing account silently
    
         - forScopes:           Permissions you want included in the access token received
         in the result in the completionBlock. Not all scopes are
         guaranteed to be included in the access token returned.
         - account:             An account object that we retrieved from the application object before that the
         authentication flow will be locked down to.
         - completionBlock:     The completion block that will be called when the authentication
         flow completes, or encounters an error.
         */
    
        updateLogging(text: "Acquiring token silently...")
    
        let parameters = MSALSilentTokenParameters(scopes: Configuration.kScopes, account: account)
    
        applicationContext.acquireTokenSilent(with: parameters) { (result, error) in
    
            if let error = error {
    
                let nsError = error as NSError
    
                // interactionRequired means we need to ask the user to sign-in. This usually happens
                // when the user's Refresh Token is expired or if the user has changed their password
                // among other possible reasons.
    
                if (nsError.domain == MSALErrorDomain) {
    
                    if (nsError.code == MSALError.interactionRequired.rawValue) {
    
                        DispatchQueue.main.async {
                            self.acquireTokenInteractively()
                        }
                        return
                    }
                }
    
                self.updateLogging(text: "Could not acquire token silently: \(error)")
                return
            }
    
            guard let result = result else {
    
                self.updateLogging(text: "Could not acquire token: No result returned")
                return
            }
    
            self.accessToken = result.accessToken
            self.updateLogging(text: "Refreshed Access token is \(self.accessToken)")
            self.updateSignOutButton(enabled: true)
        }
    }
    
    ```

    この関数では、iOS 用の MSAL を使用して、既存のアカウントのトークンをサイレントに取得します。 `applicationContext`を確認した後、トークン取得プロセスをログに記録します。 `MSALSilentTokenParameters`を使用して、必要なパラメータを定義します。 次に、トークンをサイレントに取得しようとします。 エラーがある場合は、ユーザー操作の要件を確認し、必要に応じて対話型プロセスを開始します。 成功すると、 `accessToken` プロパティが更新され、更新されたトークンがログに記録され、サインアウト ボタンが有効になって終了します。

### ユーザーをサインアウトする

MSAL for iOS を使用して iOS (Swift) アプリからユーザーをサインアウトするには、次のコードを使用します。

```swift
   @IBAction func signOut(_ sender: UIButton) {

        guard let applicationContext = self.applicationContext else { return }

        guard let account = self.currentAccount else { return }

        guard let webViewParameters = self.webViewParameters else { return }

        updateLogging(text: "Signing out...")

        do {

            /**
             Removes all tokens from the cache for this application for the provided account

             - account:    The account to remove from the cache
             */

            let signoutParameters = MSALSignoutParameters(webviewParameters: webViewParameters)

            // If testing with Microsoft's shared device mode, trigger signout from browser. More details here:
            // https://docs.microsoft.com/azure/active-directory/develop/msal-ios-shared-devices

            if (self.currentDeviceMode == .shared) {
                signoutParameters.signoutFromBrowser = true
            } else {
                signoutParameters.signoutFromBrowser = false
            }

            applicationContext.signout(with: account, signoutParameters: signoutParameters, completionBlock: {(success, error) in

                if let error = error {
                    self.updateLogging(text: "Couldn't sign out account with error: \(error)")
                    return
                }

                self.updateLogging(text: "Sign out completed successfully")
                self.accessToken = ""
                self.updateCurrentAccount(account: nil)
            })

        }
    }
```

このコードは、 `applicationContext`、 `currentAccount`、および `webViewParameters`の存在を確認します。 次に、サインアウト プロセスをログに記録します。 このコードは、指定されたアカウントのキャッシュからすべてのトークンを削除します。 現在のデバイスモードに応じて、ブラウザからサインアウトするかどうかを決定します。 完了すると、それに応じてロギングテキストが更新されます。 サインアウト プロセス中にエラーが発生した場合は、エラー メッセージがログに記録されます。 サインアウトが成功すると、アクセス トークンが空の文字列に更新され、現在のアカウントがクリアされます。

### 次のステップ

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-mobile-app-ios-swift-sign-in-call-api"} -->
## iOS アプリでユーザーをサインインさせ、保護された Web API を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-ios-swift-sign-in-call-api
- Service: identity-platform
- Article date: 2024-05-09
- Summary: このチュートリアルでは、ユーザーをサインインさせ、認証のために iOS (Swift) アプリで保護された Web API を呼び出す方法に関する詳細なガイドを提供します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

::: zone pivot="workforce"

これは、Microsoft Entra ID を使用してユーザーをサインインさせ、保護された Web API を呼び出す方法について説明するチュートリアル シリーズの 4 番目のチュートリアルです。

このチュートリアルでは、次の操作を行います。

- 保護された Web API を呼び出します。

### 前提条件

- [チュートリアル: iOS (Swift) モバイル アプリでユーザーをサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-ios-swift-sign-in)

### API の呼び出し

トークンを取得したら、アプリでそれを HTTP ヘッダーで使用して、許可された要求を Microsoft Graph に対して行うことができます。

| ヘッダー キー | 価値 |
| --- | --- |
| 認可 | ベアラー &lt;access-token&gt; |

以下のコードを `ViewController` クラスに追加します。

```swift
    func getContentWithToken() {

        // Specify the Graph API endpoint
        let graphURI = getGraphEndpoint()
        let url = URL(string: graphURI)
        var request = URLRequest(url: url!)

        // Set the Authorization header for the request. We use Bearer tokens, so we specify Bearer + the token we got from the result
        request.setValue("Bearer \(self.accessToken)", forHTTPHeaderField: "Authorization")

        URLSession.shared.dataTask(with: request) { data, response, error in

            if let error = error {
                self.updateLogging(text: "Couldn't get graph result: \(error)")
                return
            }

            guard let result = try? JSONSerialization.jsonObject(with: data!, options: []) else {

                self.updateLogging(text: "Couldn't deserialize result JSON")
                return
            }

            self.updateLogging(text: "Result from Graph: \(result))")

            }.resume()
    }
```

Microsoft Graph API の詳細については、[Microsoft Graph API](https://graph.microsoft.com) に関するページを参照してください。

### アプリをテストする

アプリをビルドし、テスト デバイスまたはシミュレーターに展開します。 サインインして、Microsoft Entra ID または個人用 Microsoft アカウントのトークンを取得できるようになります。

ユーザーは、アプリに初めてサインインするときに、Microsoft Identity から、要求されたアクセス許可に同意するよう求められます。 ほとんどのユーザーは同意できますが、一部の Microsoft Entra テナントではユーザーによる同意が無効になっており、全ユーザーに代わって管理者が同意を行う必要があります。 このシナリオをサポートするには、アプリのスコープを登録します。

サインインした後、Microsoft Graph の `/me` エンドポイントから返されたデータがアプリに表示されます。

::: zone-end

::: zone pivot="external"

これは、Microsoft Entra External ID を使用してユーザーのサインインと保護された Web API の呼び出しについて説明するチュートリアル シリーズの 4 番目のチュートリアルです。

このチュートリアルでは、次の操作を行います。

- 保護された Web API を呼び出します。

### 前提条件

- [チュートリアル: iOS (Swift) モバイル アプリでユーザーをサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-ios-swift-sign-in)
- 少なくとも 1 つのスコープ (委任されたアクセス許可) と 1 つのアプリ ロール (アプリケーション アクセス許可) を発行する API 登録 (*ToDoList.Read* など)。 まだ行っていない場合は、[サンプルの iOS モバイルアプリで API を呼び出し、機能保護された ASP.NET Core Web API を持つための](https://learn.microsoft.com/ja-jp/entra/external-id/customers/sample-native-authentication-ios-sample-app-call-web-api) 手順に従ってください。 次の手順を完了していることを確認します。

    - Web API アプリケーションを登録します。
    - API スコープを構成します。
    - アプリ ロールを構成します。
    - 省略可能な要求を構成します。
    - サンプル Web API を複製またはダウンロードします。
    - サンプル Web API を構成して実行します。

### API の呼び出し

iOS アプリから保護された Web API を呼び出すには、次のコードを使用します。

```swift
    func getContentWithToken() {
        // Specify the API endpoint in _Configuration.swift_ file you created earlier
        guard let url = URL(string: Configuration.kProtectedAPIEndpoint) else {
            let errorMessage = "Invalid API url"
            print(errorMessage)
            updateLogging(text: errorMessage)
            return
        }
        var request = URLRequest(url: url)

        // Set the Authorization header for the request. We use Bearer tokens, so we specify Bearer + the token we got from the result
        request.setValue("Bearer \(self.accessToken)", forHTTPHeaderField: "Authorization")

        self.updateLogging(text: "Performing request...")

        URLSession.shared.dataTask(with: request) { data, response, error in

            if let error = error {
                self.updateLogging(text: "Couldn't get API result: \(error)")
                return
            }

            guard let httpResponse = response as? HTTPURLResponse,
                  (200...299).contains(httpResponse.statusCode)
            else {
                self.updateLogging(text: "Couldn't get API result: \(error)")
                return
            }

            guard let data = data, let result = try? JSONSerialization.jsonObject(with: data, options: []) else {
                self.updateLogging(text: "Couldn't deserialize result JSON")
                return
            }

            self.updateLogging(text: """
                                Accessed API successfully using access token.
                                HTTP response code: \(httpResponse.statusCode)
                                HTTP response body: \(result)
                                """)

            }.resume()
    }
```

このコードでは、API エンドポイントを指定し、その有効性を確認します。 次に、要求オブジェクトを構築し、取得したアクセス トークンを使用して承認ヘッダーを設定します。 要求の開始をログに記録した後、`URLSession`を使用して非同期的に要求を実行します。

完了後、要求中にエラーが発生したかどうかを確認します。 エラーが発生すると、対応するメッセージがログに記録されます。 次に、HTTP 応答の成功を検証し、200 から 299 の状態コードの範囲内であることを確認します。 その後、受信した JSON データを逆シリアル化します。 最後に、ログ テキストが更新され、API への正常なアクセスと関連する HTTP 応答の詳細が示されます。

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-mobile-app-maui-sign-in-prepare-app"} -->
## チュートリアル: .NET MAUI シェル アプリを作成し、MSAL を追加し、イメージ リソースを含める - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-maui-sign-in-prepare-app
- Service: identity-platform
- Article date: 2025-03-12
- Summary: このチュートリアルでは、.NET MAUI シェル アプリを作成し、MSALClient を追加し、イメージ リソースを含める方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルは、.NET マルチプラットフォーム アプリ UI (.NET MAUI) シェル アプリを作成し、Microsoft Entra 管理センターを使用して認証用に準備する方法を示すシリーズのパート 1 です。 このチュートリアルでは、カスタム Microsoft Authentication Library (MSAL) クライアント ヘルパーを追加して、MSAL SDK を初期化し、必要なライブラリをインストールし、イメージ リソースを含めます。

このチュートリアルでは、次の操作を行います。

- .NET MAUI シェル アプリを作成します。
- MSAL ヘルパー クラスを使用して MSAL SDK のサポートを追加します。
- 必要なパッケージをインストールします。
- イメージ リソースを追加します。

### 前提条件

- Microsoft [Entra 管理センター](https://entra.microsoft.com)に新しいクライアント Web アプリを登録します。 *これは、組織のディレクトリと個人用の Microsoft アカウントのアカウント*用に構成されています。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **モバイル アプリケーションとデスクトップ アプリケーション** のプラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `msal{client_id}://auth``{client_id}`がアプリのアプリケーション (クライアント) ID です。
- [.NET SDK](https://dotnet.microsoft.com/download/dotnet/latest)
- MAUI ワークロードがインストールされている [Visual Studio 2022](https://aka.ms/vsdownloads):
    - [Visual Studio セットアップの手順](https://learn.microsoft.com/ja-jp/dotnet/maui/get-started/installation?tabs=visual-studio)

### .NET MAUI シェル アプリを作成する

1. Visual Studio 2022 のスタート ウィンドウで、[新しいプロジェクト作成] を選択します。
2. [**新しいプロジェクト** の作成] ウィンドウで、[すべてのプロジェクトの種類] ドロップダウンリストから [**MAUI**] を選択し、[**.NET MAUI App** テンプレート] を選択して、[**次へ**] を選択します。
3. **[新しいプロジェクトの構成]** ウィンドウで、**[プロジェクト名]** を *SignInMaui* に設定する必要があります。 **[ソリューション名]** を *sign-in-maui* に更新し、**[次へ]** を選択します。
4. [追加情報] ウィンドウで、最新の[.NET SDK]を選択し、[を作成]を[選択]します。

プロジェクトが作成され、その依存関係が復元されるまで待ちます。

### MSAL ヘルパー クラスを使用して MSAL SDK のサポートを追加する

MSAL クライアントを使用すると、開発者は外部テナントからセキュリティ トークンを取得して、セキュリティで保護された Web API を認証してアクセスできます。 このセクションでは、MSALClient を構成するファイルをダウンロードします。

次のファイルをコンピューターのフォルダーにダウンロードします。

- [AzureAdConfig.cs](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/blob/main/1-Authentication/2-sign-in-maui/MSALClient/AzureAdConfig.cs) - このファイルは、アプリ構成ファイルから Microsoft Entra アプリの一意識別子を取得して設定します。
- [DownStreamApiConfig.cs](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/blob/main/1-Authentication/2-sign-in-maui/MSALClient/DownStreamApiConfig.cs) - このファイルは、Microsoft Graph 呼び出しのスコープを取得および設定します。
- [DownstreamApiHelper.cs](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/blob/main/1-Authentication/2-sign-in-maui/MSALClient/DownstreamApiHelper.cs) - このファイルは、ダウンストリーム API を呼び出すときに発生する例外を処理します。
- [Exception.cs](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/blob/main/1-Authentication/2-sign-in-maui/MSALClient/Exception.cs) - このファイルには、例外のスローと処理に関連するいくつかの拡張メソッドが用意されています。
- [IdentityLogger.cs](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/blob/main/1-Authentication/2-sign-in-maui/MSALClient/IdentityLogger.cs) - このファイル ハンドルは、MSAL.NET ログの使用方法を示します。
- [MSALClientHelper.cs](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/blob/main/1-Authentication/2-sign-in-maui/MSALClient/MSALClientHelper.cs) - このファイルには、MSAL SDK を初期化するメソッドが含まれています。
- [PlatformConfig.cs](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/blob/main/1-Authentication/2-sign-in-maui/MSALClient/PlatformConfig.cs) - このファイルには、特定のプラットフォームを処理するメソッドが含まれています。 たとえば、Windows です。
- [PublicClientSingleton.cs](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/blob/main/1-Authentication/2-sign-in-maui/MSALClient/PublicClientSingleton.cs) - このファイルには、MSALClient と関連するクラスをラップしてプラットフォームの静的初期化モデルをサポートするシングルトン実装が含まれています。
- [WindowsHelper.cs](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/blob/main/1-Authentication/2-sign-in-maui/MSALClient/WindowsHelper.cs) - このファイルには、ウィンドウ ハンドルを取得するメソッドが含まれています。

重要

MSALClient ファイルのダウンロードはスキップしないでください。このチュートリアルを完了する必要があります。

#### Visual Studio を使用して MSALClient ファイルを移動する

1. ソリューション エクスプローラー ウィンドウで、SignInMaui プロジェクトを右クリックし、[新しいフォルダー追加] 選択します。 MSALClientフォルダーに名前を付けます。
2. MSALClient フォルダー を右クリックし、[追加 既存の項目...] を選択。
3. 前にダウンロードしたダウンロードした MSALClient ファイルを含むフォルダーに移動します。
4. ダウンロードしたすべての MSALClient ファイルを選択し、**[追加]** を選択します

### 必要なパッケージをインストールする

次のパッケージをインストールする必要があります。

- *Microsoft.Identity.Client* - このパッケージには、Microsoft Authentication Library for .NET (MSAL.NET) のバイナリが含まれています。
- *Microsoft.Extensions.Configuration.Json* - このパッケージには、Microsoft.Extensions.Configuration の JSON 構成プロバイダーの実装が含まれています。
- *Microsoft.Extensions.Configuration.Binder* - このパッケージには、Microsoft.Extensions.Configuration の構成プロバイダー内のデータにオブジェクトをバインドする機能が含まれています。
- *Microsoft.Extensions.Configuration.Abstractions* - このパッケージには、キーと値のペアベースの構成の抽象化が含まれています。
- *Microsoft.Identity.Client.Extensions.Msal* - このパッケージには、Microsoft Authentication Library for .NET (MSAL.NET) の拡張機能が含まれています。

#### NuGet パッケージ マネージャー

**NuGet パッケージ マネージャー** を使用して、Visual Studio に *Microsoft.Identity.Client* パッケージをインストールするには、次の手順に従います。

1. [**ツール]**&gt;**[NuGet パッケージ マネージャー]**&gt;**[ソリューションの NuGet パッケージの管理] を選択します。**
2. **[参照]** タブで、*Microsoft.Identity.Client* を検索します。
3. 一覧から **[Microsoft.Identity.Client]** を選択します。
4. **[プロジェクト]** リスト ペインで **[SignInMaui]** を選択します。
5. **[インストール]** を選択します。
6. インストールの確認を求めるメッセージが表示されたら、[OK]選択します。

プロセスを繰り返して、残りの必要なパッケージをインストールします。

### イメージ リソースを追加する

このセクションでは、アプリで使用するイメージをダウンロードして、ユーザーが操作する方法を強化します。

次の画像をダウンロードします。

- [アイコン: Microsoft Entra ID](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/blob/main/1-Authentication/2-sign-in-maui/Resources/Images/azure_active_directory.png) - この画像はメイン ページのアイコンとして使用されます。

#### Visual Studio を使用してイメージを移動する

1. Visual Studio の **ソリューション エクスプローラー** ウィンドウで、**Resources** フォルダーを展開すると、**Images** フォルダーが表示されます。
2. **[画像]** を右クリックし、**[追加]**&gt;**[既存の項目]** の順に選択します。
3. ダウンロードしたイメージを含むフォルダーに移動します。
4. ファイルの種類フィルターを **[画像ファイル]** に変更します。
5. ダウンロードしたイメージを選択します。
6. [**] を選択し、[**] を追加します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-mobile-app-maui-sign-in-sign-out"} -->
## チュートリアル: 外部テナントを使用して .NET MAUI シェル アプリでユーザーをサインインする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-maui-sign-in-sign-out
- Service: identity-platform
- Article date: 2025-03-12
- Summary: このチュートリアルでは、.NET マルチプラットフォーム アプリ UI (.NET MAUI) でサインインコードとサインアウト コードを追加する方法と、Android プラットフォームでアプリを実行する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルは、.NET マルチプラットフォーム アプリ UI (.NET MAUI) シェル アプリを作成し、Microsoft Entra 管理センターを使用して認証用に準備する方法を示すシリーズの最後の部分です。 [このシリーズのパート 2](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-maui-sign-in-prepare-app) では、カスタム Microsoft Authentication Library (MSAL) クライアント ヘルパーを追加して MSAL SDK を初期化し、必要なライブラリをインストールし、イメージ リソースを含めます。 この最後の手順では、.NET MAUI でサインインとサインアウトのコードを追加し、Android プラットフォームでシェル アプリを実行する方法を示します。

このチュートリアルでは、次の操作を行います。

- サインインとサインアウト コードを追加します。
- アプリ シェルを変更します。
- プラットフォーム固有のコードを追加します。
- アプリ設定を追加します。
- .NET MAUI シェル アプリを実行してテストします。

### [前提条件]

- [チュートリアル: .NET MAUI シェル アプリを作成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-maui-sign-in-prepare-app)

### サインインとサインアウト コードを追加する

.NET MAUI アプリのユーザー インターフェイス (UI) は、各ターゲット プラットフォームのネイティブ コントロールにマップされるオブジェクトで構成されています。 .NET MAUI アプリの UI の作成に使用される主なコントロール グループは、ページ、レイアウト、ビューです。

#### メイン ビュー ページを追加する

次の手順では、`main view` が定義されるようにコードを組みます。

1. プロジェクトから *MainPage.xaml* と *MainPage.xaml.cs* を削除します。これらはもう必要ありません。 **[ソリューション エクスプローラー]** ペインで、**[MainPage.xaml]** のエントリを見つけて、右クリックして **[削除]** を選択します。
2. **[SignInMaui]** プロジェクトで右クリックし、**[追加]**&gt;**[新しいフォルダー]** を選択します。 フォルダーの名前を **[Views]** にします。
3. **[Views]** で右クリックします。
4. **[追加]**&gt;**[新しい項目...]** を選択します。
5. テンプレートの一覧で **[.NET MAUI]** を選択します。
6. **[.NET MAUI ContentPage (XAML)]** テンプレートを選択します。 ファイルの名前を **[MainView.xaml]** にします。
7. [**] を選択し、[**] を追加します。
8. *MainView.xaml* ファイルが新しいドキュメント タブで開き、ページの UI を表すすべての XAML マークアップが表示されます。 XAML マークアップを次のマークアップで置き換えます。

1. ファイルを保存します。

    以下でページに配置された XAML コントロールの主要なパーツを理解しましょう。

    - `<ContentPage>` は MainView クラスのルート オブジェクトです。
    - `<VerticalStackLayout>` は ContentPage の子オブジェクトです。 このレイアウト コントロールは、子要素を垂直方向に順番に配置します。
    - `<Image>` は画像を表示します。この場合は、前にダウンロードした *azure*active\_directory.png\_ を使用しています。
    - `<Label>` コントロールはテキストを表示します。
    - `<Button>` はユーザーが押すことができ、これによって `Clicked` イベントが発生します。 `Clicked` イベントに応じてコードを実行できます。
    - `Clicked="OnSignInClicked"` ボタンの `Clicked` イベントは `OnSignInClicked` イベント ハンドラーに割り当てられ、これは分離コード ファイルで定義されます。 このコードを次の手順で作成します。

##### OnSignInClicked イベントを処理する

次の手順は、ボタンの `Clicked` イベントのコードを追加することです。

1. Visual Studio の **[ソリューション エクスプローラー]** ペインで、**[MainView.xaml]** ファイルを展開して、分離コード ファイル **[MainView.xaml.cs]** を表示します。 **[MainView.xaml.cs]** を開き、ファイルの内容を次のコードで置き換えます。

`MainView` クラスは、アプリのメイン ビューを表示するコンテンツ ページです。 コンストラクターでは、キャッシュされたユーザー アカウントが見つからない場合、`MSALClientHelper` インスタンスからの `PublicClientSingleton` を使用してキャッシュされたユーザー アカウントを取得しサインイン ボタンを有効にします。

サインイン ボタンがクリックされると、`AcquireTokenSilentAsync` メソッドを呼び出してトークンをサイレントで取得し、`claimsview` メソッドを使用して `Shell.Current.GoToAsync` ページに遷移します。 さらに、`OnBackButtonPressed` メソッドは true を返すようにオーバーライドされるので、このビューの戻るボタンは無効になります。

#### 要求ビュー ページを追加する

次の手順では、`ClaimsView` ページが定義されるようにコードを組みます。 ページには、ID トークンで見つかったユーザーの要求が表示されます。

1. Visual Studio の **[ソリューション エクスプローラー]** ペインで、**[Views]** を右クリックします。
2. **[追加]**&gt;**[新しい項目...]** を選択します。
3. テンプレートの一覧で **[.NET MAUI]** を選択します。
4. **[.NET MAUI ContentPage (XAML)]** テンプレートを選択します。 ファイルの名前を **[ClaimsView.xaml]** にします。
5. [**] を選択し、[**] を追加します。
6. *ClaimsView.xaml* ファイルが新しいドキュメント タブで開き、ページの UI を表すすべての XAML マークアップが表示されます。 XAML マークアップを次のマークアップで置き換えます。

この XAML マークアップ コードは、.NET MAUI アプリの要求ビューの UI レイアウトを表します。 まず、`ContentPage` をタイトルで定義し、戻るボタンの動作を無効にします。

`VerticalStackLayout` 内には、静的テキストを表示するいくつかの `Label` 要素があり、その後に `ListView` という名前の `Claims` が続き、ID トークンで見つかった要求を表示するための `IdTokenClaims` というコレクションにバインドされます。 各要求は、`ViewCell` 内で `DataTemplate` を使用してレンダリングされ、グリッド内で中央揃えの `Label` として表示されます。

最後に、レイアウトの下部の中央に `Sign Out` ボタンがあり、クリックされると `SignOutButton_Clicked` イベント ハンドラーがトリガーされます。

##### ClaimsView データを処理する

次の手順は、`ClaimsView` データを処理するためのコードを追加することです。

1. Visual Studio の **[ソリューション エクスプローラー]** ペインで、**[ClaimsView.xaml]** ファイルを展開して、分離コード ファイル **[ClaimsView.xaml.cs]** を表示します。 **[ClaimsView.xaml.cs]** を開き、ファイルの内容を次のコードで置き換えます。

*ClaimsView.xaml.cs* コードは、.NET MAUI アプリのクレームビューのコードビハインドを表します。 まず、必要な名前空間をインポートし、`ClaimsView` を拡張する `ContentPage` クラスを定義します。 `IdTokenClaims` プロパティは文字列の Enumerable であり、最初はクレームが見つからないことを示す 1 つの文字列に設定されています。

`ClaimsView` コンストラクターは、バインディング コンテキストを現在のインスタンスに設定し、ビュー コンポーネントを初期化し、`SetViewDataAsync` メソッドを非同期的に呼び出します。 `SetViewDataAsync` メソッドは、トークンのサイレントな取得を試み、認証結果から要求を取得し、それらを `IdTokenClaims` という名前の `ListView` に表示するために `Claims` プロパティを設定します。 認証にユーザー操作が必要であることを示す `MsalUiRequiredException` が発生した場合、アプリは要求ビューに遷移します。

`OnBackButtonPressed` メソッドは、戻るボタンの動作を常に true を返すようにオーバーライドして、ユーザーがこのビューから戻る動作をできないようにします。 `SignOutButton_Clicked` イベント ハンドラーは、`PublicClientSingleton` インスタンスを使用してユーザーをサインアウトし、完了すると `main view` に遷移します。

### アプリ シェルを変更する

`AppShell` クラスは、アプリのビジュアル階層、つまりアプリの UI の作成に使用される XAML マークアップを定義します。 `AppShell` が `Views` について知ることができるように、これを更新します。

1. `AppShell.xaml` ペインで  ファイルをダブルクリックして、XAML エディターを開きます。 XAML マークアップを次のコードで置き換えます。

    ```xaml
    <?xml version="1.0" encoding="UTF-8" ?>
    <Shell
        x:Class="SignInMaui.AppShell"
        xmlns="http://schemas.microsoft.com/dotnet/2021/maui"
        xmlns:x="http://schemas.microsoft.com/winfx/2009/xaml"
        xmlns:pages="clr-namespace:SignInMaui.Pages"
        Shell.FlyoutBehavior="Disabled">
    
        <ShellContent
            Route="signin"
            ContentTemplate="{DataTemplate pages:SignInPage}" />
    
        <ShellContent
            Route="claims"
            ContentTemplate="{DataTemplate pages:ClaimsPage}" />
    
    </Shell>
    ```

    XAML コードは、ポップアップ動作を無効にするクラスを`AppShell`定義し、メインコンテンツを、 クラスを`ShellContent`指すタイトル`Home`とコンテンツ テンプレートを持つ要素に`MainView`設定します。
2. Visual Studio の **[ソリューション エクスプローラー]** ペインで、**[AppShell.xaml]** ファイルを展開して、分離コード ファイル **[AppShell.xaml.cs]** を表示します。 **[AppShell.xaml.cs]** を開き、ファイルの内容を次のコードで置き換えます。

    ```csharp
    // Copyright (c) Microsoft Corporation. All rights reserved.
    // Licensed under the MIT License.
    
    namespace SignInMaui;
    
    public partial class AppShell : Shell
    {
        public AppShell()
        {
            InitializeComponent();
        }
    }
    ```

    `AppShell.xaml.cs` ファイルを更新して、`MainView` と `ClaimsView` に必要なルート登録を含めます。 `InitializeComponent()` メソッドを呼び出すことで、`AppShell` クラスの初期化が確実に行われるようにします。 `RegisterRoute()` メソッドは、`mainview` と `claimsview` ルートをそれぞれのビューの種類 `MainView` と `ClaimsView` に関連付けます。

### プラットフォーム固有のコードを追加する

.NET MAUI アプリ プロジェクトには Platforms フォルダーが含まれています。各子フォルダーは、.NET MAUI がターゲットにできるプラットフォームを表します。 既定のアプリケーション クラスを補完する Android アプリケーション固有の動作を提供するには、次の手順に従います。

1. `Platforms/Android/AndroidManifest.xml` ペインファイルをダブルクリックして、XML エディターを開きます。 次のプロパティを更新します。

    - **アプリケーション名**を *MAUI CIAM* に設定します。
    - **パッケージ名**を *SignInMaui.Droid* に設定します。
    - **Android の最小バージョン**を *Android 5.0 (API レベル 21) に*設定します。
2. `Platforms/Android/MainActivity.cs` ウィンドウファイルをダブルクリックして、csharp エディターを開きます。 ファイルの内容を次のコードで置き換えます。

    ```csharp
    // Copyright (c) Microsoft Corporation. All rights reserved.
    // Licensed under the MIT License.
    using Android.App;
    using Android.Content;
    using Android.Content.PM;
    using Microsoft.Identity.Client;
    
    namespace SignInMaui;
    
    [Activity(Theme = "@style/Maui.SplashTheme", MainLauncher = true, ConfigurationChanges = ConfigChanges.ScreenSize | ConfigChanges.Orientation | ConfigChanges.UiMode | ConfigChanges.ScreenLayout | ConfigChanges.SmallestScreenSize | ConfigChanges.Density)]
    public class MainActivity : MauiAppCompatActivity
    {
        protected override void OnActivityResult(int requestCode, Result resultCode, Intent? data)
        {
            base.OnActivityResult(requestCode, resultCode, data);
            AuthenticationContinuationHelper.SetAuthenticationContinuationEventArgs(requestCode, resultCode, data);
        }
    }
    ```

    追加したコードの重要な部分を分解しましょう。

    - 先頭には必要な `using` ステートメントが含まれています。
    - `MainActivity` クラスは、.NET MAUI の Android プラットフォームの基底クラスである `MauiAppCompatActivity` から継承して定義されます。
    - [Activity] 属性は、Android アクティビティのさまざまな設定を指定して、 `MainActivity`クラスに適用されます。
        - `Theme = "@style/Maui.SplashTheme"` は、アクティビティのスプラッシュ テーマを設定します。
        - `MainLauncher = true` は、このアクティビティをアプリケーションのメイン エントリ ポイントとして指定します。
        - `ConfigurationChanges` は、 *画面サイズ*、 *向き*、 *UI モード*、 *画面レイアウト*、 *最小画面サイズ*、 *密度*など、アクティビティが処理できる構成変更を指定します。
    - `OnCreate`メソッドは、アクティビティの作成時にカスタム ロジックを提供するためにオーバーライドされます。
        - `base.OnCreate(savedInstanceState)` は、メソッドの基本実装を呼び出します。
        - `PlatformConfig.Instance.RedirectUri` は、 `PublicClientSingleton.Instance.MSALClientHelper.AzureAdConfig.ClientId`に基づいて動的に生成される値に設定されます。 MSAL クライアントのリダイレクト URI を構成します。
        - `PlatformConfig.Instance.ParentWindow` は、認証関連の操作の親ウィンドウを指定する現在のアクティビティ インスタンスに設定されます。
        - `PublicClientSingleton.Instance.MSALClientHelper.InitializePublicClientAppAsync()` は、 `MSALClientHelper` と呼ばれるシングルトン インスタンスのヘルパー メソッドを使用して、MSAL クライアント アプリを非同期的に初期化します。 `Task.Run`はバックグラウンド スレッドで初期化を実行するために使用され、`.Result`はタスクの完了を同期的に待機するために使用されます。
    - `OnActivityResult`メソッドは、現在のアクティビティによって起動されたアクティビティの結果を処理するためにオーバーライドされます。
        - `base.OnActivityResult(requestCode, resultCode, data)` は、メソッドの基本実装を呼び出します。
        - `AuthenticationContinuationHelper.SetAuthenticationContinuationEventArgs(requestCode, resultCode, data)` は、受信した要求コード、結果コード、および意図データに基づいて認証継続イベント引数を設定します。 これは、外部アクティビティが結果を返した後に認証フローを続行するために使用されます。
3. Visual Studio の **ソリューション エクスプローラー** ウィンドウで、[プラットフォーム] を選択 **します**。
4. **Android** フォルダー &gt;**Add**&gt;**New Item..**. を右クリックします。
5. &gt;] を選択します。 そのファイルに `MsalActivity.cs` という名前を付けます。
6. `MsalActivity.cs` ファイルの内容を次のコードで置き換えます。

    ```csharp
    // Copyright (c) Microsoft Corporation. All rights reserved.
    // Licensed under the MIT License.
    
    using Android.App;
    using Microsoft.Identity.Client;
    
    namespace SignInMaui.Platforms.Android;
    
    /// <summary>
    /// Activity that handles the MSAL redirect URI callback from the system browser
    /// after Entra External ID authentication completes.
    /// The intent filter is declared in AndroidManifest.xml so that scripts can
    /// replace the placeholder client ID.
    /// </summary>
    [Activity(Exported = true, Name = "com.companyname.signinmaui.MsalActivity")]
    public class MsalActivity : BrowserTabActivity
    {
    }
    ```

    追加したコードの重要な部分を分解しましょう。

    - `MsalActivity` クラスは、 `MauiAppBasic.Platforms.Android.Resources` 名前空間内で宣言されます。 このクラスは、 `BrowserTabActivity` クラスを継承し、その機能を拡張することを示します。
    - クラスは `[Activity(Exported = true)]` 属性で修飾されます。これは、アクティビティがエクスポートされ、他のメソッドからアクセスできることを示します。
    - 意図フィルターは、"[IntentFilter(...)]" 属性を使用して指定します。 `ActionView`意図をインターセプトするようにアクティビティを構成します。
    - 意図フィルターは、指定した`ActionView` (`DataScheme`) と`msalEnter_the_Application_Id_Here` ("認証") を使用して`DataHost`意図を処理するように設定されます。 この構成により、アクティビティは、 `ActionView` 意図をインターセプトして処理することで、認証プロセスを処理できます。 `Enter_the_Application_Id_Here`を、先ほど登録したアプリの**アプリケーション (クライアント) ID** に置き換えます。

### アプリ設定を追加する

設定を使用すると、アプリの動作を構成するデータをコードから分離できるため、アプリを再構築せずに動作を変更できます。 `MauiAppBuilder` は、.NET MAUI アプリで設定を構成するための `ConfigurationManager` を提供します。 `appsettings.json` ファイルを `EmbeddedResource` として追加しましょう。

`appsettings.json` を作成するには、次の手順に従います。

1. Visual Studio の **[ソリューション エクスプローラー]** ペインで、**[SignInMaui]** プロジェクトで右クリック&gt;**[追加]**&gt;**[新しい項目...]**。
2. **[Web]**&gt;**[JavaScript JSON 構成ファイル]** を選択します。 そのファイルに `appsettings.json` という名前を付けます。
3. [**] を選択し、[**] を追加します。
4. **[appsettings.json]** を選択します
5. **[プロパティ]** ペインで、**[ビルド アクション]** を **[埋め込みリソース]** に設定します。
6. **[プロパティ]** ペインで、**[出力ディレクトリにコピー]** を **[常にコピー]** に設定します。
7. `appsettings.json` ファイルの内容を次のコードで置き換えます。

    ```json
    {
      "AzureAd": {
        "Authority": "https://Enter_the_Tenant_Subdomain_Here.ciamlogin.com/",
        "ClientId": "Enter_the_Application_Id_Here"
      },
      "DownstreamApi": {
        "Scopes": [
          "openid",
          "offline_access"
        ]
      }
    }
    ```
8. `appsettings.json` 内でプレースホルダーを見つけます。

    1. `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。
    2. `Enter_the_Application_Id_Here` を、前に登録したアプリのアプリケーション (クライアント) ID に置き換えます。

#### カスタム URL ドメインを使用する (省略可能)

カスタム ドメインを使用して、認証 URL を完全にブランド化します。 ユーザーの視点から見ると、認証プロセスの間、ユーザーは *ciamlogin.com* ドメイン名にリダイレクトされず、あなたのドメインにとどまります。

カスタム ドメインを使用するには、次の手順に従います。

1. 「[外部テナント内のアプリに対するカスタム URL ドメインの有効化](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-url-domain)」の手順を使用し、外部テナントに対してカスタム URL ドメインを有効にします。
2. *appsettings.json* ファイルを開きます。

    1. `Authority` プロパティの値を *https://Enter\_the\_Custom\_Domain\_Here/Enter\_the\_Tenant\_ID\_Here* に更新します。 `Enter_the_Custom_Domain_Here` を実際のカスタム URL ドメインに、`Enter_the_Tenant_ID_Here` を実際のテナント ID に置き換えます。 テナント ID がわからない場合は、[テナントの詳細を読み取る](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)方法を確認してください。
    2. `knownAuthorities` という値を持つ  プロパティを追加します。

カスタム URL ドメインが *login.contoso.com*、テナント ID が *aaaabbbb-0000-cccc-1111-dddd2222eeee* の場合、*appsettings.json* ファイルに変更を加えた後には、ファイルは次のスニペットのようになるはずです。

```json
{
  "AzureAd": {
    "Authority": "https://login.contoso.com/aaaabbbb-0000-cccc-1111-dddd2222eeee",
    "ClientId": "Enter_the_Application_Id_Here",
    "CacheFileName": "msal_cache.txt",
    "CacheDir": "C:/temp",
    "KnownAuthorities": ["login.contoso.com"]
  },
  "DownstreamApi": {
    "Scopes": "openid offline_access"
  }
}
```

### .NET MAUI モバイル アプリの実行とテスト

.NET MAUI アプリは、複数のオペレーティング システムとデバイス上で実行できるように設計されています。 どのターゲットでアプリをテストしてデバッグしたいかを選択する必要があります。

Visual Studio ツール バーの **[デバッグ ターゲット]** を、デバッグしてテストしたいデバイスに設定します。 次の手順は、**[デバッグ ターゲット]** を *Android* に設定する方法を示しています。

1. **[デバッグ ターゲット]** ドロップダウン リストを選択します。
2. **[Android エミュレーター]** を選択します。
3. エミュレーター デバイスを選択します。

*F5* キーを押すか、Visual Studio の上部にある "再生ボタン" を選択してアプリを実行します。

1. これでサンプルの .NET MAUI Android アプリをテストできるようになりました。 アプリを実行すると、エミュレーターに Android アプリ ウィンドウが表示されます。

    [Image: Android アプリケーションの [サインイン] ボタンのスクリーンショット]
2. 表示された Android ウィンドウで、**[サインイン]** ボタンを選択します。 ブラウザー ウィンドウが開き、サインインが求められます。

    [Image: Android アプリケーションで資格情報を入力するためのユーザー プロンプトのスクリーンショット。]

    サインイン プロセス中に、さまざまなアクセス許可を付与するように求められます (アプリケーションがデータにアクセスできるようにします)。 サインインと同意が成功すると、アプリケーション画面にメイン ページが表示されます。

    [Image: サインインした後に表示される Android アプリケーションのメイン ページのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-mobile-maui-role-based-access-control"} -->
## チュートリアル: Microsoft ID プラットフォームを使用して .NET MAUI モバイル アプリでロールベースのアクセス制御を使用する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-maui-role-based-access-control
- Service: identity-platform
- Article date: 2025-03-12
- Summary: このチュートリアルでは、アプリ ロールを .NET マルチプラットフォーム アプリ UI (.NET MAUI) に追加し、ID トークンで受け取る方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、アプリ ロールを .NET マルチプラットフォーム アプリ UI (.NET MAUI) に追加し、ID トークンで受け取る方法について説明します。

このチュートリアルでは、次の操作を行います。

- ID トークン内のロールにアクセスします。

### [前提条件]

- [チュートリアル: .NET MAUI シェル アプリでユーザーをサインインする](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-maui-sign-in-sign-out)
- [アプリケーションにロールベースのアクセス制御 (RBAC) を使用する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-use-app-roles-customers)

### .NET MAUI でグループとロールの要求を受信する

顧客のテナントを構成したら、クライアント アプリでロールとグループの要求を取得できます。 ロールとグループの要求は、どちらも ID トークンとアクセス トークンに存在します。 アクセス トークンは、クライアントによって取得された Web API でのみ検証されます。 クライアントはアクセス トークンを検証しないでください。

.NET MAUI は、クライアント側で承認を実装するために、ID トークン内のアプリ ロール要求を確認する必要があります。

このチュートリアル シリーズでは、 データを処理するを開発した .NET MAUI アプリを作成しました。 このファイルでは、ID トークンの内容を調べます。

ロールクレームにアクセスするには、コードスニペットを次のように修正します。

```csharp
var idToken = PublicClientSingleton.Instance.MSALClientHelper.AuthResult.IdToken;
var handler = new JwtSecurityTokenHandler();
var token = handler.ReadJwtToken(idToken);
// Get the role claim value
var roleClaim = token.Claims.FirstOrDefault(c => c.Type == "roles")?.Value;

if (!string.IsNullOrEmpty(roleClaim))
{
    // If the role claim exists, add it to the IdTokenClaims
    IdTokenClaims = new List<string> { roleClaim };
}
else
{
    // If the role claim doesn't exist, add a message indicating that no role claim was found
    IdTokenClaims = new List<string> { "No role claim found in ID token" };
}

Claims.ItemsSource = IdTokenClaims;
```

注

ID トークンを読み取るために、 `System.IdentityModel.Tokens.Jwt` パッケージをインストールする必要があります。

ユーザーを複数のロールに割り当てる場合、ロール文字列には、 `Orders.Manager, Store.Manager,...`など、コンマで区切られたすべてのロールが含まれます。 次の条件を処理するようにアプリケーションをビルドしてください。

- トークンに役割情報要求が含まれていない
- ユーザーはどの役割にも割り当てられていない
- ユーザーを複数のロールに割り当てると、ロール要求に複数の値が含まれる

アプリのアプリ ロールを定義するときは、それらのロールの承認ロジックを実装する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-android-custom-headers"} -->
## Android でネイティブ認証要求にカスタム ヘッダーを追加する (Kotlin) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-custom-headers
- Service: identity-platform / external
- Article date: 2026-05-15
- Summary: Android (Kotlin) アプリでネイティブ認証ネットワーク要求にカスタム x-* ヘッダーをアタッチして、不正検出 SDK をMicrosoft Entra 外部 IDと統合する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、Microsoft Authentication Library (MSAL) `x-*` インターフェイスを使用して、Android (Kotlin) アプリのネイティブ認証ネットワーク要求にカスタム `NativeAuthRequestInterceptor` ヘッダーをアタッチし、サードパーティの不正行為やボット検出 SDK との統合を可能にする方法について説明します。

このチュートリアルでは、次の操作を行います。

- MSAL によって適用されるヘッダーの名前付け規則について理解します。
- `NativeAuthRequestInterceptor` インターフェイスを実装します。
- インターセプターをアプリ構成に登録します。

### 前提条件

- MSAL ネイティブ認証を使用する Android (Kotlin) アプリ。 お持ちでない場合は、「 [チュートリアル: ネイティブ認証を使用して Android (Kotlin) アプリにサインインを追加](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-sign-in-sign-out)する」を完了してください。
- アプリは、 `INativeAuthPublicClientApplication` インスタンスで初期化されます。 このチュートリアルの手順では、インターセプターを登録するように既存のアプリ初期化コードを更新する方法について説明します。

### ヘッダーの名前付けルールについて

MSAL は、指定したヘッダーを評価するときに次の規則を適用します。

- ヘッダーはで始まる`x-` (大文字と小文字は区別されません)。 `x-`で始まらないヘッダーは無視されます。
- 次のいずれかの予約済みプレフィックスで始まるヘッダーは無視されます。
    - `x-client-`
    - `x-ms-`
    - `x-broker-`
    - `x-app-`
- 有効なヘッダーは、要求に追加される前に小文字に正規化されます。
- MSAL は、両方の規則をネットワーク要求に渡すヘッダーを追加します。 指定したヘッダーの名前が MSAL 独自の内部ヘッダーの 1 つと同じである場合は、値が優先されます。

これらの規則を使用して、インターセプターを実装する前にベンダーが必要とするヘッダー名を確認します。

### 要求インターセプターを実装する

`NativeAuthRequestInterceptor` インターフェイスは、MSAL が各ネットワーク要求を送信する前に同期的に呼び出す 1 つのメソッドを宣言します。 実装は要求 URL を受け取り、追加するヘッダーのマップを返します。その要求にヘッダーが必要ない場合は `null` します。

Important

`additionalHeaders` メソッドは、要求を実行するスレッド (通常はバックグラウンド/ネットワーク スレッド) で同期的に実行されます。 実装はスレッド セーフで、すぐに戻る必要があります。 このメソッドからスローされた例外は呼び出し元に伝達され、要求は失敗します。

`NativeAuthRequestInterceptor`を実装するクラスを作成します。

```kotlin
import com.microsoft.identity.common.java.nativeauth.providers.NativeAuthRequestInterceptor
import java.net.URL

class CustomHeaderInterceptor : NativeAuthRequestInterceptor {

    override fun additionalHeaders(requestUrl: URL): Map<String, String>? {
        // Scope headers to specific endpoints only.
        if (requestUrl.path.contains("oauth2/v2.0/initiate")) {
            return mapOf(
                "value_1" to "customer_header_1",          // Ignored: doesn't start with "x-"
                "x-client-header" to "customer_header_2",  // Ignored: starts with reserved prefix "x-client-"
                "X-my-custom-header" to "my data"          // Added to the network request (lowercased to "x-my-custom-header").
            )
        }

        // Return null for all other requests to avoid over-sending signals.
        return null
    }
}
```

メソッドは、 `requestUrl`で送信要求の完全な URL を受け取ります。 この URL を使用して、サインインやサインアップの開始エンドポイントなど、不正行為やボット検出ベンダーが必要とする特定のエンドポイントにヘッダーのスコープを設定します。 関連のないエンドポイントにヘッダーを送信すると、信号品質が低下し、誤検知が増加する可能性があります。

Note

常に `additionalHeaders`から値を返します。 その要求に追加のヘッダーが必要ない場合は、 `null` または空のマップを返します。

### インターセプターを登録する

インターフェイスを実装した後、アプリの`requestInterceptor`に`NativeAuthPublicClientApplicationConfiguration` プロパティを設定します。 アプリが JSON 構成ファイル ( *app/src/main/res/raw/native\_auth\_sample\_app\_config.json*など) を使用して認証クライアントを初期化する場合は、クライアントの作成後に構成にインターセプターを設定します。

```kotlin
import com.microsoft.identity.client.PublicClientApplication
import com.microsoft.identity.nativeauth.INativeAuthPublicClientApplication
import com.microsoft.identity.nativeauth.NativeAuthPublicClientApplicationConfiguration

val authClient: INativeAuthPublicClientApplication =
    PublicClientApplication.createNativeAuthPublicClientApplication(
        context,
        R.raw.native_auth_sample_app_config
    )

(authClient as? PublicClientApplication)?.let { app ->
    (app.configuration as? NativeAuthPublicClientApplicationConfiguration)?.requestInterceptor =
        CustomHeaderInterceptor()
}
```

アプリでプログラムによる構成を使用する場合は、アプリケーションを作成する前に、`requestInterceptor` インスタンスに `NativeAuthPublicClientApplicationParameters` プロパティを設定します。

```kotlin
import com.microsoft.identity.client.PublicClientApplication
import com.microsoft.identity.nativeauth.INativeAuthPublicClientApplication
import com.microsoft.identity.nativeauth.NativeAuthPublicClientApplicationParameters

val parameters = NativeAuthPublicClientApplicationParameters(
    clientId = "Enter_the_Application_Id_Here",
    authorityUrl = "https://Enter_the_Tenant_Subdomain_Here.ciamlogin.com/Enter_the_Tenant_Subdomain_Here.onmicrosoft.com/",
    challengeTypes = listOf("oob", "password")
)
parameters.requestInterceptor = CustomHeaderInterceptor()

val authClient: INativeAuthPublicClientApplication =
    PublicClientApplication.createNativeAuthPublicClientApplication(
        context,
        parameters
    )
```

### ヘッダーが適用されていることを確認する

ヘッダーが目的のエンドポイントに到達することを確認するには、Charles Proxy や Android Studio Network Inspector などのネットワーク プロキシ ツールを使用して、送信ネットワーク トラフィックを検査します。 次の内容を確認する:

- `x-`で始まり、予約済みのプレフィックスのないヘッダーが要求に表示されます。
- 予約済みプレフィックス (`x-client-`、 `x-ms-`、 `x-broker-`、 `x-app-`) を持つヘッダーは要求に表示されません。
- ヘッダーは、スコープを設定したエンドポイントにのみ送信されます。
- 有効なヘッダー名は、送信要求では小文字で表示されます。

Note

インターセプター内のログには、最終的な要求ヘッダーは表示されません。 インターセプターは、MSAL が名前付け規則を評価して適用する前に呼び出されるため、最終的に送信されるヘッダーではなく、指定したヘッダーのみが反映されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-android-email-one-time-passcode-sign-in"} -->
## ネイティブ認証を使用して Android アプリに電子メールと SMS ワンタイム パスコード MFA を追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-email-one-time-passcode-sign-in
- Service: identity-platform / external
- Article date: 2026-01-27
- Summary: ネイティブ認証を使用して Android アプリに電子メールと SMS ワンタイム パスコードを使用する多要素認証 (MFA) Microsoft Entra追加する方法について説明します。

このチュートリアルでは、ネイティブ認証を使用して Android アプリに多要素認証 (MFA) を実装する方法について説明します。 MFA では、サインイン時に追加の検証手順をユーザーに提供するように要求することで、アプリにセキュリティレイヤーが追加されます。 現在、電子メールと SMS ワンタイム パスコード MFA がサポートされています。 また、認証時にセキュリティを強化し、認証コンテキストを使用して MFA を適用する方法についても説明 [します](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#authentication-context)。

このチュートリアルでは、以下の内容を学習します。

- 電子メール ワンタイム パスコード MFA を使用してユーザーをサインインさせる
- MFA を適用するために、ユーザーのサインイン時に認証コンテキストを指定します。

### [前提条件]

1. [「チュートリアル: ネイティブ認証を使用して Android アプリにサインインを追加](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-native-authentication-android-sign-in-sign-out)する」の手順を完了します。
2. 顧客に対して MFA を有効にするには、「アプリに [MFA を追加する」](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers)の手順に従います。 現在、ネイティブ認証では、MFA の 2 番目の要素として電子メール ワンタイム パスコードがサポートされています。これは、プライマリ認証方法がパスワード付きの電子メールである場合にのみ使用できます。
3. MFA を使用したサインインの実装を調べる場合は、開始する前に、[Code サンプル](https://github.com/Azure-Samples/ms-identity-ciam-native-auth-android-sample)をご確認ください。

### クライアント構成ファイルに MFA 機能を追加する

MFA をサポートするには、Android クライアント構成を更新して、必要な MFA 機能を含めます。

```json
{
    "client_id": "Enter_the_Application_Id_Here",
    "authorities": [
    {
        "type": "CIAM",
        "authority_url": "https://Enter_the_Tenant_Subdomain_Here.ciamlogin.com/Enter_the_Tenant_Subdomain_Here.onmicrosoft.com/"
    }
    ],
    "challenge_types": ["oob", "password"],
    "capabilities": ["mfa_required"],
    "logging": {
    "pii_enabled": false,
    "log_level": "INFO",
    "logcat_enabled": true
    }
}
```

### 電子メール ワンタイム パスコード MFA を使用してユーザーをサインインさせる

電子メールワンタイム パスコード MFA を使用してユーザーをサインインさせるには、電子メールとパスワードを収集した後、ユーザーが自分のメールを確認するためのワンタイム パスコードを含む電子メールを送信する必要があります。 有効なワンタイム パスコードを入力すると、アプリはユーザーをサインインさせます。

電子メール ワンタイム パスコード MFA を使用してユーザーをサインインさせるには、次の操作を行う必要があります。

1. 「ユーザーインターフェース (UI) を作成して、以下を行う：」

    - サインインに MFA が必要であることをユーザーに知らします (省略可能)。
    - 可能な `authMethods` をユーザーに表示し、電子メールを含むものを選択させます (省略可能)。
    - ユーザーから電子メールワンタイム パスコードを収集して、2 番目の認証要素を満たします。
    - ワンタイム パスコードを再送信します (推奨)。
2. サインイン結果 `MFARequired` 処理します。

    ```kotlin
    is SignInResult.MFARequired -> {
        // Handle "mfa required" result 
        val awaitingMFAState = actionResult.nextState
    
        // You can select the first authMethod which has a challengeChannel of type email
        // Or use "actionResult.authMethods" to let the user pick the authentication method to use.
        val authMethod = actionResult.authMethods.first {it.challengeChannel.uppercase() == "EMAIL"}        
        val requestChallengeResult = awaitingMFAState.requestChallenge(authMethod: authMethod)
    
        // Handle "mfa verification required" result 
        if (requestChallengeResult is MFARequiredResult.VerificationRequired) {
            // Next Step: submitChallenge using nextState
        } else {
            // Handle unexpected result as well as errors
        }
    }
    ```

    サインイン時に MFA が必要な場合、システムは `SignInResult.MFARequired`を返します。 この時点で、認証を続行するために MFA が必要であることをユーザーに通知するか、 `requestChallenge(authMethod)` メソッドを呼び出してワンタイム パスコードをユーザーの電子メール アドレスに送信できます。 最も一般的なシナリオ `requestChallenge(authMethod)`では、結果 `MFARequiredResult.VerificationRequired`が返されます。これは、SDK がアプリがユーザーの電子メール アドレスに送信された電子メール ワンタイム パスコードを送信することを想定していることを示します。
3. 結果 `submitChallenge()` 処理します。

    ```kotlin
    val mfaRequiredState = requestChallengeResult.nextState
    val submitChallengeResult = mfaRequiredState.submitChallenge(emailCode)
    
    when (submitChallengeResult) {
        is SignInResult.Complete -> {
            // Handle sign in success               
        }
        is SubmitChallengeError -> {
            // Handle "mfa submit challenge" error
        }
        else -> {
            // Handle unexpected result
        }
    }
    ```

    `MFARequiredResult.VerificationRequired` オブジェクトには、`requestChallengeResult.nextState`を使用して取得できる新しい状態参照が含まれています。 新しい状態では、MFA チャレンジの送信に使用できる `submitChallenge()` メソッドにアクセスできます。 最も一般的なシナリオでは、 `submitChallenge()` は、ユーザーが正常に認証されたことを示す結果 ( `SignInResult.Complete`) を返します。

### サインイン時に認証コンテキストを使用する

サインイン時に認証コンテキストを指定する前に、Microsoft Entra管理センターで条件付きアクセス ポリシーを構成していることを確認します。[認証コンテキストの構成](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#configure-authentication-contexts)に記載されている手順を実行します。 認証コンテキストを構成した後、次のコード スニペットを使用して、サインイン時にこの要求要求を指定できます。

```kotlin
CoroutineScope(Dispatchers.Main).launch {
    val parameters = NativeAuthSignInParameters(username = email)
    parameters.password = password

    // before calling the signIn method, we need to specify the authentication context as request claim
    val authenticationContextClaim = "{\"access_token\":{\"acrs\":{\"essential\":true,\"value\":\"<authentication context id>\"}}}"
    parameters.claimsRequest = ClaimsRequest.getClaimsRequestFromJsonString(authenticationContextClaim)

    // now we can call the signIn method
    val actionResult = authClient.signIn(parameters)

    // Handle actionResult here
 }
```

この時点で MFA フローを開始し、メソッド `onSignInAwaitingMFA(newState)` が呼び出されます。

### 電子メール ワンタイム パスコード MFA エラーによるサインインの処理

MFA 中に発生するエラーを処理するには、次のコード スニペットを使用します。

1. `requestChallenge(authMethod:)` メソッドのエラーを処理します。

    ```kotlin
    val requestChallengeResult = mfaRequiredState.requestChallenge(authMethod: authMethod)
    
    if (requestChallengeResult is MFARequiredResult.VerificationRequired) {
        // Next Step: submit challenge
    } else if (requestChallengeResult is MFARequestChallengeError) {
        // Handle errors under MFARequestChallengeError
        when {
            error.isBrowserRequired() -> {
                // Display error
            }
            else -> {
                // Unexpected error
            }
        }
    }
    ```

    `MFARequestChallengeError`は、`mfaRequiredState.requestChallenge(authMethod: authMethod)`を使用して MFA チャレンジを要求した後に処理されます。 結果が `MFARequiredResult.VerificationRequired`場合、次の手順はチャレンジを送信することです。
2. `submitChallenge(challenge)` メソッドのエラーを処理します。

    ```kotlin
    val verifyChallengeResult = mfaRequiredState.submitChallenge(challenge = emailCode)
    
    if (verifyChallengeResult is SignInResult.Complete) {
        // Handle sign in success
    } else if (verifyChallengeResult is MFASubmitChallengeError) {
        // Handle errors under MFASubmitChallengeError
        when {
            error.isInvalidChallenge() -> {
                // Display error
            }
            else -> {
                // Unexpected error
            }
        }
    }
    ```

    `MFASubmitChallengeError`は、`mfaRequiredState.submitChallenge(challenge = emailCode)`を使用してチャレンジを送信した後に処理されます。 `isInvalidChallenge()`メソッドを使用して、送信されたチャレンジが無効ななどの特定のエラーを確認します。 この場合、チャレンジの送信を再パフォーマンスするには、前の状態参照を使用する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-android-email-strong-authentication-method"} -->
## ネイティブ認証を使用して Android アプリに電子メールの強力な認証方法の登録を追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-email-strong-authentication-method
- Service: identity-platform / external
- Article date: 2026-01-27
- Summary: Microsoft Entra ネイティブ認証を使用して、Android アプリで MFA が有効なユーザーの強力な認証方法として、電子メールのワンタイムパスコードを登録する方法を学びましょう。

このチュートリアルでは、ネイティブ認証を使用して Android アプリに電子メールの強力な認証方法の登録を実装する方法について説明します。 多要素認証 (MFA) が有効なユーザーには、少なくとも 1 つの強力な認証が必須です。 現時点では、強力な認証方法として、電子メールと SMS のワンタイム パスコードのみがサポートされています。

このチュートリアルでは、以下の内容を学習します。

- MFA が有効なユーザーの強力な認証方法を登録します。
- 強力な認証方法の登録エラーを処理します。

### [前提条件]

1. [「チュートリアル: ネイティブ認証を使用して Android アプリにサインインを追加](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-sign-in-sign-out)する」の手順を完了します。
2. 顧客に MFA を適用するには、「 [MFA をアプリに追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers) 」の手順を使用して、サインイン フローに電子メール OTP MFA を追加します。 現在、ネイティブ認証では、MFA の 2 番目の要素として電子メールと SMS ワンタイム パスコードがサポートされています。 したがって、第 2 要素としての電子メール OTP は、認証方法がパスワード付きの電子メールである場合にのみ使用できます。
3. 強力な認証方法の登録の実装を調べる場合は、開始する前に、[Code サンプル](https://github.com/Azure-Samples/ms-identity-ciam-native-auth-android-sample)をご確認ください。

### クライアント構成ファイルに強力な認証方法の登録を追加する

強力な認証方法をサポートするには、必要な登録機能を含むように Android クライアント構成を更新します。

```json
{
    "client_id": "Enter_the_Application_Id_Here",
    "authorities": [
    {
        "type": "CIAM",
        "authority_url": "https://Enter_the_Tenant_Subdomain_Here.ciamlogin.com/Enter_the_Tenant_Subdomain_Here.onmicrosoft.com/"
    }
    ],
    "challenge_types": ["oob", "password"],
    "capabilities": ["registration_required"],
    "logging": {
    "pii_enabled": false,
    "log_level": "INFO",
    "logcat_enabled": true
    }
}
```

### 強力な認証方法として電子メール ワンタイム パスコードを登録する

MFA が有効なユーザーの電子メール ワンタイム パスコードを登録するには、アカウントの作成時に指定された既定の電子メール アドレスを使用するか、強力な認証方法として代替の電子メール アドレスを指定します。 このプロセスは、MFA が初めて必要なときにのみトリガーされ、ユーザーには強力な認証方法が登録されていません。 その後、ユーザーが自分のメール アドレスを確認するためのワンタイム パスコードを含む電子メールを送信する必要があります。 ユーザーが有効なワンタイム パスコードを入力すると、アプリはサインイン プロセスを完了します。

MFA 対応ユーザーの強力な認証方法 (電子メール ワンタイム パスコード) を登録するには、次の操作を行う必要があります。

1. 「ユーザーインターフェース (UI) を作成して、以下を行う：」

    - サインインには強力な認証方法の登録が必要であることをユーザーに知らしてください (省略可能)。
    - 強力な認証方法として登録する電子メール アドレスを収集します。 ユーザーが電子メール アドレスを指定しない場合は、アカウントの作成時に指定された既定の電子メール アドレスが使用されます。
    - ユーザーから電子メールワンタイム パスコードを収集して、2 要素認証を実行します。
    - ワンタイム パスコードを再送信します (推奨)。
2. サインイン結果 `StrongAuthMethodRegistrationRequired` 処理します。

    ```kotlin
     val parameters = NativeAuthSignInParameters(username = email)
     parameters.password = password 
     val signInResult: SignInResult = authClient.signIn(parameters) 
    
     when (actionResult) {
         // Handle "strong authentication method registration required" result 
         is SignInResult.StrongAuthMethodRegistrationRequired -> {
             val authMethod = actionResult.authMethods.first {it.challengeChannel.uppercase() == "EMAIL"}
             // Next Step: challengeAuthMethod using nextState 
         }
     }
    ```

    サインイン時に、MFA が必要であり、強力な認証が登録されていない場合、システムは `SignInResult.StrongAuthMethodRegistrationRequired`返し、ユーザーに対して、強力な認証方法として代替メール アドレスを指定するか、アカウントの作成時に指定された既定の電子メール アドレスを使用するように要求します。
3. 結果 `challengeAuthMethod()` 処理します。

    ```kotlin
    val registerStrongAuthState = signInResult.nextState
    verificationContact = "user@contoso.com" // Do NOT hard-code email in production. Use the email provided during account creation or prompt the user for input.
    val params = NativeAuthChallengeAuthMethodParameters(authMethod, verificationContact = verificationContact)
    val challengeAuthResult = registerStrongAuthState.challengeAuthMethod(params)
    
    when (challengeAuthResult) {
        is RegisterStrongAuthChallengeResult.VerificationRequired -> {
            // Next Step: submitChallenge using nextState
        }
        is RegisterStrongAuthChallengeError -> {
            // Handle "register strong auth challenge" error
        }
        else -> {
            // Handle unexpected result
        }
    }
    ```

    `challengeAuthMethod()`メソッドは、`verificationContact`で指定された電子メール アドレスにワンタイム パスコードを送信します。 最も一般的なシナリオ `challengeAuthMethod()`では、結果 `RegisterStrongAuthChallengeResult.VerificationRequired`が返されます。これは、SDK が電子メール アドレスに送信された電子メール ワンタイム パスコードをアプリが送信することを想定していることを示します。 `RegisterStrongAuthChallengeResult.VerificationRequired` オブジェクトには、`challengeAuthResult.nextState`を使用して取得できる新しい状態参照が含まれています。 新しい状態では、ユーザーの電子メール アドレスに送信された電子メール ワンタイム パスコードを送信するために使用できる `submitChallenge()` メソッドにアクセスできます。
4. 結果 `submitChallenge()` 処理します。

    ```kotlin
    val registerStrongAuthVerificationRequiredState = challengeAuthResult.nextState
    val submitChallengeResult = registerStrongAuthVerificationRequiredState.submitChallenge(emailCode)
    
    when (actionResult) {
        is SignInResult.Complete -> {
            // Handle sign in success      
        }
        is RegisterStrongAuthSubmitChallengeError -> {
            // Handle "register strong auth submit challenge" error
        }
        else -> {
            // Handle unexpected result      
        }
    }
    ```

    最も一般的なシナリオでは、 `submitChallenge()` は、ユーザーが正常に認証されたことを示す結果 `SignInResult.Complete`を返します。

### 強力な認証方法の登録エラーを処理する

強力な認証方法の登録中に発生するエラーを処理するには、次のコード スニペットを使用します。

1. `challengeAuthMethod()` メソッドのエラーを処理します。

    ```kotlin
    val challengeAuthResult = registerStrongAuthState.challengeAuthMethod(params)
    
    if (challengeAuthResult is RegisterStrongAuthChallengeResult.VerificationRequired) {
        // Next Step: submit challenge
    } else if (challengeAuthResult is RegisterStrongAuthChallengeError) {
        // Handle errors under RegisterStrongAuthChallengeError
        when {
            error.isInvalidInput() -> {
                // Display error
            }
            else -> {
                // Unexpected error
            }
        }
    }
    ```

    `RegisterStrongAuthChallengeError`は、`registerStrongAuthState.challengeAuthMethod()`メソッドを使用してチャレンジを登録した後に処理されます。 `isInvalidInput()`メソッドを使用して、入力した確認連絡先が無効ななど、特定のエラーを確認します。 この場合、新しいチャレンジを送信するには、前の状態参照を使用する必要があります。
2. `submitChallenge()` メソッドのエラーを処理します。

    ```kotlin
    val submitChallengeResult = registerStrongAuthVerificationRequiredState.submitChallenge(challenge = emailCode)
    
    if (submitChallengeResult is SignInResult.Complete) {
        // Handle sign in success
    } else if (submitChallengeResult is RegisterStrongAuthSubmitChallengeError) {
        // Handle errors under RegisterStrongAuthSubmitChallengeError
        when {
            error.isInvalidChallenge() -> {
                // Display error
            }
            else -> {
                // Unexpected error
            }
        }
    }
    ```

    `RegisterStrongAuthSubmitChallengeError`は、`registerStrongAuthVerificationRequiredState.submitChallenge(challenge = emailCode)`を使用してチャレンジを送信した後に処理されます。 `isInvalidChallenge()`メソッドを使用して、送信されたチャレンジが無効ななどの特定のエラーを確認します。 この場合、チャレンジの送信を再パフォーマンスするには、前の状態参照を使用する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-android-identity-provider-sign-in-sign-up"} -->
## ネイティブ認証 Web フローを使用してフェデレーション ID プロバイダーのサインインと Android アプリへのサインアップを追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-identity-provider-sign-in-sign-up
- Service: identity-platform / external
- Article date: 2026-04-10
- Summary: Android アプリで Microsoft Entra のネイティブ認証を利用した Web ベースの認証フローを用いて、フェデレーション ID プロバイダーのサインインとサインアップを有効化する方法について学びます。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、Web フローでのネイティブ認証を使用して、Android アプリにフェデレーション ID プロバイダー (IdP) 認証を実装する方法について説明します。 フェデレーション IdP 認証を使用すると、ユーザーは Apple、Facebook、Google、カスタム OIDC プロバイダーなどのプロバイダーの既存のアカウントを使用してサインインまたはサインアップできます。

このチュートリアルでは、以下の内容を学習します。

- Web フローを使用してフェデレーション ID プロバイダーを使用してユーザーをサインインする
- Web フローを使用してフェデレーション ID プロバイダーを使用してユーザーをサインアップする

### 前提条件

1. [「チュートリアル: ネイティブ認証用に Android モバイル アプリを準備する」の手順を完了します](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-prepare-android-app)。
2. Microsoft Entra 外部 ID テナントでフェデレーション ID プロバイダーを構成します。 Microsoft Entra 管理センターの手順に従って、目的の ID プロバイダーを追加して構成します。

    - [Apple を ID プロバイダーとして構成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-apple-federation-customers)
    - [Facebook を ID プロバイダーとして構成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-facebook-federation-customers)
    - [GOOGLE を ID プロバイダーとして構成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-google-federation-customers)
    - [カスタム OIDC プロバイダーを構成します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-oidc-federation-customers)。 カスタム OIDC 用に構成された発行者 URI のドメインを `domain_hint`として使用します。
3. フェデレーション IdP サインインとサインアップの実装を調べる場合は、開始する前に、[sample Android アプリケーション](https://github.com/Azure-Samples/ms-identity-ciam-native-auth-android-sample/blob/main/app/src/main/java/com/azuresamples/msalnativeauthandroidkotlinsampleapp/IdPSignInSignUpWebFragment.kt)をご覧ください。

### フェデレーション ID プロバイダーを使用してユーザーをサインインさせる

Web フロー経由でフェデレーション ID プロバイダーを使用してユーザーをサインインさせるには、まず登録/認証に使用する ID プロバイダーと、対応する `domain_hint`を識別する必要があります。

- 前提条件の構成セクションで定義されている ID プロバイダーを使用します。
- `domain_hint`パラメーターを使用して、特定の ID プロバイダーに認証を送信します。 次のいずれかの値を選択します。
    - `"Apple"` アップル用
    - Facebook 対応 `"Facebook"`
    - `"Google"` Google の場合

ユーザーのサインインを行うには、次の操作を行う必要があります。

1. フェデレーション ID プロバイダーでサインインするユーザー インターフェイスを作成します。 これにより、特定の ID プロバイダーとそれに対応する `domain_hint`が識別されます。
2. クライアント アプリから`domain_hint`値が識別されたら、`INativeAuthPublicClientApplication.acquireToken`を使用して`domain_hint`を呼び出して、Social IdP による Web 認証をトリガーします。 コード スニペットの例を次に示します。

    ユーザーがサインインしている場合でも、対話型認証を強制するには、 `Prompt.LOGIN` として Prompt 値を使用します。

    ```kotlin
    private fun signInWithIdp(domainHint: String) {
        val acquireTokenParameters = AcquireTokenParameters.Builder()
            .startAuthorizationFromActivity(requireActivity())
            .withScopes(listOf("openid", "profile", "email"))
            .withDomainHint(domainHint)
            .withPrompt(Prompt.LOGIN)
            .withCallback(getAuthenticationCallback())
            .build()
    
        authClient.acquireToken(acquireTokenParameters)
    }
    ```
3. サインインの結果を処理するには、次の例のように `AuthenticationCallback`を実装する必要があります。

    ```kotlin
    private fun getAuthenticationCallback(): AuthenticationCallback {
        return object : AuthenticationCallback {
    
            override fun onSuccess(authenticationResult: IAuthenticationResult) {
                Log.d(TAG, "Successfully authenticated with IdP")
    
                val account = authenticationResult.account
                val idToken = account.idToken
                val accessToken = authenticationResult.accessToken
    
                Toast.makeText(
                    requireContext(),
                    "Sign in successful!",
                    Toast.LENGTH_SHORT
                ).show()
            }
    
            override fun onError(exception: MsalException) {
                Log.e(TAG, "Authentication failed: ${exception.message}", exception)
    
                showErrorDialog(
                    "Sign in failed",
                    exception.message ?: "An error occurred during authentication"
                )
            }
    
            override fun onCancel() {
                Log.d(TAG, "User cancelled authentication")
    
                Toast.makeText(
                    requireContext(),
                    "Sign in cancelled",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    ```

    エラー コードと処理については、 [MSAL for Android でのエラーと例外の処理に関するページを](https://learn.microsoft.com/ja-jp/entra/msal/android/handling-exceptions)参照してください。
4. また、認証に成功した後に、オブジェクトを返す`getCurrentAccount()`から`INativeAuthPublicClientApplication`を使用して、現在キャッシュされたアカウントを取得することもできます`accountResult`。

    ```kotlin
    val accountResult = iNativeAuthPublicClientApplication.getCurrentAccount()
    
    when (accountResult) {
        is GetAccountResult.AccountFound -> {
            accountResult.resultValue.getIdToken()
        }
    
        is GetAccountResult.NoAccountFound -> {
            Log.d(TAG, "No account found")
        }
    
        is GetAccountError -> {
            displayDialog(
                getString(R.string.msal_exception_title),
                accountResult.exception?.message ?: accountResult.errorMessage
            )
        }
    }
    ```

#### コンフィギュレーションの更新

1. クライアント JSON 構成ファイルに、Web ベースの認証フローを処理するための `redirect_uri` パラメーターが含まれていることを確認します。

    - [Microsoft Authentication Library (MSAL) の構成](https://learn.microsoft.com/ja-jp/entra/msal/android/msal-configuration#redirect_uri)

    ```json
    {
        "client_id": "Enter_the_Application_Id_Here",
        "redirect_uri": "msauth://com.yourpackage.name/Enter_your_Signature_Hash_Here",
        "authorities": [
        {
            "type": "CIAM",
            "authority_url": "https://Enter_the_Tenant_Subdomain_Here.ciamlogin.com/Enter_the_Tenant_Subdomain_Here.onmicrosoft.com/"
        }
        ],
        ...
    }
    ```
2. アプリの`8.1.0` ファイルに少なくとも`build.gradle`が存在しない場合は、MSAL 依存関係を追加または更新します。

    ```gradle
    dependencies {
        implementation 'com.microsoft.identity.client:msal:[8.1.0,)'
    }
    ```

### フェデレーション ID プロバイダーを使用してユーザーをサインアップする

フェデレーション ID プロバイダーを使用してユーザーをサインアップする場合、プロセスはサインインとほぼ同じですが、Prompt 値に小さな変更が加えられます。 `Prompt.CREATE`を使用します。

```kotlin
private fun signUpWithIdp(domainHint: String) {
    val acquireTokenParameters = AcquireTokenParameters.Builder()
        .startAuthorizationFromActivity(requireActivity())
        .withScopes(listOf("openid", "profile", "email"))
        .withDomainHint(domainHint)
        .withPrompt(Prompt.CREATE)
        .withCallback(getAuthenticationCallback())
        .build()
    
    authClient.acquireToken(acquireTokenParameters)
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-android-self-service-password-reset"} -->
## ネイティブ認証を使用した Android アプリでのセルフサービス パスワード リセット - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-self-service-password-reset
- Service: identity-platform / external
- Article date: 2024-02-23
- Summary: ネイティブ認証を使用して Android アプリにセルフサービス パスワード リセット (SSPR) を実装する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、管理者やヘルプ デスクの関与なしで、ユーザーが自分のパスワードを変更またはリセットできるようにする方法について説明します。

このチュートリアルでは、次の操作を行います。

- セルフサービス パスワード リセット (SSPR) フローを追加します。
- SSPR に必要なユーザー インターフェイス (UI) をアプリに追加します。
- エラーを処理します。

### 前提条件

- 「[ネイティブ Android モバイル アプリケーションのサンプル](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-run-native-authentication-sample-android-app)でユーザーをサインインする」の手順を完了します。 この記事では、テナント設定を使用して構成するサンプル Android を実行する方法について説明します。
- [セルフサービス パスワード リセット](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-enable-password-reset-customers)を有効にします。 この記事では、SSPR の要件であるテナント内のすべてのユーザーに対して電子メール ワンタイム パスコード認証方法を有効にすることができます。
- [チュートリアル: ネイティブ認証](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-sign-up)用に Android アプリを準備します。

### セルフサービス パスワード リセット フローを追加する

Android アプリケーションに SSPR フローを追加するには、パスワード リセット ユーザー インターフェイスが必要です。

- ユーザーのメール アドレス (ユーザー名) を収集するための入力テキスト フィールド。
- ワンタイム パスコードを収集する入力テキスト フィールド。
- 新しいパスワードを収集するための入力テキスト フィールド。

ユーザーがパスワードを忘れた場合、パスワード リセット フローを開始するためにユーザー名 (電子メール アドレス) を入力するフォームが必要です。 ユーザーが **[パスワードを忘れた場合]** ボタンまたはリンクを選択します。

#### パスワード リセット フローを開始する

ユーザーが [パスワードを忘れる] **ボタンまたはリンク** 選択したときに要求を処理するには、次のコード スニペットに示すように Android SDK の `resetPassword(parameters)` メソッドを使用します。

```kotlin
 private fun forgetPassword() { 
     CoroutineScope(Dispatchers.Main).launch { 
         val parameter = NativeAuthResetPasswordParameters(username = email)
         val actionResult = authClient.resetPassword(parameter)

         when (resetPasswordResult) { 
             is ResetPasswordStartResult.CodeRequired -> { 
                 // The implementation of submitCode() please see below. 
                 submitCode(resetPasswordResult.nextState) 
             } 
             is ResetPasswordError -> {
                 // Handle errors
                 handleResetPasswordError(resetPasswordResult)
             }
         }
     } 
 } 
```

- `resetPassword(parameters)` メソッドはパスワード リセット フローを開始し、電子メール ワンタイム パスコードがユーザーのメール アドレスに送信されて検証されます。
- `resetPassword(parameters)` の戻り値は、`ResetPasswordStartResult.CodeRequired` または `ResetPasswordError`です。
- `resetPasswordResult is ResetPasswordStartResult.CodeRequired`場合、アプリはユーザーから電子メールワンタイム パスコードを収集し、電子メールワンタイム パスコードに示すように送信する必要があります。
- `resetPasswordResult is ResetPasswordError`場合、Android SDK には、特定のエラーをさらに分析するためのユーティリティ メソッドが用意されています。 - `isUserNotFound()` - `isBrowserRequired()`
- これらのエラーは、前の操作が失敗したことを示しているため、新しい状態への参照を使用できません。 「エラー 処理する」セクション 示されているように、これらのエラーを処理します。

#### 電子メールワンタイム パスコードを送信する

アプリは、ユーザーから電子メールワンタイム パスコードを収集します。 電子メールワンタイム パスコードを送信するには、次のコード スニペットを使用します。

```kotlin
private suspend fun submitCode(currentState: ResetPasswordCodeRequiredState) { 
    val code = binding.codeText.text.toString() 
    val submitCodeResult = currentState.submitCode(code) 

    when (submitCodeResult) { 
        is ResetPasswordSubmitCodeResult.PasswordRequired -> { 
            // Handle success
            resetPassword(submitCodeResult.nextState) 
        } 
         is SubmitCodeError -> {
             // Handle errors
             handleSubmitCodeError(actionResult)
         }
    } 
} 
```

- `submitCode()` アクションの戻り値は、`ResetPasswordSubmitCodeResult.PasswordRequired` または `SubmitCodeError`です。
- `submitCodeResult is ResetPasswordSubmitCodeResult.PasswordRequired` 場合、アプリはユーザーから新しいパスワードを収集し、「新しいパスワードを送信する」に示すように送信する必要があります。
- ユーザーが電子メールのワンタイム パスコードを受信しない場合、アプリは電子メールワンタイム パスコードを再送信できます。 新しいメール ワンタイム パスコードを再送信するには、次のコード スニペットを使用します。

    ```kotlin
    private fun resendCode() { 
         clearCode() 
    
         val currentState = ResetPasswordCodeRequiredState 
    
         CoroutineScope(Dispatchers.Main).launch { 
             val resendCodeResult = currentState.resendCode() 
    
             when (resendCodeResult) { 
                 is ResetPasswordResendCodeResult.Success -> { 
                     // Handle code resent success
                 } 
                 is ResendCodeError -> {
                      // Handle ResendCodeError errors
                  }
             } 
         } 
    } 
    ```

    - `resendCode()` アクションの戻り値は、`ResetPasswordResendCodeResult.Success` または `ResendCodeError`です。
    - `ResendCodeError` SDK の予期しないエラーです。 このエラーは、前の操作が失敗したので、新しい状態への参照を使用できないことを示します。
- `submitCodeResult is SubmitCodeError`場合、Android SDK には、特定のエラーをさらに分析するためのユーティリティ メソッドが用意されています。

    - `isInvalidCode()`
    - `isBrowserRequired()`

    これらのエラーは、前の操作が失敗したことを示しているため、新しい状態への参照を使用できません。 「エラー 処理する」セクション 示されているように、これらのエラーを処理します。

#### 新しいパスワードを送信する

ユーザーの電子メールを確認したら、ユーザーから新しいパスワードを収集して送信する必要があります。 ユーザーからアプリが収集するパスワードは、Microsoft Entra のパスワード ポリシー 満たす必要があります。 次のコード スニペットを使用します。

```kotlin
private suspend fun resetPassword(currentState: ResetPasswordPasswordRequiredState) { 
    val password = binding.passwordText.text.toString() 

    val submitPasswordResult = currentState.submitPassword(password) 

    when (submitPasswordResult) { 
        is ResetPasswordResult.Complete -> { 
            // Handle reset password complete. 
        } 
        is ResetPasswordSubmitPasswordError -> {
            // Handle errors
            handleSubmitPasswordError(actionResult)
        }
    } 
} 
```

- `submitPassword()` アクションの戻り値は、`ResetPasswordResult.Complete` または `ResetPasswordSubmitPasswordError`です。
- `ResetPasswordResult.Complete` は、成功したパスワード リセット フローを示します。
- `submitPasswordResult is ResetPasswordSubmitPasswordError`場合、SDK には、返される特定の種類のエラーをさらに分析するためのユーティリティ メソッドが用意されています: - `isInvalidPassword()` - `isPasswordResetFailed()`

    これらのエラーは、前の操作が失敗したことを示しているため、新しい状態への参照を使用できません。 「エラー 処理する」セクション 示されているように、これらのエラーを処理します。

### パスワードリセット後の自動サインイン

パスワード リセット フローが正常に完了したら、新しいサインイン フローを開始せずにユーザーを自動的にサインインできます。

`ResetPasswordResult.Complete` はオブジェクト `SignInContinuationState` 返します。 `SignInContinuationState` は、`signIn(parameters)` メソッドへのアクセスを提供します。

パスワードのリセット後にユーザーを自動的にサインインさせるには、次のコード スニペットを使用します。

```kotlin
 private suspend fun resetPassword(currentState: ResetPasswordPasswordRequiredState) { 
     val submitPasswordResult = currentState.submitPassword(password) 
 
     when (submitPasswordResult) { 
         is ResetPasswordResult.Complete -> { 
             signInAfterPasswordReset(nextState = actionResult.nextState)
         } 
     } 
 } 
 
 private suspend fun signInAfterPasswordReset(nextState: SignInContinuationState) {
     val signInContinuationState = nextState

     val parameters = NativeAuthSignInContinuationParameters()
     val signInActionResult = signInContinuationState.signIn(parameters)

     when (actionResult) {
         is SignInResult.Complete -> {
             fetchTokens(accountState = actionResult.resultValue)
         }
         else {
             // Handle unexpected error
         }
     }
  }
 
 private suspend fun fetchTokens(accountState: AccountState) {
     val getAccessTokenParameters = NativeAuthGetAccessTokenParameters()
     val accessTokenResult = accountState.getAccessToken(getAccessTokenParameters)

     if (accessTokenResult is GetAccessTokenResult.Complete) {
         val accessToken =  accessTokenResult.resultValue.accessToken
         val idToken = accountState.getIdToken()
     }
 }
```

サインイン後に ID トークン要求を取得するには、「id トークン要求の読み取り の手順を使用します。

### パスワード リセット エラーの処理

予期されるエラーがいくつか発生する可能性があります。 たとえば、ユーザーが存在しないメールでパスワードをリセットしようとしたり、パスワードの要件を満たしていないパスワードを指定しようとしたりすることがあります。

エラーが発生した場合は、ユーザーにエラーのヒントを提供します。

これらのエラーは、パスワード リセット フローの開始時、または電子メールのワンタイム パスコードの送信時、またはパスワードの送信時に発生する可能性があります。

#### パスワード リセットの開始エラーを処理する

パスワードリセットの開始に起因するエラーを処理するには、次のコード スニペットを使用します。

```kotlin
private fun handleResetPasswordError(error: ResetPasswordError) {
    when {
        error.isUserNotFound() -> {
            // Display error
        }
        else -> {
            // Unexpected error
        }
    }
}
```

#### メールのワンタイムパスコード送信で発生したエラーを処理する

電子メールのワンタイム パスコードの送信によって発生するエラーを処理するには、次のコード スニペットを使用します。

```kotlin
private fun handleSubmitCodeError(error: SubmitCodeError) {
    when {
        error.isInvalidCode() -> {
            // Display error
        }
        else -> {
            // Unexpected error
        }
    }
}
```

#### パスワード送信エラーの処理

パスワードの送信によって発生するエラーを処理するには、次のコード スニペットを使用します。

```kotlin
private fun handleSubmitPasswordError(error: ResetPasswordSubmitPasswordError) {
    when {
        error.isInvalidPassword() || error.isPasswordResetFailed()
        -> {
            // Display error
        }
        else -> {
            // Unexpected error
        }
    }
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-android-sign-in-after-sign-up"} -->
## ネイティブ認証を使用した Android でのサインアップ後にユーザーを自動的にサインインさせる - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-sign-in-after-sign-up
- Service: identity-platform / external
- Article date: 2024-03-20
- Summary: ネイティブ認証を使用した Android アプリでのサインアップ後にユーザーを自動的にサインインさせる方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ネイティブ認証を使用した Android アプリでのサインアップ後にユーザーを自動的にサインインさせる方法について説明します。

このチュートリアルでは、次の操作を行います。

- サインアップ後にサインインします。
- エラーを処理します。

### 前提条件

- [サンプルのネイティブ Android モバイル アプリケーションでユーザーをサインインさせる](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-run-native-authentication-sample-android-app)に関する記事の手順を完了します。 この記事では、テナント設定を使用して構成するサンプル Android を実行する方法について説明します。
- [チュートリアル: ネイティブ認証を使用して Android モバイル アプリでサインアップを追加する。](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-sign-up) このチュートリアルの手順は、サインアップにメール アドレスとパスワードを使用するか、メールのワンタイム パスコードを使用するかに関係なく機能します。

### サインアップ後にサインインする

サインアップ フローが正常に完了したら、新しいサインイン フローを開始せずにユーザーを自動的にサインインさせることができます。

`SignUpResult.Complete` はオブジェクト `SignInContinuationState` 返します。 `SignInContinuationState` オブジェクトでは `signIn(parameters)` メソッドへのアクセスを提供します。

メール アドレスとパスワードを使用してユーザーをサインアップし、自動的にサインインさせるには、次のコード スニペットを使用できます:

```kotlin
CoroutineScope(Dispatchers.Main).launch {
    val parameters = NativeAuthSignUpParameters(username = email)
    parameters.password = password
    val actionResult: SignUpResult = authClient.signUp(parameters)

    if (SignUpActionResult is SignUpResult.CodeRequired) { 
        val nextState = signUpActionResult.nextState 
        val submitCodeActionResult = nextState.submitCode( 
            code = code 
        ) 
        if (submitCodeActionResult is SignUpResult.Complete) {
            // Handle sign up success 
            val signInContinuationState = actionResult.nextState 

            val parameters = NativeAuthSignInContinuationParameters()
            val signInActionResult = signInContinuationState.signIn(parameters)

            if (signInActionResult is SignInResult.Complete) { 
                // Handle sign in success
                val accountState = signInActionResult.resultValue

                val getAccessTokenParameters = NativeAuthGetAccessTokenParameters()
                val accessTokenResult = accountState.getAccessToken(getAccessTokenParameters)

                if (accessTokenResult is GetAccessTokenResult.Complete) {
                    val accessToken = accessTokenResult.resultValue.accessToken
                    val idToken = accountState.getIdToken()
                }
            } 
        } 
    } 
}
```

サインイン後に ID トークン要求を取得するには、「id トークン要求の読み取り の手順を使用します。

### サインイン エラーを処理する

`SignInContinuationState.signIn(parameters)` メソッドでは、サインインが成功した後に `SignInResult.Complete` を返します。 また、エラーを返すこともあります。

`SignInContinuationState.signIn(parameters)` でエラーを処理するには、次のコード スニペットを使用します:

```kotlin
val parameters = NativeAuthSignInContinuationParameters()
val signInActionResult = signInContinuationState.signIn(parameters)

when (signInActionResult) {
    is SignInResult.Complete -> {
        // Handle sign in success
         displayAccount(accountState = actionResult.resultValue)
    }
    is SignInContinuationError -> {
        // Handle unexpected error
    }
    else -> {
        // Handle unexpected error
    }
}

private fun displayAccount(accountState: AccountState) {
    CoroutineScope(Dispatchers.Main).launch {
        val getAccessTokenParameters = NativeAuthGetAccessTokenParameters()
        val accessTokenResult = accountState.getAccessToken(getAccessTokenParameters)
        if (accessTokenResult is GetAccessTokenResult.Complete) {
            val accessToken = accessTokenResult.resultValue.accessToken
            val idToken = accountState.getIdToken()
        }
    }
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-android-sign-in-call-api"} -->
## ネイティブ認証を使用して Android アプリで API を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-sign-in-call-api
- Service: identity-platform / external
- Article date: 2024-07-07
- Summary: ネイティブ認証を使用して、複数のアクセス トークンを取得し、Android アプリで API を呼び出す方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、アクセス トークンを取得し、Android モバイル アプリで API を呼び出す方法について説明します。 Android 用 Microsoft Authentication Library (MSAL) ネイティブ認証 SDK を使用すると、シングル サインインで複数のアクセス トークンを取得できます。 この機能を使用すると、ユーザーに再認証を要求する必要なく、1 つ以上のアクセス トークンを取得できます。

このチュートリアルでは、次の操作を行います。

- 1 つまたは複数のアクセス トークンを取得します。
- API を呼び出す

### [前提条件]

- [「チュートリアル: ネイティブ認証を使用して Android アプリにサインインを追加](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-sign-in-sign-out)する」の手順を完了します。 このチュートリアルでは、ネイティブ認証を使用して Android アプリでユーザーをサインインさせる方法について説明します。

### アクセス トークンを取得する

ユーザーがサインインしたら、アクセス トークンが有効なスコープを指定してアクセス トークンを取得します。

MSAL ネイティブ認証 SDK では、複数のアクセス トークンがサポートされているため、複数のスコープ セットを指定し、スコープのセットごとにアクセス トークンを要求できます。

1. 次のコード スニペットを使用して、一連の API スコープを宣言し値を設定します。

    ```kotlin
    companion object {
        // Set values for respective API scopes for their web API resources here, for example: ["api://<Resource_App_ID>/ToDoList.Read", "api://<Resource_App_ID>/ToDoList.ReadWrite"]
        // A list of scope for API 1
        private val scopesForAPI1 = listOf<String>()
        // A list of scope for API 2
        private val scopesForAPI2 = listOf<String>()
    }
    ```
2. 次のコード スニペットを使用してユーザーにサインインさせます。

    ```kotlin
    CoroutineScope(Dispatchers.Main).launch {
        val parameters = NativeAuthSignInParameters(username = email)
        parameters.password = password
        val actionResult: SignInResult = authClient.signIn(parameters)
    
        if (actionResult is SignInResult.Complete) -> {
            // Perform operations after successful sign-in
        } else if (actionResult is SignInError) {
            // Handle sign-in errors
        }
    }
    ```
3. 次のコード スニペットを使用して、1 つまたは複数のアクセス トークンを取得します。

    ```kotlin
    CoroutineScope(Dispatchers.Main).launch {
        val accountResult = authClient.getCurrentAccount()
        when (accountResult) {
            is GetAccountResult.AccountFound -> {
                try {
                    // Access token for API 1
                    val accessTokenOne = getAccessToken(accountResult.resultValue, scopesForAPI1)
                    // Access token for API 2
                    val accessTokenTwo = getAccessToken(accountResult.resultValue, scopesForAPI2)
                    // Proceed to make a call to an API
                } catch (e: Exception) {
                    // Handle Exception
                }
            }
            is GetAccountResult.NoAccountFound -> {
                // Handle etAccountResult.NoAccountFound
            }
            is GetAccountError -> {
                // Handle GetAccountError 
            }
        }
    }   
    
    ```

    次のコードに示すように、 `getAccessToken()` 関数を定義します。

    ```kotlin
    private suspend fun getAccessToken(accountState: AccountState, scopes: List<String>): String {
        val parameters = NativeAuthGetAccessTokenParameters()
        parameters.scopes = scopes
        val accessTokenState = accountState.getAccessToken(parameters)
    
        return if (accessTokenState is GetAccessTokenResult.Complete) {
            accessTokenState.resultValue.accessToken
        } else {
            throw Exception("Failed to get access token")
        }
    }
    ```

### API を呼び出す

API 呼び出しを行うには、「アクセス トークンの取得」で取得した アクセス トークン と API URL を使用します。

1. 次のコード スニペットを使用して、API URL の値を宣言して設定します。

    ```kotlin
    companion object {
        // Set values for respective API scopes for web API resources here, for example: ["api://<Resource_App_ID>/ToDoList.Read", "api://<Resource_App_ID>/ToDoList.ReadWrite"]
        // A list of scope for API 1
        private val scopesForAPI1 = listOf<String>()
        // A list of scope for API 2
        private val scopesForAPI2 = listOf<String>()
        // Set the URL of first web API resource here
        private const val WEB_API_URL_1 = "Enter_URL_Of_First_Web_API" 
        // Set the URL of second web API resource here
        private const val WEB_API_URL_2 = "Enter_URL_Of_Second_Web_API" 
    }
    ```

    次を置換します。

    - `Enter_URL_Of_First_Web_API` 最初の API の完全な URL 値を含むプレースホルダー。
    - `Enter_URL_Of_Second_Web_API` 2 番目の API の完全な URL 値を含むプレースホルダー。
2. 次のコード スニペットを使用して API を呼び出します。

    ```kotlin
    // After you acquire an access token, use it to call an API
    
    val firstApiResponse = useAccessToken(WEB_API_URL_1, accessTokenOne)
    val secondApiResponse = useAccessToken(WEB_API_URL_2, accessTokenTwo)
    
    private suspend fun useAccessToken(WEB_API_URL: String, accessToken: String): Response {
        return withContext(Dispatchers.IO) {
            ApiClient.performGetApiRequest(WEB_API_URL, accessToken)
        }
    }
    ```

    次のコードに示すように、 `performGetApiRequest()` 関数を定義します。

    ```kotlin
    object ApiClient {
        private val client = OkHttpClient()
    
        fun performGetApiRequest(WEB_API_URL: String, accessToken: String): Response {    
            val requestBuilder = Request.Builder()
                    .url(WEB_API_URL)
                    .addHeader("Authorization", "Bearer $accessToken")
                    .get()
    
            val request = requestBuilder.build()
    
            client.newCall(request).execute().use { response -> return response }
        }
    }
    ```

### カスタム クレーム プロバイダーの構成

外部システムからの要求をアプリに発行されたトークンに追加する場合は、 [カスタム要求プロバイダー](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-overview)を使用します。 カスタム クレーム プロバイダーは、外部システムからクレームを取り込むために、外部 REST API を呼び出すカスタム認証拡張機能で構成されています。

「外部システムからの要求をセキュリティ トークンに追加するように [カスタム要求プロバイダーを構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-configuration) する」の手順に従います。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-android-sign-in-sign-out"} -->
## ネイティブ認証を使用して Android アプリにサインインを追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-sign-in-sign-out
- Service: identity-platform / external
- Article date: 2025-11-17
- Summary: ネイティブ認証を使用して Android アプリでメールのワンタイム パスコードまたはユーザー名とパスワードでのサインインとサインアウトを追加する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、Android モバイル アプリでネイティブ認証を使用し、メールのワンタイム パスコードまたはユーザー名とパスワードを使ってユーザーのサインインとサインアウトを行う方法でについて説明します。

このチュートリアルでは、次の操作を行います。

- メールのワンタイム パスコードまたはユーザー名 (メール アドレス) とパスワードを使ってユーザーのサインインを行います。
- ユーザーのサインアウト。
- サインイン エラーを処理する

### [前提条件]

- [チュートリアル: ネイティブ認証用に Android アプリを準備する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-sign-up)に関する記事の手順を完了します。 このチュートリアルでは、ネイティブ認証用に Android プロジェクトまたはアプリを準備する方法について説明します。

### ユーザーのサインイン

ワンタイム パスコードを使用してユーザーのサインインを行うには、メール アドレスを収集し、ユーザーが自身のメール アドレスを検証できるようにワンタイム パスコードを含むメールを送信します。 有効なワンタイム パスコードを入力すると、アプリはユーザーをサインインさせます。

ユーザー名 (メール アドレス) とパスワードを使用してユーザーのサインインを行うには、ユーザーからメール アドレスとパスワードを収集します。 ユーザー名とパスワードが有効な場合、アプリによってユーザーのサインインが行われます。

テナントのサインアップ ユーザー フローで **Username** 組み込みユーザー属性が有効になっている場合、サインインの `username` パラメーターは、ユーザーのメール アドレスまたはユーザー名 (エイリアス) を受け入れます。 エイリアス ベースのサインインをサポートするために、個別のサインイン ロジックは必要ありません。

ユーザーのサインインを行うには、次の操作を行う必要があります。

1. 次のことを行うためのユーザー インターフェイス (UI) を作成します。

    - ユーザーからメール アドレスを収集します。 入力に検証を追加して、ユーザーが有効なメール アドレスを入力していることを確認します。
    - ユーザー名 (メール アドレス) とパスワードを使ってサインインを行う場合は、パスワードを収集します。
    - 電子メール ワンタイム パスコードでサインインする場合は、ユーザーから電子メールのワンタイム パスコードを収集します。
    - 電子メールのワンタイム パスコードでサインインする場合は、ワンタイム パスコードを再送信します (推奨)。
2. UI で、次のコード スニペットに示すように select イベントによってサインインを開始するボタンを追加します。

    ```kotlin
     CoroutineScope(Dispatchers.Main).launch {
         val parameters = NativeAuthSignInParameters(username = email)
         // Assign 'password' param if you sign in with username (email) and password
         // parameters.password = password
         val actionResult: SignInResult = authClient.signIn(parameters)
    
         if (actionResult is SignInResult.CodeRequired) {
             val nextState = actionResult.nextState
             val submitCodeActionResult = nextState.submitCode(
                 code = code
             )
             if (submitCodeActionResult is SignInResult.Complete) {
                 // Handle sign in success
                 val accountState = submitCodeActionResult.resultValue
    
                 val getAccessTokenParameters = NativeAuthGetAccessTokenParameters()
                 val accessTokenResult = accountState.getAccessToken(getAccessTokenParameters)
    
                 if (accessTokenResult is GetAccessTokenResult.Complete) {
                     val accessToken = accessTokenResult.resultValue.accessToken
                     val idToken = accountState.getIdToken()
                 }
             }
         }
     }
    ```

    例えば、ユーザーが電子メールとパスワードでサインインするなど、ユーザーがパスコードを送信する必要がない場合は以下のコード スニペットを使用します。

    ```kotlin
    CoroutineScope(Dispatchers.Main).launch {
        val parameters = NativeAuthSignInParameters(username = email)
        parameters.password = password
        val actionResult: SignInResult = authClient.signIn(parameters)
    
        if (actionResult is SignInResult.Complete) -> {
            // Handle sign in success
            val accountState = actionResult.resultValue
    
            val getAccessTokenParameters = NativeAuthGetAccessTokenParameters()
            val accessTokenResult = accountState.getAccessToken(getAccessTokenParameters)
    
            if (accessTokenResult is GetAccessTokenResult.Complete) {
                val accessToken = accessTokenResult.resultValue.accessToken
                val idToken = accountState.getIdToken()
            }
        }
    }
    ```

    - サインイン フローを開始するには、SDK の `signIn(parameters)` メソッドを使用します。
    - ユーザーから収集した電子メール アドレスである `NativeAuthSignInParameters` を含む、`username` クラスのインスタンス。
    - サインイン方法がユーザー名 (メール アドレス) とパスワードである場合、メソッドのパラメーター `password` はユーザーから収集したパスワードです。
    - 最も一般的なシナリオでは、`signIn(parameters)` は結果 `SignInResult.CodeRequired`を返します。これは、アプリがユーザーの電子メール アドレスに送信された電子メール ワンタイム パスコードを送信することを SDK が予期していることを示します。
    - `SignInResult.CodeRequired` オブジェクトには新しい状態参照が含まれており、`actionResult.nextState` を通じて取得できます。
    - 新しい状態により、次の 2 つの新しいメソッドにアクセスできるようになります。
        - `submitCode()` では、アプリによってユーザーから収集するメールのワンタイム パスコードを送信します。
        - `resendCode()` では、ユーザーがコードを受信しなかった場合にメールのワンタイム パスコードを再送信します。

#### サインイン エラーを処理する

サインイン中、すべてのアクションが成功するわけではありません。 たとえば、ユーザーが存在しないメール アドレスでサインインしようとしたり、無効なコードを送信したりすることがあります。

##### サインインの開始エラーを処理する

`signIn(parameters)` メソッドのエラーを処理するには、次のコード スニペットを使用します。

```Kotlin
 val parameters = NativeAuthSignInParameters(username = email)
 // Assign 'password' param if you sign in with username (email) and password
 // parameters.password = password
val actionResult: SignInResult = authClient.signIn(parameters)

if (actionResult is SignInResult.CodeRequired) {
    // Next step: submit code
} else if (actionResult is SignInError) {
    // Handle sign in errors
    when {
         actionResult.isUserNotFound() -> {
             // Handle "user not found" error
         }
         actionResult.isAuthNotSupported() -> {
             // Handle "authentication type not support" error
         }
         actionResult.isInvalidCredentials() -> {
             // Handle specific errors
         }
         else -> {
             // Handle other errors
         }
     }
}
```

- `SignInError` は、`signIn(parameters)` によって返された失敗したアクション結果を示します。そのため、アクション結果には新しい状態への参照が含まれません。
- `actionResult is SignUpError`の場合、Android SDK には、特定のエラーをさらに分析できるようにするユーティリティ メソッドが用意されています。
    - メソッド `isUserNotFound()` では、存在しないユーザー名 (メール アドレス) を使用してユーザーがサインインしているかどうかを確認します。
    - メソッド `isBrowserRequired()` では、認証フローを完了するためにブラウザー (Web フォールバック) が必要であるかどうかを確認します。 このシナリオは、認証フローを完了するのにネイティブ認証だけでは十分ではない場合に発生します。 たとえば、管理者は認証方法としてメール アドレスとパスワードを構成しても、アプリが *password* をチャレンジ型として送信できないか、単にそれをサポートしていない場合などです。 これが発生するシナリオを処理するには、[Android アプリでの Web フォールバックのサポート](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-support-web-fallback)に関する記事の手順を使用します。
    - メソッド `isAuthNotSupported()` は、Microsoft Entra でサポートされていないチャレンジ型 (*oob* および *password* 以外のチャレンジ型の値) をアプリで送信しているかどうかを確認します。 [チャレンジ型](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-challenge-types)の詳細を参照してください。
    - ユーザー名 (メール アドレス) とパスワードのサインインの場合、メソッド `isInvalidCredentials()` を使ってユーザー名とパスワードの組み合わせが間違っているかどうかを確認します。

##### 送信コード エラーを処理する

`submitCode()` メソッドでエラーを処理するには、次のコード スニペットを使用します:

```Kotlin
val submitCodeActionResult = nextState.submitCode(
    code = code
)
if (submitCodeActionResult is SignInResult.Complete) {
    // Sign in flow complete, handle success state.
} else if (submitCodeActionResult is SubmitCodeError && submitCodeActionResult.isInvalidCode()) {
    // Handle "invalid code" error
}
```

- `SubmitCodeError` エラーは、`submitCode()` によって返されたアクションが失敗した結果を示します。そのため、アクションの結果には新しい状態への参照は含まれません。
- `isInvalidCode()` では特定のエラーがチェックされます。 この場合、アクションを再実行するには、前の状態参照を使用する必要があります。

新しいメールのワンタイム パスコードを取得するには、以下のコード スニペットを使用します。

```kotlin
val submitCodeActionResult = nextState.submitCode(
    code = code
)
if (submitCodeActionResult is SignInError && submitCodeActionResult.isInvalidCode) {
    // Inform the user that the submitted code was incorrect or invalid, then ask them to input a new email one-time passcode
    val newCode = retrieveNewCode()
    nextState.submitCode(
        code = newCode
    )
}
```

アプリでユーザーを正常にサインアップさせるために必要なすべての手順を完了しました。 アプリケーションをビルドして実行します。 問題がなければ、メール アドレスを指定し、コードをメールで受け取り、それを使用してユーザーのサインインを完了することができます。

### ID トークン要求を読み取る

アプリが ID トークンを獲得したら、現在のアカウントに関連付けられている要求を取得できます。 これを行うには、次のコード スニペットを使用します。

```kotlin
val preferredUsername = accountState.getClaims()?.get("preferred_username")
val city = accountState.getClaims()?.get("City")
val givenName = accountState.getClaims()?.get("given_name")
//custom attribute
val loyaltyNumber = accountState.getClaims()?.get("loyaltyNumber")
```

要求値へのアクセスに使用するキーは、ユーザー属性をトークン要求として追加するときに指定する名前です。

組み込みおよびカスタム属性をトークン要求として追加する方法については、「[トークン要求にユーザー属性を追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-add-attributes-to-token)」記事を参照してください。

### ユーザーのサインアウト

ユーザーをサインアウトさせるには、キャッシュに現在保存されているアカウントを削除する必要があります。

1. 以下を含むカスタム ユーザー インターフェイス (UI) を作成します。

    - サインアウト要求を送信するためにユーザーが選択するサインアウト ボタン。
2. ユーザーをサインアウトさせるには、次のコードを使用します。

    ```kotlin
    private fun performSignOut(accountState: AccountState) {
         CoroutineScope(Dispatchers.Main).launch {
             val accountResult = authClient.getCurrentAccount()
             if (accountResult is GetAccountResult.AccountFound) {
                 val signOutResult = accountResult.resultValue.signOut()
                 if (signOutResult is SignOutResult.Complete) {
                     // Show sign out successful UI
                 }
             }
         }
     }
    ```

#### サインアウト エラーを処理する

サインアウトでエラーが発生しないようにする必要があります。 エラーが発生した場合は、次のコード スニペットを使用してエラー結果を検査します。

```kotlin
val actionResult = accountResult.signOut()
if (actionResult is SignOutResult.Complete) {
    // Show sign out successful UI
} else {
    // Handle errors
}
```

必ず import ステートメントを含めます。 Android Studio では、自動的に import ステートメントが含められます。

アプリでユーザーを正常にサインアウトさせるために必要なすべての手順を完了しました。 アプリケーションをビルドして実行します。 問題がなければ、サインアウト ボタンを選択して正常にサインアウトできます。

### カスタム クレーム プロバイダーの構成

外部システムからのクレームをアプリに発行されるトークンに追加する場合は、[カスタム クレーム プロバイダー](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-overview)を使用します。 カスタム クレーム プロバイダーは、外部システムからクレームを取り込むために、外部 REST API を呼び出すカスタム認証拡張機能で構成されています。

[カスタム クレーム プロバイダーの構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-configuration)の手順に従って、外部システムからのクレームをセキュリティ トークンに追加します。

### エイリアスまたはユーザー名を使用してサインインを有効にする

メール アドレスとパスワードを使用してサインインするユーザーも、ユーザー名とパスワードでサインインできます。 代替サインイン識別子とも呼ばれるユーザー名には、顧客 ID、アカウント番号、またはユーザー名として使用する別の識別子を指定できます。

Microsoft Entra 管理センターを使用してユーザー アカウントにユーザー名を手動で割り当てたり、Microsoft Graph API を使用してアプリで自動化したりできます。

[「エイリアスまたはユーザー名](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias)を使用してサインインする」の記事の手順を使用して、ユーザーがアプリケーションでユーザー名を使用してサインインできるようにします。

1. [サインインでユーザー名を有効にします](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#enable-username-in-sign-in-identifier-policy)。
2. [管理センターでユーザー名を持つユーザーを作成するか、ユーザー名](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username) を [追加して既存のユーザーを更新します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username)。 または、 [Microsoft Graph API を使用して、アプリでのユーザーの作成と更新を自動化](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username)することもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-android-sign-up"} -->
## ネイティブ認証を使用して Android アプリにサインアップを追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-sign-up
- Service: identity-platform / external
- Article date: 2026-06-16
- Summary: ネイティブ認証を使用して、メール ワンタイム パスコードまたは電子メールとパスワードを使用してユーザーをサインアップし、Android アプリでユーザー属性 (エイリアス) を収集する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ネイティブ認証を使用して Android モバイル アプリで電子メール ワンタイム パスコードまたはユーザー名 (電子メール) とパスワードを使用してユーザーをサインアップする方法について説明します。 また、サインアップ時にユーザー属性 (ユーザー名 (エイリアス) を含む) を収集し、エラーを処理する方法についても説明します。

このチュートリアルでは、次の操作を行います。

- メールのワンタイム パスコードまたはメール アドレスとパスワードの組み合わせを使用してユーザーを登録します。
- ユーザー名 (エイリアス) など、サインアップ時にユーザー属性を収集します。
- サインアップ エラーを処理します。

### [前提条件]

- [チュートリアル: ネイティブ認証用に Android アプリを準備する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-prepare-android-app)に関する記事の手順を完了します。
- サインアップ中にユーザ属性を収集する場合は、「[サインアップとサインインのユーザー フローを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)ときにユーザー属性を構成します。
- サインアップ時にユーザー名 (エイリアス) を収集するには、テナントのサインアップ ユーザー フローで **Username** 組み込みユーザー属性を有効にします。

### ユーザーをサインアップする

電子メールワンタイム パスコードまたはユーザー名 (電子メール) とパスワードを使用してユーザーをサインアップするには、ユーザーからメールを収集し、電子メールワンタイム パスコードを含む電子メールをユーザーに送信します。 ユーザーは、有効なメールのワンタイム パスコードを入力し、ユーザー名を検証します。

ユーザーをサインアップするには、次の操作を行う必要があります。

1. 「ユーザーインターフェース (UI) を作成して、以下を行う：」

    - ユーザーからメール アドレスを収集します。 ユーザーが有効なメール アドレスを入力していることを確認するために、入力に検証を追加します。
    - ユーザー名 (メール アドレス) とパスワードでサインアップした場合、パスワードを収集します。
    - アプリでエイリアス ベースのサインインがサポートされている場合は、ユーザー名 (エイリアス) を収集します。
    - ユーザーからメールのワンタイム パスコードを収集します。
    - 必要に応じて、ユーザー属性を収集します。
    - ワンタイム パスコードを再送信します (推奨)。
    - サインアップ フローを開始します。
2. アプリで、select イベントによって次のコード スニペットをトリガーするボタンを追加します。

    ```kotlin
    CoroutineScope(Dispatchers.Main).launch {
         val parameters = NativeAuthSignUpParameters(username = email)
         // Assign 'password' param if you sign in with username (email) and password
         // parameters.password = password
         val actionResult: SignUpResult = authClient.signUp(parameters)
    
         if (actionResult is SignUpResult.CodeRequired) {
             val nextState = actionResult.nextState
             val submitCodeActionResult = nextState.submitCode(
                code = code
             )
             if (submitCodeActionResult is SignUpResult.Complete) {
                // Handle sign up success
             }
        }
    }
    ```

    - SDK のインスタンス メソッド `signUp(parameters)` を使用して、サインアップ フローを開始します。
    - ユーザー名 (メール アドレス) とパスワードを使用してサインアップするには、`NativeAuthSignUpParameters` クラスのインスタンスを作成し、ユーザー名とパスワードを割り当てます。
    - サインアップ パラメーター `username`は、ユーザーから収集した電子メール アドレスです。
    - 最も一般的なシナリオでは、 `signUp(parameters)` は結果 ( `SignUpResult.CodeRequired`) を返します。これは、SDK がアプリがユーザーのメール アドレスに送信された電子メール ワンタイム パスコードを送信することを想定していることを示します。
    - `SignUpResult.CodeRequired` オブジェクトには、`actionResult.nextState`を使用して取得できる新しい状態参照が含まれています。
    - 新しい状態では、次の 2 つの新しいメソッドにアクセスできます。
        - `submitCode()` では、アプリによってユーザーから収集するメールのワンタイム パスコードを送信します。
        - `resendCode()` では、ユーザーがコードを受信しなかった場合にメールのワンタイム パスコードを再送信します。
    - `submitCode()` は `SignUpResult.Complete` を返しますが、これは、フローが完了し、ユーザーがサインアップされたことを示します。
    - `signUp(parameters)` は、エラーが発生したことを示す `SignUpError` を返すこともできます。

### サインアップ中にユーザー属性を収集する

メールのワンタイム パスコードを使用してユーザーをサインアップする場合でも、ユーザー名 (メール アドレス) とパスワードの組み合わせを使用して登録する場合でも、ユーザーのアカウントが作成される前にユーザー属性を収集できます。

- `NativeAuthSignUpParameters` インスタンスは、`attributes` パラメーターを受け入れます。

    ```kotlin
        CoroutineScope(Dispatchers.Main).launch {
            val parameters = NativeAuthSignUpParameters(username = email)
            // Assign 'password' param if you sign in with username (email) and password
            // parameters.password = password
            parameters.attributes = userAttributes
            val actionResult: SignUpResult = authClient.signUp(parameters)
            //...
        }
    ```
- Android SDK には、ユーザー属性を作成するために使用するユーティリティ クラス `UserAttribute.Builder` が用意されています。 たとえば、*city* および *country* ユーザー属性を送信するには、次のコード スニペットを使用して `userAttributes` 変数を構築します。

    ```kotlin
         val userAttributes = UserAttributes.Builder ()
        .country(country) 
        .city(city) 
        .build()   
    ```

    `UserAttribute.Builder` クラス内のメソッド名は、それらによって作成される、プログラム可能なユーザー属性名と同じです。 [Android SDK 属性ビルダー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-native-authentication-user-attribute-builder?tabs=android-kotlin)の詳細を確認します。
- `signUp(parameters)` メソッドは、`SignUpResult.AttributesRequired` を返して、Microsoft Entra がアカウントを作成する前に、アプリが 1 つ以上の必須属性を送信する必要があることを示すことができます。 これらの属性は、管理者によって Microsoft Entra 管理センターで必須のものとして構成されています。 Microsoft Entra では、オプションのユーザー属性を明示的に要求しません。
- `SignUpResult.AttributesRequired` の結果には、`requiredAttributes` パラメーターが含まれています。 `requiredAttributes` は、`RequiredUserAttribute` オブジェクトの一覧であり、アプリから送信される必要があるユーザー属性に関する詳細を含んでいます。 `actionResult is SignUpResult.AttributesRequired` を処理するには、次のコード スニペットを使用します。

    ```kotlin
    val parameters = NativeAuthSignUpParameters(username = email)
    // Assign 'password' param if you sign in with username (email) and password
    // parameters.password = password
    parameters.attributes = userAttributes
    val actionResult: SignUpResult = authClient.signUp(parameters)
    
    if (actionResult is SignUpResult.AttributesRequired) {
            val requiredAttributes = actionResult.requiredAttributes 
            // Handle "attributes required" result 
            val nextState = actionResult.nextState
            nextState.submitAttributes(
                attributes = moreAttributes
            )
    }
    ```

### サインアップ時にユーザー名 (エイリアス) を収集する

ユーザー名 (エイリアス) は特殊なユーザー属性です。 市区町村や国などの他の属性と同様に、サインアップ時に収集します。 これらの属性とは異なり、ユーザーは後でエイリアスを使用してサインインできます。 エイリアス ("johndoe" など) を使用すると、ユーザーはメール アドレスよりも短く、よりわかりやすい方法でサインインできます。

ユーザー名 (エイリアス) は、ユーザー名 (電子メール) に置き換えられません。 サインアップ時に、アプリは常にプライマリ識別子としてユーザー名 (電子メール) を収集し、エイリアスを電子メールと共に属性として収集する必要があります。 サインイン時に、ユーザーは自分のユーザー名 (電子メール) またはユーザー名 (エイリアス) を使用してサインインすることを選択できます。

サインアップ ユーザー フローで **Username** 組み込みユーザー属性が有効になっている場合、SDK は、`UserAttributes` メソッドを使用して、他の属性に使用されるのと同じ`flatUsername()` ビルダーを介してそれを受け入れます。 サインアップ呼び出しでユーザー名 (エイリアス) を直接渡すことができるため、ユーザーは別の属性に必要な手順を実行する必要はありません。

ユーザー名 (エイリアス) を収集するには、サインアップ UI のユーザー名の入力フィールドを電子メール フィールドと共に追加し、サインアップ呼び出しで属性としてエイリアスを渡します。

```kotlin
val email = binding.emailText.text.toString()
val password = binding.passwordText.text.toString()
val username = binding.usernameText.text.toString()

val attributes = UserAttributes.Builder()
    .flatUsername(username)
    .build()

CoroutineScope(Dispatchers.Main).launch {
    val actionResult = authClient.signUpUsingPassword(
        username = email,
        password = password,
        attributes = attributes
    )

    when (actionResult) {
        is SignUpResult.CodeRequired -> {
            // Navigate to code verification
            navigateToCodeVerification(actionResult.nextState)
        }
        is SignUpUsingPasswordError -> {
            handleSignUpError(actionResult)
        }
    }
}
```

電子メールワンタイム パスコード フロー (パスワードなし) の場合は、`signUp`の代わりに `signUpUsingPassword` を使用します。

```kotlin
val actionResult = authClient.signUp(
    username = email,
    attributes = attributes
)
```

### サインアップ エラーを処理する

サインアップ中、すべてのアクションが成功するわけではありません。 たとえば、ユーザーが既に使用されているメール アドレスでサインアップしようとしたり、無効なメールのワンタイム パスコードを送信したりすることがあります。

#### サインアップの開始エラーを処理する

`signUp()` メソッドのエラーを処理するには、次のコード スニペットを使用します。

```kotlin
 val parameters = NativeAuthSignUpParameters(username = email)
 // Assign 'password' param if you sign in with username (email) and password
 // parameters.password = password
val actionResult: SignUpResult = authClient.signUp(parameters)

if (actionResult is SignUpResult.CodeRequired) {
    // Next step: submit code
} else if (actionResult is SignUpError) {
     when {
         actionResult.isUserAlreadyExists() -> {
             // Handle "user already exists" error
         }
         else -> {
             // Handle other errors
         }
     }
}
```

- `signUp(parameters)` は `SignUpError` を返すことができます。
- `SignUpError` は、`signUp()` によって返されたアクションが失敗した結果を示し、新しい状態への参照は含まれません。
- `actionResult is SignUpError`場合、Microsoft Authentication Library (MSAL) Android SDK には、特定のエラーをさらに分析するためのユーティリティ メソッドが用意されています。

    - このメソッド `isUserAlreadyExists()` は、アカウントの作成にユーザー名またはエイリアスが既に使用されているかどうかを確認します。
    - `isInvalidAttributes()` では、アプリから送信された 1 つまたは複数の属性が、データ型が間違っているなど、検証で失敗したかどうかを確認します。 これには、 `invalidAttributes` パラメーターが含まれています。これは、アプリが送信したが検証に失敗したすべての属性の一覧です。
    - `isInvalidPassword()` は、パスワードが無効かどうかを確認します (パスワードがすべてのパスワードの複雑さの要件を満たしていない場合など)。 [Microsoft Entra のパスワード ポリシーについての詳細を確認してください](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)
    - `isInvalidUsername()` は、ユーザーの電子メールが無効な場合など、ユーザー名が無効かどうかを確認します。
    - `isBrowserRequired()` は、認証フローを完了するためにブラウザー (Web フォールバック) が必要かどうかを確認します。 このシナリオは、認証フローを完了するのにネイティブ認証だけでは十分ではない場合に発生します。 たとえば、管理者は認証方法として電子メールとパスワードを構成しますが、アプリはチャレンジの種類として *パスワード* を送信できないか、サポートしていません。 このシナリオを処理するには、 [Android アプリでの Web フォールバックのサポートに](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-support-web-fallback) 関するページの手順を使用します。
    - `isAuthNotSupported()` は、Microsoft Entra でサポートされていないチャレンジ型 (*oob* または *password* 以外のチャレンジ型の値) をアプリが送信していないかどうかを確認します。 [チャレンジ型](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-native-authentication-challenge-types)の詳細を参照してください。

    アプリの UI でわかりやすいメッセージを使用して、メール アドレスが既に使用されていることや一部の属性が無効であることをユーザーに通知します。
- 無効な属性を示すエラーを処理するには、次のコード スニペットを使用します。

    ```kotlin
    val parameters = NativeAuthSignUpParameters(username = email)
    // Assign 'password' param if you sign in with username (email) and password
    // parameters.password = password
    parameters.attributes = userAttributes
    val actionResult: SignUpResult = authClient.signUp(parameters)
    
    if (actionResult is SignUpError && actionResult.isInvalidAttributes()) {
        val invalidAttributes = actionResult.invalidAttributes
    
        // Handle "invalid attributes" error, this time submit valid attributes
        val parameters = NativeAuthSignUpParameters(username = email)
        // Assign 'password' param if you sign in with username (email) and password
        // parameters.password = password
        parameters.attributes = userAttributes
        authClient.signUp(parameters)
    } 
    //...
    ```

#### メールのワンタイムパスコード送信で発生したエラーを処理する

`submitCode()` メソッドのエラーを処理するには、次のコード スニペットを使用します。

```kotlin
val submitCodeActionResult = nextState.submitCode(
    code = code
)
if (submitCodeActionResult is SignUpResult.Complete) {
    // Sign up flow complete, handle success state.
} else if (submitCodeActionResult is SubmitCodeError) {
    // Handle errors under SubmitCodeError
     when {
         submitCodeActionResult.isInvalidCode() -> {
             // Handle "code invalid" error
         }
         else -> {
             // Handle other errors
         }
     }
}
```

- `submitCode()` は `SubmitCodeError` を返すことができます。
- `isInvalidCode()` メソッドを使用して、送信されたコードが無効であるなどの特定のエラーを確認します。 この場合、アクションを再実行するには、前の状態参照を使用する必要があります。
- 新しいメールのワンタイム パスコードを取得するには、以下のコード スニペットを使用します。

    ```kotlin
    val submitCodeActionResult = nextState.submitCode(
        code = code
    )
    if (submitCodeActionResult is SubmitCodeError && submitCodeActionResult.isInvalidCode()) {
        // Inform the user that the submitted code was incorrect or invalid and ask for a new code to be supplied
        val newCode = retrieveNewCode()
        nextState.submitCode(
            code = newCode
        )
    }
    ```

必ず import ステートメントを含めます。 Android Studio では、自動的に import ステートメントが含められます。

アプリでユーザーをサインアップするために必要なすべての手順を完了しました。 アプリケーションをビルドして実行します。 すべてが正しく構成されている場合は、電子メールワンタイム パスコードまたは電子メールとパスワードを使用してユーザーをサインアップし、ユーザー名 (エイリアス) を含むユーザー属性を収集できる必要があります。

### 省略可能: サインアップ フロー後にサインインする

サインアップ フローが正常に完了したら、サインイン フローを開始せずにユーザーをサインインできます。 ユーザーがユーザー名 (エイリアス) でサインアップした場合は、メール アドレスまたはエイリアスを使用してサインインできます。 詳細については、「[チュートリアル: Android でサインアップ後にユーザーをサインインさせる](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-sign-in-after-sign-up)」の記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-android-sms-one-time-passcode-sign-in"} -->
## ネイティブ認証を使用して Android アプリに SMS ワンタイム パスコード MFA を追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-sms-one-time-passcode-sign-in
- Service: identity-platform / external
- Article date: 2026-01-27
- Summary: Microsoft Entra ネイティブ認証を使用して、SMS ワンタイムパスコードで多要素認証 (MFA) を Android アプリに追加する方法について説明します。

このチュートリアルでは、ネイティブ認証を使用して、SMS ワンタイム パスコード (OTP) を使用して多要素認証 (MFA) を Android アプリに追加する方法について説明します。 MFA では、サインイン時に 2 つ目の検証手順を必要とすることで、セキュリティレイヤーが追加されます。 また、認証時にセキュリティを強化し、認証コンテキストを使用して MFA を適用する方法についても説明 [します](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#authentication-context)。

このチュートリアルでは、以下の内容を学習します。

- SMS ワンタイム パスコード MFA を使用してユーザーをサインインさせる
- サインイン時に認証コンテキストを指定して MFA を適用する
- SMS ワンタイム パスコード MFA エラーによるサインインの処理

### [前提条件]

1. [「チュートリアル: ネイティブ認証を使用して Android アプリでサインインとサインアウトを追加](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-sign-in-sign-out)する」の手順を完了します。
2. テナントで MFA メソッドとして SMS を有効にする: [「MFA メソッドとして SMS を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-sms-signin#enable-the-sms-based-authentication-method)」の手順に従います。
3. SMS 実装を使用して MFA を使用したサインインを調べる場合は、開始する前に、[Code サンプル](https://github.com/Azure-Samples/ms-identity-ciam-native-auth-android-sample)をご確認ください。

### クライアント構成ファイルに MFA 機能を追加する

注

現在、SMS ワンタイムパスコードを権限形式で使用する際の既知の問題があります。 `<tenantSubdomain>.ciamlogin.com/<tenantSubdomain>.onmicrosoft.com` そのため、次の形式を使用する必要があります。 `<tenantSubdomain>.ciamlogin.com/<tenantID>`

MFA をサポートするには、Android クライアント構成を更新して、必要な MFA 機能を含めます。

```json
{
    "client_id": "Enter_the_Application_Id_Here",
    "authorities": [
    {
        "type": "CIAM",
        "authority_url": "https://Enter_the_Tenant_Subdomain_Here.ciamlogin.com/Enter_the_Tenant_Id_Here/"
    }
    ],
    "challenge_types": ["oob", "password"],
    "capabilities": ["mfa_required"],
    "logging": {
    "pii_enabled": false,
    "log_level": "INFO",
    "logcat_enabled": true
    }
}
```

### SMS ワンタイム パスコード MFA を使用してユーザーをサインインさせる

SMS ワンタイム パスコード多要素認証 (MFA) を使用してユーザーをサインインさせるには、最初の要素を収集した後、ユーザーが自分の電話番号を確認するためのワンタイム パスコードを含む SMS を送信する必要があります。 有効なワンタイム パスコードを入力すると、アプリはユーザーをサインインさせます。

SMS ワンタイム パスコード MFA を使用してユーザーをサインインさせるには、次の操作を行う必要があります。

1. 「ユーザーインターフェース (UI) を作成して、以下を行う：」

    - サインインに MFA が必要であることをユーザーに知らします (省略可能)。
    - 2 番目の認証要素を満たすために、ユーザーから SMS ワンタイム パスコードを収集します。
    - ワンタイム パスコードを再送信します (推奨)。
2. サインイン結果 `MFARequired` 処理します。

    ```kotlin
    is SignInResult.MFARequired -> {
        // Handle "mfa required" result 
        val awaitingMFAState = actionResult.nextState
    
        // Select the authentication method from authMethods, either programmatically or through the UI, and assign it to authMethod.
        val authMethod = actionResult.authMethods.first {it.challengeChannel.uppercase() == "SMS"} 
        val requestChallengeResult = awaitingMFAState.requestChallenge(authMethod)
    
        // Handle "mfa verification required" result 
        if (requestChallengeResult is MFARequiredResult.VerificationRequired) {
            // Next Step: submitChallenge using nextState
        } else {
            // Handle unexpected result as well as errors
        }
    }
    ```

    サインイン時に MFA が必要な場合、システムは `SignInResult.MFARequired`を返します。 この時点で、認証を続行するために MFA が必要であることをユーザーに通知するか、 `requestChallenge(authMethod)` メソッドを呼び出してワンタイム パスコードを送信できます。 最も一般的なシナリオ `requestChallenge(authMethod)`では、結果 `MFARequiredResult.VerificationRequired`が返されます。これは、SDK がアプリがユーザーの電話に送信された SMS ワンタイム パスコードを送信することを想定していることを示します。
3. 結果 `submitChallenge()` 処理します。

    ```kotlin
    val mfaRequiredState = requestChallengeResult.nextState
    val submitChallengeResult = mfaRequiredState.submitChallenge(code)
    
    when (submitChallengeResult) {
        is SignInResult.Complete -> {
            // Handle sign in success               
        }
        is SubmitChallengeError -> {
            // Handle "mfa submit challenge" error
        }
        else -> {
            // Handle unexpected result
        }
    }
    ```

    `MFARequiredResult.VerificationRequired` オブジェクトには、`requestChallengeResult.nextState`を使用して取得できる新しい状態参照が含まれています。 新しい状態では、MFA チャレンジの送信に使用できる `submitChallenge()` メソッドにアクセスできます。 最も一般的なシナリオでは、 `submitChallenge()` は、ユーザーが正常に認証されたことを示す結果 ( `SignInResult.Complete`) を返します。

### サインイン時に認証コンテキストを使用する

サインイン時に認証コンテキストを使用する方法については、サインイン [時の認証コンテキスト](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-email-one-time-passcode-sign-in#use-authentication-context-during-sign-in) を参照してください。サインイン時に認証コンテキストを使用する方法について説明します。

### SMS ワンタイム パスコード MFA エラーによるサインインの処理

MFA 中に発生するエラーを処理するには、チャレンジ要求とチャレンジ送信の両方に対してエラー処理ロジックを実装します。

1. `requestChallenge` メソッドでエラーを処理します。

    ```kotlin
    if (requestChallengeResult is MFARequiredResult.Error) {
        if (requestChallengeResult.isBrowserRequired()) {
            showResultText("Browser is required")
        } else {
            showResultText("Unexpected error while requesting challenge: ${requestChallengeResult.errorMessage}")
        }
    }
    ```
2. `submitChallenge` メソッドでエラーを処理します。

    ```kotlin
    if (result is SignInError) {
        if (result.isInvalidChallenge()) {
            // Inform the user that the submitted code was incorrect and ask for a new code
            // Optionally, allow resubmission using newState.submitChallenge(newCode, authMethod)
        } else {
            showResultText("Unexpected error occurred: ${result.errorMessage}")
        }
    }
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-android-sms-strong-authentication-method"} -->
## ネイティブ認証を使用して Android アプリに SMS 強力な認証方法の登録を追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-sms-strong-authentication-method
- Service: identity-platform / external
- Article date: 2026-01-27
- Summary: Microsoft Entra のネイティブ認証を使用して、Android アプリで MFA 対応ユーザーのための強力な認証方法として SMS ワンタイムパスコードを登録する方法を学びます。

このチュートリアルでは、ネイティブ認証を使用して Android アプリに SMS の強力な認証方法の登録を実装する方法について説明します。 多要素認証 (MFA) が有効なユーザーには、少なくとも 1 つの強力な認証が必須です。 現時点では、強力な認証方法として、電子メールと SMS のワンタイム パスコードのみがサポートされています。

このチュートリアルでは、以下の内容を学習します。

- MFA が有効なユーザーの強力な認証方法を登録します。
- 強力な認証方法の登録エラーを処理します。

### [前提条件]

1. [「チュートリアル: ネイティブ認証を使用して Android アプリにサインインを追加](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-sign-in-sign-out)する」の手順を完了します。
2. 顧客に MFA を適用するには、「 [MFA をアプリに追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers) 」の手順を使用して、サインイン フローに SMS MFA を追加します。 現在、ネイティブ認証では、MFA の 2 番目の要素として電子メールと SMS ワンタイム パスコードがサポートされています。
3. 強力な認証方法の登録の実装を調べる場合は、開始する前に、[Code サンプル](https://github.com/Azure-Samples/ms-identity-ciam-native-auth-android-sample)をご確認ください。

### クライアント構成ファイルに強力な認証方法の登録を追加する

注

現在、SMS ワンタイムパスコードを権限形式で使用する際の既知の問題があります。 `<tenantSubdomain>.ciamlogin.com/<tenantSubdomain>.onmicrosoft.com` そのため、次の形式を使用する必要があります。 `<tenantSubdomain>.ciamlogin.com/<tenantID>`

強力な認証方法をサポートするには、必要な登録機能を含むように Android クライアント構成を更新します。

```json
{
    "client_id": "Enter_the_Application_Id_Here",
    "authorities": [
    {
        "type": "CIAM",
        "authority_url": "https://Enter_the_Tenant_Subdomain_Here.ciamlogin.com/Enter_the_Tenant_Id_Here/"
    }
    ],
    "challenge_types": ["oob", "password"],
    "capabilities": ["registration_required"],
    "logging": {
    "pii_enabled": false,
    "log_level": "INFO",
    "logcat_enabled": true
    }
}
```

### SMS ワンタイム パスコードを強力な認証方法として登録する

MFA 対応ユーザーの SMS ワンタイム パスコードを登録するには、強力な認証方法として電話番号を指定する必要があります。 このプロセスは、MFA が初めて必要なときにのみトリガーされ、ユーザーには強力な認証方法が登録されていません。 その後、ユーザーが自分の電話番号を確認するためのワンタイム パスコードを含む SMS を送信する必要があります。 ユーザーが有効なワンタイム パスコードを入力すると、SDK はサインイン プロセスを完了します。

MFA 対応ユーザーの強力な認証方法 (SMS ワンタイム パスコード) を登録するには、次の操作を行う必要があります。

1. 「ユーザーインターフェース (UI) を作成して、以下を行う：」

    - サインインには強力な認証方法の登録が必要であることをユーザーに知らしてください (省略可能)。
    - 強力な認証方法として登録する電話番号を収集します。 ユーザーは、電話番号を指定して、強力な認証方法として登録する必要があります。
    - ユーザーから SMS ワンタイム パスコードを収集して、2 要素認証を実行します。
    - ワンタイム パスコードを再送信します (推奨)。
2. サインイン結果 `StrongAuthMethodRegistrationRequired` 処理します。

    ```kotlin
     val parameters = NativeAuthSignInParameters(username = email)
     parameters.password = password 
     val signInResult: SignInResult = authClient.signIn(parameters) 
    
     when (actionResult) {
         // Handle "strong authentication method registration required" result 
         is SignInResult.StrongAuthMethodRegistrationRequired -> {
             val authMethod = actionResult.authMethods.first {it.challengeChannel.uppercase() == "SMS"}
             // Next Step: challengeAuthMethod using nextState
         }
     }
    ```

    サインイン時に MFA が必要であり、強力な認証が登録されていない場合、システムは `SignInResult.StrongAuthMethodRegistrationRequired`返し、ユーザーに強力な認証方法として電話番号を指定するよう要求します。
3. 結果 `challengeAuthMethod()` 処理します。

    ```kotlin
    val registerStrongAuthState = signInResult.nextState
    val verificationContact = "<phone number>" // format should be "<+prefix phone_number>"
    val params = NativeAuthChallengeAuthMethodParameters(authMethod, verififcationContact)
    val challengeAuthResult = registerStrongAuthState.challengeAuthMethod(params)
    
    when (challengeAuthResult) {
        is RegisterStrongAuthChallengeResult.VerificationRequired -> {
            // Next Step: submitChallenge using nextState
        }
        is RegisterStrongAuthChallengeError -> {
            // Handle "register strong auth challenge" error
        }
        else -> {
            // Handle unexpected result
        }
    }
    ```

    `challengeAuthMethod()`メソッドは、`verificationContact`で指定された電話番号にワンタイム パスコードを送信します。 `challengeAuthMethod()` は結果 `RegisterStrongAuthChallengeResult.VerificationRequired`を返します。これは、SDK がアプリが SMS ワンタイム パスコードを電話番号に送信することを想定していることを示します。 `RegisterStrongAuthChallengeResult.VerificationRequired` オブジェクトには、`challengeAuthResult.nextState`を使用して取得できる新しい状態参照が含まれています。 新しい状態では、ユーザーの電話番号に送信された SMS ワンタイム パスコードを送信するために使用できる `submitChallenge()` メソッドにアクセスできます。
4. 結果 `submitChallenge()` 処理します。

    ```kotlin
    val registerStrongAuthVerificationRequiredState = challengeAuthResult.nextState
    val submitChallengeResult = registerStrongAuthVerificationRequiredState.submitChallenge(smsCode)
    
    when (actionResult) {
        is SignInResult.Complete -> {
            // Handle sign in success      
        }
        is RegisterStrongAuthSubmitChallengeError -> {
            // Handle "register strong auth submit challenge" error
        }
        else -> {
            // Handle unexpected result      
        }
    }
    ```

    最も一般的なシナリオでは、 `submitChallenge()` は、ユーザーが正常に認証されたことを示す結果 `SignInResult.Complete`を返します。

### 強力な認証方法の登録エラーを処理する

強力な認証方法の登録中に発生するエラーを処理するには、次のコード スニペットを使用します。

1. `challengeAuthMethod()` メソッドのエラーを処理します。

    ```kotlin
    val challengeAuthResult = registerStrongAuthState.challengeAuthMethod(params)
    
    if (challengeAuthResult is RegisterStrongAuthChallengeResult.VerificationRequired) {
        // Next Step: submit challenge
    } else if (challengeAuthResult is RegisterStrongAuthChallengeError) {
        // define error from the typed variable
        val error = challengeAuthResult
    
        // Handle errors under RegisterStrongAuthChallengeError
        when {
            error.isInvalidInput() -> {
                // Display error
            }
            error.isAuthMethodBlocked() -> {
                // Authentication method selected has been blocked. Reach out to customer support  to seek assistance.
            }
            else -> {
                // Unexpected error
            }
        }
    }
    ```

    `RegisterStrongAuthChallengeError`は、`registerStrongAuthState.challengeAuthMethod()`メソッドを使用してチャレンジを登録した後に処理されます。 `isInvalidInput()`メソッドを使用して、入力した確認連絡先が無効ななど、特定のエラーを確認します。 この場合、新しいチャレンジを送信するには、前の状態参照を使用する必要があります。
2. `submitChallenge()` メソッドのエラーを処理します。

    ```kotlin
    val submitChallengeResult = registerStrongAuthVerificationRequiredState.submitChallenge(challenge = smsCode)
    
    if (submitChallengeResult is SignInResult.Complete) {
        // Handle sign in success
    } else if (submitChallengeResult is RegisterStrongAuthSubmitChallengeError) {
        // Handle errors under RegisterStrongAuthSubmitChallengeError
        when {
            error.isInvalidChallenge() -> {
                // Display error
            }
            else -> {
                // Unexpected error
            }
        }
    }
    ```

    `RegisterStrongAuthSubmitChallengeError`は、`registerStrongAuthVerificationRequiredState.submitChallenge(challenge = smsCode)`を使用してチャレンジを送信した後に処理されます。 `isInvalidChallenge()`メソッドを使用して、送信されたチャレンジが無効ななどの特定のエラーを確認します。 この場合、チャレンジの送信を再パフォーマンスするには、前の状態参照を使用する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-android-support-web-fallback"} -->
## Android アプリでの Web フォールバックのサポート - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-support-web-fallback
- Service: identity-platform / external
- Article date: 2024-04-29
- Summary: Android アプリで Web フォールバックのサポートを実装する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、`isBrowserRequired()` エラーがどのように発生するか、また、それをどのように解決できるかを示します。 ユーティリティ メソッド `isBrowserRequired()` は、ネイティブ認証では機能的かつ安全に認証フローを完了するのに不十分であるような、さまざまなシナリオのフォールバック メカニズムの必要性をチェックしています。

このチュートリアルでは、次の操作を行います。

- `isBrowserRequired()` を確認する
- `isBrowserRequired()` を処理する

### 前提条件

- 「[ネイティブ Android モバイル アプリケーションのサンプル](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-run-native-authentication-sample-android-app)でユーザーをサインインする」の手順を完了します。 この記事では、テナント設定を使用して構成するサンプル Android を実行する方法について説明します。
- 「[チュートリアル: 電子メール ワンタイム パスコードを使用するサインインとサインアウトを追加する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-sign-in-sign-out)」の手順を完了します。

### Web フォールバック

ネイティブ認証ではユーザー認証フローを完了するのに不十分なシナリオでは、[Web フォールバック メカニズム](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-web-fallback)を使用します。

Android SDK を初期化するときは、*oob* やパスワードなど、モバイル アプリケーションでサポートされるチャレンジの種類を指定します。

クライアント アプリが Microsoft Entra に必要なチャレンジの種類をサポートできない場合、Microsoft Entra の応答は、クライアント アプリがブラウザーで認証フローを続行する必要があることを示します。 たとえば、*oob* チャレンジの種類では SDK を初期化しますが、Microsoft Entra 管理センターでは、電子メールによりパスワード認証方法でアプリを構成します。

この場合、ユーティリティ メソッド `isBrowserRequired()` は true を返します。

### サンプルの流れ

`isBrowserRequired()` が返されるフローの例と、それを処理する方法を見てみましょう。

1. 初期化中に SDK に渡す JSON 構成ファイル内に、次のコード スニペットに示すように *oob* チャレンジの種類のみを追加します。

    ```kotlin
    PublicClientApplication.createNativeAuthPublicClientApplication( 
        requireContext(), 
        R.raw.native_auth_config  // JSON configuration file 
    ) 
    ```

    `native_auth_config.json` 構成には、次のコード スニペットがあります。

    ```json
    {
      "client_id" : "{Enter_the_Application_Id_Here}",
       "authorities" : [
        {
          "type": "CIAM",
          "authority_url": "https://{Enter_the_Tenant_Subdomain_Here}.ciamlogin.com/{Enter_the_Tenant_Subdomain_Here}.onmicrosoft.com/"
        }
      ],
      "challenge_types" : ["oob"],
      "logging": {
        "pii_enabled": false,
        "log_level": "INFO",
        "logcat_enabled": true
      }
    } 
    ```
2. Microsoft Entra 管理センターで、認証方法として[パスワードと電子メール](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)を使用するように**ユーザー フローを構成**します。
3. SDKの `signUp(parameters)` 方法を使用してサインアップ フローを開始します。 Microsoft Entra は `SignUpError` と `isBrowserRequired()` のチャレンジタイプを想定していますが、あなたは *oob* のみで SDK を構成したため、 チェックを通過する を受け取ります。
4. `isBrowserRequired()` を確認して処理するには、次のコード スニペットを使用します。

    ```kotlin
    val parameters = NativeAuthSignUpParameters(username = email)
    val actionResult: SignUpResult = authClient.signUp(parameters)
    
    if (actionResult is SignUpError && actionResult.isBrowserRequired()) { 
        // Handle "browser required" error
    } 
    ```

    このコードは、ネイティブ認証 SDK を使用して認証フローを完了できないこと、またブラウザーを使用する必要があることを示します。

### isBrowserRequired() エラーを処理する

このエラーを処理するには、クライアント アプリは、ブラウザーを起動して認証フローを再起動する必要があります。 Microsoft 認証ライブラリ (MSAL) `acquireToken()` メソッドを使用して実行できます。

そのためには、次の手順を行ってください。

1. 前の手順で登録したアプリにリダイレクト URI を追加するには、「[プラットフォーム リダイレクト URL を追加する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-sign-in#add-a-redirect-uri)」の手順を使用します。
2. クライアント アプリの構成ファイルを更新するには、「 [サンプル アプリケーションの構成」](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-sign-in#configure-the-sample-application)の手順を使用します。
3. `acquireToken()` メソッドを使用してトークンを取得するには、次のコード スニペットを使用します。

    ```kotlin
    val parameters = NativeAuthSignUpParameters(username = email)
    val actionResult: SignUpResult = authClient.signUp(parameters)
    
    if (actionResult is SignUpError && actionResult.isBrowserRequired()) {
        authClient.acquireToken(
            AcquireTokenParameters(
                AcquireTokenParameters.Builder()
                    .startAuthorizationFromActivity(requireActivity())
                    .withScopes(getScopes())
                    .withCallback(getAuthInteractiveCallback())
            )
            // Result will contain account and tokens retrieved through the browser.
        )
    } 
    ```

ネイティブ認証フローを通じて取得するセキュリティ トークン (ID トークン、アクセス トークン、更新トークン) は、ブラウザーで委任されたフローを介して取得するトークンと同じです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-ios-macos-identity-provider-sign-in-sign-up"} -->
## ネイティブ認証 Web フローを使用してフェデレーション ID プロバイダーのサインインと iOS アプリへのサインアップを追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-macos-identity-provider-sign-in-sign-up
- Service: identity-platform / external
- Article date: 2026-04-10
- Summary: iOS アプリで Microsoft Entra ネイティブ認証を使用し、Web ベースの認証フローを介してフェデレーション アイデンティティ プロバイダーのサインインとサインアップを有効にする方法を学びます。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、Web フローでネイティブ認証を使用して、iOS アプリにフェデレーション ID プロバイダー (IdP) 認証を実装する方法について説明します。 フェデレーション IdP 認証を使用すると、ユーザーは Apple、Facebook、Google、カスタム OIDC プロバイダーなどのプロバイダーの既存のアカウントを使用してサインインまたはサインアップできます。

このチュートリアルでは、以下の内容を学習します。

- Web フローを使用してフェデレーション ID プロバイダーを使用してユーザーをサインインする
- Web フローを使用してフェデレーション ID プロバイダーを使用してユーザーをサインアップする

### 前提条件

1. [「チュートリアル: ネイティブ認証用に iOS/macOS アプリを準備する」の手順を完了します](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-prepare-ios-macos-app)。
2. Microsoft Entra 外部 ID テナントでフェデレーション ID プロバイダーを構成します。 Microsoft Entra 管理センターの手順に従って、目的の ID プロバイダーを追加して構成します。

    - [Apple を ID プロバイダーとして構成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-apple-federation-customers)
    - [Facebook を ID プロバイダーとして構成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-facebook-federation-customers)
    - [GOOGLE を ID プロバイダーとして構成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-google-federation-customers)
    - [カスタム OIDC プロバイダーを構成します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-oidc-federation-customers)。 カスタム OIDC 用に構成された発行者 URI のドメインを `domain_hint`として使用します。
3. アプリが Web フォールバックをサポートしていることを確認する [チュートリアル: Web フォールバックをサポート](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-macos-support-web-fallback)します。
4. Microsoft Authentication Library (MSAL) 依存関係を少なくとも `2.6.0` に追加または更新します。
5. フェデレーション IdP サインインとサインアップの実装を調べる場合は、開始する前に、[サンプル iOS アプリケーション](https://github.com/Azure-Samples/ms-identity-ciam-native-auth-ios-sample/blob/main/NativeAuthSampleApp/WebFallbackViewController.swift)をご覧ください。

### フェデレーション ID プロバイダーを使用してユーザーをサインインさせる

Web フロー経由でフェデレーション ID プロバイダーを使用してユーザーをサインインさせるには、最初に認証する ID プロバイダーと、対応する `domain_hint`を識別する必要があります。

- 前提条件の構成セクションで定義されている ID プロバイダーを使用します。
- `domain_hint` パラメーターを使用して、特定の ID プロバイダーに認証を送信します。 次のいずれかの値を選択します。

    - `"Apple"` アップル用
    - Facebook 対応 `"Facebook"`
    - `"Google"` Google の場合

ユーザーのサインインを行うには、次の操作を行う必要があります。

1. ユーザーがフェデレーション ID プロバイダーを使用してサインインできるようにするユーザー インターフェイスを作成します。 このインターフェイスは、特定の ID プロバイダーとそれに対応する `domain_hint`を識別する必要があります。
2. クライアント アプリから`domain_hint`値が識別されたら、`MSALInteractiveTokenParameters`を作成し、`domain_hint`を設定し、`acquireToken(with: parameters)`の`MSALNativeAuthPublicClientApplication`メソッドを呼び出して、次のように Social IdP を使用して Web 認証をトリガーします。

    ユーザーがサインインしている場合でも、対話型認証を強制するには、 `.login` として Prompt 型の値を使用します。

    ```swift
    let parameters = MSALInteractiveTokenParameters(scopes: ["User.Read"], webviewParameters: webviewParams)
    parameters.promptType = .login
    parameters.domainHint = domainHint
    
    nativeAuth.acquireToken(with: parameters) { [weak self] (result: MSALResult?, error: Error?) in
        guard let self = self else { return }
    
        if let error = error {
            self.showResultText("Error acquiring token: \(error)")
            return
        }
    
        self.msalAccount = result?.account
    
        guard let msalAccount = self.msalAccount else {
            self.showResultText("Could not acquire token: No result or account returned")
            return
        }
    
        self.updateUI()
    }
    ```
3. 認証に成功した後、`getNativeAuthUserAccount()`から`MSALNativeAuthPublicClientApplication`を使用して、現在キャッシュされているアカウントを取得することもできます。

    ```swift
        if let account = nativeAuth.getNativeAuthUserAccount() {
            ...
        }
    ```

### フェデレーション ID プロバイダーを使用してユーザーをサインアップする

フェデレーション ID プロバイダーを使用してユーザーをサインアップする場合、プロセスはサインインとほぼ同じですが、Prompt 値に小さな変更が加えられます。 `.create`を使用します。

```swift
    let parameters = MSALInteractiveTokenParameters(scopes: ["User.Read"], webviewParameters: webviewParams)
    parameters.promptType = .create
    parameters.domainHint = domainHint

    nativeAuth.acquireToken(with: parameters) { [weak self] (result: MSALResult?, error: Error?) in
        ...
    }
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-ios-macos-self-service-password-reset"} -->
## セルフサービスパスワードリセット - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-macos-self-service-password-reset
- Service: identity-platform / external
- Article date: 2024-08-19
- Summary: ネイティブ認証を使用して iOS/macOS アプリにセルフサービス パスワード リセット (SSPR) を実装する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、管理者やヘルプ デスクが関与することなく、ユーザーが自分のパスワードを変更またはリセットできるようになるための方法を示します。

このチュートリアルでは、次の操作を行います。

- セルフサービス パスワード リセットを追加します。
- エラーを処理します。

### 前提条件

- [セルフサービス パスワード リセットを有効にする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-enable-password-reset-customers)

### [パスワードのリセット]

既存のユーザーのパスワードをリセットするには、ワンタイム パスコード (OTP) を使用してメール アドレスを検証する必要があります。

1. メール アドレスを検証するには、次のコード スニペットを使用して、SDK インスタンスから `resetPassword(parameters:delegate)` のメソッドを呼び出します。

    ```swift
    let parameters = MSALNativeAuthResetPasswordParameters(username: email)
    nativeAuth.resetPassword(parameters: parameters, delegate: self)
    ```
2. クラスに拡張機能として `ResetPasswordStartDelegate` プロトコルを実装するには、次のコード スニペットを使用します。

    ```swift
    extension ViewController: ResetPasswordStartDelegate {
        func onResetPasswordCodeRequired(
            newState: MSAL.ResetPasswordCodeRequiredState,
            sentTo: String,
            channelTargetType: MSALNativeAuthChannelType,
            codeLength: Int
        ) {
            resultTextView.text = "Verification code sent to \(sentTo)"
        }
    
        func onResetPasswordStartError(error: MSAL.ResetPasswordStartError) {
            resultTextView.text = "Error verifying code: \(error.errorDescription ?? "no description")"
        }
    }
    ```

    `resetPassword(parameters:delegate)` を呼び出すと、`onResetPasswordCodeRequired()` または `onResetPasswordStartError()` のデリゲート メソッドが呼び出されます。

    最も一般的なシナリオでは、ユーザーのメール アドレスを検証するためのコードが送信されたことを示すために、`onResetPasswordCodeRequired(newState:sentTo:channelTargetType:codeLength)` が呼び出されます。 このデリゲート メソッドには、コードが送信された場所と、コードに含まれる桁数の詳細に加えて、`newState` 型の `ResetPasswordCodeRequiredState` パラメータもあります。これにより、次の 2 つの新しいメソッドにアクセスできます。

    - `submitCode(code:delegate)`
    - `resendCode(delegate)`

    ユーザーが指定したコードを送信するには、次のコマンドを使用します。

    ```swift
    newState.submitCode(code: userSuppliedCode, delegate: self)
    ```
3. 送信済みのコードを確認するには、まず次のコード スニペットを使用して `ResetPasswordVerifyCodeDelegate` プロトコルをクラスの拡張機能として実装します。

    ```swift
    extension ViewController: ResetPasswordVerifyCodeDelegate {
    
        func onResetPasswordVerifyCodeError(
            error: MSAL.VerifyCodeError,
            newState: MSAL.ResetPasswordCodeRequiredState?
        ) {
            resultTextView.text = "Error verifying code: \(error.errorDescription ?? "no description")"
        }
    
        func onPasswordRequired(newState: MSAL.ResetPasswordRequiredState) {
            // use newState instance to submit the new password
        }
    }
    ```

    最も一般的なシナリオでは、`onPasswordRequired(newState)` インスタンスを使用して新しいパスワードを指定できることを示す `newState` の呼び出しを受け取ります。

    ```swift
    newState.submitPassword(password: newPassword, delegate: self)
    ```
4. クラスに拡張機能として `ResetPasswordRequiredDelegate` プロトコルを実装するには、次のコード スニペットを使用します。

    ```swift
    extension ViewController: ResetPasswordRequiredDelegate {
    
        func onResetPasswordRequiredError(
            error: MSAL.PasswordRequiredError,
            newState: MSAL.ResetPasswordRequiredState?
        ) {
            resultTextView.text = "Error submitting new password: \(error.errorDescription ?? "no description")"
        }
    
        func onResetPasswordCompleted(newState: SignInAfterResetPasswordState) {
            resultTextView.text = "Password reset completed"
        }
    }
    ```

    最も一般的なシナリオでは、パスワード リセット フローが完了したことを示す `onResetPasswordCompleted(newState)` の呼び出しを受け取ります。

### エラーの処理

`ResetPasswordStartDelegate` プロトコルの以前の実装では、`onResetPasswordStartError(error)` デリゲート関数を処理したときにエラーが表示されていました。

特定のエラーの種類を次のように処理することで、ユーザー エクスペリエンスを向上させることができます。

```swift
func onResetPasswordStartError(error: MSAL.ResetPasswordStartError) {
    if error.isInvalidUsername {
        resultTextView.text = "Invalid username"
    } else if error.isUserNotFound {
        resultTextView.text = "User not found"
    } else if error.isUserDoesNotHavePassword {
        resultTextView.text = "User is not registered with a password"
    } else {
        resultTextView.text = "Error during reset password flow in: \(error.errorDescription ?? "no description")"
    }
}
```

#### 状態を活用してエラーを処理する

一部のエラーには、新しい状態への参照が含まれます。 たとえば、ユーザーが誤ったメール確認コードを入力した場合、エラー ハンドラーには `ResetPasswordCodeRequiredState` への参照が含まれており、それを新しい確認コードの送信に使用することができます。

以前に `ResetPasswordVerifyCodeDelegate` プロトコルを実装した際は、`onResetPasswordError(error:newState)` デリゲート関数を処理したときに単にエラーを表示していました。

ユーザーに正しいコードの入力を求め、次のように再送信することで、ユーザー エクスペリエンスを向上させることができます。

```swift
func onResetPasswordVerifyCodeError(
    error: MSAL.VerifyCodeError,
    newState: MSAL.ResetPasswordCodeRequiredState?
) {
    if error.isInvalidCode {
        // Inform the user that the submitted code was incorrect and ask for a new code to be supplied.
        // Request a new code calling `newState.resendCode(delegate)`
        let userSuppliedCode = retrieveNewCode(newState)
        newState?.submitCode(code: userSuppliedCode, delegate: self)
    } else {
        resultTextView.text = "Error verifying code: \(error.errorDescription ?? "no description")"
    }
}
```

エラー ハンドラーが新しい状態への参照を含む別の例は、ユーザーが無効なパスワードを入力した場合です。 この場合、エラー ハンドラーには `ResetPasswordRequiredState` への参照が含まれており、それを新しいパスワードの送信に使用することができます。 次に例を示します。

```swift
func onResetPasswordRequiredError(
    error: MSAL.PasswordRequiredError,
    newState: MSAL.ResetPasswordRequiredState?
) {
    if error.isInvalidPassword {
        // Inform the user that the submitted password was invalid and ask for a new password to be supplied.
        let newPassword = retrieveNewPassword()
        newState?.submitPassword(password: newPassword, delegate: self)
    } else {
        resultTextView.text = "Error submitting password: \(error.errorDescription ?? "no description")"
    }
}
```

#### パスワード リセット後のサインイン

SDK を使用すると、開発者はパスワードをリセットした後にユーザー名を指定せずユーザーをサインインさせ、また、ワンタイム パスコードを使用して電子メール アドレスを確認できます。

パスワードのリセットが成功した後にユーザーをサインインさせるには、 `signIn(parameters:delegate)` 関数で返される新しい状態 `SignInAfterResetPasswordState` の `onResetPasswordCompleted(newState)` メソッドを使用します。

```swift
extension ViewController: ResetPasswordRequiredDelegate {

    func onResetPasswordRequiredError(
        error: MSAL.PasswordRequiredError,
        newState: MSAL.ResetPasswordRequiredState?
    ) {
        resultTextView.text = "Error submitting new password: \(error.errorDescription ?? "no description")"
    }

    func onResetPasswordCompleted() {
        resultTextView.text = "Password reset completed"
        let parameters = MSALNativeAuthSignInAfterResetPasswordParameters()
        newState.signIn(parameters: parameters, delegate: self)
    }
}
```

`signIn(parameters:delegate)` はデリゲート パラメータを受け取るため、`SignInAfterResetPasswordDelegate` プロトコルで必要なメソッドを実装する必要があります。

最も一般的なシナリオでは、ユーザーがサインインしたことを示す `onSignInCompleted(result)` の呼び出しを受け取ります。 結果を使用して、`access token` を取得できます。

```swift
extension ViewController: SignInAfterSignUpDelegate {
    func onSignInAfterSignUpError(error: SignInAfterSignUpError) {
        resultTextView.text = "Error signing in after password reset"
    }

    func onSignInCompleted(result: MSAL.MSALNativeAuthUserAccountResult) {
        // User successfully signed in
        let parameters = MSALNativeAuthGetAccessTokenParameters()
        result.getAccessToken(parameters: parameters, delegate: self)
    }
}
```

`getAccessToken(parameters:delegate)` はデリゲート パラメータを受け取るため、`CredentialsDelegate` プロトコルで必要なメソッドを実装する必要があります。

最も一般的なシナリオでは、ユーザーが `onAccessTokenRetrieveCompleted(result)` を取得したことを示す `access token` の呼び出しを受け取ります。

```swift
extension ViewController: CredentialsDelegate {
    func onAccessTokenRetrieveError(error: MSAL.RetrieveAccessTokenError) {
        resultTextView.text = "Error retrieving access token"
    }

    func onAccessTokenRetrieveCompleted(result: MSALNativeAuthTokenResult) {
        resultTextView.text = "Signed in. Access Token: \(result.accessToken)"
    }
}

```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-ios-macos-sign-in-sign-out"} -->
## ネイティブ iOS アプリでサインインとサインアウトを追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-macos-sign-in-sign-out
- Service: identity-platform / external
- Article date: 2026-03-10
- Summary: ネイティブ認証を使用して iOS/macOS アプリでメールのワンタイム パスコードまたはユーザー名とパスワードでのサインインとサインアウトを追加する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ネイティブ認証を使用して、iOS アプリでメールのワンタイム パスコードまたはユーザー名とパスワードを使用してユーザーをサインインおよびサインアウトする方法を説明します。

このチュートリアルでは、次の操作を行います。

- メールのワンタイム パスコードまたはユーザー名 (メール アドレス) とパスワードを使ってユーザーのサインインを行います。
- ユーザーのサインアウト。
- サインイン エラーを処理する

### [前提条件]

- [チュートリアル: ネイティブ認証用に iOS アプリを準備します](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-prepare-ios-macos-app)。
- **パスワード付きメール**を使用してサインインする場合は、**サインアップおよびサインインのユーザー フローを作成するときに**、[パスワード付きメール](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)を使用するようにユーザー フローを構成します。

### ユーザーのサインイン

**メールのワンタイム パスコード** フローを使用してユーザーをサインインさせるには、メールをキャプチャし、ユーザーのメールを検証するためのワンタイム パスコードを含むメールを送信します。 有効なワンタイム パスコードを入力すると、アプリはユーザーをサインインさせます。

**パスワード付きメール** フローを使用してユーザーをサインインさせるには、メール アドレスとパスワードをキャプチャします。 ユーザー名とパスワードが有効な場合、アプリによってユーザーのサインインが行われます。

テナントのサインアップ ユーザー フローで **Username** 組み込みユーザー属性が有効になっている場合、サインインの `username` パラメーターは、ユーザーのメール アドレスまたはユーザー名 (エイリアス) を受け入れます。 エイリアス ベースのサインインをサポートするために、個別のサインイン ロジックは必要ありません。

ユーザーのサインインを行うには、次の操作を行う必要があります。

1. 「ユーザーインターフェース (UI) を作成して、以下を行う：」

    - ユーザーからメール アドレスを収集します。 入力に検証を追加して、ユーザーが有効なメール アドレスを入力していることを確認します。
    - ユーザー名 (メール アドレス) とパスワードを使ってサインインを行う場合は、パスワードを収集します。
    - 電子メール ワンタイム パスコードでサインインする場合は、ユーザーから電子メールのワンタイム パスコードを収集します。
    - メール ワンタイム パスコードでサインインした場合に、ユーザーがワンタイム パスコードを再送信できるようにするボタンを追加します。
2. UI で、次のコード スニペットに示すように select イベントによってサインインを開始するボタンを追加します。

    ```swift
        @IBAction func signInPressed(_: Any) {
        guard let email = emailTextField.text else {
            resultTextView.text = "email not set"
            return
        }
    
        let parameters = MSALNativeAuthSignInParameters(username: email)
        nativeAuth.signIn(parameters: parameters, delegate: self)
    }
    ```

    **メール ワンタイム パスコード** フローを使用してユーザーをサインインさせるには、次のコード スニペットを使用します。

    ```swift
    nativeAuth.signIn(parameters: parameters, delegate: self)
    ```

    渡されたデリゲート オブジェクトのメソッドの 1 つを呼び出すことによって非同期的に応答する `signIn(parameters:delegate)` メソッドは、`SignInStartDelegate` プロトコルを実装する必要があります。 ユーザーが電子メール送信フォームで提供する電子メール アドレスを含む `MSALNativeAuthSignInParameters` のインスタンスを渡し、`self` を代理人として渡します。

    **パスワード付きメール** フローを使用してユーザーをサインインさせるには、次のコード スニペットを使用します。

    ```swift
    let parameters = MSALNativeAuthSignInParameters(username: email)
    parameters.password = password
    nativeAuth.signIn(parameters: parameters, delegate: self)
    ```

    `signIn(parameters:delegate)` メソッドでは、ユーザーが指定した電子メール アドレスとそのパスワードを含む `MSALNativeAuthSignInParameters` のインスタンスを、`SignInStartDelegate` プロトコルに準拠するデリゲート オブジェクトと共に渡します。 この例では、`self` を渡します。
3. `SignInStartDelegate` プロトコルを実装するには、**メール一時パスコード** フローを使用するときに、次のコードスニペットを使用します。

    ```swift
    extension ViewController: SignInStartDelegate {
        func onSignInStartError(error: MSAL.SignInStartError) {
            resultTextView.text = "Error signing in: \(error.errorDescription ?? "no description")"
        }
    
        func onSignInCodeRequired(
            newState: MSAL.SignInCodeRequiredState,
            sentTo: String,
            channelTargetType: MSAL.MSALNativeAuthChannelType,
            codeLength: Int
        ) {
            resultTextView.text = "Verification code sent to \(sentTo)"
        }
    }
    ```

    `signIn(parameters:delegate)` によってデリゲート メソッドが呼び出されます。 最も一般的なシナリオでは、ユーザーのメール アドレスを検証するためのコードが送信されたことを示すために、`onSignInCodeRequired(newState:sentTo:channelTargetType:codeLength)` が呼び出されます。 このデリゲート メソッドには、コードが送信された場所と、コードに含まれる桁数の詳細に加えて、`newState` 型の `SignInCodeRequiredState` パラメータもあります。このパラメータにより、次の 2 つの新しいメソッドにアクセスできます。

    - `submitCode(code:delegate)`
    - `resendCode(delegate)`

    `submitCode(code:delegate)` を使用して、ユーザーがワンタイム パスコード フォームに指定するワンタイム パスコードを送信するには、次のコード スニペットを使用します。

    ```swift
    newState.submitCode(code: userSuppliedCode, delegate: self)
    ```

    `submitCode(code:delegate)` は、ワンタイム パスコードとデリゲート パラメータを受け付けます。 コードを送信した後、`SignInVerifyCodeDelegate` プロトコルを実装してワンタイム パスコードを検証する必要があります。

    `SignInVerifyCodeDelegate` プロトコルをクラスの拡張機能として実装するには、次のコード スニペットを使用します。

    ```swift
    extension ViewController: SignInVerifyCodeDelegate {
        func onSignInVerifyCodeError(error: MSAL.VerifyCodeError, newState: MSAL.SignInCodeRequiredState?) {
            resultTextView.text = "Error verifying code: \(error.errorDescription ?? "no description")"
        }
    
        func onSignInCompleted(result: MSALNativeAuthUserAccountResult) {
            resultTextView.text = "Signed in successfully."
            let parameters = MSALNativeAuthGetAccessTokenParameters()
            result.getAccessToken(parameters: parameters, delegate: self)
        }
    }
    ```

    最も一般的なシナリオでは、ユーザーがサインインしたことを示す `onSignInCompleted(result)` の呼び出しを受け取ります。 結果を使用して、`access token` を取得できます。

    `getAccessToken(parameters:delegate)` は、`MSALNativeAuthGetAccessTokenParameters` インスタンスとデリゲート パラメーターを受け取り、`CredentialsDelegate` プロトコルで必要なメソッドを実装する必要があります。

    最も一般的なシナリオでは、ユーザーが `onAccessTokenRetrieveCompleted(result)` を取得したことを示す `access token` の呼び出しを受け取ります。

    ```swift
    extension ViewController: CredentialsDelegate {
        func onAccessTokenRetrieveError(error: MSAL.RetrieveAccessTokenError) {
            resultTextView.text = "Error retrieving access token"
        }
    
        func onAccessTokenRetrieveCompleted(result: MSALNativeAuthTokenResult) {
            resultTextView.text = "Signed in. Access Token: \(result.accessToken)"
        }
    }
    
    ```
4. `SignInStartDelegate` フローを使用する際に  プロトコルを実装するには、次のコードスニペットを使用します。

    ```swift
    extension ViewController: SignInStartDelegate {
        func onSignInStartError(error: MSAL.SignInStartError) {
            resultTextView.text = "Error signing in: \(error.errorDescription ?? "no description")"
        }
    
        func onSignInCompleted(result: MSAL.MSALNativeAuthUserAccountResult) {
            // User successfully signed in
        }
    }
    ```

    最も一般的なシナリオでは、ユーザーがサインインしたことを示す `onSignInCompleted(result)` の呼び出しを受け取ります。 結果を使用して、`access token` を取得できます。

    `getAccessToken(parameters:delegate)` は、`MSALNativeAuthGetAccessTokenParameters` インスタンスとデリゲート パラメーターを受け取り、`CredentialsDelegate` プロトコルで必要なメソッドを実装する必要があります。

    最も一般的なシナリオでは、ユーザーが `onAccessTokenRetrieveCompleted(result)` を取得したことを示す `access token` の呼び出しを受け取ります。

    ```swift
    extension ViewController: CredentialsDelegate {
        func onAccessTokenRetrieveError(error: MSAL.RetrieveAccessTokenError) {
            resultTextView.text = "Error retrieving access token"
        }
    
        func onAccessTokenRetrieveCompleted(result: MSALNativeAuthTokenResult) {
            resultTextView.text = "Signed in. Access Token: \(result.accessToken)"
        }
    }
    
    ```

### 更新トークンにアクセスする

コンパニオン デバイスのサポート (Apple Watch など) などのシナリオでは、アプリでアクセス トークンを個別に更新するために信頼できるコンポーネントが必要になる場合があります。

既定では、MSAL は更新トークンを内部的に管理し、アプリケーション コードには返しません。 シナリオで必要な場合は、サインイン後 **に更新トークンを明示的に要求** できます。

Important

更新トークンは有効期間の長い資格情報です。 更新トークンは、アプリで必要な場合にのみ要求し、安全に保存して送信します。

#### 更新トークンを要求する (オプトイン)

MSAL では、デフォルトで更新トークンの返却が無効になります。 更新トークンを要求するには、トークンを取得するときに `returnRefreshToken` を `true` に設定します。

```swift
let parameters = MSALNativeAuthGetAccessTokenParameters()
parameters.returnRefreshToken = true

result.getAccessToken(parameters: parameters, delegate: self)
```

`returnRefreshToken`を `true` に設定しない場合、MSAL は更新トークンを返しません。

#### 更新トークンを読み取る

トークンの取得が完了したら、トークンの結果から更新トークンを読み取ります。

```swift
func onAccessTokenRetrieveCompleted(result: MSALNativeAuthTokenResult) {
    let accessToken = result.accessToken
    let refreshToken = result.refreshToken
}
```

#### セキュリティ ガイダンス

更新トークン アクセスを有効にする場合は、次のガイドラインに従います。

- 更新トークンは、プラットフォームで保護された暗号化されたストレージに格納します。
- 更新トークンのログ記録やエクスポートは避けてください。
- デバイス間での更新トークンのコピーを最小限に抑えます。
- ユーザーがサインアウトしたときに、すべてのデバイスから更新トークンを削除します。

#### サインイン エラーを処理する

サインイン中、すべてのアクションが成功するわけではありません。 たとえば、ユーザーが存在しないメール アドレスでサインインしようとしたり、無効なコードを送信したりすることがあります。

1. `signIn(parameters:delegate)` メソッドのエラーを処理するには、次のコード スニペットを使用します。

    ```swift
    func onSignInStartError(error: MSAL.SignInStartError) {
        if error.isUserNotFound || error.isInvalidUsername {
            resultTextView.text = "Invalid username"
        } else {
            resultTextView.text = "Error signing in: \(error.errorDescription ?? "no description")"
        }
    }
    ```
2. `submitCode()` メソッドでエラーを処理するには、次のコード スニペットを使用します:

    ```swift
    func onSignInVerifyCodeError(error: MSAL.VerifyCodeError, newState: MSAL.SignInCodeRequiredState?) {
        if error.isInvalidCode {
            // Inform the user that the submitted code was incorrect and ask for a new code to be supplied
            let userSuppliedCode = retrieveNewCode()
            newState?.submitCode(code: userSuppliedCode, delegate: self)
        } else {
            resultTextView.text = "Error verifying code: \(error.errorDescription ?? "no description")"
        }
    }
    ```

    ユーザーが誤ったメール確認コードを入力した場合、エラー ハンドラーには `SignInCodeRequiredState` への参照が含まれており、それを更新されたコードの送信に使用することができます。 以前に `SignInVerifyCodeDelegate` プロトコルを実装した際は、`onSignInVerifyCodeError(error:newState)` デリゲート関数を処理したときに単にエラーを表示していました。

#### ID トークン要求を読み取る

アプリが ID トークンを獲得したら、現在のアカウントに関連付けられている要求を取得できます。 これを行うには、次のコード スニペットを使用します。

```swift
func onSignInCompleted(result: MSAL.MSALNativeAuthUserAccountResult) {
   let claims = result.account.accountClaims
   let preferredUsername = claims?["preferred_username"] as? String
}
```

要求値へのアクセスに使用するキーは、ユーザー属性をトークン要求として追加するときに指定する名前です。

組み込みおよびカスタム属性をトークン要求として追加する方法については、「[トークン要求にユーザー属性を追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-add-attributes-to-token)」記事を参照してください。

### ユーザーをサインアウトする

ユーザーをサインアウトさせるには、`MSALNativeAuthUserAccountResult` コールバックで受け取った `onSignInCompleted` への参照を使用するか、`getNativeAuthUserAccount()` を使用して、サインインしているアカウントをキャッシュから取得し、`accountResult` メンバー変数に参照を格納します。

1. [こちら](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-ios-swift-prepare-app?pivots=workforce#configure-xcode-project-settings)の説明に従って、プロジェクトのキーチェーン グループを構成します。
2. `ViewController` クラスに新しいメンバー変数 `var accountResult: MSALNativeAuthUserAccountResult?` を追加します。
3. `viewDidLoad` が正常に初期化された後に次の行を追加して、キャッシュされたアカウントを取得するように `nativeAuth` を更新します: `accountResult = nativeAuth.getNativeAuthUserAccount()`
4. アカウントの結果を格納するように `signInCompleted` ハンドラーを更新します。

    ```swift
    func onSignInCompleted(result: MSALNativeAuthUserAccountResult) {
        resultTextView.text = "Signed in successfully"
    
        accountResult = result
    }
    ```
5. サインアウト ボタンを追加し、次のコードを使用してユーザーをサインアウトさせます。

    ```swift
    @IBAction func signOutPressed(_: Any) {
        guard let accountResult = accountResult else {
            print("Not currently signed in")
            return
        }
    
        accountResult.signOut()
    
        self.accountResult = nil
    
        resultTextView.text = "Signed out"
    }
    ```

アプリでユーザーをサインアウトさせるために必要なすべての手順を正常に完了しました。 アプリケーションをビルドして実行します。 問題がなければ、サインアウト ボタンを選択して正常にサインアウトできます。

### エイリアスまたはユーザー名を使用してサインインを有効にする

メール アドレスとパスワードを使用してサインインするユーザーも、ユーザー名とパスワードでサインインできます。 代替サインイン識別子とも呼ばれるユーザー名には、顧客 ID、アカウント番号、またはユーザー名として使用する別の識別子を指定できます。

Microsoft Entra 管理センターを使用してユーザー アカウントにユーザー名を手動で割り当てたり、Microsoft Graph API を使用してアプリで自動化したりできます。

[「エイリアスまたはユーザー名](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias)を使用してサインインする」の記事の手順を使用して、ユーザーがアプリケーションでユーザー名を使用してサインインできるようにします。

1. [サインインでユーザー名を有効にします](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#enable-username-in-sign-in-identifier-policy)。
2. [管理センターでユーザー名を持つユーザーを作成するか、ユーザー名](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username) を [追加して既存のユーザーを更新します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username)。 または、 [Microsoft Graph API を使用して、アプリでのユーザーの作成と更新を自動化](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username)することもできます。

### カスタム クレーム プロバイダーの構成

外部システムからのクレームをアプリに発行されるトークンに追加する場合は、[カスタム クレーム プロバイダー](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-overview)を使用します。 カスタム クレーム プロバイダーは、外部システムからクレームを取り込むために、外部 REST API を呼び出すカスタム認証拡張機能で構成されています。

[カスタム クレーム プロバイダーの構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-configuration)の手順に従って、外部システムからのクレームをセキュリティ トークンに追加します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-ios-macos-sign-in-user-after-sign-up"} -->
## iOS アプリでのサインアップ後にユーザーを自動的にサインインさせる - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-macos-sign-in-user-after-sign-up
- Service: identity-platform / external
- Article date: 2024-09-02
- Summary: ネイティブ認証を使用して iOS アプリでサインアップした後、ユーザーを自動的にサインインさせる方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ネイティブ認証を使って iOS アプリにサインアップした後で、ユーザーを自動的にサインインさせる方法について説明します。

このチュートリアルでは、次の操作を行います。

- サインアップ後にサインインします。
- エラーを処理します。

### [前提条件]

- iOS を使用している場合は、「[ネイティブ認証を使用してサンプル iOS (Swift) モバイル アプリでユーザーをサインインする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-run-native-authentication-sample-ios-app)」 の手順に従います。 macOS を使用している場合は、「[ネイティブ認証を使用してサンプル macOS (Swift) アプリでユーザーをサインインする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-run-native-authentication-sample-macos-app)」の手順に従ってください。 これらの記事では、テナント設定を使用して構成するサンプルアプリを実行する方法を紹介します。
- [チュートリアル: ネイティブ認証を使用して iOS アプリにサインアップを追加する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-macos-sign-up)。 このチュートリアルの手順は、サインアップにメール アドレスとパスワードを使用するか、メールのワンタイム パスコードを使用するかに関係なく機能します。

### サインアップ後にサインインする

`Sign in after sign up` は、サインイン ユーザー フローの拡張機能であり、正常にサインアップした後に自動的にサインインできるようになります。 SDK を使用すると、開発者はサインアップ後にユーザー名を指定せずにユーザーをサインインさせたり、ワンタイム パスコードを使用してメール アドレスを確認したりできます。

サインアップが成功した後にユーザーをサインインさせるには、`signIn(delegate)` で返された新しい状態 `SignInAfterSignUpState` の `onSignUpCompleted(newState)` メソッドを使用します。

```swift
extension ViewController: SignUpVerifyCodeDelegate {
    func onSignUpVerifyCodeError(error: MSAL.VerifyCodeError, newState: MSAL.SignUpCodeRequiredState?) {
        resultTextView.text = "Error verifying code: \(error.errorDescription ?? "no description")"
    }

    func onSignUpCompleted(newState: SignInAfterSignUpState) {
        resultTextView.text = "Signed up successfully!"
        let parameters = MSALNativeAuthSignInAfterSignUpParameters()
        newState.signIn(parameters: parameters, delegate: self)
    }
}
```

`signIn(parameters:delegate)` は、`MSALNativeAuthSignInAfterSignUpParameters` インスタンスとデリゲート パラメーターを受け取り、`SignInAfterSignUpDelegate` プロトコルで必要なメソッドを実装する必要があります。

最も一般的なシナリオでは、ユーザーがサインインしたことを示す `onSignInCompleted(result)` の呼び出しを受け取ります。 結果を使用して、`access token` を取得できます。

```swift
extension ViewController: SignInAfterSignUpDelegate {
    func onSignInAfterSignUpError(error: SignInAfterSignUpError) {
        resultTextView.text = "Error signing in after sign up"
    }

    func onSignInCompleted(result: MSAL.MSALNativeAuthUserAccountResult) {
        // User successfully signed in
        let parameters = MSALNativeAuthGetAccessTokenParameters()
        result.getAccessToken(parameters: parameters, delegate: self)
    }
}
```

`getAccessToken(parameters:delegate)` は、`MSALNativeAuthGetAccessTokenParameters` インスタンスとデリゲート パラメーターを受け取り、`CredentialsDelegate` プロトコルで必要なメソッドを実装する必要があります。

最も一般的なシナリオでは、ユーザーが `onAccessTokenRetrieveCompleted(result)` を取得したことを示す `access token` の呼び出しを受け取ります。

```swift
extension ViewController: CredentialsDelegate {
    func onAccessTokenRetrieveError(error: MSAL.RetrieveAccessTokenError) {
        resultTextView.text = "Error retrieving access token"
    }

    func onAccessTokenRetrieveCompleted(result: MSALNativeAuthTokenResult) {
        resultTextView.text = "Signed in. Access Token: \(result.accessToken)"
    }
}

```

### カスタム クレーム プロバイダーの構成

外部システムからのクレームをアプリに発行されるトークンに追加する場合は、[カスタム クレーム プロバイダー](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-overview)を使用します。 カスタム クレーム プロバイダーは、外部システムからクレームを取り込むために、外部 REST API を呼び出すカスタム認証拡張機能で構成されています。

[カスタム クレーム プロバイダーの構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-configuration)の手順に従って、外部システムからのクレームをセキュリティ トークンに追加します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-ios-macos-sign-up"} -->
## ネイティブ認証を使用して iOS/macOS アプリにサインアップを追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-macos-sign-up
- Service: identity-platform / external
- Article date: 2026-06-17
- Summary: ネイティブ認証を使用して、電子メールワンタイム パスコードまたは電子メールとパスワードを使用してユーザーをサインアップし、iOS/macOS アプリでユーザー属性 (エイリアス) を収集する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ネイティブ認証を使用して、iOS/macOS アプリで電子メール ワンタイム パスコードまたはユーザー名 (電子メール) とパスワードを使用してユーザーをサインアップする方法について説明します。 また、サインアップ時にユーザー属性 (ユーザー名 (エイリアス) を含む) を収集し、エラーを処理する方法についても説明します。

このチュートリアルでは、次の操作を行います。

- メールのワンタイム パスコードまたはメール アドレスとパスワードの組み合わせを使用してユーザーを登録します。
- ユーザー名 (エイリアス) など、サインアップ時にユーザー属性を収集します。
- サインアップ エラーを処理します。

### [前提条件]

- [「チュートリアル: ネイティブ認証用に iOS/macOS アプリを準備する」の手順を完了します](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-prepare-ios-macos-app)。
- サインアップ中にユーザ属性を収集する場合は、「[サインアップとサインインのユーザー フローを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)ときにユーザー属性を構成します。
- サインアップ時にユーザー名 (エイリアス) を収集するには、テナントのサインアップ ユーザー フローで **Username** 組み込みユーザー属性を有効にします。

### ユーザーをサインアップする

電子メールワンタイム パスコードまたはユーザー名 (電子メール) とパスワードを使用してユーザーをサインアップするには、ユーザーからメールを収集し、電子メールワンタイム パスコードを含む電子メールをユーザーに送信します。 ユーザーは、有効なメールのワンタイム パスコードを入力し、ユーザー名を検証します。

ユーザーをサインアップするには、次の操作を行う必要があります。

1. 「ユーザーインターフェース (UI) を作成して、以下を行う：」

    - ユーザーからメール アドレスを収集します。 ユーザーが有効なメール アドレスを入力していることを確認するために、入力に検証を追加します。
    - ユーザー名 (メール アドレス) とパスワードでサインアップした場合、パスワードを収集します。
    - アプリでエイリアス ベースのサインインがサポートされている場合は、ユーザー名 (エイリアス) を収集します。
    - ユーザーからメールのワンタイム パスコードを収集します。
    - 必要に応じて、ユーザー属性を収集します。
    - ユーザーがコードを受信しなかった場合にワンタイム パスコードを再送信します。
    - サインアップ フローを開始します。
2. アプリで、select イベントによって次のコード スニペットをトリガーするボタンを追加します。

    ```swift
    @IBAction func signUpPressed(_: Any) {
        guard let email = emailTextField.text else {
            resultTextView.text = "Email or password not set"
            return
        }
    
        let parameters = MSALNativeAuthSignUpParameters(username: email)
        nativeAuth.signUp(parameters: parameters, delegate: self)
    }
    ```

    - **電子メール ワンタイム パスコード**を使用してユーザーをサインアップするには、ライブラリの `signUp(parameters:delegate)` メソッドを使用します。このメソッドは、渡されたデリゲート オブジェクトでメソッドの 1 つを呼び出すことによって非同期的に応答します。このメソッドは、`SignUpStartDelegate` プロトコルを実装する必要があります。 次のコード行は、ユーザーのサインアップ プロセスを開始します。

        ```swift
        nativeAuth.signUp(parameters: parameters, delegate: self)
        ```

        `signUp(parameters:delegate)` メソッドで、送信フォームからユーザーの電子メール アドレスを含む`MSALNativeAuthSignUpParameters` インスタンスをデリゲート (`SignUpStartDelegate` プロトコルを実装するクラス) と共に渡します。
    - **メール アドレスとパスワードの組み合わせ**を使用してユーザーをサインアップさせるには、次のコード スニペットを使用します。

        ```swift
        @IBAction func signUpPressed(_: Any) {
            guard let email = emailTextField.text, let password = passwordTextField.text else {
               resultTextView.text = "Email or password not set"
               return
            }
        
            let parameters = MSALNativeAuthSignUpParameters(username: email)
            parameters.password = password
            nativeAuth.signUp(parameters: parameters, delegate: self)
        }
        ```

        ライブラリの `signUp(parameters:delegate)` メソッドは、渡されたデリゲート オブジェクトのメソッドのいずれかを呼び出すことによって非同期的に応答します。このメソッドは、 `SignUpStartDelegate` プロトコルを実装する必要があります。 次のコード行は、ユーザーのサインアップ プロセスを開始します。

        ```swift
        nativeAuth.signUp(parameters: parameters, delegate: self)
        ```

        `signUp(parameters:delegate)` メソッドで、ユーザーの電子メール アドレスとそのパスワードを含む`MSALNativeAuthSignUpParameters` インスタンスをデリゲート (`SignUpStartDelegate` プロトコルを実装するクラス) と共に渡します。
    - クラスの拡張機能として `SignUpStartDelegate` プロトコルを実装するには、次のコマンドを使用します。

        ```swift
        extension ViewController: SignUpStartDelegate {
            func onSignUpStartError(error: MSAL.SignUpStartError) {
                resultTextView.text = "Error signing up: \(error.errorDescription ?? "no description")"
            }
        
            func onSignUpCodeRequired(
                newState: MSAL.SignUpCodeRequiredState,
                sentTo: String,
                channelTargetType: MSAL.MSALNativeAuthChannelType,
                codeLength: Int
            ) {
                resultTextView.text = "Verification code sent to \(sentTo)"
            }
        }
        ```

        `signUp(parameters:delegate)` を呼び出すと、`onSignUpCodeRequired()` または `onSignUpStartError()` のデリゲート メソッドが呼び出されます。 ユーザーのメール アドレスを確認するコードが送信されたことを示すために、`onSignUpCodeRequired(newState:sentTo:channelTargetType:codeLength)` が呼び出されます。 コードが送信された場所と、コードに含まれる桁数の詳細に加えて、このデリゲート メソッドには、`newState`型の`SignUpCodeRequiredState` パラメーターもあります。これにより、2 つの新しいメソッドにアクセスできます。

        - `submitCode(code:delegate)`
        - `resendCode(delegate)`

        ユーザーが指定したコードを送信するには、次のコードを使用します。

        ```swift
        newState.submitCode(code: userSuppliedCode, delegate: self)
        ```

        - クラスの拡張機能として `SignUpVerifyCodeDelegate` プロトコルを実装するには、次のコマンドを使用します。

            ```swift
            extension ViewController: SignUpVerifyCodeDelegate {
                func onSignUpVerifyCodeError(error: MSAL.VerifyCodeError, newState: MSAL.SignUpCodeRequiredState?) {
                    resultTextView.text = "Error verifying code: \(error.errorDescription ?? "no description")"
                }
            
                func onSignUpCompleted(newState: SignInAfterSignUpState) {
                    resultTextView.text = "Signed up successfully!"
                }
            }
            ```

            `submitCode(code:delegate)`はデリゲート パラメーターを受け取り、`SignUpVerifyCodeDelegate` プロトコルで必要なメソッドを実装する必要があります。 最も一般的なシナリオでは、ユーザーがサインアップされ、フローが完了したことを示す `onSignUpCompleted(newState)` の呼び出しを受け取ります。

### サインアップ中にユーザー属性を収集する

ユーザーのサインアップにメールのワンタイム パスコードを使用するか、ユーザー名 (メール アドレス) とパスワードの組み合わせを使用するかに関係なく、ユーザーのアカウントが作成される前にユーザー属性を収集できます。 `signUp(parameters:delegate)` メソッドは、属性プロパティを持つ `MSALNativeAuthSignUpParameters` を使用して呼び出すことができます。

1. ユーザー属性を収集するには、次のコード スニペットを使用します。

    ```swift
    let attributes = [
        "country": "United States",
        "city": "Redmond"
    ]
    
    let parameters = MSALNativeAuthSignUpParameters(username: email)
    parameters.password = password
    parameters.attributes = attributes
    nativeAuth.signUp(parameters: parameters, delegate: self)
    ```

    `signUp(parameters:delegate)`は、`onSignUpCodeRequired()` メソッドまたは `onSignUpStartError()` デリゲート メソッドの呼び出し、またはデリゲートに実装されている場合は `onSignUpAttributesInvalid(attributeNames: [String])` の呼び出しになります。
2. `SignUpStartDelegate` プロトコルをクラスの拡張機能として実装するには、次のコード スニペットを使用します。

    ```swift
    extension ViewController: SignUpStartDelegate {
        func onSignUpStartError(error: MSAL.SignUpStartError) {
            resultTextView.text = "Error signing up: \(error.errorDescription ?? "no description")"
        }
    
        func onSignUpCodeRequired(
            newState: MSAL.SignUpCodeRequiredState,
            sentTo: String,
            channelTargetType: MSAL.MSALNativeAuthChannelType,
            codeLength: Int
        ) {
            resultTextView.text = "Verification code sent to \(sentTo)"
        }
    
        func onSignUpAttributesInvalid(attributeNames: [String]) {
           resultTextView.text = "Invalid attributes  \(attributeNames)"
        }
    }
    ```

    属性が無効の場合は、メソッド `onSignUpAttributesInvalid(attributeNames: [String])` が呼び出されます。 この場合は、無効な属性の一覧をユーザーに表示します。 それ以外の場合は、ユーザーのメール アドレスを確認するコードが送信されたことを示すために、`onSignUpCodeRequired(newState:sentTo:channelTargetType:codeLength)` が呼び出されます。 コードの受信者やコードの桁数などの詳細とは別に、このデリゲート メソッドには `newState` 型の`SignUpCodeRequiredState` パラメーターがあり、2 つの新しいメソッドにアクセスできます。

    - `submitCode(code:delegate)`
    - `resendCode(delegate)`

#### 1つ以上のページにわたるユーザー属性

属性を 1 つ以上のページに分散するには、顧客 ID およびアクセス管理 (CIAM) テナント構成で、異なるページに収集する属性を必須として設定します。

`signUp(parameters:delegate)` インスタンスに属性を渡さずに`MSALNativeAuthSignUpParameters`を呼び出します。 次の手順では、 `newState.submitCode(code: userSuppliedCode, delegate: self)` を呼び出してユーザーの電子メールを確認します。

`SignUpVerifyCodeDelegate` プロトコルを以前と同様にクラスの拡張機能として実装しますが、今回は、必要なメソッドに加えて、省略可能なメソッド`onSignUpAttributesRequired(attributes:newState)`を実装する必要があります。

```swift
extension ViewController: SignUpVerifyCodeDelegate {
    func onSignUpAttributesRequired(newState: SignUpAttributesRequiredState) {
        resultTextView.text = "Attributes required"
    }

    func onSignUpVerifyCodeError(error: MSAL.VerifyCodeError, newState: MSAL.SignUpCodeRequiredState?) {
        resultTextView.text = "Error verifying code: \(error.errorDescription ?? "no description")"
    }

    func onSignUpCompleted(newState: SignInAfterSignUpState) {
        resultTextView.text = "Signed up successfully!"
    }
}
```

このデリゲート メソッドには、`newState` 型の`SignUpAttributesRequiredState` パラメーターがあり、新しいメソッドにアクセスできます。

- `submitAttributes(attributes:delegate)`

ユーザーが指定した属性を送信するには、次のコード スニペットを使用します。

```swift
let attributes = [
    "country": "United States",
    "city": "Redmond"
]

newState.submitAttributes(attributes: attributes, delegate: self)
```

また、クラスの拡張機能として `SignUpAttributesRequiredDelegate` プロトコルを実装します。

```swift
extension ViewController: SignUpAttributesRequiredDelegate {
    func onSignUpAttributesRequiredError(error: AttributesRequiredError) {
        resultTextView.text = "Error submitting attributes: \(error.errorDescription ?? "no description")"
    }

    func onSignUpAttributesRequired(attributes: [MSALNativeAuthRequiredAttribute], newState: SignUpAttributesRequiredState) {
        resultTextView.text = "Attributes required"
    }

    func onSignUpAttributesInvalid(attributeNames: [String], newState: SignUpAttributesRequiredState) {
        resultTextView.text = "Attributes invalid"
    }

    func onSignUpCompleted(newState: SignInAfterSignUpState) {
        resultTextView.text = "Signed up successfully!"
    }
}
```

ユーザーが必要なすべての属性を指定しない場合、または属性が無効な場合は、次のデリゲート メソッドが呼び出されます。

- `onSignUpAttributesInvalid`: 送信された 1 つ以上の属性が入力検証に失敗したことを示します。 このエラーには、attributeNames パラメータが含まれています。これは、入力検証に失敗した開発者によって送信されたすべての属性のリストです。
- `onSignUpAttributesRequired`: ユーザー アカウントを作成するには、1 つ以上の属性の送信をサーバーが要求することを示します。 これは、テナント構成で 1 つ以上の属性が必須として設定されている場合に発生します。 この結果には、属性パラメータが含まれています。これは、API に必要なユーザー属性の詳細を示す `MSALNativeAuthRequiredAttribute` オブジェクトのリストです。

両方のデリゲート メソッドに新しい状態参照が含まれています。 `newState` パラメーターを使用して、新しい属性で`submitAttributes(attributes:delegate)`を再度呼び出します。

### サインアップ時にユーザー名 (エイリアス) を収集する

ユーザー名 (エイリアス) は特殊なユーザー属性です。 市区町村や国などの他の属性と同様に、サインアップ時に収集します。 これらの属性とは異なり、ユーザーは後でエイリアスを使用してサインインできます。 エイリアス ("johndoe" など) を使用すると、ユーザーはメール アドレスよりも短く、よりわかりやすい方法でサインインできます。

ユーザー名 (エイリアス) はユーザー名 (電子メール) に置き換えられません。 サインアップ時に、アプリは常にプライマリ識別子としてユーザー名 (電子メール) を収集し、エイリアスを電子メールと共に属性として収集する必要があります。 サインイン時に、ユーザーは自分のユーザー名 (電子メール) またはユーザー名 (エイリアス) を使用してサインインすることを選択できます。

サインアップ ユーザー フローで **Username** 組み込みユーザー属性が有効になっている場合、SDK は、他の属性に使用されるのと同じ属性ディクショナリを使用して、 `flatusername` キーとして受け入れます。 `signUp`呼び出しでユーザー名 (エイリアス) を直接渡すことができるため、ユーザーは別の属性に必要な手順を実行する必要はありません。

ユーザー名 (エイリアス) を収集するには、サインアップ UI のユーザー名の入力フィールドを電子メール フィールドと共に追加し、サインアップ呼び出しで属性としてエイリアスを渡します。

```swift
guard let email = emailTextField.text, !email.isEmpty,
      let password = passwordTextField.text, !password.isEmpty,
      let username = usernameTextField.text, !username.isEmpty else {
    showResultText("Please fill in all fields")
    return
}

let attributes: [String: Any] = [
    "flatusername": username
]

let parameters = MSALNativeAuthSignUpParameters(username: email)
parameters.password = password
parameters.attributes = attributes
nativeAuth.signUp(parameters: parameters, delegate: self)
```

電子メール ワンタイム パスコード フロー (パスワードなし) の場合は、パスワードを設定せずに属性を渡します。

```swift
let parameters = MSALNativeAuthSignUpParameters(username: email)
parameters.attributes = attributes
nativeAuth.signUp(parameters: parameters, delegate: self)
```

ユーザー名 (エイリアス) サインアップのエラーを処理する場合、 `error.isUserAlreadyExists` プロパティは重複するエイリアスも対象とし、 `error.isInvalidAttributes` は無効なエイリアス値を表示します。

### サインアップ エラーを処理する

サインアップ中、すべてのアクションが成功するわけではありません。 たとえば、ユーザーが既に使用中のメール アドレスでサインアップしようとしたり、無効なコードを送信したりすることがあります。

`SignUpStartDelegate` プロトコルの以前の実装では、`onSignUpStartError(error)` デリゲート関数を処理するときにエラーが表示されただけです。

特定のエラーの種類を管理してユーザー エクスペリエンスを高めるために、次のコード スニペットを使用します。

```swift
func onSignUpStartError(error: MSAL.SignUpStartError) {
    if error.isUserAlreadyExists {
        resultTextView.text = "Unable to sign up: User already exists"
    } else if error.isInvalidPassword {
        resultTextView.text = "Unable to sign up: The password is invalid"
    } else if error.isInvalidUsername {
        resultTextView.text = "Unable to sign up: The username is invalid"
    } else {
        resultTextView.text = "Unexpected error signing up: \(error.errorDescription ?? "no description")"
    }
}
```

### 省略可能: サインアップ フロー後にサインインする

サインアップ フローが正常に完了したら、サインイン フローを開始せずにユーザーをサインインできます。 ユーザーがユーザー名 (エイリアス) でサインアップした場合は、メール アドレスまたはエイリアスを使用してサインインできます。 詳細については、「[チュートリアル: iOS アプリでサインアップ後にユーザーをサインインさせる](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-macos-sign-in-user-after-sign-up)」の記事をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-ios-macos-support-web-fallback"} -->
## Web フォールバックのサポート - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-macos-support-web-fallback
- Service: identity-platform / external
- Article date: 2024-09-02
- Summary: ネイティブ認証を使用して認証フローの安定性を確保し、iOS アプリケーションに Web フォールバックを実装する方法を学習します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、ネイティブ認証ではユーザー フローを完了するのに十分ではない場合にブラウザーからトークンを取得する方法について説明します。

このチュートリアルでは、次の操作を行います。

- BrowserRequired エラーを確認します。
- BrowserRequired エラーを処理します。

### 前提条件

- iOS を使用している場合は、「 [サンプルのネイティブ iOS モバイル アプリケーションでユーザーにサインインする](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-ios-sign-in)」の手順に従ってください。
- macOS を使用している場合は、「[ネイティブ認証を使用してサンプル macOS (Swift) アプリでユーザーをサインインする](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-macos-sign-in)」の手順に従ってください。

### ブラウザーが必須

`BrowserRequired` は、ネイティブ認証ではユーザー フローを完了するのに十分ではない、さまざまなシナリオをサポートするフォールバック メカニズムです。

アプリケーションの安定性を確保し、認証フローの中断を回避するには、SDK の `acquireToken()` メソッドを使用してブラウザーでフローを続行することを強くお勧めします。

SDK を初期化するときは、アプリケーションがサポートできるチャレンジ タイプを指定する必要があります。 SDK が受け入れるチャレンジの種類の一覧を次に示します。

- OOB (アウトオブバンド): iOS/macOS アプリケーションがワンタイム パスコード (この場合は電子メール コード) を処理できる場合は、このチャレンジ タイプを追加します。
- パスワード: アプリケーションがパスワード ベースの認証を処理できる場合に、このチャレンジの種類を追加します。

クライアントが提供できない機能を Microsoft Entra が必要とする場合に `BrowserRequired` エラーが返されます。 たとえば、チャレンジの種類に OOB のみを指定してSDK インスタンスを初期化するものの、Microsoft Entra 管理センターでは、アプリケーションは **メールアドレスとパスワード** のユーザー フローで構成されているといったケースです。 SDK インスタンスから **signUp(username)** メソッドを呼び出すと、`BrowserRequired` エラーが返されます。これは、Microsoft Entra が SDK で構成されているチャレンジの種類 (この場合はパスワード) と異なるチャレンジの種類を要求するためです。

チャレンジの種類が不十分というケースは、`BrowserRequired` が発生する可能性がある 1 つの例にすぎません。 `BrowserRequired` は、さまざまなシナリオで発生する可能性がある一般的なフォールバック メカニズムです。

### サンプル フロー

次のコード スニペットでは、SDK インスタンスの初期化中にチャレンジの種類を指定する方法を確認できます。

```swift
nativeAuth = try MSALNativeAuthPublicClientApplication(
    clientId: "<client id>",
    tenantSubdomain: "<tenant subdomain>",
    challengeTypes: [.OOB]
)
```

この場合では、チャレンジの種類 OOB のみを指定しています。 Microsoft Entra 管理センターでは、アプリケーションは**電子メールとパスワード**のユーザー フローで構成されているとします。

```swift
let parameters = MSALNativeAuthSignUpParameters(username: email)
nativeAuth.signUp(parameters: parameters, delegate: self)

func onSignUpStartError(error: MSAL.SignUpStartError) {
    if error.isBrowserRequired {
        // handle browser required error
    }
}
```

SDK インスタンスから `signUp(parameters:delegate)` メソッドを呼び出すと、`BrowserRequired` エラーが返されます。これは、Microsoft Entra が SDK で構成されているチャレンジの種類 (この場合はパスワード) と異なるチャレンジの種類を要求するためです。

### BrowserRequired エラーの処理

この種類のエラーを処理するには、ブラウザーを起動して、そこでユーザーが認証フローを行う必要があります。 これは `acquireToken()` メソッドを使用すると実行できます。 このメソッドを使用するには、いくつかの追加構成を行う必要があります。

- [Xcode プロジェクトで URL スキームを構成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-ios-swift-prepare-app?pivots=workforce#for-ios-only-configure-url-schemes)
- [Microsoft Entra 管理センターでリダイレクト URI を指定する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-ios-swift-prepare-tenant#add-a-platform-redirect-url)

トークンとアカウントを対話形式で取得できるようになりました。 これを行う方法の例を次に示します。

```swift
func onSignUpStartError(error: MSAL.SignUpStartError) {
    if error.isBrowserRequired {
        let webviewParams = MSALWebviewParameters(authPresentationViewController: self)
        let parameters = MSALInteractiveTokenParameters(scopes: ["User.Read"], webviewParameters: webviewParams)

        nativeAuth.acquireToken(with: parameters) { (result: MSALResult?, error: Error?) in
            // result will contain account and tokens retrieved in the browser
        }
    }
}
```

返されるトークンとアカウントは、ネイティブ認証フローを通じて取得されるものと同じです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-ios-sign-in-call-api"} -->
## ネイティブ認証を使用して iOS アプリで API を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-sign-in-call-api
- Service: identity-platform / external
- Article date: 2024-08-12
- Summary: ネイティブ認証を使用して、iOS アプリで複数のアクセス トークンを取得し、API を呼び出す方法を学習します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、アクセス トークンを取得し、 iOS モバイル アプリで API を呼び出す方法について説明します。 iOS 用 Microsoft 認証ライブラリ (MSAL) ネイティブ認証 SDK を使用すると、シングル サインインを使用して複数のアクセス トークンを取得できます。 この機能を使用すると、ユーザーに再認証を要求する必要なく、1 つ以上のアクセス トークンを取得できます。

このチュートリアルでは、次の操作を行います。

- 1 つまたは複数のアクセス トークンを取得します。
- API を呼び出す

### 前提条件

- 「[ネイティブ認証を使用してサンプル iOS モバイル アプリでユーザーをサインインし、API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-ios-call-api)」の手順を完了します。
- 「[チュートリアル: ネイティブ認証を使用して iOS アプリにサインインとサインアウトを追加する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-macos-sign-in-sign-out)」の手順を完了します。 このチュートリアルでは、ネイティブ認証を使用して iOS アプリでユーザーにサインインさせる方法について説明します。

### 1 つまたは複数のアクセス トークンを取得します。

MSAL ネイティブ認証 SDK は、複数のアクセス トークンを保存できます。 サインイン後、`getAccessToken(parameters:)`関数を使用し、付与する新しいアクセス トークンのスコープを指定することで、アクセス トークンを取得できます。

1. 次のコード スニペットを使用して、一連の API スコープを宣言し値を設定します。

    ```swift
    let protectedAPIUrl1: String? = nil
    let protectedAPIUrl2: String? = nil 
    let protectedAPIScopes1: [String] = []
    let protectedAPIScopes2: [String] = []
    
    var accessTokenAPI1: String?
    var accessTokenAPI2: String?
    ```

    - 最初の Web API の URL を使用して `protectedAPIUrl1` を初期化します。
    - 2 つ目の Web API の URL を使用して `protectedAPIUrl2` を初期化します。
    - `protectedAPIScopes1` など、最初の API のスコープを使用して `["api://<Resource_App_ID>/ToDoList.Read", "api://<Resource_App_ID>/ToDoList.ReadWrite"]` を定義します。
    - `protectedAPIScopes2`と同様に、2 番目の API のスコープを使用して`protectedAPIScopes1`を定義します。
    - 省略可能な文字列変数の `accessTokenAPI1` と `accessTokenAPI2`を宣言します。
2. 次のコード スニペットを使用してユーザーにサインインさせます。

    ```swift
    @IBAction func signInPressed(_: Any) {
        guard let email = emailTextField.text, let password = passwordTextField.text else {
            resultTextView.text = "Email or password not set"
            return
        }
    
        print("Signing in with email \(email) and password")
    
        showResultText("Signing in...")
        let parameters = MSALNativeAuthSignInParameters(username: email)
        parameters.password = password
        nativeAuth.signIn(parameters: parameters, delegate: self)
    }
    ```

    `signInPressed` メソッドは、サインイン ボタンの押す処理します。 電子メールとパスワードのフィールドが入力されているかどうかを確認します。 どちらかが空の場合は、「メールまたはパスワードが設定されていません」と表示されます。両方のフィールドに入力すると、メールがログに記録され、"サインイン..." と表示され、指定されたメールとパスワードを使用して `signIn` から `nativeAuth` メソッドを使用してサインインが開始されます。 スコープが指定されていないため、SDK は既定の OIDC スコープ (openid、offline\_access、プロファイル) に対して有効なトークンを取得します。
3. 次のコード スニペットを使用して、1 つまたは複数のアクセス トークンを取得します。

    ```swift
    @IBAction func protectedApi1Pressed(_: Any) {
        guard let url = protectedAPIUrl1, !protectedAPIScopes1.isEmpty else {
            showResultText("API 1 not configured.")
            return
        }
    
        if let accessToken = accessTokenAPI1 {
            accessProtectedAPI(apiUrl: url, accessToken: accessToken)
        } else {
            let parameters = MSALNativeAuthGetAccessTokenParameters()
            parameters.scopes = protectedAPIScopes1
            accountResult?.getAccessToken(parameters: parameters, delegate: self)
            let message = "Retrieving access token to use with API 1..."
            showResultText(message)
            print(message)
        }
    }
    
    @IBAction func protectedApi2Pressed(_: Any) {
        guard let url = protectedAPIUrl2, !protectedAPIScopes2.isEmpty else {
            showResultText("API 2 not configured.")
            return
        }
    
        if let accessToken = accessTokenAPI2 {
            accessProtectedAPI(apiUrl: url, accessToken: accessToken)
        } else {
            let parameters = MSALNativeAuthGetAccessTokenParameters()
            parameters.scopes = protectedAPIScopes2
            accountResult?.getAccessToken(parameters: parameters, delegate: self)
            let message = "Retrieving access token to use with API 2..."
            showResultText(message)
            print(message)
        }
    }
    ```

    `protectedApi1Pressed`メソッドと`protectedApi2Pressed`メソッドは、2 つの異なるスコープ セットのアクセス トークンを取得するプロセスを管理します。 まず、各 API の URL とスコープが適切に構成されていることを確認します。 API のアクセス トークンが既に使用可能な場合は、API に直接アクセスします。 それ以外の場合は、アクセス トークンを要求し、進行中のトークン取得プロセスについてユーザーに通知します。

    `protectedAPIScopes1`と`protectedAPIScopes2`にアクセス トークンを割り当てるには、次のスニペットを使用します。

    ```swift
    func onAccessTokenRetrieveCompleted(result: MSALNativeAuthTokenResult) {
        print("Access Token: \(result.accessToken)")
    
        if protectedAPIScopes1.allSatisfy(result.scopes.contains),
           let url = protectedAPIUrl1
        {
            accessTokenAPI1 = result.accessToken
            accessProtectedAPI(apiUrl: url, accessToken: result.accessToken)
        }
    
        if protectedAPIScopes2.allSatisfy(result.scopes.contains(_:)),
           let url = protectedAPIUrl2
        {
            accessTokenAPI2 = result.accessToken
            accessProtectedAPI(apiUrl: url, accessToken: result.accessToken)
        }
    
        showResultText("Signed in." + "\n\n" + "Scopes:\n\(result.scopes)" + "\n\n" + "Access Token:\n\(result.accessToken)")
        updateUI()
    }
    
    func onAccessTokenRetrieveError(error: MSAL.RetrieveAccessTokenError) {
        showResultText("Error retrieving access token: \(error.errorDescription ?? "No error description")")
    }
    ```

    `onAccessTokenRetrieveCompleted` メソッドは、アクセス トークンをコンソールに表示します。 次に、`protectedAPIScopes1` が結果のスコープに含まれているかどうか、`protectedAPIUrl1` が使用可能かどうかを確認します。使用可能な場合は、`accessTokenAPI1` を設定し、URL とトークンを使用して `accessProtectedAPI` を呼び出します。 `protectedAPIScopes2`と`protectedAPIUrl2`の同様のチェックが実行され、`accessTokenAPI2`が更新され、条件が満たされた場合に API 呼び出しが行われます。 最後に、サインイン状態、スコープ、アクセス トークンを含むメッセージが表示され、UI が更新されます。

    `onAccessTokenRetrieveError` メソッドは、アクセス トークン取得エラーの説明を含むエラー メッセージを表示し、説明が指定されていない場合は既定のメッセージを表示します。

### API を呼び出す

次のコード スニペットを使用して API を呼び出します。

```swift
func accessProtectedAPI(apiUrl: String, accessToken: String) {
    guard let url = URL(string: apiUrl) else {
        let errorMessage = "Invalid API url"
        print(errorMessage)
        DispatchQueue.main.async {
            self.showResultText(errorMessage)
        }
        return
    }
    
    var request = URLRequest(url: url)
    request.httpMethod = "GET"
    request.setValue("Bearer \(accessToken)", forHTTPHeaderField: "Authorization")
    
    let task = URLSession.shared.dataTask(with: request) { data, response, error in
        if let error = error {
            print("Error found when accessing API: \(error.localizedDescription)")
            DispatchQueue.main.async {
                self.showResultText(error.localizedDescription)
            }
            return
        }
        
        guard let httpResponse = response as? HTTPURLResponse, (200...299).contains(httpResponse.statusCode)
        else {
            DispatchQueue.main.async {
                self.showResultText("Unsuccessful response found when accessing the API")
            }
            return
        }
        
        guard let data = data, let result = try? JSONSerialization.jsonObject(with: data, options: []) else {
            DispatchQueue.main.async {
                self.showResultText("Couldn't deserialize result JSON")
            }
            return
        }
        
        DispatchQueue.main.async {
            self.showResultText("""
                            Accessed API successfully using access token.
                            HTTP response code: \(httpResponse.statusCode)
                            HTTP response body: \(result)
                            """)
        }
    }
    
    task.resume()
}
```

`accessProtectedAPI` メソッドは、提供されたアクセス トークンを使用して、指定された API エンドポイントに対してGETリクエストを送信します。 Authorization ヘッダーのトークンを使用して要求を構成します。 正常な応答 (HTTP 状態コード 200 から 299) を受け取ると、JSON データが逆シリアル化され、HTTP 状態コードと応答本文で UI が更新されます。 要求または応答の処理中にエラーが発生した場合は、UI にエラー メッセージが表示されます。 このメソッドは、指定された URL とアクセス トークンに応じて、API 1 または API 2 へのアクセスを許可します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-ios-swift-custom-headers"} -->
## iOS のネイティブ認証ネットワーク要求にカスタム ヘッダーを追加する (Swift) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-swift-custom-headers
- Service: identity-platform / external
- Article date: 2026-04-29
- Summary: iOS (Swift) アプリでネイティブ認証ネットワーク要求にカスタム x-* ヘッダーをアタッチして、不正検出 SDK をMicrosoft Entra 外部 IDと統合する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、`x-*` プロトコルを使用して iOS (Swift) アプリのネイティブ認証ネットワーク要求にカスタム `MSALNativeAuthRequestInterceptor` ヘッダーをアタッチし、サードパーティの不正行為やボット検出 SDK との統合を有効にする方法について説明します。

このチュートリアルでは、次の操作を行います。

- MSAL によって適用されるヘッダーの名前付け規則について理解します。
- `MSALNativeAuthRequestInterceptor` プロトコルを実装します。
- インターセプターをアプリ構成に登録します。

### 前提条件

- MSAL ネイティブ認証を使用する iOS (Swift) アプリ。 お持ちでない場合は、「 [チュートリアル: ネイティブ認証用に iOS/macOS モバイル アプリを準備する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-prepare-ios-macos-app)」を完了してください。
- アプリは、 `MSALNativeAuthPublicClientApplication` インスタンスで初期化されます。 このチュートリアルの手順では、インターセプターを登録するように既存のアプリ初期化コードを更新する方法について説明します。

### ヘッダーの名前付けルールについて

MSAL は、指定したヘッダーを評価するときに次の規則を適用します。

- ヘッダーはで始まる`x-` (大文字と小文字は区別されません)。 `x-`で始まらないヘッダーは無視されます。
- 次のいずれかの予約済みプレフィックスで始まるヘッダーは無視されます。
    - `x-client-`
    - `x-ms-`
    - `x-broker-`
    - `x-app-`
- MSAL は、両方の規則をネットワーク要求に渡すヘッダーを追加します。 指定したヘッダーの名前が MSAL 独自の内部ヘッダーの 1 つと同じである場合は、値が優先されます。

これらの規則を使用して、インターセプターを実装する前にベンダーが必要とするヘッダー名を確認します。

### 要求インターセプターを実装する

`MSALNativeAuthRequestInterceptor` プロトコルは、MSAL が各ネットワーク要求を送信する前に呼び出す 1 つのメソッドを宣言します。 実装では、要求 URL と完了ブロックを受け取り、追加するヘッダーのディクショナリを使用して完了ブロックを呼び出します。その要求にヘッダーが必要ない場合は `nil` します。

ビュー コントローラー (またはアプリ内の別のクラス) を `MSALNativeAuthRequestInterceptor`に準拠させます。

```swift
extension EmailAndPasswordViewController: MSALNativeAuthRequestInterceptor {

    func addAdditionalHeaderFields(
        _ requestUrl: URL?,
        completionBlock: @escaping MSALNativeAuthRequestInterceptorAddHeaderCompletionBlock
    ) {
        // Scope headers to specific endpoints only.
        if requestUrl?.absoluteString.contains("oauth2/v2.0/initiate") == true {
            completionBlock([
                "value_1": "customer_header_1",          // Ignored: doesn't start with "x-"
                "x-client-header": "customer_header_2",  // Ignored: starts with reserved prefix "x-client-"
                "X-my-custom-header": "my data"          // Added to the network request.
            ])
            return
        }

        // Return nil for all other requests to avoid over-sending signals.
        completionBlock(nil)
    }
}
```

メソッドは、 `requestUrl`で送信要求の完全な URL を受け取ります。 これを使用して、詐欺またはボット検出ベンダーが必要とする特定のエンドポイントにヘッダーのスコープを設定します。 たとえば、サインインまたはサインアップの開始エンドポイントなどです。 関連のないエンドポイントにヘッダーを送信すると、信号品質が低下し、誤検知が増加する可能性があります。

Note

呼び出しごとに常に 1 回だけ `completionBlock` を呼び出します。 その要求に追加のヘッダーが必要ない場合は、 `nil` を渡します。

### インターセプターを登録する

プロトコルを実装した後、`requestInterceptor`を作成する前に、`MSALNativeAuthPublicClientApplicationConfig` インスタンスの`MSALNativeAuthPublicClientApplication` プロパティにインターセプターを割り当てます。

```swift
do {
    let config = try MSALNativeAuthPublicClientApplicationConfig(
        clientId: Configuration.clientId,
        tenantSubdomain: Configuration.tenantSubdomain,
        challengeTypes: [.OOB, .password]
    )

    config.requestInterceptor = self

    nativeAuth = try MSALNativeAuthPublicClientApplication(nativeAuthConfiguration: config)
} catch {
    print("Unable to initialize MSAL \(error)")
}
```

### ヘッダーが適用されていることを確認する

ヘッダーが目的のエンドポイントに到達することを確認するには、Fiddler や Charles Proxy などのネットワーク プロキシ ツールを使用して、送信ネットワーク トラフィックを調べます。 次の内容を確認する:

- `x-`で始まり、予約済みのプレフィックスのないヘッダーが要求に表示されます。
- 予約済みプレフィックス (`x-client-`、 `x-ms-`、 `x-broker-`、 `x-app-`) を持つヘッダーは要求に表示されません。
- ヘッダーは、スコープを設定したエンドポイントにのみ送信されます。

Note

インターセプター コールバック内のログには、最終的な要求ヘッダーは表示されません。 コールバックは、MSAL が名前付け規則を評価して適用する前に呼び出されるため、最終的に送信されるヘッダーではなく、指定したヘッダーのみが反映されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-ios-swift-email-one-time-passcode-sign-in"} -->
## ネイティブ認証を使用して iOS/macOS アプリに電子メール ワンタイム パスコード MFA を追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-swift-email-one-time-passcode-sign-in
- Service: identity-platform / external
- Article date: 2026-01-27
- Summary: ネイティブ認証を使用して iOS または macOS アプリに電子メール ワンタイム パスコード (OTP) 多要素認証 (MFA) を追加し、認証コンテキストで MFA を適用する方法について説明します。

このチュートリアルでは、ネイティブ認証を使用して iOS/macOS アプリに多要素認証 (MFA) を実装する方法について説明します。 MFA では、サインイン時に追加の検証手順をユーザーに提供するように要求することで、アプリにセキュリティレイヤーが追加されます。 電子メールと SMS ワンタイム パスコード MFA をサポートしています。 また、認証時にセキュリティを強化し、認証コンテキストを使用して MFA を適用する方法についても説明 [します](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#authentication-context)。

このチュートリアルでは、以下の内容を学習します。

- 電子メール ワンタイム パスコード MFA を使用してユーザーをサインインさせる
- MFA を適用するために、ユーザーのサインイン時に認証コンテキストを指定します。

### [前提条件]

1. 「[チュートリアル: ネイティブ認証を使用して iOS アプリにサインインとサインアウトを追加する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-macos-sign-in-sign-out)」の手順を完了します。
2. 顧客に対して MFA を有効にするには、「アプリに [MFA を追加する」](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers)の手順に従います。 現在、ネイティブ認証では、MFA の 2 番目の要素として電子メール ワンタイム パスコードがサポートされています。これは、プライマリ認証方法がパスワード付きの電子メールである場合にのみ使用できます。
3. Email OTP 実装を使用した MFA によるサインインを調べる場合は、開始する前に、[Code サンプル](https://github.com/Azure-Samples/ms-identity-ciam-native-auth-ios-sample)をご確認ください。

### ネイティブ認証クライアントで MFA 機能を有効にする

`MSALNativeAuthPublicClientApplication`を初期化するときに capabilities パラメーターを構成して、多要素認証を有効にします。

```swift
let config = try MSALNativeAuthPublicClientApplicationConfig(
    clientId: Configuration.clientId,
    tenantSubdomain: Configuration.tenantSubdomain,
    challengeTypes: [.OOB, .password]
)
config.capabilities = [.mfaRequired]
nativeAuth = try MSALNativeAuthPublicClientApplication(nativeAuthConfiguration: config)
```

### 電子メール ワンタイム パスコード MFA を使用してユーザーをサインインさせる

電子メールワンタイム パスコード MFA を使用してユーザーをサインインさせるには、電子メールとパスワードを収集した後、ユーザーが自分のメールを確認するためのワンタイム パスコードを含む電子メールを送信する必要があります。 有効なワンタイム パスコードを入力すると、アプリはユーザーをサインインさせます。

電子メール ワンタイム パスコード MFA を使用してユーザーをサインインさせるには、次の操作を行う必要があります。

1. 「ユーザーインターフェース (UI) を作成して、以下を行う：」

    - サインインに MFA が必要であることをユーザーに知らします (省略可能)。
    - 可能な `authMethods` をユーザーに表示し、電子メールを含むものを選択させます (省略可能)。
    - ユーザーから電子メールワンタイム パスコードを収集して、2 番目の認証要素を満たします。
    - ワンタイム パスコードを再送信します (推奨)。
2. `onSignInAwaitingMFA` プロトコルの一部として、`SignInStartDelegate` メソッドを実装します。

    ```swift
     extension ViewController: SignInStartDelegate {
    
         // previous code omitted
    
         func onSignInAwaitingMFA(authMethods: [MSALAuthMethod], newState: AwaitingMFAState) {
             // inform the user that MFA is required
             let alert = UIAlertController(
                 title: "MFA required", 
                 message: "Do you want to proceed with MFA?", 
                 preferredStyle: .alert
             )
    
             alert.addAction(UIAlertAction(title: "OK", style: .default, handler: { _ in
                 // You can select the first authMethod which has a challengeChannel of type email
                 // Or use "actionResult.authMethods" to let the user pick the authentication method to use.
                 var authMethod = actionResult.authMethods.first { $0.channelTargetType.isEmailType }     
                 self.authMethod = authMethod
                 newState.requestChallenge(authMethod: authMethod, delegate: self)
             }))
    
             alert.addAction(UIAlertAction(title: "Cancel", style: .cancel, handler: { _ in
                 self.resultTextView.text = "Second factor authentication required"
             }))
    
             present(alert, animated: true)
         }
     }
    ```

    `signIn(username:password:delegate)` によってデリゲート メソッドが呼び出されます。 `onSignInAwaitingMFA(authMethods:newState)`メソッドは、MFA が必要なシナリオを処理します。 ユーザーにアラートを表示し、MFA を続行するかどうかを確認します。 ユーザーが **[OK] を** 選択した場合は、 `newState.requestChallenge(authMethod: authMethod delegate: self)`を呼び出して MFA チャレンジを要求します。 `newState.requestChallenge(authMethod: authMethod, delegate: self)` メソッドは、ワンタイム パスコードをユーザーのメール アドレスに送信します。

    MFA の必要性についてユーザーに通知しない場合は、 `newState.requestChallenge(authMethod: authMethod, delegate: self)`を直接呼び出すことで、ユーザーの操作をバイパスできます。 `requestChallenge(authMethod: delegate)`では、電子メールを送信する場所に authMethod を指定し、デリゲート パラメーターを受け取る必要があります。 `MFARequestChallengeDelegate` プロトコルで必要なメソッドを実装する必要があります。
3. `MFARequestChallengeDelegate` プロトコルを実装します。

    ```swift
    extension ViewController: MFARequestChallengeDelegate {
    
        func onMFARequestChallengeError(error: MFARequestChallengeError, newState: MFARequiredState?) {
            resultTextView.text = "Error requesting MFA challenge: \(error.errorDescription ?? "no description")"
        }
    
        func onMFARequestChallengeVerificationRequired(
            newState: MFARequiredState,
            sentTo: String,
            channelTargetType: MSALNativeAuthChannelType,
            codeLength: Int
        ) {
            let code = retrieveCodeFromUser()
            newState.submitChallenge(challenge: code, delegate: self)
        }
    }
    ```

    最も一般的なシナリオでは、ユーザーの電子メール アドレスを確認するためにコードが送信されたことを示す `onMFARequestChallengeVerificationRequired(newState:sentTo:channelTargetType:codeLength)` の呼び出しを受け取ります。 ユーザーからワンタイム パスコードを収集したら、次のように `submitChallenge` 呼び出しを呼び出すことができます`newState.submitChallenge(challenge: code, delegate: self)`。 `submitChallenge`はデリゲート パラメーターを受け取り、`MFASubmitChallengeDelegate` プロトコルで必要なメソッドを実装する必要があります。
4. `MFASubmitChallengeDelegate` プロトコルを実装します。

    ```swift
    extension MultiFactorAuthenticationViewController: MFASubmitChallengeDelegate {
    
        func onMFASubmitChallengeError(error: MFASubmitChallengeError, newState: MFARequiredState?) {
            resultTextView.text = "Error submitting MFA challenge: \(error.errorDescription ?? "no description")"
            newState.submitChallenge(challenge: "newCode", delegate: self)
        }
    
        func onSignInCompleted(result: MSALNativeAuthUserAccountResult) {
            print("Signed in as: \(result.account.username ?? "unknown user")")
        }
    }
    ```

    `MFASubmitChallengeDelegate` プロトコルを使用すると、MFA コードの送信と検証を処理できます。 `onMFASubmitChallengeError`による無効なコードなど、エラーを管理するメソッドを提供し、再送信を許可します。 MFA が成功すると、 `onSignInCompleted` はサインイン プロセスを完了します。 このデリゲートを実装すると、認証中の MFA 処理がスムーズになります。

### サインイン時に認証コンテキストを使用する

サインイン時に認証コンテキストを指定する前に、Microsoft Entra管理センターで条件付きアクセス ポリシーを構成していることを確認します。[認証コンテキストの構成](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#configure-authentication-contexts)に記載されている手順を実行します。 認証コンテキストを構成した後、次のコード スニペットを使用して、サインイン時にこの要求要求を指定できます。

```swift
@IBAction func signInPressed(_: Any) {
    let parameters = MSALNativeAuthSignInParameters(username: email)
    parameters.password = password
    // before calling the signIn method, we need to specify the authentication context as request claim
    var error: NSError? = nil
    let authenticationContextClaim = "{\"access_token\":{\"acrs\":{\"essential\":true,\"value\":\"<authentication context id>\"}}}"
    params.claimsRequest = MSALClaimsRequest(jsonString: authenticationContextClaim, error: &error)
    // now we can call the signIn method
    nativeAuth.signIn(parameters: parameters, delegate: self)
}
```

この時点で MFA フローを開始し、メソッド "onSignInAwaitingMFA(newState)" が呼び出されます。

### 電子メール ワンタイム パスコード MFA エラーによるサインインの処理

MFA 中に発生するエラーを処理するには、次の方法を実装する必要があります。

1. `requestChallenge` メソッドのエラーを処理するには、次のコード スニペットを使用します。

    ```swift
    func onMFARequestChallengeError(error: MFARequestChallengeError, newState: MFARequiredState?) {
        if error.isBrowserRequired {
            showResultText("Browser is required")
        } else {
            showResultText("Unexpected error while requesting challenge: \(error.errorDescription ?? "No error description")")
        }
    }
    ```

    `onMFARequestChallengeError`関数は、MFA 要求チャレンジ中に発生したエラーを処理します。
2. `submitChallenge(challenge)` メソッドのエラーを処理するには、次のコード スニペットを使用します。

    ```swift
    func onMFASubmitChallengeError(error: MFASubmitChallengeError, newState: MFARequiredState?) {
        if error.isInvalidChallenge {
            // Inform the user that the submitted code was incorrect and ask for a new code to be supplied
            let userSuppliedCode = retrieveNewCode()
            newState.submitChallenge(challenge: userSuppliedCode, delegate: self)
        } else {
            showResultText("Unexpected error occurred: \(error.errorDescription ?? "No error description")")
        }
    }
    ```

    `onMFASubmitChallengeError`関数は、MFA チャレンジの送信中に発生したエラーを処理します。 送信されたチャレンジが無効な場合は、 `newState` パラメーターを使用して新しいチャレンジを送信できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-ios-swift-email-strong-authentication-method"} -->
## ネイティブ認証を使用して iOS/macOS アプリに電子メールの強力な認証方法の登録を追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-swift-email-strong-authentication-method
- Service: identity-platform / external
- Article date: 2025-11-17
- Summary: ネイティブ認証を使用して、iOS または macOS アプリで MFA 対応ユーザーの電子メールの強力な認証方法を登録する方法について説明します。

このチュートリアルでは、ネイティブ認証を使用して、iOS/macOS アプリに電子メールの強力な認証方法の登録を実装する方法について説明します。 MFA が有効なユーザーには、少なくとも 1 つの強力な認証が必須です。 強力な認証方法として電子メールと SMS をサポートしています。

このチュートリアルでは、以下の内容を学習します。

- MFA が有効なユーザーの強力な認証方法を登録します。

### [前提条件]

1. 「[チュートリアル: ネイティブ認証を使用して iOS アプリにサインインとサインアウトを追加する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-macos-sign-in-sign-out)」の手順を完了します。
2. 顧客に多要素認証 (MFA) を適用するには、「 [アプリに多要素認証 (MFA) を追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers) 」の手順を使用して、サインイン フローに電子メール OTP MFA を追加します。 電子メールと SMS ワンタイム パスコード MFA をサポートしています。 そのため、2 番目の要素としての電子メールは、認証方法がパスワード付きの電子メールである場合にのみ使用できます。
3. 強力な認証方法の登録の実装を調べる場合は、開始する前に、[Code サンプル](https://github.com/Azure-Samples/ms-identity-ciam-native-auth-ios-sample)をご確認ください。

### 強力な認証方法の登録機能を構成する

クライアントの初期化中に registrationRequired 機能を設定して、強力な認証方法の登録をサポートします。

```swift
let config = try MSALNativeAuthPublicClientApplicationConfig(
    clientId: Configuration.clientId,
    tenantSubdomain: Configuration.tenantSubdomain,
    challengeTypes: [.OOB, .password]
)
config.capabilities = [.registrationRequired]
nativeAuth = try MSALNativeAuthPublicClientApplication(nativeAuthConfiguration: config)
```

### 強力な認証方法として電子メール ワンタイム パスコードを登録する

MFA が有効なユーザーの電子メール ワンタイム パスコードを登録するには、アカウントの作成時に指定された既定の電子メール アドレスを使用するか、強力な認証方法として代替の電子メール アドレスを指定します。 このプロセスは、MFA が初めて必要なときにのみトリガーされ、ユーザーには強力な認証方法が登録されていません。 その後、ユーザーが自分のメール アドレスを確認するためのワンタイム パスコードを含む電子メールを送信する必要があります。 ユーザーが有効なワンタイム パスコードを入力すると、SDK はサインイン プロセスを完了します。

MFA 対応ユーザーの強力な認証方法 (電子メール ワンタイム パスコード) を登録するには、次の操作を行う必要があります。

1. 「ユーザーインターフェース (UI) を作成して、以下を行う：」

    - サインインには強力な認証方法の登録が必要であることをユーザーに知らしてください (省略可能)。
    - 強力な認証方法として登録する電子メール アドレスを収集します (省略可能)。 ユーザーが電子メール アドレスを指定しない場合は、アカウントの作成時に指定された既定の電子メール アドレスが使用されます。
    - ユーザーから電子メールワンタイム パスコードを収集して、2 要素認証を実行します。
    - ワンタイム パスコードを再送信します (推奨)。
2. `onSignInStrongAuthMethodRegistration` プロトコルの一部として、`SignInStartDelegate` メソッドを実装します。

    ```swift
     extension ViewController: SignInStartDelegate {
    
         // previous code omitted
         func onSignInStrongAuthMethodRegistration(authMethods: [MSALAuthMethod], newState: RegisterStrongAuthState) {
    
             // inform the user that registration is required
             let alert = UIAlertController(
                 title: "Missing strong authentication method", 
                 message: "Registration of strong authentication method is required. Do you want to proceed with registration?", 
                 preferredStyle: .alert
             )
    
             // You can select the first authMethod which has a challengeChannel of type email
             // Or use "actionResult.authMethods" to let the user pick the authentication method to use.
             var authMethod = actionResult.authMethods.first { $0.channelTargetType.isEmailType }
    
             alert.addAction(UIAlertAction(title: "OK", style: .default, handler: { _ in
                 if verificationContact.isEmpty {
                     verificationContact = "user@contoso.com" // You can use the email address provided during account creation or specify an alternative email address.
                 }
                 let parameter = MSALNativeAuthChallengeAuthMethodParameters(authMethod: authMethod, verificationContact: verificationContact)
                 newState.challengeAuthMethod(parameters: parameter, delegate: self)
             }))
    
             alert.addAction(UIAlertAction(title: "Cancel", style: .cancel, handler: { _ in
                 self.resultTextView.text = "Strong authentication method registration is required"
             }))
    
             present(alert, animated: true)
     }
    ```

    `signIn(username:password:delegate)` によってデリゲート メソッドが呼び出されます。 `onSignInStrongAuthMethodRegistration(authMethods:newState)`メソッドは、強力な認証方法の登録が必要なシナリオを処理します。 登録を続行するかどうかを確認するアラートをユーザーに提示できます。 ユーザーが **[OK] を** 選択した場合は、 `newState.challengeAuthMethod(parameters:delegate)`を呼び出して MFA チャレンジを要求します。 `newState.challengeAuthMethod(parameters:delegate)`メソッドは、強力な認証方法をサーバーに送信して登録します。

    登録の必要性についてユーザーに通知しない場合は、 `newState.challengeAuthMethod(parameters:delegate)`を直接呼び出すことで、ユーザーの操作をバイパスできます。 `challengeAuthMethod`はパラメーターとデリゲート インスタンスを受け取ります。`RegisterStrongAuthChallengeDelegate` プロトコルで必要なメソッドを実装する必要があります。
3. `RegisterStrongAuthChallengeDelegate` プロトコルを実装します。

    ```swift
    extension ViewController: RegisterStrongAuthChallengeDelegate {
        func onRegisterStrongAuthChallengeError(
            error: RegisterStrongAuthChallengeError,
            newState: RegisterStrongAuthState?
        ) {
            resultTextView.text = "Error registering strong authentication method: \(error.errorDescription ?? "no description")"
        }
    
        func onRegisterStrongAuthVerificationRequired(
            result: MSALNativeAuthRegisterStrongAuthVerificationRequiredResult
        ) {
            let newState = result.newState
            if verificationContact.isEmpty {
                verificationContact = "user@contoso.com" // You can use the email address provided during account creation or specify an alternative email address.
            }
            let parameter = MSALNativeAuthChallengeAuthMethodParameters(authMethod: authMethod)
            newState.challengeAuthMethod(parameters: parameter, delegate: self)
        }
    }
    ```

    最も一般的なシナリオでは、アプリが電子メール アドレスに送信された電子メール ワンタイム パスコードを送信することを SDK が予期していることを示す `onRegisterStrongAuthVerificationRequired(result)` の呼び出しを受け取ります。 提出が完了したら、次の`challengeAuthMethod`のように`newState.challengeAuthMethod(parameters:delegate)` メソッドを呼び出すことができます。 `challengeAuthMethod`はデリゲート パラメーターを受け取り、`RegisterStrongAuthSubmitChallengeDelegate` プロトコルで必要なメソッドを実装する必要があります。
4. `RegisterStrongAuthSubmitChallengeDelegate` プロトコルを実装します。

    ```swift
    extension ViewController: RegisterStrongAuthSubmitChallengeDelegate {
         func onRegisterStrongAuthSubmitChallengeError(
            error: RegisterStrongAuthSubmitChallengeError,
            newState: RegisterStrongAuthVerificationRequiredState?
        ) {
            resultTextView.text = "Error submitting challenge: \(error.errorDescription ?? "no description")"
            newState.submitChallenge(challenge: "newCode", delegate: self)
        }
    
        func onSignInCompleted(result: MSALNativeAuthUserAccountResult) {
            print("Signed in as: \(result.account.username ?? "unknown user")")
        }
    }
    ```

    `RegisterStrongAuthSubmitChallengeDelegate` プロトコルを使用すると、検証コードの送信を処理できます。 `onRegisterStrongAuthSubmitChallengeError`による無効なコードなど、エラーを管理するメソッドを提供し、再送信を許可します。 検証が成功すると、 `onSignInCompleted` メソッドが呼び出され、サインイン プロセスが完了します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-ios-swift-sms-one-time-passcode-sign-in"} -->
## ネイティブ認証を使用して iOS および macOS アプリに SMS ワンタイム パスコード MFA を追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-swift-sms-one-time-passcode-sign-in
- Service: identity-platform / external
- Article date: 2026-01-27
- Summary: 認証コンテキストでの MFA の適用や MFA エラーの処理など、ネイティブ認証を使用して、SMS ワンタイム パスコード (OTP) を使用する多要素認証 (MFA) を iOS または macOS アプリに追加する方法について説明します。

このチュートリアルでは、ネイティブ認証を使用して、SMS ワンタイム パスコード (OTP) を使用して多要素認証 (MFA) を iOS/macOS アプリに追加する方法について説明します。 MFA では、サインイン時に 2 つ目の検証手順を必要とすることで、セキュリティレイヤーが追加されます。 また、認証時にセキュリティを強化し、認証コンテキストを使用して MFA を適用する方法についても説明 [します](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#authentication-context)。

このチュートリアルでは、以下の内容を学習します。

- SMS ワンタイム パスコード MFA を使用してユーザーをサインインさせる
- サインイン時に認証コンテキストを指定して MFA を適用する
- SMS ワンタイム パスコード MFA エラーによるサインインの処理

### [前提条件]

1. 「[チュートリアル: ネイティブ認証を使用して iOS アプリにサインインとサインアウトを追加する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-macos-sign-in-sign-out)」の手順を完了します。
2. テナントで MFA メソッドとして SMS を有効にする: [「MFA メソッドとして SMS を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-sms-signin#enable-the-sms-based-authentication-method)」の手順に従います。
3. SMS ワンタイム パスコード MFA を使用したサインインの実装を調べる場合は、開始する前に、[Code サンプル](https://github.com/Azure-Samples/ms-identity-ciam-native-auth-ios-sample)をご確認ください。

### MFA を要求するようにクライアント機能を構成する

注

現在、SMS ワンタイムパスコードを権限形式で使用する際の既知の問題があります。 `<tenantName>.ciamlogin.com/<tenantName>.onmicrosoft.com` そのため、次の形式を使用する必要があります。 `<tenantName>.ciamlogin.com/<tenantID>`

ネイティブ認証の初期化中に MFA を適用するために、適切な機能値を使用してクライアントを構成します。

```swift
let tenantId = "00000000-0000-0000-0000-000000000000" //Replace with your tenant ID
let authority = try MSALCIAMAuthority(url: URL(string: "https://\(Configuration.tenantSubdomain).ciamlogin.com/\(tenantId)")!)
let config = MSALNativeAuthPublicClientApplicationConfig(clientId: Configuration.clientId, authority: authority, challengeTypes: [.OOB, .password])
config.capabilities = [.mfaRequired]
nativeAuth = try MSALNativeAuthPublicClientApplication(nativeAuthConfiguration: config)
```

### SMS ワンタイム パスコード MFA を使用してユーザーをサインインさせる

SMS ワンタイム パスコード MFA を使用してユーザーをサインインさせるには、最初の要素を収集した後、ユーザーが自分の電話番号を確認するためのワンタイム パスコードを含む SMS を送信する必要があります。 有効なワンタイム パスコードを入力すると、アプリはユーザーをサインインさせます。

SMS ワンタイム パスコード MFA を使用してユーザーをサインインさせるには、次の操作を行う必要があります。

1. 「ユーザーインターフェース (UI) を作成して、以下を行う：」

    - サインインに MFA が必要であることをユーザーに知らします (省略可能)。
    - 可能な `authMethods` をユーザーに表示し、電話 SMS (省略可能) を使用してユーザーに選択させます。
    - 2 番目の認証要素を満たすために、ユーザーから SMS ワンタイム パスコードを収集します。
    - ワンタイム パスコードを再送信します (推奨)。
2. `onSignInAwaitingMFA` プロトコルの一部として、`SignInStartDelegate` メソッドを実装します。

    ```swift
     extension ViewController: SignInStartDelegate {
    
         // previous code omitted
    
         func onSignInAwaitingMFA(authMethods: [MSALAuthMethod], newState: AwaitingMFAState) {
             // inform the user that MFA is required
             let alert = UIAlertController(
                 title: "MFA required", 
                 message: "Do you want to proceed with MFA?", 
                 preferredStyle: .alert
             )
    
             alert.addAction(UIAlertAction(title: "OK", style: .default, handler: { _ in
                 // You can select the first authMethod which has a channelgeChannel of type email
                 // Or use "actionResult.authMethods" to let the user pick the authentication method to use.
                 var authMethod = actionResult.authMethods.first { $0.channelTargetType.isEmailType }
                 self.authMethod = authMethod
                 newState.requestChallenge(authMethod: authMethod, delegate: self)
             }))
    
             alert.addAction(UIAlertAction(title: "Cancel", style: .cancel, handler: { _ in
                 self.resultTextView.text = "Second factor authentication required"
             }))
    
             present(alert, animated: true)
         }
     }
    ```

    `signIn(username:delegate)` によってデリゲート メソッドが呼び出されます。 `onSignInAwaitingMFA(authMethods:newState)`メソッドは、MFA が必要なシナリオを処理します。 ユーザーにアラートを表示し、MFA を続行するかどうかを確認します。 ユーザーが **[OK] を** 選択した場合は、 `newState.requestChallenge(authMethod: authMethod delegate: self)`を呼び出して MFA チャレンジを要求します。 `newState.requestChallenge(authMethod: authMethod, delegate: self)`メソッドは、ワンタイム パスコードをユーザーの電話番号に送信します。

    MFA の必要性についてユーザーに通知しない場合は、 `newState.requestChallenge(authMethod: authMethod, delegate: self)`を直接呼び出すことで、ユーザーの操作をバイパスできます。 `requestChallenge(authMethod: delegate)`では、SMS を送信する場所に authMethod を指定し、デリゲート パラメーターを受け入れる必要があります。 `MFARequestChallengeDelegate` プロトコルで必要なメソッドを実装する必要があります。
3. `MFARequestChallengeDelegate` プロトコルを実装します。

    ```swift
    extension ViewController: MFARequestChallengeDelegate {
    
        func onMFARequestChallengeError(error: MFARequestChallengeError, newState: MFARequiredState?) {
            resultTextView.text = "Error requesting MFA challenge: \(error.errorDescription ?? "no description")"
        }
    
        func onMFARequestChallengeVerificationRequired(
            newState: MFARequiredState,
            sentTo: String,
            channelTargetType: MSALNativeAuthChannelType,
            codeLength: Int
        ) {
            let code = retrieveCodeFromUser()
            newState.submitChallenge(challenge: code, delegate: self)
        }
    }
    ```

    最も一般的なシナリオでは、ユーザーの電話番号を確認するためにコードが送信されたことを示す `onMFARequestChallengeVerificationRequired(newState:sentTo:channelTargetType:codeLength)` の呼び出しを受け取ります。 ユーザーからワンタイム パスコードを収集したら、次の`submitChallenge`のような`newState.submitChallenge(challenge: code, delegate: self)`呼び出しを呼び出すことができます。 `submitChallenge`はデリゲート パラメーターを受け取り、`MFASubmitChallengeDelegate` プロトコルで必要なメソッドを実装する必要があります。
4. `MFASubmitChallengeDelegate` プロトコルを実装します。

    ```swift
    extension MultiFactorAuthenticationViewController: MFASubmitChallengeDelegate {
    
        func onMFASubmitChallengeError(error: MFASubmitChallengeError, newState: MFARequiredState?) {
            resultTextView.text = "Error submitting MFA challenge: \(error.errorDescription ?? "no description")"
            newState.submitChallenge(challenge: "newCode", delegate: self)
        }
    
        func onSignInCompleted(result: MSALNativeAuthUserAccountResult) {
            print("Signed in as: \(result.account.username ?? "unknown user")")
        }
    }
    ```

    `MFASubmitChallengeDelegate` プロトコルを使用すると、MFA コードの送信と検証を処理できます。 `onMFASubmitChallengeError`による無効なコードなど、エラーを管理するメソッドを提供し、再送信を許可します。 MFA が成功すると、 `onSignInCompleted` はサインイン プロセスを完了します。 このデリゲートを実装すると、認証中の MFA 処理がスムーズになります。

### サインイン時に認証コンテキストを使用する

サインイン時に認証コンテキストを使用する方法については、サインイン [時の認証コンテキスト](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-swift-email-one-time-passcode-sign-in#use-authentication-context-during-sign-in) を参照してください。サインイン時に認証コンテキストを使用する方法について説明します。

### SMS ワンタイム パスコード MFA エラーによるサインインの処理

MFA 中に発生するエラーを処理するには、次の方法を実装する必要があります。

1. `requestChallenge` メソッドのエラーを処理するには、次のコード スニペットを使用します。

    ```swift
    func onMFARequestChallengeError(error: MFARequestChallengeError, newState: MFARequiredState?) {
        if error.isBrowserRequired {
            showResultText("Browser is required")
        } else if error.isAuthMethodBlocked {
            showResultText("Authentication method selected has been blocked. Reach out to customer support  to seek assistance.")
        } else {
            showResultText("Unexpected error while requesting challenge: \(error.errorDescription ?? "No error description")")
        }
    }
    ```

    `onMFARequestChallengeError`関数は、MFA 要求チャレンジ中に発生したエラーを処理します。
2. `submitChallenge(challenge)` メソッドのエラーを処理するには、次のコード スニペットを使用します。

    ```swift
    func onMFASubmitChallengeError(error: MFASubmitChallengeError, newState: MFARequiredState?) {
        if error.isInvalidChallenge {
            // Inform the user that the submitted code was incorrect and ask for a new code to be supplied
            let userSuppliedCode = retrieveNewCode()
            newState.submitChallenge(challenge: userSuppliedCode, delegate: self)
        } else {
            showResultText("Unexpected error occurred: \(error.errorDescription ?? "No error description")")
        }
    }
    ```

    `onMFASubmitChallengeError`関数は、MFA チャレンジの送信中に発生したエラーを処理します。 送信されたチャレンジが無効な場合は、 `newState` パラメーターを使用して新しいチャレンジを送信できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-native-authentication-ios-swift-sms-strong-authentication-method"} -->
## ネイティブ認証を使用して iOS および macOS アプリに電話 SMS の強力な認証方法の登録を追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-swift-sms-strong-authentication-method
- Service: identity-platform / external
- Article date: 2026-01-27
- Summary: クライアント機能の構成や登録チャレンジの処理など、ネイティブ認証を使用して、iOS または macOS アプリで MFA 対応ユーザーの強力な認証方法として電話 SMS を登録する方法について説明します。

このチュートリアルでは、ネイティブ認証を使用して、iOS/macOS アプリに SMS の強力な認証方法の登録を実装する方法について説明します。 多要素認証 (MFA) が有効なユーザーには、少なくとも 1 つの強力な認証が必須です。 現在、強力な認証方法として電子メールと SMS ワンタイム パスコードがサポートされています。

このチュートリアルでは、以下の内容を学習します。

- MFA 対応ユーザーの強力な認証方法として SMS を登録します。

### [前提条件]

1. 「[チュートリアル: ネイティブ認証を使用して iOS アプリにサインインとサインアウトを追加する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-macos-sign-in-sign-out)」の手順を完了します。
2. 顧客に MFA を適用するには、「 [iOS/macOS アプリに SMS ワンタイム パスコード MFA を追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers) 」の手順に従って、SMS ワンタイム パスコード MFA をサインイン フローに追加します。 電子メールと SMS ワンタイム パスコード MFA をサポートしています。
3. 強力な認証方法の登録の実装を調べる場合は、開始する前に、[Code サンプル](https://github.com/Azure-Samples/ms-identity-ciam-native-auth-ios-sample)をご確認ください。

### 強力な認証方法の登録用にクライアント機能を構成する

注

現在、SMS ワンタイムパスコードを権限形式で使用する際の既知の問題があります。 `<tenantName>.ciamlogin.com/<tenantName>.onmicrosoft.com` そのため、次の形式を使用する必要があります。 `<tenantName>.ciamlogin.com/<tenantID>`

ネイティブ認証クライアントを初期化するときに強力な認証方法の登録を要求するように、capabilities パラメーターを使用してクライアントを構成します。

```swift
let tenantId = "00000000-0000-0000-0000-000000000000" //Replace with your tenant ID
let authority = try MSALCIAMAuthority(url: URL(string: "https://\(Configuration.tenantSubdomain).ciamlogin.com/\(tenantId)")!)
let config = MSALNativeAuthPublicClientApplicationConfig(clientId: Configuration.clientId, authority: authority, challengeTypes: [.OOB, .password])
config.capabilities = [.registrationRequired]
nativeAuth = try MSALNativeAuthPublicClientApplication(nativeAuthConfiguration: config)
```

### SMS ワンタイム パスコードを強力な認証方法として登録する

MFA 対応ユーザーの SMS ワンタイム パスコードを登録するには、強力な認証方法として電話番号を指定する必要があります。 このプロセスは、MFA が初めて必要なときにのみトリガーされ、ユーザーには強力な認証方法が登録されていません。 その後、ユーザーが自分の電話番号を確認するためのワンタイム パスコードを含む SMS を送信する必要があります。 ユーザーが有効なワンタイム パスコードを入力すると、SDK はサインイン プロセスを完了します。

MFA 対応ユーザーの強力な認証方法 (電話 SMS パスコード) を登録するには、次の操作を行う必要があります。

1. 「ユーザーインターフェース (UI) を作成して、以下を行う：」

    - サインインには強力な認証方法の登録が必要であることをユーザーに知らしてください (省略可能)。
    - 強力な認証方法として登録する電話番号を収集します (省略可能)。 ユーザーは、強力な認証方法として登録するために電話番号を指定する必要があります。
    - ユーザーから SMS ワンタイム パスコードを収集して、2 要素認証を実行します。
    - ワンタイム パスコードを再送信します (推奨)。
2. `onSignInStrongAuthMethodRegistration` プロトコルの一部として、`SignInStartDelegate` メソッドを実装します。

    ```swift
     extension ViewController: SignInStartDelegate {
    
         // previous code omitted
         func onSignInStrongAuthMethodRegistration(authMethods: [MSALAuthMethod], newState: RegisterStrongAuthState) {
    
             // inform the user that registration is required
             let alert = UIAlertController(
                 title: "Missing strong authentication method", 
                 message: "Registration of strong authentication method is required. Do you want to proceed with registration?", 
                 preferredStyle: .alert
             )
    
             // You can select the first authMethod which has a channelgeChannel of type email
             // Or use "actionResult.authMethods" to let the user pick the authentication method to use.
             var authMethod = actionResult.authMethods.first { $0.channelTargetType.isEmailType }
    
             alert.addAction(UIAlertAction(title: "OK", style: .default, handler: { _ in
                 if verificationContact.isEmpty {
                     verificationContact = "<phone number>" // format should be "<+prefix phone_number>"
                 }
                 let parameter = MSALNativeAuthChallengeAuthMethodParameters(authMethod: authMethod, verificationContact: verificationContact)
                 newState.challengeAuthMethod(parameters: parameter, delegate: self)
             }))
    
             alert.addAction(UIAlertAction(title: "Cancel", style: .cancel, handler: { _ in
                 self.resultTextView.text = "Strong authentication method registration is required"
             }))
    
             present(alert, animated: true)
     }
    ```

    `signIn(username:password:delegate)` によってデリゲート メソッドが呼び出されます。 `onSignInStrongAuthMethodRegistration(authMethods:newState)`メソッドは、強力な認証方法の登録が必要なシナリオを処理します。 登録を続行するかどうかを確認するアラートをユーザーに提示できます。 ユーザーが **[OK] を**選択した場合は、 `newState.challengeAuthMethod(parameters:delegate)`を呼び出して MFA チャレンジを要求します。 `newState.challengeAuthMethod(parameters:delegate)`メソッドは、強力な認証方法をサーバーに送信して登録します。

    登録の必要性についてユーザーに通知しない場合は、 `newState.challengeAuthMethod(parameters:delegate)`を直接呼び出すことで、ユーザーの操作をバイパスできます。 `challengeAuthMethod`はパラメーターとデリゲート インスタンスを受け取ります。`RegisterStrongAuthChallengeDelegate` プロトコルで必要なメソッドを実装する必要があります。
3. `RegisterStrongAuthChallengeDelegate` プロトコルを実装します。

    ```swift
    extension ViewController: RegisterStrongAuthChallengeDelegate {
        func onRegisterStrongAuthChallengeError(
            error: RegisterStrongAuthChallengeError,
            newState: RegisterStrongAuthState?
        ) {
            resultTextView.text = "Error registering strong authentication method: \(error.errorDescription ?? "no description")"
        }
    
        func onRegisterStrongAuthVerificationRequired(
            result: MSALNativeAuthRegisterStrongAuthVerificationRequiredResult
        ) {
            let newState = result.newState
            if verificationContact.isEmpty {
                verificationContact = "<phone number>" // format should be "<+prefix phone_number>"
            }
            let parameter = MSALNativeAuthChallengeAuthMethodParameters(authMethod: authMethod)
            newState.challengeAuthMethod(parameters: parameter, delegate: self)
        }
    }
    ```

    最も一般的なシナリオでは、アプリが電話番号に送信された SMS ワンタイム パスコードを送信することを SDK が予期していることを示す `onRegisterStrongAuthVerificationRequired(result)` の呼び出しを受け取ります。 提出が完了したら、次の`challengeAuthMethod`のように`newState.challengeAuthMethod(parameters:delegate)` メソッドを呼び出すことができます。 `challengeAuthMethod`はデリゲート パラメーターを受け取り、`RegisterStrongAuthSubmitChallengeDelegate` プロトコルで必要なメソッドを実装する必要があります。
4. `RegisterStrongAuthSubmitChallengeDelegate` プロトコルを実装します。

    ```swift
    extension ViewController: RegisterStrongAuthSubmitChallengeDelegate {
         func onRegisterStrongAuthSubmitChallengeError(
            error: RegisterStrongAuthSubmitChallengeError,
            newState: RegisterStrongAuthVerificationRequiredState?
        ) {
            resultTextView.text = "Error submitting challenge: \(error.errorDescription ?? "no description")"
            newState.submitChallenge(challenge: "newCode", delegate: self)
        }
    
        func onSignInCompleted(result: MSALNativeAuthUserAccountResult) {
            print("Signed in as: \(result.account.username ?? "unknown user")")
        }
    }
    ```

    `RegisterStrongAuthSubmitChallengeDelegate` プロトコルを使用すると、検証コードの送信を処理できます。 `onRegisterStrongAuthSubmitChallengeError`による無効なコードなど、エラーを管理するメソッドを提供し、再送信を許可します。 検証が成功すると、 `onSignInCompleted` メソッドが呼び出され、サインイン プロセスが完了します。
<!-- /MSL-PAGE -->
