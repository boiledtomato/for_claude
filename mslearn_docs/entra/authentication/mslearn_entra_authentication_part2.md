# Microsoft Learn — Microsoft Entra / 認証 (MFA・パスワードレス・SSPR) (part 2)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 48

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-authentication-methods-manage"} -->
## 認証方法ポリシーに移行する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-methods-manage
- Service: entra-id / authentication
- Article date: 2026-01-15
- Summary: 認証方法ポリシーで多要素認証とセルフサービス パスワード リセット (SSPR) の設定を一元管理する方法について説明します。

多要素認証 (MFA) とセルフサービス パスワード リセット (SSPR) を個別に制御する Microsoft Entra ID [レガシ ポリシー設定](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-methods-manage#legacy-mfa-and-sspr-policies)を、[認証方法ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-methods-manage)を使用した一元管理に移行できます。

Microsoft Entra 管理センターの認証方法移行ガイドを使用して、移行を自動化できます。 このガイドは、MFA と SSPR の現在のポリシー設定の監査に役立つウィザードを提供します。 次に、これらの設定を認証方法ポリシーに統合します。ここでは、これらの設定は、より簡単にまとめて管理できます。

独自のスケジュールでポリシー設定を手動での移行もできます。 移行プロセスは完全に元に戻すことができます。 認証方法ポリシーでユーザーとグループの認証方法をより正確に構成しながら、テナント全体の MFA ポリシーと SSPR ポリシーを引き続き使用できます。

移行中にこれらのポリシーがどのように連携するかの詳細については、「[Microsoft Entra ID の認証方法を管理する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-methods-manage)」を参照してください。

注

移行ガイドでは、テナント ポリシー設定のみを移行します。 個々のユーザー設定は移行されません。

### 自動移行ガイド

自動移行ガイドでは、わずか数回のクリックで認証方法を管理する場所の移行ができます。 Microsoft [Entra 管理センター](https://entra.microsoft.com) からアクセスするには、 **Entra ID**&gt;**Authentication メソッド**&gt;**Policies** を参照します。

[Image: ウィザードのエントリ ポイントを強調表示している [認証方法ポリシー] ブレードのスクリーンショット。]

ウィザードの最初のページでは、その内容としくみについて説明します。 参照用の各レガシ ポリシーへのリンクも提供します。

[Image: ウィザードの最初のページを強調表示している [認証方法ポリシー] ブレードのスクリーンショット。]

次に、従来の MFA ポリシーと SSPR ポリシーで組織が現在有効にしている内容に基づいて、認証方法ポリシーを構成しています。 いずれかのレガシ ポリシーでメソッドが有効になっている場合、認証方法ポリシーでも有効にすることをお勧めします。 この構成では、ユーザーは以前に使用したものと同じ方法を使用して、サインインとパスワードのリセットを続行できます。

さらに、パスキー、一時アクセス パス、Microsoft Authenticator などの最新のセキュリティで保護された最新の方法を有効にして、組織のセキュリティ体制の改善をお勧めします。 推奨される構成の編集には、各方法の横にある鉛筆アイコンを選択します。

[Image: ウィザードの 2 番目のページを強調表示している [認証方法ポリシー] ブレードのスクリーンショット。]

構成に問題がなければ、**[移行する]** を選択し、移行を確認します。 認証方法ポリシーは、ウィザードで指定された構成と一致するように更新されます。 従来の MFA および SSPR ポリシーの認証方法は淡色表示になり、適用されなくなります。

移行の状態が **Migration Complete**に更新されます。 必要に応じて、この状態を **[進行中]** にいつでも変更して、従来のポリシーのメソッドを再度有効にできます。

### 手動移行

まず、ユーザーが使用できるそれぞれの認証方法について、既存のポリシー設定の監査を行います。 移行中にロールバックする場合は、以下の各ポリシーからの認証方法設定の記録が必要な場合があります。

- MFA ポリシー
- SSPR ポリシー (使用されている場合)
- 認証方法ポリシー (使用されている場合)

SSPR を使用しておらず、認証方法ポリシーをまだ使用していない場合は、MFA ポリシーから設定を取得するだけでかまいません。

#### レガシ MFA ポリシーを確認する

まず、レガシ MFA ポリシーで使用できる方法を文書化します。

1. [認証ポリシー管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **[Entra ID]**&gt;**[ユーザー]**&gt;**[ユーザーごとの MFA]**&gt;**[サービス設定]** を参照して設定を表示します。

テナント全体の設定が表示されるため、ユーザーまたはグループの情報は必要ありません。

[Image: レガシ Microsoft Entra 多要素認証ポリシーのスクリーンショット。]

それぞれの方法がテナントに対して有効かどうかをメモします。 次の表は、レガシ MFA ポリシーで使用できる方法と、認証方法ポリシーの対応する方法を示しています。

| 多要素認証ポリシー | 認証方法ポリシー |
| --- | --- |
| 電話の呼び出し | 音声通話 |
| 電話へのテキスト メッセージ | SMS |
| モバイル アプリでの通知 | Microsoft Authenticator |
| モバイル アプリからの確認コードまたはハードウェア トークン | サード パーティ製のソフトウェア OATH トークンハードウェア OATH トークンMicrosoft Authenticator |

#### レガシ SSPR ポリシーを確認する

従来の SSPR ポリシーで使用できる認証方法を取得するには、 **Entra ID**&gt;**Users**&gt;**Password reset**&gt;**Authentication メソッド**に移動します。 次の表は、レガシ SSPR ポリシーで使用できる方法と、認証方法ポリシーの対応する方法を示しています。

[Image: レガシ Microsoft Entra SSPR ポリシーのスクリーンショット。]

SSPR の範囲内のユーザー (すべてのユーザー、1 つの特定のグループ、またはユーザーなし) と、そのユーザーが使用できる認証方法を記録します。 セキュリティの質問は、認証方法ポリシーではまだ管理できません。また、従来の SSPR 認証方法の設定でも管理できます。 **Entra ID**&gt;**Users**&gt;**Password reset**&gt;**Properties** に移動して、現在のセキュリティの質問情報を確認できます。

| SSPR 認証方法 | 認証方法ポリシー |
| --- | --- |
| モバイル アプリの通知 | Microsoft Authenticator |
| モバイル アプリ コード | Microsoft Authenticatorソフトウェア OATH トークン |
| Email | 電子メールの OTP |
| 携帯電話 | 音声通話SMS |
| 会社電話 | 音声通話 &gt; [構成] タブ |
| セキュリティの質問 | まだ使用できません。後で使用するために質問をコピーします |

#### 認証方法ポリシー

認証方法ポリシーの設定を確認するには、少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインし、**Entra ID**&gt;**Authentication メソッド**&gt;**Policies** を参照します。 新しいテナントでは、すべての方法が既定で **[オフ]** になっています。これにより、レガシ ポリシー設定を既存の設定とマージする必要がないため、移行が簡単になります。

1. [認証ポリシー管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **[Entra ID]**&gt;**[認証方法]**&gt; に移動します。

[Image: 認証方法を示すスクリーンショット。]

認証方法ポリシーには、FIDO2 セキュリティ キー、一時アクセス パス、Microsoft Entra 証明書ベースの認証など、レガシ ポリシーで使用できないその他の方法があります。 これらの方法は移行の範囲外であるため、構成済みであれば変更を加える必要はありません。

認証方法ポリシーで他の方法を有効にした場合は、それらの方法を使用できる、または使用できないユーザーとグループを書き留めます。 方法の使い方を制御する構成パラメーターをメモしておきます。 たとえば、プッシュ通知で位置情報を提供するように Microsoft Authenticator を構成できます。 それぞれの方法に関連付けられた同様の構成パラメーターに対して有効になっているユーザーとグループを記録してください。

### 移行を開始する

使用中のポリシーから使用可能な認証方法をキャプチャしたら、移行を開始できます。 認証方法ポリシーを開いて、**[移行の管理]** を選択し、**[移行が進行中]** を選択します。

[Image: 移行プロセスを開始する方法を示すスクリーンショット。]

このオプションは、サインインシナリオとパスワードリセットシナリオの両方に新しいポリシーが適用されるため、変更を加える前に設定します。

[Image: [移行が進行中] のスクリーンショット。]

次の手順では、監査に合わせて認証方法ポリシーを更新します。 それぞれの方法を 1 つずつ確認します。 テナントがレガシ MFA ポリシーのみを使用していて、SSPR を使用していない場合、更新は簡単です。すべてのユーザーに対してそれぞれの方法を有効にして、既存のポリシーと正確に一致させることができます。

テナントが MFA と SSPR の両方を使用している場合は、それぞれの方法について検討する必要があります。

- 両方のレガシ ポリシーで方法が有効になっている場合は、認証方法ポリシーですべてのユーザーに対してその方法を有効にします。
- 両方のレガシ ポリシーで方法が無効になっている場合は、認証方法ポリシーですべてのユーザーに対してその方法を無効のままにします。
- 1 つのポリシーでのみ方法が有効になっている場合は、すべての状況でその方法を使用可能にするかどうかを決定する必要があります。

ポリシーが一致する場合は、現在の状態と簡単に一致させることができます。 不一致がある場合は、方法を完全に有効または無効にするかどうかを決定する必要があります。 たとえば、MFA のプッシュ通知を許可するために **[モバイル アプリによる通知]** が有効になっているとします。 レガシ SSPR ポリシーでは、**[モバイル アプリの通知]** 方法は有効になっていません。 その場合、レガシ ポリシーでは MFA のプッシュ通知が許可されますが、SSPR では許可されません。

認証方法ポリシーでは、SSPR と MFA の両方に対して **[Microsoft Authenticator]** を有効にするか無効にするかを選択する必要があります ([Microsoft Authenticator] を有効にすることをお勧めします)。

認証方法ポリシーでは、すべてのユーザーに加えて、ユーザーのグループに対して方法を有効にするオプションもあります。また、特定の方法を使用できないようにユーザーのグループを除外することもできます。 つまり、どのユーザーがどの方法を使用できるかをきわめて柔軟に制御できます。 たとえば、すべてのユーザーに対して **Microsoft Authenticator** を有効にし、**SMS** と **音声通話**を、これらの方法を必要とする 20 人のユーザーの 1 グループに制限できます。

認証方法ポリシーでそれぞれの方法を更新する場合、一部の方法には構成可能なパラメーターが用意されており、対象の方法の使い方を制御できます。 たとえば、認証方法として **音声通話** を有効にした場合、[ **構成** ] タブで、会社の電話と携帯電話の両方を許可するか、モバイルのみを許可するように選択できます。 プロセスを実行して、監査からそれぞれの認証方法を構成します。

既存のポリシーと一致させる必要はありません。 これは、有効な方法を確認し、テナントのセキュリティと使いやすさを最大化する新しいポリシーを選択するための絶好の機会です。 既にユーザーが使用している方法を無効にすると、ユーザーが新しい認証方法を登録する必要があり、以前に登録した方法を使用できなくなる場合があることに注意してください。

以降のセクションでは、それぞれの方法に関する移行のガイダンスを示します。

#### ワンタイム パスコードの電子メール送信

**[メールのワンタイム パスコード]** には、次の 2 つのコントロールがあります。

**[有効化とターゲット]** セクションで: テナント メンバーは、特定のグループを含めるか除外するか (またはすべてのメンバー ユーザーに対して有効にする) を指定して、**[パスワードのリセット]** で電子メール OTP の使用を許可できます。

**[構成]** セクションで: B2B ユーザーによる**サインイン**での電子メールの OTP の使用を制御する **[外部ユーザーに電子メール OTP の使用を許可する]** コントロールが別途用意されています。 この設定が有効になっている場合、電子メール OTP 認証方法を無効にすることはできません。

#### Microsoft Authenticator

レガシ MFA ポリシーで **[モバイル アプリによる通知]** が有効になっている場合は、認証方法ポリシーで **[すべてのユーザー]** に対して **[Microsoft Authenticator]** を有効にします。 プッシュ通知またはパスワードレス認証を許可するには、認証モードを **[任意]** に設定します。

レガシ MFA ポリシーで **[モバイル アプリまたはハードウェア トークンからの確認コード]** が有効になっている場合は、**[Microsoft Authenticator OTP の使用を許可する]** を **[はい]** に設定します。

[Image: Microsoft Authenticator OTP のスクリーンショット。]

注

ユーザーが **[別の認証アプリを使用します]** ウィザードを使用して、OTP コードに対してのみ Microsoft Authenticator アプリを登録する場合は、**サードパーティ製ソフトウェア OATH トークン** ポリシーを有効にする必要があります。

#### SMS と音声通話

レガシ MFA ポリシーでは、**[SMS]** と **[電話]** に個別のコントロールがあります。 ただし、SMS と音声通話の両方に対して携帯電話を有効にする **[携帯電話]** コントロールもあります。 また、**[会社電話]** の別のコントロールでは、音声通話に対してのみ会社電話を有効にします。

認証方法ポリシーでは、レガシ MFA ポリシーに一致する **SMS** と**音声通話**の制御があります。 テナントが SSPR を使用していて、**[携帯電話]** が有効になっている場合は、認証方法ポリシーで **[SMS]** と **[音声通話]** の両方を有効にします。 テナントで SSPR を使用していて**、Office 電話**が有効になっている場合は、認証方法ポリシーで**音声通話**を有効にし、[**構成**] タブで **Office 電話**オプションが有効になっていることを確認します。

注

**[サインインに使用]** オプションは、**SMS** 設定で既定で有効になっています。 このオプションによって、SMS サインインが有効になります。 ユーザーに対して SMS サインインが有効になっている場合、ユーザーはテナント間の同期からスキップされます。 テナント間同期を使用している場合、または SMS サインインを有効にしない場合は、ターゲット ユーザーの SMS サインインを無効にします。

#### OATH トークン

レガシ MFA ポリシーと SSPR ポリシーの OATH トークン コントロールは、3 種類の OATH トークン (Microsoft Authenticator アプリ、サード パーティ製のソフトウェア OATH TOTP コード ジェネレーター アプリ、ハードウェア OATH トークン) の使用を可能にする単一のコントロールでした。

認証方法ポリシーは、OATH トークンの種類ごとに個別のコントロールを使用して細かく制御できます。 Microsoft Authenticator からの OTP の使用は、ポリシーの **[Microsoft Authenticator]** セクションの **[Microsoft Authenticator OTP の使用を許可する]** コントロールによって制御されます。 サード パーティ製のアプリは、ポリシーの **[サード パーティ製のソフトウェア OATH トークン]** セクションによって制御されます。 ハードウェア OATH トークンは、ポリシーの **[ハードウェア OATH トークン]** セクションによって制御されます。

#### セキュリティの質問

**セキュリティの質問の**制御は、SSPR に残ります。 セキュリティの質問を使用していて、この移行の一環として無効にしたくない場合は、レガシ SSPR ポリシーで有効にしておく必要があります。 次のセクションで説明されているように、秘密の質問を有効にした状態で移行を完了 "できます"。

### 移行を完了する

認証方法ポリシーを更新したら、レガシ MFA ポリシーと SSPR ポリシーを確認して、それぞれの認証方法を 1 つずつ削除します。 それぞれの方法の変更をテストおよび検証します。

MFA と SSPR が想定どおりに動作することを確認し、レガシ MFA ポリシーと SSPR ポリシーが不要になった場合は、移行プロセスを **[移行が完了済み]** に変更できます。 このモードでは、Microsoft Entra は認証方法ポリシーにのみ従います。 **[移行が完了済み]** が設定されている場合、レガシ ポリシーに変更を加えることはできません (SSPR ポリシーの秘密の質問を除く)。 何らかの理由でレガシ ポリシーに戻る必要がある場合は、移行の状態を **[移行が進行中]** にいつでも戻すことができます。

[Image: [移行が完了済み] のスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-authentication-passkeys-fido2"} -->
## Microsoft Entra IDでFIDO2パスキーを有効にする方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2
- Service: entra-id / authentication
- Article date: 2026-03-08
- Summary: Microsoft Entra IDでパスキー (FIDO2) を有効にする方法について説明します。

現在パスワードを使用している企業にとって、パスキー (FIDO2) は、ユーザー名やパスワードを入力せずに認証できるシームレスな方法を従業員に提供する手段になります。 パスキー (FIDO2) を使うと、ワーカーの生産性が向上し、セキュリティが強化されます。

この記事では、組織でパスキーを有効にするための要件と手順の一覧を示します。 これらの手順を完了すると、組織内のユーザーは、FIDO2 セキュリティ キー、ネイティブまたはサード パーティのパスキー プロバイダー、またはMicrosoft Authenticatorに格納されているパスキーを使用して、Microsoft Entra アカウントに登録してサインインできます。

Microsoft Authenticatorでパスキーを有効にする方法の詳細については、「 Microsoft Authenticatorを参照してください。

詳細情報については、「[Microsoft Entra ID を使用した FIDO2 認証のサポート](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-fido2-compatibility)」を参照してください。

注

現在、Microsoft Entra IDでは次の機能がサポートされています。

- 同期されたパスキー
- FIDO2 セキュリティ キーおよび Microsoft Authenticator に格納されているデバイス バインド パスキー

パスキー (FIDO2) は、Microsoft Entra ID Free を含むすべてのMicrosoft Entra ID エディションで使用できます。 追加のライセンスは必要ありません。 詳細については、「Microsoft Entra ID の Passkeys (FIDO2) 認証方法」を参照してください。

### 概要

組織のパスキーを有効にするには、次の手順を順番に実行します。

1. **パスキー プロファイルを有効化** — Microsoft Entra 管理センターでパスキー プロファイルにオプトインします。 既存のグローバル設定は、既定のパスキー プロファイルに転送されます。
2. **パスキー プロファイルの作成** - ターゲット グループの構成証明、パスキーの種類 (デバイスバインドまたは同期済み)、およびキー制限設定を定義します。
3. **パスキー プロファイルを対象グループに適用する** - パスキー プロファイルを特定のユーザー グループまたはすべてのユーザーに割り当てます。
4. **同期されたパスキーを有効にする** (省略可能) - 同期されたパスキーを許可する場合は、プロファイルのパスキーの種類として **[同期済** み] を選択します。
5. **パスキー サインインを強制する** (省略可能) - 機密性の高いリソースに対してパスキーを要求する条件付きアクセス認証強度ポリシーを作成します。

### パスキープロファイル

#### パスキー プロファイルとは何ですか?

パスキー プロファイルを使用すると、パスキー (FIDO2) 認証に関する詳細なグループベースの構成が可能になります。 1 つのテナント全体の設定の代わりに、構成証明、パスキーの種類 (デバイスバインドまたは同期済み)、Authenticator Attestation GUID (AAGUID) の制限などの特定の要件を定義できます。 管理者と現場スタッフなど、ユーザー グループごとに異なるパスキー プロファイルに要件を適用できます。

注

認証ポリシー管理者は、同期されたパスキーを有効にするためにパスキー プロファイルを構成する必要があります。 詳細については、「 同期されたパスキーを有効にする」を参照してください。

パスキー プロファイルは、対象グループのユーザーがパスキー (FIDO2) を使用して登録および認証する方法を制御する、名前付きポリシー 規則のセットです。 プロファイルは、次のような高度なコントロールをサポートします。

| オプション | コンフィギュレーション |
| --- | --- |
| 証明の実行を強制する | 有効、無効 |
| パスキーの種類 | デバイス結合、同期済み |
| 特定の認証子をターゲットとする | AAGUID によって特定の認証子を許可またはブロックします。 詳細については、「 Authenticator Attestation GUID」を参照してください。 |

#### パスキー プロファイルのユース ケースの例

注

デバイス バインドパスキーと同期パスキーの両方のパスキー プロファイルがMicrosoft Authenticatorをターゲットとする場合、ユーザーは iOS バージョン 6.8.37 または Android バージョン 6.2507.4749 Microsoft Authenticator実行する必要があります。

##### 高い特権を持つアカウントに関する特別な考慮事項

| Passkey プロファイル | 対象グループ | パスキーの種類 | 認定の実施 | 主な制限事項 |
| --- | --- | --- | --- | --- |
| すべてのデバイス バインド パスキー (構成証明が適用されます) | IT 管理者役員エンジニアリング | デバイスに結び付けられた | 有効 | Disabled |
| 同期されたすべてのパスキーまたはデバイスに結び付けられたパスキー | HRSales | デバイス結合、同期済み | Disabled | Disabled |

##### Microsoft Authenticator でのパスキーの対象となるロールアウト

| Passkey プロファイル | 対象グループ | パスキーの種類 | 認定の実施 | 主な制限事項 |
| --- | --- | --- | --- | --- |
| すべてのデバイス バインド パスキー (Microsoft Authenticatorを除く) | すべてのユーザー | デバイスに結び付けられた | 有効 | 有効- 動作: ブロック- AAGUIDs: iOS 用のMicrosoft Authenticator、Android 用Microsoft Authenticator |
| Microsoft Authenticatorのパスキー | パイロット グループ 1パイロット グループ 2 | デバイスに結び付けられた | 有効 | 有効- 動作: 許可- AAGUIDs: iOS 用のMicrosoft Authenticator、Android 用Microsoft Authenticator |

#### パスキー (FIDO2) の Authenticator 構成証明 GUID (AAGUID)

FIDO2 仕様では、各パスキー ベンダーが登録時に Authenticator Attestation GUID (AAGUID) を提供する必要があります。 AAGUID は、製造元やモデルなどのキーの種類を表す 128 ビットの識別子です。 デスクトップおよびモバイル デバイスのパスキー (FIDO2) プロバイダーも登録時に AAGUID を提供することが求められます。

注

ベンダーは、自身が作成するすべての実質的に同一のセキュリティ キーまたはパスキー (FIDO2) プロバイダー全体で AAGUID が同一であり、他のすべての種類のセキュリティ キーまたはパスキー (FIDO2) プロバイダーの AAGUID とは (高い確率で) 異なることを保証する必要があります。 これを保証するために、特定のセキュリティ キー モデルまたはパスキー (FIDO2) プロバイダーの AAGUID はランダムに生成する必要があります。 詳細については、「 [Web 認証: 公開キー資格情報にアクセスするための API - レベル 2 (w3.org)」](https://w3c.github.io/webauthn/)を参照してください。

パスキーベンダーと協力してパスキー (FIDO2) の AAGUID を確認するか、[Microsoft Entra IDで構成証明の対象となるFIDO2セキュリティキーを参照してください](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-fido2-hardware-vendor#fido2-security-keys-eligible-for-attestation-with-microsoft-entra-id)。 パスキー (FIDO2) がすでに登録されている場合は、ユーザーのパスキー (FIDO2) の認証方法の詳細を表示することで、AAGUID を見つけることができます。

[Image: パスキーの AAGUID を表示する方法のスクリーンショット。]

#### Passkey プロファイルの前提条件

- デバイスは、パスキー (FIDO2) 認証をサポートする必要があります。 Microsoft Entra IDに参加しているWindowsデバイスの場合、Windows 10バージョン 1903 以降が最適です。 ハイブリッド参加済みデバイスは、バージョン 2004 以降Windows 10実行する必要があります。
- デバイス バインドパスキーと同期パスキーの両方のパスキー プロファイルがMicrosoft Authenticatorをターゲットとする場合、ユーザーは iOS バージョン 6.8.37 または Android バージョン 6.2507.4749 Microsoft Authenticator実行する必要があります。
- ポリシー サイズの制限:
    - **Passkey (FIDO2)** ポリシーでは、20 KB のサイズ制限がサポートされています。 サイズ制限に達した後は、パスキー プロファイルをさらに保存することはできません。
    - 参照サイズ:
        - 変更なしの基本パスキー ポリシー: 1.44 KB
        - 1 つのパスキープロファイルが適用されたターゲット: 0.23 KB
        - 5つのパスキープロファイルが適用されたターゲット: 0.4 KB
        - AAGUID のないパスキー プロファイル: 0.4 KB
        - 10 個の AAGUID を持つパスキープロファイルは 0.3 KB。
- ユーザーは、パスキー (FIDO2) を登録する前に、過去 5 分以内に多要素認証 (MFA) を完了する必要があります。
- ユーザーには、Microsoft Entra IDの構成証明要件をサポートする認証システムが必要です。 詳細については、「[Microsoft Entra ID FIDO2 セキュリティ キー ベンダーの認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-fido2-hardware-vendor)を参照してください。

#### パスキー プロファイルを有効にする

注

パスキー プロファイルを有効にすると、グローバル パスキー (FIDO2) ポリシー設定が既定の **パスキー プロファイル**に自動的に転送されます。 既定のパスキー プロファイルを含め、最大 3 つの **パスキー プロファイル**がサポートされています。 より多くのパスキー プロファイルのサポートが開発中です。

1. 少なくとも [Authentication ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)としてMicrosoft Entra 管理センターにサインインします。
2. **Entra ID**&gt;**Security**&gt;**認証方法**&gt;**ポリシー** に移動します。
3. **Passkey (FIDO2) を**選択します。 バナー テキスト内のリンクを選択して、passkeys プロファイルの使用をオプトインします。

    注

    パスキー プロファイルを有効にすることをオプトインした後は、オプトアウトできません。

    [Image: パスキー プロファイルを有効にする方法を示すスクリーンショット。]
4. [ **構成** ] タブで、[ **セルフサービスの設定を許可する** ] を **[はい**] に設定します。 **[いいえ**] に設定すると、認証方法ポリシーでパスキー (FIDO2) が有効になっている場合でも、[ユーザーはセキュリティ情報](https://mysignins.microsoft.com/security-info)を使用してパスキーを登録できません。 この設定はグローバル ポリシーです。プロファイル レベルではありません。
5. 既定の **パスキー プロファイル**を選択します。

    [Image: 既定のパスキー プロファイルを示すスクリーンショット。]

    **[パスキーの種類**] で、許可するパスキーの種類を選択します。
6. **保存**を選びます。

#### 新しいパスキー プロファイルを作成する

1. [ **構成** ] タブで、[ **+ パスキー プロファイルの追加]** を選択します。
2. プロファイルの詳細を入力します。

    [Image: パスキー プロファイルを追加する方法を示すスクリーンショット。]

    Warnung

    - [ **構成証明の強制]** を **[はい**] に設定した場合、登録時に構成証明が必要になります。 Microsoft Entra IDは、認証子の作成とモデルを信頼されたメタデータと照らして検証できます。 構成証明は、パスキーが本物であり、指定されたベンダーからのものであることを組織に保証します。 強制構成証明が **No** に設定されている場合、Microsoft Entra IDは、パスキーに関する属性 (同期またはデバイス バインドなど) を保証できません。
    - 構成証明の適用は、登録時にのみパスキー (FIDO2) が許可されるかどうかを制御します。 構成証明なしでパスキー (FIDO2) を登録したユーザーは、後で **構成証明の強制** が **はい** に設定されている場合でも、サインインをブロックされません。

    その他のベンダー構成証明の要件については、FIDO2 セキュリティ キー ベンダーの[Microsoft Entra ID構成証明](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-fido2-hardware-vendor)を参照してください。

    **キー制限ポリシー**

    - **キー制限の適用** は、組織が特定のセキュリティ キー モデルまたはパスキー プロバイダー (AAGUID によって識別される) のみを許可または禁止する場合にのみ **、[はい** ] に設定する必要があります。 セキュリティ キー ベンダーと協力して、パスキーの AAGUID を特定できます。 パスキーがすでに登録されている場合は、ユーザーのパスキーの認証方法の詳細を表示することで、AAGUID を見つけることができます。

    Warnung

    - キー制限により、特定のモデルまたはプロバイダーが登録と認証の両方に使用できるかどうかを設定します。 キーの制限を変更し、以前は許可していた AAGUID を削除すると、許可された方法を前に登録していたユーザーはサインインに使用できなくなります。
    - [ **構成証明の適用** ] が **[いいえ**] に設定されている場合は、厳密なセキュリティ制御ではなく、AAGUID リストをポリシー ガイドとして使用します。
3. **保存**を選びます。

#### パスキー プロファイルを対象グループに適用する

1. **[有効] と [ターゲット] を選択します**。
2. [ **ターゲットの追加]** を選択し、[ **すべてのユーザー** ] または **[ターゲットの選択** ] を選択して特定のグループを選択します。

    [Image: パスキー プロファイルのターゲットを追加する方法を示すスクリーンショット。]
3. 特定のターゲットに割り当てるパスキー プロファイルを選択します。

    [Image: パスキー プロファイルを選択する方法を示すスクリーンショット。]

    注

    ターゲット グループ (エンジニアリングなど) は、複数のパスキー プロファイルのスコープを設定できます。 ユーザーが複数のパスキー プロファイルのスコープに設定されている場合、パスキーがスコープ付きパスキー プロファイルの少なくとも 1 つの要件を完全に満たしている場合、パスキーを使用した登録と認証が許可されます。 チェックの順序は特にありません。 ユーザーが **Passkeys (FIDO2)** 認証方法ポリシーで除外されたグループのメンバーである場合、ユーザーは FIDO2 パスキーの登録またはサインインから完全にブロックされ、これが含 **まれる** グループに属しているグループよりも優先されます。

#### パスキー プロファイルを削除する

1. **設定**を選択します。
2. 削除するパスキー プロファイルの横にある削除アイコンを選択し、[保存] を選択 **します**。

    注

    プロファイルは、[ **有効化とターゲット]** のユーザー グループに割り当てられない場合にのみ削除できます。 削除アイコンが使用できない場合は、最初にそのプロファイルが割り当てられているターゲットを削除します。

    [Image: パスキー プロファイルを削除する方法を示すスクリーンショット。]

### 同期されたパスキー (FIDO2)

#### 同期パスキーとデバイスバウンドパスキーとは何か

パスキーは、フィッシングに強い強力な認証を提供する FIDO2 ベースの資格情報です。 Microsoft Entra IDでは、次の 2 種類のパスキーがサポートされています。

- デバイス バインド パスキー: 秘密キーは作成され、1 つの物理デバイスに格納され、残されることはありません。 例：
    - Microsoft Authenticator (iOS)
    - Microsoft Authenticator (Android)
    - セキュリティ キー
- 同期されたパスキー: 秘密キーは、ハードウェア セキュリティ モジュール (HSM) によって作成され、ローカル デバイスで暗号化されます。 この暗号化されたキーは同期され、クラウド パスキー プロバイダーに格納されます。 その後、パスキー プロバイダーで認証された他のデバイスで、パスキーを使用できます。 これはプロバイダーによって異なる場合があります。 同期されたパスキーは構成証明をサポートしていません。 例：
    - [Apple iCloud キーチェーン](https://support.apple.com/en-us/102195)
    - [Google パスワード マネージャー](https://security.googleblog.com/2022/10/SecurityofPasskeysintheGooglePasswordManager.html)

注

同期されたパスキーをフィッシングに強い資格情報として扱いますが、他の見当もついていない認証子と同じセキュリティ体制を使用します。

#### 同期されたパスキーの要件

- 少なくとも [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) のアクセス許可を持つアカウント。
- 次の表は、同期されたパスキーを使用するための最小デバイス要件の概要を示しています。 列は、ユーザーがサインインするデバイス プラットフォームを表します。

    | Passkey プロバイダー | Windows | macOS | iOS | Android |
    | --- | --- | --- | --- | --- |
    | Apple パスワード (iCloud キーチェーンとも呼ばれます) | N/A | ネイティブに組み込まれています。macOS 13 以降 | ネイティブに組み込まれています。iOS 16 以降 | N/A |
    | Google パスワード マネージャー | Chrome に組み込まれている | Chrome に組み込まれている | Chrome に組み込まれています。 iOS 17 以降 | ネイティブに組み込まれています (Samsung デバイスを除く)。 Android 9 以降 |
    | その他のパスキー プロバイダー (1Password、Bitwarden など) | ブラウザー拡張機能を確認する | ブラウザー拡張機能を確認する | アプリを確認します。 iOS 17 以降 | アプリを確認します。 Android 14 以降 |

#### 同期されたパスキーを有効にする

まだパスキー プロファイルをオプトインしておらず、構成証明の適用がオフになっている場合、同期されたパスキーは既に有効になっています。 それ以外の場合は、次の手順に従って同期されたパスキーを有効にすることができます。

1. 少なくとも [Authentication ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)としてMicrosoft Entra 管理センターにサインインします。
2. パスキー プロファイルが有効になっていることを確認します。
3. **Entra ID**&gt;**Security**&gt;**認証方法**&gt;**ポリシー** に移動します。
4. **Passkey (FIDO2)**&gt;**Configure** を選択します。
5. プロファイルを追加するか、既存のプロファイルを編集します。
6. [ **パスキーの種類**] で [ **同期済み**] を選択し、プロファイルを保存します。

注

特定のパスキー プロファイルに対して同期されたパスキーを無効にした場合、対象ユーザーは既にパスキーを登録している場合でも、同期されたパスキーでサインインできません。

#### パスキー (FIDO2) を削除する

ユーザー アカウントにひも付けているパスキー (FIDO2) を削除するには、ユーザーの認証方法から削除します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインし、パスキー (FIDO2) を削除する必要があるユーザーを検索します。
2. [**認証方法**] を選択し&gt;**パスキー (デバイス バインド)** を右クリックし、[削除] を選択**します**。

#### パスキー (FIDO2) サインインを適用する

ユーザーが機密リソースにアクセスするときにパスキー (FIDO2) を使用してサインインさせるには、次を行います。

- フィッシングに強い、組み込みの認証強度を使用する

    または
- カスタム認証強度を作成する

次の手順では、カスタム認証強度を作成する方法を示します。 これは、特定のセキュリティ キー モデルまたはパスキー (FIDO2) プロバイダーに対してのみパスキー (FIDO2) サインインを許可する条件付きアクセス ポリシーです。 FIDO2 プロバイダーの一覧については、[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-fido2-hardware-vendor)での構成証明の対象となるFIDO2 セキュリティ キーを参照してください。

1. 少なくとも [Microsoft Entra 管理センター](https://entra.microsoft.com) に [Conditional Access Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) としてサインインします。
2. **Entra ID**&gt;**認証方法**&gt;**認証の強度** に移動します。
3. [ **新しい認証強度**] を選択します。
4. 新しい認証強度の **名前** を指定します。
5. 必要に応じて **、[説明] を指定します**。
6. **パスキー (FIDO2)** を選択します。
7. 必要に応じて、特定の AAGUID を制限する場合は、[**詳細オプション**]&gt;**[AAGUID の追加**] を選択します。 AAGUID を入力し、[ **保存]** を選択します。
8. [ **次へ** ] を選択し、ポリシーの構成を確認します。

### Microsoft Graph API (プレビュー) を使用して FIDO2 セキュリティ キーをプロビジョニングする

現在プレビュー段階では、管理者は [Microsoft Graph およびカスタム クライアントを使用して、ユーザーに代わって FIDO2 セキュリティ キーをプロビジョニングできます](https://aka.ms/passkeyprovision)。 プロビジョニングには、 [認証管理者ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) または UserAuthenticationMethod.ReadWrite.All アクセス許可を持つクライアント アプリケーションが必要です。 プロビジョニングの改善点は次のとおりです。

- Microsoft Entra IDから WebAuthn **creation Options** を要求する機能
- プロビジョニングされたセキュリティ キーをMicrosoft Entra IDに直接登録する機能

これらの新しい API を使用すると、組織はユーザーに代わってセキュリティ キーにパスキー (FIDO2) 資格情報をプロビジョニングする独自のクライアントを構築できます。 このプロセスを簡略化するには、主要な手順 3 つが必要です。

1. ユーザーの**要求** creationOptions: パスキー (FIDO2) 資格情報をプロビジョニングするために Microsoft Entra ID がクライアントに必要なデータを返します。 これには、ユーザー情報、証明書利用者 ID、資格情報ポリシー要件、アルゴリズム、登録チャレンジなどの情報が含まれます。
2. 作成オプションを使用してパスキー (FIDO2) 資格情報を**プロビジョニング**します。`creationOptions`を使用し、クライアント認証プロトコル (CTAP) をサポートするクライアントを用いて資格情報を設定してください。 この手順では、セキュリティ キーを挿入し、PIN を設定する必要があります。
3. **登録** プロビジョニングされた資格情報を Microsoft Entra ID に: プロビジョニング プロセスからの書式設定された出力を使用し、ターゲット ユーザーのパスキー (FIDO2) 資格情報を登録するために必要なデータを Microsoft Entra ID に提供します。

[Image: パスキー (FIDO2) をプロビジョニングするために必要な手順を示す概念図。]

### 既知の問題

#### セキュリティ キーのプロビジョニング

セキュリティ キーの管理者プロビジョニングはプレビュー段階です。 ユーザーに代わって FIDO2 セキュリティ キーをプロビジョニングする[Microsoft Graphおよびカスタム クライアント](https://aka.ms/passkeyprovision)を参照してください。

#### ゲスト ユーザー

パスキー (FIDO2) 資格情報の登録は、リソース テナント内の B2B コラボレーション ユーザーを含む、内部または外部のゲスト ユーザーではサポートされていません。

#### UPN の変更

ユーザーの UPN が変更されると、その変更に対応するためにパスキー (FIDO2) を変更することはできなくなります。 ユーザーがパスキー (FIDO2) を持っている場合は、 [セキュリティ情報](https://mysignins.microsoft.com/security-info)にサインインし、古いパスキー (FIDO2) を削除して、新しいパスキーを追加する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-authentication-qr-code"} -->
## Microsoft Entra ID で QR コード認証を有効にする方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-qr-code
- Service: entra-id / authentication
- Article date: 2025-06-24
- Summary: Microsoft Entra ID で QR コード認証方法を有効にして、現場担当者のサインイン イベントを改善し、セキュリティで保護する方法について説明します。

このトピックでは、Microsoft Entra ID の認証方法ポリシーで QR コード認証方法を有効にする方法について説明します。 また、ユーザーの QR コード認証方法を管理する方法と、ユーザーが QR コードと PIN を使用してサインインする方法についても説明します。

QR コード認証方法を有効にする前に、現場担当者の職場または自宅のアクセスにセキュリティ制御を使用するためのベスト プラクティスを確認してください。 詳細については、「 [現場担当者を保護するためのベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/identity-platform/security-best-practices-for-frontline-workers)」を参照してください。

### QR コード認証方法を有効にする前提条件

- 有効な Azure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、 [アカウントを作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- サブスクリプションに関連付けられている Microsoft Entra テナント。
    - 必要に応じて、 [Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/entra/fundamentals/sign-up-organization) するか、 [Azure サブスクリプションをアカウントに関連付けます](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-subscriptions-associated-directory)。
- QR コード認証方法を有効にするには、少なくとも Microsoft Entra テナントの [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) ロールが必要です。
- QR コード認証方法ポリシーで有効になっている各ユーザーは、使用しない場合でもライセンスを取得する必要があります。 有効な各ユーザーは、次の Microsoft Entra ID、EMS、Microsoft 365 ライセンスのいずれかを保持している必要があります。
    - [Microsoft 365 F1 または F3](https://www.microsoft.com/licensing/news/m365-firstline-workers)
    - [Microsoft Entra ID P1 または P2](https://www.microsoft.com/security/business/microsoft-entra-pricing)
    - [Enterprise Mobility + Security (EMS) E3 または E5](https://www.microsoft.com/microsoft-365/enterprise-mobility-security/compare-plans-and-pricing) または [Microsoft 365 E3 または E5](https://www.microsoft.com/microsoft-365/compare-microsoft-365-enterprise-plans)
    - [Office 365 F3](https://www.microsoft.com/microsoft-365/business/office-365-f3?activetab=pivot%3aoverviewtab)
- Android、iOS、または iPadOS (iOS/iPadOS バージョン 15.0 以降) の共有デバイス。
- 共有デバイスで有効になっている共有デバイス モード (省略可能ですが、強くお勧めします)。
- 2 インチ x 2 インチの QR コードを印刷するプリンター。
- Teams で QR コード認証にアクセスするには、共有デバイスにインストールされている Teams アプリには、Android バージョン 1.0.0.2024143204 以降、iOS バージョン 1.0.0.77.2024132501 以降が必要です。
- 現場管理者[がマイ スタッフを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/my-staff-configure)使用して QR コードと PIN をプロビジョニング、管理、リセットする場合は、マイ スタッフ ポータルを設定します。

### QR コード認証方法を有効にする

QR コード認証方法は、Microsoft Entra 管理センターまたは Microsoft Graph API を使用して有効にすることができます。

#### Microsoft Entra 管理センターで QR コード認証方法を有効にする

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **Entra ID**&gt;**認証方法**&gt;**ポリシー** に移動します。
3. [c0>QRコードQRコードを有効にして対象を選択対象を追加 QRコードでサインインする必要があるユーザーをグループから選択します。

    [Image: 組織の QR コードを有効にする方法を示すスクリーンショット。]
4. 必要に応じて、既定の QR コード設定を更新します。

    - 既定では、PIN の長さは 8 桁です。 PIN の長さは 8 ~ 20 桁です。 PIN の長さを増やすと、新しい値は PIN に必要な最小桁数になります。 たとえば、PIN の長さを 10 に増やす場合、ユーザーは次のサインイン時に 10 桁の PIN を指定する必要があります。
    - 標準 QR コードの既定の有効期間 (長期使用のためにユーザーに提供) は 365 日です。 範囲は 1 ~ 395 日です。 特定のユーザーに対して QR コード認証方法を追加するときに、標準の QR コードの有効期間を変更できます。

    [Image: QR コードの設定を更新する方法を示すスクリーンショット。]
5. 完了したら、[ **保存**] をクリックします。

#### Microsoft Graph API で QR コード認証方法を有効にする

この例では、PIN の長さが 10 桁で、標準 QR コードの有効期間が 395 日のグループに対して QR コード認証を有効にします。

- **依頼**

    ```https
    PATCH https://graph.microsoft.com/beta/policies/authenticationMethodsPolicy/authenticationMethodConfigurations/qrCodePin
    {
      "@odata.type" : "microsoft.graph.qrCodePinAuthenticationMethodConfiguration", 
      "id": "qrCodePin", 
      "state": "enabled", 
      "includeTargets": [{ 
        "targetType": "group", 
        "id": "b185b746-e7db-4fa2-bafc-69ecf18850dd", 
        }], 
      "excludeTargets": [], 
      "standardQRCodeLifetimeInDays":395,
      "pinLength": 10
    }
    ```
- **応答**

    ```https
    204 No Response
    ```

### ユーザーの QR コード認証方法を追加する

Microsoft Entra 管理センター、マイ スタッフ、または Microsoft Graph API を使用して、ユーザーの QR コード認証方法を追加できます。 一度に許可されるアクティブな QR コード認証方法は 1 つだけです。 "認証方法の追加" 中に標準 QR コードが生成されます。 ユーザーが標準 QR コードを持っていない場合は、有効期間が短い一時 QR コードを追加できます。 標準/一時 QR コードを削除して、新しい標準/一時 QR コードを追加できます。 ユーザーは、任意の時点で 1 つの Standard と 1 つの一時 QR コードのみをアクティブにすることができます。

#### Microsoft Entra 管理センターでユーザーの QR コード認証方法を追加する

1. 少なくとも[認証管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator)にサインインします。
2. [ **ユーザー**] に移動し、ユーザーを選択して、[ **認証方法**] をクリックします。
3. [ **認証方法の追加]** をクリックし、[ **QR コード**] を選択します。

    [Image: ユーザーの QR コードを選択する方法を示すスクリーンショット。]
4. 必要に応じて、ユーザーの有効期限を変更します。 **アクティブ化時間**を今以降に設定します。 一時的な PIN を指定または生成します。 カスタム PIN は、QR コード認証方法を追加する場合にのみ指定できます。 リセット イベント中に PIN が自動生成されます。 準備ができたら、[ **追加** ] をクリックして、ユーザーの QR コード認証方法を追加します。

    [Image: ユーザーの QR コードを追加する方法を示すスクリーンショット。]
5. PIN を保存し、[ **イメージのダウンロード** ] をクリックして QR コードをダウンロードして印刷します。 QRコード画像のダウンロードは、最小の最適な印刷サイズを持っています。 QR コードのサイズを小さくすると、QR コード スキャンのパフォーマンスに影響する可能性があります。

    一意のシークレットがあるため、同じ QR コードを再生成することはできません。 何らかの理由で QR コードが機能しない場合は、削除します。 ユーザーの新しい QR コードを作成します。

    [Image: ユーザーの QR コードイメージをダウンロードする方法を示すスクリーンショット。]
6. QR コード認証方法を追加すると、ユーザーが使用できる認証方法として表示されます。

    [Image: ユーザーの使用可能な認証方法に記載されている QR コード認証方法を示すスクリーンショット。]

#### マイ スタッフにユーザーの QR コード認証方法を追加する

1. フロント ライン マネージャーとしてマイ スタッフ ポータルにサインインします。 管理単位と現場担当者を選択します。

    [Image: 管理単位を選択する方法を示すスクリーンショット。]

    [Image: ユーザーを選択する方法を示すスクリーンショット。]
2. [ **QR コード認証方法の管理**] をクリックします。

    [Image: QR コード認証方法を管理する方法を示すスクリーンショット。]
3. [ **QR コード メソッドの追加]** をクリックします。

    [Image: QR コード認証方法を追加する方法を示すスクリーンショット。]
4. 有効期限とアクティブ化の日付を指定し、[ **追加** ] をクリックして、ユーザーの QR コードと PIN を生成します。

    [Image: QR コード認証方法のアクティブ化日を設定する方法を示すスクリーンショット。]
5. PIN を保存し、QR コードをダウンロードまたは印刷して、[ **完了**] をクリックします。 QRコード画像のダウンロードは、最小の最適な印刷サイズを持っています。 サイズを小さくすると、QR コードのスキャンが困難になります。 一意のシークレットがあるため、同じ QR コードを再生成することはできません。 何らかの理由で QR コードが機能しない場合は、削除します。 ユーザーの新しい QR コードを作成します。

    [Image: 管理者が QR コードを追加した後の QR コード認証方法を示すスクリーンショット。]

#### Microsoft Graph API でユーザーの QR コード認証方法を追加する

この例では、ユーザーの QR コード認証方法を追加します。

- **依頼**

    ```https
    HTTP PUT/users/{id | userPrincipalName}/authentication/qrCodePinMethod

    {
      "standardQRCode": {
        "expireDateTime": "2024-12-30T12:00:00Z",
        "startDateTime": "2024-10-30T12:00:00Z"
      },
      "pin": {
        "code": "<PIN>"
      }
    }
    ```
- **応答**

    ```https
    HTTP/1.1 201 Created
    Location: /beta/users/aaaaaaaa-bbbb-cccc-1111-222222222222/authentication/qrCodePinMethod`
    Content-type: application/json
    
    {
      "standardQRCode": {
        "id": "BBBBBBBB-1C1C-2D2D-3E3E-444444444444"
        "expireDateTime": "2024-12-30T12:00:00Z",
        "startDateTime": "2024-10-30T12:00:00Z"
        "createdDateTime": "2024-10-30T12:00:00Z",
        "lastUsedDateTime": null,
         "image":
            {
      "binaryValue": "<binaryImageData>",
             "version": 1,
             "errorCorrectionLevel": "H".
             "rawContent": <binary data encoded in QR>        
      }
        },
      "temporaryQRCode": null,
      "pin": {
        "code": "<PIN>",
        "isForcePinChangeRequired": true,
        "createdDateTime": "2024-10-30T12:00:00Z",
        "updatedDateTime": null
      }  
    }
    ```

この例では、ユーザーに QR コード認証方法が追加されているかどうかを確認します。

- **依頼**

    ```https
    GET https://graph.microsoft.com/beta/users/flokreg@contoso.com/authentication/qrCodePinMethod`
    ```
- **応答**

    ```https
    HTTP/1.1 200 OK
    Content-type: application/json
    
    {
      "id": "<id>",
      "standardQRCode": {
        "id": "BBBBBBBB-1C1C-2D2D-3E3E-444444444444"
        "image": null,
        "expireDateTime": "2024-12-30T12:00:00Z",
        "startDateTime": "2024-10-30T12:00:00Z"
        "createdDateTime": "2024-10-30T12:00:00Z",
        "lastUsedDateTime": "2024-12-30T12:00:00Z"
      },
      "temporaryQRCode": {
        "id": "CCCCCCCC-2D2D-3E3E-4F4F-555555555555"
        "image": null,
        "expireDateTime": "2024-12-30T12:00:00Z",
        "startDateTime": "2024-10-30T12:00:00Z"
        "createdDateTime": "2024-10-30T12:00:00Z",
        "lastUsedDateTime": "2024-12-30T12:00:00Z"
      },
      "pin": {
        "code": null,
        "isForcePinChangeRequired": false,
        "createdDateTime": "2024-10-30T12:00:00Z",
        "updatedDateTime": "2024-11-30T12:00:00Z"
      }
    }
    
    ```

### ユーザーの QR コード認証方法を編集する

Microsoft Entra 管理センター、マイ スタッフ、または Microsoft Graph API を使用して、ユーザーの QR コード認証方法を編集できます。

#### Microsoft Entra 管理センターでユーザーの QR コード認証方法を編集する

- ユーザーの使用可能な認証方法に移動し、[ **編集** ] をクリックして QR コード認証方法のプロパティを編集します。

    [Image: ユーザーの使用可能な認証方法を編集する方法を示すスクリーンショット。]
- 標準 QR コードの有効期限を変更し、[ **保存**] をクリックします。 編集したら、[ **完了**] をクリックします。

    [Image: 有効期限を変更する方法を示すスクリーンショット。]
- 標準の QR コードを削除します。 標準 QR コードが期限切れ、侵害、または盗難に関する報告を受けた場合は、削除することができます。

    [Image: QR コードを削除する方法を示すスクリーンショット。]

    標準 QR コードを削除した後、記号の追加 (**+**) をクリックして、ユーザーの新しい標準 QR コードを追加します。 削除された QR コードはログインに対して有効ではなくなりました。

    新しい QR コードを印刷してユーザーに配布する必要があります。 ユーザーは既存の PIN を引き続き使用できます。

    [Image: 紛失または盗難にあった QR コードを置き換える方法を示すスクリーンショット。]
- PIN をリセットします。 ユーザー PIN をリセットする必要がある場合は、一時的な PIN を生成し、ユーザーに配布します。 ユーザーは、次のサインイン時に一時的な PIN を変更する必要があります。 マスクされた PIN の後にある鉛筆アイコンをクリックします。 [ **新しい PIN の生成** ] をクリックして、新しい一時 PIN を作成します。 [ **OK] を** クリックして、ユーザーが次のサインイン時に一時的な PIN を強制的に変更することを確認します。 一時 PIN をコピーし、ユーザーと共有します。

    [Image: PIN をリセットする方法を示すスクリーンショット。]
- 一時的な QR コードを追加または削除します。 一時的な QR コードを使用すると、ユーザーがバッジを機能させなかった場合に、バッジの QR コードをプロビジョニングおよびプロビジョニング解除する管理者のオーバーヘッドが軽減されます。 また、シフト後にQRコードを保持するストレスも軽減されます。 一時的な QR コードの有効期間は 1 ~ 12 時間で、すぐにまたは後でアクティブ化できます。 QRコードのプロビジョニングを解除するには、一時的なQRコードを削除するか、有効期限が切れて使用できなくなるのを待つことができます。

    [Image: 一時的な QR コードを追加する方法を示すスクリーンショット。]

    [Image: 一時的な QR コードをダウンロードする方法を示すスクリーンショット。]

#### マイ スタッフのユーザーの QR コード認証方法を編集する

- 標準 QR コードの有効期限を編集するには、[ **編集**] をクリックします。 有効期限を編集し、変更を保存します。

    [Image: マイ スタッフで QR コードを編集する方法を示すスクリーンショット。]
- 標準の QR コードを削除するには、[ **削除**] をクリックしてアクションを確認します。

    [Image: マイ スタッフで QR コードを削除する方法を示すスクリーンショット。]
- 新しい標準 QR コードを追加するには、標準 QR コードの横にある [ **新規追加** ] をクリックします。

    [Image: マイ スタッフに新しい QR コードを追加する方法を示すスクリーンショット。]

    QR コードのアクティブ化時刻と有効期限を選択し、[ **追加**] をクリックします。

    [Image: マイ スタッフで QR コードの有効期限を選択する方法を示すスクリーンショット。]

    QR コードをダウンロードまたは印刷し、[ **完了**] をクリックします。

    [Image: [マイ スタッフ] で新しく追加された QR コードを表示する方法を示すスクリーンショット。]
- 一時 QR コードを追加するには、一時 QR コードの横にある [ **新規追加** ] をクリックします。 有効期間 ( **時間)** と **アクティブ化の日付**を指定し、[ **追加**] をクリックします。

    [Image: 一時的な QR コードの有効期限を設定する方法を示すスクリーンショット。]

    QR コードをダウンロードまたは印刷し、[ **完了**] をクリックします。

    [Image: マイ スタッフで一時的な QR コードを表示する方法を示すスクリーンショット。]
- PIN をリセットするには、[ **PIN のリセット**] をクリックします。

    [Image: マイ スタッフで PIN をリセットする方法を示すスクリーンショット。]

    [ **PIN のコピー** ] をクリックして、PIN をクリップボードにコピーします。

    [Image: マイ スタッフで PIN をコピーする方法を示すスクリーンショット。]

#### Microsoft Graph API でユーザーの QR コード認証方法を編集する

この例では、バッジが失われる場合にユーザーの標準 QR コードを削除し、新しい標準 QR コードを作成する方法を示します。 ユーザーは PIN を変更する必要はありません。

標準 QR コードを削除します。

- **依頼**

    ```https
    DELETE https://graph.microsoft.com/beta/users/flokreg@contoso.com/authentication/qrCodePinMethod/standardQRCode`
    ```
- **応答**

    ```https
    HTTP/1.1 204 No Content
    ```

標準の QR コードを作成します。

- **依頼**

    ```https
    HTTP PATCH/users/{id | userPrincipalName}/authentication/qrCodePinMethod/standardQRCode`

    {
        "startDateTime": "2024-10-30T12:00:00Z",
        "expireDateTime": "2024-12-30T12:00:00Z"
    }
    ```
- **応答**

    ```https
    HTTP/1.1 201 Created
    Location: /beta/users/aaaaaaaa-bbbb-cccc-1111-222222222222/authentication/qrCodePinMethod/standardQRCode`
    Content-type: application/json
    
    {
        "id": "BBBBBBBB-1C1C-2D2D-3E3E-444444444444"
        "expireDateTime": "2024-12-30T12:00:00Z",
        "startDateTime": "2024-10-30T12:00:00Z"
        "createdDateTime": "2024-10-30T12:00:00Z",
        "lastUsedDateTime": null,
         "image":
            {
      "binaryValue": "<binaryImageData>",
             "version": 1,
             "errorCorrectionLevel": "H".
             "rawContent": <binary data encoded in QR>        
      }
      }
    
    ```

標準の QR コードを取得します。

- **依頼**

    ```https
    GET https://graph.microsoft.com/beta/users/{id|UPN}/authentication/qrCodePinMethod/standardQRCode`
    ```
- **応答**

    ```https
    HTTP/1.1 200 OK
    Content-type: application/json
    
    {
        "id": "BBBBBBBB-1C1C-2D2D-3E3E-444444444444",
        "image": null,
        "expireDateTime": "2024-12-30T12:00:00Z",
        "startDateTime": "2024-10-30T12:00:00Z"
        "createdDateTime": "2024-10-30T12:00:00Z",
        "lastUsedDateTime": "2024-12-30T12:00:00Z"
    }
    
    ```

この例では、ユーザーの一時的な QR コードを作成する方法を示します。 ユーザーは既存の PIN を使用できます。 この操作は、ユーザーに対して一時的な QR コードが既に存在する場合、または **expireDateTime が startDateTime** から 12 時間以上経過している場合にエラーを返 **します**。

- **依頼**

    ```https
    HTTP PATCH/users/{id | userPrincipalName}/authentication/qrCodePinMethod/temporaryQRCode`

    {
        "startDateTime": "2024-10-30T12:00:00Z",
        "expireDateTime": "2024-10-30T22:00:00Z"
    }
    ```
- **応答**

    ```https
    HTTP/1.1 201 Created
    Location: /beta/users/aaaaaaaa-bbbb-cccc-1111-222222222222/authentication/qrCodePinMethod/temporaryQRCode`
    Content-type: application/json
    
    {
        "id": "EEEEEEEE-4F$F-5A5A-6B6B-777777777777"
        "expireDateTime": "2024-10-30T22:00:00Z",
        "startDateTime": "2024-10-30T12:00:00Z"
        "createdDateTime": "2024-10-30T12:00:00Z",
        "lastUsedDateTime": null,
         "image":
            {
      "binaryValue": "<binaryImageData>",
             "version": 1,
             "errorCorrectionLevel": "H".
             "rawContent": <binary data encoded in QR>        
      }
      }

    ```

一時的な QR コードを取得します。

- **依頼**

    ```https
    GET https://graph.microsoft.com/beta/users/{id|UPN}/authentication/qrCodePinMethod/temporaryQRCode`
    ```
- **応答**

    ```https
    HTTP/1.1 200 OK
    Content-type: application/json
    
    {
        "id": "EEEEEEEE-4F$F-5A5A-6B6B-777777777777",
        "image": null,
        "expireDateTime": "2024-10-30T22:00:00Z",
        "startDateTime": "2024-10-30T12:00:00Z"
        "createdDateTime": "2024-10-30T12:00:00Z",
        "lastUsedDateTime": "2024-10-30T20:00:00Z"
    }
    
    ```

この例では、ユーザーの一時 QR コードを削除する方法を示します。

- **依頼**

    ```https
    DELETE https://graph.microsoft.com/beta/users/flokreg@contoso.com/authentication/qrCodePinMethod/temporaryQRCode`
    ```
- **応答**

    ```https
    HTTP/1.1 204 No Content
    ```

この例では、QR コード認証方法で PIN をリセットする方法を示します。

- **依頼**

    ```https
    PATCH https://graph.microsoft.com/beta/users/flokreg@contoso.com/authentication/qrCodePinMethod/pin`
    ```
- **応答**

    ```https
    {
      "code": <PIN>,
      "forceChangePinNextSignIn": true,
      "createdDateTime": "2024-10-30T12:00:00Z",
      "updatedDateTime": null
    }
    ```

この例では、QR コード認証方法の PIN をユーザーに強制的に変更させる方法を示します。

- **依頼**

    ```https
    PATCH https://graph.microsoft.com/beta/users/flokreg@contoso.com/authentication/qrCodePinMethod/updatePin`
    
    {
      "currentPin": "<Old PIN>",
      "newPin": "<New PIN>"
    }
    ```
- **応答**

    ```https
    HTTP/1.1 204 No Content
    ```

### ユーザーの QR コード認証方法を削除する

Microsoft Entra 管理センター、マイ スタッフ、または Microsoft Graph API を使用して、ユーザーの QR コード認証方法を削除できます。

#### Microsoft Entra 管理センターでユーザーの QR コード認証方法を削除する

ユーザーの QR コード認証方法が削除された場合、その認証方法を使用してサインインできなくなります。

1. 少なくとも[認証管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator)にサインインします。
2. [ **ユーザー**] に移動し、ユーザーを選択して、[ **認証方法**] をクリックします。
3. [ **使用可能な認証方法**] で、QR コードの右側にある省略記号をクリックし、[ **削除**] をクリックします。

    [Image: Microsoft Entra 管理センターでユーザーの QR コード認証方法を削除する方法を示すスクリーンショット。]

#### マイ スタッフのユーザーの QR コード認証方法を削除する

1. QR コード認証方法自体を削除するには、[ **QR コードメソッドの削除**] をクリックします。

    [Image: マイ スタッフの QR コード認証方法を削除する方法を示すスクリーンショット。]
2. [ **削除** ] をクリックしてアクションを確定します。

    [Image: マイ スタッフの QR コード認証方法の削除を確認する方法を示すスクリーンショット。]

#### Microsoft Graph API でユーザーの QR コード認証方法を削除する

この例では、ユーザーの標準 QR コードを削除する方法を示します。

- **依頼**

    ```https
    DELETE https://graph.microsoft.com/beta/users/flokreg@contoso.com/authentication/qrCodePinMethod/standardQRCode`
    ```
- **応答**

    ```https
    HTTP/1.1 204 No Content
    ```

### QR コードを使用して Microsoft Teams または Managed Home Screen (MHS) にサインインする

Microsoft Teamsおよび Managed Home Screen (MHS) には、最適化された QR コード サインイン エクスペリエンスがあります。 認証ポリシー管理者は、モバイル デバイスの QR コード認証方法を有効にするために、Intune または別のモバイル デバイス管理 (MDM) ソリューションを構成する必要があります。

#### Teams または MHS で QR コードによるサインインを有効にする

Intune で構成する場合は、QR コード認証を追加するすべてのデバイスに必要なアプリとして Microsoft Authenticator を割り当てます。

| プラットホーム | MDM アプリ構成キー | 価値 | 設定の場所 |
| --- | --- | --- | --- |
| iOS | 推奨認証構成 | qrpin | シングル サインオン (SSO) 拡張機能を構成するデバイス管理プロファイル |
| Android | 推奨認証構成 | qrpin | Microsoft Authenticator |

注

MHS は Android デバイスでのみ使用できます。

#### QR コード認証 Teams のサインイン エクスペリエンス

ユーザーは [Teams をダウンロード](https://aka.ms/teamsmobiledownload)する必要があります。 次の表に、モバイル オペレーティング システムの Teams の最小バージョンを示します。 Teams のバージョンの詳細については、「 [新しいアプリとクラシック Microsoft Teams アプリのバージョン更新履歴](https://learn.microsoft.com/ja-jp/officeupdates/teams-app-versioning)」を参照してください。

| モバイル OS | リリース日 | Teams のバージョン |
| --- | --- | --- |
| iOS と iPadOS | 2024 年 7 月 21 日 | 6.13.1 (1.0.0.77.2024132501) |
| Android | 2024 年 8 月 8 日 | 1416/1.0.0.2024143204 (2024143204) |

ユーザーは次の手順に従って、Teams で QR コードでサインインできます。

1. [Microsoft Teamsで **QR コードをスキャン** する] をクリックします。
2. QR コードをスキャンします。 カメラのアクセス許可を求められた場合は、同意します。
3. PIN を入力します。
4. これで、アプリにサインインしました。

    [Image: PIN を入力する方法を示すスクリーンショット。]
5. 一時的な PIN でサインインする場合は、変更する必要があります。

    [Image: PIN を変更する方法を示すスクリーンショット。]

### QR コード認証の Web サインイン エクスペリエンス (login.microsoftonline.com)

1. [**その他のサインイン オプション**] をクリック&gt;**組織にサインイン**&gt;**QR コードでサインイン**します。
2. カメラを許可されるとき&gt; QR コードをスキャンし&gt;、PIN を入力して&gt;、正常にサインインします。

    [Image: Web サインイン エクスペリエンスを示すスクリーンショット。]

### 条件付きアクセス ポリシーを使用して QR コード認証を使用してセキュリティを追加する

QR コード認証方法を、現場担当者、準拠デバイス、共有デバイスのみに制限します。 このセクションでは、QR コード認証方法を現場担当者と共有デバイスのみに制限するポリシーを作成する方法について説明します。

#### QR コード認証を現場担当者に制限する

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **Entra ID**&gt;**Authentication メソッド**&gt;**QR コード**&gt;**Enable と target** を参照します。
3. [**ターゲットの追加]** をクリック&gt;次のスクリーンショットの現場担当者など、**現場担当者**のみを含むグループを選択します。 このグループの選択により、QR コード認証方法の有効化は、現場担当者グループに追加された **現場担当者** のみに制限されます。

    [Image: QR コード設定にグループを追加する方法を示す Microsoft Entra 管理センターを示すスクリーンショット。]

#### QR コード認証を共有デバイスに制限する

1. [条件付きアクセス管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)にサインインします。
2. [ **条件付きアクセス**&gt;**認証の強度**&gt;**新しい認証強度**] をクリックします。

    [Image: 新しい認証強度を作成する方法を示すスクリーンショット。]
3. カスタム認証強度条件付きアクセス ポリシーを作成します。 認証 **QR コード**を選択します。
4. 共有デバイスを Intune または別の MDM ソリューションのポリシーに準拠しているとマークする必要がある条件付きアクセス ポリシーを作成します。 このポリシーにより、現場担当者は、QR コードでサインインした準拠している共有デバイスから特定のリソースにのみアクセスできるようになります。

    1. [**[ユーザーまたはワークロード ID]**&gt;**[含める]**&gt;**[ユーザーとグループ]**を選択し、**[最前線のワーカー]**の現場担当者グループを選択します。
    2. [ **ターゲット リソース**&gt;**Include**&gt; 現場担当者がアクセスできる特定のリソースを選択します。
    3. [ **条件**] で、[ **デバイスのフィルター]** をクリックし、[ **構成] を** **[はい**] に設定します。
    4. [ **ポリシーからフィルター処理されたデバイスを含める**] をクリックします。
    5. **[プロパティ**] で[**ProfileType]\(プロファイルの種類\**) を選択します。
    6. **演算子**で**等しい**を選択します。
    7. **値**で**共有**を選択します。

        [Image: 認証強度のためにポリシーからフィルター処理されたデバイスを含める方法を示すスクリーンショット。]
    8. **[アクセス制御]**&gt;**[許可]**&gt; で、**[デバイスは準拠としてマーク済みである必要があります]** を選択してから、**[選択]** をクリックします。
    9. [ **作成**] をクリックします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-authentication-sms-supported-apps"} -->
## Microsoft Entra ID の SMS ベースの認証に対するアプリのサポート - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-sms-supported-apps
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: ユーザーが SMS を使用して Microsoft Entra ID にサインインするためにサポートされているアプリについて説明します

Microsoft ID プラットフォーム (Microsoft Entra ID) と統合された Microsoft アプリでは、SMS ベースの認証を利用できます。 この記事では、SMS ベースの認証をサポートする Web アプリとモバイル アプリの一覧を示します。

### フィッシングに強い最新の認証に移行する

Important

Microsoft では、セキュリティを強化するために、フィッシングに強い認証方法を推奨しています。 ユーザーを次のいずれかの方法に移行することを検討してください。

- [パスキー (FIDO2)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)
- [Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-overview)
- [証明書ベースの認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication)

Microsoft ID プラットフォーム (Microsoft Entra ID) と統合された Microsoft アプリでは、SMS ベースの認証を利用できます。 この表は、SMS ベースの認証がサポートされている Web およびモバイル アプリの一部を示しています。 アプリを追加または検証したい場合は、[お問い合わせください](https://feedback.azure.com/d365community/forum/22920db1-ad25-ec11-b6e6-000d3a4f0789)。

| アプリ | Web/ブラウザー アプリ | ネイティブ モバイル アプリ |
| --- | --- | --- |
| Office 365 - Microsoft Online Services\* | ● |  |
| Microsoft One Note | ● |  |
| Microsoft Teams | ● | ● |
| 会社のポータル サイト | ● | ● |
| マイ アプリ ポータル | ● | 使用できません |
| Microsoft Forms | ● | 使用できません |
| Microsoft Edge | ● |  |
| Microsoft Power BI | ● |  |
| Microsoft Stream | ● |  |
| マイクロソフト パワー アプリ | ● |  |
| Microsoft Azure | ● | ● |
| Azure Virtual Desktop | ● |  |

 \* *Word や Excel などの Office アプリケーションでは、SMS サインインは Web で直接アクセスした場合は利用できませんが、[Office 365 Web アプリ](https://www.office.com)*を使用してアクセスした場合は利用できます。

上記の Microsoft アプリでは、SMS サインインがサポートされています。これは、ユーザーが電話番号と SMS コードを入力できる、Microsoft ID ログイン (`https://login.microsoftonline.com/`) が使用されるためです。

### サポートされていない Microsoft アプリ

Web で直接アクセスされる Microsoft 365 デスクトップ (Windows または Mac) アプリと Microsoft 365 Web アプリ (MS One Note を除く) では SMS サインインはサポートされません。 これらのアプリでは、サインインにパスワードを必要とする Microsoft Office ログイン (`https://office.live.com/start/*`) が使用されます。 同様の理由で、Microsoft Office モバイル アプリ (Microsoft Teams、会社のポータル サイト、Microsoft Azure を除く) では SMS サインインはサポートされません。

| サポートされていない Microsoft アプリ | 例 |
| --- | --- |
| ネイティブのデスクトップ Microsoft アプリ | Microsoft Teams、Microsoft 365 アプリ、Word、Excel など。 |
| ネイティブのモバイル Microsoft アプリ (Microsoft Teams、会社のポータル サイト、Microsoft Azure を除く) | Outlook、Edge、Power BI、Stream、SharePoint、Power Apps、Word など。 |
| Microsoft 365 Web アプリ (Web で直接アクセス) | [Outlook](https://outlook.live.com/owa/)、[Word](https://office.live.com/start/Word.aspx)、[Excel](https://office.live.com/start/Excel.aspx)、[PowerPoint](https://office.live.com/start/PowerPoint.aspx) |

### Microsoft 以外のアプリのサポート

Microsoft 以外のアプリを SMS サインイン機能と互換性のあるものにするには:

- Microsoft 以外の Web アプリをMicrosoft Entra ID と統合し、Microsoft Entra 認証を使用します。 Security Assertion Markup Language ([SAML](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-sso)) または OpenID Connect ([OIDC](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)) を使用して、Microsoft Entra SSO と統合します。
- Microsoft Entra アプリケーション プロキシ を使用して、Microsoft 以外のオンプレミス アプリ Microsoft Entra ID と統合する
- Microsoft 以外のクライアント アプリと、認証用の [Microsoft ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-overview)を統合します
    - [サンプルの iOS アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-ios)
    - [サンプルの Android アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-android)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-authentication-track-linkable-identifiers"} -->
## Microsoft Entraでリンク可能な識別子を使用して ID アクティビティを追跡および調査する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-track-linkable-identifiers
- Service: entra-id / hybrid
- Article date: 2026-09-02
- Summary: Microsoft Entraのセッション ID や一意のトークン識別子などのリンク可能な識別子が、ID 関連のアクティビティの追跡と調査に役立ち、セキュリティと透明性を強化する方法について説明します。

Microsoftは、アクティビティを 1 つのルート認証イベントに関連付けるために、すべてのアクセス トークンに特定の識別子を埋め込みます。 これらのリンク可能な識別子は、脅威ハンターやセキュリティ アナリストが ID ベースの攻撃の調査と軽減を行うのをサポートするために、顧客向けのログに表示されます。 これらの識別子を使用することで、セキュリティの専門家は、セッションとトークン全体の悪意のあるアクティビティをより効果的にトレース、分析、および対応できるため、環境の透明性とセキュリティの両方が強化されます。

### リンク可能な識別子の種類

高度な ID 調査と脅威ハンティングのシナリオをサポートするために使用されるリンク可能な識別子には、セッション ID ベースの識別子と一意のトークン識別子の 2 種類があります。

#### セッション ID ベースの識別子

セッション ID (SID ベースの識別子) に基づく識別子を使用すると、 [アクセス トークン (AT)](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)、 [更新トークン (RT)](https://learn.microsoft.com/ja-jp/entra/identity-platform/refresh-tokens)、1 つのルート認証イベントから発行されたセッション Cookie など、すべての認証成果物の関連付けが可能になります。 この識別子は、セッション全体のアクティビティを追跡する場合に特に便利です。

SID ベースの一般的な調査シナリオは次のとおりです。

- **サービス間でアクティビティを関連付ける**: Microsoft Entraサインイン ログからセッション ID で開始します。 Exchange Online監査ログやMicrosoft Graphアクティビティ ログなどのワークロード ログと結合します。 その後、同じセッション ID を共有するアクセス トークンによって実行されるすべてのアクションを識別できます。
- **ユーザーまたはデバイスでフィルター処理**する: UserId または DeviceId を使用して結果を絞り込むか、特定のセッション期間内に発行されたトークンをフィルター処理します。
- **セッションの列挙**: 特定のユーザー (UserId) またはデバイス (DeviceId) に対してアクティブなセッションの数を決定します。
- **認証成果物間のリンク**: SID 要求は対話型認証中に生成され、プライマリ更新トークン (PRT)、更新トークン、またはセッション Cookie に含まれます。 これらのソースから発行されたすべてのアクセス トークンは同じ SID を継承し、認証成果物間で一貫したリンケージを有効にします。

#### 一意のトークン識別子

一意トークン識別子 (UTI) は、すべての Microsoft Entra [access トークン (AT)](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens) または [ID トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens)に埋め込まれたグローバル一意識別子 (GUID) です。 各トークンまたは要求を一意に識別し、きめ細かい追跡可能性を提供します。

一般的な UTI ベースの調査シナリオは、 **トークン レベルのアクティビティ トレース**です。 Microsoft Entraサインイン ログから UTI を開始し、Exchange Online監査ログやMicrosoft Graphアクティビティ ログなどのワークロード ログと関連付けて、特定のアクセス トークンによって実行されたすべてのアクションをトレースします。

UTI は、すべてのアクセス トークンとセッションに対して一意であるため、調査中に疑わしいトークンや侵害されたトークンを特定するのに最適です。

### リンク可能な識別子の要求

次の表では、Entra トークン内のすべてのリンク可能な識別子の要求について説明します。

| **要求** | **形式** | **説明** |
| --- | --- | --- |
| oid | 文字列、GUID | 要求元の不変識別子。これは、ユーザーやサービス プリンシパルの検証済み ID です。 この ID によって、複数のアプリケーションで要求元が一意に識別されます。 |
| tid | 文字列、GUID | ユーザーがサインインしているテナントを表します。 |
| sid | 文字列、GUID | セッション全体の一意の識別子を表し、ユーザーが対話型認証を行うときに生成されます。 この ID は、1 つのルート認証から発行されたすべての認証成果物をリンクするのに役立ちます。 |
| deviceid | 文字列、GUID | ユーザーがアプリケーションを操作しているデバイスの一意の識別子を表します。 |
| uti | 糸 | トークン識別子要求を表します。 この ID は、トークンごとに一意な識別子であり、大文字と小文字の区別があります。 |
| イアット | int、Unix タイムスタンプ | このトークンの認証がいつ行われたのかを指定します。 |

### リンク可能な識別子のログ可用性

現在、1 つ以上のリンク可能な識別子は、次のログ ソースに記録されます。

- Microsoft Entra サインイン ログ
- Microsoft Entra 監査ログ
- Microsoft Exchange Online の監査ログ
- Microsoft Graph のアクティビティ ログ
- Microsoft Office SharePoint Online の監査ログ
- Microsoft Teamsの監査ログ

これらのログを使用すると、セキュリティ アナリストは、サービス間で認証イベントとトークンの使用状況を関連付け、ID 関連の脅威に対する包括的な調査をサポートできます。

### Microsoft Entra サインイン ログのリンク可能な識別子

すべてのサインイン ログ エントリには、リンク可能な識別子要求があります。 次の表は、リンク可能な識別子要求と Entra サインイン ログ属性の間のマッピングを示しています。

| **要求** | **Entra サインインログの属性名** |
| --- | --- |
| oid | ユーザーID |
| tid | リソース テナント ID |
| sid | セッション ID |
| deviceid | デバイス識別子 |
| uti | 一意のトークン識別子 |
| イアット | 日付 |

Microsoft Entra 管理センターからサインイン ログを表示するには:

1. 少なくとも [Microsoft Entra 管理センター](https://entra.microsoft.com/) に [Reports Reader](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader) としてサインインしてください。
2. **Microsoft Entra ID**&gt;**監視と正常性**&gt;**サインインログ** に移動します。
3. 特定のログ エントリを確認するには、時間または特定のユーザーによってフィルター処理します。
4. サインイン ログ エントリを選択します。
5. **基本情報には** 、ユーザー ID、リソース テナント ID、セッション ID、一意のトークン識別子、および日付が表示されます。 **デバイス** には、登録済みデバイスとドメイン参加済みデバイスのデバイス ID が表示されます。

[Image: Microsoft Entra 管理センターのサインイン ログエントリのスクリーンショット。]

[Image: リンク可能な識別子を含むサインイン ログ エントリのスクリーンショット。]

サインイン ログMicrosoft Entraユーザー ID 属性から開始し、ワークロード監査ログを検索して、特定のアクセス トークンを使用してすべてのアクティビティを追跡します。 同様に、セッション ID 属性を使用してワークロード監査ログを検索し、セッション内のすべてのアクティビティを追跡します。

### Microsoft Entra監査ログのリンク可能な識別子

[Microsoft Entra監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)は、テナントの管理操作とディレクトリ操作を可視化します。 これらのログは、ユーザーとグループの管理、アプリケーションとサービス プリンシパルの変更、ロールの割り当て、ポリシーの更新、デバイス ライフサイクル イベントなどのアクティビティをキャプチャします。

サポートされている監査イベントの場合、ログにはディレクトリ ユーザー セッション識別子 (DUSI) が含まれます。 DUSI を使用すると、セキュリティ アナリストは管理操作を、操作の開始元の認証済みユーザー セッションと関連付けることができます。

#### DUSI を使用した調査シナリオ

管理アクティビティを含むシナリオでは、次のことができます。

- まず、Microsoft Entra のサインイン ログ内の DUSI から始めます。
- この識別子を使用して、Microsoft Entra 監査ログで同じ認証済みセッションに属するイベントを見つけます。
- サインイン アクティビティを後続の管理操作と関連付けます。
- 特定の認証済みセッション中に実行された構成変更をトレースします。
- 疑わしいアカウントまたは侵害されたアカウントに関連付けられている特権操作を調査します。

この相関関係により、認証から後続の管理アクションまでの監査証跡が確立されます。

#### リンク可能な識別子のマッピング

次の表は、リンク可能な識別子クレームを Microsoft Entra 監査ログ内の属性名にマッピングしています。

| **要求** | **Microsoft Entra の監査ログ属性名** | **説明** |
| --- | --- | --- |
| oid | オブジェクト ID | 操作を開始したユーザーまたはサービス プリンシパルの不変識別子。 |
| tid | テナント ID | 操作が記録されたテナントの識別子。 |
| sid | セッション ID | 操作を認証済みユーザー セッションにリンクする識別子。 |
| deviceid | デバイス識別子 | セッションに関連付けられているデバイスの識別子 (使用可能な場合)。 |
| uti | 一意のトークン識別子 | 操作に関連付けられたトークンまたはリクエストの、大文字と小文字を区別する一意の識別子。 |
| イアット | 発行日時 | 操作に関連付けられたトークンが発行された日時。 |

#### サインイン アクティビティと監査ログの関連付け

同じ DUSI 値を使用して、Microsoft Entra サインイン ログと Microsoft Entra 監査ログを関連付けます:

1. Microsoft Entra のサインイン ログで不審なサインインを特定します。
2. 関連付けられている DUSI 値をキャプチャします。
3. Microsoft Entra の監査ログで同じ DUSI 値を検索します。
4. 認証されたセッションに関連付けられている管理操作とディレクトリ操作を確認します。

この方法は、サインインによってユーザー、グループ、アプリケーション、デバイス、ポリシー、またはロールの割り当てが変更されたかどうかを判断するのに役立ちます。

[Image: セッション ID と合成の例の値を示すMicrosoft Entra監査ログの詳細のスクリーンショット。]

#### 例: 侵害されたセッションからの管理アクティビティを調査する

疑わしいサインイン後に管理者アカウントが侵害された疑いがあるとします。 Microsoft Entra のサインイン ログから取得した DUSI を使用すると、次のことができます:

- セッションに関連付けられている Microsoft Entra の監査ログ イベントを識別します。
- ロールの割り当ての変更を確認します。
- 条件付きアクセス ポリシーの更新を調査します。
- アプリケーションとサービス プリンシパルの変更を調べます。
- セッションの全体的な影響を判断します。

サインインログと監査ログMicrosoft Entra全体で DUSI を使用すると、セキュリティ チームは、元の認証済みセッションに管理アクティビティをトレースするのに役立ちます。

注

DUSI は、調査と相関関係のシナリオを対象としています。 セッション識別子は、監査イベントに認証済みのユーザー セッション コンテキストが含まれている場合にのみ使用できます。 一部のサービス生成イベント、バックグラウンド イベント、またはサポートされていない監査イベントには、DUSI 値が含まれていない場合があります。

### Microsoft Exchange Online ログ内のリンク可能な識別子

Exchange Online監査ログは、重要なユーザー アクティビティを可視化し、詳細な監査イベントをキャプチャすることで詳細な調査をサポートします。 これらのログには、Microsoft Entra トークンから引き継がれたリンク可能な識別子が含まれており、認証成果物とワークロード間の相関関係が有効になります。

#### サポートされている調査シナリオ

メールボックスの更新、アイテムの移動、削除などのシナリオでは、次のことができます。

- セッション ID (SID) や一意トークン識別子 (UTI) など、Microsoft Entraサインイン ログからのリンク可能な識別子から始めます。
- これらの識別子を使用して、Microsoft Purview 監査 (Standard) ログまたは監査 (Premium) ログを検索します。
- 特定のセッション中または特定のトークンによってメールボックス アイテムに対して実行されたすべてのユーザー アクションを追跡します。

このアプローチにより、セキュリティ アナリストはサービス全体のアクティビティを追跡し、誤用や侵害の可能性を特定できます。

監査ログExchange Online検索に関する詳細なガイダンスについては、「[監査ログの検索](https://learn.microsoft.com/ja-jp/purview/audit-search)を参照してください。

次の表は、リンク可能な識別子クレームとExchange Onlineの監査ログ属性とのマッピングを示しています。

| **要求** | **Exchange Online監査ログ属性名** |
| --- | --- |
| oid | TokenObjectId |
| tid | TokenTenantId |
| sid | アプリ アクセス コンテキスト オブジェクト内の SessionID/AADSessionId |
| deviceid | DeviceId (登録済み/ドメイン参加済みデバイスでのみ使用可能) |
| uti | アプリ アクセス コンテキスト オブジェクト内の UniqueTokenId |
| イアット | アプリ アクセス コンテキスト オブジェクト内の IssuedAtTime |

#### Microsoft Purview ポータルを使用してExchange Onlineログを表示する

1. [Microsoft Purview ポータル](https://purview.microsoft.com/) に移動します。
2. 特定の期間とレコードの種類がExchangeで始まるログを検索します。

    Microsoft Purview ポータルでExchange workloadを含むログの検索を示すスクリーンショット
3. 特定のユーザーまたは Microsoft Entra のサインインログからの UTI 値に基づいてフィルター処理できます。 `SessionId`を使用して、セッション内のすべてのアクティビティ ログをフィルター処理できます。
4. 結果には、リンク可能なすべての識別子が表示されます。

    [Image: リンク可能な識別子を持つログ項目を示すMicrosoft Purview ポータルのスクリーンショット。]

    [Image: 詳細なログ項目を示すMicrosoft Purview ポータルのスクリーンショット.]
5. 監査ログをエクスポートし、特定の `SessionId` または `UniqueTokenId` を調べ、Exchange Onlineのすべてのアクティビティについて調査します。

#### PowerShell コマンドレットを使用してExchange Onlineログを表示する

1. PowerShell を管理者として実行します。
2. ExchangeOnlineManagement モジュールがインストールされていない場合は、次を実行します。

    ```powershell
    Install-Module -Name ExchangeOnlineManagement
    ```
3. Exchange Onlineに接続します。

    ```powershell
    Connect-ExchangeOnline -UserPrincipalName <user@4jkvzv.onmicrosoft.com>
    ```
4. いくつかのメールボックス コマンドを実行します。

    ```powershell
    Set-Mailbox user@4jkvzv.onmicrosoft.com -MaxSendSize 97MB
    ```

    ```powershell
    Set-Mailbox user@4jkvzv.onmicrosoft.com -MaxSendSize 98MB
    ```

    ```powershell
    Set-Mailbox user@4jkvzv.onmicrosoft.com -MaxSendSize 99MB
    ```
5. 統合監査ログを検索します。

    ```powershell
    Search-UnifiedAuditLog -StartDate 01/06/2025 -EndDate 01/08/2025 -RecordType ExchangeItem, ExchangeAdmin, ExchangeAggregatedOperation, ExchangeItemAggregated, ExchangeItemGroup, ExchangeSearch
    ```
6. 結果には、リンク可能なすべての識別子が含まれます。

注

リンク可能な識別子は、一部の集計ログ エントリまたはバックグラウンド プロセスから生成されたログのExchange Online監査ログでは使用できません。

詳細については、「[Exchange Online PowerShell](https://learn.microsoft.com/ja-jp/powershell/exchange/exchange-online-powershell)」を参照してください。

### Microsoft Graph アクティビティ ログのリンク可能な識別子

Microsoft Graphアクティビティ ログは、テナントのMicrosoft Graph サービスによって受信および処理されたすべての HTTP 要求の監査証跡を提供します。 これらのログはLog Analytics ワークスペースに格納されるため、高度な分析と調査が可能になります。

Microsoft Graphのアクティビティ ログをLog Analytics ワークスペースに送信するように構成した場合は、[Kusto クエリ言語](https://learn.microsoft.com/ja-jp/azure/data-explorer/kusto/query/)を使用してクエリを実行できます。 これにより、Microsoft 365 サービス全体のユーザーとアプリケーションの動作に関する詳細な調査を実行できます。

#### リンク可能な識別子を使用した調査シナリオ

Microsoft Graphアクティビティに関連するシナリオでは、次のことができます。

- SID や UTI など、Microsoft Entraサインイン ログからのリンク可能な識別子から始めます。
- これらの識別子を使用すると、Microsoft Graph のアクティビティ ログを通してユーザーのアクションを関連付けて追跡できます。
- 特定のトークンまたはセッションによってメールボックス アイテムまたはその他のリソースに対して実行されたすべての操作を追跡します。詳細については、「[Microsoft Graph アクティビティ ログ](https://learn.microsoft.com/ja-jp/graph/microsoft-graph-activity-logs-overview)を参照してください。

次の表は、リンク可能な識別子要求とMicrosoft Graphアクティビティ ログ属性の間のマッピングを示しています。

| **要求** | Microsoft Graph アクティビティ ログの**属性名** |
| --- | --- |
| oid | UserId |
| tid | テナント識別子 |
| sid | SessionId (セッションID) |
| deviceid | DeviceId (登録済みデバイスとドメイン参加済みデバイスでのみ使用可能) |
| uti | SignInActivityId |
| イアット | TokenIssuedAt |

#### KQL を使用してサインイン ログとMicrosoft Graphアクティビティ ログを結合する

Kusto クエリ言語 (KQL) を使用して、Microsoft Entra サインイン ログと Microsoft Graph アクティビティ ログを結合し、高度な調査シナリオを実行することができます。

**リンク可能な識別子によるフィルター処理**

- uti でフィルター処理: uti 属性を使用して、特定のアクセス トークンに関連付けられているすべてのアクティビティを分析します。 これは、サービス間で 1 つのトークンの動作をトレースする場合に便利です。
- sid でフィルター処理 (セッション ID): sid クレームを使用して、ルート対話型認証から発生したリフレッシュトークンにより発行されたアクセス トークンが実行するすべてのアクティビティを分析します。 これにより、セッションのライフサイクル全体をトレースできます。
- 追加のフィルター処理: UserId、DeviceId、時間ベースのフィルターなどの属性を使用してクエリをさらに絞り込んで、調査の範囲を絞り込むことができます。

これらの機能により、セキュリティ アナリストは認証イベントをワークロード アクティビティと関連付け、ID 関連の脅威に対する可視性と対応が向上します。

```kql
MicrosoftGraphActivityLogs
| where TimeGenerated > ago(4d) and UserId == '00aa00aa-bb11-cc22-dd33-44ee44ee44ee'
| join kind=leftouter (union
SigninLogs,
AADNonInteractiveUserSignInLogs,
AADServicePrincipalSignInLogs,
AADManagedIdentitySignInLogs,
ADFSSignInLogs
| where TimeGenerated > ago(4d))
on $left.SignInActivityId == $right.UniqueTokenIdentifier
```

[Image: 結合されたログを示す KQL クエリ結果のスクリーンショット。]

Log Analytics ワークスペースのクエリの詳細については、「Log Analyticsを参照してください。

### シナリオ例: Exchange OnlineとMicrosoft Graph全体でユーザー アクティビティをトレースする

この例では、リンク可能な識別子と監査ログを使用して、Microsoft 365 サービス全体でユーザーのアクションをトレースする方法を示します。

**シナリオの概要**

ユーザーは、次の一連のアクションを実行します。

1. Office.com にサインインします。ユーザーは対話型認証を開始し、ルート トークンを生成します。 このトークンには、後続のトークンに伝達されるセッション ID (SID) や一意トークン識別子 (UTI) などのリンク可能な識別子が含まれます。
2. ユーザーはMicrosoft Graph APIを使用して、組織のデータを取得または変更します。 各要求はMicrosoft Graphアクティビティ ログに記録され、関連付けられた SID と UTI によって元のサインイン イベントとの関連付けが有効になります。
3. Exchange Online (Outlook) を使用します。ユーザーはExchange Online経由でOutlookを開き、メールの読み取り、移動、削除などのメールボックス操作を実行します。 これらのアクションは、同じリンク可能な識別子も含まれるExchange Online監査ログにキャプチャされます。

SID を使用すると、アナリストは、同じセッションから発生したサービス全体のすべてのアクティビティを追跡できます。 または、UTI を使用して、特定のアクセス トークンに関連付けられているアクションを特定することもできます。

1. サインイン ログで対話型ログイン ログ行を見つけて、 `SessionId`をキャプチャします。

    [Image: セッション ID を持つ対話型サインイン ログ行のスクリーンショット。]
2. `SessionId`でフィルターを追加します。 対話型サインインまたは非対話型サインインの `SessionId` を取得できます。

    **対話型サインイン:**

    [Image: セッション ID でフィルター処理された対話型サインインのスクリーンショット。]

    **非対話型サインイン:**

    [Image: セッション ID でフィルター処理された非対話型サインインのスクリーンショット。]
3. この特定のセッション内でユーザーがMicrosoft Graphワークロードで行ったすべてのアクティビティを取得するには、Microsoft Entra管理センターのLog Analyticsに移動し、Microsoft EntraのサインインログとMicrosoft Graphのアクティビティログを結合するクエリを実行します。 次のクエリは、 `UserId` と `SessionId`によってフィルター処理されます。

    [Image: ユーザー ID とセッション ID でフィルター処理されたMicrosoft Graphアクティビティのスクリーンショット。]

    特定の要求によるアクセスの詳細については、 `SignInActivityId` (uti 要求) 属性でさらにフィルター処理を行うことができます。
4. Exchange Onlineアクティビティを取得するには、Microsoft Purview ポータルを開き、ユーザーまたはレコードの種類で検索します。

    [Image: ユーザーまたはレコードの種類による検索を示すMicrosoft Purview ポータルのスクリーンショット.]
5. データをエクスポートします。

    Microsoft Purview ポータルでのデータエクスポートのスクリーンショット
6. ログ エントリには、リンク可能なすべての識別子があります。 各一意のアクティビティを `UniqueTokenId` して検索し、セッション内のすべてのアクティビティを `AADSessionId` して検索できます。

    [Image: リンク可能な識別子を含むログ行のスクリーンショット。]

### Microsoft Office SharePoint Online監査ログのリンク可能な識別子

Microsoft Office SharePoint Online監査ログは、テナントの SharePoint Online サービスによって処理されたすべての要求の包括的な監査証跡を提供します。 これらのログは、ファイルやフォルダーの作成、更新、削除、リストの変更などの操作を含む、さまざまなユーザー アクティビティをキャプチャします。 SharePoint Online 監査ログの詳細な概要については、「[SharePoint オンライン監査ログ](https://learn.microsoft.com/ja-jp/purview/audit-log-sharing?tabs=microsoft-purview-portal)を参照してください。

**リンク可能な識別子を使用した調査シナリオ**

SharePoint Online アクティビティに関連するシナリオでは、次のことができます。

- SID や UTI など、Microsoft Entraサインイン ログからのリンク可能な識別子から始めます。
- これらの識別子を使用して、Microsoft Purview 監査 (Standard) ログまたは監査 (Premium) ログを検索します。
- 特定のセッション中または特定のトークンによってSharePoint Online 内で実行されたすべてのユーザー アクションを追跡します。

このアプローチにより、セキュリティ アナリストは認証イベントを SharePoint アクティビティと関連付け、潜在的な脅威に対する効果的な調査と対応をサポートできます。

SharePointオンライン監査ログの検索に関するガイダンスについては、「[監査ログを検索する |Microsoft Learn](https://learn.microsoft.com/ja-jp/purview/audit-search)。

次の表は、リンク可能な識別子要求とMicrosoft Office SharePoint Online監査ログ属性の間のマッピングを示しています。

| **要求** | **Microsoft Office SharePoint Online 監査ログ属性名** |
| --- | --- |
| oid | UserObjectId |
| tid | 組織ID |
| sid | アプリ アクセス コンテキスト オブジェクト内の AADSessionId |
| deviceid | DeviceId (登録済み/ドメイン参加済みデバイスでのみ使用可能) |
| uti | アプリ アクセス コンテキスト オブジェクト内の UniqueTokenId |
| イアット | アプリ アクセス コンテキスト オブジェクト内の IssuedAtTime |

#### Microsoft Purview ポータルを使用してMicrosoft Office SharePoint Online監査ログを表示する

1. [Microsoft Purview ポータル](https://purview.microsoft.com/) に移動します。
2. 特定の期間とワークロードのログをMicrosoft Office SharePoint Onlineとして検索します。

    SharePoint Onlineログを検索するMicrosoft Purviewポータルのスクリーンショット
3. レコードの種類でフィルター処理するには、サポートされているレコードの種類は、SharePoint以降の項目で見つけることができます。

    Microsoft Purview ポータルのスクリーンショットで、SharePoint Onlineでサポートされているレコードの種類を示しています。
4. 特定のユーザーまたは Microsoft Entra のサインインログからの UTI 値に基づいてフィルター処理できます。 `AADSessionId`を使用して、セッション内のすべてのアクティビティ ログをフィルター処理できます。
5. 監査検索結果には、SharePoint Online アクティビティのすべてのログ行が表示されます。

    [Image: Microsoft Purview ポータル のスクリーンショットは、SharePoint Online の監査ログの結果を示しています。]
6. 各ログ項目には、リンク可能なすべての識別子が表示されます。

    Microsoft Purview ポータル のスクリーンショットで、SharePoint Online のリンク可能な識別子を持つログアイテムを示しています。
7. 監査ログをエクスポートし、Microsoft Office SharePoint Onlineのすべてのアクティビティの特定の `AADSessionId` または `UniqueTokenId` を調査します。

### Microsoft Teams監査ログのリンク可能な識別子

Microsoft Teams監査ログには、テナントの Teams サービスによって処理されたすべての要求の詳細な記録がキャプチャされます。 監査対象のアクティビティには、チームの作成と削除、チャネルの追加と削除、チャネル設定の変更が含まれます。

監査対象の Teams アクティビティの完全な一覧については、 [監査ログの Teams アクティビティを](https://learn.microsoft.com/ja-jp/purview/audit-log-activities)参照してください。 Teams 監査ログの詳細については、「 [Teams 監査ログ」](https://learn.microsoft.com/ja-jp/purview/audit-teams-audit-log-events)を参照してください。 Teams 監査ログを検索する方法の詳細については、「 [監査ログの検索」を](https://learn.microsoft.com/ja-jp/purview/audit-search)参照してください。

#### リンク可能な識別子を使用した調査シナリオ

Teams のアクティビティを調査するには:

- SID や UTI など、Microsoft Entraサインイン ログからのリンク可能な識別子から始めます。
- これらの識別子を使用して、Microsoft Purview 監査 (Standard) ログまたは監査 (Premium) ログを検索します。
- チームやチャネルの操作など、Teams セッション間でユーザーアクションを追跡します。

次の表は、リンク可能な識別子要求と Teams 監査ログ属性の間のマッピングを示しています。

| **要求** | **Teams 監査ログ属性名** |
| --- | --- |
| oid | ユーザーキー |
| tid | 組織ID |
| sid | アプリ アクセス コンテキスト オブジェクト内の AADSessionId |
| deviceid | DeviceId (登録済み/ドメイン参加済みデバイスでのみ使用可能) |
| uti | アプリ アクセス コンテキスト オブジェクト内の UniqueTokenId |
| イアット | アプリ アクセス コンテキスト オブジェクト内の IssuedAtTime |

### Microsoft Teamsと SharePoint Online 全体のトークンの誤用を調査する

フィッシングなどのアクセス トークンが侵害され、その後悪意のあるアクターによって使用されるセキュリティ インシデントが発生した場合、テナント管理者は、脅威を封じ込め、その影響を調査するために直ちに対処する必要があります。

すべてのアクティブなユーザー セッションとトークンを取り消した後、管理者はフォレンジック調査を開始して、承認されていないアクティビティの範囲を特定できます。 具体的には、影響を受ける期間中に攻撃者が Microsoft Teams および SharePoint Online で実行したアクションを特定することが必要になる場合があります。

管理者は、Microsoft Entraサインイン ログのセッション ID (SID) や一意トークン識別子 (UTI) などのリンク可能な識別子を使用して、Microsoft Purview 監査 (Standard) ログと Audit (Premium) ログ間でアクティビティを関連付け、トレースできます。 これにより、次の情報を可視化できます。

チームやチャネルの作成、削除、構成の変更など、チーム関連のアクション。 SharePointファイル アクセス、作成、変更、削除などのオンライン操作。

1. まず、Microsoft Entraサインイン ログから始めて、トークンがフィッシングされた時間とユーザー objectId をフィルター処理して、このアクセス トークンのセッション ID を見つけます。

    [Image: Teams シナリオのリンク可能な識別子を持つログ項目を示すMicrosoft Purview ポータルのスクリーンショット。]
2. Teams と SharePoint Online 監査ログのフィルターとして使用する、SID や UTI などのMicrosoft Entraサインイン ログからリンク可能な識別子を決定します。
3. Purview ポータルで、Teams や SharePoint Online などのワークロードに対して、特定の期間内のログや特定のユーザーに対して検索を行います。

    [Image: SharePoint と Teams ワークロードのログの検索を示すMicrosoft Purview ポータルのスクリーンショット。]
4. この検索では、その期間内のすべての監査ログ エントリが返され、ユーザーとワークロードによって Teams および SharePoint Online としてフィルター処理されます。

    [Image: Microsoft Purview ポータル で、SPO および Teams のログの結果を示すスクリーンショット。]
5. 管理者は、攻撃者が Teams チャネルにユーザーを追加し、フィッシング メッセージを投稿し、SharePointからファイルを削除したことを示す完全な監査証跡を確認できます。
6. 各ログ項目を開き、リンク可能な識別子に関する詳細情報を取得できます。 次の例は、ユーザーがメッセージを投稿する方法を示しています。

    Microsoft Purview ポータル のスクリーンショットで、Teams および SPO のログ項目を示しています。
7. 次の例は、SharePoint Online からファイルをダウンロードするユーザーを示しています。

    [Image: Microsoft Purview ポータル のスクリーンショットで、Teams および SPO のログ項目を検索している様子を示します。]
8. 監査ログをエクスポートし、特定のアクティビティを対象に、特定の`SessionId`または`UniqueTokenId`を調査します。 次の図は、攻撃者が実行したすべての操作を示しています。

    [Image: エクスポートされたログの検索を示すMicrosoft Purview ポータルのスクリーンショット.]

リンク可能な識別子を使用してログ ファイルを分析することで、テナント管理者とセキュリティの専門家は、セッションとトークン全体の悪意のあるアクティビティを効果的に追跡、分析、対応できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-authentication-two-way-sms-unsupported"} -->
## 双方向 SMS がサポートされなくなりました - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-two-way-sms-unsupported
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: 双方向 SMS を引き続き使用するユーザーに対して別の方法を有効にする方法について説明します。

Azure Multi-Factor Authentication Server の双方向 SMS は当初 2018 年に非推奨となり、2021 年 2 月 24 日以降はサポートされなくなりました。ただし、2021 年 8 月 2 日までサポート拡張機能を受け取った組織を除きます。 管理者は、双方向 SMS を引き続き使用するユーザーに対して別の方法を有効にする必要があります。

2020 年 12 月 8 日と 2021 年 1 月 28 日に、影響を受ける管理者に電子メール通知と Service Health 通知 (ポータル トースト) が送信されました。 アラートは、サブスクリプションに関連付けられている所有者、共同所有者、管理者、サービス管理者の RBAC ロールに送信されました。 次の手順を既に完了している場合は、アクションは必要ありません。

### 必要なアクション

1. まだ有効にしていない場合は、ユーザーのモバイル アプリを有効にします。 詳細については、「[MFA Server](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfaserver-deploy-mobileapp)を使用してモバイル アプリ認証を有効にする」を参照してください。
2. モバイル アプリをアクティブ化するために、MFA Server [ユーザー ポータル](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfaserver-deploy-userportal) にアクセスするようにエンド ユーザーに通知します。 [Microsoft Authenticator アプリ](https://www.microsoft.com/en-us/account/authenticator) は、双方向 SMS よりも安全であるため、推奨される検証オプションです。 詳細については、「[認証のための電話転送から脱却するときがきました](https://techcommunity.microsoft.com/t5/azure-active-directory-identity/it-s-time-to-hang-up-on-phone-transports-for-authentication/ba-p/1751752)」を参照してください。
3. 既定の方法として、ユーザー設定を双方向テキスト メッセージからモバイル アプリに変更します。

### よくあるご質問

#### 既定の方法を双方向 SMS からモバイル アプリに変更しない場合はどうなりますか?

2021 年 2 月 24 日以降、双方向 SMS は失敗します。 ユーザーがサインインして MFA を渡そうとすると、エラーが表示されます。

#### ユーザー設定を双方向のテキスト メッセージからモバイル アプリに変更するにはどうすればよいですか?

次の手順に従って、ユーザー設定を変更する必要があります。

1. MFA サーバーで、双方向テキスト メッセージのユーザー リストをフィルター処理します。
2. すべてのユーザーを選択します。
3. [ユーザーの編集] ダイアログを開きます。
4. ユーザーをテキスト メッセージからモバイル アプリに変更します。

    エンド ユーザー の スクリーンショット

#### ユーザーは何かアクションを実行する必要がありますか? 「はい」の場合は、どうすればよいですか?

はい。 エンド ユーザーがモバイル アプリをアクティブ化するには、特定の MFA サーバー ユーザー ポータルにアクセスする必要があります (まだアクティブ化していない場合)。 手順 3 を完了すると、ユーザー ポータルにアクセスしてモバイル アプリを設定しなかったユーザーは、ユーザー ポータルにアクセスして再登録するまでサインインに失敗し始めます。

#### ユーザーがモバイル アプリをインストールできない場合はどうすればよいですか? 他にどのようなオプションがありますか?

双方向 SMS またはモバイル アプリの代替手段は、電話です。 ただし、Microsoft Authenticator アプリは推奨される検証方法です。

#### 一方向 SMS も非推奨になりますか?

いいえ。双方向 SMS は非推奨になっています。 MFA サーバーの場合、一方向 SMS はシナリオのサブセットに対して機能します。

- AD FS アダプター
- IIS 認証 (ユーザー ポータルと構成が必要)
- RADIUS (RADIUS クライアントがアクセス チャレンジをサポートし、PAP プロトコルが使用されている必要があります)

一方向の SMS の使用には制限があり、それにより、検証コードのプロンプトを必要としないモバイルアプリの方が優れた代替手段となります。 一部のシナリオで引き続き一方向 SMS を使用する場合は、これらのチェックをオンのままにしておくことができますが、**[会社の設定]** セクションの **[全般]** タブの **[ユーザーの既定のテキスト メッセージ]** は、**[双方向]** ではなく **[一方向]** に変更してください。 最後に、既定で SMS に設定されているディレクトリ同期を使用する場合は、双方向ではなく、One-Way に変更する必要があります。

#### 双方向 SMS をまだ使用しているユーザーを確認するにはどうすればよいですか?

これらのユーザーを一覧表示するには、MFA Serverを開始し、「 ユーザー 」セクションを選択し、「ユーザー一覧のフィルター 」をクリックして、「テキストメッセージ双方向」でフィルターをかけます。

#### MFA ポータルで双方向 SMS をオプションとして非表示にして、ユーザーが今後選択できないようにするにはどうすればよいですか?

MFA サーバー ユーザー ポータルで、[設定]をクリックします。テキスト メッセージをクリアして、使用できないようにすることができます。 ユーザー登録に AD FS を使用している場合は、**AD FS** セクションでも同様です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-certificate-based-authentication"} -->
## Microsoft Entra CBAを設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-certificate-based-authentication
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra 証明書ベースの認証 (CBA) を Microsoft Entra ID で構成する方法について学びます。

組織では、Microsoft Entra証明書ベースの認証 (CBA) を使用して、ユーザー X.509 証明書を使用して、フィッシングに対する耐性、最新、パスワードレスの認証を実装できます。

この記事では、X.509 証明書を使用してテナント ユーザーの認証を許可または要求するように、Microsoft Entra テナントを設定する方法について説明します。 ユーザーは、アプリケーションとブラウザーのサインインにエンタープライズ公開キー基盤 (PKI) を使用して X.509 証明書を作成します。

Microsoft Entra CBA を設定すると、サインイン中に、ユーザーはパスワードを入力するのではなく、証明書を使用して認証するオプションを表示します。 デバイスに複数の一致する証明書がある場合、ユーザーは関連する証明書を選択し、ユーザー アカウントに対して証明書が検証されます。 検証に成功すると、ユーザーはサインインします。

この記事で説明されている手順を完了して、Office 365 Enterpriseおよび米国政府のプランのテナントに対して Microsoft Entra CBA を構成して使用します。 [PKI](https://aka.ms/securingpki) が既に構成されている必要があります。

### 前提条件

次の前提条件が満たされていることを確認します。

- 少なくとも 1 つの証明機関 (CA) と中間 CA がMicrosoft Entra IDで構成されます。
- ユーザーは、Microsoft Entra IDでのクライアント認証を目的としてテナントで構成された信頼された PKI から発行されたユーザー証明書にアクセスできます。
- 各 CA には、インターネットに接続する URL から参照できる証明書失効リスト (CRL) があります。 信頼された CA に CRL が構成されていない場合、Microsoft Entra IDは CRL チェックを実行せず、ユーザー証明書の失効は機能せず、認証はブロックされません。

### 考慮事項

- PKI がセキュリティで保護されており、簡単に侵害できないことを確認します。 侵害が発生した場合、攻撃者はクライアント証明書を作成して署名し、テナント内のすべてのユーザー (オンプレミスから同期されたユーザーを含む) を侵害することができます。 強力なキー保護戦略やその他の物理的および論理的な制御により、外部の攻撃者や内部関係者の脅威によって PKI の整合性が損なわれないように、多層防御を実現できます。 詳細については、[PKI の保護](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2012-r2-and-2012/dn786443%28v=ws.11%29)に関する記事をご覧ください。
- アルゴリズム、キーの長さ、データ保護の選択など、Microsoft暗号化のベスト プラクティスについては、[Microsoftの推奨事項](https://learn.microsoft.com/ja-jp/security/sdl/cryptographic-recommendations#security-protocol-algorithm-and-key-length-recommendations)を参照してください。 推奨されるアルゴリズムの 1 つ、推奨されるキーの長さ、および NIST で承認された曲線を必ず使用してください。
- 継続的なセキュリティ強化の一環として、AzureとMicrosoft 365エンドポイントは TLS 1.3 のサポートを追加しました。 このプロセスは、AzureとMicrosoft 365全体で何千ものサービス エンドポイントをカバーするために数か月かかると予想されます。 Microsoft Entra CBA で使用される Microsoft Entra エンドポイントは、`*.certauth.login.microsoftonline.com` および `*.certauth.login.microsoftonline.us` の更新プログラムに含まれています。

    TLS 1.3 は、インターネットで最も一般的にデプロイされるセキュリティ プロトコルの最新バージョンです。 TLS 1.3 は、データを暗号化して、2 つのエンドポイント間のセキュリティで保護された通信チャネルを提供します。 古い暗号アルゴリズムを排除し、以前のバージョンよりもセキュリティを強化し、できるだけ多くのハンドシェイクを暗号化します。 アプリケーションとサービスで TLS 1.3 のテストを開始することを強くお勧めします。
- PKI を評価するときは、証明書の発行ポリシーと適用を確認することが重要です。 前述のように、CA をMicrosoft Entra構成に追加すると、それらの CA によって発行された証明書は、Microsoft Entra IDで任意のユーザーを認証できます。

    CA が証明書の発行を許可される方法とタイミング、およびそれらが再利用可能な識別子を実装する方法を考慮することが重要です。 管理者は、特定の証明書を使用してユーザーを認証できるようにする必要がありますが、特定の証明書のみがユーザーを認証できるより高いレベルの保証を実現するには、アフィニティの高いバインドのみを使用する必要があります。 詳細については、「 [高アフィニティ バインディング」](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-technical-deep-dive#username-binding-policy)を参照してください。

### Microsoft EntraのCBAを構成し、テストする

Microsoft Entra の CBA を有効にする前に、いくつかの Microsoft Entra の構成手順を完了する必要があります。

管理者は、ユーザー証明書を発行する信頼された CA を構成する必要があります。 次の図に示すように、Azureではロールベースのアクセス制御 (RBAC) を使用して、最小限の特権を持つ管理者のみが変更を行う必要があることを確認します。

重要

Microsoftでは、アクセス許可が最も少ないロールを使用することをお勧めします。 この方法は、組織のセキュリティ向上に役立ちます。 グローバル管理者は、既存のロールを使用できないときの緊急シナリオに限定する必要がある、高い特権を持つロールでます。

必要に応じて、証明書を単一要素認証または多要素認証 (MFA) にマップするように認証バインドを構成できます。 証明書フィールドをユーザー オブジェクトの属性にマップするようにユーザー名バインドを構成します。 [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)は、ユーザー関連の設定を構成できます。

すべての構成が完了したら、Microsoft Entra CBAをテナントで有効にします。

[Image: Microsoft Entraの証明書ベースの認証を有効にするために必要な手順の概要を示す図。]

### 手順 1: PKI ベースの信頼ストアを使用して CA を構成する

Microsoft Entraには、新しい PKI ベースの CA 信頼ストアがあります。 信頼ストアは、PKI ごとにコンテナー オブジェクト内に CA を保持します。 管理者は、PKI に基づいてコンテナー内の CA を簡単に管理できます。これは、CA のフラット リストを管理するよりも簡単です。

PKI ベースの信頼ストアには、CA の数と各 CA ファイルのサイズに関する従来の信頼ストアよりも高い制限があります。 PKI ベースの信頼ストアは、CA オブジェクトごとに最大 250 CA と 8 KB をサポートします。

[従来の信頼ストアを使用して CA を構成する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-configure-certificate-authorities)場合は、PKI ベースの信頼ストアを設定することを強くお勧めします。 PKI ベースの信頼ストアはスケーラブルであり、発行者ヒントなどの新機能をサポートしています。

管理者は、ユーザー証明書を発行する信頼された CA を構成する必要があります。 変更を加える必要があるのは、最小限の特権を持つ管理者だけです。 PKI ベースの信頼ストアには、 [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator) ロールが割り当てられます。

PKI ベースの信頼ストアの PKI アップロード機能は、Microsoft Entra ID P1 または P2 ライセンスでのみ使用できます。 ただし、Microsoft Entra無料ライセンスでは、管理者は PKI ファイルをアップロードするのではなく、すべての CA を個別にアップロードできます。 その後、PKI ベースの信頼ストアを構成し、アップロードした CA ファイルを追加できます。

#### Microsoft Entra 管理センターを使用して CA を構成する

##### PKI コンテナー オブジェクトの作成 (Microsoft Entra 管理センター)

PKI コンテナー オブジェクトを作成するには:

1. [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator)ロールが割り当てられているアカウントでMicrosoft Entra 管理センターにサインインします。
2. **Entra ID**&gt;**Identity Secure Score**&gt;**Public キー インフラストラクチャ**に移動します。
3. [ **PKI の作成]** を選択します。
4. **[表示名]** に名前を入力します。
5. **を選択して**を作成します。

    [Image: PKI を作成するために必要な手順を示す図。]
6. 列を追加または削除するには、[列の **編集]** を選択します。
7. PKI の一覧を更新するには、[ **最新の情報に更新**] を選択します。

##### PKI コンテナー オブジェクトを削除する

PKI を削除するには、PKI を選択し、**[削除]** を選択します。 PKI に CA が含まれている場合は、PKI の名前を入力して、PKI *内のすべての* CA の削除を確認します。 次に、[削除] を選択 **します**。

[Image: PKI を削除するために必要な手順を示す図。]

##### 個々の CA を PKI コンテナー オブジェクトにアップロードする

PKI コンテナーに CA をアップロードするには:

1. [ **証明機関の追加]** を選択します。
2. CA ファイルを選択します。
3. CA がルート証明書の場合は、[ **はい**] を選択します。 \*\* 別の場合は **いいえ** を選択します。
4. **[証明書失効リストの URL]** に、失効したすべての証明書を含む CA ベース CRL のインターネットに接続する URL を入力します。 URL が設定されていない場合、失効した証明書を使用した認証の試行は失敗しません。
5. **[Delta Certificate Revocation List URL**] には、最後のベース CRL が発行されてからのすべての失効した証明書を含む CRL のインターネットに接続する URL を入力します。
6. CA を発行者ヒントに含めてはいけない場合は、発行者ヒントをオフにします。 **発行者ヒント** フラグは、既定ではオフになっています。
7. **[保存]** を選択します。
8. CA を削除するには、CA を選択し、[ **削除**] を選択します。

    [Image: CA 証明書を削除する方法を示す図。]
9. 列を追加または削除するには、[列の **編集]** を選択します。
10. PKI の一覧を更新するには、[ **最新の情報に更新**] を選択します。

最初に、100 個の CA 証明書が表示されます。 ウィンドウを下にスクロールすると、さらに表示されます。

##### PKI コンテナー オブジェクトにすべての CA をアップロードする

すべての CA を PKI コンテナーに一括アップロードするには:

1. PKI コンテナー オブジェクトを作成するか、既存のコンテナーを開きます。
2. **[Upload PKI]\(PKI のアップロード\)** を選択します。
3. `.p7b` ファイルの HTTP インターネットに接続する URL を入力します。
4. ファイルの SHA-256 チェックサムを入力します。
5. アップロードを選択します。

    PKI アップロード プロセスは非同期です。 各 CA がアップロードされると、PKI で使用できるようになります。 PKI のアップロード全体には、最大で 30 分かかることがあります。
6. **[最新の情報に更新]** を選択して、CA の一覧を更新します。
7. アップロードされた各 CA **CRL エンドポイント** 属性は、CRL **配布ポイント** 属性としてリストされている CA 証明書の最初の使用可能な HTTP URL で更新されます。 リーフ証明書は手動で更新する必要があります。

PKI `.p7b` ファイルの SHA-256 チェックサムを生成するには、次のコマンドを実行します。

```powershell
Get-FileHash .\CBARootPKI.p7b -Algorithm SHA256
```

##### PKI を編集する

1. PKI 行で 、[ **...** ] を選択し、[ **編集]** を選択します。
2. 新しい PKI 名を入力します。
3. **[保存]** を選択します。

##### CA を編集する

1. CA 行で 、[ **...** ] を選択し、[ **編集]** を選択します。
2. 要件に従って、CA の種類 (ルートまたは中間)、CRL URL、デルタ CRL URL、または発行者ヒントが有効なフラグの新しい値を入力します。
3. **[保存]** を選択します。

##### 発行者ヒント属性を一括編集する

1. 複数の CA を編集し、 **発行者ヒントが有効** になっている属性をオンまたはオフにするには、複数の CA を選択します。
2. [ **編集]** を選択し、[ **発行者ヒントの編集]** を選択します。
3. 選択したすべての CA に対して [ **発行者ヒントが有効]** チェック ボックスをオンにするか、選択をオフにして、選択したすべての CA に対して **発行者ヒントが有効な** フラグをオフにします。 既定値は **不確定です**。
4. **[保存]** を選択します。

##### PKI を復元する

1. **[Deleted PKIs]\(削除された PKI\)** タブを選択します。
2. PKI を選択し、**[Restore PKI]\(PKI の復元\)** を選択します。

##### CA を復元する

1. **[Deleted CA]\(削除された CA\)** タブを選択します。
2. CA ファイルを選択し、[証明機関の **復元**] を選択します。

##### CA の isIssuerHintEnabled 属性を構成する

発行者ヒントは、トランスポート層セキュリティ (TLS) ハンドシェイクの一部として、*信頼された CA* インジケーターを返します。 信頼された CA リストは、テナントがMicrosoft Entra信頼ストアにアップロードする CA のサブジェクトに設定されます。 詳細については、「 [発行者のヒントについて](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-technical-deep-dive#issuer-hints)」を参照してください。

既定では、Microsoft Entra 信頼ストア内のすべての CA のサブジェクト名がヒントとして送信されます。 特定の CA に対してのみヒントを返送する場合は、発行者ヒント属性 `isIssuerHintEnabled` を `true` に設定します。

サーバーは、発行者ヒント (CA のサブジェクト名) に対して最大 16 KB の応答を TLS クライアントに送り返すことができます。 `isIssuerHintEnabled`属性は、ユーザー証明書を発行する CA に対してのみ`true`に設定することをお勧めします。

同じルート証明書の複数の中間 CA がユーザー証明書を発行する場合、既定では、すべての証明書が証明書ピッカーに表示されます。 `isIssuerHintEnabled`を特定の CA に対して`true`に設定すると、証明書ピッカーに関連するユーザー証明書のみが表示されます。

#### Microsoft Graph API を使用して CA を構成する

次の例では、MICROSOFT GRAPHを使用して、PKI または CA の HTTP メソッドを使用して作成、読み取り、更新、および削除 (CRUD) 操作を実行する方法を示します。

##### PKI コンテナー オブジェクトを作成する (Microsoft Graph)

```http
PATCH https://graph.microsoft.com/beta/directory/publicKeyInfrastructure/certificateBasedAuthConfigurations/
Content-Type: application/json
{
   "displayName": "ContosoPKI"
}
```

##### すべての PKI オブジェクトを取得する

```http
GET https://graph.microsoft.com/beta/directory/publicKeyInfrastructure/certificateBasedAuthConfigurations
ConsistencyLevel: eventual
```

##### PKI ID で PKI オブジェクトを取得する

```http
GET https://graph.microsoft.com/beta/directory/publicKeyInfrastructure/certificateBasedAuthConfigurations/<PKI-ID>/
ConsistencyLevel: eventual
```

##### .p7b ファイルを使用して CA をアップロードする

```http
PATCH https://graph.microsoft.com/beta/directory/publicKeyInfrastructure/certificateBasedAuthConfigurations/<PKI-id>/certificateAuthorities/<CA-ID>
Content-Type: application/json
{
     "uploadUrl":"https://CBA/demo/CBARootPKI.p7b,
     "sha256FileHash": "AAAAAAD7F909EC2688567DE4B4B0C404443140D128FE14C577C5E0873F68C0FE861E6F"
}
```

##### PKI 内のすべての CA を取得する

```http
GET https://graph.microsoft.com/beta/directory/publicKeyInfrastructure/certificateBasedAuthConfigurations/<PKI-ID>/certificateAuthorities
ConsistencyLevel: eventual
```

##### PKI 内の特定の CA を CA ID で取得する

```http
GET https://graph.microsoft.com/beta/directory/publicKeyInfrastructure/certificateBasedAuthConfigurations/<PKI-ID>/certificateAuthorities/<CA-ID>
ConsistencyLevel: eventual
```

##### 特定の CA 発行者ヒント フラグを更新する

```http
PATCH https://graph.microsoft.com/beta/directory/publicKeyInfrastructure/certificateBasedAuthConfigurations/<PKI-ID>/certificateAuthorities/<CA-ID>
Content-Type: application/json
{
   "isIssuerHintEnabled": true
}
```

#### PowerShell を使用して CA を構成する

これらの手順では、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) を使用します。

1. **[管理者として実行**] オプションを使用して PowerShell を起動します。
2. Microsoft Graph PowerShell SDKをインストールしてインポートします。

    ```powershell
    Install-Module Microsoft.Graph -Scope AllUsers
    Import-Module Microsoft.Graph.Authentication
    Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
    ```
3. テナントに接続し、*すべて*を受け入れます。

    ```powershell
       Connect-MGGraph -Scopes "Directory.ReadWrite.All", "User.ReadWrite.All" -TenantId <tenantId>
    ```

#### PKI ベースの信頼ストアと従来の CA ストアの間の優先順位付け

PKI ベースの CA ストアと従来の CA ストアの両方に CA が存在する場合、PKI ベースの信頼ストアが優先されます。

クラシック CA ストアは、次のシナリオで優先されます。

- CA は両方のストアに存在し、PKI ベースのストアには CRL はありませんが、クラシック ストア CA には有効な CRL があります。
- CA は両方のストアに存在し、PKI ベースのストア CA CRL は従来のストア CA CRL とは異なります。

#### サインイン ログ

Microsoft Entra サインイン ログの "中断" エントリでは、**[追加の詳細]** の下に、認証時にクラシック信頼ストアとレガシー信頼ストアのどちらが使用されたかを示す 2 つの属性が表示されます。

- **使用されているレガシ ストア** の値は **0** で、PKI ベースのストアが使用されていることを示します。 値 **1** は、クラシック ストアまたはレガシ ストアが使用されることを示します。
- **従来のストアの使用情報** には、クラシック ストアまたはレガシ ストアが使用されている理由が表示されます。

[Image: PKI ベースのストアまたは従来の CA ストアを使用するためのサインイン ログ エントリを示すスクリーンショット]

#### 監査ログ

信頼ストア内の PKI または CA で実行する CRUD 操作は、Microsoft Entra監査ログに表示されます。

[Image: [監査ログ] ウィンドウを示すスクリーンショット。]

#### 従来の CA ストアから PKI ベースのストアに移行する

テナント管理者は、PKI ベースのストアにすべての CA をアップロードできます。 その後、PKI CA ストアはクラシック ストアよりも優先され、すべての CBA 認証は PKI ベースのストアを介して行われます。 テナント管理者は、サインイン ログにクラシック ストアまたはレガシ ストアが使用されたことの兆候がないことを確認した後、クラシック ストアまたはレガシ ストアから CA を削除できます。

#### よく寄せられる質問

##### PKI のアップロードが失敗する理由

PKI ファイルが有効であり、問題なくアクセスできることを確認します。 PKI ファイルの最大サイズは 2 MB (CA オブジェクトごとに 250 CA と 8 KB) です。

##### PKI アップロードのSLAとは何ですか

PKI アップロードは非同期操作であり、完了するまでに最大 30 分かかる場合があります。

##### PKI ファイルの SHA-256 チェックサムを生成するにはどうすればよいですか?

PKI `.p7b` ファイルの SHA-256 チェックサムを生成するには、次のコマンドを実行します。

```powershell
Get-FileHash .\CBARootPKI.p7b -Algorithm SHA256
```

### 手順 2: テナントの CBA を有効にする

重要

ユーザーが認証方法ポリシーで CBA のスコープとして指定されている場合、ユーザーは MFA を完了できると見なされます。 このポリシー要件は、ユーザーが認証の一部として ID 証明を使用して他の使用可能な方法を登録できないことを意味します。 ユーザーが証明書にアクセスできない場合、ユーザーはロックアウトされ、MFA の他の方法を登録できません。 認証ポリシー管理者ロールが割り当てられている管理者は、有効な証明書を持つユーザーに対してのみ CBA を有効にする必要があります。 CBA に **[すべてのユーザー]** を含めないでください。 有効な証明書を使用できるユーザーのグループのみを使用します。 詳細については、「[Microsoft Entra多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)」を参照してください。

Microsoft Entra 管理センターを使用して CBA を有効にするには:

1. Microsoft Entra 管理センター に、少なくとも Authentication Policy Administrator ロールが割り当てられているアカウントでサインインします。
2. グループ&gt;に移動します。
3. [ **新しいグループ** ] を選択し、CBA ユーザー用のグループを作成します。
4. **Entra ID**&gt;**認証方法**&gt;**証明書ベースの認証**にアクセスします。
5. [**有効] と [ターゲット**] で [**有効**] を選択し、[**確認済み**] チェックボックスをオンにします。
6. [**グループの選択**] を選択&gt;**グループを追加します**。
7. 作成したグループなど、特定のグループを選択し、[選択] を **選択**します。 **[すべてのユーザー**] ではなく、特定のグループを使用します。
8. **[保存]** を選択します。

    [Image: CBA を有効にする方法を示すスクリーンショット。]

テナントに対して CBA を有効にすると、テナント内のすべてのユーザーに、証明書を使用してサインインするオプションが表示されます。 X.509 証明書を使用して認証できるのは、CBA を使用できるユーザーだけです。

注

ネットワーク管理者は、 `login.microsoftonline.com` エンドポイントに加えて、組織のクラウド環境の証明書認証エンドポイントへのアクセスを許可する必要があります。 証明書認証エンドポイントで TLS 検査をオフにして、クライアント証明書要求が TLS ハンドシェイクの一部として成功することを確認します。

### 手順 3: 認証バインド ポリシーを構成する

認証バインディング ポリシーは、認証の強度を単一要素または MFA に設定するのに役立ちます。 テナント上のすべての証明書の既定の保護レベルは、単一要素認証です。

テナント レベルでの既定のアフィニティ バインドは*低アフィニティ*です。 認証ポリシー管理者は、既定値を単一要素認証から MFA に変更できます。 保護レベルが変更されると、テナント上のすべての証明書が MFA に設定されます。 同様に、テナント レベルのアフィニティ バインドは *、高いアフィニティ*に設定できます。 すべての証明書は、高アフィニティ属性のみを使用して検証されます。

重要

管理者は、テナントの既定値を、ほとんどの証明書に適用できる値に設定する必要があります。 テナントの既定値とは異なる保護レベルまたはアフィニティ バインディングを必要とする特定の証明書に対してのみ、カスタム 規則を作成します。 すべての認証方法の構成は、同じポリシー ファイル内にあります。 複数の冗長ルールを作成すると、ポリシー ファイルのサイズ制限を超える可能性があります。

認証バインディング規則は、 **発行者**、 **ポリシー オブジェクト ID (OID)**、 **発行者、ポリシー OID** などの証明書属性を指定した値にマップします。 規則は、その規則の既定の保護レベルとアフィニティ バインディングを設定します。

既定のテナント設定を変更し、Microsoft Entra 管理センターを使用してカスタム ルールを作成するには:

1. 少なくとも [Authentication Policy Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) ロールが割り当てられているアカウントを使用して [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **Entra ID**&gt;**認証方法**&gt;**ポリシー**に移動します。
3. [ **移行の管理**] で、 **認証方法**&gt;**Certificate ベースの認証を**選択します。

    [Image: 認証ポリシーを設定する方法を示すスクリーンショット。]
4. 認証バインドとユーザー名バインドを設定するには、[ **構成**] を選択します。
5. 既定値を MFA に変更するには、[ **多要素認証**] を選択します。 保護レベル属性の既定値は、**単一要素認証**です。

    注

    カスタム規則が追加されない場合、既定の保護レベルが有効になります。 カスタム規則を追加すると、既定の保護レベルではなく、規則レベルで定義された保護レベルが適用されます。

    [Image: 既定の認証ポリシーを MFA に変更する方法を示すスクリーンショット。]
6. また、カスタム認証バインド規則を設定して、保護レベルまたはアフィニティ バインドにテナントの既定値とは異なる値を必要とするクライアント証明書の保護レベルを決定することもできます。 証明書の発行者のサブジェクトまたはポリシー OID、または両方のフィールドを使用して、規則を構成できます。

    認証バインディング規則は、証明書属性 (発行者またはポリシー OID) を値にマップします。 この値は、その規則の既定の保護レベルを設定します。 複数のルールを作成できます。 次の例では、テナントの既定値が **多要素認証** で、アフィニティ バインドの **場合は Low** であるとします。

    カスタム ルールを追加するには、**[ルールの追加]** をクリックします。

    [Image: カスタム ルールを追加する方法を示すスクリーンショット。]

    証明書発行者別にルールを作成するには:

    1. [ **証明書の発行者] を選択します**。
    2. [ **証明書発行者識別子**] で、関連する値を選択します。
    3. **[認証の強度**] で、[**多要素認証**] を選択します。
    4. **アフィニティ バインドの**場合は、[**低**] を選択します。
    5. **[追加]** を選択します。
    6. メッセージが表示されたら、[ **確認]** チェック ボックスをオンにしてルールを追加します。

        [Image: MFA ポリシーを高アフィニティ バインドにマップする方法を示すスクリーンショット。]

    ポリシー OID でルールを作成するには:

    1. **[ポリシー OID] を**選択します。
    2. **[ポリシー OID**] に値を入力します。
    3. **[認証の強度**] で、[**単一要素認証**] を選択します。
    4. **アフィニティ バインド**の場合は、[**低**] を選択してください。
    5. **[追加]** を選択します。
    6. メッセージが表示されたら、[ **確認]** チェック ボックスをオンにしてルールを追加します。

        [Image: アフィニティの低いバインドを使用したポリシー OID へのマッピングを示すスクリーンショット。]

    発行者とポリシー OID でルールを作成するには:

    1. [ **証明書の発行者** ] と [ **ポリシー OID] を**選択します。
    2. 発行者を選択し、ポリシー OID を入力します。
    3. **[認証の強度**] で、[**多要素認証**] を選択します。
    4. **アフィニティ バインドの**場合は、[**低**] を選択します。
    5. **[追加]** を選択します。

        [Image: 低アフィニティ バインドを選択する方法を示すスクリーンショット。]

        [Image: 低アフィニティ バインドを追加する方法を示すスクリーンショット。]
    6. `3.4.5.6`のポリシー OID を持ち、`CN=CBATestRootProd`によって発行される証明書で認証します。 多要素要求に対して認証が成功することを確認します。

    発行者とシリアル番号でルールを作成するには:

    1. 認証バインド ポリシーを追加します。 このポリシーでは、ポリシー OID が `CN=CBATestRootProd` の`1.2.3.4.6`によって発行された証明書には、高アフィニティ バインドのみが必要です。 発行者とシリアル番号が使用されます。

        [Image: Microsoft Entra 管理センター に追加された発行者とシリアル番号を示すスクリーンショット。]
    2. 証明書フィールドを選択します。 この例では、 **発行者とシリアル番号を選択します**。

        [Image: 発行者とシリアル番号を選択する方法を示すスクリーンショット。]
    3. サポートされている唯一のユーザー属性は `certificateUserIds`です。 `certificateUserIds`を選択し、[**追加]** を選択します。

        [Image: 発行者とシリアル番号を追加する方法を示すスクリーンショット。]
    4. **[保存]** を選択します。

        サインイン ログには、サインインに使用されたバインドと証明書の詳細が表示されます。

        [Image: サインイン ログの詳細を示すスクリーンショット。]
7. [ **OK] を** 選択してカスタム ルールを保存します。

重要

[オブジェクト識別子の形式](https://www.rfc-editor.org/rfc/rfc5280#section-4.2.1.4)を使用して、ポリシー OID を入力します。 たとえば、証明書ポリシーに **[すべての発行ポリシー**] と表示されている場合は、規則を追加するときに `2.5.29.32.0` としてポリシー OID を入力します。 **すべての発行ポリシー**という文字列はルール エディターに対して無効であり、有効になりません。

### 手順 4: ユーザー名バインド ポリシーを構成する

ユーザー名バインド ポリシーは、ユーザーの証明書を検証するのに役立ちます。 既定では、ユーザーを特定するために、証明書の **プリンシパル名** をユーザー オブジェクトの `userPrincipalName` にマップします。

認証ポリシー管理者は、既定値をオーバーライドし、カスタム マッピングを作成できます。 詳細については、「 [ユーザー名のバインドのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-technical-deep-dive#username-binding-policy)」を参照してください。

`certificateUserIds`属性を使用するその他のシナリオについては、「[証明書ユーザー ID](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-certificateuserids)」を参照してください。

重要

ユーザー名バインド ポリシーで、`certificateUserIds`、`onPremisesUserPrincipalName`、ユーザー オブジェクトの `userPrincipalName` 属性などの同期属性が使用されている場合、オンプレミスのWindows Server Active Directoryで管理アクセス許可を持つアカウントは、Microsoft Entra IDでこれらの属性に影響する変更を加えることができます。 たとえば、ユーザー オブジェクトに対する委任された権限を持つアカウントや、Microsoft Entra Connect Server の管理者ロールを持つアカウントは、これらの種類の変更を行うことができます。

1. X.509 証明書フィールドのいずれかを選択してユーザー属性の 1 つとバインドし、ユーザー名バインドを作成します。 ユーザー名のバインド順序は、バインドの優先順位を表します。 最初のユーザー名バインドの優先度が最も高いなどです。

    [Image: ユーザー名バインド ポリシーを示すスクリーンショット。]

    指定した X.509 証明書フィールドが証明書で見つかったが、Microsoft Entra ID対応する値を持つユーザー オブジェクトが見つからない場合、認証は失敗します。 次に、Microsoft Entra IDリスト内の次のバインドを試みます。
2. **[保存]** を選択します。

最終的な構成は、次の例のようになります。

[Image: 最終的な構成を示すスクリーンショット。]

### 手順 5: 構成をテストする

このセクションでは、証明書とカスタム認証バインド規則をテストする方法について説明します。

#### 証明書をテストする

最初の構成テストで、デバイス ブラウザーを使用して [MyApps ポータル](https://myapps.microsoft.com/) へのサインインを試みます。

1. ユーザー プリンシパル名 (UPN) を入力します。

    [Image: ユーザー プリンシパル名を示すスクリーンショット。]
2. [**次へ**] を選択します。

    [Image: 証明書を使用したサインインを示すスクリーンショット。]

    電話によるサインインや FIDO2 など、他の認証方法を使用できるようにすると、ユーザーに別のサインイン ダイアログが表示されることがあります。

    [Image: 別のサインイン ダイアログを示すスクリーンショット。]
3. **[証明書を使用してサインイン]** を選択します。
4. クライアント証明書ピッカー UI で適切なユーザー証明書を選択し、[ **OK] を選択します**。

    [Image: 証明書ピッカー UI を示すスクリーンショット。]
5. [MyApps ポータル](https://myapps.microsoft.com/)にサインインしていることを確認します。

サインインが成功した場合、次のことがわかります。

- ユーザー証明書はテスト デバイスにプロビジョニングされます。
- Microsoft Entra IDは、信頼できる CA を使用するように正しく構成されています。
- ユーザー名のバインドが正しく構成されています。 ユーザーが見つかり、認証されます。

#### カスタム認証バインド ルールをテストする

次に、強力な認証を検証するシナリオを完了します。 2 つの認証ポリシー 規則を作成します。1 つは単一要素認証を満たす発行者のサブジェクトを使用し、もう 1 つは多要素認証を満たすためにポリシー OID を使用します。

1. 単一要素認証の保護レベルで発行者のサブジェクトルールを作成します。 CA サブジェクト値に値を設定します。

    次に例を示します。

    `CN=WoodgroveCA`
2. 多要素認証の保護レベルを持つポリシー OID ルールを作成します。 証明書のポリシー OID のいずれかに値を設定します。 たとえば `1.2.3.4` です。

    [Image: ポリシー OID ルールを示すスクリーンショット。]
3. ユーザーが MFA を要求するためのMicrosoft Entra 条件付きアクセス ポリシーを作成します。 [「条件付きアクセス - MFA を要求](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength#create-a-conditional-access-policy)する」で説明されている手順を完了します。
4. [MyApps ポータル](https://myapps.microsoft.com/)に移動します。 UPN を入力し、**[次へ]** を選択します。

    [Image: ユーザー プリンシパル名を示すスクリーンショット。]
5. [ **証明書またはスマート カードを使用する**] を選択します。

    [Image: 証明書を使用したサインインを示すスクリーンショット。]

    電話によるサインインやセキュリティ キーなど、他の認証方法を使用できるようにした場合、ユーザーに別のサインイン ダイアログが表示されることがあります。

    [Image: 代替サインインを示すスクリーンショット。]
6. クライアント証明書を選択し、[ **証明書情報**] を選択します。

    [Image: クライアント ピッカーを示すスクリーンショット。]

    証明書が表示され、発行者とポリシー OID の値を確認できます。

    [Image: 発行者を示すスクリーンショット。]
7. ポリシー OID 値を表示するには、[ **詳細**] を選択します。

    [Image: 認証の詳細を示すスクリーンショット。]
8. クライアント証明書を選択し、**[OK]** を選択します。

証明書のポリシー OID は、 `1.2.3.4` の構成された値と一致し、MFA を満たします。 証明書の発行者は、 `CN=WoodgroveCA` の構成された値と一致し、単一要素認証を満たします。

ポリシー OID ルールは発行者ルールよりも優先されるため、証明書は MFA を満たします。

ユーザーの条件付きアクセス ポリシーには MFA が必要であり、証明書は MFA を満たすので、ユーザーはアプリケーションにサインインできます。

#### ユーザー名バインド ポリシーをテストする

ユーザー名のバインド ポリシーは、ユーザーの証明書を検証するために役立ちます。 ユーザー名バインド ポリシーでは、次の 3 つのバインドがサポートされています。

- `IssuerAndSerialNumber` &gt; `certificateUserIds`
- `IssuerAndSubject` &gt; `certificateUserIds`
- `Subject` &gt; `certificateUserIds`

既定では、Microsoft Entra IDは証明書の **Principal Name** をユーザー オブジェクトの `userPrincipalName` にマップして、ユーザーを特定します。 認証ポリシー管理者は、前に説明したように、既定値をオーバーライドし、カスタム マッピングを作成できます。

認証ポリシー管理者は、新しいバインディングを設定する必要があります。 準備するには、ユーザー オブジェクトの `certificateUserIds` 属性で、対応するユーザー名バインドの正しい値が更新されていることを確認する必要があります。

- クラウドのみのユーザーの場合は、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/concept-certificate-based-authentication-certificateuserids#update-certificate-user-ids-in-the-azure-portal) または [Microsoft Graph API](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/concept-certificate-based-authentication-certificateuserids#update-certificateuserids-using-microsoft-graph-queries) を使用して、`certificateUserIds` の値を更新します。
- オンプレミスの同期されたユーザーの場合は、Microsoft Entra Connect を使用して、オンプレミスの値を同期し、[Microsoft Entra Connect ルール](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/concept-certificate-based-authentication-certificateuserids#update-certificate-user-ids-using-azure-ad-connect)に従うか、[同期する`AltSecId`値](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/concept-certificate-based-authentication-certificateuserids#synchronize-alternativesecurityid-attribute-from-ad-to-azure-ad-cba-certificateuserids)を指定してください。

重要

**発行者**、**サブジェクト**、**シリアル番号**の値の形式は、証明書の形式の逆の順序にする必要があります。 **発行者**または**サブジェクト**の値にスペースを追加しないでください。

##### 発行者とシリアル番号の手動マッピング

次の例では、発行者とシリアル番号の手動マッピングを示します。

追加する **発行者** の値は次のとおりです。

`C=US,O=U.SGovernment,OU=DoD,OU=PKI,OU=CONTRACTOR,CN=CRL.BALA.SelfSignedCertificate`

[Image: 発行者の値の手動マッピングを示すスクリーンショット。]

シリアル番号の正しい値を取得するには、次のコマンドを実行します。 `certificateUserIds`に表示される値を格納します。

コマンド構文は次のとおりです。

```bash
certutil –dump –v [~certificate path~] >> [~dumpFile path~] 
```

次に例を示します。

```bash
certutil -dump -v firstusercert.cer >> firstCertDump.txt
```

`certutil` コマンドの例を次に示します。

```bash
certutil -dump -v C:\save\CBA\certs\CBATestRootProd\mfausercer.cer 

X509 Certificate: 
Version: 3 
Serial Number: 48efa06ba8127299499b069f133441b2 

   b2 41 34 13 9f 06 9b 49 99 72 12 a8 6b a0 ef 48 
```

`certificateUserId`に追加する**シリアル番号**の値は次のとおりです。

`b24134139f069b49997212a86ba0ef48`

`certificateUserIds`値は次のとおりです。

`X509:<I>C=US,O=U.SGovernment,OU=DoD,OU=PKI,OU=CONTRACTOR,CN=CRL.BALA.SelfSignedCertificate<SR> b24134139f069b49997212a86ba0ef48`

##### 発行者とサブジェクトの手動マッピング

次の例では、発行者とサブジェクトの手動マッピングを示します。

**発行者**の値は次のとおりです。

[Image: 複数のバインドで使用した場合の発行者の値を示すスクリーンショット。]

**Subject** 値は次のとおりです。

[Image: [件名] の値を示すスクリーンショット。]

`certificateUserId`値は次のとおりです。

`X509:<I>C=US,O=U.SGovernment,OU=DoD,OU=PKI,OU=CONTRACTOR,CN=CRL.BALA.SelfSignedCertificate<S> DC=com,DC=contoso,DC=corp,OU=UserAccounts,CN=FirstUserATCSession`

##### サブジェクトの手動マッピング

次の例では、サブジェクトの手動マッピングを示します。

**Subject** 値は次のとおりです。

[Image: 別の [件名] 値を示すスクリーンショット。]

`certificateUserIds`値は次のとおりです。

`X509:<S>DC=com,DC=contoso,DC=corp,OU=UserAccounts,CN=FirstUserATCSession`

#### アフィニティ バインドをテストする

1. 少なくとも[Authentication Policy Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)ロールが割り当てられているアカウントを使用して[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**認証方法**&gt;**ポリシー**に移動します。
3. [ **管理**] で、 **認証方法**&gt;**Certificate ベースの認証を**選択します。
4. **[構成]** をクリックします。
5. テナント レベルで**必要なアフィニティ バインド**を設定します。

    重要

    テナント全体のアフィニティ設定は、慎重に行って下さい。 テナントの **必須アフィニティ バインド** の値を変更し、ユーザー オブジェクトに正しい値がない場合は、テナント全体をロックアウトすることがあります。 同様に、すべてのユーザーに適用され、アフィニティの高いバインドが必要なカスタム ルールを作成すると、テナント内のユーザーがロックアウトされる可能性があります。

    [Image: 必要なアフィニティ バインディングを設定する方法を示すスクリーンショット。]
6. テストするには、[ **必要なアフィニティ バインド**] で [ **低**] を選択します。
7. サブジェクト キー識別子 (SKI) などの高アフィニティ バインディングを追加します。 [ **ユーザー名のバインド**] で、[ **ルールの追加]** を選択します。
8. **[SKI]** を選択し、**[追加]** を選択します。

    [Image: アフィニティ バインディングを追加する方法を示すスクリーンショット。]

    完了すると、ルールは次の例のようになります。

    [Image: 完了したアフィニティ バインドを示すスクリーンショット。]
9. すべてのユーザー オブジェクトについて、 `certificateUserIds` 属性をユーザー証明書の正しい SKI 値で更新します。

    詳細については、「[CertificateUserID のサポートされるパターン](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/concept-certificate-based-authentication-certificateuserids#supported-patterns-for-certificate-user-ids)」を参照してください。
10. 認証バインディング用のカスタム規則を作成します。
11. **[追加]** を選択します。

    [Image: カスタム認証バインドを示すスクリーンショット。]

    完成したルールが次の例のようになります。

    [Image: カスタム ルールを示すスクリーンショット。]
12. `certificateUserIds`の証明書とポリシー OID の適切な SKI 値を使用して、ユーザーの`9.8.7.5`値を更新します。
13. ポリシー OID が `9.8.7.5`の証明書を使用してテストします。 ユーザーが SKI バインディングで認証されていること、および MFA と証明書のみでサインインするように求められることを確認します。

### Microsoft Graph API を使用して CBA を設定する

Microsoft Graph API を使用して CBA を設定し、ユーザー名バインドを構成するには:

1. [Microsoft Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer) に移動します。
2. **Graph Explorer にサインイン**を選択し、テナントにサインインします。
3. [`Policy.ReadWrite.AuthenticationMethod`の委任されたアクセス許可に同意するには](https://learn.microsoft.com/ja-jp/graph/graph-explorer/graph-explorer-features#consent-to-permissions)、手順に従います。
4. すべての認証方法を取得します。

    ```http
    GET  https://graph.microsoft.com/v1.0/policies/authenticationmethodspolicy
    ```
5. X.509 証明書認証方法の構成を取得します。

    ```http
    GET https://graph.microsoft.com/v1.0/policies/authenticationmethodspolicy/authenticationMethodConfigurations/X509Certificate
    ```
6. 既定では、X.509 証明書の認証方法はオフになっています。 ユーザーが証明書を使用してサインインできるようにするには、認証方法を有効にし、更新操作を通じて認証ポリシーとユーザー名バインド ポリシーを構成する必要があります。 ポリシーを更新するには、 `PATCH` 要求を実行します。

#### 要求本文

    ```http
    PATCH https://graph.microsoft.com/v1.0/policies/authenticationMethodsPolicy/authenticationMethodConfigurations/x509Certificate
    Content-Type: application/json
    
    {
        "@odata.type": "#microsoft.graph.x509CertificateAuthenticationMethodConfiguration",
        "id": "X509Certificate",
        "state": "enabled",
        "certificateUserBindings": [
            {
                "x509CertificateField": "PrincipalName",
                "userProperty": "onPremisesUserPrincipalName",
                "priority": 1
            },
            {
                "x509CertificateField": "RFC822Name",
                "userProperty": "userPrincipalName",
                "priority": 2
            }, 
            {
                "x509CertificateField": "PrincipalName",
                "userProperty": "certificateUserIds",
                "priority": 3
            }
        ],
        "authenticationModeConfiguration": {
            "x509CertificateAuthenticationDefaultMode": "x509CertificateSingleFactor",
            "rules": [
                {
                    "x509CertificateRuleType": "issuerSubject",
                    "identifier": "CN=WoodgroveCA ",
                    "x509CertificateAuthenticationMode": "x509CertificateMultiFactor"
                },
                {
                    "x509CertificateRuleType": "policyOID",
                    "identifier": "1.2.3.4",
                    "x509CertificateAuthenticationMode": "x509CertificateMultiFactor"
                }
            ]
        },
        "includeTargets": [
            {
                "targetType": "group",
                "id": "all_users",
                "isRegistrationRequired": false
            }
        ]
    }
    ```
7. `204 No content`応答コードが返されることを確認します。 `GET`要求を再実行して、ポリシーが正しく更新されていることを確認します。
8. ポリシーを満たす証明書でサインインして、構成をテストします。

### Microsoft PowerShell を使用して CBA を設定する

1. PowerShell を開きます。
2. Microsoft Graphに接続します。

    ```powershell
    Connect-MgGraph -Scopes "Policy.ReadWrite.AuthenticationMethod"
    ```
3. CBA ユーザーのグループを定義するために使用する変数を作成します。

    ```powershell
    $group = Get-MgGroup -Filter "displayName eq 'CBATestGroup'"
    ```
4. 要求本文を定義します。

    ```powershell
    $body = @{
    "@odata.type" = "#microsoft.graph.x509CertificateAuthenticationMethodConfiguration"
    "id" = "X509Certificate"
    "state" = "enabled"
    "certificateUserBindings" = @(
        @{
            "@odata.type" = "#microsoft.graph.x509CertificateUserBinding"
            "x509CertificateField" = "SubjectKeyIdentifier"
            "userProperty" = "certificateUserIds"
            "priority" = 1
        },
        @{
            "@odata.type" = "#microsoft.graph.x509CertificateUserBinding"
            "x509CertificateField" = "PrincipalName"
            "userProperty" = "UserPrincipalName"
            "priority" = 2
        },
        @{
            "@odata.type" = "#microsoft.graph.x509CertificateUserBinding"
            "x509CertificateField" = "RFC822Name"
            "userProperty" = "userPrincipalName"
            "priority" = 3
        }
    )
    "authenticationModeConfiguration" = @{
        "@odata.type" = "#microsoft.graph.x509CertificateAuthenticationModeConfiguration"
        "x509CertificateAuthenticationDefaultMode" = "x509CertificateMultiFactor"
        "rules" = @(
            @{
                "@odata.type" = "#microsoft.graph.x509CertificateRule"
                "x509CertificateRuleType" = "policyOID"
                "identifier" = "1.3.6.1.4.1.311.21.1"
                "x509CertificateAuthenticationMode" = "x509CertificateMultiFactor"
            }
        )
    }
    "includeTargets" = @(
        @{
            "targetType" = "group"
            "id" = $group.Id
            "isRegistrationRequired" = $false
        }
    ) } | ConvertTo-Json -Depth 5
    ```
5. `PATCH`要求を実行します。

    ```powershell
    Invoke-MgGraphRequest -Method PATCH -Uri "https://graph.microsoft.com/v1.0/policies/authenticationMethodsPolicy/authenticationMethodConfigurations/x509Certificate" -Body $body -ContentType "application/json"
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-configure-certificate-authorities"} -->
## Microsoft Entra 証明書ベースの認証用に証明機関を構成する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-configure-certificate-authorities
- Service: entra-id / authentication
- Article date: 2025-07-02
- Summary: Microsoft Entra 証明書ベースの認証用に証明機関を構成する方法を示すトピック。

証明機関 (CA) を構成する最善の方法は、PKI ベースの信頼ストアを使用することです。 PKI ベースの信頼ストアを使用して、最小限の特権ロールに構成を委任できます。 詳細については、「 [手順 1: PKI ベースの信頼ストアを使用して証明機関を構成](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-certificate-based-authentication#step-1-configure-the-cas-with-a-pki-based-trust-store)する」を参照してください。

代わりに、グローバル管理者は、このトピックの手順に従って、Microsoft Entra 管理センター、または Microsoft Graph REST API と、Microsoft Graph PowerShell などのサポートされているソフトウェア開発キット (SDK) を使用して CA を構成することもできます。

重要

Microsoft は、アクセス許可が最も少ないロールを使用することを推奨しています。 このプラクティスは、組織のセキュリティを強化するのに役立ちます。 グローバル管理者は、緊急シナリオに限定する必要がある、または既存のロールを使用できない場合に、高い特権を持つロールです。

公開キー インフラストラクチャ (PKI) または PKI 管理者は、発行元 CA の一覧を提供できる必要があります。

すべての CA を構成していることを確認するには、ユーザー証明書を開き、[ **認定パス** ] タブをクリックします。ルートが Microsoft Entra ID 信頼ストアにアップロードされるまで、すべての CA を確認します。 CA が不足している場合、Microsoft Entra 証明書ベースの認証 (CBA) は失敗します。

#### Microsoft Entra 管理センターを使用して証明機関を構成する

Microsoft Entra 管理センターで CBA を有効にするよう証明機関を構成するには、次の手順を実行します。

重要

Microsoft は、アクセス許可が最も少ないロールを使用することを推奨しています。 このプラクティスは、組織のセキュリティを強化するのに役立ちます。 グローバル管理者は、緊急シナリオに限定する必要がある、または既存のロールを使用できない場合に、高い特権を持つロールです。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[ID セキュリティ スコア]**&gt;**[証明機関]** を参照します。
3. CA をアップロードするには、[ **アップロード**] を選択します。

    1. CA ファイルを選択します。
    2. CA がルート証明書の場合は **[はい** ] を選択し、それ以外の場合 **は [いいえ**] を選択します。
    3. **[証明書失効リスト URL]** で、失効したすべての証明書を含む CA ベース CRL のインターネットに接続する URL を設定します。 URL が設定されていない場合、失効した証明書を使用した認証が失敗しません。
    4. **[Delta Certificate Revocation List URL]\(差分証明書失効リスト URL**\) では、最後のベース CRL が発行されてからのすべての失効した証明書を含む CRL のインターネットに接続する URL を設定します。
    5. **追加**を選択します。

        [Image: 証明機関ファイルをアップロードする方法のスクリーンショット。]
4. CA 証明書を削除するには、証明書を選択し、[ **削除**] を選択します。
5. **列**を追加または削除するには**列**を選択します。

注

既存の CA の有効期限が切れた場合、新しい CA のアップロードは失敗します。 有効期限が切れた CA はすべて削除し、新しい CA をアップロードし直してください。

#### PowerShell を使用して証明機関 (CA) を構成する

信頼された CA に対してサポートされている CRL 配布ポイント (CDP) は 1 つのみです。 CDP には HTTP URL のみを指定できます。 オンライン証明書状態プロトコル (OCSP)、またはライトウェイト ディレクトリ アクセス プロトコル (LDAP) の URL はサポートされていません。

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

#### 追加

注

既存の CA のいずれかが期限切れになると、新しい CA のアップロードは失敗します。 テナント管理者は、期限切れの CA を削除してから、新しい CA をアップロードする必要があります。

上記の手順に従って、Microsoft Entra 管理センターに CA を追加します。

**権限タイプ**

- ルート証明機関を示すには、0 を使用します
- 中間または発行元の証明機関を示すには 1 を使用します

**crlDistributionPoint**

CRL をダウンロードし、CA 証明書と CRL 情報を比較します。 前の PowerShell 例の crlDistributionPoint 値が、追加する CA に対して有効であることを確認します。

次の表と図は、CA 証明書の情報とダウンロードした CRL の属性との対応関係を表しています。

| CA 証明書の情報 | = | ダウンロードされた CRL 情報 |
| --- | --- | --- |
| サブジェクト | = | 発行者 |
| サブジェクト キー識別子 | = | 機関キー識別子 (KeyID) |

[Image: CA 証明書と CRL 情報を比較します。]

ヒント

前の例の crlDistributionPoint の値は、CA の証明書失効リスト (CRL) がある場所の http です。 この値は、いくつかの場所にあります。

- CA から発行された証明書の CRL 配布ポイント (CDP) 属性。

発行元 CA が Windows Server を実行している場合:

- 証明機関 Microsoft 管理コンソール (MMC) の CA の [\[プロパティ\]](https://learn.microsoft.com/ja-jp/windows-server/networking/core-network-guide/cncg/server-certs/configure-the-cdp-and-aia-extensions-on-ca1#to-configure-the-cdp-and-aia-extensions-on-ca1)。
- CA (`certutil -cainfo cdp` を実行)。 詳細については、 [certutil](https://learn.microsoft.com/ja-jp/windows-server/administration/windows-commands/certutil#-cainfo) を参照してください。

詳細については、「 [証明書失効プロセスについて」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-certificate-revocation-list#enforce-crl-validation-for-cas)参照してください。

#### Microsoft Graph API を使用して証明機関を構成する

Microsoft Graph API を使用して、証明機関を構成できます。 Microsoft Entra 証明機関信頼ストアを更新するには、 [certificatebasedauthconfiguration MSGraph コマンド](https://learn.microsoft.com/ja-jp/graph/api/resources/certificatebasedauthconfiguration)の手順に従います。

#### 証明機関の構成を検証する

構成で Microsoft Entra CBA での次の操作が許可されていることを確認します。

- CA 信頼チェーンを検証する
- 構成された証明機関 CRL 配布ポイント (CDP) から証明書失効リスト (CRL) を取得する

CA 構成を検証するには、 [MSIdentity Tools](https://azuread.github.io/MSIdentityTools/) PowerShell モジュールをインストールし、 [Test-MsIdCBATrustStoreConfiguration](https://github.com/AzureAD/MSIdentityTools/wiki/Test-MsIdCBATrustStoreConfiguration) を実行します。 この PowerShell コマンドレットで、Microsoft Entra テナント CA の構成が確認されます。 一般的な構成ミスに関するエラーと警告が報告されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-deploy-phishing-resistant-passwordless-authentication"} -->
## Microsoft Entra ID で耐フィッシング パスワードレス認証の展開を計画する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-deploy-phishing-resistant-passwordless-authentication
- Service: entra-id / authentication
- Article date: 2026-03-26
- Summary: Microsoft Entra ID を使用する組織向けに、パスワードレスおよび耐フィッシング認証を展開するための詳細なガイダンス。

環境内でフィッシングに強いパスワードレス認証を展開して運用化する場合は、ユーザー ペルソナベースのアプローチをお勧めしますが、これは、使用している業界や予算などの他の側面によって異なる場合があります。 この展開ガイドは、組織内のユーザーにとって意味のある方法とロールアウト計画の種類を確認するのに役立ちます。 フィッシングに強いパスワードレス展開アプローチには複数の手順がありますが、他の手順に進む前に 100% 完了する必要はありません。

### ユーザー ペルソナを決定する

組織に関連するユーザー ペルソナを決定します。 ペルソナごとにニーズが異なるため、この手順はプロジェクトにとって非常に重要です。 Microsoft では、組織内の次のユーザー ペルソナを検討して評価することをお勧めします。

| ユーザー ペルソナ | 説明 |
| --- | --- |
| 管理者と厳しく規制されたユーザー | - ディレクトリ ロールを持つ管理者、または高い特権を持つアクションを実行できる管理者。<br>- 機密データ、重要な情報、またはコンピューティング システムにアクセスできるユーザー。<br>- 規制の厳しい組織で働くユーザー。<br>- 自動化を管理およびデプロイする DevOps worker または DevSecOps ワーカー。 |
| 非管理者 | - 顧客データ、重要な情報、またはコンピューティング システムにアクセスできない組織内のユーザー。 |

耐フィッシング パスワードレス認証を組織全体に広く展開することを、マイクロソフトはおすすめします。 最初に、いずれかのユーザー ペルソナを使用してデプロイを開始することをお勧めします。

これらのペルソナに基づいてユーザーを分類し、そのユーザーペルソナ専用の Microsoft Entra ID グループにユーザーを配置することをお勧めします。 これらのグループは、以降の手順でさまざまな種類のユーザーに[認証情報をロール アウト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-deploy-phishing-resistant-passwordless-authentication#drive-usage-of-phishing-resistant-credentials)し、[耐フィッシング パスワードレス認証情報の使用を開始](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-deploy-phishing-resistant-passwordless-authentication#step-4-enforcement-of-phishing-resistance-on-resources)するときに使用します。

グループを使用すると、組織の新しい部分を同時にオンボードし続けることができます。 「*完璧を求めすぎない*」アプローチを取りつつ、可能な限りセキュアな認証情報を展開します。 耐フィッシング パスワードレス認証情報を使用してサインインするユーザーが増えるほど、環境の攻撃面を減らすことができます。

### デバイスの準備を計画する

デバイスは、フィッシング詐欺に強いパスワードレス展開を成功させるために不可欠な要素です。 フィッシングに強いパスワードレス用にデバイスを準備するには、各オペレーティング システムでサポートされている最新バージョンにパッチを適用します。 フィッシングに強い資格情報を使用するには、デバイスで少なくとも次のバージョンが実行されている必要があります。

- Windows 10 22H2 (Windows Hello for Business の場合)
- Windows 11 22H2 (パスキー使用に最適なユーザー エクスペリエンスを提供するために)
- macOS 13 ベンチュラ
- iOS 17
- Android 14

これらのバージョンは、パスキー、Windows Hello for Business、macOS プラットフォーム資格情報などの機能とネイティブに統合されます。 古いオペレーティング システムでは、耐フィッシング パスワードレス認証をサポートするために、FIDO2 セキュリティ キーなどの外部認証子が必要になる場合があります。

詳細については、 [Microsoft Entra ID に対する FIDO2 のサポートについて理解を深めます](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-fido2-compatibility)。 [フィッシングに強いパスワードレス ブック (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-deploy-phishing-resistant-passwordless-authentication#driving-readiness-with-the-phishing-resistant-passwordless-workbook-preview) を使用して、テナント内のデバイスの準備状況を把握できます。

### フィッシング耐性向上のプロセス

フィッシング詐欺に強い資格情報の展開の一環として、ユーザーのオンボード方法、移植可能な資格情報とローカル資格情報の取得方法、および組織全体でフィッシングに対する耐性のある資格情報の適用方法を検討する必要があります。

[Image: 計画プロセスの最初の 3 つのフェーズを示す図。]

### 耐フィッシング認証情報をユーザーに登録する

耐フィッシング パスワードレスの展開プロジェクトで最初に行う重要なエンドユーザー向けアクティビティは、認証情報の登録とブートストラップです。 このセクションでは、**移植可能**と**ローカル**の認証情報のロールアウトについて説明します。

次の表では、さまざまな種類の資格情報と、Microsoft Entra ID で構成できる関連する認証方法を示します。

| 認証情報 | 説明 | メリット | 認証方法 |
| --- | --- | --- | --- |
| **移植可能** | [デバイスをまたいで](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-sign-in-passkey-authenticator)使用できます。 **移植可能**な資格情報を使用すると、別のデバイスにサインインすることや、他のデバイスに認証情報を登録することができます。 | デバイス間で使用でき、多くのシナリオで耐フィッシング認証を提供できるため、ほとんどのユーザーにとって最重要で登録するべき認証情報のタイプです。 | - 同期されたパスキー<br>- Authenticator の認証キー<br>- FIDO2 セキュリティキー<br>- 証明書ベースの認証 (スマート カード) |
| **ローカル** | 外部ハードウェアに依存することなく、 **ローカル** 資格情報を使用してデバイスで認証できます。 | ローカル資格情報は、ユーザーが Face ID や Windows Hello for Business の顔/指紋/PIN などのデバイス独自のロック解除ジェスチャを使用して正常に認証するためにデバイスを離れる必要がないため、優れたユーザー エクスペリエンスを提供します。 | - Windows Hello for Business<br>- Windows での Microsoft Entra パスキー<br>- Mac用プラットフォームSSO<br>- 証明書ベースの認証 |

- *新しいユーザー*の場合、登録とブートストラップのプロセスでは、既存のエンタープライズ資格情報なしでユーザーを取得し、その ID を検証します。 これにより、最初の移植可能な認証情報がブートストラップされ、その移植可能な認証情報を使用して、各コンピューティング デバイス上の他のローカル認証情報がブートストラップされます。 登録後、管理者は [Microsoft Entra ID のユーザーに対してフィッシングに対する耐性のある認証を適用](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-deploy-phishing-resistant-passwordless-authentication#step-4-enforcement-of-phishing-resistance-on-resources)できます。
- *既存のユーザー*の場合は、既存のデバイスでフィッシングに対するパスワードレスを登録するか、既存の MFA 資格情報を使用してフィッシングに対する耐性のあるパスワードレス資格情報をブートストラップする必要があります。

最終的な目標は、両方の種類のユーザーで同じです。ほとんどのユーザーは、少なくとも 1 つの **ポータブル** 資格情報と、各コンピューティング デバイス上の **ローカル** 資格情報を持っている必要があります。

注

ユーザーには少なくとも 2 つの認証方法が登録されていることを常にお勧めします。 これにより、デバイスの紛失や盗難などによりメインのメソッドに問題が発生した場合でも、ユーザーはバックアップのメソッドを利用できるようになります。 たとえば、ユーザーがパスキーを登録し、ワークステーションに Windows Hello for Business などのローカル資格情報を登録することをお勧めします。

#### 手順 1: ID 検証

新入社員など、自分の身元を証明していないリモート ユーザーにとって、エンタープライズ オンボーディングは大きな課題です。 適切な ID 検証がないと、組織は、意図したユーザーをオンボードしていることを完全に確認できません。 Microsoft Entra の検証済み ID は、高保証の ID 検証を提供できます。 組織は ID 検証パートナー (IDV) と連携して、オンボード プロセスで新しいリモート ユーザーの ID を確認できます。 ユーザーの政府発行 ID を処理した後、IDV はユーザーの ID を確認する検証済み ID を提供できます。 新しいユーザーは、この ID を確認する検証済み ID を採用組織に提示して信頼を確立し、組織が適切なユーザーをオンボードしていることを確認します。 組織は、顔照合レイヤーを検証に追加する Microsoft Entra 検証済み ID を使用して Face Check を追加し、信頼されたユーザーがその時点で ID を確認する検証済み ID を提示していることを確認できます。

校正プロセスを通じて身元を確認した後、新入社員には、最初のポータブル資格情報のブートストラップに使用できる一時的なアクセス パス (TAP) が付与されます。

Microsoft Entra Verified ID のオンボーディングと TAP 発行を有効にするには、次のガイドを参照してください。

- [ID 検証を使用して新しいリモートの従業員をオンボードする](https://learn.microsoft.com/ja-jp/entra/verified-id/remote-onboarding-new-employees-id-verification)
- [Microsoft Entra 検証済み ID で Face Check を使用して、大規模な](https://learn.microsoft.com/ja-jp/entra/verified-id/using-facecheck) での高保証検証のロックを解除する
- [一時アクセス パス ポリシーを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass#enable-the-temporary-access-pass-policy)

Microsoft Entra Verified ID のライセンスの詳細については、次のリンクを参照してください。

- [Microsoft Entra Verified ID での顔認証の価格](https://learn.microsoft.com/ja-jp/entra/verified-id/verified-id-pricing)
- [Microsoft Entra のプランと価格](https://www.microsoft.com/security/business/microsoft-entra-pricing)

組織によっては、ユーザーをオンボードして最初の認証情報を発行するために、Microsoft Entra Verified ID 以外の方法を選択する場合もあります。 このような組織には、引き続き TAP を使用すること、またはユーザーがパスワードなしでオンボードできるようにする別の方法を使用することを、マイクロソフトはおすすめします。 たとえば、[Microsoft Graph API を使用して FIDO2 セキュリティ キーをプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2#provision-fido2-security-keys-using-microsoft-graph-api-preview)できます。

#### 手順 2: 移植可能な資格情報をブートストラップする

既存のユーザーを耐フィッシング パスワードレス認証情報にブートストラップするには、まず、ユーザーが従来の MFA に既に登録されているかどうかを特定します。 従来の MFA 方法が登録されているユーザーは、耐フィッシング パスワードレスの登録ポリシーの対象とすることができます。 従来の MFA を使用して、最初の移植可能な耐フィッシング認証情報を登録し、必要に応じてローカル認証情報の登録に進むことができます。 Microsoft Entra Verified ID 統合を使用して、一時アクセス パス (TAP) を使用してフィッシングに強い認証方法を登録することをお勧めします。

MFA を持たない新しいユーザーまたはユーザーの場合は、ユーザーに TAP を発行するプロセスを実行します。 TAP の発行は、新規ユーザーに最初の認証情報を与えるのと同じ方法で、または Microsoft Entra Verified ID 統合を使用して行えます。 ユーザーが TAP を取得することで、最初の耐フィッシング認証情報をブートストラップする準備が整います。

ユーザーの最初のパスワードレス認証情報は、他のコンピューティング デバイスでの認証に使用できる移植可能な認証情報であることが重要です。 たとえばパスキーは、iOS 電話でローカルに認証することに使用できますが、クロスデバイス認証フローを使用して Windows PC で認証するために使用することもできます。 このクロスデバイス機能を使用すると、Microsoft Entra ID と [Windows 用 Web サインイン](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/web-sign-in)に参加しているデバイスを使用する場合に、Windows PC で Windows Hello for Business をブートストラップするためにポータブル パスキーを使用できます。

次の表に、さまざまなペルソナに推奨される移植可能な資格情報を示します。

| ユーザー ペルソナ | おすすめの移植可能な認証情報 | 代替の移植可能な認証情報 |
| --- | --- | --- |
| 管理者と厳しく規制されたユーザー | FIDO2 セキュリティキー | Microsoft Authenticator のパスキー、証明書ベースの認証 (スマート カード) |
| その他のユーザー | 同期されたパスキー | FIDO2 セキュリティ キー、Microsoft Authenticator アプリのパスキー |

パスキー認証資格情報のスコープは、 [パスキー プロファイル](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2)を使用して特定のユーザー グループに設定できます。 推奨されるポータブル資格情報が選択された各ペルソナに対してパスキー プロファイルを設定する必要があります。

次のガイダンスを使用して、組織の関連するユーザー ペルソナに対して推奨される代替の移植可能な認証情報を有効にします。

| メソッド | ガイダンス |
| --- | --- |
| FIDO2 セキュリティキー | - FIDO2 セキュリティ キーは、Microsoft Entra ID で有効にする必要があります。 [FIDO2 セキュリティ キーは、認証方法ポリシーで有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2)ことができます。<br>- ユーザーに代わって Microsoft Entra ID プロビジョニング API にキーを登録することを検討してください。 詳細については、「[Microsoft Graph API を使用して FIDO2 セキュリティ キーをプロビジョニングする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2#provision-fido2-security-keys-using-microsoft-graph-api-preview)」を参照してください。 |
| 同期されたパスキー | - 同期されたパスキーは、Microsoft Entra ID で有効にする必要があります。 [認証方法ポリシーで FIDO2 セキュリティ キーを有効にすることができます](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2)<br>- ユーザーは、Apple キーチェーンと Google クラウドまたはサード パーティのパスキー マネージャーによって管理される同期されたパスキーを使用できます |
| Microsoft Authenticator のパスキー | - Microsoft Authenticator のパスキーは、Microsoft Entra ID で有効にする必要があります。 [認証方法ポリシーで FIDO2 セキュリティ キーを有効にすることができます](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2)<br>- ユーザーは Microsoft Authenticator アプリに直接サインインして、アプリでパスキーをブートストラップします。<br>- ユーザーは TAP を使用して、iOS または Android デバイスで直接 Microsoft Authenticator にサインインできます。 [Android または iOS デバイスの Authenticator にパスキーを登録する。](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-register-passkey-authenticator) |
| スマート カード/証明書ベースの認証 (CBA): | - 証明書ベースの認証は、パスキーやその他のメソッドよりも設定が複雑です。 必要な場合のみ使用を検討してください。<br>- [Microsoft Entra 証明書ベースの認証を設定する方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-certificate-based-authentication)。<br>- ユーザーが多要素認証を間違いなく完了してサインインできるように、オンプレミス PKI ポリシーと Microsoft Entra ID CBA ポリシーを設定してください。 通常、この設定にはスマート カード ポリシー オブジェクト識別子 (OID) と、必要に応じたアフィニティ バインディングの設定が必要です。 より高度な CBA 設定については、「[認証バインド ポリシーの概要](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication-technical-deep-dive#authentication-binding-policy)」を参照してください。 |

#### 手順 3: ローカル資格情報のブートストラップ

ユーザーが定期的に作業している各デバイスには、ユーザーに可能な限りスムーズなユーザー エクスペリエンスを提供するために、ローカルで使用可能な資格情報を持っていることをお勧めします。 ローカルで使用できる資格情報は、ユーザーが複数のデバイスを使用する必要がなく、ユーザーがデバイスのロック解除ジェスチャを使用して認証するため、認証に必要な時間を短縮します。

ユーザーがポータブル資格情報を取得したら、ローカル資格情報をブートストラップするために使用できます。これにより、ユーザーは自分が所有している可能性がある他のデバイス間でフィッシングに対する耐性のある資格情報をネイティブに使用できます。

注

デバイスを共有するユーザーは、Windowsまたは移植可能な資格情報でのみ、Microsoft Entraパスキーを使用する必要があります。

次の表を使用して、各ユーザーペルソナに対して優先されるローカル資格情報の種類を決定します。

| ユーザー ペルソナ | おすすめのローカル認証情報 - Windows | おすすめのローカル認証情報 - macOS | おすすめのローカル認証情報 - iOS | おすすめのローカル認証情報 - Android | おすすめのローカル認証情報 - Linux |
| --- | --- | --- | --- | --- | --- |
| 管理者と厳しく規制された作業者 | Windows Hello for Business、Windows での Microsoft Entra パブリック キー、または CBA | プラットフォーム SSO セキュア エンクレーブ キーまたは CBA | Microsoft Authenticator または CBA のパスキー | Microsoft Authenticator または CBA のパスキー | N/A (代わりにスマート カードを使用する) |
| 非管理者 | Windows Hello for Business、Windows での Microsoft Entra パスキー | プラットフォーム シングル サインオン (SSO) セキュア エンクレーブ キー | 同期されたパスキー | 同期されたパスキー | N/A (代わりに移植可能な認証情報を使用する) |

次のガイダンスを使用して、組織の関連するユーザー ペルソナに対して、環境内で推奨されるローカル認証情報を有効にします。

| メソッド | ガイダンス |
| --- | --- |
| Windows Hello for Business | - Cloud Kerberos Trust メソッドを使用して、Windows Hello for Business を展開します。 詳細については「[Cloud Kerberos Trust の展開ガイド](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/deploy/hybrid-cloud-kerberos-trust?tabs=intune)」を参照してください。 Cloud Kerberos Trust メソッドは、ユーザーが オンプレミスの Active Directory から Microsoft Entra ID に同期されるすべての環境に適用されます。 これは、Microsoft Entra 参加済みまたは Microsoft Entra ハイブリッド参加済みの PC 上の同期済みユーザーを支援します。<br>- Windows Hello for Business は、PC 上の各ユーザーが、自分自身としてその PC にサインインしている場合しか使用しないようにするべきです。 共有ユーザー アカウントを使用するキオスク デバイスでは使用しないでください。<br>- Windows Hello for Business では、デバイスあたり最大 10 人のユーザーがサポートされます。 共有デバイスでより多くのユーザーをサポートする必要がある場合は、セキュリティ キーなどの移植可能な認証情報を代わりに使用します。<br>- 生体認証は省略可能ですが、推奨されます。 詳細については、「[ユーザーによる Windows Hello for Business のプロビジョニングと使用について準備する](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/deploy/prepare-users)」を参照してください。 |
| Windows での Microsoft Entra パスキー | - ユーザーは Windows Hello (顔、指紋、または PIN) で認証されます<br>- ユーザーは、Microsoft Entra参加または登録されていないデバイス上のWindowsでMicrosoft Entraパスキーを使用できます<br>- ユーザーは、各アカウントが独自のパスキーを登録して、同じ Windows デバイス上の複数の Microsoft Entra アカウントにサインインできます。<br>- Windowsのパスキー Microsoft Entraデバイスバインドされており、デバイス間で同期されません。各デバイスには、Microsoft Entra アカウントごとに個別の登録が必要です。 |
| macOS のプラットフォーム資格情報 | - macOS のプラットフォーム資格情報では、3 つの異なるユーザー認証方法 (セキュリティで保護されたエンクレーブ キー、スマート カード、パスワード) がサポートされています。 セキュア エンクレーブ キー メソッドを展開して、Windows Hello for Business を Mac にミラーリングします。<br>- macOS のプラットフォーム資格情報では、Mac がモバイル デバイス管理 (MDM) に登録されている必要があります。 Intune の具体的な手順については、「 [Microsoft Intune で macOS デバイスのプラットフォーム資格情報を構成](https://learn.microsoft.com/ja-jp/mem/intune/configuration/platform-sso-macos)する」を参照してください。<br>- Mac で別の MDM サービスを使用する場合は、MDM ベンダーのドキュメントを参照してください。 |
| Microsoft Authenticator のパスキー | - Microsoft Authenticator でパスキーをブートストラップするには、同じデバイス登録オプションを使用します。<br>- ユーザーは TAP を使用して、iOS または Android デバイスで直接 Microsoft Authenticator にサインインする必要があります。<br>- 認証方法ポリシーで、Microsoft Entra ID でパスキーを有効にする必要があります。 詳細については、「[Microsoft Authenticator でパスキーを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-enable-authenticator-passkey)」を参照してください。<br>- Android または iOS デバイスの Microsoft Authenticator アプリにパスキーを登録します。 |

#### ペルソナ固有の考慮事項

各ペルソナ内には、独自の課題と考慮事項を持つ特定のロール機能が存在する場合があります。 組織内の特定のロールに対する特定のガイダンスを含む次の表を検討できます。

| ペルソナ | ロールの例 |
| --- | --- |
| 管理者 | - [管理者と厳しく規制された作業者](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-persona-phishing-resistant-passwordless-authentication#highly-regulated-workers)<br>- IT 開発 |
| 管理者以外 | - [インフォメーション ワーカー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-persona-phishing-resistant-passwordless-authentication#information-workers)<br>- [フロントライン ワーカー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-persona-phishing-resistant-passwordless-authentication#frontline-workers) |

### 耐フィッシング認証情報の使用を促進する

この手順では、ユーザーが耐フィッシング認証情報を簡単に導入できるようにするための方法について説明します。 展開戦略をテストし、ロールアウトの計画を立て、エンド ユーザーに計画を伝える必要があります。 次に、組織全体で耐フィッシング認証情報を実施する前に、レポートを作成して進行状況を監視することができます。

#### テスト展開の戦略

前の手順で作成した展開戦略は、一連のテスト ユーザーとパイロット ユーザーでテストすることを、マイクロソフトはおすすめします。 このフェーズには、次の手順が記載されます。

- テスト ユーザーと早期導入者の一覧を作成します。 これらのユーザーは、管理者だけでなく、サポートされているデバイスの種類を使用して、異なるユーザー ペルソナを表す必要があります。
- Microsoft Entra ID グループを作成し、テスト ユーザーをグループに追加します。
- Microsoft Entra ID で [認証方法ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-methods-manage)を有効にし、テスト グループの範囲を有効にしているメソッドに設定します。
- [認証方法活動記録](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-methods-activity)レポートを使用して、パイロット ユーザーの登録ロールアウトを測定します。
- 条件付きアクセス ポリシーを作成して、オペレーティング システムの種類ごとにフィッシングに強いパスワードレス資格情報の使用を強制し、パイロット グループを対象にします。
- [Azure Monitor と Workbooks](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-workbooks) を使用して、実施の成功を測定します。
- ロールアウトの成功に関するフィードバックをユーザーから収集します。

#### ロールアウト戦略を計画する

使用の促進は、展開の準備が最も整っているユーザー ペルソナに基づいて行うことを、マイクロソフトはおすすめします。 通常、これは管理者によるパイロットを最初に行い、次に管理者以外のユーザー グループに広く展開することを意味しますが、これは組織のニーズに応じて変わる可能性があります。

次のセクションを使用して、各ペルソナ グループのエンド ユーザー通信を作成し、パスキー登録機能のスコープとロールアウトを行い、ロールアウトの進行状況を追跡するためのユーザーレポートと監視を行います。

#### Phishing-Resistant パスワードレス ワークブックを使用した準備の向上 (プレビュー)

組織は、必要に応じて、Microsoft Entra ID サインイン ログを [Azure Monitor](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs) にエクスポートして、長期的な保有、脅威ハンティング、その他の目的でエクスポートすることもできます。 Microsoft は、Azure Monitor のログを持つ組織が、フィッシングに強いパスワードレスデプロイのさまざまなフェーズを支援するために使用できる [ブック](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-workbooks) をリリースしました。 Phishing-Resistant パスワードレス ブックには、 https://aka.ms/PasswordlessWorkbook でアクセスできます。 ***Phishing-Resistant パスワードなしデプロイメント (プレビュー)*** というタイトルのワークブックを選択します。

[Image: Microsoft Entra ID のさまざまなワークブックのスクリーンショット。]

ワークブックには、次の 2 つの主要なセクションがあります。

1. 登録準備フェーズ
2. 強制準備フェーズ

##### 登録準備フェーズ

[登録の準備フェーズ] タブを使用して、テナントのサインイン ログを分析し、登録の準備ができているユーザーと登録をブロックできるユーザーを決定します。 たとえば、[登録準備フェーズ] タブでは、OS プラットフォームとして iOS を選択し、準備状況を評価する資格情報の種類として Microsoft Authenticator でパスキーを選択できます。 次に、ワークブックのビジュアライゼーションをクリックして、登録に問題があると予想されるユーザーをフィルタリングし、リストをエクスポートできます。

[Image: Phishing-Resistant パスワードレス ブックの登録フェーズのスクリーンショット。]

ブックの [登録準備フェーズ] タブは、次のオペレーティング システムと資格情報の準備状況を評価するのに役立ちます。

- ウィンドウズ
    - Windows Hello for Business
    - FIDO2 セキュリティ キー
    - Certificate-Based 認証/スマート カード
- macOS
    - プラットフォーム SSO セキュア エンクレーブ キー
    - FIDO2 セキュリティ キー
    - Certificate-Based 認証/スマート カード
- iOS
    - 同期されたパスキー
    - FIDO2 セキュリティ キー
    - Microsoft Authenticator のパスキー
    - Certificate-Based 認証/スマート カード
- アンドロイド
    - 同期されたパスキー
    - FIDO2 セキュリティ キー
    - Microsoft Authenticator のパスキー
    - Certificate-Based 認証/スマート カード

エクスポートされた各リストを使用して、登録に問題がある可能性があるユーザーをトリアージします。 登録の問題への対応には、ユーザーによるデバイスの OS バージョンのアップグレード、古くなったデバイスの交換、優先オプションが有効でない代替資格情報の選択が含まれます。 たとえば、組織は、同期されたパスキーを使用できない Android 13 ユーザーに物理 FIDO2 セキュリティ キーを提供することを選択できます。

同様に、登録準備レポートを使用して、 全体的なロールアウト戦略に合わせて、登録通信とキャンペーンを開始する準備ができているユーザーの一覧を作成するのに役立ちます。

##### 強制準備フェーズ

ユーザーがフィッシング耐性のみの認証の準備ができたら、フィッシングに対する耐性のある認証を適用できます。つまり、ポリシー内のリソースにアクセスするには、フィッシングに対する耐性のある資格情報を使用して MFA を実行する必要があります。

強制準備フェーズの最初の手順は、Report-Only モードで条件付きアクセス ポリシーを作成することです。 このポリシーにより、フィッシング詐欺耐性のある適用スコープにユーザーやデバイスを配置した場合、アクセスがブロックされたかどうかに関するデータがサインインログに記録されます。 次の設定を使用して、テナントに新しい条件付きアクセス ポリシーを作成します。

| 設定 | 価値 |
| --- | --- |
| ユーザー/グループの割り当て | ブレーク グラス アカウントを除くすべてのユーザー |
| アプリ割り当て | すべてのリソース |
| 制御の許可 | 認証強度を要求する - フィッシングに対する耐性のある MFA |
| ポリシーを有効にする | レポート専用 |

登録キャンペーンを開始する前に、ロールアウトのできるだけ早い段階でこのポリシーを作成してください。 これにより、ポリシーが適用された場合に、どのユーザーとサインインがポリシーによってブロックされたかを示す適切な履歴データセットが確実に作成されます。

次に、ワークブックを使用して、適用の準備ができているユーザーとデバイスのペアを分析します。 適用の準備ができているユーザーの一覧をダウンロードし、適用ポリシーに合わせて作成されたグループに追加 します。 まず、ポリシー フィルターで読み取り専用の条件付きアクセス ポリシーを選択します。

[Image: レポート専用の条件付きアクセス ポリシーが選択されている Phishing-Resistant パスワードレス ブックの強制フェーズのスクリーンショット。]

このレポートには、各デバイス プラットフォームでフィッシングに対するパスワードレス要件を正常に通過できたユーザーの一覧が表示されます。 各リストをダウンロードし、デバイス プラットフォームに合わせた適用グループに適切なユーザーを配置します。

[Image: Phishing-Resistant パスワードレス ブックの適用準備ができているユーザーの一覧の強制フェーズのスクリーンショット。]

各適用グループにほとんどのユーザーまたはすべてのユーザーが含まれる時点に達するまで、このプロセスを時間の経過と同時に繰り返します。 最終的には、レポート専用ポリシーを有効にして、テナント内のすべてのユーザーとデバイス プラットフォームに適用を提供できるようになります。 この完了状態に達したら、各デバイス OS の個々の適用ポリシーを削除して、必要な条件付きアクセス ポリシーの数を減らすことができます。

###### 適用の準備ができていないユーザーを調査する

[ ***さらにデータ分析*** ] タブを使用して、特定のユーザーがさまざまなプラットフォームで適用する準備ができていない理由を調査します。 ***[ポリシーを満たしていない]*** チェックボックスをオンにして、レポート専用の条件付きアクセス ポリシーによってブロックされていた可能性のあるユーザー サインインにデータをフィルター処理します。

[Image: Phishing-Resistant パスワードレス ワークブックの [さらにデータを分析] タブにおける適用フェーズのスクリーンショット。]

このレポートで提供されるデータを使用して、ブロックされたユーザー、使用していたデバイス オペレーティング システム、使用していたクライアント アプリの種類、アクセスしようとしているリソースを特定します。 このデータは、ユーザーをさまざまな修復または登録アクションの対象にするのに役立ちます。これにより、ユーザーを効果的に適用のスコープに移動できます。

#### エンド ユーザー コミュニケーションを計画する

マイクロソフトはエンド ユーザー向けのコミュニケーション テンプレートを提供しています。 [認証ロールアウト資料](https://www.microsoft.com/download/details.aspx?id=57600) には、フィッシングに強いパスワードレス認証の展開についてユーザーに通知するカスタマイズ可能な電子メール テンプレートが含まれています。 次のテンプレートを使用して、耐フィッシング パスワードレス展開について理解できるように、ユーザーとのコミュニケーションを行います。

- [ヘルプデスク用のパスキー](https://download.microsoft.com/download/1/4/E/14E6151E-C40A-42FB-9F66-D8D374D13B40/Passkey_Helpdesk.docx)
- [近日公開予定のパスキー](https://download.microsoft.com/download/1/4/E/14E6151E-C40A-42FB-9F66-D8D374D13B40/Passkeys%20coming%20soon.docx)
- [Authenticator でパスキーを登録する](https://download.microsoft.com/download/1/4/E/14E6151E-C40A-42FB-9F66-D8D374D13B40/Register%20for%20Authenticator%20App%20Passkey.docx)
- [Authenticator でパスキーを登録するためのリマインダー](https://download.microsoft.com/download/1/4/E/14E6151E-C40A-42FB-9F66-D8D374D13B40/Reminder%20to%20register%20for%20Authenticator%20App%20Passkey.docx)

できるだけ多くのユーザーを捉えられるように、コミュニケーションは繰り返し行う必要があります。 たとえば、組織では、次のようなパターンを使用して、さまざまなフェーズとタイムラインのコミュニケーションを行うことを選択できます。

1. 実施まで 60 日: 耐フィッシング認証方法の価値を伝え、ユーザーに積極的に登録することを促します
2. 適用まで残り 45 日: メッセージの再通知
3. 実施まで 30 日: 30 日後に耐フィッシングの実施が始まることを伝え、ユーザーに積極的に登録することを促します
4. 実施まで 15 日: メッセージを繰り返し、ヘルプ デスクへの連絡方法を通知します
5. 実施まで 7 日: メッセージを繰り返し、ヘルプ デスクへの連絡方法を通知します
6. 実施まで 1 日: 24 時間後に実施されることを通知し、ヘルプ デスクへの連絡方法を通知します

Microsoftでは、メール以外のチャネルを介してユーザーと通信することをお勧めします。 その他のオプションとしては、Microsoft Teamsメッセージ、休憩室のポスター、選ばれた従業員が同僚にプログラムを推奨するトレーニングを受けるチャンピオン プログラムなどがあります。

#### レポートと監視

前に説明した Phishing-Resistant パスワードレスワークブック を使用して、ロールアウトの監視やレポートを支援してください。 さらに、以下で説明するレポートを使用するか、フィッシング耐性のあるパスワードレス ワークブックを使用できない場合は、これらのレポートを参照してください。

Microsoft Entra ID レポート ([認証方法アクティビティ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-methods-activity)や[Microsoft Entra 多要素認証のサインイン イベント詳細](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-reporting)) は、導入の測定と促進に役立つ技術的およびビジネス上の分析情報を提供します。

認証方法アクティビティ ダッシュボードから、登録と使用状況を表示できます。

- **登録**には、耐フィッシング パスワードレス認証や、その他の認証方法を利用できるユーザーの数が表示されます。 ユーザーが登録した認証方法と、各方法の最近の登録を示すグラフを表示できます。
- **使用**は、サインインに使用された認証方法を表示します。

ビジネスおよびテクニカル アプリケーションのオーナーは、組織の要件に基づいてレポートを所有および受信する必要があります。

- 認証方法の登録活動記録レポートを使用して、耐フィッシング パスワードレス認証情報のロールアウトを追跡します。
- 認証方法のサインイン アクティビティ レポートとサインイン ログを使用して、フィッシングに強いパスワードレス資格情報のユーザー導入を追跡します。
- [サインイン活動記録レポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)を使用すると、さまざまなアプリケーションへのサインインに使用された認証方法を追跡できます。 ユーザー行を選択します。認証方法とそれに対応するサインイン アクティビティを表示するには、[認証の **詳細]** を選択します。

Microsoft Entra ID では、次の条件では監査ログにエントリが追加されます。

- 管理者が認証方法を変更した場合。
- ユーザーが、Microsoft Entra ID 内で資格情報に対して何らかの種類の変更を行った場合。

Microsoft Entra ID では、ほとんどの監査データが 30 日間保持されます。 監査、傾向分析やその他のビジネス ニーズに応じて、保持期間を長くすることをおすすめします。

Microsoft Entra 管理センターまたは API の監査データにアクセスし、解析システムにダウンロードします。 データ保持期間を長くする必要がある場合は、Microsoft Sentinel、Splunk、Sumo Logic などのセキュリティ情報イベント管理 (SIEM) ツールでログをエクスポートして実行します。

#### ヘルプ デスクチケットのボリュームを監視する

IT ヘルプ デスクは、デプロイがどの程度進んでいるかについて非常に重要なシグナルを提供できるため、耐フィッシング パスワードレス展開を実行するときは、ヘルプ デスク チケットの量を追跡することを、マイクロソフトはおすすめします。

ヘルプ デスクチケットの量が増えるほど、展開、ユーザー コミュニケーション、実施アクションのペースを遅くする必要があります。 チケットの量が減ると、これらのアクティビティのペースを再度上げることができます。 このアプローチを使用するには、ロールアウト計画の柔軟性を維持する必要があります。

たとえば、特定の日付ではなく日付の範囲が設定されたウェーブで展開と実施を実行するようにします。

1. 6 月 1 日から 15 日: ウェーブ 1 コーホートの登録の展開とキャンペーン
2. 6 月 16 日から 30 日:ウェーブ 2 コーホートの登録の展開とキャンペーン
3. 7 月 1 日から 15 日: ウェーブ 3 コーホートの登録の展開とキャンペーン
4. 7 月16 日から 31 日: Wave 1 コホートの適用開始
5. 8 月 1 日から 15 日: Wave 2 コーホートの適用開始
6. 8月16日から31日: ウェーブ3コホートの適用を有効化

これらの異なるフェーズを実行する際に、ヘルプ デスク チケットを開いた量に従い速度を低下させ、量が落ち着いたら再開することが必要になる場合があります。 この戦略を実行するには、ウェーブごとに Microsoft Entra ID セキュリティ グループを作成し、各グループをポリシーに一度に 1 つずつ追加することを、マイクロソフトはおすすめします。 このアプローチは、サポート チームに過度の負荷を与えることを回避するのに役立ちます。

### サインインに対して耐フィッシング メソッドを実施する

耐フィッシング パスワードレス展開の最終フェーズでは、耐フィッシング認証情報の使用を実施します。

[Image: デプロイの適用フェーズを示す図。]

#### 手順 4: リソースに対するフィッシング詐欺対策の適用

Microsoft Entra ID でフィッシングに対する耐性のある資格情報を適用する主なメカニズムは、 [条件付きアクセス認証の強度です](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strengths)。 ユーザー/デバイス ペアの方法論に基づいて各ペルソナに実施するアプローチを、マイクロソフトはおすすめしています。 たとえば、施行のロールアウトは次のパターンに従う可能性があります。

1. Windows および iOS の管理者
2. macOS および Android の管理者
3. Windows および macOS 上の他のユーザー
4. iOS および Android 上の他のユーザー

テナントからのサインイン データを使用して、すべてのユーザーとデバイスのペアのレポートを作成することを、マイクロソフトはおすすめします。 [Azure Monitor や Workbooks](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-workbooks) などのクエリ ツールを使用できます。 少なくとも、これらのカテゴリに一致するすべてのユーザーとデバイスのペアを特定してみてください。

前に解説された Phishing-Resistant パスワードレスワークブック を使用して、可能であれば適用フェーズを支援します。

ユーザーごとに、作業に定期的に使用するオペレーティング システムのリストを作成します。 そのユーザー/デバイス ペアに対する耐フィッシング サインイン実施の準備状況に、リストをマッピングします。

| OS の種類 | 実施の準備完了 | 実施の準備ができていない |
| --- | --- | --- |
| ウィンドウズ | 10 以上 | 8.1 およびそれ以前、Windows Server |
| iOS | 17 以降 | 16 以前 |
| Android | 14 以降 | 13 以前 |
| macOS | 13以上 (Ventura) | 12 以前 |
| VDI | 条件次第^1^ | 条件次第^1^ |
| その他 | 条件次第^1^ | 条件次第^1^ |

^1^デバイスのバージョンが実施準備ができていないユーザー/デバイスの各ペアについて、フィッシング耐性を強化する必要性にどのように対処するかを特定します。 古いオペレーティング システム、仮想デスクトップ インフラストラクチャ (VDI)、Linux、その他のオペレーティング システムについては、次のオプションを検討します。

- 外部ハードウェアを使用して耐フィッシングを実施する – FIDO2 セキュリティ キー
- 外部ハードウェアを使用して耐フィッシングを実施する - スマート カード
- リモート認証情報としてのパスキーを使用して、クロスデバイス認証フローでフィッシング耐性を強化する
- RDP トンネル内のリモート認証情報を使用して耐フィッシングを実施する (特に VDI の場合)

重要なタスクは、特定のプラットフォームでどのユーザーとペルソナが実施の準備ができているか、データを通して測定することです。 「被害の拡大を食い止める」ために、適用可能な状態にあるユーザー/デバイス ペアから適用を開始し、環境内でフィッシングに悪用可能な認証の発生を減らします。

次に、ユーザー/デバイス ペアに準備作業が必要な場合ついて、他のシナリオに進みます。 組織全体に耐フィッシング認証が実施されるまで、ユーザー/デバイス ペアのリストを通して作業を進めます。

Microsoft Entra ID グループのセットを作成して、実施を段階的にロールアウトします。 ウェーブ ベースのロールアウト アプローチを使用した場合は、前の手順のグループを再利用します。

#### 条件付きアクセス ポリシーの推奨される適用

特定の条件付きアクセス ポリシーで各グループを対象にします。 このアプローチは、ユーザー/デバイス ペアによって実施の管理策を段階的にロールアウトするのに役立ちます。

| Policy | ポリシーが対象とするグループ名 | ポリシー – デバイス プラットフォームの条件 | ポリシー – 許可の管理策 |
| --- | --- | --- | --- |
| 1 | フィッシング対策としてパスワードレスに対応しているWindowsユーザー | ウィンドウズ | 認証の強度を要求 - フィッシング耐性がある多要素認証 (MFA) |
| 2 | macOS の耐フィッシング機能に対応するパスワードレス認証対応ユーザー | macOS | 認証の強度を要求 - フィッシング耐性がある多要素認証 (MFA) |
| 3 | iOS の耐フィッシング パスワードレスに対応するユーザー | iOS | 認証の強度を要求 - フィッシング耐性がある多要素認証 (MFA) |
| 4 | Android の耐フィッシング パスワードレスに対応するユーザー | Android | 認証の強度を要求 - フィッシング耐性がある多要素認証 (MFA) |
| 5 | その他のパスワードを使わない耐フィッシング対応のユーザー | Windows、macOS、iOS、Android を除くすべて | 認証の強度を要求 - フィッシング耐性がある多要素認証 (MFA) |

それぞれのデバイスとオペレーティング システムについて準備ができているか、またはその種類のデバイスを持っていないかを判定して、各ユーザーを各グループに追加します。 ロールアウトの終わりには、すべてのユーザーがいずれかのグループに属している必要があります。

### パスワードレス ユーザーのリスクに対応する

Microsoft Entra ID 保護は、組織が ID ベースのリスクを検出、調査、修復するのために役立ちます。 Microsoft Entra ID 保護は、耐フィッシング パスワードレス認証情報の使用に切り替えた後でも、ユーザーにとって重要で有用な検出結果を提供します。 たとえば、耐フィッシング ユーザーに関連する検出結果には、次のようなものがあります。

- 匿名 IP アドレスからのアクティビティ
- ユーザーに対する管理者確認済みのセキュリティ侵害
- 異常なトークン
- 悪意のある IP アドレス
- Microsoft Entra の脅威インテリジェンス
- 疑わしいブラウザー
- 中間攻撃者
- プライマリ更新トークン (PRT) へのアクセス試行の可能性
- その他: [riskEventType にマッピングされたリスク検出数](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)

Microsoft Entra ID 保護ユーザーが耐フィッシング パスワードレス ユーザーを保護するために、次のアクションを実行することを、マイクロソフトはおすすめします。

1. Microsoft Entra ID 保護展開ガイダンスを確認する: [ID 保護の展開を計画する](https://learn.microsoft.com/ja-jp/entra/id-protection/how-to-deploy-identity-protection)
2. リスク ログを SIEM にエクスポートするように設定する
3. 中程度の**ユーザー**リスクを調査して対処する
4. リスクの高いユーザーをブロックするように条件付きアクセス ポリシーを構成する

Microsoft Entra ID 保護を展開した後は、[条件付きアクセス トークン保護](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-token-protection)の使用を検討してください。 ユーザーが耐フィッシング パスワードレス認証情報でサインインするにつれ、攻撃と検出が進化し続けます。 たとえば、ユーザーの認証情報を簡単にフィッシングできなくなった場合、攻撃者はユーザー デバイスからトークンを流出させる試みに移行する可能性があります。 トークン保護は、発行先のデバイスのハードウェアにトークンをバインドすることで、このリスクの軽減に役立ちます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-enable-authenticator-passkey"} -->
## Authenticator での Microsoft Entra ID のパスキーの有効化とサポート - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-enable-authenticator-passkey
- Service: entra-id / authentication
- Article date: 2026-07-05
- Summary: Microsoft Entra ID の Microsoft Authenticator におけるパスキーに関する、Authenticator 固有の要件、構成、トラブルシューティングについて説明します。

この記事では、Microsoft Entra ID の Microsoft Authenticator におけるパスキーに関する、Authenticator 固有の要件と構成について説明します。

この記事の手順に従う前に、パスキーを有効にし、パスキー プロファイルを作成します。 手順については、「[Microsoft Entra IDでパスキー (FIDO2) を有効にする」](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2)を参照してください。

### Authenticator におけるパスキーの前提条件

- 認証方法を構成するための少なくとも [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) アクセス許可を持つアカウント。
- Microsoft Entra 管理センターの**認証方法**にある**Passkey (FIDO2)**ポリシーで、[パスキーによるサインインを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2#enable-passkey-profiles)必要があります。
- Android 14 以降または iOS 17 以降。
- クロスデバイス登録と認証の場合:

    - 両方のデバイスでBluetoothとアクティブなインターネット接続が有効になっていることを確認します。 組織でBluetoothの使用が制限されている場合は、 [passkey 対応 FIDO2 認証子と排他的にペアリングするBluetoothを許可](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/passkeys/?tabs=windows%2Cintune#passkeys-in-bluetooth-restricted-environments) して、クロスデバイス パスキーのサインインと登録を許可できます。 組織では、デバイス間の登録と認証を有効にするために、次の表のエンドポイントへの接続を許可する必要があります。 これらの URL へのアクセスをデバイスに許可する必要があります。 Apple デバイスの要件の詳細については、「 [エンタープライズ ネットワークで Apple 製品を使用する](https://support.apple.com/101555)」を参照してください。

        | Platform | URL |
        | --- | --- |
        | Android | `cable.ua5v.com` |
        | iOS | `cable.auth.com``app-site-association.cdn-apple.com``app-site-association.networking.apple` |

    注

    構成証明を有効にすると、ユーザーはデバイス間登録を使用できません。

FIDO2 のサポートの詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-fido2-compatibility)を使用した FIDO2 認証のサポート」を参照してください。

注

条件付きアクセス ポリシーの一部で「**デバイスを準拠しているとマークすることを要求する**」コントロールが付与されている場合、Microsoft Authenticator アプリから UserAuthenticationMethod.Read スコープへのアクセスはブロックされません。 Authenticator は、ユーザーが構成できる資格情報を決定するために、Authenticator の登録中に UserAuthenticationMethod.Read スコープにアクセスする必要があります。 認証システムでは、資格情報を登録するために UserAuthenticationMethod.ReadWrite にアクセスする必要がありますが、**デバイスを準拠としてマークする必要があるチェック**をスキップすることはありません。

### Authenticator でパスキーのプロファイルを構成する

1. 少なくとも [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)として Microsoft Entra 管理センターにサインインします。
2. **[Entra ID]**&gt;**[Authentication メソッド]** に移動します。
3. **認証方法 |[ポリシー] ページで**、**パスキー (FIDO2)**&gt;**構成**を選択します。
4. [ **+ プロファイルの追加] を選択します**。

    [Image: パスキー プロファイルを追加する方法を示すスクリーンショット。]
5. 認証パスキーなど、プロファイルの**名前** **を入力します**。
6. **構成証明を強制する**かどうかを選択します。 詳細については、認証子の証明を参照してください。
7. **Passkey 型**の場合は、[**デバイス バインド]** を選択します。
8. **[ターゲット固有の AAGUIDS**] を選択し、[**動作**] を **[許可**] に設定します。
9. **+ AAGUID を追加**&gt;**Microsoft Authenticator**、**保存** の順に選択します。

    [Image: Authenticator のパスキーの [パスキー プロファイル設定の追加] を示したスクリーンショット。]

### Authenticator におけるパスキーのプロファイルに対してグループを有効化してターゲットにする

1. 少なくとも [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)として Microsoft Entra 管理センターにサインインします。
2. **[Entra ID]**&gt;**[Authentication メソッド]** に移動します。
3. **認証方法 | ポリシー** ページで、**パスキー (FIDO2)**&gt;**有効化して対象を指定** を選択します。
4. [ **有効化] タブと [ターゲット** ] タブで、[ **有効]** が **[オン]** になっていることを確認します。
5. [ **ターゲットの追加]** を選択し、[ **すべてのユーザー** ] または **[ターゲットの選択]** を選択して特定のグループを選択します。

    [Image: パスキー プロファイルのターゲットを追加する方法を示すスクリーンショット。]
6. Authenticator でパスキーのプロファイルを選択し、**保存**します。

    [Image: Authenticator でパスキーのプロファイルを有効にしてターゲットにする方法を示すスクリーンショット。]

    注

    ターゲット グループ (エンジニアリングなど) は、複数のパスキー プロファイルのスコープを設定できます。 ユーザーが複数のパスキー プロファイルのスコープに設定されている場合、パスキーがスコープ付きパスキー プロファイルの少なくとも 1 つの要件を完全に満たしている場合、パスキーを使用した登録と認証が許可されます。 チェックの順序は特にありません。 ユーザーが **Passkey (FIDO2)** ポリシーで除外されたグループのメンバーである場合、ユーザーはパスキー (FIDO2) の登録またはサインインから完全にブロックされます。 ブロックは、含まれるすべてのグループのメンバーシップよりも優先されます。

### 認証器の証明

Microsoft Entra 管理センターでパスキーを有効にし、パスキー プロファイルを作成する際に、構成証明を適用するかどうかを選択できます。 パスキー プロファイルを構成する一般的な手順については、「 [パスキーの有効化 (FIDO2)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2)」を参照してください。

構成証明が有効になっている場合、Microsoft Entra IDは作成されるパスキーの正当性を検証します。 ユーザーが Authenticator にパスキーを登録している場合、認証は、正規の Authenticator アプリが Apple および Google サービスを使用してパスキーを作成したことを確認します。

- **iOS**: Authenticator の証明では、[iOS アプリアテストサービス](https://developer.apple.com/documentation/devicecheck/preparing-to-use-the-app-attest-service) を使って、パスキーを登録する前に Authenticator アプリの正当性を確認します。
- **Android**:

    - Play Integrity 構成証明の場合、Authenticator 構成証明は、[Play Integrity API](https://developer.android.com/google/play/integrity/overview) を使用して Authenticator アプリの正当性を確認してから、パスキーを登録します。
    - キー構成証明の場合、Authenticator 構成証明は、[Android によるキー構成証明](https://developer.android.com/privacy-and-security/security-key-attestation)を使用して、登録対象のパスキーがハードウェアでサポートされていることを確認します。

注

iOS と Android のいずれでも、Authenticator 構成証明は Apple および Google のサービスを利用して、Authenticator アプリの信びょう性を検証します。 サービスの使用率が高いと、パスキーの登録が失敗する可能性があり、ユーザーはもう一度やり直す必要があります。 Apple および Google のサービスが停止している場合、Authenticator 構成証明は、サービスが復旧するまで構成証明を必要とする登録をブロックします。 Google Play Integrity サービスの状態を監視するには、「[Google Play ステータス ダッシュボード](https://status.play.google.com/)」を参照してください。 iOS App Attest サービスの状態を監視するには、「[システム ステータス](https://developer.apple.com/system-status/)」を参照してください。

ユーザーは保証されたパスキーのみを Authenticator アプリに直接登録できます。 クロスデバイス登録フローでは、認証済みパスキーの登録はサポートされていません。

### Authenticator の AAGUID

パスキー プロファイル内で Authenticator Attestation Globally Unique Identifier（AAGUID）を指定することで、ユーザーが Authenticator のパスキーのみを使用できるように制限できます。

必要に応じて、[ **+ AAGUID の追加]** を選択し、次の AAGUID を手動で追加することもできます。

- **Android 用 Authenticator**: `de1e552d-db1d-4423-a619-566b625cdc84`
- **iOS 用の Authenticator**: `90a3ccdf-635c-4729-a248-9b709135078f`

以前に許可した AAGUID を削除した場合、許可されたメソッドを以前に登録したユーザーは、サインインに使用できなくなります。

### Graph Explorer を使用して Authenticator でのパスキーを有効にする

Microsoft Entra 管理センターを使用するだけでなく、Graph エクスプローラーを使用して Authenticator でパスキーを有効にすることもできます。 少なくとも [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) ロールが割り当てられている場合は、認証システムの AAGUID を許可するように認証方法ポリシーを更新できます。

注

次の例では、テナント レベルの FIDO2 構成エンドポイントを使用します。 パスキー管理のプロファイル ベースのアプローチについては、「パス [キーの有効化 (FIDO2)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2)」を参照してください。

Graph Explorer を使用してポリシーを構成するには、次の手順を実行します。

1. [Graph Explorer](https://aka.ms/ge) にサインインして、**Policy.Read.All** および **Policy.ReadWrite.AuthenticationMethod** のアクセス許可に同意します。
2. 認証方法ポリシーを取得します:

    ```json
    GET https://graph.microsoft.com/v1.0/authenticationMethodsPolicy/authenticationMethodConfigurations/FIDO2
    ```
3. 認証の適用を有効にし、Authenticator の AAGUID のみを許可するようにキー制限を適用するには、次の要求本文を使用して `PATCH` 操作を実行します。

    ```json
    PATCH https://graph.microsoft.com/v1.0/authenticationMethodsPolicy/authenticationMethodConfigurations/FIDO2
    
    Request Body:
    {
        "@odata.type": "#microsoft.graph.fido2AuthenticationMethodConfiguration",
        "isAttestationEnforced": false,
        "keyRestrictions": {
            "isEnforced": true,
            "enforcementType": "allow",
            "aaGuids": [
                "90a3ccdf-635c-4729-a248-9b709135078f",
                "de1e552d-db1d-4423-a619-566b625cdc84"
    
                <insert previous AAGUIDs here to keep them stored in policy>
            ]
        }
    }
    ```
4. パスキー (FIDO2) ポリシーが正しく更新されていることを確認してください。

    ```json
    GET https://graph.microsoft.com/v1.0/authenticationMethodsPolicy/authenticationMethodConfigurations/FIDO2
    ```

### Authenticator で Bluetooth の使用をパスキーに制限する

一部の組織では、パスキーの使用を含む Bluetooth の使用を制限しています。 このような場合、組織がパスキー許可するには、パスキー対応の FIDO2 認証システムとの Bluetooth ペアリングのみを許可します。 Bluetooth の使用をパスキーのみにする構成方法について詳しくは、「[Bluetooth が制限された環境でのパスキー](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/passkeys/?tabs=windows%2Cintune#passkeys-in-bluetooth-restricted-environments)」を参照してください。

### Authenticator のパスキーの問題を解決する

このセクションでは、Authenticator でパスキーを使用するときにユーザーに表示される可能性がある問題と、管理者がそれらを解決するための考えられる方法について説明します。

#### Android プロファイルにパスキーを格納する

Android のパスキーは、保存されているプロファイルからのみ使用されます。 パスキーが Android Work プロファイルに格納されている場合は、そのプロファイルから使用されます。 パスキーが Android Personal プロファイルに格納されている場合は、そのプロファイルから使用されます。 ユーザーが必要なパスキーにアクセスして使用できるようにするには、Android Personal プロファイルと Android Work プロファイルの両方を持つユーザーが、各プロファイルの Authenticator にパスキーを作成する必要があります。

#### 認証強度の条件付きアクセス ポリシー ループの回避策

条件付きアクセス ポリシーで **、すべてのリソース (以前は "すべてのクラウド アプリ")** にアクセスするためにフィッシングに強い認証が必要な場合、Authenticator でパスキーを追加しようとすると、ユーザーはループに入ることができます。 例えば次が挙げられます。

- 条件: **すべてのデバイス (Windows、Linux、macOS、Windows、Android)**
- 対象リソース: **すべてのリソース (以前の "すべてのクラウド アプリ")**
- 許可制御: **認証強度 – Authenticator でパスキーが必要**

このポリシーにより、対象ユーザーはパスキーを使用して、Authenticator アプリを含むすべてのクラウド アプリケーションにサインインするように強制されます。 Android または iOS の Authenticator でパスキーを追加しようとすると、ユーザーはパスキーを使用する必要があります。

いくつかの回避策を次に示します。

- [アプリケーションをフィルター処理](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-filter-for-applications)し、ポリシー ターゲットを**すべてのリソース (以前の "すべてのクラウド アプリ")** から特定のアプリケーションに移行できます。 まず、テナントで使用されているアプリケーションのレビューから始めます。 フィルターを使用して Authenticator やその他のアプリケーションにタグを付ける。
- サポートコストをさらに削減するために、パスキーの使用を義務化する前に、ユーザーによるパスキーの導入を促進するための社内キャンペーンを実施できます。 パスキーの使用を適用する準備ができたら、次の 2 つの条件付きアクセス ポリシーを作成します。

    - モバイル オペレーティング システム (OS) バージョンのポリシー
    - デスクトップ OS バージョンのポリシー

    ポリシーごとに異なる認証強度を必要とし、次の表に示すその他のポリシー設定を構成します。 ユーザーに対して [一時アクセス パス (TAP)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass) を有効にするか、他の認証方法を有効にして、ユーザーがパスキーを登録できるようにします。

    TAP は、ユーザーがパスキーを登録できる時間を制限します。 これは、パスキー登録を許可するモバイル プラットフォームでのみ受け入れられます。

    | 条件付きアクセス ポリシー | デスクトップ OS | モバイル OS |
    | --- | --- | --- |
    | Name | デスクトップ OS にアクセスするには、Authenticator のパスキーが必要です。 | モバイル OS にアクセスするには、TAP、フィッシングに強い資格情報、またはその他の指定された認証方法が必要です。 |
    | 状態 | 特定のデバイス (デスクトップ オペレーティング システム)。 | 特定のデバイス (モバイル オペレーティング システム)。 |
    | Devices | N/A。 | Android、iOS。 |
    | デバイスを除外する | Android、iOS。 | N/A。 |
    | ターゲット リソース | すべてのリソース。 | すべてのリソース。 |
    | 制御を許可する | 認証の強度。 | 認証の強度。^1^ |
    | Methods | Authenticator のパスキー。 | TAP、Authenticator のパスキー。 |
    | ポリシーの結果 | Authenticator でパスキーを使用してサインインできないユーザーは、 **マイ サインイン** ウィザード モードに移動します。 登録後、モバイル デバイスで Authenticator にサインインするように求められます。 | TAP または別の許可されたメソッドを使用して Authenticator にサインインするユーザーは、Authenticator に直接パスキーを登録できます。 ユーザーが認証要件を満たしているため、ループは発生しません。 |

    ^1^ユーザーが新しいサインイン方法を登録するには、モバイル ポリシーの許可制御が条件付きアクセス ポリシーと一致して [セキュリティ情報](https://mysignins.microsoft.com/security-info)を登録する必要があります。

注

いずれの回避策でも、ユーザーは **Register セキュリティ情報** を対象とする条件付きアクセス ポリシーを満たす必要があります。または、パスキーを登録できません。 **すべてのリソース** ポリシーで他の条件を設定している場合は、パスキーの登録時にこれらの条件を満たす必要があります。

#### 「承認されたクライアント アプリが必要」または「アプリ保護ポリシーが必要」の条件付きアクセス許可制御により、パスキーを登録できないユーザー

ユーザーが次の条件付きアクセス ポリシーに含まれている場合、ユーザーは Authenticator にパスキーを登録できません。

- 条件: **すべてのデバイス (Windows、Linux、macOS、Windows、Android)**
- 対象リソース: **すべてのリソース (以前は "すべてのクラウド アプリ")**
- 許可制御: **承認されたクライアント アプリを要求するか、アプリ** **保護ポリシーを要求**する

このポリシーは、Microsoft Intuneアプリ[保護ポリシー](https://learn.microsoft.com/ja-jp/mem/intune/apps/app-protection-policy)をサポートするアプリを使用して、ユーザーにすべてのクラウド アプリケーションへのサインインを強制します。 Authenticator は、Android または iOS ではこのポリシーをサポートしていません。

いくつかの回避策を次に示します。

- [アプリケーションをフィルター処理](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-filter-for-applications)し、ポリシー ターゲットを**すべてのリソース (旧称 "すべてのクラウド アプリ")** から特定のアプリケーションに移行できます。 まず、テナントで使用されているアプリケーションのレビューから始めます。 フィルターを使用して適切なアプリケーションにタグを付ける。
- モバイル デバイス管理 (MDM) と[ **デバイスを準拠コントロールとしてマークする必要がある** ] を使用できます。 MDM がデバイスを完全に管理し、準拠している場合、Authenticator はこの許可制御を満たすことができます。 例えば次が挙げられます。

    - 条件: **すべてのデバイス (Windows、Linux、macOS、Windows、Android)**
    - 対象リソース: **すべてのリソース (以前の "すべてのクラウド アプリ")**
    - 許可制御: **承認されたクライアント アプリを要求**するか、 **アプリ保護ポリシーを要求**するか、 **デバイスを準拠としてマークする必要があります**
- 条件付きアクセス ポリシーの一時的な除外をユーザーに付与できます。 1 つ以上の補正コントロールを使用することを検討してください。

    - 一定期間だけ除外を許可します。 パスキーの登録が許可されたら、ユーザーに通信します。 期間の経過後に除外を削除します。 その後、時間を逃した場合は、ヘルプ デスクに電話するようユーザーに指示します。
    - 別の条件付きアクセス ポリシーを使用して、ユーザーが特定のネットワークの場所または準拠しているデバイスからのみ登録するように要求します。

注

提案された回避策では、ユーザーは **Register セキュリティ情報** を対象とする条件付きアクセス ポリシーを満たす必要があります。また、パスキーを登録できません。 他の条件が **[すべてのリソース** ] ポリシーで設定されている場合は、ユーザーがパスキーを登録する前に、それらの条件も満たす必要があります。

### Authenticator にパスキーを登録する

管理者が Authenticator でパスキーを有効にすると、ユーザーは iOS または Android デバイス上のアプリにパスキーを登録できます。

登録手順については、「[Microsoft Authenticatorにパスキーを登録](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-register-passkey-authenticator)する」を参照してください。

### Authenticator でパスキーを使用してサインインする

登録後、ユーザーはデバイスの Authenticator のパスキーを使用してMicrosoft Entra IDにサインインできます。

サインイン手順については、「 [Authenticator でのパスキーを使用したサインイン](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-sign-in-passkey-authenticator)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-mandatory-multifactor-authentication"} -->
## Microsoft Entra ユーザーの必須 MFA セットアップを確認する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mandatory-multifactor-authentication
- Service: entra-id / authentication
- Article date: 2026-03-12
- Summary: ユーザーがAzureポータルと管理ポータルの必須 MFA 要件を満たしていることを確認します。 Microsoft Entra ID P1、P2、および Free ライセンスの MFA を確認して有効にします。

このトピックでは、組織内のユーザーがAzureの必須 MFA 要件を満たすように設定されていることを確認する手順について説明します。 影響を受けるアプリケーションとアカウントとロールアウトのしくみの詳細については、「[Azureおよびその他の管理ポータルの必須多要素認証の計画](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mandatory-multifactor-authentication)を参照してください。

### 個人用アカウントの MFA を確認する

ユーザーは、個人アカウントを使用して、少数のユーザーに対してのみMicrosoft Entraテナントを作成できます。 個人アカウントを使用してAzureをサブスクライブした場合は、次の手順を実行して、アカウントが MFA 用に設定されていることを確認します。

1. https://account.microsoft.com/security の [Microsoft アカウント セキュリティ] タブにサインイン>。
2. [ **サインイン方法の管理** ] を選択して、自分が誰であるかを証明する方法を示します。
3. **[追加のセキュリティ]** と **[2 段階認証]** で、**[有効にする]** を選択します。
4. 画面に表示される指示に従います。

詳細については、「 Microsoft アカウントを参照してください。

### MFA でサインインするユーザーとサインインしないユーザーを検出する

MFA でサインインするユーザーとサインインしないユーザーを検出するには、次のリソースを使用します。

- ユーザーと認証方法のリストをエクスポートするには、[PowerShell](https://aka.ms/AzMFA) を使用します。
- クエリを実行してユーザーのログインを分析する場合、[MFA を要求するアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mandatory-multifactor-authentication#application-ids-and-urls) のアプリケーション ID を使用します。

### MFA の有効化を確認する

[MFA を必要とするアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mandatory-multifactor-authentication#application-ids-and-urls)にアクセスするすべてのユーザーは、MFA を使用するように設定する必要があります。 必須の MFA は、特権ロールに制限されません。

これらのユーザーに対して MFA が設定されていることを確認するか、必要に応じて有効にするには、次の手順を使用します。

1. グローバル閲覧者としてAzureポータルにサインインします。
2. **Entra ID**&gt;**Overview** に移動します。
3. テナント サブスクリプションのライセンスの種類を確認します。
4. MFA が有効であることを確認し、必要に応じて MFA を有効にするには、ライセンスの種類に応じた手順に従います。 これらの手順を完了するには、グローバル閲覧者としてサインアウトし、より高い特権のロールでもう一度サインインします。

    - Microsoft Entra ID P1 または Microsoft Entra ID P2
    - Microsoft 365 または Microsoft Entra ID Free

#### MICROSOFT ENTRA ID P1 または Microsoft Entra ID P2 ライセンスに対して MFA が有効になっていることを確認する

Microsoft Entra ID P1 または Microsoft Entra ID P2 ライセンスがある場合は、MFA を必要とする[アプリケーションにアクセスするユーザーに MFA を要求する条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mandatory-multifactor-authentication#application-ids-and-urls)を作成できます。

1. 少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)として[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Conditional Access**&gt;**Policies** に移動します。
3. **[新しいポリシー]** を選択します。
4. ポリシーに名前を設定してください。 ポリシーの名前に対する意味のある標準を組織で作成することをお勧めします。
5. **[割り当て]** で、 **[ユーザーまたはワークロード ID]** を選択します。
6. **[含む]** の下で、**[すべてのユーザー]** を選択するか、[MFA を要求するアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mandatory-multifactor-authentication#application-ids-and-urls)にサインインするユーザーのグループを選択します。
7. **Target resources**&gt;**Cloud apps**&gt;**含める**、**アプリを選択**、**Microsoft 管理ポータル** および **Windows Azure Service Management API** を選択します。
8. **[アクセス制御]**&gt;**[許可]**の下で、**[アクセス権の付与]**、**[認証強度が必要]**、**[多要素認証]** の順に選択して、**[選択]** を選択します。
9. 設定を確認し、**[ポリシーの有効化]** を **[レポート専用]** に設定します。
10. **[作成]** を選択して、ポリシーを作成および有効化します。

    重要

    Report-Only モードで CA ポリシーを作成し、ロックアウトされないようにテナントへの影響を把握します。

詳細については、「[一般的な条件付きアクセス ポリシー: Microsoft 管理ポータルにアクセスする管理者に多要素認証を要求する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/how-to-policy-mfa-admin-portals)を参照してください。

サインイン ログを含む条件付きアクセスの分析情報とレポート ブックを使用して、テナントへの影響を把握できます。 前提条件として、次の手順を実行する必要があります。

- [ワークスペースを作成します](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/quick-create-workspace)。
- [Azure Monitor](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)を使用してアクティビティ ログを統合します。

必須 MFA がテナントに与える影響を確認するには、次のパラメーターを選択します。

- 以前に作成した MFA 条件付きアクセス ポリシーを選択する
- 時間範囲を選択してデータを評価する
- **[データ ビュー]** を選択すると、ユーザー数またはサインイン数に関する結果が表示されます

サインインの詳細を照会したり、サインイン ログをダウンロードしてデータをさらに詳しく調べることもできます。 詳細については、「[条件付きアクセスに関する分析情報とレポート](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-insights-reporting)」をご覧ください。

#### mfa が Microsoft 365 または Microsoft Entra ID Free に対して有効になっていることを確認する

Microsoft 365または無料ライセンスをMicrosoft Entra IDしている場合は、セキュリティの既定値を使用して MFA を有効にすることができます。 ユーザーは必要に応じて MFA の入力を求められますが、独自のルールを定義して動作を制御することはできません。

次のようにして、セキュリティの既定値群を有効にします。

1. 少なくとも「Security Administrator」として、[Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **Entra ID**&gt;**Overview**&gt;**Properties** に移動します。
3. **[セキュリティの既定値群の管理]** を選択します。
4. **[セキュリティの既定値群]** を **[有効]** に設定します。
5. **[保存]** を選択します。

セキュリティの既定値の詳細については、「Microsoft Entra IDを参照してください。

セキュリティの既定値を使用しない場合は、ユーザーごとの MFA を有効にすることができます。 ユーザーを個別に有効にすると、ログインするたびに MFA を実行します。 認証管理者は、いくつかの例外を有効にすることができます。 ユーザーごとの MFA を有効にするには、次の手順を使用します。

1. 少なくとも [Microsoft Entra 管理センター](https://entra.microsoft.com)[Authentication Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. ユーザー アカウントを選択し、**[MFA の有効化]** をクリックします。
4. 開いたポップアップ ウィンドウで選択内容を確認します。

ユーザーを有効にした後は、ユーザーにメールで通知します。 次回のサインイン時に登録を要求するプロンプトが表示されることをユーザーに伝えます。 詳細については、「[サインイン イベントをセキュリティで保護するためのユーザーごとのMicrosoft Entra多要素認証を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-userstates)」を参照>。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-mfa-additional-context"} -->
## Authenticator 通知で追加のコンテキストを使用する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-additional-context
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: 多要素認証 (MFA) 通知で追加のコンテキストを使用する方法について説明します。

この記事では、Authenticator のパスワードレス通知とプッシュ通知にサインインのアプリケーション名と地理的な場所を追加することで、ユーザー サインインのセキュリティを強化する方法について説明します。

### 前提条件

- 組織では、新しい認証方法ポリシーを使用して、一部のユーザーまたはグループに対して Authenticator のパスワードレス通知とプッシュ通知を有効にする必要があります。 認証方法ポリシーは、Microsoft Entra 管理センターまたは Microsoft Graph API を使用して編集できます。
- 追加のコンテキストは 1 つのグループのみを対象にすることができ、動的または入れ子の場合があります。 グループは、オンプレミスまたはクラウドのみから同期できます。

### パスワードレス電話によるサインインと多要素認証

ユーザーが Authenticator でパスワードなしの電話によるサインインまたは多要素認証 (MFA) プッシュ通知を受け取ると、承認を要求するアプリケーションの名前と、サインインの発生元の IP アドレスに基づく場所が表示されます。

[Image: MFA プッシュ通知の追加コンテキストを示すスクリーンショット。]

管理者は、追加のコンテキストと [番号照合](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-number-match) を組み合わせて、サインインのセキュリティをさらに向上させることができます。

[Image: MFA プッシュ通知の数値の一致を含む追加コンテキストを示すスクリーンショット。]

#### ポリシー スキーマの変更

アプリケーション名と地理的な場所を個別に有効または無効にすることができます。 `featureSettings`では、各機能に対して次の名前マッピングを使用できます。

- **アプリケーション名の**: `displayAppInformationRequiredState`
- **地理的な場所**: `displayLocationInformationRequiredState`

Note

Microsoft Graph API の新しいポリシー スキーマを使用していることを確認します。 Graph エクスプローラーでは、`Policy.Read.All` と `Policy.ReadWrite.AuthenticationMethod` のアクセス許可に同意する必要があります。

機能ごとに 1 つのターゲット グループを特定します。 次に、次の API エンドポイントを使用して、`displayAppInformationRequiredState` の下の `displayLocationInformationRequiredState properties` または `featureSettings` を変更して、必要なグループを `enabled` したり除外したりします。

```msgraph
GET https://graph.microsoft.com/v1.0/authenticationMethodsPolicy/authenticationMethodConfigurations/MicrosoftAuthenticator
```

詳細については、「[microsoftAuthenticatorAuthenticationMethodConfiguration リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/microsoftAuthenticatorAuthenticationMethodConfiguration)」を参照してください。

##### すべてのユーザーに対して追加のコンテキストを有効にする方法の例

`featureSettings`で、`displayAppInformationRequiredState` と `displayLocationInformationRequiredState` を `default` から `enabled`に変更します。

認証モードの値は、パスワードなしの電話によるサインインを有効にするかどうかに応じて、`any` または `push`です。 これらの例では、`any`を使用しますが、パスワードレスを許可しない場合は、`push`を使用します。

以前の構成を上書きしないように、スキーマ全体を `PATCH` する必要がある場合があります。 その場合は、最初に `GET` を実行します。 それから、関連するフィールドのみを更新してから、`PATCH`を実行します。 次の例では、`displayAppInformationRequiredState`で `displayLocationInformationRequiredState` と `featureSettings` を更新する方法を示します。

アプリケーション名または地理的な場所が表示されるのは、`includeTargets` の Authenticator が有効になっているユーザーだけです。 Authenticator が有効になっていないユーザーには、これらの機能が表示されません。

```json
//Retrieve your existing policy via a GET. 
//Leverage the Response body to create the Request body section. Then update the Request body similar to the Request body as shown below.
//Change the Query to PATCH and Run query
 
{
    "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#authenticationMethodConfigurations/$entity",
    "@odata.type": "#microsoft.graph.microsoftAuthenticatorAuthenticationMethodConfiguration",
    "id": "MicrosoftAuthenticator",
    "state": "enabled",
    "featureSettings": {
        "displayAppInformationRequiredState": {
            "state": "enabled",
            "includeTarget": {
                "targetType": "group",
                "id": "all_users"
            },
            "excludeTarget": {
                "targetType": "group",
                "id": "00000000-0000-0000-0000-000000000000"
            }
        },
        "displayLocationInformationRequiredState": {
            "state": "enabled",
            "includeTarget": {
                "targetType": "group",
                "id": "all_users"
            },
            "excludeTarget": {
                "targetType": "group",
                "id": "00000000-0000-0000-0000-000000000000"
            }
        }
    },
    "includeTargets@odata.context": "https://graph.microsoft.com/v1.0/$metadata#authenticationMethodsPolicy/authenticationMethodConfigurations('MicrosoftAuthenticator')/microsoft.graph.microsoftAuthenticatorAuthenticationMethodConfiguration/includeTargets",
    "includeTargets": [
        {
            "targetType": "group",
            "id": "all_users",
            "isRegistrationRequired": false,
            "authenticationMode": "any",
        }
    ]
} 
```

##### アプリケーション名と地理的な場所を別のグループに対して有効にする方法の例

`featureSettings`で、`displayAppInformationRequiredState` と `displayLocationInformationRequiredState` を `default` から `enabled`に変更します。 各 `includeTarget`の `featureSetting` 内で、ID を `all_users` から Microsoft Entra 管理センターからグループのオブジェクト ID に変更します。

以前の構成を上書きしないように、スキーマ全体を `PATCH` する必要があります。 まずは `GET` を行うことをお勧めします。 それから、関連するフィールドのみを更新してから、`PATCH`を実行します。 次の例は、`displayAppInformationRequiredState`で `displayLocationInformationRequiredState` と `featureSettings` の更新を示しています。

アプリケーション名または地理的な場所が表示されるのは、`includeTargets` の Authenticator が有効になっているユーザーだけです。 Authenticator が有効になっていないユーザーには、これらの機能が表示されません。

```json
{
    "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#authenticationMethodConfigurations/$entity",
    "@odata.type": "#microsoft.graph.microsoftAuthenticatorAuthenticationMethodConfiguration",
    "id": "MicrosoftAuthenticator",
    "state": "enabled",
    "featureSettings": {
        "displayAppInformationRequiredState": {
            "state": "enabled",
            "includeTarget": {
                "targetType": "group",
                "id": "44561710-f0cb-4ac9-ab9c-e6c394370823"
            },
            "excludeTarget": {
                "targetType": "group",
                "id": "00000000-0000-0000-0000-000000000000"
            }
        },
        "displayLocationInformationRequiredState": {
            "state": "enabled",
            "includeTarget": {
                "targetType": "group",
                "id": "a229e768-961a-4401-aadb-11d836885c11"
            },
            "excludeTarget": {
                "targetType": "group",
                "id": "00000000-0000-0000-0000-000000000000"
            }
        }
    },
    "includeTargets@odata.context": "https://graph.microsoft.com/v1.0/$metadata#authenticationMethodsPolicy/authenticationMethodConfigurations('MicrosoftAuthenticator')/microsoft.graph.microsoftAuthenticatorAuthenticationMethodConfiguration/includeTargets",
    "includeTargets": [
        {
            "targetType": "group",
            "id": "all_users",
            "isRegistrationRequired": false,
            "authenticationMode": "any",
        }
    ]
}
```

確認するには、`GET` をもう一度実行し、オブジェクト ID を確認します。

```msgraph
GET https://graph.microsoft.com/v1.0/authenticationMethodsPolicy/authenticationMethodConfigurations/MicrosoftAuthenticator
```

##### アプリケーション名を無効にし、地理的な場所のみを有効にする方法の例

`featureSettings`で、`displayAppInformationRequiredState` の状態を `default` または `disabled` に変更し、`displayLocationInformationRequiredState` を `enabled`に変更します。 各 `includeTarget` の値について、ID を `featureSetting` 内で `all_users` から Microsoft Entra 管理センターにあるグループのオブジェクト ID に変更します。

以前の構成を上書きしないように、スキーマ全体を `PATCH` する必要があります。 まずは `GET` を行うことをお勧めします。 それから、関連するフィールドのみを更新してから、`PATCH`を実行します。 次の例は、`displayAppInformationRequiredState`で `displayLocationInformationRequiredState` と `featureSettings` の更新を示しています。

アプリケーション名または地理的な場所が表示されるのは、`includeTargets` の Authenticator が有効になっているユーザーだけです。 Authenticator が有効になっていないユーザーには、これらの機能が表示されません。

```json
{
    "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#authenticationMethodConfigurations/$entity",
    "@odata.type": "#microsoft.graph.microsoftAuthenticatorAuthenticationMethodConfiguration",
    "id": "MicrosoftAuthenticator",
    "state": "enabled",
    "featureSettings": {
        "displayAppInformationRequiredState": {
            "state": "disabled",
            "includeTarget": {
                "targetType": "group",
                "id": "44561710-f0cb-4ac9-ab9c-e6c394370823"
            },
            "excludeTarget": {
                "targetType": "group",
                "id": "00000000-0000-0000-0000-000000000000"
            }
        },
        "displayLocationInformationRequiredState": {
            "state": "enabled",
            "includeTarget": {
                "targetType": "group",
                "id": "a229e768-961a-4401-aadb-11d836885c11"
            },
            "excludeTarget": {
                "targetType": "group",
                "id": "00000000-0000-0000-0000-000000000000"
            }
        }
    },
    "includeTargets@odata.context": "https://graph.microsoft.com/v1.0/$metadata#authenticationMethodsPolicy/authenticationMethodConfigurations('MicrosoftAuthenticator')/microsoft.graph.microsoftAuthenticatorAuthenticationMethodConfiguration/includeTargets",
    "includeTargets": [
        {
            "targetType": "group",
            "id": "all_users",
            "isRegistrationRequired": false,
            "authenticationMode": "any",
        }
    ]
}
```

##### アプリケーション名と地理的な場所からグループを除外する方法の例

さらに、各機能について、`excludeTarget` の ID を Microsoft Entra 管理センターからグループのオブジェクト ID に変更します。 この変更により、そのグループにアプリケーション名または地理的な場所が表示されなくなります。

以前の構成を上書きしないように、スキーマ全体を `PATCH` する必要があります。 まずは `GET` を行うことをお勧めします。 それから、関連するフィールドのみを更新してから、`PATCH`を実行します。 次の例は、`displayAppInformationRequiredState`で `displayLocationInformationRequiredState` と `featureSettings` の更新を示しています。

アプリケーション名または地理的な場所が表示されるのは、`includeTargets` の Authenticator が有効になっているユーザーだけです。 Authenticator が有効になっていないユーザーには、これらの機能が表示されません。

```json
{
    "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#authenticationMethodConfigurations/$entity",
    "@odata.type": "#microsoft.graph.microsoftAuthenticatorAuthenticationMethodConfiguration",
    "id": "MicrosoftAuthenticator",
    "state": "enabled",
    "featureSettings": {
        "displayAppInformationRequiredState": {
            "state": "enabled",
            "includeTarget": {
                "targetType": "group",
                "id": "44561710-f0cb-4ac9-ab9c-e6c394370823"
            },
            "excludeTarget": {
                "targetType": "group",
                "id": "5af8a0da-5420-4d69-bf3c-8b129f3449ce"
            }
        },
        "displayLocationInformationRequiredState": {
            "state": "enabled",
            "includeTarget": {
                "targetType": "group",
                "id": "a229e768-961a-4401-aadb-11d836885c11"
            },
            "excludeTarget": {
                "targetType": "group",
                "id": "b6bab067-5f28-4dac-ab30-7169311d69e8"
            }
        }
    },
    "includeTargets@odata.context": "https://graph.microsoft.com/v1.0/$metadata#authenticationMethodsPolicy/authenticationMethodConfigurations('MicrosoftAuthenticator')/microsoft.graph.microsoftAuthenticatorAuthenticationMethodConfiguration/includeTargets",
    "includeTargets": [
        {
            "targetType": "group",
            "id": "all_users",
            "isRegistrationRequired": false,
            "authenticationMode": "any",
        }
    ]
}
```

##### 除外されたグループを削除する例

`featureSettings`で、`displayAppInformationRequiredState` の状態を `default` から `enabled`に変更します。 `excludeTarget` の ID を `00000000-0000-0000-0000-000000000000`に変更します。

以前の構成を上書きしないように、スキーマ全体を `PATCH` する必要があります。 まずは `GET` を行うことをお勧めします。 それから、関連するフィールドのみを更新してから、`PATCH`を実行します。 次の例は、`displayAppInformationRequiredState`で `displayLocationInformationRequiredState` と `featureSettings` の更新を示しています。

アプリケーション名または地理的な場所が表示されるのは、`includeTargets` の Authenticator が有効になっているユーザーだけです。 Authenticator が有効になっていないユーザーには、これらの機能が表示されません。

```json
{
    "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#authenticationMethodConfigurations/$entity",
    "@odata.type": "#microsoft.graph.microsoftAuthenticatorAuthenticationMethodConfiguration",
    "id": "MicrosoftAuthenticator",
    "state": "enabled",
    "featureSettings": {
        " displayAppInformationRequiredState ": {
            "state": "enabled",
            "includeTarget": {
                "targetType": "group",
                "id": "1ca44590-e896-4dbe-98ed-b140b1e7a53a"
            },
            "excludeTarget": {
                "targetType": "group",
                "id": " 00000000-0000-0000-0000-000000000000"
            }
        }
    },
    "includeTargets@odata.context": "https://graph.microsoft.com/v1.0/$metadata#authenticationMethodsPolicy/authenticationMethodConfigurations('MicrosoftAuthenticator')/microsoft.graph.microsoftAuthenticatorAuthenticationMethodConfiguration/includeTargets",
    "includeTargets": [
        {
            "targetType": "group",
            "id": "all_users",
            "isRegistrationRequired": false,
            "authenticationMode": "any"
        }
    ]
}
```

#### 追加のコンテキストをオフにする

追加のコンテキストをオフにするには、`PATCH`、`displayAppInformationRequiredState` を行い、`displayLocationInformationRequiredState` から `enabled``disabled`/へ `default` する必要があります。 また、いずれかの機能のみをオフにすることもできます。

```json
{
    "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#authenticationMethodConfigurations/$entity",
    "@odata.type": "#microsoft.graph.microsoftAuthenticatorAuthenticationMethodConfiguration",
    "id": "MicrosoftAuthenticator",
    "state": "enabled",
    "featureSettings": {
        "displayAppInformationRequiredState": {
            "state": "disabled",
            "includeTarget": {
                "targetType": "group",
                "id": "44561710-f0cb-4ac9-ab9c-e6c394370823"
            },
            "excludeTarget": {
                "targetType": "group",
                "id": "00000000-0000-0000-0000-000000000000"
            }
        },
        "displayLocationInformationRequiredState": {
            "state": "disabled",
            "includeTarget": {
                "targetType": "group",
                "id": "a229e768-961a-4401-aadb-11d836885c11"
            },
            "excludeTarget": {
                "targetType": "group",
                "id": "00000000-0000-0000-0000-000000000000"
            }
        }
    },
    "includeTargets@odata.context": "https://graph.microsoft.com/v1.0/$metadata#authenticationMethodsPolicy/authenticationMethodConfigurations('MicrosoftAuthenticator')/microsoft.graph.microsoftAuthenticatorAuthenticationMethodConfiguration/includeTargets",
    "includeTargets": [
        {
            "targetType": "group",
            "id": "all_users",
            "isRegistrationRequired": false,
            "authenticationMode": "any",
        }
    ]
}
```

### Microsoft Entra 管理センターで追加のコンテキストを有効にする

Microsoft Entra 管理センターでアプリケーション名または地理的な場所を有効にするには、次の手順に従います。

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **Entra ID**&gt;**Authentication メソッド**&gt;**Microsoft Authenticator** を参照します。
3. [**基本**] タブで、[**はい**] と [**すべてのユーザー**] を選択して、ポリシーをすべてのユーザーに対して有効にします。 **認証モード** を **任意の**に変更します。

    ここで Authenticator が有効になっているユーザーのみが、サインインのアプリケーション名または地理的な場所を表示するポリシーに含まれるか、そこから除外されます。 Authenticator が有効になっていないユーザーには、アプリケーション名または地理的な場所が表示されません。

    [Image: 任意の認証モードで Authenticator 設定を有効にする方法を示すスクリーンショット。]
4. **[構成]** タブの **[プッシュおよびパスワードレス通知にアプリケーション名を表示する]** で、**[状態]** を **[有効]** に変更します。 ポリシーに含めるまたは除外する対象者を選び、**保存**を選択します。

    [Image: アプリケーション名を有効にする方法を示すスクリーンショット。]

    次に、**[Show geographic location in push and passwordless notifications] (プッシュおよびパスワードレス通知に地理的な場所を表示する)** についても同じ操作を行います。

    [Image: 地理的な場所を有効にする方法を示すスクリーンショット。]

    アプリケーション名と地理的な場所は個別に構成できます。 たとえば、次のポリシーでは、すべてのユーザーに対してアプリケーション名と地理的な場所が有効になりますが、Operations グループには地理的な場所が表示されなくなります。

    [Image: アプリケーション名と地理的な場所を個別に有効にする方法を示すスクリーンショット。]

### 既知の問題

- ネットワーク ポリシー サーバー (NPS) または Active Directory フェデレーション サービスでは、追加のコンテキストはサポートされていません。
- ユーザーは、iOS および Android デバイスによって報告される場所を変更できます。 その結果、Authenticator は、Location-Based アクセス制御 (LBAC) 条件付きアクセス ポリシーのセキュリティ ベースラインを更新しています。 認証子は、ユーザーが Authenticator がインストールされているモバイル デバイスの実際の GPS 位置とは異なる場所を使用している可能性がある認証を拒否します。

    Authenticator の 2023 年 11 月リリースでは、デバイスの場所を変更するユーザーは、LBAC 認証を行うときに Authenticator に拒否メッセージが表示されます。 2024 年 1 月以降、古い Authenticator バージョンを実行するすべてのユーザーは、場所が変更された LBAC 認証からブロックされます。

    - Android の Authenticator バージョン 6.2309.6329 以前
    - iOS の Authenticator バージョン 6.7.16 以前

    以前のバージョンの Authenticator を実行しているユーザーを見つけるには、[Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/resources/microsoftauthenticatorauthenticationmethod#properties) を使用します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-mfa-authenticator-lite"} -->
## Outlook モバイルの Authenticator Lite を有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-authenticator-lite
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: ユーザーが自分の ID を検証できるように、Authenticator Lite for Outlook Mobile を設定する方法について説明します。

Authenticator Lite は、Microsoft Entra ユーザーが Android または iOS デバイスでプッシュ通知または時間ベースのワンタイム パスコード (TOTP) を使用して多要素認証 (MFA) を完了するためのもう 1 つの面です。 Authenticator Lite を使用すると、ユーザーは使い慣れたアプリの利便性から MFA 要件を満たすことができます。 Authenticator Lite は現在 [、Outlook モバイル](https://www.microsoft.com/microsoft-365/outlook-mobile-for-android-and-ios)で有効になっています。

ユーザーは Outlook Mobile でサインインを承認または拒否する通知を受け取るか、サインイン時に使用する TOTP をコピーできます。

注

通信トランスポートを使用して認証する場合は、次の重要なセキュリティ強化機能を使用します。

- この機能の Microsoft が管理する値は、認証方法ポリシーで有効になっています。 この機能を有効にしない場合は、状態を **既定** から **無効**に移動するか、ユーザーのグループのみにスコープを設定します。
- Authenticator Lite は、ユーザーごとの MFA ポリシーのモバイル アプリ検証オプションによる通知の一部として有効になります。 この機能を有効にしない場合は、この記事の手順に従って認証方法ポリシーで無効にすることができます。

### 前提条件

- 組織では、すべてのユーザーに対して Authenticator (第 2 要素) プッシュ通知を有効にするか、グループを選択する必要があります。 先進 [認証方法ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-methods-manage#authentication-methods-policy)を使用して Authenticator を有効にすることをお勧めします。 Microsoft Entra 管理センターまたは Microsoft Graph API を使用して、認証方法ポリシーを編集できます。 Authenticator Lite は、オンプレミスのユーザー アカウントまたはアクティブな MFA サーバーを備えた組織には適していません。

    ヒント

    Authenticator Lite を有効にする場合 [は、システム優先認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-system-preferred-authentication) も有効にすることをお勧めします。 システム優先認証を有効にすると、ユーザーは SMS や音声通話などの安全性の低いテレフォニー方式を試す前に、Authenticator Lite でサインインしようとします。
- 組織が Active Directory フェデレーション サービス (AD FS) アダプターまたはネットワーク ポリシー サーバー (NPS) 拡張機能を使用している場合は、一貫性のあるエクスペリエンスになるように最新バージョンにアップグレードしてください。
- Outlook モバイルで共有デバイス モードが有効になっているユーザーは、Authenticator Lite の対象になりません。
- ユーザーは、Outlook モバイルの最小バージョンを実行する必要があります。

    | オペレーティング システム | Outlook のバージョン |
    | --- | --- |
    | Android | 4.2310.1 |
    | iOS | 4.2312.1 |

### Authenticator Lite を有効にする

既定では、Authenticator Lite は [認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-default-enablement#microsoft-managed-settings) 方法ポリシーで Microsoft によって管理されます。 6 月 26 日に、この機能の Microsoft が管理する値が `disabled` から `enabled`に変更されました。 Authenticator Lite は、ユーザーごとの MFA ポリシーの **モバイル アプリ検証オプションによる通知** の一部としても含まれます。

#### Microsoft Entra 管理センターで Authenticator Lite を無効にする

Microsoft Entra 管理センターで Authenticator Lite を無効にするには、次の手順に従います。

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **Entra ID**&gt;**Authentication メソッド**&gt;**Microsoft Authenticator** を参照します。
3. [ **有効化とターゲット** ] タブで、[ **有効にする** ] と [ **すべてのユーザー** ] を選択してすべてのユーザーに対して Authenticator ポリシーを有効にするか、選択グループを追加します。 これらのユーザーまたはグループの認証モードを **[任意** ] または [プッシュ] に設定 **します**。

    Authenticator が有効になっていないユーザーには、この機能が表示されません。 Outlook がダウンロードされているのと同じデバイスで Authenticator をダウンロードしたユーザーは、Outlook で Authenticator Lite に登録するように求められません。 デバイスで個人プロファイルと仕事用プロファイルを使用する Android ユーザーは、Authenticator が Outlook アプリケーションとは異なるプロファイルに存在する場合に登録を求められる場合があります。
[Image: Microsoft Entra 管理センターの Authenticator の設定]
4. コンパニオン **アプリケーションの Microsoft Authenticator** の [**構成**] タブで、[**状態]** を **[無効]** に変更し、[保存] を選択**します**。
[Image: Authenticator Lite の構成設定]

組織がユーザーごとの MFA ポリシーで認証方法を引き続き管理している場合は、前の手順に加えて、モバイル **アプリによる通知** を検証オプションとして無効にする必要があります。 この手順は、認証方法ポリシーで Authenticator を有効にした後にのみ実行することをお勧めします。

Authenticator は最新の認証方法ポリシーで管理されている間、ユーザーごとの MFA ポリシーで認証方法の残りの部分を引き続き管理できます。 ただし、すべての認証方法の 管理を最新の認証方法ポリシーに移行 することをお勧めします。 ユーザーごとの MFA ポリシーの認証方法を管理する機能は、2025 年 9 月 30 日に廃止されます。

#### Graph API を使用して Authenticator Lite を有効にする

| プロパティ | タイプ | 説明 |
| --- | --- | --- |
| `excludeTarget` | `featureTarget` | この機能から除外される 1 つのエンティティ。 Authenticator Lite から除外できるグループは 1 つだけです。動的グループまたは入れ子になったグループを指定できます。 |
| `includeTarget` | `featureTarget` | この機能に含まれる 1 つのエンティティ。 Authenticator Lite には、動的グループまたは入れ子になったグループを含めることができるグループを 1 つだけ含めることができます。 |
| `State` | `advancedConfigState` | 使用可能な値:**有効にすると** 、選択したグループの機能が明示的に有効になります。**無効にすると** 、選択したグループの機能が明示的に無効になります。**既定** では、Microsoft Entra ID は、選択したグループに対して機能が有効になっているかどうかを管理できます。 |

単一のターゲット グループを特定した後、次の API エンドポイントを使用して、`CompanionAppsAllowedState`の `featureSettings` プロパティを変更します。

```http
https://graph.microsoft.com/beta/authenticationMethodsPolicy/authenticationMethodConfigurations/MicrosoftAuthenticator
```

Graph エクスプローラーでは、`Policy.ReadWrite.AuthenticationMethod` アクセス許可に同意する必要があります。

#### 要求

```JSON
//Retrieve your existing policy via a GET. 
//Leverage the Response body to create the Request body section. Then update the Request body similar to the Request body as shown below.
//Change the query to PATCH and run the query.

{
    "@odata.context": "https://graph.microsoft.com/beta/$metadata#authenticationMethodConfigurations/$entity",
    "@odata.type": "#microsoft.graph.microsoftAuthenticatorAuthenticationMethodConfiguration",
    "id": "MicrosoftAuthenticator",
    "state": "enabled",
    "isSoftwareOathEnabled": false,
    "excludeTargets": [],
    "featureSettings": {
        "companionAppAllowedState": {
            "state": "enabled",
            "includeTarget": {
                "targetType": "group",
                "id": "s4432809-3bql-5m2l-0p42-8rq4707rq36m"
            },
            "excludeTarget": {
                "targetType": "group",
                "id": "00000000-0000-0000-0000-000000000000"
            }
        }
    },
    "includeTargets@odata.context": "https://graph.microsoft.com/beta/$metadata#authenticationMethodsPolicy/authenticationMethodConfigurations('MicrosoftAuthenticator')/microsoft.graph.microsoftAuthenticatorAuthenticationMethodConfiguration/includeTargets",
    "includeTargets": [
        {
            "targetType": "group",
            "id": "all_users",
            "isRegistrationRequired": false,
            "authenticationMode": "any"
        }
    ]
}
```

### ユーザーの登録

Authenticator Lite が有効になっている場合は、Outlook モバイルから直接アカウントを登録するように求められます。 Authenticator Lite の登録は、 [マイ サインイン](https://aka.ms/mysignins)を使用して使用することはできません。ユーザーは、Outlook モバイル内から Authenticator Lite を有効または無効にすることもできます。 ユーザー エクスペリエンスの詳細については、「 [Authenticator Lite のサポート](https://aka.ms/authappliteuserdocs)」を参照してください。

[Image: Authenticator Lite を登録する方法を示すスクリーンショット。]

ユーザーが MFA メソッドを登録していない場合は、登録フローを開始するときに Authenticator をダウンロードするように求められます。 最もシームレスなエクスペリエンスを実現するには、Authenticator Lite の登録中に [一時アクセス パス (TAP)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass) を使用してユーザーをプロビジョニングします。

### Authenticator Lite の使用状況を監視する

[サインイン ログ](https://learn.microsoft.com/ja-jp/graph/api/signin-list) には、ユーザー認証を完了するために使用されたアプリが表示されます。 最新のサインインを表示するには、ベータ API エンドポイントで次の呼び出しを使用します。

```http
GET auditLogs/signIns
```

サインインが電話アプリ通知によって行われた場合は、 `authenticationAppDeviceDetails` の **clientApp** フィールドから `microsoftAuthenticator` または **Outlook** が返されます。

ユーザーが Authenticator Lite を登録している場合、ユーザーの登録された認証方法には、Microsoft Authenticator (Outlook)が含まれます。

### Authenticator Lite のプッシュ通知

Authenticator Lite から送信されるプッシュ通知は構成できず、Authenticator 機能の設定に依存しません。 Authenticator Lite ではパスワードレス認証モードをサポートされていません。 次の表に、Authenticator Lite エクスペリエンスに含まれる機能の設定を示します。 すべての認証には、認証機能の設定に関係なく、番号に一致するプロンプトが含まれており、アプリと場所のコンテキストは含まれません。

| Authenticator 機能 | Authenticator Lite エクスペリエンス |
| --- | --- |
| 数値の一致 | 有効化済み |
| 場所コンテキスト | いいえ |
| アプリケーション コンテキスト | いいえ |

Authenticator Lite がプッシュ通知を送信したときにユーザーに表示される内容を次のスクリーンショットに示します。

[Image: Outlook モバイルでのプッシュ通知を示すスクリーンショット。]

### AD FS アダプターと NPS 拡張機能

Authenticator Lite では、認証ごとに数値の一致が実施されます。 テナントで AD FS アダプターまたは NPS 拡張機能を使用している場合、ユーザーは Authenticator Lite 通知を完了できない可能性があります。 詳細については、「 [AD FS アダプター](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-number-match#ad-fs-adapter) と [NPS 拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-number-match#nps-extension)」を参照してください。

検証通知の詳細については、 [Microsoft Authenticator 認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-authenticator-app)に関するページを参照してください。

### 一般的な質問

次のセクションでは、一般的な質問の一覧を示します。

#### Authenticator Lite はブローカー アプリとして機能しますか?

いいえ。Authenticator Lite はプッシュ通知と TOTP でのみ使用できます。

#### Authenticator Lite を SSPR に使用できますか?

いいえ。Authenticator Lite はプッシュ通知と TOTP でのみ使用できます。

#### Authenticator Lite は Outlook デスクトップ アプリで使用できますか?

いいえ。Authenticator Lite は Outlook モバイルでのみ使用できます。

#### ユーザーはどこで Authenticator Lite に登録できますか?

ユーザーは、モバイル Outlook からのみ Authenticator Lite に登録できます。 Authenticator Lite の登録は、 [マイ サインイン](https://aka.ms/mysignins)から管理されます。

#### ユーザーは Authenticator と Authenticator Lite を登録できますか?

デバイスに Authenticator を持つユーザーは、その同じデバイスに Authenticator Lite を登録できません。 ユーザーが Authenticator Lite の登録を行い、その後 Authenticator をダウンロードした場合は、両方を登録できます。 ユーザーが 2 つのデバイスを持っている場合は、一方に Authenticator Lite を登録し、もう一方に Authenticator を登録できます。

### 既知の問題

次の問題がわかっている。

#### SSPR 通知

Outlook の TOTP コードは SSPR で動作しますが、プッシュ通知は機能せず、エラーが返されます。

#### 追加された条件付きアクセスの評価がログに表示されている

条件付きアクセス ポリシーは、ユーザーが Outlook アプリを開いて Authenticator Lite に登録する資格があるかどうかを判断するたびに評価されます。 これらのチェックはログに表示される場合があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-mfa-expected-inbound-assertions"} -->
## フェデレーション IdP からの MFA 要求を使用して Microsoft Entra ID 多要素認証 (MFA) コントロールを満たす - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-expected-inbound-assertions
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra ID 多要素認証 (MFA) の SAML/WSFed アサーションについて説明します。

このドキュメントでは、Security Assertions Markup Language (SAML) および WS-Fed フェデレーション用に構成された acceptIfMfaDoneByFederatedIdp および enforceMfaByFederatedIdp の [federatedIdpMfaBehaviour](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-fed) 値を優先するために、Microsoft Entra ID が[フェデレーション ID プロバイダー (IdP)](https://learn.microsoft.com/ja-jp/graph/api/domain-post-federationconfiguration#federatedidpmfabehavior-values) に要求するアサーションについて説明します。

ヒント

フェデレーション IdP による Microsoft Entra ID の構成は、**省略可能**です。 Microsoft Entra では、Microsoft Entra ID で利用可能な [認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication) を推奨しています。

- Microsoft Entra ID には、フェデレーション IdP を経由しなければ使用できなかった証明書やスマートカードを用いた認証方法のサポートが含まれており、その中には [Entra 証明書ベースの認証も含まれます](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication)
- Microsoft Entra ID には、サードパーティの MFA プロバイダーとの [外部認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-external-method-manage) の統合をサポートが含まれています。
- フェデレーション IdP と統合されたアプリケーションは、[Microsoft Entra ID と直接統合](https://learn.microsoft.com/ja-jp/entra/architecture/migration-best-practices)できます。

### WS-Fed または SAML 1.1 フェデレーション IdP の使用

管理者が必要に応じて、WS-Fed フェデレーションを使用して [フェデレーション IdP](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-fed) を使用するように Microsoft Entra ID テナントを構成すると、Microsoft Entra は認証のために IdP にリダイレクトし、SAML 1.1 アサーションを含む要求セキュリティ トークン応答 (RSTR) の形式で応答を期待します。 そのように構成されている場合、次の 2 つの要求のいずれかが存在する場合、Microsoft Entra は IdP によって実行された MFA を受け入ります。

- `http://schemas.microsoft.com/claims/multipleauthn`
- `http://schemas.microsoft.com/claims/wiaormultiauthn`

これらは、`AuthenticationStatement` 要素の一部としてアサーションに含めることができます。 例えば：

```xml
 <saml:AuthenticationStatement
    AuthenticationMethod="http://schemas.microsoft.com/claims/multipleauthn" ..>
    <saml:Subject> ... </saml:Subject>
</saml:AuthenticationStatement>
```

または、`AttributeStatement` 要素の一部としてアサーションに含めることができます。 例えば：

```xml
<saml:AttributeStatement>
  <saml:Attribute AttributeName="authenticationmethod" AttributeNamespace="http://schemas.microsoft.com/ws/2008/06/identity/claims">
       <saml:AttributeValue>...</saml:AttributeValue> 
      <saml:AttributeValue>http://schemas.microsoft.com/claims/multipleauthn</saml:AttributeValue>
  </saml:Attribute>
</saml:AttributeStatement>
```

#### WS-Fed または SAML 1.1 でのサインイン頻度とセッション制御の条件付きアクセス ポリシーの使用

[サインイン頻度](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-session#sign-in-frequency) では UserAuthenticationInstant (SAML アサーション `http://schemas.microsoft.com/ws/2008/06/identity/claims/authenticationinstant`) が使用されます。これは、SAML1.1/WS-Fed のパスワードを使用した第 1 要素認証の AuthInstant です。

### SAML 2.0 フェデレーション IdP の使用

必要に応じて、管理者が SAMLP/SAML 2.0 フェデレーションを使用して フェデレーション IdPを使用するように Microsoft Entra ID テナントを構成すると、Microsoft Entra は認証のために IdP にリダイレクトされ、SAML 2.0 アサーションを含む応答が必要になります。 受信 MFA アサーションは、`AuthnContext` の `AuthnStatement` 要素に存在する必要があります。

```xml
<AuthnStatement AuthnInstant="2024-11-22T18:48:07.547Z">
    <AuthnContext>
        <AuthnContextClassRef>http://schemas.microsoft.com/claims/multipleauthn</AuthnContextClassRef>
    </AuthnContext>
</AuthnStatement>
```

その結果、受信した MFA アサーションが Microsoft Entra によって処理されるには、それらは**必ず**`AuthnContext` 要素内に存在する必要があります`AuthnStatement`。 この方法で提示できるメソッドは 1 つだけです。

#### SAML 2.0 でのサインイン頻度とセッション制御の条件付きアクセス ポリシーの使用

[サインイン頻度](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-session#sign-in-frequency) では、`AuthnStatement`で提供される MFA 認証または First Factor 認証の AuthInstant が使用されます。 ペイロードの `AttributeReference` セクションで共有されているアサーションは、`http://schemas.microsoft.com/ws/2017/04/identity/claims/multifactorauthenticationinstant`を含め、無視されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-mfa-manage-oath-tokens"} -->
## Microsoft Entra ID で OATH トークンを管理する方法 (プレビュー) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-manage-oath-tokens
- Service: entra-id / authentication
- Article date: 2025-03-26
- Summary: Microsoft Entra ID で OATH トークンを管理して、サインイン イベントの改善とセキュリティ保護に役立てる方法について説明します。

このトピックでは、ハードウェア OATH トークンのアップロード、アクティブ化、割り当てに使用できる Microsoft Graph API など、Microsoft Entra ID でハードウェア OATH トークンを管理する方法について説明します。

### 認証方法ポリシーでハードウェア OATH トークンを管理する (プレビュー)

Microsoft Graph API または Microsoft Entra 管理センターを使用して、認証方法ポリシーでのハードウェア OATH トークンの表示と有効化を行うことができます。

- API を使用してハードウェア OATH トークン ポリシーの状態を表示するには:

    ```https
    GET https://graph.microsoft.com/beta/policies/authenticationMethodsPolicy/authenticationMethodConfigurations/hardwareOath
    ```
- API を使用してハードウェア OATH トークン ポリシーを有効にするには。

    ```https
    PATCH https://graph.microsoft.com/beta/policies/authenticationMethodsPolicy/authenticationMethodConfigurations/hardwareOath
    ```

    要求本文に以下を追加します。

    ```https
    {
      "state": "enabled"
    }
    ```

Microsoft Entra 管理センターでハードウェア OATH トークンを有効にするには:

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **Entra ID**&gt;**Authentication メソッド**&gt;**Hardware OATH トークン (プレビュー)** に移動します。
3. [を有効にする] を選択し、ポリシーに含めるユーザーのグループを選択して、[の保存] 選択します。

    [Image: Microsoft Entra 管理センターでハードウェア OATH トークンを有効にする方法のスクリーンショット。]

ハードウェア OATH トークン [を管理するには、認証方法ポリシーに移行](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-methods-manage) することをお勧めします。 レガシ MFA ポリシーで OATH トークンを有効にする場合は、Microsoft Entra 管理センターのポリシーを認証ポリシー管理者として参照します。 **Entra ID**&gt;**Multifactor 認証**&gt;**追加のクラウドベースの多要素認証設定**。 **モバイル アプリまたはハードウェア トークンからの確認コード**のチェック ボックスをオフにします。

### サード パーティ製ソフトウェア OATH トークンの管理

サード パーティ製ソフトウェア OATH トークンは、既定でサインインに対して有効になっています。 [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)は、これらを無効にして、ユーザーがサードパーティの ID プロバイダーからのワンタイム パスワードでサインインできないようにすることができます。

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **Entra ID**&gt;**認証方法**&gt;**サードパーティ ソフトウェア OATH トークン**に移動します。
3. **Enable** コントロールの silder を移動して、ユーザーがサード パーティ製ソフトウェア OATH トークンを使用してサインインできないようにします。
4. [ **確認]** をクリックし、[保存] をクリック **します**。

### シナリオ: 管理者がハードウェア OATH トークンの作成、割り当て、アクティブ化を行う

このシナリオでは、必要な API 呼び出しや検証手順など、管理者としてハードウェア OATH トークンの作成、割り当て、アクティブ化を行う方法について説明します。 これらの API を呼び出し、要求応答のサンプルを検査するために必要なアクセス許可の詳細については、「 [hardwareOathTokenAuthenticationMethodDevice の作成](https://learn.microsoft.com/ja-jp/graph/api/authenticationmethoddevice-post-hardwareoathdevices?view=graph-rest-beta&preserve-view=true)」を参照してください。

注

ポリシーの反映には最大で 20 分かかる可能性があります。 ユーザーがハードウェア OATH トークンを使用してサインインし、 [セキュリティ情報](https://mysignins.microsoft.com/security-info)に表示されるまで、ポリシーが更新されるまでに 1 時間を許可します。

グローバル管理者がトークンを作成してユーザーに割り当てる例を見てみましょう。 アクティブ化しないで割り当てを許可できます。

この例の POST の本文では、デバイスから **serialNumber** を見つけることができ、 **secretKey** が配信されます。

```https
POST https://graph.microsoft.com/beta/directory/authenticationMethodDevices/hardwareOathDevices
{ 
"serialNumber": "GALT11420104", 
"manufacturer": "Thales", 
"model": "OTP 110 Token", 
"secretKey": "C2dE3fH4iJ5kL6mN7oP1qR2sT3uV4w", 
"timeIntervalInSeconds": 30, 
"assignTo": {"id": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee"}
}
```

応答には、トークン **ID** と、トークンが割り当てられているユーザー **ID** が含まれます。

```http
{
    "@odata.context": "https://graph.microsoft.com/beta/$metadata#directory/authenticationMethodDevices/hardwareOathDevices/$entity",
    "id": "3dee0e53-f50f-43ef-85c0-b44689f2d66d",
    "displayName": null,
    "serialNumber": "GALT11420104",
    "manufacturer": "Thales",
    "model": "OTP 110 Token",
    "secretKey": null,
    "timeIntervalInSeconds": 30,
    "status": "available",
    "lastUsedDateTime": null,
    "hashFunction": "hmacsha1",
    "assignedTo": {
        "id": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
        "displayName": "Test User"
    }
}
```

認証ポリシー管理者がトークンをアクティブ化する方法を次に示します。 要求本文の確認コードを、実際のハードウェア OATH トークンのコードに置き換えます。

```https
POST https://graph.microsoft.com/beta/users/00aa00aa-bb11-cc22-dd33-44ee44ee44ee/authentication/hardwareOathMethods/3dee0e53-f50f-43ef-85c0-b44689f2d66d/activate

{ 
    "verificationCode" : "903809" 
}
```

トークンがアクティブ化されたことを検証するには、テスト ユーザーとして [セキュリティ情報](https://aka.ms/mysecurityinfo) にサインインします。 Microsoft Authenticator からのサインイン要求の承認を求めるメッセージが表示されたら、[確認コードを使用する] を選択します。

GET を使用してトークンの一覧を表示できます。

```https
GET https://graph.microsoft.com/beta/directory/authenticationMethodDevices/hardwareOathDevices 
```

この認証ポリシー管理者の例では、1 つのトークンを作成します。

```https
POST https://graph.microsoft.com/beta/directory/authenticationMethodDevices/hardwareOathDevices
```

要求本文に以下を追加します。

```https
{ 
"serialNumber": "GALT11420104", 
"manufacturer": "Thales", 
"model": "OTP 110 Token", 
"secretKey": "abcdef2234567abcdef2234567", 
"timeIntervalInSeconds": 30, 
"hashFunction": "hmacsha1" 
}

```

応答にはトークン ID が含まれています。

```http
#### Response
{
    "@odata.context": "https://graph.microsoft.com/beta/$metadata#directory/authenticationMethodDevices/hardwareOathDevices/$entity",
    "id": "3dee0e53-f50f-43ef-85c0-b44689f2d66d",
    "displayName": null,
    "serialNumber": "GALT11420104",
    "manufacturer": "Thales",
    "model": "OTP 110 Token",
    "secretKey": null,
    "timeIntervalInSeconds": 30,
    "status": "available",
    "lastUsedDateTime": null,
    "hashFunction": "hmacsha1",
    "assignedTo": null
}
```

認証ポリシー管理者またはエンド ユーザーは、トークンの割り当てを解除できます。

```https
DELETE https://graph.microsoft.com/beta/users/66aa66aa-bb77-cc88-dd99-00ee00ee00ee/authentication/hardwareoathmethods/6c0272a7-8a5e-490c-bc45-9fe7a42fc4e0
```

この例では、トークン ID が 3dee0e53-f50f-43ef-85c0-b44689f2d66d であるトークンの削除方法を示します。

```https
DELETE https://graph.microsoft.com/beta/directory/authenticationMethodDevices/hardwareOathDevices/3dee0e53-f50f-43ef-85c0-b44689f2d66d
```

### シナリオ: 管理者は、ユーザーがアクティブ化するハードウェア OATH トークンを作成して割り当てます

このシナリオでは、全体管理者がトークンを作成して割り当てます。その後、ユーザーはセキュリティ情報ページまたは Microsoft Graph Explorer を使用してトークンをアクティブ化できます。 トークンを割り当てると、ユーザーが [セキュリティ情報](https://aka.ms/mysecurityinfo) にサインインしてトークンをアクティブ化するための手順を共有できます。 [**Add sign-in method]\(サインイン方法の追加**\)&gt;**Hardware トークンを**選択できます。 ユーザーはハードウェア トークンのシリアル番号を入力する必要があります。番号は通常、デバイスの裏面に記載されています。

```https
POST https://graph.microsoft.com/beta/directory/authenticationMethodDevices/hardwareOathDevices
{ 
"serialNumber": "GALT11420104", 
"manufacturer": "Thales", 
"model": "OTP 110 Token", 
"secretKey": "C2dE3fH4iJ5kL6mN7oP1qR2sT3uV4w", 
"timeIntervalInSeconds": 30, 
"assignTo": {"id": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee"}
}
```

認証管理者は、ユーザーにトークンを割り当てることができます。

```https
POST https://graph.microsoft.com/beta/users/00aa00aa-bb11-cc22-dd33-44ee44ee44ee/authentication/hardwareOathMethods
{
    "device": 
    {
        "id": "6c0272a7-8a5e-490c-bc45-9fe7a42fc4e0" 
    }
}
```

セキュリティ情報でユーザーがハードウェア OATH トークンを自分でアクティブ化する手順は次のとおりです。

1. [セキュリティ情報](https://aka.ms/mysecurityinfo)にサインインします。
2. [ **サインイン方法の追加]** を選択し、[ **ハードウェア トークン**] を選択します。

    [Image: [セキュリティ情報] に新しいサインイン方法を追加する方法のスクリーンショット。]
3. ハードウェア トークン選択した後、[の追加] を選択します。

    [Image: セキュリティ情報にハードウェア OATH トークンを追加する方法のスクリーンショット。]
4. デバイスの背面でシリアル番号を確認し、それを入力して、[次へ]選択します。

    [Image: ハードウェア OATH トークンのシリアル番号を追加する方法のスクリーンショット。]
5. この方法を選択して多要素認証を完了するのに役立つフレンドリ名を作成し、[次へ]選択します。

    [Image: ハードウェア OATH トークンのフレンドリ名を追加する方法のスクリーンショット。]
6. デバイスのボタンをタップしたときに表示されるランダムな確認コードを入力します。 30 秒ごとにコードを更新するトークンの場合は、コードを入力し、1 分以内に [次へ]選択する必要があります。 60 秒ごとに更新されるトークンの場合は、2 分の余裕があります。

    [Image: ハードウェア OATH トークンをアクティブ化する検証コードを追加する方法のスクリーンショット。]
7. ハードウェア OATH トークンが正常に追加されたことを確認したら、[完了]選択します。

    [Image: 追加後のハードウェア OATH トークンのスクリーンショット。]
8. 使用可能な認証方法の一覧にハードウェア OATH トークンが表示されます。

    [Image: セキュリティ情報のハードウェア OATH トークンのスクリーンショット。]

ユーザーが Graph Explorer を使用してハードウェア OATH トークンを自分でアクティブ化する手順は次のとおりです。

1. Microsoft Graph Explorer を開き、サインインして、必要なアクセス許可に同意します。
2. 必要なアクセス許可を持っていることを確認します。 ユーザーが API 操作のセルフサービスを実行できるためには、管理者が `Directory.Read.All`、`User.Read.All`、`User.ReadWrite.All` に同意する必要があります。
3. 自分のアカウントに割り当てられていて、まだアクティブ化されていないハードウェア OATH トークンの一覧を取得します。

    ```https
    GET https://graph.microsoft.com/beta/me/authentication/hardwareOathMethods
    ```
4. トークン デバイスの **ID を** コピーし、URL の末尾に */activate* を追加します。 要求本文に確認コードを入力し、コードが変更される前に POST 呼び出しを送信する必要があります。

    ```https
    POST https://graph.microsoft.com/beta/me/authentication/hardwareOathMethods/b65fd538-b75e-4c88-bd08-682c9ce98eca/activate
    ```

    要求本文:

    ```https
    {
       "verificationCode": "988659"
    }
    ```

### シナリオ: 管理者は、ユーザーが自己割り当ておよびアクティブ化する複数のハードウェア OATH トークンを一括で作成します

このシナリオでは、認証ポリシー管理者が割り当てなしでトークンを作成し、ユーザーはトークンを自己割り当ておよびアクティブ化します。 新しいトークンをテナントにまとめてアップロードできます。 ユーザーは [セキュリティ情報](https://aka.ms/mysecurityinfo) にサインインしてトークンをアクティブ化できます。 [**Add sign-in method]\(サインイン方法の追加**\)&gt;**Hardware トークンを**選択できます。 ユーザーはハードウェア トークンのシリアル番号を入力する必要があります。番号は通常、デバイスの裏面に記載されています。

トークンが特定のユーザーによってのみアクティブ化されることを、より確実に保証するには、トークンをユーザーに割り当てて、自分でアクティブ化するようそのユーザーにデバイスを送ることができます。

```https
PATCH https://graph.microsoft.com/beta/directory/authenticationMethodDevices/hardwareOathDevices
{
"@context":"#$delta", 
"value": [ 
    { 
        "@contentId": "1", 
        "serialNumber": "GALT11420108", 
        "manufacturer": "Thales", 
        "model": "OTP 110 Token", 
        "secretKey": "abcdef2234567abcdef2234567", 
        "timeIntervalInSeconds": 30, 
        "hashFunction": "hmacsha1" 
        },
    { 
        "@contentId": "2", 
        "serialNumber": "GALT11420112", 
        "manufacturer": "Thales", 
        "model": "OTP 110 Token", 
        "secretKey": "2234567abcdef2234567abcdef", 
        "timeIntervalInSeconds": 30, 
        "hashFunction": "hmacsha1" 
        }
    ]          
} 
```

### ハードウェア OATH トークンに関する問題のトラブルシューティング

このセクションでは、一般的なものについて説明します

#### ユーザーが同じシリアル番号を持つ 2 つのトークンを持っている

ユーザーは、認証方法として同じハードウェア OATH トークンの 2 つのインスタンスを登録している可能性があります。 これは、レガシ トークンが Microsoft Graph を使用してアップロードされた後に、Microsoft Entra 管理センターの **OATH トークン (プレビュー)** から削除されない場合に発生します。

この場合、トークンの両方のインスタンスがユーザーに登録されたものとして一覧に表示されます。

```https
GET https://graph.microsoft.com/beta/users/{user-upn-or-objectid}/authentication/hardwareOathMethods
```

トークンの両方のインスタンスは、Microsoft Entra 管理センターの **OATH トークン (プレビュー)** にも表示されます。

[Image: Microsoft Entra 管理センターの重複するトークンのスクリーンショット。]

レガシ トークンを特定して削除するには、次の手順を実行します。

1. ユーザーのすべてのハードウェア OATH トークンの一覧を表示します。

    ```https
    GET https://graph.microsoft.com/beta/users/{user-upn-or-objectid}/authentication/hardwareOathMethods
    ```

    両方のトークンの **ID を** 見つけて、重複するトークンの **serialNumber を** コピーします。
2. レガシ トークンを特定します。 次のコマンドの応答では、トークンが 1 つだけ返されます。 そのトークンは、Microsoft Graph を使用して作成されたものです。

    ```https
    GET https://graph.microsoft.com/beta/directory/authenticationMethodDevices/hardwareOathDevices?$filter=serialNumber eq '20033752'
    ```
3. レガシ トークンの割り当てをユーザーから削除します。 新しいトークンの **ID が** わかったら、手順 1 で返された一覧からレガシ トークンの **ID を** 識別できます。 レガシ トークン ID を使用して URL を作成 **します**。

    ```https
    DELETE https://graph.microsoft.com/beta/users/{user-upn-or-objectid}/authentication/hardwareOathMethods/{legacyHardwareOathMethodId}
    ```
4. この呼び出しでレガシ トークン **ID を** 使用して、レガシ トークンを削除します。

    ```https
    DELETE https://graph.microsoft.com/beta/directory/authenticationMethodDevices/hardwareOathDevices/{legacyHardwareOathMethodId}
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-mfa-number-match"} -->
## Authenticator の MFA プッシュ通知での番号照合のしくみ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-number-match
- Service: entra-id / authentication
- Article date: 2025-11-06
- Summary: Microsoft Authenticator の多要素認証通知で番号照合を使用する方法について説明します。

この記事では、Authenticator プッシュ通知での番号の一致によってユーザーサインインのセキュリティが向上する方法について説明します。 数値照合は、Authenticator の従来の第 2 要素通知への重要なセキュリティ アップグレードです。

すべての Authenticator プッシュ通知に対して、番号照合が有効になります。

### 数値一致シナリオ

番号照合は、次のシナリオで使用できます。 有効にすると、すべてのシナリオで番号照合がサポートされます。

- MFA
- セルフサービス パスワード リセット (SSPR)
- Authenticator アプリのセットアップ中に SSPR と MFA の登録を組み合わせたもの
- Active Directoryフェデレーションサービス(AD FS)アダプター
- ネットワーク ポリシー サーバー (NPS) 拡張機能

Apple Watch または Android ウェアラブル デバイスのプッシュ通知では、番号照合はサポートされていません。 ウェアラブル デバイスのユーザーは、電話番号の照合が有効になっているときに、電話を使用して通知を承認する必要があります。

#### 多要素認証

ユーザーが Authenticator を使用して MFA プッシュ通知に応答すると、数値が表示されます。 承認を完了するには、その番号をアプリに入力する必要があります。 MFA を設定する方法の詳細については、「 [チュートリアル: Microsoft Entra 多要素認証を使用してユーザー サインイン イベントをセキュリティで保護](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-azure-mfa)する」を参照してください。

[Image: 数値の一致を入力しているユーザーを示すスクリーンショット。]

#### SSPR

Authenticator を使用する SSPR では、ユーザーが Authenticator を使用する場合に番号照合が必要です。 SSPR 中、サインイン ページには、ユーザーが Authenticator 通知に入力する必要がある番号が表示されます。 SSPR の設定方法の詳細については、「 [チュートリアル: ユーザーが自分のアカウントのロックを解除したり、パスワードをリセットしたりできるようにする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-deployment)」を参照してください。

#### 統合された登録

Authenticator との統合された登録には、番号の一致が必要です。 ユーザーが認証システムをセットアップするために統合登録を行う場合、ユーザーはアカウントを追加するための通知を承認する必要があります。 この通知には、ユーザーが Authenticator 通知に入力する必要がある番号が表示されます。 統合登録を設定する方法の詳細については、「 [統合されたセキュリティ情報の登録を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-registration-mfa-sspr-combined)」を参照してください。

#### AD FS アダプター

AD FS アダプターには、サポートされているバージョンの Windows Server で番号の一致が必要です。 以前のバージョンでは、ユーザーには引き続き **Approve**/**Deny** エクスペリエンスが表示され、アップグレードするまで番号の一致は表示されません。 AD FS アダプターは、次の表のいずれかの更新プログラムをインストールした後にのみ、番号照合をサポートします。 AD FS アダプターを設定する方法の詳細については、「 [Windows Server で AD FS を使用するように Microsoft Entra Multifactor Authentication Server を構成する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfaserver-adfs-windows-server)」を参照してください。

注

修正プログラムが適用されていないバージョンの Windows Server では、番号の照合はサポートされていません。 ユーザーには引き続き **承認**/**Deny** エクスペリエンスが表示され、これらの更新プログラムが適用されない限り、番号の一致は表示されません。

| バージョン | 更新 |
| --- | --- |
| Windows Server 2022 | [2021 年 11 月 9 日 — KB5007205 (OS ビルド 20348.350)](https://support.microsoft.com/topic/november-9-2021-kb5007205-os-build-20348-350-af102e6f-cc7c-4cd4-8dc2-8b08d73d2b31) |
| Windows Server 2019 | [2021 年 11 月 9 日 — KB5007206 (OS ビルド 17763.2300)](https://support.microsoft.com/topic/november-9-2021-kb5007206-os-build-17763-2300-c63b76fa-a9b4-4685-b17c-7d866bb50e48) |
| Windows Server 2016 | [2021 年 10 月 12 日 — KB5006669 (OS ビルド 14393.4704)](https://support.microsoft.com/topic/october-12-2021-kb5006669-os-build-14393-4704-bcc95546-0768-49ae-bec9-240cc59df384) |

#### NPS 拡張機能

NPS では番号の照合はサポートされていませんが、最新の NPS 拡張機能では、Authenticator で使用可能な TOTP、その他のソフトウェア トークン、ハードウェア FOB などの時間ベースのワンタイム パスワード (TOTP) メソッドがサポートされています。 TOTP サインインは、代替の **Approve**/**Deny** エクスペリエンスよりも優れたセキュリティを提供します。 最新バージョンの [NPS 拡張機能](https://www.microsoft.com/download/details.aspx?id=54688)を実行していることを確認します。

NPS 拡張機能バージョン 1.2.2216.1 以降で RADIUS 接続を実行するユーザーは、 **承認**/**Deny** の代わりに TOTP メソッドを使用してサインインするように求められます。 この動作を得るには、ユーザーは TOTP 認証方法を登録する必要があります。 TOTP メソッドが登録されていない場合、ユーザーは引き続き **[承認**/**Deny**] を表示します。

これらの以前のバージョンの NPS 拡張機能を実行している組織は、ユーザーに TOTP の入力を要求するようにレジストリを変更できます。

- 1.2.2131.2
- 1.2.1959.1
- 1.2.1916.2
- 1.1.1892.2
- 1.0.1850.1
- 1.0.1.41
- 1.0.1.40

注

1.0.1.40 より前のバージョンの NPS 拡張機能では、番号一致によって適用される TOTP はサポートされていません。 これらのバージョンでは引き続き **Approve**/**Deny** が使用されます。

レジストリ エントリを作成して、プッシュ通知の **[承認**]/**Deny** オプションをオーバーライドするには、代わりに TOTP が必要です。

1. NPS サーバーで、レジストリ エディターを開きます。
2. `HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\AzureMfa` にアクセスします。
3. 次の文字列と値のペアを作成します。

    - 名前: `OVERRIDE_NUMBER_MATCHING_WITH_OTP`
    - 値 = `TRUE`
4. NPS サービスを再起動します。

さらに：

- TOTP を実行するユーザーは、認証方法または他のハードウェアまたはソフトウェア OATH トークンとして Authenticator が登録されている必要があります。 TOTP メソッドを使用できないユーザーは常に、1.2.2216.1 より前のバージョンの NPS 拡張機能を使用している場合は、プッシュ通知で **承認**/**Deny** オプションが表示されます。
- NPS 拡張機能がインストールされている NPS サーバーは、パスワード認証プロトコル (PAP) を使用するように構成する必要があります。 詳細については、[ユーザーが使用できる認証方法の決定](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-nps-extension#determine-which-authentication-methods-your-users-can-use)に関するページを参照してください。

    Von Bedeutung

    MSCHAPv2 は TOTP をサポートしていません。 NPS サーバーが PAP を使用するように構成されていない場合、ユーザーの承認は、イベント ビューアーの NPS 拡張機能サーバーの **AuthZOptCh** ログのイベントで失敗します。

    - Azure MFA の NPS 拡張機能: 認証拡張機能でユーザー `npstesting_ap`に対して要求されたチャレンジ。

    PAP をサポートするように NPS サーバーを構成できます。 PAP がオプションでない場合は、`OVERRIDE_NUMBER_MATCHING_WITH_OTP = FALSE` を **承認**/**拒否**プッシュ通知にフォールバックするように設定します。

組織でリモート デスクトップ ゲートウェイを使用し、TOTP コードに登録されたユーザーと Authenticator プッシュ通知を使用している場合、ユーザーは Microsoft Entra MFA チャレンジを満たできず、リモート デスクトップ ゲートウェイのサインインが失敗します。 この場合は、`OVERRIDE_NUMBER_MATCHING_WITH_OTP = FALSE` を **承認**/**拒否** のプッシュ通知にフォールバックするように設定し、Authenticator を使います。

### Authenticator アプリの同一デバイス番号の照合

ユーザーが認証アプリと同じデバイス上の Teams や Outlook などの Microsoft モバイル アプリと一致する番号で MFA または電話サインインにサインインすると、番号を入力するのではなく、メッセージが表示されたときにはい/いいえに応答できます。 Microsoft Edge、Chrome、または Safari の Web ブラウザーでサインインするユーザーは、サインインする番号を引き続き入力します。

これにより、Authenticator を実行するのと同じデバイスで番号照合を使用してサインインするユーザーのエクスペリエンスが大幅に向上します。 サインインを開始したデバイスにのみプロンプトが表示されるため、[はい]/[いいえ] に切り替えることでユーザーのリスクが高まるわけではありません。

プラットフォーム固有のシナリオの詳細については、このトピックで説明します。

注

次のシナリオでは、ユーザーは Authenticator と同じデバイスでサインインします。 ユーザーが別のデバイスで番号の照合を完了しても、エクスペリエンスは変わりません。

#### 準備する方法

管理者は、準備するために何も構成する必要はありません。 ユーザーは、最新バージョンの Microsoft Authenticator のみを実行する必要があります。

## [iOS](#tab/iOS)
##### ユーザー エクスペリエンスの変更

| Scenario | エクスペリエンスの変更 |
| --- | --- |
| ユーザーは Authenticator にサインインして、多要素認証 (MFA) に使用するアカウントをアップグレードします。 | はい。 ユーザーは、サインイン画面に番号一致要求を含む通知を表示します。 Authenticator などの Microsoft モバイル アプリでは、通知をタップし、[はい]/[いいえ] と返信してサインインを完了できます。 |
| ユーザーは、シングル サインオン (SSO) 拡張機能なしで Outlook や Teams などの Microsoft アプリにサインインします。 | はい。 ユーザーは、サインイン画面に番号一致要求を含む通知を表示します。 Outlook や Teams などの Microsoft モバイル アプリでは、通知をタップし、[はい] または [いいえ] と返信してサインインを完了できます。 |
| ユーザーは、SSO 拡張機能を使用して Outlook や Teams などの Microsoft アプリにサインインします。 | はい。 ユーザーに [はい] または [いいえ] というプロンプトが表示されますが、サインインを完了するには Authenticator アプリを開く必要があります。 |
| ユーザーは、Edge や Chrome などのブラウザーにサインインします。 | No. ユーザーは、サインイン画面に番号一致要求を含む通知を表示します。 ブラウザーでは、通知をタップして番号を入力し、サインインを承認する必要があります。 |

## [Android](#tab/Android)
##### 同じデバイス番号の照合を行うときのユーザー エクスペリエンスの変更

| Scenario | エクスペリエンスの変更 |
| --- | --- |
| ユーザーは Authenticator にサインインして、多要素認証 (MFA) に使用するアカウントをアップグレードします。 | はい。 ユーザーは、サインイン画面に番号一致要求を含む通知を表示します。 Authenticator などの Microsoft モバイル アプリでは、通知をタップし、[はい]/[いいえ] と返信してサインインを完了できます。 |
| ユーザーは、Outlook や Teams などの Microsoft アプリにサインインします。 | はい。 ユーザーは、サインイン画面に番号一致要求を含む通知を表示します。 Outlook や Teams などの Microsoft モバイル アプリでは、通知をタップし、[はい] または [いいえ] と返信してサインインを完了できます。 |
| ユーザーは、Edge や Chrome などのブラウザーにサインインします。 | No. ユーザーは、サインイン画面に番号一致要求を含む通知を表示します。 ブラウザーでは、通知をタップして番号を入力し、サインインを承認する必要があります。 |

---

### よく寄せられる質問

このセクションでは、一般的な質問への回答を示します。

#### ユーザーは番号の照合をオプトアウトできますか?

いいえ。ユーザーは Authenticator プッシュ通知で番号の照合をオプトアウトできません。

#### Authenticator プッシュ通知が既定の認証方法として設定されている場合にのみ、番号照合が適用されますか?

はい。 ユーザーが別の既定の認証方法を持っている場合、既定のサインインに変更はありません。 既定のメソッドが Authenticator プッシュ通知の場合、番号の一致が取得されます。 既定のメソッドが、Authenticator または別のプロバイダーの TOTP など、他の方法である場合、変更はありません。

既定の方法に関係なく、Authenticator プッシュ通知でサインインするように求められたユーザーには、番号の一致が表示されます。 別のメソッドの入力を求められた場合、変更は表示されません。

#### 認証方法ポリシーで指定されていないが、レガシ MFA テナント全体のポリシーでモバイル アプリを介した通知が有効になっているユーザーはどうなりますか?

従来の MFA ポリシーで MFA プッシュ通知が有効になっているユーザーは、従来の MFA ポリシーで「モバイル アプリを介した通知」が有効になっている場合、番号の一致も表示されます。 認証方法ポリシーで Authenticator が有効になっているかどうかに関係なく、ユーザーは番号の一致を確認します。

[Image: モバイル アプリによる通知の設定を示すスクリーンショット。]

#### Azure Multifactor Authentication Server では、番号照合はサポートされていますか?

いいえ。Azure Multifactor Authentication Server でサポートされている機能ではないため、番号照合は適用されません。これは [非推奨です](https://techcommunity.microsoft.com/t5/microsoft-entra-azure-ad-blog/microsoft-entra-change-announcements-september-2022-train/ba-p/2967454)。

#### ユーザーが古いバージョンの Authenticator を実行するとどうなりますか?

ユーザーが、番号照合をサポートしていない古いバージョンの Authenticator を実行している場合、認証は機能しません。 サインインに使用するには、Authenticator の最新バージョンにアップグレードする必要があります。

#### マッチ要求が表示された後、ユーザーはモバイル iOS デバイスで番号を再確認するにはどうすればよいですか?

モバイル iOS ブローカー フロー中、2 秒の遅延後に番号の一致要求が番号上に表示されます。 番号を再確認するには、[数値をもう **一度表示する**] を選択します。 このアクションは、モバイル iOS ブローカー フローでのみ発生します。

#### Apple Watch は Authenticator でサポートされていますか?

iOS 用の 2023 年 1 月の Authenticator リリースでは、Authenticator セキュリティ機能と互換性がないため、watchOS 用のコンパニオン アプリはありません。 Apple Watch に Authenticator をインストールまたは使用することはできません。 [Apple Watch から Authenticator を削除](https://support.apple.com/HT212064)し、別のデバイスで Authenticator でサインインすることをお勧めします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-mfa-registration-campaign"} -->
## 登録キャンペーンを実行してパスキーまたはMicrosoft Authenticatorを設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-registration-campaign
- Service: entra-id / authentication
- Article date: 2026-09-15
- Summary: Microsoft Entra ID で登録キャンペーンを実施し、より強固なサインイン セキュリティに向けて、ユーザーにパスキーまたは Microsoft Authenticator の利用を促す方法について説明します。

注

このバージョンの登録キャンペーンを展開しています。 ロールアウトは 2026 年 9 月末までに完了する予定です。 それまでは、テナントでの登録キャンペーンのエクスペリエンスは、この記事で説明されているものとは異なる場合があります。

登録キャンペーンを使用すると、ユーザーがサインイン中にパスキーまたはMicrosoft Authenticatorを設定できるように微調整できます。 ユーザーが多要素認証 (MFA) を使用して対話型サインインを実行すると、対象となる認証方法を設定するように求められる場合があります。 ユーザーまたはグループを含めるか除外することで、誰に働きかけるかを制御し、安全性の低い認証方法からパスキーや Authenticator への移行を促す対象を絞ったキャンペーンを作成できます。

登録キャンペーンでは、次の 2 つの認証方法がサポートされています。

- **Passkey (FIDO2)**: ユーザーがパスキーを登録するようにナッジします。
- **Authenticator**: プッシュ通知を利用するために、ユーザーに Authenticator のダウンロードと設定を促します。

登録キャンペーンでは、一度に 1 つの認証方法をターゲットにすることができます。

### 前提条件

2 つの登録キャンペーンから選択できます。

- **Authenticator キャンペーン**: アカウントに Authenticator プッシュ通知がまだ設定されていないユーザーを対象とします。 認証方法ポリシーで Authenticator のユーザーを有効にします。 **認証モード** は[ **任意]** または[ **プッシュ**]に設定する必要があります。 モードが **パスワードレス**に設定されている場合、ユーザーはナッジの対象になりません。 詳細については、「 [Authenticator でパスワードなしのサインインを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-phone)」を参照してください。 登録キャンペーンの対象となるユーザーも、この認証方法のスコープ内にある必要があります。
- **パスキー キャンペーン**: 認証方法ポリシーでパスキー (FIDO2) 認証方法を有効にします。 また、パスキー (FIDO2) メソッドの構成で **[セルフサービス セットアップを許可する** ]を有効にします。 詳細については、「 [パスキーを有効にする」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2)参照してください。 登録キャンペーンの対象となるユーザーも、この認証方法のスコープ内にある必要があります。

必要に応じて、登録キャンペーンを構成する前に、各認証方法を登録したユーザーの数を決定します。 [認証方法のアクティビティ レポートを](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-methods-activity#registration-details)参照してください。

### 登録キャンペーンのしくみ

登録キャンペーンでは、ユーザーが多要素認証 (MFA) を完了した後、より強力な認証方法 (パスキー (FIDO2) またはMicrosoft Authenticator) を設定するように求められます。

MFA の要件は、キャンペーンの状態とターゲット認証方法によって異なります。

| キャンペーンの状態 | 対象となる認証方法 | MFA要件 |
| --- | --- | --- |
| Microsoft による管理 | Microsoft Authenticator | ユーザーは、SMS または音声通話を使用して MFA を完了します。 |
| Microsoft による管理 | パスキー (FIDO2) | ユーザーは、任意の方法を使用して MFA を完了します。 |
| Enabled | Microsoft Authenticator | ユーザーは、任意の方法を使用して MFA を完了します。 |
| Enabled | パスキー (FIDO2) | ユーザーは、任意の方法を使用して MFA を完了します。 |

どちらのキャンペーンでも、ユーザーは対象の場合にのみメッセージが表示されます。 ユーザーの資格は、キャンペーンの状態と対象となる認証方法によって異なります。

注

ユーザーが通常のサインインを行う際に、セキュリティ情報の登録を管理するMicrosoft Entra 条件付きアクセス ポリシーが適用されてから、ユーザーが認証方法を設定するように微調整されます。 たとえば、条件付きアクセス ポリシーでセキュリティ情報の更新が内部ネットワークでのみ行われる必要がある場合、ユーザーは内部ネットワーク上に存在しない限り、プロンプトは表示されません。

### キャンペーンの状態を選択する

キャンペーンの状態によって、キャンペーン設定を構成および管理するユーザーが決まります。

| 都道府県 | キャンペーンを設定するユーザー |
| --- | --- |
| Microsoft による管理 | Microsoft対象の認証方法と設定を選択し、現在のベスト プラクティスに合わせて更新します。 含めるユーザーを定義します。 |
| Enabled | 対象となる認証方法、再通知設定、および含まれるユーザーを選択します。 |
| Disabled | 登録キャンペーンは無効になっています。 |

**Microsoftマネージド**を使用して、Microsoftの推奨設定を適用します。 対象のメソッドまたは再通知の動作を制御する必要がある場合、またはマイクロソフト管理下ではパスキー プロファイルが対象外のユーザーに対してパスキー キャンペーンを実行する必要がある場合は、**[有効]** を使用します。 詳細については、「Microsoftマネージド登録キャンペーンのパスキー プロファイルの適格性」を参照してください。

次の表に、各状態で制御する設定を示します。

| Setting | Microsoft による管理 | Enabled |
| --- | --- | --- |
| 対象となる認証方法 | Microsoftで設定 | パスキーまたは認証システム |
| スヌーズできる日数 | Microsoftで設定 | 0–14 |
| スヌーズ回数の制限 | Microsoftで設定 | オンまたはオフ |
| ユーザーとグループを含める/除外する | Configurable | Configurable |

### マイクロソフト管理状態

**Microsoftマネージド**状態では、Microsoftテナントの認証方法の構成に基づいて対象となる認証方法を選択し、対応する推奨設定を適用します。 対象ユーザーでパスキーが有効になっている場合、マイクロソフトはパスキー (FIDO2) を対象とし、パスキーは有効になっていないものの Authenticator が有効になっている場合は、Microsoft Authenticator を対象とします。

次の表は、各メソッドについて Microsoft が適用する設定と利用資格を示しています。

| 財産 | Microsoft Authenticator | パスキー (FIDO2) |
| --- | --- | --- |
| スヌーズできる日数 | 1 | 1 |
| スヌーズ回数の制限 | 有効: 3回スヌーズ後、登録が必要です | 無効: スヌーズ回数無制限 |
| 対象ユーザー | 以下 **のすべてを** 満たすユーザー:音声通話またはテキスト メッセージ (SMS) を使用して MFA を実行する認証方法ポリシーで Authenticator プッシュ通知が有効になっている• Authenticator のプッシュ通知をまだ設定していない | 以下 **のすべてを** 満たすユーザー:• いずれかの MFA 方法でサインインする• 少なくとも 1 つの対象のパスキー プロファイルに属している (Microsoft 管理の登録キャンペーンにおけるパスキー プロファイルの対象条件を参照) |

#### マイクロソフトが管理する登録キャンペーンのパスキー プロファイルの適格性

登録キャンペーンが **Microsoft managed** 状態にあり、パスキーを対象としている場合、スコープ内の各ユーザーのパスキー プロファイルは、サインイン時に確認されます。 ユーザーが、次の条件を満たす**少なくとも 1 つ**のパスキー プロファイル構成の対象である場合、ユーザーは微調整されます。 このチェックは **有効** な状態では適用されません。

| パスキー プロファイル構成 | 詳細情報 |
| --- | --- |
| 無制限 | パスキー プロファイルの制限はありません。 |
| 同期済みのみ | 同期済みのパスキーのみ。 キーの制限はありません。 |
| デバイスにのみバインド 가능 | デバイスに紐づけられたパスキーのみ。 キーの制限はありません。 |
| AAGUID 制限付き | 許可リストには、次のプロバイダーに対して少なくとも 1 つの AAGUID が含まれています。• iCloud キーチェーン• Google パスワード マネージャー (GPM)• Microsoft Authenticator のパスキー• Windows 版 Microsoft Entra パスキー |
| 構成証明が適用されているデバイスバインド | キーの制限は評価されません。 |

AAGUID 制限付きプロファイルの場合:

- 前の表のプロバイダーに対して許可リストに少なくとも 1 つの AAGUID が含まれている限り、他の AAGUID を追加できます。
- **除外** リストと **ブロック** リストは、キャンペーンの適格性が決定されると無視されます。 管理者は **[除外** ] または [ **ブロック**] にエントリを含めることができますが、ターゲット ロジックでは適格性を評価しません。
- iCloud キーチェーンまたは Google パスワード マネージャーの AAGUID の場合は、 **同期された** パスキー プロファイルの種類を選択します。 Windows AAGUID における Microsoft Authenticator パスキーまたは Microsoft Entra パスキーには、**デバイス バインド** パスキー プロファイルの種類を選択します。 同期された AAGUID とデバイスバインド AAGUID の組み合わせで、パスキー プロファイルの種類の両方を選択します。

注

ユーザーがナッジの対象となるには、対象となるパスキー プロファイルが **1 つ** あれば十分です。 1 人のユーザーが複数のパスキー プロファイルに含まれており、そのうちの 1 つが上記の条件を満たしている場合、そのユーザーは対象となります。

### 有効な状態

**[有効]** 状態では、対象の認証方法を選択し、再通知設定と含まれるユーザーを構成します。 再通知の設定 (再通知が許可される日数と、スヌーズが制限されているかどうかを示す日数) は、両方の方法で同じオプションです。適格性ルールは方法によって異なります。

次の表に、各メソッドの構成と適格性を示します。

| Setting | Microsoft Authenticator | パスキー (FIDO2) |
| --- | --- | --- |
| スヌーズできる日数 | 0–14 | 0–14 |
| スヌーズ回数の制限 | [有効] または [無効] | [有効] または [無効] |
| 対象ユーザー | 以下 **のすべてを** 満たすユーザー:• いずれかの MFA 方法でサインインする認証方法ポリシーで Authenticator プッシュ通知が有効になっている• Authenticator のプッシュ通知をまだ設定していない | 以下 **のすべてを** 満たすユーザー:• いずれかの MFA 方法でサインインする• **任意**のパスキー プロファイル構成に含まれる |

**有効**な状態では、Microsoftマネージド passkey-profile の適格性チェックは適用されません。 たとえば、有効な状態を使用して、Microsoftマネージド状態のスコープ内にない AAGUID 制限を持つ同期されたパスキーをデプロイします。

### スヌーズ機能

ユーザーは、[ **今のところスキップ**] を選択することで、対象となる認証方法のセットアップを延期できます。 スヌーズ回数が制限されている場合、ユーザーは登録が必要になるまで最大3回スヌーズできます。 スヌーズの回数に制限がない場合、ユーザーは無期限にスヌーズできます。 再通知期間が経過すると、次回サインインして MFA を実行すると、ユーザーに再度メッセージが表示されます。

登録キャンペーンの状態が **[有効]** に設定されている場合は、次の設定を使用して再通知エクスペリエンスを構成します。

| Setting | 説明 |
| --- | --- |
| **スヌーズ可能日数** | 連続するプロンプトの間隔を設定します。 たとえば、期間が 3 日間の場合、登録をスキップしたユーザーは 3 日間再び求められません。 |
| **スヌーズ回数の上限** | **有効**: ユーザーはプロンプトを 3 回スキップできます。その後、対象の認証方法を登録する必要があります。**無効**: ユーザーは何回でもスヌーズできます。 |

注

**スヌーズ回数の制限** が **有効** に設定されている場合、スヌーズ回数はユーザーごとに追跡され、キャンペーンの再起動や設定変更後も維持されます（ターゲティング方法の更新を含む）。

### ユーザー エクスペリエンス

#### Authenticator キャンペーン

Authenticator 登録キャンペーンの対象になると、次のフローが発生します。

1. MFA を完了する必要があります。
2. Authenticator プッシュ通知が有効になっていて、設定されていない場合は、サインイン エクスペリエンスを向上させるために Authenticator を設定するように求められます。

    パスワードレス サインイン、セルフサービス パスワード リセット、セキュリティの既定値など、その他のセキュリティ機能でも、認証方法を設定するように求められる場合があります。

    [Image: ユーザーに Authenticator の設定を求める登録キャンペーンプロンプトを示すスクリーンショット。]
3. [ **次へ** ] を選択し、Authenticator のセットアップをステップ実行します。
4. Authenticator を設定しない場合は、**今はスキップ** を選択すると、管理者が設定した日数の間、プロンプトの表示が延期されます。 無料および試用版のサブスクリプションをご利用のユーザーは、プロンプトを最大3回まで後で再表示できます。

    [Image: 登録キャンペーンの通知を一時的にスキップする「今はスキップする」オプションが表示されたスクリーンショット。]

#### パスキー キャンペーン

パスキー登録キャンペーンの対象になると、次のフローが発生します。

1. MFA を完了する必要があります。
2. アカウントに対してパスキー登録が有効になっていて、現在のプラットフォームで資格のあるパスキーを使用できない場合は、パスキーを設定するように求められます。

    [Image: パスキー登録キャンペーンプロンプトを示すスクリーンショット。[次へ] と [その他] オプションが表示されています。]

    注

    パスキーナッジ評価では、現在の OS とブラウザーの組み合わせに対してローカル パスキーがあるかどうかを判断します。 このエクスペリエンスについてすでにローカルパスキーがある場合、通知は表示されません。 ナッジの評価は、ユーザー アカウントに登録されている内容ではなく、ご使用の各デバイスとブラウザーの組み合わせに基づいています。 各プラットフォームでナッジを満たすパスキーの種類の詳細については、「プラットフォーム 別の Passkey ナッジの評価 」セクションを参照してください。
3. **次へ**を選択します。 デバイスまたはブラウザーにパスキー作成プロンプトが表示され、パスキーが保存される場所が表示されます。 プラットフォームによっては、別のパスキー プロバイダーを選択したり、場所を保存したりすることができます。

    [Image: デバイスがセキュリティ ウィンドウを開いている間のパスキーの設定画面を示すスクリーンショット。]
4. デバイスのプロンプトに従って、顔、指紋、または PIN を使用して ID を確認します。 検証後、パスキーが保存されます。
5. [ **パスキーに名前を付ける** ] 画面で、パスキーの識別に役立つ名前を入力し、[ **次へ**] を選択します。

    [Image: パスキー名フィールドと [次へ] ボタンを使用してパスキーに名前を付ける画面を示すスクリーンショット。]
6. [ **Passkey created]\(パスキーの作成\** ) 画面で、[ **完了]** を選択してサインインを完了します。

    [Image: 登録が成功したことを確認する Passkey の作成画面を示すスクリーンショット。]
7. パスキーを設定したくない場合は、**今はスキップ** を選択して、メッセージを後で再表示できます。
8. パスキーの登録中にエラーが発生した場合は、 **スキップ** オプションを含むエラー画面が表示されます。 エラー画面からのスキップは、限られた再通知数にはカウントされないため、登録エラーによってサインインがブロックされることはありません。

### Microsoft Entra 管理センターを使用して登録キャンペーン ポリシーを有効にする

Microsoft Entra 管理センターで登録キャンペーンを有効にするには、次の手順に従います。

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **Entra ID**&gt;**認証方法**&gt;**登録キャンペーン** に移動し、**編集** を選択します。
3. **状態**の場合:

    - **[有効] を**選択して、登録キャンペーンを有効にして構成します。 状態が **[有効]** に設定されている場合は、ターゲット認証方法、再通知期間、制限された数のスヌーズ、および含まれるターゲットまたは除外されたターゲットを構成できます。
    - **Microsoft managed** を選択して、Microsoft推奨される既定値で登録キャンペーンを有効にします。 **Microsoft managed** を選択すると、ターゲット認証方法、再通知期間、および制限された数のスヌーズが自動的に設定され、構成できません。 含まれるターゲットまたは除外されたターゲットは、引き続き構成できます。 詳細については、「 [Microsoft Entra ID での認証方法の保護」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-default-enablement)参照してください。
4. **[認証方法**] で、ターゲットとする方法を選択します。

    - **Microsoft Authenticator**: ユーザーに Authenticator の設定を促します。
    - **パスキー**: ユーザーに適用されている少なくとも1つのパスキープロファイル構成の要件を満たすパスキーを登録するようユーザーに促します。
5. 登録キャンペーンに含めるユーザーまたはグループを選択し、[ **保存]** を選択します。

    [Image: 認証方法、再通知の設定、含める/除外の対象を含む、有効になっているパスキー キャンペーンが表示された Microsoft Entra 管理センターの登録キャンペーン ページを示すスクリーンショット。]

### プラットフォーム別のパスキー ナッジ評価

ユーザーが登録キャンペーンの設定に基づいてパスキーを登録する資格があると判断された後、キャンペーンは、それらを微調整する前にさらに評価を実行します。 このキャンペーンでは、ユーザーが現在の OS とブラウザーの組み合わせ (プラットフォーム) のローカル パスキーを既に持っているかどうかを確認します。

次の表は、各 OS とブラウザーの組み合わせでナッジを抑制するプラットフォーム パスキーの種類を示しています。 **ユーザーは、ナッジを抑制するために、OS とブラウザーの組み合わせで一致するパスキーの種類を少なくとも 1 つ必要とします**。 それ以外の場合、他のすべてのキャンペーン要件が満たされている場合は、互換性のあるパスキーの種類を登録するように微調整されます。

| 使用可能なパスキーの種類 | Windows + Chrome | Windows + その他のブラウザー | macOS + Chrome | macOS + その他のブラウザー | iOS | Android |
| --- | --- | --- | --- | --- | --- | --- |
| Windows Hello for Business | ✔️ | ✔️ | — | — | — | — |
| Windows での Microsoft Entra パスキー | ✔️ | ✔️ | — | — | — | — |
| Google パスワード マネージャー | ✔️ | — | ✔️ | — | — | ✔️ |
| iCloud キーチェーン (マネージドを含む) | — | — | ✔️ | ✔️ | ✔️ | — |
| macOS プラットフォーム SSO | — | — | ✔️ | ✔️ | — | — |
| Samsung Pass | — | — | — | — | — | ✔️ |
| Microsoft Authenticator のパスキー | — | — | — | — | ✔️ | ✔️ |
| セキュリティ キーなど、あらゆるクロスプラットフォーム対応プロバイダー | ✔️ | ✔️ | ✔️ | ✔️ | ✔️ | ✔️ |

✔️ この組み合わせではナッジが抑制される

たとえば、ユーザーがWindows Hello for Business資格情報を持っていて、Chrome でWindowsにサインインした場合、ナッジは抑制されます。 ただし、同じユーザーが Chrome ブラウザーで Mac にサインインすると、その資格情報はこの OS とブラウザーの組み合わせで使用できないため、ナッジされます。

注

Linux ユーザーは、パスキー登録を促すキャンペーンの対象になっていません。

#### パスキー プロファイルがナッジ評価に与える影響

プラットフォーム別のパスキー ナッジ評価は、キャンペーンが [有効] または [Microsoft 管理] のいずれかの状態である場合に適用されます。 評価は、ユーザー用に構成されたパスキー プロファイルにも依存します。 次の表では、ユーザーがスコープ内にあるパスキー プロファイルの種類ごとの動作について説明します。

| パスキー プロファイル構成 | ナッジの評価方法 |
| --- | --- |
| 無制限 | 前の表に従って OS とブラウザーごとに抑制されます。ユーザーがそのプラットフォームに対して適切なローカル パスキーを取得した後。 |
| 同期済みのみ | ユーザーは、可能な限り、各プラットフォームでローカル **同期された** パスキーを登録するように微調整されます。 ユーザーが有効なローカル同期パスキーを使用できるようになった後、プラットフォームではナッジが抑制されます。 |
| デバイスにのみバインド 가능 | ユーザーは、可能な場合は、各プラットフォームでローカル **デバイスバインド** パスキーを登録するように微調整されます。 ユーザーが有効なローカル デバイス バインド パスキーを使用できるようになった後、プラットフォームではナッジが抑制されます。 |
| AAGUID 制限付き | プラットフォームごとの評価は適用されません。 ユーザーが **1 つの適格な** パスキーを登録すると、すべての OS とブラウザーの組み合わせでナッジが停止します。 |
| 構成証明が適用されているデバイスバインド | プラットフォームごとの評価は適用されません。 ユーザーが **1 つの適格な** パスキーを登録すると、すべての OS とブラウザーの組み合わせでナッジが停止します。 |

### Graph エクスプローラーを使用して登録キャンペーン ポリシーを有効にする

Microsoft Entra 管理センターを使用するだけでなく、Graph エクスプローラーを使用して登録キャンペーン ポリシーを有効にすることもできます。 認証方法ポリシー Graph API を使用する必要があります。 少なくとも [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) ロールが割り当てられているユーザーは、ポリシーを更新できます。

Graph Explorer を使用してポリシーを構成するには、次の手順を実行します。

1. [Graph エクスプローラー](https://aka.ms/ge)にサインインし、**Policy.Read.All** および **Policy.ReadWrite.AuthenticationMethod** アクセス許可に同意します。

    [Image: Policy.Read.All と Policy.ReadWrite.AuthenticationMethod が同意されたアクセス許可ペインを示す Graph エクスプローラーを示すスクリーンショット。]
2. 認証方法ポリシーを取得します。

    ```http
    GET https://graph.microsoft.com/v1.0/policies/authenticationmethodspolicy
    ```
3. ポリシーの `registrationEnforcement` と `authenticationMethodsRegistrationCampaign` セクションを更新して、ユーザーまたはグループでナッジを有効にします。

    [Image: 認証方法ポリシーの registrationEnforcement セクションを示す Graph Explorer API 応答を示すスクリーンショット。]

    ポリシーを更新するには、更新された `PATCH` セクションのみを使用して、認証方法ポリシーに対して`registrationEnforcement`を実行します。

    ```http
    PATCH https://graph.microsoft.com/v1.0/policies/authenticationmethodspolicy
    ```

次の表に、 `authenticationMethodsRegistrationCampaign` プロパティの一覧を示します。

| 名前 | 指定できる値 | 説明 |
| --- | --- | --- |
| `snoozeDurationInDays` | 範囲: 0 から 14 | ユーザーが再度注意喚起されるまでの日数を定義します。値が `0` の場合、ユーザーは MFA の試行ごとに再確認を求められます。既定値: 1 日 |
| `enforceRegistrationAfterAllowedSnoozes` | `true``false` | ユーザーが3回スヌーズした後にセットアップを行う必要があるかどうかを指定します。`true`場合、ユーザーは登録する必要があります。`false`場合、ユーザーは無期限にスヌーズできます。既定値: `true` |
| `state` | `enabled``disabled``default` | この機能を有効または無効にすることができます。既定値は、構成が明示的に設定されていない場合に使用され、この設定にMicrosoft Entra ID既定値が使用されます。必要に応じて、状態を `enabled` (すべてのユーザー) または `disabled` に変更します。 |
| `excludeTargets` | 該当しません | 機能の対象としないさまざまなユーザーとグループを除外できます。 ユーザーが除外されたグループと含まれるグループに含まれている場合、そのユーザーは機能から除外されます。 |
| `includeTargets` | 該当しません | 機能の対象にするさまざまなユーザーとグループを含めることができます。 |

次の表に、 `includeTargets` プロパティの一覧を示します。

| 名前 | 指定できる値 | 説明 |
| --- | --- | --- |
| `targetType` | `user``group` | 対象となるエンティティの種類。 |
| `ID` | グローバル一意識別子 (GUID) | 対象となるユーザーまたはグループの ID。 |
| `targetedAuthenticationMethod` | `microsoftAuthenticator``fido2` | ユーザーに登録を促される認証方法。 `microsoftAuthenticator`を使用してユーザーを微調整して Authenticator を設定するか、`fido2`を使用してユーザーを微調整してパスキーを登録します。 |

次の表に、 `excludeTargets` プロパティの一覧を示します。

| 名前 | 指定できる値 | 説明 |
| --- | --- | --- |
| `targetType` | `user``group` | 対象となるエンティティの種類。 |
| `ID` | 文字列 | 対象となるユーザーまたはグループの ID。 |

#### 例

使い始めるには、次のサンプル JSON ボディを使用できます。

- すべてのユーザーを含め、Authenticator を対象とします。

    テナントにすべてのユーザーを含め、Authenticator を設定するように微調整するには、Graph Explorer に次の JSON を貼り付け、エンドポイントで `PATCH` を実行します。

    ```json
    {
    "registrationEnforcement": {
            "authenticationMethodsRegistrationCampaign": {
                "snoozeDurationInDays": 1,
                "enforceRegistrationAfterAllowedSnoozes": true,
                "state": "enabled",
                "excludeTargets": [],
                "includeTargets": [
                    {
                        "id": "all_users",
                        "targetType": "group",
                        "targetedAuthenticationMethod": "microsoftAuthenticator"
                    }
                ]
            }
        }
    }
    ```
- すべてのユーザーを含め、パスキーを対象とします。

    テナント内のすべてのユーザーを対象にし、パスキーを登録するよう促したい場合は、以下の JSON サンプルを更新してください。 次に、Graph エクスプローラーにそれを貼り付けて、エンドポイントで `PATCH` を実行します。

    ```json
    {
    "registrationEnforcement": {
            "authenticationMethodsRegistrationCampaign": {
                "snoozeDurationInDays": 1,
                "enforceRegistrationAfterAllowedSnoozes": true,
                "state": "enabled",
                "excludeTargets": [],
                "includeTargets": [
                    {
                        "id": "all_users",
                        "targetType": "group",
                        "targetedAuthenticationMethod": "fido2"
                    }
                ]
            }
        }
    }
    ```
- 特定のユーザーまたはユーザーのグループを含めます。

    テナントの特定のユーザーまたはグループを含める場合は、ユーザーとグループの関連する GUID を使用して次の JSON の例を更新します。 次に、Graph エクスプローラーにその JSON を貼り付けて、エンドポイントで `PATCH` を実行します。

    ```json
    {
    "registrationEnforcement": {
          "authenticationMethodsRegistrationCampaign": {
              "snoozeDurationInDays": 1,
              "enforceRegistrationAfterAllowedSnoozes": true,
              "state": "enabled",
              "excludeTargets": [],
              "includeTargets": [
                  {
                      "id": "*********PLEASE ENTER GUID***********",
                      "targetType": "group",
                      "targetedAuthenticationMethod": "microsoftAuthenticator"
                  },
                  {
                      "id": "*********PLEASE ENTER GUID***********",
                      "targetType": "user",
                      "targetedAuthenticationMethod": "microsoftAuthenticator"
                  }
              ]
          }
      }
    }  
    ```
- 特定のユーザーまたはグループを含めたり除外したりします。

    テナントに特定のユーザーまたはグループを含めたり除外したりする場合は、次の JSON 例をユーザーとグループの関連 GUID で更新します。 次に、Graph エクスプローラーにそれを貼り付けて、エンドポイントで `PATCH` を実行します。

    ```json
    {
    "registrationEnforcement": {
            "authenticationMethodsRegistrationCampaign": {
                "snoozeDurationInDays": 1,
                "enforceRegistrationAfterAllowedSnoozes": true,
                "state": "enabled",
                "excludeTargets": [
                    {
                        "id": "*********PLEASE ENTER GUID***********",
                        "targetType": "group"
                    },
                  {
                        "id": "*********PLEASE ENTER GUID***********",
                        "targetType": "user"
                    }
                ],
                "includeTargets": [
                    {
                        "id": "*********PLEASE ENTER GUID***********",
                        "targetType": "group",
                        "targetedAuthenticationMethod": "microsoftAuthenticator"
                    },
                    {
                        "id": "*********PLEASE ENTER GUID***********",
                        "targetType": "user",
                        "targetedAuthenticationMethod": "microsoftAuthenticator"
                    }
                ]
            }
        }
    }
    ```

#### JSON 要求本文のユーザー GUID を識別する

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. [ **管理** ] ウィンドウで、[ **ユーザー**] を選択します。
3. [ **ユーザー** ] ページで、対象とする特定のユーザーを特定します。
4. 特定のユーザーを選択すると、そのユーザーのオブジェクト ID (ユーザーの GUID) が表示されます。

    [Image: [オブジェクト ID] フィールドを示すユーザー プロパティ ページを示すスクリーンショット。]

#### JSON 要求本文のグループ GUID を識別する

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. [ **管理** ] ウィンドウで、[ **グループ**] を選択します。
3. [ **グループ** ] ページで、対象とする特定のグループを特定します。
4. グループを選択してオブジェクト ID を取得します。

    [Image: [オブジェクト ID] フィールドを示す [グループのプロパティ] ページを示すスクリーンショット。]

### よく寄せられる質問

#### Enabled と Microsoft マネージド状態の違いは何ですか?

**[有効]** 状態では、キャンペーンを自分で構成します。対象となる方法、再通知期間、再通知の制限、含めるユーザーまたは除外するユーザーです。 **Microsoftマネージド**状態では、Microsoftはベスト プラクティスに基づいてターゲット メソッド、再通知期間、再通知の制限を設定し、最新の状態に保ちます。 どちらの状態でも、インクルード/除外ターゲットを設定できます。 完全な比較については、「 キャンペーンの状態を選択する」を参照してください。

#### スコープを設定したユーザーが、マイクロソフト管理下のパスキーに対して微調整されないのはなぜですか?

Microsoft による管理対象のパスキー ターゲティングでは、対象ユーザーは、少なくとも 1 つの対象となるパスキー プロファイルに含まれている場合にのみ促されます。 ユーザーの唯一のパスキー プロファイルが条件を満たしていない場合 (たとえば、許可リストにサポートされている AAGUID が含まれていない AAGUID 制限付きプロファイル)、それらは微調整されません。 **有効**な状態では、パスキーのターゲット設定では、これらのプロファイル チェックは適用されません。 ルールについては、Microsoft マネージド登録キャンペーンにおけるパスキー プロファイルの適格性を参照してください。

#### ユーザーはアプリケーション内で微調整できますか?

はい。 登録キャンペーンでは、特定のアプリケーションの埋め込みブラウザー ビューがサポートされます。 このキャンペーンでは、初期設定時のエクスペリエンスや、Windows の設定に埋め込まれたブラウザー表示内でユーザーに働きかけることはありません。

#### SSO セッション内でユーザーを微調整できますか?

ユーザーが SSO で既にサインインしている場合、ナッジはトリガーされません。

#### ユーザーはモバイル デバイスで微調整できますか?

登録キャンペーンによって異なります。

- Microsoft Authenticator登録キャンペーンは、モバイル デバイスではサポートされていません。
- Passkey 登録キャンペーンは、次のようなモバイル デバイスでサポートされています。

    - モバイル デバイスでのブラウザー ベースのエクスペリエンス。
    - ネイティブ iOS モバイル アプリ。 ネイティブ Android モバイル アプリのサポートは現在利用できません。

#### キャンペーンの実行期間はどのくらいですか?

キャンペーンは、必要な期間有効にすることができます。 キャンペーンの実行が完了したら、管理センターまたは API を使用してキャンペーンを無効にします。

#### ユーザーのグループごとに異なる再通知期間を設定できますか?

いいえ。 プロンプトのスヌーズ期間はテナント全体の設定で、すべてのグループに適用されます。

#### ユーザーが登録をスキップできないようにする場合はどうすればよいでしょうか。

**再通知を許可する日数**を `0` に設定し、**スヌーズ回数の制限**を **有効** に設定します。 ユーザーは引き続き最大 3 回まで再通知することができますが、次に MFA を完了した際に再度求められます。 3回目のスヌーズ後、登録が必要です。 これらの設定は、キャンペーンの構成を制御する **有効** な状態で使用できます。

#### パスワードレスの電話によるサインインをセットアップするようにユーザーにナッジできますか?

登録キャンペーン機能では、Authenticator を使用して MFA を設定したり、パスキーを登録したりするためのユーザーの微調整がサポートされています。 パスワードレス電話によるサインインは、登録キャンペーンの対象となる方法ではありません。

#### Microsoft以外の認証アプリでサインインしたユーザーにナッジが表示されますか?

これは、対象となる認証方法によって異なります。 パスキー キャンペーンでは、ユーザーがそのほかの適格要件を満たしている場合、Microsoft 以外の認証アプリを使用して MFA を行った後に、ユーザーにパスキーの使用を促すことができます。 Authenticator キャンペーンでは、SMS または音声通話による MFA の後にのみユーザーにプロンプトが表示されます。

#### 時間ベースのワンタイム パスワード コードに対してのみ Authenticator が設定されているユーザーには、ナッジが表示されますか?

Authenticator がプッシュ通知用に設定されていない場合、ユーザーは Authenticator 登録キャンペーンの対象となります。 ただし、プロンプトは、ユーザーが時間ベースのワンタイム パスワード コードを使用した後ではなく、SMS または音声呼び出しによる MFA を完了した後にのみ表示されます。

#### 既にパスキーを持っているユーザーにはナッジが表示されますか?

passkey ナッジは、ユーザーが現在の OS とブラウザーの組み合わせに対してローカル パスキーを持っているかどうかを評価します。 ユーザーがそのエクスペリエンス用のローカルパスキーを既に持っている場合、促されることはありません。 このため、あるデバイスではユーザーに促しが表示されても、別のデバイスでは表示されないことがあります。 プラットフォーム固有の情報については、「 プラットフォーム別の Passkey ナッジの評価 」セクションを参照してください。

#### Authenticator と passkey の両方の登録キャンペーンを同時に実行できますか?

いいえ。 登録キャンペーンでは、一度に 1 つの認証方法のみを対象にすることができます。 Authenticator または passkey をターゲットにすることはできますが、同じテナント内で両方を同時にターゲットにすることはできません。

#### ユーザーが MFA 登録を通過したばかりの場合、ユーザーは同じサインイン セッションでナッジされますか?

いいえ。 優れたユーザー エクスペリエンスを提供するために、ユーザーは他の認証方法を登録したのと同じセッションで Authenticator を設定するように微調整されません。

#### 別の認証方法を登録するようにユーザーを微調整できますか?

はい。 登録キャンペーンでは、Authenticator を設定したり、パスキー (FIDO2) を登録したりするためのユーザーの微調整がサポートされています。 キャンペーンを構成するときに、対象となる認証方法を選択します。

#### 再通知オプションを非表示にして、ユーザーに Authenticator の設定を要求することはできますか?

再通知オプションをすぐに非表示にすることはできません。 **スヌーズ回数の制限** を **有効** に設定すると、ユーザーはセットアップを最大 3 回まで先送りできます。それ以降はセットアップが必要になります。

#### Microsoft Entra MFA を使用していない場合でも、ユーザーに通知できますか?

いいえ。 ナッジは、Microsoft Entra MFA を使用して MFA を実行しているユーザーに対してのみ機能します。

#### テナント内のゲスト ユーザーは微調整されますか?

Authenticator の登録キャンペーンの対象になっている場合、通知が表示されます。 ゲスト ユーザー向けのパスキー サポートは現在利用できないため、パスキーの登録キャンペーンの対象に含まれていても、これらのユーザーには登録を促す通知は表示されません。

#### ユーザーがブラウザーを閉じた場合はどうしますか?

ブラウザを閉じることは、スヌーズすることと同じです。 ユーザーが 3 回スヌーズした後に設定が必要となる場合、そのユーザーが次にサインインした際にナッジされます。

#### "セキュリティ情報の登録" の条件付きアクセス ポリシーがある場合、一部のユーザーにナッジが表示されないのはなぜですか?

ユーザーが [ **セキュリティ情報の登録** ] ページへのアクセスをブロックする条件付きアクセス ポリシーのスコープ内にある場合、ナッジは表示されません。

#### サインイン中に使用条件画面が表示されると、ユーザーにナッジが表示されますか?

サインイン中に [利用規約](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/terms-of-use) 画面が表示された場合、ナッジは表示されません。

#### 条件付きアクセスのカスタム コントロールがサインインに適用されるときに、ユーザーにナッジが表示されますか?

[条件付きアクセスのカスタム コントロール](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/controls)設定が原因でサインイン中にユーザーがリダイレクトされた場合、ナッジは表示されません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-mfa-server-migration-utility"} -->
## MFA Server Migration Utility を使用して Microsoft Entra 多要素認証に移行する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-server-migration-utility
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: MFA サーバー移行ユーティリティを使用して MFA サーバーの設定を Microsoft Entra ID に移行するための詳細なガイダンス。

このトピックでは、Microsoft Entra ユーザーの MFA 設定をオンプレミスの Microsoft Entra 多要素認証サーバーから Microsoft Entra 多要素認証に移行する方法について説明します。

### ソリューションの概要

MFA Server Migration Utility は、オンプレミスの Microsoft Entra 多要素認証サーバーに格納されている多要素認証データを Microsoft Entra 多要素認証に直接同期するのに役立ちます。 認証データが Microsoft Entra ID に移行されると、ユーザーは再登録や認証方法の確認を行うことなく、クラウドベースの MFA をシームレスに実行できます。 管理者は、MFA Server Migration Utility を使用して、テナント全体の変更を加えることなく、単一のユーザーまたはユーザー グループをテストおよび制御されたロールアウトの対象にすることができます。

### ビデオ: MFA サーバー移行ユーティリティを使用する方法

MFA Server 移行ユーティリティの概要とそのしくみについては、ビデオをご覧ください。

### 制限事項と要件

- MFA サーバー移行ユーティリティでは、プライマリ MFA サーバーに MFA Server ソリューションの新しいビルドをインストールする必要があります。 このビルドでは、MFA Server データ ファイルが更新され、新しい MFA Server 移行ユーティリティが含まれます。 WebSDK またはユーザー ポータルを更新する必要はありません。 この更新をインストールしても、移行は自動的には開始 "されません"。

    手記

    MFA サーバー移行ユーティリティは、セカンダリ MFA サーバーで実行できます。 詳細については、「 セカンダリ MFA サーバーを実行する (省略可能)」を参照してください。
- MFA サーバー移行ユーティリティは、データベース ファイルから Microsoft Entra ID のユーザー オブジェクトにデータをコピーします。 移行中、ユーザーは [段階的ロールアウト](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout)を使用してテスト目的で Microsoft Entra 多要素認証を対象にすることができます。 段階的な移行を使用すると、ドメイン フェデレーション設定を変更せずにテストできます。 移行が完了したら、ドメイン フェデレーション設定を変更して移行を完了する必要があります。
- Windows Server 2016 以降を実行している AD FS は、Microsoft Entra ID や Office 365 を含まない AD FS 証明書利用者に MFA 認証を提供するために必要です。
- AD FS アクセス制御ポリシーを確認し、認証プロセスの一環としてオンプレミスで MFA を実行する必要がないことを確認します。
- 段階的なロールアウトでは、最大 500,000 人のユーザー (10 グループにそれぞれ最大 50,000 ユーザーを含む) を対象にすることができます。

### 移行ガイド

| 段階 | ステップス |
| --- | --- |
| 準備 | Microsoft Entra 多要素認証サーバーの依存関係を特定する |
|  | Microsoft Entra 多要素認証サーバー データファイルのバックアップ |
|  | MFA サーバーの更新プログラムをインストールする |
|  | MFA サーバー移行ユーティリティの構成 |
| 移行 | ユーザー データを移行する |
|  | の検証とテスト |
|  | 段階的ロールアウト |
|  | ユーザーを教育する |
|  | ユーザーの移行を完了する |
| 最終 | MFA サーバーの依存関係を移行する |
|  | ドメイン フェデレーション設定を更新する |
|  | MFA サーバー ユーザー ポータルを無効にする |
|  | MFA サーバーの使用停止 |

MFA サーバーの移行には、通常、次のプロセスの手順が含まれます。

[Image: MFA Server 移行フェーズの図。]

いくつかの重要な点:

テスト ユーザーを追加するときは、**フェーズ 1** を繰り返す必要があります。

- 移行ツールは、MFA Server と Microsoft Entra 多要素認証の間で認証データを同期する必要があるユーザーを決定するために Microsoft Entra グループを使用します。 ユーザー データが同期されると、そのユーザーは Microsoft Entra 多要素認証を使用できるようになります。
- 段階的ロールアウトを使用すると、Microsoft Entra グループを使用して、ユーザーを Microsoft Entra 多要素認証に再ルーティングできます。 両方のツールで同じグループを使用することは可能ですが、その方法はお勧めしません。ユーザーがデータが同期される前に Microsoft Entra 多要素認証にリダイレクトされる可能性があるためです。 MFA Server Migration Utility によって認証データを同期するための Microsoft Entra グループと、対象ユーザーをオンプレミスではなく Microsoft Entra 多要素認証に誘導するための段階的ロールアウト用の別のグループセットを設定することをお勧めします。

ユーザー ベースを移行するときは、**フェーズ 2** を繰り返す必要があります。 フェーズ 2 の終わりまでに、ユーザー ベース全体で、Microsoft Entra ID に対してフェデレーションされたすべてのワークロードに対して Microsoft Entra 多要素認証を使用する必要があります。

前のフェーズでは、段階的ロールアウト フォルダーからユーザーを削除して、Microsoft Entra 多要素認証のスコープから除外し、Microsoft Entra ID から送信されるすべての MFA 要求に対してオンプレミスの Microsoft Entra 多要素認証サーバーにルーティングすることができます。

フェーズ 3、オンプレミスの MFA Server (VPN、パスワード マネージャーなど) に対して認証されるすべてのクライアントを SAML/OAUTH 経由で Microsoft Entra フェデレーションに移行する必要があります。 先進認証標準がサポートされていない場合は、Microsoft Entra 多要素認証拡張機能がインストールされている NPS サーバーを立ち上げる必要があります。 依存関係が移行されると、ユーザーは MFA サーバーでユーザー ポータルを使用しなくなりますが、Microsoft Entra ID (https://aka.ms/mfasetup) で認証方法を管理する必要があります。 ユーザーが Microsoft Entra ID で認証データの管理を開始すると、それらのメソッドは MFA Server に同期されません。 ユーザーが Microsoft Entra ID で認証方法を変更した後にオンプレミスの MFA サーバーにロールバックすると、それらの変更は失われます。 ユーザーの移行が完了したら、 [federatedIdpMfaBehavior](https://learn.microsoft.com/ja-jp/graph/api/resources/internaldomainfederation?view=graph-rest-1.0&preserve-view=true#federatedidpmfabehavior-values) ドメインのフェデレーション設定を変更します。 この変更により、Microsoft Entra ID は、グループ メンバーシップに関係なく、オンプレミスで MFA を実行しなくなり、Microsoft Entra 多要素認証を使用してすべての MFA 要求を 実行するように指示されます。

以降のセクションでは、移行手順について詳しく説明します。

#### Microsoft Entra 多要素認証サーバーの依存関係を特定する

Microsoft は、クラウドベースの Microsoft Entra 多要素認証ソリューションに移行することで、セキュリティ体制を維持および改善できるように努力してきました。 依存関係のグループ化には、次の 3 つの大きなカテゴリを使用する必要があります。

- MFA メソッド
- ユーザー ポータル
- 認証サービス

移行を支援するために、広く使用されている MFA Server の機能と、カテゴリごとに Microsoft Entra 多要素認証に相当する機能を照合しました。

##### MFA メソッド

MFA Server を開き、[会社の設定]選択します。

[Image: [会社の設定] のスクリーンショット。]

| MFA サーバー | Microsoft Entra 多要素認証 |
| --- | --- |
| **[全般] タブ** |  |
| **[ユーザーの既定値] セクション** |  |
| 電話 (標準) | アクションは必要ありません |
| テキスト メッセージ (OTP)^\*^ | アクションは必要ありません |
| モバイル アプリ (Standard) | アクションは必要ありません |
| 電話通話 (PIN)^\*^ | 音声 OTP を有効にする |
| テキスト メッセージ (OTP + PIN)^\*\*^ | アクションは必要ありません |
| モバイル アプリ (PIN)^\*^ | [番号照合を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-number-match)有効にする |
| 電話/テキスト メッセージ/モバイル アプリ/OATH トークン言語 | 言語設定は、ブラウザーのロケール設定に基づいてユーザーに自動的に適用されます |
| **[既定の PIN 規則] セクション** | 適用されません。前のスクリーンショットの更新されたメソッドを参照してください |
| **[ユーザー名の解決] タブ** | 適用されません。Microsoft Entra 多要素認証では、ユーザー名の解決は必要ありません |
| **[テキスト メッセージ] タブ** | 適用されません。Microsoft Entra 多要素認証では、テキスト メッセージに既定のメッセージが使用されます |
| [OATH トークン] タブ | 適用されません。Microsoft Entra 多要素認証では、OATH トークンに既定のメッセージが使用されます |
| レポート | [Microsoft Entra 認証方法アクティビティ レポート](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-methods-activity) |

^\*^PIN を使用してプレゼンス証明機能を提供する場合は、上記で同等の機能が提供されます。 暗号化によってデバイスに関連付けられていない PIN では、デバイスが侵害されたシナリオから十分に保護されません。 SIM スワップ攻撃など、これらのシナリオから保護するために、のベスト プラクティス Microsoft 認証方法に従って、より安全な方法にユーザーを移動します。

^\*\*^Microsoft Entra 多要素認証の既定のテキスト MFA エクスペリエンスは、認証の一部としてログイン ウィンドウに入力する必要があるコードをユーザーに送信します。 コードをラウンドトリップする要求により、存在証明機能が提供されます。

##### ユーザー ポータル

MFA サーバーを開き、ユーザー ポータル選択します。

[Image: ユーザー ポータルのスクリーンショット。]

| MFA サーバー | Microsoft Entra 多要素認証 |
| --- | --- |
| **[設定] タブ** |  |
| ユーザー ポータルの URL | https://aka.ms/mfasetup |
| ユーザー登録を許可する | 統合されたセキュリティ情報の登録  を参照してください |
| - バックアップ電話の入力を求めるメッセージ | MFA サービス設定  を参照してください |
| - サード パーティの OATH トークンの入力を求める | MFA サービス設定  を参照してください |
| ユーザーが One-Time バイパスを開始できるようにする | Microsoft Entra ID TAP 機能  を参照してください |
| ユーザーによる方法の選択を許可する | MFA サービス設定  を参照してください |
| -電話 | 電話の記録に関するドキュメントを参照 |
| -テキストメッセージ | MFA サービス設定  を参照してください |
| - モバイル アプリ | MFA サービス設定  を参照してください |
| - OATH トークン | OATH トークンのドキュメント  を参照してください |
| ユーザーが言語を選択できるようにする | 言語設定は、ブラウザーのロケール設定に基づいてユーザーに自動的に適用されます |
| ユーザーによるモバイル アプリのアクティブ化を許可する | MFA サービス設定  を参照してください |
| - デバイスの制限 | Microsoft Entra ID では、ユーザー 1 人あたり 5 つの累積デバイス (モバイル アプリ インスタンス + ハードウェア OATH トークン + ソフトウェア OATH トークン) にユーザーを制限します |
| 代替認証にセキュリティの質問を使用する | Microsoft Entra ID を使用すると、選択した認証方法が失敗した場合に、ユーザーは認証時にフォールバック方法を選択できます |
| - 回答する質問 | Microsoft Entra ID のセキュリティの質問は、SSPR にのみ使用できます。 Microsoft Entra のカスタムセキュリティ質問の詳細を参照してください |
| ユーザーがサード パーティの OATH トークンを関連付けることができるようにする | OATH トークンのドキュメント  を参照してください |
| フォールバックに OATH トークンを使用する | OATH トークンのドキュメント  を参照してください |
| セッション タイムアウト |  |
| **[セキュリティの質問] タブ** | MFA Server のセキュリティに関する質問は、ユーザー ポータルにアクセスするために使用されました。 Microsoft Entra 多要素認証では、セルフサービス パスワード リセットに関するセキュリティの質問のみがサポートされます。 [セキュリティに関する質問のドキュメントを参照してください](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-security-questions)。 |
| **[Passed Sessions]\(終了したセッション\) タブ** | すべての認証方法の登録フローは Microsoft Entra ID によって管理され、構成は必要ありません |
| **信頼できる IP** | [Microsoft Entra ID の信頼できる IP](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-mfasettings#trusted-ips) |

MFA Server で使用できる MFA メソッドは、MFA [サービス設定](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-mfasettings#mfa-service-settings)を使用して Microsoft Entra 多要素認証で有効にする必要があります。 ユーザーは、有効になっていない限り、新しく移行された MFA メソッドを試すことはできません。

##### 認証サービス

Microsoft Entra 多要素認証サーバーは、認証プロキシとして機能することで、RADIUS または LDAP を使用するサードパーティ ソリューションに MFA 機能を提供できます。 RADIUS または LDAP の依存関係を検出するには、MFA Server で RADIUS 認証選択し、LDAP 認証 オプションを します。 これらの依存関係ごとに、これらのサード パーティが先進認証をサポートしているかどうかを判断します。 その場合は、Microsoft Entra ID との直接フェデレーションを検討してください。

アップグレードできない RADIUS 展開の場合は、NPS サーバーを展開し、 [Microsoft Entra 多要素認証 NPS 拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-nps-extension)をインストールする必要があります。

アップグレードまたは RADIUS への移動ができない LDAP デプロイの場合は、 [Microsoft Entra Domain Services を使用できるかどうかを判断](https://learn.microsoft.com/ja-jp/entra/architecture/auth-ldap)します。 ほとんどの場合、LDAP はエンド ユーザーのインライン パスワード変更をサポートするためにデプロイされました。 移行後、エンド ユーザーは Microsoft Entra IDでセルフサービス パスワード リセット 使用してパスワードを管理できます。

Office 365 証明書利用者信頼を除く証明書利用者信頼に対して [AD FS 2.0 で MFA サーバー認証プロバイダー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfaserver-adfs-windows-server#secure-windows-server-ad-fs-with-azure-multi-factor-authentication-server) を有効にした場合は、 [AD FS 3.0](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/deployment/upgrading-to-ad-fs-in-windows-server) にアップグレードするか、最新の認証方法をサポートしている場合は、それらの証明書利用者を Microsoft Entra ID に直接フェデレーションする必要があります。 各依存関係に最適なアクション計画を決定します。

#### Microsoft Entra 多要素認証サーバー データファイルのバックアップ

プライマリ MFA サーバーの \Multi-Factor Authentication Server\Data\PhoneFactor.pfdata (既定の場所) %programfiles%にある MFA Server データ ファイルのバックアップを作成します。 ロールバックする必要がある場合は、現在インストールされているバージョンのインストーラーのコピーがあることを確認してください。 コピーがなくなった場合は、カスタマー サポート サービスにお問い合わせください。

ユーザー アクティビティによっては、データ ファイルがすぐに古くなる可能性があります。 MFA Server に加えられた変更、またはバックアップ後にポータルを通じて行われたエンドユーザーの変更はキャプチャされません。 ロールバックした場合、この時点以降に行われた変更は復元されません。

#### MFA サーバーの更新プログラムをインストールする

プライマリ MFA サーバーで新しいインストーラーを実行します。 サーバーをアップグレードする前に、負荷分散または他の MFA サーバーとのトラフィック共有からサーバーを削除します。 インストーラーを実行する前に、現在の MFA サーバーをアンインストールする必要はありません。 インストーラーは、現在のインストール パス (C:\Program Files\Multi-Factor Authentication Server など) を使用してインプレース アップグレードを実行します。 Microsoft Visual C++ 2015 再頒布可能更新プログラム パッケージのインストールを求められた場合は、プロンプトを受け入れます。 パッケージの x86 バージョンと x64 バージョンの両方がインストールされます。 ユーザー ポータル、Web SDK、または AD FS アダプターの更新プログラムをインストールする必要はありません。

手記

プライマリ サーバーでインストーラーを実行すると、セカンダリ サーバーが未処理の SB エントリ ログに記録し始める場合があります。 これは、セカンダリ サーバーで認識されないプライマリ サーバーで行われたスキーマの変更が原因です。 これらのエラーが予想されます。 ユーザー数が 10,000 人以上の環境では、ログ エントリの量が大幅に増加する可能性があります。 この問題を軽減するには、MFA サーバー ログのファイル サイズを増やすか、セカンダリ サーバーをアップグレードします。

#### MFA サーバー移行ユーティリティを構成する

MFA Server の更新プログラムをインストールしたら、管理者特権の PowerShell コマンド プロンプトを開きます。PowerShell アイコンにカーソルを合わせ、右選択して、[管理者として実行]選択します。 を実行します。\Configure-MultiFactorAuthMigrationUtility.ps1 MFA Server インストール ディレクトリ (既定では C:\Program Files\Multi-Factor Authentication Server) にあるスクリプトです。

このスクリプトでは、Microsoft Entra テナントのアプリケーション管理者の資格情報を指定する必要があります。 Microsoft Entra ID 内に新しい MFA Server Migration Utility アプリケーションが作成されます。これは、各 Microsoft Entra ユーザー オブジェクトにユーザー認証方法を記述するために使用されます。

移行を実行する政府機関向けクラウドのお客様の場合は、スクリプト内の ".com" エントリを ".us" に置き換えます。 このスクリプトは、HKLM:\SOFTWARE\WOW6432Node\Positive Networks\PhoneFactor\ StsUrl および GraphUrl レジストリ エントリを書き込み、適切な GRAPH エンドポイントを使用するように移行ユーティリティに指示します。

次の URL にもアクセスする必要があります。

- `https://graph.microsoft.com/*` (または政府機関向けクラウドのお客様向けの `https://graph.microsoft.us/*`)
- `https://login.microsoftonline.com/*` (または政府機関向けクラウドのお客様向けの `https://login.microsoftonline.us/*`)

このスクリプトは、新しく作成されたアプリケーションに管理者の同意を付与するように指示します。 指定された URL に移動するか、Microsoft Entra 管理センター内で、[アプリケーションの登録]選択し、MFA Server Migration Utility アプリを見つけて選択し、API のアクセス許可 を選択し、適切なアクセス許可を付与します。

[Image: アクセス許可のスクリーンショット。]

完了したら、Multi-Factor Authentication Server フォルダーに移動し、 **MultiFactorAuthMigrationUtilityUI アプリケーションを** 開きます。 次の画面が表示されます。

[Image: MFA サーバー移行ユーティリティのスクリーンショット。]

移行ユーティリティが正常にインストールされました。

手記

移行中に動作が変更されないようにするには、MFA サーバーがテナント参照のない MFA プロバイダーに関連付けられている場合は、移行するテナントの既定の MFA 設定 (カスタム あいさつなど) を MFA プロバイダーの設定と一致するように更新する必要があります。 ユーザーを移行する前に、これを行うことをお勧めします。

#### セカンダリ MFA サーバーを実行する (省略可能)

MFA Server の実装に多数のユーザーまたはビジー状態のプライマリ MFA サーバーがある場合は、MFA Server 移行ユーティリティと移行同期サービスを実行するための専用のセカンダリ MFA サーバーをデプロイすることを検討してください。 プライマリ MFA サーバーをアップグレードした後、既存のセカンダリ サーバーをアップグレードするか、新しいセカンダリ サーバーをデプロイします。 選択したセカンダリ サーバーが他の MFA トラフィックを処理しないようにする必要があります。

Configure-MultiFactorAuthMigrationUtility.ps1 スクリプトは、セカンダリ サーバーで実行して、MFA Server Migration Utility アプリの登録に証明書を登録する必要があります。 証明書は、Microsoft Graph に対する認証に使用されます。 セカンダリ MFA サーバーで移行ユーティリティと同期サービスを実行すると、手動と自動の両方のユーザー移行のパフォーマンスが向上します。

#### ユーザー データを移行する

ユーザー データを移行しても、Multi-Factor Authentication Server データベース内のデータは削除または変更されません。 同様に、このプロセスでは、ユーザーが MFA を実行する場所は変更されません。 このプロセスは、オンプレミス サーバーから Microsoft Entra ID の対応するユーザー オブジェクトへのデータの一方向コピーです。

MFA Server Migration ユーティリティは、すべての移行アクティビティに対して 1 つの Microsoft Entra グループを対象としています。 このグループにユーザーを直接追加することも、他のグループを追加することもできます。 移行中に段階的に追加することもできます。

移行プロセスを開始するには、移行する Microsoft Entra グループの名前または GUID を入力します。 完了したら、Tab キーを押すか、ウィンドウの外側を選択して適切なグループの検索を開始します。 グループ内のすべてのユーザーが設定されます。 大規模なグループの完了には数分かかる場合があります。

ユーザーの属性データを表示するには、ユーザーを強調表示し、表示 選択します。

[Image: 使用設定を表示する方法のスクリーンショット。]

このウィンドウには、Microsoft Entra ID とオンプレミス MFA サーバーの両方で、選択したユーザーの属性が表示されます。 このウィンドウを使用すると、移行後にユーザーにデータがどのように書き込まれたかを表示できます。

**[設定]** オプションを使用すると、移行プロセスの設定を変更できます。

[Image: 設定のスクリーンショット。]

- 移行 – ユーザーの既定の認証方法を移行するには、次の 3 つのオプションがあります。

    - 常に移行すべきです
    - Microsoft Entra ID でまだ設定されていない場合にのみ移行する
    - Microsoft Entra ID でまだ設定されていない場合は、使用可能な最も安全な方法に設定します

    これらのオプションは、既定の方法を移行するときに柔軟性を提供します。 さらに、移行中に認証方法ポリシーがチェックされます。 移行される既定のメソッドがポリシーで許可されていない場合は、代わりに使用可能な最も安全な方法に設定されます。
- ユーザー マッチ – 既定の userPrincipalName との一致の代わりに、Microsoft Entra UPN を照合するための別のオンプレミス Active Directory 属性を指定できます。

    - 移行ユーティリティは、オンプレミスの Active Directory 属性を使用する前に、UPN への直接照合を試みます。
    - 一致するものが見つからない場合は、Windows API を呼び出して Microsoft Entra UPN を検索し、SID を取得します。SID は MFA Server ユーザー リストの検索に使用されます。
    - Windows API でユーザーが見つからない場合、または SID が MFA サーバーで見つからない場合は、構成された Active Directory 属性を使用してオンプレミスの Active Directory でユーザーを検索し、SID を使用して MFA サーバーのユーザー一覧を検索します。
- 自動同期 – オンプレミスの MFA サーバー内のユーザーに対する認証方法の変更を継続的に監視し、定義された期間に Microsoft Entra ID に書き込むバックグラウンド サービスを開始します。
- 同期サーバー – MFA Server 移行同期サービスをプライマリでのみ実行するのではなく、セカンダリ MFA サーバーで実行できるようにします。 セカンダリ サーバーで実行するように移行同期サービスを構成するには、MFA Server Migration Utility アプリの登録に証明書を登録するために、`Configure-MultiFactorAuthMigrationUtility.ps1` スクリプトをサーバー上で実行する必要があります。 証明書は、Microsoft Graph に対する認証に使用されます。

移行プロセスは、自動でも手動でもかまいません。

手動プロセスの手順は次のとおりです。

1. ユーザーの移行プロセスまたは複数のユーザーの選択を開始するには、Ctrl キーを押しながら、移行する各ユーザーを選択します。
2. 目的のユーザーを選択した後、**ユーザーの移行**&gt;**選択されたユーザー**&gt;OK を選択**します**。
3. グループ内のすべてのユーザーを移行するには、[**ユーザーの移行**&gt;**Microsoft Entra グループ内のすべてのユーザー**&gt;**OK**] を選択します。
4. 変更されていない場合でも、ユーザーを移行できます。 既定では、ユーティリティは を変更したユーザーのみを移行するように設定されます。 [ **すべてのユーザーの移行** ] を選択して、変更されていない以前に移行したユーザーを再移行します。 変更されていないユーザーの移行は、管理者がユーザーの Microsoft Entra 多要素認証設定をリセットする必要があり、再移行する必要がある場合に、テスト中に役立ちます。

    [Image: [ユーザーの移行] ダイアログのスクリーンショット。]

自動プロセスでは、[設定]で [自動同期選択し、すべてのユーザーを同期するか、特定の Microsoft Entra グループのメンバーのみを同期するかを選択します。

次の表に、さまざまなメソッドの同期ロジックを示します。

| 方式 | 論理 |
| --- | --- |
| **電話** | 内線番号がない場合は、MFA 電話を更新します。内線番号がある場合は、オフィスの電話を更新してください。 例外: 既定の方法がテキスト メッセージの場合は、拡張機能を削除し、MFA 電話を更新します。 |
| バックアップ用の電話 | 内線番号がない場合は、代替電話を更新します。内線番号がある場合は、オフィスの電話を更新してください。例外: 電話とバックアップ電話の両方に内線番号がある場合は、バックアップ電話をスキップします。 |
| **モバイル アプリ** | 最大 5 つのデバイスが移行されます。ユーザーがハードウェア OATH トークンを持っている場合は 4 つだけです。同じ名前のデバイスが複数ある場合は、最新のデバイスのみを移行します。デバイスは最新から最も古い順に並べ替えられます。Microsoft Entra ID に既にデバイスが存在する場合は、OATH トークン秘密鍵と照合して、それに基づいて更新を行います。- OATH トークン シークレット キーに一致するものがない場合は、デバイス トークンで一致します-- 見つかった場合は、OATH Token メソッドを機能させるために MFA サーバー デバイスのソフトウェア OATH トークンを作成します。 通知は、既存の Microsoft Entra 多要素認証デバイスを使用して引き続き機能します。-- 見つからない場合は、新しいデバイスを作成します。新しいデバイスの追加が 5 つのデバイスの制限を超えた場合、デバイスはスキップされます。 |
| **OATH トークン** | Microsoft Entra ID に既にデバイスが存在する場合は、OATH トークン秘密鍵と照合して、それに基づいて更新を行います。- 見つからない場合は、新しいハードウェア OATH トークン デバイスを追加します。新しいデバイスの追加が 5 つのデバイスの制限を超えた場合、OATH トークンはスキップされます。 |

MFA メソッドは、移行された内容に基づいて更新され、既定の方法が設定されます。 MFA Server は最後の移行タイムスタンプを追跡し、ユーザーの MFA 設定が変更された場合、または管理者が **[設定]** ダイアログで移行する内容を変更した場合にのみ、ユーザーを再度移行します。

テスト中は、最初に手動移行を行い、特定の数のユーザーが期待どおりに動作するようにテストすることをお勧めします。 テストが成功したら、移行する Microsoft Entra グループの自動同期を有効にします。 このグループにユーザーを追加すると、ユーザーの情報は自動的に Microsoft Entra ID に同期されます。 MFA Server Migration Utility は 1 つの Microsoft Entra グループを対象としますが、そのグループには、ユーザーと入れ子になったユーザーのグループの両方を含めることができます。

完了すると、完了したタスクが確認されます。

[Image: 確認のスクリーンショット。]

確認メッセージで説明したように、移行されたデータが Microsoft Entra ID 内のユーザー オブジェクトに表示されるまでに数分かかる場合があります。 ユーザーは、 https://aka.ms/mfasetup に移動して、移行されたメソッドを表示できます。

ヒント

Microsoft Entra MFA メソッドを表示する必要がない場合は、グループの表示に必要な時間を短縮できます。 **[表示**&gt;**Azure AD MFA メソッド**]を選択して、**AAD Default**、**AAD Phone**、**AAD Alternate**、**AAD Office**、**AAD Devices**、**AAD OATH Token**の列の表示を切り替えます。 列が非表示の場合、一部の Microsoft Graph API 呼び出しはスキップされるため、ユーザーの読み込み時間が大幅に短縮されます。

##### 移行の詳細を表示する

監査ログまたは Log Analytics を使用して、MFA Server から Microsoft Entra への多要素認証ユーザー移行の詳細を表示できます。

###### 監査ログを使用する

Microsoft Entra 管理センターの監査ログにアクセスして、MFA Server から Microsoft Entra 多要素認証へのユーザー移行の詳細を表示するには、次の手順に従います。

1. 少なくとも[認証管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator)にサインインします。
2. **Entra ID**&gt;**監視と健康**&gt;**監査ログ**に移動します。 ログをフィルター処理するには、[ **フィルターの追加]** を選択します。

    [Image: フィルターを追加する方法のスクリーンショット。]
3. [**開始者 (アクター)**] を選択し、[**適用**] を選択します。

    [Image: [Initiated by Actor](https://learn.microsoft.com/ja-jp/entra/identity/authentication/アクターによって開始) オプションのスクリーンショット。]
4. *「Microsoft Entra 多要素認証管理*」と入力し、[**適用**] を選択します。

    [Image: MFA 管理オプションのスクリーンショット。]
5. このフィルターには、MFA Server Migration Utility ログのみが表示されます。 ユーザー移行の詳細を表示するには、行を選択し、[変更されたプロパティ] タブ 選択します。このタブには、登録済みの MFA メソッドと電話番号の変更が表示されます。

    [Image: ユーザー移行の詳細のスクリーンショット。]

    次の表に、各コードの認証方法を示します。

    | Code | 方式 |
    | --- | --- |
    | 0 | Voice モバイル |
    | 2 | 音声オフィス |
    | 3 | 音声代替モバイル |
    | 5 | SMS |
    | 6 | Microsoft Authenticator プッシュ通知 |
    | 7 | ハードウェアまたはソフトウェア トークン OTP |
6. ユーザー デバイスが移行された場合は、別のログ エントリがあります。

    [Image: 移行されたデバイスのスクリーンショット。]

###### Log Analytics を使用する

MFA Server から Microsoft Entra への多要素認証ユーザー移行の詳細は、Log Analytics を使用して照会することもできます。

```kusto
AuditLogs
| where ActivityDateTime > ago(7d)
| extend InitiatedBy = tostring(InitiatedBy["app"]["displayName"])
| where InitiatedBy == "Microsoft Entra multifactor authentication Management"
| extend UserObjectId = tostring(TargetResources[0]["id"])
| extend Upn = tostring(TargetResources[0]["userPrincipalName"])
| extend ModifiedProperties = TargetResources[0]["modifiedProperties"]
| project ActivityDateTime, InitiatedBy, UserObjectId, Upn, ModifiedProperties
| order by ActivityDateTime asc
```

このスクリーンショットは、ユーザー移行の変更を示しています。

[Image: 移行されたユーザーの Log Analytics のスクリーンショット。]

このスクリーンショットは、デバイス移行の変更を示しています。

[Image: 移行されたデバイスの Log Analytics のスクリーンショット。]

Log Analytics を使用して、ユーザーの移行アクティビティを集計することもできます。

```kusto
AuditLogs
| where ActivityDateTime > ago(7d)
| extend InitiatedBy = tostring(InitiatedBy["app"]["displayName"])
| where InitiatedBy == "Microsoft Entra multifactor authentication Management"
| extend UserObjectId = tostring(TargetResources[0]["id"])
| summarize UsersMigrated = dcount(UserObjectId) by InitiatedBy, bin(ActivityDateTime, 1d)
```

[Image: Log Analytics の概要のスクリーンショット。]

#### 検証とテスト

ユーザー データが正常に移行されたら、グローバル テナントを変更する前に、段階的ロールアウトを使用してエンドユーザー エクスペリエンスを検証できます。 次のプロセスでは、MFA の段階的ロールアウトの特定の Microsoft Entra グループを対象とすることができます。 段階的ロールアウトでは、対象グループのユーザーに対して、MFA を実行するためにオンプレミスで送信するのではなく、Microsoft Entra 多要素認証を使用して MFA を実行するように Microsoft Entra ID に指示します。 検証とテストは可能です。Microsoft Entra 管理センターを使用することをお勧めしますが、必要に応じて Microsoft Graph を使用することもできます。

##### 段階的ロールアウトを有効にする

1. 次の URL に移動します。 [段階的なロールアウト機能を有効にする - Microsoft Azure](https://entra.microsoft.com/#view/Microsoft_AAD_IAM/StagedRolloutEnablementBladeV2)。
2. **Azure 多要素認証**を **[オン]** に変更し、[**グループの管理**] を選択します。

    [Image: 段階的ロールアウトのスクリーンショット。]
3. [ **グループの追加]** を選択し、Microsoft Entra 多要素認証を有効にするユーザーを含むグループを追加します。 選択したグループが表示リストに表示されます。

    手記

    次の Microsoft Graph メソッドを使用して対象とするグループも、この一覧に表示されます。

    [Image: [グループの管理] メニューのスクリーンショット。]

##### Microsoft Graph を使用して段階的ロールアウトを有効にする

1. featureRolloutPolicy を作成します

    1. https://aka.ms/ge に移動し、段階的ロールアウト用に設定するテナントのハイブリッド ID 管理者アカウントを使用して Graph Explorer にログインします。
    2. 次のエンドポイントをターゲットとする POST が選択されていることを確認します: `https://graph.microsoft.com/v1.0/policies/featureRolloutPolicies`
    3. 要求の本文には、次のものが含まれている必要があります ( **MFA ロールアウト ポリシー** を組織の名前と説明に変更します)。

        ```msgraph
        {
             "displayName": "MFA rollout policy",
             "description": "MFA rollout policy",
             "feature": "multiFactorAuthentication",
             "isEnabled": true,
             "isAppliedToOrganization": false
        }
        ```

        [Image: 要求のスクリーンショット。]
    4. 同じエンドポイントで GET を実行し、 **ID** 値を書き留めます (次の図を参照)。

        [Image: GET コマンドのスクリーンショット。]
2. テストするユーザーを含む Microsoft Entra グループをターゲットにする

    1. 次のエンドポイントを使用して POST 要求を作成します ({ID of policy} は、手順 1d からコピーした **ID** 値に置き換えます)。

        `https://graph.microsoft.com/v1.0/policies/featureRolloutPolicies/{ID of policy}/appliesTo/$ref`
    2. 要求の本文には、次のものが含まれている必要があります ({ID of group} を、段階的ロールアウトの対象にするグループのオブジェクト ID に置き換えます)。

        ```msgraph
        {
        "@odata.id": "https://graph.microsoft.com/v1.0/directoryObjects/{ID of group}"
        }
        ```
    3. 段階的ロールアウトでターゲットにする他のグループに対して、ステップ a と b を繰り返します。
    4. 現在のポリシーを表示するには、次の URL に対して GET を実行します。

        `https://graph.microsoft.com/v1.0/policies/featureRolloutPolicies/{policyID}?$expand=appliesTo`

        上記のプロセスでは [、featureRolloutPolicy リソース](https://learn.microsoft.com/ja-jp/graph/api/resources/featurerolloutpolicy?view=graph-rest-1.0&preserve-view=true)を使用します。 パブリック ドキュメントは、新しい multifactorAuthentication 機能でまだ更新されていませんが、API の操作方法に関する詳細な情報が含まれています。
3. エンド ユーザーの MFA エクスペリエンスを確認します。 確認すべき点をいくつか次に示します。

    1. ユーザーは自分のメソッドを https://aka.ms/mfasetup で確認できますか?
    2. ユーザーは電話/テキスト メッセージを受信しますか?
    3. 上記の方法を使用して正常に認証できますか?
    4. ユーザーは Authenticator 通知を正常に受信しますか? これらの通知を承認することはできますか? 認証は成功しましたか?
    5. ユーザーはハードウェア OATH トークンを使用して正常に認証できますか?

#### ユーザーを教育する

新しい認証フローを含め、Microsoft Entra 多要素認証に移行したときに想定される内容をユーザーが把握していることを確認します。 移行が完了したら、ユーザー ポータルではなく、Microsoft Entra ID 結合登録ポータル (https://aka.ms/mfasetup) を使用して認証方法を管理するようにユーザーに指示することもできます。 Microsoft Entra ID の認証方法に加えられた変更は、オンプレミス環境に反映されません。 MFA Server にロールバックする必要がある状況では、ユーザーが Microsoft Entra ID で行った変更は、MFA サーバー ユーザー ポータルでは使用できません。

認証に Microsoft Entra 多要素認証サーバーに依存するサード パーティ製ソリューションを使用する場合 (認証サービスの参照)、ユーザー ポータルで引き続き MFA メソッドを変更する必要があります。 これらの変更は、Microsoft Entra ID に自動的に同期されます。 これらのサード パーティ製ソリューションを移行したら、Microsoft Entra ID の統合登録ページにユーザーを移動できます。

#### ユーザーの移行を完了する

すべての ユーザー データが移行されるまで、「ユーザー データの移行 」セクション と「検証とテスト 」セクションにある移行手順を繰り返します。

#### MFA サーバーの依存関係を移行する

認証サービスで収集したデータ ポイントを使用して、必要なさまざまな移行の実行を開始します。 これが完了したら、MFA サーバーのユーザー ポータルではなく、統合された登録ポータルでユーザーに認証方法を管理してもらうことを検討してください。

#### ドメイン フェデレーション設定を更新する

ユーザーの移行を完了し、すべての 認証サービス を MFA Server から移動したら、ドメイン フェデレーション設定を更新します。 更新後、Microsoft Entra はオンプレミスのフェデレーション サーバーに MFA 要求を送信しなくなりました。

オンプレミスのフェデレーション サーバーへの MFA 要求を無視するように Microsoft Entra ID を構成するには、次の例に示すように、Microsoft Graph PowerShell SDK をインストールし、federatedIdpMfaBehaviorを に設定します。

##### 依頼

```http
PATCH https://graph.microsoft.com/beta/domains/contoso.com/federationConfiguration/6601d14b-d113-8f64-fda2-9b5ddda18ecc
Content-Type: application/json
{
  "federatedIdpMfaBehavior": "rejectMfaByFederatedIdp"
}
```

##### 応答

手記

ここで示す応答オブジェクトは、読みやすくするために短縮される場合があります。

```http
HTTP/1.1 200 OK
Content-Type: application/json
{
  "@odata.type": "#microsoft.graph.internalDomainFederation",
  "id": "6601d14b-d113-8f64-fda2-9b5ddda18ecc",
   "issuerUri": "http://contoso.com/adfs/services/trust",
   "metadataExchangeUri": "https://sts.contoso.com/adfs/services/trust/mex",
   "signingCertificate": "A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0u",
   "passiveSignInUri": "https://sts.contoso.com/adfs/ls",
   "preferredAuthenticationProtocol": "wsFed",
   "activeSignInUri": "https://sts.contoso.com/adfs/services/trust/2005/usernamemixed",
   "signOutUri": "https://sts.contoso.com/adfs/ls",
   "promptLoginBehavior": "nativeSupport",
   "isSignedAuthenticationRequestRequired": true,
   "nextSigningCertificate": "A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0u",
   "signingCertificateUpdateStatus": {
        "certificateUpdateResult": "Success",
        "lastRunDateTime": "2021-08-25T07:44:46.2616778Z"
    },
   "federatedIdpMfaBehavior": "rejectMfaByFederatedIdp"
}
```

ユーザーが段階的ロールアウト ツールの対象であるかどうかに関係なく、MFA のためにオンプレミスのフェデレーション サーバーにリダイレクトされなくなります。 有効になるまでに最大 24 時間かかる場合があることに注意してください。

手記

ドメイン フェデレーション設定の更新が有効になるまでに最大 24 時間かかることがあります。

#### 省略可能: MFA サーバー ユーザー ポータルを無効にする

すべてのユーザー データの移行が完了したら、エンド ユーザーは Microsoft Entra ID の統合登録ページの使用を開始して MFA メソッドを管理できます。 ユーザーが MFA Server でユーザー ポータルを使用できないようにするには、次の 2 つの方法があります。

- MFA Server ユーザー ポータルの URL を https://aka.ms/mfasetup にリダイレクトする
- MFA Server の [ユーザー ポータル] セクションの [**設定**] タブの下にある [**ユーザーのログインを許可**する] チェック ボックスをオフにして、ユーザーがポータルに完全にログインできないようにします。

#### MFA サーバーの使用停止

Microsoft Entra 多要素認証サーバーが不要になったら、通常のサーバーの非推奨のプラクティスに従ってください。 MFA サーバーの提供終了を示すために、Microsoft Entra ID に特別なアクションは必要ありません。

### ロールバック計画

アップグレードに問題がある場合は、次の手順に従ってロールバックします。

1. MFA Server 8.1 をアンインストールします。
2. PhoneFactor.pfdata をアップグレードする前に作成されたバックアップに置き換えます。

    手記

    バックアップが行われた後の変更はすべて失われますが、アップグレードの直前にバックアップが行われ、アップグレードが失敗した場合は最小限にする必要があります。
3. 以前のバージョン (8.0.x.x など) のインストーラーを実行します。
4. オンプレミスのフェデレーション サーバーへの MFA 要求を受け入れるように Microsoft Entra ID を構成します。 次の例に示すように、Graph PowerShell を使用して [federatedIdpMfaBehavior](https://learn.microsoft.com/ja-jp/graph/api/resources/internaldomainfederation?view=graph-rest-1.0&preserve-view=true#federatedidpmfabehavior-values) を `enforceMfaByFederatedIdp`に設定します。

    **依頼**

    ```http
    PATCH https://graph.microsoft.com/beta/domains/contoso.com/federationConfiguration/6601d14b-d113-8f64-fda2-9b5ddda18ecc
    Content-Type: application/json
    {
      "federatedIdpMfaBehavior": "enforceMfaByFederatedIdp"
    }
    ```

    読みやすくするために、次の応答オブジェクトが短縮されています。

    **応答**

    ```http
    HTTP/1.1 200 OK
    Content-Type: application/json
    {
      "@odata.type": "#microsoft.graph.internalDomainFederation",
      "id": "6601d14b-d113-8f64-fda2-9b5ddda18ecc",
       "issuerUri": "http://contoso.com/adfs/services/trust",
       "metadataExchangeUri": "https://sts.contoso.com/adfs/services/trust/mex",
       "signingCertificate": "A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0u",
       "passiveSignInUri": "https://sts.contoso.com/adfs/ls",
       "preferredAuthenticationProtocol": "wsFed",
       "activeSignInUri": "https://sts.contoso.com/adfs/services/trust/2005/usernamemixed",
       "signOutUri": "https://sts.contoso.com/adfs/ls",
       "promptLoginBehavior": "nativeSupport",
       "isSignedAuthenticationRequestRequired": true,
       "nextSigningCertificate": "A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0u",
       "signingCertificateUpdateStatus": {
            "certificateUpdateResult": "Success",
            "lastRunDateTime": "2021-08-25T07:44:46.2616778Z"
        },
       "federatedIdpMfaBehavior": "enforceMfaByFederatedIdp"
    }
    ```

Microsoft Entra 多要素認証 の 段階的ロールアウトを Offに設定します。 ユーザーは、MFA のためにオンプレミスのフェデレーション サーバーに再びリダイレクトされます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-mfa-upload-oath-tokens"} -->
## CSV 形式でハードウェア OATH トークンをアップロードする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-upload-oath-tokens
- Service: entra-id / authentication
- Article date: 2025-12-11
- Summary: CSV ファイルと全体管理者ロールを使用して、Microsoft Entra ID でハードウェア OATH トークンをアップロードする方法について説明します。

ハードウェア OATH トークンには、通常、トークン内に事前にプログラムされた秘密鍵 (シード) が付属しています。 ユーザーがハードウェア OATH トークンを使用して Microsoft Entra ID で職場または学校アカウントにサインインできるようにするには、管理者がテナントにトークンを追加する必要があります。

トークンを追加する推奨される方法は、最小限の特権を持つ管理者ロールで Microsoft Graph を使用することです。 最小限の特権ロールを使用する更新されたプロセスはプレビュー段階です。 詳細については、「[ハードウェア OATH トークン (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-oath-tokens#hardware-oath-tokens-preview)」を参照してください。

Microsoft Graph API を使用する代わりに、Microsoft Entra ID Premium ライセンスを持つテナントでは、Microsoft Entra ID に対してこれらのキーを全体管理者に入力させることができます。 CSV 形式でトークンをアップロードする場合、プレビューの新機能と互換性がありません。 CSV アップロードの代わりにハードウェア OATH トークン プレビューを使用するには、「 [Microsoft Entra ID (プレビュー)で OATH トークンを管理する方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-manage-oath-tokens)」を参照してください。

### CSV 形式

秘密キーは 128 文字に制限されており、一部のトークンと互換性がありません。 秘密キーに含めることができるのは、文字 *a-z* または *A-Z* と数字 *2-7* のみです。また、*Base32* でエンコードする必要があります。

再シードできるプログラミング可能な OATH 時間ベースのワンタイム パスコード (TOTP) ハードウェア トークンは、ソフトウェア トークンの設定フローで Microsoft Entra ID を使用して設定することもできます。

[Image: OATH トークン管理のスクリーンショット。]

トークンを取得したら、全体管理者はトークンをコンマ区切り値 (CSV) ファイル形式でアップロードする必要があります。 ファイルには、次の例に示すように、UPN、シリアル番号、秘密鍵、時間間隔、製造元、モデルが含まれている必要があります:

```csv
upn,serial number,secret key,time interval,manufacturer,model
Helga@contoso.com,1234567,2234567abcdef2234567abcdef,60,Contoso,HardwareKey
```

メモ

CSV ファイルにヘッダー行が含まれていることを確認します。

CSV ファイルとして適切にフォーマットされると、全体管理者は Microsoft Entra 管理センターにサインインし、 **Entra ID**&gt;**Multifactor 認証**&gt;**OATH トークン**に移動し、結果の CSV ファイルをアップロードできます。

CSV ファイルのサイズによって異なりますが、この処理には数分間かかることがあります。 **[最新の情報に更新]** ボタンを選択して、現在の状態を取得します。 ファイルにエラーがある場合、修正するために、エラーが含まれる CSV ファイルをダウンロードできます。 ダウンロードした CSV ファイル内のフィールド名は、アップロードされたバージョンとは異なります。

ユーザーは、最大 5 つの OATH ハードウェア トークンまたはいつでも使用されるように構成された認証アプリケーション (Microsoft Authenticator アプリなど) を組み合わせることもできます。 ハードウェア OATH トークンは、リソース テナントのゲスト ユーザーに割り当てることはできません。

重要

各トークンを 1 人のユーザーのみに割り当てるようにしてください。 1 つのトークンを複数のユーザーに割り当てることはできません。

### アップロード処理中のエラーのトラブルシューティング

場合によっては、CSV ファイルのアップロード処理で競合や問題などが発生する可能性があります。 競合や問題が発生した場合は、次のように通知されます。

[Image: アップロード エラーの例のスクリーンショット。]

エラー メッセージを確認するには、必ず **[詳細の表示]** を選択 してください。 **[ハードウェア トークンの状態]** ブレードが開き、アップロードの状態の概要が表示されます。 次の例のように、エラーが発生したか、複数のエラーが発生したかが表示されます。

[Image: ハードウェア トークンの状態の例のスクリーンショット。]

一覧表示されたエラーの原因を特定するには、表示する状態の横にあるチェック ボックスをオンにして、[ **ダウンロード** ] オプションをアクティブにします。 このオプションは、特定されたエラーを含む CSV ファイルをダウンロードします。

[Image: ダウンロード状態の例のスクリーンショット。]

ダウンロードしたファイルの名前は **Failures\_filename.csv**で、 *ファイル名* はアップロードされたファイルの名前です。 ファイルは、ブラウザーの既定のダウンロード ディレクトリに保存されます。

この例では、テナント ディレクトリに現在存在しないユーザーとして識別されるエラーを示します。

[Image: エラー理由の例のスクリーンショット。]

一覧表示されているエラーを修正したら、正常に処理されるまで CSV をもう一度アップロードします。 各試行の状態情報は 30 日間保持されます。 CSV を削除するには、状態の横にあるチェック ボックスをオンにし、[ **状態の削除**] を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-migrate-mfa-server-to-azure-mfa"} -->
## MFA サーバーから Microsoft Entra 多要素認証に移行する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-migrate-mfa-server-to-azure-mfa
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: オンプレミスの MFA サーバーから Microsoft Entra 多要素認証への移行に関するステップ バイ ステップのガイダンス

多要素認証は、インフラストラクチャと資産を不正ユーザーからセキュリティで保護するために重要です。 Azure Multi-Factor Authentication Server (MFA Server) は、新しいデプロイには利用不可であり、かつ非推奨です。 MFA サーバーを使っているお客様は、クラウドベースの Microsoft Entra 多要素認証の使用に移行する必要があります。

この記事は、次のようなハイブリッド環境があることを前提としています。

- 多要素認証のために MFA Server を使用している。
- Active Directory フェデレーション サービス (AD FS) または別の ID プロバイダー フェデレーション製品によるフェデレーションを Microsoft Entra ID で使っている。
    - この記事の対象は AD FS ですが、他の ID プロバイダーにも同様の手順が適用されます。
- MFA Server が AD FS と統合されている。
- 認証に AD FS を使用しているアプリケーションがある場合がある。

目標に応じて、移行について複数の最終状態が考えられます。

| - | 目標: MFA Server のみの使用を停止する | 目標: MFA サーバーの使用を停止し、Microsoft Entra 認証に移行する | 目標: MFA Server と AD FS の使用を停止する |
| --- | --- | --- | --- |
| MFA プロバイダー | MFA プロバイダーを MFA サーバーから Microsoft Entra 多要素認証に変更します。 | MFA プロバイダーを MFA サーバーから Microsoft Entra 多要素認証に変更します。 | MFA プロバイダーを MFA サーバーから Microsoft Entra 多要素認証に変更します。 |
| ユーザー認証 | Microsoft Entra 認証には引き続きフェデレーションを使います。 | パスワード ハッシュ同期 (優先) またはパススルー認証**および**シームレスなシングル サインオン (SSO) を備えた Microsoft Entra ID に移行します。 | パスワード ハッシュ同期 (優先) またはパススルー認証**および** SSO を備えた Microsoft Entra ID に移行します。 |
| アプリケーション認証 | アプリケーション に AD FS 認証を引き続き使用します。 | アプリケーション に AD FS 認証を引き続き使用します。 | Microsoft Entra 多要素認証に移行する前に、アプリを Microsoft Entra ID に移行します。 |

可能な場合は、多要素認証とユーザー認証の両方を Azure に移行します。 詳細な手順のガイダンスについては、[Microsoft Entra 多要素認証と Microsoft Entra ユーザー認証への移行](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-migrate-mfa-server-to-mfa-user-authentication)に関する記事を参照してください。

ユーザー認証を移行できない場合は、[フェデレーションを使って Microsoft Entra 多要素認証に移行する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-migrate-mfa-server-to-mfa-with-federation)ための詳細な手順のガイダンスを参照してください。

### 前提条件

- AD FS 環境 (MFA Server を移行する前に、すべてのアプリを Microsoft Entra に移行しない場合に必要)
    - AD FS for Windows Server 2019、ファーム動作レベル (FBL) 4 にアップグレードします。 このアップグレードにより、グループ メンバーシップに基づいて認証プロバイダーを選択でき、移行がユーザーに対してよりシームレスになります。 AD FS for Windows Server 2016 FBL 3 でも移行は可能ですが、この移行はユーザーに対してシームレスではありません。 移行中は、移行が完了するまで、認証プロバイダー (MFA サーバーまたは Microsoft Entra 多要素認証) を選ぶように求めるメッセージがユーザーに表示されます。
- アクセス許可
    - Microsoft Entra 多要素認証の AD FS ファームを構成するための Active Directory のエンタープライズ管理者ロール
    - この機能を管理するには、[全体管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)が必要です。

### すべての移行パスに関する考慮事項

MFA サーバーから Microsoft Entra 多要素認証に移行するには、登録されている MFA 電話番号を移行するだけでは不十分です。 Microsoft の MFA サーバーは多くのシステムと統合できます。Microsoft Entra 多要素認証と統合するための最適な方法を理解するために、これらのシステムで MFA サーバーがどのように使われているかを評価する必要があります。

#### MFA ユーザー情報を移行する

ユーザーを一括で移行する場合の一般的な考え方には、リージョン、部署、または管理者などのロールによるユーザーの移行が含まれます。 テストおよびパイロット グループから始めて、ユーザー アカウントを反復的に移動し、必ずロールバック計画を準備してください。

[MFA サーバー移行ユーティリティ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-server-migration-utility)を使用して、オンプレミスの Azure MFA Server 内に格納されている MFA データを Microsoft Entra 多要素認証と同期し、[段階的ロールアウト](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout)を使用してユーザーを Microsoft Entra 多要素認証に再ルーティングできます。 段階的ロールアウトを使用すると、ドメイン フェデレーション設定を変更せずにテストできます。

MFA Server にリンクされている古いアカウントと新しく追加したアカウントをユーザーが区別できるようにするには、MFA Server 上のモバイル アプリのアカウント名が、2 つのアカウントを区別できる名前になっている必要があります。 たとえば、MFA Server の [モバイル アプリ] の下に表示されるアカウント名が**オンプレミス MFA Server** に変更されているとします。 Microsoft Authenticator のアカウント名は、次にユーザーにプッシュ通知されると変更されます。

電話番号を移行すると、古い番号が移行される可能性があり、パスワードレス モードの Microsoft Authenticator サインインなど、安全な方法を設定する代わりに、ユーザーが電話ベースの MFA を使用し続ける可能性があります。 したがって、選択した移行パスに関係なく、[統合されたセキュリティ情報](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-registration-mfa-sspr-combined)をすべてのユーザーに登録してもらうことをお勧めします。

##### ハードウェア セキュリティ キーを移行する

Microsoft Entra ID では、ハードウェア OATH トークンのサポートが提供されています。 [MFA サーバー移行ユーティリティ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-server-migration-utility)を使うと、MFA サーバーと Microsoft Entra 多要素認証の間で MFA 設定を同期し、[段階的ロールアウト](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout)を使って、ドメイン フェデレーション設定を変更せずにユーザーの移行をテストできます。

ハードウェア OATH トークンのみを移行する場合は、一般に "シード ファイル" と呼ばれる [CSV ファイルを使用して Microsoft Entra ID にトークンをアップロードする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-oath-tokens#hardware-oath-tokens-preview)必要があります。 シード ファイルには、シークレット キー、トークンのシリアル番号、トークンを Microsoft Entra ID にアップロードするために必要なその他の情報が含まれます。

シークレット キーが含まれるシード ファイルがなくなると、MFA Server からシークレット キーをエクスポートできません。 シークレット キーにアクセスできなくなった場合のサポートについては、ハードウェア ベンダーにお問い合せください。

MFA Server Web サービス SDK を使用すると、特定のユーザーに割り当てられた OATH トークンのシリアル番号をエクスポートできます。 この情報とシード ファイルを使って、トークンを Microsoft Entra ID にインポートし、シリアル番号に基づいて、指定されたユーザーに OATH トークンを割り当てることができます。 デバイスから OTP 情報を指定して登録を完了するように、インポート時にユーザーに通知する必要があります。 MFA Server 上のヘルプ ファイルのトピック **[GetUserInfo]**&gt;**[userSettings]**&gt;**[OathTokenSerialNumber]** を参照してください。

#### その他の移行

MFA サーバーから Microsoft Entra 多要素認証への移行を決定すると、その他の移行も可能になります。 その他の移行を実行できるかどうかは、多くの要因 (特に以下の要因) によって決まります。

- ユーザーに対して Microsoft Entra 認証を使う意図がある
- アプリケーションを Microsoft Entra ID に移行する意図がある

MFA Server はアプリケーションとユーザー認証の両方に不可欠であるため、MFA 移行の一環として、これらの機能の両方を Azure に移行し、最終的に AD FS の使用を停止することを検討してください。

推奨事項:

- 堅牢なセキュリティとガバナンスが可能になるため、Microsoft Entra ID を認証に使う
- 可能であればアプリケーションを Microsoft Entra ID に移行します

組織に最適なユーザー認証方法を選択するには、「[Microsoft Entra ハイブリッド ID ソリューションの適切な認証方法を選ぶ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/choose-ad-authn)」を参照してください。 パスワード ハッシュ同期 (PHS) を使用することをお勧めします。

#### パスワードレスの認証

2 つ目の要素として Microsoft Authenticator を使用するユーザーの登録の一環として、パスワードレスの電話でのサインインを有効にすることをお勧めします。 FIDO2 セキュリティ キーや Windows Hello for Business などの他のパスワードレスの方法を含む詳細については、「[Microsoft Entra ID でパスワードレス認証のデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-deployment#plan-for-and-deploy-microsoft-authenticator)」を参照してください。

#### Microsoft Identity Manager セルフサービス パスワード リセット

Microsoft Identity Manager (MIM) SSPR では、MFA Server を使用して、パスワード リセット フローの一部として SMS ワンタイム パスコードを呼び出すことができます。 MIM は、Microsoft Entra 多要素認証を使うように構成することはできません。 SSPR サービスの Microsoft Entra SSPR への移行を検討することをお勧めします。 ユーザーが Microsoft Entra 多要素認証に登録する機会を利用し、統合された登録エクスペリエンスを使って Microsoft Entra SSPR に登録することができます。

SSPR サービスを移動できない場合、または MFA Server を使用して Privileged Access Management (PAM) シナリオの MFA 要求を呼び出す場合は、[代替のサード パーティ MFA オプション](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/working-with-custommfaserver-for-mim)に更新することをお勧めします。

#### RADIUS クライアントと Microsoft Entra 多要素認証

MFA Server は、RADIUS プロトコルをサポートするアプリケーションとネットワーク デバイスに対して多要素認証を呼び出すことができるように、この RADIUS をサポートしています。 RADIUS と MFA サーバーを使っている場合は、クライアント アプリケーションを最新のプロトコル (SAML、OpenID Connect、Microsoft Entra ID の OAuth など) に移行することをお勧めします。 アプリケーションを更新できない場合、ネットワーク ポリシー サーバー (NPS) と Microsoft Entra 多要素認証拡張機能をデプロイできます。 ネットワーク ポリシー サーバー (NPS) 拡張機能は、RADIUS ベースのアプリケーションと Microsoft Entra 多要素認証の間のアダプターとして機能し、認証の 2 番目の要素を提供します。 この "アダプター" により、RADIUS クライアントを Microsoft Entra 多要素認証に移行でき、MFA サーバーの使用を停止できます。

##### 重要な考慮事項

RADIUS クライアントに NPS を使用する場合は制限があるため、RADIUS クライアントを評価して、最新の認証プロトコルにアップグレードできるかどうかを判断することをお勧めします。 サポートされている製品のバージョンとそれらの機能については、サービス プロバイダーに確認してください。

- NPS 拡張機能では、Microsoft Entra 条件付きアクセス ポリシーは使われません。 RADIUS の使用を継続し、NPS 拡張機能を使用する場合、NPS に送信されるすべての認証要求でユーザーが MFA を実行する必要があります。
- ユーザーは、NPS 拡張機能を使用する前に、Microsoft Entra 多要素認証に登録する必要があります。 そうしないと、拡張機能はユーザーの認証に失敗し、ヘルプ デスクの呼び出しが生成される場合があります。
- NPS 拡張機能で MFA が呼び出されると、MFA 要求がユーザーの既定の MFA メソッドに送信されます。
    - サインインは Microsoft 以外のアプリケーションで行われるため、多要素認証が必要であること、および要求がデバイスに送信されたことを知らせる視覚的通知が、多くの場合はユーザーに表示されません。
    - 多要素認証の要件を満たすために、この要件の期間中、ユーザーは既定の認証方法にアクセスできる必要があります。 別の方法を選択することはできません。 既定の認証方法は、テナントの認証方法と多要素認証のポリシーで無効になっている場合でも使用されます。
    - ユーザーは、[セキュリティ情報] ページで既定の多要素認証方法を変更できます (aka.ms/mysecurityinfo)。
- RADIUS クライアントで使用可能な MFA メソッドは、RADIUS アクセス要求を送信するクライアント システムによって制御されます。
    - パスワードを入力した後にユーザー入力が必要になる MFA メソッドは、RADIUS を使用したアクセス チャレンジ応答をサポートするシステムでのみ使用できます。 入力方式には、OTP、ハードウェア OATH トークン、または Microsoft Authenticator などがあります。
    - 一部のシステムでは、使用可能な多要素認証方法が Microsoft Authenticator プッシュ通知と電話呼び出しに制限される場合があります。

注意

RADIUS クライアントと NPS システムの間で使用されるパスワード暗号化アルゴリズムと、クライアントが使用できる入力方式によって、使用できる認証方法が決まります。 詳細については、[ユーザーが使用できる認証方法の決定](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-nps-extension)に関するページを参照してください。

一般的な RADIUS クライアントの統合には、[リモート デスクトップ ゲートウェイ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-nps-extension-rdg)や [VPN サーバー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-nps-extension-vpn)などのアプリケーションが含まれます。 その他の統合を次に示します。

- Citrix Gateway
    - [Citrix Gateway](https://docs.citrix.com/en-us/citrix-gateway) では、RADIUS と NPS の両方の拡張機能の統合と、SAML 統合がサポートされています。
- Cisco VPN
    - Cisco VPN では、[SSO の RADIUS 認証と SAML 認証](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cisco-secure-firewall-secure-client)の両方がサポートされています。
    - RADIUS 認証から SAML に移行すると、NPS 拡張機能をデプロイすることなく、Cisco VPN を統合できます。
- すべての VPN
    - 可能であれば、VPN を SAML アプリとしてフェデレーションすることをお勧めします。 このフェデレーションを使用すると、条件付きアクセスを使用できます。 詳細については、[Microsoft Entra ID アプリ ギャラリーに統合されている VPN ベンダー一覧](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/secure-hybrid-access#secure-hybrid-access-through-azure-ad-partner-integrations)を参照してください。

#### NPS のデプロイに関するリソース

- [新しい NPS インフラストラクチャを追加する](https://learn.microsoft.com/ja-jp/windows-server/networking/technologies/nps/nps-top)
- [NPS デプロイのベスト プラクティス](https://www.youtube.com/watch?v=qV9wddunpCY)
- [Microsoft Entra 多要素認証 NPS 拡張機能の正常性チェック スクリプト](https://github.com/Azure-Samples/azure-mfa-nps-extension-health-check)
- [既存の NPS インフラストラクチャと Microsoft Entra 多要素認証の統合](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-nps-extension-vpn)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-migrate-mfa-server-to-mfa-user-authentication"} -->
## Microsoft Entra多要素認証およびMicrosoft Entraユーザー認証に移行する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-migrate-mfa-server-to-mfa-user-authentication
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: オンプレミスの MFA Server から多要素認証とMicrosoft Entraユーザー認証Microsoft Entraに移行するためのガイダンス

多要素認証は、インフラストラクチャと資産を悪意のあるアクターからセキュリティ保護するのに役立ちます。 Microsoft Multi-Factor Authentication Server (MFA Server) は、新しいデプロイでは提供されなくなりました。 MFA Server を使用しているお客様は、Microsoft Entra 多要素認証に移行する必要があります。

MFA Server から Microsoft Entra ID に移行するには、いくつかのオプションがあります。

- 良い:[MFAサービスのみをMicrosoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-migrate-mfa-server-to-azure-mfa)に移動します。
- 改善: MFA サービスとユーザー認証をMicrosoft Entra IDに移行します。この記事で説明します。
- ベスト: すべてのアプリケーション、MFA サービス、ユーザー認証をMicrosoft Entra IDに移行します。 この記事で説明するアプリケーションの移動を計画している場合は、この記事の「Microsoft Entra IDへのアプリケーションの移動」セクションを参照してください。

組織に適した MFA 移行オプションを選択するには、「[MFA サーバーから Microsoft Entra 多要素認証へ移行する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-migrate-mfa-server-to-azure-mfa)」に関する考慮事項を参照してください。

次の図は、一部のアプリケーションを AD FS に保持しながら、多要素認証とクラウド認証をMicrosoft Entraに移行するプロセスを示しています。 このプロセスにより、グループ メンバーシップに基づいてユーザーをMFA ServerからMicrosoft Entra多要素認証に反復的に移行することが可能になります。

この記事の以降のセクションでは、各ステップについて説明します。

注

この移行の一環として、アプリケーションをMicrosoft Entra IDに移行することを計画している場合は、MFA 移行の前に行う必要があります。 すべてのアプリを移動する場合は、MFA 移行プロセスのセクションをスキップできます。 この記事の最後にあるアプリケーションの移動に関するセクションを参照してください。

### Microsoft Entra IDおよびユーザー認証に移行するプロセス

[Image: Microsoft Entra IDおよびユーザー認証に移行するプロセス。]

### グループと条件付きアクセスを準備する

グループは、MFA 移行のために 3 つの容量で使用されます。

- **段階的な展開を使用して、ユーザーを Microsoft Entra の多要素認証に繰り返し移行するには。**

    Microsoft Entra IDで作成されたグループ (クラウド専用グループとも呼ばれます) を使用します。 Microsoft Entraセキュリティ グループまたはMicrosoft 365 グループは、ユーザーを MFA に移動する場合と条件付きアクセス ポリシーに使用できます。

    重要

    段階的ロールアウトでは、入れ子になったグループと動的メンバーシップ グループはサポートされていません。 これらの種類のグループは使用しないでください。
- **条件付きアクセス ポリシー**。 条件付きアクセスには、Microsoft Entra ID グループまたはオンプレミス グループを使用できます。
- **AD FS アプリケーションの要求規則で Microsoft Entra の多要素認証を呼び出す。** この手順は AD FS でアプリケーションを使用する場合にのみ適用されます。

    "オンプレミスのActive Directoryセキュリティグループを使用する必要があります。" Microsoft Entraの多要素認証が追加の認証方法になると、各依存パーティの信頼でその方法を使用するユーザーのグループを指定できます。 たとえば、既に移行したユーザーには多要素認証Microsoft Entra呼び出し、まだ移行されていないユーザーには MFA Server を呼び出すことができます。 この方法は、テストと移行中の両方に役立ちます。

注

セキュリティに使用されるグループを再利用しないことをお勧めします。 条件付きアクセス ポリシーを使用して高価値のアプリのグループをセキュリティで保護するには、セキュリティ グループのみを使用します。

#### 条件付きアクセス ポリシーを構成する

ユーザーへの MFA 要求タイミングの判断で既に条件付きアクセスを使用している場合は、ポリシーを変更する必要はありません。 ユーザーがクラウド認証に移行されると、条件付きアクセス ポリシーで定義されている多要素認証Microsoft Entra使用が開始されます。 AD FS と MFA Server にはリダイレクトされなくなります。

フェデレーション ドメインで **federatedIdpMfaBehavior** が `enforceMfaByFederatedIdp` に設定されているか、**SupportsMfa** フラグが `$True` に設定されている場合 (両方が設定されている場合、**federatedIdpMfaBehavior** が **SupportsMfa** をオーバーライドします)、要求規則を使用して AD FS に MFA を適用している可能性があります。 この場合は、Microsoft Entra ID証明書利用者信頼に関する要求規則を分析し、同じセキュリティ目標をサポートする条件付きアクセス ポリシーを作成する必要があります。

必要に応じて、段階的ロールアウトを有効にする前に条件付きアクセス ポリシーを構成します。 詳細については、次のリソースを参照してください。

- [条件付きアクセスのデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/plan-conditional-access)
- [一般的な条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policy-common)

### AD FS を準備する

AD FS に MFA を必要とするアプリケーションがない場合は、このセクションをスキップし、「段階的ロールアウトを準備する」セクションに進んでかまいません。

#### AD FS サーバー ファームを 2019、FBL 4 にアップグレードする

AD FS 2019 では、Microsoftアプリケーションなどの証明書利用者の追加の認証方法を指定するのに役立つ新しい機能がリリースされました。 グループ メンバーシップを使用して認証プロバイダーを決定することで、追加の認証方法を指定できます。 追加の認証方法を指定することで、移行中に他の認証をそのまま維持しながら、多要素認証Microsoft Entraに移行できます。

詳細については、「[WID データベースを使用したWindows Server 2016での AD FS へのアップグレード](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/deployment/upgrading-to-ad-fs-in-windows-server)を参照してください。 この記事では、ファームを AD FS 2019 にアップグレードし、FBL を 4 にアップグレードする方法について説明します。

#### Microsoft Entra 多要素認証を呼び出すために、要求規則を構成する

Microsoft Entraの多要素認証が追加の認証方法として利用できるようになったため、要求規則（*リライパーティトラスト*とも呼ばれる）を構成して、Microsoft Entraの多要素認証を使用するユーザーのグループを割り当てることができます。 グループを使用すると、グローバルに、またはアプリケーションによって、どの認証プロバイダーが呼び出されるかを制御できます。 たとえば、統合されたセキュリティ情報に登録したユーザーまたは電話番号を移行したユーザーに対してMicrosoft Entra多要素認証を呼び出し、電話番号が移行されていないユーザーに対して MFA Server を呼び出すことができます。

注

要求規則には、オンプレミスのセキュリティ グループが必要です。

##### 規則をバックアップする

新しい要求規則を構成する前に、規則をバックアップします。 クレーム規則は、クリーンアップ手順の一環として復元する必要があります。

構成に応じて、既存の規則をコピーし、移行用に作成される新しい規則を追加することが必要になる場合もあります。

グローバル規則を表示するには、次のように実行します。

```powershell
Get-AdfsAdditionalAuthenticationRule
```

証明書利用者の信頼を表示するには、次のコマンドを実行し、RPTrustName を証明書利用者の信頼の要求規則の名前に置き換えます。

```powershell
(Get-AdfsRelyingPartyTrust -Name "RPTrustName").AdditionalAuthenticationRules
```

##### アクセス制御ポリシー

注

グループ メンバーシップに基づいて特定の認証プロバイダーが呼び出されるように、アクセス制御ポリシーを構成することはできません。

アクセス制御ポリシーから追加の認証規則に切り替えるには、MFA Server 認証プロバイダーを使用して、証明書利用者の信頼ごとに次のコマンドを実行します。

```powershell
Set-AdfsRelyingPartyTrust -**TargetName AppA -AccessControlPolicyName $Null**
```

このコマンドは、ロジックを現在のAccess Control ポリシーから追加の認証規則に移動します。

##### グループを設定して SID を検索する

Microsoft Entraの多要素認証を呼び出したいユーザーを配置するには、特定のグループが必要です。 そのグループのセキュリティ識別子 (SID) を見つける必要があります。 グループの SID を見つけるには、次のコマンドを実行し、`GroupName` をお使いのグループ名に置き換えます。

```powershell
Get-ADGroup GroupName
```

##### Microsoft Entra 多要素認証を呼び出す要求規則を設定する

次のMicrosoft Graph PowerShell コマンドレットは、グループ内のユーザーが企業ネットワークにいない場合に、Microsoft Entra多要素認証を呼び出します。 `"YourGroupSid"` は、前のコマンドレットを実行して見つかった SID に置き換えます。

[2019 で追加の認証プロバイダーを選択する方法](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/overview/whats-new-active-directory-federation-services-windows-server#how-to-choose-additional-auth-providers-in-2019)を必ず確認してください。

重要

続行する前に、クレーム規則をバックアップします。

###### グローバル要求規則を設定する

次のコマンドを実行し、RPTrustName を証明書利用者の信頼の要求規則の名前に置き換えます。

```powershell
(Get-AdfsRelyingPartyTrust -Name "RPTrustName").AdditionalAuthenticationRules
```

コマンドを実行すると、証明書利用者信頼の現在の追加の認証規則が返されます。 現在の要求規則に次の規則を追加する必要があります。

```console
c:[Type == "https://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid", Value == 
"YourGroupSID"] => issue(Type = "https://schemas.microsoft.com/claims/authnmethodsproviders", 
Value = "AzureMfaAuthentication");
not exists([Type == "https://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid", 
Value=="YourGroupSid"]) => issue(Type = 
"https://schemas.microsoft.com/claims/authnmethodsproviders", Value = 
"AzureMfaServerAuthentication");'
```

次の例では、ユーザーがネットワークの外部から接続しているときは MFA を要求するように、現在の要求規則が構成されているものとします。 この例には、追加する必要がある規則が含まれています。

```PowerShell
Set-AdfsAdditionalAuthenticationRule -AdditionalAuthenticationRules 'c:[type == 
"https://schemas.microsoft.com/ws/2012/01/insidecorporatenetwork", value == "false"] => issue(type = 
"https://schemas.microsoft.com/ws/2008/06/identity/claims/authenticationmethod", value = 
"https://schemas.microsoft.com/claims/multipleauthn" );
 c:[Type == "https://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid", Value == 
"YourGroupSID"] => issue(Type = "https://schemas.microsoft.com/claims/authnmethodsproviders", 
Value = "AzureMfaAuthentication");
not exists([Type == "https://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid", 
Value=="YourGroupSid"]) => issue(Type = 
"https://schemas.microsoft.com/claims/authnmethodsproviders", Value = 
"AzureMfaServerAuthentication");'
```

###### アプリケーションごとの要求規則を設定する

この例では、特定の証明書利用者の信頼 (アプリケーション) に対するクレーム ルールを変更します。 それには、追加する必要のある追加規則が含まれています。

```PowerShell
Set-AdfsRelyingPartyTrust -TargetName AppA -AdditionalAuthenticationRules 'c:[type == 
"https://schemas.microsoft.com/ws/2012/01/insidecorporatenetwork", value == "false"] => issue(type = 
"https://schemas.microsoft.com/ws/2008/06/identity/claims/authenticationmethod", value = 
"https://schemas.microsoft.com/claims/multipleauthn" );
c:[Type == "https://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid", Value == 
"YourGroupSID"] => issue(Type = "https://schemas.microsoft.com/claims/authnmethodsproviders", 
Value = "AzureMfaAuthentication");
not exists([Type == "https://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid", 
Value=="YourGroupSid"]) => issue(Type = 
"https://schemas.microsoft.com/claims/authnmethodsproviders", Value = 
"AzureMfaServerAuthentication");'
```

#### AD FS Microsoft Entra認証プロバイダーとして多要素認証を構成する

AD FS における多要素認証を設定するためには、各 AD FS サーバーで Microsoft Entra を構成する必要があります。 ファーム内に複数の AD FS サーバーがある場合は、Microsoft Graph PowerShell を使用してリモートで構成できます。

このプロセスの詳細な手順については、「[AD FS サーバーを構成する](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configure-ad-fs-and-azure-mfa#configure-the-ad-fs-servers)」を参照してください。

サーバーを構成したら、Microsoft Entra多要素認証を追加の認証方法として追加できます。

[Image: 追加の認証方法として多要素認証Microsoft Entra追加する方法のスクリーンショット。]

### 段階的ロールアウトを準備する

これで、[段階的ロールアウト](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout)を有効にする準備ができました。 段階的ロールアウトを使用すると、ユーザーを PHS または PTA に反復的に移動しながら、オンプレミスの MFA 設定も移行できます。

- [サポートされているシナリオ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout#supported-scenarios)を必ず確認してください。
- 最初に、[PHS に関する事前作業](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout#prework-for-password-hash-sync)または [PTA に関する事前作業](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout#prework-for-pass-through-authentication)を行う必要があります。 PHS をお勧めします。
- 次に、[シームレス SSO に関する事前作業](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout#prework-for-seamless-sso)を行います。
- 選択した認証方法について、[クラウド認証の段階的ロールアウトを有効にします](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout#enable-a-staged-rollout-of-a-specific-feature-on-your-tenant)。
- 段階的ロールアウト用に作成したグループを追加します。 ユーザーをグループに順次的に追加し、それらのグループは入れ子型または動的メンバーシップ グループにすることはできません。

### Microsoft Entra多要素認証にユーザーを登録する

このセクションでは、ユーザーが統合セキュリティに登録する (MFA とセルフサービス パスワード リセット) 方法と、MFA 設定を移行する方法について説明します。 Microsoft Authenticatorは、パスワードレス モードと同様に使用できます。 また、いずれかの登録方法を使用して MFA の 2 番目の要素として使用することもできます。

#### 統合されたセキュリティ登録に登録する (推奨)

ユーザーに統合されたセキュリティ情報に登録してもらうことをお勧めします。これは、MFA と SSPR の両方に認証方法とデバイスを登録するための単一の場所です。

Microsoftには、統合された登録プロセスを通じてユーザーをガイドするためにユーザーに提供できる通信テンプレートが用意されています。 これには、メール、ポスター、テーブル テント、他のさまざまなアセットのテンプレートなどがあります。 ユーザーは、`https://aka.ms/mysecurityinfo` で各自の情報を登録します。これにより、統合されたセキュリティ登録の画面に移動します。

信頼できるデバイスまたは場所から登録する必要がある[条件付きアクセスを使用して、セキュリティ登録プロセスを保護する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-security-info-registration)ことをお勧めします。 登録状態の追跡については、「Microsoft Entra ID のAuthentication メソッドアクティビティ」を参照してください。

注

信頼されていない場所またはデバイスから、統合されたセキュリティ情報を登録する必要があるユーザーについては、一時アクセス パスを発行したり、代わりにポリシーから一時的に除外したりできます。

#### MFA Server から MFA 設定を移行する

[MFA Server Migration ユーティリティ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-server-migration-utility)を使用して、MFA Server からMicrosoft Entra IDにユーザーの登録済み MFA 設定を同期できます。 電話番号、ハードウェア トークン、デバイス登録 (Microsoft Authenticator アプリ設定など) を同期できます。

#### ユーザーを適切なグループに追加する

- 新しい条件付きアクセス ポリシーを作成した場合は、それらのグループに適切なユーザーを追加します。
- 要求規則にオンプレミスのセキュリティ グループを作成した場合は、それらのグループに適切なユーザーを追加します。
- 適切な条件付きアクセス規則にユーザーを追加した後でのみ、段階的ロールアウト用に作成したグループにユーザーを追加します。 完了すると、選択した Azure 認証方法 (PHS または PTA) の使用が開始され、MFA が必要な場合には Microsoft Entra の多要素認証が使用されます。

重要

段階的ロールアウトでは、入れ子になったグループと動的メンバーシップ グループはサポートされていません。 これらの種類のグループは使用しないでください。

セキュリティに使用されるグループを再利用しないことをお勧めします。 セキュリティ グループを使用して、条件付きアクセス ポリシーで高価値アプリのグループをセキュリティで保護する場合、その目的のみにグループを使用してください。

### 監視

多くの[Azure Monitor ワークブック](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-workbooks)と**使用状況とインサイト**レポートを使用して、デプロイを監視できます。 これらのレポートは、ナビゲーション ウィンドウの Microsoft Entra ID **Monitoring** にあります。

#### 段階的ロールアウトの監視

[\[Workbooks\]](https://portal.azure.com/#blade/Microsoft_AAD_IAM/ActiveDirectoryMenuBlade/Workbooks) セクションで、 **[Public Templates](パブリック テンプレート)** を選択します。 **[Hybrid Auth](https://learn.microsoft.com/ja-jp/entra/identity/authentication/ハイブリッド認証)** セクションで、 **[Groups, Users and Sign-ins in Staged Rollout](https://learn.microsoft.com/ja-jp/entra/identity/authentication/段階的ロールアウトでのグループ、ユーザー、サインイン)** ワークブックを選択します。

このブックを使用すると、以下のアクティビティを監視できます。

- 段階的ロールアウトに追加されたユーザーとグループ。
- 段階的ロールアウトから削除されたユーザーとグループ。
- 段階的ロールアウトでのユーザーのサインイン失敗と、失敗の理由。

#### Microsoft Entra の多要素認証登録の監視

Microsoft Entra多要素認証の登録は、[認証方法の使用状況と分析情報のレポート](https://portal.azure.com/#blade/Microsoft_AAD_IAM/AuthenticationMethodsMenuBlade/AuthMethodsActivity/menuId/AuthMethodsActivity)を使用して監視できます。 このレポートは、Microsoft Entra IDにあります。 **[監視]** を選択し、**[使用状況と分析情報]** を選択します。

[Image: 使用状況と分析情報レポートを見つける方法のスクリーンショット。]

[使用状況と分析情報] で、**[認証方法]** を選択します。

Microsoft Entra 多要素認証の詳細な登録情報は、「登録」タブで確認できます。**Azure 多要素認証に登録されているユーザー**のハイパーリンクを選択すると、登録済みのユーザー一覧をドリルダウンして表示できます。

[Image: [登録] タブのスクリーン ショット。]

#### アプリのサインインの正常性の監視

Appサインイン正常性ワークブックまたはアプリケーション活動使用レポートを使用して、Microsoft Entra IDに移行したアプリケーションを監視します。

- **アプリ サインイン健全性ワークブック**。 このブックの使用に関する詳細なガイダンスについては、「[回復性のためにアプリケーションのサインインの正常性を監視する](https://learn.microsoft.com/ja-jp/entra/architecture/monitor-sign-in-health-for-resilience)」を参照してください。
- **Microsoft Entra アプリケーション アクティビティの使用状況レポート**。 この[report](https://portal.azure.com/#blade/Microsoft_AAD_IAM/UsageAndInsightsMenuBlade/Azure%20AD%20application%20activity)を使用して、個々のアプリケーションの成功したサインインと失敗したサインインを表示したり、特定のアプリケーションのサインイン アクティビティをドリルダウンして表示したりできます。

### クリーンアップのタスク

すべてのユーザーをMicrosoft Entraクラウド認証とMicrosoft Entra多要素認証に移行したら、MFA サーバーの使用を停止する準備が整います。 サーバーを削除する前に、MFA Server のログを調べて、ユーザーまたはアプリケーションによってそれが使用されていないのを確認することをお勧めします。

#### ドメインをマネージド認証に変換する

これで、Microsoft Entra ID内のフェデレーション ドメインをマネージド段階的ロールアウト構成を削除する必要があります。 この変換により、新しいユーザーは移行グループに追加されなくてもクラウド認証を使用できます。

#### AD FS の要求規則を元に戻し、MFA Server 認証プロバイダーを削除する

Microsoft Entra の多要素認証を呼び出すための要求規則を構成するの手順に従い、要求規則を元に戻して、AzureMFAServerAuthentication の要求規則を削除します。

たとえば、規則から次のセクションを削除します。

```console
c:[Type == "https://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid", Value ==
"**YourGroupSID**"] => issue(Type = "https://schemas.microsoft.com/claims/authnmethodsproviders",
Value = "AzureMfaAuthentication");
not exists([Type == "https://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid",
Value=="YourGroupSid"]) => issue(Type =
"https://schemas.microsoft.com/claims/authnmethodsproviders", Value =
"AzureMfaServerAuthentication");'
```

#### MFA Server を AD FS の認証プロバイダーとして無効にする

この変更により、多要素認証Microsoft Entra認証プロバイダーとしてのみ使用されるようになります。

1. **AD FS 管理コンソール**を開きます。
2. **[サービス]** で、**[認証方法]** を右クリックし、**[多要素認証方法の編集]** を選択します。
3. **Azure Multi-Factor Authentication Server** チェック ボックスをオフにします。

#### MFA Server の使用を停止する

エンタープライズ サーバーの使用停止プロセスに従って、環境内の MFA Server を削除します。

MFA Server の使用停止時に考えられる考慮事項は次のとおりです。

- サーバーを削除する前に、MFA Server のログを調べて、ユーザーまたはアプリケーションによってそれが使用されていないのを確認することをお勧めします。
- サーバー上のコントロール パネルから Multi-Factor Authentication Server をアンインストールします。
- 必要に応じて、最初のバックアップ後に残っているログとデータ ディレクトリをクリーンアップします。
- Multi-Factor Authentication Web Server SDK をアンインストールします (該当する場合は、inetpub\wwwroot\MultiFactorAuthWebServiceSdk や MultiFactorAuth ディレクトリに残っているファイルも)。
- 8.0.x より前のバージョンの MFA Server の場合は、Multi-Factor Auth Phone App Web Service の削除が必要になる場合もあります。

### アプリケーション認証をMicrosoft Entra IDに移動する

MFA およびユーザー認証と共にすべてのアプリケーション認証を移行する場合は、オンプレミス インフラストラクチャのかなりの部分を削除して、コストとリスクを減らすことができます。 すべてのアプリケーション認証を移動する場合は、「AD FS を準備する」ステージをスキップして、MFA の移行を簡略化できます。

すべてのアプリケーション認証を移動するプロセスを次の図に示します。

[Image: アプリケーションをMicrosoft Entra多要素認証に移行するプロセス。]

移行前にすべてのアプリケーションを移動できない場合は、開始前にできるだけ多くのアプリケーションを移動します。 アプリケーションを Azure に移行する方法の詳細については、「[Resources for migrating applications to Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migration-resources)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-migrate-mfa-server-to-mfa-with-federation"} -->
## フェデレーションを使用して多要素認証Microsoft Entraに移行する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-migrate-mfa-server-to-mfa-with-federation
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: オンプレミスの MFA Server からフェデレーションを使用した多要素認証をMicrosoft Entraに移行するためのステップ バイ ステップ ガイダンス

多要素認証 (MFA) ソリューションをMicrosoft Entra IDに移行することは、クラウドへの移行における優れた第一歩です。 今後、ユーザー認証のMicrosoft Entra IDへの移行も検討してください。 詳細については、クラウド認証を使用して多要素認証をMicrosoft Entraに移行するプロセスを参照してください。

フェデレーションMicrosoft Entra多要素認証に移行するには、Microsoft Entra多要素認証プロバイダーが AD FS にインストールされます。 Microsoft Entra IDリライパーティートラストとその他のリライパーティートラストは、移行されたユーザーに対してMicrosoft Entra多要素認証を使用するように構成されています。

以下の図は、移行プロセスを示しています。

[Image: 移行プロセスのフロー チャート。このドキュメントのプロセス領域と見出しは同じ順序です]

### 移行グループを作成する

新しい条件付きアクセス ポリシーを作成するには、それらのポリシーをグループに割り当てる必要があります。 この目的Microsoft Entraセキュリティ グループまたはMicrosoft 365 グループを使用できます。 新しいものを作成または同期することもできます。

ユーザーをMicrosoft Entraの多要素認証に段階的に移行するために、Microsoft Entraのセキュリティ グループも必要です。 これらのグループは、要求規則で使用されます。

セキュリティに使用されるグループを再利用しないでください。 セキュリティ グループを使用して、条件付きアクセス ポリシーで高価値アプリのグループをセキュリティで保護する場合、その目的のみにグループを使用してください。

### AD FS を準備する

#### AD FS サーバー ファームを 2019、FBL 4 にアップグレードする

AD FS 2019 では、アプリケーションなど、証明書利用者に対して追加の認証方法を指定できます。 グループ メンバーシップを使用して、認証プロバイダーを決定します。 追加の認証方法を指定することで、移行中に他の認証をそのまま維持しながら、多要素認証Microsoft Entraに移行できます。 詳細については、「[WID データベースを使用したWindows Server 2016での AD FS へのアップグレード](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/deployment/upgrading-to-ad-fs-in-windows-server)を参照してください。 この記事では、ファームを AD FS 2019 にアップグレードし、FBL を 4 にアップグレードする方法について説明します。

#### 多要素認証を呼び出すように要求規則Microsoft Entra構成する

多要素認証Microsoft Entra追加の認証方法になったので、それを使用するユーザーのグループを割り当てることができます。 これを行うには、要求規則を構成します。これには、信頼するパーティの信頼 (relying party trusts) とも呼ばれます。 グループを使用すると、グローバルまたはアプリケーションによって、どの認証プロバイダーが呼び出されるかを制御できます。 たとえば、統合されたセキュリティ情報を登録したユーザーに対してMicrosoft Entra多要素認証を呼び出し、そうでないユーザーに対して MFA Server を呼び出すことができます。

注

要求規則には、オンプレミスのセキュリティ グループが必要です。 要求規則を変更する前に、それらをバックアップします。

##### 規則をバックアップする

新しい要求規則を構成する前に、規則をバックアップします。 これらの規則ルールは、クリーンアップ手順の一部として復元する必要があります。

構成によっては、規則をコピーし、移行用に作成している新しい規則を追加することが必要になる場合もあります。

グローバル規則を表示するには、次のように実行します。

```powershell
Get-AdfsAdditionalAuthenticationRule
```

証明書利用者の信頼を表示するには、次のコマンドを実行し、RPTrustName を証明書利用者の信頼の要求規則の名前に置き換えます。

```powershell
(Get-AdfsRelyingPartyTrust -Name "RPTrustName").AdditionalAuthenticationRules 
```

##### アクセス制御ポリシー

注

グループ メンバーシップに基づいて特定の認証プロバイダーが呼び出されるように、アクセス制御ポリシーを構成することはできません。

アクセス制御ポリシーから追加の認証規則に切り替えるには、MFA Server 認証プロバイダーを使用して、各証明書利用者信頼に対して次のコマンドを実行します。

```powershell
Set-AdfsRelyingPartyTrust -TargetName AppA -AccessControlPolicyName $Null
```

このコマンドは、ロジックを現在のAccess Control ポリシーから追加の認証規則に移動します。

##### グループを設定して SID を検索する

Microsoft Entra の多要素認証を呼び出すには、対象のユーザーを配置する特定のグループが必要です。 そのグループのセキュリティ識別子 (SID) が必要になります。

グループ SID を検索するには、次のコマンドとグループ名を使用します。

`Get-ADGroup "GroupName"`

##### Microsoft Entra の多要素認証を呼び出すために要求規則を設定する

次の PowerShell コマンドレットは、企業ネットワークにない場合に、グループ内のユーザーに対してMicrosoft Entra多要素認証を呼び出します。 "YourGroupSid" を、上記のコマンドレットを実行して検出された SID に置き換えます。

[2019 で追加の認証プロバイダーを選択する方法](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/overview/whats-new-active-directory-federation-services-windows-server)を必ず確認してください。

重要

要求規則をバックアップする

##### グローバル要求規則を設定する

次の PowerShell コマンドレットを実行します。

```powershell
(Get-AdfsRelyingPartyTrust -Name "RPTrustName").AdditionalAuthenticationRules
```

コマンドを実行すると、信頼できるパーティ信頼に関連する現在の追加認証ルールが返されます。 現在の要求規則に次の規則を追加します。

```console
c:[Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid", Value == 
"YourGroupSID"] => issue(Type = "http://schemas.microsoft.com/claims/authnmethodsproviders", 
Value = "AzureMfaAuthentication");
not exists([Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid", 
Value=="YourGroupSid"]) => issue(Type = 
"http://schemas.microsoft.com/claims/authnmethodsproviders", Value = 
"AzureMfaServerAuthentication");'
```

次の例では、ユーザーがネットワークの外部から接続するときに、MFA を要求するように現在の要求規則が構成されていることを前提としています。 この例には、追加する必要がある規則が含まれています。

```PowerShell
Set-AdfsAdditionalAuthenticationRule -AdditionalAuthenticationRules 'c:[type == 
"http://schemas.microsoft.com/ws/2012/01/insidecorporatenetwork", value == "false"] => issue(type = 
"http://schemas.microsoft.com/ws/2008/06/identity/claims/authenticationmethod", value = 
"http://schemas.microsoft.com/claims/multipleauthn" );
 c:[Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid", Value == 
"YourGroupSID"] => issue(Type = "http://schemas.microsoft.com/claims/authnmethodsproviders", 
Value = "AzureMfaAuthentication");
not exists([Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid", 
Value=="YourGroupSid"]) => issue(Type = 
"http://schemas.microsoft.com/claims/authnmethodsproviders", Value = 
"AzureMfaServerAuthentication");'
```

##### アプリケーションごとの要求規則を設定する

この例では、特定の証明書利用者信頼 (アプリケーション) の要求規則を変更し、追加する必要がある情報が含まれています。

```PowerShell
Set-AdfsRelyingPartyTrust -TargetName AppA -AdditionalAuthenticationRules 'c:[type == 
"http://schemas.microsoft.com/ws/2012/01/insidecorporatenetwork", value == "false"] => issue(type = 
"http://schemas.microsoft.com/ws/2008/06/identity/claims/authenticationmethod", value = 
"http://schemas.microsoft.com/claims/multipleauthn" );
c:[Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid", Value == 
"YourGroupSID"] => issue(Type = "http://schemas.microsoft.com/claims/authnmethodsproviders", 
Value = "AzureMfaAuthentication");
not exists([Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid", 
Value=="YourGroupSid"]) => issue(Type = 
"http://schemas.microsoft.com/claims/authnmethodsproviders", Value = 
"AzureMfaServerAuthentication");'
```

#### AD FS Microsoft Entra認証プロバイダーとして多要素認証を構成する

AD FS Microsoft Entra多要素認証を構成するには、各 AD FS サーバーを構成する必要があります。 ファームに複数の AD FS サーバーがある場合は、Microsoft Entra PowerShell を使用してリモートで構成できます。

このプロセスの段階的な手順については、「[AD FS サーバーを構成する](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configure-ad-fs-and-azure-mfa)」の記事「[AD FS を使用した認証プロバイダーとしての Microsoft Entra 多要素認証の構成](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configure-ad-fs-and-azure-mfa)」を参照してください。

サーバーを構成したら、追加の認証方法として多要素認証Microsoft Entra追加できます。

[Image: [認証方法の編集]画面のスクリーンショットで、Microsoft Entra 多要素認証と Azure 多要素認証サーバーが選択されています。]

### Microsoft Entra IDを準備して移行を実装する

このセクションでは、ユーザーの MFA 設定を移行する前の最後の手順について説明します。

#### federatedIdpMfaBehavior を enforceMfaByFederatedIdp に設定する

フェデレーション ドメインの場合、MFA はMicrosoft Entra 条件付きアクセスまたはオンプレミスのフェデレーション プロバイダーによって適用される場合があります。 各フェデレーション ドメインには、**federatedIdpMfaBehavior** という名前のMicrosoft Graph PowerShell セキュリティ設定があります。 **federatedIdpMfaBehavior** を `enforceMfaByFederatedIdp` に設定して、Microsoft Entra ID がフェデレーション ID プロバイダーによって実行される MFA を受け入れられるようにします。 フェデレーション ID プロバイダーが MFA を実行しなかった場合、Microsoft Entra IDは、MFA を実行する要求をフェデレーション ID プロバイダーにリダイレクトします。 詳細については、[federatedIdpMfaBehavior](https://learn.microsoft.com/ja-jp/graph/api/resources/internaldomainfederation?view=graph-rest-beta&preserve-view=true#federatedidpmfabehavior-values) に関する記事を参照してください。

注

**federatedIdpMfaBehavior** 設定は、**New-MgDomainFederationConfiguration** コマンドレットの [SupportsMfa](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/new-mgdomainfederationconfiguration) プロパティの新しいバージョンです。

**SupportsMfa** プロパティを設定するドメインの場合、これらの規則によって、**federatedIdpMfaBehavior** と **SupportsMfa** の連携方法が決まります。

- **federatedIdpMfaBehavior** と **SupportsMfa** の切り替えはサポートされていません。
- **federatedIdpMfaBehavior** プロパティが設定されると、Microsoft Entra IDは **SupportsMfa** 設定を無視します。
- **federatedIdpMfaBehavior** プロパティが設定されていない場合、Microsoft Entra IDは引き続き **SupportsMfa** 設定を優先します。
- **federatedIdpMfaBehavior** または **SupportsMfa** が設定されていない場合、Microsoft Entra IDは既定で `acceptIfMfaDoneByFederatedIdp` 動作になります。

**Get-MgDomainFederationConfiguration** を使用して、[federatedIdpMfaBehavior](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdomainfederationconfiguration?view=graph-powershell-1.0&preserve-view=true&viewFallbackFrom=graph-powershell-beta) の状態を確認できます。

```powershell
Get-MgDomainFederationConfiguration –DomainID contoso.com
```

**Get-MgDomainFederationConfiguration** を使用して [SupportsMfa](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdomainfederationconfiguration) フラグの状態を確認することもできます。

```powershell
Get-MgDomainFederationConfiguration –DomainName contoso.com
```

次の例では、Graph PowerShell を使用して **federatedIdpMfaBehavior** を `enforceMfaByFederatedIdp` に設定する方法を示します。

##### リクエスト

```http
PATCH https://graph.microsoft.com/beta/domains/contoso.com/federationConfiguration/6601d14b-d113-8f64-fda2-9b5ddda18ecc
Content-Type: application/json
{
  "federatedIdpMfaBehavior": "enforceMfaByFederatedIdp"
}
```

##### [応答]

>
> **注:** ここに示されている応答オブジェクトは、読みやすくするために短縮されている可能性があります。

```http
HTTP/1.1 200 OK
Content-Type: application/json
{
  "@odata.type": "#microsoft.graph.internalDomainFederation",
  "id": "6601d14b-d113-8f64-fda2-9b5ddda18ecc",
   "issuerUri": "http://contoso.com/adfs/services/trust",
   "metadataExchangeUri": "https://sts.contoso.com/adfs/services/trust/mex",
   "signingCertificate": "A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0u",
   "passiveSignInUri": "https://sts.contoso.com/adfs/ls",
   "preferredAuthenticationProtocol": "wsFed",
   "activeSignInUri": "https://sts.contoso.com/adfs/services/trust/2005/usernamemixed",
   "signOutUri": "https://sts.contoso.com/adfs/ls",
   "promptLoginBehavior": "nativeSupport",
   "isSignedAuthenticationRequestRequired": true,
   "nextSigningCertificate": "A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0u",
   "signingCertificateUpdateStatus": {
        "certificateUpdateResult": "Success",
        "lastRunDateTime": "2021-08-25T07:44:46.2616778Z"
    },
   "federatedIdpMfaBehavior": "enforceMfaByFederatedIdp"
}
```

#### 必要に応じて条件付きアクセス ポリシーを構成する

条件付きアクセスを使用して、ユーザーに MFA をいつ要求するかを決定する場合は、ポリシーを変更する必要はありません。

フェデレーション ドメインで SupportsMfa が false に設定されている場合は、Microsoft Entra ID証明書利用者信頼に関する要求規則を分析し、同じセキュリティ目標をサポートする条件付きアクセス ポリシーを作成します。

AD FS と同じ制御を適用する条件付きアクセス ポリシーを作成した後、Microsoft Entra ID証明書利用者の要求規則のカスタマイズをバックアップおよび削除できます。

詳細については、次のリソースを参照してください。

- [条件付きアクセスのデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/plan-conditional-access)
- [一般的な条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policy-common)

### Microsoft Entra多要素認証にユーザーを登録する

このセクションでは、ユーザーが統合セキュリティに登録する (MFA とセルフサービス パスワード リセット) 方法と、MFA 設定を移行する方法について説明します。 Microsoft Authenticatorは、パスワードレス モードと同様に使用できます。 また、いずれかの登録方法を使用して MFA の 2 番目の要素として使用することもできます。

#### 統合されたセキュリティ登録に登録する (推奨)

ユーザーに統合されたセキュリティ情報に登録してもらうことをお勧めします。これは、MFA と SSPR の両方に認証方法とデバイスを登録するための単一の場所です。

Microsoftには、統合された登録プロセスを通じてユーザーをガイドするためにユーザーに提供できる通信テンプレートが用意されています。 これには、メール、ポスター、テーブル テント、他のさまざまなアセットのテンプレートなどがあります。 ユーザーは、`https://aka.ms/mysecurityinfo` で各自の情報を登録します。これにより、統合されたセキュリティ登録の画面に移動します。

信頼できるデバイスまたは場所から登録する必要がある[条件付きアクセスを使用して、セキュリティ登録プロセスを保護する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-security-info-registration)ことをお勧めします。 登録状態の追跡については、「Microsoft Entra ID のAuthentication メソッドアクティビティ」を参照してください。

注

信頼されていない場所またはデバイスから、統合されたセキュリティ情報を登録する必要があるユーザーについては、一時アクセス パスを発行したり、代わりにポリシーから一時的に除外したりできます。

#### MFA Server から MFA 設定を移行する

[MFA Server Migration ユーティリティ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-server-migration-utility)を使用して、MFA Server からMicrosoft Entra IDにユーザーの登録済み MFA 設定を同期できます。 電話番号、ハードウェア トークン、デバイス登録 (Microsoft Authenticator設定など) を同期できます。

#### ユーザーを適切なグループに追加する

- 新しい条件付きアクセス ポリシーを作成した場合は、それらのグループに適切なユーザーを追加します。
- 要求規則にオンプレミスのセキュリティ グループを作成した場合は、それらのグループに適切なユーザーを追加します。

セキュリティに使用されるグループを再利用しないことをお勧めします。 セキュリティ グループを使用して、条件付きアクセス ポリシーで高価値アプリのグループをセキュリティで保護する場合、その目的のみにグループを使用してください。

### 監視

Microsoft Entra多要素認証の登録は、[認証方法の使用状況と分析情報のレポート](https://portal.azure.com/)を使用して監視できます。 このレポートは、Microsoft Entra IDにあります。 **[監視]** を選択し、**[使用状況と分析情報]** を選択します。

[使用状況と分析情報] で、**[認証方法]** を選択します。

Microsoft Entra 多要素認証の詳細な登録情報については、「登録」タブを参照してください。「**Azure 多要素認証が可能なユーザー**」というハイパーリンクを選択することで、登録済みユーザーの一覧を詳しく見ることができます。

[Image: MFA へのユーザー登録を示す認証方法アクティビティ画面の画像]

### クリーンアップ手順

Microsoft Entra多要素認証への移行が完了し、MFA サーバーを使用停止にする準備ができたら、次の 3 つの操作を行います。

1. AD FS の要求規則を移行前の構成に戻し、MFA Server 認証プロバイダーを削除します。
2. AD FS の認証プロバイダーとして MFA Server を 削除します。 これにより、すべてのユーザーが多要素認証Microsoft Entra使用できるようになります。これは、有効になっている唯一の追加認証方法になります。
3. MFA Server の使用を停止します。

#### AD FS の要求規則を元に戻し、MFA Server 認証プロバイダーを削除する

「Microsoft Entra の多要素認証を呼び出し、バックアップされた要求規則に戻し、AzureMFAServerAuthentication の要求規則を削除するには、「要求規則を構成する」の手順に従います。」

たとえば、ルールから次を削除します。

```console
c:[Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid", Value ==
"**YourGroupSID**"] => issue(Type = "http://schemas.microsoft.com/claims/authnmethodsproviders",
Value = "AzureMfaAuthentication");
not exists([Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid",
Value=="YourGroupSid"]) => issue(Type =
"http://schemas.microsoft.com/claims/authnmethodsproviders", Value =
"AzureMfaServerAuthentication");'
```

#### MFA Server を AD FS の認証プロバイダーとして無効にする

この変更により、多要素認証Microsoft Entra認証プロバイダーとしてのみ使用されるようになります。

1. **AD FS 管理コンソール**を開きます。
2. **[サービス]** で、**[認証方法]** を右クリックし、**[多要素認証方法の編集]** を選択します。
3. **Azure Multi-Factor Authentication Server** の横にあるチェック ボックスをオフにします。

#### MFA Server の使用を停止する

エンタープライズ サーバーの使用停止プロセスに従って、環境内の MFA Server を削除します。

MFA Server の使用停止時に考えられる考慮事項は次のとおりです。

- サーバーを削除する前に、MFA Server のログを確認し、ユーザーまたはアプリケーションで MFA Server が使用されていないことを確認します。
- サーバー上のコントロール パネルから Multi-Factor Authentication Server をアンインストールする
- 必要に応じて、最初のバックアップ後に残っているログとデータ ディレクトリをクリーンアップします。
- inetpub\wwwroot\MultiFactorAuthWebServiceSdk ディレクトリや MultiFactorAuth ディレクトリに残っているファイルを含め、必要に応じて多要素認証 Web Server SDK をアンインストールします (該当する場合)。
- 8.0 より前の MFA サーバー バージョンの場合は、多要素認証電話アプリ Web サービスの削除も必要になる場合があります
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-plan-password-scramble-phishing-resistant-passwordless-authentication"} -->
## Microsoft Entra ID からパスワードを削除する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-password-scramble-phishing-resistant-passwordless-authentication
- Service: entra-id / authentication
- Article date: 2026-01-28
- Summary: Microsoft Entra ID を使用する組織にパスワードレスおよびフィッシングに強い認証を展開するために、パスワードのスクランブリングを使用して Entra からパスワードの使用を削除します。

パスワードは、最も安全性の低い認証方法の 1 つです。 フィッシング、資格情報の詰め込み、ブルート フォース攻撃、ソーシャル エンジニアリングなど、さまざまな脅威に対して脆弱です。 Microsoft Entra ID でのパスワードレス認証の利点を実現するには、テナントのサインイン オプションとしてパスワードが使用できなくなったことを確認する必要があります。

パスワードのスクランブリングにより、ユーザーはパスワードを使用して認証できなくなります。 Windows Hello for Business、FIDO2 セキュリティ キー、Microsoft Authenticator のパスキーなど、より安全なフィッシングに強いパスワードレス資格情報を強制的に使用します。 スクランブリング後のパスワードの既知の参照がないため、悪いアクターの攻撃対象領域が減少します。

この記事では、オンプレミスの Active Directory Domain Services (AD DS) から同期されるハイブリッド ユーザーと Microsoft Entra ID のクラウド専用ユーザーの両方のパスワードを取得する方法について説明します。

### [前提条件]

- Microsoft Entra Connect の同期 [バージョン 2.4.18.0 以降](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization#password-hash-synchronization-and-smart-card-authentication)

### オンプレミスの AD DS から同期されたハイブリッド ユーザー アカウントのパスワードをスクランブリングする

オンプレミスの AD DS から Microsoft Entra ID に同期されたユーザーを持つ組織は、オンプレミス環境でユーザー パスワードを奪い合う必要があります。これは、オンプレミスが同期されたユーザー アカウントとそのパスワードの権限のソースであるためです。 組織が [クラウド認証](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/choose-ad-authn) を使用し、パスワード ハッシュを Microsoft Entra ID に同期する場合、これらのスクランブルされたパスワードもクラウドに同期されます。 同期プロセスにより、ユーザーはオンプレミスでもクラウドでもパスワードを認識できなくなり、ユーザーの使用を防ぎ、より安全なフィッシングに対するパスワードレス資格情報の使用を促進できます。

### スクリプト化されたランダム値を使用してオンプレミスのユーザー パスワードをスクランブリングする

Active Directory では、ユーザー アカウントからパスワード属性を削除することはできません。 したがって、パスワードの使用を防ぐために、定期的にパスワードを奪い合うことができます。

認証にまだパスワードが必要なレガシ アプリケーションがある場合、ユーザーはセルフサービス パスワード リセット (SSPR) を使用してパスワードを既知の状態に設定し、パスワードが再び奪い合われるまで、これらのアプリに一定期間アクセスできます。

次のスクリプトを使用して、ユーザーが既知の状態にリセットするすべてのパスワードを定期的に取り戻すことができます。

このスクリプトを使用すると、AD DS ドメイン内のユーザーのパスワードをスクランブリングできます。 64 文字のランダムなパスワードが生成され、変数名$samAccountNameで指定されたユーザーに対して設定されます。 スクリプト内の$samAccountName変数を変更して、適切なユーザーをターゲットにする必要があります。 オンプレミスの AD DS で適切なアクセス許可を持つ管理者アカウントの資格情報を使用します。

注意事項

セキュリティで保護された信頼できる環境からのみスクリプトを実行し、スクリプトがログに記録されていないことを確認します。 スクリプトが実行されるホストを、ドメイン コントローラーと同じレベルのセキュリティで特権ホストとして扱います。

```PowerShell
$samAccountName = <sAMAccountName of the user>

Import-Module ActiveDirectory

function Generate-RandomPassword{
    [CmdletBinding()]
    param (
      [int]$Length = 64
    )
  $chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[]{};:,.<>/?\|`~"
  $random = New-Object System.Random
  $password = ""
  for ($i = 0; $i -lt $Length; $i++) {
    $index = $random.Next(0, $chars.Length)
    $password += $chars[$index]
  }
  return $password
}

Import-Module ActiveDirectory

$NewPassword = ConvertTo-SecureString -String (Generate-RandomPassword) -AsPlainText -Force

Set-ADAccountPassword -Identity $samAccountName -NewPassword $NewPassword -Reset
```

注

パスワード スクランブリング スクリプトの実行頻度が現在のパスワード有効期間ポリシーよりも低い場合は、パスワードの有効期間をこの頻度より長くするか、パスワードの有効期間を 0 に設定してパスワードの有効期限を無効にすることを検討する必要があります。

### Microsoft Entra ID でクラウド ユーザー アカウントのパスワードをランダム化する

クラウドベースのパスワードレス ユーザーは、パスワードをランダムな値に設定する必要があります。 パスワードをランダム化すると、ユーザーはパスワードを認識して認証に使用できなくなります。 必要に応じて、エンド ユーザーがパスワードを必要とするアプリケーションに遭遇した場合に、パスワードのリセットを許可できます。 スクリプトを定期的に実行して、ユーザーが既知の状態にリセットしたすべてのパスワードをスクランブリングします。

次のサンプル PowerShell スクリプトは、64 文字のランダム なパスワードを生成し、Microsoft Entra ID に対して **$userId** 変数名で指定されたユーザーに設定します。 スクリプトの **$userId** 変数を環境に合わせて変更し、PowerShell セッションで実行します。 Microsoft Entra ID に対する認証を求められたら、パスワードをリセットできるロールを持つアカウントの資格情報を使用します。

```PowerShell
$userId = "<UPN of the user>"

function Generate-RandomPassword{
    [CmdletBinding()]  
    param (  
      [int]$Length = 64  
    )  
  $chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[]{};:,.<>/? \|`~"  
  $rng = [System.Security.Cryptography.RandomNumberGenerator]::Create()  
  $bytes = New-Object byte[] $Length  
  $rng.GetBytes($bytes)  
  $password = -join ($bytes | ForEach-Object { $chars[$_ % $chars.Length] })  
  $rng.Dispose()  
  return $password  
}

Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser -Force
Install-Module Microsoft.Graph -Scope CurrentUser
Import-Module Microsoft.Graph.Users.Actions
Connect-MgGraph -Scopes "UserAuthenticationMethod.ReadWrite.All" -NoWelcome

$passwordParams = @{
 UserId = $userId
 AuthenticationMethodId = "28c10230-6103-485e-b985-444c60001490"
 NewPassword = Generate-RandomPassword
}

Reset-MgUserAuthenticationMethodPassword @passwordParams
```

注意事項

セキュリティで保護された信頼できる環境からのみスクリプトを実行し、スクリプトがログに記録されていないことを確認します。 スクリプトが実行されるホストを、ドメイン コントローラーと同じレベルのセキュリティで特権ホストとして扱います。

### フィッシング対策認証を完全に適用する組織に関するその他の考慮事項

すべてのアプリケーションとシナリオがパスワードなしの資格情報と互換性があるため、組織でパスワードが不要になった場合は、セルフサービスパスワード回復オプションをユーザーに提供する必要がなくなった可能性があります。 ユーザーがクランブリングされたパスワードをオーバーライドし、パスワードに再度アクセスできるようにするツールを無効にすることを検討してください。

- [Microsoft Entra](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr-writeback#clean-up-resources) セルフサービス パスワード リセットなど、セルフサービス パスワード リセット (SSPR) ツールを無効にします。
- [Microsoft Entra からオンプレミスの Active Directory への](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr-writeback#clean-up-resources)パスワード ライトバックを無効にします。
- Microsoft Entra パスワード ポリシーを[無期限に設定します](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-policy)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-plan-persona-phishing-resistant-passwordless-authentication"} -->
## Microsoft Entra ID での耐フィッシング パスワードレス認証の展開における特定のペルソナに関する考慮事項 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-persona-phishing-resistant-passwordless-authentication
- Service: entra-id / authentication
- Article date: 2025-10-30
- Summary: Microsoft Entra ID を使用する組織向けにパスワードレスで耐フィッシング認証を展開するためのペルソナ固有のガイダンス。

各ペルソナには、耐フィッシング パスワードレス展開中に共通して発生する固有の課題と考慮事項があります。 対応が必要となるペルソナを特定するときは、展開のプロジェクト計画策定にこれらの考慮事項を組み込む必要があります。 次のセクションでは、各ペルソナに固有のガイダンスを提示します。

### 管理者と高度に規制されたユーザーの展開

管理者と厳しく規制されたユーザーは、組織内で最もセキュリティに敏感なペルソナを表し、フィッシングに強いパスワードレス展開時に特別な考慮事項を必要とします。 通常、これらのユーザーは特権アクセスを操作したり、機密データを処理したり、厳格なコンプライアンス要件を持つ環境で動作したりします。 利便性よりもセキュリティに優先順位を付けますが、デプロイを成功させるには、独自の課題に対処するための慎重な計画が必要です。

#### IT プロ/DevOps ワーカー

IT 担当者と DevOps ワーカーは、特にリモート アクセスと複数のユーザー アカウントに依存しているため、インフォメーション ワーカーとは異なると見なされます。 IT プロについて、耐フィッシング パスワードレスに関して生じる課題の多くは、システムへのリモート アクセスおよび自動化の実行機能に対するニーズの高さが原因となります。

[Image: IT プロ ワーカーの要件の例を示す図。]

特にこのペルソナについては、RDP でのフィッシング耐性のためにサポートされたオプションについて理解してください。

ユーザーコンテキストで実行され、従って現在 MFA を使用していないスクリプトをユーザーがどこで使用しているか必ず理解してください。 サービス プリンシパルとマネージド ID を使用して自動化を実行する適切な方法を IT プロに指導します。 また、IT プロや他の専門家が新しいサービス プリンシパルを要求し、適切なアクセス許可を割り当てられるようにするプロセスも検討する必要があります。

- [Azure リソース用マネージド ID とは](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)
- [Microsoft Entra ID でのサービス プリンシパルのセキュリティ保護](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-principal)

##### IT プロ/DevOps ワーカーの展開フロー

IT 担当者/DevOps worker のデプロイ フローのフェーズ 1 から 3 は、通常、ユーザーのプライマリ アカウントに関して前に示したように、標準のデプロイ フローに従う必要があります。 IT プロ/DevOps ワーカーには、多くの場合、さまざまな考慮が必要とされるセカンダリ アカウントがあります。 プライマリ アカウントの環境での必要に応じて、各手順で使用するメソッドを調整します。

[Image: IT プロ/DevOps ワーカーの展開フローを示す図。]

1. フェーズ 1: オンボーディング
    1. 一時アクセス パスの取得に使用される Microsoft Entra Verified ID サービス
2. フェーズ 2: ポータブル認証情報の登録
    1. Microsoft Authenticator アプリのパスキー (推奨)
    2. FIDO2 セキュリティ キー
3. フェーズ 3: ローカル認証情報の登録
    1. Windows Hello for Business
    2. プラットフォーム SSO セキュア エンクレーブ キー

IT 担当者/DevOps ワーカーがセカンダリ アカウントを持っている場合は、それらのアカウントの処理方法が異なる場合があります。 たとえば、セカンダリ アカウントの場合、コンピューティング デバイスで代替の移植可能な認証情報を使用して、ローカルの認証情報を完全に省略することを選択できます。

[Image: IT プロ/DevOps ワーカーの代替展開フローを示す図。]

1. フェーズ 1: オンボーディング
    1. 一時アクセス パスの取得に使用される Microsoft Entra Verified ID サービス (推奨)
    2. IT プロ/DevOps ワーカーにセカンダリ アカウントの TAP を提供する代替プロセス
2. フェーズ 2: ポータブル認証情報の登録
    1. Microsoft Authenticator のパスキー (推奨)
    2. FIDO2 セキュリティ キー
    3. スマート カード
3. フェーズ 3: ローカルの認証情報に代わる移植可能な認証情報の使用

#### 高度規制ワーカー

高度規制ワーカーは、ロックダウンされたデバイスでの作業、ロックダウンされた環境での作業、特別な規制要件に従うことの必要などがあるため、平均的なインフォメーション ワーカーよりも多くの課題を抱えています。

[Image: 高度規制ワーカーの要件の例を示す図。]

高度規制ワーカーは、規制下の環境には PKI とスマート カード インフラストラクチャがすでに大規模に導入されているため、スマート カードを使用することがよくあります。 ただし、スマート カードが望ましく必要とされる場合と、Windows Hello for Business などのよりユーザー フレンドリーなオプションとのバランスを取るべきな場合とを考慮してください。

##### PKI を使用しない、厳しく規制されたワーカーの展開フロー

認定資格証、スマート カード、PKI の使用を想定していない場合、高度規制ワーカーの展開は、インフォメーション ワーカーの展開とよく似たものになります。 詳細については、「インフォメーション ワーカー」を参照してください。

##### PKI を使用した厳しく規制された従業員配備フロー

認定資格証、スマート カード、PKI を使用する予定の場合、高度規制ワーカーの展開フローは、重要な場所においては、インフォメーション ワーカーのセットアップ フローとは通常異なります。 そこでは、一部のユーザーがローカルの認証方法を使用できるかどうかを識別する必要性が高くなります。 同様に、スマート カードなど、インターネット接続なしで動作するポータブルのみの認証情報を必要とするユーザーがそこに存在するかを特定する必要があります。 必要に応じて、展開フローをさらに調整し、環境内で識別されるさまざまなユーザー ペルソナに合わせてカスタマイズできます。 環境での必要に応じて、各手順で使用するメソッドを調整します。

[Image: 高度規制ワーカーの展開フローを示す図。]

1. フェーズ 1: オンボーディング
    1. 一時アクセス パスの取得に使用される Microsoft Entra Verified ID サービス (推奨)
    2. ID 証明プロセスに従う、ユーザーに代わってのスマート カードの登録
2. フェーズ 2: ポータブル認証情報の登録
    1. スマート カード (推奨)
    2. FIDO2 セキュリティ キー
    3. Microsoft Authenticator のパスキー
3. フェーズ 3 (オプション): ローカル認証情報の登録
    1. オプション: Windows Hello for Business
    2. オプション: Windows 用 Microsoft Entra パスキー
    3. オプション: プラットフォーム SSO セキュア エンクレーブ キー

注

ユーザーには少なくとも 2 つの認証情報が登録されていることを常におすすめしています。 これにより、一方の認証情報に何らかの問題が発生した場合でも、バックアップの認証情報が使用できます。 高度に規制された労働者の場合、スマートカードを導入するだけでなく、パスキーまたは Windows Hello for Business を導入することが推奨されます。

### 管理者以外のユーザーの展開

管理者以外のユーザーの展開には、適切な通信とサポートが必要です。 これには通常、特定のアプリを自分の携帯電話にインストールするようユーザーを誘導し、ユーザーがアプリを使用しない場合にはセキュリティ キーを配布し、生体認証に関する懸念に対処し、ユーザーが認証情報の一部または全部の紛失から回復できるようにするプロセスを開発することなどがあります。

#### インフォメーション ワーカー

通常、インフォメーション ワーカーは要件が最もシンプルであり、耐フィッシング パスワードレス展開を最も楽に開始できます。 ただし、これらのユーザーに対して展開するとき頻繁に発生する問題がいくつかあります。 たとえば、次のような場合です。

[Image: インフォメーション ワーカーの要件の例を示す図。]

##### 情報作業者の展開フロー

インフォメーション ワーカーのデプロイ フローの手順 1 から 3 は、通常、次の図に示すように、標準のデプロイ フローに従う必要があります。 環境での必要に応じて、各手順で使用するメソッドを調整します。

[Image: インフォメーション ワーカーの展開フローを示す図。]

1. 入社手続き
    1. 一時アクセス パスの取得に使用される Microsoft Entra Verified ID サービス
2. ポータブル認証情報の登録
    1. 同期されたパスキー (優先)
    2. Microsoft Authenticator のパスキー
    3. FIDO2 セキュリティ キー
3. ローカル資格情報の登録
    1. Windows Hello for Business
    2. Windows での Microsoft Entra パスキー
    3. プラットフォーム SSO セキュア エンクレーブ キー

#### フロントライン ワーカー

フロントライン ワーカーは、認証情報の移植性に関するニーズが高く、小売または製造の状況で持ち運べるデバイスに制限があるため、多くの場合、より複雑な要件を抱えています。 同期されたパスキーは、現場担当者にとって最適なオプションです。 同期されたパスキーを使用できないシナリオでは、セキュリティ キーとスマート カードは他のフィッシング対策オプションです。

[Image: フロントライン ワーカーの要件の例を示す図。]

##### フロントライン ワーカーの展開フロー

現場担当者向けのデプロイ フローの手順 1 から 3 は、通常、移植可能な資格情報を強調する変更されたフローに従う必要があります。 フロントライン ワーカーの多くは常設のコンピューティング デバイスを持たないことが考えられ、Windows または Mac ワークステーション上のローカルな認証情報を必要としません。 代わりに、デバイスからデバイスへと持ち運べる移植可能な認証情報に大幅に依存します。 環境での必要に応じて、各手順で使用するメソッドを調整します。

[Image: フロントライン ワーカーの展開フローを示す図。]

1. フェーズ 1: オンボーディング
    1. FIDO2 セキュリティ キーによる代理登録 (推奨)
    2. 一時アクセス パスの取得に使用される Microsoft Entra Verified ID サービス
2. フェーズ 2: ポータブル認証情報の登録
    1. 同期されたパスキー (優先)
    2. FIDO2 セキュリティ キー
    3. Microsoft Authenticator のパスキー
    4. スマート カード
3. フェーズ 3 (オプション): ローカル認証情報の登録
    1. オプション: Windows Hello for Business
    2. 省略可: Windows での Microsoft Entra パスキー
    3. オプション: プラットフォーム SSO セキュア エンクレーブ キー

### 一般的な考慮事項

生体認証に関する懸念事項に対処するときは、Windows Hello for Business などのテクノロジーによる生体認証の処理方法を理解していることを確認してください。 生体認証データはデバイス上のローカルにのみ格納され、盗まれた場合でも生の生体認証データに変換することはできません。 詳細については、「 [Windows Hello for Business 生体認証データ ストレージ](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/how-it-works#biometric-data-storage)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-plan-prerequisites-phishing-resistant-passwordless-authentication"} -->
## Microsoft Entra ID で耐フィッシング パスワードレス認証の展開を開始する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-prerequisites-phishing-resistant-passwordless-authentication
- Service: entra-id / authentication
- Article date: 2026-03-24
- Summary: Microsoft Entra ID を使用する組織にパスワードレスで耐フィッシング認証を展開するための前提条件を計画するための詳細なガイダンス。

パスワードは、現代の敵対者にとって主要な攻撃ベクトルであり、ユーザーと管理者にとっての摩擦の原因でもあります。 全体的な[ゼロ トラストセキュリティ戦略](https://www.microsoft.com/security/business/zero-trust)の一環として、Microsoft は、認証ソリューションを[耐フィッシング パスワードレスに移行](https://www.microsoft.com/security/business/solutions/passwordless-authentication)することをおすすめしています。 このガイドは、組織に適した耐フィッシング パスワードレス認証情報の選択、準備、展開を支援します。 このガイドを使用して、耐フィッシング パスワードレスのプロジェクトを計画して実行してください。

多要素認証 (MFA) のような機能は、組織をセキュリティで保護するための優れた方法です。 しかし、ユーザーはパスワードを覚える必要がある上に、追加のセキュリティ層があることに不満を感じることがよくあります。 耐フィッシング パスワードレス認証方法の方がより便利です。 たとえば、Microsoft コンシューマー アカウントの分析では、パスワードを使用したサインインには平均で最大 24 秒かかることが示されていますが、パスキーはほとんどの場合約 8 秒しかかからず、同期されたパスキーには 3 秒しかかからずに済みます。 従来のパスワードと MFA によるサインインと比較したとき、パスキーによるサインインの速さと使いやすさは一層優れています。 パスキー ユーザーは、自分のパスワードを覚えたり、SMS メッセージを待つ必要はありません。

注

このデータは、マイクロソフト コンシューマー アカウントのサインインの解析に基づいています。

耐フィッシング パスワードレス メソッドには、追加のセキュリティも組み込まれています。 ユーザーが所持するもの (物理デバイスまたはセキュリティ キー) と、ユーザーが知っているものやユーザーそのもの (生体認証や PIN) を使用して、自動的に MFA としてカウントされます。 また、従来の MFA とは異なり、耐フィッシング パスワードレス メソッドは、簡単に侵害できないハードウェアでバックアップされた認証情報を使用して、ユーザーに対するフィッシング攻撃を回避します。

Microsoft Entra ID には、耐フィッシング パスワードレス認証のオプションとして次が用意されています。

- パスキー (FIDO2)
    - Windows Hello for Business
    - Windows での Microsoft Entra パスキー
    - macOS プラットフォーム認証情報 (プレビュー)
    - Windows 上の Entra Passkey
    - Microsoft Authenticator アプリのパスキー
    - FIDO2 セキュリティキー
    - 同期されたパスキー (Google パスワード マネージャーや iCloud キーチェーンなどのプロバイダー経由で同期)
- 認定資格証ベースの認証/スマート カード

### 前提条件

Microsoft Entra 耐フィッシング パスワードレス展開プロジェクトを開始する前に、次の前提条件を満たす必要があります。

- ライセンス要件のレビュー
- 権限アクションを実行するために必要なロールのレビュー
- 共同作業が必要な利害関係者チームの特定

#### ライセンス要件

Microsoft Entra での登録とパスワードレス サインインにはライセンスは必要ありませんが、パスワードレス展開に関連付けられている機能の完全なセットについては、少なくとも Microsoft Entra ID P1 ライセンスをおすすめします。 たとえば、Microsoft Entra ID P1 ライセンスは、条件付きアクセスによるパスワードレスサインインの実施や、認証方法の活動記録レポートによる展開の追跡を支援します。 このガイドで言及されている機能の具体的なライセンス要件については、ライセンス要件ガイダンスを参照してください。

#### アプリと Microsoft Entra ID を統合する

Microsoft Entra ID は、サービスとしてのソフトウェア (SaaS) アプリ、基幹業務 (LOB) アプリ、オンプレミス アプリなど、さまざまな種類のアプリケーションと統合されるクラウドベースの ID およびアクセス管理 (IAM) サービスです。 パスワードレスの耐フィッシング認証への投資から最大限のメリットを得るには、アプリケーションを Microsoft Entra ID と統合する必要があります。 Microsoft Entra ID を使用して多くのアプリを統合するほど、耐フィッシング認証方法の使用を実施する条件付きアクセス ポリシーを使用して、より多くの環境を保護できます。 アプリを Microsoft Entra ID と統合する方法の詳細については、「[アプリと Microsoft Entra ID を統合する 5 つの手順](https://learn.microsoft.com/ja-jp/entra/fundamentals/five-steps-to-full-application-integration)」を参照してください。

独自のアプリケーションを開発する場合、パスワードレスの耐フィッシング認証をサポートするには、開発者向けガイダンスに従ってください。 詳細については、「[開発するアプリで FIDO2 キーを使用したパスワードレス認証をサポートする](https://learn.microsoft.com/ja-jp/entra/identity-platform/support-fido2-authentication)」を参照してください。

#### 必要なロール

次の表に、耐フィッシング パスワードレス展開の最小特権ロール要件の一覧を示します。 すべての権限アカウントに対して、耐フィッシング パスワードレス認証を有効にすることをおすすめします。

| Microsoft Entra ロール | 説明 |
| --- | --- |
| [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) | 統合された登録エクスペリエンスを実装する |
| [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) | 認証方法を実装および管理する |
| [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) | 認証方法ポリシーを実装および管理する |
| ユーザー | デバイス上で Authenticator アプリを構成するか、ウェブまたは Windows 10/11 でのサインイン用のセキュリティ キー デバイスを登録する |

#### 顧客利害関係者チーム

成功を確実にするためには、適切な利害関係者と連携し、計画とロールアウトを開始する前に彼らがそれぞれの役割を理解していることを確認します。 次の表に、一般的に推奨される利害関係者チームの一覧を示します。

| 利害関係者チーム | 説明 |
| --- | --- |
| ID とアクセスの管理 (IAM) | IAM システムの日常業務の管理 |
| 情報セキュリティアーキテクチャ | 組織の情報セキュリティ プラクティスを計画および設計する |
| 情報セキュリティ運用 | 情報セキュリティ アーキテクチャに関する情報セキュリティ プラクティスを実行および監視する |
| セキュリティ保証とセキュリティ監査 | IT プロセスがセキュリティで保護され、準拠していることを確認するのに役立ちます。 定期的な監査を実施し、リスクを評価し、特定された脆弱性を緩和し、全体的なセキュリティ態勢を強化するためのセキュリティ対策を推奨します。 |
| ヘルプ デスクとサポート | 新しいテクノロジーとポリシーの展開中、または問題が発生したときに、問題に直面するエンド ユーザーを支援します |
| エンド ユーザー コミュニケーション | ユーザー向けのテクノロジロールアウトの促進を支援する準備としてエンド ユーザーへのメッセージを変更する |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-plan-rdp-phishing-resistant-passwordless-authentication"} -->
## Microsoft Entra ID でのフィッシングに強いパスワードレス認証の展開におけるリモート デスクトップ接続に関する考慮事項 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-rdp-phishing-resistant-passwordless-authentication
- Service: entra-id / authentication
- Article date: 2026-05-11
- Summary: Microsoft Entra ID を使用する組織向けにパスワードレスおよびフィッシング対策認証を展開するためのリモート デスクトップ接続ガイダンス。

フィッシングに強いパスワードレスを展開する組織では、通常、一部のペルソナがリモート デスクトップ テクノロジを使用して生産性、セキュリティ、管理を容易にする必要があります。 2 つの基本的なユース ケースは次のとおりです。

- フィッシング詐欺に強いパスワードレス資格情報を使用して、ローカル クライアントからリモート コンピューターへのリモート デスクトップ接続セッションを初期化して認証する
- 確立されたリモート デスクトップ接続セッション内でのフィッシングに対する耐性のあるパスワードレス資格情報の利用

ユース ケースごとに特定の考慮事項を確認します。

## [パスワードレス リモート デスクトップ接続セッションの開始](#tab/rdp-session-auth)
### リモート デスクトップ接続コンポーネント

Windows リモート デスクトップ プロトコルには、3 つの主要なコンポーネントが含まれています。これらのすべてのコンポーネントは、これらの資格情報を使用してリモート デスクトップ接続セッションを開始するために、フィッシングに強いパスワードレス資格情報を適切にサポートする必要があります。 これらのコンポーネントのいずれかが適切に機能しない場合、または特定のパスワードレス資格情報のサポートがない場合、概要が示されている 1 つまたは両方のシナリオは機能しません。 このガイドでは、パスキー/FIDO2 のサポートと Cert-Based 認証 (CBA) のサポートについて説明します。

[Image: Windows Hello for Business を使用してリモート デスクトップ接続セッションを確立するときのユーザー エクスペリエンスを示す GIF。]

[Image: リモート デスクトップ接続経由で接続するときにフィッシングに対するパスワードレス資格情報がどのように使用されるかを示すスイムレーン図]

次のセクションを順を追って、使用している 3 つのコンポーネントすべてに対して、フィッシングに対する耐性のあるパスワードレスのサポートが必要かどうかを判断します。 評価が必要なシナリオが複数ある場合は、このプロセスを繰り返します。

#### クライアント プラットフォーム

リモート デスクトップ セッションのインスタンス化に使用されるローカル クライアントには、いくつかの一般的に使用されるオペレーティング システムがあります。 一般的に使用されるオプションは次のとおりです。

- Windows 10 以降
- Windows Server
- macOS
- iOS
- Android
- Linux

フィッシングに強いパスワードレスおよびリモート デスクトップ接続のサポートは、パスキー プロトコル (特に [クライアントから認証プロトコル (CTAP)](https://fidoalliance.org/specs/fido-v2.0-ps-20190130/fido-client-to-authenticator-protocol-v2.0-ps-20190130.html) と [WebAuthn](https://fidoalliance.org/fido2-2/fido2-web-authentication-webauthn/)) をサポートしているクライアント プラットフォームによって異なります。 CTAP は、モバイル デバイス上の FIDO2 セキュリティ キーやパスキーなどのローミング認証子とクライアント プラットフォームの間の通信層です。 ほとんどのクライアント プラットフォームではこれらのプロトコルがサポートされていますが、サポートされていない特定のプラットフォームがあります。 特殊な OS を実行している専用シン クライアント デバイスなど、場合によっては、ベンダーに連絡してサポートを確認する必要があります。

[Microsoft Entra 証明書ベースの認証 (CBA)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication) では、ユーザーが認証に公開キー 基盤 (PKI) からの証明書を利用できるように、Microsoft Entra ID の構成が必要です。 この記事では、オンプレミスの証明書ベースの認証の実装については説明しません。

| クライアント プラットフォーム | FIDO サポート | Microsoft Entra CBA | 注記 |
| --- | --- | --- | --- |
| Windows 10 以降 | イエス | イエス |  |
| Windows Server | 部分的 | イエス | Windows Server は、クライアント コンピューティング デバイスには推奨されません。Windows Server ジャンプ サーバーは、FIDO ベースのフィッシング対策パスワードレスを妨げる可能性があります。 ジャンプ サーバーを使用する場合は、FIDO ではなく CBA をお勧めします |
| macOS | イエス | イエス | すべての Apple Web フレームワークが FIDO をサポートしているわけではありません |
| iOS | イエス | イエス | すべての Apple Web フレームワークが FIDO をサポートしているわけではありません |
| Android | イエス | イエス |  |
| Linux | 恐らく | イエス | Linux ディストリビューション ベンダーで FIDO サポートを確認する |

#### ターゲット プラットフォーム

ターゲット プラットフォームは、リモート デスクトップ接続セッション自体を確立するために、フィッシングに強いパスワードレス認証がサポートされているかどうかを判断するために重要です。

| ターゲット プラットフォーム | リモート デスクトップ接続セッション初期化 FIDO のサポート | リモート デスクトップ接続セッションの初期化 Microsoft Entra CBA |
| --- | --- | --- |
| Windows 10+ Microsoft Entra 統合済み | イエス | イエス |
| Windows Server は Microsoft Entra に参加済みです。 | はい^1^ | イエス |
| Windows 10+ Microsoft Entra ハイブリッド接続済み | イエス | イエス |
| Windows Server Microsoft Entra ハイブリッド参加済み | はい^1^ | イエス |
| Windows 10+ Microsoft Entra 登録済み | いいえ | いいえ |
| Windows 10 以降のオンプレミス ドメインのみ参加済み | いいえ | いいえ |
| Windows Server オンプレミス ドメインのみ参加済み | いいえ | いいえ |
| Windows 10+ ワークグループ | いいえ | いいえ |
| Azure Arc によって管理されるスタンドアロン/ワークグループ Windows Server^2^ | イエス | イエス |

^1. Windows Server 2022 以降を実行している Microsoft Entra 参加済みサーバーまたはハイブリッド参加済みサーバーにのみ適用されます^^2. Windows Server 2025 以降を実行している Microsoft Entra 参加済みサーバーにのみ適用されます^

#### リモート デスクトップ接続クライアント

リモート デスクトップ接続セッションでフィッシングに対する耐性のある認証をサポートするには、フィッシングに対する認証だけでは、クライアント プラットフォームのサポートは不十分です。 使用するリモート デスクトップ接続クライアントは、これらの資格情報が正常に動作するために必要なコンポーネントもサポートする必要があります。 一般的に使用されるリモート デスクトップ接続クライアントの多くと、サポートされているさまざまなオプションを確認します。

| リモート デスクトップ接続クライアント | リモート デスクトップ接続セッション初期化 FIDO のサポート | リモート デスクトップ接続セッションの初期化 Microsoft Entra CBA |
| --- | --- | --- |
| Windows クライアント用の MSTSC.exe | イエス | イエス |
| Windows Server 2022以降に対応するMSTSC.exe | イエス | イエス |
| MSTSC.exeはWindows Server 2019以前用 | いいえ | いいえ |
| Windows アプリ Windows用 | イエス | イエス |
| macOS 用 Windows アプリ | イエス | イエス |
| iOS 用 Windows アプリ | イエス | イエス |
| Android 用 Windows アプリ | イエス | イエス |
| Windows 365 Web アプリ | いいえ | いいえ |
| サード パーティのリモート デスクトップ接続クライアント | 恐らく | 恐らく |

重要

クライアントデバイスとターゲット デバイスは、Microsoft Entra 参加済み、Microsoft Entra ハイブリッド参加済み、または Microsoft Entra が同じテナントに登録されている必要があります。 テナント間認証は機能しません。異なるテナントに参加している場合、クライアント デバイスはターゲット デバイスに対して認証を行うことができません。

## [セッション内パスワードレス認証](#tab/rdp-auth-in-session)
### リモート デスクトップ接続コンポーネント

Windows リモート デスクトップ プロトコルには、3 つの主要なコンポーネントが含まれています。これらのすべてのコンポーネントは、フィッシングに強いパスワードレス資格情報を適切にサポートし、これらの資格情報のセッション内使用をサポートするためにローカル クライアントへのリダイレクトをサポートする必要があります。 これらのコンポーネントのいずれかが適切に機能しない場合、または特定のパスワードレス資格情報のサポートがない場合、概要が示されている 1 つまたは両方のシナリオは機能しません。 このガイドでは、パスキー/FIDO2 のサポートと Cert-Based 認証 (CBA) のサポートについて説明します。

[Image: Windows Hello for Business を使用してリモート デスクトップ接続セッション内で認証を行うときのユーザー エクスペリエンスを示す GIF。]

[Image: リモート デスクトップ接続セッション内でフィッシングに耐性のあるパスワードレス資格情報がどのように使用されるかを示すスイムレーン図]

次のセクションを順を追って、使用している 3 つのコンポーネントすべてに対して、フィッシングに対する耐性のあるパスワードレス サポートが必要かどうかを判断します。 評価が必要なシナリオが複数ある場合は、このプロセスを繰り返します。

#### クライアント プラットフォーム

リモート デスクトップ セッションのインスタンス化に使用されるローカル クライアントには、いくつかの一般的に使用されるオペレーティング システムがあります。 一般的に使用されるオプションは次のとおりです。

- Windows 10 以降
- Windows Server
- macOS
- iOS
- Android
- Linux

フィッシングに強いパスワードレスおよびリモート デスクトップ接続のサポートは、パスキー プロトコル (特に [クライアントから認証プロトコル (CTAP)](https://fidoalliance.org/specs/fido-v2.0-ps-20190130/fido-client-to-authenticator-protocol-v2.0-ps-20190130.html) と [WebAuthn](https://fidoalliance.org/fido2-2/fido2-web-authentication-webauthn/)) をサポートしているクライアント プラットフォームによって異なります。 CTAP は、モバイル デバイス上の FIDO2 セキュリティ キーやパスキーなどのローミング認証子とクライアント プラットフォームの間の通信層です。 ほとんどのクライアント プラットフォームではこれらのプロトコルがサポートされていますが、サポートされていない特定のプラットフォームがあります。 特殊な OS を実行している専用シン クライアント デバイスなど、場合によっては、ベンダーに連絡してサポートを確認する必要があります。

[Microsoft Entra 証明書ベースの認証 (CBA)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication) では、ユーザーが認証に公開キー 基盤 (PKI) からの証明書を利用できるように、Microsoft Entra ID の構成が必要です。 この記事では、オンプレミスの証明書ベースの認証の実装については説明しません。

| クライアント プラットフォーム | FIDO サポート | Microsoft Entra CBA | 注記 |
| --- | --- | --- | --- |
| Windows 10 以降 | イエス | イエス |  |
| Windows Server | 部分的 | イエス | Windows Server は、クライアント コンピューティング デバイスには推奨されません。Windows Server ジャンプ サーバーは、FIDO ベースのフィッシング対策パスワードレスを妨げる可能性があります。 ジャンプ サーバーを使用する場合は、FIDO ではなく CBA をお勧めします |
| macOS | イエス | イエス | すべての Apple Web フレームワークが FIDO をサポートしているわけではありません |
| iOS | イエス | イエス | すべての Apple Web フレームワークが FIDO をサポートしているわけではありません |
| Android | イエス | イエス |  |
| Linux | 恐らく | イエス | Linux ディストリビューション ベンダーで FIDO サポートを確認する |

#### ターゲット プラットフォーム

ターゲット プラットフォームは、セッション内での使用に対してフィッシングに強いパスワードレス認証がサポートされているかどうかを判断するために重要です。

| ターゲット プラットフォーム | In-Session FIDO サポート | セッション中の Microsoft Entra CBA |
| --- | --- | --- |
| Windows 10+ Microsoft Entra 統合済み | イエス | イエス |
| Windows Server は Microsoft Entra に参加済みです。 | はい^1^ | イエス |
| Windows 10+ Microsoft Entra ハイブリッド接続済み | イエス | イエス |
| Windows Server Microsoft Entra ハイブリッド参加済み | はい^1^ | イエス |
| Windows 10+ Microsoft Entra 登録済み | イエス | イエス |
| Windows 10 以降のオンプレミス ドメインに加入した | イエス | イエス |
| Windows Server オンプレミス ドメインのみ参加済み | いいえ | いいえ |
| Windows 10+ ワークグループ | イエス | イエス |
| Azure Arc により管理されている Windows Server^2^ | イエス | イエス |

^1. Windows Server 2022 以降を実行している Microsoft Entra 参加済みサーバーまたはハイブリッド参加済みサーバーにのみ適用されます^^2. Windows Server 2025 以降を実行している Microsoft Entra 参加済みサーバーにのみ適用されます^

#### リモート デスクトップ接続クライアント

リモート デスクトップ接続セッションおよびリモート デスクトップ接続セッション内でフィッシングに対する耐性のある認証をサポートするには、フィッシングに対する認証だけでは、クライアント プラットフォームのサポートは不十分です。 使用するリモート デスクトップ接続クライアントは、これらの資格情報が正常に動作するために必要なコンポーネントもサポートする必要があります。 一般的に使用されるリモート デスクトップ接続クライアントの多くと、サポートされているさまざまなオプションを確認します。

| リモート デスクトップ接続クライアント | In-Session FIDO サポート | セッション中の Microsoft Entra CBA |
| --- | --- | --- |
| Windows クライアント用の MSTSC.exe | イエス | イエス |
| Windows Server 2022以降に対応するMSTSC.exe | イエス | イエス |
| MSTSC.exeはWindows Server 2019以前用 | いいえ | いいえ |
| Windows アプリ Windows用 | イエス | イエス |
| macOS 用 Windows アプリ | いいえ | イエス |
| iOS 用 Windows アプリ | いいえ | イエス |
| Android 用 Windows アプリ | いいえ | イエス |
| Windows 365 Web アプリ | いいえ | いいえ |
| サード パーティのリモート デスクトップ接続クライアント | 恐らく | 恐らく |

---

### シナリオのサポートを評価する

このドキュメントで説明されている 3 つのコンポーネントのいずれかがシナリオをサポートしていない場合、シナリオは機能しません。 評価するには、リモート デスクトップ接続セッション認証とセッション内資格情報の使用に関する各コンポーネントを検討します。 環境内のすべてのシナリオに対してこのプロセスを繰り返して、動作が期待されるシナリオと動作しないシナリオを理解します。

#### 例 1

たとえば、シナリオが "Information Worker が Windows デバイスを使用して Azure Virtual Desktop にアクセスする必要があり、Microsoft Authenticator パスキーを使用してリモート デスクトップ接続セッションを認証し、Microsoft Edge ブラウザーのリモート デスクトップ接続セッション内でパスキーを使用する必要がある" かどうかを評価する方法を次に示します。

| シナリオ | クライアント プラットフォーム | ターゲット プラットフォーム | リモート デスクトップ接続クライアント | サポートされていますか？ |
| --- | --- | --- | --- | --- |
| Auth App Passkey を使用したリモート デスクトップ接続*セッションの初期化* | Windows 11 Microsoft Entra 参加/ハイブリッド参加/スタンドアロン | Azure Virtual Desktop が Microsoft Entra に参加しました | Windows アプリ | はい + はい + はい = **はい** |
| Auth App Passkey を使用したリモート デスクトップ接続 In-Session 認証 | Windows 11 Microsoft Entra 参加/ハイブリッド参加/スタンドアロン | Azure Virtual Desktop が Microsoft Entra に参加しました | Windows アプリ | はい + はい + はい = **はい** |

この例では、リモート デスクトップ接続セッション自体とセッション内アプリの両方で、ユーザーのパスキーを利用できます。 フィッシングに強いパスワードレスは、広く機能する必要があります。

#### 例 2

シナリオが "Information Worker が自分の macOS デバイスを使用して Azure Virtual Desktop にアクセスする必要があり、Microsoft Authenticator パスキーを使用してリモート デスクトップ接続セッションを認証し、リモート デスクトップ接続セッション内でパスキーを使用する必要がある" かどうかを評価する方法を次に示します。

| シナリオ | クライアント プラットフォーム | ターゲット プラットフォーム | リモート デスクトップ接続クライアント | サポートされていますか？ |
| --- | --- | --- | --- | --- |
| Auth App Passkey を使用したリモート デスクトップ接続*セッションの初期化* | macOS 15 | Azure Virtual Desktop が Microsoft Entra に参加しました | Windows アプリ | はい + はい + はい = **はい** |
| Auth App Passkey を使用したリモート デスクトップ接続 In-Session 認証 | macOS 15 | Azure Virtual Desktop が Microsoft Entra に参加しました | Windows アプリ | はい + はい + いいえ = **いいえ** |

この例では、ユーザーはパスキーを使用してリモート デスクトップ接続セッションを確立できますが、macOS 上の Windows アプリではこの機能がまだサポートされていないため、リモート デスクトップ接続セッション内では使用できません。 リモート デスクトップ接続クライアントでパスキーのサポートが向上するのを待つか、CBA を使用した証明書などの別の資格情報に切り替えることができます。

#### 例 3

シナリオが "管理者が Windows デバイスを使用してオンプレミスの Windows Server にアクセスする必要があり、証明書を使用してリモート デスクトップ接続セッションを認証し、リモート デスクトップ接続セッション内で証明書を使用する必要がある" かどうかを評価する方法を次に示します。

| シナリオ | クライアント プラットフォーム | ターゲット プラットフォーム | リモート デスクトップ接続クライアント | サポートされていますか？ |
| --- | --- | --- | --- | --- |
| 証明書を使用したリモート デスクトップ接続*セッションの初期化* | ウィンドウズ11 | Domain-Joined の Windows Server | MSTSC.exe | はい + はい + はい = **はい** |
| 証明書を使用したリモート デスクトップ接続 In-Session 認証 | ウィンドウズ11 | Domain-Joined の Windows Server | MSTSC.exe | はい + はい + はい = **はい** |

この例では、ユーザーは証明書を使用してリモート デスクトップ接続セッションを確立し、リモート デスクトップ接続セッション内で証明書を使用することもできます。 ただし、ドメインに参加している Windows サーバーではパスキーを使用してリモート デスクトップ接続セッションまたはセッション内を設定できないため、このシナリオはパスキーでは機能しません。

#### 例 4

シナリオが "Microsoft Entra ハイブリッドに参加していないオンプレミスのドメイン参加済み Windows Virtual Desktop Infrastructure (VDI) クライアントにアクセスするために、Linux ベースのシン クライアントを使用する必要があり、FIDO2 セキュリティ キーを使用してリモート デスクトップ接続セッションを認証し、リモート デスクトップ接続セッション内で FIDO2 セキュリティ キーを使用する必要がある"かどうかを評価する方法を次に示します。

| シナリオ | クライアント プラットフォーム | ターゲット プラットフォーム | リモート デスクトップ接続クライアント | サポートされていますか？ |
| --- | --- | --- | --- | --- |
| FIDO2 セキュリティ キーを使用したリモート デスクトップ接続*セッションの初期化* | Linux Embedded Distro | ドメイン参加済みの Windows 11 | ベンダー提供のクライアント | Maybe+No+No = **No** |
| FIDO2 セキュリティ キーを使用したリモート デスクトップ接続 In-Session 認証 | Linux Embedded Distro | ドメイン参加済みの Windows 11 | ベンダー提供のクライアント | Maybe+Yes+Maybe = **Maybe** |

この例では、ユーザーがリモート デスクトップ接続に FIDO2 セキュリティ キーをまったく使用できない可能性があります。これは、シン クライアント OS とリモート デスクトップ接続クライアントが必要なすべてのシナリオで FIDO2/passkeys をサポートしていないためです。 シン クライアント ベンダーと協力して、サポートのロードマップを理解してください。 さらに、パスキーをより適切にサポートできるように、Microsoft Entra ハイブリッド参加または Microsoft Entra をターゲット プラットフォームの仮想マシンに参加させることを計画します。

### Windows 11 のパスキーのプライバシーに関する同意をトラブルシューティングする

Windows 11 バージョン 24H2 以降には、ユーザーがパスキーをアプリまたは Web サイトで使用できるかどうかを決定できるプライバシー制御が含まれています。 ユーザーがプライバシーの同意プロンプトを拒否した場合、AVD セッションと RDP セッションではパスキーの登録と認証が機能しない可能性があります。

ユーザーは、AVD および RDP セッションで Microsoft Entra のパスキーを使用またはプロビジョニングできない場合があります。その理由は次のとおりです。

- 誤ってプライバシーの同意プロンプトを拒否し、パスキー アクセスを許可するように求められたら **[いいえ** ] を選択しました。
- 設定アプリをカスタマイズし、パスキーのプライバシー設定へのナビゲーション オプションを非表示にしました。

#### プライバシーに関する同意の問題を解決する

この問題を解決するには、ユーザーにパスキー アクセスを再度有効にするよう指示します。

1. **設定**&gt;**プライバシーとセキュリティ**&gt;**パスキー アクセス**を開きます。
2. パスキー アクセスが必要なアプリまたは Web サイトを見つけて、[許可] を選択 **します**。

設定ページが非表示になっているためにユーザーがパスキーアクセス設定に移動できない場合、管理者は [設定ページの可視性](https://learn.microsoft.com/ja-jp/windows/configuration/settings/page-visibility) を構成して、 **プライバシーとセキュリティ**&gt;**Passkeyアクセス** ページへのアクセスを復元できます。

詳細については、「[Windows でのパスキーのサポート](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/passkeys/)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-register-entra-passkey-windows"} -->
## WindowsにMicrosoft Entraパスキーを登録する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-register-entra-passkey-windows
- Service: entra-id / authentication
- Article date: 2026-07-05
- Summary: フィッシング詐欺に強いサインイン用の FIDO2 パスキー プロバイダーとしてWindows Helloを使用して、WindowsにMicrosoft Entra パスキーを登録する方法について説明します。

この記事では、Windowsに Microsoft Entra パスキーを登録する方法について説明します。 WindowsのMicrosoft Entra パスキーは、ローカル Windows Hello コンテナーに格納されているデバイス バインドパスキーです。 同期されたパスキーとは異なり、Windowsのパスキーはデバイス間で同期されません。各デバイスには個別のパスキー登録が必要です。 このアプローチでは、デバイスの参加や登録をMicrosoft Entraしなくても、Windows Hello生体認証または PIN を使用したフィッシングに強いサインインが可能になります。

Windowsでのパスキー Microsoft Entraの概要とWindows Hello for Businessとの比較については、Windows[でのパスキー Microsoft Entra](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-entra-passkeys-on-windows)参照してください。

### 前提条件

登録する前に、次の要件を確認してください。

- 管理者がパスキー (FIDO2) を有効にし、WINDOWS HELLO AAGUID を許可するパスキー プロファイルを作成しました。 構成手順については、「[Windows で Microsoft Entra パスキー用のプロファイルを構成する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-entra-passkeys-on-windows#configure-a-profile-for-microsoft-entra-passkey-on-windows)」を参照してください。
- デバイスは、サポートされているバージョンのWindowsを実行します。
- パスキー プロファイルで構成証明を強制する必要はありません。

サポートされているWindows Helloパスキー AAGUID の一覧については、「[サポートされているWindows Helloパスキー AAGUIDs](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-entra-passkeys-on-windows#supported-windows-hello-passkey-aaguids)」を参照してください。

### Windowsにパスキーを登録する

Windows デバイスにパスキーを登録するには、次の手順に従います。

1. Web ブラウザーを開き、[ [セキュリティ情報](https://mysignins.microsoft.com/security-info)] にサインインします。
2. 多要素認証 (MFA) を使用してサインインします。
3. [ **Add sign-in method**&gt;**Choose a method**&gt;**Passkey**] をタップします。
4. [**次へ**] をタップします。
5. パスキー (FIDO2) を保存する場所を選択します。

    注

    表示されるオプションは、ブラウザーとデバイスのオペレーティング システムによって異なります。 登録プロセスを開始したデバイスがパスキー (FIDO2) をサポートしている場合は、パスキーをそのデバイスに保存するように求められます。 **[別のデバイスを使用する]** または **[その他のオプション]** を選択して、パスキーを保存するための追加の方法を表示します。

    [Image: マイ セキュリティ情報にパスキー (FIDO2) を保存するダイアログのスクリーンショット。]

検証後、Windowsはパスキーを作成し、ローカル Windows Hello コンテナーに格納します。 このパスキーを使用して、Microsoft Entra IDにサインインできるようになりました。

注

同じアカウントのWindows Hello for Business資格情報が既に存在する場合、パスキーの登録が失敗する可能性があります。 詳細については、[Windows 上の Microsoft Entra パスキー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-entra-passkeys-on-windows#faq)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-register-passkey"} -->
## 同期されたパスキー (FIDO2) を登録する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-register-passkey
- Service: entra-id / authentication
- Article date: 2026-07-05
- Summary: フィッシング詐欺に強いサインイン用のブラウザーを使用して、同期されたパスキー (FIDO2) を認証方法として Windows、iOS、または Android に登録する方法について説明します。

この記事では、ユーザーが Passkey **フローを** 使用して同期されたパスキー (FIDO2) を登録する方法について説明します。 同期されたパスキーは、パスキー プロバイダー (iCloud キーチェーンや Google パスワード マネージャーなど) に格納され、ユーザーのデバイス間で同期されます。 同期されたパスキーの概要については、「[Microsoft Entra IDの同期されたパスキー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-synced-passkeys)」を参照してください。

メモ

ユーザーに代わってパスキー (FIDO2) を提供しようとしていますか？ 弊社の [API](https://aka.ms/passkeyprovision) を使用してください。

### 前提条件

同期されたパスキーを保存するには、モバイル デバイスでパスワード マネージャーを構成する必要があります。

- iOSデバイスで、同期済みのパスキーを管理するには、**Set Up Codes In**を**Passwords**に設定する必要があります。 **設定**&gt;**一般**&gt;**自動入力とパスワード**を開きます。 **コードの設定方法**で、**パスワード**を選択します。
- Android デバイスの**設定**&gt;**セキュリティとプライバシー**&gt;**その他のセキュリティ設定**&gt;**パスワード、パスキー、オートフィル**を開いて、プロバイダを選択します。

### パスキーを登録する

デバイスにパスキーを登録するには、次の手順に従います。

1. Web ブラウザーを開き、[ [セキュリティ情報](https://mysignins.microsoft.com/security-info)] にサインインします。
2. 多要素認証 (MFA) を使用してサインインします。
3. **[+ サインイン方法の追加]** をタップします。

    [Image: [サインイン方法の追加] オプションが表示されている iOS の [セキュリティ情報] ページのスクリーンショット。]
4. [ **パスキー**] をタップします。

    [Image: [Passkey](https://learn.microsoft.com/ja-jp/entra/identity/authentication/パスキー) オプションが表示されている iOS の [Add a sign-in method](サインイン 方法の追加) ページのスクリーンショット。]
5. [**次へ**] をタップします。

    [Image: [次へ] オプションが表示されている iOS の [顔、指紋、または PIN でサインインする] ページのスクリーンショット。]
6. iOS では、[ **次へ**] をタップします。

    [Image: [次へ] オプションが表示されている iOS の [パスキーの設定] ページのスクリーンショット。]

    Android では、[ **続行**] をタップします。

    メモ

    Android でパスキー プロバイダーを有効にする手順は、デバイスの作成とモデルによって異なる場合があります。 デバイス設定で Passkey を検索するか、デバイスの製造元に問い合わせてください。 デバイスで Android 14 を実行していて、パスキー プロバイダーとして Authenticator を有効にできない場合は、Android 15 にアップグレードすることをお勧めします。

    [Image: [アカウント名と続行] オプションが表示されている Android の [パスキーの作成] ページのスクリーンショット。]
7. パスキーに名前を付け、[ **次へ**] をタップします。

    [Image: パスキー名フィールドと [次へ] オプションを示す [パスキーに名前を付ける] ページのスクリーンショット。]
8. パスキーが作成されたら、[ **完了]** をタップします。

    [Image: [完了] オプションを示す [Passkey created](https://learn.microsoft.com/ja-jp/entra/identity/authentication/パスキー作成) ページのスクリーンショット。]
9. パスキーは [セキュリティ情報](https://mysignins.microsoft.com/security-info)で確認できます。

    [Image: 登録済みのパスキーを示す [セキュリティ情報] ページのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-register-passkey-authenticator"} -->
## Android と iOS デバイスの Authenticator にパスキーを登録する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-register-passkey-authenticator
- Service: entra-id / authentication
- Article date: 2026-07-05
- Summary: Android および iOS の Microsoft Authenticator でパスキーを登録する方法について説明します。 アプリにサインインするか、セキュリティ情報を使用するか、デバイス間で登録します。

この記事では、iOS または Android デバイスのMicrosoft Authenticatorにパスキーを登録する方法について説明します。

## [iOS](#tab/iOS)
Authenticator アプリに直接サインインするか、 [セキュリティ情報](https://aka.ms/mysecurityinfo)を使用するか、モバイル デバイス ブラウザーからサインインするか、ラップトップなどの別のデバイスを使用してクロスデバイス登録を使用して登録できます。 モバイル デバイスで iOS バージョン 17 以降を実行する必要があります。

- Authenticator (iOS) の使用
- セキュリティ情報の使用 (iOS)
- WebAuthn フローの使用 (iOS)

#### Authenticator (iOS) の使用

Authenticator にサインインしてアプリにパスキーを作成し、Microsoft ネイティブ アプリ全体でシームレスなシングル サインオンを取得できます。 **このフローは、Authenticator でパスキーを設定するための推奨される方法です。** Authenticator にサインインしている、または、既にアカウントをお持ちの場合でも、以下の手順を完了してアプリにパスキーを追加する必要があります。

1. App Store から Authenticator をダウンロードし、プライバシー画面に従って操作します。
2. iOS デバイスの Authenticator にアカウントを追加します。

デバイスに Authenticator を初めてインストールした場合は、**[デジタル ライフを保護する]** 画面で **[職場または学校アカウントの追加]** をタップします。

[Image: iOS デバイスの Authenticator に表示される最初の画面を示すスクリーンショット。]

デバイスに Authenticator をインストールしたが、アカウントを追加していない場合は、[アカウント追加] または [] ボタンをタップし、職場または学校アカウント を選択します。 次に、**[サインイン]** をタップします。

[Image: iOS デバイスの Authenticator を使用して登録する方法を示すスクリーンショット。]

Authenticator にアカウントを既に追加している場合は、そのアカウントをタップし、**[パスキーの作成]** をタップします。

[Image: iOS デバイスの Authenticator でパスキーを作成する方法を示すスクリーンショット。]
3. 多要素認証 (MFA) を完了します。
4. 必要に応じて、**[設定]** をタップし、画面ロックを設定します。

    [Image: Android デバイス用 Authenticator でパスキーの画面ロックを設定する方法を示すスクリーンショット。]
5. **[設定]** をタップして、Authenticator をパスキー プロバイダーとして有効にします。

    [Image: iOS デバイスの Authenticator を使用して画面の指示に従う [設定] を開く画面を示すスクリーンショット。]
6. iOS 18 デバイスで、[**設定]**&gt;**[全般]**&gt;**[パスワードの自動入力] &**に移動します。 iOS 17デバイスで、**設定**&gt;**パスワード**&gt;**パスワードオプション**に移動します。

    両方のオペレーティング システムで、オートフィル パスワードとパスキーがオンになっていることを確認します。 **[オートフィル元]** で **Authenticator** が選択されていることを確認します。

    [Image: iOS デバイスの Authenticator のターンオン パスキー サポート オプションを示すスクリーンショット。]
7. Authenticator に戻ったら、[完了]タップして、Authenticator をパスキー プロバイダーとして追加したことを確認します。 この後、アカウントのサインイン方法として追加された**パスキー**を確認できます。 もう一度 **[完了]** をタップして完了します。

    [Image: Android デバイスの Authenticator に追加されたアカウントを示すスクリーンショット。]

    Authenticator により、職場または学校のアカウント ポリシーに従って、サインイン用のパスキー、パスワードレス、MFA を設定します。 アカウントをタップすると、新しいパスキーなどの情報が表示されます。

#### セキュリティ情報の使用 (iOS)

1. Authenticator と同じ iOS デバイス、またはラップトップなどの別のデバイスを使用して、Web ブラウザーを開き、MFA を使用して[\[セキュリティ情報\]](https://mysignins.microsoft.com/security-info) にサインインします。
2. [**セキュリティ情報]**で、[**+ サインイン方法の追加]** をタップし、Microsoft Authenticatorで [**パスキー]**を選択します。

    [Image: サインイン方法として Authenticator で passkey を選択する方法を示すスクリーンショット。]
3. 多要素認証を使用してサインインするように求められた場合は、[次へ] を選択します。
4. 必要に応じて、Authenticator を iOS デバイスにダウンロードします。 Microsoft Authenticator選択し、QR コードをスキャンして iOS App Store から Authenticator をインストールできます。 Authenticator をダウンロードしたら、**[次へ]** をタップします。

    [Image: Authenticator をダウンロードするオプションをユーザーに提供しているスクリーンショット。]
5. Authenticator アプリを開き、そこでパスキーを作成するように求められます。 Authenticator を開き、必要に応じてプライバシー画面に従って操作します。

    [Image: Authenticator でパスキーのセットアップを完了するために使用されるウィザードを示すスクリーンショット。]
6. iOS デバイスの Authenticator にアカウントを追加します。

デバイスに Authenticator を初めてインストールした場合は、**[デジタル ライフを保護する]** 画面で **[職場または学校アカウントの追加]** をタップします。

[Image: iOS デバイスの Authenticator に表示される最初の画面を示すスクリーンショット。]

以前にデバイスに Authenticator をインストールしたが、アカウントを追加しなかった場合は、[アカウント の追加] または [] ボタン タップし、職場または学校アカウント選択します。 次に、**[サインイン]** をタップします。

[Image: iOS デバイスの Authenticator を使用して登録する方法を示すスクリーンショット。]

Authenticator にアカウントを既に追加している場合は、そのアカウントをタップし、**[パスキーの作成]** をタップします。

[Image: iOS デバイスの Authenticator でパスキーを作成する方法を示すスクリーンショット。]
7. 多要素認証 (MFA) を完了します。
8. 必要に応じて、**[設定]** をタップし、画面ロックを設定します。

    [Image: Android デバイス用 Authenticator でパスキーの画面ロックを設定する方法を示すスクリーンショット。]
9. **[設定]** をタップして、Authenticator をパスキー プロバイダーとして有効にします。
10. iOS 18 デバイスで、[**設定]**&gt;**[全般]**&gt;**[パスワードの自動入力] &**に移動します。 iOS 17デバイスで、**設定**&gt;**パスワード**&gt;**パスワードオプション**に移動します。

    両方のオペレーティング システムで、オートフィル パスワードとパスキーがオンになっていることを確認します。 **[オートフィル元]** で **Authenticator** が選択されていることを確認します。

    [Image: iOS デバイスの Authenticator のターンオン パスキー サポート オプションを示すスクリーンショット。]
11. Authenticator に戻ったら、[完了]タップして、Authenticator をパスキー プロバイダーとして追加したことを確認します。 この後、アカウントのサインイン方法として追加された**パスキー**を確認できます。 もう一度 **[完了]** をタップして完了します。

    [Image: Android デバイスの Authenticator に追加されたアカウントを示すスクリーンショット。]

    Authenticator により、職場または学校のアカウント ポリシーに従って、サインイン用のパスキー、パスワードレス、MFA を設定します。
12. Authenticator でパスキーのセットアップを完了した後、ブラウザーに戻り、[次へ]選択します。

    [Image: Authenticator でパスキーのセットアップを完了するためにウィザードに戻る方法を示すスクリーンショット。]
13. ウィザードは、パスキーが Authenticator で作成されたことを確認します。

    [Image: Authenticator でパスキーを検証するウィザードを示すスクリーンショット。]
14. パスキーが作成されたら、**[完了]** を選択します。

    [Image: パスキーが作成されたことを確認するスクリーンショット。]
15. **セキュリティ情報**で、追加された新しいパスキーを確認できます。

    [Image: 他のデバイスのセキュリティ情報に関する新しいパスキー サインイン方法を示すスクリーンショット。]

#### WebAuthn フローの使用 (iOS)

Authenticator にサインインしてパスキーを登録できない場合は、WebAuthnセキュリティ情報から直接登録できます。

注記

管理者が構成証明を有効にしている場合、この方法で Authenticator にパスキーを登録することはできません。

別のデバイスで **セキュリティ情報** にサインインする場合は、両方のデバイスがインターネットにアクセスでき、Bluetooth有効になっていることを確認します。

組織でBluetoothの使用が制限されている場合は、 [passkey 対応 FIDO2 認証子と排他的にペアリングするBluetoothを許可](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/passkeys/?tabs=windows%2Cintune#passkeys-in-bluetooth-restricted-environments) して、クロスデバイス パスキーのサインインと登録を許可できます。

組織では、デバイス間の登録と認証を有効にするために、次の表のエンドポイントへの接続を許可する必要があります。 デバイスは、インターセプトなしでこれらの URL にアクセスできるようにする必要があります。 ネットワーク プロキシ、インターセプト、その他のエンタープライズ システムから除外する必要があります。

| Platform | URL |
| --- | --- |
| Android | `cable.ua5v.com` |
| iOS | `cable.auth.com``app-site-association.cdn-apple.com``app-site-association.networking.apple` |

1. **[セキュリティ情報]** で Authenticator にパスキーを追加する際、**[問題が発生した場合]** をタップします。

    [Image: 問題が発生した場合に別の方法で登録する方法を示すスクリーンショット。]
2. 次に、**[別の方法でパスキーを作成]** をタップします。

    [Image: パスキーを別の方法で登録する方法を示すスクリーンショット。]
3. iPhone または iPad選択し、残りのフローを実行してデバイスにパスキーを登録します。

    [Image: 問題が発生した場合に iOS で別の方法を選択する方法を示すスクリーンショット。]

ユーザーが元の手順に戻し、サインインを使用して Authenticator にパスキーを登録する場合:

1. **[セキュリティ情報]** で Authenticator にパスキーを追加する際、**[問題が発生した場合]** をタップします。
2. 次に、Authenticator にサインインして **[別の方法でパスキーを作成]** をタップします。
3. フローの残りの部分を実行して、デバイスにパスキーを登録します。

注記

macOS の Chrome ブラウザーにパスキーを登録する場合は、メッセージが表示されたら、`login.microsoft.com` がセキュリティ キーまたはデバイスにアクセスできるようにします。

#### Authenticator for iOS でパスキーを削除する

Authenticator からパスキーを削除するには、アカウント名をタップし、[**設定]**&gt;**[パスキーの削除]**をタップします。 また、[セキュリティ情報](https://mysignins.microsoft.com/security-info)からパスキーを削除する必要があります。

#### iOS でのパスキー登録のトラブルシューティング

パスキーを登録しようとすると、認証アプリにローカルに格納されますが、認証サーバーには登録されない場合があります。 たとえば、パスキー プロバイダーが許可されていないか、接続がタイムアウトする可能性があります。パスキーを登録しようとして、パスキーが既に存在するというエラーが表示された場合は、Authenticator でローカルに作成されたパスキー を削除 、登録を再試行します。

## [Android](#tab/Android)
Authenticator アプリに直接サインインするか、 [セキュリティ情報](https://aka.ms/mysecurityinfo)を使用するか、モバイル デバイス ブラウザーからサインインするか、ラップトップなどの別のデバイスを使用してクロスデバイス登録を使用して登録できます。 モバイル デバイスで Android バージョン 14 以降を実行する必要があります。

- Authenticator の使用 (Android)
- セキュリティ情報の使用 (Android)
- WebAuthn フローを使用する (Android)

#### Authenticator の使用 (Android)

Authenticator にサインインしてアプリにパスキーを作成し、Microsoft ネイティブ アプリ全体でシームレスなシングル サインオンを取得できます。 **このフローは、Authenticator でパスキーを設定するための推奨される方法です。** Authenticator にサインインしている、または、既にアカウントをお持ちの場合でも、以下の手順を完了してアプリにパスキーを追加する必要があります。

1. Google Play から Authenticator をダウンロードし、それを開き、プライバシー画面に従って操作します。
2. Android デバイスの Authenticator にアカウントを追加します。

デバイスに Authenticator を初めてインストールした場合は、**[デジタル ライフを保護する]** 画面で **[職場または学校アカウントの追加]** をタップします。

[Image: Android デバイスの Authenticator に表示される最初の画面を示すスクリーンショット。]

以前にデバイスに Authenticator をインストールしたが、アカウントを追加しなかった場合は、[アカウント の追加] または [] ボタン タップし、職場または学校アカウント選択します。 次に、**[サインイン]** をタップします。

[Image: Android デバイスの Authenticator を使用して登録する方法を示すスクリーンショット。]

Authenticator にアカウントを既に追加している場合は、そのアカウントをタップし、**[パスキーの作成]** をタップします。

[Image: Android デバイスの Authenticator でパスキーを作成する方法を示すスクリーンショット。]
3. 多要素認証 (MFA) を完了します。
4. 必要に応じて、**[設定]** をタップし、画面ロックを設定します。

    [Image: Android デバイス用 Authenticator でパスキーの画面ロックを設定する方法を示すスクリーンショット。]
5. **[設定]** をタップして、Authenticator をパスキー プロバイダーとして有効にします。

    注記

    Android でパスキー プロバイダーを有効にする手順は、デバイスの作成とモデルによって異なる場合があります。 デバイス設定で **Passkey** を検索するか、デバイスの製造元に問い合わせてください。 デバイスで Android 14 を実行していて、パスキー プロバイダーとして Authenticator を有効にできない場合は、Android 15 にアップグレードすることをお勧めします。

    [Image: Android デバイスの Authenticator を使用して[設定] を開き、画面の指示に従っていることを示すスクリーンショット。]

    1. **[パスワードとアカウント]** を開きます。

        [Image: Android デバイスの Authenticator を使用してパスワードとパスワード オプションを選択するスクリーンショット。]
    2. [**その他のプロバイダー**] セクションで、[**Authenticator**] が選択されていることを確認します。

        [Image: Android デバイスで Authenticator を使用してプロバイダーとして Authenticator を有効にすることを示すスクリーンショット。]
6. Authenticator に戻ったら、[完了]タップして、Authenticator をパスキー プロバイダーとして追加したことを確認します。 この後、アカウントのサインイン方法として追加された**パスキー**を確認できます。 もう一度 **[完了]** をタップして完了します。

    [Image: Android デバイスの Authenticator に追加されたアカウントを示すスクリーンショット。]

    Authenticator により、職場または学校のアカウント ポリシーに従って、サインイン用のパスキー、パスワードレス、MFA を設定します。 アカウントをタップすると、新しいパスキーなどの情報が表示されます。

#### セキュリティ情報の使用 (Android)

1. Authenticator と同じ Android デバイスで、またはラップトップなどの別のデバイスを使用して、Web ブラウザーを開き、MFA を使用して [Security info](https://mysignins.microsoft.com/security-info)にサインインします。
2. [**セキュリティ情報]**で、[**+ サインイン方法の追加]** をタップし、Microsoft Authenticatorで [**パスキー]**を選択します。

    [Image: サインイン方法として Authenticator でパスキーを選択する方法を示すスクリーンショット。]
3. メッセージが表示された場合は、次  をタップし、MFA でサインインします。
4. 必要に応じて、Authenticator を Android デバイスにダウンロードします。 Microsoft Authenticator選択し、QR コードをスキャンして Google Play から Authenticator をインストールできます。 Android デバイスに Authenticator をダウンロードしたら、[次 ] を選択します。

    [Image: Authenticator をダウンロードするオプションをユーザーに提供しているスクリーンショット。]
5. Authenticator アプリを開き、そこでパスキーを作成するように求められます。

    [Image: Authenticator でパスキーのセットアップを完了するウィザードを示すスクリーンショット。]
6. Authenticator を開き、必要に応じてプライバシー画面を表示します。

    - デバイスに Authenticator を初めてインストールした場合は、**[デジタル ライフを保護する]** 画面で **[職場または学校アカウントの追加]** をタップします。

        [Image: Android デバイスの Authenticator に表示される最初の画面を示すスクリーンショット。]
    - 以前にデバイスに Authenticator をインストールしたが、アカウントを追加しなかった場合は、[アカウント の追加] または [] ボタン タップし、職場または学校アカウント選択します。 次に、**[サインイン]** をタップします。

        [Image: Android デバイスの Authenticator を使用して登録する方法を示すスクリーンショット。]
    - Authenticator にアカウントを既に追加している場合は、そのアカウントをタップし、**[パスキーの作成]** をタップします。

        [Image: Android デバイスの Authenticator でパスキーを作成する方法を示すスクリーンショット。]
7. 多要素認証 (MFA) を完了します。
8. 必要に応じて、**[設定]** をタップし、画面ロックを設定します。

    [Image: Android デバイス用 Authenticator でパスキーのロック画面を設定する方法を示すスクリーンショット。]
9. **[設定]** をタップして、Authenticator をパスキー プロバイダーとして有効にします。

    注記

    Android でパスキー プロバイダーを有効にする手順は、デバイスの作成とモデルによって異なる場合があります。 デバイス設定で **Passkey** を検索するか、デバイスの製造元に問い合わせてください。 デバイスで Android 14 を実行していて、パスキー プロバイダーとして Authenticator を有効にできない場合は、Android 15 にアップグレードすることをお勧めします。

    [Image: Android デバイスの Authenticator を使用して[設定] を開き、画面の指示に従っていることを示すスクリーンショット。]

    1. **[パスワードとアカウント]** を開きます。

        [Image: Android デバイスの Authenticator を使用してパスワードとパスワード オプションを選択するスクリーンショット。]
    2. [**その他のプロバイダー**] セクションで、[**Authenticator**] が選択されていることを確認します。

        [Image: Android デバイスで Authenticator を使用してプロバイダーとして Authenticator を有効にすることを示すスクリーンショット。]
10. Authenticator に戻ったら、[完了]タップして、Authenticator をパスキー プロバイダーとして追加したことを確認します。 この後、アカウントのサインイン方法として追加された**パスキー**を確認できます。 もう一度 **[完了]** をタップして完了します。

    [Image: Android デバイスの Authenticator に追加されたアカウントを示すスクリーンショット。]

    Authenticator により、職場または学校のアカウント ポリシーに従って、サインイン用のパスキー、パスワードレス、MFA を設定します。
11. Authenticator でパスキーのセットアップを完了したら、**セキュリティ情報** が開いているブラウザーに戻ります。 [**次へ**] を選択します。 [Image: Android 上の Authenticator でパスキーのセットアップを完了するウィザードを示すスクリーンショット。]
12. ウィザードは、パスキーが Authenticator で作成されたことを確認します。
13. パスキーが作成されたら、**[完了]** を選択します。

    [Image: パスキーが Android で作成されたことを確認するスクリーンショット。]
14. **セキュリティ情報**で、新しいパスキーが追加されたことを確認できます。

    [Image: 他のデバイスの [セキュリティ情報] の Android サインイン 方法の新しいパスキーを示すスクリーンショット。]

#### WebAuthn フローを利用する（Android）

Authenticator にサインインしてパスキーを登録できない場合は、WebAuthnセキュリティ情報から直接登録できます。

注記

管理者が構成証明を有効にしている場合、この方法で Authenticator にパスキーを登録することはできません。

別のデバイスで **セキュリティ情報** にサインインする場合は、Bluetoothとインターネット接続が必要です。 組織では、次の 2 つのエンドポイントへの接続を許可する必要があります。

- `cable.ua5v.com`
- `cable.auth.com`

組織でBluetoothの使用が制限されている場合は、パスキー対応のFIDO2認証子と排他的にBluetoothペアリングを許可することにより、パスキーのクロスデバイス登録を可能にできます。 詳細については、「[Bluetooth 制限された環境でのパスキー](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/passkeys/?tabs=windows%2Cintune#passkeys-in-bluetooth-restricted-environments)」を参照してください。

1. **[セキュリティ情報]** で Authenticator にパスキーを追加する際、**[問題が発生した場合]** をタップします。

    [Image: 問題が発生した場合に別の方法で登録する方法を示すスクリーンショット。]
2. 次に、**[別の方法でパスキーを作成]** をタップします。

    [Image: パスキーを別の方法で登録する方法を示すスクリーンショット。]
3. **[Android]** を選び、フローの残りの部分を実行して、デバイスにパスキーを登録します。

    [Image: 問題が発生した場合に Android で別の方法を選択する方法を示すスクリーンショット。]

ユーザーが元の手順に戻し、サインインを使用して Authenticator にパスキーを登録する場合:

1. **[セキュリティ情報]** で Authenticator にパスキーを追加する際、**[問題が発生した場合]** をタップします。
2. 次に、Authenticator にサインインして **[別の方法でパスキーを作成]** をタップします。
3. フローの残りの部分を実行して、デバイスにパスキーを登録します。

注記

macOS の Chrome ブラウザーにパスキーを登録する場合は、メッセージが表示されたら、`login.microsoft.com` がセキュリティ キーまたはデバイスにアクセスできるようにします。

### Authenticator for Android でパスキーを削除する

Authenticator からパスキーを削除するには、アカウント名をタップし、**[設定]** をタップしてから、**[パスキーの削除]** をタップします。

ほとんどの場合、パスキーは[セキュリティ情報](https://mysignins.microsoft.com/security-info)からも削除されます。 そうでない場合は、**セキュリティ情報** に移動し、**[削除]** を選択して削除します。

### Android でのパスキー登録のトラブルシューティング

パスキーを登録しようとすると、認証アプリにローカルに格納されますが、認証サーバーには登録されない場合があります。 たとえば、パスキー プロバイダーが許可されていない場合や、接続がタイムアウトになる場合があります。パスキーを登録しようとして、パスキーが既に存在するというエラーが表示された場合は、Authenticator でローカルに作成されたパスキー を削除 、登録を再試行します。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-register-passkey-with-security-key"} -->
## パスキーを FIDO2 セキュリティ キーに登録する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-register-passkey-with-security-key
- Service: entra-id / authentication
- Article date: 2026-07-05
- Summary: Microsoft Entra IDで FIDO2 セキュリティ キーにパスキーを登録する方法について説明します。 セキュリティ情報またはメッセージに従って行うサインイン フローを使用してください。

FIDO2 セキュリティ キーは、物理認証システムに格納されているデバイス バインドパスキーです。 秘密キーがセキュリティ キーから離れることはなく、リモート フィッシング攻撃に対する強力な保護が提供されます。 セキュリティ キーは、高度に規制された業界または特権を持つユーザーに推奨されます。

この記事では、FIDO2 セキュリティ キーを使用して、パスキーを認証方法として登録する方法について説明します。 登録後、 [セキュリティ キーを使用してサインイン](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-security-key-sign-in)できます。

### 初回登録

パスキーを初めて登録するには、Web ブラウザーで [セキュリティ情報](https://mysignins.microsoft.com/security-info) を開いて登録を完了します。

1. Web ブラウザーを開き、[ [セキュリティ情報](https://mysignins.microsoft.com/security-info)] にサインインします。
2. 多要素認証 (MFA) を使用してサインインします。
3. [ **Add sign-in method**&gt;**Choose a method**&gt;**Passkey**] をタップします。
4. [**次へ**] をタップします。
5. パスキー (FIDO2) を保存する場所を選択します。

    注

    表示されるオプションは、ブラウザーとデバイスのオペレーティング システムによって異なります。 登録プロセスを開始したデバイスがパスキー (FIDO2) をサポートしている場合は、パスキーをそのデバイスに保存するように求められます。 **[別のデバイスを使用する]** または **[その他のオプション]** を選択して、パスキーを保存するための追加の方法を表示します。
6. (省略可能)以前にモバイル デバイスでパスキー (FIDO2) を設定し、サインインを高速化するためにそのデバイスを記憶するオプションを選択した場合、デバイス名が選択可能なオプションとして表示されることがあります。 この場合は、次の手順を実行します。

    1. **セキュリティ キー**の選択。
    2. プロンプトに従ってセキュリティ キーを接続し、PIN または生体認証方法を指定します。
    3. これらの手順を完了すると、[ **マイ セキュリティ情報** ] 画面にリダイレクトされます。この画面では、新しいサインイン方法の既定の名前を変更できます。
    4. **[完了]** を選択して、新しいメソッドの登録を完了します。

### プロンプトされた登録

組織でパスキー (FIDO2) を登録する必要がある場合は、サインイン後にメッセージが表示されます。

1. パスキー (FIDO2) を追加するプロンプトが表示されたら、[ **次へ**] をタップします。
2. `login.microsoftonline.com`に向けられます。
3. パスキー (FIDO2) を保存する場所を選択します。

    注

    表示されるオプションは、ブラウザーとデバイスのオペレーティング システムによって異なります。 登録プロセスを開始したデバイスがパスキー (FIDO2) をサポートしている場合は、パスキー (FIDO2) をそのデバイスに保存するように求められます。 **[別のデバイスを使用する]** または **[その他のオプション]** を選択して、パスキーを保存するための追加の方法を表示します。
4. (省略可能)以前にモバイル デバイスでパスキー (FIDO2) を設定し、サインインを高速化するためにそのデバイスを記憶するオプションを選択した場合、デバイス名が選択可能なオプションとして表示されることがあります。 この場合は、次の手順を実行します。

    1. **セキュリティ キー**の選択。
    2. プロンプトに従ってセキュリティ キーを接続し、PIN または生体認証方法を指定します。
    3. これらの手順を完了すると、[ **マイ セキュリティ情報** ] 画面にリダイレクトされます。この画面では、新しいサインイン方法の既定の名前を変更できます。
    4. **[完了]** を選択して、新しいメソッドの登録を完了します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-security-key-sign-in"} -->
## FIDO2 セキュリティ キーを使用してサインインする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-security-key-sign-in
- Service: entra-id / authentication
- Article date: 2026-07-05
- Summary: FIDO2 セキュリティ キーを使用してMicrosoft Entra IDにサインインする方法について説明します。 Web アプリ、Windows、オンプレミスのリソースにサインインします。

FIDO2 セキュリティ キーは、物理認証システムに格納されているデバイス バインドパスキーです。 秘密キーがセキュリティ キーから離れることはなく、リモート フィッシング攻撃に対する強力な保護が提供されます。 セキュリティ キーはさまざまなフォーム ファクター (USB、NFC、Bluetooth) で提供され、高度に規制された業界や特権を持つユーザーに推奨されます。

ネイティブ アプリ、Web ブラウザー、オペレーティング システム間でのパスキー (FIDO2) 認証の可用性の詳細については、「[Microsoft Entra IDを使用した FIDO2 認証のサポート](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-fido2-compatibility)」を参照してください。

### セキュリティ キーを使用してサインインする

1. ブラウザーを開き、アクセスしようとしているリソース ( [Office](https://www.office.com) など) に移動します。
2. ユーザー名を入力し、[ **次へ** ] を選択してサインインできます。 最後にパスキーを使用してサインインした場合は、パスキーを使用してサインインするように自動的に求められます。

    [Image: ユーザー名と [次へ] ボタンを含む [サインイン] ページを示すスクリーンショット。]

    または、 **サインイン オプション**&gt;**Face、フィンガープリント、PIN、またはセキュリティ キー** を選択して、ユーザー名なしでサインインします。

    [Image: [サインイン オプション] ボタンと[Face]、[指紋]、[PIN]、[セキュリティ キー] オプションを示すスクリーンショット。]
3. **[セキュリティ キー]** を選択します。

    [Image: [セキュリティ キーを含むパスキーの選択] ダイアログ を示すスクリーンショット。]
4. デバイスでセキュリティ ウィンドウが開きます。 FIDO2 セキュリティ キーが USB キーの場合は挿入し、NFC キーの場合はリーダーの近くに配置します。
5. ID を確認するには、オペレーティング システムまたはブラウザーのダイアログでメッセージが表示されたら、指紋をスキャンするか、PIN を入力します。
6. 職場または学校アカウントでサインインしている。

### 既知の問題

#### 孤立したパスキー

孤立したパスキーとは、セキュリティ キーにパスキーが残っているにもかかわらず、Microsoft Entra ID に登録されなくなった状態を指します。 これは通常、パスキーがユーザーのセキュリティ情報から削除されたか、ポリシーの変更によって削除されたが、ローカル資格情報がクリーンアップされなかった場合に発生します。

孤立したパスキーによってサインインがブロックされている場合:

1. セキュリティ キーの管理ツールを使用して、セキュリティ キーから孤立したパスキーを削除します。
2. クリーンアップ後に新しいパスキーを再登録します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-sign-in-entra-passkey-windows"} -->
## Windowsで Microsoft Entra パスキーを使用してサインインする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-sign-in-entra-passkey-windows
- Service: entra-id / authentication
- Article date: 2026-07-05
- Summary: フィッシング詐欺に強い認証用の FIDO2 パスキー プロバイダーとしてWindows Helloを使用して、Windowsで Microsoft Entra パスキーを使用してサインインする方法について説明します。

この記事では、WindowsでMicrosoft Entraパスキーを使用してMicrosoft Entra IDにサインインする方法について説明します。 WindowsのMicrosoft Entra パスキーは、ローカル Windows Hello コンテナーに格納されているデバイス バインドパスキーです。 Windowsでのパスキー Microsoft Entraの概要とWindows Hello for Businessとの比較については、Windows[でのパスキー Microsoft Entra](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-entra-passkeys-on-windows)参照してください。

### Windowsでパスキーを使用してサインインする

Windowsでパスキーを使用してサインインするには、次の手順に従います。

1. ブラウザーを開き、アクセスしようとしているリソース ( [Office](https://www.office.com) など) に移動します。
2. ユーザー名を入力してサインインできます。 最後にパスキーを使用してサインインした場合は、パスキーを使用してサインインするように自動的に求められます。 それ以外の場合は、[ **その他のサインイン方法**] を選択し、[ **Face]、[指紋]、[PIN]、[セキュリティ キー**] の順に選択します。

    **サインイン オプションを選択すると、ユーザー名を入力せずにサインインできます。** **[サインイン] オプション**を選択した場合は、[**Face]、[指紋]、[PIN]、または [セキュリティ キー] を選択します**。 それ以外の場合、次のステップに進んでください。
3. デバイスがWindows セキュリティダイアログを開きます。 Windows Hello (指紋、顔認識、または PIN) を使用して、ID を確認します。

確認後、Microsoft Entra IDにサインインします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-sign-in-passkey"} -->
## 同期されたパスキーを使用してサインインする (FIDO2) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-sign-in-passkey
- Service: entra-id / authentication
- Article date: 2026-07-05
- Summary: Windows、iOS、または Android のブラウザーを使用して、職場または学校アカウントの同期されたパスキー (FIDO2) を使用してMicrosoft Entra IDにサインインする方法について説明します。

この記事では、同期されたパスキーを使用して職場または学校アカウントにサインインする方法について説明します。

パスキーは、サインインするのと同じデバイスに同期することも、別のデバイスに同期することもできます。

- 同じデバイスのパスキーを使用する
- 別のデバイスのパスキーを使用する

### 同じデバイスのパスキーを使用する

1. Azure ポータルなどのMicrosoft アプリケーションまたは Web サイトの **[サインイン**] を選択[します](https://ms.portal.azure.com/)。
2. 最後にパスキーを使用してサインインした場合は、パスキーを使用してサインインするように自動的に求められます。 アカウントを選びます。

    [Image: 職場または学校アカウントを含む [アカウントの選択] ダイアログを示すスクリーンショット。]

    それ以外の場合は、ユーザー名を入力し、[ **次へ** ] を選択してサインインできます。

    [Image: ユーザー名と [次へ] ボタンが入力された [サインイン] ダイアログを示すスクリーンショット。]
3. 多要素認証 (MFA) を完了します。
4. 職場または学校アカウントでサインインしている。

### 別のデバイスのパスキーを使用する

1. Azure ポータルなどのMicrosoft アプリケーションまたは Web サイトの **[サインイン**] を選択[します](https://ms.portal.azure.com/)。
2. Microsoft Edgeで、名前を入力する場所を右クリックし、[**別のデバイスからパスキーを使用**する] を選択します&gt;**電話、タブレット、またはセキュリティ キーを使用**します。

    [Image: Microsoft Edgeの [別のデバイスからパスキーを使用する] オプションと、[保存されたパスキーを使用してサインイン] ダイアログの [電話、タブレット、またはセキュリティ キーを使用する] オプションを示すスクリーンショット。]

    Google Chrome で、自分の名前を入力する場所を選択し、[ **別のデバイスからパスキーを使用**する] を選択します。

    [Image: Chrome アカウントの一覧の [別のデバイスからパスキーを使用する] オプションを示すスクリーンショット。]

    [ **サインイン**] をクリックし、 **サインイン オプション**&gt;**Face、指紋、PIN、またはセキュリティ キー**を選択することもできます。

    [Image: [サインイン オプション] ボタンと[Face]、[指紋]、[PIN]、[セキュリティ キー] オプションを示すスクリーンショット。]
3. **iPhone、iPad、または Android デバイスを選択します**。 表示されるその他のサインイン オプションは、アカウントとデバイスによって異なります。

    [Image: [パスキーの選択] ダイアログの [iPhone、iPad、または Android デバイス] オプションを示すスクリーンショット。]
4. サインインするデバイスに QR コードが表示されます。 パスキーを持つ他のデバイスで QR コードをスキャンします。

    [Image: スキャンする QR コードを含むパスキー ダイアログでのサインインを示すスクリーンショット。]
5. iOS では、[ **Passkey でサインイン**] を選択します。 Android では、[ **パスキーを使用してサインインする**] を選択します。
6. この手順ではBluetoothとインターネット接続が必要であり、両方のデバイスで有効にする必要があります。 サインインするデバイスには、次の画面が表示されます。

    [Image: [デバイスに接続されたメッセージを含むパスキーによるサインイン] ダイアログを示すスクリーンショット。]
7. 多要素認証 (MFA) を完了します。
8. 職場または学校アカウントの同期されたパスキーでサインインしています。

### 既知の問題

同期されたパスキー サインインに関する問題を回避するには、次の既知の問題を確認してください。

#### クロスデバイス認証のために両方のデバイスでBluetoothを有効にする必要があります

別のモバイル デバイスを使用してサインインする場合は、サインインしようとしているデバイスと、パスキーを使用してモバイル デバイスでBluetoothを有効にする必要があります。

一部の組織では、パスキーの使用を含む Bluetooth の使用を制限しています。 このような場合、組織がパスキー許可するには、パスキー対応の FIDO2 認証システムとの Bluetooth ペアリングのみを許可します。 詳細については、「[Bluetooth 制限された環境でのパスキー](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/passkeys/?tabs=intune#passkeys-in-bluetooth-restricted-environments)」を参照してください。

#### 孤立したパスキー

孤立状態のパスキーとは、パスキーがユーザーのデバイス上に残っている一方で、Microsoft Entra ID には登録されなくなっている状態のことです。 これは通常、パスキーがユーザーのセキュリティ情報から削除されたか、ポリシーの変更によって削除されたが、ローカル資格情報がクリーンアップされなかった場合に発生します。

孤立したパスキーによってサインインがブロックされている場合:

1. 孤立したパスキーをデバイスまたはパスキー プロバイダーから削除します。
2. クリーンアップ後に新しいパスキーを再登録します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-sign-in-passkey-authenticator"} -->
## Android および iOS デバイス用 Authenticator のパスキーを使用してサインインする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-sign-in-passkey-authenticator
- Service: entra-id / authentication
- Article date: 2026-07-05
- Summary: Microsoft Authenticator for Android および iOS でパスキーを使用してサインインする方法について説明します。 同じデバイス、クロスデバイス、またはネイティブ アプリ認証を使用します。

この記事では、Authenticator で Microsoft Entra ID でパスキーを使用する場合のサインイン エクスペリエンスについて説明します。 ネイティブ アプリケーション、Web ブラウザー、オペレーティング システムで Microsoft Entra ID パスキー (FIDO2) 認証を利用できるかどうかの詳細については、「[Microsoft Entra ID を使用した FIDO2 認証のサポート](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-fido2-compatibility)」をご覧ください。

## [iOS](#tab/iOS)
Authenticator でパスキーを使用してサインインするには、iOS デバイスで iOS 17 以降を実行する必要があります。

#### ブラウザーでの同じデバイス認証 (iOS)

iOS デバイス上の Authenticator のパスキーを使用して Microsoft Entra ID にサインインするには、次の手順に従います。

1. iOS デバイスでブラウザーを開き、アクセスしようとしているリソース ([Office](https://www.office.com)など) に移動します。
2. サインインするユーザー名を入力します。 最後にパスキーを使用してサインインした場合は、パスキーを使用してサインインするように求められます。 それ以外の場合は、「**その他の方法でサインイン**」を選択し、次に「**Face」、「指紋」、「PIN」、または「セキュリティキー**」を選択します。

    **サインイン オプションを選択すると、ユーザー名を入力せずにサインインできます。** **サインイン オプション**選択した場合は、**Face、指紋、PIN、またはセキュリティ キー**を選択します。 それ以外の場合は、次の手順に進みます。

    注

    ユーザー名なしでサインインしようとし、複数のパスキーがデバイスに保存されている場合は、サインインに使用するパスキーを選択するように求められます。
3. パスキーを選択するには、iOS オペレーティング システム ダイアログの手順に従います。 Face ID または Touch ID を使用するか、デバイスの PIN を入力して、自分を確認します。

これで、Microsoft Entra ID にサインインしました。

#### クロスデバイス認証 (iOS)

iOS デバイス上の Authenticator のパスキーを使用して、別のデバイス上の Microsoft Entra ID にサインインするには、次の手順に従います。

このサインイン オプションでは、両方のデバイスに Bluetooth とインターネット接続が必要です。 組織で Bluetooth の使用が制限されている場合、管理者は、パスキー対応の FIDO2 認証器とのみ Bluetooth ペアリングを許可することで、パスキーによるデバイス間サインインを許可できます。 Bluetooth の使用をパスキーのみにする構成方法について詳しくは、「[Bluetooth が制限された環境でのパスキー](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/passkeys/?tabs=windows%2Cintune#passkeys-in-bluetooth-restricted-environments)」をご覧ください。

1. Microsoft Entra ID にサインインする別のデバイスで、アクセスしようとしているリソース、例えば [Office](https://www.office.com)などにアクセスします。
2. サインインするユーザー名を入力します。 最後にパスキーを使用して認証を行った場合は、パスキーによる認証を求められます。 それ以外の場合は、「**その他の方法でサインイン**」を選択し、次に「**Face」、「指紋」、「PIN」、または「セキュリティキー**」を選択します。

    **サインイン オプションを選択すると、ユーザー名を入力せずにサインインできます。** **サインイン オプション**選択した場合は、**Face、指紋、PIN、またはセキュリティ キー**を選択します。 それ以外の場合は、次の手順に進みます。

    注

    ユーザー名なしでサインインしようとし、複数のパスキーがデバイスに保存されている場合は、サインインに使用するパスキーを選択するように求められます。
3. クロスデバイス認証を開始するには、オペレーティング システムまたはブラウザー プロンプトの手順に従います。 Windows 11 23H2 以降では、**[iPhone、iPad、または Android デバイス]** を選択します。
4. 画面に QR コードが表示されたら、カメラ アプリを開き、QR コードをスキャンします。

    iOS Authenticator アプリ内のカメラは、WebAuthn QR コードのスキャンをサポートしていません。 システム カメラ アプリを使用する必要があります。
5. オプションが表示されたら、**[パスキーでサインイン]** を選択します。

    この手順では Bluetooth とインターネット接続が必要であり、モバイル デバイスとリモート デバイスで両方を有効にする必要があります。
6. パスキーを選択するには、iOS オペレーティング システム ダイアログの手順に従います。 Face ID または Touch ID を使用するか、デバイスの PIN を入力して、自分を確認します。

これで、他のデバイスで Microsoft Entra ID にサインインしました。

#### ネイティブ Microsoft アプリケーション (iOS) での同じデバイス認証

iOS デバイスで Authenticator を使用すると、OneDrive、SharePoint、Outlook などの他の Microsoft アプリへのパスキーを使用してシームレスにサインインできます。

## [Android](#tab/Android)
Authenticator でパスキーを使用してサインインするには、Android デバイスで Android 14 以降を実行する必要があります。

#### ブラウザーでの同じデバイス認証 (Android)

Android デバイスの Authenticator でパスキーを使用して Microsoft Entra ID にサインインするには、次の手順に従います。

注

Android 上の Microsoft Edge での同じデバイス認証のサポートは近日公開予定です。

1. Android デバイスでブラウザーを開き、アクセスするリソース ( [Office](https://www.office.com) など) に移動します。
2. サインインするユーザー名を入力します。 最後にパスキーを使用してサインインした場合は、パスキーを使用してサインインするように求められます。 それ以外の場合は、「**その他の方法でサインイン**」を選択し、次に「**Face」、「指紋」、「PIN」、または「セキュリティキー**」を選択します。

    **サインイン オプションを選択すると、ユーザー名を入力せずにサインインできます。** **サインイン オプション**選択した場合は、**Face、指紋、PIN、またはセキュリティ キー**を選択します。 それ以外の場合は、次の手順に進みます。

    注記

    ユーザー名なしでサインインしようとし、複数のパスキーがデバイスに保存されている場合は、サインインに使用するパスキーを選択するように求められます。
3. パスキーを選択するには、Android オペレーティング システム ダイアログの手順に従います。 顔または指紋をスキャンするか、デバイスの PIN またはロック解除ジェスチャを入力して、自分を確認します。

これで、Microsoft Entra ID にサインインしました。

#### クロスデバイス認証 (Android)

Android デバイスの Authenticator でパスキーを使用して、別のデバイスの Microsoft Entra ID にサインインするには、次の手順に従います。

このサインイン オプションでは、両方のデバイスに Bluetooth とインターネット接続が必要です。 組織で Bluetooth の使用が制限されている場合、管理者は、パスキー対応の FIDO2 認証子とのペアリングに限って Bluetooth ペアリングを許可することで、パスキーによるデバイス間サインインを許可できます。 Bluetooth の使用をパスキーのみにする構成方法について詳しくは、「[Bluetooth が制限された環境でのパスキー](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/passkeys/?tabs=windows%2Cintune#passkeys-in-bluetooth-restricted-environments)」をご覧ください。

1. Microsoft Entra ID にサインインする別のデバイスで、アクセスしようとしているリソース、例えば [Office](https://www.office.com)などにアクセスします。
2. サインインするユーザー名を入力します。 最後にパスキーを使用して認証を行った場合は、パスキーによる認証を求められます。 それ以外の場合は、「**その他の方法でサインイン**」を選択し、次に「**Face」、「指紋」、「PIN」、または「セキュリティキー**」を選択します。

    または、ユーザー名を入力せずにサインインするには、**[サインイン オプション]** を選択します。 **サインイン オプション**選択した場合は、**Face、指紋、PIN、またはセキュリティ キー**を選択します。 それ以外の場合は、次の手順に進みます。
3. クロスデバイス認証を開始するには、オペレーティング システムまたはブラウザー プロンプトの手順に従います。 Windows 11 23H2 以降では、**[iPhone、iPad、または Android デバイス]** を選択します。
4. 画面に QR コードが表示されたら、カメラ アプリを開き、QR コードをスキャンします。 Authenticator でカメラを使用することもできます。 パスキー アカウント タイルに移動してタップします。 **パスキーの詳細**の下に、QR コードをスキャンするためのボタンが右下隅に表示されます。

    注

    この手順では Bluetooth とインターネット接続が必要であり、モバイル デバイスとリモート デバイスで両方を有効にする必要があります。
5. パスキーを選択するには、Android オペレーティング システム ダイアログの手順に従います。 顔または指紋をスキャンして自分を確認するか、デバイスの PIN を入力するか、ジェスチャのロックを解除します。

他のデバイスでは、Microsoft Entra ID にサインインしています。

#### ネイティブ Microsoft アプリケーションでの同じデバイス認証 (Android)

Android デバイスで Authenticator を使用すると、OneDrive、SharePoint、Outlook などの他の Microsoft アプリへのパスキーを使用してシームレスにサインインできます。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-synced-passkeys"} -->
## Microsoft Entra IDで同期されたパスキーを有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-synced-passkeys
- Service: entra-id / authentication
- Article date: 2026-07-05
- Summary: 同期されたパスキーを使用して構成、登録、サインインする方法など、Microsoft Entra IDで同期されたパスキーについて説明します。

この記事では、Microsoft Entra IDで同期されたパスキーの概要について説明します。そのしくみと作業の開始方法について説明します。 同期されたパスキーを使用すると、秘密キーを作成、暗号化、クラウド パスキー プロバイダーに同期できるため、複数のデバイスで使用できます。

### 概要

同期されたパスキーは、秘密キーがハードウェア セキュリティ モジュール (HSM) によって作成され、ローカル デバイスで暗号化されるパスキーです。 暗号化されたキーは同期され、クラウド パスキー プロバイダーに格納されます。 その後、同じパスキー プロバイダーで認証された他のデバイスは、パスキーを使用できます。 パスキー プロバイダーの例としては、 [Apple iCloud キーチェーン](https://support.apple.com/en-us/102195) や [Google パスワード マネージャー](https://security.googleblog.com/2022/10/SecurityofPasskeysintheGooglePasswordManager.html)などがあります。

同期されたパスキーは、デバイス バインドパスキーに関連する発行と管理の課題の多くを解決します。

- **回復**: 同期されたパスキーはクラウドにバックアップされるため、ユーザーは 1 つのデバイスを失ってもアクセス権を失いません。
- **使いやすさ**: ユーザーは、各デバイスに個別のパスキーを登録する必要はありません。 パスキーは自動的に同期されます。
- **コストの削減**: 組織は、個別の物理認証子を発行して管理する必要はありません。

Microsoft アカウントの何億ものコンシューマー ユーザーからの学習に基づいて、

- **99% のユーザーが同期されたパスキーを正常に登録しました。**
- 同期されたパスキーは、 **パスワードと従来の MFA の組み合わせに比べて 14 倍高速です。69 秒ではなく 3 秒です。**
- ユーザーは、**従来の認証方法 (95% 対 30%) よりも、同期されたパスキーを使用したサインインの方が 3 倍成功**しています。

同期されたパスキーは構成証明をサポートしていません。 高度に規制された環境外のユーザーや、機密性の高いシステムにアクセスしないほとんどのユーザーにとって、同期されたパスキーは従来の MFA に代わる便利で低コストの代替手段を提供します。 管理者や高い特権を持つユーザーの場合は、[FIDO2 セキュリティ キー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-register-passkey-with-security-key)や[Microsoft Authenticatorのパスキー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-enable-authenticator-passkey)などのデバイス バインドパスキーを検討してください。

パスキーの種類の比較については、[Microsoft Entra IDの「パスキー (FIDO2) 認証方法」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)。

### 同期されたパスキーの前提条件

- 認証方法を構成するための少なくとも [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) アクセス許可を持つアカウント。
- Microsoft Entra 管理センターの[認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2#enable-passkey-profiles)にある**Passkey (FIDO2)**ポリシーで、**パスキーによるサインインを有効にする**必要があります。
- 次の表は、同期されたパスキーを使用するための最小デバイス要件の概要を示しています。 列は、ユーザーがサインインするデバイス プラットフォームを表します。

    | Passkey プロバイダー | Windows | macOS | iOS | Android |
    | --- | --- | --- | --- | --- |
    | Apple パスワード (iCloud キーチェーンとも呼ばれます) | 該当なし | 標準で組み込まれています。macOS 13 以降 | 標準で組み込まれています。iOS 16 以降 | 該当なし |
    | Google パスワード マネージャー | Chrome に組み込まれている | Chrome に組み込まれている | Chrome に組み込まれています。iOS 17 以降 | ネイティブに組み込まれています (Samsung デバイスを除く)。Android 9 以降 |
    | その他のパスキー プロバイダー (1Password、Bitwarden など) | ブラウザー拡張機能を確認する | ブラウザー拡張機能を確認する | アプリを確認します。iOS 17 以降 | アプリを確認します。Android 14 以降 |

### 同期されたパスキーのプロファイルを構成する

1. 少なくとも [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)として Microsoft Entra 管理センターにサインインします。
2. **[Entra ID]**&gt;**[Authentication メソッド]** に移動します。
3. **認証方法 |[ポリシー] ページで**、**パスキー (FIDO2)**&gt;**構成**を選択します。
4. [ **セルフサービス セットアップを許可する]** が選択されていることを確認します。
5. [ **+ プロファイルの追加] を選択します**。
6. 名前を指定し、[ **パスキーの種類**] で [ **同期済み** ] と **[保存]** を選択します。

    [Image: 同期されたパスキー プロファイルを追加する方法を示すスクリーンショット。]

    注

    特定のパスキー プロファイルに対して同期されたパスキーを無効にした場合、対象ユーザーは既にパスキーを登録している場合でも、同期されたパスキーでサインインできません。

### 同期済みパスキー用プロファイルのグループを有効にして対象にする

1. 少なくとも [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)として Microsoft Entra 管理センターにサインインします。
2. **[Entra ID]**&gt;**[Authentication メソッド]** に移動します。
3. **認証方法 | ポリシー** ページで、**パスキー (FIDO2)**&gt;**有効化して対象を指定** を選択します。
4. [ **有効化] タブと [ターゲット** ] タブで、[ **有効]** が **[オン]** になっていることを確認します。
5. [ **ターゲットの追加]** を選択し、[ **すべてのユーザー** ] または **[ターゲットの選択]** を選択して特定のグループを選択します。

    [Image: パスキー プロファイルのターゲットを追加する方法を示すスクリーンショット。]
6. 同期されたパスキーのプロファイルを選択し、[ **保存]** を選択します。

    [Image: 同期されたパスキー プロファイルを有効にする方法を示すスクリーンショット。]

    注

    ターゲット グループ (エンジニアリングなど) は、複数のパスキー プロファイルのスコープを設定できます。 ユーザーが複数のパスキー プロファイルのスコープに設定されている場合、パスキーがスコープ付きパスキー プロファイルの少なくとも 1 つの要件を完全に満たしている場合、パスキーを使用した登録と認証が許可されます。 チェックの順序は特にありません。 ユーザーが **Passkey (FIDO2)** ポリシーで除外されたグループのメンバーである場合、ユーザーはパスキー (FIDO2) の登録またはサインインから完全にブロックされます。 ブロックは、含まれるすべてのグループのメンバーシップよりも優先されます。
7. 選択したユーザーの同期されたパスキーを有効にするには、[ **保存] を** 選択します。

### 同期されたパスキーを登録する

管理者が同期されたパスキーを有効にすると、ユーザーはデバイスにパスキーを登録できます。 パスキーは、ユーザーのパスキー プロバイダー (iCloud キーチェーン、Google パスワード マネージャー、サードパーティ プロバイダーなど) を介して他のデバイスと同期されます。

登録手順については、「 [同期されたパスキーの登録 (FIDO2)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-register-passkey)」を参照してください。

### 同期されたパスキーを使用してサインインする

登録後、ユーザーは、パスキーが使用可能な任意のデバイスで同期されたパスキーを使用して、Microsoft Entra IDにサインインできます。

サインイン手順については、「 [同期されたパスキーを使用したサインイン (FIDO2)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-sign-in-passkey)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-transfer-authenticator-new-phone"} -->
## Microsoft Authenticatorを新しい電話に転送する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-transfer-authenticator-new-phone
- Service: entra-id / authentication
- Article date: 2026-07-02
- Summary: パスキーのセットアップ手順など、新しい電話に切り替えるときにMicrosoft Authenticatorアカウント エントリをバックアップおよび復元する方法について説明します。

Microsoft Authenticatorは、多要素認証、検証コード、パスワードレス サインイン、パスキーをサポートすることで、サインインの安全性と利便性を高めるのに役立ちます。 新しい電話を入手したら、サポートされているアカウント エントリを回復し、これらの方法をバックアップして復元することで、セキュリティで保護されたサインイン方法を引き続き使用できます。 職場または学校アカウントでは、復元後に追加のサインインとセットアップ手順が必要です。 古い携帯電話を消去、取引、またはリサイクルする前に転送を完了してください。

職場または学校アカウントの場合、バックアップと復元によってアカウント名が転送され、新しい電話でアカウントを認識できるようになります。 セットアップを完了するには、引き続きもう一度サインインする必要があります。 パスキーは、Authenticator アカウントのバックアップとは別に処理されます。 パスキーが古い電話にのみ保存されている場合は、新しい電話機の新しいパスキーを追加します。 同期された資格情報マネージャーにパスキーが保存されている場合は、その資格情報マネージャーにサインインした後で使用できる可能性があります。

Important

新しい電話から最も頻繁に使用するアカウントとリソースにサインインできることを確認するまで、古い電話を維持します。

次の手順を使用して、古い電話でMicrosoft Authenticatorをバックアップし、新しい電話で復元し、必要に応じてパスキーをもう一度設定します。

### iOS でMicrosoft Authenticatorをバックアップする

iOS デバイスで Authenticator をバックアップするには、Authenticator の iCloud ドライブ、キーチェーン、バックアップを有効にします。

1. iOS デバイスで iCloud Drive を有効にします。
2. iOS デバイスで iCloud キーチェーンを有効にします。
3. iOS デバイスで iCloud バックアップを有効にします。
4. **Apple Account**&gt;**iCloud**&gt;**Saved to iCloud に**移動し、**Authenticator** を検索します。
5. **Authenticator** トグルをオンにします。

### Android でMicrosoft Authenticatorをバックアップする

Android デバイスで Authenticator アカウント エントリをバックアップするには:

1. Microsoft Authenticator を開きます。
2. メニューを開き **、[設定]** を選択します。
3. **クラウド バックアップ**を有効にします。
4. バックアップを保存する Microsoft 個人用アカウントを選択します。
5. [**OK**] をタップします。

Note

バックアップを間違ったアカウントに保存した場合、または復旧アカウントを変更する必要がある場合は、既存のバックアップを削除して新しいバックアップを作成します。

### 復元後の予期される動作

#### 職場または学校アカウント

- アカウント名のみが復元されます。
- 新しい電話でセットアップを完了するには、アカウントを開き、もう一度サインインします。
- **"サインインしてアカウントを追加する"** という赤いテキストが表示される場合があります。

    [Image: [サインインしてアカウントを追加する] メッセージが表示されているMicrosoft Authenticatorに復元された職場アカウントのスクリーンショット。]
- アカウントにパスキーが適用されている場合は、古いデバイスを削除する前に、新しい電話のパスキーを設定します。

新しい電話でパスキーを設定する手順:

1. 引き続き古いデバイスにアクセスできる場合は、それを使用して最初にサインインします。
2. **aka.ms/mysecurityinfo** の [\[セキュリティ情報](https://aka.ms/mysecurityinfo)] に移動し、[**サインイン方法の追加]** を選択し、**Microsoft Authenticatorで** **[パスキー**] または [パスキー] を選択します。
3. プロンプトに従って、新しいパスキーを作成して保存します。
4. 新しいパスキーを使用してサインインをテストします。
5. 新しいパスキーが機能したら、使用しなくなった古いパスキーまたはデバイスを削除します。

Note

パスワード マネージャー、Google パスワード マネージャー、Apple iCloud キーチェーン Microsoftなどの同期された資格情報マネージャーにパスキーが保存されている場合は、新しいパスキーを作成する必要がない場合があります。 古いデバイスを削除する前に、パスキーが使用可能であることを確認します。

IT 管理者がセルフサービス パスキーのセットアップを許可していない場合は、承認された回復または登録プロセスについて管理者またはヘルプ デスクに問い合わせてください。

#### 個人用 Microsoft アカウント

- アカウントで 30 秒ごとに更新される 1 回限りのパスワード コードのみを使用している場合は、確認コードエントリが復元されます。
- アカウントでパスワードなしのサインインも使用されている場合は、アカウント名のみが復元され、セットアップを完了するにはもう一度サインインする必要があります。

#### サードパーティのアカウント (Amazon、Facebook、Gmail など)

- 通常、これらのアカウントでは、30 秒ごとに更新される 1 回限りのパスワード コードが使用されます。
- 確認コード エントリが復元されます。

### 一般的な問題のトラブルシューティング

| Issue | Resolution |
| --- | --- |
| **"サインインしてアカウントを追加します。"** | 復元後の職場または学校アカウントに必要です。 アカウントを開き、もう一度サインインします。 |
| **バックアップからの復元は使用できません。** | 古い電話でバックアップが有効になっていて、同じ回復アカウントを使用していて、同じデバイスの種類に復元していることを確認します。 |
| **利用可能なパスキーがありません。** | 新しい電話で画面ロックが有効になっており、Bluetoothとインターネット接続がデバイス間のサインインに使用できることを確認します。 |
| **パスキーは使用できなくなりました。** | 新しいパスキーを作成し、新しいメソッドが動作した後にのみ、古いパスキーを削除します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/how-to-unlock-users-for-mandatory-multifactor-authentication"} -->
## Azure portal、Microsoft Entra 管理センター、または Microsoft Intune 管理センターの必須多要素認証 (MFA) 要件のロールアウト後にユーザーがサインインできないテナントの適用を延期する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-unlock-users-for-mandatory-multifactor-authentication
- Service: entra-id / authentication
- Article date: 2025-04-19
- Summary: Azure portal、Microsoft Entra 管理センター、または Microsoft Intune 管理センターの必須 MFA 要件のロールアウト後にユーザーがサインインできないテナントの強制を延期するスクリプト

MFA を使用するための必須要件がテナントにロールアウトされた後に MFA メソッドの使用に問題がある場合、ユーザーは Azure portal、Microsoft Entra 管理センター、または Microsoft Intune 管理センターにサインインできない可能性があります。

ユーザーがサインインできない場合は、グローバル管理者として次のスクリプトを実行して、テナントの MFA 適用を一時的に延期できます。

Azure の必須 MFA 要件の詳細については、「 [Azure およびその他の管理ポータルの必須多要素認証の計画」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mandatory-multifactor-authentication)。 次のスクリプトは、フェーズ 1 のアプリケーションにのみ適用されます。

### スクリプト アクション

このスクリプトは、次のアクションを実行します。

- ユーザーのテナントがある場合はそれを選択するか、ユーザーが選択できるテナントの一覧を表示します。 必要に応じて、スクリプトは施行日を要求します。 デフォルトの日付は 2025 年 9 月 30 日です。
- ユーザーをそのテナントにログインします。
- 関連する認証トークンを取得します。
- ユーザーが昇格されたアクセス権を持っているかどうかを確認します。 そうでない場合は、スクリプトが昇格を行います。
- 設定リソース プロバイダー (RP) でユーザーに適切なロールが割り当てられているかどうかを確認します。 そうでない場合は、スクリプトによって適切なロールが割り当てられます。
- Entra ID の施行日を更新します。
- スクリプトによって追加された昇格されたアクセスを削除しようとします。

### [前提条件]

- [Az PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/azure/what-is-azure-powershell)
- [グローバル管理者ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)

### スクリプト

```powershell
param (
    [Parameter(Mandatory=$false)]
    [string]$TenantId,

    [Parameter(Mandatory=$false)]
    [string]$PostponementDateInUTC
)

# Make sure the Az.Accounts module is imported
Import-Module Az.Accounts

function Set-TenantSettingsMFAPostponement {

    Set-ExecutionPolicy -ExecutionPolicy Unrestricted -Scope Process

    $cutoffDate = [datetime]::Parse("2025-10-01T00:00:00Z")
    $isDefaultDate = $false
    if ($PostponementDateInUTC) {
        # ISO 8601 check (basic): YYYY-MM-DDTHH:mm:ssZ
        if ($PostponementDateInUTC -notmatch '^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}Z$') {
            Write-Host "Invalid PostponementDateInUTC format. Must be in ISO 8601 format like '2025-09-30T23:59:59Z'." -ForegroundColor Red
            return
        }
        $valid = $false
        $today = [DateTime]::UtcNow.Date
        $minDate = $today.AddDays(1)
        $maxDate = [DateTime]::ParseExact("2025-09-30T23:59:59Z", "yyyy-MM-ddTHH:mm:ssZ", $null).ToUniversalTime()
        $valid = Check-Date-Is-Valid -maxDate $maxDate -minDate $minDate -dateToCheck $PostponementDateInUTC
        if (-not $valid) {
            return
        }
    } else {
        $PostponementDateInUTC = "2025-09-30T23:59:59Z"
        $isDefaultDate = $true
    }

    # If user didn't specify a tenant in params, let them select.
    if (-not $TenantId) {
        try {
            # Have user log into relevant account
            $connected = Connect-AzAccount -ErrorAction Stop
            # Get all tenants the user has access to
            $tenants = Get-AzTenant -ErrorAction Stop
        } catch {
            Write-Host "Failed to connect and/or fetch list of user's tenants. Error: $($_.Exception.Message)" -ForegroundColor Red
            Write-Host
            return
        }

        if (-not $tenants) {
            Write-Host "No tenants found for this user." -ForegroundColor Red
            return
        }

        # Display them as a numbered list
        Write-Host "Please select a tenant from the list below"
        Write-Host " "
        for ($i = 0; $i -lt $tenants.Count; $i++) {
            Write-Host "$($i + 1)) $($tenants[$i].TenantId) - $($tenants[$i].Name) ($($tenants[$i].DefaultDomain))"
        }
        Write-Host

        # Ask user to select one
        $selection = Read-Host "Enter the number for the tenant you want to use"

        # Validate and extract selected tenant
        if ($selection -match '^\d+$') {
            $selection = [int]$selection
            if ($selection -ge 1 -and $selection -le $tenants.Count) {
                $chosenTenant = $tenants[$selection - 1]
                Write-Host "You selected tenant: $($chosenTenant.TenantId) - $($chosenTenant.Name) ($($chosenTenant.DefaultDomain))" -ForegroundColor Green
                Write-Host
                # Use $chosenTenant.TenantId later in the script
                $TenantId = $chosenTenant.TenantId
            } else {
                Write-Host "Number is out of range. Exiting..." -ForegroundColor Red
                return
            }
        } else {
            Write-Host "Invalid selection. Exiting..." -ForegroundColor Red
            return
        }
    }

    if ($isDefaultDate) {
        $newDate = Select-Postponement-Date
        if (-not $newDate) {
            return
        } else {
            $PostponementDateInUTC = $newDate.ToString("yyyy-MM-ddTHH:mm:ssZ")
            $isDefaultDate = $false
        }
    }

    if ($isDefaultDate) {
        Write-Host "This will update the MFA enforcement date for TenantId: '$($TenantId)' to the DEFAULT date of '$($PostponementDateInUTC)'"
    } else {
        Write-Host "This will update the MFA enforcement date for TenantId: '$($TenantId)' to the date of '$($PostponementDateInUTC)'"
    }
    
    Write-Host
    $confirmation = Read-Host "Do you want to continue (Y/N)?"
    if ($confirmation -match '^[Yy]$') {
        Write-Host "Proceeding..." -ForegroundColor Green
        Write-Host
    } else {
        Write-Host "Operation canceled by user." -ForegroundColor Red
        return
    }

    try {
        $connected = Connect-AzAccount -TenantId $TenantId
    } catch {
        Write-Host "Failed to log the user in to specified tenant. Error: $($_.Exception.Message)" -ForegroundColor Red
        Write-Host
        return
    }
    Start-Sleep -Seconds 3

    # Constants
    $ELEVATED_TENANT_ADMIN_ROLE_ID = "/providers/Microsoft.Authorization/roleDefinitions/18d7d88d-d35e-4fb5-a5c3-7773c20a72d9"
    $OWNER_ROLE_ID = "/providers/Microsoft.Authorization/roleDefinitions/8e3af657-a8ff-443c-a75c-2fe8c4bcb635"

    # Get tokens
    Write-Host "Fetching necessary authorization tokens..."
    try {
        $armToken = Get-StringFromSecureString -secureString (Get-AzAccessToken -ResourceUrl "https://management.azure.com/").Token
        if ($null -eq $armToken) {
            Write-Host "Failed to fetch an authorization token for Azure Resource Manager. Make sure you run: Connect-AzAccount -TenantId '<your tenant id>'" -ForegroundColor Red
            return
        }
            
        Start-Sleep -Seconds 3
    } catch {
        Write-Host "Failed to fetch Azure Resource Manager token. Error: $($_.Exception.Message)" -ForegroundColor Red
        Write-Host
        return
    }

    try {
        $coreToken = Get-StringFromSecureString -secureString (Get-AzAccessToken -ResourceUrl "https://management.core.windows.net/").Token
        if ($null -eq $coreToken) {
            Write-Host "Failed to fetch an authorization token for Azure Resource Manager core. Make sure you run: Connect-AzAccount -TenantId '<your tenant id>'" -ForegroundColor Red
            return
        }
            
        Start-Sleep -Seconds 3
    } catch {
        Write-Host "Failed to fetch Azure Resource Manager token. Error: $($_.Exception.Message)" -ForegroundColor Red
        Write-Host
        return
    }
    
    $armClaims = Decode-JwtPayload -Jwt $armToken
    $objectId = $armClaims.oid
    if ($null -eq $objectId) {
        Write-Host "Failed to parse objectId from oid claim in Azure Resource Manager token. Make sure you are an admin of this tenant." -ForegroundColor Red
        return
    }
    
    Write-Host "Successfully fetched authorization tokens." -ForegroundColor Green
    Write-Host

    # Check elevated access
    try {
        $roleCheckUri = "https://management.azure.com/providers/Microsoft.PortalServices/providers/Microsoft.Authorization/roleAssignments?api-version=2022-04-01&`$filter=principalId eq '$($objectId)'"
        $roleAssignments = Invoke-RestMethod -Headers @{Authorization = "Bearer $armToken"} -Uri $roleCheckUri -Method GET
            
        Start-Sleep -Seconds 3
    } catch {
        Write-Host "Failed to check user's elevated access. Error: $($_.Exception.Message)" -ForegroundColor Red
        Write-Host
        return
    }

    Write-Host "Checking for elevated access..."
    $hasElevatedAccess = $false
    foreach ($item in $roleAssignments.value) {
        if ($item.properties.roleDefinitionId -eq $ELEVATED_TENANT_ADMIN_ROLE_ID) {
            $hasElevatedAccess = $true
        }
    }

    # Used to determine whether or not to delete elevated access at end
    $alreadyHadElevatedStatus = $hasElevatedAccess
    
    if (-not $hasElevatedAccess) {
        Write-Host "User does NOT have elevated access. Elevating access..."
        $elevateUri = "https://management.azure.com/providers/Microsoft.Authorization/elevateAccess?api-version=2017-05-01"

        try {
            # Attempt the API call and capture the response
            $response = Invoke-RestMethod -Headers @{Authorization = "Bearer $coreToken"} -Uri $elevateUri -Method POST -ErrorAction Stop

            # Even if there's no content in the response, the request could still have succeeded
            Write-Host "Successfully elevated access." -ForegroundColor Green
            Write-Host
            
            Start-Sleep -Seconds 3
        } catch {
            Write-Host "Failed to elevate access. Error: $($_.Exception.Message)"
            Write-Host "Make sure you are already a tenant admin"
            return
        }
    } else {
        Write-Host "User already has elevated access." -ForegroundColor Green
        Write-Host
    }

    try {
        # Re-check role assignments after possible elevation
        $roleAssignments = Invoke-RestMethod -Headers @{Authorization = "Bearer $armToken"} -Uri $roleCheckUri -Method GET
            
        Start-Sleep -Seconds 3
    } catch {
        Write-Host "Failed to re-check user's elevated access. Error: $($_.Exception.Message)" -ForegroundColor Red
        Write-Host

        # Clean up elevated access if we added it
        if (-not $alreadyHadElevatedStatus) {
            Remove-ElevatedAccess -objectId $objectId -TenantId $TenantId
        }
        return
    }

    Write-Host "Checking if owner role exists..."
    $hasOwnerRole = $false
    foreach ($item in $roleAssignments.value) {
        if ($item.properties.roleDefinitionId -eq $OWNER_ROLE_ID) {
            $hasOwnerRole = $true
        }
    }

    try {
        if (-not $hasOwnerRole) {
            Write-Host "Owner role does NOT exist. Assigning Owner Role..."
            
            # register provider
            $regProviderUri = "https://management.azure.com/providers/Microsoft.PortalServices/register?api-version=2024-03-01"
            try { 
                $providerRegistered = Invoke-RestMethod -Headers @{Authorization = "Bearer $armToken"} -Uri $regProviderUri -Method POST
                
                Start-Sleep -Seconds 3
            } catch {
                Write-Host "Failed to register PortalServices provider. Error: $($_.Exception.Message)" -ForegroundColor Red
                Write-Host
                throw "Provider registration failed"
            }

            # assign owner role
            $assignmentId = [guid]::NewGuid()
            $assignUri = "https://management.azure.com/providers/Microsoft.PortalServices/providers/Microsoft.Authorization/roleAssignments/$($assignmentId)?api-version=2020-04-01-preview"
            $assignBody = @{
                properties = @{
                    roleDefinitionId = $OWNER_ROLE_ID
                    principalId = $objectId
                    principalType = "User"
                    scope = "/providers/Microsoft.PortalServices"
                }
            } | ConvertTo-Json -Depth 5
            try {
                Invoke-RestMethod -Headers @{Authorization = "Bearer $armToken"; "Content-Type" = "application/json"} `
                    -Uri $assignUri -Method PUT -Body $assignBody
                Start-Sleep -Seconds 3
                Write-Host "Successfully assigned owner role." -ForegroundColor Green
            } catch {
                Write-Host "Failed to assign owner role. Error: $($_.Exception.Message)" -ForegroundColor Red
                Write-Host
                throw "Owner role assignment failed"
            }
        } else {
            Write-Host "Owner role already exists."
            Write-Host
        }

        # Update Tenant Settings
        Write-Host "Trying to postpone MFA enforcement..."
        $settingsUri = "https://management.azure.com/providers/Microsoft.PortalServices/settings/default?api-version=2024-09-01-preview"
        $settingsBody = @{
            properties = @{
                multiFactorAuthentication = @{
                    portalEnforcement = "OptOut"
                    portalJustification = "Postponed MFA by user with Powershell script"
                    portalEnforcementDate = $PostponementDateInUTC
                }
            }
        } | ConvertTo-Json -Depth 5

        $successfulUpdate = $false
        try {
            $updateResults = Invoke-WebRequest -Headers @{Authorization = "Bearer $armToken"; "Content-Type" = "application/json"} `
                -Uri $settingsUri -Method PUT -Body $settingsBody
                
            Start-Sleep -Seconds 3
        } catch {
            Write-Host "Failed to postpone MFA. Error: $($_.Exception.Message)" -ForegroundColor Red
            Write-Host
            throw "MFA postponement failed"
        }
        
        if ($updateResults.StatusCode -ge 200 -and $updateResults.StatusCode -lt 300) {
            # Convert content to JSON
            $jsonResponse = $updateResults.Content | ConvertFrom-Json

            # Check if provisioningState is 'Succeeded'
            if ($jsonResponse.properties.provisioningState -eq "Succeeded") {
                Write-Host "Successfully postponed MFA to $($PostponementDateInUTC)." -ForegroundColor Green
                Write-Host
                $successfulUpdate = $true
            } else {
                Write-Host "Provisioning state is not Succeeded. It is $($jsonResponse.properties.provisioningState)." -ForegroundColor Red
                Write-Host
                throw "MFA postponement failed - incorrect provisioning state"
            }
        } else {
            Write-Host "Request failed with status: $($updateResults.StatusCode)" -ForegroundColor Red
            Write-Host
            throw "MFA postponement failed - incorrect status code"
        }

        # Optional verification
        if ($successfulUpdate) {
            Write-Host "Verifying that postponement date was properly stored..."
            try {
                $verify = Invoke-RestMethod -Headers @{Authorization = "Bearer $armToken"} `
                    -Uri $settingsUri -Method GET
                
                Start-Sleep -Seconds 3

                Write-Host "The postponement date of '$($verify.properties.multiFactorAuthentication.portalEnforcementDate)' is set for tenant '$($TenantId)'" -ForegroundColor Green
                Write-Host
            } catch {
                Write-Host "Failed to fetch the stored postponement date. Error: $($_.Exception.Message)" -ForegroundColor Red
                Write-Host
                # Continue despite verification failure as update was successful
            }
        }
    }
    catch {
        Write-Host "An error occurred during the operation: $($_.Exception.Message)" -ForegroundColor Red
        Write-Host
    }
    finally {
        # Remove elevated access only if we were the ones that added it in the script.
        if (-not $alreadyHadElevatedStatus) {
            Remove-ElevatedAccess -objectId $objectId -TenantId $TenantId
        }
    }
}

function Remove-ElevatedAccess {
    param (
        [string]$objectId,
        [string]$TenantId
    )

    Write-Host "Removing temporary elevated access..."
    $roleCheckUri = "https://management.azure.com/providers/Microsoft.Authorization/roleAssignments?api-version=2022-04-01&`$filter=principalId+eq+'$($objectId)'"
    try {
        $roleAssignments = Invoke-RestMethod -Headers @{Authorization = "Bearer $armToken"} -Uri $roleCheckUri -Method GET
    } catch {
        Write-Host "Failed to fetch elevated access status. Error: $($_.Exception.Message)" -ForegroundColor Red
        Write-Host
        return
    }

    $newAssignmentId = $false
    foreach ($item in $roleAssignments.value) {
        if ($item.properties.roleDefinitionId -eq $ELEVATED_TENANT_ADMIN_ROLE_ID) {
            $newAssignmentId = $($item.name)
        }
    }

    if ($newAssignmentId -eq $false) {
        Write-Host "Could not find the elevated role assignment id. You will need to manually delete your elevated status.2" -ForegroundColor Red
        return
    }

    try {
        $connected = Connect-AzAccount -TenantId $TenantId
    } catch {
        Write-Host "Failed re-connect user. You will need to manually delete your elevated status. Error: $($_.Exception.Message)" -ForegroundColor Red
        Write-Host
        return
    }

    Write-Host "Refreshing authorization tokens..."
    Start-Sleep -Seconds 3
    try {
        $coreToken = Get-StringFromSecureString -secureString (Get-AzAccessToken -ResourceUrl "https://management.core.windows.net/").Token
        if ($null -eq $coreToken) {
            Write-Host "Failed to fetch an authorization token for Azure Resource Manager core. You will need to manually delete your elevated status." -ForegroundColor Red
            return
        }
    } catch {
        Write-Host "Failed to refresh authorization tokens. You will need to manually delete your elevated status. Error: $($_.Exception.Message)" -ForegroundColor Red
        Write-Host
        return
    }

    $retryCount = 0
    $maxRetries = 3

    do {
        $result = Delete-Elevated-Access -roleAssignmentId $newAssignmentId -coreToken $coreToken -retryCount $retryCount
        if ($result -eq $false) {
            $retryCount = $retryCount + 1
        } else {
            return
        }
    } while ($retryCount -lt $maxRetries)

    if ($retryCount -ge $maxRetries) {
        Write-Host "Failed to remove elevated access. You will need to manually delete your elevated status. " -ForegroundColor Red
        Write-Host
        return
    }
}

function Delete-Elevated-Access {
    param (
        [string]$roleAssignmentId,
        [string]$coreToken,
        [int]$retryCount
    )

    try {
        $deleteUri = "https://management.azure.com/providers/Microsoft.Authorization/roleAssignments/" + $roleAssignmentId + "?api-version=2018-07-01"
        # Attempt the API call and capture the response
        $response = Invoke-RestMethod -Headers @{Authorization = "Bearer $coreToken"} -Uri $deleteUri -Method DELETE -ErrorAction Stop

        # Even if there's no content in the response, the request could still have succeeded
        Write-Host "Successfully removed elevated access." -ForegroundColor Green
        Write-Host
        
        Start-Sleep -Seconds 3
        return $true
    } catch {
        Write-Host "(Attempt #$($retryCount)): Failed to remove elevated access. Error: $($_.Exception.Message)" -ForegroundColor Yellow
        Start-Sleep -Seconds 3
        return $false
    }
}

function Get-StringFromSecureString {
    param (
        [System.Security.SecureString] $secureString
    )
    
    $ptr = [System.Runtime.InteropServices.Marshal]::SecureStringToBSTR($secureString)
    $plainText = [System.Runtime.InteropServices.Marshal]::PtrToStringBSTR($ptr)
    [System.Runtime.InteropServices.Marshal]::ZeroFreeBSTR($ptr)
    
    return $plainText
}

function Decode-JwtPayload {
    param (
        [string]$Jwt
    )

    $parts = $Jwt -split '\.'
    if ($parts.Count -lt 2) {
        throw "Invalid JWT format"
    }

    $payload = $parts[1]

    # Replace URL-safe base64 chars
    $payload = $payload.Replace('-', '+').Replace('_', '/')

    # Add padding if needed
    switch ($payload.Length % 4) {
        2 { $payload += '==' }
        3 { $payload += '=' }
        1 { throw "Invalid base64url string" }
    }

    $json = [System.Text.Encoding]::UTF8.GetString([Convert]::FromBase64String($payload))
    return $json | ConvertFrom-Json
}

function Check-Date-Is-Valid {
    param (
        [DateTime]$maxDate,
        [DateTime]$minDate,
        [string]$dateToCheck
    )

    $inputDate = $maxDate
    if ([string]::IsNullOrWhiteSpace($dateToCheck)) {
        Write-Host "No input provided. Please enter a date in the required format.`n" -ForegroundColor Red
        return $valid
    }

    $parsed = [DateTime]::TryParse($dateToCheck, [ref]$inputDate)
    if (-not $parsed) {
        Write-Host "Invalid date format. Please try again using format like 2025-09-15T00:00:00Z.`n" -ForegroundColor Red
        return $valid
    }

    $inputDate = $inputDate.ToUniversalTime()
    if ($inputDate -ge $minDate -and $inputDate -le $maxDate) {
        return $inputDate
    } else {
        Write-Host "Date must be between $($minDate.ToString("u")) and $($maxDate.ToString("u")) (UTC). Try again.`n" -ForegroundColor Red
    }

    return $valid
}

function Select-Postponement-Date {
    $valid = $false
    $today = [DateTime]::UtcNow.Date
    $minDate = $today.AddDays(1)
    $maxDate = [DateTime]::ParseExact("2025-09-30T23:59:59Z", "yyyy-MM-ddTHH:mm:ssZ", $null).ToUniversalTime()

    $inputDate = $maxDate
    while (-not $valid) {
        $inputDateStr = Read-Host "Enter a UTC date up to 2025-09-30T23:59:59Z (e.g., 2025-09-15T00:00:00Z) or Enter to use the default" 
        $defaultChosen = $false
        if([string]::IsNullOrWhiteSpace($inputDateStr)) {
            $inputDateStr = "2025-09-30T23:59:59Z"
            $defaultChosen = $true
        }
        
        $inputDate = Check-Date-Is-Valid -maxDate $maxDate -minDate $minDate -dateToCheck $inputDateStr

        if (-not $inputDate) {
            $valid = $false
        } else {
            $valid = $true
            if ($defaultChosen) {
                Write-Host "You chose the enforcement date: $($inputDateStr)" -ForegroundColor Green
            } else {
                Write-Host "You entered the enforcement date: $($inputDateStr)" -ForegroundColor Green
            }
            Write-Host
        }
    }

    return $inputDate
}

# Call the function
Set-TenantSettingsMFAPostponement -TenantId $TenantId -PostponementDateInUTC $PostponementDateInUTC
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-authentication-methods-activity"} -->
## 認証方法アクティビティ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-methods-activity
- Service: entra-id / authentication
- Article date: 2025-10-22
- Summary: サインインしてパスワードをリセットするためにユーザーが登録する認証方法の概要。

新しい認証方法アクティビティのダッシュボードを使用すると、管理者が組織全体の認証方法の登録と使用を監視できます。 このレポート機能は、組織が登録中の方法や各方法の使用状況を確認する手段となるものです。

Note

個人データの表示または削除の詳細については、 [GDPR に対する Azure データ主体の要求に関するページを](https://learn.microsoft.com/ja-jp/microsoft-365/compliance/gdpr-dsr-azure)参照してください。 GDPR の詳細については、 [Microsoft セキュリティ センターの GDPR セクション](https://www.microsoft.com/trust-center/privacy/gdpr-overview) と [Service Trust ポータルの GDPR セクションを参照](https://servicetrust.microsoft.com/ViewPage/GDPRGetStarted)してください。

### アクセス許可とライセンス

以下のアクセス許可が付与されている組み込みロールとカスタム ロールを使用すると、[認証方法アクティビティ] ブレードと API にアクセスできます。

- Microsoft.directory/auditLogs/allProperties/read
- Microsoft.directory/signInReports/allProperties/read

以下のロールには、この必要なアクセス許可が付与されています。

- レポート閲覧者
- セキュリティ閲覧者
- グローバル閲覧者
- アプリケーション管理者
- クラウド アプリケーション管理者
- セキュリティ オペレーター
- セキュリティ管理者
- グローバル管理者

使用状況と分析情報にアクセスするには、Microsoft Entra ID P1 または P2 ライセンスが必要です。 Microsoft Entra 多要素認証とセルフサービス パスワード リセット (SSPR) のライセンス情報は、 [Microsoft Entra の価格サイト](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)にあります。

### しくみ

認証方法の使用状況と分析情報にアクセスするには:

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **[Entra ID]**&gt;**[認証 メソッド]**&gt;**[アクティビティ]** を参照します。
3. レポートには、 **登録** と使用状況という 2 つのタブ **があります**。

    [Image: 認証方法アクティビティの概要]

### 登録の詳細

[ **登録** ] タブにアクセスして、多要素認証、パスワードレス認証、セルフサービス パスワード リセットが可能なユーザーの数を表示できます。

次のいずれかのオプションをクリックして、ユーザー登録の詳細の一覧を事前にフィルター処理します。

- **Azure 多要素認証が可能なユーザー** には、両方のユーザーの内訳が表示されます。

    - 強力な認証方法に登録されている
    - MFA でその方法を使用するためのポリシーによって有効にされている

    この数には、Microsoft Entra ID 以外で MFA に登録されているユーザーは反映されません。
- **パスワードレス認証が可能なユーザー** は、FIDO2、Windows Hello for Business、または Microsoft Authenticator アプリでのパスワードレス電話サインインを使用して、パスワードなしでサインインするために登録されているユーザーの内訳を表示します。
- **セルフサービス パスワード リセットが可能なユーザー** には、パスワードをリセットできるユーザーの内訳が表示されます。 ユーザーが自分のパスワードをリセットできるのは、次の両方に当てはまる場合です。

    - セルフサービス パスワード リセットに関する組織のポリシーを満たすのに十分な方法で登録されている
    - パスワードのリセットが有効になっている

    [Image: 登録できるユーザーのスクリーンショット]

**認証方法によって登録されたユーザー** には、各認証方法に登録されているユーザーの数が表示されます。 認証方法をクリックすると、その方法に登録されているユーザーが表示されます。

[Image: 登録済みユーザーのスクリーンショット]

**認証方法による最近の登録** では、成功した登録と失敗した登録の数が、認証方法別に並べ替えられます。 認証方法をクリックすると、その方法の最近の登録イベントが表示されます。

[Image: 最近登録されたスクリーンショット]

### 使用状況の詳細

**使用状況**レポートには、サインインとパスワードのリセットに使用される認証方法が表示されます。

[Image: [使用状況] ページのスクリーンショット]

**認証要件によるサインイン** は、Microsoft Entra ID での単一要素認証と多要素認証に必要な成功したユーザー対話型サインインの数を示します。 サードパーティーの MFA プロバイダーによって MFA が適用されたサインインは含まれません。

[Image: 認証要件によるサインインのスクリーンショット]

**認証方法によるサインインは、使用された認証方法** によるユーザー対話型サインイン (成功と失敗) の数を示します。 トークン内の要求によって認証要件が満たされたサインインは含まれません。

[Image: メソッド別のサインインのスクリーンショット]

**パスワードのリセットとアカウントのロック解除の数** は、パスワードの変更とパスワードのリセット (セルフサービスと管理者による) の成功回数を示します。

[Image: リセットとロック解除のスクリーンショット]

**認証方法によるパスワード リセットは、認証方法によるパスワード リセット** フロー中の成功した認証と失敗した認証の数を示します。

[Image: メソッド別のリセットのスクリーンショット]

### ユーザー登録の詳細

一覧の一番上にあるコントロールを使うと、特定のユーザーを検索したり、表示されている列に基づいてユーザーの一覧を絞り込んだりできます。 レポートは、テナント内のほとんどのユーザーに対して 36 時間で更新されます。 まれに、少数のユーザーのレポートがその時間範囲から外れる可能性があります。 その場合は、24 時間後にレポートを見直してください。

Note

最近削除されたユーザー アカウント ( [論理的に削除されたユーザー](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-restore)とも呼ばれます) は、ユーザー登録の詳細には表示されません。 障害のあるユーザーの場合も同じです。

登録の詳細レポートには、ユーザーごとに次の情報が表示されます。

- ユーザー プリンシパル名
- 名前
- MFA 対応 (対応、非対応)
- パスワードレス対応 (対応、非対応)
- SSPR 登録済み (登録済み、未登録)
- SSPR 有効 (有効、有効でない)
- SSPR 対応 (対応、非対応)
- 登録されている方法 (代替携帯電話、資格情報ベースの認証、Email、FIDO2 セキュリティ キー、ハードウェア OATH トークン、Microsoft Authenticator アプリ、Microsoft パスワードレスの電話によるサインイン、携帯電話、会社の電話、セキュリティの質問、ソフトウェア OATH トークン、一時アクセス パス、Windows Hello for Business)
- 最終更新日時 (レポートが最後に更新された日時。この値は、ユーザーの認証方法の登録とは関係ありません)

    [Image: ユーザー登録の詳細のスクリーンショット]

### 登録とリセットのイベント

**登録イベントとリセット イベント** には、過去 24 時間、過去 7 日間、または過去 30 日間の登録イベントとリセット イベントが表示されます。

- 日付
- ユーザー名
- ユーザー
- 特徴 (登録、リセット)
- 使用された方法 (アプリ通知、アプリ コード、電話、会社電話、代替携帯電話による通話、SMS、メール、セキュリティの質問)
- 状態 (成功、失敗)
- 失敗の理由 (説明)

    [Image: 登録イベントとリセット イベントのスクリーンショット]

### 制限事項

- レポート内のデータはリアルタイムで更新されず、最大 36 時間の待機時間が反映される場合があります。 まれに、少数のユーザーのレポートがその時間範囲から外れる可能性があります。 その場合は、24 時間後にレポートを再確認します。
- ユーザーが構成した可能性がある **PhoneAppNotification** または **PhoneAppOTP** メソッドは、 **Microsoft Entra 認証方法 (ポリシー**) のダッシュボードに表示されません。
- Microsoft Entra 管理ポータルでの一括操作は、非常に大規模なテナントではタイムアウトして失敗する可能性があります。 この制限はスケーリングの制限による既知の問題です。 詳細については、「 [一括操作](https://learn.microsoft.com/ja-jp/entra/fundamentals/bulk-operations-service-limitations?WT.mc_id=Portal-Microsoft_AAD_IAM)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-authentication-passwordless-faqs"} -->
## ハイブリッド FIDO2 セキュリティ キーの展開に関する FAQ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-faqs
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra ID を使用したパスワードレス ハイブリッド FIDO2 セキュリティ キー サインインについてよく寄せられる質問について説明します

この記事では、Microsoft Entra ハイブリッド参加済みデバイスのデプロイに関してよく寄せられる質問 (FAQ) と、オンプレミス リソースへのパスワードレス サインインについて説明します。 このパスワードレス機能を使用すると、FIDO2 セキュリティ キーを使用し、Microsoft Entra ハイブリッド参加デバイスに対して Windows 10 デバイスで Microsoft Entra 認証を有効にすることができます。 ユーザーは、FIDO2 キーなどの最新の資格情報を使用してデバイス上の Windows にサインインし、オンプレミス リソースへのシームレスなシングル サインオン (SSO) エクスペリエンスを使用して従来の Active Directory Domain Services (AD DS) ベースのリソースにアクセスできます。

ハイブリッド環境のユーザーに対して、次のシナリオがサポートされています。

- FIDO2 セキュリティ キーを使用して Microsoft Entra ハイブリッド参加済みデバイスにサインインし、オンプレミス リソースへの SSO アクセスを取得します。
- FIDO2 セキュリティ キーを使用して Microsoft Entra 参加済みデバイスにサインインし、オンプレミス リソースへの SSO アクセスを取得します。

FIDO2 のセキュリティ キーおよびオンプレミスのリソースへのハイブリッド アクセスの概要については、次の記事を参照してください。

- [パスワードレスの FIDO2 セキュリティ キー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key)
- [パスワードレスの Windows 10](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key-windows)
- [パスワードレスのオンプレミス](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key-on-premises)

### セキュリティ キー

- 自分の組織では、リソースにアクセスするために 2 要素認証が必要です。 この要件をサポートするにはどうすればよいですか。
- 準拠している FIDO2 セキュリティ キーはどこにありますか?
- セキュリティ キーを紛失した場合はどうすればよいですか?
- FIDO2 セキュリティ キーでデータはどのように保護されますか?
- FIDO2 セキュリティ キーの登録のしくみ
- 管理者がユーザーのキーを直接プロビジョニングする方法はありますか?

#### 自分の組織では、リソースにアクセスするために多要素認証が必要です。 この要件をサポートするにはどうすればよいですか。

FIDO2 セキュリティ キーには、さまざまなフォーム ファクターがあります。 デバイスの製造元に問い合わせて、PIN または生体認証を 2 番目の要素としてデバイスを有効にする方法について説明します。 サポートされているプロバイダーの一覧については、「 [FIDO2 セキュリティ キー プロバイダー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)」を参照してください。

#### 準拠している FIDO2 セキュリティ キーはどこにありますか?

サポートされているプロバイダーの一覧については、「 [FIDO2 セキュリティ キー プロバイダー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)」を参照してください。

#### セキュリティ キーを紛失した場合はどうすればよいですか?

キーを削除するには、[ **セキュリティ情報** ] ページに移動し、FIDO2 セキュリティ キーを削除します。

#### FIDO2 セキュリティ キーでデータはどのように保護されますか?

FIDO2 セキュリティ キーには、それらに格納されている秘密キーを保護するセキュリティで保護されたエンクレーブがあります。 FIDO2 セキュリティ キーには、Windows Hello のように、秘密キーを抽出できないハンマリング防止プロパティも組み込まれています。

#### FIDO2 セキュリティ キーの登録のしくみ

FIDO2 セキュリティ キーを登録して使用する方法の詳細については、「 [パスワードレス セキュリティ キーのサインインを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key)」を参照してください。

#### 管理者がユーザーのキーを直接プロビジョニングする方法はありますか?

現時点ではできません。

#### FIDO2 キーを登録するときに、ブラウザーで "NotAllowedError" が表示されるのはなぜですか?

fido2 キー登録ページから "NotAllowedError" を受け取ります。 これは通常、Windows がセキュリティ キーに対して CTAP2 authenticatorMakeCredential 操作を試行しているときにエラーが発生した場合に発生します。 詳細については、Microsoft-Windows-WebAuthN/Operational イベント ログを参照してください。

### [前提条件]

- インターネットに接続できない場合、この機能は機能しますか?
- Microsoft Entra ID を開くために必要な特定のエンドポイントは何ですか?
- Windows 10 デバイスのドメイン参加の種類 (Microsoft Entra 参加済みまたは Microsoft Entra ハイブリッド参加済み) を特定するにはどうすればよいですか?
- 修正プログラムを適用する必要がある DC の数に関する推奨事項は何ですか?
- オンプレミスのみのデバイスに FIDO2 資格情報プロバイダーをデプロイできますか?
- FIDO2 セキュリティ キーのサインインが、ドメイン管理者またはその他の高い特権アカウントで機能していません。 なぜでしょうか。

#### インターネットに接続できない場合、この機能は機能しますか?

インターネット接続は、この機能を有効にするための前提条件です。 ユーザーが初めて FIDO2 セキュリティ キーを使用してサインインするときは、インターネットに接続する必要があります。 後続のサインイン イベントでは、キャッシュされたサインインが機能し、ユーザーがインターネットに接続せずに認証できるようにする必要があります。

一貫性のあるエクスペリエンスを実現するには、デバイスがインターネットにアクセスでき、DCが視界に入ることを確認してください。

#### Microsoft Entra ID を開くために必要な特定のエンドポイントは何ですか?

登録と認証には、次のエンドポイントが必要です。

- `*.microsoftonline.com`
- `*.microsoftonline-p.com`
- `*.msauth.net`
- `*.msauthimages.net`
- `*.msecnd.net`
- `*.msftauth.net`
- `*.msftauthimages.net`
- `*.phonefactor.net`
- `enterpriseregistration.windows.net`
- `management.azure.com`
- `policykeyservice.dc.ad.msft.net`
- `secure.aadcdn.microsoftonline-p.com`

Microsoft オンライン製品を使用するために必要なエンドポイントの完全な一覧については、 [Office 365 の URL と IP アドレス範囲に関するページを](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/urls-and-ip-address-ranges)参照してください。

#### Windows 10 デバイスのドメイン参加の種類 (Microsoft Entra 参加済みまたは Microsoft Entra ハイブリッド参加済み) を特定するにはどうすればよいですか?

Windows 10 クライアント デバイスに適切なドメイン参加の種類があるかどうかを確認するには、次のコマンドを使用します。

```console
Dsregcmd /status
```

次の出力例は、 *AzureADJoined* が *YES* に設定されているため、デバイスが Microsoft Entra 参加済みであることを示しています。

```output
+---------------------+
| Device State        |
+---------------------+

AzureADJoined: YES
EnterpriseJoined: NO
DomainedJoined: NO
```

次の出力例は、デバイスが Microsoft Entra ハイブリッド参加済みであることを示 *しています。DomainedJoined* も *YES* に設定されています。 *DomainName* も表示されます。

```output
+---------------------+
| Device State        |
+---------------------+

AzureADJoined: YES
EnterpriseJoined: NO
DomainedJoined: YES
DomainName: CONTOSO
```

Windows Server 2016 または 2019 ドメイン コントローラーで、次のパッチが適用されていることを確認します。 必要に応じて、Windows Update を実行してインストールします。

- Windows Server 2016 - [KB4534307](https://support.microsoft.com/help/4534307/windows-10-update-kb4534307)
- Windows Server 2019 - [KB4534321](https://support.microsoft.com/help/4534321/windows-10-update-kb4534321)

クライアント デバイスから、次のコマンドを実行して、パッチがインストールされている適切なドメイン コントローラーへの接続を確認します。

```console
nltest /dsgetdc:<domain> /keylist /kdc
```

#### 修正プログラムを適用する必要がある DC の数に関する推奨事項は何ですか?

組織の認証要求の負荷を確実に処理できるように、Windows Server 2016 または 2019 ドメイン コントローラーの大部分にパッチを適用することをお勧めします。

Windows Server 2016 または 2019 ドメイン コントローラーで、次のパッチが適用されていることを確認します。 必要に応じて、Windows Update を実行してインストールします。

- Windows Server 2016 - [KB4534307](https://support.microsoft.com/help/4534307/windows-10-update-kb4534307)
- Windows Server 2019 - [KB4534321](https://support.microsoft.com/help/4534321/windows-10-update-kb4534321)

#### オンプレミスのみのデバイスに FIDO2 資格情報プロバイダーをデプロイできますか?

いいえ。この機能は、オンプレミスのみのデバイスではサポートされていません。 FIDO2 資格情報プロバイダーは表示されません。

#### FIDO2 セキュリティ キーのサインインが、ドメイン管理者またはその他の高い特権アカウントで機能していません。 なぜでしょうか。

既定のセキュリティ ポリシーでは、オンプレミスのリソースに対して高い特権アカウントに署名するためのアクセス許可が Microsoft Entra に付与されません。

Microsoft Entra ID から Active Directory への攻撃ベクトルの可能性があるため、コンピューター オブジェクト CN=AzureADKerberos、OU=Domain Controllers、&lt;domain-DN&gt; のパスワード レプリケーション ポリシーを緩和して、これらのアカウントのブロックを解除することはお勧めしません。

### しくみ

- Microsoft Entra Kerberos は、オンプレミスの Active Directory Domain Services 環境にどのようにリンクされていますか?
- AD で作成され、Microsoft Entra ID で公開されている Kerberos サーバー オブジェクトはどこで表示できますか?
- インターネットに依存しないように、オンプレミスの AD DS に公開キーを登録できないのはなぜですか?
- Kerberos サーバー オブジェクトでキーはどのようにローテーションされますか?
- Microsoft Entra Connect が必要な理由 Microsoft Entra ID から AD DS に情報が書き戻されますか?
- PRT+ 部分 TGT を要求すると、HTTP 要求/応答はどのように表示されますか?

#### Microsoft Entra Kerberos は、オンプレミスの Active Directory Domain Services 環境にどのようにリンクされていますか?

オンプレミスの AD DS 環境と Microsoft Entra テナントの 2 つの部分があります。

**Active Directory Domain Services (AD DS)**

Microsoft Entra Kerberos サーバーは、オンプレミスの AD DS 環境でドメイン コントローラー (DC) オブジェクトとして表されます。 この DC オブジェクトは、複数のオブジェクトで構成されます。

- `CN=AzureADKerberos,OU=Domain Controllers,<domain-DN>`

    AD DS の Read-Only ドメイン コントローラー (RODC) を表す *Computer* オブジェクト。 このオブジェクトに関連付けられているコンピューターはありません。 むしろ、これは DC の論理的な表現です。
- `CN=krbtgt_AzureAD,CN=Users,<domain-DN>`

    RODC Kerberos チケット許可チケット (TGT) 暗号化キーを表す *User* オブジェクト。
- `CN=900274c4-b7d2-43c8-90ee-00a9f650e335,CN=AzureAD,CN=System,<domain-DN>`

    Microsoft Entra Kerberos サーバー オブジェクトに関するメタデータを格納する *ServiceConnectionPoint* オブジェクト。 管理ツールは、このオブジェクトを使用して、Microsoft Entra Kerberos サーバー オブジェクトを識別して見つけます。

**Microsoft Entra ID**

Microsoft Entra Kerberos サーバーは、 *KerberosDomain* オブジェクトとして Microsoft Entra ID で表されます。 各オンプレミス AD DS 環境は、Microsoft Entra テナント内の 1 つの *KerberosDomain* オブジェクトとして表されます。

たとえば、 `contoso.com` と `fabrikam.com`などの 2 つのドメインを持つ AD DS フォレストがあるとします。 Microsoft Entra ID がフォレスト全体に対して Kerberos チケット許可チケット (TGT) を発行できるようにする場合、Microsoft Entra ID には 2 つの `KerberosDomain` オブジェクト ( `contoso.com` 用のオブジェクトと `fabrikam.com`用のオブジェクト) があります。

複数の AD DS フォレストがある場合は、各フォレスト内のドメインごとに 1 つの `KerberosDomain` オブジェクトがあります。

#### AD DS で作成され、Microsoft Entra ID で発行された Kerberos サーバー オブジェクトはどこで表示できますか?

すべてのオブジェクトを表示するには、Microsoft Entra Connect の最新バージョンに含まれている Microsoft Entra Kerberos サーバー PowerShell コマンドレットを使用します。

オブジェクトを表示する方法の手順など、詳細については、 [Kerberos Server オブジェクトの作成を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key-on-premises#create-a-kerberos-server-object)参照してください。

#### インターネットに依存しないように、オンプレミスの AD DS に公開キーを登録できないのはなぜですか?

Windows Hello for Business の展開モデルの複雑さに関するフィードバックを受け取りました。そのため、証明書と PKI を使用せずにデプロイ モデルを簡略化したいと考えていました (FIDO2 では証明書は使用されません)。

#### Kerberos サーバー オブジェクトでキーはどのようにローテーションされますか?

他の DC と同様に、Microsoft Entra Kerberos サーバー暗号化 *krbtgt* キーは定期的にローテーションする必要があります。 他のすべての AD DS *krbtgt* キーをローテーションする場合と同じスケジュールに従うことをお勧めします。

注

*krbtgt* キーをローテーションするツールは他にもありますが、PowerShell コマンドレットを使用して Microsoft Entra Kerberos サーバーの [*krbtgt* キーをローテーション](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key-on-premises#rotate-the-azure-ad-kerberos-server-key)する必要があります。 このメソッドにより、オンプレミスの AD DS 環境と Microsoft Entra ID の両方でキーが更新されます。

#### Microsoft Entra Connect が必要な理由 Microsoft Entra ID から AD DS に情報が書き戻されますか?

Microsoft Entra Connect は、Microsoft Entra ID から Active Directory DS に情報を書き戻しません。 このユーティリティには、AD DS で Kerberos サーバー オブジェクトを作成し、Microsoft Entra ID で発行するための PowerShell モジュールが含まれています。

#### PRT+ 部分 TGT を要求すると、HTTP 要求/応答はどのように表示されますか?

HTTP 要求は、標準のプライマリ更新トークン (PRT) 要求です。 この PRT 要求には、Kerberos チケット許可チケット (TGT) が必要であることを示す要求が含まれています。

| 主張 | 価値 | 説明 |
| --- | --- | --- |
| tgt | ほんとう | クライアントが TGT を必要としていることを請求は示しています。 |

Microsoft Entra ID は、暗号化されたクライアント キーとメッセージ バッファーを追加のプロパティとして PRT 応答に結合します。 ペイロードは、Microsoft Entra Device セッション キーを使用して暗号化されます。

| フィールド | タイプ | 説明 |
| --- | --- | --- |
| tgt\_client\_key | 文字列 | Base64 でエンコードされたクライアント キー (シークレット)。 このキーは、TGT を保護するために使用されるクライアント シークレットです。 このパスワードなしのシナリオでは、クライアント シークレットは各 TGT 要求の一部としてサーバーによって生成され、応答でクライアントに返されます。 |
| tgt\_key\_type | 整数 (int) | KERB\_MESSAGE\_BUFFERに含まれるクライアント キーと Kerberos セッション キーの両方に使用されるオンプレミスの AD DS キーの種類。 |
| tgt\_message\_buffer | 文字列 | Base64 でエンコードされたKERB\_MESSAGE\_BUFFER。 |

#### ユーザーはドメイン ユーザー Active Directory グループのメンバーである必要がありますか?

はい。 Microsoft Entra Kerberos を使用してサインインできるようにするには、ユーザーがドメイン ユーザー グループに含まれている必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-authentication-passwordless-phone"} -->
## Authenticator を使用したパスワードなしのサインイン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-phone
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Authenticator を使用して Microsoft Entra ID へのパスワードなしのサインインを有効にする方法について説明します。

Authenticator は、パスワードを使用せずに Microsoft Entra アカウントにサインインするために使用されます。 Authenticator は、キーベースの認証を使用して、デバイスに関連付けられているユーザー資格情報を有効にします。この場合、デバイスは PIN または生体認証を使用します。 [Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-identity-verification) でも同様のテクノロジが使用されます。

認証テクノロジは、モバイルを含む任意のデバイス プラットフォームで使用できます。 Authenticator は、iOS または Android で実行できます。

[Image: ユーザーにサインインの承認を求めるブラウザー サインインの例を示すスクリーンショット。]

Authenticator からの電話によるサインインには、アプリ内の番号をタップするようにユーザーに求めるメッセージが表示されます。 ユーザー名やパスワードは要求されません。 アプリでサインイン プロセスを完了するには、次の手順に従います。

1. [Authenticator]\(認証\) ダイアログで、サインイン画面に表示される番号を入力します。
2. [ **承認] を選択します**。
3. PIN または生体認証を提供します。

### 複数のアカウント

サポートされている任意の Android または iOS デバイスの Authenticator で、複数のアカウントに対してパスワードなしの電話によるサインインを有効にすることができます。 Microsoft Entra ID に複数のアカウントを持つコンサルタント、学生、その他のユーザーは、Authenticator に各アカウントを追加し、同じデバイスからすべてのユーザーにパスワードなしの電話サインインを使用できます。

Microsoft Entra アカウントの所属先は同じテナントでも異なるテナントでもかまいません。 1 台のデバイスからの複数アカウント サインインは、ゲスト アカウントではサポートされません。

### 前提条件

Authenticator でパスワードなしの電話によるサインインを使用するには、次の前提条件を満たす必要があります。

- 推奨: Microsoft Entra 多要素認証 (MFA) と、検証方法として許可されるプッシュ通知。 ユーザーのスマートフォンまたはタブレットにプッシュ通知を送信すると、Authenticator アプリがアカウントへの不正アクセスを防ぎ、不正なトランザクションを停止できます。 Authenticator アプリは、プッシュ通知を行うように設定すると、自動的にコードが生成されます。 デバイスが接続されない場合でも、ユーザーにはバックアップのサインイン方法があります。
- サインインに使用される各テナントにデバイスを登録する必要があります。 たとえば、すべてのアカウントがサインインできるようにするには、次のデバイスを Contoso と Wingtip Toys に登録する必要があります。

    - balas@contoso.com
    - balas@wingtiptoys.com および bsandhu@wingtiptoys

Microsoft Entra ID でパスワードレス認証を使用するには、最初に統合された登録エクスペリエンスを有効にしてから、パスワードなしの方法でユーザーを有効にします。

### パスワードなしの電話によるサインインの認証方法を有効にする

Microsoft Entra ID を使用すると、 [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) は、サインインに使用できる認証方法を選択できます。 認証方法ポリシーで **Microsoft Authenticator** を有効にして、従来のプッシュ MFA メソッドとパスワードレス認証方法の両方を管理できます。

認証方法として Microsoft Authenticator有効にすると、ユーザーは セキュリティ情報 に移動して、サインインする方法として Authenticator を登録できます。 **Microsoft Authenticator** は、 **セキュリティ情報**の方法として一覧表示されます。 たとえば、有効と登録内容に応じて、 **Microsoft Authenticator-Passwordless** または **Microsoft Authenticator-MFA Push** が表示されます。

パスワードレス電話によるサインインの認証方法を有効にするには、次の手順に従います。

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **Entra ID**&gt;**認証メソッド**&gt;**ポリシー** に移動します。

各グループは、既定で **Any** モードを使用するように有効になっています。 **どのモードでも** 、グループ メンバーはプッシュ通知またはパスワードなしの電話によるサインインでサインインできます。

注意

保存しようとするとエラーが表示される場合は、追加されるユーザーまたはグループの数が原因である可能性があります。 回避策として、追加しようとしているユーザーとグループを同じ操作の 1 つのグループに置き換えます。 次に、[ **保存]** をもう一度選択します。

### ユーザーの登録

ユーザーは、Microsoft Entra ID のパスワードなしの認証方法に登録します。 MFA 用に Authenticator アプリを既に登録しているユーザーは、次のセクションに進み、電話によるサインインを有効にする 。

#### 電話による直接サインインの登録

ユーザーは、パスワードなしの電話によるサインインに直接 Authenticator アプリ内で登録できます。ただし、最初に Authenticator を自分のアカウントに登録する必要はありません。ただし、パスワードは発生しません。 その方法は次のとおりです。

1. 管理者または組織から [一時アクセス パス](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass) を取得します。
2. Authenticator アプリをダウンロードしてモバイル デバイスにインストールします。
3. Authenticator を開き、[アカウント追加] を選択し、職場または学校アカウント選択します。
4. [ **サインイン] を選択します**。
5. 手順に従って、管理者または組織から提供された一時アクセス パスを使用してアカウントでサインインします。
6. サインイン後、追加の手順に従って電話によるサインインを設定します。

#### マイ Sign-Ins へのガイド付き登録

注意

ユーザーは、Authenticator 認証モードが **[任意** ] または [ **プッシュ**] に設定されている場合にのみ、統合された登録を使用して Authenticator を登録できます。

Authenticator アプリを登録するには、次の手順に従います。

1. [\[セキュリティ情報](https://aka.ms/mysecurityinfo)] を参照します。
2. サインインし、[Authenticator アプリメソッド 追加][ を追加して Authenticator を追加する] を選択します。
3. 指示に従って、デバイスに Authenticator アプリをインストールして構成します。
4. [ **完了] を** 選択して、Authenticator の構成を完了します。

##### 電話によるサインインを有効にする

ユーザーが Authenticator アプリに登録したら、電話によるサインインを有効にする必要があります。

1. **Microsoft Authenticator** で、登録されているアカウントを選択します。
2. [ **パスワードレス サインイン要求の設定**] を選択します。
3. アプリの指示に従って、パスワードなしの電話によるサインインに対するアカウントの登録を完了します。

組織はユーザーに対して、パスワードを使用しないで、自分の電話でサインインするように指示することができます。 Authenticator の構成と電話によるサインインの有効化の詳細については、Authenticator アプリを使用したアカウントへのサインイン に関するページを参照してください。

注意

ポリシーによってユーザーが電話によるサインインを使用できないように制限されている場合、ユーザーは Authenticator 内でそれを有効にできません。

### パスワードなしの資格情報でサインインする

ユーザーは、以下の操作がすべて完了したら、パスワードレス サインインの利用を開始できます。

- 管理者がユーザーのテナントを有効にしました。
- ユーザーがサインイン方法として Authenticator を追加しました。

電話によるサインイン プロセスを初めて開始するには、次の手順に従います。

1. **[サインイン**] ウィンドウに自分の名前を入力します。
2. [ **次へ**] を選択します。
3. 必要に応じて、[ **その他のサインイン方法] を**選択します。
4. **[Authenticator アプリで要求を承認する**] を選択します。

その後、数値が表示されます。 アプリは、パスワードを入力するのではなく、適切な番号を入力してユーザーに認証を求めます。

ユーザーがパスワードなしの電話によるサインインを使用すると、アプリは引き続きこの方法でユーザーをガイドします。 ユーザーには、別の方法を選択するオプションも表示されます。

[Image: Authenticator アプリを使用したブラウザー サインインの例を示すスクリーンショット。]

##### 一時アクセス パス

テナント管理者が、ユーザーが一時アクセス パスを使用して Authenticator アプリで初めてパスワードなしのサインインを設定できるようにセルフサービス パスワード リセットを有効にした場合は、次の手順に従います。

1. モバイル デバイスまたはデスクトップでブラウザーを開き、[ [セキュリティ情報](https://aka.ms/mysecurityinfo)] に移動します。
2. Authenticator アプリをサインイン方法として登録します。 このアクションにより、アカウントがアプリにリンクされます。
3. モバイル デバイスに戻り、Authenticator アプリを使用してパスワードなしのサインインをアクティブ化します。

### 管理

Authenticator を管理する最善の方法として、認証方法ポリシーをお勧めします。 認証ポリシー管理者、このポリシーを編集して Authenticator を有効または無効にすることができます。 管理者は、特定のユーザーとグループを利用対象に含めたり除外したりできます。

管理者は、Authenticator の使用方法をより適切に制御するようにパラメーターを構成することもできます。 たとえば、ユーザーが承認する前にコンテキストを増やすことができるように、サインイン要求に場所またはアプリ名を追加できます。

### 既知の問題

次の既知の問題点があります。

#### パスワードなしの電話によるサインインのオプションが表示されない

1 つのシナリオでは、ユーザーが未応答のパスワードなしの電話によるサインイン検証を保留にしている可能性があります。 ユーザーがもう一度サインインしようとすると、パスワードを入力するオプションのみが表示されます。

このシナリオを解決するには、次の手順を実行します。

1. Authenticator を開きます。
2. 通知プロンプトに応答します。

次に、パスワードなしの電話によるサインインを引き続き使用します。

#### AuthenticatorAppSignInPolicy はサポートされていません

レガシ ポリシー `AuthenticatorAppSignInPolicy` は Authenticator ではサポートされていません。 Authenticator アプリでユーザーがプッシュ通知またはパスワードなしの電話によるサインインを有効にするには、 [認証方法ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-methods-manage)を使用します。

#### フェデレーション アカウント

ユーザーがパスワードなしの資格情報を有効にすると、Microsoft Entra サインイン プロセスは `login\_hint`の使用を停止します。 このプロセスは、ユーザーをフェデレーション サインインの場所に向けて加速させなくなりました。

通常、このロジックにより、ハイブリッド テナント内のユーザーがサインイン検証のために Active Directory フェデレーション サービスに誘導されるのを防ぐことができます。 [ **代わりにパスワードを使用** する] を選択するオプションは引き続き使用できます。

#### オンプレミスのユーザー

管理者は、オンプレミスの ID プロバイダーを介して MFA のユーザーを有効にすることができます。 ユーザーは、パスワードなしの電話によるサインイン資格情報を 1 つ作成して使用できます。

ユーザーがパスワードなしの電話によるサインイン資格情報を使用して Authenticator の複数のインストール (5 以降) をアップグレードしようとすると、この変更によってエラーが発生する可能性があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-authentication-passwordless-security-key-on-premises"} -->
## オンプレミスのリソースへのパスワードなしのセキュリティ キー サインイン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key-on-premises
- Service: entra-id / authentication
- Article date: 2025-07-13
- Summary: Microsoft Entra ID を使用して、オンプレミスのリソースへのパスワードレス セキュリティ キー サインインを有効にする方法について説明します

このトピックでは、Windows 10 バージョン 2004 以降を実行するデバイスを備えた環境で、オンプレミス リソースのパスワードレス認証を有効にする方法について説明します。 デバイスは、"Microsoft Entra 参加済み" または "Microsoft Entra ハイブリッド参加済み" であることができます。 このパスワードレスの認証機能により、Microsoft 互換のセキュリティ キーを使う場合、または [Windows Hello for Business クラウドの信頼](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-hybrid-cloud-kerberos-trust)を使う場合に、オンプレミス リソースへのシームレスなシングル サインオン (SSO) が提供されます。

### SSO を使用して FIDO2 キーでオンプレミスのリソースにサインインする

Microsoft Entra ID では、1 つ以上の Active Directory ドメインの Kerberos Ticket Granting Ticket (TGT) を発行できます。 この機能により、ユーザーは FIDO2 セキュリティ キーなどの最新の資格情報で Windows にサインインし、従来の Active Directory ベースのリソースにアクセスできます。 Kerberos サービス チケットと認可は、引き続きオンプレミスの Active Directory ドメイン コントローラー (DC) によって制御されます。

Microsoft Entra Kerberos サーバー オブジェクトがオンプレミスの Active Directory インスタンスに作成され、Microsoft Entra Connect を使用して Microsoft Entra ID に安全に発行されます。 このオブジェクトは、どの物理サーバーにも関連付けられていません。 これは単に、Active Directory ドメインの Kerberos TGT を生成するために Microsoft Entra ID で使用できるリソースです。

[Image: Microsoft Entra ID と Active Directory Domain Services から TGT を取得する方法を示す図。]

1. ユーザーは、FIDO2 セキュリティ キーを使用して Windows 10 デバイスにサインインし、Microsoft Entra ID に対して認証を行います。
2. Microsoft Entra ID によって、ユーザーのオンプレミスの Active Directory ドメインと一致する Kerberos サーバー キーがディレクトリにあるかどうかが確認されます。

    Microsoft Entra ID によって、ユーザーのオンプレミスの Active Directory ドメインの Kerberos TGT が生成されます。 TGT にはユーザーの SID のみが含まれ、認可データは含まれていません。
3. ユーザーの Microsoft Entra プライマリ更新トークン (PRT) と共に、TGT がクライアントに返されます。
4. クライアント マシンは、オンプレミスの Active Directory ドメイン コントローラーに接続し、部分的な TGT と引き換えに完全な形式の TGT を入手します。
5. この時点でクライアント マシンには Microsoft Entra PRT と完全な Active Directory TGT があり、クラウドとオンプレミスの両方のリソースにアクセスできます。

### 前提条件

この記事の手順を開始する前に、組織で、「[組織のパスキー (FIDO2) を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key)」の手順を完了する必要があります。

また、次のシステム要件を満たしている必要があります。

- デバイスでは、Windows 10 バージョン 2004 以降を実行している必要があります。
- Windows Server ドメイン コントローラーは、Windows Server 2016 以降を実行し、次のサーバー用のパッチがインストールされている必要があります。

    - [Windows Server 2016](https://support.microsoft.com/help/4534307/windows-10-update-kb4534307)
    - [Windows Server 2019](https://support.microsoft.com/help/4534321/windows-10-update-kb4534321)
- **[ネットワーク セキュリティ: Kerberos で許可する暗号化の種類を構成する]** ポリシーをドメイン コントローラーで[構成](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/security-policy-settings/network-security-configure-encryption-types-allowed-for-kerberos)する場合は、AES256\_HMAC\_SHA1 を有効にする必要があります。
- シナリオの手順を完了するために必要な、次の資格情報を用意します。

    - ドメインの Domain Admins グループのメンバーであり、フォレストの Enterprise Admins グループのメンバーである Active Directory ユーザー。 以下、**$domainCred** と表記します。
    - [ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)ロールを持つ Microsoft Entra ユーザー。 以下、**$cloudCred** と表記します。
- ユーザーは、Microsoft Entra Connect を使用して、次の Microsoft Entra 属性を設定する必要があります。

    - `onPremisesSamAccountName` (Microsoft Entra Connect の `accountName`)
    - `onPremisesDomainName` (Microsoft Entra Connect の `domainFQDN`)
    - `onPremisesSecurityIdentifier` (Microsoft Entra Connect の `objectSID`)

    Microsoft Entra Connect では既定でこれらの属性が同期されます。 同期する属性を変更する場合は、同期のために `accountName`、`domainFQDN`、および `objectSID` を選ぶようにしてください。

#### サポートされるシナリオ

この記事のシナリオでは、次の両方のインスタンスで SSO がサポートされています。

- Microsoft 365 や他の Security Assertion Markup Language (SAML) 対応アプリケーションなどのクラウド リソース。
- オンプレミスのリソースと、Web サイトに対する Windows 統合認証。 リソースには、IIS 認証を必要とする Web サイトおよび SharePoint サイトや、NTLM 認証を使用するリソースを含めることができます。

#### サポートされていないシナリオ

以下のシナリオはサポートされていません。

- Windows Server Active Directory Domain Services (AD DS) 参加済みの (オンプレミス専用デバイスの) デプロイ。
- セキュリティ キーを使用したリモート デスクトップ プロトコル (RDP)、仮想デスクトップ インフラストラクチャ (VDI)、Citrix のシナリオ。
- セキュリティ キーを使用した S/MIME。
- セキュリティ キーを使用して *[別のユーザーとして実行]*。
- セキュリティ キーを使用したサーバーへのログイン。

### AzureADHybridAuthenticationManagement モジュールをインストールする

[`AzureADHybridAuthenticationManagement` モジュール](https://www.powershellgallery.com/packages/AzureADHybridAuthenticationManagement)では、管理者に FIDO2 管理機能が提供されます。

1. [管理者として実行] オプションを使用して、PowerShell プロンプトを開きます。
2. `AzureADHybridAuthenticationManagement` モジュールをインストールします。

    ```powershell
    # First, ensure TLS 1.2 for PowerShell gallery access.
    [Net.ServicePointManager]::SecurityProtocol = [Net.ServicePointManager]::SecurityProtocol -bor [Net.SecurityProtocolType]::Tls12
    
    # Install the AzureADHybridAuthenticationManagement PowerShell module.
    Install-Module -Name AzureADHybridAuthenticationManagement -AllowClobber
    ```

注記

- 更新プログラム 2.3.331.0 以降、AzureADHybridAuthenticationManagement モジュールは AzureADPreview モジュールをインストールしません。
- `AzureADHybridAuthenticationManagement` モジュールは、Microsoft Entra Connect ソリューションに依存せずに、オンプレミスの Active Directory ドメイン コントローラーにアクセスできる任意のコンピューターにインストールできます。
- `AzureADHybridAuthenticationManagement` モジュールは、[PowerShell ギャラリー](https://www.powershellgallery.com/)を介して配布されます。 PowerShell ギャラリーは、PowerShell コンテンツの中央リポジトリです。 ここには、PowerShell コマンドと Desired State Configuration (DSC) リソースを含む、便利な PowerShell モジュールがあります。

### Kerberos サーバー オブジェクトを作成する

管理者は、`AzureADHybridAuthenticationManagement` モジュールを使用して、オンプレミスのディレクトリに Microsoft Entra Kerberos サーバー オブジェクトを作成します。 このオブジェクトは、Microsoft Entra Connect サーバー上、または Microsoft.Online.PasswordSynchronization.Rpc.dll 依存関係がインストールされているサーバー上で、作成される必要があります。

Microsoft Entra ユーザーを含む組織内の各ドメインおよびフォレストで、次の手順を実行してください。

1. [管理者として実行] オプションを使用して、PowerShell プロンプトを開きます。
2. 次の PowerShell コマンドを実行して、オンプレミスの Active Directory ドメインと Microsoft Entra テナントの両方に新しい Microsoft Entra Kerberos サーバー オブジェクトを作成します。

#### Azure クラウドの選択 (既定値は Azure Commercial)

既定では、`Set-AzureADKerberosServer` コマンドレットには商用クラウド エンドポイントを使用します。 別のクラウド環境で Kerberos を構成している場合は、指定されたクラウドを使用するようにコマンドレットを設定する必要があります。

使用できるクラウドの**一覧**と変更が必要な数値を取得するには、次のコマンドを実行します。`Get-AzureADKerberosServerEndpoint`

出力例:

```Console
Current Endpoint = 0(Public)
Supported Endpoints:
   0 :Public
   1 :China
   2 :Us Government
```

目的のクラウド環境の横にある**数値**に注意してください。

次に、目的のクラウド環境を**設定**するには、次を実行します。

"(例: 米国政府のクラウドの場合)"

`Set-AzureADKerberosServerEndpoint -TargetEndpoint 2`

ヒント

Azure Commercial がソブリン クラウドを比較する方法の詳細については、「 [Azure Commercial クラウドと Azure ソブリン クラウドの違い](https://aka.ms/SovCC)」を参照してください。

#### すべての資格情報を要求すプロンプト例1

```powershell
# Specify the on-premises Active Directory domain. A new Microsoft Entra ID
# Kerberos Server object will be created in this Active Directory domain.
$domain = $env:USERDNSDOMAIN

# Enter an Azure Active Directory Hybrid Identity Administrator username and password.
$cloudCred = Get-Credential -Message 'An Active Directory user who is a member of the Hybrid Identity Administrators group for Microsoft Entra ID.'

# Enter a Domain Administrator username and password.
$domainCred = Get-Credential -Message 'An Active Directory user who is a member of the Domain Admins group and an Enterprise Admin for the forest.'

# Create the new Microsoft Entra ID Kerberos Server object in Active Directory
# and then publish it to Azure Active Directory.
Set-AzureADKerberosServer -Domain $domain -CloudCredential $cloudCred -DomainCredential $domainCred
```

#### クラウドの資格情報を要求すプロンプト例2

注記

ドメイン管理者特権があるアカウントでドメイン参加済みマシンを操作している場合は、"-DomainCredential" パラメーターを省略できます。 "-DomainCredential" パラメーターが指定されていない場合、オンプレミスの Active Directory ドメイン コントローラーにアクセスする際に、現在の Windows ログイン資格情報が使用されます。

```powershell
# Specify the on-premises Active Directory domain. A new Microsoft Entra ID
# Kerberos Server object will be created in this Active Directory domain.
$domain = $env:USERDNSDOMAIN

# Enter an Azure Active Directory Hybrid Identity Administrator username and password.
$cloudCred = Get-Credential

# Create the new Microsoft Entra ID Kerberos Server object in Active Directory
# and then publish it to Azure Active Directory.
# Use the current windows login credential to access the on-premises AD.
Set-AzureADKerberosServer -Domain $domain -CloudCredential $cloudCred
```

#### 例3先進認証を使用してすべての資格情報を要求するプロンプト

注記

組織でパスワードベースのサインインを保護し、多要素認証、FIDO2、スマート カード テクノロジなどの最新の認証方法を適用している場合は、`-UserPrincipalName` パラメーターにハイブリッド ID 管理者のユーザー プリンシパル名 (UPN) を指定する必要があります。

- 次の例の `contoso.corp.com` を、オンプレミスの Active Directory ドメイン名に置き換えます。
- 次の例の `administrator@contoso.onmicrosoft.com` を、ハイブリッド ID 管理者の UPN に置き換えます。

```powershell
# Specify the on-premises Active Directory domain. A new Microsoft Entra ID
# Kerberos Server object will be created in this Active Directory domain.
$domain = $env:USERDNSDOMAIN

# Enter a UPN of a Hybrid Identity Administrator
$userPrincipalName = "administrator@contoso.onmicrosoft.com"

# Enter a Domain Administrator username and password.
$domainCred = Get-Credential

# Create the new Microsoft Entra ID Kerberos Server object in Active Directory
# and then publish it to Azure Active Directory.
# Open an interactive sign-in prompt with given username to access the Microsoft Entra ID.
Set-AzureADKerberosServer -Domain $domain -UserPrincipalName $userPrincipalName -DomainCredential $domainCred
```

#### 例4：先進認証を使用したクラウドの資格情報を要求するプロンプト

注記

ドメイン管理者特権を持つアカウントでドメイン参加済みマシンを使用しており、組織でパスワードベースのサインインを保護し、多要素認証、FIDO2、スマート カード テクノロジなどの最新の認証方法を適用している場合は、`-UserPrincipalName` パラメーターにハイブリッド ID 管理者のユーザー プリンシパル名 (UPN) を指定する必要があります。 また、"-DomainCredential" パラメーターを省略できます。 &gt; - 次の例の `administrator@contoso.onmicrosoft.com` を、ハイブリッド ID 管理者の UPN に置き換えます。

```powershell
# Specify the on-premises Active Directory domain. A new Microsoft Entra ID
# Kerberos Server object will be created in this Active Directory domain.
$domain = $env:USERDNSDOMAIN

# Enter a UPN of a Hybrid Identity Administrator
$userPrincipalName = "administrator@contoso.onmicrosoft.com"

# Create the new Microsoft Entra ID Kerberos Server object in Active Directory
# and then publish it to Azure Active Directory.
# Open an interactive sign-in prompt with given username to access the Microsoft Entra ID.
Set-AzureADKerberosServer -Domain $domain -UserPrincipalName $userPrincipalName
```

#### Microsoft Entra Kerberos サーバーを表示して確認する

新しく作成された Microsoft Entra Kerberos サーバーは、次のコマンドを使用して表示および確認できます。

```powershell
 # When prompted to provide domain credentials use the userprincipalname format for the username instead of domain\username
Get-AzureADKerberosServer -Domain $domain -UserPrincipalName $userPrincipalName -DomainCredential (get-credential)
```

このコマンドで、Microsoft Entra Kerberos サーバーのプロパティが出力されます。 プロパティを確認して、すべてが適切な順序であることを確認できます。

注記

ドメイン\ユーザー名の形式で資格情報を指定して別のドメインに対して実行すると、NTLM を使って接続され、失敗します。 ただし、ドメイン管理者にユーザープリンシパル名形式を使用すると、Kerberos を正しく使用して DC への RPC バインドが試行されることが保証されます。 ユーザーが Active Directory の Protected Users セキュリティ グループに属している場合は、これらの手順を実行して問題を解決します: **ADConnect** で別のドメイン ユーザーとしてサインインします。"-domainCredential" を指定しないでください。 現在サインインしているユーザーの Kerberos チケットが使用されます。 `whoami /groups` を実行することで、前のコマンドを実行するために必要な Active Directory のアクセス許可がユーザーにあるかどうかを検証できます。

| プロパティ | 説明 |
| --- | --- |
| ID | AD DS DC オブジェクトの一意の ID。 この ID は、"*スロット*" または "*ブランチ ID*" と呼ばれることもあります。 |
| DomainDnsName | Active Directory ドメインの DNS ドメイン名。 |
| コンピューターアカウント | Microsoft Entra Kerberos サーバー オブジェクト (DC) のコンピューター アカウント オブジェクト。 |
| ユーザーアカウント | Microsoft Entra Kerberos サーバーの TGT 暗号化キーを保持する、無効なユーザー アカウント オブジェクト。 このアカウントのドメイン名は `CN=krbtgt_AzureAD,CN=Users,<Domain-DN>` です。 |
| KeyVersion | Microsoft Entra Kerberos サーバーの TGT 暗号化キーのキー バージョン。 バージョンは、キーの作成時に割り当てられます。 バージョンは、キーがローテーションされるたびに増やされます。 増分はレプリケーション メタデータに基づいており、ほとんどの場合、1 より大きい値です。 たとえば、初期の *KeyVersion* が *192272* だったとします。 キーが最初にローテーションされると、バージョンは *212621* に進む可能性があります。 確認すべき重要な点は、オンプレミスのオブジェクトの *KeyVersion* とクラウド オブジェクトの *CloudKeyVersion* が同じであることです。 |
| KeyUpdatedOn | Microsoft Entra Kerberos サーバーの TGT 暗号化キーが更新または作成された日時。 |
| KeyUpdatedFrom | Microsoft Entra Kerberos サーバーの TGT 暗号化キーが最後に更新された DC。 |
| CloudId | Microsoft Entra オブジェクトの ID。 この表の最初の行の ID と一致している必要があります。 |
| クラウドドメインDNS名 | Microsoft Entra オブジェクトの *DomainDnsName*。 この表の 2 番目の行の *DomainDnsName* と一致している必要があります。 |
| クラウドキーのバージョン | Microsoft Entra オブジェクトの *KeyVersion*。 この表の 5 番目の行の *KeyVersion* と一致している必要があります。 |
| CloudKeyUpdatedOn | Microsoft Entra オブジェクトの *KeyUpdatedOn*。 この表の 6 番目の行の *KeyUpdatedOn* と一致している必要があります。 |

#### Microsoft Entra Kerberos サーバー キーをローテーションする

Microsoft Entra Kerberos サーバー暗号化の *krbtgt* キーは定期的にローテーションする必要があります。 Active Directory DC の他のすべての *krbtgt* キーのローテーションに使用するものと同じスケジュールに従うことをお勧めします。

警告

*krbtgt* キーをローテーションできるツールはほかにもあります。 ただし、Microsoft Entra Kerberos サーバーの *krbtgt* キーのローテーションには、このドキュメントに記載されているツールを使用する必要があります。 これにより、オンプレミスの Active Directory と Microsoft Entra ID の両方でキーが確実に更新されます。

```powershell
Set-AzureADKerberosServer -Domain $domain -CloudCredential $cloudCred -DomainCredential $domainCred -RotateServerKey
```

#### Microsoft Entra Kerberos サーバーを削除する

シナリオを元に戻し、オンプレミスの Active Directory と Microsoft Entra ID の両方から Microsoft Entra Kerberos サーバーを削除する場合は、次のコマンドを実行します。

```powershell
Remove-AzureADKerberosServer -Domain $domain -CloudCredential $cloudCred -DomainCredential $domainCred
```

#### マルチフォレストとマルチドメインのシナリオ

Microsoft Entra Kerberos サーバー オブジェクトは、Microsoft Entra ID 内では *KerberosDomain* オブジェクトとして表されます。 オンプレミスの各 Active Directory ドメインは、Microsoft Entra ID 内では 1 つの *KerberosDomain* オブジェクトとして表されます。

たとえば、`contoso.com` と `fabrikam.com` の 2 つのドメインを含む Active Directory フォレストが組織にあるとします。 Microsoft Entra ID でフォレスト全体の Kerberos TGT を発行することを許可した場合は、Microsoft Entra ID 内に 2 つの *KerberosDomain* オブジェクトがあります。1 つは  用の `contoso.com` オブジェクトで、もう 1 つは `fabrikam.com` 用です。 複数の Active Directory フォレストがある場合は、各フォレスト内のドメインごとに 1 つの *KerberosDomain* オブジェクトがあります。

Microsoft Entra ユーザーを含む組織内の各ドメインとフォレストで、「Kerberos サーバー オブジェクトを作成する」の手順に従います。

### 既知の動作

パスワードの有効期限が切れている場合、FIDO を使用したサインインはブロックされます。 ユーザーは、FIDO を使用してログインする前に、パスワードをリセットしておく必要があります。 この動作は、Windows Hello for Business クラウド kerberos トラストを使用したハイブリッド オンプレミスの同期されたユーザー サインインにも適用されます。

### トラブルシューティングとフィードバック

このパスワードレス セキュリティ キー サインイン機能について問題が発生した場合や、フィードバックを共有したい場合は、次の手順を実行して、Windows フィードバック Hub アプリ経由で共有します。

1. **フィードバック Hub** を開き、サインインしていることを確認します。
2. 次のカテゴリを選択してフィードバックを送信します。
    - カテゴリ:セキュリティとプライバシー
    - サブカテゴリ: FIDO
3. ログをキャプチャするには、 **[Recreate my Problem](https://learn.microsoft.com/ja-jp/entra/identity/authentication/問題の再現)** オプションを使用します。

### パスワードレス セキュリティ キー サインインに関する FAQ

パスワードレス サインインについてよく寄せられる質問とその回答を次に示します。

#### パスワードレス セキュリティ キー サインインはオンプレミス環境で機能しますか?

この機能は、純粋なオンプレミス AD DS 環境では機能しません。

#### 私の組織では、リソースにアクセスするために 2 要素認証が必要です。 この要件をサポートするにはどうすればよいですか。

セキュリティ キーは、さまざまなフォーム ファクターで提供されています。 2 番目の要素として PIN または生体認証を使用してデバイスを有効にする方法については、デバイスの製造元にお問い合わせください。

#### 管理者はセキュリティ キーを設定できますか?

Microsoft は、この機能の一般提供 (GA) リリースに向けてこの機能に取り組んでいます。

#### 準拠しているセキュリティ キーはどこで見つけることができますか。

準拠しているセキュリティ キーについては、「[FIDO2 セキュリティ キー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)」を参照してください。

#### セキュリティ キーを紛失した場合はどうすればよいですか?

登録されたセキュリティ キーを削除するには、[myaccount.microsoft.com](https://myaccount.microsoft.com/) にサインインし、**[セキュリティ情報]** ページに移動します。

#### ハイブリッド Microsoft Entra 参加済みマシンを作成した直後に FIDO セキュリティ キーを使用できない場合はどうすればよいですか?

ハイブリッド Microsoft Entra 参加済みマシンをクリーンインストールした場合、ドメイン参加および再起動プロセスの後、FIDO セキュリティ キーを使用してサインインするには、パスワードを使用してサインインし、ポリシーが同期されるまで待つ必要があります。

- コマンド プロンプト ウィンドウで `dsregcmd /status` を実行して現在の状態を確認し、**AzureAdJoined** と **DomainJoined** の両方の状態が *YES* と表示されていることを確認します。
- 同期のこの遅延は、ドメイン参加済みデバイスの既知の制限であり、FIDO に固有のものではありません。

#### FIDO を使用してサインインし、資格情報プロンプトが表示された後、NTLM ネットワーク リソースにシングル サインオンできない場合はどうなりますか?

時間内に応答してリソース要求を処理できるように、十分な数の DC に修正プログラムが適用されていることを確認してください。 DC で機能が実行されているかどうかを確認するには、`nltest /dsgetdc:contoso /keylist /kdc` を実行し、出力を確認します。

注記

`/keylist` コマンドの `nltest` スイッチは、Windows 10 v2004 以降のクライアントで使用できます。

#### Microsoft Entra Kerberos のトークンごとにグループの最大数はありますか?

はい。トークンあたり最大 1,010 個のグループを持つことができます。

#### `Failed to read secrets` モジュール コマンドを実行するときに`AzureADHybridAuthenticationManagement`エラーを解決するにはどうすればよいですか?

[FIPS ポリシー](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-10/security/threat-protection/security-policy-settings/system-cryptography-use-fips-compliant-algorithms-for-encryption-hashing-and-signing)を一時的に無効にします。 fips ポリシーは、 `AzureADHybridAuthenticationManagement` モジュールで手順を実行した後で再度有効にすることができます。 FIPS ポリシーを無効にした後もエラーが解決しない場合は、使用されているアカウントに既定の管理アクセス許可があることを確認します。

#### ハイブリッド環境に RODC が存在する Windows ログインで FIDO2 セキュリティ キーは機能しますか?

FIDO2 を使用した Windows のログインでは、ユーザー TGT を交換するために書き込み可能な DC を探します。 サイトごとに少なくとも 1 つの書き込み可能 DC があれば、ログインは正常に機能します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-authentication-passwordless-security-key-windows"} -->
## FIDO2 セキュリティ キーによるWindowsへのサインイン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key-windows
- Service: entra-id / authentication
- Article date: 2026-07-05
- Summary: FIDO2 セキュリティ キーを使用してパスワードレス セキュリティ キーのサインインを有効にしてMicrosoft Entra IDでWindowsする方法について説明します。 デバイスの要件について確認します。

この記事では、Windows 10および 11 台のデバイスで FIDO2 セキュリティ キーベースのパスワードレス認証を有効にすることに重点を置いています。 この記事の手順を完了すると、FIDO2 セキュリティ キーを使用して、Microsoft Entra アカウントを使用して、Microsoft Entra IDとMicrosoft Entraハイブリッド参加済みWindows デバイスの両方にサインインできます。

### FIDO2 セキュリティ キーの前提条件

- 認証方法を構成するための少なくとも [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) アクセス許可を持つアカウント。
- Microsoft Entra 管理センターの[認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2#enable-passkey-profiles)にある**Passkey (FIDO2)**ポリシーで、**パスキーによるサインインを有効にする**必要があります。
- デバイスは、次の要件を満たす必要があります。

    | デバイスの種類 | Microsoft Entra参加済み | Microsoft Entra のハイブリッド参加 |
    | --- | --- | --- |
    | 互換性のある [FIDO2 セキュリティ キー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2) | x | x |
    | WebAuthN にはバージョン 1903 以降Windows 10必要 | x | x |
    | [Microsoft Entra参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)にはWindows 10 バージョン 1909 以上が必要です。 | x |  |
    | [Microsoft Entra ハイブリッド参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join)には、Windows 10 バージョン 2004 以降が必要です。 |  | x |
    | Windows Server 2016 以降を実行する完全に修正プログラムが適用されたドメイン コントローラー |  | x |
    | [Microsoft Entra ハイブリッド認証管理モジュール](https://www.powershellgallery.com/packages/AzureADHybridAuthenticationManagement/2.1.1.0) |  | x |
    | [Microsoft Intune](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/what-is-intune) (省略可能) | x | x |
    | プロビジョニング パッケージ (オプション) | x | x |
    | グループ ポリシー (省略可能) |  | x |

#### サポートされていないシナリオ

以下のシナリオはサポートされていません。

- Windows Server Active Directory Domain Services (AD DS) ドメイン参加済みの (オンプレミス専用デバイスの) 展開。
- [webauthn リダイレクト](https://learn.microsoft.com/ja-jp/azure/virtual-desktop/authentication)以外のセキュリティ キーを使用する RDP、VDI、Citrix などのシナリオ。
- セキュリティ キーを使用した S/MIME。
- セキュリティ キーを使用して *[別のユーザーとして実行]* します。
- セキュリティ キーを使用したサーバーへのサインイン。

#### デバイスのサインインとロック解除

- FIDO2 セキュリティ キーを使用した OOBE サインインがサポートされています。 Web サインインを使用して Windows デバイスのロックを解除できます。 詳細については、「 [Web Sign-In を使用して Windows でパスワードレス Sign-In を有効にする](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/web-sign-in)」を参照してください。
- 複数のMicrosoft Entra アカウントを含むセキュリティ キーを使用してWindows デバイスにサインインまたはロック解除する場合、デバイスの既定値はキーに最後に追加されたアカウントになります。 ただし、WebAuthn を使用すると、ユーザーは認証に使用する特定のアカウントを選択できます。
- デバイスのロックを解除するには、Windows 10 バージョン 1809 が必要です。 最適なエクスペリエンスを得るためのバージョン 1903 以降Windows 10使用してください。

### デバイスを準備する

参加済みのMicrosoft Entraデバイスは、Windows 10バージョン1909以降を実行する必要があります。

Microsoft Entra のハイブリッド参加デバイスは、Windows 10 バージョン 2004 以降を実行する必要があります。

### FIDO2 セキュリティ キーのデバイス バインド パスキー プロファイルを構成する

デバイス バインド キー プロファイルを使用すると、物理セキュリティ キーに格納されているデバイス バインド パスキーの構成証明とキー制限設定を定義できます。

1. 少なくとも [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)として Microsoft Entra 管理センターにサインインします。
2. **[Entra ID]**&gt;**[Authentication メソッド]** に移動します。
3. **認証方法 |[ポリシー] ページで**、**パスキー (FIDO2)**&gt;**構成**を選択します。
4. [ **+ プロファイルの追加] を選択します**。

    [Image: パスキー プロファイルを追加する方法を示すスクリーンショット。]
5. プロファイルの **名前** ( **FIDO2 セキュリティ キー**など) を入力します。
6. **[パスキーの種類**] で、[**デバイス バインド**] と **[保存]** を選択します。

    注

    特定のパスキー プロファイルに対してデバイス バインド パスキーを無効にした場合、対象ユーザーは既に登録されている場合でも、デバイス バインド パスキーを使用してサインインすることはできません。

    [Image: パスキーの種類がデバイス バインドに設定されている FIDO2 セキュリティ キーという名前のパスキー プロファイルを示すスクリーンショット。]

#### 例: 特定の AAGUID をターゲットとする

特定の AAGUID をターゲットにして、ユーザーが登録できる認証子を制御できます。 この例では、パスキー プロファイルで許可されるのは、FIDO2 セキュリティ キーの特定のモデルに対する AAGUID のみです。

このプロファイルを構成するには:

1. **ターゲット固有の AAGUID を選択します**。
2. **[動作]** を **[許可**] に設定します。
3. [ **モデル/プロバイダー AAGUID]** で、サインインを許可する FIDO2 セキュリティ キー モデルの AAGUID を追加し、[保存] を選択 **します**。

    Warning

    - [ **構成証明の強制**] を選択した場合は、登録時に構成証明が必要です。 Microsoft Entra IDは、認証子の作成とモデルを信頼されたメタデータと照らして検証できます。 構成証明は、パスキーが本物であり、指定されたベンダーからのものであることを組織に保証します。 [**構成証明の強制**] を選択しない場合、Microsoft Entra IDは、パスキーに関する属性 (同期またはデバイスバインドなど) を保証できません。
    - 構成証明の適用は、登録時にのみパスキー (FIDO2) が許可されるかどうかを制御します。 構成証明なしでパスキー (FIDO2) を登録したユーザーは、後で [ **構成証明の強制** ] が選択されている場合、サインインはブロックされません。

    [Image: 「FIDO2 セキュリティ キー」という名前のパスキー プロファイルのスクリーンショット。[構成証明を適用する] と [特定の AAGUID をターゲットにする] が選択されており、[動作] が [許可] に設定され、特定の FIDO2 セキュリティ キー モデルの AAGUID が追加されています。]

### デバイス バインド パスキー プロファイルの有効化とターゲット グループ

組織は、次の 1 つ以上の方法を使用して、組織の要件に基づいてWindowsサインインにセキュリティ キーを使用することを選択できます。

- Microsoft Entra 管理センターで有効にする
- Microsoft Intuneで有効にする
- ターゲットにされたMicrosoft Intuneの展開
- プロビジョニング パッケージで有効にする
- グループ ポリシーを使用して有効にする (Microsoft Entraハイブリッド参加済みデバイスのみ)

重要

**Microsoft Entraハイブリッド参加済みデバイス**を持つ組織は、**さらに**、[「オンプレミス リソースに対する FIDO2 認証を有効にする」](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key-on-premises) の記事に記載された手順を完了しなければ、Windows 10でFIDO2セキュリティキー認証が機能しません。

**Microsoft Entra参加しているデバイスを持つ組織**は、デバイスが FIDO2 セキュリティ キーを使用してオンプレミス リソースに対して認証を行う前に、これを行う必要があります。

#### Microsoft Entra 管理センターで有効にする

1. 少なくとも [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)として Microsoft Entra 管理センターにサインインします。
2. **[Entra ID]**&gt;**[Authentication メソッド]** に移動します。
3. **認証方法 | ポリシー** ページで、**パスキー (FIDO2)**&gt;**有効化して対象を指定** を選択します。
4. [ **有効化] タブと [ターゲット** ] タブで、[ **有効]** が **[オン]** になっていることを確認します。
5. [ **ターゲットの追加]** を選択し、[ **すべてのユーザー** ] または **[ターゲットの選択]** を選択して特定のグループを選択します。

    [Image: パスキー プロファイルのターゲットを追加する方法を示すスクリーンショット。]
6. デバイスにバインドされたパスキーのプロファイルを選択します。

    [Image: デバイス バインド パスキーのプロファイルを有効にしてターゲットにする方法を示すスクリーンショット。]
7. 選択したユーザーのデバイス バインド パスキーを有効にするには、[ **保存] を** 選択します。

#### Microsoft Intuneで有効にする

Intune を使用してセキュリティ キーを使用できるようにするには、次の手順を実行します。

1. [Microsoft Intune管理センター](https://intune.microsoft.com/)にサインインします。
2. **デバイス**&gt;**デバイス登録**&gt;**Windows登録**&gt;**Windows Hello for Business** に進みます。
3. **[サインインにセキュリティ キーを使用する]** を **[有効]** に設定します。

サインインのセキュリティ キーの構成は、Windows Hello for Businessの構成に依存しません。

注

これにより、既にプロビジョニングされているデバイスのセキュリティ キーは有効になりません。 その場合は、次の方法（対象を絞った Intune 展開）を使用してください。

#### ターゲットとなる Intune のデプロイ

資格情報プロバイダーを有効にするために特定のデバイス グループをターゲットにするには、Intune を介して次のカスタム設定を使用します。

1. [Microsoft Intune管理センター](https://intune.microsoft.com/)にサインインします。
2. **Devices**&gt;**Windows**&gt;**Configuration profiles**&gt;**Create profile** に移動します。
3. 次の設定を使用して、新しいプロファイルを構成します。
    - プラットフォーム: Windows 10以降
    - プロファイルの種類: カスタム &gt; テンプレート
    - 名前: Windows サインインのセキュリティ キー
    - 説明: Windowsサインイン中に FIDO セキュリティ キーを使用できるようにします
4. **次へ**&gt;**追加**, **行の追加**で、次のカスタム OMA-URI 設定を追加します。
    - 名前: Windows サインインの FIDO セキュリティ キーを有効にする
    - 説明: (省略可能)
    - OMA-URI: ./デバイス/ベンダー/MSFT/パスポートフォーワーク/セキュリティキー/サインインにセキュリティキーを使用
    - データ型:Integer
    - 値: 1
5. 特定のユーザー、デバイス、グループなど、残りのポリシー設定を割り当てます。 詳細については、「Microsoft Intuneを参照してください。

#### プロビジョニング パッケージを使用して有効にする

Microsoft Intuneによって管理されていないデバイスの場合は、プロビジョニング パッケージをインストールして機能を有効にすることができます。 Windows構成デザイナー アプリは、[Microsoft ストア](https://www.microsoft.com/p/windows-configuration-designer/9nblggh4tx22)からインストールできます。 プロビジョニング パッケージを作成するには、次の手順を実行します。

1. Windows構成デザイナーを起動します。
2. [&gt;] を選択します。
3. プロジェクトに名前を付け、プロジェクトが作成されるパスを書き留め、[ **次へ**] を選択します。
4. *[選択したプロジェクト ワークフロー*] として [**プロビジョニング パッケージ**] を選択したままにして、[**次へ**] を選択します。
5. *すべての Windows デスクトップ エディション*を**表示および構成する設定を選択**し、**次へ**を選択します。
6. **完了**を選択します。
7. 新しく作成したプロジェクトで、 **ランタイム設定**&gt;**WindowsHelloForBusiness**&gt;**SecurityKeys**&gt;**UseSecurityKeyForSignIn** に移動します。
8. **UseSecurityKeyForSignIn** を*有効*に設定します。
9. **エクスポート**&gt;**プロビジョニング パッケージの**選択
10. **[ビルド**] ウィンドウの [**プロビジョニング パッケージの説明**] で既定値のままにして、[**次へ**] を選択します。
11. **[ビルド**] ウィンドウの [**プロビジョニング パッケージのセキュリティの詳細の選択**] で既定値のままにして、[**次へ**] を選択します。
12. **[ビルド**] ウィンドウの [**プロビジョニング パッケージを保存する場所の選択**] でパスをメモまたは変更し、[**次へ**] を選択します。
13. **[** **プロビジョニング パッケージのビルド] ページで [ビルド] を**選択します。
14. 作成した 2 つのファイル (*ppkg* と *cat*) を、後でマシンに適用できる場所に保存します。
15. 作成したプロビジョニング パッケージを適用するには、「 [プロビジョニング パッケージの適用](https://learn.microsoft.com/ja-jp/windows/configuration/provisioning-packages/provisioning-apply-package)」を参照してください。

注

バージョン 1903 Windows 10実行されているデバイスでは、共有 PC モード (*EnableSharedPCMode*) も有効にする必要があります。 この機能の有効化の詳細については、「 Windows 10を参照してください。

#### グループ ポリシーを使用して有効にする

**Microsoft Entraハイブリッド参加済みデバイス**の場合、組織は次のグループ ポリシー設定を構成して FIDO セキュリティ キーのサインインを有効にすることができます。 この設定は、 **コンピューターの構成**&gt;**Administrative Templates**&gt;**System**&gt;**Logon**&gt;**Turn on security key sign-in** にあります。

- このポリシーを **[有効]** に設定すると、ユーザーはセキュリティ キーを使用してサインインできます。
- このポリシーを **[無効]** または [ **未構成]** に設定すると、ユーザーはセキュリティ キーを使用してサインインできなくなります。

このグループ ポリシー設定には、 `CredentialProviders.admx` グループ ポリシー テンプレートの更新バージョンが必要です。 この新しいテンプレートは、次のバージョンの Windows Server と Windows 10 20H1 で使用できます。 この設定は、これらの新しいバージョンのWindowsのいずれかを実行しているデバイスで管理するか、[グループ ポリシー管理テンプレートの中央ストアを作成して管理する方法に関するガイダンスに従って、Windows](https://support.microsoft.com/help/3087759/how-to-create-and-manage-the-central-store-for-group-policy-administra)で一元的に管理できます。

### Microsoft Graph API (プレビュー) を使用して FIDO2 セキュリティ キーをプロビジョニングする

現在プレビュー段階では、管理者は [Microsoft Graph およびカスタム クライアントを使用して、ユーザーに代わって FIDO2 セキュリティ キーをプロビジョニングできます](https://aka.ms/passkeyprovision)。 プロビジョニングには、 [認証管理者ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) または UserAuthenticationMethod.ReadWrite.All アクセス許可を持つクライアント アプリケーションが必要です。 プロビジョニングの改善点は次のとおりです。

- Microsoft Entra ID から WebAuthn **作成オプション** を要求する機能
- プロビジョニングされたセキュリティ キーをMicrosoft Entra IDに直接登録する機能

これらの新しい API を使用すると、組織はユーザーに代わってセキュリティ キーにパスキー (FIDO2) 資格情報をプロビジョニングする独自のクライアントを構築できます。 このプロセスを簡略化するには、主要な手順 3 つが必要です。

1. ユーザー用の **creationOptions のリクエスト**: Microsoft Entra ID は、あなたのクライアントがパスキー (FIDO2) 資格情報をプロビジョニングするために必要なデータを返します。 これには、ユーザー情報、証明書利用者 ID、資格情報ポリシー要件、アルゴリズム、登録チャレンジなどの情報が含まれます。
2. 作成オプションを使用してパスキー (FIDO2) 資格情報を**プロビジョニング**します。`creationOptions`を使用し、クライアント認証プロトコル (CTAP) をサポートするクライアントを用いて資格情報を設定してください。 この手順では、セキュリティ キーを挿入し、PIN を設定する必要があります。
3. プロビジョニングされた資格情報を Microsoft Entra ID に**登録**する: プロビジョニング プロセスの書式設定された出力を使用して、対象ユーザーのパスキー (FIDO2) 資格情報を登録するために必要なデータを Microsoft Entra ID に提供します。

[Image: パスキー (FIDO2) をプロビジョニングする手順を示す図。]

### トラブルシューティングとフィードバック

フィードバックを共有したい場合や、この機能に関する問題が発生した場合は、次の手順に従ってWindowsフィードバック Hub アプリを使用して共有してください。

1. **フィードバック ハブ**を起動し、サインインしていることを確認します。
2. 次の分類でフィードバックを送信します。
    - カテゴリ:セキュリティとプライバシー
    - サブカテゴリ: FIDO
3. ログをキャプチャするには、[ **問題を再作成**する] オプションを使用します。

### FIDO2 セキュリティ キーを登録する

管理者がデバイス バインド パスキー プロファイルを作成した後、ユーザーはデバイスに FIDO2 セキュリティ キーを登録できます。

登録手順については、「 [FIDO2 セキュリティ キーを使用してパスキーを登録する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-register-passkey-with-security-key)」を参照してください。

### FIDO2 セキュリティ キーを使用してサインインする

登録後、ユーザーはデバイスの FIDO2 セキュリティ キーを使用してMicrosoft Entra IDにサインインできます。

サインイン手順については、「 [FIDO2 セキュリティ キーを使用したサインイン](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-security-key-sign-in)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-authentication-passwordless-troubleshoot"} -->
## ハイブリッド FIDO 2 セキュリティ キーの既知の問題とトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-troubleshoot
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra ID を使用したパスワードレス ハイブリッド FIDO2 セキュリティ キー サインインの既知の問題とトラブルシューティングの方法について説明します

この記事では、Microsoft Entra ハイブリッド参加済みデバイスと、オンプレミス リソースへのパスワードレス サインインについてよく寄せられる質問について説明します。 このパスワードレス機能を使用すると、FIDO2 セキュリティ キーを使用し、Microsoft Entra ハイブリッド参加デバイスに対して Windows 10 デバイスで Microsoft Entra 認証を有効にすることができます。 ユーザーは、FIDO2 キーなどの最新の資格情報を使用してデバイス上の Windows にサインインし、オンプレミス リソースへのシームレスなシングル サインオン (SSO) エクスペリエンスを使用して従来の Active Directory Domain Services (AD DS) ベースのリソースにアクセスできます。

ハイブリッド環境のユーザーに対して、次のシナリオがサポートされています。

- FIDO2 セキュリティ キーを使用して Microsoft Entra ハイブリッド参加済みデバイスにサインインし、オンプレミス リソースへの SSO アクセスを取得します。
- FIDO2 セキュリティ キーを使用して Microsoft Entra 参加済みデバイスにサインインし、オンプレミス リソースへの SSO アクセスを取得します。

FIDO2 のセキュリティ キーおよびオンプレミスのリソースへのハイブリッド アクセスの概要については、次の記事を参照してください。

- [パスワードレス セキュリティ キー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key)
- [パスワードレスの Windows 10](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key-windows)
- [パスワードレスのオンプレミス](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key-on-premises)

### 既知の問題

- Windows Hello Face が高速すぎて、これが既定のサインイン メカニズムであるため、ユーザーが FIDO2 セキュリティ キーを使用してサインインできない
- ユーザーが Microsoft Entra ハイブリッド参加済みマシンを作成した直後に、FIDO2 セキュリティ キーを使用できない
- FIDO2 セキュリティ キーを使用してサインインし、資格情報のプロンプトを受け取った後、ユーザーが my NTLM ネットワーク リソースへの SSO を取得できない

#### Windows Hello Face が高速すぎて、これが既定のサインイン メカニズムであるため、ユーザーが FIDO2 セキュリティ キーを使用してサインインできない

Windows Hello Face は、ユーザーが登録されているデバイスに最適なエクスペリエンスです。 FIDO2 セキュリティ キーは、共有デバイスで使用するか、Windows Hello for Business への登録が障壁となる場合に使用することが想定されています。

Windows Hello Face のせいでユーザーが FIDO2 セキュリティ キーによるサインイン シナリオを試すことができない場合、ユーザーは **[設定] &gt; [サインイン オプション]** で Face の登録を削除することで Hello Face サインインをオフにできます。

#### ユーザーが Microsoft Entra ハイブリッド参加済みマシンを作成した直後に、FIDO2 セキュリティ キーを使用できない

Microsoft Entra ハイブリッド参加済みマシンのクリーン インストールで、ドメイン参加と再起動プロセスの後、FIDO2 セキュリティ キーを使用してサインインする前に、パスワードを使用してサインインし、ポリシーが同期されるまで待つ必要があります。

この動作はドメインに参加しているデバイスの既知の制限であり、FIDO2 セキュリティ キーに固有のものではありません。

現在の状態を確認するには、`dsregcmd /status` コマンドを使用します。 *AzureAdJoined* と *DomainJoined* の両方が *YES* と表示されていることを確認します。

#### FIDO2 セキュリティ キーを使用してサインインし、資格情報のプロンプトを受け取った後、ユーザーが my NTLM ネットワーク リソースへの SSO を取得できない

時間内に応答してリソース要求を処理できるように、十分な数の DC に修正プログラムが適用されていることを確認してください。 機能を実行しているサーバーが表示できるかどうかを確認するには、`nltest /dsgetdc:<dc name> /keylist /kdc` の出力を確認します。

この機能を使用して DC を表示できる場合は、ユーザーがサインインした後にユーザーのパスワードが変更されているか、別の問題が発生している可能性があります。 次のセクションで説明するように、Microsoft サポート チームがデバッグを行うためのログを収集してください。

### トラブルシューティング

トラブルシューティングには 2 つの領域があります。Windows クライアントの問題とデプロイの問題です。

#### Windows クライアントの問題

Windows へのサインインや、Windows 10 デバイスからのオンプレミス リソースへのアクセスに関する問題のトラブルシューティングに役立つデータを収集するには、次の手順を実行します。

1. **フィードバック ハブ** アプリを開きます。 アプリの左下に自分の名前が表示されていることを確認してから、 **[新しいフィードバック項目の作成]** を選択します。

    フィードバック項目の種類として、 *[問題]* を選択します。
2. *[セキュリティとプライバシー]* カテゴリ、 *[FIDO]* サブカテゴリの順に選択します。
3. *[フィードバックと共に添付ファイルと診断情報を Microsoft に送信する]* チェック ボックスを切り替えます。
4. *[問題の再現]* を選択してから、 *[キャプチャの開始]* を選択します。
5. FIDO2 セキュリティ キーを使用してコンピューターをロックし、ロックを解除します。 問題が発生した場合は、他の資格情報でロックを解除してみてください。
6. **[フィードバック ハブ]** に戻り、 **[キャプチャの停止]** を選択して、フィードバックを送信します。
7. *[フィードバック]* ページの *[マイ フィードバック]* タブにアクセスします。最近送信したフィードバックを選択します。
8. 右上隅にある *[共有]* ボタンを選択して、フィードバックへのリンクを取得します。 サポート ケースを開くとき、または既存のサポート ケースに割り当てられたエンジニアに返信することは、このリンクを含めてください。

次のイベント ログとレジストリ キー情報が収集されます。

**レジストリ キー**

- *HKEY\_LOCAL\_MACHINE\SOFTWARE\Policies\Microsoft\FIDO [\*]*
- *HKEY\_LOCAL\_MACHINE\SOFTWARE\Policies\Microsoft\PassportForWork\* [\*]*
- *HKEY\_LOCAL\_MACHINE\SOFTWARE\Microsoft\Policies\PassportForWork\* [\*]*

**診断情報**

- ライブ カーネル ダンプ
- AppX パッケージ情報の収集
- UIF コンテキスト ファイル

**イベント ログ**

- *%SystemRoot%\System32\winevt\Logs\Microsoft-Windows-AAD%40Operational.evtx*
- *%SystemRoot%\System32\winevt\Logs\Microsoft-Windows-WebAuthN%40Operational.evtx*
- *%SystemRoot%\System32\winevt\Logs\Microsoft-Windows-HelloForBusiness%40Operational.evtx*

#### 展開に関する問題

Microsoft Entra Kerberos サーバーのデプロイに関する問題をトラブルシューティングするには、新しい PowerShell モジュール [AzureADHybridAuthenticationManagement](https://www.powershellgallery.com/packages/AzureADHybridAuthenticationManagement) のログを使用します。

##### ログの表示

[AzureADHybridAuthenticationManagement](https://www.powershellgallery.com/packages/AzureADHybridAuthenticationManagement) モジュール内の Microsoft Entra Kerberos サーバー PowerShell コマンドレットでは、標準の Microsoft Entra Connect ウィザードと同じログ記録が使用されます。 コマンドレットから情報またはエラーの詳細を表示するには、次の手順を実行します。

1. [AzureADHybridAuthenticationManagement](https://www.powershellgallery.com/packages/AzureADHybridAuthenticationManagement) モジュールが使用されたマシンで、`C:\ProgramData\AADConnect\` を参照します。 このフォルダーは既定では表示されません。
2. このディレクトリにある最新の `trace-*.log` ファイルを開いて表示します。

##### Microsoft Entra Kerberos サーバー オブジェクトの表示

Microsoft Entra Kerberos サーバー オブジェクトを表示し、正常な状態であることを確認するには、次の手順を実行します。

1. Microsoft Entra Connect Server または [AzureADHybridAuthenticationManagement](https://www.powershellgallery.com/packages/AzureADHybridAuthenticationManagement) モジュールがインストールされている他の任意のコンピューターで、PowerShell を開き、`C:\Program Files\Microsoft Azure Active Directory Connect\AzureADKerberos\` に移動します。
2. 次の PowerShell コマンドを実行して、Microsoft Entra とオンプレミス AD DS の両方の Microsoft Entra Kerberos サーバーを表示します。

    *corp.contoso.com* は、実際のオンプレミス AD DS ドメインの名前に置き換えます。

    ```powershell
    Import-Module ".\AzureAdKerberos.psd1"
    
    # Specify the on-premises AD DS domain.
    $domain = "corp.contoso.com"
    
    # Enter an Azure Active Directory Global Administrator username and password.
    $cloudCred = Get-Credential
    
    # Enter a Domain Admin username and password.
    $domainCred = Get-Credential
    
    # Get the Azure AD Kerberos Server Object
    Get-AzureADKerberosServer -Domain $domain -CloudCredential $cloudCred -DomainCredential
    $domainCred
    ```

このコマンドは、Microsoft Entra ID とオンプレミス AD DS の両方の Microsoft Entra Kerberos サーバーのプロパティを出力します。 プロパティを確認して、すべてが正常な状態であることを確認します。 プロパティの確認には、次の表を使用してください。

最初のプロパティ セットは、オンプレミス AD DS 環境のオブジェクトのものです。 後半 (*Cloud*\* で始まるプロパティ) は、Microsoft Entra ID の Kerberos サーバー オブジェクトのものです。

| プロパティ | 説明 |
| --- | --- |
| ID (アイディー) | AD DS ドメイン コントローラー オブジェクトの一意の *ID*。 |
| DomainDnsName | AD DS ドメインの DNS ドメイン名。 |
| コンピューターアカウント | Microsoft Entra Kerberos サーバー オブジェクト (DC) のコンピューター アカウント オブジェクト。 |
| ユーザーアカウント | Microsoft Entra Kerberos サーバーの TGT 暗号化キーを保持する、無効なユーザー アカウント オブジェクト。 このアカウントの DN は *CN=krbtgt\_AzureAD,CN=Users,&lt;Domain-DN&gt;* です |
| KeyVersion | Microsoft Entra Kerberos サーバーの TGT 暗号化キーのキー バージョン。 バージョンは、キーの作成時に割り当てられます。 バージョンは、キーがローテーションされるたびに増やされます。 増分はレプリケーション メタデータに基づいており、多くの場合に 1 より大きい値になります。 たとえば、初期の *KeyVersion* が *192272* だったとします。 キーが最初にローテーションされると、バージョンは *212621* に進む可能性があります。 確認すべき重要な点は、オンプレミスのオブジェクトの *KeyVersion* とクラウド オブジェクトの *CloudKeyVersion* が同じであることです。 |
| KeyUpdatedOn | Microsoft Entra Kerberos サーバーの TGT 暗号化キーが更新または作成された日時。 |
| KeyUpdatedFrom | Microsoft Entra Kerberos サーバーの TGT 暗号化キーが最後に更新された DC。 |
| CloudId | Microsoft Entra オブジェクトの *ID*。 上記の *Id* と一致している必要があります。 |
| クラウドドメインDNS名 | Microsoft Entra オブジェクトの *DomainDnsName*。 上記の *DomainDnsName* と一致している必要があります。 |
| クラウドキーのバージョン | Microsoft Entra オブジェクトの *KeyVersion*。 上記の *KeyVersion* と一致している必要があります。 |
| CloudKeyUpdatedOn | Microsoft Entra オブジェクトの *KeyUpdatedOn*。 上記の *KeyUpdatedOn* と一致している必要があります。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-authentication-sms-signin"} -->
## Microsoft Entra IDの SMS ベースのユーザー サインイン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-sms-signin
- Service: entra-id / authentication
- Article date: 2026-03-04
- Summary: ユーザーが SMS を使用してMicrosoft Entra IDにサインインできるように構成し、有効にする方法について説明します

アプリケーションとサービスへのサインインを簡素化し、セキュリティで保護するために、Microsoft Entra ID は SMS ベースの認証を提供します。 この方法を使用すると、現場担当者などのユーザーは、登録された電話番号と SMS 経由で送信されたワンタイム パスコード (OTP) のみを使用してサインインできます。ユーザー名やパスワードは必要ありません。

### フィッシングに強い最新の認証に移行する

Important

Microsoft では、セキュリティを強化するために、フィッシングに強い認証方法を推奨しています。 ユーザーを次のいずれかの方法に移行することを検討してください。

- [パスキー (FIDO2)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)
- [Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-overview)
- [証明書ベースの認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication)

SMS ベースの認証Microsoft Entra、ユーザーは登録された電話番号と SMS 経由で送信されたワンタイム パスコード (OTP) のみを使用してサインインできます。ユーザー名やパスワードは必要ありません。 これは、MICROSOFT ENTRA SMS 多要素認証とは異なります。通常、MFA メソッドとしてユーザー名、パスワード、SMS が必要です。 この認証方法は、主に現場担当者のサインイン エクスペリエンスを簡素化するように設計されており、インフォメーション ワーカー (IW) には推奨されません。

Microsoft Entraには、[QR コード認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-qr-code)と呼ばれる現場担当者向けの代替アプローチもあります。これは、組織が現場共有デバイスのシナリオを検討する場合があります。

この記事の残りの部分では、Microsoft Entra IDで選択したユーザーまたはグループの最初の要素として SMS ベースの認証を有効にする方法について説明します。 SMS ベースのサインインの使用をサポートするアプリの一覧については、「SMS ベースの [認証に対するアプリのサポート](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-sms-supported-apps)」を参照してください。

### 開始する前に

開始する前に、いくつかの重要なポイントを次に示します。

- SMS 認証は、現場担当者 *に対してのみ* 有効にする必要があります。
- SMS 認証を有効にする場合は、現場担当者の職場または自宅へのアクセスにセキュリティ制御を使用するためのベスト プラクティスに従ってください。 詳細については、「 [現場担当者を保護するためのベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/identity-platform/security-best-practices-for-frontline-workers)」を参照してください。
- 現場担当者に対して SMS 認証を有効にする場合は、QR コード認証の使用に移行することをお勧めします。 詳細については、「Microsoft Entra IDの認証方法 - QR コード認証方法の認証方法」を参照してください。

アプリケーションとサービスへのサインインを簡略化してセキュリティで保護するために、Microsoft Entra IDには複数の認証オプションが用意されています。 SMS ベースの認証を使用すると、現場担当者などのユーザーは、サインインの最初の要素として SMS コードを入力できます。 ユーザーは、ユーザー名とパスワードを指定したり、知ったりする必要はありません。

ユーザーは、ID 管理者によってアカウントが作成された後、サインイン プロンプトで電話番号を入力できます。 ユーザーは、サインインを完了するために指定できる SMS 認証コードを受け取ります。 この認証方法を使用すると、特に第一線で働くユーザーにとっては、アプリケーションやサービスへのアクセスが簡単になります。

### [前提条件]

この記事を完了するには、以下のリソースと特権が必要です。

- アクティブなAzure サブスクリプション。
    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- サブスクリプションに関連付けられているMicrosoft Entra テナント。
    - 必要に応じて、[Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/fundamentals/sign-up-organization)または[アカウントにAzureサブスクリプションを関連付けます](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-subscriptions-associated-directory)。
- SMS ベースの認証を有効にするには、Microsoft Entra テナントに少なくとも [Authentication Policy Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) ロールが必要です。
- SMS 認証方法ポリシーで有効になっている各ユーザーは、その方法を使用しない場合でも、ライセンスを取得している必要があります。 有効になっている各ユーザーには、Microsoft Entra ID、EMS、またはMicrosoft 365の次のいずれかのライセンスが必要です。
    - [Microsoft 365 F1 または F3](https://www.microsoft.com/licensing/news/m365-firstline-workers)
    - [Microsoft Entra ID P1 または P2](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)
    - [Enterprise Mobility + Security (EMS) E3 または E5](https://www.microsoft.com/microsoft-365/enterprise-mobility-security/compare-plans-and-pricing) または [Microsoft 365 E3 または E5](https://www.microsoft.com/microsoft-365/compare-microsoft-365-enterprise-plans)
    - [Office 365 F3](https://www.microsoft.com/microsoft-365/business/office-365-f3?activetab=pivot%3aoverviewtab)

### 既知の問題

既知の問題の一部をここに挙げます。

- SMS ベースの認証は現在、Microsoft Entra多要素認証と互換性がありません。
- Teams を除き、SMS ベース認証には、ネイティブな Office アプリケーションとの互換性はありません。
- B2B アカウントの場合、SMS ベース認証はサポートされていません。
- フェデレーション ユーザーは、ホーム テナントでは認証されません。 クラウドでのみ認証されます。
- 既定のサインイン方法を電話番号に対するテキスト メッセージまたは音声通話にしている場合は、多要素認証時に、SMS コードか音声呼び出しを自動的に発信します。 2021 年 6 月の時点で、一部のアプリではユーザーに **テキスト** または **通話** を最初に選択するよう求められます。 このオプションにより、さまざまなアプリに対して必要以上に多くのセキュリティ コードを送信しなくて済みます。 既定のサインイン方法がMicrosoft Authenticator アプリ () の場合、アプリ通知は自動的に送信されます。
- [テナント間同期](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/known-issues?pivots=cross-tenant-synchronization) では、SMS サインインが有効になっているユーザーはサポートされません。

### SMS ベースの認証方法を有効にする

組織で SMS ベース認証を有効にして使用するには、次の 3 つの主要な手順があります。

- 認証方法ポリシーを有効にする。
- SMS ベースの認証方法を使用できるユーザーまたはグループを選択する。
- 各ユーザー アカウントに電話番号を割り当てる。
    - この電話番号は、Microsoft Entra管理センター (この記事で示します) と、*My Staff* または *My Account* で割り当てることができます。

まず、Microsoft Entra テナントに対して SMS ベースの認証を有効にしましょう。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)としてサインインします。
2. **Entra ID**&gt;**認証メソッド**&gt;**ポリシー** に移動します。
3. 使用可能な認証方法の一覧から、[ **SMS**] を選択します。

    [Image: SMS 認証方法を選択する方法を示すスクリーンショット。]
4. **[有効にする]** を選択し、[**ターゲット ユーザー]** を選択します。 *すべてのユーザー*またはグループに対して SMS ベースの認証を有効にすることを選択できます。

    注

    第 1 要素に SMS ベースの認証を構成するには (つまり、ユーザーがこの方法でサインインできるようにするには)、[ **サインインに使用** する] チェック ボックスをオンにします。 これをオフのままにすると、SMS ベースの認証は多要素認証とセルフサービス パスワード リセットのみに使用できるようになります。

    [Image: 認証方法ポリシー ウィンドウで SMS 認証を有効にする]

### 認証方法をユーザーとグループに割り当てる

Microsoft Entra テナントで SMS ベースの認証が有効になっているので、この認証方法の使用を許可する一部のユーザーまたはグループを選択します。

1. [SMS 認証ポリシー] ウィンドウで、[ **ターゲット]** を *[ユーザーの選択*] に設定します。
2. [ **すべてのユーザーまたはグループの追加]** を選択し、 *Contoso ユーザー* や *Contoso SMS ユーザー*などのテスト ユーザーまたはグループを選択します。
3. ユーザーまたはグループを選択したら、[ **選択**] を選択し、更新された認証方法ポリシーを **保存します** 。

SMS 認証方法ポリシーで有効になっている各ユーザーは、その方法を使用しない場合でも、ライセンスを取得している必要があります。 特に、大規模なユーザー グループに対して機能を有効にする場合は、認証方法ポリシーで有効にするユーザーに適切なライセンスがあることを確認してください。

### ユーザー アカウントに電話番号を設定する

ユーザーは SMS ベースの認証が有効になりましたが、サインインする前に、Microsoft Entra IDのユーザー プロファイルに電話番号を関連付ける必要があります。 ユーザーは [My Account](https://support.microsoft.com/account-billing/set-up-sms-sign-in-as-a-phone-verification-method-0aa5b3b3-a716-4ff2-b0d6-31d2bcfbac42) でこの電話番号を自分で設定することができるほか、*Microsoft Entra 管理センター* を使用して電話番号を割り当てることもできます。 電話番号は、少なくとも [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) ロールを持つユーザーが設定できます。

SMS ベースのサインインに電話番号が設定されている場合は、[Microsoft Entra多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-azure-mfa)および[サービスパスワードリセット](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr)で使用することもできます。

1. **Microsoft Entra ID** を検索して選択します。
2. Microsoft Entra ウィンドウの左側にあるナビゲーション メニューから、**Users** を選択します。
3. 前のセクションで SMS ベースの認証を有効にしたユーザー ( *Contoso ユーザー*など) を選択し、[ **認証方法**] を選択します。
4. [ **+ 認証方法の追加]** を選択し、[ *方法の選択* ] ドロップダウン メニューで [ **電話番号**] を選択します。

    *+1 xxxxxxxxx* など、国コードを含むユーザーの電話番号を入力します。 Microsoft Entra管理センターは、電話番号が正しい形式であることを検証します。

    次に、[ *電話の種類* ] ドロップダウン メニューから、必要に応じて [ *モバイル*]、[ *代替モバイル*]、または *[その他* ] を選択します。

    [Image: SMS ベースの認証で使用するMicrosoft Entra管理センターのユーザーの電話番号を設定します]

    電話番号はテナント内で一意にする必要があります。 複数のユーザーに同じ電話番号を使用しようとすると、エラー メッセージが表示されます。
5. ユーザーのアカウントに電話番号を適用するには、[追加] を選択 **します**。

正常にプロビジョニングされると、 *SMS サインインが有効*になっているかどうかのチェック マークが表示されます。

### SMS ベースのサインインをテストする

SMS ベースのサインインが有効になったユーザー アカウントをテストするには、次の手順を実行します。

1. https://www.office.com に新しい InPrivate または Incognito Web ブラウザー ウィンドウを開きます
2. 右上隅にある [ **サインイン**] を選択します。
3. サインイン プロンプトで、前のセクションでユーザーに関連付けられている電話番号を入力し、[ **次へ**] を選択します。

    [Image: テスト ユーザーのサインイン プロンプトで電話番号を入力する]
4. 指定された電話番号に、SMS メッセージが送信されます。 サインイン プロセスを完了するには、SMS メッセージに記載されている 6 桁のコードをサインイン プロンプトに入力します。

    [Image: ユーザーの電話番号に送信された SMS 確認コードを入力します]
5. これでユーザーは、ユーザー名やパスワードを入力しなくてもサインインできるようになりました。

### SMS ベースのサインインをトラブルシューティングする

SMS ベースのサインインの有効化と使用に問題がある場合は、次のシナリオとトラブルシューティング手順を使用できます。 SMS ベースのサインインの使用をサポートするアプリの一覧については、「SMS ベースの [認証に対するアプリのサポート](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-sms-supported-apps)」を参照してください。

#### ユーザー アカウントに既に電話番号が設定されている

ユーザーが既に多要素認証またはセルフサービス パスワード リセット (SSPR) Microsoft Entra登録している場合は、アカウントに既に電話番号が関連付けられています。 SMS ベースのサインインに使用できるように、この電話番号が自動的に利用可能になるわけではありません。

エンド ユーザー エクスペリエンスの詳細については、 [電話番号の SMS サインイン ユーザー エクスペリエンスに関する説明を](https://support.microsoft.com/account-billing/set-up-sms-sign-in-as-a-phone-verification-method-0aa5b3b3-a716-4ff2-b0d6-31d2bcfbac42)参照してください。

#### ユーザーのアカウントに電話番号を設定しようとするとエラーが発生する

Microsoft Entra管理センターでユーザー アカウントの電話番号を設定しようとしたときにエラーが発生した場合は、次のトラブルシューティング手順を確認してください。

1. SMS ベースのサインインに対してご自身が有効になっていることを確認します。
2. **SMS** 認証方法ポリシーでユーザー アカウントが有効になっていることを確認します。
3. Microsoft Entra管理センター (+1 4251234567) で検証されているように、適切な書式で電話番号を設定してください。
4. 電話番号がテナント内の他の場所に使用されていないことを確認します。
5. アカウントに音声番号が設定されていないことを確認します。 音声番号が設定されている場合は、削除してからもう一度電話番号の設定を試してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-authentication-temporary-access-pass"} -->
## パスワードレス認証方法を登録するようにMicrosoft Entra IDで一時アクセス パスを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass
- Service: entra-id / authentication
- Article date: 2026-03-04
- Summary: 一時アクセス パス (TAP) を使用してパスワードレス認証方法を登録するように構成し、ユーザーが利用できるようにする方法を説明します。

パスキー (FIDO2) などのパスワードなしの認証方法を使用すると、ユーザーはパスワードなしで安全にサインインできます。 ユーザーは、次の 2 つの方法のいずれかでパスワードレスの方法をブートストラップできます。

- 既存のMicrosoft Entra多要素認証方法を使用する
- 一時アクセス パスを使用する

一時アクセス パス (TAP) は、1 回の使用または複数のサインイン用に構成できる時間制限付きパスコードです。ユーザーは TAP を使用してサインインして、他のパスワードレス認証方法をオンボードできます。 TAP を使用すると、ユーザーが強力な認証方法を失ったり忘れたりした場合の回復も容易になります。

この記事では、[Microsoft Entra 管理センター](https://entra.microsoft.com)を使用して TAP を有効にして使用する方法について説明します。 これらのアクションは、REST API を使用して実行することもできます。

### 一時アクセス パス ポリシーを有効にする

TAP ポリシーは、テナントで作成されたパスの有効期間や、TAP を使用してサインインできるユーザーとグループなどの設定を定義します。

ユーザーが TAP を使用してサインインできるようにするには、認証方法ポリシーでこの方法を有効にし、TAP を使用してサインインできるユーザーとグループを選択する必要があります。

TAP は任意のユーザーに対して作成できますが、ポリシーに含まれるユーザーのみがサインインできます。 TAP 認証方法ポリシーを更新するには、 [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) ロールが必要です。

認証方法ポリシーで TAP を構成するには:

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)としてサインインします。
2. **Entra ID**&gt;**認証メソッド**&gt;**ポリシー** に移動します。
3. 使用可能な認証方法の一覧から、[ **一時アクセス パス**] を選択します。

    [Image: 認証方法ポリシー エクスペリエンス内で一時アクセス パスを管理する方法のスクリーンショット。]
4. **[有効にする]** を選択し、ポリシーに含めるユーザーまたはポリシーから除外するユーザーを選択します。

    [Image: 認証方法ポリシーで一時アクセス パスを有効にする方法のスクリーンショット。]
5. (省略可能)[ **構成] を** 選択して、最大有効期間や長さの設定など、一時アクセス パスの既定の設定を変更し、[ **更新**] を選択します。

    [Image: 一時アクセス パスの設定をカスタマイズする方法のスクリーンショット。]
6. [ **保存] を** 選択してポリシーを適用します。

    次の表では、既定値と許可される値の範囲について説明します。

    | 設定 | 既定値 | 使用できる値 | コメント |
    | --- | --- | --- | --- |
    | 最短有効期間 | 1 時間 | 10 – 43,200 分 (30 日) | TAP が有効である最短時間 (分)。 |
    | 最長有効期間 | 8 時間 | 10 – 43,200 分 (30 日) | TAP が有効である最長時間 (分)。 |
    | 既定の有効期間 | 1 時間 | 10 – 43,200 分 (30 日) | ポリシーによって構成される最短と最長の有効期間内の個々のパスによって、規定値をオーバーライドできます。 |
    | 1 回限りの使用 | いいえ | 真/偽 | ポリシーが false に設定されている場合、テナント内の TAP は、その有効期間中に 1 回または複数回使用できます (最長有効期間)。 TAP ポリシーで 1 回限りの使用を強制する場合、テナントで作成されたすべてのパスは 1 回限りの使用になります。 |
    | Length | 8 | 8 ～ 48 文字 | パスコードの長さを定義します。 |

### 一時アクセス パスを作成する

TAP ポリシーを有効にすると、Microsoft Entra IDのユーザーに対して TAP ポリシーを作成できます。 次のロールは、TAP に関連するさまざまなアクションを実行できます。

- [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator) は、管理者とメンバー (それ自体を除く) の TAP を作成、削除、および表示できます。
- [認証管理者は](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) 、メンバー (それ自体を除く) の TAP を作成、削除、および表示できます。
- [認証ポリシー管理者は](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) 、TAP を有効にしたり、グループを含めたり除外したり、認証方法ポリシーを編集したりできます。
- [グローバル閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) は、(コード自体を読まずに) ユーザーの TAP の詳細を表示できます。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. TAP を作成するユーザーを選びます。
4. [ **認証方法** ] を選択し、[ **認証方法の追加]** を選択します。

    [Image: 一時アクセス パスを作成する方法のスクリーンショット。]
5. [ **一時アクセス パス]** を選択します。
6. カスタムのアクティブ化時間または期間を定義し、[ **追加**] を選択します。

    [Image: メソッドの追加のスクリーンショット - 一時アクセス パス。]
7. 追加されると、TAP の詳細が表示されます。

    重要

    ユーザーにこの値を指定するため、実際の TAP 値を書き留めます。 **[OK]** を選択した後は、この値を表示できません。

    [Image: 一時アクセス パスの詳細のスクリーンショット。]
8. 完了したら、[ **OK] を選択します** 。

次のコマンドは、PowerShell を使用して TAP を作成および取得する方法を示しています。

```powershell
# Create a Temporary Access Pass for a user
$properties = @{}
$properties.isUsableOnce = $True
$properties.startDateTime = '2022-05-23 06:00:00'
$propertiesJSON = $properties | ConvertTo-Json

New-MgUserAuthenticationTemporaryAccessPassMethod -UserId user2@contoso.com -BodyParameter $propertiesJSON

Id                                   CreatedDateTime       IsUsable IsUsableOnce LifetimeInMinutes MethodUsabilityReason StartDateTime         TemporaryAccessPass
--                                   ---------------       -------- ------------ ----------------- --------------------- -------------         -------------------
00aa00aa-bb11-cc22-dd33-44ee44ee44ee 5/22/2022 11:19:17 PM False    True         60                NotYetValid           23/05/2022 6:00:00 AM TAPRocks!

# Get a user's Temporary Access Pass
Get-MgUserAuthenticationTemporaryAccessPassMethod -UserId user3@contoso.com

Id                                   CreatedDateTime       IsUsable IsUsableOnce LifetimeInMinutes MethodUsabilityReason StartDateTime         TemporaryAccessPass
--                                   ---------------       -------- ------------ ----------------- --------------------- -------------         -------------------
00aa00aa-bb11-cc22-dd33-44ee44ee44ee 5/22/2022 11:19:17 PM False    True         60                NotYetValid           23/05/2022 6:00:00 AM

```

詳細については、「 [New-MgUserAuthenticationTemporaryAccessPassMethod](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/new-mguserauthenticationtemporaryaccesspassmethod) 」および [「Get-MgUserAuthenticationTemporaryAccessPassMethod](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/get-mguserauthenticationtemporaryaccesspassmethod)」を参照してください。

### 一時アクセス パスを使用する

TAP は、ユーザーが最初のサインインやデバイスのセットアップの際に認証の詳細を登録する場合に最もよく使用されます。その場合、追加のセキュリティ プロンプトを完了する必要はありません。 認証方法は [、セキュリティ情報](https://mysignins.microsoft.com/security-info)に登録されます。 ユーザーは、ここで既存の認証方法を更新することもできます。

1. Web ブラウザーを開き、 [セキュリティ情報を表示します](https://mysignins.microsoft.com/security-info)。
2. *tapuser@contoso.com*など、TAP を作成したアカウントの UPN を入力します。
3. ユーザーが TAP ポリシーに含まれている場合、TAP を入力する画面が表示されます。
4. Microsoft Entra管理センターに表示された TAP を入力します。

    [Image: 一時アクセス パスを入力する方法のスクリーンショット。]

注

フェデレーション ドメインの場合は、フェデレーションよりも TAP を使用することをお勧めします。 TAP を持つユーザーは、Microsoft Entra IDで認証を完了し、フェデレーション ID プロバイダー (IdP) にリダイレクトされません。

これでユーザーがサインインし、FIDO2 セキュリティ キーなどの方法を更新または登録できるようになりました。 資格情報やデバイスを紛失したために認証方法を更新するユーザーは、古い認証方法を必ず削除する必要があります。 ユーザーは、パスワードを使用して引き続きサインインすることもできます。TAP はユーザーのパスワードを置き換えません。

#### 一時アクセス パスのユーザー管理

[セキュリティ情報](https://mysignins.microsoft.com/security-info)を管理しているユーザーには、一時アクセス パスのエントリが表示されます。 ユーザーが他の登録メソッドを持っていない場合は、新しいサインイン 方法を追加することを示すバナーが画面の上部に表示されます。 ユーザーは TAP の有効期限を表示したり、不要になった TAP を削除したりできます。

[Image: ユーザーがマイ セキュリティ情報で一時アクセス パスを管理する方法のスクリーンショット。]

#### デバイス登録とパスワードレス登録

デバイス登録とパスワードレス資格情報の登録 (Windows Hello for Businessなど) をサポートするために一時アクセス パス (TAP) を使用する場合、エンド ユーザー エクスペリエンスを円滑にするための 2 つの方法がサポートされます。

| Option | プロセスの説明 | Steps |
| --- | --- | --- |
| 2つの使い捨てタップ | 組織が単一使用の TAP を適用し、デバイスの登録プロセス全体に 10 分以上かかる場合、ユーザーはパスワードレス資格情報の登録に 2 つ目の単一使用 TAP を入力する必要があります。 注: ユーザーがシングルユース TAP を入力してパスワードレス認証方法を登録する場合は、サインインから 10 分以内に登録を完了する必要があります。 この動作は、多要素認証 (MFA) を必要とするすべての新しい認証方法の登録 ( [セキュリティ情報](https://mysignins.microsoft.com/security-info)を使用して開始された登録を含む) で一貫しています。 10 分間の要件は、資格情報の登録中の MFA の適用に関連しますが、特に TAP には関係しません。 | デバイスの登録を完了するには、最初の単一使用 TAP が使用されます。デバイス登録プロセスが 10 分を超えてWindows Hello for Business登録手順に達した場合は、資格情報を登録するために 2 つ目の単一使用 TAP を指定する必要があります。 |
| 多用途のTAP | 組織で複数の TAP を有効にしている場合、ユーザーは有効期間中に 1 回の TAP で複数回サインインできます。 たとえば、ユーザーは 1 時間に 1 回の TAP で複数回サインインできます。マルチユース TAP を使用すると、複数のパスコードを配布する必要性を減らすことで、エンド ユーザー エクスペリエンスを簡素化できます。 TAP の使用状況を監視して、有効期間中に予想以上の時間が使用されていないことを確認します。 | ユーザーは同じ TAP を 1 回入力してデバイスを登録し、もう一度Windows Hello for Business登録できます。ユーザーが新しい認証方法の登録を開始すると、10 分間の MFA 要件が効果的に再起動します。 タイマーの再起動により、デバイスのセットアップが中断されないようにします。 |

#### Windows デバイスのセットアップ

TAP を使用しているユーザーは、Windows 10と 11 のセットアップ プロセスを移動して、デバイス参加操作を実行し、Windows Hello for Businessを構成できます。 Windows Hello for Businessを設定するための TAP の使用は、デバイスの参加状態によって異なります。

Microsoft Entra IDに参加しているデバイスの場合:

- Microsoft Entra参加のセットアップ プロセス中に、ユーザーは TAP で認証を行い (パスワードは必要ありません)、デバイスに参加してWindows Hello for Business登録できます。
- 既に参加しているデバイスでは、TAP を使用してWindows Hello for Businessを設定する前に、まずパスワード、スマートカード、FIDO2 キーなどの別の方法で認証する必要があります。
- Windowsの [Web サインイン](https://learn.microsoft.com/ja-jp/windows/client-management/mdm/policy-csp-authentication#authentication-enablewebsignin)機能も有効になっている場合、ユーザーは TAP を使用してデバイスにサインインできます。 これは、デバイスの初期セットアップの完了、またはユーザーがパスワードを知らない場合やパスワードを持っていない場合の回復のみを目的としています。

ハイブリッド参加済みデバイスの場合、ユーザーは TAP を使用してWindows Hello for Businessを設定する前に、パスワード、スマートカード、FIDO2 キーなどの別の方法で認証する必要があります。

注

フェデレーション ドメインの場合、MFA が必要な場合、 **FederatedIdpMfaBehavior** 設定によって動作が変更されます。

- **enforceMfaByFederatedIdp** に設定すると、ユーザーは MFA のフェデレーション ID プロバイダー (IdP) にリダイレクトされ、TAP でWindows Hello for Businessを設定するように求められることはありません。
- **acceptIfMfaDoneByFederatedIdp** に設定した場合、ユーザーは、Windows Hello for Business のプロビジョニングのための MFA 中に Microsoft Entra ID で TAP プロンプトを表示します。

スクリーンショット: Windows を設定するときに一時アクセスパスを入力する方法。

#### Microsoft Authenticatorでの TAP の使用

ユーザーは TAP を使用して、自分のアカウントにMicrosoft Authenticatorを登録することもできます。 職場または学校のアカウントを追加し、TAP を使用してサインインすることで、ユーザーは Authenticator アプリから直接パスキーとパスワードレス電話サインインの両方を登録できます。

詳細については、「[職場または学校アカウントを Microsoft Authenticator アプリに追加する](https://support.microsoft.com/account-billing/add-your-work-or-school-account-to-the-microsoft-authenticator-app-43a73ab5-b4e8-446d-9e54-2a4cb8e4e93c)を参照してください。

[Image: 職場または学校アカウントを使用して一時アクセス パスを入力する方法のスクリーンショット。]

#### ゲスト アクセス

TAP をサインイン方法として内部ゲストに追加できますが、他の種類のゲストには追加できません。 内部ゲストには、ユーザー オブジェクト **UserType** が **Guest** に設定されています。 Microsoft Entra IDに登録されている認証方法があります。 内部ゲストとその他のゲスト アカウントの詳細については、「 [B2B ゲスト ユーザーのプロパティ](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)」を参照してください。

Microsoft Entra管理センターまたはMicrosoft Graphの外部ゲスト アカウントに TAP を追加しようとすると、外部ゲスト ユーザーに **Temporary Access Pass を追加できないというエラーが表示されます**

外部ゲスト ユーザーは、TAP がホーム テナント認証要件を満たし、クロステナント アクセス ポリシーがユーザーのホーム テナントから MFA を信頼するように構成されている場合、ホーム テナントによって発行された TAP を使用してリソース テナントにサインインできます。「 [B2B コラボレーションのテナント間アクセス設定の管理](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration)」を参照してください。

#### 有効期限

有効期限が切れたか、または削除された TAP を、対話型または非対話型認証に使用することはできません。

TAP の有効期限が切れたか、または削除された後、ユーザーは別の認証方法で再認証する必要があります。

TAP サインイン中に発行されたトークン (セッション トークン、更新トークン、アクセス トークンなど) は、発行時の TAP 有効期限時に最大有効期間を上限とします。 ただし、TAP の有効期限は、既に確立されているセッションをさかのぼって無効にすることはありません。 セッション トークンの有効期間は [条件付きアクセス セッション制御](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-session-lifetime)によって制御されるため、TAP を使用してサインインしたユーザーは、条件付きアクセスの構成に応じて、TAP の有効期間よりも長くアクセスを保持できます。 TAP サインイン後のアクセスの期間を制限するには、 [サインイン頻度](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime#sign-in-frequency) ポリシーを構成します。

### 期限切れの一時アクセス パスを削除する

ユーザーの **認証方法の** 下の **[詳細]** 列には、TAP の有効期限が切れた日時が表示されます。 以下の手順に従って、有効期限切れの TAP を削除できます。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動し、ユーザー*タップ*などのユーザーを選択してから、[**認証方法**] を選択します。
3. 一覧に表示されている **一時アクセス パス** 認証方法の右側で、[削除] を選択 **します**。

PowerShell を使用することもできます。

```powershell
# Remove a user's Temporary Access Pass
Remove-MgUserAuthenticationTemporaryAccessPassMethod -UserId user3@contoso.com -TemporaryAccessPassAuthenticationMethodId 00aa00aa-bb11-cc22-dd33-44ee44ee44ee
```

詳細については、「 [Remove-MgUserAuthenticationTemporaryAccessPassMethod](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/remove-mguserauthenticationtemporaryaccesspassmethod)」を参照してください。

### 一時アクセス パスを置き換える

- ユーザーは、1 つの TAP しか持つことができません。 パスコードは、TAP の開始時から終了時にかけて使用できます。
- ユーザーが新しい TAP を必要とする場合、次のようになります。
    - 既存の TAP が有効な場合、管理者は新しい TAP を作成して、既存の有効な TAP をオーバーライドできます。
    - 既存の TAP の有効期限が切れている場合は、新しい TAP によって既存の TAP がオーバーライドされます。

オンボードと復旧のための NIST 標準の詳細については、「 [NIST Special Publication 800-63A](https://pages.nist.gov/800-63-3/sp800-63a.html#sec4)」を参照してください。

### 制限事項

以下の制限事項に留意してください。

- 1 回限りの TAP を使用して FIDO2 セキュリティ キーや電話によるサインインなどのパスワードレスの方法を登録する場合、ユーザーは 1 回限りの TAP を使って 10 分以内に登録を完了する必要があります。 この制限は、複数回使用できる TAP には適用されません。
- セルフサービス パスワード リセット (SSPR) 登録ポリシー *or*[Microsoft Entra ID Protection 多要素認証登録ポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-mfa-policy)のスコープ内のユーザーは、ブラウザーを使用して TAP でサインインした後に認証方法を登録する必要があります。 これらのポリシーの対象となるユーザーは、[統合登録の割り込みモードに](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-registration-mfa-sspr-combined#combined-registration-modes)リダイレクトされます。 現在、このエクスペリエンスでは、FIDO2 と電話によるサインインの登録はサポートされていません。
- TAP は、ネットワーク ポリシー サーバー (NPS) 拡張機能と Active Directory Federation Services (AD FS) アダプターでは使用できません。
- 変更がレプリケートされるまで数分かかる場合があります。 このため、TAP がアカウントに追加された後、プロンプトが表示されるまでにしばらく時間がかかることがあります。 同様の理由で、TAP の有効期限が切れた後も、ユーザーには TAP のプロンプトが表示されることがあります。

### トラブルシューティング

- サインイン中に TAP がユーザーに提供されない場合、次を実行します。
    - ユーザーが認証方法ポリシーで TAP 使用のスコープ内にあることを確認します。
    - ユーザーが有効な TAP を持っていることを確認します。 TAP が 1 回限りの使用である場合は、まだ使われていないことを確認します。
- TAP を使用した **サインイン時にユーザー資格情報ポリシーが表示されるために一時アクセス パス**のサインインがブロックされた場合:
    - ユーザーが TAP ポリシーのスコープ内にあることを確認します。
    - 認証方法ポリシーで 1 回限りの TAP が必要な場合は、ユーザーが複数使用するための TAP を持っていないことを確認します。
    - 1 回限りの TAP が既に使われているかどうかを確認します。
- アカウントに認証方法として TAP を追加しようとした際に、「一時アクセスパスを外部ゲストユーザーに追加できません」というエラーメッセージが表示された場合、そのアカウントは外部ゲストであることを示しています。 内部ゲスト アカウントと外部ゲスト アカウントの両方に、Microsoft Entra管理センターと Microsoft Graph API にサインインするための TAP を追加するオプションがあります。 ただし、TAP を発行できるのは内部ゲスト アカウントのみです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-authentication-use-email-signin"} -->
## 代替ログイン ID としてメール アドレスを使用して Microsoft Entra ID にサインインする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-use-email-signin
- Service: entra-id / authentication
- Article date: 2025-04-17
- Summary: ユーザーが代替ログイン ID としてメール アドレスを使用して Microsoft Entra ID にサインインできるようにする方法について説明します

注

メールを代替ログイン ID としてMicrosoft Entra ID にサインインすることは、Microsoft Entra ID のパブリック プレビュー機能です。 プレビューの詳細については、「 [Microsoft Azure プレビューの追加使用条件](https://aka.ms/EntraPreviewsTermsOfUse)」を参照してください。

多くの組織では、ユーザーがオンプレミス ディレクトリ環境と同じ資格情報を使用して Microsoft Entra ID にサインインできるようにしたいと考えています。 ハイブリッド認証と呼ばれるこの方法では、ユーザーは 1 組の資格情報を記憶しておくだけでよくなります。

一部の組織では、次の理由によりハイブリッド認証に移行していません。

- 既定で、Microsoft Entra のユーザー プリンシパル名 (UPN) が、オンプレミス UPN と同じ値に設定されています。
- Microsoft Entra UPN を変更すると、オンプレミス環境と Microsoft Entra 環境の間で不一致が生じ、特定のアプリケーションやサービスで問題が発生する可能性があります。
- ビジネスまたはコンプライアンス上の理由により、組織では、Microsoft Entra ID へのサインインにオンプレミスの UPN を使用したくないと考えています。

ハイブリッド認証に移行するために、ユーザーが代替ログイン ID として各自のメール アドレスでサインインできるようにMicrosoft Entra ID を構成できます。 たとえば、*Contoso が*レガシ  UPN でサインインし続けるのではなく、`ana@contoso.com` にブランド変更した場合、代替ログイン ID として電子メールを使用できます。 アプリケーションまたはサービスにアクセスするために、ユーザーは、UPN 以外のメール アドレス (`ana@fabrikam.com` など) を使用して Microsoft Entra ID にサインインします。

[Image: 代替ログイン ID としての電子メールの図。]

この記事では、代替ログイン ID として電子メールを有効にして使用する方法について説明します。

### 開始する前に

代替ログイン ID としてのメール アドレスについて知る必要がある情報を次に示します。

- この機能は、Microsoft Entra ID Free エディション以降で使用できます。
- この機能により、クラウド認証された Microsoft Entra ユーザーに対して、UPN に加えて *ProxyAddresses* を使用したサインインが可能になります。 これは、 B2B セクションの Microsoft Entra Business-to-Business (B2B) コラボレーションにどのように適用されるかについて詳しく説明します。
- ユーザーが UPN 以外の電子メールでサインインすると、`unique_name`内の`preferred_username`要求と要求 (存在する場合) は、UPN 以外の電子メールを返します。
    - 使用中の UPN 以外のメール アドレスが古くなった (ユーザーに属さなくなった) 場合、これらの要求は代わりに UPN を返します。
- この機能では、パスワード ハッシュ同期 (PHS) またはパススルー認証 (PTA) による、マネージド認証がサポートされています。
- この機能を構成するには、次の 2 つのオプションがあります。
    - ホーム領域検出 (HRD) ポリシー - このオプションを使用して、テナント全体の機能を有効にします。 少なくとも [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) ロールが必要です。
    - 段階的ロールアウト ポリシー - このオプションを使用して、特定の Microsoft Entra グループで機能をテストします。 段階的なロールアウトで、セキュリティ グループを初めて追加する時点では、UX タイムアウトを回避するために、200 ユーザーに制限されます。グループを追加した後、必要に応じて、そのグループにさらにユーザーを直接追加できます。

### プレビューの制限事項

現在のプレビュー状態では、代替ログイン ID としてのメール アドレスに次の制限が適用されます。

- **ユーザー エクスペリエンス** - UPN 以外のメールでサインインした場合でも、UPN が表示される場合があります。 次のような動作の例が表示されます。

    - `login_hint=<non-UPN email>` を使用した Microsoft Entra サインインに誘導される場合、ユーザーは UPN でサインインするように求められます。
    - ユーザーが UPN 以外の電子メールでサインインし、正しくないパスワードを入力すると、[パスワードの *入力* ] ページが変更され、UPN が表示されます。
    - Microsoft Office などの一部の Microsoft サイトやアプリでは、通常、右上に表示される *アカウント マネージャー* コントロールに、サインインに使用される UPN 以外の電子メールではなく、ユーザーの UPN が表示される場合があります。
- **サポートされていないフロー** - 一部のフローは、次のような UPN 以外の電子メールと現在互換性がありません。

    - Microsoft Entra ID Protection は、UPN 以外の電子メールと*漏洩した資格情報の*リスク検出を一致させません。 このリスク検出では、UPN を使用して、漏洩した認証情報を照合します。 詳細については、「 [方法: リスクを調査する」を](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)参照してください。
    - ユーザーが UPN 以外のメール アドレスを使用してサインインしている場合、ユーザーは自分のパスワードを変更できません。 Microsoft Entra セルフサービス パスワード リセット (SSPR) は想定どおりに機能します。 SSPR 中に、ユーザーが UPN 以外のメール アドレスを使用して自分の ID を確認すると、UPN が表示される場合があります。
- **サポートされていないシナリオ** - 次のシナリオはサポートされていません。 UPN 以外のメール アドレスを使用した次へのサインイン:

    - [Microsoft Entra ハイブリッドに参加したデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join)
    - [Microsoft Entra 参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)
    - [Microsoft Entra 登録済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-device-registration)
    - [モバイルプラットフォームにおけるシングル Sign-On とアプリ保護ポリシー](https://learn.microsoft.com/ja-jp/entra/identity-platform/mobile-sso-support-overview)
    - POP3 や SMTP などのレガシ認証
- **サポートされていないアプリ** - 一部のサード パーティ製アプリケーションは、 `unique_name` または `preferred_username` 要求が不変であると想定している場合や、常に UPN などの特定のユーザー属性と一致すると想定した場合に動作しない可能性があります。
- **ログ -** HRD ポリシーで機能の構成に加えられた変更は、監査ログに明示的に表示されません。
- **段階的ロールアウト ポリシー** - 次の制限は、段階的ロールアウト ポリシーを使用して機能が有効になっている場合にのみ適用されます。

    - この機能は、他の段階的ロールアウト ポリシーに含まれているユーザーに対しては想定どおりに機能しません。
    - 段階的ロールアウト ポリシーでは、機能ごとに最大 10 個のグループがサポートされます。
    - 段階的ロールアウト ポリシーでは、入れ子になったグループはサポートされていません。
    - 段階的ロールアウト ポリシーでは、動的メンバーシップ グループはサポートされていません。
    - グループ内の連絡先オブジェクトによって、段階的ロールアウト ポリシーへのグループの追加がブロックされます。
- **値の重複** - テナント内では、クラウド専用ユーザーの UPN は、オンプレミス ディレクトリから同期された別のユーザーのプロキシ アドレスと同じ値にすることができます。 このシナリオでは、機能を有効にすると、クラウド専用のユーザーは UPN でサインインできなくなります。 この問題の詳細については、「 トラブルシューティング 」セクションを参照してください。

### 代替ログイン ID オプションの概要

Microsoft Entra ID にサインインするために、ユーザーは自分のアカウントを一意に識別する値を入力します。 これまでは、サインイン ID として使用できるのは Microsoft Entra UPN のみでした。

オンプレミスの UPN がユーザーの推奨されるサインイン メールである組織の場合、この方法は優れていました。 そうした組織では、Microsoft Entra UPN をオンプレミスの UPN とまったく同じ値に設定することで、ユーザーの一貫したサインイン エクスペリエンスを実現しています。

#### AD FS の代替ログイン ID

ただし、組織によっては、オンプレミスの UPN をサインイン ID として使用していません。 オンプレミス環境では、代替ログイン ID を使用してサインインできるようにローカル AD DS を構成しています。 Microsoft Entra ID でユーザーはその値を使用してサインインするよう求められるため、Microsoft Entra UPN をオンプレミスの UPN と同じ値に設定することはできません。

#### Microsoft Entra Connect の代替ログイン ID

この問題の一般的な回避策は、 Microsoft Entra UPN を、ユーザーがサインインに使用することを想定しているメール アドレスに設定することでした。 この方法は有効ですが、結果的にオンプレミスの AD と Microsoft Entra ID で UPN が異なります。また、この構成はすべての Microsoft 365 ワークロードと互換性があるわけではありません。

#### 代替ログイン ID としてのメール アドレス

別の方法は、Microsoft Entra ID とオンプレミスの UPN を同じ値に同期し、ユーザーが検証済みの電子メールで Microsoft Entra ID にサインインできるように Microsoft Entra ID を構成することです。 この機能を提供するには、オンプレミス ディレクトリのユーザーの *ProxyAddresses* 属性に 1 つ以上の電子メール アドレスを定義します。 *ProxyAddresses* は、Microsoft Entra Connect を使用して Microsoft Entra ID に自動的に同期されます。

| オプション | 説明 |
| --- | --- |
| [AD FS の代替ログイン ID](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configuring-alternate-login-id) | AD FS ユーザーの代替属性 (Mail など) を使用したサインインを有効にします。 |
| [Microsoft Entra Connect の代替ログイン ID](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-userprincipalname#alternate-login-id) | 代替属性 (Mail など) を Microsoft Entra UPN として同期します。 |
| 代替ログイン ID としてのメール アドレス | Microsoft Entra ユーザーの検証済みドメイン *ProxyAddresses* を使用してサインインを有効にします。 |

### サインイン電子メール アドレスを Microsoft Entra ID に同期する

従来の Active Directory ドメイン サービス (AD DS) 認証または Active Directory フェデレーションサービス (AD FS) 認証は、ネットワーク上で直接行われ、AD DS インフラストラクチャによって処理されます。 ハイブリッド認証では、ユーザーは代わりに Microsoft Entra ID に直接サインインすることができます。

このハイブリッド認証アプローチをサポートするには、Microsoft Entra Connect を使用してオンプレミスの AD DS 環境を [Microsoft Entra](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect) ID に同期し、PHS または PTA を使用するように構成します。 詳細については、「 [Microsoft Entra ハイブリッド ID ソリューションに適した認証方法を選択](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/choose-ad-authn)する」を参照してください。

どちらの構成オプションでも、ユーザーはユーザー名とパスワードを Microsoft Entra ID に送信し、ここで資格情報が検証され、チケットが発行されます。 ユーザーが Microsoft Entra ID にサインインすると、組織が AD FS インフラストラクチャをホストし管理する必要性はなくなります。

Microsoft Entra Connect によって自動的に同期されるユーザー属性の 1 つは *ProxyAddresses です*。 ユーザーが *ProxyAddresses* 属性の一部としてオンプレミスの AD DS 環境で定義されているメール アドレスを持っている場合は、自動的に Microsoft Entra ID に同期されます。 このメール アドレスは、直接 Microsoft Entra サインイン プロセスで、代替ログイン ID として使用できます。

重要

テナントの確認済みドメインのメールのみが Microsoft Entra ID に同期されます。 それぞれの Microsoft Entra テナントには、1 つ以上の確認済みドメインがあり、これらは所有権が実証され、テナントに一意にバインドされます。

詳細については、「 [Microsoft Entra ID でカスタム ドメイン名を追加して確認](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)する」を参照してください。

### メール アドレスを使用した B2B ゲスト ユーザーのサインイン

[Image: B 2 B ゲスト ユーザー サインインの代替ログイン ID としての電子メールの図。]

代替ログイン ID としての電子メールは、"Bring your own sign-in identifiers" モデルの下で [Microsoft Entra B2B コラボレーション](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) に適用されます。 代替ログイン ID としてのメール アドレスがホーム テナントで有効になっている場合、Microsoft Entra ユーザーは、UPN 以外のメール アドレスを使用して、リソース テナント エンドポイントでゲスト サインインを実行できます。 この機能を有効にするために、リソース テナントで要求される操作はありません。

注

機能が有効になっていないリソース テナント エンドポイントで代替ログイン ID を使用すると、サインイン プロセスはシームレスに動作しますが、SSO は中断されます。

### メール アドレスを使用してユーザーのサインインを有効にする

注

この構成オプションには HRD ポリシーを使用します。 詳細については、「 [homeRealmDiscoveryPolicy リソースの種類」を参照してください](https://learn.microsoft.com/ja-jp/graph/api/resources/homerealmdiscoverypolicy)。

*ProxyAddresses* 属性が適用されたユーザーが Microsoft Entra Connect を使用して Microsoft Entra ID に同期されたら、ユーザーがテナントの代替ログイン ID として電子メールでサインインする機能を有効にする必要があります。 この機能は、UPN 値に対してサインイン識別子を確認するだけでなく、電子メール アドレスの *ProxyAddresses* 値に対しても確認するように Microsoft Entra ログイン サーバーに指示します。

Microsoft Entra 管理センターか Graph PowerShell 使用し、機能を設定できます。

#### Microsoft Entra 管理センター

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)としてサインインします。
2. **Entra ID**&gt;**Entra Connect**&gt;**Connect Sync** に移動します
3. [代替ログイン ID として電子メール] を選択します\*\*。

    [Image: Microsoft Entra 管理センターの代替ログイン ID オプションとしての電子メールのスクリーンショット。]
4. [ *代替ログイン ID として電子メールを送信*する] の横にあるチェック ボックスをオンにします。
5. [ **保存] をクリックします**。

    [Image: Microsoft Entra 管理センターの [代替ログイン ID] ブレードとしての電子メールのスクリーンショット。]

ポリシーが適用されている場合は、伝達されて、ユーザーが代替ログイン ID を使用してサインインできるようになるまでに、最大 1 時間かかることがあります。

#### PowerShell

注

この構成オプションには HRD ポリシーを使用します。 詳細については、「 [homeRealmDiscoveryPolicy リソースの種類」を参照してください](https://learn.microsoft.com/ja-jp/graph/api/resources/homeRealmDiscoveryPolicy?view=graph-rest-1.0&preserve-view=true)。

*ProxyAddresses* 属性が適用されたユーザーが Microsoft Entra Connect を使用して Microsoft Entra ID に同期されたら、ユーザーがテナントの代替ログイン ID として電子メールでサインインする機能を有効にする必要があります。 この機能は、UPN 値に対してサインイン識別子を確認するだけでなく、電子メール アドレスの *ProxyAddresses* 値に対しても確認するように Microsoft Entra ログイン サーバーに指示します。

1. 管理者として PowerShell セッションを開き、 コマンドレットを使用して `Install-Module` モジュールをインストールします。

    ```powershell
    Install-Module Microsoft.Graph
    ```

    インストールの詳細については、「 [Microsoft Graph PowerShell SDK のインストール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)」を参照してください。
2. `Connect-MgGraph` コマンドレットを使用して、Microsoft Entra テナントにサインインします:

    ```powershell
    Connect-MgGraph -Scopes "Policy.ReadWrite.ApplicationConfiguration" -TenantId organizations
    ```

    このコマンドにより、Web ブラウザーを使用して認証するよう求められます。
3. 次のように、 コマンドレットを使用して、テナントに `Get-MgPolicyHomeRealmDiscoveryPolicy` が既に存在するかどうかを確認します。

    ```powershell
    Get-MgPolicyHomeRealmDiscoveryPolicy
    ```
4. 現在構成されているポリシーがない場合、コマンドは何も返しません。 ポリシーが返された場合は、この手順をスキップし、次の手順に進んで、既存のポリシーを更新します。

    *HomeRealmDiscoveryPolicy を*テナントに追加するには、`New-MgPolicyHomeRealmDiscoveryPolicy` コマンドレットを使用し、*AlternateIdLogin* 属性を *"Enabled" に設定します。次*の例に示すように true。

    ```powershell
    $AzureADPolicyDefinition = @(
      @{
         "HomeRealmDiscoveryPolicy" = @{
            "AlternateIdLogin" = @{
               "Enabled" = $true
            }
         }
      } | ConvertTo-JSON -Compress
    )
    
    $AzureADPolicyParameters = @{
      Definition            = $AzureADPolicyDefinition
      DisplayName           = "BasicAutoAccelerationPolicy"
      AdditionalProperties  = @{ IsOrganizationDefault = $true }
    }
    
    New-MgPolicyHomeRealmDiscoveryPolicy @AzureADPolicyParameters
    ```

    ポリシーが正常に作成されると、次の出力例に示すように、コマンドによってポリシー ID が返されます。

    ```powershell
    Definition                                                           DeletedDateTime Description DisplayName                 Id            IsOrganizationDefault
    ----------                                                           --------------- ----------- -----------                 --            ---------------------
    {{"HomeRealmDiscoveryPolicy":{"AlternateIdLogin":{"Enabled":true}}}}                             BasicAutoAccelerationPolicy HRD_POLICY_ID True
    ```
5. 構成済みのポリシーが既にある場合は、次のポリシー出力例に示すように、 *AlternateIdLogin* 属性が有効になっているかどうかを確認します。

    ```powershell
    Definition                                                           DeletedDateTime Description DisplayName                 Id            IsOrganizationDefault
    ----------                                                           --------------- ----------- -----------                 --            ---------------------
    {{"HomeRealmDiscoveryPolicy":{"AlternateIdLogin":{"Enabled":true}}}}                             BasicAutoAccelerationPolicy HRD_POLICY_ID True
    ```

    ポリシーが存在するが *、AlternateIdLogin* 属性が存在しないか有効になっていない場合、または保持するポリシーに他の属性が存在する場合は、 `Update-MgPolicyHomeRealmDiscoveryPolicy` コマンドレットを使用して既存のポリシーを更新します。

    重要

    ポリシーを更新するときは、古い設定と新しい *AlternateIdLogin* 属性が含まれていることを確認します。

    次の例では、 *AlternateIdLogin* 属性を追加し、以前に設定した *AllowCloudPasswordValidation* 属性を保持します。

    ```powershell
    $AzureADPolicyDefinition = @(
      @{
         "HomeRealmDiscoveryPolicy" = @{
            "AllowCloudPasswordValidation" = $true
            "AlternateIdLogin" = @{
               "Enabled" = $true
            }
         }
      } | ConvertTo-JSON -Compress
    )
    
    $AzureADPolicyParameters = @{
      HomeRealmDiscoveryPolicyId = "HRD_POLICY_ID"
      Definition                 = $AzureADPolicyDefinition
      DisplayName                = "BasicAutoAccelerationPolicy"
      AdditionalProperties       = @{ "IsOrganizationDefault" = $true }
    }
    
    Update-MgPolicyHomeRealmDiscoveryPolicy @AzureADPolicyParameters
    ```

    更新されたポリシーに変更内容が表示され *、AlternateIdLogin* 属性が有効になっていることを確認します。

    ```powershell
    Get-MgPolicyHomeRealmDiscoveryPolicy
    ```

注

ポリシーが適用されている場合は、伝達されて、ユーザーが代替ログイン ID としてメールを使用してサインインできるようになるまでに、最大 1 時間かかることがあります。

#### ポリシーの削除

HRD ポリシーを削除するには、`Remove-MgPolicyHomeRealmDiscoveryPolicy` コマンドレットを使用します。

```powershell
Remove-MgPolicyHomeRealmDiscoveryPolicy -HomeRealmDiscoveryPolicyId "HRD_POLICY_ID"
```

### メール アドレスを使用したユーザー サインインをテストするために段階的なロールアウトを有効にする

注

この構成オプションには、段階的ロールアウト ポリシーを使用します。 詳細については、「 [featureRolloutPolicy リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/featurerolloutpolicy)」を参照してください。

段階的なロールアウト ポリシーを使用すると、テナント管理者は特定の Microsoft Entraグループに対して各機能を有効にすることができます。 テナント管理者が、段階的なロールアウトを使用して、メール アドレスでのユーザー サインインをテストすることをお勧めします。 管理者がこの機能をテナント全体に展開する準備ができたら、 HRD ポリシーを使用する必要があります。

1. 管理者として PowerShell セッションを開き、*Install-Module* コマンドレットを使用して [Microsoft.Graph.Beta](https://learn.microsoft.com/ja-jp/powershell/module/powershellget/install-module) モジュールをインストールします。

    ```powershell
    Install-Module Microsoft.Graph.Beta
    ```

    メッセージが表示されたら、 **Y** を選択して NuGet をインストールするか、信頼されていないリポジトリからインストールします。
2. [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph) コマンドレットを使用して Microsoft Entra テナントにサインインします。

    ```powershell
    Connect-MgGraph -Scopes "Directory.ReadWrite.All"
    ```

    このコマンドは、アカウント、環境、およびテナント ID に関する情報を返します。
3. 次のコマンドレットを使用して、既存の段階的なロールアウト ポリシーをすべて一覧表示します。

    ```powershell
    Get-MgBetaPolicyFeatureRolloutPolicy
    ```
4. この機能について既存の段階的なロールアウト ポリシーが存在しない場合は、段階的ロールアウト ポリシーを新たに作成し、ポリシー ID を書き留めておきます。

    ```powershell
    $MgPolicyFeatureRolloutPolicy = @{
    Feature    = "EmailAsAlternateId"
    DisplayName = "EmailAsAlternateId Rollout Policy"
    IsEnabled   = $true
    }
    New-MgBetaPolicyFeatureRolloutPolicy @MgPolicyFeatureRolloutPolicy
    ```
5. 段階的なロールアウト ポリシーに追加されるグループの directoryObject ID を検索します。 次の手順で使用するため、 *Id* パラメーターに返される値に注意してください。

    ```powershell
    Get-MgBetaGroup -Filter "DisplayName eq 'Name of group to be added to the staged rollout policy'"
    ```
6. 次の例に示すように、グループを段階的なロールアウト ポリシーに追加します。 *-FeatureRolloutPolicyId* パラメーターの値を、手順 4 でポリシー ID に返された値に置き換え*、-OdataId* パラメーターの値を手順 5 で示した *ID* に置き換えます。 グループ内のユーザーが代替ログイン ID としてメール アドレスを使用して Microsoft Entra ID にサインインできるようになるまで、最大 1 時間かかることがあります。

    ```powershell
    New-MgBetaDirectoryFeatureRolloutPolicyApplyToByRef `
       -FeatureRolloutPolicyId "ROLLOUT_POLICY_ID" `
       -OdataId "https://graph.microsoft.com/v1.0/directoryObjects/{GROUP_OBJECT_ID}"
    ```

新しいメンバーをグループに追加した場合、代替ログイン ID としてメール アドレスを使用して Microsoft Entra ID にサインインできるようになるまで、最大で 24 時間かかることがあります。

#### グループの削除

段階的なロールアウト ポリシーからグループを削除するには、次のコマンドを実行します。

```powershell
Remove-MgBetaPolicyFeatureRolloutPolicyApplyToByRef -FeatureRolloutPolicyId "ROLLOUT_POLICY_ID" -DirectoryObjectId "GROUP_OBJECT_ID"
```

#### ポリシーの削除

段階的なロールアウト ポリシーを削除するには、まずポリシーを無効にして、システムから削除します。

```powershell
Update-MgBetaPolicyFeatureRolloutPolicy -FeatureRolloutPolicyId "ROLLOUT_POLICY_ID" -IsEnabled:$false 
Remove-MgBetaPolicyFeatureRolloutPolicy -FeatureRolloutPolicyId "ROLLOUT_POLICY_ID"
```

### メール アドレスを使用してユーザーのサインインをテストする

ユーザーがメール アドレスを使用してサインインできるかどうかをテストするには、https://myprofile.microsoft.com に移動し、UPN 以外のメール アドレス (`balas@fabrikam.com` など) を使用してサインインします。 サインイン エクスペリエンスは、UPN を使用したサインインと同じ外観になります。

### トラブルシューティング

ユーザーがメール アドレスを使用してサインインするときに問題が発生した場合は、次のトラブルシューティングの手順を確認してください。

1. メール アドレスが代替ログイン ID として有効になってから少なくとも 1 時間が経過していることを確認します。 段階的ロールアウト ポリシーの適用対象であるグループにユーザーが最近追加された場合は、グループに追加されてから少なくとも 24 時間が経過していることを確認します。
2. HRD ポリシーを使用している場合は、Microsoft Entra ID *HomeRealmDiscoveryPolicy* の *AlternateIdLogin* 定義プロパティが *"Enabled": true に* 設定され、 *IsOrganizationDefault プロパティが* *True* に設定されていることを確認します。

    ```powershell
    Get-MgBetaPolicyHomeRealmDiscoveryPolicy | Format-List *
    ```

    段階的ロールアウト ポリシーを使用している場合は、Microsoft Entra ID *FeatureRolloutPolicy* に *IsEnabled* プロパティが *True* に設定されていることを確認します。

    ```powershell
    Get-MgBetaPolicyFeatureRolloutPolicy
    ```
3. ユーザー アカウントのメール アドレスが、Microsoft Entra ID の *ProxyAddresses* 属性に設定されていることを確認します。

#### サインイン ログ

[Image: 代替ログイン ID アクティビティとして電子メールを示す Microsoft Entra サインイン ログのスクリーンショット。]

詳細については、 [Microsoft Entra ID のサインイン ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins) を確認できます。 代替ログイン ID として電子メールを使用したサインインでは、[`proxyAddress`] フィールドにが出力され、[*サインイン識別子*] フィールドに入力されたユーザー名が出力されます。

#### クラウド専用ユーザーと同期されたユーザーの間で競合する値

テナント内では、クラウド専用ユーザーの UPN が、オンプレミス ディレクトリから同期された別のユーザーのプロキシ アドレスと同じ値になる場合があります。 このシナリオでは、機能を有効にすると、クラウド専用のユーザーは UPN でサインインできなくなります。 この問題のインスタンスを検出する手順を次に示します。

1. 管理者として PowerShell セッションを開き、 [Install-Module](https://learn.microsoft.com/ja-jp/powershell/module/powershellget/install-module) コマンドレットを使用して Microsoft Graph をインストールします。

    ```powershell
    Install-Module Microsoft.Graph.Authentication
    ```

    メッセージが表示されたら、 **Y** を選択して NuGet をインストールするか、信頼されていないリポジトリからインストールします。
2. Microsoft Graph に接続する

    ```powershell
    Connect-MgGraph -Scopes "User.Read.All"
    ```
3. 影響を受けるユーザーを取得します。

    ```powershell
    # Get all users
    $allUsers = Get-MgUser -All
    
    # Get list of proxy addresses from all synced users
    $syncedProxyAddresses = $allUsers |
        Where-Object {$_.ImmutableId} |
        Select-Object -ExpandProperty ProxyAddresses |
        ForEach-Object {$_ -Replace "smtp:", ""}
    
    # Get list of user principal names from all cloud-only users
    $cloudOnlyUserPrincipalNames = $allUsers |
        Where-Object {!$_.ImmutableId} |
        Select-Object -ExpandProperty UserPrincipalName
    
    # Get intersection of two lists
    $duplicateValues = $syncedProxyAddresses |
        Where-Object {$cloudOnlyUserPrincipalNames -Contains $_}
    ```
4. 影響を受けるユーザーを出力するには:

    ```powershell
    # Output affected synced users
    $allUsers |
        Where-Object {$_.ImmutableId -And ($_.ProxyAddresses | Where-Object {($duplicateValues | ForEach-Object {"smtp:$_"}) -Contains $_}).Length -GT 0} |
        Select-Object ObjectId, DisplayName, UserPrincipalName, ProxyAddresses, ImmutableId, UserType
    
    # Output affected cloud-only users
    $allUsers |
        Where-Object {!$_.ImmutableId -And $duplicateValues -Contains $_.UserPrincipalName} |
        Select-Object ObjectId, DisplayName, UserPrincipalName, ProxyAddresses, ImmutableId, UserType
    ```
5. 影響を受けるユーザーを CSV に出力するには:

    ```powershell
    # Output affected users to CSV
    $allUsers |
        Where-Object {
            ($_.ImmutableId -And ($_.ProxyAddresses | Where-Object {($duplicateValues | ForEach-Object {"smtp:$_"}) -Contains $_}).Length -GT 0) -Or
            (!$_.ImmutableId -And $duplicateValues -Contains $_.UserPrincipalName)
        } |
        Select-Object ObjectId, DisplayName, UserPrincipalName, @{n="ProxyAddresses"; e={$_.ProxyAddresses -Join ','}}, @{n="IsSyncedUser"; e={$_.ImmutableId.Length -GT 0}}, UserType |
        Export-Csv -Path .\AffectedUsers.csv -NoTypeInformation
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-mfa-adfs"} -->
## Microsoft Entra 多要素認証と ADFS を使用してリソースをセキュリティで保護する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-adfs
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: これは、クラウドでの Microsoft Entra 多要素認証と AD FS の使用を開始する方法を説明する Microsoft Entra 多要素認証ページです。

組織が Microsoft Entra ID とフェデレーションされている場合は、Microsoft Entra 多要素認証または Active Directory フェデレーション サービス (AD FS) を使用して、Microsoft Entra ID によってアクセスされるリソースをセキュリティで保護します。 Microsoft Entra 多要素認証または Active Directory フェデレーション サービス (ADFS) を使用して Microsoft Entra リソースをセキュリティで保護するには、次の手順に従います。

注

ドメイン設定 [federatedIdpMfaBehavior](https://learn.microsoft.com/ja-jp/graph/api/resources/internaldomainfederation?view=graph-rest-beta&preserve-view=true#federatedidpmfabehavior-values) を `enforceMfaByFederatedIdp` に設定するか (推奨)、**SupportsMFA** を `$True` に設定します。 両方が設定されている場合、**federatedIdpMfaBehavior** 設定によって **SupportsMFA** がオーバーライドされます。

### AD FS を使用して Microsoft Entra リソースをセキュリティで確保する

クラウド リソースをセキュリティで保護するには、ユーザーが 2 段階認証の実行に成功したときに、Active Directory フェデレーション サービスが multipleauthn 要求を出力するよう要求規則を設定します。 この要求は、Microsoft Entra ID に渡されます。 以下では、その手順を説明します。

1. AD FS 管理を開きます。
2. 左側で、 **[証明書利用者信頼]** を選択します。
3. **Microsoft Office 365 ID プラットフォーム**を右選択し、[**要求規則の編集]** を選択します。

    [Image: ADFS コンソール - 証明書利用者信頼]
4. [発行変換規則] で、**[規則の追加]** を選択します。

    [Image: 発行変換規則の編集]
5. 変換要求規則の追加ウィザードで、ドロップダウンから **[パススルー] または [受信要求のフィルター処理** ] を選択し、[ **次へ**] を選択します。

    [Image: 変換要求規則の追加ウィザードを示すスクリーンショット。ここでは、要求規則テンプレートを選択できます。]
6. 規則に名前を付けます。
7. 受信要求の種類として **[認証方法の参照]** を選択します。
8. **[すべての要求値をパススルーする]** を選択します。

    [Image: 変換要求規則の追加ウィザードを示すスクリーンショット。ここでは、[すべての要求値をパススルーする] を選択できます。]
9. **完了** を選択します。 AD FS 管理コンソールを閉じます。

### フェデレーション ユーザー用の信頼できる IP

管理者は信頼できる IP を使用すると、特定の IP アドレス、またはイントラネット内から要求が送信されているフェデレーション ユーザーの 2 段階認証をバイパスできます。 次のセクションでは、信頼できる IP を使用してバイパスを構成する方法について説明します。 これは、要求の種類 [企業ネットワーク内] で [入力方向の要求をパススルーまたはフィルター処理] テンプレートを使用するように AD FS を構成することによって実現されます。

ここで示す例では、証明書利用者信頼で Microsoft 365 を使用します。

#### AD FS 要求規則を構成する

最初に実行する必要があるのは、AD FS の要求を構成することです。 2 つの要求規則を作成します。1 つは [企業ネットワーク内] という要求の種類用であり、もう 1 つはユーザーのサインイン状態を維持するためのものです。

1. AD FS 管理を開きます。
2. 左側で、 **[証明書利用者信頼]** を選択します。
3. **Microsoft Office 365 ID プラットフォーム**を右選択し、[**要求規則の編集]を選択します。..**

    [Image: ADFS コンソール - 要求規則の編集]
4. [発行変換規則] で、**[規則の追加]** を選択します。

    [Image: 要求規則の追加]
5. 変換要求規則の追加ウィザードで、ドロップダウンから **[パススルー] または [受信要求のフィルター処理** ] を選択し、[ **次へ**] を選択します。

    [Image: 変換要求規則の追加ウィザードを示すスクリーンショット。ここでは、[入力方向の要求をパススルーまたはフィルター処理] を選択できます。]
6. [要求規則名] の横にあるボックスに、規則の名前を入力します。 次に例を示します。InsideCorpNet。
7. [入力方向の要求の種類] の横にあるドロップダウンから、 **[企業ネットワーク内]** を選択します。

    [Image: 企業ネットワーク内要求の追加]
8. **完了** を選択します。
9. [発行変換規則] で、**[規則の追加]** を選択します。
10. 変換要求規則の追加ウィザードで、ドロップダウンから **[カスタム規則を使用して要求を送信** ] を選択し、[ **次へ**] を選択します。
11. [要求規則名] の下のボックスに 「*Keep Users Signed In*」(ユーザーをサインインしたままにする) と入力します。
12. [カスタム規則:] ボックスに次のように入力します。

    ```ad
        c:[Type == "https://schemas.microsoft.com/2014/03/psso"]
            => issue(claim = c); 
    ```

    [Image: ユーザーをサインインしたままにするカスタム要求を作成する]
13. **完了** を選択します。
14. **を選択して**を適用します。
15. **OK** を選択します。
16. AD FS 管理を閉じます。

#### フェデレーション ユーザーを使用して Microsoft Entra 多要素認証の信頼できる IP を構成する

これで要求が準備できたので、信頼できる IP を構成できます。

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **条件付きアクセス**&gt;**名前付きの場所**を参照します。
3. **[条件付きアクセス - ネームド ロケーション]** ブレードから **[MFA の信頼できる IP の構成]** を選択します。

    [Image: [Microsoft Entra 条件付きアクセス] のネームド ロケーションの [MFA の信頼できる IP の構成]]
4. [サービス設定] ページの **[信頼できる IP]** で、**[イントラネット内のフェデレーション ユーザーからのリクエストの場合、多要素認証をスキップする]** を選択します。
5. **[保存]** を選択します。

それです！ この時点で、Microsoft 365 のフェデレーション ユーザーは、企業のイントラネットの外部から要求を送信するときに、MFA のみを使用するだけですみます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-mfa-app-passwords"} -->
## Microsoft Entra 多要素認証のアプリ パスワードを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-app-passwords
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra 多要素認証でレガシ アプリケーションのアプリ パスワードを構成して使用する方法について説明します

一部の古い非ブラウザー アプリ (Office 2010 以前や iOS 11 より前の Apple Mail など) では、認証プロセスの一時停止や中断が認識されません。 これらの古い非ブラウザー アプリのいずれかにサインインしようとする Microsoft Entra 多要素認証 (多要素認証の Microsoft Entra) ユーザーは、正常に認証できません。 ユーザー アカウントに対して適用されている Microsoft Entra 多要素認証を使用した安全な方法でこれらのアプリケーションを使用するには、アプリ パスワードを使用できます。 これらのアプリ パスワードは従来のパスワードを置き換え、アプリは多要素認証をバイパスして正常に動作できるようになります。

Microsoft Office 2013 クライアント以降向けの最新の認証がサポートされています。 Office 2013 クライアント (Outlook を含む) は、最新の認証プロトコルをサポートしており、2 段階認証で正しく機能します。 Microsoft Entra 多要素認証を適用した後は、クライアントにアプリ パスワードは必要ありません。

この記事では、多要素認証プロンプトをサポートしていないレガシ アプリケーションで、アプリ パスワードを使用する方法について説明します。

注

アプリ パスワードは、先進認証を使用するように要求されるアカウントでは機能しません。

### 概要と考慮事項

ユーザー アカウントに Microsoft Entra 多要素認証が適用されると、通常のサインイン プロンプトが追加の検証の要求によっ、中断されます。 一部の古いアプリケーションでは、サインイン プロセスのこの中断を認識しないため、認証が失敗します。 ユーザー アカウントのセキュリティを維持し、Microsoft Entra 多要素認証が適用されたままにするには、ユーザーの通常のユーザー名とパスワードの代わりにアプリ パスワードを使用できます。 サインイン時にアプリ パスワードを使用すると、追加の検証プロンプトが表示されず、認証が成功します。

アプリ パスワードは自動的に生成され、ユーザーが指定しません。 この自動的に生成されたパスワードの方が攻撃者から推測されづらく、より安全です。 アプリ パスワードはアプリケーションごとに 1 回入力するだけであるため、ユーザーはパスワードを追跡したり、毎回それらを入力したりする必要がありません。

アプリ パスワードを使用する場合は、次の考慮事項が適用されます。

- ユーザー 1 人あたりのアプリ パスワード数の上限は 40 個である。
- パスワードをキャッシュし、オンプレミス シナリオで使用しているアプリケーションでは、職場または学校アカウント以外で、アプリ パスワードが不明であるため、失敗する可能性があります。 このシナリオの例として、Exchangeのメールがオンプレミスであるが、そのアーカイブメールがクラウドにある場合があります。 このシナリオでは、同じパスワードは機能しません。
- ユーザーのアカウントに Microsoft Entra 多要素認証が適用された後は、Outlook や Microsoft Skype for Business などのほとんどの非ブラウザー クライアントでアプリ パスワードを使用できます。 ただし、Windows PowerShell などの非ブラウザー アプリケーションからアプリ パスワードを使用して、管理操作を実行することはできません。 ユーザーが管理者アカウントを持っている場合でも、操作を実行することはできません。
    - PowerShell スクリプトを実行するには、サービス アカウントを強固なパスワードで作成します。そのアカウントで 2 段階認証を適用しないでください。
- ユーザー アカウントが侵害された疑いがあり、アカウント パスワードを取り消すか、またはリセットした場合は、アプリ パスワードも更新する必要があります。 ユーザー アカウントのパスワードが取り消されるか、またはリセットされても、アプリ パスワードは自動的には取り消されません。 ユーザーは既存のアプリ パスワードを削除し、新しいアプリ パスワードを作成する必要があります。
    - 詳細については、「[\[追加のセキュリティ確認\] ページを使用してアプリ パスワードを作成および削除する](https://support.microsoft.com/account-billing/manage-app-passwords-for-two-step-verification-d6dc8c6d-4bf7-4851-ad95-6d07799387e9#create-and-delete-app-passwords-from-the-additional-security-verification-page)」を参照してください。

警告

クライアントがオンプレミスの自動検出エンドポイントとクラウドの自動検出エンドポイントの両方と通信するハイブリッド環境では、アプリ パスワードは機能しません。 オンプレミスでの認証にはドメイン パスワードが必要です。 クラウドでの認証にはアプリ パスワードが必要です。

#### アプリ パスワード名

アプリ パスワードの名前は、それが使用されるデバイスを反映させるようにします。 Outlook、Word、Excel などのブラウザー以外のアプリケーションがあるラップトップでは、これらのアプリに対して **Laptop** という名前のアプリ パスワードを 1 つ作成します。 デスクトップ コンピューター上で実行される同じアプリケーションに対しては、**Desktop** という名前の別のアプリ パスワードを作成します。

アプリケーションごとに 1 つのアプリ パスワードではなく、デバイスごとに 1 つのアプリ パスワードを作成することをお勧めします。

### フェデレーション (シングル サインオン) アプリ パスワード

Microsoft Entra ID では、オンプレミスの Active Directory Domain Services (AD DS) とのフェデレーション (シングル サインオン (SSO)) がサポートされています。 組織が Microsoft Entra ID とフェデレーションされており、Microsoft Entra 多要素認証を使用している場合は、次のアプリ パスワードの考慮事項が適用されます。

注

次の点はフェデレーション (SSO) 顧客にのみ適用されます。

- アプリ パスワードは Microsoft Entra ID によって検証されます。したがって、フェデレーションをバイパスします。 フェデレーションは、アプリ パスワードを設定するときにのみアクティブに使用されます。
- フェデレーション (SSO) ユーザーの場合、パッシブ フローとは異なり、ID プロバイダー (IdP) には接続されません。 アプリ パスワードは職場または学校アカウントに格納されます。 ユーザーが退職した場合、そのユーザーの情報は、**DirSync** を使用して、リアルタイムで職場または学校アカウントに送信されます。 アカウントの無効化/削除を同期させるには最大 3 時間かかる場合があり、Microsoft Entra ID 内のアプリ パスワードの無効化または削除が遅れることがあります。
- オンプレミスのクライアント アクセス制御設定は、アプリ パスワード機能には適用されません。
- アプリ パスワード機能で使用できるオンプレミスの認証ログまたは監査機能はありません。

一部の高度なアーキテクチャでは、多要素認証をクライアントで使用するときに資格情報の組み合わせが必要になります。 これらの資格情報には、職場または学校アカウントのユーザー名とパスワード、およびアプリ パスワードを含めることができます。 要件は認証の実行方法によって異なります。 オンプレミスのインフラストラクチャに対して認証するクライアントの場合は、職場または学校アカウントのユーザー名が必要です。 Microsoft Entra ID に対して認証するクライアントの場合は、アプリ パスワードが必要です。

たとえば、次のアーキテクチャがあるとします。

- Active Directory のオンプレミスのインスタンスが Microsoft Entra ID とフェデレーションされている。
- Exchange Online を使用している。
- Skype for Business をオンプレミスで使用している。
- Microsoft Entra 多要素認証を使用している。

このシナリオでは、次の資格情報を使用します。

- Skype for Business にサインインする場合は、職場または学校アカウントのユーザー名とパスワードを使用します。
- Exchange にオンラインで接続している Outlook クライアントからアドレス帳にアクセスする場合は、アプリ パスワードを使用します。

### ユーザーがアプリ パスワードを作成できるようにする

既定では、ユーザーはアプリ パスワードを作成できません。 アプリ パスワード機能を有効にして、ユーザーがそれらを使えるようにする必要があります。 ユーザーがアプリ パスワードを作成できるようにするには、**管理者が次の手順を実行する必要があります**。

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **条件付きアクセス**&gt;**名前付きの場所**を参照します。
3. [条件付きアクセス | ネームド ロケーション] ウィンドウの上部のバーで **[MFA の信頼できる IP の構成]** を選択します。
4. **[多要素認証]** ページで、**[ブラウザーではないアプリケーションへのサインイン用にアプリケーション パスワードの作成を許可する]** オプションを選択します。

    [Image: アプリ パスワードをユーザーに許可するための多要素認証サービス設定を示すスクリーンショット]

注

アプリ パスワードが有効になっている場合、ユーザーは Microsoft Entra 多要素認証登録の一部としてアプリ パスワードを作成する必要があります。

ユーザーがアプリ パスワードを作成できないようにしても、既存のアプリ パスワードを引き続き使用できます。 ただし、この機能を無効にされると、ユーザーはこれらの既存のアプリ パスワードを管理したり削除したりできなくなります。

アプリ パスワードの作成機能を無効にする場合は、[条件付きアクセス ポリシーを作成して、レガシ認証を使用できないようにする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-block-legacy-authentication)こともお勧めします。 この方法を使用すると、既存のアプリ パスワードは機能しなくなり、最新の認証方法が強制的に使用されます。

### アプリケーション パスワードの作成

ユーザーが Microsoft Entra 多要素認証の初期登録を完了すると、登録プロセスの最後にアプリ パスワードを作成するように求められます。

ユーザーによるアプリ パスワードの作成は、登録後も可能です。 詳細情報とユーザー向けの詳細な手順については、次のリソースを参照してください。

- [\[セキュリティ情報\] ページからアプリ パスワードを作成する](https://support.microsoft.com/account-billing/create-app-passwords-from-the-security-info-preview-page-d8bc744a-ce3f-4d4d-89c9-eb38ab9d4137)
<!-- /MSL-PAGE -->
