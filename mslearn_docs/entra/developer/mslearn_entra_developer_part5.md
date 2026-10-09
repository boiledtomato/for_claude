# Microsoft Learn — Microsoft Entra / 開発者向け (Identity Platform・MSAL・Microsoft.Identity.Web) (part 5)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 45

---

<!-- MSL-PAGE {"url":"entra/identity-platform/reference-saml-tokens"} -->
## SAML 2.0 トークン要求リファレンス - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-saml-tokens
- Service: identity-platform
- Article date: 2023-01-19
- Summary: MICROSOFT ID プラットフォームによって発行された SAML 2.0 トークンに含まれる要求 (JWT に相当するものを含む) の詳細を含む要求リファレンス。

Microsoft ID プラットフォームは、各認証フローの処理で、いくつかの種類のセキュリティ トークンを出力します。 このドキュメントでは、SAML 2.0 トークンの形式、セキュリティ特性、および内容について説明します。

### SAML トークン内の要求

| 名前 | 同等の JWT 要求 | 説明 | 例 |
| --- | --- | --- | --- |
| 聴衆 | `aud` | トークンの目的の受信者。 トークンを受け取るアプリケーションは、対象ユーザーの値が正しいことを確認し、別の対象ユーザー向けのトークンを拒否する必要があります。 | `<AudienceRestriction>``<Audience>``https://contoso.com``</Audience>``</AudienceRestriction>` |
| 認証インスタント |  | 認証が行われた日時を記録します。 | `<AuthnStatement AuthnInstant="2011-12-29T05:35:22.000Z">` |
| 認証方法 | `amr` | トークンのサブジェクトが認証された方法を識別します。 | `<AuthnContextClassRef>``http://schemas.microsoft.com/ws/2008/06/identity/claims/authenticationmethod/password``</AuthnContextClassRef>` |
| 名前 | `given_name` | Microsoft Entra ユーザー オブジェクトに設定されている、ユーザーの最初または "指定された" 名前を提供します。 | `<Attribute Name="http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname">``<AttributeValue>Frank<AttributeValue>` |
| グループ | `groups` | サブジェクトのグループ メンバーシップを表すオブジェクト ID を提供します。 これらの値は一意であり (オブジェクト ID を参照)、リソースへのアクセス許可の適用など、アクセスの管理に安全に使用できます。 グループ要求に含まれるグループは、アプリケーション マニフェストの "groupMembershipClaims" プロパティを使用して、アプリケーションごとに構成されます。 値が null の場合、すべてのグループが除外され、値 "SecurityGroup" にはディレクトリ ロールと Active Directory セキュリティ グループ メンバーシップが含まれます。値 "All" には、セキュリティ グループと Microsoft 365 配布リストの両方が含まれます。 **注**:  ユーザーが参加しているグループの数が制限を超えた場合 (SAML の場合は 150、JWT の場合は 200)、超過分の要求は、ユーザーのグループの一覧を含む Graph エンドポイントを指す要求ソースを追加します。 | `<Attribute Name="http://schemas.microsoft.com/ws/2008/06/identity/claims/groups">``<AttributeValueaaaaaaaa-0000-1111-2222-bbbbbbbbbbbb</AttributeValue>` |
| グループ超過インジケーター | `groups:src1` | 長さ制限はありませんが、トークンに対して大きすぎるトークン要求の場合は、ユーザーの完全なグループ リストへのリンクが含まれます。 SAML の場合、これは `groups` 要求の代わりに新しい要求として追加されます。 **注**:  Azure AD Graph API は、Microsoft Graph API に置き換えられます。 同等のエンドポイントの詳細については、 [user: getMemberObjects](https://learn.microsoft.com/ja-jp/graph/api/directoryobject-getmemberobjects) を参照してください。 | `<Attribute Name=" http://schemas.microsoft.com/claims/groups.link">``<AttributeValue>https://graph.windows.net/{tenantID}/users/{userID}/getMemberObjects<AttributeValue>` |
| ID プロバイダー | `idp` | トークンのサブジェクトを認証した ID プロバイダーを記録します。 ユーザー アカウントが発行者とは異なるテナントにある場合を除き、この値は発行者要求の値と同じです。 | `<Attribute Name=" http://schemas.microsoft.com/identity/claims/identityprovider">``<AttributeValue>https://sts.windows.net/aaaabbbb-0000-cccc-1111-dddd2222eeee/<AttributeValue>` |
| IssuedAt | `iat` | トークンが発行された時刻を格納します。 トークンの鮮度を測定するためによく使用されます。 | `<Assertion ID="_d5ec7a9b-8d8f-4b44-8c94-9812612142be" IssueInstant="2014-01-06T20:20:23.085Z" Version="2.0" xmlns="urn:oasis:names:tc:SAML:2.0:assertion">` |
| 発行者 | `iss` | トークンを構築して返すセキュリティ トークン サービス (STS) を識別します。 Microsoft Entra ID が返すトークンでは、発行者は sts.windows.net。 発行者要求値の GUID は、Microsoft Entra ディレクトリのテナント ID です。 テナント ID は、ディレクトリの不変で信頼性の高い識別子です。 | `<Issuer>https://sts.windows.net/aaaabbbb-0000-cccc-1111-dddd2222eeee/</Issuer>` |
| 姓 | `family_name` | Microsoft Entra ユーザー オブジェクトで定義されているユーザーの姓、姓、またはファミリ名を提供します。 | `<Attribute Name=" http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname">``<AttributeValue>Miller<AttributeValue>` |
| 名前 | `unique_name` | トークンのサブジェクトを識別する、人が判読できる値を提供します。 この値はテナント内で一意であるとは限らず、表示目的でのみ使用するように設計されています。 | `<Attribute Name="http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name">``<AttributeValue>frankm@contoso.com<AttributeValue>` |
| オブジェクト ID | `oid` | Microsoft Entra ID のオブジェクトの一意識別子を格納します。 この値は変更不可で、再割り当ても再利用もできません。 オブジェクト ID を使用して、Microsoft Entra ID に対するクエリ内のオブジェクトを識別します。 | `<Attribute Name="http://schemas.microsoft.com/identity/claims/objectidentifier">``<AttributeValue>bbbbbbbb-1111-2222-3333-cccccccccccc<AttributeValue>` |
| 役割 | `roles` | グループ メンバーシップを通じて直接および間接的にサブジェクトに付与されたすべてのアプリケーション ロールを表し、ロールベースのアクセス制御を適用するために使用できます。 アプリケーション ロールは、アプリケーション マニフェストの `appRoles` プロパティを使用して、アプリケーションごとに定義されます。 各アプリケーション ロールの `value` プロパティは、ロール要求に表示される値です。 | `<Attribute Name="http://schemas.microsoft.com/ws/2008/06/identity/claims/role">` |
| サブジェクト | `sub` | トークンが情報をアサートするプリンシパル (アプリケーションのユーザーなど) を識別します。 サブジェクトは、Microsoft Entra ID が発行する SAML トークンに常に存在します。 [NameID 形式](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization#nameid-format)によっては、*この値を*使用して汎用承認システムのサブジェクトを識別できます (承認に[電子メール アドレスを使用する場合のリスク](https://msrc.microsoft.com/blog/2023/06/potential-risk-of-privilege-escalation-in-azure-ad-applications/)を参照してください)。`SubjectConfirmation` は要求ではありません。 トークンのサブジェクトの検証方法について説明します。 `Bearer` は、トークンの所有によってサブジェクトが確認されることを示します。 | `<Subject>``<NameID>S40rgb3XjhFTv6EQTETkEzcgVmToHKRkZUIsJlmLdVc</NameID>``<SubjectConfirmation Method="urn:oasis:names:tc:SAML:2.0:cm:bearer" />``</Subject>` |
| テナント ID | `tid` | トークンを発行したディレクトリ テナントを識別する、不変で再利用可能でない識別子。 この値を使用して、マルチテナント アプリケーションのテナント固有のディレクトリ リソースにアクセスできます。 たとえば、この値を使用して、Graph API の呼び出しでテナントを識別できます。 | `<Attribute Name="http://schemas.microsoft.com/identity/claims/tenantid">``<AttributeValue>aaaabbbb-0000-cccc-1111-dddd2222eeee<AttributeValue>` |
| トークンの有効期間 | `nbf`、`exp` | トークンが有効な時間間隔を定義します。 トークンを検証するサービスでは、現在の日付がトークンの有効期間内にあることを確認する必要があります。それ以外の場合は、トークンを拒否する必要があります。 このサービスでは、Microsoft Entra ID とサービスの間のクロック時間 ("時間スキュー") の違いを考慮するために、トークンの有効期間の範囲を超えて最大 5 分間許可される場合があります。 | `<Conditions``NotBefore="2013-03-18T21:32:51.261Z"``NotOnOrAfter="2013-03-18T22:32:51.261Z"``>` |

### SAML トークンのサンプル

これは、一般的な SAML トークンのサンプルです。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<t:RequestSecurityTokenResponse xmlns:t="http://schemas.xmlsoap.org/ws/2005/02/trust">
    <t:Lifetime>
        <wsu:Created xmlns:wsu="https://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd">2014-12-24T05:15:47.060Z</wsu:Created>
        <wsu:Expires xmlns:wsu="https://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd">2014-12-24T06:15:47.060Z</wsu:Expires>
    </t:Lifetime>
    <wsp:AppliesTo xmlns:wsp="http://schemas.xmlsoap.org/ws/2004/09/policy">
        <EndpointReference xmlns="https://www.w3.org/2005/08/addressing">
            <Address>https://contoso.onmicrosoft.com/MyWebApp</Address>
        </EndpointReference>
    </wsp:AppliesTo>
    <t:RequestedSecurityToken>
        <Assertion xmlns="urn:oasis:names:tc:SAML:2.0:assertion" ID="_aaaaaaaa-0b0b-1c1c-2d2d-333333333333" IssueInstant="2014-12-24T05:20:47.060Z" Version="2.0">
            <Issuer>https://sts.windows.net/aaaabbbb-0000-cccc-1111-dddd2222eeee/</Issuer>
            <ds:Signature xmlns:ds="https://www.w3.org/2000/09/xmldsig#">
                <ds:SignedInfo>
                    <ds:CanonicalizationMethod Algorithm="https://www.w3.org/2001/10/xml-exc-c14n#" />
                    <ds:SignatureMethod Algorithm="https://www.w3.org/2001/04/xmldsig-more#rsa-sha256" />
                    <ds:Reference URI="#_aaaaaaaa-0b0b-1c1c-2d2d-333333333333">
                        <ds:Transforms>
                            <ds:Transform Algorithm="https://www.w3.org/2000/09/xmldsig#enveloped-signature" />
                            <ds:Transform Algorithm="https://www.w3.org/2001/10/xml-exc-c14n#" />
                        </ds:Transforms>
                        <ds:DigestMethod Algorithm="https://www.w3.org/2001/04/xmlenc#sha256" />
                        <ds:DigestValue>E3fH4iJ5kL6mN7oP8qR9sT0uV1wX2y/nDY=</ds:DigestValue>
                    </ds:Reference>
                </ds:SignedInfo>
                <ds:SignatureValue>aB1cD2eF3gH4i...J5kL6-mN7oP8qR==</ds:SignatureValue>
                <KeyInfo xmlns="https://www.w3.org/2000/09/xmldsig#">
                    <X509Data>
                        <X509Certificate>C2dE3fH4iJ5kL6mN7oP8qR9sT0uV1w</X509Certificate>
                    </X509Data>
                </KeyInfo>
            </ds:Signature>
            <Subject>
                <NameID Format="urn:oasis:names:tc:SAML:2.0:nameid-format:persistent">m_H3naDei2LNxUmEcWd0BZlNi_jVET1pMLR6iQSuYmo</NameID>
                <SubjectConfirmation Method="urn:oasis:names:tc:SAML:2.0:cm:bearer" />
            </Subject>
            <Conditions NotBefore="2014-12-24T05:15:47.060Z" NotOnOrAfter="2014-12-24T06:15:47.060Z">
                <AudienceRestriction>
                    <Audience>https://contoso.onmicrosoft.com/MyWebApp</Audience>
                </AudienceRestriction>
            </Conditions>
            <AttributeStatement>
                <Attribute Name="http://schemas.microsoft.com/identity/claims/objectidentifier">
                    <AttributeValue>aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb</AttributeValue>
                </Attribute>
                <Attribute Name="http://schemas.microsoft.com/identity/claims/tenantid">
                    <AttributeValue>aaaabbbb-0000-cccc-1111-dddd2222eeee</AttributeValue>
                </Attribute>
                <Attribute Name="http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name">
                    <AttributeValue>sample.admin@contoso.onmicrosoft.com</AttributeValue>
                </Attribute>
                <Attribute Name="http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname">
                    <AttributeValue>Admin</AttributeValue>
                </Attribute>
                <Attribute Name="http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname">
                    <AttributeValue>Sample</AttributeValue>
                </Attribute>
                <Attribute Name="http://schemas.microsoft.com/ws/2008/06/identity/claims/groups">
                    <AttributeValue>5581e43f-6096-41d4-8ffa-04e560bab39d</AttributeValue>
                    <AttributeValue>07dd8a89-bf6d-4e81-8844-230b77145381</AttributeValue>
                    <AttributeValue>0e129f4g-6b0a-4944-982d-f776000632af</AttributeValue>
                    <AttributeValue>3ee07328-52ef-4739-a89b-109708c22fb5</AttributeValue>
                    <AttributeValue>329k14b3-1851-4b94-947f-9a4dacb595f4</AttributeValue>
                    <AttributeValue>6e32c650-9b0a-4491-b429-6c60d2ca9a42</AttributeValue>
                    <AttributeValue>f3a169a7-9a58-4e8f-9d47-b70029v07424</AttributeValue>
                    <AttributeValue>8e2c86b2-b1ad-476d-9574-544d155aa6ff</AttributeValue>
                    <AttributeValue>1bf80264-ff24-4866-b22c-6212e5b9a847</AttributeValue>
                    <AttributeValue>4075f9c3-072d-4c32-b542-03e6bc678f3e</AttributeValue>
                    <AttributeValue>76f80527-f2cd-46f4-8c52-8jvd8bc749b1</AttributeValue>
                    <AttributeValue>0ba31460-44d0-42b5-b90c-47b3fcc48e35</AttributeValue>
                    <AttributeValue>edd41703-8652-4948-94a7-2d917bba7667</AttributeValue>
                </Attribute>
                <Attribute Name="http://schemas.microsoft.com/identity/claims/identityprovider">
                    <AttributeValue>https://sts.windows.net/aaaabbbb-0000-cccc-1111-dddd2222eeee/</AttributeValue>
                </Attribute>
            </AttributeStatement>
            <AuthnStatement AuthnInstant="2014-12-23T18:51:11.000Z">
                <AuthnContext>
                    <AuthnContextClassRef>urn:oasis:names:tc:SAML:2.0:ac:classes:Password</AuthnContextClassRef>
                </AuthnContext>
            </AuthnStatement>
        </Assertion>
    </t:RequestedSecurityToken>
    <t:RequestedAttachedReference>
        <SecurityTokenReference xmlns="https://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" xmlns:d3p1="https://docs.oasis-open.org/wss/oasis-wss-wssecurity-secext-1.1.xsd" d3p1:TokenType="http://docs.oasis-open.org/wss/oasis-wss-saml-token-profile-1.1#SAMLV2.0">
            <KeyIdentifier ValueType="http://docs.oasis-open.org/wss/oasis-wss-saml-token-profile-1.1#SAMLID">_aaaaaaaa-0b0b-1c1c-2d2d-333333333333</KeyIdentifier>
        </SecurityTokenReference>
    </t:RequestedAttachedReference>
    <t:RequestedUnattachedReference>
        <SecurityTokenReference xmlns="https://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" xmlns:d3p1="https://docs.oasis-open.org/wss/oasis-wss-wssecurity-secext-1.1.xsd" d3p1:TokenType="http://docs.oasis-open.org/wss/oasis-wss-saml-token-profile-1.1#SAMLV2.0">
            <KeyIdentifier ValueType="http://docs.oasis-open.org/wss/oasis-wss-saml-token-profile-1.1#SAMLID">_aaaaaaaa-0b0b-1c1c-2d2d-333333333333</KeyIdentifier>
        </SecurityTokenReference>
    </t:RequestedUnattachedReference>
    <t:TokenType>http://docs.oasis-open.org/wss/oasis-wss-saml-token-profile-1.1#SAMLV2.0</t:TokenType>
    <t:RequestType>http://schemas.xmlsoap.org/ws/2005/02/trust/Issue</t:RequestType>
    <t:KeyType>http://schemas.xmlsoap.org/ws/2005/05/identity/NoProofKey</t:KeyType>
</t:RequestSecurityTokenResponse>
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/reference-third-party-cookies-spas"} -->
## ブラウザーでサードパーティの Cookie ブロックを処理する方法 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-third-party-cookies-spas
- Service: identity-platform
- Article date: 2022-03-14
- Summary: サードパーティの Cookie が許可されなくなった場合のシングルページ アプリ (SPA) 認証。

多くのブラウザーでは、 *サードパーティの cookie*、ブラウザーのアドレスバーに表示されているドメイン以外のドメインに対する要求の cookie がブロックされています。 これらのクッキーは、*クロスドメイン Cookie* とも呼ばれます。 このブロックにより、暗黙的なフローが中断され、ユーザーを正常にサインインさせるための新しい認証パターンが必要になります。 Microsoft ID プラットフォームでは、サードパーティの Cookie がブロックされている場合にユーザーのサインインを維持するために、Proof Key for Code Exchange (PKCE) と更新トークンで認証コード フローを使用します。 この承認コード フローと Proof Key for Code Exchange アプローチは、暗黙的なフローよりも推奨されます。

### インテリジェント追跡保護 (ITP) とプライバシー サンドボックスとは

Apple Safari では、[Intelligent Tracking Protection](https://webkit.org/tracking-prevention-policy/) (*ITP*) と呼ばれるプライバシー保護機能が既定でオンになっています。 Chrome には、[プライバシー サンドボックス](https://developers.google.com/privacy-sandbox/overview)というブラウザー プライバシー イニシアティブがあります。 これらのイニシアティブには、ブラウザーによって異なる多数のブラウザー プライバシーに関する取り組みがあり、タイムラインも異なります。 どちらの取り組みでも、ドメインをまたがる要求の "サードパーティ" Cookie をブロックしており、Safari と Brave は既定でサードパーティ Cookie をブロックしています。 最近、Chrome は[サードパーティ Cookie の既定でのブロック](https://privacysandbox.com/open-web/#the-privacy-sandbox-timeline)を開始することを発表しました。 プライバシー サンドボックスには、[パーティション分割ストレージ](https://developers.google.com/privacy-sandbox/3pcd/storage-partitioning)への変更とサードパーティ Cookie のブロックが含まれています。

一般的な形式のユーザー追跡は、バックグラウンドで iframe をサードパーティ サイトに読み込み、Cookie を使用してインターネット経由でユーザーを関連付けることによって行われます。 残念ながら、このパターンは、シングルページ アプリ (SPA) で[暗黙的なフロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-implicit-grant-flow)を実装するための標準的な方法でもあります。 ユーザーのプライバシーを保護するためにサードパーティの Cookie をブロックするブラウザーは、SPA の機能をブロックすることもできます。 サードパーティの Cookie のブロックと、それに関連するセキュリティ リスクのため、SPA での暗黙的なフローの使用は推奨されなくなりました。

この記事で概説するソリューションは、これらすべてのブラウザー、またはサードパーティの Cookie がブロックされるあらゆる場所で機能します。

### ソリューションの概要

SPA でユーザーの認証を続行するには、アプリ開発者が[認可コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)を使用する必要があります。 認可コード フローでは、ID プロバイダーによってコードが発行され、SPA でアクセス トークンと更新トークンのコードが引き換えられます。 アプリで新しいトークンが必要な場合は、[更新トークンのフロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow#refresh-the-access-token)を使って新しいトークンを取得できます。 JavaScript v2.0 以上用の Microsoft Authentication Library (MSAL) では、SPA の認可コード フローを実装し、マイナー更新を行って、MSAL.js 1.x の代わりとして、そのまま使用することができます。 SPA を暗黙的なコード フローから認証コード フローに移行するには、[移行ガイド](https://learn.microsoft.com/ja-jp/entra/identity-platform/migrate-spa-implicit-to-auth-code)を参照してください。

Microsoft ID プラットフォームの場合、SPA とネイティブ クライアントでは以下の同様のプロトコル ガイダンスに従います。

- [PKCE コード チャレンジ](https://tools.ietf.org/html/rfc7636)を使用する
    - Microsoft ID プラットフォーム上の SPA では、PKCE が "*必須*" となります。 ネイティブおよび Confidential クライアントでは、PKCE が "*推奨*" されます。
- クライアント シークレットを使用しない

SPA にはさらに 2 つの制約があります。

- ログイン エンドポイントで CORS を有効にするには、[リダイレクト URI が `spa` という種類としてマークされている必要があります](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow#redirect-uris-for-single-page-apps-spas)。
- `spa` リダイレクト URI に認可コード フローを通じて発行される更新トークンには、90 日の有効期間ではなく、24 時間の有効期間があります。

[Image: シングル ページ アプリとセキュリティ トークン サービス エンドポイントの間の OAuth 2 認証コード フローを示す図。]

### パフォーマンスと UX への影響

暗黙的なフローを使用する一部のアプリケーションでは、`prompt=none` を使ってログイン iframe を開くことで、リダイレクトすることなくサインインが試行されます。 ほとんどのブラウザーでは、この要求で、現在サインインしているユーザーのトークンを使用して応答します (同意が得られていることが前提)。 このパターンは、アプリケーションでユーザーをサインインさせるためのフル ページ リダイレクトが不要であり、パフォーマンスとユーザー エクスペリエンスが向上することを意味していました。この場合、ユーザーは Web ページにアクセスし、既にサインインしています。 サードパーティの Cookie がブロックされている場合、iframe の `prompt=none` はオプションではなくなるため、アプリケーションでは、サインイン パターンを調整して認可コードを発行する必要があります。

サードパーティの Cookie がない場合、サインインを実現するには、次の 2 つの方法があります。

- **フル ページ リダイレクト**
    - SPA の最初の読み込み時に、セッションがまだ存在しない場合 (またはセッションの有効期限が切れている場合) に、ユーザーをサインイン ページにリダイレクトします。 ユーザーのブラウザーではログイン ページにアクセスし、ユーザー セッションを含む Cookie を提示してから、フラグメントのコードとトークンを使用してアプリケーションに再びリダイレクトします。
    - このリダイレクトによって、SPA が 2 回読み込まれることになります。 SPA のキャッシュのベスト プラクティスに従って、アプリが 2 度にわたって完全にダウンロードされないようにします。
    - アプリで JavaScript ペイロードを完全にアンパックして実行する前に、ログイン セッションを確認し、ログイン ページにリダイレクトするアプリではプリロード シーケンスを使用することを検討してください。
- **ポップアップ**
    - フル ページ リダイレクトのユーザー エクスペリエンス (UX) がアプリケーションで機能しない場合は、ポップアップを使用して認証を扱うことを検討してください。
    - 認証後にポップアップでアプリケーションへのリダイレクトが完了すると、リダイレクト ハンドラーのコードによって、アプリケーションで使用するためにローカル ストレージに認証コードとトークンが格納されます。 MSAL.js では、ほとんどのライブラリの場合と同様に、認証用のポップアップがサポートされています。
    - ブラウザーではポップアップのサポートが減少しているため、最も信頼性の高いオプションではない場合があります。 ブラウザーの要件を満たすには、ポップアップを作成する前にユーザーと SPA とのやり取りが必要になる場合があります。

Apple では、元のウィンドウからサードパーティの Cookie にアクセスできるようにするための一時的な互換性修正プログラムとして、[ポップアップ手法について説明](https://webkit.org/blog/8311/intelligent-tracking-prevention-2-0/)しています。 Apple は今後、アクセス許可のこの移転をなくす可能性がありますが、このガイダンスには影響しません。

ここでは、ログイン ページへのファースト パーティのナビゲーションとしてポップアップが使用されているため、セッションが検出され、認可コードを提供できます。 そのため、今後も引き続きご利用いただけるはずです。

開発者は、サード パーティの Cookie がブロックされたときに `prompt=none` エラーの割合が高くなることを期待して、 を引き続き使用できます。 サイレント トークンの取得中にエラーが発生した場合は、常に [対話型メソッド フォールバック](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-js-prompt-behavior)を使用することをおすすめします。

#### Iframe の使用

Web アプリ の一般的なパターンでは、iframe を使用して 1 つのアプリを別のアプリケーションに埋め込みます。最上位レベルのフレーム ハンドルはユーザーを認証し、iframe でホストされるアプリケーションは、ユーザーがサインインしていることを信頼し、暗黙的なフローを使用してトークンを警告なしにフェッチします。 ただし、ブラウザーでサードパーティの Cookie が有効になっているかブロックされているかに関係なく、この想定にはいくつかの注意事項があります。

サードパーティの Cookie がブロックされている場合、サイレント トークンの取得は機能しなくなりました。つまり、iframe に埋め込まれたアプリケーションでは、埋め込みフレーム内のログイン ページに移動できないため、ポップアップを使用してユーザーのセッションにアクセスするように切り替える必要があります。

親アプリから iframed アプリにユーザー (アカウント) ヒントを渡すことにより、同じオリジン *と* クロスオリジンのJavaScript スクリプト API アクセスで、iframeのアプリ と親アプリの間でシングルサインオンを実現できます。 詳細については、「GitHub の MSAL.js リポジトリの[iframアプリでの MSAL.js の使用](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/iframe-usage.md)」を参照してください。

### ブラウザーでの更新トークンのセキュリティへの影響

クロスサイト スクリプティング (XSS) 攻撃または JS パッケージの侵害により、更新トークンが盗まれ、有効期限が切れるか失効するまでリモートで使用される可能性があります。 アプリケーション開発者には、クロスサイト スクリプティングに対するアプリケーションのリスクを軽減する責任があります。 盗まれた更新トークンのリスクを最小限に抑えるために、SPA には 24 時間のみ有効なトークンが発行されます。 24 時間後、アプリでは、ログイン ページにアクセスするために最上位フレームを介して新しい認証コードを取得する必要があります。

この有効期間が制限された更新トークンのパターンは、セキュリティと低下した UX のバランスを取るために選ばれました。 更新トークンやサードパーティの Cookie がないと、新しいトークンまたは追加のトークンが必要になったときに、認可コード フロー ([OAuth セキュリティのベスト プラクティス ドラフト](https://tools.ietf.org/html/draft-ietf-oauth-security-topics-14)で推奨) が煩雑になります。 トークンが期限切れになるたび (Microsoft ID プラットフォームのトークンの場合、通常は 1 時間ごと) に、すべてのシングル トークンでフル ページ リダイレクトまたはポップアップが必要になります。

### ユーザーの種類固有の軽減策

すべてのユーザーとアプリケーションがサードパーティの Cookie による影響を一様に受けるわけではありません。 アーキテクチャまたはデバイスの管理により、サード パーティの Cookie なしでトークンを更新するためのサイレント呼び出しを実行できるシナリオがいくつかあります。

*マネージド エンタープライズ デバイス*のシナリオでは、特定のブラウザーとプラットフォームの組み合わせで、[デバイスの条件付きアクセス](https://learn.microsoft.com/ja-jp/azure/active-directory/conditional-access/concept-conditional-access-conditions#supported-browsers)がサポートされます。 認証状態はブラウザーではなくデバイスから取得できるため、デバイス ID を適用することで、サードパーティの Cookie の必要性を最小限に抑えることができます。

*Azure AD B2C アプリケーション*のシナリオの場合、お客様はアプリケーションのドメインに合わせて[カスタム ログイン ドメイン](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/custom-domain?pivots=b2c-user-flow)を設定できます。 このシナリオでは、Cookie が同じドメイン (`login.contoso.com` から `app.contoso.com` など) に残るため、ブラウザーはサードパーティの Cookie をブロックしません。

### サード パーティの Cookie のないフロント チャネル ログアウトに関する制限事項

SPA からユーザーをサインアウトする場合、MSAL.js では、[ポップアップ メソッドまたはリダイレクト ログアウト メソッド](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-sign-in?tabs=javascript2#sign-out-with-a-pop-up-window)を使用することをおすすめします。 これにより、サーバーとブラウザー ストレージの認証セッションがクリアされますが、サードパーティの Cookie にアクセスしないと、一部のフェデレーション アプリケーションでサインアウトが同時に表示されないリスクが生じます。 これは、[OpenID Front-Channel Logout 1.0 仕様](https://openid.net/specs/openid-connect-frontchannel-1_0.html#ThirdPartyContent)の既知の制限です。 これがユーザーにとって意味することは、同じユーザーに対して他のアプリケーションのアクセス トークンが既にある場合、有効期限が切れるまでは引き続き有効であるということです。 ユーザーがタブ A のアプリケーション A からログアウトしたとしても、アクセス トークンの残りの有効期間中は、タブ B のアプリケーション B には引き続きログイン済みと表示されます。 アプリケーション B のトークンの有効期限が切れ、新しいトークンを取得するためにサーバーへの呼び出しが行われると、アプリケーション B はサーバーからセッションの有効期限が切れたという応答を受け取り、ユーザーに認証を求めます。

Microsoft のサインアウト ページと[インターネット プライバシーのベスト プラクティス](https://support.microsoft.com/en-us/windows/protect-your-privacy-on-the-internet-ffe36513-e208-7532-6f95-a3b1c8760dfa)では、ユーザーがアプリケーションからログアウトした後にすべてのブラウザー ウィンドウを閉じることをおすすめしています。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/reference-v2-libraries"} -->
## Microsoft ID プラットフォームの認証ライブラリ - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries
- Service: identity-platform
- Article date: 2022-10-28
- Summary: Microsoft ID プラットフォームと互換性のあるクライアント ライブラリとミドルウェアの一覧。 これらのライブラリを使用して、ユーザーのサインイン (認証) と保護された Web API アクセス (認可) のサポートをアプリケーションに追加します。

次の表は、いくつかのアプリケーションの種類に対する Microsoft 認証ライブラリのサポートを示しています。 これには、ライブラリ ソース コードへのリンク、アプリのプロジェクトのパッケージを取得する場所、ライブラリでユーザーのサインイン (認証)、保護された Web API へのアクセス (認可)、またはその両方がサポートされるかどうかが含まれます。

Microsoft ID プラットフォームは、[認定 OpenID プロバイダ](https://openid.net/certification/)として OpenID Foundation に認定されています。 Microsoft Authentication Library (MSAL) や Microsoft がサポートしている別のライブラリではないライブラリを使用する場合は、[認定 OpenID Connect 実装](https://openid.net/developers/certified/)のものを選択します。

[OAuth 2.0 または OpenID Connect 1.0](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols) の独自のプロトコルレベル実装を手作業でコーディングする場合は、各標準の仕様におけるセキュリティの考慮事項に細心の注意を払い、[Microsoft SDL](https://www.microsoft.com/securityengineering/sdl/) に見られるような安全なソフトウェア設計と開発の手法に従ってください。

### シングルページ アプリケーション (SPA)

シングルページ アプリケーションは、ブラウザー上で完全に実行され、ページ データ (HTML、CSS、JavaScript) を動的に、またはアプリケーションの読み込み時にフェッチします。 Web API を呼び出して、バックエンド データ ソースとやり取りできます。

SPA のコードは完全にブラウザーで実行されるため、シークレットを安全に格納できない*パブリック クライアント*と見なされます。

| 言語/フレームワーク | プロジェクトGitHub | パッケージ | 取得開始 | ユーザーのサインイン | Web API へのアクセス |
| --- | --- | --- | --- | --- | --- |
| 反応する | [MSAL React](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-react)^2^ | [msal-react](https://www.npmjs.com/package/@azure/msal-react) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] |
| JavaScript | [MSAL.js](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-browser)^2^ | [msal-browser](https://www.npmjs.com/package/@azure/msal-browser) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] |
| Angular（アンギュラー） | [MSAL Angular](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular)^2^ | [msal-angular](https://www.npmjs.com/package/@azure/msal-angular) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] |

### Web アプリケーション

Web アプリケーションでは、HTML、CSS、および JavaScript を生成し、ユーザーの Web ブラウザーに送信してレンダリングするサーバーでコードを実行します。 ユーザーの ID は、ユーザーのブラウザー (フロントエンド) と Web サーバー (バックエンド) の間のセッションとして保持されます。

Web アプリケーションのコードは Web サーバー上で実行されるため、シークレットを安全に格納できる*機密クライアント*と見なされます。

| 言語/フレームワーク | プロジェクトGitHub | パッケージ | 取得開始 | ユーザーのサインイン | Web API へのアクセス | 一般提供 (GA)*または*パブリック プレビュー^1^ |
| --- | --- | --- | --- | --- | --- | --- |
| .NET | [MSAL.NET](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet) | [Microsoft.Identity.クライアント](https://www.nuget.org/packages/Microsoft.Identity.Client) | — | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できません。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| .NET | [Microsoft.IdentityModel](https://github.com/AzureAD/azure-activedirectory-identitymodel-extensions-for-dotnet) | [Microsoft.IdentityModel](https://www.nuget.org/packages?q=Microsoft.IdentityModel) | — | [Image: ライブラリは、ユーザー サインインの ID トークンを要求できません。]^2^ | [Image: ライブラリは、保護された Web API のアクセス トークンを要求できません。]^2^ | GA |
| ASP.NET Core | [マイクロソフト.アイデンティティ.Web](https://github.com/AzureAD/microsoft-identity-web) | [マイクロソフト.アイデンティティ.Web](https://www.nuget.org/packages/Microsoft.Identity.Web) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-dotnet-core-sign-in) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| Java | [MSAL4J](https://github.com/AzureAD/microsoft-authentication-library-for-java) | [msal4j](https://central.sonatype.com/artifact/com.microsoft.azure/msal4j) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-java-sign-in) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| 春 | spring-cloud-azure-starter-active-directory を する | spring-cloud-azure-starter-active-directory を する | [チュートリアル](https://learn.microsoft.com/ja-jp/azure/developer/java/spring-framework/configure-spring-boot-starter-java-app-with-azure-active-directory) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| Node.js | [MSAL ノード](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node) | [msal-node](https://www.npmjs.com/package/@azure/msal-node) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-nodejs-sign-in) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| Python | [MSAL Python](https://github.com/AzureAD/microsoft-authentication-library-for-python) | [msal](https://pypi.org/project/msal) | — | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| Python | [同一性](https://github.com/rayluo/identity) | [同一性](https://pypi.org/project/identity/) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-python-flask) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | -- |

^(1)^[オンライン サービスのユニバーサル ライセンス条項](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)は、"パブリック プレビュー" のライブラリに適用されます。

^(2)^[Microsoft.IdentityModel](https://github.com/AzureAD/azure-activedirectory-identitymodel-extensions-for-dotnet) ライブラリはトークンの "検証" のみを行います。ID やアクセス トークンを要求することはできません。

### デスクトップ アプリケーション

デスクトップ アプリケーションは通常は、ユーザー インターフェイスを表示するバイナリ (コンパイル済み) コードであり、ユーザーのデスクトップで実行されるように意図されています。

デスクトップ アプリケーションはユーザーのデスクトップで実行されるため、シークレットを安全に保存できない*パブリック クライアント*と見なされます。

| 言語/フレームワーク | プロジェクトGitHub | パッケージ | 取得開始 | ユーザーのサインイン | Web API へのアクセス | 一般提供 (GA)*または*パブリック プレビュー^1^ |
| --- | --- | --- | --- | --- | --- | --- |
| 電子 | [MSAL Node.js](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node) | [msal-node](https://www.npmjs.com/package/@azure/msal-node) | — | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | パブリック プレビュー |
| Java | [MSAL4J](https://github.com/AzureAD/microsoft-authentication-library-for-java) | [msal4j](https://mvnrepository.com/artifact/com.microsoft.azure/msal4j) | — | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| macOS (Swift/Obj-C) | [iOS および macOS 用の MSAL](https://github.com/AzureAD/microsoft-authentication-library-for-objc) | [MSAL](https://cocoapods.org/pods/MSAL) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-ios) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| UWP | [MSAL.NET](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet) | [Microsoft.Identity.クライアント](https://www.nuget.org/packages/Microsoft.Identity.Client) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-windows-uwp) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| WPF | [MSAL.NET](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet) | [Microsoft.Identity.クライアント](https://www.nuget.org/packages/Microsoft.Identity.Client) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-windows-desktop) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |

^1^[オンライン サービスのユニバーサル ライセンス条項](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)は、"パブリック プレビュー" のライブラリに適用されます。

### モバイル アプリケーション

モバイル アプリケーションは通常、ユーザー インターフェイスを表示するバイナリ (コンパイル済み) コードであり、ユーザーのモバイル デバイスで実行されるように意図されています。

モバイル アプリケーションはユーザーのモバイル デバイスで実行されるため、シークレットを安全に保存できない*パブリック クライアント*と見なされます。

| プラットフォーム | プロジェクトGitHub | パッケージ | 取得開始 | ユーザーのサインイン | Web API へのアクセス | 一般提供 (GA)*または*パブリック プレビュー^1^ |
| --- | --- | --- | --- | --- | --- | --- |
| Android (Java) | [MSAL アンドロイド](https://github.com/AzureAD/microsoft-authentication-library-for-android) | [MSAL](https://mvnrepository.com/artifact/com.microsoft.identity.client/msal) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-android-sign-in) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| Android (Kotlin) | [MSAL アンドロイド](https://github.com/AzureAD/microsoft-authentication-library-for-android) | [MSAL](https://mvnrepository.com/artifact/com.microsoft.identity.client/msal) | — | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| iOS (Swift/Obj-C) | [iOS および macOS 用の MSAL](https://github.com/AzureAD/microsoft-authentication-library-for-objc) | [MSAL](https://cocoapods.org/pods/MSAL) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-ios) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |

^1^[オンライン サービスのユニバーサル ライセンス条項](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)は、"パブリック プレビュー" のライブラリに適用されます。

### サービス/デーモン

サービスとデーモンは、サーバー間およびその他の無人 (*ヘッドレス* とも呼ばれます) 通信でよく使用されます。 キーボードの前にユーザーがいて資格情報を入力したりリソースへのアクセスを同意したりすることがないため、これらのアプリケーションでは、Web API のリソースへの認可済みアクセスを要求するときに、ユーザーではなくそれ自身として認証されます。

サーバーで実行されるサービスまたはデーモンは、シークレットを安全に格納できる*機密クライアント*と見なされます。

| 言語/フレームワーク | プロジェクトGitHub | パッケージ | 取得開始 | ユーザーのサインイン | Web API へのアクセス | 一般提供 (GA)*または*パブリック プレビュー^1^ |
| --- | --- | --- | --- | --- | --- | --- |
| .NET | [MSAL.NET](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet) | [Microsoft.Identity.クライアント](https://www.nuget.org/packages/Microsoft.Identity.Client/) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-daemon-dotnet-acquire-token) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できません。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| Java | [MSAL4J](https://github.com/AzureAD/microsoft-authentication-library-for-java) | [msal4j](https://javadoc.io/doc/com.microsoft.azure/msal4j/latest/index.html) | — | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できません。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| Node | [MSAL ノード](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node) | [msal-node](https://www.npmjs.com/package/@azure/msal-node) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-console-app-nodejs-acquire-token) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できません。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| Python | [MSAL Python](https://github.com/AzureAD/microsoft-authentication-library-for-python) | [msal-python](https://github.com/AzureAD/microsoft-authentication-library-for-python) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-daemon-app-python-acquire-token) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できません。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |

^1^[オンライン サービスのユニバーサル ライセンス条項](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)は、"パブリック プレビュー" のライブラリに適用されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/refresh-tokens"} -->
## Microsoft ID プラットフォームで更新トークンを使用する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/refresh-tokens
- Service: identity-platform
- Article date: 2025-11-05
- Summary: Microsoft ID プラットフォームで使用される更新トークンについて説明します。

更新トークンを使用して、現在のアクセス トークンの有効期限が切れたときに、新しいアクセス トークンと更新トークンのペアを取得します。 クライアントは保護されたリソースにアクセスするためのアクセス トークンを取得するときに、更新トークンも受け取ります。

更新トークンは、他のリソースの追加のアクセス トークンを取得するのにも使用されます。 更新トークンは、ユーザーとクライアントの組み合わせにバインドされていますが、リソースまたはテナントには関連付けられていません。 クライアントは更新トークンを使用して、リソースとテナントの任意の組み合わせで、権限があればアクセス トークンを取得することができます。 更新トークンは暗号化され、Microsoft ID プラットフォームでのみ読み取ることができます。

### トークンの有効期間

更新トークンの有効期間は、アクセス トークンよりも長くなります。 更新トークンの既定の有効期間は次のとおりです。

- シングルページ アプリケーションの**場合は 24 時間**。
- 電子メール ワンタイム パスコード認証フローを使用するアプリの場合、**24 時間**。
- 他のすべてのシナリオ**で 90 日間**。

更新トークンは、使用するたびに新しいトークンに置き換えられます。 Microsoft ID プラットフォームでは、古い更新トークンが、新しいアクセス トークンを取得するために使用されるときに失効になりません。 古い更新トークンは、新しい更新トークンを取得した後に安全に削除します。 更新トークンは、アクセス トークンやアプリケーション資格情報と同様に安全に保存されている必要があります。

注

`spa` として登録されたリダイレクト URI に送信される更新トークンは、24 時間後に期限切れになります。 初期更新トークンを使用して取得された追加の更新トークンは、その有効期限を引き継ぎます。そのため、24 時間ごとに新しい更新トークンを取得するために、対話型認証を使用して認可コード フローを再実行するようにアプリを準備する必要があります。 ユーザーは自分の資格情報を入力する必要はなく、通常は関連するユーザー エクスペリエンスも表示されず、アプリケーションを再度読み込むだけです。 ブラウザーは、ログイン セッションを表示するために、最上位フレームでサインイン ページにアクセスする必要があります。 これは、[サード パーティの Cookie をブロックするブラウザーのプライバシー機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-third-party-cookies-spas)のためです。

### トークンの有効期限

更新トークンは、有効期間が経過すると自動的に期限切れになります。 また、有効期限が切れる前に、サインイン サービスによっていつでも取り消すことができます。 アプリでは、ユーザーを対話型サインイン プロンプトにリダイレクトして再認証し、新しいトークンを取得することで、このような失効を適切に処理する必要があります。

#### トークンの失効

資格情報の変更、ユーザーの操作、または管理者の操作が原因で、サーバーが更新トークンを取り消す場合があります。 更新トークンは、機密クライアント (右端の列) に発行されるトークンと、パブリック クライアント (他のすべての列) に発行されるトークンの 2 つのクラスに分類されます。

| 変更 | パスワードに基づくクッキー | パスワードに基づくトークン | パスワードに基づかないクッキー | パスワードに基づかないトークン | クライアント機密トークン |
| --- | --- | --- | --- | --- | --- |
| パスワードが期限切れ | 存続 | 存続 | 存続 | 存続 | 存続 |
| ユーザーによるパスワードの変更 | 取り消し | 取り消し | 存続 | 存続 | 存続 |
| ユーザーがSSPRである | 取り消し | 取り消し | 存続 | 存続 | 存続 |
| 管理者がパスワードをリセットする (Azure portal) | 取り消し | 取り消し | 存続 | 存続 | 存続 |
| 管理者がパスワードをリセットする (Microsoft Entra 管理センター) | 取り消し | 取り消し | 存続 | 取り消し | 取り消し |
| 管理者がパスワードをリセットする (M365 管理センター) | 取り消し | 取り消し | 存続 | 取り消し | 取り消し |
| ユーザーが更新トークンを取り消す | 取り消し | 取り消し | 取り消し | 取り消し | 取り消し |
| 管理者がユーザーのすべての更新トークンを無効にする | 取り消し | 取り消し | 取り消し | 取り消し | 取り消し |
| シングル サインアウト | 取り消し | 存続 | 取り消し | 存続 | 存続 |

注

リソース テナント内の B2B ユーザーの更新トークンは取り消されません。 このトークンはホーム テナントで取り消す必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/reply-url"} -->
## リダイレクト URI (応答 URL) のベスト プラクティスと制限事項 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/reply-url
- Service: identity-platform
- Article date: 2025-05-14
- Summary: Microsoft ID プラットフォームでのリダイレクト URI に関するベスト プラクティスと制限についての説明。

Microsoft ID プラットフォームと統合するアプリケーションを構築する場合は、リダイレクト URI を構成する方法を理解することが不可欠です。 この記事では、リダイレクト URI のベスト プラクティス、サポートされる構成、制限事項に関する包括的なガイドを提供します。 Web、モバイル、デスクトップのどちらのアプリケーションを開発する場合でも、この記事は、セキュリティ要件を満たすようにリダイレクト URI を正しく構成するのに役立ちます。

### リダイレクト URI とは

リダイレクト URI (応答 URL) は、ユーザーが正常に承認され、アクセス トークンを付与された後で、Microsoft Entra 認証サーバーからユーザーに送信される場所です。 ユーザーをサインインさせるには、アプリケーションで、リダイレクト URI をパラメーターとして指定してログイン要求を送信する必要があります。これにより、ユーザーが正常にサインインした後、認証サーバーによってユーザーがリダイレクトされ、ログイン要求で指定されたリダイレクト URI にアクセス トークンが発行されます。

たとえば、運用 Web アプリケーションでは、多くの場合、リダイレクト URI は、アプリが実行されているパブリック エンドポイント (例: `https://contoso.com/auth-response`) です。 開発時には、アプリをローカルで実行するエンドポイント (例: `https://127.0.0.1/auth-response` または `http://localhost/auth-response`) も追加するのが一般的です。 不要な開発環境またはリダイレクト URI が運用環境のアプリで公開されていないことを確認します。 これを行うには、開発と運用用に別々のアプリ登録を行います。

### リダイレクト URI をアプリ登録に追加する必要がある理由

セキュリティ上の理由から、認証サーバーは、アプリ登録に追加されていない URI に対してユーザーのリダイレクトまたはトークンの送信を行いません。 Microsoft Entra ログイン サーバーは、アプリ登録に追加されたリダイレクト URI に対してのみ、ユーザーのリダイレクトおよびトークンの送信を行います。 ログイン要求で指定されたリダイレクト URI が、アプリケーションで追加したリダイレクト URI と一致しない場合、`AADSTS50011: The reply URL specified in the request does not match the reply URLs configured for the application` などのエラー メッセージが表示されます。

エラー コードについて詳しくは、「[Microsoft Entra 認証と承認のエラー コード](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes)」をご覧ください。

### リダイレクト URI をアプリ登録に追加する必要の有無

リダイレクト URI をアプリ登録に追加する必要があるかどうかは、アプリケーションで使用される認証プロトコルによって異なります。 アプリケーションで次の認証プロトコルが使用されている場合は、適切なリダイレクト URI をアプリ登録に追加する必要があります。

- [OAuth 2.0 承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)
- [OAuth 2.0 クライアント資格情報フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)
- [OAuth 2.0 暗黙的な許可フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-implicit-grant-flow)
- [OpenID Connect](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc)
- [シングル サインオンの SAML プロトコル](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol)

アプリケーションで次の認証プロトコルまたは機能が使用されている場合は、リダイレクト URI をアプリ登録に追加する必要はありません。

- [ネイティブ認証](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-native-authentication)
- [OAuth 2.0 デバイス コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-device-code)
- [OAuth 2.0 On-Behalf-Of フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow)
- [OAuth 2.0 リソース所有者パスワード資格情報フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth-ropc)
- [Windows 統合認証フロー](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/integrated-windows-authentication)
- [SAML 2.0 シングルサインオン用アイデンティティプロバイダー (IdP)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-saml-idp)

#### リダイレクト URI を追加する必要があるプラットフォーム

ビルドしているアプリケーションのアプリ登録に 1 つ以上のリダイレクト URI が含まれる場合は、[パブリック クライアント フロー構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-client-applications)を有効にする必要があります。 次の表は、アプリケーションを作成しているプラットフォームに基づいて、追加する必要がある、または追加してはならないリダイレクト URI の種類に関するガイダンスです。

##### Web アプリケーションのリダイレクト URI の構成

| アプリケーションの種類 | 一般的な言語/フレームワーク | アプリ登録でリダイレクト URI を追加するプラットフォーム |
| --- | --- | --- |
| ほとんどのアプリケーション ロジックがサーバー上で実行される従来の Web アプリケーション | Node.js、Web、ASP.NET、Python、Java、ASP.NET Core、PHP、Ruby、Blazor Server | Web |
| 主に Web API を使って Web サーバーと通信する、Web ブラウザーでほとんどのユーザー インターフェイス ロジックが実行されるシングルページ アプリケーション | JavaScript、Angular、React、Blazor WebAssembly、Vue.js | シングルページ アプリケーション (SPA) |

##### モバイルとデスクトップ アプリケーションのリダイレクト URI の構成

| アプリケーションの種類 | 一般的な言語/フレームワーク | アプリ登録でリダイレクト URI を追加するプラットフォーム |
| --- | --- | --- |
| この表の下で示すシナリオを除く iOS または macOS アプリ | Swift、Objective-C | IOS/macOS |
| Android アプリ | Java または Kotlin | Android |
| モバイル デバイスまたはデスクトップ マシンでネイティブに実行されるアプリ | Node.js electron, Windows デスクトップ, UWP, React Native, Android, iOS/macOS | モバイル アプリケーションとデスクトップ アプリケーション |

次のいずれかの方法を使用して iOS アプリをビルドしている場合は、**モバイルおよびデスクトップ アプリケーション**のプラットフォームを使用してリダイレクト URI を追加します。

- オープンソース SDK を使用する iOS アプリ (AppAuth)
- サポートされていないクロスプラットフォーム テクノロジを使用する iOS アプリ (Flutter)
- OAuth プロトコルを直接実装する iOS アプリ
- サポートされていないクロスプラットフォーム テクノロジを使用する macOS アプリ (Electron)

##### リダイレクト URI を必要としないアプリケーション

| アプリケーションの種類 | 例/メモ | 関連付けられている OAuth フロー |
| --- | --- | --- |
| キーボードがないデバイスで実行されているアプリケーション | スマート テレビ、IoT デバイス、またはプリンターで実行されているアプリケーション | デバイス コード フロー ([詳細](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-device-code)) |
| ユーザーを Entra でホストされているログイン Web サイトにリダイレクトし、Entra に安全な方法でユーザー パスワードを処理させる代わりに、ユーザーが直接入力するパスワードを処理するアプリケーション。 | このフローは、認可コード フローなどの他のより安全なフローほど安全でないため、それらを使用できない場合にのみ使う必要があります。 | リソース所有者のパスワード資格情報フロー ([詳細情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)) |
| Web アカウント マネージャーではなく Windows 統合認証フローを使って、Windows ドメイン (AD または Azure AD 参加済み) に接続されている Windows またはコンピューター上で実行されているデスクトップまたはモバイル アプリケーション | ユーザーが Entra 資格情報を使って Windows PC システムにサインインした後で自動的にサインインする必要があるデスクトップまたはモバイル アプリケーション | Windows 統合認証フロー ([詳細情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)) |

### Microsoft Entra アプリケーションのためのリダイレクト URI の制限

Microsoft Entra アプリケーション モデルでは、リダイレクト URI に対して以下の制限が指定されています。

- `https`、リダイレクト URI はスキーム  で始まっている必要があります。
- リダイレクト URI では大文字と小文字が区別され、実行中のアプリケーションの URL パスの大文字と小文字が一致する必要があります。

    *例:*

    - アプリケーションがそのパスの一部として `.../abc/response-oidc` を含む場合は、リダイレクト URI で `.../ABC/response-oidc` を指定しないでください。 Web ブラウザーでは大文字と小文字を区別を区別するものとしてパスが処理されるため、`.../abc/response-oidc` に関連付けられている cookie は、大文字と小文字が一致しない `.../ABC/response-oidc` URL にリダイレクトされた場合に除外される可能性があります。
- パス セグメントで構成 "*されていない*" リダイレクト URI は、応答に末尾のスラッシュ ('`/`') が付加されて返されます。 これは、応答モードが `query` または `fragment` の場合にのみ適用されます。

    *例:*

    - `https://contoso.com` は `https://contoso.com/` として返されます
    - `http://localhost:7071` は `http://localhost:7071/` として返されます
- パス セグメントを含むリダイレクト URI は、応答に末尾のスラッシュが付加 "*されません*"。

    *例:*

    - `https://contoso.com/abc` は `https://contoso.com/abc` として返されます
    - `https://contoso.com/abc/response-oidc` は `https://contoso.com/abc/response-oidc` として返されます
- リダイレクト URI では、特殊文字 () は`! $ ' ( ) , ;`
- リダイレクト URI では、国際化ドメイン名は*サポートされません*

#### リダイレクト URI の最大数と URI の長さ

セキュリティ上の理由から、最大数のリダイレクト URI を生成することはできません。 シナリオ上、許可される最大制限を超えるリダイレクト URI が必要な場合は、解決方法として次の状態パラメーター アプローチを検討してください。 次の表では、Microsoft ID プラットフォームでアプリ登録に追加できるリダイレクト URI の最大数を示します。

| サインイン中のアカウント | リダイレクト URI の最大数 | 説明 |
| --- | --- | --- |
| いずれかの組織の Microsoft Entra テナント内の Microsoft の職場または学校アカウント | 256 | アプリケーション マニフェストの `signInAudience` フィールドは *AzureADMyOrg* か *AzureADMultipleOrgs* に設定されています |
| Microsoft の個人用アカウント、職場用アカウント、学校用アカウント | 100 | アプリケーション マニフェストの `signInAudience` フィールドは *AzureADandPersonalMicrosoftAccount* に設定されています |

アプリの登録に追加するリダイレクト URI ごとに最大 256 文字を使用できます。

#### アプリケーション オブジェクトとサービス プリンシパル オブジェクトにおけるリダイレクト URI

- "常に" リダイレクト URI はアプリケーション オブジェクトにのみ追加します。
- サービス プリンシパルにはリダイレクト URI の値を追加 "しないでください"。サービス プリンシパル オブジェクトがアプリケーション オブジェクトと同期するとき、これらの値が削除される場合があります。 これは、2 つのオブジェクトの間の同期をトリガーする更新操作のために発生する可能性があります。

#### リダイレクト URI におけるクエリ パラメーターのサポート

アプリケーションのリダイレクト URI にクエリ パラメーターが*許可*されるのは、サインインするユーザーが職場または学校アカウントを有するユーザー "のみ" の場合です。

Outlook.com (Hotmail)、Messenger、OneDrive、MSN、Xbox Live、Microsoft 365 など、個人用 Microsoft アカウントを使ってユーザーをサインインさせるように構成されたアプリ登録では、リダイレクト URI でクエリ パラメーターを使用できません。

| アプリ登録のサインイン対象ユーザー | リダイレクト URI でのクエリ パラメーターのサポート |
| --- | --- |
| この組織ディレクトリのみに含まれるアカウント (Contoso のみ - シングル テナント) |  |
| 任意の組織ディレクトリ (Microsoft Entra ディレクトリ - マルチテナント) 内のアカウント |  |
| 任意の組織のディレクトリ (Microsoft Entra ディレクトリ - マルチテナント) 内のアカウントと、個人用の Microsoft アカウント (Skype、Xbox など) を選択します。 |  |
| 個人用 Microsoft アカウントのみ |  |

### サポートされているスキーム

**HTTPS:** HTTPS スキーム (`https://`) は、すべての HTTP ベースのリダイレクト URI でサポートされています。

**HTTP:** HTTP スキーム (`http://`) は *localhost* URI で "*のみ*" サポートされ、アクティブなローカル アプリケーションの開発およびテスト中にのみ使用する必要があります。

| リダイレクト URI の例 | 有効期限までの日数 |
| --- | --- |
| `https://contoso.com` | 有効 |
| `https://contoso.com/abc/response-oidc` | 有効 |
| `https://localhost` | 有効 |
| `http://contoso.com/abc/response-oidc` | 無効 |
| `http://localhost` | 有効 |
| `http://localhost/abc` | 有効 |

#### Localhost の例外

[RFC 8252 のセクション 8.3](https://tools.ietf.org/html/rfc8252#section-8.3) と [7.3](https://tools.ietf.org/html/rfc8252#section-7.3) に従って、"loopback" または "localhost" リダイレクト URI には、次の 2 つの特別な考慮事項があります。

1. リダイレクトがデバイスから切り離されることはないため、`http` URI スキームを指定できます。 そのため、次の両方の URI を使用できます。

    - `http://localhost/myApp`
    - `https://localhost/myApp`
2. ネイティブ アプリケーションでは一時的なポート範囲が必要になることが多いため、localhost リダイレクト URI の照合のためにポート コンポーネント ( `:5001` や `:443`など) は無視されます。 その結果、これらの URI はすべて同等と見なされます。

    - `http://localhost/MyApp`
    - `http://localhost:1234/MyApp`
    - `http://localhost:5000/MyApp`
    - `http://localhost:8080/MyApp`

    これは、localhost リダイレクト URI *に対してのみ* 当てはまります。 それ以外の場合は、リダイレクト URI の照合時にポート コンポーネントは無視 *されません* 。

開発の観点から見ると、これはいくつかのことを意味します。

- ポートのみが異なる場合は、複数の localhost リダイレクト URI を登録しないでください。 ログイン サーバーは任意に 1 つを選択し、その登録されたリダイレクト URI に関連付けられている動作を使用します (たとえば、 `web`、 `native`、 `spa` 型のリダイレクトなど)。

    これは、同じアプリケーションの登録で異なる認証フロー (認可コードの付与と暗黙のフローなど) を使用する場合に特に重要です。 各リダイレクト URI に適切な応答動作を関連付けるには、ログイン サーバーでリダイレクト URI を区別できる必要があり、ポートのみが異なる場合はそれをできません。
- 複数のリダイレクト URI を localhost に登録して開発中にさまざまなフローをテストする場合は、URI の "*パス*" コンポーネントを使用してそれらを区別します。 たとえば、`http://localhost/MyWebApp` は `http://localhost/MyNativeApp` と一致しません。
- IPv6 ループバック アドレス (`[::1]`) は、現在サポートされていません。

##### 127.0.0.1 以上の localhost が推奨されています

ファイアウォールの正しくない構成や、ネットワーク インターフェイスの名前変更によって、アプリが動作不能になるのを防ぐには、リダイレクト URI で `127.0.0.1` ではなく IP リテラル ループバック アドレス `localhost` を使います。 たとえば、`https://127.0.0.1` のようにします。

ただし、Azure portal の **[リダイレクト URI]** テキスト ボックスを使って、`http` スキームを使うループバックベースのリダイレクト URI を追加することはできません。

[Image: 許可されていない HTTP ベースのループバック リダイレクト URI を示す Azure portal のエラー ダイアログ]

`http` ループバック アドレスで `127.0.0.1` スキームを使用するリダイレクト URI を追加するには、現在のところアプリケーション マニフェストで [replyUrlsWithType](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-app-manifest#replyurlswithtype-attribute) 属性を変更する必要があります。

### リダイレクト URI のワイルドカードに関する制限事項

`https://*.contoso.com` のようなワイルドカード URI は、便利に思えることもありますが、セキュリティへの影響のため、回避する必要があります。 OAuth 2.0 仕様 ([セクション 3.1.2 of RFC 6749](https://tools.ietf.org/html/rfc6749#section-3.1.2)) によると、リダイレクト エンドポイント URI は絶対 URI にする必要があります。 そのため、構成されたワイルドカード URI がリダイレクト URI と一致すると、リダイレクト URI 内のクエリ文字列とフラグメントが削除されます。

ワイルドカード URI は、現在、個人用 Microsoft アカウントと職場または学校のアカウントにサインインするように構成されているアプリの登録ではサポートされていません。 ただし、組織の Microsoft Entra テナントで、職場または学校のアカウントのみをサインインするように構成されているアプリの場合は、ワイルドカード URI が許可されています。

職場または学校のアカウントにサインインするアプリの登録に、ワイルドカードを含むリダイレクト URI を追加するには、Azure portal の **[アプリの登録]** で、アプリケーション マニフェスト エディターを使用します。 マニフェスト エディターを使用して、ワイルドカードを含むリダイレクト URI を設定することも可能ですが、RFC 6749 のセクション 3.1.2 に準拠することを*強く*お勧めします。 絶対 URI のみを使用するようにしてください。

シナリオ上、許可される最大制限を超えるリダイレクト URI が必要な場合は、ワイルドカードを含むリダイレクト URI を追加する代わりに、次の状態パラメーター アプローチを検討してください。

#### 状態パラメーターを使用する

いくつかのサブドメインがあり、シナリオ上それが必要である場合は、認証成功時にユーザーを開始時と同じページにリダイレクトしますが、状態パラメーターを使用すると便利な場合があります。

このアプローチでは:

1. 認証エンドポイントから受け取ったセキュリティ トークンを処理する "共有" リダイレクト URI をアプリケーション別に作成します。
2. アプリケーションでは、状態パラメーターで、アプリケーション固有のパラメーター (ユーザーの起点になったサブドメイン URL やブランド情報のような任意のもの) を送信できます。 状態パラメーターの使用時、CSRF 対策をしてください。仕様は [RFC 6749 のセクション 10.12](https://tools.ietf.org/html/rfc6749#section-10.12) にあります。 セキュリティとプライバシーのために、状態パラメーターに URL やその他の機密データを直接配置しないでください。 代わりに、localStorage や sessionStorage などのブラウザー ストレージに格納されているデータに対応するキーまたは識別子を使用します。 この方法により、アプリは認証後に必要なデータを安全に参照できます。
3. アプリケーション固有のパラメーターには、アプリケーションでユーザーに適切なエクスペリエンスをレンダリングするため、つまりアプリケーションの適切な状態を構築するために必要な情報がすべて含まれます。 Microsoft Entra 承認エンドポイントにより状態パラメーターから HTML が取り除かれるため、このパラメーターで HTML の内容を渡さないでください。
4. Microsoft Entra ID は、"共有" リダイレクト URI に応答を送信するとき、状態パラメーターをアプリケーションに送り返します。
5. その後、ユーザーをさらに送信する宛先となる URL を決定する目的で、状態パラメーターの値をアプリケーションで利用できます。 CSRF 対策の有効性を確認してください。

警告

この手法では、セキュリティを侵害されたクライアントが状態パラメーターで送信された追加パラメーターを変更し、ユーザーを別の URL にリダイレクトすることを許します。これは RFC 6819 に説明がある[オープン リダイレクターの脅威](https://tools.ietf.org/html/rfc6819#section-4.2.4)です。 そのため、クライアントは状態を暗号化するか、リダイレクト URI に含まれるドメイン名をトークンと比べて検証するなど、何か他の手段で状態を検証することによって、これらのパラメーターを保護する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/retire-service-principal-less-authentication"} -->
## サービス プリンシパルレス認証の廃止 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/retire-service-principal-less-authentication
- Service: identity-platform
- Article date: 2025-03-30
- Summary: サービス プリンシパルレス認証の廃止に対してテナント管理者が実行する必要がある軽減手順について説明します。

2026 年 3 月から、Microsoft Entra ID はサービス プリンシパルなしでアプリ認証をサポートしなくなります。 この記事では、退職に備える方法について説明します。 テナント管理者は、影響を受けるアプリを特定し、サービス プリンシパルを作成し、加えた変更を確認します。

### 前提条件

- **アプリケーション管理者**または**クラウド アプリケーション管理者**ロールが割り当てられているアカウント。

### サービス プリンシパルなしでアプリ認証をブロックする

Microsoft Entra ID は、認証するテナントにサービス プリンシパルがない Microsoft 以外のすべてのマルチテナント アプリケーションの認証をブロックします。 このシナリオは、サービス プリンシパルレス認証とも呼ばれます。 この動作は、Microsoft 以外のほとんどのアプリケーションで既に無効になっています。 この変更は、残りのいくつかの例外に対処し、予防的なセキュリティ対策です。

サービス プリンシパルのないアプリ認証を使用すると、マルチテナント クライアント アプリケーションは、オブジェクト識別子 (オブジェクト ID) 要求なしでテナントからアプリ専用トークンを取得できます。 ほとんどの場合、サービス プリンシパルが存在しないことは、アプリにデータへのアクセス許可が付与されていないことを意味し、これは無害です。 ただし、ターゲット API が不適切な承認チェックを実装している場合はまれに、この機能によって不正アクセスが発生する可能性があります。 Microsoft は、Microsoft が公開した API がこの種の不正使用に対して脆弱でないことを既に確認しています。 この動作を無効にすると、承認チェックが不十分な Microsoft 以外の API も完全に保護されます。

さらに、すべてのアプリケーションが認証するすべてのテナントにサービス プリンシパルが必要であるという要件を適用することで、条件付きアクセス ポリシーを使用してこれらのアプリを個別にターゲットにする機能など、すべてのアクセスに対するテナント管理者のガバナンスを容易にします。

依存しているアプリケーションがテナントのサービス プリンシパルなしで認証されている場合は、中断を回避するために **2026 年 3 月 31 日より前** に動作する必要があります。

**廃止に関する最新の情報については、「[Microsoft Entra ID でサービス プリンシパルが必須に | Microsoft コミュニティ ハブ](https://techcommunity.microsoft.com/blog/microsoft-entra-blog/service-principal-required-for-microsoft-entra-id/4405796/)」を参照してください 。**

### サインイン ログを使用して、サービス プリンシパルなしで認証されているアプリケーションを見つける

注

アクションは、"サービス プリンシパル サインイン" (アプリ専用) サインイン ログにサービス プリンシパルが見つからないアプリの認証にのみ必要です。 *ユーザー* サインイン ログには、サービス プリンシパルなしで認証されている Microsoft アプリケーションとサービスが含まれます。 Microsoft アプリによるサービス プリンシパルを使用しないサインインと認証は想定されており、お客様によるアクションは必要ありません。

まず、一覧表示されているリソースへの名前付きアプリケーションによるアクセスが必要であることを確認する必要があります。 アプリケーションのサインイン アクティビティは、サインイン [ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)を使用してリソース テナントの管理者が確認できます。 サービス プリンシパルのない認証を行うアプリケーションのサービス プリンシパル ID は、リソース テナントのサインイン ログに `00000000-0000-0000-0000-000000000000` として表示されます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/)に移動します。
2. 左側のナビゲーション パネルで、**Entra ID**&gt;**監視と健康**&gt;**サインインログに移動します**。
3. [ **サービス プリンシパルのサインイン** ] タブに移動します。
4. **サービス プリンシパル ID** でフィルター処理し、入力フィールドに`00000000-0000-0000-0000-000000000000`を入力します。
5. 日付範囲を **[過去 1 か月]** に設定します。
6. ログ エントリをクリックして詳細を表示し、アプリの **アプリケーション ID を識別します**。 これは次の手順で必要になります。

[Image: MICROSOFT Entra 管理センターのサインイン ログ ページを示すスクリーンショット。SP レス認証サインインで抽出するフィルターが適用されています。]

### サービス プリンシパルを作成する

サービス プリンシパルなしで認証するアプリケーションを特定したら、サインイン ログの詳細を使用して、想定されているかどうかを判断し、テナントでの認証を続行する必要があります。

アプリを認識せず、ブロックする場合は、アプリの [サービス プリンシパルを作成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/create-service-principal-cross-tenant?pivots=msgraph-powershell) し、 [サービス プリンシパルを無効にします](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/disable-user-sign-in-portal?pivots=portal)。 アプリのサービス プリンシパルを無効にすると、テナント内のそのアプリによる今後のサインインと認証の試行がすべてブロックされます。

### 加えた変更を確認する

アプリケーションに対してアクションを実行すると、サインイン ログにアプリの新しいサービス プリンシパル ID が含まれるようになり、一意の英数字 GUID が `aaaaaaaa-bbbb-cccc-1111-222222222222`形式になります。 これにより、アプリがテナントにサービス プリンシパルを持ち、今後の変更の影響を受けないことが確認されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/saml-claims-customization"} -->
## SAML トークン クレームをカスタマイズする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization
- Service: identity-platform
- Article date: 2025-05-14
- Summary: エンタープライズ アプリケーションの SAML トークンで Microsoft ID プラットフォームによって発行された要求をカスタマイズする方法について説明します。

Microsoft ID プラットフォームでは、アプリケーション ギャラリーとカスタム アプリケーションのほとんどの事前に統合されたアプリケーションで [シングル サインオン (SSO)](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on) がサポートされています。 ユーザーが SAML 2.0 プロトコルを使用して Microsoft ID プラットフォーム経由でアプリケーションに対する認証を行うと、アプリケーションにトークンが送信されます。 アプリケーションはトークンを検証し、ユーザー名とパスワードの入力を求める代わりに、そのトークンを使ってユーザーをサインインさせます。

これらの SAML トークンには、クレームと呼ばれるユーザーに関する情報が含 *まれています*。 要求とは、そのユーザーに発行するトークンの中にあるユーザーに関する ID プロバイダーが提示した情報を指します。 SAML トークンでは、通常、"クレーム" のデータは SAML 属性ステートメントに含まれています。 通常、ユーザーの一意の ID は、名前識別子 (`nameID`) とも呼ばれる SAML サブジェクトで表されます。

既定では、Microsoft ID プラットフォームにより、ユーザーを一意に識別できるユーザー名 (別名: ユーザー プリンシパル名) を値として持つクレームを含む SAML トークンがアプリケーションに対して発行されます。 また、SAML トークンには、ユーザーのメール アドレスや姓名を含む他のクレームも含まれています。

### クレームを表示または編集する

アプリケーションの SAML トークンで発行されたクレームを表示または編集するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[すべてのアプリケーション]** を参照します。
3. アプリケーションを選択し、左側のメニューで **[シングル サインオン**] を選択し、[**属性と要求**] セクションで **[編集]** を選択します。

次の理由から、SAML トークンで発行されたクレームを編集することが必要な場合があります。

- そのアプリケーションでは、`NameIdentifier` または `nameID` クレームが、ユーザー名 (ユーザー プリンシパル名) 以外のものである必要がある。
- アプリケーションが、別の要求 URI または要求値のセットを必要とするように記述されている。

### `nameID` を編集します

名前識別子の値クレームを編集するには、次の手順に従います。

1. **[名前識別子の値]** ページを開きます。
2. 属性または属性に適用する変換を選択します。 必要に応じて、`nameID` クレームの形式を指定できます。

#### NameID の形式

SAML 要求に特定の形式の `NameIDPolicy` 要素が含まれている場合、Microsoft ID プラットフォームではその要求の形式が優先されます。

SAML 要求に `NameIDPolicy` 要素が含まれていない場合は、Microsoft ID プラットフォームによって、ユーザーが指定した形式の `nameID` が発行されます。 形式を指定しないと、Microsoft ID プラットフォームでは、選択されたクレーム ソースに関連付けられている既定のソース形式が使われます。 構成されたソース属性が `null`されると、Microsoft 識別プラットフォームは `userprincipalName`を使用します。 変換の結果が null または無効な値である場合、Microsoft Entra ID によって、`nameID` 内の永続的なペアワイズ識別子が送信されます。

[ **名前識別子の形式の選択** ] ドロップダウンから、次の表のいずれかのオプションを選択します。

| `nameID`の形式 | 説明 |
| --- | --- |
| **デフォルト** | Microsoft ID プラットフォームでは既定のソース形式が使われます。 |
| **粘り強い** | Microsoft ID プラットフォームでは、`Persistent` の形式として `nameID` が使用されます。 |
| **アドレス** | Microsoft ID プラットフォームでは、`EmailAddress` の形式として `nameID` が使用されます。 |
| **未指定** | Microsoft ID プラットフォームでは、`Unspecified` の形式として `nameID` が使用されます。 |
| **Windows domain qualified name (Windows ドメイン修飾名)** | Microsoft ID プラットフォームでは、`WindowsDomainQualifiedName` の形式が使用されます。 |

一時的な `nameID` もサポートされていますが、ドロップダウンで選択できず、Azure 側では構成できません。 `NameIDPolicy`属性の詳細については、「[シングル サインオン SAML プロトコル](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol)」を参照してください。

#### 属性

`NameIdentifier` (または `nameID`) クレームの目的のソースを選択します。 次の表のオプションから選択できます。

| 名前 | 説明 |
| --- | --- |
| `Email` | ユーザーのメール アドレス。 |
| `userprincipalName` | ユーザーのユーザー プリンシパル名 (UPN)。 |
| `onpremisessamaccountname` | オンプレミスの Microsoft Entra ID から同期された SAM アカウント名。 |
| `objectid` | Microsoft Entra ID のユーザーのオブジェクト ID。 |
| `employeeid` | ユーザーの従業員 ID。 |
| `Directory extensions` | [Microsoft Entra Connect Sync を使用してオンプレミスの Active Directory から同期された](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions)ディレクトリ拡張機能。 |
| `Extension Attributes 1-15` | Microsoft Entra スキーマを拡張するために使用されるオンプレミス拡張属性。 |
| `pairwiseid` | 永続的な形式のユーザー識別子。 |

識別子の値の詳細については、このページで後述するソースごとの有効な ID 値を示す表を参照してください。

任意の定数値 (静的な値) を任意のクレームに割り当てることができます。 定数値を割り当てるには、次の手順に従います。

1. [ **属性と要求** ] ブレードで、変更する必要な要求を選択します。
2. 組織に従って **ソース属性** に引用符なしで定数値を入力し、[ **保存]** を選択します。 定数値が表示されます。

#### ディレクトリ スキーマ拡張

ディレクトリ スキーマ拡張属性を非条件付き/条件付き属性として構成することもできます。 単一値または複数値のディレクトリ スキーマ拡張属性をクレームとして構成するには、次の手順に従います。

1. [ **属性と要求** ] ブレードで、[ **新しい要求の追加** ] を選択するか、既存の要求を編集します。
2. アプリケーション ピッカーから、拡張プロパティが定義されているソース アプリケーションを選択します。
3. [ **追加]** を選択して、選択内容を要求に追加します。
4. [ **保存]** をクリックして変更をコミットします。

### 特別なクレームの変換

特別なクレームの変換関数を使用できます。

| Function | 説明 |
| --- | --- |
| **ExtractMailPrefix()** | メール アドレスまたはユーザー プリンシパル名からドメイン サフィックスを除去します。 この関数は、渡されたユーザー名の最初の部分のみを抽出します (例: joe\_smith@contoso.com ではなく "joe\_smith")。 |
| **ToLower()** | 選択した属性の文字を小文字に変換します。 |
| **ToUpper()** | 選択した属性の文字を大文字に変換します。 |

### アプリケーション固有のクレームを追加する

アプリケーション固有の要求を追加するには:

1. [ **属性と要求** ] ブレードで、[ **新しい要求の追加** ] を選択して、[ **ユーザー要求の管理** ] ページを開きます。
2. 要求の **名前** を入力します。 値は、SAML 仕様に従って URI パターンに厳密に従う必要はありません。URI パターンが必要な場合は、[ **名前空間]** フィールドに配置できます。
3. 要求の値を取得する **ソース** を選択します。 ソース属性のドロップダウンからユーザー属性を選択するか、または要求として生成する前にユーザー属性に変換を適用することができます。

### グループ要求を追加する

グループ クレームは、アプリまたはサービス プロバイダーがリソースにアクセスする際の承認の決定に使用されます。 グループ クレームを追加するには、次の手順に従います。

1. **[アプリの登録]** に移動し、グループ要求を追加するアプリを選択します。
2. [ **グループ要求の追加] を選択します**。
3. トークンに含めるグループの種類を選択します。 セキュリティ グループ、ディレクトリ グループ、または特定のアプリケーションに割り当てられたグループを追加できます。
4. グループ要求に含める値を選択し、[ **追加**] を選択します。

#### 要求の変換

ユーザー属性に変換を適用するには、次の手順を行います。

1. [ **要求の管理**] で、要求ソースとして *[変換* ] を選択し、[ **変換の管理** ] ページを開きます。
2. 変換ドロップダウンから関数を選択します。 選択した関数に応じて、変換で評価するパラメーターと定数値を指定します。
3. 適切なラジオ ボタンをクリックして、属性のソースを選択します。
4. 属性名をドロップダウンから選択します。
5. **ソースを複数値として扱う** チェックボックスは、変換をすべての値に適用するか、最初の値のみに適用するかを示すチェック ボックスです。 既定では、変換は複数値クレームの最初の要素にのみ適用されます。このチェックボックスをオンにすると、すべてに適用されます。 このチェックボックスは、複数の値を持つ属性に対してのみ有効になります (例: `user.proxyaddresses`)。
6. 複数の変換を適用するには、[ **変換の追加]** を選択します。 要求には、最大 2 つの変換を適用できます。 たとえば、最初に `user.mail` のメール プレフィックスを抽出できます。 次に、文字列を大文字にします。

次の関数を使用して、要求を変換できます。

| Function | 説明 |
| --- | --- |
| **ExtractMailPrefix()** | メール アドレスまたはユーザー プリンシパル名からドメイン サフィックスを除去します。 この関数は、渡されるユーザー名の最初の部分のみを抽出します。 たとえば、`joe_smith` の代わりに `joe_smith@contoso.com` となります。 |
| **Join()** | 2 つの属性を結合することで、新しい値を作成します。 必要に応じて、2 つの属性の間に区切り記号を使用できます。 `nameID`要求変換の場合、変換入力にドメイン部分がある場合、**Join()** 関数には特定の動作があります。 入力からドメイン部分を削除した後、区切り記号および選択されたパラメーターを結合します。 たとえば、変換の入力が `joe_smith@contoso.com`、区切り記号が `@`、パラメーターが `fabrikam.com` の場合、この入力の組み合わせでは `joe_smith@fabrikam.com` という結果になります。 |
| **ToLowercase()** | 選択した属性の文字を小文字に変換します。 |
| **ToUppercase()** | 選択した属性の文字を大文字に変換します。 |
| **Contains()** | 入力が指定した値と一致する場合、属性または定数を出力します。 一致しない場合は、別の出力を指定できます。 たとえば、値がユーザーのメール アドレスで、そこに `@contoso.com` というドメインが含まれる場合、そのクレームは出力し、それ以外の場合はユーザーのプリンシパル名を出力したいとします。 この関数を実行するには、`Parameter 1(input): user.email`、`Value: "@contoso.com"`、`Parameter 2 (output): user.email`、`Parameter 3 (output if there's no match): user.userprincipalname` の各値を構成します。 |
| **EndWith()** | 入力が指定した値で終わっている場合、属性または定数を出力します。 一致しない場合は、別の出力を指定できます。たとえば、ユーザーの従業員 ID が `000` で終わっている場合は従業員 ID を値とするクレームを出力し、それ以外の場合は拡張属性を出力するとします。 この関数を実行するには、`Parameter 1(input): user.employeeid`、`Value: "000"`、`Parameter 2 (output): user.employeeid`、`Parameter 3 (output if there's no match): user.extensionattribute1` の各値を構成します。 |
| **StartWith()** | 入力が指定した値で始まっている場合、属性または定数を出力します。 一致しない場合は、別の出力を指定できます。 たとえば、国または地域が `US` で始まっている場合はユーザーの従業員 ID を値とするクレームを出力し、それ以外の場合は拡張属性を出力するとします。 この関数を実行するには、`Parameter 1(input): user.country`、`Value: "US"`、`Parameter 2 (output): user.employeeid`、`Parameter 3 (output if there's no match): user.extensionattribute1` の各値を構成します。 |
| **Extract() - 照合後** | 指定した値との一致より後の部分文字列を返します。 たとえば、入力の値が `Finance_BSimon` で、一致する値が `Finance_` の場合、クレームの出力は `BSimon` です。 |
| **Extract() - 照合する前** | 指定した値との一致より前の部分文字列を返します。 たとえば、入力の値が `BSimon_US` で、一致する値が `_US` の場合、クレームの出力は `BSimon` です。 |
| **Extract() - 一致の間** | 指定した値との一致より前の部分文字列を返します。 たとえば、入力の値が `Finance_BSimon_US`、1 番目の一致する値が `Finance_`、2 番目の一致する値が `_US` の場合、クレームの出力は `BSimon` です。 |
| **ExtractAlpha() - プレフィックス** | 文字列のプレフィックスのアルファベット部分を返します。 たとえば、入力の値が `BSimon_123` の場合、`BSimon` が返されます。 |
| **ExtractAlpha() - サフィックス** | 文字列のサフィックスのアルファベット部分を返します。 たとえば、入力の値が `123_Simon` の場合、`Simon` が返されます。 |
| **ExtractNumeric() - プレフィックス** | 文字列のプレフィックスの数字部分を返します。 たとえば、入力の値が `123_BSimon` の場合、`123` が返されます。 |
| **ExtractNumeric() - サフィックス** | 文字列のサフィックスの数字部分を返します。 たとえば、入力の値が `BSimon_123` の場合、`123` が返されます。 |
| **IfEmpty()** | 入力が null または空の場合、属性または定数を出力します。 たとえば、ユーザーの従業員 ID が空の場合に、拡張属性に格納されている属性を出力するとします。 この関数を実行するには、`Parameter 1(input): user.employeeid`、`Parameter 2 (output): user.extensionattribute1`、`Parameter 3 (output if there's no match): user.employeeid` の各値を構成します。 |
| **IfNotEmpty()** | 入力が null または空ではない場合、属性または定数を出力します。 たとえば、ユーザーの従業員 ID が空ではない場合に、拡張属性に格納されている属性を出力するとします。 この関数を実行するには、`Parameter 1(input): user.employeeid`、`Parameter 2 (output): user.extensionattribute1` の各値を構成します。 |
| **Substring() - 固定長** | 指定した位置にある文字から始まる文字列要求の種類の一部が抽出され、指定した文字数が返されます。 `sourceClaim` は、実行する必要がある変換のクレーム ソースです。 `StartIndex` は、このインスタンス内の substring の 0 から始まる開始文字位置です。 `Length` は、substring の文字の長さです。 たとえば、`sourceClaim - PleaseExtractThisNow`、`StartIndex - 6`、`Length - 11` の場合、`ExtractThis` という出力が生成されます。 |
| **Substring() - EndOfString** | 指定した位置にある文字から始まる文字列要求の種類の一部が抽出され、指定した開始インデックスから要求の残りが返されます。 `sourceClaim` は、実行する必要がある変換のクレーム ソースです。 `StartIndex` は、このインスタンス内の substring の 0 から始まる開始文字位置です。 たとえば、`sourceClaim - PleaseExtractThisNow`、`StartIndex - 6` の場合、`ExtractThisNow` という出力が生成されます。 |
| **RegexReplace()** | 正規表現ベースのクレーム変換の詳細については、次のセクションを参照してください。 |

### 正規表現ベースのクレーム変換

正規表現を使用して要求を変換できます。 正規表現ベースの要求変換を使用する場合、最大 20 個の正規表現置換を行うことができます。

次の画像は、変換の第 1 レベルの例を示したものです。

[Image: 変換の最初のレベルのスクリーンショット。]

次の表に示すアクションは、変換の第 1 レベルに関する情報を提供します。これらは前の画像のラベルに対応しています。 [ **編集] を** 選択して要求変換ブレードを開きます。

| アクション | フィールド | 説明 |
| --- | --- | --- |
| `1` | `Transformation` | **[変換**] オプションから **RegexReplace()** オプションを選択して、要求変換に正規表現ベースの要求変換メソッドを使用します。 |
| `2` | `Parameter 1` | 正規表現変換の入力。 たとえば、`admin@fabrikam.com` のようなユーザー メール アドレスを含む user.mail。 |
| `3` | `Treat source as multivalued` | 一部の入力ユーザー属性は、複数値のユーザー属性になれます。 選択したユーザー属性が複数の値をサポートしていて、変換に複数の値を使用する場合は、[ **ソースを複数値として扱う**] を選択する必要があります。 オンにした場合は、すべての値が正規表現の照合に使われ、オフにした場合は、最初の値のみが使われます。 |
| `4` | `Regex pattern` | "パラメーター 1" として選ばれたユーザー属性の値に対して評価される正規表現。 たとえば、ユーザーのメール アドレスからユーザー エイリアスを抽出する正規表現は、`(?'domain'^.*?)(?i)(\@fabrikam\.com)$` と表されます。 |
| `5` | `Add additional parameter` | 複数のユーザー属性を変換に使用できます。 その場合、属性の値は正規表現の変換出力とマージされます。 最大 5 つの追加パラメーターがサポートされています。 |
| `6` | `Replacement pattern` | 置換パターンは、正規表現の結果に対するプレースホルダーを含むテキスト テンプレートです。 `{group-name}` のように、すべてのグループ名を中かっこで囲む必要があります。 たとえば、管理者は他のドメイン名 (例: `xyz.com`) と共にユーザーの別名を使い、国名をそれとマージしたいとします。 このケースでは、置換パターンは `{country}.{domain}@xyz.com` になります。`{country}` は入力パラメーターの値であり、`{domain}` は正規表現の評価からのグループ出力です。 このようなケースでは、予想される結果は `US.swmal@xyz.com` になります。 |

次の画像は、変換の 2 番目のレベルの例を示したものです。

[Image: 要求変換の第 2 レベルのスクリーンショット。]

次の表では、変換の 2 番目のレベルに関する情報を示します。 表に示されているアクションは、前の画像のラベルに対応しています。

| アクション | フィールド | 説明 |
| --- | --- | --- |
| `1` | `Transformation` | 正規表現ベースのクレーム変換は、第 1 の変換に限定されず、第 2 レベルの変換としても使用できます。 その他のいかなる変換方法も、最初の変換として使用できます。 |
| `2` | `Parameter 1` | **RegexReplace()** が第 2 レベル変換として選択されている場合、第 1 レベル変換の出力が第 2 レベル変換の入力として使用されます。 変換を適用するには、第 2 レベルの正規表現式が、第 1 の変換の出力と一致する必要があります。 |
| `3` | `Regex pattern` | **正規表現パターン** は、第 2 レベル変換の正規表現です。 |
| `4` | `Parameter input` | 第 2 レベルの変換に対するユーザー属性の入力。 |
| `5` | `Parameter input` | パラメーターが不要になった場合、管理者は選んだ入力パラメーターを削除できます。 |
| `6` | `Replacement pattern` | 置換パターンは、正規表現の結果グループ名、入力パラメーター グループ名、静的テキスト値のプレースホルダーを含むテキスト テンプレートです。 `{group-name}` のように、すべてのグループ名を中かっこで囲む必要があります。 たとえば、管理者は他のドメイン名 (例: `xyz.com`) と共にユーザーの別名を使い、国名をそれとマージしたいとします。 このケースでは、置換パターンは `{country}.{domain}@xyz.com` になります。`{country}` は入力パラメーターの値であり、{domain} は正規表現の評価からのグループ出力です。 このようなケースでは、予想される結果は `US.swmal@xyz.com` になります。 |
| `7` | `Test transformation` | RegexReplace() 変換は、 *パラメーター 1* に対して選択されたユーザー属性の値が **、Regex パターン** テキスト ボックスに指定された正規表現と一致する場合にのみ評価されます。 一致しない場合は、既定のクレーム値がトークンに追加されます。 入力パラメーター値に対して正規表現を検証するには、変換ブレード内でテスト エクスペリエンスを使用できます。 このテスト エクスペリエンスはダミー値でのみ動作します。 追加の入力パラメーターを使うと、実際の値ではなく、パラメーターの名前がテスト結果に追加されます。 テスト セクションにアクセスするには、[ **テスト変換**] を選択します。 |

次の図は、変換のテストの例を示したものです。

[Image: 変換のテストのスクリーンショット。]

次の表では、変換のテストに関する情報を示します。 表に示されているアクションは、前の画像のラベルに対応しています。

| アクション | フィールド | 説明 |
| --- | --- | --- |
| `1` | `Test transformation` | [閉じる] または [(X)] ボタンを選択してテスト セクションを非表示にし、ブレードで [ **テスト変換** ] ボタンをもう一度レンダリングします。 |
| `2` | `Test regex input` | 正規表現テストの評価に使われる入力を受け入れます。 正規表現ベースのクレーム変換が第 2 レベルの変換として構成されている場合、第 1 の変換の予想される出力を値として指定します。 |
| `3` | `Run test` | テスト正規表現入力が指定され、 **Regex パターン**、 **置換パターン** 、 **および入力パラメーター** が構成されたら、[ **テストの実行**] を選択して式を評価できます。 |
| `4` | `Test transformation result` | 評価に成功すると、テスト変換の出力が **テスト変換の結果** ラベルに対してレンダリングされます。 |
| `5` | `Remove transformation` | 2 番目のレベルの変換は、[変換の **削除**] を選択して削除できます。 |
| `6` | `Specify output if no match` | *正規表現*と一致しない**パラメーター 1** に対して正規表現入力値が構成されている場合、変換はスキップされます。 このような場合は、代替ユーザー属性を構成できます。これは、 **一致しない場合に出力を指定**するチェック をオンにして、要求のトークンに追加されます。 |
| `7` | `Parameter 3` | 一致しない場合に**「一致がない場合の出力を指定」が選択されているときに、**代替ユーザー属性を返す必要がある場合は、ドロップダウンを使用して代替ユーザー属性を選択できます。 このドロップダウンは **、パラメーター 3 (一致しない場合の出力)** に対して使用できます。 |
| `8` | `Summary` | ブレードの下部には、簡単なテキストで変換の意味を説明するフォーマットの概要が表示されます。 |
| `9` | `Add` | 変換の構成設定が検証されたら、[ **追加**] を選択して要求ポリシーに保存できます。 [**要求の管理**] ブレードで **[保存]** を選択して変更を保存します。 |

RegexReplace() 変換は、グループ要求変換でも使用できます。

#### RegexReplace() 変換の検証

**[追加**] または [**テストの実行**] を選択した後に次の条件が発生すると、問題に関する詳細情報を示すメッセージが表示されます。

- 重複するユーザー属性を持つ入力パラメーターは許可されません。
- 使われていない入力パラメーターが見つかりました。 定義された入力パラメーターは、置換パターン テキストでそれぞれ使用される必要があります。
- 指定されたテスト正規表現入力が、指定された正規表現と一致しません。
- 置換パターンへのグループのソースが見つかりません。

### SAML トークンへの UPN 要求の追加

`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/upn`要求は、[SAML 制限付き要求セット](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-claims-customization#saml-restricted-claim-set)の一部です。 カスタム署名キーが構成されている場合は、[ **属性] と [要求** ] セクションで追加できます。

カスタム署名キーが構成されていない場合は、 [SAML 制限付き要求セット](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-claims-customization#saml-restricted-claim-set)を参照してください。 Azure portal の[アプリ登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)を使用して、**オプションの要求**として追加できます。

**[アプリの登録**] でアプリケーションを開き、[**トークンの構成**] を選択し、[**省略可能な要求の追加]** を選択します。 **SAML** トークンの種類を選択し、一覧から **upn** を選択し、[**追加**] をクリックしてトークンに要求を追加します。

**[属性と要求**] セクションでカスタマイズを行うと、[**アプリの登録**] の省略可能な要求が上書きされる可能性があります。

### 条件に基づいてクレームを出力する

ユーザーの種類とユーザーが属するグループに基づいて、要求のソースを指定することができます。

ユーザーの種類は次のとおりです。

- **Any** - すべてのユーザーがアプリケーションにアクセスできます。
- **メンバー**: テナントのネイティブ メンバー
- **すべてのゲスト**: ユーザーは、Microsoft Entra ID の有無にかかわらず、外部組織から引き継がれる。
- **Microsoft Entra ゲスト**: ゲスト ユーザーは、Microsoft Entra ID を使用して別の組織に属しています。
- **外部ゲスト**: ゲスト ユーザーは、Microsoft Entra ID を持たない外部組織に属しています。

ユーザーの種類が役立つのは、クレームのソースが、ゲストとアプリケーションにアクセスする従業員とで異なる場合です。 ユーザーが従業員である場合は NameID を user.email から取得するように指定できます。 ユーザーがゲストの場合は、NameID は user.extensionattribute1 から取得されます。

要求条件を追加するには、次の手順を行います。

1. [ **要求の管理]** で、[要求の条件] を展開します。
2. ユーザーの種類を選択します。
3. ユーザーが属するグループを選択します。 特定のアプリケーションに対するすべての要求で、最大 50 個の一意のグループを選択できます。
4. 要求の値を取得する **ソース** を選択します。 ソース属性のドロップダウンからユーザー属性を選択することも、そのユーザー属性に変換を適用することもできます。 また、クレームとして出力する前に、ディレクトリ スキーマ拡張を選択することもできます。

条件を追加する順序は重要です。 Microsoft Entra では、まず、すべての条件をソース `Attribute` で評価し、次に、すべての条件をソース `Transformation` で評価して、クレームに出力する値を決定します。 ソースが同じ条件は、上から下に評価されます。 式と一致する最後の値がクレームに出力されます。 `IsNotEmpty` や `Contains` などの変換は、制限のように機能します。

たとえば、Britta Simon は Contoso テナントのゲスト ユーザーです。 Britta は、Microsoft Entra ID も使用する別の組織に属しています。 Fabrikam アプリケーションが次のように構成されている場合、Britta が Fabrikam にサインインしようとすると、Microsoft ID プラットフォームで条件が評価されます。

まず、Microsoft ID プラットフォームは、Britta のユーザーの種類が **[すべてのゲスト]** であるかどうかを確認します。 種類は **[すべてのゲスト]** であるため、Microsoft ID プラットフォームは要求のソースを `user.extensionattribute1`に割り当てます。 次に、Microsoft ID プラットフォームは、Britta のユーザーの種類が **Microsoft Entra ゲスト**であるかどうかを確認します。 種類は **[すべてのゲスト]** であるため、Microsoft ID プラットフォームは要求のソースを `user.mail`に割り当てます。 最終的に、クレームは Britta の `user.mail` の値を使って出力されます。

もう 1 つの例として、Britta Simon がサインインを試み、次の構成が使用される場合について考えてみます。 まず、すべての条件が `Attribute` のソースで評価されます。 Britta のユーザーの種類は **Microsoft Entra ゲスト**であるため、 `user.mail` は要求のソースとして割り当てられます。 次に、変換が評価されます。 Britta はゲストなので、`user.extensionattribute1` がクレームの新しいソースです。 Britta は **Microsoft Entra ゲスト**にあるため、 `user.othermail` がこの要求のソースになりました。 最終的に、クレームは Britta の `user.othermail` の値を使って出力されます。

最後の例として、Britta の `user.othermail` が構成されていない場合、または空の場合にどうなるかを考えます。 どちらの場合も、条件の入力は無視され、クレームは代わりに `user.extensionattribute1` にフォールバックします。

### SAML クレームの詳細オプション

SAML2.0 アプリケーションで OIDC トークンと同じクレームを公開するように、クレームの詳細オプションを構成できます。また、SAML2.0 と OIDC の両方の応答トークンで同じクレーム使用するアプリケーションの場合は、その逆に構成できます。

高度な要求オプションは、[要求の**管理**] ブレードの [**高度な SAML 要求オプション]** の下にあるチェック ボックスをオンにして構成できます。

次の表は、アプリケーション用に構成できる他の詳細オプションの一覧です。

| 回答内容 | 説明 |
| --- | --- |
| 発行者にアプリケーション ID を付加する | 発行者の要求にアプリケーション ID を自動的に追加します。 このオプションでは、同じアプリケーションのインスタンスが複数ある場合に、各インスタンスに対して一意の要求値が保証されます。 アプリケーションのカスタム署名キーが構成されていない場合、この設定は無視されます。 |
| 対象ユーザーのクレームをオーバーライドする | アプリケーションに送信された対象ユーザーのクレームのオーバーライドを許可します。 指定した値は有効な絶対 URI である必要があります。 アプリケーションのカスタム署名キーが構成されていない場合、この設定は無視されます。 |
| 属性名の形式を含める | 選択した場合、Microsoft Entra ID によって、アプリケーションの制限付き、コア、オプションの各クレームに、名前の形式を記述する `NameFormat` という属性が追加されます。 詳細については、「[要求マッピング ポリシーの種類](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-claims-customization#claim-sets)」を参照してください。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/saml-protocol-reference"} -->
## Microsoft ID プラットフォームでの SAML プロトコルの使用方法 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-protocol-reference
- Service: identity-platform
- Article date: 2022-11-04
- Summary: この記事では、Microsoft Entra ID でのシングル サインオンとシングル Sign-Out SAML プロファイルの概要について説明します。

Microsoft ID プラットフォームでは、SAML 2.0 やその他のプロトコルを使用して、アプリケーションがユーザーにシングル サインオン (SSO) エクスペリエンスを提供できるようにします。 Microsoft Entra ID の [SSO](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol) および [シングル サインアウト](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-out-saml-protocol) SAML プロファイルでは、ID プロバイダー サービスで SAML アサーション、プロトコル、バインディングがどのように使用されるかが説明されています。

SAML プロトコルでは、ID プロバイダー (Microsoft ID プラットフォーム) とサービス プロバイダー (アプリケーション) が自身に関する情報を交換する必要があります。

アプリケーションが Microsoft Entra ID に登録されると、アプリ開発者はフェデレーション関連の情報を Microsoft Entra ID に登録します。 この情報には、アプリケーションの **リダイレクト URI** と **メタデータ URI** が含まれます。

Microsoft ID プラットフォームでは、クラウド サービスの **メタデータ URI を** 使用して、署名キーとログアウト URI を取得します。 これにより、Microsoft ID プラットフォームは正しい URL に応答を送信できます。 [Microsoft Entra 管理センター](https://entra.microsoft.com/)で。

- **Microsoft Entra ID** でアプリを開き、[**アプリの登録**] を選択します
- **[管理]** で、 **[認証]** を選択します。 そこからログアウト URL を更新できます。

Microsoft Entra ID は、テナント固有の共通 (テナントに依存しない) SSO とシングル サインアウト エンドポイントを公開します。 これらの URL はアドレス指定可能な場所を表し、識別子だけではありません。 その後、エンドポイントに移動してメタデータを読み取ることができます。

- テナント固有のエンドポイントは、 `https://login.microsoftonline.com/<TenantDomainName>/FederationMetadata/2007-06/FederationMetadata.xml`にあります。 * &lt;TenantDomainName&gt;* プレースホルダーは、Microsoft Entra テナントの登録済みのドメイン名または TenantID GUID を表します。 たとえば、 `contoso.com` テナントのフェデレーション メタデータは次のとおりです。 `https://login.microsoftonline.com/contoso.com/FederationMetadata/2007-06/FederationMetadata.xml`
- テナントに依存しないエンドポイントは、 `https://login.microsoftonline.com/common/FederationMetadata/2007-06/FederationMetadata.xml`にあります。 このエンドポイント アドレスでは、テナント ドメイン名または ID の代わりに *共通* が表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/sample-v2-code"} -->
## 認証と承認のコード サンプル - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/sample-v2-code
- Service: identity-platform
- Article date: 2025-01-27
- Summary: アプリの種類、言語、フレームワークごとにグループ化された ID プラットフォーム コード サンプルのインデックスは、これらのライブラリでアプリの認証と承認を有効にする方法を示しています。

これらのコード サンプルは、Microsoft ID プラットフォームでの認証ライブラリの使用方法を示すために、Microsoft によって構築および管理されています。 一般的な認証と認可のシナリオは、いくつかの[アプリケーションの種類](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-app-types)、開発言語、およびフレームワークで実装されています。

- Web アプリケーションにユーザーをサインインさせ、保護された Web API への承認されたアクセスを提供します。
- API 操作を実行するためのアクセストークンを要求することで、Web API を保護します。

各コードサンプルには、プロジェクトをビルドする方法 (該当する場合) とサンプル アプリケーションを実行する方法を説明する *README.md* ファイルが含まれています。 コード内のコメントにより、これらのライブラリをアプリケーション内でどのように使用して ID プラットフォームで認証と認可を行うかを理解することができます。

### サンプルとガイド

タブを使用して、アプリケーションの種類または優先の言語/フレームワークごとにサンプルを並べ替えます。

## [アプリの種類別](#tab/apptype)
#### シングルページアプリケーション

これらのサンプルでは、Microsoft ID プラットフォームを使用してセキュリティ保護されているシングルページ アプリケーションの作成方法を示します。 これらのサンプルでは、MSAL.js のいずれかの種類を使用します。

| 言語 /プラットフォーム | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| 反応する | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-docs-code-javascript/tree/main/react-spa) | [MSAL React](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-react) | PKCE を使用した認可コード | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-react-sign-in) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-react-prepare-app) |
| Angular（アンギュラー） | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-docs-code-javascript/tree/main/angular-spa) | [MSAL アンギュラー](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-angular) | PKCE を使用した認可コード | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-angular-sign-in) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-angular-auth-code) |
| JavaScript | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-docs-code-javascript/tree/main/vanillajs-spa) • [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-javascript-tutorial/tree/main/2-Authorization-I/1-call-graph/README.md)• [Node.js Web API の呼び出し](https://github.com/Azure-Samples/ms-identity-javascript-tutorial/tree/main/3-Authorization-II/1-call-api/README.md)• [Azure Storage および App Service へのデプロイ](https://github.com/Azure-Samples/ms-identity-javascript-tutorial/tree/main/4-Deployment/README.md) | [MSAL.js](https://learn.microsoft.com/ja-jp/javascript/api/overview/msal-overview) | PKCE を使用した認可コード | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-sign-in) |  |
| Blazor WebAssembly（ブレイザー ウェブアセンブリ） | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-docs-code-dotnet/tree/main/spa-blazor-wasm)• [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-blazor-wasm/blob/main/WebApp-graph-user/Call-MSGraph/README.md)• [Azure App Service へのデプロイ](https://github.com/Azure-Samples/ms-identity-blazor-wasm/blob/main/Deploy-to-Azure/README.md) | [MSAL.js](https://learn.microsoft.com/ja-jp/javascript/api/overview/msal-overview) | PKCE を使用した認可コード | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-blazor-wasm-sign-in) |  |

#### Web アプリケーション

次のサンプルは、ユーザーがサインインする Web アプリケーションを示しています。 サンプルの中には、Microsoft Graph、またはユーザーの ID を使用してユーザー独自の Web API を呼び出すアプリケーションを示しているものもあります。

| 言語/プラットフォーム | GitHub 上のサンプル コード | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| ASP.NET | • [Microsoft Graph トレーニング サンプル](https://github.com/microsoftgraph/msgraph-training-aspnetmvcapp) • [ユーザーのサインインと、管理者が制限したスコープでの Microsoft Graph の呼び出し](https://github.com/azure-samples/active-directory-dotnet-admin-restricted-scopes-v2) | • [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet)• [マイクロソフト.Identity.Web](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft-authentication-library-dotnet/confidentialclient)• [高度なトークン キャッシュのシナリオ](https://github.com/Azure-Samples/ms-identity-dotnet-advanced-token-cache) | • OpenID 接続 • 承認コード • On-Behalf-Of (OBO) | [クイックスタート](https://github.com/AzureAdQuickstarts/AppModelv2-WebApp-OpenIDConnect-DotNet) |  |
| ASP.NET Core | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-docs-code-dotnet/tree/main/web-app-aspnet)• [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/2-WebApp-graph-user/2-1-Call-MSGraph/README.md)• [トークン キャッシュのカスタマイズ](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/2-WebApp-graph-user/2-2-TokenCache/README.md)• [条件付きアクセス認証コンテキストを使用してステップアップ認証を実行](https://github.com/Azure-Samples/ms-identity-dotnetcore-ca-auth-context-app/blob/main/README.md)• [Call Graph（マルチテナント）](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/2-WebApp-graph-user/2-3-Multi-Tenant/README.md)• [Azure REST API の呼び出し](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/3-WebApp-multi-APIs/README.md)• [Web API の保護](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/4-WebApp-your-API/4-1-MyOrg/README.md)• [マルチテナント Web API の保護](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/4-WebApp-your-API/4-3-AnyOrg/Readme.md)• [アクセス制御にアプリのロールを使用する](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/5-WebApp-AuthZ/5-1-Roles/README.md)• [アクセス制御にセキュリティ グループを使用する](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/5-WebApp-AuthZ/5-2-Groups/README.md)• [Azure Storage および App Service へのデプロイ](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/6-Deploy-to-Azure/README.md)• [Active Directory フェデレーション サービス (AD FS) から Microsoft Entra への移行](https://github.com/Azure-Samples/ms-identity-dotnet-adfs-to-aad) | [マイクロソフト.アイデンティティ.Web](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft-authentication-library-dotnet/confidentialclient) | • OpenID 接続 • 承認コード • On-Behalf-Of フロー (OBO) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-dotnet-core-sign-in) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-dotnet-prepare-app) |
| Blazor | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-docs-code-dotnet/tree/main/spa-blazor-wasm)• [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-blazor-server/tree/main/WebApp-graph-user/Call-MSGraph)• [Web API の呼び出し](https://github.com/Azure-Samples/ms-identity-blazor-server/tree/main/WebApp-your-API/MyOrg) | [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet) | ハイブリッド フロー |  |  |
| Java Spring（ジャバ・スプリング） | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/4-spring-web-app/1-Authentication/sign-in)• [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/4-spring-web-app/2-Authorization-I/call-graph)• [アクセス制御にアプリのロールを使用する](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/4-spring-web-app/3-Authorization-II/roles)• [アクセス制御にグループを使用する](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/4-spring-web-app/3-Authorization-II/groups)• [Web API を保護する](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/4-spring-web-app/3-Authorization-II/protect-web-api)• [Azure App Service へのデプロイ](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/4-spring-web-app/4-Deployment/deploy-to-azure-app-service) | [MSAL Java](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j) | Authorization code (承認コード) |  | [チュートリアル](https://learn.microsoft.com/ja-jp/azure/developer/java/spring-framework/configure-spring-boot-starter-java-app-with-azure-active-directory) |
| Java サーブレット | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/3-java-servlet-web-app/1-Authentication/sign-in)• [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/3-java-servlet-web-app/2-Authorization-I/call-graph)• [アクセス制御にアプリのロールを使用する](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/3-java-servlet-web-app/3-Authorization-II/roles)• [アクセス制御にセキュリティ グループを使用する](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/3-java-servlet-web-app/3-Authorization-II/groups)• [Azure App Service へのデプロイ](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/3-java-servlet-web-app/4-Deployment/deploy-to-azure-app-service) | [MSAL Java](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j) | Authorization code (承認コード) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-java-sign-in) |  |
| Node.js エクスプレス | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-javascript-nodejs-tutorial/blob/main/1-Authentication/1-sign-in/README.md)• [MSAL Node と Microsoft ID プラットフォームを使用して構築された Express Web アプリケーション](https://github.com/Azure-Samples/ms-identity-node/blob/main/README.md)• [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-javascript-nodejs-tutorial/blob/main/2-Authorization/1-call-graph/README.md)• [アクセス制御にアプリのロールを使用する](https://github.com/Azure-Samples/ms-identity-javascript-nodejs-tutorial/blob/main/4-AccessControl/1-app-roles/README.md)• [アクセス制御にセキュリティ グループを使用する](https://github.com/Azure-Samples/ms-identity-javascript-nodejs-tutorial/blob/main/4-AccessControl/2-security-groups/README.md)• [Azure App Service へのデプロイ](https://github.com/Azure-Samples/ms-identity-javascript-nodejs-tutorial/blob/main/3-Deployment/README.md) | [MSAL ノード](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node) | • 承認コード • Backend-for-Frontend (BFF) プロキシ | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-nodejs-sign-in) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-nodejs-webapp-msal) |
| パイソンフラスコ | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-docs-code-python/tree/main/flask-web-app)• [Template を使用して Microsoft Entra ID にサインインし、必要に応じてダウンストリーム API を呼び出します (Microsoft Graph)](https://github.com/Azure-Samples/ms-identity-python-webapp) | [MSAL Python](https://learn.microsoft.com/ja-jp/entra/msal/python) | Authorization code (承認コード) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-python-flask) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-python-register-app) |
| Pythonジャンゴ | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-docs-code-python/tree/main/django-web-app) | [MSAL Python](https://learn.microsoft.com/ja-jp/entra/msal/python) | Authorization code (承認コード) |  |  |
| Ruby | • [ユーザーのサインインと Microsoft Graph の呼び出し](https://github.com/microsoftgraph/msgraph-training-rubyrailsapp) | OmniAuth OAuth2 | Authorization code (承認コード) |  |  |

#### Web API

次のサンプルは、Microsoft ID プラットフォームで Web API を保護する方法と、その Web API からダウンストリーム API を呼び出す方法を示しています。

| 言語 /プラットフォーム | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| ASP.NET | • [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-aspnet-webapi-onbehalfof) | [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet) | On-Behalf-Of (OBO) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-api-aspnet-protect-api) |  |
| ASP.NET Core | • [Microsoft ID プラットフォームによるアクセス制御 (保護されたルート)](https://github.com/Azure-Samples/ms-identity-docs-code-dotnet/tree/main/web-api) | [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet) | On-Behalf-Of (OBO) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-api-aspnet-core-protect-api) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-api-dotnet-register-app) |
| Java | • [Microsoft ID プラットフォームを使用して Java Spring Boot Web API を保護する](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/4-spring-web-app/3-Authorization-II/protect-web-api) | [MSAL Java](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j) | On-Behalf-Of (OBO) |  |  |
| Node.js | • [Node.js Web API の保護](https://github.com/Azure-Samples/ms-identity-javascript-tutorial/tree/main/3-Authorization-II/1-call-api) | [MSAL ノード](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node) | 認可ベアラー |  |  |

#### デスクトップ

次のサンプルは、ユーザーの名前で Microsoft Graph API またはお客様独自の Web API にアクセスするパブリック クライアント デスクトップ アプリケーションを示しています。 "*Web Authentication Manager (WAM) を使用するデスクトップ (コンソール)*" を除き、これらのすべてのクライアント アプリケーションでは Microsoft Authentication Library (MSAL) が使用されます。

| 言語 /プラットフォーム | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| .NET コア | • [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-dotnet-desktop-tutorial/tree/master/1-Calling-MSGraph/1-1-AzureAD) • [トークン キャッシュを使用した Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-dotnet-desktop-tutorial/tree/master/2-TokenCache) • [カスタム Web UI HTML を使用して Microsoft Graph を呼び出す](https://github.com/Azure-Samples/ms-identity-dotnet-desktop-tutorial/tree/master/3-CustomWebUI/3-1-CustomHTML) • [カスタム Web ブラウザーを使用した Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-dotnet-desktop-tutorial/tree/master/3-CustomWebUI/3-2-CustomBrowser) • [デバイス コード フローを使用してユーザーをサインインさせる](https://github.com/Azure-Samples/ms-identity-dotnet-desktop-tutorial/tree/master/4-DeviceCodeFlow)• [ユーザー名/パスワードを使用したユーザーのサインインによる Microsoft Graph の呼び出し](https://github.com/azure-samples/active-directory-dotnetcore-console-up-v2) | [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet) | • PKCE を使用した認可コード  • デバイス コード • リソース所有者のパスワード資格情報 |  |  |
| Java | • [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/2-client-side/Integrated-Windows-Auth-Flow) | [MSAL Java](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j) | 統合 Windows 認証 |  |  |
| Node.js | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-javascript-nodejs-desktop) | [MSAL ノード](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node) | PKCE を使用した認可コード | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-desktop-app-nodejs-electron-sign-in) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-nodejs-desktop) |
| Python | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-python-desktop) | [MSAL Python](https://learn.microsoft.com/ja-jp/entra/msal/python) | リソース所有者のパスワード資格情報 |  |  |
| Windows プレゼンテーション ファンデーション (WPF) | • [ユーザーのサインインと Microsoft Graph の呼び出し](https://github.com/Azure-Samples/active-directory-dotnet-native-aspnetcore-v2/tree/master/2.%20Web%20API%20now%20calls%20Microsoft%20Graph)• [Windows Presentation Foundation (WPF) ユーザー サインイン、保護された Web API アクセス (Microsoft Graph)](https://github.com/Azure-Samples/ms-identity-docs-code-dotnet/tree/main/desktop-wpf)• [ユーザーのサインインと ASP.NET Core Web API の呼び出し](https://github.com/Azure-Samples/active-directory-dotnet-native-aspnetcore-v2/tree/master/1.%20Desktop%20app%20calls%20Web%20API) • [ユーザーのサインインと Microsoft Graph の呼び出し](https://github.com/azure-samples/active-directory-dotnet-desktop-msgraph-v2) | [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet) | PKCE を使用した認可コード | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-desktop-app-uwp-sign-in) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-desktop-app-wpf-sign-in) |

#### モバイル

次のサンプルは、Microsoft Graph API にアクセスするパブリック クライアント モバイル アプリケーションを示しています。 これらのクライアント アプリケーションでは、Microsoft Authentication Library (MSAL) が使用されます。

| 言語 /プラットフォーム | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| .NET コア | • [MAUI を使用して Microsoft Graph を呼び出す](https://github.com/Azure-Samples/ms-identity-dotnetcore-maui/tree/main/MauiAppBasic) • [MAUI とブローカーを使用して Microsoft Graph を呼び出す](https://github.com/Azure-Samples/ms-identity-dotnetcore-maui/tree/main/MauiAppWithBroker) | [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet) | PKCE を使用した認可コード |  |  |
| iOS | • [Microsoft Graph ネイティブの呼び出し](https://github.com/Azure-Samples/ms-identity-mobile-apple-swift-objc) | [MSAL iOS](https://github.com/AzureAD/microsoft-authentication-library-for-objc) | PKCE を使用した認可コード | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-ios-sign-in) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-ios) |
| Java | • [ユーザーのサインインと Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-android-java) | [MSAL アンドロイド](https://github.com/AzureAD/microsoft-authentication-library-for-android) | PKCE を使用した認可コード | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-android-sign-in) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-android) |
| コトリン | • [ユーザーのサインインと Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-android-kotlin) | [MSAL アンドロイド](https://github.com/AzureAD/microsoft-authentication-library-for-android) | PKCE を使用した認可コード |  |  |

#### サービス/デーモン

以下に、アプリケーション独自の ID (ユーザーなしで) で Microsoft Graph API にアクセスするサンプル アプリケーションを示します。

| 言語 /プラットフォーム | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| .NET | • [保護された Web API にアクセスする .NET コンソール アプリ](https://github.com/Azure-Samples/ms-identity-docs-code-dotnet/tree/main/console-daemon) • [Microsoft ID プラットフォーム エンドポイントを使用するマルチテナント](https://github.com/Azure-Samples/ms-identity-aspnet-daemon-webapp) | [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet) | クライアント資格情報付与 | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-daemon-dotnet-acquire-token) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-aspnet-daemon-web-app) |
| .NET コア | • [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2/tree/master/1-Call-MSGraph) • [Web API の呼び出し](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2/tree/master/2-Call-OwnApi) • [マネージド ID を使用して MSGraph を呼び出す](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2/tree/master/5-Call-MSGraph-ManagedIdentity) • [マネージド ID を使用して API を呼び出す](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2/tree/master/6-Call-OwnApi-ManagedIdentity) • [API を呼び出すワーカー ロール](https://github.com/AzureAD/microsoft-identity-web/tree/master/tests/DevApps/ContosoWorker) | マイクロソフト.アイデンティティ.Web | クライアント資格情報付与 |  |  |
| Java | • [シークレットを使用した Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/1-server-side/msal-client-credential-secret) • [証明書を使用した Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/1-server-side/msal-client-credential-certificate) | [MSAL Java](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j) | クライアント資格情報付与 | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-daemon-app-java-acquire-token) |  |
| Node.js | • [シークレットを使用した Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-javascript-nodejs-console) | [MSAL ノード](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node) | クライアント資格情報付与 | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-console-app-nodejs-acquire-token) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-nodejs-console) |
| Python | • [シークレットを使用した Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-python-daemon/tree/master/1-Call-MsGraph-WithSecret) • [証明書を使用した Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-python-daemon/tree/master/2-Call-MsGraph-WithCertificate) | [MSAL Python](https://learn.microsoft.com/ja-jp/entra/msal/python) | クライアント資格情報付与 | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-daemon-app-python-acquire-token) |  |

#### ブラウザーレス (ヘッドレス)

次のサンプルは、Web ブラウザーがないデバイスで実行されるパブリック クライアント アプリケーションを示しています。 このアプリは、コマンド ライン ツール、Linux または Mac 上で実行されるアプリ、あるいは IoT アプリケーションにすることもできます。 このサンプルでは、Microsoft Graph API にアクセスするアプリを、別のデバイス (携帯電話など) で対話形式でサインインするユーザーの名前で示します。 このクライアント アプリケーションでは、Microsoft Authentication Library (MSAL) を使用します。

| 言語 /プラットフォーム | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| .NET コア | • [テキストのみのデバイスから保護された API を呼び出す](https://github.com/azure-samples/active-directory-dotnetcore-devicecodeflow-v2) | [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet) | デバイス コード |  |  |
| Java | • [ユーザーに署名し、テキストのみのデバイスから保護された API を呼び出す](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/2-client-side/Device-Code-Flow) | [MSAL Java](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j) | デバイス コード |  |  |
| Python | • [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-python-devicecodeflow) | [MSAL Python](https://learn.microsoft.com/ja-jp/entra/msal/python) | デバイス コード |  |  |

#### Web API としての Azure Functions

次のサンプルは、HttpTrigger を使用し、Microsoft ID プラットフォームで Web API を公開して Azure Function を保護する方法と、その Web API からダウンストリーム API を呼び出す方法を示しています。

| 言語 /プラットフォーム | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| Python | • [Microsoft Entra ID によってセキュリティ保護されたPython Azure 関数 Web API](https://github.com/Azure-Samples/ms-identity-python-webapi-azurefunctions) | [MSAL Python](https://learn.microsoft.com/ja-jp/entra/msal/python) | Authorization code (承認コード) |  |  |

#### Microsoft Teams アプリケーション

次のサンプルは、ユーザーをサインインさせる Microsoft Teams Tab アプリケーションを示しています。 また、Microsoft Authentication Library (MSAL) を使用して、ユーザーの ID で Microsoft Graph API を呼び出す方法についても説明します。

| 言語 /プラットフォーム | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| Node.js | • [Teams タブ アプリ: シングル サインオン (SSO) と Microsoft Graph の呼び出し](https://github.com/OfficeDev/Microsoft-Teams-Samples/tree/main/samples/tab-sso/nodejs) | [MSAL ノード](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node) | On-Behalf-Of (OBO) |  |  |

#### マルチテナント SaaS

次のサンプルでは、任意の Microsoft Entra テナントからのサインインを受け入れるようにアプリケーションを構成する方法を示します。 アプリケーションを *マルチテナントに構成する* は、多くの組織に **Software as a Service** (SaaS) アプリケーションを提供し、ユーザーが同意した後にアプリケーションにサインインできることを意味します。

| 言語 /プラットフォーム | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| ASP.NET Core | • [ASP.NET Core MVC Web アプリケーションによる Microsoft Graph API の呼び出し](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/tree/master/2-WebApp-graph-user/2-3-Multi-Tenant) • [ASP.NET Core MVC Web アプリケーションで ASP.NET Core Web API を呼び出す](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/tree/master/4-WebApp-your-API/4-3-AnyOrg) | [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet) | • OpenID 接続  • 承認コード |  |  |

## [言語/フレームワーク別](#tab/framework)
#### C#

次のサンプルは、C# 言語とフレームワークを使用してアプリケーションをビルドする方法を示しています

##### .NET コア

| アプリの種類 | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| デスクトップ | • [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-dotnet-desktop-tutorial/tree/master/1-Calling-MSGraph/1-1-AzureAD) • [トークン キャッシュを使用した Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-dotnet-desktop-tutorial/tree/master/2-TokenCache) • [カスタム Web UI HTML を使用して Microsoft Graph を呼び出す](https://github.com/Azure-Samples/ms-identity-dotnet-desktop-tutorial/tree/master/3-CustomWebUI/3-1-CustomHTML) • [カスタム Web ブラウザーを使用した Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-dotnet-desktop-tutorial/tree/master/3-CustomWebUI/3-2-CustomBrowser) • [デバイス コード フローを使用してユーザーをサインインさせる](https://github.com/Azure-Samples/ms-identity-dotnet-desktop-tutorial/tree/master/4-DeviceCodeFlow)• [ユーザー名/パスワードを使用したユーザーのサインインによる Microsoft Graph の呼び出し](https://github.com/azure-samples/active-directory-dotnetcore-console-up-v2) | [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet) | • PKCE を使用した認可コード  • デバイス コード |  |  |
| モバイル | • [MAUI を使用して Microsoft Graph を呼び出す](https://github.com/Azure-Samples/ms-identity-dotnetcore-maui/tree/main/MauiAppBasic) • [MAUI とブローカーを使用して Microsoft Graph を呼び出す](https://github.com/Azure-Samples/ms-identity-dotnetcore-maui/tree/main/MauiAppWithBroker) | [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet) | PKCE を使用した認可コード |  |  |
| サービス/ |

デーモン • [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2/tree/master/1-Call-MSGraph) • [Web API の呼び出し](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2/tree/master/2-Call-OwnApi) • [マネージド ID と Azure Key Vault の使用](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2/tree/master/3-Using-KeyVault) | [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet) | クライアント資格情報付与 |  |  || ヘッドレス | • [テキストのみのデバイスから保護された API を呼び出す](https://github.com/azure-samples/active-directory-dotnetcore-devicecodeflow-v2) | [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet) | デバイス コード |  |  |

---

##### ASP.NET

| アプリの種類 | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| Web アプリケーション | • [Microsoft Graph トレーニング サンプル](https://github.com/microsoftgraph/msgraph-training-aspnetmvcapp) • [ユーザーのサインインと、管理者が制限したスコープでの Microsoft Graph の呼び出し](https://github.com/azure-samples/active-directory-dotnet-admin-restricted-scopes-v2) | [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet) | • OpenID 接続  • 承認コード | [クイックスタート](https://github.com/AzureAdQuickstarts/AppModelv2-WebApp-OpenIDConnect-DotNet) |  |
| Web API | • [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-aspnet-webapi-onbehalfof) | [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet) | On-Behalf-Of (OBO) |  |  |
| サービス/デーモン | • [Microsoft ID プラットフォーム エンドポイントを使用するマルチテナント](https://github.com/Azure-Samples/ms-identity-aspnet-daemon-webapp) | [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet) | クライアント資格情報付与 |  |  |

##### ASP.NET Core

| アプリの種類 | コード サンプルGitHub 上 | Authライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| Web アプリケーション | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-docs-code-dotnet/tree/main/web-app-aspnet)• [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/2-WebApp-graph-user/2-1-Call-MSGraph/README.md)• [トークン キャッシュのカスタマイズ](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/2-WebApp-graph-user/2-2-TokenCache/README.md)• [条件付きアクセス認証コンテキストを使用してステップアップ認証を実行](https://github.com/Azure-Samples/ms-identity-dotnetcore-ca-auth-context-app/blob/main/README.md)• [コール グラフ (マルチテナント)](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/2-WebApp-graph-user/2-3-Multi-Tenant/README.md)• [Azure REST API の呼び出し](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/3-WebApp-multi-APIs/README.md)• [Web API の保護](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/4-WebApp-your-API/4-1-MyOrg/README.md)• [マルチテナント Web API の保護](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/4-WebApp-your-API/4-3-AnyOrg/Readme.md)• [アクセス制御にアプリのロールを使用する](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/5-WebApp-AuthZ/5-1-Roles/README.md)• [アクセス制御にセキュリティ グループを使用する](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/5-WebApp-AuthZ/5-2-Groups/README.md)• [Azure Storage および App Service へのデプロイ](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/6-Deploy-to-Azure/README.md)• [Active Directory フェデレーション サービス (AD FS) から Microsoft Entra への移行](https://github.com/Azure-Samples/ms-identity-dotnet-adfs-to-aad)• [Active Directory フェデレーション サービス (AD FS) から Microsoft Entra への移行](https://github.com/Azure-Samples/ms-identity-dotnet-adfs-to-aad)[条件付きアクセス認証コンテキストを使用して、ステップアップ認証を実行する](https://github.com/Azure-Samples/ms-identity-dotnetcore-ca-auth-context-app/blob/main/README.md)[高度なトークン キャッシュのシナリオ](https://github.com/Azure-Samples/ms-identity-dotnet-advanced-token-cache) | [マイクロソフト.アイデンティティ.Web](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft-authentication-library-dotnet/confidentialclient) | • OpenID 接続  • 承認コード • On-Behalf-Of | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-dotnet-core-sign-in) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-dotnet-prepare-app) |
| Web API | • [ユーザーのサインインと Microsoft Graph の呼び出し](https://github.com/Azure-Samples/active-directory-dotnet-native-aspnetcore-v2/tree/master/2.%20Web%20API%20now%20calls%20Microsoft%20Graph) | [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet) | On-Behalf-Of (OBO) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-api-aspnet-core-protect-api) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-api-dotnet-register-app) |
| マルチテナント SaaS | • [ASP.NET Core MVC Web アプリケーションによる Microsoft Graph API の呼び出し](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/tree/master/2-WebApp-graph-user/2-3-Multi-Tenant)• [ASP.NET Core MVC Web アプリケーションで ASP.NET Core Web API を呼び出す](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/tree/master/4-WebApp-your-API/4-3-AnyOrg) | [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet) | OpenID Connect |  |  |

##### Blazor

| アプリの種類 | コード サンプルGitHub 上 | Authライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| シングルページ アプリケーション | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-docs-code-dotnet/tree/main/spa-blazor-wasm)• [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-blazor-wasm/blob/main/WebApp-graph-user/Call-MSGraph/README.md)• [Azure App Service へのデプロイ](https://github.com/Azure-Samples/ms-identity-blazor-wasm/blob/main/Deploy-to-Azure/README.md) | [MSAL.js](https://learn.microsoft.com/ja-jp/javascript/api/overview/msal-overview) | 暗黙的なフロー | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-blazor-wasm-sign-in) |  |
| Web アプリケーション | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-blazor-server/tree/main/WebApp-OIDC/MyOrg) • [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-blazor-server/tree/main/WebApp-graph-user/Call-MSGraph) • [Web API の呼び出し](https://github.com/Azure-Samples/ms-identity-blazor-server/tree/main/WebApp-your-API/MyOrg) | [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet) | インプリシット・フロー／ハイブリッド・フロー |  |  |

#### iOS

次のサンプルは、iOS プラットフォーム用のアプリケーションをビルドする方法を示しています。

| アプリの種類 | コード サンプルGitHub 上 | Authライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| モバイル | • [Microsoft Graph ネイティブの呼び出し](https://github.com/Azure-Samples/ms-identity-mobile-apple-swift-objc) | [MSAL iOS](https://github.com/AzureAD/microsoft-authentication-library-for-objc) | PKCE を使用した認可コード | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-ios-sign-in) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-ios) |

#### JavaScript

##### Vanilla JavaScript

次のサンプルは、JavaScript 言語とプラットフォーム用のアプリケーションをビルドする方法を示しています。

| アプリの種類 | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| シングルページアプリケーション | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-docs-code-javascript/tree/main/vanillajs-spa) • [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-javascript-tutorial/tree/main/2-Authorization-I/1-call-graph/README.md)• [Node.js Web API の呼び出し](https://github.com/Azure-Samples/ms-identity-javascript-tutorial/tree/main/3-Authorization-II/1-call-api/README.md)• [Azure Storage および App Service へのデプロイ](https://github.com/Azure-Samples/ms-identity-javascript-tutorial/tree/main/4-Deployment/README.md) | [MSAL.js](https://learn.microsoft.com/ja-jp/javascript/api/overview/msal-overview) | PKCE を使用した認可コード | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-sign-in) |  |

##### Angular（アンギュラー）

| アプリの種類 | コード サンプルGitHub 上 | Authライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| シングルページ アプリケーション | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-docs-code-javascript/tree/main/angular-spa) | [MSAL アンギュラー](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-angular) | PKCE を使用した認可コード | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-angular-sign-in) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-angular-auth-code) |

##### Node.js

| アプリの種類 | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| Web API | • [Node.js Web API の保護](https://github.com/Azure-Samples/ms-identity-javascript-tutorial/tree/main/3-Authorization-II/1-call-api) | [MSAL ノード](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node) | 認可ベアラー |  |  |
| デスクトップ | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-javascript-nodejs-desktop) | [MSAL ノード](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node) | PKCE を使用した認可コード |  | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-nodejs-desktop) |
| サービス、デーモン | • [シークレットを使用した Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-javascript-nodejs-console) | [MSAL ノード](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node) | クライアント資格情報付与 | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-console-app-nodejs-acquire-token) |  |
| Microsoft Teams アプリケーション | • [Teams タブ アプリ: シングル サインオン (SSO) と Microsoft Graph の呼び出し](https://github.com/OfficeDev/Microsoft-Teams-Samples/tree/main/samples/tab-sso/nodejs) | [MSAL ノード](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node) | On-Behalf-Of (OBO) |  |  |

##### Node.js(エクスプレス)

| アプリの種類 | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| Web アプリケーション | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-javascript-nodejs-tutorial/blob/main/1-Authentication/1-sign-in/README.md) • [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-javascript-nodejs-tutorial/blob/main/2-Authorization/1-call-graph/README.md) • [Azure App Service へのデプロイ](https://github.com/Azure-Samples/ms-identity-javascript-nodejs-tutorial/blob/main/3-Deployment/README.md) • [アクセス制御にアプリのロールを使用する](https://github.com/Azure-Samples/ms-identity-javascript-nodejs-tutorial/blob/main/4-AccessControl/1-app-roles/README.md) • [アクセス制御にセキュリティ グループを使用する](https://github.com/Azure-Samples/ms-identity-javascript-nodejs-tutorial/blob/main/4-AccessControl/2-security-groups/README.md) • [MSAL Node と Microsoft ID プラットフォームを使用して構築された Express Web アプリケーション](https://github.com/Azure-Samples/ms-identity-node) | [MSAL ノード](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node) | Authorization code (承認コード) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-nodejs-sign-in) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-nodejs-webapp-msal) |

##### 反応する

| アプリの種類 | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| シングルページ アプリケーション | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-docs-code-javascript/tree/main/react-spa) | [MSAL React](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-react) | • PKCE を使用した認可コード | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-react-sign-in) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-react-prepare-app) |

#### Java

次のサンプルは、Java 言語とプラットフォーム用のアプリケーションをビルドする方法を示しています。

| アプリの種類 | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| Web API | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/4-spring-web-app/3-Authorization-II/protect-web-api) | [MSAL Java](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j) | On-Behalf-Of (OBO) |  |  |
| デスクトップ | • [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/2-client-side/Integrated-Windows-Auth-Flow) | [MSAL Java](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j) | 統合 Windows 認証 |  |  |
| モバイル | • [ユーザーのサインインと Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-android-java) | [MSAL アンドロイド](https://github.com/AzureAD/microsoft-authentication-library-for-android) | PKCE を使用した認可コード |  |  |
| サービス/デーモン | • [シークレットを使用した Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/1-server-side/msal-client-credential-secret) • [証明書を使用した Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/1-server-side/msal-client-credential-certificate) | [MSAL Java](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j) | クライアント資格情報付与 | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-daemon-app-java-acquire-token) |  |

##### Java Spring（ジャバ・スプリング）

| アプリの種類 | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| Web アプリケーション | Microsoft Entra Spring Boot Starter シリーズ  • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/4-spring-web-app/1-Authentication/sign-in) • [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/4-spring-web-app/2-Authorization-I/call-graph) • [アクセス制御にアプリのロールを使用する](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/4-spring-web-app/3-Authorization-II/roles) • [アクセス制御にグループを使用する](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/4-spring-web-app/3-Authorization-II/groups) • [Azure App Service へのデプロイ](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/4-spring-web-app/4-Deployment/deploy-to-azure-app-service) • [Web API を保護する](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/4-spring-web-app/3-Authorization-II/protect-web-api) | • [MSAL Java](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j) • Microsoft Entra ID Boot Starter（マイクロソフト エントラ ID ブートスターター） | Authorization code (承認コード) |  | [チュートリアル](https://learn.microsoft.com/ja-jp/azure/developer/java/spring-framework/configure-spring-boot-starter-java-app-with-azure-active-directory) |

##### Java サーブレット

| アプリの種類 | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| Web アプリケーション | Spring-less サーブレット シリーズ  • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/3-java-servlet-web-app/1-Authentication/sign-in) • [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/3-java-servlet-web-app/2-Authorization-I/call-graph) • [アクセス制御にアプリのロールを使用する](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/3-java-servlet-web-app/3-Authorization-II/roles) • [アクセス制御にセキュリティ グループを使用する](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/3-java-servlet-web-app/3-Authorization-II/groups) • [Azure App Service へのデプロイ](https://github.com/Azure-Samples/ms-identity-msal-java-samples/tree/main/3-java-servlet-web-app/4-Deployment/deploy-to-azure-app-service) | [MSAL Java](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j) | Authorization code (承認コード) |  |  |

#### Python

次のサンプルは、Python 言語とプラットフォーム向けのアプリケーションをビルドする方法を示しています。

| アプリの種類 | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| Web API としての Azure Functions | • [Microsoft Entra ID によってセキュリティ保護されたPython Azure 関数 Web API](https://github.com/Azure-Samples/ms-identity-python-webapi-azurefunctions) | [MSAL Python](https://learn.microsoft.com/ja-jp/entra/msal/python) | Authorization code (承認コード) |  |  |
| デスクトップ | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-python-desktop) | [MSAL Python](https://learn.microsoft.com/ja-jp/entra/msal/python) | リソース所有者のパスワード資格情報 |  |  |
| ヘッドレス | • [Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-python-devicecodeflow) | [MSAL Python](https://learn.microsoft.com/ja-jp/entra/msal/python) | デバイス コード |  |  |
| デーモン | • [シークレットを使用した Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-python-daemon/tree/master/1-Call-MsGraph-WithSecret) • [証明書を使用した Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-python-daemon/tree/master/2-Call-MsGraph-WithCertificate) | [MSAL Python](https://learn.microsoft.com/ja-jp/entra/msal/python) | クライアント資格情報付与 | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-daemon-app-python-acquire-token) |  |

##### Flask

| アプリの種類 | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| Web アプリケーション | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-docs-code-python/tree/main/flask-web-app)• [Microsoft Entra ID にサインインし、必要に応じてダウンストリーム API を呼び出すテンプレート (Microsoft Graph)](https://github.com/Azure-Samples/ms-identity-python-webapp) | [MSAL Python](https://learn.microsoft.com/ja-jp/entra/msal/python) | Authorization code (承認コード) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-python-flask) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-python-register-app) |

##### Django

| アプリの種類 | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| Web アプリケーション | • [ユーザーのサインイン](https://github.com/Azure-Samples/ms-identity-docs-code-python/tree/main/django-web-app) • [Django で記述された Python Web アプリケーションと Microsoft Entra ID の統合](https://github.com/Azure-Samples/ms-identity-python-webapp-django) | [MSAL Python](https://learn.microsoft.com/ja-jp/entra/msal/python/) | Authorization code (承認コード) |  |  |

#### コトリン

次のサンプルは、Kotlin を使用してアプリケーションをビルドする方法を示しています。

| アプリの種類 | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| モバイル | • [ユーザーのサインインと Microsoft Graph の呼び出し](https://github.com/Azure-Samples/ms-identity-android-kotlin) | [MSAL アンドロイド](https://github.com/AzureAD/microsoft-authentication-library-for-android) | PKCE を使用した認可コードフロー |  |  |

#### Ruby

次のサンプルは、Ruby を使用してアプリケーションをビルドする方法を示しています。

| アプリの種類 | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| Web アプリケーション | グラフのトレーニング  • [ユーザーのサインインと Microsoft Graph の呼び出し](https://github.com/microsoftgraph/msgraph-training-rubyrailsapp) | OmniAuth OAuth2 | Authorization code (承認コード) |  |  |

#### Windows プレゼンテーション ファンデーション (WPF)

次のサンプルは、Windows Presentation Foundation (WPF) を使用してアプリケーションをビルドする方法を示しています。

| アプリの種類 | コード サンプルGitHub 上 | 認証ライブラリ | 認証フロー | クイック スタート | チュートリアル |
| --- | --- | --- | --- | --- | --- |
| デスクトップ | • [ユーザーのサインインと Microsoft Graph の呼び出し](https://github.com/Azure-Samples/active-directory-dotnet-native-aspnetcore-v2/tree/master/2.%20Web%20API%20now%20calls%20Microsoft%20Graph) | [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet) | PKCE を使用した認可コード |  |  |
| デスクトップ | • [ユーザーのサインインと ASP.NET Core Web API の呼び出し](https://github.com/Azure-Samples/active-directory-dotnet-native-aspnetcore-v2/tree/master/1.%20Desktop%20app%20calls%20Web%20API) • [ユーザーのサインインと Microsoft Graph の呼び出し](https://github.com/azure-samples/active-directory-dotnet-desktop-msgraph-v2) | [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/) | PKCE を使用した認可コード | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-desktop-app-uwp-sign-in) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-desktop-app-wpf-sign-in) |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-daemon-acquire-token"} -->
## デーモン アプリケーションを使用して Web API を呼び出すためのトークンを取得する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-daemon-acquire-token
- Service: identity-platform / workforce
- Article date: 2026-06-15
- Summary: Web API を呼び出すデーモン アプリ (トークンを取得する) をビルドする方法について学習します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

### 機密クライアント アプリケーションのトークンを取得する

ユーザーの操作なしで実行されるデーモン アプリケーションやその他の機密クライアント アプリは、OAuth 2.0 クライアント資格情報フローを使用してアプリ専用アクセス トークンを取得します。 この記事では、必要なスコープを構成し、 `AcquireTokenForClient` API (または同等のプラットフォーム) を呼び出してトークンを取得し、一般的なエラーのトラブルシューティングを行う方法について説明します。 先に進む前に、機密クライアント アプリケーションが既に作成されていることを確認します。

### 要求するスコープ

クライアントの資格情報フローのために要求するスコープは、後に `/.default` が続くリソースの名前です。 `/.default`表記は、アプリケーションの登録時に静的に宣言された*アプリケーション レベルのアクセス許可*を使用するようにMicrosoft Entra IDに指示します。 また、これらの API アクセス許可は、テナント管理者によって付与される必要があります。

## [.NET](#tab/idweb)
[*appsettings.json*](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2/blob/master/2-Call-OwnApi/daemon-console/appsettings.json) ファイルの構成の一部として Web API のスコープを定義する例を次に示します。 この例は、GitHub の [.NET コンソール デーモン](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2)のコード サンプルから引用しています。

```json
{
    "AzureAd": {
        // Same AzureAd section as before.
    },

    "MyWebApi": {
        "BaseUrl": "https://localhost:44372/",
        "RelativePath": "api/TodoList",
        "RequestAppToken": true,
        "Scopes": [ "[Enter here the scopes for your web API]" ]
    }
}
```

## [ジャワ](#tab/java)
Javaで、既定のMicrosoft Graph スコープを定数として定義します。

```Java
final static String GRAPH_DEFAULT_SCOPE = "https://graph.microsoft.com/.default";
```

## [Node.js](#tab/nodejs)
Node.jsで、トークン要求オブジェクトのスコープを指定します。

```JavaScript
const tokenRequest = {
    scopes: [process.env.GRAPH_ENDPOINT + '.default'], // e.g. 'https://graph.microsoft.com/.default'
};
```

## [Python](#tab/python)
MSAL Python では、構成ファイルは次のコード スニペットのようになります。

```Json
{
    "scope": ["https://graph.microsoft.com/.default"],
}
```

## [.NET (低レベル)](#tab/dotnet)
低レベルの.NET コードで、リソース ID に`/.default`を追加してスコープを構築します。

```csharp
ResourceId = "someAppIDURI";
var scopes = new [] {  ResourceId+"/.default"};
```

---

#### Azure AD (v1.0) リソース

クライアント資格情報に使用するスコープは、常に、リソース ID の後に `/.default` が続くものにする必要があります。

重要

MSAL がバージョン 1.0 のアクセス トークンを受け入れるリソースのためのアクセス トークンを要求すると、Microsoft Entra ID では、最後のスラッシュの前のすべてを取得してそれをリソース ID として使って、要求されたスコープから目的の対象ユーザーの解析が行われます。 そのため、Azure SQL Database (`https://database.windows.net`) と同様に、リソースでスラッシュで終わる対象ユーザーが必要な場合 (Azure SQL Database の場合、 `https://database.windows.net/`)、 `https://database.windows.net//.default`のスコープを要求する必要があります。 (二重スラッシュに注意してください)。「MSAL.NET issue [#747: `Resource url's trailing slash is omitted, which caused sql auth failure`](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/issues/747)」も参照してください。

### AcquireTokenForClient API

アプリのトークンを取得するには、プラットフォームに応じて `AcquireTokenForClient` またはそれと同等のものを使用します。

## [.NET](#tab/idweb)
Microsoft.Identity.Web を使用すると、トークンを直接取得する必要はありません。 [デーモン アプリケーションからの Web API の呼び出し](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-daemon-call-api)に関するページに示されているように、上位の API を使用できます。 ただし、トークンを必要とする SDK を使用している場合は、次の例に示すように、 `ITokenAcquirer` 抽象化を使用してアプリ トークンを取得できます。

```csharp
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Identity.Abstractions;
using Microsoft.Identity.Web;

// In the Program.cs, acquire a token for your downstream API

var tokenAcquirerFactory = TokenAcquirerFactory.GetDefaultInstance();
ITokenAcquirer acquirer = tokenAcquirerFactory.GetTokenAcquirer();
AcquireTokenResult tokenResult = await acquirer.GetTokenForUserAsync(new[] { "https://graph.microsoft.com/.default" });
string accessToken = tokenResult.AccessToken;
```

## [ジャワ](#tab/java)
次のJavaトークン取得の例は、[MSAL Java開発サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-java/tree/dev/msal4j-sdk/src/samples/confidential-client/)から抽出されます。

```Java
private static IAuthenticationResult acquireToken() throws Exception {

     // Load token cache from file and initialize token cache aspect. The token cache will have
     // dummy data, so the acquireTokenSilently call will fail.
     TokenCacheAspect tokenCacheAspect = new TokenCacheAspect("sample_cache.json");

     IClientCredential credential = ClientCredentialFactory.createFromSecret(CLIENT_SECRET);
     ConfidentialClientApplication cca =
             ConfidentialClientApplication
                     .builder(CLIENT_ID, credential)
                     .authority(AUTHORITY)
                     .setTokenCacheAccessAspect(tokenCacheAspect)
                     .build();

     IAuthenticationResult result;
     try {
         SilentParameters silentParameters =
                 SilentParameters
                         .builder(SCOPE)
                         .build();

         // try to acquire token silently. This call will fail since the token cache does not
         // have a token for the application you are requesting an access token for
         result = cca.acquireTokenSilently(silentParameters).join();
     } catch (Exception ex) {
         if (ex.getCause() instanceof MsalException) {

             ClientCredentialParameters parameters =
                     ClientCredentialParameters
                             .builder(SCOPE)
                             .build();

             // Try to acquire a token. If successful, you should see
             // the token information printed out to console
             result = cca.acquireToken(parameters).join();
         } else {
             // Handle other exceptions accordingly
             throw ex;
         }
     }
     return result;
 }
```

## [Node.js](#tab/nodejs)
次のコード スニペットは、MSAL Node の機密クライアント アプリケーションでトークンを取得する方法を示しています。

```JavaScript
try {
    const authResponse = await cca.acquireTokenByClientCredential(tokenRequest);
    console.log(authResponse.accessToken) // display access token
} catch (error) {
    console.log(error);
}
```

## [Python](#tab/python)
次の例は、クライアント資格情報を使用してアプリ専用アクセス トークンを取得するための MSAL Python パターンを示しています。

```Python
result = None

# First, the code looks up a token from the cache.
# Because we're looking for a token for the current app, not for a user,
# use None for the account parameter.
result = app.acquire_token_silent(config["scope"], account=None)

if not result:
    logging.info("No suitable token exists in cache. Let's get a new one from Azure AD.")
    result = app.acquire_token_for_client(scopes=config["scope"])

if "access_token" in result:
    # Call a protected API with the access token.
    print(result["token_type"])
else:
    print(result.get("error"))
    print(result.get("error_description"))
    print(result.get("correlation_id"))  # You might need this when reporting a bug.
```

## [.NET (低レベル)](#tab/dotnet)
```csharp
using Microsoft.Identity.Client;

// With client credentials flows, the scope is always of the shape "resource/.default" because the
// application permissions need to be set statically (in the portal or by PowerShell), and then granted by
// a tenant administrator.
string[] scopes = new string[] { "https://graph.microsoft.com/.default" };

AuthenticationResult result = null;
try
{
 result = await app.AcquireTokenForClient(scopes)
                  .ExecuteAsync();
}
catch (MsalUiRequiredException ex)
{
    // The application doesn't have sufficient permissions.
    // - Did you declare enough app permissions during app creation?
    // - Did the tenant admin grant permissions to the application?
}
catch (MsalServiceException ex) when (ex.Message.Contains("AADSTS70011"))
{
    // Invalid scope. The scope has to be in the form "https://resourceurl/.default"
    // Mitigation: Change the scope to be as expected.
}
```

#### AcquireTokenForClient によるアプリケーション トークン キャッシュの使用

MSAL.NET では、`AcquireTokenForClient` によってアプリケーション トークン キャッシュが使用されます (他のすべての AcquireToken*XX* メソッドでは、ユーザー トークン キャッシュが使用されます)。`AcquireTokenSilent` は "`AcquireTokenForClient`" トークン キャッシュを使用するため、`AcquireTokenSilent` を呼び出す前に  を呼び出さないでください。 `AcquireTokenForClient` は*アプリケーション* トークン キャッシュそのものをチェックして、更新します。

---

#### プロトコル

選択した言語のライブラリがまだない場合は、プロトコルを直接使用することもできます。

##### 最初のケース:共有シークレットを使用してトークン要求にアクセスする

```HTTP
POST /{tenant}/oauth2/v2.0/token HTTP/1.1           //Line breaks for clarity.
Host: login.microsoftonline.com
Content-Type: application/x-www-form-urlencoded

client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&scope=https%3A%2F%2Fgraph.microsoft.com%2F.default
&client_secret=A1b-C2d_E3f.H4i,J5k?L6m!N7o-P8q_R9s.T0u
&grant_type=client_credentials
```

##### 2 番目のケース:証明書を使用してトークン要求にアクセスする

```HTTP
POST /{tenant}/oauth2/v2.0/token HTTP/1.1               // Line breaks for clarity.
Host: login.microsoftonline.com
Content-Type: application/x-www-form-urlencoded

scope=https%3A%2F%2Fgraph.microsoft.com%2F.default
&client_id=11112222-bbbb-3333-cccc-4444dddd5555
&client_assertion_type=urn%3Aietf%3Aparams%3Aoauth%3Aclient-assertion-type%3Ajwt-bearer
&client_assertion=aaaaaaaa-0b0b-...
&grant_type=client_credentials
```

詳細については、プロトコルのドキュメント:[Microsoft ID プラットフォームと OAuth 2.0 クライアント資格情報フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)。

### トラブルシューティング

#### リソース/.default スコープを使用しましたか?

無効なスコープを使用したことを示すエラー メッセージが表示された場合は、`resource/.default` スコープを使用しなかった可能性があります。

#### 管理者の同意を提供するのを忘れましたか? デーモン アプリにはそれが必要です。

API を呼び出すときに、**この操作を完了するのに十分な特権がありません**というエラーが表示された場合は、テナント管理者がアプリケーションにアクセス許可を付与する必要があります。

アプリケーションに管理者の同意を与えない場合、API は次の例のようなエラー応答を返します。

```json
Failed to call the web API: Forbidden
Content: {
  "error": {
    "code": "Authorization_RequestDenied",
    "message": "Insufficient privileges to complete the operation.",
    "innerError": {
      "request-id": "<guid>",
      "date": "<date>"
    }
  }
}
```

ロールに応じて、次のオプションのいずれかを選択します。

###### クラウド アプリケーション管理者

クラウド アプリケーション管理者の場合は、Microsoft Entra 管理センターで**エンタープライズ アプリケーション**にアクセスします。 アプリの登録を選択し、左ペインの **[セキュリティ]** セクションで **[アクセス許可]** を選択します。 次に、**[{テナント名} に管理者の同意を与えます]** というラベルの付いた大きいボタンを選択します (**{テナント名}** はディレクトリの名前です)。

###### 標準ユーザー

テナントの標準ユーザーの場合は、アプリケーションに管理者の同意を与えるようクラウド アプリケーション管理者に依頼してください。 同意を要求するには、管理者に次の管理者の同意エンドポイント URL を指示します。

```url
https://login.microsoftonline.com/Enter_the_Tenant_Id_Here/adminconsent?client_id=Enter_the_Application_Id_Here
```

URL 内:

- `Enter_the_Tenant_Id_Here` をテナント ID またはテナント名 (`contoso.microsoft.com` など) に置き換えます。
- `Enter_the_Application_Id_Here` は、登録済みアプリケーションのアプリケーション (クライアント) ID です。

上記の URL を使用してアプリに同意を与えると、エラー `AADSTS50011: No reply address is registered for the application` が表示されることがあります。 `AADSTS50011` エラーは、アプリケーションと URL にリダイレクト URI がないために発生します。 このエラーは無視しても問題ありません。

#### 独自の API を呼び出していますか？

デーモン アプリで独自の Web API を呼び出すとき、デーモンのアプリ登録にアプリのアクセス許可を追加できなかった場合、[Web API のアプリ登録にアプリ ロールを追加する](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-daemon-app-configuration"} -->
## Web API を呼び出すデーモン アプリを構成する方法 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-daemon-app-configuration
- Service: identity-platform / workforce
- Article date: 2026-06-15
- Summary: シークレット、証明書、またはクライアント アサーションを使用して Web API を呼び出すデーモン アプリを構成する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Web API を呼び出すデーモン アプリケーションのコードを構成する方法について説明します。

### デーモン アプリをサポートしている Microsoft ライブラリ

次の Microsoft ライブラリはデーモン アプリをサポートしています。

| 言語/フレームワーク | ～に関するプロジェクトGitHub | パッケージ | 取得開始済み | ユーザーのサインイン | Web API へのアクセス | 一般公開 (GA)*または*パブリック プレビュー^1^ |
| --- | --- | --- | --- | --- | --- | --- |
| .NET | [MSAL.NET](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet) | [Microsoft.Identity.Client](https://www.nuget.org/packages/Microsoft.Identity.Client/) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-daemon-dotnet-acquire-token) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できません。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| Java | [MSAL4J](https://github.com/AzureAD/microsoft-authentication-library-for-java) | [msal4j](https://javadoc.io/doc/com.microsoft.azure/msal4j/latest/index.html) | — | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できません。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| Node | [MSAL ノード](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node) | [msal-node](https://www.npmjs.com/package/@azure/msal-node) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-console-app-nodejs-acquire-token) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できません。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| Python | [MSAL Python](https://github.com/AzureAD/microsoft-authentication-library-for-python) | [msal-python](https://github.com/AzureAD/microsoft-authentication-library-for-python) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-daemon-app-python-acquire-token) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できません。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |

^1^[オンライン サービスのユニバーサル ライセンス条項](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)は、"パブリック プレビュー" のライブラリに適用されます。

### 認証局を設定する

デーモン アプリケーションでは、委任されたアクセス許可ではなく、アプリケーションのアクセス許可が使用されます。 そのため、サポートされているアカウントの種類を、組織のディレクトリ内のアカウントまたは個人用 Microsoft アカウント (例: Skype、Xbox、Outlook.com) にすることはできません。 Microsoft の個人アカウントの場合、デーモン アプリケーションに同意を付与するテナント管理者はいません。 "自分の所属組織のアカウント" または "任意の組織のアカウント" を選択する必要があります。

アプリケーション構成で指定する機関には、テナント ID または組織に関連付けられているドメイン名を含める必要があります。

マルチテナント ツールを提供したい場合でも、このフローでは、サービスではどのテナントを使用すべきかを確実に推測できないため、**...**`common` や `organizations` ではなく、テナント ID またはドメイン名を使用する必要があります。

### アプリケーションを構成してインスタンス化する

Microsoft 認証ライブラリ (MSAL) では、クライアント資格情報 (シークレットまたは証明書) が機密クライアント アプリケーション構築のパラメーターとして渡されます。

重要

アプリケーションが、サービスとして実行されているコンソール アプリケーションであっても、それがデーモン アプリケーションの場合は、機密クライアント アプリケーションである必要があります。

#### 構成ファイル

構成ファイルでは、以下を定義します。

- *機関*を構成するクラウド インスタンスとテナント ID。
- アプリケーションの登録から返されたクライアント ID。
- クライアント シークレットまたは証明書のいずれか。

## [.NET](#tab/idweb)
[*appsettings.json* サンプル構成ファイルで構成](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2/blob/master/1-Call-MSGraph/daemon-console/appsettings.json)を定義する例を次に示します。 この例は、GitHub の [.NET コンソール デーモン](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2)のコード サンプルから引用しています。

```json
{
    "AzureAd": {
        "Instance": "https://login.microsoftonline.com/",
        "TenantId": "[Enter here the tenantID or domain name for your Azure AD tenant]",
        "ClientId": "[Enter here the ClientId for your application]",
        "ClientCredentials": [
            {
                "SourceType": "ClientSecret",
                "ClientSecret": "[Enter here a client secret for your application]"
            }
        ]
    }
}

```

クライアント シークレットではなく証明書、または[ワークロード ID フェデレーション](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation)資格情報を指定します。

## [Java](#tab/java)
次の例は、デーモン アプリのJava構成定数を示しています。

```Java
 private final static String CLIENT_ID = "";
 private final static String AUTHORITY = "https://login.microsoftonline.com/<tenant>/";
 private final static String CLIENT_SECRET = "";
 private final static Set<String> SCOPE = Collections.singleton("https://graph.microsoft.com/.default");
```

## [Node.js](#tab/nodejs)
[Node.js デーモン サンプル](https://github.com/Azure-Samples/ms-identity-javascript-nodejs-console/)の構成パラメーターは、 *.env* ファイルにあります。

```JavaScript
# Credentials
TENANT_ID=Enter_the_Tenant_Info_Here
CLIENT_ID=Enter_the_Application_Id_Here

// You provide either a ClientSecret or a CertificateConfiguration, or a ClientAssertion. These settings are exclusive
CLIENT_SECRET=Enter_the_Client_Secret_Here
CERTIFICATE_THUMBPRINT=Enter_the_certificate_thumbprint_Here
CERTIFICATE_PRIVATE_KEY=Enter_the_certificate_private_key_Here
CLIENT_ASSERTION=Enter_the_Assertion_String_Here

# Endpoints
// the Azure AD endpoint is the authority endpoint for token issuance
AAD_ENDPOINT=Enter_the_Cloud_Instance_Id_Here // https://login.microsoftonline.com/
// the graph endpoint is the application ID URI of Microsoft Graph
GRAPH_ENDPOINT=Enter_the_Graph_Endpoint_Here // https://graph.microsoft.com/
```

## [Python](#tab/python)
クライアント シークレットを使用して機密クライアントを構築する場合、[Python デーモン](https://github.com/Azure-Samples/ms-identity-python-daemon/blob/master/1-Call-MsGraph-WithSecret/parameters.json)[サンプルのクライアント シークレット フロー](https://github.com/Azure-Samples/ms-identity-python-daemon)構成ファイルのparameters.json サンプルは次のとおりです。

```Json
{
  "authority": "https://login.microsoftonline.com/<your_tenant_id>",
  "client_id": "your_client_id",
  "scope": [ "https://graph.microsoft.com/.default" ],
  "secret": "The secret generated by Azure AD during your confidential app registration",
  "endpoint": "https://graph.microsoft.com/v1.0/users"
}
```

証明書を使用して機密クライアントを構築する場合、[Python デーモン](https://github.com/Azure-Samples/ms-identity-python-daemon/blob/master/2-Call-MsGraph-WithCertificate/parameters.json)[サンプルの証明書フロー](https://github.com/Azure-Samples/ms-identity-python-daemon)構成ファイルのparameters.json サンプルは次のとおりです。

```Json
{
  "authority": "https://login.microsoftonline.com/<your_tenant_id>",
  "client_id": "your_client_id",
  "scope": [ "https://graph.microsoft.com/.default" ],
  "thumbprint": "790E... The thumbprint generated by Azure AD when you upload your public cert",
  "private_key_file": "server.pem",
  "endpoint": "https://graph.microsoft.com/v1.0/users"
}
```

## [.NET (低レベル)](#tab/dotnet)
[*appsettings.json* デーモン コンソール構成ファイル](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2/blob/master/1-Call-MSGraph/daemon-console/appsettings.json)で構成を定義する例を次に示します。 この例は、GitHub の [.NET コンソール デーモン](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2)のコード サンプルから引用しています。

```json
{
  "Instance": "https://login.microsoftonline.com/{0}",
  "Tenant": "[Enter here the tenantID or domain name for your Azure AD tenant]",
  "ClientId": "[Enter here the ClientId for your application]",
  "ClientSecret": "[Enter here a client secret for your application]",
  "CertificateName": "[Or instead of client secret: Enter here the name of a certificate (from the user cert store) as registered with your application]"
}
```

`ClientSecret` または `CertificateName` のいずれかを指定します。 これらの設定は相互排他的です。

---

#### MSAL アプリケーションをインスタンス化する

MSAL アプリケーションをインスタンス化するには、(言語に応じて) MSAL パッケージを追加、参照、またはインポートします。

使用しているのがクライアント シークレットか証明書か (または高度なシナリオとして、署名付きアサーションか) によって、構築に違いがあります。

##### パッケージを参照

アプリケーション コードで MSAL パッケージを参照します。

## [.NET](#tab/idweb)
[Microsoft.Identity.Web.TokenAcquisition](https://www.nuget.org/packages/Microsoft.Identity.Web.TokenAcquisition) NuGet パッケージをアプリケーションに追加します。 または、Microsoft Graph を呼び出す場合は、[Microsoft.Identity.Web.GraphServiceClient](https://www.nuget.org/packages/Microsoft.Identity.Web.GraphServiceClient) パッケージを追加します。 プロジェクトは次のようになります。 *appsettings.json* ファイルを出力ディレクトリにコピーする必要があります。

```xml
<Project Sdk="Microsoft.NET.Sdk">

  <PropertyGroup>
    <OutputType>Exe</OutputType>
    <TargetFramework>net7.0</TargetFramework>
    <RootNamespace>daemon_console</RootNamespace>
  </PropertyGroup>

  <ItemGroup>
    <PackageReference Include="Microsoft.Identity.Web.GraphServiceClient" Version="2.12.2" />
  </ItemGroup>

  <ItemGroup>
    <None Update="appsettings.json">
      <CopyToOutputDirectory>PreserveNewest</CopyToOutputDirectory>
    </None>
  </ItemGroup>
</Project>
```

Program.cs ファイルで、Microsoft.Identity.Web を参照する `using` ディレクティブをコードに追加します。

```csharp
using Microsoft.Identity.Abstractions;
using Microsoft.Identity.Web;
```

## [Java](#tab/java)
```java
import com.microsoft.aad.msal4j.ClientCredentialFactory;
import com.microsoft.aad.msal4j.ClientCredentialParameters;
import com.microsoft.aad.msal4j.ConfidentialClientApplication;
import com.microsoft.aad.msal4j.IAuthenticationResult;
import com.microsoft.aad.msal4j.IClientCredential;
import com.microsoft.aad.msal4j.MsalException;
import com.microsoft.aad.msal4j.SilentParameters;
```

## [Node.js](#tab/nodejs)
`npm install` ファイルがあるフォルダーで `package.json` を実行して、パッケージをインストールします。 次に、`msal-node` パッケージをインポートします。

```JavaScript
const msal = require('@azure/msal-node');
```

## [Python](#tab/python)
必要な MSAL モジュールとヘルパー モジュールをPython アプリケーションにインポートします。

```python
import msal
import json
import sys
import logging
```

## [.NET (低レベル)](#tab/dotnet)
[Microsoft.Identity.Client](https://www.nuget.org/packages/Microsoft.Identity.Client) NuGet パッケージをアプリケーションに追加し、`using` ディレクティブをコードに追加してこのパッケージを参照します。

MSAL.NET では、機密クライアント アプリケーションは `IConfidentialClientApplication` インターフェイスによって表されます。

```csharp
using Microsoft.Identity.Client;
IConfidentialClientApplication app;
```

---

##### クライアント シークレットを使用して機密クライアント アプリケーションをインスタンス化する

クライアント シークレットを使用して機密クライアント アプリケーションをインスタンス化するコードを次に示します。

## [.NET](#tab/idweb)
次の例では、Microsoftのクライアント シークレットを使用して、機密クライアント アプリケーションを作成します。Identity.Web:

```csharp
   class Program
    {
        static async Task Main(string[] _)
        {
            // Get the Token acquirer factory instance. By default it reads an appsettings.json
            // file if it exists in the same folder as the app (make sure that the 
            // "Copy to Output Directory" property of the appsettings.json file is "Copy if newer").
            TokenAcquirerFactory tokenAcquirerFactory = TokenAcquirerFactory.GetDefaultInstance();

            // Configure the application options to be read from the configuration
            // and add the services you need (Graph, token cache)
            IServiceCollection services = tokenAcquirerFactory.Services;
            services.AddMicrosoftGraph();
            // By default, you get an in-memory token cache.
            // For more token cache serialization options, see https://aka.ms/msal-net-token-cache-serialization

            // Resolve the dependency injection.
            var serviceProvider = tokenAcquirerFactory.Build();

            // ...
        }
    }
```

構成は *appsettings.json* から読み取られます。

## [Java](#tab/java)
次のJavaコードを使用して、クライアント シークレットを含む機密クライアント アプリケーションを作成します。

```Java
IClientCredential credential = ClientCredentialFactory.createFromSecret(CLIENT_SECRET);

ConfidentialClientApplication cca =
        ConfidentialClientApplication
                .builder(CLIENT_ID, credential)
                .authority(AUTHORITY)
                .build();
```

## [Node.js](#tab/nodejs)
次の Node.js 構成を使用して、クライアント シークレットを使用して機密クライアント アプリケーションをインスタンス化します。

```JavaScript

const msalConfig = {
  auth: {
    clientId: process.env.CLIENT_ID,
    authority: process.env.AAD_ENDPOINT + process.env.TENANT_ID,
    clientSecret: process.env.CLIENT_SECRET,
  }
};

const apiConfig = {
  uri: process.env.GRAPH_ENDPOINT + 'v1.0/users',
};

const tokenRequest = {
  scopes: [process.env.GRAPH_ENDPOINT + '.default'],
};

const cca = new msal.ConfidentialClientApplication(msalConfig);
```

## [Python](#tab/python)
```Python
# Pass the parameters.json file as an argument to this Python script. E.g.: python your_py_file.py parameters.json
config = json.load(open(sys.argv[1]))

# Create a preferably long-lived app instance that maintains a token cache.
app = msal.ConfidentialClientApplication(
    config["client_id"], authority=config["authority"],
    client_credential=config["secret"],
    # token_cache=...  # Default cache is in memory only.
                       # You can learn how to use SerializableTokenCache from
                       # https://msal-python.rtfd.io/en/latest/#msal.SerializableTokenCache
    )
```

## [.NET (低レベル)](#tab/dotnet)
次の MSAL.NET 例では、構成されたクライアント シークレットを使用して機密クライアント アプリケーションを作成します。

```csharp
app = ConfidentialClientApplicationBuilder.Create(config.ClientId)
           .WithClientSecret(config.ClientSecret)
           .WithAuthority(new Uri(config.Authority))
           .Build();
```

`Authority` は、クラウド インスタンスとテナント ID (`https://login.microsoftonline.com/contoso.onmicrosoft.com`、`https://login.microsoftonline.com/aaaabbbb-0000-cccc-1111-dddd2222eeee` など) を連結したものです。 「*構成ファイル*」セクションに示した appsettings.json ファイルでは、インスタンスとテナントはそれぞれ `Instance` と `Tenant` の値で表されます。

前のスニペットを引用したコード サンプルでは、`Authority` は [AuthenticationConfig](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2/blob/ffc4a9f5d9bdba5303e98a1af34232b434075ac7/1-Call-MSGraph/daemon-console/AuthenticationConfig.cs#L61-L70) クラスのプロパティであり、次のように定義されています。

```csharp
/// <summary>
/// URL of the authority
/// </summary>
public string Authority
{
    get
    {
        return String.Format(CultureInfo.InvariantCulture, Instance, Tenant);
    }
}
```

---

##### クライアント証明書を使用して機密クライアント アプリケーションをインスタンス化する

証明書を使用してアプリケーションをビルドするコードを次に示します。

## [.NET](#tab/idweb)
アプリケーション構築コードは、クライアント シークレットの例と同じです。 唯一の違いは、証明書がシークレットではなく構成で記述されていることです。 証明書を取得する方法はたくさんあります。 詳細については、「[Microsoft Identity Web で証明書を使用する」を](https://aka.ms/ms-id-web-certificates)参照してください。 次の構成例は、Azure Key Vaultから証明書を取得する方法を示しています。 Microsoft ID から Azure Identity の DefaultAzureCredential に委任し、KeyVault から証明書にアクセスできる場合はマネージド ID を使用します。 DefaultAzureCredential では開発者の資格情報が使用されるため、アプリケーションをローカルでデバッグできます。

```json
  "ClientCredentials": [
      {
        "SourceType": "KeyVault",
        "KeyVaultUrl": "https://yourKeyVaultUrl.vault.azure.net",
        "KeyVaultCertificateName": "NameOfYourCertificate"
      }
```

## [Java](#tab/java)
MSAL Java では、証明書を使用して機密クライアント アプリケーションをインスタンス化するには、次の 2 つのビルダーがあります。

```Java

InputStream pkcs12Certificate = ... ; /* Containing PCKS12-formatted certificate*/
string certificatePassword = ... ;    /* Contains the password to access the certificate */

IClientCredential credential = ClientCredentialFactory.createFromCertificate(pkcs12Certificate, certificatePassword);

ConfidentialClientApplication cca =
        ConfidentialClientApplication
                .builder(CLIENT_ID, credential)
                .authority(AUTHORITY)
                .build();
```

または

```Java
PrivateKey key = getPrivateKey(); /* RSA private key to sign the assertion */
X509Certificate publicCertificate = getPublicCertificate(); /* x509 public certificate used as a thumbprint */

IClientCredential credential = ClientCredentialFactory.createFromCertificate(key, publicCertificate);

ConfidentialClientApplication cca =
        ConfidentialClientApplication
                .builder(CLIENT_ID, credential)
                .authority(AUTHORITY)
                .build();
```

## [Node.js](#tab/nodejs)
次の Node.js 例では、証明書を使用するように機密クライアント アプリケーションを構成します。

```JavaScript

const config = {
    auth: {
        clientId: process.env.CLIENT_ID,
        authority: process.env.AAD_ENDPOINT + process.env.TENANT_ID,
        clientCertificate: {
            thumbprint:  process.env.CERTIFICATE_THUMBPRINT, // a 40-digit hexadecimal string
            privateKey:  process.env.CERTIFICATE_PRIVATE_KEY,
        }
    }
};

// Create an MSAL application object
const cca = new msal.ConfidentialClientApplication(config);
```

詳細については、[MSAL ノードでの証明書資格情報の使用](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/certificate-credentials.md)に関するページを参照してください。

## [Python](#tab/python)
```Python
# Pass the parameters.json file as an argument to this Python script. E.g.: python your_py_file.py parameters.json
config = json.load(open(sys.argv[1]))

# Create a preferably long-lived app instance that maintains a token cache.
app = msal.ConfidentialClientApplication(
    config["client_id"], authority=config["authority"],
    client_credential={"thumbprint": config["thumbprint"], "private_key": open(config['private_key_file']).read()},
    # token_cache=...  # Default cache is in memory only.
                       # You can learn how to use SerializableTokenCache from
                       # https://msal-python.rtfd.io/en/latest/#msal.SerializableTokenCache
    )
```

## [.NET](#tab/dotnet)
証明書を読み込み、機密クライアント アプリケーションをビルドするには、次の MSAL.NET コードを使用します。

```csharp
X509Certificate2 certificate = ReadCertificate(config.CertificateName);
app = ConfidentialClientApplicationBuilder.Create(config.ClientId)
    .WithCertificate(certificate)
    .WithAuthority(new Uri(config.Authority))
    .Build();
```

---

##### 高度なシナリオ: クライアント アサーションを使用して機密クライアント アプリケーションをインスタンス化する

## [.NET](#tab/idweb)
機密クライアント アプリケーションでは、クライアント シークレットや証明書に加えて、クライアント アサーションを使用して ID を証明することもできます。 詳細については、「[CredentialDescription](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.abstractions.credentialdescription?view=msal-model-dotnet-latest&preserve-view=true)」を参照してください。

## [Java](#tab/java)
次のJava例では、クライアント アサーションを使用して機密クライアント アプリケーションを作成します。

```Java
IClientCredential credential = ClientCredentialFactory.createFromClientAssertion(assertion);

ConfidentialClientApplication cca =
        ConfidentialClientApplication
                .builder(CLIENT_ID, credential)
                .authority(AUTHORITY)
                .build();
```

## [Node.js](#tab/nodejs)
次の Node.js 構成を使用して、クライアント アサーションを使用して機密クライアント アプリケーションを初期化します。

```JavaScript
const clientConfig = {
    auth: {
        clientId: process.env.CLIENT_ID,
        authority: process.env.AAD_ENDPOINT + process.env.TENANT_ID,
        clientAssertion:  process.env.CLIENT_ASSERTION
    }
};
const cca = new msal.ConfidentialClientApplication(clientConfig);
```

詳細については、[ConfidentialClientApplication オブジェクトの初期化](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/initialize-confidential-client-application.md)に関するページを参照してください。

## [Python](#tab/python)
MSAL Python では、この `ConfidentialClientApplication` の秘密キーによって署名される要求を使用して、クライアント要求を提供できます。

```Python
# Pass the parameters.json file as an argument to this Python script. E.g.: python your_py_file.py parameters.json
config = json.load(open(sys.argv[1]))

# Create a preferably long-lived app instance that maintains a token cache.
app = msal.ConfidentialClientApplication(
    config["client_id"], authority=config["authority"],
    client_credential={"thumbprint": config["thumbprint"], "private_key": open(config['private_key_file']).read()},
    client_claims = {"client_ip": "x.x.x.x"}
    # token_cache=...  # Default cache is in memory only.
                       # You can learn how to use SerializableTokenCache from
                       # https://msal-python.rtfd.io/en/latest/#msal.SerializableTokenCache
    )
```

詳細については、[ConfidentialClientApplication](https://msal-python.readthedocs.io/en/latest/#msal.ClientApplication.__init__) に関する MSAL Python のリファレンス ドキュメントを参照してください。

## [.NET (低レベル)](#tab/dotnet)
機密クライアント アプリケーションでは、クライアント シークレットまたは証明書ではなく、クライアント アサーションを使用してその ID を証明することもできます。

MSAL.NET には、機密クライアント アプリに署名付きアサーションを提供する方法が 2 つあります。

- `.WithClientAssertion()`
- `.WithClientClaims()`

`WithClientAssertion` を使用する場合は、署名付き JWT を提供します。 この高度なシナリオの詳細については、[クライアント アサーション](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/msal-net-client-assertions)に関する記事を参照してください。

```csharp
string signedClientAssertion = ComputeAssertion();
app = ConfidentialClientApplicationBuilder.Create(config.ClientId)
                                          .WithClientAssertion(signedClientAssertion)
                                          .Build();
```

`WithClientClaims` を使用する場合は、Microsoft Entra ID で想定される要求と、送信する追加のクライアント要求を含む署名付きアサーションが、MSAL.NET によって生成されます。 次のコードでは、それを行う方法を示します。

```csharp
string ipAddress = "192.168.1.2";
var claims = new Dictionary<string, string> { { "client_ip", ipAddress } };
X509Certificate2 certificate = ReadCertificate(config.CertificateName);
app = ConfidentialClientApplicationBuilder.Create(config.ClientId)
                                          .WithAuthority(new Uri(config.Authority))
                                          .WithClientClaims(certificate, claims)
                                          .Build();
```

詳細については、「 [クライアント アサーション」を](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/msal-net-client-assertions)参照してください。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-daemon-call-api"} -->
## デーモン アプリからダウンストリーム Web API を呼び出す方法 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-daemon-call-api
- Service: identity-platform / workforce
- Article date: 2025-03-25
- Summary: Web API を呼び出すデーモン アプリをビルドする方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

.NET デーモン アプリでは、Web API を呼び出すことができます。 .NET デーモン アプリでは、事前承認されたいくつかの Web API を呼び出すこともできます。

### デーモン アプリケーションからの Web API の呼び出し

ここでは、トークンを使用して API を呼び出す方法を示します。

## [.NET](#tab/idweb)
Microsoft.Identity.Web では、MSAL.NET の複雑さを抽象化します。 条件付きアクセス エラーの処理、キャッシュなど、MSAL.NET の内部を自動的に処理する上位の API が提供されます。

ダウンストリーム API を呼び出すデーモン アプリの Program.cs を次に示します。

```csharp
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Identity.Abstractions;
using Microsoft.Identity.Web;

// In the Program.cs, acquire a token for your downstream API

var tokenAcquirerFactory = TokenAcquirerFactory.GetDefaultInstance();
tokenAcquirerFactory.Services.AddDownstreamApi("MyApi",
    tokenAcquirerFactory.Configuration.GetSection("MyWebApi"));
var sp = tokenAcquirerFactory.Build();

var api = sp.GetRequiredService<IDownstreamApi>();
var result = await api.GetForAppAsync<IEnumerable<TodoItem>>("MyApi");
Console.WriteLine($"result = {result?.Count()}");
```

Microsoft Graph を呼び出すデーモン アプリの Program.cs を次に示します。

```csharp
var tokenAcquirerFactory = TokenAcquirerFactory.GetDefaultInstance();
tokenAcquirerFactory.Services.AddMicrosoftGraph();
var serviceProvider = tokenAcquirerFactory.Build();
try
{
    GraphServiceClient graphServiceClient = serviceProvider.GetRequiredService<GraphServiceClient>();
    var users = await graphServiceClient.Users
        .GetAsync(r => r.Options.WithAppOnly());
    Console.WriteLine($"{users.Count} users");
    Console.ReadKey();
}
catch (Exception ex) { Console.WriteLine("We could not retrieve the user's list: " + $"{ex}"); }
```

## [ジャワ](#tab/java)
```Java
HttpURLConnection conn = (HttpURLConnection) url.openConnection();

// Set the appropriate header fields in the request header.
conn.setRequestProperty("Authorization", "Bearer " + accessToken);
conn.setRequestProperty("Accept", "application/json");

String response = HttpClientHelper.getResponseStringFromConn(conn);

int responseCode = conn.getResponseCode();
if(responseCode != HttpURLConnection.HTTP_OK) {
    throw new IOException(response);
}

JSONObject responseObject = HttpClientHelper.processResponse(responseCode, response);
```

## [Node.js](#tab/nodejs)
[Axios](https://www.npmjs.com/package/axios)などの HTTP クライアントを使用し、"*承認ベアラー*" としてアクセス トークンを使用して API エンドポイント URI を呼び出します。

```JavaScript
const axios = require('axios');

async function callApi(endpoint, accessToken) {

    const options = {
        headers: {
            Authorization: `Bearer ${accessToken}`
        }
    };

    console.log('request made to web API at: ' + new Date().toString());

    try {
        const response = await axios.default.get(endpoint, options);
        return response.data;
    } catch (error) {
        console.log(error)
        return error;
    }
};
```

## [Python](#tab/python)
```Python
endpoint = "url to the API"
http_headers = {'Authorization': 'Bearer ' + result['access_token'],
                'Accept': 'application/json',
                'Content-Type': 'application/json'}
data = requests.get(endpoint, headers=http_headers, stream=False).json()
```

## [.NET 低レベル](#tab/dotnet)
#### MSAL.NET の AuthenticationResult プロパティ

トークンを取得するメソッドでは、`AuthenticationResult` が返されます。 非同期メソッドでは、`Task<AuthenticationResult>` が返されます。

MSAL.NET では、`AuthenticationResult` は次を公開します。

- Web API がリソースにアクセスするための `AccessToken`。 このパラメーターは、通常は base 64 でエンコードされた JWT の文字列です。 クライアントがアクセス トークン内を見ることはありません。 この形式が変わらないことは保証されておらず、リソース用に暗号化できます。 クライアント上のアクセス トークンのコンテンツに応じてコードを記述することは、エラーとクライアント ロジックの中断を起こす最大の原因の 1 つです。 詳細については、「[アクセス トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)」を参照してください。
- ユーザー向け `IdToken`。 このパラメーターは、エンコードされた JWT です。 詳細については、[ID トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens)に関するページを参照してください。
- トークンの有効期限の日時は、`ExpiresOn` から知ることができます。
- `TenantId` には、ユーザーが存在するテナントが含まれています。 ゲスト ユーザー (Microsoft Entra B2B のシナリオ) の場合、テナント ID は一意のテナントではなく、ゲスト テナントです。 ユーザーにトークンが配信されるときに、`AuthenticationResult` にはこのユーザーに関する情報も含まれます。 アプリケーションのユーザーなしでトークンが要求される機密のクライアント フローの場合、このユーザー情報は null です。
- トークンが発行された `Scopes`。
- ユーザーの一意の ID。

#### IAccount

MSAL.NET では、`IAccount` インターフェイスでアカウントの概念が定義されます。 この破壊的変更により、正しいセマンティクスが得られます。 同じユーザーは、異なる Microsoft Entra ディレクトリに複数のアカウントを持つことができます。 また、MSAL.NET では、ホーム アカウント情報が提供されるので、ゲスト シナリオについてはより詳細な情報が提供されます。 次の図は、`IAccount` インターフェイスの構造を示しています。

[Image: IAccount インターフェイスの構造]

`AccountId` クラスでは、次の表のプロパティで、特定のテナントのアカウントを識別します。

| プロパティ | 説明 |
| --- | --- |
| `TenantId` | GUID (アカウントが存在するテナントの ID) の文字列表現。 |
| `ObjectId` | GUID (テナント内でアカウントを所有するユーザーの ID) の文字列表現。 |
| `Identifier` | アカウントの一意識別子。 `Identifier` は `ObjectId` と `TenantId` をコンマで区切って連結したものです。 base 64 ではエンコードされていません。 |

`IAccount` インターフェイスは 1 つのアカウントに関する情報を表します。 同じユーザーが異なるテナントに存在することができます。つまり、1 人のユーザーが複数のアカウントを持つことができます。 そのメンバーは、次の表のとおりです。

| プロパティ | 説明 |
| --- | --- |
| `Username` | UserPrincipalName (UPN) 形式の表示可能な値の文字列 (例: john.doe@contoso.com)。 null にすることができない HomeAccountId と HomeAccountId.Identifier とは異なり、この文字列は null にできます。 このプロパティは、MSAL.NET の以前のバージョンの `DisplayableId` の `IUser` プロパティを置き換えます。 |
| `Environment` | このアカウントの ID プロバイダーを含む文字列 (例: `login.microsoftonline.com`)。 `IdentityProvider` には、クラウド環境に加えテナントに関する情報もありますが、それを除き、このプロパティは、`IUser` の `IdentityProvider` プロパティと置き換えることができます。 ここでは値はホストのみです。 |
| `HomeAccountId` | ユーザーのホーム アカウントのアカウント ID。 このプロパティは、Microsoft Entra テナント全体でユーザーを一意に識別します。 |

#### トークンを使用して保護された API を呼び出す

MSAL によって `AuthenticationResult` に `result` が返された後、保護された Web API にアクセスする呼び出しを行う前に、これを HTTP Authorization ヘッダーに追加します。

```csharp
httpClient = new HttpClient();
httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", result.AccessToken);

// Call the web API.
HttpResponseMessage response = await _httpClient.GetAsync(apiUri);
...

```

---

### 複数の API の呼び出し

デーモン アプリの場合、呼び出す Web API は事前に承認されている必要があります。 デーモン アプリに対する増分同意はありません。 (ユーザーによる操作はありません)。テナント管理者は、アプリケーションとすべての API アクセス許可に事前に同意する必要があります。 複数の API を呼び出す場合、`AcquireTokenForClient` を呼び出すたびに各リソースのトークンを取得します。 MSAL では、不要なサービス呼び出しを回避するために、アプリケーションのトークン キャッシュを使用します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-desktop-acquire-token"} -->
## Web API を呼び出すためのトークンを取得する (デスクトップ アプリ) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token
- Service: identity-platform / workforce
- Article date: 2023-03-27
- Summary: Web API を呼び出すデスクトップ アプリを構築して、そのアプリのトークンを取得する方法について説明します

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

パブリック クライアント アプリケーションのインスタンスを構築したら、それを使用して、トークンを取得します。これは、Web API を呼び出すために後で使用します。

### 推奨パターン

Web API はその*スコープ*によって定義されます。 どのようなエクスペリエンスをアプリケーション内で提供する場合でも、使用するパターンは次のようになります。

- `AcquireTokenSilent` を呼び出すことで、トークン キャッシュからのトークンの取得を体系的に試行します。
- この呼び出しが失敗した場合は、使用したい `AcquireToken` フローを使用します (ここでは `AcquireTokenXX` で表されています)。

## [.NET](#tab/dotnet)
#### MSAL.NET の場合

```csharp
AuthenticationResult result;
var accounts = await app.GetAccountsAsync();
IAccount account = ChooseAccount(accounts); // for instance accounts.FirstOrDefault
                                            // if the app manages is at most one account
try
{
 result = await app.AcquireTokenSilent(scopes, account)
                   .ExecuteAsync();
}
catch(MsalUiRequiredException ex)
{
  result = await app.AcquireTokenXX(scopes, account)
                    .WithOptionalParameterXXX(parameter)
                    .ExecuteAsync();
}
```

## [Java](#tab/java)
```java
Set<IAccount> accountsInCache = pca.getAccounts().join();
// Take first account in the cache. In a production application, you would filter
// accountsInCache to get the right account for the user authenticating.
IAccount account = accountsInCache.iterator().next();

IAuthenticationResult result;
try {
    SilentParameters silentParameters =
            SilentParameters
                    .builder(SCOPE, account)
                    .build();

    // try to acquire token silently. This call will fail since the token cache
    // does not have any data for the user you are trying to acquire a token for
    result = pca.acquireTokenSilently(silentParameters).join();
} catch (Exception ex) {
    if (ex.getCause() instanceof MsalException) {

        InteractiveRequestParameters parameters = InteractiveRequestParameters
                .builder(new URI("http://localhost"))
                .scopes(SCOPE)
                .build();

        // Try to acquire a token interactively with system browser. If successful, you should see
        // the token and account information printed out to console
        result = pca.acquireToken(parameters).join();
    } else {
        // Handle other exceptions accordingly
        throw ex;
    }
}
return result;
```

## [macOS](#tab/macOS)
#### iOS および macOS 用の MSAL の場合

Objective-C:

```objc
MSALAccount *account = [application accountForIdentifier:accountIdentifier error:nil];

MSALSilentTokenParameters *silentParams = [[MSALSilentTokenParameters alloc] initWithScopes:scopes account:account];
[application acquireTokenSilentWithParameters:silentParams completionBlock:^(MSALResult *result, NSError *error) {

    // Check the error
    if (error && [error.domain isEqual:MSALErrorDomain] && error.code == MSALErrorInteractionRequired)
    {
        // Interactive auth will be required, call acquireTokenWithParameters:error:
    }
}];
```

Swift:

```swift
guard let account = try? application.account(forIdentifier: accountIdentifier) else { return }
let silentParameters = MSALSilentTokenParameters(scopes: scopes, account: account)
application.acquireTokenSilent(with: silentParameters) { (result, error) in

    guard let authResult = result, error == nil else {

    let nsError = error! as NSError

        if (nsError.domain == MSALErrorDomain &&
            nsError.code == MSALError.interactionRequired.rawValue) {

            // Interactive auth will be required, call acquireToken()
            return
        }
        return
    }
}
```

## [Node.js](#tab/nodejs)
MSAL ノードでは、コード交換のための証明鍵 (PKCE) を使用して認可コード フロー経由でトークンを取得します。 MSAL ノードでは、メモリ内のトークン キャッシュを使用して、キャッシュ内にユーザー アカウントがあるかどうかを確認します。 存在する場合は、アカウント オブジェクトを `acquireTokenSilent()` メソッドに渡してキャッシュされたアクセス トークンを取得できます。

```javascript

const msal = require("@azure/msal-node");

const msalConfig = {
    auth: {
        clientId: "your_client_id_here",
        authority: "your_authority_here",
    }
};

const pca = new msal.PublicClientApplication(msalConfig);
const msalTokenCache = pca.getTokenCache();

let accounts = await msalTokenCache.getAllAccounts();

    if (accounts.length > 0) {

        const silentRequest = {
            account: accounts[0], // Index must match the account that is trying to acquire token silently
            scopes: ["user.read"],
        };

        pca.acquireTokenSilent(silentRequest).then((response) => {
            console.log("\nSuccessful silent token acquisition");
            console.log("\nResponse: \n:", response);
            res.sendStatus(200);
        }).catch((error) => console.log(error));
    } else {
        const {verifier, challenge} = await msal.cryptoProvider.generatePkceCodes();

        const authCodeUrlParameters = {
            scopes: ["User.Read"],
            redirectUri: "your_redirect_uri",
            codeChallenge: challenge, // PKCE Code Challenge
            codeChallengeMethod: "S256" // PKCE Code Challenge Method 
        };

        // get url to sign user in and consent to scopes needed for application
        pca.getAuthCodeUrl(authCodeUrlParameters).then((response) => {
            console.log(response);

            const tokenRequest = {
                code: response["authorization_code"],
                codeVerifier: verifier, // PKCE Code Verifier 
                redirectUri: "your_redirect_uri",
                scopes: ["User.Read"],
            };

            // acquire a token by exchanging the code
            pca.acquireTokenByCode(tokenRequest).then((response) => {
                console.log("\nResponse: \n:", response);
            }).catch((error) => {
                console.log(error);
            });
        }).catch((error) => console.log(JSON.stringify(error)));
    }
```

## [Python](#tab/python)
```python
result = None

# Firstly, check the cache to see if this end user has signed in before
accounts = app.get_accounts(username=config["username"])
if accounts:
    result = app.acquire_token_silent(config["scope"], account=accounts[0])

if not result:
    result = app.acquire_token_by_xxx(scopes=config["scope"])
```

---

デスクトップ アプリケーション内でトークンを取得するさまざまな方法があります。

- [対話型](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-interactive)
- [統合 Windows 認証](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-integrated-windows-authentication)
- [WAM](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-wam)
- [ユーザー名とパスワード](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-username-password)
- [デバイス コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-device-code-flow)

重要

ユーザーが多要素認証 (MFA) を使用してアプリケーションにログインすると、ログインできずにブロックされます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-desktop-acquire-token-device-code-flow"} -->
## Web API を呼び出し、デバイス コード フローでトークンを取得する (デスクトップ アプリ) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-device-code-flow
- Service: identity-platform / workforce
- Article date: 2022-10-07
- Summary: Web API を呼び出し、デバイス コード フローでアプリのトークンを取得するデスクトップ アプリを構築する方法について説明します

**適用対象**: [Image: 白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: 灰色の X 記号が付いた白い円。] 外部テナント ([詳細はこちら](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Web コントロールのないコマンドライン ツールを記述するときに前述のフローを使用できない (しない) 場合は、デバイス コード フローを使用します。

### デバイス コード フロー

Microsoft Entra ID を使用した対話型認証には、Web ブラウザーが必要です。 詳細については、[Web ブラウザーの使用](https://aka.ms/msal-net-uses-web-browser)に関する記事を参照してください。 Web ブラウザーを提供しないデバイスまたはオペレーティング システム上でユーザーを認証する場合、ユーザーはデバイス コード フローによって別のデバイス (コンピューターや携帯電話など) を使用して対話形式でサインインできます。 デバイス コード フローを使用すると、アプリケーションでは、これらのデバイスまたはオペレーティング システム用に設計された 2 ステップ プロセスを通じてトークンを取得します。 このようなアプリケーションには、IoT やコマンドライン ツール (CLI) で実行されるアプリケーションがあります。 考え方は次のとおりです。

1. ユーザー認証が必要になるたびに、アプリではユーザーに対してコードを提供します。 ユーザーは、インターネットに接続されたスマートフォンなどの別のデバイスを使用して URL (例: `https://microsoft.com/devicelogin`) にアクセスするように求められます。 次に、ユーザーはそのコードを入力するように要求されます。 完了すると、Web ページにおいて通常の認証エクスペリエンス (必要に応じて、同意のプロンプトと多要素認証が含まれます) が実行されます。
2. 認証が成功すると、コマンドライン アプリはバック チャネル経由で必要なトークンを受信し、それらを使用して必要な Web API の呼び出しを実行します。

### 使用方法

## [.NET](#tab/dotnet)
`IPublicClientApplication` には `AcquireTokenWithDeviceCode` というメソッドが含まれています。

```csharp
 AcquireTokenWithDeviceCode(IEnumerable<string> scopes,
                            Func<DeviceCodeResult, Task> deviceCodeResultCallback)
```

このメソッドは、パラメーターとして次のものを受け取ります。

- アクセス トークンを要求する対象の `scopes`。
- [`DeviceCodeResult`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.devicecoderesult) を受信するコールバック。

次のサンプル コードは、最新のケースの概要 (および取得可能な例外の種類の説明とその軽減策) を示しています。 完全に機能するコード サンプルについては、GitHub の「[active-directory-dotnetcore-devicecodeflow-v2](https://github.com/azure-samples/active-directory-dotnetcore-devicecodeflow-v2)」を参照してください。

```csharp
private const string ClientId = "<client_guid>";
private const string Authority = "https://login.microsoftonline.com/contoso.com";
private readonly string[] scopes = new string[] { "user.read" };

static async Task<AuthenticationResult> GetATokenForGraph()
{
    IPublicClientApplication pca = PublicClientApplicationBuilder
            .Create(ClientId)
            .WithAuthority(Authority)
            .WithDefaultRedirectUri()
            .Build();

    var accounts = await pca.GetAccountsAsync();

    // All AcquireToken* methods store the tokens in the cache, so check the cache first
    try
    {
        return await pca.AcquireTokenSilent(scopes, accounts.FirstOrDefault())
            .ExecuteAsync();
    }
    catch (MsalUiRequiredException ex)
    {
        // No token found in the cache or Azure AD insists that a form interactive auth is required (e.g. the tenant admin turned on MFA)
        // If you want to provide a more complex user experience, check out ex.Classification

        return await AcquireByDeviceCodeAsync(pca);
    }
}

private static async Task<AuthenticationResult> AcquireByDeviceCodeAsync(IPublicClientApplication pca)
{
    try
    {
        var result = await pca.AcquireTokenWithDeviceCode(scopes,
            deviceCodeResult =>
            {
                    // This will print the message on the console which tells the user where to go sign-in using
                    // a separate browser and the code to enter once they sign in.
                    // The AcquireTokenWithDeviceCode() method will poll the server after firing this
                    // device code callback to look for the successful login of the user via that browser.
                    // This background polling (whose interval and timeout data is also provided as fields in the
                    // deviceCodeCallback class) will occur until:
                    // * The user has successfully logged in via browser and entered the proper code
                    // * The timeout specified by the server for the lifetime of this code (typically ~15 minutes) has been reached
                    // * The developing application calls the Cancel() method on a CancellationToken sent into the method.
                    //   If this occurs, an OperationCanceledException will be thrown (see catch below for more details).
                    Console.WriteLine(deviceCodeResult.Message);
                return Task.FromResult(0);
            }).ExecuteAsync();

        Console.WriteLine(result.Account.Username);
        return result;
    }

    // TODO: handle or throw all these exceptions depending on your app
    catch (MsalServiceException ex)
    {
        // Kind of errors you could have (in ex.Message)

        // AADSTS50059: No tenant-identifying information found in either the request or implied by any provided credentials.
        // Mitigation: as explained in the message from Azure AD, the authority needs to be tenanted. you have probably created
        // your public client application with the following authorities:
        // https://login.microsoftonline.com/common or https://login.microsoftonline.com/organizations

        // AADSTS90133: Device Code flow is not supported under /common or /consumers endpoint.
        // Mitigation: as explained in the message from Azure AD, the authority needs to be tenanted

        // AADSTS90002: Tenant <tenantId or domain you used in the authority> not found. This may happen if there are
        // no active subscriptions for the tenant. Check with your subscription administrator.
        // Mitigation: if you have an active subscription for the tenant this might be that you have a typo in the
        // tenantId (GUID) or tenant domain name.
    }
    catch (OperationCanceledException ex)
    {
        // If you use a CancellationToken, and call the Cancel() method on it, then this *may* be triggered
        // to indicate that the operation was cancelled.
        // See https://learn.microsoft.com/dotnet/standard/threading/cancellation-in-managed-threads
        // for more detailed information on how C# supports cancellation in managed threads.
    }
    catch (MsalClientException ex)
    {
        // Possible cause - verification code expired before contacting the server
        // This exception will occur if the user does not manage to sign-in before a time out (15 mins) and the
        // call to `AcquireTokenWithDeviceCode` is not cancelled in between
    }
}
```

## [ジャワ](#tab/java)
この抜粋は、[MSAL Java コード サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-java/blob/dev/msal4j-sdk/src/samples/public-client/DeviceCodeFlow.java)から取得したものです。

```java
 private static IAuthenticationResult acquireTokenDeviceCode() throws Exception {

        // Load token cache from file and initialize token cache aspect. The token cache will have
        // dummy data, so the acquireTokenSilently call will fail.
        TokenCacheAspect tokenCacheAspect = new TokenCacheAspect("sample_cache.json");

        PublicClientApplication pca = PublicClientApplication.builder(CLIENT_ID)
                .authority(AUTHORITY)
                .setTokenCacheAccessAspect(tokenCacheAspect)
                .build();

        Set<IAccount> accountsInCache = pca.getAccounts().join();
        // Take first account in the cache. In a production application, you would filter
        // accountsInCache to get the right account for the user authenticating.
        IAccount account = accountsInCache.iterator().next();

        IAuthenticationResult result;
        try {
            SilentParameters silentParameters =
                    SilentParameters
                            .builder(SCOPE, account)
                            .build();

            // try to acquire token silently. This call will fail since the token cache
            // does not have any data for the user you are trying to acquire a token for
            result = pca.acquireTokenSilently(silentParameters).join();
        } catch (Exception ex) {
            if (ex.getCause() instanceof MsalException) {

                Consumer<DeviceCode> deviceCodeConsumer = (DeviceCode deviceCode) ->
                        System.out.println(deviceCode.message());

                DeviceCodeFlowParameters parameters =
                        DeviceCodeFlowParameters
                                .builder(SCOPE, deviceCodeConsumer)
                                .build();

                // Try to acquire a token via device code flow. If successful, you should see
                // the token and account information printed out to console, and the sample_cache.json
                // file should have been updated with the latest tokens.
                result = pca.acquireToken(parameters).join();
            } else {
                // Handle other exceptions accordingly
                throw ex;
            }
        }
        return result;
    }
```

## [macOS](#tab/macOS)
このフローは macOS には適用されません。

## [Node.js](#tab/nodejs)
この抜粋は、[MSAL ノード dev サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/device-code)から取得したものです。

```javascript
const msal = require('@azure/msal-node');

const msalConfig = {
    auth: {
        clientId: "your_client_id_here",
        authority: "your_authority_here",
    }
};

const pca = new msal.PublicClientApplication(msalConfig);

const deviceCodeRequest = {
    deviceCodeCallback: (response) => (console.log(response.message)),
    scopes: ["user.read"],
    timeout: 20,
};

pca.acquireTokenByDeviceCode(deviceCodeRequest).then((response) => {
    console.log(JSON.stringify(response));
}).catch((error) => {
    console.log(JSON.stringify(error));
});
```

## [Python](#tab/python)
この抜粋は、[MSAL Python dev サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-python/blob/dev/sample/)から取得したものです。

```python
# Create a preferably long-lived app instance which maintains a token cache.
app = msal.PublicClientApplication(
    config["client_id"], authority=config["authority"],
    # token_cache=...  # Default cache is in memory only.
                       # You can learn how to use SerializableTokenCache from
                       # https://msal-python.rtfd.io/en/latest/#msal.SerializableTokenCache
    )

# The pattern to acquire a token looks like this.
result = None

# Note: If your device-flow app does not have any interactive ability, you can
#   completely skip the following cache part. But here we demonstrate it anyway.
# We now check the cache to see if we have some end users signed in before.
accounts = app.get_accounts()
if accounts:
    logging.info("Account(s) exists in cache, probably with token too. Let's try.")
    print("Pick the account you want to use to proceed:")
    for a in accounts:
        print(a["username"])
    # Assuming the end user chose this one
    chosen = accounts[0]
    # Now let's try to find a token in cache for this account
    result = app.acquire_token_silent(config["scope"], account=chosen)

if not result:
    logging.info("No suitable token exists in cache. Let's get a new one from Azure AD.")

    flow = app.initiate_device_flow(scopes=config["scope"])
    if "user_code" not in flow:
        raise ValueError(
            "Fail to create device flow. Err: %s" % json.dumps(flow, indent=4))

    print(flow["message"])
    sys.stdout.flush()  # Some terminal needs this to ensure the message is shown

    # Ideally you should wait here, in order to save some unnecessary polling
    # input("Press Enter after signing in from another device to proceed, CTRL+C to abort.")

    result = app.acquire_token_by_device_flow(flow)  # By default it will block
        # You can follow this instruction to shorten the block time
        #    https://msal-python.readthedocs.io/en/latest/#msal.PublicClientApplication.acquire_token_by_device_flow
        # or you may even turn off the blocking behavior,
        # and then keep calling acquire_token_by_device_flow(flow) in your own customized loop
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-desktop-acquire-token-integrated-windows-authentication"} -->
## Web API を呼び出し、統合 Windows 認証でトークンを取得する (デスクトップ アプリ) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-integrated-windows-authentication
- Service: identity-platform / workforce
- Article date: 2022-10-07
- Summary: Web API を呼び出し、統合 Windows 認証でアプリのトークンを取得するデスクトップ アプリを構築する方法について説明します

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

ドメインまたは Microsoft Entra 参加済みのマシンでドメイン ユーザーをサインインさせるには、統合 Windows 認証 (IWA) を使用します。

### 制約

- 統合 Windows 認証は、"*フェデレーション+*" ユーザー (Active Directory で作成され、Microsoft Entra ID によってサポートされているユーザー) に対してのみ使用できます。 Microsoft Entra ID で直接作成され、Active Directory のサポートのないユーザー ("*マネージド*" ユーザーと呼ばれます) はこの認証フローを使用できません。 この制限は、ユーザー名とパスワードのフローには影響しません。
- IWA では[多要素認証 (MFA)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks) はバイパスされません。 MFA が構成されている状況では、MFA チャレンジが必要な場合に IWA が失敗する可能性があります。これは、MFA でユーザーの操作が必要になるためです。

    IWA は非対話型ですが、MFA にはユーザーの操作が必要です。 ID プロバイダーが MFA の実行を要求するタイミングの制御は、ユーザーではなくテナント管理者が行います。 弊社の観測によると、MFA が必要なのは、他の国/地域からサインインする場合と VPN 経由で企業ネットワークに接続されていない場合です。ただし、VPN 経由で接続されている場合であっても MFA が必要になる可能性があります。 確定的なルール セットを想定しないでください。 Microsoft Entra ID では、AI を使用して、MFA が必要かどうかを継続的に学習します。 IWA が失敗した場合は、対話型認証やデバイス コード フローなどのユーザー プロンプトにフォールバックしてください。
- `PublicClientApplicationBuilder` で渡される機関の要件は次のとおりです。

    - `https://login.microsoftonline.com/{tenant}/` の形式でテナント化されている。ここで、`tenant` は、テナント ID を表す GUID またはテナントに関連付けられているドメインです。
    - 任意の職場および学校アカウント用である: `https://login.microsoftonline.com/organizations/`。
    - Microsoft 個人アカウントはサポートされていません。 /common および /consumers テナントを使用することはできません。
- 統合 Windows 認証はサイレント フローであるため、次の要件が適用されます。

    - アプリケーションのユーザーが、アプリケーションの使用に事前に同意しておく必要があります。
    - または、テナント管理者が、テナント内のすべてのユーザーによるアプリケーションの使用に事前に同意しておく必要があります。
    - つまり、以下の要件が適用されます。
        - 開発者が自分で Azure portal 上の **[許可]** ボタンを選択しておきます。
        - または、テナント管理者がアプリケーションの登録の **[API のアクセス許可]** タブにある **[{テナント ドメイン} の管理者の同意を付与/取り消す]** ボタンを選択しておきます。 詳細については、「[Web API にアクセスするためのアクセス許可を追加する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-configure-app-access-web-apis#add-permissions-to-access-your-web-api)」を参照してください。
        - または、ユーザーがアプリケーションに同意する方法を指定しておきます。 詳細については、「[個々のユーザーの同意を要求する](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview#user-consent)」を参照してください。
        - または、テナント管理者がアプリケーションに同意する方法を指定しておきます。 詳細については、[管理者の同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview#administrator-consent)に関する記事を参照してください。
- このフローは、.NET デスクトップ、.NET、UWP の各アプリに対して有効です。

同意の詳細については、[Microsoft ID プラットフォームでのアクセス許可と同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)に関する記事を参照してください。

### 使用方法の確認

## [.NET](#tab/dotnet)
MSAL.NET では、次のように使用します。

```csharp
AcquireTokenByIntegratedWindowsAuth(IEnumerable<string> scopes)
```

通常、必要なパラメーターは 1 つだけです (`scopes`)。 Windows 管理者によるポリシーの設定方法に応じて、Windows マシン上のアプリケーションではサインインしているユーザーの検索が許可されない場合があります。 その場合は、2 番目のメソッドである `.WithUsername()` を使用して、サインインしているユーザーのユーザー名を UPN 形式で渡します (例: `joe@contoso.com`)。

次のサンプルは、最新のケース (および取得可能な例外の種類の説明とその軽減策) を示しています。

```csharp
static async Task GetATokenForGraph()
{
 string authority = "https://login.microsoftonline.com/contoso.com";
 string[] scopes = new string[] { "user.read" };
 IPublicClientApplication app = PublicClientApplicationBuilder
      .Create(clientId)
      .WithAuthority(authority)
      .Build();

 var accounts = await app.GetAccountsAsync();

 AuthenticationResult result = null;
 if (accounts.Any())
 {
  result = await app.AcquireTokenSilent(scopes, accounts.FirstOrDefault())
      .ExecuteAsync();
 }
 else
 {
  try
  {
   result = await app.AcquireTokenByIntegratedWindowsAuth(scopes)
      .ExecuteAsync(CancellationToken.None);
  }
  catch (MsalUiRequiredException ex)
  {
   // MsalUiRequiredException: AADSTS65001: The user or administrator has not consented to use the application
   // with ID '{appId}' named '{appName}'.Send an interactive authorization request for this user and resource.

   // you need to get user consent first. This can be done, if you are not using .NET (which does not have any Web UI)
   // by doing (once only) an AcquireToken interactive.

   // If you are using .NET or don't want to do an AcquireTokenInteractive, you might want to suggest the user to navigate
   // to a URL to consent: https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id={clientId}&response_type=code&scope=user.read

   // AADSTS50079: The user is required to use multi-factor authentication.
   // There is no mitigation - if MFA is configured for your tenant and AAD decides to enforce it,
   // you need to fallback to an interactive flows such as AcquireTokenInteractive or AcquireTokenByDeviceCode
   }
   catch (MsalServiceException ex)
   {
    // Kind of errors you could have (in ex.Message)

    // MsalServiceException: AADSTS90010: The grant type is not supported over the /common or /consumers endpoints. Please use the /organizations or tenant-specific endpoint.
    // you used common.
    // Mitigation: as explained in the message from Azure AD, the authority needs to be tenanted or otherwise organizations

    // MsalServiceException: AADSTS70002: The request body must contain the following parameter: 'client_secret or client_assertion'.
    // Explanation: this can happen if your application was not registered as a public client application in Azure AD
    // Mitigation: in the Azure portal, edit the manifest for your application and set the `allowPublicClient` to `true`
   }
   catch (MsalClientException ex)
   {
      // Error Code: unknown_user Message: Could not identify logged in user
      // Explanation: the library was unable to query the current Windows logged-in user or this user is not AD or AAD
      // joined (work-place joined users are not supported).

      // Mitigation 1: on UWP, check that the application has the following capabilities: Enterprise Authentication,
      // Private Networks (Client and Server), User Account Information

      // Mitigation 2: Implement your own logic to fetch the username (e.g. john@contoso.com) and use the
      // AcquireTokenByIntegratedWindowsAuth form that takes in the username

      // Error Code: integrated_windows_auth_not_supported_managed_user
      // Explanation: This method relies on a protocol exposed by Active Directory (AD). If a user was created in Azure
      // Active Directory without AD backing ("managed" user), this method will fail. Users created in AD and backed by
      // AAD ("federated" users) can benefit from this non-interactive method of authentication.
      // Mitigation: Use interactive authentication
   }
 }

 Console.WriteLine(result.Account.Username);
}
```

AcquireTokenByIntegratedWindowsAuthentication で使用可能な修飾子の一覧については、[AcquireTokenByIntegratedWindowsAuthParameterBuilder](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokenbyintegratedwindowsauthparameterbuilder#methods) に関する記事を参照してください。

## [ジャワ](#tab/java)
この抜粋は、[MSAL Java コード サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-java/blob/dev/msal4j-sdk/src/samples/public-client/IntegratedWindowsAuthenticationFlow.java)から取得したものです。

```java
   PublicClientApplication pca = PublicClientApplication.builder(clientId)
                .authority(authority)
                .build();

        Set<IAccount> accountsInCache = pca.getAccounts().join();
        IAccount account = getAccountByUsername(accountsInCache, username);

        //Attempt to acquire token when user's account is not in the application's token cache
        IAuthenticationResult result = acquireTokenIntegratedWindowsAuth(pca, scope, account, username);
        System.out.println("Account username: " + result.account().username());
        System.out.println("Access token:     " + result.accessToken());
        System.out.println("Id token:         " + result.idToken());
        System.out.println();

        //Get list of accounts from the application's token cache, and search them for the configured username
        //getAccounts() will be empty on this first call, as accounts are added to the cache when acquiring a token
        accountsInCache = pca.getAccounts().join();
        account = getAccountByUsername(accountsInCache, username);

        //Attempt to acquire token again, now that the user's account and a token are in the application's token cache
        result = acquireTokenIntegratedWindowsAuth(pca, scope, account, username);
        System.out.println("Account username: " + result.account().username());
        System.out.println("Access token:     " + result.accessToken());
        System.out.println("Id token:         " + result.idToken());
    }

    private static IAuthenticationResult acquireTokenIntegratedWindowsAuth( PublicClientApplication pca,
                                                                            Set<String> scope,
                                                                            IAccount account,
                                                                            String username) throws Exception {

        IAuthenticationResult result;
        try {
            SilentParameters silentParameters =
                    SilentParameters
                            .builder(scope)
                            .account(account)
                            .build();
            // Try to acquire token silently. This will fail on the first acquireTokenIntegratedWindowsAuth() call
            // because the token cache does not have any data for the user you are trying to acquire a token for
            result = pca.acquireTokenSilently(silentParameters).join();
            System.out.println("==acquireTokenSilently call succeeded");
        } catch (Exception ex) {
            if (ex.getCause() instanceof MsalException) {
                System.out.println("==acquireTokenSilently call failed: " + ex.getCause());
                IntegratedWindowsAuthenticationParameters parameters =
                        IntegratedWindowsAuthenticationParameters
                                .builder(scope, username)
                                .build();

                // Try to acquire a token using Integrated Windows Authentication (IWA). You will need to generate a Kerberos ticket.
                // If successful, you should see the token and account information printed out to console
                result = pca.acquireToken(parameters).join();
                System.out.println("==Integrated Windows Authentication flow succeeded");
            } else {
                // Handle other exceptions accordingly
                throw ex;
            }
        }
        return result;
    }
```

## [macOS](#tab/macOS)
このフローは macOS には適用されません。

## [Node.js](#tab/nodejs)
このフローは、MSAL ノードではまだサポートされていません。

## [Python](#tab/python)
このフローは、MSAL Python ではまだサポートされていません。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-desktop-acquire-token-interactive"} -->
## Web API を呼び出し、対話式でトークンを取得する (デスクトップ アプリ) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-interactive
- Service: identity-platform / workforce
- Article date: 2026-06-15
- Summary: Web API を呼び出し、対話式でアプリのトークンを取得するデスクトップ アプリを構築する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

次の例は、Microsoft Graph を使用してユーザーのプロファイルを読み取るためにトークンを対話形式で取得する最小限のコードを示しています。

## [.NET](#tab/dotnet)
#### MSAL.NET のコード

```csharp
string[] scopes = new string[] { "user.read" };

var app = PublicClientApplicationBuilder.Create("YOUR_CLIENT_ID")
    .WithDefaultRedirectUri()
    .Build();

var accounts = await app.GetAccountsAsync();

AuthenticationResult result;
try
{
    result = await app.AcquireTokenSilent(scopes, accounts.FirstOrDefault())
      .ExecuteAsync();
}
catch (MsalUiRequiredException)
{
    result = await app.AcquireTokenInteractive(scopes).ExecuteAsync();
}
```

#### 必須のパラメーター

`AcquireTokenInteractive` の必須パラメーターは `scopes` の 1 つだけです。 これには、トークンを必要とするスコープを定義する文字列の列挙型が含まれています。 Microsoft Graph 用のトークンの場合、必要なスコープは各 Microsoft Graph API の API リファレンスの「アクセス許可」というセクションにあります。たとえば、[ユーザーの連絡先を一覧表示する](https://learn.microsoft.com/ja-jp/graph/api/user-list-contacts)には、`User.Read` および `Contacts.Read` の両方をスコープとして使用する必要があります。 詳細については、「[Microsoft Graph のアクセス許可のリファレンス](https://learn.microsoft.com/ja-jp/graph/permissions-reference)」を参照してください。

デスクトップ アプリケーションとモバイル アプリケーションの両方で、 `.WithParentActivityOrWindow`を使用して対話型サインイン UI の親ウィンドウまたはアクティビティを指定することが重要です。 多くの場合、これは要件であり、MSAL は例外を発生させます。

デスクトップ アプリケーションについては、「[親ウィンドウ ハンドル](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-wam#parent-window-handles)」を参照してください。

モバイル アプリケーションの場合は、`Activity` (Android) または `UIViewController` (iOS) を指定します。

#### MSAL.NET の特定の省略可能なパラメーター

次の省略可能なパラメーターを使用すると、対話型トークン取得エクスペリエンスをカスタマイズできます。

##### WithParentActivityOrWindow

サインイン エクスペリエンスは対話型であるため、親ウィンドウまたはアクティビティの指定は重要です。 `AcquireTokenInteractive` には、親ウィンドウまたはアクティビティを (それをサポートするプラットフォーム用に) 指定できる 1 つの特定の省略可能なパラメーターがあります。 デスクトップ アプリケーションで `.WithParentActivityOrWindow` を使用する場合、その型はプラットフォームによって異なります。

または、サインイン ダイアログが画面のどこに表示されるかを制御する必要がない場合は、省略可能な親ウィンドウ パラメーターを省略してウィンドウを作成することができます。 このオプションは、コマンド ラインに基づいたアプリケーション、他の任意のバックエンド サービスへの呼び出しを渡すために使用されるアプリケーション、ユーザーの操作のためのウィンドウを必要としないアプリケーションに適用されます。

```csharp
// net45
WithParentActivityOrWindow(IntPtr windowPtr)
WithParentActivityOrWindow(IWin32Window window)

// Mac
WithParentActivityOrWindow(NSWindow window)

// .NET Standard (this will be on all platforms at runtime, but only on .NET Standard platforms at build time)
WithParentActivityOrWindow(object parent).
```

解説:

- .NET Standard では、想定される `object` の値は `Activity` (Android の場合)、`UIViewController` (iOS の場合)、`NSWindow` (Mac の場合)、`IWin32Window` または `IntPr` (Windows の場合) です。
- Windows では、埋め込みブラウザーが適切な UI 同期コンテキストを取得するように、UI スレッドから `AcquireTokenInteractive` を呼び出す必要があります。 UI スレッドからの呼び出しでない場合は、メッセージが適切にポンプされなかったり、UI によるデッドロック シナリオの原因となったりする可能性があります。 UI スレッドでない場所で UI スレッドから Microsoft 認証ライブラリ (MSAL) を呼び出す方法の 1 つとしては、Windows Presentation Foundation (WPF) で `Dispatcher` を使用する方法があります。
- WPF を使用している場合に WPF コントロールからウィンドウを取得するには、`WindowInteropHelper.Handle` クラスを使用できます。 そして、呼び出しは WPF コントロール (`this`) から次のように行われます。

    ```csharp
    result = await app.AcquireTokenInteractive(scopes)
                      .WithParentActivityOrWindow(new WindowInteropHelper(this).Handle)
                      .ExecuteAsync();
    ```

##### WithPrompt

`WithPrompt()` を使用して、プロンプトを指定してユーザーとのインタラクティビティを制御します。 厳密な動作を [Microsoft.Identity.Client.Prompt](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.prompt) 構造体で制御できます。

この構造体では次の定数を定義します。

- `SelectAccount` により、ユーザーがセッションを確立しているアカウントを含む、アカウントの選択ダイアログがセキュリティ トークン サービス (STS) で強制的に表示されます。 これは既定のオプションです。 これは、さまざまな ID からの選択をユーザーにさせる場合に有用です。

    このオプションを使用すると、MSAL から ID プロバイダーに `prompt=select_account` が送信されます。 これにより、使用可能な情報 (アカウント、ユーザーのセッションの有無など) に基づいて最適なエクスペリエンスが提供されます。 正当な理由がない限り、変更しないでください。
- `Consent` により、アプリケーションで以前に同意が得られている場合であっても、ユーザーの同意を強制的に求めることができます。 この場合、MSAL から ID プロバイダーに `prompt=consent` が送信されます。 このオプションは、組織のガバナンスによって、アプリケーションを開くたびに同意のダイアログ ボックスをユーザーに対して表示することを求めるような、セキュリティを重視する一部のアプリケーションで使用できます。
- `ForceLogin` により、このユーザー プロンプトが不要な場合でも、アプリケーションでユーザーに資格情報の入力を求めるようにすることができます。 このオプションは、トークンの取得に失敗した場合に、ユーザーが再度サインインできるようにするのに役立ちます。 この場合、MSAL から ID プロバイダーに `prompt=login` が送信されます。 組織では、このオプションを、アプリケーションの特定の部分にアクセスするたびにユーザーが再度サインインすることがガバナンスとして求められる、セキュリティを重視するアプリケーションで使用することがあります。
- `Create` により、`prompt=create` を ID プロバイダーに送信することで、外部 ID のためのサインアップ エクスペリエンスがトリガーされます。 Azure Active Directory B2C (Azure AD B2C) アプリでは、このプロンプトを送信しないでください。 詳細については、「[セルフサービス サインアップのユーザー フローをアプリに追加する](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-user-flow)」を参照してください。
- `Never`（.NET 4.5 および Windows ランタイム のみ）では、ユーザーに確認を求めません。 代わりに、非表示の埋め込み Web ビューに格納された Cookie の使用を試みます。

    このオプションを使用すると、失敗する場合があります。 その場合、`AcquireTokenInteractive` は、UI 操作が必要であることを通知するために例外をスローします。 次に、別の `Prompt` パラメーターが使用されます。
- `NoPrompt` は、ID プロバイダーにプロンプトを送信しません。 ID プロバイダーは、ユーザーに最適なサインイン エクスペリエンスを決定します (シングル サインオンまたはアカウントの選択)。

    このオプションは、Azure AD B2C でプロファイル ポリシーを編集する場合には必須です。 詳細については、[Azure AD B2C での詳細](https://aka.ms/msal-net-b2c-specificities)に関するページを参照してください。

##### WithUseEmbeddedWebView

`WithUseEmbeddedWebView` では、埋め込み WebView とシステム WebView のどちらを強制的に使用するかを指定できます (使用可能な場合)。 詳細については、[Web ブラウザーの使用](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/using-web-browsers)に関する記事を参照してください。

```csharp
var result = await app.AcquireTokenInteractive(scopes)
                    .WithUseEmbeddedWebView(true)
                    .ExecuteAsync();
```

##### WithExtraScopeToConsent

`WithExtraScopeToConsent` は、ユーザーが前もって複数のリソースに同意し、増分同意を使用しない高度なシナリオ用です。 開発者は通常、MSAL.NET とMicrosoft ID プラットフォームでは追加の同意を使用します。

```csharp
var result = await app.AcquireTokenInteractive(scopesForCustomerApi)
                     .WithExtraScopeToConsent(scopesForVendorApi)
                     .ExecuteAsync();
```

##### WithCustomWebUi

Web UI は、ブラウザーを起動するメカニズムです。 このメカニズムは、専用の UI WebBrowser コントロール、またはブラウザーを開くことを委任する方法の場合があります。 MSAL によりほとんどのプラットフォームに Web UI の実装が提供されますが、これらの場合には、ブラウザーを自分でホストする必要があることがあります。

- デスクトップ上の Blazor、Unity、Mono など、MSAL が明示的にカバーしていないプラットフォームがあります。
- UI でアプリケーションのテストを行い、Selenium で使用できる自動化されたブラウザーを使用する必要がある場合。
- ブラウザーと MSAL を実行するアプリが別々のプロセスにある場合。

これを実現するには、MSAL に `start Url` を指定します。これは、ユーザーが自分のユーザー名などの項目を入力できるように、ブラウザーに表示される必要があります。 認証が完了したら、Microsoft Entra ID によって提供されるコードが含まれる MSAL `end Url` にアプリを返す必要があります。 `end Url` のホストは常に `redirectUri` です。 `end Url` をインターセプトするには、次のいずれかを行います。

- `redirect Url` に到達するまでブラウザーのリダイレクトを追跡します。
- ブラウザーを監視する URL にリダイレクトさせます。

`WithCustomWebUi` は、パブリック クライアント アプリケーションで独自の UI を提供するために使用できる拡張ポイントです。 また、ユーザーが ID プロバイダーの `/Authorize` エンドポイントを経由し、サインインして同意できるようにすることもできます。 MSAL.NET では、その後で認証コードを引き換えて、トークンを取得できます。

たとえば、Visual Studio で `WithCustomWebUi` を使用して、電子アプリケーション (Visual Studio フィードバックなど) に Web 操作を提供させて、ほとんどの処理を MSAL.NET に任せることができます。 また、`WithCustomWebUi` を使用して UI オートメーションを提供することもできます。

パブリック クライアント アプリケーションの場合、MSAL.NET では Proof Key for Code Exchange (PKCE) 標準を使用してセキュリティが確保されるようにします。 コードを引き換えることができるのは MSAL.NET のみです。 詳細については、「[RFC 7636 - Proof Key for Code Exchange by OAuth Public Clients](https://tools.ietf.org/html/rfc7636)」を参照してください。

```csharp
using Microsoft.Identity.Client.Extensions;
```

###### WithCustomWebUI を使用する

`WithCustomWebUI` を使用するには、次の手順に従います。

1. `ICustomWebUi` インターフェイスを実装します。 詳細については、[GitHubの ICustomWebUi インターフェイス ソースを](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/blob/053a98d16596be7e9ca1ab916924e5736e341fe8/src/Microsoft.Identity.Client/Extensibility/ICustomWebUI.cs#L32-L70)参照してください。
2. 1 つの `AcquireAuthorizationCodeAsync` メソッドを実装し、MSAL.NET が計算する認証コード URL を受け入れます。
3. ユーザーに ID プロバイダーとの対話を実行させ、ID プロバイダーが実装のコールバックに使用した URL (認証コードを含む) を返します。 問題がある場合は、MSAL と適切に連携するために、`MsalExtensionException` 例外が実装によってスローされます。
4. `AcquireTokenInteractive` の呼び出しで、カスタム Web UI のインスタンスを渡して `.WithCustomWebUi()` 修飾子を使用します。

    ```csharp
    result = await app.AcquireTokenInteractive(scopes)
                      .WithCustomWebUi(yourCustomWebUI)
                      .ExecuteAsync();
    ```

MSAL.NET チームでは、この拡張メカニズムを使用するように UI テストを書き換えました。 関心がある場合は、MSAL.NET のソース コードで [SeleniumWebUI](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/blob/053a98d16596be7e9ca1ab916924e5736e341fe8/tests/Microsoft.Identity.Test.Integration/Infrastructure/SeleniumWebUI.cs#L15-L160) クラスをご確認ください。

###### SystemWebViewOptions を使用して優れたエクスペリエンスを提供する

MSAL.NET 4.1 [`SystemWebViewOptions`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.systemwebviewoptions) から、以下を指定できます。

- システム Web ブラウザーでサインイン エラーまたは同意エラーが表示される場合に、(`BrowserRedirectError`) に移動する URI または (`HtmlMessageError`) を表示する HTML フラグメント。
- サインインあるいは同意が成功した場合に、(`BrowserRedirectSuccess`) に移動する URI または (`HtmlMessageSuccess`) を表示する HTML フラグメント。
- システム ブラウザーを起動するために実行するアクション。 `OpenBrowserAsync` デリゲートを設定して、独自の実装を提供できます。 このクラスでは、2 つのブラウザー (Microsoft Edge 用の `OpenWithEdgeBrowserAsync` と `OpenWithChromeEdgeBrowserAsync` 用 の ) の既定の実装も用意されています。

この構造体を使用するには、次の例のように記述します。

```csharp
IPublicClientApplication app;
...

options = new SystemWebViewOptions
{
 HtmlMessageError = "<b>Sign-in failed. You can close this tab ...</b>",
 BrowserRedirectSuccess = "https://contoso.com/help-for-my-awesome-commandline-tool.html"
};

var result = app.AcquireTokenInteractive(scopes)
                .WithEmbeddedWebView(false)       // The default in .NET
                .WithSystemWebViewOptions(options)
                .Build();
```

##### その他の省略可能なパラメーター

`AcquireTokenInteractive`の追加の省略可能なパラメーターについては、「[AcquireTokenInteractiveParameterBuilder](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokeninteractiveparameterbuilder#methods)」を参照してください。

## [ジャワ](#tab/java)
#### MSAL Javaのコード

```java
private static IAuthenticationResult acquireTokenInteractive() throws Exception {

    // Load the token cache from the file and initialize the token cache aspect. The token cache will have
    // dummy data, so the acquireTokenSilently call will fail.
    TokenCacheAspect tokenCacheAspect = new TokenCacheAspect("sample_cache.json");

    PublicClientApplication pca = PublicClientApplication.builder(CLIENT_ID)
            .authority(AUTHORITY)
            .setTokenCacheAccessAspect(tokenCacheAspect)
            .build();

    Set<IAccount> accountsInCache = pca.getAccounts().join();
    // Take the first account in the cache. In a production application, you would filter
    // accountsInCache to get the right account for the user who is authenticating.
    IAccount account = accountsInCache.iterator().next();

    IAuthenticationResult result;
    try {
        SilentParameters silentParameters =
                SilentParameters
                        .builder(SCOPE, account)
                        .build();

        // try to acquire the token silently. This call will fail because the token cache
        // does not have any data for the user you're trying to acquire a token for
        result = pca.acquireTokenSilently(silentParameters).join();
    } catch (Exception ex) {
        if (ex.getCause() instanceof MsalException) {

            InteractiveRequestParameters parameters = InteractiveRequestParameters
                    .builder(new URI("http://localhost"))
                    .scopes(SCOPE)
                    .build();

            // Try to acquire a token interactively with the system browser. If successful, you should see
            // the token and account information printed out to the console
            result = pca.acquireToken(parameters).join();
        } else {
            // Handle other exceptions accordingly
            throw ex;
        }
    }
    return result;
}
```

## [macOS](#tab/macOS)
#### iOS および macOS 用の MSAL のコード

次の例は、iOS および macOS 用 MSAL での対話型トークンの取得 (Objective-C と Swift) を示しています。

```objc
MSALInteractiveTokenParameters *interactiveParams = [[MSALInteractiveTokenParameters alloc] initWithScopes:scopes webviewParameters:[MSALWebviewParameters new]];
[application acquireTokenWithParameters:interactiveParams completionBlock:^(MSALResult *result, NSError *error) {
    if (!error)
    {
        // You'll want to get the account identifier to retrieve and reuse the account
        // for later acquireToken calls
        NSString *accountIdentifier = result.account.identifier;

        NSString *accessToken = result.accessToken;
    }
}];
```

```swift
let interactiveParameters = MSALInteractiveTokenParameters(scopes: scopes, webviewParameters: MSALWebviewParameters())
application.acquireToken(with: interactiveParameters, completionBlock: { (result, error) in

    guard let authResult = result, error == nil else {
        print(error!.localizedDescription)
        return
    }

    // Get the access token from the result
    let accessToken = authResult.accessToken
})
```

## [Node.js](#tab/nodejs)
MSAL ノードでは、コード交換のための証明鍵 (PKCE) を使用して認可コード フロー経由でトークンを取得します。 このプロセスは 2 つの手順で構成されます。

1. アプリケーションで、認可コードを生成するために使用できる URL が取得されます。 ユーザーはブラウザーで URL を開き、資格情報を入力できます。 その後、認可コードとともに、`redirectUri`（アプリ登録時に登録されたもの）にリダイレクトして戻されます。
2. アプリケーションでは受け取った認可コードを `acquireTokenByCode()` メソッドに渡し、そこでアクセス トークンに交換されます。

```javascript
const msal = require("@azure/msal-node");

const msalConfig = {
    auth: {
        clientId: "your_client_id_here",
        authority: "your_authority_here",
    }
};

const pca = new msal.PublicClientApplication(msalConfig);

const {verifier, challenge} = await msal.cryptoProvider.generatePkceCodes();

const authCodeUrlParameters = {
    scopes: ["User.Read"],
    redirectUri: "your_redirect_uri",
    codeChallenge: challenge, // PKCE code challenge
    codeChallengeMethod: "S256" // PKCE code challenge method 
};

// Get the URL to sign in the user and consent to scopes needed for the application
pca.getAuthCodeUrl(authCodeUrlParameters).then((response) => {
    console.log(response);

    const tokenRequest = {
        code: response["authorization_code"],
        codeVerifier: verifier // PKCE code verifier 
        redirectUri: "your_redirect_uri",
        scopes: ["User.Read"],
    };

    // Acquire a token by exchanging the code
    pca.acquireTokenByCode(tokenRequest).then((response) => {
        console.log("\nResponse: \n:", response);
    }).catch((error) => {
        console.log(error);
    });
}).catch((error) => console.log(JSON.stringify(error)));
```

## [Python](#tab/python)
#### MSAL Pythonのコード

Note

この機能には、MSAL Python バージョン 1.7 以降が必要です。

MSAL Pythonには、トークンを取得するための対話型メソッドが用意されています。

```python
result = None

# Check the cache to see if this user has signed in before
accounts = app.get_accounts(username=config["username"])
if accounts:
    result = app.acquire_token_silent(config["scope"], account=accounts[0])

if not result:
    result = app.acquire_token_interactive(  # It automatically provides PKCE protection
         scopes=config["scope"])
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-desktop-acquire-token-username-password"} -->
## ユーザー名とパスワードを使用して Web API を呼び出すためのトークンを取得する (デスクトップ アプリ) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-username-password
- Service: identity-platform / workforce
- Article date: 2026-06-15
- Summary: デスクトップ アプリでのユーザー名とパスワード (ROPC) フローのしくみ、非推奨となる理由、より安全な認証フローに移行する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

デスクトップ アプリケーションでは、リソース所有者 パスワード 認証情報と呼ばれるユーザー名とパスワードのフローを使ってサイレントにトークンを取得できます。

警告

ユーザー名とパスワードのフローは、セキュリティ 上のリスクにより非推奨になりました。 Microsoft では、より安全な認証フローを使用することをお勧めします。 [リソース所有者パスワード資格情報 (ROPC) フローから移行する](https://aka.ms/msal-ropc-migration)方法に関するガイダンスに従ってください。 ROPC フローによって発生するリスクと課題の詳細については、[「パスワードの増大する問題の解決策は何ですか?」](https://news.microsoft.com/features/whats-solution-growing-problem-passwords-says-microsoft/)を参照してください。

さらに、ユーザー名とパスワードを使用することで、開発者は次のような多くのことを放棄します。

- 現代のアイデンティティの基本原則 - パスワードはフィッシングされて再生される可能性があります。共有された秘密は傍受される可能性があるためです。
- 多要素認証（MFA）- 相互作用がないため、ユーザーはサインインできません。
- シングル サインオン (SSO) 機能。

ユーザー名とパスワードのフローには、次の制約もあります。

- ユーザー名とパスワードのフローは、条件付きアクセスおよび多要素認証との互換性がありません。 ほとんどの組織が行うように、テナント管理者が多要素認証を必要とする Microsoft Entra でアプリを実行する場合は、このフローを使用できません。
- 職場および学校アカウントにのみ有効です。個別のMicrosoft アカウントではありません。
- このフローは、.NET デスクトップと .NET で使用できますが、UWP では使用できません。

DevOps シナリオなど、場合によっては、ユーザー名とパスワードを使用すると便利です。 ただし、独自の UI を提供する対話型のシナリオでユーザー名とパスワードを使用する場合は、ユーザー名とパスワードのフローから移動することを検討してください。

Windows上のデスクトップ アプリの場合、サイレント トークンの取得に ROPC に代わる推奨される代替手段は[、Windows 認証 ブローカー](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-wam)です。 ROPC または Windows 認証 ブローカーの代わりに、開発者は、Web ブラウザーにアクセスせずにデバイスで[デバイス コード フロー](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/device-code-flow)を使用できます。

リソース所有者パスワード資格情報 (ROPC) フローを使用してソーシャル ID を持つユーザーをサインインさせるデスクトップ アプリケーションを構築する場合は、[Azure AD B2C を使用してソーシャル ID を持つユーザーをサインインする方法を参照してください](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/social-identities)

### ROPC フローを使用する

## [.NET](#tab/dotnet)
`IPublicClientApplication` にはメソッド `AcquireTokenByUsernamePassword` が含まれています。

次のサンプルは、シンプルなケースを示しています。

```csharp
static async Task GetATokenForGraph()
{
 string authority = "https://login.microsoftonline.com/contoso.com";
 string[] scopes = new string[] { "user.read" };
 IPublicClientApplication app;
 app = PublicClientApplicationBuilder.Create(clientId)
       .WithAuthority(authority)
       .Build();
 var accounts = await app.GetAccountsAsync();

 AuthenticationResult result = null;
 if (accounts.Any())
 {
  result = await app.AcquireTokenSilent(scopes, accounts.FirstOrDefault())
                    .ExecuteAsync();
 }
 else
 {
  try
  {
   var securePassword = new SecureString();
   foreach (char c in "dummy")        // you should fetch the password
    securePassword.AppendChar(c);  // keystroke by keystroke

   result = await app.AcquireTokenByUsernamePassword(scopes,
                                                    "joe@contoso.com",
                                                     securePassword)
                      .ExecuteAsync();
  }
  catch(MsalException)
  {
   // See details below
  }
 }
 Console.WriteLine(result.Account.Username);
}
```

次のサンプルは、最新のケース (および取得可能な例外の種類の説明とその軽減策) を示しています。

```csharp
static async Task GetATokenForGraph()
{
 string authority = "https://login.microsoftonline.com/contoso.com";
 string[] scopes = new string[] { "user.read" };
 IPublicClientApplication app;
 app = PublicClientApplicationBuilder.Create(clientId)
                                   .WithAuthority(authority)
                                   .Build();
 var accounts = await app.GetAccountsAsync();

 AuthenticationResult result = null;
 if (accounts.Any())
 {
  result = await app.AcquireTokenSilent(scopes, accounts.FirstOrDefault())
                    .ExecuteAsync();
 }
 else
 {
  try
  {
   var securePassword = new SecureString();
   foreach (char c in "dummy")        // you should fetch the password keystroke
    securePassword.AppendChar(c);  // by keystroke

   result = await app.AcquireTokenByUsernamePassword(scopes,
                                                    "joe@contoso.com",
                                                    securePassword)
                    .ExecuteAsync();
  }
  catch (MsalUiRequiredException ex) when (ex.Message.Contains("AADSTS65001"))
  {
   // Here are the kind of error messages you could have, and possible mitigations

   // ------------------------------------------------------------------------
   // MsalUiRequiredException: AADSTS65001: The user or administrator has not consented to use the application
   // with ID '{appId}' named '{appName}'. Send an interactive authorization request for this user and resource.

   // Mitigation: you need to get user consent first. This can be done either statically (through the portal),
   /// or dynamically (but this requires an interaction with Azure AD, which is not possible with
   // the username/password flow)
   // Statically: in the portal by doing the following in the "API permissions" tab of the application registration:
   // 1. Click "Add a permission" and add all the delegated permissions corresponding to the scopes you want (for instance
   // User.Read and User.ReadBasic.All)
   // 2. Click "Grant/revoke admin consent for <tenant>") and click "yes".
   // Dynamically, if you are not using .NET (which does not have any Web UI) by
   // calling (once only) AcquireTokenInteractive.
   // remember that Username/password is for public client applications that is desktop/mobile applications.
   // If you are using .NET or don't want to call AcquireTokenInteractive, you might want to:
   // - use device code flow (See https://aka.ms/msal-net-device-code-flow)
   // - or suggest the user to navigate to a URL to consent: https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id={clientId}&response_type=code&scope=user.read
   // ------------------------------------------------------------------------

   // ------------------------------------------------------------------------
   // ErrorCode: invalid_grant
   // SubError: basic_action
   // MsalUiRequiredException: AADSTS50079: The user is required to use multi-factor authentication.
   // The tenant admin for your organization has chosen to oblige users to perform multi-factor authentication.
   // Mitigation: none for this flow
   // Your application cannot use the Username/Password grant.
   // Like in the previous case, you might want to use an interactive flow (AcquireTokenInteractive()),
   // or Device Code Flow instead.
   // Note this is one of the reason why using username/password is not recommended;
   // ------------------------------------------------------------------------

   // ------------------------------------------------------------------------
   // ex.ErrorCode: invalid_grant
   // subError: null
   // Message = "AADSTS70002: Error validating credentials.
   // AADSTS50126: Invalid username or password
   // In the case of a managed user (user from an Azure AD tenant opposed to a
   // federated user, which would be owned
   // in another IdP through ADFS), the user has entered the wrong password
   // Mitigation: ask the user to re-enter the password
   // ------------------------------------------------------------------------

   // ------------------------------------------------------------------------
   // ex.ErrorCode: invalid_grant
   // subError: null
   // MsalServiceException: ADSTS50034: To sign into this application the account must be added to
   // the {domainName} directory.
   // or The user account does not exist in the {domainName} directory. To sign into this application,
   // the account must be added to the directory.
   // The user was not found in the directory
   // Explanation: wrong username
   // Mitigation: ask the user to re-enter the username.
   // ------------------------------------------------------------------------
  }
  catch (MsalServiceException ex) when (ex.ErrorCode == "invalid_request")
  {
   // ------------------------------------------------------------------------
   // AADSTS90010: The grant type is not supported over the /common or /consumers endpoints.
   // Please use the /organizations or tenant-specific endpoint.
   // you used common.
   // Mitigation: as explained in the message from Azure AD, the authority you use in the application needs
   // to be tenanted or otherwise "organizations". change the
   // "Tenant": property in the appsettings.json to be a GUID (tenant Id), or domain name (contoso.com)
   // if such a domain is registered with your tenant
   // or "organizations", if you want this application to sign-in users in any Work and School accounts.
   // ------------------------------------------------------------------------

  }
  catch (MsalServiceException ex) when (ex.ErrorCode == "unauthorized_client")
  {
   // ------------------------------------------------------------------------
   // AADSTS700016: Application with identifier '{clientId}' was not found in the directory '{domain}'.
   // This can happen if the application has not been installed by the administrator of the tenant or consented
   // to by any user in the tenant.
   // You may have sent your authentication request to the wrong tenant
   // Cause: The clientId in the appsettings.json might be wrong
   // Mitigation: check the clientId and the app registration
   // ------------------------------------------------------------------------
  }
  catch (MsalServiceException ex) when (ex.ErrorCode == "invalid_client")
  {
   // ------------------------------------------------------------------------
   // AADSTS70002: The request body must contain the following parameter: 'client_secret or client_assertion'.
   // Explanation: this can happen if your application was not registered as a public client application in Azure AD
   // Mitigation: in the Azure portal, edit the manifest for your application and set the `allowPublicClient` to `true`
   // ------------------------------------------------------------------------
  }
  catch (MsalServiceException)
  {
   throw;
  }

  catch (MsalClientException ex) when (ex.ErrorCode == "unknown_user_type")
  {
   // Message = "Unsupported User Type 'Unknown'. Please see https://aka.ms/msal-net-up"
   // The user is not recognized as a managed user, or a federated user. Azure AD was not
   // able to identify the IdP that needs to process the user
   throw new ArgumentException("U/P: Wrong username", ex);
  }
  catch (MsalClientException ex) when (ex.ErrorCode == "user_realm_discovery_failed")
  {
   // The user is not recognized as a managed user, or a federated user. Azure AD was not
   // able to identify the IdP that needs to process the user. That's for instance the case
   // if you use a phone number
   throw new ArgumentException("U/P: Wrong username", ex);
  }
  catch (MsalClientException ex) when (ex.ErrorCode == "unknown_user")
  {
   // the username was probably empty
   // ex.Message = "Could not identify the user logged into the OS. See https://aka.ms/msal-net-iwa for details."
   throw new ArgumentException("U/P: Wrong username", ex);
  }
  catch (MsalClientException ex) when (ex.ErrorCode == "parsing_wstrust_response_failed")
  {
   // ------------------------------------------------------------------------
   // In the case of a Federated user (that is owned by a federated IdP, as opposed to a managed user owned in an Azure AD tenant)
   // ID3242: The security token could not be authenticated or authorized.
   // The user does not exist or has entered the wrong password
   // ------------------------------------------------------------------------
  }
 }

 Console.WriteLine(result.Account.Username);
}
```

`AcquireTokenByUsernamePassword` に適用できるすべての修飾子の詳細については、[AcquireTokenByUsernamePasswordParameterBuilder](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokenbyusernamepasswordparameterbuilder#methods) に関する記事を参照してください。

## [ジャワ](#tab/java)
次の抜粋は、[MSAL Java コード サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-java/blob/dev/msal4j-sdk/src/samples/public-client/UsernamePasswordFlow.java)から取得したものです。

```java
 PublicClientApplication pca = PublicClientApplication.builder(clientId)
                .authority(authority)
                .build();

        //Get list of accounts from the application's token cache, and search them for the configured username
        //getAccounts() will be empty on this first call, as accounts are added to the cache when acquiring a token
        Set<IAccount> accountsInCache = pca.getAccounts().join();
        IAccount account = getAccountByUsername(accountsInCache, username);

        //Attempt to acquire token when user's account is not in the application's token cache
        IAuthenticationResult result = acquireTokenUsernamePassword(pca, scope, account, username, password);
        System.out.println("Account username: " + result.account().username());
        System.out.println("Access token:     " + result.accessToken());
        System.out.println("Id token:         " + result.idToken());
        System.out.println();

        accountsInCache = pca.getAccounts().join();
        account = getAccountByUsername(accountsInCache, username);

        //Attempt to acquire token again, now that the user's account and a token are in the application's token cache
        result = acquireTokenUsernamePassword(pca, scope, account, username, password);
        System.out.println("Account username: " + result.account().username());
        System.out.println("Access token:     " + result.accessToken());
        System.out.println("Id token:         " + result.idToken());
    }

    private static IAuthenticationResult acquireTokenUsernamePassword(PublicClientApplication pca,
                                                                      Set<String> scope,
                                                                      IAccount account,
                                                                      String username,
                                                                      String password) throws Exception {
        IAuthenticationResult result;
        try {
            SilentParameters silentParameters =
                    SilentParameters
                            .builder(scope)
                            .account(account)
                            .build();
            // Try to acquire token silently. This will fail on the first acquireTokenUsernamePassword() call
            // because the token cache does not have any data for the user you are trying to acquire a token for
            result = pca.acquireTokenSilently(silentParameters).join();
            System.out.println("==acquireTokenSilently call succeeded");
        } catch (Exception ex) {
            if (ex.getCause() instanceof MsalException) {
                System.out.println("==acquireTokenSilently call failed: " + ex.getCause());
                UserNamePasswordParameters parameters =
                        UserNamePasswordParameters
                                .builder(scope, username, password.toCharArray())
                                .build();
                // Try to acquire a token via username/password. If successful, you should see
                // the token and account information printed out to console
                result = pca.acquireToken(parameters).join();
                System.out.println("==username/password flow succeeded");
            } else {
                // Handle other exceptions accordingly
                throw ex;
            }
        }
        return result;
    }
```

## [macOS](#tab/macOS)
ROPC フローは、macOS 用の MSAL ではサポートされていません。

## [Node.js](#tab/nodejs)
次のコード例は、 [MSAL Node 開発サンプルのコード例](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples/username-password)です。 次のコード スニペットでは、ユーザー名とパスワードは、説明のためにのみハード コード化されています。 運用環境では避ける必要があります。 代わりに、ユーザーに自分のユーザー名とパスワードの入力を求める基本的な UI を使用することをお勧めします。

```javascript
const msal = require("@azure/msal-node");

const msalConfig = {
    auth: {
        clientId: "your_client_id_here",
        authority: "your_authority_here",
    }
};

const pca = new msal.PublicClientApplication(msalConfig);

// For testing, enter your username and password below.
// In production, replace this with a UI prompt instead.
const usernamePasswordRequest = {
    scopes: ["user.read"],
    username: "", // Add your username here
    password: "", // Add your password here
};

pca.acquireTokenByUsernamePassword(usernamePasswordRequest).then((response) => {
    console.log("acquired token by password grant");
}).catch((error) => {
    console.log(error);
});
```

## [Python](#tab/python)
次のコード例は、[MSAL Python開発サンプルのコード例です](https://github.com/AzureAD/microsoft-authentication-library-for-python/blob/dev/sample/)。

```python
# Create a preferably long-lived app instance which maintains a token cache.
app = msal.PublicClientApplication(
    config["client_id"], authority=config["authority"],
    # token_cache=...  # Default cache is in memory only.
                       # You can learn how to use SerializableTokenCache from
                       # https://msal-python.rtfd.io/en/latest/#msal.SerializableTokenCache
    )

# The pattern to acquire a token looks like this.
result = None

# Firstly, check the cache to see if this end user has signed in before
accounts = app.get_accounts(username=config["username"])
if accounts:
    logging.info("Account(s) exists in cache, probably with token too. Let's try.")
    result = app.acquire_token_silent(config["scope"], account=accounts[0])

if not result:
    logging.info("No suitable token exists in cache. Let's get a new one from AAD.")
    # See this page for constraints of Username Password Flow.
    # https://github.com/AzureAD/microsoft-authentication-library-for-python/wiki/Username-Password-Authentication
    result = app.acquire_token_by_username_password(
        config["username"], config["password"], scopes=config["scope"])
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-desktop-acquire-token-wam"} -->
## Web アカウント マネージャーを使用して Web API を呼び出すためのトークンを取得する (デスクトップ アプリ) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-wam
- Service: identity-platform / workforce
- Article date: 2024-01-15
- Summary: Web API を呼び出し、Web アカウント マネージャーを使用してアプリのトークンを取得するデスクトップ アプリの構築方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Authentication Library (MSAL) は、認証ブローカーとして機能する Windows 10+ コンポーネントである Web アカウント マネージャー (WAM) を呼び出します。 ブローカーによって、アプリのユーザーは、Windows で認識されているアカウント (Windows セッションへとサインインしたアカウントなど) との統合による恩恵を受けられるようになります。

### WAM の価値提案

WAM などの認証ブローカーを使用することで、次のような多くの利点があります。

- セキュリティの強化。 「[トークン保護](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-token-protection)」を参照してください。
- Windows Hello、条件付きアクセス、FIDO キーのサポート。
- Windows の **[電子メール アカウント]** ビューとの統合。
- 高速のシングル サインオン。
- 現在の Windows アカウントを使用してサイレントにサインインする機能。
- バグ修正と機能強化が Windows に付属して提供される。

### WAM の制限事項

- WAM は Windows 10 以降と Windows Server 2019 以降で使用できます。 Mac、Linux、以前のバージョンの Windows では、MSAL はブラウザーに自動的にフォールバックします。
- Azure Active Directory B2C (Azure AD B2C) およびActive Directory フェデレーション サービス (AD FS) オーソリティはサポートされていません。 MSAL はブラウザーにフォールバックします。

### WAM 統合パッケージ

ほとんどのアプリでは、この統合を使用するには `Microsoft.Identity.Client.Broker` パッケージを参照する必要があります。 .NET MAUI アプリでは、これを行う必要はありません。ターゲットが `net6-windows` 以降である場合、この機能は MSAL の内部にあります。

### WAM 呼び出しパターン

WAM については、次のパターンを使用できます。

```csharp
    // 1. Configuration - read below about redirect URI
    var pca = PublicClientApplicationBuilder.Create("client_id")
                    .WithBroker(new BrokerOptions(BrokerOptions.OperatingSystems.Windows))
                    .Build();

    // Add a token cache; see https://learn.microsoft.com/azure/active-directory/develop/msal-net-token-cache-serialization?tabs=desktop

    // 2. Find an account for silent login

    // Is there an account in the cache?
    IAccount accountToLogin = (await pca.GetAccountsAsync()).FirstOrDefault();
    if (accountToLogin == null)
    {
        // 3. No account in the cache; try to log in with the OS account
        accountToLogin = PublicClientApplication.OperatingSystemAccount;
    }

    try
    {
        // 4. Silent authentication 
        var authResult = await pca.AcquireTokenSilent(new[] { "User.Read" }, accountToLogin)
                                    .ExecuteAsync();
    }
    // Cannot log in silently - most likely Azure AD would show a consent dialog or the user needs to re-enter credentials
    catch (MsalUiRequiredException) 
    {
        // 5. Interactive authentication
        var authResult = await pca.AcquireTokenInteractive(new[] { "User.Read" })
                                    .WithAccount(accountToLogin)
                                    // This is mandatory so that WAM is correctly parented to your app; read on for more guidance
                                    .WithParentActivityOrWindow(myWindowHandle) 
                                    .ExecuteAsync();
                                    
        // Consider allowing the user to re-authenticate with a different account, by calling AcquireTokenInteractive again                                  
    }
```

ブローカーが存在しない場合 (Win 8.1、Mac、Linux など)、MSAL はブラウザーにフォールバックされ、リダイレクト URI 規則が適用されます。

#### リダイレクト URI

MSAL で WAM リダイレクト URI を構成する必要はありませんが、アプリの登録でそれらを構成する必要があります。

```
ms-appx-web://Microsoft.AAD.BrokerPlugin/{client_id}

```

#### トークン キャッシュの永続性

MSAL のトークン キャッシュを永続化することが重要です。MSAL では、ID トークンとアカウント メタデータを引き続きそこに格納するためです。 詳細については、「[MSAL.NET でのトークン キャッシュのシリアル化](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization?tabs=desktop)」を参照してください。

#### サイレント ログインのためのアカウント

サイレント ログインのアカウントを見つけるには、次のパターンをお勧めします。

- ユーザーが既にログインしている場合は、そのアカウントを使用します。 そうでない場合は、現在の Windows アカウントについては `PublicClientApplication.OperatingSystemAccount` を使用します。
- ユーザーが対話形式でログインすることで別のアカウントに変更できるようにします。

### 親ウィンドウ ハンドル

`WithParentActivityOrWindow` API を使用して、対話型エクスペリエンスを親にする必要のあるウィンドウで MSAL を構成する必要があります。

#### UI アプリケーション

Windows Forms (WinForms)、Windows Presentation Foundation (WPF)、Windows UI ライブラリ バージョン 3 (WinUI3) などの UI アプリについては、「[ウィンドウ ハンドルを取得する](https://learn.microsoft.com/ja-jp/windows/apps/develop/ui-input/retrieve-hwnd)」を参照してください。

#### コンソール アプリケーション

コンソール アプリケーションの場合は、ターミナル ウィンドウとそのタブのために、構成がもう少し複雑になります。 次のコードを使用します。

```csharp
enum GetAncestorFlags
{   
    GetParent = 1,
    GetRoot = 2,
    /// <summary>
    /// Retrieves the owned root window by walking the chain of parent and owner windows returned by GetParent.
    /// </summary>
    GetRootOwner = 3
}

/// <summary>
/// Retrieves the handle to the ancestor of the specified window.
/// </summary>
/// <param name="hwnd">A handle to the window whose ancestor will be retrieved.
/// If this parameter is the desktop window, the function returns NULL. </param>
/// <param name="flags">The ancestor to be retrieved.</param>
/// <returns>The return value is the handle to the ancestor window.</returns>
[DllImport("user32.dll", ExactSpelling = true)]
static extern IntPtr GetAncestor(IntPtr hwnd, GetAncestorFlags flags);

[DllImport("kernel32.dll")]
static extern IntPtr GetConsoleWindow();

// This is your window handle!
public IntPtr GetConsoleOrTerminalWindow()
{
   IntPtr consoleHandle = GetConsoleWindow();
   IntPtr handle = GetAncestor(consoleHandle, GetAncestorFlags.GetRootOwner );
  
   return handle;
}
```

### トラブルシューティング

#### "WAM アカウント ピッカーからアカウントが返されない" というエラー メッセージ

"WAM アカウント ピッカーからアカウントが返されない" というメッセージは、アカウントを表示するダイアログをアプリケーション ユーザーが閉じたこと、またはダイアログ自体がクラッシュしたことを示します。 Windows コントロールである `AccountsControl` が Windows に正しく登録されていない場合に、クラッシュが発生する可能性があります。 これを解決するには、次の手順に従います。

1. タスク バーで、**[スタート]** を右クリックして、**[Windows PowerShell (管理者)]** を選択します。
2. ユーザー アカウント制御ダイアログによって入力を求められた場合は、**[はい]** を選択して PowerShell を起動します。
3. 以下のスクリプトをコピーして実行します。

    ```powershell
    if (-not (Get-AppxPackage Microsoft.AccountsControl)) { Add-AppxPackage -Register "$env:windir\SystemApps\Microsoft.AccountsControl_cw5n1h2txyewy\AppxManifest.xml" -DisableDevelopmentMode -ForceApplicationShutdown } Get-AppxPackage Microsoft.AccountsControl
    ```

#### 単一ファイルのデプロイ中に "MsalClientException: エラー コード: wam\_runtime\_init\_failed" というエラー メッセージが表示される

アプリケーションを [1 つのファイル バンドル](https://learn.microsoft.com/ja-jp/dotnet/core/deploying/single-file/overview)にパッケージ化すると、次のエラーが表示されることがあります。

```
MsalClientException: wam_runtime_init_failed: The type initializer for 'Microsoft.Identity.Client.NativeInterop.API' threw an exception. See https://aka.ms/msal-net-wam#troubleshooting
```

このエラーは、[Microsoft.Identity.Client.NativeInterop](https://www.nuget.org/packages/Microsoft.Identity.Client.NativeInterop/) のネイティブ バイナリが 1 つのファイル バンドルにパッケージ化されなかったことを示します。 抽出用にそれらのファイルを埋め込み、1 つの出力ファイルを取得するには、`IncludeNativeLibrariesForSelfExtract` プロパティを `true` に設定します。 詳細については、[ネイティブ バイナリを 1 つのファイルにパッケージ化する方法](https://learn.microsoft.com/ja-jp/dotnet/core/deploying/single-file/overview?tabs=cli#native-libraries)を参照してください。

#### 接続の問題

"接続をチェックしてからやり直してください" のようなエラー メッセージがアプリケーション ユーザーに定期的に表示される場合は、「[Office のトラブルシューティング ガイド](https://learn.microsoft.com/ja-jp/microsoft-365/troubleshoot/authentication/connection-issue-when-sign-in-office-2016)」を参照してください。 このトラブルシューティング ガイドでは、ブローカーも使用します。

### サンプル

WAM を使用する WPF サンプルは、[GitHub](https://github.com/azure-samples/active-directory-dotnet-desktop-msgraph-v2) にあります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-desktop-app-configuration"} -->
## Web API を呼び出すデスクトップ アプリを構成する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-app-configuration
- Service: identity-platform / workforce
- Article date: 2024-04-09
- Summary: Web API を呼び出すデスクトップ アプリのコードを構成する方法について説明します

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

この記事には、アプリケーションの座標を使用してコードを構成するのに役立つ手順が含まれています。

### [前提条件]

- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID

### プラットフォーム リダイレクト URI を追加する

アプリの登録にアプリの種類を指定するには、次の手順に従います。

1. [**管理**] で、**認証**&gt;プラットフォームの**追加**&gt;**Mobile およびデスクトップ アプリケーションを選択します**
2. 使用している認証方法に応じて、次のいずれかのオプションを選択します。
    - 埋め込みブラウザーを使用するアプリの場合は、正確な値を使用します。 `https://login.microsoftonline.com/common/oauth2/nativeclient`
    - システム ブラウザーを使用するアプリの場合は、正確な値を使用します。 `http://localhost`
    - macOS 用の Objective-C または Swift アプリ: `msauth.<your.app.bundle.id>://auth`。
    - Node.js Electron アプリケーション: `msal{Your_Application/Client_Id}://auth`

注

[Web 認証マネージャー (WAM)](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-wam) アプリの場合、MSAL ではリダイレクト URI は必要ありません。

### パブリック クライアント フローを有効にする

デバイス コード フロー、統合 Windows 認証、およびユーザー名とパスワードをデーモン アプリケーションで使用されるクライアント資格情報フローを使用した機密クライアント アプリケーションと区別するために、これらはリダイレクト URI を必要としないので、パブリック クライアント アプリケーションとして構成します。 この構成を実現するには

アプリをパブリック クライアントとして識別するには、次の手順に従います。

1. **[管理]** で、 **[認証]** を選択します。
2. **[詳細設定]** で、**[パブリック クライアント フローを許可する]** に対して **[はい]** を選択します。
3. **[保存]** を選択して変更を保存します。

### デスクトップ アプリをサポートする Microsoft ライブラリ

次の Microsoft ライブラリはデスクトップ アプリをサポートしています。

| 言語/フレームワーク | プロジェクトGitHub | Package | 取得started | ユーザーのサインイン | Web API へのアクセス | 一般提供 (GA)*または*パブリック プレビュー^1^ |
| --- | --- | --- | --- | --- | --- | --- |
| Electron | [MSAL Node.js](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node) | [msal-node](https://www.npmjs.com/package/@azure/msal-node) | — | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | パブリック プレビュー |
| Java | [MSAL4J](https://github.com/AzureAD/microsoft-authentication-library-for-java) | [msal4j](https://mvnrepository.com/artifact/com.microsoft.azure/msal4j) | — | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| macOS (Swift/Obj-C) | [iOS および macOS 用の MSAL](https://github.com/AzureAD/microsoft-authentication-library-for-objc) | [MSAL](https://cocoapods.org/pods/MSAL) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-ios) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| UWP | [MSAL.NET](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet) | [Microsoft.Identity.Client](https://www.nuget.org/packages/Microsoft.Identity.Client) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-windows-uwp) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| WPF | [MSAL.NET](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet) | [Microsoft.Identity.Client](https://www.nuget.org/packages/Microsoft.Identity.Client) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-windows-desktop) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |

^1^[オンライン サービスのユニバーサル ライセンス条項](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)は、"パブリック プレビュー" のライブラリに適用されます。

### パブリック クライアント アプリケーション

コードの観点から、デスクトップ アプリケーションは、パブリック クライアント アプリケーションです。 対話型認証を使用するかどうかによって、構成は少し異なります。

## [.NET](#tab/dotnet)
MSAL.NET `IPublicClientApplication` を作成して操作する必要があります。

[Image: IPublicClientApplication]

#### コードで排他的に

以下のコードでは、パブリック クライアント アプリケーションをインスタンス化して、職場または学校アカウントあるいは個人用 Microsoft アカウントを使用して、Microsoft Azure のパブリック クラウドにユーザーをサインインさせます。

```csharp
IPublicClientApplication app = PublicClientApplicationBuilder.Create(clientId)
    .Build();
```

前述のように、対話型認証またはデバイス コード フローを使用する場合は、`.WithRedirectUri` 修飾子を使用します。

```csharp
IPublicClientApplication app;
app = PublicClientApplicationBuilder.Create(clientId)
        .WithDefaultRedirectUri()
        .Build();
```

#### 構成ファイルを使用する

次のコードは、(プログラムで入力、または構成ファイルから読み取られる) 構成オブジェクトからパブリック クライアント アプリケーションをインスタンス化します。

```csharp
PublicClientApplicationOptions options = GetOptions(); // your own method
IPublicClientApplication app = PublicClientApplicationBuilder.CreateWithApplicationOptions(options)
        .WithDefaultRedirectUri()
        .Build();
```

#### より詳細な構成

多数の修飾子を追加して、アプリケーションの構築を細かく設定できます。 たとえば、お使いのアプリケーションを各国のクラウド (ここで示した米国政府など) のマルチテナント アプリケーションにする場合、次のように記述できます。

```csharp
IPublicClientApplication app;
app = PublicClientApplicationBuilder.Create(clientId)
        .WithDefaultRedirectUri()
        .WithAadAuthority(AzureCloudInstance.AzureUsGovernment,
                         AadAuthorityAudience.AzureAdMultipleOrgs)
        .Build();
```

MSAL.NET には、Active Directory フェデレーション サービス (AD FS) 2019 の修飾子も含まれています。

```csharp
IPublicClientApplication app;
app = PublicClientApplicationBuilder.Create(clientId)
        .WithAdfsAuthority("https://consoso.com/adfs")
        .Build();
```

最後に、Azure Active Directory (Azure AD) B2C テナントのトークンを取得する場合は、次のコード スニペットで示されているとおり、お使いのテナントを指定します。

```csharp
IPublicClientApplication app;
app = PublicClientApplicationBuilder.Create(clientId)
        .WithB2CAuthority("https://fabrikamb2c.b2clogin.com/tfp/{tenant}/{PolicySignInSignUp}")
        .Build();
```

#### 詳細情報

MSAL.NET デスクトップ アプリケーションを構成する方法の詳細については、以下のとおりです。

- `PublicClientApplicationBuilder` で使用可能なすべての修飾子の一覧については、リファレンス ドキュメント [PublicClientApplicationBuilder](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplicationbuilder#methods) を参照してください。
- `PublicClientApplicationOptions` に公開されているすべてのオプションの説明については、リファレンス ドキュメント内の [PublicClientApplicationOptions](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplicationoptions) を参照してください。

#### 構成オプションを使用した詳細な例

次のような `appsettings.json` 構成ファイルを使用する .NET コンソール アプリケーションについて考えます。

```json
{
  "Authentication": {
    "AzureCloudInstance": "AzurePublic",
    "AadAuthorityAudience": "AzureAdMultipleOrgs",
    "ClientId": "00001111-aaaa-2222-bbbb-3333cccc4444"
  },

  "WebAPI": {
    "MicrosoftGraphBaseEndpoint": "https://graph.microsoft.com"
  }
}
```

.NET で提供される構成フレームワークを使用して、このファイル内で読み取るコードはほとんどありません。

```csharp
public class SampleConfiguration
{
 /// <summary>
 /// Authentication options
 /// </summary>
 public PublicClientApplicationOptions PublicClientApplicationOptions { get; set; }

 /// <summary>
 /// Base URL for Microsoft Graph (it varies depending on whether the application runs
 /// in Microsoft Azure public clouds or national or sovereign clouds)
 /// </summary>
 public string MicrosoftGraphBaseEndpoint { get; set; }

 /// <summary>
 /// Reads the configuration from a JSON file
 /// </summary>
 /// <param name="path">Path to the configuration json file</param>
 /// <returns>SampleConfiguration as read from the json file</returns>
 public static SampleConfiguration ReadFromJsonFile(string path)
 {
  // .NET configuration
  IConfigurationRoot Configuration;
  var builder = new ConfigurationBuilder()
                    .SetBasePath(Directory.GetCurrentDirectory())
                    .AddJsonFile(path);
  Configuration = builder.Build();

  // Read the auth and graph endpoint configuration
  SampleConfiguration config = new SampleConfiguration()
  {
   PublicClientApplicationOptions = new PublicClientApplicationOptions()
  };
  Configuration.Bind("Authentication", config.PublicClientApplicationOptions);
  config.MicrosoftGraphBaseEndpoint =
  Configuration.GetValue<string>("WebAPI:MicrosoftGraphBaseEndpoint");
  return config;
 }
}
```

ここで、アプリケーションを作成するために、次のコードを記述します。

```csharp
SampleConfiguration config = SampleConfiguration.ReadFromJsonFile("appsettings.json");
var app = PublicClientApplicationBuilder.CreateWithApplicationOptions(config.PublicClientApplicationOptions)
           .WithDefaultRedirectUri()
           .Build();
```

`.Build()` メソッドを呼び出す前に、前述のように `.WithXXX` メソッドを呼び出すことで構成を上書きできます。

## [Java](#tab/java)
MSAL Java 開発サンプルでその構成に使用されているクラスを次に示します。[TestData](https://github.com/AzureAD/microsoft-authentication-library-for-java/tree/dev/msal4j-sdk/src/samples/public-client/)。

```Java
PublicClientApplication pca = PublicClientApplication.builder(CLIENT_ID)
        .authority(AUTHORITY)
        .build();
```

## [MacOS](#tab/macOS)
以下のコードでは、パブリック クライアント アプリケーションをインスタンス化して、職場または学校アカウントあるいは個人用 Microsoft アカウントを使用して、Microsoft Azure のパブリック クラウドにユーザーをサインインさせます。

#### クイック構成

Objective-C:

```objc
NSError *msalError = nil;

MSALPublicClientApplicationConfig *config = [[MSALPublicClientApplicationConfig alloc] initWithClientId:@"<your-client-id-here>"];
MSALPublicClientApplication *application = [[MSALPublicClientApplication alloc] initWithConfiguration:config error:&msalError];
```

Swift:

```swift
let config = MSALPublicClientApplicationConfig(clientId: "<your-client-id-here>")
if let application = try? MSALPublicClientApplication(configuration: config){ /* Use application */}
```

#### より詳細な構成

多数の修飾子を追加して、アプリケーションの構築を細かく設定できます。 たとえば、お使いのアプリケーションを各国のクラウド (ここで示した米国政府など) のマルチテナント アプリケーションにする場合、次のように記述できます。

Objective-C:

```objc
MSALAADAuthority *aadAuthority =
                [[MSALAADAuthority alloc] initWithCloudInstance:MSALAzureUsGovernmentCloudInstance
                                                   audienceType:MSALAzureADMultipleOrgsAudience
                                                      rawTenant:nil
                                                          error:nil];

MSALPublicClientApplicationConfig *config =
                [[MSALPublicClientApplicationConfig alloc] initWithClientId:@"<your-client-id-here>"
                                                                redirectUri:@"<your-redirect-uri-here>"
                                                                  authority:aadAuthority];

NSError *applicationError = nil;
MSALPublicClientApplication *application =
                [[MSALPublicClientApplication alloc] initWithConfiguration:config error:&applicationError];
```

Swift:

```swift
let authority = try? MSALAADAuthority(cloudInstance: .usGovernmentCloudInstance, audienceType: .azureADMultipleOrgsAudience, rawTenant: nil)

let config = MSALPublicClientApplicationConfig(clientId: "<your-client-id-here>", redirectUri: "<your-redirect-uri-here>", authority: authority)
if let application = try? MSALPublicClientApplication(configuration: config) { /* Use application */}
```

## [Node.js](#tab/nodejs)
構成パラメーターは、JavaScript ファイルなどの多くのソースから、または環境変数から読み込むことができます。 以下では、*authConfig.js* ファイルが使われます。

```js
/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

const { LogLevel } = require("@azure/msal-node");

/**
 * Configuration object to be passed to MSAL instance on creation.
 * For a full list of MSAL.js configuration parameters, visit:
 * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/configuration.md
 */
const AAD_ENDPOINT_HOST = "Enter_the_Cloud_Instance_Id_Here"; // include the trailing slash

const msalConfig = {
    auth: {
        clientId: "Enter_the_Application_Id_Here",
        authority: `${AAD_ENDPOINT_HOST}Enter_the_Tenant_Info_Here`,
    },
    system: {
        loggerOptions: {
            loggerCallback(loglevel, message, containsPii) {
                console.log(message);
            },
            piiLoggingEnabled: false,
            logLevel: LogLevel.Verbose,
        },
    },
};

/**
 * Add here the endpoints and scopes when obtaining an access token for protected web APIs. For more information, see:
 * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/resources-and-scopes.md
 */
const GRAPH_ENDPOINT_HOST = "Enter_the_Graph_Endpoint_Here"; // include the trailing slash

const protectedResources = {
    graphMe: {
        endpoint: `${GRAPH_ENDPOINT_HOST}v1.0/me`,
        scopes: ["User.Read"],
    }
};

module.exports = {
    msalConfig: msalConfig,
    protectedResources: protectedResources,
};
```

*authConfig.js* ファイルから構成オブジェクトをインポートします。 MSAL Node は、下のように最小限に初期化できます。 使用できる[構成オプション](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/configuration.md)を参照してください。

```JavaScript
const { PublicClientApplication } = require('@azure/msal-node');
const { msalConfig } = require('./authConfig')

/**
* Initialize a public client application. For more information, visit:
* https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/initialize-public-client-application.md
*/
clientApplication = new PublicClientApplication(msalConfig);
```

## [Python](#tab/python)
```Python
config = json.load(open(sys.argv[1]))

app = msal.PublicClientApplication(
    config["client_id"], authority=config["authority"],
    # token_cache=...  # Default cache is in memory only.
                       # You can learn how to use SerializableTokenCache from
                       # https://msal-python.rtfd.io/en/latest/#msal.SerializableTokenCache
    )
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-desktop-call-api"} -->
## デスクトップ アプリから Web API を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-call-api
- Service: identity-platform / workforce
- Article date: 2019-10-30
- Summary: Web API を呼び出すデスクトップ アプリを構築する方法について説明します

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

トークンを取得すると、保護された Web API を呼び出せます。

### Web API を呼び出す

## [.NET](#tab/dotnet)
#### MSAL.NET の AuthenticationResult プロパティ

トークンを取得するメソッドでは、`AuthenticationResult` が返されます。 非同期メソッドでは、`Task<AuthenticationResult>` が返されます。

MSAL.NET では、`AuthenticationResult` は次を公開します。

- Web API がリソースにアクセスするための `AccessToken`。 このパラメーターは、通常は base 64 でエンコードされた JWT の文字列です。 クライアントがアクセス トークン内を見ることはありません。 この形式が変わらないことは保証されておらず、リソース用に暗号化できます。 クライアント上のアクセス トークンのコンテンツに応じてコードを記述することは、エラーとクライアント ロジックの中断を起こす最大の原因の 1 つです。 詳細については、「[アクセス トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)」を参照してください。
- ユーザー向け `IdToken`。 このパラメーターは、エンコードされた JWT です。 詳細については、[ID トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens)に関するページを参照してください。
- トークンの有効期限の日時は、`ExpiresOn` から知ることができます。
- `TenantId` には、ユーザーが存在するテナントが含まれています。 ゲスト ユーザー (Microsoft Entra B2B のシナリオ) の場合、テナント ID は一意のテナントではなく、ゲスト テナントです。 ユーザーにトークンが配信されるときに、`AuthenticationResult` にはこのユーザーに関する情報も含まれます。 アプリケーションのユーザーなしでトークンが要求される機密のクライアント フローの場合、このユーザー情報は null です。
- トークンが発行された `Scopes`。
- ユーザーの一意の ID。

#### IAccount

MSAL.NET では、`IAccount` インターフェイスでアカウントの概念が定義されます。 この破壊的変更により、正しいセマンティクスが得られます。 同じユーザーは、異なる Microsoft Entra ディレクトリに複数のアカウントを持つことができます。 また、MSAL.NET では、ホーム アカウント情報が提供されるので、ゲスト シナリオについてはより詳細な情報が提供されます。 次の図は、`IAccount` インターフェイスの構造を示しています。

[Image: IAccount インターフェイスの構造]

`AccountId` クラスでは、次の表のプロパティで、特定のテナントのアカウントを識別します。

| プロパティ | 説明 |
| --- | --- |
| `TenantId` | GUID (アカウントが存在するテナントの ID) の文字列表現。 |
| `ObjectId` | GUID (テナント内でアカウントを所有するユーザーの ID) の文字列表現。 |
| `Identifier` | アカウントの一意識別子。 `Identifier` は `ObjectId` と `TenantId` をコンマで区切って連結したものです。 base 64 ではエンコードされていません。 |

`IAccount` インターフェイスは 1 つのアカウントに関する情報を表します。 同じユーザーが異なるテナントに存在することができます。つまり、1 人のユーザーが複数のアカウントを持つことができます。 そのメンバーは、次の表のとおりです。

| プロパティ | 説明 |
| --- | --- |
| `Username` | UserPrincipalName (UPN) 形式の表示可能な値の文字列 (例: john.doe@contoso.com)。 null にすることができない HomeAccountId と HomeAccountId.Identifier とは異なり、この文字列は null にできます。 このプロパティは、MSAL.NET の以前のバージョンの `DisplayableId` の `IUser` プロパティを置き換えます。 |
| `Environment` | このアカウントの ID プロバイダーを含む文字列 (例: `login.microsoftonline.com`)。 `IdentityProvider` には、クラウド環境に加えテナントに関する情報もありますが、それを除き、このプロパティは、`IUser` の `IdentityProvider` プロパティと置き換えることができます。 ここでは値はホストのみです。 |
| `HomeAccountId` | ユーザーのホーム アカウントのアカウント ID。 このプロパティは、Microsoft Entra テナント全体でユーザーを一意に識別します。 |

#### トークンを使用して保護された API を呼び出す

MSAL によって `AuthenticationResult` に `result` が返された後、保護された Web API にアクセスする呼び出しを行う前に、これを HTTP Authorization ヘッダーに追加します。

```csharp
httpClient = new HttpClient();
httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", result.AccessToken);

// Call the web API.
HttpResponseMessage response = await _httpClient.GetAsync(apiUri);
...

```

## [ジャワ](#tab/java)
```Java
HttpURLConnection conn = (HttpURLConnection) url.openConnection();

PublicClientApplication pca = PublicClientApplication.builder(clientId)
        .authority(authority)
        .build();

// Acquire a token, acquireTokenHelper would call publicClientApplication's acquireTokenSilently then acquireToken
// see https://github.com/Azure-Samples/ms-identity-java-desktop for a full example
IAuthenticationResult authenticationResult = acquireTokenHelper(pca);

// Set the appropriate header fields in the request header.
conn.setRequestProperty("Authorization", "Bearer " + authenticationResult.accessToken);
conn.setRequestProperty("Accept", "application/json");

String response = HttpClientHelper.getResponseStringFromConn(conn);

int responseCode = conn.getResponseCode();
if(responseCode != HttpURLConnection.HTTP_OK) {
    throw new IOException(response);
}

JSONObject responseObject = HttpClientHelper.processResponse(responseCode, response);
```

## [MacOS](#tab/macOS)
### iOS および macOS 用の MSAL での Web API の呼び出し

トークンを取得するメソッドは `MSALResult` オブジェクトを返します。 `MSALResult` により、Web API を呼び出すために使用できる `accessToken` プロパティが公開されます。 保護された Web API にアクセスするための呼び出しを行う前に、アクセス トークンを HTTP Authorization ヘッダーに追加してください。

Objective-C:

```objc
NSMutableURLRequest *urlRequest = [NSMutableURLRequest new];
urlRequest.URL = [NSURL URLWithString:"https://contoso.api.com"];
urlRequest.HTTPMethod = @"GET";
urlRequest.allHTTPHeaderFields = @{ @"Authorization" : [NSString stringWithFormat:@"Bearer %@", accessToken] };

NSURLSessionDataTask *task =
[[NSURLSession sharedSession] dataTaskWithRequest:urlRequest
     completionHandler:^(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error) {}];
[task resume];
```

Swift:

```swift
let urlRequest = NSMutableURLRequest()
urlRequest.url = URL(string: "https://contoso.api.com")!
urlRequest.httpMethod = "GET"
urlRequest.allHTTPHeaderFields = [ "Authorization" : "Bearer \(accessToken)" ]

let task = URLSession.shared.dataTask(with: urlRequest as URLRequest) { (data: Data?, response: URLResponse?, error: Error?) in }
task.resume()
```

### 複数の API の呼び出し:増分同意と条件付きアクセス

同じユーザーに対して複数の API を呼び出すには、最初の API のトークンを取得した後、`AcquireTokenSilent` を呼び出します。 その他の API のトークンは、ほとんどの場合、自動的に取得されます。

```csharp
var result = await app.AcquireTokenXX("scopeApi1")
                      .ExecuteAsync();

result = await app.AcquireTokenSilent("scopeApi2")
                  .ExecuteAsync();
```

対話は、次の場合に必要です。

- ユーザーが最初の API については同意したが、より多くのスコープについて同意する必要が生じた。 この種の同意は、増分同意 と呼ばれます。
- 最初の API は多要素認証を必要としなかったが、次の API は必要とする。

```csharp
var result = await app.AcquireTokenXX("scopeApi1")
                      .ExecuteAsync();

try
{
 result = await app.AcquireTokenSilent("scopeApi2")
                  .ExecuteAsync();
}
catch(MsalUiRequiredException ex)
{
 result = await app.AcquireTokenInteractive("scopeApi2")
                  .WithClaims(ex.Claims)
                  .ExecuteAsync();
}
```

## [Node.js](#tab/nodejs)
[Axios](https://www.npmjs.com/package/axios)などの HTTP クライアントを使用し、"*認可ベアラー*" としてアクセス トークンを使用して API エンドポイント URI を呼び出します。

```javascript
const axios = require('axios');

async function callEndpointWithToken(endpoint, accessToken) {
    const options = {
        headers: {
            Authorization: `Bearer ${accessToken}`
        }
    };

    console.log('Request made at: ' + new Date().toString());

    const response = await axios.default.get(endpoint, options);

    return response.data;
}

```

## [Python](#tab/python)
```Python
endpoint = "url to the API"
http_headers = {'Authorization': 'Bearer ' + result['access_token'],
                'Accept': 'application/json',
                'Content-Type': 'application/json'}
data = requests.get(endpoint, headers=http_headers, stream=False).json()
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-mobile-acquire-token"} -->
## Web API を呼び出すトークンを取得する (モバイル アプリ) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-mobile-acquire-token
- Service: identity-platform / workforce
- Article date: 2019-05-07
- Summary: Web API を呼び出すモバイル アプリを構築する方法について説明します。 (アプリのトークンを取得します。)

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

保護された Web API をアプリから呼び出すには、アクセス トークンが必要です。 この記事では、Microsoft Authentication Library (MSAL) を使用してトークンを取得するプロセスについて説明します。

### スコープの定義

トークンを要求するときは、スコープを定義します。 スコープでは、お使いのアプリでアクセスできるデータを決定します。

スコープを定義する最も簡単な方法は、必要な Web API `App ID URI` をスコープ `.default` と組み合わせることです。 この定義により、アプリで、ポータルに設定されているすべてのスコープが必要であることを Microsoft ID プラットフォームに通知できます。

#### Android

```Java
String[] SCOPES = {"https://graph.microsoft.com/.default"};
```

#### iOS

```swift
let scopes = ["https://graph.microsoft.com/.default"]
```

### トークンを取得する

#### MSAL によるトークンの取得

MSAL により、アプリはサイレントかつ対話的にトークンを取得できます。 `AcquireTokenSilent()` または `AcquireTokenInteractive()` を呼び出すと、要求されたスコープのアクセス トークンが MSAL から返されます。 正しいパターンは、サイレント要求を行ってから、対話型の要求にフォールバックすることです。

##### Android

```Java
String[] SCOPES = {"https://graph.microsoft.com/.default"};
PublicClientApplication sampleApp = new PublicClientApplication(
                    this.getApplicationContext(),
                    R.raw.auth_config);

// Check if there are any accounts we can sign in silently.
// Result is in the silent callback (success or error).
sampleApp.getAccounts(new PublicClientApplication.AccountsLoadedCallback() {
    @Override
    public void onAccountsLoaded(final List<IAccount> accounts) {

        if (!accounts.isEmpty() && accounts.size() == 1) {
            // One account found, attempt silent sign-in.
            sampleApp.acquireTokenSilentAsync(SCOPES, accounts.get(0), getAuthSilentCallback());
        } else if (accounts.isEmpty()) {
            // No accounts found. Interactively request a token.
            sampleApp.acquireToken(getActivity(), SCOPES, getAuthInteractiveCallback());
        } else {
            // Multiple accounts found. Handle according to your app logic.
            // You may need to prompt the user to select an account.
        }
    }
});

[...]

// No accounts found. Interactively request a token.
// TODO: Create an interactive callback to catch successful or failed requests.
sampleApp.acquireToken(getActivity(), SCOPES, getAuthInteractiveCallback());
```

##### iOS

まず、トークンをサイレント モードで取得します。

```objc

NSArray *scopes = @[@"https://graph.microsoft.com/.default"];
NSString *accountIdentifier = @"my.account.id";

MSALAccount *account = [application accountForIdentifier:accountIdentifier error:nil];

MSALSilentTokenParameters *silentParams = [[MSALSilentTokenParameters alloc] initWithScopes:scopes account:account];
[application acquireTokenSilentWithParameters:silentParams completionBlock:^(MSALResult *result, NSError *error) {

    if (!error)
    {
        // You'll want to get the account identifier to retrieve and reuse the account
        // for later acquireToken calls
        NSString *accountIdentifier = result.account.identifier;

        // Access token to call the web API
        NSString *accessToken = result.accessToken;
    }

    // Check the error
    if (error && [error.domain isEqual:MSALErrorDomain] && error.code == MSALErrorInteractionRequired)
    {
        // Interactive auth will be required, call acquireTokenWithParameters:error:
        return;
    }
}];
```

```swift

let scopes = ["https://graph.microsoft.com/.default"]
let accountIdentifier = "my.account.id"

guard let account = try? application.account(forIdentifier: accountIdentifier) else { return }
let silentParameters = MSALSilentTokenParameters(scopes: scopes, account: account)
application.acquireTokenSilent(with: silentParameters) { (result, error) in

    guard let authResult = result, error == nil else {

    let nsError = error! as NSError

    if (nsError.domain == MSALErrorDomain &&
        nsError.code == MSALError.interactionRequired.rawValue) {

            // Interactive auth will be required, call acquireToken()
            return
         }
         return
     }

    // You'll want to get the account identifier to retrieve and reuse the account
    // for later acquireToken calls
    let accountIdentifier = authResult.account.identifier

    // Access token to call the web API
    let accessToken = authResult.accessToken
}
```

MSAL から `MSALErrorInteractionRequired` が返された場合は、トークンを対話形式で取得します。

```objc
UIViewController *viewController = ...; // Pass a reference to the view controller that should be used when getting a token interactively
MSALWebviewParameters *webParameters = [[MSALWebviewParameters alloc] initWithAuthPresentationViewController:viewController];
MSALInteractiveTokenParameters *interactiveParams = [[MSALInteractiveTokenParameters alloc] initWithScopes:scopes webviewParameters:webParameters];
[application acquireTokenWithParameters:interactiveParams completionBlock:^(MSALResult *result, NSError *error) {
    if (!error)
    {
        // You'll want to get the account identifier to retrieve and reuse the account
        // for later acquireToken calls
        NSString *accountIdentifier = result.account.identifier;

        NSString *accessToken = result.accessToken;
    }
}];
```

```swift
let viewController = ... // Pass a reference to the view controller that should be used when getting a token interactively
let webviewParameters = MSALWebviewParameters(authPresentationViewController: viewController)
let interactiveParameters = MSALInteractiveTokenParameters(scopes: scopes, webviewParameters: webviewParameters)
application.acquireToken(with: interactiveParameters, completionBlock: { (result, error) in

    guard let authResult = result, error == nil else {
        print(error!.localizedDescription)
        return
    }

    // Get access token from result
    let accessToken = authResult.accessToken
})
```

MSAL for iOS と macOS では、トークンを対話形式またはサイレント モードで取得するためのさまざまな修飾子がサポートされています。

- [トークンを取得するための共通パラメーター](https://azuread.github.io/microsoft-authentication-library-for-objc/Classes/MSALTokenParameters.html#/Configuration%20parameters)
- [対話型トークンを取得するためのパラメーター](https://azuread.github.io/microsoft-authentication-library-for-objc/Classes/MSALInteractiveTokenParameters.html#/Configuring%20MSALInteractiveTokenParameters)
- [サイレント トークンを取得するための共通パラメーター](https://azuread.github.io/microsoft-authentication-library-for-objc/Classes/MSALSilentTokenParameters.html)

##### MSAL.NET の必須のパラメーター

`AcquireTokenInteractive` の必須パラメーターは `scopes`の 1 つだけです。 `scopes` パラメーターで、トークンが必要なスコープを定義する文字列を列挙します。 Microsoft Graph 用のトークンの場合、必要なスコープは各 Microsoft Graph API の API リファレンスで見つけることができます。 リファレンスの「アクセス許可」セクションにアクセスします。

たとえば、[ユーザーの連絡先を一覧表示する](https://learn.microsoft.com/ja-jp/graph/api/user-list-contacts)には、"User.Read"、"Contacts.Read" スコープを使用します。 詳細については、「[Microsoft Graph のアクセス許可のリファレンス](https://learn.microsoft.com/ja-jp/graph/permissions-reference)」を参照してください。

Android では、`PublicClientApplicationBuilder` を使用してアプリの作成時に親アクティビティを指定できます。 この時点で親アクティビティを指定しない場合は、次のセクションのように、`.WithParentActivityOrWindow` を使用して後で指定できます。 親アクティビティを指定した場合は、操作後にトークンがその親アクティビティに通知されます。 指定しない場合は、`.ExecuteAsync()` を呼び出して例外をスローします。

##### MSAL.NET の特定の省略可能なパラメーター

以下のセクションでは、MSAL.NET の省略可能なパラメーターについて説明します。

###### WithPrompt

`WithPrompt()` パラメーターでは、プロンプトを指定してユーザーとのインタラクティビティを制御します。

[Image: プロンプト構造内のフィールドを示している画像。これらの定数値は、WithPrompt() パラメーターによって表示されるプロンプトの種類を定義することでユーザーとのインタラクティビティを制御します。]

このクラスでは次の定数を定義します。

- `SelectAccount` は、セキュリティ トークン サービス (STS) で、アカウントの選択ダイアログ ボックスを強制的に表示します。 ダイアログ ボックスには、ユーザーがセッションを持っているアカウントが表示されます。 このオプションは、ユーザーが異なる ID を選択できるようにする場合に使用できます。 このオプションを使用すると、MSAL から ID プロバイダーに `prompt=select_account` が送信されます。

    `SelectAccount` 定数は既定値であり、使用可能な情報に基づいて、考えられる最善のエクスペリエンスが効果的に提供されます。 使用可能な情報には、アカウントや、ユーザーのセッションの有無などが含まれます。 適切な理由がない限り、この既定値を変更しないでください。
- `Consent` を使用すると、事前に同意が得られていた場合でも、ユーザーに同意を求めることができます。 この場合、MSAL から ID プロバイダーに `prompt=consent` が送信されます。

    `Consent` 定数は、組織のガバナンスにより、ユーザーがアプリケーションを使用するたびに同意ダイアログ ボックスを表示することが求められるセキュリティ重視のアプリケーションで使用できます。
- `ForceLogin` を使用すると、プロンプトが必須ではない場合でも、ユーザーに資格情報の入力を求めるサービスを実現できます。

    このオプションは、トークンの取得に失敗し、ユーザーが再度サインインできるようにする場合に役立ちます。 この場合、MSAL から ID プロバイダーに `prompt=login` が送信されます。 このオプションは、組織のガバナンスにより、ユーザーがアプリケーションの特定の部分にアクセスするたびにサインインすることが求められるセキュリティ重視のアプリケーションで使用できます。
- `Never` は、.NET 4.5 と Windows ランタイム (WinRT) のみに使用できます。 この定数を使用すると、ユーザーの操作を求めず、非表示の埋め込み Web ビューに格納された Cookie を使用しようとします。 詳細については、「[Web ブラウザーを使用する (MSAL.NET)](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/using-web-browsers)」を参照してください。

    このオプションが失敗した場合は、`AcquireTokenInteractive` によって、UI 操作が必要であることを通知する例外がスローされます。 次に、別の `Prompt` パラメーターが使用されます。
- `NoPrompt` を使用すると、ID プロバイダーにプロンプトが送信されません。

    このオプションは、Azure Active Directory (Azure AD) B2C のプロファイルの編集ポリシーに対してのみ有効です。 詳細については、[B2C の詳細](https://aka.ms/msal-net-b2c-specificities)に関するページを参照してください。

###### WithExtraScopeToConsent

複数のリソースに対してユーザーの事前の同意を求める高度なシナリオでは、`WithExtraScopeToConsent` 修飾子を使用します。 この修飾子は、MSAL.NET または Microsoft ID プラットフォームで通常使用される増分同意を使用しない場合に使用できます。 詳細については、「[複数のリソースでユーザーの同意を事前に取得する](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-production#have-the-user-consent-upfront-for-several-resources)」を参照してください。

次にコード例を示します。

```csharp
var result = await app.AcquireTokenInteractive(scopesForCustomerApi)
                     .WithExtraScopeToConsent(scopesForVendorApi)
                     .ExecuteAsync();
```

###### その他の省略可能なパラメーター

`AcquireTokenInteractive` のその他の省略可能なパラメーターの詳細については、[AcquireTokenInteractiveParameterBuilder の参照ドキュメント](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokeninteractiveparameterbuilder#methods)をご覧ください。

#### プロトコルを使用してトークンを取得する

プロトコルを直接使用してトークンを取得することはお勧めしません。 これを行うと、シングルサインオン (SSO)、デバイス管理、条件付きアクセスが含まれるいくつかのシナリオがアプリでサポートされなくなります。

プロトコルを使用してモバイル アプリのトークンを取得する場合は、次の 2 つの要求を行います。

- 承認コードを取得します。
- トークンのコードを交換します。

##### 承認コードを取得する

```
https://login.microsoftonline.com/{tenant}/oauth2/v2.0/authorize?
client_id=<CLIENT_ID>
&response_type=code
&redirect_uri=<ENCODED_REDIRECT_URI>
&response_mode=query
&scope=openid%20offline_access%20https%3A%2F%2Fgraph.microsoft.com%2F.default
&state=12345
```

##### アクセスを取得してトークンを更新する

```HTTP
POST /{tenant}/oauth2/v2.0/token HTTP/1.1
Host: https://login.microsoftonline.com
Content-Type: application/x-www-form-urlencoded

client_id=<CLIENT_ID>
&scope=https%3A%2F%2Fgraph.microsoft.com%2F.default
&code=OAAABAAAAiL9Kn2Z27UubvWFPbm0gLWQJVzCTE9UkP3pSx1aXxUjq3n8b2JRLk4OxVXr...
&redirect_uri=<ENCODED_REDIRECT_URI>
&grant_type=authorization_code
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-mobile-app-configuration"} -->
## Web API を呼び出すモバイル アプリを構成する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-mobile-app-configuration
- Service: identity-platform / workforce
- Article date: 2025-03-19
- Summary: Web API を呼び出すようにモバイル アプリのコードを構成する方法について説明します

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

アプリケーションを作成した後、アプリ登録パラメーターを使用してコードを構成する方法を確認します。 モバイル アプリケーションでは、作成フレームワークへの適合に関連するいくつかの複雑さが伴います。

### [前提条件]

- アクティブなサブスクリプションを持つ Azure アカウント。 [無料でアカウントを作成できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。 このアカウントには、アプリケーションを管理するためのアクセス許可が必要です。 アプリケーションを登録するために必要な次のロールのいずれかを使用します。
    - アプリケーション管理者
    - アプリケーション開発者
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID

### プラットフォーム リダイレクト URI を追加する

アプリの登録にアプリの種類を指定するには、次の手順に従います。

1. **[管理]** で、 **[認証]**&gt;**[プラットフォームの追加]**&gt;**[iOS/macOS]** の順に選択します。
2. バンドル ID を入力した後、 **[構成]** を選択します。 リダイレクト URI が計算されます。

リダイレクト URI を手動で構成する場合は、アプリケーション マニフェストを介して行えます。 マニフェストの推奨される形式は次のとおりです。

- **iOSの場合**: `msauth.<BUNDLE_ID>://auth`
    - たとえば、「`msauth.com.yourcompany.appName://auth`」と入力します。
- **アンドロイドの場合**: `msauth://<PACKAGE_NAME>/<SIGNATURE_HASH>`
    - Android の署名のハッシュは、KeyTool コマンドを通じて、リリース キーまたはデバッグ キーを使用して生成できます。

### パブリック クライアント フローを有効にする

アプリでユーザー名とパスワード認証のみを使用する場合は、アプリケーションのリダイレクト URI を登録する必要はありません。 このフローによって、Microsoft ID プラットフォームへのラウンド トリップが実行されます。 アプリケーションが特定の URI でコールバックされることはありません。 ただし、パブリック クライアント フローを有効にする必要があります。

アプリをパブリック クライアントとして識別するには、次の手順に従います。

1. **[管理]** で、 **[認証]** を選択します。
2. **[詳細設定]** で、**[パブリック クライアント フローを許可する]** に対して **[はい]** を選択します。
3. **[保存]** を選択して変更を保存します。

### モバイル アプリをサポートする Microsoft ライブラリ

次の Microsoft ライブラリはモバイル アプリをサポートしています。

| プラットフォーム | プロジェクトGitHub | Package | 取得started | ユーザーのサインイン | Web API へのアクセス | 一般提供 (GA)*または*パブリック プレビュー^1^ |
| --- | --- | --- | --- | --- | --- | --- |
| Android (Java) | [MSAL アンドロイド](https://github.com/AzureAD/microsoft-authentication-library-for-android) | [MSAL](https://mvnrepository.com/artifact/com.microsoft.identity.client/msal) | [クイック スタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-android-sign-in) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| Android (Kotlin) | [MSAL アンドロイド](https://github.com/AzureAD/microsoft-authentication-library-for-android) | [MSAL](https://mvnrepository.com/artifact/com.microsoft.identity.client/msal) | — | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| iOS (Swift/Obj-C) | [iOS および macOS 用の MSAL](https://github.com/AzureAD/microsoft-authentication-library-for-objc) | [MSAL](https://cocoapods.org/pods/MSAL) | [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-ios) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |

^1^[オンライン サービスのユニバーサル ライセンス条項](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)は、*パブリック プレビュー*のライブラリに適用されます。

### アプリケーションをインスタンス化する

#### Android

モバイル アプリケーションでは `PublicClientApplication` クラスが使用されます。 これをインスタンス化する方法を次に示します。

```Java
PublicClientApplication sampleApp = new PublicClientApplication(
                    this.getApplicationContext(),
                    R.raw.auth_config);
```

#### iOS

iOS 上のモバイル アプリケーションは、`MSALPublicClientApplication` クラスをインスタンス化する必要があります。 このクラスをインスタンス化するには、次のコードを使用します。

```objc
NSError *msalError = nil;

MSALPublicClientApplicationConfig *config = [[MSALPublicClientApplicationConfig alloc] initWithClientId:@"<your-client-id-here>"];
MSALPublicClientApplication *application = [[MSALPublicClientApplication alloc] initWithConfiguration:config error:&msalError];
```

```swift
let config = MSALPublicClientApplicationConfig(clientId: "<your-client-id-here>")
if let application = try? MSALPublicClientApplication(configuration: config){ /* Use application */}
```

[追加の MSALPublicClientApplicationConfig プロパティ](https://azuread.github.io/microsoft-authentication-library-for-objc/Classes/MSALPublicClientApplicationConfig.html#/Configuration%20options)で、既定の機関のオーバーライド、リダイレクト URI の指定、または MSAL トークンのキャッシュ動作の変更を行うことができます。

#### UWP

このセクションでは、UWP アプリのアプリケーションをインスタンス化する方法について説明します。

##### アプリケーションをインスタンス化する

UWP では、アプリケーションをインスタンス化する最も簡単な方法は、次のコードを使用することです。 このコードの `ClientId` は、登録済みアプリの GUID です。

```csharp
var app = PublicClientApplicationBuilder.Create(clientId)
                                        .Build();
```

追加の `With<Parameter>` メソッドを使用して、親 UI の設定、既定の機関のオーバーライド、クライアント名とバージョンの指定 (テレメトリ用)、リダイレクト URI の指定、および使用する HTTP ファクトリの指定を実行します。 たとえば、HTTP ファクトリを使用して、プロキシの処理とテレメトリとログの指定を行います。

次のセクションで、アプリケーションのインスタンス化について詳しく説明します。

###### 親 UI、ウィンドウ、またはアクティビティを指定する

Android では、対話型認証を行う前に親アクティビティを渡します。 iOS でブローカーを使用する場合は、`ViewController` を渡します。 UWP の場合と同じように、親ウィンドウを渡すことができます。 トークンを取得するときに、それを渡します。 ただし、アプリを作成するときに、コールバックを `UIParent` を返すデリゲートとして指定することもできます。

```csharp
IPublicClientApplication application = PublicClientApplicationBuilder.Create(clientId)
  .ParentActivityOrWindowFunc(() => parentUi)
  .Build();
```

Android では、[`CurrentActivityPlugin`](https://github.com/jamesmontemagno/CurrentActivityPlugin) の使用をお勧めします。 結果の `PublicClientApplication` ビルダー コードは、次の例のようになります。

```csharp
// Requires MSAL.NET 4.2 or above
var pca = PublicClientApplicationBuilder
  .Create("<your-client-id-here>")
  .WithParentActivityOrWindow(() => CrossCurrentActivity.Current)
  .Build();
```

###### 他のアプリ ビルド パラメーターを見つける

`PublicClientApplicationBuilder` で使用できるすべてのメソッドの一覧については、[メソッドの一覧](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplicationbuilder#methods)を参照してください。

`PublicClientApplicationOptions` で公開されるすべてのオプションの説明については、[リファレンス ドキュメント](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplicationoptions)を参照してください。

### iOS と macOS 用の MSAL のタスク

iOS と macOS 用の MSAL を使用する場合は、次のタスクが必要です。

- `openURL` コールバックの実装
- [キーチェーン アクセス グループの有効化](https://learn.microsoft.com/ja-jp/entra/msal/objc/howto-v2-keychain-objc)
- [ブラウザーと WebView のカスタマイズ](https://learn.microsoft.com/ja-jp/entra/msal/objc/customize-webviews)

##### UWP のタスク

UWP では企業ネットワークを使用できます。 次のセクションで、企業のシナリオで完了する必要があるタスクについて説明します。

詳細については、[MSAL.NET での UWP 固有の考慮事項](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/uwp)に関する記事を参照してください。

### ブローカーを使用するようにアプリケーションを構成する

Android と iOS では、ブローカーによって次のことが可能になります。

- **シングル サインオン (SSO)**: Microsoft Entra ID に登録されているデバイスに SSO を使用できます。 SSO を使用すると、ユーザーはアプリケーションごとにサインインする必要がなくなります。
- **デバイスの識別**: この設定により、Microsoft Entra デバイスに関連する条件付きアクセス ポリシーが有効になります。 認証プロセスでは、デバイスがワークプレースに参加したときに作成されたデバイス証明書が使用されます。
- **アプリケーション ID の検証**: アプリケーションでは、ブローカーを呼び出すときにそのリダイレクト URL を渡します。 ブローカーによってそれが検証されます。

#### Android 向け MSAL に対するブローカーの有効化

Android でブローカーを有効にする方法の詳細については、「[Android のブローカー認証](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-android-single-sign-on)」を参照してください。

#### iOS および macOS 用の MSAL に対するブローカーの有効化

iOS および macOS 用の MSAL を使用する Microsoft Entra シナリオでは、ブローカー認証が既定で有効化されています。

以降のセクションでは、iOS および macOS のブローカー認証サポート用にアプリケーションを構成する手順について説明します。 この 2 つの手順セットでは、一部の手順が異なります。

#### iOS および macOS 用の MSAL のブローカー認証

Microsoft Entra シナリオでは、ブローカー認証が既定で有効化されています。

##### 手順 1:コールバックを処理するように AppDelegate を更新する

iOS および macOS 用の MSAL でブローカーが呼び出されると、ブローカーは `openURL` メソッドを使用してアプリケーションにコールバックします。 MSAL がブローカーからの応答を待っているため、アプリケーションが協力して MSAL をコールバックする必要があります。 次のコード例に示すように、メソッドをオーバーライドするように `AppDelegate.m` ファイルを更新することで、この機能を設定します。

```objc
- (BOOL)application:(UIApplication *)app
            openURL:(NSURL *)url
            options:(NSDictionary<UIApplicationOpenURLOptionsKey,id> *)options
{
    return [MSALPublicClientApplication handleMSALResponse:url
                                         sourceApplication:options[UIApplicationOpenURLOptionsSourceApplicationKey]];
}
```

```swift
    func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool {

        guard let sourceApplication = options[UIApplication.OpenURLOptionsKey.sourceApplication] as? String else {
            return false
        }

        return MSALPublicClientApplication.handleMSALResponse(url, sourceApplication: sourceApplication)
    }
```

iOS 13 以降で `UISceneDelegate` を採用した場合は、代わりに `scene:openURLContexts:` の `UISceneDelegate` に MSAL のコールバックを配置します。 MSAL `handleMSALResponse:sourceApplication:` の呼び出しは URL ごとに 1 回のみにする必要があります。

詳しくは、[Apple のドキュメント](https://developer.apple.com/documentation/uikit/uiscenedelegate/3238059-scene?language=objc)をご覧ください。

##### 手順 2:URL スキームを登録する

iOS および macOS 用の MSAL では、URL を使用してブローカーが呼び出され、ブローカーの応答がアプリに返されます。 ラウンド トリップを終了するには、`Info.plist` ファイルにアプリの URL スキームを登録します。

アプリのスキームを登録するには:

1. カスタム URL スキームの前に `msauth` を付けます。
2. バンドル ID をスキーマの末尾に追加します。 次のパターンに従います。

    `$"msauth.(BundleId)"`

    ここでは、`BundleId` によってデバイスが一意に識別されます。 たとえば、`BundleId` が `yourcompany.xforms` の場合、URL スキームは `msauth.com.yourcompany.xforms` になります。

    この URL スキームは、ブローカーから応答を受け取るときにアプリを一意に識別するリダイレクト URI の一部になります。 `msauth.(BundleId)://auth` 形式のリダイレクト URI がアプリケーションに対して登録されていることを確認してください。

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
    ```

##### 手順 3:LSApplicationQueriesSchemes を追加する

Microsoft Authenticator アプリがインストールされている場合にその呼び出しを許可するために、`LSApplicationQueriesSchemes` を追加します。

注

アプリが Xcode 11 以降を使用してコンパイルされている場合は、`msauthv3` スキームが必要です。

`LSApplicationQueriesSchemes` を追加する方法の例を次に示します。

```xml
<key>LSApplicationQueriesSchemes</key>
<array>
  <string>msauthv2</string>
  <string>msauthv3</string>
</array>
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-mobile-call-api"} -->
## モバイル アプリから Web API を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-mobile-call-api
- Service: identity-platform / workforce
- Article date: 2020-05-18
- Summary: Web API を呼び出すモバイル アプリを構築する方法について説明します。 (Web API を呼び出す。)

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

アプリがユーザーをサインインさせ、トークンを受信すると、Microsoft Authentication Library (MSAL) では、ユーザー、ユーザーの環境、発行されたトークンに関する情報を公開します。 アプリではこれらの値が使用され、Web API を呼び出したり、ウェルカム メッセージをユーザーに表示したりすることができます。

この記事では、最初に MSAL の結果を確認します。 次に、`AuthenticationResult` または `result` からのアクセス トークンを使用して保護された Web API を呼び出す方法を説明します。

### MSAL の結果

MSAL には次のような値があります。

- `AccessToken` は、HTTP ベアラー要求で保護された Web API を呼び出します。
- `IdToken` には、サインインしたユーザーに関する有用な情報が含まれています。 この情報には、ユーザー名、ホーム テナント、ストレージの一意識別子などが含まれます。
- `ExpiresOn` はトークンの有効期限です。 MSAL は、アプリの自動更新を処理します。
- `TenantId` は、ユーザーがサインインした場所のテナントの識別子です。 Microsoft Entra B2B のゲスト ユーザーの場合、この値によって、ユーザーがサインインしたテナントが識別されます。 この値は、ユーザーのホーム テナントを識別しません。
- `Scopes` は、お使いのトークンで付与されたスコープを示します。 付与されたスコープは、要求したスコープのサブセットである場合があります。

また、MSAL では `Account` 値の抽象化も提供されます。 `Account` 値は、現在のユーザーのサインインしたアカウントを表します。

- `HomeAccountIdentifier` は、ユーザーのホーム テナントの識別子を識別します。
- `UserName` は、ユーザーの推奨ユーザー名です。 Azure AD B2C ユーザーの場合、この値は空の場合があります。
- `AccountIdentifier` は、サインインしているユーザーを識別します。 ほとんどの場合、ユーザーが別のテナントのゲストでない限り、この値は `HomeAccountIdentifier` 値と同じです。

### API を呼び出す

アクセス トークンがあれば、Web API を呼び出すことができます。 お使いのアプリではトークンを使って HTTP 要求が作成され、実行されます。

#### Android

```Java
        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject parameters = new JSONObject();

        try {
            parameters.put("key", "value");
        } catch (Exception e) {
            // Error when constructing.
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, MSGRAPH_URL,
                parameters,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Successfully called Graph. Process data and send to UI.
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Error.
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();

                // Put access token in HTTP request.
                headers.put("Authorization", "Bearer " + authResult.getAccessToken());
                return headers;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(
                3000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
```

#### iOS および macOS 用の MSAL

トークンを取得するメソッドは `MSALResult` オブジェクトを返します。 `MSALResult` は `accessToken` プロパティを公開します。 `accessToken` を使用して、Web API を呼び出すことができます。 保護された Web API にアクセスするための呼び出しを行う前に、このプロパティを HTTP Authorization ヘッダーに追加してください。

```objc
NSMutableURLRequest *urlRequest = [NSMutableURLRequest new];
urlRequest.URL = [NSURL URLWithString:"https://contoso.api.com"];
urlRequest.HTTPMethod = @"GET";
urlRequest.allHTTPHeaderFields = @{ @"Authorization" : [NSString stringWithFormat:@"Bearer %@", accessToken] };

NSURLSessionDataTask *task =
[[NSURLSession sharedSession] dataTaskWithRequest:urlRequest
     completionHandler:^(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error) {}];
[task resume];
```

```swift
let urlRequest = NSMutableURLRequest()
urlRequest.url = URL(string: "https://contoso.api.com")!
urlRequest.httpMethod = "GET"
urlRequest.allHTTPHeaderFields = [ "Authorization" : "Bearer \(accessToken)" ]

let task = URLSession.shared.dataTask(with: urlRequest as URLRequest) { (data: Data?, response: URLResponse?, error: Error?) in }
task.resume()
```

### 複数の API 要求を行う

同じ API を複数回呼び出す場合、または複数の API を呼び出す場合は、アプリを構築するときに、次の点を考慮してください。

- **増分同意**:Microsoft ID プラットフォームでは、すべて開始時にではなく、アクセス許可が必要なときに、アプリがユーザーの同意を得られるようにしています。 アプリが API を呼び出す準備ができたら、毎回、必要なスコープのみを要求します。
- **条件付きアクセス**: 複数の API 要求を行うときに、特定のシナリオでは、追加の条件付きアクセスの要件を満たさなければならない場合があります。 最初の要求に条件付きアクセス ポリシーが適用されておらず、アプリが条件付きアクセスを必要とする新しい API にサイレントでアクセスしようとする場合に、要件がこのように増加することがあります。 この問題に対処するため、必ずサイレント要求からのエラーをキャッチし、対話型の要求を行えるように準備します。 詳細については、[条件付きアクセスについてのガイダンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-conditional-access-dev-guide)を参照してください。

### 増分同意と条件付きアクセスを使用して複数の API を呼び出す

同じユーザーに対して複数の API を呼び出す場合、ユーザーのトークンを取得した後、続けて `AcquireTokenSilent` を呼び出してトークンを取得すれば、ユーザーに何度も資格情報の入力を求める必要がなくなります。

```csharp
var result = await app.AcquireTokenXX("scopeApi1")
                      .ExecuteAsync();

result = await app.AcquireTokenSilent("scopeApi2")
                  .ExecuteAsync();
```

対話は、次の場合に必要です。

- ユーザーが最初の API については同意したが、より多くのスコープについて同意する必要が生じた。 この場合は、増分同意を使用します。
- 最初の API は[多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)を必要としなかったが、次の API は多要素認証を必要とする。

```csharp
var result = await app.AcquireTokenXX("scopeApi1")
                      .ExecuteAsync();

try
{
 result = await app.AcquireTokenSilent("scopeApi2")
                  .ExecuteAsync();
}
catch(MsalUiRequiredException ex)
{
 result = await app.AcquireTokenInteractive("scopeApi2")
                  .WithClaims(ex.Claims)
                  .ExecuteAsync();
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-protected-web-api-app-configuration"} -->
## 保護された Web API アプリを構成する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-protected-web-api-app-configuration
- Service: identity-platform / workforce
- Article date: 2025-04-14
- Summary: 保護された Web API をビルドして、アプリケーションのコードを構成する方法について学習します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

保護された Web API のコードを構成するには、次の点を理解しておいてください。

- API を保護対象として定義するもの
- ベアラー トークンの構成方法
- トークンの検証方法

### 承認済みトークンのバージョン

Microsoft ID プラットフォームでは、v1.0 トークンと v2.0 トークンを発行できます。 これらのトークンの詳細については、「 [アクセス トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)」を参照してください。

API が受け入れるトークンのバージョンは、Azure portal で Web API アプリケーションの登録を作成するときに **サポートされているアカウントの種類** の選択によって異なります。

- **サポートされているアカウントの種類**の値が**任意の組織のディレクトリ内のアカウントと個人用 Microsoft アカウント (Skype、Xbox、Outlook.com など) の**場合、受け入れ可能なトークンのバージョンは v2.0 である必要があります。
- それ以外の場合は、承認済みトークンのバージョンを v1.0 にできます。

アプリケーションを作成した後、これらの手順に従って、承認済みトークンのバージョンを決定または変更できます。

1. Microsoft Entra 管理センターで、アプリを選択し、[マニフェスト] を選択 **します**。
2. マニフェストで **プロパティ accessTokenAcceptedVersion** を見つけます。
3. その値により、Web API で受け入れられるトークンのバージョンが Microsoft Entra に対して指定されます。
    - 値が 2 の場合、Web API では v2.0 トークンが受け入れられます。
    - 値が **null** の場合、Web API は v1.0 トークンを受け入れます。
4. トークンのバージョンを変更した場合は、[ **保存]** を選択します。

Web API で受け入れられるトークンのバージョンを指定します。 クライアントで、Microsoft ID プラットフォームに Web API 用のトークンを要求すると、クライアントは Web API が受け入れるトークンのバージョンを示すトークンを受け取ります。

### ASP.NET と ASP.NET Core の API を保護対象として定義するものとは

Web アプリと同じように、ASP.NET と ASP.NET Core の Web API は、そのコントローラー アクションに **[Authorize]** 属性のプレフィックスがあるため、保護されています。 コントローラー アクションは、承認されている ID で API が呼び出された場合にのみ呼び出すことができます。

次の質問について考えてみましょう。

- Web API を呼び出せるのはアプリのみです。 API では、呼び出し元のアプリの ID をどのように認識しますか?
- ユーザーの代わりにアプリで API が呼び出される場合、ユーザーの ID は何ですか?

### ベアラー トークン

アプリが呼び出されたときにヘッダーに設定されるベアラー トークンには、アプリ ID に関する情報が保持されます。 また、Web アプリがデーモン アプリからのサービス間の呼び出しを受け入れる場合を除き、ユーザーに関する情報も保持されます。

これは、Microsoft Authentication Library for .NET (MSAL.NET) を使用してトークンを取得した後、API を呼び出すクライアントを示す C# コードの例です。

```csharp
var scopes = new[] {$"api://.../access_as_user"};
var result = await app.AcquireToken(scopes)
                      .ExecuteAsync();

httpClient = new HttpClient();
httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", result.AccessToken);

// Call the web API.
HttpResponseMessage response = await _httpClient.GetAsync(apiUri);
```

重要

クライアント アプリケーションにより、"*Web API の*" Microsoft ID プラットフォームにベアラー トークンが要求されます。 API は、トークンを検証し、含まれている要求を表示する必要がある唯一のアプリケーションです。 クライアント アプリで、トークンの要求を検査してみることはできません

将来、Web API で、トークンの暗号化が要求される可能性があります。 この要件により、アクセス トークンを表示できるクライアント アプリのアクセスが禁止されます。

### JwtBearer の構成

このセクションでは、ベアラー トークンの構成方法について説明します。

#### config ファイル

1 つのテナント (基幹業務アプリ) からのアクセス トークンを受け入れる場合にのみ `TenantId` を指定する必要があります。 それ以外の場合、`common` のままにできます。 異なる値には、次の値を指定できます。

- GUID (テナント ID = ディレクトリ ID)
- `common` は、任意の組織アカウントと個人アカウントにすることができます
- `organizations` は、任意の組織にできます
- `consumers` は、Microsoft 個人アカウントです

```Json
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "ClientId": "Enter_the_Application_(client)_ID_here",
    "TenantId": "common"
  },
  "Logging": {
    "LogLevel": {
      "Default": "Warning"
    }
  },
  "AllowedHosts": "*"
}
```

##### Web API にカスタム アプリ ID URI を使用する

Azure portal によって提案された既定のアプリ ID URI を受け入れた場合は、対象ユーザーを指定する必要はありません。 それ以外の場合は、Web API のアプリ ID URI を値とする `Audience` プロパティを追加します。 これは通常、`api://` で始まります。

```Json
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "ClientId": "Enter_the_Application_(client)_ID_here",
    "TenantId": "common",
    "Audience": "Enter_the_Application_ID_URI_here"
  },
}
```

#### コードの初期化

**[Authorize]** 属性を保持するコントローラー アクションでアプリが呼び出されると、ASP.NET と ASP.NET Core により、Authorization ヘッダーのベアラー トークンからアクセス トークンが抽出されます。 その後、アクセス トークンは JwtBearer ミドルウェアに転送され、Microsoft IdentityModel Extensions for .NET が呼び出されます。

##### マイクロソフト.アイデンティティ.Web

## [ASP.NET Core](#tab/aspnetcore)
ASP.NET Core で Web API を開発する場合は、[Microsoft.Identity.Web](https://www.nuget.org/packages/Microsoft.Identity.Web) NuGet パッケージを使用することをお勧めします。

*Microsoft.Identity.Web* を使用すると、ASP.NET Core、認証ミドルウェア、および .NET 用の [Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) 間を結び付けることができます。 これにより、より明確で堅牢な開発者エクスペリエンスが可能になり、Microsoft ID プラットフォームと Azure AD B2C の機能を活用できます。

##### .NET 6.0 用 ASP.NET

Microsoft.Identity.Web を使う新しい Web API プロジェクトを作成するには、.NET 6.0 CLI または Visual Studio でプロジェクト テンプレートを使います。

**.NET Core CLI**

```dotnetcli
# Create new web API that uses Microsoft.Identity.Web
dotnet new webapi --auth SingleOrg
```

**Visual Studio** - Visual Studio で Web API プロジェクトを作成するには、**[ファイル]**&gt;**[新規作成]**&gt;**[プロジェクト]**&gt;**[ASP.NET Core Web API]** を選択します。

.NET CLI と Visual Studio の両方のプロジェクト テンプレートで、このコード スニペットのような *Program.cs* ファイルが作成されます。 `Microsoft.Identity.Web` using ディレクティブと、認証と承認を含む行に注目してください。

```csharp
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.Identity.Web;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApi(builder.Configuration.GetSection("AzureAd"));

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthentication();
app.UseAuthorization();

app.MapControllers();

app.Run();
```

## [ASP.NET](#tab/aspnet)
ASP.NET で Web API を開発するときは、[Microsoft.Identity.Web.OWIN](https://www.nuget.org/packages/Microsoft.Identity.Web.OWIN) NuGet パッケージを使用することをお勧めします。

"Microsoft.Identity.Web.OWIN" を使用すると、ASP.NET、ASP.NET 認証ミドルウェア、および .NET 用の *Microsoft Authentication Library (MSAL)* 間を結び付けることができます。 これにより、より明確で堅牢な開発者エクスペリエンスが可能になり、Microsoft ID プラットフォームと Azure AD B2C の機能を活用できます。

ASP.NET Core (appsettings.json) と同じ構成ファイルを使用するため、このファイルがプロジェクトの出力とともにコピーされていることを確認する必要があります (プロパティのコピーは、常に Visual Studio または .csproj のファイルのプロパティにあります)

"Microsoft.Identity.Web.OWIN" は、 という名前の拡張メソッドを IAppBuilder に追加します。`AddMicrosoftIdentityWebApi` これらのメソッドは、`OwinTokenAcquirerFactory` のインスタンスをパラメーターとして受け取って、`OwinTokenAcquirerFactory.GetDefaultInstance<OwinTokenAcquirerFactory>()` を呼び出し、`IServiceCollection` のインスタンスを表示します。このインスタンスに対して、ダウンストリーム API の呼び出しや、トークン キャッシュの構成を行う多くのサービスを追加できます。

[Startup.Auth.cs](https://github.com/AzureAD/microsoft-identity-web/blob/master/tests/DevApps/aspnet-mvc/OwinWebApp/App_Start/Startup.Auth.cs) のサンプル コードを次に示します。 詳細なコードは [tests/DevApps/aspnet-mvc/OwinWebApp](https://github.com/AzureAD/microsoft-identity-web/tree/master/tests/DevApps/aspnet-mvc/OwinWebApp) から入手できます

```CSharp
using Microsoft.Owin.Security;
using Microsoft.Owin.Security.Cookies;
using Owin;
using Microsoft.Identity.Web;
using Microsoft.Identity.Web.TokenCacheProviders.InMemory;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Identity.Client;
using Microsoft.Identity.Abstractions;
using Microsoft.Identity.Web.OWIN;
using System.Web.Services.Description;

namespace OwinWebApp
{
    public partial class Startup
    {
        public void ConfigureAuth(IAppBuilder app)
        {
            app.SetDefaultSignInAsAuthenticationType(CookieAuthenticationDefaults.AuthenticationType);
            app.UseCookieAuthentication(new CookieAuthenticationOptions());

            OwinTokenAcquirerFactory factory = TokenAcquirerFactory.GetDefaultInstance<OwinTokenAcquirerFactory>();

            app.AddMicrosoftIdentityWebApp(factory);
            factory.Services
                .Configure<ConfidentialClientApplicationOptions>(options => { options.RedirectUri = "https://localhost:44386/"; })
                .AddMicrosoftGraph()
                .AddDownstreamApi("DownstreamAPI1", factory.Configuration.GetSection("DownstreamAPI"))
                .AddInMemoryTokenCaches();
            factory.Build();
        }
    }
}
```

--

### トークンの検証

前のスニペットでは、Web アプリの OpenID Connect ミドルウェアと同様に、JwtBearer ミドルウェアによって `TokenValidationParameters` の値に基づいてトークンが検証されます。 トークンは必要に応じて暗号化が解除され、要求が抽出され、署名が検証されます。 その後、ミドルウェアでは、このデータを調べてトークンを確認します。

- Audience:トークンが Web API のターゲットとなっていること。
- サブ:Web API の呼び出しが許可されているアプリに対して発行されたこと。
- 発行者:信頼できるセキュリティ トークン サービス (STS) によって発行されたこと。
- 有効期限:有効期間が範囲内であること。
- 署名:改ざんされていないこと。

特別な検証もできます。 たとえば、署名キー (トークンに埋め込まれている場合) が信頼されていること、およびトークンが再生されていないことを確認することができます。 最後に、一部のプロトコルでは、特定の検証が必要です。

#### 検証コントロール

確認手順は、検証コントロールでキャプチャされます。これらは [Microsoft IdentityModel Extensions for .NET](https://github.com/AzureAD/azure-activedirectory-identitymodel-extensions-for-dotnet) のオープン ソース ライブラリで提供されています。 検証コントロールは、ライブラリ ソース ファイル [Microsoft.IdentityModel.Tokens/Validators.cs](https://github.com/AzureAD/azure-activedirectory-identitymodel-extensions-for-dotnet/blob/master/src/Microsoft.IdentityModel.Tokens/Validators.cs) で定義されています。

次の表では、検証コントロールについて説明します。

| 検証コントロール | 説明 |
| --- | --- |
| **ValidateAudienceの** | トークンが、自分のトークンを確認するアプリケーション用であることを保証します。 |
| **ValidateIssuer (発行者の検証)** | トークンが信頼できる STS、つまり自分が信頼する人から発行されたことを保証します。 |
| **ValidateIssuerSigningKey の** | トークンを確認するアプリケーションで、トークンの署名に使用されたキーが信頼されていることを保証します キーがトークンに埋め込まれている特殊なケースがあります。 ただし、このケースは通常は発生しません。 |
| **ValidateLifetimeの** | トークンが引き続きまたは既に有効であることを保証します。 検証コントロールにより、トークンの有効期間が **notbefore** 要求と **expires** 要求で指定された範囲内にあるかどうかが確認されます。 |
| **ValidateSignature (署名のバリデーション)** | トークンが改ざんされていないことを保証します。 |
| **ValidateTokenReplay** | トークンが再生されていないことを保証します 一部の 1 回限りの使用のプロトコルには特殊なケースがあります。 |

##### トークンの検証のカスタマイズ

検証コントロールは、*TokenValidationParameters* クラスのプロパティに関連付けられています。 このプロパティは、ASP.NET と ASP.NET Core の構成から初期化されます。

ほとんどの場合、パラメーターを変更する必要はありません。 シングル テナントではないアプリは例外です。 これらの Web アプリでは、任意の組織から、または個人用 Microsoft アカウントからのユーザーを受け入れます。 この場合、発行者を検証する必要があります。 Microsoft.Identity.Web では発行者の検証も行われます。

ASP.NET Core で、トークン検証パラメーターをカスタマイズする場合は、*Startup.cs* で次のスニペットを使用します。

```c
services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
        .AddMicrosoftIdentityWebApi(Configuration);
services.Configure<JwtBearerOptions>(JwtBearerDefaults.AuthenticationScheme, options =>
{
  options.TokenValidationParameters.ValidAudiences = new[] { /* list of valid audiences */};
});
```

ASP.NET MVC の場合、次のコード サンプルにカスタム トークンの検証を行う方法が示されています。

https://github.com/azure-samples/active-directory-dotnet-webapi-manual-jwt-validation

### Azure Functions でのトークンの検証

Azure Functions では、受信アクセス トークンを検証することもできます。 このような検証の例については、GitHub の次のコード サンプルを参照してください。

- .NET:[Azure-Samples/ms-identity-dotnet-webapi-azurefunctions](https://github.com/Azure-Samples/ms-identity-dotnet-webapi-azurefunctions)
- Node.js:[Azure-Samples/ms-identity-nodejs-webapi-azurefunctions](https://github.com/Azure-Samples/ms-identity-nodejs-webapi-azurefunctions)
- Python: [Azure-Samples/ms-identity-python-webapi-azurefunctions)](https://github.com/Azure-Samples/ms-identity-python-webapi-azurefunctions)

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-protected-web-api-expose-scopes"} -->
## 保護された Web API でスコープを公開する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-protected-web-api-expose-scopes
- Service: identity-platform / workforce
- Article date: 2024-05-28
- Summary: 保護された Web API でスコープを公開する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

この記事では、保護された Web API のアプリケーションにスコープを追加する方法について説明します。

### [前提条件]

- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID

### スコープとアプリケーション ID URI

通常、スコープの形式は `resourceURI/scopeName` です。 Microsoft Graph の場合、スコープにはショートカットがあります。 たとえば、`User.Read` は `https://graph.microsoft.com/user.read` のショートカットです。

アプリの登録時に、これらのパラメーターを定義します。

- リソース URI
- 1 つまたは複数のスコープ
- 1 つまたは複数のアプリ ロール

既定では、アプリケーションの登録ポータルでは、リソース URI `api://{clientId}` を使用することをお勧めします。 この URI は一意ですが、人間が判読できるものではありません。 URI を変更する場合は、新しい値が一意になるようにしてください。 アプリケーション登録ポータルでは、 [構成済みのパブリッシャー ドメイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-configure-publisher-domain)が確実に使用されます。

クライアント アプリケーションでは、スコープは *委任されたアクセス許可* として表示され、アプリ ロールは Web API の *アプリケーションアクセス許可* として表示されます。

スコープは、アプリのユーザーに提示される同意ウィンドウにも表示されます。 そのため、次の場合にスコープについて説明する、対応する文字列を指定します。

- ユーザーに表示される場合。
- 管理者の同意を許可できる、テナント管理者に表示される場合。

アプリ ロールは、ユーザーが同意することはできません (ユーザー自身の代わりに Web API を呼び出すアプリケーションによって使用されるため)。 テナント管理者は、アプリ ロールを公開している Web API のクライアント アプリケーションに同意する必要があります。 詳細については、 [管理者の同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-admin-consent) を参照してください。

#### 委任されたアクセス許可 (スコープ) を公開する

委任されたアクセス許可または *スコープ*を公開するには、「 [Web API を公開するようにアプリケーションを構成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-configure-app-expose-web-apis)」の手順に従います。

この一連の記事で説明されている Web API シナリオに従っている場合は、次の設定を使用します。

- **アプリケーション ID URI**: 提案されたアプリケーション ID URI (*api://&lt;clientId&gt;*) を受け入れます (メッセージが表示された場合)
- **スコープ名**: *access\_as\_user*
- **同意できるユーザー**: *管理者とユーザー*
- **管理者の同意の表示名**: *ユーザーとしての TodoListService へのアクセス*
- **管理者の同意の説明**: *ユーザーとして TodoListService Web API にアクセス*します
- **ユーザーの同意の表示名**: *ユーザーとしての TodoListService へのアクセス*
- **ユーザーの同意の説明**: *ユーザーとして TodoListService Web API にアクセスします*
- **状態**: *有効*

ヒント

**アプリケーション ID URI** の場合は、API の物理機関 (`https://graph.microsoft.com`など) に設定できます。 これは、呼び出す必要がある API の URL がわかっている場合に便利です。

#### サービスまたはデーモン アプリで Web API が呼び出される場合

API にデーモン、サービス、またはその他の (人間による) 非対話型アプリケーションからアクセスする必要がある場合は、委任されたアクセス許可ではなく、"アプリケーションのアクセス許可" を公開します。 デーモンとサービスの種類のアプリケーションは無人で実行され、自分の ID で認証されるため、アクセス許可を "委任" するユーザーはいません。

##### アプリケーションのアクセス許可 (アプリ ロール) を公開する

アプリケーションのアクセス許可を公開するには、「アプリロールを [アプリに追加する](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)」の手順に従います。

[**許可されるメンバーの種類**] の [**アプリ ロールの作成**] ウィンドウで、[アプリケーション] を選択**します**。 または、この記事の説明に従って **、アプリケーション マニフェスト エディター** を使用してロールを追加します。

##### アクセス トークンを特定のクライアント アプリに制限する

アプリ ロールは、アプリケーション開発者がアプリのアクセス許可を公開するために使用するメカニズムです。 Web API のコードでは、呼び出し元から受け取るアクセス トークン内のアプリ ロールを確認する必要があります。

別のセキュリティ層を追加するために、Microsoft Entra テナント管理者は、テナント管理者が API アクセスを承認したクライアント アプリ *にのみ* 、Microsoft ID プラットフォームがセキュリティ トークンを発行するようにテナントを構成できます。

アプリ ロールが割り当てられているクライアント アプリのみにトークンの発行を制限してセキュリティを強化するには、以下の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、[**Entra ID**&gt;**App registrations**] でアプリを選択します。
2. アプリケーションの **[概要** ] ページの **[Essentials**] で、 **ローカル ディレクトリでマネージド アプリケーション** を見つけて選択し、[ **エンタープライズ アプリケーションの概要** ] ページに移動します。
3. [ **管理**] で [プロパティ] を選択 **します**。
4. [ **割り当てが必要]** を [ **はい**] に設定します。
5. **保存** を選択します。

これで、Microsoft Entra ID は、Web API のアクセス トークンを要求するクライアント アプリケーションのアプリ ロールの割り当てを確認するようになりました。 クライアント アプリにアプリ ロールが割り当てられていない場合、Microsoft Entra ID は `_invalid_client: AADSTS501051: Application \<application name\> isn't assigned to a role for the \<web API\>_` のようなエラー メッセージをクライアントに返します。

Warnung

**アプリケーションのコードでは、AADSTS エラー コード** またはそのメッセージ文字列をリテラルとして使用しないでください。 "AADSTS" エラー コードと Microsoft Entra ID によって返されるエラー メッセージ文字列は不変ではありません\*。Microsoft は、お客様の知識がなくてもいつでも変更できます。 AADSTS コードまたはそのメッセージ文字列の値に基づいてコード内で分岐の決定を行うと、アプリケーションの機能と安定性が危険にさらされます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-protected-web-api-verification-scope-app-roles"} -->
## 保護された Web API でスコープとアプリ ロールを検証する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-protected-web-api-verification-scope-app-roles
- Service: identity-platform / workforce
- Article date: 2025-01-30
- Summary: API が、適切なスコープを持つユーザーに代わるアプリケーションによって、また適切なアプリケーション ロールを持つデーモン アプリによってのみ呼び出されることを確認します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

この記事では、Web API に承認を追加する方法について説明します。 この保護により、API を呼び出せるのは以下のものだけになります。

- 適切なスコープとロールを持つユーザーに代わるアプリケーション。
- 適切なアプリケーション ロールを持つデーモン アプリ。

この記事のコード スニペットは、GitHub の以下のコード サンプルから抜粋されたものです。

- [ASP.NET Core Web API の増分チュートリアル](https://github.com/Azure-Samples/active-directory-dotnet-native-aspnetcore-v2/blob/master/1.%20Desktop%20app%20calls%20Web%20API/TodoListService/Controllers/TodoListController.cs)
- [ASP.NET Web API のサンプル](https://github.com/Azure-Samples/ms-identity-aspnet-webapi-onbehalfof/blob/master/TodoListService/Controllers/TodoListController.cs)

ASP.NET または ASP.NET Core の Web API を保護するには、次のいずれかの項目に `[Authorize]` 属性を追加する必要があります。

- すべてのコントローラー アクションを保護する場合は、コントローラー自体
- API の個々のコントローラー アクション

```csharp
    [Authorize]
    public class TodoListController : Controller
    {
     // ...
    }
```

ただし、この保護は不十分です。 ASP.NET および ASP.NET Core でトークンが検証されることしか保証されません。 API は、API の呼び出しに使用されるトークンが、予期された要求を使用して要求されていることを検証する必要があります。 特に次の要求では検証が必要です。

- API がユーザーの代わりに呼び出される場合は、"*スコープ*"。
- API をデーモン アプリから呼び出すことができる場合は、"*アプリ ロール*"。

### ユーザーに代わって呼び出される API のスコープの確認

クライアント アプリがユーザーに代わって API を呼び出す場合、API は、API 用の特定のスコープを持つベアラー トークンを要求する必要があります。 詳細については、[「コード構成」の「ベアラー トークン」](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-protected-web-api-app-configuration#bearer-token)を参照してください。

## [ASP.NET Core](#tab/aspnetcore)
ASP.NET Core では、各コントローラー アクションのスコープを検証するために、[Microsoft.Identity.Web](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/microsoft-identity-web/) を使用できます。 コントローラーのレベルまたはアプリケーション全体で検証することもできます。

##### 各コントローラー アクションのスコープを確認する

`[RequiredScope]` 属性を使用して、コントローラー アクション内のスコープを検証できます。 この属性にはいくつかのオーバーライドがあります。 必要なスコープを直接取得するものと、構成に対するキーを取得するものです。

###### ハードコードされたスコープでコントローラー アクションのスコープを検証する

次のコード スニペットは、ハードコードされたスコープでの `[RequiredScope]` 属性の使用方法を示しています。

```csharp
using Microsoft.Identity.Web

[Authorize]
public class TodoListController : Controller
{
    /// <summary>
    /// The web API will accept only tokens that have the `access_as_user` scope for
    /// this API.
    /// </summary>
    const string scopeRequiredByApi = "access_as_user";

    // GET: api/values
    [HttpGet]
    [RequiredScope(scopeRequiredByApi)]
    public IEnumerable<TodoItem> Get()
    {
        // Do the work and return the result.
        // ...
    }
 // ...
}
```

###### 構成で定義されたスコープを使用して、コントローラー アクションのスコープを検証する

構成でこれらの必要なスコープを宣言し、構成キーを参照することもできます。

たとえば、*appsettings.json* に次のような構成があるとします。

```JSON
{
 "AzureAd" : {
   // more settings
   "Scopes" : "access_as_user access_as_admin"
  }
}
```

この場合、`[RequiredScope]` 属性でこれを参照します。

```csharp
using Microsoft.Identity.Web

[Authorize]
public class TodoListController : Controller
{
    // GET: api/values
    [HttpGet]
    [RequiredScope(RequiredScopesConfigurationKey = "AzureAd:Scopes")]
    public IEnumerable<TodoItem> Get()
    {
        // Do the work and return the result.
        // ...
    }
 // ...
}
```

###### スコープを条件付きで検証する

スコープを条件付きで検証したい場合があります。 これは、`VerifyUserHasAnyAcceptedScope` で `HttpContext` 拡張メソッドを使用して行います。

```csharp
using Microsoft.Identity.Web

[Authorize]
public class TodoListController : Controller
{
    /// <summary>
    /// The web API will accept only tokens 1) for users, 2) that have the `access_as_user` scope for
    /// this API.
    /// </summary>
    static readonly string[] scopeRequiredByApi = new string[] { "access_as_user" };

    // GET: api/values
    [HttpGet]
    public IEnumerable<TodoItem> Get()
    {
         HttpContext.VerifyUserHasAnyAcceptedScope(scopeRequiredByApi);
        // Do the work and return the result.
        // ...
    }
 // ...
}
```

##### コントローラーのレベルでスコープを検証する

コントローラー全体のスコープを検証することもできます

###### ハードコードされたスコープでコントローラーのスコープを検証する

次のコード スニペットは、コントローラーのハードコードされたスコープでの `[RequiredScope]` 属性の使用方法を示しています。 RequiredScopeAttribute を使用するには、次のいずれかを行う必要があります。

- `AddMicrosoftIdentityWebApi` で  を、[コード構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-protected-web-api-app-configuration)で示すように使用する
- あるいは、`ScopeAuthorizationRequirement`で説明しているように  を承認ポリシーに追加する

```csharp
using Microsoft.Identity.Web

[Authorize]
[RequiredScope(scopeRequiredByApi)]
public class TodoListController : Controller
{
    /// <summary>
    /// The web API will accept only tokens 1) for users, 2) that have the `access_as_user` scope for
    /// this API.
    /// </summary>
    static readonly string[] scopeRequiredByApi = new string[] { "access_as_user" };

    // GET: api/values
    [HttpGet]
    public IEnumerable<TodoItem> Get()
    {
        // Do the work and return the result.
        // ...
    }
 // ...
}
```

###### 構成で定義されたスコープを使用して、コントローラーのスコープを検証する

アクションと同様に、構成でこれらの必要なスコープを宣言し、構成キーを参照することもできます。

```csharp
using Microsoft.Identity.Web

[Authorize]
[RequiredScope(RequiredScopesConfigurationKey = "AzureAd:Scopes")]
public class TodoListController : Controller
{
    // GET: api/values
    [HttpGet]
    public IEnumerable<TodoItem> Get()
    {
        // Do the work and return the result.
        // ...
    }
 // ...
}
```

##### スコープをよりグローバルに検証する

Web API の詳細なスコープを定義し、各コントローラー アクションでスコープを確認することが、お勧めの方法です。 ただし、アプリケーションまたはコントローラーのレベルでスコープを検証することもできます。 詳細については、ASP.NET のコア ドキュメントの「[ASP.NET Core でのクレーム ベースの承認](https://learn.microsoft.com/ja-jp/aspnet/core/security/authorization/claims)」を参照してください。

##### 検証される対象

`[RequiredScope]` 属性と `VerifyUserHasAnyAcceptedScope` メソッドは、次の手順のような処理を行います。

- `http://schemas.microsoft.com/identity/claims/scope` または `scp` という名前の要求があることを確認します。
- 要求が、API で想定されているスコープを含む値を持っていることを確認します。

## [ASP.NET クラシック](#tab/aspnet)
ASP.NET アプリケーションでは、次の方法でスコープを検証できます。

```CSharp
[Authorize]
public class TodoListController : ApiController
{
    public IEnumerable<TodoItem> Get()
    {
       ValidateScopes(new[] {"read"; "admin" } );
       // ...
    }
```

次に、`ValidateScopes` の簡略化されたバージョンを示します。

```csharp
private void ValidateScopes(IEnumerable<string> acceptedScopes)
{
    //
    // The `role` claim tells you what permissions the client application has in the service.
    // In this case, we look for a `role` value of `access_as_application`.
    //
    Claim scopeClaim = ClaimsPrincipal.Current.FindFirst("scp");
    if (scopeClaim == null || !scopeClaim.Value.Split(' ').Intersect(acceptedScopes).Any())
    {
        throw new HttpResponseException(new HttpResponseMessage
        { StatusCode = HttpStatusCode.Forbidden,
            ReasonPhrase = $"The 'scp' claim does not contain '{scopeClaim}' or was not found"
        });
    }
}
```

ASP.NET Core の `ValidateScopes` の完全なバージョンは、「[*ScopesRequiredHttpContextExtensions.cs*](https://github.com/AzureAD/microsoft-identity-web/blob/master/src/Microsoft.Identity.Web/Resource/ScopesRequiredHttpContextExtensions.cs)」を参照してください。

---

### デーモン アプリによって呼び出される API のアプリ ロールの確認

Web API が [デーモン アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-daemon-app-configuration)によって呼び出された場合、そのアプリには Web API に対するアプリケーションのアクセス許可が必要です。 「[アプリケーションのアクセス許可 (アプリ ロール) の公開](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-protected-web-api-expose-scopes#expose-application-permissions-app-roles)」に示されているように、API はそのようなアクセス許可を公開します。 1 つの例として、`access_as_application` アプリ ロールがあります。

ここでは、受け取ったトークンに `roles` 要求が含まれていることと、この要求に想定されている値が含まれていることを API に確認させる必要があります。 この確認コードは、委任されたアクセス許可を確認するコードと似ています。異なるのは、コントローラー アクションでテストされるのが、スコープではなくロールである点です。

## [ASP.NET Core](#tab/aspnetcore)
次のコード スニペットは、アプリケーション ロールを検証する方法を示しています

```csharp
using Microsoft.Identity.Web

[Authorize]
public class TodoListController : ApiController
{
    public IEnumerable<TodoItem> Get()
    {
        HttpContext.ValidateAppRole("access_as_application");
        // ...
    }
```

代わりに、コントローラーまたはアクション (または razor ページ) で [`[Authorize(Roles = "access_as_application")]`] 属性を使用できます。

```CSharp
[Authorize(Roles = "access_as_application")]
MyController : ApiController
{
    // ...
}
```

[ASP.NET Core のロールベースの承認](https://learn.microsoft.com/ja-jp/aspnet/core/security/authorization/roles)には、ロールベースの承認を実装するためのアプローチがいくつかリストされています。 開発者は、それぞれのシナリオに応じて、いずれかのアプローチを選択できます。

実際のサンプルについては、[ロールとグループによる認可](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/tree/master/5-WebApp-AuthZ)に関する Web アプリの増分チュートリアルを参照してください。

## [ASP.NET クラシック](#tab/aspnet)
ASP.NET アプリケーションでは、次の方法でアプリ ロールを検証できます。

```CSharp
[Authorize]
public class TodoListController : ApiController
{
    public IEnumerable<TodoItem> Get()
    {
       ValidateAppRole("access_as_application");
       // ...
    }
```

`ValidateAppRole` の簡略化されたバージョンは、次のとおりです。

```csharp
private void ValidateAppRole(string appRole)
{
    //
    // The `role` claim tells you what permissions the client application has in the service.
    // In this case, we look for a `role` value of `access_as_application`.
    //
    Claim roleClaim = ClaimsPrincipal.Current.FindFirst("roles");
    if (roleClaim == null || !roleClaim.Value.Split(' ').Contains(appRole))
    {
        throw new HttpResponseException(new HttpResponseMessage
        { StatusCode = HttpStatusCode.Unauthorized,
            ReasonPhrase = $"The 'roles' claim does not contain '{appRole}' or was not found"
        });
    }
}
```

ASP.NET Core の `ValidateAppRole` の完全バージョンは、[*RolesRequiredHttpContextExtensions.cs*](https://github.com/AzureAD/microsoft-identity-web/blob/master/src/Microsoft.Identity.Web/Resource/RolesRequiredHttpContextExtensions.cs) コードを参照してください。

---

#### ユーザーに代わって呼び出される API のアプリ ロールの確認

ユーザーは、「[方法: アプリケーションにアプリ ロールを追加してトークンで受け取る](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)」に示されているように、ユーザー割り当てパターンでロール要求を使用することもできます。 ロールが両方に割り当て可能な場合は、ロールをチェックすると、アプリはユーザーとして、ユーザーはアプリとしてサインインできるようになります。 この混乱を避けるために、ユーザー用とアプリ用に異なるロールを宣言することをお勧めします。

ユーザー / グループを使用してアプリ ロールを定義している場合は、ロール要求をスコープと共に API で検証することもできます。 このシナリオのアプリ ロールの検証ロジックは、ユーザー/グループとアプリケーションのロール要求に区別がないため、デーモン アプリによって API が呼び出された場合と同じです。

#### Web API がデーモン アプリのみによって呼び出される必要がある場合のアプリ専用トークンの受け入れ

デーモン アプリのみが Web API を呼び出せるようにしたい場合は、アプリ ロールを検証するときに、トークンがアプリ専用トークンであるという条件を追加します。

```csharp
string oid = ClaimsPrincipal.Current.FindFirst("oid")?.Value;
string sub = ClaimsPrincipal.Current.FindFirst("sub")?.Value;
bool isAppOnly = oid != null && sub != null && oid == sub;
```

逆の条件をチェックすると、ユーザーとしてサインインするアプリのみが API を呼び出すことができます。

#### ACL ベースの認可の使用

アプリロール ベースの認可の代わりに、[`roles` 要求のないトークンを制御する](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow#controlling-tokens-without-the-roles-claim)ためのアクセス制御リスト (ACL) ベースの認可パターンで Web API を保護することができます。

ASP.NET Core で `Microsoft.Identity.Web` を使用している場合は、ACL ベースの承認を使用していることを宣言する必要があります。それ以外の場合、指定された要求にロールもスコープも含まれていない場合、Microsoft Identity Web は例外をスローします。

```text
System.UnauthorizedAccessException: IDW10201: Neither scope or roles claim was found in the bearer token.
```

この例外を回避するには、`AllowWebApiToBeAuthorizedByACL` の `true` 構成プロパティをプログラムで  に設定します。

```JSON
{
 "AzureAD"
 {
  // other properties
  "AllowWebApiToBeAuthorizedByACL" : true,
  // other properties
 }
}
```

`AllowWebApiToBeAuthorizedByACL` を `true` に設定した場合、ACL メカニズムを確保するのは**自分の責任**になります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-spa-acquire-token"} -->
## Web API を呼び出すトークンを取得する (シングルページ アプリ) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-acquire-token
- Service: identity-platform / workforce
- Article date: 2025-05-12
- Summary: シングルページ アプリでトークンを取得し、Microsoft ID プラットフォームを使用して Web API を呼び出す方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

[MSAL.js](https://github.com/AzureAD/microsoft-authentication-library-for-js) を使用して API 用のトークンを取得するパターンは、`acquireTokenSilent` メソッドを使用してサイレント トークン要求を最初に試行することです。 このメソッドが呼び出されると、ライブラリでは、まずブラウザー ストレージ内のキャッシュを調べて、期限切れでないアクセス トークンが存在するかどうかを確認し、それを返します。 アクセス トークンが見つからないか、見つかったアクセス トークンの有効期限が切れている場合は、その更新トークンを使用して新しいアクセス トークンを取得しようとします。 更新トークンの 24 時間の有効期間も期限切れになった場合、MSAL.js は非表示の iframe を開き、Microsoft Entra ID との既存のアクティブなセッション (存在する場合) を利用して新しい認可コードをサイレントに要求します。その後、これは新しいトークン セット (アクセス*および*更新トークン) と交換されます。

シングル サインオン (SSO) セッションおよび Microsoft Entra ID 内のトークン有効期間値の詳細については、[トークンの有効期間](https://learn.microsoft.com/ja-jp/entra/identity-platform/configurable-token-lifetimes)に関するページを参照してください。 MSAL.js キャッシュ検索ポリシーの詳細については、「[アクセス トークンの取得](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/acquire-token.md#acquiring-an-access-token)」を参照してください。

Microsoft Entra ID へのサイレント トークン要求は、パスワードの変更や条件付きアクセス ポリシーの更新などの理由により、失敗する場合があります。 エラーは多くの場合、更新トークンの 24 時間の有効期限が切れ、[ブラウザーがサード パーティの Cookie をブロックしている](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-third-party-cookies-spas)ことが原因で発生します。この場合、非表示の iframe を使用してユーザーの認証を継続することができなくなります。 このような場合は、トークンを取得するために、(ユーザーにプロンプトを表示できる) 対話型のメソッドのいずれかを呼び出す必要があります。

- ポップアップ ウィンドウを`acquireTokenPopup` 使用する
- を使用して`acquireTokenRedirect`する

### ポップアップ エクスペリエンスか、リダイレクト エクスペリエンスを選択

ポップアップまたはリダイレクト エクスペリエンスのいずれを選択するかは、ご利用のアプリケーション フローに依存します。

- 認証中にユーザーにメイン アプリケーション ページから移動してほしくない場合は、ポップアップ メソッドをお勧めします。 認証リダイレクトはポップアップ ウィンドウで行われるため、メイン アプリケーションの状態は保持されます。
- ポップアップウィンドウが無効になっているブラウザの制約またはポリシーがある場合は、リダイレクト方法を使用できます。 [Internet Explorer のポップアップ ウィンドウには既知の問題](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-js-known-issues-ie-edge-browsers) があるので、Internet Explorer ブラウザーでは、リダイレクト メソッドを使用してください。

アクセス トークン要求を作成するときにアクセス トークンに含める API スコープを設定することができます。 要求されたすべてのスコープがアクセス トークンに付与されるとは限りません。 これは、ユーザーの同意によって異なります。

### ポップアップ ウィンドウを使用してトークンを取得する

## [反応する](#tab/react)
次のコードでは、前に説明したパターンと、ポップアップ エクスペリエンスのメソッドを組み合わせています。

```javascript
import {
  InteractionRequiredAuthError,
  InteractionStatus,
} from "@azure/msal-browser";
import { AuthenticatedTemplate, useMsal } from "@azure/msal-react";

function ProtectedComponent() {
  const { instance, inProgress, accounts } = useMsal();
  const [apiData, setApiData] = useState(null);

  useEffect(() => {
    if (!apiData && inProgress === InteractionStatus.None) {
      const accessTokenRequest = {
        scopes: ["user.read"],
        account: accounts[0],
      };
      instance
        .acquireTokenSilent(accessTokenRequest)
        .then((accessTokenResponse) => {
          // Acquire token silent success
          let accessToken = accessTokenResponse.accessToken;
          // Call your API with token
          callApi(accessToken).then((response) => {
            setApiData(response);
          });
        })
        .catch((error) => {
          if (error instanceof InteractionRequiredAuthError) {
            instance
              .acquireTokenPopup(accessTokenRequest)
              .then(function (accessTokenResponse) {
                // Acquire token interactive success
                let accessToken = accessTokenResponse.accessToken;
                // Call your API with token
                callApi(accessToken).then((response) => {
                  setApiData(response);
                });
              })
              .catch(function (error) {
                // Acquire token interactive failure
                console.log(error);
              });
          }
          console.log(error);
        });
    }
  }, [instance, accounts, inProgress, apiData]);

  return <p>Return your protected content here: {apiData}</p>;
}

function App() {
  return (
    <AuthenticatedTemplate>
      <ProtectedComponent />
    </AuthenticatedTemplate>
  );
}
```

または、React コンポーネントの外部でトークンを取得する必要がある場合は、`acquireTokenSilent` を呼び出すことはできますが、失敗した場合には対話にフォールバックしないでください。 コンポーネント ツリーの `MsalProvider` コンポーネントの下で、すべての対話が行われます。

```javascript
// MSAL.js v2 exposes several account APIs, logic to determine which account to use is the responsibility of the developer
const account = publicClientApplication.getAllAccounts()[0];

const accessTokenRequest = {
  scopes: ["user.read"],
  account: account,
};

// Use the same publicClientApplication instance provided to MsalProvider
publicClientApplication
  .acquireTokenSilent(accessTokenRequest)
  .then(function (accessTokenResponse) {
    // Acquire token silent success
    let accessToken = accessTokenResponse.accessToken;
    // Call your API with token
    callApi(accessToken);
  })
  .catch(function (error) {
    //Acquire token silent failure
    console.log(error);
  });
```

## [JavaScript (MSAL.js)](#tab/javascript)
次のコードでは、前に説明したパターンと、ポップアップ エクスペリエンスのメソッドを組み合わせています。

```javascript
// MSAL.js v2 exposes several account APIs, logic to determine which account to use is the responsibility of the developer
const account = publicClientApplication.getAllAccounts()[0];

const accessTokenRequest = {
  scopes: ["user.read"],
  account: account,
};

publicClientApplication
  .acquireTokenSilent(accessTokenRequest)
  .then(function (accessTokenResponse) {
    // Acquire token silent success
    let accessToken = accessTokenResponse.accessToken;
    // Call your API with token
    callApi(accessToken);
  })
  .catch(function (error) {
    //Acquire token silent failure, and send an interactive request
    if (error instanceof InteractionRequiredAuthError) {
      publicClientApplication
        .acquireTokenPopup(accessTokenRequest)
        .then(function (accessTokenResponse) {
          // Acquire token interactive success
          let accessToken = accessTokenResponse.accessToken;
          // Call your API with token
          callApi(accessToken);
        })
        .catch(function (error) {
          // Acquire token interactive failure
          console.log(error);
        });
    }
    console.log(error);
  });
```

## [Angular (MSAL.js)](#tab/angular)
MSAL Angular ラッパーを使用すると、自動的にアクセス トークンをサイレントに取得して API への HTTP 要求にそれを添付する HTTP インターセプターが提供されます。

API のスコープは、`protectedResourceMap` 構成オプションで指定できます。 `MsalInterceptor` は、トークンを自動的に取得するときに指定されたスコープを要求します。

```javascript
// In app.module.ts
import { PublicClientApplication, InteractionType } from "@azure/msal-browser";
import { MsalInterceptor, MsalModule } from "@azure/msal-angular";

@NgModule({
  declarations: [
    // ...
  ],
  imports: [
    // ...
    MsalModule.forRoot(
      new PublicClientApplication({
        auth: {
          clientId: "Enter_the_Application_Id_Here",
        },
        cache: {
          cacheLocation: "localStorage",
          storeAuthStateInCookie: isIE,
        },
      }),
      {
        interactionType: InteractionType.Popup,
        authRequest: {
          scopes: ["user.read"],
        },
      },
      {
        interactionType: InteractionType.Popup,
        protectedResourceMap: new Map([
          ["https://graph.microsoft.com/v1.0/me", ["user.read"]],
        ]),
      }
    ),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: MsalInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
```

サイレント トークン取得の成功と失敗について、MSAL Angular からサブスクライブできるイベントが提供されます。 サブスクライブ解除を忘れずに行うことも重要です。

```javascript
import { MsalBroadcastService } from '@azure/msal-angular';
import { EventMessage, EventType } from '@azure/msal-browser';

import { filter, Subject, takeUntil } from 'rxjs';

// In app.component.ts
export class AppComponent implements OnInit {
  private readonly _destroying$ = new Subject<void>();

  constructor(private broadcastService: MsalBroadcastService) { }

  ngOnInit() {
    this.broadcastService.msalSubject$
    .pipe(
      filter((msg: EventMessage) => msg.eventType === EventType.ACQUIRE_TOKEN_SUCCESS),
      takeUntil(this._destroying$)
    )
    .subscribe((result: EventMessage) => {
      // Do something with event payload here
    });
  }

  ngOnDestroy(): void {
    this._destroying$.next(undefined);
    this._destroying$.complete();
  }
}
```

あるいは、コア MSAL.js ライブラリ内に記述されているように、取得トークン メソッドを使用してトークンを明示的に取得することができます。

---

### リダイレクトを使用してトークンを取得する

## [反応する](#tab/react)
`acquireTokenSilent` が失敗した場合は、`acquireTokenRedirect` にフォールバックします。 この方法により、フルフレーム リダイレクトが開始され、応答はアプリケーションに戻るときに処理されます。 リダイレクトから戻った後にこのコンポーネントがレンダリングされるときに、トークンがキャッシュからプルされるため、`acquireTokenSilent` は成功します。

```javascript
import {
  InteractionRequiredAuthError,
  InteractionStatus,
} from "@azure/msal-browser";
import { AuthenticatedTemplate, useMsal } from "@azure/msal-react";

function ProtectedComponent() {
  const { instance, inProgress, accounts } = useMsal();
  const [apiData, setApiData] = useState(null);

  useEffect(() => {
    const accessTokenRequest = {
      scopes: ["user.read"],
      account: accounts[0],
    };
    if (!apiData && inProgress === InteractionStatus.None) {
      instance
        .acquireTokenSilent(accessTokenRequest)
        .then((accessTokenResponse) => {
          // Acquire token silent success
          let accessToken = accessTokenResponse.accessToken;
          // Call your API with token
          callApi(accessToken).then((response) => {
            setApiData(response);
          });
        })
        .catch((error) => {
          if (error instanceof InteractionRequiredAuthError) {
            instance.acquireTokenRedirect(accessTokenRequest);
          }
          console.log(error);
        });
    }
  }, [instance, accounts, inProgress, apiData]);

  return <p>Return your protected content here: {apiData}</p>;
}

function App() {
  return (
    <AuthenticatedTemplate>
      <ProtectedComponent />
    </AuthenticatedTemplate>
  );
}
```

または、React コンポーネントの外部でトークンを取得する必要がある場合は、`acquireTokenSilent` を呼び出すことはできますが、失敗した場合には対話にフォールバックしないでください。 コンポーネント ツリーの `MsalProvider` コンポーネントの下で、すべての対話が行われます。

```javascript
// MSAL.js v2 exposes several account APIs, logic to determine which account to use is the responsibility of the developer
const account = publicClientApplication.getAllAccounts()[0];

const accessTokenRequest = {
  scopes: ["user.read"],
  account: account,
};

// Use the same publicClientApplication instance provided to MsalProvider
publicClientApplication
  .acquireTokenSilent(accessTokenRequest)
  .then(function (accessTokenResponse) {
    // Acquire token silent success
    let accessToken = accessTokenResponse.accessToken;
    // Call your API with token
    callApi(accessToken);
  })
  .catch(function (error) {
    //Acquire token silent failure
    console.log(error);
  });
```

## [JavaScript (MSAL.js)](#tab/javascript)
次のパターンは前述したとおりですが、トークンを対話形式で取得するリダイレクト メソッドを使用して示しています。 ページの読み込み時に `handleRedirectPromise` を呼び出して待つ必要があります。

```javascript
const redirectResponse = await publicClientApplication.handleRedirectPromise();
if (redirectResponse !== null) {
  // Acquire token silent success
  let accessToken = redirectResponse.accessToken;
  // Call your API with token
  callApi(accessToken);
} else {
  // MSAL.js v2 exposes several account APIs, logic to determine which account to use is the responsibility of the developer
  const account = publicClientApplication.getAllAccounts()[0];

  const accessTokenRequest = {
    scopes: ["user.read"],
    account: account,
  };

  publicClientApplication
    .acquireTokenSilent(accessTokenRequest)
    .then(function (accessTokenResponse) {
      // Acquire token silent success
      // Call API with token
      let accessToken = accessTokenResponse.accessToken;
      // Call your API with token
      callApi(accessToken);
    })
    .catch(function (error) {
      //Acquire token silent failure, and send an interactive request
      console.log(error);
      if (error instanceof InteractionRequiredAuthError) {
        publicClientApplication.acquireTokenRedirect(accessTokenRequest);
      }
    });
}
```

### 省略可能な要求を要請する

次の目的で省略可能な要求を使用できます。

- アプリケーションのトークンに追加の要求を含める。
- Microsoft Entra ID からトークンで返される特定の要求の動作を変更する。
- アプリケーションのカスタムの要求を追加してアクセスする。

`IdToken` で省略可能な要求を要請するには、`claimsRequest` クラスの `AuthenticationParameters.ts` フィールドに文字列化された要求オブジェクトを送信します。

```javascript
var claims = {
  optionalClaims: {
    idToken: [
      {
        name: "auth_time",
        essential: true,
      },
    ],
  },
};

var request = {
  scopes: ["user.read"],
  claimsRequest: JSON.stringify(claims),
};

myMSALObj.acquireTokenPopup(request);
```

詳細については、[省略可能な要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)に関するページを参照してください。

## [Angular (MSAL.js)](#tab/angular)
このコードは、リダイレクトを処理するために `MsalRedirectComponent` をブートストラップすることを推奨していることを除いて、前に説明したものと同じです。 リダイレクトを使用するように `MsalInterceptor` 構成を変更することもできます。

```javascript
// In app.module.ts
import { PublicClientApplication, InteractionType } from "@azure/msal-browser";
import {
  MsalInterceptor,
  MsalModule,
  MsalRedirectComponent,
} from "@azure/msal-angular";

@NgModule({
  declarations: [
    // ...
  ],
  imports: [
    // ...
    MsalModule.forRoot(
      new PublicClientApplication({
        auth: {
          clientId: "Enter_the_Application_Id_Here",
        },
        cache: {
          cacheLocation: "localStorage",
          storeAuthStateInCookie: isIE,
        },
      }),
      {
        interactionType: InteractionType.Redirect,
        authRequest: {
          scopes: ["user.read"],
        },
      },
      {
        interactionType: InteractionType.Redirect,
        protectedResourceMap: new Map([
          ["https://graph.microsoft.com/v1.0/me", ["user.read"]],
        ]),
      }
    ),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: MsalInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent, MsalRedirectComponent],
})
export class AppModule {}
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-spa-app-configuration"} -->
## シングルページ アプリの構成 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-app-configuration
- Service: identity-platform / workforce
- Article date: 2025-05-12
- Summary: シングルページ アプリケーション (アプリのコード構成) を構築する方法について説明します

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

ご利用のシングルページ アプリケーション (SPA) のコードを構成する方法について説明します。

### [前提条件]

- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **シングルページ アプリケーション** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost:3000/`。

### シングルページ アプリをサポートする Microsoft ライブラリ

次の Microsoft ライブラリはシングルページ アプリをサポートしています。

| 言語/フレームワーク | プロジェクトGitHub | Package | 取得started | ユーザーのサインイン | Web API へのアクセス |
| --- | --- | --- | --- | --- | --- |
| 反応する | [MSAL React](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-react)^2^ | [msal-react](https://www.npmjs.com/package/@azure/msal-react) | [クイック スタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] |
| JavaScript | [MSAL.js](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-browser)^2^ | [msal-browser](https://www.npmjs.com/package/@azure/msal-browser) | [クイック スタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] |
| Angular（アンギュラー） | [MSAL Angular](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular)^2^ | [msal-angular](https://www.npmjs.com/package/@azure/msal-angular) | [クイック スタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] |

### アプリケーションのコード構成

MSAL ライブラリでは、ライブラリの初期化中にアプリケーションの登録情報が構成として渡されます。

## [反応する](#tab/react)
```javascript
import { PublicClientApplication } from "@azure/msal-browser";
import { MsalProvider } from "@azure/msal-react";

// Configuration object constructed.
const config = {
    auth: {
        clientId: 'your_client_id'
    }
};

// create PublicClientApplication instance
const publicClientApplication = new PublicClientApplication(config);

// Wrap your app component tree in the MsalProvider component
ReactDOM.render(
    <React.StrictMode>
        <MsalProvider instance={publicClientApplication}>
            <App />
        </ MsalProvider>
    </React.StrictMode>,
    document.getElementById('root')
);
```

## [JavaScript](#tab/javascript2)
```javascript
import * as Msal from "@azure/msal-browser"; // if using CDN, 'Msal' will be available in global scope

// Configuration object constructed.
const config = {
    auth: {
        clientId: 'your_client_id'
    }
};

// create PublicClientApplication instance
const publicClientApplication = new Msal.PublicClientApplication(config);
```

## [角度](#tab/angular2)
```javascript
// In app.module.ts
import { MsalModule } from '@azure/msal-angular';
import { PublicClientApplication } from '@azure/msal-browser';

@NgModule({
    imports: [
        MsalModule.forRoot( new PublicClientApplication({
            auth: {
                clientId: 'Enter_the_Application_Id_Here',
            }
        }), null, null)
    ]
})
export class AppModule { }
```

---

構成可能なオプションの詳細については、[MSAL.js を使用したアプリケーションの初期化](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-js-initializing-client-applications)に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-spa-call-api"} -->
## Web API を呼び出すシングル ページ アプリを作成する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-call-api
- Service: identity-platform / workforce
- Article date: 2025-05-12
- Summary: Web API を呼び出すシングル ページ アプリケーションをビルドする方法について学ぶ

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Web API を呼び出す前に、`acquireTokenSilent` メソッドを呼び出してアクセス トークンを取得または更新することをお勧めします。 トークンを取得した後に、保護された Web API を呼び出せます。

### Web API を呼び出す

## [JavaScript](#tab/javascript)
取得したアクセス トークンを HTTP 要求のベアラーとして使用して、Microsoft Graph API などの任意の Web API を呼び出します。 次に例を示します。

```javascript
    var headers = new Headers();
    var bearer = "Bearer " + access_token;
    headers.append("Authorization", bearer);
    var options = {
         method: "GET",
         headers: headers
    };
    var graphEndpoint = "https://graph.microsoft.com/v1.0/me";

    fetch(graphEndpoint, options)
        .then(function (response) {
             //do something with response
        })
```

## [角度](#tab/angular)
MSAL Angular ラッパーを使用すると、自動的にアクセス トークンをサイレントに取得して API への HTTP 要求にそれを添付するために HTTP インターセプターを利用できます。 詳細については、[API を呼び出すためのトークンの取得](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-acquire-token)に関するページを参照してください。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-spa-sign-in"} -->
## シングルページ アプリのサインインとサインアウト コード - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-sign-in
- Service: identity-platform / workforce
- Article date: 2025-05-12
- Summary: シングルページ アプリケーション (サインイン) を構築する方法を学習する

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

アプリケーション内の API にアクセスするトークンを取得する前に、認証されたユーザー コンテキストが必要です。 ユーザーを認証するために、ポップアップ ウィンドウやリダイレクト サインイン メソッドを使用することができます。

認証されたユーザー コンテキストまたは ID トークンに、ご利用のアプリケーションからアクセスできる場合は、サインイン手順をスキップして、直接トークンを取得することができます。 詳細については、[ユーザー ヒントを使用するシングル サインオン (SSO)](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-js-sso#with-user-hint) に関するページを参照してください。

### ポップアップ エクスペリエンスか、リダイレクト エクスペリエンスを選択

ポップアップまたはリダイレクト エクスペリエンスのいずれを選択するかは、ご利用のアプリケーション フローに依存します。

- 認証中にユーザーにメイン アプリケーション ページから移動してほしくない場合は、ポップアップ ウィンドウを使用します。 認証リダイレクトはポップアップ ウィンドウで行われるため、メイン アプリケーションの状態は保持されます。
- ユーザーのブラウザーに制約またはポリシーがあり、ポップアップ ウィンドウが無効になっている場合は、リダイレクトを使用します。 たとえば、[Internet Explorer のポップアップ ウィンドウには既知の問題](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-js-use-ie-browser)があります。

### ポップアップ ウィンドウを使用してサインインする

## [反応する](#tab/react)
ユーザーがまだサインインしていないときにサインイン エクスペリエンスを呼び出すには、`MsalAuthenticationTemplate` の `@azure/msal-react` 関数を使用します。 MSAL React ラッパーでは、特定のコンポーネントを `MsalAuthenticationTemplate` コンポーネントでラップすることによって保護します。

```javascript
import { InteractionType } from "@azure/msal-browser";
import { MsalAuthenticationTemplate, useMsal } from "@azure/msal-react";

function WelcomeUser() {
  const { accounts } = useMsal();
  const username = accounts[0].username;

  return <p>Welcome, {username}</p>;
}

// Remember that MsalProvider must be rendered somewhere higher up in the component tree
function App() {
  return (
    <MsalAuthenticationTemplate interactionType={InteractionType.Popup}>
      <p>This will only render if a user is not signed-in.</p>
      <WelcomeUser />
    </MsalAuthenticationTemplate>
  );
}
```

ユーザー操作 (ボタンの選択など) に基づいて特定のサインイン エクスペリエンスを呼び出すには、`AuthenticatedTemplate` の `UnauthenticatedTemplate` や `@azure/msal-react` 関数を使用します。

```javascript
import {
  useMsal,
  AuthenticatedTemplate,
  UnauthenticatedTemplate,
} from "@azure/msal-react";

function signInClickHandler(instance) {
  instance.loginPopup();
}

// SignInButton Component returns a button that invokes a popup sign in when clicked
function SignInButton() {
  // useMsal hook will return the PublicClientApplication instance you provided to MsalProvider
  const { instance } = useMsal();

  return <button onClick={() => signInClickHandler(instance)}>Sign In</button>;
}

function WelcomeUser() {
  const { accounts } = useMsal();
  const username = accounts[0].username;

  return <p>Welcome, {username}</p>;
}

// Remember that MsalProvider must be rendered somewhere higher up in the component tree
function App() {
  return (
    <>
      <AuthenticatedTemplate>
        <p>This will only render if a user is signed-in.</p>
        <WelcomeUser />
      </AuthenticatedTemplate>
      <UnauthenticatedTemplate>
        <p>This will only render if a user is not signed-in.</p>
        <SignInButton />
      </UnauthenticatedTemplate>
    </>
  );
}
```

## [JavaScript (MSAL.js)](#tab/javascript)
```javascript
const config = {
  auth: {
    clientId: "your_app_id",
    redirectUri: "your_app_redirect_uri", //defaults to application start page
    postLogoutRedirectUri: "your_app_logout_redirect_uri",
  },
};

const loginRequest = {
  scopes: ["User.ReadWrite"],
};

let accountId = "";

const myMsal = new PublicClientApplication(config);

myMsal
  .loginPopup(loginRequest)
  .then(function (loginResponse) {
    accountId = loginResponse.account.homeAccountId;
    // Display signed-in user content, call API, etc.
  })
  .catch(function (error) {
    //login failure
    console.log(error);
  });
```

## [Angular (MSAL.js)](#tab/angular)
特定のルートのサインイン エクスペリエンスを呼び出すには、`@angular/router` をインポートし、ルート定義に `MsalGuard` を追加します。

```javascript
// In app-routing.module.ts
import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { ProfileComponent } from "./profile/profile.component";
import { MsalGuard } from "@azure/msal-angular";
import { HomeComponent } from "./home/home.component";

const routes: Routes = [
  {
    path: "profile",
    component: ProfileComponent,
    canActivate: [MsalGuard],
  },
  {
    path: "",
    component: HomeComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: false })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
```

ポップアップ ウィンドウ エクスペリエンスを有効にするには、`interactionType` で `InteractionType.Popup` 構成を `MsalGuardConfiguration` に設定します。 同意を必要とするスコープを渡すこともできます。

```javascript
// In app.module.ts
import { PublicClientApplication, InteractionType } from "@azure/msal-browser";
import { MsalModule } from "@azure/msal-angular";

@NgModule({
  imports: [
    MsalModule.forRoot(
      new PublicClientApplication({
        auth: {
          clientId: "your_app_id",
        },
        cache: {
          cacheLocation: "localStorage",
          storeAuthStateInCookie: isIE,
        },
      }),
      {
        interactionType: InteractionType.Popup, // MsalGuard Configuration
        authRequest: {
          scopes: ["User.Read"],
        },
      },
      null
    ),
  ],
})
export class AppModule {}
```

---

### リダイレクトを使用してサインインする

## [反応する](#tab/react)
ユーザーがサインインしていないときにサインイン エクスペリエンスを呼び出すには、`MsalAuthenticationTemplate` の `@azure/msal-react` 関数を使用します。

```javascript
import { InteractionType } from "@azure/msal-browser";
import { MsalAuthenticationTemplate, useMsal } from "@azure/msal-react";

function WelcomeUser() {
  const { accounts } = useMsal();
  const username = accounts[0].username;

  return <p>Welcome, {username}</p>;
}

// Remember that MsalProvider must be rendered somewhere higher up in the component tree
function App() {
  return (
    <MsalAuthenticationTemplate interactionType={InteractionType.Redirect}>
      <p>This will only render if a user is not signed-in.</p>
      <WelcomeUser />
    </MsalAuthenticationTemplate>
  );
}
```

ユーザー操作 (ボタンの選択など) に基づいて特定のサインイン エクスペリエンスを呼び出すには、`AuthenticatedTemplate` の `UnauthenticatedTemplate` や `@azure/msal-react` 関数を使用します。

```javascript
import {
  useMsal,
  AuthenticatedTemplate,
  UnauthenticatedTemplate,
} from "@azure/msal-react";

function signInClickHandler(instance) {
  instance.loginRedirect();
}

// SignInButton Component returns a button that invokes a popup login when clicked
function SignInButton() {
  // useMsal hook will return the PublicClientApplication instance you provided to MsalProvider
  const { instance } = useMsal();

  return <button onClick={() => signInClickHandler(instance)}>Sign In</button>;
}

function WelcomeUser() {
  const { accounts } = useMsal();
  const username = accounts[0].username;

  return <p>Welcome, {username}</p>;
}

// Remember that MsalProvider must be rendered somewhere higher up in the component tree
function App() {
  return (
    <>
      <AuthenticatedTemplate>
        <p>This will only render if a user is signed-in.</p>
        <WelcomeUser />
      </AuthenticatedTemplate>
      <UnauthenticatedTemplate>
        <p>This will only render if a user is not signed-in.</p>
        <SignInButton />
      </UnauthenticatedTemplate>
    </>
  );
}
```

## [JavaScript (MSAL.js)](#tab/javascript)
```javascript
const config = {
  auth: {
    clientId: "your_app_id",
    redirectUri: "your_app_redirect_uri", //defaults to application start page
    postLogoutRedirectUri: "your_app_logout_redirect_uri",
  },
};

const loginRequest = {
  scopes: ["User.ReadWrite"],
};

let accountId = "";

const myMsal = new PublicClientApplication(config);

function handleResponse(response) {
  if (response !== null) {
    accountId = response.account.homeAccountId;
    // Display signed-in user content, call API, etc.
  } else {
    // In case multiple accounts exist, you can select
    const currentAccounts = myMsal.getAllAccounts();

    if (currentAccounts.length === 0) {
      // no accounts signed-in, attempt to sign a user in
      myMsal.loginRedirect(loginRequest);
    } else if (currentAccounts.length > 1) {
      // Add choose account code here
    } else if (currentAccounts.length === 1) {
      accountId = currentAccounts[0].homeAccountId;
    }
  }
}

myMsal.handleRedirectPromise().then(handleResponse);
```

## [Angular (MSAL.js)](#tab/angular)
リダイレクト エクスペリエンスを有効にするには、`interactionType` で `InteractionType.Redirect` 構成を `MsalGuardConfiguration` に設定し、リダイレクトを処理するために `MsalRedirectComponent` をブートストラップします。

```javascript
// In app.module.ts
import { PublicClientApplication, InteractionType } from "@azure/msal-browser";
import { MsalModule, MsalRedirectComponent } from "@azure/msal-angular";

@NgModule({
  imports: [
    MsalModule.forRoot(
      new PublicClientApplication({
        auth: {
          clientId: "Enter_the_Application_Id_Here",
        },
        cache: {
          cacheLocation: "localStorage",
          storeAuthStateInCookie: isIE,
        },
      }),
      {
        interactionType: InteractionType.Redirect, // Msal Guard Configuration
        authRequest: {
          scopes: ["user.read"],
        },
      },
      null
    ),
  ],
  bootstrap: [AppComponent, MsalRedirectComponent],
})
export class AppModule {}
```

---

### ブラウザーでのサインアウト動作

確実に 1 つまたは複数のアプリから安全にサインアウトするには、次のメソッドをお勧めします。

- 共有デバイスでは、ユーザーはブラウザーのプライベート/シークレット モードを使用し、デバイスから離れる前にすべてのブラウザー ウィンドウを閉じる必要があります。
- 共有されていないデバイスでは、ユーザーはオペレーティング システムのロック画面を使用して、デバイス上のオペレーティング システム セッション全体をロックまたはサインアウトする必要があります。 Microsoft ではサインアウト ページを使用して、これらのプライバシーとセキュリティのベスト プラクティスをユーザーに通知します。

詳細については、Microsoft の [インターネット プライバシーに関するベスト プラクティス](https://support.microsoft.com/en-us/windows/protect-your-privacy-on-the-internet-ffe36513-e208-7532-6f95-a3b1c8760dfa)を参照してください。

ユーザーが推奨事項を使用してサインアウトしないことを選択した場合に、サインアウト機能を有効にする他のメソッドを以下に示します。

- フェデレーション サインアウト用の Microsoft の OpenID Connect の[フロント チャネル ログアウト](https://openid.net/specs/openid-connect-frontchannel-1_0.html)。このオプションは、あるアプリが新しいアプリとサインイン状態を共有するものの、独自のセッション トークン/Cookie を管理する場合に使用できます。 ブラウザーでサードパーティの Cookie がブロックされる場合など、コンテンツがブロックされる場合、この実装にはいくつかの制限があります。
- ローカル アプリのサインアウト用のポップアップ ウィンドウやリダイレクト。ポップアップおよびリダイレクト メソッドでは、エンドポイントとローカル アプリでユーザーのセッションが終了します。 しかし、フロント チャネル通信がブロックされている場合、これらのメソッドでは他のフェデレーション アプリケーションのセッションがすぐにクリアされない可能性があります。

### ポップアップ ウィンドウを使用してサインアウトする

MSAL.js v2 以上には `logoutPopup` メソッドが用意されています。これにより、ブラウザー ストレージのキャッシュがクリアされ、Microsoft Entra サインアウト ページへのポップアップ ウィンドウが開かれます。 サインアウト後、リダイレクトは既定でサインインの開始ページに設定され、ポップアップは閉じられます。

サインアウト後のエクスペリエンスでは、ユーザーを特定の URI にリダイレクトするように `postLogoutRedirectUri` を設定できます。 この URI は、アプリケーションの登録でリダイレクト URI として登録する必要があります。 要求の一部として `logoutPopup` を渡すことによって、メイン ウィンドウを別のページ (ホーム ページやサインイン ページなど) にリダイレクトするように `mainWindowRedirectUri` を構成することもできます。

## [反応する](#tab/react)
```javascript
import {
  useMsal,
  AuthenticatedTemplate,
  UnauthenticatedTemplate,
} from "@azure/msal-react";

function signOutClickHandler(instance) {
  const logoutRequest = {
    account: instance.getAccountByHomeId(homeAccountId),
    mainWindowRedirectUri: "your_app_main_window_redirect_uri",
    postLogoutRedirectUri: "your_app_logout_redirect_uri",
  };
  instance.logoutPopup(logoutRequest);
}

// SignOutButton component returns a button that invokes a pop-up sign out when clicked
function SignOutButton() {
  // useMsal hook will return the PublicClientApplication instance you provided to MsalProvider
  const { instance } = useMsal();

  return (
    <button onClick={() => signOutClickHandler(instance)}>Sign Out</button>
  );
}

// Remember that MsalProvider must be rendered somewhere higher up in the component tree
function App() {
  return (
    <>
      <AuthenticatedTemplate>
        <p>This will only render if a user is signed-in.</p>
        <SignOutButton />
      </AuthenticatedTemplate>
      <UnauthenticatedTemplate>
        <p>This will only render if a user is not signed-in.</p>
      </UnauthenticatedTemplate>
    </>
  );
}
```

## [JavaScript (MSAL.js)](#tab/javascript)
```javascript
const config = {
  auth: {
    clientId: "your_app_id",
    redirectUri: "your_app_redirect_uri", // defaults to application start page
    postLogoutRedirectUri: "your_app_logout_redirect_uri",
  },
};

const myMsal = new PublicClientApplication(config);

// you can select which account application should sign out
const logoutRequest = {
  account: myMsal.getAccountByHomeId(homeAccountId),
  mainWindowRedirectUri: "your_app_main_window_redirect_uri",
};

await myMsal.logoutPopup(logoutRequest);
```

## [Angular (MSAL.js)](#tab/angular)
```javascript
// In app.module.ts
@NgModule({
    imports: [
        MsalModule.forRoot( new PublicClientApplication({
            auth: {
                clientId: 'your_app_id',
                postLogoutRedirectUri: 'your_app_logout_redirect_uri'
            }
        }), null, null)
    ]
})

// In app.component.ts
logout() {
    this.authService.logoutPopup({
        mainWindowRedirectUri: "/"
    });
}
```

---

### リダイレクトを使用してサインアウトする

MSAL.js は、ブラウザー ストレージ内のキャッシュをクリアし、Microsoft Entra サインアウト ページにリダイレクトする v2 の `logoutRedirect` メソッドを提供します。 サインアウト後、リダイレクトは既定でサインインの開始ページに設定されます。 サインアウト後のエクスペリエンスでは、ユーザーを特定の URI にリダイレクトするように `postLogoutRedirectUri` を設定できます。 この URI は、アプリケーションの登録でリダイレクト URI として登録する必要があります。

この方法では、プライベート ブラウザーとロック画面の使用に関する [インターネット プライバシーのベスト プラクティス](https://support.microsoft.com/en-us/windows/protect-your-privacy-on-the-internet-ffe36513-e208-7532-6f95-a3b1c8760dfa) に関する Microsoft のリマインダーが表示されないため、ベスト プラクティスを説明し、アプリからサインアウトしたときにすべてのブラウザー ウィンドウを閉じるようユーザーに通知することができます。

## [反応する](#tab/react)
```javascript
import {
  useMsal,
  AuthenticatedTemplate,
  UnauthenticatedTemplate,
} from "@azure/msal-react";

function signOutClickHandler(instance) {
  const logoutRequest = {
    account: instance.getAccountByHomeId(homeAccountId),
    postLogoutRedirectUri: "your_app_logout_redirect_uri",
  };
  instance.logoutRedirect(logoutRequest);
}

// SignOutButton Component returns a button that invokes a redirect logout when clicked
function SignOutButton() {
  // useMsal hook will return the PublicClientApplication instance you provided to MsalProvider
  const { instance } = useMsal();

  return (
    <button onClick={() => signOutClickHandler(instance)}>Sign Out</button>
  );
}

// Remember that MsalProvider must be rendered somewhere higher up in the component tree
function App() {
  return (
    <>
      <AuthenticatedTemplate>
        <p>This will only render if a user is signed-in.</p>
        <SignOutButton />
      </AuthenticatedTemplate>
      <UnauthenticatedTemplate>
        <p>This will only render if a user is not signed-in.</p>
      </UnauthenticatedTemplate>
    </>
  );
}
```

## [JavaScript (MSAL.js)](#tab/javascript)
```javascript
const config = {
  auth: {
    clientId: "your_app_id",
    redirectUri: "your_app_redirect_uri", //defaults to application start page
    postLogoutRedirectUri: "your_app_logout_redirect_uri",
  },
};

const myMsal = new PublicClientApplication(config);

// you can select which account application should sign out
const logoutRequest = {
  account: myMsal.getAccountByHomeId(homeAccountId),
};

myMsal.logoutRedirect(logoutRequest);
```

## [Angular (MSAL.js)](#tab/angular)
```javascript
// In app.module.ts
@NgModule({
    imports: [
        MsalModule.forRoot( new PublicClientApplication({
            auth: {
                clientId: 'your_app_id',
                postLogoutRedirectUri: 'your_app_logout_redirect_uri'
            }
        }), null, null)
    ]
})

// In app.component.ts
logout() {
    this.authService.logoutRedirect();
}
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-token-exchange-saml-oauth"} -->
## Microsoft Entra ID で SAML および OIDC/OAuth を使用した Microsoft ID プラットフォームのトークン交換のシナリオ - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-token-exchange-saml-oauth
- Service: identity-platform
- Article date: 2025-05-14
- Summary: Microsoft Entra ID で SAML および OIDC/OAuth を使用する場合の、一般的なトークン交換シナリオについて説明します。

SAML と OpenID Connect (OIDC) または OAuth は、シングル サインオン (SSO) を実装するために使用される一般的なプロトコルです。 一部のアプリでは SAML のみが実装され、その他のアプリでは OIDC または OAuth のみが実装されている場合があります。 どちらのプロトコルも、トークンを使用してシークレットを通信します。 SAML の詳細については、「[シングル サインオンの SAML プロトコル](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol)」を参照してください。 OIDC と OAuth の詳細については、「[Microsoft ID プラットフォームにおける OAuth 2.0 プロトコルと OpenID Connect プロトコル](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols)」を参照してください。

この記事では、アプリで SAML が実装されているが、OIDC または OAuth を使用する Graph API を呼び出す一般的なシナリオの概要について説明します。 このシナリオを使用するユーザー向けの基本的なガイダンスが提供されます。

### シナリオ:SAML トークンがあり、Graph API を呼び出す必要がある

多くのアプリが、SAML を使用して実装されています。 ただし、Graph API では、OIDC および OAuth のプロトコルが使用されます。 OIDC または OAuth の機能を SAML アプリに追加することは簡単ではありませんが、可能です。 OAuth 機能がアプリで使用できるようになると、Graph API を使用できます。

一般的な方法は、OIDC/OAuth スタックをアプリに追加することです。 両方の標準が実装されているアプリでは、セッション Cookie を使用できます。 トークンを明示的に交換しません。 SAML を使用してユーザーをログインさせます。これにより、セッション Cookie が生成されます。 Graph API により OAuth フローが呼び出されたときに、そのセッション Cookie を使用して認証を行います。 この方法で条件付きアクセスチェックに合格したと見なされ、ユーザーが承認されます。

注意

アプリケーションへの OIDC または OAuth 動作の追加に推奨されるライブラリは、[Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/msal/) です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-web-api-call-api-acquire-token"} -->
## Web API を呼び出す Web API のトークンを取得する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-api-call-api-acquire-token
- Service: identity-platform / workforce
- Article date: 2025-03-21
- Summary: 開発者として、アプリケーションで安全な API 呼び出しを有効にできるように、Web API のトークンを取得する方法を学習したいと考えています。  

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

クライアント アプリケーション オブジェクトを構築したら、それを使用して、Web API の呼び出しに使用できるトークンを取得します。

### コントローラーのコード

## [ASP.NET Core](#tab/aspnetcore)
*Microsoft.Identity.Web* には、Microsoft Graph またはダウンストリーム Web API を呼び出す便利なサービスを提供する拡張メソッドが追加されています。 これらのメソッドの詳細については、「[Web API を呼び出す Web アプリ: API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-call-api-call-api)」を参照してください。 これらのヘルパー メソッドを使用すれば、トークンを手動で取得する必要はありません。

ただし、トークンを手動で取得する場合は、ホーム コントローラーで取得することを目的とした *Microsoft.Identity.Web* の使用の例が、次のコードによって示されています。 これにより、REST API (Microsoft Graph SDK ではなく) を使用した、Microsoft Graph の呼び出しが行われます。 通常、トークンを取得する必要はありませんが、要求に追加する Authorization ヘッダーを作成する必要があります。 Authorization ヘッダーを取得するには、コントローラーのコンストラクター (Blazor を使用する場合はページ コンストラクター) 内での依存関係の挿入によって、`IAuthorizationHeaderProvider` サービスを挿入し、それをコントローラー アクションで使用します。 このインターフェイスには、プロトコル (Bearer や Pop など) とトークンを含む文字列を生成するメソッドが用意されています。 ユーザーに代わって API を呼び出す Authorization ヘッダーを取得するには、(`CreateAuthorizationHeaderForUserAsync`) を使用します。 アプリケーション自体に代わってダウンストリーム API を呼び出す Authorization ヘッダーを取得するには、デーモン シナリオで (`CreateAuthorizationHeaderForAppAsync`) を使用します。

これらのコントローラー メソッドは、`[Authorize]` 属性によって保護されていて、認証された呼び出しのみのに Web API の使用が確実に許可されます。

```csharp
[Authorize]
public class MyApiController : Controller
{
    /// <summary>
    /// The web API will accept only tokens 1) for users, 2) that have the `access_as_user` scope for
    /// this API.
    /// </summary>
    static readonly string[] scopeRequiredByApi = new string[] { "access_as_user" };

     static readonly string[] scopesToAccessDownstreamApi = new string[] { "api://MyTodolistService/access_as_user" };

     readonly IAuthorizationHeaderProvider authorizationHeaderProvider;

    public MyApiController(IAuthorizationHeaderProvider authorizationHeaderProvider)
    {
      this.authorizationHeaderProvider = authorizationHeaderProvider;
    }

    [RequiredScopes(Scopes = scopesToAccessDownstreamApi)]
    public IActionResult Index()
    {
        // Get an authorization header.
        IAuthorizationHeaderProvider authorizationHeaderProvider = this.GetAuthorizationHeaderProvider();
        string[] scopes = new string[]{"user.read"};
        string authorizationHeader = await authorizationHeaderProvider.CreateAuthorizationHeaderForUserAsync(scopes);

        return await callTodoListService(authorizationHeader);
    }
}
```

`callTodoListService` メソッドの詳細については、「[Web API を呼び出す Web API: API の呼び出し](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-api-call-api-call-api)」を参照してください。

## [ASP.NET](#tab/aspnet)
ASP.NET のコードは、ASP.NET Core 用に示したコードと似ています。

- [Authorize] 属性によって保護されたコントローラー アクションは、コントローラーの `ClaimsPrincipal` メンバーのテナント ID とユーザー ID を抽出します。 (ASP.NET では `HttpContext.User` を使用します) *Microsoft.Identity.Web.OWIN* では、Microsoft Graph またはダウンストリーム Web API を呼び出すための、あるいは Authorization ヘッダーを (またはトークンさえも) 取得するための便利なサービスを提供する拡張メソッドをコントローラーに追加します。 API を直接呼び出すのに使用されるメソッドの詳細については、「[Web API を呼び出す Web アプリ: API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-call-api-call-api)」を参照してください。 これらのヘルパー メソッドを使用すれば、トークンを手動で取得する必要はありません。

ただし、トークンの取得または Authorization ヘッダーの構築を手動で行いたい場合は、*Microsoft.Identity.Web* を使用してコントローラーでそれを行う方法を示す次のコードを参照してください。 これにより、Microsoft Graph SDK ではなく REST API を使用した、API (Microsoft Graph) の呼び出しが行われます。

Authorization ヘッダーを取得するには、拡張メソッド `IAuthorizationHeaderProvider` を使用してコントローラーから `GetAuthorizationHeaderProvider` サービスを取得します。 ユーザーに代わって API を呼び出す Authorization ヘッダーを取得するには、(`CreateAuthorizationHeaderForUserAsync`) を使用します。 アプリケーション自体に代わってダウンストリーム API を呼び出す Authorization ヘッダーを取得するには、デーモン シナリオで (`CreateAuthorizationHeaderForAppAsync`) を使用します。

コントローラー メソッドは、認証されたユーザーのみが Web アプリを使用できるようにする `[Authorize]` 属性によって保護されます。

次のスニペットは、`HomeController` のアクション (REST API として Microsoft Graph を呼び出す Authorization ヘッダーが取得されます) を示したものです。

```csharp
[Authorize]
public class MyApiController : Controller
{
 [AuthorizeForScopes(Scopes = new[] { "user.read" })]
 public async Task<IActionResult> Profile()
 {
  // Get an authorization header.
  IAuthorizationHeaderProvider authorizationHeaderProvider = this.GetAuthorizationHeaderProvider();
  string[] scopes = new string[]{"user.read"};
  string authorizationHeader = await authorizationHeaderProvider.CreateAuthorizationHeaderForUserAsync(scopes);

  // Use the access token to call a protected web API.
  HttpClient client = new HttpClient();
  client.DefaultRequestHeaders.Add("Authorization", authorizationHeader);
  string json = await client.GetStringAsync(url);
 }
}
```

次のスニペットは、`MyApiController` のアクション (REST API として Microsoft Graph を呼び出すアクセス トークンが取得されます) を示したものです。

```csharp
[Authorize]
public class HomeController : Controller
{
 [AuthorizeForScopes(Scopes = new[] { "user.read" })]
 public async Task<IActionResult> Profile()
 {
  // Get an authorization header.
  ITokenAcquirer tokenAcquirer = TokenAcquirerFactory.GetDefaultInstance().GetTokenAcquirer();
  string[] scopes = new string[]{"user.read"};
  string token = await await tokenAcquirer.GetTokenForUserAsync(scopes);

  // Use the access token to call a protected web API.
  HttpClient client = new HttpClient();
  client.DefaultRequestHeaders.Add("Authorization", $"Bearer {token}");
  string json = await client.GetStringAsync(url);
 }
}
```

## [ジャワ](#tab/java)
API コントローラーのアクションで呼び出されるコードの例を次に示します。 ダウンストリーム API - Microsoft Graph が呼び出されます。

```java
@RestController
public class ApiController {

    @Autowired
    MsalAuthHelper msalAuthHelper;

    @RequestMapping("/graphMeApi")
    public String graphMeApi() throws MalformedURLException {

        String oboAccessToken = msalAuthHelper.getOboToken("https://graph.microsoft.com/.default");

        return callMicrosoftGraphMeEndpoint(oboAccessToken);
    }

}
```

## [Python](#tab/python)
Python Web API では、クライアントから受信したベアラー トークンを検証するためにミドルウェアを使用する必要があります。 Web API は、[`acquire_token_on_behalf_of`](https://msal-python.readthedocs.io/en/latest/?badge=latest#msal.ConfidentialClientApplication.acquire_token_on_behalf_of) メソッドを呼び出すことにより、MSAL Python ライブラリを使用してダウンストリーム API のアクセス トークンを取得できます。

`acquire_token_on_behalf_of` メソッドと Flask フレームワークを使用してアクセス トークンを取得するコードの例を次に示します。 これはダウンストリーム API (Azure 管理サブスクリプション エンドポイント) を呼び出します。

```python
def get(self):
 
        _scopes = ["https://management.azure.com/user_impersonation"]
        _azure_management_subscriptions_uri = "https://management.azure.com/subscriptions?api-version=2020-01-01"
 
        current_access_token = request.headers.get("Authorization", None)
        
        #This example only uses the default memory token cache and should not be used for production
        msal_client = msal.ConfidentialClientApplication(
                client_id=os.environ.get("CLIENT_ID"),
                authority=os.environ.get("AUTHORITY"),
                client_credential=os.environ.get("CLIENT_SECRET"))
 
        #acquire token on behalf of the user that called this API
        arm_resource_access_token = msal_client.acquire_token_on_behalf_of(
            user_assertion=current_access_token.split(' ')[1],
            scopes=_scopes
        )
 
        headers = {'Authorization': arm_resource_access_token['token_type'] + ' ' + arm_resource_access_token['access_token']}
 
        subscriptions_list = req.get(_azure_management_subscriptions_uri), headers=headers).json()
 
        return jsonify(subscriptions_list)
```

### (詳細)バックグラウンド アプリ、API、およびサービスからサインインしているユーザーのトークン キャッシュにアクセスする

不在のユーザーに代わって操作を続けられるよう、アクセス トークン キャッシュの使用をバックグラウンドのアプリ、API、サービスに許可するため、MSAL のトークン キャッシュ実装を利用できます。 これは特に、ユーザーがフロントエンド Web アプリを終了した後、バックグラウンドのアプリやサービスがユーザーの代わりに作業を続けなければならない場合に便利です。

現在、ほとんどのバックグラウンド プロセスにおいて、ユーザーの不在時にユーザーのデータを使用して認証または再認証しなければならないとき、[アプリケーションのアクセス許可](https://learn.microsoft.com/ja-jp/graph/auth/auth-concepts#microsoft-graph-permissions)が使用されます。 アプリケーションのアクセス許可は多くの場合、特権の昇格を必要とする管理者の同意を必要とするため、不要な衝突が発生します。その理由は、開発者のアプリに対してユーザーが最初に同意した以上のアクセス権を取得することを開発者が意図しなかったことにあります。

GitHub にあるこのコード サンプルからは、バックグラウンド アプリから MSAL のトークン キャッシュにアクセスすることでこの不要な衝突を回避する方法を確認できます。

[バックグラウンドのアプリ、API、サービスからログイン ユーザーのトークン キャッシュにアクセスする](https://github.com/Azure-Samples/ms-identity-dotnet-advanced-token-cache)

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-web-api-call-api-app-configuration"} -->
## Web API を呼び出す Web API を構成する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-api-call-api-app-configuration
- Service: identity-platform / workforce
- Article date: 2024-07-19
- Summary: Web API を呼び出す Web API を構築する方法について説明します (アプリのコード構成)

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

この記事では、[OAuth 2.0 認証コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)を使用して、Web API アプリのコードを構成する方法について説明します。

ダウンストリーム Web API を呼び出す ASP.NET Core で保護された API を開発する場合は、[Microsoft.Identity.Web](https://www.nuget.org/packages/Microsoft.Identity.Web) NuGet パッケージを使用することをお勧めします。 「[保護された Web API: Web API のコンテキストでそのライブラリをすばやく表示するには、コード構成の「Microsoft.Identity.Web」](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-protected-web-api-app-configuration#microsoftidentityweb)を参照してください。

### 前提条件

- アクティブなサブスクリプションを持つ Azure アカウント。 [無料でアカウントを作成できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。 このアカウントには、アプリケーションを管理するためのアクセス許可が必要です。 アプリケーションを登録するために必要な次のロールのいずれかを使用します。
    - アプリケーション管理者
    - アプリケーション開発者
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- クライアント証明書をアプリ登録に追加します。 詳細については、「 [Microsoft Entra ID でのアプリケーション資格情報の追加と管理」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials)参照してください。

### アプリを設定する

Web API の言語を選択します。

## [ASP.NET Core](#tab/aspnetcore)
### クライアント シークレットまたはクライアント証明書

ご利用の Web アプリでダウンストリーム Web API を呼び出すことができるようになったため、クライアント シークレットまたはクライアント証明書を *appsettings.json* ファイルに指定してください。 次を指定するセクションを追加することもできます。

- ダウンストリーム Web API の URL
- API の呼び出しに必要なスコープ

次の例では、`GraphBeta` セクションでこれらの設定を指定しています。

```JSON
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "ClientId": "[Enter_the_Application_Id_Here]",
    "TenantId": "common",

   // To call an API
   "ClientCredentials": [
    {
      "SourceType": "ClientSecret",
      "ClientSecret":"[Enter_the_Client_Secret_Here]"
    }
  ]
 },
 "GraphBeta": {
    "BaseUrl": "https://graph.microsoft.com/beta",
    "Scopes": ["user.read"]
    }
}
```

注

Azure Kubernetes のワークロード ID フェデレーションのような資格情報のないソリューションなど、クライアント資格情報のコレクションを提案できます。 以前のバージョンの Microsoft.Identity.Web では、"ClientCredentials" ではなく、単一の "ClientSecret" プロパティでクライアント シークレットが表現されていました。 これは下位互換性のために引き続きサポートされていますが、"ClientSecret" プロパティと "ClientCredentials" コレクションの両方を使用することはできません。

クライアント シークレットの代わりに、クライアント証明書を指定することができます。 次のコード スニペットは、Azure Key Vault に格納されている証明書の使用を示しています。

```JSON
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "ClientId": "[Enter_the_Application_Id_Here]",
    "TenantId": "common",

   // To call an API
   "ClientCredentials": [
      {
        "SourceType": "KeyVault",
        "KeyVaultUrl": "https://msidentitywebsamples.vault.azure.net",
        "KeyVaultCertificateName": "MicrosoftIdentitySamplesCert"
      }
   ]
  },
  "GraphBeta": {
    "BaseUrl": "https://graph.microsoft.com/beta",
    "Scopes": ["user.read"]
  }
}
```

警告

`Scopes` を配列に変更することを忘れた場合、`IDownstreamApi` を使用しようとすると、スコープに null が表示され、`IDownstreamApi` はダウンストリーム API の匿名 (認証されていない) 呼び出しを試みます。その結果、`401/unauthenticated` が発生します。

*Microsoft.Identity.Web* では、構成およびコードで証明書を記述するいくつかの方法を提供しています。 詳細については、GitHub 上の「[Microsoft.Identity.Web - 証明書の使用](https://github.com/AzureAD/microsoft-identity-web/wiki/Using-certificates)」を参照してください。

### Program.cs

```csharp
using Microsoft.Identity.Web;

// ...
builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApi(Configuration, Configuration.GetSection("AzureAd"))
    .EnableTokenAcquisitionToCallDownstreamApi()
    .AddInMemoryTokenCaches();
// ...
```

Web API では、ダウンストリーム API のトークンを取得する必要があります。 これを指定するには、`.EnableTokenAcquisitionToCallDownstreamApi()` の後に `.AddMicrosoftIdentityWebApi(Configuration)` 行を追加します。 この行により、コントローラーまたはページのアクションで使用できる `ITokenAcquisition` サービスが公開されます。

ただし、別の方法として、トークン キャッシュを実装する方法もあります。 たとえば、`.AddInMemoryTokenCaches()` を *Program.cs* に追加すると、トークンをメモリにキャッシュできます。

```csharp
using Microsoft.Identity.Web;

// ...
builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApi(Configuration, Configuration.GetSection("AzureAd"))
    .EnableTokenAcquisitionToCallDownstreamApi()
    .AddInMemoryTokenCaches();
// ...
```

*Microsoft.Identity.Web* には、別の API からダウンストリーム Web API を呼び出すための 2 つのメカニズムが用意されています。 選択するオプションは、Microsoft Graph または別の API のどちらを呼び出すかによって異なります。

#### オプション 1: Microsoft Graph の呼び出し

Microsoft Graph を呼び出すために、*Microsoft.Identity.Web* では、(Microsoft Graph SDK で公開されている) `GraphServiceClient` を API アクションで直接使用できます。

注

Microsoft Graph SDK v5 以降には継続的な問題があります。 詳細については、[GitHub イシュー](https://github.com/AzureAD/microsoft-identity-web/issues/2097)を参照してください。

Microsoft Graph を公開するには、次の手順を実行します。

1. [Microsoft.Identity.Web.GraphServiceClient](https://www.nuget.org/packages/Microsoft.Identity.Web.GraphServiceClient) NuGet パッケージをプロジェクトに追加します。
2. `.AddMicrosoftGraph()` で `.EnableTokenAcquisitionToCallDownstreamApi()` の後に  を追加します。 `.AddMicrosoftGraph()` にはいくつかのオーバーライドがあります。 構成セクションをパラメーターとして受け取るオーバーライドを使用すると、コードは次のようになります。

```csharp
using Microsoft.Identity.Web;

// ...
builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApi(Configuration, Configuration.GetSection("AzureAd"))
    .EnableTokenAcquisitionToCallDownstreamApi()
    .AddMicrosoftGraph(Configuration.GetSection("GraphBeta"))
    .AddInMemoryTokenCaches();
// ...
```

#### オプション 2:Microsoft Graph 以外のダウンストリーム Web API を呼び出す

1. [Microsoft.Identity.Web.DownstreamApi](https://www.nuget.org/packages/Microsoft.Identity.Web.DownstreamApi) NuGet パッケージをプロジェクトに追加します。
2. `.AddDownstreamApi()` で `.EnableTokenAcquisitionToCallDownstreamApi()` の後に  を追加します。 コードは次のようになります。

```csharp
using Microsoft.Identity.Web;

// ...
builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApi(Configuration, "AzureAd")
    .EnableTokenAcquisitionToCallDownstreamApi()
    .AddDownstreamApi("MyApi", Configuration.GetSection("MyApiScope"))
    .AddInMemoryTokenCaches();
// ...
```

各要素の説明:

- `MyApi` は、Web API が呼び出す予定のダウンストリーム Web API の名前を示します
- `MyApiScope` は、ダウンストリーム Web API と対話するために、Web API の要求に必要なスコープです

これらの値は、次のスニペットのような JSON で表されます。

```json
"DownstreamAPI": {
      "BaseUrl": "https://downstreamapi.contoso.com/",
      "Scopes": "user.read"
    },
```

Web アプリで別の API リソースを呼び出す必要がある場合は、次のスニペットに示すように、関連するスコープで `.AddDownstreamApi()` メソッドを繰り返します。

```csharp
using Microsoft.Identity.Web;

// ...
builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddMicrosoftIdentityWebApi(Configuration, "AzureAd")
    .EnableTokenAcquisitionToCallDownstreamApi()
    .AddDownstreamApi("MyApi", Configuration.GetSection("MyApiScope"))
    .AddDownstreamApi("MyApi2", Configuration.GetSection("MyApi2Scope"))
    .AddInMemoryTokenCaches();
// ...
```

`.EnableTokenAcquisitionToCallDownstreamApi` はパラメーターなしで呼び出されることに注意してください。これにより、コントローラーがスコープを指定してトークンを要求すると、アクセス トークンがジャスト イン タイムで取得されます。

スコープは、`.EnableTokenAcquisitionToCallDownstreamApi` を呼び出すときに渡すこともできます。これにより、Web アプリは最初のユーザー ログイン自体の間にトークンを取得します。 その後、コントローラーが要求すると、トークンがキャッシュからプルされます。

Web アプリと同様に、さまざまなトークン キャッシュ実装を選択できます。 詳細については、GitHub の [Microsoft identity web - Token cache serialization](https://aka.ms/ms-id-web/token-cache-serialization) (トークン キャッシュのシリアル化) を参照してください。

次の図は、実現可能な *Microsoft.Identity.Web* と *Program.cs* への影響を示しています。

[Image: Web API を呼び出し、トークン キャッシュの実装を指定するための Startup.cs のサービス構成オプションを示すブロック図]

注

これらのコード例を完全に理解するために、[ASP.NET Core の基礎](https://learn.microsoft.com/ja-jp/aspnet/core/fundamentals/)、特に[依存関係の挿入](https://learn.microsoft.com/ja-jp/aspnet/core/fundamentals/dependency-injection)と[オプション](https://learn.microsoft.com/ja-jp/aspnet/core/fundamentals/configuration/options)についてよく理解してください。

## [ASP.NET](#tab/aspnet)
### クライアント シークレットまたはクライアント証明書

ご利用の Web アプリでダウンストリーム Web API を呼び出すことができるようになったため、クライアント シークレットまたはクライアント証明書を *appsettings.json* ファイルに指定してください。 次を指定するセクションを追加することもできます。

- ダウンストリーム Web API の URL
- API の呼び出しに必要なスコープ

次の例では、`GraphBeta` セクションでこれらの設定を指定しています。

```JSON
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "ClientId": "[Enter_the_Application_Id_Here]",
    "TenantId": "common",

   // To call an API
   "ClientCredentials": [
    {
      "SourceType": "ClientSecret",
      "ClientSecret":"[Enter_the_Client_Secret_Here]"
    }
  ]
 },
 "GraphBeta": {
    "BaseUrl": "https://graph.microsoft.com/beta",
    "Scopes": ["user.read"]
    }
}
```

注

Azure Kubernetes のワークロード ID フェデレーションのような資格情報のないソリューションなど、クライアント資格情報のコレクションを提案できます。 以前のバージョンの Microsoft.Identity.Web では、"ClientCredentials" ではなく、単一の "ClientSecret" プロパティでクライアント シークレットが表現されていました。 これは下位互換性のために引き続きサポートされていますが、"ClientSecret" プロパティと "ClientCredentials" コレクションの両方を使用することはできません。

クライアント シークレットの代わりに、クライアント証明書を指定することができます。 次のコード スニペットは、Azure Key Vault に格納されている証明書の使用を示しています。

```JSON
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "ClientId": "[Enter_the_Application_Id_Here]",
    "TenantId": "common",

   // To call an API
   "ClientCredentials": [
      {
        "SourceType": "KeyVault",
        "KeyVaultUrl": "https://msidentitywebsamples.vault.azure.net",
        "KeyVaultCertificateName": "MicrosoftIdentitySamplesCert"
      }
   ]
  },
  "GraphBeta": {
    "BaseUrl": "https://graph.microsoft.com/beta",
    "Scopes": ["user.read"]
  }
}
```

警告

`Scopes` を配列に変更することを忘れた場合、`IDownstreamApi` を使用しようとすると、スコープに null が表示され、`IDownstreamApi` はダウンストリーム API の匿名 (認証されていない) 呼び出しを試みます。その結果、`401/unauthenticated` が発生します。

*Microsoft.Identity.Web* では、構成およびコードで証明書を記述するいくつかの方法を提供しています。 詳細については、GitHub 上の「[Microsoft.Identity.Web - 証明書の使用](https://github.com/AzureAD/microsoft-identity-web/wiki/Using-certificates)」を参照してください。

### *Startup.Auth.cs* を変更する

Web アプリでダウンストリーム API のトークンを取得する必要があります。*Microsoft.Identity.Web* には、Web API からダウンストリーム API を呼び出すための 2 つのメカニズムが用意されています。 選択するオプションは、Microsoft Graph または別の API のどちらを呼び出すかによって異なります。

#### オプション 1: Microsoft Graph の呼び出し

Microsoft Graph を呼び出す場合は、*Microsoft.Identity.Web* を使用すると、API アクションで `GraphServiceClient` (Microsoft Graph SDK によって公開されている) を直接使用することができます。 Microsoft Graph を公開するには、次の手順を実行します。

1. [Microsoft.Identity.Web.GraphServiceClient](https://www.nuget.org/packages/Microsoft.Identity.Web.GraphServiceClient) NuGet パッケージをプロジェクトに追加します。
2. `.AddMicrosoftGraph()` を *Startup.Auth.cs* ファイル内のサービス コレクションに追加します。 `.AddMicrosoftGraph()` にはいくつかのオーバーライドがあります。 構成セクションをパラメーターとして受け取るオーバーライドを使用すると、コードは次のようになります。

    ```csharp
    using Microsoft.Extensions.DependencyInjection;
    using Microsoft.Identity.Client;
    using Microsoft.Identity.Web;
    using Microsoft.Identity.Web.OWIN;
    using Microsoft.Identity.Web.TokenCacheProviders.InMemory;
    using Microsoft.IdentityModel.Validators;
    using Microsoft.Owin.Security;
    using Microsoft.Owin.Security.Cookies;
    using Owin;
    
    namespace WebApp
    {
       public partial class Startup
       {
           public void ConfigureAuth(IAppBuilder app)
           {
               app.SetDefaultSignInAsAuthenticationType(CookieAuthenticationDefaults.AuthenticationType);
    
               app.UseCookieAuthentication(new CookieAuthenticationOptions());
    
               // Get an TokenAcquirerFactory specialized for OWIN
               OwinTokenAcquirerFactory owinTokenAcquirerFactory = TokenAcquirerFactory.GetDefaultInstance<OwinTokenAcquirerFactory>();
    
               // Configure the web app.
               app.AddMicrosoftIdentityWebApi(owinTokenAcquirerFactory);
    
               // Add the services you need.
               owinTokenAcquirerFactory.Services
                   .AddMicrosoftGraph()
                   .AddInMemoryTokenCaches();
               owinTokenAcquirerFactory.Build();
           }
       }
    }
    ```

#### オプション 2:Microsoft Graph 以外のダウンストリーム Web API を呼び出す

Microsoft Graph ではなく API を呼び出す場合は、*Microsoft.Identity.Web* を使用すると、API アクションで `IDownstreamApi` インターフェイスを使用できます。 このインターフェイスを使用するには、以下を行います。

1. [Microsoft.Identity.Web.DownstreamApi](https://www.nuget.org/packages/Microsoft.Identity.Web.DownstreamApi) NuGet パッケージをプロジェクトに追加します。
2. `.AddDownstreamApi()` ファイルで `.EnableTokenAcquisitionToCallDownstreamApi()` の後に  を追加します。 `.AddDownstreamApi()`には、次の 2 つの引数があります。
    - サービス (API) の名前: コントローラー アクションでこの名前を使用して、対応する構成を参照します。
    - ダウンストリーム Web API の呼び出しに使用されるパラメーターを表す構成セクション。

コードは次のとおりです。

```csharp
  using Microsoft.Extensions.DependencyInjection;
  using Microsoft.Identity.Client;
  using Microsoft.Identity.Web;
  using Microsoft.Identity.Web.OWIN;
  using Microsoft.Identity.Web.TokenCacheProviders.InMemory;
  using Microsoft.IdentityModel.Validators;
  using Microsoft.Owin.Security;
  using Microsoft.Owin.Security.Cookies;
  using Owin;

  namespace WebApp
  {
      public partial class Startup
      {
          public void ConfigureAuth(IAppBuilder app)
          {
              app.SetDefaultSignInAsAuthenticationType(CookieAuthenticationDefaults.AuthenticationType);

              app.UseCookieAuthentication(new CookieAuthenticationOptions());

              // Get a TokenAcquirerFactory specialized for OWIN.
              OwinTokenAcquirerFactory owinTokenAcquirerFactory = TokenAcquirerFactory.GetDefaultInstance<OwinTokenAcquirerFactory>();

              // Configure the web app.
              app.AddMicrosoftIdentityWebApi(owinTokenAcquirerFactory);

              // Add the services you need.
              owinTokenAcquirerFactory.Services
                  .AddDownstreamApi("Graph", owinTokenAcquirerFactory.Configuration.GetSection("GraphBeta"))
                  .AddInMemoryTokenCaches();
              owinTokenAcquirerFactory.Build();
          }
      }
  }
```

## [ジャワ](#tab/java)
### On-Behalf-Of (OBO) フローを使用する

On-Behalf-Of (OBO) フローは、ダウンストリーム Web API を呼び出すためのトークンを取得する際に使用されます。 このフローでは、Web API は、ユーザーが委任したアクセス許可を持つベアラー トークンをクライアント アプリケーションから受信し、このトークンを別のアクセス トークンと交換してダウンストリーム Web API を呼び出します。

次のコードでは、Web API で Spring Security フレームワークの `SecurityContextHolder` を使用して、検証済みのベアラー トークンを取得します。 次に、MSAL Java ライブラリを使用して、`acquireToken` を設定した `OnBehalfOfParameters` を使ってダウンストリーム API のトークンを取得します。 MSAL はトークンをキャッシュし、API への後続の呼び出しで `acquireTokenSilently` を使用してキャッシュされたトークンを取得できるようにします。

```java
@Component
class MsalAuthHelper {

    @Value("${security.oauth2.client.authority}")
    private String authority;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String secret;

    @Autowired
    CacheManager cacheManager;

    private String getAuthToken(){
        String res = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null){
            res = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
        }
        return res;
    }

    String getOboToken(String scope) throws MalformedURLException {
        String authToken = getAuthToken();

        ConfidentialClientApplication application =
                ConfidentialClientApplication.builder(clientId, ClientCredentialFactory.create(secret))
                        .authority(authority).build();

        String cacheKey = Hashing.sha256()
                .hashString(authToken, StandardCharsets.UTF_8).toString();

        String cachedTokens = cacheManager.getCache("tokens").get(cacheKey, String.class);
        if(cachedTokens != null){
            application.tokenCache().deserialize(cachedTokens);
        }

        IAuthenticationResult auth;
        SilentParameters silentParameters =
                SilentParameters.builder(Collections.singleton(scope))
                        .build();
        auth = application.acquireTokenSilently(silentParameters).join();

        if (auth == null){
            OnBehalfOfParameters parameters =
                    OnBehalfOfParameters.builder(Collections.singleton(scope),
                            new UserAssertion(authToken))
                            .build();

            auth = application.acquireToken(parameters).join();
        }

        cacheManager.getCache("tokens").put(cacheKey, application.tokenCache().serialize());

        return auth.accessToken();
    }
}
```

## [Python](#tab/python)
### On-Behalf-Of (OBO) フローを使用する

On-Behalf-Of (OBO) フローは、ダウンストリーム Web API を呼び出すためのトークンを取得する際に使用されます。 このフローでは、Web API は、ユーザーが委任したアクセス許可を持つベアラー トークンをクライアント アプリケーションから受信し、このトークンを別のアクセス トークンと交換してダウンストリーム Web API を呼び出します。

Python Web API では、クライアントから受信したベアラー トークンを検証するためになんらかのミドルウェアを使用する必要があります。 Web API は、[`acquire_token_on_behalf_of`](https://msal-python.readthedocs.io/en/latest/?badge=latest#msal.ConfidentialClientApplication.acquire_token_on_behalf_of) メソッドを呼び出すことにより、MSAL Python ライブラリを使用してダウンストリーム API のアクセス トークンを取得できます。 この API の使用例については、[GitHub の microsoft-authentication-library-for-python のテスト コード](https://github.com/AzureAD/microsoft-authentication-library-for-python/blob/1.2.0/tests/test_e2e.py#L429-L472)を参照してください。 また、中間層アプリケーションの必要性をなくす方法については、同じリポジトリ内の[問題 53](https://github.com/AzureAD/microsoft-authentication-library-for-python/issues/53) の説明も参照してください。

[ms-identity-python-on-behalf-of](https://github.com/Azure-Samples/ms-identity-python-on-behalf-of) サンプルで、OBO フローの実装の例を参照することもできます。

---

また、[Node.js と Azure Functions](https://github.com/Azure-Samples/ms-identity-nodejs-webapi-onbehalfof-azurefunctions/blob/master/Function/MyHttpTrigger/index.js#L61) への OBO フロー実装の例を確認することもできます。

### プロトコル

OBO プロトコルの詳細については、「[Microsoft ID プラットフォームと OAuth 2.0 On-Behalf-Of フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-web-api-call-api-call-api"} -->
## Web API を呼び出す Web API - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-api-call-api-call-api
- Service: identity-platform / workforce
- Article date: 2025-03-21
- Summary: Microsoft ID プラットフォームを使用して他の API を呼び出す Web API を構築します。 トークンを取得し、安全な API 呼び出しを行う方法について説明します。  

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

トークンを取得した後に、保護された Web API を呼び出せます。 通常、Web アプリのコントローラーまたはページからダウンストリーム API を呼び出します。

### コントローラー コード

## [ASP.NET Core](#tab/aspnetcore)
*Microsoft.Identity.Web* を使用する場合、3 つの使用シナリオがあります。

- オプション 1: SDK を使用して Microsoft Graph を呼び出す
- オプション 2: ヘルパー クラスを使用してダウンストリーム Web API を呼び出す
- オプション 3:ヘルパー クラスを使用せずにダウンストリーム Web API を呼び出す

##### オプション 1: SDK を使用して Microsoft Graph を呼び出す

このシナリオでは **Microsoft.Identity.Web.GraphServiceClient** NuGet パッケージを追加し、`.AddMicrosoftGraph()`で説明されているように *Startup.cs* に  を追加しました。また、コントローラーかページ コンストラクターに `GraphServiceClient` を直接挿入して、アクションで使用できます。 次の例の Razor ページには、サインインしたユーザーの写真が表示されます。

```csharp
 [Authorize]
 [AuthorizeForScopes(Scopes = new[] { "user.read" })]
 public class IndexModel : PageModel
 {
     private readonly GraphServiceClient _graphServiceClient;

     public IndexModel(GraphServiceClient graphServiceClient)
     {
         _graphServiceClient = graphServiceClient;
     }

     public async Task OnGet()
     {
         var user = await _graphServiceClient.Me.GetAsync();
         try
         {
             using (var photoStream = await _graphServiceClient.Me.Photo.Content.GetAsync())
             {
                 byte[] photoByte = ((MemoryStream)photoStream).ToArray();
                 ViewData["photo"] = Convert.ToBase64String(photoByte);
             }
             ViewData["name"] = user.DisplayName;
         }
         catch (Exception)
         {
             ViewData["photo"] = null;
         }
     }
 }
```

##### オプション 2:ヘルパー クラスを使用してダウンストリーム Web API を呼び出す

このシナリオでは、`.AddDownstreamApi()`に関するページで説明されているように、*Startup.cs* に  を追加し、コントローラーまたはページ コンストラクターに `IDownstreamWebApi` サービスを直接挿入してアクションでそれを使用することができます。

```csharp
 [Authorize]
 [AuthorizeForScopes(ScopeKeySection = "TodoList:Scopes")]
 public class TodoListController : Controller
 {
     private IDownstreamWebApi _downstreamWebApi;
     private const string ServiceName = "TodoList";

     public TodoListController(IDownstreamWebApi downstreamWebApi)
     {
         _downstreamWebApi = downstreamWebApi;
     }

     public async Task<ActionResult> Details(int id)
     {
         var value = await _downstreamWebApi.CallApiForUserAsync(
             ServiceName,
             options =>
             {
                 options.RelativePath = $"me";
             });
         return View(value);
     }
```

また、`CallApiForUserAsync` メソッドには、オブジェクトを直接受け取ることができる、厳密に型指定されたジェネリック オーバーライドもあります。 たとえば、次のメソッドは `Todo` インスタンスを受け取ります。これは、Web API から返された JSON の厳密に型指定された表現です。

```csharp
 // GET: TodoList/Details/5
 public async Task<ActionResult> Details(int id)
 {
     var value = await _downstreamWebApi.CallApiForUserAsync<object, Todo>(
         ServiceName,
         null,
         options =>
         {
             options.HttpMethod = HttpMethod.Get;
             options.RelativePath = $"api/todolist/{id}";
         });
     return View(value);
 }
```

##### オプション 3:ヘルパー クラスを使用せずにダウンストリーム Web API を呼び出す

`IAuthorizationHeaderProvider` インターフェイスを使用して承認ヘッダーを取得することにした場合、次のコードは、「[Web API を呼び出す Web API: アプリのトークンを取得する](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-api-call-api-acquire-token)」に示されているコード例を続行します。 このコードは、API コントローラーのアクションで呼び出されます。 *todolist* という名前のダウンストリーム API を呼び出します。

トークンを取得した後、それをベアラー トークンとして使用してダウンストリーム API を呼び出します。

```csharp
private async Task CallTodoListService(string accessToken)
{
  // After the token has been returned by Microsoft.Identity.Web, add it to the HTTP authorization header before making the call to access the todolist service.
  authorizationHeader = await authorizationHeaderProvider.GetAuthorizationHeaderForUserAsync(scopes);
  _httpClient.DefaultRequestHeaders["Authorization"] = authorizationHeader;

  // Call the todolist service.
  HttpResponseMessage response = await _httpClient.GetAsync(TodoListBaseAddress + "/api/todolist");
  // ...
}
```

## [ASP.NET](#tab/aspnet)
*Microsoft.Identity.Web* を使用する場合、API を呼び出すための使用オプションが 3 つあります。

- オプション 1: Microsoft Graph SDK を使用して Microsoft Graph を呼び出す
- オプション 2: ヘルパー クラスを使用してダウンストリーム Web API を呼び出す
- オプション 3:ヘルパー クラスを使用せずにダウンストリーム Web API を呼び出す

##### オプション 1: OWIN アプリの SDK を使用して Microsoft Graph を呼び出す

Microsoft Graph を呼び出す場合は、`AddMicrosoftGraph`で指定されている[Startup.csに](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-call-api-app-configuration#option-1-call-microsoft-graph)を追加します。 コントローラーの `GetGraphServiceClient()` 拡張メソッドを使用して、アクションで使用するコントローラーまたはページ コンストラクターの `GraphServiceClient` を取得します。 サインインしたユーザーの画像を次の例に示します。

```csharp
[Authorize]
public class HomeController : Controller
{

 public async Task GetIndex()
 {
  var graphServiceClient = this.GetGraphServiceClient();
  var user = await graphServiceClient.Me.GetAsync();
  try
  {
   using (var photoStream = await graphServiceClient.Me.Photo.Content.GetAsync())
   {
    byte[] photoByte = ((MemoryStream)photoStream).ToArray();
    ViewData["photo"] = Convert.ToBase64String(photoByte);
   }
   ViewData["name"] = user.DisplayName;
  }
  catch (Exception)
  {
   ViewData["photo"] = null;
  }
 }
}
```

##### オプション 2: ヘルパー クラスを使用して OWIN アプリからダウンストリーム Web API を呼び出す

Microsoft Graph 以外の Web API を呼び出す必要があります。 その場合は、`AddDownstreamApi`で指定されている[Startup.csに](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-call-api-app-configuration#option-2-call-a-downstream-web-api-other-than-microsoft-graph)を追加します。 コントローラーで `GetDownstreamApi` 拡張メソッドを使用して、 `IDownstreamApi` サービスを取得します。

```csharp
[Authorize]
public class TodoListController : Controller
{ 
  public async Task<ActionResult> Details(int id)
  {
    var downstreamApi = this.GetDownstreamApi();
    var value = await downstreamApi.CallApiForUserAsync(
      ServiceName,
      options =>
      {
        options.RelativePath = $"me";
      });
      return View(value);
  }
}
```

また、`CallApiForUserAsync` には、オブジェクトを直接受け取ることができる、厳密に型指定されたジェネリック オーバーライドもあります。 たとえば、次のメソッドは `Todo` インスタンスを受け取ります。これは、Web API から返された JSON の厳密に型指定された表現です。

```csharp
    // GET: TodoList/Details/5
    public async Task<ActionResult> Details(int id)
    {
        var downstreamApi = this.GetDownstreamApi();
        var value = await downstreamApi.GetForUserAsync<object, Todo>(
            ServiceName,
            null,
            options =>
            {
                options.RelativePath = $"api/todolist/{id}";
            });
        return View(value);
    }
```

##### オプション 3: ヘルパー クラスなしで OWIN アプリからダウンストリーム Web API を呼び出す

`IAuthorizationHeaderProvider` サービスを使用して承認ヘッダーを取得することにしました。現在、`HttpClient` または `HttpRequest` でそれを使用する必要があります。 この場合、次のコードは、「[Web API を呼び出す Web API:アプリのトークンを取得する](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-api-call-api-acquire-token)」で示されているコード例の続きです。 このコードは、Web API コントローラーのアクションで呼び出されます。

```csharp
public async Task<IActionResult> Profile()
{
  // Acquire the access token.
  string[] scopes = new string[]{"user.read"};
  var IAuthorizationHeaderProvider = this.GetAuthorizationHeaderProvider();
  string authorizationHeader = await IAuthorizationHeaderProvider.GetAuthorizationHeaderForUserAsync(scopes);

  // Use the access token to call a protected web API.
  HttpClient httpClient = new HttpClient();
  client.DefaultRequestHeaders.Add("Authorization", authorizationHeader);

  var response = await httpClient.GetAsync($"{webOptions.GraphApiUrl}/beta/me");

  if (response.StatusCode == HttpStatusCode.OK)
  {
    var content = await response.Content.ReadAsStringAsync();

    dynamic me = JsonConvert.DeserializeObject(content);
    ViewData["Me"] = me;
  }

  return View();
}
```

## [ジャワ](#tab/java)
次のコードは、「[Web API を呼び出す Web API: アプリのトークンを取得する](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-api-call-api-acquire-token)」で示されているコード例の続きです。 このコードは、API コントローラーのアクションで呼び出されます。 ダウンストリーム API MS Graph を呼び出します。

トークンを取得した後、ダウンストリーム API を呼び出すベアラー トークンとしてそれを使用します。

```java
private String callMicrosoftGraphMeEndpoint(String accessToken){
    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    headers.set("Authorization", "Bearer " + accessToken);

    HttpEntity<String> entity = new HttpEntity<>(null, headers);

    String result = restTemplate.exchange("https://graph.microsoft.com/v1.0/me", HttpMethod.GET,
            entity, String.class).getBody();

    return result;
}
```

## [Python](#tab/python)
このフローを MSAL Python でデモンストレーションするサンプルは、[ms-identity-python-on-behalf-of](https://github.com/Azure-Samples/ms-identity-python-on-behalf-of) で入手できます。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-web-app-call-api-acquire-token"} -->
## Web API を呼び出す Web アプリのトークンを取得する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-call-api-acquire-token
- Service: identity-platform / workforce
- Article date: 2025-01-30
- Summary: Web API を呼び出す Web アプリのトークンを取得する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

クライアント アプリケーション オブジェクトは構築済みです。 次はこれを使用して Web API を呼び出すトークンを取得します。 ASP.NET または ASP.NET Core では、Web API の呼び出しはコントローラーで実行されます。

- トークン キャッシュを使用して Web API のトークンを取得します。 このトークンを取得するには、Microsoft 認証ライブラリ (MSAL) の `AcquireTokenSilent` メソッド (または Microsoft.Identity.Web の同等のもの) を呼び出します。
- 保護された API を呼び出して、アクセス トークンをパラメーターとして渡します。

## [ASP.NET Core](#tab/aspnetcore)
*Microsoft.Identity.Web* には、Microsoft Graph またはダウンストリーム Web API を呼び出す便利なサービスを提供する拡張メソッドが追加されています。 これらのメソッドの詳細については、「[Web API を呼び出す Web アプリ: API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-call-api-call-api)」を参照してください。 これらのヘルパー メソッドを使用すれば、トークンを手動で取得する必要はありません。

ただし、トークンを手動で取得する場合は、ホーム コントローラーで取得することを目的とした *Microsoft.Identity.Web* の使用の例が、次のコードによって示されています。 これにより、REST API (Microsoft Graph SDK ではなく) を使用した、Microsoft Graph の呼び出しが行われます。 通常、トークンを取得する必要はありませんが、要求に追加する Authorization ヘッダーを作成する必要があります。 Authorization ヘッダーを取得するには、コントローラーのコンストラクター (Blazor を使用する場合はページ コンストラクター) 内での依存関係の挿入によって、`IAuthorizationHeaderProvider` サービスを挿入し、それをコントローラー アクションで使用します。 このインターフェイスには、プロトコル (Bearer や Pop など) とトークンを含む文字列を生成するメソッドが用意されています。 ユーザーに代わって API を呼び出す Authorization ヘッダーを取得するには、(`CreateAuthorizationHeaderForUserAsync`) を使用します。 アプリケーション自体に代わってダウンストリーム API を呼び出す Authorization ヘッダーを取得するには、デーモン シナリオで (`CreateAuthorizationHeaderForAppAsync`) を使用します。

コントローラー メソッドは、認証されたユーザーのみが Web アプリを使用できるようにする `[Authorize]` 属性によって保護されます。

```csharp
[Authorize]
public class HomeController : Controller
{
 readonly IAuthorizationHeaderProvider authorizationHeaderProvider;

 public HomeController(IAuthorizationHeaderProvider authorizationHeaderProvider)
 {
  this.authorizationHeaderProvider = authorizationHeaderProvider;
 }

 // Code for the controller actions (see code below)

}
```

ASP.NET Coreは、依存関係の挿入によって `IAuthorizationHeaderProvider` を使用できるようにします。

Microsoft Graph を呼び出すトークンを取得する `HomeController` のアクションの簡略化されたコードを次に示します。

```csharp
[AuthorizeForScopes(Scopes = new[] { "user.read" })]
public async Task<IActionResult> Profile()
{
 // Acquire the access token.
 string[] scopes = new string[]{"user.read"};
 string accessToken = await authorizationHeaderProvider.CreateAuthorizationHeaderForUserAsync(scopes);

 // Use the access token to call a protected web API.
 HttpClient client = new HttpClient();
 client.DefaultRequestHeaders.Add("Authorization", accessToken);
 string json = await client.GetStringAsync(url);
}
```

このシナリオに必要なコードをさらに理解したい場合は、[ms-identity-aspnetcore-Webapp-tutorial](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/tree/master/2-WebApp-graph-user/2-1-Call-MSGraph) チュートリアルのフェーズ 2 ([2-1-Web App Calls Microsoft Graph](https://github.com/Azure-Samples/ms-identity-aspnetcore-webapp-tutorial)) の手順を参照してください。

コントローラー アクションの上 (または Razor テンプレートを使用する場合は Razor ページ) の `AuthorizeForScopes` 属性は、Microsoft.Identity.Web によって提供されます。 これにより、必要に応じて、かつ段階的にユーザーに同意が求められます。

次のような複雑なバリエーションもあります。

- 複数の API の呼び出し。
- 増分同意と条件付きアクセスの処理。

このような高度な手順については、[3-WebApp-multi-APIs](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/tree/master/3-WebApp-multi-APIs) チュートリアルの第 3 章を参照してください。

## [ASP.NET](#tab/aspnet)
ASP.NET のコードは、ASP.NET Core 用に示したコードと似ています。

- `[Authorize]` 属性によって保護されたコントローラー アクションは、コントローラーの `ClaimsPrincipal` メンバーのテナント ID とユーザー ID を抽出します (ASP.NET は `HttpContext.User` を使用)。 これにより、認証されたユーザーのみがアプリを使用できるようになります。 **Microsoft.Identity.Web** は、Microsoft Graph やダウンストリーム Web API を呼び出したり、Authorization ヘッダーやトークンを取得したりするための便利なサービスを提供する拡張メソッドをコントローラーに追加します。 API を直接呼び出すのに使用されるメソッドの詳細については、「[Web API を呼び出す Web アプリ: API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-call-api-call-api)」を参照してください。 これらのヘルパー メソッドを使用すれば、トークンを手動で取得する必要はありません。

ただし、トークンの取得または Authorization ヘッダーの構築を手動で行いたい場合は、Microsoft.Identity.Web を使用してコントローラーでそれを行う方法を示す次のコードを参照してください。 これにより、Microsoft Graph SDK ではなく REST API を使用した、API (Microsoft Graph) の呼び出しが行われます。

Authorization ヘッダーを取得するには、拡張メソッド `IAuthorizationHeaderProvider` を使用してコントローラーから `GetAuthorizationHeaderProvider` サービスを取得します。 ユーザーに代わって API を呼び出す Authorization ヘッダーを取得するには、`CreateAuthorizationHeaderForUserAsync` を使用します。 アプリケーション自体に代わってダウンストリーム API を呼び出す Authorization ヘッダーを取得するには、デーモン シナリオで `CreateAuthorizationHeaderForAppAsync` を使用します。

次のスニペットは、`HomeController` のアクション (REST API として Microsoft Graph を呼び出す Authorization ヘッダーが取得されます) を示したものです。

```csharp
[Authorize]
public class HomeController : Controller
{
 [AuthorizeForScopes(Scopes = new[] { "user.read" })]
 public async Task<IActionResult> Profile()
 {
  // Get an authorization header.
  IAuthorizationHeaderProvider authorizationHeaderProvider = this.GetAuthorizationHeaderProvider();
  string[] scopes = new string[]{"user.read"};
  string authorizationHeader = await authorizationHeaderProvider.CreateAuthorizationHeaderForUserAsync(scopes);

  // Use the access token to call a protected web API.
  HttpClient client = new HttpClient();
  client.DefaultRequestHeaders.Add("Authorization", authorizationHeader);
  string json = await client.GetStringAsync(url);
 }
}
```

次のスニペットは、`HomeController` のアクション (REST API として Microsoft Graph を呼び出すアクセス トークンが取得されます) を示したものです。

```csharp
[Authorize]
public class HomeController : Controller
{
 [AuthorizeForScopes(Scopes = new[] { "user.read" })]
 public async Task<IActionResult> Profile()
 {
  // Get an authorization header.
  ITokenAcquirer tokenAcquirer = TokenAcquirerFactory.GetDefaultInstance().GetTokenAcquirer();
  string[] scopes = new string[]{"user.read"};
  string token = await tokenAcquirer.GetTokenForUserAsync(scopes);

  // Use the access token to call a protected web API.
  HttpClient client = new HttpClient();
  client.DefaultRequestHeaders.Add("Authorization", $"Bearer {token}");
  string json = await client.GetStringAsync(url);
 }
}
```

## [ジャワ](#tab/java)
Java のサンプルでは、API を呼び出すコードは `getUsersFromGraph` メソッド ([AuthPageController.java#L62](https://github.com/Azure-Samples/ms-identity-java-webapp/blob/d55ee4ac0ce2c43378f2c99fd6e6856d41bdf144/src/main/java/com/microsoft/azure/msalwebsample/AuthPageController.java#L62)) にあります。

メソッドから `getAuthResultBySilentFlow` の呼び出しが試行されます。 ユーザーがより多くのスコープに同意する必要がある場合、コードは`MsalInteractionRequiredException` オブジェクトを処理し、ユーザーにチャレンジを提示します。

```java
@RequestMapping("/msal4jsample/graph/me")
public ModelAndView getUserFromGraph(HttpServletRequest httpRequest, HttpServletResponse response)
        throws Throwable {

    IAuthenticationResult result;
    ModelAndView mav;
    try {
        result = authHelper.getAuthResultBySilentFlow(httpRequest, response);
    } catch (ExecutionException e) {
        if (e.getCause() instanceof MsalInteractionRequiredException) {

            // If the silent call returns MsalInteractionRequired, redirect to authorization endpoint
            // so user can consent to new scopes.
            String state = UUID.randomUUID().toString();
            String nonce = UUID.randomUUID().toString();

            SessionManagementHelper.storeStateAndNonceInSession(httpRequest.getSession(), state, nonce);

            String authorizationCodeUrl = authHelper.getAuthorizationCodeUrl(
                    httpRequest.getParameter("claims"),
                    "User.Read",
                    authHelper.getRedirectUriGraph(),
                    state,
                    nonce);

            return new ModelAndView("redirect:" + authorizationCodeUrl);
        } else {

            mav = new ModelAndView("error");
            mav.addObject("error", e);
            return mav;
        }
    }

    if (result == null) {
        mav = new ModelAndView("error");
        mav.addObject("error", new Exception("AuthenticationResult not found in session."));
    } else {
        mav = new ModelAndView("auth_page");
        setAccountInfo(mav, httpRequest);

        try {
            mav.addObject("userInfo", getUserInfoFromGraph(result.accessToken()));

            return mav;
        } catch (Exception e) {
            mav = new ModelAndView("error");
            mav.addObject("error", e);
        }
    }
    return mav;
}
// Code omitted here
```

## [Node.js](#tab/nodejs)
この Node.js サンプルでは、トークンを取得するコードは `acquireToken` クラスの `AuthProvider` メソッドにあります。

```js
acquireToken(options = {}) {
    return async (req, res, next) => {
        try {
            const msalInstance = this.getMsalInstance(this.msalConfig);

            /**
             * If a token cache exists in the session, deserialize it and set it as the 
             * cache for the new MSAL CCA instance. For more, see: 
             * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/caching.md
             */
            if (req.session.tokenCache) {
                msalInstance.getTokenCache().deserialize(req.session.tokenCache);
            }

            const tokenResponse = await msalInstance.acquireTokenSilent({
                account: req.session.account,
                scopes: options.scopes || [],
            });

            /**
             * On successful token acquisition, write the updated token 
             * cache back to the session. For more, see: 
             * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/caching.md
             */
            req.session.tokenCache = msalInstance.getTokenCache().serialize();
            req.session.accessToken = tokenResponse.accessToken;
            req.session.idToken = tokenResponse.idToken;
            req.session.account = tokenResponse.account;

            res.redirect(options.successRedirect);
        } catch (error) {
            if (error instanceof msal.InteractionRequiredAuthError) {
                return this.login({
                    scopes: options.scopes || [],
                    redirectUri: options.redirectUri,
                    successRedirect: options.successRedirect || '/',
                })(req, res, next);
            }

            next(error);
        }
    };
}
```

このアクセス トークンは、`/profile` エンドポイントへの要求を処理するために使用されます。

```js
router.get('/profile',
    isAuthenticated, // check if user is authenticated
    async function (req, res, next) {
        try {
            const graphResponse = await fetch(GRAPH_ME_ENDPOINT, req.session.accessToken);
            res.render('profile', { profile: graphResponse });
        } catch (error) {
            next(error);
        }
    }
);
```

## [Python](#tab/python)
Python のサンプルでは、API を呼び出すコードは *app.py* にあります。

このコードでは、トークン キャッシュからのトークンの取得が試行されます。 トークンを取得できない場合、ユーザーはサインイン ルートにリダイレクトされます。 それ以外の場合は、API の呼び出しに進むことができます。

```python
@app.route("/call_downstream_api")
def call_downstream_api():
    token = auth.get_token_for_user(app_config.SCOPE)
    if "error" in token:
        return redirect(url_for("login"))
    # Use access token to call downstream api
    api_result = requests.get(
        app_config.ENDPOINT,
        headers={'Authorization': 'Bearer ' + token['access_token']},
        timeout=30,
    ).json()
    return render_template('display.html', result=api_result)
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-web-app-call-api-app-configuration"} -->
## Web API を呼び出す Web アプリを構成する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-call-api-app-configuration
- Service: identity-platform / workforce
- Article date: 2024-07-19
- Summary: Web API を呼び出す Web アプリのコードを構成する方法について説明します

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

この記事では、アプリケーション コードを構成し、ユーザーをサインインさせるだけでなく Web API の呼び出しも行うように Web アプリを変更する方法について説明します。 作成するアプリケーションでは、[OAuth 2.0 認証コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)を使用して、ユーザーをサインインさせます。 このフローには、次の 2 つのステップがあります。

1. 承認コードを要求します。 この部分では、ユーザーとのプライベートな対話を Microsoft ID プラットフォームに委任します。 その対話の間に、ユーザーはサインインして Web API の使用に同意します。 プライベートな対話が正常に終了すると、Web アプリはそのリダイレクト URI での承認コードを受け取ります。
2. 承認コードを引き換えることによって、API のアクセス トークンを要求します。

### 前提条件

- アクティブなサブスクリプションを持つ Azure アカウント。 [無料でアカウントを作成できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。 このアカウントには、アプリケーションを管理するためのアクセス許可が必要です。 アプリケーションを登録するために必要な次のロールのいずれかを使用します。
    - アプリケーション管理者
    - アプリケーション開発者
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID

### Web アプリをサポートしている Microsoft ライブラリ

次の Microsoft ライブラリで Web アプリがサポートされています。

| 言語/フレームワーク | プロジェクト開始GitHub | パッケージ | 取得する開始した | ユーザーのサインイン | Web API へのアクセス | 一般提供 (GA)*または*パブリック プレビュー^1^ |
| --- | --- | --- | --- | --- | --- | --- |
| .NET | [MSAL.NET](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet) | [Microsoft.Identity.Client](https://www.nuget.org/packages/Microsoft.Identity.Client) | — | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できません。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| .NET | [Microsoft.IdentityModel](https://github.com/AzureAD/azure-activedirectory-identitymodel-extensions-for-dotnet) | [Microsoft.IdentityModel](https://www.nuget.org/packages?q=Microsoft.IdentityModel) | — | [Image: ライブラリは、ユーザー サインインの ID トークンを要求できません。]^2^ | [Image: ライブラリは、保護された Web API のアクセス トークンを要求できません。]^2^ | GA |
| ASP.NET Core | [Microsoft.Identity.Web](https://github.com/AzureAD/microsoft-identity-web) | [Microsoft.Identity.Web](https://www.nuget.org/packages/Microsoft.Identity.Web) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-dotnet-core-sign-in) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| Java | [MSAL4J](https://github.com/AzureAD/microsoft-authentication-library-for-java) | [msal4j](https://central.sonatype.com/artifact/com.microsoft.azure/msal4j) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-java-sign-in) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| Spring | [spring-cloud-azure-starter-active-directory](https://github.com/Azure/azure-sdk-for-java/tree/spring-cloud-azure-autoconfigure_4.3.0/sdk/spring/spring-cloud-azure-starter-active-directory) | [spring-cloud-azure-starter-active-directory](https://central.sonatype.com/artifact/com.azure.spring/spring-cloud-azure-starter-active-directory) | [チュートリアル](https://learn.microsoft.com/ja-jp/azure/developer/java/spring-framework/configure-spring-boot-starter-java-app-with-azure-active-directory) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| Node.js | [MSAL ノード](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node) | [msal-node](https://www.npmjs.com/package/@azure/msal-node) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-nodejs-sign-in) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| Python | [MSAL Python](https://github.com/AzureAD/microsoft-authentication-library-for-python) | [msal](https://pypi.org/project/msal) | — | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | GA |
| Python | [identity](https://github.com/rayluo/identity) | [identity](https://pypi.org/project/identity/) | [クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-python-flask) | [Image: ライブラリでは、ユーザー サインインの ID トークンを要求できます。] | [Image: ライブラリでは、保護された Web API のアクセス トークンを要求できます。] | -- |

^(1)^[オンライン サービスのユニバーサル ライセンス条項](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)は、"パブリック プレビュー" のライブラリに適用されます。

^(2)^[Microsoft.IdentityModel](https://github.com/AzureAD/azure-activedirectory-identitymodel-extensions-for-dotnet) ライブラリはトークンの "検証" のみを行います。ID やアクセス トークンを要求することはできません。

関心があるプラットフォームのタブを選択してください。

## [ASP.NET Core](#tab/aspnetcore)
### クライアント シークレットまたはクライアント証明書

ご利用の Web アプリでダウンストリーム Web API を呼び出すことができるようになったため、クライアント シークレットまたはクライアント証明書を *appsettings.json* ファイルに指定してください。 次を指定するセクションを追加することもできます。

- ダウンストリーム Web API の URL
- API の呼び出しに必要なスコープ

次の例では、`GraphBeta` セクションでこれらの設定を指定しています。

```JSON
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "ClientId": "[Enter_the_Application_Id_Here]",
    "TenantId": "common",

   // To call an API
   "ClientCredentials": [
    {
      "SourceType": "ClientSecret",
      "ClientSecret":"[Enter_the_Client_Secret_Here]"
    }
  ]
 },
 "GraphBeta": {
    "BaseUrl": "https://graph.microsoft.com/beta",
    "Scopes": ["user.read"]
    }
}
```

注意

Azure Kubernetes のワークロード ID フェデレーションのような資格情報のないソリューションなど、クライアント資格情報のコレクションを提案できます。 以前のバージョンの Microsoft.Identity.Web では、"ClientCredentials" ではなく単一のプロパティ "ClientSecret" でクライアント シークレットが表現されていました。 これは下位互換性のために引き続きサポートされていますが、"ClientSecret" プロパティと "ClientCredentials" コレクションの両方を使用することはできません。

クライアント シークレットの代わりに、クライアント証明書を指定することができます。 次のコード スニペットは、Azure Key Vault に格納されている証明書の使用を示しています。

```JSON
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "ClientId": "[Enter_the_Application_Id_Here]",
    "TenantId": "common",

   // To call an API
   "ClientCredentials": [
      {
        "SourceType": "KeyVault",
        "KeyVaultUrl": "https://msidentitywebsamples.vault.azure.net",
        "KeyVaultCertificateName": "MicrosoftIdentitySamplesCert"
      }
   ]
  },
  "GraphBeta": {
    "BaseUrl": "https://graph.microsoft.com/beta",
    "Scopes": ["user.read"]
  }
}
```

警告

`Scopes` を配列に変更することを忘れた場合、`IDownstreamApi` を使用しようとするとスコープに null が表示され、`IDownstreamApi` はダウンストリーム API に対して匿名 (非認証) の呼び出しを試み、その結果、`401/unauthenticated` が発生します。

*Microsoft.Identity.Web* では、構成またはコードの両方で証明書を記述するいくつかの方法を提供しています。 詳細については、GitHub 上の「[Microsoft.Identity.Web - 証明書の使用](https://github.com/AzureAD/microsoft-identity-web/wiki/Using-certificates)」を参照してください。

### *Startup.cs* ファイルを変更する

Web アプリでは、ダウンストリーム API のトークンを取得する必要があります。 これを指定するには、`.EnableTokenAcquisitionToCallDownstreamApi()` の後に `.AddMicrosoftIdentityWebApp(Configuration)` 行を追加します。 この行により、コントローラーおよびページのアクションで使用できる `IAuthorizationHeaderProvider` サービスが公開されます。 ただし、次の 2 つのオプションでわかるように、これはもっと簡単に行うことができます。 "Startup.cs" で、`.AddInMemoryTokenCaches()` などのトークン キャッシュの実装を選択する必要もあります。

```csharp
using Microsoft.Identity.Web;

public class Startup
{
  // ...
  public void ConfigureServices(IServiceCollection services)
  {
  // ...
  services.AddAuthentication(OpenIdConnectDefaults.AuthenticationScheme)
          .AddMicrosoftIdentityWebApp(Configuration, "AzureAd")
            .EnableTokenAcquisitionToCallDownstreamApi(new string[]{"user.read" })
            .AddInMemoryTokenCaches();
   // ...
  }
  // ...
}
```

`EnableTokenAcquisitionToCallDownstreamApi` に渡されるスコープは省略可能であり、Web アプリでサインイン時にスコープとそれらのスコープに対するユーザーの同意を要求できます。 スコープを指定しない場合は、*Microsoft.Identity.Web* によって、増分同意エクスペリエンスが有効になります。

"Microsoft.Identity.Web" には、トークンを取得しなくても Web アプリから Web API を呼び出すための 2 つのメカニズムが用意されています。 選択するオプションは、Microsoft Graph または別の API のどちらを呼び出すかによって異なります。

#### オプション 1: Microsoft Graph の呼び出し

Microsoft Graph を呼び出す場合は、*Microsoft.Identity.Web* を使用すると、API アクションで `GraphServiceClient` (Microsoft Graph SDK によって公開されている) を直接使用することができます。 Microsoft Graph を公開するには、次の手順を実行します。

1. [Microsoft.Identity.Web.GraphServiceClient](https://www.nuget.org/packages/Microsoft.Identity.Web.GraphServiceClient) NuGet パッケージをプロジェクトに追加します。
2. *Startup.cs* ファイルで `.EnableTokenAcquisitionToCallDownstreamApi()` の後に `.AddMicrosoftGraph()` を追加します。 `.AddMicrosoftGraph()` にはいくつかのオーバーライドがあります。 構成セクションをパラメーターとして受け取るオーバーライドを使用すると、コードは次のようになります。

    ```csharp
    using Microsoft.Identity.Web;
    
    public class Startup
    {
      // ...
      public void ConfigureServices(IServiceCollection services)
      {
      // ...
      services.AddAuthentication(OpenIdConnectDefaults.AuthenticationScheme)
              .AddMicrosoftIdentityWebApp(Configuration, "AzureAd")
                .EnableTokenAcquisitionToCallDownstreamApi(new string[]{"user.read" })
                   .AddMicrosoftGraph(Configuration.GetSection("GraphBeta"))
                .AddInMemoryTokenCaches();
       // ...
      }
      // ...
    }
    ```

#### オプション 2:Microsoft Graph 以外のダウンストリーム Web API を呼び出す

Microsoft Graph ではなく API を呼び出す場合は、*Microsoft.Identity.Web* を使用すると、API アクションで `IDownstreamApi` インターフェイスを使用できます。 このインターフェイスを使用するには、以下を行います。

1. [Microsoft.Identity.Web.DownstreamApi](https://www.nuget.org/packages/Microsoft.Identity.Web.DownstreamApi) NuGet パッケージをプロジェクトに追加します。
2. *Startup.cs* ファイルで `.EnableTokenAcquisitionToCallDownstreamApi()` の後に `.AddDownstreamApi()` を追加します。 `.AddDownstreamApi()` には、次のスニペットに示すように 2 つの引数があります。

    - 対応する構成を参照するためにコントローラー アクションで使用されるサービスの名前 (API)
    - ダウンストリーム Web API の呼び出しに使用されるパラメーターを表す構成セクション。

    ```csharp
    using Microsoft.Identity.Web;
    
    public class Startup
    {
      // ...
      public void ConfigureServices(IServiceCollection services)
      {
      // ...
      services.AddAuthentication(OpenIdConnectDefaults.AuthenticationScheme)
              .AddMicrosoftIdentityWebApp(Configuration, "AzureAd")
                .EnableTokenAcquisitionToCallDownstreamApi(new string[]{"user.read" })
                   .AddDownstreamApi("MyApi", Configuration.GetSection("GraphBeta"))
                .AddInMemoryTokenCaches();
       // ...
      }
      // ...
    }
    ```

#### まとめ

Web API と同様に、さまざまなトークン キャッシュの実装を選択できます。 詳細については、GitHub の [Microsoft.Identity.Web - トークン キャッシュのシリアル化](https://aka.ms/ms-id-web/token-cache-serialization)のページを参照してください。

次の図は、*Microsoft.Identity.Web* のさまざまな可能性と、*Startup.cs* ファイルへの影響を示しています。

[Image: Web API を呼び出し、トークン キャッシュの実装を指定するための Startup.cs のサービス構成オプションを示すブロック図]

注意

これらのコード例を完全に理解するために、[ASP.NET Core の基礎](https://learn.microsoft.com/ja-jp/aspnet/core/fundamentals)、特に[依存関係の挿入](https://learn.microsoft.com/ja-jp/aspnet/core/fundamentals/dependency-injection)と[オプション](https://learn.microsoft.com/ja-jp/aspnet/core/fundamentals/configuration/options)についてよく理解してください。

## [ASP.NET](#tab/aspnet)
### クライアント シークレットまたはクライアント証明書

ご利用の Web アプリでダウンストリーム Web API を呼び出すことができるようになったため、クライアント シークレットまたはクライアント証明書を *appsettings.json* ファイルに指定してください。 次を指定するセクションを追加することもできます。

- ダウンストリーム Web API の URL
- API の呼び出しに必要なスコープ

次の例では、`GraphBeta` セクションでこれらの設定を指定しています。

```JSON
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "ClientId": "[Enter_the_Application_Id_Here]",
    "TenantId": "common",

   // To call an API
   "ClientCredentials": [
    {
      "SourceType": "ClientSecret",
      "ClientSecret":"[Enter_the_Client_Secret_Here]"
    }
  ]
 },
 "GraphBeta": {
    "BaseUrl": "https://graph.microsoft.com/beta",
    "Scopes": ["user.read"]
    }
}
```

注意

Azure Kubernetes のワークロード ID フェデレーションのような資格情報のないソリューションなど、クライアント資格情報のコレクションを提案できます。 以前のバージョンの Microsoft.Identity.Web では、"ClientCredentials" ではなく単一のプロパティ "ClientSecret" でクライアント シークレットが表現されていました。 これは下位互換性のために引き続きサポートされていますが、"ClientSecret" プロパティと "ClientCredentials" コレクションの両方を使用することはできません。

クライアント シークレットの代わりに、クライアント証明書を指定することができます。 次のコード スニペットは、Azure Key Vault に格納されている証明書の使用を示しています。

```JSON
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "ClientId": "[Enter_the_Application_Id_Here]",
    "TenantId": "common",

   // To call an API
   "ClientCredentials": [
      {
        "SourceType": "KeyVault",
        "KeyVaultUrl": "https://msidentitywebsamples.vault.azure.net",
        "KeyVaultCertificateName": "MicrosoftIdentitySamplesCert"
      }
   ]
  },
  "GraphBeta": {
    "BaseUrl": "https://graph.microsoft.com/beta",
    "Scopes": ["user.read"]
  }
}
```

警告

`Scopes` を配列に変更することを忘れた場合、`IDownstreamApi` を使用しようとするとスコープに null が表示され、`IDownstreamApi` はダウンストリーム API に対して匿名 (非認証) の呼び出しを試み、その結果、`401/unauthenticated` が発生します。

*Microsoft.Identity.Web* では、構成またはコードの両方で証明書を記述するいくつかの方法を提供しています。 詳細については、GitHub 上の「[Microsoft.Identity.Web - 証明書の使用](https://github.com/AzureAD/microsoft-identity-web/wiki/Using-certificates)」を参照してください。

### Startup.Auth.cs

Web アプリでダウンストリーム API のトークンを取得する必要があります。"Microsoft.Identity.Web" には、Web アプリから Web API を呼び出すための 2 つのメカニズムが用意されています。 選択するオプションは、Microsoft Graph または別の API のどちらを呼び出すかによって異なります。

#### オプション 1: Microsoft Graph の呼び出し

Microsoft Graph を呼び出す場合は、*Microsoft.Identity.Web* を使用すると、API アクションで `GraphServiceClient` (Microsoft Graph SDK によって公開されている) を直接使用することができます。 Microsoft Graph を公開するには、次の手順を実行します。

1. [Microsoft.Identity.Web.GraphServiceClient](https://www.nuget.org/packages/Microsoft.Identity.Web.GraphServiceClient) NuGet パッケージをプロジェクトに追加します。
2. `.AddMicrosoftGraph()` を *Startup.Auth.cs* ファイル内のサービス コレクションに追加します。 `.AddMicrosoftGraph()` にはいくつかのオーバーライドがあります。 構成セクションをパラメーターとして受け取るオーバーライドを使用すると、コードは次のようになります。

```csharp
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Identity.Client;
using Microsoft.Identity.Web;
using Microsoft.Identity.Web.OWIN;
using Microsoft.Identity.Web.TokenCacheProviders.InMemory;
using Microsoft.IdentityModel.Validators;
using Microsoft.Owin.Security;
using Microsoft.Owin.Security.Cookies;
using Owin;

namespace WebApp
{
    public partial class Startup
    {
        public void ConfigureAuth(IAppBuilder app)
        {
            app.SetDefaultSignInAsAuthenticationType(CookieAuthenticationDefaults.AuthenticationType);

            app.UseCookieAuthentication(new CookieAuthenticationOptions());

            // Get an TokenAcquirerFactory specialized for OWIN
            OwinTokenAcquirerFactory owinTokenAcquirerFactory = TokenAcquirerFactory.GetDefaultInstance<OwinTokenAcquirerFactory>();

            // Configure the web app.
            app.AddMicrosoftIdentityWebApp(owinTokenAcquirerFactory,
                                          updateOptions: options => {});

            // Add the services you need.
            owinTokenAcquirerFactory.Services
                .Configure<ConfidentialClientApplicationOptions>(options => 
                      { options.RedirectUri = "https://localhost:44326/"; })
                .AddMicrosoftGraph()
                .AddInMemoryTokenCaches();
            owinTokenAcquirerFactory.Build();
        }
    }
}
```

#### オプション 2:Microsoft Graph 以外のダウンストリーム Web API を呼び出す

Microsoft Graph ではなく API を呼び出す場合は、*Microsoft.Identity.Web* を使用すると、API アクションで `IDownstreamApi` インターフェイスを使用できます。 このインターフェイスを使用するには、以下を行います。

1. [Microsoft.Identity.Web.DownstreamApi](https://www.nuget.org/packages/Microsoft.Identity.Web.DownstreamApi) NuGet パッケージをプロジェクトに追加します。
2. *Startup.cs* ファイルで `.EnableTokenAcquisitionToCallDownstreamApi()` の後に `.AddDownstreamApi()` を追加します。 `.AddDownstreamApi()`には次の 2 つの引数があります。
    - サービスの名前 (API): コントローラー アクションでこの名前を使用して、対応する構成を参照します
    - ダウンストリーム Web API の呼び出しに使用されるパラメーターを表す構成セクション。

コードは次のとおりです。

```csharp
  using Microsoft.Extensions.DependencyInjection;
  using Microsoft.Identity.Client;
  using Microsoft.Identity.Web;
  using Microsoft.Identity.Web.OWIN;
  using Microsoft.Identity.Web.TokenCacheProviders.InMemory;
  using Microsoft.IdentityModel.Validators;
  using Microsoft.Owin.Security;
  using Microsoft.Owin.Security.Cookies;
  using Owin;

  namespace WebApp
  {
      public partial class Startup
      {
          public void ConfigureAuth(IAppBuilder app)
          {
              app.SetDefaultSignInAsAuthenticationType(CookieAuthenticationDefaults.AuthenticationType);

              app.UseCookieAuthentication(new CookieAuthenticationOptions());

              // Get a TokenAcquirerFactory specialized for OWIN.
              OwinTokenAcquirerFactory owinTokenAcquirerFactory = TokenAcquirerFactory.GetDefaultInstance<OwinTokenAcquirerFactory>();

              // Configure the web app.
              app.AddMicrosoftIdentityWebApp(owinTokenAcquirerFactory,
                                            updateOptions: options => {});

              // Add the services you need.
              owinTokenAcquirerFactory.Services
                  .Configure<ConfidentialClientApplicationOptions>(options => 
                        { options.RedirectUri = "https://localhost:44326/"; })
                  .AddDownstreamApi("Graph", owinTokenAcquirerFactory.Configuration.GetSection("GraphBeta"))
                  .AddInMemoryTokenCaches();
              owinTokenAcquirerFactory.Build();
          }
      }
  }
```

#### まとめ

さまざまなトークン キャッシュの実装を選択できます。 詳細については、GitHub の [Microsoft.Identity.Web - トークン キャッシュのシリアル化](https://aka.ms/ms-id-web/token-cache-serialization)のページを参照してください。

次の図は、*Microsoft.Identity.Web* のさまざまな可能性と、*Startup.cs* ファイルへの影響を示しています。

[Image: Web API を呼び出し、トークン キャッシュの実装を指定するための Startup.cs のサービス構成オプションを示すブロック図]

注意

これらのコード例を完全に理解するために、[ASP.NET Core の基礎](https://learn.microsoft.com/ja-jp/aspnet/core/fundamentals/)、特に[依存関係の挿入](https://learn.microsoft.com/ja-jp/aspnet/core/fundamentals/dependency-injection)と[オプション](https://learn.microsoft.com/ja-jp/aspnet/core/fundamentals/configuration/options)についてよく理解してください。

この記事および以下のコードの例は、[ASP.NET Web アプリ サンプル](https://github.com/Azure-Samples/ms-identity-aspnet-webapp-openidconnect)から抜粋されたものです。 完全な実装の詳細については、そのサンプルをご覧ください。

## [Java](#tab/java)
### Java コード サンプルを実装する

この記事および以下のコードの例は、[Microsoft Graph を呼び出す Java Web アプリケーション](https://github.com/Azure-Samples/ms-identity-java-webapp) (MSAL for Java を使用する Web アプリ サンプル) から抜粋されています。 そのサンプルでは、現在、MSAL for Java を使用して、承認コードの URL を生成し、Microsoft ID プラットフォームに対する承認エンドポイントへのナビゲーションを処理しています。 また、Sprint セキュリティを使用してユーザーをサインインさせることもできます。 完全な実装の詳細については、そのサンプルをご覧ください。

## [Node.js](#tab/nodejs)
### Node.js コード サンプルを実装する

この記事と次のコード例は、[Node.js Microsoft Graph](https://github.com/Azure-Samples/ms-identity-node) を呼び出す Express.js Web アプリケーション (MSAL Node を使用する Web アプリ サンプル) から抜粋されています。

そのサンプルでは、現在、MSAL ノードを使用して、承認コードの URL を生成し、Microsoft ID プラットフォームに対する承認エンドポイントへのナビゲーションを処理しています。 これを次に示します。

```js
/**
 * Prepares the auth code request parameters and initiates the first leg of auth code flow
 * @param req: Express request object
 * @param res: Express response object
 * @param next: Express next function
 * @param authCodeUrlRequestParams: parameters for requesting an auth code url
 * @param authCodeRequestParams: parameters for requesting tokens using auth code
 */
redirectToAuthCodeUrl(authCodeUrlRequestParams, authCodeRequestParams, msalInstance) {
    return async (req, res, next) => {
        // Generate PKCE Codes before starting the authorization flow
        const { verifier, challenge } = await this.cryptoProvider.generatePkceCodes();

        // Set generated PKCE codes and method as session vars
        req.session.pkceCodes = {
            challengeMethod: 'S256',
            verifier: verifier,
            challenge: challenge,
        };

        /**
         * By manipulating the request objects below before each request, we can obtain
         * auth artifacts with desired claims. For more information, visit:
         * https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_node.html#authorizationurlrequest
         * https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_node.html#authorizationcoderequest
         **/
        req.session.authCodeUrlRequest = {
            ...authCodeUrlRequestParams,
            responseMode: msal.ResponseMode.FORM_POST, // recommended for confidential clients
            codeChallenge: req.session.pkceCodes.challenge,
            codeChallengeMethod: req.session.pkceCodes.challengeMethod,
        };

        req.session.authCodeRequest = {
            ...authCodeRequestParams,
            code: '',
        };

        try {
            const authCodeUrlResponse = await msalInstance.getAuthCodeUrl(req.session.authCodeUrlRequest);
            res.redirect(authCodeUrlResponse);
        } catch (error) {
            next(error);
        }
    };
}
```

## [Python](#tab/python)
### Python コード サンプルを実装する

この記事内および以下のコード スニペットは、[ID パッケージ](https://github.com/Azure-Samples/ms-identity-python-webapp) (MSAL Python 用ラッパー) を使用して [Microsoft Graph を呼び出す Python Web アプリケーション](https://pypi.org/project/identity/) サンプルから抜粋されています。

そのサンプルでは、identity パッケージを使用して承認コードの URL を生成し、Microsoft ID プラットフォームの承認エンドポイントへのナビゲーションを処理しています。 完全な実装の詳細については、そのサンプルをご覧ください。

---

### 承認コードを引き換えるコード

## [ASP.NET Core](#tab/aspnetcore)
Microsoft.Identity.Web では、正しい OpenID Connect 設定を設定し、コードの受信イベントをサブスクライブして、コードを引き換えることで、コードを簡単にすることができます。 認可コードを引き換えるために、特別なコードは必要ありません。 この仕組みの詳細については、[Microsoft.Identity.Web のソース コード](https://github.com/AzureAD/microsoft-identity-web/blob/c29f1a7950b940208440bebf0bcb524a7d6bee22/src/Microsoft.Identity.Web/WebAppExtensions/WebAppCallsWebApiAuthenticationBuilderExtensions.cs#L140)をご覧ください。

## [ASP.NET](#tab/aspnet)
"Microsoft.Identity.Web.OWIN" では、正しい OpenID Connect を設定し、コードの受信イベントをサブスクライブして、コードを引き換えることで、コードを簡素化できます。 認可コードを引き換えるために、特別なコードは必要ありません。 この仕組みの詳細については、[Microsoft.Identity.Web のソース コード](https://github.com/AzureAD/microsoft-identity-web/blob/9fdcf15c66819b31b1049955eed5d3e5391656f5/src/Microsoft.Identity.Web.OWIN/AppBuilderExtension.cs#L95)をご覧ください。

## [Node.js](#tab/nodejs)
*AuthProvider* クラスの **handleRedirect** メソッドは、Microsoft Entra ID から受信した認証コードを処理します。 これを次に示します。

```js
handleRedirect(options = {}) {
    return async (req, res, next) => {
        if (!req.body || !req.body.state) {
            return next(new Error('Error: response not found'));
        }

        const authCodeRequest = {
            ...req.session.authCodeRequest,
            code: req.body.code,
            codeVerifier: req.session.pkceCodes.verifier,
        };

        try {
            const msalInstance = this.getMsalInstance(this.msalConfig);

            if (req.session.tokenCache) {
                msalInstance.getTokenCache().deserialize(req.session.tokenCache);
            }

            const tokenResponse = await msalInstance.acquireTokenByCode(authCodeRequest, req.body);

            req.session.tokenCache = msalInstance.getTokenCache().serialize();
            req.session.idToken = tokenResponse.idToken;
            req.session.account = tokenResponse.account;
            req.session.isAuthenticated = true;

            const state = JSON.parse(this.cryptoProvider.base64Decode(req.body.state));
            res.redirect(state.successRedirect);
        } catch (error) {
            next(error);
        }
    }
}
```

## [Java](#tab/java)
アプリが承認コードを受け取った後、 [AuthFilter.java#L51-L56](https://github.com/Azure-Samples/ms-identity-java-webapp/blob/d55ee4ac0ce2c43378f2c99fd6e6856d41bdf144/src/main/java/com/microsoft/azure/msalwebsample/AuthFilter.java#L51-L56):

1. `AuthHelper.processAuthenticationCodeRedirect` の [AuthHelper.java#L67-L97](https://github.com/Azure-Samples/ms-identity-java-webapp/blob/d55ee4ac0ce2c43378f2c99fd6e6856d41bdf144/src/main/java/com/microsoft/azure/msalwebsample/AuthHelper.java#L67-L97) メソッドに委任します。
2. `getAuthResultByAuthCode` を呼び出します。

```Java
class AuthHelper {
  // Code omitted
  void processAuthenticationCodeRedirect(HttpServletRequest httpRequest, String currentUri, String fullUrl)
            throws Throwable {

  // Code omitted
  AuthenticationResponse authResponse = AuthenticationResponseParser.parse(new URI(fullUrl), params);

  // Code omitted
  IAuthenticationResult result = getAuthResultByAuthCode(
                    httpRequest,
                    oidcResponse.getAuthorizationCode(),
                    currentUri);

// Code omitted
  }
}
```

`getAuthResultByAuthCode` メソッドは [AuthHelper.java#L176](https://github.com/Azure-Samples/ms-identity-java-webapp/blob/d55ee4ac0ce2c43378f2c99fd6e6856d41bdf144/src/main/java/com/microsoft/azure/msalwebsample/AuthHelper.java#L176) で定義されています。 MSAL の `ConfidentialClientApplication` を作成した後、承認コードから作成された `acquireToken()` を使用して `AuthorizationCodeParameters` を呼び出します。

```Java
   private IAuthenticationResult getAuthResultByAuthCode(
            HttpServletRequest httpServletRequest,
            AuthorizationCode authorizationCode,
            String currentUri) throws Throwable {

        IAuthenticationResult result;
        ConfidentialClientApplication app;
        try {
            app = createClientApplication();

            String authCode = authorizationCode.getValue();
            AuthorizationCodeParameters parameters = AuthorizationCodeParameters.builder(
                    authCode,
                    new URI(currentUri)).
                    build();

            Future<IAuthenticationResult> future = app.acquireToken(parameters);

            result = future.get();
        } catch (ExecutionException e) {
            throw e.getCause();
        }

        if (result == null) {
            throw new ServiceUnavailableException("authentication result was null");
        }

        SessionManagementHelper.storeTokenCacheInSession(httpServletRequest, app.tokenCache().serialize());

        return result;
    }

    private ConfidentialClientApplication createClientApplication() throws MalformedURLException {
        return ConfidentialClientApplication.builder(clientId, ClientCredentialFactory.create(clientSecret)).
                authority(authority).
                build();
    }
```

## [Python](#tab/python)
[「チュートリアル: Microsoft ID プラットフォームを使用して Python Flask Web アプリにユーザーをサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-python-flask-sign-in-out)させる」を参照してください

Microsoft サインイン画面は、アプリ登録で指定された `/getAToken` URL に承認コードを送信します。 `auth_response` ルートは、`auth.complete_login` を呼び出して承認コードを処理し、エラーを返すか、ホーム ページにリダイレクトして、その URL を処理します。

```python
@app.route(app_config.REDIRECT_PATH)
def auth_response():
    result = auth.complete_log_in(request.args)
    if "error" in result:
        return render_template("auth_error.html", result=result)
    return redirect(url_for("index"))
```

そのコードの完全なコンテキストについては、*app.py* に関するページを参照してください。

---

機密クライアント アプリケーションでは、クライアント シークレットではなく、クライアント証明書またはクライアント アサーションを使用してその ID を証明することもできます。 クライアント アサーションの使用は高度なシナリオであり、詳細については[クライアント アサーション](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/msal-net-client-assertions)に関するページをご覧ください。

### トークンのキャッシュ

重要

Web アプリまたは Web API でのトークン キャッシュの実装は、[ファイル ベース](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization)であることが多いデスクトップ アプリケーションでの実装とは異なります。 セキュリティとパフォーマンスのため、Web アプリと Web API では、ユーザー アカウントごとに 1 つのトークン キャッシュが存在することが重要です。 アカウントごとにトークン キャッシュをシリアル化する必要があります。

## [ASP.NET Core](#tab/aspnetcore)
ASP.NET Core のチュートリアルでは、依存関係の挿入を使用して、アプリケーションの Startup.cs ファイルでトークン キャッシュの実装を決定できます。 Microsoft.Identity.Web には、「[トークン キャッシュのシリアル化](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization)」で説明されている構築済みのトークン キャッシュ シリアライザーが付属しています。 興味深い可能性として、ASP.NET Core の[分散メモリ キャッシュ](https://learn.microsoft.com/ja-jp/aspnet/core/performance/caching/distributed#distributed-memory-cache)を選択できます。

```csharp
// Use a distributed token cache by adding:
    services.AddMicrosoftIdentityWebAppAuthentication(Configuration, "AzureAd")
            .EnableTokenAcquisitionToCallDownstreamApi(
                initialScopes: new string[] { "user.read" })
            .AddDistributedTokenCaches();

// Then, choose your implementation.
// For instance, the distributed in-memory cache (not cleared when you stop the app):
services.AddDistributedMemoryCache();

// Or a Redis cache:
services.AddStackExchangeRedisCache(options =>
{
 options.Configuration = "localhost";
 options.InstanceName = "SampleInstance";
});

// Or even a SQL Server token cache:
services.AddDistributedSqlServerCache(options =>
{
 options.ConnectionString = _config["DistCache_ConnectionString"];
 options.SchemaName = "dbo";
 options.TableName = "TestCache";
});
```

トークン キャッシュ プロバイダーの詳細については、Microsoft.Identity.Web の[トークン キャッシュのシリアル化](https://aka.ms/ms-id-web/token-cache-serialization)に関する記事、および Web アプリのチュートリアルの [ASP.NET Core Web アプリのチュートリアル | トークン キャッシュ](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/tree/master/2-WebApp-graph-user/2-2-TokenCache)のフェーズもご覧ください。

## [ASP.NET](#tab/aspnet)
ASP.NET のチュートリアルでは、依存関係の挿入を使用して、アプリケーションの "Startup.Auth.cs" ファイルでトークン キャッシュの実装を決定できます。 "Microsoft.Identity.Web" には、*トークン キャッシュのシリアル化*に関するページで説明されている構築済みのトークン キャッシュ シリアライザーが付属しています。 興味深い可能性として、ASP.NET Core の[分散メモリ キャッシュ](https://learn.microsoft.com/ja-jp/aspnet/core/performance/caching/distributed#distributed-memory-cache)を選択できます。

```csharp
var services = owinTokenAcquirerFactory.Services;
// Use a distributed token cache by adding:
services.AddDistributedTokenCaches();

// Then, choose your implementation.
// For instance, the distributed in-memory cache (not cleared when you stop the app):
services.AddDistributedMemoryCache();

// Or a Redis cache:
services.AddStackExchangeRedisCache(options =>
{
 options.Configuration = "localhost";
 options.InstanceName = "SampleInstance";
});

// Or even a SQL Server token cache:
services.AddDistributedSqlServerCache(options =>
{
 options.ConnectionString = _config["DistCache_ConnectionString"];
 options.SchemaName = "dbo";
 options.TableName = "TestCache";
});
```

トークン キャッシュ プロバイダーの詳細については、*Microsoft.Identity.Web* の[トークン キャッシュのシリアル化](https://aka.ms/ms-id-web/token-cache-serialization)に関する記事、および Web アプリのチュートリアルの [ASP.NET Core Web アプリのチュートリアル | トークン キャッシュ](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/tree/master/2-WebApp-graph-user/2-2-TokenCache)のフェーズもご覧ください。

詳細については、[MSAL.NET のトークン キャッシュ シリアル化](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization)に関するページを参照してください。

## [Java](#tab/java)
MSAL Java では、トークン キャッシュをシリアル化および逆シリアル化するメソッドが提供されています。 Java のサンプルでは、セッションからのシリアル化を処理する方法が、`getAuthResultBySilentFlow` の [AuthHelper.java#L99-L122](https://github.com/Azure-Samples/ms-identity-java-webapp/blob/d55ee4ac0ce2c43378f2c99fd6e6856d41bdf144/src/main/java/com/microsoft/azure/msalwebsample/AuthHelper.java#L99-L122) メソッドに示されています。

```Java
IAuthenticationResult getAuthResultBySilentFlow(HttpServletRequest httpRequest, HttpServletResponse httpResponse)
      throws Throwable {

  IAuthenticationResult result =  SessionManagementHelper.getAuthSessionObject(httpRequest);

  IConfidentialClientApplication app = createClientApplication();

  Object tokenCache = httpRequest.getSession().getAttribute("token_cache");
  if (tokenCache != null) {
      app.tokenCache().deserialize(tokenCache.toString());
  }

  SilentParameters parameters = SilentParameters.builder(
          Collections.singleton("User.Read"),
          result.account()).build();

  CompletableFuture<IAuthenticationResult> future = app.acquireTokenSilently(parameters);
  IAuthenticationResult updatedResult = future.get();

  // Update session with latest token cache.
  SessionManagementHelper.storeTokenCacheInSession(httpRequest, app.tokenCache().serialize());

  return updatedResult;
}
```

`SessionManagementHelper` クラスの詳細は、[MSAL for Java のサンプル](https://github.com/Azure-Samples/ms-identity-java-webapp/blob/d55ee4ac0ce2c43378f2c99fd6e6856d41bdf144/src/main/java/com/microsoft/azure/msalwebsample/SessionManagementHelper.java)で提供されています。

## [Node.js](#tab/nodejs)
Node.js サンプルでは、​​アプリケーション セッションを使用してトークン キャッシュを保存します。 MSAL ノード キャッシュ メソッドを使用すると、トークン要求が行われる前にセッション内のトークン キャッシュが読み取られ、トークン要求が正常に完了すると更新されます。 これを次に示します。

```js
acquireToken(options = {}) {
    return async (req, res, next) => {
        try {
            const msalInstance = this.getMsalInstance(this.msalConfig);

            /**
             * If a token cache exists in the session, deserialize it and set it as the 
             * cache for the new MSAL CCA instance. For more, see: 
             * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/caching.md
             */
            if (req.session.tokenCache) {
                msalInstance.getTokenCache().deserialize(req.session.tokenCache);
            }

            const tokenResponse = await msalInstance.acquireTokenSilent({
                account: req.session.account,
                scopes: options.scopes || [],
            });

            /**
             * On successful token acquisition, write the updated token 
             * cache back to the session. For more, see: 
             * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/caching.md
             */
            req.session.tokenCache = msalInstance.getTokenCache().serialize();
            req.session.accessToken = tokenResponse.accessToken;
            req.session.idToken = tokenResponse.idToken;
            req.session.account = tokenResponse.account;

            res.redirect(options.successRedirect);
        } catch (error) {
            if (error instanceof msal.InteractionRequiredAuthError) {
                return this.login({
                    scopes: options.scopes || [],
                    redirectUri: options.redirectUri,
                    successRedirect: options.successRedirect || '/',
                })(req, res, next);
            }

            next(error);
        }
    };
}
```

## [Python](#tab/python)
Python サンプルでは、ストレージにグローバル `session` オブジェクトを使用して、identity パッケージによってトークン キャッシュが処理されます。

Flask には Cookie に格納されているセッションのサポートが組み込まれていますが、ID Cookie の長さにより、サンプルでは代わりに [Flask セッション](https://flask-session.readthedocs.io/) パッケージが使用されます。 すべては *app.py* で初期化されます。

```python
import identity
import identity.web
import requests
from flask import Flask, redirect, render_template, request, session, url_for
from flask_session import Session

import app_config

app = Flask(__name__)
app.config.from_object(app_config)
Session(app)

auth = identity.web.Auth(
    session=session,
    authority=app.config["AUTHORITY"],
    client_id=app.config["CLIENT_ID"],
    client_credential=app.config["CLIENT_SECRET"],
)
```

`SESSION_TYPE="filesystem"` の `app_config.py` 設定により、Flask セッション パッケージはローカル ファイル システムを使用してセッションを格納します。

運用環境では、"sqlachemy" や "redis" など、アプリの複数のインスタンスとデプロイにまたがって保持される[設定](https://flask-session.readthedocs.io/en/latest/#configuration)を使用する必要があります。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-web-app-call-api-call-api"} -->
## Web アプリから Web API を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-call-api-call-api
- Service: identity-platform / workforce
- Article date: 2025-03-21
- Summary: Microsoft ID プラットフォームを使用して保護された Web API を呼び出す Web アプリを構築する方法について説明します。 ASP.NET Core、ASP.NET、Java、Node.js、Python のオプションについて説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

トークンを取得すると、保護された Web API を呼び出せます。 通常、Web アプリのコントローラーまたはページからダウンストリーム API を呼び出します。

### 保護された Web API を呼び出す

保護された Web API の呼び出しは、選択した言語とフレームワークによって異なります。

## [ASP.NET Core](#tab/aspnetcore)
*Microsoft.Identity.Web* を使用する場合、API を呼び出すための使用オプションが 3 つあります。

- オプション 1: Microsoft Graph SDK を使用して Microsoft Graph を呼び出す
- オプション 2: ヘルパー クラスを使用してダウンストリーム Web API を呼び出す
- オプション 3:ヘルパー クラスを使用せずにダウンストリーム Web API を呼び出す

##### オプション 1: SDK を使用して Microsoft Graph を呼び出す

このシナリオでは、**Code 構成**に関するページで説明されているように、`.AddMicrosoftGraph()` NuGet パッケージを追加し、 を [Startup.cs](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-call-api-app-configuration#option-1-call-microsoft-graph) にを含めることで、Microsoft Graph を呼び出します。 その後、`GraphServiceClient` をコントローラーまたはページ コンストラクターに直接挿入し、それをアクションで使用することができます。 次の例の Razor ページには、サインインしたユーザーの写真が表示されます。

```csharp
[Authorize]
[AuthorizeForScopes(Scopes = new[] { "user.read" })]
public class IndexModel : PageModel
{
 private readonly GraphServiceClient _graphServiceClient;

 public IndexModel(GraphServiceClient graphServiceClient)
 {
    _graphServiceClient = graphServiceClient;
 }

 public async Task OnGet()
 {
  var user = await _graphServiceClient.Me.GetAsync();
  try
  {
   using (var photoStream = await _graphServiceClient.Me.Photo.Content.GetAsync())
   {
    byte[] photoByte = ((MemoryStream)photoStream).ToArray();
    ViewData["photo"] = Convert.ToBase64String(photoByte);
   }
   ViewData["name"] = user.DisplayName;
  }
  catch (Exception)
  {
   ViewData["photo"] = null;
  }
 }
}
```

詳細なサンプルについては、[Microsoft Graph を呼び出す ASP.NET Core Web アプリ](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/2-WebApp-graph-user/2-1-Call-MSGraph/README.md)に関するページを参照してください。

##### オプション 2:ヘルパー クラスを使用してダウンストリーム Web API を呼び出す

Microsoft Graph 以外の Web API を呼び出す必要があります。 この場合、`AddDownstreamApi`に関するページで説明されているように、 を [Startup.cs](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-call-api-app-configuration#option-2-call-a-downstream-web-api-other-than-microsoft-graph) に追加し、`IDownstreamApi` サービスをコントローラーまたはページ コンストラクターに直接挿入して、それをアクションで使用することができます。

```csharp
[Authorize]
[AuthorizeForScopes(ScopeKeySection = "TodoList:Scopes")]
public class TodoListController : Controller
{
  private IDownstreamApi _downstreamApi;
  private const string ServiceName = "TodoList";

  public TodoListController(IDownstreamApi downstreamApi)
  {
    _downstreamApi = downstreamApi;
  }

  public async Task<ActionResult> Details(int id)
  {
    var value = await _downstreamApi.CallApiForUserAsync(
      ServiceName,
      options =>
      {
        options.RelativePath = $"me";
      });
      return View(value);
  }
}
```

また、`CallWebApiForUserAsync` には、オブジェクトを直接受け取ることができる、厳密に型指定されたジェネリック オーバーライドもあります。 たとえば、次のメソッドは `Todo` インスタンスを受け取ります。これは、Web API から返された JSON の厳密に型指定された表現です。

```csharp
    // GET: TodoList/Details/5
    public async Task<ActionResult> Details(int id)
    {
        var value = await _downstreamApi.CallApiForUserAsync<object, Todo>(
            ServiceName,
            null,
            options =>
            {
                options.HttpMethod = HttpMethod.Get;
                options.RelativePath = $"api/todolist/{id}";
            });
        return View(value);
    }
```

詳細なサンプルについては、[API を呼び出す ASP.NET Core Web アプリ](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/tree/master/4-WebApp-your-API/4-1-MyOrg)を参照してください

##### オプション 3:ヘルパー クラスを使用せずにダウンストリーム Web API を呼び出す

`IAuthorizationHeaderProvider` サービスを使用して手動でトークンを取得することにしました。そのため、トークンを使用する必要があります。 この場合、次のコードは、「[Web API を呼び出す Web アプリ: アプリのトークンを取得する](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-call-api-acquire-token)」で示されているコード例の続きです。 このコードは、Web アプリ コントローラーのアクションで呼び出されます。

トークンを取得したら、それをベアラー トークンとして使用してダウンストリーム API (この場合は Microsoft Graph) を呼び出します。

```csharp
public async Task<IActionResult> Profile()
{
  // Acquire the access token.
  string[] scopes = new string[]{"user.read"};
  string authorizationHeader = await IAuthorizationHeaderProvider.GetAuthorizationHeaderForUserAsync(scopes);

  // Use the access token to call a protected web API.
  HttpClient httpClient = new HttpClient();
  client.DefaultRequestHeaders.Add("Authorization", authorizationHeader);

  var response = await httpClient.GetAsync($"{webOptions.GraphApiUrl}/beta/me");

  if (response.StatusCode == HttpStatusCode.OK)
  {
    var content = await response.Content.ReadAsStringAsync();

    dynamic me = JsonConvert.DeserializeObject(content);
    ViewData["Me"] = me;
  }

  return View();
}
```

注意

同じ原則を使用するとどの web API も呼び出すことができます。

ほとんどの Azure Web API には、Microsoft Graph の場合のように、API の呼び出しを簡略化する SDK が用意されています。

## [ASP.NET](#tab/aspnet)
*Microsoft.Identity.Web* を使用する場合、API を呼び出すための使用オプションが 3 つあります。

- オプション 1: Microsoft Graph SDK を使用して Microsoft Graph を呼び出す
- オプション 2: ヘルパー クラスを使用してダウンストリーム Web API を呼び出す
- オプション 3:ヘルパー クラスを使用せずにダウンストリーム Web API を呼び出す

##### オプション 1: OWIN アプリの SDK を使用して Microsoft Graph を呼び出す

Microsoft Graph を呼び出す必要があります。 このシナリオでは、`AddMicrosoftGraph`で指定されているとおりに *Startup.cs* に  を追加し、コントローラーに対して `GraphServiceClient` 拡張メソッドを使用することで、コントローラーまたはページ コンストラクターで `GetGraphServiceClient()` を取得してアクションで使用できます。 サインインしたユーザーの画像を次の例に示します。

```csharp
[Authorize]
[AuthorizeForScopes(Scopes = new[] { "user.read" })]
public class HomeController : Controller
{

 public async Task GetIndex()
 {
  var graphServiceClient = this.GetGraphServiceClient();
  var user = await graphServiceClient.Me.GetAsync();
  try
  {
   using (var photoStream = await graphServiceClient.Me.Photo.Content.GetAsync())
   {
    byte[] photoByte = ((MemoryStream)photoStream).ToArray();
    ViewData["photo"] = Convert.ToBase64String(photoByte);
   }
   ViewData["name"] = user.DisplayName;
  }
  catch (Exception)
  {
   ViewData["photo"] = null;
  }
 }
}
```

詳細なサンプルについては、[Microsoft Graph を呼び出す ASP.NET OWIN Web アプリ](https://github.com/Azure-Samples/ms-identity-aspnet-webapp-openidconnect)を参照してください

##### オプション 2: ヘルパー クラスを使用して OWIN アプリからダウンストリーム Web API を呼び出す

Microsoft Graph 以外の Web API を呼び出す必要があります。 その場合は、`AddDownstreamApi`で指定されているように *Startup.cs* に  を追加し、コントローラーに対して `IDownstreamApi` 拡張メソッドを呼び出すことで、コントローラーで `GetDownstreamApi` サービスを取得できます。

```csharp
[Authorize]
public class TodoListController : Controller
{ 
  public async Task<ActionResult> Details(int id)
  {
    var downstreamApi = this.GetDownstreamApi();
    var value = await downstreamApi.CallApiForUserAsync(
      ServiceName,
      options =>
      {
        options.RelativePath = $"me";
      });
      return View(value);
  }
}
```

また、`CallApiForUserAsync` には、オブジェクトを直接受け取ることができる、厳密に型指定されたジェネリック オーバーライドもあります。 たとえば、次のメソッドは `Todo` インスタンスを受け取ります。これは、Web API から返された JSON の厳密に型指定された表現です。

```csharp
    // GET: TodoList/Details/5
    public async Task<ActionResult> Details(int id)
    {
        var downstreamApi = this.GetDownstreamApi();
        var value = await downstreamApi.CallApiForUserAsync<object, Todo>(
            ServiceName,
            null,
            options =>
            {
                options.HttpMethod = HttpMethod.Get;
                options.RelativePath = $"api/todolist/{id}";
            });
        return View(value);
    }
```

##### オプション 3: ヘルパー クラスなしで OWIN アプリからダウンストリーム Web API を呼び出す

`IAuthorizationHeaderProvider` サービスを使用して承認ヘッダーを取得することにしました。現在、HttpClient または HttpRequest でそれを使用する必要があります。 この場合、次のコードは、「[Web API を呼び出す Web アプリ: アプリのトークンを取得する](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-call-api-acquire-token)」で示されているコード例の続きです。 このコードは、Web アプリ コントローラーのアクションで呼び出されます。

```csharp
public async Task<IActionResult> Profile()
{
  // Acquire the access token.
  string[] scopes = new string[]{"user.read"};
  var IAuthorizationHeaderProvider = this.GetAuthorizationHeaderProvider();
  string authorizationHeader = await IAuthorizationHeaderProvider.GetAuthorizationHeaderForUserAsync(scopes);

  // Use the access token to call a protected web API.
  HttpClient httpClient = new HttpClient();
  client.DefaultRequestHeaders.Add("Authorization", authorizationHeader);

  var response = await httpClient.GetAsync($"{webOptions.GraphApiUrl}/beta/me");

  if (response.StatusCode == HttpStatusCode.OK)
  {
    var content = await response.Content.ReadAsStringAsync();

    dynamic me = JsonConvert.DeserializeObject(content);
    ViewData["Me"] = me;
  }

  return View();
}
```

## [ジャワ](#tab/java)
```java
private String getUserInfoFromGraph(String accessToken) throws Exception {
    // Microsoft Graph user endpoint
    URL url = new URL("https://graph.microsoft.com/v1.0/me");

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    // Set the appropriate header fields in the request header.
    conn.setRequestProperty("Authorization", "Bearer " + accessToken);
    conn.setRequestProperty("Accept", "application/json");

    String response = HttpClientHelper.getResponseStringFromConn(conn);

    int responseCode = conn.getResponseCode();
    if(responseCode != HttpURLConnection.HTTP_OK) {
        throw new IOException(response);
    }

    JSONObject responseObject = HttpClientHelper.processResponse(responseCode, response);
    return responseObject.toString();
}
```

## [Node.js](#tab/nodejs)
トークンの取得に成功すると、コードは **axios** パッケージを使用して API エンドポイントにクエリを実行し、JSON 結果を取得します。

```js
/**
 * Attaches a given access token to a MS Graph API call
 * @param endpoint: REST API endpoint to call
 * @param accessToken: raw access token string
 */
async function fetch(endpoint, accessToken) {
    const options = {
        headers: {
            Authorization: `Bearer ${accessToken}`
        }
    };

    console.log(`request made to ${endpoint} at: ` + new Date().toString());

    try {
        const response = await axios.get(endpoint, options);
        return await response.data;
    } catch (error) {
        throw new Error(error);
    }
}
```

## [Python](#tab/python)
トークンを正常に取得すると、コードで要求パッケージを使用して API エンドポイントに対してクエリを実行し、JSON 結果を取得します。

```python
@app.route("/call_downstream_api")
def call_downstream_api():
    token = auth.get_token_for_user(app_config.SCOPE)
    if "error" in token:
        return redirect(url_for("login"))
    # Use access token to call downstream api
    api_result = requests.get(
        app_config.ENDPOINT,
        headers={'Authorization': 'Bearer ' + token['access_token']},
        timeout=30,
    ).json()
    return render_template('display.html', result=api_result)
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scenario-web-app-call-api-sign-in"} -->
## サインアウト時にトークン キャッシュからアカウントを削除する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-call-api-sign-in
- Service: identity-platform / workforce
- Article date: 2025-03-21
- Summary: Microsoft ID プラットフォームを使用して Web API を呼び出す Web アプリでグローバル サインアウト中にトークン キャッシュからアカウントを削除する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

サインアウトは、Web API を呼び出す Web アプリでは異なります。 ユーザーがアプリケーションまたは任意のアプリケーションからサインアウトするときに、トークン キャッシュからそのユーザーに関連付けられているトークンを削除する必要があります。 Web アプリでサインインを実装する方法の詳細については、 [サンプル Web アプリでのユーザー](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-sign-in) のサインインに関するページを参照してください。

### シングル サインアウト後にコールバックをインターセプトする

サインアウトしたアカウントに関連付けられているトークン キャッシュ エントリをクリアするには、アプリケーションで `logout` 後のイベントを受け取ることができます。 Web アプリは、各ユーザーのアクセス トークンをトークン キャッシュに格納します。 Web アプリケーションは、`logout` 後のコールバックをインターセプトすることにより、キャッシュからユーザーを削除できます。

## [ASP.NET Core](#tab/aspnetcore)
Microsoft.Identity.Web では、サインアウトの実装が自動処理されます。 詳細については、[Microsoft.Identity.Web のソース コード](https://github.com/AzureAD/microsoft-identity-web/blob/c29f1a7950b940208440bebf0bcb524a7d6bee22/src/Microsoft.Identity.Web/WebAppExtensions/WebAppCallsWebApiAuthenticationBuilderExtensions.cs#L168-L176)を参照してください。

## [ASP.NET](#tab/aspnet)
ASP.NET のサンプルでは、グローバル サインアウト時にアカウントはキャッシュから削除されません。

## [ジャワ](#tab/java)
Java のサンプルでは、グローバル サインアウト時にアカウントはキャッシュから削除されません。

## [Node.js](#tab/nodejs)
Node サンプルでは、​​グローバル サインアウト時にキャッシュからアカウントが削除されません。

## [Python](#tab/python)
Python のサンプルでは、グローバル サインアウト時にアカウントはキャッシュから削除されません。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/schema-extensions"} -->
## 要求中のディレクトリ拡張属性 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/schema-extensions
- Service: identity-platform
- Article date: 2023-05-26
- Summary: トークン要求でユーザー データをアプリケーションに送信するために使用されるディレクトリ拡張属性について説明します。

ディレクトリ拡張属性は、ユーザーなどのディレクトリ オブジェクトにさらに多くのデータを格納する方法を提供します。 アプリケーションに対する要求の出力に使用できるのは、ユーザー オブジェクトの拡張属性だけです。 この記事では、トークン要求でユーザー データをアプリケーションに送信するために、ディレクトリ拡張属性を使用する方法について説明します。

注

Microsoft Graph には、Graph オブジェクトをカスタマイズするためのその他 3 つの拡張メカニズムが用意されています。 これらは、拡張属性 1 から 15、オープン拡張機能、およびスキーマ拡張機能です。 詳細については、[Microsoft Graph のドキュメント](https://learn.microsoft.com/ja-jp/graph/extensibility-overview)を参照してください。 オープンとスキーマの拡張機能を使用して Microsoft Graph オブジェクトに格納されたデータは、トークン内の要求のソースとしては使用できません。

ディレクトリ拡張属性は常にテナント内のアプリケーションと関連付けられます。 ディレクトリ属性の名前には、その名前にアプリケーションの *appId* が含まれます。

ディレクトリ拡張属性の識別子の形式は、"Extension\_xxxxxxxxx\_AttributeName" です。`extension_xxxxxxxxx_AttributeName` ここで、"xxxxxxxxx" は、拡張子が定義されたアプリケーションの "appId" であり、文字 0 から 9 と A から Z のみが含まれます。`xxxxxxxxx`

### ディレクトリ拡張を登録して使用する

次のいずれかの方法でディレクトリ拡張属性を登録します。

- Microsoft Entra Connect を構成して、それらを作成し、オンプレミスからデータを同期します。 「[Microsoft Entra Connect Sync ディレクトリ拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions)」を参照してください。
- Microsoft Graph を使用して、[ディレクトリ拡張](https://learn.microsoft.com/ja-jp/graph/extensibility-overview#directory-azure-ad-extensions)を登録し、その値を設定して、そこから読み取りを行います。 [PowerShell コマンドレット](https://learn.microsoft.com/ja-jp/powershell/azure/active-directory/using-extension-attributes-sample)も使用できます。

#### Microsoft Entra Connect からのデータを使用して要求を送信する

Microsoft Entra Connect を使用して作成および同期されるディレクトリ拡張属性は、Microsoft Entra Connect によって使用されるアプリケーション ID と常に関連付けられます。 これらの属性は、ポータルの**エンタープライズ アプリケーション**の構成で要求として構成することで、両方の要求のソースとして使用できます。 AD Connect を使用してディレクトリ拡張属性を作成すると、SAML SSO 要求構成に表示されます。

#### Graph または PowerShell を使用して要求を生成する

ディレクトリ拡張属性を Microsoft Graph または PowerShell を使用するように登録すると、アプリケーションはユーザーがサインインする時に、その属性内のデータを受信するように構成できます。 アプリケーション マニフェストで設定できる[オプションの要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)を使用して、アプリケーションに登録されているディレクトリ拡張のデータを受信するようにアプリケーションを構成できます。

その後、マルチテナント アプリケーションで、独自に使用するディレクトリ拡張属性を登録できます。 アプリケーションがテナントにプロビジョニングされると、関連付けられているディレクトリ拡張は、そのテナント内のユーザーが利用可能になり使用されます。 ディレクトリ拡張を使用すると、Microsoft Graph を使用してデータを格納および取得できます。 ディレクトリ拡張は、Microsoft Identity プラットフォームがアプリケーションに出力するトークン内の要求にマップすることもできます。

別のアプリケーションに登録されている拡張属性からのデータを使用して、アプリケーションから要求を送信する必要がある場合は、[要求 マッピング ポリシー](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)を使用して、拡張属性を要求にマッピングする必要があります。

ディレクトリ拡張属性を管理するための一般的なパターンは、必要なすべてのディレクトリ拡張のための専用のアプリケーションを登録することです。 この種類のアプリケーションを使用すると、すべての拡張機能の名前に同じ appID が含まれます。

たとえば、次のコードが示すのは、OAuth/OIDC トークンのディレクトリ拡張属性から 1 つの要求を生成するための要求マッピング ポリシーです。

```json
{
    "ClaimsMappingPolicy": {
        "Version": 1,
        "IncludeBasicClaimSet": "false",
        "ClaimsSchema": [{
                "Source": "User",
                "ExtensionID": "extension_xxxxxxx_test",
                "JWTClaimType": "http://schemas.contoso.com/identity/claims/exampleclaim"
            },
        ]
    }
}
```

ここで、"xxxxxxx" は、拡張機能が登録されたアプリケーションの appID (またはクライアント ID) です。`xxxxxxx`

Warnung

上の例で示す通り、ディレクトリの拡張属性に対する要求 マッピング ポリシーを定義するときは、`ExtensionID` 配列の本体の中で `ID` プロパティの代わりに `ClaimsSchema` プロパティを使用します。

ヒント

オブジェクトにディレクトリ拡張属性を設定するときは、大文字と小文字の区別の整合性が重要です。 拡張属性名は、設定時には大文字と小文字が区別されませんが、トークン サービスがディレクトリから読み取るときは大文字と小文字が区別されます。 拡張属性がユーザー オブジェクトに "LegacyId" という名前で設定され、別のユーザー オブジェクトに "legacyid" という名前で設定された場合、この属性が要求に "LegacyId" という名前を使用してマッピングされると、最初のユーザーに対してはデータが正常に取得され、要求はトークンに含まれますが、2 番目のユーザーに対してはこれが行われません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/scopes-oidc"} -->
## Microsoft ID プラットフォームでのスコープとアクセス許可 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/scopes-oidc
- Service: identity-platform
- Article date: 2025-07-24
- Summary: Microsoft ID プラットフォーム エンドポイントの openID 接続スコープとアクセス許可について説明します。

Microsoft ID プラットフォームは [、OAuth 2.0](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols) 承認プロトコルを実装します。 OAuth 2.0 は、ユーザーに代わってサードパーティのアプリが Web でホストされるリソースにアクセスできる方法です。 Web でホストされる任意のリソースは、Microsoft ID プラットフォームと統合されると、リソース識別子つまり "*アプリケーション ID URI*" を保有します。

この記事では、ID プラットフォームのスコープとアクセス許可について説明します。

次のリストに、Microsoft Web でホストされるリソースの例をいくつか示します。

- Microsoft Graph: `https://graph.microsoft.com`
- Microsoft 365 メール API: `https://outlook.office.com`
- Azure Key Vault: `https://vault.azure.net`

Microsoft ID プラットフォームと統合されるサード パーティのリソースについても同様です。 これらのリソースは、そのリソースの機能をより小さなチャンクに分割するアクセス許可のセットを定義することもできます。 たとえば、 [Microsoft Graph](https://graph.microsoft.com) では、次のタスクを実行するためのアクセス許可が定義されています。

- ユーザーの予定表の読み取り
- ユーザーの予定表への書き込み
- ユーザーとしてのメールの送信

これらのアクセス許可定義により、リソースはデータと API 機能の公開方法をきめ細かく制御できます。 サード パーティのアプリでは、ユーザーや管理者にこれらのアクセス許可を要求でき、ユーザーや管理者が承認してからでなければ、アプリはデータにアクセスしたり、ユーザーの代理として動作したりできません。

リソースの機能がきめ細かいアクセス許可に分割されている場合、サードパーティ製アプリを構築して、その機能を実行するために必要なアクセス許可のみを要求できます。 ユーザーと管理者は、アプリによってアクセスできるデータを把握できます。 また、アプリが悪意のある目的を持って動作していないことも確信できます。 開発者は常に最小限の特権の原則に従って、アプリケーションが機能するために必要なアクセス許可のみを要求する必要があります。

OAuth 2.0 では、これらのアクセス許可は *スコープ*と呼ばれますが、多くの場合、 *アクセス許可*と呼ばれます。 Microsoft ID プラットフォームでは、アクセス許可は文字列値として表現されます。 `scope` クエリ パラメーターでアクセス許可を指定することによって、アプリが必要なアクセス許可を要求します。 ID プラットフォームは、いくつかの適切に定義された OpenID connect スコープ とリソースベースのアクセス許可をサポートします (各アクセス許可は、リソースの識別子またはアプリケーション ID の URI にアクセス許可値を追加することによって示されます)。 たとえば、アクセス許可文字列 `https://graph.microsoft.com/Calendars.Read` を使用して、Microsoft Graph のユーザーの予定表を読み取るアクセス許可を要求します。

### 管理者によって制限されるアクセス許可

Microsoft ID プラットフォームのアクセス許可は、管理者限定に設定できます。 たとえば、多くのより高い特権の Microsoft Graph のアクセス許可には、管理者の承認が必要です。 アプリが管理者によって制限されたアクセス許可を必要とする場合、組織の管理者は、組織のユーザーの代わりにこれらのスコープに同意する必要があります。 次のセクションでは、これらのアクセス許可の種類の例を示します。

- `User.Read.All`: すべてのユーザーの完全なプロファイルを読み取る
- `Directory.ReadWrite.All`: 組織のディレクトリ データへの書き込み
- `Group.Read.All`: 組織のディレクトリ内の全グループの読み取り

Note

Microsoft ID プラットフォームの認可、トークン、または同意エンドポイントへの要求では、スコープ パラメーターでリソース識別子が省略されている場合、リソースは Microsoft Graph と見なされます。 たとえば、`scope=User.Read` は、`https://graph.microsoft.com/User.Read` と同じです。

コンシューマー ユーザーがこの種類のデータへのアプリケーション アクセスを許可する場合がありますが、組織のユーザーは会社の同じ機密データへのアクセスを許可することはできません。 アプリケーションが組織のユーザーにこれらのアクセス許可のいずれかへのアクセスを要求すると、ユーザーは、アプリのアクセス許可に同意する権限がないという内容のエラー メッセージを受け取ります。

アプリケーションがアプリケーションのアクセス許可を要求していて、管理者がこれらのアクセス許可を付与する場合、この許可は特定のユーザーに代わっては行われません。 代わりに、クライアント アプリケーションに *直接*アクセス許可が付与されます。 これらの種類のアクセス許可は、バックグラウンドで実行されるデーモン サービスと他の非対話型アプリケーションでのみ使用する必要があります。 直接アクセス シナリオの詳細については、[Microsoft ID プラットフォームのアクセス シナリオ](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)に関するページを参照してください。

Web API でスコープを公開する方法のステップ バイ ステップ ガイドについては、「[Web API を公開するようにアプリケーションを構成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-configure-app-expose-web-apis)」を参照してください。

### OpenID Connect のスコープ

OpenID Connect の Microsoft ID プラットフォーム実装には、Microsoft Graph でもホストされている適切に定義されたスコープ `openid`、`email`、`profile`、`offline_access` があります。 `address` と `phone` の OpenID Connect スコープはサポートされていません。 これらのスコープは省略可能で、ID トークン エンリッチメントと見なされる場合があります。 これらのスコープは、ユーザーに対する同意プロンプトで常に別々の行に表示されるとは限りません。

OpenID Connect スコープとトークンを要求すると、 [UserInfo エンドポイント](https://learn.microsoft.com/ja-jp/entra/identity-platform/userinfo)を呼び出すトークンが取得されます。

#### `openid` スコープ

[アプリが OpenID Connect](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols) を使用してサインインする場合は、`openid`スコープを要求する必要があります。 `openid` スコープは、職場アカウントの同意ページでは **サインイン** アクセス許可として表示されます。

アプリは、このアクセス許可を使用して、`sub` 要求の形式でユーザーの一意の識別子を受け取ります。 このアクセス許可により、アプリは UserInfo エンドポイントにもアクセスできます。 `openid` スコープは、Microsoft ID プラットフォーム トークン エンドポイントで ID トークンを取得するために使用できます。 これらのトークンは、アプリが認証に使用できます。

#### `email` スコープ

`email` スコープは `openid` スコープやその他のスコープと共に使用できます。 これにより、アプリがユーザーのプライマリ電子メール アドレスに `email` 要求の形式でアクセスできます。

電子メール アドレスがユーザー アカウントと関連付けられている場合のみ (常にではありません)、`email` 要求はトークンに含まれます。 アプリは、`email` スコープを使用する場合は、トークン内に `email` 要求が存在しない状況に対応できる必要があります。

#### `profile` スコープ

`profile` スコープは `openid` スコープやその他のスコープと共に使用できます。 これにより、アプリはユーザーの多くの情報にアクセスできます。 アプリがアクセスできる情報には、ユーザーの名、姓、希望するユーザー名、オブジェクト ID などがありますが、これらに限定されるものではありません。

特定のユーザーに対して`profile`パラメーターで使用できる`id_tokens`要求の完全な一覧については、[`id_tokens`リファレンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens)をご覧ください。

#### `offline_access` スコープ

[`offline_access` スコープ](https://openid.net/specs/openid-connect-core-1_0.html#OfflineAccess)を使用すると、アプリはユーザーの代わりに、長期間にわたってリソースにアクセスできます。 同意ページで、このスコープは、**アクセス権を与えたデータへのアクセスを管理する**アクセス許可として表示されます。

委任されたアクセス許可が付与されている場合、offline\_accessは暗黙的に付与されます。 委任されたアクセス許可が付与されている場合、アプリケーションにoffline\_accessがあると想定できます。 更新トークンの有効期間は長期です。 アプリは、古いアクセス トークンの有効期限が切れると、新しいアクセス トークンを取得できます。

Note

現在、このアクセス許可は、更新トークンを提供しないフロー ( [暗黙的フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-implicit-grant-flow)など) の場合でも、すべての同意ページに表示されます。 この設定は、クライアントが暗黙的フロー内で開始した後、更新トークンが予測されるコード フローに移行することができるシナリオに対応します。

Microsoft ID プラットフォーム (要求は v2.0 エンドポイントに対して行われます) では、更新トークンを受信するには、アプリが `offline_access` スコープを明示的に要求する必要があります。 そのため、[OAuth 2.0 認証コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols)で認証コードを使用すると、`/token` エンドポイントからアクセス トークンを受け取ります。

アクセス トークンは約 1 時間有効です。 その時点で、アプリはユーザーを `/authorize` エンドポイントにリダイレクトして、新しい承認コードを要求する必要があります。 このリダイレクト中に、アプリの種類によっては、ユーザーが資格情報を再入力したり、アクセス許可に再同意したりする必要がある場合もあります。

更新トークンの有効期限はアクセス トークンよりも長く、通常は 90 日間有効です。 更新トークンの取得方法と使用方法の詳細については、[Microsoft ID プラットフォーム プロトコルのリファレンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols)を参照してください。

応答に更新トークンを含めるかどうかは、アプリケーションの特定の構成や、認可プロセス中に要求されたスコープなど、いくつかの要因によって異なります。 応答で更新トークンを受け取れると期待しているのに受け取れない場合は、次のような要素が考えられます。

- **スコープの要件**: 他の必要なスコープと共に `offline_access` スコープを要求していることを確認します。
- **認可付与タイプ**: 認証コード付与タイプを使用すると、更新トークンが提供されます。 フローが異なる場合は、応答が影響を受ける可能性があります。
- **クライアント構成**: ID プラットフォームでアプリケーションの設定を確認します。 特定の構成では、refresh\_tokens の発行が制限される場合があります。

### `.default` スコープ

`.default` スコープは、特定のアクセス許可を特定せずに、要求内のリソース サービス (API) を汎用的に参照するために使用されます。 同意が必要な場合、`.default` を使用すると、アプリケーションの登録に示されているすべての必要なアクセス許可 (一覧内のすべての API) について、同意を求めるメッセージが表示されます。

スコープ パラメーターの値は、リソースの識別子 URI と、スラッシュ (`.default`) で区切られた `/` を使用して構築されます。 たとえば、リソースの識別子 URI が `https://contoso.com` の場合、要求するスコープは `https://contoso.com/.default` です。 トークンを正しく要求するために 2 つ目のスラッシュを含める必要がある場合は、末尾のスラッシュに関するセクションを参照してください。

`scope={resource-identifier}/.default` を使用することは、機能的には v1.0 エンドポイントの `resource={resource-identifier}` と同じです (ここで、`{resource-identifier}` は API の識別子 URI であり、たとえば Microsoft Graph の場合は `https://graph.microsoft.com`)。

`.default`スコープは、任意の OAuth 2.0 フローで使用でき、[管理者の同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-admin-consent)を開始します。 これは[On-Behalf-Of フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow)と[クライアント資格情報フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)で使用する必要があります。

クライアントは、静的 (`.default`) な同意と動的な同意を 1 つの要求に結合することはできません。 そのため、`scope=https://graph.microsoft.com/.default Mail.Read` を指定すると、スコープの種類が結合されるため、エラーになります。

#### ユーザーが同意した場合の `.default`

`.default` スコープ パラメーターにより、サインインしているユーザーの代わりに、クライアントとリソースの間で委任されたアクセス許可に対して同意が付与されていない場合にのみ、同意プロンプトがトリガーされます。

同意が存在する場合、返されるトークンには、サインインしたユーザーのそのリソースに付与されているすべてのスコープが含まれます。 ただし、要求されたリソースに対するアクセス許可が付与されていない場合 (または、`prompt=consent` パラメーターが指定されている場合) は、一覧のすべての API に対して、クライアント アプリケーション登録で構成されているすべての必要なアクセス許可に対する同意プロンプトが表示されます。

たとえば、スコープ `https://graph.microsoft.com/.default` が要求された場合、アプリケーションは Microsoft Graph API のアクセス トークンを要求しています。 サインインしているユーザーに代わって Microsoft Graph に少なくとも 1 つの委任されたアクセス許可が付与されている場合、サインインは続行されます。 そのユーザーに付与されている Microsoft Graph のすべての委任されたアクセス許可が、アクセス トークンに含まれます。 要求されたリソース (この例では Microsoft Graph) に対するアクセス許可が付与されていない場合は、一覧内のすべての API に対して、アプリケーションで構成されているすべての必要なアクセス許可に対する同意プロンプトが表示されます。

##### 例 1:ユーザーまたはテナント管理者がアクセス許可を付与している

この例では、ユーザーまたはテナント管理者が `Mail.Read` と `User.Read` の Microsoft Graph アクセス許可をクライアントに付与しています。

クライアントが `scope=https://graph.microsoft.com/.default` を要求すると、Microsoft Graph に対するクライアント アプリケーションの登録済みアクセス許可の内容に関係なく、同意プロンプトは表示されません。 返されるトークンにはスコープ `Mail.Read` と `User.Read` が含まれます。

##### 例 2:ユーザーがクライアントとリソース間のアクセス許可を付与していない

この例では、ユーザーがクライアントと Microsoft Graph 間の同意を付与しておらず、管理者もいません。 クライアントは、`User.Read` および `Contacts.Read` のアクセス許可に登録し、Azure Key Vault スコープ `https://vault.azure.net/user_impersonation`に登録しました。

クライアントが `scope=https://graph.microsoft.com/.default` のトークンを要求すると、Microsoft Graph の`User.Read` および `Contacts.Read` スコープと、Azure Key Vault の`user_impersonation` スコープの同意ページがユーザーに表示されます。 返されるトークンには `User.Read` および `Contacts.Read` スコープのみが含まれ、これは Microsoft Graph に対してのみ使用できます。

##### 例 3: ユーザーは同意済みで、クライアントが追加のスコープを要求する

この例では、ユーザーは、クライアントの `Mail.Read` に既に同意しています。 クライアントは、`Contacts.Read` スコープに登録されています。

クライアントは、まず、`scope=https://graph.microsoft.com/.default` を使用してサインインを実行します。 応答の `scopes` パラメーターに基づいて、アプリケーションのコードで `Mail.Read` のみが付与されていることが検出されます。 クライアントは `scope=https://graph.microsoft.com/.default` を使用して 2 回目のサインインを開始し、今度は `prompt=consent` を使用して強制的に同意します。 アプリケーションが登録したすべてのアクセス許可に対してユーザーの同意が許可されている場合は、同意プロンプトが表示されます。 (そうでない場合は、エラー メッセージまたは [管理者の同意要求](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-admin-consent-workflow)フォームが表示されます)。同意プロンプトには、`Contacts.Read` と `Mail.Read` の両方があります。 同意が付与され、サインインが続行される場合、返されるトークンは Microsoft Graph 用であり、`Mail.Read` と `Contacts.Read` が含まれます。

#### クライアントで `.default` スコープを使用する

場合によっては、クライアントが独自の `.default` スコープを要求できることがあります。 このシナリオを以下の例で説明します。

```http
// Line breaks are for legibility only.

GET https://login.microsoftonline.com/{tenant}/oauth2/v2.0/authorize
    ?response_type=token            //Code or a hybrid flow is also possible here
    &client_id=00001111-aaaa-2222-bbbb-3333cccc4444
    &scope=9ada6f8a-6d83-41bc-b169-a306c21527a5/.default
    &redirect_uri=https%3A%2F%2Flocalhost
    &state=1234
```

同意と `.default` の前述の説明がこのシナリオに適用される場合、このコード例では登録済みのすべてのアクセス許可の同意ページが生成されます。 この場合、コードによってアクセス トークンではなく `id_token` が返されます。

Microsoft ID プラットフォームを対象とする新しいクライアントでは、このセットアップを使用しないでください。 必ず Azure AD Authentication Library (ADAL) から [Microsoft Authentication Library (MSAL) に移行してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-migration)。

#### クライアント資格情報の付与フローおよび `.default`

`.default`のもう 1 つの用途は、[クライアント資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)付与フローを使用して Web API を呼び出すデーモン アプリのような非対話型アプリケーションでアプリ ロール (アプリケーションのアクセス許可とも呼ばれます) を要求することです。

Web API についてのアプリ ロール (アプリケーションのアクセス許可) を定義するには、[アプリケーションへのアプリ ロールの追加](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)に関する記事を参照してください。

クライアント サービスのクライアント資格情報要求には、*必ず*`scope={resource}/.default`。 ここで、`{resource}` はアプリが呼び出す予定の Web API であり、アクセス トークンを取得する必要があります。 個々のアプリケーションのアクセス許可 (ロール) を使用したクライアント資格情報要求の発行はサポート *されていません* 。 返されるアクセス トークンには、その Web API に付与されているすべてのアプリ ロール (アプリケーションのアクセス許可) が含まれます。

アプリケーション向けの管理者の同意の付与など、定義するアプリ ロールにアクセス権を付与するには、「[Web API にアクセスするようにクライアント アプリケーションを構成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-configure-app-access-web-apis)」を参照してください。

#### 末尾のスラッシュおよび `.default`

一部のリソース URI には末尾にスラッシュが付いています。たとえば、`https://contoso.com/` ではなく `https://contoso.com` のようになります。 末尾にスラッシュがあると、トークンの検証で問題が発生する場合があります。 主に、Azure Resource Manager (`https://management.azure.com/`) のトークンが要求されたときに問題が発生します。

この場合、リソース URI の末尾のスラッシュは、トークンの要求時にスラッシュが必要であることを意味します。 そのため、`https://management.azure.com/` のトークンを要求し、`.default` を使用する場合は、`https://management.azure.com//.default` を要求する必要があります (二重スラッシュに注意してください)。

一般的に、トークンが発行されていることを検証していて、それを受け入れるべき API がトークンを拒否している場合は、2 番目のスラッシュを追加して再試行することを検討してください。

### 事前認証

事前認証を使用すると、リソース アプリケーションの所有者は、事前認証された一連のアクセス許可に対する同意プロンプトをユーザーに表示しなくても、クライアント アプリにアクセス許可を付与できます。 リソース所有者は、Azure ポータルで、または PowerShell や Microsoft Graph などの API を使用して、クライアント アプリを事前認証できます。

ほとんどの場合、Microsoft Entra 外部 IDの顧客向けアプリケーションは、アプリケーションが要求するアクセス許可に同意できない組織外のユーザーを対象としているため、事前認証が必要です。 事前認証により、これらのユーザーは同意を求められることなく、アプリケーションにアクセスできるようになります。

事前認証されたアクセス許可は、常に関連するトークン要求に表示されます。 すべての事前認証されたアクセス許可をすべてのテナントで付与できるわけではありません。

#### Microsoft サービス間の内部アクセス許可

お客様に代わって、またはお客様のテナント内で他の Microsoft サービスを呼び出す Microsoft サービスは、事前認可を使用して認可される場合があります。 事前認証されたアクセス許可は、同意などの他の方法で付与されたアクセス許可と同様に、トークン要求に表示されます。

場合によっては、これらの要求は、サービス間要求専用に作成され、外部開発者が使用できない事前認証されたアクセス許可を使用します。 これらのアクセス許可により、パブリック API で公開されていない内部ビジネス データやその他の機密性の高いコンテンツへのアクセス権が付与される場合もあります。 内部の名前付け規則に従って、これらのサービス間アクセス許可の一部には、 `MS-{permissionName}` や `_A.AA`などの要求値が含まれます。

トークン要求でこのような値が表示されるお客様は、これが予期される動作であり、サービス利用規約で説明されているように、Microsoft サービス間の通常のアクセスを表していることを知っている必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/secure-group-access-control"} -->
## Microsoft Entra ID のグループを使用してアクセス制御をセキュリティで保護する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/secure-group-access-control
- Service: identity-platform
- Article date: 2024-08-25
- Summary: グループを使用して、Microsoft Entra ID 内のリソースへのアクセスを安全に制御する方法についてご確認ください。

Microsoft Entra ID では、グループを使用して、組織内のリソースへのアクセスを管理できます。 アプリケーションへのアクセスを管理して最小限に制限するには、アクセス制御にグループを使用します。 グループを使用すると、それらのグループのメンバーのみがリソースにアクセスできます。 グループを使用することにより、次の管理機能も有効になります。

- 属性ベースの動的メンバーシップ グループ
- オンプレミスの Active Directory から同期される外部グループ
- 管理者による管理またはセルフサービス管理のグループ

アクセス制御のためのグループの利点の詳細については、[アプリケーションへのアクセスの管理](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-access-management)に関するページを参照してください。

アプリケーションの開発中に、グループ要求を使用してアクセスを承認します。 詳細については、[Microsoft Entra ID を使用してアプリケーションに対するグループ要求を構成する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-group-claims)方法に関するページを参照してください。

現在、多くのアプリケーションは、スケールの課題を回避するために (つまり、トークンで返されるグループの数を減らすために)、`securityEnabled` フラグが `true` に設定されたグループのサブセットを選択しています。 グループに対して `securityEnabled` フラグを true に設定しても、グループが安全に管理される保証はありません。

### リスクを軽減するためのベスト プラクティス

次の表は、セキュリティ グループに関するいくつかのセキュリティのベスト プラクティスと、各プラクティスが軽減する潜在的なセキュリティ リスクを示しています。

| セキュリティのベスト プラクティス | 軽減されるセキュリティ リスク |
| --- | --- |
| **リソース所有者とグループ所有者が同じプリンシパルであることを確認する**。 アプリケーションは、独自のグループ管理エクスペリエンスを構築し、アクセスを管理するための新しいグループを作成する必要があります。 たとえば、アプリケーションは、`Group.Create` アクセス許可を使用してグループを作成し、それ自体をグループの所有者として追加できます。 これにより、アプリケーションはそのグループを制御できますが、テナント内の他のグループを変更するための過度な特権を持つことはありません。 | グループ所有者とリソース所有者が異なるエンティティである場合、グループ所有者は、リソースにアクセスすることが想定されていないユーザーをグループに追加する可能性があり、その結果、そのユーザーは意図せずにそれにアクセスできるようになります。 |
| **リソース所有者とグループ所有者の間で暗黙的な合意を形成する**。 リソース所有者とグループ所有者は、グループの目的、ポリシー、およびリソースへのアクセス権を取得するためにグループに追加できるメンバーに関して、調整を行う必要があります。 このレベルの信頼は、技術的な問題ではなく、当事者間またはビジネス上の合意に依存します。 | グループ所有者とリソース所有者が異なる意図を持っている場合、グループ所有者は、リソース所有者がアクセス権を付与することを意図していなかったユーザーをグループに追加する可能性があります。 このアクションにより、不要で潜在的にリスクがあるアクセスが発生する可能性があります。 |
| **アクセス制御にプライベート グループを使用する**。 Microsoft 365 グループは、[可視性の概念](https://learn.microsoft.com/ja-jp/graph/api/resources/group?view=graph-rest-1.0&preserve-view=true#group-visibility-options)によって管理されます。 このプロパティは、グループの参加ポリシーとグループ リソースの可視性を制御します。 セキュリティ グループには参加ポリシーがあり、誰にでも参加を許可するか、所有者の承認を必要とするかが決定されます。 オンプレミスで同期されるグループも、パブリックまたはプライベートである可能性があります。 オンプレミスで同期されるグループに参加しているユーザーは、クラウド リソースにもアクセスできます。 | アクセス制御にパブリック グループを使用する場合は、すべてのメンバーがグループに参加し、リソースへのアクセス権を取得することができます。 外部リソースへのアクセス権を付与するためにパブリック グループを使用する場合は、特権の昇格のリスクが存在します。 |
| **グループの入れ子**。 アクセス制御にグループを使用し、他のグループがそのメンバーになっている場合、サブグループのメンバーは、リソースへのアクセス権を取得できます。 この場合は、親グループとサブグループの複数のグループ所有者が存在します。 | 各グループの目的と、これらのグループにどのようにして適切なメンバーを追加するかに関して、複数のグループ所有者との調整を行うと、作業がより複雑になり、誤ってアクセス権を付与する可能性が高くなります。 入れ子にするグループの数を制限するか、可能であればまったく使用しないようにしてください。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/secure-least-privileged-access"} -->
## 最小限の特権の原則でアプリケーションのセキュリティを高める - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/secure-least-privileged-access
- Service: identity-platform
- Article date: 2023-01-06
- Summary: 最小限の特権の原則で、アプリケーションとそのデータのセキュリティを強化する方法について説明します。

最小限の特権の情報セキュリティ原則では、ジョブの実行に必要なデータと操作へのアクセス権のみをユーザーとアプリケーションに付与する必要があることをアサートします。 ここでのガイダンスに従って、アプリケーションの攻撃面を縮小し、Microsoft ID プラットフォーム統合アプリケーションでセキュリティ侵害が発生した場合には、その影響 (*影響範囲*) を軽減します。

### 推奨事項の一覧

- **未使用**のアクセス許可と*再割り当て可能な*アクセス許可を取り消して、*過剰な特権*が与えられているアプリケーションを防止する。
- ID プラットフォームの**同意**フレームワークを使用して、保護されたデータへのアクセスを求めるアプリケーションからの要求に対して人間の同意が必要になるようにする。
- 開発のすべての段階で、最低限の特権に留意してアプリケーションを**構築**する。
- デプロイされたアプリケーションを定期的に**監査**して、過剰な特権を持つものを特定する。

### 過剰な特権を持つアプリケーション

**未使用**または**再割り当て可能な**アクセス許可が付与されているアプリケーションは、過剰な特権が与えられていると見なされます。 未使用および再割り当て可能なアクセス許可により、アプリケーションまたはそのユーザーがジョブの実行に必要としないデータまたは操作に対して、未承認または意図されていないアクセスが行われる可能性があります。 適切なアクセス許可のみを付与することで、未使用および再割り当て可能なアクセス許可によって発生するセキュリティ リスクを回避します。 適切なアクセス許可は、アプリケーションまたはユーザーが必要なタスクを実行するために必要になる最小限のアクセス権を持つものです。

#### 未使用のアクセス許可

未使用のアクセス許可とは、アプリケーションに付与されているが、そのアクセス許可によって公開される API や操作が、意図どおりに使用されたときにそのアプリケーションによって呼び出されることがないアクセス許可のことです。

- **例**: アプリケーションは、[Files.Read](https://learn.microsoft.com/ja-jp/graph/permissions-reference) アクセス許可を使用して Microsoft Graph API を呼び出して、サインインしたユーザーの OneDrive に保存されたファイルの一覧を表示します。 ただし、このアプリケーションは、[Calendars.Read](https://learn.microsoft.com/ja-jp/graph/permissions-reference#calendars-permissions) アクセス許可も付与されていますが、カレンダー機能は提供しておらず、Calendars API は呼び出しません。
- **セキュリティ リスク**: 未使用のアクセス許可では、*水平方向の特権エスカレーション*のセキュリティ リスクが発生します。 アプリケーションのセキュリティの脆弱性を悪用するエンティティは、未使用のアクセス許可を使用して、意図どおりに使用されれば通常はアプリケーションでサポートも許可もされていない API または操作へのアクセスを取得できます。
- **軽減策**: アプリケーションで行われた API 呼び出しで使用されていないアクセス許可をすべて削除します。

#### 再割り当て可能なアクセス許可

再割り当て可能なアクセス許可とは、必要なタスクを実行するために必要とするアクセス権をアプリケーションとそのユーザーに提供し続ける特権を低めた対応物を持つアクセス許可です。

- **例**: アプリケーションでは、Microsoft Graph API を呼び出して、サインインしたユーザーのプロファイル情報を表示しますが、プロファイルの編集はサポートしていません。 しかし、アプリケーションには [User.ReadWrite.All](https://learn.microsoft.com/ja-jp/graph/permissions-reference#user-permissions) アクセス許可が付与されています。 制限の厳しい *User.Read.All* アクセス許可によってユーザー プロファイル データへの十分な読み取り専用アクセス権が付与されるので、*User.ReadWrite.All* アクセス許可はここでは再割り当て可能と見なされます。
- **セキュリティ リスク**: 再割り当て可能なアクセス許可では、*垂直方向の特権エスカレーション*のセキュリティ リスクが発生します。 アプリケーションのセキュリティの脆弱性を悪用するエンティティは、再割り当て可能なアクセス許可を使用して、データに不正アクセスしたり、通常ならばそのエンティティのロールでは許可されない操作を実行したりできます。
- **軽減策**: アプリケーションの再割り当て可能なアクセス許可のそれぞれを、アプリケーションの目的の機能を引き続き可能にする特権の最も少ないアクセス許可に置き換えます。

### 同意を使用してデータへのアクセスを制御する

ほとんどのアプリケーションでは、保護されたデータへのアクセスが必要であり、そのデータの所有者は、そのアクセスに[同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/consent-types-developer)する必要があります。 同意は、Microsoft Entra テナントの*すべて*のユーザーに代わって同意できるテナント管理者による方法や、アクセス権を付与できるアプリケーション ユーザー自身による方法など、複数の方法で付与できます。

デバイスで実行されるアプリケーションでは、保護されたデータへのアクセスを要求するたびに、その保護されたデータへのアクセスを許可する前にユーザーの同意を求める必要があります。 アプリケーションが処理を進めるには、まずユーザーが、要求されたアクセス許可に対する同意を与える (または拒否する) 必要があります。

### アプリケーション開発中の最小限の特権

アプリケーションおよびそれがアクセスするユーザー データのセキュリティは、開発者の責任です。

アプリケーションの開発中に以下のガイドラインに従うと、過剰な特権が付与されないようにすることができます。

- アプリケーションで行う必要がある API 呼び出しに必要なアクセス許可について完全に理解する。
- [Graph Explorer](https://developer.microsoft.com/graph/graph-explorer) を使用して、アプリケーションで行う必要がある各 API 呼び出しの最小特権のアクセス許可について理解する。
- 最小から最大特権までの対応する[アクセス許可](https://learn.microsoft.com/ja-jp/graph/permissions-reference)を見つける。
- アプリケーションが重複するアクセス許可を持つ API 呼び出しを行う場合に、重複するアクセス許可のセットを削除する。
- アクセス許可の一覧で最小特権のアクセス許可を選択することで、最小特権のアクセス許可セットのみをアプリケーションに適用する。

### デプロイされたアプリケーションの最小限の特権

組織では多くの場合、通常の業務が影響されないように、実行中のアプリケーションを変更したくありません。 ただし、組織では、過剰な特権アクセスを使用してセキュリティ インシデントが発生したり、より深刻になったりするというリスクを軽減するために、アプリケーションのスケジュールされた更新を行う価値があると考える必要があります。

以下のような標準的な慣行を組織で行うことで、デプロイされたアプリケーションに過剰な特権が付与されず、時間が経過しても過剰な特権が与えられることがなくなります。

- アプリケーションから行われている API 呼び出しを評価する。
- [Graph Explorer](https://developer.microsoft.com/graph/graph-explorer) および [Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/overview) ドキュメントを使用して、必須および最小特権アクセス許可について確認する。
- ユーザーまたはアプリケーションに付与される特権を監査する。
- 最小特権のアクセス許可セットでアプリケーションを更新する。
- アクセス許可を定期的に見直して、すべての承認されたアクセス許可が引き続き関連していることを確認する。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/security-best-practices-for-app-registration"} -->
## アプリケーション プロパティのセキュリティに関するベスト プラクティス - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/security-best-practices-for-app-registration
- Service: identity-platform
- Article date: 2026-10-01
- Summary: Microsoft Entra ID のセキュリティ関連アプリケーション プロパティのベスト プラクティスと一般的なガイダンスについて説明します。

セキュリティは、Microsoft Entra ID にアプリケーションを登録する際の重要な概念であり、組織でのそのビジネス利用における重要な部分です。 アプリケーションの構成ミスは、ダウンタイムや侵害につながる可能性があります。 アプリケーションに追加されるアクセス許可によっては、組織全体に影響が及ぶことがあります。

セキュリティで保護されたアプリケーションは組織にとって不可欠であるため、セキュリティの問題が原因でダウンタイムが発生すると、ビジネス、またはビジネスが依存するいくつかの重要なサービスに影響が及ぶ可能性があります。 したがって、アプリケーションが常に正常で安全な状態に保たれるように時間とリソースを割り当てることが重要です。 コードのセキュリティ脅威モデルの評価と同様に、アプリケーションのセキュリティと正常性の評価を定期的に実施します。 組織のセキュリティに関するより広い観点については、[セキュリティ開発ライフサイクル](https://www.microsoft.com/securityengineering/sdl) (SDL) を参照してください。

この記事では、次のアプリケーションのプロパティとシナリオに関するセキュリティのベスト プラクティスについて説明します。

- IDの種類
- 資格情報
- リダイレクト URI
- 暗黙的なフロー構成
- アプリケーション ID URI (識別子 URI とも呼ばれます)
- アクセス トークンのバージョン
- アプリケーション インスタンスのロック
- アプリケーションの所有権

### IDの種類

[Microsoft Entra アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-applications-are-added)のセキュリティのベスト プラクティス (アプリの登録またはアプリ オブジェクトとも呼ばれます) について学習する場合があります。 ただし、Entra で保護されたリソース (Azure リソースのマネージド ID と呼ばれる) にアクセスするために使用できる別 [の ID の種類があります](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)。

Azure マネージド ID は既定でセキュリティで保護されており、継続的なメンテナンスやオーバーヘッドはほとんど必要ありません。 次のすべてが当てはまる場合は、アプリ ID に Microsoft Entra アプリケーションの代わりにマネージド ID を使用することを検討してください。

- サービスは Azure クラウドで実行されます
- アプリでユーザーをサインインさせる必要はありません
- アプリはトークン フロー内のリソースとして機能する必要はありません (Web API ではありません)
- アプリは複数のテナントで動作する必要はありません

注

マネージド ID を使用して、 [Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/overview) を含む Azure 外部のリソースにアクセスできます。

この記事の残りの部分では、Entra アプリ登録のプロパティのセキュリティのベスト プラクティスについて説明します。

### 資格情報 (証明書とシークレットを含む)

資格情報は、機密クライアントとして使用されるアプリケーションの重要な部分です。 Azure portal のアプリケーション **の [証明書とシークレット** ] ページで、資格情報を追加または削除できます。

[Image: [証明書とシークレット] の場所を示すスクリーンショット。]

証明書とシークレットに関連する次のガイダンスを検討してください。

- 可能な限り [、マネージド ID を](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) 資格情報として使用します。 マネージド ID はどちらも最も安全なオプションであり、継続的な資格情報管理を必要としないため、これを強くお勧めします。 [このガイダンス](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-config-app-trust-managed-identity)に従って、資格情報としてマネージド ID を構成します。 ただし、このオプションは、アプリが使用されているサービスが Azure で実行されている場合にのみ可能です。
- アプリが使用されているサービスが Azure で実行されておらず、資格情報の自動管理を提供する別のプラットフォームで実行される場合は、 [そのプラットフォームの ID を資格情報として使用](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-create-trust)することを検討してください。 たとえば、 [GitHub アクション ワークフローを資格情報として構成できるため](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-create-trust#github-actions)、GitHub アクション パイプラインの資格情報を管理してセキュリティで保護する必要がなくなります。 この方法には注意を払い、信頼できるプラットフォームからのフェデレーション資格情報のみを構成してください。 アプリは、資格情報として構成した ID プラットフォームと同じくらい安全です。
- マネージド ID またはその他のセキュリティで保護された外部 ID プロバイダーを使用できない場合は、 [証明書の資格情報を使用します](https://learn.microsoft.com/ja-jp/entra/identity-platform/certificate-credentials)。 ***パスワード*資格情報 (シークレットとも呼ばれます) は使用しないでください**。 パスワード シークレットを資格情報として使用すると便利ですが、パスワード資格情報は誤って管理され、簡単に侵害される可能性があります。
- マネージド ID の代わりに証明書を使用する必要がある場合は、その証明書を [Azure Key Vault](https://azure.microsoft.com/products/key-vault) などのセキュリティで保護されたキー コンテナーに格納します。
- マネージド ID の代わりに証明書を使用する必要がある場合は、自己署名証明書ではなく、信頼された証明機関 (CA) の証明書を使用します。 信頼された発行者からの証明書を適用するように[ポリシーを構成](https://devblogs.microsoft.com/identity/app-management-policy/)します。 ただし、信頼された CA を使用できない場合でも、自己署名証明書はパスワードよりも優先されます。
- [アプリケーション管理ポリシーを](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationauthenticationmethodpolicy)構成して、シークレットの有効期間を制限したり、使用を完全にブロックしたりして、シークレットの使用を管理します。
- アプリケーションがパブリック クライアントまたはインストール済みクライアント (エンド ユーザー コンピューターにインストールされているモバイル アプリやデスクトップ アプリなど) としてのみ使用される場合は、アプリケーション オブジェクトに資格情報が指定されていないことを確認します。
- アプリケーションで使用されている資格情報について、使用状況と有効期限を確認します。 使用されていない資格情報がアプリケーションにあると、セキュリティ侵害が発生する可能性があります。 資格情報を頻繁にロールオーバーし、アプリケーション間で資格情報を共有しないでください。 1 つのアプリケーションに対して多くの資格情報を設定しないでください。
- 運用環境のパイプラインを監視して、任意の種類の資格情報がコード リポジトリにコミットされないようにします。 [Credential Scanner](https://learn.microsoft.com/ja-jp/previous-versions/azure/security/develop/security-code-analysis-overview#credential-scanner) は、ソース コードおよびビルド出力内の資格情報 (およびその他の機密コンテンツ) を検出するために使用できる静的分析ツールです。

### リダイレクト URI

アプリケーションのリダイレクト URI を最新の状態に保つことが重要です。 Azure portal のアプリケーションの **[認証]** で、アプリケーションのプラットフォームを選択する必要があります。その後、**[リダイレクト URI]** プロパティを定義できます。

[Image: リダイレクト URI プロパティの場所を示すスクリーンショット。]

リダイレクト URI に関する次のガイダンスを検討してください。

- すべての URI の所有権を保持します。 いずれかのリダイレクト URI の所有権が失効すると、アプリケーションの侵害につながる可能性があります。
- すべての DNS レコードが定期的に更新され、変更の有無が監視されていることを確認してください。
- ワルドカード応答 URL や、http または URN などのセキュリティ保護されていない URI スキームは使用しないでください。
- リストを小さく保ちます。 不要な URI をすべてトリミングします。 可能な場合は、URL を Http から Https に更新します。

### 暗黙的なフロー構成

**暗黙的なフロー**を必要とするシナリオで、**承認コード フロー**を使用して、暗黙的なフローの誤用に関連する侵害のリスクを軽減できるようになりました。 Azure portal のアプリケーションの **[認証]** で、アプリケーションのプラットフォームを選択する必要があります。その後、**[アクセス トークン (暗黙的なフローに使用)]** プロパティを設定できます。

[Image: 暗黙的なフロー プロパティがある場所を示すスクリーンショット。]

### アプリケーション ID URI (識別子 URI とも呼ばれます)

アプリケーションの**アプリケーション ID URI** プロパティは、Web API を識別するために使用されるグローバルに一意の URI を指定します。 これは、Microsoft Entra への要求のスコープ値のプレフィックスです。 これは、v1.0 アクセス トークンの対象ユーザー (`aud`) 要求の値でもあります。 マルチテナント アプリケーションの場合、値はグローバルに一意である必要もあります。 **識別子 URI** とも呼ばれます。 Azure portal のアプリケーションの **[API の公開]** で、**アプリケーション ID URI** プロパティを定義できます。

[Image: アプリケーション ID URI の場所を示すスクリーンショット。]

アプリケーション ID URI を定義するためのベスト プラクティスは、アプリが v1.0 または v2.0 のアクセス トークンを発行するかどうかによって変わります。 アプリが v1.0 アクセス トークンを発行されているかどうかわからない場合は、[アプリ マニフェストの `requestedAccessTokenVersion` を確認](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-microsoft-graph-app-manifest)。 `null` または `1` の値は、アプリが v1.0 アクセス トークンを受け取っていることを示します。 `2` の値は、アプリが v2.0 アクセス トークンを受け取っていることを示します。

v1.0 アクセス トークンが発行されたアプリケーションでは、既定の URI のみを使用する必要があります。 既定の URI は `api://<appId>` と `api://<tenantId>/<appId>`です。 - `nonDefaultUriAddition`の制限を構成して、組織内のアプリケーションに対する今後の更新に対してこのベスト プラクティスを適用します。

v2.0 アクセス トークンが発行されるアプリケーションの場合は、アプリ ID URI を定義するときに次のガイドラインを使用します。

- `api` または `https` URI スキームをお勧めします。 組織内の URI の競合を回避するために、サポートされている形式でプロパティを設定します。 ワイルドカードは使用しないでください。
- 組織の検証済みドメインを使用します。
- セキュリティを維持するために、組織内の URI のインベントリを保持します。

次の API および HTTP スキームベースのアプリケーション ID URI 形式がサポートされます。 表の後の一覧の説明に従って、プレースホルダーの値を置き換えてください。

| サポートされるアプリケーション IDURI 形式 | アプリ ID URI の例 |
| --- | --- |
| *api://&lt;appId&gt;* | *api://aaaabbbb-0000-cccc-1111-dddd2222eeee* |
| *api://&lt;tenantId&gt;/&lt;appId&gt;* | *api://aaaabbbb-0000-cccc-1111-dddd2222eeee/00001111-aaaa-2222-bbbb-3333cccc4444* |
| *api://&lt;tenantId&gt;/&lt;string&gt;* | *api://aaaabbbb-0000-cccc-1111-dddd2222eeee/api* |
| *api://&lt;string&gt;/&lt;appId&gt;* | *api://productapi/00001111-aaaa-2222-bbbb-3333cccc4444* |
| *https://&lt;tenantInitialDomain&gt;.onmicrosoft.com/&lt;string&gt;* | *`https://contoso.onmicrosoft.com/productsapi`* |
| *https://&lt;verifiedCustomDomain&gt;/&lt;string&gt;* | *`https://contoso.com/productsapi`* |
| *https://&lt;string&gt;.&lt;verifiedCustomDomain&gt;* | *`https://product.contoso.com`* |
| *https://&lt;string&gt;.&lt;verifiedCustomDomain&gt;/&lt;string&gt;* | *`https://product.contoso.com/productsapi`* |
| *api://&lt;string&gt;.&lt;verifiedCustomDomainOrInitialDomain&gt;/&lt;string&gt;* | *`api://contoso.com/productsapi`* |

- * &lt;appId&gt;* - アプリケーション オブジェクトのアプリケーション ID (appId) プロパティ。
- * &lt;string&gt;* - ホストまたは API パスのセグメントの文字列値。
- * &lt;tenantId&gt;* - Azure 内のテナントを表すために Azure によって生成された GUID。
- * &lt;tenantInitialDomain&gt;* - *&lt;tenantInitialDomain&gt;.onmicrosoft.com*。*&lt;tenantInitialDomain&gt;* は、テナント作成時にテナント作成者が指定した初期ドメイン名です。
- * &lt;verifiedCustomDomain&gt;* - Microsoft Entra テナント用に構成された[検証済みのカスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)。

注

*api://* スキームを使用している場合は、"api://" の直後に文字列値を追加します。 たとえば、*api://&lt;string&gt;* です。 その文字列値には、GUID または任意の文字列を指定できます。 GUID 値を追加する場合は、アプリ ID またはテナント ID と一致する必要があります。 文字列値を使用する場合は、テナントの検証済みのカスタム ドメインまたは初期ドメインを使用する必要があります。 *api://&lt;appId&gt;* を使用することをお勧めします。

重要

アプリケーション ID URI の値は、スラッシュ "/" 文字で終わる必要があります。

重要

アプリケーション ID URI の値は、テナント内で一意である必要があります。

### アクセス トークンのバージョン

このセクションは、リソース アプリケーション (アクセス トークンの対象ユーザーとして機能するアプリケーション) にのみ適用されます。 通常、リソース アプリケーションは Web API です。 アプリケーションがクライアントとしてのみ機能する場合 (つまり、Microsoft Graph などのリソースに送信するトークンを取得します)、このセクションは適用されません。

カスタム 識別子 URI を 構成したリソース アプリケーションでは、v2.0 アクセス トークン形式を使用する必要があります。 アプリで v2.0 アクセス トークンを使用する必要があるかどうかを確認するには、アプリのアプリ`identifierUris`の  プロパティを確認します。

[Image: マニフェスト エディターでの識別子 URI 変更エクスペリエンスのスクリーンショット。]

`api://{appId}`または`api://{tenantId}/{appId}`の形式で構成されていない値がある場合、アプリは v2.0 アクセス トークンを使用する必要があります。

v2.0 アクセス トークンにアップグレードするには、まずアプリが [v2.0 トークン要求を](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-token-claims-reference)処理できることを確認します。 次に、マニフェスト エディターを使用して、アプリケーションが発行するアクセス トークンのバージョンを更新します。

[Image: 更新トークンのバージョン エクスペリエンスのスクリーンショット。]

v2.0 トークンを使用するようにアプリケーション構成が更新されたら、アプリケーションの対象ユーザー検証ロジックがその`appId`受け入れるように変更されていることを確認します。

### アプリケーション インスタンスプロパティロック

アプリケーションにテナントにプロビジョニングされたサービス プリンシパルがある場合、そのサービス プリンシパルはテナント管理者がカスタマイズできます。これは、そのテナントがアプリケーションのホーム テナントか外部テナントかに関係なく当てはまります。 これらのカスタマイズ機能を使用すると、アプリ所有者が予期していなかった変更を行うことができるため、セキュリティ 上のリスクが生じる可能性があります。 たとえば、資格情報は通常アプリの開発者や所有者が管理すべきですが、サービス プリンシパルに追加することもできます。

このリスクを軽減するには、アプリケーションで [アプリ インスタンスロックを構成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-configure-app-instance-property-locks)必要があります。 アプリ インスタンスのロックを構成する場合は、使用できるすべての機密性の高いプロパティを常にロックします。 これはマルチテナント アプリケーションでは特に極めて重要ですが、シングルテナント アプリケーションでは、アプリ インスタンス ロックを使用して機密性の高いプロパティを保護する必要もあります。

### 権限

保護されたリソースまたは API にアクセスするためのアクセス許可をアプリケーションに付与する必要がある場合があります。 アクセス許可を要求するときは、常に次のことを確認してください。

- [最小限の特権](https://learn.microsoft.com/ja-jp/entra/identity-platform/secure-least-privileged-access)の原則に従います。 アプリが実行する必要があるアクションを実行するために必要な最小限のアクセス許可を付与するアクセス許可のみを要求します。 Microsoft Graph を呼び出す場合は、 [API ドキュメント](https://learn.microsoft.com/ja-jp/graph/api/overview) を使用して、特定の API 呼び出しに対する最も制限の少ないアクセス許可を特定します。 アプリのアクセス許可を定期的に確認して、特権の低いオプションが使用可能かどうかを確認します。 アプリにアクセス許可が不要になった場合は、削除します。
- 可能な限り、アプリ専用[アクセスではなく、委任された](https://learn.microsoft.com/ja-jp/entra/identity-platform/delegated-access-primer)[アクセスを](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-only-access-primer)使用します。
- [アクセス許可と同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)に関するドキュメントを確認して、アクセス許可の基礎を理解していることを確認します。

### アプリの所有権の構成

所有者は、登録されているアプリケーションのすべての側面を管理できます。 組織内のすべてのアプリケーションの所有権を定期的に確認することが重要です。 詳細については、「[Microsoft Entra のアクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)」をご覧ください。 Azure portal のアプリケーションの **[所有者]** で、アプリケーションの所有者を管理できます。

[Image: アプリケーションの所有者が管理される場所を示すスクリーンショット。]

アプリケーションの所有者の指定に関連する次のガイダンスを検討してください。

- アプリケーションの所有権が、組織内のユーザーの最小セットに保たれている必要があります。
- 管理者は、所有者リストを数か月ごとに 1 回確認し、所有者がまだ組織に属しており、アプリケーションを引き続き所有する必要があることを確認する必要があります。

### Entra の推奨事項を確認する

[Microsoft Entra の推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations)機能は、テナントの状態を監視するのに役立ちます。そうする必要はありません。 これらのレコメンデーションは、テナントが安全で正常な状態であることを確認するのに役立ち、また、Microsoft Entra ID で使用できる機能の価値を最大限に高めることができます。 アプリのエコシステムを正常な状態に保つために、アプリのプロパティまたはアプリ構成に関連するアクティブな Microsoft Entra の推奨事項を定期的に確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/security-tokens"} -->
## トークンと要求の概要 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/security-tokens
- Service: identity-platform
- Article date: 2025-03-21
- Summary: Microsoft Entra テナントが認証エンドポイント、承認エンドポイント、スコープ、要求のメタデータを公開する方法について説明します。

一元化された ID プロバイダーは、世界中にいるユーザーが必ずしも企業のネットワークからサインインするわけではないアプリにとって特に役立ちます。 Microsoft ID プラットフォームではユーザーを認証し、セキュリティ トークン (アクセス トークン、リフレッシュトークン、ID トークンなど) を提供します。 セキュリティ トークンを使用すると、クライアント アプリケーションは、リソース サーバー上の保護されたリソースにアクセスできます。

- **アクセス トークン** - アクセス トークンは、OAuth 2.0 フローの一部として承認サーバーによって発行されるセキュリティ トークンです。 これには、そのトークンの対象となるユーザーとリソースに関する情報が含まれています。 この情報を使用すると、Web API やその他の保護されたリソースにアクセスできます。 リソースはアクセス トークンを検証して、クライアント アプリケーションへのアクセスを許可します。 詳細については、「[Microsoft ID プラットフォームのアクセス トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)」を参照してください。
- **更新トークン** - アクセス トークンは短時間しか有効でないため、承認サーバーでは、アクセス トークンの発行と同時に更新トークンを発行する場合があります。 クライアント アプリケーションでは、必要に応じて、このリフレッシュトークンを新しいアクセス トークンに交換できます。 詳細については、「[Microsoft ID プラットフォームの更新トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/refresh-tokens)」を参照してください。
- **ID トークン** - ID トークンは、OpenID Connect フローの一部としてクライアント アプリケーションに送信されます。 これらは、アクセス トークンの代わりに、またはアクセス トークンと共に送信できます。 ID トークンは、ユーザーを認証するためにクライアントによって使用されます。 Microsoft ID プラットフォームが ID トークンを発行する方法の詳細については、「[Microsoft ID プラットフォームの ID トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens)」を参照してください。

多くのエンタープライズ アプリケーションは、SAML を使用してユーザーを認証します。 SAML アサーションの詳細については、「[SAML トークンのリファレンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-saml-tokens)」を参照してください。

### トークンを検証する

トークンの検証は、そのトークンが生成されたアプリケーション、ユーザーをサインインさせた Web アプリ、または呼び出し先の Web API に任されています。 承認サーバーは、秘密キーを使用してトークンに署名します。 承認サーバーでは、対応する公開キーを発行します。 トークンを検証するために、アプリでは、承認サーバーの公開キーを使用して署名を検証することによって、その署名が秘密キーを使用して作成されたことを検証します。 詳しくは、「[要求を検証してアプリケーションと API をセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-validation)」に関する記事をご覧ください。

可能な限り、サポートされている [Microsoft 認証ライブラリ (MSAL)](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) を使用することをおすすめします。 これにより、トークンの取得、更新、検証が実装されます。 また、テナントの OpenID の既知の検出ドキュメントを使用して、標準に準拠したテナント設定とキーの検出を実装します。 MSAL は、.NET、JavaScript、Java、Python、Android、iOS などの、さまざまなアプリケーション アーキテクチャとプラットフォームをサポートします。

トークンは限られた時間だけ有効であるため、承認サーバーはトークンのペアを頻繁に提供します。 アクセス トークンが提供されます。これは、アプリケーションまたは保護されたリソースにアクセスします。 更新トークンが提供されます。これは、アクセス トークンが有効期限に近づいたとき、そのアクセス トークンを更新するために使用されます。

アクセス トークンは、`Authorization` ヘッダーのベアラー トークンとして Web API に渡されます。 アプリでは、承認サーバーにリフレッシュトークンを提供できます。 ユーザーのアプリへのアクセスが取り消されなかった場合、アプリは新しいアクセス トークンと新しい更新トークンを受け取ります。 承認サーバーは、更新トークンを受信すると、ユーザーがまだ承認されている場合にのみ次のアクセス トークンを発行します。

### JSON Web トークンと要求

Microsoft ID プラットフォームでは、*要求*を含む JSON Web トークン (JWT) としてセキュリティ トークンを実装します。 JWT はセキュリティ トークンとして使用されるため、この認証形式は *JWT 認証*と呼ばれることがあります。

要求では、一方のエンティティ (クライアント アプリケーション、リソース所有者など) に関するアサーションが、もう一方のエンティティ (リソース サーバーなど) に渡されます。 要求は、JWT 要求または JSON Web トークン要求と呼ばれることもあります。

要求は、トークン サブジェクトに関するファクトを中継する名前または値のペアです。 たとえば、ある要求に、承認サーバーが認証したセキュリティ プリンシパルに関するファクトが含まれていることがあります。 特定のトークン内に存在する要求は、トークンの種類、サブジェクトを認証するために使用される資格情報の種類、アプリケーション構成など、多くのものによって異なります。

アプリケーションでは、以下のような各種タスクのために要求を使用できます:

- トークンを検証する
- トークン サブジェクトのテナントを識別する
- ユーザー情報を表示する
- サブジェクトの承認を判断する

要求は、以下のような種類の情報を提供するキーと値のペアで構成されます:

- トークンを生成したセキュリティ トークン サーバー
- トークンが生成された日付
- サブジェクト (ユーザーと同じですが、デーモンは含まれません)
- 対象ユーザー。トークンが生成されたアプリです
- トークンを要求したアプリ (クライアント)

### トークン エンドポイントと発行者

Microsoft Entra ID では、2 つのテナント構成がサポートされています。社内での使用を目的として、従業員とビジネス ゲストを管理する従業員構成と、 制限された外部向けディレクトリ内のコンシューマーとパートナーを分離するために最適化された[顧客構成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-supported-features-customers) です。 基になる ID サービスは両方のテナント構成で同じですが、外部テナントのサインイン ドメインとトークン発行機関は異なります。 これにより、必要に応じて、アプリケーションで従業員と外部 ID のワークフローを分離し続けることができます。

Microsoft Entra の従業員テナントでは、*sts.windows.net* によって発行されたトークンを使用して、login.microsoftonline.comで認証を行います。 ワークフォース テナント トークンは、基になる信頼関係によってこの相互運用性が許可されている限り、テナントとマルチテナント アプリケーション間で交換可能です。 Microsoft Entra 外部テナントは、フォーム `{tenantname}.ciamlogin.com`のエンドポイントを使用します。 外部テナントに登録されているアプリケーションでは、トークンを正しく受信して検証するために、この分離を認識している必要があります。

すべての Microsoft Entra テナントは、標準準拠の既知のメタデータを発行します。 このドキュメントには、発行者名、認証および承認エンドポイント、サポートされているスコープと要求に関する情報が含まれています。 外部テナントの場合、ドキュメントは `https://{tenantname}.ciamlogin.com/{tenantid}/v2.0/.well-known/openid-configuration` で公開されています。 このエンドポイントは、発行者の値 `https://{tenantid}.ciamlogin.com/{tenantid}/v2.0` を返します。

### 承認フローと認証コード

クライアントの構築方法に応じて、Microsoft ID プラットフォームでサポートされている認証フローの 1 つ (または複数) を使用できます。 サポートされているフローでは、各種のトークンと承認コードを生成でき、それらを機能させるためにさまざまなトークンが必要です。 以下の表に概要を示します。

| フロー | 必要なもの | ID トークン | アクセス トークン | リフレッシュトークン | Authorization code (承認コード) |
| --- | --- | --- | --- | --- | --- |
| [承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) |  | x | x | x | x |
| [暗黙的なフロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-implicit-grant-flow) |  | x | x |  |  |
| [ハイブリッド OIDC フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc#protocol-diagram-access-token-acquisition) |  | x |  |  | x |
| [更新トークンの引き換え](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow#refresh-the-access-token) | リフレッシュトークン | x | x | x |  |
| [On-Behalf-Of フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow) | アクセス トークン | x | x | x |  |
| [クライアントの資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow) |  |  | x (アプリのみ) |  |  |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/signing-key-rollover"} -->
## Microsoft ID プラットフォームでの署名キーのロールオーバー - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/signing-key-rollover
- Service: identity-platform
- Article date: 2024-10-28
- Summary: この記事では、Microsoft Entra ID での署名キーのロール オーバーのベスト プラクティスについて説明します。

この記事では、セキュリティ トークンに署名するために Microsoft ID プラットフォームで使用される公開キーに関して、知っておく必要があることについて説明します。 これらのキーは定期的にロールオーバーされ、緊急時にはすぐにロールオーバーされる可能性があることにご注意ください。 Microsoft ID プラットフォームを使用するすべてのアプリケーションは、キーのロールオーバー プロセスをプログラムで処理できる必要があります。 キーのしくみ、アプリケーションへのロールオーバーの影響を評価する方法について説明します。 また、アプリケーションを更新する方法や、必要に応じてキー ロールオーバーを処理する定期的な手動ロールオーバー プロセスを確立する方法についても説明します。

### Microsoft ID プラットフォームでの署名キーの概要

Microsoft ID プラットフォームは、業界標準に基づいて構築された公開キー暗号化を使って、それ自体とそれを使用するアプリケーションの間での信頼を確立します。 具体的には、次のように機能します。Microsoft ID プラットフォームでは、公開キーと秘密キーのペアで構成される署名キーが使用されます。 認証に Microsoft ID プラットフォームを使用するアプリケーションにユーザーがサインインすると、Microsoft ID プラットフォームによって、そのユーザーに関する情報を含むセキュリティ トークンが作成されます。 このトークンは、Microsoft ID プラットフォームによって秘密キーを使用して署名されてから、アプリケーションに返送されます。 トークンが有効であり、Microsoft ID プラットフォームからのものであることを確認するには、アプリケーションで、テナントの [OpenID Connect Discovery ドキュメント](https://openid.net/specs/openid-connect-discovery-1_0.html)または SAML/WS-Fed の[フェデレーション メタデータ ドキュメント](https://learn.microsoft.com/ja-jp/entra/identity-platform/federation-metadata)に含まれる、Microsoft ID プラットフォームによって公開された公開キーを使って、トークンの署名を検証する必要があります。

セキュリティ上の理由から、Microsoft ID プラットフォームの署名キーは定期的にロールオーバーされ、緊急時には即座にロールオーバーされる場合があります。 これらのキー ロール間には、設定または保証された時間はありません。 Microsoft ID プラットフォームと統合されているすべてのアプリケーションは、発生頻度に関係なくキー ロールオーバー イベントをいつでも処理できる必要があります。 アプリケーションで突発的な更新が処理されず、期限切れのキーを使用してトークンの署名の検証が試行されると、アプリケーションでそのトークンが誤って拒否されます。 [標準ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)を使用して、キー メタデータが正しく更新され、最新の状態に保たれるよう徹底することをお勧めします。 標準ライブラリが使用されていない場合は、実装がベスト プラクティス セクションに従っていることを確認してください。

OpenID Connect Discovery ドキュメントとフェデレーション メタデータ ドキュメントには、利用できる有効なキーが常に複数存在します。 1 つのキーがすぐにロールされて別のキーに置き換えられる可能性があるので、アプリケーションは、このドキュメントで指定されているどのキーでも使用できるようになっている必要があります。 存在するキーの数は、Microsoft ID プラットフォームの内部アーキテクチャに基づいて、新しいプラットフォーム、新しいクラウド、または新しい認証プロトコルのサポートに伴って時間の経過と共に変わる場合があります。 JSON 応答でのキーの順序とそれらが公開された順序はどちらも、アプリケーションにとって意味があってはなりません。 JSON Web キーのデータ構造の詳細については、[RFC7517](https://www.rfc-editor.org/rfc/rfc7517) を参照してください。

1 つの署名キーのみがサポートされるアプリケーション、または署名キーを手動で更新する必要があるアプリケーションは、本質的に安全性と信頼性が低くなります。 これらは、[標準ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)を使用し、他のベスト プラクティスでも常に最新の署名キーを使用するように更新される必要があります。

### キー メタデータのキャッシュと検証のベスト プラクティス

- [OpenID Connect (OIDC)](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc) と[フェデレーション メタデータ](https://learn.microsoft.com/ja-jp/entra/identity-platform/federation-metadata)で説明されているように、テナント固有のエンドポイントを使用してキーを検出します。
- アプリケーションが複数のテナントにデプロイされている場合でも、アプリケーションが機能を提供するテナントごとに個別にキーを常に検出してキャッシュすることをお勧めします (テナント固有のエンドポイントを使用)。 現在のテナント間で共通のキーが、将来的にテナント間で区別される可能性があります。
- 以下のキャッシュ アルゴリズムを使用して、キャッシュの回復性と安全性を確保します。

#### キー メタデータのキャッシュ アルゴリズム:

[標準ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)では、回復性がある安全なキーのキャッシュを実装します。 これらを使用して実装の軽微な欠陥を回避することをお勧めします。 カスタム実装については、大まかなアルゴリズムを次に示します。

##### 一般的な考慮事項:

- トークンを検証するサービスには、多数の異なるキー (10-1000) を格納できるキャッシュが必要です。
- キーは、キー ID (OIDC キー メタデータ仕様の“kid” ) をキャッシュ キーとして使用して個別にキャッシュする必要があります。
- キャッシュ内のキーの有効期限は 24 時間に構成し、更新を 1 時間ごとに行う必要があります。 これにより、削除されるキーに対してシステムが迅速に応答しつつ、キーのフェッチに関する問題の影響を受けないように十分なキャッシュ期間を確保できます。
- キーは、以下の場合に更新する必要があります。
    - プロセスが起動された場合またはキャッシュが空になった場合
    - バックグラウンド ジョブとして定期的に (1 時間ごとを推奨)
    - 受信したトークンが不明なキー (不明な **kid** またはヘッダー内の **tid**) で署名された場合に動的に

##### KeyRefresh プロシージャ (IdentityModel の概念アルゴリズム)

1. **初期化**

    構成マネージャーは、構成データをフェッチするための特定のアドレスと、このデータを取得して検証するために必要なインターフェイスを使用して設定されます。
2. **構成チェック**

    新しいデータをフェッチする前に、最初に、定義済みの更新間隔に基づいて、既存のデータがまだ有効かどうかが確認されます。
3. **データ取得** データが古いか不足している場合、ロックダウンが行われ、重複 (およびスレッド枯渇) を回避するために、1 つのスレッドのみが新しいデータをフェッチするよう徹底されます。 その後、指定されたエンドポイントから最新の構成データの取得が試みられます。
4. **検証**

    新しいデータが取得されたら、必要な標準を満たしており、破損していないことを確認するために検証されます。 メタデータは、受信要求が新しいキーを使用して正常に検証された場合にのみ受け入れられます。
5. **エラー処理**

    データ取得中にエラーが発生した場合は、ログに記録されます。 新しいデータをフェッチできない場合、最新の既知の正常な構成を使用して動作が続行されます。
6. **自動更新** 更新間隔 (ジッターをプラスまたはマイナス 1 時間にして 12 時間にすることを推奨) に基づいて構成データが定期的に自動的にチェックおよび更新されます。 また、必要に応じて更新を手動で要求し、データが常に最新になるよう徹底することもできます。
7. **新しいキーを使用したトークンの検証** まだ不明な署名キーを使用したトークンが構成から到着した場合、通常予想される更新以外のメタデータ内の新しいキーを処理するために、ホット パスで同期呼び出しを使用した構成のフェッチが試みられます (ただし、頻度は 5 分未満)。

この方法により、最新の有効な構成データを常に使用しながら、エラーを適切に処理し、冗長な操作を回避できるよう徹底します。

このアルゴリズムの .NET 実装は、[BaseConfigurationManager](https://github.com/AzureAD/azure-activedirectory-identitymodel-extensions-for-dotnet/blob/dev/src/Microsoft.IdentityModel.Tokens/BaseConfigurationManager.cs) から入手できます。 これは、回復性とセキュリティの評価に基づいて変更される可能性があります。 [こちら](https://github.com/AzureAD/azure-activedirectory-identitymodel-extensions-for-dotnet/wiki/signing-key-rollover)の説明も参照してください。

##### KeyRefresh プロシージャ (擬似コード):

この手順では、グローバル (lastSuccessfulRefreshTime タイムスタンプ) を使用して、キーの更新頻度が高すぎる状況を回避します。

- [OpenID Connect (OIDC)](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc)

```csharp
KeyRefresh(issuer)
{
  // Store cache entries and last successful refresh timestamp per distinct 'issuer'
 
  if (LastSuccessfulRefreshTime is set and more recent than 5 minutes ago) 
    return // without refreshing
 
  // Load keys URI using the tenant-specific OIDC configuration endpoint ('issuer' is the input parameter)
  oidcConfiguration = download JSON from "{issuer}/.well-known/openid-configuration"
 
  // Load list of keys from keys URI
  keyList = download JSON from jwks_uri property of oidcConfiguration
 
  foreach (key in keyList) 
  {
    cache entry = lookup in cache by kid property of key
    if (cache entry found) 
      set expiration of cache entry to now + 24h 
    else 
      add key to cache with expiration set to now + 24h
  }
 
  set LastSuccessfulRefreshTime to now // current timestamp
}

```

##### サービス スタートアップ プロシージャ:

- キーを更新するための KeyRefresh
- KeyRefresh を 1 時間ごとに呼び出すバックグラウンド ジョブを起動する

#### キーを検証するための TokenValidation プロシージャ (擬似コード):

```csharp
ValidateToken(token)
{
  kid = token.header.kid // get key id from token header
  issuer = token.body.iss // get issuer from 'iss' claim in token body
 
  key = lookup in cache by issuer and kid
  if (key found)
  {
     validate token with key and return
  }
  else // key is not found in the cache
  {
    call KeyRefresh(issuer) // to opportunistically refresh the keys for the issuer
    key = lookup in cache by issuer and kid
    if (key found)
    {
      validate token with key and return
    }
    else // key is not found in the cache even after refresh
    {
      return token validation error
    }
  }
}
```

### アプリケーションに影響が波及するかどうかの評価とその対処法

キー ロールオーバーへのアプリケーション側の対応は、アプリケーションの種類や使用されている ID プロトコル、ライブラリなどさまざまな要因によって異なります。 以下の各セクションでは、ごく一般的なアプリケーションにおけるキー ロールオーバーへの影響の有無を評価し、キーの自動ロールオーバーまたは手動更新に対応するようにアプリケーションを更新するうえでの指針を取り上げています。

- リソースにアクセスするネイティブ クライアント アプリケーション
- リソースにアクセスする Web アプリケーション/API
- Azure App Service を使用して構築された、リソースを保護する Web アプリケーション/API
- ASP.NET OWIN OpenID Connect、WS-Fed、または WindowsAzureActiveDirectoryBearerAuthentication のいずれかのミドルウェアを使用してリソースを保護する Web アプリケーションまたは API
- ASP.NET Core OpenID Connect または JwtBearerAuthentication のいずれかのミドルウェアを使用してリソースを保護する Web アプリケーションまたは API
- Node.js `passport-azure-ad` モジュールを使用し、リソースを保護する Web アプリケーション/API
- Visual Studio 2015 以降を使用して作成された、リソースを保護する Web アプリケーションや Web API
- Visual Studio 2013 を使用して作成された、リソースを保護する Web アプリケーション
- Visual Studio 2013 を使用して作成された、リソースを保護する Web API
- Visual Studio 2012 を使用して作成された、リソースを保護する Web アプリケーション
- その他のライブラリが使用されているか、サポートされているプロトコルが手動で実装された、リソースを保護する Web アプリケーション/API

このガイダンスは、次のアプリケーションには適用 **されません** 。

- Microsoft Entra アプリケーション ギャラリーから追加したアプリケーション (カスタムも含む) については、キーの署名に関して別個のガイダンスがあります。 詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-manage-certificates-for-federated-single-sign-on)を参照してください。
- アプリケーション プロキシ経由で発行されたオンプレミスのアプリケーションでは、キーの署名について配慮する必要はありません。

#### リソースにアクセスするネイティブ クライアント アプリケーション

リソース (例: Microsoft Graph、KeyVault、Outlook API、その他の Microsoft API) にアクセスするだけのアプリケーションは、トークンを取得してリソース所有者に渡すだけです。 それらのアプリケーションでリソースを保護しない場合は、トークンを調べることはないため、トークンに適切に署名されることを保証する必要はありません。

デスクトップかモバイルかを問わず、ネイティブ クライアント アプリケーションはこのカテゴリに分類され、ロールオーバーの影響を受けません。

#### リソースにアクセスする Web アプリケーション/API

リソース (Microsoft Graph、KeyVault、Outlook API、その他の Microsoft API など) にアクセスするだけのアプリケーションは、トークンを取得してリソース所有者に渡すだけです。 それらのアプリケーションでリソースを保護しない場合は、トークンを調べることはないため、トークンに適切に署名されることを保証する必要はありません。

トークンを要求するためのアプリ専用のフロー (クライアント資格情報/クライアント証明書) を使用する Web アプリケーションと Web API はこのカテゴリに分類され、ロールオーバーの影響を受けません。

#### Azure App Service を使用して構築された、リソースを保護する Web アプリケーション/API

Azure App Service の認証/承認 (EasyAuth) 機能には、キーのロールオーバーを自動的に処理するうえで必要なロジックがあります。

#### ASP.NET OWIN OpenID Connect、WS-Fed、または WindowsAzureActiveDirectoryBearerAuthentication のいずれかのミドルウェアを使用してリソースを保護する Web アプリケーションまたは API

アプリケーションで ASP.NET OWIN OpenID Connect、WS-Fed、WindowsAzureActiveDirectoryBearerAuthentication のいずれかのミドルウェアを使用している場合、キーのロールオーバーを自動的に処理するために必要なロジックが既にあります。

それらがアプリケーションで使用されているかどうかは、アプリケーションの Startup.cs または Startup.Auth.cs ファイルで次のスニペットを探すことで確認できます。

```csharp
app.UseOpenIdConnectAuthentication(
    new OpenIdConnectAuthenticationOptions
    {
        // ...
    });
```

```csharp
app.UseWsFederationAuthentication(
    new WsFederationAuthenticationOptions
    {
        // ...
    });
```

```csharp
app.UseWindowsAzureActiveDirectoryBearerAuthentication(
    new WindowsAzureActiveDirectoryBearerAuthenticationOptions
    {
        // ...
    });
```

#### .NET Core OpenID Connect または JwtBearerAuthentication のいずれかのミドルウェアを使用し、リソースを保護する Web アプリケーションまたは API

アプリケーションで ASP.NET OWIN OpenID Connect または JwtBearerAuthentication のいずれかのミドルウェアを使用している場合、キーのロールオーバーを自動的に処理するために必要なロジックが既にあります。

それらがアプリケーションで使用されているかどうかは、アプリケーションの Startup.cs または Startup.Auth.cs から次のスニペットを探すことで確認できます。

```
app.UseOpenIdConnectAuthentication(
     new OpenIdConnectAuthenticationOptions
     {
         // ...
     });
```

```
app.UseJwtBearerAuthentication(
    new JwtBearerAuthenticationOptions
    {
     // ...
     });
```

#### Node.js `passport-azure-ad` モジュールを使ってリソースを保護する Web アプリケーションと API

アプリケーションで Node.js passport-ad モジュールを使用している場合、キーのロールオーバーに自動的に対処するうえで必要なロジックがあらかじめ用意されています。

アプリケーションで passport-ad が使用されているかどうかは、アプリケーションの app.js から次のスニペットを探すことで確認できます。

```
var OIDCStrategy = require('passport-azure-ad').OIDCStrategy;

passport.use(new OIDCStrategy({
    //...
));
```

#### Visual Studio 2015 以降を使用して作成された、リソースを保護する Web アプリケーションや Web API

アプリケーションが Visual Studio 2015 以降の Web アプリケーション テンプレートを使用して作成されており、 **[認証の変更]** メニューで **[職場または学校アカウント]** を選択した場合は、キーのロールオーバーに自動的に対処するうえで必要なロジックがあらかじめ用意されています。 このロジックは OpenID Connect Discovery ドキュメントからキーを取得してキャッシュし、定期的にそれらを更新するもので、OWIN OpenID Connect ミドルウェアに組み込まれています。

認証を手動でソリューションに追加した場合は、キーのロールオーバーに対応するうえで必要なロジックがアプリケーションに備わっていない可能性があります。 必要なロジックを独自に作成するか、あるいはその他のライブラリを使用する、またはサポートされているプロトコルを手動で実装する Web アプリケーション/API に関するセクションの手順に従ってください。

#### Visual Studio 2013 を使用して作成された、リソースを保護する Web アプリケーション

アプリケーションが Visual Studio 2013 の Web アプリケーション テンプレートを使用して作成されており、 **[認証の変更]** メニューで **[組織アカウント]** を選択した場合、キーのロールオーバーに自動的に対処するうえで必要なロジックがあらかじめ用意されています。 このロジックにより、プロジェクトに関連付けられた 2 つのデータベース テーブルに組織の一意識別子と署名キー情報が保存されます。 このデータベースの接続文字列は、プロジェクトの Web.config ファイル内に格納されています。

認証を手動でソリューションに追加した場合は、キーのロールオーバーに対応するうえで必要なロジックがアプリケーションに備わっていない可能性があります。 必要なロジックを独自に作成するか、あるいはその他のライブラリを使用する、またはサポートされているプロトコルを手動で実装する Web アプリケーション/API に関するセクションの手順に従ってください。

アプリケーションでロジックが適切に機能するかどうかは、以下の手順によって確認できます。

1. Visual Studio 2013 でソリューションを開き、右側のウィンドウで **[サーバー エクスプローラー]** タブをクリックします。
2. **[データ接続]** 、 **[既定の接続]** 、 **[テーブル]** の順に展開します。 **IssuingAuthorityKeys** テーブルを右クリックし、**[テーブル データの表示]** を選択します。
3. **IssuingAuthorityKeys** テーブルには少なくとも 1 つの行があり、キーの拇印の値に対応しています。 テーブル内の任意の行を削除します。
4. **Tenants** テーブルを右クリックし、**[テーブル データの表示]** を選択します。
5. **Tenants** テーブルには少なくとも 1 つの行があり、一意のディレクトリ テナント ID に対応しています。 テーブル内の任意の行を削除します。 **Tenants** テーブルと **IssuingAuthorityKeys** テーブルの両方で行を削除しないと、実行時にエラーが表示されます。
6. アプリケーションをビルドして実行します。 アカウントにログインすると、アプリケーションを停止できます。
7. **[サーバー エクスプローラー]** に戻り、**IssuingAuthorityKeys** テーブルと **Tenants** テーブルの値を確認します。 テーブルにはフェデレーション メタデータ ドキュメントから適切な情報が自動的に入力されていることを確認できます。

#### Visual Studio 2013 を使用して作成された、リソースを保護する Web API

Visual Studio 2013 で Web API テンプレートを使用して Web API を作成し、 **[認証の変更]** メニューで **[組織アカウント]** を選択した場合、アプリケーションには既に必要なロジックが含まれています。

手動で認証を構成する場合は、以下の手順に従って、自動的にキー情報を更新するように Web API を構成する方法を確認してください。

次のコード スニペットは、フェデレーション メタデータ ドキュメントから最新のキーを取得し、[JWT トークン ハンドラー](https://learn.microsoft.com/ja-jp/previous-versions/dotnet/framework/windows-identity-foundation/json-web-token-handler) を使用してトークンを検証する方法を示しています。 このコード スニペットでは、データベース内か、構成ファイル内か、またはその他の場所に配置するかどうかにかかわらず、Microsoft ID プラットフォームからの将来のトークンを検証するキーを保持するために、独自のキャッシュ メカニズムが使用されることを前提としています。

```
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IdentityModel.Tokens;
using System.Configuration;
using System.Security.Cryptography.X509Certificates;
using System.Xml;
using System.IdentityModel.Metadata;
using System.ServiceModel.Security;
using System.Threading;

namespace JWTValidation
{
    public class JWTValidator
    {
        private string MetadataAddress = "[Your Federation Metadata document address goes here]";

        // Validates the JWT Token that's part of the Authorization header in an HTTP request.
        public void ValidateJwtToken(string token)
        {
            JwtSecurityTokenHandler tokenHandler = new JwtSecurityTokenHandler()
            {
                // Do not disable for production code
                CertificateValidator = X509CertificateValidator.None
            };

            TokenValidationParameters validationParams = new TokenValidationParameters()
            {
                AllowedAudience = "[Your App ID URI goes here]",
                ValidIssuer = "[The issuer for the token goes here, such as https://sts.windows.net/aaaabbbb-0000-cccc-1111-dddd2222eeee/]",
                SigningTokens = GetSigningCertificates(MetadataAddress)

                // Cache the signing tokens by your desired mechanism
            };

            Thread.CurrentPrincipal = tokenHandler.ValidateToken(token, validationParams);
        }

        // Returns a list of certificates from the specified metadata document.
        public List<X509SecurityToken> GetSigningCertificates(string metadataAddress)
        {
            List<X509SecurityToken> tokens = new List<X509SecurityToken>();

            if (metadataAddress == null)
            {
                throw new ArgumentNullException(metadataAddress);
            }

            using (XmlReader metadataReader = XmlReader.Create(metadataAddress))
            {
                MetadataSerializer serializer = new MetadataSerializer()
                {
                    // Do not disable for production code
                    CertificateValidationMode = X509CertificateValidationMode.None
                };

                EntityDescriptor metadata = serializer.ReadMetadata(metadataReader) as EntityDescriptor;

                if (metadata != null)
                {
                    SecurityTokenServiceDescriptor stsd = metadata.RoleDescriptors.OfType<SecurityTokenServiceDescriptor>().First();

                    if (stsd != null)
                    {
                        IEnumerable<X509RawDataKeyIdentifierClause> x509DataClauses = stsd.Keys.Where(key => key.KeyInfo != null && (key.Use == KeyType.Signing || key.Use == KeyType.Unspecified)).
                                                             Select(key => key.KeyInfo.OfType<X509RawDataKeyIdentifierClause>().First());

                        tokens.AddRange(x509DataClauses.Select(token => new X509SecurityToken(new X509Certificate2(token.GetX509RawData()))));
                    }
                    else
                    {
                        throw new InvalidOperationException("There is no RoleDescriptor of type SecurityTokenServiceType in the metadata");
                    }
                }
                else
                {
                    throw new Exception("Invalid Federation Metadata document");
                }
            }
            return tokens;
        }
    }
}
```

#### Visual Studio 2012 を使用して作成された、リソースを保護する Web アプリケーション

アプリケーションが Visual Studio 2012 で作成されている場合、通常、アプリケーションは ID およびアクセス ツールを使用して構成されています。 また、通常は[発行者名レジストリの検証 (VINR)](https://learn.microsoft.com/ja-jp/previous-versions/dotnet/framework/windows-identity-foundation/validating-issuer-name-registry) が使用されている可能性が高いです。 VINR の役割は、信頼できる ID プロバイダー (Microsoft ID プラットフォーム) に関する情報、およびそれらによって発行されたトークンを検証するために使用されるキーに関する情報を維持することです。 また、VINR を使用すると、ディレクトリに関連付けられている最新のフェデレーション メタデータ ドキュメントをダウンロードし、構成が期限切れになっているかどうかを確認し、必要に応じて新しいキーでアプリケーションを更新することにより、Web.config ファイルに保存されているキー情報を自動的に更新することが容易になります。

Microsoft から提供されたコード サンプルまたはチュートリアルのいずれかを使用してアプリケーションを作成した場合、キーのロールオーバー ロジックは既にプロジェクトに含まれています。 以下に示すコードがプロジェクト内に存在していることを確認できます。 アプリケーションにこのロジックが含まれていない場合は、以下の手順を実行してロジックを追加し、正常に機能することを確認してください。

1. **[ソリューション エクスプローラー]** で、適切なプロジェクトの **System.IdentityModel** アセンブリへの参照を追加します。
2. **Global.asax.cs** ファイルを開き、次の using ディレクティブを追加します。

    ```
    using System.Configuration;
    using System.IdentityModel.Tokens;
    ```
3. 次のメソッドを **Global.asax.cs** ファイルに追加します。

    ```
    protected void RefreshValidationSettings()
    {
     string configPath = AppDomain.CurrentDomain.BaseDirectory + "\\" + "Web.config";
     string metadataAddress =
                   ConfigurationManager.AppSettings["ida:FederationMetadataLocation"];
     ValidatingIssuerNameRegistry.WriteToConfig(metadataAddress, configPath);
    }
    ```
4. 次に示すように、**Global.asax.cs** 内の**Application\_Start()** メソッドで **RefreshValidationSettings()** メソッドを呼び出します。

    ```
    protected void Application_Start()
    {
     AreaRegistration.RegisterAllAreas();
     ...
     RefreshValidationSettings();
    }
    ```

この手順を実行すると、アプリケーションの Web.config は、最新のキーなど、フェデレーション メタデータ ドキュメントの最新情報で更新されます。 この更新は、IIS のアプリケーション プールがリサイクルされるたびに行われます。IIS の既定では、アプリケーションは 29 時間ごとにリサイクルされます。

キーのロールオーバー ロジックが機能していることを確認するには、次の手順に従います。

1. アプリケーションで上記のコードが使用されていることを確認したら、**Web.config** ファイルを開き、**&lt;issuerNameRegistry&gt;** ブロックに移動して、以下の行を見つけます。

    ```
    <issuerNameRegistry type="System.IdentityModel.Tokens.ValidatingIssuerNameRegistry, System.IdentityModel.Tokens.ValidatingIssuerNameRegistry">
         <authority name="https://sts.windows.net/aaaabbbb-0000-cccc-1111-dddd2222eeee/">
           <keys>
             <add thumbprint="AA11BB22CC33DD44EE55FF66AA77BB88CC99DD00" />
           </keys>
    ```
2. ** &lt;add thumbprint=""&gt;** 設定で、どれか 1 文字を別の文字に置き換えて拇印の値を変更します。 **Web.config** ファイルを保存します。
3. アプリケーションをビルドし、実行します。 サインイン プロセスを完了できる場合、アプリケーションではディレクトリのフェデレーション メタデータ ドキュメントから必要な情報をダウンロードすることによってキーが正しく更新されています。 サインインで問題が発生する場合は、[Microsoft ID プラットフォームを使用した Web アプリケーションへのサインオンの追加](https://github.com/Azure-Samples/active-directory-dotnet-webapp-openidconnect)に関する記事を読むか、次のコード サンプルをダウンロードして調べることによって、アプリケーションの変更が正しいことを確認してください: [Microsoft Entra ID 向けのマルチテナント クラウド アプリケーション](https://code.msdn.microsoft.com/multi-tenant-cloud-8015b84b)。

#### その他のライブラリが使用されているか、サポートされているプロトコルが手動で実装された、リソースを保護する Web アプリケーション/API

その他何らかのライブラリを使用している場合、またはサポートされているプロトコルを手動で実装した場合、OpenID Connect Discovery ドキュメントまたはフェデレーション メタデータ ドキュメントから確実にキーが取得されるよう、そのライブラリまたは実装内容を再確認する必要があります。 この点はたとえば、自分が記述したコードやライブラリのコードから、OpenID のディスカバリー ドキュメントまたはフェデレーション メタデータ ドキュメントの呼び出しを検索することによって確認できます。

キーがどこかに格納されている場合、またはアプリケーション内でハードコーディングされている場合は、キーを手動で取得し、このガイダンスの末尾にある手順に従って手動ロールオーバーを実行することで、適宜キーを更新できます。 将来、Microsoft ID プラットフォームでロールオーバーの周期が短くなった場合や、緊急のアウトオブバンド ロールオーバーが発生した場合に、中断やオーバーヘッドを避けるため、この記事で説明されているいずれかのアプローチを使って、**自動ロールオーバーをサポートするようにお客様のアプリケーションを拡張することを強くお勧めします**。

### アプリケーションをテストして、影響を受けるかどうかを判別する

以下のPowerShellスクリプトを使用することで、アプリケーションが自動キーロールオーバーをサポートしているかどうかを検証することができます。

PowerShell を使用して署名キーを確認および更新するには、[MSIdentityTools](https://www.powershellgallery.com/packages/MSIdentityTools) PowerShell モジュールが必要です。

1. [MSIdentityTools](https://www.powershellgallery.com/packages/MSIdentityTools) PowerShell モジュールをインストールします。

    ```powershell
    Install-Module -Name MSIdentityTools
    ```
2. Connect-MgGraphコマンドを使用して、管理者アカウントでサインインし、以下の必要なスコープを承認します。

    ```powershell
     Connect-MgGraph -Scope "Application.ReadWrite.All"
    ```
3. 使用可能な署名キーの拇印の一覧を取得します。

    ```powershell
    Get-MsIdSigningKeyThumbprint
    ```
4. 任意のキーの拇印を選択し、アプリケーションでそのキーを使用するように Microsoft Entra ID を構成します ([Microsoft Entra 管理センター](https://entra.microsoft.com/#view/Microsoft_AAD_RegisteredApps/ApplicationsListBlade)からアプリ ID を取得します):

    ```powershell
    Update-MsIdApplicationSigningKeyThumbprint -ApplicationId <ApplicationId> -KeyThumbprint <Thumbprint>
    ```
5. 新しいトークンを取得するためにサインインして、web アプリケーションをテストします。 キーの更新の変更は瞬時に行われますが、新しいトークンが発行されるように、新しいブラウザー セッション (Internet Explorer の "InPrivate" モード、Chrome の "シークレット" モード、Firefox の "プライベート" モードなど) を使用していることを確認してください。
6. 返された各署名キーの拇印について、`Update-MsIdApplicationSigningKeyThumbprint`コマンドレットを実行し、web アプリケーションのサインインプロセスをテストします。
7. Web アプリケーションが適切にサインインすると、自動ロールオーバーがサポートされます。 そうでない場合は、手動ロールオーバーをサポートするようにアプリケーションを変更します。 詳細について は、「手動ロールオーバープロセスの確立 」を参照してください。
8. 次のスクリプトを実行して、通常の動作に戻します。

    ```powershell
    Update-MsIdApplicationSigningKeyThumbprint -ApplicationId <ApplicationId> -Default
    ```

### アプリケーションで自動ロールオーバーがサポートされていない場合に手動ロールオーバーを実行する方法

アプリケーションで自動ロールオーバーがサポートされていない場合は、Microsoft ID プラットフォームの署名キーを定期的に監視し、適宜手動ロールオーバーを実行するプロセスを確立する必要があります。

PowerShell を使用して署名キーを確認および更新するには、[`MSIdentityTools`](https://www.powershellgallery.com/packages/MSIdentityTools) PowerShell モジュールが必要です。

1. [`MSIdentityTools`](https://www.powershellgallery.com/packages/MSIdentityTools) PowerShell モジュールをインストールします。

    ```powershell
    Install-Module -Name MSIdentityTools
    ```
2. 最新の署名キーを取得します ([Microsoft Entra 管理センター](https://entra.microsoft.com/#view/Microsoft_AAD_IAM/ActiveDirectoryMenuBlade/Overview)からテナント ID を取得します)。

    ```powershell
    Get-MsIdSigningKeyThumbprint -Tenant <tenantId> -Latest
    ```
3. このキーを、アプリケーションが現在ハードコードされているキー、または使用するように構成されているキーと比較します。
4. 最新のキーがアプリケーションで使用しているキーと異なる場合は、最新の署名キーをダウンロードします。

    ```powershell
    Get-MsIdSigningKeyThumbprint -Latest -DownloadPath <DownloadFolderPath>
    ```
5. 新しいキーを使用するようにアプリケーションのコードまたは構成を更新します。
6. アプリケーションでその最新のキーを使用するように Microsoft Entra ID を構成します ([Microsoft Entra 管理センター](https://entra.microsoft.com/#view/Microsoft_AAD_RegisteredApps/ApplicationsListBlade)からアプリ ID を取得します):

    ```powershell
    Get-MsIdSigningKeyThumbprint -Latest | Update-MsIdApplicationSigningKeyThumbprint -ApplicationId <ApplicationId>
    ```
7. 新しいトークンを取得するためにサインインして、web アプリケーションをテストします。 キーの更新と変更は瞬時に行われますが、新しいブラウザー セッションを使用して、新しいトークンが発行されていることを確認してください。 たとえば、Microsoft Edge の "InPrivate" モード、Chrome の "シークレット" モード、または Firefox の "プライベート" モードを使用してください。
8. 問題が発生した場合は、使用していた以前のキーに戻し、Azure サポートにお問い合わせください。

    ```powershell
    Update-MsIdApplicationSigningKeyThumbprint -ApplicationId <ApplicationId> -KeyThumbprint <PreviousKeyThumbprint>
    ```
9. 手動ロールオーバーをサポートするようにアプリケーションを更新した後、通常の動作に戻します。

    ```powershell
    Update-MsIdApplicationSigningKeyThumbprint -ApplicationId <ApplicationId> -Default
    ```
<!-- /MSL-PAGE -->
