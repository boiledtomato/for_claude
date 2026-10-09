# Microsoft Learn — Microsoft Entra / 認証 (MFA・パスワードレス・SSPR) (part 3)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 46

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-mfa-getstarted"} -->
## Microsoft Entra 多要素認証デプロイに関する考慮事項 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-getstarted
- Service: entra-id / authentication
- Article date: 2025-11-02
- Summary: Microsoft Entra 多要素認証の実装がうまくいくデプロイに関する考慮事項と戦略について説明します

Microsoft Entra 多要素認証を使用すると、2 つ目の形式の認証を使用して別のセキュリティ層が提供され、データやアプリケーションへのアクセスを保護できます。 組織は、[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)を使用して多要素認証を有効化し、ソリューションを組織の特定のニーズに適合させることができます。

本デプロイ ガイドでは、[Microsoft Entra 多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)のロールアウトのプランニングと実装の方法について説明します。

### Microsoft Entra 多要素認証をデプロイするための前提条件

デプロイを開始する前に、関連するシナリオについて次の前提条件が満たされていることを確認してください。

| シナリオ | 前提条件 |
| --- | --- |
| 先進認証を使用する**クラウド専用**の ID 環境 | **前提となるタスクなし** |
| **ハイブリッド ID** のシナリオ | [Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/whatis-hybrid-identity) をデプロイし、オンプレミスの Active Directory Domain Services (AD DS) と Microsoft Entra ID の間でユーザー ID を同期する。 |
| クラウド アクセス用に公開された**オンプレミスのレガシ アプリケーション** | [Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/conceptual-deployment-plan)をデプロイする |

### MFA の認証方法を選択する

2 つ目の要素の認証に使用できる方法は多数あります。 使用可能な認証方法の一覧から選択して、セキュリティ、使いやすさ、可用性の観点からそれぞれを評価できます。

重要

複数の MFA の方法を有効にして、最初の方法を使用できないときにユーザーがバックアップの方法を使用できるようにします。 次のメソッドがあります。

- [Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-overview)
- [Microsoft Authenticator アプリ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-authenticator-app)
- [FIDO2 セキュリティ キー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)
- [Microsoft Authenticator passkey](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-authenticator-app)
- 同期されたパスキー (プレビュー)
- [ハードウェア OATH トークン (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-oath-tokens#hardware-oath-tokens-preview)
- [ソフトウェア OATH トークン](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-oath-tokens#software-oath-tokens)
- [SMS による認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-phone-options#mobile-phone-verification)
- [音声通話の確認](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-phone-options)

これらの方法の強度とセキュリティ、およびそれらの動作の詳細については、次のリソースを参照してください。

- [Microsoft Entra ID で使用できる認証方法と検証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)
- [ビデオ: 組織の安全を維持するために適切な認証方法を選択する](https://youtu.be/LB2yj4HSptc)

最適な柔軟性と使いやすさを実現するには、Microsoft Authenticator アプリを使用してください。 この認証方法は、パスワードレス、MFA のプッシュ通知、OATH コードなど、最適なユーザー エクスペリエンスと複数のモードを提供します。 Microsoft Authenticator アプリは、米国国立標準技術研究所 (NIST) の [Authenticator Assurance Level 2 の要件](https://learn.microsoft.com/ja-jp/entra/standards/nist-authenticator-assurance-level-2)にも対応しています。

テナントで使用できる認証方法を制御できます。 たとえば、SMS などの安全性の最も低い方法をブロックすることができます。

| 認証方法 | ここから管理する | スコープ設定 |
| --- | --- | --- |
| Microsoft Authenticator (プッシュ通知とパスワードレスの電話によるサインイン) | MFA の設定または認証方法ポリシー | Authenticator のパスワードレスの電話によるサインインのスコープをユーザーとグループに設定できます |
| FIDO2 セキュリティ キー | 認証方法ポリシー | ユーザーとグループにスコープを設定できます |
| ソフトウェアまたはハードウェアの OATH トークン | MFA の設定 |  |
| SMS による認証 | MFA の設定 プライマリ認証の SMS サインインを認証ポリシーで管理します。 | SMS サインインのスコープをユーザーとグループに設定できます。 |
| 音声通話 | 認証方法ポリシー |  |

### 条件付きアクセス ポリシーを計画する

Microsoft Entra 多要素認証は条件付きアクセス ポリシーを使って適用されます。 これらのポリシーを使用すると、セキュリティのために必要な場合はユーザーに MFA を要求し、不要な場合はユーザーに対して何も行わないようにすることができます。

[Image: 概念的な条件付きアクセスのプロセス フロー]

Microsoft Entra 管理センターでは、 **Entra ID**&gt;**Conditional Access** で条件付きアクセス ポリシーを構成します。

条件付きアクセス ポリシーの作成の詳細については、[ユーザーがサインインするときに Microsoft Entra 多要素認証を要求する条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-azure-mfa)に関するページを参照してください。 これにより、以下の知識が得られます。

- ユーザー インターフェイスに慣れる。
- 条件付きアクセスのしくみについて第一印象を得る。

Microsoft Entra の条件付きアクセスのデプロイに関するエンドツーエンドのガイドについては、[条件付きアクセスのデプロイ計画](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/plan-conditional-access)に関するページを参照してください。

#### Microsoft Entra 多要素認証の一般的なポリシー

Microsoft Entra 多要素認証を要求する一般的なユース ケースは次のとおりです。

- [管理者](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-old-require-mfa-admin)のため
- [特定のアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-azure-mfa)用
- [すべてのユーザー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength)に対して
- [Azure の管理](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-old-require-mfa-azure-mgmt)用に
- [信頼されていないネットワークの場所](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength)から

#### 名前付き場所

条件付きアクセス ポリシーを管理するには、条件付きアクセス ポリシーの場所の条件によって、アクセス制御設定をユーザーのネットワークの場所に関連付けることができます。 IP アドレスの範囲または国や地域の論理グループを作成できるように、[ネームド ロケーション](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network)を使用することをお勧めします。 これにより、そのネームド ロケーションからのサインインをブロックする、すべてのアプリ用のポリシーが作成されます。 管理者はこのポリシーから必ず除外してください。

#### リスクベースのポリシー

組織内で [Microsoft Entra ID 保護](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) を使用してリスクの徴候を検出している場合は、ネームド ロケーションではなく[リスクベースのポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)を使用することを検討してください。 ポリシーは、ID 侵害の脅威がある場合にパスワードの変更を強制したり、資格情報の漏洩、匿名 IP アドレスからのサインインなど、サインインが[危険](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)と見なされる場合に MFA を要求したりするために作成することができます。

リスク ポリシーには次のものが含まれます。

- [すべてのユーザーに対して Microsoft Entra 多要素認証への登録を必須にする](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-mfa-policy)
- [リスクの高いユーザーのパスワードの変更を必須にする](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#user-risk-policy-in-conditional-access)
- [サインインのリスクが中以上のユーザーに対して MFA を要求する](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#sign-in-risk-policy-in-conditional-access)

#### ユーザーをユーザーごとの MFA から条件付きアクセス ベースの MFA に変換する

ユーザーが、ユーザーごとの MFA を使用して有効になっており、Microsoft Entra 多要素認証が適用されている場合は、すべてのユーザーに対して条件付きアクセスを有効にして、続いて、ユーザーごとの多要素認証を手動で無効にすることをお勧めします。 詳細については、「[条件付きアクセス ポリシーを作成する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength#create-a-conditional-access-policy)」を参照してください。

### ユーザー セッションの有効期間を計画する

多要素認証のデプロイを計画するときには、ユーザーに入力を求める頻度を検討することが重要です。 ユーザーに認証情報を求めることはしばしば合理的に思えるが、逆効果になる可能性があります。 考えることなしに資格情報を入力するようにユーザーが訓練されている場合、悪意のある資格情報プロンプトに情報を意図せず渡してしまうことがあります。 Microsoft Entra ID には、再認証を必要とする頻度を決定する複数の設定あります。 ビジネスおよびユーザーのニーズを理解し、環境が最適なバランスとなる設定を構成してください。

特定のビジネスのユース ケースでのみサインイン頻度ポリシーを使用して、エンド ユーザーのエクスペリエンスを向上させ、セッションの有効期間を短縮するために、プライマリ更新トークン (PRT) を持つデバイスを使用することをお勧めします。

詳細については、「[再認証プロンプトを最適化し、Microsoft Entra 多要素認証のセッションの有効期間について理解する」](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concepts-azure-multi-factor-authentication-prompts-session-lifetime)を参照してください。

### ユーザーの登録を計画する

多要素認証のすべてのデプロイに共通する重要な手順の一つが、Microsoft Entra 多要素認証を使用できるようユーザーに登録してもらうことです。 音声や SMS などの認証方法では事前登録が可能ですが、Authenticator アプリのような他の認証方法ではユーザーによる操作が必要となります。 管理者は、ユーザーがメソッドを登録する方法を決定する必要があります。

#### SSPR と Microsoft Entra 多要素認証のための統合された登録

[Microsoft Entra 多要素認証とセルフサービス パスワード リセット (SSPR) を組み合わせた登録エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-registration-mfa-sspr-combined)により、ユーザーは MFA と SSPR の両方に統合されたエクスペリエンスで登録できます。 SSPR では、ユーザーは、Microsoft Entra 多要素認証に使用するのと同じ方法を使用して、セキュリティで保護された方法でパスワードをリセットすることができます。 機能とエンド ユーザー エクスペリエンスを確実に理解するには、[統合されたセキュリティ情報の登録の概念](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-registration-mfa-sspr-combined)に関する記事を参照してください。

予定されている変更、登録要件、必要なユーザー操作について、ユーザーに通知することが重要です。 お客様のユーザーに新しいエクスペリエンスに向けて準備をさせ、ロールアウトの確実な成功を支援するために、[通信テンプレート](https://aka.ms/mfatemplates)と[ユーザー ドキュメント](https://support.microsoft.com/account-billing/set-up-security-info-from-a-sign-in-page-28180870-c256-4ebf-8bd7-5335571bf9a8)が用意されています。 ユーザーを https://myprofile.microsoft.com に誘導し、そのページの **[セキュリティ情報]** リンクを選択して登録してもらいます。

#### Microsoft Entra ID 保護への登録

Microsoft Entra ID 保護は、Microsoft Entra 多要素認証スキームに、登録ポリシーと、自動化されたリスクの検出・修復ポリシーの両面で貢献します。 ポリシーは、ID 侵害の脅威がある場合にパスワードの変更を強制したり、サインインにリスクがあると見なされる場合に MFA を要求したりするために作成することができます。 Microsoft Entra ID 保護を使用する場合は、次に対話形式でサインインするときにユーザーに登録を求めるよう、[Microsoft Entra 多要素認証登録ポリシーを構成](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-mfa-policy)してください。

#### Microsoft Entra ID 保護を使用しない登録

Microsoft Entra ID 保護を有効にするライセンスがない場合、ユーザーに対して、次にサインインで MFA が必要となったときに登録を求めます。 ユーザーに MFA を使用するように求めるには、条件付きアクセス ポリシーを使用して、HR システムなどの頻繁に使用されるアプリケーションを対象にすることができます。 ユーザーのパスワードが侵害された場合は、それを MFA の登録のために使用して、ユーザーのアカウントを制御することができます。 そのため、信頼されたデバイスと場所を必要とする[条件付きアクセス ポリシーでセキュリティ登録プロセスを保護する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-security-info-registration)ことをお勧めします。 また、[一時アクセス パス](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass)も要求することにより、このプロセスをさらにセキュリティで保護することができます。 管理者によって発行される期間限定のパスコードは、強力な認証の要件を満たし、パスワードレスの方法を含む他の認証方法をオンボードするために使用できます。

#### 登録されたユーザーのセキュリティを強化する

SMS または音声通話を使用した MFA に登録されているユーザーがいる場合は、Microsoft Authenticator アプリなどのより安全な方法に移行できます。 Microsoft では、ユーザーにサインイン時に Microsoft Authenticator アプリのセットアップを求めることができるようにする機能のパブリック プレビューを提供しています。 これらのセットアップの要請はグループ別に設定して、セットアップを求めるユーザーを制御できます。これにより、対象を限定したキャンペーンでユーザーをより安全な方法に移行できます。

#### 復旧シナリオを計画する

既に説明したように、ユーザーに複数の MFA 方法に登録してもらうことで、1 つの方法が使用できない場合にバックアップ方法で認証できるようにしてください。 ユーザーに使用可能なバックアップの方法がない場合は、次のようにすることができます。

- 独自の認証方法を管理できるように、一時アクセス パスを提供します。 また、リソースに一時的にアクセスできる一時アクセス パスを提供することもできます。
- 管理者として方法を更新します。 これを行うには、Microsoft Entra 管理センターでユーザーを選択し、 **Entra ID**&gt;**Authentication メソッド** を選択し、そのメソッドを更新します。

### オンプレミスのシステムとの統合を計画する

Microsoft Entra ID に対して直接認証を行い、最新の認証 (WS-Fed、SAML、OAuth、OpenID Connect) を使用するアプリケーションでは、条件付きアクセス ポリシーを使用できます。 一部のレガシおよびオンプレミスのアプリケーションでは、Microsoft Entra ID に対して直接認証が行われず、Microsoft Entra 多要素認証を使用するには追加の手順が必要となります。 Microsoft Entra アプリケーション プロキシまたは[ネットワーク ポリシー サービス](https://learn.microsoft.com/ja-jp/windows-server/networking/core-network-guide/core-network-guide#BKMK_optionalfeatures)を使用してそれらを統合することができます。

#### AD FS リソースとの統合

Active Directory フェデレーション サービス (AD FS) でセキュリティ保護されているアプリケーションを Microsoft Entra ID に移行することをお勧めします。 ただし、これらを Microsoft Entra ID に移行する準備ができていない場合は、Azure 多要素認証アダプターを AD FS 2016 以降で使用できます。

組織が Microsoft Entra ID とのフェデレーション認証を採用している場合、オンプレミスとクラウドの両方で、[AD FS リソースを使用した認証プロバイダーとして Microsoft Entra 多要素認証を構成](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configure-ad-fs-and-azure-mfa)できます。

#### RADIUS クライアントと Microsoft Entra 多要素認証

RADIUS 認証を使用しているアプリケーションの場合は、クライアント アプリケーションを最新のプロトコル (SAML、Open ID Connect、Microsoft Entra ID 上の OAuth など) に移行することをお勧めします。 アプリケーションを更新できない場合は、[ネットワーク ポリシー サーバー (NPS) 拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-nps-extension)を展開できます。 ネットワーク ポリシー サーバー (NPS) 拡張機能は、RADIUS ベースのアプリケーションと Microsoft Entra 多要素認証をつなぐアダプターとなり、2 番目の認証要素として機能します。

##### 一般的な統合

多くのベンダーでアプリケーションの SAML 認証がサポートされるようになりました。 可能な場合は、これらのアプリケーションを Microsoft Entra ID とフェデレーションし、条件付きアクセスを使用して MFA を適用することをお勧めします。 ベンダーが先進認証をサポートしていない場合は、NPS 拡張機能を使用できます。 一般的な RADIUS クライアントの統合には、[リモート デスクトップ ゲートウェイ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-nps-extension-rdg)や [VPN サーバー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-nps-extension-vpn)などのアプリケーションが含まれます。

他には次のものが含まれる場合があります:

- Citrix ゲートウェイ

    [Citrix Gateway](https://docs.citrix.com/en-us/advanced-concepts/implementation-guides/citrix-gateway-microsoft-azure.html#microsoft-azure-mfa-deployment-methods) では、RADIUS と NPS の両方の拡張機能の統合と、SAML 統合がサポートされています。
- シスコVPN

    - Cisco VPN では、[SSO の RADIUS 認証と SAML 認証](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cisco-secure-firewall-secure-client)の両方がサポートされています。
    - RADIUS 認証から SAML に移行すると、NPS 拡張機能をデプロイすることなく、Cisco VPN を統合できます。
- すべての VPN

### Microsoft Entra 多要素認証をデプロイする

Microsoft Entra 多要素認証のロールアウト計画には、パイロット デプロイと、それに続くサポート キャパシティ内でのデプロイ ウェーブが含まれる必要があります。 条件付きアクセス ポリシーをパイロット ユーザーの小さなグループに適用することによってロールアウトを開始します。 パイロット ユーザーへの影響、使用されているプロセス、および登録動作を評価した後、ポリシーに新しいグループを追加するか、既存のグループにユーザーを追加するかしてロールアウトを進めてください。

次のステップを実行します。

1. 必要な前提条件を満たす
2. 選択した認証方法を構成します
3. 条件付きアクセス ポリシーを構成します
4. セッションの有効期間設定を構成します
5. Microsoft Entra 多要素認証の登録ポリシーを構成する

### Microsoft Entra 多要素認証を管理する

このセクションでは、Microsoft Entra 多要素認証のレポートとトラブルシューティングに関する情報を提供します。

#### レポートと監視

Microsoft Entra ID には、技術的な分析情報とビジネス上の分析情報の提供、デプロイの進行状況の追跡、ユーザーが MFA でのサインインに成功したかどうかの確認を行うためのレポートがあります。 ビジネスおよび技術アプリケーションの所有者に、組織の要件に基づいてこれらのレポートの所有権を引き受けさせ、レポートを使用させます。

[認証方法アクティビティのダッシュボード](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-methods-activity)を使用すると、組織全体での認証方法の登録と使用を監視できます。 これにより、登録中の方法や各方法の使用状況を把握できます。

##### サインイン ログを使用して MFA イベントを確認する

Microsoft Entra サインイン ログには、ユーザーが MFA を求められた場合や、条件付きアクセス ポリシーが使用されていた場合のイベントの認証の詳細が含まれます。

クラウド MFA アクティビティの NPS 拡張機能と AD FS ログが [サインイン ログに](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)含まれるようになり、 **アクティビティ レポート**に発行されなくなります。

詳細と追加の Microsoft Entra 多要素認証レポートが必要な場合、「[Microsoft Entra 多要素認証イベントのレビュー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-reporting#view-the-azure-ad-sign-ins-report)」を参照してください。

#### Microsoft Entra 多要素認証のトラブルシューティング

一般的な問題については、「[Microsoft Entra 多要素認証のトラブルシューティング](https://support.microsoft.com/help/2937344/troubleshooting-azure-multi-factor-authentication-issues)」を参照してください。

### ガイド付きウォークスルー

この記事内の多くの推奨事項に関するガイド付きチュートリアルについては、[Microsoft 365 多要素認証の構成のガイド付きチュートリアル](https://go.microsoft.com/fwlink/?linkid=2221401)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-mfa-mfasettings"} -->
## Microsoft Entra の多要素認証を設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-mfasettings
- Service: entra-id / authentication
- Article date: 2026-02-27
- Summary: Microsoft Entra多要素認証の設定を構成する方法について説明します

Microsoft Entra多要素認証 (MFA) のエンド ユーザー エクスペリエンスをカスタマイズするには、疑わしいアクティビティを報告するためのオプションを構成できます。 次の表では、Microsoft Entra MFA の設定について説明し、サブセクションでは各設定について詳しく説明します。

Note

疑わしいアクティビティの報告は、ユーザーのブロック/ブロック解除、不正アクセスのアラート、通知などのレガシ機能に代わるものです。 2025 年 3 月 1 日に、レガシ機能が削除されました。

| Feature | Description |
| --- | --- |
| 疑わしいアクティビティを報告する | ユーザーが不正な確認要求を通報できるようにする設定を構成します。 |
| [OATH トークン](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-oath-tokens) | クラウドベースのMicrosoft Entra MFA 環境で使用され、ユーザーの OATH トークンを管理します。 |
| 電話の設定 | クラウド環境とオンプレミス環境の電話と案内メッセージに関連する設定を構成します。 |
| Providers | アカウントに関連付けた既存の認証プロバイダーが表示されます。 2018 年 9 月 1 日より、新しいプロバイダーの追加は無効になっています。 |

### 疑わしいアクティビティを報告する

不明で疑わしい MFA プロンプトを受信すると、ユーザーはMicrosoft Authenticatorを使用するか、電話でアクティビティを報告できます。 **Report suspicious activity** は、リスクに基づく修復、レポート、最小特権管理のために、[Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) と統合されています。

MFA プロンプトを疑わしいと報告したユーザーは、 **高いユーザー リスク**に設定されます。 管理者は、リスクベースのポリシーを使用して、これらのユーザーのアクセスを制限したり、ユーザーが自分で問題を修復するためのセルフサービス パスワード リセット (SSPR) を有効にしたりすることができます。

リスクベースのポリシーに対する Microsoft Entra ID P2 ライセンスがない場合は、リスク検出イベントを使用して、影響を受けるユーザーを手動で識別して無効にするか、Microsoft Graphでカスタム ワークフローを使用して自動化を設定できます。 ユーザー リスクの調査と修復について詳しくは、以下をご覧ください。

- [方法: リスクを調査する](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)
- [方法: リスクを修復し、ユーザーのブロックを解除する](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock)

認証方法ポリシー**設定**から**疑わしいアクティビティを報告**できるようにするには:

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)としてサインインします。
2. **Entra ID**&gt;**認証メソッド**&gt;**設定**に移動します。
3. [**疑わしいアクティビティの報告** **] を [有効]** に設定します。 **Microsoft マネージド**を選択した場合、この機能は無効のままです。 Microsoft のマネージド値の詳細については、「Microsoft Entra IDを参照してください。 [Image: [疑わしいアクティビティの報告] を有効にする方法のスクリーンショット。]
4. **[すべてのユーザー**] または特定のグループを選択します。
5. テナントのカスタム 案内応答もアップロードする場合は、 **レポート コードを**選択します。 レポート コードは、疑わしいアクティビティを報告するためにユーザーが自分の電話に入力する数字です。 レポート コードは、カスタム 案内応答も [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)によってアップロードされる場合にのみ適用されます。 それ以外の場合、ポリシーで指定された値に関係なく、既定のコードは 0 です。
6. **[保存]** をクリックします。

#### Microsoft Entra ID P1 ライセンスを持つテナントのリスクの修復

ユーザーが MFA プロンプトを疑わしいと報告すると、イベントはサインイン ログ (ユーザーによって拒否されたサインインとして)、監査ログ、およびリスク検出レポートに表示されます。

| レポート | 管理センター | Details |
| --- | --- | --- |
| リスク検出レポート | **ID 保護**&gt;**ダッシュボード**&gt;**リスク検出** | 検出の種類: **ユーザーが報告した疑わしいアクティビティ**リスク レベル: **高**ソース **エンド ユーザーの報告** |
| サインイン ログ | **Entra ID**&gt;**監視と正常性**&gt;**サインイン ログ**&gt;**認証の詳細** | 結果の詳細が **MFA 拒否**として表示される |
| 監査ログ | **Entra ID**&gt;**監視と正常性**&gt;**監査ログ** | 不審なアクティビティが [**アクティビティの種類**] に表示される |

Note

ユーザーがパスワードレス認証を実行する場合、そのユーザーは高リスクとして報告されません。

また、Microsoft Graphを使用して、リスク検出とリスクのフラグが設定されたユーザーのクエリを実行することもできます。

| API | Detail |
| --- | --- |
| [riskDetection リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/riskdetection) | リスクイベントタイプ: `userReportedSuspiciousActivity` |
| [riskyUsers を一覧表示する](https://learn.microsoft.com/ja-jp/graph/api/riskyuser-list) | riskLevel = **高い** |

手動で修復を行う場合、管理者またはヘルプデスクは、セルフサービス パスワード リセット (SSPR) を使用してパスワードをリセットするようユーザーに依頼するか、ユーザーの代わりにパスワードをリセットすることができます。 自動修復を行うには、Microsoft Graph API を使用するか、PowerShell を使用して、ユーザーのパスワードの変更、SSPR の強制、サインイン セッションの取り消し、またはユーザー アカウントの一時的な無効化を行うスクリプトを作成します。

#### Microsoft Entra ID P2 ライセンスを持つテナントのリスクの修復

Microsoft Entra ID P2 ライセンスを持つテナントでは、P2 ライセンスMicrosoft Entra IDオプションに加えて、リスクベースの条件付きアクセス ポリシーを使用してユーザー リスクを自動的に修復できます。

**条件**&gt;**ユーザー リスク**の下でユーザー リスクを確認するポリシーを構成します。 リスクが高のユーザーを探して、サインインをブロックするか、パスワードのリセットを要求します。

[Image: リスクベースの条件付きアクセス ポリシーを有効にする方法のスクリーンショット。]

詳細については、「 [サインイン リスクベースの条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-policies#sign-in-risk-based-conditional-access-policy)」を参照してください。

### OATH トークン

Microsoft Entra IDでは、30 秒または 60 秒ごとにコードを更新する OATH TOTP SHA-1 トークンの使用がサポートされています。 これらのトークンは、選択したベンダーから購入できます。

OATH TOTP ハードウェア トークンには、通常、トークンで事前にプログラミングされた秘密鍵 (シード) が付属しています。 次の手順で説明するように、これらのキーをMicrosoft Entra IDに入力する必要があります。 秘密鍵は 128 文字に制限されていて、すべてのトークンと互換性があるとは限りません。 秘密鍵には、 *a から z* または *A から Z* の文字と *1 から 7* の数字のみを含めることができます。 Base32 でエンコードする必要があります。

再シード可能なプログラム可能な OATH TOTP ハードウェア トークンは、ソフトウェア トークンのセットアップ フローでMicrosoft Entra IDを使用して設定することもできます。

OATH ハードウェア トークンはパブリック プレビュー段階でサポートされています。 プレビューの詳細については、「Microsoft Azure プレビューの[使用条件](https://aka.ms/EntraPreviewsTermsOfUse)を参照してください。

[Image: OATH トークン セクションを示すスクリーンショット。]

トークンを取得した後は、コンマ区切り値 (CSV) ファイル形式でアップロードする必要があります。 次の例に示すように、UPN、シリアル番号、秘密鍵、期間、製造元、モデルを含めてください。

```csv
upn,serial number,secret key,time interval,manufacturer,model
Helga@contoso.com,1234567,1234567abcdef1234567abcdef,60,Contoso,HardwareKey
```

Note

CSV ファイルにヘッダー行が含まれていることを確認します。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. **Entra ID**&gt;**Multifactor authentication**&gt;**OATH トークン**に移動し、CSV ファイルをアップロードします。

CSV ファイルのサイズによって異なりますが、この処理には数分間かかることがあります。 [ **最新の情報に更新]** を選択して状態を取得します。 ファイルにエラーがある場合、それが一覧表示された CSV ファイルをダウンロードできます。 ダウンロードした CSV ファイル内のフィールド名は、アップロードされたバージョンとは異なります。

エラーに対処した後、管理者はトークンの **[アクティブ化** ] を選択し、トークンに表示される OTP を入力することで、各キーをアクティブ化できます。

ユーザーは、最大 5 つの OATH ハードウェア トークンまたは認証アプリケーション (Microsoft Authenticator アプリなど) をいつでも組み合わせて使用できるように構成できます。

Important

各トークンを 1 人のユーザーのみに割り当てるようにしてください。 今後、セキュリティ リスクを防ぐために、複数のユーザーに 1 つのトークンを割り当てるサポートが停止されます。

### 電話の設定

ユーザーが MFA プロンプトの電話を受ける場合は、発信者番号や音声案内など、ユーザーのエクスペリエンスを構成できます。

United Statesで MFA 発信者番号を構成していない場合、Microsoft からの音声通話は次の番号から送信されます。 迷惑メール フィルターを使用しているユーザーは、これらの番号を除外する必要があります。

既定の数値: *+1 (855) 330-8653*、 *+1 (855) 336-2194*、 *+1 (855) 341-5605*

次の表に、さまざまな国/地域の数値を示します。

| Country/Region | Number(s) |
| --- | --- |
| Austria | +43 6703062076 |
| Bangladesh | +880 9604606026 |
| Belgium | +32 480298502 |
| China | +44 1235619418, +44 1235619535, +44 1235619536, +44 1235619537, +44 1235619538, +44 1235619539, +44 7897087681, +44 7897087690, +44 7897087692, +66 977832930, +86 1052026902, +86 1052026905, +86 1052026907, +86 2157007919, +86 2157007923, +86 2157007926 |
| Croatia | +385 15507766 |
| Ecuador | +593 964256042 |
| Estonia | +372 6712726 |
| France | +33 744081468, +33 939370036 |
| Ghana | +233 308250245 |
| ギリシャ | +30 2119902739 |
| Guatemala | +502 23055056 |
| 香港特別行政区 | +852 25716964 |
| India | +91 1203524400, +91 1205089400, +91 2235543727, +91 2235543728, +91 2235543729, +91 2235543730, +91 2235544120, +91 2271897557, +91 3335105700, +91 3371568300, +91 4435279600, +91 4471566601 |
| Jordan | +962 797639442 |
| Kenya | +254 709605276 |
| Netherlands | +31 202490048, +31 635230021 |
| ニュージーランド | +64 95585975 |
| Nigeria | +234 7080627886 |
| Pakistan | +92 4232618686, +44 7897087681, +44 7897087690, +44 7897087692, +66 977832930 |
| Poland | +48 699740036 |
| ロシア連邦 | +7 9300653362 |
| サウジアラビア | +966 115122726 |
| 南アフリカ | +27 872405062 |
| Spain | +34 913305144 |
| スリランカ | +94 117750440 |
| Sweden | +46 701924176 |
| 台湾 | +886 277515260、+886 255686508 |
| Türkiye | +90 8505404893 |
| Ukraine | +380 443332000, +380 443332393 |
| アラブ首長国連邦 | +971 44015046 |
| Vietnam | +84 2039990161 |

Note

Microsoft Entra多要素認証呼び出しが公衆電話ネットワーク経由で発信される場合、呼び出しは発信者番号をサポートしていない通信事業者を介してルーティングされることがあります。 このため、Microsoft Entra 多要素認証が常に発信者 ID を送信しても、保証されません。 これは、多要素認証によって提供される電話とテキスト メッセージMicrosoft Entra両方に適用されます。 テキスト メッセージが多要素認証からのMicrosoft Entraであることを検証する必要がある場合は、「[メッセージの送信に短いコードを使用する方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/multi-factor-authentication-faq#what-short-codes-are-used-for-sending-text-messages-to-my-users-)を参照してください。

独自の発信者番号を構成するには、次の手順を実行します。

1. **Entra ID**&gt;**Multifactor authentication**&gt;**Phone 呼び出しの設定**に移動します。
2. **MFA 発信者番号**を、ユーザーが自分の電話で表示する番号に設定します。 米国ベースの番号だけを使用できます。
3. **保存**を選びます。

Note

Microsoft Entra多要素認証呼び出しが公衆電話ネットワーク経由で発信される場合、呼び出しは発信者番号をサポートしていない通信事業者を介してルーティングされることがあります。 このため、Microsoft Entra 多要素認証が常に発信者 ID を送信しても、保証されません。 これは、多要素認証によって提供される電話とテキスト メッセージMicrosoft Entra両方に適用されます。 テキスト メッセージが多要素認証からのMicrosoft Entraであることを検証する必要がある場合は、「[メッセージの送信に短いコードを使用する方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/multi-factor-authentication-faq#what-short-codes-are-used-for-sending-text-messages-to-my-users-)を参照してください。

#### カスタム音声メッセージ

Note

Microsoft Entra音声通話認証のカスタム音声メッセージは、2026 年 2 月 28 日に廃止されます。 廃止後、すべての音声通話の案内応答では、既定で Microsoft の標準録音が使用されます。

Microsoft Entra多要素認証には、独自の録音やあいさつ文を使用できます。 これらのメッセージは、既定の Microsoft の録音に加えて使用するか、その代わりに使用できます。

開始する前に、次の制限に注意してください。

- サポートされているファイルの形式は .wav と .mp3 です。
- ファイル サイズの上限は 1 MB です。
- 認証メッセージは、20 秒より短くする必要があります。 20 秒より長いメッセージの場合は、確認に失敗する可能性があります。 メッセージが終わる前にユーザーが応答しない場合、確認がタイムアウトになります。

#### カスタム メッセージ言語の動作

カスタム音声メッセージがユーザーに再生されるときのメッセージの言語は、次の要因によって決まります。

- ユーザーの言語。
    - ユーザーのブラウザーで検出された言語。
    - 他の認証シナリオでは、異なる動作になる可能性があります。
- 使用できるカスタム メッセージの言語。
    - この言語は、カスタム メッセージが追加されるときに管理者が選択します。

たとえば、カスタム メッセージが 1 つしかなく、それがドイツ語である場合は、次のようになります。

- ドイツ語で認証されたユーザーには、カスタムのドイツ語のメッセージが聞こえます。
- 英語で認証されたユーザーには、標準の英語メッセージが聞こえます。

#### カスタム音声メッセージの既定値

次のサンプル スクリプトを使用すると、独自のカスタム メッセージを作成できます。 これらのフレーズは、独自のカスタム メッセージを構成しない場合に既定で使用されます。

| メッセージ名 | Script |
| --- | --- |
| 認証が成功しました | サインインが成功しました。 |
| 拡張機能のプロンプト | これは Microsoft です。 サインインしようとしている場合は、# キーを押して続行します。 |
| 詐欺の確認 | サインインしようとしていない場合は、1 キーを押して IT チームに通知してアカウントを保護します。 |
| 詐欺の挨拶 | これは Microsoft です。 サインインしようとしている場合は、# キーを押してサインインを完了します。 サインインを試みない場合は、0 キーと #キーを押します。 |
| 不正行為の報告 | IT チームに通知しました。それ以上のアクションは必要ありません。 ヘルプについては、会社の IT チームにお問い合わせください。 Goodbye. |
| Activation | Microsoft のサインイン確認システムを使用していただきありがとうございます。 #キーを押して確認を完了してください。 |
| 認証拒否後の再試行 | 申し訳ございません。現時点ではサインインできません。 後で再度お試しください。 |
| 再試行 (標準) | Microsoft のサインイン確認システムを使用していただきありがとうございます。 #キーを押して確認を完了してください。 |
| あいさつ (標準) | これは Microsoft です。 サインインしようとしている場合は、# キーを押してサインインを完了します。 |
| あいさつ (PIN) | これは Microsoft です。 サインインしようとしている場合は、PIN を入力してサインインを完了します。 |
| 不正アクセスの案内 (PIN) | これは Microsoft です。 サインインしようとしている場合は、PIN を入力してサインインを完了します。 サインインを試みない場合は、0 キーと #キーを押します。 |
| 再試行 (PIN) | Microsoft のサインイン確認システムをご利用いただきありがとうございます。 認証を完了するには、PIN と #キーを入力してください。 |
| 内線番号ダイヤル後 | この拡張機能が既にある場合は、# キーを押して続行します。 |
| 認証が拒否されました | 申し訳ございません。現時点ではサインインできません。 後で再度お試しください。 |
| アクティブ化応答メッセージ (Standard) | Microsoft のサインイン確認システムを使用していただきありがとうございます。 #キーを押して確認を完了してください。 |
| アクティブ化の再試行 (Standard) | Microsoft のサインイン確認システムを使用していただきありがとうございます。 #キーを押して確認を完了してください。 |
| アクティブ化の案内 (PIN) | Microsoft のサインイン確認システムをご利用いただきありがとうございます。 認証を完了するには、PIN と #キーを入力してください。 |
| 内線番号ダイヤル前 | Microsoft のサインイン確認システムをご利用いただきありがとうございます。 この呼び出しを内線 {0}に転送してください。 |

#### カスタム メッセージを設定する

独自のカスタム メッセージを使用するには、次の手順を実行します。

1. **Entra ID**&gt;**Multifactor authentication**&gt;**Phone 呼び出しの設定**に移動します。
2. [ **あいさつの追加] を**選択します。
3. あいさつ (**標準)** や認証成功など、あいさつの**種類**を選択**します**。
4. 言語を選択 **します**。 カスタム メッセージ言語の動作については、前のセクションを参照してください。
5. アップロードする .mp3 または .wav サウンド ファイルを参照して、選択します。
6. [ **追加] を選択** し、[ **保存] を選択します**。

### MFA サービス設定

サービス設定には、アプリ パスワード、信頼できる IP、認証オプション、信頼済みデバイスでの多要素認証の記憶などの設定があります。 これは、従来のポータルです。

Microsoft Entra管理センターからサービス設定にアクセスするには、Entra ID > Multifactor 認証 > はじめに > 構成 > 追加のクラウドベースのMFA設定 を選択します。 ウィンドウまたはタブが開き、追加のサービス設定のオプションが表示されます。

#### 信頼できる IP

場所の条件は、IPv6 のサポートやその他の機能強化により、条件付きアクセスを使用して MFA を構成するときに推奨される方法です。 場所の条件の詳細については、「 [条件付きアクセス ポリシーでの場所の条件の使用」を](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/location-condition#location-condition-in-policy)参照してください。 場所を定義し、条件付きアクセス ポリシーを作成する手順については、「 [条件付きアクセス: 場所によるアクセスをブロック](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-block-by-location)する」を参照してください。

Microsoft Entra多要素認証の信頼できる IP 機能では、定義済みの IP アドレス範囲からサインインするユーザーに対する MFA プロンプトもバイパスされます。 オンプレミス環境の信頼できる IP の範囲を設定できます。 ユーザーがこれらの場所のいずれかにいる場合、多要素認証プロンプトMicrosoft Entraはありません。 信頼できる IP 機能には、P1 エディションMicrosoft Entra ID必要があります。

Note

信頼できる IP には、MFA Server を使用する場合にのみ、プライベート IP 範囲を含めることができます。 クラウドベースのMicrosoft Entra多要素認証では、パブリック IP アドレス範囲のみを使用できます。

IPv6 範囲は、 [名前付き場所](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network#ipv4-and-ipv6-address-ranges)でサポートされています。

組織がオンプレミスのアプリケーションに MFA を提供するために NPS 拡張機能をデプロイしている場合は、ソース IP アドレスが常に認証が試行される NPS サーバーとして表示されます。

| Microsoft Entra のテナントの種類 | 信頼できる IP 機能のオプション |
| --- | --- |
| Managed | **特定の IP アドレスの範囲**: 管理者は、会社のイントラネットからサインインするユーザーに対して多要素認証をバイパスできる IP アドレスの範囲を指定します。 最大 50 件の信頼できる IP 範囲を構成できます。 |
| Federated | **すべてのフェデレーション ユーザー**: 組織内からサインインするすべてのフェデレーション ユーザーは、多要素認証をバイパスできます。 ユーザーは、Active Directory Federation Services (AD FS) によって発行された要求を使用して検証をバイパスします。**特定の IP アドレスの範囲**: 管理者は、会社のイントラネットからサインインするユーザーに対して多要素認証をバイパスできる IP アドレスの範囲を指定します。 |

信頼できる IP のバイパスは、会社のイントラネット内からのみ機能します。 **[すべてのフェデレーション ユーザー**] オプションを選択し、ユーザーが会社のイントラネットの外部からサインインする場合、ユーザーは多要素認証を使用して認証する必要があります。 ユーザーが AD FS 要求を提示している場合でもプロセスは同じです。

Note

テナントでユーザーごとの MFA ポリシーと条件付きアクセス ポリシーの両方が構成されている場合は、条件付きアクセス ポリシーに信頼できる IP を追加し、MFA サービス設定を更新する必要があります。

##### 企業ネットワーク内のユーザー エクスペリエンス

信頼できる IP 機能が無効な場合、ブラウザーのフローでは多要素認証が必要です。 以前のリッチ クライアント アプリケーションではアプリ パスワードが必要です。

信頼できる IP を使用する場合、ブラウザーのフローで多要素認証は不要です。 ユーザーがアプリ パスワードを作成していない場合は、以前のリッチ クライアント アプリケーションにアプリ パスワードは不要です。 アプリ パスワードを使用している場合は、パスワードが必要です。

##### 企業ネットワーク外のユーザー エクスペリエンス

信頼できる IP が定義されているかどうかに関係なく、ブラウザーのフローで多要素認証が必要です。 以前のリッチ クライアント アプリケーションではアプリ パスワードが必要です。

##### 条件付きアクセスを使用したネームド ロケーションの有効化

条件付きアクセス規則を利用すると、次のステップを使用してネームド ロケーションを定義できます。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[条件付きアクセス]**&gt;**[ネームド ロケーション]** の順に移動します。
3. [ **新しい場所] を選択します**。
4. 場所の名前を入力します。
5. [ **信頼できる場所としてマークする**] を選択します。
6. お使いの環境の IP 範囲を CIDR 表記で入力します。 たとえば、 *40.77.182.32/27* などです。
7. **を選択して**を作成します。

##### 条件付きアクセスを使用して信頼できる IP 機能を有効化する

条件付きアクセス ポリシーを使用して信頼できる IP を有効にするには、次のステップを実行します。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[条件付きアクセス]**&gt;**[ネームド ロケーション]** の順に移動します。
3. [ **多要素認証の信頼できる IP の構成] を選択します**。
4. [ **サービス設定]** ページの [ **信頼できる IP] で**、次のいずれかのオプションを選択します。

    - **イントラネットから送信されたフェデレーション ユーザーからの要求の場合**: このオプションを選択するには、チェック ボックスをオンにします。 企業ネットワークからサインインするフェデレーション ユーザーは全員、AD FS によって発行される要求を使用して、多要素認証をバイパスします。 イントラネットの要求を適切なトラフィックに追加する規則が AD FS にあることを確認します。 規則が存在しない場合は、AD FS で次の規則を作成します。

        `c:[Type== "https://schemas.microsoft.com/ws/2012/01/insidecorporatenetwork"] => issue(claim = c);`

        Note

        [ **イントラネット上のフェデレーション ユーザーからの要求に対する多要素認証をスキップ** する] オプションは、場所の条件付きアクセスの評価に影響します。 **insidecorporatenetwork** 要求を含む要求は、そのオプションが選択されている場合、信頼できる場所からの要求として扱われます。
    - **パブリック IP の特定の範囲からの要求の場合**: このオプションを選択するには、テキスト ボックスに CIDR 表記で IP アドレスを入力します。

        - *xxx.xxx.xxx.1 ~ xxx.xxx.xxx.254* の範囲にある IP アドレスの場合は、***xxx.xxx.xxx.0*/24** のような表記を使用します。
        - 1 つの IP アドレスには、 ***xxx.xxx.xxx.xxx*/32** などの表記を使用します。
        - 最大で 50 の IP アドレス範囲を入力します。 これらの IP アドレスからサインインしたユーザーは、多要素認証をバイパスします。
5. **保存**を選びます。

##### サービス設定を使用して信頼できる IP 機能を有効化する

条件付きアクセス ポリシーを使用して信頼できる IP を有効にしない場合は、次の手順を使用して、Microsoft Entra多要素認証のサービス設定を構成できます。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)としてサインインします。
2. **Entra ID**&gt;**Multifactor 認証**&gt;**追加クラウドベースの MFA 設定**に移動します。
3. [ **サービス設定** ] ページの [ **信頼できる IP] で**、次のオプションのいずれかまたは両方を選択します。

    - **イントラネット上のフェデレーション ユーザーからの要求の場合**: このオプションを選択するには、チェック ボックスをオンにします。 企業ネットワークからサインインするフェデレーション ユーザーは全員、AD FS によって発行される要求を使用して、多要素認証をバイパスします。 イントラネットの要求を適切なトラフィックに追加する規則が AD FS にあることを確認します。 規則が存在しない場合は、AD FS で次の規則を作成します。

        `c:[Type== "https://schemas.microsoft.com/ws/2012/01/insidecorporatenetwork"] => issue(claim = c);`
    - **指定した範囲の IP アドレス サブネットからの要求の場合**: このオプションを選択するには、テキスト ボックスに CIDR 表記で IP アドレスを入力します。

        - *xxx.xxx.xxx.1 ~ xxx.xxx.xxx.254* の範囲にある IP アドレスの場合は、***xxx.xxx.xxx.0*/24** のような表記を使用します。
        - 1 つの IP アドレスには、 ***xxx.xxx.xxx.xxx*/32** などの表記を使用します。
        - 最大で 50 の IP アドレス範囲を入力します。 これらの IP アドレスからサインインしたユーザーは、多要素認証をバイパスします。
4. **保存**を選びます。

#### 検証方法

サービス設定ポータルでユーザーが使用できる検証方法を選択できます。 ユーザーは、Microsoft Entra多要素認証のために自分のアカウントを登録するときに、有効にしたオプションから優先する検証方法を選択します。 ユーザー登録プロセスのガイダンスについては、「 [多要素認証用にアカウントを設定する」](https://support.microsoft.com/account-billing/how-to-use-the-microsoft-authenticator-app-9783c865-0308-42fb-a519-8cf666fe0acc)を参照してください。

Important

2023 年 3 月に、従来の多要素認証とセルフサービス パスワード リセット (SSPR) ポリシーでの認証方法の管理の廃止を発表しました。 2025 年 9 月 30 日以降、これらのレガシ MFA および SSPR ポリシーでは認証方法を管理できません。 お客様には、手動移行制御を使用して、非推奨となる日までに認証方法ポリシーに移行することをお勧めします。 移行制御のヘルプについては、「 MFA と SSPR ポリシー設定を Microsoft Entra IDを参照してください。

次の検証方法を使用できます。

| Method | Description |
| --- | --- |
| 電話の呼び出し | 自動音声通話を行います。 ユーザーは、呼び出しに応答し、電話の # を押して認証を行います。 電話番号はon-premises Active Directoryに同期されません。 |
| 電話へのテキスト メッセージ | 確認コードを含むテキスト メッセージを送信します。 ユーザーは、この確認コードをサインイン インターフェイスに入力するように求められます。 このプロセスを一方向の SMS といいます。 双方向の SMS は、ユーザーが特定のコードを返信する必要があることを意味します。 双方向の SMS は非推奨となり、2018 年 11 月 14 日以降はサポートされなくなります。 管理者は、以前に双方向の SMS を使用していたユーザーに対して別の方法を有効にする必要があります。 |
| モバイル アプリでの通知 | 電話または登録されたデバイスにプッシュ通知が送信されます。 ユーザーは通知を表示し、[ **確認** ] を選択して検証を完了します。 Microsoft Authenticator アプリは、[Windows Phone](https://www.microsoft.com/p/microsoft-authenticator/9nblgggzmcj6)、[Android](https://go.microsoft.com/fwlink/?Linkid=825072)、および [iOS](https://go.microsoft.com/fwlink/?Linkid=825073) で使用できます。 |
| モバイル アプリからの確認コードまたはハードウェア トークン | Microsoft Authenticator アプリは、30 秒ごとに新しい OATH 検証コードを生成します。 ユーザーは確認コードをサインイン インターフェイスに入力します。 Microsoft Authenticator アプリは、[Windows Phone](https://www.microsoft.com/p/microsoft-authenticator/9nblgggzmcj6)、[Android](https://go.microsoft.com/fwlink/?Linkid=825072)、および [iOS](https://go.microsoft.com/fwlink/?Linkid=825073) で使用できます。 |

詳細については、「Microsoft Entra ID?を参照してください。

##### 検証方法を有効または無効にする

検証方法を有効または無効にするには、次の手順を実行します。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. **ユーザーごとの MFA** を選択します。
4. ページの上部にある **[多要素認証** ] で、[ **サービス設定**] を選択します。
5. [ **サービス設定** ] ページの [ **確認オプション**] で、適切なチェック ボックスをオンまたはオフにします。
6. **保存**を選びます。

#### 多要素認証を記憶する

**多要素認証の記憶**機能を使用すると、MFA を使用してデバイスに正常にサインインした後、ユーザーは指定した日数だけ後続の検証をバイパスできます。 使いやすさを向上させ、ユーザーが指定のデバイスに対して MFA を実行する必要がある回数を最小限に抑えるには、90 日以内の期間を選択します。

Important

アカウントまたはデバイスが侵害された場合、信頼できるデバイスに対する MFA の記憶はセキュリティに影響する可能性があります。 企業アカウントが侵害された場合、または信頼されたデバイスが失われたり盗まれたりした場合は、 [セッションを取り消す](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-userdevicesettings)必要があります。

取り消し操作により、信頼された状態がすべてのデバイスから失われ、ユーザーは多要素認証を再度実行する必要があります。 また、「 [多要素認証の設定を管理](https://support.microsoft.com/account-billing/change-your-two-step-verification-method-and-settings-c801d5ad-e0fc-4711-94d5-33ad5d4630f7#turn-on-two-factor-verification-prompts-on-a-trusted-device)する」で示されているように、独自のデバイスで元の MFA 状態を復元するようにユーザーに指示することもできます。

##### 機能のしくみ

**多要素認証を記憶**する機能は、ユーザーがサインイン時に **[*X* 日を再度要求しない**] オプションを選択したときに、ブラウザーに永続的な Cookie を設定します。 この場合、Cookie の有効期限が切れるまでは、同じブラウザーからユーザーが再度 MFA を求められることはありません。 そのユーザーが同じデバイスで異なるブラウザーを開くか、Cookie をクリアした場合は、再度、認証が求められます。

アプリが先進認証をサポートしているかどうかに関係なく、ブラウザー以外のアプリケーションでは 、[***X* 日を再度要求しない**] オプションは表示されません。 これらのアプリでは *、* 1 時間ごとに新しいアクセス トークンを提供する更新トークンが使用されます。 更新トークンが検証されると、Microsoft Entra IDは、指定した日数内に最後の多要素認証が行われたことを確認します。

この機能を使用すると、Web アプリでの認証回数 (通常は毎回プロンプトが表示される) が減ります。 より短い期間が構成されている場合、この機能では、先進認証クライアントの認証の回数 (通常は 180 日ごとにプロンプトが表示される) が増える場合があります。 条件付きアクセス ポリシーと組み合わされた場合にも認証数が増える場合があります。

Important

多 **要素認証の記憶** 機能は、ユーザーが MFA Server またはサード パーティの多要素認証ソリューションを介して AD FS の多要素認証を実行する場合に、AD FS の **サインインしたまま** にする機能と互換性がありません。

ユーザーが [AD FS で **サインイン** したままにする] を選択し、デバイスを MFA に対して信頼済みとしてマークした場合、 **多要素認証** の日数が経過した後も、ユーザーは自動的に検証されません。 Microsoft Entra IDは新しい多要素認証を要求しますが、AD FS は、多要素認証を再度実行するのではなく、元の MFA 要求と日付を持つトークンを返します。 *この反応は、Microsoft Entra IDとAD FSの間で検証ループを開始します。*

**多要素認証の記憶**機能は B2B ユーザーと互換性がないため、招待されたテナントにサインインしても B2B ユーザーには表示されません。

**多要素認証の記憶**機能は、サインイン頻度の条件付きアクセス制御と互換性がありません。 詳細については、「 [条件付きアクセスを使用して認証セッション管理を構成する」を](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-session-lifetime#configuring-authentication-session-controls)参照してください。

##### 多要素認証の記憶を有効にする

ユーザーが MFA の状態を記憶し、プロンプトをバイパスできるオプションを有効にして構成するには、次のステップを実行します。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. **ユーザーごとの MFA** を選択します。
4. ページの上部にある **[多要素認証** ] で、 **サービス設定**を選択します。
5. **[サービス設定**] ページの [**多要素認証を記憶**する] で、[**信頼できるデバイスでの多要素認証の記憶をユーザーに許可する**] を選択します。
6. 信頼できるデバイスが多要素認証をバイパスできるようにする日数を設定します。 最適なユーザー エクスペリエンスを実現するには、期間を 90 日以上に延長します。
7. **保存**を選びます。

##### デバイスを信頼済みとマークする

**多要素認証の記憶**機能を有効にした後、ユーザーは [**もう一度要求しない**] を選択して、サインイン時にデバイスを信頼済みとしてマークできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-mfa-nps-extension"} -->
## NPS で Microsoft Entra 多要素認証を使用する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-nps-extension
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: 既存のネットワーク ポリシー サーバー (NPS) 認証インフラストラクチャで Microsoft Entra 多要素認証機能を使用する方法について説明します

Microsoft Entra 多要素認証のネットワーク ポリシー サーバー (NPS) 拡張機能は、既存のサーバーを使用してクラウド ベースの MFA 機能を認証インフラストラクチャに追加します。 NPS 拡張機能を使用すると、新しいサーバーをインストール、構成、管理することなく、電話、テキスト メッセージ、またはモバイル アプリによる検証を既存の認証フローに追加できます。

この NPS 拡張機能は、RADIUS とクラウドベースの Microsoft Entra 多要素認証との間でアダプターとして機能し、認証の 2 番目の要素をフェデレーション ユーザーまたは同期済みユーザーに提供します。

### NPS 拡張機能のしくみ

Microsoft Entra 多要素認証の NPS 拡張機能を使用する場合、認証フローには次のコンポーネントが含まれます。

1. **NAS/VPN サーバーは、** VPN クライアントから要求を受信し、NPS サーバーへの RADIUS 要求に変換します。
2. **NPS Server** はACTIVE DIRECTORY DOMAIN SERVICES (AD DS) に接続して RADIUS 要求のプライマリ認証を実行し、成功すると、インストールされている拡張機能に要求を渡します。
3. **NPS 拡張機能** は、セカンダリ認証の Microsoft Entra 多要素認証に対する要求をトリガーします。 拡張機能が応答を受け取り、MFA チャレンジが成功した場合は、NPS サーバーに、AZURE STS によって発行された MFA 要求を含むセキュリティ トークンを提供することで、認証要求を完了します。

    Note

    NPS は [number matching](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-number-match) をサポートしていませんが、最新の NPS 拡張機能では、Microsoft Authenticatorで使用できる TOTP などの時間ベースのワンタイム パスワード (TOTP) メソッドがサポートされています。 TOTP サインインは、代替の **Approve**/**Deny** エクスペリエンスよりも優れたセキュリティを提供します。

    2023 年 5 月 8 日以降、すべてのユーザーに対して数値の一致が有効になっている場合、NPS 拡張機能バージョン 1.2.2216.1 以降との RADIUS 接続を実行するユーザーは、代わりに TOTP 方式でサインインするように求められます。 この動作を得るには、ユーザーは TOTP 認証方法を登録する必要があります。 TOTP メソッドが登録されていない場合、ユーザーは引き続き **[承認**/**Deny**] を表示します。
4. **Microsoft Entra 多要素認証**はMicrosoft Entra IDと通信してユーザーの詳細を取得し、ユーザーに構成された検証方法を使用してセカンダリ認証を実行します。

次の図に、この認証要求フローの概要を示します。

[Image: VPN サーバーを介して NPS サーバーと Microsoft Entra 多要素認証 NPS 拡張機能を介して認証するユーザーの認証フローの図]

#### RADIUS プロトコルの動作と NPS 拡張機能

RADIUS は UDP プロトコルであるため、送信側はパケット損失を想定し、応答を待機します。 一定の時間が経過すると、接続がタイムアウトする場合があります。その場合、送信側はパケットが送信先に届かなかったと想定してパケットを再送信します。 この記事の認証シナリオでは、VPN サーバーが要求を送信し、応答を待機します。 接続がタイムアウトした場合、VPN サーバーは要求を再度送信します。

[Image: NPS サーバーからの応答のタイムアウト後の RADIUS UDP パケット フローと要求の図]

NPS サーバーは、MFA 要求がまだ処理されている可能性があるため、接続がタイムアウトする前は、VPN サーバーの元の要求に応答できません。 ユーザーが MFA プロンプトに正常に応答しなかった場合もあります。そのため、Microsoft Entra 多要素認証の NPS 拡張機能は、そのイベントが完了するのを待機しています。 この場合、NPS サーバーでは、追加の VPN サーバー要求が複製要求と識別されます。 NPS サーバーでは、これらの重複する VPN サーバー要求が破棄されます。

[Image: RADIUS サーバーからの重複する要求を破棄する NPS サーバーの図]

NPS サーバーのログを見ると、これらの追加の要求が破棄されている可能性があります。 この動作は、エンド ユーザーが 1 回の認証試行に対して複数の要求を取得できないようにするための仕様です。 NPS サーバーのイベント ログの破棄された要求は、NPS サーバーまたは Microsoft Entra 多要素認証の NPS 拡張機能に問題があることを示すものではありません。

破棄される要求を最小限に抑えるには、VPN サーバーを少なくとも 60 秒のタイムアウトで構成することをお勧めします。 必要に応じて、またはイベント ログの破棄された要求を減らすために、VPN サーバーのタイムアウト値を 90 秒または 120 秒に増やすことができます。

この UDP プロトコルの動作により、NPS サーバーは、ユーザーが最初の要求に応答した後でも複製要求を受信し、別の MFA プロンプトを送信する可能性があります。 このタイミング条件を回避するために、Microsoft Entra 多要素認証の NPS 拡張機能は、正常な応答が VPN サーバーに送信されてから最大 10 秒間、複製要求をフィルター処理して破棄し続けます。

[Image: 応答が正常に返された後、VPN サーバーからの重複する要求を 10 秒間破棄し続ける NPS サーバーの図]

この場合も、Microsoft Entra 多要素認証のプロンプトが正常に終了したにも関わらず、NPS サーバーのイベント ログには破棄された要求がある可能性があります。 これは予期される動作であり、NPS サーバーまたは Microsoft Entra 多要素認証の NPS 拡張機能に問題があることを示すものではありません。

### デプロイを計画する

NPS 拡張機能は、自動的に冗長性を処理するため、特別な構成は不要です。

Microsoft Entra MFA に対応した NPS サーバーは、必要な数だけ作成できます。 複数のサーバーをインストールする場合、サーバーごとに異なるクライアント証明書を使用する必要があります。 サーバーごとに証明書を作成することは、各証明書を個別に更新でき、すべてのサーバー全体でのダウンタイムを心配しなくてもよいことを意味します。

VPN サーバーは認証要求をルーティングするため、新しい Microsoft Entra 多要素認証に対応した NPS サーバーを認識する必要があります。

### 前提条件

NPS 拡張機能は、既存のインフラストラクチャで使用します。 開始する前に、以下の前提条件を確認してください。

#### ライセンス

Microsoft Entra 多要素認証用の NPS 拡張機能は、< Microsoft Entra 多要素認証>>ライセンス (Microsoft Entra ID P1< および Premium P2 またはEnterprise Mobility + Securityに含まれる>) をお持ちのお客様が利用できます。 Microsoft Entra 多要素認証の従量課金ベース ライセンス (ユーザーごと、認証ごとのライセンスなど) は、NPS 拡張機能に対応していません。

#### ソフトウェア

- Windows Server 2012以降。 [Windows Server 2012はサポート終了に達しました](https://learn.microsoft.com/ja-jp/lifecycle/announcements/windows-server-2012-r2-end-of-support)。
- Microsoft Graph PowerShell モジュールには、.NET Framework 4.7.2 以降が必要です。
- PowerShell version 5.1 以降。 PowerShell のバージョンを確認するには、次のコマンドを実行します。

    ```powershell
    PS C:\> $PSVersionTable.PSVersion
    Major  Minor  Build  Revision
    -----  -----  -----  --------
    5      1      16232  1000
    ```

#### ライブラリ

- Visual Studio 2017 C++ 再頒布可能パッケージ (x64) は、NPS 拡張機能インストーラーによってインストールされます。
- Microsoft Graph PowerShell は、セットアップ プロセスの一部として実行する構成スクリプトを使用してインストールされます (まだ存在しない場合)。 モジュールを事前にインストールする必要はありません。

#### ディレクトリ テナント ID を取得する

NPS 拡張機能の構成の一環として、管理者の資格情報と Microsoft Entra テナントの ID を入力する必要があります。 テナント ID を取得するには、次の手順のようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Overview**&gt;**Properties** に移動します。

    [Image: Microsoft Entra 管理センターからテナント ID を取得する]

#### ネットワークの要件

NPS サーバーは、TCP ポート 443 を使って次の URL と通信できる必要があります。

- `https://login.microsoftonline.com`
- `https://login.microsoftonline.us (Azure Government)`
- `https://login.chinacloudapi.cn (Microsoft Azure operated by 21Vianet)`
- `https://credentials.azure.com`
- `https://strongauthenticationservice.auth.microsoft.com`
- `https://strongauthenticationservice.auth.microsoft.us (Azure Government)`
- `https://strongauthenticationservice.auth.microsoft.cn (Microsoft Azure operated by 21Vianet)`
- `https://adnotifications.windowsazure.com`
- `https://adnotifications.windowsazure.us (Azure Government)`
- `https://adnotifications.windowsazure.cn (Microsoft Azure operated by 21Vianet)`

さらに、 提供された PowerShell スクリプトを使用してアダプターのセットアップを完了するには、次の URL への接続が必要です。

- `https://onegetcdn.azureedge.net`
- `https://graph.microsoft.com`
- `https://go.microsoft.com`
- `https://provisioningapi.microsoftonline.com`
- `https://www.powershellgallery.com`
- `https://aadcdn.msauth.net`
- `https://aadcdn.msftauthimages.net`

次の表で、NPS 拡張機能に必要なポートとプロトコルについて説明します。 TCP 443 (受信および送信) は、NPS 拡張機能サーバーから Entra ID の間に必要な唯一のポートです。 ACCESS ポイントと NPS 拡張サーバーの間には RADIUS ポートが必要です。

| プロトコル | 港 / ポート | 説明 |
| --- | --- | --- |
| HTTPS | 443 | Entra ID に対してユーザー認証を有効にします (拡張機能をインストールするときに必要) |
| UDP | 1812 | NPS による RADIUS 認証の一般的なポート |
| UDP | 1645 | NPS による RADIUS 認証の一般的ではないポート |
| UDP | 1813 | NPS による RADIUS アカウンティングの一般的なポート |
| UDP | 1646 | NPS による RADIUS アカウンティングの一般的ではないポート |

### 環境を準備する

NPS 拡張機能をインストールする前に、認証トラフィックを処理するように環境を準備してください。

#### ドメインに参加しているサーバーで NPS 役割を有効にする

NPS サーバーはMicrosoft Entra IDに接続し、MFA 要求を認証します。 この役割に対して 1 台のサーバーを選択します。 NPS 拡張機能は RADIUS でないすべての要求に対してエラーをスローするため、他のサービスからの要求を処理しないサーバーを選択することをお勧めします。 NPS サーバーを、環境のプライマリおよびセカンダリ認証サーバーとしてセットアップする必要があります。 別のサーバーに RADIUS 要求をプロキシすることはできません。

1. サーバーで、**Server Manager**を開きます。 **[クイック スタート**] メニューから *[役割と機能の追加ウィザード*] を選択します。
2. インストールの種類として、[ **ロールベース] または [機能ベースのインストール**] を選択します。
3. **Network Policy と Access Services** サーバーロールを選択します。 ウィンドウがポップアップし、この役割を実行するために必要な追加機能が通知される場合があります。
4. *[確認*] ページまでウィザードを続行します。 準備ができたら、[ **インストール**] を選択します。

NPS サーバーの役割のインストールには数分かかる場合があります。 完了したら次のセクションに進み、VPN ソリューションからの受信 RADIUS 要求を処理するようにこのサーバーを構成します。

#### NPS サーバーと通信するように VPN ソリューションを構成する

使用する VPN ソリューションに応じて、RADIUS 認証ポリシーを構成する手順は異なります。 RADIUS NPS サーバーをポイントするように VPN ポリシーを構成します。

#### クラウドにドメイン ユーザーを同期する

この手順は、テナントで既に完了している可能性がありますが、Microsoft Entra Connect によってデータベースが最近同期済みであるか再確認することをお勧めします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[Hybrid ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)としてサインインします。
2. **Entra ID**&gt;**Entra Connect** に移動します。
3. 同期の状態が **[有効]** で、最後の同期が 1 時間以内であることを確認します。

新しい同期のラウンドを開始する必要がある場合は、「 [Microsoft Entra Connect Sync: Scheduler](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-scheduler#start-the-scheduler)」を参照してください。

#### ユーザーが使用できる認証方法を決定する

NPS 拡張機能のデプロイで使用できる認証方法に影響する 2 つの要素があります。

- RADIUS クライアント (VPN、Netscaler サーバーなど) と NPS サーバー間で使用されるパスワードの暗号化アルゴリズム。

    - **PAP** は、クラウドでの Microsoft Entra 多要素認証のすべての認証方法 (電話、一方向テキスト メッセージ、モバイル アプリ通知、OATH ハードウェア トークン、モバイル アプリ検証コード) をサポートしています。
    - **CHAPV2** と **EAP** は、電話呼び出しとモバイル アプリ通知をサポートします。
- クライアント アプリケーション (VPN、Netscaler サーバーなど) が処理できる入力方式。 たとえば、VPN クライアントに、ユーザーがテキストまたはモバイル アプリから確認コードを入力できるようにするなんらかの手段があるかどうか。

Azureでは、サポートされていない認証方法を[無効にすることができます](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-mfasettings#verification-methods)。

Note

使用されている認証プロトコル (PAP、CHAP、または EAP) に関係なく、MFA メソッドがテキストベース (SMS、モバイル アプリ確認コード、または OATH ハードウェア トークン) であり、ユーザーが VPN クライアントの UI 入力フィールドにコードまたはテキストを入力する必要がある場合は、認証が成功する可能性があります。 ネットワークアクセス ポリシーで構成されている RADIUS 属性は、 RADIUS クライアント (VPN ゲートウェイなどのネットワークアクセスデバイス) に転送されません。 その結果、VPN クライアントのaccessが必要以上に多かったり、accessが少なくなったり、accessがなくなったりする可能性があります。

回避策として、[CrpUsernameStuffing スクリプト](https://github.com/OneMoreNate/CrpUsernameStuffing) を実行して、ネットワーク Access ポリシーで構成されている RADIUS 属性を転送し、ユーザーの認証方法で SMS、Microsoft Authenticator パスコード、ハードウェア FOB などの One-Time パスコード (OTP) の使用が必要な場合に MFA を許可することができます。

#### ユーザーを MFA に登録する

NPS 拡張機能を展開して使用する前に、Microsoft Entra 多要素認証を実行する必要があるユーザーを、MFA に登録しておく必要があります。 また、拡張機能をデプロイ時にテストするには、Microsoft Entra 多要素認証に完全に登録されている少なくとも 1 つのテスト アカウントが必要です。

テスト アカウントを作成して構成する必要がある場合は、次の手順を使用します。

1. テスト アカウントで https://aka.ms/mfasetup にサインインします。
2. 表示されたメッセージに従って、確認方法を設定します。
3. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも[認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)としてサインインします。
4. **Entra ID**&gt;**Multifactor 認証**に移動し、テスト アカウントを有効にします。

重要

ユーザーが Microsoft Entra 多要素認証に正常に登録されていることを確認します。 ユーザーが以前にセルフサービス パスワード リセット (SSPR) にのみ登録している場合は、自分のアカウントに対して *StrongAuthenticationMethods* が有効になります。 ユーザーが SSPR にのみ登録されている場合でも、 *StrongAuthenticationMethods* が構成されている場合は、Microsoft Entra 多要素認証が適用されます。

SSPR と Microsoft Entra 多要素認証を同時に構成する、統合されたセキュリティ登録を有効にすることができます。 詳細については、「Microsoft Entra ID でのセキュリティ情報の登録を統合する機能を有効化」を参照してください。

ユーザーが以前に SSPR のみを有効にしていた場合は、 [認証方法を再登録するように強制](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-userdevicesettings) することもできます。

ユーザー名とパスワードを使用して NPS サーバーに接続するユーザーは、多要素認証プロンプトを完了する必要があります。

### NPS 拡張機能のインストール

重要

VPN access ポイントとは別のサーバーに NPS 拡張機能をインストールします。

#### Microsoft Entra 多要素認証の NPS 拡張機能をダウンロードしてインストールする

NPS 拡張機能をダウンロードしてインストールするには、次の手順を実行します。

1. Microsoft ダウンロード センターから [NPS 拡張機能](https://aka.ms/npsmfa)をダウンロードします。
2. 構成するネットワーク ポリシー サーバーにバイナリをコピーします。
3. *setup.exe* 実行し、インストール手順に従います。 エラーが発生した場合は、 前提条件セクションのライブラリ が正常にインストールされていることを確認してください。

##### NPS 拡張機能のアップグレード

既存の NPS 拡張機能のインストールを後からアップグレードする場合は、基になるサーバーの再起動を回避するために、次の手順を行います。

1. 既存のバージョンをアンインストールする。
2. 新しいインストーラーを実行する。
3. *ネットワーク ポリシー サーバー (IAS)* サービスを再起動します。

#### PowerShell スクリプトの実行

インストーラーによって、`C:\Program Files\Microsoft\AzureMfa\Config` (`C:\` はインストール先のドライブ) に PowerShell スクリプトが作成されます。 この PowerShell スクリプトは、実行されるたびに次のアクションを実行します。

- 自己署名証明書を作成する。
- 証明書の公開キーを、Microsoft Entra IDのサービス プリンシパルに関連付けます。
- ローカル コンピューターの証明書ストアに証明書を格納する。
- 証明書の秘密キーにaccessをネットワーク ユーザーに付与します。
- NPS サービスを再起動する。

(PowerShell スクリプトで生成される自己署名証明書ではなく) 独自の証明書を使用する場合を除き、PowerShell スクリプトを実行して NPS 拡張機能のインストールを完了します。 複数のサーバーに拡張機能をインストールする場合は、それぞれのサーバーに独自の証明書が必要です。

負荷分散機能または冗長性を提供するには、必要に応じて、追加の NPS サーバーで次の手順を繰り返します。

1. 管理者として Windows PowerShell プロンプトを開きます。
2. インストーラーによって PowerShell スクリプトが作成されたディレクトリに移動します。

    ```powershell
    cd "C:\Program Files\Microsoft\AzureMfa\Config"
    ```
3. インストーラーによって作成された PowerShell スクリプトを実行します。

    PowerShell を正常に接続してパッケージをダウンロードできるようにするには、まず TLS 1.2 を有効にする必要がある可能性があります。

    `[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12`

    重要

    米国政府機関向けAzureを使用する場合、または 21Vianet クラウドが運用するAzureの場合は、まず *AzureMfaNpsExtnConfigSetup.ps1* スクリプトを編集して、必要なクラウドの *Environment* パラメーターを含めます。 たとえば、 *-Environment USGov* または *-Environment China* を指定します。 環境オプション: USGov、USGovDoD、ドイツ、中国、グローバル。 例: Connect-MgGraph -Scopes Application.ReadWrite.All -Environment USGov -NoWelcome -Verbose -ErrorAction Stop。

    ```powershell
    .\AzureMfaNpsExtnConfigSetup.ps1
    ```
4. メッセージが表示されたら、Microsoft Entra IDにサインインします。 この機能を管理するには、[Global Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)が必要です。
5. PowerShell によって、テナント ID の入力が求められます。 前提条件セクションでコピーした *テナント ID* GUID を使用します。
6. スクリプトが終了すると、成功メッセージが表示されます。

以前のコンピューター証明書が期限切れになり、新しい証明書が生成された場合は、期限切れの証明書をすべて削除する必要があります。 期限切れの証明書があると、NPS 拡張機能の起動で問題が生じる可能性があります。

Note

PowerShell スクリプトで証明書を生成する代わりに独自の証明書を使用する場合は、クライアント認証の目的が含まれていることと、秘密キーにユーザー **NETWORK SERVICE** に*対する READ* アクセス許可が付与されていることを確認します。

バージョン 1.2.2893.1 以降を使用する場合は、証明書の拇印を使用して証明書を識別できます。 レジストリ設定のフィールドにHKEY\_LOCAL\_MACHINE\SOFTWARE\Microsoft\AzureMfa\CLIENT\_CERT\_IDENTIFIERをハッシュ値として**設定します**。 一部の証明書のサブジェクト名の参照に問題があります。 拇印を使用すると、この問題が回避されます。

バージョン 1.2.2677.2 以前を使用する場合、証明書は NPS の名前付け規則に従う必要があり、サブジェクト名は **CN=&lt;TenantID&gt;,OU=Microsoft NPS 拡張機能**である必要があります。

#### Microsoft Azure Governmentまたは21Vianetによって運営されるMicrosoft Azureに対する追加手順

21Vianet クラウドによって運用されているAzure GovernmentまたはAzureを使用しているお客様には、各 NPS サーバーで次の追加の構成手順が必要です。

重要

21Vianetによって運営されているAzureまたはAzure Governmentのお客様である場合にのみ、これらのレジストリ設定を構成してください。

1. 21Vianet によって運営されている Azure または Azure Government のお客様の場合は、NPS サーバーで **Registry Editor** を開きます。
2. `HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\AzureMfa` に移動します。
3. Azure Governmentのお客様の場合は、次のキー値を設定します。

    | レジストリ キー | [値] |
    | --- | --- |
    | AZURE\_MFA\_HOSTNAME | strongauthenticationservice.auth.microsoft.us |
    | AZURE\_MFA\_RESOURCE\_HOSTNAME | adnotifications.windowsazure.us |
    | STS\_URL | https://login.microsoftonline.us/ |
4. 21Vianet のお客様が運営する Microsoft Azureの場合は、次のキー値を設定します。

    | レジストリ キー | [値] |
    | --- | --- |
    | AZURE\_MFA\_HOSTNAME | strongauthenticationservice.auth.microsoft.cn |
    | AZURE\_MFA\_RESOURCE\_HOSTNAME | adnotifications.windowsazure.cn |
    | STS\_URL | https://login.chinacloudapi.cn/ |
5. 前の 2 つの手順を繰り返して、各 NPS サーバーのレジストリ キーの値を設定します。
6. NPS サーバーごとに NPS サービスを再起動します。

    影響を最小限に抑えるには、各 NPS サーバーを 1 つずつ NLB ローテーションから外し、すべての接続がドレインされるのを待ちます。

#### 証明書のロールオーバー

NPS 拡張機能のリリース *1.0.1.32* では、複数の証明書の読み取りがサポートされるようになりました。 この機能により、証明書の有効期限が切れる前のローリング アップデートが容易になります。 組織で以前のバージョンの NPS 拡張機能を実行している場合は、バージョン *1.0.1.32* 以降にアップグレードします。

`AzureMfaNpsExtnConfigSetup.ps1` スクリプトによって作成された証明書は、2 年間有効です。 証明書の有効期限を監視します。 NPS 拡張機能の証明書は、*個人用*の*ローカル コンピューター*証明書ストアに配置され、インストール スクリプトに指定されたテナント ID に*発行*されます。

証明書の有効期限が近づいている場合は、置き換えるための新しい証明書を作成する必要があります。 このプロセスを完了するには、`AzureMfaNpsExtnConfigSetup.ps1` を再度実行し、メッセージが表示されたら同じテナント ID を指定します。 このプロセスは、環境内の各 NPS サーバーで繰り返す必要があります。

### NPS 拡張機能の構成

環境を準備し、必要なサーバーに NPS 拡張機能をインストールしたら、拡張機能を構成できます。

このセクションでは、NPS 拡張機能を正常にデプロイするために必要な設計上の考慮事項と提案を示します。

#### 構成の制限

- Microsoft Entra 多要素認証の NPS 拡張機能には、MFA サーバーからクラウドにユーザーと設定を移行するためのツールは含まれません。 このため、既存のデプロイではなく、新しいデプロイに拡張機能を使用することをお勧めします。 既存のデプロイで拡張機能を使用する場合、ユーザーはクラウドに MFA の詳細を設定するために、再度セキュリティ確認を実行する必要があります。
- この NPS 拡張機能では、電話通話設定で構成されたカスタム電話通話はサポートされません。 既定の通話言語 (EN-US) が使用されます。
- NPS 拡張機能は、オンプレミスの AD DS 環境の UPN を使用して Microsoft Entra 多要素認証のユーザーを識別し、セカンダリ認証を行います。代替ログイン ID やカスタム AD DS フィールドなど、UPN 以外の識別子を使用するように NPS 拡張機能を構成することができます。 詳細については、 [多要素認証用の NPS 拡張機能の詳細な構成オプションに関する記事を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-nps-extension-advanced)参照してください。
- すべての暗号化プロトコルで、すべての検証メソッドがサポートされるわけではありません。
    - **PAP** では、電話、一方向のテキスト メッセージ、モバイル アプリ通知、モバイル アプリ検証コードがサポートされます
    - **CHAPV2** と **EAP** は、電話通話およびモバイルアプリ通知をサポートします。

#### MFA を必須とする RADIUS クライアントの制御

NPS 拡張機能を使用して RADIUS クライアントに対して MFA を有効にすると、このクライアントに対するすべての認証で MFA の実行が必須になります。 一部の RADIUS クライアントに対してのみ MFA を有効にする場合は、2 つの NPS サーバーを構成し、そのうちの一方に拡張機能をインストールします。

MFA を必須とする RADIUS クライアントについては、拡張機能が構成された NPS サーバーに要求を送信するよう構成し、他の RADIUS クライアントについては、拡張機能が構成されていない NPS サーバーに要求を送信するよう構成します。

#### MFA に登録されていないユーザーのための準備

MFA に登録されていないユーザーがいる場合は、そのユーザーが認証しようとしたときの動作を決める必要があります。 この動作を制御するには、レジストリ パス *HKLM\Software\Microsoft\AzureMFA* で*REQUIRE\_USER\_MATCH*設定を使用します。 この設定の構成オプションは 1 つだけです。

| 鍵 | [値] | 既定値 |
| --- | --- | --- |
| REQUIRE\_USER\_MATCH | TRUE または FALSE | 未設定 (TRUE に相当) |

この設定により、ユーザーが MFA に登録されていない場合のto doが決まります。 キーが存在しない、設定されていない、または *TRUE* に設定されていて、ユーザーが登録されていない場合、拡張機能は MFA チャレンジに失敗します。

キーが *FALSE* に設定されていて、ユーザーが登録されていない場合、MFA を実行せずに認証が続行されます。 ユーザーが MFA に登録されている場合、 *REQUIRE\_USER\_MATCH* が *FALSE* に設定されている場合でも、ユーザーは MFA で認証する必要があります。

ユーザーのオンボード中にこのキーを作成して *FALSE* に設定することもできます。Microsoft Entra 多要素認証に登録されていない場合もあります。 ただし、MFA に登録されていないユーザーのサインインが許可されることになるため、このキーは運用環境に移行する前に削除してください。

### トラブルシューティング

#### NPS 拡張機能の正常性チェック スクリプト

[Microsoft Entra 多要素認証 NPS 拡張機能の正常性チェック スクリプト](https://github.com/Azure-Samples/azure-mfa-nps-extension-health-check)は、NPS 拡張機能のトラブルシューティング時に基本的な正常性チェックを実行します。 スクリプトを実行し、使用可能なオプションのいずれかを選択します。

#### `AzureMfaNpsExtnConfigSetup.ps1` スクリプトの実行中の "サービス プリンシパルが見つかりませんでした" というエラーを修正する方法を教えてください。

何らかの理由で "Azure Multi-factor Auth Client" サービス プリンシパルがテナントに作成されていない場合は、PowerShell を実行して手動で作成できます。

```powershell
Connect-MgGraph -Scopes 'Application.ReadWrite.All'
New-MgServicePrincipal -AppId 981f26a1-7f43-403b-a875-f8b09b8cd720 -DisplayName "Azure Multi-Factor Auth Client"
```

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt; に移動し、"Azure Multi-factor Auth Client" を検索します。
3. **このアプリの [プロパティの確認] を**クリックします。 サービス プリンシパルが有効か無効かを確認します。
4. アプリケーション エントリ &gt;**Properties** をクリックします。
5. [ユーザーの **サインインを有効にする** ] オプションが **[いいえ**] に設定されている場合は、[ **はい**] に設定します。

`AzureMfaNpsExtnConfigSetup.ps1` スクリプトをもう一度実行すると、**サービス プリンシパルが見つかりませんでしたエラーが返されません**。

#### クライアント証明書が想定どおりにインストールされていることをどのように確認しますか？

インストーラーによって作成された自己署名証明書を証明書ストアで探し、秘密キーにユーザー *NETWORK SERVICE* に "READ" アクセス許可が付与されていることを確認します。 証明書には、**CN &lt;tenantid&gt; OU = Microsoft NPS 拡張機能**のサブジェクト名があります

`AzureMfaNpsExtnConfigSetup.ps1` スクリプトによって生成された自己署名証明書の有効期間は 2 年間です。 証明書がインストールされていることを確認するときは、証明書の有効期限が切れていないことも確認する必要があります。

#### クライアント証明書がMicrosoft Entra IDのテナントに関連付けられていることを確認するにはどうすればよいですか?

PowerShell を開き、次のコマンドを実行します。

```powershell
Connect-MgGraph -Scopes 'Application.Read.All'
(Get-MgServicePrincipal -Filter "appid eq '981f26a1-7f43-403b-a875-f8b09b8cd720'" -Property "KeyCredentials").KeyCredentials | 
Format-List KeyId, DisplayName, StartDateTime, EndDateTime, 
@{Name = "Key"; Expression = {[System.Convert]::ToBase64String($_.Key) }}, 
@{Name = "Thumbprint"; Expression = { [Convert]::ToBase64String($_.CustomKeyIdentifier)}}
```

このコマンドは、テナントと NPS 拡張機能のインスタンスを関連付けているすべての証明書を PowerShell セッションに出力します。 秘密キーを使用せずに *Base-64 でエンコードされた X.509(.cer)* ファイルとしてクライアント証明書をエクスポートして証明書を探し、PowerShell の一覧と比較します。 サーバーにインストールされている証明書のサムプリントとこれを比較します。 証明書のサムプリントが一致する必要があります。

*StartDateTime* タイムスタンプと *EndDateTime* タイムスタンプ (人間が判読できる形式) を使用すると、コマンドが複数の証明書を返した場合に明らかな誤ったフィットを除外できます。

#### サインインできないのはなぜですか。

パスワードの有効期限が切れていないことを確認します。 NPS 拡張機能では、サインイン ワークフローの中でパスワードを変更することはできません。 ご自分の組織の IT スタッフに連絡してサポートを依頼してください。

#### 要求がセキュリティ トークン エラーで失敗するのはなぜですか。

このエラーにはいくつかの原因が考えられます。 次の手順に従ってトラブルシューティングを行います。

1. NPS サーバーを再起動します。
2. クライアント証明書が正常にインストールされていることを確認します。
3. 証明書がMicrosoft Entra IDのテナントに関連付けられていることを確認します。
4. 拡張機能を実行しているサーバーから `https://login.microsoftonline.com/` にアクセスできることを確認します。

#### HTTP ログにユーザーが見つからないというエラーが記録され、認証が失敗するのはなぜですか。

AD Connect が実行されていること、およびユーザーがオンプレミスの AD DS 環境と Microsoft Entra ID の両方に存在することを確認します。

#### すべての認証が失敗し、ログに HTTP 接続エラーが記録されるのはなぜですか。

NPS 拡張機能を実行しているサーバーから `https://adnotifications.windowsazure.com` と `https://strongauthenticationservice.auth.microsoft.com` に到達可能であることを確認します。

#### 有効な証明書があるにもかかわらず認証が機能しないのはなぜですか。

以前のコンピューター証明書が期限切れになり、新しい証明書が生成された場合は、期限切れの証明書をすべて削除します。 期限切れの証明書があると、NPS 拡張機能の起動で問題が生じる可能性があります。

有効な証明書があるかどうかを確認するには、MMC を使用してローカル *コンピューター アカウントの証明書ストア* を確認し、証明書の有効期限が過ぎされていないことを確認します。 新しく有効な証明書を生成するには、「 PowerShell インストーラー スクリプトを実行する」の手順を再実行します。

#### NPS サーバーのログに破棄された要求があるのはなぜですか。

タイムアウト値が小さすぎる場合、VPN サーバーは NPS サーバーに対して繰り返し要求を送信することがあります。 これらの複製要求は NPS サーバーによって検出され、破棄されます。 この動作は仕様どおりであり、NPS サーバーまたは Microsoft Entra 多要素認証の NPS 拡張機能に問題があることを示すものではありません。

NPS サーバー ログに破棄されたパケットが表示される理由の詳細については、この記事の冒頭にある RADIUS プロトコルの動作と NPS 拡張機能 を参照してください。

#### Microsoft Authenticator の番号の一致を NPS で動作させるにはどうすればよいですか?

NPS では番号の照合はサポートされていませんが、最新の NPS 拡張機能では、Microsoft Authenticatorで使用可能な TOTP、その他のソフトウェア トークン、ハードウェア FOB などの時間ベースのワンタイム パスワード (TOTP) メソッドがサポートされています。 TOTP サインインは、代替の **Approve**/**Deny** エクスペリエンスよりも優れたセキュリティを提供します。 最新バージョンの [NPS 拡張機能](https://www.microsoft.com/download/details.aspx?id=54688)を実行していることを確認します。

2023 年 5 月 8 日以降、すべてのユーザーに対して数値の一致が有効になっている場合、NPS 拡張機能バージョン 1.2.2216.1 以降との RADIUS 接続を実行するユーザーは、代わりに TOTP 方式でサインインするように求められます。

この動作を得るには、ユーザーは TOTP 認証方法を登録する必要があります。 TOTP メソッドが登録されていない場合、ユーザーは引き続き **[承認**/**Deny**] を表示します。

2023 年 5 月 8 日以降、NPS 拡張機能バージョン 1.2.2216.1 のリリース前に、これより前のバージョンの NPS 拡張機能を実行する組織では、レジストリを変更することでユーザーに TOTP の入力を求めるようにすることができます。 詳細については、「 [NPS 拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-number-match#nps-extension)」を参照してください。

### TLS/SSL プロトコルと暗号スイートの管理

組織で求められない限り、以前の強度の低い暗号スイートを無効にするか、削除することをお勧めします。 このタスクを完了する方法については、[AD FS の SSL/TLS プロトコルと暗号スイートの管理に関する](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/manage-ssl-protocols-in-ad-fs)記事を参照してください。

#### その他のトラブルシューティング

その他のトラブルシューティング ガイダンスと考えられる解決策については、 [Microsoft Entra 多要素認証の NPS 拡張機能からのエラー メッセージの解決に関する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-nps-extension-errors)記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-mfa-nps-extension-advanced"} -->
## Microsoft Entra 多要素認証 NPS 拡張機能を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-nps-extension-advanced
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: NPS 拡張機能をインストールした後、許可された IP リストや UPN の置換などの高度な構成には、次の手順を使用します。

ネットワーク ポリシー サーバー (NPS) 拡張機能は、クラウドベースの Microsoft Entra 多要素認証機能をオンプレミス インフラストラクチャに拡張します。 この記事では、拡張機能が既にインストールされており、ニーズに合わせて拡張機能をカスタマイズする方法を知りたいことを前提としています。

### 代替ログインID

NPS 拡張機能はオンプレミスとクラウドの両方のディレクトリに接続するため、オンプレミスのユーザー プリンシパル名 (UPN) がクラウド内の名前と一致しないという問題が発生する可能性があります。 この問題を解決するには、代替サインイン ID を使用します。

NPS 拡張機能内で、Microsoft Entra 多要素認証の UPN として使用する Active Directory 属性を指定できます。 これにより、オンプレミスの UPN を変更することなく、2 段階認証でオンプレミスのリソースを保護できます。

代替サインイン ID を構成するには、`HKLM\SOFTWARE\Microsoft\AzureMfa` に移動し、次のレジストリ値を編集します。

| 名前 | Type | 既定値 | 説明 |
| --- | --- | --- | --- |
| LDAP\_ALTERNATE\_LOGINID\_ATTRIBUTE | 糸 | 空っぽ | UPN として使用する Active Directory 属性の名前を指定します。 この属性は AlternateLoginId 属性として使用されます。 このレジストリ値が有効な Active Directory 属性(mail や displayName など) に設定されている場合、属性の値は認証にユーザーの UPN として使用されます。 このレジストリ値が空であるか、構成されていない場合、AlternateLoginId は無効になり、ユーザーの UPN が認証に使用されます。 |
| LDAP\_FORCE\_GLOBAL\_CATALOG | ブーリアン | いいえ | AlternateLoginId を検索するときに、LDAP 検索にグローバル カタログを強制的に使用するには、このフラグを使用します。 ドメイン コントローラーをグローバル カタログとして構成し、AlternateLoginId 属性をグローバル カタログに追加して、このフラグを有効にします。  LDAP\_LOOKUP\_FORESTSが構成されている場合 (空ではない)、レジストリ設定の値に関係なく、このフラグは trueとして適用 。 この場合、NPS 拡張機能では、各フォレストの AlternateLoginId 属性を使用してグローバル カタログを構成する必要があります。 |
| LDAP\_LOOKUP\_FORESTS | 糸 | 空っぽ | 検索するフォレストのセミコロンで区切られた一覧を指定します。 たとえば、*contoso.com。foobar.com*. このレジストリ値が構成されている場合、NPS 拡張機能は、表示された順序ですべてのフォレストを繰り返し検索し、最初に成功した AlternateLoginId 値を返します。 このレジストリ値が構成されていない場合、AlternateLoginId 参照は現在のドメインに限定されます。 |

代替サインイン ID の問題をトラブルシューティングするには、[代替サインイン ID エラー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-nps-extension-errors#alternate-login-id-errors)に対する推奨手順を使用します。

### IP の例外

ワークロードを送信する前にロード バランサーがどのサーバーが実行されているかを確認する場合など、サーバーの可用性を監視する必要がある場合は、検証要求でこれらのチェックをブロックしないようにします。 代わりに、サービス アカウントで使用されていることがわかっている IP アドレスの一覧を作成し、その一覧の多要素認証要件を無効にします。

IP 許可リストを構成するには、`HKLM\SOFTWARE\Microsoft\AzureMfa` に移動し、次のレジストリ値を構成します。

| 名前 | Type | 既定値 | 説明 |
| --- | --- | --- | --- |
| IP\_WHITELIST | 糸 | 空っぽ | IP アドレスのセミコロンで区切られた一覧を指定します。 NAS/VPN サーバーなど、サービス要求が発生したマシンの IP アドレスを含めます。 IP 範囲とサブネットはサポートされていません。  たとえば、 *10.0.0.1; 10.0.0.2; 10.0.0.3*。 |

手記

このレジストリ キーはインストーラーによって既定では作成されず、サービスの再起動時に AuthZOptCh ログにエラーが表示されます。 ログ内のこのエラーは無視できますが、このレジストリ キーが作成され、不要な場合は空のままにした場合、エラー メッセージは返されません。

`IP_WHITELIST`に存在する IP アドレスから要求が送信されると、2 段階認証はスキップされます。 IP リストは、RADIUS 要求の *ratNASIPAddress* 属性で提供される IP アドレスと比較されます。 ratNASIPAddress 属性を指定せずに RADIUS 要求が入力されると、"RADIUS 要求 NasIpAddress 属性にソース IP が見つからないので、IP\_WHITE\_LIST\_WARNING::IP ホワイトリストは無視されています" という警告がログに記録されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-mfa-nps-extension-rdg"} -->
## RDG と NPS 拡張機能を使用して Microsoft Entra 多要素認証を統合する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-nps-extension-rdg
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Azure のネットワーク ポリシー サーバー拡張機能を使用して、リモート デスクトップ ゲートウェイ インフラストラクチャを Microsoft Entra 多要素認証と統合する

この記事では、Microsoft Azure のネットワーク ポリシー サーバー (NPS) 拡張機能を使用して、リモート デスクトップ ゲートウェイ インフラストラクチャを Microsoft Entra 多要素認証と統合する方法について詳しく説明します。

Azure のネットワーク ポリシー サーバー (NPS) 拡張機能を使用すると、お客様は Azure のクラウドベース [の多要素](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)認証を使用してリモート認証ダイヤルイン ユーザー サービス (RADIUS) クライアント認証を保護できます。 このソリューションは、ユーザーのサインインとトランザクションにセキュリティの第 2 レイヤーを追加するための 2 段階認証を提供します。

この記事では、Azure の NPS 拡張機能を使用して、NPS インフラストラクチャを Microsoft Entra 多要素認証と統合するための詳細な手順について説明します。 これにより、リモート デスクトップ ゲートウェイにサインインしようとするユーザーを確実に検証できるようになります。

注

この記事は、MFA Server のデプロイでは使用しないでください。また、Microsoft Entra 多要素認証 (クラウドベース) のデプロイでのみ使用してください。

ネットワーク ポリシーとアクセス サービス (NPS) により、組織は次のことができるようになります。

- 接続できるユーザー、接続が許可される時間帯、接続の期間、クライアントが接続に使用する必要があるセキュリティのレベルなどを指定して、ネットワーク要求を管理および制御するための集約された場所をそれぞれ定義できます。 これらのポリシーは、VPN またはリモート デスクトップ (RD) ゲートウェイ サーバーごとに指定するのではなく、集約された場所で一度に指定できます。 RADIUS プロトコルは、一元化された認証、承認、アカウンティング (AAA) を提供します。
- デバイスにネットワーク リソースへの無制限のアクセスを許可するか制限付きアクセスを許可するかを決定する、ネットワーク アクセス保護 (NAP) クライアント正常性ポリシーを制定し、強制できます。
- 802.1x 対応ワイヤレス アクセス ポイントとイーサネット スイッチへのアクセスに認証と承認を強制する手段を提供できます。

一般に、組織では NPS (RADIUS) を使用して VPN ポリシーの管理を簡素化し、一元化しています。 にもかかわらず、多くの組織が、NPS を使用してリモート デスクトップの接続承認ポリシー (RD CAP) の管理も簡素化し、一元化しています。

組織は、NPS を Microsoft Entra 多要素認証と統合して、セキュリティを強化し、高度なコンプライアンスを提供することもできます。 このことは、リモート デスクトップ ゲートウェイにサインインする際の 2 段階認証をユーザーが確実に制定するための助けとなります。 ユーザーはアクセスの許可を得るために、ユーザー名/パスワードの組み合わせをユーザーが独自に管理している情報と共に提供する必要があります。 この情報は、信頼性があり、簡単に複製できないもの (携帯電話番号、固定電話番号、モバイル デバイス上のアプリケーションなど) である必要があります。 RDG は現在、2FA の Microsoft 認証アプリ メソッドからの電話呼び出しと **承認**/**Deny** プッシュ通知をサポートしています。 サポートされている認証方法の詳細については、「ユーザーが使用できる認証方法を決定する」セクション を参照してください。

組織でリモート デスクトップ ゲートウェイを使っていて、ユーザーが Authenticator のプッシュ通知と共に TOTP コードを登録している場合、ユーザーは MFA チャレンジを満たすことができず、リモート デスクトップ ゲートウェイでのサインインに失敗します。 その場合は、新しいレジストリ キー (**OVERRIDE\_NUMBER\_MATCHING\_WITH\_OTP**) を作成して、Authenticator で承認/拒否へのプッシュ通知にフォールバックすることで、この動作をオーバーライドできます。 これを実行するには、 [NPS 拡張機能のオーバーライド番号の一致](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-number-match#nps-extension) 手順に従います。最終的な値が *OVERRIDE\_NUMBER\_MATCHING\_WITH\_OTP = FALSE* であると仮定します。

Azure の NPS 拡張機能が利用できる前に、統合された NPS および Microsoft Entra 多要素認証環境に対して 2 段階認証を実装したいお客様は、 [RADIUS を使用したリモート デスクトップ ゲートウェイと Azure Multi-Factor Authentication Server](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfaserver-nps-rdg) に記載されているように、オンプレミス環境で個別の MFA サーバーを構成して維持する必要がありました。

Azure の NPS 拡張機能の登場により、オンプレミス ベースの MFA ソリューションとクラウド ベースの MFA ソリューションのどちらを RADIUS クライアント認証の保護用にデプロイするかを組織が選択できるようになりました。

### 認証フロー

ユーザーは、リモート デスクトップ ゲートウェイ経由でのネットワーク リソースへのアクセスの許可を得るために、1 つの RD 接続承認ポリシー (RD CAP) と 1 つの RD リソース承認ポリシー (RD RAP) で指定された条件を満たす必要があります。 RD CAP では、RD ゲートウェイへの接続を許可するユーザーを指定します。 RD RAP では、ユーザーに RD ゲートウェイ経由での接続を許可するネットワーク リソース (リモート デスクトップやリモート アプリなど) を指定します。

RD ゲートウェイは、RD CAP に集約型ポリシー ストアを使用するように構成できます。 RD RAP は RD ゲートウェイで処理されるため、中央ポリシーを使用できません。 RD CAP に集約型ポリシー ストアを使用するように構成された RD ゲートウェイの例として、セントラル ポリシー ストアとして機能する別の NPS サーバーの RADIUS クライアントがあります。

Azure の NPS 拡張機能を NPS およびリモート デスクトップ ゲートウェイと統合した場合、正常な認証フローは次のようになります。

1. リモート デスクトップ ゲートウェイ サーバーが、リソース (リモート デスクトップ セッションなど) に接続するための認証要求をリモート デスクトップ ユーザーから受信します。 RADIUS クライアントとして機能するリモート デスクトップ ゲートウェイ サーバーは、要求を RADIUS Access-Request メッセージに変換し、NPS 拡張機能がインストールされている RADIUS (NPS) サーバーにメッセージを送信します。
2. Active Directory でユーザー名とパスワードの組み合わせが検証され、ユーザーが認証されます。
3. NPS 接続要求とネットワーク ポリシーで指定されているすべての条件が満たされていれば (時刻やグループ メンバーシップの制約など)、NPS 拡張機能によって、Microsoft Entra 多要素認証を使用したセカンダリ認証の要求がトリガーされます。
4. Microsoft Entra 多要素認証は、Microsoft Entra ID と通信してユーザーの詳細を取得し、サポートされている方法を使用してセカンダリ認証を実行します。
5. MFA チャレンジが成功すると、Microsoft Entra 多要素認証は結果を NPS 拡張機能に送信します。
6. 拡張機能がインストールされている NPS サーバーは、RD CAP ポリシーの RADIUS Access-Accept メッセージをリモート デスクトップ ゲートウェイ サーバーに送信します。
7. ユーザーは、要求したネットワーク リソースへの RD ゲートウェイ経由でのアクセスを許可されます。

### 前提条件

このセクションでは、Microsoft Entra 多要素認証をリモート デスクトップ ゲートウェイと統合する前に必要な前提条件について詳しく説明します。 作業を開始する前に、次の前提条件を満たしておく必要があります。

- リモート デスクトップ サービス (RDS) インフラストラクチャ
- Microsoft Entra 多要素認証ライセンス
- Windows Server ソフトウェア
- ネットワーク ポリシーとアクセス サービス (NPS) のロール
- オンプレミスの Active Directory と同期した Microsoft Entra
- Microsoft Entra GUID ID

#### リモート デスクトップ サービス (RDS) インフラストラクチャ

正常に稼働しているリモート デスクトップ サービス (RDS) インフラストラクチャが必要です。 作成しない場合は、クイック スタート テンプレート「 [リモート デスクトップ セッション コレクション](https://github.com/Azure/azure-quickstart-templates/tree/ad20c78b36d8e1246f96bb0e7a8741db481f957f/rds-deployment)のデプロイを作成する」を使用して、Azure でこのインフラストラクチャをすばやく作成できます。

テストのためにオンプレミスの RDS インフラストラクチャを手動ですばやく作成したい場合は、これをデプロイする手順に従います。 **詳細情報**: Azure クイック スタートと[基本的な RDS インフラストラクチャのデプロイ](https://learn.microsoft.com/ja-jp/windows-server/remote/remote-desktop-services/rds-in-azure)[を使用して RDS](https://learn.microsoft.com/ja-jp/windows-server/remote/remote-desktop-services/rds-deploy-infrastructure) をデプロイする。

#### Windows Server ソフトウェア

NPS 拡張機能を使用するには、NPS 役割サービスがインストールされた Windows Server 2008 R2 SP1 以上が必要です。 このセクションの手順はすべて Windows Server 2016 を使用して実行されました。

#### ネットワーク ポリシーとアクセス サービス (NPS) のロール

NPS 役割サービスは、RADIUS サーバーとクライアントの機能とネットワーク アクセス ポリシーの正常性サービスを提供します。 この役割は、インフラストラクチャ内の少なくとも 2 台のコンピューター (リモート デスクトップ ゲートウェイと別のメンバー サーバーまたはドメイン コントローラー) にインストールする必要があります。 既定では、この役割はリモート デスクトップ ゲートウェイとして構成されているコンピューターに既に存在します。 また、ドメイン コントローラーやメンバー サーバーなど、別のコンピューターにも NPS の役割をインストールする必要があります。

WINDOWS Server 2012 以前の NPS 役割サービスのインストールの詳細については、「 [NAP 正常性ポリシー サーバーのインストール](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2008-R2-and-2008/dd296890%28v=ws.10%29)」を参照してください。 ドメイン コントローラーに NPS をインストールするための推奨事項など、NPS のベスト プラクティスについては、「 [NPS のベスト プラクティス」](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2008-R2-and-2008/cc771746%28v=ws.10%29)を参照してください。

#### オンプレミスの Active Directory と同期した Microsoft Entra

NPS 拡張機能を使用するには、オンプレミス ユーザーを Microsoft Entra ID と同期し、MFA を有効にする必要があります。 このセクションでは、オンプレミスのユーザーが AD Connect を使用して Microsoft Entra ID と同期されていることを前提としています。 Microsoft Entra Connect の詳細については、「 [オンプレミスのディレクトリを Microsoft Entra ID と統合する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/whatis-hybrid-identity)。

#### Microsoft Entra GUID ID

NPS 拡張機能をインストールするには、Microsoft Entra ID の GUID を理解している必要があります。 Microsoft Entra ID の GUID を見つける手順を次に示します。

### 多要素認証を構成する

このセクションでは、Microsoft Entra 多要素認証をリモート デスクトップ ゲートウェイと統合する手順について説明します。 管理者は、ユーザーが多要素認証デバイスまたはアプリケーションを自己登録する前に、Microsoft Entra 多要素認証サービスを構成する必要があります。

[クラウドでの Microsoft Entra 多要素認証の概要](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-getstarted)に関する記事の手順に従って、Microsoft Entra ユーザーの MFA を有効にします。

#### 2 段階認証用にアカウントを構成する

アカウントで MFA が有効になったら、2 番目の認証要素に使用するように信頼されたデバイスを正常に構成し、2 段階認証を使用して認証するまで、MFA ポリシーによって管理されるリソースにサインインすることはできません。

ユーザー アカウントで MFA 用のデバイスを理解し、適切に構成するには、「 [Microsoft Entra 多要素認証の意味](https://support.microsoft.com/account-billing/how-to-use-the-microsoft-authenticator-app-9783c865-0308-42fb-a519-8cf666fe0acc) 」の手順に従ってください。

重要

リモート デスクトップ ゲートウェイのサインインでは、Microsoft Entra 多要素認証で確認コードを入力することはできません。 ユーザー アカウントは、電話による確認または **承認**/**Deny** プッシュ通知を使用した Microsoft Authenticator アプリ用に構成する必要があります。

電話による確認も、 **承認**/**Deny** プッシュ通知もユーザーに対して構成されていない場合、ユーザーは Microsoft Entra 多要素認証チャレンジを完了してリモート デスクトップ ゲートウェイにサインインできなくなります。

確認コードを入力する手段がないため、SMS テキストはリモート デスクトップ ゲートウェイには使用できません。

### NPS 拡張機能のインストールと構成

このセクションでは、リモート デスクトップ ゲートウェイでのクライアント認証に Microsoft Entra 多要素認証を使用するように RDS インフラストラクチャを構成する手順について説明します。

#### ディレクトリ テナント ID を取得する

NPS 拡張機能の構成の一環として、管理者資格情報と Microsoft Entra テナントの ID を入力する必要があります。 テナント ID を取得するには、次の手順のようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Overview**&gt;**Properties** に移動します。

    [Image: Microsoft Entra 管理センターからテナント ID を取得する]

#### NPS 拡張機能のインストール

ネットワーク ポリシーとアクセス サービス (NPS) の役割がインストールされているサーバーに NPS 拡張機能をインストールします。 これが、設計で RADIUS サーバーとして機能します。

重要

リモート デスクトップ ゲートウェイ (RDG) サーバーには NPS 拡張機能をインストールしないでください。 RDG サーバーはそのクライアントと共に RADIUS プロトコルを使用しないため、この拡張機能では MFA を解釈して実行することができません。

RDG サーバーと、NPS 拡張機能を備えた NPS サーバーが異なるサーバーである場合、RDG は NPS を内部で使用して他の NPS サーバーと通信し、RADIUS をプロトコルとして使用して正しく通信します。

1. [NPS 拡張機能](https://aka.ms/npsmfa)をダウンロードします。
2. セットアップ実行可能ファイル (NpsExtnForAzureMfaInstaller.exe) を NPS サーバーにコピーします。
3. NPS サーバーで、 **NpsExtnForAzureMfaInstaller.exe**をダブルクリックします。 メッセージが表示されたら、[ **実行**] を選択します。
4. [NPS Extension For Microsoft Entra Multifactor authentication Setup] ダイアログ ボックスで、ソフトウェア ライセンス条項を確認し、[ **ライセンス条項に同意する**] をオンにして、[ **インストール**] を選択します。
5. [Microsoft Entra 多要素認証の NPS 拡張機能のセットアップ] ダイアログ ボックスで、[ **閉じる**] を選択します。

#### PowerShell スクリプトを使用して NPS 拡張機能で使用する証明書を構成する

次に、セキュリティで保護された通信と保証を確保するために、NPS 拡張機能で使用する証明書を構成する必要があります。 NPS コンポーネントには、NPS で使用する自己署名証明書を構成する PowerShell スクリプトが含まれています。

このスクリプトは、次のアクションを実行します。

- 自己署名証明書を作成する
- 資格情報の公開キーを Microsoft Entra ID のサービス プリンシパルに関連付ける
- ローカル コンピューターのストアに証明書を格納する
- ネットワーク ユーザーに証明書の秘密キーへのアクセスを許可する
- ネットワーク ポリシー サーバー サービスを再起動する

独自の証明書を使用する場合は、Microsoft Entra ID のサービス プリンシパルへの証明書の公開キーの関連付けなどを行う必要があります。

スクリプトを使用するには、Microsoft Entra 管理者の資格情報と、先ほどコピーした Microsoft Entra テナント ID を拡張機能に入力します。 NPS 拡張機能がインストールされている各 NPS サーバーでスクリプトを実行します。 次に、次を実行します。

1. 管理用の Windows PowerShell プロンプトを開きます。
2. PowerShell プロンプトで、「 `cd 'c:\Program Files\Microsoft\AzureMfa\Config'`」と入力し、 **Enter キー**を押します。
3. 「 `.\AzureMfaNpsExtnConfigSetup.ps1`」と入力し、 **Enter キー**を押します。 このスクリプトで、PowerShell モジュールがインストールされているかどうかが確認されます。 インストールされていない場合は、スクリプトによってモジュールがインストールされます。

    [Image: PowerShell での AzureMfaNpsExtnConfigSetup.ps1 の実行]
4. PowerShell モジュールのインストールの確認後、PowerShell モジュールのダイアログ ボックスが表示されます。 ダイアログ ボックスで、Microsoft Entra 管理者の資格情報とパスワードを入力し、[ **サインイン**] を選択します。
5. メッセージが表示されたら、先ほどコピーした *テナント ID を* クリップボードに貼り付け、 **Enter キー**を押します。

    [Image: PowerShell でのテナント ID の入力]
6. スクリプトによって自己署名証明書が作成され、他の構成変更が実行されます。

### リモート デスクトップ ゲートウェイでの NPS コンポーネントの構成

このセクションでは、リモート デスクトップ ゲートウェイの接続承認ポリシーと他の RADIUS 設定を構成します。

認証フローでは、リモート デスクトップ ゲートウェイと、NPS 拡張機能がインストールされている NPS サーバー間で RADIUS メッセージを交換する必要があります。 つまり、リモート デスクトップ ゲートウェイと NPS 拡張機能がインストールされている NPS サーバーの両方で RADIUS クライアント設定を構成する必要があります。

#### セントラル ストアを使用するようにリモート デスクトップ ゲートウェイの接続承認ポリシーを構成する

リモート デスクトップの接続承認ポリシー (RD CAP) では、リモート デスクトップ ゲートウェイ サーバーに接続するための要件を指定します。 RD CAP は、ローカルに保存することも (既定)、NPS を実行している RD CAP のセントラル ストアに保存することもできます。 Microsoft Entra 多要素認証と RDS の統合を構成するには、セントラル ストアの使用を指定する必要があります。

1. RD ゲートウェイ サーバーで、 **サーバー マネージャー**を開きます。
2. メニューの [ **ツール**] を選択し、[ **リモート デスクトップ サービス**] をポイントして、[ **リモート デスクトップ ゲートウェイ マネージャー**] を選択します。
3. RD ゲートウェイ マネージャーで、[ **サーバー名] (ローカル)** を右クリックし、[プロパティ] を選択 **します**。
4. [プロパティ] ダイアログ ボックスで、[ **RD CAP ストア** ] タブを選択します。
5. [RD CAP ストア] タブで、 **NPS を実行している中央サーバー**を選択します。
6. [ **NPS を実行しているサーバーの名前または IP アドレスを入力** してください] フィールドに、NPS 拡張機能をインストールしたサーバーの IP アドレスまたはサーバー名を入力します。

    [Image: NPS サーバーの名前または IP アドレスを入力します]
7. **[追加]**を選択します。
8. [ **共有シークレット** ] ダイアログ ボックスで、共有シークレットを入力し、[ **OK]** を選択します。 この共有シークレットを記録し、記録を安全な場所に保管してください。

    注

    共有シークレットは、RADIUS サーバーと RADIUS クライアント間の信頼を確立するために使用されます。 長い複雑なシークレットを作成してください。

    [Image: 信頼を確立するための共有シークレットの作成]
9. [ **OK] を** 選択してダイアログ ボックスを閉じます。

#### リモート デスクトップ ゲートウェイの NPS で RADIUS のタイムアウト値を構成する

ユーザーの資格情報の検証、2 段階認証の実行、応答の受信、RADIUS メッセージへの応答を行う時間を確保するには、RADIUS タイムアウト値を調整する必要があります。

1. RD ゲートウェイ サーバーで、サーバー マネージャーを開きます。 メニューの [ **ツール**] を選択し、[ **ネットワーク ポリシー サーバー**] を選択します。
2. **NPS (ローカル)** コンソールで、[**RADIUS クライアントとサーバー**] を展開し、[**リモート RADIUS サーバー**] を選択します。

    [Image: リモート RADIUS サーバーが表示されているネットワーク ポリシー サーバー管理コンソール]
3. 詳細ウィンドウで、 **TS ゲートウェイ サーバー グループ**をダブルクリックします。

    注

    この RADIUS サーバー グループは、NPS ポリシーのセントラル サーバーを構成したときに作成されました。 RD ゲートウェイは、このサーバーまたはサーバーのグループ (グループに複数のサーバーが含まれている場合) に RADIUS メッセージを転送します。
4. **[TS ゲートウェイ サーバー グループのプロパティ**] ダイアログ ボックスで、RD CAP を格納するように構成した NPS サーバーの IP アドレスまたは名前を選択し、[編集] を選択**します**。

    [Image: 前に構成した NPS サーバーの IP または名前を選択します]
5. [ **RADIUS サーバーの編集** ] ダイアログ ボックスで、[ **負荷分散** ] タブを選択します。
6. [ **負荷分散** ] タブ **の [要求が破棄されたと見なされるまでの応答がない** 秒数] フィールドで、既定値を 3 から 30 ~ 60 秒の値に変更します。
7. [ **サーバーが使用不可と識別されたときの要求間隔の秒数** ] フィールドで、既定値の 30 秒を、前の手順で指定した値以上の値に変更します。

    [Image: [負荷分散] タブで Radius Server のタイムアウト設定を編集する]
8. [ **OK] を** 2 回選択してダイアログ ボックスを閉じます。

#### 接続要求ポリシーを確認する

既定では、接続承認ポリシーに集約型ポリシー ストアを使用するように RD ゲートウェイを構成すると、CAP 要求を NPS サーバーに転送するように RD ゲートウェイが構成されます。 Microsoft Entra 多要素認証拡張機能がインストールされている NPS サーバーで、RADIUS アクセス要求が処理されます。 次の手順は、既定の接続要求ポリシーを確認する方法を示しています。

1. RD ゲートウェイの NPS (ローカル) コンソールで、[ **ポリシー**] を展開し、[ **接続要求ポリシー**] を選択します。
2. **[TS GATEWAY AUTHORIZATION POLICY]** をダブルクリックします。
3. **[TS ゲートウェイ承認ポリシーのプロパティ**] ダイアログ ボックスで、[**設定]** タブを選択します。
4. [ **設定]** タブの [接続要求の転送] で、[ **認証**] を選択します。 認証のために要求を転送するように RADIUS クライアントが構成されています。

    [Image: サーバー グループを指定して認証設定を構成する]
5. [ **キャンセル] を選択します**。

注

接続要求ポリシーの作成の詳細については、「接続要求ポリシーの構成 ドキュメント」記事を参照してください。

### NPS 拡張機能がインストールされているサーバーでの NPS の構成

NPS 拡張機能がインストールされている NPS サーバーは、リモート デスクトップ ゲートウェイの NPS サーバーと RADIUS メッセージを交換できる必要があります。 このメッセージ交換を可能にするには、NPS 拡張サービスがインストールされているサーバーで NPS コンポーネントを構成する必要があります。

#### Active Directory にサーバーを登録する

このシナリオで正常に機能させるには、NPS サーバーを Active Directory に登録する必要があります。

1. NPS サーバーで、 **サーバー マネージャー**を開きます。
2. サーバー マネージャーで、[ **ツール**] を選択し、[ **ネットワーク ポリシー サーバー**] を選択します。
3. ネットワーク ポリシー サーバー コンソールで、 **NPS (ローカル)** を右選択し、[ **Active Directory にサーバーを登録**する] を選択します。
4. [ **OK] を** 2 回選択します。

    [Image: Active Directory に NPS サーバーを登録する]
5. 次の手順で使用するため、コンソールは開いたままにしておきます。

#### RADIUS クライアントを作成して構成する

リモート デスクトップ ゲートウェイは、NPS サーバーの RADIUS クライアントとして構成する必要があります。

1. NPS 拡張機能がインストールされている NPS サーバーの NPS **(ローカル)** コンソールで、[ **RADIUS クライアント** ] を右クリックし、[ **新規**] を選択します。

    [Image: NPS コンソールで新しい RADIUS クライアントを作成する]
2. [ **新しい RADIUS クライアント** ] ダイアログ ボックスで、 *ゲートウェイ*などのフレンドリ名と、リモート デスクトップ ゲートウェイ サーバーの IP アドレスまたは DNS 名を指定します。
3. [ **共有シークレット** ] フィールドと [ **共有シークレットの確認** ] フィールドに、前に使用したのと同じシークレットを入力します。

    [Image: フレンドリ名と IP または DNS アドレスを構成する]
4. [ **OK] を** 選択して [新しい RADIUS クライアント] ダイアログ ボックスを閉じます。

#### ネットワーク ポリシーを構成する

Microsoft Entra 多要素認証拡張機能がインストールされている NPS サーバーは、接続承認ポリシー (CAP) の指定された集約型ポリシー ストアであることを思い出してください。 そのため、有効な接続要求を承認するために、NPS サーバーに CAP を実装する必要があります。

1. NPS サーバーで、NPS (ローカル) コンソールを開き、[ **ポリシー]** を展開し、[ **ネットワーク ポリシー**] を選択します。
2. **[他のアクセス サーバーへの接続]** を右選択し、[**ポリシーの複製**] を選択します。

    [Image: 他のアクセス サーバー ポリシーへの接続を複製する]
3. **[他のアクセス サーバーへの接続のコピー**] を右選択し、[プロパティ] を選択**します**。
4. [ **他のアクセス サーバーへの接続のコピー** ] ダイアログ ボックスの [ **ポリシー名**] に、適切な名前 ( *RDG\_CAP*など) を入力します。 **ポリシーが有効になっていることを**確認し、[**アクセス権の付与**] を選択します。 必要に応じて、[ **ネットワーク アクセス サーバーの種類**] で [ **リモート デスクトップ ゲートウェイ**] を選択するか、[ **未指定]** のままにすることができます。

    [Image: ポリシーに名前を付け、有効にして、アクセス権を付与する]
5. [ **制約** ] タブを選択し、[ **認証方法をネゴシエートせずにクライアントが接続できるようにする**] をオンにします。

    [Image: クライアントが接続できるように認証方法を変更する]
6. 必要に応じて、[ **条件** ] タブを選択し、接続を承認するために満たす必要がある条件 (特定の Windows グループのメンバーシップなど) を追加します。

    [Image: 必要に応じて接続条件を指定する]
7. [ **OK] を選択します**。 対応するヘルプ トピックを表示するように求められたら、[ **いいえ**] を選択します。
8. 新しいポリシーが一覧の一番上に表示されていること、ポリシーが有効になっていること、ポリシーがアクセスを許可していることを確認します。

    [Image: ポリシーを一覧の一番上に移動する]

### 構成の確認

構成を確認するには、適切な RDP クライアントでリモート デスクトップ ゲートウェイにサインインする必要があります。 必ず、接続承認ポリシーで許可され、Microsoft Entra 多要素認証が有効になっているアカウントを使用してください。

次の図に示すように、 **リモート デスクトップ Web アクセス** ページを使用できます。

[Image: リモート デスクトップ Web アクセスでのテスト]

プライマリ認証の資格情報を正常に入力すると、次のセクションに示すように、[リモート デスクトップ接続] ダイアログ ボックスに [リモート接続の開始] の状態が表示されます。

Microsoft Entra 多要素認証で以前に構成したセカンダリ認証方法で正常に認証された場合は、リソースに接続されます。 ただし、セカンダリ認証が成功しなかった場合は、リソースへのアクセスが拒否されます。

[Image: リモートデスクトップ接続によるリモート接続の開始]

次の例では、Windows Phone の Authenticator アプリを使用して、セカンダリ認証を提供します。

[Image: 検証を示す Windows Phone Authenticator アプリの例]

セカンダリ認証方法を使用して正常に認証されると、通常どおりリモート デスクトップ ゲートウェイにログインします。 ただし、信頼されたデバイス上のモバイル アプリを使用してセカンダリ認証方法を使用する必要があるため、サインイン プロセスはそれ以外の場合よりも安全です。

#### 成功したログオン イベントのイベント ビューアーのログを表示する

Windows イベント ビューアーのログで成功したサインイン イベントを表示するには、次の PowerShell コマンドを発行して、Windows Terminal サービスのログと Windows のセキュリティ ログを照会します。

ゲートウェイ操作ログ *(イベント ビューアー\アプリケーションとサービス ログ\Microsoft\Windows\TerminalServices-Gateway\Operational)* で正常なサインイン イベントを照会するには、次の PowerShell コマンドを使用します。

- `Get-WinEvent -Logname Microsoft-Windows-TerminalServices-Gateway/Operational | where {$_.ID -eq '300'} | FL`
- このコマンドは、ユーザーがリソース承認ポリシーの要件 (RD RAP) を満たしており、アクセスが許可されたことを示す Windows イベントを表示します。

[Image: PowerShell を使用したイベントの表示]

- `Get-WinEvent -Logname Microsoft-Windows-TerminalServices-Gateway/Operational | where {$_.ID -eq '200'} | FL`
- このコマンドは、ユーザーが接続承認ポリシーの要件を満たしていることを示すイベントを表示します。

[Image: PowerShell を使用した接続承認ポリシーの表示]

このログを表示し、イベント ID 300 と 200 でフィルター処理することもできます。 イベント ビューアーのセキュリティ ログの成功したログオン イベントを照会するには、次のコマンドを使用します。

- `Get-WinEvent -Logname Security | where {$_.ID -eq '6272'} | FL`
- このコマンドは、セントラル NPS サーバーまたは RD ゲートウェイ サーバーで実行できます。

[Image: 成功したログオン イベントのサンプル]

セキュリティ ログまたはネットワーク ポリシーと Access Services のカスタム ビューを表示することもできます。

[Image: ネットワーク ポリシーとアクセス サービス イベント ビューアー]

Microsoft Entra 多要素認証の NPS 拡張機能がインストールされているサーバーで、拡張機能に固有のイベント ビューアーのアプリケーション ログ (*アプリケーションとサービス ログ\Microsoft\AzureMfa*) を確認できます。

[Image: イベント ビューアーの AuthZ アプリケーション ログ]

### トラブルシューティング ガイド

構成が期待どおりに動作しない場合、トラブルシューティングを開始する最初の場所は、ユーザーが Microsoft Entra 多要素認証を使用するように構成されていることを確認することです。 ユーザーに [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインさせます。 ユーザーがセカンダリ検証を求められ、正常に認証できれば、Microsoft Entra 多要素認証の構成の問題はなくなります。

Microsoft Entra 多要素認証がユーザーに対して機能している場合は、関連するイベント ログを確認する必要があります。 これには、前のセクションで説明したセキュリティ イベント ログ、ゲートウェイの操作ログ、Microsoft Entra 多要素認証ログが含まれます。

失敗したログオン イベント (イベント ID 6273) を示すセキュリティ ログの出力例を次に示します。

[Image: 失敗したログオン イベントのサンプル]

AzureMFA ログからの関連イベントを次に示します。

[Image: イベント ビューアーでの Microsoft Entra 多要素認証ログのサンプル]

高度なトラブルシューティング オプションを実行するには、NPS サービスがインストールされているサーバーで NPS データベース形式のログ ファイルを参照します。 これらのログ ファイルは、\ * System32\Logs フォルダー%SystemRoot%* コンマ区切りのテキスト ファイルとして作成されます。

これらのログ ファイルの詳細については、「 [NPS データベース形式のログ ファイルの解釈](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2008-R2-and-2008/cc771748%28v=ws.10%29)」を参照してください。 これらのログ ファイルのエントリは、スプレッドシートやデータベースにインポートしないと解釈するのが難しい可能性があります。 ログ ファイルの解釈に役立つ IAS パーサーがオンラインでいくつか見つかります。

次の図は、このようなダウンロード可能な [Shareware アプリケーション](https://www.deepsoftware.com/iasviewer)の出力を示しています。

[Image: Shareware アプリ IAS パーサーのサンプル]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-mfa-nps-extension-vpn"} -->
## VPN と NPS 拡張機能を使用した Microsoft Entra 多要素認証 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-nps-extension-vpn
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Azure 向けのネットワーク ポリシー サーバー拡張機能を使用して VPN インフラストラクチャを Microsoft Entra 多要素認証と統合する

Azure のネットワーク ポリシー サーバー (NPS) 拡張機能を使用すると、組織は、2 段階認証を提供するクラウドベースの [Microsoft Entra 多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfaserver-nps-rdg)を使用して、リモート認証ダイヤルイン ユーザー サービス (RADIUS) クライアント認証を保護できます。

この記事では、Azure の NPS 拡張機能を使用して、NPS インフラストラクチャを MFA と統合する手順を説明します。 このプロセスを実行すると、VPN を使用してネットワークに接続しようとするユーザーに対して安全な 2 段階認証が使用されるようになります。

注

NPS MFA 拡張機能は時間ベースのワンタイム パスワード (TOTP) をサポートしていますが、Windows VPN などの特定の VPN クライアントはサポートしていません。 NPS 拡張機能で有効にする前に、現在使用している VPN クライアントが認証方法として TOTP をサポートしていることを確認してください。

ネットワーク ポリシーとアクセス サービスにより、組織は次のことができるようになります。

- ネットワーク要求を管理および制御するための一元化された場所を割り当てて、以下を指定します。

    - 接続できるユーザー
    - 接続を許可する時刻
    - 接続の期間
    - クライアントが接続に使用する必要があるセキュリティのレベル

        各 VPN やリモート デスクトップ ゲートウェイ サーバーにポリシーを指定するのではなく、一元化された場所で処理される場合のポリシーを指定します。 一元化された認証、承認、アカウンティング (AAA) を提供するために、RADIUS プロトコルが使用されます。
- デバイスにネットワーク リソースへの無制限のアクセスを許可するか制限付きアクセスを許可するかを決定する、ネットワーク アクセス保護 (NAP) クライアント正常性ポリシーを制定し、強制できます。
- 802.1x 対応ワイヤレス アクセス ポイントとイーサネット スイッチへのアクセスに認証と承認を強制する方法を提供できます。 詳細については、「 [ネットワーク ポリシー サーバー」を](https://learn.microsoft.com/ja-jp/windows-server/networking/technologies/nps/nps-top)参照してください。

組織がセキュリティを強化し、高水準のコンプライアンスを実現するには、NPS を Microsoft Entra 多要素認証と統合して、ユーザーが 2 段階認証を使用して VPN サーバーの仮想ポートに接続する方法があります。 ユーザーはアクセスの許可を得るために、ユーザー名とパスワードの組み合わせを、ユーザーが管理している他の情報と共に提供する必要があります。 これは、信頼できる、簡単に複製できない情報にする必要があります。 たとえば、携帯電話番号、固定電話番号、モバイル デバイス上のアプリケーションなどです。

組織で VPN を使っていて、ユーザーが Authenticator のプッシュ通知と共に TOTP コードを登録している場合、ユーザーは MFA チャレンジを満たすことができず、リモートでのサインインに失敗します。 このケースでは、OVERRIDE\_NUMBER\_MATCHING\_WITH\_TOP = FALSE と設定することで、Authenticator の承認/禁止のプッシュ通知にフォールバックできます。

NPS 拡張機能が VPN ユーザーに機能し続けるには、NPS サーバー上にこのレジストリ キーを作成する必要があります。 NPS サーバーで、レジストリ エディターを開きます。 次のように移動します。

HKEY\_LOCAL\_MACHINE\SOFTWARE\Microsoft\AzureMfa

以下の文字列と値のペアを作成します。

名前: OVERRIDE\_NUMBER\_MATCHING\_WITH\_OTP

値 = FALSE

Azure の NPS 拡張機能を利用できるようになる前は、NPS と MFA の統合環境の 2 段階認証の実装を希望するお客様は、オンプレミス環境に別の MFA サーバーを構成し、管理する必要がありました。 リモート デスクトップ ゲートウェイと Azure Multi-Factor Authentication Server では、RADIUS を使用してこの種類の認証が提供されます。

組織は Azure の NPS 拡張機能を使用して、オンプレミス ベースの MFA ソリューションとクラウド ベースの MFA ソリューションのどちらを RADIUS クライアント認証の保護用にデプロイするかを選択できるようになりました。

### 認証フロー

ユーザーが VPN サーバー上の仮想ポートに接続する場合、最初に多様なプロトコルを使用して認証を受ける必要があります。 これらのプロトコルでは、ユーザー名とパスワードの組み合わせと、証明書ベースの認証方法を使用できます。

認証と ID の確認に加え、ユーザーには適切なダイヤルインのアクセス許可が必要です。 単純な実装では、アクセスを許可するこれらのダイヤルインのアクセス許可は、Active Directory ユーザー オブジェクトで直接設定します。

[Image: Active Directory ユーザーとコンピューターのユーザー プロパティの [ダイヤルイン] タブ]

単純な実装では、各 VPN サーバーは、各ローカル VPN サーバーで定義されているポリシーに基づいてアクセスを許可または拒否します。

大規模でスケーラブルな実装では、VPN アクセスを許可または拒否するポリシーは、RADIUS サーバーで一元化されます。 このような場合、VPN サーバーは、接続要求とアカウント メッセージを RADIUS サーバーに転送するアクセス サーバー (RADIUS クライアント) として機能します。 VPN サーバーの仮想ポートに接続するには、ユーザーは認証を受け、RADIUS サーバーで一元的に定義された条件を満たす必要があります。

Azure の NPS 拡張機能を NPS と統合した場合、正常な認証フローは次のようになります。

1. VPN サーバーが、リソース (リモート デスクトップ セッションなど) に接続するためのユーザー名とパスワードが含まれた認証要求を VPN ユーザーから受信します。
2. RADIUS クライアントとして機能する VPN サーバーは、要求を RADIUS *アクセス要求* メッセージに変換し、NPS 拡張機能がインストールされている RADIUS サーバーに (暗号化されたパスワードを使用して) 送信します。
3. Active Directory でユーザー名とパスワードの組み合わせが検証されます。 ユーザー名またはパスワードが正しくない場合、RADIUS サーバーは *アクセス拒否* メッセージを送信します。
4. NPS 接続要求とネットワーク ポリシーの条件 (時刻やグループ メンバーシップの制限など) が満たされている場合、NPS 拡張機能は Microsoft Entra 多要素認証によるセカンダリ認証を要求します。
5. Microsoft Entra 多要素認証は、Microsoft Entra ID と通信し、ユーザーの詳細を取得し、ユーザーが構成した方法 (携帯電話の呼び出し、テキスト メッセージ、またはモバイル アプリ) を使用してセカンダリ認証を実行します。
6. MFA チャレンジが成功すると、Microsoft Entra 多要素認証は結果を NPS 拡張機能に送信します。
7. 接続試行が認証および承認された後、拡張機能がインストールされている NPS は、RADIUS *Access-Accept* メッセージを VPN サーバー (RADIUS クライアント) に送信します。
8. ユーザーは、VPN サーバーの仮想ポートへのアクセスが許可され、暗号化された VPN トンネルを確立します。

### 前提条件

このセクションでは、MFA を VPN と統合する前に満たす必要がある前提条件について詳しく説明します。 作業を開始する前に、次の前提条件を満たしておく必要があります。

- VPN インフラストラクチャ
- ネットワーク ポリシーとアクセス サービス ロール
- Microsoft Entra 多要素認証ライセンス
- Windows Server ソフトウェア
- ライブラリ
- オンプレミスの Active Directory と同期した Microsoft Entra ID
- Microsoft Entra GUID ID

#### VPN インフラストラクチャ

この記事では、Microsoft Windows Server 2016 を使用する適切な作業用 VPN インフラストラクチャがあり、現時点では VPN サーバーが接続要求を RADIUS サーバーに転送するように構成されていないことを前提としています。 この記事では、中央の RADIUS サーバーを使用するように VPN インフラストラクチャを構成します。

動作する VPN インフラストラクチャがない場合は、Microsoft およびサード パーティのサイトで入手できる多数の VPN セットアップ チュートリアルのガイダンスに従って、簡単に作成できます。

#### ネットワーク ポリシーとアクセス サービス ロール

ネットワーク ポリシーとアクセス サービスは、RADIUS サーバーと RADIUS クライアントの機能を提供します。 この記事では、環境内のメンバー サーバーまたはドメイン コントローラーにネットワーク ポリシーとアクセス サービス ロールがインストールされていることを前提としています。 このガイドで、VPN 構成の RADIUS を構成します。 VPN サーバー *以外* のサーバーにネットワーク ポリシーとアクセス サービスの役割をインストールします。

ネットワーク ポリシーおよびアクセス サービスの役割サービス Windows Server 2012 以降のインストールの詳細については、「 [NAP 正常性ポリシー サーバーのインストール](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2008-R2-and-2008/dd296890%28v=ws.10%29)」を参照してください。 Windows Server 2016 では、NAP は非推奨となります。 ドメイン コントローラーに NPS をインストールするための推奨事項など、NPS のベスト プラクティスについては、「 [NPS のベスト プラクティス](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2008-R2-and-2008/cc771746%28v=ws.10%29)」を参照してください。

#### Windows Server ソフトウェア

NPS 拡張機能を使用するには、ネットワーク ポリシーとアクセス サービス ロールがインストールされた Windows Server 2008 R2 SP1 以降が必要です。 このガイドの手順はすべて Windows Server 2016 を使用して実行されました。

#### ライブラリ

NPS 拡張機能を含め、次のライブラリが自動的にインストールされます。

- [Visual Studio 2013 用 Visual C++ 再頒布可能パッケージ (X64)](https://www.microsoft.com/download/details.aspx?id=40784)

Microsoft Graph PowerShell モジュールがまだ存在しない場合は、セットアップ プロセスの一部として実行する構成スクリプトと共にインストールされます。 事前に Graph PowerShell をインストールする必要はありません。

#### オンプレミスの Active Directory と同期した Microsoft Entra ID

NPS 拡張機能を使用するには、オンプレミス ユーザーを Microsoft Entra ID と同期し、MFA を有効にする必要があります。 このガイドでは、オンプレミス ユーザーが Microsoft Entra Connect 経由で Microsoft Entra ID と同期されていることを前提としています。 MFA に対してユーザーを有効にする手順については、次のセクションを参照してください。

Microsoft Entra Connect の詳細については、「 [オンプレミスのディレクトリを Microsoft Entra ID と統合する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/whatis-hybrid-identity)。

#### Microsoft Entra GUID ID

NPS 拡張機能をインストールするには、Microsoft Entra ID の GUID を理解している必要があります。 Microsoft Entra ID の GUID を確認する手順については、次のセクションで説明します。

### VPN 接続用の RADIUS の構成

メンバー サーバーに NPS ロールをインストールした場合は、VPN 接続を要求する VPN クライアントを認証および承認するように構成する必要があります。

このセクションでは、ネットワーク ポリシーとアクセス サービスの役割をインストールしたが、インフラストラクチャで使用するように構成していないことを前提としています。

注

一元化された RADIUS サーバーを認証に使用する作業用 VPN サーバーが既にある場合は、このセクションを省略して構いません。

#### Active Directory にサーバーを登録する

このシナリオで正常に機能させるには、NPS サーバーを Active Directory に登録する必要があります。

1. サーバー マネージャを開きます。
2. サーバー マネージャーで、[ **ツール**] を選択し、[ **ネットワーク ポリシー サーバー**] を選択します。
3. [ネットワーク ポリシー サーバー] コンソールで、[ **NPS (ローカル)]** を右クリックし、[ **Active Directory にサーバーを登録**] を選択します。 [ **OK] を** 2 回選択します。

    [Image: [Active Directory にサーバーを登録する] メニュー オプション]
4. 次の手順で使用するため、コンソールは開いたままにしておきます。

#### ウィザードを使用して RADIUS サーバーを構成する

(ウィザード ベースの) 標準構成オプションまたは詳細構成オプションを使用して、RADIUS サーバーを構成できます。 このセクションでは、ウィザード ベースの標準構成オプションを使用していることを想定しています。

1. ネットワーク ポリシー サーバー コンソールで、 **NPS (ローカル)** を選択します。
2. **[Standard Configuration]\(標準構成\)** で、**ダイヤルアップ接続または VPN 接続用の RADIUS サーバー**を選択し、[**VPN またはダイヤルアップの構成]** を選択します。

    [Image: ダイヤルアップまたは VPN 接続用に RADIUS サーバーを構成する]
3. [ **ダイヤルアップまたは仮想プライベート ネットワーク接続の種類の選択** ] ウィンドウで、[ **仮想プライベート ネットワーク接続**] を選択し、[ **次へ**] を選択します。

    [Image: 仮想プライベート ネットワーク接続を構成する]
4. [ **ダイヤルアップまたは VPN サーバーの指定** ] ウィンドウで、[ **追加**] を選択します。
5. [ **新しい RADIUS クライアント** ] ウィンドウで、フレンドリ名を指定し、VPN サーバーの解決可能な名前または IP アドレスを入力して、共有シークレットパスワードを入力します。 この共有シークレットのパスワードは、長い複雑なものにします。 このパスワードは次のセクションで必要になるので、メモしておきます。

    [Image: 新しい RADIUS クライアント ウィンドウを作成する]
6. [ **OK] を**選択し、[ **次へ**] を選択します。
7. [ **認証方法の構成** ] ウィンドウで、既定の選択 (**Microsoft Encrypted Authentication バージョン 2 [MS-CHAPv2])** をそのまま使用するか、別のオプションを選択して、[ **次へ**] を選択します。

    注

    拡張認証プロトコル (EAP) を構成する場合は、Microsoft チャレンジ ハンドシェイク認証プロトコル (CHAPv2) または Protected Extensible Authentication Protocol (PEAP) を使用する必要があります。 他の EAP はサポートされていません。
8. [ **ユーザー グループの指定** ] ウィンドウで、[ **追加**] を選択し、適切なグループを選択します。 グループが存在しない場合は、すべてのユーザーにアクセスを許可するために、選択フィールドを空白のままにしておきます。

    [Image: [ユーザー グループの指定] ウィンドウでアクセスを許可または拒否する]
9. [ **次へ**] を選択します。
10. [ **IP フィルターの指定] ウィンドウで** 、[ **次へ**] を選択します。
11. [ **暗号化設定の指定]** ウィンドウで、既定の設定をそのまま使用し、[ **次へ**] を選択します。

    [Image: [暗号化設定の指定] ウィンドウ]
12. [ **領域名の指定]** ウィンドウで、領域名を空白のままにし、既定の設定をそのまま使用して、[ **次へ**] を選択します。

    [Image: [領域名の指定] ウィンドウ]
13. [ **新しいダイヤルアップまたは仮想プライベート ネットワーク接続と RADIUS クライアントの完了** ] ウィンドウで、[ **完了]** を選択します。

    [Image: 完了した構成ウィンドウ]

#### RADIUS 構成を確認する

このセクションでは、ウィザードを使用して作成した構成について詳しく説明します。

1. ネットワーク ポリシー サーバーの NPS (ローカル) コンソールで、[ **RADIUS クライアント**] を展開し、[ **RADIUS クライアント**] を選択します。
2. 詳細ウィンドウで、作成した RADIUS クライアントを右クリックし、[ **プロパティ**] を選択します。 RADIUS クライアント (VPN サーバー) のプロパティが次のように表示されます。

    [Image: VPN のプロパティと構成を確認する]
3. [ **キャンセル] を選択します**。
4. ネットワーク ポリシー サーバーの NPS (ローカル) コンソールで、[ **ポリシー**] を展開し、[ **接続要求ポリシー**] を選択します。 次の図のように VPN 接続ポリシーが表示されます。

    [Image: VPN 接続ポリシーを示す接続要求ポリシー]
5. [ **ポリシー] で**、[ **ネットワーク ポリシー**] を選択します。 次の画像のような仮想プライベート ネットワーク (VPN) 接続ポリシーが表示されます。

    [Image: 仮想プライベート ネットワーク接続ポリシーを示すネットワーク ポリシー]

### RADIUS 認証を使用するための VPN サーバーの構成

このセクションでは、RADIUS 認証を使用するように VPN サーバーを構成します。 この手順では、VPN サーバーの動作構成はあるものの、RADIUS 認証を使用するように構成していないことを前提としています。 VPN サーバーの構成後、構成が予想どおりに動作していることを確認します。

注

RADIUS 認証を使用する動作中の VPN サーバー構成が既にある場合は、このセクションを省略して構いません。

#### 認証プロバイダーを構成する

1. VPN サーバーで、サーバー マネージャーを開きます。
2. サーバー マネージャーで、[ **ツール**] を選択し、[ **ルーティングとリモート アクセス**] を選択します。
3. [ **ルーティングとリモート アクセス** ] ウィンドウで、 **&lt;サーバー名&gt; (ローカル)** を右クリックし、[ **プロパティ**] を選択します。
4. **&lt;サーバー名&gt; (ローカル) の [プロパティ**] ウィンドウで、[**セキュリティ**] タブを選択します。
5. [ **セキュリティ** ] タブの [ **認証プロバイダー**] で、[ **RADIUS 認証**] を選択し、[ **構成**] を選択します。

    [Image: RADIUS 認証プロバイダーの構成]
6. **[RADIUS 認証**] ウィンドウで、[**追加**] を選択します。
7. [ **RADIUS サーバーの追加** ] ウィンドウで、次の操作を行います。

    1. [ **サーバー名** ] ボックスに、前のセクションで構成した RADIUS サーバーの名前または IP アドレスを入力します。
    2. **[共有シークレット**] で [**変更**] を選択し、先ほど作成して記録した共有シークレットのパスワードを入力します。
    3. [ **タイムアウト (秒)]** ボックスに値 **60** を入力します。 破棄される要求を最小限に抑えるには、VPN サーバーを少なくとも 60 秒のタイムアウトで構成することをお勧めします。 必要に応じて、またはイベント ログの破棄された要求を減らすために、VPN サーバーのタイムアウト値を 90 秒または 120 秒に増やすことができます。
8. [ **OK] を選択します**。

#### VPN 接続をテストする

このセクションでは、VPN 仮想ポートに接続しようとしたときに、RADIUS サーバーが VPN クライアントを認証して承認することを確認します。 この手順では、Windows 10 を VPN クライアントとして使用していることを前提としています。

注

VPN サーバーに接続するように VPN クライアントを既に構成し、設定を保存している場合は、VPN 接続オブジェクトの構成と保存に関連する手順をスキップできます。

1. VPN クライアント コンピューターで、[ **スタート** ] ボタンを選択し、[ **設定]** ボタンを選択します。
2. **[Windows の設定]** ウィンドウで、[**ネットワーク] と [インターネット**] を選択します。
3. **[VPN**] を選択します。
4. [ **VPN 接続の追加] を**選択します。
5. [ **VPN 接続の追加** ] ウィンドウの **[VPN プロバイダー** ] ボックスで、 **Windows (組み込み)** を選択し、必要に応じて残りのフィールドに入力し、[保存] を選択 **します**。

    [Image: [VPN 接続の追加] ウィンドウ]
6. **[コントロール パネル**] に移動し、[**ネットワークと共有センター**] を選択します。
7. [ **アダプター設定の変更] を選択します**。

    [Image: ネットワークと共有センター - アダプターの設定を変更する]
8. VPN ネットワーク接続を右クリックし、[ **プロパティ**] を選択します。
9. [VPN のプロパティ] ウィンドウで、[ **セキュリティ** ] タブを選択します。
10. [ **セキュリティ** ] タブで、 **Microsoft CHAP Version 2 (MS-CHAP v2)** のみが選択されていることを確認し、[ **OK] を選択します**。

    [Image: [Allow these protocols](https://learn.microsoft.com/ja-jp/entra/identity/authentication/これらのプロトコルを許可する) オプション]
11. VPN 接続を右クリックし、[ **接続**] を選択します。
12. **[設定]** ウィンドウで、[**接続**] を選択します。 成功した接続は、次に示すように、RADIUS サーバーのセキュリティ ログにイベント ID 6272 として表示されます。

    [Image: 成功した接続を示す [イベントのプロパティ] ウィンドウ]

### RADIUS のトラブルシューティング

認証と承認に一元化された RADIUS サーバーを使用するように VPN サーバーを構成するまでは VPN 構成が動作していたとします。 構成が機能していた場合は、RADIUS サーバーの構成が間違っているか、無効なユーザー名またはパスワードを使用して問題が発生した可能性があります。 たとえば、ユーザー名に代替 UPN サフィックスを使用している場合、サインイン試行が失敗する可能性があります。 同じアカウント名を使用することをお勧めします。

これらの問題のトラブルシューティングを行うときは、まず、RADIUS サーバーのセキュリティ イベント ログを調べることをお勧めします。 イベントの検索時間を節約するには、次に示すように、イベント ビューアーのロールベースの [ネットワーク ポリシーとアクセス サービス] カスタム ビューを使用します。 "イベント ID 6273" は、NPS がユーザーに対してアクセスを拒否したイベントを示しています。

[Image: NPAS イベントを示すイベント ビューアー]

### 多要素認証を構成する

多要素認証用にユーザーを構成する方法については、[クラウドベースの Microsoft Entra 多要素認証の展開の計画](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-getstarted#plan-conditional-access-policies)と [2 段階認証用のアカウントの設定に関](https://support.microsoft.com/account-billing/how-to-use-the-microsoft-authenticator-app-9783c865-0308-42fb-a519-8cf666fe0acc)する記事を参照してください。

### NPS 拡張機能のインストールと構成

このセクションでは、VPN サーバーでのクライアント認証に MFA を使用するように VPN を構成する手順について説明します。

注

REQUIRE\_USER\_MATCH レジストリ キーでは、大文字と小文字が区別されます。 値はすべて大文字の形式で設定する必要があります。

NPS 拡張機能をインストールして構成した後、このサーバーで MFA を使用するには、すべての RADIUS ベースのクライアント認証が必要になります。 すべての VPN ユーザーを Microsoft Entra 多要素認証に登録していない場合、次のいずれかを実行できます。

- 別の RADIUS サーバーを設定して、MFA を使用するように構成されていないユーザーを認証します。
- ユーザーが Microsoft Entra 多要素認証に登録されている場合、チャレンジされたユーザーが 2 つ目の認証要素を提供できるようにするレジストリ エントリを作成します。

*HKLM\SOFTWARE\Microsoft\AzureMfa に REQUIRE\_USER\_MATCH* という名前の新しい文字列値を作成し、*値を TRUE* または FALSE に設定*します*。

[Image: [Require User Match](https://learn.microsoft.com/ja-jp/entra/identity/authentication/ユーザー一致を要求する) 設定]

値が *TRUE* に設定されている場合、または空白の場合、すべての認証要求は MFA チャレンジの対象となります。 値が *FALSE* に設定されている場合、MFA チャレンジは、Microsoft Entra 多要素認証に登録されているユーザーにのみ発行されます。 *FALSE* 設定は、オンボーディング期間中のテスト環境または運用環境でのみ使用します。

#### ディレクトリ テナント ID を入手します。

NPS 拡張機能の構成の一環として、管理者資格情報と Microsoft Entra テナントの ID を入力する必要があります。 テナント ID を取得するには、次の手順のようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Overview**&gt;**Properties** に移動します。

    [Image: Microsoft Entra 管理センターからテナント ID を取得する]

#### NPS 拡張機能のインストール

ネットワーク ポリシーとアクセス サービスの役割がインストールされ、設計で RADIUS サーバーとして機能するサーバーに、NPS 拡張機能をインストールする必要があります。 VPN サーバーに NPS 拡張機能をインストール *しないでください* 。

1. [Microsoft ダウンロード センターから NPS 拡張機能をダウンロードします](https://aka.ms/npsmfa)。
2. セットアップ実行可能ファイル (*NpsExtnForAzureMfaInstaller.exe*) を NPS サーバーにコピーします。
3. NPS サーバーで、NpsExtnForAzureMfaInstaller.exe をダブルクリックし、メッセージが表示されたら、[の実行] 選択します。
4. **[NPS Extension For Microsoft Entra Multifactor authentication Setup]\(Microsoft Entra 多要素認証の NPS 拡張機能のセットアップ\**) ウィンドウで、ソフトウェア ライセンス条項を確認し、[**ライセンス条項に同意する**] チェック ボックスをオンにして、[**インストール**] を選択します。

    [Image: [Nps Extension for Microsoft Entra Multifactor authentication Setup](Microsoft Entra 多要素認証セットアップの NPS 拡張機能) ウィンドウ]
5. **[NPS Extension For Microsoft Entra Multifactor authentication Setup]\(Microsoft Entra 多要素認証の NPS 拡張機能のセットアップ\**) ウィンドウで、[**閉じる**] を選択します。

    [Image: [セットアップが成功しました] 確認ウィンドウ]

#### Graph PowerShell スクリプトを使用して NPS 拡張機能で使用する認定資格証を構成する

セキュリティで保護された通信と保証を確保するには、NPS 拡張機能で使用する証明書を構成します。 NPS コンポーネントには、NPS で使用する自己署名認定資格証を構成する Graph PowerShell スクリプトが含まれています。

このスクリプトは、次のアクションを実行します。

- 自己署名証明書を作成する。
- Microsoft Entra ID のサービス プリンシパルに資格認定証の公開キーを関連付ける。
- ローカル コンピューターのストアに証明書を格納する。
- ネットワーク ユーザーに証明書の秘密キーへのアクセスを許可する。
- NPS サービスを再起動する。

独自の認定資格証を使用する場合は、Microsoft Entra ID のサービス プリンシパルへの認定資格証の公開キーを関連付ける必要があります。

スクリプトを使用するには、Microsoft Entra 管理者の資格情報と、先ほどコピーした Microsoft Entra テナント ID を拡張機能に入力します。 このアカウントは、拡張機能を有効にするのと同じ Microsoft Entra テナントに存在する必要があります。 NPS 拡張機能がインストールされている各 NPS サーバーでスクリプトを実行します。

1. Graph PowerShell を管理者として実行します。
2. PowerShell コマンド プロンプトで、「 **c:\Program Files\Microsoft\AzureMfa\Config」と**入力し、Enter キーを押します。
3. 次のコマンド プロンプトで、「 **.\AzureMfaNpsExtnConfigSetup.ps1**」と入力し、Enter キーを押します。 このスクリプトで、Graph PowerShell がインストールされているかどうかがチェックされます。 インストールされていない場合は、スクリプトによって Graph PowerShell がインストールされます。

    [Image: AzureMfsNpsExtnConfigSetup.ps1 構成スクリプトの実行]

    TLS によってセキュリティ エラーが発生した場合は、PowerShell プロンプトから `[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12` コマンドを使用して TLS 1.2 を有効にします。

    スクリプトによって PowerShell モジュールのインストールが確認されると、Graph PowerShell モジュールのサインイン ウィンドウが表示されます。
4. Microsoft Entra 管理者の資格情報とパスワードを入力し、[ **サインイン**] を選択します。
5. コマンド プロンプトに先ほどコピーしたテナント ID を貼り付け、Enter キーを押します。

    [Image: 前にコピーした Microsoft Entra テナント ID を入力します]

    スクリプトによって自己署名証明書が作成され、他の構成変更が実行されます。 出力は次の図のようになります。

    [Image: 自己署名証明書を示す PowerShell ウィンドウ]
6. サーバーを再起動します。

#### 構成を確認する

構成を確認するには、VPN サーバーとの新しい VPN 接続を確立する必要があります。 プライマリ認証の資格情報を正常に入力すると、VPN 接続は、次のセクションに示すように、接続が確立される前にセカンダリ認証が成功するまで待機します。

[Image: [Windows 設定 VPN] ウィンドウ]

以前に Microsoft Entra 多要素認証で構成したセカンダリ検証方法で正常に認証された場合は、リソースに接続されます。 ただし、セカンダリ認証が失敗した場合、リソースへのアクセスは拒否されます。

次の例では、Windows Phone の Microsoft Authenticator アプリでセカンダリ認証を提供しています。

[Image: Windows Phone での MFA プロンプトの例]

セカンダリ メソッドを使用して正常に認証されると、VPN サーバー上の仮想ポートへのアクセス権が付与されます。 信頼済みデバイスでモバイル アプリを使用したセカンダリ認証方法を使用する必要があったため、ユーザー名とパスワードの組み合わせだけを使用する場合よりもサインイン プロセスの安全性が高まります。

#### 成功したサインイン イベントのイベント ビューアーのログを表示する

Windows イベント ビューアーで成功したサインイン イベントを表示するには、次の図に示されているように、セキュリティ ログまたはネットワーク ポリシーおよびアクセス サービスのカスタム ビューを表示します。

[Image: ネットワーク ポリシー サーバー ログの例]

Microsoft Entra 多要素認証用の NPS 拡張機能をインストールしたサーバーで、拡張機能に固有のイベント ビューアー アプリケーション ログは、 *Application and Services Logs\Microsoft\AzureMfa* にあります。

[Image: イベント ビューアーの [AuthZ ログ] ウィンドウの例]

### トラブルシューティング ガイド

構成が期待どおりに動作しない場合は、ユーザーが MFA を使用するように構成されていることを確認してトラブルシューティングを開始します。 ユーザーに [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインさせます。 ユーザーがセカンダリ認証を求められ、正常に認証できれば、MFA の構成には問題がないことがわかります。

ユーザーの MFA が機能している場合、関連するイベント ビューアー ログを確認する必要があります。 ログには、前のセクションで説明したセキュリティ イベント ログ、ゲートウェイの操作ログ、Microsoft Entra 多要素認証ログが含まれます。

失敗したサインイン イベント (イベント ID 6273) が表示されたセキュリティ ログの例を次に示します。

[Image: 失敗したサインイン イベントを示すセキュリティ ログ]

Microsoft Entra 多要素認証ログからの関連イベントを次に示します。

[Image: Microsoft Entra 多要素認証ログ]

高度なトラブルシューティングを実行するには、NPS サービスがインストールされているサーバーで NPS データベース形式のログ ファイルを参照します。 ログ ファイルは、 *%SystemRoot%\System32\Logs* フォルダーにコンマ区切りのテキスト ファイルとして作成されます。 ログ ファイルの詳細については、「 [NPS データベース形式のログ ファイルの解釈](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2008-R2-and-2008/cc771748%28v=ws.10%29)」を参照してください。

これらのログ ファイルのエントリは、スプレッドシートやデータベースにインポートしないと解釈が困難です。 ログ ファイルを解釈する場合に役立つ Internet Authentication Service (IAS) 解析ツールは、オンラインで多数見つかります。 このようなダウンロード可能な [Shareware アプリケーション](https://www.deepsoftware.com/iasviewer) の出力を次に示します。

[Image: Shareware アプリ IAS パーサーのサンプル]

追加のトラブルシューティングを行うには、Wireshark や [Microsoft Message Analyzer](https://learn.microsoft.com/ja-jp/message-analyzer/microsoft-message-analyzer-operating-guide) などのプロトコル アナライザーを使用できます。 Wireshark の次の画像は、VPN サーバーと NPS 間の RADIUS メッセージを示しています。

[Image: フィルター処理されたトラフィックを示す Microsoft Message Analyzer]

詳細については、「 [既存の NPS インフラストラクチャを Microsoft Entra 多要素認証と統合](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-nps-extension)する」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-mfa-reporting"} -->
## Microsoft Entra 多要素認証のサインイン イベントの詳細 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-reporting
- Service: entra-id / authentication
- Article date: 2025-06-24
- Summary: Microsoft Entra 多要素認証イベントとステータス メッセージのサインイン アクティビティを表示する方法について説明します。

Microsoft Entra 多要素認証イベントを確認して理解するには、Microsoft Entra サインイン ログを使用できます。 このレポートには、ユーザーが多要素認証を求められた場合や、条件付きアクセス ポリシーが使用されていた場合のイベントの認証の詳細が表示されます。 サインイン ログの詳細については、 [Microsoft Entra ID でのサインイン アクティビティ レポートの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)を参照してください。

### MFA の解釈に関する注意

ユーザーが初めて Microsoft Entra に対話形式でサインインするときは、厳密に必要でない場合でも、サポートされている任意の認証方法 (強力な認証を含む) を使用できます。 ユーザーがパスワードレスまたは別の強力な認証方法を使用して認証することを選択した場合、ユーザーは MFA 要求を受け取ります。 アプリケーションと認証サービスの間の待機時間と不要なリダイレクトを減らすために、リソース プロバイダーは通常、毎回新しい要求セットを要求するのではなく、認証するユーザーに既に与えられている既存の要求を確認します。 その結果、ユーザーの以前の MFA 要求が受け入れられたため、アプリケーションに MFA 要件があるにもかかわらず、特定のサインインが "単一要素" として表示される可能性があります。 その特定の認証に対して MFA 要件が要求またはログに記録されませんでした。 認証コンテキストを正確に理解するには、各イベントに関連付けられている MFA の詳細とルート認証方法の両方を常に確認することが重要です。 明示的に強力な認証を使用する必要がないため、以前に満たされていた MFA 要求は考慮されないため、 `authenticationRequirement` フィールドだけに依存しないでください。

### Microsoft Entra サインイン ログを表示する

サインイン ログには、マネージド アプリケーションとユーザー サインイン アクティビティの使用状況に関する情報が提供されます。これには、多要素認証の使用状況に関する情報が含まれます。 MFA データを使用すると、組織での MFA の動作に関する分析情報が得られます。 次のような質問に答えます。

- MFA を使用したサインイン チャレンジが実行されたかどうか。
- ユーザーはどのように MFA を完了しましたか?
- サインイン中に使用された認証方法はどれですか?
- ユーザーが MFA を完了できなかったのはなぜですか?
- MFA に対してチャレンジされるユーザーの数はいくつですか?
- MFA チャレンジを完了できないユーザーの数
- エンド ユーザーが実行している MFA の一般的な問題は何ですか?

[Microsoft Entra 管理センター](https://entra.microsoft.com)でサインイン アクティビティ レポートを表示するには、次の手順を実行します。 [レポート API](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-prerequisites-for-reporting-api) を使用してデータのクエリを実行することもできます。

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. 左側のメニューから **Entra ID**&gt;**Users** に移動します。
3. 左側のメニューから、[ **サインイン ログ**] を選択します。
4. 状態を含むサインイン イベントの一覧が表示されます。 イベントを選択すると、詳細を表示できます。

    イベントの詳細の **[条件付きアクセス** ] タブには、MFA プロンプトをトリガーしたポリシーが表示されます。

    [Image: Microsoft Entra サインイン ログの例のスクリーンショット]

使用可能な場合は、テキスト メッセージ、Microsoft Authenticator アプリ通知、電話呼び出しなどの認証が表示されます。

[ **認証の詳細** ] タブには、認証の試行ごとに次の情報が表示されます。

- 適用された認証ポリシーの一覧 (条件付きアクセス、ユーザーごとの MFA、セキュリティの既定値など) は、各サインインの詳細ビューの [条件付きアクセス] セクションに表示されます
- サインインに使用される認証方法のシーケンス
- 認証の試行が成功したかどうか
- 認証の試行が成功または失敗した理由の詳細

この情報により、管理者はユーザーのサインインの各手順のトラブルシューティングを行い、次の情報を追跡できます。

- 多要素認証によって保護されるサインインの量
- 各認証方法の使用状況と成功率
- パスワードレス認証方法の使用 (パスワードレス電話サインイン、FIDO2、Windows Hello for Business など)
- トークン要求によって認証要件が満たされる頻度 (ユーザーが対話形式でパスワードの入力や SMS OTP の入力などを求められることはありません)

サインイン ログを表示しているときに、[ **認証の詳細** ] タブを選択します。

[Image: [認証の詳細] タブのスクリーンショット]

注

**OATH 検証コードは、OATH** ハードウェア トークンとソフトウェア トークン (Microsoft Authenticator アプリなど) の両方の認証方法としてログに記録されます。

重要

[ **認証の詳細** ] タブには、ログ情報が完全に集計されるまで、最初は不完全または不正確なデータが表示される場合があります。 既知の例を次に示します。

- サインイン イベントが最初にログに記録されると、トークン メッセージの **要求によって満** たされた値が正しく表示されません。
- **プライマリ認証**行は最初にログに記録されません。

MFA 要求が満たされたか拒否されたかを示すサインイン イベントの [ **認証の詳細]** ウィンドウに、次の詳細が表示されます。

- MFA が満たされた場合、この列には MFA が満たされた方法に関する詳細情報が表示されます。

    - クラウドで完了
    - テナントで設定されたポリシーにより期限切れとなりました
    - 登録のプロンプトが表示される
    - satisfied by claim in the token (トークンの要求によって満たされました)
    - satisfied by claim provided by external provider (外部プロバイダーから送信された要求によって満たされました)
    - satisfied by strong authentication (強力な認証によって満たされました)
    - skipped as flow exercised was Windows broker logon flow (実行されたフローが Windows ブローカー ログオン フローのためスキップされました)
    - アプリのパスワードが原因でスキップされました
    - 場所が原因でスキップされました
    - skipped due to registered device (登録済みデバイスによりスキップされました)
    - skipped due to remembered device (記憶済みデバイスによりスキップされました)
    - 正常に完了しました
- MFA が拒否された場合、この列は拒否の理由を示します。

    - 認証の進行中
    - 重複する認証の試行
    - 正しくないコードが何度も入力されました
    - 無効な認証
    - 無効なモバイル アプリ検証コード
    - 誤設定
    - phone call went to voicemail (ボイスメールに対する電話の呼び出し)
    - 電話番号の形式が無効です
    - サービス エラー
    - ユーザーの電話に接続できない
    - モバイル アプリ通知をデバイスに送信できない
    - モバイル アプリ通知を送信できない
    - ユーザーが認証を拒否した
    - ユーザーがモバイル アプリの通知に応答しなかった
    - ユーザーに認証方法が登録されていない
    - ユーザーが正しくないコードを入力した
    - ユーザーが正しくない PIN を入力しました
    - ユーザーが認証に成功せずに電話を切った
    - ユーザーがブロックされている
    - ユーザーが確認コードを入力しなかった
    - ユーザーが見つかりません
    - 検証コードは既に 1 回使用されています

### MFA に登録されているユーザーに関する PowerShell レポート

まず、 [Microsoft Graph PowerShell SDK のインストール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) がインストールされていることを確認します。

次の PowerShell を使用して、MFA に登録したユーザーを特定します。 これらのアカウントは Microsoft Entra ID に対して認証できないため、この一連のコマンドは無効なユーザーを除外します。

```powershell
Get-MgUser -All | Where-Object {$_.StrongAuthenticationMethods -ne $null -and $_.BlockCredential -eq $False} | Select-Object -Property UserPrincipalName
```

次の PowerShell コマンドを実行して、MFA に登録されていないユーザーを特定します。 これらのアカウントは Microsoft Entra ID に対して認証できないため、この一連のコマンドは無効なユーザーを除外します。

```powershell
Get-MgUser -All | Where-Object {$_.StrongAuthenticationMethods.Count -eq 0 -and $_.BlockCredential -eq $False} | Select-Object -Property UserPrincipalName
```

登録されているユーザーと出力メソッドを識別します。

```powershell
Get-MgUser -All | Select-Object @{N='UserPrincipalName';E={$_.UserPrincipalName}},@{N='MFA Status';E={if ($_.StrongAuthenticationRequirements.State){$_.StrongAuthenticationRequirements.State} else {"Disabled"}}},@{N='MFA Methods';E={$_.StrongAuthenticationMethods.methodtype}} | Export-Csv -Path c:\MFA_Report.csv -NoTypeInformation
```

### その他の MFA レポート

クラウド MFA アクティビティ用の NPS 拡張機能と AD FS アダプターが、特定のアクティビティ レポートではなく、サインイン ログに含まれるようになりました。

オンプレミスの AD FS アダプターまたは NPS 拡張機能からのクラウド MFA サインイン イベントでは、オンプレミス コンポーネントによって返されるデータが制限されているため、サインイン ログにすべてのフィールドが設定されるわけではありません。 これらのイベントは、resourceID *adfs* またはイベント プロパティ内の *半径* によって識別できます。 これには次のようなものがあります。

- resultSignature
- appID
- デバイス詳細
- 条件付きアクセスステータス
- 認証コンテキスト
- インタラクティブかどうか
- トークン発行者名
- riskDetail、riskLevelAggregated、riskLevelDuringSignIn、riskState、riskEventTypes、riskEventTypes\_v2
- 認証プロトコル
- 受信トークンタイプ

最新バージョンの NPS 拡張機能を実行している組織、または Microsoft Entra Connect Health を使用している組織には、イベント内の場所の IP アドレスがあります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-mfa-reporting-datacollection"} -->
## Microsoft Entra によるユーザー データの収集 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-reporting-datacollection
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: ユーザーの認証を補助するため、セルフサービス パスワード リセットと Microsoft Entra 多要素認証によってどのような情報が使われるのでしょうか。

この記事では、Microsoft Entra 多要素認証 (クラウドベース) とセルフサービス パスワード リセット (SSPR) によって収集されたユーザー情報を、削除する場合に検索する方法について説明します。

注

個人データの表示または削除については、[GDPR の Windows データ主体要求](https://learn.microsoft.com/ja-jp/microsoft-365/compliance/gdpr-dsr-windows)に関するページで Microsoft のガイダンスをご確認ください。 GDPR に関する一般情報については、[Microsoft Trust Center の GDPR に関するセクション](https://www.microsoft.com/trust-center/privacy/gdpr-overview)および [Service Trust Portal の GDPR に関するセクション](https://servicetrust.microsoft.com/ViewPage/GDPRGetStarted)をご覧ください。

### 収集される MFA 情報

MFA サーバー、NPS 拡張機能、および Windows Server 2016 Microsoft Entra 多要素認証 AD FS アダプターでは、次の情報が収集されて 90 日間保存されます。

認証の試行 (レポートとトラブルシューティングに使用):

- タイムスタンプ
- ユーザー名
- 名
- 姓
- 電子メール アドレス
- ユーザー グループ
- 認証方法 (電話、テキスト メッセージ、モバイル アプリ、OATH トークン)
- 電話呼び出しモード (標準、PIN)
- テキスト メッセージの方向 (一方向、双方向)
- テキスト メッセージのモード (OTP、OTP + PIN)
- モバイル アプリのモード (標準、PIN)
- OATH トークンのモード (標準、PIN)
- 認証の種類
- アプリケーション名
- メインの電話番号の国番号
- メインの電話番号
- メインの電話番号の内線番号
- 認証されたメインの電話番号
- メインの電話番号の呼び出し結果
- 予備の電話番号の国番号
- 予備の電話番号
- 予備の電話番号の内線番号
- 認証された予備の電話番号
- 予備の電話番号の呼び出し結果
- 全体の認証
- 全体の結果
- 結果
- 認証済み
- 結果
- 発信 IP アドレス
- デバイス
- デバイス トークン
- デバイスの種類
- モバイル アプリのバージョン
- OS バージョン
- 結果
- 使用された通知の確認

アクティブ化 (Microsoft Authenticator モバイル アプリでアカウントをアクティブ化する試行):

- ユーザー名
- アカウント名
- タイムスタンプ
- アクティブ化コードの取得の結果
- アクティブ化の成功
- アクティブ化のエラー
- アクティブ化の状態の結果
- デバイス名
- デバイスの種類
- アプリのバージョン
- OATH トークンが有効

ブロック (ブロックされた状態の判定とレポートのために使用):

- ブロックのタイムスタンプ
- ユーザー名によるブロック
- ユーザー名
- 国番号
- 電話番号
- 書式化された電話番号
- 拡張機能
- クリーンな内線番号
- ブロックされました
- ブロックした理由
- 完了のタイムスタンプ
- 完了理由
- アカウントのロックアウト
- 不正アクセスのアラート
- ブロックされなかった不正アクセス アラート
- 言語

バイパス (レポートに使用):

- バイパスのタイムスタンプ
- バイパスの秒数
- ユーザー名によるバイパス
- ユーザー名
- 国番号
- 電話番号
- 書式化された電話番号
- 拡張機能
- クリーンな内線番号
- バイパスの理由
- 完了のタイムスタンプ
- 完了理由
- バイパスの使用

変更 (MFA サーバーまたは Microsoft Entra ID にユーザーの変更を同期するために使用):

- 変更のタイムスタンプ
- ユーザー名
- 新しい国番号
- 新しい電話番号
- 新しい内線番号
- 新しい予備の電話番号の国番号
- 新しい予備の電話番号
- 新しい予備の内線番号
- 新しい PIN
- PIN の変更が必要
- 古いデバイス トークン
- 新しいデバイス トークン

### NPS 拡張機能からデータを収集する

Microsoft Privacy ポータルを使用して、エクスポートを要求します。

- MFA 情報はエクスポートに含まれます。この操作が完了するまでに数時間または数日かかることがあります。
- AzureMfa/AuthN/AuthNOptCh、AzureMfa/AuthZ/AuthZAdminCh、および AzureMfa/AuthZ/AuthZOptCh イベント ログ内に出現するユーザー名は運用データと見なされ、エクスポートで提供される情報と重複していると見なされます。

### NPS 拡張機能からデータを削除する

Microsoft Privacy ポータルを使用してアカウントの削除要求を行って、このユーザー用に収集されたすべての MFA クラウド サービス情報を削除します。

- データが完全に削除されるまでには最大で 30 日かかる場合があります。

### Microsoft Entra 多要素認証 AD FS アダプターからデータを収集する

Microsoft Privacy ポータルを使用して、エクスポートを要求します。

- MFA 情報はエクスポートに含まれます。この操作が完了するまでに数時間または数日かかることがあります。
- AD FS Tracing/Debug イベント ログ (有効な場合) 内に出現するユーザー名は運用データと見なされ、エクスポートで提供される情報と重複していると見なされます。

### Microsoft Entra 多要素認証 AD FS アダプターからデータを削除する

Microsoft Privacy ポータルを使用してアカウントの削除要求を行って、このユーザー用に収集されたすべての MFA クラウド サービス情報を削除します。

- データが完全に削除されるまでには最大で 30 日かかる場合があります。

### Microsoft Entra 多要素認証に関するデータを収集する

Microsoft Privacy ポータルを使用して、エクスポートを要求します。

- MFA 情報はエクスポートに含まれます。この操作が完了するまでに数時間または数日かかることがあります。

### Microsoft Entra 多要素認証に関するデータを削除する

Microsoft Privacy ポータルを使用してアカウントの削除要求を行って、このユーザー用に収集されたすべての MFA クラウド サービス情報を削除します。

- データが完全に削除されるまでには最大で 30 日かかる場合があります。

### セルフサービス パスワード リセットのデータの削除

ユーザーは SSPR の一部として、セキュリティの質問への回答を追加できます。 セキュリティの質問と回答は、不正アクセスを防ぐためにハッシュ化されます。 ハッシュ化されたデータのみが保存されるため、セキュリティの質問と回答はエクスポートできません。 ユーザーは [\[マイ サインイン\]](https://mysignins.microsoft.com/security-info) にアクセスして、それらを編集または削除できます。 SSPR に保存されるその他の情報は、ユーザーのメール アドレスのみです。

[特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator)ロールが割り当てられているユーザーは、任意のユーザーに対して収集されたデータの削除ができます。 Microsoft Entra ID の [**ユーザー**] ページで、[**認証方法]** を選択し、ユーザーを選択して自分の電話またはメール アドレスを削除します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-mfa-userdevicesettings"} -->
## Microsoft Entra多要素認証の認証方法を管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-userdevicesettings
- Service: entra-id / authentication
- Article date: 2025-02-27
- Summary: Microsoft Entra多要素認証Microsoft Entraユーザー設定を構成する方法について説明します

Microsoft Entra IDのユーザーには、次の 2 つの異なる連絡先情報のセットがあります。

- パブリック プロファイルの連絡先情報。これはユーザー プロファイルで管理され、自分の組織のメンバーに表示されます。 on-premises Active Directoryから同期されたユーザーの場合、この情報はオンプレミスのWindows Server Active Directory Domain Servicesで管理されます。
- 認証方法。これは常に非公開で、多要素認証を含む認証にのみ使用されます。 管理者はユーザーの認証方法ブレードでこれらの方法を管理でき、ユーザーは MyAccount の [セキュリティ情報] ページで自分の方法を管理できます。

ユーザーの多要素認証方法Microsoft Entra管理する場合、認証管理者は次のことができます。

- MFA に使用される電話番号など、特定のユーザーの認証方法を追加します。
- ユーザーのパスワードをリセットします。
- ユーザーに MFA の再登録を要求します。
- セッションを取り消します。
- ユーザーの既存のアプリ パスワードを削除する。

注

このトピックのスクリーンショットでは、Microsoft Entra管理センターで更新されたエクスペリエンスを使用してユーザー認証方法を管理する方法を示します。 従来のエクスペリエンスもあり、管理者は管理センターのバナーを使用して 2 つを切り替えることができます。 最新のエクスペリエンスは従来のエクスペリエンスと完全な同等性を有し、一時アクセス パス、パスキー、その他の設定などの最新のメソッドを管理します。 Microsoft Entra管理センターのレガシ エクスペリエンスは、2025 年 9 月 30 日に廃止されました。 廃止の前に組織がとるべきアクションはありません。

### 前提条件

多要素認証Microsoft Entra。既定で有効になっています。

### ユーザーの認証方法を追加または変更する

Microsoft Entra 管理センターまたは Microsoft Graph PowerShell を使用して、ユーザーの認証方法を追加または変更できます。 Microsoft Entra管理センターでは、ユーザー認証方法を管理するための従来の方法は、2025 年 9 月 30 日以降に廃止されます。

注

セキュリティ上の理由から、パブリック ユーザーの連絡先情報フィールドを使用して MFA を実行しないでください。 代わりに、ユーザーは、MFA に使用する自分の認証方法番号を入力する必要があります。

[Image: Microsoft Entra管理センターから認証方法を追加する方法のスクリーンショット.]

Microsoft Entra管理センターでユーザーの認証方法を追加または変更するには:

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 認証方法を追加または変更するユーザーを選択し、[ **認証方法**] を選択します。
4. ウィンドウの上部にある [ **+ 認証方法の追加]**を選択します。
    - 方法 (電話番号または電子メール) を選択します。 電子メールは、自己パスワードのリセットに使用できますが、認証には使用できません。 電話番号を追加する場合は、電話の種類を選択し、有効な形式で電話番号を入力します (`+1 4255551234` など)。
    - [**] を選択し、[**] を追加します。

ユーザーはマイ サインインで独自の認証方法を追加または編集できます [|セキュリティ情報](https://mysignins.microsoft.com/security-info)。 たとえば、電話番号を変更 **するには、[電話番号** ] を選択して [ **変更**] をタップします。

#### PowerShell を使用して方法を管理する

次のコマンドを使用して、Microsoft.Graph.Identity.Signins PowerShell モジュールをインストールします。

```powershell
Install-module Microsoft.Graph.Identity.Signins
Connect-MgGraph -Scopes "User.Read.all","UserAuthenticationMethod.Read.All","UserAuthenticationMethod.ReadWrite.All"
Select-MgProfile -Name beta
```

List phone based authentication methods for a specific user.

```powershell
Get-MgUserAuthenticationPhoneMethod -UserId balas@contoso.com
```

Create a mobile phone authentication method for a specific user.

```powershell
New-MgUserAuthenticationPhoneMethod -UserId balas@contoso.com -phoneType "mobile" -phoneNumber "+1 7748933135"
```

Remove a specific phone method for a user

```powershell
Remove-MgUserAuthenticationPhoneMethod -UserId balas@contoso.com -PhoneAuthenticationMethodId 00aa00aa-bb11-cc22-dd33-44ee44ee44ee
```

Authentication methods can also be managed using Microsoft Graph APIs. For more information, see [Authentication and authorization basics](https://learn.microsoft.com/ja-jp/graph/auth/auth-concepts).

### ユーザー認証オプションを管理する

[認証管理者は、他の](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) ユーザーにパスワードのリセット、MFA への再登録、またはユーザーのセッションの取り消しを要求できます。 ユーザーは自分のユーザー オブジェクトを更新できません。 独自のセキュリティ方法を変更またはリセットするには、セキュリティ [情報](https://aka.ms/security-info)に移動するか、 [セルフサービスパスワードリセット](https://aka.ms/sspr) に移動してパスワードをリセットします。 他のユーザー設定を管理するには、次の手順を実行します。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. アクションを実行するユーザーを選択し、[ **認証方法**] を選択します。 ウィンドウの上部で、ユーザーに対して次のいずれかのオプションを選択します。

    - **パスワードをリセット** すると、ユーザーのパスワードがリセットされ、次のサインイン時に変更する必要がある一時的なパスワードが割り当てられます。
    - ** MFA の再登録**はユーザーのハードウェア OATH トークンを非アクティブ化し、このユーザーから次の認証方法 (電話番号、Microsoft Authenticator アプリ、ソフトウェア OATH トークン) を削除します。 必要に応じて、ユーザーは次回サインインする際に新しい MFA 認証方法を設定することが求められます。
    - **セッションを取り消すと** 、ユーザーの更新トークンが無効になり、アクティブなセッションとアプリケーション間で再認証が強制されます。

### ユーザーの既存のアプリ パスワードを削除する

アプリ パスワードを定義したユーザーの場合、管理者はこれらのパスワードを削除して、これらのアプリケーションのレガシ認証が失敗するようにすることも選択できます。 これらのアクションは、ユーザーを支援する必要がある場合や認証方法をリセットする必要がある場合に必要になることがあります。 これらのアプリ パスワードに関連付けられたブラウザー以外のアプリは、新しいアプリ パスワードが作成されるまで動作を停止します。

ユーザーのアプリ パスワードを削除するには、次の手順を実行します。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. **[多要素認証]** を選択します。 このメニュー オプションを表示するには、必要に応じて右にスクロールします。 次のスクリーンショットの例を選択すると、ウィンドウ全体とメニューの位置が表示されます。[Image: Microsoft Entra ID の [ユーザー] ウィンドウから多要素認証を選択したスクリーンショット]
4. 操作したいユーザーの横にあるチェックボックスをオンにします。 クイック ステップのオプションの一覧が右側に表示されます。
5. 次の例に示すように、[ **ユーザー設定の管理**] を選択し、[ **選択したユーザーによって生成されたすべての既存のアプリ パスワードを**削除する] チェック ボックスをオンにします。 [Image: 既存のアプリ パスワードをすべて削除するスクリーンショット。]
6. **[保存]** を選択して**閉じます**。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-mfa-userstates"} -->
## ユーザーごとの多要素認証を有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-userstates
- Service: entra-id / authentication
- Article date: 2025-07-13
- Summary: ユーザーの状態を変更することで、ユーザーごとの Microsoft Entra 多要素認証を有効にする方法について説明します。

Microsoft Entra ID でユーザー サインイン イベントのセキュリティを確保するため、Microsoft Entra 多要素認証 (MFA) を必須にできます。 Microsoft Entra MFA でユーザーを保護する最善の方法は、条件付きアクセス ポリシーを作成することです。 条件付きアクセスは Microsoft Entra ID P1 または P2 の機能であり、特定のシナリオで必要に応じて MFA を要求するルールを適用できます。 条件付きアクセスの使用を開始するには、「 [チュートリアル: Microsoft Entra 多要素認証を使用してユーザー サインイン イベントをセキュリティで保護](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-azure-mfa)する」を参照してください。

条件付きアクセスのない Microsoft Entra ID Free テナントの場合は、 [セキュリティの既定値を使用してユーザーを保護](https://learn.microsoft.com/ja-jp/entra/fundamentals/security-defaults)できます。 ユーザーは必要に応じて MFA の入力を求められますが、独自のルールを定義して動作を制御することはできません。

必要な場合には、その代わりに各アカウントでユーザーごとの Microsoft Entra MFA を有効にすることができます。 ユーザーを個別に有効にすると、ユーザーはサインインするたびに MFA を実行します。 信頼された IP アドレスからサインインするときや、信頼された **デバイスで MFA を記憶** する機能が有効になっている場合など、例外を有効にすることができます。

Microsoft Entra ID ライセンスに条件付きアクセスが含まれていないので、セキュリティの既定値を使用しない場合を除き、 ユーザーの状態 を変更することはお勧めしません。 MFA を有効にするさまざまな方法の詳細については、「 [Microsoft Entra 多要素認証の機能とライセンス](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-licensing)」を参照してください。

重要

この記事では、ユーザーごとの Microsoft Entra 多要素認証の状態を表示および変更する方法について詳しく説明します。 条件付きアクセスまたはセキュリティの既定値を使用する場合、これらの手順を使用してユーザー アカウントを確認したり、有効にしたりしません。

条件付きアクセス ポリシーを使用して Microsoft Entra 多要素認証を有効にしても、ユーザーの状態は変更されません。 ユーザーが無効に見えても問題ありません。 条件付きアクセスでは、状態は変更されません。

**条件付きアクセス ポリシーを使用する場合は、ユーザーごとの Microsoft Entra 多要素認証を有効または適用しないでください。**

### Microsoft Entra 多要素認証におけるユーザーの状態

ユーザーの状態には、認証管理者がユーザーごとの Microsoft Entra 多要素認証にユーザーを登録したかどうかが反映されます。 Microsoft Entra 多要素認証のユーザー アカウントには、次の 3 つの異なる状態があります。

| 状態コード | 説明 | 影響を受けるレガシ認証 | ブラウザー アプリに影響があるか | 影響を受ける先進認証 |
| --- | --- | --- | --- | --- |
| いいえ | ユーザーごとの Microsoft Entra 多要素認証に登録されていないユーザーの既定の状態です。 | いいえ | いいえ | いいえ |
| 有効化 | ユーザーはユーザーごとの Microsoft Entra 多要素認証に登録されていますが、レガシ認証では引き続きパスワードを使用できます。 ユーザーが MFA 認証方法に登録されていない場合は、先進認証 (Web ブラウザーでサインインする場合など) を使用して次回サインインするときに登録するように求められます。 | その必要はありません。 レガシ認証は、登録プロセスが完了するまで機能し続けます。 | はい。 セッションの有効期限が切れると、Microsoft Entra 多要素認証の登録が必要になります。 | はい。 アクセス トークンの有効期限が切れると、Microsoft Entra 多要素認証の登録が必要になります。 |
| 強制 | ユーザーは、ユーザーごとに Microsoft Entra 多要素認証で登録されています。 ユーザーが認証方法に登録されていない場合は、先進認証 (Web ブラウザーでサインインする場合など) を使用して次回サインインするときに登録するように求められます。 *有効*になっている間に登録を完了したユーザーは、自動的に*強制*状態に移動されます。 | はい。 アプリはアプリ パスワードを必要とします。 | はい。 サインイン時に Microsoft Entra 多要素認証が必要です。 | はい。 サインイン時に Microsoft Entra 多要素認証が必要です。 |

すべてのユーザーが *[無効*] から開始します。 管理者がユーザーをユーザーごとの Microsoft Entra 多要素認証に登録すると、ユーザーの状態は *[有効]* に変わります。 ユーザーがサインインして登録プロセスを完了すると、ユーザーの状態が *[強制]* に変わります。 管理者は、*強制から* *有効*または無効になど、ユーザーを状態間で移動*できます*。

Note

ユーザーごとに MFA が再び有効になっていて、ユーザーが再登録しない場合、MFA の状態は MFA 管理 UI で *[有効]* から [ *強制]* に切り替わりません。 管理者は、ユーザーを [ *強制]* に直接移動する必要があります。

### ユーザーの状態を表示する

Microsoft Entra 管理センターにおけるユーザーごとの MFA 管理エクスペリエンスが最近改善されました。 ユーザーの状態を表示および管理するには、次の手順を実行します。

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **[ID]**&gt;**[ユーザー]**&gt;**[すべてのユーザー]** の順に移動します。
3. [ **ユーザーごとの MFA**] をクリックします。
4. ユーザー アカウントを選択し、ユーザー MFA 設定選択します。
5. 変更を加えた後、[ **保存]** を選択します。

    [Image: ユーザーの MFA 設定の例を示すスクリーンショット。]

    何千人ものユーザーを並べ替えようとすると、「表示するユーザーはいません」と結果が正常に返されることがあります**。** 検索を絞り込むには、より具体的な検索条件を入力するか、特定の **状態** フィルターまたは **ビュー** フィルターを適用します。

    [Image: 大量のユーザーを並べ替えてフィルター処理する方法の例を示すスクリーンショット。]

### ユーザーの状態を変更する

特定のユーザーに関し、ユーザーごとの Microsoft Entra 多要素認証の状態を変更するには、次の手順を実行します。

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **[ID]**&gt;**[ユーザー]**&gt;**[すべてのユーザー]** の順に移動します。
3. [ **ユーザーごとの MFA**] をクリックします。
4. ユーザー アカウントを選択し、[ **MFA を有効にする]** を選択します。 [Image: Microsoft Entra 多要素認証のユーザーを有効にする方法を示すスクリーンショット。]

    ヒント

    *[有効]* なユーザーは、Microsoft Entra 多要素認証に登録すると自動的に *[Enforced](https://learn.microsoft.com/ja-jp/entra/identity/authentication/強制)* に切り替えられます。 ユーザーが既に登録されている場合、またはユーザーがレガシ認証プロトコルへの接続の中断を経験できる場合を除き、ユーザーの状態を手動で [ *強制]* に変更しないでください。
5. 開いたポップアップ ウィンドウで選択内容を確認します。

ユーザーを有効にした後は、ユーザーにメールで通知します。 次回のサインイン時に登録を要求するプロンプトが表示されることをユーザーに伝えます。 ブラウザーで実行されず、先進認証もサポートしていないアプリケーションを組織で使用している場合は、アプリケーション パスワードを作成できます。 詳細については、「 [アプリ パスワードを使用してレガシ アプリケーションで Microsoft Entra 多要素認証を適用する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-app-passwords)」を参照してください。

### Microsoft Graph を使用してユーザーごとの MFA を管理する

Microsoft Graph REST API ベータ版を使用して、ユーザーごとの MFA 設定を管理できます。 [認証リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/authentication)を使用して、ユーザーの認証方法の状態を公開できます。

ユーザーごとの MFA を管理するには、users/id/authentication/requirements の perUserMfaState プロパティを使用します。 詳細については、「 [strongAuthenticationRequirements リソースの種類」を参照してください](https://learn.microsoft.com/ja-jp/graph/api/resources/strongauthenticationrequirements)。

#### ユーザーごとの MFA 状態を表示する

特定のユーザーに関するユーザーごとの多要素認証状態を取得するには、以下を実行します。

```http
GET /users/{id | userPrincipalName}/authentication/requirements
```

例えば次が挙げられます。

```http
GET https://graph.microsoft.com/beta/users/071cc716-8147-4397-a5ba-b2105951cc0b/authentication/requirements
```

ユーザーごとの MFA がこのユーザーに関して有効になっている場合、応答は次のようになります。

```http
HTTP/1.1 200 OK
Content-Type: application/json

{
  "perUserMfaState": "enforced"
}
```

詳細については、「 [認証方法の状態を取得する](https://learn.microsoft.com/ja-jp/graph/api/authentication-get)」を参照してください。

#### ユーザーの MFA 状態を変更する

ユーザーの多要素認証状態を変更するには、そのユーザーの strongAuthenticationRequirements を使用します。 例えば次が挙げられます。

```http
PATCH https://graph.microsoft.com/beta/users/071cc716-8147-4397-a5ba-b2105951cc0b/authentication/requirements
Content-Type: application/json

{
  "perUserMfaState": "disabled"
}
```

成功の場合、応答は次のようになります。

```http
HTTP/1.1 204 No Content
```

詳細については、「 [認証方法の状態を更新](https://learn.microsoft.com/ja-jp/graph/api/authentication-update)する」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-password-ban-bad-on-premises-agent-versions"} -->
## パスワード保護エージェントのリリース履歴 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-agent-versions
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: ドキュメントのバージョンのリリースと動作の変更履歴

最新バージョンをダウンロードするには、 [Windows Server Active Directory の Microsoft Entra パスワード保護に関する](https://www.microsoft.com/download/details.aspx?id=57071)ページを参照してください。

### 1.2.177.1

リリース日: 2022 年 3 月 28 日

- ソフトウェア バージョンが正しくない問題を修正しました

### 1.2.177.0

リリース日: 2022 年 3 月 14 日

- 軽微なバグ修正
- Microsoft Entra Connect エージェント アップデーターが更新されない問題を修正しました

### 1.2.176.0

リリース日: 2021 年 6 月 4 日

- 特定の環境でプロキシおよび DC エージェントが正常に実行されなくなる問題に対する軽微なバグ修正。

### 1.2.172.0

リリース日: 2021 年 2 月 22 日

オンプレミスの Microsoft Entra Password Protection エージェントの GA バージョンがリリースされてから、ほぼ 2 年が経過しました。 新しい更新プログラムが利用可能になりました。以下の変更の説明を参照してください。 製品に関するフィードバックをお寄せいただき、ありがとうございます。

- DC エージェントとプロキシ エージェント ソフトウェアの両方で、.NET 4.7.2 をインストールする必要があります。
    - .NET 4.7.2 がまだインストールされていない場合は、 [Windows 用 .NET Framework 4.7.2 オフライン インストーラー](https://support.microsoft.com/topic/microsoft-net-framework-4-7-2-offline-installer-for-windows-05a72734-2127-a15d-50cf-daf56d5faec2)にあるインストーラーをダウンロードして実行します。
- AzureADPasswordProtection PowerShell モジュールも DC エージェント ソフトウェアによってインストールされるようになりました。
- Test-AzureADPasswordProtectionDCAgent と Test-AzureADPasswordProtectionProxy という 2 つの新しい正常性関連の PowerShell コマンドレットが追加されました。
- AzureADPasswordProtection DC Agent パスワード フィルター dll は、lsass.exe が PPL モードで実行するように構成されているマシンで読み込んで実行されるようになりました。
- 5 文字未満の長さの禁止パスワードを誤って受け入れることを許可したパスワード アルゴリズムのバグ修正。
    - このバグは、最初に 5 文字未満のパスワードを許可するようにオンプレミスの AD パスワードの最小長ポリシーが構成されている場合にのみ適用されます。
- その他の軽微なバグ修正。

新しいインストーラーは、古いバージョンのソフトウェアを自動的にアップグレードします。 DC エージェントとプロキシ ソフトウェアの両方を 1 台のコンピューターにインストールしている場合 (テスト環境でのみ推奨)、両方を同時にアップグレードする必要があります。

ドメインまたはフォレスト内で古いバージョンの DC エージェントとプロキシ ソフトウェアを実行することはサポートされていますが、ベスト プラクティスとしてすべてのエージェントを最新バージョンにアップグレードすることをお勧めします。 エージェントのアップグレードの順序はサポートされています。新しい DC エージェントは古いプロキシ エージェントを介して通信でき、古い DC エージェントは新しいプロキシ エージェントを介して通信できます。

### 1.2.125.0

リリース日: 2019 年 3 月 2 日

- イベント ログ メッセージの軽微な入力ミスエラーを修正する
- EULA 契約を最終的な一般提供バージョンに更新する

注

ビルド 1.2.125.0 は一般提供ビルドです。 製品に関するフィードバックをお寄せいただき、ありがとうございます。

### 1.2.116.0

リリース日: 2019 年 3 月 3 日

- Get-AzureADPasswordProtectionProxy コマンドレットと Get-AzureADPasswordProtectionDCAgent コマンドレットでは、ソフトウェアバージョンと現在の Azure テナントが次の制限事項で報告されるようになりました。
    - ソフトウェア バージョンと Azure テナント データは、バージョン 1.2.116.0 以降を実行している DC エージェントとプロキシでのみ使用できます。
    - Azure テナント データは、プロキシまたはフォレストの再登録 (または更新) が発生するまで報告されない場合があります。
- プロキシ サービスでは、.NET 4.7 がインストールされている必要があります。
    - .NET 4.7 がまだインストールされていない場合は、 [Windows 用 .NET Framework 4.7 オフライン インストーラーにあるインストーラー](https://support.microsoft.com/help/3186497/the-net-framework-4-7-offline-installer-for-windows)をダウンロードして実行します。
    - Server Core システムでは、成功させるために /q フラグを .NET 4.7 インストーラーに渡す必要がある場合があります。
- プロキシ サービスで自動アップグレードがサポートされるようになりました。 自動アップグレードでは、Microsoft Entra Connect Agent Updater サービスが使用されます。このサービスはプロキシ サービスと並行してインストールされます。 自動アップグレードは既定でオンになっています。
- 自動アップグレードは、Set-AzureADPasswordProtectionProxyConfiguration コマンドレットを使用して有効または無効にすることができます。 現在の設定は、Get-AzureADPasswordProtectionProxyConfiguration コマンドレットを使用して照会できます。
- DC エージェント サービスのサービス バイナリの名前が AzureADPasswordProtectionDCAgent.exeに変更されました。
- プロキシ サービスのサービス バイナリの名前が AzureADPasswordProtectionProxy.exeに変更されました。 サード パーティ製のファイアウォールが使用中の場合は、それに応じてファイアウォール規則を変更する必要があります。
    - 注: 以前のプロキシ インストールで http プロキシ構成ファイルが使用されていた場合は、このアップグレード後に名前を ( *proxyservice.exe.config* から *AzureADPasswordProtectionProxy.exe.config*に) 変更する必要があります。
- 時間制限付き機能チェックはすべて DC エージェントから削除されています。
- 軽微なバグの修正とログ記録の改善。

### 1.2.65.0

リリース日: 2019 年 2 月 1 日

変遷：

- Server Core で DC エージェントとプロキシ サービスがサポートされるようになりました。 OS の最小要件は以前と変わりません。DC エージェントの場合は Windows Server 2012、プロキシの場合は Windows Server 2012 R2 です。
- Register-AzureADPasswordProtectionProxy コマンドレットと Register-AzureADPasswordProtectionForest コマンドレットで、デバイス コードベースの Azure 認証モードがサポートされるようになりました。
- Get-AzureADPasswordProtectionDCAgent コマンドレットは、壊れたサービス接続ポイントや無効なサービス接続ポイントを無視します。 この変更により、ドメイン コントローラーが出力に複数回表示されることがあるバグが修正されます。
- Get-AzureADPasswordProtectionSummaryReport コマンドレットは、壊れたサービス接続ポイントや無効なサービス接続ポイントを無視します。 この変更により、ドメイン コントローラーが出力に複数回表示されることがあるバグが修正されます。
- これで、プロキシ PowerShell モジュールが %ProgramFiles%\WindowsPowerShell\Modules から登録されました。 コンピューターの PSModulePath 環境変数は変更されなくなりました。
- フォレストまたはドメイン内の登録済みプロキシの検出に役立つ新しい Get-AzureADPasswordProtectionProxy コマンドレットが追加されました。
- DC エージェントは、パスワード ポリシーやその他のファイルをレプリケートするために sysvol 共有内の新しいフォルダーを使用します。

    古いフォルダーの場所:

    `\\<domain>\sysvol\<domain fqdn>\Policies\{4A9AB66B-4365-4C2A-996C-58ED9927332D}`

    新しいフォルダーの場所:

    `\\<domain>\sysvol\<domain fqdn>\AzureADPasswordProtection`

    (この変更は、誤検知の "切り離された GPO" の警告を回避するために行われました。)

    注

    古いフォルダーと新しいフォルダーの間でデータの移行や共有は行われません。 以前の DC エージェントのバージョンでは、このバージョン以降にアップグレードされるまで、古い場所が引き続き使用されます。 すべての DC エージェントがバージョン 1.2.65.0 以降を実行すると、古い sysvol フォルダーが手動で削除される可能性があります。
- DC エージェントとプロキシ サービスは、それぞれのサービス接続ポイントの壊れたコピーを検出して削除するようになりました。
- 各 DC エージェントは、ドメイン内の壊れたサービス接続ポイントや古いサービス接続ポイントを、DC エージェントおよびプロキシ サービス接続ポイントの両方について定期的に削除します。 DC エージェントとプロキシ サービスの両方の接続ポイントは、ハートビート タイムスタンプが 7 日より古い場合、古いと見なされます。
- DC エージェントは、必要に応じてフォレスト証明書を更新します。
- プロキシ サービスは、必要に応じてプロキシ証明書を更新します。
- パスワード検証アルゴリズムの更新: グローバル禁止パスワード リストと顧客固有の禁止パスワード リスト (構成されている場合) は、パスワード検証の前に結合されます。 グローバルリストと顧客固有リストの両方のトークンが含まれている場合、特定のパスワードが拒否される (失敗または監査のみ) 可能性があります。 イベント ログのドキュメントは、これを反映するように更新されました。 [Microsoft Entra パスワード保護の監視](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-monitor)を参照してください。
- パフォーマンスと堅牢性の修正
- ログ記録の強化

Warnung

制限付き機能: このリリース (1.2.65.0) の DC エージェント サービスは、2019 年 9 月 1 日の時点でパスワード検証要求の処理を停止します。 以前のリリースの DC エージェント サービス (下記の一覧を参照) は、2019 年 7 月 1 日の時点で処理を停止します。 すべてのバージョンの DC エージェント サービスは、これらの期限までの 2 か月間に 10021 イベントを管理者イベント ログに記録します。 時間制限の制限はすべて、今後の GA リリースで削除される予定です。 プロキシ エージェント サービスはどのバージョンでも時間制限はありませんが、以降のすべてのバグ修正やその他の機能強化を利用するために、最新バージョンにアップグレードする必要があります。

### 1.2.25.0

リリース日: 2018 年 11 月 1 日

修正

- 証明書の信頼エラーにより、DC エージェントとプロキシ サービスが失敗しなくなりました。
- DC エージェントとプロキシ サービスには、FIPS 準拠のマシンに対する修正プログラムがあります。
- プロキシ サービスは、TLS 1.2 のみのネットワーク環境で正常に動作するようになりました。
- パフォーマンスと堅牢性に関する軽微な修正
- ログ記録の強化

変遷：

- プロキシ サービスに必要な最小 OS レベルは、Windows Server 2012 R2 になりました。 DC エージェント サービスに必要な最小 OS レベルは、Windows Server 2012 のままです。
- プロキシ サービスに .NET バージョン 4.6.2 が必要になりました。
- パスワード検証アルゴリズムでは、拡張文字正規化テーブルが使用されます。 この変更により、以前のバージョンで受け入れられたパスワードが拒否される可能性があります。

### 1.2.10.0

リリース日: 2018 年 8 月 17 日

修正

- Register-AzureADPasswordProtectionProxy と Register-AzureADPasswordProtectionForest で多要素認証がサポートされるようになりました
- Register-AzureADPasswordProtectionProxy では、暗号化エラーを回避するために、ドメインに WS2012 以降のドメイン コントローラーが必要です。
- DC エージェント サービスは、起動時に Azure に新しいパスワード ポリシーを要求する方が信頼性が高くなります。
- DC エージェント サービスは、必要に応じて 1 時間ごとに Azure に新しいパスワード ポリシーを要求しますが、ランダムに選択された開始時刻に要求されます。
- DC エージェント サービスを使用すると、レプリカとしてプロモーションされる前にサーバー上にインストールされるときに、新しい DC アドバタイズで無限に遅延が発生することがなくなります。
- DC エージェント サービスでは、"Windows Server Active Directory でのパスワード保護を有効にする" 構成設定が優先されるようになりました
- DC エージェントとプロキシ インストーラーの両方で、将来のバージョンにアップグレードするときにインプレース アップグレードがサポートされるようになりました。

Warnung

バージョン 1.1.10.3 からのインプレース アップグレードはサポートされていないため、インストール エラーが発生します。 バージョン 1.2.10 以降にアップグレードするには、まず DC エージェントとプロキシ サービス ソフトウェアを完全にアンインストールしてから、新しいバージョンを最初からインストールする必要があります。 Microsoft Entra パスワード保護プロキシ サービスの再登録が必要です。 フォレストを再登録する必要はありません。

注

DC エージェント ソフトウェアのインプレース アップグレードには再起動が必要です。

- DC エージェントとプロキシ サービスでは、FIPS 準拠アルゴリズムのみを使用するように構成されたサーバーでの実行がサポートされるようになりました。
- パフォーマンスと堅牢性に関する軽微な修正
- ログ記録の強化

### 1.1.10.3

リリース日: 2018 年 6 月 15 日

初期パブリック プレビュー リリース
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-password-ban-bad-on-premises-deploy"} -->
## オンプレミスの Microsoft Entra パスワード保護をデプロイする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-deploy
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: オンプレミスの Active Directory Domain Services 環境で Microsoft Entra パスワード保護を計画してデプロイする方法について説明します

ユーザーは多くの場合、学校、スポーツ チーム、有名人などのありふれたローカル単語を使用してパスワードを作成します。 これらのパスワードは簡単に推測できるため、辞書ベースの攻撃に対しては脆弱です。 組織に強力なパスワードを適用するため、Microsoft Entra のパスワード保護では、グローバルかつカスタムの禁止パスワードの一覧が提供されます。 この禁止パスワードの一覧に一致するものがある場合、パスワードの変更要求はエラーとなります。

オンプレミスの Active Directory Domain Services (AD DS) 環境を保護するために、Microsoft Entra パスワード保護をインストールして、オンプレミスの DC と連携するように構成することができます。 この記事では、オンプレミスの環境に Microsoft Entra パスワード保護プロキシ サービスと Microsoft Entra パスワード保護 DC エージェントをインストールして登録する方法について説明します。

オンプレミス環境での Microsoft Entra パスワード保護の動作の詳細については、「 [Windows Server Active Directory に Microsoft Entra パスワード保護を適用する方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-on-premises)」を参照してください。

### 展開戦略

次の図は、Microsoft Entra パスワード保護の基本コンポーネントが、オンプレミスの Active Directory 環境でどのように連携するかを示しています。

[Image: Microsoft Entra パスワード保護コンポーネントの連携方法]

ソフトウェアをデプロイする前にしくみを確認することをお勧めします。 詳細については、「 [Microsoft Entra パスワード保護の概念の概要](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-on-premises)」を参照してください。

*監査*モードでデプロイを開始することをお勧めします。 監査モードは既定の初期設定であり、パスワードは後から設定できます。 ブロックされるパスワードはイベント ログに記録されます。 プロキシ サーバーと DC エージェントを監査モードでデプロイした後、パスワード ポリシーが適用されるときに、そのポリシーがユーザーに与える影響を監視してください。

多くの組織では、この監査段階で次のような状況を認識します。

- より安全なパスワードを使用するように既存の運用プロセスを改善する必要がある。
- 多くの場合、ユーザーは安全でないパスワードを使用している。
- セキュリティの適用の今後の変更、生じる可能性がある影響、およびより安全なパスワードを選択する方法をユーザーに通知する必要がある。

より強力なパスワード検証が、既存の Active Directory ドメイン コントローラーのデプロイの自動化に影響を与える可能性もあります。 このような問題を発見できるように、監査期間の評価中に、少なくとも 1 つの DC 昇格と 1 つの DC 降格が行われるようにすることをお勧めします。 詳細については、次の記事を参照してください。

- [Ntdsutil.exe は弱いディレクトリ サービス修復モードのパスワードを設定できません](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-troubleshoot#ntdsutilexe-fails-to-set-a-weak-dsrm-password)
- [ディレクトリ サービス修復モードのパスワードが弱いため、ドメイン コントローラー レプリカの昇格が失敗する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-troubleshoot#domain-controller-replica-promotion-fails-because-of-a-weak-dsrm-password)
- [ローカル管理者パスワードが弱いため、ドメイン コントローラーの降格が失敗する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-troubleshoot#domain-controller-demotion-fails-due-to-a-weak-local-administrator-password)

この機能が適切な期間監査モードで実行されたら、構成を *監査* から *強制* に切り替えて、より安全なパスワードを要求できます。 この期間中に、追加の監視を行うことをお勧めします。

Microsoft Entra のパスワード保護では、パスワードの変更または設定操作中にのみパスワードの検証を行うことができることに注意してください。 Microsoft Entra のパスワード保護をデプロイする前に Active Directory に受け入れられ保存されたパスワードは、検証されず、そのまま動作し続けます。 時間の経過に伴い、すべてのユーザーとアカウントでは、通常どおり既存のパスワードの有効期限が切れると Microsoft Entra のパスワード保護で検証されたパスワードの使用が開始されます。 ただし、[パスワードを無期限にする] を使用して構成されたアカウントを除きます。

#### 複数のフォレストに関する考慮事項

Microsoft Entra パスワード保護を複数のフォレストに展開するための追加要件はありません。

オンプレミス の Microsoft Entra パスワード保護を展開するために、次のセクションで説明するように、各フォレストは個別に構成されます。 各 Microsoft Entra パスワード保護プロキシでサポートできるのは、参加先のフォレストのドメイン コントローラーのみです。

どのフォレスト内の Microsoft Entra パスワード保護ソフトウェアも、Active Directory 信頼の構成に関係なく、別のフォレストにデプロイされているパスワード保護ソフトウェアを認識しません。

#### 読み取り専用ドメイン コントローラーに関する考慮事項

パスワードの変更または設定のイベントは、読み取り専用ドメイン コントローラー (RODC) では処理および永続化されません。 代わりに、これらは、書き込み可能なドメイン コントローラーに転送されます。 RODC に Microsoft Entra パスワード保護 DC エージェント ソフトウェアをインストールする必要はありません。

さらに、読み取り専用ドメイン コントローラーでの Microsoft Entra パスワード保護プロキシサービスの実行はサポートされていません。

#### 高可用性に関する考慮事項

パスワード保護に関する主要な関心事は、フォレストの DC が Azure から新しいポリシーやその他のデータをダウンロードしようとしたときの Microsoft Entra パスワード保護プロキシ サーバーの可用性です。 各 Microsoft Entra パスワード保護 DC エージェントでは、どのプロキシ サーバーを呼び出すかを決定するときに、単純なラウンドロビン方式のアルゴリズムを使用します。 エージェントは、応答しないプロキシ サーバーをスキップします。

完全に接続された Active Directory デプロイの場合、ディレクトリと sysvol フォルダー状態の両方の正常なレプリケーションがあるときは、可用性を確保するには 2 つの Microsoft Entra パスワード保護プロキシ サーバーがあれば十分です。 この構成により、新しいポリシーやその他のデータがタイミングよくダウンロードされます。 必要に応じて、追加の Microsoft Entra パスワード保護プロキシ サーバーをデプロイできます。

高可用性に関連する一般的な問題は、Microsoft Entra パスワード保護 DC エージェント ソフトウェアの設計によって軽減されています。 Microsoft Entra パスワード保護 DC エージェントは、最後にダウンロードされたパスワード ポリシーのローカル キャッシュを保持します。 登録されているすべてのプロキシ サーバーが使用できなくなった場合でも、Microsoft Entra パスワード保護 DC エージェントは、キャッシュされたパスワード ポリシーを引き続き適用します。

大規模なデプロイでのパスワード ポリシーの妥当な更新頻度は、通常は日単位であり、時間単位やそれ以下の単位ではありません。 そのため、プロキシ サーバーが短時間停止しても、Microsoft Entra パスワード保護に大きな影響はありません。

### デプロイ要件

ライセンスの詳細については、「 [Microsoft Entra Password Protection のライセンス要件」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad#license-requirements)参照してください。

次の主要な要件が適用されます。

- ドメイン コントローラーを含め、Microsoft Entra パスワード保護コンポーネントがインストールされているすべてのマシンに、ユニバーサル C ランタイムがインストールされている必要があります。

    - Windows Update からすべての更新プログラムを確実に取得することでランタイムを入手できます。 または、OS 固有の更新プログラム パッケージで入手できます。 詳細については、「 [Windows のユニバーサル C ランタイムの更新プログラム](https://support.microsoft.com/help/2999226/update-for-uniersal-c-runtime-in-windows)」を参照してください。
- Microsoft Entra ID に Windows Server Active Directory フォレストを登録するために、フォレスト ルート ドメインの Active Directory ドメイン管理者特権を持つアカウントが必要です。
- Windows Server 2012 以降を実行している、ドメイン内のすべてのドメイン コントローラーで、キー配布サービスを有効にする必要があります。 既定では、このサービスは手動トリガーで開始して有効化されます。
- 各ドメイン内の少なくとも 1 つのドメイン コントローラーと、Microsoft Entra パスワード保護用のプロキシ サービスをホストする少なくとも 1 つのサーバーとの間に、ネットワーク接続が存在する必要があります。 この接続では、ドメイン コントローラーがプロキシ サービス上の RPC エンドポイント マッパー ポート 135 および RPC サーバー ポートにアクセスできるようにする必要があります。

    - 既定では、RPC サーバー ポートは範囲 (49152 - 65535) からの動的 RPC ポートですが、 静的ポートを使用するように構成できます。
- Microsoft Entra のパスワード保護プロキシサービスがインストールされるすべてのマシンに、次のエンドポイントへのネットワーク アクセスが必要です。

    | **エンドポイント** | **目的** |
    | --- | --- |
    | `https://login.microsoftonline.com` | 認証要求 |
    | `https://enterpriseregistration.windows.net` | Microsoft Entra パスワード保護機能 |
    | `https://autoupdate.msappproxy.net` | Microsoft Entra パスワード保護の自動アップグレード機能 |

注

CRL エンドポイントなどの一部のエンドポイントについては、この記事では扱いません。 サポートされているすべてのエンドポイントの一覧については、 [Microsoft 365 の URL と IP アドレス範囲に関するページを](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/urls-and-ip-address-ranges#microsoft-365-common-and-office-online)参照してください。 さらに、Microsoft Entra 管理センターの認証には他のエンドポイントも必要です。 詳細については、 [プロキシ バイパスに関する Microsoft Entra 管理センターの URL を](https://learn.microsoft.com/ja-jp/azure/azure-portal/azure-portal-safelist-urls?tabs=public-cloud#azure-portal-urls-for-proxy-bypass)参照してください。

#### Microsoft Entra パスワード保護の DC エージェント

Microsoft Entra パスワード保護の DC エージェントには、次の要件が適用されます。

- Microsoft Entra パスワード保護の DC エージェント ソフトウェアがインストールされるマシンで、Windows Server Core エディションなど、Windows Server 2012 R2 以降が実行されている必要があります。
    - Active Directory ドメインまたはフォレストは、サポートされている任意の機能レベルにすることができます。
- Microsoft Entra パスワード保護の DC エージェントがインストールされるすべてのマシンには、.NET 4.7.2 をインストールしておく必要があります。
    - .NET 4.7.2 がまだインストールされていない場合は、 [Windows 用 .NET Framework 4.7.2 オフライン インストーラー](https://support.microsoft.com/topic/microsoft-net-framework-4-7-2-offline-installer-for-windows-05a72734-2127-a15d-50cf-daf56d5faec2)にあるインストーラーをダウンロードして実行します。
- Microsoft Entra パスワード保護の DC エージェント サービスを実行しているすべての Active Directory ドメインは、sysvol レプリケーションに分散ファイル システム レプリケーション (DFSR) を使用する必要があります。
    - ご利用のドメインでまだ DFSR を使用していない場合、Microsoft Entra パスワード保護をインストールする前に移行する必要があります。 詳細については、「[SYSVOL レプリケーション移行ガイド: FRS から DFS へのレプリケーション](https://learn.microsoft.com/ja-jp/windows-server/storage/dfs-replication/migrate-sysvol-to-dfsr)」を参照してください。

        警告

        Microsoft Entra パスワード保護の DC エージェント ソフトウェアは現在のところ、sysvol レプリケーションのために依然として FRS (DFSR の前身テクノロジ) を使用しているドメインにあるドメイン コントローラーにインストールされますが、この環境ではソフトウェアは正しく機能しません。

        その他のマイナスの副作用としては、個々のファイルを複製できない、sysvol 復元処理が成功したように見えたが、一部のファイルの複製に失敗しており、何のエラーも表示されない、などがあります。

        できるだけ早くドメインを移行して、DFSR に固有のメリットと Microsoft Entra パスワード保護のデプロイのブロック解除のために、DFSR を使用するようにしてください。 このソフトウェアの今後のバージョンは、依然として FRS を使用しているドメインで実行すると、自動的に無効になります。

#### Microsoft Entra パスワード保護のプロキシ サービス

Microsoft Entra パスワード保護のプロキシ サービスには、次の要件が適用されます。

- Microsoft Entra パスワード保護のプロキシ サービスがインストールされるすべてのマシンで、Windows Server Core エディションを含め、Windows Server 2012 R2 以降が実行されている必要があります。

    注

    インターネットへの直接の送信接続がドメイン コントローラーにある場合でも、Microsoft Entra パスワード保護のデプロイには、Microsoft Entra パスワード保護のプロキシ サービスのデプロイが必須要件です。
- Microsoft Entra パスワード保護のプロキシ サービスがインストールされるすべてのマシンには、.NET 4.7.2 をインストールしておく必要があります。

    - .NET 4.7.2 がまだインストールされていない場合は、 [Windows 用 .NET Framework 4.7.2 オフライン インストーラー](https://support.microsoft.com/topic/microsoft-net-framework-4-7-2-offline-installer-for-windows-05a72734-2127-a15d-50cf-daf56d5faec2)にあるインストーラーをダウンロードして実行します。
- Microsoft Entra パスワード保護のプロキシ サービスがホストされているすべてのマシンを、このプロキシ サービスにログオンする機能をドメイン コントローラーに許可するように、構成する必要があります。 この機能は、"ネットワーク経由でコンピューターへアクセス" 特権の割り当てによって制御されます。
- Microsoft Entra パスワード保護のプロキシ サービスがホストされているすべてのマシンを、送信 TLS 1.2 HTTP トラフィックを許可するように構成する必要があります。
- [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)は、特定のテナントで初めて Microsoft Entra Password Protection プロキシ サービスを登録する必要があります。 Microsoft Entra ID を使用した後続のプロキシとフォレストの登録では、少なくとも [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) ロールを持つアカウントを使用できます。
- [アプリケーション プロキシ環境のセットアップ手順](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-connectors)で指定されたポートと URL のセットに対して、ネットワーク アクセスを有効にする必要があります。 これは、上記の 2 つのエンドポイントに加えて必要となります。

#### Microsoft Entra Connect Agent Updater の前提条件

Microsoft Entra Connect Agent Updater サービスは、Microsoft Entra パスワード保護のプロキシ サービスとサイド バイ サイドでインストールされます。 Microsoft Entra Connect エージェント アップデーター サービスを機能させるには、追加の構成が必要です。

- 環境で HTTP プロキシ サーバーを使用している場合は、「既存のオンプレミス プロキシ [サーバーを使用する」](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-connectors-with-proxy-servers)で指定されているガイドラインに従ってください。
- Microsoft Entra Connect エージェント アップデーター サービスには、 [TLS 要件](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-connectors)で指定された TLS 1.2 の手順も必要です。

警告

Microsoft Entra パスワード保護プロキシと Microsoft Entra アプリケーション プロキシでは、異なるバージョンの Microsoft Entra Connect エージェント アップデーター サービスがインストールされます。そのため、説明ではアプリケーション プロキシの内容を示しています。 これらの異なるバージョンは、サイドバイサイドでインストールされた場合に互換性がないため、これを実行するとエージェント アップデーター サービスがソフトウェアの更新のために Azure に接続できなくなります。そのため、Microsoft Entra パスワード保護プロキシとアプリケーション プロキシを同じコンピューター上にインストールしないでください。

### 必要なソフトウェアのダウンロード

オンプレミスの Microsoft Entra パスワード保護のデプロイには 2 つのインストーラーが必要です。

- Microsoft Entra パスワード保護の DC エージェント (*AzureADPasswordProtectionDCAgentSetup.msi*)
- Microsoft Entra パスワード保護プロキシ (*AzureADPasswordProtectionProxySetup.exe*)

両方のインストーラーを [Microsoft ダウンロード センターからダウンロードします](https://www.microsoft.com/download/details.aspx?id=57071)。

### プロキシ サービスのインストールと構成

Microsoft Entra パスワード保護プロキシ サービスは、通常、オンプレミスの AD DS 環境のメンバー サーバー上にあります。 インストールされると、Microsoft Entra パスワード保護のプロキシ サービスは Microsoft Entra ID と通信して、Microsoft Entra テナント用のグローバルおよび顧客の禁止パスワード一覧のコピーを保持します。

次のセクションでは、オンプレミスの AD DS 環境のドメイン コントローラーに Microsoft Entra パスワード保護の DC エージェントをインストールします。 これらの DC エージェントはプロキシ サービスと通信して、ドメイン内でパスワード変更イベントを処理するときに使用する最新の禁止パスワードの一覧を取得します。

Microsoft Entra パスワード保護のプロキシ サービスをホストする 1 つ以上のサーバーを選択します。 このサーバーには、次の考慮事項が適用されます。

- これら各サービスは、単一フォレストのパスワード ポリシーのみを提供できます。 ホスト マシンは、そのフォレスト内の任意のドメインに参加している必要があります。
- ルートまたは子ドメイン、またはそれらの組み合わせのいずれかにプロキシ サービスをインストールできます。
- フォレストの各ドメインの少なくとも 1 つの DC とパスワード保護プロキシ サーバーの間にネットワーク接続が必要です。
- テストのためにドメイン コントローラーで Microsoft Entra パスワード保護のプロキシ サービスを実行することはできますが、その場合、ドメイン コントローラーにはインターネット接続が必要です。 この接続は、セキュリティ上の問題になる可能性があります。 この構成はテスト専用としてお勧めします。
- 高可用性に関する考慮事項に関する前のセクションで説明したように、冗長性を確保するために、フォレストごとに少なくとも 2 つの Microsoft Entra パスワード保護プロキシ サーバーをお勧めします。
- 読み取り専用ドメイン コントローラーでの Microsoft Entra パスワード保護プロキシサービスの実行はサポートされていません。
- 必要に応じて、[ **プログラムの追加と削除**] を使用してプロキシ サービスを削除できます。 プロキシ サービスが維持する状態を手動でクリーンアップする必要はありません。

Microsoft Entra パスワード保護のプロキシ サービスをインストールするには、次の手順を実行します。

1. Microsoft Entra パスワード保護のプロキシ サービスをインストールするために、`AzureADPasswordProtectionProxySetup.exe` ソフトウェア インストーラーを実行します。

    このソフトウェアのインストールでは再起動を必要とせず、次の例のように、標準 MSI プロシージャを使用して自動化できます。

    ```console
    AzureADPasswordProtectionProxySetup.exe /quiet
    ```

    注

    インストール エラーを回避するため、`AzureADPasswordProtectionProxySetup.exe` パッケージをインストールする前に Windows ファイアウォール サービスを実行しておく必要があります。

    Windows Firewall が実行されないように構成されている場合は、回避策として、インストールの際に一時的に Windows Firewall サービスを有効化して実行します。 インストール後はプロキシ ソフトウェアが Windows Firewall に特に依存することはありません。

    サードパーティ製のファイアウォールを使用している場合も、デプロイ要件を満たすように構成する必要があります。 これらには、ポート 135 およびプロキシ RPC サーバー ポートへの着信アクセスの許可が含まれます。 詳細については、 デプロイ要件に関する前のセクションを参照してください。
2. Microsoft Entra パスワード保護のプロキシ ソフトウェアには、新しい PowerShell モジュール `AzureADPasswordProtection` が含まれています。 この後の手順では、この PowerShell モジュールからさまざまなコマンドレットを実行します。

    このモジュールを使用するには、管理者として PowerShell ウィンドウを開き、次のように新しいモジュールをインポートします。

    ```powershell
    Import-Module AzureADPasswordProtection
    ```

    警告

    64 ビット バージョンの PowerShell を使用する必要があります。 PowerShell (x86) では、特定のコマンドレットが動作しない可能性があります。
3. Microsoft Entra パスワード保護のプロキシ サービスが実行されていることを確認するには、次の PowerShell コマンドを使用します。

    ```powershell
    Get-Service AzureADPasswordProtectionProxy | fl
    ```

    結果には **ステータス**が *実行中* と表示されます。
4. プロキシ サービスはマシンで実行されていますが、Microsoft Entra ID と通信するための資格情報を保持していません。 `Register-AzureADPasswordProtectionProxy` コマンドレットを使用して、Microsoft Entra パスワード保護プロキシ サーバーを Microsoft Entra ID に登録します。

    このコマンドレットでは、特定のテナントに対してプロキシが初めて登録されるときに *、グローバル管理者* の資格情報が必要です。 同じプロキシでも異なるプロキシでも、そのテナント内の後続のプロキシ登録では、 *セキュリティ管理者* の資格情報を使用できます。

    このコマンドが 1 回成功すると、次回以降の呼び出しも成功しますが、これ以上は必要はありません。

    `Register-AzureADPasswordProtectionProxy` コマンドレットでは、以下の 3 つの認証モードがサポートされます。 Microsoft Entra 多要素認証は、最初の 2 つのモードではサポートされますが、3 つ目のモードではサポートされません。

    ヒント

    このコマンドレットを特定の Azure テナントに対して最初に実行するときは、完了するまでにかなり時間がかかることがあります。 エラーが報告されない限り、この遅延については心配しないでください。

    - 対話型認証モード:

        ```powershell
        Register-AzureADPasswordProtectionProxy -AccountUpn 'yourglobaladmin@yourtenant.onmicrosoft.com'
        ```

        注

        このモードは、Server Core オペレーティング システムでは機能しません。 代わりに以下の認証モードのいずれかを使用できます。 また、Internet Explorer の強化されたセキュリティ構成が有効になっている場合、このモードは失敗する可能性があります。 回避策としては、その構成を無効にし、プロキシを登録してから、もう一度構成を有効にします。
    - デバイスコード認証モード:

        ```powershell
        Register-AzureADPasswordProtectionProxy -AccountUpn 'yourglobaladmin@yourtenant.onmicrosoft.com' -AuthenticateUsingDeviceCode
        ```

        プロンプトが表示されたら、リンクに従って Web ブラウザーを開き、認証コードを入力します。
    - サイレント (パスワードベース) 認証モード:

        ```powershell
        $globalAdminCredentials = Get-Credential
        Register-AzureADPasswordProtectionProxy -AzureCredential $globalAdminCredentials
        ```

        注

        このモードは、お使いのアカウントで Microsoft Entra 多要素認証が必要な場合は失敗します。 その場合は、前の 2 つの認証モードのいずれかを使用するか、MFA を必要としない別のアカウントを使用してください。

        グローバルに MFA を要求するように Azureデバイス登録 (Microsoft Entra パスワード保護によってバックグラウンドで使用されます) が構成されている場合も、MFA が必要であることが表示されます。 この要件に対処するには、前の 2 つの認証モードのいずれかで、MFA をサポートしている別のアカウントを使用します。または、Azure Device Registration の MFA 要件を一時的に緩めることもできます。

        この変更を行うには、**Microsoft Entra 管理センター**で [\[Entra ID](https://entra.microsoft.com)] を選択し、[**デバイス**&gt;**デバイスの設定]** を選択します。 **[デバイスを参加させるために多要素認証を要求する**] を *[いいえ*] に設定します。 登録が完了したら、この設定を必ず *[はい* ] に再構成してください。

        MFA の要件のバイパスは、テスト目的でのみ使用することをお勧めします。

    現在、将来の機能のために予約 *されている -ForestCredential* パラメーターを指定する必要はありません。

    Microsoft Entra パスワード保護のプロキシ サービスの登録は、サービスの有効期間内に 1 回だけ行う必要があります。 その後、他の必要なメンテナンスは、Microsoft Entra パスワード保護のプロキシ サービスによって自動的に実行されます。
5. 変更が有効になっていることを確認するには、`Test-AzureADPasswordProtectionProxyHealth -TestAll` を実行します。 エラーの解決については、「 [トラブルシューティング: オンプレミスの Microsoft Entra パスワード保護](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-troubleshoot)」を参照してください。
6. 次は、`Register-AzureADPasswordProtectionForest` PowerShell コマンドレットを使用して、Azure と通信するために必要な資格情報でオンプレミスの Active Directory フォレストを登録します。

    注

    環境に複数の Microsoft Entra パスワード保護のプロキシ サーバーがインストールされている場合、フォレストの登録にはどのプロキシ サーバーを使用しても問題はありません。

    このコマンドレットには、Azure テナントの *グローバル管理者* または *セキュリティ管理者* の資格情報が必要です。 また、オンプレミスの Active Directory のエンタープライズ管理者特権も必要です。 ローカル管理者特権を持つアカウントを使って、このコマンドレットを実行する必要があります。 フォレストの登録に使用される Azure アカウントは、オンプレミスの Active Directory アカウントとは異なる場合があります。

    この手順は、フォレストごとに 1 回実行されます。

    `Register-AzureADPasswordProtectionForest` コマンドレットでは、以下の 3 つの認証モードがサポートされます。 Microsoft Entra 多要素認証は、最初の 2 つのモードではサポートされますが、3 つ目のモードではサポートされません。

    ヒント

    このコマンドレットを特定の Azure テナントに対して最初に実行するときは、完了するまでにかなり時間がかかることがあります。 エラーが報告されない限り、この遅延については心配しないでください。

    - 対話型認証モード:

        ```powershell
        Register-AzureADPasswordProtectionForest -AccountUpn 'yourglobaladmin@yourtenant.onmicrosoft.com'
        ```

        注

        このモードは、Server Core オペレーティング システムでは機能しません。 代わりに以下の 2 つの認証モードのいずれかを使用します。 また、Internet Explorer の強化されたセキュリティ構成が有効になっている場合、このモードは失敗する可能性があります。 回避策としては、その構成を無効にし、フォレストを登録してから、もう一度構成を有効にします。
    - デバイスコード認証モード:

        ```powershell
        Register-AzureADPasswordProtectionForest -AccountUpn 'yourglobaladmin@yourtenant.onmicrosoft.com' -AuthenticateUsingDeviceCode
        ```

        プロンプトが表示されたら、リンクに従って Web ブラウザーを開き、認証コードを入力します。
    - サイレント (パスワードベース) 認証モード:

        ```powershell
        $globalAdminCredentials = Get-Credential
        Register-AzureADPasswordProtectionForest -AzureCredential $globalAdminCredentials
        ```

        注

        このモードは、お使いのアカウントで Microsoft Entra 多要素認証が必要な場合は失敗します。 その場合は、前の 2 つの認証モードのいずれかを使用するか、MFA を必要としない別のアカウントを使用してください。

        グローバルに MFA を要求するように Azureデバイス登録 (Microsoft Entra パスワード保護によってバックグラウンドで使用されます) が構成されている場合も、MFA が必要であることが表示されます。 この要件に対処するには、前の 2 つの認証モードのいずれかで、MFA をサポートしている別のアカウントを使用します。または、Azure Device Registration の MFA 要件を一時的に緩めることもできます。

        この変更を行うには、**Microsoft Entra 管理センター**で [\[Entra ID](https://entra.microsoft.com)] を選択し、[**デバイス**&gt;**デバイスの設定]** を選択します。 **[デバイスを参加させるために多要素認証を要求する**] を *[いいえ*] に設定します。 登録が完了したら、この設定を必ず *[はい* ] に再構成してください。

        MFA の要件のバイパスは、テスト目的でのみ使用することをお勧めします。

        これらの例は、現在サインインしているユーザーがルート ドメインの Active Directory ドメイン管理者でもある場合にのみ正常に機能します。 そうでない場合は、 *-ForestCredential* パラメーターを使用して代替ドメイン資格情報を指定できます。

    Active Directory フォレストの登録は、フォレストの有効期間中に 1 回だけ必要です。 その後、他の必要なメンテナンスは、フォレスト内の Microsoft Entra パスワード保護の DC エージェントによって自動的に実行されます。 フォレストに対して `Register-AzureADPasswordProtectionForest` が正常に実行された後、それ以降のコマンドレットの呼び出しも成功しますが、必要ありません。

    `Register-AzureADPasswordProtectionForest` を成功させるには、Windows Server 2012 以降を実行している少なくとも 1 台の DC が Microsoft Entra パスワード保護のプロキシ サーバーのドメイン内で使用できる必要があります。 この手順の前に、Microsoft Entra パスワード保護の DC エージェント ソフトウェアをドメイン コントローラーにインストールしておく必要はありません。
7. 変更が有効になっていることを確認するには、`Test-AzureADPasswordProtectionProxyHealth -TestAll` を実行します。 エラーの解決については、「 [トラブルシューティング: オンプレミスの Microsoft Entra パスワード保護](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-troubleshoot)」を参照してください。

#### HTTP プロキシを通じて通信するようにプロキシ サービスを構成する

ご使用の環境で Azure との通信に特定の HTTP プロキシを使用する必要がある場合は、次の手順を使用して、Microsoft Entra パスワード保護サービスを構成します。

 フォルダーに `%ProgramFiles%\Azure AD Password Protection Proxy\Service` ファイルを作成します。 次の内容を含めます。

```xml
<configuration>
   <system.net>
      <defaultProxy enabled="true">
      <proxy bypassonlocal="true"
         proxyaddress="http://yourhttpproxy.com:8080" />
      </defaultProxy>
   </system.net>
</configuration>
```

HTTP プロキシで認証が必要な場合は、 *useDefaultCredentials タグを* 追加します。

```xml
<configuration>
   <system.net>
      <defaultProxy enabled="true" useDefaultCredentials="true">
      <proxy bypassonlocal="true"
         proxyaddress="http://yourhttpproxy.com:8080" />
      </defaultProxy>
   </system.net>
</configuration>
```

いずれの場合も、`http://yourhttpproxy.com:8080` を特定の HTTP プロキシ サーバーのアドレスとポートに置き換えます。

HTTP プロキシが承認ポリシーを使用するように構成されている場合は、パスワード保護用プロキシ サービスをホストしているコンピューターの Active Directory コンピューター アカウントにアクセス許可を付与する必要があります。

*AzureADPasswordProtectionProxy.exe.config* ファイルを作成または更新した後、Microsoft Entra Password Protection プロキシ サービスを停止して再起動することをお勧めします。

プロキシ サービスでは、HTTP プロキシへの接続に特定の資格情報を使用することはサポートされていません。

#### 特定のポートでリッスンするようにプロキシ サービスを構成する

Microsoft Entra パスワード保護の DC エージェント ソフトウェアは、TCP 経由の RPC を使用してプロキシ サービスと通信します。 既定では、Microsoft Entra パスワード保護のプロキシ サービスは、使用可能な動的 RPC エンドポイントでリッスンします。 ご使用の環境のネットワーク トポロジまたはファイアウォールの要件のために必要な場合には、特定の TCP ポート上でリッスンするようにサービスを構成できます。 静的ポートを構成する場合は、ポート 135 と、選択した静的ポートを開く必要があります。

静的ポートで実行するようにサービスを構成するには、次のように `Set-AzureADPasswordProtectionProxyConfiguration` コマンドレットを使用します。

```powershell
Set-AzureADPasswordProtectionProxyConfiguration –StaticPort <portnumber>
```

警告

これらの変更を有効にするには、Microsoft Entra パスワード保護のプロキシ サービスを停止して再起動する必要があります。

動的ポートで実行するようにサービスを構成するには、同じ手順を使用しますが、 *StaticPort* をゼロに戻します。

```powershell
Set-AzureADPasswordProtectionProxyConfiguration –StaticPort 0
```

警告

これらの変更を有効にするには、Microsoft Entra パスワード保護のプロキシ サービスを停止して再起動する必要があります。

ポート構成の変更後、Microsoft Entra パスワード保護のプロキシ サービスを手動で再起動する必要があります。 これらの構成変更の後、ドメイン コントローラー上の Microsoft Entra パスワード保護の DC エージェント サービスを再起動する必要はありません。

サービスの現在の構成を照会するには、次の例に示すように `Get-AzureADPasswordProtectionProxyConfiguration` コマンドレットを使用します

```powershell
Get-AzureADPasswordProtectionProxyConfiguration | fl
```

次の出力例は、Microsoft Entra パスワード保護のプロキシ サービスが動的ポートを使用していることを示しています。

```output
ServiceName : AzureADPasswordProtectionProxy
DisplayName : Azure AD password protection Proxy
StaticPort  : 0
```

### DC エージェント サービスをインストールする

Microsoft Entra パスワード保護の DC エージェント サービスをインストールするには、`AzureADPasswordProtectionDCAgentSetup.msi` パッケージを実行します。

ソフトウェアのインストールは、次の例に示すように、標準 MSI プロシージャを使用して自動化できます。

```console
msiexec.exe /i AzureADPasswordProtectionDCAgentSetup.msi /quiet /qn /norestart
```

インストーラーでマシンを自動的に再起動する場合は、`/norestart` フラグを省略できます。

ソフトウェアをインストールまたはアンインストールすると再起動が必要になります。 この要件は、パスワード フィルター DLL は再起動しないとロードまたはアンロードされないためです。

オンプレミスの Microsoft Entra パスワード保護のインストールが完了するのは、DC エージェント ソフトウェアがドメイン コントローラーにインストールされ、そのコンピューターが再起動された後です。 これ以外の構成は必要ないか、可能ではありません。 オンプレミスの DC に対するパスワード変更イベントでは、Microsoft Entra の構成された禁止パスワードの一覧が使用されます。

オンプレミスの Microsoft Entra パスワード保護を有効にするか、カスタム禁止パスワードを構成するには、「 [オンプレミスの Microsoft Entra パスワード保護を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-operations)」を参照してください。

ヒント

Microsoft Entra パスワード保護の DC エージェントは、まだドメイン コントローラーになっていないマシンにインストールできます。 この場合、サービスは開始して実行されますが、マシンがドメイン コントローラーにレベル上げされるまでアクティブになりません。

### プロキシ サービスのアップグレード

Microsoft Entra パスワード保護のプロキシサービスでは、自動アップグレードがサポートされています。 自動アップグレードでは、プロキシ サービスとサイド バイ サイドでインストールされる Microsoft Entra Connect エージェント アップデーター サービスが使用されます。 自動アップグレードは既定でオンになっており、`Set-AzureADPasswordProtectionProxyConfiguration` コマンドレットを使用して有効または無効にすることができます。

現在の設定は、`Get-AzureADPasswordProtectionProxyConfiguration` コマンドレットを使用して照会できます。 自動アップグレードの設定を常に有効にしておくことをお勧めします。

`Get-AzureADPasswordProtectionProxy` コマンドレットを使用して、フォレストに現在インストールされているすべての Microsoft Entra パスワード保護のプロキシ サーバーのソフトウェア バージョンを照会できます。

注

プロキシ サービスは、重要なセキュリティ パッチが必要な場合にのみ、新しいバージョンに自動的にアップグレードされます。

#### 手動アップグレード プロセス

手動アップグレードを行うには、`AzureADPasswordProtectionProxySetup.exe` ソフトウェア インストーラーの最新バージョンを実行します。 最新バージョンのソフトウェアは、 [Microsoft ダウンロード センター](https://www.microsoft.com/download/details.aspx?id=57071)で入手できます。

現在のバージョンの Microsoft Entra パスワード保護のプロキシ サービスをアンインストールする必要はありません。インストーラーによってインプレース アップグレードが実行されます。 プロキシ サービスをアップグレードする際に、再起動は不要です。 ソフトウェアのアップグレードは、標準 MSI プロシージャを使用して自動化できます。たとえば、`AzureADPasswordProtectionProxySetup.exe /quiet` などです。

### DC エージェントのアップグレード

新しいバージョンの Microsoft Entra パスワード保護の DC エージェント ソフトウェアを使用できるようになったら、最新バージョンの `AzureADPasswordProtectionDCAgentSetup.msi` ソフトウェア パッケージを実行してアップグレードを行います。 最新バージョンのソフトウェアは、 [Microsoft ダウンロード センター](https://www.microsoft.com/download/details.aspx?id=57071)で入手できます。

現在のバージョンの DC エージェント ソフトウェアをアンインストールする必要はありません。インストーラーによってインプレース アップグレードが実行されます。 DC エージェント ソフトウェアをアップグレードするときは、再起動が常に必要になります。この要件は、Windows のコア動作によるものです。

ソフトウェアのアップグレードは、標準 MSI プロシージャを使用して自動化できます。たとえば、`msiexec.exe /i AzureADPasswordProtectionDCAgentSetup.msi /quiet /qn /norestart` などです。

インストーラーでコンピューターが自動的に再起動されるようにする場合は、`/norestart` フラグを省略できます。

`Get-AzureADPasswordProtectionDCAgent` コマンドレットを使用して、フォレストに現在インストールされているすべての Microsoft Entra パスワード保護の DC エージェントのソフトウェア バージョンを照会できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-password-ban-bad-on-premises-faq"} -->
## オンプレミスの Microsoft Entra パスワード保護に関する FAQ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-faq
- Service: entra-id / authentication
- Article date: 2025-01-14
- Summary: オンプレミスの Active Directory Domain Services 環境での Microsoft Entra パスワード保護に関してよく寄せられる質問を確認する

このセクションでは、Microsoft Entra パスワード保護に関してよく寄せられる質問の多くへの回答を提供します。

### 一般的な質問

#### 安全なパスワードを選択する方法に関して、ユーザーにどのようなガイダンスが与えられますか?

このトピックに関する Microsoft の現在のガイダンスについては、次のリンクを参照してください。

[Microsoft のパスワードのガイダンス](https://www.microsoft.com/research/publication/password-guidance)

#### オンプレミスの Microsoft Entra パスワード保護はパブリックでないクラウドでサポートされますか?

オンプレミスの Microsoft Entra パスワード保護は、Azure グローバルと Azure Government の両方のクラウドでサポートされています。

Microsoft Entra 管理センターでは、サポートされているクラウド以外の場合でもオンプレミス固有の [Windows Server Active Directory のパスワード保護] 構成を変更できます。このような変更は保存されますが、実行されません。 サポートされていないクラウドでは、オンプレミスのプロキシ エージェントまたはフォレストの登録はサポートされません。そのような登録の試行は常に失敗します。

#### どのようにして Microsoft Entra パスワード保護の利点を自分のオンプレミスのユーザーのサブセットに適用できますか?

サポートされていません。 展開して有効にすると、Microsoft Entra パスワード保護はすべてのユーザーに均等に適用されます。

#### パスワードの変更とパスワードの設定 (またはリセット) の違いは何ですか?

パスワードの変更は、ユーザーが古いパスワードを知っていることを証明した後に新しいパスワードを選択する場合のアクションです。 パスワードの変更は、たとえば、ユーザーが Windows にログインした後に新しいパスワードを選択するように求められたときに発生します。

パスワードの設定 (パスワードのリセットとも呼ばれます) は、たとえば Active Directory ユーザーとコンピューターの管理ツールを使用して、管理者がアカウントのパスワードを新しいパスワードに置き換える場合のアクションです。 この操作には高いレベルの特権 (通常はドメイン管理者) が必要であり、通常、操作を実行する担当者は古いパスワードを知りません。 ヘルプ デスクのシナリオで、パスワードの設定がよく実行されます。たとえば、パスワードを忘れたユーザーを支援する場合などです。 また、パスワードを指定して新しいユーザー アカウントを初めて作成するときにもパスワードの設定イベントが発生します。

パスワード検証ポリシーは、実行されているのがパスワードの変更か設定かに関係なく同じように動作します。 Microsoft Entra パスワード保護 DC エージェント サービスは、パスワードの変更または設定の操作が行われたかをユーザーに通知するために、さまざまなイベントをログに記録します。 「[Microsoft Entra パスワード保護の監視とログ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-monitor)」を参照してください。

#### Microsoft Entra のパスワード保護では、インストール後に既存のパスワードが検証されますか?

いいえ。Microsoft Entra のパスワード保護では、パスワードの変更または設定操作中に、クリア テキストのパスワードに対してのみパスワード ポリシーを適用できます。 Active Directory がパスワードを受け入れると、そのパスワードの認証プロトコル固有のハッシュのみが保持されます。 平文のパスワードは保存されないため、Microsoft Entra パスワード保護は既存のパスワードを検証できません。

Microsoft Entra のパスワード保護を最初にデプロイした後、すべてのユーザーとアカウントでは、時間の経過に伴い通常どおり既存のパスワードの有効期限が切れると、Microsoft Entra のパスワード保護で検証されたパスワードを使用し始めます。 必要に応じて、ユーザー アカウント パスワードを 1 回だけ手動で期限切れにすることによって、このプロセスを高速化できます。

[パスワードを無期限にする] を使用して構成されたアカウントの場合は、手動で期限切れにしない限りパスワードの変更が強制されることはありません。

#### Active Directory ユーザーとコンピューター管理スナップインを利用して弱いパスワードを設定しようとすると、重複パスワード拒否イベントがログに記録されるのはなぜですか。

Active Directory ユーザーとコンピューター管理スナップインではまず、Kerberos プロトコルを利用して新しいパスワードを設定しようとします。 エラーが発生すると、スナップインは、レガシ (SAM RPC) プロトコルを使用してもう一度パスワードを設定しようとします。 使用される特定のプロトコルは重要ではありません。 新しいパスワードが Microsoft Entra パスワード保護で脆弱であると見なされると、このスナップイン動作の結果、2 セットのパスワード リセット拒否イベントがログに記録されることになります。

#### Microsoft Entra パスワード保護のパスワード検証イベントが空のユーザー名でログに記録されるのはなぜですか。

Active Directory では、パスワードをテストして、ドメインの現在のパスワード複雑さ要件が満たされているかどうかを確認する機能がサポートされています (たとえば、[NetValidatePasswordPolicy](https://learn.microsoft.com/ja-jp/windows/win32/api/lmaccess/nf-lmaccess-netvalidatepasswordpolicy) API を使用して)。 この方法でパスワードが検証される際、テストには、Microsoft Entra パスワード保護などのパスワード フィルター dll ベースの製品による検証も含まれます。しかし、所定のパスワード フィルター dll に渡されるユーザー名は空になります。 このシナリオにおいても、Microsoft Entra パスワード保護は、現在有効なパスワード ポリシーを使用してパスワードを検証し、結果をキャプチャするためのイベント ログ メッセージを発行します。 ただし、イベント ログ メッセージのユーザー名フィールドは空になります。

#### Microsoft Entra ID でパスワードの変更を試みたところ、"同じパスワードがこれまでに何度も使われています。 より推測されにくいパスワードをお選びください。" という応答が返されたハイブリッド ユーザーがいます。この場合、オンプレミスの検証の試行が表示されないのはなぜですか?

ハイブリッド ユーザーが Microsoft Entra ID でパスワードを変更すると、Microsoft Entra SSPR、MyAccount、または別の Microsoft Entra パスワード変更メカニズムを使用した場合でも、そのパスワードはクラウド内のグローバルおよびカスタムの禁止パスワード リストに照らして評価されます。 パスワードがパスワード書き戻しにより Active Directory に到達するときには、Microsoft Entra ID での検証が完了しています。

Microsoft Entra ID で開始され、検証に失敗したハイブリッド ユーザーのパスワードのリセットと変更は、Microsoft Entra 監査ログで確認できます。 「[Microsoft Entra ID でのセルフサービス パスワード リセットのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/authentication/troubleshoot-sspr)」を参照してください。

#### その他のパスワードフィルターベースの製品と並行して Microsoft Entra パスワード保護をインストールすることはサポートされていますか?

はい。 複数の登録されたパスワード フィルター dll に対するサポートは、コア Windows 機能であり、Microsoft Entra パスワード保護に固有のものではありません。 登録されたパスワード フィルター DLL はすべて、パスワードを受け入れる前に一致している必要があります。

#### Azure を使用せずに、Active Directory 環境に Microsoft Entra パスワード保護をデプロイし、構成するにはどうすればよいですか?

サポートされていません。 Microsoft Entra パスワード保護は、オンプレミスの Active Directory 環境への拡張をサポートする Azure の機能です。

#### Active Directory レベルでポリシーの内容を変更するにはどうすればよいですか?

サポートされていません。 ポリシーは、Microsoft Entra 管理センターを使用してのみ管理できます。 前の質問を参照してください。

#### sysvol レプリケーションに DFSR が必要なのはなぜですか?

FRS (DFSR に対する先行テクノロジ) には、多くの既知の問題があり、より新しいバージョンの Windows Server Active Directory ではまったくサポートされていません。 FRS で構成されたドメインでは Microsoft Entra パスワード保護のテストは全く行われません。

詳細については、次の記事を参照してください。

[sysvol レプリケーションを DFSR に移行するケース](https://learn.microsoft.com/ja-jp/archive/blogs/askds/the-case-for-migrating-sysvol-to-dfsr)

[FRS の終了が近づいています](https://blogs.technet.microsoft.com/filecab/2014/06/25/the-end-is-nigh-for-frs)

ご利用のドメインでまだ DFSR を使用していない場合、Microsoft Entra パスワード保護をインストールする前に DFSR 使用にドメインを移行する必要があります。 詳細については、次のリンクを参照してください。

[SYSVOL レプリケーション移行ガイド: FRS レプリケーションから DFS レプリケーションに移行する](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2008-R2-and-2008/dd640019%28v=ws.10%29)

警告

Microsoft Entra パスワード保護 DC エージェント ソフトウェアは、現在、sysvol レプリケーションにまだ FRS を使用しているドメインのドメイン コントローラーにインストールされますが、この環境ではソフトウェアは正しく機能しません。 マイナスの副作用としては、個々のファイルを複製できない、sysvol 復元処理が成功したように見えたが、一部のファイルの複製に失敗しており、何のエラーも表示されない、などがあります。 DFSR に固有のメリットと Microsoft Entra パスワード保護のデプロイのブロック解除の両方のために、できるだけ早く DFSR を使用するようにドメインの移行を行う必要があります。 今後のバージョンでは、ドメインで依然として FRS を使用している場合、このソフトウェアは自動的に無効になります。

#### この機能では、ドメイン sysvol 共有にどのくらいのディスク領域が必要ですか?

正確な領域の使用量は、Microsoft グローバル禁止リストとテナントごとのカスタム リストで禁止されているトークンの数と長さ、および暗号化オーバーヘッドなどの要素に依存するため、さまざまに異なります。 これらのリストの内容は、将来拡大する可能性があります。 このことを念頭に、妥当な予想として、機能には、ドメイン sysvol 共有に最低 5 メガバイトの領域が必要になります。

#### DC エージェント ソフトウェアのインストールまたはアップグレードに再起動が必要なのはなぜですか?

これはコア Windows 動作によって必要になります。

#### 特定のプロキシ サーバーを使用するように DC エージェントを構成する方法はありますか?

いいえ。 プロキシ サーバーはステートレスであるため、特定のどのプロキシ サーバーを使用するかは重要ではありません。

#### Microsoft Entra Connect などの他のサービスと並行して Microsoft Entra パスワード保護プロキシ サービスをデプロイしても大丈夫ですか?

はい。 Microsoft Entra パスワード保護プロキシ サービスと Microsoft Entra Connect は、互いに直接競合することはないはずです。

残念ながら、Microsoft Entra パスワード保護プロキシ ソフトウェアは、[Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy) ソフトウェアによってインストールされたバージョンと互換性のないバージョンの Microsoft Entra Connect Agent Updater サービスをインストールします。 この非互換性が原因で、エージェント アップデーター サービスがソフトウェアの更新のために Azure に接続できなくなっている可能性があります。 Microsoft Entra パスワード保護プロキシと Microsoft Entra アプリケーション プロキシを同じマシンにインストールすることはお勧めしません。

#### DC エージェントとプロキシはどのような順序でインストールして登録する必要がありますか?

プロキシ エージェントのインストール、DC エージェントのインストール、フォレストの登録、プロキシの登録は、任意の順序での実行がサポートされています。

#### この機能のデプロイによるドメイン コントローラーでのパフォーマンスの影響を懸念する必要はありますか?

Microsoft Entra パスワード保護 DC エージェント サービスは、既存の正常な Active Directory デプロイにおいてドメイン コントローラーのパフォーマンスに著しく影響を及ぼすことはないはずです。

ほとんどの Active Directory デプロイで、パスワード変更操作は、特定のどのドメイン コントローラーでもワークロード全体のごく一部になります。 たとえば、10000 ユーザー アカウントと 30 日に設定された MaxPasswordAge ポリシーがある Active Directory ドメインを想像してください。 平均で、このドメインでは毎日 10000/30 = 333 回までのパスワード変更操作が発生しますが、これは 1 つのドメイン コントローラーの場合でも少ない操作数です。 可能性のある最悪のケースのシナリオを考えてみます。1 時間に 1 つの DC でこの 333 回までのパスワードの変更が行われたとします。 たとえば、月曜日の朝に多数の従業員が全員出勤してくる場合に、このシナリオが発生する可能性があります。 この場合でも、333 回まで/60 分 = 1 分あたり 6 回のパスワードの変更が発生することになり、これも同様に重大な負荷ではありません。

ただし現在のドメイン コントローラーが既にパフォーマンス制限付きレベルで実行している (たとえば、CPU、ディスク領域、ディスク I/O に関して最大限に達しているなど) 場合は、この機能をデプロイする前に、ドメイン コン ローラーを追加するか、または使用可能なディスク領域を拡張することをお勧めします。 上記の sysvol ディスク領域の使用に関する前の質問を参照してください。

#### ドメイン内のほんの一部の DC で Microsoft Entra パスワード保護をテストしたいと思います。 ユーザー パスワードの変更で、これらの特定の DC を強制的に使用させることはできますか?

いいえ。 Windows クライアント OS は、ユーザーがパスワードを変更するときに、どのドメイン コントローラーを使用するかを制御します。 ドメイン コントローラーは、Active Directory サイトとサブネット割り当て、環境固有のネットワーク構成などの要素に基づいて選択されます。 Microsoft Entra パスワード保護は、これらの要素を制御せず、ユーザーのパスワードを変更するためにどのドメイン コントローラーが選択されるかに影響を与えることはできません。

この目標に部分的に到達する 1 つの方法は、特定の Active Directory サイト内のすべてのドメイン コントローラーに Microsoft Entra パスワード保護をデプロイすることです。 このアプローチは、そのサイトに割り当てられる Windows クライアントに対し、そしてそれらのクライアントにログインし、パスワードを変更するユーザーに対しても、妥当なカバレッジを提供します。

#### プライマリ ドメイン コントローラー (PDC) にだけ Microsoft Entra パスワード保護 DC エージェント サービスをインストールした場合、ドメイン内のその他すべてのドメイン コントローラーも保護されますか?

いいえ。 PDC 以外の特定のドメイン コントローラーで、ユーザーのパスワードが変更された場合、クリアテキスト パスワードは PDC に送信されません (この考えは一般的な誤解です)。 特定の DC で、新しいパスワードが受け入れられると、その DC はそのパスワードを使用して、そのパスワードのさまざまな認証プロトコル固有のハッシュを作成し、ディレクトリでそれらのハッシュを保持します。 クリア テキスト パスワードは保持されません。 更新されたハッシュが、PDC にレプリケートされます。 場合により、ユーザー パスワードは、ネットワーク トポロジや Active Directory サイトの設計などのさまざまな要素に応じて、PDC で直接変更されることがあります。 (前の質問を参照してください。)

まとめると、PDC への Microsoft Entra パスワード保護 DC エージェント サービスのデプロイでは、ドメイン全体で 100% の機能のセキュリティ カバレッジに到達する必要があります。 PDC のみに機能をデプロイしても、ドメイン内の他の DC には、Microsoft Entra パスワード保護のセキュリティ上の恩恵はありません。

#### オンプレミスの Active Directory 環境にエージェントをインストールした後でも、カスタム スマート ロックアウトが機能しないのはなぜですか?

カスタム スマート ロックアウトは Microsoft Entra ID でのみサポートされています。 エージェントがインストールされていても、オンプレミスの Active Directory 環境では、Microsoft Entra 管理センターでカスタム スマート ロックアウトの設定を変更しても環境への影響はありません。

#### Microsoft Entra パスワード保護で、System Center Operations Manager 管理パックは使用できますか?

いいえ。

#### ポリシーが監査モードになるよう構成した場合でも Microsoft Entra ID が依然として脆弱なパスワードを拒否しているのはなぜですか?

監査モードがサポートされるのは、オンプレミスの Active Directory 環境でのみです。 Microsoft Entra ID は、パスワードを評価する際、暗黙的に常に "強制" モードになります。

#### パスワードが Microsoft Entra パスワード保護によって拒否された場合、ユーザーには従来の Windows エラー メッセージが表示されます。 このエラー メッセージをカスタマイズして、実際に発生したことをユーザーに知らせることはできますか?

いいえ。 ドメイン コントローラーによってパスワードが拒否されたときにユーザーに表示されるエラー メッセージは、ドメイン コントローラーではなく、クライアント コンピューターによって制御されています。 この動作は、パスワードが既定の Active Directory パスワード ポリシーによって拒否されたか、Microsoft Entra パスワード保護などのパスワード フィルター ベースのソリューションによって拒否されたかにかかわらず、発生します。

### パスワードのテスト手順

ソフトウェアの適切な動作を検証するため、および[パスワード評価アルゴリズム](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad#how-are-passwords-evaluated)について理解を深めるために、さまざまなパスワードの基本的なテストを実行することが望ましい場合があります。 このセクションでは、反復可能な結果を生成することを目的とした、このようなテストの方法について概説します。

このような手順に従う必要があるのはなぜでしょうか? オンプレミスの Active Directory 環境には、パスワードの制御された反復可能なテストを実行することが困難になる要因がいくつかあります。

- パスワード ポリシーが Azure で構成および永続化され、ポリシーのコピーが、ポーリング メカニズムを使用して、オンプレミスの DC エージェントによって定期的に同期されます。 このポーリング サイクルに固有の待機時間が原因で混乱が発生する可能性があります。 たとえば、Azure でポリシーを構成しても、それを DC エージェントに同期することを忘れた場合、テストで期待どおりの結果が得られないことがあります。 ポーリング間隔は、1 時間に 1 回になるように現在ハードコーディングされていますが、ポリシーの変更間に 1 時間待機することは、対話型のテスト シナリオでは理想的ではありません。
- 新しいパスワード ポリシーがドメイン コントローラーに同期された後、それが他のドメイン コントローラーにレプリケートされるまで、さらに待機時間が発生します。 最新バージョンのポリシーをまだ受け取っていないドメイン コントローラーに対してパスワードの変更をテストした場合、これらの遅延が原因で、予期しない結果が発生する可能性があります。
- ユーザー インターフェイスを使用してパスワードの変更をテストした場合、結果の信頼性を確保することが難しくなります。 たとえば、ユーザー インターフェイスに無効なパスワードを誤入力することは容易に起こります。これは特に、ほとんどのパスワード ユーザー インターフェイスでユーザー入力が非表示になっているためです (たとえば、Windows の Ctrl + Alt + Delete -&gt; [パスワードの変更] UI など)。
- ドメインに参加しているクライアントからパスワードの変更をテストするときに、どのドメイン コントローラーを使用するかを厳密に制御することはできません。 Windows クライアント OS では、Active Directory サイトとサブネットの割り当て、環境固有のネットワーキング構成などの要因に基づいてドメイン コントローラーが選択されます。

これらの問題を回避するため、以下の手順は、ドメイン コントローラーにログインしているときのパスワード リセットのコマンドライン テストに基づいています。

警告

これらの手順は、テスト環境でのみ使用してください。 DC エージェント サービスが停止している間、すべての受信パスワードの変更とリセットは検証なしで受け入れられます。 これは、ドメイン コントローラーにログインするリスクの増加を回避するのにも役立ちます。

次の手順は、DC エージェントが少なくとも 1 つのドメイン コントローラーにインストールされていること、少なくとも 1 つのプロキシがインストールされていること、プロキシとフォレストの両方が登録されていることを前提としています。

1. ドメイン管理者の資格情報、またはテスト ユーザー アカウントを作成し、パスワードをリセットするための十分な特権を持つその他の資格情報を使用して、ドメイン コントローラーにログオンします。 ドメイン コントローラーに DC エージェント ソフトウェアがインストールされ、ドメイン コントローラーが再起動されていることを確認します。
2. イベント ビューアーを開いて、[DC エージェント管理イベント ログ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-monitor#dc-agent-admin-event-log)に移動します。
3. 昇格した [コマンド プロンプト] ウィンドウを開きます。
4. パスワード テストを実行するためのテスト アカウントを作成します。

    ユーザー アカウントを作成する方法は多数ありますが、反復的なテスト サイクル中にこれを簡単に行う方法として、コマンドライン オプションが提供されています。

    ```text
    net.exe user <testuseraccountname> /add <password>
    ```

    下の説明のために、"ContosoUser" という名前のテスト アカウントを作成したと仮定します。次に例を示します。

    ```text
    net.exe user ContosoUser /add <password>
    ```
5. [認証管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator)にサインインします。
6. [保護]、[認証方法]、[パスワード保護] の順に進みます。
7. 実行したいテストの必要に応じて Microsoft Entra パスワード保護ポリシーを変更します。 たとえば、強制または監査のモードを構成する場合や、カスタムの禁止パスワード リストの禁止語句のリストを変更する場合があります。
8. DC エージェント サービスを停止および再起動して、新しいポリシーを同期します。

    この手順は、さまざまな方法で実行できます。 1 つの方法は、[Microsoft Entra パスワード保護 DC エージェント サービス] を右クリックし、"再起動" を選択することで、サービス管理の管理コンソールを使用することです。 もう 1 つの方法では、次のようにコマンド プロンプト ウィンドウから実行できます。

    ```text
    net stop AzureADPasswordProtectionDCAgent && net start AzureADPasswordProtectionDCAgent
    ```
9. イベント ビューアーを確認して、新しいポリシーがダウンロードされていることを確認します。

    DC エージェント サービスが停止して開始されるたびに、連続して発行された 2 つの 30006 イベントが表示されます。 1 番目の 30006 イベントには、sysvol 共有のディスクにキャッシュされたポリシーが反映されます。 2 番目の 30006 イベント (存在する場合) では、テナント ポリシーの日付が更新されており、その場合、Azure からダウンロードされたポリシーが反映されます。 テナント ポリシーの日付値は、ポリシーが Azure からダウンロードされたおおよそのタイムスタンプを示すように現在コーディングされています。

    2 番目の 30006 イベントが表示されない場合は、続行する前に問題をトラブルシューティングする必要があります。

    30006 イベントは、この例のようになります。

    ```text
    The service is now enforcing the following Azure password policy.
    
    Enabled: 1
    AuditOnly: 0
    Global policy date: ‎2018‎-‎05‎-‎15T00:00:00.000000000Z
    Tenant policy date: ‎2018‎-‎06‎-‎10T20:15:24.432457600Z
    Enforce tenant policy: 1
    ```

    たとえば、強制モードと監査モードを切り替えると、AuditOnly フラグが変更されます (AuditOnly=0 で一覧表示されているポリシーは強制モードです)。 カスタムの禁止パスワード リストに対する変更は、上記の 30006 イベントには直接反映されず、セキュリティ上の理由から他のどこにも記録されません。 この変更の後に Azure からポリシーを正常にダウンロードした場合は、変更されたカスタムの禁止パスワード リストも含まれます。
10. テスト ユーザー アカウントの新しいパスワードのリセットを試行することで、テストを実行します。

    この手順は、コマンド プロンプト ウィンドウから次のように実行できます。

    ```text
    net.exe user ContosoUser <password>
    ```

    コマンドを実行した後、イベント ビューアーを確認することで、コマンドの結果に関する詳細情報を得ることができます。 パスワード検証結果イベントについては、「[DC エージェント管理イベント ログ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-monitor#dc-agent-admin-event-log)」というトピックを参照してください。net.exe コマンドからの対話型出力に加えて、このようなイベントを使用して、テストの結果を検証します。

    例を試してみましょう。Microsoft グローバル リストで禁止されているパスワードを設定しようとしています (このリストは[ドキュメント化されていません](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad#global-banned-password-list)が、ここでは既知の禁止語句に対してテストすることができます)。 この例では、ポリシーを強制モードに構成し、カスタムの禁止パスワード リストに 0 個の語句を追加したことを前提としています。

    ```text
    net.exe user ContosoUser PassWord
    The password doesn't meet the password policy requirements. Check the minimum password length, password complexity, and password history requirements.
    
    More help is available by typing NET HELPMSG 2245.
    ```

    ドキュメントによると、このテストはパスワード リセット操作であったため、ContosoUser ユーザーに対して 10017 および 30005 イベントが表示されます。

    10017 イベントは、この例のようになります。

    ```text
    The reset password for the specified user was rejected because it didn't comply with the current Azure password policy. For more information, please see the correlated event log message.
    
    UserName: ContosoUser
    FullName: 
    ```

    30005 イベントは、この例のようになります。

    ```text
    The reset password for the specified user was rejected because it matched at least one of the tokens present in the Microsoft global banned password list of the current Azure password policy.
    
    UserName: ContosoUser
    FullName: 
    ```

    以上です。別の例を試してみましょう。 ここでは、ポリシーが監査モードのときに、カスタムの禁止リストによって禁止されているパスワードを設定してみます。 この例は、次の手順を実行したことを前提としています。ポリシーを監査モードに構成し、カスタムの禁止パスワード リストに "lachrymose" という語句を追加し、すでに説明したように、DC エージェント サービスを停止して開始することで、結果の新しいポリシーをドメイン コントローラーに同期しました。

    次に、禁止パスワードのバリエーションを設定します。

    ```text
    net.exe user ContosoUser LaChRymoSE!1
    The command completed successfully.
    ```

    今回は、ポリシーが監査モードであるために成功したということを覚えておいてください。 ContosoUser ユーザーに対して 10025 および 30007 イベントが表示されます。

    10025 イベントは、この例のようになります。

    ```text
    The reset password for the specified user would normally have been rejected because it didn't comply with the current Azure password policy. The current Azure password policy is configured for audit-only mode so the password was accepted. Please see the correlated event log message for more details.
    
    UserName: ContosoUser
    FullName: 
    ```

    30007 イベントは、この例のようになります。

    ```text
    The reset password for the specified user would normally be rejected because it matches at least one of the tokens present in the per-tenant banned password list of the current Azure password policy. The current Azure password policy is configured for audit-only mode so the password was accepted.
    
    UserName: ContosoUser
    FullName: 
    ```
11. 前のステップで説明した手順を使用して、引き続き任意のさまざまなパスワードをテストし、イベント ビューアーで結果を確認します。 Microsoft Entra 管理センターでポリシーを変更する必要がある場合は、前に説明したように、新しいポリシーを DC エージェントに同期することを忘れないでください。

Microsoft Entra パスワード保護のパスワード検証動作の制御されたテストを実行できるようにする手順について説明しました。 ドメイン コントローラーでコマンド ラインからユーザー パスワードを直接リセットすることは、このようなテストを実行するための特殊な手段のように思われるかもしれませんが、前述のように、この目的は反復可能な結果を生成することです。 さまざまなパスワードをテストする際には、[パスワード評価アルゴリズム](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad#how-are-passwords-evaluated)を念頭に置いてください。これは、予期しない結果を説明するのに役立つ場合があるためです。

警告

すべてのテストが完了したら、テストのために作成したユーザー アカウントを忘れずに削除してください。

### その他のコンテンツ

次のリンクは、主要な Microsoft Entra パスワード保護ドキュメントの一部ではありませんが、機能に関する追加情報の有用なソースになるかもしれません。

[Microsoft Entra Password Protection is now generally available! (Microsoft Entra パスワード保護の一般提供が開始されました)](https://techcommunity.microsoft.com/t5/Azure-Active-Directory-Identity/Azure-AD-Password-Protection-is-now-generally-available/ba-p/377487)

[メールのフィッシング保護ガイド パート 15: Microsoft Entra パスワード保護サービスを実装する (オンプレミスの場合も)](http://kmartins.com/2018/10/14/email-phishing-protection-guide-part-15-implement-the-microsoft-azure-ad-password-protection-service-for-on-premises-too/)

[Microsoft Entra Password Protection and Smart Lockout are now in Public Preview! (Microsoft Entra パスワード保護およびスマート ロックアウトがパブリック プレビュー段階になりました)](https://techcommunity.microsoft.com/t5/Azure-Active-Directory-Identity/Azure-AD-Password-Protection-and-Smart-Lockout-are-now-in-Public/ba-p/245423#M529)

### Microsoft Premier\Unified サポート トレーニングの提供開始

Microsoft Entra パスワード保護とその展開方法の詳細については、Microsoft プロアクティブ サービスを使用してください。 このサービスは、Premier または Unified サポート契約をお持ちのお客様が利用できます。 サービスの名称は Microsoft Entra ID: パスワード保護です。 詳細については、カスタマー サクセス アカウント マネージャーにお問い合わせください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-password-ban-bad-on-premises-monitor"} -->
## オンプレミスの Microsoft Entra パスワード保護を監視する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-monitor
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: オンプレミスの Active Directory Domain Services 環境の Microsoft Entra Password Protection のログを監視および確認する方法について説明します

Microsoft Entra Password Protection の展開後、監視とレポートは重要なタスクです。 この記事では、各サービスが情報をログに記録する場所や、Microsoft Entra パスワード保護の使用方法を報告する方法など、さまざまな監視手法を理解するのに役立つ詳細について説明します。

監視とレポートは、イベント ログ メッセージまたは PowerShell コマンドレットを実行して実行されます。 DC エージェントとプロキシは、両方のログ イベント ログ メッセージを処理します。 以下で説明するすべての PowerShell コマンドレットは、プロキシ サーバーでのみ使用できます (AzureADPasswordProtection PowerShell モジュールを参照)。 DC エージェント ソフトウェアは PowerShell モジュールをインストールしません。

### DC エージェント イベントのログ記録

各ドメイン コントローラーで、DC エージェント サービス ソフトウェアは、個々のパスワード検証操作 (およびその他の状態) の結果をローカル イベント ログに書き込みます。

`\Applications and Services Logs\Microsoft\AzureADPasswordProtection\DCAgent\Admin`

`\Applications and Services Logs\Microsoft\AzureADPasswordProtection\DCAgent\Operational`

`\Applications and Services Logs\Microsoft\AzureADPasswordProtection\DCAgent\Trace`

DC エージェント管理ログは、ソフトウェアの動作に関する情報の主要なソースです。

トレース ログは既定でオフになっていることに注意してください。

さまざまな DC エージェント コンポーネントによってログに記録されるイベントは、次の範囲内にあります。

| コンポーネント | イベント ID の範囲 |
| --- | --- |
| DC エージェント パスワード フィルター dll | 10000-19999 |
| DC エージェント サービスのホスティング プロセス | 20000-29999 |
| DC エージェント サービス ポリシー検証ロジック | 30000-39999 |

### DC エージェント管理者イベントログ

#### パスワード検証の結果イベント

各ドメイン コントローラーで、DC エージェント サービス ソフトウェアは、個々のパスワード検証の結果を DC エージェント管理者イベント ログに書き込みます。

パスワード検証操作を成功させるには、通常、DC エージェントのパスワード フィルター dll から 1 つのイベントがログに記録されます。 パスワード検証操作が失敗した場合、一般に、DC エージェント サービスと DC エージェント パスワード フィルター dll の 2 つのイベントがログに記録されます。

これらの状況をキャプチャする個別のイベントは、次の要因に基づいてログに記録されます。

- 特定のパスワードが設定されているか変更されているか。
- 特定のパスワードの検証に合格したか失敗したか。
- Microsoft グローバル ポリシー、組織ポリシー、または組み合わせによって検証が失敗したかどうか。
- 現在のパスワード ポリシーに対して監査のみのモードが現在有効または無効になっているかどうか。

パスワード検証関連の主要なイベントは次のとおりです。

| 出来事 | パスワードの変更 | パスワード セット |
| --- | --- | --- |
| パス | 10014 | 10015 |
| 失敗 (顧客のパスワード ポリシーが原因) | 10016, 30002 | 10017, 30003 |
| 失敗 (Microsoft パスワード ポリシーが原因) | 10016, 30004 | 10017, 30005 |
| 失敗 (Microsoft と顧客のパスワード ポリシーが組み合わされているため) | 10016, 30026 | 10017, 30027 |
| 失敗 (ユーザー名が原因) | 10016, 30021 | 10017, 30022 |
| 監査のみの合格 (顧客のパスワード ポリシーに失敗) | 10024, 30008 | 10025, 30007 |
| 監査のみの合格 (Microsoft のパスワード ポリシーに失敗) | 10024, 30010 | 10025, 30009 |
| 監査専用パス (Microsoft と顧客のパスワード ポリシーを組み合わせて失敗した可能性があります) | 10024, 30028 | 10025, 30029 |
| 監査のみの合格 (ユーザー名のために失敗) | 10016, 30024 | 10017, 30023 |

上の表の「組み合わせポリシー」を参照するケースは、ユーザーのパスワードに Microsoft 禁止パスワード リストと顧客禁止パスワード リストの両方から少なくとも 1 つのトークンが含まれていることが判明した状況を指しています。

上の表の 「ユーザー名」を参照するケースは、ユーザーのパスワードにユーザーのアカウント名またはユーザーのフレンドリ名のいずれかが含まれていることが判明した状況を示しています。 どちらのシナリオでも、ポリシーが [強制] に設定されている場合はユーザーのパスワードが拒否されるか、ポリシーが監査モードの場合は渡されます。

イベントのペアが一緒にログに記録されると、両方のイベントが同じ CorrelationId を持つことによって明示的に関連付けられます。

#### PowerShell を使用したパスワード検証の概要レポート

`Get-AzureADPasswordProtectionSummaryReport` コマンドレットは、パスワード検証アクティビティの概要ビューを生成するために使用できます。 このコマンドレットの出力例は次のとおりです。

```powershell
Get-AzureADPasswordProtectionSummaryReport -DomainController bplrootdc2
DomainController                : bplrootdc2
PasswordChangesValidated        : 6677
PasswordSetsValidated           : 9
PasswordChangesRejected         : 10868
PasswordSetsRejected            : 34
PasswordChangeAuditOnlyFailures : 213
PasswordSetAuditOnlyFailures    : 3
PasswordChangeErrors            : 0
PasswordSetErrors               : 1
```

コマンドレットのレポートのスコープは、–Forest、-Domain、または –DomainController パラメーターのいずれかを使用して影響を受ける可能性があります。 パラメーターを指定しない場合は、–Forest を意味します。

注

DC エージェントを 1 つの DC にのみインストールする場合、Get-AzureADPasswordProtectionSummaryReport はその DC からのみイベントを読み取ります。 複数の DC からイベントを取得するには、各 DC に DC エージェントがインストールされている必要があります。

`Get-AzureADPasswordProtectionSummaryReport` コマンドレットは、DC エージェント管理者イベント ログに対してクエリを実行し、表示される各結果カテゴリに対応するイベントの合計数をカウントすることで機能します。 次の表に、各結果とそれに対応するイベント ID の間のマッピングを示します。

| Get-AzureADPasswordProtectionSummaryReport のプロパティ | 対応するイベント ID |
| --- | --- |
| パスワード変更が検証されました | 10014 |
| PasswordSetsValidated | 10015 |
| パスワード変更が拒否されました | 10016 |
| パスワード設定拒否 | 10017 |
| PasswordChangeAuditOnlyFailures | 10024 |
| PasswordSetAuditOnlyFailures | 10025 |
| パスワード変更エラー | 10012 |
| パスワード設定エラー | 10013 |

`Get-AzureADPasswordProtectionSummaryReport` コマンドレットは PowerShell スクリプト 形式で配布され、必要に応じて次の場所で直接参照できます。

`%ProgramFiles%\WindowsPowerShell\Modules\AzureADPasswordProtection\Get-AzureADPasswordProtectionSummaryReport.ps1`

注

このコマンドレットは、各ドメイン コントローラーへの PowerShell セッションを開くことで機能します。 成功するには、各ドメイン コントローラーで PowerShell リモート セッションのサポートを有効にする必要があり、クライアントには十分な特権が必要です。 PowerShell リモート セッションの要件の詳細については、PowerShell ウィンドウで 'Get-Help about\_Remote\_Troubleshooting' を実行してください。

注

このコマンドレットは、各 DC エージェント サービスの管理者イベント ログに対してリモートでクエリを実行することで機能します。 イベント ログに多数のイベントが含まれている場合、コマンドレットの完了に時間がかかる場合があります。 さらに、大規模なデータ セットの一括ネットワーク クエリは、ドメイン コントローラーのパフォーマンスに影響する可能性があります。 そのため、このコマンドレットは運用環境で慎重に使用する必要があります。

#### イベント ログ メッセージのサンプル

##### イベント ID 10014 (パスワードの変更に成功)

```text
The changed password for the specified user was validated as compliant with the current Azure password policy.

UserName: SomeUser
FullName: Some User
```

##### イベント ID 10017 (パスワードの変更に失敗):

```text
The reset password for the specified user was rejected because it did not comply with the current Azure password policy. Please see the correlated event log message for more details.

UserName: SomeUser
FullName: Some User
```

##### イベント ID 30003 (パスワードの変更に失敗):

```text
The reset password for the specified user was rejected because it matched at least one of the tokens present in the per-tenant banned password list of the current Azure password policy.

UserName: SomeUser
FullName: Some User
```

##### イベント ID 10024 (監査専用モードのポリシーにより受け入れられたパスワード)

```text
The changed password for the specified user would normally have been rejected because it did not comply with the current Azure password policy. The current Azure password policy is con-figured for audit-only mode so the password was accepted. Please see the correlated event log message for more details. 
 
UserName: SomeUser
FullName: Some User
```

##### イベント ID 30008 (監査専用モードのポリシーによりパスワードが受け入れられます)

```text
The changed password for the specified user would normally have been rejected because it matches at least one of the tokens present in the per-tenant banned password list of the current Azure password policy. The current Azure password policy is configured for audit-only mode so the password was accepted. 

UserName: SomeUser
FullName: Some User

```

##### イベント ID 30001 (使用可能なポリシーがないためパスワードが受け入れられました)

```text
The password for the specified user was accepted because an Azure password policy is not available yet

UserName: SomeUser
FullName: Some User

This condition may be caused by one or more of the following reasons:%n

1. The forest has not yet been registered with Azure.

   Resolution steps: an administrator must register the forest using the Register-AzureADPasswordProtectionForest cmdlet.

2. An Azure AD password protection Proxy is not yet available on at least one machine in the current forest.

   Resolution steps: an administrator must install and register a proxy using the Register-AzureADPasswordProtectionProxy cmdlet.

3. This DC does not have network connectivity to any Azure AD password protection Proxy instances.

   Resolution steps: ensure network connectivity exists to at least one Azure AD password protection Proxy instance.

4. This DC does not have connectivity to other domain controllers in the domain.

   Resolution steps: ensure network connectivity exists to the domain.
```

##### イベント ID 30006 (新しいポリシーが適用されます)

```text
The service is now enforcing the following Azure password policy.

 Enabled: 1
 AuditOnly: 1
 Global policy date: ‎2018‎-‎05‎-‎15T00:00:00.000000000Z
 Tenant policy date: ‎2018‎-‎06‎-‎10T20:15:24.432457600Z
 Enforce tenant policy: 1
```

##### イベント ID 30019 (Microsoft Entra パスワード保護が無効になっています)

```text
The most recently obtained Azure password policy was configured to be disabled. All passwords submitted for validation from this point on will automatically be considered compliant with no processing performed.

No further events will be logged until the policy is changed.%n
```

### DC エージェント操作ログ

DC エージェント サービスでは、操作関連のイベントも次のログに記録されます。

`\Applications and Services Logs\Microsoft\AzureADPasswordProtection\DCAgent\Operational`

### DC エージェント トレース ログ

DC エージェント サービスでは、詳細なデバッグ レベルのトレース イベントを次のログに記録することもできます。

`\Applications and Services Logs\Microsoft\AzureADPasswordProtection\DCAgent\Trace`

トレース ログは既定で無効になっています。

Warnung

有効にすると、トレース ログは大量のイベントを受信し、ドメイン コントローラーのパフォーマンスに影響を与える可能性があります。 そのため、この拡張ログは、問題がより詳細な調査を必要とする場合にのみ有効にしてから、最小限の時間だけ有効にする必要があります。

### DC エージェントのテキストログ記録

DC エージェント サービスは、次のレジストリ値を設定することで、テキスト ログに書き込むよう構成できます。

```text
HKLM\System\CurrentControlSet\Services\AzureADPasswordProtectionDCAgent\Parameters!EnableTextLogging = 1 (REG_DWORD value)
```

既定では、テキスト ログは無効になっています。 この値を変更して有効にするには、DC エージェント サービスの再起動が必要です。 有効にすると、DC エージェント サービスは次の場所にあるログ ファイルに書き込みます。

`%ProgramFiles%\Azure AD Password Protection DC Agent\Logs`

ヒント

テキスト ログは、トレース ログに記録できるのと同じデバッグ レベルのエントリを受け取りますが、通常は確認と分析が簡単な形式です。

Warnung

有効にすると、このログは大量のイベントを受信し、ドメイン コントローラーのパフォーマンスに影響を与える可能性があります。 そのため、この拡張ログは、問題がより詳細な調査を必要とする場合にのみ有効にしてから、最小限の時間だけ有効にする必要があります。

### DC エージェントのパフォーマンスの監視

DC エージェント サービス ソフトウェアは、 **Microsoft Entra Password Protection** という名前のパフォーマンス カウンター オブジェクトをインストールします。 現在、次のパフォーマンス カウンターを使用できます。

| パフォーマンス カウンター名 | 説明 |
| --- | --- |
| 処理されたパスワード | このカウンターには、最後の再起動以降に処理された (受け入れられたか拒否された) パスワードの合計数が表示されます。 |
| 受け入れられたパスワード | このカウンターには、前回の再起動以降に受け入れられたパスワードの合計数が表示されます。 |
| パスワードが拒否されました | このカウンターには、前回の再起動以降に拒否されたパスワードの合計数が表示されます。 |
| 進行中のパスワード フィルター要求 | このカウンターには、現在進行中のパスワード フィルター要求の数が表示されます。 |
| パスワード フィルター要求のピーク | このカウンターには、前回の再起動以降の同時パスワード フィルター要求のピーク数が表示されます。 |
| パスワード フィルター要求エラー | このカウンターには、前回の再起動以降にエラーが発生したために失敗したパスワード フィルター要求の合計数が表示されます。 Microsoft Entra Password Protection DC エージェント サービスが実行されていない場合、エラーが発生する可能性があります。 |
| パスワード フィルター要求/秒 | このカウンターには、パスワードの処理速度が表示されます。 |
| パスワード フィルター要求の処理時間 | このカウンターには、パスワード フィルター要求の処理に必要な平均時間が表示されます。 |
| パスワード フィルター要求の処理時間のピーク | このカウンターには、前回の再起動以降のピーク時のパスワード フィルター要求の処理時間が表示されます。 |
| 監査モードのため、パスワードが受け入れられます。 | このカウンターには、通常は拒否されたが、パスワード ポリシーが監査モード (前回の再起動以降) に構成されたために受け入れられたパスワードの合計数が表示されます。 |

### DC エージェントの検出

`Get-AzureADPasswordProtectionDCAgent` コマンドレットは、ドメインまたはフォレストで実行されているさまざまな DC エージェントに関する基本情報を表示するために使用できます。 この情報は、実行中の DC エージェント サービスによって登録された serviceConnectionPoint オブジェクトから取得されます。

このコマンドレットの出力例は次のとおりです。

```powershell
Get-AzureADPasswordProtectionDCAgent
ServerFQDN            : bplChildDC2.bplchild.bplRootDomain.com
Domain                : bplchild.bplRootDomain.com
Forest                : bplRootDomain.com
PasswordPolicyDateUTC : 2/16/2018 8:35:01 AM
HeartbeatUTC          : 2/16/2018 8:35:02 AM
```

さまざまなプロパティは、各 DC エージェント サービスによって約 1 時間ごとに更新されます。 データは引き続き Active Directory レプリケーションの待機時間の影響を受けます。

コマンドレットのクエリのスコープは、–Forest パラメーターまたは –Domain パラメーターを使用して影響を受ける可能性があります。

HeartbeatUTC 値が古くなった場合、これは、そのドメイン コントローラー上の Microsoft Entra Password Protection DC エージェントが実行されていないか、アンインストールされているか、コンピューターが降格され、ドメイン コントローラーでなくなった現象である可能性があります。

PasswordPolicyDateUTC の値が古くなった場合、これは、そのマシン上の Microsoft Entra Password Protection DC エージェントが正常に動作しない現象である可能性があります。

### 使用可能な DC エージェントの新しいバージョン

DC エージェント サービスは、新しいバージョンの DC エージェント ソフトウェアが使用可能であることを検出すると、次に例を示す 30034 警告イベントを操作ログに記録します。

```text
An update for Azure AD Password Protection DC Agent is available.

If autoupgrade is enabled, this message may be ignored.

If autoupgrade is disabled, refer to the following link for the latest version available:

https://aka.ms/AzureADPasswordProtectionAgentSoftwareVersions

Current version: 1.2.116.0
```

上記のイベントでは、新しいソフトウェアのバージョンは指定されていません。 その情報については、イベント メッセージのリンクに移動する必要があります。

注

上記のイベント メッセージの "autoupgrade" への参照にもかかわらず、DC エージェント ソフトウェアでは現在、この機能はサポートされていません。

### プロキシ サービスイベントログ

プロキシ サービスは、次のイベント ログに最小限のイベント セットを出力します。

`\Applications and Services Logs\Microsoft\AzureADPasswordProtection\ProxyService\Admin`

`\Applications and Services Logs\Microsoft\AzureADPasswordProtection\ProxyService\Operational`

`\Applications and Services Logs\Microsoft\AzureADPasswordProtection\ProxyService\Trace`

トレース ログは既定でオフになっていることに注意してください。

Warnung

有効にすると、トレース ログは大量のイベントを受信し、プロキシ ホストのパフォーマンスに影響を与える可能性があります。 そのため、このログは、問題がより詳細な調査を必要とする場合にのみ有効にし、その後、最小限の時間だけ有効にする必要があります。

イベントは、次の範囲を使用して、さまざまなプロキシ コンポーネントによってログに記録されます。

| コンポーネント | イベント ID の範囲 |
| --- | --- |
| プロキシ サービスのホスティング プロセス | 10000-19999 |
| プロキシ サービスのコア ビジネス ロジック | 20000-29999 |
| PowerShell コマンドレット | 30000-39999 |

### プロキシ サービス テキスト ログ

プロキシ サービスは、次のレジストリ値を設定することで、テキスト ログに書き込むよう構成できます。

HKLM\System\CurrentControlSet\Services\AzureADPasswordProtectionProxy\Parameters!EnableTextLogging = 1 (REG\_DWORD 値)

既定では、テキスト ログは無効になっています。 この値を変更して有効にするには、プロキシ サービスを再起動する必要があります。 有効にすると、プロキシ サービスは次の場所にあるログ ファイルに書き込みます。

`%ProgramFiles%\Azure AD Password Protection Proxy\Logs`

ヒント

テキスト ログは、トレース ログに記録できるのと同じデバッグ レベルのエントリを受け取りますが、通常は確認と分析が簡単な形式です。

Warnung

有効にすると、このログは大量のイベントを受信し、マシンのパフォーマンスに影響を与える可能性があります。 そのため、この拡張ログは、問題がより詳細な調査を必要とする場合にのみ有効にしてから、最小限の時間だけ有効にする必要があります。

### PowerShell コマンドレットのログ記録

状態の変化 (たとえば、Register-AzureADPasswordProtectionProxy) をもたらす PowerShell コマンドレットは、通常、結果イベントを操作ログに記録します。

さらに、ほとんどの Microsoft Entra Password Protection PowerShell コマンドレットは、次の場所にあるテキスト ログに書き込みます。

`%ProgramFiles%\Azure AD Password Protection Proxy\Logs`

コマンドレット エラーが発生し、原因や解決策が容易に明らかでない場合は、これらのテキスト ログも参照される可能性があります。

### プロキシの検出

`Get-AzureADPasswordProtectionProxy` コマンドレットは、ドメインまたはフォレストで実行されているさまざまな Microsoft Entra パスワード保護プロキシ サービスに関する基本情報を表示するために使用できます。 この情報は、実行中のプロキシ サービスによって登録された serviceConnectionPoint オブジェクトから取得されます。

このコマンドレットの出力例は次のとおりです。

```powershell
Get-AzureADPasswordProtectionProxy
ServerFQDN            : bplProxy.bplchild2.bplRootDomain.com
Domain                : bplchild2.bplRootDomain.com
Forest                : bplRootDomain.com
HeartbeatUTC          : 12/25/2018 6:35:02 AM
```

さまざまなプロパティは、各プロキシ サービスによって約 1 時間ごとに更新されます。 データは引き続き Active Directory レプリケーションの待機時間の影響を受けます。

コマンドレットのクエリのスコープは、–Forest パラメーターまたは –Domain パラメーターを使用して影響を受ける可能性があります。

HeartbeatUTC 値が古くなった場合は、そのマシン上の Microsoft Entra パスワード保護プロキシが実行されていないか、アンインストールされている現象である可能性があります。

### 使用可能なプロキシ エージェントの新しいバージョン

プロキシ サービスは、新しいバージョンのプロキシ ソフトウェアが使用可能であることを検出すると、次に例を示す 20002 警告イベントを操作ログに記録します。

```text
An update for Azure AD Password Protection Proxy is available.

If autoupgrade is enabled, this message may be ignored.

If autoupgrade is disabled, refer to the following link for the latest version available:

https://aka.ms/AzureADPasswordProtectionAgentSoftwareVersions

Current version: 1.2.116.0
.
```

上記のイベントでは、新しいソフトウェアのバージョンは指定されていません。 その情報については、イベント メッセージのリンクに移動する必要があります。

このイベントは、プロキシ エージェントが自動アップグレードを有効にして構成されている場合でも生成されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-password-ban-bad-on-premises-operations"} -->
## オンプレミスの Microsoft Entra パスワード保護を有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-operations
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: オンプレミスの Active Directory Domain Services 環境で Microsoft Entra Password Protection を有効にする方法について説明します

多くの場合、ユーザーは、学校、スポーツ チーム、有名人物などの一般的なローカル語を使用するパスワードを作成します。 これらのパスワードは推測が簡単で、辞書ベースの攻撃に対して脆弱です。 組織で強力なパスワードを適用するために、Microsoft Entra Password Protection には、グローバルおよびカスタムの禁止パスワード リストが用意されています。 これらの禁止パスワード リストに一致するものがある場合、パスワード変更要求は失敗します。

オンプレミスの Active Directory Domain Services (AD DS) 環境を保護するには、オンプレミス DC と連携するように Microsoft Entra Password Protection をインストールして構成します。 この記事では、オンプレミス環境で Microsoft Entra パスワード保護を有効にする方法について説明します。

オンプレミス環境での Microsoft Entra パスワード保護の動作の詳細については、「[Windows Server Active Directory](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-on-premises)に Microsoft Entra パスワード保護を適用する方法」を参照してください。

### 開始する前に

この記事では、オンプレミス環境で Microsoft Entra パスワード保護を有効にする方法について説明します。 この記事を完了する前に、オンプレミスの AD DS 環境に [Microsoft Entra パスワード保護プロキシ サービスと DC エージェントをインストールして登録します](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-deploy)。

### オンプレミスのパスワード保護を有効にする

1. 少なくとも [認証管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) にサインインします。
2. **Entra ID**&gt;**認証方法**&gt;**パスワード保護**に移動します。
3. **Windows Server Active Directory** でパスワード保護を有効にするオプションを *[はい]*に設定します。

    この設定を [なし] に設定すると、展開されているすべての Microsoft Entra Password Protection DC エージェントが休止モードに移行し、すべてのパスワードが as-is受け入れられます。 検証アクティビティは実行されず、監査イベントは生成されません。
4. 最初に、**モード** を *監査*に設定することをお勧めします。 この機能と組織内のユーザーへの影響に慣れた後は、**モードの** を *強制*に切り替えることができます。 詳細については、操作モードの次のセクションを参照してください。
5. 準備ができたら、**[保存]** を選択します。

    [Image: Microsoft Entra 管理センターの [認証方法] でオンプレミスのパスワード保護を有効にする]

### 操作モード

オンプレミスの Microsoft Entra パスワード保護を有効にする場合は、*監査モード* または *強制モード* を使用できます。 初期デプロイとテストは常に監査モードで開始することをお勧めします。 その後、イベント ログのエントリを監視して、*モードを強制* 有効にすると、既存の運用プロセスが妨げられるかどうかを予測する必要があります。

#### 監査モード

*監査* モードは、"what if" モードでソフトウェアを実行する方法として意図されています。 各 Microsoft Entra Password Protection DC エージェント サービスは、現在アクティブなポリシーに従って受信パスワードを評価します。

現在のポリシーが監査モードに構成されている場合、"無効" のパスワードはイベント ログ メッセージになりますが、処理および更新されます。 この動作は、監査モードと強制モードの唯一の違いです。 その他の操作はすべて同じように実行されます。

#### 強制モード

*強制* モードは、最終的な構成として使用されます。 監査モードの場合と同様に、各 Microsoft Entra Password Protection DC エージェント サービスは、現在アクティブなポリシーに従って受信パスワードを評価します。 ただし、強制モードが有効になっている場合、ポリシーに従って安全でないと見なされるパスワードは拒否されます。

Microsoft Entra Password Protection DC エージェントによってパスワードが強制モードで拒否されると、エンド ユーザーは、従来のオンプレミスのパスワードの複雑さの適用によってパスワードが拒否されたかどうかを確認するのと同様のエラーが表示されます。 たとえば、ユーザーが Windows ログオンまたはパスワードの変更画面に次の従来のエラー メッセージを表示する場合があります。

*"パスワードを更新できません。 新しいパスワードに指定された値が、ドメインの長さ、複雑さ、または履歴の要件を満たしていません。*

このメッセージは、考えられるいくつかの結果の 1 つの例にすぎません。 具体的なエラー メッセージは、セキュリティで保護されていないパスワードを設定しようとしている実際のソフトウェアまたはシナリオによって異なる場合があります。

影響を受けるエンド ユーザーは、IT スタッフと協力して新しい要件を理解し、セキュリティで保護されたパスワードを選択する必要があります。

手記

Microsoft Entra Password Protection は、脆弱なパスワードが拒否されたときにクライアント コンピューターによって表示される特定のエラー メッセージを制御しません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-password-ban-bad-on-premises-troubleshoot"} -->
## オンプレミスの Microsoft Entra パスワード保護のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-troubleshoot
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: オンプレミスの Active Directory Domain Services 環境に対する Microsoft Entra パスワード保護のトラブルシューティング方法について説明します

Microsoft Entra Password Protection の展開後、トラブルシューティングが必要になる場合があります。 この記事では、いくつかの一般的なトラブルシューティング手順を理解するのに役立つ詳細について説明します。

### DC エージェントがディレクトリ内のプロキシを見つけることができません

この問題の主な症状は、DC エージェント管理者イベント ログの 30,017 イベントです。

この問題の通常の原因は、プロキシが登録されていないことです。 プロキシが登録されている場合、特定の DC エージェントがそのプロキシを確認できるようになるまで、AD レプリケーションの待機時間が原因で多少の遅延が発生する可能性があります。

### DC エージェントがプロキシと通信できない

この問題の主な症状は、DC エージェント管理者イベント ログの 30,018 イベントです。 この問題には、いくつかの原因が考えられます。

- DC エージェントは、登録済みプロキシへのネットワーク接続を許可しないネットワークの分離された部分に配置されます。 この問題は、他の DC エージェントが Azure からパスワード ポリシーをダウンロードするためにプロキシと通信できる限り、無害である可能性があります。 ダウンロードされると、これらのポリシーは、sysvol 共有内のポリシー ファイルのレプリケーションを介して分離 DC によって取得されます。
- プロキシ ホスト マシンが RPC エンドポイント マッパー エンドポイント (ポート 135) へのアクセスをブロックしている

    Microsoft Entra パスワード保護プロキシ インストーラーは、ポート 135 へのアクセスを許可する Windows ファイアウォール受信規則を自動的に作成します。 この規則が後で削除または無効になった場合、DC エージェントはプロキシ サービスと通信できません。 別のファイアウォール製品の代わりに組み込みの Windows ファイアウォールが無効になっている場合は、ポート 135 へのアクセスを許可するようにそのファイアウォールを構成する必要があります。
- プロキシ ホスト マシンが、プロキシ サービスによってリッスンされている RPC エンドポイント (動的または静的) へのアクセスをブロックしている

    Microsoft Entra パスワード保護プロキシ インストーラーは、Microsoft Entra パスワード保護プロキシ サービスによってリッスンされる受信ポートへのアクセスを許可する Windows ファイアウォール受信規則を自動的に作成します。 この規則が後で削除または無効になった場合、DC エージェントはプロキシ サービスと通信できません。 別のファイアウォール製品の代わりに組み込みの Windows ファイアウォールが無効になっている場合は、Microsoft Entra パスワード保護プロキシ サービスによってリッスンされる受信ポートへのアクセスを許可するように、そのファイアウォールを構成する必要があります。 プロキシ サービスが (`Set-AzureADPasswordProtectionProxyConfiguration` コマンドレットを使用して) 特定の静的 RPC ポートでリッスンするように構成されている場合は、この構成をより具体的にすることができます。
- ドメイン コントローラーがコンピューターにサインインできるようにプロキシ ホスト コンピューターが構成されていません。 この動作は、"ネットワークからこのコンピューターにアクセスする" ユーザー特権の割り当てによって制御されます。 フォレスト内のすべてのドメイン内のすべてのドメイン コントローラーに、この特権を付与する必要があります。 この設定は、多くの場合、大規模なネットワーク強化作業の一環として制約されます。

### プロキシ サービスが Azure と通信できない

1. プロキシ マシンが、展開要件に記載されているエンドポイントに接続されていることを確認します。
2. フォレストとすべてのプロキシ サーバーが同じ Azure テナントに対して登録されていることを確認します。

    この要件を確認するには、`Get-AzureADPasswordProtectionProxy` コマンドレットおよび `Get-AzureADPasswordProtectionDCAgent` PowerShell コマンドレットを実行し、返された各アイテムの `AzureTenant` プロパティを比較します。 正しい操作を行うには、報告されるテナント名がすべての DC エージェントとプロキシ サーバーで同じである必要があります。

    Azure テナントの登録の不一致条件が存在する場合は、必要に応じて `Register-AzureADPasswordProtectionProxy` コマンドレットまたは `Register-AzureADPasswordProtectionForest` PowerShell コマンドレットを実行し、すべての登録に同じ Azure テナントの資格情報を使用することで、この問題を解決できます。

### DC エージェントがパスワード ポリシー ファイルを暗号化または暗号化解除できない

Microsoft Entra Password Protection は、Microsoft キー配布サービスによって提供される暗号化と暗号化解除の機能に重大な依存関係があります。 暗号化または復号化の失敗は、さまざまな症状で現れ、いくつかの潜在的な原因を伴う可能性があります。

- KDS サービスが有効になっており、ドメイン内のすべての Windows Server 2012 以降のドメイン コントローラーで機能していることを確認します。

    既定では、KDS サービスのサービス開始モードは手動 (トリガー開始) として構成されます。 この構成は、クライアントがサービスを初めて使用しようとしたときに、オンデマンドで開始されることを意味します。 この既定のサービス開始モードは、Microsoft Entra Password Protection が機能するために許容されます。

    KDS サービス開始モードが無効に構成されている場合は、Microsoft Entra パスワード保護が正常に機能する前に、この構成を修正する必要があります。

    この問題の簡単なテストは、サービス管理 MMC コンソールを使用するか、他の管理ツールを使用して KDS サービスを手動で開始することです (たとえば、コマンド プロンプト コンソールから "net start kdssvc" を実行します)。 KDS サービスは正常に開始され、実行を続ける必要があります。

    KDS サービスを開始できない最も一般的な根本原因は、Active Directory ドメイン コントローラー オブジェクトが既定のドメイン コントローラー OU の外部に配置されていることです。 この構成は KDS サービスでサポートされておらず、Microsoft Entra パスワード保護によって課される制限ではありません。 この条件の修正は、ドメイン コントローラー オブジェクトを既定のドメイン コントローラー OU の下の場所に移動することです。
- 互換性のない KDS 暗号化バッファー形式が Windows Server 2012 R2 から Windows Server 2016 に変更される

    KDS で暗号化されたバッファーの形式を変更する KDS セキュリティ修正プログラムが Windows Server 2016 で導入されました。 これらのバッファーは、Windows Server 2012 および Windows Server 2012 R2 で復号化に失敗することがあります。 逆方向でも問題ありません。 Windows Server 2012 および Windows Server 2012 R2 で KDS で暗号化されたバッファーは、常に Windows Server 2016 以降で正常に暗号化解除されます。 Active Directory ドメイン内のドメイン コントローラーでこれらのオペレーティング システムの組み合わせが実行されている場合、Microsoft Entra Password Protection の復号化エラーが報告されることがあります。 セキュリティ修正プログラムの性質上、これらのエラーのタイミングや症状を正確に予測することはできません。 また、どのドメイン コントローラーの Microsoft Entra Password Protection DC エージェントが特定の時点でデータを暗号化するかは非決定的です。

    Active Directory ドメインでこれらの互換性のないオペレーティング システムを組み合わせて実行しない以外に、この問題の回避策はありません。 つまり、Windows Server 2012 および Windows Server 2012 R2 ドメイン コントローラーのみを実行するか、Windows Server 2016 以降のドメイン コントローラーのみを実行する必要があります。

### DCのエージェントは、その森林が登録されていないと考えている

この問題の症状は、30,016 件のイベントが DC Agent\Admin チャネルに記録され、その一部が次のように表示されます。

```text
The forest hasn't been registered with Azure. Password policies can't be downloaded from Azure unless this is corrected.
```

この問題には 2 つの原因が考えられます。

- 森が登録されていません。 この問題を解決するには、[のデプロイ要件に関する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-deploy)の説明に従って、Register-AzureADPasswordProtectionForest コマンドを実行します。
- フォレストは登録されていますが、DC エージェントはフォレスト登録データの暗号化を解除できません。 このケースの根本原因は、[DC エージェントがパスワード ポリシー ファイル](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-troubleshoot#dc-agent-is-unable-to-encrypt-or-decrypt-password-policy-files)暗号化または暗号化解除できない問題 2 と同じです。 この理論を確認する簡単な方法は、Windows Server 2012 または Windows Server 2012R2 ドメイン コントローラーで実行されている DC エージェントでのみこのエラーが表示されますが、Windows Server 2016 以降のドメイン コントローラーで実行されている DC エージェントは問題ありません。 回避策は同じです。すべてのドメイン コントローラーを Windows Server 2016 以降にアップグレードします。

### 脆弱なパスワードは受け入れられているが、受け入れてはならない

この問題には、いくつかの原因が考えられる場合があります。

- DC エージェントが、期限切れのパブリック プレビュー ソフトウェア バージョンを実行している。 「[パブリック プレビュー DC エージェント ソフトウェアの有効期限切れ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-troubleshoot#public-preview-dc-agent-software-has-expired)」を参照してください。
- DC エージェントがポリシーをダウンロードできないか、既存のポリシーの暗号化を解除できません。 前の記事で考えられる原因を確認します。
- パスワード ポリシーの [強制] モードは引き続き [監査] に設定されています。 この構成が有効な場合は、Microsoft Entra パスワード保護ポータルを使用して適用するように再構成します。 詳細については「[動作モード](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-operations#modes-of-operation)」を参照してください。
- パスワード ポリシーが無効になっています。 この構成が有効な場合は、Microsoft Entra パスワード保護ポータルを使用して有効にするように再構成します。 詳細については「[動作モード](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-operations#modes-of-operation)」を参照してください。
- ドメイン内のすべてのドメイン コントローラーに DC エージェント ソフトウェアをインストールしていません。 このような状況では、リモート Windows クライアントがパスワード変更操作中に特定のドメイン コントローラーをターゲットにすることを確認することは困難です。 DC エージェント ソフトウェアがインストールされている特定の DC を正常にターゲットにしたと思われる場合は、DC エージェント管理者イベント ログを再確認することで確認できます。結果に関係なく、パスワード検証の結果を文書化するイベントが少なくとも 1 つあります。 パスワードが変更されたユーザーにイベントが存在しない場合、パスワードの変更は別のドメイン コントローラーによって処理された可能性があります。

    別のテストとして、DC エージェント ソフトウェアがインストールされている DC に直接ログインしているときにパスワードを設定または変更してみてください。 この手法は、運用環境の Active Directory ドメインには推奨されません。

    DC エージェント ソフトウェアの増分展開は、これらの制限に従ってサポートされますが、できるだけ早く DC エージェント ソフトウェアをドメイン内のすべてのドメイン コントローラーにインストールすることを強くお勧めします。
- パスワード検証アルゴリズムは、実際には想定どおりに動作している可能性があります。 パスワードの評価方法を参照してください。

### 弱い DSRM パスワードを設定できない Ntdsutil.exe

Active Directory は、新しい Directory Services 修復モードのパスワードを常に検証して、ドメインのパスワードの複雑さの要件を満たしていることを確認します。この検証では、Microsoft Entra Password Protection などのパスワード フィルター dll も呼び出されます。 新しい DSRM パスワードが拒否された場合、次のエラー メッセージが表示されます。

```text
C:\>ntdsutil.exe
ntdsutil: set dsrm password
Reset DSRM Administrator Password: reset password on server null
Please type password for DS Restore Mode Administrator Account: ********
Please confirm new password: ********
Setting password failed.
        WIN32 Error Code: 0xa91
        Error Message: Password doesn't meet the requirements of the filter dll's
```

Microsoft Entra Password Protection が Active Directory DSRM パスワードのパスワード検証イベント ログ イベントをログに記録する場合、イベント ログ メッセージにユーザー名が含まれないことが予想されます。 この動作は、DSRM アカウントが実際の Active Directory ドメインの一部ではないローカル アカウントであるために発生します。

### DSRM パスワードが弱いため、ドメイン コントローラー レプリカの昇格が失敗する

DC 昇格プロセス中に、新しいディレクトリ サービス修復モードのパスワードが、検証のためにドメイン内の既存の DC に送信されます。 新しい DSRM パスワードが拒否された場合、次のエラー メッセージが表示されます。

```powershell
Install-ADDSDomainController : Verification of prerequisites for Domain Controller promotion failed. The Directory Services Restore Mode password doesn't meet a requirement of the password filter(s). Supply a suitable password.
```

前の問題と同様に、Microsoft Entra Password Protection のパスワード検証結果イベントには、このシナリオの空のユーザー名が含まれます。

### ローカル管理者パスワードが弱いため、ドメイン コントローラーの降格が失敗する

DC エージェント ソフトウェアをまだ実行しているドメイン コントローラーを降格することがサポートされています。 ただし、DC エージェント ソフトウェアは降格手順中に現在のパスワード ポリシーを引き続き適用することを管理者は認識する必要があります。 新しいローカル管理者アカウントのパスワード (降格操作の一部として指定) は、他のパスワードと同様に検証されます。 DC 降格手順の一環として、ローカル管理者アカウントに対してセキュリティで保護されたパスワードを選択することをお勧めします。

降格が成功し、ドメイン コントローラーが再起動され、通常のメンバー サーバーとして再び実行されると、DC エージェント ソフトウェアはパッシブ モードで実行に戻ります。 その後、いつでもアンインストールできます。

### ディレクトリ サービス修復モードでの起動

ドメイン コントローラーがディレクトリ サービス修復モードで起動された場合、DC エージェントのパスワード フィルター dll はこの条件を検出し、現在アクティブなポリシー構成に関係なく、すべてのパスワード検証または強制アクティビティが無効になります。 DC エージェント パスワード フィルター dll は、管理者イベント ログに 10023 警告イベントをログに記録します。次に例を示します。

```text
The password filter dll is loaded but the machine appears to be a domain controller that is booted into Directory Services Repair Mode. All password change and set requests are automatically approved. No further messages are logged until after the next reboot.
```

### パブリック プレビュー DC エージェント ソフトウェアの有効期限が切れています

Microsoft Entra Password Protection パブリック プレビュー期間中、DC エージェント ソフトウェアは、次の日付にパスワード検証要求の処理を停止するようにハードコーディングされました。

- バージョン 1.2.65.0 は、2019 年 9 月 1 日にパスワード検証要求の処理を停止しました。
- バージョン 1.2.25.0 以前では、2019 年 7 月 1 日にパスワード検証要求の処理が停止されました。

期限が近づくと、時間制限付き DC エージェント のすべてのバージョンでは、起動時に次のような 10021 イベントが DC エージェント管理者イベント ログに出力されます。

```text
The password filter dll has successfully loaded and initialized.

The allowable trial period is nearing expiration. Once the trial period has expired, the password filter dll no longer processes passwords. Please contact Microsoft for a newer supported version of the software.

Expiration date:  9/01/2019 0:00:00 AM

This message won't be repeated until the next reboot.
```

期限が過ぎると、時間制限付き DC エージェントのすべてのバージョンでは、起動時に DC エージェント管理者イベント ログに次のような 10022 イベントが出力されます。

```text
The password filter dll is loaded but the allowable trial period has expired. All password change and set requests are automatically approved. Please contact Microsoft for a newer supported version of the software.

No further messages are logged until after the next reboot.
```

期限は初回起動時にのみチェックされるため、カレンダーの期限が過ぎるまでこれらのイベントが表示されない場合があります。 期限が認識されると、すべてのパスワード以外のドメイン コントローラーまたは大規模な環境への悪影響は自動的に承認されません。

重要

Microsoft では、期限切れのパブリック プレビュー DC エージェントを最新バージョンに直ちにアップグレードすることをお勧めします。

アップグレードが必要な環境内の DC エージェントを簡単に検出するには、`Get-AzureADPasswordProtectionDCAgent` コマンドレットを実行します。次に例を示します。

```powershell
PS C:\> Get-AzureADPasswordProtectionDCAgent

ServerFQDN            : bpl1.bpl.com
SoftwareVersion       : 1.2.125.0
Domain                : bpl.com
Forest                : bpl.com
PasswordPolicyDateUTC : 8/1/2019 9:18:05 PM
HeartbeatUTC          : 8/1/2019 10:00:00 PM
AzureTenant           : bpltest.onmicrosoft.com
```

この記事では、SoftwareVersion フィールドは明らかに確認する重要なプロパティです。 PowerShell フィルターを使用して、必要なベースライン バージョン以上の DC エージェントをフィルターで除外することもできます。次に例を示します。

```powershell
PS C:\> $LatestAzureADPasswordProtectionVersion = "1.2.125.0"
PS C:\> Get-AzureADPasswordProtectionDCAgent | Where-Object {$_.SoftwareVersion -lt $LatestAzureADPasswordProtectionVersion}
```

Microsoft Entra パスワード保護プロキシ ソフトウェアは、どのバージョンでも時間制限はありません。 MICROSOFT では、DC エージェントとプロキシ エージェントの両方を、リリース時に最新バージョンにアップグレードすることをお勧めします。 `Get-AzureADPasswordProtectionProxy` コマンドレットは、DC エージェントの上記の例と同様に、アップグレードを必要とするプロキシ エージェントを検索するために使用できます。

特定のアップグレード手順の詳細については、「DC エージェント のアップグレードとプロキシ サービス のアップグレードの 」を参照してください。

### 緊急修復

DC エージェント サービスが問題の原因となっている状況が発生した場合は、DC エージェント サービスが直ちにシャットダウンされる可能性があります。 DC エージェントのパスワード フィルター dll は、実行されていないサービスの呼び出しを試み、警告イベント (10012、10013) をログに記録しますが、その間にすべての受信パスワードが受け入れられます。 DC エージェント サービスは、必要に応じて、スタートアップの種類が "無効" の Windows サービス コントロール マネージャーを介して構成することもできます。

もう 1 つの修復方法は、Microsoft Entra パスワード保護ポータルで [有効] モードを [いいえ] に設定することです。 更新されたポリシーがダウンロードされると、各 DC エージェント サービスは休止モードに移行し、すべてのパスワードが as-is受け入れられます。 詳細については「[動作モード](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-operations#modes-of-operation)」を参照してください。

### 削除

Microsoft Entra パスワード保護ソフトウェアをアンインストールし、ドメインとフォレストから関連するすべての状態をクリーンアップする場合は、次の手順を使用してこのタスクを実行できます。

重要

これらの手順を順番に実行することが重要です。 プロキシ サービスのインスタンスが実行したままになっている場合は、その serviceConnectionPoint オブジェクトを定期的に再作成します。 DC エージェント サービスのインスタンスが実行したままの場合は、serviceConnectionPoint オブジェクトと sysvol 状態が定期的に再作成されます。

1. すべてのマシンからプロキシ ソフトウェアをアンインストールします。 この手順では、再起動する**必要はありません**。
2. すべてのドメイン コントローラーから DC エージェント ソフトウェアをアンインストールします。 手順 **は再起動** が必要です。
3. 各ドメインの名前付けコンテキスト内のすべてのプロキシ サービス接続ポイントを手動で削除します。 これらのオブジェクトの場所は、次の Active Directory PowerShell コマンドを使用して検出できます。

    ```powershell
    $scp = "serviceConnectionPoint"
    $keywords = "{ebefb703-6113-413d-9167-9f8dd4d24468}*"
    Get-ADObject -SearchScope Subtree -Filter { objectClass -eq $scp -and keywords -like $keywords }
    ```

    $keywords変数値の末尾にあるアスタリスク ("\*") は省略しないでください。

    `Get-ADObject` コマンドで見つかったオブジェクトは、`Remove-ADObject`にパイプ処理したり、手動で削除したりすることができます。
4. 各ドメインの名前付けコンテキスト内のすべての DC エージェント接続ポイントを手動で削除します。 ソフトウェアの展開の広さに応じて、フォレスト内のドメイン コントローラーごとにこれらのオブジェクトが 1 つ存在する場合があります。 そのオブジェクトの場所は、次の Active Directory PowerShell コマンドを使用して検出できます。

    ```powershell
    $scp = "serviceConnectionPoint"
    $keywords = "{2bac71e6-a293-4d5b-ba3b-50b995237946}*"
    Get-ADObject -SearchScope Subtree -Filter { objectClass -eq $scp -and keywords -like $keywords }
    ```

    `Get-ADObject` コマンドで見つかったオブジェクトは、`Remove-ADObject`にパイプ処理したり、手動で削除したりすることができます。

    $keywords変数値の末尾にあるアスタリスク ("\*") は省略しないでください。
5. フォレスト レベルの構成状態を手動で削除します。 フォレスト構成の状態は、Active Directory 構成の名前付けコンテキストのコンテナーで維持されます。 次のように検出および削除できます。

    ```powershell
    $passwordProtectionConfigContainer = "CN=Azure AD Password Protection,CN=Services," + (Get-ADRootDSE).configurationNamingContext
    Remove-ADObject -Recursive $passwordProtectionConfigContainer
    ```
6. 次のフォルダーとそのすべての内容を手動で削除して、sysvol 関連のすべての状態を手動で削除します。

    `\\<domain>\sysvol\<domain fqdn>\AzureADPasswordProtection`

    必要に応じて、このパスは特定のドメイン コントローラーでローカルにアクセスすることもできます。既定の場所は、次のパスのようになります。

    `%windir%\sysvol\domain\Policies\AzureADPasswordProtection`

    sysvol 共有が既定以外の場所に構成されている場合、このパスは異なります。

### PowerShell コマンドレットを使用した正常性テスト

AzureADPasswordProtection PowerShell モジュールには、ソフトウェアがインストールされ動作していることを基本的に検証する 2 つの正常性関連のコマンドレットが含まれています。 新しいデプロイを設定した後、その後、問題が調査されている場合は、定期的にこれらのコマンドレットを実行することをお勧めします。

個々の正常性テストは、基本的な成功または失敗の結果に加えて、失敗した場合はオプションのメッセージを返します。 エラーの原因が明確でない場合は、エラーを説明する可能性のあるエラー イベント ログ メッセージを探します。 テキスト ログ メッセージを有効にすると、便利な場合もあります。 詳細については、「[Microsoft Entra パスワード保護](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-monitor)を監視する」を参照してください。

### プロキシの正常性テスト

Test-AzureADPasswordProtectionProxyHealth コマンドレットは、個別に実行できる 2 つの正常性テストをサポートしています。 3 番目のモードでは、パラメーター入力を必要としないすべてのテストを実行できます。

#### プロキシ登録の検証

このテストでは、プロキシ エージェントが Azure に適切に登録され、Azure に対して認証できることを確認します。 成功した実行は次のようになります。

```powershell
PS C:\> Test-AzureADPasswordProtectionProxyHealth -VerifyProxyRegistration

DiagnosticName          Result AdditionalInfo
--------------          ------ --------------
VerifyProxyRegistration Passed
```

エラーが検出された場合、テストは失敗した結果とオプションのエラー メッセージを返します。 考えられる 1 つのエラーの例を次に示します。

```powershell
PS C:\> Test-AzureADPasswordProtectionProxyHealth -VerifyProxyRegistration

DiagnosticName          Result AdditionalInfo
--------------          ------ --------------
VerifyProxyRegistration Failed No proxy certificates were found - please run the Register-AzureADPasswordProtectionProxy cmdlet to register the proxy.
```

#### エンドツーエンドの Azure 接続のプロキシ検証

このテストは、-VerifyProxyRegistration テストのスーパーセットです。 プロキシ エージェントが Azure に適切に登録され、Azure に対して認証できる必要があります。最後に、メッセージを Azure に正常に送信できることを確認し、エンドツーエンドの完全な通信が機能していることを確認する必要があります。

成功した実行は次のようになります。

```powershell
PS C:\> Test-AzureADPasswordProtectionProxyHealth -VerifyAzureConnectivity

DiagnosticName          Result AdditionalInfo
--------------          ------ --------------
VerifyAzureConnectivity Passed
```

#### すべてのテストのプロキシ検証

このモードでは、パラメーター入力を必要としないコマンドレットでサポートされているすべてのテストを一括実行できます。 成功した実行は次のようになります。

```powershell
PS C:\> Test-AzureADPasswordProtectionProxyHealth -TestAll

DiagnosticName          Result AdditionalInfo
--------------          ------ --------------
VerifyTLSConfiguration  Passed
VerifyProxyRegistration Passed
VerifyAzureConnectivity Passed
```

### DC エージェントのヘルスチェック

Test-AzureADPasswordProtectionDCAgentHealth コマンドレットは、個別に実行できるいくつかの正常性テストをサポートしています。 3 番目のモードでは、パラメーター入力を必要としないすべてのテストを実行できます。

#### 基本的な DC エージェントのヘルステスト

次のテストはすべて個別に実行でき、パラメーターは受け入れられません。 各テストの簡単な説明を次の表に示します。

| DC エージェントの正常性テスト | 説明 |
| --- | --- |
| -VerifyPasswordFilterDll | パスワード フィルター dll が現在読み込まれており、DC エージェント サービスを呼び出すことができることを確認します |
| -VerifyForestRegistration | フォレストが現在登録されていることを確認します。 |
| -VerifyEncryptionDecryption | Microsoft KDS サービスを使用して、基本的な暗号化と暗号化解除が機能していることを確認します |
| -VerifyDomainIsUsingDFSR | 現在のドメインが sysvol レプリケーションに DFSR を使用していることを確認します |
| -VerifyAzureConnectivity | Azure とのエンドツーエンドの通信が、使用可能なプロキシを使用して動作していることを確認します |

次に示すのは、-VerifyPasswordFilterDll テストの成功の例であり、他の成功したテストは次のようになります。

```powershell
PS C:\> Test-AzureADPasswordProtectionDCAgentHealth -VerifyPasswordFilterDll

DiagnosticName          Result AdditionalInfo
--------------          ------ --------------
VerifyPasswordFilterDll Passed
```

#### すべてのテストの DC エージェント検証

このモードでは、パラメーター入力を必要としないコマンドレットでサポートされているすべてのテストを一括実行できます。 成功した実行は次のようになります。

```powershell
PS C:\> Test-AzureADPasswordProtectionDCAgentHealth -TestAll

DiagnosticName             Result AdditionalInfo
--------------             ------ --------------
VerifyPasswordFilterDll    Passed
VerifyForestRegistration   Passed
VerifyEncryptionDecryption Passed
VerifyDomainIsUsingDFSR    Passed
VerifyAzureConnectivity    Passed
```

#### 特定のプロキシ サーバーを使用した接続テスト

多くのトラブルシューティングの状況では、DC エージェントとプロキシ間のネットワーク接続の調査が含まれます。 このような問題に特に焦点を当てるには、2 つの正常性テストを使用できます。 これらのテストでは、特定のプロキシ サーバーを指定する必要があります。

##### DC エージェントと特定のプロキシ間の接続の確認

このテストでは、DC エージェントからプロキシへの最初の通信区間での接続を検証します。 プロキシが呼び出しを受信することを確認しますが、Azure との通信は関係しません。 成功した実行は次のようになります。

```powershell
PS C:\> Test-AzureADPasswordProtectionDCAgentHealth -VerifyProxyConnectivity bpl2.bpl.com

DiagnosticName          Result AdditionalInfo
--------------          ------ --------------
VerifyProxyConnectivity Passed
```

ターゲット サーバーで実行されているプロキシ サービスが停止するエラー状態の例を次に示します。

```powershell
PS C:\> Test-AzureADPasswordProtectionDCAgentHealth -VerifyProxyConnectivity bpl2.bpl.com

DiagnosticName          Result AdditionalInfo
--------------          ------ --------------
VerifyProxyConnectivity Failed The RPC endpoint mapper on the specified proxy returned no results; please check that the proxy service is running on that server.
```

##### DC エージェントと Azure の間の接続の確認 (特定のプロキシを使用)

このテストでは、特定のプロキシを使用して、DC エージェントと Azure の間の完全なエンド ツー エンド接続を検証します。 成功した実行は次のようになります。

```powershell
PS C:\> Test-AzureADPasswordProtectionDCAgentHealth -VerifyAzureConnectivityViaSpecificProxy bpl2.bpl.com

DiagnosticName                          Result AdditionalInfo
--------------                          ------ --------------
VerifyAzureConnectivityViaSpecificProxy Passed
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-password-smart-lockout"} -->
## スマート ロックアウトを使用した攻撃の防止 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout
- Service: entra-id / authentication
- Article date: 2026-06-03
- Summary: Microsoft Entra のスマート ロックアウトを使用して、ユーザー パスワードを推測するブルート フォース攻撃から組織を守る方法について説明します。

スマート ロックアウトは、組織のユーザーのパスワードを推測したり、ブルート フォース方法を使用して侵入しようとする悪意のあるユーザーのロックアウトを支援します。 スマート ロックアウトでは、有効なユーザーからのサインインを認識し、攻撃者やその他の不明なソースからのサインインとは異なる方法で処理できます。 ユーザーがアカウントにアクセスし続け、生産性を維持している間、攻撃者はロックアウトされます。

### スマート ロックアウトのしくみ

既定のスマート ロックアウトでは、以下の後にアカウントがロックされ、サインインできなくなります。

- 21Vianet テナントによって運営されている Azure Public と Microsoft Azure で試行に 10 回失敗した
- Azure 米国政府機関 テナントに対して失敗した 3 回の試行

以降のサインイン試行が失敗するたびに、アカウントは再度ロックされます。 ロックアウト期間は最初は 1 分で、以降の試行ではこれより長くなります。 攻撃者がこの動作を回避できる方法を最小限にするために、サインイン試行の失敗後のロックアウト期間がどの程度延長されるかは開示されていません。

スマート ロックアウトでは、直近 3 つの無効なパスワード ハッシュを追跡して、同じパスワードに対するロックアウト カウンターの増分を回避します。 同じ無効なパスワードが複数回入力された場合、この動作によってアカウントがロック アウトされることはありません。

注

クラウドではなくオンプレミスで認証が行われるため、パススルー認証が有効になっているお客様はハッシュ追跡機能を使用できません。

Active Directory フェデレーション サービス (AD FS) 2016 と AD FS 2019 を使用するフェデレーション展開では、 [AD FS エクストラネット ロックアウトとエクストラネット スマート ロックアウト](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configure-ad-fs-extranet-smart-lockout-protection)を使用して同様の利点を実現できます。 [マネージド認証](https://www.microsoft.com/security/business/identity-access/upgrade-adfs)に移行することをお勧めします。

スマート ロックアウトは、セキュリティと使いやすさの適切な組み合わせを提供するこれらの既定の設定を使用して、すべてのMicrosoft Entraのお客様に対して常にオンになっています。 組織に固有の値を使用してスマート ロックアウト設定をカスタマイズするには、ユーザー Microsoft Entra ID P1 以上のライセンスが必要です。

スマート ロックアウトを使用しても、正規のユーザーが絶対にロックアウトされないという保証はありません。スマート ロックアウトによってユーザー アカウントがロックされた場合、Microsoft では可能な限り正規ユーザーをロックアウトしないよう試みます。 ロックアウト サービスでは、悪意のあるアクターが正規のユーザー アカウントへのアクセスを確実に阻止するよう試みます。 次の考慮事項が適用されます。

- Microsoft Entra データ センター全体のロックアウト状態が同期されます。 ただし、アカウントがロックアウトされるまでに許可された失敗したサインイン試行の合計数は、構成されたロックアウトのしきい値と若干異なります。 アカウントがロックアウトされると、すべての Microsoft Entra データ センターのあらゆる場所でロックアウトされます。
- スマート ロックアウトでは、悪意のあるアクターと正規のユーザーを区別するために、"既知の場所" と "未知の場所" を使用します。 未知および既知の場所の両方に、個別のロックアウト カウンターが設定されます。

    システムがユーザーのサインインを未知の場所からロックアウトしないようにするには、正しいパスワードを使用してロックアウトされないようにし、未知の場所からの以前のロックアウト試行回数が少ないようにする必要があります。 ユーザーが未知の場所からロックアウトされている場合は、SSPR を考慮してロックアウト カウンターをリセットする必要があります。

- アカウントのロックアウト後、ユーザーはセルフサービス パスワード リセット (SSPR) を開始して、もう一度サインインできます。 SSPR を使用すると、ユーザーはヘルプ デスクや管理者の支援を受けずに、自分のパスワードをリセットまたは変更できます。 ユーザーが SSPR 中に **パスワードを忘れた** 場合、ロックアウトの期間は 0 秒にリセットされるため、ユーザーはロックアウト期間の有効期限が切れるのを待つ必要はありません。 ユーザーが SSPR 中に **自分のパスワード** がわかっていると選択した場合、ロックアウト タイマーは続行され、ロックアウトの期間はリセットされません。 その場合、アクセスを回復するには、ユーザーは自分のパスワードを変更するか、構成されたロックアウト期間の有効期限が切れるまで待つ必要があります。

スマート ロックアウトを、パスワード ハッシュ同期またはパススルー認証を使用してオンプレミスのActive Directory Domain Services (AD DS) アカウントが攻撃者によってロックアウトされないように保護するハイブリッド展開と統合できます。 Microsoft Entra IDでスマート ロックアウト ポリシーを適切に設定することで、オンプレミスの AD DS に到達する前に攻撃を除外できます。

[パススルー認証](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta)を使用する場合は、次の考慮事項が適用されます。

- Microsoft Entra ロックアウトのしきい値は、AD DS アカウントのロックアウトしきい値より **小さく** する必要があります。 AD DS アカウント ロックアウトしきい値が Microsoft Entra のロックアウトしきい値より少なくとも 2 から 3 倍大きくなるように値を設定します。
- Microsoft Entra ロックアウト期間は、AD DS アカウントのロックアウト期間よりも **長く** する必要があります。 AD DS の期間は分単位で設定されますが、Microsoft Entra の期間は秒単位で設定されます。
    ヒント

    この構成により、Microsoft Entra スマート ロックアウトにより、Microsoft Entra アカウントに対する [パスワード スプレー攻撃](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks#password-spray) など、オンプレミスの AD DS アカウントがブルート フォース攻撃によってロックアウトされないようにします。

たとえば、Microsoft Entra のスマート ロックアウト期間が AD DS よりも高くなるようにしたい場合は、オンプレミス AD が 1 分 (60 秒) に設定されていても、Microsoft Entra は 120 秒 (2 分) にします。 Microsoft Entra ロックアウトしきい値を 10 にする場合は、オンプレミスの AD DS ロックアウトしきい値を 20 に設定します。

重要

管理者は、ユーザーのクラウド アカウントのロックを解除できます。スマート ロックアウトがロックアウトされた場合、ロックアウト期間の有効期限が切れるのを待たずにロック解除できます。 詳細については、「 [ユーザーのパスワードをリセットする」を](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-reset-password-azure-portal)参照してください。

### オンプレミス アカウントのロックアウト ポリシーを検証する

オンプレミス AD DS アカウントのロックアウト ポリシーを検証するには、ドメインに参加しているシステムから管理者特権で次の手順を実行します。

1. グループ ポリシー管理ツールを開きます。
2. **既定のドメイン** ポリシーなど、組織のアカウント ロックアウト ポリシーを含むグループ ポリシーを編集します。
3. **コンピューターの構成**&gt;**ポリシー**&gt;**Windows 設定**&gt;**セキュリティ設定**&gt;**アカウント ポリシー**&gt;**アカウント ロックアウト ポリシー**に移動します。
4. **アカウント ロックアウトのしきい値**を確認し、値**の後にアカウント ロックアウト カウンターをリセット**します。

[Image: オンプレミスの Active Directory アカウント ロックアウト ポリシーを変更する]

### Microsoft Entra スマート ロックアウト値の管理

組織の要件に基づいて、Microsoft Entra スマート ロックアウトの値をカスタマイズできます。 組織に固有の値を使用してスマート ロックアウト設定をカスタマイズするには、ユーザー Microsoft Entra ID P1 以上のライセンスが必要です。 スマート ロックアウト設定のカスタマイズは、21Vianet テナントが運用するMicrosoft Azureでは使用できません。

組織のスマート ロックアウト値を確認または編集するには、次の手順を実行します。

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **Entra ID**&gt;**認証方法**&gt;**パスワード保護**に移動します。
3. 最初のロックアウト前にアカウントで許可される失敗したサインインの数に基づいて、 **ロックアウトのしきい値** を設定します。

    既定値は、Azure パブリック テナントの場合は 10、Azure 米国政府機関 テナントの場合は 3 です。
4. ロックアウト期間を **秒単位で**、各ロックアウトの長さ (秒) に設定します。

    既定値は 60 秒 (1 分) です。

注

ロックアウト期間が過ぎた後の最初のサインインも失敗した場合、アカウントは再びロックアウトされます。 アカウントが繰り返しロックされた場合は、ロックアウト時間が長くなります。

[Image: Microsoft Entra 管理センターで Microsoft Entra スマート ロックアウト ポリシーをカスタマイズする方法を示すスクリーンショット。]

### スマート ロックアウトのテスト

スマート ロックアウトのしきい値がトリガーされると、アカウントがロックされている間に次のメッセージが表示されます。

*ご使用のアカウントは、不正使用を防ぐために一時的にロックされています。 後でもう一度お試しください。問題が解決しない場合は管理者にお問い合わせください。*

スマート ロックアウトをテストする際、Microsoft Entra 認証サービスの地理的分散および負荷分散の性質により、サインイン要求はさまざまなデータセンターによって処理される可能性があります。

スマート ロックアウトでは、直近 3 つの無効なパスワード ハッシュを追跡して、同じパスワードに対するロックアウト カウンターの増分を回避します。 同じ無効なパスワードが複数回入力された場合、この動作によってアカウントがロック アウトされることはありません。

### 既定の保護

スマート ロックアウトに加えて、Microsoft Entra ID ではまた、トラフィックを含むシグナルを分析し、異常な動作を識別することで攻撃を防ぎます。 Microsoft Entra ID は、これらの悪意のあるサインインを既定でブロックし、パスワードの有効性に関係なく [、AADSTS50053 - IdsLocked エラー コード](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes)を返します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-registration-mfa-sspr-combined"} -->
## セキュリティ情報の統合登録 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-registration-mfa-sspr-combined
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: 多要素認証とセルフサービス パスワード リセット登録を組み合わせてMicrosoft Entraユーザー エクスペリエンスを簡素化する方法について説明します。

統合された登録が導入される前に、ユーザーは、Microsoft Entra多要素認証 (MFA) とセルフサービス パスワード リセット (SSPR) の認証方法を個別に登録していました。 ユーザーは、Microsoft Entra MFA と SSPR に同様の方法が使用されたと混乱しましたが、両方の機能に登録する必要がありました。 これで、統合された登録により、ユーザーは 1 回登録して、Microsoft Entra MFA と SSPR の両方の利点を得ることができます。

新しいエクスペリエンスの機能と効果を理解するには、「 [統合されたセキュリティ情報登録の概念](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-registration-mfa-sspr-combined)」を参照してください。

[Image: 統合されたセキュリティ情報登録の強化されたエクスペリエンスを示すスクリーンショット。]

### 統合登録の条件付きアクセス ポリシー

ユーザーが MFA と SSPR Microsoft Entra登録するタイミングと方法をセキュリティで保護するには、Microsoft Entra 条件付きアクセス ポリシーでユーザー アクションを使用できます。 組織はこの機能を有効にして、ユーザーが一元的な場所からMicrosoft Entra MFA と SSPR に登録できるようにします。 たとえば、ユーザーは、人事のオンボーディング中にアクセスする信頼できるネットワークの場所を使用できます。

注

このポリシーは、ユーザーが統合登録ページにアクセスした場合にのみ適用されます。 このポリシーは、ユーザーが他のアプリケーションにアクセスしたときに MFA 登録を強制しません。

MFA 登録ポリシーを作成するには、「[Microsoft Entra ID 保護: MFA ポリシーの構成](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-mfa-policy)を参照してください。

条件付きアクセスで信頼できる場所を作成する方法の詳細については、「[Microsoft Entra 条件付きアクセス?](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network#trusted-locations) の場所の条件は何ですか>を参照してください。

#### 信頼できる場所からの登録を要求するポリシーを作成する

次の手順では、統合された登録エクスペリエンスを使用して登録を試みる選択したすべてのユーザーに適用されるポリシーを作成します。 非信頼ネットワークに接続されているユーザーは、MFA を実行するか、一時的なアクセス パスを使用してサインインして MFA に登録するか、SSPR を使用してパスワードをリセットする必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **Entra ID**&gt;**Conditional Access** に移動します。
3. **[新しいポリシー]** を選択します。
4. このポリシーの名前を入力します。たとえば、"**信頼されたネットワーク上の統合されたセキュリティ情報の登録**" などです。
5. **[割り当て]** で、 **[ユーザー]** を選択します。 このポリシーを使用する必要があるユーザーとグループを選択します。

    警告

    統合された登録に対してユーザーを有効にする必要があります。
6. **[クラウド アプリまたはアクション]** で、 **[ユーザー操作]** を選択します。 [ **セキュリティ情報の登録** ] チェック ボックスをオンにし、[完了] を選択 **します**。

    [Image: セキュリティ情報の登録を制御する条件付きアクセス ポリシーの作成を示すスクリーンショット。]
7. **[条件]**&gt;**[場所]** で、次のオプションを構成します。

    1. **[はい]** を構成します。
    2. **[任意の場所]** を含めます。
    3. **[すべての信頼できる場所]** を除外します。
8. **アクセス制御**&gt;**許可** で、**多要素認証を必須にする** を選択し、**選択** を選択します。
9. **[ポリシーを有効にする]** を **[オン]** に設定します。
10. ポリシーを完了するには、 **[作成]** を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-registration-mfa-sspr-combined-troubleshoot"} -->
## 統合登録のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-registration-mfa-sspr-combined-troubleshoot
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra の多要素認証とセルフサービス パスワード リセットの統合登録のトラブルシューティング

この記事の情報は、統合された登録エクスペリエンスのユーザーによって報告された問題のトラブルシューティングを行っている管理者に役立ちます。

### 監査ログ

統合登録のためにログに記録されたイベントは、Microsoft Entra監査ログに一覧表示されます。 [ **サービス** ] 列の「 **認証方法」**を参照してください。

[Image: 登録イベントを表示する Microsoft Entra 監査ログ インターフェイス画面のスクリーンショット。]

次の表に、統合された登録によって生成されたすべての監査イベントを示します。

| アクティビティ | ステータス | 理由 | 説明 |
| --- | --- | --- | --- |
| ユーザーが必要なすべてのセキュリティ情報を登録しました。 | Success | ユーザーが必要なすべてのセキュリティ情報を登録しました。 | このイベントは、ユーザーが正常に登録を完了した後に発生します。 |
| ユーザーが必要なすべてのセキュリティ情報を登録しました。 | 障害 | ユーザーがセキュリティ情報の登録を取り消しました。 | このイベントは、ユーザーが中断モードから登録を取り消したときに発生します。 |
| ユーザーが登録したセキュリティ情報。 | Success | ユーザーがメソッドを登録しました。 | このイベントは、ユーザーが個々の方法を登録したときに発生します。 この方法には、Authenticator、電話、電子メール、セキュリティの質問、アプリ パスワード、代替電話などを指定できます。 |
| ユーザーがセキュリティ情報を確認しました。 | Success | ユーザーがセキュリティ情報を正常に確認しました。 | このイベントは、ユーザーが [セキュリティ情報のレビュー] ページで **[問題ありません** ] を選択したときに発生します。 |
| ユーザーがセキュリティ情報を確認しました。 | 障害 | ユーザーがセキュリティ情報を確認できませんでした。 | このイベントは、ユーザーが [セキュリティ情報レビュー] ページで **[正常に見える** ] を選択したが、バックエンドで何かが失敗した場合に発生します。 |
| ユーザーがセキュリティ情報を削除しました。 | Success | ユーザーがメソッドを削除しました。 | このイベントは、ユーザーが個々の方法を削除したときに発生します。 この方法には、Authenticator、電話、電子メール、セキュリティの質問、アプリ パスワード、代替電話などを指定できます。 |
| ユーザーがセキュリティ情報を削除しました。 | 障害 | ユーザーがメソッドを削除できませんでした。 | このイベントは、ユーザーがメソッドを削除しようとしても失敗した場合に発生します。 この方法には、Authenticator、電話、電子メール、セキュリティの質問、アプリ パスワード、代替電話などを指定できます。 |
| ユーザーが既定のセキュリティ情報を変更しました。 | Success | ユーザーは、メソッドの既定のセキュリティ情報を変更しました。 | このイベントは、ユーザーが既定の方法を変更したときに発生します。 このメソッドには、Authenticator 通知、Authenticator またはトークンからのコードを指定できます。 +X XXXXXXXXXX の呼び出し、または +X XXXXXXXXXX へのコードを含むテキストを指定することもできます。 |
| ユーザーが既定のセキュリティ情報を変更しました。 | 障害 | ユーザーは、メソッドの既定のセキュリティ情報を変更できませんでした。 | このイベントは、ユーザーが既定のメソッドを変更しようとしても失敗した場合に発生します。 このメソッドには、Authenticator 通知、Authenticator またはトークンからのコードを指定できます。 +X XXXXXXXXXX の呼び出し、または +X XXXXXXXXXX へのコードを含むテキストを指定することもできます。 |

### 割り込みモードのトラブルシューティング

| 症状 | トラブルシューティングの手順 |
| --- | --- |
| 期待した方法が表示されません。 | 1. ユーザーが Microsoft Entra 管理者ロールを持っているどうかを確認します。 "はい" の場合は、セルフサービス パスワード リセット (SSPR) 管理者ポリシーの違いを確認します。  2. 多要素認証 (MFA) 登録の適用または SSPR 登録の適用により、ユーザーが中断されているかどうかを判断します。 表示する方法を決定するには、「結合登録モード」の [フローチャート](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-registration-mfa-sspr-combined#combined-registration-modes) を参照してください。  3. MFA または SSPR ポリシーが最近いつ変更されたかを確認します。 最近、変更が加えられた場合、更新されたポリシーが反映されるまでしばらく時間がかかることがあります。 |

### 管理モードのトラブルシューティングを行う

| 症状 | トラブルシューティングの手順 |
| --- | --- |
| 特定のメソッドを追加する選択肢はありません。 | 1. メソッドが MFA または SSPR に対して有効になっているかどうかを判断します。  2. メソッドが有効になっている場合は、ポリシーをもう一度保存し、もう一度テストする前に 1 ~ 2 時間待ちます。  3. メソッドが有効になっている場合は、ユーザーが設定が許可されているメソッドの最大数を設定していないことを確認します。 |

### ユーザーに MFA の再登録を要求する

1. 少なくとも [認証ポリシー管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) にサインインします。
2. **[ユーザー**] を参照し、MFA を再登録するユーザーを選択します。
3. [**認証方法**] を選択します&gt;**多要素認証に再登録する必要があります**。
4. **[OK]** を選択して確定します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-sspr-authenticationdata"} -->
## Self-Service パスワードリセットの連絡先情報を事前入力します - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-authenticationdata
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra セルフサービス パスワード リセット (SSPR) のユーザーが登録プロセスを完了しなくてもこの機能を使用できるように、連絡先情報を事前に入力する方法について説明します。

Microsoft Entra のセルフサービス パスワード リセット (SSPR) を使用するには、ユーザーの認証情報を提示する必要があります。 ほとんどの組織では、ユーザーが認証データを自分で登録し、多要素認証のための情報を収集しています。

一部の組織では、Active Directory Domain Servicesに既に存在する認証データを同期して、このプロセスをブートストラップすることを好みます。 ユーザーが介入しなくても、同期されたデータは Microsoft Entra ID と SSPR で利用できるようになります。 ユーザーが自分のパスワードを変更またはリセットする必要がある場合、以前に連絡先情報を登録していない場合でも、ユーザーはそれを実行できます。

Important

**2026 年 11 月 9** 日より、SSPR 設定でユーザーがサインイン中に登録する必要があり、有効なユーザーが SSPR を完了するための十分な方法がない場合、登録キャンペーンでは、適用前に影響を受けるユーザーにメソッドの登録を求められます。 ユーザーが SSPR ポリシーを満たす少なくとも 1 つの方法を登録していることを確認します。 詳細については、「 [認証方法を管理する方法」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-methods-manage)参照してください。

**2026 年 10 月 5 日**より、SSPR は明示的に登録された認証方法のみを受け入れます。 登録されなかったディレクトリ ソースのプロパティ ( `mobilePhone`、 `businessPhone`、 `otherMails` など) は、SSPR 検証では機能しなくなります。

次の要件を満たしている場合、認証連絡先情報を事前設定することができます。

- オンプレミスのディレクトリのデータを適切に書式設定しました。
- Microsoft [Entra テナントに対して Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-express) を構成しました。

電話番号の形式が *+CountryCode PhoneNumber* (*+1 4251234567* など) である必要があります。 その他の制限は次のとおりです。

- 国番号と電話番号の間にスペースが必要です。
- パスワードのリセットは内線番号をサポートしていません。 *+1 4251234567X12345* の形式であっても、電話がかけられる前に内線番号は削除されます。

### 取り込まれるフィールド

Microsoft Entra Connect の既定の設定を使用すると、SSPR の認証連絡先情報を入力するために次のマッピングが行われます。

| オンプレミスの Active Directory | Microsoft Entra ID |
| --- | --- |
| `telephoneNumber` | 会社電話 |
| `mobile` | 携帯電話番号 |

ユーザーが携帯電話番号を認証すると、Microsoft Entra の **[認証の連絡先情報]** の下にある **[電話番号]** フィールドにもその番号が設定されます。

### 認証の連絡先情報

Microsoft Entra 管理センターの Microsoft Entra ユーザーの **[認証方法** ] ページで、少なくとも [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator) ロールが割り当てられているユーザーは、任意のユーザーの認証連絡先情報を手動で設定できます。 既存の方法を確認するには、 **[使用可能な認証方法** ] セクションまたは **[+ 認証方法の追加]** を選択します。

[Image: 認証方法の管理方法を示すスクリーンショット]

この認証連絡先情報には、次の考慮事項が適用されます。

- **[電話番号]** フィールドに電話番号が設定され、SSPR ポリシーの **[携帯電話]** が有効になると、その番号がパスワード リセット登録ページに表示され、パスワード リセット ワークフロー中にも表示されます。
- **[電子メール]** フィールドにメール アドレスが設定され、SSPR ポリシーの **[電子メール]** が有効になると、そのメール アドレスがパスワード リセット登録ページに表示され、パスワード リセット ワークフロー中にも表示されます。

### セキュリティの質問と回答

セキュリティの質問と回答は Microsoft Entra テナントに安全に保存され、ユーザーは My Security-Info [の組み合わせ登録エクスペリエンス](https://aka.ms/mfasetup)を通じてのみアクセスできます。 管理者は、他のユーザーの質問と回答の内容を表示、設定、または変更することはできません。

### ユーザーが登録するとどうなりますか?

ユーザーが登録するとき、登録ページには次のフィールドが設定されます。

- **認証用電話**
- **認証用電子メール**
- **セキュリティの質問と回答**

**[携帯電話]** または **[連絡用メール アドレス]** に値が指定されている場合、ユーザーはそれらの値を使用してすぐにパスワードをリセットすることができます。これはユーザーがサービスに登録していない場合でも実行できます。

ユーザーは、初めて登録するときにもこれらの値を確認し、必要に応じて変更できます。 ユーザーが正常に登録された後、これらの値は、それぞれ **[認証用電話]** フィールドと **[認証用メール]** フィールドの固定値になります。

### PowerShell を使用した認証データの設定と読み取り

PowerShell を使用して、次のフィールドを設定できます。

- **連絡用メール アドレス**
- **携帯電話**
- **会社電話**
    - オンプレミスのディレクトリと同期していない場合にのみ設定できます。

[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview) を使用して、Microsoft Entra ID を操作できます。 [Microsoft Graph REST API を使用して、認証方法を管理する](https://learn.microsoft.com/ja-jp/graph/api/resources/authenticationmethods-overview)こともできます。

#### Microsoft Graph PowerShell を使用する

操作を開始するには、[Microsoft Graph PowerShell モジュールをダウンロードしてインストール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview)します。

`Install-Module` をサポートする PowerShell の最新バージョンから簡単にインストールするには、次のコマンドを実行します。 最初の行では、モジュールがすでにインストールされているかどうかを確認します。

```PowerShell
Get-Module Microsoft.Graph
Install-Module Microsoft.Graph
Select-MgProfile -Name "beta"
Connect-MgGraph -Scopes "User.ReadWrite.All"
```

モジュールがインストールされたら、次の手順に従って各フィールドを構成します。

##### Microsoft Graph PowerShell を使用して認証データを設定する

```PowerShell
Connect-MgGraph -Scopes "User.ReadWrite.All"

Update-MgUser -UserId 'user@domain.com' -otherMails @("emails@domain.com")
Update-MgUser -UserId 'user@domain.com' -mobilePhone "+1 4251234567"
Update-MgUser -UserId 'user@domain.com' -businessPhones "+1 4252345678"

Update-MgUser -UserId 'user@domain.com' -otherMails @("emails@domain.com") -mobilePhone "+1 4251234567" -businessPhones "+1 4252345678"
```

##### Microsoft Graph PowerShell を使用して認証データを読み取る

```PowerShell
Connect-MgGraph -Scopes "User.Read.All"

Get-MgUser -UserId 'user@domain.com' | select otherMails
Get-MgUser -UserId 'user@domain.com' | select mobilePhone
Get-MgUser -UserId 'user@domain.com' | select businessPhones

Get-MgUser -UserId 'user@domain.com' | Select businessPhones, mobilePhone, otherMails | Format-Table
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-sspr-customization"} -->
## Self-Service パスワードリセットのカスタマイズ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-customization
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra のセルフサービス パスワード リセットのユーザー表示とエクスペリエンス オプションをカスタマイズする方法について説明します。

セルフサービス パスワード リセット (SSPR) を使用すると、Microsoft Entra ID のユーザーは、管理者やヘルプデスクの関与なしに、パスワードを変更またはリセットできます。 ユーザーはアカウントがロックされた場合やパスワードを忘れた場合でも、画面の指示に従って自分自身のブロックを解除して、作業に戻ることができます。 この機能により、ヘルプデスクへの問い合わせが減り、ユーザーがデバイスやアプリケーションにサインインできない場合の生産性の低下が減ります。

ユーザーに対する SSPR のエクスペリエンスを向上させるには、パスワード リセット ページ、メール通知、またはサインイン ページのルック アンド フィールをカスタマイズできます。 カスタマイズ オプションは、ユーザーが適切な場所にいることを明確にし、会社のリソースにアクセスしていることをユーザーに確信させるのに役立ちます。

この記事では、ユーザー向けの SSPR 電子メール リンク、会社のブランド化、および Active Directory フェデレーション サービス (AD FS) サインイン ページ リンクをカスタマイズする方法について説明します。 [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)ロールが割り当てられているユーザーは、これらのオプションのほとんどをカスタマイズできます。

### [管理者に問い合わせる] リンクをカスタマイズする

ユーザーが SSPR のサポートを求めるのを支援するために、パスワード リセット ポータルに [ **管理者に問い合わせる** ] リンクが表示されます。 ユーザーがこのリンクを選択すると、次の 2 つのいずれかが実行されます。

- この連絡先リンクをデフォルトの状態のままにしておくと、管理者に電子メールが送信され、ユーザーのパスワードの変更を支援するように求められます。 次の電子メールの例では、この既定の電子メール メッセージが示されています。

    [Image: 管理者に送信されたメールをリセットするサンプル要求を示すスクリーンショット。]
- この連絡先リンクがカスタマイズされている場合、ユーザーは Web ページに移動するか、管理者が指定したアドレスに電子メールを送信してサポートを受けます。

    - このリンクをカスタマイズする場合は、ユーザーがサポートのために既に慣れ親しんでいるリンクに設定することをお勧めします。

    警告

    パスワードのリセットが必要なメールアドレスとアカウントでこの設定をカスタマイズすると、ユーザーはサポートを求めることができない可能性があります。

#### 既定の電子メールの動作

既定の連絡先メールは、次の順序で受信者に送信されます。

1. ヘルプデスク管理者ロールまたはパスワード管理者ロールが割り当てられている場合は、これらのロールを持つ管理者が通知を受け取ります。
2. ヘルプデスク管理者またはパスワード管理者が割り当てられていない場合は、ユーザー管理者ロールを持つ管理者が通知を受け取ります。
3. 上記のどのロールも割り当てられていない場合は、グローバル管理者が通知を受け取ります。

どの場合も、最大 100 人の受信者が通知を受け取ります。

さまざまな管理者ロールとその割り当て方法の詳細については、「 [Microsoft Entra ID で管理者ロールを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)」を参照してください。

#### [管理者のメールに連絡する] を無効にする

組織でパスワードのリセット要求について管理者に通知しない場合は、次の構成オプションを使用できます。

- ヘルプデスクのリンクをカスタマイズして、ユーザーがサポートを受けるために使用できる Web URL アドレスを指定します。 このオプションは **[パスワード リセット]**&gt;**[カスタマイズ]**&gt;**[カスタム ヘルプデスクの電子メールまたは URL]** の下にあります。
- すべてのユーザーに対してセルフサービス パスワード リセットを有効にします。 このオプションは **[パスワード リセット]**&gt;**[プロパティ]** の下にあります。 ユーザーに自分のパスワードをリセットさせたくない場合は、アクセスの対象を空のグループにすることができます。 *このオプションは推奨されません。*

### サインイン ページとアクセス パネルをカスタマイズする

たとえば、サインイン ページをカスタマイズして、会社のブランドに適した画像と共に表示されるロゴを追加できます。 会社のブランドを構成する方法の詳細については、[Microsoft Entra ID のサインイン ページへの会社のブランドの追加](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-customize-branding)に関する記事を参照してください。

選択したグラフィックは、次の状況で表示されます。

- ユーザーがユーザー名を入力した後。
- ユーザーがカスタマイズされた URL にアクセスした場合:
    - パスワードリセットページに `whr` パラメータを渡す (例: `https://login.microsoftonline.com/?whr=contoso.com`)。
    - パスワードリセットページに `username` パラメータを渡す (例: `https://login.microsoftonline.com/?username=admin@contoso.com`)。

SSPR では、ブラウザーの言語設定が優先されます。 ブラウザー言語のカスタマイズがある場合、ページはブラウザー言語のカスタマイズで表示されます。 それ以外の場合は、既定のロケールのカスタマイズに該当します。

#### ディレクトリ名

よりパーソナライズされるように、ポータルと自動通信で組織名を変更できます。

Microsoft Entra 管理センターでディレクトリ名属性を変更するには:

重要

Microsoft は、アクセス許可が最も少ないロールを使用することを推奨しています。 このプラクティスは、組織のセキュリティを強化するのに役立ちます。 グローバル管理者は、緊急シナリオに限定する必要がある、または既存のロールを使用できない場合に、高い特権を持つロールです。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. **Entra ID**&gt;**Overview**&gt;**Properties** に移動します。
3. 名前を更新します。
4. **保存** を選択します。

この組織名のオプションは、次の例のように、自動メールで最も目立ちます。

- **メールの表示名**: たとえば、 *CONTOSO デモの代わりに Microsoft*
- **メールの件名**: *例: CONTOSO デモ アカウントのメール確認コード*

### AD FS サインイン ページをカスタマイズする

ユーザーのサインイン イベントに AD FS を使用する場合は、「 [サインイン ページの説明を追加する](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/add-sign-in-page-description)」の記事のガイダンスを使用して、サインイン ページへのリンクを追加できます。

SSPR ワークフローに入るためのページへのリンクをユーザーに提供します (例: `https://passwordreset.microsoftonline.com` )。 AD FS サインイン ページにリンクを追加するには、AD FS サーバーで次のコマンドを使用します。

```powershell
Set-ADFSGlobalWebContent -SigninPageDescriptionText "<p><a href='https://passwordreset.microsoftonline.com' target='_blank'>Can't access your account?</a></p>"
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-sspr-reporting"} -->
## セルフサービス パスワード リセット レポート - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-reporting
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra セルフサービス パスワード リセット イベントに関するレポート

多くの組織は、デプロイ後に、セルフサービス パスワード リセット (SSPR) が本当に使用されているかどうかや、どのように使用されているかを把握することを望んでいます。 Microsoft Entra ID で提供されるレポート機能によって、質問に対する答えをあらかじめ用意されたレポートから得ることができます。 適切にライセンスを付与されている場合は、カスタム クエリを作成することもできます。

[Image: SSPR レポートの監査ログのスクリーンショット。]

[Microsoft Entra 管理センター](https://entra.microsoft.com)に存在するレポートでは、次の質問に回答できます。

注

組織に代わってこのデータが収集されるようにオプトインする必要があります。 オプトインするには、[ **レポート** ] タブまたは監査ログに少なくとも 1 回アクセスする必要があります。 それまでは、ご自分の組織のデータが収集されることはありません。

- SSPR ポリシーにどのような変更が加えられましたか?
- パスワード リセットを登録した人数
- パスワード リセットを登録したユーザー
- ユーザーが登録しているデータ
- 過去 7 日間で自分のパスワードをリセットしたユーザー数
- パスワードをリセットするためにユーザーまたは管理者がもっとも使用する方法
- パスワード リセットを試みる場合に、ユーザーまたは管理者が直面する一般的な問題
- 自らのパスワードを頻繁にリセットしている管理者
- パスワード リセットに関する不審なアクティビティの有無

### パスワード管理レポートを表示する方法

パスワード リセットおよびパスワード リセット登録イベントを表示するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. **ユーザー** ブレードから **監査ログ** を選択します。 ディレクトリ内のすべてのユーザーに対して発生したすべての監査イベントが表示されます。 このビューをフィルター処理して、すべてのパスワード関連イベントを表示できます。
4. ウィンドウの上部にある **[フィルター** ] メニューから[ **サービス** ] ドロップダウン リストを選択し、 **セルフサービス パスワード管理** サービスの種類に変更します。
5. 必要に応じて、関心のある特定の **アクティビティ** を選択して、一覧をさらにフィルター処理します。

#### 統合された登録

[統合された登録](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-registration-mfa-sspr-combined) セキュリティ情報の登録と管理イベントは、監査ログの **[セキュリティ**&gt;**認証方法**] にあります。

### レポートの列の説明

次の一覧では、レポートの各列について詳細に説明します。

- **ユーザー**: パスワード リセット登録操作を試みたユーザー。
- **ロール**: ディレクトリ内のユーザーのロール。
- **日付と時刻**: 試行の日時。
- **登録済みデータ**: パスワード リセット登録時にユーザーが指定した認証データ。

### レポートの値の説明

次の表では、各列に設定できる、さまざまな値について説明します。

| 列 | 使用できる値とその意味 |
| --- | --- |
| 登録データ | **連絡用メール**: ユーザーは、代替メールまたは認証メールを使用して認証を行いました。<br>**Office Phone**: ユーザーは、認証に会社の電話を使用しました。<br><br>**携帯電話**: ユーザーは、認証に携帯電話または認証電話を使用しました。<br><br>**セキュリティの質問**: ユーザーは、セキュリティの質問を使用して認証を行いました。<br><br>**前の方法 (代替メール + 携帯電話など) の任意の組み合わせ**: 2 ゲート ポリシーが指定されたときに発生し、ユーザーがパスワード リセット要求の認証に使用した 2 つの方法を示します。 |

### [Self-Service Password Management](https://learn.microsoft.com/ja-jp/entra/identity/authentication/セルフサービスのパスワード管理) のアクティビティの種類

**Self-Service パスワード管理**監査イベント カテゴリには、次のアクティビティの種類が表示されます。

- セルフサービス パスワード リセット ポリシーの変更: 古い値や新しい値など、SSPR ポリシーへの変更を示します。
- セルフサービス パスワード リセットからのブロック: ユーザーが 24 時間に合計 5 回以上、パスワードのリセット、特定のゲートの使用、または電話番号の検証を試みたことを示します。
- パスワードの変更 (セルフサービス): ユーザーが任意のパスワード変更を実行したか、(有効期限が切れたために) 強制的に変更されたことを示します。
- パスワードのリセット (管理者別): 管理者がユーザーに代わってパスワードリセットを実行したことを示します。
- パスワードのリセット (セルフサービス): ユーザーが [Microsoft Entra](https://passwordreset.microsoftonline.com) パスワード リセットからパスワードを正常にリセットしたことを示します。
- セルフサービス パスワード リセット フロー アクティビティの進行状況: パスワード リセット プロセスの一環として、特定のパスワード リセット認証ゲートを渡すなど、ユーザーが実行する特定の各ステップを示します。
- ユーザー アカウントのロック解除 (セルフサービス): ユーザーが、リセットなしでアカウントロック解除の Active Directory 機能を使用して [、Microsoft Entra パスワード リセットからパスワードをリセット](https://passwordreset.microsoftonline.com) せずに Active Directory アカウントのロックを正常に解除したことを示します。
- セルフサービス パスワード リセットに登録されたユーザー: ユーザーが、現在指定されているテナント パスワード リセット ポリシーに従ってパスワードをリセットするために必要なすべての情報を登録したことを示します。

#### アクティビティの種類: セルフサービス パスワード リセット ポリシーの変更

このアクティビティの詳しい説明は次のとおりです。

- **アクティビティの説明**: 管理者が SSPR ポリシーの設定を更新したことを示します。
- **アクティビティ アクター**: 変更を行った管理者の表示名とユーザー プリンシパル名 (UPN)。
- **アクティビティターゲット**: SSPR ポリシーのプロパティを更新しました。
- **アクティビティの状態**:
    - *成功*: ユーザーが SSPR ポリシーの設定を正常に変更したことを示します。
    - *失敗*: ユーザーが SSPR ポリシーの設定を変更できなかったことを示します。
- **アクティビティの状態エラーの理由**:
    - アクセス許可に失敗しましたか?

#### アクティビティの種類: セルフサービス パスワード リセットのブロック

このアクティビティの詳しい説明は次のとおりです。

- **アクティビティの説明**: ユーザーがパスワードのリセット、特定のゲートの使用、または電話番号の検証を 24 時間で合計 5 回以上試みたことを示します。
- **アクティビティ アクター**: 追加のリセット操作を実行することが制限されたユーザー。 このユーザーは、エンド ユーザーまたは管理者である可能性があります。
- **アクティビティのターゲット**: 追加のリセット操作を行うことが制限されたユーザー。 このユーザーは、エンド ユーザーまたは管理者である可能性があります。
- **アクティビティの状態**:
    - *成功*: ユーザーは、今後24時間内に追加のリセットを行うこと、追加の認証方法を試すこと、および追加の電話番号を確認することがすべて制限されていることを示します。
- **アクティビティの状態エラーの理由**: 適用されません。

#### アクティビティの種類: パスワードの変更 (セルフサービス)

このアクティビティの詳しい説明は次のとおりです。

- **アクティビティの説明**: ユーザーが任意のパスワード変更または強制 (期限切れのため) のパスワード変更を実行したことを示します。
- **アクティビティ アクター**: パスワードを変更したユーザー。 このユーザーは、エンド ユーザーまたは管理者である可能性があります。
- **アクティビティのターゲット**: パスワードを変更したユーザー。 このユーザーは、エンド ユーザーまたは管理者である可能性があります。
- **アクティビティの状態**:
    - *成功*: ユーザーが自分のパスワードを正常に変更したことを示します。
    - *失敗*: ユーザーが自分のパスワードを変更できなかったことを示します。 この行を選択すると、 **アクティビティの状態の理由** カテゴリを確認して、エラーが発生した理由の詳細を確認できます。
- **アクティビティの状態エラーの理由**:
    - *FuzzyPolicyViolationInvalidPassword*: ユーザーは、Microsoft の禁止パスワード検出機能が一般的すぎるか、特に脆弱であることが判明したため、自動的に禁止されたパスワードを選択しました。

#### アクティビティの種類: パスワードのリセット (管理者)

このアクティビティの詳しい説明は次のとおりです。

- **アクティビティの説明**: 管理者がユーザーに代わってパスワード リセットを実行したことを示します。
- **アクティビティ アクター**: 別のエンド ユーザーまたは管理者に代わってパスワード リセットを実行した管理者。 パスワード管理者、ユーザー管理者、ヘルプデスク管理者などである必要があります。
- **アクティビティのターゲット**: パスワードがリセットされたユーザー。 このユーザーは、エンド ユーザーまたは別の管理者である可能性があります。
- **アクティビティの状態**:
    - *成功*: 管理者がユーザーのパスワードを正常にリセットしたことを示します。
    - *失敗*: 管理者がユーザーのパスワードを変更できなかったことを示します。 この行を選択すると、 **アクティビティの状態の理由** カテゴリを確認して、エラーが発生した理由の詳細を確認できます。
- **アクティビティの追加の詳細 OnPremisesAgent**:
    - *なし*: クラウドのみのリセットを示します。
    - *Microsoft Entra Connect: Microsoft Entra Connect* ライトバック エージェントを使用してオンプレミスでパスワードがリセットされたことを示します。
    - *CloudSync*: Microsoft Entra CloudSync ライトバック エージェントを介してオンプレミスでパスワードがリセットされたことを示します。

#### アクティビティの種類: パスワードのリセット (セルフサービス)

このアクティビティの詳しい説明は次のとおりです。

- **アクティビティの説明**: ユーザーが [Microsoft Entra](https://passwordreset.microsoftonline.com) のパスワード リセットからパスワードを正常にリセットしたことを示します。
- **アクティビティ アクター**: パスワードをリセットするユーザー。 このユーザーは、エンド ユーザーまたは管理者である可能性があります。
- **アクティビティのターゲット**: パスワードをリセットするユーザー。 このユーザーは、エンド ユーザーまたは管理者である可能性があります。
- **アクティビティの状態**:
    - *成功*: ユーザーが自分のパスワードを正常にリセットしたことを示します。
    - *失敗*: ユーザーが自分のパスワードをリセットできなかったことを示します。 この行を選択すると、 **アクティビティの状態の理由** カテゴリを確認して、エラーが発生した理由の詳細を確認できます。
- **アクティビティの状態エラーの理由**:
    - *FuzzyPolicyViolationInvalidPassword*: 管理者は、Microsoft の禁止パスワード検出機能で、パスワードが一般的すぎるか、特に脆弱であることが判明したため、自動的に禁止されたパスワードを選択しました。

#### アクティビティの種類: セルフサービス パスワード リセット フロー アクティビティの進行状況

このアクティビティの詳しい説明は次のとおりです。

- **アクティビティの説明**: ユーザーがパスワード リセット プロセスの一環として進む特定の各ステップ (特定のパスワード リセット認証ゲートを渡すなど) を示します。
- **アクティビティ アクター**: パスワード リセット フローの一部を実行したユーザー。 このユーザーは、エンド ユーザーまたは管理者である可能性があります。
- **アクティビティのターゲット**: パスワード リセット フローの一部を実行したユーザー。 このユーザーは、エンド ユーザーまたは管理者である可能性があります。
- **アクティビティの状態**:
    - *成功*: ユーザーがパスワード リセット フローの特定の手順を正常に完了したことを示します。
    - *失敗*: パスワード リセット フローの特定の手順が失敗したことを示します。 この行を選択すると、 **アクティビティの状態の理由** カテゴリを確認して、エラーが発生した理由の詳細を確認できます。
- **アクティビティの状態の理由**: 許容されるすべてのリセット アクティビティの状態の理由については、次の表を参照してください。

#### アクティビティの種類: ユーザー アカウントのロック解除 (セルフサービス)

このアクティビティの詳しい説明は次のとおりです。

- **アクティビティの説明**: ユーザーが、リセットなしでアカウントロック解除の Active Directory 機能を使用して [、Microsoft Entra パスワード リセットからパスワードをリセット](https://passwordreset.microsoftonline.com) せずに Active Directory アカウントのロックを正常に解除したことを示します。
- **アクティビティ アクター**: パスワードをリセットせずにアカウントのロックを解除したユーザー。 このユーザーは、エンド ユーザーまたは管理者である可能性があります。
- **アクティビティターゲット**: パスワードをリセットせずにアカウントのロックを解除したユーザー。 このユーザーは、エンド ユーザーまたは管理者である可能性があります。
- **許可されるアクティビティの状態**:
    - *成功*: ユーザーが自分のアカウントのロックを正常に解除したことを示します。
    - *失敗*: ユーザーが自分のアカウントのロックを解除できなかったことを示します。 この行を選択すると、 **アクティビティの状態の理由** カテゴリを確認して、エラーが発生した理由の詳細を確認できます。

#### アクティビティの種類: セルフサービス パスワード リセットに登録されたユーザー

このアクティビティの詳しい説明は次のとおりです。

- **アクティビティの説明**: ユーザーが、現在指定されているテナント パスワード リセット ポリシーに従ってパスワードをリセットするために必要なすべての情報を登録したことを示します。
- **アクティビティ アクター**: パスワード リセットに登録したユーザー。 このユーザーは、エンド ユーザーまたは管理者である可能性があります。
- **アクティビティのターゲット**: パスワード リセットに登録したユーザー。 このユーザーは、エンド ユーザーまたは管理者である可能性があります。
- **許可されるアクティビティの状態**:
    - *成功*: ユーザーが現在のポリシーに従ってパスワード リセットに正常に登録されたことを示します。
    - *失敗*: ユーザーがパスワード リセットの登録に失敗したことを示します。 この行を選択すると、 **アクティビティの状態の理由** カテゴリを確認して、エラーが発生した理由の詳細を確認できます。

        注

        失敗は、ユーザーが自分のパスワードをリセットできないことを意味するわけではありません。 登録プロセスを完了していないことを意味します。 ユーザーのアカウントに未確認の正しいデータ (未確認の電話番号など) がある場合、そのデータを確認していなくても、パスワードのリセットに使用できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/howto-sspr-windows"} -->
## Windows デバイス向けセルフサービス パスワード リセット - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-windows
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Windows サインイン画面で Microsoft Entra のセルフサービス パスワード リセットを有効にする方法について説明します。

Microsoft Entra ID のセルフサービス パスワード リセット (SSPR) を使用すると、ユーザーは管理者やヘルプデスクの関与なしにパスワードを変更またはリセットできます。 通常、ユーザーは別のデバイスで Web ブラウザーを開いて [SSPR ポータル](https://aka.ms/sspr)にアクセスします。 Windows 7、8、8.1、10、11 を実行しているコンピューターでのエクスペリエンスを向上させるために、ユーザーが Windows サインイン画面でパスワードをリセットできるようにすることができます。

[Image: SSPR リンクを含む Windows サインイン画面の例を示すスクリーンショット。]

この記事では、管理者に、企業内の Windows デバイスに対して SSPR を有効にする方法について説明します。

IT チームが Windows デバイスから SSPR を使用する機能を有効にしていない場合、またはサインイン中に問題が発生した場合は、ヘルプデスクに連絡してサポートを受けてください。

### 一般的な制限事項

Windows サインイン画面から SSPR を使用する場合、次の制限が適用されます。

- パスワードのリセットは、現在、リモート デスクトップや Hyper-V 拡張セッションからはサポートされていません。
- Microsoft 以外の一部の資格情報プロバイダーは、この機能で問題を引き起こすことが知られています。
- [EnableLUA レジストリ キー](https://learn.microsoft.com/ja-jp/openspecs/windows_protocols/ms-gpsb/958053ae-5397-4f96-977f-b7700ee461ec)を変更してユーザー アカウント制御を無効にすると、問題が発生することがわかっています。
- この機能は、802.1x ネットワーク認証が展開され、[ **ユーザー ログオンの直前に実行する**] オプションがあるネットワークでは機能しません。 802.1x ネットワーク認証が展開されているネットワークでは、マシン認証を使用してこの機能を有効にすることをお勧めします。
- Microsoft Entra ハイブリッド参加済みデバイスで新しいパスワードを使用し、キャッシュされた資格情報を更新するには、ドメイン コントローラーに直接到達可能なネットワーク接続が必要です。 デバイスは、組織の内部ネットワーク上、またはオンプレミスのドメイン コントローラーへのネットワーク アクセスを備えた仮想プライベート ネットワーク上にある必要があります。 SSPR が唯一の要件である場合、ドメイン コントローラーへのネットワーク接続回線は必要ありません。
- イメージを使用する場合は、実行する前に、`sysprep``CopyProfile`手順を実行する前に、組み込み管理者の Web キャッシュがクリアされていることを確認してください。 詳細については、「 [カスタムのデフォルトユーザープロファイルを使用するとパフォーマンスが低下する](https://support.microsoft.com/help/4056823/performance-issue-with-custom-default-user-profile)」を参照してください。
- 次の設定は、Windows 10 デバイスでパスワードを使用およびリセットする機能を妨げることがわかっています。

    - ロック画面の通知がオフになっている場合、 **パスワードのリセット** は機能しません。
    - `HideFastUserSwitching` が **[有効]** または **[1**] に設定されている。
    - `DontDisplayLastUserName` が **[有効]** または **[1**] に設定されている。
    - `NoLockScreen` が **[有効]** または **[1**] に設定されている。
    - `BlockNonAdminUserInstall` が **[有効]** または **[1**] に設定されている。
    - `EnableLostMode` がデバイスに設定されている。
    - *Explorer.exe* はカスタムシェルに置き換えられます。
    - **対話型ログオン: [スマート カードが必要]** が **[有効** ] または **1** に設定されています。
- 次の特定の 3 つの設定を組み合わせると、この機能が動作しなくなる可能性があります。

    - **対話型ログオン: CTRL+ALT+DEL キーが** **[無効** ] に設定されている必要はありません (Windows 10 バージョン 1710 以前の場合のみ)。
    - `DisableLockScreenAppNotifications` が **[有効]** または **[1**] に設定されている。
    - Windows版はHome版です。

注

これらの制限は、デバイスのロック画面からの Windows Hello for Business PIN リセットにも適用されます。

### Windows 11 および Windows 10 のパスワード リセット

サインイン画面で Windows 11 または Windows 10 デバイスを SSPR 用に構成するには、次の前提条件と構成手順を確認します。

#### Windows 11 と Windows 10 の前提条件

- 少なくとも [Authentication Policy Administrator](https://entra.microsoft.com) として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインし、[Microsoft Entra SSPR を有効にします](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr)。
- ユーザーは、 [Windows サインイン画面で](https://aka.ms/ssprsetup)この機能を使用する前に、SSPR に登録する必要があります。
    - すべてのユーザーは、パスワードをリセットする前に認証の連絡先情報を提供する必要がありますが、これは Windows サインイン画面から SSPR を使用する場合に固有のことではありません。
- ネットワークプロキシの要件:
    - ポート 443 を `passwordreset.microsoftonline.com` および `ajax.aspnetcdn.com` に。
    - Windows 10 デバイスでは、SSPR の実行に使用される一時的な `defaultuser1` アカウントに対して、コンピューター レベルのプロキシ構成またはスコープ付きプロキシ構成が必要です。 詳細については、「トラブルシューティング」セクション を 参照してください。
- Windows 10 バージョン 2018 年 4 月更新プログラム (v1803) 以上を実行し、デバイスは次のいずれかである必要があります。
    - Microsoft Entra が参加。
    - Microsoft Entra ハイブリッド参加済み。

#### Microsoft Intuneを使用してWindows 11とWindows 10を有効にする

Intune を使用して Windows サインイン画面から SSPR を有効にするための構成変更をデプロイするのは、最も柔軟な方法です。 Intuneを使用すると、定義した特定のマシン グループに構成の変更をデプロイできます。 この方法では、デバイスのIntune登録が必要です。

##### Microsoft Intuneで設定カタログ ポリシーを作成する

1. [Microsoft Intune 管理センター](https://go.microsoft.com/fwlink/?linkid=2109431)にサインインします。
2. [構成**] に移動**し&gt; [ **+ 作成**] を選択して [**新しいポリシー**] を選択して、新しいデバイス構成プロファイルを作成します。

    - [**プラットフォーム] で**、[**Windows 10 以降**] を選択します。
    - **[プロファイルの種類** **] で、[設定カタログ]** を選択します。
3. **[作成**] を選択し、プロファイルにわかりやすい名前 (**Windows 11 サインイン画面 SSPR** など) を指定します。

    必要に応じて、プロファイルのわかりやすい説明を入力し、[ **次へ**] を選択します。
4. **[構成設定**] で **[追加**] を選択し、次の OMA-URI 設定を指定してパスワードのリセット リンクを有効にします。

    - 設定の動作を説明するわかりやすい名前 ( **[SSPR リンクの追加**] など) を入力します。
    - 必要に応じて、設定のわかりやすい説明を入力します。
    - **[認証**] を参照し、[**Aad パスワードリセットを許可する**] を選択します。
    - トグルを **[許可]** に設定します。

    **次へ**を選択します。
5. ポリシーは、特定のユーザー、デバイス、またはグループに割り当てることができます。 環境に必要なプロファイルを割り当てます。 ベスト プラクティスは、最初にデバイスのテスト グループに割り当ててから、 **[次へ**] を選択することです。

    詳細については、[Microsoft Intune でユーザーとデバイスのプロファイルを割り当てる](https://learn.microsoft.com/ja-jp/mem/intune/configuration/device-profile-assign)方法に関するページを参照してください。
6. 環境に必要な適用ルール ( **OS エディションが Windows 10 Enterprise の場合にプロファイルを割り当てる**など) を構成し、[ **次へ**] を選択します。
7. プロファイルを確認し、 **[作成**] を選択します。

#### レジストリを使用してWindows 11およびWindows 10を有効にする

レジストリ キーを使用して Windows サインイン画面で SSPR を有効にするには、次の手順を実行します。

1. 管理者の資格情報を使用して Windows PC にサインインします。
2. **Windows** + **R** キーを押して **［ファイル名を指定して実行］** ダイアログを開き、**regedit** を管理者として実行します。
3. 次のレジストリ キーを設定します。

    ```cmd
    HKEY_LOCAL_MACHINE\SOFTWARE\Policies\Microsoft\AzureADAccount
       "AllowPasswordReset"=dword:00000001
    ```

#### Windows 11 と Windows 10 のパスワード リセットのトラブルシューティング

Windows サインイン画面から SSPR を使用して問題が発生した場合、Microsoft Entra 監査ログには、次の出力例に示すように、パスワードのリセットが発生した IP アドレスと `ClientType`に関する情報が含まれています。

[Image: Microsoft Entra 監査ログでの Windows 7 パスワード リセットの例を示すスクリーンショット。]

ユーザーが Windows 11 または 10 デバイスのサインイン画面からパスワードをリセットすると、 `defaultuser1` と呼ばれる特権の低い一時的なアカウントが作成されます。 このアカウントは、パスワードのリセットプロセスを安全に保つために使用されます。

アカウント自体にはランダムに生成されたパスワードがあり、組織のパスワードポリシーに照らして検証されます。 パスワードはデバイスのサインインには表示されず、ユーザーがパスワードをリセットすると自動的に削除されます。 複数の `defaultuser` プロファイルが存在する場合がありますが、無視しても問題ありません。

##### Windows パスワード リセットのプロキシ構成

パスワードのリセット中に、SSPR は `https://passwordreset.microsoftonline.com/n/passwordreset` に接続するための一時的なローカル ユーザー アカウントを作成します。 プロキシがユーザー認証用に構成されている場合、「問題が発生しました。」というエラーで失敗することがあります。 後でもう一度やり直してください。」このエラーは、ローカル ユーザー アカウントが認証されたプロキシの使用を許可されていないために発生します。

この場合は、次のいずれかの回避策を使用します。

- マシンにサインインしているユーザーのタイプに依存しないマシン全体のプロキシ設定を構成します。 たとえば、ワークステーションのグループ ポリシー **の [プロキシ設定の作成] を (ユーザーごとではなく) マシンごとに** 有効にできます。
- 既定のアカウントのレジストリ テンプレートを変更する場合は、SSPR のユーザーごとのプロキシ構成を使用することもできます。 コマンドは次のとおりです。

    ```cmd
    reg load "hku\Default" "C:\Users\Default\NTUSER.DAT"
    reg add "hku\Default\SOFTWARE\Microsoft\Windows\CurrentVersion\Internet Settings" /v ProxyEnable /t REG_DWORD /d "1" /f
    reg add "hku\Default\SOFTWARE\Microsoft\Windows\CurrentVersion\Internet Settings" /v ProxyServer /t REG_SZ /d "<your proxy:port>" /f
    reg unload "hku\Default"
    ```
- 「問題が発生しました」というエラーは、URL `https://passwordreset.microsoftonline.com/n/passwordreset`への接続が中断された場合にも発生する可能性があります。 たとえば、このエラーは、ウイルス対策ソフトウェアが URL の除外なしでワークステーションで実行されている場合に発生する可能性があります `passwordreset.microsoftonline.com`、 `ajax.aspnetcdn.com`、 `ocsp.digicert.com`。 このソフトウェアを一時的に無効にして、問題が解決したかどうかをテストします。

### Windows 7、8、および 8.1 のパスワード リセット

Windows サインイン画面で Windows 7、8、または 8.1 デバイスを SSPR 用に構成するには、次の前提条件と構成手順を確認してください。

#### Windows 7、8、8.1 の前提条件

- 少なくとも [Authentication Policy Administrator](https://entra.microsoft.com) として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインし、[Microsoft Entra SSPR を有効にします](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr)。
- ユーザーは、この機能を使用する前に、 [Windows サインイン画面で](https://aka.ms/ssprsetup)SSPR に登録する必要があります。
    - すべてのユーザーは、パスワードをリセットする前に認証の連絡先情報を提供する必要がありますが、これは Windows サインイン画面から SSPR を使用する場合に固有のことではありません。
- ネットワークプロキシの要件:
    - ポート 443 へ `passwordreset.microsoftonline.com`
- Windows 7またはWindows 8.1オペレーティングシステムにパッチを適用しました。
- TLS 1.2 は、「 [トランスポート層セキュリティ (TLS) レジストリ設定](https://learn.microsoft.com/ja-jp/windows-server/security/tls/tls-registry-settings#tls-12)」のガイダンスに従って有効にします。
- マシンで Microsoft 以外の資格情報プロバイダーが複数有効になっている場合、Windows サインイン画面には複数のユーザー プロファイルが表示されます。

Warnung

TLS 1.2 は、単に自動ネゴシエーションに設定するのではなく、有効にする必要があります。

#### SSPR コンポーネントをインストールする

Windows 7、8、8.1 の場合、Windows サインイン画面で SSPR を有効にするには、コンピューターに小さなコンポーネントをインストールする必要があります。 この SSPR コンポーネントをインストールするには、次の手順を実行します。

1. 有効にするWindowsバージョンに適したインストーラーをダウンロードします。

    ソフトウェア インストーラーは、 [Microsoft ダウンロード センター](https://aka.ms/sspraddin)から入手できます。
2. インストールするマシンにサインインし、インストーラーを実行します。
3. インストール後、再起動を実行することをお勧めします。
4. 再起動後、Windows サインイン画面でユーザーを選択し、[ **パスワードを忘れた場合]** を選択してパスワード リセット ワークフローを開始します。
5. 手順に従ってパスワードをリセットします。

    [Image: パスワードを忘れた場合のWindows 7の例を示すスクリーンショット?SSPR フロー。]

##### サイレントインストール

プロンプトを表示せずに SSPR コンポーネントをインストールまたはアンインストールするには、次のコマンドを使用します。

- **サイレントインストール**: `msiexec /i SsprWindowsLogon.PROD.msi /qn`を使用します。
- **サイレントアンインストール**: `msiexec /x SsprWindowsLogon.PROD.msi /qn`を使用します。

##### Windows 7、8、8.1 のパスワード リセットのトラブルシューティング

Windows サインイン画面から SSPR を使用するときに問題が発生した場合、イベントはマシンと Microsoft Entra ID に記録されます。 Microsoft Entra イベントには、パスワードのリセットが発生した IP アドレスと `ClientType` パラメーターに関する情報が含まれます。

[Image: Microsoft Entra 監査ログでの Windows 7 パスワード リセットの例を示すスクリーンショット。]

さらにログが必要な場合は、マシンのレジストリ キーを変更して詳細ログを有効にします。 トラブルシューティングの目的でのみ詳細ログ記録を有効にするには、次のレジストリ キーの値を使用します。

```cmd
HKLM\SOFTWARE\Microsoft\Windows\CurrentVersion\Authentication\Credential Providers\{86D2F0AC-2171-46CF-9998-4E33B3D7FD4F}
```

- 詳細ログ出力を有効にするには、`REG_DWORD: "EnableLogging"` を作成し、その値を **1** に設定します。
- 詳細ログ出力を無効にするには、`REG_DWORD: "EnableLogging"` を **0** に変更してください。
- ソース `AADPasswordResetCredentialProvider`の下にあるアプリケーション イベント ログのデバッグ ログを確認します。

### ユーザーには何が表示されますか?

Windows デバイス用に SSPR が構成されている場合、ユーザーにとってどのような変更が加えられますか? サインイン画面でパスワードをリセットできることは、どのようにしてわかりますか? 次の例のスクリーンショットは、ユーザーが SSPR を使用してパスワードをリセットするためのその他のオプションを示しています。

[Image: Windows 7 と 10 のサインイン画面と SSPR リンクが示されたスクリーンショット。]

ユーザーがサインインしようとすると、サインイン画面に SSPR エクスペリエンスを開く [ **パスワードのリセット** ] または [ **パスワードを忘れた場合** ] リンクが表示されます。 これで、ユーザーは別のデバイスを使用してWebブラウザにアクセスしなくても、パスワードをリセットできます。

この機能の使用方法の詳細については、「 [職場または学校のパスワードを再設定する](https://support.microsoft.com/account-billing/reset-your-work-or-school-password-using-security-info-23dde81f-08bb-4776-ba72-e6b72b9dda9e)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/kerberos"} -->
## Microsoft Entra Kerberos の概要 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/kerberos
- Service: entra-id / authentication
- Article date: 2026-09-28
- Summary: Microsoft Entra Kerberos プロトコルの概要について説明します。

Microsoft Entra Kerberos は、クラウドとオンプレミスの両方のリソースへのセキュリティで保護されたアクセスを有効にすることで、ハイブリッド ID シナリオをブリッジするクラウドネイティブ認証プロトコルです。 従来の Kerberos 機能を Microsoft Entra エコシステムに拡張するため、組織はレガシ システムとの互換性を損なうことなく ID インフラストラクチャを最新化できます。 また、Windows Hello for Businessや FIDO2 セキュリティ キーなどの最新の資格情報で認証されたユーザーのオンプレミス リソースへのシームレスなシングル サインオン (SSO) も可能になります。

Microsoft Entra Kerberos は、従来のオンプレミス認証プロトコルと最新のクラウド ID の間のギャップを埋めるために、2021 年に導入されました。 実際には、Microsoft Entra Kerberos では、Microsoft Entra IDを Kerberos 認証用のクラウドベースのキー配布センター (KDC) に変換します。 この機能により、Microsoft Entra IDはユーザーに対して Kerberos チケットを発行でき、従来の Kerberos 認証はオンプレミスの Active Directoryを超えて拡張されます。

オンプレミス Active Directory Domain Services (AD DS) にアカウントが存在し、それらのユーザーがMicrosoft Entra IDに同期されるハイブリッド シナリオでは、Kerberos Microsoft Entraが重要な役割を果たします。 これにより、これらのハイブリッド ユーザーは、Kerberos を使用してクラウドとオンプレミスのリソースに対して認証を行うことができます。ドメイン コントローラーに直接アクセスする必要はありません。 たとえば、Microsoft Entra ID参加しているWindows クライアントがインターネット経由でファイル共有またはアプリケーションにアクセスする場合、Microsoft Entra IDはオンプレミスの Active Directory環境に代わって必要な Kerberos チケットを発行できます。

Windowsの Kerberos の詳細については、「Windows Server の Kerberos 認証の概要」を参照してください。

Microsoft Entra Kerberos は、サービスでサポートされている場合、ハイブリッド ID とクラウドのみの ID で動作します。

### ハイブリッド ID

ハイブリッド ID とは、オンプレミスの AD DS と Microsoft Entra ID の両方に存在するユーザー ID を指します。 これらの ID は Microsoft Entra Connect などのツールを使用して同期されるため、ユーザーは単一の資格情報セットを使用してクラウドベースとオンプレミスの両方のリソースにアクセスできます。

このセットアップにより、環境全体でシームレスな認証と SSO エクスペリエンスが可能になります。 レガシ インフラストラクチャを維持しながらクラウドに移行したい組織に最適です。

### クラウドのみの ID (プレビュー)

クラウド専用 ID とは、Microsoft Entra ID (旧称 Azure AD) にのみ存在し、オンプレミスの Active Directoryに対応するアカウントを持たないユーザー アカウントを指します。

### 主な機能と利点

**Seamless ハイブリッド認証**: Microsoft Entra Kerberos を使用すると、アカウントがオンプレミスの AD DS に存在し、Microsoft Entra IDに同期されているユーザーは、クラウドとオンプレミスのリソース間で認証できます。 これにより、ドメイン コントローラーに直接接続する必要が減り (場合によっては不要) になります。

たとえば、Microsoft Entra ID参加しているWindows クライアントがインターネット経由でファイル共有またはアプリケーションにアクセスする場合、Microsoft Entra IDは、リソースに関連付けられている KDC として必要な Kerberos チケットを発行できます。

**クラウド専用 ID のサポート (プレビュー)**: クラウド専用 ID は、オンプレミスの AD DS を必要とせずに、Azure Filesなどのワークロードに Kerberos 認証を使用できるようになりました。 これは、クラウドベースの KDC として機能する Entra Kerberos によって有効になります。

**最新の資格情報のサポートによるセキュリティの強化**: ユーザーは、Windows Hello for Businessや FIDO2 セキュリティ キーなどのパスワードなしの方法を使用してサインインできますが、Kerberos 保護を備えたオンプレミス リソースに引き続きアクセスできます。 この機能により、多要素認証 (MFA) とパスワードレス認証を使用して、パスワードの盗難やフィッシング攻撃に関連するリスクを軽減できます。

**安全なチケット交換**: Microsoft Entra Kerberos は、セキュリティ強化のためにチケット付与チケット (TGT) 交換モデルを使用します。

**スケール可能なグループ メンバーシップ**: Microsoft Entra Kerberos は、信頼性とユーザー エクスペリエンスを向上させるために、大規模または動的なグループ メンバーシップで従来の Kerberos の制限に対処します。 大規模なユーザー グループが関係するシナリオでは、サイト内のすべてのドメイン コントローラー (DC) に対する自動負荷分散によってパフォーマンスが最適化されます。 Azure Virtual Desktop環境でのデプロイでは、応答性を維持するために、十分な DC が使用可能であり、地理的に環境に近い場所にあることを確認することをお勧めします。

### Kerberos Microsoft Entraのしくみ

ハイブリッド シナリオでは、Microsoft Entra Kerberos を使用すると、Microsoft Entra ID テナントを既存のオンプレミスの Active Directory領域と共に専用の Kerberos 領域として動作できます。 ユーザーが参加済みまたはハイブリッド参加済みMicrosoft Entra ID Windows デバイスにサインインすると、デバイスは Microsoft Entra ID で認証され、[Primary Refresh Token (PRT)](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token) を受け取ります。

PRT に加えて、Microsoft Entra IDは、クラウド リソースへの認証に使用される領域 KERBEROS.MICROSOFTONLINE.COM のクラウド TGT を発行します。 このドキュメントで後述するように、オンプレミス のリソースにアクセスするために別の部分的な TGT が発行されます。 このモデルでは、Microsoft Entra IDはシームレスな認証を容易にする KDC として機能します。

#### クラウドのみの ID シナリオ (プレビュー)

Entra Kerberos に対するクラウド専用 ID のサポートには、Entra Kerberos が有効で、Microsoft Entra参加している Windows 10/11 デバイスを持つMicrosoft Entra ID テナントが必要です。

Entra Kerberos を使用したクラウド専用 ID のMicrosoft Entra IDをサポートすることで、Entra 参加済みセッション ホストは、従来のActive Directory インフラストラクチャに依存することなく、Azure ファイル共有などのクラウド リソースを認証してアクセスできます。 この機能は、エンタープライズ レベルのセキュリティ、アクセス制御、暗号化を維持しながらドメイン コントローラーの必要性を排除するために、クラウドのみの戦略を採用する組織にとって不可欠です。

現在、サポートされているワークロードは、Azure SQL Managed InstanceへのAzure Files、Azure Virtual Desktop、Windows 認証アクセスです。

### 認証フロー

#### 1. ユーザー認証

ユーザーは、Microsoft Entra参加済みまたはハイブリッド参加済みのWindows デバイスにサインインします。

ローカルセキュリティ機関 (LSA) は、Cloud Authentication Provider (CloudAP) を使用して、OAuth を通じて Microsoft Entra ID で認証します。

#### 2. トークンの発行

認証が成功すると、Microsoft Entra IDはユーザーとデバイスの情報を含む PRT を発行します。 PRT と共に、Microsoft Entra IDは領域 `KERBEROS.MICROSOFTONLINE.COM` のクラウド TGT を発行します。

Microsoft Entra IDは、ユーザーのセキュリティ識別子 (SID) を含むがグループ要求を含まない OnPremTgt (部分的な TGT) も発行します。 この部分的な TGT は、オンプレミス リソースへの直接アクセスには不十分です。

##### クラウド TGT の発行

Microsoft Entra IDは、必要に応じてクラウド TGT をクライアントに発行することで、クラウド リソースの KDC として機能します。 クライアントは、Microsoft Entra ID テナントをクラウド リソース用の個別の Kerberos 領域として認識し、TGT はクライアントの Kerberos チケット キャッシュに格納されます。 Cloud TGT はローカルにキャッシュされ、PowerShell コマンド 'klist cloud\_debug' を使用して検証できます。

Microsoft Entra IDが発行するクラウドTGT:

- 領域 `KERBEROS.MICROSOFTONLINE.COM`に対応しています。
- Azure Files、Azure SQL、Microsoft Entra Kerberos と統合されているその他のサービスなどのクラウドベースのリソースにアクセスできるようにします。
- クラウド サービスに固有の承認データが含まれており、クラウド リソースの Kerberos サービス チケットを要求するために直接使用されます。
- ユーザーがサポートされている資格情報 (Windows Hello for Businessや FIDO2 など) を使用してWindows デバイスにサインインすると、常に発行されます。
- オンプレミスのドメイン コントローラーに依存しません。

注

クラウド TGT は、オンプレミスの TGT の代わりではありません。 これは、クラウド リソースへのアクセスを許可する別のチケットです。 オンプレミスのリソースにアクセスするために、オンプレミスの TGT が引き続き必要です。

##### オンプレミスアクセスのためのOnPremTgtの発行

次の前提条件が適用されます。

- ユーザーは、Microsoft Entra Connect を使用してオンプレミスの Active DirectoryからMicrosoft Entra IDに同期する必要があります。
- Kerberos サーバー オブジェクトは、オンプレミスの Active Directoryに存在し、Microsoft Entra IDに同期する必要があります。 このオブジェクトを使用すると、Microsoft Entra IDはオンプレミスのドメイン コントローラーが引き換えることができる OnPremTgt を発行できます。
- デバイスは、Windows 10 (2004 以降) またはWindows 11実行されている必要があります。
- デバイスは、Microsoft Entra参加済みまたはハイブリッド参加済みである必要があります。
- 最適な統合を実現するために、Windows Hello for Businessまたは FIDO2 認証方法をお勧めします。
- Kerberos Cloud Trust をサポートするには、オンプレミスのドメイン コントローラーに修正プログラムを適用する必要があります。
- チケット交換のために、クライアント デバイスとドメイン コントローラー間の通信経路を確認します。

ユーザーがWindows 10 (2004以降) またはWindows 11デバイスでパスワード不要の方法 (例えば、FIDO2やWindows Hello for Business) を使用してサインインした場合、Microsoft Entra IDは、ユーザーのオンプレミスActive DirectoryドメインのためにOnPremTgtを発行します。 この OnPremTgt にはユーザーの SID が含まれていますが、承認データは含まれていません。

Microsoft Entra ID によって発行される OnPremTgt:

- Microsoft Entra IDとActive Directoryの間のブリッジとして機能することで、オンプレミス リソースへのアクセスを有効にします。
- 制限されたデータ (ユーザーの SID など) が含まれており、グループ要求は含まれていません。 オンプレミスのリソースにアクセスするだけでは不十分です。
- 環境がサポートするように構成されている場合にのみ発行されます。 たとえば、ハイブリッド ID のセットアップと、Active DirectoryのMicrosoft Entra Kerberos サーバー オブジェクトがあるとします。
- ユーザーの SID、完全な PAC (すべてのグループ メンバーシップ)、セッション キー、およびその他のアクセス制御データを含む完全な TGT に対して、オンプレミスの Active Directory ドメイン コントローラーと交換する必要があります。 その後、サーバー メッセージ ブロック (SMB) 共有や SQL サーバーなどのリソースにアクセスするために、完全な TGT が使用されます。

`dsregcmd /status` コマンドは両方の TGT の結果を表示します。 詳細については、「 [dsregcmd コマンドを使用したデバイスのトラブルシューティング」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-device-dsregcmd#sso-state)。

- OnPremTgt: オンプレミス リソースにアクセスするための Cloud Kerberos チケットが、ログインユーザーのデバイスに存在する場合は、状態を YES に設定します。
- CloudTgt: クラウド リソースにアクセスするための Cloud Kerberos チケットがログイン ユーザーのデバイスに存在する場合は、状態を YES に設定します。

##### Microsoft Entra の Kerberos TGT と Active Directory アクセス制御

ユーザーのオンプレミスの Active Directory ドメインに対してMicrosoft Entra Kerberos TGT を所有しても、完全なActive Directory TGT へのアクセス権は自動的に付与されません。

Microsoft Entra Kerberos は、Read-Only ドメイン コントローラー (RODC) オブジェクトの許可リストとブロックリストを使用して、オンプレミスリソースアクセスのためにMicrosoft Entra IDから部分的な TGT を受信できるユーザーを制御します。 このメカニズムは、公開を制限し、セキュリティ境界を適用するために重要です。 このメカニズムは、ハイブリッド環境において特に重要です。Microsoft Entra ID は、完全な TGT を取得するためにオンプレミスの Active Directory ドメインコントローラーに引き渡される必要がある部分的な TGT を発行します。

交換を完了するには、ユーザーがブロックリストではなく RODC オブジェクトの許可リストにリストされている必要があります。

Active Directory のユーザー アカウント プロパティのスクリーンショットです。

交換プロセス中に、部分的なMicrosoft Entra Kerberos TGT が完全なActive Directory TGT に変換されます。 Microsoft Entra IDは、リストを評価してアクセスの適格性を判断します。 ユーザーが許可リストに含まれている場合、Microsoft Entra IDは完全な TGT を発行します。 ユーザーがブロックリストに含まれている場合、Microsoft Entra IDは要求を拒否し、認証は失敗します。

ベスト プラクティスとして、既定の構成を **[拒否**] に設定します。 許可されているグループに対してのみ、Microsoft Entra Kerberos の明示的な **Allow** アクセス許可を付与します。

Important

部分的な TGT を認識するのは、オンプレミスの Active Directoryだけです。 部分的な TGT にアクセスしても、Active Directory外のリソースへのアクセスは提供されません。

##### 領域マッピング

領域マッピングは、Windowsクライアントがユーザーがリソースにアクセスするときに接続する Kerberos 領域を決定できるようにするメカニズムです。 このメカニズムは、組織が同じ環境でオンプレミスの Active DirectoryとMicrosoft Entra IDの両方を使用する場合に特に重要です。

Windowsは、サービスの名前空間 (`*.file.core.windows.net` など) を使用して、Kerberos チケットのActive DirectoryまたはMicrosoft Entra IDに問い合わせるかどうかを決定します。 クラウドとオンプレミスの両方のサービスが同じ名前空間を共有する可能性があるため、Windowsはそれらを自動的に区別できません。

この状況を解決するために、管理者は次の方法でホスト名と Kerberos 領域のマッピングを構成します。

- グループ ポリシー: **コンピューターの構成**&gt;**管理者テンプレート**&gt;**System**&gt;**Kerberos**&gt;**Define ホスト名と Kerberos 領域のマッピング**
- Intune Policy 構成サービス プロバイダー (CSP): **Kerberos/HostToRealm**

マッピングの例として、contoso.com は `.file.core.windows.net` から `KERBEROS.MICROSOFTONLINE.COM` に対応付けられています。 このマッピングは、特定のAzure Files インスタンスに対して Microsoft Entra Kerberos を使用するようにWindowsに指示し、他のインスタンスは既定で オンプレミスの Active Directory に設定します。

##### Microsoft Entra Kerberos でAzureテナント情報

Microsoft Entra IDは、クラウド リソースの KDC として機能します。 Kerberos チケットの発行と検証方法をガイドするテナント固有の構成が保持されます。

- **Cloud TGT**: Microsoft Entra ID はこの TGT を領域 `KERBEROS.MICROSOFTONLINE.COM` に対して発行します。 クライアントの Kerberos チケット キャッシュに格納され、クラウド リソースへのアクセスに使用されます。
- **KDC プロキシ**: このプロトコルは、インターネット経由で Kerberos トラフィックをMicrosoft Entra IDに安全にルーティングします。 このルーティングにより、クライアントはドメイン コントローラーに直接接続せずにチケットを取得できます。
- **Azure テナント認識**: Kerberos スタックは、領域マッピングとテナント ID を使用してクラウド TGT を検証し、サービス チケットを発行します。

#### 3. サービス チケットの要求と発行

オンプレミス リソースへのクライアント アクセスの場合 (ハイブリッド シナリオ):

1. Microsoft Entra IDは部分的な TGT を発行します。
2. クライアントは、部分的な TGT を完全な TGT と交換するために、オンプレミスの Active Directory ドメイン コントローラーに接続します。
3. 完全な TGT は、SMB 共有や SQL サーバーなどのオンプレミス リソースにアクセスするために使用されます。

クライアントはクラウド TGT を使用して、クラウド リソースのサービス チケットを要求します。 オンプレミスの Active Directoryとの対話は必要ありません。 クラウド リソースへのクライアント アクセスの場合:

1. ユーザーがサービスにアクセスすると (たとえば、Azure Files)、クライアントは TGT を提示してMicrosoft Entra IDからサービス チケットを要求します。
2. クライアントは、チケット付与サービス要求 (TGS-REQ) をMicrosoft Entra IDに送信します。
3. Kerberos はサービス (たとえば、 `cifs/mystuff.file.core.windows.net`) を識別し、ドメインを `KERBEROS.MICROSOFTONLINE.COM`にマップします。 KDC プロキシ プロトコルを使用すると、インターネット経由での Kerberos 通信が可能になります。
4. Microsoft Entra IDは、クラウド TGT とユーザーの ID を検証します。 また、Microsoft Entra IDに登録されているAzure Files リソースの要求されたサービス プリンシパル名 (SPN) も検索します。
5. Microsoft Entra IDはサービス チケットを生成し、サービス プリンシパルのキーを使用して暗号化します。 Microsoft Entra IDは、チケット付与サービス応答 (TGS-REP) でクライアントにチケットを返します。
6. Kerberos スタックは TGS-REP を処理し、チケットを抽出し、アプリケーション要求 (AP-REQ) を生成します。
7. AP-REQ は SMB に提供され、Azure Files要求に含まれます。
8. Azure Filesはチケットを復号化し、アクセスを許可します。 FSLogixは、Azure Filesからユーザー プロファイルを読み取り、Azure Virtual Desktop セッションを読み込むことができます。

クラウド リソースへのクライアント アクセスの場合:

1. クラウドのみの ID を持つユーザーは、クラウド リソース (Azure Files共有など) にアクセスします。
2. SMB クライアントは、リソース SPN ( `cifs/<storageaccount>.file.core.windows.net` など) の Kerberos サービス チケットを要求します。
3. Entra Kerberos は、クラウド TGT に基づいてサービス チケットを発行します。 チケットには、ユーザーのEntra ID ID とグループ要求が含まれます。
4. Azure Filesは、Entra IDに対して Kerberos チケットを検証します。 承認は、Azure RBAC ロール (ストレージ ファイル データ SMB 共有共同作成者など) を使用して適用されます。
5. RBAC アクセス許可が満たされている場合、ユーザーはリソースにアクセスできます。 オンプレミスの AD または NTFS ACL は関与しません。承認は完全にクラウドベースです。

**従来の Kerberos との主な違い:**

- オンプレミスの KDC または Active Directory DS はありません。
- NTFS ACL の適用なし。では、Azure RBAC が使用されます。
- クラウド内の Entra Kerberos によって発行された Kerberos チケット。

#### 概要

| 特徴 | クラウド TGT | オンプレミスの TGT |
| --- | --- | --- |
| 発行者 | Microsoft Entra ID | オンプレミスのActive Directory (Exchange 経由) |
| 国土 | `KERBEROS.MICROSOFTONLINE.COM` | オンプレミス Active Directory ドメイン |
| 承認データ | クラウド固有 | 完全なActive Directory グループ メンバーシップ |
| 交換が必要です | いいえ | はい (部分的な TGT から完全な TGT) |
| 利用シーン | Azure Files、Azure SQL | SMB 共有、レガシーアプリ |
| 検証ツール (macOS) | `tgt_cloud` | `tgt_ad` |
| 検証ツール (Windows) | `klist cloud_debug` | `klist get krbtgt` |

### シナリオ

Microsoft Entra Kerberos は、先進認証方法を使用してActive Directory リソースにアクセスできるようにする、いくつかの認証シナリオの基礎として機能します。 これらのシナリオには、クラウドマネージド ID のアクセス、Windows Hello for Businessクラウド Kerberos 信頼、FIDO2 セキュリティ キーサインイン、Azure Files認証、Azure Virtual Desktop プロファイル アクセス、macOS でのプラットフォーム SSO が含まれます。

#### Windows Hello for Business クラウド Kerberos 信頼

Windows Hello for Business のクラウド Kerberos トラストは、Microsoft Entra Kerberos を使用して、Active Directory リソースへのパスワードを使用しないアクセスを提供します。 ユーザーがWindows Hello for Businessでサインインすると、Microsoft Entra IDはクラウドベースの Kerberos チケットを発行します。これにより、ユーザーはファイル共有や基幹業務アプリケーションなど、Active Directoryによって保護されたリソースの Kerberos サービス チケットを取得できます。 この展開モデルでは、証明書の展開または公開キー基盤 (PKI) の要件を削除することで、パスワードレスの導入が簡略化されます。

Important

Active Directoryによって保護されているリソースにアクセスするには、ユーザーがActive Directoryに対応するアカウントを持っている必要があります。 クラウド管理ユーザーの場合は、[Microsoft Entra ID to Active Directory provisioning](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/overview-provision-entra-id-to-active-directory) を使用してユーザー アカウントをプロビジョニングします。 Active Directory アカウントを持たないクラウド専用ユーザーは、Active Directory TGT を取得したり、Active Directoryによって承認されたリソースにアクセスしたりすることはできません。

Microsoft Entra IDはユーザーを認証し、部分的な Kerberos TGT を発行します。 クライアントは、プロビジョニングされたActive Directory アカウントとそのグループ メンバーシップを使用して承認を実行し、完全な TGT とサービス チケットを発行する、Active Directory ドメイン コントローラーとチケットを交換します。 Cloud Kerberos の信頼では、ユーザーのWindows Hello for Business キーをActive Directoryに同期する必要はありません。

詳細については、[Windows Hello for Businessクラウド Kerberos 信頼展開ガイド](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/deploy/hybrid-cloud-kerberos-trust?tabs=intune)を参照してください。

#### クラウドマネージド ID を使用してActive Directoryリソースにアクセスする

クラウドファーストの ID モデルを採用している組織は、Active Directoryによって保護されたアプリケーションとリソースを引き続き使用しながら、Microsoft Entra IDでユーザーとグループを管理できます。

Microsoft Entra Cloud Sync を使用すると、組織はMicrosoft Entra IDユーザー、グループ、メンバーシップをActive Directoryにプロビジョニングできます。 Microsoft Entra Kerberos を使用すると、これらのユーザーは、Windows Hello for Business のクラウド Kerberos 信頼や FIDO2 セキュリティ キーなどの最新の認証方法を使用して、Kerberos で保護されたリソースにアクセスできます。

このシナリオにより、組織は次のことが可能になります。

- ユーザーとグループの権限ソースをMicrosoft Entra IDに移動します。
- オンプレミスの ID 管理への依存関係を減らします。
- Kerberos で保護されたアプリケーションとリソースへのアクセスを続行します。
- 既存のActive Directory環境との互換性を維持しながら、クラウドマネージド ID をサポートします。

Important

ユーザーをMicrosoft Entra IDからActive Directoryにプロビジョニングすると、対応するActive Directory アカウントが作成および管理されます。 プロビジョニングだけでは、Active Directory リソースへの Kerberos 認証やパスワードレス アクセスは有効になりません。 最新の認証方法を使用して Kerberos で保護されたリソースにアクセスするには、Kerberos Microsoft Entra展開し、サポートされている認証方法 (Windows Hello for Business クラウド Kerberos 信頼や FIDO2 セキュリティ キーなど) を構成する必要もあります。

次の例は、Active Directoryにプロビジョニングされたクラウドマネージド ID が Kerberos Microsoft Entra使用する方法を示しています。

1. ユーザー アカウントは、Microsoft Entra IDで管理されます。
2. Microsoft Entra Cloud Sync は、ユーザーをActive Directoryするようにプロビジョニングします。
3. ユーザーは、Windows Hello for Businessまたは FIDO2 セキュリティ キーを使用してサインインします。
4. Microsoft Entra IDは、Microsoft Entra Kerberos チケットを発行します。
5. Active Directoryは、承認されたリソースの Kerberos サービス チケットを発行します。
6. ユーザーは、パスワードを入力せずに Kerberos で保護されたアプリケーションとリソースにアクセスします。

サポートされているリソースの例を次に示します。

- Windows ファイル共有
- Windows統合認証を使用するインターネット インフォメーション サービス (IIS) アプリケーション。
- Azure Files。
- Kerberos 認証に依存する基幹業務アプリケーション。

注

Microsoft Entra Kerberos は、最新のMicrosoft Entra認証方法と従来のActive Directory リソースの間の Kerberos 認証ブリッジを提供します。 ユーザー プロビジョニングとソースオブオーソリティ転送のシナリオでは、Microsoft Entra Kerberos を使用して、クラウド優先の ID モデルを採用しながら Kerberos で保護されたリソースへの継続的なアクセスを有効にすることができます。

詳細については、以下を参照してください:

- [Microsoft Entra ID オブジェクトをActive Directoryにプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/overview-provision-entra-id-to-active-directory)します。
- [Microsoft Entra クラウドファースト ID ガイダンス](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/guidance-it-architects-source-of-authority)
- [ユーザーの権限ソースをMicrosoft Entra IDに転送](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/user-source-of-authority-overview)します。

#### Microsoft Entra Kerberos を使用して、Azure SQL Managed Instance に対して Windows 認証でアクセスする

Microsoft Entra IDの Kerberos 認証を使用すると、Azure SQL Managed InstanceにWindows 認証アクセスできます。 マネージド インスタンスのWindows 認証により、シームレスなユーザー エクスペリエンスを維持しながら、既存のサービスをクラウドに移行できます。 この機能により、インフラストラクチャの最新化の基礎が提供されます。

詳細については、「[Azure SQL Managed Instance における Microsoft Entra プリンシパルに対する Windows 認証とは何か？](https://learn.microsoft.com/ja-jp/azure/azure-sql/managed-instance/winauth-azuread-overview)」を参照してください。

#### SSO を使用して FIDO2 キーを使用してオンプレミス リソースにサインインする

Microsoft Entra Kerberos ユーザーは、FIDO2 セキュリティ キーなどの最新の資格情報を使用してWindowsにサインインし、従来のActive Directory ベースのリソースにアクセスできます。

詳細については、「 Microsoft Entra IDを参照してください。

#### プラットフォーム SSO で、オンプレミスの Active Directory と Microsoft Entra ID の Kerberos リソースに対して、Kerberos SSO を有効にする。

プラットフォーム SSO PRT と共に、Microsoft Entra はオンプレミスおよびクラウドベースの Kerberos TGT の両方を発行します。 その後、これらの TGT は、プラットフォーム SSO の TGT マッピングを介して macOS のネイティブ Kerberos スタックと共有されます。

詳細については、「[Platform SSO](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-macos-platform-single-sign-on-kerberos-configuration) でオンプレミスのActive DirectoryおよびMicrosoft Entra IDのKerberosリソースに対してKerberos SSOを有効にする」を参照してください。

#### Azure Virtual Desktop用のFSLogixを使用してプロファイル コンテナーを格納する

仮想デスクトップのユーザー プロファイルをホストするには、Microsoft Entra Kerberos 経由でアクセスするAzure ファイル共有にプロファイルを格納します。 Microsoft Entra Kerberos を使用すると、Microsoft Entra IDは業界標準の SMB プロトコルを使用してファイル共有にアクセスするために必要な Kerberos チケットを発行できます。

詳細については、「[Azure Files で Microsoft Entra ID を使用してハイブリッドシナリオにおける FSLogix プロファイルコンテナーをストアする](https://learn.microsoft.com/ja-jp/fslogix/how-to-configure-profile-container-entra-id-hybrid)」を参照してください。

#### Azure Files Microsoft Entra Kerberos 認証を有効にする

Microsoft Entra Kerberos 認証を使用すると、ハイブリッド ID とクラウドのみの ID が Kerberos 認証を使用してAzureファイル共有にアクセスできるようになります。 このシナリオでは、Microsoft Entra IDを使用して、SMB プロトコルを介してファイル共有にアクセスするために必要な Kerberos チケットを発行します。

詳細については、「[Azure Files](https://learn.microsoft.com/ja-jp/azure/storage/files/storage-files-identity-auth-hybrid-identities-enable?tabs=azure-portal%2Cintune) でのハイブリッド ID に対する Kerberos 認証Microsoft Entraを有効にする」を参照してください。

### セキュリティに関する考慮事項

- Microsoft Entra Kerberos では、Microsoft Entra IDに同期されていない ID に部分的な TGT は発行されません。
- Microsoft Entra Kerberos は、KDC プロキシ経由でセキュリティで保護された TGT 交換モデルを使用します。 このモデルは、ドメイン コントローラーへの露出を最小限に抑え、攻撃対象領域を減らします。
- 管理者は、Kerberos チケットに含めるグループを制限するようにグループ解決ポリシーを構成できます。 これらのコントロールは、チケット サイズを管理し、不要なグループ データへの露出を減らすために不可欠です。
- 特権エスカレーションのリスクを防ぐために、クラウド環境とオンプレミス環境の分離をクリアし、機密性の高いアカウントkrbtgt\_AzureAD同期しないことをお勧めします。 `krbtgt_AzureAD` アカウントは、Microsoftのクラウド サービスによって自動的に作成および管理されるEntra IDにのみ存在する必要があります。
- RODC オブジェクトの許可リストとブロックリストを使用して、オンプレミスリソースアクセスのためにMicrosoft Entra IDから部分的な TGT を受信できるユーザーを制御します。

### 制限事項とその他の考慮事項

#### クラウド専用ユーザー ID のサポート (プレビュー)

Microsoft Entra IDでのみ管理されるクラウド専用ユーザー アカウントは、Azure Files、Azure Virtual Desktop、Windows 認証アクセス、そして Azure SQL Managed Instance へのアクセスなどのワークロードで Kerberos 認証がサポートされます。

#### オペレーティング システムとデバイスの制限

Microsoft Entra Kerberos は、Microsoft Entra参加済みまたはハイブリッド参加済みWindows 10 (2004 以降) およびWindows 11デバイスでサポートされます。 一部の機能は、特定のWindowsバージョンとパッチによって異なります。

#### ACL 構成のネットワーク接続要件

ユーザーは、ドメイン コントローラー Azure直接接続することなく、インターネット経由でファイル共有にアクセスできます。 ただし、Windowsアクセス制御リスト (ACL) またはハイブリッド ID のファイル レベルのアクセス許可を構成するには、オンプレミスのドメイン コントローラーに対するアクセス許可のないネットワーク アクセスが必要です。

#### テナント間またはゲスト ユーザーのサポートなし

企業間のゲスト ユーザーまたは他のMicrosoft Entra テナントのユーザーは、現在、Microsoft Entra Kerberos を使用して認証することはできません。

#### パスワードの有効期限

ストレージ アカウントのサービス プリンシパル パスワードは 6 か月ごとに期限切れになり、アクセスを維持するにはローテーションする必要があります。

#### グループ メンバーシップの制限

Kerberos チケットには、含めることができるグループ SID の数を制限するサイズ制約があります。 既定の上限は、チケットあたり 1,010 グループです。 上限を超えた場合は、最初の 1,010 のみが含まれます。 残りの部分を除外すると、大規模な組織のユーザーにアクセスエラーが発生する可能性があります。

#### MFA の Azure Files 認証非互換性

Azure ファイル共有の Kerberos 認証Microsoft Entraは MFA をサポートしていません。 MFA を適用するMicrosoft Entra 条件付きアクセスポリシーは、ストレージ アカウント アプリケーションを除外する必要があります。そうしないと、ユーザーに認証エラーが発生します。

MFA を必要とする条件付きアクセス ポリシーからAzure Filesを除外します。 このタスクを実行するには、ストレージ アカウントまたはAzure Filesにアクセスする特定のアプリケーションを除外するようにポリシーをスコープ設定します。

#### 属性同期の要件

Microsoft Entra Kerberos を機能させるには、キー オンプレミスの Active Directoryユーザー属性の適切な同期が不可欠です。 これらの属性には、 `onPremisesDomainName`、 `onPremisesUserPrincipalName`、および `onPremisesSamAccountName`が含まれます。

#### Azure Storage アカウントごとに 1 つのActive Directoryメソッド

Azure Files ID ベースの認証の場合、ストレージ アカウントごとに一度に有効にできるActive Directory方法は 1 つだけです。 これらの方法には、Microsoft Entra Kerberos、オンプレミス AD DS、Microsoft Entra Domain Servicesが含まれます。 メソッドを切り替える場合は、最初に現在のメソッドを無効にする必要があります。

#### Kerberos 暗号化の設定

Microsoft Entra Kerberos での Kerberos チケット暗号化では、AES-256 のみが使用されます。 SMB チャネル暗号化は、要件に基づいて個別に構成できます。

### Microsoft Entra Kerberos の開始ガイド

1. ハイブリッド ID を認証するには、まず Microsoft Entra Connect を設定して、オンプレミスの AD DS ユーザーをMicrosoft Entra IDに同期する必要があります。 詳細については、[Microsoft Entra Connect インストール ガイド](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites)を参照してください。
2. Microsoft Entra Kerberos 認証を使用するようにAzure Filesまたはその他のサービスを構成します。 手順については、「[Microsoft Entra Kerberos 認証を有効にする](https://learn.microsoft.com/ja-jp/azure/storage/files/storage-files-identity-auth-hybrid-cloud-trust#enable-microsoft-entra-kerberos-authentication?tabs=azure-portal)」を参照してください。
3. Windowsクライアントが最新であり、Microsoft Entra Kerberos用に構成されていることを確認してください。
4. 必要に応じて、サービス プリンシパル パスワードを監視およびローテーションします。
5. [Microsoft Entra ID レポートと監視ツール](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-monitoring-health)を使用して、認証イベントを追跡します。

### Entra Kerberos でのグループ SID の制限 (プレビュー)

Kerberos チケットには、グループに対して最大 1,010 個のセキュリティ識別子 (SID) を含めることができます。 これは、Windows仕様の制限です。 Entra Kerberos がクラウド専用 ID (ハイブリッドに加えて) をサポートするようになったので、チケットにはオンプレミス グループ SID とクラウド グループ SID の両方を含める必要があります。 多くの場合、大企業には、入れ子になったメンバーシップや動的メンバーシップなど、数百または数千のグループにユーザーがいます。 結合されたグループ SID が 1,010 を超える場合、Kerberos チケットを発行できず、認証に失敗します。 これは、NTFS ACL チェックがチケットの完全なグループ メンバーシップに依存する、Azure Filesなどの SMB アクセス シナリオで特に問題になります。

短期的なソリューションとして、クラウド専用 ID に Entra Kerberos を使用するアプリは、アプリケーション マニフェストにタグを追加できます。 Kerberos サービスでこのタグが表示されると、要求にクラウド専用 ID が含まれることが認識されます。 サインインと PRT 発行が成功しました。ただし、ユーザーが Kerberos で保護されたリソースにアクセスし、1010 グループ SID の制限を超えると、サービス チケット時にエラーが発生する可能性があります。

#### 一般的なエンドユーザー エラー

**Windows SMB/Azure Files** - マッピング/マウントの試行が一般的な SMB エラーで失敗する可能性があります (たとえば、システム エラー 86 や 1327 は、MFA などの他のポリシーの競合に表示される可能性があります)。 - ユーザーが 1010 グループ SID の制限を超えたために、小規模なグループ ユーザーのアクセスは成功する可能性がありますが、同じテナント内のグループ化が多いユーザーでは断続的に失敗します。

**サインインとリソース アクセス** - サインインと PRT 発行が成功します。エラーは、サービス チケット時 (ユーザーが Kerberos で保護されたリソースにアクセスした場合) に発生します。

**Entra サインイン ログ エントリ** - エラー 140011 - Entra サインイン ログの KerberosUsersGroupNumberExceeded は、ユーザーの有効なグループ メンバーシップが Kerberos チケットのセキュリティ識別子 (SID) の最大数を超えたため、Kerberos チケット発行プロセスが失敗したことを示します。 管理者は、影響を受けているユーザー (特にネストまたは動的グループ) のグループメンバーシップを減らす必要があります。

#### アプリケーション マニフェスト ファイルで Tags 属性を更新する方法

**オプション 1: Entra 管理ポータルでタグを更新する**

1. Microsoft Entra 管理センターまたはクラウド アプリケーション管理者ロールにサインインします。
2. 次のキーに移動します。
    - Entra ID → アプリの登録 → アプリケーションを選択します。
3. [管理] で、[マニフェスト] をクリックします。
    - JSON エディターで tags プロパティを見つけて、"kdc\_enable\_cloud\_group\_sids" を追加します。
4. [保存] をクリックして変更を適用します。

オプション 2: Microsoft Graph API (アクセス許可: Application.ReadWrite.All) を使用してタグを更新します

##### 要求本文

```http
PATCH https://graph.microsoft.com/v1.0/applications/{applicationObjectId}
Content-Type: application/json
{
   "tags": [
           "kdc_enable_cloud_group_sids"
    ]
}
```

**オプション 3: PowerShell コマンドレットを使用してタグを更新する**

1. PowerShell を管理者特権で起動します。
2. Microsoft Graph PowerShell SDKをインストールしてインポートします。

    ```powershell
    Install-Module Microsoft.Graph -Scope CurrentUser
    Import-Module Microsoft.Graph.Authentication
    Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
    ```
3. テナントに接続し、すべてを受け入れます。

    ```powershell
    Connect-MGGraph -Scopes "Application.ReadWrite.All" -TenantId <tenantId>
    ```
4. 特定のユーザーの certificateUserIds 属性を一覧表示します。

    ```powershell
    Update-MgApplication -ApplicationId "<AppObjectId>" -Tags @("kdc_enable_cloud_group_sids")
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/kerberos-faq"} -->
## Microsoft Entra Kerberos FAQ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/kerberos-faq
- Service: entra-id / authentication
- Article date: 2025-11-04
- Summary: Microsoft Entra Kerberos についてよく寄せられる質問と回答。

この記事では、Microsoft Entra Kerberos のしくみについてよく寄せられる質問について説明します。

### Cloud Kerberos Trust とは

Windows Hello for Business で Kerberos のトラスト アンカーとして Entra ID を使用し、Active Directory フェデレーション サーバー (ADFS) の必要性を排除したり、ユーザー証明書を発行したりできるようにするデプロイ モデル。 デバイスは Entra ID からクラウド TGT を取得し、(必要に応じて) オンプレミス DC と部分的な TGT をオンプレミスアクセス用に交換します。

### クラウドチケット・グランティング・チケット (TGT) と部分的 (リファーラル) TGT の違いは何ですか?

クラウド TGT: KERBEROS.MICROSOFTONLINE.COM 領域の Entra ID によって発行されます。クラウド統合リソース (Azure Files、Azure SQL など) のサービス チケットを要求するために使用されます。 部分的な TGT (紹介): オンプレミスのリソースの完全な AD TGT を取得するためにクライアントがオンプレミス DC と交換する Entra ID からの最小チケット。

### Cloud Kerberos Trust でサポートされているデバイスはどれですか?

Azure AD 参加済みまたはハイブリッド Azure AD 参加済みの Windows 10 バージョン 2004 以降、および Windows 11 デバイス。

### macOS はサポートされていますか?

はい。Kerberos シングル サインオン プロファイルを使用したプラットフォーム シングル サインオンを使用します。 macOS は、Kerberos 拡張機能で構成されている場合、tgt\_cloud (Entra) チケットと tgt\_ad (オンプレミス) チケットを取得できます。

### Windows クライアントで有効にする必要があるポリシーは何ですか?

Windows Hello for Business を有効にし、[オンプレミス認証にクラウド信頼を使用する] を有効にします。 これは通常、Intune 設定カタログまたはグループ ポリシー オブジェクトを使用して展開されます。

### Cloud Kerberos Trust でサポートされているユーザー サインイン方法はどれですか?

キーベースのサインイン方法のみ: Windows Hello for Business (PIN または FIDO2) またはパスワードなしの電話によるサインイン。 パスワード サインインはサポートされていません。

### クライアントがログオン時にクラウド TGT を取得する方法

デバイス ポリシー CloudKerberosTicketRetrievalEnabled = 1 (Microsoft Intune 構成サービス プロバイダーまたはグループ ポリシー オブジェクト) を構成します。 クライアントがクラウド TGT を自動的に取得しないことになります。

### デバイスが正しく参加していて、シングル サインオン状態であるかどうかを確認するにはどうすればよいですか?

`dsregcmd /status`実行し、AzureAdJoined = YES (またはハイブリッド)、AzureAdPrt = YES、CloudTgt = YES を確認します。

### リソースのサービス チケットを確認するにはどうすればよいですか?

`klist get cifs/<storage>.file.core.windows.net` (Azure Files の例) を使用し、klist を使用して取得したチケットを表示します。

### "klist cloud\_debug" では、ポリシーによって Cloud Kerberos が有効になっていると表示されるのはなぜですか。

クライアント ポリシーは適用されません。 Intune またはグループ ポリシー オブジェクトを使用して CloudKerberosTicketRetrievalEnabled = 1 を設定し、再起動して適用します。

### ポリシーを有効にした後でも Cloud TGT が見つからないのはなぜですか?

ユーザーがキーベースのメソッド (WHfB/FIDO2) でサインインし、デバイスが Entra またはハイブリッドに参加していることを確認します。 ハイブリッド アクセスが必要な場合は、信頼されたドメイン オブジェクトが存在し、DC 接続が存在するかどうかを確認します。

### ハイブリッド シナリオで部分 TGT （リファラル）が欠けているのはなぜですか？

AzureADKerberos オブジェクト (信頼されたドメイン オブジェクト) が作成され、正常であることを検証します。最初の対話型サインイン時に DC への見通し線を確認します。

### 詳細な診断のために Entra Kerberos トラフィックを検査するにはどうすればよいですか?

Kerberos.NET Fiddler 拡張機能を使用して、Entra ID へのキー配布センター プロキシ HTTPS トラフィックを復号化し、認証サーバー/チケット付与サービスのフローとエラー コードを調査します。

### Entra Kerberos 経由でレガシ アプリに条件付きアクセスと MFA を適用できますか?

はい。認証は最初に Entra ID を通過するため、条件付きアクセスを適用してから、アプリアクセスのために Kerberos チケットに依存することができます。

### Cloud Kerberos Trust は WHfB 証明書信頼と共存できますか?

No. 証明書信頼ポリシーが存在する場合は、クラウド信頼よりも優先されます。 デバイスごとに 1 つの信頼モデルを選択する

### クラウド専用 ID の AD に AzureADKerberos コンピューター オブジェクトが必要ですか?

いいえ。AD の AzureADKerberos コンピューター オブジェクトは、ハイブリッド シナリオでのみ必要です。

### Entra Kerberos はパスワードの変更をどのように処理しますか?

キーベースのサインインの場合、パスワードの変更は Kerberos チケットに影響しません。 ユーザーは、中断することなく WHfB/FIDO2 で認証を続けます。

### クラウドのみのユーザーのクラウド セキュリティ識別子 (SID) を検索するにはどうすればよいですか?

```msgraph
GET https://graph.microsoft.com/v1.0/users/{userid}?$select=securityIdentifier
ConsistencyLevel: eventual
```

### ハイブリッド ユーザーのオンプレミス SID を見つける方法

```msgraph
GET https://graph.microsoft.com/v1.0/users/{userid}?$select=onPremisesSecurityIdentifier
ConsistencyLevel: eventual
```

### クラウドのみのユーザーのクラウド グループ SID を検索するにはどうすればよいですか?

```msgraph
GET https://graph.microsoft.com/v1.0/groups?$filter=securityEnabled eq true&$select=id,displayName,securityIdentifier
ConsistencyLevel: eventual
```

### ハイブリッド ユーザーのオンプレミス グループ SID を見つける方法

```msgraph
GET https://graph.microsoft.com/v1.0/groups?$filter=securityEnabled eq true&$select=id,displayName,onPremisesSecurityIdentifier
ConsistencyLevel: eventual
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/kerberos-server-key-rotation"} -->
## Microsoft Entra Kerberos の Kerberos サーバー キーをローテーションする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/kerberos-server-key-rotation
- Service: entra-id / authentication
- Article date: 2026-04-23
- Summary: Microsoft Entra Kerberos サーバー キーをローテーションしてセキュリティを維持し、ハイブリッド ID 環境のベスト プラクティスに合わせる方法について説明します。

ハイブリッド ID 環境では、Microsoft Entra Kerberos はオンプレミス Active Directory Domain Services (AD DS) とMicrosoft Entra IDの間で共有 Kerberos サーバー キーを使用します。 このキーは、Microsoft Entra IDによって発行されたチケット許可チケット (TGT) を暗号化して保護します。 この記事では、キーをローテーションしてセキュリティを維持し、Active Directoryのベスト プラクティスに合わせる方法について説明します。

キーは、オンプレミスの Active Directory内の専用Microsoft Entra Kerberos サーバー オブジェクトに格納され、Microsoft Entra IDに安全に発行されます。 このオブジェクトは論理であり、物理サーバーではなく、Kerberos 信頼の読み取り専用ドメイン コントローラー (RODC) のように機能します。

### 前提条件

- `AzureADHybridAuthenticationManagement` PowerShell モジュールがインストールされています。
- オンプレミス AD DS のドメイン管理者または同等の資格情報。
- Microsoft Entra IDのクラウド管理者の資格情報。
- オンプレミスの Active Directoryで既に構成されているMicrosoft Entra Kerberos サーバー オブジェクト。

### キーローテーションが重要な理由を理解する

Kerberos サーバー キーの定期的なローテーションは、次の場合に役立ちます。

- 暗号化マテリアルの有効期間を制限します。
- キーが侵害された場合のリスクを軽減します。
- 標準的な Kerberos とActive Directoryのセキュリティ プラクティスに合わせます。

Microsoftでは、他のActive Directory Kerberos (`krbtgt`) キーに使用するのと同じスケジュールで、Microsoft Entra Kerberos サーバー キーをローテーションすることをお勧めします。

### キーのローテーションのしくみ

Microsoft Entra Kerberos では、ローテーション中のサービス中断を回避するためにデュアルキー モデルを使用します。

- **主キー**: 新しく発行されたすべての Kerberos チケットに使用されます。
- **セカンダリ キー**: 既存のチケットが自然に期限切れになるまで、前のキーを保持して既存のチケットを検証します。

キーをローテーションすると、新しいキーが主キーになり、前の主キーがセカンダリ キーとして保持されます。 Microsoft Entra IDは、セカンダリ キーによって保護されたチケットを引き続き受け入れながら、主キーを使用して Kerberos チケットを検証します。 このプロセスは、ユーザー アクセスを中断しません。

### Kerberos サーバー キーをローテーションする

キーをローテーションするには、 `Set-AzureADKerberosServer` コマンドレットを使用します。 このコマンド:

- 新しい Kerberos サーバー キーを生成します。
- オンプレミスの Active Directory Kerberos サーバー オブジェクトにキーを格納します。
- キーをMicrosoft Entra IDに安全に発行します。
- 両方の環境のキー バージョンを更新します。

```powershell
Set-AzureADKerberosServer -Domain $domain -CloudCredential $cloudCred -DomainCredential $domainCred -RotateServerKey
```

Warning

`krbtgt` キーを回転できる他のツールがあります。 ただし、`Set-AzureADKerberosServer` コマンドレットを使用して、Microsoft Entra Kerberos サーバーの `krbtgt` キーをローテーションする必要があります。 これにより、オンプレミスの Active Directory と Microsoft Entra ID の両方でキーが確実に更新されます。

Important

古いキーを完全に廃止するには、元のプライマリ キーとセカンダリ キーの両方の有効期限が切れたことを確認して、ローテーションを 2 回実行します。

#### -Force パラメーターを使用する

`-Force`で`Set-AzureADKerberosServer`を使用すると、確認プロンプトなしで Kerberos サーバー構成を適用または更新できます。 このフラグは、完全なセキュリティ制御を維持しながら、一貫性のある中断のない実行を保証します。

```powershell
Set-AzureADKerberosServer -Domain $domain -CloudCredential $cloudCred -DomainCredential $domainCred -RotateServerKey -Force
```

通常、 `-Force` は次の場合に使用されます。

- コマンドを再実行して、構成を修復または調整します。
- Kerberos のセットアップまたはキー管理を自動化する。
- 不完全または失敗した構成からの復旧。
- 手動による確認なしで、環境間で一貫性のある状態を確保します。

### キーを回転させるタイミング

Microsoftでは、固定ローテーション間隔は必須ではありませんが、既存の Kerberos セキュリティ プラクティスに合わせることをお勧めします。 一般的な方法は次のとおりです。

- Active Directory `krbtgt` キーと同じ周期で回転します。
- スケジュールされたセキュリティ メンテナンス期間の一部としてローテーションする。
- 資格情報の侵害の疑いがある直後にローテーションする。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/multi-factor-authentication-faq"} -->
## Microsoft Entra 多要素認証に関してよく寄せられる質問 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/multi-factor-authentication-faq
- Service: entra-id / authentication
- Article date: 2026-02-09
- Summary: Microsoft Entra 多要素認証に関してよく寄せられる質問と回答。

この FAQ では、Microsoft Entra 多要素認証と多要素認証サービスの利用について、よく寄せられる質問に回答します。 FAQ の内容は、サービス全般、課金モデル、ユーザー エクスペリエンス、トラブルシューティングに分けてまとめられています。

### 全般

#### ユーザーに SMS メッセージを送る際には、どのショート コードが使われますか。

次の表に示す短いコードを使用して、多要素認証のためにテキスト メッセージをユーザーに送信します。 使用される短いコードは、ユーザーが配置されている国または地域によって異なります。

| 国/リージョン | 短いコード |
| --- | --- |
| アメリカ合衆国 | 26096517896982985873878929767199399673803 |
| カナダ | 208733710797671673803759731 |
| イタリア | 39439000927394390009264 |

SMS メッセージまたは音声ベースの多要素認証のプロンプトが常に同一番号で配信されるという保証はありません。 ユーザーのために、Microsoft は、ルートを調整して SMS メッセージの配信率を向上させる際に任意のタイミングでショート コードを追加または削除する場合があります。

Microsoft は、米国とカナダ以外の国または地域ではショート コードをサポートしていません。

#### Microsoft Entra 多要素認証ではユーザーのサインインが調整されますか。

はい。通常、短い時間枠で認証要求が繰り返される場合、Microsoft Entra 多要素認証では、通信ネットワークを保護し、MFA 疲労スタイルの攻撃を軽減し、すべての顧客の利益のために独自のシステムを保護するためのユーザー サインイン試行が調整されます。

特定の調整制限は共有しませんが、適切な使用に基づいています。

#### 認証用の電話呼び出しやテキスト メッセージの送信について、自分の組織に料金が請求されることはありますか。

いいえ。Microsoft Entra 多要素認証経由でユーザーに対して行われる電話呼び出しや送信されるテキスト メッセージの料金が個別に請求されることはありません。

ユーザーが受ける電話呼び出しやテキスト メッセージの料金は、個人で契約している電話サービスに従って請求されます。

### ユーザー アカウントの管理とサポート

#### ユーザーが電話で応答を受信できない場合、そのユーザーにはどのように伝えればよいですか。

認証用の電話または SMS メッセージを受け取るための操作を、5 分間に 5 回を上限として試行するようユーザーに伝えてください。 Microsoft では、発信と SMS メッセージの配信には複数のプロバイダーを使っています。 このアプローチがうまくいかない場合は、サポート ケースを開いてさらにトラブルシューティングを行うことができます。

サードパーティのセキュリティ アプリでは、確認コードのテキスト メッセージまたは音声通話をブロックすることもできます。 サードパーティのセキュリティ アプリを使用している場合は、保護を無効にしてから、別の MFA 検証コードを送信するように依頼するようにしてください。

前の手順が機能しない場合は、ユーザーが複数の検証方法用に構成されているかどうかを確認します。 再度サインインを試しますが、その際にサインイン ページで別の認証方法を選択します。

詳細については、[エンドユーザー向けトラブルシューティング ガイド](https://support.microsoft.com/account-billing/common-problems-with-two-step-verification-for-a-work-or-school-account-63acbb9b-16a1-47b9-8619-6a865e8071a5)を参照してください。

#### アカウントに入れないユーザーがいる場合はどうすればよいですか。

ユーザーに登録プロセスを再度実行してもらうことで、ユーザーのアカウントをリセットできます。 詳細については、[クラウドでの Microsoft Entra 多要素認証によるユーザーおよびデバイスの設定の管理](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-userdevicesettings)に関するページを参照してください。

#### テキスト メッセージが届かない場合や、認証がタイムアウトになる場合があると、ユーザーが訴えています。

サービスの信頼性に影響する可能性がある制御不能な要因があるため、SMS メッセージの配信は保証されません。 これらの要因には、宛先の国または地域、携帯電話会社、信号の強さなどがあります。

サードパーティのセキュリティ アプリでは、確認コードのテキスト メッセージまたは音声通話をブロックすることもできます。 サードパーティのセキュリティ アプリを使用している場合は、保護を無効にしてから、別の MFA 検証コードを送信するように依頼するようにしてください。

テキスト メッセージがユーザーに確実に届かない問題が頻発する場合は、代わりに Microsoft Authenticator アプリか電話呼び出しによる認証方法を使用するようユーザーに指示してください。 Microsoft Authenticator は、携帯電話と Wi-Fi 接続の両方で通知を受け取ることができます。 さらに、デバイスに信号がまったくない場合でも、モバイル アプリは検証コードを生成できます。 Microsoft Authenticator アプリは、[Android](https://go.microsoft.com/fwlink/?Linkid=825072)、[iOS](https://go.microsoft.com/fwlink/?Linkid=825073)、[Windows Phone](https://www.microsoft.com/p/microsoft-authenticator/9nblgggzmcj6) で利用できます。

#### セキュリティ情報の登録を求めるメッセージがユーザーに表示されるのはなぜでしょうか。

セキュリティ情報の登録を求めるメッセージがユーザーに表示される場合、以下のようないくつかの理由が考えられます。

- そのユーザーは Microsoft Entra ID の管理者によって MFA が有効化されているが、まだアカウントにセキュリティ情報を登録していない。
- そのユーザーに対して、Microsoft Entra ID でのセルフサービス パスワード リセット が有効化されている。 セキュリティ情報は、将来パスワードを忘れた場合に、それをリセットするために役立ちます。
- そのユーザーは、MFA を要求する条件付きアクセス ポリシーが設定されたアプリケーションにアクセスしたが、以前に MFA に登録していない。
- そのユーザーは Microsoft Entra ID (Microsoft Entra Join を含む) にデバイスを登録しようとしており、ユーザーの組織ではデバイスの登録に MFA を要求しているが、ユーザーは事前に MFA への登録を行っていない。
- そのユーザーは Windows 10 で (MFA を要求する) Windows Hello for Business を生成しているが、以前に MFA に登録していない。
- 組織で作成および有効化されている MFA 登録ポリシーが、そのユーザーに適用されている。
- そのユーザーは事前に MFA への登録を行っているが、選択した認証方法が、その後管理者によって無効化されている。 このため、ユーザーはもう一度 MFA 登録を行い、新しい既定の認証方法を選択する必要があります。

### エラー

#### モバイル アプリ通知の使用時に "認証要求がアクティブ化されたアカウントに対してではありません" というエラー メッセージが表示された場合、ユーザーはどうすればよいですか?

次の手順を完了して、Microsoft Authenticator から自分のアカウントを削除し、再度追加するようにユーザーに依頼します。

1. [自分のアカウント プロファイル](https://account.activedirectory.windowsazure.com/profile/)に移動して、組織のアカウントでサインインします。
2. **[追加のセキュリティ確認]** を選択します。
3. Microsoft Authenticator アプリから既存のアカウントを削除します。
4. **[**の構成] を選択し、指示に従って Microsoft Authenticator を再構成します。

#### 非ブラウザー アプリケーションへのサインイン時に 0x800434D4L エラー メッセージが表示された場合、ユーザーはどうすればよいですか?

*0x800434D4L* エラーは、2 段階認証を必要とするアカウントでは機能しないローカル コンピューターにインストールされている非ブラウザー アプリケーションにサインインしようとすると発生します。

このエラーの回避策は、管理者関連の操作と管理者以外の操作用に個別のユーザー アカウントを持つことです。 後で、管理者アカウントと非管理者アカウントの間でメールボックスをリンクして、管理者以外のアカウントを使用して Outlook にサインインできるようにします。 このソリューションの詳細については、「[管理者がユーザーのメールボックスの内容を開いたり表示したりできるようにする](https://help.outlook.com/141/gg709759.aspx?sl=1)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/multi-factor-authentication-get-started-adfs"} -->
## 2 段階認証 Microsoft Entra 多要素認証と ADFS - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/multi-factor-authentication-get-started-adfs
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: これは、Microsoft Entra 多要素認証と AD FS の使用を開始する方法を説明する Microsoft Entra 多要素認証ページです。

[Image: Microsoft Entra 多要素認証と ADFS の概要]

組織が AD FS を使用してオンプレミスの Active Directory と Microsoft Entra ID をフェデレーションしている場合は、Microsoft Entra 多要素認証を使用するための 2 つのオプションがあります。

- Microsoft Entra 多要素認証または Active Directory フェデレーション サービスを使用してクラウド リソースをセキュリティで保護する
- Azure Multifactor Authentication Server を使用してクラウドとオンプレミスのリソースをセキュリティで保護する

次の表は、Microsoft Entra 多要素認証と AD FS を使用してリソースをセキュリティで保護する場合の検証エクスペリエンスをまとめたものです。

| 検証エクスペリエンス - ブラウザーベースのアプリ | 検証エクスペリエンス - ブラウザーベース以外のアプリ |
| --- | --- |
| Microsoft Entra 多要素認証を使用した Microsoft Entra リソースのセキュリティ保護 | - 最初の検証手順は、AD FS を使用してオンプレミスで実行されます。<br>- 2 番目の手順は、クラウド認証を使用して実行される電話ベースの方法です。 |
| Active Directory フェデレーション サービスを使用した Microsoft Entra リソースのセキュリティ保護 | - 最初の検証手順は、AD FS を使用してオンプレミスで実行されます。<br>- 2 番目の手順は、要求を受け入れてオンプレミスで実行されます。 |

フェデレーション ユーザーのアプリ パスワードに関する注意事項:

- アプリ パスワードはクラウド認証を使用して検証されるため、フェデレーションはバイパスされます。 フェデレーションは、アプリ パスワードを設定するときにのみアクティブに使用されます。
- オンプレミスのクライアント アクセス制御設定は、アプリ パスワードでは受け入れられません。
- アプリ パスワードのオンプレミス認証ログ機能が失われます。
- アカウントの無効化/削除は、ディレクトリ同期に最大 3 時間かかる場合があり、クラウド ID でのアプリ パスワードの無効化/削除が遅れる場合があります。

Ad FS を使用した Microsoft Entra 多要素認証または Azure Multifactor Authentication Server の設定については、次の記事を参照してください。

- Microsoft Entra 多要素認証と AD FS を使用してクラウド リソースをセキュリティで保護する
- Windows Server で Azure Multifactor Authentication Server を使用してクラウドとオンプレミスのリソースをセキュリティで保護する
- AD FS 2.0 で Azure Multifactor Authentication Server を使用してクラウドとオンプレミスのリソースをセキュリティで保護する
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/overview-authentication"} -->
## Microsoft Entra 認証の概要 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication
- Service: entra-id / authentication
- Article date: 2026-04-30
- Summary: 認証は、アクセスを許可する前に ID を確認するプロセスです。 Microsoft Entra IDで使用できる認証方法について説明します。

認証は、アプリ、サービス、デバイス、またはネットワークへのアクセスを許可する前に、ユーザーの ID を検証するセキュリティ プロセスです。

### Microsoft Entra ID でサポートされる認証方法

次の表では、認証方法をプライマリ認証 (第 1 要素)、Microsoft Entra多要素認証 (MFA)、セルフサービス パスワード リセット (SSPR)、またはアカウントの回復で使用できる場合の概要を示します。

| メソッド | プライマリ認証 | セカンダリ認証 | SSPR/アカウントの回復 |
| --- | --- | --- | --- |
| [オーセンティケーター・ライト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-authenticator-lite) | いいえ | MFA | いいえ |
| [証明書ベースの認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication) | イエス | MFA | いいえ |
| [電子メール OTP](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-howitworks#authentication-methods) | いいえ | SSPR とサインイン^2^ | SSPR |
| [外部のMFA](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-external-method-manage) | いいえ | MFA | いいえ |
| [ハードウェア OATH トークン (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-oath-tokens#hardware-oath-tokens-preview) | いいえ | MFA | SSPR |
| [Microsoft Authenticator のパスワードレス](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-authenticator-app#passwordless-sign-in-via-notifications) | イエス | いいえ | いいえ |
| [Microsoft Authenticator プッシュ通知](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-authenticator-app#mfa-via-notifications-through-mobile-app) | いいえ | MFA | SSPR |
| [Passkey (FIDO2)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2) | イエス | MFA | いいえ |
| [Microsoft Authenticator のパスキー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-authenticator-app) | イエス | MFA | いいえ |
| パスワード | イエス | いいえ | いいえ |
| [macOS のプラットフォーム資格情報](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-platform-credential-for-macos) | イエス | MFA | いいえ |
| [QRコード](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-qr-code) | イエス | いいえ | いいえ |
| [SMS サインイン](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-sms-signin) | イエス | MFA | SSPR |
| [ソフトウェア OATH トークン](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-oath-tokens#software-oath-tokens) | いいえ | MFA | SSPR |
| [同期されたパスキー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2) | イエス | MFA | いいえ |
| [一時アクセス パス (TAP)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass) | イエス | MFA | いいえ |
| [検証済み ID](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-verified-id)^3^ | いいえ | いいえ | アカウントの回復 |
| [音声通話](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-phone-options) | いいえ | MFA | SSPR |
| [Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-overview) | イエス | MFA^1^ | いいえ |

^1^Windows Hello for Business は、ユーザーがパスキー (FIDO2) を有効にしていて、パスキーが登録されている場合に、ステップアップ MFA 資格情報として機能できます。

^2^セルフサービス [パスワード リセット (SSPR)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-howitworks#authentication-methods) では、テナント メンバーに電子メール OTP を使用できます。 [ゲスト ユーザーによるサインイン](https://learn.microsoft.com/ja-jp/entra/external-id/one-time-passcode)用に構成することもできます。

^3^検証済み ID は、従来の認証方法ではなく、ID 検証機能です。 アカウント回復の ID 証明を提供しますが、サインイン、MFA、または SSPR には使用できません。

### フィッシングに強い認証方法

SMS、電子メール OTP、または認証アプリを使用した従来の MFA では、パスワードのみのシステムよりもセキュリティが大幅に向上しますが、これらのオプションでは、コードの入力、プッシュ通知の承認、認証アプリの使用など、ユーザーに追加の手順が必要な摩擦が生じます。 さらに、これらの MFA オプションは、リモート フィッシング攻撃を受けやすくなります。 リモート フィッシング攻撃では、攻撃者はソーシャル エンジニアリングと AI 駆動型ツールを使用して、ユーザーのデバイスに物理的にアクセスすることなく、パスワードやワンタイム コードなどの ID 資格情報を盗みます。

Microsoft では、Windows Hello for Business、パスキー (FIDO2)、FIDO2 セキュリティ キー、証明書ベースの認証 (CBA) などのフィッシングに強い認証方法を使用することをお勧めします。これは、最も安全なサインイン エクスペリエンスを提供するためです。

Microsoft Entra IDでは、次のフィッシングに対する耐性のある認証方法を使用できます。

- Windows Hello for Business
- macOS のプラットフォーム資格情報
- 同期されたパスキー (FIDO2)
- FIDO2 セキュリティキー
- Microsoft Authenticator のパスキー
- 証明書ベースの認証 (CBA)

### 検証済み ID ID の検証

検証済み ID は、従来の認証方法ではなく、Microsoft Entra IDでの ID 検証機能です。 サインイン、MFA、SSPR などの認証要件を満たすために使用することはできません。 代わりに、検証済み ID は、すべての認証方法が失われた場合のアカウントの回復など、信頼を再確立する必要があるシナリオに対して、ユーザーの検証済み ID の暗号化証明を提供します。

ID 検証プロファイルは、検証済み ID フローに参加できるユーザー、検証を実行するプロバイダー、および ID 要求の検証方法を制御します。 管理者は、Microsoft Entra 管理センターのアカウント回復セットアップ ウィザードを使用してプロファイルを構成します。[確認済み ID ポリシー] ページでは、プロファイルの割り当てとグローバル除外を表示できます。

詳細については、「 [検証済み ID の ID 検証の概要」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-verified-id)参照してください。

### 高保証アカウントの復旧

アカウントの回復は、すべての資格情報を失い、アカウントにアクセスできなくなったユーザーを支援するプロセスです。 従来、ユーザーはヘルプ デスクに電話をかけ、質問に回答して本人確認を行い、ヘルプ デスクは資格情報をリセットします。 Microsoft Entra IDでは、高保証アカウントの回復のための生体認証照合による政府発行の ID 検証がサポートされるようになりました。ヘルプデスクの介入が不要になり、ソーシャル エンジニアリングのリスクが排除されます。

組織は、[Microsoft Security Store](https://securitystore.microsoft.com/) を通じて、主要な ID 検証プロバイダー (IDV) から選択できます。 これらのパートナーは、192 の国/地域にわたるカバレッジと、運転免許証やパスポートを含む政府発行のほとんどの ID ドキュメントのリモート検証を提供しています。 Microsoft Entra Verified ID顔チェックは、Azure AI サービスを利用して、ユーザーのリアルタイムの自撮り写真を自分の ID ドキュメントから写真と照合することで、プレゼンスの証明を検証します。 一致結果のみが共有され、機密性の高い ID データは共有されません。これは、強力な ID 保証を提供しながら、ユーザーのプライバシーを維持します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/passkey-faq"} -->
## パスキーに関する FAQ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/passkey-faq
- Service: entra-id / authentication
- Article date: 2026-03-26
- Summary: パスキーに関連してよく寄せられる質問と回答。

この記事では、パスキーに関してよく寄せられる質問について説明します。 最新のコンテンツを常にチェックしてください。

### Microsoft Entra のパスキーに関する FAQ

#### サインイン中にユーザーがパスキーの登録を求められるのはいつですか?

次の場合、ユーザーにパスキー登録プロンプトが表示される場合があります。

- パスキー登録キャンペーン (ナッジ) が有効になっているか、
- 管理者は、ポリシーを通じてパスキー登録を明示的に要求します。たとえば、条件付きアクセス認証の強度でフィッシングに強い MFA を適用します。

#### 管理者はパスキーの使用状況を監視または監査するにはどうすればよいですか?

管理者は、監査ログ、サインイン ログ、ユーザー通知を使用して、パスキーの作成と使用状況を追跡できます。 現在、パスキーに自動有効期限は設定されていないため、監視およびライフサイクル管理の徹底が推奨されます。

#### Microsoft Entra のパスキーは、量子証明ですか?

現在、パスキーは完全には量子安全ではありませんが、Microsoft は、ポスト量子暗号化を通じて、パスキーを含めた Microsoft Entra 認証を量子安全にするための[ロードマップ](https://www.microsoft.com/en-us/security/blog/2025/08/20/quantum-safe-security-progress-towards-next-generation-cryptography/)を公開しており、2033 年までに完全な移行を目指しています。

### 同期されたパスキーに関する FAQ

#### パスキーを同期する利点は何ですか?

ユーザーのデバイスに既に存在するネイティブおよびサード パーティのパスキー プロバイダーに格納されている同期されたパスキーは、個別の認証デバイスに関連するハード発行と管理の問題の多くを解決します。 パスキーがユーザーのクライアント デバイスとクラウドの間で同期できるという事実により、デバイス バインドパスキーに関連する回復性と再発行のコストが大幅に削減されます。 この利点の組み合わせにより、同期されたパスキーがほとんどのユーザーと組織に最適なオプションになると予想されます。

#### 同期済みのパスキーを段階的にロールアウトするにはどうすればよいですか?

パスキー プロファイルを使用して、同期されたパスキーのロールアウトのスコープを設定してユーザー グループを選択できます。 Microsoft では、管理者と高い特権を持つユーザーにはデバイス バインドパスキー、組織内の管理者以外のアクセス許可を持つすべてのユーザーには同期されたパスキーをお勧めします。

#### 管理者として、パスキーの使用を取り消すことができますか?

Yes. 管理者は、ユーザーごとの認証方法 UX または API を使用して、ユーザーの Microsoft Entra ID アカウントからパスキーを削除できます。

#### 同期されたパスキーを使用する場合、ユーザー アカウントとデバイスを保護するための保護は何ですか?

パスキー プロバイダーにパスキーを登録するには、ほとんどの場合、最初に 2 要素認証を設定する必要があります。 ほとんどのパスキー プロバイダーでは、パスキーをデバイスに格納する前に、デバイス ロックを構成する必要もあります。 このエクスペリエンスは、Google パスワード マネージャーと iCloud キーチェーン全体で一般的ですが、他のパスキー プロバイダーによって異なる場合があります。

#### 管理者は、同期されたパスキーを使用できるデバイスを制御できますか。また、パスキーが共有されないようにすることはできますか?

現在、管理者は、同期されたパスキーのコピーを保持しているデバイスを正確に表示または制御することも、同期されたパスキーが同期された場所を照会することもできません。 これは、ユーザーの個人デバイス間で同期される資格情報の可視性に関する、業界全体にわたる広範な制限を反映しています。 業界は、FIDO アライアンスと共に、この分野の改善に積極的に取り組み、証明書利用者に対してより強力なシグナルと制御を提供しています。 このため、セキュリティとコンプライアンスの要件に基づいて適切なパスキー モデルを選択することが重要です。厳密なデバイス境界制御が困難な要件である場合は、デバイス バインドパスキーが推奨されるオプションです。 これらの資格情報は特定のデバイスに関連付けられており、他の場所では同期されません。 他のユーザー層には、同期されたパスキーが推奨されます。 同期されたパスキーは強力なフィッシング耐性を提供しますが、多くの従来の方法ではデバイスの可視性がなく、フィッシング攻撃の影響を受けやすくなります。

### Authenticator passkey に関する FAQ

#### Microsoft Authenticator はデバイスにパスキーをどのように格納しますか?

Authenticator パスキーはハードウェアによってサポートされます。

iOS では、Authenticator は秘密キーを [セキュリティで保護されたエンクレーブ](https://support.apple.com/guide/security/secure-enclave-sec59b0b31ff/web)に格納します。

Android では、Authenticator は [Android キーストア システム API](https://developer.android.com/privacy-and-security/keystore) を使用して、デバイス バインドパスキーを安全に格納します。 Android キーストア システムでは、次の順序で、Android デバイスのセキュリティで保護されたハードウェアへのキー マテリアルのバインドがサポートされています。

- [セキュア・エレメント (SE)](https://developer.android.com/privacy-and-security/keystore#StrongBoxKeyMint)
- [信頼された実行環境 (TEE)](https://source.android.com/docs/security/features/trusty)

Android では、Android デバイスにこれら 2 つのセキュリティで保護されたハードウェア オプションのいずれかが含まれている場合にのみ、Authenticator によってパスキー (秘密キー) が格納されます。 どちらのハードウェア オプションも存在しない場合、構成証明が無効になっている場合でも、Authenticator のパスキーの登録は失敗します。

#### Authenticator パスキーを新しいデバイスに復元または同期できますか?

Authenticator のパスキーはデバイスにバインドされたものであり、同期できません。 詳細については、「 [Microsoft Authenticator のデバイス バインド パスキー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-authenticator-app#device-bound-passkey)」を参照してください。

#### クロスデバイス認証を実行するには、Bluetoothを有効にする必要がありますか?

Authenticator でパスキーでクロスデバイス認証を使用するには、Bluetoothを有効にし、両方のデバイスでインターネット にアクセスできるようにする必要があります。

#### "デバイスが接続できませんでした" でデバイス間のサインインと登録が失敗する理由

両方のデバイスでインターネット にアクセスでき、Bluetoothが有効になっていることを確認します。 クロスデバイス登録と認証では、構成証明を有効にすると、クロスデバイスでの登録や認証を使用できなくなります。

| Platform | URL |
| --- | --- |
| Android | `cable.ua5v.com` |
| iOS | `cable.auth.com``app-site-association.cdn-apple.com``app-site-association.networking.apple` |

組織でBluetoothの使用が制限されている場合は、 [passkey 対応 FIDO2 認証子と排他的にペアリングするBluetoothを許可](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/passkeys/?tabs=windows%2Cintune#passkeys-in-bluetooth-restricted-environments) して、クロスデバイス サインインとパスキーの登録を許可できます。

#### Authenticator で複数のパスキーを使用できますか?

Authenticator のアカウントごとにパスキーを 1 つだけ持つことができます。 現時点では、Authenticator でサポートされているのは Microsoft Entra ID のパスキーのみです。

#### Authenticator アプリ のカメラを使用して、WebAuthn QR コードをスキャンして登録と認証を行うことができますか?

オーセンティケーターのカメラを使用して、パスキーの登録と認証を行うことができます。 このオプションは、組織がシステム カメラ アプリを Android の仕事用プロファイルにプッシュしていない場合に便利です。

#### インターネットに接続せずに Authenticator でパスキーを使用できますか?

インターネットに接続しないと、パスキーを使用することはできません。 同じデバイスのシナリオでは、パスキーを含むモバイル デバイスにインターネット アクセスが必要です。 クロスデバイスのシナリオでは、パスキーを持つデバイスとサインインするセカンダリ デバイスの両方にインターネット アクセスが必要です。

#### Microsoft Authenticator アプリにパスキーを登録しようとしましたが、"Passkey を追加できませんでした" または "不明なエラー" というエラーが表示されました。どうすればよいですか?

このエラーが発生した場合は、メイン メニューに移動し、[フィードバックの送信] → [問題が発生しました] を選択して、アプリからフィードバックを送信します。 送信されたら、認証アプリのログを確認できるようにフィードバック ID を指定します。

#### 私はAndroid 14デバイスを使っており、すべての手順に従いました。 Authenticator アプリにパスキーを登録できないのはなぜですか?

Authenticator アプリは、Android 14 以降で [Android API](https://developer.android.com/identity/sign-in/credential-provider) を使ってパスキーを利用します。 製造元は、作成するデバイスごとにこれらの API を実装するかどうかを選択します。 デバイスでこれらの API がサポートされていない場合は、Android 14 のデバイスで Authenticator アプリが機能しない可能性があります。 最適なエクスペリエンスを得るために、Android 15 にアップグレードすることをお勧めします。

#### Android 個人用プロファイルの Microsoft Authenticator アプリにパスキーを保存しましたが、仕事用プロファイルで使用できませんか?

この動作は、Microsoft Authenticator アプリに固有の動作ではありません。 Android Work Profile は、意図的に 2 つの分離された環境 (個人用と仕事用) を作成し、企業 ID とデバイス セキュリティに参加するアプリケーションは、仕事用プロファイル コンテナー内で実行する必要があります。 この分離モデルのため、アプリ (Microsoft Authenticator を含む) をプロファイル間で共有することはできません。また、仕事用プロファイルには個別のインスタンスが必要です。 これはプラットフォーム レベルのセキュリティ設計であり、アプリケーションの選択や制限ではありません。

#### Android デバイスで生体認証サインインではなく PIN の入力を求められるのはなぜですか?

Android デバイスで生体認証サインインが失敗した場合、Authenticator アプリは PIN の入力を求めるメッセージを表示します。 次にパスキーを使用してサインインすると、Authenticator は生体認証サインインではなく PIN を要求し続けます。 Authenticator は、生体認証サインインを定期的に再試行します。 生体認証サインインが成功した場合は、後続のサインインに使用されます。

#### Android デバイスで PIN または生体認証サインインを変更した後、パスキーはどうなりますか?

PIN を変更した場合、または生体認証サインインを拇印から顔に変更した場合、またはその逆の場合、パスキーは無効になります。 パスキーが無効になっている場合は、別の方法を使用してサインインし、新しいパスキーを作成する必要があります。

#### 中国の Authenticator でパスキーを使用してサインインできますか?

パスキーは、21Vianet が運営する Microsoft Azure では使用できません。 iOS では、Authenticator のパスキーを使用して、旅行中など、他のグローバル組織にサインインできます。 詳細については、「 [中国での Microsoft Authenticator のダウンロード](https://support.microsoft.com/account-billing/download-microsoft-authenticator-in-china-ebbef05c-a429-4236-8570-1bb1900fec35)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/passwords-faq"} -->
## セルフサービス パスワード リセットに関する FAQ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/passwords-faq
- Service: entra-id / authentication
- Article date: 2025-01-16
- Summary: Microsoft Entra セルフサービス パスワード リセットについてよく寄せられる質問

セルフサービス パスワード リセットについてのあらゆる問題に関するよくあるご質問 (FAQ) を次に示します。

Microsoft Entra ID とセルフサービス パスワード リセット (SSPR) に関する一般的な質問がある場合は、コミュニティに支援を求めることができます。 これは、Microsoft Entra IDの Microsoft Q&A 質問ページで行うことができます。 コミュニティのメンバーには、エンジニア、製品マネージャー、MVP、IT プロフェッショナルなどが含まれます。

この FAQ は、次のセクションに分かれています。

- パスワード リセット登録に関する質問
- パスワード リセットに関する質問
- パスワードの変更に関する質問
- パスワード管理レポートに関する質問
- パスワード ライトバックに関する質問

### パスワード リセット登録

#### ユーザーは独自のパスワード リセット データを登録できますか。

>
> はい。 パスワード リセットが有効になっていてライセンスが付与されている限り、ユーザーはパスワード リセット登録ポータル (https://aka.ms/ssprsetup) にアクセスして認証情報を登録できます。 ユーザーは、アクセス パネル (https://myapps.microsoft.com) で登録することもできます。 アクセス パネルで登録するには、自分のプロフィール画像を選び、 **[プロファイル]** を選んで、 **[パスワード リセットの登録]** オプションを選ぶ必要があります。
>
> [統合された登録](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-registration-mfa-sspr-combined)を有効にすると、ユーザーは SSPR と Microsoft Entra 多要素認証の両方に同時に登録できます。

#### グループに対してパスワード リセットを有効にし、その後全員に対してパスワード リセットを有効にする場合、ユーザーは再登録する必要がありますか。

>
> いいえ。 認証データを入力したユーザーは、再登録する必要はありません。

#### 別のユーザーのパスワード リセット データを定義できますか。

>
> はい。Microsoft Entra Connect、PowerShell、[Microsoft Entra 管理センター](https://entra.microsoft.com)または [Microsoft 365 管理センター](https://admin.microsoft.com)でこれを行うことができます。 詳細については、[Microsoft Entra のセルフサービス パスワード リセットで使われるデータ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-authenticationdata)に関するページをご覧ください。

#### オンプレミスからセキュリティの質問のデータを同期できますか。

>
> いいえ、現在は不可能です。

#### ユーザーは、他のユーザーに見られないようにデータを登録することはできますか。

>
> はい。 ユーザーがパスワード リセット登録ポータルを使ってデータを登録すると、データは[特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator)とそのユーザーだけが見ることのプライベート認証フィールドに保存されます。

#### パスワード リセットを使用するには、ユーザー自身が事前に登録する必要がありますか。

>
> いいえ。 ユーザーに代わって必要な認証情報を定義している場合は、ユーザー が登録を行う必要はありません。 パスワードのリセットは、ディレクトリ内の適切なフィールドに格納されているデータを適切に書式設定する限り機能します。

#### ユーザーの代わりに [認証用電話]、[認証用電子メール]、または [代替の認証用電話] フィールドを同期または設定できますか。

>
> [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator)が設定できるフィールドは、[SSPR データ要件](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-authenticationdata)に関する記事に定義されています。

#### 登録ポータルでユーザーに対して表示されるオプションはどのように決定されていますか。

>
> パスワード リセット登録ポータルには、ユーザーに対して有効にしたオプションのみが表示されます。 これらのオプションは、ディレクトリの [ の構成] タブの ユーザー パスワード リセット ポリシー セクション にあります。たとえば、セキュリティの質問を有効にしていない場合、ユーザーはそのオプションに登録できません。

#### どの段階でユーザーの登録が完了したとみなされますか。

>
> ユーザーは、Microsoft Entra 管理センターで設定したパスワード リセットするために必要な 数以上の方法を登録すると、SSPR に登録されていると見なされます。

### パスワードのリセット

#### ユーザーが短期間に何回もパスワードのリセットを試みることは禁止されていますか。

>
> はい。パスワードのリセットには悪用防止のためのセキュリティ機能が組み込まれています。
>
> ユーザーは自分の情報 (電話番号など) の検証を試みることができますが、24時間以内に 5 回身分証明できなかった場合は、24 時間ロックアウトされます。
>
> ユーザーが 1 時間の間に試行できる電話番号の確認、アプリの認証、テキスト メッセージの送信、セキュリティの質問と回答の確認の回数は 5 回に制限されていて、この回数を超えるとユーザーは 24 時間ロックアウトされます。
>
> ユーザーは、24 時間ロックアウトされるまでの 10 分間に最大 10 回メールを送信できます。
>
> ユーザーが自分のパスワードをリセットすると、カウンターはリセットされます。

#### パスワードのリセットからメール、テキスト メッセージ、または電話呼び出しを受け取るまでにどのくらいの時間がかかりますか?

>
> メール、テキスト メッセージ、電話呼び出しは、1 分以内に着信するはずです。 通常は 5 から 20 秒です。 この期間内に通知を受け取らない場合は、
>
> - 迷惑メール フォルダーを確認します。
> - 連絡を受けている番号やメールが、自分の想定しているものであることを確認してください。
> - ディレクトリ内の認証データの書式が正しいことを確認してください (例: +1 4255551234 または *user@contoso.com*)。
>

#### パスワード リセットはどのような言語に対応していますか。

>
> パスワードのリセット UI、テキスト メッセージ、音声通話は、Microsoft 365 でサポートされているのと同じ言語にローカライズされています。

#### ディレクトリの構成タブで組織のブランド項目を設定した場合、パスワードのリセット エクスペリエンスのどの部分がブランド化されますか。

>
> パスワード リセット ポータルにお客様の組織のロゴが表示されるほか、[管理者に連絡してください] リンクにカスタムのメール アドレスまたは URL を構成できます。 パスワード リセットによって送信されるメールは、組織のロゴ、色、メールの本文内の名前を含み、その特定の名前に対する設定でカスタマイズされます。

#### パスワード リセット ページへの移動方法をユーザーに指示するにはどうしたらよいですか。

>
> [SSPR のデプロイ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-deployment)に関する記事に記載されている提案をいくつか試します。

#### モバイル デバイスからこのページを利用できますか。

>
> はい。このページは、モバイル デバイスでも使用できます。

#### ユーザーがパスワードをリセットするときに、ローカルの Active Directory アカウントのロックを解除できますか。

>
> はい。 ユーザーがパスワードをリセットすると、パスワード ライトバックが Microsoft Entra Connect を通じて展開されると、パスワードをリセットすると、そのユーザーのアカウントのロックが自動的に解除されます。

#### パスワードのリセットをユーザーのデスクトップ サインイン エクスペリエンスに直接統合できますか。

>
> Microsoft Entra ID P1 または P2 のお客様は、追加料金なしで Microsoft Identity Manager をインストールし、オンプレミスのパスワード リセット ソリューションを配置することができます。

#### ロケールごとに異なるセキュリティの質問を設定できますか。

>
> いいえ、現在は不可能です。

#### セキュリティの質問の認証オプションには質問をいくつ設定できますか。

>
> [Microsoft Entra 管理センター](https://entra.microsoft.com)では、カスタムのセキュリティの質問を最大 20 個設定できます。

#### セキュリティの質問の長さに制限はありますか。

>
> セキュリティの質問に許される長さは 3 文字から 200 文字です。

#### セキュリティの質問に対する回答の長さに制限はありますか。

>
> 回答に許される長さは 3 文字から 40 文字です。

#### 回答を複数設定しようとすると拒否されますか。

>
> はい。セキュリティの質問に対して重複する回答は拒否されます。

#### ユーザーが同じセキュリティの質問を 2 回以上登録することはできますか。

>
> いいえ。 ユーザーは、特定の質問を登録した後、同じ質問を再度登録することはできません。

#### 登録とリセットに使用するセキュリティの質問に最低限必要となる個数を設定できますか。

>
> はい。登録用とリセット用にそれぞれ制限を設定できます。 登録に要求できるセキュリティの質問の数は 3 から 5 個、リセットに要求できる質問の数も 3 から 5 個です。

#### リセットの場合はユーザーにセキュリティの質問の使用を要求するようにポリシーを構成しましたが、Azure 管理者に対する設定は異なっているようです。\*\*

>
> これは正しい動作です。 Microsoft では、任意の Azure 管理者ロールに強力な既定の 2 ゲート パスワードのリセット ポリシーを適用します。 これにより、管理者はセキュリティの質問を使用できなくなります。 このポリシーについて詳しくは、「[Microsoft Entra ID のパスワード ポリシーと制限](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-policy)」をご覧ください。

#### リセットに必要な質問の最大数を超える数の質問をユーザーが登録した場合、セキュリティの質問はリセット時にどのように選択されますか。

>
> ユーザーが登録したすべてのセキュリティの質問の中から *N* 個の質問がランダムに選択されます。*N* は、**[リセットに必要な質問の数]** オプションに設定されている数です。 たとえば、ユーザーが 5 個のセキュリティの質問を登録してあり、パスワードのリセットには 3 個だけが必要な場合は、5 個の質問から 3 個がランダムに選ばれて、リセット時に表示されます。 同じ質問が繰り返し表示されるのを防ぐため、ユーザーが質問の答えを間違えた場合は、選択プロセスが最初から行われます。

#### メールとテキスト メッセージのワンタイム パスコードの有効期間はどのくらいですか?

>
> パスワードのリセットのセッション有効期間は 15 分です。 パスワード リセット操作の開始からパスワードをリセットするまで、ユーザーに 15 分の時間が与えられます。 ワンタイム パスコードは、パスワード リセット セッション中に 5 分間有効です。

#### ユーザーがパスワードをリセットするのをブロックできますか。

>
> はい。グループを使って SSPR を有効にしている場合、ユーザーがパスワードをリセットできるグループから個別のユーザーを削除できます。 ユーザーが [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator)である場合、ユーザーはパスワードをリセットでき、無効にすることはできません。

### パスワードの変更

#### ユーザーが自分のパスワードを変更するにはどこにアクセスすればよいですか。

>
> ユーザーは [Office 365](https://portal.office.com) ポータルや[アクセス パネル](https://myapps.microsoft.com)など、右上隅にプロファイル画像やアイコンが表示されるページであればどこでもパスワードを変更できます。 ユーザーは[アクセス パネルのプロファイル ページ](https://account.activedirectory.windowsazure.com/r#/profile)からパスワードを変更できます。 パスワードの有効期限が切れている場合は、Microsoft Entra サインイン ページでパスワードを自動的に変更するように求めることもできます。 最終的には、ユーザーは [Microsoft Entra のパスワード変更ポータル](https://account.activedirectory.windowsazure.com/ChangePassword.aspx)を表示して、パスワードを変更できます。

#### ユーザーのオンプレミスのパスワードの有効期限が切れたときに Office ポータルに通知できますか。

>
> はい。現在、Active Directory フェデレーション サービス (AD FS) を使っている場合は通知できるようになりました。 AD FS を使っている場合は、「[Sending password policy claims with AD FS](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configure-ad-fs-to-send-password-expiry-claims?f=255&MSPPError=-2147217396)」(AD FS でパスワード ポリシー要求を送信する) の手順に従ってください。 パスワード ハッシュ同期を使用する場合、現時点ではこれを行うことはできません。 マイクロソフトではパスワードのポリシーをオンプレミスのディレクトリから同期していないため、有効期限切れの通知をクラウドに送信できません。 いずれの場合でも、[PowerShell を使ってパスワードの有効期限が迫っていることをユーザーに通知する](https://social.technet.microsoft.com/wiki/contents/articles/23313.notify-active-directory-users-about-password-expiry-using-powershell.aspx)ことは可能です。

#### ユーザーがパスワードを変更するのをブロックできますか。

>
> クラウド専用のユーザーの場合、パスワードの変更をブロックすることはできません。 オンプレミス ユーザーの場合は、[ユーザーがパスワード を変更できない ] オプションを選択済みに設定できます。 選択したユーザーは自分のパスワードを変更できません。

### パスワード管理レポート

#### パスワード管理レポートにデータが表示されるまでにどのくらいの時間がかかりますか。

>
> データは、5 分から 10 分でパスワード管理レポートに表示されます。 場合によっては、最大 1 時間かかることもあります。

#### パスワード管理レポートのフィルターにはどのような方法がありますか。

>
> パスワード管理レポートをフィルター処理するには、レポート上部の列ラベルの右端にある小さな虫眼鏡アイコンを選びます。 より包括的なフィルター処理を行うには、レポートを Excel にダウンロードしてピボット テーブルを作成します。

#### パスワード管理レポートに格納される最大イベント数はどれだけですか。

>
> パスワード管理レポートには、最大 30 日間の範囲について、最大 75,000 個のパスワード リセット イベントまたはパスワード リセット登録イベントが格納されます。 この数を増やして、より多くのイベントを含むように取り組んでいます。

#### パスワード管理レポートはどの程度の期間まで遡って作成できますか。

>
> パスワード管理レポートには、過去 30 日以内に発生した操作が表示されます。 今のところ、このデータをアーカイブする必要がある場合は、レポートを定期的にダウンロードして別の場所に保存してください。

#### パスワード管理レポートに表示できる行の最大数はありますか。

>
> はい。 パスワード管理レポートに表示できる行は、UI に表示されているかダウンロードされているかに関係なく、最大 75,000 行です。

#### パスワードのリセットまたは登録レポート データにアクセスする API はありますか。

>
> はい。この情報は、[認証方法のアクティビティ レポート](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-methods-activity)、または[パスワード リセット アクティビティを取得するための API](https://learn.microsoft.com/ja-jp/graph/api/reportroot-list-usercredentialusagedetails) から取得できます。 [監査ログ API](https://learn.microsoft.com/ja-jp/graph/api/resources/azure-ad-auditlog-overview) を使用して、SSPR イベントでフィルター処理することもできます。

### パスワードの書き戻し

#### パスワード ライトバックは、バック グラウンドでどのよう処理を行うのですか。

>
> パスワード ライトバックを有効にした場合の動作とシステム内でデータがオンプレミスの環境に戻る経路について詳しくは、[パスワード ライトバックのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr-writeback)に関する記事を参照してください。

#### パスワード ライトバックが機能するにはどれくらいの時間がかかりますか。 パスワード ハッシュ同期のような同期遅延がありますか。

>
> パスワード ライトバックは即座に実行されます。 これは、パスワード ハッシュ同期とは動作が異なる同期パイプラインです。 パスワード ライトバックでは、ユーザーは、パスワードのリセット操作または変更操作の完了についてリアルタイムのフィードバックを受け取ります。 正常なパスワード ライトバックの平均時間は 500 ミリ秒未満です。

#### オンプレミスのアカウントが無効にされている場合、クラウド アカウントとアクセスにどのような影響がありますか。

>
> お客様のオンプレミスの ID が無効にされている場合、Microsoft Entra Connect による次回の同期間隔で、お客様のクラウド ID とアクセスも無効になります。 既定では、この同期は 30 分ごとに行われます。

#### オンプレミスのアカウントがオンプレミスの Active Directory パスワード ポリシーによって制約されている場合に、パスワードを変更すると、SSPR はこのポリシーに従いますか。

>
> はい。SSPR は、オンプレミスの Active Directory パスワード ポリシーに依存し、これに従います。 このポリシーには、一般的な Active Directory ドメイン パスワード ポリシーと、ユーザーを対象とする定義済みのきめ細かいパスワード ポリシーが含まれます。

#### パスワード ライトバックはどの種類のアカウントで動作しますか。

>
> パスワード ライトバックが動作するのは、オンプレミスの Active Directory から Microsoft Entra ID に対して同期化されるユーザー アカウントです (フェデレーション ユーザー、パスワード ハッシュ同期ユーザー、パススルー認証ユーザーなど)。

#### パスワード ライトバックでは、ドメインのパスワード ポリシーが適用されますか。

>
> はい。 パスワード ライトバックでは、ローカル ドメインのパスワードにユーザーが設定したパスワードの有効期間、履歴、複雑さ、フィルター、その他の制限が適用されます。

#### パスワード ライトバックはセキュリティで保護されていますか。 ハッキングされないようにするにはどうすればよいですか。

>
> はい。パスワード ライトバックはセキュリティで保護されています。 パスワード ライトバック サービスによって実装される多層セキュリティについて詳しくは、[パスワード ライトバックの概要](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-writeback#password-writeback-security)の記事の[パスワード ライトバックのセキュリティ モデル](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr-writeback)に関するセクションをご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/phishing-resistant-authentication-videos"} -->
## Microsoft Entra ID でのフィッシング耐性のある認証 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/phishing-resistant-authentication-videos
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: このビデオ シリーズでは、Microsoft Entra ID を使用したフィッシング耐性のある認証方法について説明します。

このビデオ シリーズでは、Microsoft Entra ID でのフィッシング耐性のある認証方法の基本について説明します。 多要素認証は、敵対者が機密情報にアクセスするのを防ぐための最も効果的な制御の 1 つです。

以下のビデオを見るか、「[Microsoft Entra ID でのフィッシング耐性のある認証](https://www.youtube.com/playlist?list=PL3ZTgFEc7LysTnItcN7SJnJ6wpPJif2-k)」のビデオ シリーズを参照して、フィッシング耐性のある多要素認証方法をどのように構成するかに関するガイダンスを確認してください。

詳細については、[Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/)、[認定資格証ベースの認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication)、およびキーパス (旧称 [FIDO2](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2) セキュリティキー) を参照してください。 さらに、意思決定に役立つ情報をまとめ、組織のポリシーを適用する [Microsoft Entra 条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)について確認してください。

| ビデオのタイトル | Video |
| --- | --- |
| **フィッシング耐性のある認証が重要な理由** **Alex Weinert** (VP Identity and Network Access Security) が、現在の脅威の状況と、フィッシング耐性のある認証の実装が組織の優先事項である理由を説明します。 (12:40) |  |
| **[フィッシング耐性のある多要素認証の利用を開始する](https://www.youtube.com/watch?v=fSIM_Zrlv70)** **Ehud Itshaki** (Principal Product Manager) が、Microsoft Entra ID 認証方法でオープン標準を実装してフィッシング耐性のある特性を実現する方法について詳しく説明します。 フィッシングとリアルタイム フィッシング フローの意味を確認します。 (10:24) |  |
| **Microsoft Entra ID で使用できるフィッシング耐性のある多要素認証方法** **Keith Brewer** (Principal Product Manager) が、Microsoft Entra ID で使用できるフィッシング耐性のある 4 つの認証方法の概要について説明します。 (5:13) |  |
| **[Windows Hello for Business とクラウド Kerberos の信頼プロビジョニング](https://www.youtube.com/watch?v=Cqn3INyjg5s)** **Bailey Bercik** (Senior Product Manager) と **Merill Fernando** (Principal Product Manager) が、Windows Hello for Business とクラウド Kerberos デプロイ モデルを使用してデプロイを簡単にする方法について説明します。 (8:08)**[パスワードレス認証用に Windows Hello for Business を構成する](https://www.youtube.com/watch?v=5LJIv4-034E)** **Merill Fernando** (Principal Product Manager) と **Bailey Bercik** (Senior Product Manager) が、Windows Hello for Business クラウド Kerberos モデルのデプロイについて説明します。 (8:48) |  |
| **[Microsoft 証明書ベースの認証を構成する](https://www.youtube.com/watch?v=R9_z7J4Q0M8)** **Nick Wryter** (Principal Product Manager) と **Vimala Ranganathan** (Principal Product Manager) が、Active Directory フェデレーション サービス (ADFS) などのフェデレーション ID プロバイダーをデプロイ、セキュリティ保護、管理する必要がない、Microsoft Entra ID の証明書ベースの認証 (CBA) について説明します。 (6:53)**[Microsoft Entra 証明書ベースの認証のユーザー エクスペリエンスを構成する](https://www.youtube.com/watch?v=g3rR2Cqb75s)** **Vimala Ranganathan** (Principal Product Manager) が、エンド ユーザー エクスペリエンスを含めて、Microsoft Entra 証明書ベースの認証 (CBA) の構成に関するガイダンスを提供します。 (4:55) |  |
| **[Microsoft Entra 条件付きアクセスの認証強度](https://www.youtube.com/watch?v=S5cELyuZve8)** **Grace Picking** (Senior Product Manager) と **Inbar Cizer Kobrinsky** (Principal Product Manager) が、認証の強度と、それが組織でフィッシング耐性のある認証の使用を強制するうえでどのように役立つかについて説明します。 (13:24)**[条件付きアクセスの認証強度ポリシーを構成する](https://www.youtube.com/watch?v=-w4YHCQIWz4)** **Inbar Cizer Kobrinsky** (Principal Product Manager) と **Grace Picking** (Senior Product Manager) が、条件付きアクセスでの認証強度のしくみに関する分析情報を提供します。 (6:53) |  |
| [**Microsoft Entra ID のパスキーの概要**](https://www.youtube.com/watch?v=zxf75zF91dY)**Calvin Lui** (Product Manager) と **Mayur Santani** (Product Manager) が Microsoft Entra ID のパスキーとその構成方法について説明します。 [Microsoft Entra ID のパスキーの概要](https://www.youtube.com/watch?v=zxf75zF91dY)のビデオでは、仕事用のパスキーについて詳述しています。 組織がフィッシング耐性のある未来を導くためにパスキーがどのように役立つかについて説明します (11:16)。 |  |
| [**Microsoft Entra ID でパスキーを構成する方法**](https://www.youtube.com/watch?v=jIZBP7tG5I8)**Calvin Lui** (Product Manager) と **Mayur Santani** (Product Manager) が Microsoft Entra ID のパスキーとその構成方法について説明します。 「[Microsoft Entra ID でパスキーを構成する方法](https://www.youtube.com/watch?v=jIZBP7tG5I8)」のビデオでは、Calvin と Mayur がパスキーの設定方法とアカウントでの使用方法について説明します。 Microsoft Authenticator アプリでのパスキーの登録と認証のエクスペリエンスおよび FIDO2 セキュリティキーでのパスキーについて説明しています (7:10)。 |  |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/phone-providers-faq"} -->
## Microsoft Entra IDのテレフォニー プロバイダーに関してよく寄せられる質問 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/phone-providers-faq
- Service: entra-id / authentication
- Article date: 2026-09-20
- Summary: Microsoft Entra IDに対する独自のテレフォニー プロバイダーの選択、構成、評価、サポートに関する一般的な質問に対する回答を見つけます。

Microsoft Entra IDで独自のテレフォニー プロバイダーを選択する方法についてよく寄せられる質問を次に示します。 ここで回答できない質問については、[Microsoft Entra ID の Microsoft Q&A 質問ページ](https://learn.microsoft.com/ja-jp/answers/topics/azure-active-directory.html)のコミュニティにお問い合わせください。

この FAQ では、次の内容について説明します。

- プロバイダーの選択と可用性
- コストと課金
- 移行と評価
- サポートとトラブルシューティング

### プロバイダーの選択と可用性

#### 独自のテレフォニー プロバイダーをいつ選択できますか?

プライベート プレビューに参加しているテレフォニー プロバイダーに関する情報が利用可能になりました。 構成エクスペリエンスは、2026 年 10 月 30 日以降に利用可能になります。

構成エクスペリエンスが使用可能になるまで、サポートされているプロバイダー オプションを確認し、Microsoft管理された SMS および音声認証からの移行の計画を開始します。

#### プライベート プレビュー中に利用できるテレフォニー プロバイダーはどれですか?

Soprano と Telesign は、プライベート プレビュー中に利用できる初期テレフォニー プロバイダーです。 一般提供により、より多くのプロバイダーが利用できるようになります。

これらのプロバイダーの価格やその他の商用の詳細は、Microsoft Security ストアで入手できます。

#### 既存の SMS プロバイダーを使用できますか?

はい。サポートされているテレフォニー プロバイダーの 1 つである場合は、既存のプロバイダーを使用できます。 現在のプロバイダーがサポートされていない場合は、サポートされているプロバイダーを選択して、Microsoftマネージド テレフォニーが廃止された後も SMS または音声認証を使用し続ける必要があります。

#### [Choose Your Own テレフォニー プロバイダー] を使用して、SMS サインインをプライマリ認証方法として保持することはできますか?

No. この機能では、プライマリ認証方法としての SMS サインインはサポートされていません。 プライマリ認証方法としての SMS サインインの廃止は、Choose Your Own テレフォニー プロバイダーを使用している場合でも適用されます。 組織が現在、プライマリ認証に SMS サインインを使用している場合は、シナリオに基づいて、サポートされている代替手段にユーザーを移行します。 代わりに、パスキー、QR コード認証、FIDO2 セキュリティ キー、およびMicrosoft Entra IDでサポートされているその他の認証方法があります。

#### これは、MICROSOFT ENTRA ID、Microsoft Entra 外部 ID、Azure AD B2C で動作しますか?

[独自のテレフォニー プロバイダーの選択] は現在、Microsoft Entra IDでのみ使用できます。 Microsoft Entra 外部 IDとAzure AD B2C は含まれません。

#### 複数のテレフォニー プロバイダーを構成できますか?

2026 年 10 月 30 日以降、チャネルごとに 1 つのテレフォニー プロバイダー (SMS 用のプロバイダーと音声用のプロバイダー) を構成できます。

### コストと課金

#### 独自のテレフォニー プロバイダーの選択コストはどのくらいですか?

主なコストは、プロバイダーの価格と使用状況に基づいて、選択したテレフォニー プロバイダーが SMS および音声メッセージに対して課金する料金です。 この機能では、Azure サブスクリプションにデプロイされたルーティング機能を使用して、選択したテレフォニー プロバイダーにMicrosoft Entra IDを安全に接続します。 ルーティング機能には標準のAzure消費料金が適用されますが、ほとんどのお客様はテレフォニー プロバイダーの料金と比較して、これらのコストが最小限に抑えられると予想されます。

利用可能なプロバイダー オファーの価格とその他の商用の詳細を確認します。

- ソプラノ: Microsoft Security ストアでの[ユーザー](https://securitystore.microsoft.com/solutions/sopranodesignlimited1620113206416.soprano_entraid_per_user)[ごとのオファーまたはトランザクションごとのオファー](https://securitystore.microsoft.com/solutions/sopranodesignlimited1620113206416.soprano_entraid_per_transaction)。
- Telesign: Microsoft Security Store の[Microsoft Entra 向け Telesign Verify](https://securitystore.microsoft.com/solutions/telesigncorporation1779799505747.telesign-verify-cyot-azure)。

### 移行と評価

#### 移行するにはどうすればよいですか？

Choose Your Own テレフォニー プロバイダーは、2026 年 10 月 30 日以降に利用可能になります。 使用可能になったら、Microsoft Entra 管理センターから機能を構成します。

移行には通常、次の手順が含まれます。

1. サポートされているテレフォニー プロバイダーを選択し、そのプロバイダーとのアカウントを確立します。
2. Azure サブスクリプションにルーティング関数をデプロイします。 ルーティング関数は、Microsoft Entra IDからプロバイダーに認証要求を安全に送信します。
3. SMS、音声、またはその両方にプロバイダーを使用するユーザーまたはグループを選択します。
4. ユーザーに影響を与える前に、統合を評価します。
5. 本番環境の認証トラフィックに対して、プロバイダを段階的に有効化します。

この機能がリリースされると、詳細な構成とデプロイの手順を使用できるようになります。 2027 年 2 月 1 日にMicrosoftマネージド テレフォニーが廃止される前に移行を完了して、テストと段階的な運用ロールアウトに十分な時間を確保します。

#### ユーザーを新しいプロバイダーに切り替える前に評価できますか?

Yes. 評価モードでは、ユーザーの認証エクスペリエンスを変更することなく、認証要求がテレフォニー プロバイダーに正常にルーティングされたことを検証できます。

ユーザーは、評価結果を確認しながら、現在アクティブな配信方法を通じて認証メッセージを受け取り続けます。 統合が期待どおりに動作していることを確認したら、運用トラフィックに対してプロバイダーを有効にし、ロールアウトを徐々に展開できます。

### サポートとトラブルシューティング

#### サポート モデルとは

Microsoftは、テレフォニー プロバイダーの構成エクスペリエンス、認証ワークフロー、Microsoft Entra IDとテレフォニー プロバイダー間のルーティングなど、Microsoft Entra ID プラットフォームのサポートを提供します。

組織は、プロバイダー アカウントの構成、サービスの可用性、メッセージ配信、SMS または音声配信に関連する問題など、選択したテレフォニー プロバイダーの関係を管理する責任を負います。 プロバイダーまたはメッセージ配信の問題については、プロバイダーのサポート チームにお問い合わせください。

Microsoftでは、問題がMicrosoft Entra ID プラットフォームとテレフォニー プロバイダーの統合のいずれに関連しているかを判断するのに役立つ診断とトラブルシューティングのガイダンスを提供します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/self-service-account-recovery"} -->
## Microsoft Entra IDでのアカウントの回復に関してよく寄せられる質問 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/self-service-account-recovery
- Service: entra-id / authentication
- Article date: 2026-08-14
- Summary: セットアップ、ID 検証プロファイル、カスタム認証拡張機能、トラブルシューティングなど、Microsoft Entra ID アカウントの回復に関する一般的な質問に対する回答を確認します。

Microsoft Entra IDでのアカウントの回復に関してよく寄せられる質問を次に示します。 ここで回答できない質問については、[Microsoft Entra ID の Microsoft Q&A 質問ページ](https://learn.microsoft.com/ja-jp/answers/topics/azure-active-directory.html)のコミュニティにお問い合わせください。

この FAQ では、次の内容について説明します。

- アカウントの回復のしくみ
- ID 検証プロファイル
- アカウント検証とカスタム認証拡張機能
- ID 検証プロバイダー (IDV) の選択と使用状況
- Troubleshooting

### アカウントの回復のしくみ

#### アカウントの回復を使用するために必要なライセンスは何ですか?

アカウントの回復を使用するには、[Microsoft Entra ID P1 ライセンス](https://www.microsoft.com/security/business/microsoft-entra-pricing)が必要です。 [Face Check ライセンス](https://learn.microsoft.com/ja-jp/entra/verified-id/using-facecheck)も、Entra スイートまたはスタンドアロンで必要です。 ID 検証プロバイダーのコストは、Microsoft Security ストアでサブスクライブするオファーによって異なります。

#### ID 検証プロセス中、ユーザー データはどこに移動しますか?

ID 検証プロバイダーには、Microsoft Security ストアからの検証オファーの一部として、独自のプライバシーポリシーとデータ保持ポリシーがあります。 詳細については、プロバイダーのドキュメントを参照してください。

#### 検証済み ID は、Microsoft Entra ID アカウントの詳細とどのように照合されますか?

アカウントの回復では、まず、ユーザーの政府発行 ID の写真をリアルタイムの [Face Check](https://learn.microsoft.com/ja-jp/entra/verified-id/using-facecheck) と比較して、プレゼンスの証明を検証します。 次に、システムは検証済みの政府機関の ID からの名と姓の属性を、Microsoft Entra ID のユーザーの **First name** および **Last name** プロファイル プロパティと照合します。 この 2 つのプロパティのみが照合に使用されます。 **表示名** と **ユーザー プリンシパル名** は、アカウント回復照合プロセスでは使用されません。

管理者は、各 ID 検証プロファイルの一致信頼レベルを構成できます。

- **Exact** — 検証済み資格情報の名と姓は、ユーザーの **First name** および **Last name** プロパティと完全に一致する必要があり、Microsoft Entra IDに一致しなければなりません。
- **Relaxed** — クロスフィールド ワード マッチングを使用して、政府発行のドキュメントとMicrosoft Entra IDユーザー プロファイルでの名前の表示方法のバリエーションに対応します。

**リラックスしたマッチングのしくみ**

政府発行のドキュメント (特にパスポート) には、名と姓を区別せずに、1 つのフィールドにユーザーの完全な法的名が含まれている場合があります。 一部の ID 検証プロバイダーは、氏名全体を検証済み資格情報の名もしくは姓のフィールドに 記載することがあります。 さらに、ユーザーのMicrosoft Entra ID プロファイルには、完全な法的名に含まれる名前のサブセットのみが含まれる場合があります。 たとえば、パスポートに "Maria Elena Garcia Lopez" と読み込まれるユーザーは、**First 名**として "Maria" のみを持ち、Microsoft Entra ID プロファイルに **Last name** として "Garcia" のみを持つことができます。

これらのバリエーションを処理するために、緩やかな一致では次のロジックが使用されます。

- Microsoft Entra IDの**First name**プロパティは、検証された資格情報の名または姓のクレーム内の**いずれかの単語**と一致する必要があります。
- **AND** Microsoft Entra ID の **Last name** プロパティは、検証された資格情報の名または姓の要求に含まれる**いずれかの単語** と一致する必要があります。
- 一致が成功するには、両方の条件が True である必要があります。

上の例では、"Maria" は検証済みの資格情報の名要求 ("Maria Elena") の単語と一致し、"Garcia" は姓要求 ("Garcia Lopez") の単語と一致するため、Microsoft Entra ID プロファイルに完全な法的名のサブセットのみが含まれている場合でも、一致は成功します。

この方法では、検証された資格情報とMicrosoft Entra IDの間で名前が異なる方法で分割されている場合、またはユーザー プロファイルに政府発行のドキュメントに完全な法的名のサブセットが含まれている場合に、一致を成功させます。

いずれかの信頼レベルで名前の一致が失敗した場合、ユーザーは回復を完了できず、通常のヘルプデスク プロセスに従う必要があります。

名前の照合を超えてより強力な検証を必要とする組織の場合、 カスタム認証拡張機能 では、組織データに対する追加の要求を検証できます。

#### 同じ名前のユーザーがアカウントを回復しようとするとどうなりますか?

管理者は、カスタム認証拡張機能を使用して検証プロバイダーからの ID 要求を処理し、HRIS システムや従業員ディレクトリなどの組織データと比較して、似た名前のユーザー間であいまいさを解消できます。

カスタム認証拡張機能がない場合、別のユーザーと同じ名前のユーザーからの回復試行がブロックされる可能性があります。 これを回避するには、ID 検証プロファイルでカスタム認証拡張機能を有効にし、姓と名を超える追加の属性を検証します。

#### アカウントの回復に一時的なアクセス パス (TAP) を使用する必要がありますか?

Yes. このリリースでは、アカウントの回復には TAP が必要です。

#### ユーザーがデバイスにMicrosoft Authenticatorしていない場合はどうなりますか?

回復フロー中、ID 検証プロバイダーは、必要に応じてアプリをダウンロードするようユーザーに通知します。 Microsoft Authenticatorインストールする必要があるだけです。ユーザーは、プロバイダーによって発行された検証済み ID を格納するためにサインインする必要はありません。

#### アカウントの回復中に条件付きアクセス ポリシーは適用されますか?

Yes. テナントの条件付きアクセス ポリシーは、アカウントの復旧中に適用できます。 ポリシーによってアクセスがブロックされた場合、または準拠しているデバイス、信頼できる場所、多要素認証などの制御が必要な場合、ユーザーの回復試行が中断またはブロックされる可能性があります。

アカウントの回復をより広範にロールアウトする前に、少数のユーザー グループで条件付きアクセス ポリシーを確認してテストします。

#### 組織内のすべてのユーザーに対してアカウントの回復を有効にする必要がありますか?

アカウントの回復では、ID 検証プロファイルを使用した段階的なロールアウトがサポートされます。 各プロファイルは特定のユーザー グループを対象としているため、評価モードで小さなテスト グループから始めて、自信を持って拡張することができます。

推奨される方法:

- 小さなテスト グループを対象とする **評価** モードでプロファイルを作成し、ID 検証フローを検証します。
- テストでフローがユーザーに対して機能することを確認したら、プロファイルを **運用** モードに切り替えます。
- 必要に応じて、より広範なユーザー集団に対して追加のプロファイルを作成します。

CEO や財務管理者に属するアカウントなど、特定のアカウントでは、回復を、ループに人間を含む対人プロセスまたはリモート プロセスに制限する場合があります。 これらのアカウントは、機密性の高い性質のため、セルフサービスの復旧には適さない場合があります。

#### 組織全体のアカウント回復のコストを見積もる方法

アカウント回復要求は、通常、毎月約 1%–3% のユーザーに影響します。 Microsoft Entra 管理センターの**コスト削減推定ツール** (**Entra ID**&gt;**Account recovery**) を使用すると、潜在的な節約額を見積もるのに役立ちます。 現在のヘルプデスクのコスト、ターゲット ユーザー数、選択したプロバイダーからの検証あたりのコストを入力します。 見積もりツールは、現在の支出と予測されるセルフサービス復旧コストを比較して、シナリオの節約をモデル化できるようにします。

### ID 検証プロファイル

#### ID 検証プロファイルとは

ID 検証プロファイルは、特定のユーザー グループに対する ID 検証の設定方法を定義する構成オブジェクトです。 プロファイルは、検証を実行する ID 検証プロバイダー、スコープ内のユーザー、およびアカウント検証の処理方法を制御します。 現在、ID 検証プロファイルはアカウントの回復を構成するために使用されており、将来の追加の ID 検証シナリオをサポートするように設計されています。

各プロファイルは次を指定します。

- **ユーザー グループ スコープ** - プロファイルが適用されるユーザー(含まれるグループと除外されたグループによって定義されます)
- **ID 検証プロバイダー - ID** 証明を実行するサード パーティのプロバイダー
- **アカウント検証規則** - 一致の信頼度やオプションのカスタム認証拡張機能など、ID 要求の照合方法
- **回復モード** - アカウント回復シナリオの場合: 評価 (テスト) または運用 (完全復旧)

プロファイルは、**Entra ID**&gt;**Account recovery**&gt;**Profiles** の下にあるMicrosoft Entra 管理センターのウィザードを使用して作成されます。

#### 複数の ID 検証プロファイルを作成できますか?

Yes. 組織は複数のプロファイルを作成して、構成が異なるさまざまなユーザー集団をサポートできます。 例えば次が挙げられます。

- 厳密に一致する 1 つの ID 検証プロバイダーを使用する企業従業員のプロファイル
- 別のサービスプロバイダーを利用する現場作業者向けで、マッチング基準が緩和されているプロファイル
- 新しいプロバイダーをテストするパイロット グループの評価モードのプロファイル

各プロファイルは、独自のユーザー スコープ、プロバイダー、検証規則、およびモード設定で個別に動作します。

#### 評価モードと運用モードとは

各 ID 検証プロファイルは、次の 2 つのモードのいずれかで動作します。

- **評価** — ユーザーは、ID 検証フローをテストして、正しく動作することを確認できます。 アカウントは、このモードでは回復 **されません** 。 完全復旧を有効にする前に、Evaluation を使用してエクスペリエンスを検証します。
- **運用** - ID 検証を完了したユーザーは、アカウントを完全に回復し、一時的なアクセス パスを受け取り、認証方法を再登録できます。

Microsoft Entra 管理センターでプロファイルを編集することで、いつでもプロファイルを評価から運用に切り替えることができます。

#### ユーザーが複数のプロファイルと一致する場合に優先順位を設定するにはどうすればよいですか?

Microsoft Entra 管理センターの **Profiles** タブには、**Set priority** オプションが含まれています。 ユーザーが複数のプロファイルに一致するグループに属している場合、システムは優先順位でプロファイルを評価し、最初に一致するプロファイルを適用します。

### アカウント検証とカスタム認証拡張機能

#### 利用可能な一致度信頼レベルはどれですか?

名前の照合には、次の 2 つの一致信頼度レベルを使用できます。

- **Exact** — ID 検証プロバイダーの名と姓は、ユーザーのMicrosoft Entra ID プロファイル プロパティと正確に一致する必要があります。
- **緩い** — 省略形や音訳などの違いを考慮するために、名前の一致の小さなバリエーションを許可します。

一致の信頼度は、ID 検証プロファイルごとに構成されます。

#### カスタム認証拡張機能とアカウントの回復のしくみ

カスタム認証拡張機能は、復旧プロセス中に組織固有のアカウント検証ロジックを追加します。 ユーザーが ID 検証を完了すると、プロバイダーからの検証済み要求が組織のエンドポイント (Azure関数、ロジック アプリ、または REST API) に渡されます。これは、次のような権限のあるデータ ソースに対して検証します。

- HRIS システム (従業員レコード)
- 従業員ディレクトリ
- バッジ管理システム

拡張機能は、アカウント復旧フローに対して一致結果を返します。 これにより、姓と名の照合以外の検証が可能になり、類似した名前を持つユーザーのあいまいさを解消できます。

Important

カスタム認証拡張機能によって処理されるすべてのデータは、組織の信頼境界内に留まります。 組織のデータはMicrosoftと共有されません。一致した結果のみがアカウントの回復に返されます。

#### カスタム認証拡張機能を設定するには何が必要ですか?

アカウントの回復でカスタム認証拡張機能を使用するには、次のものが必要です。

- 検証済みの要求を受け取り、一致の決定を返すことができるAzure関数、ロジック アプリ、または REST API エンドポイント
- 組織の権限のあるデータ ソース (HRIS、従業員ディレクトリなど) へのアクセス
- Microsoft Entra IDでカスタム認証拡張機能として登録されたエンドポイント

ID 検証プロファイル ウィザードの **アカウント検証** 手順で拡張機能を有効にします。

### ID 検証プロバイダー (IDV) の選択と使用状況

#### 自分の IDV と契約をアカウント回復に持ち込むことはできますか?

アカウントの回復は、[Microsoft Security Store](https://securitystore.microsoft.com/) を通じて確認および承認されたプロバイダーのみをサポートします。

#### 復旧フローで独自の IDV を使用できますか?

現在、アカウントの回復では、Microsoft Security ストアからの ID 検証プロバイダーのみがサポートされています。 これにより、ユーザーの復旧フローの一貫性が確保されます。

#### プロバイダーの検証済み ID が既にある場合、IDV ドキュメントの検証をスキップできますか?

最も一般的な総ロックアウト回復シナリオでは、ユーザーは新しいデバイス上にあるため、ウォレットに既存の検証済み ID がありません。 アカウントの回復では、この最も一般的なケースに焦点を当てています。復旧中にプロバイダーから新しい検証済み ID を取得します。

#### IDV によってユーザーの政府 ID が拒否された場合、ユーザーは何を行う必要がありますか?

IDV のサインアップとプロビジョニング プロセスの一環として、テナントはプロバイダーのサポート チームと協力して個々のユーザーの特定の ID 発行エラーをデバッグするための連絡先の指示を受け取ります。

#### IDV フローでは、モバイル 運転免許証、ヨーロッパデジタル ID (EUDI)、または電子 ID (eID) がサポートされていますか?

現在、アカウント回復では、Microsoft Entra Verified IDを使用した標準の検証済み資格情報によるユーザー検証がサポートされています。

### Troubleshooting

#### 管理者監査ログに Core Directory の "アプリケーション管理" エラーが表示されます。

これらのエラーはアカウントの回復とは無関係であり、無視しても問題ありません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/troubleshoot-authentication-strengths"} -->
## 条件付きアクセスの認証強度をトラブルシューティングする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/troubleshoot-authentication-strengths
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra 条件付きアクセス認証の強度を使用しているときにエラーを解決する方法について説明します。

この記事では、Microsoft Entra 条件付きアクセス認証の強度を使用するときに発生する可能性がある問題を解決する方法について説明します。

### ユーザーは別の方法でサインインするように求められますが、予期されるメソッドは表示されません

サインインの場合、認証方法は次のようにする必要があります。

- ユーザーが登録されました。
- 認証方法のポリシーによって有効になります。

詳細については、「 [条件付きアクセス認証の強度のしくみ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strength-how-it-works)」を参照してください。

メソッドを使用できることを確認するには:

1. 必要な認証強度を確認します。 [ **セキュリティ**&gt;**認証方法**&gt;**認証の強度**を選択します。
2. 認証方法のポリシーを確認して、認証強度が必要な方法に対してユーザーが有効になっているかどうかを確認します。 **[セキュリティ**&gt;**認証方法**&gt;ポリシー] を選択**します**。
3. 必要に応じて、認証強度が必要な方法でテナントが有効になっているかどうかを確認します。 [ **セキュリティ**&gt;**多要素認証**&gt;**追加クラウドベースの多要素認証設定**を選択します。
4. 認証方法のポリシーでユーザーに登録されている認証方法を確認します。 **ユーザーとグループ**&gt;*ユーザー名*&gt;**認証メソッドを選択します**。

ユーザーが認証強度を満たす有効な方法に登録されている場合、プライマリ認証後に使用できない別の方法 (Windows Hello for Business など) を使用することが必要になる場合があります。 詳細については、「 [Microsoft Entra ID の認証方法」を](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)参照してください。 ユーザーはセッションを再起動し、[ **サインイン オプション**] を選択し、認証強度に必要な方法を選択する必要があります。

[Image: 別のサインイン方法を選択するためのダイアログのスクリーンショット。]

### ユーザーがリソースにアクセスできない

認証強度にユーザーが使用できない方法が必要な場合、ユーザーはサインインをブロックされます。 認証の強度が必要な方法と、ユーザーが登録して使用できるようにする方法を確認するには、 前のセクションの手順に従います。

### サインイン中に適用された認証強度を確認する必要がある

**サインイン** ログを使用してサインインの詳細を確認します。

- [ **認証の詳細** ] タブの [ **要件** ] 列に、認証強度ポリシーの名前が表示されます。

    [Image: サインイン ログの認証の強度を示すスクリーンショット。]
- [ **条件付きアクセス** ] タブで、適用された条件付きアクセス ポリシーを確認できます。 ポリシーの名前を選択し、[ **許可コントロール** ] を探して、適用された認証強度を確認します。

    [Image: サインイン ログの [条件付きアクセス ポリシーの詳細] の下の認証強度を示すスクリーンショット。]

### ユーザーがサインイン中に新しいメソッドを登録できない

サインイン中に登録できないメソッドや、統合された登録を超えるセットアップが必要なメソッドもあります。 詳細については、[パスワードレス認証の方法を登録する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strength-how-it-works#registration-of-passwordless-authentication-methods)に関する記事を参照してください。

[Image: ユーザーがメソッドを登録できない場合のサインイン エラーのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/troubleshoot-sspr"} -->
## セルフサービス パスワード リセットのトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/troubleshoot-sspr
- Service: entra-id / authentication
- Article date: 2025-06-06
- Summary: Microsoft Entra ID でのセルフサービス パスワード リセットに関する一般的な問題をトラブルシューティングする方法と解決手順について説明します

Microsoft Entra のセルフサービス パスワード リセット (SSPR) を使用すると、ユーザーはクラウド内の自分のパスワードをリセットできます。

SSPR で問題が発生した場合は、次のトラブルシューティング手順と一般的なエラーが役立つことがあります。 この短いビデオでは、[6 つの最も一般的な SSPR エンドユーザー エラー メッセージを解決する方法](https://www.youtube.com/watch?v=9RPrNVLzT8I&amp;list=PL3ZTgFEc7LyuS8615yo39LtXR7j1GCerW&amp;index=1)についても説明します。

問題に対する回答が見つからない場合は、Microsoft のサポート チームがいつでも問題の解決をお手伝いいたします。

### Microsoft Entra 管理センターでの SSPR 構成

Microsoft Entra 管理センターで、特定の SSPR オプションが表示されないか、これらのオプションを構成できない場合は、次のトラブルシューティング手順を確認してください。

#### Microsoft Entra 管理センターの [保護] に [パスワードのリセット] が表示されない

操作を実行する管理者に Microsoft Entra ID ライセンスが割り当てられていない場合、**[パスワードのリセット]** は表示されません。

管理者アカウントにライセンスを割り当てるには、「[Microsoft 365 管理センターのユーザーに対するライセンスの割り当てまたは割り当て解除」を](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/assign-licenses-to-users?view=o365-worldwide&preserve-view=true)参照してください。

#### 特定の構成オプションが表示されません

UI の多くの要素は、必要になるまで表示されません。 特定の構成オプションを探す前に、オプションが有効になっていることを確認してください。

#### [オンプレミスの統合] タブが表示されません

オンプレミスのパスワード ライトバックは、Microsoft Entra Connect をダウンロードして構成した場合にのみ表示されます。

詳細については、「[Getting started with Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-express) (Microsoft Entra Connect の概要)」を参照してください。

### SSPR レポート

Microsoft Entra 管理センターの SSPR レポートで問題が発生した場合は、次のトラブルシューティングの手順を確認してください。

#### 統合された登録の [方法の追加] オプションで無効にした認証方法が表示される。

統合された登録では、 **[方法の追加]** に表示される方法を決定するにあたり、次の 3 つのポリシーが考慮されます。

- [セルフサービス パスワード リセット](https://portal.azure.com/#blade/Microsoft_AAD_IAM/ActiveDirectoryMenuBlade/PasswordReset)
- [MFA](https://account.activedirectory.windowsazure.com/UserManagement/MfaSettings.aspx)
- [認証方法](https://portal.azure.com/#blade/Microsoft_AAD_IAM/AuthenticationMethodsMenuBlade/AdminAuthMethods)

アプリの通知を SSPR では無効にしたにもかかわらず MFA ポリシーでは有効にした場合、統合された登録にそのオプションが表示されます。 別の例として、ユーザーが SSPR で **Office 電話** を無効にした場合でも、ユーザーが **Phone/Office** 電話プロパティを設定していると、それはオプションとして引き続き表示されます。

#### [セルフサービスのパスワード管理] 監査イベント カテゴリに、パスワード管理アクティビティの種類が表示されない

操作を実行する管理者に、Microsoft Entra ID ライセンスが割り当てられていません。

該当する管理者アカウントにライセンスを割り当てるには、[Microsoft 365 管理センターのユーザーのライセンスの割り当てまたは割り当て解除を](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/assign-licenses-to-users?view=o365-worldwide&preserve-view=true)参照してください。

#### ユーザー登録に複数の時刻が表示されます

ユーザーが登録すると、現在、個別のイベントとして登録された個々のデータがログに記録されます。

このデータを集計してデータの表示方法をより柔軟なものにするには、レポートをダウンロードして Excel のピボット テーブルでデータを開きます。

### SSPR 登録ポータル

ユーザーが SSPR の登録を行うときに問題が発生した場合は、次のトラブルシューティングの手順を確認してください。

#### このディレクトリでは、パスワードのリセットが有効になっていません。 "管理者がこの機能を使用することを許可していません" というエラーがユーザーに表示されることがあります。

SSPR は、すべてのユーザーまたは選択したグループのユーザーに対して有効にするか、すべてのユーザーに対して無効にすることができます。 現在、Microsoft Entra 管理センターを使用して SSPR に対して有効にできる Microsoft Entra グループは 1 つだけです。 SSPR のより広範な展開の一環として、入れ子になったグループがサポートされています。 選択したグループ内のユーザーに適切なライセンスが割り当てられていることを確認してください。

Microsoft Entra 管理センターで、**[セルフサービス パスワード リセットが有効]** の構成を *[選択]* または *[すべて]* に変更して、**[保存]** を選択します。

#### ユーザーに Microsoft Entra ID のライセンスが割り当てられていません。 "管理者がこの機能を使用することを許可していません" というエラーがユーザーに表示されることがあります。

現在、Microsoft Entra 管理センターを使用して SSPR に対して有効にできる Microsoft Entra グループは 1 つだけです。 SSPR のより広範な展開の一環として、入れ子になったグループがサポートされています。 選択したグループ内のユーザーに適切なライセンスが割り当てられていることを確認してください。 前のトラブルシューティングの手順を確認し、必要に応じて SSPR を有効にします。

また、トラブルシューティングの手順を確認して、構成オプションの設定を実行している管理者にライセンスが割り当てられていることを確認します。 該当する管理者アカウントにライセンスを割り当てるには、手順に従って、[Microsoft 365 管理センターのユーザーのライセンスを割り当てるか、割り当てを解除](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/assign-licenses-to-users?view=o365-worldwide&preserve-view=true)します。

### SSPR の使用

SSPR に関する問題を解決するには、以下の手順を確認します。

| Error | ソリューション |
| --- | --- |
| このディレクトリでは、パスワードのリセットが有効になっていません。 | Microsoft Entra 管理センターで、**[セルフサービス パスワード リセットが有効]** の構成を *[選択]* または *[すべて]* に変更して、**[保存]** を選択します。 |
| ユーザーに Microsoft Entra ID のライセンスが割り当てられていません。 | Microsoft Entra ID ライセンスが、対象のユーザーに割り当てられていません。 該当する管理者アカウントにライセンスを割り当てるには、手順に従って、[Microsoft 365 管理センターのユーザーのライセンスを割り当てるか、割り当てを解除](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/assign-licenses-to-users?view=o365-worldwide&preserve-view=true)します。 |
| ディレクトリでパスワードのリセットが有効になっていますが、ユーザーの認証情報が見つからないか、認証情報の形式が正しくありません。 | ユーザー アカウントがディレクトリ内のファイルに正しい形式の連絡先データを指定していることを確認します。 詳しくは、[Microsoft Entra のセルフサービス パスワード リセット で使われるデータ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-authenticationdata)に関するページをご覧ください。 |
| ディレクトリでパスワードのリセットが有効になっていますが、ポリシーが 2 つの検証方法を要求するように設定されている場合に、ユーザーがファイルに 1 つの連絡先データしか指定していません。 | ユーザーが少なくとも 2 つの連絡方法を正しく構成していることを確認します。 たとえば、携帯電話番号*と*会社の電話番号などです。 |
| ディレクトリでパスワードのリセットが有効になっており、ユーザーが正しく構成されていますが、ユーザーに連絡できません。 | 一時的なサービス エラーが発生したか、連絡先データが間違っていて正しく検出できません。  ユーザーが 10 秒間待機すると、[もう一度お試しください] と [管理者に問い合わせてください] のリンクが表示されます。 [もう一度お試しください] を選択した場合は、呼び出しが再試行されます。 ユーザーが [管理者に連絡してください] を選択すると、そのユーザーのアカウントに対してパスワードのリセットを実行するよう管理者に要求するフォーム メールが送信されます。 |
| 低速なコンピューターでは、パスワードのリセットを再試行することが必要になる場合があります。 | 低速なコンピューターで [ **パスワードのリセット** ] をクリックしても、パスワード リセット ダイアログが表示されない場合は、パスワードが表示されるまで再試行してください。 |
| ユーザーがパスワードのリセットの SMS または電話を受け取っていません。 | ディレクトリ内の電話番号の形式が正しくない可能性があります。 電話番号の形式が "+1 4251234567" となっていることをご確認ください。 ディレクトリ内の電話番号を指定した場合でも、パスワードのリセットは内線番号をサポートしていません。 内線番号は呼び出しが行われる前に削除されます。 内線番号のない電話番号を使用するか、構内交換機 (PBX) で電話番号と内線番号を統合してください。 |
| ユーザーがパスワードのリセットのメールを受け取っていません。 | この問題の主な原因は、メッセージがスパム フィルターによって拒否されたことである可能性があります。 スパム、迷惑メール、削除済みアイテムのフォルダーをご確認ください。  また、SSPR に登録されている正しい電子メール アカウントを確認するようにユーザーに要請してください。 |
| パスワードのリセット ポリシーを設定し、管理者アカウントでパスワードのリセットを使用しても、ポリシーが適用されません。 | マイクロソフトは、最高レベルのセキュリティを維持するために、管理者パスワード リセット ポリシーを管理、制御しています。 |
| ユーザーが 1 日に何回もパスワードの リセットを試みることはできません。 | ユーザーが短時間に何回もパスワードをリセットできないように、自動調整メカニズムが使用されています。 調整は次のシナリオで行われます。- ユーザーが 1 時間に 5 回、電話番号の検証を試みる場合。- ユーザーが 1 時間に 5 回、セキュリティの質問ゲートの使用を試みる場合。- ユーザーが 1 時間に 5 回、同じユーザー アカウントのパスワードのリセットを試みる場合。- この問題が発生した場合、ユーザーは最後に試行したときから 24 時間待つ必要があります。 そのあと、ユーザーはパスワードをリセットできるようになります。 |
| ユーザーが自分の電話番号を検証するときにエラーが表示されます。 | このエラーは、入力した電話番号がファイルの電話番号と一致しない場合に発生します。 電話ベースの方法でパスワードのリセットを試みる場合は、ユーザーが国番号と市外局番を含む完全な電話番号を入力していることを確認します。 |
| ユーザーが自分のメール アドレスを使用しているときにエラーが表示されます。 | UPN がユーザーのプライマリ ProxyAddress/SMTPAddress と異なる場合、テナントの [\[代替ログイン ID としてメール アドレスを使用して Microsoft Entra ID にサインインする\]](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-use-email-signin) 設定が有効になっている必要があります。 |
| 要求の処理中にエラーが発生しました。 | 一般的な SSPR 登録エラーはさまざまな問題が原因で発生することがありますが、一般的にサービスの停止や構成の問題が原因で発生します。 SSPR 登録処理を再試行したときに、この一般的なエラーが引き続き表示される場合は、Microsoft サポートに問い合わせて、追加のサポートを要請してください。 |
| オンプレミスのポリシーの違反 | パスワードは、オンプレミスの Active Directory のパスワード ポリシーを満たしていません。 ユーザーは、複雑さまたは強度の要件を満たすパスワードを定義する必要があります。 |
| パスワードがあいまいポリシーに準拠していません | 使用されたパスワードは[禁止パスワード リスト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad#how-are-passwords-evaluated)に表示され、使用することはできません。 ユーザーは、禁止パスワード リストのポリシーを満たしているかそれ以上の強度のパスワードを定義する必要があります。 |

### ユーザーに表示される可能性のある SSPR エラー

次のエラーと技術的な詳細は、SSPR 処理の一環としてユーザーに表示される場合があります。 多くの場合、SSPR 機能を有効にしたり、構成したり、アカウントを登録したりする必要があるため、ユーザーが自分で解決できるエラーではありません。

次の情報を使用して、問題と、Microsoft Entra テナントまたは個々のユーザー アカウントで修正する必要があることを理解してください。

| Error | 説明 | 技術的な詳細 |
| --- | --- | --- |
| TenantSSPRFlagDisabled = 9 | 申し訳ありません。管理者が組織でのパスワードのリセットを無効にしているため、現時点ではパスワードをリセットできません。 この状況を解決するために実行できるアクションはこれ以上ありません。 管理者に連絡して、この機能を有効にするように依頼してください。詳細については、[Microsoft Entra パスワードを忘れた場合](https://support.microsoft.com/account-billing/reset-your-work-or-school-password-using-security-info-23dde81f-08bb-4776-ba72-e6b72b9dda9e#common-problems-and-their-solutions)に関する記事をご覧ください。 | SSPR\_0009: 管理者がパスワードのリセットを有効にしていないことが検出されました。 管理者に連絡して、組織のパスワードのリセットを有効にするように依頼してください。 |
| WritebackNotEnabled = 10 | 申し訳ございません。現時点では、管理者が組織に必要なサービスを有効にしていないため、パスワードをリセットできません。 この状況を解決するために実行できるアクションはこれ以上ありません。 管理者に連絡して、組織の構成を確認するように依頼してください。この必要なサービスの詳細については、[パスワード ライトバックの構成](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr-writeback)に関する記事をご覧ください。 | SSPR\_0010: パスワード ライトバックが有効になっていないことが検出されました。 管理者に連絡して、パスワード ライトバックを有効にするように依頼してください。 |
| SsprNotEnabledInUserPolicy = 11 | 申し訳ございません。現時点では、管理者が組織のパスワード リセットを構成していないため、パスワードをリセットできません。 この状況を解決するために実行できるアクションはこれ以上ありません。 管理者に連絡して、パスワードのリセットを構成するように依頼してください。パスワードのリセット構成の詳細については、[Microsoft Entra のセルフサービス パスワード リセット のクイック スタート](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr)に関する記事をご覧ください。 | SSPR\_0011: 組織でパスワード リセット ポリシーが定義されていません。 管理者に連絡して、パスワード リセット ポリシーを定義するように依頼してください。 |
| ユーザーライセンス未取得 = 12 | 申し訳ありません。組織で必要なライセンスが不足しているため、現時点ではパスワードをリセットできません。 この状況を解決するために実行できるアクションはこれ以上ありません。 管理者に連絡して、ご自分のライセンスの割り当てを確認するように依頼してください。ライセンスの詳細については、「[Microsoft Entra のセルフサービス パスワード リセットのライセンス要件](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-licensing)」をご覧ください。 | SSPR\_0012: 組織には、パスワードのリセットを実行するために必要なライセンスがありません。 管理者に連絡して、ライセンスの割り当てを見直すように依頼してください。 |
| UserNotMemberOfScopedAccessGroup = 13 | 申し訳ございません。管理者がパスワード リセットを使用するようにアカウントを構成していないため、現時点ではパスワードをリセットできません。 この状況を解決するために実行できるアクションはこれ以上ありません。 管理者に連絡して、お使いのアカウントでパスワード リセットを構成するように依頼してください。パスワードのリセットのアカウント構成の詳細については、[ユーザーのパスワード リセットのロールアウト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-deployment)に関する記事をご覧ください。 | SSPR\_0013: パスワード リセットが有効になっているグループのメンバーではありません。 管理者に連絡して、このグループに追加してもらうよう依頼してください。 |
| ユーザーが正しく構成されていません = 14 | 申し訳ありません。お使いのアカウントでは必要な情報が不足しているため、現時点ではパスワードをリセットできません。 この状況を解決するために実行できるアクションはこれ以上ありません。 管理者に連絡して、パスワードをリセットしてもらうよう依頼してください。 ご自分のアカウントにもう一度アクセスできるようになったら、必要な情報を登録していただく必要があります。情報を登録するには、「[セルフサービス パスワード リセット を登録する](https://support.microsoft.com/account-billing/register-the-password-reset-verification-method-for-a-work-or-school-account-47a55d4a-05b0-4f67-9a63-f39a43dbe20a)」に記載されている手順に従ってください。 | SSPR\_0014: パスワードをリセットするための追加のセキュリティ情報が必要です。 続行するには、管理者に連絡して、パスワードをリセットしてもらうよう依頼してください。 ご自分のアカウントにアクセスできるようになったら、https://aka.ms/ssprsetup で追加のセキュリティ情報を登録することができます。 管理者は、[パスワードのリセットの認証データの設定と読み取り](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-authenticationdata)に関する記事に記載された手順に従い、ご利用のアカウントに追加のセキュリティ情報を追加できます。 |
| OnPremisesAdminActionRequired = 29 | 申し訳ありません。組織のパスワード リセット構成に問題があるため、現時点ではパスワードをリセットできません。 この状況を解決するために実行できるアクションはこれ以上ありません。 管理者に連絡して、調査するように依頼してください。 または現時点では、組織のパスワード リセット構成に問題があるため、パスワードをリセットできません。 この問題を解決するために実行できるアクションはこれ以上ありません。 管理者に連絡して、調査するように依頼してください。潜在的な問題の詳細については、「[パスワード ライトバックのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/authentication/troubleshoot-sspr-writeback)」をご覧ください。 | SSPR\_0029: オンプレミスの構成でエラーが発生したため、パスワードをリセットできません。 管理者に連絡して、調査するように依頼してください。 |
| OnPremisesConnectivityError = 30 | 申し訳ありません。組織への接続に問題があるため、現時点ではパスワードをリセットできません。 今すぐ実行するアクションはありませんが、後でもう一度やり直すと問題が解決される可能性があります。 問題が解決しない場合は、管理者に連絡して、調査するように依頼してください。接続の問題の詳細については、[パスワード ライトバックの接続のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/authentication/troubleshoot-sspr-writeback)に関する記事をご覧ください。 | SSPR\_0030: オンプレミス環境との接続状況が悪いため、パスワードをリセットできません。 管理者に連絡して、調査するように依頼してください。 |
| OnPremisesSuccessCloudFailure | パスワードは正常にリセットされましたが、変更がクラウドにコミットされるまで数分待つ必要があります。 これらの変更がコミットされた後は、職場または学校アカウントでサインインする場所であれば、いつでも新しいパスワードを使用できます。 | パスワードのリセットはオンプレミスで成功しましたが、クラウドへの書き込み中にエラーが発生しました。 このエラーは、タイムアウト、クラウド パスワード ポリシー、調整、またはその他の理由によって発生する可能性があります。 |

### Microsoft Entra フォーラム

Microsoft Entra ID やセルフサービス パスワード リセットに関する一般的な質問がある場合は、[Microsoft Entra ID に関する Microsoft Q&A 質問ページ](https://learn.microsoft.com/ja-jp/answers/tags/455/entra-id)でコミュニティに支援を求めることができます。 コミュニティのメンバーには、エンジニア、製品マネージャー、MVP、IT プロフェッショナルなどが含まれます。

### Microsoft サポートに問い合わせる

問題に対する回答が見つからない場合は、Microsoft のサポート チームが随時、問題の解決をお手伝いします。

適切なサポートを提供するため、ケースをオープンする際はできるだけ詳しい情報のご提供をお願いいたします。 これらの詳細には、次の内容が含まれています。

- **エラーの一般的な説明**: どのようなエラーですか。 どのような動作が見られましたか。 エラーを再現することはできますか。 できるだけ詳しくお知らせください。
- **ページ**: エラーが表示されたときに、どのページを表示していましたか。 可能な場合はそのページの URL とスクリーンショットをお送りください。
- **サポート コード**: エラーが表示されたときに生成されたサポート コードをお知らせください。
    - このコードを見つけるには、エラーを再現してから、画面の下部にある**サポート コード**のリンクを選択し、生成された GUID をサポート エンジニアに送信します。

        [Image: サポート コードは、Web ブラウザー ウィンドウの右下にあります。]
    - ページの下部にサポート コードが表示されない場合は、F12 キーを押して SID と CID を検索し、この 2 つの結果をサポート エンジニアに送信します。
- **日付、時刻、タイム ゾーン**: エラーが発生した正確な日付、時刻、"*タイム ゾーン*" をお知らせください。
- **ユーザー ID**: エラーが表示されたユーザーをお知らせください。 たとえば *user@contoso.com*です。
    - このユーザーは、フェデレーション ユーザーですか。
    - このユーザーは、パススルー認証ユーザーですか。
    - このユーザーは、パスワード ハッシュ同期ユーザーですか。
    - このユーザーは、クラウド限定ユーザーですか。
- **ライセンス**: ユーザーに Microsoft Entra ID ライセンスが割り当てられていますか。
- **アプリケーション イベント ログの**: パスワード ライトバックを使用していて、エラーがオンプレミスインフラストラクチャにある場合は、Microsoft Entra Connect サーバーからアプリケーション イベント ログの zip 形式のコピーを含めます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/troubleshoot-sspr-writeback"} -->
## Microsoft Entra ID のセルフサービス パスワード リセットのライトバックに関するトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/troubleshoot-sspr-writeback
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra ID のセルフサービス パスワード リセットのライトバックについて、一般的な問題のトラブルシューティング方法と解決手順について説明します。

Microsoft Entra のセルフサービス パスワード リセット (SSPR) を使用すると、ユーザーはクラウドで自分のパスワードをリセットできます。 パスワード ライトバックは、[Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/whatis-hybrid-identity) または[クラウド同期](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-cloud-sync-sspr-writeback)と共に有効にできる機能です。クラウドで変更されたパスワードを、既存のオンプレミス ディレクトリにリアルタイムでライトバックできます。

SSPR の書き戻しで問題が発生した場合は、次のトラブルシューティング手順と一般的なエラーが役立つことがあります。 問題に対する回答が見つからない場合は、Microsoft のサポート チームがいつでも問題の解決をお手伝いいたします。

### 接続のトラブルシューティング

Microsoft Entra Connect のパスワード ライトバックで問題が発生した場合、以下の手順が問題解決につながる可能性があります。 サービスを回復するには、次に示す手順に順番に従うことをお勧めします。

- ネットワーク接続を確認する
- TLS 1.2 を確認する
- Microsoft .NET 4.8 を更新する
- Microsoft Entra Connect Sync サービスを再起動する
- パスワード ライトバック機能を無効にしてから再び有効にする
- 最新の Microsoft Entra Connect リリースをインストールする
- パスワード ライトバックのトラブルシューティング

#### ネットワーク接続を確認する

最も一般的な障害点は、ファイアウォールまたはプロキシ ポート、またはアイドル タイムアウトが正しく構成されていないことです。

Microsoft Entra Connect バージョン *1.1.443.0* 以降の場合は、以下のアドレスへの*アウトバウンド HTTPS* アクセスが必要です。

- *\*.passwordreset.microsoftonline.com*
- *\*.servicebus.windows.net*

[Azure for US Government エンドポイント](https://learn.microsoft.com/ja-jp/azure/azure-government/compare-azure-government-global-azure#guidance-for-developers):

- *\*.passwordreset.microsoftonline.us*
- *\*.servicebus.usgovcloudapi.net*

Azure China 21Vianet エンドポイント:

- *ssprdedicatedsbmcprodcne.servicebus.chinacloudapi.cn*
- *ssprdedicatedsbmcprodcnn.servicebus.chinacloudapi.cn*

より高い細分性が必要な場合は、[パブリック クラウド向けの Microsoft Azure の IP 範囲とサービス タグの一覧](https://www.microsoft.com/download/details.aspx?id=56519)を参照してください。

Azure for US Government の場合は、「[Azure for US Government クラウド向けの Microsoft Azure の IP 範囲とサービス タグの一覧](https://www.microsoft.com/download/details.aspx?id=57063)」を参照してください。

これらのファイルは毎週更新されます。

グローバル Azure クラウドなどの環境で URL とポートへのアクセスが制限されているかどうかを確認するには、次の手順に従います。

1. Entra Connect サーバーでイベント ビューアー ログ ([Windows ログ] > [アプリケーション]) を開き、イベント ID 31034 または 31019 を探します。
2. 該当するイベント ID から、サービス バス リスナーの名前を特定します。

    [Image: イベント ビューアーのアプリケーション ログにイベント ID 31019 が表示されているスクリーンショット。]
3. 次のコマンドレットを実行します。

    ```powershell
    Test-NetConnection -ComputerName <namespace>.servicebus.windows.net -Port 443
    ```

    または以下を実行します。

    ```powershell
    Invoke-WebRequest -Uri https://<namespace>.servicebus.windows.net -Verbose
    ```

     &lt;名前空間&gt; を、前にイベント ID から抽出したのと同じ値に置き換えます。 たとえば、上の場合、コマンドは次のようになります。

    ```powershell
    Test-NetConnection -ComputerName ssprdedicatedsbprodfra-1.servicebus.windows.net -Port 443
    ```

詳細については、[Microsoft Entra の接続の前提条件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites)に関するページを参照してください。

#### TLS 1.2 が有効になっていることを確認する

追加のトラブルシューティング手順として、同期サーバーで TLS 1.2 が正しく有効になっていることを確認します。 [Entra Connect サーバーで TLS 1.2 を確認する PowerShell スクリプト](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-tls-enforcement#powershell-script-to-check-tls-12)を実行します。 スクリプトは必ず管理者モードで実行してください。

正しく有効になっている場合は、確認用スクリプトの出力が次の画像のように (パス、名前、値の列) 表示されます。 そうでない場合は、[Entra Connect サーバーで TLS 1.2 を有効にする PowerShell スクリプト](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-tls-enforcement#powershell-script-to-enable-tls-12)を実行し、サーバーを再起動してから、再度 TLS 1.2 を確認するスクリプトを実行します。

#### Microsoft .NET Framework 4.8 以降が有効になっていることを確認する (同期サーバー)

同期サーバーで Microsoft .NET Framework 4.8 以降が有効になっていることを確認します。

- [.NET が既にインストールされていることを確認する方法](https://learn.microsoft.com/ja-jp/dotnet/core/install/how-to-detect-installed-versions)
- [PowerShell を使用してレジストリのクエリを実行する](https://learn.microsoft.com/ja-jp/dotnet/framework/migration-guide/how-to-determine-which-versions-are-installed#query-the-registry-using-powershell)
- [.NET Framework をダウンロードする](https://dotnet.microsoft.com/download/dotnet-framework/net48)

#### Microsoft Entra Connect Sync サービスを再起動する

サービスでの接続の問題またはその他の一時的な問題を解決するには、次の手順を完了して Microsoft Entra Connect 同期サービスを再起動します。

1. Microsoft Entra Connect を実行するサーバーの管理者として、**[開始]** を選択します。
2. 検索フィールドに「*services.msc*」を入力して、**[入力]** を選択します。
3. *Azure AD Sync* エントリを探します。
4. このサービス エントリを右クリックして **[再起動]** を選択し、操作が完了するまで待ちます。

    [Image: GUI を使用して Azure AD Sync サービスを再起動する]

これらの手順によって Microsoft Entra ID との接続が再確立され、接続の問題が解決されます。

Microsoft Entra Connect 同期サービスを再起動しても問題が解決されない場合は、次のセクションでパスワード ライトバック機能を無効にしてから再び有効にしてみてください。

#### パスワード ライトバック機能を無効にしてから再び有効にする

引き続き問題をトラブルシューティングするには、次の手順を完了して、パスワード ライトバック機能を無効にしてから再び有効にします。

1. Microsoft Entra Connect を実行するサーバーの管理者として、**Microsoft Entra Connect 構成ウィザード**を開きます。
2. In **[Microsoft Entra ID に接続する]** に、Microsoft Entra ハイブリッド管理者の資格情報を入力します。
3. **[AD DS に接続]** で、オンプレミス Active Directory Domain Services の管理者資格情報を入力します。
4. **[ユーザーを一意に識別]** で、**[次へ]** を選択します。
5. **[オプション機能]** で、**[パスワード ライトバック]** チェック ボックスをオフにします。
6. 他のダイアログ ページは何も変更せずに、**[構成の準備完了]** ページが表示されるまで **[次へ]** を選択します。
7. **[構成の準備完了] ページ**に *[パスワード ライトバック]* オプションが *[無効]* として表示されることを確認します。 緑色の **[構成]** ボタンを選択して変更をコミットします。
8. **[完了]** で、**[今すぐ同期]** オプションをオフにし、**[完了]** を選択してウィザードを閉じます。
9. **Microsoft Entra Connect 構成ウィザード**をもう一度開きます。
10. 手順 2. ～ 8. を繰り返しますが、今度は *[オプション機能]* ページで **[パスワード ライトバック]** オプションをオンにしてサービスを再び有効にします。

これらの手順によって Microsoft Entra ID との接続が再確立され、接続の問題が解決されます。

パスワード ライトバック機能を無効にしてから再び有効にしても問題が解決されない場合は、次のセクションで Microsoft Entra Connect を再インストールします。

#### 最新の Microsoft Entra Connect リリースをインストールする

Microsoft Entra Connect を再インストールすると、Microsoft Entra ID とローカルの Active Directory Domain Services 環境の間の構成と接続の問題が解決される場合があります。 この手順は、接続を確認およびトラブルシューティングするために前の手順を試した後にのみ実行することをお勧めします。

警告

標準の同期規則をカスタマイズしている場合は、*アップグレードを続行する前にバックアップし、完了後にそれらを手動で再デプロイしてください。*

1. Microsoft Entra Connect の [[管理](https://entra.microsoft.com/#view/Microsoft_AAD_Connect_Provisioning/AADConnectMenuBlade/%7E/GetStarted)] タブにある **Microsoft Entra 管理センター**から最新バージョンをダウンロードする **|[作業の開始]** ページ。
2. Microsoft Entra Connect を既にインストールしている場合は、インプレース アップグレードを実行して、Microsoft Entra Connect のインストールを最新バージョンに更新します。

    ダウンロードされたパッケージを実行し、画面の指示に従って Microsoft Entra Connect を更新します。

これらの手順によって Microsoft Entra ID との接続が再確立され、接続の問題が解決されます。

最新バージョンの Microsoft Entra Connect サーバーをインストールしても問題が解決されない場合は、最新リリースをインストールした後に、最後の手順としてパスワード ライトバックを無効にしてから再び有効にしてみてください。

### Microsoft Entra Connect の必須のアクセス許可を確認する

パスワード ライトバックを実行するには、Microsoft Entra Connect に AD DS の **[パスワードのリセット]** アクセス許可が必要です。 Microsoft Entra Connect に対して、特定のオンプレミスの AD DS ユーザー アカウントへの必要なアクセス許可が設定されていることを確認するには、**Windows の有効なアクセス許可**機能を使用します。

1. Microsoft Entra Connect サーバーにサインインし、** [開始] **&gt; の順に選択して、**Sychronization Service Manager** を開始します。
2. **[コネクタ]** タブで、オンプレミスの **[Active Directory Domain Services]** コネクタを選択してから、**[プロパティ]** を選択します。

    [Image: プロパティの編集方法を示す Sychronization Service Manager]
3. ポップアップ ウィンドウで **[Connect to Active Directory Forest](Active Directory フォレストに接続)** を選択し、**[ユーザー名]** のプロパティをメモします。 このプロパティは、ディレクトリ同期を実行するために、Microsoft Entra Connect によって使用される AD DS アカウントです。

    Microsoft Entra Connect でパスワード ライトバックを実行するために、AD DS アカウントにはパスワードのリセットのアクセス許可が必要です。 このユーザー アカウントに対するアクセス許可は、次の手順で確認します。

    [Image: 同期サービスの Active Directory ユーザー アカウントの検索]
4. オンプレミスのドメイン コントローラーにサインインし、**[Active Directory ユーザーとコンピューター]** アプリケーションを起動します。
5. **[表示]** を選択し、**[高度な機能]** オプションが有効であることを確認します。

    [Image: [Active Directory ユーザーとコンピューター] に [高度な機能] が表示される]
6. 確認する AD DS ユーザー アカウントを探します。 アカウント名を右クリックし、**[プロパティ]** を選択します。
7. ポップアップ ウィンドウで、**[セキュリティ]** タブに移動し、**[詳細設定]** を選択します。
8. **[Advanced Security Settings for Administrator](https://learn.microsoft.com/ja-jp/entra/identity/authentication/管理者のセキュリティの詳細設定)** ポップアップ ウィンドウで、**[有効なアクセス]** タブに移動します。
9. **[ユーザーの選択]** を選択し、Microsoft Entra Connect によって使用される AD DS アカウントを選択してから、**[有効なアクセス許可の表示]** を選択します。

    [Image: 同期アカウントを示す [有効なアクセス] タブ]
10. 下にスクロールし、**[パスワードのリセット]** を探します。 エントリにチェック マークがついている場合は、選択した Active Directory ユーザー アカウントのパスワードをリセットするアクセス許可が AD DS アカウントにあることを意味します。

    [Image: 同期アカウントにパスワードのリセット権限があることの検証]

### パスワード ライトバックの一般的なエラー

パスワード ライトバックでは、次のより具体的な問題が発生する可能性があります。 これらのエラーのいずれかが発生した場合は、提案されている解決策を確認し、それによりパスワード ライトバックが正しく機能するかどうかを確認してください。

| Error | ソリューション |
| --- | --- |
| オンプレミスでパスワード リセット サービスが開始されません。 Microsoft Entra Connect マシンのアプリケーション イベント ログにエラー 6800 が表示されます。  フェデレーション、パススルー認証、またはパスワード ハッシュ同期されたユーザーは、オンボード後に自分のパスワードをリセットできません。 | パスワード ライトバックを有効にすると、同期エンジンはライトバック ライブラリを呼び出し、クラウド オンボード サービスと通信して構成 (オンボード) を実行します。 オンボード中またはパスワード ライトバックの Windows Communication Foundation (WCF) エンドポイントの起動中に発生したエラーは、Microsoft Entra Connect マシンのイベント ログのエラーになります。  Azure AD Sync (ADSync) サービスの再起動時にライトバックが構成された場合は、WCF エンドポイントが起動します。 ただし、エンドポイントの起動に失敗した場合は、イベント 6800 をログに記録して同期サービスを起動させます。 このイベントの存在は、パスワード ライトバックのエンドポイントが起動しなかったことを示します。 このイベント 6800 のイベント ログ詳細では、PasswordResetService コンポーネントで生成されたイベント ログ エントリとともに、エンドポイントが起動できなかった理由が示されます。 パスワード ライトバックがまだ機能していない場合は、これらのイベント ログ エラーを確認し、Microsoft Entra Connect を再起動してみてください。 問題が解決しない場合は、パスワード ライトバックを無効にしてから再び有効にしてみてください。 |
| パスワード ライトバックを有効にした状態でユーザーがアカウントのロック解除またはパスワードのリセットを試みると操作に失敗します。  また、Microsoft Entra Connect のイベント ログを見ると、ロック解除操作が実行された後に "Synchronization Engine returned an error hr=800700CE, message=The filename or extension is too long (同期エンジンから hr=800700CE エラーと、ファイル名または拡張子が長すぎるというメッセージが返されました)" というイベントが記録されています。 | Microsoft Entra Connect の Active Directory アカウントを探し、そのパスワードを 256 文字以内に収めてリセットします。 次に、**[開始]** メニューから **[同期サービス]** を開きます。 **[コネクタ]** を表示し、**[Active Directory Connector](Active Directory コネクタ)** を探します。 これを選択してから、**[プロパティ]** を選択します。 **[資格情報]** ページを表示して、新しいパスワードを入力します。 **[OK]** を選択してページを閉じます。 |
| Microsoft Entra Connect インストール プロセスの最後の手順で、パスワード ライトバックを構成できなかったことを示すエラーが表示されます。  Microsoft Entra Connect アプリケーション イベント ログには、エラー 32009 と "認証トークンの取得エラー" というテキストが含まれています。 | このエラーは、次の 2 つの場合に発生します。<br>- Microsoft Entra Connect のインストール プロセスの開始時に指定するハイブリッド管理者アカウント用のパスワードが誤っていた。<br>- Microsoft Entra Connect インストール プロセスの開始時に指定するハイブリッド管理者アカウントにフェデレーション ユーザーを使用しようとした。<br><br> この問題を解決するには、インストール プロセスの開始時に指定するハイブリッド管理者にフェデレーション アカウントを使用していないこと、および指定したパスワードが正しいことを確認してください。 |
| Microsoft Entra Connect マシンのイベント ログに、PasswordResetService の実行によってスローされたエラー 32002 が記録されています。  エラーの内容は次のとおりです。"Error Connecting to ServiceBus. The token provider was unable to provide a security token. (ServiceBus への接続中にエラーが発生しました。トークン プロバイダーはセキュリティ トークンを提供できませんでした。)" | ご利用のオンプレミス環境では、クラウドの Azure Service Bus エンドポイントに接続できません。 特定のポートまたは Web アドレスへの送信接続をブロックしているファイアウォール規則では、通常、このエラーが発生します。 詳細については、[接続の前提条件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites)に関する記事をご覧ください。 これらの規則を更新した後、Microsoft Entra Connect サーバーを再起動すると、パスワード ライトバックが再び機能し始めます。 |
| フェデレーション、パススルー認証、またはパスワード ハッシュ同期されたユーザーは、しばらく操作した後に自分のパスワードをリセットできません。 | Microsoft Entra Connect を再起動したときに、まれにパスワード ライトバック サービスの再起動に失敗することがあります。 これらの場合は、まず、パスワード ライトバックがオンプレミスで有効になっているかどうかを確認します。 Microsoft Entra Connect ウィザードまたは PowerShell のどちらかを使用して確認できます。 この機能が有効であると表示されている場合は、この機能を再び有効または無効にしてみてください。 このトラブルシューティング手順が機能しない場合は、Microsoft Entra Connect の完全なアンインストールと再インストールを試してください。 |
| 自分のパスワードのリセットを試みるフェデレーション、パススルー認証、またはパスワード ハッシュ同期されたユーザーには、パスワード送信後にエラーが表示されます。 このエラーは、サービスに問題があったことを示しています。  この問題に加えて、パスワードのリセット操作中に、管理エージェントがオンプレミスのイベント ログでアクセスを拒否されたというエラーが表示されることがあります。 | イベント ログにこれらのエラーが表示される場合は、構成中に指定された Active Directory 管理エージェント (ADMA) アカウントにパスワード ライトバックに必要なアクセス許可があることを確認します。  このアクセス許可が付与された後、ドメイン コントローラー (DC) の `sdprop` バックグラウンド タスクを介して、アクセス許可が適用されるのに最大 1 時間かかることがあります。  パスワードのリセットが機能するには、パスワードがリセットされるユーザー オブジェクトのセキュリティ記述子に権限を設定する必要があります。 このアクセス許可がユーザー オブジェクトに表示されるまでは、引き続きアクセス拒否メッセージが表示されてパスワードのリセットが失敗します。 |
| 自分のパスワードのリセットを試みるフェデレーション、パススルー認証、またはパスワード ハッシュ同期されたユーザーには、パスワード送信後にエラーが表示されます。 このエラーは、サービスに問題があったことを示しています。  この問題に加えて、パスワードのリセット操作中、Microsoft Entra Connect サービスからのイベント ログに、"Object could not be found (オブジェクトが見つかりませんでした)" というエラーが記録されることがあります。 | このエラーは通常、Microsoft Entra コネクタ スペース内またはリンクされたメタバース (MV) 内のユーザー オブジェクトか、Microsoft Entra コネクタ スペース オブジェクトのいずれかを、同期エンジンが検索できないことを示しています。  この問題をトラブルシューティングするには、Microsoft Entra Connect の現在のインスタンスを介してオンプレミスから Microsoft Entra ID にユーザーが実際に同期されていることを確認し、コネクタ スペースと MV 内のオブジェクトの状態を検査します。 Active Directory 証明書サービス (AD CS) オブジェクトが "Microsoft.InfromADUserAccountEnabled.xxx" 規則によって MV オブジェクトに接続されていることを確認してください。 |
| 自分のパスワードのリセットを試みるフェデレーション、パススルー認証、またはパスワード ハッシュ同期されたユーザーには、パスワード送信後にエラーが表示されます。 このエラーは、サービスに問題があったことを示しています。  この問題に加えて、パスワードのリセット操作中、Microsoft Entra Connect サービスからのイベント ログに、"Multiple matches found (複数の一致が見つかりました)" というエラーが記録されることがあります。 | これは、MV オブジェクトが "Microsoft.InfromADUserAccountEnabled.xxx" によって複数の AD CS オブジェクトに接続されていることを同期エンジンが検出したことを示します。 これは、ユーザーが複数のフォレストに有効なアカウントを持っていることを意味します。 このシナリオでは、パスワード ライトバックがサポートされません。 |
| パスワード操作は構成エラーにより失敗しました アプリケーション イベント ログには、Microsoft Entra Connect エラー 6329 が含まれています。"0x8023061f (この管理エージェントでパスワード同期が有効になっていないため、操作に失敗しました)" というテキストが含まれています。 | このエラーは、パスワード ライトバック機能を有効にした後、新しい Active Directory フォレストを追加する (または既存のフォレストを削除して再度追加する) ために、Microsoft Entra Connect の構成が変更された場合に発生します。 このような最近追加されたフォレスト内のユーザーのパスワード操作は失敗します。 この問題を解決するには、フォレスト構成の変更が完了した後、パスワード ライトバック機能を無効にしてから再度有効にします。 |
| SSPR\_0029: オンプレミス構成でのエラーのため、パスワードをリセットすることができません。 管理者に連絡して、調査するように依頼してください。 | 問題: パスワード ライトバックは、必要なすべての手順に従って有効になっていますが、パスワードを変更しようとすると、"SSPR\_0029: 組織がパスワード リセット用にオンプレミスの構成を適切に設定していない" というメッセージが表示されます。Microsoft Entra Connect システムでイベント ログを確認すると、管理エージェントの資格情報のアクセスが拒否されたことが示されます。 考えられる解決策: Microsoft Entra Connect システムおよびドメイン コントローラーで RSOP を使用し、[コンピューターの構成] &gt; [Windows の設定] &gt; [セキュリティ設定] &gt; [ローカル ポリシー] &gt; [セキュリティ オプション] の下にある "ネットワーク アクセス - SAM へのリモートの呼び出しを許可するクライアントを制限する" ポリシーが有効になっていることを確認します。 このポリシーを編集して MSOL\_XXXXXXX 管理アカウントを許可されたユーザーとして含めます。 詳細については、「[エラー SSPR_0029 のトラブルシューティング: 組織でパスワードのリセットのためのオンプレミス構成が正しく設定されていない](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/password-writeback-error-code-sspr-0029)」を参照してください。 |

### パスワード ライトバックのイベント ログのエラー コード

パスワード ライトバックに関する問題をトラブルシューティングする場合のベスト プラクティスは、Microsoft Entra Connect マシンでアプリケーション イベント ログを調べることです。 このイベント ログには、パスワード ライトバックの 2 つのソースからのイベントが含まれます。 *PasswordResetService* ソースは、パスワード ライトバックの操作に関連した操作と問題を記述します。 *ADSync* ソースは、Active Directory Domain Services 環境でのパスワードの設定に関連した操作と問題を記述します。

#### イベントのソースが ADSync の場合

| Code | 名前またはメッセージ | 説明 |
| --- | --- | --- |
| 6329 | BAIL: MMS(4924) 0x80230619: "A restriction prevents the password from being changed to the current one specified. (制限によりパスワードを現在指定されているパスワードに変更することができません。)" | このイベントは、パスワード ライトバック サービスが、ドメインのパスワードの有効期間、履歴、複雑さ、またはフィルター処理の要件を満たしていないローカル ディレクトリにパスワードを設定しようとした場合に発生します。 このイベントは、ユーザーのパスワードを変更できない場合にも発生することがあります。  パスワードの最小有効期間が残っていて、最近その期間内にパスワードを変更した場合は、そのドメインで指定された期限に達するまで、もう一度パスワードを変更することはできません。 テストのために、最小有効期間は 0 に設定する必要があります。  パスワードの履歴の要件が有効になっている場合は、過去 *N* 回で使用されていないパスワードを選択する必要があります。ここで、*N* はパスワードの履歴の設定です。 最後の *N* 回に使用されたパスワードを選択すると、この場合にエラーが発生します。 テストのために、パスワードの履歴は 0 に設定する必要があります。  パスワードの複雑さの要件を指定する場合は、ユーザーがパスワードを変更またはリセットしようとすると、すべての要件が適用されます。  パスワード フィルターが有効になっているときに、ユーザーがフィルター条件を満たしていないパスワードを選択した場合、リセットまたは変更操作は失敗します。  ユーザーに対して PASSWD\_CANT\_CHANGE プロパティ フラグが設定されている場合、そのユーザーはパスワードを同期できません。 確認のため、一時的に PASSWD\_CANT\_CHANGE プロパティ フラグを削除してみてください。 詳細については、「[プロパティ フラグの説明](https://learn.microsoft.com/ja-jp/troubleshoot/windows-server/active-directory/useraccountcontrol-manipulate-account-properties#property-flag-descriptions)」を参照してください。 |
| 6329 | MMS(3040): admaexport.cpp(2837): サーバーに LDAP のパスワード ポリシー コントロールが含まれていません。 | この問題は、DC で LDAP\_SERVER\_POLICY\_HINTS\_OID コントロール (1.2.840.113556.1.4.2066) が有効になっていない場合に発生します。 パスワード ライトバック機能を使用するのには、コントロールを有効にする必要があります。 これを行うには、DCがWindows Server 2016以降にインストールされている必要があります。 |
| HR 8023042 | 同期エンジンから hr=80230402 エラーと、"同じアンカーに重複するエントリがあるためオブジェクトの取得に失敗しました" というメッセージが返されました。 | このエラーは、複数のドメインで同じユーザー ID を有効にした場合に発生します。 たとえば、アカウントとリソース フォレストを同期したときに、各フォレスト内に同じユーザー ID が存在し、どちらも有効な場合です。  また、一意ではないアンカー属性 (エイリアスや UPN) を使用し、2 人のユーザーがその同じアンカー属性を共有している場合にも、このエラーが発生します。  この問題を解決するには、ドメイン内に重複するユーザーがいないようにして、各ユーザーに一意のアンカー属性を使用します。 |

#### イベントのソースが PasswordResetService の場合

| Code | 名前またはメッセージ | 説明 |
| --- | --- | --- |
| 31001 | パスワードリセット開始 | このイベントは、オンプレミスのサービスが、クラウドから送信されたフェデレーション、パススルー認証、またはパスワード ハッシュ同期されたユーザーへのパスワードのリセット要求を検出したことを示します。 このイベントは、すべてのパスワード リセットのライトバック操作における最初のイベントです。 |
| 31002 | パスワードリセット成功 | このイベントは、パスワードのリセット操作中に、ユーザーが新しいパスワードを選択したことを示します。 このパスワードが企業のパスワード要件を満たしていると判断されました。 パスワードは、ローカルの Active Directory 環境に正常に書き戻されます。 |
| 31003 | パスワードリセット失敗 | このイベントは、ユーザーがパスワードを選択し、そのパスワードが正しくオンプレミス環境に届いたことを示します。 しかし、ローカルの Active Directory 環境でパスワードの設定を試みたときに、エラーが発生しました。 このエラーの原因としては、以下が考えられます。<br>- ユーザーのパスワードがドメインの有効期間、履歴、複雑さ、またはフィルターの要件を満たしていない。 この問題を解決するには、新しいパスワードを作成します。<br>- ADMA サービス アカウントに、対象のユーザー アカウントに新しいパスワードを設定するための適切なアクセス許可がない。<br>- ユーザーのアカウントが、パスワードの設定操作が禁止されている保護されたグループ (ドメインまたはエンタープライズ管理者グループなど) に含まれている。 |
| 31004 | オンボーディングイベント開始 | このイベントは、Microsoft Entra Connect でのパスワード ライトバックが有効になっているときに、パスワード ライトバック Web サービスへの組織のオンボードが開始された場合に発生します。 |
| 31005 | オンボーディングイベント成功 | このイベントは、オンボード プロセスが成功し、パスワード ライトバック機能を使用する準備が整ったことを示します。 |
| 31006 | パスワード変更開始 | このイベントは、オンプレミスのサービスが、クラウドから送信されたフェデレーション、パススルー認証、またはパスワード ハッシュ同期されたユーザーへのパスワード変更要求を検出したことを示します。 このイベントは、すべてのパスワード変更のライトバック操作における最初のイベントです。 |
| 31007 | パスワード変更成功 | このイベントは、ユーザーがパスワード変更操作中に新しいパスワードを選択し、パスワードが会社のパスワード要件を満たしていることを確認し、パスワードがローカル Active Directory 環境に正常に書き戻されたことを示します。 |
| 31008 | パスワード変更失敗 | このイベントは、ユーザーがパスワードを選択し、そのパスワードがオンプレミス環境に正常に届いたものの、ローカルの Active Directory 環境でパスワードを設定しようとしたところエラーが発生したことを示します。 このエラーの原因としては、以下が考えられます。<br>- ユーザーのパスワードがドメインの有効期間、履歴、複雑さ、またはフィルターの要件を満たしていない。 この問題を解決するには、新しいパスワードを作成します。<br>- ADMA サービス アカウントに、対象のユーザー アカウントに新しいパスワードを設定するための適切なアクセス許可がない。<br>- ユーザーのアカウントが、パスワードの設定操作が禁止されている保護されたグループ (ドメインまたはエンタープライズ管理者など) に含まれている。 |
| 31009 | ResetUserPasswordByAdminStart | オンプレミスのサービスが、ユーザーに代わって管理者から送信された、フェデレーション、パススルー認証、またはパスワード ハッシュ同期されたユーザーへのパスワードのリセット要求を検出しました。 このイベントは、管理者が開始したパスワードのリセットのライトバック操作における最初のイベントです。 |
| 31010 | ユーザーのパスワードが管理者によって正常にリセットされました | 管理者が開始したパスワードのリセット操作中に、管理者が新しいパスワードを選択しました。 このパスワードが企業のパスワード要件を満たしていると判断されました。 パスワードは、ローカルの Active Directory 環境に正常に書き戻されます。 |
| 31011 | 管理者によるユーザーパスワードのリセット失敗 | 管理者がユーザーの代わりにパスワードを選択しました。 パスワードが正常にオンプレミス環境に到着しました。 しかし、ローカルの Active Directory 環境でパスワードの設定を試みたときに、エラーが発生しました。 このエラーの原因としては、以下が考えられます。<br>- ユーザーのパスワードがドメインの有効期間、履歴、複雑さ、またはフィルターの要件を満たしていない。 この問題を解決するには、新しいパスワードを試します。<br>- ADMA サービス アカウントに、対象のユーザー アカウントに新しいパスワードを設定するための適切なアクセス許可がない。<br>- ユーザーのアカウントが、パスワードの設定操作が禁止されている保護されたグループ (ドメインまたはエンタープライズ管理者など) に含まれている。 |
| 31012 | OffboardingEventStart | このイベントは、Microsoft Entra Connect でパスワード ライトバックが無効になっている場合に発生し、組織がパスワード ライトバック Web サービスのオフボードを開始したことを示します。 |
| 31013 | OffboardingEventSuccess | このイベントは、オフボード プロセスが成功し、パスワード ライトバック機能が正常に無効になっていることを示します。 |
| 31014 | オフボーディングイベント失敗 | このイベントは、オフボード プロセスが失敗したことを示します。 これは、構成時に指定したクラウドまたはオンプレミスの管理者アカウント上で起こったアクセス許可のエラーが原因である可能性があります。 また、このエラーは、パスワード ライトバックを無効にしているときにフェデレーション クラウド ハイブリッド管理者の使用を試みた場合にも、発生することがあります。 この問題を解決するには、管理者のアクセス許可を確認し、パスワード ライトバック機能を構成する場合にフェデレーション アカウントを使用していないことを確認します。 |
| 31015 | WriteBackServiceStarted | このイベントは、パスワード ライトバック サービスが正常に開始されたことを示します。 クラウドからのパスワード管理要求を受け入れる準備ができています。 |
| 31016 | 書き戻しサービスが停止しました | このイベントは、パスワード ライトバック サービスが停止したことを示します。 クラウドからのパスワード管理要求はすべて失敗します。 |
| 31017 | 認証トークン成功 | このイベントは、オフボードまたはオンボード プロセスを開始するために、Microsoft Entra Connect のセットアップ時に指定されたハイブリッド管理者の認可トークンを正常に取得したことを示します。 |
| 31018 | KeyPairCreationSuccess | このイベントは、パスワード暗号化キーが正常に作成されたことを示します。 このキーは、クラウドからのパスワードを暗号化してご利用のオンプレミス環境に送信するために使用されます。 |
| 31019 | ServiceBusHeartBeat | このイベントは、テナントの Service Bus インスタンスへの要求が正常に送信されたことを示します。 |
| 31034 | サービスバスリスナーのエラー | このイベントは、テナントの Service Bus リスナーへの接続中にエラーが発生したことを示します。 エラー メッセージに "リモート証明書が無効です" と表示される場合は、「[Azure TLS 証明書の変更](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/tls-certificate-changes)」で説明されているように、すべての必要なルート CA が Microsoft Entra Connect サーバーに存在していることを確認してください。 |
| 31044 | パスワードリセットサービス | このイベントは、パスワード ライトバックが機能していないことを示します。 Service Bus は、冗長性を確保するために 2 つの異なるリレーで要求をリッスンします。 それぞれのリレー接続は、独自のサービス ホストによって管理されます。 いずれかのサービス ホストが実行されていない場合、ライトバック クライアントはエラーを返します。 |
| 三万二千 | 不明なエラー | このイベントは、パスワード管理操作中に発生した原因不明のエラーを示します。 詳細については、イベントの例外の説明をご覧ください。 問題が発生した場合は、パスワード ライトバックを無効にしてから再び有効にしてみてください。 これで解決されない場合は、サポート リクエストを開いたときに指定される追跡 ID と共にイベント ログのコピーを含めてください。 |
| 32001 | サービスエラー | このイベントは、クラウド パスワード リセット サービスへの接続中にエラーが発生したことを示します。 このエラーは通常、オンプレミスのサービスがパスワード リセット Web サービスに接続できない場合に発生します。 |
| 32002 | サービスバスエラー | このイベントは、テナントの Service Bus インスタンスへの接続中にエラーが発生したことを示します。 これは、オンプレミス環境で発信接続をブロックしている場合に発生する可能性があります。 ファイアウォールで TCP 443 経由の https://ssprdedicatedsbprodncu.servicebus.windows.net への接続が許可されていることを確認してから、やり直してください。 問題が解決しない場合は、パスワード ライトバックを無効にしてから再び有効にしてみてください。 |
| 32003 | InPutValidationError | このイベントは、Web サービス API に渡された入力が無効だったことを示します。 操作をやり直してください。 |
| 32004 | 復号エラー | このイベントは、クラウドから受信したパスワードの解読中にエラーが発生したことを示します。 これは、クラウド サービスとオンプレミス環境との間で暗号化の解除キーが一致しないことが原因である可能性があります。 この問題を解決するには、オンプレミス環境でパスワード ライトバックを無効にしてから再び有効にします。 |
| 32005 | 構成エラー(ConfigurationError) | オンボード中に、オンプレミスの環境内の構成ファイルにテナント固有の情報を保存します。 このイベントは、このファイルの保存中にエラーが発生した、またはサービスの開始時にファイルの読み取りエラーが発生したことを示します。 この問題を解決するには、この構成ファイルを強制的に書き換えるためにパスワード ライトバックを無効にしてから再び有効にしてみてください。 |
| 32007 | OnBoardingConfigUpdateError（オンボーディング設定更新エラー） | オンボード中に、クラウドからオンプレミスのパスワードのリセット サービスにデータを送信します。 そのデータは、同期サービスに送信される前にメモリ内ファイルに書き込まれ、ディスクに安全に格納されます。 このイベントは、メモリ内でのそのデータの書き込みまたは更新に問題があることを示します。 この問題を解決するには、この構成ファイルを強制的に書き換えるためにパスワード ライトバックを無効にしてから再び有効にしてみてください。 |
| 32008 | ValidationError | このイベントは、パスワード リセット Web サービスから無効な応答を受け取ったことを示します。 この問題を解決するには、パスワード ライトバックを無効にしてから再び有効にしてみてください。 |
| 32009 | 認証トークンエラー | このイベントは、Microsoft Entra Connect のセットアップ時に指定されたハイブリッド管理者アカウントの承認トークンを取得できなかったことを示します。 このエラーは、ハイブリッド管理者アカウントに指定された無効なユーザー名またはパスワードが原因である可能性があります。 また、指定されたハイブリッド管理者アカウントがフェデレーションされているために発生している可能性もあります。 この問題を解決するには、適切なユーザー名とパスワードを使用して構成を再実行し、管理者が管理 (クラウドのみまたはパスワード同期された) アカウントになっていることを確認します。 |
| 32010 | CryptoError | このイベントは、パスワード暗号化キーの生成中、またはクラウド サービスから受信するパスワードの暗号化の解除中にエラーが発生したことを示します。 このエラーは、ご利用の環境に問題がある可能性を示しています。 この問題の解決方法の詳細については、イベント ログの詳細を確認します。 また、パスワード ライトバック サービスを無効にしてから再び有効にすると解決できる場合もあります。 |
| 32011 | OnBoardingServiceError | このイベントは、オンプレミスのサービスが、オンボード プロセスを開始しようとしてパスワード リセット Web サービスと正しく通信できなかったことを示します。 これは、ファイアウォール規則の結果として、またはテナントの認証トークンの取得中に問題が生じた場合に発生することがあります。 この問題を解決するには、TCP 443 と TCP 9350-9354 経由の発信接続、または https://ssprdedicatedsbprodncu.servicebus.windows.net への接続がブロックされていないことを確認します。 また、オンボードに使用している Microsoft Entra 管理者アカウントがフェデレーションされていないことも確認します。 |
| 32013 | OffBoardingError | このイベントは、オンプレミスのサービスが、オフボード プロセスを開始しようとしてパスワード リセット Web サービスと正しく通信できなかったことを示します。 これは、ファイアウォール規則の結果として、またはテナントの承認トークンの取得中に問題が生じた場合に発生することがあります。 この問題を解決するには、TCP 443 経由の発信接続、または https://ssprdedicatedsbprodncu.servicebus.windows.net への接続がブロックされていないことと、オフボードに使用している Microsoft Entra 管理者アカウントがフェデレーションされていないことを確認します。 |
| 32014 | サービスバス警告 | このイベントは、テナントの Service Bus インスタンスへの接続を再試行する必要があったことを示します。 通常の条件下では、これは問題になりませんが、このイベントが何度も発生する場合は、特に待機時間が長い場合や低帯域幅の接続である場合は、Service Bus へのネットワーク接続を確認することを検討してください。 |
| 32015 | ReportServiceHealthError | パスワード ライトバック サービスの正常性を監視するには、パスワード リセット Web サービスに 5 分ごとにハートビート データを送信します。 このイベントは、この正常性情報をクラウド Web サービスに送信するときにエラーが発生したことを示します。 この正常性情報に個人データは含まれず、クラウド内のサービスの状態情報を提供できるように、純粋にハートビートと基本的なサービス統計情報で構成されています。 |
| 33001 | ADUnKnownError | このイベントは、Active Directory から不明なエラーが返されたことを示します。 Microsoft Entra Connect サーバーのイベント ログで、ADSync ソースからのイベントの詳細を確認します。 |
| 33002 | ADUserNotFoundError | このイベントは、パスワードをリセットまたは変更しようとしているユーザーがオンプレミスディレクトリに見つからなかったことを示します。 このエラーは、ユーザーがオンプレミスで削除され、クラウドでは削除されない場合に発生する可能性があります。 このエラーはまた、同期に問題がある場合にも発生することがあります。同期ログと、最近のいくつかの同期実行に関する詳細情報を確認します。 |
| 33003 | ADMutliMatchError | パスワードのリセットまたは変更要求がクラウドから送信されると、Microsoft Entra Connect のセットアップ プロセス中に指定されたクラウドのアンカーを使用して、その要求をオンプレミスの環境内のユーザーにリンクする方法を決定します。 このイベントは、オンプレミスのディレクトリ内に同じクラウドのアンカー属性を持つユーザーが 2 人見つかったことを示します。 同期ログと、最近のいくつかの同期実行に関する詳細情報を確認します。 |
| 33004 | ADPermissionsError | このイベントは、Active Directory 管理エージェント (ADMA) サービス アカウントに、新しいパスワードを設定するための対象のアカウントに対する適切なアクセス許可がないことを示します。 ユーザーのフォレスト内の ADMA アカウントに、フォレスト内のすべてのオブジェクトに対する [パスワードのリセット] アクセス許可があることを確認してください。 アクセス許可を設定する方法の詳細については、「Step 4: Set up the appropriate Active Directory permissions (手順 4: Active Directory の適切なアクセス許可を設定する)」をご覧ください。 このエラーは、ユーザーの属性 AdminCount が 1 に設定されている場合にも発生する可能性があります。 |
| 33005 | AD ユーザーアカウントが無効になっている | このイベントは、オンプレミスで無効になっていたアカウントのパスワードをリセットまたは変更しようとしたことを示します。 アカウントを有効にして、操作をやり直してください。 |
| 33006 | ADUserAccountLockedOut | このイベントは、オンプレミスでロックアウトされたアカウントのパスワードをリセットまたは変更しようとしたことを示します。 ロックアウトは、ユーザーが短時間にパスワードの変更またはリセット操作を何度も試みた場合に発生する可能性があります。 アカウントのロックを解除して、操作をやり直してください。 |
| 33007 | ADユーザーのパスワードが間違っています | このイベントは、ユーザーがパスワードの変更操作を行うときに、現在のパスワードを正しく指定しなかったことを示します。 現在の正しいパスワードを指定して、やり直してください。 |
| 33008 | ADパスワードポリシーエラー (ADのパスワードポリシーに関するエラー) | このイベントは、パスワード ライトバック サービスが、ドメインのパスワードの有効期間、履歴、複雑さ、またはフィルター処理の要件を満たしていないローカル ディレクトリにパスワードを設定しようとした場合に発生します。  パスワードの最小有効期間が残っていて、最近その期間内にパスワードを変更した場合は、そのドメインで指定された期限に達するまで、もう一度パスワードを変更することはできません。 テストのために、最小有効期間は 0 に設定する必要があります。  パスワードの履歴の要件が有効になっている場合は、過去 *N* 回で使用されていないパスワードを選択する必要があります。ここで、*N* はパスワードの履歴の設定です。 最後の *N* 回に使用されたパスワードを選択すると、この場合にエラーが発生します。 テストのために、パスワードの履歴は 0 に設定する必要があります。  パスワードの複雑さの要件を指定する場合は、ユーザーがパスワードを変更またはリセットしようとすると、すべての要件が適用されます。  パスワード フィルターが有効になっているときに、ユーザーがフィルター条件を満たしていないパスワードを選択した場合、リセットまたは変更操作は失敗します。 |
| 33009 | ADConfigurationError | このイベントは、Active Directory の構成に問題があるため、オンプレミスのディレクトリへのパスワード書き込みエラーが発生したことを示します。 エラーが発生した詳細については、Microsoft Entra Connect マシンのアプリケーション イベント ログで ADSync サービスからのメッセージを確認してください。 |

### 組織単位 (OU) 構造での予約文字の使用によるパスワード ライトバックの失敗

次の表に、パスワード ライトバックを妨げる予約文字を示します。 これらの文字がオンプレミスの組織単位 (OU) 構造に使用されている場合、パスワード ライトバックがイベント ID 33001 で失敗する可能性があります。

| 予約文字 | 説明 | 16 進値 |
| --- | --- | --- |
|  | 文字列の先頭のスペースまたは # 文字 |  |
|  | 文字列の末尾の空白文字 |  |
| , | コンマ | 0x2C |
| + | プラス記号 | 0x2B |
| 「」 | 引用符 | 0x22 |
| \ | 円記号 | 0x5C |
| &lt; | 左山かっこ | 0x3C |
| &gt; | 右山かっこ | 0x3E |
| ; | セミコロン | 0x3B |
| LF | ライン フィード | 0x0A |
| CR | キャリッジ リターン | 0x0D |
| = | 等号 | 0x3D |
| / | スラッシュ | 0x2F |

### Microsoft Entra フォーラム

Microsoft Entra ID やセルフサービス パスワード リセットに関する一般的な質問がある場合は、[Microsoft Entra ID に関する Microsoft Q&A 質問ページ](https://learn.microsoft.com/ja-jp/answers/tags/455/entra-id)でコミュニティに支援を求めることができます。 コミュニティのメンバーには、エンジニア、製品マネージャー、MVP、IT プロフェッショナルなどが含まれます。

### Microsoft サポートに問い合わせる

問題に対する回答が見つからない場合は、Microsoft のサポート チームが随時、問題の解決をお手伝いします。

適切なサポートを提供するため、ケースをオープンする際はできるだけ詳しい情報のご提供をお願いいたします。 これらの詳細には、次の内容が含まれています。

- **エラーの一般的な説明**: どのようなエラーですか。 どのような動作が見られましたか。 エラーを再現することはできますか。 できるだけ詳しくお知らせください。
- **ページ**: エラーが表示されたときに、どのページを表示していましたか。 可能な場合はそのページの URL とスクリーンショットをお送りください。
- **サポート コード**: エラーが表示されたときに生成されたサポート コードをお知らせください。
    - このコードを見つけるには、エラーを再現してから、画面の下部にある**サポート コード**のリンクを選択し、生成された GUID をサポート エンジニアに送信します。

        [Image: サポート コードは、Web ブラウザー ウィンドウの右下にあります。]
    - ページの下部にサポート コードが表示されない場合は、F12 キーを押して SID と CID を検索し、この 2 つの結果をサポート エンジニアに送信します。
- **日付、時刻、タイム ゾーン**: エラーが発生した正確な日付、時刻、"*タイム ゾーン*" をお知らせください。
- **ユーザー ID**: エラーが表示されたユーザーをお知らせください。 たとえば *user@contoso.com*です。
    - このユーザーは、フェデレーション ユーザーですか。
    - このユーザーは、パススルー認証ユーザーですか。
    - このユーザーは、パスワード ハッシュ同期ユーザーですか。
    - このユーザーは、クラウド限定ユーザーですか。
- **ライセンス**: ユーザーに Microsoft Entra ID のライセンスが割り当てられていますか?
- **アプリケーション イベント ログの**: パスワード ライトバックを使用していて、エラーがオンプレミスインフラストラクチャにある場合は、Microsoft Entra Connect サーバーからアプリケーション イベント ログの zip 形式のコピーを含めます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/troubleshoot-voice-call-sms"} -->
## MFA 音声通話と SMS に関する問題のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/troubleshoot-voice-call-sms
- Service: entra-id / authentication
- Article date: 2026-04-09
- Summary: MFAに関する音声通話とSMSの問題をトラブルシューティングし、通信配信を理解し、Microsoft Supportが配信の問題を調査する際にサポートする方法について説明します。

この記事では、多要素認証 (MFA) の音声通話または SMS サインイン方法に関する問題を調査して解決する方法について説明します。 このセクションでは、通信配信がバックグラウンドでどのように機能するか、Microsoft が見ることができるもの、見ることができないもの、調査中に何を期待するかを説明します。

### フィッシングに強い最新の認証に移行する

Important

Microsoftは、組織がフィッシングに強い最新の認証方法を採用できるように取り組んでいます。 通信ベースの MFA はセキュリティの重要なレイヤーを提供しますが、パスキー、証明書ベースの認証、Windows Hello for Business などの方法では、傍受やソーシャル エンジニアリング攻撃に対するより強力な保護が提供されます。

これらの方法は、グローバル通信ネットワークに固有の配信の変動も回避します。 Microsoftでは、次のより安全な代替手段に移行することをお勧めします。

- [パスキー (FIDO2)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passwordless#fido2-security-keys)
- [Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/)
- [証明書ベースの認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication)

### グローバル通信配信のしくみ

Microsoftは、グローバルに利用できる主要な通信プロバイダーと連携します。 これらのプロバイダーは、ローカルのエンド キャリア ネットワークを介して SMS と音声通話のルートを制御するサプライヤーの独自のネットワークに依存しています。 このサプライ チェーンは、世界中の何千もの個々の運送業者とルートを表しています。

Microsoft では、すべての SMS および音声 MFA 要求の配信を確実に成功させるために、堅牢なプロセスを維持しています。 ただし、要求が Microsoft ネットワークから離れると、外部通信プロバイダーに配信が委任されます。

これらのプロバイダーは、多くの場合、特定の目的地の国で同じ最終ルートと運送業者を共有しますが、異なる契約契約または交通の優先順位の下で動作する可能性があります。 その結果、別のプロバイダーを介して要求をルーティングしても、常に問題が解決されるわけではありません。新しい要求は同じダウンストリーム パスに従い、同じ障害が発生する可能性があります。

### SMS または音声 MFA 要求の流れ

サインインし、SMS または音声 MFA チャレンジがトリガーされると、Microsoft は関連付け ID とタイムスタンプを指定して次の手順を確認できます。

1. **要求が受信されました。** Microsoft Entra は、MFA 要求を生成し、想定される Microsoft または会社のブランドを使用して SMS または音声要求のペイロードを構築します。
2. **プロバイダーが選択されています。** Microsoft は、お客様の国コードに一致する複数の外部サード パーティの通信プロバイダーのいずれかに要求を送信します。
3. **チャネルが一致しました。** 要求は、構成、選択、ポリシーの要件に従って、適切なチャネル (SMS または音声) を介してルーティングされます。
4. **配信が確認されました。** プロバイダーは通信要求の受信を確認し、変更なしで正常に配信されたとマークします。

プロバイダーが配信を確認すると、要求は、Microsoft が直接可視性を持たない外部通信ネットワークに入ります。 この時点を超えて発生する問題を調査するには、プロバイダーとのコラボレーションが必要です。

### プロバイダーへの配信後に問題が発生する可能性があります

要求が外部通信プロバイダーに到達すると、2 種類の問題が発生する可能性があります。

#### 呼び出しまたはメッセージが破棄またはブロックされる

プロバイダーは、この問題をMicrosoftに報告する必要があります。 一般的な原因には、次のようなものがあります。

- **デバイス レベルの構成。** メッセージを送信するすべての手順が正しい場合でも、デバイス自体は通話を拒否したり、ボイスメールにルーティングしたりすることができます。 Microsoftは、異なるプロバイダー間でこれらのエラーを自動的に再試行します。
- **キャリア側でのブロック。** 国際自動不正検出システムにより、通信事業者が通話またはメッセージをブロックする場合があります。
- **リージョンの可用性の問題。** まれに、特定の国の可用性の問題が原因でメッセージが削除されます。 Microsoftとそのプロバイダーは、これらのシナリオに対して堅牢な再試行と再ルーティングのメカニズムを備え、影響が広く報告されたときに自動停止通信を行います。

#### 呼び出しまたはメッセージは、ダウンストリームの通信事業者によって変更されます

プロバイダーは既に配信を確認しているため、Microsoftは仕様に従って正常な配信のみを確認できます。 メッセージの内容が変更された場合:

- 顧客は、デバイスで受信したメッセージが、想定されるブランドまたはチャネルと共に表示されなかったことを示す証拠を提供する必要があります。
- Microsoftプロバイダーと協力して、要求に代わって実行されるエンド キャリアの動作を理解します。
- プロバイダーは、影響を受ける国番号または電話番号のルートを修正して、元のメッセージ コントラクトを受け入れていない通信事業者を回避する可能性があります。

### 調査中に予想される内容

グローバルな通信ルーティングの複雑さとプロバイダーを超えた可視性の制限により、通信配信の調査には、複数の企業と利害関係者間の調整が含まれます。

#### タイムライン

ダウンストリームの顧客から報告された配信の問題の調査には、4 ~ 6 週間かかる場合があります。 プロバイダーはログをチェックし、ローカルで問題が見つからない場合は、追加のプロバイダーを使用する可能性があるダウンストリーム パートナーにクエリを実行します。

#### 根本原因分析 (RCA) ポリシー

Microsoftは、外部通信プロバイダーに起因する問題の RCA を発行しません。 多くの場合、通信事業者は、国際的な可視性と競争上の合意により、根本原因の詳細をMicrosoftと共有しません。

### 調査に関するヘルプ

Microsoftのサポートがプロバイダー関連の問題を調査するのを支援するには:

- 調査が必要なサインインの**関連付け ID とタイムスタンプを送信**します。 これらの値は [、サインイン ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)で確認できます。 関連付け ID は、サインインに関連付けられているすべての要求 ID を返します。

    Microsoft Entra admin centerからサインイン ログを表示するには:

    1. [Microsoft Entra admin center](https://entra.microsoft.com) に少なくとも [Reports Reader](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader) としてサインインします。
    2. **Entra ID**&gt;**Monitoring & health**&gt;**Sign-in logs** に移動して参照します。

    [Image: Microsoft Entra 管理センターのサインイン ログにおける [日付] フィールドと [関連 ID] フィールドを示すスクリーンショット]
- **新しいサンプルを提供します。** エンド キャリアでは、多くの場合、過去 48 ~ 72 時間の関連付け ID が必要です。 古い要求は、調査に間に合って最終的なエンド キャリアに伝達されない場合があります。 複数のサンプルを提供するように求められる場合があります。
- **変更されたメッセージの証拠を提供します。** 受信したメッセージが、想定されるブランドまたはチャネルで表示されなかった場合は、受信した内容のスクリーンショットまたは説明を共有します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/tutorial-configure-custom-password-protection"} -->
## カスタム Microsoft Entra パスワード保護リストを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-configure-custom-password-protection
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: このチュートリアルでは、カスタムの Microsoft Entra ID 禁止パスワード保護一覧を構成して、環境内でよく使用される単語を制限する方法について説明します。

ユーザーは多くの場合、学校、スポーツ チーム、有名人などのありふれたローカル単語を使用してパスワードを作成します。 これらのパスワードは簡単に推測できるため、辞書ベースの攻撃に対しては脆弱です。 組織で強力なパスワードを適用するために、Microsoft Entra カスタム禁止パスワード リストを使用すると、評価およびブロックする特定の文字列を追加できます。 カスタムの禁止パスワードの一覧に一致するものがある場合、パスワードの変更要求はエラーとなります。

このチュートリアルで学習する内容は次のとおりです。

- カスタムの禁止パスワードを有効にする
- カスタムの禁止パスワードの一覧にエントリを追加する
- 禁止パスワードを使用してパスワードの変更をテストする

### 前提条件

このチュートリアルを完了するには、以下のリソースと特権が必要です。

- Microsoft Entra ID P1 以上か試用版のライセンスが有効になっている稼働中の Microsoft Entra テナント。
    - 必要に応じて、 [無料で作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 少なくとも [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) ロールを持つアカウント。
- あなたが知っているパスワードを持つ管理者以外のユーザー、例えば*testuser*。 このチュートリアルでは、このアカウントを使用してパスワード変更イベントをテストします。
    - ユーザーを作成する必要がある場合は、「 [クイック スタート: Microsoft Entra ID に新しいユーザーを追加する」を](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users)参照してください。
    - 禁止パスワードを使用してパスワード変更操作をテストするには、Microsoft Entra テナントが [セルフサービス パスワード リセット用に構成されている](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr)必要があります。

### 禁止パスワードの一覧とは

Microsoft Entra ID には、グローバル禁止パスワード リストが含まれています。 グローバル禁止パスワードの一覧の内容は、どの外部データ ソースにも基づいていません。 代わりに、グローバル禁止パスワードの一覧は、Microsoft Entra のセキュリティ テレメトリと分析の継続的な結果に基づいています。 ユーザーまたは管理者が自分の資格情報を変更またはリセットしようとすると、希望するパスワードが禁止パスワードの一覧に照らしてチェックされます。 グローバル禁止パスワードの一覧に一致するものがある場合、パスワードの変更要求はエラーとなります。 この既定のグローバル禁止パスワード リストを編集することはできません。

許可されるパスワードの柔軟性を高めるために、カスタムの禁止パスワードの一覧を定義することもできます。 カスタムの禁止パスワードの一覧とグローバル禁止パスワードの一覧を組み合わせて使用することで、自分の組織内に強力なパスワードを適用できます。 カスタムの禁止パスワードの一覧には、次の例のような組織固有の用語を追加できます。

- ブランド名
- 製品名
- 場所 (本社など)
- 会社固有の内部用語
- 会社固有の意味を持つ略語
- 貴社のローカル言語を使用した月と曜日

ユーザーが、パスワードをグローバルまたはカスタムの禁止パスワードの一覧に記載されているものにリセットしようとすると、次のエラー メッセージのいずれかが表示されます。

- *残念ながら、パスワードを簡単に推測できる単語、語句、またはパターンが含まれています。 別のパスワードで再実行してください。*
- *残念ながら、管理者によってブロックされている単語または文字が含まれているためにそのパスワードを使用できません。 別のパスワードで再実行してください。*

カスタムの禁止パスワードの一覧は、最大 1,000 個の用語に制限されています。 多数のパスワードをブロックできるようには設計されていません。 カスタム禁止パスワード リストの利点を最大限に活用するには、 [カスタム禁止パスワード リストの概念](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad#custom-banned-password-list) と [パスワード評価アルゴリズムの概要](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad#how-are-passwords-evaluated)を確認します。

### カスタムの禁止パスワードを構成する

カスタムの禁止パスワードの一覧を有効にし、いくつかのエントリを追加してみましょう。 カスタムの禁止パスワードの一覧には、いつでも新しいエントリを追加できます。

カスタムの禁止パスワードの一覧を有効にし、エントリを追加するには、次の手順を実行します。

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **Entra ID**&gt;**Authentication メソッド**、**パスワード保護**の順に参照します。
3. [ **カスタム リストの適用]** オプションを *[はい*] に設定します。
4. **カスタム禁止パスワード リスト**に、1 行に 1 つの文字列を追加します。 カスタムの禁止パスワードの一覧には、次の考慮事項と制限事項が適用されます。

    - カスタムの禁止パスワードの一覧には、最大 1,000 個の用語を含めることができます。
    - カスタム禁止パスワード リストでは、大文字と小文字は区別されません。
    - カスタムの禁止パスワードの一覧では、一般的な文字の置き換え ("o" と "0" や "a" と "@" など) が考慮されています。
    - 最小文字数は 4 文字で、最大文字数は 16 文字です。

    次の例に示すように、禁止する独自のカスタム パスワードを指定します

    [Image: [認証方法] のカスタム禁止パスワード リストを変更する]
5. **[Windows Server Active Directory でのパスワード保護を有効にする]** のオプションは *[いいえ*] のままにします。
6. カスタム禁止パスワードとエントリを有効にするには、[ **保存]** を選択します。

カスタム禁止パスワード リストの更新が適用されるまでに数時間かかることがあります。

ハイブリッド環境の場合は、 [Microsoft Entra パスワード保護をオンプレミス環境に展開](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-deploy)することもできます。 クラウドとオンプレミスの両方のパスワード変更要求に対して、同一のグローバルおよびカスタム禁止パスワードの一覧が使用されます。

### カスタムの禁止パスワードの一覧をテストする

カスタムの禁止パスワードの一覧が機能していることを確認するには、パスワードを、前のセクションで追加したパスワードとわずかに異なるものに変更してみます。 Microsoft Entra ID でパスワードの変更が処理される際に、そのパスワードがカスタムの禁止パスワードの一覧にあるエントリと照合されます。 すると、エラーがユーザーに表示されます。

注

ユーザーが Web ベースのポータルでパスワードをリセットする前に、セルフサービス パスワード リセット用に Microsoft Entra テナント [を構成](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr)する必要があります。 必要に応じて、ユーザーは [https://aka.ms/ssprsetup で SSPR に登録](https://aka.ms/ssprsetup)できます。

1. **マイアプリ** ページにhttps://myapps.microsoft.com移動します。
2. 右上隅で自分の名前を選択し、ドロップダウン メニューから [ **プロファイル** ] を選択します。

    [Image: プロファイルの選択]
3. [ **プロファイル** ] ページで、[ **パスワードの変更**] を選択します。
4. [ **パスワードの変更** ] ページで、既存の (古い) パスワードを入力します。 前のセクションで定義したカスタム禁止パスワードリストにある新しいパスワードを入力して確認し、[ **送信]** を選択します。
5. 次の例に示すように、管理者によってパスワードがブロックされたことを示すエラー メッセージが返されます。

    [Image: カスタム禁止パスワード リストの一部であるパスワードを使用しようとすると、エラー メッセージが表示される]

### リソースをクリーンアップする

このチュートリアルの一環として構成したカスタムの禁止パスワードの一覧をもう使用しない場合は、次の手順を実行します。

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **Entra ID**&gt;**Authentication メソッド**、**パスワード保護**の順に参照します。
3. [ **カスタム リストを適用する]** のオプションを *[いいえ*] に設定します。
4. カスタム禁止パスワード構成を更新するには、[ **保存]** を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/tutorial-enable-azure-mfa"} -->
## Microsoft Entra 多要素認証を有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-azure-mfa
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: このチュートリアルでは、ユーザーのグループに対して Microsoft Entra 多要素認証を有効にし、サインイン イベント時に第 2 要素のプロンプトをテストする方法について説明します。

多要素認証 (MFA) は、サインイン・イベントの間に、ユーザが追加的な識別形態を要求されるプロ セスであります。 たとえば、携帯電話でのコード入力や指紋のスキャンが求められる場合があります。 2 つ目の認証形式を要求すると、この追加要素は、攻撃者が容易に取得したり複製したりできないため、セキュリティが向上します。

Microsoft Entra 多要素認証と条件付きアクセス ポリシーを使用すると、特定のサインイン イベント中にユーザーに MFA を要求する柔軟性が得られます。

重要

このチュートリアルでは、管理者が Microsoft Entra 多要素認証を有効にする方法について説明します。 ユーザーとして多要素認証をステップ実行するには、「 [2 段階認証方法を使用して職場または学校アカウントにサインイン](https://support.microsoft.com/account-billing/sign-in-to-your-work-or-school-account-using-your-two-step-verification-method-c7293464-ef5e-4705-a24b-c4a3ec0d6cf9)する」を参照してください。

IT チームが Microsoft Entra 多要素認証を使用する機能を有効にしていない場合、またはサインイン時に問題が発生した場合は、ヘルプ デスクに連絡して追加のサポートを依頼してください。

このチュートリアルで学習する内容は次のとおりです。

- ユーザーのグループに対してMicrosoft Entra 多要素認証を有効にする条件付きアクセス ポリシーを作成する。
- MFA を要求する条件を設定するポリシーを構成する。
- ユーザーとして多要素認証の構成と使用をテストする。

### 前提条件

このチュートリアルを完了するには、以下のリソースと特権が必要です。

- Microsoft Entra ID P1 か試用版のライセンスが有効になっている稼働中の Microsoft Entra テナント。

    - 必要に応じて、 [無料で作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 少なくとも [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) ロールを持つアカウント。 一部の MFA 設定は、 [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)が管理することもできます。
- 既知のパスワードを持つ非管理者アカウント。 このチュートリアルでは、 *testuser* という名前のアカウントを作成しました。 このチュートリアルでは、Microsoft Entra 多要素認証を構成して使用するエンドユーザー エクスペリエンスをテストします。

    - ユーザー アカウントの作成に関する情報が必要な場合は、「 [Microsoft Entra ID を使用してユーザーを追加または削除](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users)する」を参照してください。
- 管理者以外のユーザーが所属するグループ。 このチュートリアルでは、 *MFA-Test-Group* という名前のグループを作成しました。 このチュートリアルでは、このグループに対して Microsoft Entra 多要素認証を有効にします。

    - グループの作成の詳細については、「 [基本的なグループを作成し、Microsoft Entra ID を使用してメンバーを追加](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups)する」を参照してください。

### 条件付きアクセス ポリシーを作成する

Microsoft Entra Multi-Factor Authentication を有効にして使用する推奨の方法は、Conditional Access ポリシーを使用することです。 条件付きアクセスを使用すると、サインイン イベントに反応し、アプリケーションまたはサービスへのアクセスをユーザーに許可する前に二次的なアクションを要求するポリシーを作成および定義することができます。

[Image: サインイン プロセスをセキュリティで保護するための条件付きアクセスのしくみの概要図]

条件付きアクセス ポリシーは、特定のユーザー、グループ、アプリに適用できます。 目標は、組織を保護しながら、アクセスを必要とするユーザーに適切なレベルのアクセスを提供することです。

このチュートリアルでは、ユーザーがサインインしたときに MFA を求める基本的な条件付きアクセス ポリシーを作成します。 このシリーズの後続のチュートリアルでは、リスクベースの条件付きアクセス ポリシーを使用して Microsoft Entra 多要素認証を構成します。

まず、次のとおり条件付きアクセス ポリシーを作成し、ユーザーのテスト グループを割り当てます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **Entra ID**&gt;**Conditional Access**&gt; Overview に移動し、[ **+ 新しいポリシーの作成**] を選択します。

[Image: [条件付きアクセス] ページのスクリーンショット。[新しいポリシー] を選択し、[新しいポリシーの作成] を選択します。]

1. ポリシーの名前 ( *MFA パイロット*など) を入力します。
2. [ **割り当て]** で、[ **ユーザーまたはワークロード ID**] で現在の値を選択します。

    [Image: [条件付きアクセス] ページのスクリーンショット。[ユーザーまたはワークロード ID] で現在の値を選択します。]
3. [ **このポリシーの適用対象] で**、[ **ユーザーとグループ** ] が選択されていることを確認します。
4. [ **含める**] で、[ **ユーザーとグループの選択**] を選択し、[ **ユーザーとグループ**] を選択します。

    [Image: 新しいポリシーを作成するためのページのスクリーンショット。ユーザーとグループを指定するオプションを選択します。]

    まだ割り当てられていない場合は、ユーザーとグループの一覧 (次の手順を参照) が自動的に開きます。
5. *MFA-Test-Group* などの Microsoft Entra グループを参照して選択し、[選択] を**選択します**。

    [Image: ユーザーとグループの一覧のスクリーンショット。結果は文字 M F A でフィルター処理され、[MFA-Test-Group] が選択されています。]

ポリシーを適用するグループを選択しました。 次のセクションでは、ポリシーを適用する条件を構成します。

### 多要素認証の条件を構成する

条件付きアクセス ポリシーを作成し、ユーザーのテスト グループを割り当てたので、ポリシーをトリガーするクラウド アプリまたはアクションを定義します。 これらのクラウド アプリまたはアクションは、追加の処理 (多要素認証の要求など) を要求することを決定するシナリオです。 たとえば、財務アプリケーションへのアクセスまたは管理ツールの使用には、追加の認証を要求する必要があると決定した場合などが考えられます。

#### 多要素認証を要求するアプリを構成する

このチュートリアルでは、ユーザーがサインインするときに多要素認証を要求するように条件付きアクセス ポリシーを構成します。

1. [ **クラウド アプリまたはアクション**] で現在の値を選択し、[ **このポリシーの適用対象を選択]** で [ **クラウド アプリ** ] が選択されていることを確認します。
2. [ **含める**] で、[ **リソースの選択**] を選択します。

    アプリがまだ選択されていないので、アプリの一覧 (次の手順を参照) が自動的に開きます。

    ヒント

    条件付きアクセス ポリシーを **すべてのリソース (以前は "すべてのクラウド アプリ") に** 適用するか **、リソースを選択**するか選択できます。 柔軟に、特定のアプリをポリシーから除外することもできます。
3. 使用可能なサインイン イベントの一覧を参照します。 このチュートリアルでは、ポリシーがサインイン イベントに適用されるように、 **Windows Azure Service Management API** を選択します。 次に、[選択] を **選択します**。

    [Image: 新しいポリシーが適用されるアプリである Windows Azure サービス管理 API を選択する [条件付きアクセス] ページのスクリーンショット。]

#### アクセスのための多要素認証を構成する

次に、アクセスの制御を構成します。 アクセスの制御を使用すると、ユーザーがアクセス権を付与されるための要件を定義できます。 承認されたクライアント アプリまたは Microsoft Entra ID にハイブリッド結合されたデバイスを使用することが必要な場合があります。

このチュートリアルでは、サインイン イベント時に多要素認証を要求するようにアクセスの制御を構成します。

1. [ **アクセス制御**] で、[ **許可**] で現在の値を選択し、[ **アクセス権の付与**] を選択します。

    [Image: [条件付きアクセス] ページのスクリーンショット。[許可] を選択し、[アクセス権の付与] を選択します。]
2. [ **多要素認証が必要]** を選択し、[選択] を **選択します**。

    [Image: [多要素認証を要求する] を選択した、アクセス権を付与するためのオプションのスクリーンショット。]

#### ポリシーをアクティブ化する

条件付きアクセス ポリシーは、構成がユーザーにどのように影響するかを確認する場合は **レポート専用** に設定できます。現在使用ポリシーを使用しない場合は **オフ** に設定できます。 このチュートリアルではテストグループのユーザーを対象としているので、ポリシーを有効にして、Microsoft Entra Multi-Factor Authentication をテストしてみます。

1. **ポリシーの有効化** で **オン** を選択します。

    [Image: ポリシーを有効にするかどうかを指定する Web ページの下部付近にあるコントロールのスクリーンショット。]
2. 条件付きアクセス ポリシーを適用するには、[ **作成**] を選択します。

### Microsoft Entra の多要素認証をテストする

条件付きアクセス ポリシーと Microsoft Entra 多要素認証が機能していることを確認しましょう。

まず、MFA が要求されないリソースにサインインします。

1. InPrivate または incognito モードで新しいブラウザー ウィンドウを開き、https://account.activedirectory.windowsazure.com に移動します。

    ブラウザーのプライベート モードを使用すると、既存の資格情報がこのサインイン イベントに影響を与えるのを防ぐことができます。
2. 管理者以外のテスト ユーザー ( *testuser* など) でサインインします。 必ず、`@` とユーザー アカウントのドメイン名を含めてください。

    このアカウントを使用して初めてサインインする場合は、パスワードを変更するように求められます。 ただし、多要素認証の構成または使用を求めるプロンプトは表示されません。
3. ブラウザー ウィンドウを閉じます。

サインインの追加認証を要求するように条件付きアクセス ポリシーを構成しました。 この構成であるため、Microsoft Entra 多要素認証を使用するか、まだ構成していない場合は方法を構成するように要求されます。 Microsoft Entra 管理センターにサインインし、この新しい要件をテストします。

1. InPrivate モードまたはシークレット モードで新しいブラウザー ウィンドウを開き、 [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. 管理者以外のテスト ユーザー ( *testuser* など) でサインインします。 必ず、`@` とユーザー アカウントのドメイン名を含めてください。

    Microsoft Entra 多要素認証に登録して使用する必要があります。

3. [ **次へ** ] を選択してプロセスを開始します。

    認証用電話、会社電話、またはモバイル アプリを認証用として構成することができます。 "認証用電話" では、テキスト メッセージと通話がサポートされます。"会社電話" では、内線のある番号への通話がサポートされます。"モバイル アプリ" では、認証の通知を受信したり、認証コードを生成したりするためのモバイル アプリの使用がサポートされます。

4. 画面の指示に従って、選択した多要素認証の方法を構成します。
5. ブラウザー ウィンドウを閉じ、 [Microsoft Entra 管理センター](https://entra.microsoft.com) にもう一度サインインして、構成した認証方法をテストします。 たとえば、認証用にモバイル アプリを構成した場合、次のようなプロンプトが表示されます。

    [Image: サインインするには、ブラウザーのプロンプトに従い、多要素認証に登録したデバイスのプロンプトに従います。]
6. ブラウザー ウィンドウを閉じます。

### リソースをクリーンアップする

このチュートリアルの中で構成した条件付きアクセス ポリシーを使用する必要がなくなった場合は、次の手順に従ってポリシーを削除します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **[ポリシー**&gt;**Conditional Access**] を参照し、作成したポリシー (**MFA パイロット**など) を選択します。
3. [ **削除]** を選択し、ポリシーを削除することを確認します。

    [Image: 開いた条件付きアクセス ポリシーを削除するには、ポリシーの名前の下にある [削除] を選択します。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/tutorial-enable-cloud-sync-sspr-writeback"} -->
## Microsoft Entra 接続のクラウド同期パスワード ライトバックを有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-cloud-sync-sspr-writeback
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: このチュートリアルでは、Microsoft Entra Connect クラウド同期を使用してセルフサービス パスワード リセット ライトバックMicrosoft Entra有効にして、変更をオンプレミスのActive Directory Domain Services環境に同期する方法について説明します。

注

クラウド同期を使用したセルフサービス パスワード リセット ライトバックは、21Vianet によって運用されるMicrosoft Azureではサポートされていません。 代わりに、管理者は [Microsoft Entra Connect 同期](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr-writeback)を使用して SSPR ライトバックをデプロイできます。

Microsoft Entraクラウド同期では、切断されたオンプレミス Active Directory Domain Services (AD DS) ドメイン内のユーザー間で、Microsoft Entraパスワードの変更をリアルタイムで同期できます。 Microsoft Entraクラウド同期は、ドメイン レベルで [Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr-writeback) とサイド バイ サイドで実行して、会社の分割やマージのために切断されたドメインにいるユーザーなど、追加のシナリオに対するパスワード ライトバックを簡略化できます。 さまざまなドメイン内の各サービスを、ニーズに応じてさまざまなユーザー セットが対象になるように構成できます。 Microsoft Entraクラウド同期では、軽量Microsoft Entraクラウド プロビジョニング エージェントを使用して、セルフサービス パスワード リセット (SSPR) ライトバックのセットアップを簡略化し、クラウド内のパスワード変更をオンプレミスディレクトリに安全に送信する方法を提供します。

### 前提条件

- 少なくとも Microsoft Entra ID P1 または試用版ライセンスが有効になっているMicrosoft Entra テナント。 必要に応じて、 [無料で作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- [ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)アカウント
- Microsoft Entra IDセルフサービス パスワード リセット用に構成されています。 必要に応じて、このチュートリアルを完了し、Microsoft Entra SSPR を有効にします。
- [Microsoft Entra クラウド同期バージョン 1.1.977.0 以降](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provisioning-agent-release-version-history)で構成されたオンプレミス AD DS 環境。 [エージェントの現在のバージョンを特定する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-automatic-upgrade)方法について説明します。

### デプロイメントの手順

1. Microsoft Entra クラウド同期サービス アカウントのアクセス許可を構成する
2. Microsoft Entra Connect クラウド同期でパスワード書き戻しを有効にする
3. SSPR のパスワード ライトバックを有効にする

#### Microsoft Entraのクラウド同期サービス アカウントのアクセス許可を構成する

クラウド同期のアクセス許可は、既定では構成済みになっています。 アクセス許可をリセットする必要がある場合は、パスワード ライトバックに必要な特定のアクセス許可と、PowerShell を使用して設定する方法の詳細については、 トラブルシューティング を参照してください。

#### SSPR でパスワード ライトバックを有効にする

Microsoft Entra Connect クラウド同期プロビジョニングは、Microsoft Entra管理センターまたは PowerShell を使用して直接有効にすることができます。

##### Microsoft Entra管理センターでパスワード ライトバックを有効にする

Microsoft Entra Connect クラウド同期でパスワード ライトバックが有効になっている場合、Microsoft Entra セルフサービス パスワード リセット (SSPR) のパスワード ライトバックを確認し、構成します。 SSPR でパスワード ライトバックを使用できるようにすると、自分のパスワードを変更またはリセットするユーザーは、その更新したパスワードがオンプレミスの AD DS 環境にも同期されるようになります。

SSPR でパスワード ライトバックを検証して有効にするには、次の手順を実行します。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも [Hybrid Identity Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) としてサインインします。
2. **Entra ID**&gt;**Password reset** に移動し、[**オンプレミス統合**] を選択します。
3. **同期されたユーザーのパスワードライトバックを有効にするオプションをオンにします**。
4. (省略可能)Microsoft Entra Connect プロビジョニング エージェントが検出された場合は、** Microsoft Entra Connect クラウド同期** を使用してパスワードを書き戻すオプションも確認できます。
5. [ユーザーがパスワードを **[はい**] *にリセットせずにアカウントのロックを解除できるようにする*オプションをオンにします。

    [Image: Microsoft Entra のパスワード ライトバックのためにセルフサービス パスワード リセットを有効にする]
6. 準備ができたら、**[保存]** を選択します。

##### PowerShell

PowerShell を使用すると、プロビジョニング エージェントを使用してサーバー上の Set-AADCloudSyncPasswordWritebackConfiguration コマンドレットを使用して、Microsoft Entra Connect クラウド同期を有効にすることができます。

```powershell
Import-Module 'C:\\Program Files\\Microsoft Azure AD Connect Provisioning Agent\\Microsoft.CloudSync.Powershell.dll' 
Set-AADCloudSyncPasswordWritebackConfiguration -Enable $true -Credential $(Get-Credential)
```

### リソースをクリーンアップする

このチュートリアルの一環として構成した SSPR の書き戻し機能を、もう使用しない場合は、次の手順を実行します。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも [Hybrid Identity Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) としてサインインします。
2. **Entra ID**&gt;**Password reset** に移動し、[**オンプレミス統合**] を選択します。
3. **同期されたユーザーのパスワードライトバックを有効にするオプションをオフにします**。
4. **Microsoft Entra Connect クラウド同期でパスワードを書き戻すオプション**をオフにします。
5. [ **ユーザーがパスワードをリセットせずにアカウントのロックを解除できるようにする] オプションを**オフにします。
6. 準備ができたら、**[保存]** を選択します。

SSPR ライトバック機能に Microsoft Entra Connect クラウド同期を使用しなくなったが、書き戻しに Microsoft Entra Connect Sync エージェントを引き続き使用する場合は、次の手順を実行します。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも [Hybrid Identity Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) としてサインインします。
2. **Entra ID**&gt;**Password reset** に移動し、[**オンプレミス統合**] を選択します。
3. **Microsoft Entra Connect クラウド同期でパスワードを書き戻す** オプションをオフにします。
4. 準備ができたら、**[保存]** を選択します。

PowerShell を使用して、SSPR ライトバック機能の Microsoft Entra Connect クラウド同期を無効にすることもできます。Microsoft Entra Connect クラウド同期サーバーから、ハイブリッド ID 管理者の資格情報を使用して `Set-AADCloudSyncPasswordWritebackConfiguration` を実行して、Microsoft Entra Connect クラウド同期によるパスワード ライトバックを無効にします。

```powershell
Import-Module ‘C:\\Program Files\\Microsoft Azure AD Connect Provisioning Agent\\Microsoft.CloudSync.Powershell.dll’ 
Set-AADCloudSyncPasswordWritebackConfiguration -Enable $false -Credential $(Get-Credential)
```

### サポート対象の操作

以下の状況において、エンドユーザーと管理者のパスワードが再度記録されます。

| アカウント | サポート対象の操作 |
| --- | --- |
| 最終利用者 | エンドユーザーが自らの意思で行う自己サービス型のパスワード変更操作。エンドユーザーが行うセルフサービスによるパスワード変更操作（例: パスワードの期限切れ）。エンドユーザーにより、パスワード リセットから実行されたセルフサービス パスワード リセット。 |
| 管理者 | 管理者による自発的なパスワード変更。管理者によるセルフサービスでの強制的なパスワード変更操作（例: パスワードの有効期限切れ）。管理者自身によるセルフサービスのパスワードリセットが、パスワードリセットから始まる場合。 管理者がMicrosoft Entra管理センターから開始したエンドユーザーパスワードのリセット。Microsoft Graph APIから管理者によって開始されるエンドユーザーパスワードのリセット。 |

### サポートされていない操作

パスワードは次の状況では書き戻されません。

| アカウント | サポートされていない操作 |
| --- | --- |
| 最終利用者 | PowerShell コマンドレットまたは Microsoft Graph APIを使用して、エンド ユーザーが自分のパスワードをリセットする。 |
| 管理者 | PowerShell コマンドレットを使って管理者が開始したエンド ユーザーのパスワードのリセット。管理者が開始したエンドユーザーパスワードのリセットは、Microsoft 365管理センターから行われます。パスワード リセット ツールを使用して自分のパスワードをリセットしたり、パスワード ライトバックのためにMicrosoft Entra IDの他の管理者を使用したりすることはできません。 |

### 検証シナリオ

パスワード ライトバックを使用してシナリオを検証する場合は、次の操作を行ってみてください。 すべての検証シナリオでは、クラウド同期がインストールされていて、ユーザーがパスワード ライトバックのスコープに含まれている必要があります。

| シナリオ | 詳細 |
| --- | --- |
| サインイン ページからパスワードをリセットする | 切断されているドメインとフォレストの 2 人のユーザーが SSPR を実行するようにします。 また、Microsoft Entra Connect とクラウド同期をサイド バイ サイドでデプロイし、クラウド同期構成のスコープに 1 人のユーザーを配置し、別のユーザーを Microsoft Entra Connect のスコープに配置し、それらのユーザーにパスワードをリセットしてもらうこともできます。 |
| 期限切れのパスワードの変更を強制する | 切断されているドメインとフォレストの 2 人のユーザーが期限切れのパスワードを変更するようにします。 また、Microsoft Entra Connect とクラウド同期をサイド バイ サイドでデプロイし、クラウド同期構成のスコープに 1 人のユーザーを配置し、もう 1 人のユーザーを Microsoft Entra Connect のスコープに配置することもできます。 |
| 通常のパスワード変更 | 切断されているドメインとフォレストの 2 人のユーザーが通常のパスワード変更を行うようにします。 また、Microsoft Entra Connect とクラウド同期を並べて、クラウド同期構成のスコープに 1 人のユーザーを配置し、もう 1 人のユーザーを Microsoft Entra Connect のスコープに配置することもできます。 |
| 管理者がユーザーのパスワードをリセットしました | 2 人のユーザーがドメインとフォレストを切断して、Microsoft Entra管理センターまたは現場担当者ポータルからパスワードをリセットさせます。 また、Microsoft Entra Connect とクラウド同期をサイド バイ サイドにして、クラウド同期構成のスコープに 1 人のユーザーを配置し、もう 1 人のユーザーを Microsoft Entra Connect のスコープに配置することもできます。 |
| セルフサービス アカウントのロック解除 | 切断されているドメインとフォレストの 2 人のユーザーが、SSPR ポータルでアカウントのロックを解除してパスワードをリセットするようにします。 また、Microsoft Entra Connect とクラウド同期を並べて、クラウド同期構成のスコープに 1 人のユーザーを配置し、もう 1 人のユーザーを Microsoft Entra Connect のスコープに配置することもできます。 |

### トラブルシューティング

- Microsoft Entra Connect クラウド同期グループのマネージド サービス アカウントには、既定でパスワードを書き戻す次のアクセス許可が設定されている必要があります。

    - [パスワードのリセット]
    - lockoutTime に対する書き込みアクセス許可
    - pwdLastSet に対する書き込みアクセス許可
    - まだ設定していない場合は、そのフォレスト内の各ドメインのルート オブジェクトに対する「パスワードの有効期限を解除する」ための拡張された権限。

    これらのアクセス許可が設定されていない場合は、Set-AADCloudSyncPermissions コマンドレットとオンプレミスのエンタープライズ管理者の資格情報を使用して、サービス アカウントに対する PasswordWriteBack アクセス許可を設定できます。

    ```powershell
    Import-Module ‘C:\\Program Files\\Microsoft Azure AD Connect Provisioning Agent\\Microsoft.CloudSync.Powershell.dll’ 
    Set-AADCloudSyncPermissions -PermissionType PasswordWriteBack -EACredential $(Get-Credential)
    ```

    アクセス許可を更新した後、ディレクトリ内のすべてのオブジェクトにこれらのアクセス許可がレプリケートされるまで最大で 1 時間以上かかる場合があります。
- 一部のユーザー アカウントのパスワードがオンプレミスのディレクトリにライトバックされない場合は、オンプレミスの AD DS 環境でそのアカウントの継承が無効になっていないことを確認してください。 この機能を正常に動作させるには、パスワードの書き込みアクセス許可を子孫オブジェクトに適用する必要があります。
- オンプレミスの AD DS 環境のパスワード ポリシーによって、パスワードのリセットが正しく処理されない場合があります。 この機能をテストしていて、ユーザーのパスワードを 1 日に複数回リセットする場合は、[パスワードの変更禁止期間] のグループ ポリシーを 0 に設定する必要があります。 この設定は、gpmc.msc 内のコンピューター構成&gt; ポリシー &gt; Windows 設定 &gt; セキュリティ設定 &gt; アカウント ポリシー &gt; パスワード ポリシーにあります。
- グループ ポリシーを更新する場合は、更新されたポリシーがレプリケートされるまで待つか、gpupdate /force コマンドを使用します。
- パスワードがすぐに変更されるようにするには、 [パスワードの変更禁止期間] を 0 に設定する必要があります。 ただし、ユーザーがオンプレミスのポリシーに従って、パスワードの最小有効期間が 0 より大きい値に設定している場合、オンプレミス ポリシーの評価後にパスワード ライトバックは機能しません。

適切なアクセス許可を検証または設定する方法の詳細については、「[Microsoft Entra Connect のアカウントアクセス許可の構成](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr-writeback#configure-account-permissions-for-azure-ad-connect)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/tutorial-enable-security-notifications-for-audit-logs"} -->
## 監査ログ イベントのセキュリティ通知を有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-security-notifications-for-audit-logs
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: Microsoft Entra 監査ログを監視し、さまざまな監査ログ イベントに基づいてセキュリティ メール通知をユーザーに送信する Azure ロジック アプリを作成します。

このチュートリアルでは、Microsoft Entra 監査ログを監視する [Azure ロジック アプリ](https://learn.microsoft.com/ja-jp/azure/logic-apps/logic-apps-overview)を作成する方法について説明します。 ロジック アプリは、さまざまな監査ログ イベントに基づいて、セキュリティの電子メールによる通知をユーザーに送信できます。

このチュートリアルでは、ユーザーの認証方法が変更されたときに電子メールで送信されるセキュリティ通知について説明します。 ロジック アプリを使用して、他の監査ログ イベントのセキュリティ通知を送信するワークフローを作成することもできます。 これらのセキュリティ通知は、ユーザーを更新し、危険なアクティビティを通知するのに役立ちます。 ユーザーは、適切な手順を迅速に実行して報告できます。

[Image: セキュリティ通知のスクリーンショット。]

### 前提条件

この機能を使用するには、次が必要です。

- Azure サブスクリプション。 Azure サブスクリプションを持っていない場合は、[無料試用版にサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- Microsoft Entra テナント。
- Microsoft Entra テナントの[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)以上であるユーザー。
- Azure サブスクリプション内の Event Hubs 名前空間とイベント ハブ。 [イベント ハブの作成](https://learn.microsoft.com/ja-jp/azure/event-hubs/event-hubs-create)方法に関するページを参照してください。
- ログをイベント ハブにストリーミングできるようにします。 ログをイベント ハブにストリーム配信する方法については、[こちら](https://learn.microsoft.com/ja-jp/azure/azure-monitor/essentials/stream-monitoring-data-event-hubs)を参照してください。 セキュリティ通知を送信するログのみを選択します。 このチュートリアルでは、監査ログをストリーミングします。
- Office 365 Outlook や Outlook.com など、Azure Logic Apps と連携するサービスの電子メール アカウント。 サポートされている他の電子メール プロバイダーについては、[Azure Logic Apps のコネクタ](https://learn.microsoft.com/ja-jp/connectors/connector-reference/connector-reference-logicapps-connectors)に関する記事をご覧ください。

### ロジック アプリを作成します

1. Azure portal にサインインします。
2. ホーム ページの **[Azure サービス]** の下にある **[Logic Apps]** を選択します。
3. **[追加]** を選択します。
4. **[ロジック アプリを作成する]**で、ロジック アプリを構成します。
    1. ロジック アプリを作成する **[サブスクリプション]** を選択します。
    2. イベント ハブ用に作成した **[リソース グループ]** を選択します。
    3. **[ロジック アプリ名]** を入力すると、その名前が使用できるかどうかがすぐに自動で確認されます。
    4. ロジック アプリの **[リージョン]** を選択します。
    5. **[プランの種類]** で **[従量課金]** 階層を選択します。 組織のサイズとニーズに合ったリージョンとプランの種類を選択します。 階層間の違いについては、「[Standard および従量課金ロジック アプリのワークフロー](https://learn.microsoft.com/ja-jp/azure/logic-apps/logic-apps-overview#create-and-deploy-to-different-environments)」を参照してください。
    6. 他の設定は変更しないでください。

        注

        ゾーン冗長をサポートするのは一部のリージョンのみです。 場所によっては、ゾーン冗長セクションが自動的に有効または無効になる可能性があります。 詳細については、「[ゾーン冗長性と可用性ゾーンを使用してリージョンの障害からロジック アプリを保護する](https://learn.microsoft.com/ja-jp/azure/logic-apps/set-up-zone-redundancy-availability-zones)」を参照してください。

        [Image: アプリケーション名のスクリーンショット。]
    7. **[Review + create](レビュー + 作成)** を選択します。 次に、ロジック アプリの設定を確認し、**[作成]** を選択します。
    8. デプロイが完了するまで待ってください。

### 空のテンプレートを選択する

1. Azure でロジック アプリ リソースが正常にデプロイされたら、**[リソースに移動]** を選択するか、Azure 検索ボックスに名前を入力してロジック アプリ リソースを検索して選択します。

    [Image: [リソースに移動] のスクリーンショット。]
2. ビデオの後まで下スクロールし、**[テンプレート]** の下で **[空のロジック アプリ]** を選択します。 テンプレートを選ぶと、デザイナーに空のワークフローが表示されます。

    [Image: 空のロジック アプリのスクリーンショット。]

### Logic Apps デザイナー

1. [コネクタとトリガー] セクションで、**[Event Hubs]** を選択するか、検索バーで検索します。

    [Image: [イベント ハブ] のスクリーンショット。]
2. **[Event Hubs トリガーでイベントを使用できるとき]** を選択します。 Event Hubs トリガーを初めて使用すると、イベント ハブへの接続を作成するように求められます。 詳細および手順については、「[イベント ハブ接続の作成](https://learn.microsoft.com/ja-jp/azure/connectors/connectors-create-api-azure-event-hubs#create-an-event-hub-connection)」を参照してください。
3. **[Event Hub 名]** で、[前提条件] で作成したイベント ハブを選びます。 ロジック アプリでセキュリティ通知を送信するイベント ハブを選択します。
4. **[項目のチェック頻度]** の下で、イベント ハブをチェックする頻度を選択します。 このチュートリアルでは、1 分ごとにイベントをチェックします。

    [Image: イベントを確認する頻度のスクリーンショット。]

### 変数を初期化する

ここでは、3 つの変数を初期化します。 1 つは、トリガーされ、イベント ハブにストリーミングされたイベントの内容です。 他の 2 つの変数は、電子メール本文の空の変数と、アクティビティの日付と時刻であり、後でイベントの情報を入力します。

1. デザイナーの **[Event Hubs トリガーでイベントを使用できるとき]** の下で、**[新しいステップ]** を選択します。
2. **[操作を選択してください]** で **[組み込み]** を選択します。 検索ボックスに「*変数*」と入力し、**[変数を初期化する]** を選択します。

    [Image: [変数の初期化] のスクリーンショット。]
3. **[名前]** で、「*コンテンツ*」と入力します。
4. **[型]** で **[文字列]** を選択します。
5. **[値]** プロパティにカーソルを置くと、**[動的コンテンツ]** が表示されます。
6. **[動的コンテンツ]** ペインで、**[コンテンツ]** を検索して選択します。

    [Image: [動的コンテンツ] のスクリーンショット。]
7. **[新しいステップ]** を選択します。
8. **[操作を選択してください]** で **[組み込み]** を選択します。 検索ボックスに「*変数*」と入力し、**[変数を初期化する]** を選択します。
9. 変数に *emailBody* などの 名前を付けます。
10. **[タイプ]** で、**[文字列]** を選択し、**[値]** を空白のままにします。

    [Image: メール本文変数のスクリーンショット。]
11. **[新しいステップ]** を選択します。
12. **[操作を選択してください]** で **[組み込み]** を選択します。 検索ボックスに「*変数*」と入力し、**[変数を初期化する]** を選択します。
13. 変数に *dateTime* などの 名前を付けます。
14. **[タイプ]** で、**[文字列]** を選択し、**[値]** を空白のままにします。

    [Image: date-time 変数の初期化のスクリーンショット。]

### JSON の解析

次に、JSON を解析してイベント ハブにストリーミングされたイベントから受信した生の JSON を書式設定し、そのコンテンツ内の特定のデータにアクセスできるようにします。

1. **[変数 3 の初期化]** の下で、**[新しいステップ]** を選択します。
2. [コネクタとアクションを検索する] 検索バーに、「*Parse JSON*」と入力します。
3. **[アクション]** タブに切り替えて、**[Parse JSON]** を選択します。

    [Image: [変数の初期化] のスクリーンショット。]
4. **[コンテンツ]** で、**[動的コンテンツの追加]** を選択します。
5. **[動的コンテンツ]** で、[変数] の下の **[コンテンツ]** を選択します。
6. [スキーマ] セクションで、次の JSON テンプレートをコピーして貼り付けます。

    ```json
    {
     "type": "object",
     "properties": {
         "records": {
             "type": "array",
             "items": {
                 "type": "object",
                 "properties": {
                     "time": {
                         "type": "string"
                     },
                     "resourceId": {
                         "type": "string"
                     },
                     "operationName": {
                         "type": "string"
                     },
                     "operationVersion": {
                         "type": "string"
                     },
                     "category": {
                         "type": "string"
                     },
                     "tenantId": {
                         "type": "string"
                     },
                     "resultSignature": {
                         "type": "string"
                     },
                     "durationMs": {
                         "type": "integer"
                     },
                     "correlationId": {
                         "type": "string"
                     },
                     "Level": {
                         "type": "integer"
                     },
                     "properties": {
                         "type": "object",
                         "properties": {
                             "id": {
                                 "type": "string"
                             },
                             "category": {
                                 "type": "string"
                             },
                             "correlationId": {
                                 "type": "string"
                             },
                             "result": {
                                 "type": "string"
                             },
                             "resultReason": {
                                 "type": "string"
                             },
                             "activityDisplayName": {
                                 "type": "string"
                             },
                             "activityDateTime": {
                                 "type": "string"
                             },
                             "loggedByService": {
                                 "type": "string"
                             },
                             "operationType": {
                                 "type": "string"
                             },
                             "userAgent": {},
                             "initiatedBy": {
                                 "type": "object",
                                 "properties": {
                                     "user": {
                                         "type": "object",
                                         "properties": {
                                             "id": {
                                                 "type": "string"
                                             },
                                             "displayName": {},
                                             "userPrincipalName": {
                                                 "type": "string"
                                             },
                                             "ipAddress": {
                                                 "type": "string"
                                             },
                                             "roles": {
                                                 "type": "array"
                                             }
                                         }
                                     }
                                 }
                             },
                             "targetResources": {
                                 "type": "array",
                                 "items": {
                                     "type": "object",
                                     "properties": {
                                         "id": {
                                             "type": "string"
                                         },
                                         "displayName": {},
                                         "type": {
                                             "type": "string"
                                         },
                                         "userPrincipalName": {
                                             "type": "string"
                                         },
                                         "modifiedProperties": {
                                             "type": "array"
                                         },
                                         "administrativeUnits": {
                                             "type": "array"
                                         }
                                     },
                                     "required": [
                                         "id",
                                         "displayName",
                                         "type",
                                         "userPrincipalName",
                                         "modifiedProperties",
                                         "administrativeUnits"
                                     ]
                                 }
                             },
                             "additionalDetails": {
                                 "type": "array"
                             }
                         }
                     }
                 },
                 "required": [
                     "time",
                     "resourceId",
                     "operationName",
                     "operationVersion",
                     "category",
                     "tenantId",
                     "resultSignature",
                     "durationMs",
                     "correlationId",
                     "Level",
                     "properties"
                 ]
             }
         }
     }
    }
    
    ```
7. **[Parse JSON]** アクションは、次のスクリーンショットのようになります。

    [Image: [JSON の解析] のスクリーンショット。]

### セキュリティ通知の電子メール本文

次に、アカウントに対して実行されたアクションについてユーザーに通知するセキュリティ電子メールを作成し、スタイルを設定します。 ここでは、発生したアクティビティをユーザーに通知し、自分のアクションではない場合は報告するように求めます。

1. **[Parse JSON]** の下で、**[新しいステップ]** を選択します。
2. **[操作を選択してください]** で **[組み込み]** を選択します。 検索ボックスに「*for each*」と入力し、**[アクション]** のリストから、**[For each]** を選択します。

    [Image: for each ステップのスクリーンショット。]
3. **[前の手順から出力を選択]** の下で、**[動的コンテンツの追加]** を選択します。
4. **[動的コンテンツ]** で、**[レコード]** を選択します。

    [Image: レコードのスクリーンショット。]
5. [**For each**] アクション内で、**[アクションの追加]** を選択します。

    [Image: レコードのアクションを追加する方法のスクリーンショット。]
6. **[操作を選択してください]** で **[組み込み]** を選択します。 検索ボックスに「**変数**」と入力し、**[変数の設定]** を選択します。
7. **[名前]** の下で、作成した *dateTime* 変数を選択します。
8. **[値]** 内で、**[動的コンテンツの追加]** を選択します。
9. **[動的コンテンツ]** で、[Parse JSON] の下で **[時間]** を検索して選択します。

    [Image: 時間を選ぶ方法のスクリーンショット。]
10. **[変数の設定]** の下で、**[組み込み]** を選択します。 検索ボックスに「**変数**」と入力し、**[変数の設定]** を選択します。
11. **[名前]** の下で、作成した *emailBody* 変数を選択します。
12. **[値]** の下で、セキュリティ通知電子メールの本文に表示するテキストを入力します。 本文は html で書式設定できます。 このテンプレートから始めてカスタマイズできます。 たとえば、href プレースホルダーを、組織に関連するリンクに置き換えます。

    ```html
    <div>
     <h2>
         You recently changed your authentication methods
     </h2>
     <p>
         We have been notified of the following action: (operation) on (date & time). <br><br>
         If you initiated this, no action is required. <br><br>
         If you haven't, please report it now. <br><br>
         <b>Instructions</b>
         <ol>
             <li>Review your account activity in <a href="https://mysignins.microsoft.com/security-info" class="link">Microsoft Security Info</a>.</li>
             <li>If you do not recognize this action, report it immediately:</li>
             <ul>
                 <li>Go to <a href="#" class="link">ReportItNow</a> and select your security event.</li>
                 <li>Provide any additional information in the form and submit.</li>
             </ul>
         </ol>
         <b>Information and Support</b>
         <ul>
             <li>Technical Assistance - Contact <a href="#" class="link">Helpdesk</a> support services</li>
         </ul>
         <b>Do NOT reply to this email. This is an unmonitored mailbox.</b><br>
         For more information, contact the <a href="#" class="link">Security Department</a>
         <br><br>
         <a href="#"><button type="button">Report device</button></a><br><br>
         <div class="footer">
             Contoso, Ltd., 4567 Main St Buffalo, NY 98052<br>
             <br>Facilitated by <br>
             <img src="#" alt="Company Logo" style="height:70px;">
         </div>
         <style>
             .link {
                 text-decoration:none;
                 color: #0078D4
             }
             button {
                 background-color: #0078D4;
                 color: white;
                 padding: 10px;
                 border-radius: 5px;
                 text-decoration: none;
    
             }
             button:hover {
                 cursor: pointer;
             }
             .footer {
                 width: 100%;
                 height: 10%;
                 padding-top: 10px;
                 padding-left: 10px;
                 padding-right: 10px;
                 background-color: rgb(237, 237, 237);
             }
         </style>
     </p>
    </div>
    
    ```

### 動的コンテンツを電子メール本文に追加する

1. 上記のテンプレートを使用している場合は、[変数の設定] アクションの [値] フィールドにコピーして貼り付けます。
2. テンプレートを貼り付けた値フィールド内で、最初の数行のテキストに戻り、"(コンテンツ)" を強調表示します。 下の画像を参照してください。
3. そのテキストが強調表示されると、 アクション ボックスの右側に **[動的コンテンツ]** がポップアップ表示されます。 [動的コンテンツ] の検索バーで、operationName を検索して選択します。

    [Image: operationName を選ぶ方法のスクリーンショット。]
4. ここでも、テンプレートを貼り付けた値フィールド内で、最初の数行のテキストに戻り、"(date & time)" を強調表示します。 下の画像を参照してください。
5. そのテキストが強調表示されると、アクション ボックスの右側に [動的コンテンツ] セクションがポップアップ表示されます。 [式] タブに移動し、入力ボックスに次のコードを入力します。

    ```code
    formatDateTime(variables('dateTime'),'yyyy-MM-dd tH:mm:ss')
    ```
6. 入力ボックスに上記のコードを貼り付けた後、**[OK]** を選択します。

    [Image: 変数を設定する方法のスクリーンショット。]

動的コンテンツを使用して電子メールをさらにカスタマイズする方法の詳細については、「[ワークフローの動的コンテンツ](https://learn.microsoft.com/ja-jp/purview/how-to-use-workflow-dynamic-content)」を参照してください。

### セキュリティ電子メールの送信

1. **[変数の設定]** アクションの下で、**[アクションの追加]** を選択します。
2. **[操作を選択してください]** で **[組み込み]** を選択します。 検索ボックスに「**for each**」と入力し、アクションリストから **[For each]** という名前のアクションを選択します。

    [Image: For each アクションを選ぶ方法のスクリーンショット。]
3. **[前の手順から出力を選択]** で、**[動的コンテンツ]** から **targetResources** を選択します。

    [Image: ターゲット リソースを選ぶ方法のスクリーンショット。]
4. **[For each 2]** アクション ブロック内と **targetResources** の下で、**[アクションの追加]** を選択します。
5. **[操作を選択してください]** で **[組み込み]** を選択します。 検索ボックスに「*条件*」と入力し、**[条件]** という名前のアクションを選択します。

    [Image: 条件を選ぶ方法のスクリーンショット。]
6. **[値の選択]** 内で **operationName** を検索して選択します。

    [Image: 操作名を選ぶ方法のスクリーンショット。]
7. **[値の選択]** に、セキュリティ通知電子メールを送信するアクティビティの正確な名前を入力します。 フィルター処理して通知を送信できるアクティビティの完全なリストについては、「[監査ログ アクティビティ](https://learn.microsoft.com/ja-jp/purview/audit-log-activities)」を参照してください。
8. このチュートリアルでは、**[ユーザー パスワードのリセット]** アクティビティに関する電子メールを送信します。

    [Image: ユーザー パスワードのリセットを選ぶ方法のスクリーンショット。]
9. 複数のアクティビティのセキュリティ電子メールを送信する場合は、**[条件アクション]** ブロック内で **[追加]** を選択し、**[行の追加]** を選択し、**[値の選択]** で別のアクティビティ名に対してこれらの手順を繰り返します。

### 電子メール通知設定

1. **[条件]** の下に、**[True]** と **[False]** のアクションがあります。 **[True]** アクション ボックス内で、 **[アクションの追加]** を選択します。

    [Image: True アクションを追加する方法のスクリーンショット。]
2. **[操作を選択してください]** で **[組み込み]** を選択します。 検索ボックスに「**電子メール**」と入力し、**[Office 365 Outlook]** を選択します。 Outlook 電子メールの代わりに、さまざまなサービスで通知を送信できます。 別のサービスを見つけるには、**[操作の選択]** の検索バーに移動し、目的のサービスを検索します。

    [Image: Outlook を選ぶ方法のスクリーンショット。]
3. **[アクション]** の下で、下スクロールし、 **[電子メールの送信 (V2)]** を選択します。

    [Image: [メールの送信] を選ぶ方法のスクリーンショット。]
4. **[To]** フィールド内で、**[動的コンテンツ]** で **userPrincipalName** を検索し、2 番目のオプションを選択します。

    [Image: userPrincipalName を選ぶ方法のスクリーンショット。]
5. **[件名]** フィールドで、**[動的コンテンツ]** で **operationName** を検索して選択します。

    [Image: [動的コンテンツ] で操作名を選ぶ方法のスクリーンショット。]
6. **[本文]** フィールドで、**[動的コンテンツ]** で emailBody を検索して選択します。

    [Image: [動的コンテンツ] でメール本文を選ぶ方法のスクリーンショット。]
7. **[重要度]** を選択すると、電子メールの重要度を変更できます。

### ワークフローを実行する

ワークフローを手動で開始するには、デザイナーのツール バーで、**[トリガーの実行]**&gt;**[実行]** を選択します。 監査ログがイベント ハブにストリーミングされると、ロジック アプリがセキュリティ通知を送信するようにトリガーされます。

このワークフローをカスタマイズして、他のログやアクティビティをフィルター処理したり、Teams などのさまざまなサービスを介して通知を送信したりして、ユーザーに疑わしいアクティビティを認識させる最適なエクスペリエンスを作成できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/tutorial-enable-sspr"} -->
## Microsoft Entra のセルフサービス パスワード リセットを有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: このチュートリアルでは、ユーザーのグループに対して Microsoft Entra のセルフサービス パスワード リセットを有効にし、パスワード リセット プロセスをテストする方法について説明します。

Microsoft Entra セルフサービス パスワード リセット (SSPR) により、ユーザーは、管理者やヘルプ デスクが関与することなく、自分のパスワードを変更またはリセットできるようになります。 Microsoft Entra ID によって自分のアカウントがロックされた場合やパスワードを忘れた場合でも、ユーザーは画面の指示に従って自分自身のブロックを解除して、作業に戻ることができます。 この機能により、ユーザーが自分のデバイスやアプリケーションにサインインできなくなった場合のヘルプ デスクの問い合わせが減り、生産性の喪失も軽減されます。 [Microsoft Entra ID で SSPR を有効にして構成する方法については、](https://www.youtube.com/watch?v=rA8TvhNcCvQ)このビデオをお勧めします。 また、 [SSPR で最も一般的な 6 つのエンド ユーザー エラー メッセージを解決するための](https://www.youtube.com/watch?v=9RPrNVLzT8I)ビデオも IT 管理者向けに用意されています。

重要

このチュートリアルでは、管理者向けにセルフサービス パスワード リセットを有効にする方法を示します。 セルフサービス パスワード リセットに既に登録されているエンド ユーザーで、アカウントに戻る必要がある場合は、 [Microsoft Online のパスワード リセット](https://passwordreset.microsoftonline.com/) ページに移動します。

ユーザーが自分でパスワードをリセットする機能が IT チームによって有効にされていない場合は、ヘルプデスクに連絡して追加のサポートを依頼してください。

このチュートリアルで学習する内容は次のとおりです。

- Microsoft Entra ユーザーのグループに対してセルフサービス パスワード リセットを有効にする
- 認証方法と登録オプションを設定する
- ユーザーとして SSPR プロセスをテストする

重要

2023 年 3 月に、従来の多要素認証とセルフサービス パスワード リセット (SSPR) ポリシーでの認証方法の管理の廃止を発表しました。 2025 年 9 月 30 日以降、これらのレガシ MFA および SSPR ポリシーでは認証方法を管理できません。 お客様には、手動移行制御を使用して、非推奨となる日までに認証方法ポリシーに移行することをお勧めします。

### ビデオ チュートリアル

関連ビデオ「 [Microsoft Entra ID で SSPR を有効にして構成する方法](https://www.youtube.com/embed/rA8TvhNcCvQ?azure-portal=true)」も参照してください。

### 前提条件

このチュートリアルを完了するには、以下のリソースと特権が必要です。

- パスワードのリセットには、少なくとも Microsoft Entra ID P1 ライセンスがある稼働中の Microsoft Entra テナントが必要です。 Microsoft Entra ID でのパスワード変更とパスワード リセットのライセンス要件の詳細については、「 [Microsoft Entra のセルフサービス パスワード リセットのライセンス要件](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-licensing)」を参照してください。
- 少なくとも認証ポリシー管理者ロールを持つアカウント。
- 管理者以外のユーザーで、たとえば*testuser*のように、あなたが知っているパスワードを持つユーザー。 このチュートリアルでは、このアカウントを使用してエンドユーザーの SSPR エクスペリエンスをテストします。
    - ユーザーを作成する必要がある場合は、「 [クイック スタート: Microsoft Entra ID に新しいユーザーを追加する」を](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users)参照してください。
- 管理者以外のユーザーがメンバーであるグループ ( *SSPR-Test-Group*など)。 このチュートリアルでは、このグループに対して SSPR を有効にします。
    - グループを作成する必要がある場合は、「 [基本的なグループを作成し、Microsoft Entra ID を使用してメンバーを追加](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups)する」を参照してください。

### セルフサービス パスワード リセットを有効にする

Microsoft Entra ID を使用すると、 *なし*、 *選択*済み、または *すべての* ユーザーに対して SSPR を有効にすることができます。 この詳細な機能により、SSPR の登録プロセスとワークフローのテスト対象となるユーザーのサブセットを選択できます。 このプロセスに慣れ、より広範なユーザーに要求を伝えることができるようになったら、ユーザー グループを選択して、SSPR を有効にすることができます。 または、Microsoft Entra テナント内のすべてのユーザーに対して SSPR を有効にすることもできます。

注

現在、Microsoft Entra 管理センターを使用して SSPR に対して有効にできる Microsoft Entra グループは、1 つだけです。 SSPR のより広範なデプロイの一環として、Microsoft Entra ID では、入れ子になったグループがサポートされます。

このチュートリアルでは、テスト グループ内の一連のユーザーに対して SSPR を設定します。 *SSPR-Test-Group* を使用し、必要に応じて独自の Microsoft Entra グループを提供します。

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **Entra ID**&gt;**パスワード リセット**を開いてください。
3. **[プロパティ**] ページの [*セルフサービス パスワード リセットが有効]* オプションの下にある [**選択済み**] を選択します。
4. グループが表示されない場合は、[ **グループが選択されていません**] を選択し、 *SSPR-Test-Group* などの Microsoft Entra グループを参照して選択し、[選択] を *選択します*。

    [Image: セルフサービス パスワード リセットを有効にするグループを選択する]
5. 選択したユーザーに対して SSPR を有効にするには、[ **保存]** を選択します。

### 認証方法と登録オプションを選択する

ユーザーが自分のアカウントのロックを解除したり、パスワードをリセットしたりする必要がある場合は、別の確認方法を求められます。 この追加の認証要素によって、承認された SSPR イベントのみが Microsoft Entra ID により処理されるようになります。 ユーザーが指定する登録情報に基づいて、どの認証方法を許可するかを選択できます。

1. **[認証方法**] ページの左側にあるメニューから、[**リセットに必要なメソッドの数] を***2* に設定します。

    セキュリティを強化するために、SSPR に必要な認証方法の数を増やすことができます。
2. 組織が許可する **[ユーザーが使用できる方法]** を選択します。 このチュートリアルでは、次のメソッドを有効にします。

    - *モバイル アプリの通知*
    - *モバイル アプリ コード*
    - *電子メール*
    - *携帯電話*
3. 認証方法を適用するには、[ **保存]** を選択します。

ユーザーが自分のアカウントのロックを解除したり、パスワードをリセットしたりするには、自分の連絡先情報を登録しておく必要があります。 Microsoft Entra ID では、この連絡先情報が、前の手順で設定したさまざまな認証方法に使用されます。

この連絡先情報は、管理者が手動で入力することも、ユーザーが登録ポータルに移動して自分で入力することもできます。 このチュートリアルでは、ユーザーが次回サインインしたときに登録を求められるように Microsoft Entra ID を設定します。

1. **[登録**] ページの左側にあるメニューから、[*サインイン時にユーザーの登録を必須にする*] で [**はい**] を選択します。
2. **ユーザーが認証情報を再確認するように求められるまでの日数**を *180* に設定します。

    連絡先情報が最新の状態に保たれていることが重要です。 SSPR イベントの開始時に古い連絡先情報が存在する場合、ユーザーは自分のアカウントのロックを解除できないか、パスワードをリセットできない可能性があります。
3. 登録設定を適用するには、[ **保存]** を選択します。

注

サインイン中のセキュリティ情報の登録の中断は、設定で構成されている条件が満たされた場合にのみ発生します。 これは、Microsoft Entra セルフサービス パスワード リセットを使用してパスワードをリセットできるユーザーと管理者アカウントにのみ適用されます。 登録割り込みは、対話型セッションごとに 1 回だけユーザーに表示されます。

### 通知とカスタマイズを設定する

アカウントのアクティビティについて常にユーザーに通知されるように、SSPR イベントが発生したときに電子メール通知を送信するように Microsoft Entra ID を設定できます。 これらの通知は、通常のユーザー アカウントと管理者アカウントの両方を対象とすることができます。 管理者アカウントの場合は、この通知により、SSPR を使用して特権管理者アカウントのパスワードがリセットされたタイミングをより明確に認識できます。 管理者アカウントで SSPR を使用した場合、Microsoft Entra ID によってすべてのグローバル管理者に通知されます。

1. **[通知**] ページの左側にあるメニューから、次のオプションを設定します。

    - [ **パスワード リセット時にユーザーに通知する] オプションを***[はい*] に設定します。
    - **[他の管理者がパスワードをリセットしたときにすべての管理者に通知する]** を *[はい*] に設定します。
2. 通知設定を適用するには、[ **保存]** を選択します。

ユーザーが SSPR プロセスについてさらにヘルプを必要とする場合は、[ **管理者に問い合わせる] リンクを** カスタマイズできます。 このリンクは、ユーザーが SSPR 登録プロセスで選択できるほか、自分のアカウントのロックを解除したりパスワードをリセットしたりするときに選択できます。 ユーザーが必要なサポートを受けられるようにするには、カスタム ヘルプデスクのメール アドレスまたは URL を指定することをお勧めします。

1. **[カスタマイズ**] ページの左側にあるメニューから、[**ヘルプデスクのカスタマイズ] リンク**を *[はい*] に設定します。
2. **[カスタム ヘルプデスクの電子メールまたは URL**] フィールドで、ユーザーが組織からさらに支援を受けることができるメール アドレスまたは Web ページの URL を指定します (例:*https://support.contoso.com/*
3. カスタム リンクを適用するには、[ **保存]** を選択します。

### セルフサービス パスワード リセット をテストする

SSPR を有効にして設定したら、前のセクションで選択したグループの一部であるユーザー ( *Test-SSPR-Group など) で SSPR プロセスをテスト*します。 次の例では、 *testuser* アカウントを使用します。 独自のユーザー アカウントを指定してください。このチュートリアルの最初のセクションで SSPR を有効にしたグループに属しているものです。 このチュートリアルの最初のセクションで SSPR を有効にしたグループに属しているものです。

注

セルフサービス パスワード リセットをテストする場合は、管理者以外のアカウントを使用します。 既定では、Microsoft Entra ID でセルフサービス パスワード リセットが管理者に対して有効になっています。 自分のパスワードをリセットするには 2 つの認証方法を使用する必要があります。 詳細については、「 [管理者リセット ポリシーの相違点](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-policy#administrator-reset-policy-differences)」を参照してください。

1. 手動の登録プロセスを確認するには、InPrivate またはシークレット モードで新しいブラウザー ウィンドウを開き、*https://aka.ms/ssprsetup* を参照します。 Microsoft Entra ID は、次回サインインするときにユーザーをこの登録ポータルに誘導します。
2. 管理者以外のテスト ユーザー ( *testuser* など) でサインインし、認証方法の連絡先情報を登録します。
3. 完了したら、[ **外観が良い** ] とマークされたボタンを選択し、ブラウザー ウィンドウを閉じます。
4. InPrivate または incognito モードで新しいブラウザー ウィンドウを開き、*https://aka.ms/sspr* を参照します。
5. 管理者以外のテスト ユーザーのアカウント情報 ( *testuser*、CAPTCHA の文字など) を入力し、[ **次へ**] を選択します。

    [Image: ユーザー アカウント情報を入力してパスワードをリセットする]
6. 検証の手順に従って、パスワードをリセットします。 完了すると、パスワードがリセットされたことを示す電子メール通知が届きます。

### リソースをクリーンアップする

このシリーズの後のチュートリアルでは、パスワード ライトバックを設定します。 この機能により、パスワードの変更が Microsoft Entra SSPR からオンプレミス AD 環境に書き戻されます。 このチュートリアル シリーズを続行してパスワード ライトバックを設定する場合は、ここでは SSPR を無効にしないでください。

このチュートリアルの一部として設定した SSPR 機能を使用しなくなった場合は、次の手順を使用して、SSPR の状態を [なし]  に設定します。

1. 少なくとも[認証ポリシー管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator)にサインインします。
2. **Entra ID**&gt;**パスワード リセット**を開いてください。
3. [ **プロパティ** ] ページの [ *セルフ サービス によるパスワード リセットが有効]* オプションの下にある [なし] を選択 **します**。
4. SSPR の変更を適用するには、[ **保存]** を選択します。

### よく寄せられる質問

このセクションでは、SSPR を試す管理者とエンドユーザーからの一般的な質問について説明します。

- SSPR 中にオンプレミスのパスワード ポリシーが表示されないのはなぜですか。

    現時点で、Microsoft Entra Connect とクラウド同期では、クラウドとのパスワード ポリシーの詳細の共有はサポートされていません。 SSPR はクラウド パスワード ポリシーの詳細のみを表示し、オンプレミス ポリシーは表示できません。
- オンプレミスから同期されたパスワードを使用できるようになるまでに、フェデレーション ユーザー **がパスワードがリセットされたことを** 確認してから最大 2 分待つのはなぜですか?

    パスワードが同期されているフェデレーション ユーザーの場合、パスワードの権限のソースはオンプレミスです。 その結果、SSPR は、オンプレミスのパスワードのみを更新します。 Microsoft Entra ID へのパスワード ハッシュの同期は、2 分ごとにスケジュールされています。
- 電話やメールなどの SSPR データが事前に設定されている新しく作成されたユーザーが SSPR 登録ページにアクセスすると、**アカウントへのアクセスを失わないでください！**がページのタイトルに表示されます。 SSPR データが事前に設定されている他のユーザーにこのメッセージが表示されないのはなぜですか。

    [ **アカウントへのアクセスを失わない** ] と表示されるユーザーは、テナント用に構成された SSPR/結合された登録グループのメンバーです。 **アカウントへのアクセスを失わないでください！**が表示されないユーザーは、SSPR/統合登録グループに含まれませんでした。
- SSPR プロセスを実行してパスワードをリセットするときに、ユーザーによってパスワード強度インジケーターが表示されない場合があるのはなぜですか。

    脆弱または堅牢パスワード強度が表示されないユーザーでは、同期パスワード ライトバックが有効になっています。 SSPR は、お客様のオンプレミス環境のパスワード ポリシーを決定できないため、パスワードの強度や弱点を検証できません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/tutorial-enable-sspr-writeback"} -->
## Microsoft Entra パスワード ライトバックを有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr-writeback
- Service: entra-id / authentication
- Article date: 2026-05-27
- Summary: このチュートリアルでは、Microsoft Entra Connect を使用して Microsoft Entra セルフサービス パスワード リセットのライトバックを有効にし、変更をオンプレミスの Active Directory Domain Services 環境に同期する方法について説明します。

Microsoft Entraセルフサービス パスワード リセット (SSPR) を使用すると、ユーザーは Web ブラウザーを使用してパスワードを更新したり、アカウントのロックを解除したりできます。 [Entra ID で SSPR を有効にして構成する方法](https://www.youtube.com/watch?v=rA8TvhNcCvQ)に関するこの動画をお勧めします。 Microsoft Entra IDがオンプレミス Active Directory Domain Services (AD DS) 環境に接続されているハイブリッド環境では、2 つのディレクトリ間でパスワードが異なる場合があります。

パスワード ライトバックを使用して、Microsoft Entraのパスワード変更をオンプレミスの AD DS 環境に同期できます。 Microsoft Entra Connect には、これらのパスワード変更を Microsoft Entra ID から既存のオンプレミス ディレクトリに送信するための安全なメカニズムが用意されています。

重要

このチュートリアルでは、管理者がセルフサービスパスワードリセットを有効にしてオンプレミス環境に戻す方法について説明します。 既にセルフサービス パスワード リセットの登録が済んでいて、自分のアカウントに戻る必要があるエンド ユーザーは、 https://aka.ms/sspr にアクセスしてください。

ユーザーが自分でパスワードをリセットする機能が IT チームによって有効にされていない場合は、ヘルプデスクに連絡して追加のサポートを依頼してください。

このチュートリアルでは、以下の内容を学習します。

- パスワード ライトバックに必要なアクセス許可を構成する
- Microsoft Entra Connect でパスワード ライトバック オプションを有効にする
- Microsoft Entra SSPR でパスワード ライトバックを有効にする

### 前提条件

このチュートリアルを完了するには、以下のリソースと特権が必要です。

- Microsoft Entra ID P1 以上か試用版のライセンスが有効になっている稼働中の Microsoft Entra テナント。
    - 必要に応じて、 [無料で作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
    - 詳細については、「 [Microsoft Entra SSPR のライセンス要件](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-licensing)」を参照してください。
- [ハイブリッド ID 管理者を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)持つアカウント。
- セルフサービス パスワード リセット用に構成された Microsoft Entra ID。
    - 必要に応じて、 [前のチュートリアルを完了して Microsoft Entra SSPR を有効にします](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr)。
- Microsoft Entra Connect の現在のバージョンを使って構成された既存のオンプレミスの AD DS 環境。
    - 必要に応じて、 [Express](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-express) またはカスタム設定を使用して Microsoft Entra Connect [を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom) 構成します。
    - パスワード ライトバックを使用するために、ドメイン コントローラーはサポートされている任意のバージョンの Windows Server を実行できます。

### Microsoft Entra Connect のアカウントのアクセス許可を構成する

Microsoft Entra Connect を使用すると、オンプレミスの AD DS 環境と Microsoft Entra ID の間でユーザー、グループ、資格情報を同期できます。 通常は、オンプレミスの AD DS ドメインに参加している Windows Server 2016 以降のコンピューターに Microsoft Entra をインストールします。

SSPR のライトバックを正しく操作するには、Microsoft Entra Connect で指定されたアカウントに適切なアクセス許可とオプションが設定されている必要があります。 現在使用中のアカウントがわからない場合は、Microsoft Entra Connect を開き、[ **現在の構成の表示** ] オプションを選択します。 アクセス許可を追加する必要があるアカウントは、[ **同期されたディレクトリ**] の下に一覧表示されます。 このアカウントには、次のアクセス許可とオプションを設定する必要があります。

- **パスワードのリセット**
- **パスワードの変更**
- での`lockoutTime`
- での`pwdLastSet`
- まだ設定されていない場合、そのフォレスト内の**各ドメイン**のルート オブジェクトに対する **Unexpire Password** の*拡張権限*。

これらのアクセス許可を割り当てない場合、ライトバックは正しく構成されているように見えますが、ユーザーがクラウドからオンプレミスのパスワードを管理するときにエラーが発生します。 Active Directoryで **Unexpire Password** アクセス許可を設定する場合は、それを **This オブジェクトとすべての子孫オブジェクトに適用する必要があります** **このオブジェクトのみ**、または **すべての子孫オブジェクト**; それ以外の場合は、**Unexpire Password** アクセス許可は表示されません。

ヒント

一部のユーザー アカウントのパスワードがオンプレミスのディレクトリに書き戻されない場合は、オンプレミスの AD DS 環境でそのアカウントの継承が無効になっていないことを確認してください。 この機能を正常に動作させるには、パスワードの書き込みアクセス許可を子孫オブジェクトに適用する必要があります。

パスワード ライトバックを行うための適切なアクセス許可を設定するには、以下の手順を完了します。

1. オンプレミスの AD DS 環境で、適切なドメイン管理者アクセス許可を持つアカウントで **Active Directory ユーザーとコンピューター** を開きます。
2. [ **表示** ] メニューで、[ **高度な機能** ] がオンになっていることを確認します。
3. 左側のパネルで、ドメインのルートを表すオブジェクトを右クリックし、 **プロパティ**&gt;**Security**&gt;**Advanced** を選択します。
4. [ **アクセス許可** ] タブで、[ **追加**] を選択します。
5. **Principal** で、アクセス許可を適用するアカウント (Microsoft Entra Connect が使用するアカウント) を選択します。
6. [ **適用対象** ] ドロップダウン リストで、[ **子孫ユーザー オブジェクト**] を選択します。
7. [ **アクセス許可**] で、[ **パスワードのリセット**] ボックスを選択します。
8. [ **プロパティ]** で、次のオプションのボックスを選択します。 一覧をスクロールして、既定で既に設定されている可能性がある次のオプションを見つけます。

- **lockoutTimeを書き込み**
- **pwdLastSet の書き込み**

    [Image: Microsoft Entra Connect が使用するアカウントの Active Users と Computers で適切なアクセス許可を設定します。]

1. 準備ができたら、[ **適用] または [OK] を** 選択して変更を適用します。
2. [ **アクセス許可** ] タブで、[ **追加**] を選択します。
3. **Principal** で、アクセス許可を適用するアカウント (Microsoft Entra Connect で使用するアカウント) を選択します。
4. [**適用対象**] ドロップダウン リストで、[**このオブジェクトとすべての子孫オブジェクト**] を選択します。
5. [ **アクセス許可**] で、次のオプションのボックスを選択します。
    - **無期限パスワード**
6. 準備ができたら、[ **適用] または [OK] を** 選択して変更を適用し、開いているダイアログ ボックスをすべて終了します。

アクセス許可を更新すると、ディレクトリ内のすべてのオブジェクトにこれらのアクセス許可がレプリケートされるまで最大で 1 時間以上かかる場合があります。

オンプレミスの AD DS 環境のパスワード ポリシーによって、パスワードのリセットが正しく処理されない場合があります。 パスワード ライトバックを最も効率的に機能させるには、パスワードの **最小有効期間** のグループ ポリシーを **0** に設定する必要があります。 この設定は、**[コンピューターの構成] &gt; [ポリシー] &gt; [Windows の設定] &gt; [セキュリティの設定] &gt; [アカウント ポリシー]** 配下の `gpmc.msc` にあります。

グループ ポリシーを更新する場合は、更新されたポリシーがレプリケートされるまで待機するか、 `gpupdate /force` コマンドを使用します。

Note

ユーザーが 1 日に 1 回以上パスワードを変更またはリセットできるようにする必要がある場合は、 **パスワードの最小有効期間** を **0** に設定する必要があります。 パスワードライトバックは、オンプレミスのパスワードポリシーが正常に評価された後に動作します。

### Microsoft Entra Connect でパスワード ライトバックを有効にする

Microsoft Entra Connect の構成オプションの 1 つはパスワード ライトバック用です。 このオプションが有効になっていると、パスワード変更イベントにより、Microsoft Entra Connect は更新された資格情報をオンプレミスの AD DS 環境に同期します。

SSPR のライトバックを有効にするには、まず、Microsoft Entra Connect でライトバック オプションを有効にします。 Microsoft Entra Connect サーバーから、次の手順を実行します。

1. Microsoft Entra Connect サーバーにサインインし、 **Microsoft Entra Connect** 構成ウィザードを開始します。
2. [ **ようこそ** ] ページで、[ **構成**] を選択します。
3. [ **その他のタスク** ] ページで、[ **同期オプションのカスタマイズ**] を選択し、[ **次へ**] を選択します。
4. [Microsoft Entra ID] ページで、Azure テナントのハイブリッド管理者の資格情報を入力し、Next を選択します。
5. [ **ディレクトリの接続** ] ページと **[ドメイン/OU** フィルター] ページで、[ **次へ**] を選択します。
6. [ **オプション機能** ] ページで、[ **パスワード ライトバック** ] の横にあるボックスを選択し、[ **次へ**] を選択します。
7. [ **ディレクトリ拡張機能** ] ページで、[ **次へ**] を選択します。
8. [ **構成の準備完了** ] ページで、[ **構成** ] を選択し、プロセスが完了するまで待ちます。
9. 構成が完了したら、[終了] を選択 **します**。

Note

`PasswordWritebackEnabled`からのの更新は、この機能フラグが使用されていないためサポートされていません。

### SSPR のパスワード ライトバックを有効にする

Microsoft Entra Connect でパスワード ライトバックが有効になっているので、書き戻し用Microsoft Entra SSPR を構成できるようになりました。 Microsoft Entra Connect 同期エージェントと Microsoft Entra Connect プロビジョニング エージェント (クラウド同期) を使用して書き戻しするように SSPR を構成できます。 SSPR でパスワード ライトバックを使用できるようにすると、自分のパスワードを変更またはリセットするユーザーは、その更新したパスワードがオンプレミスの AD DS 環境にも同期されるようになります。

SSPR でパスワード ライトバックを有効にするには、次の手順を実行します。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;**Password reset** に移動し、[**オンプレミス統合**] を選択します。
3. **[パスワードをオンプレミス のディレクトリに書き戻す**] オプションをオンにします。
4. (省略可能)Microsoft Entra Connect プロビジョニング エージェントが検出された場合は、 **Microsoft Entra Connect クラウド同期を使用してパスワードを書き戻す**オプションも確認できます。
5. [ **ユーザーがパスワードをリセットせずにアカウントのロックを解除できるようにする] オプションを** **[はい**] に設定します。
6. 準備ができたら、[ **保存]** を選択します。

### リソースをクリーンアップする

このチュートリアルの一環として構成した SSPR のライトバック機能をもう使用しない場合は、次の手順を実行します。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;**Password reset** に移動し、[**オンプレミス統合**] を選択します。
3. **[パスワードをオンプレミス のディレクトリに書き戻す**] オプションをオフにします。
4. **Microsoft Entra Connect クラウド同期を使用してパスワードを書き戻す**オプションをオフにします。
5. [ **ユーザーがパスワードをリセットせずにアカウントのロックを解除できるようにする] オプションを**オフにします。
6. 準備ができたら、[ **保存]** を選択します。

SSPR ライトバック機能に Microsoft Entra Connect クラウド同期を使用しなくなった場合に、ライトバックに Microsoft Entra Connect 同期エージェントを引き続き使用するには、次の手順を実行します。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;**Password reset** に移動し、[**オンプレミス統合**] を選択します。
3. **Microsoft Entra Connect クラウド同期を使用してパスワードを書き戻す**オプションをオフにします。
4. 準備ができたら、[ **保存]** を選択します。

パスワード機能を使用する必要がなくなった場合は、Microsoft Entra Connect サーバーから次の手順を実行します。

1. Microsoft Entra Connect サーバーにサインインし、 **Microsoft Entra Connect** 構成ウィザードを開始します。
2. [ **ようこそ** ] ページで、[ **構成**] を選択します。
3. [ **その他のタスク** ] ページで、[ **同期オプションのカスタマイズ**] を選択し、[ **次へ**] を選択します。
4. [**Connect to Microsoft Entra ID**] ページで、ハイブリッド管理者の資格情報を入力し、[**Next** を選択します。
5. [ **ディレクトリの接続** ] ページと **[ドメイン/OU** フィルター] ページで、[ **次へ**] を選択します。
6. [ **オプション機能** ] ページで、[ **パスワード ライトバック** ] の横にあるボックスの選択を解除し、[ **次へ**] を選択します。
7. [ **構成の準備完了** ] ページで、[ **構成** ] を選択し、プロセスが完了するまで待ちます。
8. 構成が完了したら、[終了] を選択 **します**。

重要

パスワードの書き戻しを初めて有効にすると、パスワードの変更が発生していない場合でも、パスワード変更イベント 656 と 657 がトリガーされる可能性があります。 これは、パスワード ハッシュ同期サイクルの実行後にすべてのパスワード ハッシュが再同期されるためです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/tutorial-password-policy-overview-frequently-asked-questions"} -->
## パスワード ポリシーの概要とよく寄せられる質問 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-password-policy-overview-frequently-asked-questions
- Service: entra-id / authentication
- Article date: 2026-04-15
- Summary: このドキュメントでは、パスワード ポリシーについて明確かつわかりやすい方法で説明します。

このドキュメントでは、パスワード ポリシーとパスワードの有効期限に関してよく寄せられる質問に対する回答を示します。これらのポリシーは、Microsoft Entra IDのさまざまなユーザーの種類にどのように適用されるかに焦点を当てています。

### パスワード ポリシーのクイック リファレンス (ユーザーの種類別)

[Image: パスワード ポリシーのクイック リファレンスのスクリーンショット。]

### パスワードの変更またはリセット中に評価されたポリシー (パスワードの長さと複雑さ)

#### 同期されたユーザー

同期されたユーザーに適用されるオンプレミス Active Directory Domain Services (AD DS) パスワード ポリシーは、**Account Policies**&gt;**Password Policy** で変更されます。 たとえば、パスワードの最小長を変更することで、要件を 6 文字以下に減らしたり、10 文字以上などのより厳密なポリシーを適用することができます。

[Image: パスワード ポリシーのスクリーンショット。]

Microsoft Entra Connect のパスワード ライトバック オプションが有効になっている場合、同期されたユーザーは、Microsoft Entra IDから直接パスワードを変更またはリセットできます。 この場合、Microsoft Entra IDは、オンプレミスの AD DS パスワード ポリシーが適用される前にパスワードを評価します。 このプロセスにより、ユーザーは推測しやすいパスワードを簡単に設定できなくなります。 環境によっては、同期されたユーザーにも、グローバル禁止パスワード リストなどのMicrosoft Entra ID制限が適用される場合があります。 詳細については、 FAQ セクションを参照してください。

#### クラウド専用ユーザー

クラウド ユーザーの場合、パスワードの有効期限を除き、Microsoft Entra IDパスワード ポリシーをカスタマイズすることはできません。 Entra ID パスワード ポリシーの詳細については、「[Microsoft Entra パスワード ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-policy?tabs=ms-powershell#microsoft-entra-password-policies)」を参照してください。 Microsoft Entra IDでは、オンプレミスの AD DS と同じ詳細なパスワードの複雑さの設定は提供されませんが、グローバル禁止パスワード リストとカスタム禁止パスワード リストが含まれます。 グローバル禁止パスワード リストは、すべてのテナントで有効になっており、無効にすることはできません。 管理者や野球などの脆弱なパスワードがブロックされます。 カスタム禁止パスワード リストを使用すると、組織は会社名や省略形などの単語を登録し、パスワードで使用されないようにすることができます。 詳細については、 [グローバル禁止パスワードの一覧を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad#global-banned-password-list)。

#### ゲスト ユーザー

ゲスト ユーザーには、最初にメンバーとして登録されたホーム テナントの設定が適用されます。 ゲスト ユーザーに適用されるパスワード ポリシーを確認する必要がある場合は、ホーム テナントの管理者に問い合わせてください。

### 認証中に評価されたポリシー (パスワードの有効期限)

パスワードの有効期限は、1 つのパスワードを使用できる最大日数を指定します。 有効期限に達すると、ユーザーは次回サインインするときにパスワードを変更する必要があります。 パスワードの有効期限を検討するときは、ユーザーが同期されたユーザーかクラウド ユーザーかだけでなく、認証がオンプレミスで行われるかクラウドで行われるかを調べるのが役立ちます。 各シナリオについては、次のセクションで説明します。 確認するユーザーの種類と環境に一致するセクションを参照してください

#### パスワード ハッシュ同期を使用して同期されたユーザー

一部の管理者は、オンプレミスの AD DS パスワードの有効期限がMicrosoft Entra IDに直接同期すると考える場合があります。 ただし、同期されたユーザーのパスワードの有効期限の値は、オンプレミスの AD DS と Microsoft Entra ID に個別に格納されます。 パスワード情報は両方の環境に存在するため、適用される有効期限ポリシーはユーザーがサインインする場所 (認証が行われる場所) によって異なります。 例えば次が挙げられます。

- ドメインに参加しているクライアント デバイスにサインインすると、オンプレミスの AD DS で認証が行われるため、オンプレミスの AD DS パスワードの有効期限が適用されます。
- Azure ポータルまたはMicrosoft 365にサインインすると、Microsoft Entra IDパスワードの有効期限ポリシーが適用されます。

オンプレミスの AD DS パスワードの有効期限が切れると、ユーザーは次のオンプレミス サインイン時にパスワードの変更を求められます。 パスワードが変更された後、Microsoft Entra IDがそれを同期します。 その後、ユーザーは新しいパスワードでサインインできます。 既定では、パスワード ハッシュ同期ユーザーの場合、Microsoft Entra IDパスワードの有効期限は無期限に設定されます。 その結果、オンプレミスの AD DS パスワードの有効期限が切れた後でも、ユーザーは期限切れのパスワードを使用してMicrosoft Entra IDにサインインできます。 ユーザーが期限切れのオンプレミスパスワードでMicrosoft Entra IDにサインインしないようにするには、`CloudPasswordPolicyForPasswordSyncedUsersEnabled` オプションを有効にして、Microsoft Entra IDがパスワードを期限切れでないパスワードとして扱わないようにします。

#### オンプレミス (パススルー認証または AD FS) を認証した同期されたユーザー

パススルー認証またはActive Directory フェデレーション サービス (AD FS) (AD FS) を使用する場合、Azure ポータルとMicrosoft 365サインインの認証は、Microsoft Entra IDではなくオンプレミスの AD DS で実行されます。 そのため、ユーザーが Azure ポータルにサインインした場合でも、オンプレミスの AD DS パスワードの有効期限ポリシーが適用されます。

パスワードの有効期限の設定を確認するには、オンプレミスの AD DS パスワード ポリシーを確認します。 グループ ポリシー管理コンソールで**既定のドメイン** ポリシーを編集し、**アカウント** ポリシー&gt;**Password ポリシー**に移動することで、オンプレミスの AD DS パスワード ポリシーを表示できます。

アカウント ポリシーは、ドメインにリンクされている 1 つのグループ ポリシー オブジェクト (GPO) でのみ定義できます。 このため、 **既定のドメイン ポリシー**を編集して、これらの設定を構成します。

[Image: パスワード ポリシーのスクリーンショット。]

#### Microsoft Entra IDで直接作成されたクラウド専用ユーザー

Microsoft Entra IDで直接作成されたクラウド ユーザーの場合、Microsoft Entra IDパスワード ポリシーで指定されたパスワードの有効期限が適用されます。 指定した日数が経過すると、ユーザーはMicrosoft Entra IDにサインインするときにパスワードの変更を求められます。 Microsoft Entra IDのパスワードの有効期限は、Microsoft 365 管理センターまたは PowerShell を使用して変更できます。

1. [\[パスワードの有効期限\] ポリシー設定](https://admin.cloud.microsoft/?#/Settings/SecurityPrivacy/:/Settings/L1/PasswordPolicy)に移動します。
2. 次の図では、パスワードの有効期限は **[有効期限なし**] に設定されています。

    [Image: 無期限に設定されたパスワードのスクリーンショット。]

    パスワードの有効期限を構成する場合は、チェック ボックスをオフにします。

    [Image: 期限切れに設定されたパスワードのスクリーンショット。]

Tip

上記の設定を使用してパスワードの有効期限が 90 日に設定されている環境でも、システム アカウントなど、特定のユーザーに対してのみパスワードの有効期限が切れないように設定するシナリオが存在する場合があります。 この場合は、その特定のアカウントに対して `DisablePasswordExpiration` コマンドを使用して`Update-MgUser`設定することで、アカウントの有効期限が切れないことを個別に構成できます。 詳細については、「 [個々のユーザーのパスワードを無期限に設定する」を](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/password-policy-recommendations)参照してください。

#### ゲスト ユーザー

ゲスト ユーザーのパスワードの有効期限は、ゲストのホーム テナント内の組織によって管理されます。 ゲストを招待したリソース テナントでは、この設定は管理されません。 ゲスト ユーザーが user@outlook.com などのMicrosoft アカウントを使用する場合は、そのサービスによって定義されたパスワード ポリシーが適用されます。

### FAQ

#### パスワード ハッシュ同期環境では、オンプレミスの AD DS パスワードの有効期限が切れた後も、ユーザーは古いパスワードでMicrosoft Entra IDにアクセスできます。 同期されたユーザーのMicrosoft Entra IDパスワードの有効期限を "無期限" から変更できますか?

Yes. この動作を構成するには、テナント レベルで CloudPasswordPolicyForPasswordSyncedUsersEnabled オプションを有効にします。 ユーザーがMicrosoft Entra IDからパスワードを変更できるようにするには、Microsoft Entra Connect でパスワード ライトバックも有効にする必要があります。 次のコマンドを実行すると、Microsoft Entra IDで同期されたユーザーのパスワードの有効期限を有効にすることができます。

$OnPremSync = Get-MgDirectoryOnPremiseSynchronization $OnPremSync.Features.CloudPasswordPolicyForPasswordSyncedUsersEnabled = $true

Update-MgDirectoryOnPremiseSynchronization `-OnPremisesDirectorySynchronizationId $OnPremSync.Id` -Features $OnPremSync.Features

Warning

同期されたユーザーのパスワードの有効期限の値は、オンプレミスの AD DS からMicrosoft Entra IDに同期されません。 この機能を有効にすると、オンプレミスのパスワード ポリシーがオンプレミスのサインイン時に適用され、Microsoft Entra ID パスワード ポリシーは、Microsoft Entra IDで認証が行われるときに適用されます。 その結果、該当するパスワードの有効期限ポリシーは、ユーザーがサインインする場所によって異なります。 異なる有効期限が構成されている場合 (たとえば、オンプレミスで 30 日、Microsoft Entra IDで 90 日間)、ユーザーがパスワードの有効期限を判断することが困難になる可能性があります。 このため、オンプレミスの AD DS とMicrosoft Entra IDの両方に同じ有効期限を構成することをお勧めします。 たとえば、両方の環境で 90 日後にパスワードの変更が必要な場合、パスワードはほぼ同じ時間に期限切れになります。 パスワードがオンプレミスまたはMicrosoft Entra IDで変更されると、パスワード ハッシュ同期とパスワード ライトバックによって変更が同期され、有効期限タイマーがリセットされます。

#### オンプレミスで "ユーザーは次回ログオン時にパスワードを変更する必要があります" が設定されている場合、Microsoft Entra IDへのサインイン時に "正しくないパスワード" エラーが表示されます。 ユーザーは代わりにMicrosoft Entra IDで自分のパスワードを変更できますか?

Yes. これは、テナント レベルで `UserForcePasswordChangeOnLogonEnabled` オプションを有効にすることで可能です。 Microsoft Entra IDからのパスワード変更を許可するには、Microsoft Entra Connect でパスワード ライトバックを有効にする必要があります。

```powershell
$OnPremSync = Get-MgDirectoryOnPremiseSynchronization
$OnPremSync.Features.UserForcePasswordChangeOnLogonEnabled = $true

Update-MgDirectoryOnPremiseSynchronization
 -OnPremisesDirectorySynchronizationId $OnPremSync.Id
 -Features $OnPremSync.Features
```

既定では、この機能は無効になっています。 有効にせずに、ユーザーは最初にオンプレミスでパスワードを変更する必要があります。そうしないと、サインインは失敗します。 このオプションを有効にすると、ユーザーはEntra IDで自分のパスワードを変更するように求められます。 詳細については、「 [同期されたユーザーにクラウド パスワード ポリシーを適用する」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization#enforcecloudpasswordpolicyforpasswordsyncedusers)参照してください。

#### Microsoft 365 管理センターに表示される既定のパスワードの有効期限は何ですか?

以前は、既定値は 90 日でした。 ただし、2021 年春以降に作成されたテナントの場合、既定値は期限切れになりません。 その結果、一部のテナントは 90 日間の有効期限を持ち、他のテナントは既定で有効期限なしで構成されます。

#### Microsoft Entra IDパスワード ポリシーは期限切れにならないように設定されました。 誰が変更したかを確認する方法はありますか?

ポリシーが最近変更された場合は、Microsoft Entra ID監査ログを確認できます。 [パスワード ポリシーの設定] アクティビティでフィルター処理し、イニシエーター (アクター) として一覧表示されているユーザーを確認します。

#### PowerShell で Get-MgDomain を実行すると、パスワードの有効期限2147483647日数が表示されます。 これはどういう意味ですか？

この値は、ドメインのパスワードの有効期限が無期限に設定されていることを示します。

#### Microsoft 365 管理センターまたは PowerShell でパスワード ポリシーを編集しましたが、有効期限の通知を受け取りません。

以前は、Microsoft 365 ポータルのベル アイコンを使用して通知が表示されていました。 ただし、この通知機能は廃止され、"パスワードの有効期限が切れようとしています" という通知は送信されなくなります。

#### パスワードの有効期限が切れている場合でも、Microsoft Entra参加済みデバイスにサインインできます。 変更が必要になるのはいつですか?

パスワードの有効期限が切れた場合でも、デバイスにサインインし、Windows デスクトップにアクセスできます。 Azure ポータル、Microsoft 365 管理センター、Exchange OnlineなどのMicrosoft Entra ID統合クラウド リソースにサインインするときに、パスワードを変更するように求められます。

#### テナント全体の設定ではなく、個々のユーザーのパスワードの有効期限を確認する方法はありますか。

Azure ポータルでこれを確認する簡単な方法はありません。 PowerShell から取得した値を使用して計算する必要があります。 テナント パスワードの有効期限ポリシーを確認するには:

1. `Connect-MgGraph` を実行します。
2. `Get-MgContext`を実行して、正しいテナントに接続していることを確認します。
3. 次のコマンドを実行します。

    ```powershell
    Get-MgDomain -DomainId contoso.onmicrosoft.com |  select Id, PasswordNotificationWindowInDays, PasswordValidityPeriodInDays
    ```

    出力例:

    ```powershell
    Id                            PasswordNotificationWindowInDays PasswordValidityPeriodInDays
    --                            -------------------------------- ----------------------------
    contoso.onmicrosoft.com                                   14                   90
    ```
4. ユーザーが最後にパスワードを変更した日付を取得するには、次のコマンドを実行します。

    ```powershell
    Get-MgUser -UserId admin@contoso.onmicrosoft.com `  -Property UserPrincipalName, LastPasswordChangeDateTime |  fl UserPrincipalName, LastPasswordChangeDateTime
    ```

    出力例:

    ```powershell
    UserPrincipalName          : admin@M365x61971868.onmicrosoft.com
    LastPasswordChangeDateTime : 2024/02/27 4:51:12
    ```
5. 最後のパスワード変更日と有効期限を比較して、有効期限を決定します。

#### "パスワードの変更" は "パスワードのリセット" と同じですか?

No. これらの用語は、さまざまなアクションを表します。

[Image: パスワードの変更とパスワードのリセットのスクリーンショット。]

パスワードの変更とは、ユーザーが現在のパスワードを既に知っている場合に、パスワードを新しいパスワードに変更するシナリオを指します。 パスワードの変更を実行する場合、ユーザーは現在の (古い) パスワードを入力する必要があります。 たとえば、次に示すプロンプトもパスワードの変更と見なされます。 さらに、パスワードの有効期限がまだ切れていない場合でも、ユーザーは [ **マイ アカウント]** ページからパスワードを明示的に変更できます。

一方、パスワードのリセットでは、ユーザーが現在のパスワードを知っている必要はありません。 ユーザーは、現在のパスワードがわかっている場合でも、パスワードをリセットできます。 ただし、パスワードのリセットは、パスワードが不明または忘れられている場合に最も一般的です。 (検出されたリスクやセキュリティ イベントへの対応としてパスワードのリセットが必要になるシナリオもあります)。ユーザーが自分でパスワードのリセットを実行すると、次の **[アカウントの回復** ] 画面が表示されます。

[Image: セルフサービス パスワード リセットのスクリーンショット。]

#### ユーザーがパスワードを変更できないようにする方法はありますか?

No. ユーザーはいつでも自分のパスワードを変更できます。 ただし、管理者はセルフサービス パスワード リセット (SSPR) を無効にできます。

#### パスワードで特定の単語が使用されないようにすることはできますか?

Yes. 会社名などの単語をカスタム禁止パスワード リストに追加できます。 管理者やパスワードなどの一般的な脆弱なパスワードは、すべてのテナントに自動的に適用されるグローバル禁止パスワード リストによって既にブロックされています。

#### グローバルおよびカスタムの禁止パスワードが評価されるタイミング

これらは、パスワードの変更およびパスワード リセット操作中に評価されます。 パスワードの評価方法に関するページで説明されている [パスワード評価](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad#how-are-passwords-evaluated)の結果によっては、カスタム禁止単語を含むパスワードが引き続き受け入れられる場合があります。

#### 同期されたユーザーがMicrosoft Entra IDからパスワードを変更すると、画面に常に "数分待ってください" と表示されます。

これは、パスワードがオンプレミスの AD パスワード ポリシーを満たしているが、Entra IDパスワード ポリシーを満たしていない場合に発生します。 たとえば、次の場合に発生する可能性があります。

- オンプレミスの最小パスワード長は 7 文字以下です
- オンプレミスでパスワードの複雑さが無効になっている

パスワードが Microsoft Entra ID ポリシーとオンプレミス ポリシーの両方を満たしている場合 (たとえば、少なくとも 8 文字、4 種類の 3 文字など)、パスワードはすぐに更新され、メッセージは表示されません。 オンプレミス ポリシーのみが満たされている場合、パスワードはオンプレミスで受け入れられ、次のパスワード ハッシュ同期サイクル (最大 2 分) の間にMicrosoft Entra IDに同期されます。 これは予期される動作であり、修復は必要ありません。

#### Microsoft Entra IDからパスワードをリセットすると、同じ "数分待ってください" というメッセージが表示されます。 なぜですか?

理由と動作はパスワードの変更時と同じですが、表示される画面は若干異なります。

#### Microsoftは推奨されるパスワードポリシーのガイダンスを提供していますか?

[Microsoftの推奨されるパスワード ポリシー](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/password-policy-recommendations)には、次の原則が含まれています。

- パスワードの最小長を 8 文字に設定する
- 特定の文字構成 (必須の記号など) は必要ありません
- パスワードの定期的な変更を強制しない
- 一般的に使用されるパスワードまたは脆弱なパスワードをブロックする
- ビジネス以外の目的で組織のパスワードを再利用しない

調査によると、ユーザーがパスワードを再利用または簡素化する傾向がある場合、パスワードの変更や複雑なシンボルの要件を頻繁に適用すると、セキュリティが低下する可能性があります。 Microsoftでは、古い前提ではなく、これらのガイドラインに基づく最新のパスワード プラクティスをお勧めします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/authentication/tutorial-risk-based-sspr-mfa"} -->
## Microsoft Entra ID でのリスクベースのユーザー サインイン保護 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-risk-based-sspr-mfa
- Service: entra-id / authentication
- Article date: 2025-03-04
- Summary: このチュートリアルでは、ユーザーのアカウントで危険なサインイン動作が検出されたときに Microsoft Entra ID Protection を有効にしてユーザーを保護する方法について学習します。

ユーザーを保護するため、危険な行為に自動的に対応するリスクベースの Microsoft Entra 条件付きアクセス ポリシーを構成できます。 これらのポリシーでは、サインインの試みを自動的にブロックしたり、安全なパスワードの変更の要求や Microsoft Entra 多要素認証の要求といった追加のアクションを要求したりできます。 これらのポリシーは、既存の Microsoft Entra 条件付きアクセス ポリシーと共に、組織の追加の保護レイヤーとして機能します。 ユーザーがこれらのポリシーのいずれかで危険な行為をトリガーすることはないと思われますが、セキュリティを侵害しようとする試みがあった場合、組織は保護されます。

重要

このチュートリアルでは、管理者のためにリスクベースの多要素認証 (MFA) を有効にする方法を示します。

IT チームが Microsoft Entra 多要素認証を使用するための機能を有効にしていない場合、またはサインイン時に問題が発生している場合は、ヘルプ デスクに連絡して追加のサポートを依頼してください。

このチュートリアルでは、次の作業を行う方法について説明します。

- 利用できるポリシーを理解する
- Microsoft Entra 多要素認証登録を有効にする
- リスクベースのパスワードの変更を有効にする
- リスクベースの多要素認証を有効にする
- ユーザー サインイン試行に対するリスクベースのポリシーをテストする

### 前提条件

このチュートリアルを完了するには、以下のリソースと特権が必要です。

- 少なくとも Microsoft Entra ID P2 または試用版のライセンスが有効になっている稼働中の Microsoft Entra テナント。
    - 必要に応じて、[無料で作成できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- セキュリティ管理者特権を持つアカウント。
- セルフサービス パスワード リセットと Microsoft Entra 多要素認証の構成がされている Microsoft Entra ID
    - 必要に応じて、[Microsoft Entra SSPR を有効にするためのチュートリアルを完了します](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr)。
    - 必要に応じて、[Microsoft Entra 多要素認証を有効にするためのチュートリアルを完了します](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-azure-mfa)。

### Microsoft Entra ID Protection の概要

Microsoft では、ユーザーのサインイン試行の一環として、毎日、何兆もの匿名化されたシグナルを収集して分析しています。 これらのシグナルは、適切なユーザー サインイン動作のパターンを構築し、潜在的に危険なサインイン試行を識別するのに役立ちます。 Microsoft Entra ID Protection は、ユーザーによるサインインの試みを確認し、疑わしい行為がある場合は追加のアクションを実行できます。

Microsoft Entra ID Protection のリスク検出をトリガーする可能性があるアクションの一部を次に示します。

- 資格情報が漏洩したユーザー。
- 匿名の IP アドレスからのサインイン。
- 特殊な場所へのあり得ない移動。
- 感染しているデバイスからのサインイン。
- 不審なアクティビティのある IP アドレスからのサインイン。
- 未知の場所からのサインイン。

この記事では、ユーザーを保護して、疑わしいアクティビティへの応答を自動化する、3 つのポリシーを有効にする手順について説明します。

- 多要素認証登録ポリシー
    - ユーザーが Microsoft Entra 多要素認証に登録されていることを確認します。 サインイン リスク ポリシーで MFA を要求する場合は、ユーザーが Microsoft Entra 多要素認証に登録済みである必要があります。
- ユーザー リスクのポリシー
    - 資格情報が侵害された可能性のあるユーザー アカウントを識別して自動的に対処します。 ユーザーに新しいパスワードの作成を促すことができます。
- サインイン リスク ポリシー
    - 疑わしいサインインの試みを識別して自動的に対処します。 Microsoft Entra 多要素認証を使って、追加の検証フォームを提供するようユーザーに要求できます。

リスクベースのポリシーを有効にするときは、リスク レベルのしきい値 ("低"、"中"、または "高") を選ぶこともできます。 この柔軟性により、不審なサインイン イベントの制御をどの程度積極的に行うかを決定できます。 次のポリシー構成をお勧めします。

Microsoft Entra ID Protection の詳細については、「[Microsoft Entra ID Protection とは?](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)」を参照してください

### 多要素認証登録ポリシーを有効にする

Microsoft Entra ID Protection には、Microsoft Entra 多要素認証にユーザーを登録するのに役立つ既定のポリシーが含まれています。 他のポリシーを使ってサインイン イベントを保護する場合は、ユーザーが MFA に既に登録されている必要があります。 このポリシーを有効にすると、ユーザーはサインイン イベントごとに MFA を実行する必要がなくなります。 このポリシーは、ユーザーの登録状態のみを調べ、必要があればユーザーに事前登録を求めます。

多要素認証を使うユーザーに対しては、この登録ポリシーを有効にすることをお勧めします。 このポリシーを有効にするには、次の手順を実行します。

1. [セキュリティ管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **ID Protection**&gt;**Dashboard**&gt;**Multifactor 認証登録ポリシー**を参照します。
3. 既定では、ポリシーは *[すべてのユーザー]* に適用されます。 必要に応じて、 **[割り当て]** を選択し、ポリシーを適用するユーザーまたはグループを選択します。
4. *[制御]* で、 **[アクセス]** を選択します。 *[Microsoft Entra 多要素認証登録の要求]* のオプションにチェックが入っていることを確認してから、**[選択]** を選択します。
5. **[ポリシーの適用]** を *[オン]* にして、 **[保存]** を選択します。

[Image: MFA への登録をユーザーに要求する手順のスクリーンショット。]

### ユーザー リスク ポリシーを有効にしてパスワード変更を求める

Microsoft では、研究者、法執行機関、Microsoft のさまざまなセキュリティ チーム、その他の信頼できる情報源と協力して、ユーザー名とパスワードのペアを調査しています。 それらのペアのいずれかが環境内のアカウントに一致すると、リスクベースのパスワード変更を要求できます。 このポリシーとアクションでは、以前に流出した資格情報を無効にするために、ユーザーは、サインインする前にパスワードを更新する必要があります。

このポリシーを有効にするには、次の手順を実行します。

1. [条件付きアクセス管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)にサインインします。
2. **Entra ID**&gt;**Conditional Access** に移動します。
3. **[新しいポリシー]** を選択します。
4. ポリシーに名前を付けます。 ポリシーの名前に対する意味のある標準を組織で作成することをお勧めします。
5. **[割り当て]** で、 **[ユーザーまたはワークロード ID]**を選択します。
    1. **[Include](https://learn.microsoft.com/ja-jp/entra/identity/authentication/含める)** で、 **[すべてのユーザー]** を選択します。
    2. **[除外]** で、 **[ユーザーとグループ]** を選択し、組織の緊急アクセス用または非常用アカウントを選択します。
    3. **完了** を選択します。
6. [ **ターゲット リソース**&gt;**Include**] で、[ **すべてのリソース ] (以前の [すべてのクラウド アプリ])** を選択します。
7. **[条件]**&gt;**[ユーザー リスク]** で、**[構成]** を **[はい]**に設定します。
    1. **[ポリシーを適用するために必要なユーザー リスク レベルを構成する]** で、**[高]** を選びます。 [このガイダンスは Microsoft の推奨事項に基づいており、組織ごとに異なる場合があります](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#choosing-acceptable-risk-levels)
    2. **完了** を選択します。
8. **[アクセス制御]**&gt;**[許可]** で、 **[アクセス権の付与]**を選択します。
    1. **リスク修正を要求**を選択します。 **[Require authentication strength** grant control]\(認証強度付与の制御が必要\) が自動的に選択されます。 組織に適した強度を選択します。
    2. **[選択]** を選択します。
9. [ **セッション**] の [ **サインイン頻度] - 毎回** がセッション コントロールとして自動的に適用され、必須です。
10. 設定を確認し、 **[ポリシーの有効化]** を **[レポート専用]** に設定します。
11. [ **作成] を** 選択してポリシーを作成します。

[ポリシーの影響モードまたはレポート専用モード](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-report-only#reviewing-results)を使用して設定を確認したら、[**ポリシーの有効化]** トグルを **[レポートのみ]** から **[オン]** に移動します。

### サインイン リスク ポリシーを有効にして MFA を求める

ほとんどのユーザーは、追跡可能な通常の動作を行うものです。 ユーザーがその通常動作から外れた場合、そのユーザーに正常なサインインを許可すると、危険である可能性があります。 代わりに、そのユーザーをブロックしたり、多要素認証の実行を求めたりすることもできます。 ユーザーが MFA チャレンジを正常に完了した場合は、それを有効なサインイン試行と見なして、アプリケーションまたはサービスへのアクセスを許可することができます。

このポリシーを有効にするには、次の手順を実行します。

1. [条件付きアクセス管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)にサインインします。
2. **Entra ID**&gt;**Conditional Access** に移動します。
3. **[新しいポリシー]** を選択します。
4. ポリシーに名前を付けます。 ポリシーの名前に対する意味のある標準を組織で作成することをお勧めします。
5. **[割り当て]** で、 **[ユーザーまたはワークロード ID]**を選択します。
    1. **[Include](https://learn.microsoft.com/ja-jp/entra/identity/authentication/含める)** で、 **[すべてのユーザー]** を選択します。
    2. **[除外]** で、 **[ユーザーとグループ]** を選択し、組織の緊急アクセス用または非常用アカウントを選択します。
    3. **完了** を選択します。
6. **[Cloud アプリまたはアクション]**&gt;**[含める]** で、**[すべてのリソース] (旧称 "すべてのクラウド アプリ")** を選択します。
7. **[条件]**&gt;**[ログイン リスク]** で、**[構成]** を **[はい]**に設定します。
    1. **[このポリシーを適用するサインイン リスク レベルを選択します]** で、 **[高]** と **[中]** を選択します。 [このガイダンスは Microsoft の推奨事項に基づいており、組織ごとに異なる場合があります](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#choosing-acceptable-risk-levels)
    2. **完了** を選択します。
8. **[アクセス制御]**&gt;**[許可]** で、 **[アクセス権の付与]**を選択します。
    1. **[認証強度を要求する]** を選択し、一覧から組み込みの認証強度である **[多要素認証]** を選択します。
    2. **[選択]** を選択します。
9. **[セッション]**で
    1. **[サインインの頻度]** を選択します。
    2. **[毎回]** が選択されていることを確認します。
    3. **[選択]** を選択します。
10. 設定を確認し、 **[ポリシーの有効化]** を **[レポート専用]** に設定します。
11. **[作成]** を選択して、ポリシーを作成および有効化します。

[ポリシーの影響モードまたはレポート専用モード](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-report-only#reviewing-results)を使用して設定を確認したら、[**ポリシーの有効化]** トグルを **[レポートのみ]** から **[オン]** に移動します。

#### パスワードレスのシナリオ

[パスワードレス認証方式](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-deployment)を採用している組織の場合は、次の変更を行います。

##### パスワードレス サインイン リスク ポリシーを更新する

1. **[ユーザー]**の:
    1. [**含む**] で [**ユーザーとグループ**] を選択し、パスワードレス ユーザーを対象とします。
    2. **[除外]** で、 **[ユーザーとグループ]** を選択し、組織の緊急アクセス用または非常用アカウントを選択します。
    3. **完了** を選択します。
2. **[Cloud アプリまたはアクション]**&gt;**[対象]** で、**[すべてのリソース]** (旧称 [すべてのクラウド アプリ]) を選択します。
3. **[条件]**&gt;**[ログイン リスク]** で、**[構成]** を **[はい]**に設定します。
    1. **[このポリシーを適用するサインイン リスク レベルを選択します]** で、 **[高]** と **[中]** を選択します。 リスク レベルの詳細については、「[許容されるリスク レベル](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies#choosing-acceptable-risk-levels)の選択」を参照してください。
    2. **完了** を選択します。
4. **[アクセス制御]**&gt;**[許可]** で、 **[アクセス権の付与]**を選択します。
    1. **[認証強度が必要]** を選択してから、対象のユーザーが持つ方法に基づいて、組み込みの **[パスワードレス MFA]** または **[フィッシング防止 MFA]** を選択します。
    2. **[選択]** を選択します。
5. **[セッション]**で:
    1. **[サインインの頻度]** を選択します。
    2. **[毎回]** が選択されていることを確認します。
    3. **[選択]** を選択します。

### 危険なサインイン イベントをテストする

ほとんどのユーザー サインイン イベントでは、前の手順で構成したリスクベースのポリシーがトリガーされることはありません。 MFA やパスワードのリセットを求めるプロンプトを、ユーザーが一度も目にしない可能性もあります。 ユーザーの資格情報が安全に保たれ、その動作が一貫している場合、そのユーザーのサインイン イベントは成功します。

前の手順で作成した Microsoft Entra ID Protection ポリシーをテストするには、リスクのある振舞いまたは潜在的な攻撃をシミュレートする手段が必要です。 これらのテストを実行する手順は、検証したい Microsoft Entra ID Protection ポリシーによって異なります。 シナリオと手順の詳細については、「[Microsoft Entra ID Protection でのリスク検出のシミュレーション](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-simulate-risk)」を参照してください。

### リソースをクリーンアップする

テストを完了し、リスクベースのポリシーを有効にする必要がなくなったら、無効にする各ポリシーに戻って、**[ポリシーの有効化]** を *[オフ]* に設定するか、ポリシーを削除します。
<!-- /MSL-PAGE -->
