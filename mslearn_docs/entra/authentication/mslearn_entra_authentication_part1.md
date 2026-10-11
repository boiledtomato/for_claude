# Microsoft Learn — Microsoft Entra / 認証 (MFA・パスワードレス・SSPR) (part 1)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 65

---

<!-- MSL-PAGE {"url":"entra/identity/authentication"} -->
## Microsoft Entra 認証ドキュメント - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication
- Service: entra-id / authentication
- Article date: 2025-11-05
- Summary: Microsoft Entra セルフサービス パスワード リセット、多要素認証、カスタム禁止パスワード リスト、スマート ロックアウトを管理および展開する方法について説明します。

Microsoft Entra セルフサービス パスワード リセット、多要素認証、カスタム禁止パスワード リスト、スマート ロックアウトを管理および展開する方法について説明します。

### 認証について

#### 概要

- [認証とは](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)
- [Microsoft Entra ID の認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)

### フィッシングに強い認証を展開する

#### 概念

- [パスキー (FIDO2)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)
- [Microsoft Authenticator のパスキー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-authenticator-app)
- [Windows Hello for Business](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-windows-hello)
- [証明書ベースの認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication)
- [プラットフォーム SSO](https://learn.microsoft.com/ja-jp/intune/intune-service/configuration/platform-sso-macos)

#### デプロイ

- [フィッシングに強い MFA の使用を開始する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-prerequisites-phishing-resistant-passwordless-authentication)

#### 攻略ガイド

- [組織のパスキー (FIDO2) を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2)
- [証明書ベースの認証を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-certificate-based-authentication)

### Microsoft Entra アカウントの回復

#### 概念

- [Microsoft Entra アカウントの回復とは](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-account-recovery-overview)
- [Microsoft Entra アカウントの回復に関する FAQ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/self-service-account-recovery)

#### 攻略ガイド

- [Microsoft Entra アカウントの回復を構成する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-account-recovery-enable)
- [ユーザーが自分のアカウントを回復する方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-account-recovery-for-users)

### Microsoft Entra 多要素認証を有効にする

#### 概念

- [Microsoft Entra 多要素認証のしくみ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)

#### チュートリアル

- [Microsoft Entra 多要素認証を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-azure-mfa)
- [リスクベースの Microsoft Entra 多要素認証を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-risk-based-sspr-mfa)

#### デプロイ

- [Microsoft Entra 多要素認証の展開ガイド](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-getstarted)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/accessibility/authentication-methods-accessibility"} -->
## Microsoft Entra ID で多要素認証を使用してアクセシビリティを強化する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/accessibility/authentication-methods-accessibility
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: 認証方法のアクセシビリティについて説明します

サイバーセキュリティの脅威が進化するにつれて、多要素認証 (MFA) は安全なデジタル ID の基礎となっています。 Microsoft Entra ID には、堅牢なセキュリティと、アクセシビリティの制約を持つユーザーのニーズなど、多様なユーザー ニーズ向けに設計されたさまざまな MFA メソッドが用意されています。 これらの MFA オプションによってアクセシビリティと包括性が向上するしくみについて詳しく説明します。

### Microsoft Authenticator

Microsoft Authenticator アプリは、迅速な承認のための通知を提供するか、より従来式の MFA エントリに対する時間ベースのコードを生成します。 このアプリは、スクリーン リーダーを含むさまざまな支援技術と互換性があり、視覚障碍のあるユーザーがアクセスできるようになります。 また、SMS または音声通話だけに依存したくない個人向けの柔軟性も提供します。

[Microsoft Authenticator をダウンロード](https://www.microsoft.com/security/mobile-authenticator-app?msockid=04750fac1789618938f71b4a16ee6056)。

### テキストと音声通話

テキストと音声通話のオプションは、スマートフォン アプリを使用しない可能性のあるユーザーに対応します。 これは、特定のアクセシビリティニーズを持つ個人にとって有益な場合があります。

- **テキスト:** ユーザーがテキスト メッセージを介して確認コードを受け取ることができます。これは、聴覚障碍のあるユーザーまたはテキストベースのコミュニケーションを好むユーザーに役立ちます。
- **音声通話:** 音声通話は、視覚または触知覚による情報ではなく、オーディオによる情報を提供するため、視覚障碍のあるユーザーに対する優れたオプションです。

詳しくは、[電話認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-phone-options)を参照してください。

### FIDO2 セキュリティキー

FIDO2 セキュリティ キーは、アクセス性が高く安全な MFA オプションを提供する物理デバイスです。 これらのハードウェア キーは生体認証 (指紋スキャンなど) または PIN をサポートしているため、従来のパスワードまたはその他の認証方法が困難なユーザーに対して理想的です。 FIDO2 キーは、複雑なパスワードの入力が困難な物理的な障碍を持つユーザーにとって有益です。

詳細については、[パスキー (FIDO2) を登録する方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-register-passkey-with-security-key)を参照してください。

### Windows Hello for Business

Windows Hello for Business では、生体認証 (顔認識または指紋) と PIN を活用し、迅速で安全かつアクセス可能な MFA オプションを提供します。 この方法を使用すると、身体または認知の障碍があるユーザーには困難な場合がある、パスワード入力の必要がなくなります。 生体認証を使用すると、強力なセキュリティを維持しながらシームレスにアクセスできます。

詳細については、「[Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/policy-settings?tabs=feature)」を参照してください。

### メールによる確認

他の MFA 方法ほど安全ではありませんが、メールによる確認は、フォールバック オプションを提供する特定のアクセシビリティのシナリオで役立ちます。 テキスト、音声、またはアプリベースの認証が困難なユーザーに対して、メールは使い慣れた、簡単にアクセスできる代替手段を提供できます。

参照:

- [使用可能な確認方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)
- [MFA を有効にする方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-azure-mfa)

### まとめ

Microsoft Entra ID のさまざまな MFA オプションにより、多様なニーズのある個人が、使いやすさを損なうことなく、安全な認証にアクセスできるようになります。 Microsoft Entra ID は、すべてのユーザーに対してアクセス可能で包括的なセキュリティ対策を維持するために、Authenticator アプリ、SMS と音声通話、FIDO2 キー、Windows Hello、電子メール検証などのさまざまなオプションを提供します。

適切な MFA 方法の選択は、個々のニーズと制約によって異なります。 柔軟で包括的な認証に対する Microsoft のコミットメントは、身体的または技術的な制限に関係なく、すべてのユーザーがセキュリティを維持するのに役立ちます。 特定のアクセシビリティ要件があるユーザーに対しては、各 MFA オプションを確認し、個人の好みや使いやすさのニーズに最適なものを見つける価値があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/certificate-based-authentication-faq"} -->
## Microsoft Entra CBA FAQ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/certificate-based-authentication-faq
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra 証明書ベースの認証 (CBA) についてよく寄せられる質問と回答。

この記事では、Microsoft Entra 証明書ベースの認証 (CBA) のしくみについてよく寄せられる質問について説明します。 更新されたコンテンツを確認します。

### ユーザー名を入力した後、証明書を使用して Microsoft Entra ID にサインインするオプションが表示されないのはなぜですか?

管理者は、ユーザーが使用できる証明書を使用してサインインするオプションをテナントに対して有効にする必要があります。 詳細については、「 [手順 3: 認証バインディング ポリシーを構成する」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-certificate-based-authentication#step-3-configure-an-authentication-binding-policy)参照してください。

### ユーザーのサインインが失敗した後、診断情報を取得できる場所

エラー ページで、[ **詳細** ] を選択して、テナント管理者に役立つ詳細情報を確認します。テナント管理者は、サインイン ログを確認してエラーを調査できます。 たとえば、ユーザー証明書が失効し、証明書失効リスト (CRL) に含まれている場合、認証は意図したとおりに失敗します。

### Microsoft Entra CBA を有効にする方法

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)ロールが割り当てられている [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **Entra ID**&gt;**Authentication メソッド**&gt;**Policies** に移動します。
3. **証明書ベースの認証**ポリシーを選択します。
4. [ **有効]タブと[ターゲット** ]タブで、[ **有効にする**]を選択します。

### Microsoft Entra CBA は無料の機能ですか?

Microsoft Entra CBA は無料の機能です。

Microsoft Entra ID のすべてのエディションには、Microsoft Entra CBA が含まれています。

各 Microsoft Entra エディションの機能の詳細については、Microsoft Entra の価格 を参照してください。

### Microsoft Entra CBA では、userPrincipalName の代わりに代替 ID がユーザー名としてサポートされていますか?

No. 現時点では、代替メールなどの UPN 以外の値を使用したサインインはサポートされていません。

### 証明機関に対して複数の CRL 配布ポイントを使用できますか?

いいえ。証明機関 (CA) ごとにサポートされている CRL 配布ポイント (CDP) は 1 つだけです。

### CDP に HTTP 以外の URL を使用できますか?

No. CDP は HTTP URL のみをサポートします。

### CA の CRL を見つける方法、または "AADSTS2205015: 証明書失効リスト (CRL) が署名検証に失敗しました" というエラーのトラブルシューティングを行うにはどうすればよいですか?

CRL をダウンロードし、CA 証明書と CRL 情報を比較して、 `crlDistributionPoint` 値が追加する CA に対して有効であることを検証します。 CA の発行者サブジェクト キー識別子 (SKI) を CRL の機関キー識別子 (AKI) と照合することで、対応する CA への CRL を構成できます (CA 発行者 SKI == CRL AKI)。

次の表と図は、CA 証明書からダウンロードした CRL の属性に情報をマップする方法を示しています。

| CA 証明書の情報 | = | ダウンロードした CRL 情報 |
| --- | --- | --- |
| 件名 | = | 発行者 |
| サブジェクト キー識別子 (SKI) | = | 機関キー識別子 (KeyID) |

[Image: CA 証明書フィールドと CRL 情報を比較するスクリーンショット。]

### CA 構成を検証する方法

信頼ストアの証明機関の構成によって、証明機関の信頼チェーンを両方とも検証する Microsoft Entra の機能が得られるようにすることが重要です。 さらに、構成された証明機関 CRL 配布ポイント (CDP) から証明書失効リスト (CRL) を正常に取得する必要があります。 このタスクを支援するには、 [MSIdentity Tools](https://aka.ms/msid) PowerShell モジュールをインストールし、 [Test-MsIdCBATrustStoreConfiguration](https://github.com/AzureAD/MSIdentityTools/wiki/Test-MsIdCBATrustStoreConfiguration) を実行することをお勧めします。 この PowerShell コマンドレットは、Microsoft Entra テナント証明機関の構成を確認し、構成ミスの一般的な問題に関するエラー/警告を表示します。

### 認証方法ポリシーの変更はすぐに有効になりますか?

ポリシーがキャッシュされます。 ポリシーの更新後、変更が有効になるまでに最大 1 時間かかる場合があります。

### 失敗した後に CBA オプションが表示される理由

認証方法ポリシーでは、ユーザーが任意の方法を使用してサインインを再試行できるように、使用可能なすべての認証方法が常にユーザーに表示されます。

Microsoft Entra ID は、サインインの成功または失敗に基づいて使用可能なメソッドを非表示にしません。

### 失敗した後に CBA がループするのはなぜですか?

証明書ピッカーが表示された後、ブラウザーによって証明書がキャッシュされます。 ユーザーが認証を再試行すると、キャッシュされた証明書が自動的に使用されます。 ユーザーはブラウザーを閉じ、新しいセッションをもう一度開いて CBA をもう一度試す必要があります。

### 単一要素証明書を使用する場合、他の認証方法を登録するための ID 証明がオプションとして表示されないのはなぜですか?

ユーザーが認証方法ポリシーで CBA のスコープ内にある場合、ユーザーは多要素認証 (MFA) が可能と見なされます。 このポリシー要件は、ユーザーが認証の一部として ID 証明を使用して他の使用可能な方法を登録できないことを意味します。

### 単一要素証明書を使用して MFA を完了するにはどうすればよいですか?

MFA を取得するための単一要素 CBA がサポートされています。 パスワードなしの電話によるサインインを含む CBA シングルファクターと FIDO2 を使用した CBA 単一要素は、単一要素証明書を使用して MFA を取得するためにサポートされる 2 つの組み合わせです。

詳細については、 [単一要素証明書を使用した MFA に関する説明](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-technical-deep-dive#mfa-authentication-flow-by-using-single-factor-certificates-and-passwordless-sign-in)を参照してください。

### certificateUserIds の更新は、既存の値であるため失敗します。 管理者は、同じ値を持つすべてのユーザー オブジェクトに対してクエリを実行するにはどうすればよいですか?

テナント管理者は、Microsoft Graph クエリを実行して、特定の `certificateUserIds` 値を持つすべてのユーザーを検索できます。 詳細については、「 [`certificateUserIds` Graph クエリ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-certificateuserids#update-certificateuserids-using-microsoft-graph-queries)」を参照してください。

たとえば、このコマンドは、`bob@contoso.com`で`certificateUserIds`値を持つすべてのユーザー オブジェクトを返します。

```http
GET  https://graph.microsoft.com/v1.0/users?$filter=certificateUserIds/any(x:x eq 'bob@contoso.com')
```

### Microsoft Entra CBA は Microsoft Surface Hub で使用できますか?

はい。 CBA は、スマート カードとスマート カード リーダーのほとんどの組み合わせにすぐに使用できます。 スマート カードとスマート カード リーダーの組み合わせに他のドライバーが必要な場合は、Surface Hub でスマート カードとスマート カード リーダーの組み合わせを使用する前にドライバーをインストールする必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/certificate-based-authentication-federation-android"} -->
## フェデレーションを使用した Android 証明書ベースの認証 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/certificate-based-authentication-federation-android
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Android デバイスでソリューションに証明書ベースの認証を構成するための対応シナリオや要件について説明する

Android デバイスは、次に接続する場合、証明書ベースの認証 (CBA) を使用して、そのデバイス上のクライアント証明書で Microsoft Entra ID に対して認証できます。

- Microsoft Outlook や Microsoft Word などの Office モバイル アプリケーション
- Exchange ActiveSync (EAS) クライアント

この機能を構成すると、モバイル デバイスで特定のメールおよび Microsoft Office アプリケーションにユーザー名とパスワードの組み合わせを入力する必要がなくなります。

### Microsoft モバイル アプリケーションのサポート

| アプリケーション | サポート |
| --- | --- |
| Azure Information Protection アプリ | [Image: Check mark signifying support for this application] |
| Intune [ポータル サイト] | [Image: Check mark signifying support for this application] |
| Microsoft Teams | [Image: Check mark signifying support for this application] |
| OneNote | [Image: Check mark signifying support for this application] |
| OneDrive | [Image: Check mark signifying support for this application] |
| Outlook | [Image: Check mark signifying support for this application] |
| Power BI | [Image: Check mark signifying support for this application] |
| Skype for Business | [Image: Check mark signifying support for this application] |
| Word/Excel/PowerPoint | [Image: Check mark signifying support for this application] |
| Yammer | [Image: Check mark signifying support for this application] |

#### 実装の要件

デバイスの OS バージョンは、Android 5.0 (Lollipop) 以降である必要があります。

フェデレーション サーバーを構成する必要があります。

Microsoft Entra ID でクライアント証明書を失効させるには、AD FS トークンに次の要求が必要です。

- `http://schemas.microsoft.com/ws/2008/06/identity/claims/<serialnumber>` (クライアント証明書のシリアル番号)
- `http://schemas.microsoft.com/2012/12/certificatecontext/field/<issuer>` (クライアント証明書の発行者の文字列)

Microsoft Entra ID を使用すると、これらの要求が AD FS トークン (またはその他の SAML トークン) で使用できる場合に、このような要求を更新トークンに追加することができます。 更新トークンを検証する必要がある場合、この情報を使用して失効を確認します。

ベスト プラクティスとして、組織の AD FS エラー ページを次の情報で更新するようにしてください。

- Android に Microsoft Authenticator をインストールするための要件。
- ユーザー証明書を取得する手順

詳細については、「[AD FS サインイン ページのカスタマイズ](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2012-R2-and-2012/dn280950%28v=ws.11%29)」を参照してください。

先進認証を有効にした Office アプリでは、要求で "*prompt=login*" が Microsoft Entra ID に送信されます。 既定では、Microsoft Entra ID によって、AD FS への要求内の "*prompt=login*" が、"*wauth=usernamepassworduri*" (AD FS に U/P 認証を実行するよう依頼する) および "*wfresh=0*" (AD FS に SSO 状態を無視し、新しい認証を実行するよう依頼する) として変換されます。 これらのアプリに対して証明書ベースの認証を有効にするには、既定の Microsoft Entra の動作を変更する必要があります。 フェデレーション ドメインの設定で "*PromptLoginBehavior*" を "*Disabled*" に設定します。 [New-MgDomainFederationConfiguration](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/new-mgdomainfederationconfiguration) を使って、このタスクを実行できます。

```powershell
New-MgDomainFederationConfiguration -DomainId <domain> -PromptLoginBehavior "disabled"
```

### Exchange ActiveSync クライアントのサポート

Android 5.0 (Lollipop) 以降の特定の Exchange ActiveSync アプリケーションがサポートされています。 電子メール アプリケーションがこの機能をサポートしているかどうかを確認するには、アプリケーション開発者に問い合わせてください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/certificate-based-authentication-federation-get-started"} -->
## フェデレーションを使用した証明書ベースの認証 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/certificate-based-authentication-federation-get-started
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: 環境内でフェデレーションを使用して証明書ベースの認証を構成する方法について説明します

フェデレーションを使用した証明書ベースの認証 (CBA) を使用すると、Microsoft Entra ID は、Exchange オンライン アカウントを次に接続するときに、Windows、Android、または iOS デバイス上のクライアント証明書を使用してユーザーを認証できます。

- Microsoft Outlook や Microsoft Word などの Microsoft モバイル アプリケーション
- Exchange ActiveSync (EAS) クライアント

この機能を構成すると、モバイル デバイス上の特定のメールおよび Microsoft Office アプリケーションにユーザー名とパスワードの組み合わせを入力する必要がなくなります。

注

別の方法として、組織はフェデレーションを必要とせずに Microsoft Entra CBA を展開できます。 詳細については、「 [Microsoft Entra ID に対する Microsoft Entra 証明書ベースの認証の概要」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication)参照してください。

このトピック:

- Office 365 Enterprise、Business、Education、US Government プランのテナントのユーザーに対して CBA を構成して使用する手順について説明します。
- [公開キー 基盤 (PKI)](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2012-R2-and-2012/hh831740%28v=ws.11%29) と [AD FS](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-whatis) が既に構成されていることを前提としています。

### 要求事項

フェデレーションを使用して CBA を構成するには、次のステートメントが true である必要があります。

- フェデレーションを使用する CBA は、ブラウザー アプリケーション、先進認証を使用するネイティブ クライアント、または MSAL ライブラリのフェデレーション環境でのみサポートされます。 1 つの例外は Exchange Online (EXO) の Exchange Active Sync (EAS) であり、フェデレーション アカウントとマネージド アカウントに使用できます。 フェデレーションを必要とせずに Microsoft Entra CBA を構成するには、「 [Microsoft Entra 証明書ベースの認証を構成する方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-certificate-based-authentication)」を参照してください。
- ルート証明機関と中間証明機関は、Microsoft Entra ID で構成する必要があります。
- 各証明機関には、インターネットに接続する URL を介して参照できる証明書失効リスト (CRL) が必要です。
- Microsoft Entra ID で少なくとも 1 つの証明機関が構成されている必要があります。 関連する手順は、「 証明機関の構成」 セクションにあります。
- Exchange ActiveSync クライアントの場合、クライアント証明書には、[サブジェクトの別名] フィールドの [プリンシパル名] または [RFC822 Name] の値に、Exchange Online のユーザーのルーティング可能な電子メール アドレスが必要です。 Microsoft Entra ID は、RFC822 値をディレクトリ内のプロキシ アドレス属性にマップします。
- クライアント デバイスは、クライアント証明書を発行する少なくとも 1 つの証明機関にアクセスできる必要があります。
- クライアント認証用のクライアント証明書がクライアントに発行されている必要があります。

重要

Microsoft Entra ID が正常にダウンロードおよびキャッシュするための CRL の最大サイズは 20 MB で、CRL のダウンロードに必要な時間は 10 秒を超えてはなりません。 Microsoft Entra ID が CRL をダウンロードできない場合、対応する CA によって発行された証明書を使用した証明書ベースの認証は失敗します。 CRL ファイルがサイズの制約内にあることを確認するためのベスト プラクティスは、証明書の有効期間を妥当な制限内に保ち、期限切れの証明書をクリーンアップすることです。

### 手順 1: デバイス プラットフォームを選択する

最初の手順として、関心のあるデバイス プラットフォームの場合は、次の内容を確認する必要があります。

- Office モバイル アプリケーションのサポート
- 特定の実装要件

関連情報は、次のデバイス プラットフォームに存在します。

- [アンドロイド](https://learn.microsoft.com/ja-jp/entra/identity/authentication/certificate-based-authentication-federation-android)
- [iOS](https://learn.microsoft.com/ja-jp/entra/identity/authentication/certificate-based-authentication-federation-ios)

### 手順 2: 証明機関を構成する

Microsoft Entra ID で証明機関を構成するには、証明機関ごとに次のものをアップロードします。

- 証明書のパブリック部分 ( *.cer* 形式)
- 証明書失効リスト (CRL) が存在する、インターネットに接続する URL

証明機関のスキーマは次のようになります。

```csharp
    class TrustedCAsForPasswordlessAuth
    {
       CertificateAuthorityInformation[] certificateAuthorities;
    }

    class CertificateAuthorityInformation

    {
        CertAuthorityType authorityType;
        X509Certificate trustedCertificate;
        string crlDistributionPoint;
        string deltaCrlDistributionPoint;
        string trustedIssuer;
        string trustedIssuerSKI;
    }

    enum CertAuthorityType
    {
        RootAuthority = 0,
        IntermediateAuthority = 1
    }
```

構成には、 [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph) を使用できます。

1. Windows PowerShell を管理者特権で起動します。
2. [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) をインストールします。

    ```powershell
        Install-Module Microsoft.Graph
    ```

構成の最初の手順では、テナントとの接続を確立する必要があります。 テナントへの接続が確立されるとすぐに、ディレクトリに定義されている信頼された証明機関をレビュー、追加、削除、および変更できます。

#### 接続する

テナントとの接続を確立するには、 [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph) を使用します。

```powershell
    Connect-MgGraph
```

#### 取得

ディレクトリで定義されている信頼された証明機関を取得するには、 [Get-MgOrganizationCertificateBasedAuthConfiguration](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/get-mgorganizationcertificatebasedauthconfiguration) を使用します。

```powershell
    Get-MgOrganizationCertificateBasedAuthConfiguration
```

重要

Microsoft は、アクセス許可が最も少ないロールを使用することを推奨しています。 このプラクティスは、組織のセキュリティを強化するのに役立ちます。 グローバル管理者は、緊急シナリオに限定する必要がある、または既存のロールを使用できない場合に、高い特権を持つロールです。

CA を追加、変更、または削除するには、Microsoft Entra 管理センターを使用します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[ID セキュリティ スコア]**&gt;**[証明機関]** を参照します。
3. CA をアップロードするには、[ **アップロード**] を選択します。

    1. CA ファイルを選択します。
    2. CA がルート証明書の場合は **[はい]** を選択し、それ以外の場合は **[いいえ]** を選択します。
    3. **[証明書失効リスト URL]** には、失効したすべての証明書を含む CA ベース CRL のインターネットに接続する URL を設定します。 URL が設定されていない場合、失効した証明書を使用した認証は失敗しません。
    4. **[デルタ証明書失効リストの URL]** には、最後のベース CRL が発行されて以降に失効したすべての証明書を含む CRL のインターネットに接続する URL を設定します。
    5. [**] を選択し、[**] を追加します。

        [Image: 証明機関ファイルをアップロードする方法のスクリーンショット。]
4. CA 証明書を削除するには、証明書を選択し、**[削除]** を選択します。
5. **[列]** を選択して列を追加または削除します。

### 手順 3: 失効を構成する

クライアント証明書を失効させるために、Microsoft Entra ID は、証明機関の情報の一部としてアップロードされた URL から証明書失効リスト (CRL) をフェッチし、キャッシュします。 CRL の最後の発行タイムスタンプ (**有効日** プロパティ) は、CRL がまだ有効であることを確認するために使用されます。 CRL は定期的に参照されて、リストに含まれる証明書へのアクセスは無効になります。

即時の失効が必要な場合 (たとえば、ユーザーがデバイスを紛失した場合) は、ユーザーの認証トークンを無効にできます。 承認トークンを無効にするには、Windows PowerShell を使用して、この特定のユーザーの **StsRefreshTokensValidFrom** フィールドを設定します。 アクセスを取り消す各ユーザーの **StsRefreshTokensValidFrom** フィールドを更新する必要があります。

失効が維持されるようにするには、CRL の **有効日** を **StsRefreshTokensValidFrom** によって設定された値の後の日付に設定し、問題の証明書が CRL にあることを確認する必要があります。

次の手順では、 **StsRefreshTokensValidFrom** フィールドを設定して承認トークンを更新および無効化するプロセスについて説明します。

```https
# Authenticate to Microsoft Graph
Connect-MgGraph -Scopes "User.Read.All"

# Get the user
$user = Get-MgUser -UserPrincipalName "test@contoso.com"

# Get the StsRefreshTokensValidFrom property
$user.StsRefreshTokensValidFrom
```

設定する日付は、現在より後の日付にする必要があります。 日付が将来でない場合、 **StsRefreshTokensValidFrom** プロパティは設定されません。 日付が将来の場合、 **StsRefreshTokensValidFrom** は現在の時刻に設定されます (Set-MsolUser コマンドで示される日付ではありません)。

### 手順 4: 構成をテストする

#### 証明書のテスト

最初の構成テストとして、[デバイス上のブラウザー](https://outlook.office365.com)を使用して [Outlook Web Access](https://microsoft.sharepoint.com) または **SharePoint Online** にサインインする必要があります。

サインインが成功した場合、次のことがわかります。

- ユーザー証明書がテスト デバイスにプロビジョニングされている
- AD FS が正しく構成されている

#### Office モバイル アプリケーションのテスト

1. テスト デバイスに、Office モバイル アプリケーション (OneDrive など) をインストールします。
2. アプリケーションを起動します。
3. ユーザー名を入力し、使用するユーザー証明書を選択します。

正常にサインインしている必要があります。

#### Exchange ActiveSync クライアント アプリケーションのテスト

証明書ベースの認証を使用して Exchange ActiveSync (EAS) にアクセスするには、クライアント証明書を含む EAS プロファイルをアプリケーションで使用できる必要があります。

EAS プロファイルには、次の情報が含まれている必要があります。

- 認証に使用するユーザー証明書
- EAS エンドポイント (たとえば、outlook.office365.com)

EAS プロファイルは、Microsoft Intune などのモバイル デバイス管理 (MDM) を使用するか、デバイスの EAS プロファイルに証明書を手動で配置することで、デバイスに構成して配置できます。

#### Android での EAS クライアント アプリケーションのテスト

1. 前のセクションの要件を満たす EAS プロファイルをアプリケーションで構成します。
2. アプリケーションを開き、メールが同期していることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/certificate-based-authentication-federation-ios"} -->
## iOS でのフェデレーションを使用した証明書ベースの認証 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/certificate-based-authentication-federation-ios
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: サポートされているシナリオと、iOS デバイスを使用するソリューションで Microsoft Entra ID の証明書ベースの認証を構成するための要件について説明します

セキュリティを向上させるために、iOS デバイスでは、証明書ベースの認証 (CBA) を使用して、次のアプリケーションまたはサービスに接続するときに、デバイス上のクライアント証明書を使用して Microsoft Entra ID に対する認証を行うことができます。

- Microsoft Outlook や Microsoft Word などの Office モバイル アプリケーション
- Exchange ActiveSync (EAS) クライアント

証明書を使用すると、モバイル デバイス上の特定のメールおよび Microsoft Office アプリケーションにユーザー名とパスワードの組み合わせを入力する必要がなくなります。

### Microsoft モバイル アプリケーションのサポート

| アプリ | 支援 |
| --- | --- |
| Azure Information Protection アプリ | [Image: このアプリケーションのサポートを示すチェック マーク] |
| ポータル サイト | [Image: このアプリケーションのサポートを示すチェック マーク] |
| Microsoft Teams | [Image: このアプリケーションのサポートを示すチェック マーク] |
| Office (モバイル) | [Image: このアプリケーションのサポートを示すチェック マーク] |
| OneNote | [Image: このアプリケーションのサポートを示すチェック マーク] |
| OneDrive | [Image: このアプリケーションのサポートを示すチェック マーク] |
| 前途 | [Image: このアプリケーションのサポートを示すチェック マーク] |
| Power BI | [Image: このアプリケーションのサポートを示すチェック マーク] |
| Skype for Business | [Image: このアプリケーションのサポートを示すチェック マーク] |
| Word/Excel/PowerPoint | [Image: このアプリケーションのサポートを示すチェック マーク] |
| Yammer | [Image: このアプリケーションのサポートを示すチェック マーク] |

### 要求事項

iOS で CBA を使用するには、次の要件と考慮事項が適用されます。

- デバイスの OS バージョンは iOS 9 以降である必要があります。
- iOS 上の Office アプリケーションには、Microsoft Authenticator が必要です。
- ID 設定は、AD FS サーバーの認証 URL を含む macOS キーチェーンに作成する必要があります。 詳細については、「 [Mac のキーチェーン アクセスで ID 設定を作成](https://support.apple.com/guide/keychain-access/create-an-identity-preference-kyca6343b6c9/mac)する」を参照してください。

次の Active Directory フェデレーション サービス (AD FS) の要件と考慮事項が適用されます。

- AD FS サーバーで証明書認証を有効にし、フェデレーション認証を使用する必要があります。
- 証明書では、拡張キー使用法 (EKU) を使用し、 *サブジェクトの別名 (NT プリンシパル名*) にユーザーの UPN を含める必要があります。

### AD FS の構成

Microsoft Entra ID でクライアント証明書を取り消すには、AD FS トークンに次の要求が必要です。 Microsoft Entra ID は、AD FS トークン (またはその他の SAML トークン) で使用できる場合、これらの要求を更新トークンに追加します。 更新トークンを検証する必要がある場合は、この情報を使用して失効を確認します。

- `http://schemas.microsoft.com/ws/2008/06/identity/claims/<serialnumber>` - クライアント証明書のシリアル番号を追加する
- `http://schemas.microsoft.com/2012/12/certificatecontext/field/<issuer>` - クライアント証明書の発行者の文字列を追加します

ベスト プラクティスとして、組織の AD FS エラー ページも次の情報で更新する必要があります。

- iOS に Microsoft Authenticator をインストールするための要件。
- ユーザー証明書を取得する方法について説明します。

詳細については、 [AD FS サインイン ページのカスタマイズを](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2012-R2-and-2012/dn280950%28v=ws.11%29)参照してください。

### Office アプリで先進認証を使用する

先進認証が有効になっている一部の Office アプリは、要求で microsoft Entra ID に `prompt=login` を送信します。 既定では、Microsoft Entra ID は要求の `prompt=login` を `wauth=usernamepassworduri` として AD FS に変換し (AD FS に U/P 認証を実行するように要求します)、 `wfresh=0` (SSO 状態を無視して新しい認証を行うように AD FS に要求します)。 これらのアプリに対して証明書ベースの認証を有効にする場合は、既定の Microsoft Entra 動作を変更します。

既定の動作を更新するには、フェデレーション ドメイン設定の "*PromptLoginBehavior*" を *[無効]* に設定します。 次の例に示すように、 [New-MgDomainFederationConfiguration](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/new-mgdomainfederationconfiguration) コマンドレットを使用してこのタスクを実行できます。

```powershell
New-MgDomainFederationConfiguration -DomainId <domain> -PromptLoginBehavior "disabled"
```

### Exchange ActiveSync クライアントのサポート

iOS 9 以降では、ネイティブの iOS メール クライアントがサポートされています。 この機能が他のすべての Exchange ActiveSync アプリケーションでサポートされているかどうかを確認するには、アプリケーション開発者に問い合わせてください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-account-recovery-overview"} -->
## Microsoft Entra IDのアカウント回復の概要 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-account-recovery-overview
- Service: entra-id / authentication
- Article date: 2026-04-29
- Summary: Microsoft Entra IDでのアカウントの回復により、すべての認証方法が失われた場合に、ユーザーは ID 検証を通じてアクセスを回復できます。 しくみについて説明します。

アカウントの回復は、プライマリ デバイスの紛失、盗難、侵害など、登録されているすべての認証方法を失ったときに、ユーザーが組織のアカウントへのアクセスを回復できるようにするMicrosoft Entra ID機能です。 ユーザーが少なくとも 1 つの認証方法を保持する必要があるセルフサービス パスワード リセット (SSPR) とは異なり、アカウントの回復では、アクセスを復元する前に、サード パーティの ID 検証を通じてユーザーの ID の信頼を再確立します。

アカウントの回復は、ヘルプデスク主導の手動回復を自動化された ID 証明に置き換えます。 従来のヘルプデスクの回復は、悪意のあるアクターがサポート スタッフを操作して不正にアクセスを許可するソーシャル エンジニアリング攻撃に対して脆弱です。 アカウントの回復では、認定された ID 検証プロバイダーによる政府発行の識別と生体認証の検証を使用して、この攻撃ベクトルを排除します。

### アカウント回復の基礎

アカウントの回復は、ユーザーが完全な認証ロックアウトに直面するシナリオの認証回復メカニズムです。登録されているすべての方法は使用できず、従来のセルフサービス オプションは役に立たない。

#### 主要な特徴

- **ID 中心** - 政府発行の ID と生体認証を使用した包括的な ID 検証を通じて、 *ユーザーが知* っているもの (パスワード) や *自分が持っているもの* (デバイス、セキュリティ キー) から自分 *の身元* を回復します。
- **信頼の再確立** — 復旧を再オンボード シナリオとして扱います。 組織は、新しい認証方法を登録するためのアクセス権を付与する前に、ユーザーの ID に対する信頼を検証して再確立します。
- **検証済み ID に対して構築** — Microsoft Security ストアで利用できるサード パーティの ID 検証プロバイダーと共にMicrosoft Entra Verified IDと Face Check を使用します。
- **プロファイルベースの構成** - 管理者は、検証を実行するプロバイダー、回復モードの適用方法、アカウントの検証のしくみなど、ユーザー作成ごとの回復動作を定義する ID 検証プロファイルを作成します。

#### アカウント回復を使用するタイミング

- **デバイスの紛失または盗難** - ユーザーは、認証アプリ、パスキー、またはその他の認証方法を含む電話またはセキュリティ キーを紛失します。
- **認証ロックアウトの完了** - 登録されているすべての認証方法が同時に使用できなくなります。
- **アカウント侵害対応** — セキュリティ インシデントでは、インシデント対応の一環として、完全な認証方法のリセットが必要です。

#### セルフサービス パスワード リセットと比較したアカウントの回復

アカウントの回復と SSPR はどちらもユーザー アクセスを復元しますが、さまざまなシナリオに対処します。

| 特徴 | セルフサービス パスワード リセット (SSPR) | アカウントの回復 |
| --- | --- | --- |
| **主なユース ケース** | ユーザーはパスワードを忘れたが、認証方法へのアクセスを保持する | ユーザーがすべての認証方法へのアクセスを失った |
| **認証要件** | 少なくとも 1 つの登録済みメソッド (ポリシーには最大 2 つが必要な場合があります) | 認定プロバイダーを介した ID 検証 |
| **信頼の前提条件** | ユーザーの ID は、既存のメソッドを使用して検証されます | ユーザーの ID を再確立する必要がある |
| **復旧スコープ** | パスワードのみ | 認証方法のリセットを完了する |
| **テクノロジの依存関係** | 既存の MFA メソッド | ID 検証サービス、検証済み ID |
| **セキュリティ レベル** | Medium — 事前登録されたメソッドに依存 | High — 包括的な ID 証明が必要 |

#### ビジネス上の利点

- **ヘルプデスクの負担の軽減** - ロックアウトシナリオ全体のセルフサービス復旧をセキュリティで保護することで、手動介入を必要とする優先度の高いサポート チケットが削減されます。
- **ユーザー エクスペリエンスの向上** - ユーザーはヘルプデスクの可用性を待たずに個別にアカウントを回復し、重大な状況でのダウンタイムを短縮します。
- **より強力なセキュリティ体制** - ID 検証は、ソーシャル エンジニアリングの脆弱な情報に依存する従来の復旧方法よりも強力な保証を提供します。
- **リモートワークに対応できる拡張性** - 組織は、物理的なプレゼンスや複雑な手動検証を必要とせずに、分散型従業員をサポートします。

Tip

Microsoft Entra 管理センターのアカウント回復の概要ページには、従来のヘルプデスクによる回復コストとセルフサービスのアカウント回復を比較し、組織が潜在的なコスト削減を見積もるのに役立つ **コスト削減見積もりツール** が含まれています。

### アカウントの回復のしくみ

アカウントの回復は、サインイン時に開始される構造化された ID 検証と信頼の再確立プロセスを通じて動作します。

#### コア コンポーネント

- **ID 検証プロバイダー (IDV)** - 政府発行のドキュメント、生体認証検証、およびその他の高保証方法を使用してユーザー ID を検証する、信頼できるサード パーティのサービス。 これらのプロバイダーは、ユーザーの検証済み ID を証明する検証可能な資格情報を発行します。 プロバイダーは、[Microsoft Security Store](https://securitystore.microsoft.com/) を通じて利用できます。
- **Microsoft Entra Verified ID** — ユーザーが回復中に暗号でセキュリティで保護された ID 資格情報を提示できるようにする分散型 ID サービス。 これらの資格情報は、機密性の高い個人情報を公開することなく、改ざんを防止する本人確認を提供します。 詳細については、「[Microsoft Entra Verified ID の概要](https://learn.microsoft.com/ja-jp/entra/verified-id/decentralized-identifier-overview)」を参照してください。
- **Identity 検証プロファイル** — 特定のユーザー グループのアカウント回復のしくみを定義するMicrosoft Entra 管理センター内の構成オブジェクト。 各プロファイルは、回復モード、ターゲット ユーザー グループ、ID 検証プロバイダー、およびアカウント検証規則を指定します。 組織は複数のプロファイルを作成して、異なる要件を持つさまざまなユーザー集団をサポートできます。
- **Custom 認証拡張機能** - 回復中に組織固有のアカウント検証ロジックを追加する省略可能なAzure関数、ロジック アプリ、または REST API エンドポイント。 ID 検証プロバイダーからの検証済み要求が拡張機能に渡され、HRIS システムや従業員ディレクトリなどの組織データに対して検証されます。 拡張機能では、サービス間通信にマネージド ID またはその他のシークレットレス認証を使用する必要があります。 拡張機能によって処理されるすべてのデータは、組織の信頼境界内に留まります。組織のデータはMicrosoftと共有されません。

#### 復旧フロー

アカウント回復プロセスでは、複数の検証レイヤーを組み合わせて、正当なアカウント所有者のみがアクセスを回復できるようにします。

#### アカウントの検出

ユーザーはサインイン時に自分のアカウント識別子を指定し、自分のアカウントにアクセスできないことを示します。 システムは、テナント管理者によって構成された ID 検証プロファイルに基づいてアカウントが回復の対象であるかどうかを確認し、ユーザーを適切な ID 検証プロバイダーに誘導します。

#### ID 検証

ユーザーは、該当するプロファイルで指定された ID 検証プロバイダーにリダイレクトされます。 プロバイダーは、高度な不正行為検出を使用して、政府発行の ID ドキュメントを検証します。 ライブネス チェックと顔認識により、その人が物理的に存在することを確認します。 検証が成功すると、ユーザーは、Microsoft Authenticatorに格納されている検証可能な資格情報 (検証済み ID) を受け取ります。

#### 資格情報の検証

ユーザーは、新しく取得した検証済み ID をMicrosoft Entra IDに提示します。 システムは資格情報の信頼性を検証し、資格情報の ID 属性を格納されているユーザー プロファイル情報 (既定では名と姓) と照合します。 カスタム認証拡張機能がプロファイルで構成されている場合、追加の要求検証が組織データに対して実行されます。

#### アクセスの復元

ユーザーは、有効期間が制限された一時アクセス パスを受け取り、パスキーなどの新しい認証方法を登録する方法を案内されます。

### ID 検証プロファイル

ID 検証プロファイルは、アカウント回復の中心的な構成オブジェクトです。 各プロファイルは次を定義します。

- **プロファイル名と説明** - プロファイルの目的を識別するためのフレンドリ名。
- **回復モード** : プロファイルが評価モードで動作するか (テストのみ — アカウントは復旧されません)、運用モード (完全復旧が有効) かどうか。
- **ユーザー グループ スコープ** - プロファイルが適用されるユーザー。含まれるグループと除外されたグループによって定義されます。
- **ID 検証プロバイダー** - このプロファイルのユーザーに対して ID 証明を実行するサード パーティのプロバイダー。
- **アカウント検証規則** - 一致の信頼度 (正確または緩和) やオプションのカスタム認証拡張機能の検証など、ID 要求を Entra ユーザー プロパティと照合する方法。

組織では、複数のプロファイルを作成して、ユーザーの母集団間で異なる要件に対処します。 たとえば、企業の従業員のプロファイルでは、現場担当者のプロファイルとは異なる ID 検証プロバイダーまたはより厳密な検証規則を使用できます。

#### 評価モードと運用モード

各 ID 検証プロファイルは、次の 2 つのモードのいずれかで動作します。

- **評価** — ユーザーは、ID 検証フローをテストして、正しく動作することを確認できます。 アカウントは、このモードでは回復 **されません** 。 評価モードを使用して、完全復旧を有効にする前に、小さなグループでエクスペリエンスを検証します。
- **運用** - ID 検証を完了したユーザーは、アカウントを完全に回復し、一時的なアクセス パスを受け取り、認証方法を再登録できます。

新しいプロファイルの評価モードから開始し、運用環境に切り替える前に、ユーザーとポリシーに対して ID 検証フローが機能することを確認します。

Note

本人確認には、政府機関が発行したドキュメントと生体認証データをサード パーティのプロバイダーを介して処理する必要があります。 アカウントの回復を展開する前に、組織のプライバシー、データ保持、地域のコンプライアンス要件を確認します。

#### アカウント検証とカスタム認証拡張機能

既定では、アカウントの回復は、検証プロバイダーからの ID 要求を、Microsoft Entra IDのユーザーの **First name** プロパティおよび **Last name** プロパティと照合します。 管理者は、一致の信頼度レベルを構成できます。

- **Exact** — 要求は正確に一致する必要があります。
- **緩和** — 名前照合における軽微な差異を許容します。

Tip

機密性の高いユーザー集団の場合は、カスタム認証拡張機能を有効にして、姓と名以外の追加の要求を検証することを検討してください。 既定の名前のみの照合では、リスクの高いアカウントに対して十分な保証が提供されない場合があります。

より強力な検証を必要とする組織では、カスタム認証拡張機能によってアカウント照合の 2 番目のレイヤーが追加されます。 復旧中、ID 検証プロバイダーからの検証済み要求は、組織所有のエンドポイント (Azure関数、ロジック アプリ、または REST API) に渡されます。これにより、次のような権限のあるデータ ソースに対して検証されます。

- HRIS システム (従業員レコード)
- 従業員ディレクトリ
- バッジ管理システム
- その他の組織所有のデータ ストア

Important

カスタム認証拡張機能によって処理されるデータは、組織の信頼境界内に留まります。 組織データはMicrosoftと共有されません。一致した結果のみがアカウント復旧フローに返されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-authentication-authenticator-app"} -->
## Microsoft Authenticator の認証方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-authenticator-app
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: サインインのセキュリティを守るために Microsoft Entra ID で Microsoft Authenticator を使用する方法について説明します

Microsoft Authenticator は、Microsoft Entra の職場または学校アカウントまたは Microsoft アカウントにもう 1 つのセキュリティ レベルを提供します。 [Android](https://go.microsoft.com/fwlink/?linkid=866594) と [iOS](https://go.microsoft.com/fwlink/?linkid=866594) でご利用可能です。 Microsoft Authenticator アプリを使うと、ユーザーはサインイン時にパスワードレスで認証を行うことができます。 さらに、セルフサービス パスワード リセット (SSPR) または多要素認証 (MFA) イベントの間の認証オプションとして使用することもできます。

Microsoft Authenticator では、通知と確認コードを使用することによりパスキー、パスワードレス サインイン、MFA がサポートされます。

- ユーザーは Authenticator アプリでパスキーを使用してサインインし、生体認証サインインまたはデバイス PIN を使ってフィッシングに強い認証を実行できます。
- ユーザーは、Authenticator の通知を設定し、ユーザー名とパスワードの代わりに Authenticator を使ってサインインできます。
- ユーザーはモバイル デバイスで MFA 要求を受け取り、携帯電話からサインインの試行を承認または拒否できます。
- さらに、Authenticator アプリで OATH 確認コードを使用して、サインイン インターフェイスにコードを入力することもできます。

詳細については、「[Microsoft Authenticator を使ったパスワードレスのサインインを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-phone)」を参照してください。

注

ポータル サイトのバージョンが 2111 (5.0.5333.0) より前の Android ユーザーは、ポータル サイト アプリケーションを新しいバージョンに更新するまで Authenticator を登録できません。

### パスキー サインイン

Authenticator は、ユーザーが携帯電話からフィッシングに強いパスワードレス認証を実行可能にする無料のパスキー ソリューションです。 Authenticator アプリでパスキーを使用する主な利点は次のとおりです。

- パスキーは、簡単かつ大規模にデプロイできます。 その後、モバイル デバイス管理 (MDM) と Bring Your Own Device (BYOD) シナリオの両方で、ユーザーの電話でパスキーを使用できます。
- Authenticator のパスキーは、余計なコストがかからず、ユーザーがどこにでも持ち運ぶことができます。
- Authenticator のパスキーはデバイスバインドであり、パスキーが作成されたデバイスから離れないことを保証します。
- ユーザーは、オープンな WebAuthn 標準に基づく最新のパスキー イノベーションを最新の状態で利用し続けることができます。
- 企業は、認証フローに加えて、Federal Information Processing Standards (FIPS) 140 コンプライアンスなどの他の機能を付加できます。

#### デバイス バインド パスキー

Authenticator アプリのパスキーは、パスキーが作成されたデバイスから分離されないようデバイスにバインドされています。 iOS デバイスでは、Authenticator はセキュリティで保護されたエンクレーブを使用してパスキーを作成します。 Android では、セキュア エレメントをサポートするデバイス上のセキュア エレメントでパスキーを作成するか、高信頼実行環境 (TEE) にフォールバックします。

#### Authenticator でパスキーの構成証明が機能するしくみ

**パスキー (FIDO2)** ポリシーで構成証明が有効になっている場合、Microsoft Entra ID は、パスキーが作成されているセキュリティ キー モデルまたはパスキー プロバイダーの正当性の検証を試みます。 ユーザーが Authenticator にパスキーを登録すると、正規の Microsoft Authenticator アプリが Apple と Google のサービスを使ってパスキーを作成したことが構成証明によって検証されます。 各プラットフォームにおける構成証明の詳細なしくみは次のとおりです。

- iOS: Authenticator 構成証明は、[iOS App Attest サービス](https://developer.apple.com/documentation/devicecheck/preparing-to-use-the-app-attest-service)を使用して Authenticator アプリの正当性を確認してから、パスキーを登録します。
- アンドロイド：

    - Play Integrity 構成証明の場合、Authenticator 構成証明は、[Play Integrity API](https://developer.android.com/google/play/integrity/overview) を使用して Authenticator アプリの正当性を確認してから、パスキーを登録します。
    - キー構成証明の場合、Authenticator 構成証明は、[Android によるキー構成証明](https://developer.android.com/privacy-and-security/security-key-attestation)を使用して、登録対象のパスキーがハードウェアでサポートされていることを確認します。

注

iOS と Android のいずれでも、Authenticator 構成証明は Apple および Google のサービスを利用して、Authenticator アプリの信びょう性を検証します。 サービスの使用量が多いとパスキーの登録に失敗する可能性があり、ユーザーは再試行が必要になることがあります。 Apple および Google のサービスが停止している場合、Authenticator 構成証明は、サービスが復旧するまで構成証明を必要とする登録をブロックします。 Google Play Integrity サービスの状態を監視するには、「[Google Play ステータス ダッシュボード](https://status.play.google.com/)」を参照してください。 iOS App Attest サービスの状態を監視するには、「[システム ステータス](https://developer.apple.com/system-status/)」を参照してください。

構成証明の構成方法について詳しくは、「[Microsoft Entra ID 用に Microsoft Authenticator でパスキーを有効にする方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-enable-authenticator-passkey)」をご覧ください。

### 通知を通じたパスワードレス サインイン

Authenticator アプリから電話によるサインインを有効にしたユーザーには、ユーザー名の入力後にパスワードの入力を求めるプロンプトは表示されず、代わりにアプリに番号を入力するよう求めるメッセージが表示されます。 正しい番号を選択すると、サインイン プロセスは完了です。

[Image: サインインを承認するようユーザーに求めている、ブラウザーでのサインインの例。]

この認証方法によって高レベルのセキュリティが実現し、ユーザーがサインイン時にパスワードを入力する必要がなくなります。

パスワードレスのサインインを開始するには、「[Microsoft Authenticator でパスワードレスのサインインを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-phone)」を参照してください。

### モバイル アプリを介した通知による MFA

Authenticator アプリは、スマートフォンまたはタブレットに通知をプッシュして、アカウントへの不正アクセスを防止したり、不正なトランザクションを停止させたりするのに役立ちます。 ユーザーは通知を確認し、適切であった場合は、 **[確認]** を選択します。 適切でない場合は、 **[拒否]** を選択します。

注

2023 年 8 月以降、未知の場所からのサインインで通知が生成されないのと同様、異常なサインインでは通知が生成されません。 異常なサインインを承認するには、ユーザーは、Microsoft Authenticator を開くか、Outlook などの関連するコンパニオン アプリで Authenticator Lite を開くことができます。 次に、プルダウンして更新するか、**[更新]** をタップして、要求を承認できます。

[Image: サインイン プロセスを完了するために、Web ブラウザーで Authenticator アプリの通知を求める例のスクリーンショット。]

中国では、Android デバイスの*モバイル アプリを介した通知*の方法は機能しません。これは、Google Play のサービス (プッシュ通知など) が同地域でブロックされているためです。 ただし、iOS の通知は機能します。 Android デバイスの場合、それらのユーザーが代替の認証方法を利用できるようにする必要があります。

### モバイル アプリからの確認コード

Authenticator アプリをソフトウェア トークンとして使用して、OATH 確認コードを生成できます。 ユーザー名とパスワードを入力したら、Authenticator アプリから提供されたコードをサインイン インターフェイスに入力します。 検証コードにより、2 番目の形式の認証が行われます。

注

Authenticator によって生成された OATH 検証コードは、証明書ベースの認証ではサポートされていません。

ユーザーは、最大 5 つの OATH ハードウェア トークンまたは Authenticator アプリなどの認証アプリケーションを組み合わせて、いつでも使用できるように構成できます。

### Microsoft Entra 認証に準拠した FIPS 140

[米国国立標準技術研究所 (NIST) 特別出版物 800-63B](https://pages.nist.gov/800-63-3/sp800-63b.html) に記載されているガイドラインに従って、米国政府機関が使用する認証子は、FIPS 140 検証済み暗号化を使用する必要があります。 このガイドラインは、米国政府機関がエグゼクティブ オーダー (EO) 14028 の要件を満たすのに役立ちます。 加えて、このガイドラインは、[規制薬物の電子処方箋 (EPCS)](https://learn.microsoft.com/ja-jp/azure/compliance/offerings/offering-epcs-us) を扱う医療機関などの他の規制対象業界が規制要件を満たすのにも役立ちます。

Federal Information Processing Standards (FIPS) 140 は、情報技術の製品やシステムに含まれる暗号化モジュールに関して最低限のセキュリティ要件を規定する米国政府の規格です。 [暗号化モジュール検証プログラム (CMVP)](https://csrc.nist.gov/Projects/cryptographic-module-validation-program?azure-portal=true) は、FIPS 140 標準に対するテストを維持します。

#### iOS 用 Microsoft Authenticator

バージョン 6.6.8 以降、iOS 用 Microsoft Authenticator は、Apple iOS FIPS 140 準拠デバイスで FIPS 検証済み暗号化用のネイティブ Apple CoreCrypto モジュールを使用します。 フィッシングに強いデバイス バインド パスキー、プッシュ多要素認証 (MFA)、パスワードレス携帯電話サインイン (PSI)、時間ベースのワンタイム パスコード (TOTP) を使用するすべての Microsoft Entra 認証では、FIPS 暗号化が使用されます。

使用され、準拠している iOS デバイスの FIPS 140 検証済み暗号化モジュールの詳細については、 [Apple iOS セキュリティ認定を](https://support.apple.com/guide/certifications/ios-security-certifications-apc3fa917cb49/1/web/1.0)参照してください。

#### Android 用 Microsoft Authenticator

Android 用 Microsoft Authenticator のバージョン 6.2409.6094 以降では、パスキーを含む Microsoft Entra ID のすべての認証が FIPS 準拠と見なされます。 Authenticator は wolfSSL Inc. 暗号化モジュールを使用して、Android デバイスで FIPS 140 セキュリティ レベル 1 のコンプライアンスを実現します。 認定の詳細については、「 [暗号化モジュール検証プログラム](https://csrc.nist.gov/projects/cryptographic-module-validation-program/certificate/4718)」を参照してください。

### セキュリティ情報での Microsoft Authenticator 登録の種類の決定

ユーザーは、[セキュリティ情報](https://mysignins.microsoft.com/security-info) (次のセクションの URL を参照) にアクセスするか、MyAccount からセキュリティ情報を選択して、Microsoft Authenticator の登録を管理および追加できます。 Microsoft Authenticator 登録がパスワードレスの携帯電話によるサインインと MFA のどちらであるかを区別するために、専用のアイコンが使用されます。

| Authenticator 登録の種類 | アイコン |
| --- | --- |
| Microsoft Authenticator: パスワードレスの電話によるサインイン | [Image: Microsoft Authenticator のパスワードなしのサインインに対応] |
| Microsoft Authenticator: (通知/コード) | [Image: Microsoft Authenticator MFA に対応] |

#### SecurityInfo のリンク

| クラウド | セキュリティ情報 URL |
| --- | --- |
| Azure Commercial (Government Community Cloud (GCC) を含む) | https://aka.ms/MySecurityInfo |
| 米国政府向け Azure (GCC High と DoD を含む) | https://aka.ms/MySecurityInfo-us |

### Authenticator の更新プログラム

Microsoft では、高いレベルのセキュリティを維持するため、Authenticator を継続的に更新しています。 ユーザーが可能な限り最高のエクスペリエンスを得られるよう、Authenticator アプリを継続的に更新することをお勧めします。 重要なセキュリティ更新プログラムがある場合、最新でないバージョンのアプリが動作せず、ユーザーが認証を完了できなくなる可能性があります。 サポートされていないバージョンのアプリを使っているユーザーは、サインインを続ける前に最新バージョンにアップグレードするよう求められます。

さらに、組織が高いセキュリティ水準を維持できるよう、Microsoft は古いバージョンの Authenticator アプリを定期的に廃止しています。 ユーザーのデバイスが最新バージョンの Microsoft Authenticator をサポートしていない場合、ユーザーはアプリでサインインできません。 MFA を完了するには、Microsoft Authenticator で OATH 検証コードを使用してサインインすることをお勧めします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-authentication-default-enablement"} -->
## Microsoft Entra ID での認証方法の保護 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-default-enablement
- Service: entra-id / authentication
- Article date: 2026-05-04
- Summary: Microsoft Entra ID が、既定の保護と登録キャンペーン向けの Microsoft 管理設定を使用して認証方法をどのように保護するかについて説明します。

Microsoft Entra IDは、増加する攻撃から顧客を保護するためのセキュリティ機能を追加および改善します。 新しい攻撃ベクトルが出現すると、Microsoft Entra IDは既定で保護を有効にして対応し、お客様が新たなセキュリティの脅威に先んじるのに役立ちます。

たとえば、MFA 疲労攻撃の増加を受け、Microsoft ではお客様が[ユーザーを防御](https://techcommunity.microsoft.com/t5/microsoft-entra-azure-ad-blog/defend-your-users-from-mfa-fatigue-attacks/ba-p/2365677)する方法を推奨しています。 誤った多要素認証 (MFA) の承認を防ぐには、 [番号照合](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-number-match)を有効にします。 その結果、数値照合の既定の動作は、すべてのMicrosoft Authenticator ユーザーに対して明示的に **Enabled** になります。 番号一致などの新しいセキュリティ機能の詳細については、ブログ記事「[Advanced Microsoft Authenticator セキュリティ機能が一般公開されました!](https://techcommunity.microsoft.com/t5/microsoft-entra-azure-ad-blog/advanced-microsoft-authenticator-security-features-are-now/ba-p/2365673)。

セキュリティ機能の保護を既定で有効にするには、次の 2 つの方法があります。

- セキュリティ機能がリリースされた後、お客様は Microsoft Entra 管理センターまたは Graph API を使用して、自分のスケジュールに従って変更をテストおよびロールアウトできます。 新たな攻撃ベクトルから保護するために、Microsoft Entra ID では、特定の日付にすべてのテナントで既定で保護を有効にすることがあり、これを無効化するオプションはありません。 Microsoftは、顧客が準備する時間を与えるために、既定の保護をかなり前もってスケジュールします。 Microsoft が既定で保護をスケジュールしている場合、お客様はオプトアウトできません。
- 保護は**Microsoft管理**できます。つまり、Microsoft Entra IDは、現在のセキュリティ上の脅威の状況に基づいて保護を有効または無効にすることができます。 お客様は、Microsoft による保護の管理を許可するかどうかを選択できます。 **Microsoftマネージド**から明示的に**Enabled**または**Disabled**に変更できます。

手記

重要なセキュリティ機能のみが既定で保護を有効にします。

### Microsoft Entra ID で有効になっている既定の保護

番号照合は、現在すべてのテナントにおいて、Microsoft Authenticator のプッシュ通知に関して省略可能な認証方法の一つであり、その保護の良い例です。 お客様は、ユーザーとグループに対して Microsoft Authenticator でプッシュ通知の番号照合を有効にするか、無効のままにすることができます。 Microsoft Authenticator でのパスワードレス通知の既定の動作は、既に番号照合であり、ユーザーはオプトアウトできません。

MFA 疲労攻撃が増加するにつれて、サインイン セキュリティには数の一致が不可欠です。 その結果、Microsoft Authenticator のプッシュ通知の既定の動作が変更されます。

### Microsoft によって管理される設定

IT 管理者は、認証方法ポリシー設定を Enabled または Disabledのいずれかに構成するだけでなく、認証方法ポリシーの一部の設定を Microsoft マネージドするように構成できます。 **Microsoftマネージド**設定を使用するとMicrosoft Entra ID機能を自動的に有効または無効にすることができます。

Microsoft Entra ID にこの設定の管理を任せるオプションは、組織が Microsoft に機能の既定値を管理させるための便利な方法です。 組織は、Microsoftを信頼して機能を有効にする必要があるかどうかを判断することで、セキュリティ体制を改善できます。 **Microsoft が管理する** (Graph API では *default* という名前) として設定を構成することで、IT 管理者は Microsoft を信頼して、明示的に無効にしていないセキュリティ機能を有効にできます。

たとえば、管理者はプッシュ通知で [場所とアプリケーション名の](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-additional-context) を有効にして、ユーザーが Microsoft Authenticator で MFA 要求を承認するときにコンテキストを増やすことができます。 追加のコンテキストは、明示的に無効にすることや、**Microsoft マネージド**として設定することもできます。 現在、場所とアプリケーション名の **Microsoft による管理**構成は **[無効]** になっています。これにより、管理者が Microsoft Entra ID で設定を管理することを選択した環境では、このオプションが実質的に無効になります。

セキュリティ上の脅威の状況が時間の経過と同時に変化するにつれて、Microsoft は場所とアプリケーション名の **Microsoft マネージド** 構成を **Enabled**に変更できます。 Microsoftに依存してセキュリティ体制を改善したいお客様にとって、セキュリティ機能を Microsoft マネージドセキュリティの脅威を先取りする簡単な方法です。 Microsoftは、現在の脅威の状況に基づいて最適な構成を決定します。

次の表に、Microsoft マネージドに設定できる各設定と、その設定が既定で有効か無効かを示します。

| 設定 | 設定 |
| --- | --- |
| [登録キャンペーン](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-registration-campaign) | 有効 |
| [Microsoft Authenticator 通知の場所](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-additional-context) | いいえ |
| [Microsoft Authenticator 通知のアプリケーション名](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-additional-context) | いいえ |
| [システム優先認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-system-preferred-authentication) | 有効 |
| [オーセンティケーター・ライト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-authenticator-lite) | 有効 |
| [疑わしいアクティビティの](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-mfasettings#report-suspicious-activity) を報告する | いいえ |

#### Microsoft 管理対象の登録キャンペーン

登録キャンペーンが **Microsoft managed** に設定されている場合、Microsoftはベスト プラクティスに基づいてテナントの最適なキャンペーン構成を決定します。 Microsoftは、テナント設定とスコープ付きユーザーを評価して、対象となる認証方法を決定します。

- テナントに、すべての種類のパスキー（同期パスキーとデバイス バインド パスキーの両方）を許可するパスキー（FIDO2）プロファイルに含まれる、登録キャンペーンの対象ユーザーがいる場合、対象メソッドはパスキーに変更されます。
- その条件を満たすユーザーがいない場合、対象のメソッドはMicrosoft Authenticatorのままです。

パスキー (FIDO2) が有効で、アクティブな登録キャンペーンが **Microsoft managed** に設定されているテナントでは、Microsoft がテナントに対する変更を順次展開するのに伴い、キャンペーン設定が段階的に更新されます。

手記

登録キャンペーンでは、一度に 1 つの認証方法のみを対象にすることができます。 テナントは、Microsoft Authenticatorとパスキーの両方に対してキャンペーンを同時に実行することはできません。

次のMicrosoftマネージド登録キャンペーンの設定が更新されます。

| 設定 | 前の値 | 新しい値 |
| --- | --- | --- |
| **対象となる認証方法** | Microsoft Authenticator | パスキー (FIDO2) |
| **スヌーズ可能日数** | 3 日間 | 1 日（設定不可） |
| **スヌーズ回数の上限** | 有効 | 無効 (構成不可) |
| **ユーザー のターゲット設定** | 音声通話またはテキスト メッセージユーザー | すべての多要素認証 (MFA) 対応ユーザー |

これらの変更が有効になった後、多要素認証が完了した後、対象ユーザーはサインイン中にパスキー登録ナッジを受け取ります。 テナントがパスキー (FIDO2) ポリシーの特定の AAGUID (Authenticator Attestation GUID) を対象としている場合、対象の認証方法は、Microsoftマネージド モードのパスキーに更新されません。 引き続き **[有効]** に切り替えて、パスキーのターゲット設定を手動で構成できます。

手記

Microsoft管理設定が組織のニーズを満たしていない場合は、登録キャンペーンの状態を **Enabled** に切り替えてすべての設定を手動で構成するか、**Disabled** を切り替えてキャンペーンを無効にすることができます。 たとえば、パスキーを有効にしたいが、登録キャンペーンでパスキーをターゲットにしたくない場合は、状態を **Enabled** に切り替え、Microsoft Authenticatorをターゲットにします。 詳細については、「 [登録キャンペーンを実行する」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-registration-campaign)参照してください。

脅威ベクトルの変化に応じて、Microsoft Entra ID は、**のリリース ノート** や、[Tech Community](https://learn.microsoft.com/ja-jp/entra/fundamentals/whats-new)などのよく読まれるフォーラムで、[Microsoft マネージド](https://techcommunity.microsoft.com/) 設定に対する既定の保護を発表できます。

詳細については、ブログ記事「 [It's Time to Hang Up on Phone Transports for Authentication](https://techcommunity.microsoft.com/t5/microsoft-entra-azure-ad-blog/it-s-time-to-hang-up-on-phone-transports-for-authentication/ba-p/1751752) 」を参照してください。この記事では、テキスト メッセージや音声通話の使用からの移行について説明しています。 Microsoftマネージド登録キャンペーンは、ユーザーがMicrosoft Authenticatorやパスキーなどの先進認証方法を設定するのに役立ちます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-authentication-external-method-provider"} -->
## Microsoft Entra 外部 MFA メソッド プロバイダー リファレンス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-external-method-provider
- Service: entra-id / authentication
- Article date: 2026-02-23
- Summary: Microsoft Entra 多要素認証用に外部 MFA メソッド プロバイダーを構成する方法について説明します。

この記事では、外部多要素認証 (MFA) プロバイダーが Microsoft Entra MFA に接続する方法について説明します。

ユーザーがサインインすると、テナント ポリシーが評価されます。 認証要件は、ユーザーがアクセスしようとするリソースに基づいて決定されます。

パラメーターによっては、サインインに複数のポリシーが適用される場合があります。 これらのパラメーターには、ユーザーとグループ、アプリケーション、プラットフォーム、サインイン リスク レベルなどが含まれます。

認証要件に基づいて、ユーザーは MFA 要件を満たすために別の要素でサインインする必要がある場合があります。 2 番目の因子の型は、最初の因子の型を補完する必要があります。

テナント管理者は、Microsoft Entra ID に外部 MFA メソッドを追加します。 テナントで MFA に外部 MFA メソッドが必要な場合、Microsoft Entra ID が両方を検証した後、サインインは Microsoft Entra MFA 要件を満たしていると見なされます。

- 最初の認証要素は Microsoft Entra ID で完了しました。
- 2 番目の要素は外部 MFA メソッドで完了した。

この検証は、次の 2 つ以上の種類のメソッドの MFA 要件を満たします。

- 知っていること (知識)
- 所有しているもの (所持品)
- 自分そのもの (固有性)

外部 MFA メソッドは、OpenID Connect (OIDC) の上に実装されます。 この実装では、外部 MFA メソッドを実装するために、少なくとも 3 つの公開エンドポイントが必要です。

- プロバイダー メタデータの検出に従った OIDC ディスカバリーエンドポイント
- 有効な OIDC 認証エンドポイント
- プロバイダーの公開証明書が発行される URL

外部 MFA メソッドでのサインインのしくみを次に示します。

1. ユーザーは、パスワードなどの最初の要素を使って、Microsoft Entra ID で保護されているアプリケーションにサインインしようとします。
2. Microsoft Entra ID は、別の要素を満たす必要があることを判断します (たとえば、条件付きアクセス ポリシーで MFA が必要な場合)。
3. ユーザーは、2 番目の要素として外部 MFA メソッドを選択します。
4. Microsoft Entra ID は、ユーザーのブラウザー セッションを外部 MFA メソッドの URL にリダイレクトします。

    この URL は、管理者が外部 MFA メソッドを作成したときにプロビジョニングした検出 URL から検出されます。

    アプリケーションは、ユーザーとテナントを識別する情報を含む、期限切れまたは期限切れ間近のトークンを提供します。
5. 外部 MFA プロバイダーは、トークンが Microsoft Entra ID から取得されたことを検証し、トークンの内容を確認します。
6. 外部 MFA プロバイダーが Microsoft Graph を呼び出して、ユーザーに関する追加情報をフェッチする場合があります。
7. 外部 MFA プロバイダーは、資格情報を使用してユーザーを認証するなど、必要と思われるアクションを実行します。
8. 外部 MFA プロバイダーは、必要なすべての要求を含む有効なトークンを使用して、ユーザーを Microsoft Entra ID にリダイレクトします。
9. Microsoft Entra ID は、トークンの署名が構成済みの外部 MFA プロバイダーから取得されたことを検証し、トークンの内容を確認します。
10. Microsoft Entra ID は、要件に照らしてトークンを検証します。
11. 検証が成功した場合は、ユーザーが MFA 要件を満たしたことを意味します。 ユーザーは他のポリシー要件も満たさなければならない場合があります。

### Microsoft Entra ID を使用して新しい外部 MFA プロバイダーを構成する

`id_token_hint`を発行するには、外部 MFA メソッドに統合を表すアプリケーションが必要です。 アプリケーションは、次の 2 つの方法で作成できます。

- 外部プロバイダーを使用する各テナント。
- 1 つのマルチテナント アプリケーションとして。 テナントの統合を有効にするには、特権ロール管理者が同意を付与する必要があります。

マルチテナント アプリケーションを使用すると、各テナントで構成ミスが発生する可能性が低くなります。 プロバイダーは、各テナントに変更を要求するのではなく、メタデータ (たとえば、1 か所で応答 URL) を変更することもできます。

マルチテナント アプリケーションを構成するには、プロバイダー管理者はまず次のことを行う必要があります。

1. Microsoft Entra ID テナントを作成します (まだ存在しない場合)。
2. テナントにアプリケーションを登録します。
3. アプリケーションの [ **サポートされているアカウントの種類**] で、 **任意の組織ディレクトリ (任意の Microsoft Entra ID テナント - マルチテナント) の [アカウント**] を選択します。
4. Microsoft Graph に対する委任されたアクセス許可 `openid` および `profile` を追加します。
5. このアプリケーションではスコープを公開しないでください。
6. 外部 ID プロバイダーの有効な `authorization_endpoint` URL を応答 URL としてそのアプリケーションに追加します。

    注

    アプリケーションの登録で、プロバイダーの検出ドキュメントに指定された `authorization_endpoint` 値をリダイレクト URL として追加します。 それ以外の場合は、"ENTRA IDSTS50161: 外部クレーム プロバイダーの承認 URL を検証できませんでした!" というエラーが表示されます。

アプリケーションの登録プロセスでは、いくつかのプロパティを持つアプリケーションが作成されます。 このシナリオでは、これらのプロパティが必要です。

| プロパティ | 説明 |
| --- | --- |
| オブジェクト ID | プロバイダーは、Microsoft Graph でオブジェクト ID を使って、アプリケーション情報のクエリを実行できます。 プロバイダーはオブジェクト ID を使って、プログラムでアプリケーション情報を取得および編集できます。 |
| アプリケーション ID | プロバイダーは、アプリケーション ID をアプリケーションのクライアント ID として使用できます。 |
| ホーム ページ URL | プロバイダーのホーム ページ URL は何にも使用されませんが、アプリケーションを登録するために必要です。 |
| 返信 URL | プロバイダーの有効なリダイレクト URL。 1 つは、プロバイダーのテナントに設定されたプロバイダー ホスト URL と一致する必要があります。 登録されている応答 URL の 1 つは、OIDC Discovery を使用してホスト URL に対して Microsoft Entra ID が取得する `authorization_endpoint` 値のプレフィックスと一致する必要があります。 |

統合をサポートするためのもう 1 つの有効なモデルは、テナントごとにアプリケーションを使用することです。 シングルテナント登録を使う場合、テナント管理者は、シングルテナント アプリケーション用に上記の表のプロパティを使ってアプリケーションの登録を作成する必要があります。

注

外部 MFA メソッドを使用するテナント内のアプリケーションに対する管理者の同意が必要です。 同意を付与しない場合、管理者が外部 MFA メソッドを使用しようとすると、"AADSTS900491: サービス プリンシパル &lt;アプリ ID&gt; が見つかりません" というエラーが表示されます。

#### 省略可能な要求の設定

プロバイダーは、[`id_token`の省略可能な要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)を使用して、より多くの要求を構成できます。

注

アプリケーションの作成方法に関係なく、プロバイダーはクラウド環境ごとにオプションのクレームを構成する必要があります。 マルチテナント アプリケーションがグローバル Azure と米国政府向け Azure に使われている場合、各クラウド環境には別々のアプリケーションとアプリケーション ID が必要です。

### Microsoft Entra ID への外部 MFA メソッドの追加

外部 ID プロバイダー情報は、各テナントの認証方法ポリシーに格納されます。 プロバイダー情報は、 `externalAuthenticationMethodConfiguration`型の認証方法として格納されます。

各プロバイダーには、ポリシーのリスト オブジェクトに 1 つのエントリが含まれます。 各エントリは次の内容を持つ必要があります。

- メソッドが有効になっている場合。
- メソッドを使用できる含まれるグループ。
- メソッドを使用できない除外されたグループ。

ユーザー サインインの MFA 要件を設定するために、条件付きアクセス管理者ロールを持つユーザーは、MFA 付与を要求するポリシーを作成できます。 外部 MFA メソッドは、現在、認証の強みではサポートされていません。

Microsoft Entra 管理センターで [外部 MFA メソッドを追加する方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-external-method-manage) の詳細について説明します。

### Microsoft Entra ID とプロバイダーのやり取り

次のセクションでは、プロバイダーの要件について説明し、Microsoft Entra ID がプロバイダーとやり取りする方法の例を示します。

#### プロバイダー メタデータの検出

外部 ID プロバイダーは [、OIDC 検出エンドポイント](http://openid.net/specs/openid-connect-discovery-1_0.html#ProviderConfig)を提供する必要があります。 このエンドポイントは、より多くの構成データを取得するために使われます。

探索 *URL は*、`https`スキームを使用し、*必ず*`/.well-known/openid-configuration`で終わらなければなりません。 このセグメントの後に追加のパス セグメント、クエリ文字列、またはフラグメントを含めることはできません。 完全な検出 URL は、外部 MFA メソッドの作成時に構成する探索 URL に含まれている必要があります。

エンドポイントは、そこでホストされているプロバイダー メタデータ [JSON ドキュメント](http://openid.net/specs/openid-connect-discovery-1_0.html#ProviderMetadata) を返します。 エンドポイントは、有効なコンテンツ長ヘッダーも返す必要があります。 メタデータ ドキュメントは*、OpenID Connect Discovery 1.0* (errata セット 2 を組み込む) に準拠し、必要なすべての OIDC メタデータ フィールドを含める[必要があります](http://openid.net/specs/openid-connect-discovery-1_0.html)。

プロバイダーのメタデータには、次の表に示すデータが含まれている必要があります。 この機能拡張シナリオでは、これらの値が必要です。 JSON メタデータ ドキュメントには、より多くの情報が含まれている場合があります。

プロバイダー メタデータの値を含む OIDC ドキュメントについては、「 [プロバイダー](http://openid.net/specs/openid-connect-discovery-1_0.html#ProviderMetadata) メタデータ」を参照してください。

| メタデータ値 | 価値 | コメント |
| --- | --- | --- |
| `Issuer` |  | HTTPS URL である必要があります。発行者の値は、構成された発行者、探索ドキュメントの発行者値、およびプロバイダーのサービスによって発行されたトークンの要求と文字単位で一致する必要があります。*発行者には*ポートまたはパス セグメントを含めることができますが、クエリ パラメーターやフラグメント識別子を含*めてはなりません*。 |
| `authorization_endpoint` |  | Microsoft Entra ID が承認のために通信するエンドポイント。 このエンドポイントは、許可されたアプリケーションの応答 URL の 1 つとして存在する必要があります。 |
| `jwks_uri` |  | プロバイダーによって発行された署名を確認するために必要な公開キーを Microsoft Entra ID が検索できる場所。 `jwks_uri`は HTTPS *エンドポイントであり、*クエリ パラメーターやフラグメント識別子を含*めてはなりません*。指定されたキーの X.509 表現を提供するには、JSON Web Key (JWK) `x5c` パラメーターが存在する必要があります。 |
| `scopes_supported` | `openid` | その他の値も含まれる場合がありますが、必須ではありません。 |
| `response_types_supported` | `id_token` | その他の値も含まれる場合がありますが、必須ではありません。 |
| `subject_types_supported` |  |  |
| `id_token_signing_alg_values_supported` |  | Microsoft は RS256 をサポートしています。 |
| `claim_types_supported` | `normal` | このプロパティは省略可能ですが、存在する場合は、 `normal` 値を含める必要があります。 その他の値も含まれる場合があります。 |

```json
https://customcaserver.azurewebsites.net/v2.0/.well-known/openid-configuration
{
  "authorization_endpoint": "https://customcaserver.azurewebsites.net/api/Authorize",
  "claims_supported": [
    "email"
  ],
  "grant_types_supported": [
    "implicit"
  ],
  "id_token_signing_alg_values_supported": [
    "RS256"
  ],
  "issuer": "https://customcaserver.azurewebsites.net/v2.0",
  "jwks_uri": "https://customcaserver.azurewebsites.net/.well-known/jwks",
  "response_modes_supported": [
    "form_post"
  ],
  "response_types_supported": [
    "id_token"
  ],
  "scopes_supported": [
    "openid"
  ],
  "SigningKeys": [],
  "subject_types_supported": [
    "public"
  ]
}

https://customcaserver.azurewebsites.net/.well-known/jwks
{
  "keys": [
    {
      "kty": "RSA",
      "use": "sig",
      "kid": "A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0u",
      "x5t": "A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0u",
      "n": "jq277LRoE6WKM0awT3b...vt8J6MZvmgboVB9S5CMQ",
      "e": "AQAB",
      "x5c": [
        "cZa3jz...Wo0rzA="
      ]
    }
  ]
}
```

注

指定されたキーの X.509 表現を提供するには、JWK `x5c` パラメーターが存在する必要があります。

##### 検出 URL と発行者の例

次の例は、この統合の有効な検出 URL と無効な検出 URL と発行者の組み合わせを示しています。

###### 有効な検出 URL と発行者のペア

- 検出URL: `https://example.com/.well-known/openid-configuration`発行者: `https://example.com`
- 検出URL: `https://example.com:8443/.well-known/openid-configuration`発行者: `https://example.com:8443`
- 検出URL: `https://example.com/tenant1/.well-known/openid-configuration`発行者: `https://example.com/tenant1`

###### 無効な検出 URL と発行者の例

- 検出URL: `https://example.com/.well-known/openid-configuration`発行者: `https://example.com:443/` (発行者で明示的に追加された既定の HTTPS ポート)。
- 検出URL: `https://example.com:443/.well-known/openid-configuration`発行者: `https://example.com/` (ポートの不一致)。
- 検出URL: `https://example.com/.well-known/openid-configuration?client_id=0oasxuxkghOniBjlQ697`発行者: `https://example.com` (探索 URL にクエリ文字列を含めることはできません)。

##### プロバイダー メタデータのキャッシュ

パフォーマンスを向上させるために、Microsoft Entra ID は、キーを含め、プロバイダーが返すメタデータをキャッシュします。 プロバイダーメタデータキャッシュは、Microsoft Entra ID が外部 ID プロバイダーと通信するたびに検出呼び出しを防ぎます。

このキャッシュは 24 時間ごとに更新されます。 プロバイダーは、次の手順に従ってキーをロール オーバーすることをお勧めします。

1. で**既存の証明書**と`jwks_uri`を発行します。
2. Microsoft Entra ID キャッシュが更新、期限切れ、または更新されるまで (2 日ごとに) **既存の証明書** でサインインし続けます。
3. **New Cert** を使用したサインインに切り替えます。

キーのロールオーバーのスケジュールは公開されません。 依存サービスは、即時ロールオーバーと定期ロールオーバーの両方を処理できるように準備する必要があります。 [`azure-activedirectory-identitymodel-extensions-for-dotnet`](https://github.com/AzureAD/azure-activedirectory-identitymodel-extensions-for-dotnet)のように、この目的のために構築された専用ライブラリを使用することをお勧めします。 詳細については、「 [Microsoft Entra ID での署名キーのロールオーバー」を](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/active-directory-signing-key-rollover)参照してください。

##### Microsoft Entra ID メタデータの検出

プロバイダーは、Microsoft Entra ID によって発行されたトークンを検証するために、Microsoft Entra ID の公開キーを取得する必要もあります。

Microsoft Entra ID メタデータの検出エンドポイント。

- グローバル Azure: `https://login.microsoftonline.com/common/v2.0/.well-known/openid-configuration`
- 米国政府向け Azure: `https://login.microsoftonline.us/common/v2.0/.well-known/openid-configuration`
- 21Vianet によって運営される Microsoft Azure: `https://login.partner.microsoftonline.cn/common/v2.0/.well-known/openid-configuration`

トークンの公開キー識別子 ([JSON Web Signature (JWS) の "kid"](https://tools.ietf.org/html/rfc7515#section-4.1.4)) を使用して、 `jwks_uri` プロパティから取得したキーを Microsoft Entra ID トークン署名の検証に使用する必要があるかどうかを判断できます。

##### Microsoft Entra ID によって発行されたトークンの検証

Microsoft Entra ID によって発行されたトークンを検証する方法については、「 [ID トークンの検証」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens#validating-an-id_token)参照してください。 検出メタデータの使用者に特別な手順はありません。

トークン検証の詳細については、Microsoft の [トークン検証ライブラリ](https://github.com/AzureAD/azure-activedirectory-identitymodel-extensions-for-dotnet/wiki)を参照してください。 ソース コードを参照して、これらの詳細を確認することもできます。 サンプルについては、 [Azure のサンプル](https://github.com/Azure-Samples/active-directory-dotnet-webapi-manual-jwt-validation)を参照してください。

検証が成功したら、要求ペイロードを操作して、ユーザーとそのテナントに関する詳細を取得できます。

注

`id_token_hint`値を検証して、それが Microsoft テナントからのものであり、統合を表していることを確認することが重要です。 `id_token_hint`値は、特に署名、発行者、対象ユーザー、およびその他の要求値を完全に検証する必要があります。

#### 外部 ID プロバイダーへの Microsoft Entra ID 呼び出し

Microsoft Entra ID は [、OIDC 暗黙的フロー](http://openid.net/specs/openid-connect-core-1_0.html#ImplicitFlowAuth) を使用して外部 ID プロバイダーと通信します。 このフローを使用する場合、プロバイダーとの通信は、プロバイダーの承認エンドポイントのみを使用して行われます。

Microsoft Entra ID が要求を行っているユーザーについてプロバイダーに通知するために、Microsoft Entra ID は [`id_token_hint`](http://openid.net/specs/openid-connect-core-1_0.html#AuthRequest) パラメーターを介してトークンを渡します。

この呼び出しは、パラメーターの大きなリストがプロバイダーに渡されるため、 `POST` 要求によって行われます。 大きなリストでは、 `GET` 要求の長さを制限するブラウザーを使用できません。

認証要求パラメーターを次の表に示します。

注

プロバイダーは、次の表に示す場合を除き、要求内の他のパラメーターを無視する必要があります。

| 認証クエリ パラメーター | 価値 | 説明 |
| --- | --- | --- |
| `scope` | `openid` |  |
| `response_type` | `Id_token` | 暗黙的なフローに使われる値。 |
| `response_mode` | `form_post` | フォーム `POST` を使用して、大きな URL の問題を回避します。 すべてのパラメーターを要求本文で送信することが想定されています。 |
| `client_id` |  | 外部 ID プロバイダーによって Microsoft Entra ID に指定されたクライアント ID ( `ABCD`など)。 詳細については、「 外部 MFA メソッドの説明」を参照してください。 |
| `redirect_uri` |  | 外部 ID プロバイダーが応答を送信するリダイレクト用の Uniform Resource Identifier (URI) (`id_token_hint`)。 この表の後の 例 を参照してください。 |
| `nonce` |  | Microsoft Entra ID によって生成されるランダムな文字列。 セッション ID にすることもできます。 指定した場合は、Microsoft Entra ID への応答で返す必要があります。 |
| `state` |  | 渡された場合、プロバイダーは応答で `state` を返す必要があります。 Microsoft Entra ID は、呼び出しに関するコンテキストを保持するために `state` を使用します。 |
| `id_token_hint` |  | Microsoft Entra ID がユーザーに対して発行し、プロバイダーの利益のために渡すトークン。 |
| `claims` |  | 要求されたクレームを含む JSON BLOB。 このパラメーターの形式の詳細については、OIDC ドキュメントの [要求要求パラメーター](http://openid.net/specs/openid-connect-core-1_0.html#ClaimsParameter) と、この表の後の 例 を参照してください。 |
| `client-request-id` | GUID 値 | プロバイダーはこの値をログして、問題のトラブルシューティングに役立てることができます。 |

##### リダイレクト URI の例

リダイレクト URI は、プロバイダーのオフバンドに登録する必要があります。 送信できるリダイレクト URI は次のとおりです。

- グローバル Azure: `https://login.microsoftonline.com/common/federation/externalauthprovider`
- 米国政府向け Azure: `https://login.microsoftonline.us/common/federation/externalauthprovider`
- 21Vianet によって運営される Microsoft Azure: `https://login.partner.microsoftonline.cn/common/federation/externalauthprovider`

##### MFA を満たす外部 MFA メソッドの例

外部 MFA メソッドが MFA 要件を満たす例を次に示します。 この例は、Microsoft Entra ID で想定されるクレームが何かをプロバイダーが把握するのに役立ちます。

Microsoft Entra ID では、 `acr` 値と `amr` 値の組み合わせを使用して、次のことが検証されます。

- 2 番目の要素に使用される認証方法は、MFA 要件を満たします。
- 認証方法は、Microsoft Entra ID へのサインインの最初の要素を完了するために使用される方法とは異なる *種類* です。

```json
{
  "id_token": {
    "acr": {
      "essential": true,
      "values":["possessionorinherence"]
    },
    "amr": {
      "essential": true,
      "values": ["face", "fido", "fpt", "hwk", "iris", "otp", "pop", "retina", "sc", "sms", "swk", "tel", "vbm"]
    }
  }
}
```

##### 既定のid\_token\_hintクレーム

このセクションでは、プロバイダーに対して行われた要求で `id_token_hint` として渡されるトークンの必須コンテンツについて説明します。 トークンには、次の表に示すよりも多くの要求が含まれている場合があります。

| 要求 | 価値 | 説明 |
| --- | --- | --- |
| `iss` |  | トークンを構築して返すセキュリティ トークン サービス (STS)、ユーザーが認証された Microsoft Entra ID テナントを特定します。アプリにサインインできるテナントのセットを制限するために、該当する場合は要求のGUID部分を使用してください。発行者は、ユーザーがサインインしたテナントの OIDC Discovery JSON メタデータの発行者 URL と一致する必要があります。 |
| `aud` |  | 対象ユーザーは、Microsoft Entra ID の外部 ID プロバイダーのクライアント ID に設定する必要があります。 |
| `exp` |  | 有効期限は、発行時間の少し後に期限切れになるように設定されており、時間のずれの問題を回避するのに十分です。 このトークンは認証を目的としていないため、その有効性が要求よりも大幅に長く続く理由はありません。 |
| `iat` |  | 通常どおりに発行時刻を設定します。 |
| `tid` |  | テナント ID はプロバイダーにテナントをアドバタイズするためのものです。 これは、ユーザーが属している Microsoft Entra ID テナントを表します。 |
| `oid` |  | Microsoft ID プラットフォーム内のオブジェクトの不変の識別子。 この場合はユーザー アカウントです。 また、認可チェックを安全に実行するために使うことや、データベースのテーブルのキーとして使うことができます。この ID は、アプリケーション全体でユーザーを一意に識別します。 同じユーザーにサインインする 2 つの異なるアプリケーションは、 `oid` 要求で同じ値を受け取ります。 したがって、 `oid` 要求は、Microsoft Graph などの Microsoft オンライン サービスに対するクエリで使用できます。 |
| `preferred_username` |  | トークンのサブジェクトを識別する人間が判読できる値を提供します。 この値はテナント内で一意であることが保証されておらず、表示のみを目的としています。 |
| `sub` |  | 発行者のユーザーのサブジェクト識別子。 トークンが情報を主張する対象（アプリケーションのユーザーなど）。この値は変更不可で、再割り当ても再利用もできません。 トークンを使用してリソースにアクセスする場合など、承認チェックを安全に実行するために使用できます。 データベース テーブルのキーとして使用できます。サブジェクトは Microsoft Entra ID が発行するトークン内に常に存在するため、汎用の認可システムではこの値を使うことをお勧めします。 ただし、サブジェクトはペアワイズ識別子であり、特定のアプリケーション ID に対して一意です。*そのため、1 人のユーザーが 2 つの異なるクライアント ID を使用して 2 つの異なるアプリケーションにサインインした場合、それらのアプリケーションはサブジェクト要求に対して 2 つの異なる値を受け取ります*。アーキテクチャとプライバシーの要件によっては、この結果が必要な場合と望まない場合があります。`oid`要求も参照してください (テナント内のアプリ間で同じままです)。 |

トークンがヒント以外に使用されないようにするために、期限切れの状態で発行されます。 トークンは署名されており、公開された Microsoft Entra ID 検出メタデータを使用して検証できます。

##### Microsoft Entra ID からのオプションのクレーム

プロバイダーが Microsoft Entra ID からの省略可能な要求を必要とする場合は、`id_token`、`given_name`、`family_name`、`preferred_username`の`upn`に対して次の省略可能な要求を構成できます。 詳細については、「 [省略可能な要求](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/optional-claims)」を参照してください。

##### クレームの推奨される使用法

`oid`と`tid`要求を使用して、プロバイダー側のアカウントを Azure のアカウントに関連付けすることをお勧めします。 これら 2 つのクレームは、テナント内のアカウントに対して一意であることが保証されています。

##### id\_token\_hintの例

ディレクトリ メンバーの `id_token_hint` の例を次に示します。

```json
{
  "typ": "JWT",
  "alg": "RS256",
  "kid": "C2dE3fH4iJ5kL6mN7oP8qR9sT0uV1w"
}.{
  "ver": "2.0",
  "iss": "https://login.microsoftonline.com/aaaabbbb-0000-cccc-1111-dddd2222eeee/v2.0",
  "sub": "mBfcvuhSHkDWVgV72x2ruIYdSsPSvcj2R0qfc6mGEAA",
  "aud": "00001111-aaaa-2222-bbbb-3333cccc4444",
  "exp": 1536093790,
  "iat": 1536093791,
  "nbf": 1536093791,
  "name": "Test User 2",
  "preferred_username": "testuser2@contoso.com"
  "oid": "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb",
  "tid": "aaaabbbb-0000-cccc-1111-dddd2222eeee"
  }.

```

テナント内のゲスト ユーザーの `id_token_hint` の例を次に示します。

```json
{
  "typ": "JWT",
  "alg": "RS256",
  "kid": "C2dE3fH4iJ5kL6mN7oP8qR9sT0uV1w"
}.{
  "ver": "2.0",
  "iss": "https://login.microsoftonline.com/9122040d-6c67-4c5b-b112-36a304b66dad/v2.0",
  "sub": "mBfcvuhSHkDWVgV72x2ruIYdSsPSvcj2R0qfc6mGEAA",
  "aud": "00001111-aaaa-2222-bbbb-3333cccc4444",
  "exp": 1536093790,
  "iat": 1536093791,
  "nbf": 1536093791,
  "name": "External Test User (Hotmail)",
  "preferred_username": "externaltestuser@hotmail.com",
  "oid": "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb",
  "tid": "aaaabbbb-0000-cccc-1111-dddd2222eeee"
  }.

```

##### 外部 ID プロバイダーに推奨される操作

外部 ID プロバイダーは、次の項目を完了することをお勧めします。 この一覧はすべてを網羅しているわけではないため、プロバイダーは必要に応じて他の検証手順を完了する必要があります。

- 要求に基づいて:

    - `redirect_uri`の説明に従って、が公開されていることを確認します。
    - 構成された探索 URL が HTTPS を使用し、 `/.well-known/openid-configuration`で終わることを確認します。 また、クエリ パラメーターやフラグメント識別子が含まれていないことを確認します。 発行者の値が検出ドキュメントと正確に一致していることを確認します。
    - `client_id`など、`ABCD`に Microsoft Entra ID に値が割り当てられていることを確認します。
    - プロバイダーはまず、Microsoft Entra ID が提示するを`id_token_hint`する必要があります。
- `id_token_hint`の要求から:

    - (省略可能) [Microsoft Graph](https://graph.microsoft.com/) を呼び出して、このユーザーに関するその他の詳細を取得します。 `oid`における`tid`および`id_token_hint`のクレームは、この点で役立ちます。 `id_token_hint`で提供される要求の詳細については、「既定の`id_token_hint`要求」を参照してください。
- プロバイダーの製品に対して他の認証アクティビティを実行します。
- 次のセクションで説明するように、ユーザーのアクションの結果やその他の要因に応じて、プロバイダーは応答を構築し、Microsoft Entra ID に返信します。

##### プロバイダー応答の Microsoft Entra ID 処理

プロバイダーは、 `POST` を使用して応答を `redirect_uri`に送信する必要があります。 成功した応答では、次のパラメーターを指定する必要があります。

| パラメーター | 価値 | 説明 |
| --- | --- | --- |
| `id_token` |  | 外部 ID プロバイダーが発行するトークン。 |
| `state` |  | 要求で渡されたものと同じ状態 (存在する場合)。 それ以外の場合、この値は存在しません。 |

成功した場合、プロバイダーはユーザーに `id_token` 値を発行します。 Microsoft Entra ID は、発行された OIDC メタデータを使用して、トークンに予期される要求が含まれていることを確認し、OIDC に必要なその他のトークン検証を実行します。

| 要求 | 価値 | 説明 |
| --- | --- | --- |
| `iss` |  | 発行者: プロバイダーの検出メタデータの発行者と一致する必要があります。 |
| `aud` |  | 対象ユーザー: Microsoft Entra ID クライアント ID。 外部 ID プロバイダーへの Microsoft Entra ID 呼び出しの`client_id`を参照してください。 |
| `exp` |  | 有効期限: 通常どおりに設定します。 |
| `iat` |  | 発行時間: 通常どおりに設定します。 |
| `sub` |  | 件名: この要求を開始するためには、件名が送信された id\_token\_hint の sub と一致する必要があります。 |
| `nonce` |  | 要求で渡されたのと同じ `nonce` 値。 |
| `acr` |  | 認証リクエストに対する`acr`クレーム。 この値は、この要求を開始するために送信された要求の値の 1 つと一致する必要があります。 1 つの `acr` 要求のみを返す必要があります。 要求の一覧については、「 サポートされている `acr` 要求」を参照してください。 |
| `amr` |  | 使用される認証方法の `amr` 要求。 この値は配列として返される必要があり、返される方法のクレームは 1 つだけです。 要求の一覧については、「 サポートされている `amr` 要求」を参照してください。 |

###### サポートされている acr クレーム

| 要求 | メモ |
| --- | --- |
| `possessionorinherence` | 認証では、所有または一貫性に基づく要素を使用する必要があります。 |
| `knowledgeorpossession` | 認証では、知識ベースまたは所有ベースの要素を使用する必要があります。 |
| `knowledgeorinherence` | 認証では、ナレッジベースまたはインヒーレンスベースの要素を使用する必要があります。 |
| `knowledgeorpossessionorinherence` | 認証では、知識、所有、または一貫性に基づく要素を使用する必要があります。 |
| `knowledge` | 認証では、ナレッジ ベースの要素を使用する必要があります。 |
| `possession` | 認証では、所有ベースの要素を使用する必要があります。 |
| `inherence` | 認証では、インヒーレンス ベースの要素を使用する必要があります。 |

###### サポートされている AMR クレーム

| 要求 | メモ |
| --- | --- |
| `face` | 顔認識による生体認証 |
| `fido` | FIDO2が使用された |
| `fpt` | 指紋による生体認証 |
| `hwk` | ハードウェアで保護されたキーの所有証明 |
| `iris` | 虹彩スキャンによる生体認証 |
| `otp` | ワンタイム パスワード |
| `pop` | 所持証明 |
| `retina` | 網膜スキャンによる生体認証 |
| `sc` | スマート カード |
| `sms` | 登録された番号へのテキスト メッセージによる確認 |
| `swk` | ソフトウェアで保護されたキーの存在の確認 |
| `tel` | 電話での確認 |
| `vbm` | ボイスプリントによる生体認証 |

Microsoft Entra ID では、MFA 要求でトークンを発行するために MFA が満たされている必要があります。 その結果、別の種類の方法だけが 2 番目の要素の要件を満たすことができます。 前述したように、2 番目の要素を満たすために使用できる別の方法の種類は、知識、所有、固有性です。

Microsoft Entra ID は、次の表に基づいて種類のマッピングを検証します。

| 要求メソッド | タイプ | メモ |
| --- | --- | --- |
| `face` | 固有性 | 顔認識による生体認証。 |
| `fido` | 所有 | FIDO2 が使用されます。 実装によっては生体認証が必要な場合もありますが、所有メソッドの種類はプライマリ セキュリティ属性であるためマップされます。 |
| `fpt` | 固有性 | 指紋による生体認証。 |
| `hwk` | 所有 | ハードウェアで保護されたキーの所有証明。 |
| `iris` | 固有性 | 虹彩スキャンによる生体認証。 |
| `otp` | 所有 | ワンタイム パスワード。 |
| `pop` | 所有 | 所有権証明 |
| `retina` | 固有性 | 網膜スキャンの生体認証。 |
| `sc` | 所有 | スマート カード。 |
| `sms` | 所有 | 登録された番号へのテキストによる確認。 |
| `swk` | 所有 | ソフトウェアで保護されたキーの存在の証明。 |
| `tel` | 所有 | 電話による確認。 |
| `vbm` | 固有性 | ボイスプリントを使用した生体認証。 |

Microsoft Entra ID は、トークンに問題が見つからない場合は MFA が満たされていると見なし、ユーザーにトークンを発行します。 それ以外の場合、ユーザーの要求は失敗します。

失敗は、エラー応答パラメーターの発行によって示されます。

| パラメーター | 価値 | 説明 |
| --- | --- | --- |
| エラー |  | ASCIIエラーコードである`access_denied`や`temporarily_unavailable` |

応答に `id_token parameter` が存在し、トークンが有効な場合、Microsoft Entra ID は要求が成功したと見なします。 それ以外の場合、要求は失敗したと見なされます。 Microsoft Entra ID は、条件付きアクセス ポリシーの要件により、元の認証試行に失敗します。

Microsoft Entra ID は、プロバイダーへのリダイレクトから約 5 分後に、認証試行の状態を破棄します。

### Microsoft Entra ID エラー応答処理

Microsoft Azure サービスでは、 `correlationId` 値を使用して、さまざまな内部および外部システム間で呼び出しを関連付けます。 これは、複数の HTTP 呼び出しが含まれる可能性がある操作またはフロー全体の共通の識別子として機能します。 いずれかの操作中にエラーが発生した場合、応答には **関連付け ID** という名前のフィールドが含まれます。

Microsoft サポートまたは同様のサービスに連絡する場合は、 **関連付け ID の** 値を指定します。 テレメトリとログにすばやくアクセスするのに役立ちます。

次に例を示します。

`ENTRA IDSTS70002: Error validating credentials. ENTRA IDSTS50012: External ID token from issuer 'https://sts.XXXXXXXXX.com/auth/realms/XXXXXXXXXmfa' failed signature verification. KeyID of token is 'A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0u'`

`Trace ID: 0000aaaa-11bb-cccc-dd22-eeeeee333333`

`Correlation ID: aaaa0000-bb11-2222-33cc-444444dddddd`

`Timestamp: 2023-07-24 16:51:34Z`

### カスタム コントロールと外部 MFA メソッド

Microsoft Entra ID では、外部 MFA メソッドと条件付きアクセス カスタム コントロールは、顧客が外部 MFA メソッドの準備と移行を行っている間に並行して動作できます。

現在、カスタム コントロールを使って外部プロバイダーとの統合を使っている顧客は、それらと、アクセスを管理するために構成した条件付きアクセス ポリシーを引き続き使用できます。 管理者は、移行期間中に条件付きアクセス ポリシーの並列セットを作成することをお勧めします。

- ポリシーでは、カスタム コントロールの許可ではなく、 **多要素認証** 許可コントロールを使用する必要があります。

    注

    組み込みの MFA 強度を含む認証の強度に基づく制御を付与することは、外部 MFA メソッドでは満たされません。 ポリシーは、[ **多要素認証が必要**] でのみ構成する必要があります。
- 新しいポリシーは、最初にユーザーのサブセットを使ってテストできます。 テスト グループは、カスタム コントロールを必要とするポリシーから除外され、MFA を必要とするポリシーに含まれます。 MFA を必要とするポリシーが外部 MFA メソッドによって満たされていることを管理者が快適に確認できる場合、管理者は MFA 許可を持つすべての必要なユーザーをポリシーに含めることができます。 カスタム コントロール用に構成されたポリシーは、[ **オフ** ] 設定に移動できます。

### 統合サポート

Microsoft Entra ID と外部 MFA メソッドの統合を構築するときに問題がある場合は、Microsoft カスタマー エクスペリエンス エンジニアリング (CxE) 独立ソリューション ベンダー (ISV) が支援できる可能性があります。 CxE ISV チームと連携するには、 [サポートの要求](https://aka.ms/EAMProviderSupport)を送信します。

### 関連情報

- [OAuth2.0 と OIDC の仕様](https://oauth.net/2/)

### 用語集

| 用語 | 説明 |
| --- | --- |
| MFA | 多要素認証。 |
| 外部 MFA メソッド | ユーザーの認証の一部として使用される Microsoft Entra ID 以外のプロバイダーからの認証方法。 |
| OIDC | OpenID Connect は、OAuth 2.0 に基づく認証プロトコルです。 |
| `00001111-aaaa-2222-bbbb-3333cccc4444` | 外部 MFA メソッドに統合された `appid` 値の例。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-authentication-methods-manage"} -->
## 認証方法を管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-methods-manage
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: 認証方法のポリシーと、認証方法を管理するさまざまな方法について説明します。

Microsoft Entra ID では、多岐にわたるサインイン シナリオをサポートできるよう、さまざまな認証方法を使用できます。 使用可能なオプションの概要については、「 [Microsoft Entra ID の認証方法」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)参照してください。 管理者は、ユーザー エクスペリエンスとセキュリティの目標に合わせて、各手法を具体的に構成できます。 このトピックでは、Microsoft Entra ID の認証方法を管理する方法と、構成オプションがユーザーのサインインおよびパスワードのリセットのシナリオにどのように影響するかについて説明します。

### 認証方法ポリシー

認証方法ポリシーは、パスワードレス認証のような最新の方法を含む、認証方法を管理するための推奨方法です。 [認証ポリシー管理者は、](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) このポリシーを編集して、すべてのユーザーまたは特定のグループの認証方法を有効にすることができます。

認証方法ポリシーで有効にした方法は、通常、認証とパスワード リセットの両方のシナリオで、Microsoft Entra ID の任意の場所で使用できます。 ただし、FIDO2 や Windows Hello for Business など、もともと認証での使用に限定されるメソッドや、セキュリティの質問など、パスワード リセットでの使用に限定されるメソッドは例外です。 特定の認証シナリオで使用できる方法をより詳細に制御する場合は、 **認証強度** 機能の使用を検討してください。

ほとんどのメソッドには、そのメソッドの使用方法をより正確に制御するための構成パラメーターもあります。 たとえば、 **音声通話**を有効にした場合、携帯電話に加えて会社の電話を使用できるかどうかを指定することもできます。

あるいは、Microsoft Authenticator によるパスワードレス認証を有効にするとします。 ユーザーのサインイン場所や、サインインしているアプリの名前を表示するなどの追加パラメーターを設定できます。 これらのオプションは、ユーザーがサインインするときにより多くのコンテキストを提供し、誤った MFA の承認を防ぐのに役立ちます。

認証方法ポリシーを管理するには、少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインし、**Entra ID**&gt;**Authentication メソッド**&gt;**Policies** を参照します。

[Image: 認証方法ポリシーのスクリーンショット。]

[統合された登録エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-registration-mfa-sspr-combined)のみが認証方法ポリシーを認識します。 認証方法ポリシーのスコープ内にあるが、統合された登録エクスペリエンスではないユーザーには、登録する正しい方法が表示されません。

### レガシ MFA と SSPR ポリシー

**多要素認証**設定と**パスワード リセット**設定にある他の 2 つのポリシーは、テナント内のすべてのユーザーに対して一部の認証方法を管理する従来の方法を提供します。 有効にした認証方法を使うユーザーや、その方法をどのように使用できるかを制御することはできません。

重要

2023 年 3 月に、レガシ多要素認証とセルフサービス パスワード リセット (SSPR) ポリシーでの認証方法の管理の廃止を発表しました。 2025 年 9 月 30 日より、これらのレガシ MFA および SSPR ポリシーでは認証方法を管理できません。 顧客には、手動移行制御を使用して、非推奨となる日までに認証方法ポリシーに移行することをお勧めします。

従来の MFA ポリシーを管理するには、**Entra ID**&gt;**多要素認証**&gt;**開始**&gt;**構成**&gt;**追加のクラウドベースの多要素認証設定**に移動します。

[Image: MFA サービス設定のスクリーンショット。]

セルフサービス パスワード リセット (SSPR) の認証方法を管理するには、 **Entra ID**&gt;**Password reset**&gt;**Authentication メソッド**を参照します。 このポリシーの **[携帯電話** ] オプションを使用すると、音声通話またはテキスト メッセージを携帯電話に送信できます。 **Office 電話**オプションでは、音声通話のみが許可されます。

[Image: パスワード リセット設定のスクリーンショット。]

### ポリシーの連動について

設定はポリシー間で同期されないため、管理者は各ポリシーを個別に管理できます。 Microsoft Entra ID は、すべてのポリシーの設定を尊重するため、 *任意* のポリシーで認証方法を有効にしたユーザーは、その方法を登録して使用できます。 ユーザーがある方法を使用できないようにするには、すべてのポリシーでそれを無効にする必要があります。

会計グループに所属するユーザーが、Microsoft Authenticator を登録する例を見てみましょう。 登録プロセスでは、まず認証方法ポリシーを確認します。 会計グループが Microsoft Authenticator に対して有効な場合、ユーザーはそれを登録できます。

そうでない場合、登録プロセスはレガシ MFA ポリシーを確認します。 このポリシーでは、これらの設定のいずれかが MFA に対して有効になっていれば、どのユーザーも Microsoft Authenticator を登録できます。

- **モバイル アプリを使用した通知**
- **モバイル アプリまたはハードウェア トークンからの確認コード**

ユーザーがこれらのポリシーのいずれかに基づいて Microsoft Authenticator を登録できない場合、登録プロセスではレガシ SSPR ポリシーが確認されます。 このポリシーでも、ユーザーが SSPR に対して有効になっており、これらの設定のいずれかが有効な場合、ユーザーは Microsoft Authenticator を登録できます。

- **モバイル アプリの通知**
- **モバイル アプリ コード**

SSPR の **携帯電話** に対して有効になっているユーザーの場合、ポリシー間の独立した制御がサインイン動作に影響する可能性があります。 他のポリシーにテキスト メッセージと音声通話用の個別のオプションがある場合、SSPR の **携帯電話** では両方のオプションが有効になります。 その結果、SSPR に **携帯電話** を使用するすべてのユーザーは、他のポリシーで音声通話が許可されていない場合でも、パスワードリセットのために音声通話を使用できます。

同様に、グループに対して **音声通話** を有効にしたとします。 有効にした後、グループのメンバーでないユーザーでも音声通話でサインインできることに気づきます。 この場合、これらのユーザーは、従来の SSPR ポリシーで **携帯電話** に対して有効になっている可能性があります。または、従来の MFA ポリシーで **電話を呼び出** す可能性があります。

### ポリシー間の移行

認証方法ポリシーには、すべての認証方法の統合管理への移行ガイドが用意されています。 ポリシーの対象が意図したユーザーグループ、またはすべてのユーザーである場合、すべての希望するメソッドを「認証方法」ポリシーで有効にすることができます。 認証方法移行ガイドでは、MFAとSSPRに関する現在のポリシー設定を監査する手順を自動化し、それらを認証方法ポリシーに統合します。 [Microsoft Entra 管理センター](https://entra.microsoft.com)からガイドにアクセスするには、**Entra ID**&gt;**Authentication メソッド**&gt;**Policies** を参照します。

[Image: ウィザードのエントリ ポイントが強調表示されている [認証方法ポリシー] ブレードのスクリーンショット。]

ポリシー設定を手動で移行もできます。 移行には、自分のペースで進められ、移行中のサインインまたは SSPR の問題を回避するための 三つの設定があります。

移行が完了したら、従来の MFA ポリシーと SSPR ポリシーのメソッドを無効にできます。 サインインと SSPR の両方の認証方法のコントロールを 1 か所に集中でき、レガシ MFA と SSPR のポリシーは無効にします。

注

セキュリティの質問は、現在、レガシ SSPR ポリシーを使用することでのみ有効にすることができます。 秘密の質問を使用していて、それらを無効にしたくない場合は、移行コントロールが使用可能になるまで、レガシ SSPR ポリシーで秘密の質問を有効にしておく必要があります。 認証方法の残りの部分を移行し、レガシ SSPR ポリシーでセキュリティの質問を管理し続けることができます。

移行オプションを表示するには、認証方法ポリシーを開き、[ **移行の管理**] をクリックします。

[Image: 移行オプションのスクリーンショット。]

各オプションの説明を次の表に示します。

| オプション | 説明 |
| --- | --- |
| 移行前 | 認証方法ポリシーは、認証にのみ使用されます。レガシ ポリシー設定は維持されます。 |
| 移行が進行中 | 認証方法ポリシーは、認証と SSPR に使用されます。レガシ ポリシー設定は維持されます。 |
| 移行の完了 | 認証方法ポリシーのみが、認証と SSPR に使用されます。レガシ ポリシー設定は無視されます。 |

テナントは、そのテナントの現在の状態に応じて、既定で [移行前] または [移行が進行中] のいずれかに設定されます。 "移行前" で開始する場合は、任意の状態にいつでも移動できます。 移行中に移行を開始した場合は、いつでも移行中と移行完了の間を移動できますが、移行前への移行は許可されません。 [移行の完了] に移行した後、以前の状態にロールバックすることを選んだ場合、製品のパフォーマンスを評価するために、その理由をお客様に確認します。

[Image: ロールバックの理由のスクリーンショット。]

注

すべての認証方法が完全に移行された後も、レガシ SSPR ポリシーの次の要素はアクティブなままになります。

- 制御 **をリセットするために必要な方法の数** : 管理者は、ユーザーが SSPR を実行する前に確認する必要がある認証方法の数を変更し続けることができます。
- SSPR 管理者ポリシー: 管理者は、レガシ SSPR 管理者ポリシーに一覧表示されている方法、または認証方法ポリシーで使用できるようになっている方法を引き続き登録および使用できます。

将来、これらの機能はどちらも認証方法ポリシーと統合される予定です。

### 既知の問題と制限事項

- 最近の更新で、個々のユーザーをターゲットにする機能が削除されました。 過去にターゲットとされていたユーザーはポリシー内に残りますが、ターゲットとされるグループにそれらを移動することをお勧めします。
- 認証方法ポリシーまたは登録キャンペーンに多数のグループが含まれている場合、認証方法の登録が失敗する可能性があります。 認証方法ごとに複数のグループを 1 つのグループに統合することをお勧めします。 統合中にユーザーの登録を維持するには、同じ操作内で新しいグループを追加して現在のグループを削除します。

    注

    多数のグループを対象とし、ポリシー サイズが 20 KB を超える場合、認証方法ポリシーに更新を保存できない場合があります。 この制限を回避するには、ターゲット グループを可能な限り統合します。

### ユーザーが使用できるメソッドと使用できないメソッド

管理者は、Microsoft Entra 管理センターでユーザー認証方法を表示できます。 使用可能なメソッドが最初に一覧表示され、その後に使用できないメソッドが続きます。

各認証方法は、さまざまな理由で使用できなくなる可能性があります。 たとえば、一時アクセス パスの有効期限が切れたり、FIDO2 セキュリティ キーが構成証明に失敗したりする場合があります。 ポータルが更新され、メソッドが使用できない理由が説明されます。

**[多要素認証の再登録を要求する]** のために使用できなくなった認証方法もここに表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-authentication-oath-tokens"} -->
## OATH トークンの認証方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-oath-tokens
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra ID で OATH トークンを使用して、サインイン イベントの改善と安全性の確保に役立てる方法について説明します。

OATH 時間ベースのワンタイム パスワード (TOTP) は、1 回限りのパスワード (OTP) のコードの生成方法を指定するオープン標準です。 OATH TOTP は、コードを生成するために、ソフトウェアまたはハードウェアを使用して実装できます。 Microsoft Entra ID は、別のコード生成標準である OATH HOTP をサポートしていません。

### ソフトウェア OATH トークン

ソフトウェア OATH トークンは、通常、Microsoft Authenticator アプリやその他の認証アプリなどのアプリケーションです。 Microsoft Entra ID は、各 OTP を生成するためにアプリに入力して使用される秘密鍵 (シード) を生成します。

Authenticator アプリは、プッシュ通知を行うように設定されたときに自動的にコードを生成します。これにより、デバイスが接続されていない場合でも、ユーザーにはバックアップがあります。 OATH TOTP を使用してコードを生成するサード パーティ アプリケーションを使用することもできます。

一部の OATH TOTP ハードウェア トークンはプログラミング可能であり、秘密鍵やシードは事前にプログラミングされていません。 これらのプログラミング可能なハードウェア トークンは、ソフトウェア トークンのセットアップ フローから取得した秘密鍵またはシードを使用して設定できます。 顧客は、選択したベンダーからこれらのトークンを購入し、ベンダーのセットアップ プロセスで秘密鍵またはシードを使用することができます。

### ハードウェア OATH トークン (プレビュー)

Microsoft Entra ID は、30 秒または 60 秒ごとにコードを更新する OATH-TOTP SHA-1 および SHA-256 トークンの使用をサポートしています。 顧客は、選択したベンダーからこれらのトークンを購入できます。

Microsoft Entra ID には、Azure 用の新しいプレビューの Microsoft Graph API が用意されています。 管理者は、最小限の特権ロールを持つ Microsoft Graph API にアクセスして、プレビューでトークンを管理できます。 Microsoft Entra 管理センターでのこのプレビュー更新では、ハードウェア OATH トークンを管理するオプションはありません。

Microsoft Entra 管理センターの **OATH トークン**で、元のプレビューのトークンを引き続き管理できます。 一方、Microsoft Graph API を使用して管理できるのは、プレビュー更新のトークンのみです。

このプレビュー更新のために Microsoft Graph で追加したハードウェア OATH トークンは、他のトークンと共に管理センターに表示されます。 ただし、Microsoft Graph を使用してのみ管理できます。

#### 時間ドリフトの修正

Microsoft Entra ID は、アクティブ化とすべての認証時のトークンの時間ドリフトを調整します。 次の表に、アクティブ化とサインイン時に Microsoft Entra ID がトークンに対して行う時間調整の一覧を示します。

| トークンの更新間隔 | アクティブ化時間の範囲 | 認証時間の範囲 |
| --- | --- | --- |
| 30 秒 | +/- 1 日 | +/- 1 分 |
| 60 秒 | +/- 2 日 | +/- 2 分 |

#### プレビュー更新の機能強化

このハードウェア OATH トークン プレビューの更新では、全体管理者の要件が削除されることで、組織の柔軟性とセキュリティが向上します。 組織は、トークンの作成、割り当て、アクティブ化を特権認証管理者または認証ポリシー管理者に委任できます。

次の表に、プレビュー更新でハードウェア OATH トークンを管理するためのロール要件を示します。

| タスク | プレビュー更新ロール |
| --- | --- |
| テナントのインベントリに新しいトークンを作成します。 | 認証ポリシー管理者 |
| テナントのインベントリからトークンを読み取ります。シークレットは返しません。 | 認証ポリシー管理者 |
| テナント内のトークンを更新します。 たとえば、製造元またはモジュールを更新します。シークレットは更新できません。 | 認証ポリシー管理者 |
| テナントのインベントリからトークンを削除します。 | 認証ポリシー管理者 |

プレビューの更新の一環として、エンド ユーザーは自分の[セキュリティ情報](https://mysignins.microsoft.com/security-info)からトークンを自己割り当てしてアクティブにすることもできます。 プレビューの更新では、トークンは 1 人のユーザーにのみ割り当てることができます。 次の表に、トークンを割り当ててアクティブにするためのトークンとロールの要件を示します。

| タスク | トークンの状態 | ロールの要件 |
| --- | --- | --- |
| インベントリからテナント内のユーザーにトークンを割り当てます。 | 割り当て済み | メンバー (自分)認証管理者特権認証管理者 |
| ユーザーのトークンを読み取りますが、シークレットは返しません。 | アクティブ化/割り当て済み (トークンが既にアクティブにされているかどうかによって異なります) | メンバー (自分自身)認証管理者 (標準の読み取り権限はなく、限定された読み取り権限のみ)特権認証管理者 |
| ユーザーのトークンを更新します。たとえば、アクティブ化用の現在の 6 桁のコードの指定や、トークン名の変更などです。 | アクティブ化済み | メンバー (自分)認証管理者特権認証管理者 |
| ユーザーからトークンを削除します。 トークンはトークン インベントリに戻ります。 | 使用可能 (テナント インベントリに戻る) | メンバー (自分)認証管理者特権認証管理者 |

従来の多要素認証 (MFA) ポリシーでは、ハードウェアとソフトウェアの OATH トークンは同時に有効にすることしかできません。 レガシ MFA ポリシーで OATH トークンを有効にすると、エンド ユーザーにはセキュリティ情報ページに**ハードウェア OATH トークン**を追加するオプションが表示されます。

**ハードウェア OATH トークン**を追加するオプションをエンド ユーザーに表示させたくない場合は、認証方法ポリシーに移行します。 認証方法ポリシーでは、ハードウェアおよびソフトウェア OATH トークンを個別に有効にして管理できます。 認証方法ポリシーに移行する方法の詳細については、「[MFA と SSPR のポリシー設定を Microsoft Entra ID の認証方法ポリシーに移行する方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-methods-manage)」を参照してください。

Microsoft Entra ID P1 または P2 ライセンスを持つテナントは、元のプレビューと同様に、引き続きハードウェア OATH トークンをアップロードできます。 詳細については、「[CSV 形式でハードウェア OATH トークンをアップロードする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-upload-oath-tokens)」を参照してください。

ハードウェア OATH トークンと、トークンのアップロード、アクティブ化、割り当てに使用できる Microsoft Graph API を有効にする方法の詳細については、[OATH トークンの管理方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-manage-oath-tokens)に関するページを参照してください。

### OATH トークンのアイコン

ユーザーは、[\[セキュリティ情報\]](https://aka.ms/mysecurityinfo) で OATH トークンを追加および管理できます。また、**[マイ アカウント]** から **[セキュリティ情報]** を選択することもできます。 ソフトウェアとハードウェアの OATH トークンには、異なるアイコンが使用されています。

| トークンの登録の種類 | アイコン |
| --- | --- |
| OATH ソフトウェア トークン | [Image: ソフトウェア OATH トークン] |
| OATH ハードウェア トークン | [Image: ハードウェア OATH トークン] |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-authentication-passkeys-fido2"} -->
## Microsoft Entra IDでのパスキー (FIDO2) 認証方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2
- Service: entra-id / authentication
- Article date: 2026-02-02
- Summary: Microsoft Entra IDでパスキー (FIDO2) 認証を使用してサインイン イベントを改善し、セキュリティで保護する方法について説明します

リモート フィッシング攻撃が増加しています。 これらの攻撃は、ユーザーのデバイスに物理的にアクセスすることなく、ID 証明 (パスワード、SMS コード、電子メール ワンタイム パスコードなど) を盗んだり中継したりすることを目的とします。 攻撃者は多くの場合、ソーシャル エンジニアリング、資格情報の収集、またはダウングレードの手法を使用して、パスキーやセキュリティ キーなどの強力な保護をバイパスします。 AI 主導の攻撃ツールキットにより、これらの脅威はより高度でスケーラブルになっています。

パスキーは、パスワード、SMS、電子メール コードなどのフィッシング詐欺の方法を置き換えることで、リモート フィッシングを防ぐのに役立ちます。 **FIDO (Fast Identity Online) 標準**に基づいて構築されたパスキーでは、配信元にバインドされた公開キー暗号化が使用され、資格情報を再生したり、悪意のあるアクターと共有したりできなくなります。

業界のセキュリティ専門家によって開発された相互運用可能な **FIDO (Fast Identity Online) 標準** に基づいて構築されています。 配信元にバインドされた公開キー暗号化を使用し、ローカル ユーザーの操作を必要とします。 これらの特性を組み合わせると、パスキーをフィッシングすることはほとんど不可能となります。

秘密キーはデバイスに格納され、公開キーはサインインしたアプリまたは Web サイトに格納されます。 サインインするには、両方の一意のキーが必要です。 このキー ペアの組み合わせは一意であるため、パスキーは Web サイトまたは作成したアプリでのみ機能します。

サインインを試みるたびに、サインインに使用するデバイスでパスキーのロックを解除する必要があります。 他人が管理する別のデバイスにサインインするように騙されることはありません。

より強力なセキュリティに加えて、パスキー (FIDO2) は、パスワードを排除し、プロンプトを減らし、デバイス間で高速で安全な認証を有効にすることで、摩擦のないサインイン エクスペリエンスを提供します。 これらを使用して、Microsoft Entra ID または Microsoft Entra ハイブリッド 参加 Windows 11 デバイスにサイン インし、クラウドおよびオンプレミス リソースにシングルサイン オンします。

### パスキーとは何ですか

パスキーは、 **強力な認証** を提供するフィッシングに強い資格情報であり、デバイスの生体認証または PIN と組み合わせると **多要素認証 (MFA)** メソッドとして機能します。 認証子は、検証者のなりすましに対する耐性を提供します。これにより、パスキーが登録された証明書利用者（RP）にのみシークレットを解放し、そのRPを装う攻撃者には解放しません。 Passkeys (FIDO2) は FIDO2 標準に従い、ブラウザーには WebAuthn を使用し、認証子通信には CTAP を使用します。

次のプロセスは、ユーザーがパスキー (FIDO2) を使用してMicrosoft Entra IDにサインインするときに使用されます。

1. ユーザーがMicrosoft Entra IDへのサインインを開始します。
2. ユーザーはパスキーを選択します。
    - 同じデバイス (デバイスに格納)
    - デバイス間 (QR コード経由) または FIDO2 セキュリティ キー
3. Microsoft Entra ID は、Authenticator にチャレンジ (nonce) を送信します。
4. 認証子は、ハッシュ RP ID と資格情報 ID を使用してキー ペアを検索します。
5. ユーザーは生体認証または PIN ジェスチャを実行して秘密キーのロックを解除します。
6. 認証子は秘密キーを使用してチャレンジに署名し、署名を返します。
7. Microsoft Entra ID公開キーを使用して署名を検証し、トークンを発行します。

### パスキーの種類

- **デバイス バインド パスキー**: 秘密キーは作成され、1 つの物理デバイスに格納され、残されることはありません。 例：
    - Microsoft Authenticator
    - FIDO2 セキュリティ キー
- **同期されたパスキー**: 秘密キーは、ハードウェア セキュリティ モジュール (HSM) によって作成され、ローカル デバイスで暗号化されます。 この暗号化されたキーは同期され、クラウド パスキー プロバイダーに格納されます。 その後、パスキー プロバイダーで認証された他のデバイスで、パスキーを使用できます。 これはプロバイダーによって異なる場合があります。 同期されたパスキーは認証をサポートしていません。 例：
    - [Apple iCloud キーチェーン](https://support.apple.com/en-us/102195)
    - [Google パスワード マネージャー](https://security.googleblog.com/2022/10/SecurityofPasskeysintheGooglePasswordManager.html)

同期されたパスキーは、ユーザーが顔、指紋、PIN などのデバイスのネイティブロック解除メカニズムを使用して認証できるシームレスで便利なユーザー エクスペリエンスを提供します。 同期されたパスキーを登録して使用しているMicrosoft アカウントの何億ものコンシューマー ユーザーからの学習に基づいて、次の点を学習しました。

- **99% のユーザーが同期されたパスキーを正常に登録する**
- 同期されたパスキーは、 **パスワードと従来の MFA の組み合わせに比べて 14 倍高速です。69 秒ではなく 3 秒**
- ユーザーは、**従来の認証方法 (95% 対 30%) よりも、同期されたパスキーを使用したサインインの方が 3 倍成功**しています
- Microsoft Entra IDで同期されたパスキーを使用すると、すべてのエンタープライズ ユーザーが MFA を大規模に簡素化できます。 これは、SMS や認証アプリなどの従来の MFA オプションに代わる便利で低コストの代替手段です。

組織内でパスキーを展開する方法の詳細については、「 [同期されたパスキーを有効にする方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2)」を参照してください。

**構成証明** は、登録時にパスキープロバイダーまたはデバイスの真正性を検証します。 適用される場合:

- FIDO メタデータ サービス (MDS) を介して、暗号で検証可能なデバイス ID を提供します。 構成証明が適用されると、依存側は、Authenticator モデルを検証でき、認定されたデバイスに対してポリシーに基づく判断を適用できます。
- 未認証のパスキーには、同期されたパスキーや、未認証のデバイスに結び付けられたパスキーが含まれており、デバイスの由来は提供されません。

Microsoft Entra ID において:

- 構成証明は、 **パスキー プロファイル** レベルで適用できます。
- 構成証明が有効な場合は、デバイスに結び付けられたパスキーのみが許可されます。同期されたパスキーは使用できません。

### 適切なパスキー オプションを選択する

FIDO2 セキュリティ キーは、高度に規制された業界または特権を持つユーザーに推奨されます。 強力なセキュリティを提供しますが、特にユーザーが物理的なキーを紛失し、アカウントの回復が必要な場合は、機器、トレーニング、ヘルプデスクのサポートのコストを増やすことができます。 Microsoft Authenticator アプリのパスキーは、これらのユーザー グループのもう 1 つのオプションです。

ほとんどのユーザー (厳しく規制された環境外のユーザー、または機密性の高いシステムにアクセスしないユーザー) には、**同期されたパスキー** によって、従来の MFA に代わる便利で低コストの代替手段が提供されます。 Apple と Google は、クラウドに格納されているパスキーに対する高度な保護を実装しています。

種類 (デバイスバインドまたは同期) に関係なく、パスキーは、フィッシング可能な MFA メソッドよりも重要なセキュリティ アップグレードを表します。

詳細については、「[Microsoft Entra ID におけるフェッシングに強い MFA 展開の開始](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-prerequisites-phishing-resistant-passwordless-authentication)」をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-authentication-phone-options"} -->
## 音声呼び出し認証方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-phone-options
- Service: entra-id / authentication
- Article date: 2025-10-26
- Summary: Microsoft Entra ID で音声呼び出し認証方法を使用して、サインイン イベントの改善とセキュリティ保護を支援する方法について説明します

Microsoft では、ユーザーが多要素認証にテキスト メッセージまたは音声通話を使用しないことをお勧めします。 別の方法として、[Microsoft Authenticator](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-authenticator-app) などの最新の認証方法を使用することをお勧めします。 詳細については、「[認証のための電話転送から脱却する時がきました](https://aka.ms/hangup)」を参照してください。 引き続きユーザーは、多要素認証またはセルフサービス パスワード リセット (SSPR) で使用される認証のセカンダリ形式として、携帯電話または会社電話を使用して自身を確認できます。

テキスト メッセージを使用した直接認証では、[ユーザーが SMS ベースの認証を構成して有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-sms-signin)ことができます。 テキスト メッセージは、現場担当者にとって便利です。 テキスト メッセージでは、ユーザーはアプリケーションやサービスにアクセスするためのユーザー名とパスワードを知る必要がありません。 代わりに、登録済みの携帯電話番号を入力し、確認コードを含むテキスト メッセージを受信して、サインイン インターフェイスに入力します。

注

試用版サブスクリプションを使用した Microsoft Entra テナントでは、音声通話の確認は使用できません。 たとえば、試用版ライセンスの Microsoft Enterprise Mobility and Security (EMS) にサインアップした場合、音声通話の確認は使用できません。 電話番号の形式を *+* (例: *+1 4251234567*) で指定する必要があります。 国/地域番号と電話番号の間にスペースを入れる必要があります。

### 携帯電話の確認

Microsoft Entra 多要素認証または SSPR の場合、ユーザーは、サインイン インターフェイスに入力する確認コードを含むテキスト メッセージを受信するか、電話を受けるかを選択できます。

ユーザーが、携帯電話番号をディレクトリに表示したくなく、それでもパスワードのリセットにその番号を使いたい場合は、管理者がその電話番号をディレクトリに設定しないようにする必要があります。 代わりに、ユーザーは **[自分のサインイン]** で[認証用電話](https://aka.ms/setupsecurityinfo)を設定する必要があります。管理者は、この情報をユーザーのプロファイルで確認できますが、他の場所には公開されていません。

[Image: 電話番号が入力された認証方法を示す Microsoft Entra 管理センターのスクリーンショット]

注

電話の内線番号は、会社電話でのみサポートされています。

Microsoft では、テキスト メッセージまたは音声ベースの一貫した Microsoft Entra 多要素認証プロンプトを、同一番号で配信するとは限りません。 ユーザーのために、Microsoft は、ルートを調整して SMS メッセージの配信率を向上させる際に任意のタイミングでショート コードを追加または削除する場合があります。 Microsoft は、米国とカナダ以外の国/リージョンではショート コードをサポートしていません。

注

無料または試用版サブスクリプションを持つテナントでテキスト メッセージまたは音声通話を受信できるように、配信方法の最適化が適用されます。

#### テキスト メッセージの確認

SSPR または Microsoft Entra 多要素認証でテキスト メッセージの確認を使用すると、確認コードを含むテキスト メッセージが携帯電話番号に送信されます。 サインイン プロセスを完了するには、指定された確認コードをサインイン インターフェイスに入力します。

テキスト メッセージは、ショート メッセージ サービス (SMS)、リッチ コミュニケーション サービス (RCS)、WhatsApp などのチャネル経由で送信できます。

Android ユーザーは自分のデバイスで RCS を有効にすることができます。 RCS を使用すると、SMS に対する暗号化やその他の機能強化が提供されます。 Android の場合、MFA テキスト メッセージは SMS ではなく RCS 経由で送信される可能性があります。 MFA テキスト メッセージは SMS に似ていますが、RCS メッセージはより Microsoft ブランド化されており、確認済みのチェックマークが付いているため、信頼できるメッセージであることをユーザーに知らせることができます。

[Image: RCS メッセージ内の Microsoft ブランドのスクリーンショット。]

一部のユーザーは、WhatsApp で確認コードを受け取る場合があります。 RCS と同様に、これらのメッセージは SMS に似ていますが、Microsoft のブランド化がより適用され、認証済みのチェックマークが付いています。 ユーザーが WhatsApp で初めて確認コードを受け取るときに、変更後の動作が SMS テキスト メッセージによって通知されます。

WhatsApp を持つユーザーのみが、このチャネルを介して確認コードを受信します。 ユーザーが WhatsApp を持っているかどうかを確認するために、テキスト メッセージによる検証用に登録された電話番号を使用して、アプリでユーザーへのメッセージ配信をサイレントに試行します。

ユーザーがインターネットに接続していない場合、または WhatsApp をアンインストールしている場合、ユーザーは SMS 確認コードを受け取ります。 Microsoft の WhatsApp Business Agent に関連付けられている電話番号は、*+1 (217) 302 1989* です。

[Image: 確認のスクリーンショット。]

#### 音声通話の確認

SSPR または Microsoft Entra 多要素認証で音声通話の確認を使用すると、ユーザーが登録した電話番号に自動音声通話が発信されます。 サインイン プロセスを完了するには、ユーザーはテンキーで # を入力するように求められます。

ユーザーが音声通話を着信する通話番号は、国ごとに異なります。 考えられるすべての音声通話番号を確認するには、「[電話の設定](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-mfasettings#phone-call-settings)」を参照してください。

注

SSPR は、プライマリ電話方式またはオフィス電話方式でのみ完了できます。 代替電話の方法は、MFA でのみ使用できます。

### 会社電話の確認

SSPR または Microsoft Entra 多要素認証で会社の音声通話による検証を使うと、ユーザーが登録した電話番号に自動音声通話が発信されます。 サインイン プロセスを完了するには、ユーザーはテンキーで # を入力するように求められます。

### 電話オプションのトラブルシューティング

Microsoft Entra ID の電話認証で問題が発生した場合は、次のトラブルシューティングの手順を確認してください。

- サインイン中の [確認呼び出しの上限に達しました]、または [テキスト確認コードの上限に達しました] というエラー メッセージ

    - Microsoft では、同じユーザーまたは組織が短時間に認証の試行を繰り返すことを制限する場合があります。 この制限は、Microsoft Authenticator または確認コードには適用されません。 これらの制限に達した場合は、Authenticator アプリまたは確認コードを使用するか、数分後にもう一度サインインを試行することができます。
- サインイン中に「申し訳ありませんが、アカウントの確認に問題が生じています」というエラー メッセージ

    - Microsoft では、音声またはテキスト メッセージ認証の試行の回数が多いことが原因で、同じユーザー、電話番号、または組織によって実行される音声またはテキスト メッセージ認証の試行を制限またはブロックすることがあります。 このエラーが発生した場合は、Authenticator や確認コードなどの別の方法を試したり、管理者に連絡してサポートを受けたりすることができます。
- 1 つのデバイスで発信者 ID がブロックされる。

    - デバイスで構成されているすべてのブロック済み番号を確認します。
- 電話番号が間違っているか、国/地域コードが正しくない。または、個人の電話番号と勤務先の電話番号を混同している。

    - ユーザー オブジェクトおよび構成されている認証方法をトラブルシューティングします。 正しい電話番号が登録されていることを確認します。
- 間違った PIN の入力。

    - ユーザーが自分のアカウントに登録されている正しい PIN を使用していることを確認します (MFA サーバー ユーザーのみ)。
- ボイスメールへの通話の転送。

    - ユーザーの電話の電源が入っていて、ユーザーがいる場所でサービスを利用できることを確認するか、または別の方法を使います。
- ユーザーがブロックされている

    - Microsoft Entra 管理センターで Microsoft Entra 管理者にユーザーのブロックを解除させます。
- SMS、RCS、WhatsApp などのテキスト メッセージング プラットフォームは、デバイスでサブスクライブされていません。

    - ユーザーに、方法の変更またはデバイス上でのテキスト メッセージング プラットフォームのアクティブ化を依頼してください。
- 通信プロバイダーの障害 (電話入力が検出されない、DTMF トーンが発行されない、複数のデバイスで発信者 ID がブロックされる、または複数のデバイスでテキスト メッセージがブロックされる場合など)。

    - Microsoft では、認証用の電話呼び出しおよびテキスト メッセージのルーティングに、複数の通信プロバイダーを使います。 これらの問題のいずれかが発生する場合は、ユーザーにその方法を 5 分以内に 5 回以上使わせて、Microsoft サポートに問い合わせるときにそのユーザーの情報を提供できるようにしてください。
- 信号品質が低い。

    - Authenticator アプリをインストールし、Wi-Fi 接続を使用してログインすることをユーザーに試してもらいます。
    - または、電話 (音声) 認証の代わりにテキスト メッセージを使用してください。
- 電話番号がブロックされ、音声 MFA に使用できない

    - Microsoft Entra 管理者が国番号をオプトインしていない場合、音声 MFA に対してブロックされている国番号がいくつかあります。 Microsoft Entra 管理者に、これらの国番号の MFA を受け取ることをオプトインしてもらいます。
    - または、音声認証の代わりに Microsoft Authenticator を使用します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-authentication-platform-credential-for-macos"} -->
## Microsoft Entra ID での macOS 認証方法のプラットフォーム資格情報 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-platform-credential-for-macos
- Service: entra-id / authentication
- Article date: 2025-10-25
- Summary: Microsoft Entra ID で macOS 認証にプラットフォーム資格情報を使用して、サインイン イベントの改善とセキュリティ保護を支援する方法について説明します

macOS プラットフォーム資格情報は、Microsoft Enterprise シングル サインオン拡張機能 (SSOe) を使用して有効になっている macOS の新機能です。 この機能は、認証に Microsoft Entra ID を使用するアプリ間での SSO に使用される、セキュア エンクレーブでサポートされたハードウェア バインド暗号化キーをプロビジョニングします。 ユーザーのローカル アカウント パスワードは影響を受けず、Mac にログオンするために必要になります。

[Image: プラットフォーム のシングル サインオンを使用して macOS アカウントを ID プロバイダーに登録するようユーザーに求めるポップアップ ウィンドウの例を示すスクリーンショット。]

macOS プラットフォーム資格情報を使用すると、ユーザーは、デバイスのロックを解除するように Touch ID を構成することでパスワードレスにでき、Windows Hello for Business テクノロジに基づいてフィッシングに対する耐性のある資格情報を使用できます。 これにより、セキュリティ キーの必要性を排除し、セキュリティで保護されたエンクレーブとの統合を使用してゼロ トラストの目標を進めることで、お客様の組織のコストを節約できます。

macOS のプラットフォーム資格情報は、WebAuthn のチャレンジ (ブラウザーの再認証シナリオなど) で使用するためのフィッシングに強い資格情報としても使用できます。 FIDO ポリシーでキー制限ポリシーを利用する場合は、許可されている AAGUID の一覧: `7FD635B3-2EF9-4542-8D9D-164F2C771EFC` に、macOS プラットフォーム資格情報の AAGUID を追加する必要があります。

[Image: macOS プラットフォーム SSO を使用したユーザー サインインに関連する手順の概要を示す図]

1. ユーザーが指紋またはパスワード ジェスチャを使用して macOS のロックを解除すると、キー バッグのロックが解除され、UserSecureEnclaveKey にアクセスできるようになります。
2. macOS は Microsoft Entra ID に nonce (1 回しか使用できないランダムな任意の数) を要求します。
3. Microsoft Entra ID からは 5 分間有効な nonce が返されます。
4. オペレーティング システム (OS) は、セキュリティで保護されたエンクレーブに存在する UserSecureEnclaveKey で署名された埋め込みアサーションを使用して、Microsoft Entra ID にログイン要求を送信します。
5. Microsoft Entra ID では、ユーザーの安全に登録された UserSecureEnclave キーの公開キーを使用して、署名されたアサーションを検証します。 Microsoft Entra ID は、署名と nonce を検証します。 アサーションが検証されると、Microsoft Entra ID は、登録時に交換される UserDeviceEncryptionKey の公開キーで暗号化[プライマリ更新トークン (PRT)](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token) を作成し、応答を OS に送信します。
6. OS は、応答の暗号化を解除して検証し、SSO トークンを取得した後に格納し、SSO を提供するために SSO 拡張機能と共有します。 ユーザーは、SSO を使用して macOS、クラウド、オンプレミスのアプリケーションにアクセスできます。

macOS プラットフォーム資格情報を構成して展開する方法の詳細については、「[macOS プラットフォーム SSO](https://learn.microsoft.com/ja-jp/entra/identity/devices/macos-psso)」を参照してください。

### SmartCard を使用した macOS プラットフォーム シングル サインオン

macOS プラットフォーム シングル サインオン (PSSO) を使用すると、ユーザーは SmartCard 認証方法を使用してパスワードレスに移動できます。 ユーザーは、外部のスマート カードまたはスマート カードと互換性のあるハードウェア ベースのトークン (Yubikey など) を使用してデバイスにサインインします。 デバイスのロックが解除されると、スマート カードが Microsoft Entra ID と共に使用され、 [証明書ベースの認証 (CBA)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication) を使用した認証に Microsoft Entra ID を使用するアプリ間で SSO が付与されます。 この機能を動作させるには、ユーザーに対して CBA を構成して有効にする必要があります。 CBA を構成する方法の詳細については、「 [Microsoft Entra 証明書ベースの認証を構成する方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-certificate-based-authentication)」を参照してください。

これを有効にするには、管理者が Microsoft Intune またはサポートされているその他のモバイルデバイス管理 (MDM) ソリューションを使用して PSSO を構成する必要があります。

[Image: macOS プラットフォーム SSO を使用したユーザー サインインに関連する手順の概要を示す図]

1. ユーザーは、スマート カードとキー バッグのロックを解除して、セキュリティで保護されたエンクレーブに存在するデバイス登録キーへのアクセスを提供するスマート カード ピンを使用して、macOS のロックを解除します。
2. macOS は Microsoft Entra ID から、1回限り使用できるランダムな数値である nonce を要求します。
3. Microsoft Entra ID は 5 分間有効な nonce を返します。
4. オペレーティング システム (OS) は、スマート カードからユーザーの Microsoft Entra 証明書で署名された埋め込みアサーションを使用して、Microsoft Entra ID にログイン要求を送信します。
5. Microsoft Entra ID は、署名付きアサーション、署名、ノンスを検証します。 アサーションが検証されると、Microsoft Entra ID は、登録時に交換される UserDeviceEncryptionKey の公開キーで暗号化[プライマリ更新トークン (PRT)](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token) を作成し、応答を OS に送信します。
6. OS は、応答の暗号化を解除して検証し、SSO トークンを取得した後に格納し、SSO を提供するために SSO 拡張機能と共有します。 ユーザーは、SSO を使用して macOS、クラウド、オンプレミスのアプリケーションにアクセスできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-authentication-qr-code"} -->
## Microsoft Entra ID の QR コード認証方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-qr-code
- Service: entra-id / authentication
- Article date: 2026-06-26
- Summary: Microsoft Entra ID で QR コード認証方法を使用して、現場担当者のサインイン イベントを改善し、セキュリティで保護する方法について説明します。

QR コード認証方法を使用すると、現場担当者は共有デバイス上のアプリに効率的にサインインできます。 ユーザーは、提供された一意の QR コードを使用して PIN を入力してサインインできるため、複雑なユーザー名とパスワードを入力する必要がありません。 現在、QR コード認証は、iOS/iPadOS または Android を実行するモバイル デバイスでのみサポートされています。

QR コード認証方法を有効にする前に、現場担当者の職場または自宅のアクセスにセキュリティ制御を使用するためのベスト プラクティスを確認してください。 詳細については、「 [現場担当者を保護するためのベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/identity-platform/security-best-practices-for-frontline-workers)」を参照してください。

### QR コード認証とは

QR コード認証は、主に現場担当者向けに設計された簡単な認証方法です。 一意の QR コードと数値 PIN で構成されます。 QR コードは識別子として機能し、ユーザーに固有です。 Microsoft Entra 管理センター、マイ スタッフ、または Microsoft Graph を使用してダウンロードして印刷できます。 便宜上、QRコードはバッジやその他のウェアラブルアイテムに取り付けることができます。

認証管理者は、ユーザーに一時的な PIN を提供し、サインイン時に変更します。 PIN を知っているのはユーザーだけです。 QR コードにのみバインドされます。 ユーザー名や電話番号など、他のユーザー識別子と共に使用することはできません。 QR コード認証は、PIN (知っているもの) が資格情報である単一要素の方法です。

### QR コード認証の利点

| Benefit | Description |
| --- | --- |
| 簡単かつ迅速なサインイン | 現場担当者は、シフトを通じて共有デバイスに複数回サインインするために複雑なユーザー名やパスワードを入力する必要はありません。 |
| Inexpensive | QR コードの印刷コストはハードウェア キーよりも低くなります。これは、一時的な現場担当者を持つ組織ではコストが非常に高い場合があります。 |

#### PIN のプロパティ

認証ポリシー管理者が PIN を作成またはリセットすると、次のポリシーが適用されます。

| Policy | Values |
| --- | --- |
| 使用できる文字 | 数字 (0 - 9) |
| 未割り当て文字 | - 文字 (A ~ Z、a ~ z)- 記号 (- @ # $ % ^ & \* - \_ ! + = [ ] { } |\ : ' , . ? / ' ~ " ( ) ; &lt;&gt;)- Unicode 文字- 空白 |
| PIN の長さ | 8 ~ 20 桁の数字 |
| PIN の複雑さ | 繰り返しと一般的なシーケンスを回避するために適用されます。 次のパターンがチェックされます。- 0123456789または9876543210を含めないでください。- 121212、123123、342342など、PIN に 2 ~ 3 桁のシーケンスを繰り返さないでください。PIN に未承認の文字が含まれているか、PIN の最小長より小さい場合、 **無効な** PIN エラーが表示されます。 |

### QR コード認証を使用して実装するためのベスト セキュリティ プラクティス

単一要素認証 (知っているもの) であるため、QR コード認証方法を有効にする場合は、次の対策をお勧めします。

- QR コード認証は、主に現場担当者 (FLW) 用であり、インフォメーション ワーカー (IW) 用ではありません。 IW にはフィッシングに強い認証または MFA をお勧めします。
- テナント内のすべてのユーザーに対して QR コード認証を有効にしないでください。 この認証方法を使用する対象ユーザーに対してのみ有効にします。たとえば、現場担当者用のグループを作成し、Microsoft Entra 認証方法ポリシーでユーザーに対してのみ QR コード認証を有効にします。
- QR コード認証と条件付きアクセス ポリシーを別のセキュリティ 層として組み合わせます。 準拠デバイス、ネットワーク内のアクセス、特定のアプリケーションの許可、共有デバイス モードなどのポリシーをお勧めします。
- ユーザーがストアまたはワークプレース ネットワークの外部からリソースにアクセスするときに、フィッシングに強い認証または MFA を適用します。
- 紛失または盗難にあった QR コードを置き換えます。
- アクセスをブロックするために [、サインイン リスクベースの条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-policies#sign-in-risk-based-conditional-access-policy) を適用します。

### カスタム認証強度を使用して QR コードサインインを強制する

現場担当者、特定のリソース、またはその両方など、特定のユーザー グループに対して QR コードサインインを要求するには、**単一要素認証**の下に **QR コード**を含むカスタム条件付きアクセス認証強度を作成します。 次に、QR コードサインインが必要なリソースとユーザーの条件付きアクセス ポリシーでカスタム認証強度を使用します。 手順については、「 [カスタム条件付きアクセス認証の強度を作成および管理する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strength-advanced-options#create-a-custom-authentication-strength)」を参照してください。

### 認証方法ポリシーの QR コード構成

認証ポリシー管理者は、Microsoft Entra 管理センターの認証方法で QR コードを有効にすることができます。 QR コード認証は既定で無効になっています。

QR コードの認証方法ポリシーでは、次の構成を行うことができます。

- PIN の長さ: 8 ~ 20 桁。
- 標準 QR コードの有効期間: 1 ~ 395 日。 既定値は 365 日です。 認証ポリシー管理者は、ユーザーの標準 QR コードを追加するときに既定値を変更できます。

    たとえば、管理者は認証方法ポリシーの値を 30 日に設定できます。 そのテナント内のすべてのユーザーに対して、標準 QR コードの既定の有効期限は 30 日です。 管理者は、特定のユーザーの標準 QR コードの既定の有効期間を変更できます。

このスクリーンショットでは、PIN の長さが既定値の 8 桁に設定されています。 標準 QR コードの有効期間は 200 日に短縮されます。

[Image: QR コードの設定を示すスクリーンショット。]

### QR コード認証方法の機能の詳細

認証ポリシー管理者がユーザーの QR コード認証方法を追加すると、標準の QR コードと PIN が生成されます。 一時的な QR コードを作成するには、QR コードの認証方法を編集する必要があります。

一時的な QR コードは、ユーザーが標準の QR コードでバッジを持ち込むのを忘れた場合に役立ちます。 有効期間が短く、最大 12 時間です。 ユーザーの QR コード認証方法が削除された場合、ユーザーは既存の QR コードと PIN でサインインできません。

PIN は標準の QR コードと一時的な QR コードの両方で機能します。これは、PIN が QR コード認証方法に対して有効であるためです。 認証ポリシー管理者は、QR コード認証方法を作成するときに、カスタム PIN を指定したり、PIN を生成したりすることができます。 一時的な PIN は、生成されたときにのみコピーできます。 その後、PIN は露出を防ぐためにマスクされます。

標準の QR コード、一時的な QR コード、QR コード認証方法の PIN の使いやすさの状態は、相互に関連していません。 たとえば、アクティブな QR コード認証方法では、標準 QR コードとアクティブな一時 QR コードを削除または期限切れにすることができます。 任意の時点で、アクティブな標準 QR コードとアクティブな一時 QR コードは 1 つだけです。

次の表に、標準 QR コード、一時 QR コード、PIN の状態の組み合わせの例を示します。 認証を成功させるには、アクティブ QR コードとアクティブ PIN が必要です。

| 標準 QR コード | 一時 QR コード | QR コード認証方法の PIN |
| --- | --- | --- |
| Active | 存在しない | 一時的かユーザーにより更新される |
| Active | Active | 一時的かユーザーにより更新される |
| Deleted | 存在しない | 一時的かユーザーにより更新される |
| Expired | Active | 一時的かユーザーにより更新される |
| Expired | Expired | 一時的かユーザーにより更新される |

QR コードを管理する方法の詳細については、「 [Microsoft Entra ID で QR コード認証方法を有効にする方法」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-qr-code)参照してください。

### QR コード認証を使用したユーザー サインイン エクスペリエンス

ユーザーは、Web サインイン エクスペリエンスまたは最適化されたアプリ サインイン エクスペリエンスを使用して、QR コードでサインインできます。

#### モバイル Web サインイン エクスペリエンス

Microsoft の Web ブラウザーのサインイン エクスペリエンス (login.microsoft.com) を使用して、ユーザーを認証できます。 ユーザーは、[**サインイン オプション**] をクリック&gt;**組織にサインイン**&gt;**QR コードでサインイン**します。

[Image: Web サインイン エクスペリエンスを示すスクリーンショット。]

#### モバイル アプリのサインイン エクスペリエンス

Microsoft Authentication Library (MSAL) を使用してアプリのサインインを最適化し、サインイン ページで QR コードをオプションとして追加できます。 その後、ユーザーは 2 回のクリックで QR コードをスキャンできます。 この最適化されたサインイン エクスペリエンスは、BlueFletch および Jamf アプリ起動ツールで利用できます。

サインイン エクスペリエンスを最適化する方法、またはカメラの同意プロンプトを表示しない方法の詳細については、次を参照してください。

- [Android アプリで最適化された QR コード認証エクスペリエンスを設定する](https://learn.microsoft.com/ja-jp/entra/identity-platform/android-qr-code-pin-authentication)
- [iOS アプリで最適化された QR コード認証エクスペリエンスを設定する](https://learn.microsoft.com/ja-jp/entra/identity-platform/ios-qr-code-pin-authentication)

[Image: Teams のサインイン エクスペリエンスを示すスクリーンショット。]

### 現在のリリースでサポートされていないユーザー シナリオ

- ユーザーのセルフサービス PIN リセット
- QR コードと PIN の一括プロビジョニング
- バーコード スキャナーによる QR コード スキャン
- QR コード認証がデスクトップ アプリまたはブラウザーで機能しない
- サインイン用のカスタム テナント エンドポイント
- アカウント ロックアウトのしきい値、期間、または PIN の複雑さを定義する構成可能な PIN 保護ポリシー

### 既知の制限

ユーザーに対して QR コード認証を有効にした場合、ユーザーが初めて QR コードでサインインする前に、既存の認証方法でサインインする必要があります。そうしないと、 **正しくない QR コード** エラーが表示されます。

例えば次が挙げられます。

- ユーザーに対して QR コード認証を有効にします。
- ユーザーは、自分のパスワードまたは別のサインイン方法でサインインする必要があります。
- 以降のサインインでは、QR コードを使用してサインインできます。

キャッシュされたユーザー認証方法ポリシーは、ユーザーが再認証されるまで更新されないため、ユーザーは別の方法でサインインする必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-authentication-security-questions"} -->
## セキュリティ質問認証方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-security-questions
- Service: entra-id / authentication
- Article date: 2026-02-18
- Summary: Microsoft Entra ID でセキュリティの質問を使用してサインイン イベントの改善とセキュリティ保護を行う方法について説明します

セキュリティの質問は、サインイン イベント中に認証方法として使用されません。 代わりに、セルフサービス パスワード リセット (SSPR) プロセス中にセキュリティの質問を使用して、ユーザーを確認できます。 管理者アカウントでは、SSPR の検証方法としてセキュリティの質問を使用することはできません。

Warnung

**セキュリティに関する質問は、2027 年 3 月にセルフサービス パスワード リセット (SSPR) で廃止される予定です。** その日を過ぎると、ユーザーはセキュリティの質問を使用してパスワードをリセットできなくなります。 ユーザーが認証方法ポリシーで [サポートされている認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr#select-authentication-methods-and-registration-options) で設定されていることを確認します。

この機能は、セキュリティ リスクと信頼性が低いため、非推奨とされています。 セキュリティの質問は、多くの場合、推測可能であるか、ソーシャル エンジニアリングの影響を受けやすく、SSPR 中にアカウントの引き継ぎのリスクが高まります。 より強力な検証方法により、セキュリティが向上し、リセットエラーが減り、エスカレーションがサポートされます。

2027 年 3 月に適用が開始されると、ユーザー ロックアウト、ヘルプデスクのエスカレーション、およびパスワード リセットの失敗エクスペリエンスが発生しないように、事前に準備します。

ユーザーが SSPR に登録すると、使用する認証方法を選択するように求められます。 セキュリティの質問を使用することを選択した場合は、表示される一連の質問を選択して、その回答を入力します。

[Image: セキュリティに関する質問の認証方法とオプションを示す Microsoft Entra 管理センターのスクリーンショット]

手記

セキュリティの質問は、ディレクトリ内のユーザー オブジェクトにプライベートかつ安全に保存され、登録時にのみユーザーが回答できます。 管理者がユーザーの質問や回答を読んだり変更したりする方法はありません。

セキュリティの質問は、他のユーザーの質問に対する回答を知っている人もいるため、他の方法よりも安全性が低い場合があります。 SSPR でセキュリティの質問を使用する場合は、別の方法と共に使用することをお勧めします。 ユーザーは、SSPR プロセス中に Microsoft Authenticator アプリまたは電話認証を使用して ID を確認し、電話または登録されたデバイスがない場合にのみセキュリティの質問を選択するように求められます。

### 定義済みの質問

次の定義済みのセキュリティの質問は、SSPR での検証方法として使用できます。 これらのセキュリティの質問はすべて、ユーザーのブラウザー ロケールに基づいて、Microsoft 365 言語の完全なセットに翻訳およびローカライズされます。

- 最初の配偶者/パートナーと出会った都市は何ですか?
- 両親はどの都市で会いましたか。
- 最寄りの兄弟はどの都市に住んでいますか?
- お父さんが生まれた都市は何ですか?
- あなたの最初の仕事は何の都市でしたか?
- お母さんが生まれた都市は?
- 2000年の新年にはどのような都市にいましたか?
- 高校のお気に入りの先生の姓は何ですか?
- 申請したが、出席しなかった大学の名前は何ですか?
- 初めての結婚披露宴を開催した場所の名前は何ですか?
- お父さんのミドルネームは?
- あなたの好きな食べ物は何ですか?
- 母方のおばあちゃんの姓は何ですか?
- お母さんのミドルネームは?
- 最年長の兄弟の誕生日の月と年は何ですか? (例: 1985 年 11 月)
- 最も古い兄弟のミドル ネームは何ですか?
- 父方の祖父の名前と姓は何ですか?
- 一番若い兄弟のミドルネームは?
- 6年生は何の学校に通いましたか?
- 幼なじみの名と姓は何でしたか?
- あなたの最初の恋人の名前と名字は何でしたか?
- お気に入りの小学校の先生の姓は何でしたか?
- あなたの最初の車やバイクのメーカーとモデルは何でしたか?
- 最初に通った学校の名前は何でしたか?
- あなたが生まれた病院の名前は何でしたか?
- 最初の子供時代の家の通りの名前は何でしたか?
- 子供の頃のヒーローの名前は何でしたか?
- お気に入りのぬいぐるみの名前は何でしたか?
- 最初のペットの名前は何でしたか?
- 子供の頃のニックネームは何でしたか?
- 高校で好きなスポーツは何でしたか?
- 最初の仕事は何でしたか?
- 子供の頃の電話番号の最後の 4 桁は何でしたか?
- 若い頃、あなたは成長したときに何になりたいと思っていましたか?
- あなたが今まで会った中で最も有名な人は誰ですか?

### カスタム セキュリティに関する質問

柔軟性を高めるために、独自のカスタム セキュリティの質問を定義できます。 カスタム セキュリティの質問の最大長は 200 文字です。

カスタム セキュリティの質問は、既定のセキュリティの質問と同様に自動的にローカライズされません。 すべてのカスタム質問は、ユーザーのブラウザーロケールが異なる場合でも、管理ユーザー インターフェイスに入力されたのと同じ言語で表示されます。 ローカライズされた質問が必要な場合は、定義済みの質問を使用する必要があります。

### セキュリティの質問の要件

既定とカスタムの両方のセキュリティの質問には、次の要件と制限事項が適用されます。

- 回答の最小文字数制限は 3 文字です。
- 最大応答文字の制限は 40 文字です。
- ユーザーが同じ質問に複数回回答することはできません。
- ユーザーは、複数の質問に対して同じ回答を提供することはできません。
- 任意の文字セットを使用して、Unicode 文字を含む質問と回答を定義できます。
- 定義された質問の数は、登録に必要な質問の数以上である必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-authentication-strength-advanced-options"} -->
## カスタム条件付きアクセス認証の強度を作成および管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strength-advanced-options
- Service: entra-id / authentication
- Article date: 2026-06-26
- Summary: 管理者がパスキー (FIDO2) セキュリティ キーと証明書ベースの認証の高度なオプションを使用してカスタム認証の強度を作成する方法について説明します。

認証強度は、リソースにアクセスするための認証方法の組み合わせを指定する Microsoft Entra 条件付きアクセス制御です。 管理者は、要件に正確に合わせて最大 15 個のカスタム認証強度を作成できます。

### [前提条件]

- 条件付きアクセスを使用するには、テナントに Microsoft Entra ID P1 ライセンスが必要です。 このライセンスをお持ちでない場合は、 [無料試用版](https://www.microsoft.com/security/business/get-started/start-free-trial)を開始できます。

### カスタム認証強度を作成する

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **Entra ID**&gt;**認証メソッド**&gt;**認証の強度**を表示します。
3. [ **新しい認証強度**] を選択します。
4. **[名前]** に、新しい認証強度のわかりやすい名前を指定します。
5. **[説明]** には、省略可能な説明を指定できます。
6. 許可する使用可能なメソッドを選択します。 たとえば、現場担当者などのユーザーグループに QR コード サインインを必要とするカスタム認証強度を作成するには、[ **単一要素認証**] を展開して、[ **QR コード**] を選択します。
7. [ **次へ** ] を選択し、ポリシーの構成を確認します。

[Image: QR コードが選択されたカスタム認証強度を示すスクリーンショット。]

### カスタム認証の強度を更新および削除する

カスタム認証強度を編集できます。 条件付きアクセス ポリシーがその認証強度を参照している場合は、削除できないため、編集を確認する必要があります。

条件付きアクセス ポリシーが認証強度を参照しているかどうかを確認するには、[ **条件付きアクセス ポリシー** ] 列に移動します。

### パスキーの詳細オプションの構成 (FIDO2)

パスキー (FIDO2) の使用は、Authenticator 構成証明 GUID (AAGUID) に基づいて制限できます。 この機能を使用して、リソースへのアクセスに特定の製造元の FIDO2 セキュリティ キーを要求できます。

1. カスタム認証強度を作成したら、 **Passkeys (FIDO2)**&gt;**Advanced オプションを選択します**。

    [Image: パスキーの詳細オプションのリンクを示すスクリーンショット。]
2. [ **AAGUID の追加]** の横にあるプラス記号 (**+**) を選択し、AAGUID 値をコピーして、[ **保存]** を選択します。

    [Image: 認証子構成証明 GUID を追加する方法を示すスクリーンショット。]

### 証明書ベースの認証の詳細オプションを構成する

[認証バインド ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-certificate-based-authentication#step-3-configure-an-authentication-binding-policy)では、証明書の発行者またはポリシー オブジェクト識別子 (OID) に基づいて、証明書を単一要素認証保護レベルまたは多要素認証保護レベルにバインドするかどうかを構成できます。 条件付きアクセス認証強度ポリシーに基づいて、特定のリソースに対して単一要素認証証明書または多要素認証証明書を要求することもできます。

認証強度の詳細オプションを使用すると、アプリケーションへのサインインをさらに制限するために、特定の証明書発行者またはポリシー OID を要求できます。

たとえば、Contoso という名前の組織が、3 種類の多要素証明書を持つ従業員にスマート カードを発行するとします。 1 つの証明書は機密のクリアランス用で、もう 1 つは秘密のクリアランス用で、3 つ目は極秘のクリアランス用です。 各プロパティは、発行者やポリシー OID などの証明書のプロパティによって区別されます。 Contoso は、適切な多要素証明書を持つユーザーのみが分類ごとにデータにアクセスできるようにしたいと考えています。

次のセクションでは、Microsoft Entra 管理センターと Microsoft Graph を使用して、証明書ベースの認証 (CBA) の詳細オプションを構成する方法について説明します。

#### Microsoft Entra 管理センター

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に管理者としてサインインします。
2. **Entra ID**&gt;**認証メソッド**&gt;**認証の強度**を表示します。
3. [ **新しい認証強度**] を選択します。
4. **[名前]** に、新しい認証強度のわかりやすい名前を指定します。
5. **[説明]** には、省略可能な説明を指定できます。
6. 証明書ベースの認証 (単一要素または多要素) のオプションの下にある [ **詳細オプション**] を選択します。

    [Image: 証明書ベースの認証の詳細オプションのリンクを示すスクリーンショット。]
7. 証明書の発行者を選択または入力し、許可されているポリシー OID を入力します。

    証明書発行者を構成するには、**テナントの証明機関から証明書発行者を選択**するドロップダウン リストを使用します。 ドロップダウン リストには、テナントのすべての証明機関 (単一要素か多要素か) が表示されます。

    使用する証明書がテナントの証明機関にアップロードされないシナリオでは、[ **SubjectkeyIdentifier によるその他の証明書発行者** ] ボックスに証明書発行者を入力できます。 このような例の 1 つは、ユーザーがホーム テナントで認証され、認証強度がリソース テナントに適用されている外部ユーザー シナリオです。

    [Image: 証明書の発行者とポリシー オブジェクト識別子の構成オプションを示すスクリーンショット。]

    次の条件が適用されます。

    - 両方の属性 (証明書発行者とポリシー OID) を構成する場合、ユーザーは、認証強度を満たすために、少なくとも 1 つの発行者 *と* 一覧のポリシー OID を持つ証明書を使用する必要があります。
    - 証明書発行者属性のみを構成する場合、ユーザーは認証強度を満たすために、少なくとも 1 つの発行者を持つ証明書を使用する必要があります。
    - ポリシー OID 属性のみを構成する場合、ユーザーは認証強度を満たすために、ポリシー OID の少なくとも 1 つを持つ証明書を使用する必要があります。

    注

    認証強度には、最大 5 つの発行者と 5 つの OID を構成できます。
8. [ **次へ** ] を選択して構成を確認し、[ **作成**] を選択します。

#### Microsoft Graph

証明書の `combinationConfigurations` を使用して新しい条件付きアクセス認証強度ポリシーを作成するには、次のコードを使用します。

```json
POST  /beta/identity/conditionalAccess/authenticationStrength/policies
{
    "displayName": "CBA Restriction",
    "description": "CBA Restriction with both IssuerSki and OIDs",
    "allowedCombinations": [
        " x509CertificateMultiFactor "
    ],
    "combinationConfigurations": [
        {
            "@odata.type": "#microsoft.graph.x509CertificateCombinationConfiguration",
            "appliesToCombinations": [
                "x509CertificateMultiFactor"
            ],
            "allowedIssuerSkis": ["9A4248C6AC8C2931AB2A86537818E92E7B6C97B6"],
            "allowedPolicyOIDs": [
                "1.2.3.4.6",
                "1.2.3.4.5.6"
            ]
        }
    ]
}
```

既存のポリシーに新しい `combinationConfiguration` 情報を追加するには、次のコードを使用します。

```json
POST beta/identity/conditionalAccess/authenticationStrength/policies/{authenticationStrengthPolicyId}/combinationConfigurations

{
    "@odata.type": "#microsoft.graph.x509CertificateCombinationConfiguration",
    "allowedIssuerSkis": [
        "9A4248C6AC8C2931AB2A86537818E92E7B6C97B6"
    ],
    "allowedPolicyOIDs": [],
    "appliesToCombinations": [
        "x509CertificateSingleFactor "
    ]
}
```

### 制限事項を理解する

#### パスキーの詳細オプション (FIDO2)

ホーム テナントとリソース テナントが異なる Microsoft クラウドに配置されている外部ユーザーでは、パスキー (FIDO2) の詳細オプションはサポートされていません。

#### 証明書ベースの認証の詳細オプション

- ユーザーは、各ブラウザー セッションで 1 つの証明書のみを使用できます。 ユーザーが証明書を使用してサインインすると、セッション中はブラウザーにキャッシュされます。 認証強度の要件を満たしていない場合、ユーザーは別の証明書を選択するように求められません。 ユーザーはサインアウトしてサインインし直してセッションを再開し、関連する証明書を選択する必要があります。
- 証明機関とユーザー証明書は、X.509 v3 標準に準拠している必要があります。 具体的には、発行者のサブジェクト キー識別子 (SKU) に CBA 制限を適用するには、証明書に有効な機関キー識別子 (AKIs) が必要です。

    [Image: 機関キー識別子を示すスクリーンショット。]

    注

    証明書が準拠していない場合、ユーザー認証は成功する可能性がありますが、認証強度ポリシーの発行者 SKI 制限を満たしていない可能性があります。
- サインイン時に、Microsoft Entra ID はユーザー証明書からの最初の 5 つのポリシー OID を考慮し、認証強度ポリシーで構成されたポリシー OID と比較します。 ユーザー証明書に 5 つ以上のポリシー OID がある場合、Microsoft Entra ID では、認証強度要件に一致する最初の 5 つのポリシー OID (字句順) が考慮されます。
- 企業間ユーザーの場合、Contoso が別の組織 (Fabrikam) のユーザーをテナントに招待する例を見てみましょう。 この場合、Contoso はリソース テナントであり、Fabrikam はホーム テナントです。 アクセスは、テナント間のアクセス設定によって異なります。

    - クロステナント アクセス設定が **[オフ]** の場合、Contoso はホーム テナントが実行した MFA を受け入れないことを意味します。 リソース テナントに対する証明書ベースの認証はサポートされていません。
    - テナント間アクセス設定が **[オン]**の場合、Fabrikam テナントと Contoso テナントは同じ Microsoft クラウド (Azure 商用クラウド プラットフォームまたは米国政府機関向け Azure クラウド プラットフォーム) 上にあります。 さらに、Contoso はホーム テナントで実行された MFA を信頼します。 この場合、次のようになります。
        - 管理者は、カスタム認証強度ポリシーのポリシー OID または **SubjectkeyIdentifier によるその他の証明書発行者** 設定を使用して、特定のリソースへのアクセスを制限できます。
        - 管理者は、カスタム認証強度ポリシーの **[SubjectkeyIdentifier によるその他の証明書発行者** ] 設定を使用して、特定のリソースへのアクセスを制限できます。
    - テナント間アクセス設定が **[オン]** の場合、Fabrikam と Contoso は同じ Microsoft クラウド上にありません。 たとえば、Fabrikam のテナントは Azure 商用クラウド プラットフォーム上にあり、Contoso のテナントは米国政府向け Azure クラウド プラットフォーム上にあります。 管理者は、カスタム認証強度ポリシーの発行者 ID またはポリシー OID を使用して、特定のリソースへのアクセスを制限することはできません。

### 認証の強度に関する高度なオプションのトラブルシューティング

#### ユーザーがパスキー (FIDO2) を使用してサインインできない

条件付きアクセス管理者は、特定のセキュリティ キーへのアクセスを制限できます。 ユーザーが使用できないキーでサインインしようとすると、"ここからアクセスできません" というメッセージが表示されます。 ユーザーはセッションを再起動し、別のパスキー (FIDO2) でサインインする必要があります。

[Image: ユーザーが制限付きパスキーを使用している場合のサインイン エラーのスクリーンショット。]

#### 証明書の発行者またはポリシー OID を確認する必要がある

個人証明書のプロパティが、認証強度の詳細オプションの構成と一致することを確認できます。

1. ユーザーのデバイスで、管理者としてサインインします。
2. [ **実行**] を選択し、「 **certmgr.msc**」と入力し、Enter キーを 押 します。
3. **[個人用**&gt;**Certificates**] を選択し、証明書を右クリックし、[**詳細**] タブに移動します。

[Image: 証明書の発行者またはポリシー OID を確認するための選択を示すスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-authentication-strength-external-users"} -->
## 外部ユーザーの条件付きアクセス認証の強度のしくみ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strength-external-users
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: 管理者が Microsoft Entra ID で外部ユーザーの認証強度要件を使用する方法について説明します。

認証の強度は、組織内の機密性の高いアプリへの外部アクセスを制限する場合に特に便利です。 外部ユーザーに対して、フィッシングに強い方法などの特定の認証方法を適用できます。

条件付きアクセス認証の強度ポリシーを外部の Microsoft Entra ユーザーに適用すると、ポリシーはクロステナント アクセス設定の多要素認証 (MFA) 信頼設定と連携して、外部ユーザーが MFA を実行する必要がある場所と方法を決定します。 Microsoft Entra ユーザーは、ホームの Microsoft Entra テナントで認証を行います。 ユーザーがリソースにアクセスすると、Microsoft Entra ID によってポリシーが適用され、MFA 信頼が有効になっているかどうかを確認します。

注

MFA 信頼の有効化は、企業間 (B2B) コラボレーションでは省略可能ですが、*B2B 直接接続*には[必要](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-direct-connect-overview#multifactor-authentication-mfa)です。

外部ユーザー シナリオでは、ユーザーがホーム テナントとリソース テナントのどちらで MFA を完了しているかによって、認証の強度を満たす認証方法が異なります。 次の表では、各テナントで許容される方法を示します。 リソース テナントが外部の Microsoft Entra 組織からの要求を信頼することを選択した場合、MFA のリソース テナントは、テーブルの [ホーム テナント] 列に一覧表示されている要求のみを受け入れます。 リソース テナントが MFA の信頼を無効にする場合、外部ユーザーは、リソース テナントの [リソース テナント] 列に記載されているいずれかの方法を使用して MFA を完了する必要があります。

| 認証方法 | ホーム テナント | リソース テナント |
| --- | --- | --- |
| 第 2 要素としてのテキスト メッセージ | ✅ | ✅ |
| 音声通話 | ✅ | ✅ |
| Microsoft Authenticator プッシュ通知 | ✅ | ✅ |
| Microsoft Authenticator の電話によるサインイン | ✅ |  |
| OATH ソフトウェア トークン | ✅ | ✅ |
| OATH ハードウェア トークン | ✅ |  |
| FIDO2 セキュリティ キー | ✅ |  |
| Windows Hello for Business | ✅ |  |
| 証明書ベースの認証 | ✅ |  |

外部ユーザーの認証強度を設定する方法の詳細については、「外部ユーザーに [多要素認証の強度を要求する」](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-guests-mfa-strength)を参照してください。

### 外部ユーザーのユーザー エクスペリエンス

条件付きアクセス認証の強度ポリシーは、テナント間アクセス [設定の MFA 信頼設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration) と連携します。 まず、Microsoft Entra ユーザーがホーム テナント内の自分のアカウントで認証を行います。 ユーザーがリソースにアクセスしようとすると、Microsoft Entra ID によって条件付きアクセス認証の強度ポリシーが適用され、MFA 信頼が有効になっているかどうかを確認します。

- **MFA 信頼が有効になっている場合**: Microsoft Entra ID は、ユーザーのホーム テナントで MFA が満たされたことを示す要求について、ユーザーの認証セッションを確認します。 外部ユーザーのホーム テナントで完了したときに MFA に許容される認証方法については、前の表を参照してください。

    ユーザーのホーム テナントで MFA ポリシーが既に満たされていることを示す要求がセッションに含まれていて、その方法が認証強度要件を満たしている場合、ユーザーはアクセスを許可されます。 それ以外の場合、Microsoft Entra ID は、受け入れ可能な認証方法を使用してホーム テナントで MFA を完了するチャレンジをユーザーに提示します。
- **MFA 信頼が無効になっている場合**: Microsoft Entra ID は、許容可能な認証方法を使用して、リソース テナントで MFA を完了するためのチャレンジをユーザーに提示します。 外部ユーザーが MFA に使用できる認証方法については、前の表を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-authentication-strength-how-it-works"} -->
## 条件付きアクセス ポリシーでの認証の強度のしくみ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strength-how-it-works
- Service: entra-id / authentication
- Article date: 2025-03-17
- Summary: 管理者が条件付きアクセス ポリシーを使用して、リソースにアクセスするために特定の認証の組み合わせを要求する方法について説明します。

この記事では、Microsoft Entra 条件付きアクセスの認証強度によって、サインインとリソースへのアクセスに使用できる認証方法の組み合わせを制限する方法について説明します。

### 認証方法のポリシーで認証強度がどのように機能するか

リソースへのアクセスに使用できる認証方法は、2 つのポリシーによって決まります。 いずれかのポリシーで認証方法を有効にしたユーザーは、その方法を使用してサインインできます。

- **[セキュリティ]**&gt;**[認証方法]**&gt;**[ポリシー]** は、特定のユーザーやグループの認証方法を管理するための、より新しい方法です。 メソッドのユーザーとグループを指定できます。 メソッドの使用方法を制御するパラメーターを構成することもできます。

    [Image: 認証方法の [ポリシー] ページのスクリーンショット。]
- **セキュリティ**&gt;**多要素認証**&gt;**追加のクラウドベースの多要素認証設定** は、テナント内のすべてのユーザーの多要素認証 (MFA) メソッドを制御する従来の方法です。

    [Image: 多要素認証のサービス設定のスクリーンショット。]

ユーザーは、有効になっている認証方法に登録できます。 管理者は、証明書ベースの認証などの方法を使用して、ユーザーのデバイスを構成することもできます。

### サインイン時の認証強度ポリシーの評価方法

条件付きアクセス認証強度ポリシーは、ユーザーが使用できる認証方法を定義します。 Microsoft Entra ID は、サインイン中にポリシーをチェックして、リソースへのユーザーのアクセスを決定します。

たとえば、管理者は、パスキー (FIDO2 セキュリティ キー) またはパスワードとテキスト メッセージの組み合わせを必要とするカスタム認証強度を持つ条件付きアクセス ポリシーを構成します。 ユーザーは、このポリシーが保護に役立つリソースにアクセスします。

サインイン時に、Microsoft Entra ID はすべての設定をチェックして、許可されるメソッド、登録されるメソッド、条件付きアクセス ポリシーに必要なメソッドを決定します。 サインインを成功させるには、メソッドを許可し、(アクセス要求の前または一部として) ユーザーが登録し、認証強度を満たす必要があります。

### 複数の認証強度ポリシーの評価方法

一般に、サインインに複数の条件付きアクセス ポリシーが適用される場合、ユーザーはすべてのポリシーのすべての条件を満たす必要があります。 同様に、複数の条件付きアクセス認証強度ポリシーがサインインに適用される場合、ユーザーはすべての認証強度条件を満たす必要があります。

たとえば、2 つの認証強度ポリシーの両方にパスキー (FIDO2) が必要な場合、ユーザーは FIDO2 セキュリティ キーを使用して両方のポリシーを満たすことができます。 2 つの認証強度ポリシーの一連の方法が異なる場合、ユーザーは複数の方法を使用して、両方のポリシーを満たす必要があります。

#### セキュリティ情報を登録するために複数の認証強度ポリシーを評価する方法

セキュリティ情報登録の [割り込みモード](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-registration-mfa-sspr-combined#interrupt-mode) の場合、認証の評価は異なる方法で処理されます。 **[セキュリティ情報の登録**] のユーザー アクションを対象とする認証強度は、**すべてのリソース** (以前のすべての**クラウド アプリ**) を対象とする他の認証強度ポリシーよりも優先されます。 サインインのスコープ内にある他のすべての条件付きアクセス ポリシーからの他のすべての許可制御 ( **デバイスを準拠としてマークする必要**があるなど) は、通常どおり適用されます。

たとえば、組織は、ユーザーが常に MFA メソッドを使用して、準拠しているデバイスからサインインするように要求するとします。 また、組織では、一時アクセス パス (TAP) を使用して、新しい従業員がこれらの MFA メソッドを登録できるようにしたいと考えています。 ユーザーは他のリソースで TAP を使用できません。 この目標を達成するために、管理者は次の手順を実行できます。

1. TAP 認証の組み合わせを含む **Bootstrap と Recovery** という名前のカスタム認証強度を作成します。 また、MFA メソッドを含めることもできます。
2. TAP を使用せずに、許可されているすべての MFA メソッドを含む **サインイン用** の MFA という名前のカスタム認証強度を作成します。
3. **すべてのリソース** (旧称**すべてのクラウド アプリ**) を対象とする条件付きアクセス ポリシーを作成し、**サインイン認証の**強度に MFA と**準拠デバイス**許可制御を要求する両方を必要とします。
4. **[セキュリティ情報の登録]** ユーザー アクションを対象とし、**ブートストラップと回復**の認証強度を必要とする条件付きアクセス ポリシーを作成します。

その結果、準拠しているデバイスのユーザーは TAP を使用して MFA メソッドを登録できます。 その後、新しく登録された方法を使用して、Outlook などの他のリソースに対する認証を行うことができます。

メモ

- 複数の条件付きアクセス ポリシーが **[セキュリティ情報の登録]** ユーザー アクションを対象とし、それぞれが認証強度を適用する場合、ユーザーはサインインするためにこれらすべての認証強度を満たす必要があります。
- 一部のパスワードレスおよびフィッシングに対する耐性のある方法は、割り込みモードから登録できません。 詳細については、この記事 で後述するパスワードレス認証方法の登録 を参照してください。

### ユーザー エクスペリエンス

次の要素により、ユーザーがリソースにアクセスできるかどうかが決定されます。

- ユーザーが以前に使用した認証方法はどれですか?
- 認証強度に使用できる方法はどれか?
- 認証方法のポリシーでユーザーのサインインを許可する方法はどれですか?
- ユーザーは使用可能な方法に登録されているか?

ユーザーが条件付きアクセス認証強度ポリシーによって保護されたリソースにアクセスすると、Microsoft Entra ID は、ユーザーが以前に使用したメソッドが認証強度を満たしているかどうかを評価します。 ユーザーが満足のいく方法を使用した場合、Microsoft Entra ID はリソースへのアクセスを許可します。

たとえば、ユーザーがパスワードとテキスト メッセージの組み合わせを使用してサインインするとします。 ユーザーは、MFA 認証強度によって保護されたリソースにアクセスします。 この場合、ユーザーは別の認証プロンプトなしでリソースにアクセスできます。

次に、同じユーザーがフィッシングに強い MFA 認証強度によって保護されたリソースにアクセスするとします。 この時点で、ユーザーは、Windows Hello for Business などのフィッシングに強い認証方法を提供するように求められます。

ユーザーが認証強度を満たす方法に登録しなかった場合は、 [統合された登録](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-registration-mfa-sspr-combined#interrupt-mode)にリダイレクトされます。

ユーザーは、認証強度要件を満たす認証方法を 1 つだけ登録する必要があります。 認証強度にユーザーが登録して使用できる方法が含まれていない場合、Microsoft Entra ID はユーザーがリソースにサインインするのをブロックします。

#### パスワードレス認証方法の登録

次の認証方法は、統合登録の割り込みモードの一部として登録できません。 サインインにこれらの方法を使用することを要求できる条件付きアクセス ポリシーを適用する前に、ユーザーがこれらに登録されていることを確認します。 これらのメソッドに登録されていないユーザーは、必要なメソッドが登録されるまでリソースにアクセスできません。

| 方法 | 登録の要件 |
| --- | --- |
| [Microsoft Authenticator (電話によるサインイン)](https://support.microsoft.com/account-billing/add-your-work-or-school-account-to-the-microsoft-authenticator-app-43a73ab5-b4e8-446d-9e54-2a4cb8e4e93c) | Authenticator アプリから登録できます。 |
| [Passkey (FIDO2)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key) | [統合登録のマネージド モードを](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-registration-mfa-sspr-combined#manage-mode)経由して登録し、[統合登録の割り込みモード](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-registration-mfa-sspr-combined#interrupt-mode)を通じて認証強度によって適用できます。 |
| [証明書ベースの認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication) | 管理者のセットアップが必要です。 ユーザーは登録できません。 |
| [Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-prepare-people-to-use) | Windows Out of Box Experience (OOBE) または Windows **設定** メニューに登録できます。 |

#### フェデレーション ユーザー エクスペリエンス

フェデレーション ドメインの場合、管理者は Microsoft Entra 条件付きアクセスを使用するか、オンプレミスのフェデレーション プロバイダーの `federatedIdpMfaBehavior` を設定して MFA を適用できます。 `federatedIdpMfaBehavior`が `enforceMfaByFederatedIdp` に設定されている場合、ユーザーはフェデレーション ID プロバイダーで認証する必要があり、認証強度要件の*フェデレーション多要素*の組み合わせのみを満たすことができます。 フェデレーション設定の詳細については、「 [フェデレーションからクラウド認証への移行](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/migrate-from-federation-to-cloud-authentication)」を参照してください。

フェデレーション ドメインのユーザーが段階的ロールアウトのスコープに MFA 設定を持っている場合、ユーザーはクラウドで多要素認証を完了し、*フェデレーション単一要素にユーザーが保有する追加要素*の組み合わせ条件を満たすことができます。 段階的なロールアウトの詳細については、「段階的な [ロールアウトを有効にする」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-server-migration-utility#enable-staged-rollout)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-authentication-strengths"} -->
## 条件付きアクセス認証の長所の概要 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strengths
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: 管理者が Microsoft Entra 条件付きアクセスを使用して、関連するセキュリティ要因に基づいてユーザーが使用できる認証方法を区別する方法について説明します。

認証強度は、ユーザーがリソースへのアクセスに使用できる認証方法の組み合わせを指定する Microsoft Entra 条件付きアクセス制御です。 ユーザーは、許可されている組み合わせのいずれかを使って認証を行うことで、強度要件を満たすことができます。

たとえば、認証強度では、機密性の高いリソースにアクセスするために、フィッシングに強い認証方法のみを使用するようにユーザーに要求できます。 無知なリソースにアクセスするために、管理者は、パスワードやテキスト メッセージなど、安全性の低い多要素認証 (MFA) の組み合わせを可能にする別の認証強度を作成できます。

認証強度は、 [認証方法のポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)に基づいています。 つまり、管理者は、Microsoft Entra ID フェデレーション アプリケーション全体で使用する特定のユーザーとグループの認証方法のスコープを設定できます。 認証強度により、機密性の高いリソース アクセス、ユーザー リスク、場所などの特定のシナリオに基づいて、これらの方法の使用方法をさらに制御できます。

### 前提条件

- 条件付きアクセスを使用するには、テナントに Microsoft Entra ID P1 ライセンスが必要です。 このライセンスをお持ちでない場合は、 [無料試用版](https://www.microsoft.com/security/business/get-started/start-free-trial)を開始できます。

### 認証強度のシナリオ

認証強度を使用すると、お客様が次のようなシナリオに対処するのに役立ちます。

- 機密リソースにアクセスするために、特定の認証方法を要求します。
- ユーザーがアプリケーション内で機密アクションを実行するときに、特定の認証方法を要求します (条件付きアクセス認証コンテキストとの組み合わせ)。
- 企業ネットワークの外部にある機密性の高いアプリケーションにアクセスする場合は、ユーザーに特定の認証方法の使用を要求します。
- リスクの高いユーザーに、より安全な認証方法を要求します。
- リソース テナントにアクセスするゲスト ユーザーに、特定の認証方法を要求します (テナント間の設定との組み合わせ)。

### 組み込みおよびカスタム認証の強度

管理者は、**[認証強度が必要]** コントロールで条件付きアクセス ポリシーを作成することで、リソースにアクセスするための認証強度を指定できます。 3 つの組み込み認証強度 (**多要素認証強度**、**パスワードレス MFA 強度**、**フィッシングに強い MFA 強度**) から選択できます。 また、許可する認証方法の組み合わせに基づいて、カスタム認証強度を作成することもできます。

[Image: 許可コントロールで認証強度が構成されている条件付きアクセス ポリシーのスクリーンショット。]

#### 組み込みの認証強度

組み込みの認証強度は、Microsoft が事前に定義した認証方法の組み合わせです。 組み込みの認証強度は常に使用でき、変更することはできません。 Microsoft では、新しい方法が利用可能になったときに、組み込みの認証強度を更新します。

たとえば、組み込みの **フィッシング耐性 MFA 強度** 認証強度では、次の組み合わせが可能になります。

- Windows Hello for Business またはプラットフォームの資格情報
- FIDO2 セキュリティ キー
- Microsoft Entra 証明書ベースの認証 (多要素)

[Image: フィッシングに強い多要素認証の認証強度の定義を示すスクリーンショット。]

次の表に、組み込みの各認証強度の認証方法の組み合わせを示します。 これらの組み合わせには、ユーザーが登録する必要があり、管理者が認証方法のポリシーまたは従来の MFA 設定のポリシーで有効にする必要がある方法が含まれます。

- **MFA の強度**: **多要素認証を要求** する設定を満たすために使用できる組み合わせの同じセット。
- **パスワードレス MFA の強度: MFA** を満たすがパスワードを必要としない認証方法が含まれます。
- **フィッシングに強い MFA 強度**: 認証方法とサインイン画面の間の対話を必要とする方法が含まれます。

| 認証方法の組み合わせ | MFA 強度 | パスワードレス MFA 強度 | フィッシングに強い MFA 強度 |
| --- | --- | --- | --- |
| FIDO2 セキュリティ キー | ✅ | ✅ | ✅ |
| Windows Hello for Business またはプラットフォームの資格情報 | ✅ | ✅ | ✅ |
| 証明書ベースの認証 (多要素) | ✅ | ✅ | ✅ |
| Microsoft Authenticator (電話によるサインイン) | ✅ | ✅ |  |
| 一時的なアクセス パス (1 回限りで複数回使用) | ✅ |  |  |
| パスワードとユーザーが持っているもの^1^ | ✅ |  |  |
| フェデレーション単一要素とユーザーが ^1^ を持つもの | ✅ |  |  |
| フェデレーション多要素 | ✅ |  |  |
| 証明書ベースの認証 (単一要素) |  |  |  |
| SMS サインイン |  |  |  |
| パスワード |  |  |  |
| フェデレーションシングルファクター |  |  |  |
| QRコード |  |  |  |

^1^*ユーザーが持っているものは* 、テキスト メッセージ、音声、プッシュ通知、ソフトウェア OATH トークン、またはハードウェア OATH トークンのいずれかの方法を指します。

次の API 呼び出しを使用して、すべての組み込みの認証強度の定義を一覧表示できます。

```http
GET https://graph.microsoft.com/beta/identity/conditionalAccess/authenticationStrength/policies?$filter=policyType eq 'builtIn'
```

#### カスタム認証強度

条件付きアクセス管理者は、アクセス要件に正確に合わせてカスタム認証の強度を作成することもできます。 詳細については、「 [カスタム条件付きアクセス認証の強度を作成および管理する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strength-advanced-options)」を参照してください。

### 制限事項

- **認証の強度の影響**: 条件付きアクセス ポリシーは、最初の認証の後にのみ評価されます。 その結果、認証強度によってユーザーの初期認証が制限されることはありません。

    組み込みの **フィッシング耐性 MFA 強度** 認証強度を使用しているとします。 ユーザーは引き続きパスワードを入力できますが、続行する前に、FIDO2 セキュリティ キーなどのフィッシングに強い方法を使用してサインインする必要があります。
- **許可コントロールのサポートされていない組み合わせ**: 同じ条件付きアクセス ポリシーで、 **多要素認証を要求** し、 **認証強度** 付与コントロールを一緒に要求することはできません。 その理由は、組み込みの **多要素認証の** 強度は、[ **多要素** 認証の許可の要求] コントロールと同じであるためです。
- **サポートされていない認証方法**: **電子メール ワンタイム パス (ゲスト)** 認証方法は、現在、使用可能な組み合わせではサポートされていません。
- **Windows Hello for Business**: ユーザーがプライマリ認証方法として Windows Hello for Business でサインインした場合は、Windows Hello for Business を含む認証強度要件を満たすために使用できます。 ただし、ユーザーがプライマリ認証方法として別の方法 (パスワードなど) を使用してサインインし、認証強度に Windows Hello for Business が必要な場合、ユーザーは Windows Hello for Business でサインインするように求められません。 ユーザーは、セッションを再起動し、 **サインイン オプション**を選択し、認証強度に必要な方法を選択する必要があります。

### 既知の問題

- **認証の強度とサインインの頻度**: リソースに認証強度とサインイン頻度が必要な場合、ユーザーは 2 つの異なるタイミングで両方の要件を満たすことができます。

    たとえば、1 時間のサインイン頻度と共に、認証強度にパスキー (FIDO2) が必要なリソースがあるとします。 ユーザーがパスキー (FIDO2) を使用してサインインし、24 時間前にリソースにアクセスした。

    ユーザーが Windows Hello for Business を使用して Windows デバイスのロックを解除すると、リソースに再度アクセスできます。 昨日のサインインで認証強度の要件を満たし、今日のデバイスのロック解除はサインイン頻度の要件を満たします。

### FAQ

#### 認証方法に認証強度またはポリシーを使用する必要がありますか?

認証強度は、 **認証方法** ポリシーに基づいています。 **認証方法**ポリシーは、ユーザーとグループが Microsoft Entra ID 全体で使用できる認証方法のスコープ設定と構成に役立ちます。 認証の強度により、機密性の高いリソース アクセス、ユーザー リスク、場所など、特定のシナリオに対する方法の別の制限が可能になります。

たとえば、Contoso という名前の組織の管理者が、ユーザーがプッシュ通知またはパスワードレス認証モードで Microsoft Authenticator を使用することを許可するとします。 管理者は、 **認証方法** ポリシーの Authenticator 設定に移動し、関連するユーザーのポリシーのスコープを設定し、 **認証モード** を **[任意]** に設定します。

Contoso の最も機密性の高いリソースの場合、管理者はアクセスをパスワードレス認証方法のみに制限したいと考えています。 管理者は、組み込みの **パスワードレス MFA 強度** 認証強度を使用して、新しい条件付きアクセス ポリシーを作成します。

その結果、Contoso のユーザーは、Authenticator からのパスワードとプッシュ通知を使用するか、Authenticator (電話によるサインイン) のみを使用して、テナント内のほとんどのリソースにアクセスできます。 ただし、テナント内のユーザーが機密性の高いアプリケーションにアクセスする場合は、Authenticator (電話によるサインイン) を使用する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-authentication-verified-id"} -->
## Microsoft Entra IDの検証済みIDの身元確認概要 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-verified-id
- Service: entra-id / authentication
- Article date: 2026-04-30
- Summary: Microsoft Entra IDの検証済み ID が ID 検証プロファイルを使用して、アカウントの回復などの ID 検証フローをセキュリティで保護するようにユーザーをスコープする方法について説明します。

検証済み ID は、Microsoft Entra IDの ID 検証機能であり、ユーザーの検証済み ID の暗号化証明を提供しますが、サインイン、MFA、SSPR などの認証要件を満たすために使用することはできません。 代わりに、検証済み ID は、すべての認証方法が失われた場合のアカウントの回復など、ユーザーの ID を再確立する必要があるシナリオの ID 証明レイヤーとして機能します。

ID 検証プロファイルは、検証済み ID フローに参加できるユーザー、検証を実行するプロバイダー、および ID 要求の検証方法を制御するポリシー レイヤーです。 現在、検証済み ID プロファイルはアカウントの回復に使用されています。これにより、すべての認証方法を失ったユーザーは、政府が発行した ID 検証を通じてアクセスを回復できます。 プロファイル フレームワークは、将来の追加の ID 検証シナリオをサポートするように設計されています。

### ID 検証プロファイル

ID 検証プロファイルは、ユーザーのグループの ID 検証動作を定義する構成オブジェクトです。 プロファイルでは、次の項目を指定します。

- **名前と説明** — プロファイルの目的を識別する表示名と説明。
- **状態** : プロファイルが有効か無効か。
- **ユーザー グループ スコープ** — プロファイルが適用されるユーザー。グループベースの割り当てによって定義されます。
- **ID 検証プロバイダー** — 検証者 DID (分散識別子) によって識別される ID 証明を実行するサード パーティのプロバイダー。資格情報交換の検証者の一意の暗号化識別子です。
- **Face Check configuration** — 検証中のMicrosoft Entra Verified ID Face Check 動作の設定。
- **プロファイルの構成** - 受け入れられた資格情報の発行者、ID 要求がユーザーのプロパティにマップされる方法、および交換の資格情報の種類。
- **使用状況の構成** - プロファイルが適用されるシナリオ ( `recovery`など)。 今後、追加のシナリオがサポートされる可能性があります。
- **優先順位** — ユーザーが複数のプロファイルと一致する場合の処理順序。

#### アカウントの回復による自動作成

id 検証プロファイルは、[Authentication Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) がMicrosoft Entra 管理センター (**Entra ID**&gt;**Account 回復**) を使用してアカウントの回復を設定すると、自動的に作成および構成されます。 アカウント回復セットアップ ウィザードでは、プロファイルの作成、プロバイダーの構成、ユーザー グループのスコープ、およびアカウント検証規則が処理されます。

Important

アカウントの回復によって作成されたプロファイルでは、認証方法のポリシー設定で個別の管理は必要ありません。 アカウント回復セットアップ ウィザードを使用して、回復のために検証済み ID の割り当てを管理することをお勧めします。これにより、プロファイルを作成および構成するためのガイド付きエクスペリエンスが提供されます。

#### 認証方法ポリシーでのプロファイル管理

検証済み ID 認証方法ポリシーは、プロファイルとユーザー割り当てを可視化します。

[Image: [有効] トグル、[含める] タブと [除外] タブ、グループ割り当てテーブル、プロファイル ドロップダウンが表示された [確認済み ID 認証方法ポリシー] ページを示すスクリーンショット。]

管理者は、ポリシー設定から次のことができます。

- **プロファイルの詳細の表示** - アカウント検証要求のマッピングやカスタム認証拡張機能の設定など、各 ID 検証プロファイルの構成、プロバイダー、状態、および優先順位を確認します。

    [Image: プロファイル モード、プロファイルの詳細、ユーザー グループ、ID 検証プロバイダー、アカウント検証要求テーブル、カスタム認証拡張機能を含む [Identity Verification Profile details](ID 検証プロファイルの詳細) パネルを示すスクリーンショット。]
- **ユーザー グループのスコープ** - ユーザーのグループを特定の検証済み ID プロファイルに割り当てて、検証済みの資格情報フローに参加できるユーザーを制御します。
- **新しい割り当てを作成** する - 特定の ID 検証プロファイルへのアクセスが必要なユーザーに対して、新しいグループ間割り当てを追加します。
- **グローバル除外** - すべての ID 検証プロファイルから特定のグループを除外します。 どのプロファイルが構成されているかに関係なく、除外されたユーザーは検証済みの ID ベースのフローに参加できません。

Tip

認証方法ポリシー ビューは、組織全体のプロファイル割り当てを監査する場合に役立ちます。 プロファイルを作成および構成するには、アカウント回復セットアップ ウィザードを使用します。

#### 複数のプロファイル

組織では、複数の ID 検証プロファイルを作成して、さまざまなユーザー設定をサポートできます。 例えば次が挙げられます。

- 正確な名前の一致を持つ 1 つの ID 検証プロバイダーを使用する会社の従業員のプロファイル
- 別のサービスプロバイダーを利用する現場作業者向けで、マッチング基準が緩和されているプロファイル
- 広範な展開の前に新しいプロバイダーをテストするパイロット グループのプロファイル

ユーザーが複数のプロファイルに一致するグループに属している場合、システムは優先順位でプロファイルを評価し、最初に一致するプロファイルを適用します。

### 使用シナリオ

各 ID 検証プロファイルには、プロファイルが適用されるシナリオを定義する 1 つ以上の使用構成が含まれています。

#### アカウントの回復

アカウントの回復は、現在の検証済み ID プロファイルの主な使用シナリオです。 ユーザーが登録されているすべての認証方法 (デバイスの紛失、盗難、侵害など) を失った場合、アカウントの回復では、検証済み ID プロファイルを使用して次の判断が行われます。

- ユーザーの ID を検証する ID 検証プロバイダー
- プロバイダーからの ID 要求をユーザーのMicrosoft Entra ID プロファイルと照合する方法
- プロファイルが評価モード (テスト) または運用モード (完全復旧) のいずれで動作するか

アカウントの回復の構成の詳細については、「アカウントの回復をMicrosoft Entra IDを参照してください。

Note

ID 検証プロバイダーは、Microsoft Security ストアを通じて利用できる外部サービスです。 運用ユーザーにプロファイルを展開する前に、プロバイダーのプライバシー、データ保持、コンプライアンス ポリシーを確認します。

### プロファイルのプロパティ

次のプロパティは、検証済み ID プロファイルを定義します。

| 財産 | Description |
| --- | --- |
| **名前** | プロファイルの表示名 |
| **説明** | プロファイルの目的の説明 |
| **状態** | プロファイルが `enabled` か `disabled` |
| **優先度** | 複数のプロファイルがユーザーと一致する場合の処理順序 |
| **verifierDid** | 資格情報交換の検証ツールを表す分散識別子 (DID) |
| **顔認識設定** | Entra Verified ID Face Check の動作の設定 |
| **verifiedIdProfileConfiguration** | 承認された発行者、クレーム バインディング、および資格情報の種類 |
| **verifiedIdUsageConfigurations** | プロファイルが適用されるシナリオ ( `recovery`など) |
| **lastModifiedDateTime** | プロファイルが最後に変更されたとき |

完全な API リファレンスについては、 [verifiedIdProfile リソースの種類 (ベータ)](https://learn.microsoft.com/ja-jp/graph/api/resources/verifiedidprofile?view=graph-rest-beta&preserve-view=true) に関するページを参照してください。

### 顔チェック

検証済み ID プロファイルには、ID 検証中にプレゼンスの証明を確認するための Face Check 構成が含まれます。 Face Check は、ユーザーの政府発行の ID の写真とリアルタイムの生体認証チェックを比較し、資格情報を提示するユーザーが物理的に存在することを確認します。

Face Check には、[Face Check ライセンス](https://learn.microsoft.com/ja-jp/entra/verified-id/using-facecheck)が必要です。Entra スイートまたはスタンドアロン ライセンスで利用できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-authentication-web-browser-cookies"} -->
## Microsoft Entra認証で使用される Web ブラウザー Cookie - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-web-browser-cookies
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra認証で使用される Web ブラウザー Cookie について説明します。

Web ブラウザーを介したMicrosoft Entra IDに対する認証では、複数の Cookie がプロセスに関与します。 一部の Cookie は、すべての要求で共通です。 その他の Cookie は、特定の認証フローまたは特定のクライアント側の条件に使用されます。

永続的なセッション トークンは、Web ブラウザーの cookie jar に永続的な Cookie として保存されます。 非永続的なセッション トークンは、Web ブラウザーにセッション Cookie として保存され、ブラウザー セッションが閉じられると破棄されます。

| クッキー名 | タイプ | コメント |
| --- | --- | --- |
| ESTSAUTH | 共通 | SSO を容易にするためのユーザーのセッション情報が含まれています。 一時的。 |
| ESTSAUTHPERSISTENT | 共通 | SSO を容易にするためのユーザーのセッション情報が含まれています。 永続的 |
| ESTSAUTHLIGHT | 共通 | セッション GUID 情報が含まれます。 OIDC のサインアウトを容易にするために、クライアント側の JavaScript によって排他的に使用される Lite セッション状態 Cookie。セキュリティ機能。 |
| サインイン状態クッキー | 共通 | サインアウトを容易にするためにアクセスされるサービスの一覧が含まれます。ユーザー情報がありません。 セキュリティ機能。 |
| CCState | 共通 | Microsoft Entra IDと [Microsoft Entra Backup Authentication Service](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/resilience-defaults) の間で使用されるセッション情報の状態が含まれます。 |
| buid | 共通 | ブラウザー関連情報を追跡します。 サービス テレメトリと保護メカニズムに使用されます。 |
| fpc | 共通 | ブラウザー関連情報を追跡します。 要求と調整の追跡に使用されます。 |
| esctx | 共通 | セッション コンテキスト Cookie 情報。 CSRF保護のために。 要求を特定のブラウザー インスタンスにバインドして、要求をブラウザーの外部で再生できないようにします。 ユーザー情報はありません。 |
| ch | 共通 | 証拠保有クッキー（ProofOfPossessionCookie）。 所有証明 Cookie ハッシュをユーザー エージェントに保存します。 |
| ESTSSC | 共通 | セッション数情報を含むレガシ Cookie は使用されなくなりました。 |
| ESTSSSOTILES | 共通 | セッションサインアウトを追跡します。存在し、有効期限が切れていない場合、値 "ESTSSSOTILES=1" を指定すると、特定の SSO 認証モデルの SSO が中断され、ユーザー アカウントの選択用のタイルが表示されます。 |
| AADSSOTILES | 共通 | セッションのサインアウトを追跡します。ESTSSSOTILES に似ていますが、他の特定の SSO 認証モデルの場合と同様です。 |
| ESTSUSERLIST | 共通 | ブラウザー SSO ユーザーの一覧を追跡します。 |
| SSOCOOKIEPULLED | 共通 | 特定のシナリオでのループを防止します。 ユーザー情報はありません。 |
| cltm | 共通 | テレメトリの目的のため。 AppVersion、ClientFlight、およびネットワークの種類を追跡します。 |
| brcap | 共通 | クライアント/Web ブラウザーのタッチ機能を検証するためのクライアント側 Cookie (JavaScript によって設定)。 |
| clrc | 共通 | クライアント上のローカル キャッシュ セッションを制御するためのクライアント側 Cookie (JavaScript によって設定)。 |
| CkTst | 共通 | クライアント側 Cookie (JavaScript によって設定)。 現在使用されていません。 |
| wlidperf | 共通 | パフォーマンスのためにローカル時刻を追跡するクライアント側 Cookie (JavaScript によって設定されます)。 |
| x-ms-gateway-slice | 共通 | Microsoft Entraゲートウェイ Cookie は、追跡と負荷分散の目的で使用されます。 |
| stsservicecookie | 共通 | Microsoft Entraゲートウェイ Cookie は、追跡目的にも使用されます。 |
| x-ms-refreshtokencredential | 固有 | [プライマリ更新トークン (PRT)](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token) が使用中の場合に使用できます。 |
| estsStateTransient | 固有 | 新しいセッション情報モデルにのみ適用されます。 一時的。 |
| estsStatePersistent | 固有 | estsStateTransient と同じですが、永続的です。 |
| ESTSNCLOGIN | 固有 | National Cloud Login 関連の Cookie。 |
| UsGovTraffic | 固有 | US Gov Cloud Traffic Cookie。 |
| ESTSWCTXFLOWTOKEN | 固有 | ADFS にリダイレクトするときに flowToken 情報を保存します。 |
| CcsNtv | 固有 | Microsoft Entra ゲートウェイが要求を [Microsoft Entra Backup Authentication Service](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/resilience-defaults) に送信するタイミングを制御します。 ネイティブ フロー。 |
| CcsWeb | 固有 | Microsoft Entra ゲートウェイが要求を [Microsoft Entra Backup Authentication Service](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/resilience-defaults) に送信するタイミングを制御します。 ウェブフロー。 |
| Ccs\* | 固有 | プレフィックス Ccs\* の Cookie は、プレフィックスのない Cookie と同じ目的を持ちますが、[Microsoft Entra Backup Authentication Service](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/resilience-defaults) が使用されている場合にのみ適用されます。 |
| threxp | 固有 | スロットリング制御に使用されます。 |
| rrc | 固有 | 最近の B2B 招待の引き換えを識別するために使用される Cookie。 |
| デバッグ | 固有 | ユーザーのブラウザー セッションが DebugMode に対して有効になっているかどうかを追跡するために使用される Cookie。 |
| MSFPC | 固有 | この Cookie は ESTS フローに固有のものではありませんが、存在する場合があります。 これは、すべてのMicrosoftサイトに適用されます (ユーザーが受け入れた場合)。 Microsoft サイトにアクセスする一意の Web ブラウザーを識別します。 広告、サイト分析、およびその他の運用目的で使用されます。 |

注意

クライアント側の Cookie として識別される Cookie は、JavaScript によってクライアント デバイス上でローカルに設定されるため、HttpOnly=false でマークされます。

Cookie の定義とそれぞれの名前は、Microsoft Entra サービス要件に応じていつでも変更される可能性があります。

### Entra IDで Cookie を使用できない場合

特定の条件下では、上記の Cookie がMicrosoft Entra IDで完全に使用できない場合があります。 これは、サポートされていない認証ライブラリまたはMicrosoft以外の認証ライブラリの使用、プライバシー機能、ブラウザー固有の動作 (InPrivate または Incognito ブラウズ モードが使用されるシナリオなど) が原因で発生する可能性があります。

cookie がEntra IDに正しく提供されない場合、ユーザーは次の 1 つ以上の問題を経験する可能性があります。

- シングル サインオン (SSO) が期待どおりに機能せず、ユーザーに再認証を求めるメッセージが表示される
- ユーザーに予期しない確認または同意ダイアログが表示される場合がある
- サインアウト エクスペリエンスは、完了前に失敗または終了する可能性があります (たとえば、 `post_logout_redirect_uri` パラメーターは処理されません)。

一貫性のある信頼性の高い認証エクスペリエンスを確保するために、Microsoftは、サポートされているブラウザーとサポートされているクライアント認証ライブラリ (Microsoft Authentication Library (MSAL) など) を使用することをお勧めします。 これにより、Cookie が正しく処理され、認証とサインアウト フローが設計どおりに動作することを確認できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-authentication-windows-hello"} -->
## Microsoft Entra ID での Windows Hello for Business 認証方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-windows-hello
- Service: entra-id / authentication
- Article date: 2025-11-01
- Summary: Microsoft Entra ID で Windows Hello for Business 認証を使用してサインイン イベントを改善およびセキュリティで保護する方法について説明します

Windows Hello for Business は、指定された自分用の Windows PC を持っているインフォメーション ワーカーに最適です。 生体認証と PIN 資格情報はユーザーの PC に直接関連付けられており、所有者以外のユーザーからのアクセスを禁止します。 公開キー 基盤 (PKI) 統合とシングル サインオン (SSO) の組み込みサポートにより、Windows Hello for Business は、オンプレミスおよびクラウド内の企業リソースにシームレスにアクセスするための便利な方法を提供します。

[Image: Windows Hello for Business を使用したユーザー サインインの例。]

### Microsoft Entra ID での Windows Hello for Business でのサインインのしくみ

以下の手順で、Microsoft Entra ID を使用したサインイン プロセスがどのように機能するかを示します。

[Image: Windows Hello for Business を使用したユーザー サインインに関連する手順の概要を示す図]

1. ユーザーは、生体認証または PIN のジェスチャを使用して Windows にサインインします。 このジェスチャは、Windows Hello for Business の秘密キーのロックを解除し、*クラウド認証プロバイダー (CloudAP)* というクラウド認証セキュリティ サポート プロバイダーに送信されます。 CloudAP の詳細については、「[プライマリ更新トークンとは](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token)」をご覧ください。
2. CloudAP により、Microsoft Entra ID に nonce (1 回使用できるランダムな任意の数) が要求されます。
3. Microsoft Entra ID からは 5 分間有効な nonce が返されます。
4. CloudAP では、ユーザーの秘密キーを使用して nonce に署名され、署名済みの nonce が Microsoft Entra ID に返されます。
5. Microsoft Entra ID では、ユーザーの安全に登録された公開キーを使用して、署名済みの nonce が nonce の署名に対して検証されます。 Microsoft Entra ID によってその署名が検証された後、返された署名済み nonce が検証されます。 nonce が検証されると、Microsoft Entra ID ではデバイスのトランスポート キーに暗号化されたセッション キーを使用してプライマリ更新トークン (PRT) が作成され、それが CloudAP に返されます。
6. CloudAP は、セッション キーと共に暗号化された PRT を受け取ります。 CloudAP は、デバイスの秘密トランスポート キーを使用してセッション キーの暗号化を解除し、デバイスのトラステッド プラットフォーム モジュール (TPM) を使用してセッション キーを保護します。
7. CloudAP から Windows に成功した認証応答が返されます。 そして、ユーザーはシームレス サインオン (SSO) を使用して、Windows、クラウド、オンプレミスのアプリケーションにアクセスできます。

Windows Hello for Business の[計画ガイド](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-planning-guide)を使用すると、Windows Hello for Business の展開の種類と、検討する必要がある選択肢を決定することができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-certificate-based-authentication"} -->
## Microsoft Entra CBA の概要 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: フェデレーションなしの Microsoft Entra 証明書ベースの認証 (CBA) について説明します。

組織では、Microsoft Entra 証明書ベースの認証 (CBA) を使用して、アプリケーションとブラウザーのサインインに Microsoft Entra ID で認証された X.509 証明書を使用して、ユーザーに直接認証を許可または要求できます。

この機能を使用して、フィッシングに強い認証を採用し、公開キー基盤 (PKI) に対して X.509 証明書を使用して認証します。

### Microsoft Entra CBA とは何ですか?

CBA から Microsoft Entra ID へのクラウド管理のサポートを利用できるようになる前に、組織は、Microsoft Entra ID に対して X.509 証明書を使用してユーザーが認証できるように、フェデレーション CBA を実装する必要がありました。 これには、Active Directory フェデレーション サービス (AD FS) の展開が含まれていました。 Microsoft Entra CBA を使用すると、Microsoft Entra ID に対して直接認証を行い、簡素化された環境とコスト削減のためにフェデレーション AD FS の必要性を排除できます。

次の図は、Microsoft Entra CBA がフェデレーション AD FS を排除することによって環境を簡素化する方法を示しています。

#### フェデレーション対応 AD FS を使用した CBA

[Image: フェデレーションを使用した CBA を示す図。]

#### Microsoft Entra CBA

[Image: Microsoft Entra CBA を示す図。]

### Microsoft Entra CBA を使用する主な利点

| メリット | 説明 |
| --- | --- |
| ユーザー エクスペリエンスの向上 | - CBA を必要とするユーザーは、Microsoft Entra ID に対して直接認証できるようになり、フェデレーション AD FS に投資する必要はありません。- 管理センターを使用すると、証明書フィールドをユーザー オブジェクト属性に簡単にマップして、テナント内のユーザーを検索できます ([証明書のユーザー名バインド](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-technical-deep-dive#username-binding-policy))- 管理センターを使用して [認証ポリシーを構成し、](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-technical-deep-dive#authentication-binding-policy) どの証明書が単一要素と多要素かを判断するのに役立ちます。 |
| デプロイと管理が容易 | - Microsoft Entra CBA は無料の機能です。 使用するために Microsoft Entra ID の有料エディションは必要ありません。 - 簡単にオンプレミスにデプロイしてネットワーク構成できます。- Microsoft Entra ID に対して直接認証します。 |
| セキュリティで保護 | - オンプレミス パスワードは、いかなる形でもクラウドに保存する必要はありません。- フィッシングに強い [多要素認証 (MFA)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks) を含む Microsoft Entra 条件付きアクセス ポリシーをシームレスに使用して、ユーザー アカウントを保護します。 MFA には、 [ライセンスされたエディション](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-licensing) とレガシ認証のブロックが必要です。- 強力な認証のサポート。 管理者は、証明書フィールド (発行者、ポリシー オブジェクト識別子 (ポリシー OID) など) を使用して認証ポリシーを定義して、単一要素と多要素のどちらとして修飾されるかを判断できます。- この機能は、[条件付きアクセス機能](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)と認証強度機能とシームレスに連携して、ユーザーのセキュリティ保護に役立つ MFA を適用します。 |

### サポートされるシナリオ

次のシナリオがサポートされます。

- すべてのプラットフォーム上の Web ブラウザー ベースのアプリケーションへのユーザー サインイン
- iOS および Android プラットフォーム上の Office モバイル アプリへのユーザー サインインと、Windows の Office ネイティブ アプリ (Outlook や OneDrive を含む)
- モバイル ネイティブ ブラウザーでのユーザー サインイン
- 証明書発行者のサブジェクトとポリシー OID を使用した MFA の詳細な認証規則
- 任意の証明書フィールドを使用して、証明書とユーザー アカウントのバインドを行います。

    - `SubjectAlternativeName` (`SAN`)、 `PrincipalName`、および `RFC822Name`
    - `SubjectKeyIdentifier` (`SKI`) と `SHA1PublicKey`
    - `IssuerAndSubject` および `IssuerAndSerialNumber`
- 任意のユーザー オブジェクト属性を使用した証明書とユーザー アカウントのバインド:

    - `userPrincipalName`
    - `onPremisesUserPrincipalName`
    - `certificateUserIds`

### サポートされていないシナリオ

以下のシナリオはサポートされていません。

- Windows ログイン (ロック/サインイン画面) での Web サインイン オプションでは、CBA はサポートされていません。
- 信頼された CA に対してサポートされている CRL 配布ポイント (CDP) は 1 つだけです。
- CDP には HTTP URL のみを指定できます。 オンライン証明書ステータス プロトコル (OCSP) またはライトウェイト ディレクトリ アクセス プロトコル (LDAP) URL はサポートされていません。
- 認証方法としてのパスワードをオフにすることはできません。 ユーザーが Microsoft Entra CBA メソッドを使用できる場合でも、パスワードを使用してサインインするオプションが表示されます。

### Windows Hello for Business 証明書に関する既知の制限事項

Windows Hello for Business は Microsoft Entra ID の MFA に使用できますが、Windows Hello for Business は新しい MFA ではサポートされていません。 Windows Hello for Business キー/ペアを使用して、ユーザーの証明書を登録することを選択できます。 適切に構成すると、Microsoft Entra ID の MFA に Windows Hello for Business 証明書を使用できます。

Windows Hello for Business 証明書は、Microsoft Edge および Chrome ブラウザーの Microsoft Entra CBA と互換性があります。 現在、Windows Hello for Business 証明書は、Office 365 アプリケーションなどの非ブラウザー シナリオでは Microsoft Entra CBA と互換性がありません。 解決策は、 **サインイン Windows Hello またはセキュリティ キー** オプションを使用してサインインすることです (使用可能な場合)。 このオプションは認証に証明書を使用せず、Microsoft Entra CBA の問題を回避します。 このオプションは、以前の一部のアプリケーションでは使用できない場合があります。

### スコープ外

次のシナリオは、Azure AD CBA の範囲外です:

- クライアント証明書を作成するための公開キー 基盤 (PKI) の作成または提供。 独自の PKI を構成し、ユーザーとデバイスに証明書をプロビジョニングする必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-certificate-based-authentication-certificate-revocation-list"} -->
## Microsoft Entra の証明書ベース認証の証明書失効リストを理解する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-certificate-revocation-list
- Service: entra-id / authentication
- Article date: 2025-08-26
- Summary: Microsoft Entra での証明書ベース認証における証明書失効リストのしくみを学びましょう。

証明書失効リスト (CRL) は、発行証明機関 (CA) によってスケジュールされた有効期限日より前に失効した証明書の一覧です。 CRL は、認証の整合性を維持するために不可欠です。 証明書が失効すると、有効期限が切れていない場合でも信頼されていないとマークされます。 証明書ベースの認証に CRL を組み込むことで、有効で失効していない証明書のみが受け入れられ、Microsoft Entra IDは失効した証明書を使用する試行をブロックします。

CRL は CA によってデジタル署名され、パブリックにアクセス可能な場所に公開されるため、証明書の失効状態を確認するためにインターネット経由でダウンロードできます。 クライアントが認証用の証明書を提示すると、システムは CRL をチェックして、証明書が取り消されたかどうかを判断します。

CRL で証明書が見つかった場合、認証の試行は拒否されます。 CRL は通常、定期的に更新され、組織は証明書の有効性に関する正確な決定を行うために、最新バージョンの CRL があることを確認する必要があります。

Microsoft Entra証明書ベースの認証 (CBA) では、CRL が構成されている場合、システムは認証時に CRL を取得して検証する必要があります。 MICROSOFT ENTRA IDが CRL エンドポイントにアクセスできない場合、証明書の有効性を確認するために CRL が必要であるため、認証は失敗します。

### 証明書ベースの認証での CRL のしくみ

CRL は、認証に使用される証明書の有効性を確認するメカニズムを提供することによって機能します。 このプロセスには、いくつかの重要な手順が含まれます。

- **証明書の発行:** CA によって発行された証明書は、前に失効しない限り、有効期限が切れるまで有効です。 各証明書には公開キーが含まれており、CA によって署名されます。
- **撤回：** 証明書を取り消す必要がある場合 (秘密キーが侵害された場合や証明書が不要になった場合など)、CA によって CRL に追加されます。
- **CRL ディストリビューション:** CA は、クライアントがアクセスできる場所 (Web サーバーやディレクトリ サービスなど) に CRL を発行します。 CRL は通常、整合性を確保するために CA によって署名されます。 CRL が CA によって署名されていない場合は、暗号化エラー [AADSTS2205015](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-certificate-revocation-list#crl-error-reference) がスローされ、 [FAQ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-certificate-revocation-list#frequently-asked-questions) の手順に従って問題のトラブルシューティングを行います。
- **クライアント チェック:** クライアントが認証用の証明書を提示すると、システムは発行された場所から証明書チェーン内の各 CA の CRL を取得し、失効した CA がないか確認します。 CRL の場所が使用できない場合、システムは証明書の失効状態を確認できないため、認証は失敗します。
- **認証：** CRL で証明書が見つかった場合、認証の試行は拒否され、クライアントはアクセスを拒否されます。 証明書が CRL に含まれていない場合、認証は通常どおりに続行されます。
- **CRL の更新:** CRL は CA によって定期的に更新されます。クライアントは、証明書の有効性に関する正確な決定を行うために、最新バージョンを確保する必要があります。 システムは、ネットワーク トラフィックを減らし、パフォーマンスを向上させるために CRL を一定期間キャッシュしますが、更新プログラムも定期的にチェックします。

### Microsoft Entra の証明書ベースの認証における証明書失効プロセスを理解する

証明書失効プロセスにより、認証ポリシー管理者は、以前に発行された証明書を取り消して、将来の認証に使用できなくなります。

認証ポリシー管理者は、Microsoft Entra テナントの信頼された発行者のセットアップ プロセス中に CRL 配布ポイントを構成します。 信頼された各発行者には、インターネットに接続する URL を使用して参照できる CRL が必要です。 詳細については、「証明機関の [構成」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-certificate-based-authentication#step-1-configure-the-cas-with-a-pki-based-trust-store)参照してください。

Microsoft Entra IDは、1 つの CRL エンドポイントのみをサポートし、HTTP または HTTPS のみをサポートします。 CRL 配布には HTTPS の代わりに HTTP を使用することをお勧めします。 CRL チェックは証明書ベースの認証中に行われ、CRL の取得の遅延または失敗によって認証がブロックされる可能性があります。 HTTP を使用すると、待機時間が最小限になり、HTTPS (それ自体に証明書の検証が必要) によって発生する可能性のある循環依存関係が回避されます。 信頼性を確保するには、高可用性 HTTP エンドポイントで CRL をホストし、インターネット経由でアクセスできることを確認します。

Important

対話型サインインで正常にダウンロードするMicrosoft Entra IDの CRL の最大サイズは、パブリック Microsoft Entra IDでは 20 MB、Azure 米国政府機関 クラウドでは 45 MB です。 CRL のダウンロードに必要な時間は、10 秒を超えてはなりません。 Microsoft Entra ID CRL をダウンロードできない場合、対応する CA によって発行された証明書を使用した証明書ベースの認証は失敗します。 CRL ファイルをサイズ制限内に保持するベスト プラクティスとして、証明書の有効期間を妥当な制限内に保持し、期限切れの証明書をクリーンアップします。

1. ユーザーが証明書を使用して対話型サインインを実行すると、Microsoft Entra IDは、証明機関から顧客の証明書失効リスト (CRL) をダウンロードしてキャッシュし、ユーザーの認証中に証明書が取り消されたかどうかを確認します。 Microsoft Entraでは、SubjectName の代わりに SubjectKeyIdentifier 属性を使用して証明書チェーンを構築します。 CRL が有効になっている場合、PKI 構成には、適切な失効チェックを確実にするために、SubjectKeyIdentifier と Authority Key Identifier の値を含める必要があります。

    SubjectKeyIdentifier は、証明書の公開キーに対して一意の不変識別子を提供し、サブジェクト名よりも信頼性が高く、証明書間で変更または複製される可能性があります。 この属性により、複雑な PKI 環境での正確なチェーン構築と一貫した CRL 検証が保証されます。

    Important

    認証ポリシー管理者が CRL の構成をスキップした場合、Microsoft Entra IDは、ユーザーの証明書ベースの認証中に CRL チェックを実行しません。 この動作は初期のトラブルシューティングに役立ちますが、運用環境では考慮しないでください。

    - ベース CRL のみ: ベース CRL が構成されている場合、Microsoft Entra ID が次の更新タイムスタンプまでそれをダウンロードし、キャッシュします。 CRL の有効期限が切れており、接続の問題が原因で更新できない場合、または CRL エンドポイントが更新バージョンを提供していない場合、認証は失敗します。 Microsoft Entraでは、CRL のバージョン管理が厳密に適用されます。新しい CRL が発行される場合、その CRL 番号は以前のバージョンより大きくする必要があります。

        CRL 番号は単調なバージョン管理を保証し、古い CRL が失効チェックをバイパスするために再導入される可能性があるリプレイ攻撃を防ぎます。 新しい CRL ごとにバージョン番号をより高くすることを要求することで、Microsoft Entra ID は最新の失効データが常に使用されることを保証します。
    - Base + Delta CRL: 両方が構成されている場合は、両方が有効でアクセス可能である必要があります。 存在しないか期限切れになっている場合、RFC 5280 標準に従って証明書の検証が失敗します。
2. ユーザー証明書ベースの認証は、信頼された発行者に対してCRLが構成されている場合に失敗します。Microsoft Entra IDが、可用性、サイズ、または待機時間の制約によってCRLをダウンロードできないためです。 この制限により、CRL エンドポイントは重大な単一障害点になり、Microsoft Entra IDの証明書ベースの認証の回復性が低下します。 このリスクを軽減するには、CRL エンドポイントの継続的なアップタイムを確保する高可用性ソリューションを使用することをお勧めします。
3. CRL がクラウドの対話型の制限を超えた場合、ユーザーの初期サインインは失敗し、次のエラーが表示されます。

    `The Certificate Revocation List (CRL) downloaded from {uri} has exceeded the maximum allowed size ({size} bytes) for CRLs in Microsoft Entra ID. Try again in few minutes. If the issue persists, contact your tenant administrators.`
4. Microsoft Entra IDは、サービス側の制限に従って CRL のダウンロードを試みます (パブリック Microsoft Entra IDでは 65 MB、米国政府の場合は Azure で 150 MB)。
5. ユーザーは数分後に認証を再試行できます。 ユーザーの証明書が失効し、CRL に表示される場合、認証は失敗します。

    Important

    CRL キャッシュのため、失効した証明書のトークン失効は直ちに行われません。 CRL が既にキャッシュされている場合、キャッシュが更新された CRL で更新されるまで、新しく失効した証明書は検出されません。 デルタ CRL には通常、これらの更新プログラムが含まれているため、デルタ CRL が読み込まれると失効が有効になります。 デルタ CRL を使用しない場合、失効は基本 CRL の有効期間によって異なります。 管理者は、セキュリティが高いシナリオなど、即時失効が重要な場合にのみ、トークンを手動で取り消す必要があります。 詳細については、「失効の [構成」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/certificate-based-authentication-federation-get-started#step-3-configure-revocation)参照してください。
6. パフォーマンスと信頼性の理由から、オンライン証明書ステータス プロトコル (OCSP) はサポートされていません。 OCSP 用のクライアント ブラウザーがすべての接続で CRL をダウンロードする代わりに、Microsoft Entra ID が最初のサインイン時に一度ダウンロードしてキャッシュします。 このアクションにより、CRL 検証のパフォーマンスと信頼性が向上します。 また、毎回検索がはるかに高速になるように、キャッシュのインデックスを作成します。
7. CRL Microsoft Entra正常にダウンロードされた場合は、その CRL をキャッシュし、その後の使用のために再利用します。 **次の更新日**を尊重し、利用可能な場合には、CRL ドキュメント内の **次の CRL 発行日** (Windows Server CA により使用されます) も重視します。
8. ユーザーの証明書が CRL で失効済みとして表示されている場合、ユーザー認証は失敗します。

    [Image: CRL で失効したユーザー証明書のスクリーンショット。]

    Important

    CRL キャッシュと発行サイクルの性質上、証明書の失効がある場合は、影響を受けるユーザーのすべてのセッションをMicrosoft Entra IDで取り消すことも強くお勧めします。
9. Microsoft Entra IDは、キャッシュされた CRL ドキュメントの有効期限が切れている場合に、配布ポイントから新しい CRL を事前にフェッチしようとします。 Microsoft Entra は、CRL に「次の発行日」がある場合、キャッシュ内の CRL の有効期限が切れていなくても、CRLプリフェッチを行います。 現時点では、CRL のダウンロードを手動で強制または再トリガーする方法はありません。

    注

    Microsoft Entra IDは、発行元 CA の CRL と PKI 信頼チェーン内の他の CA をルート CA までチェックします。 PKI チェーンの CRL 検証では、リーフ クライアント証明書から最大 10 CA の制限があります。 この制限は、悪意のあるユーザーがCRLサイズが大きい、非常に多数のCAを含むPKIチェーンをアップロードすることでサービスを停止させることを防ぐためのものです。 テナントの PKI チェーンに 10 を超える CA があり、CA が侵害された場合、認証ポリシー管理者は、侵害された信頼された発行者をMicrosoft Entraテナント構成から削除する必要があります。 詳細については、「 [CRL プリフェッチ」を](https://learn.microsoft.com/ja-jp/windows/win32/seccrypto/certificate-revocation-list-semantics#crl-pre-fetching)参照してください。

### 失効を構成する方法

クライアント証明書を取り消すには、Microsoft Entra ID証明機関情報の一部としてアップロードされた URL から証明書失効リスト (CRL) をフェッチしてキャッシュします。 CRL の最後の発行タイムスタンプ (**有効日** プロパティ) は、CRL がまだ有効であることを確認するために使用されます。 CRL は、リストの一部である証明書へのアクセスを取り消すために定期的に参照されます。

**Entra CBA とのセッションの即時失効**

ユーザーのすべてのアクセスが取り消されるように、管理者にすべてのセッション トークンをすぐに取り消す必要があるシナリオは多数あります。 このようなシナリオには、次のようなものがあります。

- 侵害されたアカウント
- 従業員の退職
- CRL 検証を含まない状態でキャッシュされた資格情報が使用されたEntraの障害
- その他の内部関係者の脅威。

より迅速な失効が必要な場合 (たとえば、ユーザーがデバイスを紛失した場合)、ユーザーの承認トークンを無効にすることができます。 特定のユーザーの認証トークンを無効にするには、Windows PowerShell を使用して **StsRefreshTokensValidFrom** フィールドを設定します。 アクセスを取り消す各ユーザーの **StsRefreshTokensValidFrom** フィールドを更新する必要があります。

失効が維持されるようにするには、CRL の **有効日** を **StsRefreshTokensValidFrom** によって設定された値の後の日付に設定し、問題の証明書が CRL にあることを確認する必要があります。

次の手順では、 **StsRefreshTokensValidFrom** フィールドを設定して承認トークンを更新および無効化するプロセスについて説明します。

```https
# Authenticate to Microsoft Graph
Connect-MgGraph -Scopes "User.Read.All"

# Get the user
$user = Get-MgUser -UserPrincipalName "test@contoso.com"

# Get the StsRefreshTokensValidFrom property
$user.StsRefreshTokensValidFrom
```

設定する日付は将来である必要があります。 日付が将来でない場合、 **StsRefreshTokensValidFrom** プロパティは設定されません。 日付が将来の場合、 **StsRefreshTokensValidFrom** は現在の時刻に設定されます (Set-MsolUser コマンドで示される日付ではありません)。

### CA に CRL 検証を適用する

CA をMicrosoft Entra信頼ストアにアップロードするときに、CRL または CrlDistributionPoint 属性を含める必要はありません。 CRL エンドポイントなしで CA をアップロードできます。発行元 CA で CRL が指定されていない場合、証明書ベースの認証は失敗しません。

セキュリティを強化し、構成ミスを回避するために、エンド ユーザー証明書を発行する CA で CRL が構成されていない場合、認証ポリシー管理者は CBA 認証の失敗を要求できます。

#### CRL 検証を有効にする

1. **CRL 検証を有効にするには、[CRL 検証が必要です (推奨)]** を選択します。

    [Image: CRL 検証を要求する方法のスクリーンショット。]

    この設定を有効にすると、エンド ユーザー証明書が CRL を構成していない CA から取得された場合、CBA は失敗します。
2. 認証ポリシー管理者は、CRL に修正が必要な問題がある場合、CA を除外できます。 [ **除外の追加]** を選択し、除外する CA を選択します。

    [Image: CRL 検証から CA を除外する方法のスクリーンショット。]
3. 除外リスト内の CA は CRL を構成する必要がなく、発行するエンド ユーザー証明書は認証に失敗しません。

    CA を選択し、[ **追加]** を選択します。 [ **検索** ] テキスト ボックスを使用して CA リストをフィルター処理し、特定の CA を選択します。

    [Image: CRL 検証から除外される CA のスクリーンショット。]

### Microsoft Entra IDの CRL (ベースおよびデルタ CRL) の設定に関するガイダンス

1. アクセス可能な CRL を発行する:

    - ベース CRL と差分 CRL (該当する場合) の両方が、HTTP 経由でアクセス可能なインターネットに接続された URL に CA によって公開されていることを確認します。
    - CRL が内部専用サーバーでホストされている場合、Microsoft Entra IDは証明書を検証できません。 URL は高可用性、パフォーマンス、回復性を備え、使用できないことによる認証エラーを防ぐ必要があります。
    - ブラウザーで CRL URL をテストし、配布チェックに certutil -url を使用して、CRL のアクセシビリティを検証します。
2. Microsoft Entra IDで CRL URL を構成します。

    - CA パブリック証明書をMicrosoft Entra IDにアップロードし、CRL 配布ポイント (CDN) を構成します。
    - ベース CRL URL: 失効したすべての証明書が含まれています。
    - Delta CRL URL (省略可能ですが推奨): 最後のベース CRL が発行されてから失効した証明書が含まれています。
    - certutil などのツールを使用して、CRL の有効性を確認し、証明書と CRL の問題をローカルでトラブルシューティングします。
3. 有効期間を設定します。

    - 運用上のオーバーヘッドとセキュリティのバランスを取るのに十分な長さの基本 CRL 有効期間を設定します (通常は数日から数週間)。
    - 失効した証明書をタイムリーに認識できるように、デルタ CRL の有効期間を短く (通常は 24 時間) 設定します。
    - デルタ CRL の有効性が短いほど、失効した証明書は有効なままですが、発行と配布の負荷が増加するウィンドウが減るため、セキュリティが向上します。
    - Windows サーバー上のデルタ CRL に対して推奨される 24 時間の既定の有効性は、広く受け入れわれている標準的なセキュリティとパフォーマンスです。
    - Microsoft Entra IDは、パフォーマンスが低下することなく頻繁な差分 CRL 更新プログラムを効率的に処理するように設計されており、継続的な改善がこれをさらに強化するのに役立ちます。
    - Microsoft Entra IDは、デルタ CRL のダウンロード中に DDoS 攻撃から保護するために調整メカニズムを適用します。その結果、ユーザーの小さなサブセットに対して "AADSTS2205013" のような一時的なエラーが発生する可能性があります。
4. 高可用性とパフォーマンスを確保する:

    - 信頼性の高い Web サーバーまたはコンテンツ配信ネットワーク (CDN) で CRL をホストし、取得中の遅延や障害を最小限に抑えます。
    - CRL の公開とアクセシビリティを事前に監視します。
5. スロットリングや分散型サービス拒否攻撃 (DDoS) から保護します。

    - Microsoft Entra IDサービスとユーザーを保護するために、高負荷または潜在的な不正使用の際に、CRLフェッチ操作にスロットリングが適用されます。
    - オフピーク時に CRL の発行と有効期限のサイクルをスケジュールして、帯域制限がユーザーに影響を与える可能性を最小限に抑えます。
6. CRL サイズの管理

    - フェッチ速度を向上させ、帯域幅を減らすために、CRL ペイロードをできるだけ小さくします。理想的には、頻繁にデルタ CRL の発行と古いエントリのアーカイブを行います。
7. CRL 検証を有効にする

    - 失効した証明書が検出されるように、Microsoft Entra ID ポリシーで CRL 検証を適用します。 詳細については、「 [CRL 検証を有効にする」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-certificate-revocation-list#enable-crl-validation)参照してください。
    - セキュリティ リスクを理解しながら、トラブルシューティング中の最後の手段として CRL チェックの一時的なバイパスのみを検討してください。
8. テストと監視

    - 定期的なテストを実行して、CRL がダウンロード可能で、Microsoft Entra IDによって正しく認識されていることを確認します。
    - 監視を使用して、CRL の可用性または検証の問題を検出して迅速に修復します。

### CRL エラー リファレンス

| エラー コードとメッセージ | Description | 一般的な原因 | 推奨事項 |
| --- | --- | --- | --- |
| **AADSTS500171: 証明書が取り消されました。 管理者に問い合わせてください。** | 証明書が CRL 内にあり、失効したことを示します。 | 証明書は管理者によって取り消されます。 | 証明書が CRL に誤って含まれている場合は、発行元 CA に、目的の失効を正確に反映する更新されたリストを使用して CRL を再発行させます。 |
| **AADSTS500172: '{issuer}' によって発行された証明書 '{name}' が無効です。 現在の時刻: '{curTime}'。 証明書 NotBefore: '{startTime}'。 証明書の有効期限: '{endTime}'。** | CRL は時間内に有効ではありません。 | 証明書の検証に使用される CRL またはデルタ CRL には、有効期限切れの CRL や正しく構成されていないパブリケーション/有効期間などのタイミングの問題があります。 | - 証明書の NotBefore 日付と NotAfter 日付が現在の時刻を正しく含むか確認します。- CA によって発行されたベース CRL とデルタ CRL の有効期限が切れていないことを確認します。 |
| **AADSTS500173: &gt;証明書失効リスト (CRL) をダウンロードできません。 CRL 配布ポイントの状態コード {code} が無効です。 管理者に問い合わせてください。** | エンドポイントの問題が原因で CRL をダウンロードできませんでした。 | - CRL エンドポイントが HTTP エラー (403 など) を返す- 更新なしで CRL の有効期限が切れた | - CRL エンドポイントから有効なデータが返されたことを確認する- CA が更新された CRL を定期的に公開することを確認する - CRL URL は、ネットワークの問題、ファイアウォール ブロック、またはサーバーのダウンタイムが原因でアクセスできません。- CRL フェールセーフを有効にして、検証できない証明書をブロックします。 |
| **AADSTS500174: 応答から有効な証明書失効リスト (CRL) を作成できません。** | Microsoft Entra IDは、指定した配布ポイントから取得した CRL を解析または使用できません。 | - CRL URL は、ネットワークの問題、ファイアウォール ブロック、またはサーバーのダウンタイムが原因でアクセスできません。 - ダウンロードした CRL ファイルが破損しているか、不完全であるか、正しくフォーマットされていません。- 証明書の CDP フィールドの URL が有効な CRL ファイルを指していないか、正しく構成されていません。 | - CRL のアクセシビリティ、有効性、整合性を確認します。 - CRL ファイルで破損または不完全なコンテンツがないか調べます。 |
| **AADSTS500175: チェーン内の 1 つの証明書の証明書失効リスト (CRL) がないため、失効チェックに失敗しました。** | 証明書失効チェック中に、Microsoft Entra必要なセグメントまたは証明書失効リスト (CRL) の一部を見つけることができませんでした。 | - CRL 配布ポイント (CDP) からダウンロードされた CRL ファイルが破損しているか、切り捨てられます。- CA による CRL の発行が正しくないか不完全です。- CRL のダウンロードが不完全または失敗する原因となっているネットワークの問題。- CRL 配布ポイントの URL またはファイル セグメントの構成が正しくありません。 | - CRL の整合性を確認する- CRL の再発行または再生成 - ネットワークとプロキシの設定を確認する - すべての CA で正しい CDP 設定を確認する |
| **AADSTS500176: 証明書を発行した証明機関がテナントに設定されていません。 管理者に問い合わせてください。** | Microsoft Entra信頼された証明書ストアに発行元 CA 証明書が見つかりませんでした。 これにより、ユーザー証明書の信頼チェーンの検証が正常に行われなくなります。 | - 発行元の CA 証明書 (ルートまたは中間) が、Microsoft Entra ID信頼された証明書の一覧にアップロードまたは構成されていません。- クライアントまたはデバイスに格納されている証明書チェーンが、信頼された CA 証明書に適切にリンクされていません。- 証明書チェーン内のサブジェクト キー識別子 (SKI) と機関キー識別子 (AKI) の参照が一致しない、または見つからない。- 発行元の証明書の有効期限が切れているか、失効しているか、または無効である可能性があります。 | - テナント管理者は、関連するすべてのルート証明書と中間 CA 証明書を、Microsoft Entra 管理センター経由で信頼された証明書ストアMicrosoft Entraにアップロードする必要があります。- 発行元 CA 証明書の SKI がユーザーの証明書の AKI と一致していることを確認して、適切なチェーン リンケージを確保します。- certutil や OpenSSL などのツールを使用して、完全な証明書チェーンが損なわれなく、信頼されていることを確認します。 - チェーンの有効性を維持するために、信頼されたストア内の期限切れまたは失効した CA 証明書を置き換えます。 |
| **AADSTS500177: 証明書失効リスト (CRL) が正しく構成されていません。 デルタ CRL 配布ポイントは、対応するベース CRL 配布ポイントなしで構成されます。 管理者に問い合わせてください。** | CA 構成に Delta CRL 配布ポイントが含まれているが、対応するベース CRL 配布ポイントが見つからないか、正しく構成されていないことを示します。 | - 証明書または CA の設定で構成されている CRL 配布ポイント (CDP) が無効、アクセスできない、または正しくない URL です。- CA が CRL を正しく公開していないか、CRL の有効期限が切れているので、検証エラーが発生しています。- ファイアウォール規則、プロキシの制限、またはネットワーク接続の問題により、デバイスまたはMicrosoft Entra ID サービスが CRL URL にアクセスできません。- Microsoft Entraまたは CRL 処理に関連する発行元証明機関で設定が正しく構成されていません。 | - CRL 配布ポイントを確認し、パブリックにアクセスできる正確な URL に更新します。- 有効期限が切れる前に CRL が定期的に公開および更新されていることを確認します。 可能であれば、CRL の公開を自動化します。- ファイアウォール、プロキシ、またはセキュリティ デバイスの規則を更新して、CRL 配布ポイントへの必要なネットワーク トラフィックを許可します。- ダウンロードした CRL の破損または切り捨てを確認し、必要に応じて再発行します。- CRL の発行、URL、検証ポリシーに関連するMicrosoft Entra IDと CA の構成を再確認します。 |
| **AADSTS500178: {type} の有効な CRL セグメントを取得できません。 後でもう一度やり直してください。** | Microsoft Entra IDは、証明書の検証中に証明書失効リスト (CRL) の必要なすべてのセグメントをダウンロードまたは処理できません。 | - CRL は複数のセグメントで発行され、1 つ以上のセグメントが見つからない、破損している、またはアクセスできない。- ネットワーク制限またはファイアウォールによって、1 つ以上の CRL セグメントへのアクセスがブロックされます。- 使用可能な CRL セグメントの有効期限が切れているか、適切に更新されていない可能性があります。- セグメントがホストされている証明書の CRL 配布ポイントの URL が正しくないか、エントリがありません。 | - 配布ポイントからすべての CRL セグメントを手動でダウンロードし、完全性と有効性を確認します。- すべての CRL セグメント URL が正しく構成され、アクセス可能であることを確認します。 CDP URL が変更された場合は、証明書または CA 構成を更新します。- CA がすべての CRL セグメントを正しく公開および維持し、破損や欠落がないことを確認します。 |
| **AADSTS500179: CRL 検証がタイムアウトしました。後でもう一度やり直してください。** | CRL のダウンロードがタイムアウトしたか、中断されました。 | - CRL サイズが制限を超えています- ネットワーク待機時間または不安定性 | - CRL サイズを 20 MB (商用Azure) または 45 MB (米国政府の場合はAzure) 以下にしてください- `Next Update` 間隔を少なくとも 1 週間に設定する- サインイン ログを使用して CRL ダウンロードのパフォーマンスを監視します。 |
| **AADSTS500183: 証明書が取り消されました。 管理者に問い合わせてください** | クライアント デバイスが発行元 CA によって失効した証明書を提示したため、認証の試行に失敗しました。 | 認証に使用される証明書は、証明書失効リスト (CRL) に含まれているか、CA によって失効済みとしてフラグが付けられます。 | - テナント管理者は、新しい証明書が正しくプロビジョニングされ、Microsoft Entra IDによって信頼されていることを確認する必要があります。- CA によって公開された CRL とデルタ CRL が最新であり、デバイスでアクセス可能であることを確認します。 |
| **AADSTS2205011: ダウンロードした証明書失効リスト (CRL) が有効な ASN.1 エンコード形式ではありません。 管理者に問い合わせてください。** | Microsoft Entraによってフェッチされた CRL ファイルは、CRL データの解析と検証に必要な抽象構文表記法 1 (ASN.1) Distinguished Encoding Rules (DER) 標準に従って正しくエンコードされていません。 | - CRL ファイルが破損しているか、パブリケーションまたは転送中に切り捨てられます。- CRL が CA によって正しく生成またはエンコードされておらず、ASN.1 DER 標準に準拠していません。- ファイル形式の変換 (不適切な base64/PEM エンコードなど) によって CRL データが破損しました。 | - CRL を手動でダウンロードし、openssl や特殊な ASN.1 パーサーなどのツールで調べて、破損しているか形式が正しくないかどうかを確認します。- CA から CRL を再生成して再発行し、ASN.1 DER エンコード標準に準拠していることを確認します。- CRL を生成する CA ソフトウェアまたはツールが RFC 5280 に準拠し、ASN.1 DER 形式で CRL を正しくエンコードしていることを確認します。 |
| **AADSTS2205012: 対話型サインイン中に '{uri}' から証明書失効リスト (CRL) をダウンロードしようとするとタイムアウトしました。もう一度ダウンロードしようとしています。 数分後にもう一度お試しください。** | Microsoft Entra ID指定した URL から予想時間内に CRL ファイルを取得できませんでした。 | - Microsoft Entra IDサービスは、ネットワークの停止、ファイアウォールの制限、または DNS エラーのために CRL 配布ポイントに到達できません。- CRL をホストしているサーバーがダウンしているか、過負荷になっているか、タイムリーに応答していません。- 大きな CRL のダウンロードに時間がかかり、タイムアウトが発生する可能性があります。 | - ダウンロード時間を短縮するために、デルタ CRL を使用して CRL ファイル のサイズを小さくし、更新頻度を高める。- オフピーク時に CRL を発行または更新して、サーバーの負荷を軽減し、応答時間を向上させます。- CRL ホスティング サーバーの高可用性とパフォーマンスを監視および維持します。 |
| **AADSTS2205013: 証明書失効リスト (CRL) のダウンロードは現在進行中です。 数分後にもう一度お試しください。** | 複数の認証試行が同時に CRL のダウンロードをトリガーし、システムが現在の CRL の取得を処理している場合に発生します。 | - CRL の有効期限が切れるか、有効期限が切れようとしている場合、複数のユーザーが同時にサインインすると、新しい CRL のダウンロードが同時に試行される可能性があります。- Microsoft Entra IDは、同じ CRL の同時ダウンロードを防ぐためにロック メカニズムを適用して、負荷と潜在的な競合状態を軽減します。 これにより、この再試行メッセージで一部の認証要求が一時的に拒否されます。- ユーザーの人口が多いか、サインインバーストが多いと、このエラーの頻度が増加する可能性があります。 | - サインインを再試行する前に、進行中の CRL のダウンロードが完了するまで数分かかります。- 強制的な再ダウンロードを減らすために、有効期限が切れる前に CRL が定期的に公開および更新されていることを確認します。 |
| **AADSTS2205014:対話型サインイン中に '{uri}' から証明書失効リスト (CRL) をダウンロードしようとすると、許可される最大サイズ ({size} バイト) を超えました。 CRL は CRL のサービス ダウンロード制限でプロビジョニングされています。数分後にもう一度お試しください。** | Microsoft Entra ID がダウンロードしようとした CRL ファイルは、サービスのサイズ制限を超えています。 Microsoft Entraは、より高い制限でバックグラウンドでダウンロードしようとします。 | - CA によって発行された CRL ファイルが大きすぎます。多くの場合、失効した証明書の数が多いためです。- 失効した証明書がクリーンアップされない場合、または CA が失効データの有効期限を長く保持している場合、大きな CRL が発生する可能性があります。- CRL サイズが大きいと、証明書ベースの認証時にダウンロード時間とリソース消費量が増加します。 | - CA データベースから古い証明書または期限切れの失効した証明書を削除します。- CRL の有効期間を短縮し、発行頻度を増やして CRL サイズを管理できるようにします。- 差分 CRL を実装して、増分失効情報のみを配布し、帯域幅を削減します。 |
| **AADSTS2205015: 証明書失効リスト (CRL) が署名の検証に失敗しました。 予期される SubjectKeyIdentifier {expectedSKI} が CRL の AuthorityKeyIdentifier {crlAK} と一致しません。 管理者に問い合わせてください。** | CRL の暗号化署名を検証できませんでした。これは、サブジェクト キー識別子 (SKI) が、Microsoft Entra IDで予期される機関キー識別子 (AKI) と一致しない証明書によって CRL が署名されているためです。 | - CRL の署名に使用された CA 証明書が変更されましたが、新しい SKI が信頼された証明書の一覧で更新または同期されませんでした。- PKI 階層の構成が正しくないため、CRL が古くなっているか、一致していません。- 信頼された証明書リストに中間CA証明書が不正確または欠落しています。- CRL 署名証明書に、CRL の署名に適切なキー使用法がない可能性があります。 | - CRL に署名する CA 証明書のサブジェクト キー識別子 (SKI) が CRL の機関キー識別子 (AKI) と一致するかどうかを確認します。- 署名 CA 証明書がアップロードされ、Microsoft Entra IDで信頼されていることを確認します。- CRL の署名に使用される CA 証明書で、適切なキー使用法フラグ (CRL 署名など) が有効になっていることを確認し、証明書チェーンが損なわれないままであることを確認します。- Microsoft Entra IDの信頼された証明機関の一覧に正しいルート CA 証明書と中間 CA 証明書をアップロードまたは更新し、CRL の署名に使用される証明書が含まれており、正しく構成されていることを確認します。 |
| **AADSTS7000214: 証明書が取り消されました。** | 証明書が取り消されました。 | - CRL に記載されている証明書 | - 失効した証明書を置き換える- CA とともに失効理由を調査する- 証明書のライフサイクルと更新を監視する |

### よく寄せられる質問

この次のセクションでは、証明書失効リストに関連する一般的な質問と回答について説明します。

#### CRL サイズに制限はありますか?

次の CRL サイズ制限が適用されます。

- 対話型サインインのダウンロード制限: 20 MB (Azure Global、GCC を含む)、45 MB (米国政府 Azure、GCC High、国防総省を含む)
- サービス ダウンロードの制限: 65 MB (Azure Global には GCC が含まれます)、150 MB (GCC High、国防省を含む Azure 米国政府)

CRL のダウンロードが失敗すると、次のメッセージが表示されます。

"{uri} からダウンロードされた証明書失効リスト (CRL) が、Microsoft Entra IDの CRL の最大許容サイズ ({size} バイト) を超えました。 数分後にもう一度やり直してください。 問題が解決しない場合は、テナント管理者に問い合わせてください。

ダウンロードはバックグラウンドに残り、上限が高くなります。

これらの制限の影響を確認しており、それらを削除する計画があります。

#### 有効な証明書失効リスト (CRL) エンドポイント セットが表示されますが、CRL 失効が表示されないのはなぜですか?

- CRL 配布ポイントが有効な HTTP URL に設定されていることを確認します。
- CRL 配布ポイントにインターネットに接続する URL 経由でアクセスできることを確認します。
- CRL サイズが制限内にあることを確認します。

#### 証明書をすぐに取り消す方法

手順に従って [、証明書を手動で取り消します](https://learn.microsoft.com/ja-jp/entra/identity/authentication/certificate-based-authentication-federation-get-started#step-3-configure-revocation)。

#### 特定の CA に対して証明書失効チェックを有効または無効にする方法

証明書を取り消すことができないため、証明書失効リスト (CRL) チェックを無効にしないことをお勧めします。 ただし、CRL チェックに関する問題を調査する必要がある場合は、MICROSOFT ENTRA ADMIN CENTERでの CRL チェックから CA を除外できます。 CBA 認証方法ポリシーで、[ **構成** ] を選択し、[ **除外の追加]** を選択します。 除外する CA を選択し、[ **追加**] を選択します。

#### CRL エンドポイントが構成されると、エンド ユーザーはサインインできず、"AADSTS500173: CRL をダウンロードできません。" CRL 配布ポイントから取得したステータスコードが無効で「Forbidden」です。

問題が原因でMicrosoft Entraが CRL をダウンロードできない場合、多くの場合、ファイアウォールの制限が原因です。 ほとんどの場合、必要な IP アドレスを許可するようにファイアウォール規則を更新して問題を解決Microsoft Entra CRL を正常にダウンロードできます。 詳細については、[公式 Microsoft ダウンロード センターからの Azure IP 範囲とサービス タグ - パブリック クラウドをダウンロード](https://www.microsoft.com/download/details.aspx?id=56519) を参照してください。

#### CA の CRL を見つける方法、または "AADSTS2205015: 証明書失効リスト (CRL) が署名検証に失敗しました" というエラーのトラブルシューティングを行うにはどうすればよいですか?

CRL をダウンロードし、CA 証明書と CRL 情報を比較して、 `crlDistributionPoint` 値が追加する CA に対して有効であることを検証します。 CA の発行者サブジェクト キー識別子 (SKI) を CRL の機関キー識別子 (AKI) と照合することで、対応する CA への CRL を構成できます (CA 発行者 SKI == CRL AKI)。

次の表と図は、CA 証明書からダウンロードした CRL の属性に情報をマップする方法を示しています。

| CA 証明書の情報 | = | ダウンロードした CRL 情報 |
| --- | --- | --- |
| サブジェクト | = | 発行者 |
| サブジェクト キー識別子 (SKI) | = | 機関キー識別子 (KeyID) |

[Image: CA 証明書フィールドと CRL 情報を比較するスクリーンショット。]

- [Microsoft Entra CBA 証明書失効リスト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-certificate-revocation-list)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-certificate-based-authentication-certificateuserids"} -->
## Microsoft Entra ID の certificateUserIds 属性へのマッピング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-certificateuserids
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: フェデレーションなしの Microsoft Entra 証明書ベースの認証に使用する証明書ユーザー ID について

Microsoft Entra ID のユーザー オブジェクトには、certificateUserIds という名前の属性があります。

- certificateUserIds 属性は複数値で、最大 10 個の値を保持できます。
- 各値の文字数は 1024 文字以下にする必要があります。
- 各値は一意である必要があります。 1 つのユーザー アカウントに値が存在する場合、その値を同じ Microsoft Entra テナント内の他のユーザー アカウントに書き込むことはできません。
- この値は電子メール ID 形式である必要はありません。 certificateUserIds 属性は、 *bob@woodgrove* や *bob@local*などのルーティング不可能なユーザー プリンシパル名 (UPN) を格納できます。

注

各値は Microsoft Entra ID で一意である必要がありますが、複数のユーザー名バインドを実装することで、1 つの証明書を複数のアカウントにマップできます。 詳細については、「 [複数のユーザー名バインド」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-technical-deep-dive#secure-microsoft-entra-configuration-by-using-multiple-username-bindings)参照してください。

### 証明書ユーザー ID でサポートされるパターン

certificateUserIds に格納される値は、次の表で説明する形式にする必要があります。 X509: &lt;マッピング&gt; プレフィックスでは大文字と小文字が区別されます。

| 証明書マッピング フィールド | certificateUserIds の値の例 |
| --- | --- |
| プリンシパルネーム | `X509:<PN>bob@woodgrove.com` |
| プリンシパルネーム | `X509:<PN>bob@woodgrove` |
| RFC822Name | `X509:<RFC822>user@woodgrove.com` |
| 発行者と対象 | `X509:<I>DC=com,DC=contoso,CN=CONTOSO-DC-CA<S>DC=com,DC=contoso,OU=UserAccounts,CN=mfatest` |
| サブジェクト | `X509:<S>DC=com,DC=contoso,OU=UserAccounts,CN=mfatest` |
| スキー | `X509:<SKI>aB1cD2eF3gH4iJ5kL6mN7oP8qR` |
| SHA1PublicKey | `X509:<SHA1-PUKEY>cD2eF3gH4iJ5kL6mN7oP8qR9sT` |
| IssuerAndSerialNumber（発行者およびシリアル番号） | `X509:<I>DC=com,DC=contoso,CN=CONTOSO-DC-CA<SR>eF3gH4iJ5kL6mN7oP8qR9sT0uV` シリアル番号の正しい値を取得するには、このコマンドを実行し、certificateUserIds に示されている値を格納します。**構文**:`Certutil –dump –v [~certificate path~] >> [~dumpFile path~]`**例**: `certutil -dump -v firstusercert.cer >> firstCertDump.txt` |

### certificateUserIds を更新するロール

certificateUserIds を更新するには、クラウド専用ユーザーに少なくとも **特権認証管理者** ロールが必要です。 クラウド専用ユーザーの場合は、Microsoft Entra 管理センターまたは Microsoft Graph を使用して certificateUserIds の値を更新できます。

同期されたユーザーは、certificateUserIds を更新するために、少なくとも **ハイブリッド ID 管理者** ロールを持っている必要があります。 オンプレミスから値を同期して certificateUserIds を更新するには、Microsoft Entra Connect のみが使用できます。

注

Active Directory 管理者は、同期されたアカウントの Microsoft Entra ID の certificateUserIds 値に影響を与える変更を行うことができます。 管理者は、同期されたユーザー アカウントに対する代理管理特権を持つアカウント、または Microsoft Entra Connect サーバーに対する管理者権限を含めることができます。

### PowerShell モジュールを使用してエンド ユーザー証明書からユーザーの正しい CertificateUserIds 値を検索する方法

証明書 UserId は、テナントの UserName バインド構成に従って、その値の特定のパターンに従います。 次の PowerShell コマンドは、管理者がエンド ユーザー証明書からユーザーの Certificate UserIds 属性の正確な値を取得するのに役立ちます。 管理者は、特定のユーザー名バインドのユーザーの Certificate UserIds 属性の現在の値を取得し、Certificate UserIds 属性の値を設定することもできます。

詳細については、 [Microsoft Entra PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/entra-powershell/installation) と [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) を参照してください。

1. PowerShell を起動します。
2. Microsoft Graph PowerShell SDK をインストールおよびインポートします。

    ```powershell
    Install-Module Microsoft.Graph -Scope CurrentUser
    Import-Module Microsoft.Graph.Authentication
    Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
    ```
3. Microsoft Entra PowerShell モジュールをインストールする (最低限必要なバージョンは 1.0.6)

    ```powershell
        Install-Module -Name Microsoft.Entra
    ```

CertificateBasedAuthentication モジュールの詳細については [、こちらを参照してください](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.entra)

#### Get-EntraUserCBAAuthorizationInfo

Get-EntraUserCBAAuthorizationInfo は、証明書ベースの認証識別子など、Microsoft Entra ID ユーザーの承認情報を取得するのに役立ちます。

**構文:** Get-EntraUserCBAAuthorizationInfo `[-UserId] <String>``[-Raw]``[<CommonParameters>]`

**例 1: ユーザー プリンシパル名でユーザーの認証情報を取得する**

```powershell
Connect-Entra -Scopes 'User.Read.All' 
Get-EntraUserCBAAuthorizationInfo -UserId ‘user@contoso.com'
```

**応答：**

| 特性 | 価値 |
| --- | --- |
| ID (アイディー) | `aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb` |
| DisplayName | `Contoso User` |
| ユーザープリンシパルネーム | `user@contoso.com` |
| ユーザータイプ | `Member` |
| 認証情報 | `@{CertificateUserIds=System.Object[]; RawAuthorizationInfo=System.Collections.Hashtable}` |

このコマンドは、指定されたユーザー プリンシパル名を持つユーザーの認証情報を取得します。

**例 2: ユーザーの認証情報を取得する**

```powershell
Connect-Entra -Scopes 'User.Read.All'
$userInfo = Get-EntraUserCBAAuthorizationInfo -UserId 'user@contoso.com'
$userInfo.AuthorizationInfo.CertificateUserIds | Format-Table Type, TypeName, Value
```

**応答：**

| タイプ | タイプ名 | 価値 |
| --- | --- | --- |
| PN | プリンシパルネーム | `user@contoso.com` |
| S | サブジェクト | `CN=user@contoso.com` |
| スキー | サブジェクトキー識別子 | `1111112222333344445555` |

この例では、認証情報を取得します。

**例 3: 特定の証明書ユーザー ID を抽出する**

```powershell
Connect-Entra -Scopes 'User.Read.All'
$userInfo = Get-EntraUserCBAAuthorizationInfo -UserId user@contoso.com'
$userInfo.AuthorizationInfo.CertificateUserIds | Where-Object Type -eq "PN" | Select-Object -ExpandProperty Value
```

**応答：**user@contoso.com

この例では、承認情報を取得し、プリンシパル名証明書の値のみを表示するようにフィルター処理します。

#### Get-EntraUserCertificateUserIdsFromCertificate

Certificate-Based 認証のために CertificateUserID を構成するために必要な証明書の値を持つオブジェクトを Microsoft Entra ID で返します。

**構文:** Get-EntraUserCertificateUserIdsFromCertificate `[-Path] <string>``[[-Certificate] <System.Security.Cryptography.X509Certificates.X509Certificate2> [-CertificateMapping] <string>]``[<CommonParameters>]`

証明書の値が長すぎる場合は、出力をファイルに送信し、そこからコピーできます。

```powershell
Connect-Entra -Scopes 'User.Read.All'
Get-EntraUserCertificateUserIdsFromCertificate -Path C:\Downloads\test.pem | Format-List | Out-File -FilePath ".\certificateUserIds.txt"
```

**例 1: 証明書パスから証明書オブジェクトを取得する**

```powershell
Get-EntraUserCertificateUserIdsFromCertificate -Path 'C:\path\to\certificate.cer'
```

**応答：**

| 名前 | 価値 |
| --- | --- |
| サブジェクト | X509:`<S>DC=com,DC=contoso,OU=UserAccounts,CN=user` |
| IssuerAndSerialNumber（発行者およびシリアル番号） | X509:`<I>DC=com,DC=contoso,CN=CONTOSO-DC-CA<SR>eF3gH4iJ5kL6mN7oP8qR9sV0uD` |
| RFC822Name | X509:`<RFC822>user@contoso.com` |
| SHA1PublicKey | X509:`<SHA1-PUKEY>cA2eB3gH4iJ5kL6mN7oP8qR9sT` |
| 発行者と対象 | X509:`<I>DC=com,DC=contoso,CN=CONTOSO-DC-CA<S>DC=com,DC=contoso,OU=UserAccounts,CN=user` |
| スキー | X509:`<SKI>aB1cD2eF3gH4iJ5kL6mN7oP8qR` |
| プリンシパルネーム | X509:`<PN>user@contoso.com` |

この例では、考えられるすべての証明書マッピングをオブジェクトとして取得する方法を示します。

**例 2: 証明書パスと証明書マッピングから証明書オブジェクトを取得する**

```powershell
Get-EntraUserCertificateUserIdsFromCertificate -Path 'C:\path\to\certificate.cer' -CertificateMapping 'Subject' 
```

**応答：**`X509:<S>DC=com,DC=contoso,OU=UserAccounts,CN=user`

このコマンドは PrincipalName プロパティを返します。

**例 3: 証明書から証明書オブジェクトを取得する**

```powershell
$text = "-----BEGIN CERTIFICATE-----
MIIDiz...=
-----END CERTIFICATE-----"
$bytes = [System.Text.Encoding]::UTF8.GetBytes($text)
$certificate = [System.Security.Cryptography.X509Certificates.X509Certificate2]::new($bytes)
Get-EntraUserCertificateUserIdsFromCertificate -Certificate $certificate -CertificateMapping 'Subject'
```

**応答：**`X509:<S>DC=com,DC=contoso,OU=UserAccounts,CN=user`

このコマンドは PrincipalName プロパティを返します。

#### Set-EntraUserCBACertificateUserId

証明書ファイルまたはオブジェクトを使用して、Microsoft Entra ID のユーザーの証明書ベースの認証ユーザー ID を設定します。

**構文** Set-EntraUserCBACertificateUserId `-UserId <string>``[-CertPath <string>]``[-Cert <System.Security.Cryptography.X509Certificates.X509Certificate2>]``-CertificateMapping <string[]>``[<CommonParameters>]`

**例 1: 証明書パスを使用してユーザーの証明書認証情報を更新する**

```powershell
Connect-Entra -Scopes 'Directory.ReadWrite.All', 'User.ReadWrite.All'
Set-EntraUserCBACertificateUserId -UserId ‘user@contoso.com' -CertPath 'C:\path\to\certificate.cer' -CertificateMapping @('Subject', 'PrincipalName')
```

次の使用例は、証明書ファイルを使用して指定したユーザーの証明書ユーザー ID を設定し、Subject フィールドと PrincipalName フィールドの両方をマッピングします。 Get-EntraUserCBAAuthorizationInfo コマンドを使用して、更新された詳細を表示できます。

**例 2: 証明書を使用してユーザーの証明書認証情報を更新する**

```powershell
Connect-Entra -Scopes 'Directory.ReadWrite.All', 'User.ReadWrite.All'
$text = '-----BEGIN CERTIFICATE-----
MIIDiz...=
-----END CERTIFICATE-----'
$bytes = [System.Text.Encoding]::UTF8.GetBytes($text)
$certificate = [System.Security.Cryptography.X509Certificates.X509Certificate2]::new($bytes)
Set-EntraUserCBACertificateUserId -UserId user@contoso.com' -Cert $certificate -CertificateMapping @('RFC822Name', 'SKI')
```

次の使用例は、RFC822Name フィールドと SKI フィールドをマッピングする証明書オブジェクトを使用して、指定されたユーザーの証明書ユーザー ID を設定します。 Get-EntraUserCBAAuthorizationInfo コマンドを使用して、更新された詳細を表示できます。

### Microsoft Entra 管理センターを使用して certificateUserIds を更新する

ユーザーの certificateUserIds を更新するには、次の手順に従います。

1. クラウド専用ユーザーの場合は少なくとも[特権認証管理者](https://entra.microsoft.com)として、または同期されたユーザーの場合は少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator)として[、Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **[すべてのユーザー**] を検索して選択します。

    [Image: テスト ユーザー アカウントのスクリーンショット。]
3. ユーザーを選択し、[プロパティの編集]選択します。
4. **[承認情報**] の横にある [表示] を選択**します**。

    [Image: [承認情報の表示] のスクリーンショット。]
5. [ **証明書ユーザー ID の編集] を選択します**。

    [Image: 証明書ユーザー ID の編集のスクリーンショット。]
6. **追加**を選択します。

    [Image: certificateUserIds を追加する方法のスクリーンショット。]
7. 値を入力し、[ **保存]** を選択します。 最大 4 つの値 (120 文字ずつ) を追加できます。

    [Image: certificateUserIds に入力する値のスクリーンショット。]

### Microsoft Graph クエリを使用して certificateUserIds を更新する

次の例は、Microsoft Graph を使用して certificateUserIds を検索して更新する方法を示しています。

#### certificateUserIds を検索する

認証されている呼び出し元は、Microsoft Graph クエリを実行して、特定の certificateUserId 値を持つすべてのユーザーを検索できます。 Microsoft Graph [ユーザー](https://learn.microsoft.com/ja-jp/graph/api/resources/user) オブジェクトでは、certificateUserIds のコレクションが **authorizationInfo** プロパティに格納されます。

すべてのユーザー オブジェクトの certificateUserIds を取得するには:

```msgraph
GET https://graph.microsoft.com/v1.0/users?$select=authorizationinfo
ConsistencyLevel: eventual
```

ユーザーの ObjectId によって特定のユーザーの certificateUserIds を取得するには:

```msgraph
GET https://graph.microsoft.com/v1.0/users/{user-object-id}?$select=authorizationinfo
ConsistencyLevel: eventual
```

certificateUserIds 内の特定の値を持つユーザー オブジェクトを取得するには:

```msgraph
GET https://graph.microsoft.com/v1.0/users?$select=authorizationinfo&$filter=authorizationInfo/certificateUserIds/any(x:x eq 'X509:<PN>user@contoso.com')&$count=true
ConsistencyLevel: eventual
```

`not` および `startsWith` 演算子を使用して、フィルター条件を一致させることもできます。 certificateUserIds オブジェクトに対してフィルター処理するには、要求に `$count=true` クエリ文字列を含める必要があり、 **ConsistencyLevel** ヘッダーを `eventual`に設定する必要があります。

#### certificateUserIds を更新する

PATCH 要求を実行して、特定のユーザーの certificateUserIds を更新します。

##### 要求本文

```http
PATCH https://graph.microsoft.com/v1.0/users/{user-object-id}
Content-Type: application/json
{
    "authorizationInfo": {
        "certificateUserIds": [
            "X509:<PN>123456789098765@mil"
        ]
    }
}
```

### Microsoft Graph PowerShell コマンドを使用して certificateUserIds を更新する

この構成では、 [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) を使用できます。

1. PowerShell を管理者特権で起動します。
2. Microsoft Graph PowerShell SDK をインストールおよびインポートします。

    ```powershell
        Install-Module Microsoft.Graph -Scope CurrentUser
        Import-Module Microsoft.Graph.Authentication
        Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
    ```
3. テナントに接続し、すべてを受け入れます。

    ```powershell
       Connect-MGGraph -Scopes "Directory.ReadWrite.All", "User.ReadWrite.All" -TenantId <tenantId>
    ```
4. 特定のユーザーの certificateUserIds 属性を一覧表示します。

    ```powershell
      $results = Invoke-MGGraphRequest -Method get -Uri 'https://graph.microsoft.com/v1.0/users/<userId>?$select=authorizationinfo' -OutputType PSObject -Headers @{'ConsistencyLevel' = 'eventual' }
      #list certificateUserIds
      $results.authorizationInfo
    ```
5. certificateUserIds 値を使用して変数を作成します。

    ```powershell
      #Create a new variable to prepare the change. Ensure that you list any existing values you want to keep as this operation will overwrite the existing value
      $params = @{
            authorizationInfo = @{
                  certificateUserIds = @(
                  "X509:<SKI>gH4iJ5kL6mN7oP8qR9sT0uV1wX", 
                  "X509:<PN>user@contoso.com"
                  )
            }
      }
    ```
6. certificateUserIds 属性を更新します。

    ```powershell
       $results = Invoke-MGGraphRequest -Method patch -Uri 'https://graph.microsoft.com/v1.0/users/<UserId>/?$select=authorizationinfo' -OutputType PSObject -Headers @{'ConsistencyLevel' = 'eventual' } -Body $params
    ```

**ユーザー オブジェクトを使用して certificateUserIds を更新する**

1. ユーザー オブジェクトを取得します。

    ```powershell
      $userObjectId = "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb"
      $user = Get-MgUser -UserId $userObjectId -Property AuthorizationInfo
    ```
2. ユーザー オブジェクトの certificateUserIds 属性を更新します。

    ```powershell
       $user.AuthorizationInfo.certificateUserIds = @("X509:<SKI>iJ5kL6mN7oP8qR9sT0uV1wX2yZ", "X509:<PN>user1@contoso.com") 
       Update-MgUser -UserId $userObjectId -AuthorizationInfo $user.AuthorizationInfo
    ```

### Microsoft Entra Connect を使用して certificateUserIds を更新する

Microsoft Entra Connect では、オンプレミスの Active Directory 環境から certificateUserIds への値の同期がサポートされています。 オンプレミスの Active Directory では、証明書ベースの認証と複数のユーザー名バインディングがサポートされています。 [Microsoft Entra Connect](https://www.microsoft.com/download/details.aspx?id=47594) の最新バージョンを使用していることを確認します。

これらのマッピング方法を使用するには、オンプレミスの Active Directoryのユーザー オブジェクトに altSecurityIdentities 属性を設定する必要があります。 さらに、[KB5014754](https://support.microsoft.com/topic/kb5014754-certificate-based-authentication-changes-on-windows-domain-controllers-ad2c23b0-15d8-4340-a468-4d4f3b188f16)で説明されているように、Windows ドメイン コントローラーに証明書ベースの認証の変更を適用した後、オンプレミスの Active Directory の強力な証明書バインドの適用要件を満たすために、一部の再利用不可な (Type=strong) マッピング方法を実装している可能性があります。

同期エラーを回避するには、同期される値が certificateUserIds でサポートされている形式のいずれかに従っていることを確認します。

開始する前に、オンプレミスの Active Directoryから同期されているすべてのユーザー アカウントが次の条件を満たすことを確認します。

- altSecurityIdentities 属性の値が 10 個以下であること
- 1,024 文字を超える値がありません
- 重複した値がない

    重複する値が、1 つの証明書を複数のオンプレミスの Active Directory アカウントにマップすることを意図したものであるかどうかを慎重に検討してください。 詳細については、「 [複数のユーザー名バインド」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-technical-deep-dive#secure-microsoft-entra-configuration-by-using-multiple-username-bindings)参照してください。

    注

    特定のシナリオでは、ユーザーの 1 つのサブセットが、1 つの証明書を複数のオンプレミスの Active Directory アカウントにマップするという業務上の正当な理由を持つ場合があります。 これらのシナリオを確認し、必要に応じて、オンプレミスの Active Directory と Microsoft Entra ID の両方で複数のアカウントにマップする個別のマッピング方法を実装します。

**certificateUserIds の継続的な同期に関する考慮事項**

- オンプレミスの Active Directoryの値を設定するためのプロビジョニング プロセスで適切な検疫が実装されていることを確認します。 現在の有効な証明書に関連付けられている値のみが設定されます。
- 値は、対応する証明書の有効期限が切れたり失効したりすると削除されます。
- 1024 文字を超える値は設定されません。
- 重複する値はプロビジョニングされません。
- Microsoft Entra Connect Health を使用して同期を監視します。

userPrincipalName を certificateUserIds に同期するように Microsoft Entra Connect を構成するには、次の手順に従います。

1. Microsoft Entra Connect サーバーで、 **同期規則エディター**を見つけて起動します。
2. [**方向**] を選択し、[**外向き**] を選択します。

    [Image: 送信同期規則のスクリーンショット。]
3. **Microsoft Entra ID – ユーザー ID に対する規則を**見つけ、[**編集]** を選択し、[**はい**] を選択して確定します。

    [Image: ユーザー ID のスクリーンショット。]
4. [優先順位の ] フィールドに高い数値を入力し、[次へ ] を選択します。

    [Image: 優先順位の値のスクリーンショット。]
5. [**変換**]&gt;**[変換の追加]**を選択します。 新しい変換を作成する前に、変換の一覧を下にスクロールする必要がある場合があります。

#### X509:&lt;PN&gt;PrincipalNameValue の同期

X509:&lt;PN&gt;PrincipalNameValue を同期するには、送信同期規則を作成し、フローの種類で **[式]** を選択します。 **certificateUserIds** としてターゲット属性を選択し、ソース フィールドに次の式を追加します。 ソース属性が userPrincipalName でない場合は、それに応じて式を変更できます。

```
"X509:<PN>"&[userPrincipalName]
```

[Image: x509 を同期する方法のスクリーンショット。]

#### X509:&lt;RFC822&gt;RFC822Name の同期

X509:&lt;RFC822&gt;RFC822Name を同期するには、送信同期規則を作成し、フローの種類で **[式]** を選択します。 **certificateUserIds** としてターゲット属性を選択し、ソース フィールドに次の式を追加します。 ソース属性が userPrincipalName でない場合は、それに応じて式を変更できます。

```
"X509:<RFC822>"&[userPrincipalName]
```

[Image: RFC822Name を同期する方法のスクリーンショット。]

1. **[ターゲット属性]** を選択し、[**certificateUserIds**] を選択し、[**ソース**] を選択し、[**userPrincipalName**] を選択して、[**保存]** を選択します。

    [Image: ルールを保存する方法のスクリーンショット。]
2. **[OK] を**選択して確定します。

重要

上記の例では、変換規則のソース属性として userPrincipalName 属性を使用しています。 任意の、適切な値を持つ利用可能な属性を使用できます。 たとえば、一部の組織ではメール属性を使用します。 より複雑な変換規則については、「[Microsoft Entra Connect Sync: 宣言型プロビジョニング式について」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/concept-azure-ad-connect-sync-declarative-provisioning-expressions)参照してください。

宣言型プロビジョニング式の詳細については、「 [Microsoft Entra Connect: 宣言型プロビジョニング式」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/concept-azure-ad-connect-sync-declarative-provisioning-expressions)参照してください。

### altSecurityIdentities 属性を Active Directory から Microsoft Entra certificateUserIds に同期する

altSecurityIdentities 属性は、既定の属性セットの一部ではありません。 管理者は、メタバースの person オブジェクトに新しい属性を追加し、適切な同期規則を作成して、このデータを Microsoft Entra ID の certificateUserIds に中継する必要があります。

1. メタバース デザイナーを開き、person オブジェクトを選択します。 alternativeSecurityId 属性を作成するには、[ **新しい属性**] を選択します。 属性サイズを最大 1024 文字 (certificateUserIds でサポートされる最大長) にするには、[ **文字列 ] (インデックスなし)** を選択します。 **文字列 (インデックス可能)** を選択した場合、属性値の最大サイズは 448 文字です。 [ **複数値**] を選択していることを確認します。

    [Image: 新しい属性を作成する方法のスクリーンショット。]
2. メタバース デザイナーを開き、alternativeSecurityId を選択して person オブジェクトに追加します。

    [Image: person オブジェクトに alternativeSecurityId を追加する方法のスクリーンショット。]
3. altSecurityIdentities から alternativeSecurityId 属性に変換する受信同期規則を作成します。

    受信規則では、次のオプションを使用します。

    | 回答内容 | 価値 |
    | --- | --- |
    | 名前 | 規則のわかりやすい名前 (例: In from Active Directory - altSecurityIdentities) |
    | 接続先システム | オンプレミスの Active Directory ドメイン |
    | 接続先システム オブジェクトの種類 | ユーザー |
    | メタバース オブジェクトの種類 | 個人 |
    | 優先順位 | 現在使用されていない 100 未満の数値を選択する |

    次のスクリーンショットに示すように、 **変換を** 選択し、ソース属性 altSecurityIdentities からターゲット属性 alternativeSecurityId への直接マッピングを作成します。

    [Image: altSecurityIdentities から alternateSecurityId 属性に変換する方法のスクリーンショット。]
4. alternativeSecurityId 属性から Microsoft Entra ID の certificateUserIds 属性に変換する送信同期規則を作成します。

    | 回答内容 | 価値 |
    | --- | --- |
    | 名前 | 規則のわかりやすい名前 (例: Out to Microsoft Entra ID - certificateUserIds) |
    | 接続先システム | Microsoft Entra ドメイン |
    | 接続先システム オブジェクトの種類 | ユーザー |
    | メタバース オブジェクトの種類 | 個人 |
    | 優先順位 | 現在、すべての既定のルールの上で使用されていない高い数値 (150 など) を選択する |

    次のスクリーンショットに示すように、 **変換を** 選択し、ソース属性 alternativeSecurityId からターゲット属性 certificateUserIds への直接マッピングを作成します。

    [Image: alternateSecurityId 属性から certificateUserIds に変換する送信同期規則のスクリーンショット。]
5. 同期を実行して、certificateUserIds 属性にデータを事前設定します。
6. 成功したか確認するには、Microsoft Entra ID でユーザーの認可情報を表示します。

    [Image: 同期が成功したスクリーンショット。]

altSecurityIdentities 属性から値のサブセットをマッピングするには、手順 4 の「変換」を「式」に置き換えます。 式を使用するには、[ **変換** ] タブに進み、FlowType オプションを Expression に変更し、ターゲット属性を certificateUserIds に変更し、式を [ソース] フィールドに入力します。 次の例では、SKI および SHA1PublicKey 証明書マッピング フィールドに合わせた値のみをフィルター処理します。

[Image: 式のスクリーンショット。]

**式コード**:

```powershell
IIF(IsPresent([alternativeSecurityId]),
                Where($item,[alternativeSecurityId],BitOr(InStr($item, "X509:<SKI>"),InStr($item, "X509:<SHA1-PUKEY>"))>0),[alternativeSecurityId]
)
```

管理者は、サポートされているパターンに合わせて altSecurityIdentities から値をフィルター処理できます。 certificateUserIds に同期されるユーザー名バインディングをサポートし、これらの値を使用して認証を有効にするために、CBA 構成が更新されていることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-certificate-based-authentication-limitations"} -->
## フェデレーションなしの Microsoft Entra 証明書ベースの認証に関する制限事項 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-limitations
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra 証明書ベースの認証でサポートされているシナリオとサポートされていないシナリオについて説明します

この記事では、Microsoft Entra 証明書ベースの認証でサポートされているシナリオとサポートされていないシナリオについて説明します。

### サポートされているシナリオ

次のシナリオがサポートされています。

- すべてのプラットフォーム上の Web ブラウザー ベースのアプリケーションへのユーザー サインイン。
- Outlook、OneDrive などの Office モバイル アプリへのユーザー サインイン。
- モバイル ネイティブ ブラウザーでのユーザー サインイン。
- 証明書発行者 **サブジェクト** と **ポリシー OID**を使用した、多要素認証の詳細な認証規則のサポート。
- 次の任意の証明書フィールドを使用して、証明書とユーザー アカウントのバインドを構成します。
    - サブジェクト代替名 (SAN) PrincipalName と SAN RFC822Name
    - サブジェクト キー識別子 (SKI) と SHA1PublicKey
- 次の任意のユーザー オブジェクト属性を使用して、証明書とユーザー アカウントのバインドを構成します。
    - ユーザー プリンシパル名
    - オンプレミスユーザープリンシパル名 (onPremisesUserPrincipalName)
    - 証明書ユーザーID

### サポートされていないシナリオ

以下のシナリオはサポートされていません。

- クライアント証明書を作成するための公開キー基盤。 お客様は、独自の公開キー基盤 (PKI) を構成し、ユーザーとデバイスに証明書をプロビジョニングする必要があります。
- 証明機関のヒントはサポートされていないため、UI 内のユーザーに表示される証明書の一覧のスコープは設定されていません。
- 信頼された CA に対してサポートされている CRL 配布ポイント (CDP) は 1 つのみです。
- CDP には HTTP URL のみを指定できます。 オンライン証明書状態プロトコル (OSCP) およびライトウェイト ディレクトリ アクセス プロトコル (LDAP) の URL はサポートされていません。
- **サブジェクト + 発行者**または**発行者 + シリアル番号**の使用など、他の証明書からユーザーへのアカウント バインドの構成は、このリリースでは使用できません。
- 現時点では、CBA が有効で、パスワードを使用してサインインするオプションが表示されている場合、パスワードを無効にすることはできません。

### サポートされているオペレーティング システム

| オペレーティング システム | デバイス上の証明書/派生 PIV | スマート カード |
| --- | --- | --- |
| ウィンドウズ | ✅ | ✅ |
| macOS | ✅ | ✅ |
| iOS | ✅ | サポートされているベンダーのみ |
| Android | ✅ | サポートされているベンダーのみ |

### サポートされているブラウザー

| オペレーティング システム | デバイス上の Chrome 証明書 | Chrome スマート カード | デバイス上の Safari 証明書 | Safari スマート カード | デバイス上の Microsoft Edge 証明書 | Microsoft Edge スマート カード |
| --- | --- | --- | --- | --- | --- | --- |
| ウィンドウズ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| macOS | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| iOS | ❌ | ❌ | ✅ | サポートされているベンダーのみ | ❌ | ❌ |
| Android | ✅ | ❌ | なし | なし | ❌ | ❌ |

注

iOS および Android モバイルでは、Microsoft Edge ブラウザー ユーザーは Microsoft Edge にサインインし、アカウントの追加フローなどの Microsoft 認証ライブラリ (MSAL) を使用してプロファイルを設定できます。 プロファイルを使用して Microsoft Edge にログインすると、CBA はデバイス上の証明書とスマート カードでサポートされます。

### スマート カード プロバイダー

| プロバイダー | ウィンドウズ | macOS | iOS | Android |
| --- | --- | --- | --- | --- |
| YubiKey | ✅ | ✅ | ✅ | ✅ |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-certificate-based-authentication-migration"} -->
## フェデレーションから Microsoft Entra CBA に移行する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-migration
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: フェデレーション サーバーから Microsoft Entra ID に移行する方法についての説明

この記事では、オンプレミスの Active Directory フェデレーション サービス (AD FS) などのフェデレーション サーバーの実行から、Microsoft Entra 証明書ベースの認証 (CBA) を使用したクラウド認証に移行する方法について説明します。

### 段階的ロールアウト

テナント管理者は、パイロット テストなしでフェデレーション ドメインを Microsoft Entra CBA に完全に移行することができます。 これを行うには、Microsoft Entra ID で CBA 認証方法を有効にし、ドメイン全体をマネージド認証に変換します。 ただし、マネージドへの完全なドメイン一括移行の前に、Microsoft Entra CBA に対して少数のユーザーの認証をテストする必要がある場合は、段階的ロールアウト機能を利用できます。

 証明書ベースの認証 (CBA) の[段階的ロールアウト](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout)は、フェデレーション IdP での CBA の実行から Microsoft Entra ID へお客様が切り替えるのを支援します。選択したユーザーのグループを使って少人数のユーザーを選択して Microsoft Entra ID での CBA の使用へと移行させ (フェデレーション IdP にはリダイレクトされなくなる)、その後で Microsoft Entra ID のドメイン構成をフェデレーションからマネージドに変換します。 段階的ロールアウトは、ドメインが長期間、または多数のユーザーに対してフェデレーションを維持するようには設計されていません。

ADFS 証明書ベースの認証から Microsoft Entra CBA への移行を紹介する短いビデオをご覧ください。

注

ユーザーに対して段階的ロールアウトが有効になっている場合、ユーザーはマネージド ユーザーと見なされ、すべての認証は Microsoft Entra ID で行われます。 フェデレーション テナントの場合、段階的ロールアウトで CBA が有効になっていると、パスワード認証は PHS も有効になっている場合にのみ機能します。 それ以外の場合、パスワード認証は失敗します。

### ご利用のテナント上で証明書ベースの認証の段階的ロールアウトを有効にする

段階的ロールアウトを構成するには、こちらの手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。このとき、[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上のアクセス許可が必要です。
2. **Microsoft Entra Connect** を検索して選択します。
3. [Microsoft Entra Connect] ページの [クラウド認証の段階的ロールアウト] で、**[マネージド ユーザー サインインの段階的ロールアウトを有効にする]** を選択します。
4. **[段階的ロールアウトを有効にする]** 機能ページで、**[証明書ベースの認証]** オプションに対して [\[オン\]](https://learn.microsoft.com/ja-jp/entra/identity/authentication/certificate-based-authentication-federation-get-started) を選択します。
5. **[グループの管理]** を選択し、クラウド認証に参加させたいグループを追加します。 タイムアウトを回避するには、最初に、セキュリティ グループに含まれるメンバーが 200 人以下であることを確認してください。

注

Microsoft では、Entra 証明書ベースの認証と証明書ベースの認証方法ポリシーの段階的なロールアウトを管理するために、個別のグループを使用することをお勧めします

詳細については、「[段階的ロールアウト」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout)を参照してください。

### Microsoft Entra Connect を使用して certificateUserIds 属性を更新する

AD FS 管理者は、**同期規則エディター**を使用して、AD FS から Microsoft Entra ユーザー オブジェクトに属性の値を同期させる規則を作成できます。 詳細については、[certificateUserIds の同期規則](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-certificateuserids#update-certificate-user-ids-using-azure-ad-connect)に関するページを参照してください。

Microsoft Entra Connect には、必要なアクセス許可を付与する **[ハイブリッド ID の管理者]** という名前の特別なロールが必要です。 新しいクラウド属性に書き込みを行うアクセス許可を得るには、このロールが必要です。

注

ユーザー名バインド用のユーザー オブジェクト内の onPremisesUserPrincipalName 属性など、同期された属性をユーザーが使用している場合は、Microsoft Entra Connect サーバーへの管理アクセス権を持つすべてのユーザーが、同期された属性マッピングの変更、および同期された属性の値の変更を行うことができます。 ユーザーはクラウド管理者である必要はありません。AD FS 管理者は、Microsoft Entra Connect サーバーへの管理アクセスが制限されていること、および特権アカウントがクラウド専用アカウントであることを確認する必要があります。

### AD FS から Microsoft Entra ID への移行についてよく寄せられる質問

#### フェデレーション AD FS サーバーに対して特権アカウントを持つことができますか?

可能ですが、Microsoft では特権アカウントをクラウド専用アカウントとすることをお勧めします。 特権アクセスにクラウド専用アカウントを使用すると、侵害されたオンプレミス環境を原因とする Microsoft Entra ID での漏洩が制限されます。 詳細については、「[オンプレミスの攻撃から Microsoft 365 を保護する](https://learn.microsoft.com/ja-jp/entra/architecture/protect-m365-from-on-premises-attacks)」を参照してください。

#### 組織が AD FS と Azure CBA の両方をハイブリッド運用している場合、AD FS の侵害に対してまだ脆弱ですか?

Microsoft では、特権アカウントをクラウド専用アカウントにすることをお勧めしています。 この方法を採用することで、侵害されたオンプレミス環境を原因とする Microsoft Entra ID での露出が制限されます。 クラウド専用の特権アカウントを維持することは、この目標の基本です。

同期されたアカウントの場合:

- マネージド ドメイン (フェデレーションではなく) 内に存在する場合、フェデレーション IdP からのリスクはありません。
- フェデレーション ドメイン内に置かれているが、段階的ロールアウトによってアカウントのサブセットが Microsoft Entra CBA に移動中である場合、フェデレーション ドメインがクラウド認証に完全に切り替えられるまでは、フェデレーション Idp に関連するリスクの対象となります。

#### AD FS から Azure にピボットする機能を防ぐために、組織は AD FS などのフェデレーション サーバーを排除する必要がありますか?

フェデレーションの場合、攻撃者は、高い特権を持つ管理者アカウントのようなクラウド専用ロールを取得できないにしても、CIO など、誰かを偽装することが可能です。

ドメインが Microsoft Entra ID でフェデレーションされている場合は、フェデレーション IdP に高いレベルの信頼が置かれます。 AD FS は 1 つの例ですが、この概念は "あらゆる" フェデレーション IdP に当てはまります。 組織の多くは、証明書ベースの認証を実現するために、AD FS などのフェデレーション IdP を排他的にデプロイしています。 この場合、Microsoft Entra CBA によって AD FS の依存関係は完全に削除されます。 Microsoft Entra CBA を使用すれば、お客様はアプリケーション資産を Microsoft Entra ID に移行して、IAM インフラストラクチャを最新化し、強化されたセキュリティを利用してコストを削減できます。

セキュリティの観点からは、資格情報 (X.509 証明書、CAC、PIV など)、または使用中の PKI に変更はありません。 PKI の所有者は、証明書の発行と、失効のライフサイクルおよびポリシーを引き続き完全に制御できます。 失効チェックと認証は、フェデレーション Idp ではなく Microsoft Entra ID で行われます。 これらのチェックにより、すべてのユーザーを対象にしてフィッシングに強いパスワードレスの認証が Microsoft Entra ID に対して直接有効になります。

#### Windows でフェデレーション AD FS と Microsoft Entra クラウド認証を使用すると、認証はどのように機能しますか?

Microsoft Entra CBA では、サインインするユーザーの Microsoft Entra UPN を指定することをユーザーまたはアプリケーションに求めます。

ブラウザーの例では、ほとんどの場合、ユーザーは自分の Microsoft Entra UPN を入力します。 Microsoft Entra UPN は、領域とユーザーの検出に使用されます。 使用する証明書は、ポリシーで構成されたユーザー名バインドのいずれかを使用して、このユーザーと照合させる必要があります。

Windows サインインでは、デバイスがハイブリッドであるか Microsoft Entra に参加しているかによって照合が異なります。 ただし、どちらの場合も、ユーザー名ヒントが指定されている場合、Windows からはそのヒントが Microsoft Entra UPN として送信されます。 使用する証明書は、ポリシーで構成されたユーザー名バインドのいずれかを使用して、このユーザーと照合させる必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-certificate-based-authentication-mobile-android"} -->
## Android デバイスでの Microsoft Entra 証明書ベースの認証 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-mobile-android
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Android デバイスでの Microsoft Entra 証明書ベースの認証について説明します

Microsoft Entra 証明書ベースの認証は、デバイスにプロビジョニングされた証明書と、YubiKeys などの外部セキュリティ キーを使用してサポートされます。

### [前提条件]

- Android バージョンは Android 5.0 (Lollipop) 以降である必要があります。
- 最新の MSAL ライブラリまたは Microsoft Authenticator を使用する Microsoft ファースト パーティ アプリは、CBA を実行できます。
- 最新の MSAL ライブラリを使用するか、Microsoft Authenticator と統合されたサード パーティ製アプリケーションで CBA を実行できます。

### デバイス上の証明書を使用したCBA

お客様は、選択したモバイル デバイス管理 (MDM) を使用して、デバイスに証明書をプロビジョニングできます。 エンド ユーザーは、まずデバイスを MDM に登録し、デバイスにプロビジョニングされた証明書を取得する必要があります。 デバイスで証明書がプロビジョニングされると、ユーザーは CBA を使用して認証できます。

Android 上の Microsoft アプリで YubiKey をテストする手順:

1. Outlook を開きます。
2. [ **アカウントの追加]** を選択し、ユーザー プリンシパル名 (UPN) を入力します。
3. **[Continue]** をクリックします。
4. [ **証明書またはスマート カードの使用**] を選択します。
5. ダイアログボックス **でデバイスの証明書** を選択します\*\*\*\*
6. 証明書ピッカーが表示されます。
7. ユーザーのアカウントに関連付けられている証明書を選択します。 **[Continue]** をクリックします。
8. 認証が成功した場合、ユーザーは Outlook リソースにアクセスできます。

### ハードウェア セキュリティ キー上の証明書によるCBA（証明書ベースの認証）

証明書は、秘密キー アクセスを保護するための PIN と共に、ハードウェア セキュリティ キーなどの外部デバイスにプロビジョニングできます。 Microsoft Entra ID では、YubiKey で CBA がサポートされます。

#### ハードウェア セキュリティ キーでの証明書の利点

証明書を含むセキュリティ キー:

- ユーザーが異なるデバイスで同じ証明書を使用できるようにする、セキュリティ キーのローミングの性質があります。
- PIN でハードウェアで保護され、フィッシングに強くなります。
- 証明書の秘密キーにアクセスするための第 2 要素として PIN を使用して多要素認証を提供します。
- 別のデバイスで MFA を使用する業界の要件を満たす。
- Fast Identity Online 2 (FIDO2) キーなど、複数の資格情報を格納できる将来の証明に役立ちます。

#### YubiKey を使用した Android モバイルでの Microsoft Entra CBA

Android では、証明書を使用してスマートカードまたはセキュリティ キーをサポートできるミドルウェア アプリケーションが必要です。 Microsoft Entra CBA で YubiKeys をサポートするために、YubiKey Android SDK は、最新の Microsoft 認証ライブラリ (MSAL) を通じて利用できる Microsoft ブローカー コードに統合されています。

Microsoft Entra CBA with YubiKey on Android mobile は最新の MSAL を使用して有効になっているため、Android サポートには YubiKey Authenticator アプリは必要ありません。

Android 上の Microsoft アプリで YubiKey をテストする手順:

1. Microsoft Authenticator をインストールします。
2. YubiKey に USB-C がある場合は、Outlook を開き、YubiKey をプラグインします。
3. [ **アカウントの追加]** を選択し、ユーザー プリンシパル名 (UPN) を入力します。
4. [ **続行**] をクリックし、YubiKey にアクセスするためのアクセス許可を求められたら、[ **OK] をクリックします**。
5. [ **証明書またはスマート カードの使用**] を選択します。
6. NFC 対応の Yubikey を使用している場合は、Yubikey をデバイスの背面に保持します。
7. カスタム証明書ピッカーが表示されます。
8. ユーザーのアカウントに関連付けられている証明書を選択し、[ **続行**] をクリックします。
9. YubiKey にアクセスするための PIN を入力し、[ **ロック解除**] を選択します。
10. NFC で Yubikey を使用している場合は、Yubikey をもう一度電話の背面に保持して PIN を検証します。
11. 認証が成功したら、Outlook にアクセスできます。

注

スムーズな CBA フローを実現するために、アプリケーションが開いたらすぐに YubiKey をプラグインし、YubiKey からの同意ダイアログを受け入れてから、[ **証明書またはスマート カードを使用**する] リンクを選択します。 1 つの接続のみを使用する場合は、ユーザーに NFC ではなく USB を使用して YubiKey を接続することを検討してください。これは、ログインの開始時に 1 回だけ実行する必要があります。

### Exchange ActiveSync クライアントのサポート

Android 5.0 (Lollipop) 以降の特定の Exchange ActiveSync アプリケーションがサポートされています。 メール アプリケーションが Microsoft Entra CBA をサポートしているかどうかを確認するには、アプリケーション開発者に問い合わせてください。

### サポートされている Microsoft Entra のユース ケース

#### Microsoft モバイル アプリケーションのサポート

| アプリケーション | 支援 |
| --- | --- |
| Azure Information Protection アプリ | ✅ |
| ポータル サイト | ✅ |
| Microsoft Teams | ✅ |
| Office (モバイル) | ✅ |
| OneNote | ✅ |
| OneDrive | ✅ |
| 前途 | ✅ |
| Power BI | ✅ |
| Skype for Business | ✅ |
| Word/Excel/PowerPoint | ✅ |
| Yammer | ✅ |
| プロファイル ログインを含む Edge ブラウザー | ✅ |
| マネージド ホーム スクリーン | ✅ |

注

キオスク モード (共有デバイス モードで共通) で Android デバイスで Microsoft Entra 証明書ベースの認証を使用する場合、お客様は、認証を完了するために適切な UI が表示されるように、必要なパッケージとして list com.android.systemui を許可する必要があります。

#### ブラウザー

| オペレーティング システム | デバイス上の Chrome 証明書 | Chrome スマート カード/セキュリティ キー | デバイス上の Safari 証明書 | Safari スマート カード/セキュリティ キー | デバイス上のエッジ証明書 | Edge スマート カード/セキュリティ キー |
| --- | --- | --- | --- | --- | --- | --- |
| Android | ✅ | ❌ | なし | なし | ✅ | ❌ |

注

ブラウザーとしての Edge はサポートされていませんが、プロファイルとしての Edge (アカウント ログイン用) は、Android で CBA をサポートする MSAL アプリです。

#### オペレーティング システム

| オペレーティング システム | デバイス上の証明書/派生 PIV | スマート カード/セキュリティ キー |
| --- | --- | --- |
| Android | ✅ | サポートされているベンダーのみ |

#### セキュリティ キー プロバイダー

| プロバイダー | Android |
| --- | --- |
| YubiKey | ✅ |

#### ハードウェア セキュリティ キーの証明書のトラブルシューティング

##### ユーザーが Android デバイスと YubiKey の両方に証明書を持っている場合はどうなりますか?

- ユーザーが Android デバイスと YubiKey の両方に証明書を持っている場合、ユーザーが [ **証明書またはスマート カードの使用**] をクリックする前に YubiKey が接続されている場合、ユーザーには YubiKey の証明書が表示されます。
- ユーザーが [ **証明書またはスマート カードの使用**] をクリックする前に YubiKey が接続されていない場合は、デバイスまたは物理スマート カードの証明書を選択するように求められます。 ユーザーが **[デバイス上の証明書**] を選択すると、デバイスに証明書が表示されます。 ユーザーが **物理スマート カードで [証明書**] を選択した場合は、YubiKey を背面に接続または保持すると、YubiKey に証明書が表示されます。

##### 誤って PIN を 3 回入力した後、YubiKey がロックされます。 どのように修正すればよいですか

- PIN の試行回数が多すぎることを示すダイアログが表示されます。 このダイアログは、その後、[ **証明書またはスマート カードを使用**する] を選択しようとしたときにも表示されます。
- ユーザーは管理者に連絡して YubiKey PIN をリセットする必要があります。

##### Microsoft 認証システムをインストールしましたが、YubiKey で証明書ベースの認証を行うオプションがまだ表示されません。

Microsoft Authenticator をインストールする前に、ポータル サイトをアンインストールし、Microsoft Authenticator のインストール後にインストールします。

##### Microsoft Entra CBA は NFC 経由で YubiKey をサポートしていますか?

Microsoft Entra CBA では、USB と NFC での YubiKey の使用がサポートされています。

##### CBA が失敗すると、エラー ページの [その他のサインイン方法] リンクで CBA オプションをもう一度クリックすると失敗します。

この問題は、証明書のキャッシュが原因で発生します。 回避策として、[キャンセル] をクリックしてログイン フローを再起動すると、ユーザーは新しい証明書を選択し、正常にログインできるようになります。

##### Microsoft Entra CBA と YubiKey が失敗しています。 問題のデバッグに役立つ情報は何ですか?

1. Microsoft Authenticator アプリを開き、右上隅にある 3 つのドット アイコンをクリックし、[ **フィードバックの送信**] を選択します。
2. [ **問題が発生しましたか?**] をクリックします。
3. [ **オプションの選択] で**、[ **追加] を選択するか、アカウントにサインイン**します。
4. 追加する詳細について説明します。
5. 右上隅にある送信矢印をクリックします。 ダイアログに表示されるコードに注意してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-certificate-based-authentication-mobile-ios"} -->
## Microsoft Entraを使用したAppleデバイスでの証明書ベースの認証 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-mobile-ios
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: macOS または iOS を実行する Apple デバイスでの証明書ベースの認証のMicrosoft Entraについて説明します

このトピックでは、macOS および iOS デバイスMicrosoft Entra証明書ベースの認証 (CBA) のサポートについて説明します。

### macOS デバイスでのMicrosoft Entraによる証明書ベースの認証

macOS を実行するデバイスでは、CBA を使用して、X.509 クライアント証明書を使用してMicrosoft Entra IDに対する認証を行うことができます。 Microsoft Entra CBA は、デバイス上の証明書と外部ハードウェアで保護されたセキュリティ キーでサポートされています。 macOS では、Microsoft Entra CBA はすべてのブラウザーとMicrosoftファースト パーティ アプリケーションでサポートされます。

#### macOS でサポートされているブラウザー

| Edge | クロム | Safari | Firefox |
| --- | --- | --- | --- |
| ✅ | ✅ | ✅ | ✅ |

#### Microsoft Entra CBA を使用した macOS デバイスのサインイン

現在、Microsoft Entra CBA は、macOS マシンへのデバイス ベースのサインインではサポートされていません。 デバイスへのサインインに使用する証明書は、ブラウザーまたはデスクトップ アプリケーションからのMicrosoft Entra IDに対する認証に使用される証明書と同じにすることができますが、デバイスのサインイン自体は、Microsoft Entra IDに対してまだサポートされていません。

### iOS デバイスにおけるMicrosoft Entraの証明書ベースの認証

iOS を実行するデバイスは、証明書ベースの認証 (CBA) を使用して、接続時にデバイス上のクライアント証明書を使用してMicrosoft Entra IDに対する認証を行うことができます。

- Microsoft OutlookやMicrosoft Wordなどの Office モバイル アプリケーション
- Exchange ActiveSync (EAS) クライアント

Microsoft Entra CBA は、ネイティブ ブラウザー上のデバイス上の証明書と、iOS デバイス上Microsoftファースト パーティ アプリケーションでサポートされています。

#### 前提条件

- iOS バージョンは iOS 9 以降である必要があります。
- ファースト パーティ アプリケーションには、Microsoft Authenticatorまたはポータル サイトが必要です。

#### デバイス上の証明書と外部ストレージのサポート

デバイス上の証明書はデバイスにプロビジョニングされます。 モバイルデバイス管理 (MDM) を使用して、デバイスに証明書をプロビジョニングできます。 iOS では既定でハードウェア保護キーがサポートされていないため、お客様は証明書に外部ストレージ デバイスを使用できます。

#### サポートされているプラットフォーム

- ネイティブ ブラウザーのみがサポートされている
- 最新の MSAL ライブラリまたは Microsoft Authenticator を使用するアプリケーションは CBA を実行できます
- ユーザーがアカウントを追加し、プロファイルにログインしたときに、プロファイルを使用する Edge で CBA がサポートされる
- Microsoft のファースト パーティ アプリで最新の MSAL ライブラリを使用するか、Microsoft Authenticator を使用して CBA を実行できます。

#### ブラウザー

| Edge | クロム | Safari | Firefox |
| --- | --- | --- | --- |
| ❌ | ❌ | ✅ | ❌ |

#### Microsoft モバイル アプリケーションのサポート

| アプリケーション | サポート |
| --- | --- |
| Azure Information Protection アプリ | ✅ |
| 会社ポータル | ✅ |
| Microsoft Teams | ✅ |
| Office (モバイル) | ✅ |
| OneNote | ✅ |
| OneDrive | ✅ |
| Outlook | ✅ |
| Power BI | ✅ |
| Skype for Business | ✅ |
| Word/Excel/PowerPoint | ✅ |
| Yammer | ✅ |

#### Exchange ActiveSync クライアントのサポート

iOS 9 以降では、ネイティブの iOS メール クライアントがサポートされます。

電子メール アプリケーションが CBA Microsoft Entraサポートしているかどうかを確認するには、アプリケーション開発者に問い合わせてください。

### ハードウェア セキュリティ キーでの証明書のサポート

証明書をハードウェア セキュリティ キーなどの外部デバイスに PIN と共にプロビジョニングすると、秘密キーのアクセスを保護できます。 Microsoftのモバイル証明書ベースのソリューションとハードウェア セキュリティ キーは、シンプルで便利な FIPS (Federal Information Processing Standards) 認定フィッシング耐性 MFA メソッドです。

iOS 16/iPadOS 16.1 に関しては、Apple デバイスでは、USB-C または Lightning に接続された CCID 準拠のスマート カードに対してネイティブ ドライバーのサポートが提供されています。 つまり、iOS 16/iPadOS 16.1 の Apple デバイスでは、追加のドライバーやサード パーティのアプリを使用せずに、USB-C または Lightning に接続された CCID 準拠のデバイスがスマート カードとして見なされます。 Microsoft Entra CBA は、これらの USB-A、USB-C、または Lightning に接続された CCID 準拠のスマート カードで動作します。

#### ハードウェア セキュリティ キーでの証明書の利点

証明書を使用するセキュリティ キーの場合、次のようになります。

- 任意のデバイスで使用でき、ユーザーが所有するすべてのデバイスで証明書のプロビジョニングを必要としない
- PIN でハードウェア保護され、フィッシングに対する耐性が高くなる
- 証明書の秘密キーにアクセスするための、PIN を第 2 要素とする多要素認証が提供される
- 別のデバイスで MFA を使用する業界の要件を満たす
- 将来、Fast Identity Online 2 (FIDO2) キーを含む複数の資格情報を格納する場所の確保に役立つ

#### iOS モバイルで YubiKey を使用して、Microsoft Entra の CBA を利用する

Lightning に接続された CCID 準拠スマート カードの場合は iOS/iPadOS 上のネイティブのスマートカード/CCID ドライバーが使用できますが、YubiKey 5Ci Lightning コネクタの場合、Yubico Authenticator のような PIV (個人の ID 検証) ミドルウェアを使用しないと、これらのデバイス上では接続されたスマート カードとは見なされません。

#### ワンタイム登録の前提条件

- スマートカードの証明書がプロビジョニングされた PIV 対応 YubiKey の所有
- v14.2 以降の iPhone に [Yubico Authenticator for iOS アプリ](https://apps.apple.com/app/yubico-authenticator/id1476679808)をダウンロードします
- アプリを開き、YubiKey を挿入するか、近距離無線通信 (NFC) をタップして、手順に従って証明書を iOS キーチェーンにアップロードします

#### iOS モバイル上のMicrosoft アプリで YubiKey をテストする手順

1. 最新のMicrosoft Authenticator アプリをインストールします。
2. Outlookを開き、YubiKey をプラグインします。
3. **[アカウントの追加]** を選択し、ユーザー プリンシパル名 (UPN) を入力します。
4. **続行** を選択すると、iOS 証明書ピッカーが表示されます。
5. ユーザーのアカウントに関連付けられている YubiKey からコピーしたパブリック証明書を選択します。
6. **[YubiKey が必要です]** を選択して、YubiKey 認証アプリを開きます。
7. YubiKey にアクセスするための PIN を入力し、左上隅にある [戻る] ボタンを選択します。

ユーザーは正常にログインし、Outlookホームページにリダイレクトされます。

#### ハードウェア セキュリティ キーでの証明書のトラブルシューティング

##### ユーザーが iOS デバイスと YubiKey の両方に証明書を持っている場合はどうなりますか?

iOS 証明書ピッカーには、iOS デバイス上の証明書と、YubiKey から iOS デバイスにコピーされた証明書がすべて表示されます。 ユーザーが選択した証明書に応じて、PIN を入力する YubiKey authenticator に移動するか、直接認証されます。

##### 誤った PIN を 3 回入力した後、YubiKey がロックされます。 これを解決するにはどうすればいいですか?

- ユーザーには、PIN の試行回数が多すぎることを示すダイアログが表示されます。 このダイアログは、その後、**[証明書またはスマート カードを使用する]** を選択しようとしたときにもポップアップ表示されます。
- [YubiKey Manager](https://www.yubico.com/support/download/yubikey-manager/) で、YubiKey の PIN をリセットできます。

##### CBA が失敗した後、[その他のサインイン方法] リンクの CBA オプションも失敗します。 回避策はありますか?

この問題は、証明書のキャッシュが原因で発生します。 キャッシュをクリアするための更新に取り組んでいます。 回避策として、キャンセル 選択し、サインインを再試行して、新しい証明書を選択します。

##### YubiKeyを使用したMicrosoft Entra CBAが失敗しています。 問題のデバッグに役立つ情報はありますか?

1. アプリMicrosoft Authenticator開き、右上隅にある 3 つのドット アイコンを選択し、**Send Feedback** を選択します。
2. [問題が発生している **を選択しますか?**.
3. **[オプションの選択]** で、**[アカウントの追加またはサインイン]** を選択します。
4. 追加する詳細について説明します。
5. 右上隅にある送信矢印を選択します。 ダイアログに表示されるコードをメモします。

##### モバイル上のブラウザー ベースのアプリケーションで、ハードウェア セキュリティ キーを使用したフィッシングに強い MFA を適用するにはどうすればよいですか?

証明書ベースの認証と条件付きアクセス認証の強度機能は、認証を適用したいとお考えのお客様を強力に支援します。 プロファイルとしての Edge (アカウントの追加) は、YubiKey などのハードウェア セキュリティ キーと連携し、認証強度機能を備えた条件付きアクセス ポリシーによって CBA でフィッシングに強い認証を適用できます。

YubiKey の CBA サポートは、最新の Microsoft Authentication Library (MSAL) ライブラリと、最新の MSAL を統合する任意のサードパーティ アプリケーションで利用できます。 すべてのMicrosoftファースト パーティ アプリケーションでは、CBA と条件付きアクセス認証の強度を使用できます。

#### サポートされるオペレーティング システム

| オペレーティング システム | デバイス上の証明書/派生 PIV | スマート カード/セキュリティ キー |
| --- | --- | --- |
| iOS | ✅ | サポートされているベンダーのみ |

#### サポートされているブラウザー

| オペレーティング システム | デバイス上の Chrome 証明書 | Chrome スマート カード/セキュリティ キー | デバイス上の Safari 証明書 | Safari スマート カード/セキュリティ キー | デバイス上の Edge 証明書 | Edge スマート カード/セキュリティ キー |
| --- | --- | --- | --- | --- | --- | --- |
| iOS | ❌ | ❌ | ✅ | ✅ | ❌ | ❌ |

#### セキュリティ キーのプロバイダー

| プロバイダー | iOS |
| --- | --- |
| YubiKey | ✅ |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-certificate-based-authentication-smartcard"} -->
## Microsoft Entra 証明書ベースの認証を使用した Windows スマート カードのサインイン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-smartcard
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra 証明書ベースの認証を使用して Windows スマート カードサインインを有効にする方法について説明します

Microsoft Entra ユーザーは、Windows サインイン時に Microsoft Entra ID に対して、スマート カード上の X.509 証明書を使用して直接認証できます。 スマート カード認証を受け入れるために、Windows クライアントに特別な構成は必要ありません。

### ユーザー エクスペリエンス

Windows スマート カードのサインインを設定するには、次の手順に従います。

1. マシンを Microsoft Entra ID またはハイブリッド環境 (ハイブリッド参加) に参加させます。
2. 「Microsoft Entra CBA の構成」の説明に従って、テナントで [Microsoft Entra CBA を構成](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-certificate-based-authentication)します。
3. ユーザーがマネージド認証を使用しているか、 [段階的ロールアウト](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout)を使用していることを確認します。
4. 物理スマート カードまたは仮想スマート カードをテスト マシンに提示します。
5. スマート カード アイコンを選択し、PIN を入力して、ユーザーを認証します。

    [Image: スマート カードサインインのスクリーンショット。]

ユーザーは、サインインが成功した後、Microsoft Entra ID からプライマリ更新トークン (PRT) を取得します。 CBA の構成に応じて、PRT には多要素要求が含まれます。

### ユーザー UPN を Microsoft Entra CBA に送信する Windows の予期される動作

| サインイン | Microsoft Entra に参加 | ハイブリッド参加 |
| --- | --- | --- |
| 最初のサインイン | 証明書からのプル | AD UPN または x509Hint |
| 以降のサインイン | 証明書からのプル | キャッシュされた Microsoft Entra UPN |

#### Microsoft Entra 参加済みデバイスの UPN を送信するための Windows ルール

Windows は最初にプリンシパル名を使用し、存在しない場合は、Windows へのサインインに使用されている証明書の SubjectAlternativeName (SAN) から RFC822Name を使用します。 どちらも存在しない場合、ユーザーはユーザー名ヒントを追加で指定する必要があります。 詳細については、「[ユーザー名のヒント」を](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/smart-cards/smart-card-group-policy-and-registry-settings#allow-user-name-hint)参照してください。

#### Microsoft Entra ハイブリッド参加済みデバイスの UPN を送信するための Windows ルール

ハイブリッド参加サインインは、まず Active Directory (AD) ドメインに対して正常にサインインする必要があります。 ユーザー AD UPN が Microsoft Entra ID に送信されます。 ほとんどの場合、Active Directory UPN 値は Microsoft Entra UPN 値と同じであり、Microsoft Entra Connect と同期されます。

Active Directory でルーティング不可能な UPN 値 ( user@woodgrove.local など) を保持しているお客様もいます。このような場合、Windows によって送信される値が Microsoft Entra UPN ユーザーと一致しない場合があります。 Microsoft Entra ID が Windows によって送信された値と一致しないこれらのシナリオをサポートするために、 **onPremisesUserPrincipalName** 属性に一致する値を持つユーザーに対して後続の参照が実行されます。 サインインが成功した場合、Windows はユーザーの Microsoft Entra UPN をキャッシュし、以降のサインインで送信されます。

注

いずれの場合も、ユーザー指定のユーザー名ログイン ヒント (X509UserNameHint) が指定された場合に送信されます。 詳細については、「[ユーザー名のヒント」を](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/smart-cards/smart-card-group-policy-and-registry-settings#allow-user-name-hint)参照してください。

Von Bedeutung

ユーザーがユーザー名ログイン ヒント (X509UserNameHint) を指定した場合、指定する値は UPN 形式である **必要があります** 。

Windows フローの詳細については、「 [証明書の要件と列挙 (Windows)](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/smart-cards/smart-card-certificate-requirements-and-enumeration)」を参照してください。

### サポートされている Windows プラットフォーム

Windows スマート カードのサインインは、Windows 11 の最新のプレビュー ビルドで動作します。 この機能は、次 [のいずれかの更新プログラム](https://support.microsoft.com/topic/september-20-2022-kb5017383-os-build-22000-1042-preview-62753265-68e9-45d2-adcb-f996bf3ad393)をKB5017383適用した後、これらの以前のバージョンの Windows でも使用できます。

- [Windows 11 - kb5017383](https://support.microsoft.com/topic/september-20-2022-kb5017383-os-build-22000-1042-preview-62753265-68e9-45d2-adcb-f996bf3ad393)
- [Windows 10 - kb5017379](https://support.microsoft.com/topic/20-september-2022-kb5017379-os-build-17763-3469-preview-50a9b9e2-745d-49df-aaae-19190e10d307)
- [Windows Server 20H2- kb5017380](https://support.microsoft.com/topic/20-september-2022-kb5017380-os-builds-19042-2075-19043-2075-og-19044-2075-preview-59ab550c-105e-4481-b440-c37f07bf7897)
- [Windows Server 2022 - kb5017381](https://support.microsoft.com/topic/20-september-2022-kb5017381-os-build-20348-1070-preview-dc843fea-bccd-4550-9891-a021ae5088f0)
- [Windows Server 2019 - kb5017379](https://support.microsoft.com/topic/20-september-2022-kb5017379-os-build-17763-3469-preview-50a9b9e2-745d-49df-aaae-19190e10d307)

### サポートされているブラウザー

| Edge | クロム | Safari | Firefox |
| --- | --- | --- | --- |
| ✅ | ✅ | ✅ | ✅ |

注

Microsoft Entra CBA では、デバイス上の証明書と、Windows のセキュリティ キーなどの外部ストレージの両方がサポートされています。

### Windows Out of the Box エクスペリエンス (OOBE)

Windows OOBE では、ユーザーが外部スマート カード リーダーを使用してログインし、Microsoft Entra CBA に対して認証できるようにする必要があります。 既定では、Windows OOBE には、OOBE のセットアップ前に必要なスマート カード ドライバーまたはスマート カード ドライバーが Windows イメージに追加されている必要があります。

### 制限事項と注意事項

- Microsoft Entra CBA は、ハイブリッドまたは Microsoft Entra 参加済みの Windows デバイスでサポートされています。
- ユーザーはマネージド ドメインに属しているか、段階的ロールアウトを使用している必要があり、フェデレーション認証モデルを使用することはできません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-certificate-based-authentication-technical-deep-dive"} -->
## Microsoft Entra CBA の技術的概念 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-technical-deep-dive
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra証明書ベース認証 (CBA) のしくみと、CBA の設定と管理に必要な技術的概念について説明します。

この記事では、Microsoft Entra の証明書ベースの認証 (CBA) の仕組みを説明するための技術的な概念について説明します。 技術的背景を理解し、テナントで CBA Microsoft Entra設定および管理する方法について理解を深めましょう。

### Microsoft Entra の証明書ベース認証はどのように機能しますか？

次の図は、Microsoft Entra の CBA が構成されているテナントのアプリケーションにユーザーがサインインしようとする際に何が起こるかを示しています。

[Image: Microsoft Entra 証明書ベースの認証手順の概要を示す図。]

次の手順では、Microsoft Entra CBA プロセスの概要を示します。

1. ユーザーは、 [MyApps ポータル](https://myapps.microsoft.com/)などのアプリケーションにアクセスしようとします。
2. ユーザーがまだサインインしていない場合は、`https://login.microsoftonline.com/` の Microsoft Entra ID ユーザー サインイン ページにリダイレクトされます。
3. Microsoft Entraサインイン ページでユーザー名を入力し、**Next** を選択します。 Microsoft Entra IDは、テナント名を使用してホーム領域の検出を完了します。 ユーザー名を使用してテナント内のユーザーを検索します。

    [Image: MyApps ポータルのサインイン ページを示すスクリーンショット。]
4. Microsoft Entra IDは、CBA がテナント用に設定されているかどうかを確認します。 CBA が設定されている場合、ユーザーにはパスワード ページに **[証明書またはスマート カードを使用** する] へのリンクが表示されます。 ユーザーにサインイン リンクが表示されない場合は、テナントに対して CBA が設定されていることを確認します。

    詳細については、「[Microsoft Entra CBA を有効にする方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/certificate-based-authentication-faq#how-do-we-turn-on-microsoft-entra-cba-)」を参照。

    注

    テナントに対して CBA が設定されている場合、すべてのユーザーはパスワード サインイン ページに **[証明書またはスマート カードの使用** ] リンクを表示します。 ただし、MICROSOFT ENTRA IDを ID プロバイダーとして使用するアプリケーションに対して正常に認証できるのは、CBA のスコープ内のユーザーだけです。

    [Image: 証明書またはスマート カードを使用するオプションを示すスクリーンショット。]

    電話によるサインインやセキュリティ キーなど、他の認証方法を使用できるようにすると、ユーザーに別のサインイン ダイアログが表示されることがあります。

    [Image: FIDO2 認証も使用できる場合のサインイン ダイアログを示すスクリーンショット。]
5. ユーザーが CBA を選択すると、クライアントは証明書認証エンドポイントにリダイレクトされます。 パブリック Microsoft Entra IDの場合、証明書認証エンドポイントは `https://certauth.login.microsoftonline.com` です。 [Azure Government](https://learn.microsoft.com/ja-jp/azure/azure-government/compare-azure-government-global-azure#guidance-for-developers) の場合、証明書認証エンドポイントは `https://certauth.login.microsoftonline.us` です。

    エンドポイントはトランスポート層セキュリティ (TLS) 相互認証を実行し、TLS ハンドシェイクの一部としてクライアント証明書を要求します。 この要求のエントリはサインイン ログに表示されます。

    注

    管理者は、ユーザーのサインイン ページと、クラウド環境の `*.certauth.login.microsoftonline.com` 証明書認証エンドポイントへのアクセスを許可する必要があります。 証明書認証エンドポイントで TLS 検査をオフにして、クライアント証明書要求が TLS ハンドシェイクの一部として成功することを確認します。

    TLS 検査をオフにしても、新しい URL を持つ発行者ヒントに対しても機能することを確認します。 テナント ID を使用して URL をハードコーディングしないでください。 テナント ID は、企業間 (B2B) ユーザーに対して変更される可能性があります。 正規表現を使用して、TLS 検査をオフにしたときに、前の URL と新しい URL の両方が機能することを許可します。 たとえば、プロキシに応じて、`*.certauth.login.microsoftonline.com` または `*certauth.login.microsoftonline.com` を使用します。 Azure Governmentでは、`*.certauth.login.microsoftonline.us` または `*certauth.login.microsoftonline.us` を使用します。

    アクセスが許可されない限り、 発行者ヒントを有効にすると CBA は失敗します。
6. Microsoft Entra IDはクライアント証明書を要求します。 ユーザーがクライアント証明書を選択し、[ **OK]** を選択します。

    [Image: 証明書ピッカーを示すスクリーンショット。]
7. Microsoft Entra IDは、証明書失効リスト (CRL) を検証して、証明書が失効していないことを確認し、有効であることを確認します。 Microsoft Entra IDは、テナントで構成された [username バインディング](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-certificate-based-authentication#step-4-configure-username-binding-policy)を使用してユーザーを識別し、証明書フィールドの値をユーザー属性値にマップします。
8. Microsoft Entra 条件付きアクセス ポリシーによって、多要素認証 (MFA) と 認証バインド規則が MFA を満たし、一意のユーザーが検出された場合、Microsoft Entra IDはすぐにそのユーザーをサインインさせます。 MFA が必要で、証明書が 1 つの要素のみを満たしている場合、ユーザーが既に登録されている場合は、パスワードレス サインインと FIDO2 が 2 番目の要素として提供されます。
9. Microsoft Entra IDは、サインインが成功したことを示すプライマリ更新トークンを送信してサインイン プロセスを完了します。

ユーザーのサインインが成功した場合、ユーザーはアプリケーションにアクセスできます。

### 発行者のヒント

発行者によるヒントは、TLS ハンドシェイクの一部として *信頼された CA* インジケーターを返します。 信頼された CA リストは、テナントがMicrosoft Entra信頼ストアにアップロードする CA のサブジェクトに設定されます。 ブラウザー クライアントまたはネイティブ アプリケーション クライアントは、サーバーが返すヒントを使用して、証明書ピッカーに表示される証明書をフィルター処理できます。 クライアントには、信頼ストア内の CA によって発行された認証証明書のみ表示されます。

#### 発行者ヒントを有効にする

発行者ヒントを有効にするには、[ **発行者ヒント** ] チェック ボックスをオンにします。 [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)は、TLS 検査が設定されているプロキシが正しく更新されていることを確認した後、[**確認する**] を選択し、変更を保存する必要があります。

注

組織に TLS 検査を使用するファイアウォールまたはプロキシがある場合は、使用中の特定のプロキシに従ってカスタマイズされた、 `[*.]certauth.login.microsoftonline.com`の下の任意の名前を照合できる CA エンドポイントの TLS 検査をオフにしたことを確認します。

[Image: 発行者ヒントを有効にする方法を示すスクリーンショット。]

注

発行者ヒントを有効にすると、CA URL の形式は `t<tenantId>.certauth.login.microsoftonline.com`。

[Image: 発行者ヒントを有効にした後の証明書ピッカーを示すスクリーンショット。]

#### CA 信頼ストアの更新の伝達

発行者ヒントを有効にし、信頼ストアから CA を追加、更新、または削除すると、発行者ヒントがクライアントに反映されるまでに最大 10 分の遅延が発生する可能性があります。 発行者ヒントが利用可能になった後に伝達を開始するために、認証ポリシー管理者は証明書でサインインする必要があります。

ヒントが伝達されるまで、新しい CA によって発行された証明書を使用してユーザーを認証することはできません。 CA 信頼ストアの更新が反映されると、次のエラー メッセージが表示されます。

[Image: 更新が進行中かどうかをユーザーが確認するエラーを示すスクリーンショット。]

### 単一要素認証であるCBAを用いたMFA

Microsoft Entra CBA は、第 1 要素認証と第 2 要素認証の両方を対象とします。

サポートされている組み合わせをいくつか次に示します。

- CBA (第 1 要素) と[パスキー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-enable-authenticator-passkey) (第 2 要素)
- CBA (第 1 要素) と [パスワードなしの電話によるサインイン](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-phone#enable-passwordless-phone-sign-in-authentication-methods) (2 番目の要素)
- CBA (第 1 要素) および [FIDO2 セキュリティ キー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key-windows) (第 2 要素)
- パスワード (第 1 要素) と CBA (第 2 要素)

ユーザーは、MICROSOFT ENTRA CBA を使用してサインインする前に、MFA を取得し、パスワードなしのサインインまたは FIDO2 を登録する方法が必要です。

重要

ユーザー名が CBA メソッドの設定に表示される場合、ユーザーは MFA 対応と見なされます。 このシナリオでは、ユーザーは自分の ID を認証の一部として使用して、他の使用可能な方法を登録することはできません。 有効な証明書を持たないユーザーが CBA メソッドの設定に含まれていないことを確認します。 認証のしくみの詳細については、「[Microsoft Entra多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)」を参照してください。

### CBA を有効にして MFA 機能を取得するオプション

Microsoft Entra CBA は、テナントの構成に応じて、単一要素または多要素のいずれかになります。 CBA を有効にすると、ユーザーは MFA を完了できる可能性があります。 単一要素証明書またはパスワードを持つユーザーは、MFA を完了するために別の要素を使用する必要があります。

最初に MFA を満たさなければ、他の方法の登録は許可されません。 ユーザーに他の MFA メソッドが登録されておらず、CBA のスコープ内にある場合、ユーザーは ID 証明を使用して他の認証方法を登録し、MFA を取得することはできません。

CBA 対応ユーザーが単一要素証明書のみを持っており、MFA を完了する必要がある場合は、 *次のいずれかのオプション* を選択してユーザーを認証します。

- ユーザーはパスワードを入力し、単一要素証明書を使用できます。
- 認証ポリシー管理者は、一時的なアクセス パスを発行できます。
- 認証ポリシー管理者は、電話番号を追加し、ユーザー アカウントの音声またはテキスト メッセージ認証を許可できます。

CBA 対応ユーザーが証明書を発行されておらず、MFA を完了する必要がある場合は、次 *のいずれかの* オプションを選択してユーザーを認証します。

- 認証ポリシー管理者は、一時的なアクセス パスを発行できます。
- 認証ポリシー管理者は、電話番号を追加し、ユーザー アカウントの音声またはテキスト メッセージ認証を許可できます。

CBA 対応ユーザーが多要素証明書を使用できない場合 (スマート カードのサポートなしでモバイル デバイスを使用しているが、MFA を完了する必要がある場合など) は、 *次のいずれかのオプション* を選択してユーザーを認証します。

- 認証ポリシー管理者は、一時的なアクセス パスを発行できます。
- ユーザーは別の MFA 方法を登録できます (ユーザーがデバイスで多要素証明書を使用 *できる* 場合)。
- 認証ポリシー管理者は、電話番号を追加し、ユーザー アカウントの音声またはテキスト メッセージ認証を許可できます。

#### CBA を使用してパスワードなしの電話によるサインインを設定する

パスワードなしの電話によるサインインを機能させるには、まず、ユーザーのモバイル アプリを通じてレガシ通知をオフにします。

1. 少なくとも [Microsoft Entra 管理センター](https://entra.microsoft.com)[認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)としてサインインします。
2. [「パスワードレス電話によるサインイン認証を有効にする」で](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-phone#enable-passwordless-phone-sign-in-authentication-methods)説明されている手順を完了します。

    重要

    **[パスワードなし**] オプションを選択していることを確認します。 パスワードなしの電話によるサインインに追加するグループの場合は、 **認証モード** の値を **パスワードレス**に変更する必要があります。 [ **任意**] を選択した場合、CBA とパスワードなしのサインインは機能しません。
3. **Entra ID**&gt;**Multifactor authentication**&gt;**追加クラウドベースの多要素認証設定** を選択します。

    [Image: MFA 設定を構成する方法を示すスクリーンショット。]
4. [ **確認オプション**] で、[ **モバイル アプリによる通知** ] チェック ボックスをオフにし、[保存] を選択 **します**。

    [Image: モバイル アプリを使用して通知を削除する方法を示すスクリーンショット。]

### 単一要素証明書とパスワードレス サインインを使用した MFA 認証フロー

単一要素証明書を持ち、パスワードレス サインイン用に構成されているユーザーの例を考えてみましょう。 ユーザーは、次の手順を実行します。

1. ユーザー プリンシパル名 (UPN) を入力し、[ **次へ**] を選択します。

    [Image: ユーザー プリンシパル名を入力する方法を示すスクリーンショット。]
2. [ **証明書またはスマート カードを使用する**] を選択します。

    [Image: 証明書を使用してサインインする方法を示すスクリーンショット。]

    電話によるサインインやセキュリティ キーなど、他の認証方法を使用できるようにすると、ユーザーに別のサインイン ダイアログが表示されることがあります。

    [Image: 証明書を使用してサインインする別の方法を示すスクリーンショット。]
3. クライアント証明書ピッカーで、適切なユーザー証明書を選択し、[ **OK]** を選択します。

    [Image: 証明書を選択する方法を示すスクリーンショット。]
4. 証明書は単一要素認証の強度として構成されているため、MFA 要件を満たすには 2 番目の要素が必要です。 使用可能な 2 つ目の要因は、サインイン ダイアログに表示されます。 この場合は、パスワードなしのサインインです。 [**Microsoft Authenticator アプリで要求を承認する**を選択します。

    [Image: 第 2 要素要求の完了を示すスクリーンショット。]
5. 電話で通知を受け取ります。 [ **サインインの承認]** を選択します。 [Image: 電話承認要求を示すスクリーンショット。]
6. Microsoft Authenticatorで、ブラウザーまたはアプリに表示される番号を入力します。

    [Image: 数値の一致を示すスクリーンショット。]
7. [ **はい**] を選択すると、認証とサインインを行うことができます。

### 認証バインド ポリシー

認証バインディング ポリシーは、認証の強度を単一要素または多要素として設定するのに役立ちます。 認証ポリシー管理者は、既定の方法を単一要素から多要素に変更できます。 管理者は、 `IssuerAndSubject`、 `PolicyOID`、または証明書の `Issuer` と `PolicyOID` を使用して、カスタム ポリシー構成を設定することもできます。

#### 証明書の強度

認証ポリシー管理者は、証明書の強度が単一要素か多要素かを判断できます。 詳細については、[NIST 認証保証レベルをMicrosoft Entra認証方法](https://aka.ms/AzureADNISTAAL)にマップするドキュメントを参照してください。これは、[NIST 800-63B SP 800-63B、デジタル ID ガイドライン: 認証およびライフサイクル Mgmt](https://csrc.nist.gov/publications/detail/sp/800-63b/final) に基づいています。

#### 多要素証明書認証

ユーザーが多要素証明書を持っている場合、ユーザーは証明書を使用してのみ MFA を実行できます。 ただし、認証ポリシー管理者は、多要素と見なされるために、証明書が PIN または生体認証によって保護されていることを確認する必要があります。

#### 複数の認証ポリシー バインドルール

異なる証明書属性を使用して、複数のカスタム認証バインディング ポリシー規則を作成できます。 たとえば、発行者とポリシー OID を使用する場合、またはポリシー OID のみを使用する場合、あるいは発行者のみを使用する場合があります。

次のシーケンスは、カスタム 規則が重複する場合の認証保護レベルを決定します。

1. 発行者とポリシー OID の規則は、ポリシー OID 規則よりも優先されます。 ポリシー OID ルールは、証明書発行者ルールよりも優先されます。
2. 発行者とポリシー OID の規則が最初に評価されます。 発行者が CA1 で、ポリシー OID `1.2.3.4.5` を持つ MFA 用のカスタム規則がある場合、発行者の値とポリシー OID の両方を満たす証明書 A にのみ MFA が付与されます。
3. ポリシー OID を使用するカスタム 規則が評価されます。 ポリシー OID が `1.2.3.4.5` の証明書 A と、 `1.2.3.4.5.6`のポリシー OID を持つ証明書に基づく派生資格情報 B があり、カスタム規則が MFA で `1.2.3.4.5` 値を持つポリシー OID として定義されている場合、証明書 A のみが MFA を満たします。 資格情報 B は単一要素認証のみを満たします。 ユーザーがサインイン時に派生資格情報を使用し、MFA 用に構成されている場合、認証を成功させるために 2 番目の要素を求められます。
4. 複数のポリシー OID の間に競合がある場合 (たとえば、1 つの証明書に 2 つのポリシー OID があり、一方が単一要素認証にバインドされ、もう一方が MFA にバインドされている場合など)、証明書を単一要素認証として扱います。
5. 発行者 CA を使用するカスタム ルールが評価されます。 証明書にポリシー OID と発行者の規則が一致する場合、ポリシー OID は常に最初にチェックされます。 ポリシー規則が見つからない場合は、発行者のバインドがチェックされます。 ポリシー OID は、発行者よりも高い強力な認証バインディングの優先順位を持ちます。
6. 1 つの CA が MFA にバインドされている場合、この CA によって発行されるすべてのユーザー証明書は MFA として適格となります。 同じロジックが単一要素認証に適用されます。
7. 1 つのポリシー OID が MFA にバインドされている場合、OID の 1 つとしてこのポリシー OID を含むすべてのユーザー証明書が MFA として修飾されます。 (1 つのユーザー証明書に複数のポリシー OID を含めることができます)。
8. 1 つの証明書発行者が有効な強力な認証バインドを 1 つだけ持つことができます (つまり、証明書を単一要素認証と MFA の両方にバインドすることはできません)。

重要

現在、対処中の既知の問題では、認証ポリシー管理者が発行者とポリシー OID の両方を使用して CBA ポリシー規則を作成した場合、一部のデバイス登録シナリオが影響を受けます。

影響を受けるシナリオは次のとおりです。

- Windows Hello for Business登録
- FIDO2 セキュリティ キーの登録
- Windows を使用した電話を使ったパスワードレスのサインイン

Workplace Join、Microsoft Entra ID、Microsoft Entra ハイブリッド参加済みシナリオへのデバイス登録は影響を受けません。 発行者 *または* ポリシー OID を使用する CBA ポリシー規則は影響を受けません。

この問題を軽減するには、認証ポリシー管理者が次 *のいずれかの* オプションを完了する必要があります。

- 発行者とポリシー OID の両方を現在使用している CBA ポリシー規則を編集して、発行者またはポリシー ID の要件を削除します。
- 発行者とポリシー OID の両方を現在使用している認証ポリシー規則を削除し、発行者またはポリシー OID のみを使用する規則を作成します。

### ユーザー名バインド ポリシー

ユーザー名のバインド ポリシーは、ユーザーの証明書を検証するために役立ちます。 既定では、証明書のサブジェクト代替名 (SAN) プリンシパル名は、ユーザーオブジェクトの `userPrincipalName` 属性にマップされ、ユーザーを識別します。

#### 証明書バインドを使用してセキュリティを強化する

Microsoft Entraでは、証明書バインドを使用するための 7 つの方法がサポートされています。 一般に、マッピング型は、 `SubjectKeyIdentifier` (`SKI`) や `SHA1PublicKey`など、再利用できない識別子に基づいている場合、高いアフィニティと見なされます。 これらの識別子は、1 つの証明書のみを使用してユーザーを認証できることを保証します。

ユーザー名とメール アドレスに基づくマッピングの種類は、アフィニティが低いと見なされます。 Microsoft Entra IDは、再利用可能な識別子に基づいて低アフィニティと見なされる 3 つのマッピングを実装します。 その他は高アフィニティ バインドとみなされます。 詳細については、[`certificateUserIds`](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-certificateuserids)を参照してください。

| 証明書マッピング フィールド | の値の例`certificateUserIds` | ユーザー オブジェクト属性 | タイプ |
| --- | --- | --- | --- |
| `PrincipalName` | `X509:<PN>bob@woodgrove.com` | `userPrincipalName``onPremisesUserPrincipalName``certificateUserIds` | 低アフィニティ |
| `RFC822Name` | `X509:<RFC822>user@woodgrove.com` | `userPrincipalName``onPremisesUserPrincipalName``certificateUserIds` | 低アフィニティ |
| `IssuerAndSubject` | `X509:<I>DC=com,DC=contoso,CN=CONTOSO-DC-CA<S>DC=com,DC=contoso,OU=UserAccounts,CN=mfatest` | `certificateUserIds` | 低アフィニティ |
| `Subject` | `X509:<S>DC=com,DC=contoso,OU=UserAccounts,CN=mfatest` | `certificateUserIds` | 低アフィニティ |
| `SKI` | `X509:<SKI>aB1cD2eF3gH4iJ5kL6-mN7oP8qR=` | `certificateUserIds` | 高い親和性 |
| `SHA1PublicKey` | `X509:<SHA1-PUKEY>aB1cD2eF3gH4iJ5kL6-mN7oP8qR``SHA1PublicKey`値 (公開キーを含む証明書コンテンツ全体の SHA1 ハッシュ) は、証明書の**拇印**プロパティにあります。 | `certificateUserIds` | 高い親和性 |
| `IssuerAndSerialNumber` | `X509:<I>DC=com,DC=contoso,CN=CONTOSO-DC-CA<SR>cD2eF3gH4iJ5kL6mN7-oP8qR9sT` シリアル番号の正しい値を取得するには、次のコマンドを実行し、 `certificateUserIds`に示されている値を格納します。**構文**:`certutil –dump –v [~certificate path~] >> [~dumpFile path~]`**例**: `certutil -dump -v firstusercert.cer >> firstCertDump.txt` | `certificateUserIds` | 高い親和性 |

重要

[`CertificateBasedAuthentication` PowerShell モジュール](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-certificateuserids#how-to-find-the-correct-certificateuserids-values-for-a-user-from-the-end-user-certificate-using-powershell-module)を使用して、証明書内のユーザーの正しい`certificateUserIds`値を見つけることができます。

#### アフィニティ バインディングの定義とオーバーライド

認証ポリシー管理者は、ユーザーが低アフィニティまたは高アフィニティのユーザー名バインディング マッピングを使用して認証できるかどうかを構成できます。

テナントに **必要なアフィニティ バインディング** を設定します。これはすべてのユーザーに適用されます。 テナント全体の既定値をオーバーライドするには、発行者とポリシー OID、またはポリシー OID のみに基づいて、または発行者のみに基づいてカスタム 規則を作成します。

#### 複数のユーザー名ポリシー バインディングルール

複数のユーザー名ポリシー バインド規則を解決するために、Microsoft Entra IDは最も高い優先度 (最小数) のバインドを使用します。

1. ユーザー名または UPN を使用してユーザー オブジェクトを検索します。
2. `priority`属性で並べ替えられた CBA メソッド構成で認証ポリシー管理者によって設定されたすべてのユーザー名バインドの一覧を取得します。 現在、優先度は管理センターに表示されません。 Microsoft Graphは、各バインディングの `priority` 属性を返します。 次に、評価プロセスで優先順位が使用されます。
3. テナントに高アフィニティ バインドが構成されている場合、または証明書の値が高アフィニティ バインドを必要とするカスタム規則と一致する場合は、低アフィニティ バインドをすべて一覧から削除します。
4. 認証が成功するまで、リスト内の各バインドを評価します。
5. 構成されたバインディングの X.509 証明書フィールドが提示された証明書にある場合、Microsoft Entra IDは証明書フィールドの値をユーザー オブジェクト属性値と一致させます。
    - 一致するものが見つかった場合、ユーザー認証は成功します。
    - 一致するものが見つからない場合は、次の優先度バインドに移動します。
6. X.509 証明書フィールドが提示された証明書にない場合は、次の優先順位バインドに移動します。
7. 構成されているすべてのユーザー名バインドを検証します。そのうちの 1 つが一致し、ユーザー認証が成功するまでです。
8. 構成されているユーザー名バインディングのいずれにも一致するものが見つからない場合、ユーザー認証は失敗します。

### 複数のユーザー名バインドを使用してMicrosoft Entra構成をセキュリティで保護する

証明書をMicrosoft Entraユーザー アカウント (`userPrincipalName`、`onPremiseUserPrincipalName`、および `certificateUserIds`) にバインドするために使用できる各Microsoft Entra ユーザー オブジェクト属性には、証明書が 1 つのMicrosoft Entra ユーザー アカウントにのみ一致するようにする一意の制約があります。 ただし、Microsoft Entra CBA では、ユーザー名バインド ポリシーで複数のバインド メソッドがサポートされています。 認証ポリシー管理者は、複数のMicrosoft Entraユーザー アカウント構成で使用される 1 つの証明書に対応できます。

重要

複数のバインディングを構成する場合、Microsoft Entra の CBA 認証は、各バインディングを検証してユーザーを認証するため、最もアフィニティの低いバインディングと同じセキュリティレベルになります。 1 つの証明書が複数のMicrosoft Entra アカウントと一致するシナリオを防ぐために、認証ポリシー管理者は次のことができます。

- ユーザー名バインド ポリシーで 1 つのバインド方法を構成します。
- テナントに複数のバインド方法が構成されていて、1 つの証明書を複数のアカウントにマップすることを許可しない場合、認証ポリシー管理者は、ポリシーで構成されたすべての許可メソッドが同じMicrosoft Entra アカウントにマップされるようにする必要があります。 すべてのユーザー アカウントには、すべてのバインディングに一致する値が必要です。
- テナントに複数のバインド方法が構成されている場合、認証ポリシー管理者は、アフィニティの低いバインドが複数存在しないことを確認する必要があります。

たとえば、 `PrincipalName` の 2 つのユーザー名バインドが `UPN`にマップされ、 `SubjectKeyIdentifier` (`SKI`) が `certificateUserIds`にマップされます。 証明書を 1 つのアカウントのみに使用する場合、認証ポリシー管理者は、アカウントに証明書に存在する UPN があることを確認する必要があります。 次に、管理者は同じアカウントの`SKI`属性に`certificateUserIds`マッピングを実装します。

#### 1 つのMicrosoft Entra ユーザー アカウントを持つ複数の証明書のサポート (M:1)

一部のシナリオでは、組織は 1 つの ID に対して複数の証明書を発行します。 モバイル デバイスの派生資格情報である可能性がありますが、セカンダリ スマート カードや、YubiKey などの X.509 資格情報ホルダー対応デバイスの場合もあります。

##### クラウド専用アカウント (M:1)

クラウド専用アカウントの場合は、 `certificateUserIds` フィールドに一意の値を設定して各証明書を識別することで、使用する証明書を最大 5 つマップできます。 証明書をマップするには、管理センターで [ **承認情報** ] タブに移動します。

組織が `IssuerAndSerialNumber`などの高アフィニティ バインディングを使用している場合、 `certificateUserIds` の値は次の例のようになります。

`X509:<I>DC=com,DC=contoso,CN=CONTOSO-DC-CA<SR>cD2eF3gH4iJ5kL6mN7-oP8qR9sT``X509:<I>DC=com,DC=contoso,CN=CONTOSO-DC-CA<SR>eF3gH4iJ5kL6mN7oP8-qR9sT0uV`

この例では、最初の値は *X509Certificate1* を表します。 2 番目の値は *X509Certificate2* を表します。 ユーザーは、サインイン時にいずれかの証明書を提示できます。 特定のバインディングの種類 (この例では `certificateUserIds`) を検索するために CBA ユーザー名バインドが `IssuerAndSerialNumber` フィールドを指すように設定されている場合、ユーザーは正常にサインインします。

##### ハイブリッド同期アカウント (M:1)

同期されたアカウントの場合は、複数の証明書をマップできます。 オンプレミスの Active Directoryで、`altSecurityIdentities` フィールドに、各証明書を識別する値を設定します。 組織で、 `IssuerAndSerialNumber`のような高アフィニティ バインディング (つまり、強力な認証) を使用している場合、値は次の例のようになります。

`X509:<I>DC=com,DC=contoso,CN=CONTOSO-DC-CA<SR>cD2eF3gH4iJ5kL6mN7-oP8qR9sT``X509:<I>DC=com,DC=contoso,CN=CONTOSO-DC-CA<SR>eF3gH4iJ5kL6mN7oP8-qR9sT0uV`

この例では、最初の値は *X509Certificate1* を表します。 2 番目の値は *X509Certificate2* を表します。 その後、Microsoft Entra IDの `certificateUserIds` フィールドに値を同期する必要があります。

#### 複数のMicrosoft Entra ユーザー アカウントを持つ 1 つの証明書のサポート (1:M)

一部のシナリオでは、組織では、ユーザーが同じ証明書を使用して複数の ID に対して認証を行う必要があります。 管理者アカウント、開発者または一時的な職務アカウントの場合があります。

オンプレミスの Active Directoryでは、`altSecurityIdentities` フィールドに証明書の値が設定されます。 サインイン時にヒントを使用して、サインインを確認する目的のアカウントにActive Directoryを指示します。

Microsoft Entra CBA には異なるプロセスがあり、ヒントは含まれません。 代わりに、ホーム領域検出によって目的のアカウントが識別され、証明書の値が確認されます。 Microsoft Entra CBA では、`certificateUserIds` フィールドにも一意性が適用されます。 2 つのアカウントで同じ証明書の値を設定することはできません。

重要

同じ資格情報を使用して異なるMicrosoft Entra アカウントで認証することは、セキュリティで保護された構成ではありません。 複数のMicrosoft Entra ユーザー アカウントに対して 1 つの証明書を使用することは許可しないことをお勧めします。

##### クラウド専用アカウント (1:M)

クラウド専用アカウントの場合は、複数のユーザー名バインドを作成し、証明書を使用する各ユーザー アカウントに一意の値をマップします。 各アカウントへのアクセスは、異なるユーザー名バインドを使用して認証されます。 この認証レベルは、1 つのディレクトリまたはテナントの境界に適用されます。 認証ポリシー管理者は、アカウントごとに値が一意のままである場合に、証明書を別のディレクトリまたはテナントで使用するようにマップできます。

`certificateUserIds` フィールドに、証明書を識別する一意の値を設定します。 このフィールドを設定するには、管理センターで [ **承認情報** ] タブに移動します。

組織で、 `IssuerAndSerialNumber` や `SKI`などの高アフィニティ バインディング (つまり、強力な認証) を使用している場合、値は次の例のようになります。

ユーザー名バインド:

- `IssuerAndSerialNumber` &gt; `certificateUserIds`
- `SKI` &gt; `certificateUserIds`

ユーザー アカウントの `certificateUserIds` 値:`X509:<I>DC=com,DC=contoso,CN=CONTOSO-DC-CA<SR>aB1cD2eF3gH4iJ5kL6-mN7oP8qR``X509:<SKI>cD2eF3gH4iJ5kL6mN7-oP8qR9sT`

これで、いずれかのユーザーがサインイン時に同じ証明書を提示すると、アカウントがその証明書の一意の値と一致するため、ユーザーは正常にサインインします。 1 つのアカウントは `IssuerAndSerialNumber` を使用して認証され、もう 1 つは `SKI` バインドを使用して認証されます。

注

この方法で使用できるアカウントの数は、テナントで構成されたユーザー名バインドの数によって制限されます。 組織で高アフィニティ バインディングのみを使用する場合、サポートされるアカウントの最大数は 3 です。 組織で低アフィニティ バインディングも使用している場合、その数は 7 つのアカウント (1 つの `PrincipalName`、1 つの `RFC822Name`、1 つの `SKI`、1 つの `SHA1PublicKey`、1 つの `IssuerAndSubject`、1 つの `IssuerAndSerialNumber`、1 つの `Subject`) に増加します。

##### ハイブリッド同期アカウント (1:M)

同期されたアカウントには、別のアプローチが必要です。 認証ポリシー管理者は、証明書を使用する各ユーザー アカウントに一意の値をマップできますが、Microsoft Entra ID内の各アカウントにすべての値を設定する一般的な方法では、このアプローチが困難になります。 代わりに、Microsoft Entra Connect では、アカウントごとに値をフィルター処理して、Microsoft Entra IDのアカウントに入力された一意の値に設定する必要があります。 一意性ルールは、1 つのディレクトリまたはテナントの境界に適用されます。 認証ポリシー管理者は、アカウントごとに値が一意のままである場合に、証明書を別のディレクトリまたはテナントで使用するようにマップできます。

組織には、1 つのMicrosoft Entra テナントにユーザーを提供する複数のActive Directory フォレストがある場合もあります。 この場合、Microsoft Entra Connect は、同じ目標を持つ各Active Directory フォレストにフィルターを適用します。クラウド アカウントに特定の一意の値のみを設定します。

Active Directoryの `altSecurityIdentities` フィールドに、証明書を識別する値を設定します。 そのユーザー アカウントの種類 ( `detailed`、 `admin`、 `developer`など) の特定の証明書の値を含めます。 Active Directoryでキー属性を選択します。 この属性は、ユーザーが評価しているユーザー アカウントの種類 ( `msDS-cloudExtensionAttribute1`など) を同期に通知します。 この属性には、 `detailed`、 `admin`、 `developer`など、使用するユーザーの種類の値を設定します。 アカウントがユーザーのプライマリ アカウントの場合、値は空または NULL にすることができます。

次の例と似たようにアカウントが見えるか確認してください。

フォレスト 1: Account1 (bob@woodgrove.com):`X509:<SKI>aB1cD2eF3gH4iJ5kL6mN7oP8qR``X509:<SHA1-PUKEY>cD2eF3gH4iJ5kL6mN7oP8qR9sT``X509:<PN>bob@woodgrove.com`

フォレスト 1: Account2 (bob-admin@woodgrove.com): `X509:<SKI>aB1cD2eF3gH4iJ5kL6mN7oP8qR``X509:<SHA1-PUKEY>cD2eF3gH4iJ5kL6mN7oP8qR9sT``X509:<PN>bob@woodgrove.com`

フォレスト 2: ADAccount1 (bob-tdy@woodgrove.com):`X509:<SKI>aB1cD2eF3gH4iJ5kL6mN7oP8qR``X509:<SHA1-PUKEY>cD2eF3gH4iJ5kL6mN7oP8qR9sT``X509:<PN>bob@woodgrove.com`

その後、これらの値を Microsoft Entra ID の `certificateUserIds` フィールドに同期する必要があります。

`certificateUserIds`に同期するには:

1. Microsoft Entra Connect を構成して、`alternativeSecurityIds` フィールドをメタバースに追加します。
2. オンプレミスの Active Directory フォレストごとに、優先順位の高い新しいカスタム受信規則 (100 未満の低い数値) を構成します。 `Expression` フィールドをソースとして使用して、`altSecurityIdentities`変換を追加します。 ターゲット式では、選択して設定したキー属性が使用され、定義したユーザー型へのマッピングが使用されます。

例えば次が挙げられます。

```powershell
IIF((IsPresent([msDS-cloudExtensionAttribute1]) && IsPresent([altSecurityIdentities])), 
    IIF((InStr(LCase([msDS-cloudExtensionAttribute1]),LCase("detailee"))>0), 
    Where($item,[altSecurityIdentities],(InStr($item, "X509:<SHA1-PUKEY>")>0)), 
        IIF((InStr(LCase([msDS-cloudExtensionAttribute1]),LCase("developer"))>0), 
        Where($item,[altSecurityIdentities],(InStr($item, "X509:<SKI>")>0)), NULL) ), 
    IIF(IsPresent([altSecurityIdentities]), 
    Where($item,[altSecurityIdentities],(BitAnd(InStr($item, "X509:<I>"),InStrRev($item, "<SR>"))>0)), NULL) 
)
```

この例では、 `altSecurityIdentities` とキー属性 `msDS-cloudExtensionAttribute1` が最初にチェックされ、値が設定されているかどうかを確認します。 設定されていない場合は、`altSecurityIdentities` に値が設定されているかどうかのチェックが行なわれます。 空の場合は、NULL に設定します。 それ以外の場合、アカウントは既定のシナリオです。

また、この例では、 `IssuerAndSerialNumber` マッピングでのみフィルター処理します。 キー属性が設定されている場合、値がチェックされ、定義されているユーザーの種類の 1 つと等しいかどうかを確認します。 その例では、もしその値が`detailed`の場合、`SHA1PublicKey`からの`altSecurityIdentities`値をフィルターします。 値が`developer`の場合は、`SubjectKeyIssuer`から`altSecurityIdentities`値をフィルター処理します。

特定の種類の複数の証明書値が発生する可能性があります。 たとえば、複数の `PrincipalName` 値、複数の `SKI` 、または `SHA1-PUKEY` 値が表示される場合があります。 フィルターはすべての値を取得し、最初に見つけた値だけでなく、Microsoft Entra IDで同期します。

コントロール属性が空の場合に空の値をプッシュする方法を示す 2 番目の例を次に示します。

```powershell
IIF((IsPresent([msDS-cloudExtensionAttribute1]) && IsPresent([altSecurityIdentities])), 
    IIF((InStr(LCase([msDS-cloudExtensionAttribute1]),LCase("detailee"))>0), 
    Where($item,[altSecurityIdentities],(InStr($item, "X509:<SHA1-PUKEY>")>0)), 
        IIF((InStr(LCase([msDS-cloudExtensionAttribute1]),LCase("developer")>0), 
        Where($item,[altSecurityIdentities],(InStr($item, "X509:<SKI>")>0)), NULL) ), 
    IIF(IsPresent([altSecurityIdentities]), 
    AuthoritativeNull, NULL) 
) 
```

`altSecurityIdentities`の値がコントロール属性の検索値と一致しない場合は、`AuthoritativeNull`値が渡されます。 この値により、 `alternativeSecurityId` を設定する前または後続の規則は無視されます。 結果は、Microsoft Entra IDでは空です。

空の値を同期するには:

1. 優先順位を低く (数値は高く、160 より大きく、かつ一覧の下位になるように) 設定した、新しいカスタムのアウトバウンド規則を構成します。
2. `alternativeSecurityIds` フィールドをソースとして、`certificateUserIds` フィールドをターゲットとして直接変換を追加します。
3. 同期サイクルを実行して、Microsoft Entra IDでのデータ入力を完了します。

各テナントの CBA が、証明書からマップしたフィールドの種類の `certificateUserIds` フィールドを指すユーザー名バインドで構成されていることを確認します。 これで、これらのユーザーは、サインイン時に証明書を提示できます。 証明書の一意の値が `certificateUserIds` フィールドに対して検証されると、ユーザーは正常にサインインします。

### 証明機関 (CA) の範囲設定

Microsoft Entraの CA スコープを使用すると、テナント管理者は特定の CA の使用を定義されたユーザー グループに制限できます。 この機能は、特定の CA によって発行された証明書を使用して、承認されたユーザーのみが認証できるようにすることで、CBA のセキュリティと管理性を強化します。

CA スコープは、複数の CA が異なるユーザー集団間で使用されるマルチ PKI または B2B シナリオで役立ちます。 意図しないアクセスを防ぎ、組織のポリシーへの準拠をサポートします。

#### 主な利点

- 証明書の使用を特定のユーザー グループに制限する
- 複数の CA を介して複雑な PKI 環境をサポートする
- 証明書の誤用や侵害に対する強化された保護を提供します
- サインイン ログと監視ツールを使用して CA の使用状況を可視化します

管理者は、CA スコープを使用して、(SKI によって識別される) CA を特定のMicrosoft Entra グループに関連付ける規則を定義できます。 ユーザーが証明書を使用して認証を試みると、証明書の発行元 CA のスコープがユーザーを含むグループに設定されているかどうかをシステムがチェックします。 Microsoft Entraは CA チェーンを上に進めます。 すべてのスコープ ルール内のいずれかのグループにユーザーが見つかるまで、すべてのスコープ ルールが適用されます。 ユーザーがスコープ 付きグループに属していない場合、証明書が有効な場合でも認証は失敗します。

#### CA スコープ機能を設定する

1. 少なくとも [Microsoft Entra 管理センター](https://entra.microsoft.com)[認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)としてサインインします。
2. **Entra ID**&gt;**認証方法**&gt;**証明書ベースの認証**に移動します。
3. [ **構成]** で、 **証明書発行者のスコープ ポリシーに移動します**。

    [Image: CA スコープ ポリシーを示すスクリーンショット。]
4. [ **ルールの追加] を選択します**。

    [Image: CA スコープ規則を追加する方法を示すスクリーンショット。]
5. **[PKI による CA のフィルター処理] を**選択します。

    **クラシック CA には、クラシック** CA ストアのすべての CA が表示されます。 特定の PKI を選択すると、選択した PKI のすべての CA が表示されます。
6. PKI を選択します。

    [Image: CA スコープ PKI フィルターを示すスクリーンショット。]
7. **証明書発行者**の一覧には、選択した PKI のすべての CA が表示されます。 スコープ ルールを作成する CA を選択します。

    [Image: CA スコープで CA を選択する方法を示すスクリーンショット。]
8. [ **グループの追加] を選択します**。

    [Image: CA スコープの [グループの追加] オプションを示すスクリーンショット。]
9. グループを選択します。

    [Image: CA スコープの [グループの選択] オプションを示すスクリーンショット。]
10. [ **追加]** を選択してルールを保存します。

    [Image: CA スコープの [規則の保存] オプションを示すスクリーンショット。]
11. [ **確認する** ] チェック ボックスをオンにし、[保存] を選択 **します**。

    [Image: CA スコープの [CBA 構成の保存] オプションを示すスクリーンショット。]
12. CA スコープ ポリシーを編集または削除するには、「...」を選択します。 ルールを編集するには、[ **編集]** を選択します。 ルールを削除するには、[削除] を選択 **します**。

    [Image: CA スコープで編集または削除する方法を示すスクリーンショット。]

#### 既知の制限事項

- CA ごとに割り当てることができるグループは 1 つだけです。
- 最大 30 個のスコープ規則がサポートされています。
- スコープは中間 CA レベルで適用されます。
- 有効なスコープ規則が存在しない場合は、不適切な構成によってユーザーロックアウトが発生する可能性があります。

#### サインイン ログ エントリ

- サインイン ログに成功が表示されます。 **[追加の詳細]** タブには、スコープ ポリシー規則に基づく CA の SKI が表示されます。

    [Image: CA スコープ ルールのサインイン ログの成功を示すスクリーンショット。]
- CA スコープ規則が原因で CBA が失敗した場合、サインイン ログの [ **基本情報** ] タブにエラー コード **500189**が表示されます。

    [Image: CA のスコープサインイン ログ エラーを示すスクリーンショット。]

    エンド ユーザーには、次のエラー メッセージが表示されます。

    [Image: CA スコープ ユーザー エラーを示すスクリーンショット。]

### CBA と条件付きアクセスの認証強度ポリシーの連携方法

組み込みの Microsoft Entra *Phishing 耐性 MFA* 認証強度を使用して、CBA を使用してリソースにアクセスすることを指定する条件付きアクセス認証ポリシーを作成できます。 このポリシーでは、CBA、FIDO2 セキュリティ キー、Windows Hello for Businessなど、フィッシングに強い認証方法のみを許可します。

さらに、カスタム認証強度を作成して、機密性の高いリソースに CBA のみアクセス可能にすることもできます。 CBA は、単一要素認証、MFA、またはその両方として許可できます。 詳細については、「 [条件付きアクセス認証の強度](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strengths)」を参照してください。

#### 高度なオプションで強化された CBA

CBA メソッド ポリシーでは、認証ポリシー管理者は、CBA メソッドの 認証バインド ポリシー を使用して、証明書の強度を判断できます。 ユーザーが特定の機密性の高いリソースにアクセスするために CBA を実行するときに、発行者とポリシー OID に基づいて特定の証明書を使用するように要求できるようになりました。 カスタム認証強度を作成する場合は、[ **詳細オプション]** に移動します。 この機能により、リソースにアクセスできる証明書とユーザーを決定するためのより正確な構成が提供されます。 詳細については、「 [条件付きアクセス認証の強度の詳細オプション](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strength-advanced-options)」を参照してください。

### サインイン ログ

サインイン ログには、サインインに関する情報と、組織内でのリソースの使用方法が示されます。 詳細については、「Microsoft Entra ID の Sign-in ログ」を参照してください。

次に、2 つのシナリオを検討します。 1 つのシナリオでは、証明書は単一要素認証を満たします。 2 番目のシナリオでは、証明書が MFA を満たします。

テスト シナリオでは、MFA を必要とする条件付きアクセス ポリシーを持つユーザーを選択します。

**サブジェクトの別名**と**プリンシパル名**を `userPrincipalName` ユーザー オブジェクトにマッピングして、ユーザー バインド ポリシーを構成します。

ユーザー証明書は、次のスクリーンショットに示す例のように構成する必要があります。

[Image: ユーザー証明書を示すスクリーンショット。]

#### サインイン ログにおける動的変数を利用したサインインエラーのトラブルシューティング

サインイン ログは通常、サインインの問題をデバッグするために必要なすべての情報を提供しますが、特定の値が必要な場合があります。 サインイン ログでは動的変数がサポートされていないため、場合によっては、サインイン ログにデバッグに必要な情報がありません。

たとえば、サインイン ログのエラーの理由に `"The Certificate Revocation List (CRL) failed signature validation. Expected Subject Key Identifier <expectedSKI> doesn't match CRL Authority Key <crlAK>. Request your tenant administrator to check the CRL configuration."` このシナリオでは、`<expectedSKI>` と `<crlAKI>` に正しい値が設定されていないと表示される場合があります。

ユーザーが CBA でサインインできない場合は、エラー ページの **[詳細** ] リンクからログの詳細をコピーできます。 詳細については、「 [CBA エラー ページについて」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-technical-deep-dive#cba-error-page)参照してください。

#### 単一要素認証のテスト

最初のテスト シナリオでは、 `IssuerAndSubject` 規則が単一要素認証を満たす認証ポリシーを構成します。

[Image: 認証ポリシーの構成と必要な単一要素認証を示すスクリーンショット。]

1. CBA を使用して、[Microsoft Entra 管理センター](https://entra.microsoft.com) にテスト ユーザーとしてサインインします。 認証ポリシーは、 `IssuerAndSubject` 規則が単一要素認証を満たす場所に設定されます。
2. **サインイン ログ**を検索して選択します。

    次の図は、サインイン ログで確認できるエントリの一部を示しています。

    最初のエントリは、ユーザーに X.509 証明書を要求します。 **Interrupted** 状態はMicrosoft Entra IDテナントに対して CBA が設定されていることを検証したことを意味します。 認証のために証明書が要求されます。

    [Image: サインイン ログの単一要素認証エントリを示すスクリーンショット。]

    **アクティビティの詳細** は、要求が、ユーザーが証明書を選択する想定されるサインイン フローの一部であることを示しています。

    [Image: サインイン ログのアクティビティの詳細を示すスクリーンショット。]

    **その他の詳細** には、証明書の情報が表示されます。

    [Image: サインイン ログの多要素の追加の詳細を示すスクリーンショット。]

    他のエントリは、認証が完了し、プライマリ更新トークンがブラウザーに送り返され、ユーザーにリソースへのアクセス権が付与されていることを示しています。

    [Image: サインイン ログの更新トークン エントリを示すスクリーンショット。]

#### MFA のテスト

次のテスト シナリオでは、 `policyOID` ルールが MFA を満たす認証ポリシーを構成します。

[Image: MFA が必要であることを示す認証ポリシーの構成を示すスクリーンショット。]

1. CBA を使用して [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。 ポリシーは MFA を満たすように設定されているため、ユーザーのサインインは 2 つ目の要素なしで成功します。
2. **[サインイン**] を検索して選択します。

    サインイン ログには、中断状態の **エントリ** を含むいくつかのエントリが表示されます。

    [Image: サインイン ログ内のいくつかのエントリを示すスクリーンショット。]

    **アクティビティの詳細** は、要求が、ユーザーが証明書を選択する想定されるサインイン フローの一部であることを示しています。

    [Image: サインイン ログの第 2 要素サインインの詳細を示すスクリーンショット。]

    **[中断]** 状態のエントリの詳細な診断情報は、**[追加の詳細]** タブに表示されます。

    [Image: サインイン ログで中断された試行の詳細を示すスクリーンショット。]

    次の表に、各フィールドの説明を示します。

    | フィールド | 説明 |
    | --- | --- |
    | **ユーザー証明書のサブジェクト名** | 証明書のサブジェクト名フィールドを指します。 |
    | **ユーザー証明書のバインド** | 証明書: `PrincipalName`; ユーザー属性: `userPrincipalName`;ランク: 1このフィールドは、どの SAN `PrincipalName` 証明書フィールドが `userPrincipalName` ユーザー属性にマップされ、優先順位 1 だったかを示します。 |
    | **ユーザー証明書の認証レベル** | `multiFactorAuthentication` |
    | **ユーザー証明書の認証レベルの種類** | `PolicyId`このフィールドには、認証の強度を判断するためにポリシー OID が使用されたことが示されます。 |
    | **ユーザー証明書の認証レベル識別子** | `1.2.3.4`これは、証明書の識別子ポリシー OID の値を示しています。 |

### CBA エラー ページ

CBA は複数の理由で失敗する可能性があります。 たとえば、無効な証明書、ユーザーが間違った証明書を選択した、有効期限が切れた証明書、CRL の問題が発生したなどです。 証明書の検証が失敗すると、次のエラー メッセージが表示されます。

[Image: 証明書の検証エラーを示すスクリーンショット。]

CBA がブラウザーで失敗した場合、証明書ピッカーを取り消したために失敗した場合でも、ブラウザー セッションを閉じます。 新しいセッションを開いて CBA をもう一度試します。 ブラウザーが証明書をキャッシュするため、新しいセッションが必要です。 CBA が再試行されると、ブラウザーは TLS チャレンジ中にキャッシュされた証明書を送信します。これにより、サインインエラーと検証エラーが発生します。

1. サインイン ログから認証ポリシー管理者に送信するログ情報を取得するには、[ **詳細**] を選択します。

    [Image: エラーの詳細を示すスクリーンショット。]
2. サインイン **するその他の方法を** 選択し、他の使用可能な認証方法を試してサインインします。

    [Image: 新しいサインイン試行を示すスクリーンショット。]

### Microsoft Edgeで証明書の選択をリセットする

Microsoft Edge ブラウザーは、ブラウザーを再起動せずに証明書の選択を<>に設定する機能を追加しました。

ユーザーは次の手順を実行します。

1. CBA が失敗すると、エラー ページが表示されます。

    [Image: 証明書の検証エラーを示すスクリーンショット。]
2. アドレス URL の左側にあるロック アイコンを選択し、[ **証明書の選択**] を選択します。

    [Image: Microsoft Edge ブラウザー証明書の選択肢を示すスクリーンショット。]
3. [ **証明書の選択項目のリセット] を選択します**。

    [Image: Microsoft Edge ブラウザー証明書の選択をリセットするスクリーンショットを示す]
4. **「リセットの選択肢をリセット」**を選択します。

    [Image: Microsoft Edgeブラウザー証明書の選択のリセットの同意を示すスクリーンショット。]
5. [ **その他のサインイン方法] を**選択します。

    [Image: 証明書の検証エラーを示すスクリーンショット。]
6. [ **証明書またはスマート カードを使用する** ] を選択し、CBA 認証を続行します。

### MostRecentlyUsed メソッドにおける CBA

ユーザーが CBA を使用して正常に認証されると、ユーザーの `MostRecentlyUsed` (MRU) 認証方法が CBA に設定されます。 ユーザーが次回 UPN を入力して **[次へ**] を選択すると、CBA メソッドが表示され、[ **証明書またはスマート カードを使用**する] を選択する必要はありません。

MRU メソッドをリセットするには、証明書ピッカーをキャンセルし、[ **その他のサインイン方法**] を選択します。 別の使用可能な方法を選択し、認証を完了します。

MRU 認証方法はユーザー レベルで設定されます。 ユーザーが別の認証方法を使用して別のデバイスに正常にサインインした場合、ユーザーの MRU は現在サインインしている方法にリセットされます。

### 外部 ID のサポート

外部 ID B2B ゲスト ユーザーは、ホーム テナントで CBA を使用できます。 リソース テナントのクロステナント設定がホーム テナントから MFA を信頼するように設定されている場合は、ホーム テナント上のユーザーの CBA が優先されます。 詳細については、「 [B2B コラボレーションのテナント間アクセスの構成](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration)」を参照してください。 現在、リソース テナントの CBA はサポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-fido2-compatibility"} -->
## Microsoft Entra IDを使用したパスキー (FIDO2) 認証マトリックス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-fido2-compatibility
- Service: entra-id / authentication
- Article date: 2026-04-16
- Summary: Microsoft Entra IDを使用した FIDO2 パスワードレス認証に対する Web ブラウザーとネイティブ アプリのサポート。

この記事では、Microsoft Entra IDでのパスキー (FIDO2) 認証サポートの包括的な概要について説明します。 Web ブラウザー、ネイティブ アプリ、オペレーティング システム間の互換性について概説し、パスワードレス多要素認証を有効にします。 また、プラットフォーム固有の考慮事項、既知の問題、およびサード パーティのアプリと ID プロバイダー (IdP) のサポートに関するガイダンスも確認できます。 この情報を使用して、環境内のパスキーとのシームレスな統合と最適なユーザー エクスペリエンスを確保します。

Windows デバイスで FIDO2 セキュリティ キーを使用してサインインする方法の詳細については、「[WINDOWS 10 への FIDO2 セキュリティ キーのサインインを有効にする」および「Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key-windows) を使用した 11 台のデバイスへのサインイン」を参照してください。

注

Microsoft Entra IDでは、一般公開 (GA) 認証方法として同期されたパスキーとデバイス バインド パスキーの両方がサポートされます。

## [ウェブブラウザ](#tab/web)
次のセクションでは、Microsoft Entra IDを使用した Web ブラウザーでのパスキー (FIDO2) 認証のサポートについて説明します。

| オペレーティングシステム (OS) | クロム | Edge | Firefox | Safari |
| --- | --- | --- | --- | --- |
| **Windows** | ✅ | ✅ | ✅ | なし |
| **macOS** | ✅ | ✅ | ✅ | ✅ |
| **ChromeOS** | ✅ | なし | なし | なし |
| **リナックス** | ✅ | ✅ | ✅ | なし |
| **iOS** | ✅ | ✅ | ✅ | ✅ |
| **アンドロイド** | ✅ | ✅ | ❌ | なし |

### 各プラットフォームに関する考慮事項

#### Windows

- セキュリティ キーを使用したサインインには、次のいずれかの項目が必要です。
    - Windows 10 バージョン 1903 以降
    - Chromium ベースのMicrosoft Edge
    - Chrome 76 以降
    - Firefox 66 以降

#### macOS

- Microsoft Entra IDでは多要素認証にユーザー検証が必要であるため、passkey を使用したサインインには macOS Catalina 11.1 以降 (Safari 14 以降) が必要です。
- macOS では、Apple が近距離通信 (NFC) と Bluetooth Low Energy (BLE) セキュリティキーをサポートしていません。
- これらの macOS ブラウザーでは、生体認証または PIN の設定を求めないため、新しいセキュリティ キーの登録は機能しません。

#### ChromeOS

- Google による ChromeOS では、NFC と BLE のセキュリティ キーはサポートされていません。
- ChromeOS または Chrome ブラウザーでは、セキュリティ キーの登録はサポートされていません。

#### Linux

- Microsoft Authenticatorでのパスキーによるサインインは、Linux 上の Firefox ではサポートされていません。

#### iOS

- passkey を使用したサインインには iOS 14.3 以降が必要です。Microsoft Entra IDには多要素認証に対するユーザー検証が必要であるためです。
- Apple では、iOS では BLE セキュリティ キーはサポートされていません。
- FIPS 140-3 認定セキュリティ キーを使用した NFC は、Apple の iOS ではサポートされていません。
- 新しいセキュリティ キーの登録は、生体認証または PIN の設定を求めないため、iOS ブラウザーでは機能しません。

#### Android

- Microsoft Entra IDでは多要素認証にユーザー検証が必要であるため、パスキーを使用したサインインには Google Play Services 21 以降が必要です。
- Google では、Android では BLE セキュリティ キーはサポートされていません。

## [ネイティブ アプリ](#tab/native)
次のセクションでは、次のMicrosoft Entra IDでのパスキー (FIDO2) 認証のサポートについて説明します。

- Microsoft認証ブローカーでのアプリのサポート
- Microsoft認証ブローカーを使用しないアプリのサポート
- 認証ブローカーを使用しないサード パーティ製アプリのサポート
- サード パーティ ID プロバイダー (IdP) のサポート

#### 認証ブローカーを使用したアプリのサポートのMicrosoft

Microsoft アプリでは、オペレーティング システム用に認証ブローカーがインストールされているすべてのユーザーに対して、パスキー認証のネイティブ サポートが提供されます。 Passkey 認証は、認証ブローカーを使用するサード パーティ製アプリでもサポートされています。

ユーザーが認証ブローカーをインストールした場合は、Outlookなどのアプリにアクセスするときに、パスキーを使用してサインインすることを選択できます。 パスキーを使用してサインインするようにリダイレクトされ、認証が成功した後、サインインユーザーとしてOutlookにリダイレクトされます。

次の表に、オペレーティング システムごとにサポートされている認証ブローカーを示します。

| オペレーティングシステム (OS) | 認証ブローカー |
| --- | --- |
| **iOS** | Microsoft Authenticator |
| **macOS** | Microsoft Intune ポータル サイト |
| **アンドロイド** | Authenticator、ポータル サイト、または Windows アプリへのリンク |

#### 認証ブローカーを使用しないMicrosoft アプリのサポート

次の表Microsoft、認証ブローカーを使用しないパスキー (FIDO2) に対するアプリのサポートを示しています。 アプリを最新バージョンに更新して、パスキーで動作するようにします。

| アプリ | macOS | iOS | Android |
| --- | --- | --- | --- |
| [リモート デスクトップ](https://learn.microsoft.com/ja-jp/azure/virtual-desktop/compare-remote-desktop-clients) | ✅ | ✅ | ✅ |
| [Windows アプリ](https://learn.microsoft.com/ja-jp/windows-app/compare-platforms-features) | ✅ | ✅ | ✅ |
| Microsoft Copilot (Office) | なし | ✅ | ✅ |
| Word | ✅ | ✅ | ✅ |
| PowerPoint | ✅ | ✅ | ✅ |
| Excel | ✅ | ✅ | ✅ |
| OneNote | ✅ | ✅ | ✅ |
| ループ | なし | ✅ | ✅ |
| OneDrive | ✅ | ✅ | ❌ |
| Outlook | ✅ | ✅ | ❌ |
| チーム | ✅ | ✅ | ❌ |
| Edge | ✅ | ✅ | ✅ |

#### 認証ブローカーを使用しないサード パーティ製アプリのサポート

ユーザーがまだ認証ブローカーをインストールしていない場合でも、MSAL 対応アプリにアクセスするときにパスキーを使用してサインインできます。 MSAL 対応アプリの要件の詳細については、「開発するアプリ [で FIDO2 キーを使用したパスワードレス認証をサポート](https://learn.microsoft.com/ja-jp/entra/identity-platform/support-fido2-authentication)する」を参照してください。

#### サード パーティ IdP のサポート

注

現時点では、サード パーティの IdP を使用したパスキー認証は、認証ブローカーを使用するサード パーティ製アプリや、Android、iOS、または macOS 上のMicrosoft アプリではサポートされていません。

Microsoft Entra IDでは、iOS/macOS 上のサードパーティ IdP によるパスキー認証はサポートされていません。 回避策として、サードパーティの IdP は、Mobile デバイス管理 (MDM) によって管理されている場合、iOS/macOS デバイスに独自のシングル サインオン (SSO) 拡張機能を実装できます。

MDM で管理されるデバイス上の Apple の拡張可能な SSO フレームワークを使用すると、ID プロバイダーは URL に送信されたネットワーク要求をインターセプトできます。 ID プロバイダーの SSO 拡張機能がネットワーク要求をインターセプトすると、カスタム認証ハンドシェイクを実装できます。 これにより、Microsoft アプリケーションで変更を加えることなく、パスキー認証にシステム ブラウザーまたはネイティブ Apple API を使用できます。

詳細については、次の Apple ドキュメントを参照してください。

- [ExtensibleSingleSignOn デバイス管理プロファイル](https://developer.apple.com/documentation/devicemanagement/extensiblesinglesignon)
- [エンタープライズ シングル サインオン (SSO) API コレクション](https://developer.apple.com/documentation/authenticationservices/enterprise-single-sign-on-sso?language=objc)

### 各プラットフォームに関する考慮事項

#### Windows

- ネイティブ アプリへの FIDO2 セキュリティ キーを使用したサインインには、バージョン 1903 以降Windows 10必要があります。
- ネイティブ アプリにMicrosoft Authenticatorパスキーを使用してサインインするには、バージョン 22H2 以降Windows 11必要です。
- [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview)では、パスキー (FIDO2) がサポートされます。 Edge の代わりにInternet Explorerを使用する一部の PowerShell モジュールでは、FIDO2 認証を実行できません。 たとえば、SharePoint Online または Teams 用の PowerShell モジュールや、管理者の資格情報を必要とする PowerShell スクリプトでは、FIDO2 の入力を求められません。
    - 回避策として、ほとんどのベンダーは FIDO2 セキュリティ キーに証明書を配置できます。 証明書ベースの認証 (CBA) は、すべてのブラウザーで機能します。 これらの管理者アカウントに対して CBA を有効にできる場合は、一時的に FIDO2 ではなく CBA を要求できます。

##### iOS

- [Microsoft Enterprise Single Sign On (SSO) プラグイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin) を使用せずにネイティブ アプリでパスキーを使用してサインインするには、iOS 16.0 以降が必要です。
- SSO プラグインを使用してネイティブ アプリでパスキーを使用してサインインするには、iOS 17.1 以降が必要です。

##### macOS

- macOS では、認証ブローカーとしてポータル サイトを有効にするには、[Microsoft Enterprise シングル サインオン (SSO) プラグイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin)が必要です。 macOS を実行するデバイスは、モバイル デバイス管理への登録を含む SSO プラグインの要件を満たしている必要があります。
- SSO プラグインを使用してネイティブ アプリでパスキーを使用してサインインするには、macOS 14.0 以降が必要です。

##### Android

- ネイティブ アプリへの FIDO2 セキュリティ キーを使用したサインインには、Android 13 以降が必要です。
- ネイティブ アプリにMicrosoft Authenticatorパスキーを使用してサインインするには、Android 14 以降が必要です。
- YubiOTP が有効になっている Yubico 製の FIDO2 セキュリティ キーを使用したサインインは、古い Android デバイスでは機能しない可能性があります。 回避策として、ユーザーは YubiOTP を無効にして、もう一度サインインを試みることができます。 詳細については、「 [Android OEM デバイス FIDO の既知の問題](https://support.yubico.com/s/article/Android-OEM-devices-FIDO-known-issues)」を参照してください。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-fido2-hardware-vendor"} -->
## FIDO2 セキュリティ キー ベンダー向けMicrosoft Entra IDの認証 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-fido2-hardware-vendor
- Service: entra-id / authentication
- Article date: 2026-09-17
- Summary: Microsoft Entra IDを使用して構成証明用に FIDO2 ハードウェアを準備するための要件について説明します。

パスキー (FIDO2) を使用すると、フィッシングに対する耐性のある認証が有効になります。 脆弱な資格情報を、サービス間で再利用、再生、共有できない強力なフィッシング耐性の公開/秘密キー資格情報に置き換えることができます。 これらは、デバイスに安全に保存することも、暗号化されたクラウド サービスを介して信頼されたデバイス間で同期することもできます。

Microsoft Entra ID認証方法ポリシーでは、認証ポリシー管理者は FIDO2 セキュリティ キーの構成証明を適用できます。 **[構成証明を適用する]** が選択されている場合、Microsoft は、テナントに登録されているパスキー (FIDO2) から追加のメタデータを要求します。 ベンダーとして、構成証明の要件が満たされている場合、構成証明が適用されているときにパスキー (FIDO2) を使用できます。

注

Microsoft Entra IDでは、デバイス バインドパスキーと同期パスキー (FIDO2) がサポートされます。 パスキー (FIDO2) を有効にする方法の詳細については、「 [組織のパスキー (FIDO2) を有効にする」](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2)を参照してください。

### 証明の要件

Microsoftは、[FIDO Alliance Metadata Service (MDS)](https://fidoalliance.org/metadata/) に依存して、Windows、Microsoft Edge ブラウザー、およびオンライン Microsoft アカウントとのパスキー認証子の互換性を判断します。 ベンダーは、FIDO MDS にデータを報告します。

FIDO2 標準 (WebAuthn および CTAP) では、プロバイダーが有効な認証ステートメントを返す必要があります。

具体的な要件は、管理者が **Passkeys (FIDO2)** ポリシーで構成証明要件を構成する方法によって異なります。

| Attestation | 説明 |
| --- | --- |
| 施行 | ベンダーは、Microsoftがキーのメタデータを検証できるように、有効な *packed* 構成証明ステートメントと、FIDO Alliance MDS から抽出された構成証明ルートにチェーンバックする完全な証明書を提供する必要があります。 |
| 適用されない | ベンダーは、次のいずれかの有効な証明書を提供する必要があります。なし- "tpm"- "packed" (AttCA)- カスタム構成証明形式 &lt;= 32 文字 |

注

ベンダーは、すべてのルート構成証明証明書を FIDO Alliance MDS に発行する責任があります。そうしないと、構成証明の検証が失敗する可能性があります。

さらに、認証が強制されている場合は、以下の要件が満たされる必要があります。

- 認証子に FIDO2 認定資格が必要です。 これは*任意*レベルで指定できます。 認定の詳細については、 [FIDO Alliance Certification Overview](https://fidoalliance.org/certification/) Web サイトを参照してください。
- 製品メタデータは FIDO アライアンス MDS にアップロードする必要があり、ユーザーはメタデータが MDS 内にあることを検証する必要があります。 メタデータは、認証子が次をサポートしていることを示す必要があります。
    - FIDO 2.0 以降。
    - ユーザー検証またはクライアント PIN — Microsoft Entra IDは、すべての FIDO2 認証試行に対して生体認証または PIN を使用したユーザー検証を必要とします。
    - 常駐キー (または検出可能な資格情報) - ユーザー名を入力せずにセキュリティ キーを使用してMicrosoft Entra IDにサインインするには、常駐キーが必要です。
    - Hash-Based メッセージ認証コード (HMAC) シークレット拡張機能または Pseudo-Random 関数 (PRF) 拡張機能 — オフライン シナリオでセキュリティ キーを使用してWindowsのロックを解除するには、HMAC シークレット拡張機能または PRF 拡張機能が必要です。

### タイムライン

Microsoftは、毎月最新バージョンの FIDO Alliance MDS を取り込みます。 FIDO Alliance MDS に FIDO2 セキュリティ キーが表示されるまで、Microsoftがキー モデルを認識するまでの最大 4 週間の遅延が発生する可能性があります。 キーがMicrosoft構成証明の要件を満たしている場合は、MICROSOFT FIDO2 パートナー ページに自動的に表示されます。

### Microsoft Entra ID での認証の対象となる FIDO2 セキュリティ キー

次の表には、MICROSOFT ENTRA IDでの構成証明の対象となる MDS バージョン 279 に記載されている各 FIDO2 セキュリティ キー モデルが含まれています。 以下のテーブルに、各モデルの Authenticator Attestation Globally Unique Identifier (AAGUID) と機能を示します。

| 説明 | AAGUID | 経歴 | USB | NFC | BLE |
| --- | --- | --- | --- | --- | --- |
| ACS FIDO 認証器 | 50a45b0c-80e7-f944-bf29-f552bfa2e048 | ❌ | ✅ | ❌ | ❌ |
| ACS FIDO Authenticator カード | 973446ca-e21c-9a9b-99f5-9b985a67af0f | ❌ | ❌ | ✅ | ❌ |
| ACS FIDO Authenticator NFC | c89e6a38-6c00-5426-5aa5-c9cbf48f0382 | ❌ | ✅ | ✅ | ❌ |
| ACS PocketKey+ Bio | cb4f796c-a20a-af9e-d639-213c1ec247f3 | ✅ | ✅ | ✅ | ❌ |
| Allthenticator Android アプリ: Windows、Mac、Linux、および Allthenticate ドア リーダー向けのローミング BLE FIDO2 Allthenticator | 5ca1ab1e-fa57-1337-f1d0-a117371ca702 | ✅ | ✅ | ❌ | ❌ |
| Allthenticator iOS アプリ: Windows、Mac、Linux、および Allthenticate ドア リーダー向けのローミング BLE FIDO2 Allthenticator | 5ca1ab1e-1337-fa57-f1d0-a117e71ca702 | ✅ | ✅ | ❌ | ❌ |
| Arculus FIDO 2.1 キー カード [P71] | 3f59672f-20aa-4afe-b6f4-7e5e916b6d98 | ❌ | ❌ | ✅ | ❌ |
| Arculus FIDO2/U2F キー カード | 9d3df6ba-282f-11ed-a261-0242ac120002 | ❌ | ❌ | ✅ | ❌ |
| ATKey.Card CTAP2.0 | d41f5a69-b817-4144-a13c-9ebd6d9254d6 | ✅ | ❌ | ❌ | ❌ |
| ATKey.Card NFC | da1fa263-8b25-42b6-a820-c0036f21ba7f | ✅ | ✅ | ✅ | ❌ |
| ATKey.Pro CTAP2.0 | e1a96183-5016-4f24-b55b-e3ae23614cc6 | ✅ | ❌ | ❌ | ❌ |
| ATKey.Pro CTAP2.1 | e416201b-afeb-41ca-a03d-2281c28322aa | ✅ | ✅ | ❌ | ❌ |
| ATKey.ProS | ba76a271-6eb6-4171-874d-b6428dbe3437 | ✅ | ✅ | ❌ | ❌ |
| ATLKey Authenticator | 019614a3-2703-7e35-a453-285fd06c5d24 | ❌ | ✅ | ❌ | ❌ |
| Atos CardOS FIDO2 | 1c086528-58d5-f211-823c-356786e36140 | ❌ | ✅ | ✅ | ❌ |
| authenton1 - CTAP2.1 | b267239b-954f-4041-a01b-ee4f33c145b6 | ❌ | ✅ | ✅ | ❌ |
| CardOS FIDO2 トークン | 8da0e4dc-164b-454e-972e-88f362b23d59 | ❌ | ✅ | ✅ | ❌ |
| チップウォン Clife キー | 930b0c03-ef46-4ac4-935c-538dccd1fcdb | ❌ | ✅ | ❌ | ❌ |
| 中華通信 FIDO2 スマートカード認証器 | 175cd298-83d2-4a26-b637-313c07a6434e | ❌ | ❌ | ✅ | ❌ |
| Clife キー 2 | fc5ca237-69a0-4f3c-afe4-1ebc66def6df | ❌ | ✅ | ❌ | ❌ |
| Clife Key 2 NFC | 23315ad0-6aca-4ba1-952e-f044f1e36976 | ❌ | ✅ | ✅ | ❌ |
| Crayonic KeyVault K1 (USB-NFC-BLE FIDO2認証器) | be727034-574a-f799-5c76-0929e0430973 | ✅ | ✅ | ✅ | ✅ |
| Cryptnox FIDO2 | 9c835346-796b-4c27-8898-d6032f515cc5 | ❌ | ❌ | ✅ | ❌ |
| Cryptnox FIDO2.1 | 1d1b4e33-76a1-47fb-97a0-14b10d0933f1 | ❌ | ❌ | ✅ | ❌ |
| Deepnet SafeKey/Classic (NFC) | b12eac35-586c-4809-a4b1-d81af6c305cf | ❌ | ❌ | ❌ | ❌ |
| Android 用 Egomet FIDO2 Authenticator | 1105e4ed-af1d-02ff-ffff-ffffffffffff | ✅ | ❌ | ❌ | ❌ |
| Ensurity AUTH BioPro | 454e5346-4944-4ffd-6c93-8e9267193e9b | ✅ | ✅ | ❌ | ❌ |
| 保証 ThinC | 454e5346-4944-4ffd-6c93-8e9267193e9a | ✅ | ✅ | ❌ | ❌ |
| NFC を使用したエンタープライズ セキュリティ キー シリーズ (コンシューマー プロファイル) | 24083bcb-3034-4867-99de-a3b52e1d426a | ❌ | ✅ | ✅ | ❌ |
| NFC を使用したエンタープライズ セキュリティ キー シリーズ (エンタープライズ プロファイル) | ab7d1767-3fa0-4388-b6c4-feef7a844809 | ❌ | ✅ | ✅ | ❌ |
| eToken FIDO NFC | b113a455-cfb6-4c17-8cba-cd952feb7d48 | ❌ | ❌ | ✅ | ❌ |
| eToken Fusion BIO | d716019a-9f4e-4041-9750-17c78f8ae81a | ✅ | ✅ | ❌ | ❌ |
| eToken Fusion FIPS（eトークン フュージョン FIPS） | 050dd0bc-ff20-4265-8d5d-305c4b215192 | ❌ | ✅ | ❌ | ❌ |
| eToken Fusion NFC FIPS（電子セキュリティトークン） | 10c70715-2a9a-4de1-b0aa-3cff6d496d39 | ❌ | ❌ | ✅ | ❌ |
| eToken Fusion NFC PIV | 146e77ef-11eb-4423-b847-ce77864e9411 | ❌ | ❌ | ✅ | ❌ |
| eWBM eFA310 FIDO2 認証デバイス | 95442b2e-f15e-4def-b270-efb106facb4e | ✅ | ❌ | ❌ | ❌ |
| eWBM eFA320 FIDO2 認証器 | 87dbc5a1-4c94-4dc8-8a47-97d800fd1f3c | ✅ | ❌ | ❌ | ❌ |
| eWBM eFPA FIDO2 アスティフケーター | 61250591-b2bc-4456-b719-0b17be90bb30 | ✅ | ❌ | ❌ | ❌ |
| Excelsecu eSecu FIDO2 フィンガープリント キー | 6002f033-3c07-ce3e-d0f7-0ffe5ed42543 | ✅ | ✅ | ❌ | ❌ |
| Excelsecu eSecu FIDO2 フィンガープリント セキュリティ キー | 20f0be98-9af9-986a-4b42-8eca4acb28e4 | ✅ | ✅ | ❌ | ❌ |
| Excelsecu eSecu FIDO2 フィンガープリント セキュリティ キー | d384db22-4d50-ebde-2eac-5765cf1e2a44 | ✅ | ✅ | ❌ | ❌ |
| Excelsecu eSecu FIDO2 NFC セキュリティ キー | a3975549-b191-fd67-b8fb-017e2917fdb3 | ❌ | ✅ | ✅ | ❌ |
| Excelsecu eSecu FIDO2 NFC セキュリティ キー | fbefdf68-fe86-0106-213e-4d5fa24cbe2e | ❌ | ✅ | ✅ | ❌ |
| Excelsecu eSecu FIDO2 Pro セキュリティ キー | 0d9b2e56-566b-c393-2940-f821b7f15d6d | ❌ | ✅ | ✅ | ✅ |
| Excelsecu eSecu FIDO2 PRO セキュリティ キー | bbf4b6a7-679d-f6fc-c4f2-8ac0ddf9015a | ❌ | ✅ | ✅ | ✅ |
| Excelsecu eSecu FIDO2 セキュリティ キー | cdbdaea2-c415-5073-50f7-c04e968640b6 | ❌ | ✅ | ❌ | ❌ |
| Feitian AllinOne FIDO2 Authenticator | 12ded745-4bed-47d4-abaa-e713f51d6393 | ✅ | ✅ | ✅ | ✅ |
| Feitian BioPass FIDO2 Authenticator | 77010bd7-212a-4fc9-b236-d2ca5e9d4084 | ✅ | ✅ | ❌ | ❌ |
| Feitian BioPass FIDO2 Plus（エンタープライズプロファイル） | a02140b7-0cbd-42e1-a9b5-a39da2545114 | ✅ | ✅ | ❌ | ❌ |
| Feitian BioPass FIDO2 Plus Authenticator | 42df17de-06ba-4177-a2bb-6701be1380d6 | ✅ | ✅ | ❌ | ❌ |
| Feitian BioPass FIDO2 Plus Authenticator | b6ede29c-3772-412c-8a78-539c1f4c62d2 | ✅ | ✅ | ❌ | ❌ |
| Feitian BioPass FIDO2 Pro（企業向けプロファイル） | 2bff89f2-323a-48fc-b7c8-9ff7fe87c07e | ✅ | ✅ | ❌ | ❌ |
| Feitian BioPass FIDO2 Pro Authenticator | 4c0cf95d-2f40-43b5-ba42-4c83a11c04ba | ✅ | ✅ | ❌ | ❌ |
| Feitian ePass FIDO Authenticator (CTAP2.1, CTAP2.0, U2F) | 12755c32-8ad1-46eb-881c-e0b38d848b09 | ❌ | ✅ | ❌ | ❌ |
| Feitian ePass FIDO-NFC (エンタープライズ プロファイル) (CTAP2.1、CTAP2.0、U2F) | 39589099-9a75-49fc-afaa-801ca211c62a | ❌ | ✅ | ✅ | ❌ |
| Feitian ePass FIDO-NFC(CTAP2.1, CTAP2.0, U2F) | 78ba3993-d784-4f44-8d6e-cc0a8ad5230e | ❌ | ✅ | ✅ | ❌ |
| Feitian ePass FIDO2 Authenticator | 833b721a-ff5f-4d00-bb2e-bdda3ec01e29 | ❌ | ✅ | ❌ | ❌ |
| Feitian ePass FIDO2-NFC 認証器 | ee041bce-25e5-4cdb-8f86-897fd6418464 | ❌ | ✅ | ✅ | ❌ |
| Feitian ePass FIDO2-NFC シリーズ(CTAP2.1、CTAP2.0、U2F) | 234cd403-35a2-4cc2-8015-77ea280c77f5 | ❌ | ✅ | ✅ | ❌ |
| FEITIAN FT-JCOS BioCard | 238ab2f5-b57f-4917-b3c6-3d3c6c0c350f | ✅ | ✅ | ✅ | ❌ |
| Feitian iePass FIDO Authenticator | 3e22415d-7fdf-4ea4-8a0c-dd60c4249b9d | ❌ | ✅ | ❌ | ❌ |
| FIDO KeyPass S3 | f4c63eff-d26c-4248-801c-3736c7eaa93a | ❌ | ✅ | ❌ | ❌ |
| Foongtone FIDO 認証器 | 46544d5d-8f5d-4db4-89ac-ea8977073fff | ❌ | ❌ | ✅ | ❌ |
| FT-JCOS FIDO 指紋カード | 8c97a730-3f7b-41a6-87d6-1e9b62bda6f0 | ❌ | ❌ | ✅ | ❌ |
| G + D StarKey FIDO2-NFC | 7a53c643-9dec-4219-b3a4-f9d24aca4e12 | ❌ | ✅ | ✅ | ❌ |
| GoldKey セキュリティ トークン | 0db01cd6-5618-455b-bb46-1ec203d3213e | ❌ | ✅ | ✅ | ❌ |
| Google Titan セキュリティ キー v2 | 42b4fb4a-2866-43b2-9bf7-6c6669c2e5d3 | ❌ | ✅ | ✅ | ❌ |
| GoTrust Cyber Key | 6d4aa745-dad5-40c4-b9b4-6a252fcee70f | ❌ | ✅ | ✅ | ❌ |
| GoTrust Idem カード | 9f0d8150-baa5-4c00-9299-ad62c8bb4e87 | ❌ | ❌ | ❌ | ❌ |
| GoTrust Idem キー | 3b1adb99-0dfe-46fd-90b8-7f7614a4de2a | ❌ | ✅ | ✅ | ❌ |
| GoTrust Idem キー | c611b55c-77b2-4527-8082-590e931b2f08 | ❌ | ✅ | ✅ | ❌ |
| GoTrust Idem Key mini | 72a2b5b1-95a5-4df9-a881-4192aff4f72e | ❌ | ✅ | ❌ | ❌ |
| GSTAG OAK FIDO2 認証器 | 773c30d9-5919-4e96-a4f5-db65e95cf890 | ❌ | ❌ | ✅ | ❌ |
| HID Crescendo 4000 | 0b8b05a4-ebd4-4b0b-8f5f-33d7b6e606ab | ❌ | ❌ | ✅ | ❌ |
| HID Crescendo 4000 | 2a55aee6-27cb-42c0-bc6e-04efe999e88a | ❌ | ❌ | ✅ | ❌ |
| HID Crescendo 4000 FIDO | aa79f476-ea00-417e-9628-1e8365123922 | ❌ | ❌ | ✅ | ❌ |
| HID Crescendo 4000 FIPS | 8eec9bf9-486c-46da-9a67-1fbb4f66b9ed | ❌ | ❌ | ✅ | ❌ |
| HID Crescendo C2300 | aeb6569c-f8fb-4950-ac60-24ca2bbe2e52 | ❌ | ❌ | ✅ | ❌ |
| HID Crescendo C3000 | c80dbd9a-533f-4a17-b941-1a2f1c7cedff | ❌ | ❌ | ✅ | ❌ |
| HID Crescendo 有効 | 54d9fee8-e621-4291-8b18-7157b99c5bec | ❌ | ❌ | ✅ | ❌ |
| HID クレッシェンド フュージョン | c4ddaf11-3032-4e77-b3b9-3a340369b9ad | ❌ | ❌ | ✅ | ❌ |
| HID Crescendo キー | 692db549-7ae5-44d5-a1e5-dd20a493b723 | ❌ | ✅ | ✅ | ❌ |
| HID Crescendo キー V2 | 2d3bec26-15ee-4f5d-88b2-53622490270b | ❌ | ✅ | ✅ | ❌ |
| HID クレッシェンド キー V3 | 7991798a-a7f3-487f-98c0-3faf7a458a04 | ❌ | ✅ | ✅ | ❌ |
| HID クレッシェンド キー V3 | 87c13177-85d6-40ac-8c61-fe7ab3de9dfb | ❌ | ✅ | ✅ | ❌ |
| HID クレッシェンド キー V3 - Enterprise Edition | 13ac47cf-1d78-4fd5-9060-aedaabacf826 | ❌ | ✅ | ✅ | ❌ |
| Hideez Key 4 FIDO2 ソフトウェア開発キット (SDK) | 4e768f2c-5fab-48b3-b300-220eb487752b | ❌ | ✅ | ✅ | ✅ |
| Hyper FIDO Bio セキュリティ キー | d821a7d4-e97c-4cb6-bd82-4237731fd4be | ✅ | ✅ | ❌ | ❌ |
| Hyper FIDO Pro | 9f77e279-a6e2-4d58-b700-31e5943c6a98 | ❌ | ✅ | ❌ | ❌ |
| Hyper FIDO Pro (CTAP2.1、CTAP2.0、U2F) | 6999180d-630c-442d-b8f7-424b90a43fae | ❌ | ✅ | ❌ | ❌ |
| Hyper FIDO Pro NFC (NFC = 近距離無線通信) | 23195a52-62d9-40fa-8ee5-23b173f4fb52 | ❌ | ✅ | ✅ | ❌ |
| HYPR FIDO2 Authenticator | 0076631b-d4a0-427f-5773-0ec71c9e0279 | ✅ | ❌ | ❌ | ❌ |
| ID-One カード | bb405265-40cf-4115-93e5-a332c1968d8c | ❌ | ❌ | ✅ | ❌ |
| ID-One キー | 82b0a720-127a-4788-b56d-d1d4b2d82eac | ❌ | ✅ | ✅ | ❌ |
| ID-One キー | f2145e86-211e-4931-b874-e22bba7d01cc | ❌ | ✅ | ✅ | ❌ |
| IDCore 3121 Fido | e86addcd-7711-47e5-b42a-c18257b0bf61 | ❌ | ❌ | ✅ | ❌ |
| IDEMIA ID-ONE カード | 8d1b1fcb-3c76-49a9-9129-5515b346aa02 | ❌ | ✅ | ✅ | ❌ |
| IDEMIA SOLVO Fly 80 R3 FIDO Card c | dda9aa35-aaf1-4d3c-b6db-7902fd7dbbbf | ❌ | ❌ | ✅ | ❌ |
| IDEMIA SOLVO Fly 80 R3 FIDO カード e | def8ab1a-9f91-44f1-a103-088d8dc7d681 | ❌ | ❌ | ✅ | ❌ |
| IDEX CTAP2.1 生体認証 | 49a15c1c-3f63-3f51-23a7-b9e00096edd1 | ✅ | ✅ | ✅ | ❌ |
| IDmelon Authenticator | 820d89ed-d65a-409e-85cb-f73f0578f82a | ✅ | ✅ | ❌ | ✅ |
| IDmelon キー | 39a5647e-1853-446c-a1f6-a79bae9f5bc7 | ✅ | ✅ | ✅ | ❌ |
| IDPrime 3930 FIDO | ca4cff1b-5a81-4404-8194-59aabcf1660b | ❌ | ❌ | ✅ | ❌ |
| IDPrime 3940 FIDO | b50d5e0a-7f81-4959-9b12-f45407407503 | ❌ | ❌ | ✅ | ❌ |
| IDPrime 931 Fido | 2194b428-9397-4046-8f39-007a1605a482 | ❌ | ❌ | ✅ | ❌ |
| IDPrime 941 Fido | 2ffd6452-01da-471f-821b-ea4bf6c8676a | ❌ | ❌ | ✅ | ❌ |
| IIST FIDO2 Authenticator | 4b89f401-464e-4745-a520-486ddfc5d80e | ❌ | ✅ | ❌ | ❌ |
| ImproveID 認証アプリ | 4c50ff10-1057-4fc6-b8ed-43a529530c3c | ❌ | ✅ | ✅ | ❌ |
| KEY-ID FIDO2 認証器 | d91c5288-0ef0-49b7-b8ae-21ca0aa6b3f3 | ❌ | ✅ | ❌ | ❌ |
| KeyXentic FIDO2 Secp256R1 FIDO2 CTAP2 認証器 | 4b3f8944-d4f2-4d21-bb19-764a986ec160 | ✅ | ✅ | ❌ | ❌ |
| KeyXentic FIDO2 Secp256R1 FIDO2 CTAP2 認証器 | ec31b4cc-2acc-4b8e-9c01-bade00ccbe26 | ✅ | ✅ | ❌ | ❌ |
| KONAI Secp256R1 FIDO2 準拠テスト CTAP2 認証器 | f7c558a0-f465-11e8-b568-0800200c9a66 | ✅ | ✅ | ✅ | ❌ |
| KX701 SmartToken FIDO | fec067a1-f1d0-4c5e-b4c0-cc3237475461 | ❌ | ✅ | ✅ | ❌ |
| FIDO2 を使用したメトルセミ ヴィシュワアス イーグル 認証システム | 489ff376-b48d-6640-bb69-782a860ca795 | ❌ | ✅ | ❌ | ❌ |
| FIDO2 を使用したメトルセミ ヴィシュワアス ホーク 認証システム | bb66c294-de08-47e4-b7aa-d12c2cd3fb20 | ❌ | ✅ | ❌ | ❌ |
| NEOWAVE Badgeo FIDO2 (ニューワヴェ バッジオ FIDO2) | c5703116-972b-4851-a3e7-ae1259843399 | ❌ | ✅ | ✅ | ❌ |
| NEOWAVE Badgeo FIDO2（CTAP 2.1） | a7fc3f84-86a3-4da4-a3d7-eb6485a066d8 | ❌ | ✅ | ✅ | ❌ |
| NEOWAVE Winkeo FIDO2 | 3789da91-f943-46bc-95c3-50ea2012f03a | ❌ | ✅ | ❌ | ❌ |
| NEOWAVE WINKEO V2.0 | 2c2aeed8-8174-4159-814b-486e92a261d0 | ❌ | ✅ | ❌ | ❌ |
| Nitrokey 3 AM | 2cd2f727-f6ca-44da-8f48-5c2e5da000a2 | ❌ | ✅ | ❌ | ❌ |
| NXP Semiconductros FIDO2 準拠テスト CTAP2 Authenticator | 07a9f89c-6407-4594-9d56-621d5f1e358b | ❌ | ❌ | ❌ | ❌ |
| Nymi FIDO2認証デバイス | 0acf3011-bc60-f375-fb53-6f05f43154e0 | ✅ | ❌ | ✅ | ❌ |
| OCTATCO EzFinger2 FIDO2 AUTHENTICATOR | a1f52be5-dfab-4364-b51c-2bd496b14a56 | ✅ | ❌ | ❌ | ❌ |
| OneKey FIDO2 Bluetooth Authenticator（認証器） | 70e7c36f-f2f6-9e0d-07a6-bcc243262e6b | ❌ | ✅ | ❌ | ✅ |
| OneSpan DIGIPASS FX1 BIO | 30b5035e-d297-4ff1-b00b-addc96ba6a98 | ✅ | ✅ | ❌ | ✅ |
| OneSpan DIGIPASS FX1-C | 30b5035e-d297-4ff1-020b-addc96ba6a98 | ❌ | ✅ | ✅ | ❌ |
| OneSpan DIGIPASS FX1a | 30b5035e-d297-4ff1-010b-addc96ba6a98 | ✅ | ✅ | ✅ | ❌ |
| OneSpan DIGIPASS FX2-A | 30b5035e-d297-4ff2-010b-addc96ba6a98 | ✅ | ✅ | ✅ | ✅ |
| OneSpan DIGIPASS FX7 | 30b5035e-d297-4ff7-020b-addc96ba6a98 | ❌ | ✅ | ❌ | ❌ |
| OneSpan DIGIPASS FX7 | 30b5035e-d297-4ff7-b00b-addc96ba6a98 | ❌ | ✅ | ❌ | ❌ |
| OneSpan DIGIPASS FX7-B | 30b5035e-d297-4ff7-010b-addc96ba6a98 | ❌ | ✅ | ❌ | ❌ |
| OneSpan DIGIPASS FX7-C | 30b5035e-d297-4ff7-030b-addc96ba6a98 | ❌ | ✅ | ✅ | ❌ |
| OneSpan FIDO Touch | 30b5035e-d297-4fc1-b00b-addc96ba6a97 | ❌ | ✅ | ❌ | ✅ |
| OnlyKey Secp256R1 FIDO2 CTAP2 認証装置 | 998f358b-2dd2-4cbe-a43a-e8107438dfb3 | ❌ | ❌ | ❌ | ❌ |
| OpenSK Authenticator | 664d9f67-84a2-412a-9ff7-b4f7d8ee6d05 | ❌ | ✅ | ❌ | ❌ |
| Pone Biometrics OFFPAD Authenticator | 69700f79-d1fb-472e-bd9b-a3a3b9a9eda0 | ✅ | ❌ | ❌ | ✅ |
| Precision InnaIT Key FIDO 2 レベル 2 認定 | 88bbd2f0-342a-42e7-9729-dd158be5407a | ✅ | ✅ | ❌ | ❌ |
| Android 用 RSA Authenticator 4 | 59f85fe7-faa5-4c92-9f52-697b9d4d5473 | ✅ | ❌ | ❌ | ❌ |
| iOS 用 RSA Authenticator 4 | 8681a073-5f50-4d52-bce4-e21658d207b3 | ✅ | ❌ | ❌ | ❌ |
| RSA DS100 | 7e3f3d30-3557-4442-bdae-139312178b39 | ❌ | ✅ | ❌ | ❌ |
| SafeNet eToken FIDO | efb96b10-a9ee-4b6c-a4a9-d32125ccd4a4 | ❌ | ✅ | ❌ | ❌ |
| SafeNet eToken Fusion | 74820b05-a6c9-40f9-8fb0-9f86aca93998 | ❌ | ✅ | ❌ | ❌ |
| SafeNet eToken Fusion CC | 23786452-f02d-4344-87ed-aaf703726881 | ❌ | ✅ | ❌ | ❌ |
| SECORA ID Key S USB by インフィニオン コンシューマーエディション | 9a272558-5cfa-4424-be37-65509677b77d | ❌ | ❌ | ✅ | ❌ |
| SECORA ID V2 by インフィニオンペイエディション | 3e9db280-256a-4e17-b08e-19d79e9be166 | ❌ | ❌ | ✅ | ❌ |
| SECORA ID V2 by Infineon Pay Edition M | 005b20e1-f146-4b87-8f3a-36848ff60ea6 | ❌ | ❌ | ✅ | ❌ |
| SECORA ID V2 FIDO2.1 L1 | 4e2ddbc2-2687-4709-8551-cb66c9776bfe | ❌ | ❌ | ✅ | ❌ |
| Securitag Assembly Group FIDO認証機 NFC | 5df66f62-5b47-43d3-aa1d-a6e31c8dbeb5 | ❌ | ✅ | ✅ | ❌ |
| Yubico のセキュリティ キー | b92c3f9a-c014-4056-887f-140a2501163b | ❌ | ✅ | ❌ | ❌ |
| Yubico のセキュリティ キー | f8a011f3-8c0a-4d15-8006-17111f9edc7d | ❌ | ✅ | ❌ | ❌ |
| NFC を使用した Yubico のセキュリティ キー | 149a2021-8ef6-4133-96b8-81f8d5b7f1f5 | ❌ | ✅ | ✅ | ❌ |
| NFC を使用した Yubico のセキュリティ キー | 6d44ba9b-f6ec-2e49-b930-0c8fe920cb73 | ❌ | ✅ | ✅ | ❌ |
| Yubico のセキュリティ キー NFC | a4e9fc6d-4cbe-4758-b8ba-37598bb5bbaa | ❌ | ✅ | ✅ | ❌ |
| Yubico のセキュリティ キー NFC | b7d3f68e-88a6-471e-9ecf-2df26d041ede | ❌ | ✅ | ✅ | ❌ |
| Yubico のセキュリティ キー NFC | e77e3c64-05e3-428b-8824-0cbeb04b829d | ❌ | ✅ | ✅ | ❌ |
| Yubico製セキュリティキー NFC - エンタープライズ・エディション | 0bb43545-fd2c-4185-87dd-feb0b2916ace | ❌ | ✅ | ✅ | ❌ |
| Yubico製セキュリティキー NFC - エンタープライズ・エディション | 47ab2fb4-66ac-4184-9ae1-86be814012d5 | ❌ | ✅ | ✅ | ❌ |
| Yubico製セキュリティキー NFC - エンタープライズ・エディション | ed042a3a-4b22-4455-bb69-a267b652ae7e | ❌ | ✅ | ✅ | ❌ |
| Yubico によるセキュリティキー NFC - エンタープライズ エディション (エンタープライズ プロファイル) | 72c6b72d-8512-4c66-8359-9d3d10d9222f | ❌ | ✅ | ✅ | ❌ |
| Yubico によるセキュリティキー NFC - エンタープライズ エディション (エンタープライズ プロファイル) | 9ff4cc65-6154-4fff-ba09-9e2af7882ad2 | ❌ | ✅ | ✅ | ❌ |
| NFC を使用したセキュリティ キー シリーズ (コンシューマー プロファイル) | 0f083f18-4105-43a8-ad69-24e812e38141 | ❌ | ✅ | ✅ | ❌ |
| Sentry Enterprises CTAP2 認証器 | 89b19028-256b-4025-8872-255358d950e4 | ✅ | ✅ | ❌ | ✅ |
| シャローオース | 57235694-51a5-4a4d-a81a-f42185df6502 | ❌ | ✅ | ❌ | ❌ |
| SI0X FIDO CL WRIST バージョン1.0 | 912435d9-4a88-42f3-972d-1244b0d51420 | ❌ | ❌ | ✅ | ❌ |
| SmartDisplayer BobeePass FIDO2 認証器 | 516d3969-5a57-5651-5958-4e7a49434167 | ❌ | ✅ | ✅ | ✅ |
| Solo Secp256R1 FIDO2 CTAP2 認証器 | 8876631b-d4a0-427f-5773-0ec71c9e0279 | ❌ | ❌ | ❌ | ❌ |
| Solo Tap Secp256R1 FIDO2 CTAP2 Authenticator | 8976631b-d4a0-427f-5773-0ec71c9e0279 | ❌ | ❌ | ✅ | ❌ |
| Somu Secp256R1 FIDO2 CTAP2 認証器 | 9876631b-d4a0-427f-5773-0ec71c9e0279 | ❌ | ❌ | ❌ | ❌ |
| StarSign FIDO カード | c89674e3-a765-4b07-888a-7c086fbdf04b | ❌ | ❌ | ✅ | ❌ |
| スターサインキーフォブ | f8d5c4e9-e539-4c06-8662-ec2a4155a555 | ✅ | ✅ | ✅ | ✅ |
| Swissbit iShield Key 2 | 7787a482-13e8-4784-8a06-c7ed49a7aaf4 | ❌ | ✅ | ✅ | ❌ |
| Swissbit iShield Key 2 Enterprise | e400ef8c-711d-4692-af46-7f2cf7da23ad | ❌ | ✅ | ✅ | ❌ |
| Swissbit iShield Key 2 FIPS | 817cdab8-0d51-4de1-a821-e25b88519cf3 | ❌ | ✅ | ✅ | ❌ |
| スイスビット iShield Key 2 FIPS エンタープライズ | 5eaff75a-dd43-451f-af9f-87c9eeae293e | ❌ | ✅ | ✅ | ❌ |
| Swissbit iShield Key FIDO2 | 931327dd-c89b-406c-a81e-ed7058ef36c6 | ❌ | ✅ | ❌ | ❌ |
| Swissbit iShield Key Pro | 5d629218-d3a5-11ed-afa1-0242ac120002 | ❌ | ✅ | ✅ | ❌ |
| T-Shield TrustSec FIDO2 BioおよびクライアントPINバージョン | 882adaf5-3aa9-4708-8e7d-3957103775b4 | ✅ | ✅ | ✅ | ❌ |
| Taglio CTAP2.1 BIO | 0f00cc22-4640-41e7-9585-384ec73ffe9b | ✅ | ✅ | ✅ | ❌ |
| Taglio CTAP2.1 CS | 092277e5-8437-46b5-b911-ea64b294acb7 | ❌ | ❌ | ✅ | ❌ |
| Taglio CTAP2.1 EP | 7d2afadd-bf6b-44a2-a66b-e831fceb8eff | ❌ | ❌ | ✅ | ❌ |
| Thales IDPrime FIDO Bio | 4d41190c-7beb-4a84-8018-adf265a6352d | ✅ | ❌ | ✅ | ❌ |
| Thales PAY GFCX13 認証装置 | 04a8fcf2-19c1-457b-911e-69219f17583f | ❌ | ❌ | ✅ | ❌ |
| Thetis Pro FIDO2 キー | 1f8e43df-71ff-e11d-bea3-c4ee7003b232 | ❌ | ✅ | ✅ | ❌ |
| Token Ring 3 FIDO2 Authenticator | c62100de-759b-4bf8-b22b-63b3e3a80401 | ✅ | ❌ | ✅ | ❌ |
| トークンリング FIDO2 認証デバイス | 91ad6b93-264b-4987-8737-3a690cad6917 | ✅ | ❌ | ✅ | ❌ |
| TOKEN2 FIDO2 セキュリティ キー | ab32f0c6-2239-afbb-c470-d2ef4e254db7 | ❌ | ❌ | ❌ | ❌ |
| TOKEN2 PIN Plus セキュリティ キー シリーズ | eabb46cc-e241-80bf-ae9e-96fa6d2975cf | ❌ | ✅ | ✅ | ❌ |
| TruU FIDO2 認証ツール | bb878d7b-cf54-4784-b390-357030497043 | ❌ | ❌ | ❌ | ❌ |
| uTrust FIDO2 セキュリティ キー | 73402251-f2a8-4f03-873e-3cb6db604b03 | ❌ | ✅ | ✅ | ❌ |
| VALMIDO PRO FIDO | 5626bed4-e756-430b-a7ff-ca78c8b12738 | ✅ | ❌ | ❌ | ✅ |
| VeridiumID Passkey Android SDK | 8d4378b0-725d-4432-b3c2-01fcdaf46286 | ✅ | ❌ | ❌ | ✅ |
| VeridiumID Passkey iOS SDK | 1e906e14-77af-46bc-ae9f-fe6ef18257e4 | ✅ | ❌ | ❌ | ✅ |
| VeriMark Guard フィンガープリント キー | d94a29d9-52dd-4247-9c2d-8b818b610389 | ✅ | ❌ | ❌ | ❌ |
| VeriMark NFC+ USB-A セキュリティ キー | 76692dc1-c56a-48d9-8e7d-31b5ced430ac | ❌ | ✅ | ✅ | ❌ |
| VeriMark NFC+ USB-C セキュリティ キー | ee7fa1e0-9539-432f-bd43-9c2fc6d4f311 | ❌ | ✅ | ✅ | ❌ |
| VeriMark(TM) Guard 2.1 フィンガープリント セキュリティ キー | 09619fbf-d75e-4a62-be1d-fe4d240864ae | ✅ | ✅ | ❌ | ❌ |
| VeroCard FIDO2 Authenticator | 99ed6c29-4573-4847-816d-78ad8f1c75ef | ❌ | ❌ | ❌ | ✅ |
| VinCSS FIDO2 Authenticator | 5fdb81b8-53f0-4967-a881-f5ec26fe4d18 | ❌ | ❌ | ❌ | ❌ |
| VinCSS FIDO2 フィンガープリント | 9012593f-43e4-4461-a97a-d92777b55d74 | ✅ | ✅ | ✅ | ✅ |
| WiSECURE AuthTron USB FIDO2 Authenticator | 504d7149-4e4c-3841-4555-55445a677357 | ✅ | ✅ | ❌ | ❌ |
| YubiKey 5 CCN シリーズ (NFC 付き) | 3aa78eb1-ddd8-46a8-a821-8f8ec57a7bd5 | ❌ | ✅ | ✅ | ❌ |
| YubiKey 5 CCN シリーズと NFC (コンシューマー プロファイル) | eb7ef748-cbe0-4b40-b8f6-07bd2d592d35 | ❌ | ✅ | ✅ | ❌ |
| YubiKey 5 CCN シリーズと NFC (エンタープライズ プロファイル) | 3ec9c8d3-a5a7-415b-a7b5-f1d606368d3f | ❌ | ✅ | ✅ | ❌ |
| YubiKey 5 CCN シリーズと NFC (エンタープライズ プロファイル) | 4fc84f16-2545-4e53-b8fc-7bf4d7282a10 | ❌ | ✅ | ✅ | ❌ |
| YubiKey 5 FIPS シリーズ | 57f7de54-c807-4eab-b1c6-1c9be7984e92 | ❌ | ✅ | ❌ | ❌ |
| YubiKey 5 FIPS シリーズ | 73bb0cd4-e502-49b8-9c6f-b59445bf720b | ❌ | ✅ | ❌ | ❌ |
| YubiKey 5 FIPS シリーズ (エンタープライズ プロファイル) | 905b4cb4-ed6f-4da9-92fc-45e0d4e9b5c7 | ❌ | ✅ | ❌ | ❌ |
| YubiKey 5 FIPS シリーズ (Lightning 搭載) | 7b96457d-e3cd-432b-9ceb-c9fdd7ef7432 | ❌ | ✅ | ❌ | ❌ |
| YubiKey 5 FIPS シリーズ (Lightning 搭載) | 85203421-48f9-4355-9bc8-8a53846e5083 | ❌ | ✅ | ❌ | ❌ |
| ライトニング接続対応YubiKey 5 FIPSシリーズ（企業向けプロファイル） | 3a662962-c6d4-4023-bebb-98ae92e78e20 | ❌ | ✅ | ❌ | ❌ |
| YubiKey 5 FIPS シリーズ (NFC 使用) | c1f9a0bc-1dd2-404a-b27f-8e29047a43fd | ❌ | ✅ | ✅ | ❌ |
| YubiKey 5 FIPS シリーズ (NFC 使用) | fcc0118f-cd45-435b-8da1-9782b2da0715 | ❌ | ✅ | ✅ | ❌ |
| YubiKey 5 FIPS シリーズと NFC (エンタープライズ プロファイル) | 79f3c8ba-9e35-484b-8f47-53a5a0f5c630 | ❌ | ✅ | ✅ | ❌ |
| YubiKey 5 シリーズ | 19083c3d-8383-4b18-bc03-8f1c9ab2fd1b | ❌ | ✅ | ❌ | ❌ |
| YubiKey 5 シリーズ | cb69481e-8ff7-4039-93ec-0a2729a154a8 | ❌ | ✅ | ❌ | ❌ |
| YubiKey 5 シリーズ | ee882879-721c-4913-9775-3dfcce97072a | ❌ | ✅ | ❌ | ❌ |
| YubiKey 5 シリーズ | ff4dac45-ede8-4ec2-aced-cf66103f4335 | ❌ | ✅ | ❌ | ❌ |
| YubiKey 5 シリーズ (コンシューマー プロファイル) | 0a357157-9b18-4c8a-920e-d156e972b2f8 | ❌ | ✅ | ❌ | ❌ |
| YubiKey 5 シリーズ (エンタープライズ プロファイル) | 20ac7a17-c814-4833-93fe-539f0d5e3389 | ❌ | ✅ | ❌ | ❌ |
| YubiKey 5 シリーズ (エンタープライズ プロファイル) | 4599062e-6926-4fe7-9566-9e8fb1aedaa0 | ❌ | ✅ | ❌ | ❌ |
| YubiKey 5 シリーズ (エンタープライズ プロファイル) | 524de2de-982f-49b4-a769-2b5e3b73ad79 | ❌ | ✅ | ❌ | ❌ |
| YubiKey 5 シリーズ Lightning 対応 | 24673149-6c86-42e7-98d9-433fb5b73296 | ❌ | ✅ | ❌ | ❌ |
| YubiKey 5 シリーズ Lightning 対応 | a02167b9-ae71-4ac7-9a07-06432ebb6f1c | ❌ | ✅ | ❌ | ❌ |
| YubiKey 5 シリーズ Lightning 対応 | c5ef55ff-ad9a-4b9f-b580-adebafe026d0 | ❌ | ✅ | ❌ | ❌ |
| YubiKey 5 Series with Lightning（コンシューマープロファイル） | 03012cb7-4fb2-42e7-9e8d-a81f10e2a5e9 | ❌ | ✅ | ❌ | ❌ |
| YubiKey 5シリーズ ライトニング対応 (エンタープライズプロファイル) | 3b24bf49-1d45-4484-a917-13175df0867b | ❌ | ✅ | ❌ | ❌ |
| YubiKey 5シリーズ ライトニング対応 (エンタープライズプロファイル) | b90e7dc1-316e-4fee-a25a-56a666a670fe | ❌ | ✅ | ❌ | ❌ |
| YubiKey 5シリーズ ライトニング対応 (エンタープライズプロファイル) | c3479970-e58a-4f70-836f-853bf42fb063 | ❌ | ✅ | ❌ | ❌ |
| YubiKey 5 シリーズ (NFC 使用) | 2fc0579f-8113-47ea-b116-bb5a8db9202a | ❌ | ✅ | ✅ | ❌ |
| YubiKey 5 シリーズ (NFC 使用) | a25342c0-3cdc-4414-8e46-f4807fca511c | ❌ | ✅ | ✅ | ❌ |
| YubiKey 5 シリーズ (NFC 使用) | d7781e5d-e353-46aa-afe2-3ca49f13332a | ❌ | ✅ | ✅ | ❌ |
| YubiKey 5 シリーズ (NFC 使用) | fa2b99dc-9e39-4257-8f92-4a30d23c4118 | ❌ | ✅ | ✅ | ❌ |
| NFC 付き YubiKey 5 シリーズ - 強化された PIN | 662ef48a-95e2-4aaa-a6c1-5b9c40375824 | ❌ | ✅ | ✅ | ❌ |
| YubiKey 5 シリーズ NFC - 拡張 PIN (エンタープライズ プロファイル) | b2c1a50b-dad8-4dc7-ba4d-0ce9597904bc | ❌ | ✅ | ✅ | ❌ |
| NFC 搭載 YubiKey 5 シリーズ (コンシューマー プロファイル) | f4ce5fc0-57d3-46f5-a736-efb7d5bc63b5 | ❌ | ✅ | ✅ | ❌ |
| YubiKey 5 シリーズ NFC 付き (コンシューマー プロファイル) KVZR57-2 | 7dab85a5-d16d-4eaf-a7ef-4c1385b151c5 | ❌ | ✅ | ✅ | ❌ |
| NFC 付き YubiKey 5 シリーズ (エンタープライズ プロファイル) | 1ac71f64-468d-4fe0-bef1-0e5f2f551f18 | ❌ | ✅ | ✅ | ❌ |
| NFC 付き YubiKey 5 シリーズ (エンタープライズ プロファイル) | 41e39911-c669-4811-b860-c6ad0b411b96 | ❌ | ✅ | ✅ | ❌ |
| NFC 付き YubiKey 5 シリーズ (エンタープライズ プロファイル) | 6ab56fad-881f-4a43-acb2-0be065924522 | ❌ | ✅ | ✅ | ❌ |
| NFC 拡張 PIN 付き YubiKey 5 シリーズ (コンシューマー プロファイル) | 0ebd9f2c-f685-441c-8c3e-a02a234a840a | ❌ | ✅ | ✅ | ❌ |
| NFC 拡張 PIN を備えた YubiKey 5 シリーズ (エンタープライズ プロファイル) | 9a3f2abd-a73d-439c-9ee7-1b53a857eaa7 | ❌ | ✅ | ✅ | ❌ |
| NFC KVZR57 の YubiKey 5 シリーズ | 9eb7eabc-9db5-49a1-b6c3-555a802093f4 | ❌ | ✅ | ✅ | ❌ |
| YubiKey Bio Fido Edition (コンシューマー プロファイル) | 9dd8d593-2213-438a-97f8-d6b813d51c27 | ✅ | ✅ | ❌ | ❌ |
| YubiKey Bio Fido Edition（エンタープライズ プロファイル） | add92433-0d69-4026-8166-29b25bce64e9 | ✅ | ✅ | ❌ | ❌ |
| YubiKey Bio Multi-protocol Edition (コンシューマープロファイル) | ba0a9266-40d8-4048-9786-d710b5474752 | ✅ | ✅ | ❌ | ❌ |
| YubiKey Bio Multi-protocol Edition (コンシューマープロファイル) 1VDJSN-2 | 9806a2c8-c0da-478e-b4ca-620005d34182 | ✅ | ✅ | ❌ | ❌ |
| YubiKey Bio マルチプロトコル版（エンタープライズ プロファイル） | dc5e949d-f939-43b3-9877-a85c7186b753 | ✅ | ✅ | ❌ | ❌ |
| YubiKey Bio シリーズ - FIDO Edition | 7409272d-1ff9-4e10-9fc9-ac0019c124fd | ✅ | ✅ | ❌ | ❌ |
| YubiKey Bio シリーズ - FIDO Edition | d8522d9f-575b-4866-88a9-ba99fa02f35b | ✅ | ✅ | ❌ | ❌ |
| YubiKey Bio シリーズ - FIDO Edition | dd86a2da-86a0-4cbe-b462-4bd31f57bc6f | ✅ | ✅ | ❌ | ❌ |
| YubiKey Bio Series - FIDO Edition (Enterprise Profile) | 83c47309-aabb-4108-8470-8be838b573cb | ✅ | ✅ | ❌ | ❌ |
| YubiKey Bio Series - FIDO Edition (Enterprise Profile) | 8c39ee86-7f9a-4a95-9ba3-f6b097e5c2ee | ✅ | ✅ | ❌ | ❌ |
| YubiKey Bio Series - FIDO Edition (Enterprise Profile) | ad08c78a-4e41-49b9-86a2-ac15b06899e2 | ✅ | ✅ | ❌ | ❌ |
| YubiKey Bio シリーズマルチプロトコル エディション | 34744913-4f57-4e6e-a527-e9ec3c4b94e6 | ✅ | ✅ | ❌ | ❌ |
| YubiKey Bio シリーズマルチプロトコル エディション | 7d1351a6-e097-4852-b8bf-c9ac5c9ce4a3 | ✅ | ✅ | ❌ | ❌ |
| YubiKey Bio シリーズマルチプロトコル エディション | 90636e1f-ef82-43bf-bdcf-5255f139d12f | ✅ | ✅ | ❌ | ❌ |
| YubiKey Bio シリーズ - マルチプロトコル エディション (エンタープライズ プロファイル) | 6ec5cff2-a0f9-4169-945b-f33b563f7b99 | ✅ | ✅ | ❌ | ❌ |
| YubiKey Bio シリーズ - マルチプロトコル エディション (エンタープライズ プロファイル) | 97e6a830-c952-4740-95fc-7c78dc97ce47 | ✅ | ✅ | ❌ | ❌ |
| YubiKey Bio シリーズマルチプロトコル エディション 1VDJSN | 58276709-bb4b-4bb3-baf1-60eea99282a7 | ✅ | ✅ | ❌ | ❌ |
| ZTPass SmartAuth | b415094c-49d3-4c8b-b3fe-7d0ad28a6bc4 | ❌ | ✅ | ✅ | ❌ |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-mandatory-multifactor-authentication"} -->
## 必須の Microsoft Entra 多要素認証 (MFA) を計画する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mandatory-multifactor-authentication
- Service: entra-id / authentication
- Article date: 2026-04-03
- Summary: Azure、Microsoft 365、およびその他の管理ポータルに対する必須の多要素認証 (MFA) の適用と、テナントを準備する方法について説明します。

Microsoft では、お客様に最高レベルのセキュリティを提供することに取り組んでいます。 利用できる最も効果的なセキュリティ対策の 1 つは、多要素認証 (MFA) です。 [Microsoft の調査によると](https://www.microsoft.com/security/blog/2019/08/20/one-simple-action-you-can-take-to-prevent-99-9-percent-of-account-attacks) 、MFA は 99.2% を超えるアカウント侵害攻撃をブロックできます。

そのため、2024 年から、すべてのAzureサインイン試行に必須の MFA が適用されます。 この要件の詳細については、ブログ記事「[Azure必須の多要素認証: 2025 年 10 月以降のフェーズ 2](https://azure.microsoft.com/blog/azure-mandatory-multifactor-authentication-phase-2-starting-in-october-2025/) および [サインイン](https://aka.ms/azuremfablogpost)の必須多要素 Azure認証の発表に関するブログ記事を参照してください。 このトピックでは、影響を受けるアプリケーションとアカウント、テナントへの強制のロール アウトの方法、その他の一般的な質問と回答について説明します。

組織が既に MFA を適用している場合や、パスワードレスやパスキー (FIDO2) などのより強力な方法でサインインしている場合は、ユーザーに変更はありません。 MFA が有効になっていることを確認するには、「 [ユーザーが必須の MFA に対して設定されていることを確認する方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mandatory-multifactor-authentication)」を参照してください。

### 実施範囲

適用の範囲には、適用のタイミング、影響を受けるアプリケーション、アカウントの要件が含まれます。

#### 実施フェーズ

Note

フェーズ 2 の適用日が 2025 年 10 月 1 日に変更されました。

アプリケーションに対する MFA の適用は、2 つのフェーズでロールアウトされます。

##### フェーズ 1 のアプリケーション

2024 年 10 月以降、作成、読み取り、更新、または削除 (CRUD) 操作を実行するには、Azure portal、Microsoft Entra 管理センター、Microsoft Intune管理センターにサインインするアカウントに MFA が必要です。 適用は、世界中のすべてのテナントに徐々に実施されます。 2025 年 2 月から、MICROSOFT 365 ADMIN CENTER へのサインインに対する MFA の適用が徐々に開始されます。 フェーズ 1 は、Azure CLI、Azure PowerShell、Azure モバイル アプリ、IaC ツールなどの他のAzure クライアントには影響しません。

##### フェーズ 2 のアプリケーション

2025 年 10 月 1 日以降、Azure CLI、Azure PowerShell、Azure モバイル アプリ、IaC ツール、REST API エンドポイントにサインインするアカウントに対して、作成、更新、または削除の操作を実行する MFA の適用が徐々に開始されます。 読み取り操作では MFA は必要ありません。

一部のお客様は、Microsoft Entra IDサービス アカウントとしてユーザー アカウントを使用できます。 これらのユーザー ベースのサービス アカウントを移行して、[ワークロード ID を](https://learn.microsoft.com/ja-jp/entra/architecture/secure-service-accounts)持つ[クラウドベースのサービス アカウントをセキュリティで保護](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identities-overview)することをお勧めします。

#### アプリケーション ID と URL

次の表に、影響を受けるアプリ、アプリ ID、Azureの URL を示します。

| アプリケーション名 | アプリ ID | 強制の開始 |
| --- | --- | --- |
| [Azure Portal](https://learn.microsoft.com/ja-jp/azure/azure-portal/) | c44b4083-3bb0-49c1-b47d-974e53cbdf3c | 2024 年後半 |
| [Microsoft Entra 管理センター](https://aka.ms/MSEntraPortal) | c44b4083-3bb0-49c1-b47d-974e53cbdf3c | 2024 年後半 |
| [Microsoft Intune管理センター](https://aka.ms/IntunePortal) | c44b4083-3bb0-49c1-b47d-974e53cbdf3c | 2024 年後半 |
| [Azureコマンドライン インターフェイス (Azure CLI)](https://learn.microsoft.com/ja-jp/cli/azure/) | 04b07795-8ddb-461a-bbee-02f9e1bf7b46 | 2025 年 10 月 1 日 |
| [Azure PowerShell](https://learn.microsoft.com/ja-jp/powershell/azure/) | 1950a258-227b-4e31-a9cf-717495945fc2 | 2025 年 10 月 1 日 |
| [Azureモバイル アプリ](https://learn.microsoft.com/ja-jp/azure/azure-portal/mobile-app/overview) | 0c1307d4-29d6-4389-a11c-5cbe7f65d7fa | 2025 年 10 月 1 日 |
| [コードとしてのインフラストラクチャ (IaC) ツール](https://learn.microsoft.com/ja-jp/devops/deliver/what-is-infrastructure-as-code) | Azure CLIまたはAzure PowerShell ID を使用する | 2025 年 10 月 1 日 |
| [REST API (コントロール プレーン)](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/control-plane-and-data-plane#control-plane) | N/A | 2025 年 10 月 1 日 |
| [Azure SDK](https://learn.microsoft.com/ja-jp/azure/developer/intro/azure-developer-create-resources#azure-sdk-and-rest-apis) | N/A | 2025 年 10 月 1 日 |

次の表に、影響を受けるアプリとMicrosoft 365の URL を示します。

| アプリケーション名 | URL | 強制の開始 |
| --- | --- | --- |
| Microsoft 365管理センター | `https://portal.office.com/adminportal/home` | 2025 年 2 月 |
| Microsoft 365管理センター | `https://admin.cloud.microsoft` | 2025 年 2 月 |
| Microsoft 365管理センター | `https://admin.microsoft.com` | 2025 年 2 月 |

#### Accounts

アプリケーション セクションに記載されている操作を実行するためにサインインするすべてのアカウントは、適用の開始時に MFA を完了する必要があります。 Azureでホストされている他のアプリケーション、Web サイト、またはサービスaccess場合、ユーザーは MFA を使用する必要はありません。 前述の各アプリケーション、Web サイト、またはサービス所有者は、ユーザーの認証要件を制御します。

 適用が開始されると、[ブレーク グラス用または緊急用アクセス アカウント](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)も、MFA でサインインする必要があります。 [パスキー (FIDO2)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2) を使用するようにこれらのアカウントを更新するか、MFA の[証明書ベースの認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-certificate-based-authentication)を構成することをお勧めします。 どちらの方法も MFA 要件を満たしています。

マネージド ID やサービス プリンシパルなどのワークロード ID は、この MFA 適用 のいずれかのフェーズ の影響を受けません。 自動化を実行するためにユーザー ID がサービス アカウントとしてログインするのに使用される場合 (スクリプトやその他の自動化タスクを含む)、実施が開始されたらそれらのユーザー ID は MFA でログインする必要があります。 自動化にはユーザー ID は推奨されません。 これらのユーザー ID を [ワークロード ID](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identities-overview) に移行する必要があります。

#### クライアント ライブラリ

OAuth 2.0 リソース所有者パスワード認証 (ROPC) トークン付与フローは、MFA と互換性がありません。 Microsoft Entra テナントで MFA が有効になった後、アプリケーションで使用される ROPC ベースの API によって例外がスローされます。 [Microsoft 認証ライブラリ (MSAL)](https://learn.microsoft.com/ja-jp/entra/msal/) で ROPC ベースの API から移行する方法の詳細については、「[ROPC から移行する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth-ropc#how-to-migrate-away-from-ropc)」を参照してください。 言語固有の MSAL ガイダンスについては、次のタブを参照してください。

## [.NET](#tab/dotnet)
[Microsoft.Identity.Client](https://www.nuget.org/packages/Microsoft.Identity.Client) パッケージと、アプリケーションで次のいずれかの API を使用する場合は、変更が必要です。 パブリック クライアント API は、4.74.0 リリースから非推奨になっています。

- [IByUsernameAndPassword.AcquireTokenByUsernamePassword](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.ibyusernameandpassword.acquiretokenbyusernamepassword) (機密クライアント API)
- [PublicClientApplication.AcquireTokenByUsernamePassword](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplication.acquiretokenbyusernamepassword) (パブリック クライアント API) [非推奨]

## [Go](#tab/go)
[microsoft-authentication-library-for-go](https://pkg.go.dev/github.com/AzureAD/microsoft-authentication-library-for-go) モジュールと、アプリケーションで次のいずれかの API を使用する場合は、変更が必要です。

- [Client.AcquireTokenByUsernamePassword](https://pkg.go.dev/github.com/AzureAD/microsoft-authentication-library-for-go@v1.4.0/apps/confidential#Client.AcquireTokenByUsernamePassword) (機密クライアント API)
- [Client.AcquireTokenByUsernamePassword](https://pkg.go.dev/github.com/AzureAD/microsoft-authentication-library-for-go@v1.4.0/apps/public#Client.AcquireTokenByUsernamePassword) (パブリック クライアント API) [**非推奨**`1.6.0` リリース時点]

## [Java](#tab/java)
[msal4j](https://central.sonatype.com/artifact/com.microsoft.azure/msal4j) パッケージと次の API をアプリケーションで使用する場合は、変更が必要です。

[PublicClientApplication.acquireToken(UserNamePasswordParameters パラメーター)](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.publicclientapplication#com-microsoft-aad-msal4j-publicclientapplication-acquiretoken%28com-microsoft-aad-msal4j-usernamepasswordparameters%29) [ リリース時点`1.24.0`]

## [Node.js](#tab/js)
[@azure/msal-node](https://www.npmjs.com/package/@azure/msal-node) パッケージと次のいずれかの API をアプリケーションで使用する場合は、変更が必要です。 これらの API は、 リリースの時点で、`3.2.3`。

- [ClientApplication.acquireTokenByUsernamePassword](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/confidentialclientapplication#@azure-msal-node-confidentialclientapplication-acquiretokenbyusernamepassword)
- [IConfidentialClientApplication.acquireTokenByUsernamePassword](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/iconfidentialclientapplication#@azure-msal-node-iconfidentialclientapplication-acquiretokenbyusernamepassword)
- [IPublicClientApplication.acquireTokenByUsernamePassword](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/ipublicclientapplication#@azure-msal-node-ipublicclientapplication-acquiretokenbyusernamepassword)

## [Python](#tab/python)
[msal](https://pypi.org/project/msal/) パッケージと次の API をアプリケーションで使用する場合は、変更が必要です。

[ClientApplication.acquire_token_by_username_password](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.clientapplication#msal-application-clientapplication-acquire-token-by-username-password) [ リリース時点でのパブリック クライアント フローでは`1.35.0`]

---

同じ一般的な MSAL ガイダンスは、Azure ID ライブラリにも適用されます。 これらのライブラリで提供される `UsernamePasswordCredential` クラスは、MSAL ROPC ベースの API を使用します。 言語固有のガイダンスについては、次のタブを参照してください。

## [.NET](#tab/dotnet)
[Azure.Identity](https://www.nuget.org/packages/Azure.Identity) パッケージを使用する場合、そしてアプリケーションで次のいずれかの操作を行う場合は、変更が必要です。

- 次の 2 つの環境変数を設定して、[DefaultAzureCredential](https://learn.microsoft.com/ja-jp/dotnet/api/azure.identity.defaultazurecredential) または [EnvironmentCredential](https://learn.microsoft.com/ja-jp/dotnet/api/azure.identity.environmentcredential)を使用します。
    - `AZURE_USERNAME`
    - `AZURE_PASSWORD`
- `UsernamePasswordCredential` を使用する ( リリース時点で[`1.14.0-beta.2`](https://github.com/Azure/azure-sdk-for-net/blob/main/sdk/identity/Azure.Identity/CHANGELOG.md#1140-beta2-2025-03-11))

## [Go](#tab/go)
[azidentity](https://pkg.go.dev/github.com/Azure/azure-sdk-for-go/sdk/azidentity) モジュールを使用し、アプリケーションで次のいずれかの操作を行う場合は、変更が必要です。

- 次の 2 つの環境変数を設定して、[DefaultAzureCredential](https://pkg.go.dev/github.com/Azure/azure-sdk-for-go/sdk/azidentity#DefaultAzureCredential) または [EnvironmentCredential](https://pkg.go.dev/github.com/Azure/azure-sdk-for-go/sdk/azidentity#EnvironmentCredential)を使用します。
    - `AZURE_USERNAME`
    - `AZURE_PASSWORD`
- UsernamePasswordCredential ( リリース時に 非推奨 とされました) の使用

## [Java](#tab/java)
[azure-identity](https://central.sonatype.com/artifact/com.azure/azure-identity) パッケージを使用し、アプリケーションで次のいずれかの操作を行う場合は、変更が必要です。

- 次の 2 つの環境変数を設定して、[DefaultAzureCredential](https://learn.microsoft.com/ja-jp/java/api/com.azure.identity.defaultazurecredential) または [EnvironmentCredential](https://learn.microsoft.com/ja-jp/java/api/com.azure.identity.environmentcredential)を使用します。
    - `AZURE_USERNAME`
    - `AZURE_PASSWORD`
- UsernamePasswordCredential ( リリース時に 非推奨 とされました) の使用

## [Node.js](#tab/js)
[@azure/identity](https://www.npmjs.com/package/@azure/identity) パッケージを使用し、アプリケーションで次のいずれかの操作を行う場合は、変更が必要です。

- 次の 2 つの環境変数を設定して、[DefaultAzureCredential](https://learn.microsoft.com/ja-jp/javascript/api/@azure/identity/defaultazurecredential) または [EnvironmentCredential](https://learn.microsoft.com/ja-jp/javascript/api/@azure/identity/environmentcredential)を使用します。
    - `AZURE_USERNAME`
    - `AZURE_PASSWORD`
- UsernamePasswordCredential ( リリース時に 非推奨 とされました) の使用

## [Python](#tab/python)
[azure-identity](https://pypi.org/project/azure-identity) パッケージを使用し、アプリケーションで次のいずれかの操作を行う場合は、変更が必要です。

- 次の 2 つの環境変数を設定して、[DefaultAzureCredential](https://learn.microsoft.com/ja-jp/python/api/azure-identity/azure.identity.defaultazurecredential) または [EnvironmentCredential](https://learn.microsoft.com/ja-jp/python/api/azure-identity/azure.identity.environmentcredential)を使用します。
    - `AZURE_USERNAME`
    - `AZURE_PASSWORD`
- UsernamePasswordCredential ( リリース時に 非推奨 とされました) の使用

---

#### ユーザー ベースのサービス アカウントをワークロード ID に移行する

サービス アカウントとして使用されているユーザー アカウントを検出し、ワークロード ID への移行を開始することをお勧めします。 多くの場合、移行では、ワークロード ID を使用するためにスクリプトと自動化プロセスを更新する必要があります。

ユーザーが必須の MFA を設定されていることを確認する方法を確認し、サービス アカウントとして使用されているユーザー アカウントを含む、アプリケーションにサインインするすべてのユーザー アカウントを特定します。

これらのアプリケーションで認証するために、ユーザーベースのサービス アカウントからワークロード ID に移行する方法の詳細については、次を参照してください。

- Azure CLI
- [Azure CLI を使用してサービス プリンシパルで Azure にサインインします](https://learn.microsoft.com/ja-jp/cli/azure/authenticate-azure-cli-service-principal)
- [自動化シナリオで非対話形式でAzure PowerShellにサインインします](https://learn.microsoft.com/ja-jp/powershell/azure/authenticate-noninteractive)には、マネージド ID とサービス プリンシパルのユース ケースの両方に関するガイダンスが含まれています

一部のお客様は、ユーザー ベースのサービス アカウントに条件付きAccess ポリシーを適用します。 ユーザー ベースのライセンスを再利用し、[workload ID](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identities-overview) ライセンスを追加して、ワークロード ID に [Conditional Access](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/workload-identity) を適用できます。

### フェデレーション ID プロバイダーを外部 MFA に移行する

外部 MFA ソリューションのサポートは [外部 MFA](https://aka.ms/EAMAdminDocs) で利用でき、MFA 要件を満たすために使用できます。 従来の条件付きAccessカスタム コントロール プレビューでは、MFA 要件を満たしていません。 Microsoft Entra ID で外部ソリューションを使用するには、外部 MFA に移行する必要があります。

Active Directory フェデレーション サービス (AD FS)などのフェデレーション ID プロバイダー (IdP) を使用していて、MFA プロバイダーがこのフェデレーション IdP と直接統合されている場合は、MFA 要求を送信するようにフェデレーション IdP を構成する必要があります。 詳細については、[Microsoft Entra MFA の予期される受信アサーション](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-expected-inbound-assertions)に関する記事を参照してください。

### 必須の MFA 実施に向けた準備

MFA の適用を準備するには、ユーザーが MFA でサインインする必要がある [Conditional Access ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mandatory-multifactor-authentication#verify-mfa-is-enabled-for-microsoft-entra-id-p1-or-microsoft-entra-id-p2-license)を構成します。 ポリシーで例外または除外を構成した場合は、適用されなくなります。 Azureを対象とするより制限の厳しい条件付きAccess ポリシーがあり、フィッシングに強い MFA など、より強力な認証が必要な場合は、引き続き適用されます。

条件付きAccessには、Microsoft Entra ID P1 または P2 ライセンスが必要です。 条件付きAccessを使用できない場合は、[セキュリティの既定値](https://learn.microsoft.com/ja-jp/entra/fundamentals/security-defaults)を有効にします。

Azure Policyの組み込み定義を使用して MFA を自己適用できます。 詳細を確認し、これらのポリシー割り当てを環境に適用する手順の概要については、「Tutorial: Azure Policyを参照してください。 Azure Policyでは、`Audit`効果 (ポリシー コンプライアンスの結果に準拠していないと報告される) と、非準拠の要求をブロックする`Deny`効果の両方がサポートされます。

最適な互換性エクスペリエンスを実現するには、テナント内のユーザーが Azure CLI バージョン 2.76 以降および Azure PowerShell バージョン 14.3 以降を使用していることを確認してください。 それ以外の場合は、次のトピックで説明されているようにエラー メッセージが表示されます。

- Azure PowerShell
- Azure CLI

Note

MFA なしでサインインするユーザーは、 フェーズ 2 アプリケーションを使用できます。 ただし、リソースを作成、更新、または削除しようとすると、アプリは MFA と要求チャレンジでサインインする必要があることを示すエラーを返します。 一部のクライアントは、要求チャレンジを使用して、ユーザーに MFA のステップ アップと実行を求めます。 他のクライアントは、MFA プロンプトなしでエラーのみを返します。 条件付きAccess ポリシーまたはセキュリティの既定値は、ユーザーがエラーを表示する前に MFA を満たすのに役立ちます。

### フェーズ 1 MFA の適用に備える時間を増やす

一部のお客様は、この MFA 要件の準備により多くの時間が必要になる場合があることを理解しています。 Microsoft では、複雑な環境や技術的な障壁を持つお客様が、テナントに対するフェーズ 1 の適用を 2025 年 9 月 30 日まで延期できます。

適用の開始日を延期するテナントごとに、Global Administratorは https://aka.ms/managemfaforazure に移動して開始日を選択できます。

Caution

Azure portalのような Microsoft サービスをaccessアカウントは脅威アクターにとって非常に価値のあるターゲットであるため、適用の開始日を延期することで、追加のリスクが発生します。 すべてのテナントが今すぐ MFA を設定し、クラウド リソースをセキュリティで保護することをお勧めします。

### フェーズ 2 MFA の適用に備える時間を増やす

Microsoft では、複雑な環境や技術的な障壁を持つお客様が、テナントに対するフェーズ 2 の適用を 2026 年 7 月 1 日まで延期できます。 https://aka.ms/postponePhase2MFAでは、フェーズ 2 の MFA の適用に備える時間を増やすことができます。 別の開始日を選択し、[ **適用**] を選択します。 フェーズ 2 の適用が開始されたら、Microsoft のヘルプとサポートに要求を送信して、強制を一時的に解除できます。 要求は、セキュリティへの影響により、Global Administratorによって行われる必要があります。

Note

フェーズ 1 の開始を延期した場合、フェーズ 2 の開始も同じ日付に延期されます。 フェーズ 2 では、後の開始日を選択できます。

[Image: フェーズ 2 の必須 MFA を延期する方法のスクリーンショット。]

### 必須の MFA の適用を確認する

#### フェーズ 1 の適用を確認する

フェーズ 1 の必須 MFA がテナントに適用されていることを確認するには:

1. [グローバル管理者](https://portal.azure.com)として [Azure portal](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) にサインインします。
2. https://aka.ms/managemfaforazure にアクセスしてください。
3. **多要素認証 (フェーズ 1)** ページに、テナントの適用が開始されたことを確認するバナーが表示されていることを確認します。

    [Image: Azure portal の [多要素認証フェーズ 1] ページのスクリーンショット。ディレクトリ内のすべてのユーザーに対して MFA が適用されていることを示しています。]

#### フェーズ 2 の適用を確認する

フェーズ 2 の必須 MFA がテナントに適用されていることを確認するには:

1. [グローバル管理者](https://portal.azure.com)として [Azure portal](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) にサインインします。
2. https://aka.ms/postponePhase2MFA にアクセスしてください。
3. **多要素認証 (フェーズ 2)** ページに、テナントの適用が開始されたことを確認するバナーが表示されていることを確認します。

    [Image: Azure portal の [多要素認証フェーズ 2] ページのスクリーンショット。MFA の適用が 2026 年 2 月 20 日以降に開始されたことを示しています。]

Microsoft Entra ID [サインイン ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins) MFA 要件のソースとして MFA を適用したアプリケーションが表示されます。

### FAQs

**質問**: フェーズ 2 の MFA 適用の影響を受けるアカウントはどれですか?

**Answer**: Azure フェーズ 2 の適用は、PowerShell、CLI、SDK、REST API など、Azure クライアントを介してAzureリソース管理アクションを実行するすべてのユーザー アカウントに適用されます。 この強制はAzure Resource Manager サーバー側で行われるので、`https://management.azure.com` を対象とする要求は適用の対象となります。 Automation アカウントは、マネージド ID またはサービス プリンシパルを使用している限り、スコープ内にありません。 ユーザー ID として設定されたすべてのオートメーション アカウントには強制的な措置が講じられます。

**Question**: 条件付きAccessなしで MFA 強制の影響を理解するにはどうすればよいですか?

**Answer**: Microsoft Entra ID ライセンスに条件付きAccessが含まれていない場合は、Azure Policyを使用して、MFA の適用がテナントに与える影響を理解できます。 システムの適用中に、Microsoft はテナントに [Azure Policy](https://learn.microsoft.com/ja-jp/azure/governance/policy/tutorials/mfa-enforcement) を展開します。 これらの手順に従って、いつでも同じAzure policyを自分でデプロイできます。 ポリシーを監査モードで展開し、強制モードに変換できます。 強制モードの間に、テナントでこのポリシーを適用する日付を選択できます。 その後、Microsoft が MFA を適用しても、テナントにそれ以上の影響はありません。

**質問**: 特定のアカウントに例外はありますか?

**回答**: システム適用は、アカウントが、アクティブなロールまたは権限のあるロールが付与されている学生用アカウント、ブレークグラス用アカウント、管理者アカウント、またはこのようなアカウントに対して[ユーザーが除外](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength#user-exclusions)されているかに関係なく、すべてのユーザー アカウントに適用されます。 これらのアカウントの種類はそれぞれ、Azureでリソース管理アクションを実行でき、侵害された場合は同じセキュリティ リスクが発生します。

**Question**: Microsoft Graph API はフェーズ 2 の適用対象ですか?

**Answer**: 一般に、Microsoft Graph API はAZURE MFA の適用の対象になりません。 `https://management.azure.com/` に送信された要求のみが適用の対象となります。

**質問**: テナントがテストにのみ使用されている場合、MFA は必要ですか?

**Answer**: はい。すべてのAzure テナントでは MFA が必要になりますが、テスト環境に対する例外はありません。

**Question**: この要件はMicrosoft 365 管理センターにどのように影響しますか?

**Answer**: 必須の MFA は、2025 年 2 月からMicrosoft 365 管理センターにロールアウトされます。 Microsoft 365 管理センターの必須 MFA 要件の詳細については、ブログ記事「Microsoft 365 管理センターを参照してください。

**質問**: **サインインしたまま**にするオプションを選択した場合、MFA を完了する必要がありますか?

**回答**: はい。[ **サインインしたまま**にする] を選択した場合でも、これらのアプリケーションにサインインする前に MFA を完了する必要 があります。

**質問**: B2B ゲスト アカウントには適用されますか?

**Answer**: はい。MFA は、パートナー リソース テナントから、またはユーザーのホーム テナントに準拠する必要があります (テナント間のaccessを使用してリソース テナントに MFA 要求を送信するように適切に設定されている場合)。

**質問**: Azureの米国政府向けクラウドやソブリンクラウドにも適用されますか?

**Answer**: Microsoft は、パブリック Azure クラウドでのみ必須の MFA を適用します。 現在、Microsoft では、米国政府やその他のAzureソブリン クラウドに対して、Azureで MFA を適用していません。

**質問**: 別の ID プロバイダーまたは MFA ソリューションを使用して MFA を適用し、Microsoft Entra MFA を使用して適用しない場合、どのように準拠できますか。

**Answer**: サードパーティの MFA をMicrosoft Entra IDと直接統合できます。 詳細については、「 [Microsoft Entra 多要素認証の外部メソッド プロバイダー リファレンス」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-external-method-provider)。 Microsoft Entra IDは、必要に応じてフェデレーション ID プロバイダーを使用して構成できます。 その場合は、 `multipleauthn` 要求を Microsoft Entra ID に送信するように ID プロバイダー ソリューションを適切に構成する必要があります。 詳細については、「[Satisfy Microsoft Entra ID多要素認証 (MFA) コントロールとフェデレーション IdP からの MFA 要求](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-expected-inbound-assertions)を参照してください。

**質問**: 必須の MFA は、Microsoft Entra Connect または Microsoft Entra Cloud Sync と同期する機能に影響しますか?

**回答 :** いいえ。 同期サービス アカウントは、必須の MFA 要件の影響を受けません。 サインイン に MFA が 必要なのは、前述のアプリケーションのみです。

**質問**: オプトアウトできますか?

**Answer**: オプトアウトする方法はありません。このセキュリティ モーションは、Azure プラットフォームの安全性とセキュリティにとって重要であり、クラウド ベンダー間で繰り返されています。 たとえば、 [2024 年の MFA 要件の強化については、「設計によるセキュリティ保護: AWS](https://aws.amazon.com/blogs/security/security-by-design-aws-to-enhance-mfa-requirements-in-2024/)」を参照してください。

実施開始日を延期するオプションはお客様に提供されます。 グローバル管理者は、[Azure portal](https://aka.ms/managemfaforazure) に移動して、テナントの適用の開始日を延期できます。 グローバル管理者は、このページで MFA の適用の開始日を延期する前に、[昇格されたアクセス](https://aka.ms/enableelevatedaccess) を持っている必要があります。 延期が必要なテナントごとに、このアクションを実行する必要があります。

**Question**: 問題が発生しないことを確認するために、Azure がポリシーを強制する前に MFA をテストできますか?

Answer: はい。MFA の手動セットアッププロセスを通じて、MFA をテストできます。 これを設定してテストすることをお勧めします。 条件付きAccessを使用して MFA を適用する場合は、条件付きAccess テンプレートを使用してポリシーをテストできます。 詳細については、「Microsoft 管理ポータルにアクセスする管理者のための多要素認証の必要性」を参照してください。 Microsoft Entra IDの無料版を実行する場合は、[セキュリティの既定値](https://learn.microsoft.com/ja-jp/entra/fundamentals/security-defaults)を有効にすることができます。

**質問**: MFA が既に有効になっている場合、次はどうなりますか?

**Answer**: 前述のアプリケーションをaccessしたユーザーに対して MFA が既に必要な顧客には変更はありません。 ユーザーのサブセットにのみ MFA が必要な場合、MFA をまだ使用していないユーザーは、アプリケーションにログインするときに MFA の使用が必要になりました。

**Question**: Microsoft Entra IDで MFA アクティビティを確認するにはどうすればよいですか?

**回答**: ユーザーが MFA を使用してサインインするように求められるタイミングの詳細を確認するには、Microsoft Entra サインイン ログを使用します。 詳細については、 [Microsoft Entra 多要素認証のサインイン イベントの詳細を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-reporting)。

**質問**: "非常用" シナリオがある場合は?

**回答**: これらのアカウントを更新して [、パスキー (FIDO2)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2) を使用するか、MFA の [証明書ベースの認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-certificate-based-authentication) を構成することをお勧めします。 どちらの方法も MFA 要件を満たしています。

**質問**: MFA が適用される前に MFA の有効化に関する電子メールを受信せず、ロックアウトされた場合はどうすればよいですか。解決方法を教えてください。

**回答**: ユーザーはロックアウトしないでくださいが、テナントの適用が開始されると、MFA を有効にするように求めるメッセージが表示されることがあります。 ユーザーがロックアウトされた場合、他の問題が発生している場合があります。 詳細については、「 [アカウントがロックされている](https://support.microsoft.com/account-billing/account-has-been-locked-805e8b0d-4141-29b2-7b65-df6ff6c9ce27)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-mfa-authprovider"} -->
## Microsoft Entra 多要素認証プロバイダー - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-authprovider
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: どのような場合に Microsoft Entra 多要素認証 (MFA) を備えた認証プロバイダーを使用する必要がありますか?

重要

2018 年 9 月 1 日より、新しい認証プロバイダーが作成されなくなります。 既存の認証プロバイダーは引き続き使用および更新できますが、移行はもうできません。 多要素認証は、Microsoft Entra ID P1 または P2 ライセンスの機能として引き続き使用できます。

Microsoft Entra ID の管理者と Microsoft 365 ユーザーは、既定で 2 段階認証を利用できます。 ただし、 [高度な機能](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-mfasettings) を利用する場合は、条件付きアクセスを使用して Microsoft Entra 多要素認証を有効にする必要があります。 詳細については、「 [一般的な条件付きアクセス ポリシー: すべてのユーザーに MFA を要求する」を](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-policy-all-users-mfa)参照してください。

Microsoft Entra 多要素認証プロバイダーは、 **ライセンスを持たない**ユーザーに対して Microsoft Entra 多要素認証によって提供される機能を利用するために使用されます。

### Microsoft Entra 多要素認証 SDK に関連する注意事項

SDK は非推奨であり、2018 年 11 月 14 日以降に SDK の呼び出しが失敗する点に注意してください

### MFA プロバイダーとは

2 種類の認証プロバイダーがあり、違いは Azure サブスクリプションの課金方法です。 認証ごとのオプションは、1 か月間にテナントに対して実行された認証の数を計算します。 このオプションは、一部のアカウントで認証を行う頻度が低い場合に最適です。 ユーザーごとのオプションでは、MFA を実行する資格があるアカウントの数が計算されます。これは、Microsoft Entra ID の全アカウントと MFA サーバーで有効になっている全アカウントです。 このオプションは、一部のユーザーがライセンスを持っている一方で、ライセンス制限を超えてより多くのユーザーに MFA を拡張する必要がある場合に最適です。

### MFA プロバイダーの管理

MFA プロバイダーの作成後に使用モデル (有効化されたユーザーごと、または認証ごと) を変更することはできません。

MFA が有効化されているすべてのユーザーに対応できる、十分な数のライセンスを購入している場合は、MFA プロバイダーをすべて削除することもできます。

MFA プロバイダーが Microsoft Entra テナントにリンクされていない場合、または新しい MFA プロバイダーを別の Microsoft Entra テナントにリンクする場合、ユーザー設定と構成オプションは転送されません。 また、既存の Microsoft Entra 多要素認証サーバーは、MFA プロバイダーを通じて生成されたアクティブ化資格情報を使用して再アクティブ化する必要があります。

#### 認証プロバイダーの削除

注意事項

認証プロバイダーを削除しても確認されません。 [ **削除** ] の選択は永続的なプロセスです。

認証プロバイダーは、 [Microsoft Entra 管理センター](https://entra.microsoft.com)にあります。 少なくとも [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)としてサインインします。 **Entra ID**&gt;**Multifactor authentication**&gt;**Providers** に移動します。 一覧表示されたプロバイダーをクリックすると、そのプロバイダーに関連付けられている詳細と構成が表示されます。

認証プロバイダーを削除する前に、プロバイダーで構成されているカスタマイズされた設定を記録しておいてください。 ご利用のプロバイダーから一般的な MFA の設定に移行する必要がある設定を決定し、それらの設定の移行を完了します。

プロバイダーにリンクされている Microsoft Entra 多要素認証サーバーは、 **サーバー設定**で生成された資格情報を使用して再アクティブ化する必要があります。 再アクティブ化する前に、環境内の Microsoft Entra 多要素認証サーバーの `\Program Files\Multi-Factor Authentication Server\Data\` ディレクトリから次のファイルを削除する必要があります。

- caCert
- 証明書
- groupCACert
- groupKey
- グループ名
- ライセンスキー
- pkey

[Image: 認証プロバイダーを削除する]

すべての設定が移行されたことを確認したら、[ **プロバイダー** ] を参照し、省略記号 **...** を選択し、[削除] を選択 **します**。

警告

認証プロバイダーを削除すると、そのプロバイダーに関連付けられているすべてのレポート情報が削除されます。 プロバイダーを削除する前に、アクティビティ レポートを保存することができます。

Note

古いバージョンの Microsoft Authenticator アプリと Microsoft Entra 多要素認証サーバーを使用しているユーザーは、アプリの再登録が必要になる場合があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-mfa-data-residency"} -->
## Microsoft Entra 多要素認証データの保存場所 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-data-residency
- Service: entra-id / authentication
- Article date: 2025-06-06
- Summary: Microsoft Entra 多要素認証で保存される、お客様とユーザーに関する個人データと組織データ、および配信元の国/地域内に残っているデータについて説明します。

Microsoft Entra ID は、Microsoft 365 や Azure などの Microsoft オンライン サービスをサブスクライブするときに組織が提供する住所に基づいて、地理的な場所に顧客データを格納します。 顧客データの格納場所については、Microsoft セキュリティ センターの [データの場所](https://www.microsoft.com/trust-center/privacy/data-location) を参照してください。

Microsoft Entra 多要素認証は、個人データと組織データを処理して格納します。 この記事では、データが格納される内容と場所について説明します。

Microsoft Entra 多要素認証サービスには、米国、ヨーロッパ、アジア太平洋にデータセンターがあります。 次のアクティビティは、特に記載されている場合を除き、リージョンのデータセンターから発生します。

- 多要素認証 SMS と電話呼び出しは、顧客のリージョン内のデータセンターから発信され、グローバル プロバイダーによってルーティングされます。 **これらのプロバイダーは、ユーザーと会社の場所の外部に SMS または電話をルーティングできます。** カスタム あいさつを使用した電話は、常に米国内のデータ センターから発信されます。
- 他のリージョンからの汎用ユーザー認証要求は、現在、ユーザーの場所に基づいて処理されます。
- Microsoft Authenticator アプリを使用するプッシュ通知は、現在、ユーザーの場所に基づいてリージョンのデータセンターで処理されます。 Apple Push Notification Service や Google Firebase Cloud Messaging などのベンダー固有のデバイス サービスが、ユーザーの場所外にある可能性があります。

### Microsoft Entra 多要素認証によって格納される個人データ

個人データは、特定のユーザーに関連付けられているユーザー レベルの情報です。 次のデータ ストアには、個人情報が含まれています。

- バイパスされたユーザー
- Microsoft Authenticator デバイス トークンの変更要求
- 多要素認証アクティビティ レポート - 多要素認証のオンプレミス コンポーネント NPS 拡張機能と AD FS アダプターからの多要素認証アクティビティを格納します。
- Microsoft Authenticator のアクティブ化

この情報は 90 日間保持されます。

Microsoft Entra 多要素認証では、ユーザー名、電話番号、IP アドレスなどの個人データは記録されません。 ただし、 *UserObjectId* はユーザーに対する認証試行を識別します。 ログ データは 30 日間保存されます。

#### Microsoft Entra 多要素認証によって格納されるデータ

Azure AD B2C 認証、NPS 拡張機能、Windows Server 2016 または 2019 Active Directory フェデレーション サービス (AD FS) アダプターを除く Azure パブリック クラウドの場合、次の個人データが格納されます。

| イベントの種類 | データ ストアの種類 |
| --- | --- |
| OATH トークン | 多要素認証ログ |
| 片方向ショートメッセージ | 多要素認証ログ |
| 音声通話 | 多要素認証ログ多要素認証アクティビティ レポート データ ストア |
| Microsoft Authenticator の通知 | 多要素認証ログ多要素認証アクティビティ レポート データ ストアMicrosoft Authenticator デバイス トークンが変更されたときに要求を変更する |

Microsoft Azure Government、21Vianet、Azure AD B2C 認証、NPS 拡張機能、Windows Server 2016 または 2019 AD FS アダプターが運営する Microsoft Azure の場合、次の個人データが格納されます。

| イベントの種類 | データ ストアの種類 |
| --- | --- |
| OATH トークン | 多要素認証ログ多要素認証アクティビティ レポート データ ストア |
| 片方向ショートメッセージ | 多要素認証ログ多要素認証アクティビティ レポート データ ストア |
| 音声通話 | 多要素認証ログ多要素認証アクティビティ レポート データ ストア |
| Microsoft Authenticator の通知 | 多要素認証ログ多要素認証アクティビティ レポート データ ストアMicrosoft Authenticator デバイス トークンが変更されたときに要求を変更する |

### Microsoft Entra 多要素認証によって格納される組織データ

組織データは、構成または環境のセットアップを公開できるテナント レベルの情報です。 多要素認証ページのテナント設定には、着信電話認証要求のロックアウトしきい値や発信者 ID 情報などの組織データが格納される場合があります。

- アカウントロックアウト
- 通知
- 電話の設定
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-mfa-howitworks"} -->
## Microsoft Entra 多要素認証の概要 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra 多要素認証を使用して、シンプルなサインイン プロセスを好むユーザーの要望に応えながら、データやアプリケーションへのアクセスを保護する方法について学習します。

多要素認証は、サインイン プロセスでユーザーに別の形式の ID (携帯電話に示されるコードや指紋スキャンなど) を求めるプロセスです。

ユーザーの認証にパスワードのみを使用する場合、不安な攻撃ベクトルが残ります。 パスワードが脆弱である場合、または他の場所で公開されている場合、攻撃者がパスワードを使用してアクセス権を取得している可能性があります。 2 つ目の認証形式を義務付ければ、その二次的な要素は攻撃者が容易に取得したり複製したりできるようなものではないため、セキュリティが向上します。

[Image: さまざまな形式の多要素認証の概念図。]

Microsoft Entra 多要素認証は、次の認証方法のうち 2 つ以上を要求することで機能します。

- ユーザーが知っているもの (通常はパスワード)。
- ユーザーが持っているもの (携帯電話やハードウェア キーのように、簡単には複製できない信頼できるデバイスなど)。
- 本人の特徴 (指紋スキャンや顔認識などの生体認証)。

Microsoft Entra 多要素認証を使用すると、パスワードのリセットをさらにセキュリティで保護できます。 ユーザーは、Microsoft Entra 多要素認証に自分自身を登録するときに、セルフサービス パスワード リセットも 1 回のステップで登録できます。 管理者は、セカンダリ認証の形式を選択し、構成の決定に基づいて MFA のチャレンジを構成できます。

Microsoft Entra 多要素認証を使用するために、アプリケーションまたはサービスを変更する必要はありません。 検証プロンプトは Microsoft Entra サインインの一部であり、必要に応じて MFA チャレンジを自動的に要求して処理します。

注

プロンプト言語は、ブラウザーのロケール設定によって決定されます。 カスタムあいさつを使用しますが、ブラウザーのロケールで識別される言語用のあいさつがない場合、既定で英語が使用されます。 ネットワーク ポリシー サーバー (NPS) では、カスタムの案内応答に関係なく、既定で常に英語が使用されます。 ブラウザーのロケールを識別できない場合も既定で英語が使用されます。

[Image: MFA のサインイン画面。]

### 使用可能な検証方法

ユーザーがアプリまたはサービスにサインインすると、MFA プロンプトが表示され、登録されているいずれかの追加の検証形式を選択できます。 ユーザーは[マイ プロファイル](https://myprofile.microsoft.com)にアクセスして、検証方法を編集または追加できます。

Microsoft Entra 多要素認証では、次のような追加の検証形式を使用できます。

- Microsoft Authenticator
- Authenticator Lite (Outlook 内)
- Windows Hello for Business
- パスキー (FIDO2)
- Microsoft Authenticator でのパスキー
- QRコード
- 証明書ベースの認証 (多要素認証用に構成されている場合)
- 外部 MFA
- 一時アクセス パス (TAP)
- OATH ハードウェア トークン (プレビュー)
- OATH ソフトウェア トークン
- SMS
- 音声通話

### Microsoft Entra 多要素認証の有効化と使用方法

Microsoft Authenticator をすべてのユーザーに対してすぐに有効にするために、Microsoft Entra テナントで[セキュリティの既定値群](https://learn.microsoft.com/ja-jp/entra/fundamentals/security-defaults)を使用できます。 サインイン中にユーザーとグループに追加の検証を要求するように、Microsoft Entra 多要素認証を有効にすることができます。

より詳細な制御を行うために、[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) ポリシーを使用して、MFA を必要とするイベントやアプリを定義できます。 これらのポリシーにより、ユーザーが企業ネットワークまたは登録済みデバイスを使用中の場合は通常のサインインを許可しますが、ユーザーがリモートまたは個人用デバイスを使用中の場合は追加の検証要素を求めることができます。

[Image: サインイン プロセスをセキュリティで保護するために条件付きアクセスがどのように機能するかを示す図。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-mfa-licensing"} -->
## Microsoft Entra 多要素認証のバージョンと従量課金プラン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-licensing
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra 多要素認証クライアントと、使用可能なさまざまな方法およびバージョンについて説明します。

組織内のユーザー アカウントを保護するには、多要素認証を使用する必要があります。 リソースへの特権アクセスが認められているアカウントでは、この機能が特に重要となります。 基本的な多要素認証機能は、追加料金なしで Microsoft 365 と Microsoft Entra のユーザーとグローバル管理者が利用できます。 管理者の機能アップグレードや、他のユーザーへの多要素認証の拡張、より多くの認証方法とより高度な制御を希望する場合は、条件付きアクセスを使用して Microsoft Entra の多要素認証を有効にすることができます。 詳細については、「[一般的な条件付きアクセス ポリシー: すべてのユーザーに対して MFA を必須にする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-policy-all-users-mfa)」を参照してください。

重要

この記事では、Microsoft Entra 多要素認証のライセンスを取得して使用するさまざまな方法について詳しく説明します。 価格と課金の詳細については、[Microsoft Entra の価格に関するページ](https://www.microsoft.com/security/business/microsoft-entra-pricing)を参照してください。

### Microsoft Entra 多要素認証の使用可能なバージョン

Microsoft Entra 多要素認証は、組織のニーズに応じて、いくつかの異なる方法で使用し、ライセンスを取得することができます。 すべてのテナントには、セキュリティの既定値を使用した基本的な多要素認証機能を使用する権利があります。 現在お持ちのライセンスによっては、すでに高度な Microsoft Entra 多要素認証を使用する権利がある場合があります。 たとえば、Microsoft Entra External ID での最初の 50,000 人の月間アクティブ ユーザーは、MFA および他の Premium P1 または P2 の機能を無料で使用できます。

次の表では、Microsoft Entra 多要素認証を入手するさまざまな方法と、それぞれの機能およびユース ケースについて詳しく説明します。

| 次のユーザーの場合 | 機能とユース ケース |
| --- | --- |
| [Microsoft 365 Business Premium](https://www.microsoft.com/microsoft-365/business) および [EMS](https://www.microsoft.com/security/business/enterprise-mobility-security) または [Microsoft 365 E3 と E5](https://www.microsoft.com/microsoft-365/enterprise/compare-office-365-plans) | EMS E3、Microsoft 365 E3、Microsoft 365 Business Premium には Microsoft Entra ID P1 が含まれています。 EMS E5 または Microsoft 365 E5 には、Microsoft Entra ID P2 が含まれています。 次のセクションに記載されている同じ条件付きアクセス機能を使用して、ユーザーに多要素認証を提供できます。 |
| [Microsoft Entra ID P1](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium) | [Microsoft Entra 条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength)を使用して、ビジネス要件に合わせて特定のシナリオやイベントの際に多要素認証をユーザーに求めることができます。 |
| [Microsoft Entra ID P2](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium) | 最も強力なセキュリティのポジションと、向上したユーザー エクスペリエンスを提供します。 Microsoft Entra ID P1の機能に[リスクベースの条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-risk-based-sign-in)を追加し、ユーザーのパターンに合わせて多要素認証プロンプトを最小限に抑えます。 |
| [すべての Microsoft 365 プラン](https://www.microsoft.com/microsoft-365/compare-microsoft-365-enterprise-plans) | Microsoft Entra 多要素認証は、[セキュリティの既定値群](https://learn.microsoft.com/ja-jp/entra/fundamentals/security-defaults)を使用して、すべてのユーザーについて有効にすることができます。 Microsoft Entra 多要素認証の管理は、Microsoft 365 ポータルを通じて行います。 ユーザー エクスペリエンスを向上させるには、Microsoft Entra ID P1 または P2 にアップグレードし、条件付きアクセスを使用します。 詳細については、[多要素認証を使用した Microsoft 365 リソースのセキュリティ保護](https://learn.microsoft.com/ja-jp/microsoft-365/admin/security-and-compliance/set-up-multi-factor-authentication)に関するページを参照してください。 |
| [Office 365 Free](https://www.microsoft.com/microsoft-365/enterprise/compare-office-365-plans)[Microsoft Entra ID Free](https://learn.microsoft.com/ja-jp/entra/verified-id/how-to-create-a-free-developer-account) | 必要に応じて[セキュリティの既定値群](https://learn.microsoft.com/ja-jp/entra/fundamentals/security-defaults)を使用して多要素認証をユーザーに要求できますが、有効となるユーザーまたはシナリオをきめ細かく制御することはできません。ただし、追加のセキュリティ措置を提供することはできます。 |

### ライセンスに基づく機能比較

次の表に、さまざまなバージョンの Microsoft Entra ID 多要素認証で使用できる機能の一覧を示します。 ユーザー認証のセキュリティ保護に必要なものを詳しく検討し、その要件を満たす方法を決定します。 たとえば、Microsoft Entra ID Free は Microsoft Entra 多要素認証を提供するセキュリティの既定値群を提供しますが、認証プロンプトに使用できるのはモバイル認証アプリだけです。 モバイル認証アプリがユーザーの個人のデバイスにインストールされていることを保証できない場合、この方法は制約を受けるかもしれません。 詳細については、後の「Microsoft Entra ID Free レベル」を参照してください。

| 機能 | Microsoft Entra ID Free - セキュリティの既定値群 (すべてのユーザーに対して有効) | Microsoft Entra ID Free - グローバル管理者のみ | オフィス365 | Microsoft Entra ID P1 | Microsoft Entra ID P2 |
| --- | --- | --- | --- | --- | --- |
| MFA で Microsoft Entra テナント管理者アカウントを保護する | ● | ● (*Microsoft Entra グローバル管理者*アカウントの場合のみ) | ● | ● | ● |
| モバイル アプリを 2 番目の要素にする | ● | ● | ● | ● | ● |
| 音声通話を 2 番目の要素にする |  |  | ● | ● | ● |
| 2 番目の要素としてのテキスト メッセージ |  | ● | ● | ● | ● |
| 検証方法の管理制御 |  | ● | ● | ● | ● |
| 不正アクセスのアラート |  |  |  | ● | ● |
| MFA レポート |  |  |  | ● | ● |
| 音声通話のカスタムあいさつ文 |  |  |  | ● | ● |
| 音声通話のカスタム発信元 ID |  |  |  | ● | ● |
| 信頼できる IP |  |  |  | ● | ● |
| 信頼済みデバイスの MFA の記憶 |  | ● | ● | ● | ● |
| オンプレミス アプリケーション用の MFA |  |  |  | ● | ● |
| 条件付きアクセス |  |  |  | ● | ● |
| リスクベースの条件付きアクセス |  |  |  |  | ● |

### 多要素認証ポリシーを比較する

MFA を適用する方法としては、[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)の使用が推奨されます。 次の表を見て、ご利用のライセンスに含まれる機能を確認してください。

| Policy | セキュリティの既定値群 | 条件付きアクセス | ユーザーごとの MFA |
| --- | --- | --- | --- |
| **管理** |  |  |  |
| 会社の安全を維持するためのセキュリティ規則の標準セット | ● |  |  |
| ワンクリックのオンまたはオフ | ● |  |  |
| Office 365 ライセンスに含まれる (ライセンスに関する考慮事項に関するセクションを参照してください) | ● |  | ● |
| Microsoft 365 管理センター ウィザードでの事前構成済みのテンプレート | ● | ● |  |
| 構成の柔軟性 |  | ● |  |
| **機能** |  |  |  |
| ポリシーからユーザーを除外する |  | ● | ● |
| 電話またはテキスト メッセージによる認証 | ● | ● | ● |
| Microsoft Authenticator とソフトウェア トークンによる認証 | ● | ● | ● |
| FIDO2、Windows Hello for Business、およびハードウェア トークンによる認証 |  | ● | ● |
| レガシ認証プロトコルのブロック | ● | ● | ● |
| 新しい従業員の自動的な保護 | ● | ● |  |
| リスク イベントに基づく動的 MFA トリガー |  | ● |  |
| 認証および承認ポリシー |  | ● |  |
| 場所とデバイスの状態に基づいて構成可能 |  | ● |  |
| "レポート専用" モードのサポート |  | ● |  |
| ユーザーまたはサービスを完全にブロックする機能 |  | ● |  |

### Microsoft Entra ID Free レベル

Microsoft Entra ID Free テナントのすべてのユーザーは、セキュリティの既定値群を使用して Microsoft Entra 多要素認証を使用できます。 Microsoft Entra ID Free のセキュリティの既定値群を使用しているときは、Microsoft Entra 多要素認証にモバイル認証アプリを使用できます。

- [Microsoft Entra ID のセキュリティの既定値群の詳細情報](https://learn.microsoft.com/ja-jp/entra/fundamentals/security-defaults)
- [Microsoft Entra ID Free でユーザーに対してセキュリティの既定値群を有効にする](https://learn.microsoft.com/ja-jp/entra/fundamentals/security-defaults#enabling-security-defaults)

使用しているアカウントの種類に応じて、次のいずれかの方法で Microsoft Entra 多要素認証を有効にします。

- Microsoft アカウントを使用している場合は、[多要素認証に登録](https://support.microsoft.com/help/12408/microsoft-account-about-two-step-verification)します。
- Microsoft アカウントを使用していない場合は、[Microsoft Entra ID のユーザーまたはグループの多要素認証を有効にします](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-userstates)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-mfa-regional-opt-in"} -->
## テレフォニー不正防止とスロットル - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-regional-opt-in
- Service: entra-id / authentication
- Article date: 2025-07-28
- Summary: Microsoft Entra ID では、ヒューリスティックと機械学習を使用して、MFA 中の疑わしいテレフォニー アクティビティを検出して調整します。 一部のリージョンでは、不正行為のリスクが高いため、サポート チケットによるオプトインが必要です。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

テレフォニーベースの不正使用や不正行為からお客様を保護するために、Microsoft Entra ID は、すべての通信ベースの認証要求にインテリジェントな検出と調整のメカニズムを適用します。

これらの保護では、ヒューリスティック、機械学習モデル、リスクベースのシグナルの組み合わせを使用して、不正または不正なテレフォニーアクティビティをリアルタイムで検出してブロックします。

さらに、一部のリージョン コードではオプトインが必要です。 管理者は、必要に応じて、これらのリージョンのテレフォニー検証を有効にするサポート 要求を送信できます。

これらのセーフガードを組み合わせることで、正当なユーザーのスムーズな認証エクスペリエンスを維持しながら、組織は不正行為から防御できます。

Note

B2C テナントは、 [B2C サービスの制限](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/service-limits)のガイドラインに従う必要があります。 Microsoft Entra 外部 ID テナントは、「 [コードオプトインのリージョンを設定する方法」のガイドラインに](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-region-code-opt-in)従う必要があります。

### 舞台裏の保護

テレフォニー詐欺に対処するために、Microsoft Entra ID は、ヒューリスティック、リスクシグナル、機械学習モデルの組み合わせを使用して各テレフォニー トランザクション (SMS または音声通話) を評価する階層型保護を採用しています。

これらの保護は、次の検出に役立ちます。

- 通常とは異なる通話ボリュームまたは繰り返しの試行
- 高リスクの地理的パターンまたは通信事業者のパターン
- 既知の不正な動作シグナル
- 無料または従量制の電話番号サービスの不正使用

トランザクションが悪用される可能性があるフラグが設定されている場合:

- トランザクションが制限されることで、SMSや音声通話の即時配信が妨げられる可能性があります。
- ユーザーに "申し訳ございません。アカウントの確認に問題があります" というメッセージが表示されることがあります。
- ユーザーは、Microsoft Authenticator アプリや別の登録済み方法の使用など、代替の認証方法を選択できます。

新しいテナントには、新しく作成されたテナントや侵害されたテナントからの不正使用を防ぐための追加のセーフガードが適用されます。 具体的には、この危険度の高い期間中に過剰な通信使用量を制限し、不正行為の露出を減らすために、テナントが作成された後、テレフォニー アクティビティは最初の数日間調整されます。

### この保護が必要な理由

今日のデジタルの世界では、電気通信サービスが私たちの生活に浸透しています。 しかし、進歩には、不正行為のリスクも高まります。 国際収益シェア詐欺 (IRSF) は、サービスの信頼性を低下させる重大な財務上の影響を与える脅威です。

IRSF は、犯罪者が電気通信サービス プロバイダーの課金システムを悪用して利益を得るテレフォニー詐欺の一種です。 不適切なアクターは、通信ネットワークへの不正アクセスを取得し、トラフィックをプレミアム レート番号に転送して、それらの番号に送信された各トランザクションから利益を得ることができます。 トラフィックを促進するために、不適切なアクターは資格情報を盗んだり、新しいアカウントを作成したり、テキスト メッセージや音声通話を送信する MFA ワークフローを利用したりする可能性があります。 この動作により、顧客に対する過剰な料金、信頼性の低さ、サービスの中断が発生します。

IRSF 攻撃の一般的な動作を次に示します。

1. 不正な者が高料金の電話番号を登録します。
2. 自動スクリプトを使用して、音声通話またはテキスト メッセージを繰り返し要求します。 通信プロバイダーと協力して、彼らの番号にトラフィックを集め、利益の一部を取り出します。
3. リージョン コード間でローテーションして検出を回避し、利益を最大化します。

最も一般的な IRSF 攻撃ベクトルは、テキストまたは音声呼び出しに依存するエンド ユーザー MFA を介して行われます。 悪意のある行為者は、これらのメカニズムを利用してトラフィックをプレミアム番号へ流し、大規模な収益のスキミングと、数十億ドルの損失が発生する可能性があります。

IRSF はオンライン サービスに重大な脅威を与え、評判の害を引き起こす可能性があります。 脅威を理解することで、地域の制限、レート制限、電話番号の検証などの予防措置を実装できます。

### MFA テレフォニー検証に Opt-In が必要なリージョン

SMS と音声の検証では、次のリージョン コードにオプトインが必要です。 これらのリージョンでテレフォニーを有効にする場合は、サポート 要求を送信する必要があります。 **これは、B2C または外部テナントには適用されません。 B2C テナントの場合は、 [B2C サービスの制限](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/service-limits)のガイドラインに従ってください。**

Note

少なくとも 1 つは通信ベースではなく、複数の認証方法を構成することを強くお勧めします。

Note

ユーザーが以前に国で SMS または音声通話を受信したが、受信を停止した場合は、調整が有効になる可能性があります。 しばらくしてからやり直してください。 テナント内のユーザーが特定の国で SMS または音声通話を受信できず、その国が以下に一覧表示されている場合は、テナントがそのリージョンで通信認証を有効にしていない可能性があります。 このような場合は、別の方法を使用してユーザーのブロックを解除することをお勧めします。 通信認証が必要で、代替手段が利用できない場合は、サポート チケットを開いてください。

| リージョン コード | リージョン名 |
| --- | --- |
| Afghanistan | 93 |
| Albania | 355 |
| Algeria | 213 |
| 米領サモア | 1684 |
| Andorra | 376 |
| Angola | 244 |
| Anguilla | 1264 |
| Antarctica | 672 |
| アンティグア・バーブーダ | 1268 |
| Argentina | 54 |
| Armenia | 374 |
| Aruba | 297 |
| Ascension | 247 |
| Azerbaijan | 994 |
| Bahamas | 1242 |
| Bahrain | 973 |
| Bangladesh | 880 |
| Barbados | 1246 |
| Belarus | 375 |
| Belgium | 32 |
| Belize | 501 |
| Benin | 229 |
| Bermuda | 1441 |
| Bhutan | 975 |
| Bolivia | 591 |
| ボスニア・ヘルツェゴビナ | 387 |
| Botswana | 267 |
| 英領インド洋地域 | 246 |
| 英領ヴァージン諸島 | 1284 |
| Brunei | 673 |
| Bulgaria | 359 |
| ブルキナファソ | 226 |
| Burundi | 257 |
| Cambodia | 855 |
| Cameroon | 237 |
| カーボベルデ | 238 |
| ケイマン諸島 | 1345 |
| 中央アフリカ共和国 | 236 |
| Chad | 235 |
| Chile | 56 |
| Comoros | 269 |
| クック諸島 | 682 |
| コスタリカ | 506 |
| Croatia | 385 |
| Cuba | 53 |
| キュラソー島, オランダ領アンティル諸島 | 599 |
| Cyprus | 357 |
| コンゴ民主共和国 | 243 |
| Denmark | 45 |
| Djibouti | 253 |
| Dominica | 1767 |
| ドミニカ共和国 | 1829 |
| ドミニカ共和国 | 1849 |
| ドミニカ共和国 | 1809 |
| 東ティモール | 670 |
| Ecuador | 593 |
| Egypt | 20 |
| エルサルバドル | 503 |
| 赤道ギニア | 240 |
| Eritrea | 291 |
| Estonia | 372 |
| Ethiopia | 251 |
| フォークランド諸島 | 500 |
| フェロー諸島 | 298 |
| Fiji | 679 |
| 仏領ギアナ | 594 |
| フランス領ポリネシア | 689 |
| Gabon | 241 |
| Gambia | 220 |
| Georgia | 995 |
| Ghana | 233 |
| Gibraltar | 350 |
| ギリシャ | 30 |
| Greenland | 299 |
| Grenada | 1473 |
| Guam | 1671 |
| Guatemala | 502 |
| Guinea | 224 |
| Guinea-Bissau | 245 |
| Guyana | 592 |
| Haiti | 509 |
| Honduras | 504 |
| 香港特別行政区 | 852 |
| Hungary | 36 |
| Iceland | 354 |
| Indonesia | 62 |
| Iran | 98 |
| Iraq | 964 |
| Israel | 972 |
| コートジボワール | 225 |
| Jamaica | 1876 |
| Jamaica | 1658 |
| Jordan | 962 |
| Kenya | 254 |
| Kiribati | 686 |
| Kosovo | 383 |
| Kuwait | 965 |
| Kyrgyzstan | 996 |
| Laos | 856 |
| Latvia | 371 |
| Lebanon | 961 |
| Lesotho | 266 |
| Liberia | 231 |
| Libya | 218 |
| Liechtenstein | 423 |
| Lithuania | 370 |
| Luxembourg | 352 |
| マカオ | 853 |
| マケドニア | 389 |
| Madagascar | 261 |
| Malawi | 265 |
| Malaysia | 60 |
| Maldives | 960 |
| Mali | 223 |
| Malta | 356 |
| マーシャル諸島 | 692 |
| Martinique | 596 |
| Mauritania | 222 |
| Mauritius | 230 |
| マヨット、レユニオン | 262 |
| Micronesia | 691 |
| Moldova | 373 |
| Monaco | 377 |
| Mongolia | 976 |
| Montenegro | 382 |
| Montserrat | 1664 |
| モロッコ、西砂漠 | 212 |
| Mozambique | 258 |
| Myanmar | 95 |
| Namibia | 264 |
| Nauru | 674 |
| Nepal | 977 |
| Netherlands | 31 |
| ニューカレドニア | 687 |
| ニュージーランド、ピトケアン | 64 |
| Nicaragua | 505 |
| Niger | 227 |
| Nigeria | 234 |
| Niue | 683 |
| 北朝鮮 | 850 |
| 北マリアナ諸島 | 1670 |
| ノルウェー、スバールバル諸島、ヤン マイエン | 47 |
| Oman | 968 |
| Pakistan | 92 |
| Palau | 680 |
| パレスチナ | 970 |
| Panama | 507 |
| パプアニューギニア | 675 |
| Paraguay | 595 |
| Peru | 51 |
| Philippines | 63 |
| Portugal | 351 |
| プエルトリコ | 1939 |
| プエルトリコ | 1787 |
| Qatar | 974 |
| コンゴ共和国 | 242 |
| ロシア、カザフスタン | 7 |
| Rwanda | 250 |
| Saint Barthelemy, Saint Martin, Guadeloupe | 590 |
| セントヘレナ | 290 |
| セントクリストファー・ネーヴィス | 1869 |
| セントルシア | 1758 |
| サンピエール島/ミクロン島 | 508 |
| セントビンセント・グレナディーン諸島 | 1784 |
| Samoa | 685 |
| サンマリノ | 378 |
| サン・トメ・プリンシペ | 239 |
| サウジアラビア | 966 |
| Senegal | 221 |
| Serbia | 381 |
| Seychelles | 248 |
| シエラレオネ | 232 |
| Slovakia | 421 |
| Slovenia | 386 |
| ソロモン諸島 | 677 |
| Somalia | 252 |
| 南アフリカ | 27 |
| 南スーダン | 211 |
| スリランカ | 94 |
| Sudan | 249 |
| Suriname | 597 |
| スワジランド、エスワティーニ | 268 |
| Sweden | 46 |
| Syria | 963 |
| 台湾 | 886 |
| Tajikistan | 992 |
| Tanzania | 255 |
| Thailand | 66 |
| Togo | 228 |
| Tokelau | 690 |
| Tonga | 676 |
| トリニダード・トバゴ | 1868 |
| Tunisia | 216 |
| Turkmenistan | 993 |
| タークス・カイコス諸島 | 1649 |
| Tuvalu | 688 |
| 米領バージン諸島 | 1340 |
| Uganda | 256 |
| Ukraine | 380 |
| アラブ首長国連邦 | 971 |
| Uruguay | 598 |
| Uzbekistan | 998 |
| Vanuatu | 678 |
| Venezuela | 58 |
| Vietnam | 84 |
| ウォリス フツナ | 681 |
| Yemen | 967 |
| Zambia | 260 |
| Zimbabwe | 263 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-mfa-telephony-fraud"} -->
## Microsoft Entra 多要素認証のテレフォニー詐欺リスクについて - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-telephony-fraud
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra 多要素認証テレフォニー検証の予防措置を実装するために、国際収益共有詐欺 (IRSF) について理解することが重要です。

今日のデジタル環境では、通信サービスが私たちの日常生活にシームレスに統合されています。 しかし、技術的な進歩は、財務上の結果やサービスの中断をもたらす国際収益シェア詐欺(IRSF)のような不正行為のリスクをもたらします。 IRSF には、未承認のアクターによる通信課金システムの悪用が含まれます。 テレフォニー トラフィックを流用し、 *トラフィック ポンプ*と呼ばれる手法によって利益を生み出します。 トラフィック ポンプは多要素認証システムを対象としており、料金の増大、サービスの信頼性の低下、システム エラーの原因となります。

このリスクに対抗するために、IRSF を十分に理解することは、地域の制限や電話番号の確認などの予防措置を実施するために不可欠ですが、システムは中断を最小限に抑え、ビジネス、ユーザー、およびビジネスの両方を保護することを目的としています。そのため、お客様のセキュリティに優先順位を付け、予防的な措置を取ることがあります。

### テレフォニー詐欺と戦う方法

お客様を保護し、詐欺を試みる悪いアクターに対して慎重に防御するために、詐欺攻撃が発生した場合に予防的な修復に取り組む場合があります。 テレフォニー詐欺は非常に動的な空間であり、数秒でも大きな経済的影響を及ぼす可能性があります。 この影響を制限するために、特定のリージョン、電話、またはユーザーからの過剰な認証要求を検出するときに、一時的な調整を積極的に行う場合があります。 これらのスロットルは通常、数時間から数日後に消去されます。

### テレフォニー詐欺との闘いを支援する方法

テレフォニー詐欺と戦うために、B2C のお客様は、サインイン、MFA、パスワードリセット、ユーザー名の忘れなどの認証アクティビティのセキュリティを強化するための手順を実行できます。

- 推奨バージョンのユーザー フローを使用する
- 組織に関連しない地域コードを削除する
- CAPTCHA を使用して人間のユーザーと自動化されたボットを区別する
- 通信の使用状況を確認して、ユーザーからの予想される動作と一致することを確認します

詳細については、「 [B2C での電話ベースの MFA のセキュリティ保護](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/phone-based-mfa)」を参照してください。

さらに、オプトインが必要なリージョンからトラフィックを要求しているため、スロットルが発生する場合もあります。 詳細については、「 [MFA テレフォニー検証をオプトインする必要があるリージョン](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-regional-opt-in)」を参照してください。

Microsoft Entra 外部 ID の場合、外部テナントの既定の SMS 検証は無効になります。 アプリケーション内の特定の国コードに対してテレフォニー トラフィックを有効にするには、 [外部テナントでの MFA テレフォニー検証のリージョンオプトインに関するページ](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-region-code-opt-in)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-passkey-browser-authentication-federated-identity-provider"} -->
## Microsoft Entra IDでの外部 ID プロバイダーのブラウザー認証 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-passkey-browser-authentication-federated-identity-provider
- Service: entra-id / authentication
- Article date: 2026-08-21
- Summary: 外部 ID プロバイダーのブラウザー認証が、埋め込み WebView からシステム ブラウザーにフェデレーション サインインを移動する方法について説明します。

外部 ID プロバイダー (IdP) のブラウザー認証は、外部フェデレーション ID プロバイダーの認証手順を埋め込み WebView からシステム ブラウザーに移動します。 この変更により、FIDO2 セキュリティ キー、パスキー、WebAuthn、既存のブラウザー シングル サインオンなど、ブラウザーの機能に依存する認証エクスペリエンスが可能になります。

この機能により、埋め込み WebView でサポートできない 3 つのシナリオがロック解除されます。

- 外部 ID プロバイダーによって発行されたサードパーティの FIDO2 資格情報とパスキー。
- ブローカー Microsoft アプリから、外部 IdP 認証手順のためにシステム ブラウザーにシングル サインオンします。
- 埋め込み WebView ユーザー エージェントを拒否する IDP は、システム ブラウザーを使用できます。

### それが可能にするもの

- **外部 IdP パスキーの機能**: 外部 IdP が発行したパスキーまたは FIDO2 資格情報を持つユーザーは、Microsoft 365にサインインできます。
- **システム ブラウザーへの SSO**: Brokered アプリは、外部 IdP 認証手順の SSO をシステム ブラウザーに提供できます。
- **WebView ブロック IdP** 成功: 埋め込み WebView をブロックする IdP は、システム ブラウザーで認証が実行されるため機能します。
- **プロンプトの数が少ない**: フロー全体で表示される冗長なパスワード プロンプトの数が少なくなります。

Important

この機能は、Microsoft ID ブローカーがサードパーティの FIDO2 セキュリティ キーを直接サポートするわけではありません。 これは、外部 IdP 認証ステップをシステム ブラウザーで実行できることを意味します。この場合、パスキー、FIDO 対応 IdP フロー、ブラウザー SSO Cookie、WebView をブロックする IdP がすべてより確実に機能します。

### サポートされているスコープと要件

外部 IdP のブラウザー認証は、Microsoft Entra パブリック クラウドで一般提供されています。 国内クラウドでは、既知の問題が解決されている間、この機能はプレビューのままです。

一般公開エクスペリエンスでは、ブローカー認証のみがサポートされます。 サポートされているブローカーをデバイスにインストールする必要があります。 外部 IdP のブラウザー認証では、WS-Fed または SAML 2.0 を介してフェデレーションされたドメインがサポートされます。 該当するブローカーは、開始するアプリのシナリオによって異なります。

| プラットフォームまたはシナリオ | 必要なブラウザー | ブローカーまたはコンポーネントの最小値 | メモ |
| --- | --- | --- | --- |
| Android | 既定のブラウザーとして構成された Chrome | Microsoft Authenticator `6.2510.6857`以降;Windows `1.25102.138.0`以降へのリンク。該当する場合はポータル サイト `5.0.6768.0`以降。ブローカー ライブラリ `14.0.2`以降 | ブローカーの適用可能性は、アプリのシナリオによって異なります。 |
| Android 共有デバイス モード | クロム | 該当する最小値を満たすサポートされている Android ブローカー | このシナリオでは Chrome が必要です。 |
| iOS 機能の最小値 | Safari | Microsoft Authenticator `6.8.29` 以降 | これは機能の最小値です。 |
| 現在の既知の修正プログラムを含む iOS | Safari | Microsoft Authenticator `6.8.37` 以降 | このバージョンは、現在の既知の修正プログラムに推奨されます。 |
| 管理対象の macOS | Safari | ポータル サイト `5.2511` 以降 | デバイスを管理する必要があります。 |

#### サポートされているMicrosoft アプリ

次のMicrosoft アプリはテストされており、一般公開されているブローカー エクスペリエンスでサポートされています。

- 前途
- Teams
- OneDrive
- Word、Excel、PowerPoint
- Microsoft To Do

他のブローカー アプリは、この機能で動作する可能性がありますが、サポート対象として正式に一覧表示されるまでプレビューのままになります。 一般公開されているアプリ エクスペリエンスとプレビュー アプリ エクスペリエンスの両方で、サポートされているブローカーが必要です。

次のシナリオはサポートされていません。

- Linux。
- アンマネージド macOS。
- OAuth2/OIDC ソーシャル IdP。

Android ポータル サイトへの直接サインインは埋め込み WebView のままであり、サポートされているシステム ブラウザーシナリオではありません。 ポータル サイトは、該当する要件が満たされている場合でも、サポートされている他の Android アプリ シナリオのブローカーとして機能できます。

注

Windowsでは、サードパーティの FIDO は既にネイティブでサポートされており、外部 IDP に対してブラウザー認証を提供するためにこの機能と同じメカニズムを使用していません。

### 認証エクスペリエンス

1. ユーザーは、サポートされているブローカー アプリでサインインを開始します。
2. ブローカーは、サポートされているシステム ブラウザーに外部 IdP 認証手順を提供します。
3. ユーザーは、別の認証境界である外部 IdP で認証を完了します。
4. リダイレクト ディープ リンクは、ブローカーに制御を返します。
5. ブローカーは、元のアプリ フローを再開します。

ブラウザーのハンドオフとリターンは、外部で監視できます。 このドキュメントでは、Microsoftまたは外部 IdP が資格情報、パスキー マテリアル、認証応答、セッション、ログ、ストレージ、またはリテンション期間を処理する方法については説明しません。

### コンフィギュレーション

この機能は既定ではオフになっており、テナント管理者が`InternalDomainFederation`の `systemBrowserEnabledOn` プロパティ (スペース区切りまたはコンマ区切りのプラットフォーム リスト) で有効にした場合にのみ有効になります。

#### Microsoft Graph API

##### internalDomainFederations の一覧表示 (API)

内部フェデレーション ドメインの一覧を取得します。

注

`"id"`値 (フェデレーション ドメインの構成 ID) を使用して、パスキーとセキュリティ キーのブラウザー認証を有効にします。

```http
GET /domains/{federated_domain_name}/federationConfiguration
```

##### 有効にする (API)

グローバル管理者は、フェデレーション ドメインの構成 ID に対して `PATCH` 呼び出しを発行し、パスキーとセキュリティ キーのブラウザー認証をサポートするプラットフォームを指定します。

**アクセス許可**: InternalFederation.ReadWrite.All、Domain.ReadWrite.All

サポートされているプラットフォーム値は、 `Ios`、 `Android`、 `Macos`です。 `Android`、`Ios`など、複数の値を一緒に指定できます。 これを `none` に設定すると、機能がオフになります。

```http
PATCH /domains/contoso.com/federationConfiguration/{configuration id}
```

##### リクエスト本文

```json
{
   "systemBrowserEnabledOn": "Android, Ios"
}
```

##### クエリ構成 (API)

```http
GET /domains/contoso.com/federationConfiguration/{configuration id}
```

**サンプル結果**

```json
{
    "@odata.type": "#microsoft.graph.internalDomainFederation",
    "displayName": "Auth0 IdP",
    "issuerUri": "urn:login.contoso.com",
    "signingCertificate": "{signing cert value}",
    "passiveSignInUri": "[https://login.contoso.com/samlp/AbCdEfg1234567](https://login.contoso.com/samlp/AbCdEfg1234567)",
    "preferredAuthenticationProtocol": "saml",
    "systemBrowserEnabledOn": "Android, Ios"
}
```

#### Microsoft Graph PowerShell

##### internalDomainFederations の一覧表示 (PowerShell)

```powershell
Connect-MgGraph -Scope "Domain-InternalFederation.ReadWrite.All"
Update-MgDomainFederationConfiguration -DomainId 'contoso.com'
```

##### 有効にする (PowerShell)

```powershell
Connect-MgGraph -Scope "Domain-InternalFederation.ReadWrite.All"
Update-MgDomainFederationConfiguration -DomainId 'contoso.com' -InternalDomainFederationId '<guid>' -SystemBrowserEnabledOn "Android iOS"
```

##### クエリ構成 (PowerShell)

```powershell
Connect-MgGraph -Scope "Domain-InternalFederation.ReadWrite.All"
Update-MgDomainFederationConfiguration -DomainId 'contoso.com' -InternalDomainFederationId '<guid>'
```

[InternalDomainFederation 関連プロパティ](https://learn.microsoft.com/ja-jp/graph/api/resources/internaldomainfederation#properties):

- `displayName`
- `issuerUri`
- `metadataExchangeUri`
- `passiveSignInUri`
- `activeSignInUri`
- `preferredAuthenticationProtocol`
- `federatedIdpMfaBehavior`
- `promptLoginBehavior`
- `systemBrowserEnabledOn`

現在サポートされているフェデレーション プロトコル: **WS-Fed** と **SAML 2.0**。

### エクスペリエンスを検証する

1. サポート マトリックスに従って、フェデレーション プロトコル、プラットフォーム、ブラウザー、ブローカーが対象であることを確認します。
2. 構成済みのブラウザーとインストールされているブローカーまたはコンポーネントのバージョンが、一覧表示されている要件を満たしていることを確認します。
3. サポートされているブローカー アプリからサインインを開始します。
4. 外部 IdP の認証手順のためにシステムブラウザーが開くことに注意してください。
5. 外部 IdP で認証を完了します。
6. 制御が元のアプリ フローに戻っていることを確認します。

検証は、監視とローカルのままにする必要があります。 認証または診断証拠をキャプチャ、記録、アップロード、アタッチ、保持、または共有しないでください。

### エクスペリエンスのトラブルシューティング

| 症状: | 確認 |
| --- | --- |
| サインインは埋め込み WebView に残ります | シナリオで WS-Fed または SAML 2.0 を使用し、該当するフェデレーション構成とプラットフォームに対して有効になっており、プラットフォームとブローカーの要件を満たしていることを確認します。 Android ポータル サイトへの直接サインインは埋め込み WebView のままであり、サポートされているシステム ブラウザーシナリオではありません。 |
| 予期されるブラウザーが Android で開かない | Chrome が既定のブラウザーとして構成されていることを確認します。 |
| ブラウザーが開きますが、アプリに戻りません | アプリとブローカーが登録済みのリダイレクト ディープ リンクを使用していること、およびインストールされているコンポーネントのバージョンがサポート マトリックスを満たしていることを確認します。 |
| iOS ではエクスペリエンスを利用できません | Safari と Microsoft Authenticator `6.8.29` 以降がインストールされていることを確認します。現在の既知の修正プログラムには、`6.8.37`以降をお勧めします。 |
| macOS ではエクスペリエンスを利用できません | デバイスが管理され、ポータル サイト `5.2511`以降で Safari を使用していることを確認します。 |

### セキュリティとプライバシーに関する考慮事項

- トークン、証明成果物、ブローカー フローの状態、テナント ID、ユーザーまたはデバイス識別子、およびその他の機密認証または個人データは、URL、診断ログ、テレメトリ、またはサポート ノートに配置しないでください。
- 承認されたプロセスを通じてトラブルシューティング データを処理する場合は、データの最小化と承認されたプライバシーと保持の処理が必要です。
- 外部 IdP を個別の認証境界として扱い、そのセキュリティとプライバシーのプラクティスを個別に評価します。

### Terminology

- **ブローカー**は、アプリ、Microsoft Entra ID、システム ブラウザー間の認証を調整する信頼できるプラットフォーム コンポーネントです。
- **システム ブラウザー**は、開始アプリの外部で開かれたデバイスの既定のブラウザーまたはプラットフォーム ブラウザーです。
- **埋め込み WebView** は、アプリまたはブローカー内に表示されるブラウザー サーフェイスです。
- **外部 ID プロバイダーまたはフェデレーション ID プロバイダー (IdP)** は、Microsoft Entra IDとフェデレーションされたドメインのユーザーを認証する個別の認証境界です。
- **FIDO2 セキュリティ キー**は、フィッシングに対する耐性のある認証に FIDO2 標準を使用する物理認証子です。
- **パスキー**は、パスワードなしでサインインするために使用される FIDO 資格情報です。
- **WebAuthn** は、セキュリティ キー、パスキー、またはその他の互換性のある認証子を使用して公開キー認証を要求するために使用される Web 標準ブラウザーです。
- **共有デバイス モード** は、組織が管理する共有デバイス用に設計された Android デバイス モードであり、参加しているアプリ間でサポートされているアカウント切り替えです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-password-ban-bad"} -->
## Microsoft Entra ID のパスワード保護 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra パスワード保護を使用して環境から脆弱なパスワードを動的に禁止する方法についての概要

一般的な規則として、セキュリティ ガイダンスでは、複数の場所で同じパスワードを使用しないことをお勧めします。複雑にしたり、*Password123*などの単純なパスワードを回避したりすることをお勧めします。 [パスワードの選択方法に関するガイダンス](https://www.microsoft.com/research/publication/password-guidance)をユーザーに提供できますが、それでも脆弱なパスワードやセキュリティで保護されていないパスワードが使用されることがよくあります。 Microsoft Entra パスワード保護は、既知の脆弱なパスワードとそのバリアントを検出し、ブロックします。また、組織に固有のその他の脆弱な用語をブロックすることもできます。

Microsoft Entra パスワード保護では、既定のグローバル禁止パスワード リストが Microsoft Entra テナント内のすべてのユーザーに自動的に適用されます。 独自のビジネス ニーズやセキュリティ ニーズに対応するため、カスタム禁止パスワード リストにエントリを定義できます。 ユーザーがパスワードを変更またはリセットすると、これらの禁止パスワード リストがチェックされ、強力なパスワードの使用が強制されます。

Microsoft Entra パスワード保護によって適用される強力なパスワードだけに依存せずに、[Microsoft Entra 多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)などの他の機能も使用する必要があります。 サインイン イベントに対して複数のセキュリティ層を使用する方法の詳細については、「[Your Pa$$word doesn't matter (パスワードは関係ない)](https://techcommunity.microsoft.com/t5/Azure-Active-Directory-Identity/Your-Pa-word-doesn-t-matter/ba-p/731984)」を参照してください。

重要

概念に関するこの記事では、Microsoft Entra パスワード保護のしくみを管理者向けに説明します。 既にセルフサービス パスワード リセットの登録が済んでいて、自分のアカウントに戻る必要があるエンド ユーザーは、 https://aka.ms/sspr にアクセスしてください。

ユーザー自身でパスワードをリセットする機能が IT チームによって有効にされていない場合は、ヘルプデスクに問い合わせてください。

### グローバル禁止パスワード リスト

Microsoft Entra ID Protection チームは、常時 Microsoft Entra セキュリティ テレメトリのデータを分析し、一般的に使用される脆弱なパスワードや侵害されたパスワードを探しています。 具体的には、この分析によって脆弱なパスワードとしてよく使用される脆弱な基本用語を探しています。 脆弱な用語が見つかると、その用語は*グローバル禁止パスワード リスト*に追加されます。 グローバル禁止パスワード リストの内容は、外部のデータ ソースではなく、Microsoft Entra のセキュリティ テレメトリと分析の結果に基づいています。

Microsoft Entra テナントのユーザーのパスワードが変更またはリセットされた場合、現バージョンのグローバル禁止パスワード リストを使用してパスワードの強度が検証されます。 この検証チェックの結果、すべての Microsoft Entra ユーザーのパスワードの強度が向上します。

グローバル禁止パスワード リストは、Microsoft Entra テナントのすべてのユーザーに自動的に適用されます。 何かを有効化したり構成したりする必要はありませんが、無効にすることもできません。 ユーザーが Microsoft Entra ID を使用して自身のパスワードを変更またはリセットしたときに、このグローバル禁止パスワード リストがユーザーに適用されます。

注

サイバー犯罪者も、攻撃の際に同じような戦略を使用して、よく使われる脆弱なパスワードやそのバリエーションを識別します。 セキュリティを強化するため、Microsoft ではグローバル禁止パスワード リストの内容を公開していません。

### カスタム禁止パスワードの一覧

組織によっては、セキュリティを強化するため、グローバル禁止パスワード リストに独自のカスタマイズを追加する場合があります。 独自のエントリを追加するには、*カスタム禁止パスワード リスト*を使用します。 カスタム禁止パスワード リストに追加する用語は、次の例のような組織固有の用語に重点を置く必要があります。

- ブランド名
- 製品名
- 場所 (本社など)
- 会社固有の内部用語
- 会社固有の意味を持つ略語

カスタム禁止パスワード リストに追加した用語は、グローバル禁止パスワード リストの用語と結合されます。 パスワードの変更またはリセット イベントは、これらの結合された禁止パスワード リストに照らして検証されます。

注

カスタム禁止パスワード リストは、最大 1,000 の用語に制限されています。 パスワードの非常に大きなリストをブロックするようには設計されていません。

カスタム禁止パスワード リストの利点を完全に適用するには、まず、カスタム禁止リストに用語を追加する前に、パスワード の評価方法 理解してください。 このアプローチにより、多数の脆弱なパスワードとそのバリエーションを効率的に検出してブロックできるようになります。

[Image: [認証方法] でカスタム禁止パスワード リストを変更する]

*Contoso* という名前の顧客がいるとします。 この会社は、ロンドンに拠点があり、*Widget* という名前の製品を作っています。 このユーザーの例では、以下のような用語のバリエーションをブロックしようとするのは無駄であり、安全性も低下します。

- "Contoso!1"
- "Contoso@London"
- "ContosoWidget"
- "!Contoso"
- "LondonHQ"

その代わり、次の例のような重要な基本用語のみをブロックした方が、はるかに効率的もよく、安全です。

- "Contoso"
- "London"
- "ウィジェット"

パスワード検証アルゴリズムによって、脆弱なバリエーションや組み合わせが自動的にブロックされます。

カスタム禁止パスワード リストの使用を開始するには、次のチュートリアルをご覧ください。

### パスワード スプレー攻撃とサード パーティの侵害されたパスワード一覧

Microsoft Entra パスワード保護は、パスワード スプレー攻撃からの保護に役立ちます。 パスワード スプレー攻撃の大半は、どの個別のアカウントにも数回以上は攻撃しません。 そのような動作をすると、アカウントのロックアウトやその他の手段によって検知される可能性が高まります。

むしろ、ほとんどのパスワード スプレー攻撃では、企業の各アカウントに対して既知の最も脆弱なパスワードが数回のみ送信されます。 この手法を使用すると、攻撃者は容易にセキュリティを侵害できるアカウントを短時間で検索し、潜在的な検出のしきい値を回避することができます。

Microsoft Entra パスワード保護は、パスワード スプレー攻撃で使用される可能性が高いすべての既知の脆弱なパスワードを効率的にブロックします。 この保護では、Microsoft Entra ID の現実世界のセキュリティ テレメトリ データに基づき、グローバル禁止パスワード リストが作成されます。

数百万ものパスワードが列挙されているサード パーティの Web サイトがあります。これらのパスワードは、広く知られている以前のセキュリティ違反によって侵害されたものです。 サード パーティ製のパスワード検証製品では、これら数百万個のパスワードとのブルート フォースによる比較を行うのが一般的です。 しかし、パスワード スプレー攻撃の攻撃者が使用する一般的な戦略を踏まえれば、このような手法はパスワードの強度を向上させる最善の方法ではありません。

注

グローバル禁止パスワード リストは、侵害されたパスワードのリストを含め、いかなるサード パーティ製のデータ ソースにも基づいていません。

グローバル禁止リストは一部のサード パーティ製の巨大なリストに比べれば小さめですが、実際のパスワード スプレー攻撃による現実世界のセキュリティ テレメトリに基づいています。 この手法によって全体的なセキュリティと有効性が向上し、パスワード検証アルゴリズムでもスマートなあいまい一致の手法が使用されます。 その結果、Microsoft Entra パスワード保護では、特によく使われる脆弱なパスワードが効率的に検出され、企業で使用されることがないようにブロックされます。

### オンプレミスのハイブリッド シナリオ

多くの組織では、オンプレミスの Active Directory Domain Services (AD DS) 環境を含むハイブリッド ID モデルが使用されています。 Microsoft Entra パスワード保護のセキュリティ上の利点を AD DS 環境に拡張するため、オンプレミスのサーバーにコンポーネントをインストールできます。 これらのエージェントでは、オンプレミスの AD DS 環境のパスワード変更イベントを Microsoft Entra ID の場合と同じパスワード ポリシーに準拠させる必要があります。

詳細については、[AD DS に Microsoft Entra パスワード保護を適用する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-on-premises)を参照してください。

### パスワードの評価方法

ユーザーが自分のパスワードを変更またはリセットすると、グローバルとカスタム禁止パスワード リストから結合された用語リストに対して新しいパスワードを検証することで、その強度と複雑さがチェックされます。

ユーザーのパスワードに禁止パスワードが含まれている場合でも、パスワード全体が十分に強力な場合、そのパスワードは受け入れられる可能性があります。 新しく構成されたパスワードは、受け入れるか拒否するかを決定するために、次の手順を踏んで全体的な強度が評価されます。

注

Microsoft Entra ID のパスワード保護とオンプレミス ユーザーのパスワード保護の間に相関関係はありません。 パスワード保護の検証は、2 つのサービスにまたがるユーザーで一貫性がありません。 最初にパスワードを設定するとき、または SSPR を完了するときに、テナント内のユーザーがそれぞれのサービスに必要なパスワード パラメーターを満たしていることを確認してください。

#### 手順 1:正規化

新しいパスワードは、まず、正規化プロセスを完了します。 この手法により、少数の禁止パスワードを、脆弱な可能性のある非常に大きいパスワードのセットにマップすることができます。

正規化は次の 2 つの部分に分かれています。

- すべて大文字の文字が小文字に変更されます。
- 次に、次の例のように、共通の文字置換が実行されます。

    | 元の文字 | 置換される文字 |
    | --- | --- |
    | 0 | o |
    | 1 | l |
    | $ | s |
    | @ | am |

次に例を示します。

- "blank" というパスワードが禁止されています。
- あるユーザーが自分のパスワードを "Bl@nK" に変更しようとしています。
- "Bl@nk" が禁止されていないとしても、正規化プロセスによってこのパスワードは "blank" に変換されます。
- このパスワードは拒否されます。

#### 手順 2:パスワードが禁止と見なされるかどうか確認する

次に、パスワードは他の一致の動作で確認され、スコアが生成されます。 この最終的なスコアによって、パスワード変更要求を受け入れるか拒否するかが決定されます。

##### あいまい一致の動作

あいまい一致は、グローバルまたはカスタム禁止パスワード リストのいずれかにあるパスワードが含まれているかどうかを確認するために、正規化されたパスワードに対して使用されます。 一致プロセスは、編集距離 1 の比較に基づいています。

次に例を示します。

- "abcdef" というパスワードが禁止されています。
- あるユーザーが自分のパスワードを次のいずれかに変更しようとしています。

    - 'abcdeg' - *末尾の文字を 'f' から 'g' に変更*
    - 'abcdefg' - *末尾に 'g' を追加*
    - 'abcde' - *末尾の 'f' を末尾から削除*
- 上のパスワードのそれぞれは、厳密に言えば禁止パスワード "abcdef" に一致しません。

    ただし、それぞれの例は禁止されている用語 'abcdef' の編集距離 1 以内にあるため、これらはすべて "abcdef" に一致するものと見なされます。
- これらのパスワードは拒否されます。

##### 部分文字列の照合 (特定の条件下)

部分文字列照合は、正規化されたパスワードで、ユーザーの名と姓、およびテナント名を確認するために使用されます。 テナント名の照合は、オンプレミスのハイブリッド シナリオの AD DS ドメイン コントローラー上でパスワードを検証する場合は行われません。

重要

部分文字列の照合は、4 文字以上の名前とその他の用語に対してのみ適用されます。

次に例を示します。

- 例: Pol というユーザーが、自分のパスワードを "p0LL23fb" にリセットしようとしているとします。
- 正規化後、このパスワードは "poll23fb" になります。
- 部分文字列の照合により、このパスワードにはユーザーの名前 "Poll" が含まれることがわかります。
- "poll23fb" は厳密にはどちらの禁止パスワード リストにも含まれていませんが、部分文字列の照合ではパスワード内に "Poll" が見つかりました。
- このパスワードは拒否されます。

##### スコアの計算

次の手順では、ユーザーの正規化された新しいパスワード内の、禁止されたパスワードのすべてのインスタンスを特定します。 次の基準に基づいてポイントが割り当てられます。

1. ユーザーのパスワードで見つかった禁止パスワードには、それぞれ 1 つのポイントが与えられます。
2. 禁止パスワードに含まれない残りの各文字には、1 ポイントが与えられます。
3. パスワードが受け入れられるには、少なくとも 5 ポイント必要です。

次の 2 つのシナリオ例では、Contoso が Microsoft Entra パスワード保護を使用していて、カスタム禁止パスワード リストに "contoso" が含まれているとします。 また、グローバル リストに "blank" が含まれているとします。

次のシナリオ例では、ユーザーが自分のパスワードを "C0ntos0Blank12" に変更します。

- 正規化後、このパスワードは "contosoblank12" になります。
- 照合プロセスにより、このパスワードには "contoso" と "blank" という 2 つの禁止パスワードが含まれていることがわかります。
- このパスワードには以下のスコアが与えられます。

    *[contoso] + [blank] + [1] + [2] = 4 ポイント*
- このパスワードは 5 ポイント未満のため、拒否されます。

パスワードの複雑さを増すことで、受け入れられるために必要なポイント数が発生するしくみを示すため、少し異なる例を確認しましょう。 次のシナリオ例では、ユーザーが自分のパスワードを "ContoS0Bl@nkf9!" に変更します。

- 正規化後、このパスワードは "contosoblankf9!" になります。
- 照合プロセスにより、このパスワードには "contoso" と "blank" という 2 つの禁止パスワードが含まれていることがわかります。
- このパスワードには以下のスコアが与えられます。

    *[contoso] + [blank] + [f] + [9] + [!] = 5 ポイント*
- このパスワードは 5 ポイント以上であるため、受け入れられます。

重要

禁止パスワード アルゴリズムとグローバル禁止パスワード リストは、継続的なセキュリティ分析と調査に基づいて Azure でいつでも変更できます。

ハイブリッド シナリオでのオンプレミスの DC エージェント サービスの場合、更新されたアルゴリズムは DC エージェント ソフトウェアがアップグレードされた後でのみ有効になります。

### ユーザーに表示される画面

ユーザーがパスワードを禁止されるパスワードにリセットまたは変更しようとすると、以下のいずれかのエラー メッセージが表示されます。

*"残念ながら、パスワードを簡単に推測できる単語、語句、またはパターンが含まれています。 別のパスワードで再実行してください。"*

*"同じパスワードがこれまでに何度も使われています。 より推測されにくいパスワードをお選びください。"*

*"第三者によって推測されにくいパスワードを選択してください。"*

### ライセンスの要件

| ユーザー | グローバル禁止パスワード リストを使用した Microsoft Entra パスワード保護 | カスタム禁止パスワード リストを使用した Microsoft Entra パスワード保護 |
| --- | --- | --- |
| クラウド専用ユーザー | Microsoft Entra ID Free（無料） | Microsoft Entra ID P1 または P2 |
| オンプレミスの AD DS から同期されたユーザー | Microsoft Entra ID P1 または P2 | Microsoft Entra ID P1 または P2 |

注

Microsoft Entra ID に同期されていないオンプレミスの AD DS ユーザーも、同期されたユーザー向けの既存のライセンスに基づく Microsoft Entra パスワード保護の利点を受けることができます。

ライセンスについての詳細は、[Microsoft Entra の価格に関するサイト](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-password-ban-bad-combined-policy"} -->
## パスワード ポリシーの組み合わせと、Microsoft Entra ID での脆弱なパスワードのチェック - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: パスワード ポリシーの組み合わせと、Microsoft Entra ID での脆弱なパスワードのチェックについての概要

2021 年 10 月から、パスワード ポリシーに準拠するための Microsoft Entra 検証には、[既知の脆弱なパスワード](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad) とそのバリアントのチェックも含まれます。 この記事では、Microsoft Entra ID によってチェックされるパスワード ポリシー条件の詳細について説明します。

### Microsoft Entra のパスワード ポリシー

パスワード ポリシーは、Microsoft Entra ID で直接作成および管理されるすべてのユーザーおよび管理者のアカウントに適用されます。 [脆弱なパスワードを禁止し](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad)、不正なパスワードの試行が繰り返された後に[アカウントをロックする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout)パラメーターを定義できます。 その他のパスワード ポリシー設定は変更できません。

Microsoft Entra パスワード ポリシーは、CloudPasswordPolicyForPasswordSyncedUsersEnabled を有効にしない限り、Microsoft Entra Connect を使用してオンプレミスの AD DS 環境から同期されたユーザー アカウントには適用されません。 CloudPasswordPolicyForPasswordSyncedUsersEnabled とパスワード ライトバックが有効になっている場合、Microsoft Entra パスワードの有効期限ポリシーが適用されますが、長さ、複雑さなどについては、オンプレミスのパスワード ポリシーが優先されます。

次の Microsoft Entra パスワード ポリシー要件が、Microsoft Entra ID で作成、変更、リセットされるすべてのパスワードに適用されます。 要件は、ユーザー プロビジョニング、パスワードの変更、パスワード リセットのフロー中に適用されます。 これらの設定は、記載されている場合を除き、変更できません。

| プロパティ | 要件 |
| --- | --- |
| 使用できる文字 | 大文字 (A から Z)小文字 (a から z)数字 (0 から 9)記号:- @ # $ % ^ & \* - \_ ! + = [ ] { } |\ : ' , . ? / ` ~ " ( ) ; &lt;&gt;- 空白 |
| 使用できない文字 | Unicode 文字。 注: Microsoft Entra 外部 ID テナントの場合、ユーザーが Microsoft Graph API または Self-Service サインアップを使用して作成された場合、Unicode 文字が許可されます。 |
| パスワードの長さ | パスワードに必要な条件- 8 文字以上- 最大 256 文字 |
| パスワードの複雑さ | パスワードには、次の 4 つのカテゴリのうち 3 つが必要です。- 大文字- 小文字- 数字- 記号 注: Education テナントでは、パスワードの複雑さのチェックは要求されません。 |
| 最近使用されていないパスワード | ユーザーが自分のパスワードを変更する場合、新しいパスワードは現在のパスワードと同じにしないでください。 |
| パスワードが [Microsoft Entra パスワード保護](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad) によって禁止されていないこと | パスワードは、Microsoft Entra パスワード保護についての禁止パスワードのグローバル リストや、組織に固有のカスタマイズ可能な禁止パスワード リストに含まれていてはなりません。 |

### パスワードの有効期限のポリシー

パスワードの有効期限ポリシーは変更されませんが、完全を期す目的でこの記事に含まれています。 少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) が割り当てられているユーザーは、[Microsoft Graph PowerShell コマンドレット](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/) を使用し、ユーザーのパスワードの有効期限が切れないように設定できます。

注

既定では、期限切れにならないように設定できるのは、Microsoft Entra Connect による同期を行っていないユーザー アカウントのパスワードのみです。 ディレクトリ同期の詳細については、「[AD と Microsoft Entra ID を接続する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization#password-expiration-policy)」を参照してください。

また、PowerShell を使用すると、期限のない構成を削除したり、期限が切れないように設定されているユーザー パスワードを確認したりすることもできます。

次の有効期限の要件は、Microsoft Intune や Microsoft 365 などの、ID やディレクトリ サービスに Microsoft Entra ID を使用する他のプロバイダーに適用されます。

| プロパティ | 要件 |
| --- | --- |
| パスワードの有効期間 (パスワードの最大有効期間) | 既定値:**90** 日。この値は、Microsoft Graph PowerShell モジュールの [Update-MgDomain](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/update-mgdomain) コマンドレットを使って構成できます。 |
| パスワードの有効期限 (パスワードを無期限にします) | 既定値: **false** (パスワードの有効期限が指定されていることを示します)。各ユーザー アカウントの値を構成するには、[Update-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/update-mguser) コマンドレットを使用します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-password-ban-bad-on-premises"} -->
## Microsoft Entraパスワード保護 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-on-premises
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra Password Protection を使用して、オンプレミスの Active Directory Domain Services 環境で脆弱なパスワードを禁止する

Microsoft Entra Password Protection は、既知の脆弱なパスワードとそのバリエーションを検出してブロックし、組織に固有の弱い用語を追加でブロックすることもできます。 Microsoft Entra Password Protection のオンプレミス展開では、Microsoft Entra ID に格納されている同じグローバルおよびカスタムの禁止パスワード リストが使用され、Microsoft Entra ID がクラウドベースの変更に対して行うのと同じチェックをオンプレミスのパスワード変更に対して行います。 これらのチェックは、パスワードの変更時とパスワードのリセット イベント発生時にオンプレミスの Active Directory Domain Services (AD DS) ドメイン コントローラーに対して実行されます。

### 設計の原則

Microsoft Entra パスワード保護は、次の原則を念頭に置いて設計されています。

- ドメイン コントローラー (DC) は、インターネットと直接通信する必要はありません。
- DC で新しいネットワーク ポートは開かなくなります。
- AD DS スキーマの変更は必要ありません。 ソフトウェアは、既存の AD DS *コンテナー* と *serviceConnectionPoint* スキーマ オブジェクトを使用します。
- サポートされている AD DS ドメインまたはフォレストの機能レベルを使用できます。
- このソフトウェアは、保護する AD DS ドメイン内にアカウントを作成せず、要求もしません。
- ユーザーのクリア テキスト パスワードは、パスワード検証操作中またはその他のタイミングで、ドメイン コントローラーから離れることはありません。
- このソフトウェアは、他の Microsoft Entra 機能に依存していません。 たとえば、Microsoft Entra パスワード ハッシュ同期 (PHS) は、Microsoft Entra パスワード保護と関連性がなく、必須ではありません。
- 増分デプロイはサポートされていますが、ドメイン コントローラー エージェント (DC エージェント) がインストールされている場合にのみパスワード ポリシーが適用されます。

### 段階的デプロイ

Microsoft Entra Password Protection では、AD DS ドメイン内の DC 間での増分デプロイがサポートされています。 これが本当に何を意味し、トレードオフなのかを理解することが重要です。

Microsoft Entra Password Protection DC エージェント ソフトウェアは、DC にインストールされている場合にのみパスワードを検証でき、その DC に送信されるパスワードの変更に対してのみ検証できます。 ユーザー パスワードの変更を処理するために Windows クライアント コンピューターによって選択される DC を制御することはできません。 一貫性のある動作とユニバーサル Microsoft Entra Password Protection セキュリティの適用を保証するには、ドメイン内のすべての DC に DC エージェント ソフトウェアをインストールする必要があります。

多くの組織では、完全な展開の前に、DC のサブセットで Microsoft Entra Password Protection を慎重にテストしたいと考えています。 このシナリオをサポートするために、Microsoft Entra Password Protection では部分的な展開がサポートされています。 特定の DC 上の DC エージェント ソフトウェアは、ドメイン内の他の DC に DC エージェント ソフトウェアがインストールされていない場合でも、パスワードをアクティブに検証します。 この種類の部分的なデプロイはセキュリティで保護されておらず、テスト目的以外には推奨されません。

### アーキテクチャ図

オンプレミスの AD DS 環境に Microsoft Entra Password Protection を展開する前に、基になる設計と機能の概念を理解しておくことが重要です。 次の図は、Microsoft Entra Password Protection のコンポーネントがどのように連携するかを示しています。

[Image: Microsoft Entra パスワード保護コンポーネントの連携方法]

- Microsoft Entra パスワード保護プロキシ サービスは、現在の AD DS フォレスト内のドメインに参加している任意のマシンで実行されます。 サービスの主な目的は、DC からのパスワード ポリシーダウンロード要求を Microsoft Entra ID に転送し、Microsoft Entra ID からの応答を DC に返することです。
- DC エージェントのパスワード フィルター DLL は、オペレーティング システムからユーザーのパスワード検証要求を受け取ります。 フィルターによって、DC 上でローカルに実行されている DC エージェント サービスに転送されます。
- Microsoft Entra Password Protection の DC エージェント サービスは、DC エージェントのパスワード フィルター DLL からパスワード検証要求を受け取ります。 DC エージェント サービスは、現在の (ローカルで使用可能な) パスワード ポリシーを使用してそれらを処理し、 *成功* または失敗の結果を返 *します*。

### Microsoft Entra パスワード保護のしくみ

オンプレミスの Microsoft Entra パスワード保護コンポーネントは、次のように動作します。

1. 各 Microsoft Entra パスワード保護プロキシ サービス インスタンスは、Active Directory で *serviceConnectionPoint* オブジェクトを作成することで、フォレスト内の DC に自身をアドバタイズします。

    Microsoft Entra パスワード保護用の各 DC エージェント サービスは、Active Directory に *serviceConnectionPoint* オブジェクトも作成します。 このオブジェクトは、主にレポートと診断に使用されます。
2. DC エージェント サービスは、Microsoft Entra ID からの新しいパスワード ポリシーのダウンロードを開始する役割を担います。 最初の手順では、フォレストに対して proxy *serviceConnectionPoint* オブジェクトのクエリを実行して、Microsoft Entra パスワード保護プロキシ サービスを見つけます。
3. 使用可能なプロキシ サービスが見つかると、DC エージェントはパスワード ポリシーのダウンロード要求をプロキシ サービスに送信します。 プロキシ サービスは、要求を Microsoft Entra ID に送信し、DC エージェント サービスに応答を返します。
4. DC エージェント サービスが Microsoft Entra ID から新しいパスワード ポリシーを受け取った後、サービスはドメイン *sysvol* フォルダー共有のルートにある専用フォルダーにポリシーを格納します。 DC エージェント サービスは、新しいポリシーがドメイン内の他の DC エージェント サービスからレプリケートされる場合にも、このフォルダーを監視します。
5. DC エージェント サービスは、サービスの起動時に常に新しいポリシーを要求します。 DC エージェント サービスの起動後、1 時間ごとに現在のローカルで使用可能なポリシーの有効期間が確認されます。 ポリシーが 1 時間以上前の場合、DC エージェントは、前述のように、プロキシ サービス経由で Microsoft Entra ID に新しいポリシーを要求します。 現在のポリシーが 1 時間を超えない場合、DC エージェントはそのポリシーを引き続き使用します。
6. DC によってパスワード変更イベントが受信されると、キャッシュされたポリシーを使用して、新しいパスワードが受け入れられるか拒否されるかが判断されます。

#### 主な考慮事項と機能

- Microsoft Entra パスワード保護パスワード ポリシーがダウンロードされるたびに、そのポリシーはテナントに固有です。 つまり、パスワード ポリシーは常に、Microsoft グローバル禁止パスワード リストとテナントごとのカスタム禁止パスワード リストの組み合わせです。
- DC エージェントは、TCP 経由で RPC 経由でプロキシ サービスと通信します。 プロキシ サービスは、構成に応じて、動的または静的 RPC ポートでこれらの呼び出しをリッスンします。
- DC エージェントは、ネットワークで使用可能なポートではリッスンしません。
- プロキシ サービスは DC エージェント サービスを呼び出しません。
- プロキシ サービスはステートレスです。 Azure からダウンロードされたポリシーやその他の状態はキャッシュされません。
- プロキシの登録は、AADPasswordProtectionProxy サービス プリンシパルに資格情報を追加することによって機能します。 これが発生した場合でも、監査ログ内のイベントに心配しないでください。
- DC エージェント サービスでは、常に最新のローカルで使用可能なパスワード ポリシーを使用して、ユーザーのパスワードを評価します。 ローカル DC で使用できるパスワード ポリシーがない場合、パスワードは自動的に受け入れられます。 その場合、管理者に警告するイベント メッセージがログに記録されます。
- Microsoft Entra パスワード保護は、リアルタイム ポリシー アプリケーション エンジンではありません。 Microsoft Entra ID でパスワード ポリシーの構成変更が行われると、その変更がすべての DC に適用されるまでに遅延が発生する可能性があります。
- Microsoft Entra Password Protection は、代替ではなく、既存の AD DS パスワード ポリシーの補足として機能します。 これには、インストールされる可能性のある他のサード パーティ製パスワード フィルター dll が含まれます。 AD DS では、パスワードを受け入れる前にすべてのパスワード検証コンポーネントが同意する必要があります。

### Microsoft Entra パスワード保護のフォレストおよびテナントのバインディング

AD DS フォレストに Microsoft Entra Password Protection を展開するには、そのフォレストを Microsoft Entra ID に登録する必要があります。 デプロイされる各プロキシ サービスも、Microsoft Entra ID で登録する必要があります。 これらのフォレストとプロキシの登録は、特定の Microsoft Entra テナントに関連付けられます。これは、登録時に使用される資格情報によって暗黙的に識別されます。

AD DS フォレストと、フォレスト内にデプロイされたすべてのプロキシ サービスは、同じテナントに登録する必要があります。 AD DS フォレストまたはそのフォレスト内のプロキシ サービスを異なる Microsoft Entra テナントに登録することはサポートされていません。 このような誤って構成された展開の症状には、パスワード ポリシーをダウンロードできないことが含まれます。

注

したがって、複数の Microsoft Entra テナントを持つお客様は、Microsoft Entra パスワード保護の目的で各フォレストを登録するために、1 つの識別テナントを選択する必要があります。

### ダウンロード

Microsoft Entra Password Protection に必要な 2 つのエージェント インストーラーは、 [Microsoft ダウンロード センター](https://www.microsoft.com/download/details.aspx?id=57071)から入手できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-phone-providers"} -->
## SMS および音声認証用のテレフォニー プロバイダーを選択する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-phone-providers
- Service: entra-id / authentication
- Article date: 2026-09-23
- Summary: Microsoft Entra IDでの SMS および音声認証用に独自のテレフォニー プロバイダーを選択する方法について説明します。

Microsoft Entra IDは Choose Your Own テレフォニー プロバイダーをサポートします。これにより、組織はサポートされているテレフォニー プロバイダーで SMS または音声認証を引き続き使用できます。 Microsoft Security ストアからテレフォニー プロバイダーを選択し、組織のプロバイダー関係を管理します。

Microsoftでは、SMS や音声ではなく、[パスキー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)などのフィッシングに強い認証方法をお勧めします。 テレフォニー プロバイダーは、テレフォニー ベースの認証にビジネス、規制、または技術的な要件があるユーザー集団にのみ使用します。

Important

「独自のテレフォニー プロバイダーを選択」は、まだ構成できません。 プライベート プレビューに参加しているプロバイダーに関する情報が利用可能になりました。 構成エクスペリエンスは、2026 年 10 月 30 日以降に利用可能になります。

### 使用可能なテレフォニー プロバイダー

Soprano と Telesign は、プライベート プレビュー中に利用できる初期テレフォニー プロバイダーです。 一般提供により、より多くのプロバイダーが利用できるようになります。

利用可能なプロバイダー オファーの価格とその他の商用の詳細を確認します。

- ソプラノ: Microsoft Security ストアでの[ユーザー](https://securitystore.microsoft.com/solutions/sopranodesignlimited1620113206416.soprano_entraid_per_user)[ごとのオファーまたはトランザクションごとのオファー](https://securitystore.microsoft.com/solutions/sopranodesignlimited1620113206416.soprano_entraid_per_transaction)。
- Telesign: Microsoft Security Store の[Microsoft Entra 向け Telesign Verify](https://securitystore.microsoft.com/solutions/telesigncorporation1779799505747.telesign-verify-cyot-azure)。

### 独自のテレフォニー プロバイダーの選択のしくみ

選択したテレフォニー プロバイダーは、ユーザーが認証を完了するために必要な SMS メッセージまたは音声通話を配信します。 Microsoft Entra IDは引き続き認証方法ポリシーを適用し、認証エクスペリエンスを調整します。

組織は、テレフォニー プロバイダーの選択、地域のカバレッジと条件の確認、プロバイダー契約の完了、Microsoft Entra ID用のプロバイダーの構成、サービスの監視を担当します。

完全な移行スケジュールについては、[既定でのパスキーと、Microsoft提供される SMS および音声認証の廃止](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sms-voice-retirement)に関するページを参照してください。

### 独自のテレフォニー プロバイダー導入を計画する

プロバイダーを選択する前に、次の操作を行います。

- SMS または音声認証の要件が文書化されているユーザーを特定します。
- フィッシング対策の方法が要件を満たしていないことを確認します。
- 地理的範囲、サポートされている配信チャネル、価格、サポート、セキュリティ、コンプライアンス機能に基づいて、サポートされているテレフォニー プロバイダーを比較します。
- 調達、プライバシー、規制の要件を組織内の適切なチームで確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-registration-mfa-sspr-combined"} -->
## SSPR と Microsoft Entra 多要素認証のための統合された登録 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-registration-mfa-sspr-combined
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra 多要素認証とセルフサービス パスワード リセットの両方でユーザーの登録を実現する、Microsoft Entra ID の統合された登録エクスペリエンスについて説明します。

統合された登録の前、ユーザーは Microsoft Entra 多要素認証とセルフサービス パスワード リセット (SSPR) の認証方法を別々に登録しました。 ユーザーは 多要素認証 と セルフサービス パスワード リセット に同様の方法が使用されることに困惑しましたが、どちらのフィーチャーも登録する必要がありました。 現在では、統合された登録を使用することで、ユーザーは 1 回登録して 多要素認証 と セルフサービス パスワード リセット の両方のベネフィットを得ることができます。 「[Microsoft Entra ID で SSPR を有効にして構成する方法](https://www.youtube.com/watch?v=rA8TvhNcCvQ)」の動画をぜひご覧ください。

[Image: ユーザーの登録済みのセキュリティ情報を示しているマイ アカウント]

新しいエクスペリエンスを有効にする前に、この管理者対象のドキュメントとユーザー対象のドキュメントを確認して、この機能とその影響を確実に理解するようにしてください。 [ユーザー ドキュメント](https://support.microsoft.com/account-billing/set-up-your-security-info-from-a-sign-in-prompt-28180870-c256-4ebf-8bd7-5335571bf9a8)に基づいたトレーニングによってユーザーが新しいエクスペリエンスに対して準備できるようにし、ロールアウトの成功に役立ててください。

統合された登録は、米国政府機関向けの Azure と Azure のすべての顧客にロールアウトされます。 テナントが統合された登録に移行した後、レガシから統合された登録エクスペリエンスに切り替え可能なポータル コントロールが削除されます。

*[マイ アカウント]* ページは、そのページにアクセスしているコンピューターの言語設定に基づいてローカライズされます。 Microsoft は、ページへの以降のアクセスの試みが引き続き最後に使用された言語でレンダリングされるように、利用された最新の言語をブラウザー キャッシュに格納します。 キャッシュをクリアすると、ページは再レンダリングされます。

強制的に特定の言語にする場合は、URL の末尾に `?lng=<language>` を追加することができます。`<language>` は、レンダリングする言語のコードです。

[Image: SSPR またはその他のセキュリティ検証方法をセットアップする]

### 統合された登録で使用できる方法

統合された登録は、次表の認証方法とアクションをサポートしています。

| メソッド | Register | 変更 | 削除 |
| --- | --- | --- | --- |
| Microsoft Authenticator | はい (最大 5) | いいえ | あり |
| その他の認証アプリ | はい (最大 5) | いいえ | あり |
| ハードウェア トークン | いいえ | いいえ | あり |
| 電話番号 | はい (最大 2) | あり | あり |
| 代替電話 | あり | あり | あり |
| オフィス電話\* | あり | あり | あり |
| Email | あり | あり | あり |
| セキュリティの質問 | あり | いいえ | あり |
| パスワード | いいえ | あり | いいえ |
| アプリ パスワード\* | あり | いいえ | あり |
| パスキー (FIDO2)\* | はい (最大 10) | いいえ | あり |

Note

認証方法ポリシーで Microsoft Authenticator のパスワードレス認証モードを有効にする場合、Authenticator アプリでパスワードレス サインインも有効にする必要があります。

代替の電話は、*[セキュリティ情報]* において[管理モード](https://aka.ms/mysecurityinfo)でのみ登録できます。また、認証方法ポリシーで音声通話を有効にする必要があります。

ユーザーの*ビジネス電話* プロパティが設定されている場合にのみ、オフィス電話を*割り込みモード* で登録できます。 Office Phone は、この要件を満たしていなくても、*[管理モード]* でユーザーが [\[セキュリティ情報\]](https://aka.ms/mysecurityinfo) から追加できます。

アプリ パスワードは、ユーザーごとの MFA に適用されているユーザーのみが使用できます。 条件付きアクセス ポリシーによって Microsoft Entra 多要素認証が有効になっているユーザーはアプリ パスワードを使用できません。

パスキー (FIDO2) は、Microsoft Graph とのカスタム クライアントまたはパートナーの統合を使用してもプロビジョニングできます。 詳細については、[API](https://aka.ms/passkeyprovision) に関するページを参照してください。

ユーザーは、デフォルトの 多要素認証方法として、次のオプションのいずれかを設定できます。

- Microsoft Authenticator - プッシュ通知またはパスワードレス
- 認証アプリまたはハードウェア トークン – コード
- 音声通話
- テキスト メッセージ

Note

音声通話や SMS メッセージでは、仮想電話番号はサポートされていません。

サード パーティの認証アプリでは、プッシュ通知は提供されません。 Microsoft では引き続き Microsoft Entra ID により多くの認証方法を追加していくので、統合された登録で、それらの方法を使用できるようになります。

### 統合された登録のモード

統合された登録には次の 2 つのモードがあります。

- **中断モード**は、ウィザードに似たエクスペリエンスであり、ユーザーがサインイン時に自分のセキュリティ情報を登録または更新するときにユーザーに表示されます。
- **管理モード**は、ユーザーのプロファイルの一部であり、ユーザーが自分のセキュリティ情報を管理できるようにします。

どちらのモードでも、Microsoft Entra 多要素認証に使用できるメソッドを既に登録しているユーザーが自分のセキュリティ情報にアクセスするには、多要素認証を実行する必要があります。 ユーザーは、以前に登録したメソッドの使用を続ける前に、自分の情報を確認する必要があります。

#### 中断モード

統合された登録は、多要素認証 と セルフサービス パスワード リセット の両方のポリシーに準拠します (テナントで両方が有効になっている場合)。 これらのポリシーは、ユーザーがサインイン中に登録を中断されるかどうか、および登録にどの方法を使用できるかを制御します。 SSPR ポリシーのみが有効になっている場合、ユーザーは登録の中断をスキップ (無期限) し、後で完了することができます。

ユーザーが自分のセキュリティ情報を登録または更新するよう求められる可能性があるサンプル シナリオを次に示します。

- *Microsoft Entra ID 保護 によって多要素認証の登録が強制されている:* ユーザーは、サインイン中に登録するよう求められます。 ユーザーは 多要素認証方法と セルフサービス パスワード リセット方法を登録します (ユーザーが セルフサービス パスワード リセット に対して有効になっている場合)。
- *ユーザーごとの多要素認証によって多要素認証の登録が強制されている:* ユーザーは、サインイン中に登録するよう求められます。 ユーザーは 多要素認証方法と セルフサービス パスワード リセット方法を登録します (ユーザーが セルフサービス パスワード リセット に対して有効になっている場合)。
- *条件付きアクセス ポリシーまたはその他のポリシーによって多要素認証の登録が強制されている:* ユーザーは、多要素認証を必要とするリソースを使用する時点で登録するよう求められます。 ユーザーは 多要素認証方法と セルフサービス パスワード リセット方法を登録します (ユーザーが セルフサービス パスワード リセット に対して有効になっている場合)。
- *SSPR の登録が強制されている:* ユーザーは、サインイン中に登録するよう求められます。 ユーザーは SSPR 方法のみを登録します。
- *SSPR の更新が強制されている:* ユーザーは、管理者によって設定された間隔で自分のセキュリティ情報を確認する必要があります。ユーザーには自分の情報が表示され、現在の情報を確認するか、または必要に応じて変更を行うことができます。

登録が適用されると、ユーザーには、多要素認証と セルフサービス パスワード リセット の両方のポリシーに準拠するために必要な最小のメソッドが安全性の高い順に表示されます。 MFA と SSPR の両方の登録が適用され、SSPR ポリシーに 2 つの方法が必要な統合登録を行うユーザーは、最初に MFA メソッドを最初の方法として登録する必要があり、2 番目に登録された方法として別の MFA または SSPR 固有の方法 (電子メール、セキュリティの質問など) を選択できます。

次のシナリオ例について考えてみます。

- ユーザーが SSPR に対して有効になっています。 SSPR ポリシーでは、リセットするには 2 つの方法が必要であり、Microsoft Authenticator アプリ、電子メール、電話が有効になっています。
- ユーザーが登録することを選択する場合、次の 2 つの方法が必要とされます。
    - ユーザーには既定で、Microsoft Authenticator アプリと電話が表示されます。
    - ユーザーは、Authenticator アプリや電話の代わりに、メールを登録することを選択できます。

Microsoft Authenticator を設定すると、ユーザーは **[別の方法を設定する]** を選択して他の認証方法を登録できます。 使用可能な方法の一覧は、テナントの認証方法ポリシーによって決まります。

[Image: Microsoft Authenticator を設定するときに別の方法を選択する方法のスクリーンショット。]

次のフローチャートは、サインイン中に登録を中断されたときにユーザーにどの方法が表示されるかを示しています。

[Image: 結合されたセキュリティ情報のフローチャート]

多要素認証 と セルフサービス パスワード リセット の両方が有効になっている場合は、多要素認証の登録を適用することをお勧めします。

SSPR ポリシーでユーザーが定期的に自分のセキュリティ情報を確認する必要がある場合、ユーザーはサインイン中に中断され、自分が登録したすべての方法が表示されます。 ユーザーは、現在の情報が最新かどうかを確認することも、必要な場合は変更することもできます。 このページにアクセスするには、ユーザーが多要素認証を実行する必要があります。

#### 管理モード

ユーザーは、[\[セキュリティ情報\]](https://aka.ms/mysecurityinfo) に移動するか、[マイ アカウント] から **[セキュリティ情報]** を選択できます。 そこから、ユーザーは方法の追加、既存の方法の削除または変更、既定の方法の変更などを実行できます。

#### 統合された登録のセッション制御

既定では、統合された登録では、セキュリティ情報を登録または管理する前に、すべての MFA 対応ユーザーが厳密に認証されるように強制されます。

- パスキー (FIDO2) 認証方法を追加または変更するには、ユーザーが過去 5 分以内に強力な認証を完了している必要があります。 過去 5 分間に MFA が完了していない場合、ユーザーはサインインして新しい MFA を完了するように求められます。
- MC1135479 で発表されたように、2025 年 8 月 25 日から、これが実施される時点で、現在のセッションが始まってから 10 分以内に行っていない場合、ユーザーは資格情報の管理や My Sign-ins へのアクセス時に、多要素認証（MFA）を完了する必要があります。 セキュリティ情報の登録に対して認証の強度を適用すると、これらの両方の要件と競合する可能性があります。 ユーザーに *"Let's try 何か他のものを試してみましょう" というエラー メッセージが表示される場合があります。このリソースにアクセスするには、別のサインイン方法が必要です。ブラウザーを閉じてもう一度やり直しますが、別のサインイン方法を選択してください"*。

テナント レベルまたはユーザー レベルで変更を行うことができます。

- テナント レベルで、**サインイン頻度: 毎回**を**セキュリティ情報の登録**ユーザーアクションに適用するか、Windows Hello for Business ユーザーのためにパスキーを有効にします。
- ユーザー レベルでは、ユーザーが 10 分以内のセッションで認証するか、強制された認証強度に含まれる方法の組み合わせで認証されるようにします。 組織は、 [セキュリティ情報の登録をセキュリティで保護するための条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-security-info-registration)を定義することで、認証要件を変更できます。

### 主な使用シナリオ

#### MySignIns でパスワードを変更する

ユーザーが [\[セキュリティ情報\]](https://aka.ms/mysecurityinfo) に移動します。 サインインした後、ユーザーは自分のパスワードを変更できます。 ユーザーがパスワードと多要素認証方法を使用して認証を行う場合、既存のパスワードを入力せずに、強化されたユーザー エクスペリエンスを使用してパスワードを変更できます。 完了すると、ユーザーの [セキュリティ情報] ページのパスワードが新しいパスワードに更新されます。 一時アクセス パス (TAP) などの認証方法の場合、ユーザーが既存のパスワードを知らない限り、パスワードを変更できません。

Note

従来のパスワード変更エクスペリエンスを指すリンクがある場合は、次の転送リンクに更新して、新しい**マイ サインイン パスワード変更**エクスペリエンス (https://go.microsoft.com/fwlink/?linkid=2224198) にユーザーを誘導します。

#### 条件付きアクセスを使用したセキュリティ情報の登録の保護

ユーザーが Microsoft Entra 多要素認証とセルフサービス パスワード リセットの登録を実行するタイミングと方法をセキュリティで保護するため、条件付きアクセス ポリシーのユーザー アクションを使用できます。 この機能では、HR オンボード中に信頼できるネットワークの場所などの一元化された場所から Microsoft Entra 多要素認証と SSPR の登録をユーザーに行わせたい組織で有効にすることができます。 [セキュリティ情報の登録をセキュリティで保護するための一般的な条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-security-info-registration)を構成する方法の詳細を確認します。

#### サインイン中にセキュリティ情報を設定する

管理者が登録を強制しました。

ユーザーが必要なすべてのセキュリティ情報を設定していない場合は、Microsoft Entra 管理センターに移動します。 ユーザー名とパスワードをユーザーが入力すると、ユーザーはセキュリティ情報を設定するよう求められます。 ユーザーは次に、ウィザードに示されている手順に従って、必要なセキュリティ情報を設定します。 ユーザーは、設定によって許可される場合、既定で表示されるもの以外の方法を設定することを選択できます。 ウィザードが完了した後、ユーザーは、設定したメソッドと多要素認証の既定のメソッドを確認します。 設定プロセスを完了するために、ユーザーは情報を確認し、Microsoft Entra 管理センターに進みます。

#### [マイ アカウント] からセキュリティ情報を設定する

管理者は登録を強制していません。

必要なすべてのセキュリティ情報をまだ設定していないユーザーが https://myaccount.microsoft.com に移動します。 ユーザーは左側のウィンドウで **[セキュリティ情報]** を選択します。 そこから、ユーザーは方法を追加することを選択し、自分が使用できるいずれかの方法を選択した後、手順に従ってその方法を設定します。 完了すると、設定された方法が [セキュリティ情報] ページに表示されます。

#### 部分的な登録後に他のメソッドを設定する

ユーザーまたは管理者によって実行された既存の認証方法の登録により、ユーザーが MFA または SSPR の登録を部分的に満たしている場合、登録が必要な場合にのみ、認証方法ポリシー設定で許可される追加情報の登録がユーザーに求められます。 ユーザーが他の複数の認証方法を選択して登録できる場合は、**別の方法を設定する** というタイトルの登録エクスペリエンスのオプションが表示され、ユーザーは必要な認証方法を設定できます。

[Image: 別のメソッドを設定する方法のスクリーンショット。]

#### [マイ アカウント] からセキュリティ情報を削除する

少なくとも 1 つの方法を以前に設定しているユーザーが [\[セキュリティ情報\]](https://aka.ms/mysecurityinfo) に移動します。 ユーザーは、以前に登録された方法のいずれかを削除することを選択します。 完了すると、その方法は [セキュリティ情報] ページに表示されなくなります。

#### [マイ アカウント] から既定の方法を変更する

多要素認証に使用できる少なくとも 1 つのメソッドを以前に設定しているユーザーが [\[セキュリティ情報\]](https://aka.ms/mysecurityinfo) に移動します。 ユーザーは、現在の既定の方法を別の既定の方法に変更します。 完了すると、新しい既定の方法が [セキュリティ情報] ページに表示されます。

#### ディレクトリを切り替える

B2B ユーザーなどの外部 ID は、サードパーティ テナントのセキュリティ登録情報を変更するために、ディレクトリを切り替える必要がある場合があります。 さらに、リソース テナントにアクセスするユーザーは、ホーム テナントの設定を変更したときに混乱する可能性がありますが、リソース テナントの表示には変更が反映されません。

たとえば、ユーザーが Microsoft Authenticator アプリのプッシュ通知を、ホーム テナントにサインインするためのプライマリ認証として設定し、別のオプションとして SMS/テキスト オプションも使用しているとします。 このユーザーが、リソース テナントでも SMS/テキスト オプションを使用するよう構成しています。 このユーザーがホーム テナントで、認証オプションの 1 つとしての SMS/テキストを削除した場合、リソース テナントにアクセスした際に SMS/テキスト メッセージに応答するよう求められて混乱します。

Microsoft Entra 管理センターでディレクトリを切り替えるには、右上隅にあるユーザー アカウント名を選択し、**ディレクトリの切り替え**を選択します。

[Image: 外部ユーザーはディレクトリを切り替えることができます。]

または、セキュリティ情報にアクセスするための URL でテナントを指定することもできます。

`https://mysignins.microsoft.com/security-info?tenant=<Tenant Name>`

`https://mysignins.microsoft.com/security-info/?tenantId=<Tenant ID>`

Note

統合された登録または [自分のサインイン] ページを使用してセキュリティ情報を登録または管理しようとしているお客様は、Microsoft Edge などの最新のブラウザーを使用する必要があります。

IE11 は、アプリケーションで Web ビューまたはブラウザーを作成するために正式にはサポートされていません。これは、すべてのシナリオで想定どおりに動作するわけではありません。

レガシ Web ビューに依存する Azure AD 認証ライブラリ (ADAL) をまだ使用しているアプリケーションは、古いバージョンの Internet Explorer にフォールバックできます。 これらのシナリオでは、ユーザーが [マイ サインイン] ページに移動すると、空白のページが表示されます。 この問題を解決するには、最新のブラウザーに切り替えます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-resilient-controls"} -->
## 回復性があるアクセス制御管理戦略を作成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-resilient-controls
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: このドキュメントでは、予期されていない中断の間のロックアウトのリスクを軽減する回復性を提供するために、組織で採用する必要がある戦略に関するガイダンスを示します

Note

このドキュメントに含まれる情報は、取り上げている問題についての、公開時点における Microsoft Corporation の見解を表します。 Microsoft は変化する市場状況に対応する必要があるため、これを Microsoft によるコミットメントと解釈してはなりません。また、Microsoft は、記載されている情報の公開後の正確性を保証できません。

多要素認証や 1 つのネットワークの場所などの単一のアクセス制御に依存して IT システムをセキュリティ保護している組織では、その単一のアクセス制御が使用不能になったり誤って構成された場合に、アプリやリソースへのアクセス障害の影響を受けやすくなります。 たとえば、自然災害によって、広範囲の通信インフラストラクチャや企業ネットワークを使用できなくなる可能性があります。 このようなが中断のため、エンド ユーザーや管理者がサインインできなくなることがあります。

このドキュメントでは、次のようなシナリオで、予期されていない中断の間のロックアウトのリスクを軽減する回復性を提供するために、組織で採用する必要がある戦略に関するガイダンスを示します。

- 組織では、リスク軽減戦略またはコンティンジェンシー計画を実装することで、**中断の前に**ロックアウトのリスクを軽減する回復性を向上させることができます。
- 組織では、リスク軽減戦略とコンティンジェンシー計画を導入することで、**中断の間に**選択したアプリとリソースへのアクセスを続けることができます。
- 組織では、**中断の後**、実装されているコンティンジェンシーをロールバックする前に、ログなどの情報が保持されるようにする必要があります。
- 防止戦略や代替計画を実装していない組織でも、中断に対処する**緊急オプション**を実装できる場合があります。

### 重要なガイダンス

このドキュメントで重要なことは次の 4 つです。

- 緊急アクセス用アカウントを使用して、管理者のロックアウトを回避する。
- ユーザーごとの MFA ではなく条件付きアクセスを使用して、MFA を実装する。
- 複数の条件付きアクセス制御を使用して、ユーザーのロックアウトを軽減する。
- 複数の認証方法または同等の手段をユーザーごとにプロビジョニングして、ユーザーのロックアウトを軽減する。

### 中断する前

発生する可能性があるアクセス制御の問題への対応において、組織が主眼にする必要があるのは、実際の中断を軽減することです。 軽減策には、実際のイベントに対する計画に加えて、アクセス制御と運用が中断の間に影響を受けないようにする戦略の実装が含まれます。

#### 回復性があるアクセス制御が必要な理由

ID は、アプリとリソースにアクセスするユーザーのコントロール プレーンです。 ID システムでは、どのユーザーが、どのような条件の下で (アクセス制御や認証の要件など)、アプリケーションにアクセスできるかが制御されます。 不測の事態のため、ユーザーの認証に対して 1 つ以上の認証要件またはアクセス制御要件を使用できないと、次の問題の一方または両方が組織に発生する可能性があります。

- **管理者のロックアウト:** 管理者は、テナントまたはサービスを管理できません。
- **ユーザーのロックアウト:** ユーザーは、アプリまたはリソースにアクセスできません。

#### 管理者ロックアウトのコンティンジェンシー

Microsoft は、組織が [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) ロールが永続的に割り当てられる 2 つのクラウド専用の緊急アクセス アカウントを作成することを推奨しています。 このようなアカウントは高い特権を持っており、特定のユーザーには割り当てられません。 これらのアカウントは、通常のアカウントを使用できない、または他のすべての管理者が誤ってロックアウトされたという緊急または "ブレーク グラス" のシナリオに限定されます。これらのアカウントは、[緊急アクセス アカウントに関するレコメンデーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)に従って作成する必要があります。

#### ユーザー ロックアウトの軽減

ユーザー ロックアウトのリスクを緩和するには、アプリやリソースへのアクセス方法をユーザーが選択できる、複数の制御を備えた条件付きアクセス ポリシーを使用します。 たとえば、MFA でのサインイン、**または**、マネージド デバイスからのサインイン、**または**、企業ネットワークからのサインインをユーザーが選択できるようにすることで、いずれかのアクセス制御を利用できない場合でも、ユーザーには作業を続けるための他のオプションがあります。

##### Microsoft のレコメンデーション

組織の既存の条件付きアクセス ポリシーに次のアクセス制御を組み込みます。

- さまざまな通信チャネルに依存するユーザーごとに、複数の認証方法をプロビジョニングします。たとえば、Microsoft Authenticator アプリ (インターネット ベース)、OATH トークン (デバイス上で生成)、SMS (電話) が挙げられます。
- Windows 10 デバイスに Windows Hello for Business を展開し、デバイスのサインインから直接 MFA 要件を満たします。
- [Microsoft Entra ハイブリッド参加](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview) または [Microsoft Intune](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/intune-planning-guide) を介して信頼できるデバイスを使用します。 信頼できるデバイスを使用すると、信頼できるデバイス自体でポリシーの強力な認証要件を満たすことができ、ユーザーに MFA チャレンジを行う必要がないので、ユーザー エクスペリエンスが向上します。 その場合、新しいデバイスを登録するとき、および信頼されていないデバイスからアプリやリソースにアクセスするときに、MFA が必要になります。
- 固定の MFA ポリシーの代わりに、ユーザーまたはサインインにリスクがあるときにアクセスを禁止する Microsoft Entra ID Protection のリスクに基づくポリシーを使用します。
- Microsoft Entra 多要素認証 NPS 拡張機能を使用して VPN アクセスを保護している場合、VPN ソリューションを [SAML アプリ](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/view-applications-portal) としてフェデレーションすることを検討し、以下に推奨されるようにアプリのカテゴリを決定します。

Note

リスクに基づくポリシーには、[Microsoft Entra ID P2](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing) ライセンスが必要です。

次の例では、アプリやリソースにアクセスするユーザーに対して回復性があるアクセス制御を提供するために作成する必要があるポリシーについて説明します。 この例では、アクセスを許可する対象のユーザーを含むセキュリティ グループ **AppUsers**、コア管理者を含むグループ **CoreAdmins**、緊急アクセス用アカウントを含むグループ **EmergencyAccess** が必要です。 この例のポリシー セットでは、**AppUsers** で選択されているユーザーが、信頼できるデバイスから接続している場合、または MFA などの強力な認証を提供する場合に、選択されたアプリへのアクセスをユーザーに許可します。 緊急アカウントとコア管理者は除外されます。

**条件付きアクセス緩和ポリシーの設定:**

- ポリシー 1:ターゲット グループ外のユーザーにアクセスをブロックする
    - ユーザーとグループ:すべてのユーザーを含める。 AppUsers、CoreAdmins、EmergencyAccess を除外する
    - クラウド アプリ:すべてのアプリを含める
    - 条件:(なし)
    - 許可の制御:ブロック
- ポリシー 2:MFA または信頼済みデバイスを要求して、AppUsers にアクセスを許可する。
    - ユーザーとグループ:AppUsers を含める。 CoreAdmins、EmergencyAccess を除外する
    - クラウド アプリ:すべてのアプリを含める
    - 条件:(なし)
    - 許可の制御: アクセスを許可する、多要素認証が必要、デバイスの準拠が必要。 複数の制御の場合:選択した制御のいずれかが必要。

#### ユーザー ロックアウトのコンティンジェンシー

または、組織でコンティンジェンシー ポリシーを作成することもできます。 コンティンジェンシー ポリシーを作成するには、ビジネス継続性、運用コスト、財務コスト、セキュリティ リスクの間のトレードオフ条件を定義する必要があります。 たとえば、ユーザーのサブセット、アプリのサブセット、クライアントのサブセット、または場所のサブセットに対してのみ、コンティンジェンシー ポリシーをアクティブにする場合があります。 コンティンジェンシー ポリシーでは、緩和策が実装されていない場合の中断時に、管理者とエンド ユーザーに対して、アプリとリソースへのアクセスが許可されます。 Microsoft では、コンティンジェンシー ポリシーを使用しない場合は[レポート専用モード](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-insights-reporting)で有効にしておき、ポリシーをオンにすることが必要になった場合に備えて、管理者がそれらの潜在的な影響を監視できるようにすることをお勧めしています。

中断中の危険な状態を理解することは、リスクを軽減するのに役立ち、計画プロセスの重要な一部です。 コンティンジェンシー計画を作成するには、まず、組織での次のビジネス要件を決定します。

1. 事前にミッション クリティカルなアプリを決定します。低いリスク/セキュリティ体制であっても、アクセス権を付与する必要があるアプリは何ですか。 そのようなアプリの一覧を作成し、すべてのアクセス制御が失われた場合であってもそれらのアプリの実行を継続する必要があることを、他の利害関係者 (ビジネス、セキュリティ、法律、リーダーシップ) が全員同意していることを確認します。 最終的に次のカテゴリに該当する可能性があります。
    - **カテゴリ 1: ミッション クリティカルなアプリ**: 数分より長く使用不可能になることはできません。たとえば、組織の収益に直接影響するアプリなどです。
    - **カテゴリ 2: 重要なアプリ**: 数時間以内にアクセス可能になる必要があります。
    - **カテゴリ 3: 優先順位の低いアプリ**: 数日間停止しても許容されます。
2. カテゴリ 1 と 2 のアプリについては、許可するアクセス レベルの種類を事前に計画することを推奨します。
    - フル アクセスまたは制限されたセッション (ダウンロードの制限など) を許可しますか。
    - アプリ全体へのアクセスは許可せず、アプリの一部へのアクセスを許可しますか。
    - アクセス制御が復元されるまで、情報ワーカーのアクセスを許可し、管理者のアクセスをブロックしますか。
3. このようなアプリについては、Microsoft では、意図的に開くアクセス手段と閉じるアクセス手段を計画することも推奨しています。
    - ブラウザーのみのアクセスは許可し、オフライン データを保存できるリッチ クライアントはブロックしますか。
    - 企業ネットワーク内のユーザーに対してのみアクセスを許可し、外部のユーザーはブロックしますか。
    - 中断中は、特定の国または地域からのアクセスのみを許可しますか。
    - 代わりのアクセス制御が利用できない場合、特にミッション クリティカルなアプリの場合に、コンティンジェンシー ポリシーに対するポリシーを失敗させますか、それとも成功させますか?

##### Microsoft のレコメンデーション

コンティンジェンシー条件付きアクセス ポリシーは、Microsoft Entra 多要素認証、サードパーティ MFA、リスクベースまたはデバイスベースの制御を省略する **バックアップ ポリシー** です。 コンティンジェンシー ポリシーが有効になっている場合の予期しない中断を最小限に抑えるために、ポリシーを使用しないときはレポート専用モードのままにしておく必要があります。 管理者は、条件付きアクセスに関する分析情報のブックを使用して、コンティンジェンシー ポリシーの潜在的な影響を監視できます。 組織でコンティンジェンシー計画の有効化が決定されたときに、管理者はそのポリシーを有効にし、通常の制御に基づくポリシーを無効にできます。

重要

ユーザーにセキュリティを強制するポリシーを無効にすると、一時的であっても、コンティンジェンシー計画が実施されている間は、セキュリティ体制が低下します。

- 1 つの資格情報の種類または 1 つのアクセス制御メカニズムの中断によってアプリへのアクセスが影響を受ける場合は、フォールバック ポリシーのセットを構成します。 サードパーティの MFA プロバイダーを必要とするアクティブなポリシーに対するバックアップとして、制御としてドメイン参加を必要とするポリシーをレポート専用状態で構成します。
- [パスワードのガイダンス](https://aka.ms/passwordguidance) に関するホワイト ペーパーに記載の方法に従って、MFA が必要ないときに、パスワードを推測する悪意のあるユーザーのリスクを緩和します。
- [Microsoft Entra セルフサービス パスワード リセット (SSPR)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr) および [Microsoft Entra パスワード保護](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-deploy) をデプロイし、ユーザーが禁止する一般的なパスワードや用語を使用しないようにします。
- 単にフル アクセスにフォールバックするのではなく、特定の認証レベルが満たされていない場合、アプリ内でアクセスを制限するポリシーを使用します。 例:
    - Exchange および SharePoint に制限されたセッション要求を送信するバックアップ ポリシーを構成します。
    - 組織で Microsoft Defender for Cloud Apps を使用している場合、Defender for Cloud Apps を使用するポリシーにフォールバックし、読み取り専用アクセスは許可するが、アップロードは許可しないことを検討してください。
- 中断中にポリシーを簡単に見つけられるよう、ポリシーに名前を付けます。 ポリシー名には次の要素を含めます。
    - ポリシーの*ラベル番号*。
    - 表示するテキスト。このポリシーは緊急時のみを対象としています。 次に例を示します。**ENABLE IN EMERGENCY**
    - 適用される*中断*。 次に例を示します。**During MFA Disruption**
    - ポリシーをアクティブ化する順序を示す、*シーケンス番号*。
    - 適用される*アプリ*。
    - 適用される*コントロール*。
    - 必要な*条件*。

コンティンジェンシー ポリシーの場合、この命名基準は次のようになります。

```
EMnnn - ENABLE IN EMERGENCY: [Disruption][i/n] - [Apps] - [Controls] [Conditions]
```

次に例を示します。**例 A - ミッション クリティカルなコラボレーション アプリへのアクセスを復元するコンティンジェンシー条件付きアクセス ポリシー**は、企業の一般的なコンティンジェンシーです。 このシナリオの組織では、一般に、すべての Exchange Online と SharePoint Online のアクセスには MFA が必要であり、このケースでの中断は、顧客の MFA プロバイダーが停止状態にあることです (Microsoft Entra 多要素認証、オンプレミス MFA プロバイダー、サード パーティ MFA を問いません)。 このポリシーでは、信頼できる Windows デバイスからアプリへの、特定の対象ユーザーのアクセスを、信頼できる企業ネットワークからアプリにアクセスしている場合にのみ許可することによって、この停止状態を緩和します。 また、緊急アカウントとコア管理者はこれらの制限から除外されます。 これにより、対象ユーザーは Exchange Online と SharePoint Online へのアクセスが許可されますが、その他のユーザーは障害のためにアプリにまだアクセスできません。 この例では、名前付きのネットワークの場所 **CorpNetwork**、対象ユーザーを含むセキュリティ グループ **ContingencyAccess**、コア管理者を含むグループ **CoreAdmins**、緊急アクセス用アカウントを含むグループ **EmergencyAccess** が必要です。 コンティンジェンシーでは、必要なアクセスを提供するために 4 つのポリシーが必要です。

**例 A - ミッションクリティカルなコラボレーション アプリへのアクセスを復元するコンティンジェンシー条件付きアクセス ポリシー:**

- ポリシー 1:Exchange と SharePoint に対するドメイン参加済みデバイスが必要
    - 名前: EM001 - 緊急時に有効: MFA 中断 [1/4] - Exchange SharePoint - Microsoft Entra ハイブリッド参加が必要
    - ユーザーとグループ:ContingencyAccess を含める。 CoreAdmins、EmergencyAccess を除外する
    - クラウド アプリ:Exchange Online と SharePoint Online
    - 条件:Any
    - 許可の制御:ドメインへの参加が必要
    - 状態:レポート専用
- ポリシー 2:Windows 以外のプラットフォームをブロック
    - 名前:EM002 - ENABLE IN EMERGENCY:MFA Disruption[2/4] - Exchange SharePoint - Block access except Windows
    - ユーザーとグループ:すべてのユーザーを含める。 CoreAdmins、EmergencyAccess を除外する
    - クラウド アプリ:Exchange Online と SharePoint Online
    - 条件:デバイス プラットフォームは、Windows 以外のすべてのプラットフォームを含む
    - 許可の制御:ブロック
    - 状態:レポート専用
- ポリシー 3:CorpNetwork 以外のネットワークをブロック
    - 名前:EM003 - ENABLE IN EMERGENCY:MFA Disruption[3/4] - Exchange SharePoint - Block access except Corporate Network
    - ユーザーとグループ:すべてのユーザーを含める。 CoreAdmins、EmergencyAccess を除外する
    - クラウド アプリ:Exchange Online と SharePoint Online
    - 条件:場所は、CorpNetwork 以外のすべての場所を含む
    - 許可の制御:ブロック
    - 状態:レポート専用
- ポリシー 4:EAS を明示的にブロックする
    - 名前:EM004 - ENABLE IN EMERGENCY:MFA Disruption[4/4] - Exchange - Block EAS for all users
    - ユーザーとグループ:すべてのユーザーを含める
    - クラウド アプリ:Exchange Online を含める
    - 条件:クライアント アプリ:Exchange Active Sync
    - 許可の制御:ブロック
    - 状態:レポート専用

アクティブ化の順序:

1. 既存の MFA ポリシーから ContingencyAccess、CoreAdmins、EmergencyAccess を除外します。 ContingencyAccess に含まれるユーザーが SharePoint Online と Exchange Online にアクセスできることを確認します。
2. ポリシー 1 を有効化: 除外グループに含まれないドメイン参加済みデバイスのユーザーが、Exchange Online と SharePoint Online にアクセスできることを確認します。 除外グループに含まれるユーザーが、任意のデバイスから SharePoint Online と Exchange にアクセスできることを確認します。
3. ポリシー 2 を有効化: 除外グループに含まれないユーザーが、自分のモバイル デバイスから SharePoint Online と Exchange Online にアクセスできないことを確認します。 除外グループに含まれるユーザーが、任意のデバイス (Windows/iOS/Android) から SharePoint と Exchange にアクセスできることを確認します。
4. ポリシー 3 を有効化: 除外グループに含まれないユーザーが、 ドメイン参加済みコンピューターであっても、企業ネットワーク外から SharePoint および Exchange にアクセスできないことを確認します。 除外グループに含まれるユーザーが、任意のネットワークから SharePoint と Exchange にアクセスできることを確認します。
5. ポリシー 4 を有効化: すべてのユーザーが、モバイル デバイス上のネイティブ メール アプリケーションから Exchange Online にアクセスできないことを確認します。
6. SharePoint Online と Exchange Online に対する既存の MFA ポリシーを無効にします。

次の例、**例 B - Salesforce へのモバイル アクセスを許可するコンティンジェンシー条件付きアクセス ポリシー**では、ビジネス アプリのアクセスを復元します。 このシナリオの顧客は、通常、営業社員に対し、準拠しているモバイル デバイスから (Microsoft Entra ID でシングルサイン オンを構成された) Salesforce へのアクセスのみを許可する必要があります。 このケースでの中断は、デバイスのコンプライアンスの評価に関する問題が発生しているためであり、営業チームが取引成立のために Salesforce にアクセスする必要がある時間的制約のある場面で障害が発生しています。 これらのコンティンジェンシー ポリシーでは、取引成立の流れを途切れさせず、ビジネスが中断しないよう、モバイル デバイスから Salesforce へのクリティカルなユーザー アクセスが許可されます。 この例では、**SalesforceContingency** にアクセスを維持する必要があるすべての営業社員が含まれ、**SalesAdmins** に Salesforce の必要な管理者が含まれます。

**例 B - コンティンジェンシー条件付きアクセス ポリシー:**

- ポリシー 1:SalesContingency チームに含まれないすべてのユーザーをブロックする
    - 名前:EM001 - ENABLE IN EMERGENCY:Device Compliance Disruption[1/2] - Salesforce - Block All users except SalesforceContingency
    - ユーザーとグループ:すべてのユーザーを含める。 SalesAdmins と SalesforceContingency を除外する
    - クラウド アプリ:Salesforce。
    - 条件:なし
    - 許可の制御:ブロック
    - 状態:レポート専用
- ポリシー 2:(攻撃対象領域を減らすため) モバイル以外の任意のプラットフォームからのセールス チームをブロックする
    - 名前:EM002 - ENABLE IN EMERGENCY:Device Compliance Disruption[2/2] - Salesforce - Block All platforms except iOS and Android
    - ユーザーとグループ:SalesforceContingency を含める。 SalesAdmins を除外する
    - クラウド アプリ:Salesforce
    - 条件:デバイス プラットフォームは、iOS と Android 以外のすべてのプラットフォームを含む
    - 許可の制御:ブロック
    - 状態:レポート専用

アクティブ化の順序:

1. Salesforce に対する既存のデバイス コンプライアンス ポリシーから SalesAdmins と SalesforceContingency を除外します。 SalesforceContingency グループに含まれるユーザーが Salesforce にアクセスできることを確認します。
2. ポリシー 1 を有効化: SalesContingency に含まれないユーザーが Salesforce にアクセスできないことを確認します。 SalesAdmins と SalesforceContingency に含まれるユーザーが Salesforce にアクセスできることを確認します。
3. ポリシー 2 を有効化: SalesContingency グループに含まれるユーザーが、Windows/Mac のラップトップからは Salesforce にアクセスできないが、モバイル デバイスからはアクセスできることを確認します。 SalesAdmin が任意のデバイスから Salesforce にアクセスできることを確認します。
4. Salesforce に対する既存のデバイス コンプライアンス ポリシーを無効にします。

#### オンプレミス リソースからのユーザー ロックアウト用のコンティンジェンシー (NPS 拡張機能)

Microsoft Entra 多要素認証 NPS 拡張機能を使用して VPN アクセスを保護している場合、VPN ソリューションを [SAML アプリ](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/view-applications-portal) としてフェデレーションすることを検討し、以下に推奨されるようにアプリのカテゴリを決定します。

VPN やリモート デスクトップ ゲートウェイなどのオンプレミス リソースを MFA を使用して保護するために Microsoft Entra 多要素認証 NPS 拡張機能をデプロイしている場合は、緊急時に MFA を無効にする準備ができているかどうかを事前に検討する必要があります。

このケースでは、NPS 拡張機能を無効にすることができ、その結果、NPS サーバーではプライマリ認証のみが検証され、ユーザーに MFA は適用されません。

NPS 拡張機能を無効にする:

- HKEY\_LOCAL\_MACHINE \SYSTEM\CurrentControlSet\Services\AuthSrv\Parameters レジストリ キーをバックアップとしてエクスポートします。
- Parameters キーではなく、"AuthorizationDLLs" と "ExtensionDLLs" のレジストリ値を削除します。
- ネットワーク ポリシー サービス (IAS) を再起動して変更を有効にします。
- VPN のプライマリ認証が成功するかどうかを確認します。

サービスが回復し、ユーザーに再び MFA を適用する準備が整えば、NPS 拡張機能を有効にします。

- バックアップの HKEY\_LOCAL\_MACHINE \SYSTEM\CurrentControlSet\Services\AuthSrv\Parameters からレジストリ キーをインポートします。
- ネットワーク ポリシー サービス (IAS) を再起動して変更を有効にします。
- VPN のプライマリ認証とセカンダリ認証が正常に実行されるかどうかを確認します。
- NPS サーバーと VPN ログを見直して、緊急期間中にサインインしたユーザーを特定します。

#### フェデレーションされている場合、またはパススルー認証を使用している場合でも、パスワード ハッシュ同期をデプロイする

ユーザーのロックアウトは、次の条件が満たされる場合にも発生します。

- 組織で、パススルー認証またはフェデレーションによるハイブリッド ID ソリューションが使用されている。
- オンプレミスの ID システム (Active Directory、AD FS、依存コンポーネントなど) を使用できない。

回復性を向上させるには、組織で[パスワード ハッシュ同期を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/choose-ad-authn)必要があります。そうすれば、オンプレミスの ID システムがダウンした場合は、[パスワード ハッシュ同期の使用に切り替える](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-user-signin)ことができます。

##### Microsoft のレコメンデーション

組織でフェデレーションまたはパススルー認証が使用されているかどうかにかかわらず、Microsoft Entra Connect ウィザードを使用してパスワード ハッシュ同期を有効にします。

重要

パスワード ハッシュ同期を使用するために、ユーザーをフェデレーション認証からマネージド認証に変換する必要はありません。

### 中断の間

緩和プランを実装することを選択した場合、単一のアクセス制御の中断を自動的に回避することができます。 一方、コンティンジェンシー プランを作成することを選択した場合は、アクセス制御の中断中、コンティンジェンシー ポリシーをアクティブ化することができます。

1. 対象ユーザーに対して特定ネットワークから特定アプリへのアクセスを許可するコンティンジェンシー ポリシーを有効にします。
2. 通常の制御に基づくポリシーを無効にします。

#### Microsoft のレコメンデーション

中断の間に軽減とコンティンジェンシーのどちらを使用するかによっては、組織でパスワードだけによるアクセスが許可される可能性があります。 保護がないことは、慎重に検討すべき重大なセキュリティ リスクです。 組織では次のようにする必要があります。

1. 変更管理戦略の一環として、アクセス制御が完全に動作するようになったら直ちに、実装したコンティンジェンシーをロールバックできるように、すべての変更と以前の状態を文書化します。
2. MFA を無効にしている間は、悪意のあるユーザーがパスワード スプレーやフィッシング攻撃を使用してパスワードを取得しようとするものと想定します。 また、悪意のあるユーザーが、以前はどのリソースにもアクセスが許可されなかったパスワードを既に入手していて、この期間にアクセスを試みるかもしれません。 管理職などの重要なユーザーについては、MFA を無効にする前に、それらのユーザーのパスワードをリセットすることで、このリスクを軽減することができます。
3. すべてのサインイン アクティビティをアーカイブし、MFA が無効にされていた期間に誰がアクセスしたかを識別します。
4. この期間中に[報告されたすべてのリスク検出をトリアージ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)します。

### 中断の後

中断の原因となったサービスが復元された後、アクティブ化されたコンティンジェンシー計画の一部として行われた変更を元に戻します。

1. 通常のポリシーを有効にします
2. コンティンジェンシー ポリシーをレポート専用モードに戻すことができないようにします。
3. 中断中に行って文書化した他のすべての変更をロールバックします。
4. 緊急アクセス用アカウントを使用した場合は、緊急アクセス用アカウントの手順の一部として、忘れずに資格情報を再生成し、新しい資格情報の詳細を物理的にセキュリティ保護します。
5. 不審なアクティビティのため、中断後も引き続き[報告されたすべてのリスク検出をトリアージ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)します。
6. 一連のユーザーを対象に発行された[すべての更新トークンを取り消し](https://learn.microsoft.com/ja-jp/entra/identity/users/users-revoke-access) ます。 すべての更新トークンの取り消しは中断中に使用された特権アカウントについて重要であり、そうすることで、強制的に再認証が行われ、復元されたポリシーの制御に対応します。

### 緊急時のオプション

緊急事態が発生し、組織で以前に緩和策またはコンティンジェンシー プランを実施したことがない場合で、既に条件付きアクセス ポリシーを使用して MFA を適用しているときは、ユーザー ロックアウトのコンティンジェンシー セクションのレコメンデーションに従います。 組織でユーザーごとの MFA レガシ ポリシーを使用している場合は、次の代替手段を検討します。

- 企業ネットワークに送信 IP アドレスがある場合は、それを信頼できる IP として追加し、企業ネットワークに対してのみ認証を有効にできます。
- 送信 IP アドレスのインベントリがない場合、または企業ネットワークの内部と外部でアクセスを有効にする必要があった場合は、0.0.0.0/1 と 128.0.0.0/1 を指定することにより、IPv4 アドレス空間全体を信頼できる IP アドレスとして追加できます。

重要

アクセスのブロックを解除するために信頼できる IP アドレスの範囲を広げた場合、IP アドレスに関連するリスク検出 (たとえば、あり得ない移動や未知の場所) は生成されません。

Note

Microsoft Entra 多要素認証用の[信頼された IP](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-mfasettings) の構成は、[Microsoft Entra ID P1 または P2 ライセンス](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-licensing)でのみ利用できます。

### さらに学ぶ

- [Microsoft Entra認証に関するドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)
- [Microsoft Entra ID で緊急アクセス用管理アカウントを管理する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)
- [Microsoft Entra ID でネームド ロケーションを構成する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network)
- [Microsoft Entra ハイブリッド参加済みデバイスを構成する方法](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)
- [Windows Hello for Business のデプロイ ガイド](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-deployment-guide)
    - [パスワードのガイダンス - Microsoft Research](https://research.microsoft.com/pubs/265143/microsoft_password_guidance.pdf)
- [Microsoft Entra 条件付きアクセスの条件とは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions)
- [Microsoft Entra 条件付きアクセスのアクセス制御とは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/controls)
- [条件付きアクセスのレポート専用モードとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-report-only)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-sms-voice-retirement"} -->
## 既定でのパスキーと、Microsoftが提供する SMS および音声認証の廃止 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sms-voice-retirement
- Service: entra-id / authentication
- Article date: 2026-09-23
- Summary: Microsoft Entra IDで提供Microsoft SMS および音声認証の廃止に備え、ユーザーをパスキーに移行する方法について説明します。

企業が大規模に AI を導入できるようにするには、ユーザーがセキュリティで保護された認証を使用し、フィッシング対応の認証方法から [パスキー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)などのフィッシングに強い認証方法に移行することが不可欠です。 そのため、Microsoft Entra IDはパスキーを既定のサインイン エクスペリエンスにしているため、すべての組織が既定でフィッシングに強いセキュリティを取得します。 SMS と音声はセキュリティで保護された認証方法として配置されなくなり、Entra IDではネイティブに提供されなくなります。

2026 年 9 月 1 日から、パスキーが既定の認証エクスペリエンスになり、SMS または音声が有効になっているユーザーに対して自動的に有効になります。 2027 年 2 月 1 日から、SMS と音声のMicrosoft提供されたテレフォニー配信は、グローバル管理者と外部ユーザーを除くすべてのユーザーに対して廃止されます。 グローバル管理者と外部ユーザーの場合、Microsoft提供の SMS および音声認証は、2027 年 7 月 1 日に廃止されます。 これらの方法を引き続き必要とするお客様は、Microsoft Security ストアを通じてテレフォニー プロバイダーを構成する必要があります。 Soprano と Telesign はプライベート プレビュー期間中に利用可能な初期プロバイダーであり、一般提供により、より多くのプロバイダーが利用できるようになります。 プロバイダーとオファーの情報については、「 [SMS および音声認証用のテレフォニー プロバイダーの選択](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-phone-providers)」を参照してください。

既にパスキー、Windows Hello for Business、またはその他のフィッシング詐欺に強い方法でサインインしているユーザーは、これらの方法を引き続き使用できます。 ただし、SMS または音声を有効にしたままのユーザーには、対象となるデバイスにパスキーを登録するためのプロンプトが引き続き表示される場合があります。 2027 年 7 月 1 日の提供終了日は、グローバル管理者と外部ユーザーに適用されます。 内部ゲスト ユーザーはその 7 月 1 日のグループに含まれていないので、引き続き 2027 年 2 月 1 日の提供終了日に従います。 引き続き SMS または音声を使用しているテナントのユーザーを確認するには、「 テナントでアクティブな SMS または音声ユーザーを検索する」を参照してください。

### 退役のタイムライン

| Date | マイルストーン | 行うべきこと |
| --- | --- | --- |
| 2026 年 9 月 1 日 | ユーザーが SMS または音声で有効になっているテナントでは、それらのユーザーは自動有効になり、MFA サインイン時に Passkey 登録用にナッジされます。 | 変更が発生した場合は、エンド ユーザーに通知します。 [パスキーのデプロイ ガイド](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-deploy-phishing-resistant-passwordless-authentication)を使用して、パスキーを使用するための環境を準備します。 |
| 2027 年 2 月 1 日 | Microsoft提供の SMS および音声認証は、グローバル管理者と外部ユーザーを除くすべてのユーザーに対して廃止されます。 内部ゲスト ユーザーは、2 月 1 日の提供終了の対象のままです。 | 2 月 1 日の提供終了のスコープ内のユーザーが、この日付より前にフィッシングに強い方法 (パスキー、Windows Hello、または FIDO2) を使用しているか、サインインの中断が発生する可能性があることを確認します。 |
| 2027 年 2 月 1 日以降 | 2 月 1 日に提供終了となる対象ユーザーのうち、**利用可能な唯一の MFA 方法が SMS または音声である**ユーザーは、引き続きアカウントにアクセスするために、サインイン時にパスキーを登録する必要があります。 このプロンプトは **ブロックしています**。 ユーザーは、アカウント **へのサインインを続行する前に、パスキーを登録する** 必要があります。**2 月 1 日の提供終了の範囲のユーザーについては、この 2 月 1 日の動作からのオプトアウトはありません。** | 2 月 1 日の提供終了のスコープ内のユーザーをフィッシングに強い方法に移行するか、SMS または音声を引き続き使用するテレフォニー プロバイダーを選択します。 |
| 2027 年 7 月 1 日 | Microsoft提供される SMS および音声認証は、グローバル管理者と外部ユーザーに対して廃止されます。 内部ゲスト ユーザーは、この 7 月 1 日のグループには含まれていませんが、引き続き 2027 年 2 月 1 日の提供終了日に従います。 | グローバル管理者と外部ユーザーが、この日付より前にフィッシングに強い方法を使用しているか、サインインの中断が発生する可能性があることを確認します。 |
| 2027 年 7 月 1 日以降 | グローバル管理者と **、使用可能な MFA 方法が SMS または音声のみである** 外部ユーザーは、サインイン時にパスキーを登録してアカウントへのアクセスを続行する必要があります。 このプロンプトは **ブロックしています**。 ユーザーは、アカウント **へのサインインを続行する前に、パスキーを登録する** 必要があります。**この 7 月 1 日の動作は、グローバル管理者と外部ユーザーに対してはオプトアウトされません。** | グローバル管理者と外部ユーザーをフィッシングに強い方法に移行するか、SMS または音声を引き続き使用するテレフォニー プロバイダーを選択します。 |

### パスキーへの移行を準備する

#### 1. SMS または音声が有効なユーザーを検索する

Microsoftでは、移行を計画する前に、SMS または音声が有効になっているユーザーを特定することをお勧めします。 各グループを検索するには、次の手順を使用します。

SMS または音声が有効になっているユーザーを検索するには、この [PowerShell スクリプト](https://github.com/microsoft/entra-sms-voice-usage-analyzer)を実行します。 グローバル閲覧者、認証ポリシー管理者、またはセキュリティ閲覧者ロールのいずれかが有効になっていることを確認します。

#### 2. ユーザーをパスキーに移動する

パスキーは、Microsoft Entra IDの既定のフィッシング耐性資格情報です。 デバイスまたは同期された資格情報ストアに関連付けられ、共有シークレットの代わりに暗号化キーを使用し、フィッシング、SIM スワップ、リプレイ攻撃に対して耐性があります。

Microsoft Entra IDでは、次の 2 種類の[パスキーがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)。

- [同期されたパスキー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2#types-of-passkeys) — プラットフォーム資格情報マネージャー (iCloud キーチェーン、Google パスワード マネージャーなど) に保存され、ユーザーのデバイス間で同期されるパスキー。 プラットフォーム資格情報マネージャーを既に使用しているユーザーに最適です。
- [デバイス バインド パスキー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key-windows) — パスキーは、Microsoft Authenticatorの Passkey、Windows の Entra Passkey、FIDO2 ハードウェア セキュリティ キーなど、ユーザーのデバイスに作成されて格納されます。

テナントのパスキーを有効にしてロールアウトを計画するには、「[Microsoft Entra IDでのパスキーのデプロイの計画](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-deploy-phishing-resistant-passwordless-authentication)」および[「組織のパスキーの有効化 (FIDO2)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2)」を参照してください。

Entra IDがサポートするパスワードレス認証方法の完全な一覧については、[認証の概要Microsoft](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)参照してください。

Important

2026 年 9 月 1 日、Entra 認証方法ポリシー (AMP) または従来の MFA 設定で SMS または音声が有効になっているユーザーは、AMP のパスキーに対して自動有効になります。 対象ユーザーは、すべての種類のパスキーが許可されるパスキー プロファイルに追加されます。 登録キャンペーンの設定は、パスキーを対象とした Microsoft 管理状態に設定され、これらのユーザーは自動的に対象に含まれます。

これらのユーザーが次にサインインして MFA を完了すると、登録キャンペーンによってパスキーが登録されます。 既定では、ユーザーはナッジプロンプトを無制限にスヌーズできます。 これを行わない場合は、9 月 1 日より前に AMP の SMS または音声からユーザーを移動します。

##### 登録キャンペーンで積極的に導入を推進する

パスキー登録キャンペーンは、2026 年 9 月 1 日に SMS および音声対応ユーザーに対して自動的に有効になる前に有効にすることができます。 登録キャンペーンでは、ユーザーが次回サインインして MFA を完了する際にパスキーを設定するように求められます。 ヘルプ デスクの負荷を追加することなく、ユーザーを SMS と音声から大規模に移動する最も効果的な方法です。

登録キャンペーンを構成する前に、認証方法として Passkey (FIDO2) が有効になっており、SMS/Voice ユーザーがパスキー対応の認証方法ポリシーに含まれていることを確認します。

パスキーの登録キャンペーンを設定するには:

1. 認証ポリシー管理者として、Microsoft Entra 管理センターにサインインします。
2. **Entra ID &gt;認証方法&gt;登録キャンペーン** に移動します。
3. **State** を **Microsoft Managed** に設定し、手順 1 で作成した SMS および音声のユーザーのセキュリティ グループを対象にします。

#### 3. 運用上のニーズに合わせてMicrosoft Security ストアのテレフォニー プロバイダーを評価する

Microsoftでは、可能な限り、すべてのユーザーのプライマリ移行パスとしてパスキーを使用することをお勧めします。 規制対象の業界で運用している場合、またはテレフォニー チャネルの運用上のニーズがある場合 (たとえば、帯域外 SMS を必要とする特定のコンプライアンス体制や、他の方法が機能しないシナリオなど) は、それらのユーザー セグメントに対して Microsoft Security Store を通じて利用できるテレフォニー プロバイダーを使用できます。

1. テレフォニー チャネルに対して本物の規制または運用上のニーズがある特定のユーザー セグメントを特定します。 該当する規制やシナリオなどの要件を文書化します。
2. 初期プライベート プレビュー プロバイダーである Soprano および Telesign とそのオファーについては、「 [SMS および音声認証用のテレフォニー プロバイダーの選択](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-phone-providers)」を参照してください。 一般提供により、より多くのプロバイダーが利用できるようになります。
3. 2026 年 10 月 30 日以降、SMS または音声の使用を継続する必要があるお客様は、Microsoft Security ストアからテレフォニー プロバイダーを選択して構成できます。
4. 広範なロールアウトの前に、マーケットプレース フローを通じて運送業者契約を立ち上げ、パイロット グループでテストします。
5. テナント内の他のすべてのユーザー セグメントに対して、既定では passkeys が使用されます。

#### 4. 変更をユーザーに伝える

テナントでパスキーを有効にしている場合は、登録を開始する前に、何がいつ変更されるのか、またユーザーが何を行う必要があるのかを、ユーザーに明確に知らせてください。 調整された通信は、スムーズなパスキーロールアウトの唯一の最大の予測因子です。

Microsoftでは、提供終了のタイムラインに合わせた段階的な通信計画が推奨されます。

1. **認識** — SMS と音声が廃止されることを発表し、その理由を説明し、移行先の方法をユーザーに伝えます。
2. **アクション** — ユーザーに、デバイスの種類 (Windows Hello、iOS、Android) の詳細なガイダンスを使用して、パスキーを登録するように指示します。
3. **リマインダー** - フィッシングに強い方法をまだ登録していないユーザーに、実行する必要があるアクションを思い出させます。

電子メール、Teams、および従業員のコミュニケーション ポータルには、 [エンド ユーザー](https://aka.ms/mfatemplates) のコミュニケーション テンプレートを使用します。 Microsoftでは、手順 1 で作成した SMS ユーザーと音声ユーザーのセキュリティ グループにメッセージングのスコープを設定し、適切なユーザーが適切なタイミングでユーザーから聞き取るようにすることをお勧めします。

#### 5. 退職後

2027 年 2 月 1 日より、グローバル管理者と外部ユーザーを除くすべてのユーザーに対して、Microsoft提供の SMS と音声配信がMicrosoft Entra IDで廃止されます。 内部ゲスト ユーザーは、2027 年 2 月 1 日の廃止の対象のままです。

テナントで 2027 年 2 月 1 日の提供終了のスコープ内のユーザーが SMS または音声に対して有効になっていて、Microsoft Security ストア経由でテレフォニー プロバイダーを構成していない場合、それらのユーザーは SMS または音声を使用して MFA を完了し、通常どおりサインインできなくなります。

この日以降、2027 年 2 月 1 日の廃止の対象となるユーザーのうち、利用可能な MFA 方法が SMS または音声のみであるユーザーは、引き続きアカウントにアクセスするために、サインイン時にパスキーを登録する必要があります。 このプロンプトはブロッキングです。 ユーザーは、アカウントへのサインインを続行する前に、パスキーを登録する必要があります。

**2 月 1 日の提供終了の範囲のユーザーについては、この 2 月 1 日の動作からのオプトアウトはありません。**

2027 年 7 月 1 日以降、Microsoft提供の SMS と音声配信は、グローバル管理者と外部ユーザーに対して廃止されます。 内部ゲスト ユーザーはその 7 月 1 日のグループに含まれていないので、引き続き 2027 年 2 月 1 日の提供終了日に従います。

この日以降、グローバル管理者と、使用可能な MFA 方法が SMS または音声のみの外部ユーザーは、サインイン時にパスキーを登録してアカウントへのアクセスを続行する必要があります。 このプロンプトはブロッキングです。 ユーザーは、アカウントへのサインインを続行する前に、パスキーを登録する必要があります。

**この 7 月 1 日の動作は、グローバル管理者と外部ユーザーに対してはオプトアウトされません。**

サインインの中断を回避するには、ユーザーがパスキーを登録するか、該当する提供終了日の前に別のフィッシングに対する耐性のある認証方法に移動してください。 組織で SMS または音声を使用し続ける必要がある有効なビジネス、規制、運用上のニーズがある場合は、その日付より前にテレフォニー プロバイダーを構成します。

### 自動パスキー有効化を一時的にオプトアウトする

一時的なオプトアウトは、2026 年 9 月 1 日から 2027 年 2 月 1 日までの変更で利用できます。 これにより、テレフォニー プロバイダーの構成や他の認証方法への移行などの移行アクティビティの完了中に、パスキーと登録キャンペーンの有効化を遅らせることができる。

オプトアウトするには、Microsoft Graph `Policy.ReadWrite.AuthenticationMethod`アクセス許可が必要です。 Microsoft Graphを使用して認証方法ポリシーを更新し、`passkeyDynamicMigration` プロパティを `true` に設定します。

**Request**

```http
PATCH https://graph.microsoft.com/beta/policies/authenticationmethodspolicy
Content-Type: application/json

{
   "optOutSettings": {
     "passkeyDynamicMigration": true
   }
}
```

この設定を適用すると、オプトアウト期間中にテナントが自動パスキー有効化と登録キャンペーンのロールアウトから除外されます。 2027 年 2 月 1 日以降、2 月 1 日の提供終了のスコープ内のユーザーには、この設定に関係なく、標準のパスキーの移行と適用のタイムラインが適用されます。 代わりに、グローバル管理者と外部ユーザーは、2027 年 7 月 1 日の提供終了日に従います。 内部ゲスト ユーザーの廃止日は、2027 年 2 月 1 日のままです。

テナントで、該当する提供終了日にMicrosoftマネージド SMS または音声のユーザーがまだ有効になっていて、Microsoft Security ストアを通じてテレフォニー プロバイダーを構成していない場合、それらのユーザーは SMS または音声を使用して MFA 要件を満たし、サインインを続行できなくなります。

**強制のオプトアウトはありません。 この要件は、各ユーザー集団に適用される廃止日において、すべてのテナントに適用されます。**

### よく寄せられる質問

SMS と音声の提供終了、テレフォニー プロバイダー、オプトアウト オプション、パスキーの移行に関する一般的な質問に対する回答については、 [SMS と音声の提供終了に関してよく寄せられる質問](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sms-voice-retirement-faq)を参照してください。

### 関連リンク

- [認証方法とは](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)
- [パスキーの導入を計画する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-deploy-phishing-resistant-passwordless-authentication)
- [認証方法アクティビティ レポート](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-methods-activity)
- [テレフォニー プロバイダーに関してよく寄せられる質問](https://learn.microsoft.com/ja-jp/entra/identity/authentication/phone-providers-faq)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-sms-voice-retirement-faq"} -->
## Microsoftが提供する SMS と音声の提供終了に関する FAQ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sms-voice-retirement-faq
- Service: entra-id / authentication
- Article date: 2026-09-23
- Summary: Microsoft Entra IDでのMicrosoft提供された SMS および音声認証の廃止と、パスキーへの移行に関してよく寄せられる質問。

この記事では、Microsoft Entra IDでのMicrosoftが提供する SMS および音声認証の廃止に関してよく寄せられる質問に回答します。 完全なタイムラインと移行のガイダンスについては、[既定でのパスキーと、Microsoft提供される SMS および音声認証の廃止](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sms-voice-retirement)に関するページを参照してください。

### なぜ Microsoft 提供の SMS および音声向け通信配信は終了するのですか？

プライマリ ドライバーはセキュリティです。 業界がフィッシング耐性のある認証へ移行する中で、Microsoft Entra ID ではパスキーを既定の認証方法として採用しています。 SMS と音声は、現在利用できる最も脆弱な認証方法の 1 つであり、パスキーよりもフィッシングやアカウントの侵害に対する保護が大幅に弱くなっています。

SMS または音声を引き続き必要とする組織は、正当なビジネス、規制、または技術的なニーズがある場合に、テレフォニー プロバイダーを通じてそのオプションを引き続き利用できます。 この変更は、これらのシナリオの柔軟性を維持しながら、認証を最新化することを目的としています。

2026 年 9 月 1 日から、現在 SMS または音声認証が有効になっているユーザーには、パスキーが自動的に有効になります。 2027 年 2 月 1 日以降、廃止は、グローバル管理者と外部ユーザーを除くすべてのユーザーに適用されます。 Microsoft Security ストアを通じてテレフォニー プロバイダーを構成していない場合、2 月 1 日の提供終了のスコープのユーザーは、MFA に SMS または音声を使用できなくなります。 MFA の方法が SMS または音声通話のみで、その廃止の対象となるユーザーは、引き続きアカウントにアクセスするには、サインイン時にパスキーを登録する必要があります。 グローバル管理者と外部ユーザーには、2027 年 7 月 1 日というより遅い廃止日が適用されます。 内部ゲスト ユーザーはその 7 月 1 日のグループに含まれていないので、引き続き 2027 年 2 月 1 日の提供終了日に従います。 テレフォニー プロバイダーを構成したテナントは、組織のポリシーに従って SMS または音声を引き続き使用できます。

### グローバル管理者と外部ユーザーでは、廃止日は異なりますか?

Yes. Microsoft提供の SMS および音声認証は、2027 年 7 月 1 日にグローバル管理者と外部ユーザーに対して廃止されます。

内部ゲスト ユーザーはその 7 月 1 日のグループに含まれていないので、引き続き 2027 年 2 月 1 日の提供終了日に従います。

### Microsoft Security ストアを介したテレフォニー プロバイダーの使用に関連するコストは発生しますか?

Yes. 料金は、テレフォニー プロバイダーとリージョンによって異なります。 コストは、使用状況、地理的分布、選択したプロバイダー、オファーによって異なります。 特定の価格と商用の詳細については、 [テレフォニー プロバイダーの FAQ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/phone-providers-faq#costs-and-billing) で Soprano と Telesign のオファーを確認してください。

ただし、提供Microsoft SMS および音声ユーザーを Passkeys に移行しても、追加コストは発生しません。

### ユーザーは自動移行されますか、それとも行う必要がありますか?

2026 年 9 月 1 日、Entra 認証方法ポリシー (AMP) で SMS または音声が有効になっているユーザーは、AMP のパスキーに対して自動的に有効になります。 登録キャンペーンの設定も Microsoft 管理状態に更新され、これらのユーザーは自動的に対象に含まれるようになります。

これらのユーザーが次にサインインして MFA を完了すると、登録キャンペーンによってパスキーが登録されます。 既定では、ユーザーはナッジプロンプトを無制限にスヌーズできます。 これを行わない場合は、9 月 1 日より前に SMS または音声 AMP からユーザーを移動します。

### SSPR (セルフサービス パスワード リセット) についてはどうですか?

ネイティブ SMS と音声の廃止は、SSPR を含むMicrosoft Entra全体に適用されます。 ただし、ユーザーは引き続き、Microsoft Security ストアで利用可能なテレフォニー プロバイダーを介して SMS と音声を使用できます。

Microsoftでは、パスワードなしのサインインで認証を行うユーザーのパスワード変更のサポートも導入する予定です。 詳細については、以下を参照してください。

### Microsoft Securityストアとは

Microsoft提供される SMS または音声の代わりに、Microsoft Security ストアを介してサポートされているテレフォニー プロバイダーと直接契約できます。 地域管理を取得し、ローカルのセキュリティとコンプライアンスの要件を満たすプロバイダーを選択できます。 このオプションは、テレフォニー チャネルの規制要件または運用要件があるお客様を対象としています。

Soprano と Telesign は、プライベート プレビュー中に利用できる初期テレフォニー プロバイダーです。 一般提供により、より多くのプロバイダーが利用できるようになります。 構成エクスペリエンスは、2026 年 10 月 30 日以降に利用可能になります。 プロバイダーとオファーの情報については、「 [SMS および音声認証用のテレフォニー プロバイダーの選択](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-phone-providers)」を参照してください。

### テナントがスコープ内にあるかどうかを確認できる場所

SMS または音声をまだ使用しているユーザーを見つけるには、メインの提供終了に関する記事で説明されている [PowerShell スクリプト](https://github.com/microsoft/entra-sms-voice-usage-analyzer) を実行します。 0 以外の結果は、スコープ内に存在していることを意味します。

### このタイムラインに含まれるクラウド環境はどれですか?

このタイムラインは、パブリック クラウド環境にのみ適用されます。 その他のクラウド環境は、後のスケジュールに従い、お客様が移行に備えるのに役立つ事前の通信を提供します。

### AZURE AD B2C または Microsoft Entra 外部 ID テナントはこの発表の影響を受けますか?

Azure AD B2C は範囲外であり、影響を受けません。 Microsoft Entra 外部 IDの場合、この変更は来年に行われ、別途お知らせが続きます。 そのため、このお知らせでは、Azure AD B2C または Microsoft Entra 外部 ID テナントに変更はありません。

### B2B ユーザーがパスキーのサポートを利用できるようになるのはいつですか?

B2B ユーザーと内部ゲスト ユーザーに対するパスキーのサポートは、2026 年の年末までに利用できるようになる予定です。 これらのユーザーは、Microsoft提供された SMS および音声認証の廃止のスコープに含まれています。

外部ユーザーは、2027 年 7 月 1 日の提供終了日に従います。 内部ゲスト ユーザーは、引き続き 2027 年 2 月 1 日の提供終了日に従います。

### 外部 MFA メソッドは SMS と音声の提供終了の影響を受けますか?

いいえ。SMS および音声認証方法のポリシーと従来の MFA ポリシーのみが廃止されます。

2026 年 9 月 1 日、認証方法ポリシーまたは従来の MFA ポリシーで SMS または音声が有効になっているユーザーは、パスキーに対して自動有効になり、登録がナッジされます。 外部 MFA ユーザーは、SMS または音声でも有効になっていない限り、スコープ内にありません。

### SMS/音声ユーザー向けにパスキーを有効にする以外の予定（たとえば、テレフォニー プロバイダーを構成する、またはユーザーを別の認証方法に移行するなど）がテナントにある場合は、どうすればよいですか?

一時的なオプトアウトは、2026 年 9 月 1 日から 2027 年 2 月 1 日までの変更で利用できるようになります。 これにより、テレフォニー プロバイダーの構成や他の認証方法への移行などの移行アクティビティの完了中に、パスキーと登録キャンペーンの有効化を遅らせることができます。

オプトアウトするには、Microsoft Graphを使用して認証方法ポリシーを更新し、`passkeyDynamicMigration` プロパティを `true` に設定します。

**Request**

```http
PATCH https://graph.microsoft.com/beta/policies/authenticationmethodspolicy
Content-Type: application/json

{
   "optOutSettings": {
     "passkeyDynamicMigration": true
   }
}
```

この設定を適用すると、オプトアウト期間中にテナントが自動パスキー有効化と登録キャンペーンのロールアウトから除外されます。 2027 年 2 月 1 日以降、2 月 1 日の提供終了のスコープ内のユーザーに対しては、この設定に関係なく、標準のパスキーの移行と適用のタイムラインが適用されます。 代わりに、グローバル管理者と外部ユーザーは、2027 年 7 月 1 日の提供終了日に従います。 内部ゲスト ユーザーの廃止日は、2027 年 2 月 1 日のままです。

ただし、テナントで該当する提供終了日にMicrosoftマネージド SMS または音声のユーザーがまだ有効になっていて、Microsoft Security ストアを通じてテレフォニー プロバイダーを構成していない場合、それらのユーザーは MFA 要件を満たすために SMS または音声を使用してサインインを続行できなくなります。

該当する提供終了日を過ぎると、使用可能な MFA 方法が SMS または音声のみのユーザーは、サインイン時にパスキーを登録してアカウントへのアクセスを続行する必要があります。

強制のオプトアウトはありません。 この要件は、各ユーザー集団に適用される廃止日において、すべてのテナントに適用されます。

### テレフォニー プロバイダーを構成した場合、ユーザーはパスキー登録のブロックプロンプトを受け取りますか?

No. Microsoft Security ストアを通じてテレフォニー プロバイダーを構成し、該当する提供終了日より前に SMS または音声を必要とするすべてのユーザーを移行した場合、それらのユーザーは SMS および音声提供終了の適用の一環として、ブロックパスキー登録プロンプトを受け取りません。

テレフォニー プロバイダーは、SMS または音声を引き続き使用するビジネス、規制、または技術的なニーズがある組織を対象としています。 プロバイダーが構成されていること、および影響を受けるすべてのユーザーが該当する提供終了日より前に移行されていることを確認します。 グローバル管理者と外部ユーザーは、2027 年 7 月 1 日の提供終了日に従います。 内部ゲスト ユーザーはその 7 月 1 日のグループに含まれていないので、引き続き 2027 年 2 月 1 日の提供終了日に従います。 Microsoft指定された SMS または音声を有効にしたままのユーザーは、引き続きパスキー登録要件の対象となります。

### 顧客は提供終了日にアカウントにアクセスできなくなりますか?

No. 該当する提供終了日までにテレフォニー プロバイダーを構成していない場合、SMS と音声を引き続き使用するユーザーには、パスキーを登録するためのブロック登録プロンプトが表示されます。 このプロンプトはスキップできなくなり、サインインを続行する前にパスキーの登録を完了する必要があります。 グローバル管理者と外部ユーザーは、2027 年 7 月 1 日の提供終了日に従います。 内部ゲスト ユーザーはその 7 月 1 日のグループに含まれていないので、引き続き 2027 年 2 月 1 日の提供終了日に従います。 退職後に SMS または音声を必要とする組織は、Microsoft Security ストアを通じてテレフォニー プロバイダーを選択できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-sspr-deploy"} -->
## Microsoft Entra セルフサービス パスワード リセットの展開に関する考慮事項 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-deploy
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra のセルフサービス パスワード リセットの実装を成功させるためのデプロイに関する考慮事項と戦略について説明します

重要

このデプロイ計画は、Microsoft Entra セルフサービス パスワード リセット (SSPR) をデプロイするためのガイダンスとベスト プラクティスを提供します。

**自分がエンド ユーザーであり、自分のアカウントを回復する必要がある場合は、https://aka.ms/sspr** にアクセスします。

Microsoft Entra の機能である[セルフサービス パスワード リセット (SSPR)](https://www.youtube.com/watch?v=pS3XwfxJrMo) を使用すると、ユーザーは自分のパスワードをリセットすることができ、IT スタッフにヘルプを依頼する必要はありません。 ユーザーは場所や時間に関係なく、自分ですぐにブロックを解除して作業を続けることができます。 自分でブロックを解除することが従業員に許可されている場合は、パスワードに関連する多くの一般的な問題に対する非生産的な時間と高いサポート コストを削減できます。

SSPR の主な機能は次のとおりです。

- セルフサービスを使用すると、エンド ユーザーは、管理者またはヘルプデスクにサポートを求めなくても、期限切れまたは期限切れではないパスワードをリセットできます。
- [パスワード ライトバック](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-writeback)を使用すると、クラウドを介してオンプレミスのパスワードを管理し、アカウントのロックアウトを解決することができます。
- パスワード管理アクティビティ レポートでは、組織内で発生したパスワードのリセットおよび登録アクティビティの詳細が管理者に提供されます。

このデプロイ ガイドでは、SSPR のロールアウトを計画してテストする方法について説明します。

まず、SSPR の動作の概要を確認してから、デプロイに関するその他の考慮事項について説明します。

ヒント

この記事のコンパニオンとして、Microsoft 365 管理センターにサインインするときに、[セルフサービス パスワード リセットの展開を計画する](https://go.microsoft.com/fwlink/?linkid=2221501)ガイドを使用するよう、お勧めします。 このガイドでは、環境に基づいてエクスペリエンスをカスタマイズします。 サインインして自動セットアップ機能をアクティブ化せずにベスト プラクティスを確認するには、[M365 セットアップ ポータル](https://go.microsoft.com/fwlink/?linkid=2221600)に移動します。

### SSPR の詳細

SSPR の詳細を参照してください。 「[動作のしくみ: Azure AD のセルフサービス パスワード リセット](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-howitworks)」を参照してください。

#### 主な利点

SSPR を有効にする主な利点は次のとおりです。

- **コスト管理**。 SSPR は、ユーザーが自分でパスワードをリセットできるようにすることで、IT サポートのコストを削減します。 また、パスワードの紛失やロックアウトによって損失する時間が短縮されます。
- **直感的なユーザー エクスペリエンス**。 ユーザーがパスワードをリセットし、任意のデバイスや場所からの要求時にアカウントのブロックを解除できるため、直感的なワンタイム ユーザー登録プロセスが実現します。 SSPR を使用すると、ユーザーは迅速に作業を再開し、生産性を高めることができます。
- **柔軟性とセキュリティ**。 SSPR を使用すると、企業はクラウド プラットフォームが提供するセキュリティと柔軟性にアクセスできます。 管理者は、新しいセキュリティ要件に合わせて設定を変更し、サインインを中断せずにこれらの変更をユーザーにロールアウトすることができます。
- **堅牢な監査と使用状況追跡**。 ユーザーが自分のパスワードをリセットしている間も、組織はビジネス システムが安全であることを確認できます。 堅牢な監査ログには、パスワード リセット プロセスの各手順の情報が含まれます。 これらのログは API から入手でき、これによりユーザーは、選択したセキュリティ インシデントおよびイベント監視 (SIEM) システムにデータをインポートできます。

#### ライセンス

Microsoft Entra ID はユーザーごとのライセンスであり、機能を利用するには、各ユーザーに適切なライセンスが必要です。 SSPR にはグループベースのライセンスが推奨されます。

エディションと機能を比較し、グループベースまたはユーザーベースのライセンスを有効にする場合は、[Microsoft Entra ID のセルフサービス パスワード リセットのライセンス要件](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-licensing)に関する記事を参照してください。

価格の詳細については、[Microsoft Entra の価格](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)に関する記事を参照してください。

#### 前提条件

- 少なくとも試用版ライセンスが有効になっている、動作している Microsoft Entra テナント。 必要に応じて、[無料で作成できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 少なくとも[認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)ロールが割り当てられている必要があります。

#### ガイド付きウォークスルー

この記事内の多くの推奨事項のガイド付きチュートリアルについては、Microsoft 365 管理センターにサインインしたときの「[セルフサービス パスワード リセットの展開を計画する](https://go.microsoft.com/fwlink/?linkid=2221501)」ガイドを参照してください。 サインインして自動セットアップ機能をアクティブ化せずにベスト プラクティスを確認するには、[M365 セットアップ ポータル](https://go.microsoft.com/fwlink/?linkid=2221600)に移動します。

#### トレーニング リソース

| リソース | リンクと説明 |
| --- | --- |
| ビデオ | [IT のスケーラビリティ向上によるユーザーの支援](https://youtu.be/g9RpRnylxS8) |
|  | [セルフサービス パスワード リセットとは](https://youtu.be/hc97Yx5PJiM) |
|  | [セルフサービス パスワード リセットのデプロイ](https://www.youtube.com/watch?v=Pa0eyqjEjvQ&amp;index=18&amp;list=PLLasX02E8BPBm1xNMRdvP6GtA6otQUqp0) |
|  | [Microsoft Entra ID で SSPR を有効にして構成する方法](https://www.youtube.com/watch?v=rA8TvhNcCvQ) |
|  | [Microsoft Entra ID のセキュリティ情報を登録する \[ユーザーを準備する\] 方法](https://youtu.be/gXuh0XS18wA) |
| オンライン コース | [Microsoft Entra ID での ID の管理](https://www.pluralsight.com/courses/microsoft-azure-active-directory-managing-identities) SSPR を使用して、ユーザーに最新の保護されたエクスペリエンスを提供します。 特に "[`Managing Microsoft Entra Users and Groups`](https://app.pluralsight.com/library/courses/microsoft-azure-active-directory-managing-identities/table-of-contents)" モジュールを参照してください。 |
|  | [Microsoft Enterprise Mobility Suite の概要](https://www.pluralsight.com/courses/microsoft-enterprise-mobility-suite-getting-started) 認証、承認、暗号化、およびセキュリティで保護されたモバイル エクスペリエンスを実現する方法で、オンプレミスの資産をクラウドに拡張するためのベスト プラクティスについて説明します。 特に Microsoft Entra ID P1 または P2 の高度な機能の構成に関するモジュールを参照してください。 |
| チュートリアル | [Microsoft Entra セルフサービス パスワード リセット パイロットのロールアウトを完了する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr) |
|  | 「[パスワード ライトバックを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr-writeback)」を使用して、パスワード ライトバックを有効にする |
|  | [Windows 10 のログイン画面からの Microsoft Entra パスワード リセット](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-windows) |
| FAQ | [パスワード管理に関するよく寄せられる質問 (FAQ)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/passwords-faq) |

#### ソリューションのアーキテクチャ

次の例では、一般的なハイブリッド環境向けのパスワード リセット ソリューションのアーキテクチャについて説明しています。

[Image: ソリューション アーキテクチャの図]

ワークフローの説明

パスワードをリセットするためには、ユーザーは[パスワードのリセット用ポータル](https://aka.ms/sspr)にアクセスします。 ユーザーは、以前に登録した認証方法 (1 つまたは複数) を使用して、身元を証明する必要があります。 パスワードが正常にリセットされると、リセット プロセスが開始されます。

- クラウド専用ユーザーの場合、SSPR では新しいパスワードが Microsoft Entra ID に格納されます。
- ハイブリッド ユーザーの場合、SSPR では、パスワードは Microsoft Entra Connect サービスを介してオンプレミスの Active Directory にライトバックされます。

注

[パスワード ハッシュ同期 (PHS)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs) が無効になっているユーザーの場合、SSPR では、パスワードはオンプレミスの Active Directory にのみ格納されます。

#### ベスト プラクティス

別の一般的なアプリケーションまたはサービスを、SSPR と共に組織にデプロイすることで、ユーザーを迅速に登録できます。 このアクションでは、大量のサインインが生成され、登録が促進されます。

SSPR をデプロイする前に、各パスワード リセット呼び出しの数と平均コストを決定することも選択できます。 このデプロイ後のデータを使用して、SSPR によって組織にもたらされる価値を示すことができます。

#### SSPR と Microsoft Entra の多要素認証の統合登録

SSPR では、ユーザーは、Microsoft Entra 多要素認証に使用するのと同じ方法を使用して、セキュリティで保護された方法でパスワードをリセットすることができます。 [統合された登録](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-registration-mfa-sspr-combined)は、MFA と SSPR の両方の方法の登録を同時に有効にするエンド ユーザーのための 1 つの登録手順です。 機能とエンド ユーザー エクスペリエンスを確実に理解するには、[統合されたセキュリティ情報の登録の概念](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-registration-mfa-sspr-combined)に関する記事を参照してください。

予定されている変更、登録要件、必要なユーザー操作について、ユーザーに通知することが重要です。 お客様のユーザーに新しいエクスペリエンスに向けて準備をさせ、ロールアウトの確実な成功を支援するために、[通信テンプレート](https://aka.ms/mfatemplates)と[ユーザー ドキュメント](https://support.microsoft.com/account-billing/set-up-security-info-from-a-sign-in-page-28180870-c256-4ebf-8bd7-5335571bf9a8)が用意されています。 ユーザーを https://myprofile.microsoft.com に誘導し、そのページの **[セキュリティ情報]** リンクを選択して登録してもらいます。

### デプロイ プロジェクトを計画する

お客様の環境でこのデプロイの戦略を決定するときは、お客様の組織のニーズを考慮してください。

#### 適切な関係者を関わらせる

テクノロジ プロジェクトが失敗する場合、通常の原因は、影響、結果、および責任に対する想定の不一致です。 これらの潜在的な危険を回避するには、[適切な利害関係者を含めて](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans)、利害関係者およびそのプロジェクトでの入力と説明責任を文書化することで、プロジェクトでの利害関係者の役割をよく理解させます。

##### 必要な管理者の役割

| ビジネス ロール/ペルソナ | Microsoft Entra のロール（必要に応じて） |
| --- | --- |
| レベル 1 ヘルプデスク | パスワード管理者 |
| レベル 2 ヘルプデスク | ユーザー管理者 |
| SSPR 管理者 | 認証管理者 |

#### パイロットを計画する

SSPR の初期構成はテスト環境で行うことをお勧めします。 組織内のユーザーのサブセットに対して SSPR を有効にすることで、パイロット グループから始めてください。 「[パイロットのベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans)」を参照してください。

グループを作成するには、[Microsoft Entra ID でグループを作成し、メンバーを追加する](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups)方法を参照してください。

### 構成を計画する

推奨値で SSPR を有効にするには、次の設定が必要です。

| 領域 | 設定 | 値 |
| --- | --- | --- |
| **SSPR のプロパティ** | セルフサービス パスワード リセット が有効 | パイロットの場合は **[選択済み]** グループ/運用環境の場合は **[すべて]** |
| **認証方法** | Authentication methods required to register (登録に必要な認証方法) | リセットのため必要な数より常に 1 つ多い値 |
|  | Authentication methods required to reset (リセットに必要な認証方法) | 1 つまたは 2 つ |
| **登録** | サインイン時にユーザーに登録を求めますか | はい |
|  | ユーザーが認証情報を再確認するように求められるまでの日数 | 90 – 180 日 |
| **通知** | [パスワードのリセットについてユーザーに通知しますか] | はい |
|  | 他の管理者が自分のパスワードをリセットしたときに、すべての管理者に通知しますか | はい |
| **カスタマイズ** | ヘルプデスク リンクのカスタマイズ | はい |
|  | カスタム ヘルプデスクの電子メールまたは URL | サポート サイトまたはメール アドレス |
| **オンプレミスの統合** | オンプレミスの AD へのパスワードの書き戻し | はい |
|  | パスワードをリセットせずにアカウントのロックを解除することをユーザーに許可する | はい |

#### SSPR のプロパティ

SSPR を有効にする場合は、パイロット環境で適切なセキュリティ グループを選択します。

- すべてのユーザーに SSPR 登録を適用する場合は、 **[すべて]** オプションを使用することをお勧めします。
- それ以外の場合は、適切な Microsoft Entra ID または AD セキュリティ グループを選びます。

#### 認証方法

SSPR が有効になっている場合、ユーザーが自分のパスワードをリセットできるのは、管理者が有効にしている認証方法にデータがある場合のみです。 方法には、電話、Authenticator アプリの通知、セキュリティの質問などがあります。 詳細については、「[認証方法とは](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)」を参照してください。

次の認証方法の設定が推奨されます。

- **[Authentication methods required to register](https://learn.microsoft.com/ja-jp/entra/identity/authentication/登録に必要な認証方法)** を、リセットに必要な数より少なくとも 1 つ多い数に設定します。 複数の認証を許可すると、リセットが必要なときのユーザーの柔軟性が増します。
- **[リセットのために必要な方法の数]** を、組織に適したレベルに設定します。 1 つでは最小限の手間で済みますが、2 つではセキュリティ対策が強化されることがあります。

注意: ユーザーは、[Microsoft Entra ID のパスワード ポリシーと制限](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-policy)を構成している認証方法を備えている必要があります。

#### 登録設定

**サインイン時にユーザーに登録を求めますか** を **[はい]** に設定します。 この設定では、サインイン時にユーザーに登録を求めることで、すべてのユーザーが確実に保護されるようにします。

組織で短い期間が必要でない限り、 **ユーザーが認証情報を再確認するように求められるまでの日数** を **90** から **180** 日の範囲に設定します。

#### 通知の設定

**パスワードのリセットについてユーザーに通知しますか** と **他の管理者が自分のパスワードをリセットしたときに、すべての管理者に通知しますか** の両方を、 **[はい]** に设定します。 両方で **[はい]** を選択すると、パスワードがリセットされたことをユーザーが確実に認識できるため、セキュリティが向上します。 また、1 人の管理者がパスワードを変更した場合でも、すべての管理者が確実に認識できます。 ユーザーまたは管理者は、通知を受け取ったときに変更を開始していない場合は、すぐにセキュリティの問題の可能性を報告できます。

注

SSPR サービスからのメール通知は、使用している Azure クラウドに基づいて、次のアドレスから送信されます。

- パブリック: msonlineservicesteam@microsoft.com
- 中国: msonlineservicesteam@oe.21vianet.com
- 政府機関: msonlineservicesteam@azureadnotifications.us

通知の受信で問題が発生した場合は、スパム設定を確認してください。

#### カスタマイズ設定

問題が発生したユーザーがすぐにヘルプを受けられるよう、ヘルプデスクのメールまたは URL をカスタマイズすることが重要です。 このオプションを、ユーザーがよく知っている一般的なヘルプデスクのメール アドレスまたは Web ページに設定します。

詳細については、[セルフサービス パスワード リセットのための Microsoft Entra 機能のカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-customization)に関する記事を参照してください。

#### パスワード書き戻し

**パスワード ライトバック**は、[Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/whatis-hybrid-identity) で有効になっていて、クラウドでのパスワード リセットを既存のオンプレミスのディレクトリにリアルタイムで書き戻します。 詳しくは、「[パスワード ライトバックとは](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-writeback)」をご覧ください

次の設定が推奨されます。

- **[オンプレミスの Active Directory へのパスワードの書き戻し]** が **[はい]** に設定されていることを確認します。
- **[パスワードをリセットせずにアカウントのロックを解除することをユーザーに許可する]** を **[はい]** に設定します。

既定では、Microsoft Entra ID はパスワード リセットを実行するときにアカウントをロック解除します。

#### 管理者パスワード設定

管理者のアカウントは、アクセス許可が引き上げられています。 オンプレミスのエンタープライズ管理者またはドメイン管理者は、SSPR で自分のパスワードをリセットできません。 オンプレミスの管理者アカウントには、次の制限があります。

- オンプレミス環境でのみ、自分のパスワードを変更できます。
- パスワードをリセットする方法として、秘密の質問と回答を使用できません。

オンプレミスの Active Directory 管理者アカウントは Microsoft Entra ID と同期させないことをお勧めします。

#### 複数の ID 管理システムがある環境

環境によっては、複数の ID 管理システムが存在する場合があります。 Oracle IAM や SiteMinder などのオンプレミスの ID マネージャーでは、パスワードについて AD との同期が必要です。 これは、Microsoft Identity Manager (MIM) を使用したパスワード変更通知サービス (PCNS) のようなツールを使用することで実行できます。 このより複雑なシナリオについては、「[ドメイン コントローラーに MIM パスワード変更通知サービスを展開する](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/deploying-mim-password-change-notification-service-on-domain-controller)」をご覧ください。

### テストとサポートを計画する

初期のパイロット グループから組織全体に至るまでのデプロイの各段階で、確実に期待どおりの結果が得られるようにします。

#### テストを計画する

デプロイが意図したとおりに動作することを確認するには、実装を検証にするテスト ケースのセットを計画します。 テスト ケースにアクセスするには、パスワードを持つ非管理者テスト ユーザーが必要です。 ユーザーを作成する必要がある場合は、[Microsoft Entra ID への新しいユーザーの追加](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users)に関する記事を参照してください。

次の表では、ポリシーに基づいて組織で予想される結果を文書化するために使用できる有用なテスト シナリオを示します。

| ビジネス ケース | 予想される結果 |
| --- | --- |
| 企業ネットワーク内から SSPR ポータルにアクセスできる | 組織によって決定される |
| 企業ネットワーク外から SSPR ポータルにアクセスできる | 組織によって決定される |
| ユーザーがパスワード リセットを有効にされていないときに、ブラウザーからユーザーのパスワードをリセットする | ユーザーはパスワード リセット フローにアクセスできない |
| ユーザーがパスワード リセットに登録されていないときに、ブラウザーからユーザーのパスワードをリセットする | ユーザーはパスワード リセット フローにアクセスできない |
| パスワード リセットの登録が強制されているときに、ユーザーがサインインする | ユーザーにセキュリティ情報を登録するよう求める |
| パスワード リセットの登録が完了したら、ユーザーがサインインする | ユーザーにセキュリティ情報を登録するよう求める |
| ユーザーがライセンスを持っていないときに、SSPR ポータルにアクセスできる | アクセスできる |
| Windows 10 Microsoft Entra 参加済みまたは Microsoft Entra ハイブリッド参加済みデバイスのロック画面からユーザー パスワードをリセットする | ユーザーはパスワードをリセットできる |
| SSPR の登録と使用状況のデータを、管理者がほぼリアルタイムで使用できる | 監査ログを介して利用できる |

[Microsoft Entra のセルフサービス パスワード リセットのパイロット展開の完了](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr)に関する記事も参照してください。 このチュートリアルでは、SSPR を組織にパイロット展開できるようにし、管理者以外のアカウントを使用してテストします。

#### サポートを計画する

通常、SSPR ではユーザーの問題は発生しませんが、発生する可能性のある問題に対応できるようサポート スタッフを準備することが重要です。 サポート チームが成功できるように、ユーザーから受け取った質問に基づいて、FAQ を作成できます。 次に例をいくつか示します。

| シナリオ | 説明 |
| --- | --- |
| ユーザーに使用可能な登録済み認証方法がない | ユーザーは、自分のパスワードをリセットしようとしていますが、使用可能な登録済みの認証方法がありません (例: 携帯電話が自宅にあり、メールにアクセスできない) |
| ユーザーはオフィスまたは携帯電話でテキストまたは通話を受け取っていない | ユーザーは、テキストまたは通話により本人確認をしようとしていますが、テキスト/通話を受け取っていません。 |
| ユーザーはパスワード リセット ポータルにアクセスできない | ユーザーは、パスワードのリセットを望んでいますが、パスワードのリセットが有効になっておらず、パスワード更新ページにアクセスできません。 |
| ユーザーは新しいパスワードを設定できない | ユーザーは、パスワード リセット フローで検証を完了しましたが、新しいパスワードを設定できません。 |
| ユーザーの Windows 10 デバイスに [パスワードのリセット] リンクが表示されない | ユーザーは、Windows 10 のロック画面からパスワードをリセットしようとしていますが、デバイスが Microsoft Entra ID に参加していないか、または Microsoft Intune のデバイス ポリシーが有効になっていません |

#### ロールバックを計画する

デプロイをロールバックするには、以下を行います。

- 1 人のユーザーの場合は、セキュリティ グループからユーザーを削除します
- グループの場合は、SSPR 構成からグループを削除します
- 全員に対して、Microsoft Entra テナントの SSPR を無効にする

### SSPR をデプロイする

デプロイの前に、次の操作を完了済みであることを確認します。

1. 適切な構成設定を決定した。
2. パイロット環境と運用環境のユーザーとグループを特定した。
3. 登録とセルフサービス用の構成設定を決定した。
4. ハイブリッド環境がある場合は、パスワード ライトバックを構成してください。

**これで、SSPR をデプロイする準備が整いました。**

次の領域の構成の詳細な手順については、「[セルフサービス パスワード リセットを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr#enable-self-service-password-reset)」を参照してください。

1. [認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)
2. [登録設定](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-registration-mfa-sspr-combined)
3. 通知設定
4. [カスタマイズ設定](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-customization)
5. [オンプレミスの統合](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr-writeback)

#### Windows で SSPR を有効にする

Windows 7、8、8.1、および 10 を実行中のコンピューターでは、[Windows のサインイン画面でユーザーが自分のパスワードをリセットできるように設定する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-windows)ことができます

### SSPR を管理する

Microsoft Entra ID では、監査とレポートによって SSPR のパフォーマンスに関する追加情報を提供できます。

#### パスワード管理アクティビティ レポート

Microsoft Entra 管理センター で事前構築済みのレポートを使用して、SSPR のパフォーマンスを測定できます。 適切にライセンスを付与されている場合は、カスタム クエリを作成することもできます。 詳細については、[Microsoft Entra のパスワード管理に関するレポート オプション](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-reporting)の記事を参照してください。

注

このデータを組織用に収集するには、オプトインすることが必要です。 オプトインするには、Microsoft Entra 管理センター の レポート タブまたは監査ログに少なくとも 1 回アクセスする必要があります。 それまでは、ご自分の組織のデータは収集されません。

登録とパスワード リセットに関する監査ログは、30 日間利用できます。 企業内のセキュリティ監査をもっと長い期間保有する必要がある場合、ログをエクスポートし、[Microsoft Sentinel](https://learn.microsoft.com/ja-jp/azure/sentinel/connect-azure-active-directory)、Splunk、ArcSight などの SIEM ツールに取り込む必要があります。

[Image: SSPR レポートのスクリーンショット]

#### 認証方法 - 使用状況と分析情報

[使用状況と分析情報](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-methods-activity)を使うと、Microsoft Entra 多要素認証や SSPR などの機能の認証方法が組織内でどのように機能しているかについて理解を深めることができます。 このレポート機能は、組織がどの方法で登録を行い、それらをどのように使用しているかを把握するための手段となるものです。

#### トラブルシューティング

- 「[セルフサービスのパスワードのリセットのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/authentication/troubleshoot-sspr)」を参照してください
- 「[パスワード管理に関するよく寄せられる質問 (FAQ)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/passwords-faq)」に従ってください

#### 役に立つドキュメント

- [認証方法とは](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)
- [動作のしくみ: Microsoft Entra のセルフサービス パスワード リセット](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-howitworks)
- [セルフサービス パスワード リセットのための Microsoft Entra 機能のカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-customization)
- [Microsoft Entra ID のパスワード ポリシーと制限](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-policy)
- [パスワード ライトバックとは](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-writeback)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-sspr-howitworks"} -->
## セルフサービス パスワード リセットの詳細解説 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-howitworks
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: セルフサービス パスワード リセットのしくみ

Microsoft Entra セルフサービス パスワード リセット (SSPR) により、ユーザーは、管理者やヘルプ デスクが関与することなく、自分のパスワードを変更またはリセットできるようになります。 ユーザーのアカウントがロックされている場合、またはパスワードを忘れた場合は、プロンプトに従ってブロックを解除し、作業に戻ることができます。 この機能により、ユーザーが自分のデバイスまたはアプリケーションにサインインできない場合のヘルプ デスクの呼び出しと生産性の低下が軽減されます。 [Microsoft Entra ID で SSPR を有効にして構成する方法については、](https://www.youtube.com/watch?v=rA8TvhNcCvQ)このビデオをお勧めします。

Von Bedeutung

この概念記事では、セルフサービス パスワード リセットのしくみについて管理者に説明します。 既にセルフサービス パスワード リセットの登録が済んでいて、自分のアカウントに戻る必要があるエンド ユーザーは、 https://aka.ms/sspr にアクセスしてください。

ユーザーが自分でパスワードをリセットする機能が IT チームによって有効にされていない場合は、ヘルプデスクに連絡して追加のサポートを依頼してください。

### パスワード リセット プロセスのしくみ

ユーザーは [、SSPR ポータル](https://aka.ms/sspr)を使用してパスワードをリセットまたは変更できます。 また、[SSPR for Administrators]\(管理者の SSPR\) を強調表示するには、SSPR はエンド ユーザー専用であるため、既定ではテナントで有効になっていません。 最初に、必要な認証方法を登録する必要があります。 ユーザーが SSPR ポータルにアクセスすると、Microsoft Entra プラットフォームでは次の要因が考慮されます。

- ページをローカライズする方法
- ユーザー アカウントは有効ですか?
- ユーザーはどの組織に属していますか?
- ユーザーのパスワードはどこで管理されますか?

ユーザーがアプリケーションまたはページから **[アカウントにアクセスできない** ] リンクを選択するか、直接 [https://aka.ms/sspr](https://passwordreset.microsoftonline.com)に移動すると、SSPR ポータルで使用される言語は次のオプションに基づいています。

- 既定では、ブラウザーのロケールは、適切な言語で SSPR を表示するために使用されます。 パスワード リセット エクスペリエンスは、 [Microsoft 365 でサポート](https://support.microsoft.com/office/what-languages-is-office-available-in-26d30382-9fba-45dd-bf55-02ab03e2a7ec)されているのと同じ言語にローカライズされています。
- 特定のローカライズされた言語で SSPR にリンクする場合は、パスワード リセット URL の末尾に必要なロケールと共に `?mkt=`を追加します。
    - たとえば、スペイン語 * のes-us* ロケールを指定するには、 `?mkt=es-us` - https://passwordreset.microsoftonline.com/?mkt=es-usを使用します。

SSPR ポータルが必要な言語で表示されると、ユーザーはユーザー ID を入力して captcha を渡すように求められます。 Microsoft Entra ID は、次のチェックを実行して、ユーザーが SSPR を使用できることを確認するようになりました。

- ユーザーが SSPR を有効にしていることを確認します。
    - ユーザーが SSPR を有効にしていない場合、ユーザーは管理者に連絡してパスワードをリセットするように求められます。
- ユーザーが管理者ポリシーに従って自分のアカウントで定義されている適切な認証方法を持っていることを確認します。
    - ポリシーで必要な方法が 1 つだけの場合は、管理者ポリシーによって有効になっている認証方法の少なくとも 1 つに対してユーザーに適切なデータが定義されていることを確認します。
        - 認証方法が構成されていない場合、ユーザーは管理者に連絡してパスワードをリセットすることをお勧めします。
    - ポリシーに 2 つの方法が必要な場合は、管理者ポリシーによって有効になっている認証方法のうち少なくとも 2 つについて、ユーザーに適切なデータが定義されていることを確認します。
        - 認証方法が構成されていない場合、ユーザーは管理者に連絡してパスワードをリセットすることをお勧めします。
    - Microsoft Entra管理者ロールがユーザーに割り当てられている場合は、強力な 2 ゲート パスワード ポリシーが適用されます。 詳細については、「 [管理者リセット ポリシーの相違点](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-policy#administrator-reset-policy-differences)」を参照してください。
- Microsoft Entra テナントがフェデレーション認証、パススルー認証、パスワード ハッシュ同期を使用している場合など、ユーザーのパスワードがオンプレミスで管理されているかどうかを確認します。
    - SSPR ライトバックが構成されていて、ユーザーのパスワードがオンプレミスで管理されている場合、ユーザーはパスワードの認証とリセットを続行できます。
    - SSPR ライトバックがデプロイされておらず、ユーザーのパスワードがオンプレミスで管理されている場合、ユーザーは管理者に連絡してパスワードをリセットするように求められます。

上記のすべてのチェックが正常に完了した場合、ユーザーはパスワードをリセットまたは変更するプロセスを案内されます。

注

SSPR は、パスワード リセット プロセスの一環としてユーザーに電子メール通知を送信する場合があります。 これらの電子メールは、複数のリージョンでアクティブ/アクティブ モードで動作する SMTP リレー サービスを使用して送信されます。

SMTP リレー サービスは、電子メール本文を受信して処理しますが、保存しません。 顧客から提供された情報を含む可能性がある SSPR 電子メールの本文は、SMTP リレー サービス ログに格納されません。 ログにはプロトコル メタデータのみが含まれます。

SSPR の使用を開始するには、次のチュートリアルを完了します。

### サインイン時にユーザーに登録を要求する

ユーザーが先進認証または Web ブラウザーを使用して Microsoft Entra ID を使用して任意のアプリケーションにサインインする場合に、SSPR 登録の完了を要求するオプションを有効にすることができます。 このワークフローには、次のアプリケーションが含まれています。

- Microsoft 365
- Microsoft Entra 管理センター
- アクセス パネル
- 連合アプリケーション
- Microsoft Entra ID を使用したカスタム アプリケーション

登録を必要としない場合、ユーザーはサインイン中にメッセージを表示されませんが、手動で登録できます。 ユーザーは、https://aka.ms/ssprsetupにアクセスするか、アクセス パネルの [**プロファイル**] タブの [**パスワード リセットに登録**] リンクを選択できます。

[Image: Microsoft Entra ID のパスワード リセット登録のスクリーンショット。]

注

ユーザーは、 **キャンセル** を選択するか、ウィンドウを閉じることで、SSPR 登録ポータルを閉じることができます。 ただし、登録が完了するまで、サインインするたびに登録するように求められます。

SSPR に登録するこの割り込みは、ユーザーが既にサインインしている場合、ユーザーの接続を中断しません。

### 認証情報を再確認する

ユーザーに対して、一定期間後に認証情報の確認を要求できます。 このオプションは、[ **サインイン時にユーザーの登録を必須** にする] オプションを有効にした場合にのみ使用できます。

ユーザーに認証情報の確認を求める有効な値は *0* から *730* 日です。 この値を *0* に設定すると、ユーザーは認証情報の確認を求められることはありません。 ユーザーは、情報を再確認する前にサインインする必要があります。

注

SSPR で複数の認証方法が必要な場合、メソッドを削除したユーザーは、ユーザーが認証情報の再確認を **求められるまでの日数に達するまで、認証情報を確認する**必要はありません。

### 認証方法

ユーザーが SSPR を有効にした場合、少なくとも 1 つの認証方法を登録する必要があります。 ユーザーが必要なときに 1 つの方法にアクセスできない場合に柔軟性を高めるために、2 つ以上の認証方法を選択することを強くお勧めします。 詳細については、「[認証方法とは](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)」を参照してください。

SSPR で使用できる認証方法は次のとおりです。

- [Microsoft Authenticator プッシュ通知](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-authenticator-app#mfa-via-notifications-through-mobile-app)
- [ハードウェア OATH トークン (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-oath-tokens#hardware-oath-tokens-preview)
- [ソフトウェア OATH トークン](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-oath-tokens#software-oath-tokens)
- [ショート メッセージ サービス (SMS) サインイン](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-sms-signin)
- [音声通話](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-phone-options)
- メールOTP

ユーザーは、管理者が有効にした認証方法を登録した場合にのみ、パスワードをリセットできます。

Warnung

「*管理者リセット ポリシーの相違点*」セクションで定義されている方法を使用するには、Azure [管理者](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-policy#administrator-reset-policy-differences)ロールが割り当てられたアカウントが必要です。

[Image: Microsoft Entra ID の認証方法ポリシーのスクリーンショット。]

#### 必要な認証方法の数

ユーザーがパスワードをリセットまたはロック解除するために指定する必要がある使用可能な認証方法の数を構成できます。 この値は、 *1 つまたは* *2 つに*設定できます。

ユーザーは、1 つの方法にアクセスできない場合に別の方法でサインインできるように、複数の認証方法を登録する必要があります。

ユーザーが必要なメソッドの最小数を登録しない場合、SSPR を使用しようとするとエラー ページが表示されます。 管理者にパスワードのリセットを要求する必要があります。 詳細については、「 認証方法の変更」を参照してください。

##### モバイル アプリと SSPR

Microsoft Authenticator などのパスワード リセットの方法としてモバイル アプリを使用する場合、組織が [一元化された認証方法ポリシーに移行](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-methods-manage)していない場合は、次の考慮事項が適用されます。

- 管理者がパスワードのリセットに 1 つの方法を使用する必要がある場合は、確認コードのみが使用可能なオプションです。
- 管理者がパスワードのリセットに 2 つの方法を使用する必要がある場合、ユーザーは、他の有効な方法に加えて、通知 **または** 確認コードを使用できます。

| リセットに必要なメソッドの数 | 1 | 2 |
| --- | --- | --- |
| モバイル アプリの機能を利用できる | Code | コードまたは通知 |

ユーザーは、 https://aka.ms/mfasetupで、または https://aka.ms/setupsecurityinfoの統合セキュリティ情報登録でモバイル アプリを登録できます。

Von Bedeutung

1 つの方法のみが必要な場合、認証方法として認証子を選択することはできません。 同様に、2 つのメソッドが必要な場合は、Authenticator と追加のメソッドを 1 つだけ選択できません。

認証アプリをメソッドとして含む SSPR ポリシーを構成する場合は、1 つの方法が必要な場合は少なくとも 1 つの追加メソッドを選択し、2 つの方法を構成するときに少なくとも 2 つの方法を選択する必要があります。

#### 認証方法を変更する

リセットまたはロック解除に必要な認証方法が 1 つしか登録されていないポリシーから始めて、2 つの方法に変更するとどうなりますか?

| 登録されたメソッドの数 | 必要なメソッドの数 | 結果 |
| --- | --- | --- |
| 1 つ以上 | 1 | リセットまたはロック解除**が可能** |
| 1 | 2 | リセットまたはロック解除**できない** |
| 2 つ以上 | 2 | リセットまたはロック解除**が可能** |

使用可能な認証方法を変更すると、ユーザーに問題が発生する可能性もあります。 使用できる認証方法を変更した場合、使用可能な最小限のデータ量を持たないユーザーは SSPR を使用できません。

次のシナリオ例について考えてみます。

1. 元のポリシーは、必要な 2 つの認証方法で構成されます。 オフィスの電話番号とセキュリティの質問のみを使用します。
2. 管理者は、セキュリティの質問を使用しなくなったポリシーを変更しますが、携帯電話と代替メールの使用を許可します。
3. 携帯電話または代替メール フィールドが設定されていないユーザーは、パスワードをリセットできません。

### 通知

パスワード イベントの認識を向上させるために、SSPR では、ユーザーと ID 管理者の両方に通知を構成できます。

#### [パスワードのリセットについてユーザーに通知しますか]

このオプションが **[はい**] に設定されている場合、パスワードをリセットしたユーザーは、パスワードが変更されたことを通知する電子メールを受け取ります。 電子メールは、SSPR ポータルを介して、Microsoft Entra ID に格納されているプライマリ電子メール アドレスと代替メール アドレスに送信されます。 プライマリまたは代替の電子メール アドレスが定義されていない場合、SSPR はユーザー ユーザー プリンシパル名 (UPN) を介して電子メール通知を試みます。 リセット イベントが他のユーザーに通知されません。

#### 他の管理者が自分のパスワードをリセットしたときにすべての管理者に通知する

このオプションが **[はい**] に設定されている場合、グローバル管理者は、Microsoft Entra ID に格納されているプライマリ 電子メール アドレスに電子メールを受信します。 電子メールは、別の管理者が SSPR を使用してパスワードを変更したことを通知します。

注

SSPR サービスからの電子メール通知は、使用している Azure クラウドに基づいて、次のアドレスから送信されます。

- パブリック: msonlineservicesteam@microsoft.com、 msonlineservicesteam@microsoftonline.com
- 21Vianet が運営する Microsoft Azure (中国の Azure): msonlineservicesteam@oe.21vianet.com、 21Vianetonlineservicesteam@21vianet.com
- 米国政府機関向け Azure: msonlineservicesteam@azureadnotifications.us、 msonlineservicesteam@microsoftonline.us

通知の受信で問題が発生した場合は、スパム設定を確認してください。

カスタム管理者に通知メールを受信させる場合は、SSPR のカスタマイズを使用し、 [カスタム ヘルプデスク リンクまたは電子メールを設定](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr#set-up-notifications-and-customizations)します。

### オンプレミスの統合

ハイブリッド環境では、Microsoft Entra Connect クラウド同期を構成して、パスワード変更イベントを Microsoft Entra ID からオンプレミス ディレクトリに書き戻すことができます。

[Image: Microsoft Entra ID からオンプレミス統合へのパスワード ライトバックが有効になっている状態のスクリーンショット。]

Microsoft Entra ID は、現在のハイブリッド接続を確認し、Microsoft Entra 管理センターでメッセージを提供します。 考えられるエラーの解決に関するヘルプについては、「 [Microsoft Entra Connect のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/authentication/troubleshoot-sspr-writeback)」を参照してください。

SSPR 書き戻しの使用を開始するには、次のチュートリアルをご覧ください。

#### オンプレミス のディレクトリにパスワードを書き戻す

Microsoft Entra 管理センターを使用してパスワード ライトバックを有効にすることができます。 Microsoft Entra Connect を再構成しなくても、パスワード ライトバックを一時的に無効にすることもできます。

- オプションが**はい**に設定されている場合、ライトバックが有効になります。 フェデレーション認証、パススルー認証、またはパスワード ハッシュ同期ユーザーは、自分のパスワードをリセットできます。
- オプションが**いいえ**に設定されている場合、ライトバックは無効になります。 フェデレーション認証、パススルー認証、またはパスワード ハッシュ同期ユーザーは、自分のパスワードをリセットできません。

#### ユーザーが自分のパスワードをリセットせずにアカウントのロックを解除できるようにする

既定では、Microsoft Entra ID はパスワード リセットを実行するときにアカウントをロック解除します。 柔軟性を提供するために、ユーザーがパスワードをリセットしなくても、オンプレミスアカウントのロックを解除できるようにすることができます。 この設定を使用して、これら 2 つの操作を分離します。

- **[はい**] に設定すると、ユーザーはパスワードをリセットしてアカウントのロックを解除するか、パスワードをリセットせずにアカウントのロックを解除することができます。
- **[いいえ**] に設定すると、ユーザーはパスワードリセットとアカウントのロック解除操作を組み合わせた操作のみを実行できます。

#### オンプレミスの Active Directory パスワード フィルター

SSPR は、Active Directory で管理者が開始したパスワード リセットと同等の処理を実行します。 サード パーティのパスワード フィルターを使用してカスタム パスワード規則を適用し、Microsoft Entra のセルフサービス パスワード リセット中にこのパスワード フィルターをチェックする必要がある場合は、管理者パスワード リセット シナリオで適用するようにサードパーティのパスワード フィルター ソリューションが構成されていることを確認します。 [Active Directory Domain Services の Microsoft Entra パスワード保護](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-on-premises) は、既定でサポートされています。

### B2B ユーザーのパスワード リセット

パスワードのリセットと変更は、すべての企業間 (B2B) 構成で完全にサポートされています。 B2B ユーザー パスワードリセットは、次の 3 つのケースでサポートされます。

- **既存の Microsoft Entra テナントを持つパートナー組織のユーザー**: パートナーが Microsoft Entra テナントを持っている場合は、そのテナントで有効になっているパスワード リセット ポリシーが尊重されます。 パスワードのリセットを機能させるには、パートナー組織は Microsoft Entra SSPR が有効になっていることを確認する必要があります。 Microsoft 365 のお客様に対するその他の料金は発生しません。
- **セルフサービス サインアップを通じてサインアップするユーザー**: パートナーが [セルフサービス サインアップ](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-self-service-signup) 機能を使用してテナントに入った場合は、登録したメールでパスワードをリセットできます。
- **B2B ユーザー**: 新しい [Microsoft Entra B2B 機能](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) を使用して作成された新しい B2B ユーザーは、招待プロセス中に登録したメールでパスワードをリセットすることもできます。

このシナリオをテストするには、これらのパートナー ユーザーの 1 人と `https://passwordreset.microsoftonline.com` に移動します。 ユーザーが代替メールまたは認証メールを定義した場合、パスワードのリセットは想定どおりに機能します。

注

Hotmail.com、Outlook.com、その他の個人用メール アドレスなど、Microsoft Entra テナントへのゲスト アクセスが許可されている Microsoft アカウントは、Microsoft Entra SSPR を使用できません。 詳細については、「 [Microsoft アカウントにサインインできない場合」を参照してください](https://support.microsoft.com/help/12429/microsoft-account-sign-in-cant)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-sspr-licensing"} -->
## ライセンスセルフサービスパスワードリセット - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-licensing
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra のセルフサービス パスワード リセット ライセンス要件の違いについて説明します

ユーザーがデバイスまたはアプリケーションにサインインできない場合のヘルプ デスクの呼び出しと生産性の低下を減らすために、Microsoft Entra ID のユーザー アカウントでセルフサービス パスワード リセット (SSPR) を有効にすることができます。 SSPR を構成する機能には、パスワードの変更、リセット、ロック解除、オンプレミス ディレクトリへの書き戻しが含まれます。 基本的な SSPR 機能は、Microsoft 365 Business Standard 以降およびすべての Microsoft Entra ID P1 または P2 SKU で無償で利用できます。

この記事では、セルフサービス パスワード リセットのライセンス付与と使用に関するさまざまな方法について詳しく説明します。 価格と課金の詳細については、Microsoft Entra の価格ページを参照してください。

一部のライセンスのないユーザーは技術的には SSPR にアクセスできる場合がありますが、サービスの恩恵を受ける予定のユーザーにはライセンスが必要です。

手記

一部のテナント サービスでは、現在、特定のユーザーに特典を制限することはできません。 サービス特典をライセンスを持つユーザーに制限する作業を行う必要があります。 これにより、ターゲット機能を利用できるようになると、組織のサービス中断の可能性を回避できます。

### エディションと機能を比較する

次の表は、パスワードの変更、リセット、またはオンプレミスのライトバックに関するさまざまな SSPR シナリオと、この機能を提供する SKU の概要を示しています。

| 特徴 | Microsoft Entra ID Free（無料） | Microsoft 365 Business Standard | Microsoft 365 Business Premium | Microsoft Entra ID P1 または P2 |
| --- | --- | --- | --- | --- |
| **クラウド専用のユーザー パスワード変更**Microsoft Entra ID のユーザーが自分のパスワードを知っていて、新しいものに変更する場合。 | ● | ● | ● | ● |
| **クラウドオンリー ユーザーのパスワード リセット**Microsoft Entra ID のユーザーがパスワードを忘れ、リセットする必要がある場合。 |  | ● | ● | ● |
| **オンプレミスの書き戻しを含む、ハイブリッド ユーザーのパスワードの変更またはリセット**Microsoft Entra Connect を使用してオンプレミスのディレクトリから同期された Microsoft Entra のユーザーが、パスワードを変更またはリセットし、新しいパスワードをオンプレミスに書き戻す必要がある場合。 |  |  | ● | ● |

警告

スタンドアロンの Microsoft 365 Basic および Standard ライセンス プランでは、オンプレミスのライトバックを使用した SSPR はサポートされていません。 オンプレミスの書き戻し機能には、Microsoft Entra ID P1、Premium P2、または Microsoft 365 Business Premium が必要です。

コストなどの追加のライセンス情報については、次のページを参照してください。

- [セキュリティ & コンプライアンスのための Microsoft 365 ライセンス ガイダンス](https://learn.microsoft.com/ja-jp/office365/servicedescriptions/microsoft-365-service-descriptions/microsoft-365-tenantlevel-services-licensing-guidance/microsoft-365-security-compliance-licensing-guidance)
- [Microsoft Entra の価格](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)
- [Microsoft Entra の機能と能力](https://www.microsoft.com/cloud-platform/azure-active-directory-features)
- [エンタープライズ モビリティ + セキュリティ](https://www.microsoft.com/cloud-platform/enterprise-mobility-security)
- [Microsoft 365 Enterprise](https://www.microsoft.com/microsoft-365/enterprise)
- [Microsoft 365 Business](https://learn.microsoft.com/ja-jp/office365/servicedescriptions/office-365-service-descriptions-technet-library)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-sspr-policy"} -->
## セルフサービス パスワード リセット ポリシー - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-policy
- Service: entra-id / authentication
- Article date: 2026-05-26
- Summary: パスワードの複雑さの要件、管理者のリセット ポリシー、パスワードの有効期限の設定など、Microsoft Entraセルフサービス パスワード リセット (SSPR) ポリシー オプションについて説明します。

Microsoft Entra ID には、パスワードの複雑さ、長さ、有効期間などの設定を定義するパスワード ポリシーがあります。 また、ユーザー名に使用できる文字と長さを定義するポリシーもあります。

セルフサービス パスワード リセット (SSPR) を使用して Microsoft Entra ID 内のパスワードを変更またはリセットする場合は、パスワード ポリシーが確認されます。 パスワードがポリシーの要件を満たしていない場合、ユーザーは再試行するように求められます。 Microsoft Entra管理者には、通常のユーザー アカウントとは異なる SSPR の使用に関するいくつかの制限があり、Microsoft Entra IDの試用版と無料版には軽微な例外があります。

この記事では、ユーザー アカウントに関連付けられたパスワード ポリシー設定と複雑さの要件について説明します。 また、PowerShell を使用してパスワードの有効期限設定を確認または設定する方法についても説明します。

### ユーザー名ポリシー

Microsoft Entra ID にサインインする必要があるユーザー アカウントはいずれも、一意のユーザー プリンシパル名 (UPN) 属性値がそのアカウントに関連付けられている必要があります。 Microsoft Entra Connect を使用して Microsoft Entra ID に同期されたオンプレミスの Active Directory ドメイン サービス環境を持つハイブリッド環境では、既定で Microsoft Entra ID UPN はオンプレミスの UPN に設定されます。

次の表は、Microsoft Entra ID に同期されているオンプレミスの Microsoft Entra ID アカウントと、Microsoft Entra ID で直接作成されたクラウド専用のユーザー アカウントの両方に適用されるユーザー名ポリシーの概要を示しています。

| プロパティ | UserPrincipalName の要件 |
| --- | --- |
| 使用できる文字 | A-Za - z0-9'。 - \_ ! #^~ |
| 使用できない文字 | ユーザー名とドメインの間以外にある '@' 文字。ピリオド文字 '.' を '@' 記号の直前に含めることはできません |
| 長さの制限 | 全体の長さは 113 文字以内にする必要があります'@' 記号の前に最大 64 文字まで可能'@' 記号の後に最大 48 文字まで可能 |

### Microsoft Entra のパスワード ポリシー

パスワード ポリシーは、Microsoft Entra ID で直接作成および管理されるすべてのユーザーおよび管理者のアカウントに適用されます。 これらのパスワード ポリシー設定の一部は変更できませんが、[Microsoft Entra のパスワード保護用のカスタム禁止パスワード](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-configure-custom-password-protection)またはアカウント ロックアウト パラメーターを構成することができます。

既定では、間違ったパスワードを使用して 10 回サインインに失敗すると、アカウントはロックアウトされます。 ユーザーは 1 分間ロックされます。 さらに不正なサインインを試行すると、ロックアウト期間が長くなります。 [スマート ロックアウト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout)では、直近 3 つの無効なパスワード ハッシュを追跡して、同じパスワードに対するロックアウト カウンターの増分を回避します。 同じ無効なパスワードが複数回入力されても、ロックアウトされることはありません。スマート ロックアウトのしきい値と期間を定義できます。

次の Microsoft Entra パスワード ポリシー オプションが定義されています。 特に明記されていない場合、これらの設定を変更することはできません。

| プロパティ | 要件 |
| --- | --- |
| 使用できる文字 | A-Za - z0-9@ # $ % ^ & \* - \_ ! + = [ ] { } |\ : ' , . ? / ` ~ " ( ) ; &lt;&gt;空白 |
| 使用できない文字 | Unicode 文字 |
| パスワードの制限 | 8 文字以上 256 文字以下。次の 4 種類の文字のうち 3 つが必要です。- 小文字- 大文字- 数値 (0 から 9)- 記号 (上述のパスワードの制限を参照してください) |
| パスワードの有効期間 (パスワードの最大有効期間) | 既定値: **有効期限なし**。 テナントが 2021 より前に作成された場合、既定では **90** 日間の有効期限の値が設定されます。 現在のポリシーは [Get-MgDomain](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdomain) を使用して確認できます。この値は、PowerShell 用の Microsoft Graph PowerShell モジュールの [Update-MgDomain](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/update-mgdomain) コマンドレットを使って構成できます。 |
| パスワードの有効期限 (パスワードを無期限にします) | 既定値: **false** (パスワードの有効期限が指定されていることを示します)。各ユーザー アカウントの値を構成するには、[Update-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/update-mguser) コマンドレットを使用します。 |
| パスワード変更履歴 | ユーザーがパスワードを変更する場合、前回のパスワードを再度使用することは*できません*。 |
| パスワード リセット履歴 | ユーザーが忘れたパスワードをリセットする場合、前回のパスワードを再度使用することが ''*できます*''。 |

Von Bedeutung

パスワード変更履歴は、パスワード ライトバックに適用されます。 クラウド内のユーザーの場合のみ、Microsoft Entra IDのパスワードのリセットにはユーザーの古いパスワードがないため、パスワードの再利用を確認したり防止したりすることはできません。

*EnforceCloudPasswordPolicyForPasswordSyncedUsers* を有効にすると、Microsoft Entra のパスワード ポリシーは、Microsoft Entra Connect を使用してオンプレミスから同期されたユーザー アカウントに対して適用されます。 さらに、ユーザーがオンプレミスでパスワードを変更して Unicode 文字を含めると、オンプレミスではパスワードの変更が成功しても、Microsoft Entra ID では成功しない可能性があります。 Microsoft Entra Connect でパスワード ハッシュ同期が有効になっている場合でも、ユーザーはクラウド リソースのアクセス トークンを受け取ることができます。 ただし、テナントが[ユーザー リスクベースのパスワード変更](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-risk-based-user)を有効にした場合、パスワードの変更は高リスクとして報告されます。

ユーザーは、パスワードをもう一度変更するように求められます。 ただし、変更に Unicode 文字が含まれている場合は、[スマート ロックアウト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout)も有効になっていると、ロックアウトされる可能性があります。

### リスクベースのパスワード リセット ポリシーの制限事項

[EnforceCloudPasswordPolicyForPasswordSyncedUsers](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-risk-based-user) を有効にすると、リスクが高い場合にクラウド パスワードを変更する必要があります。 ユーザーは、Microsoft Entra ID にサインインするときにパスワードを変更するように求められます。 新しいパスワードは、クラウドとオンプレミスの両方のパスワード ポリシーに準拠している必要があります。

パスワードの変更がオンプレミスの要件を満たしているがクラウドの要件を満たしていない場合、パスワード ハッシュ同期が有効になっていればパスワードの変更は成功します。 たとえば、新しいパスワードに Unicode 文字が含まれている場合、パスワードの変更はオンプレミスでは更新できますが、クラウドでは更新できません。

パスワードがクラウド パスワード要件に準拠していない場合、クラウドで更新されず、アカウントのリスクは減少しません。 ユーザーは引き続きクラウド リソースのアクセス トークンを受け取りますが、次回クラウド リソースにアクセスするときに再度パスワードを変更するように求められます。 選択したパスワードがクラウドの要件を満たしていないことを示すエラーや通知はユーザーには表示されません。

### 管理者リセット ポリシーの相違点

既定で、管理者アカウントはセルフサービスのパスワード リセットが有効になっており、強力な既定の *2 ゲート* パスワード リセット ポリシーが適用されます。 このポリシーは、ユーザーに対して定義したポリシーとは異なる場合があり、このポリシーを変更することはできません。 パスワード リセット機能は、Microsoft Entra管理者ロールが割り当てられていなくても、常にユーザーとしてテストする必要があります。

2 ゲート ポリシーでは、電子メール アドレス、認証アプリ、電話番号などの 2 つの認証データが必要となり、セキュリティの質問は禁止されます。 Office とモバイルの音声通話は、Microsoft Entra ID の試用版または無料版でも禁止されています。

SSPR 管理者ポリシーは、認証方法ポリシーに依存しません。 たとえば、認証方法ポリシーでサード パーティ製ソフトウェア トークンを無効にした場合でも、管理者アカウントはサード パーティのソフトウェア トークン アプリケーションを登録して使用できますが、SSPR の場合のみ使用できます。

2 ゲート ポリシーは次のような状況で適用されます。

- 次の管理者ロールが影響を受ける。

    | ロール A ~ D | ロール D ~ N | ロール O ~ Y |
    | --- | --- | --- |
    | アドホック ライセンス管理者 | Dynamics 365 管理者 | Office アプリ管理者 |
    | アプリケーション管理者 | Dynamics 365 Business Central 管理者 | 組織ブランド管理者 |
    | アプリケーション プロキシ サービス管理者 | Edge 管理者 | パートナー レベル 1 のサポート |
    | 攻撃のシミュレーションの管理者 | メールで確認済みのユーザー作成者 | パートナー レベル 2 のサポート |
    | 属性割り当て管理者 | Exchange 管理者 | パスワード管理者 |
    | 属性定義管理者 | Exchange 受信者管理者 | アクセス許可管理管理者 |
    | 属性ログ管理者 | 外部IDユーザーフロー管理者 | Power BI サービス管理者 |
    | 認証管理者 | 外部 ID ユーザー フロー属性管理者 | Power Platform 管理者 |
    | 認証機能拡張管理者 | 外部 ID プロバイダー管理者 | プリンター管理者 |
    | 認証ポリシー管理者 | グローバル管理者 | 特権認証管理者 |
    | Azure DevOps 管理者 | グローバルセキュリティで保護されたアクセス管理者 | 特権ロール管理者 |
    | Azure Information Protection 管理者 | グループ管理者 | 検索管理者 |
    | B2C IEF キーセット管理者 | ヘルプデスク管理者 | セキュリティ管理者 |
    | B2C IEF ポリシー管理者 | ハイブリッド ID の管理者 | サービス サポート管理者 |
    | 課金管理者 | アイデンティティガバナンス管理者 | SharePoint 管理者 |
    | Cloud App Security 管理者 | Insights 管理者 | Skype for Business 管理者 |
    | クラウド デバイス管理者 | Intune 管理者 | Teams 管理者 |
    | コンプライアンス管理者 | ナレッジ管理者 | Teams 通信管理者 |
    | コンプライアンス データ管理者 | ライセンス管理者 | Teams デバイス管理者 |
    | 条件付きアクセス管理者 | ライフサイクル ワークフロー管理者 | ユーザー管理者 |
    | カスタマーロックボックス アクセス承認者 | メールボックス管理者 | 仮想訪問管理者 |
    | デスクトップアナリティクス管理者 | Microsoft Entra 参加済みデバイスのローカル管理者 | Viva Goals 管理者 |
    | デバイス管理者 | Microsoft ハードウェア保証管理者 | ビバパルス管理者 |
    | ディレクトリ同期アカウント | Microsoft 365 移行管理者 | Windows365 管理者 |
    | ディレクトリ ライター | Modern Commerce 管理者 | ウィンドウズ アップデート デプロイ管理者 |
    | ドメイン名管理者 | ネットワーク管理者 | Yammer 管理者 |
- 評価版サブスクリプションで 30 日間が経過した

    -または-
- Microsoft Entra テナント用に、*contoso.com* のようなカスタム ドメインが構成されている

    -または-
- Microsoft Entra Connect がオンプレミスのディレクトリからの ID を同期している

テナント承認ポリシーの `AllowedToUseSspr` プロパティの値を `false` に設定することで、管理者アカウントに対する SSPR の使用を無効にすることができます。 管理者アカウントの SSPR を有効または無効にするポリシーの変更では、反映に最大で 60 分かかることがあります。

Von Bedeutung

管理者のパスワード リセット ポリシーが無効になっている場合、ユーザーのパスワード リセット ポリシーのスコープ内にある場合でも、管理者は SSPR を使用してパスワードをリセットできません。 SSPR 登録が有効になっていて、管理者がユーザーのパスワード リセット ポリシーに含まれている場合、登録を求められますが、メソッドを登録できないことを示すメッセージが表示されます。 このエクスペリエンスを回避するには、管理者のパスワード リセット ポリシーが無効になっている場合に、ユーザーのパスワード リセット ポリシーから管理者を明示的に除外します。

## [PowerShell](#tab/ms-powershell)
[Update-MgPolicyAuthorizationPolicy](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/update-mgpolicyauthorizationpolicy)

```powershell
Connect-MgGraph -Scopes Policy.ReadWrite.Authorization
Update-MgPolicyAuthorizationPolicy -AllowedToUseSspr:$false
```

## [Microsoft Graph](#tab/ms-graph)
[承認ポリシーの更新](https://learn.microsoft.com/ja-jp/graph/api/authorizationpolicy-update)

```http
PATCH https://graph.microsoft.com/v1.0/policies/authorizationPolicy
{
  "allowedToUseSSPR":false
}
```

---

#### 例外

1 ゲート ポリシーには、1 つの認証データが必要です。電子メール アドレスまたは電話番号などです。 1 ゲート ポリシーは次のような状況で適用されます。

- 試用版サブスクリプションの最初の 30 日以内である

    -または-
- カスタム ドメインが構成されておらず (テナントで既定の \**.onmicrosoft.com* を使用している。これは運用環境での使用はお勧めしません)、Microsoft Entra Connect で ID を同期していない。

### パスワードの有効期限のポリシー

[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)は、[Microsoft Graph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/) を使用して、ユーザーのパスワードを有効期限が切れないように設定できます。

また、PowerShell コマンドレットを使用すると、期限が切れない構成を削除したり、期限が切れないように設定されているユーザー パスワードを確認したりすることもできます。

このガイダンスは、Intune や Microsoft 365 などの他のプロバイダーに適用され、これらは ID およびディレクトリ サービスについては Microsoft Entra ID にも依存します。 パスワード有効期限が、ポリシーの変更できる唯一の部分です。

注

既定では、期限切れにならないように設定できるのは、Microsoft Entra Connect による同期を行っていないユーザー アカウントのパスワードのみです。 ディレクトリ同期の詳細については、「[AD と Microsoft Entra ID を接続する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization#password-expiration-policy)」を参照してください。

#### PowerShell を使用したパスワード ポリシーの設定または確認

操作を開始するには、[Microsoft Graph PowerShell モジュールをダウンロードしてインストール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)し、[それをお使いの Microsoft Entra テナントに接続](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph)します。

モジュールがインストールされたら、次の手順を使用して、各タスクを必要に応じて完了します。

#### パスワードの有効期限ポリシーを確認する

1. PowerShell プロンプトを開き、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph)として [Microsoft Entra テナントに接続](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)します。
2. 個々のユーザーまたはすべてのユーザーに対して、次のいずれかのコマンドを実行します。

    - 1 人のユーザーのパスワードが無期限に設定されているかどうかを確認するには、次のコマンドレットを実行します。 `<user ID>` を、確認したいユーザーのユーザー ID ( など) に置き換えます。

        ```powershell
        Get-MgUser -UserId <user ID> -Property UserPrincipalName, PasswordPolicies | Select-Object @{N="PasswordNeverExpires";E={$_.PasswordPolicies -contains "DisablePasswordExpiration"}}
        ```
    - すべてのユーザーについて**パスワードを無期限にする**設定を表示するには、次のコマンドレットを実行します。

        ```powershell
        Get-MgUser -All -Property UserPrincipalName, PasswordPolicies | Select-Object UserPrincipalName, @{N="PasswordNeverExpires";E={$_.PasswordPolicies -contains "DisablePasswordExpiration"}}
        ```

#### パスワードを期限付きに設定する

1. PowerShell プロンプトを開き、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph)として [Microsoft Entra テナントに接続](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)します。
2. 個々のユーザーまたはすべてのユーザーに対して、次のいずれかのコマンドを実行します。

    - 1 人のユーザーのパスワードを期限付きに設定するには、次のコマンドレットを実行します。 `<user ID>` を、確認したいユーザーのユーザー ID ( など) に置き換えます。

        ```powershell
        Update-MgUser -UserId <user ID> -PasswordPolicies None
        ```
    - 組織内のすべてのユーザーのパスワードを期限付きに設定するには、次のコマンドを使用します。

        ```powershell
        Get-MgUser -All | foreach $_ { Update-MgUser -UserId $_.Id -PasswordPolicies None }
        ```

#### パスワードを無期限に設定する

1. PowerShell プロンプトを開き、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph)として [Microsoft Entra テナントに接続](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)します。
2. 個々のユーザーまたはすべてのユーザーに対して、次のいずれかのコマンドを実行します。

    - 1 人のユーザーのパスワードを無期限に設定するには、次のコマンドレットを実行します。 `<user ID>` を、確認したいユーザーのユーザー ID ( など) に置き換えます。

        ```powershell
        Update-MgUser -UserId <user ID> -PasswordPolicies DisablePasswordExpiration
        ```
    - 組織内のすべてのユーザーのパスワードを無期限に設定するには、次のコマンドレットを実行します。

        ```powershell
        Get-MgUser -All | foreach $_ { Update-MgUser -UserId $_.Id -PasswordPolicies DisablePasswordExpiration }
        ```

    警告

    `-PasswordPolicies DisablePasswordExpiration` を設定したパスワードは、引き続き `LastPasswordChangeDateTime` 属性に基づいて使用時間が計測されます。 `LastPasswordChangeDateTime` 属性に基づいて、有効期限を `-PasswordPolicies None` に変更すると、90 日より古い `LastPasswordChangeDateTime` を持つすべてのパスワードは、ユーザーが次回サインインで変更する必要があります。 この変更は多数のユーザーに影響を与える可能性があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-sspr-writeback"} -->
## セルフサービス パスワード リセットを使用したオンプレミス パスワードの書き戻し - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-writeback
- Service: entra-id / authentication
- Article date: 2025-10-25
- Summary: Microsoft Entra ID のパスワード変更またはリセット イベントをオンプレミスのディレクトリ環境に書き戻す方法について説明します

Microsoft Entra セルフサービス パスワード リセット (SSPR) を使用すると、ユーザーはクラウドでパスワードをリセットできますが、ほとんどの企業にはユーザー向けのオンプレミスの Active Directory Domain Services (AD DS) 環境もあります。 パスワード ライトバックを使用すると、 [Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/whatis-hybrid-identity) または [Microsoft Entra Connect クラウド同期](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-cloud-sync-sspr-writeback)を使用して、クラウド内のパスワード変更をオンプレミスのディレクトリにリアルタイムで書き戻すことができます。ユーザーがクラウドで SSPR を使用してパスワードを変更またはリセットすると、更新されたパスワードもオンプレミスの AD DS 環境に書き戻されます。

Von Bedeutung

この概念記事では、セルフサービス パスワード リセット ライトバックのしくみについて管理者に説明します。 既にセルフサービス パスワード リセットの登録が済んでいて、自分のアカウントに戻る必要があるエンド ユーザーは、 https://aka.ms/sspr にアクセスしてください。

ユーザーが自分でパスワードをリセットする機能が IT チームによって有効にされていない場合は、ヘルプデスクに連絡して追加のサポートを依頼してください。

パスワード ライトバックは、次のハイブリッド ID モデルを使用する環境でサポートされています。

- パスワード ハッシュ同期
- [パススルー認証](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta)
- [Active Directory フェデレーション サービス (AD FS)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-management)

注

セキュリティ グループに対して段階的なロールアウトが有効になっている場合、オンプレミス ドメインへの書き戻しを伴う SSPR はサポートされません。 場合によっては機能しますが、段階的ロールアウトが有効になっている場合、SSPR が一貫して動作することを保証することはできません。

パスワード ライトバックには、次の機能が用意されています。

- **オンプレミス Active Directory Domain Services (AD DS) パスワード ポリシーの適用**: ユーザーが自分のパスワードをリセットすると、そのディレクトリにコミットする前に、パスワードがオンプレミスの AD DS ポリシーを満たしていることを確認します。 このレビューには、履歴、複雑さ、有効期間、パスワード フィルター、AD DS で定義したその他のパスワード制限の確認が含まれます。
- **ゼロ遅延フィードバック**: パスワード ライトバックは同期操作です。 パスワードがポリシーを満たしていない場合、または何らかの理由でリセットまたは変更できない場合、ユーザーは直ちに通知を受け取ります。
- **アクセス パネルと Microsoft 365 からのパスワード変更をサポート**します。フェデレーション ユーザーまたはパスワード ハッシュ同期されたユーザーが期限切れまたは期限切れでないパスワードを変更すると、それらのパスワードが AD DS に書き戻されます。
- **管理者が Microsoft Entra 管理センターからパスワード ライトバックをリセットするときにパスワード ライトバックをサポート**します。管理者が [Microsoft Entra 管理センター](https://entra.microsoft.com)でユーザーのパスワードをリセットすると、そのユーザーがフェデレーションまたはパスワード ハッシュ同期されている場合、パスワードはオンプレミスに書き戻されます。 この機能は現在、Office 管理ポータルではサポートされていません。
- **受信ファイアウォール規則は必要ありません**。パスワード ライトバックでは、基になる通信チャネルとして Azure Service Bus リレーが使用されます。 すべての通信はポート 443 経由で送信されます。
- **Microsoft Entra Connect** または[クラウド同期](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr-writeback)を使用した[サイド バイ サイドのドメイン レベルの展開をサポート](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-cloud-sync-sspr-writeback)し、切断されたドメイン内のユーザーを含め、ニーズに応じて異なるユーザー セットをターゲットにします。

注

パスワード ライトバック要求を処理するオンプレミスのサービス アカウントは、保護されたグループに属するユーザーのパスワードを変更できません。 管理者はクラウドでパスワードを変更できますが、パスワード ライトバックを使用して、オンプレミス ユーザーの忘れたパスワードをリセットすることはできません。 保護されたグループの詳細については、「 [AD DS の保護されたアカウントとグループ](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/plan/security-best-practices/appendix-c--protected-accounts-and-groups-in-active-directory)」を参照してください。

SSPR ライトバックの使用を開始するには、次のチュートリアルのいずれかまたは両方を完了します：

- [チュートリアル: セルフサービス パスワード リセット (SSPR) 書き戻しを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr-writeback)
- [チュートリアル:Microsoft Entra Connect クラウド同期のオンプレミス環境へのセルフサービス パスワード リセット ライトバックを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-cloud-sync-sspr-writeback)

### Microsoft Entra Connect とクラウド同期のサイド バイ サイドデプロイ

異なるドメインに Microsoft Entra Connect とクラウド同期をサイド バイ サイドで展開して、さまざまなユーザー セットをターゲットにすることができます。 これにより、会社の合併または分割のためにユーザーが切断されたドメインにいる場合にオプションを追加しながら、既存のユーザーがパスワードの変更を書き戻し続けるのに役立ちます。 Microsoft Entra Connect とクラウド同期は異なるドメインで構成できるため、あるドメインのユーザーは Microsoft Entra Connect を使用でき、別のドメインのユーザーはクラウド同期を使用できます。クラウド同期は、Microsoft Entra Connect の 1 つのインスタンスに依存しないため、可用性を高めることもできます。 2 つのデプロイ オプション間の機能の比較については、「 [Microsoft Entra Connect とクラウド同期の比較](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/connect-to-cloud-sync-decision-guide#comparison-between-microsoft-entra-connect-and-cloud-sync)」を参照してください。

注

Microsoft Entra Connect Cloud Sync と Microsoft Entra Connect Sync が共存し、同じドメインに対して構成されている場合、そのドメインから同期されたユーザーのすべてのパスワード ライトバック操作は Cloud Sync エージェントによって処理されます。

### パスワード ライトバックのしくみ

フェデレーション、パスワード ハッシュ同期 (または Microsoft Entra Connect 展開の場合はパススルー認証) 用に構成されたユーザー アカウントが、クラウド内のパスワードのリセットまたは変更を試みると、次のアクションが実行されます。

1. ユーザーが持っているパスワードの種類を確認するチェックが実行されます。 パスワードがオンプレミスで管理されている場合:

    - 書き戻しサービスが稼働しているかどうかを確認するチェックが実行されます。 その場合、ユーザーは続行できます。
    - ライトバック サービスがダウンしている場合、ユーザーは自分のパスワードを今すぐリセットできないことを通知されます。
2. 次に、ユーザーは適切な認証ゲートを渡し、[ **パスワードのリセット** ] ページに到達します。
3. ユーザーが新しいパスワードを選択し、確認します。
4. ユーザーが **[送信]** を選択すると、プレーンテキスト パスワードは、書き戻しのセットアップ プロセス中に作成された公開キーで暗号化されます。
5. 暗号化されたパスワードは、HTTPS チャネル経由でテナント固有の Service Bus Relay に送信されるペイロードに含まれます (書き戻しのセットアップ プロセス中に設定されます)。 このリレーは、オンプレミスのインストールでのみ認識されるランダムに生成されたパスワードによって保護されます。
6. メッセージがサービス バスに到達すると、パスワード リセット エンドポイントは自動的に起動し、リセット要求が保留中であることを確認します。
7. その後、サービスはクラウド アンカー属性を使用してユーザーを検索します。 この検索を成功させるには、次の条件を満たす必要があります。

    - ユーザー オブジェクトは、AD DS コネクタ スペースに存在する必要があります。
    - ユーザー オブジェクトは、対応するメタバース (MV) オブジェクトにリンクされている必要があります。
    - ユーザー オブジェクトは、対応する Microsoft Entra コネクタ オブジェクトにリンクされている必要があります。
    - AD DS コネクタ オブジェクトから MV へのリンクには、リンクに `Microsoft.InformADUserAccountEnabled.xxx` 同期規則が必要です。

    呼び出しがクラウドから着信すると、同期エンジンは **cloudAnchor** 属性を使用して Microsoft Entra コネクタ スペース オブジェクトを検索します。 次に、MV オブジェクトへのリンクに戻り、AD DS オブジェクトへのリンクに戻ります。 同じユーザーに対して複数の AD DS オブジェクト (マルチフォレスト) が存在する可能性があるため、同期エンジンは `Microsoft.InformADUserAccountEnabled.xxx` リンクに依存して正しいものを選択します。
8. ユーザー アカウントが見つかったら、適切な AD DS フォレストでパスワードを直接リセットしようとしました。
9. パスワードの設定操作が成功すると、ユーザーは自分のパスワードが変更されたと通知されます。

    注

    ユーザーのパスワード ハッシュがパスワード ハッシュ同期を使用して Microsoft Entra ID に同期されている場合、オンプレミスのパスワード ポリシーがクラウド パスワード ポリシーよりも弱い可能性があります。 この場合、オンプレミス ポリシーが適用されます。 このポリシーにより、パスワード ハッシュ同期またはフェデレーションを使用してシングル サインオンを提供する場合でも、オンプレミス ポリシーがクラウドに適用されます。
10. パスワードの設定操作が失敗した場合、エラーが発生すると、ユーザーに再試行を求められます。 次の理由により、操作が失敗する可能性があります。

    - サービスが停止しました。
    - 選択したパスワードが組織のポリシーを満たしていません。
    - ローカル AD DS 環境でユーザーが見つかりません。

    エラー メッセージは、管理者の介入なしに解決を試みることができるように、ユーザーにガイダンスを提供します。

### パスワード ライトバックのセキュリティ

パスワード書き戻しは、安全性の高いサービスです。 情報を確実に保護するために、次のように 4 層セキュリティ モデルが有効になります。

- **テナント固有のサービスバスリレー**
    - サービスを設定すると、Microsoft がアクセスできないランダムに生成された強力なパスワードによって保護されるテナント固有の Service Bus Relay が設定されます。
- **厳重に保護された、暗号的に強固な、パスワード暗号化キー**
    - Service Bus Relay が作成されると、強力な対称キーが作成されます。このキーは、ネットワーク経由でパスワードを暗号化するために使用されます。 このキーは、ディレクトリ内の他のパスワードと同様に、クラウド内の会社のシークレット ストアにのみ存在します。これは、頻繁にロックダウンされ、監査されます。
- **業界標準のトランスポート層セキュリティ (TLS)**
    1. クラウドでパスワードのリセットまたは変更操作が行われると、プレーンテキスト パスワードは公開キーで暗号化されます。
    2. 暗号化されたパスワードは、Microsoft TLS/SSL 証明書を使用して Service Bus Relay に送信される、暗号化されたチャネル経由で送信される HTTPS メッセージに配置されます。
    3. メッセージが Service Bus に到着すると、オンプレミス のエージェントが起動し、以前に生成された強力なパスワードを使用してサービス バスに対して認証を行います。
    4. オンプレミス エージェントは、暗号化されたメッセージを取得し、秘密キーを使用して暗号化を解除します。
    5. オンプレミス エージェントは、AD DS SetPassword API を使用してパスワードの設定を試みます。 この手順により、クラウドで AD DS オンプレミスパスワード ポリシー (複雑さ、年齢、履歴、フィルターなど) を適用できます。
- **メッセージの有効期限ポリシー**
    - オンプレミスのサービスが停止しているためにメッセージが Service Bus に配置されている場合はタイムアウトになり、数分後に削除されます。 メッセージのタイムアウトと削除により、セキュリティがさらに強化されます。

#### パスワード ライトバックの暗号化の詳細

ユーザーがパスワード リセットを送信した後、リセット要求は、オンプレミス環境に到着する前にいくつかの暗号化手順を経ます。 これらの暗号化手順により、サービスの信頼性とセキュリティを最大限に高めることができます。 これらは次のように説明されています。

1. **2048 ビット RSA キーを使用したパスワード暗号化**: ユーザーがパスワードを送信してオンプレミスに書き戻した後、送信されたパスワード自体は 2048 ビット RSA キーで暗号化されます。
2. **256 ビット AES-GCM を使用したパッケージ レベルの暗号化**: パッケージ全体、パスワード、および必要なメタデータは、AES-GCM を使用して暗号化されます (キー サイズは 256 ビット)。 この暗号化により、基になる Service Bus チャネルに直接アクセスできるユーザーがコンテンツを表示または改ざんできなくなります。
3. **すべての通信は TLS/SSL 経由で行われます**。Service Bus との通信はすべて SSL/TLS チャネルで行われます。 この暗号化により、承認されていない第三者からコンテンツが保護されます。
4. **6 か月ごとに自動キー ロールオーバー**: すべてのキーが 6 か月ごとにロールオーバーされるか、パスワード ライトバックが無効になってから Microsoft Entra Connect で再度有効になるたびに、サービスのセキュリティと安全性を最大限に確保します。

#### パスワード ライトバックの帯域幅の使用

パスワード ライトバックは、次の状況で要求をオンプレミス エージェントに送り返すだけの低帯域幅サービスです。

- この機能が Microsoft Entra Connect を通じて有効または無効になると、2 つのメッセージが送信されます。
- サービスが実行されている限り、5 分ごとに 1 つのメッセージがサービス ハートビートとして送信されます。
- 新しいパスワードが送信されるたびに、次の 2 つのメッセージが送信されます。
    - 最初のメッセージは、操作を実行する要求です。
    - 2 番目のメッセージには操作の結果が含まれており、次の状況で送信されます。
        - ユーザーのセルフサービス パスワード リセット中に新しいパスワードが送信されるたびに。
        - ユーザーのパスワード変更操作中に新しいパスワードが送信されるたびに。
        - 管理者が開始したユーザー パスワードのリセット中に新しいパスワードが送信されるたびに (Entra 管理ポータルからのみ)。

##### メッセージ のサイズと帯域幅に関する考慮事項

前述の各メッセージのサイズは、通常 1 KB 未満です。 極端な負荷の下でも、パスワード ライトバック サービス自体は帯域幅の 1 秒あたり数キロビットを消費しています。 各メッセージは、パスワードの更新操作で必要な場合にのみリアルタイムで送信されるため、メッセージ サイズが非常に小さいため、書き戻し機能の帯域幅の使用が小さすぎて測定可能な影響を与える可能性があります。

### サポートされている書き戻し操作

パスワードは、次のすべての状況で書き戻されます。

- **サポートされているエンドユーザー操作**

    - エンドユーザーが自らの意思で行う自己サービス型のパスワード変更操作。
    - エンドユーザーが行うセルフサービスによるパスワード変更操作（例: パスワードの期限切れ）。
    - エンドユーザーにより、[パスワード リセット ポータル](https://passwordreset.microsoftonline.com)から実行されたセルフサービス パスワード リセット。
- **サポートされている管理者操作**

    - 管理者による自発的なパスワード変更。
    - 管理者によるセルフサービスでの強制的なパスワード変更操作（例: パスワードの有効期限切れ）。
    - 管理者による、[パスワード リセット ポータル](https://passwordreset.microsoftonline.com)から行われたセルフサービス パスワード リセット。
    - Microsoft Entra 管理センターから管理者が開始したエンドユーザーのパスワードのリセット。
    - 管理者が開始したエンドユーザーのパスワードリセットを [Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/authenticationmethod-resetpassword) から行う。

### サポートされていない書き戻し操作

パスワードは、次のどの状況でも書き戻されません。

- **サポートされていないエンドユーザー操作**

    - PowerShell バージョン 1、バージョン 2、または Microsoft Graph API を使った、エンド ユーザーによるパスワードのリセット。
- **サポートされていない管理者操作**

    - 管理者によって開始されたエンドユーザーのパスワードリセットは、PowerShell バージョン 1 またはバージョン 2 から行います。
    - 管理者が開始したあらゆるエンドユーザーのパスワードリセットは[Microsoft 365 管理センター](https://admin.microsoft.com)から行います。
    - すべての管理者は、パスワード リセット ツールを使用して自身のパスワード ライトバック用パスワードをリセットすることはできません。

注

Active Directory (AD) で [パスワードの有効期限が切れない] オプションがユーザーに設定されている場合、Active Directory (AD) ではパスワード変更フラグが設定されないため、管理者が開始したエンドユーザーパスワードのリセット中に、次回のログオン時にパスワードの変更を強制するオプションが選択されている場合でも、ユーザーは次回のサインイン時にパスワードの変更を求められることはありません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concept-system-preferred-authentication"} -->
## Microsoft Entra IDでのシステム優先認証 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-system-preferred-authentication
- Service: entra-id / authentication
- Article date: 2026-04-15
- Summary: システム優先認証で、第 1 要素認証と第 2 要素認証の両方について、最も安全なサインイン オプションをユーザーに求める方法を評価する方法について説明します。

システム優先認証では、ユーザーが登録した最も安全な方法を使用してサインインするように求められます。 これは、パスワードや SMS などの安全性の低い方法を使用して認証を行うユーザーにとって重要なセキュリティ強化です。

たとえば、ユーザーがパスワードとパスキーの両方を登録した場合、システム優先認証では、パスワードではなくパスキーを使用してサインインするように求められます。 ユーザーは別の方法を使用してサインインすることもできますが、最初に登録した最も安全な方法を試すように求められます。

システム優先認証は、Microsoft が管理する設定です。これは、3 状態のポリシー（有効、無効、または Microsoft 管理）です。 システム優先認証を有効にしない場合は、状態を **Microsoft managed** から **Disabled** に変更するか、ポリシーからユーザーとグループを除外します。

Note

**Microsoft 管理**状態での動作は、第一要素認証と多要素認証の両方に影響し、2026 年 8 月にかけてテナントに段階的に展開されます。 **State** が **Microsoft managed** の場合、テナントまたはユーザーがシステム優先認証を最初の要素として使用していない場合、ロールアウトはまだテナントに対してデプロイされていません。

システム優先認証が有効になった後、認証システムはすべての処理を実行します。 システムにより、常に、登録した最も安全な方法を決定して提示されるため、ユーザーはどの認証方法も既定として設定する必要はありません。

### サインインにシステム優先認証を適用する方法

システム優先認証には、次の 3 つのモードがあります。

- **無効** - サインイン ロジックに変更はありません。
- **有効** - システム優先認証は第 2 要素にのみ適用されます。 既存のサインイン動作は、引き続き第 1 要素認証に適用されます。
- **Microsoft マネージド** - システム優先認証は、第 1 要素認証と第 2 要素認証の両方に適用されます。 システムは、ユーザーに登録されている資格情報を評価し、各認証手順で最も高いランクの方法を選択します。

**有効**モードと **Microsoft 管理**モードの両方で、管理者は特定のユーザーまたはグループを含めたり除外したりできます。

Tip

システム優先認証を第 1 要素認証に適用しない場合は、**Microsoft managed** から **Enabled** に切り替えます。 **Enabled** 状態は、システム優先ロジックを第 2 要素のみに適用します。

Note

システム優先認証は、デバイスではなく、ユーザーにスコープが設定されます。 管理者はユーザーまたはグループを含めるか除外しますが、特定のデバイスまたはデバイス グループに機能を割り当てることはできません。

#### 既知の制限

- ターゲット グループのポリシーを変更すると、ユーザーの次のサインインに変更が反映されないことがあります。 それ以降のすべてのサインインに適用されます。
- 条件付きアクセス ポリシーは第 2 要素認証に対してのみ検証され、第 1 要素認証には適用されません。 最初に認証が行われ、次に条件付きアクセスによって承認が評価されます。 システム優先認証は、条件付きアクセス ポリシーや認証強度の要件をオーバーライドしません。

#### 第一要素サインインにおけるWindows Hello for BusinessとmacOS Platform SSO

Windows Hello for Businessと macOS Platform SSO は、最初の要素としてのみ機能するデバイス バインドパスキーです。 **Microsoftマネージド**状態では、最初の要素でシステム優先認証が適用されるため、パスワードの前にこれらの資格情報を提供できます。

現在のデバイスで使用しない、または完了できないデバイスバインド資格情報をユーザーに求めないように、システム優先認証では、ユーザーがパスキーを使用して最近サインインした場合にのみ、最初の要素として Windows Hello for Business または macOS Platform SSO が提供されます。 動作は、ユーザーが登録したパスキーによって異なります。

- ユーザーが Windows Hello for Business または macOS Platform SSO 以外のパスキーを持っている場合、システム優先認証では、第 2 要素サインイン時にそのパスキーの入力を既に求めているのと同様に、最初の要素でパスキーの入力を求められます。
- ユーザーの登録済みパスキーのみが Windows Hello for Business または macOS Platform SSO で、ユーザーが最後にパスキーでサインインした場合、システム優先認証では最初の要素でパスキー のサインインを求められます。
- ユーザーの登録されたパスキーのみが Windows Hello for Business または macOS Platform SSO であり、ユーザーの最新のサインインがパスキーを使用していない場合、システム優先認証では最初の要素でスキップされ、代わりにユーザーの資格情報の順序で次にランクが高い方法が求められます。

ユーザーはいつでも別 **の方法でサインイン** を選択して、別の登録済み方法を選択できます。

### Microsoft Entra 管理センターでシステム優先認証を有効にする

既定では、システム優先認証はすべてのユーザーに対してMicrosoft管理されます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも[認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)としてサインインします。
2. **Microsoft Entra ID**&gt;**認証方法**&gt;**設定** に移動します。
3. **System 優先認証**の場合、 **Microsoft-managed**、**Enabled**、または **Disabled** を選択し、すべてのユーザーを含めるまたは除外します。 除外されるグループは、含めるグループよりも優先されます。
4. 変更が完了したら、[ **保存]** を選択します。

### Graph API を使用してシステム優先認証を有効にする

システム優先認証を事前に有効にするには、 要求 の例に示すように、スキーマ構成の 1 つのターゲット グループを選択します。

#### 認証方法機能の構成プロパティ

既定では、システム優先認証は [Microsoft によって管理されます](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-default-enablement#microsoft-managed-settings)。

| 財産 | タイプ | Description |
| --- | --- | --- |
| ターゲットを除外する | featureTarget | この機能から除外される 1 つのエンティティ。 システム優先認証から除外できるグループは 1 つだけです。動的グループまたは入れ子になったグループを指定できます。 |
| includeTarget | featureTarget | この機能に含まれる 1 つのエンティティ。 システム優先認証に含めることができるグループは 1 つだけで、動的グループまたは入れ子になったグループにすることができます。 |
| 状態 | advancedConfigState | 値の例は次のとおりです。**[有効]** では、選択したグループに対してこの機能を明示的に有効にします。**[無効]** では、選択したグループに対してこの機能を明示的に無効にします。**default** では、選択したグループに対してこの機能を有効にするかどうかを Microsoft Entra ID で管理できます。 |

#### 機能ターゲット プロパティ

システム優先認証は、1 つのグループ (動的グループまたは入れ子になったグループ) に対してのみ有効にすることができます。

| 財産 | タイプ | Description |
| --- | --- | --- |
| ID | String | ターゲットとなるエンティティの ID。 |
| ターゲットタイプ | featureTargetType | グループ、ロール、管理ユニットなど、ターゲットとなるエンティティの種類。 使用可能な値は、'group'、'administrativeUnit'、'role'、'unknownFutureValue' です。 |

**systemCredentialPreferences** を有効にし、グループを含めるか除外するには、次の API エンドポイントを使用します。

```text
https://graph.microsoft.com/v1.0/policies/authenticationMethodsPolicy
```

Note

Graph Explorer で、**Policy.ReadWrite.AuthenticationMethod** のアクセス許可に同意する必要があります。

#### 依頼

次の例では、サンプル ターゲット グループを除外し、すべてのユーザーを含めます。 詳細については、「[authenticationMethodsPolicy を更新する](https://learn.microsoft.com/ja-jp/graph/api/authenticationmethodspolicy-update)」を参照してください。

```http
PATCH https://graph.microsoft.com/v1.0/policies/authenticationMethodsPolicy
Content-Type: application/json

{
    "systemCredentialPreferences": {
        "state": "enabled",
        "excludeTargets": [
            {
                "id": "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb",
                "targetType": "group"
            }
        ],
        "includeTargets": [
            {
                "id": "all_users",
                "targetType": "group"
            }
        ]
    }
}
```

### FAQ

#### システム優先認証で最も安全な方法はどのように決定されますか?

ユーザーがサインインすると、認証プロセスによって、登録されているメソッドが確認されます。 ユーザーは、次の順序に従って、最も安全な方法でサインインするように求められます。 メソッドの順序は動的であり、セキュリティ ランドスケープが変化すると更新されます。 ユーザーはいつでもキャンセルして、別の使用可能なサインイン方法を選択できます。 特定の認証方法を必要とする条件付きアクセス ポリシーが組織にある場合、これらのポリシーはシステム優先の認証順序よりも引き続き優先されます。

**Microsoft managed** 状態の場合、システムは使用可能な資格情報を評価し、第 1 要素認証と第 2 要素認証の両方に対して最高ランクの方法を選択します。

| Rank | 資格情報 | Category | の要件を満たす |
| --- | --- | --- | --- |
| 1 | [一時アクセス パス (TAP)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass) | 復元 | 1FA (単要素認証) + MFA (多要素認証) |
| 2 | [Passkey](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)^1^ | フィッシングに対する耐性 | 1FA (単要素認証) + MFA (多要素認証) |
| 3 | [証明書ベースの認証 (CBA)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication) | フィッシングに対する耐性 | 1FA または 1FA + MFA |
| 4 | [Microsoft Authenticator 通知](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-authenticator-app) | パスワードレス | 1FA (単要素認証) + MFA (多要素認証) |
| 5 | [外部多要素認証 (MFA)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-external-method-manage) | — | MFA |
| 6 | [時間ベースのワンタイム パスワード (TOTP)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-oath-tokens)^2^ | — | MFA |
| 7 | [テレフォニー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-phone-options)^3^ | — | MFA |
| 8 | QRコード | 現場担当者 | 1FA |
| 9 | パスワード | — | 1FA |

^1^セキュリティ キー、Authenticator アプリのパスキー、同期されたパスキー、Windows Hello for Business、macOS Platform SSO が含まれます。

^2^Microsoft Authenticator、Authenticator Lite、またはサード パーティ製アプリケーションのハードウェアまたはソフトウェア TOTP が含まれます。

^3^SMS 通話と音声通話が含まれます。

Important

証明書ベースの認証 (CBA) は、CBA とシステム優先認証に関する既知の問題により、システム優先認証の順序で最後に配置されていました。 これらの問題が解決されたので、2026 年 3 月 18 日以降、証明書ベースの認証は認証順序の 3 番目の位置に移動しました。

現在のMicrosoftマネージド動作では、ユーザーは、システム優先 MFA の順序に基づいて、最初と 2 番目の両方の要素で使用可能な最適な認証方法に誘導されます。 これにより、既定ではパスワード ページが表示されなくなりますが、証明書のないデバイス上のユーザーは CBA の間すぐに失敗し、別の方法で続行するには、別 **の方法で手動で [サインイン** ] を選択する必要があります。

#### システム優先認証が NPS 拡張機能に与える影響

システム優先認証は、ネットワーク ポリシー サーバー (NPS) 拡張機能を使用してサインインするユーザーには影響しません。 これらのユーザーの、サインイン エクスペリエンスに変更はありません。

#### フェデレーション ユーザーに対するシステム優先認証のしくみ

フェデレーション ユーザーの場合、第 1 要素サインインは変更されません。 システム優先認証は最初の要素では適用されないため、フェデレーション ユーザーは引き続き外部 ID プロバイダーにルーティングされてサインインされます。 システム優先認証は、これらのユーザーの第 2 要素認証にのみ適用されます。

#### システム優先認証は第 1 要素サインインにどのように影響しますか?

**Microsoft managed** に設定すると、システムは資格情報のランク付けを第 1 要素認証と第 2 要素認証の両方に適用します。 たとえば、ユーザーがパスワードとパスキーの両方を登録している場合、パスワードではなく、最初の要素のサインイン時にパスキーを求められます。 ユーザーは引き続き他のサインイン オプションを選択できます。

**[有効]** に設定すると、資格情報のランク付けは第 2 要素認証にのみ適用されます。 第 1 要素のサインイン動作は変更されません。

#### ユーザーは引き続き別のサインイン方法を選択できますか?

Yes. システム優先認証では、最も高いランクの資格情報を持つユーザーにプロンプトが表示されますが、ユーザーはサインイン時に他の許可される方法を選択できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/concepts-azure-multi-factor-authentication-prompts-session-lifetime"} -->
## Microsoft Entra 多要素認証のプロンプトとセッションの有効期間 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/concepts-azure-multi-factor-authentication-prompts-session-lifetime
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra 多要素認証での再認証プロンプトの推奨構成と、セッションの有効期間の適用方法について説明します。

Microsoft Entra ID には、ユーザーが再認証を必要とする頻度を決定する複数の設定があります。 この再認証には、パスワード、Fast IDentity Online (FIDO)、パスワードレス Microsoft Authenticator など、第 1 要素のみが関係するものがあります。 または、多要素認証 (MFA) が求められる場合もあります。 これらの再認証の設定は、必要に応じて、独自の環境と目的のユーザー エクスペリエンスに合わせて構成することができます。

ユーザー サインインの頻度に関する Microsoft Entra ID の既定の構成は、90 日間のローリング ウィンドウです。 ユーザーに資格情報を求めることはしばしば目的にかなっているように思われますが、不足の結果に終わる可能性があります。 考えることなしに資格情報を入力するようにユーザーが訓練されている場合、悪意のある資格情報プロンプトに情報を意図せず渡してしまうことがあります。

ユーザーに再度サインインを求めないというのは、危険だと思われるかもしれません。 ただし、IT ポリシーの違反があれば、セッションは取り消されます。 たとえば、パスワードの変更、非準拠のデバイス、アカウントの無効化操作などがあります。 また、[Microsoft Graph PowerShell を使用して明示的にユーザーのセッションを取り消す](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users.actions/revoke-mgusersigninsession)こともできます。

この記事では、推奨される構成と、さまざまな設定のしくみと相互作用について説明します。

### 推奨設定

適切な頻度でサインインするように要求することで、セキュリティと使いやすさのバランスをユーザーに付与するには、次の構成をお勧めします。

- Microsoft Entra ID P1 または P2 を持っている場合:
    - [マネージド デバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)または[シームレス SSO](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso) を使用して、アプリケーション間でシングル サインオン (SSO) を有効にします。
    - 再認証が必要な場合は、Microsoft Entra 条件付きアクセスの[サインイン頻度](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime)ポリシーを使用します。
    - アンマネージド デバイスからサインインするユーザーやモバイル デバイスのシナリオでは、永続的ブラウザー セッションは望ましくない場合があります。 または、条件付きアクセスを使用して、**サインイン頻度**ポリシーで永続的ブラウザー セッションを有効にすることもできます。 サインイン リスクに基づいて、期間を適切な時間に制限すると、リスクの低いユーザーほどセッション継続時間が長くなります。
- Microsoft 365 Apps ライセンスまたは Microsoft Entra ID Free ライセンスを持っている場合:
    - [マネージド デバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)または [シームレス SSO](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso) を使用して、アプリケーション間で SSO を有効にします。
    - **[サインインしたままにするオプションを表示する]** オプションを有効のままにして、ユーザーがサインイン時に **[サインインの状態を維持しますか?]** に同意するようガイドします。
- モバイル デバイス シナリオの場合、ユーザーが Microsoft Authenticator アプリを必ず使用するようにします。 このアプリは他の Microsoft Entra ID フェデレーション アプリへのブローカーとして使用され、デバイスの認証プロンプトを減らします。

この調査では、ほとんどのテナントに対してこれらの設定が適切であることが示されています。 これらの **[多要素認証を記憶する]** や **[サインインしたままにするオプションを表示する]** などの設定の組み合わせによっては、ユーザーが認証を頻繁に行うように求めるメッセージが表示される可能性があります。 通常の再認証プロンプトでは、ユーザーの生産性が低下し、攻撃に対して脆弱になる可能性があります。

### Microsoft Entra セッションの有効期間を構成する

ユーザーの認証プロンプトの頻度を最適化するには、Microsoft Entra セッションの有効期間を構成します。 ビジネスおよびユーザーのニーズを理解し、環境に最適なバランスを提供する設定を構成してください。

#### セッション有効期間のポリシー

セッションの有効期間が設定されていない場合、ブラウザー セッションに永続的な Cookie はありません。 ユーザーがブラウザーを閉じて開くたびに、再認証を求めるプロンプトが表示されます。 Office クライアントでは、既定の期間は 90 日のローリング ウィンドウです。 この既定の Office 構成では、ユーザーが自分のパスワードをリセットした場合、またはセッションで非アクティブな状態が 90 日を超えた場合、ユーザーは必要な第 1 要素と第 2 要素を使用して再認証を行う必要があります。

Microsoft Entra ID の ID がないデバイスでは、ユーザー向けに複数の MFA プロンプトが表示される場合があります。 各アプリケーションに他のクライアント アプリと共有されていない独自の OAuth 更新トークンがあると、複数のプロンプトが表示されます。 このシナリオでは、MFA を使用して OAuth 更新トークンを検証するよう各アプリケーションから要求されるため、MFA によって複数回プロンプトが表示されます。

Microsoft Entra ID では、セッションの有効期間に関する最も制限の厳しいポリシーによって、ユーザーが再認証を必要とするタイミングが決まります。 次の両方の設定を有効にするシナリオについて考えてみましょう。

- **[サインインしたままにするオプションを表示する]**。永続的なブラウザー Cookie が使用されます。
- **[多要素認証を記憶する]**。14 日間、値が保持されます。

この例では、ユーザーは 14 日ごとに再認証する必要があります。 この動作は、**[サインインしたままにするオプションを表示する]** 自体でブラウザーでユーザーに再認証を要求としない場合でも、最も制限の厳しいポリシーに従います。

#### マネージド デバイス

Microsoft Entra 参加または Microsoft Entra ハイブリッド参加を使用して Microsoft Entra ID に参加しているデバイスは、アプリケーション間で SSO を使用するために、[プライマリ更新トークン (PRT)](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token) を受け取ります。

この PRT を使用すると、ユーザーはデバイスに一度サインインすることができ、IT スタッフはデバイスがセキュリティとコンプライアンスの基準を満たしているかどうかを確認できます。 アプリまたはシナリオによっては、参加しているデバイスでより頻繁にサインインするようにユーザーに求める必要がある場合は、条件付きアクセスの[サインイン頻度](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime)ポリシーを使用できます。

#### サインインしたままにするオプション

ユーザーが、サインイン中に **[サインインの状態を維持しますか?]** プロンプト オプションで **[はい]** を選択した場合、永続的な Cookie がブラウザーに設定されます。 この永続的な Cookie は、第 1 要素と第 2 要素を記憶し、ブラウザーでの認証要求にのみ適用されます。

[Image: サインインしたままの状態を続けるよう求めるメッセージの例のスクリーンショット]

Microsoft Entra ID P1 または P2 ライセンスをお持ちの場合は、**[永続ブラウザー セッション]** に条件付きアクセス ポリシーを使うことをお勧めします。 このポリシーは、**[サインインしたままにするオプションを表示する]** の設定を上書きして、ユーザー エクスペリエンスを向上させます。 Microsoft Entra ID P1 または P2 ライセンスをお持ちでない場合は、ユーザーに対して **[サインインしたままにするオプションを表示する]** 設定を有効にすることをお勧めします。

ユーザーがサインインの状態を維持するためのオプションの構成の詳細については、[\[サインインの状態を維持しますか?\] プロンプトの管理に関する記事](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-stay-signed-in-prompt)を参照してください。

#### 多要素認証を記憶するオプション

**[多要素認証を記憶する]** 設定では、1 日から 365 日までの間の値を設定できます。 ユーザーがサインイン時に **[今後 X 日間はこのメッセージを表示しない]** オプションを選択すると、永続的 Cookie がブラウザーに設定されます。

[Image: サインイン要求の承認を求めるメッセージの例のスクリーンショット]

この設定によって Web アプリの認証回数が減りますが、Office クライアントなどの最新の認証クライアントで認証回数が増えます。 これらのクライアントでは、通常、パスワードのリセットまたは 90 日の非アクティブな状態の経過後にのみメッセージが表示されます。 ただし、この値を 90 日間より短く設定すると Office クライアントの既定 MFA プロンプトが短縮され、再認証の頻度が増加します。 この設定を **[サインインしたままにするオプションを表示する]** または条件付きアクセス ポリシーと組み合わせて使用すると、認証要求回数が増える場合があります。

**[多要素認証を記憶する]** を使用し、Microsoft Entra ID P1 ライセンスまたは P2 ライセンスをお持ちの場合には、これらの設定を条件付きアクセスの**サインイン頻度**に移行することを検討してください。 それ以外の場合は、代わりに **[サインインしたままにするオプションを表示する]** を使用することを検討してください。

詳細については、「[多要素認証を記憶する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-mfasettings#remember-multi-factor-authentication)」を参照してください。

#### 条件付きアクセスを使用した認証セッション管理

管理者は **[サインインの頻度]** ポリシーを使用して、クライアントとブラウザーの両方の第 1 要素と第 2 要素の両方に適用されるサインインの頻度を選択できます。 認証セッションを制限する必要があるシナリオでは、マネージド デバイスの使用と共にこれらの設定を使用することをお勧めします。 たとえば、重要なビジネス アプリケーションの認証セッションを制限しなければならないことがあります。

**[永続的ブラウザー セッション]** では、ユーザーはブラウザー ウィンドウを閉じてから再度開いた後でもサインインした状態を維持できます。 **[サインインしたままにするオプションを表示する]** 設定と同様に、これによりブラウザーに永続的な Cookie が設定されます。 ただし、これは管理者が構成するため、ユーザーが **[サインインの状態を維持しますか?]** オプションで **[はい]** を選択する必要はありません。 そのため、ユーザー エクスペリエンスが向上します。 **[サインインしたままにするオプションを表示する]** オプションを使用する場合には、代わりに **[永続的ブラウザー セッション]** ポリシーを有効にすることをお勧めします。

詳細については、「[適応型セッション有効期間ポリシーを構成する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime)」をご覧ください。

#### 構成可能なトークンの有効期間

**[構成可能なトークンの有効期間]** 設定を使用すると、Microsoft Entra ID が発行したトークンの有効期間を構成できます。 **条件付きアクセスを使用した認証セッション管理**がこのポリシーに置き換わります。 現在、**構成可能なトークンの有効期間**を使用している場合は、条件付きアクセス ポリシーへの移行を開始することをお勧めします。

### テナント構成を確認する

さまざまな設定のしくみと推奨される構成を理解したので、次はテナントの構成を確認します。 最初に、サインイン ログを参照して、サインイン時にどのセッションの有効期間ポリシーが適用されたかを把握することができます。

各サインイン ログで、 **[認証の詳細]** タブに移動して、 **[セッションの有効期間ポリシーが適用されました]** を確認します。 詳細については、[サインイン ログ アクティビティの詳細についての学習](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-in-log-activity-details)に関する記事を参照してください。

[Image: [認証の詳細] のスクリーンショット。]

**[サインインしたままにするオプションを表示する]** オプションを構成するか見直すには、次の操作を実行します。

重要

Microsoft は、アクセス許可が最も少ないロールを使用することを推奨しています。 このプラクティスは、組織のセキュリティを強化するのに役立ちます。 グローバル管理者は、緊急シナリオに限定する必要がある、または既存のロールを使用できない場合に、高い特権を持つロールです。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. **Entra ID**&gt;**Custom Branding** に移動します。 ロケールごとに **[サインインしたままにするオプションを表示する]** を選択します。
3. **[はい]** を選択し、**[保存]** を選択します。

信頼されたデバイスで多要素認証を記憶するには、次の操作を実行します。

1. [認証ポリシー管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **Entra ID**&gt;**多要素認証**に移動します。
3. **[構成]** で、 **[追加のクラウドベースの MFA 設定]** を選択します。
4. **[多要素認証サービス設定]** ペインで、**[多要素認証を記憶する]** 設定までスクロールして、チェックボックスを選択します。

サインインの頻度と永続的なブラウザー セッションに条件付きアクセス ポリシーを構成するには、次の操作を実行します。

1. [条件付きアクセス管理者](https://entra.microsoft.com)以上として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)にサインインします。
2. **Entra ID**&gt;**Conditional Access** に移動します。
3. この記事で推奨しているセッション管理オプションを使用して、ポリシーを構成します。

トークンの有効期間を確認するには、[Microsoft Graph PowerShell を使用して Microsoft Entra ポリシーのクエリを実行します](https://learn.microsoft.com/ja-jp/entra/identity-platform/configure-token-lifetimes)。 使用しているすべてのポリシーを無効にします。

テナントで複数の設定が有効になっている場合は、使用可能なライセンスに基づいて設定を更新することをお勧めします。 たとえば、Microsoft Entra ID P1 ライセンスあるいは P2 ライセンスをお持ちの場合は、**[サインインの頻度]** および **[永続的ブラウザー セッション]** の条件付きアクセス ポリシーのみを使用する必要があります。 Microsoft 365 Apps ライセンスまたは Microsoft Entra ID Free ライセンスをお持ちの場合は、**[サインインしたままにするオプションを表示する]** の構成を使用する必要があります。

構成可能なトークンの有効期間が有効になっている場合、この機能はすぐに削除されることに注意してください。 条件付きアクセス ポリシーへの移行を計画します。

ライセンスに基づく推奨事項を次の表に示します。

| カテゴリ | Microsoft 365 Apps または Microsoft Entra ID Free | Microsoft Entra ID P1 または P2 |
| --- | --- | --- |
| SSO | [Microsoft Entra 参加](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)、[Microsoft Entra ハイブリッド参加](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join)、またはアンマネージド デバイス向けの[シームレス SSO](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso) | Microsoft Entra 参加または Microsoft Entra ハイブリッド参加 |
| 再認証の設定 | **サインインしたままにする表示オプション**。 | サインインの頻度と永続的なブラウザー セッションの条件付きアクセス ポリシー |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/feature-availability"} -->
## 米国政府機関向けの Azure での Microsoft Entra 機能の可用性 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/feature-availability
- Service: entra-id / authentication
- Article date: 2026-01-15
- Summary: 米国政府機関向けの Azure で利用できる Microsoft Entra 機能について説明します。

次の表に、米国政府機関向け Azure での Microsoft Entra 機能の可用性を示します。

### Microsoft Entra ID

| Service | 機能 | 可用性 |
| --- | --- | --- |
| **認証、シングル サインオン、MFA** | クラウド認証 (パススルー認証、パスワード ハッシュ同期) | ✅ |
|  | フェデレーション認証 (Active Directory フェデレーション サービス (AD FS) または他の ID プロバイダーとのフェデレーション) | ✅ |
|  | 無制限のシングル サインオン (SSO) | ✅ |
|  | 多要素認証 (MFA) | ✅ |
|  | パスワードレス (Windows Hello for Business、Microsoft Authenticator、FIDO2 セキュリティ キー統合) | ✅ |
|  | 証明書ベースの認証 | ✅ |
|  | サービス レベル アグリーメント | ✅ |
| **アプリケーションのアクセス** | 先進認証を使用する SaaS アプリ (Microsoft Entra アプリケーション ギャラリー アプリ、SAML、OAUTH 2.0) | ✅ |
|  | アプリケーションへのグループの割り当て | ✅ |
|  | クラウド アプリの検出 (Microsoft Defender for Cloud Apps) | ✅ |
|  | オンプレミス用アプリケーション プロキシ、ヘッダー ベース、統合 Windows 認証 | ✅ |
|  | セキュリティ保護されたハイブリッド アクセス パートナーシップ (Kerberos、NTLM、LDAP、RDP、SSH 認証) | ✅ |
| **承認と条件付きアクセス** | ロール ベースのアクセス制御 (RBAC) | ✅ |
|  | 条件付きアクセス | ✅ |
|  | SharePoint 制限付きアクセス | ✅ |
|  | セッションの有効期間の管理 | ✅ |
|  | ID 保護 (脆弱性、危険なアカウント、リスク イベント調査、SIEM 接続) | Microsoft Entra ID Protection を参照してください。 |
| **管理とハイブリッド ID** | ユーザーとグループの管理 | ✅ |
|  | グループの権威の情報源 (SOA) | ✅ |
|  | 高度なグループ管理 (動的グループ、名前付けポリシー、有効期限、既定の分類) | ✅ |
|  | ディレクトリ同期 — Microsoft Entra Connect (同期とクラウド同期) | ✅ |
|  | Microsoft Entra Connect Health レポート | ✅ |
|  | 委任された管理 — 組み込みロール | ✅ |
|  | グローバルなパスワードの保護と管理 – クラウド専用ユーザー | ✅ |
|  | グローバルなパスワードの保護と管理 – カスタム禁止パスワード、オンプレミスの Active Directory から同期されたユーザー | ✅ |
|  | Microsoft Identity Manager ユーザーのクライアント アクセス ライセンス (CAL) | ✅ |
| **エンド ユーザーのセルフサービス** | アプリケーション起動ポータル (マイ アプリ) | ✅ |
|  | マイ アプリのユーザー アプリケーション コレクション | ✅ |
|  | セルフサービス アカウント管理ポータル (マイ アカウント) | ✅ |
|  | クラウド ユーザーに対するセルフサービスのパスワード変更 | ✅ |
|  | オンプレミスの書き戻しによるセルフサービスのパスワードのリセット、変更、ロック解除 | ✅ |
|  | セルフサービスのサインイン アクティビティの検索とレポート | ✅ |
|  | セルフサービスのグループ管理 (自分のグループ) | ✅ |
|  | セルフサービスのエンタイトルメント管理 (マイ アクセス) | ✅ |
| **ID ガバナンス** | 自動化されたアプリへのユーザー プロビジョニング | ✅ |
|  | アプリへのグループの自動プロビジョニング | ✅ |
|  | 人事主導のプロビジョニング | 部分的。 「HR プロビジョニング アプリ」を参照してください。 |
|  | 使用条件 | ✅ |
|  | アクセス権レビュー | ✅ |
|  | エンタイトルメント管理 | ✅ |
|  | Privileged Identity Management (PIM) | ✅ |
|  | Microsoft Entra ID ガバナンスのライフサイクル ワークフロー | ✅ |
| **イベント ログとレポート** | 基本的なセキュリティと使用状況レポート | ✅ |
|  | 高度なセキュリティと使用状況レポート | ✅ |
|  | ID 保護: 脆弱性と危険なアカウント | ✅ |
|  | ID 保護: リスク イベントの調査、SIEM 接続 | ✅ |
| **最前線のワーカー** | SMS サインイン | ✅ |
|  | 共有デバイスのサインアウト | Windows 10 デバイスの Enterprise State Roaming は使用できません。 |
|  | 委任されたユーザー管理ポータル (マイ スタッフ) | ❌ |

### Microsoft Entra ID Protection（マイクロソフト エントラ ID 保護）

| リスク検出 | 可用性 |
| --- | --- |
| 漏洩した資格情報 (Microsoft アカウント不正使用 Exchange) | ✅ |
| Microsoft Entra の脅威インテリジェンス | ❌ |
| 匿名 IP アドレス | ✅ |
| 通常とは異なる移動 | ✅ |
| 異常なトークン | ✅ |
| トークン発行者の異常 | ✅ |
| マルウェアにリンクした IP アドレス | ✅ |
| 疑わしいブラウザー | ✅ |
| 見慣れないサインイン プロパティ | ✅ |
| ユーザーに対する管理者確認済みのセキュリティ侵害 | ✅ |
| 悪意のある IP アドレス | ✅ |
| 受信トレイに対する疑わしい操作ルール | ✅ |
| パスワード スプレー | ✅ |
| あり得ない移動 | ✅ |
| 初めての国 | ✅ |
| 匿名 IP アドレスからのアクティビティ | ✅ |
| 受信トレイからの疑わしい転送 | ✅ |
| 検出された追加のリスク | ✅ |

### HR プロビジョニング アプリ

| HR プロビジョニング アプリ | 可用性 |
| --- | --- |
| Workday からの Microsoft Entra へのユーザー プロビジョニング | ✅ |
| ワークデイライドバック | ✅ |
| SuccessFactors から Microsoft Entra へのユーザー プロビジョニング | ✅ |
| 書き戻しに対する SuccessFactors | ✅ |
| API 駆動型インバウンド プロビジョニング | ✅ |
| プロビジョニング エージェントの構成と Azure for US Government テナントへの登録 | ✅ |

### その他の Microsoft Entra 製品

[Microsoft Entra ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals) は、米国政府コミュニティ クラウド (GCC)、GCC-High、および国防総省のクラウド環境で利用できます。 [Microsoft Entra Workload Identities Premium エディション](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identities-faqs#is-the-workload-id-premium-plan-available-on-azure-government-clouds) は、米国政府機関向けの Azure で利用できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-account-recovery-cost-savings-estimator"} -->
## Microsoft Entra IDでのアカウント回復のコスト削減額の見積もり - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-account-recovery-cost-savings-estimator
- Service: entra-id / authentication
- Article date: 2026-04-02
- Summary: コスト削減見積もりツールを使用して、ヘルプ デスクの復旧コストと、Microsoft Entra IDでのセルフサービス アカウントの復旧とプロジェクトの潜在的な節約額を比較します。

コスト削減見積もり機能は、Microsoft Entra ID でアカウントの回復を有効にすることで生じる潜在的な財務および生産性の利点を組織が理解するのに役立ちます。 このツールは、従来のヘルプ デスクの回復とセルフサービス回復のコストと時間の影響を比較し、次の見積もりを提供します。

- 毎月のコスト削減
- 1 か月あたりに得られた時間

Important

表示される節約額は、業界平均に基づく見積もりであり、 **保証された金額ではありません**。 実際の節約額は、サブスクリプション、ライセンス、内部コスト構造によって異なる場合があります。

### コスト削減見積もりツールの仕組み

推定器は次の値を計算します。

- 毎月のコスト削減: ヘルプ デスクとセルフサービス回復コストの違い。
- 1 か月あたりに得られる時間: ユーザーがアカウントを回復したときに失われる生産性時間の短縮。

#### 入力

次の表の値を指定します。

| フィールド | Description |
| --- | --- |
| 組織内のユーザーの合計数 | アカウントの回復が必要なアクティブ なユーザーの数。 (既定値は、テナント内の有効なユーザーの合計数です) |
| 毎月の復旧の割合 | 毎月アカウントの回復を必要とするユーザーの推定割合。 |
| 中間層ヘルプ デスクの平均コスト | ヘルプ デスクによって処理される復旧あたりの一般的なコスト (業界平均: $60)。 |
| 生産性の時間が失われた (分) | 回復中にユーザーが作業できない平均時間。*ヘルプ デスクのシナリオ*: 長い (例: 60 分)*セルフサービス シナリオ*: 短い (例: 5 分) |

#### 出力

このツールには、次の 2 つの比較パネルが表示されます。

- 従来のヘルプ デスク: 入力に基づいて、毎月の合計コストと損失時間が表示されます。
- Self-Service アカウント回復 (SSAR): セルフサービス回復を使用すると、コストと時間の損失が削減されます。

下部に、次の内容が表示されます。

- 毎月のコスト削減: 推定ドル削減額。
- 1 か月あたりに得られた時間: 生産性の時間が回復しました。

### 推定器を使用する

1. 組織のユーザー数と回復率を入力します。
2. 環境を反映するようにコストと時間の値を調整します。
3. ヘルプデスクの合計値をセルフサービスのリカバリーと比較します。
4. 節約額の見積もりを使用して、次の手順を実行します。
    - セルフサービス アカウントの復旧を導入するためのビジネス ケースを構築します。
    - ROI を関係者に伝えます。
    - 運用の改善を計画します。

### ベスト プラクティス

- 内部ヘルプ デスクのコストと回復時間に基づいて現実的な値を使用します。
- ユーザー数と回復パターンの変化に応じて、定期的に見積もりを見直します。
- 正確な ROI 計算のために、データをライセンスとサブスクリプションの詳細と組み合わせます。

### コスト削減見積の例

次の組織の場合:

- 112 ユーザー
- 3% 月次回収
- ヘルプ デスクのコスト: 回復あたり 60 ドル、失われた 60 分
- セルフサービス コスト: 復旧あたり 2 ドル、失われた 5 分

推定節約額:

- 1 か月あたり 195 ドル
- 1 か月あたり 3.1 時間回復
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-account-recovery-enable"} -->
## Microsoft Entra IDでアカウントの回復を有効にして構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-account-recovery-enable
- Service: entra-id / authentication
- Article date: 2026-04-29
- Summary: ユーザーが ID 検証を使用してアクセスを回復できるように、Microsoft Entra IDでアカウントの回復を構成します。 プロファイルとプロバイダーを設定する方法について説明します。

アカウントの回復は、電話、ハードウェア トークン、パスキーを紛失した場合など、すべての認証方法を失ったときにユーザーが自分のアカウントへのアクセスを回復するのに役立つMicrosoft Entra ID機能です。 この機能では、サードパーティの ID 検証プロバイダーを使用して、政府発行の ID を使用してユーザーの ID を検証し、アカウントを回復して認証方法を再登録できるようにします。

この記事では、初期セットアップから ID 検証プロファイルの作成、運用環境へのデプロイまで、アカウントの回復を有効にして構成する完全なプロセスについて説明します。

### [前提条件]

- Microsoft Entra ID P1 ライセンス
- [検証済み ID](https://learn.microsoft.com/ja-jp/entra/verified-id/verifiable-credentials-configure-tenant-quick) が有効になっており、[Face Check](https://learn.microsoft.com/ja-jp/entra/verified-id/using-facecheck) があなたのテナントで構成済みです。
- Microsoft Entra テナントの [Authentication Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) ロール
- Azure サブスクリプションの共同作成者または課金管理者ロール (ID 検証プロバイダー サブスクリプションに必要)

### アカウントの回復を開く

1. 少なくとも[認証管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator)にサインインします。
2. **Entra ID**&gt;**Account recovery** に移動します。
3. 初めてアカウントの回復を開く場合は、セットアップ チェックリストが表示された **[作業の開始** ] ページが表示されます。
4. セットアップが完了すると、[ **アカウント回復** の概要] ページに各セットアップ タスクの状態が表示され、機能に関する情報が表示されます。

    [Image: アカウント回復の概要ページを示すスクリーンショット。概要チェックリストと機能情報が示されています。]

概要ページでは、必要なセットアップ タスクについて説明します。

- **検証済み ID の設定** - セキュリティで保護された準拠アカウントの回復をサポートするために、数回のクリックで検証済み ID を構成します。
- **検証済み ID を使用して Face Check を設定** する - 適切なユーザーがアクセスを回復できるように、Face Check を使用して ID を確認します。
- **回復後にパスキーを設定する - アクセスを回復** した後にパスキーを追加するようユーザーに依頼します。 これにより、アカウントを強力な状態に戻すのに役立ちます。 認証方法ポリシーでパスキーが有効になっていない場合は、ユーザーが回復できるようにパスキーを有効にします。
- **最初の ID 検証プロバイダーを選択してサブスクライブします** — アカウントの回復のために、Microsoft Security ストアから ID 検証プロバイダーを選択します。
- **最初の ID 検証プロファイルを作成** する - 構成ウィザードを完了してプロバイダーに接続し、アカウントの回復を有効にします。

Tip

[概要] ページで [削減額の **見積もり** ] を選択して、コスト削減見積もりツールを開きます。 このツールを使用すると、従来のヘルプ デスクの回復コストとセルフサービス アカウント回復のコストを比較することで、潜在的な節約を予測できます。

[Image: コスト削減の見積もりパネルを示すスクリーンショット。ユーザーのフィールド、回復率、および予想される節約額が表示されています。]

### ID 検証プロバイダーをサブスクライブする

ID 検証プロファイルを作成する前に、Microsoft Security ストアを介して少なくとも 1 つの ID 検証プロバイダーをサブスクライブします。

1. [アカウントの回復の概要] ページで、[ **最初の ID 検証プロバイダーを選択してサブスクライブ**する] を選択するか、[ **プロファイル** ] タブを選択して **[追加**] を選択します。
2. [ **ID 検証プロバイダー** ] パネルで、使用可能なプロバイダーを参照します。 コンプライアンス標準でフィルター処理できます。

    [Image: 使用可能なプロバイダー、価格、コンプライアンス バッジ、サブスクリプション オプションを含む ID 検証プロバイダー パネルを示すスクリーンショット。]
3. サブスクライブしていないプロバイダーの場合は、**Get Solution** を選択して、Microsoft Security ストアでプロバイダーの登録情報を開きます。
4. Microsoft Security ストアで次の手順を実行します。

    1. [ **アカウントの詳細**] で、 **課金サブスクリプション** と **リソース グループ**を選択し、 **リソース名**を指定します。
    2. **[ソリューションの詳細**] で、[**プランの選択**] を選択し、価格プランを選択します。
    3. [ **次へ**] を選択し、注文の詳細を確認して、[ **注文]** を選択します。
5. SaaS サブスクリプションの準備ができたら、[ **今すぐアカウントの構成]** を選択します。 アクティブ化を完了するには、SaaS プロバイダーの管理ポータルにリダイレクトされます。
6. プロバイダーの **[全般]** ページで、必要な詳細 (連絡先名、電子メール、電話番号) を入力し、[ **アクティブ化**] を選択します。
7. 成功の確認が表示されたら、Microsoft Entra 管理センターの [アカウントの回復] ページに戻ります。

既にサブスクライブしているプロバイダーの場合は、[ **選択** ] を選択してプロファイルで使用します。

### ID 検証プロファイルを作成する

ID 検証プロファイルは、特定のユーザー グループに対するアカウントの回復のしくみ (検証を実行するプロバイダー、使用する回復モード、アカウント検証の処理方法) を定義します。 異なる構成を持つ複数のユーザーグループをサポートするために、複数のプロファイルを作成できます。

1. Microsoft Entra 管理センターで、**Entra ID**&gt;**Account recovery** に移動し、**Profiles** タブを選択します。
2. [ **追加]** を選択してプロファイル作成ウィザードを開始します。

#### プロファイルの詳細を追加する

1. [ **プロファイルの詳細** ] ステップで、プロファイルの **名前** とオプションの **説明** を入力して、後で識別できるようにします。

    [Image: プロファイル名と説明のフィールドを含むウィザードのプロファイルの詳細ステップを示すスクリーンショット。]
2. [**次へ**] を選択します。

#### 回復モードを選択する

1. **回復モード**の手順で、次のいずれかのモードを選択します。

    - **評価** - ユーザーは、実際にアカウントを回復することなく、ID 検証プロセスをテストできます。 運用環境のデプロイ前にフローを検証するには、このモードを使用します。
    - **運用** — ID 検証を完了したユーザーは、アカウントを完全に回復し、認証方法を再登録できます。

    [Image: [評価] オプションと [運用] オプションとその説明を含む回復モードの手順を示すスクリーンショット。]

    注

    評価 **モードから** 開始し、完全復旧を有効にする前に、小さなグループで ID 検証フローをテストします。 評価モードでは、アカウントは復旧 **されません** 。ユーザーはプロセスが動作することを確認することしかできません。
2. [**次へ**] を選択します。

#### ユーザー グループの選択

1. **[ユーザー グループの選択**] ステップで、この回復プロファイルを使用できるユーザーを選択します。

    - [ **含める** ] タブを選択して、特定のグループを対象とします。 [ **すべてのユーザー]** または **[ターゲットの選択** ] を選択し、[ **グループの選択** ] を選択して特定のグループを選択します。
    - プロファイルから特定のグループを除外するには、[ **除外** ] タブを選択します。 除外は機能全体で行います。

    [Image: [含める] タブと [除外] タブとグループ選択オプションを含むユーザー グループの選択手順を示すスクリーンショット。]
2. [**次へ**] を選択します。

#### ID 検証プロバイダーを選択する

1. [ **ID 検証プロバイダー** ] ステップで、このプロファイルに使用するプロバイダーを選択します。 一覧には、既にサブスクライブしているプロバイダーと、新しいサブスクリプションで使用できるプロバイダーが表示されます。

    [Image: ID 検証プロバイダーのステップと、使用可能なプロバイダーとそのコンプライアンス バッジを示すスクリーンショット。]

    - サブスクライブしているプロバイダーの場合は、[ **選択**] を選択します。
    - 新しいプロバイダーの場合は、**Get Solution** を選択して、最初に Microsoft Security ストアをサブスクライブします。
2. [**次へ**] を選択します。

#### アカウントの検証を構成する

1. **Account 検証** ステップで、検証プロバイダーからの ID 要求をMicrosoft Entra IDのユーザー プロパティと照合する方法を構成します。

    [Image: ID 要求の照合、一致の信頼度、カスタム認証拡張機能オプションを含むアカウント検証手順を示すスクリーンショット。]

    **ID 要求照合**は、プロバイダーの要求とMicrosoft Entra属性の間の既定の要求マッピングを示します。

    | プロバイダー・クレーム（ターゲット） | Entra クレーム (ソース) |
    | --- | --- |
    | 名（ファーストネーム） | firstName |
    | 姓 | surName |
2. [ **一致の信頼度**] で、一致する動作を選択します。

    - **Exact** — 要求は正確に一致する必要があります。
    - **Relaxed** — クロスフィールド ワード マッチングを使用して、政府発行のドキュメントとMicrosoft Entra IDユーザー プロファイルでの名前の表示方法のバリエーションに対応します。 一致の緩和のしくみの詳細については、「[確認済み ID とMicrosoft Entra IDアカウントの詳細の照合方法」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/authentication/self-service-account-recovery#how-is-the-verified-id-matched-against-microsoft-entra-id-account-details-)
3. (推奨)[ **追加の要求の検証**] で、カスタム認証拡張機能を有効にして、復旧中に組織固有のアカウント照合ロジックを追加します。 アカウントの復旧中に検証済み ID 要求を検証するカスタム認証拡張機能の例については、「Microsoft Entra [アカウント回復要求検証用のカスタム認証拡張機能の作成](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-custom-authentication-extension-account-recovery)を参照してください。

    この手順では、HRIS システム、従業員ディレクトリ、その他の権限のあるソースなど、組織のデータに接続する事前に構成されたAzure関数、ロジック アプリ、または REST エンドポイントが必要です。 復旧中に、ID 検証プロバイダーからの検証済み要求がエンドポイントに渡され、組織のデータに対して検証され、一致の決定が返されます。

    Important

    カスタム認証拡張機能によって処理されるすべてのデータは、組織の信頼境界内に留まります。 組織データはMicrosoftと共有されません。一致した結果のみがアカウント復旧フローに返されます。

    追加の要求検証を構成するには:

    1. **[有効化**] トグルをオンに設定します。
    2. 既存のカスタム認証拡張機能を選択するか、**Create new extension** を選択して、新しいAzure関数、ロジック アプリ、または REST API エンドポイントを登録します。

    [Image: 有効化トグルと拡張機能の選択を含むカスタム認証拡張機能の構成を示すスクリーンショット。]
4. [**次へ**] を選択します。

#### レビューと最終処理

1. [ **確認と最終処理** ] 手順で、すべての構成の選択肢を確認します。

    - **回復モード** - 評価または運用
    - **ユーザー グループ** - 含まれているグループと除外されたグループ
    - **ID 検証プロバイダー** — 選択したプロバイダー
    - **アカウントの検証** - 要求マッピング、一致の信頼度、カスタム認証拡張機能の設定

    [Image: すべてのプロファイル構成設定の概要を示す、レビューと最終処理の手順を示すスクリーンショット。]

    セクションの横にある **[編集]** を選択して戻り、設定を変更します。
2. [ **完了]** を選択してプロファイルを作成します。

### ID 検証プロファイルを管理する

1 つ以上のプロファイルを作成すると、[ **プロファイル** ] タブに、テーブル ビューにすべての ID 検証プロファイルが表示されます。

[Image: プロファイル名、プロバイダー、状態、モード、優先度、アクションを一覧表示するテーブルを含む [プロファイル] タブを示すスクリーンショット。]

このページからは、次のことを行うことができます。

- **[追加]** - 別の ID 検証プロファイルを作成します。
- **更新** - プロファイルの一覧を更新します。
- **監査ログの表示** - アカウント回復の変更に関する監査ログ エントリを確認します。
- **優先度の設定** - ユーザーが複数のプロファイルにアクセスできる場合にプロファイルを評価する順序を変更します。
- **列の編集** - テーブルに表示する列をカスタマイズします。
- プロファイルの**編集** - 編集アイコンを選択して、既存のプロファイルの構成を変更します。
- プロファイルを**コピー**する - 既存のプロファイルを新しいプロファイルの開始点として複製します。
- プロファイルを**削除**する - 不要になったプロファイルを削除します。

### ユーザー プロファイルがアカウント回復の準備ができているかどうかを確認する

アカウントの回復が正しく機能するためには、ユーザー プロパティが ID 検証プロバイダーによって返される要求と一致する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users**&gt;**All Users** に移動し、確認するユーザーを選択します。
3. [ **プロパティの編集] を選択します**。
4. **[名**] プロパティと [**姓]** プロパティが入力され、ユーザーの政府発行 ID と一致していることを確認します。 表示名は、アカウント回復照合プロセスでは使用されません。 **名** と **姓** のプロパティのみが使用されます。

Important

First Name プロパティまたは Last Name プロパティが空白の場合、アカウントの回復は ID 検証プロバイダーからの要求と一致しません。 ユーザーの政府機関 ID の実際の名前が、Microsoft Entra ID アカウントに記載されているものと一致しない場合があります。これらのプロパティを確認して修正してから、それらのユーザーの回復を有効にします。

### プロファイルを評価から運用環境に移動する

評価モードでアカウントの回復をテストし、ID 検証が期待どおりに動作することを確認したら、プロファイルを更新して完全復旧を有効にします。

1. Microsoft Entra 管理センターで、**Entra ID**&gt;**Account recovery** に移動し、**Profiles** タブを選択します。
2. 更新するプロファイルの横にある編集アイコンを選択します。
3. **回復モード**の手順で、**実稼働**を選択してアカウントの完全復旧を有効にします。
4. その他の設定を確認して確認し、[ **完了** ] を選択して変更を適用します。

プロファイルを運用モードに設定すると、スコープ付きグループのユーザーはアカウントの回復を使用して、アカウントへのアクセスを完全に回復できます。 構成されたプロバイダーを介して ID 検証を完了し、一時アクセス パスを受け取って認証方法を再登録します。

Caution

運用モードに切り替える前に、アカウントの回復の対象になっているユーザーを確認します。 組織のセキュリティ要件に基づいて、適切なユーザー集団のみがこの機能にアクセスできるかどうかを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-account-recovery-for-users"} -->
## Microsoft Entra IDでアカウントの回復を実行する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-account-recovery-for-users
- Service: entra-id / authentication
- Article date: 2026-04-02
- Summary: すべての認証方法が失われた場合に、エンド ユーザーが ID 検証を通じてMicrosoft Entra ID アカウントを回復する方法について説明します。 ステップ バイ ステップの復旧プロセスに従います。

ユーザーは、いくつかの簡単な手順でアカウントを回復できます。 この記事では、ユーザーがアカウント回復プロセスを検出して開始する方法と、組織の構成済みプロバイダーを介した ID 検証中に想定される内容について説明します。

### ビデオ: Microsoft Entra IDで職場または学校アカウントを回復する

### ユーザーステップ

1. まず、Microsoft Teamsなどのアプリケーションにサインインするか、https://login.microsoftonline.com で直接サインインします。
2. サインインに使用する初期認証方法が表示されます。
3. この方法を使用できないため、[ **その他のサインイン方法**] を選択します。 追加のサインイン オプションが表示されます。
4. どの方法でもサインインできない場合は、[アカウントの回復] を選択 **します**。

    [Image: サインインしてアカウントの回復を設定する方法を示すスクリーンショット。]
5. アカウントの回復プロセスを説明し、組織の構成済みの外部 ID 証明サービスを通じて ID 検証を完了するためのガイダンスを提供する情報画面が表示されます。
6. 組織によって構成された ID 証明サービスにリダイレクトされ、ID 証明プロセスを開始します。

    注

    ID 検証に必要な具体的な手順は、組織によって構成された ID 検証パートナーによって異なる場合があります。 プロバイダーがユーザーに提示できる一般的な手順を次に示します。
7. モバイル デバイスでカメラを開き、ID 証明アプリケーションの Web サイトで QR コードをスキャンします。
8. モバイル デバイスで ID 校正アプリケーションまたはページが開いたら、[ **続行**] を選択します。
9. 名前と電子メールを入力し、アプリの使用条件に同意して、[続行] を選択 **します**。
10. お客様の国/地域、および **パスポート** や運転免許証など、お持ちの本人確認書類の種類を選択 **します**。
11. 明確なドキュメント写真を撮る方法を確認し、各手順を読んだ後に **[続行** ] を選択します。
12. 写真を撮影したら、[写真の **送信]** を選択します。
13. 顔を鮮明に撮れるようにする方法を確認し、各手順を読んだ後に**続行**を選択します。
14. 写真を撮ると、ID 証明アプリケーションまたはページによって ID が検証され、Microsoft Authenticator アプリに検証可能な資格情報が発行されます。

    注

    これは、検証済み **ID の追加** や **Authenticator で開く**などのオプションとしてプロバイダーによって表示される場合があります。
15. メッセージが表示されたら、**Open** を選択し、Microsoft Authenticatorのロックを解除します。
16. アカウントの所有権を検証する前に、簡単な Face Check を完了するように求められます。
17. プロセスが完了すると、一時的なアクセス パスが取得されます。 このコードをコピーし、[サインイン] を選択 **します**。
18. 最後に、新しい認証方法を登録し、アクセスを完全に回復できる [セキュリティ情報](https://mysignins.microsoft.com/security-info)にリダイレクトされます。

    [Image: アカウント回復用のパスキーを作成する方法を示すスクリーンショット。]

### トラブルシューティング

#### ユーザーが評価モードでアカウントを回復できない

プロファイルが既定の評価モードの場合、ID 検証 (IDV) プロバイダーと Face Check を使用してユーザー検証に合格すると、次の画面が表示されます。 このエクスペリエンスは、評価モードで想定されます。 認証ポリシー管理者は、一時アクセス パス (TAP) を発行する前に、ユーザーを運用モードにする必要があります。

[Image: ユーザーのプロファイルが評価モードであることを示すスクリーンショット。]

#### ユーザーが TAP を発行していない

TAP 画面に赤いエラー メッセージが表示され、TAP が発行されない場合は、要求 ID 情報と実際の名前Microsoft Graph一致することを確認します。

[Image: TAP 画面に赤いエラー メッセージを示すスクリーンショット。]

#### ユーザーが他のサインイン方法を選択した場合、[アカウントの回復] オプションが表示されない場合があります

アカウントの回復は、以前の認証イベントがあるアクティブに使用されるアカウント用に設計されています。 アカウント回復のスコープを有効または変更した後、回復オプションを使用できるようにするには、ユーザーが初期認証を完了することが必要になる場合があります。 テスト アカウントを使用して復旧を評価する場合は、アカウントの回復を試みる前に、まずユーザーの認証を行ってください。

後でサインインしても **アカウントの回復** が表示されない場合は、アカウント回復プロファイル (エンジニアリングなど) に含めるグループに、セルフサービス回復を許可するすべてのユーザーが含まれていることを確認します。 選択したグループに含まれていない場合、ログイン中に回復タスクは提供されません。

[Image: サインインする別の方法を選択する方法を示すスクリーンショット。]

#### 回復の開始時に要求を完了できないことを示すエラーがユーザーに表示される場合があります

これは、アカウントの回復で設定された ID 検証プロバイダーが使用できない場合、またはセキュリティ ストアに問題がある場合に発生します。 管理者は、プロバイダーのアカウント回復の構成とセキュリティ ストアの状態を確認する必要があります。

[Image: エラーを示し、もう一度試すスクリーンショット。]

#### ID 検証プロバイダーのエラー

ドキュメント検証中に、IDV (IDV) プロバイダーが政府のドキュメントまたは運転免許証のアップロードまたは自撮りの写真画像を読み取る際に問題が発生する可能性があります。 天井灯や窓からの反射光によって、プラスチックカードの撮影が困難になり、IDVが処理するためのデータをすべて確認するのが難しくなる可能性があります。

ID 検証プロバイダーは、通常、アップロードした ID の写真に対して独自の顔生体認証チェックを行います。

照明環境を変更したり、別の利用可能な政府機関文書を使用したりすると役立つ場合があります。

[Image: ID 検証プロバイダーのドキュメント検証中の名前の不一致エラーを示すスクリーンショット。]

#### Face Check の完了時のエラー

Face Check のパフォーマンスは、キャプチャ中のユーザーの照明条件と背景によって影響を受ける可能性があります。 障害が発生した場合、イベント ログを使用すると、管理者は Face Check によって達成された信頼度スコアを確認できます。 改善された結果を得るには、明るいウィンドウやライトから離れた暗い環境で Face Check を実行することをお勧めします。

顔チェックは、過度に明るい設定に適応するように設計されたアクティブモードが含まれています。このモードは困難な照明条件の下で正確さを高めるためにユーザー姿勢の手掛かりを利用する。

[Image: アカウントの回復中の一般的なエラーを示すスクリーンショット。]

アカウントの回復の一環として、提示された検証済み ID の写真がアクティブな Face Check と照合されます。 検証済み ID の写真は、ぼやけすぎたり品質が低かったりすると問題になる可能性がありますが、通常は政府のドキュメントと ID 検証プロバイダーのプロセスに由来するため問題ありません。 Microsoft Authenticatorウォレットの検証済みID写真をチェックして、顔を正確に比較するのに十分明確であることを確認します。

[Image: 写真の一致を示すスクリーンショット。]

#### ID 検証ドキュメントの検証後の一時アクセス パス コードの問題

ユーザーが検証済み ID を Microsoft Entra と共有した後、アカウントの検証を試み、ID 検証プロバイダーによって発行された ID の検証済み要求と照合します。 このエラーは、アカウントの所有権を確認できなかった場合に発生する可能性があります。多くの場合、ユーザーのプロファイルの姓と ID の名前が一致していないことが原因です。 "John" と "Jonathan" の違い、または複雑な姓など、これらの問題が発生する可能性があります。 管理者は、プロファイル情報を更新することで、プレビュー中にこれを解決できます。 もう 1 つの考えられる原因は、回復中のユーザーに対する不適切な一時アクセス パス発行グループの構成です。 認証方法ポリシーを確認し、復旧のスコープ内のユーザーが一時アクセス パスメソッドでも有効になっていることを確認します。

#### パスキーが発行されていません

ユーザーが完全に検証され、新しい資格情報を登録するために MySignIns にリダイレクトされたら、監査ログを確認してパスキー登録エラーを評価する必要があります。 詳細については、 [パスキーを登録する方法 (FIDO2)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-register-passkey) を参照してください。

同期されたパスキーの場合は、これらのパスキーがオペレーティング システムにネイティブであるため、デバイスのオペレーティング システムが更新されていることを確認します。 一時アクセス パスの有効期限が切れる前に新しい認証方法が登録されていない場合、ユーザーはアカウントの回復を再開して新しい一時アクセス パスを取得する必要があります。

[Image: パスキーが登録されていない場合のエラーを示すスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-authentication-entra-passkeys-on-windows"} -->
## Windows で Microsoft Entra パスキーを有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-entra-passkeys-on-windows
- Service: entra-id / authentication
- Article date: 2026-07-05
- Summary: Windows 上の Microsoft Entra パスキーが、Windows Hello を FIDO2 パスキー プロバイダーとして使用することで、職場または学校のアカウントでフィッシング耐性のある認証を可能にする仕組みについて説明します。

この記事では、Windowsのパスキー Microsoft Entra、そのしくみ、およびWindows Hello for Businessとの違いについて説明します。

Windows Hello を FIDO2 パスキー プロバイダーとして許可するパスキー プロファイルを構成するには、Windows で Microsoft Entra パスキー用のプロファイルを構成するを参照してください。

### 概要

Windows 上の Microsoft Entra パスキーでは、ユーザーはパスキー（FIDO2）を各自のデバイス上のローカル Windows Hello コンテナーに直接登録できます。 その後、ユーザーはこれらのパスキーを使用して、Microsoft Entra IDにサインインできます。 Windows 上の Microsoft Entra パスキーを使用すると、デバイスを Microsoft Entra に参加または登録しなくても、Windows Hello 生体認証または PIN を使用してフィッシングに対する耐性のあるサインインが可能になります。

Windows で Microsoft Entra パスキーを使用する方法:

- ユーザーは、ローカルの Windows Hello コンテナーにパスキー (FIDO2) を登録できます。
- ローカル Windows パスキーを使用するために、デバイスを Microsoft Entra に参加または登録する必要はありません。
- 1 つの Windows PC で、複数の Microsoft Entra アカウントの複数のパスキーを格納できます。
- Windows Hello に登録されているパスキー (FIDO2) は、Microsoft Entra passkey (FIDO2) ポリシーとパスキー プロファイルによって管理されます。

### Microsoft Entra パスキーが Windows でどのように機能するか

Windows Hello は、Windows デバイス上のセキュリティで保護されたローカル資格情報コンテナーとして機能します。 コンテナーは、次のようなユーザープレゼンス検証によって保護されます。

- PIN
- 指紋
- 顔認識

Windows 上の Microsoft Entra パスキーを使用すると、この Windows Hello コンテナー内にパスキー (FIDO2) を作成して格納し、Microsoft Entra ID への認証に使用できます。

この動作は、デバイスが Microsoft Intune で構成された Windows Hello for Business ポリシーによって管理されている場合にも適用されます。 ただし、パスキー（FIDO2）は、Microsoft Entra ID へのデバイス登録時に自動的に登録される可能性がある Windows Hello for Business の資格情報とは異なります。

### Windows での Microsoft Entra パスキーと Windows Hello for Business の比較

どちらの機能も Windows Hello を使用しますが、Windows と Windows Hello for Business の Microsoft Entra パスキーの目的と動作は異なります。

| 特徴 | Windows での Microsoft Entra パスキー | Windows Hello for Business |
| --- | --- | --- |
| 標準ベース | Fido2 | 認証用の FIDO2、デバイス サインイン用のファースト パーティ (1P) プロトコル |
| Registration | ユーザーが開始した場合、デバイスの参加や登録は必要ありません | デバイス登録中に一部の Microsoft Entra 参加済みまたは登録済みデバイスで自動的にプロビジョニングされる |
| デバイスのサインインとシングル サインオン (SSO) | N/A | デバイスのサインイン後に Microsoft Entra 統合リソースへのデバイス サインインと SSO を有効にします |
| パスキーの種類 | デバイスに結び付けられた | デバイスに結び付けられた |
| 資格情報のバインディング | デバイスにバインドされ、ローカルの Windows Hello コンテナーに格納されます。 ユーザーは、同じデバイス上の複数の職場または学校アカウントに対して複数のパスキーを登録できます。 | 主に、デバイスの信頼にリンクされたデバイス バインドサインイン方法。 資格情報は、デバイスの登録に使用される職場または学校アカウントにのみ関連付けられます。 |
| 管理 | Microsoft Entra ID 認証方法ポリシー | Microsoft Intuneグループ ポリシー |

注

Microsoft Entra 参加済みデバイスまたは Microsoft Entra 登録済みデバイスを使用している場合、Windows Hello を設定すると、デバイスのリンクされたアカウントの Windows Hello for Business 資格情報が自動的に登録される場合があります。 その後、同じアカウントの Windows にパスキーを登録しようとすると、Windows Hello for Business 資格情報が既に存在するため、登録は失敗します。 再試行時に、パスキーが既に登録されていることを示すエラーが表示されます。

### Windows での Microsoft Entra パスキーの前提条件

- 認証方法を構成するための少なくとも [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) アクセス許可を持つアカウント。
- Microsoft Entra 管理センターの[認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2#enable-passkey-profiles)にある**Passkey (FIDO2)**ポリシーで、**パスキーによるサインインを有効にする**必要があります。
- Windows 10またはWindows 11
- デバイスが Windows Hello をサポートしている必要がある

### サポートされている Windows Hello パスキー AAGUID

Windows Helloパスキーは、次の AAGUID を使用して識別および制御されます。 登録を有効にするには、パスキー プロファイルでこれらの AAGUID を明示的に許可する必要があります。

| Windows Hello Authenticator | AAGUID | 説明 |
| --- | --- | --- |
| Windows Hello ハードウェア認証システム | 08987058-cadc-4b81-b6e1-30de50dcbe96 | ハードウェア ベースの TPM に格納されている秘密キー。 |
| Windows Hello VBS ハードウェア認証システム | 9ddd1817-af5a-4672-a2b9-3e3dd95000a9 | 仮想化ベースのセキュリティ (VBS) は、ハードウェア仮想化と Windows ハイパーバイザーを使用して、ホスト コンピューターの TPM に秘密キーを格納します。 |
| Windows Hello ソフトウェア認証システム | 6028b017-b1d4-4c02-b4b3-afcdafc96bb2 | ソフトウェア ベースの TPM に格納されている秘密キー。 |

### Windows で Microsoft Entra パスキー用のプロファイルを構成する

Windows 上で Microsoft Entra パスキーを使用するには、認証ポリシー管理者が以下の設定でパスキー プロファイルを構成する必要があります。

- プロファイルは、特定のWindows Hello AAGUID を対象とする必要があります。
- このプロファイルでは **構成証明を強制**できません。

1. 少なくとも [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)として Microsoft Entra 管理センターにサインインします。
2. **[Entra ID]**&gt;**[Authentication メソッド]** に移動します。
3. **認証方法 |[ポリシー] ページで**、**パスキー (FIDO2)**&gt;**構成**を選択します。
4. [ **+ プロファイルの追加] を選択します**。

    [Image: パスキー プロファイルを追加する方法を示すスクリーンショット。]
5. プロファイルの**名前**を入力します。たとえば、**Windows 上の Entra パスキー**などです。
6. **Passkey 型**の場合は、[**デバイス バインド]** を選択します。
7. **[ターゲット固有の AAGUIDS**] を選択し、[**動作**] を **[許可**] に設定します。
8. **+ AAGUID を追加**&gt;**Windows Hello** と **保存**を選択します。

    [Image: Windows Hello AAGUIDs 構成オプションを示すパスキー プロファイル構成設定のスクリーンショット。]

#### 例: Microsoft Authenticator と Windows Hello のパスキーを許可する

特定の AAGUID をターゲットにして、ユーザーが登録できる認証子を制御できます。 この例では、パスキー プロファイルにより、Windows または Microsoft Authenticator でのパスキーの使用が許可されます。

このプロファイルを構成するには:

1. **ターゲット固有の AAGUID を選択します**。
2. **[動作]** を **[許可**] に設定します。
3. **+ AAGUID を追加**&gt;**Windows Hello** と **保存**を選択します。 **+ AAGUID を追加**&gt;**Microsoft Authenticator**、**保存** の順に選択します。

この構成では、両方の AAGUID セットが許可リストに含まれているため、ユーザーはパスキーをMicrosoft AuthenticatorまたはWindowsのWindows Helloに登録できます。

[Image: ターゲット固有の AAGUID が選択され、動作が [許可] に設定され、Microsoft Authenticatorと Windows Hello AAGUID が追加されたパスキー プロファイル設定の追加のスクリーンショット。]

#### 例: Windows Helloハードウェア認証子のみを許可する

また、特定の AAGUID をターゲットにして、ハードウェアベースのWindows Helloパスキーを要求することもできます。 この例では、高保証パスキー プロファイルでは、Windows Helloハードウェア認証子のみが許可され、Windows Helloソフトウェア認証システムは許可されません。

この制限を構成するには:

1. **ターゲット固有の AAGUID を選択します**。
2. **[動作]** を **[許可**] に設定します。
3. **モデル/プロバイダー AAGUID** で、**Windows Hello Hardware Authenticator** と **Windows Hello VBS Hardware Authenticator** の AAGUID を追加し、**保存**します。

この構成では、ユーザーは、デバイスがハードウェアベースのWindows Helloをサポートしている場合にのみ、Windowsにパスキーを登録できます。 Windows Hello ソフトウェア認証システム AAGUID は許可リストに含まれていないため、ソフトウェアのみの登録はブロックされます。

[Image: [ターゲット固有の AAGUID] が選択され、[動作] が [許可] に設定され、Windows Hello ハードウェア認証器と Windows Hello VBS ハードウェア認証器の AAGUID が追加された [パスキー プロファイルの追加] 設定のスクリーンショット。]

### Windows で Microsoft Entra パスキー用プロファイルのグループを有効にしてターゲットにする

1. 少なくとも [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)として Microsoft Entra 管理センターにサインインします。
2. **[Entra ID]**&gt;**[Authentication メソッド]** に移動します。
3. **認証方法 | ポリシー** ページで、**パスキー (FIDO2)**&gt;**有効化して対象を指定** を選択します。
4. [ **有効化] タブと [ターゲット** ] タブで、[ **有効]** が **[オン]** になっていることを確認します。
5. [ **ターゲットの追加]** を選択し、[ **すべてのユーザー** ] または **[ターゲットの選択]** を選択して特定のグループを選択します。

    [Image: パスキー プロファイルのターゲットを追加する方法を示すスクリーンショット。]
6. Windows 上の Microsoft Entra パスキーのプロファイルを選択し、[**Save**] を選択します。

    [Image: Windows で Microsoft Entra パスキー用のプロファイルを有効にして対象にする方法を示すスクリーンショット。]

### FAQ

**質問**: Windows での Microsoft Entra パスキーのユース ケースは何ですか?

**回答**: 次の場合は、Windows で Microsoft Entra パスキーを使用します。

- Windows にパスキー (FIDO2) をローカルに格納する必要があります。
- ユーザーは、1 台の PC から複数の Microsoft Entra アカウントにアクセスします。
- 未登録、個人用、または共有デバイスで、標準ベースのフィッシングに強い Microsoft Entra へのサインインが必要です。

**質問**: Windows 上の Microsoft Entra パスキーは Windows Hello for Business に置き換えられますか?

**回答 :** いいえ。 Windows 上の Microsoft Entra パスキーは、Windows Hello for Business に代わることはありません。 Windows Hello for Business は、企業の管理対象デバイス、Microsoft Entra 参加済みデバイス、または登録済みデバイスにサインインするための推奨ソリューションです。 Windows 上の Microsoft Entra passkey は、デバイスが参加または登録されていないシナリオで Windows でパスキー (FIDO2) を有効にすることで、Windows Hello for Business を補完します。 Windows 上の Microsoft Entra パスキーは、デバイスのサインインをサポートしていません。

注

Windows Hello for Business 資格情報が同じアカウントとコンテナーに既に存在する場合、ユーザーは Windows にパスキーを登録できません。 このブロックは、ユーザーが合計 50 個のプラットフォーム資格情報を超えると適用されない場合があります。

**質問**: Microsoft Entra パスキーは同期されていますか?

**回答 :** いいえ。 Windows 上の Microsoft Entra パスキーはデバイスにバインドされ、ローカルの Windows Hello コンテナーに格納されます。 デバイス間で同期されることはありません。 各デバイスには、Microsoft Entra アカウントごとに個別のパスキー登録が必要です。

### WindowsにMicrosoft Entraパスキーを登録する

管理者が Windows パスキー プロファイルを作成した後、ユーザーはパスキーをデバイス上のローカル Windows Hello コンテナーに直接登録できます。

登録手順については、「[WindowsにMicrosoft Entraパスキーを登録する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-register-entra-passkey-windows)」を参照してください。

### Windowsで Microsoft Entra パスキーを使用してサインインする

登録後、ユーザーはデバイスのWindows Helloに格納されているパスキーを使用して、Microsoft Entra IDにサインインできます。

サインイン手順については、「Windows[でMicrosoft Entraパスキーを使用してサインインする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-sign-in-entra-passkey-windows)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-authentication-external-method-manage"} -->
## Microsoft Entra ID で外部 MFA を管理する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-external-method-manage
- Service: entra-id / authentication
- Article date: 2026-02-24
- Summary: Microsoft Entra 多要素認証の外部 MFA メソッドを管理する方法について説明します

以前は外部認証方法と呼ばれる外部多要素認証 (MFA) を使用すると、ユーザーは職場または学校アカウントでサインインするときに MFA 要件を満たすために外部プロバイダーを選択できます。 Microsoft Entra ID は、ID コントロール プレーンとして、ポリシーの完全な評価とアクセスの決定を引き続き処理します。

### 外部 MFA を構成するために必要なメタデータ

外部 MFA を構成するには、外部認証プロバイダーから次の情報が必要です。

- **アプリケーション ID** は、一般的にはプロバイダーのマルチテナント アプリケーションで、統合の一部として使用されます。 テナントのこのアプリケーションには、管理者の同意が必要です。
- **クライアント ID** は、認証を要求する Microsoft Entra ID を識別するために、認証統合の一部として使用されるプロバイダーの識別子です。
- **探索 URL** は、外部認証プロバイダーの OpenID Connect (OIDC) 検出エンドポイントです。

    アプリの登録を設定する方法の詳細については、「 [Microsoft Entra ID を使用して新しい外部認証プロバイダーを構成する」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-external-method-provider#configure-a-new-external-authentication-provider-with-microsoft-entra-id)参照してください。

    重要

    kid (キー ID) プロパティが、id\_tokenの JSON Web トークン (JWT) ヘッダーと、プロバイダーのjwks\_uriから取得した JSON Web キー セット (JWKS) の両方で base64 でエンコードされていることを確認します。 このエンコードアラインメントは、認証プロセス中にトークン署名をシームレスに検証するために不可欠です。 配置が間違うと、キーの一致または署名の検証に問題が発生する可能性があります。

### Microsoft Entra 管理センターで外部 MFA を管理する

外部 MFA は、組み込みメソッドと同様に、Microsoft Entra ID 認証方法ポリシーを使用して管理されます。

#### 管理センターで外部 MFA を構成する

管理センターで外部 MFA を構成する前に、 外部 MFA を構成するためのメタデータがあることを確認してください。

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **Entra ID**&gt;**Authentication メソッド**&gt;**外部 MFA の追加**に移動します。

    [Image: Microsoft Entra 管理センターで外部 MFA を追加する方法のスクリーンショット。]

    プロバイダーからの構成情報に基づいてメソッドのプロパティを追加します。 次に例を示します。

    - 名前: Adatum
    - クライアント ID: 00001111-aaaa-2222-bbbb-3333cccc4444
    - 探索エンドポイント: `https://adatum.com/.well-known/openid-configuration`
    - アプリ ID: 11112222-bbbb-3333-cccc-4444dddd5555

    重要

    ユーザーには、メソッド ピッカーに表示名が表示されます。 メソッドの作成後に名前を変更することはできません。 表示名は一意である必要があります。

    [Image: 外部 MFA プロパティを追加する方法のスクリーンショット。]

    プロバイダーのアプリケーションに対する管理者の同意を付与するには、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)ロールが必要です。 同意の付与に必要なロールがない場合でも、認証方法を保存することはできますが、同意が付与されるまで有効にすることはできません。

    プロバイダーからの値を入力した後、ボタンを押して、正しく認証するために必要な情報をユーザーから読み取ることができるように、アプリケーションに管理者の同意を付与するよう要求します。 管理者アクセス許可を持つアカウントでサインインし、プロバイダーのアプリケーションに必要なアクセス許可を付与するよう要求されます。

    サインインした後、[ **同意** する] を選択して管理者の同意を付与します。

    [Image: 管理者の同意を付与する方法を示すスクリーンショット。]

    同意を付与する前に、プロバイダーのアプリケーションで要求されているアクセス許可を確認できます。 管理者の同意を付与し、変更が複製されると、管理者の同意が付与されたことを示すためにページが更新されます。

    [Image: 同意が付与された後の認証方法ポリシーのスクリーンショット。]

アプリケーションにアクセス許可がある場合は、保存する前にメソッドを有効にすることもできます。 そうでない場合は、メソッドを無効の状態で保存し、アプリケーションの同意が付与された後に有効にする必要があります。

メソッドが有効になると、範囲内のすべてのユーザーが MFA プロンプトに対してこのメソッドを選択できるようになります。 プロバイダーからのアプリケーションの同意が承認されていない場合、そのメソッドでのサインインは失敗します。

アプリケーションが削除されたり、アクセス許可がなくなったりすると、ユーザーにエラーが表示され、サインインに失敗します。 メソッドは使用できません。

#### 管理センターで外部 MFA を管理する

Microsoft Entra 管理センターで外部 MFA を管理するには、認証方法ポリシーを開きます。 メソッド名を選択すると、構成オプションが開きます。 どのユーザーをこのメソッドに含めるか、除外するかを選択することができます。

[Image: 特定のユーザーの外部 MFA の使用状況をスコープ設定する方法のスクリーンショット。]

#### 管理センターで外部 MFA を削除する

ユーザーが外部 MFA を使用できないようにする場合は、次のいずれかを実行できます。

- **[有効にする]** を **[オフ]** に設定して、メソッド構成を保存します
- **[削除] を**選択してメソッドを削除する

[Image: 外部 MFA を削除する方法のスクリーンショット。]

### Microsoft Graph を使用して外部 MFA を管理する

Microsoft Graph を使用して認証方法ポリシーを管理するには、`Policy.ReadWrite.AuthenticationMethod` アクセス許可が必要です。 詳細については、「[authenticationMethodsPolicy を更新する](https://learn.microsoft.com/ja-jp/graph/api/authenticationmethodspolicy-update)」を参照してください。

### ユーザー エクスペリエンス

外部 MFA が有効になっているユーザーは、サインイン時に多要素認証が必要な場合に使用できます。

ユーザーがサインインする他の方法があり、 [システム優先認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-system-preferred-authentication) が有効になっている場合、それらの他の方法は既定の順序で表示されます。 ユーザーは別の方法を使用して、外部 MFA を選択できます。 たとえば、ユーザーが別の方法として Authenticator を有効にしている場合、[数値の一致](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-number-match)が要求されます。

[Image: システム優先認証が有効になっているときに外部 MFA を選択する方法のスクリーンショット。]

ユーザーが他の方法を有効にしていない場合は、外部 MFA のみを選択できます。 外部認証プロバイダーにリダイレクトされ、認証が完了します。

[Image: 外部 MFA を使用してサインインする方法のスクリーンショット。]

### 外部 MFA の認証方法の登録

外部 MFA が有効になっているグループのメンバーであるユーザーは、外部 MFA を使用して MFA を満たすことができます。 これらのユーザーは、認証方法の登録に関するレポートには含まれません。

#### ユーザーがセキュリティ情報に外部 MFA を登録する方法

ユーザーは、次の手順に従って、セキュリティ情報に外部 MFA を登録できます。

1. [セキュリティ情報](https://mysignins.microsoft.com/security-info)にサインインします。
2. [ **+ サインイン方法の追加]** を選択します。
3. 使用可能なオプションの一覧からサインイン方法を選択するように求められたら、[ **外部認証方法**] を選択します。
4. 確認画面で **[次へ** ] を選択します。
5. 外部プロバイダーで 2 番目の要素チャレンジを完了します。 成功した場合、ユーザーはサインイン方法に一覧表示されている外部 MFA を確認できます。

#### 登録ウィザードを使用してユーザーが外部 MFA を登録する方法

ユーザーがサインインすると、登録ウィザードによって、使用が有効になっている外部 MFA メソッドを登録できます。 他の認証方法が使用可能になっている場合は、**別の方法を設定する**&gt;**外部認証方法**を選択し、続行する必要があるかもしれません。 外部 MFA を Microsoft Entra ID に登録するには、外部 MFA プロバイダーで認証する必要があります。

認証が成功すると、登録が完了したことを確認するメッセージが表示され、外部 MFA が登録されます。 ユーザーは、アクセスするリソースにリダイレクトされます。

認証に失敗した場合、ユーザーは登録ウィザードにリダイレクトされ、登録ページにエラー メッセージが表示されます。 ユーザーは、もう一度試すか、他の方法で有効になっている場合は、別のサインイン方法を選択できます。

#### 管理者がユーザーの外部 MFA を登録する方法

管理者は、外部 MFA のユーザーを登録できます。 外部 MFA にユーザーを登録する場合、ユーザーは [、セキュリティ情報](https://mysignins.microsoft.com/security-info) または登録ウィザードを使用して外部 MFA を登録する必要はありません。

管理者は、ユーザーの代わりに登録を削除することもできます。 次の署名によって新しい登録がトリガーされるため、ユーザーは回復シナリオで役立つ登録を削除できます。 外部 MFA 登録は、 [Microsoft Entra 管理センター](https://entra.microsoft.com/) または [Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/resources/externalauthenticationmethod) で削除できます。 管理者は、PowerShell スクリプトを作成して、複数のユーザーの登録状態を一度に更新できます。

Microsoft Entra 管理センターの場合:

1. **[ユーザー]**&gt;**[すべてのユーザー]** の順に選択します。
2. 外部 MFA に登録する必要があるユーザーを選択します。
3. [ユーザー] メニューの [ **認証方法**] を選択し、[ **+ 認証方法の追加]** を選択します。
4. **[外部認証方法]** を選択します。
5. 1 つ以上の外部 MFA メソッドを選択し、[ **保存]** を選択します。
6. 成功メッセージが表示され、前に選択した方法が **[使用可能な認証方法]** に一覧表示されます。

### 外部 MFA と条件付きアクセスを使用するためのベスト プラクティス

外部 MFA と条件付きアクセスを使用する場合は、いくつかの重要な要素を考慮する必要があります。

#### 外部 MFA と条件付きアクセスのカスタム コントロールを並列で使用する

外部 MFA とカスタム コントロールは並列で動作できます。 Microsoft では、管理者に次の 2 つの条件付きアクセス ポリシーを構成することを推奨しています。

- カスタム コントロールを適用するための 1 つのポリシー
- MFA の付与が必要なもう 1 つのポリシー

各ポリシーにユーザーのテスト グループを含めますが、両方ではありません。 ユーザーが両方のポリシーに含まれる場合、または両方の条件を持つポリシーに含まれる場合、ユーザーはサインイン時に MFA を満たす必要があります。 また、カスタム コントロールを満たす必要があるため、外部プロバイダーに 2 回リダイレクトされます。

#### 条件付きアクセスにおけるサインイン頻度ポリシーでの外部 MFA の使用

調査では、MFA プロンプトをユーザーの意図に合わせることの重要性が強化されました。 外部 MFA では、ユーザーは条件付きアクセス サインイン頻度ポリシーを使用して構成された MFA の鮮度要件に基づいて、MFA プロバイダーにリダイレクトされます。 ユーザーエクスペリエンスに悪影響を与え、生産性を低下させ、ユーザーが資格情報を入力するように調整することでフィッシングリスクを高める可能性があるため、過度に頻繁に再認証することはお勧めしません。 サインイン頻度ポリシーを構成するときは、 [再認証のガイダンス](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concepts-azure-multi-factor-authentication-prompts-session-lifetime) に従うことをお勧めします。

### FAQ

**質問：** デバイスのセットアップ中に Windows 10 で外部 MFA が機能しないのはなぜですか?

**答え：** **外部 MFA 専用 ID を**使用して **Windows 10** を実行しているデバイスを設定すると、**Out-of-Box (OOB) セットアップ エクスペリエンス**が失敗し、サインインを続行できないという問題が発生する可能性があります。

この動作は、 **Windows 10 が OOBE (Out-of-Box Experience) 中に外部 MFA をネイティブにサポートしていないため**に発生します。 Microsoft は Windows 10 (https://www.microsoft.com/windows/end-of-support?msockid=26a3312b6b246f501ec624846a4f6e11) をサポートしなくなり、 **外部 MFA サポートを拡張する予定はありません** 。

サインインに外部 MFA を使用するには、 **Windows 11 にアップグレードします**。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-authentication-find-coverage-gaps"} -->
## Microsoft Entra ID で管理者の強力な認証範囲にギャップがないか見つけて対処する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-find-coverage-gaps
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra ID で管理者の強力な認証範囲にギャップがないか見つけて対処する方法について説明します

テナントの管理者に対して多要素認証 (MFA) を要求することは、テナントのセキュリティを強化するために実行できる最初の手順の 1 つです。 この記事では、すべての管理者を確実に多要素認証の対象とする方法について説明します。

### Microsoft Entra ビルトイン Administrator ロールの現在の使用状況を検出する

[Microsoft Entra ID のセキュア スコア](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-identity-secure-score)を使うと、テナントで**管理者ロールに対して MFA が必須になっているかどうか**についてのスコアが提供されます。 この改善のための処置では、[管理者ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)を持つユーザーの MFA 使用状況を追跡します。

管理者が MFA ポリシーの対象となっているかどうかは、さまざまな方法で確認できます。

- 特定の管理者のサインインのトラブルシューティングを行う場合は、サインイン ログを使用できます。 サインイン ログでは、特定のユーザーの **[認証要件]** をフィルター処理できます。 **[認証要件]** が **[単一要素認証]** となっているサインインは、サインインに必要な多要素認証ポリシーが適用されていないことを意味します。

    [Image: サインイン ログのスクリーンショット。]

    特定のサインインの詳細を表示する場合は、**[認証の詳細]** タブを選択して、MFA 要件の詳細を確認します。 詳細については、「[サインイン ログ アクティビティの詳細](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-in-log-activity-details)」を参照してください。

    [Image: 認証アクティビティの詳細のスクリーンショット。]
- ユーザー ライセンスに基づいて有効にするポリシーを選択できるように、[MFA ポリシーを比較し](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-licensing#compare-multi-factor-authentication-policies)、組織にとって最適な手順を確認するうえで役立つ新しい MFA 有効化ウィザードが用意されています。 ウィザードには、過去 30 日間に MFA によって保護された管理者が表示されます。

    [Image: 多要素認証有効化ウィザードのスクリーンショット。]
- [このスクリプト](https://github.com/microsoft/AzureADToolkit/blob/main/src/Find-AADToolkitUnprotectedUsersWithAdminRoles.ps1)を実行すると、過去 30 日間に MFA を使用して、またはなしでサインインしたディレクトリ ロールの割り当てを持つすべてのユーザーのレポートをプログラムで生成できます。 このスクリプトでは、すべてのアクティブな組み込みロールとカスタムロールの割り当て、すべての利用可能な組み込みロールとカスタムロールの割り当て、およびロールが割り当てられているグループが列挙されます。

### 管理者に多要素認証を適用する

多要素認証で保護されていない管理者を見つけた場合は、次のいずれかの方法で保護できます。

- 管理者に Microsoft Entra ID P1 または P2 のライセンスが付与されている場合は、[条件付きアクセス ポリシーを作成](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-azure-mfa)して管理者に MFA を適用することができます。 このポリシーを更新することで、カスタム ロールに含まれるユーザーに MFA を要求することもできます。
- [MFA 有効化ウィザード](https://aka.ms/MFASetupGuide)を実行して MFA ポリシーを選択します。
- [Privileged Identity Management](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) でカスタムまたは組み込みの管理者ロールを割り当てると、ロールのアクティブ化時に多要素認証が必要になります。

### 管理者に対してパスワードレスおよびフィッシングに抵抗できる認証方法を使用する

管理者が多要素認証の対象となり、しばらく使用した後、認証強度を高め、パスワードレスでフィッシングに対抗できる認証方法を使用します。

- [電話によるサインイン (Microsoft Authenticator を使用)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-authenticator-app)
- [FIDO2](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)
- [Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/)

これらの認証方法とそのセキュリティに関する考慮事項の詳細については、[Microsoft Entra の認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)に関するページ参照してください。
<!-- /MSL-PAGE -->
