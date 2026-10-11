# Microsoft Learn — Microsoft Entra / ユーザー・デバイス・ロール・マネージド ID (part 1)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 54

---

<!-- MSL-PAGE {"url":"entra/identity"} -->
## Microsoft Entra ID のドキュメント - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity
- Service: entra-id
- Article date: 2025-10-23
- Summary: Microsoft Entra ID は、コア ディレクトリ サービス、アプリケーション アクセス管理、ID 保護を 1 つのソリューションに組み合わせた、Microsoft のマルチテナント、クラウドベースのディレクトリ、ID 管理サービスです。

Microsoft Entra ID を使用してユーザー ID を管理し、アプリ、データ、リソースへのアクセスを制御します。

### 作業の開始

[Microsoft Entra ID とは](https://learn.microsoft.com/ja-jp/entra/fundamentals/what-is-entra)
ユーザー ID を管理し、アプリ、データ、リソースへのアクセスを制御します。

[認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)
セルフサービス パスワード リセットや多要素認証などのサインイン方法とセキュリティ機能を構成します。

[アプリケーション管理](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-application-management)
アプリを開発、追加、または Microsoft Entra ID に接続し、アクセスを管理します。

[ロールベースのアクセス制御](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview)
Microsoft Entra ロールベースのアクセス制御を使用して、管理者のアクセス許可を管理し、最小特権の原則を適用します。

[ユーザー管理](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-overview-user-model)
ユーザーとグループの管理、ライセンスの割り当て、ドメイン名の管理。

[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)
ユーザー、場所、デバイスなどのポリシーを適用して、リソースへのアクセスを制御します。

[デバイス ID](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)
デバイス管理と条件付きアクセスのMicrosoft Entra IDにデバイスを登録して参加させる。

[ハイブリッド ID](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/whatis-hybrid-identity)
オンプレミスとクラウドの両方のリソースにアクセスできるユーザー ID を作成します。

[アプリケーションのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)
Microsoft Entra ID からプロビジョニングすることで、アプリケーションでユーザー ID を作成および管理します。

[アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)
セキュリティで保護されたリモート アクセスとクラウド スケールのセキュリティをプライベート アプリケーションに提供します。

[Azure リソースのマネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)
Microsoft Entra 認証を使用してリソースに接続できるアプリケーション ID を作成します。

[アプリケーションの統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)
クラウド対応のサービスとしてのソフトウェア (SaaS) アプリを Microsoft Entra ID と統合します。

[監視とヘルス](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-monitoring-health)
ログとレポートを使用して、アプリとサービスの使用状況の特定、リスクの検出、問題のトラブルシューティングを行います。

[マルチテナント組織](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/overview)
組織内のテナント間で共同作業を行います。

[ドメイン サービス](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/overview)
ユーザー アカウント、グループ、アクセスを維持しながら、レガシ アプリをクラウド内のマネージド ドメインに移動します。

[テナント ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/overview)
関連するテナントの検出、テナント間のガバナンス関係の管理、テナント構成の監視、セキュリティで保護されたテナントの作成。

[Azure Active Directory B2C](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/)
アプリ向けのビジネス・トゥ・コンシューマー (B2C) 向けのアイデンティティとアクセス管理。

### リソース

[Microsoft Entra ID トレーニング](https://learn.microsoft.com/ja-jp/training/browse/?resource_type=module&expanded=entra&products=entra-id)
Microsoft Entra ID の詳細については、詳細なガイダンスを参照してください。

[Microsoft Entra ID の新機能](https://learn.microsoft.com/ja-jp/entra/fundamentals/whats-new)
Microsoft Entra ID の最近の開発について説明します。

[Azure AD の新しい名前](https://learn.microsoft.com/ja-jp/entra/fundamentals/new-name)
新しい名前、変更の意味、およびそれがユーザーに与える影響の詳細について説明します。

[組織の ID をセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/fundamentals/configure-security)
ID を保護するためのセキュリティに関する推奨事項。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices"} -->
## Microsoft Entra デバイス ID のドキュメント - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices
- Service: entra-id / devices
- Article date: 2024-06-27
- Summary: Microsoft Entra ID でデバイス ID を構成および管理する方法について説明します。

Microsoft Entra ID でデバイス ID を構成および管理する方法について説明します。

### デバイス ID について

#### 概要

- [デバイス ID とは](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)
- [エンタープライズ状態ローミングとは](https://learn.microsoft.com/ja-jp/entra/identity/devices/enterprise-state-roaming-enable)
- [Microsoft Entra デバイスの展開を計画する](https://learn.microsoft.com/ja-jp/entra/identity/devices/plan-device-deployment)

#### 概念

- [Microsoft Entra 登録済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-device-registration)
- [Microsoft Entra 参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)
- [Microsoft Entra ハイブリッド参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join)
- [オンプレミス リソースへのシングル サインオン (SSO)](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-sso-to-on-premises-resources)
- [プライマリ更新トークン](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token)

### Microsoft Entra 参加済みデバイスの構成と管理

#### 攻略ガイド

- [Microsoft Entra 参加の実装を計画する](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-plan)
- [Microsoft Entra 参加済みデバイスでローカル管理者グループを管理する](https://learn.microsoft.com/ja-jp/entra/identity/devices/assign-local-admin)
- [Microsoft Entra ID で古いデバイスを検索して管理する](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-stale-devices)

### Microsoft Entra ハイブリッド参加済みデバイスの構成と管理

#### 攻略ガイド

- [Microsoft Entra ハイブリッド結合の実装を計画する](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)
- [Microsoft Entra ハイブリッド参加済みデバイスのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-hybrid-join-windows-current)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/assign-local-admin"} -->
## Microsoft Entra 参加済みデバイスのローカル管理者を管理する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/assign-local-admin
- Service: entra-id / devices
- Article date: 2026-06-17
- Summary: Windows デバイスのローカル管理者グループに Azure のロールを割り当てる方法について説明します。

Windows デバイスを管理するには、ローカル管理者グループのメンバーになる必要があります。 Microsoft Entra 参加プロセスの一環として、Microsoft Entra ID によってデバイスでのこのグループのメンバーシップが更新されます。 メンバーシップの更新方法は、ビジネス要件に応じてカスタマイズすることもできます。 メンバーシップの更新は、たとえば、デバイスへの管理者権限を要するタスクをヘルプデスク スタッフが実行できるようにするうえで役立ちます。

この記事では、Microsoft Entra 参加の間に、ローカル管理者のメンバーシップがどのように更新されるのか、およびそれをカスタマイズする方法について説明します。 この記事の内容は、 **Microsoft Entra ハイブリッド参加済みデバイスには** 適用されません。

### しくみ

Microsoft Entra 参加時に、次のセキュリティ プリンシパルが、デバイスのローカル管理者グループに追加されます。

- [Microsoft Entra 参加済みデバイスのローカル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#microsoft-entra-joined-device-local-administrator)ロールおよび[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)ロール
- Microsoft Entra の参加を実行するユーザー

メモ

これは、結合操作中にのみ行われます。 この時点以降に管理者が変更を加える場合は、デバイスのグループ メンバーシップを更新する必要があります。

ユーザーを Microsoft Entra 参加済みデバイスローカル管理者ロールに追加することで、そのデバイス上で何も変更することなく、デバイスを管理できるユーザーを Microsoft Entra ID 内でいつでも更新できます。 Microsoft Entra 参加済みデバイス ローカル管理者ロールは、最小限の特権の原則をサポートするために、ローカル管理者グループに追加されます。

### 管理者ロールを管理する

[管理者ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) ロールのメンバーシップを表示および更新するには、次を参照してください。

- [Microsoft Entra ID で管理者ロールのすべてのメンバーを表示する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/view-assignments)
- [Microsoft Entra ID でユーザーを管理者ロールに割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)

### Microsoft Entra 参加済みデバイスのローカル管理者ロールを管理する

[Microsoft Entra 参加済みデバイスローカル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#microsoft-entra-joined-device-local-administrator)ロールは、**デバイス設定**から管理できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **Entra ID**&gt;**デバイス**&gt;**すべてのデバイス**&gt;**デバイス設定**に移動します。
3. **すべての Microsoft Entra 参加済みデバイスで [追加のローカル管理者の管理**] を選択します。
4. [ **割り当ての追加]** を選択し、追加する他の管理者を選択し、[追加] を選択 **します**。

Microsoft Entra 参加済みデバイスのローカル管理者ロールを変更するには、 **すべての Microsoft Entra 参加済みデバイスで追加のローカル管理者**を構成します。

メモ

このオプションには、Microsoft Entra ID P1 または P2 ライセンスが必要です。

Microsoft Entra 参加済みデバイスのローカル管理者は、すべての Microsoft Entra 参加済みデバイスに割り当てられます。 このロールの対象範囲を特定のデバイス セットに限定することはできません。 Microsoft Entra 参加済みデバイスのローカル管理者ロールを更新しても、影響を受けるユーザーにすぐに影響を与えるわけではありません。 ユーザーが既にサインインしているデバイスでは、次の "両方の" アクションが発生したときに特権の昇格が行われます。

- Microsoft Entra ID が適切な特権を持つ新しいプライマリ更新トークンを発行するのに、最大で 4 時間経過した。
- ユーザーはプロファイルを更新するために、ロック解除ではなく、一度サインアウトしてから再度サインインします。

ユーザーはローカル管理者グループに直接リストされず、そのアクセス許可はプライマリ更新トークンを通じて受け取られます。

メモ

上記のアクションは、関連するデバイスに以前にサインインしていないユーザーには適用されません。 この場合、管理者特権は、デバイスへの最初のサインインの直後に適用されます。

### Microsoft Entra グループを使用して管理者特権を管理する

Microsoft Entra グループを使用すると、 [ローカル ユーザーおよび](https://learn.microsoft.com/ja-jp/windows/client-management/mdm/policy-csp-localusersandgroups) グループ モバイル デバイス管理 (MDM) ポリシーを使用して、Microsoft Entra 参加済みデバイスの管理者特権を管理できます。 このポリシーを使用すると、Microsoft Entra 参加済みデバイスのローカル管理者グループに個々のユーザーまたは Microsoft Entra グループを割り当てることができ、さまざまなデバイス グループに対して個別の管理者をきめ細かく構成できます。

組織では、Intune を使用して [、カスタム OMA-URI 設定](https://learn.microsoft.com/ja-jp/mem/intune/configuration/custom-settings-windows-10) または [アカウント保護ポリシー](https://learn.microsoft.com/ja-jp/mem/intune/protect/endpoint-security-account-protection-policy)を使用してこれらのポリシーを管理できます。 このポリシーを使用する際の考慮事項は次のとおりです。

- ポリシーを使用して Microsoft Entra グループを追加するには、Microsoft [Graph API for Groups](https://learn.microsoft.com/ja-jp/graph/api/resources/group) を実行して取得できるグループのセキュリティ識別子 (SID) が必要です。 SID は、API 応答の `securityIdentifier` プロパティと同等です。
- このポリシーを使う管理者特権は、Windows 10 以降のデバイスの既知のグループ (Administrators、Users、Guests、Power Users、リモート デスクトップ Users、Remote Management Users) に対してのみ評価されます。
- Microsoft Entra グループを使用したローカル管理者の管理は、Microsoft Entra ハイブリッド参加済みデバイスまたは Microsoft Entra 登録済みデバイスには適用されません。
- このポリシーを使用してデバイスに展開された Microsoft Entra グループは、リモート デスクトップ接続には適用されません。 Microsoft Entra 参加済みデバイスに対するリモート デスクトップのアクセス許可を制御するには、個々のユーザーの SID を適切なグループに追加する必要があります。

重要

Microsoft Entra ID による Windows サインインでは、最大 20 グループについての管理者権限の評価がサポートされています。 管理者権限が正しく割り当てられるように、各デバイスに 20 個以下の Microsoft Entra グループを用意し、ユーザーを 20 グループ以下のメンバーにすることをお勧めします。 この制限は、入れ子になったグループにも適用されます。

### 通常のユーザーの管理

Microsoft Entra ID では既定で、Microsoft Entra 参加を実行するユーザーはデバイスの管理者グループに追加されます。 通常のユーザーがローカル管理者になることを防ぐ必要がある場合は、次のオプションで対応できます。

- **デバイス登録ポリシー** - デバイス登録ポリシーは、Microsoft Entra参加を実行するユーザーが、参加するデバイスのローカル管理者になるかどうかを制御します。 この設定は、参加しているデバイスのローカル Administrators グループのメンバーシップに影響します。 グローバル管理者などのMicrosoft Entra ディレクトリ ロールは割り当てられません。また、Microsoft Entra参加済みデバイスローカル管理者ロールにはユーザーを追加しません。 Microsoft Graphでは、この設定は`azureADJoin.localAdmins.registeringUsers`の  プロパティによって表されます。
- [Windows Autopilot](https://learn.microsoft.com/ja-jp/autopilot/windows-autopilot) - Windows Autopilot には、 [Autopilot プロファイルを作成](https://learn.microsoft.com/ja-jp/autopilot/enrollment-autopilot#create-an-autopilot-deployment-profile)することで、参加を実行するプライマリ ユーザーがローカル管理者になることを防ぐオプションが用意されています。
- [一括登録](https://learn.microsoft.com/ja-jp/mem/intune/enrollment/windows-bulk-enroll) - 一括登録のコンテキストで実行される Microsoft Entra の結合は、自動生成されたユーザーのコンテキストで行われます。 デバイスの参加後にサインインしたユーザーは、管理者グループに追加されません。

### デバイスのユーザーを手動で昇格させる

Microsoft Entra 参加プロセスの使用に加えて、通常のユーザーを手動で昇格させて、特定のデバイスのローカル管理者にすることもできます。 この手順を実行するには、既にローカル管理者グループのメンバーになっている必要があります。

**Windows 10 1709** リリース以降では、**設定**&gt;**Accounts**&gt;**その他のユーザー**からこのタスクを実行できます。 [**職場または学校のユーザーの追加]** を選択し、[ユーザー **アカウント**] にユーザーのユーザー プリンシパル名 (UPN) を入力し、[*アカウントの種類*] で **[管理者**] を選択します

また、コマンド プロンプトを使用してユーザーを追加することもできます。

- テナント ユーザーがオンプレミスの Active Directory から同期されている場合は、 `net localgroup administrators /add "<domain>\<username>"`を使用します。 `<domain>` はオンプレミスの Active Directory ドメイン名で、 `<username>` はユーザーの SAM アカウント名です。
- テナント ユーザーが Microsoft Entra ID で作成されている場合は、 `net localgroup administrators /add "AzureAD\<UserUPN>"`を使用します。ここで、 `<UserUPN>` はユーザーのユーザー プリンシパル名です。

### 考慮事項

- ロール ベースのグループは、Microsoft Entra 参加済みデバイスローカル管理者ロールにのみ割り当てることができます。
- Microsoft Entra 参加済みデバイスのローカル管理者ロールは、すべての Microsoft Entra 参加済みデバイスに割り当てられます。 このロールを特定のデバイス セットに限定することはできません。
- Windows デバイスのローカル管理者権限は、 [Microsoft Entra B2B ゲスト ユーザー](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)には適用されません。
- Microsoft Entra 参加済みデバイスローカル管理者ロールからユーザーを削除しても、変更はすぐには行われません。 ユーザーは、デバイスにサインインしている限り、ローカルの管理者特権を持っています。 特権は、次回サインイン中に、新しいプライマリ更新トークンが発行されるときに失効します。 この失効は、特権の昇格と同様に、最大で 4 時間かかる場合があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/browser-access-guidance-third-party-devices"} -->
## サード パーティのモバイル デバイス管理プロバイダー向けのブラウザー アクセス ガイダンス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/browser-access-guidance-third-party-devices
- Service: entra-id / devices
- Article date: 2025-10-15
- Summary: MDM プロバイダーとして、お客様が CA で保護されたリソースにシームレスにアクセスできるように、デバイス登録中にブラウザー アクセスを有効にするように Android デバイス ポリシーを更新したいと考えています。

デバイスの登録中にブラウザー アクセスを自動的に有効にできるように [、Android デバイス ポリシー](https://developers.google.com/android/management/reference/rest/v1/enterprises.policies) リソースを更新します。

Microsoft Entra の新機能ブログで 2024 年 9 月と 2025 年 11 月に発表されたとおり、Android ユーザー向けのブラウザー アクセスは既定で自動的に有効になります。 この変更は、 [セキュリティで保護された将来のイニシアティブ](https://www.microsoft.com/microsoft-cloud/resources/secure-future-initiative)の一部として、すべての Microsoft 製品を強化する一環です。 この取り組みの一環として、登録が完了した後にデバイス ストレージからデバイス登録キーをエクスポートするメカニズムはなくなりました。

この変更により、Android デバイスのユーザーは、デバイスが Microsoft Entra ID に登録された後、Authenticator アプリまたはポータル サイトでブラウザーのアクセス設定を変更できなくなります。 代わりに、Android ユーザーは既定でブラウザー アクセスが有効になっています。

### MDM プロバイダーの場合

デバイスの登録時にブラウザー アクセスを有効にできるように [、Android デバイス ポリシー](https://developers.google.com/android/management/reference/rest/v1/enterprises.policies) リソースを変更するようお願いします。 ポリシー リソースは、顧客がデバイスに適用するデバイスとアプリ管理設定のグループを作成して保存するために使用されます。

顧客に代わってブラウザー アクセスを有効にするには、Authenticator アプリとポータル サイトに委任された証明書を提供するように既存のポリシーを作成または変更する必要があります。

ポリシーの例を次に示します。

**認証子の委任されたスコープCERT\_INSTALLを設定するためのポリシーの例**

```html
"applications": [{
    "packageName": "com.azure.authenticator"
    "installType": "REQUIRED_FOR_SETUP"
    "delegatedScopes": [
        "CERT_INSTALL"
      ]   
}],
```

CERT\_INSTALL - 証明書のインストールと管理へのアクセスを許可します。 - https://developer.android.com/reference/android/app/admin/DevicePolicyManager#DELEGATION_CERT_INSTALL

詳細については、 [Android MDM でのポリシーの作成と適用](https://microsoft.sharepoint-df.com/:w:/t/AzureADDevices/EUBvQT-nqK1GhgrNwoOsUbYBUfCGH0uZM7bLQBPS56bggw?e=UvUuF0)に関するページを参照してください。

要求された変更を行わなかった場合、エンド ユーザーは次の動作を行います。

- デバイスがブラウザー アクセスが有効になっている Microsoft Entra に登録されている場合
    - 効果なし
- デバイスが Microsoft Entra に登録されているが、ブラウザー アクセスが有効になっていない場合:
    - Microsoft Edge 以外のブラウザーで CA で保護されたリソースへのアクセスはブロックされます。
    - ユーザーが Web ブラウザーで CA で保護されたリソースにアクセスする必要がある場合は、Microsoft Edge を使用する必要があります。
- デバイスが初めて登録を受けている場合:
    - ユーザーは、証明書の種類を選択し、登録フローの一部として証明書に名前を付けるよう求められます。
    - この証明書は、ブラウザー アクセスを有効にするために使用されます。
    - この証明書がデバイス上にあると、ブラウザーアクセスが有効になり、ユーザーは任意のブラウザーで CA で保護されたリソースにアクセスできます。

[Image: デバイス証明書の設定のスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/concept-device-registration"} -->
## Microsoft Entra 登録済みデバイスとは - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-device-registration
- Service: entra-id / devices
- Article date: 2025-06-27
- Summary: Microsoft Entra 登録済みデバイスによって、Bring Your Own Device (BYOD) シナリオまたはモバイル デバイス シナリオのサポートをユーザーに提供できる仕組みを説明します。

Microsoft Entra 登録済みデバイス (ワークプレース参加済みデバイス) の目的は、Bring Your Own Device (BYOD) シナリオまたはモバイル デバイス シナリオのサポートをユーザーに提供することです。 これらのシナリオでは、ユーザーは個人所有のデバイスを使用して、組織のリソースにアクセスできます。

| Microsoft Entra 登録済み | 説明 |
| --- | --- |
| **定義** | 組織アカウントでのデバイスへのサインインを必要としない Microsoft Entra ID 登録 |
| **主な対象** | 次の条件に該当するすべてのユーザーに適用できます。<br>- 自分のデバイスを持ち込む<br>- モバイル デバイス |
| **デバイスの所有権** | ユーザーまたは組織 |
| **オペレーティング システム** | - Windows 10 以降<br>- macOS 10.15 以降<br>- iOS 15 以降<br>- Android<br>- Linux エディション:<br>- Ubuntu 22.04/24.04 LTS<br>- Red Hat Enterprise Linux 8/9 LTS |
| **プロビジョニング** | - Windows 10 以降 – 設定<br>- iOS/Android – ポータル サイトまたは Microsoft Authenticator アプリ<br>- macOS – 会社ポータル<br>- Linux - Intune エージェント |
| **デバイスのサインイン オプション** | - エンドユーザーのローカル資格情報<br>- パスワード<br>- Windows Hello<br>- PIN<br>- 他のデバイスの生体認証またはパターン認証 |
| **デバイス管理** | - モバイル デバイス管理 (例:Microsoft Intune)<br>- モバイル アプリケーション管理 |
| **主な機能** | - クラウド リソースへのシングル サインオン (SSO)<br>- Intune への登録時の条件付きアクセス<br>- アプリ保護ポリシーを使用する条件付きアクセス<br>- Microsoft Authenticator アプリでの電話によるサインインを有効にします |

[Image: Microsoft Entra の登録デバイス]

Microsoft Entra 登録済みデバイスへのサインインには、ローカル アカウント (たとえば、Windows 10 以降のデバイスで使用する Microsoft アカウントなど) を使用できます。 登録済みデバイスには、組織のリソースにアクセスするための Microsoft Entra アカウントがあります。 組織内リソースへのアクセスは、その Microsoft Entra アカウントと、デバイス ID に適用される条件付きアクセス ポリシーに基づいて制限できます。

Microsoft Entra における登録は、デバイスの登録とは異なる概念です。 管理者がユーザーにデバイスの登録を許可している組織では、それらの Microsoft Entra 登録済みデバイスを Microsoft Intune などのモバイル デバイス管理 (MDM) ツールに登録することで、さらに制御を強化できます。 MDM では、ストレージの暗号化、パスワードの複雑さ、セキュリティ ソフトウェアを常に最新の状態に保つことを求めるなど、組織に必要な構成を適用する手段が提供されます。

Microsoft Entra の登録は、作業用アプリケーションに初めてアクセスする際に自動で実行することも、Windows 10 または Windows 11 の設定メニューから手動で実行することもできます。

### シナリオ

組織内のユーザーが、自宅の PC から福利厚生の登録ツールにアクセスしたいと考えています。 組織によって、すべてのユーザーは Intune に準拠したデバイスからこのツールにアクセスすることが要求されています。 ユーザーが自宅の PC を Microsoft Entra ID に登録し、そのデバイスを Intune に登録すると、必要な Intune ポリシーが適用され、ユーザーにリソースへのアクセス権が付与されます。

別のユーザーは、ルート化されている個人用の Android フォンで組織のメールにアクセスしたいと考えています。 会社では準拠デバイスが必要であり、ルート化されたデバイスをブロックする Intune デバイス コンプライアンス ポリシーがあります。 このデバイス上の組織リソースへの従業員からのアクセスは阻止されます。

注

Microsoft Entra 登録済みデバイスでは、 [統合書き込みフィルター機能は](https://learn.microsoft.com/ja-jp/windows/configuration/unified-write-filter/) サポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/concept-directory-join"} -->
## Microsoft Entra 参加済みデバイスとは何ですか? - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join
- Service: entra-id / devices
- Article date: 2025-06-27
- Summary: Microsoft Entra 参加済みデバイスは、環境内のリソースにアクセスするデバイスを管理するために役立ちます。

規模や業界を問わずあらゆる組織で、Microsoft Entra 参加済みデバイスをデプロイできます。 Microsoft Entra 参加は、ハイブリッド環境でも動作し、クラウドおよびオンプレミス アプリとリソースと両方にアクセスできます。

| Microsoft Entra Join | 説明 |
| --- | --- |
| **定義** | - Microsoft Entra ID にのみ参加し、デバイスにサインインするには組織アカウントが必要です |
| **主な対象** | - クラウド専用およびハイブリッド組織の両方に適しています。<br>- 組織内のすべてのユーザーに適用できます |
| **デバイスの所有権** | - 組織 |
| **オペレーティング システム** | - すべての Windows 11 および Windows 10 デバイス (Home エディションを除く)<br>- [Azure で実行されている Windows Enterprise マルチセッション仮想マシン](https://learn.microsoft.com/ja-jp/azure/virtual-desktop/windows-multisession-faq#can-windows-enterprise-multi-session-be-microsoft-entra-joined)<br>- [Azure で実行されている Windows Server 2019 以降の仮想マシン](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-vm-sign-in-azure-ad-windows) (Server コアはサポートされていません)<br>- macOS 13 以降を実行している Apple デバイス<br>- Linux エディション:<br>    - Ubuntu 22.04/24.04/26.04 LTS<br>    - Red Hat Enterprise Linux 9/10 LTS |
| **プロビジョニング** | - セルフサービス: Windows の Out of Box Experience (OOBE) または設定<br>- 一括登録<br>- Windows オートパイロット<br>- (パブリック プレビュー) Apple 自動デバイス登録 (Apple デバイスのみに適用) |
| **デバイスの管理** | - モバイル デバイス管理 (例:Microsoft Intune)<br>- [Configuration Manager のスタンドアロンまたは Microsoft Intune との共同管理](https://learn.microsoft.com/ja-jp/mem/configmgr/comanage/overview) |
| **主な機能** | - クラウドとオンプレミスの両方のリソースへのシングル サインオン (SSO)<br>- 条件付きアクセス<br>- [ロック画面でのセルフサービス パスワード リセットおよび Windows Hello PIN のリセット](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-windows) |
|  |  |

### **デバイスのサインイン オプション**

Microsoft Entra 参加済みデバイスでサポートされているサインイン オプションを次に示します。 これらのオプションの可用性は、デバイスのオペレーティング システムと構成によって異なります。 たとえば、Windows Hello for Business では追加のセットアップが必要であり、一部のデバイスでは使用できない場合があります。

| プラットフォーム | パスワード | SmartCard | Microsoft Authenticator電話によるサインイン | [Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-planning-guide)/[プラットフォーム資格情報](https://learn.microsoft.com/ja-jp/entra/identity/devices/macos-psso) | ウェブサインイン | Fido2 |
| --- | --- | --- | --- | --- | --- | --- |
| Windows 10/11 | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| macOS 13 以降 | ✅ | ✅ |  | ✅ |  |  |
| Ubuntu 22.04/24.04/26.04 LTS | ✅ | ✅ | ✅ |  |  |  |
| RHEL 9/10 | ✅ | ⚠️ プレビュー | ✅ |  |  |  |

Microsoft Entra アカウントを使用して、Microsoft Entra 参加済みデバイスにサインインします。 自分のアカウントと、デバイスに適用される[条件付きアクセスポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-alt-all-users-compliant-hybrid-or-mfa)に基づいて、リソースへのアクセスを制御できます。

管理者は、Microsoft Intune などの Mobile デバイス管理 (MDM) ツールを使用するか、Microsoft Endpoint Configuration Manager を使用する共同管理シナリオで、Microsoft Entra 登録済みデバイスをセキュリティで保護し、さらに制御することができます。 これらのツールは、次のような組織に必要な構成を適用するための手段を提供します。

- ストレージを暗号化する必要がある
- パスワードの複雑さ
- ソフトウェア インストール
- ソフトウェア更新プログラム

管理者は、Configuration Manager を利用して Microsoft Entra 参加済みデバイスで組織のアプリケーションを利用できるようにし、[ビジネス向けおよび教育機関向けの Microsoft Store からのアプリを管理する](https://learn.microsoft.com/ja-jp/mem/configmgr/apps/deploy-use/manage-apps-from-the-windows-store-for-business)ことができます。

Microsoft Entra 参加は、Out of Box Experience (OOBE)、一括登録、[Apple 自動デバイス登録 (パブリック プレビュー)](https://learn.microsoft.com/ja-jp/mem/intune/enrollment/device-enrollment-program-enroll-macos)、[Windows オートパイロット](https://learn.microsoft.com/ja-jp/autopilot/enrollment-autopilot)などのセルフサービス オプションを使用して実行できます。

Microsoft Entra 参加済みデバイスでは、オンプレミス リソースが組織のネットワーク上にある場合、引き続きそれらへのシングル サインオン アクセスを維持できます。 Microsoft Entra 参加済みであるデバイスは引き続き、ファイル、印刷、およびその他のアプリケーションなどのオンプレミス サーバーに対して認証することができます。

### シナリオ

Microsoft Entra 参加は、次のようなさまざまなシナリオで使用できます。

- Microsoft Entra と、Intune などの MDM を使用して、クラウド ベースのインフラストラクチャに移行しようと考えている。
- たとえば、タブレットや電話などのモバイル デバイスを管理する必要がある場合に、オンプレミスのドメインへの参加を使用できない。
- ユーザーは主に、Microsoft 365 や、Microsoft Entra ID に統合されているその他のサービスとしてのソフトウェア (SaaS) アプリへのアクセスを必要としている。
- ユーザーのグループを Active Directory ではなく Microsoft Entra ID で管理したい。 このシナリオは、季節従業員、請負業者、学生などに適用できます。
- 在宅で働く作業員やリモートの支店の作業員に制限のあるオンプレミスのインフラに対する参加機能を提供したい。

すべての Windows 11 および Windows 10 デバイス (Home エディションを除く) で Microsoft Entra 参加を構成できます。

Microsoft Entra 参加済みデバイスの目的は、次の操作を単純化することです。

- 職場所有のデバイスの Windows と macOS でのデプロイ
- 任意の Windows と macOS デバイスからの組織のアプリとリソースへのアクセス
- 作業が所有するデバイスの、クラウド ベースの管理
- ユーザーが、Microsoft Entra ID または同期された Active Directory の職場あるいは学校アカウントを使用して、デバイスにサインインする動作。

[Image: オンプレミス ドメインと対話する Microsoft Entra 参加済みデバイスを示す図。]

Microsoft Entra 参加 は、次の方法のいずれかを使用してデプロイできます。

- [Windows Autopilot](https://learn.microsoft.com/ja-jp/autopilot/windows-autopilot)
- [一括デプロイ](https://learn.microsoft.com/ja-jp/mem/intune/enrollment/windows-bulk-enroll)
- [セルフサービス エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-out-of-box)
- [Apple 自動デバイス登録 (パブリック プレビュー)](https://learn.microsoft.com/ja-jp/mem/intune/enrollment/device-enrollment-program-enroll-macos)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/concept-hybrid-join"} -->
## Microsoft Entra ハイブリッド参加済みデバイスとは何ですか? - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join
- Service: entra-id / devices
- Article date: 2025-06-27
- Summary: デバイス ID 管理を使用して、環境内のリソースにアクセスしているデバイスを管理する方法について説明します。

既存の Active Directory 実装を持つ組織は、Microsoft Entra ハイブリッド参加済みデバイスを実装することで、Microsoft Entra ID によって提供される機能の一部を活用できます。 これらのデバイスは、オンプレミスの Active Directory に参加し、Microsoft Entra ID に登録されます。

Microsoft Entra ハイブリッド参加を使用したデバイスには、オンプレミスのドメイン コントローラーへのネットワーク接続が定期的に必要になります。 この接続がない場合、デバイスは使用できなくなります。 この要件が懸念される場合は、 [デバイスへの Microsoft Entra の参加](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join) を検討してください。

| Microsoft Entra ハイブリッド参加 | 説明 |
| --- | --- |
| **定義** | デバイスへのサインインに組織アカウントを必要とするオンプレミスの Microsoft Windows Server Active Directory と Microsoft Entra ID に参加している |
| **主な対象** | 既存のオンプレミスの Microsoft Windows Server Active Directory インフラストラクチャを持つハイブリッド組織に適しています |
|  | 組織内のすべてのユーザーに適用できます |
| **デバイスの所有権** | 組織 |
| **オペレーティング システム** | Windows 11 または Windows 10 (Home エディションを除く) |
|  | Windows Server 2016、2019、2022 |
| **プロビジョニング** | Windows 11、Windows 10、Windows Server 2016/2019/2022 |
|  | IT によるドメイン参加と Microsoft Entra Connect または AD FS 構成による自動参加 |
|  | Windows Autopilot によるドメイン参加と Microsoft Entra Connect または AD FS 構成による自動参加 |
| **デバイスのサインイン オプション** | 以下を使用する組織アカウント: |
|  | パスワード |
|  | [パスワードレス](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2) オプション ([Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-planning-guide) 、FIDO2.0 セキュリティ キーなど)。 |
| **デバイスの管理** | [グループ ポリシー](https://learn.microsoft.com/ja-jp/mem/configmgr/comanage/faq#my-environment-has-too-many-group-policy-objects-and-legacy-authenticated-apps--do-i-have-to-use-hybrid-azure-ad-) |
|  | [Configuration Manager のスタンドアロンまたは Microsoft Intune との共同管理](https://learn.microsoft.com/ja-jp/mem/configmgr/comanage/overview) |
| **主な機能** | クラウドとオンプレミスの両方のリソースへのシングル サインオン (SSO) |
|  | ドメイン参加または Intune (共同管理されている場合) による条件付きアクセス |
|  | [ロック画面でのセルフサービス パスワード リセットおよび Windows Hello PIN のリセット](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-sspr-windows) |

[Image: ハイブリッド参加済みデバイスのしくみを示す図。]

### シナリオ

次の場合は、Microsoft Entra ハイブリッド参加済みデバイスを使用します。

- 引き続き [グループ ポリシー](https://learn.microsoft.com/ja-jp/mem/configmgr/comanage/faq#my-environment-has-too-many-group-policy-objects-and-legacy-authenticated-apps--do-i-have-to-use-hybrid-azure-ad-) を使用してデバイス構成を管理する必要があります。
- 既存のイメージング ソリューションを引き続き使用して、デバイスを展開および構成する必要があります。
- Active Directory マシン認証に依存するこれらのデバイスに Win32 アプリが展開されている。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/concept-primary-refresh-token"} -->
## Microsoft Entra ID のプライマリ更新トークン (PRT) について - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token
- Service: entra-id / devices
- Article date: 2025-06-27
- Summary: Microsoft Entra ID でのプライマリ更新トークン (PRT) の役割と管理について説明します。

プライマリ更新トークン (PRT) は、サポートされているバージョンの Windows、iOS/macOS、Android、Linux での Microsoft Entra 認証の重要な成果物です。 PRT は、これらのデバイスで使用されるアプリケーション全体でシングル サインオン (SSO) を有効にするために、Microsoft ファースト パーティ トークン ブローカーに特別に発行されるセキュリティで保護された成果物です。 この記事では、PRT を発行、使用、保護する方法について説明します。これにより、セキュリティが強化され、アプリケーション間でシングル サインオン (SSO) が有効になります。

この記事では、Microsoft Entra ID で使用できるさまざまなデバイスの状態と、Windows でのシングル サインオンのしくみを既に理解していることを前提としています。 Microsoft Entra ID のデバイスの詳細については、「Microsoft [Entra ID でのデバイス管理とは」を](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)参照してください。

### 主要な用語とコンポーネント

次の Windows コンポーネントは、プライマリ更新トークン (PRT) の要求と使用において重要な役割を果たします。

| **用語** | **説明** |
| --- | --- |
| **ブローカー** | ID ブローカーは、ID プロバイダー (IdP) とサービス プロバイダー (SP) の間の仲介役として機能し、認証と承認を簡素化するサービスです。 Web アカウント マネージャーは、ID ブローカーの例です。 |
| **クラウド認証プロバイダー (CloudAP)** | CloudAP は、Windows サインイン用の最新の認証プロバイダーであり、ユーザーが Windows 10 以降のデバイスにログインしていることを確認します。 ID プロバイダーは、CloudAP が提供するプラグイン フレームワークを利用して、その ID プロバイダーの資格情報を使った Windows への認証を有効にすることができます。 |
| **Web アカウント マネージャー (WAM)** | WAM は、Windows 10 以降のデバイスの既定のトークン ブローカーです。 また ID プロバイダーは、WAM が提供するプラグイン フレームワークを利用して、その ID プロバイダーを利用しているアプリケーションへの SSO を有効にすることができます。 |
| **Microsoft Entra CloudAP プラグイン** | Windows サインイン中に Microsoft Entra ID を使用してユーザー資格情報を検証する CloudAP フレームワーク上に構築された Microsoft Entra 固有のプラグイン。 |
| **Microsoft Entra WAM プラグイン** | 認証に Microsoft Entra ID に依存するアプリケーションへの SSO を可能にする WAM フレームワーク上に構築された Microsoft Entra 固有のプラグイン。 |
| **Dsreg** | すべてのデバイス状態のデバイス登録プロセスを処理する、Windows 10 以降の Microsoft Entra 固有のコンポーネント。 |
| **トラステッド プラットフォーム モジュール (TPM)** | TPM は、ユーザーとデバイスのシークレットに対してハードウェア ベースのセキュリティ機能を提供するデバイスに組み込まれているハードウェア コンポーネントです。 詳細については、「[トラステッド プラットフォーム モジュール技術概要](https://learn.microsoft.com/ja-jp/windows/security/hardware-security/tpm/trusted-platform-module-overview)」の記事を参照してください。 |

### PRT は何に使用されますか?

- **シングル Sign-On (SSO)** - ユーザーがデバイスにサインインすると、PRT により、ユーザーが資格情報を再入力しなくても、Microsoft 365、Azure、およびその他のクラウド アプリにアクセスできるようになります。 Office、Microsoft Edge、Teams などのアプリは、ブローカーを介して PRT を使用してユーザーをサイレント認証し、ユーザー エクスペリエンスを向上させ、複数のサインインの必要性を減らし、生産性を向上させます。
- **トークンの取得** - PRT は、Windows Web アカウント マネージャー (WAM) または他のプラットフォーム上のブローカー プラグインを使用して、さまざまなサービス (Outlook、Teams、SharePoint など) のアクセス トークンと更新トークンを要求するために使用されます。
- **条件付きアクセス コンプライアンス** - デバイスとユーザーの要求を保持します。この要求は、条件付きアクセス ポリシーを適用するために Microsoft Entra ID によって評価されます (準拠しているデバイスや MFA などを要求する場合など)。

### PRT の種類は何ですか?

大まかに言えば、2 種類の PRT 成果物があります。

- **登録済みデバイス PRT は** 、関連付けられた Microsoft Entra ID を持つデバイスにバインドされます。
- **未登録のデバイス PRT は** 、Microsoft Entra ID を持たないデバイスにバインドされます。この ID は、クライアントによって生成されたデバイス上の暗号化キー ペアに関連付けられています。

クライアントは、可能な限り常に "登録済みデバイス PRT" の使用を試みます。 PRT がデバイス登録ポリシーを満たすのは、登録済みデバイスに対して発行されている場合のみです。 未登録のデバイス PRT は、ユーザーが個人用デバイスのブラウザーにサインインしたときや、デバイス登録をサポートしていないアプリにユーザーがサインインしたときなど、デバイスに Microsoft Entra ID がないシナリオで使用されます。

#### PRT の内容を見ることはできますか?

PRT は Microsoft Entra から送信される不透明な BLOB であり、その内容はどのクライアント コンポーネントにも知られることはありません。 PRT 内の内容は表示されません。

### PRT はどのようにして発行されますか?

**登録済みデバイス PRT の**場合、PRT は登録済みデバイス上のユーザーに発行されます。 デバイス登録の詳細については、「[Windows Hello for Business とデバイスの登録](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-registration-how-it-works)」の記事を参照してください。 デバイス登録中、dsreg コンポーネントによって暗号化キーの組が 2 セット生成されます。

- デバイス キー (dkpub/dkpriv)
- トランスポート キー (tkpub/tkpriv)

PRT は、Microsoft Entra ID ブローカーが存在する場合にのみ発行できます。 Broker は、macOS と Linux の Intune ポータル サイト、iOS 上の Authenticator、Authenticator、Windows へのリンク、Android 上のポータル サイトの各アプリで配布されるコンポーネントです。 Mac では、モバイル デバイス管理 (MDM) が SSO 拡張機能プロファイルと共にブローカーをアクティブ化するために必要です: [Apple SSO プラグイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin)

## [ウィンドウズ](#tab/windows-prt-issued)
デバイスに有効で機能する TPM/セキュア ハードウェア ストレージがある場合、秘密キーは、サポートされているプラットフォーム上のデバイスの Secure Storage にバインドされます。 公開キーは、PRT 要求中にデバイスの状態を検証するために、デバイス登録プロセス中に Microsoft Entra ID に送信されます。

PRT は Windows 10 以降のデバイス上でのユーザー認証中に発行され、2 つのシナリオがあります。

- **Microsoft Entra 参加済み** または **Microsoft Entra ハイブリッド参加済み**: ユーザーが組織の資格情報でサインインすると、Windows サインイン中に PRT が発行されます。 PRT は、パスワードや Windows Hello for Business など、Windows 10 以降でサポートされているすべての資格情報と共に発行されます。 このシナリオでは、Microsoft Entra CloudAP プラグインが PRT の主要な権限主体です
- **Microsoft Entra 登録済みデバイス**: ユーザーが Windows 10 以降のデバイスにセカンダリ職場アカウントを追加すると、PRT が発行されます。 ユーザーは、次の 2 つの方法で Windows 10 以降にアカウントを追加できます。
    - アプリ (Outlook など) にサインインした後で、 **[組織がデバイスを管理できるようにする]** プロンプトからアカウントを追加する
    - **[設定]**&gt;**[アカウント]**&gt;**[Access Work or School](https://learn.microsoft.com/ja-jp/entra/identity/devices/職場または学校にアクセスする)**&gt;**[接続]** からアカウントを追加する

Microsoft Entra の登録済みデバイスのシナリオでは、Windows へのサインインがこの Microsoft Entra アカウントを使用して行われないため、PRT の主たる認証主体は Microsoft Entra WAM プラグインです。

## [macOS](#tab/macos-prt-issued)
**プラットフォーム SSO を使用した macOS**

macOS [Platform Single Sign-on (PSSO)](https://learn.microsoft.com/ja-jp/entra/identity/devices/macos-psso) は、ユーザーが Microsoft Entra ID 資格情報を使用して Mac デバイスにサインインできるようにする、Microsoft の Enterprise SSO プラグインである macOS 用プラットフォーム資格情報を搭載した新機能です。

PSSO プライマリ更新トークン (PRT) は、プラットフォーム SSO 登録を正常に完了した Entra 参加済み macOS デバイスにのみ発行されます。 この登録プロセス中に、macOS はセキュリティで保護されたエンクレーブに基づく暗号化キー ペアを生成します。1 つはデバイス署名用、もう 1 つはデバイス暗号化用です。 公開キー参照は、SSO 拡張機能と共有されます。

SSO 拡張機能では、これらのキーを使用して、Microsoft Entra ID Device Registration Service を使用してデバイスの登録を完了します。 次に、PRT 取得に必要なパラメーターを使用して Apple の loginConfiguration API を構成します。

**プラットフォーム SSO なしの macOS**

macOS は、Microsoft Edge に未登録の PRT をサポートし、デバイスが職場環境に参加していてブローカーが存在する場合には登録済み PRT もサポートします。

登録が正常に完了すると、macOS はデバイス署名キーで署名されたサインイン要求を開始します。 Microsoft Entra ID は、要求、デバイス署名キー、および関連するパラメーターを検証し、PRT 応答を発行します。

## [iOS と Android](#tab/other-prt-issued)
**iOS、macOS、および Android では、** ブローカーが存在する場合、Microsoft Edge の未登録 PRT と登録済み PRT の両方がサポートされます。

## [リナックス](#tab/linux-prt-issued)
**Linux** では、ブローカーが存在する場合、Microsoft Edge の未登録 PRT と登録済み PRT の両方がサポートされます。

---

#### ブラウザーの動作

ブラウザーは、オペレーティング システムに応じて、複数の方法で PRT にアクセスできます。

## [ウィンドウズ](#tab/browser-behavior-windows)
Windows - 以下のブラウザーで、ブローカーからブラウザーへ PRT をプルします。

- Microsoft Edge
- Firefox
- クロム

## [アンドロイド](#tab/browser-behavior-android)
Android - Microsoft Edge はブローカーからブラウザーに PRT をプルします

## [iOS と macOS](#tab/browser-behavior-apple)
iOS および macOS では、SSO 拡張機能プロファイルのインストール時にブラウザーで PRT を取得することもできます。これにより、次の機能が有効になります。

- macOS での Chrome と Firefox のサポート
- iOS と macOS の両方での Safari サポート
- iOS および macOS の Microsoft Edge

## [リナックス](#tab/browser-behavior-linux)
Linux - Microsoft Edge はブローカーからブラウザーに PRT をプルします。

---

サポートされているブラウザーの一覧については、以下を参照してください。 [サポートされているブラウザー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions#supported-browsers)

注

Microsoft 以外の ID プロバイダーとの Entra フェデレーションを有効にしているお客様は、Windows 10 以降のデバイスで PRT 発行を有効にするために、WS-Trust プロトコルをサポートするようにこれらの ID プロバイダーを構成する必要があります。 フェデレーション ケースの WS-Trust がないと、Microsoft Entra ハイブリッド参加済みデバイスまたは Microsoft Entra 参加済みデバイスのユーザーに PRT を発行することはできません。

注

ADFS の場合、 `usernamemixed` エンドポイントが必要です。 Windows サインイン中に `Smartcard/certificate` を使用する場合は、 `certificatemixed` エンドポイントを ADFS で構成する必要があります。 `windowstransport` は、イントラネットに接続するエンドポイントとしてのみ有効にする必要があり、Web アプリケーション プロキシを介してエクストラネットに接続するエンドポイントとして **公開しないでください** 。

注

MICROSOFT Entra 条件付きアクセス ポリシーは、PRT の発行時には評価されません。

注

Microsoft Entra PRT の発行と更新については、Microsoft 以外の資格情報プロバイダーはサポートされていません。

### PRT はどのように使用されますか?

## [ウィンドウズ](#tab/windows-prt-used)
Windows で、PRT は 2 つの主要コンポーネントによって使用されます。

- **Microsoft Entra CloudAP プラグイン:**Windows サインイン時に、Microsoft Entra CloudAP プラグインは、ユーザーが入力した資格情報を使用して Microsoft Entra ID に PRT を要求します。 また、PRT をキャッシュして、ユーザーがインターネット接続にアクセスできないときのキャッシュ サインインを有効にします。
- **Microsoft Entra WAM プラグイン:** ユーザーがアプリケーションにアクセスしようとすると、Microsoft Entra WAM プラグインは PRT を使用して、Windows 10 以降で SSO を有効にします。 Microsoft Entra WAM プラグインは、トークン要求に WAM を使用するアプリケーションに対して、PRT を使用して更新トークンおよびアクセス トークンを要求します。 また、ブラウザーの要求に PRT を挿入して、ブラウザー上で SSO を有効にします。 Windows 10 以降のブラウザー SSO は、Microsoft Edge (ネイティブ)、Chrome ([Microsoft シングル サインオン](https://chrome.google.com/webstore/detail/windows-10-accounts/ppnbnpeolgkicgegkbkbjmhlideopiji?hl=en)拡張機能を使用)、Mozilla Firefox v91+ (Firefox [Windows SSO 設定](https://support.mozilla.org/kb/windows-sso)) でサポートされています。
    注

    ユーザーが同じ Microsoft Entra テナントの 2 つのアカウントでブラウザー アプリケーションにサインインしている場合、プライマリ アカウントの PRT が提供するデバイス認証は、2 つ目のアカウントにも自動的に適用されます。 その結果、2 番目のアカウントも、テナントのデバイスベースの条件付きアクセス ポリシーに適合します。

## [macOS](#tab/macos-prt-used)
PRT は、アプリや Web サイトに SSO を提供するために SSO [拡張機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin) によって使用されます。 PRT は、トークン要求の SSO 拡張機能に依存するアプリケーションの更新トークンとアクセス トークンを要求するために使用されます。 また、ブラウザーの要求に PRT を挿入して、ブラウザー上で SSO を有効にします。

ブラウザー SSO をサポートするブラウザーは、Safari、Firefox、Chrome、Microsoft Edge です。

## [iOS](#tab/ios-prt-used)
iOS の場合、Browser SSO では Microsoft Edge と Safari のみがサポートされています。

## [アンドロイド](#tab/android-prt-used)
Android の場合、Browser SSO では Microsoft Edge のみがサポートされています。

## [リナックス](#tab/linux-prt-used)
現在統合されているネイティブ アプリは、Intune と Microsoft Edge Browser のみです。 ブラウザーのサポートでは、デバイス バインド トークンと条件付きアクセスの適用に対して Microsoft Edge Browser のみが保護されます。

---

### PRT の有効期間はどれくらいですか?

発行後、PRT は 90 日間有効であり、ユーザーがデバイスをアクティブに使用している限り、継続的に更新されます。 組織では、サインイン [頻度セッション制御](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-session)を使用してリソースにアクセスするために、ユーザーの再認証を要求できます。

### PRT はどのように更新されますか?

## [ウィンドウズ](#tab/windows-prt-renewal)
##### Windows プラットフォーム

PRT は、次の 2 つの異なる方法で更新されます。

- **Microsoft Entra CloudAP プラグインによる 4 時間ごとの更新 :** CloudAP プラグインは、Windows サインイン中、4 時間ごとに PRT を更新します。 その時点でユーザーがインターネットに接続していない場合でも、デバイスがインターネットに接続されて新たに Windows にサインインすると、CloudAP プラグインが PRT を更新します。
- **アプリのトークン要求時の Microsoft Entra WAM プラグインによる更新**WAM プラグインは、Windows 10 以降のデバイスでアプリケーションに対するサイレント トークン要求を可能にすることで、SSO を実現します。 WAM プラグインは、これらのトークン要求中に 2 つの異なる方法で PRT を更新できます。
    - アプリが WAM に対してサイレントにアクセス トークンを要求するが、そのアプリ用の更新トークンが存在しない場合。 この場合、WAM は PRT を使用してアプリのトークンを要求し、応答で新しい PRT を再取得します。
    - アプリが WAM にアクセス トークンを要求するが、PRT が無効であるか、Microsoft Entra ID が追加の認証 (たとえば、Microsoft Entra の多要素認証) を要求している場合。 このシナリオでは、WAM は対話型サインインを開始し、ユーザーが再認証または追加の検証を行う必要があり、認証が成功すると新しい PRT が発行されます。

ADFS 環境では、ドメイン コントローラーに直接アクセスして PRT を更新する必要はありません。 PRT の更新には、WS-Trust プロトコルを使用して、プロキシ上で `/adfs/services/trust/2005/usernamemixed`/adfs/services/trust/2005/usernamemixed および `/adfs/services/trust/13/usernamemixed` エンドポイントのみが有効になっていれば十分です。

Windows トランスポート エンドポイントは、パスワードが変更された場合にのみパスワード認証に必要であり、PRT の更新には必要ありません。

##### 重要な考慮事項

- Microsoft Entra に参加済みおよび Microsoft Entra ハイブリッド参加済みのデバイスでは、CloudAP プラグインが PRT のプライマリ機関となります。 WAM ベースのトークン要求中に PRT が更新された場合、PRT は CloudAP プラグインに返送され、受け入れ前に Microsoft Entra ID での PRT の有効性が検証されます。

## [macOS](#tab/macos-prt-renewal)
macOS では、PSSO プライマリ更新トークン (PRT) が 4 時間ごとに更新されます。SSO トークンが見つからないか、有効期限が切れている場合は、より早く更新されます。

## [iOS](#tab/iOS-prt-renewal)
iOS では、iOS オペレーティング システムによるリソースの割り当てに応じて、デバイスの充電中に 2 日に 1 回以上発生しない日和見的な更新もあります。 他のプラットフォームと同様に、PRT は使用されている場合は 90 日間有効です。

## [リナックス](#tab/linux-prt-renewal)
PRT は 90 日間有効であり、ユーザーがデバイスをアクティブに使用している場合は 4 時間ごとに更新されます。

## [アンドロイド](#tab/android-prt-renewal)
- PRT は 90 日間有効であり、ユーザーがデバイスをアクティブに使用している限り、継続的に更新されます。
- PRT はネイティブ アプリの認証中にのみ発行および更新されます。 PRT はブラウザー セッション中は更新も発行もされません。
- デバイス登録 ([Workplace Join](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/walkthrough--workplace-join-to-an-android-device)) の必要なしに PRT を取得し、SSO を有効にすることもできます。
- デバイス登録なしで取得された PRT は、デバイスの状態やコンプライアンスに基づく条件付きアクセスの認証条件を満たすことができません。

---

注

MICROSOFT Entra 条件付きアクセス ポリシーは、PRT が更新されるときに評価されません。

### PRT はどのように保護されますか?

## [ウィンドウズ](#tab/windows-prt-protection)
PRT は、ユーザーがサインインしたデバイスにバインドすることで保護されます。この場合、使用可能でサポートされている場合はハードウェア バインドが使用されます。

Microsoft Entra ID と Windows 10 以降では、次の方法によって PRT 保護を有効にします。

- **初回サインイン中**: 初回サインイン中、暗号化形式でデバイス登録中に生成されたデバイス キーを使用して要求に署名することによって PRT が発行されます。 TPM が有効に動作しているデバイスでは、デバイス キーは TPM によってセキュリティで保護され、悪意のあるアクセスを防ぎます。 対応するデバイス キーの署名を検証できない場合、PRT は発行されません。
- **トークンの要求および更新中**: PRT が発行されると、Microsoft Entra ID は暗号化されたセッション キーもデバイスに発行します。 それは生成されたパブリック トランスポート キー (tkpub) を使用して暗号化され、デバイス登録の一環として Microsoft Entra ID に送信されます。 このセッション キーは、TPM によってセキュリティで保護されたプライベート トランスポート キー (tkpriv) によってのみ復号化できます。 セッション キーは、Microsoft Entra ID に送信されるすべての要求に対しての所有証明 (POP) キーです。 セッション キーも TPM によって保護され、他の OS コンポーネントからアクセスできません。 トークン要求または PRT 更新要求は、TPM を使用してこのセッション キーによって安全に署名されるため、改ざんできません。 Microsoft Entra では、対応するセッション キーによって署名されていないデバイスからの要求が無効になります。

これらのキーを TPM で保護することで、キーの窃取や PRT のリプレイを試みる悪意のある攻撃者から PRT のセキュリティを強化できます。 そのため、TPM を使用することで、Microsoft Entra 参加済みデバイス、Microsoft Entra hybrid 参加済みデバイス、および Microsoft Entra 登録デバイスの認証情報窃取に対するセキュリティが大幅に向上します。 パフォーマンスと信頼性の観点から、Windows 10 以降のすべての Microsoft Entra デバイス登録シナリオでは、TPM 2.0 の使用が推奨されています。 Windows 10、1903 の更新後、Microsoft Entra ID では、信頼性の問題のため、上記のキーに TPM 1.2 は使用されません。

## [macOS](#tab/macOS-prt-protection)
プライマリ更新トークン (PRT) は、ユーザーがサインインするデバイスに安全にバインドされます。 Microsoft Entra ID と macOS では、いくつかのメカニズムによってこの保護が適用されます。

PRT は、セキュリティで保護されたエンクレーブに基づくデバイス署名キーを使用して要求が署名された後にのみ発行されます。これは、デバイスの登録時に暗号化によって生成されます。 このキーの署名を検証できない場合、PRT は発行されません。

プラットフォーム SSO が使用されている macOS の場合、既定ではハードウェア バインディングが使用されます。

iOS と Mac でハードウェア バインドを有効にするには、[Apple SSO プラグイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin#upcoming-changes-to-device-identity-key-storage)のガイダンスに従ってください

## [その他のオペレーティング システム](#tab/other-prt-protection)
Android、iOS、Linux では、PRT のハードウェア バインドはサポートされていません。

iOS と Mac でハードウェア バインドを有効にするには、[Apple SSO プラグイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin#upcoming-changes-to-device-identity-key-storage)のガイダンスに従ってください

---

### アプリ トークンはどのように保護されますか?

トークンの一般的な保護方法の概要については、[Microsoft Entra ID でのトークンの保護に関するページを](https://learn.microsoft.com/ja-jp/entra/identity/devices/protecting-tokens-microsoft-entra-id)参照してください。

## [ウィンドウズ](#tab/windows-apptokens)
- アプリが WAM を介してトークンを要求すると、Microsoft Entra ID はアクセス トークンを発行し、一部の種類の要求では更新トークンを発行します。 ただし、WAM はアクセス トークンのみをアプリに返し、更新トークンをセキュリティで保護します。
    - SSO ユーザーの更新トークンである場合、この更新トークンは、セッション キー (PRT と同じ) またはデバイス キーを使用してデバイスにバインドされます。
    - SSO 以外のユーザーの更新トークンである場合、この更新トークンはデバイスにバインドされません。
- すべての更新トークンは DPAPI によって暗号化されます。

## [macOS と iOS](#tab/iOSMacOS-prt-apptokens)
- **iOS アンマネージド**: MDM SSO 拡張機能プロファイルがないデバイスでは、ブローカーはアクセス トークンと更新トークンの両方を呼び出し元アプリに返します。 呼び出し元のアプリは、後続の更新に更新トークンを使用し、その更新トークンは保護されません。
- **macOS/iOS マネージド**: MDM SSO 拡張機能プロファイルを持つデバイスでは、ブローカーは呼び出し元アプリにアクセス トークンのみを返します。 ブローカーは後続のトークン更新に PRT を使用し、保護されていない更新トークンは使用しません。 この構成は、追加の保護が提供されるため、アンマネージド構成よりも使用することをお勧めします。

## [アンドロイド](#tab/android-apptokens)
- ブローカーは、呼び出し元のアプリにのみアクセス トークンを返し、マネージド デバイスとアンマネージド デバイスの両方のアプリ更新トークンをローカルに格納します。

## [リナックス](#tab/linux-apptokens)
- Linux では、ブローカーは呼び出し元アプリにのみアクセス トークンを返し、マネージド デバイスとアンマネージド デバイスの両方の更新トークンをローカルに格納します。
- ローカルに格納された更新トークンは、UNIX ユーザーのサインイン キーリングに格納されている暗号化キーで暗号化されます。

---

### ブラウザーの Cookie の保護方法

## [ウィンドウズ](#tab/windows-browsercookies)
- Windows 10 以降では、Microsoft Entra ID は、ネイティブサポートまたは [拡張機能](https://chromewebstore.google.com/detail/microsoft-single-sign-on/ppnbnpeolgkicgegkbkbjmhlideopiji) を介して Google Chrome で、ブラウザー設定を介して Mozilla Firefox v91+ で、Microsoft Edge でブラウザー SSO をネイティブにサポートしています。
- ユーザーがブラウザーの対話を開始すると、ブラウザー (または拡張機能) によって [プラットフォーム API](https://learn.microsoft.com/ja-jp/windows/win32/api/proofofpossessioncookieinfo/nf-proofofpossessioncookieinfo-iproofofpossessioncookieinfomanager-getcookieinfoforuri) が呼び出されます。 拡張機能は、ネイティブ メッセージング ホストを介してこの API を呼び出します。 API は、ページが許可されているドメインの 1 つからのものであることを確認します。 ブラウザーは、nonce を含む完全なクエリ文字列を送信します。 プラットフォーム API は、TPM で保護されたキーで署名された PRT およびデバイス ヘッダーを作成します。 PRT ヘッダーはセッション キーによって署名され、デバイス ヘッダーはデバイス キーによって署名されるため、改ざんが困難です。 これらのヘッダーは、送信元のデバイスとユーザーを検証するための Microsoft Entra ID のすべての要求に含まれます。 Microsoft Entra ID は、これらのヘッダーを検証すると、ブラウザーにセッション Cookie を発行します。 このセッション Cookie には、要求の署名に使用したのと同じセッションまたはデバイス キーも含まれています。 後続の要求の間、セッション キーが検証されます。このとき、Cookie は事実上デバイスにバインドされ、他の場所からの再生を防ぎます。

## [iOS と Mac](#tab/iOS-browsercookies)
SSO 拡張機能プロファイルを使用する Safari と Microsoft Edge には、PRT セッション キーによって保護された Cookie が含まれます。 Microsoft Edge では、ユーザーが Microsoft Edge プロファイルにログインしている必要があります。 COOKIE は、SSO 拡張機能プロファイルなしでは保護されません。

## [アンドロイド](#tab/android-browsercookies)
対話型サインイン中に、ブラウザーの SSO Cookie が生成されます (Android アカウントのマネージド ストレージにある PRT とセッション キーを使用)。 Microsoft Edge ブラウザーにのみ適用され、ユーザーはサインインする必要があります。

## [リナックス](#tab/linux-browsercookies)
Microsoft Edge on Linux では、ブラウザーの SSO Cookie をブローカーに要求して、Microsoft Edge で SSO を有効にすることができます。 ブローカーは、生成された Cookie を Microsoft Edge に返すために、それぞれユーザーとデバイス ブローカーのコンテキストに格納されている PRT とセッション キーを使用して SSO Cookie を生成します。 Microsoft Edge では、ユーザーがプロファイルにログインしている必要があります。 理論的には、Linux プラットフォームにはアプリ ID がないため、Microsoft Edge 以外のアプリでもこの Cookie をブローカーに要求できます。 OS のセキュリティ境界は UNIX ユーザーの周囲にあり、同じユーザー コンテキスト内のアプリのみが、そのコンテキストでユーザーの SSO Cookie を取得できます。

---

### PRT が MFA 要求を受けるのはいつですか?

特定のシナリオで、PRT が多要素認証要求を受けることがあります。 アプリケーションのトークンを要求するために MFA ベースの PRT が使用されると、MFA 要求がアプリ トークンに転送されます。 この機能は、それが必要なすべてのアプリでの MFA チャレンジを防ぐことによって、シームレスなエクスペリエンスをユーザーに提供します。 PRT は次の方法で MFA 要求を取得できます。

## [ウィンドウズ](#tab/windows-mfa)
- **Windows Hello for Business を使用してサインイン**: Windows Hello for Business は、パスワードを置き換え、暗号化キーを使用して強力な 2 要素認証を提供します。 Windows Hello for Business はデバイス上のユーザーに固有であり、それ自体がプロビジョニングのために MFA を必要とします。 ユーザーが Windows Hello for Business を使用してログインすると、ユーザーの PRT が MFA 要求を取得します。 このシナリオは、スマート カード認証が ADFS から MFA 要求を生成する場合に、スマート カードを使用してログインするユーザーにも適用されます。
    - Windows Hello for Business は多要素認証と見なされ、PRT 自体が更新されると MFA 要求が更新されるため、ユーザーが Windows Hello for Business を使用してサインインすると MFA の期間は継続的に延長されます。
- **WAM 対話型サインイン中の MFA:**WAM を通じたトークン要求中に、ユーザーがアプリにアクセスするために MFA を実行する必要がある場合、この対話中に更新される PRT には MFA 要求が刻印されます。
    - この場合、MFA 要求は継続的に更新されないため、MFA 期間はディレクトリで設定された有効期間に基づきます。
    - 以前の既存の PRT と RT がアプリへのアクセスに使用されている場合、PRT と RT は最初の認証の証明と見なされます。 2 番目の証明と刻印された MFA 要求で、新しい RT が必要になります。 このプロセスにより、新しい PRT と RT も発行されます。
- Windows 10 以降では、PRT のパーティション分割されたリストを資格情報ごとに保持します。 したがって、Windows Hello for Business、パスワード、またはスマート カードのそれぞれに PRT があります。 このパーティション分割により、使用する資格情報に基づいて MFA 要求が分離され、トークン要求中に混同されないことが保証されます。

注

パスワードを使用して Windows 10 以降の Microsoft Entra 参加済みデバイスまたは Microsoft Entra ハイブリッド参加済みデバイスにサインインする場合、ユーザーがそのセッション内で 2FA プロセスに合格したかどうかに応じて、PRT に関連付けられているセッション キーがロールされた後に、WAM 対話型サインイン中の MFA が必要になることがあります。

## [iOS/Mac/Android/Linux](#tab/other-mfa)
CA ポリシーが管理者によって設定されている場合、ユーザーは MFA を実行する必要があります。 このような場合、PRT はアップグレードされ、MFA 要求が取得されます。 要求の期間は、ディレクトリに設定された有効期間に基づいています。

---

### PRT はどのようにして無効になりますか?

次のシナリオでは、PRT が無効になります。

- **無効なユーザー:** ユーザーが Microsoft Entra ID で削除または無効化された場合、そのユーザーの PRT は無効になり、アプリケーションのトークンを取得するために使用できません。 削除または無効化されたユーザーがデバイスに既にサインインしていた場合、CloudAP が無効状態を認識するまで、そのユーザーはキャッシュ サインインによってログイン可能です。 CloudAP は、ユーザーが無効であると判断すると、それ以降のログオンをブロックします。 無効なユーザーは、資格情報がキャッシュされていない新しいデバイスへのサインインを自動的にブロックされます。
- **無効なデバイス**: Microsoft Entra ID でデバイスが削除または無効化された場合、そのデバイスで取得された PRT は無効になり、他のアプリケーションのトークンを取得するために使用できません。 無効なデバイスにユーザーが既にサインインしている場合、その状態を継続できます。 ただし、デバイス上のすべてのトークンは無効になり、ユーザーはそのデバイスのリソースに対する SSO を失います。
- **パスワードの変更**: ユーザーがパスワードを使用して PRT を取得した場合、ユーザーがパスワードを変更すると、Microsoft Entra ID によって PRT が無効になります。 パスワードを変更すると、ユーザーは新しい PRT を取得することになります。 この無効化は、次の 2 とおりの方法で発生します。
    - ユーザーが新しいパスワードで Windows にサインインした場合、CloudAP は古い PRT を破棄し、新しいパスワードに対する PRT を Microsoft Entra ID に要求します。 ユーザーがインターネットに接続していない場合、新しいパスワードを検証できないため、Windows は古いパスワードの入力をユーザーに要求することがあります。
    - ユーザーが古いパスワードを使用してログインしていたか、または Windows へのサインイン後にパスワードを変更した場合、WAM ベースのトークン要求には古い PRT が使用されます。 このシナリオでは、ユーザーは WAM トークンの要求中に再認証を求められ、新しい PRT が発行されます。
- **TPM に関する問題:** デバイスの TPM の不具合または故障により、TPM で保護されているキーにアクセスできなくなることがあります。 この場合、暗号キーの所有を証明できないため、デバイスは PRT を取得できず、既存の PRT を使用してトークンを要求することもできません。 その結果、既存の PRT はすべて Microsoft Entra ID. によって無効化されます。 Windows 10 が障害を検出すると、新しい暗号化キーを使用してデバイスを再登録するための復旧フローが開始します。 Microsoft Entra ハイブリッド 参加済みの場合は、初回登録時と同様、ユーザーによる入力なしでサイレントに回復が行われます。 Microsoft Entra 参加済みまたは Microsoft Entra 登録済みデバイスの場合、そのデバイスに対する管理者特権を持つユーザーが復旧を実行する必要があります。 このシナリオでは、復旧フローは、デバイスを正常に復旧できるようユーザーをガイドする Windows プロンプトによって開始されます。

### 詳細なフロー

以下の図は、アプリケーションのアクセス トークンを要求するために PRT を発行、更新、および使用する際の基本的な詳細を示しています。 さらに、これらの手順では、前述のセキュリティ メカニズムがこれらの操作中にどのように適用されるかについても説明します。

Windows オペレーティング システムに固有の詳細なフローを次に示します。

#### 初回サインイン時の PRT 発行 (Windows)

[Image: 初回サインイン中の PRT 発行の詳細フロー]

注

Microsoft Entra 参加済みデバイス内では、ユーザーが Windows にサインインする前に、Microsoft Entra PRT の発行 (手順 A から F) が同期的に行われます。 Microsoft Entra ハイブリッド参加済みデバイスでは、オンプレミスの Active Directory がプライマリ機関となります。 そのため、ユーザーはサインインに必要な TGT を取得できれば Microsoft Entra ハイブリッド参加済みの Windows にサインインできます。一方、PRT の発行は非同期で行われます。 サインインでは Microsoft Entra 資格情報が使用されないため、このシナリオは Microsoft Entra 登録済みデバイスには適用されません。

注

Microsoft Entra ハイブリッド参加済みの Windows 環境では、PRT が非同期で発行されます。 フェデレーション プロバイダーに問題がある場合、PRT の発行に失敗することがあります。 このエラーにより、ユーザーがクラウド リソースにアクセスしようとすると、サインオンの問題が発生する可能性があります。 フェデレーション プロバイダーでこのシナリオのトラブルシューティングを行う必要があります。

| ステップ | 説明 |
| --- | --- |
| A | ユーザーがサインイン UI で自分のパスワードを入力します。 LogonUI は認証バッファー内の認証情報を LSA に渡し、LSA はそれを内部で CloudAP に渡します。 CloudAP はこの要求を CloudAP プラグインに転送します。 |
| B | CloudAP プラグインは、ユーザーの ID プロバイダーを識別するためにレルム検出要求を開始します。 ユーザーのテナントにフェデレーション プロバイダーのセットアップがある場合、Microsoft Entra ID はフェデレーション プロバイダーの Metadata Exchange (MEX) エンドポイントを返します。 そうでない場合、Microsoft Entra ID はユーザーが管理対象であることを返し、ユーザーを Microsoft Entra ID で認証できることを示します。 |
| C | ユーザーがマネージド ユーザーの場合、CloudAP は Microsoft Entra ID からノンスを取得します。 ユーザーがフェデレーション ユーザーの場合、CloudAP プラグインはユーザーの認証情報を使用して、フェデレーション プロバイダーから SAML (Security Assertion Markup Language) トークンを要求します。 ノンスは、SAML トークンが Microsoft Entra ID に送信される前に要求されます。 |
| D | CloudAP プラグインは、ユーザーの資格情報、ノンス、ブローカー スコープを含む認証要求を構築し、デバイス キー (dkpriv) を使用してその要求に署名したうえで、Microsoft Entra ID に送信します。 フェデレーション環境では、CloudAP プラグインは、ユーザーの資格情報ではなく、フェデレーション プロバイダーによって返される SAML トークンを使用します。 |
| E | Microsoft Entra ID は、ユーザーの資格情報、nonce、およびデバイスの署名を検証し、デバイスがテナント内で有効であることを確認し、暗号化された PRT を発行します。 Microsoft Entra ID は、PRT と共に、セッション キーと呼ばれる対称キーも発行します。これは、トランスポート キー (tkpub) を使用して Microsoft Entra ID によって暗号化されます。 さらに、セッション キーも PRT に埋め込まれます。 このセッション キーは、PRT を使用した後続の要求に対する所有証明 (PoP) キーとして機能します。 |
| F | CloudAP プラグインは、暗号化された PRT とセッション キーを CloudAP に渡します。 トランスポート キー (tkpriv) を使用してセッション キーを復号化し、TPM 自身のキーを使用してそれを再暗号化するよう、CloudAP が TPM に要求します。 CloudAP は、暗号化されたセッション キーをそのキャッシュに PRT と共に保存します。 |

#### 後続のログオンでの PRT 更新 (Windows)

[Image: 後続のログオンでの PRT 更新]

| ステップ | 説明 |
| --- | --- |
| A | ユーザーがサインイン UI で自分のパスワードを入力します。 LogonUI は認証バッファー内の認証情報を LSA に渡し、LSA はそれを内部で CloudAP に渡します。 CloudAP はこの要求を CloudAP プラグインに転送します。 |
| B | ユーザーが以前にこのセッションにサインインしたことがある場合、Windows はキャッシュ サインインを開始し、資格情報を検証してユーザーをログインさせます。 4 時間ごとに、CloudAP プラグインは PRT の更新を非同期的に開始します。 |
| C | CloudAP プラグインは、ユーザーの ID プロバイダーを識別するためにレルム検出要求を開始します。 ユーザーのテナントにフェデレーション プロバイダーのセットアップがある場合、Microsoft Entra ID はフェデレーション プロバイダーの MEX (Metadata Exchange) エンドポイントを返します。 そうでない場合、Microsoft Entra ID はユーザーが管理対象であることを返し、ユーザーを Microsoft Entra ID で認証できることを示します。 |
| D | ユーザーがフェデレーションされている場合、CloudAP プラグインはユーザーの認証情報を使用して SAML トークンをフェデレーション プロバイダーから要求します。 ノンスは、SAML トークンが Microsoft Entra ID に送信される前に要求されます。 ユーザーが管理対象の場合、CloudAP は直接、Microsoft Entra ID からノンスを取得します。 |
| E | CloudAP プラグインは、ユーザーの資格情報、ノンス、既存の PRT を使用して認証要求を構築し、セッション キーを使用して要求に署名し、それを Microsoft Entra ID に送信します。 フェデレーション環境では、CloudAP プラグインは、ユーザーの資格情報ではなく、フェデレーション プロバイダーによって返される SAML トークンを使用します。 |
| F | Microsoft Entra ID は、PRT に埋め込まれたセッション キーと比較することによってセッション キーの署名を検証し、ノンスを検証し、デバイスがテナント内で有効であることを確認したうえで、新しい PRT を発行します。 前述のように、PRT はやはり、トランスポート キー (tkpub) によって暗号化されたセッション キーを伴います。 |
| G | CloudAP プラグインは、暗号化された PRT とセッション キーを CloudAP に渡します。 CloudAP は TPM に要求して、トランスポート キー (tkpriv) を使用してセッション キーを復号化し、TPM 自身のキーを使用してそれを再暗号化します。 CloudAP は、暗号化されたセッション キーをそのキャッシュに PRT と共に保存します。 |

注

 エンドポイントが外部で有効になっている場合、VPN 接続を必要とせずに PRT を外部で更新できます。

#### アプリ トークン要求中の PRT の使用 (Windows)

[Image: アプリ トークン要求中の PRT 使用状況]

| ステップ | 説明 |
| --- | --- |
| A | Microsoft Outlook などのアプリケーションは、WAM へのトークン要求を開始します。 WAM はさらに、トークン要求の処理を Microsoft Entra WAM プラグインに依頼します。 |
| B | アプリケーションの更新トークンが既に利用可能な場合、Microsoft Entra WAM プラグインはそれを使用してアクセス トークンを要求します。 デバイス バインディングの証明を提供するために、WAM プラグインはセッション キーを使用して要求に署名します。 Microsoft Entra ID はセッション キーを検証し、アプリのアクセス トークンと新しい更新トークンを、セッション キーによって暗号化して発行します。 WAM プラグインはトークンの復号化を CloudAP プラグインに要求し、次に CloudAP プラグインがセッション キーを使用した復号化を TPM に要求します。その結果、WAM プラグインは両方のトークンを取得します。 次に、WAM プラグインはアクセス トークンのみをアプリケーションに提供する一方で、更新トークンを DPAPI で再暗号化してそれ自身のキャッシュに保存します |
| C | アプリケーションの更新トークンを使用できない場合、Microsoft Entra WAM プラグインは PRT を使用してアクセス トークンを要求します。 所有の証明を提供するために、WAM プラグインはセッション キーを使用して、PRT が含まれている要求に署名します。 Microsoft Entra ID は、PRT に埋め込まれたセッション キーと比較することによってセッション キーの署名を検証し、デバイスが有効であることを確認し、アプリケーションのアクセス トークンと更新トークンを発行します。 さらに、Microsoft Entra ID は、セッション キーによって暗号化された新しい PRT (更新サイクルに基づく) を発行できます。 |
| D | WAM プラグインはトークンの復号化を CloudAP プラグインに要求し、次に CloudAP プラグインがセッション キーを使用した復号化を TPM に要求します。その結果、WAM プラグインは両方のトークンを取得します。 次に、WAM プラグインはアクセス トークンのみをアプリケーションに提供する一方で、更新トークンを DPAPI で再暗号化してそれ自身のキャッシュに保存します。 WAM プラグインはこのアプリケーションに対して、今後は更新トークンを使用します。 WAM プラグインはさらに、新しい PRT を CloudAP プラグインに返します。CloudAP プラグインは、自身のキャッシュ内でそれを更新する前に、Microsoft Entra ID で PRT を検証します。 これ以降、CloudAP プラグインは新しい PRT を使用します。 |
| E | WAM は、呼び出し元アプリケーションに新しく発行されたアクセス トークンを提供します。 |

#### PRT を使用したブラウザー SSO (Windows)

[Image: PRT を使用したブラウザー SSO]

| ステップ | 説明 |
| --- | --- |
| A | ユーザーは自分の資格情報を使用して Windows にログインし、PRT を取得します。 ユーザーがブラウザーを開くと、ブラウザー (または拡張機能) がレジストリから URL を読み込みます。 |
| B | ユーザーが Microsoft Entra サインイン URL を開くと、ブラウザーまたは拡張機能によって、レジストリから取得した URL が検証されます。 一致する場合、ブラウザーはトークンを取得するためにネイティブ クライアント ホストを呼び出します。 |
| C | ネイティブ クライアント ホストは、URL が Microsoft ID プロバイダー (Microsoft アカウントまたは Microsoft Entra ID) に属していることを検証し、URL から送信された nonce を抽出し、CloudAP プラグインの呼び出しを実行して PRT Cookie を取得します。 |
| D | CloudAP プラグインは、PRT Cookie を作成し、TPM バインド セッション キーを使用して署名し、ネイティブ クライアント ホストに送信します。 |
| E | ネイティブ クライアント ホストは、この PRT Cookie をブラウザーに返します。この Cookie は、x-ms-RefreshTokenCredential と呼ばれる要求ヘッダーの一部として含まれ、Microsoft Entra ID からトークンを要求します。 |
| F | Microsoft Entra ID は PRT Cookie のセッション キー署名を検証し、nonce を検証し、デバイスがテナント内で有効であることを確認し、Web ページの ID トークンと、ブラウザーの暗号化されたセッション Cookie を発行します。 |

注

前の手順で説明されているブラウザー SSO フローは、Microsoft Edge の InPrivate、Google Chrome のシークレット モード (Microsoft Accounts 拡張機能の使用時)、Mozilla Firefox v 91 以上のプライベート モードなどのプライベート モードのセッションには該当しません
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/concept-soft-delete-devices"} -->
## Microsoft Entra IDでのデバイスの論理的な削除 (プレビュー) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-soft-delete-devices
- Service: entra-id / devices
- Article date: 2026-04-05
- Summary: Microsoft Entra IDでのデバイスの論理的な削除 (プレビュー) について説明します。削除されたデバイスは完全に削除されるのではなく、回復可能な状態になります。

デバイスの論理的な削除は、削除されたデバイス オブジェクトを完全に削除するのではなく、中断状態に移行するMicrosoft Entra IDの回復機能です。 デバイスが論理的に削除されると、Azure Device Registration Service (ADRS) はデバイスの登録を解除し、デバイス オブジェクトをディレクトリ内の別の論理的に削除されたコンテナーに移動します。 デバイスはアクティブなデバイス リストから削除されますが、最大 30 日間回復可能なままになります。

この機能は、BitLocker 回復キーやローカル管理者パスワード ソリューション (LAPS) パスワードなど、重要なデバイス データが誤って失われるのを防ぐのに役立ちます。 また、孤立したデバイス オブジェクトが原因でテナントのオブジェクト クォータに達するリスクを軽減し、ユーザーやグループでのソフト削除と同様に、デバイスの削除を元に戻すオプションも提供します。

Important

デバイスの論理的な削除は現在プレビュー段階です。 一般提供の前に、一部の機能と動作が変更される場合があります。

### デバイスの論理的な削除のしくみ

管理者またはデバイス所有者がMicrosoft Entra IDからデバイスを削除しても、デバイス オブジェクトは完全には削除されません。 代わりに、ADRS は、デバイスの認証更新トークンを無効にする登録解除シーケンスを開始し、デバイス オブジェクトを論理的に削除されたコンテナーに移動します。 デバイスは、ソフト削除状態でも一意の識別子と鍵マテリアルを保持します。

デバイスがソフト削除状態の場合:

- デバイスは、Microsoft Entra IDによって保護されたクラウド リソースを認証またはアクセスできません。
- デバイス オブジェクトは、管理ツールでは変更または更新できません。
- デバイスは、Azure ポータルのデバイスリスト、Intune、およびMicrosoft Graphクエリから非表示になります。 デバイスのクエリでは、HTTP 404 Not Found エラーが返されます。
- デバイスの DeviceId は予約されたままになっています。 論理的に削除されたデバイスが復元されるか完全に削除されるまで、新しいデバイスは同じ DeviceId では登録できません。
- 論理的に削除されたデバイスは引き続きディレクトリ オブジェクト クォータにカウントされますが、tombstone オブジェクトはアクティブ オブジェクトの 4 分の 1 としてカウントされます。

ソフト削除状態で 30 日間経過すると、デバイスは自動的にハード削除されます（完全に削除されます）。

### サポートされているデバイスの種類

プレビュー期間中、デバイスのソフト削除は、次のデバイスの種類でサポートされています。

- **Microsoft Entra参加済みデバイス** — Microsoft Entra IDに直接参加しているエンタープライズマネージド デバイス。
- **Microsoft Entraハイブリッド参加済みデバイス** — オンプレミスの Active Directory ドメインに参加し、Microsoft Entra IDに登録されたエンタープライズマネージド デバイス。
- **Microsoft Entra登録済みデバイス** — 職場または学校アカウントに登録されている個人または BYOD デバイス。

次のデバイスの種類は現在、論理的な削除ではサポートされておらず、削除されるとすぐにハード削除されます。

- 信頼の種類が認識されていないデバイス (Microsoft Graph API 経由で直接作成されたデバイスなど)
- マネージド ID を持つセキュリティで保護された VM、非永続的 VDI インスタンス、プリンターなど、特定の特殊なデバイスの種類

### 役割の要件

特定のロールのみが、デバイスを開始、復元、または完全に削除できます。

- **クラウド デバイス管理者**、 **Intune 管理者**、 **グローバル管理者** は、任意のデバイスを論理的に削除し、論理的に削除されたデバイスを復元し、論理的に削除されたデバイスを完全に削除できます。
- **デバイス所有者 (デバイス** に参加または登録したユーザー) は、自分のデバイスを論理的に削除できますが、復元または完全に削除することはできません。

ソフト削除または復元のアクセス許可が付与されたカスタム ロールは、現在は利用できません。

### 論理的な削除中に保持されるデータ

デバイスが論理的に削除されると、デバイス オブジェクトに関連付けられている重要なデータは、論理的に削除されたコンテナーに保持されます。

- **BitLocker 回復キー** - デバイスに格納されている回復キーは、管理者が引き続きアクセスできます。 復元後も、登録済みの所有者によるセルフサービス BitLocker キーの回復を含め、キーは引き続き使用できます。
- **LAPS パスワード— LAPS** によって管理されるローカル管理者パスワードは保持されます。
- **デバイス ID とキー マテリアル** - デバイスの一意の識別子とキー マテリアルが維持され、デバイス オブジェクトの完全な復元が可能になります。

デバイスが復元されると、デバイス オブジェクトは、これらのプロパティをそのまま使用してアクティブなコンテナーに戻ります。

### コンプライアンスのリセット

デバイスが論理的に削除されると、Microsoft Entra IDは、復元された場合にデバイスから予期しない値が返されないように、コンプライアンス関連のプロパティをいくつかリセットします。 Specifically:

- **IsCompliant** が False に設定 **されています**。
- その他のコンプライアンス関連フラグは null または false に設定されます。

Intune などの管理機関を識別する MDM アプリケーション ID は保持され、論理的な削除中はクリアされません。 デバイスは、復元後も管理機関に関連付けられたままになります。

デバイスが復元された後、デバイスが管理機関でチェックインし、新しいコンプライアンス評価が完了するまで、IsCompliant 値は False のままです。 この動作は想定されており、通常、デバイスが Intune または別の MDM プロバイダーと同期した後に解決されます。

### 論理的に削除されたデバイスを復元する

プレビュー期間中、論理的に削除されたデバイスは、Microsoft Graph API または PowerShell を使用して復元できます。 論理的に削除されたデバイスを表示および復元するためのポータル エクスペリエンスは、一般提供 (GA) のために計画されています。

デバイスが論理的に削除されているかどうかを確認するために、管理者は次を使用できます。

- **Microsoft Graph API** — 削除済みアイテム エンドポイント `GET https://graph.microsoft.com/beta/directory/deletedItems/microsoft.graph.device` にクエリを実行して、論理的に削除されたすべてのデバイスを一覧表示します。
- **PowerShell** — Microsoft Graph PowerShell モジュールを使用して、論理的に削除されたデバイス オブジェクトを一覧表示します。

デバイスが復元されたとき:

- デバイス オブジェクトは、論理的に削除されたコンテナーから Active Directory コンテナーに戻ります。
- デバイスは認証を行い、再度管理できます。 ユーザーがセッションを更新するには、もう一度サインインするか、デバイスを再起動する必要がある場合があります。
- コンプライアンス関連のプロパティは、デバイスが管理機関でチェックインするまでリセット状態のままになります。

Important

ソフト削除されたデバイスを復元できるのは、クラウド デバイス管理者、Intune 管理者、およびグローバル管理者のみです。

### 完全な削除

ハード削除では、デバイス オブジェクトがMicrosoft Entra IDから完全に削除されます。 ハード削除は、次の状況で発生します。

- 論理的に削除されたデバイスは、30 日以内に復元されません。
- 管理者は、ソフト削除されたデバイスに対して、明示的に完全削除を実行します。
- デバイスの種類では、論理的な削除はサポートされていません。

デバイスがハード削除されると、BitLocker 回復キーや LAPS パスワードを含むすべての関連データが完全に失われ、回復できません。 デバイス オブジェクトは完全に再作成する必要があります。

Caution

ハード削除されたデバイスとそれに関連するデータは回復できません。 デバイスを完全に削除する前に、デバイスの BitLocker 回復キーやその他のデータが不要であることを確認します。

### Microsoft Entra Connect とソフト削除

Microsoft Entra Connect がオンプレミスの Active Directory と Microsoft Entra ID の間でデバイスを同期するハイブリッド環境では、ソフト削除は同期処理と関連して動作します。 デバイスが誤って同期スコープから削除された場合 (たとえば、同期された組織単位からコンピューター オブジェクトを移動するなど)、Microsoft Entra Connect は次の同期サイクル中に論理的に削除されたオブジェクトを検出し、重複を作成する代わりに復元できます。

この自動復元動作は、同期スコープの変更による誤った大量削除による資格情報の損失を防ぐのに役立ちます。 Microsoft Entra Connect が論理的に削除されたデバイスの再作成を試みると (同じ DeviceId で一致する)、システムは論理的に削除されたオブジェクトを認識して復元します。

### 制限事項

- プレビュー期間中は、論理的に削除されたデバイスを表示または復元するためのポータル エクスペリエンスはありません。 復元するには、PowerShell または Microsoft Graph APIが必要です。
- デバイスの論理的な削除または復元操作のカスタム RBAC ロールはサポートされていません。
- DeviceId の一意性は、アクティブなコンテナーと論理的に削除されたコンテナーの両方に適用されます。 新しいデバイスは、そのデバイスが復元されるか完全に削除されるまで、ソフト削除されたデバイスと同じ DeviceId では登録できません。
- 論理的な削除を認識しない古いAzure AD Graph API では、デバイスを論理的に削除するのではなく、ハード削除する可能性があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/concept-tokens-microsoft-entra-id"} -->
## Microsoft Entra ID のトークンについて - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-tokens-microsoft-entra-id
- Service: entra-id / devices
- Article date: 2025-05-01
- Summary: トークンの盗難やリプレイ攻撃から保護するには、Microsoft Entra で使用されるトークンの種類、認証におけるその役割、および戦略について説明します。

攻撃者はますます高度な攻撃を使用するため、トークンの盗難やトークンリプレイから環境を強化することで、データ流出から保護することが重要です。 困難ですが、攻撃対象領域を減らし、攻撃者がトークンを正常に盗んで再生するためのコストを増やすために、簡単な手順を実行できます。 トークンを保護するための堅牢な戦略には、多層防御の詳細なアプローチが必要です。これには、次のものが含まれている必要があります。

- フィッシング詐欺に強い資格情報の展開
- マルウェアベースの攻撃に対するデバイスの強化
- デバイス ベースとリスクベースの条件付きアクセスの使用
- 可能な場合はデバイス バインド トークンを適用する
- ネットワーク ベースの強制の実装

このドキュメントでは、トークンの概要、トークンの盗み方、環境内での攻撃の成功リスクを軽減するための具体的な手順について説明します。 Microsoft Entra の複雑さと多種多様なトークンにより、一部のトピックは簡略化のために一般化されており、すべてのエッジ ケースを網羅しているわけではありません。 ただし、このガイダンスでは、パブリック クライアントのほとんどのシナリオについて説明します。 [機密クライアント](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-client-applications#public-client-and-confidential-client-authorization) のシナリオは、スコープ内にありません。

パスワードベースの攻撃は、Microsoft によって見られる 99% を超える攻撃で引き続き構成されており、ほとんどの侵害された ID の根本原因です。 組織は、フィッシングに対する耐性のある MFA を ID の防御の最前線としてデプロイする必要があります。 そうすることで、敵対者は戦術を調整し、次の論理的な攻撃ベクトルに移動します。これはトークン盗難の可能性があります。 「トークンの盗難の結果、パスワード攻撃よりもはるかに少ない ID 侵害が発生しますが、Microsoft の検出は、インシデントが 1 日あたり推定 39,000 件にまで増加したことを示しています。 さらに、昨年、AiTM フィッシング攻撃が 146% 増加しました。これは、攻撃者がユーザーをだましてリンクをクリックし、攻撃者に代わって MFA を完了した場合に発生します。"\* フィッシングに強い MFA の展開を最優先事項にする必要がある一方で、トークン盗難攻撃ベクトルが時間の経過と同時に増加し続けているので、組織もトークン盗難軽減戦略の準備を開始する必要があります。 パスワードベースの攻撃の実行可能性が低くなるにつれて、トークンの盗難から保護することがより重要になります。

*\* [2024 年の Microsoft Digital Defense レポート](https://aka.ms/mddr) (40 ページ) から*

### トークンとは

トークンは、リソースへのアクセスを許可するために、さまざまな認証および承認プロセスで使用されるデジタル オブジェクトです。 ユーザーまたはワークロードの ID を確認し、各トランザクションのパスワードまたは資格情報の送信を必要とせずにリソースへのアクセスを許可します。 トークンは、ユーザーの ID とそのアクセス許可に関する情報をセキュリティで保護された形式でカプセル化し、認証プロセス中も機密情報を確実に保護します。

デジタル環境では、セキュリティで保護された効率的な認証メカニズムを有効にすることで、トークンがセキュリティを強化する上で重要な役割を果たします。 これらは、ネットワーク経由での資格情報の公開を最小限に抑えることで、資格情報の盗難のリスクを軽減するのに役立ちます。 ただし、デバイスまたはネットワークが侵害された場合、攻撃者によって流出される可能性があるという特性があります。 攻撃者はこれらのトークンを使用して、サインインしているユーザーとしてリソースにアクセスできます。

### トークンの種類の概要

トークンにはさまざまな種類がありますが、一般的には次の 2 つのカテゴリのいずれかに分類されます。

- **サインイン セッション** – これらのトークンは、ユーザーのサインイン状態を維持し、ユーザーが頻繁に再認証を必要とせずにリソースにアクセスできるようにします。 これらは ID プロバイダーに渡され、アプリ セッション カテゴリ内のトークンを要求します。 これらは、OAuth 2.0 標準の更新トークンとも呼ばれます。
- **アプリ セッション** – これらのトークンは、特定のアプリケーションへのアクセスを承認します。 これらは有効期間が短く、クライアントとアプリケーションの間で再生されます。 OAuth 2.0 標準では、アクセス トークンとも呼ばれます。

トークンは、クライアント アプリケーションによっても異なる場合があります。 ブラウザー経由でアクセスされる Web アプリケーションでは、Outlook や Teams などのネイティブ アプリと比較して、さまざまな種類のトークンが使用される場合があります。

[Image: Microsoft Entra ID およびその他のアプリケーションとサービスによって評価されたトークンのスクリーンショット。]

ベスト プラクティスとして、最初にサインイン セッション トークンの保護に優先順位を付けます。これらのトークンは数週間または数か月間続く可能性があります。これにより、盗難された場合に永続的な未承認アクセスが有効にされる可能性があります。

2 つのトークン ファミリのもう 1 つの違いは、サインイン セッション トークンは設計によって取り消されますが、アプリ セッションは通常は取り消されません。 たとえば、Entra ID アクセス トークンは、アプリケーションに継続的アクセス評価が統合されている場合にのみ取り消すことができます。

| トークンの種類 | 発行者 | 目的 | リソースに限定されたスコープ | 有効期間 | 取消可能な | 再生可能 |
| --- | --- | --- | --- | --- | --- | --- |
| プライマリ更新トークン (PRT) | 保守する | アクセス トークンを要求する | いいえ – 任意のリソースのアクセス トークンを要求できます | 90 日\* | イエス | イエス |
| 更新トークン | 保守する | アクセス トークンを要求する | イエス | 90 日\* | イエス | イエス |
| アクセストークン | 保守する | リソースにアクセスする | イエス | 変数 60 ~ 90 分 | はい (CAE 対応の場合) | いいえ |
| アプリ認証 Cookie | Web アプリ | リソースにアクセスする | イエス | アプリケーションによって決定されます | アプリケーションに依存 | いいえ |

\*ローリング ウィンドウ – トークンを使用するたびに有効期間が再起動されます。

### トークン盗難攻撃ベクトル

敵対者は、トークンを盗むためにさまざまな攻撃ベクトルを使用できます。 トークンが盗まれたら、敵対者はユーザーを偽装し、不正アクセスを取得したり、機密データを流出させることもできます。 これらの攻撃ベクトルの例を次に示します。

- **中間攻撃者**: 中間者 (MitM) 攻撃の高度な形式。 このシナリオでは、攻撃者は 2 つの通信相手の間に自分自身を配置し、いずれかの当事者の知識がなくても通信を傍受し、変更する可能性があります。 このシナリオでは、攻撃者は資格情報、セッション Cookie、その他のデータなどの機密情報をキャプチャでき、多要素認証などのセキュリティ対策をバイパスすることもできます。 [アドバーサリー・イン・ザ・ミドル・フィッシング攻撃](https://techcommunity.microsoft.com/blog/microsoft-entra-blog/defeating-adversary-in-the-middle-phishing-attacks/1751777)について詳しくはこちらをご覧ください。
- **マルウェア**: マルウェアは、システムに侵入し、ネットワーク トラフィックを監視したり、格納されているデータにアクセスしたりして、デバイスからトークンを盗むことができます。 インストールが完了すると、マルウェアはデバイスと正当なサービスの間の通信を傍受することで、認証トークン、セッション Cookie、またはその他の資格情報をキャプチャできます。 また、脆弱性を悪用して、メモリまたはストレージからトークンを直接抽出することもできます。

この記事では、エンド ユーザーに向けられた攻撃 (前述のものなど) を倒す方法について主に説明します。 サーバー側やアプリケーションの侵害などの攻撃ベクトルは、この記事の範囲外です。 このような攻撃を軽減するには、組織は次の一般的なベスト プラクティスに従う必要があります。

- アプリケーションの認証をセキュリティで保護する
- アプリケーションのアクセス許可が最小限の特権であることを確認する
- サーバー側ログでのトークンのキャプチャと保持を回避する
- 他のリソースへのアクセス許可を持つ OAuth アプリケーションのセキュリティ侵害を監視する
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/deprecation-key-derivation-function-version-1"} -->
## 認証のための KDFv1 アルゴリズムのサポートを削除するためのセキュリティ更新プログラム - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/deprecation-key-derivation-function-version-1
- Service: entra-id / devices
- Article date: 2025-06-27
- Summary: キー派生関数バージョン 1 アルゴリズムの削除と、デバイス管理者向けのプロアクティブ ガイダンス。

Microsoft は、2021 年 7 月より前にリリースされた Windows のビルドで、Microsoft Entra 参加済みまたは Microsoft Entra ハイブリッド参加済みデバイスの認証に使用されるキー派生関数バージョン 1 (KDFv1) アルゴリズムのサポートを削除しています。

KDFv1 アルゴリズムは、これまで古いバージョンの Windows でのデバイス認証に使用されてきました。 [CVE-2021-33781](https://www.cve.org/CVERecord?id=CVE-2021-33781) で説明されているように、承認されていない認証を許可する重大なセキュリティ上の欠陥が発見されました。 この脆弱性に対処するため、Microsoft は 2021 年 7 月に Windows セキュリティ更新プログラムを公開しました。 2021 年 7 月以降にリリースされた Windows ビルドはどれも、KDFv1 アルゴリズムを使用していません。

Microsoft は、セキュリティ強化に向けた継続的な取り組みの一環として、Microsoft Entra での認証に KDFv1 アルゴリズムの使用をブロックするセキュリティ更新プログラムを段階的に展開しています。

### セキュリティ更新プログラムの効果

Microsoft Entra を使用して認証するすべての Windows デバイスは、セキュリティ更新プログラムが適用済みであるか、2021 年 7 月以降にリリースされた Windows のビルドを実行している必要があります。 この変更のロールアウトが完了すると、更新プログラムが適用されていない Windows デバイスは Microsoft Entra での認証が行えなくなります。

#### エラー メッセージ

更新プログラムが適用されていないデバイス上でユーザーがサインインを試みると、以下のエラー メッセージが表示されます。

>
> サインイン エラー コード: 5000611
>
> エラーの理由: Symmetric Key Derivation Function バージョン '1' は無効です。 デバイスを最新の更新プログラムで更新してください。

このエラー メッセージは Microsoft Entra サインイン ログにも表示され、管理者は非推奨の KDFv1 アルゴリズムによる認証エラーを特定できます。

注記

セキュリティ更新プログラムの増分ロールアウトにより、修正プログラムが適用されていない Windows デバイスでの認証エラーは、最初は一時的または断続的に発生する可能性があります。 ロールアウトの初期段階では、認証の再試行が成功する可能性があります。 シームレスな認証エクスペリエンスを維持するためには、Windows セキュリティ更新プログラムを適用して、これらの問題に迅速に対処することが重要です。

### 必要な操作

Microsoft Entra 管理者は、このセキュリティ更新プログラムの影響を受ける可能性があるテナント内のデバイスを事前に特定して対処する必要があります。 次の手順が推奨されます。

- 認証エラーの監視: Microsoft Entra サインイン ログ内にエラー コード 5000611 とそれに対応するエラー理由がないかを定期的に確認します。
- デバイスの更新: KDFv1 アルゴリズムに言及しているエラー メッセージによる認証エラーをユーザーが報告している場合は、それらのデバイスをユーザーの Windows バージョンの最新のセキュリティ更新プログラムで更新します。
- 影響を受けるビルドの検索: CVE レコード CVE-2021-33781 内で提供されているガイダンスを使用して、影響を受けるビルドを実行している可能性があるテナント内の Windows デバイスを検索します。
- ユーザーとのコミュニケーション: デバイスを最新の状態に保つことの重要性をユーザーに伝達し、必要な更新プログラムを適用する方法について指示します。

#### 積極的な監視と更新

認証の中断を回避するには、デバイスの監視と更新を積極的に行うことが重要です。 Microsoft Entra 管理者が使用できる戦略は以下のとおりです。

- 自動更新: すべてのデバイスが最新のセキュリティ更新プログラムを迅速に受け取れるようにするために、自動更新のポリシーを実装します。
- 定期的な監査: デバイスの定期的な監査を実施して、セキュリティ更新プログラムに関する要件のコンプライアンスを確保します。
- ユーザー トレーニング: タイムリーな更新の重要性と、更新を確認して適用する方法についてユーザーを教育します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/device-join-macos-platform-single-sign-on"} -->
## macOS PSSO の即時使用環境で、Microsoft Entra ID を使用して Mac デバイスを登録する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-macos-platform-single-sign-on
- Service: entra-id / devices
- Article date: 2024-12-19
- Summary: macOS プラットフォーム シングル サインオンを使用して新しい Mac デバイスで Microsoft Entra を設定する方法

Mac ユーザーは、新しいデバイスの初回セットアップ (OOBE) の際に、Microsoft Entra ID にデバイスを参加させることができます。 macOS プラットフォーム シングル サインオン (PSSO) は、[Microsoft Enterprise シングル サインオン拡張機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin)を使用して有効になっている macOS の機能です。 PSSO を使用すると、ユーザーはハードウェア バインド キー、スマート カード、または Microsoft Entra ID パスワードを使用して Mac デバイスにサインインできます。 このチュートリアルでは、OOBE の間に自動デバイス登録を使用する PSSO を使うように Mac デバイスを設定する方法を示します。

### Prerequisites

- macOS 14 Sonoma の推奨される最小バージョン。 macOS 13 Ventura はサポートされていますが、最適なエクスペリエンスを得るために macOS 14 Sonoma の使用を強くお勧めします。
- [自動デバイス登録 (ADE)](https://support.apple.com/HT204142) が登録されているデバイス。 デバイスがこの要件に登録されているかどうかがわからない場合は、管理者に問い合わせてください。
- [Microsoft Intune ポータル サイト](https://learn.microsoft.com/ja-jp/mem/intune/apps/apps-company-portal-macos) バージョン 5.2404.0 以降。
- Microsoft Intune を使用してモバイル デバイス管理 (MDM) に登録されている Mac デバイス。
- [管理者が Intune で PSSO 設定](https://learn.microsoft.com/ja-jp/mem/intune/configuration/platform-sso-macos)を使用して構成されたシングル サインオン (SSO) 拡張機能 MDM ペイロード
- [Microsoft Authenticator](https://support.microsoft.com/account-billing/how-to-use-the-microsoft-authenticator-app-9783c865-0308-42fb-a519-8cf666fe0acc) (推奨): デバイスの登録を完了するには、ユーザーがモバイル デバイスで何らかの形式の Microsoft Entra ID 多要素認証 (MFA) に登録されている必要があります。
- スマート カードの設定の場合は、構成されて有効になっている[証明書ベースの認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-certificate-based-authentication)。 証明書がロードされたスマートカードと Microsoft Entra での認証、ローカルアカウントとペアリングされたスマートカード。
- ユーザーは、 [デバイスを登録して Microsoft Entra ID に参加するための](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-macos-platform-single-sign-on-extension?tabs=macOS14#insufficient-permissions)十分なアクセス許可を持っている必要があります。
- ネットワーク プロキシのフィルター処理または TLS 検査が環境内で有効になっている場合は、[プラットフォーム シングル Sign-On トラブルシューティング ガイド](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-macos-platform-single-sign-on-extension?tabs=macOS14#tls-inspection-urls-to-be-excluded-for-platform-sso)に記載されている推奨設定を確認してください

### macOS デバイスを設定する

1. Mac を初めて開いたときに "Hello" 画面が表示されたら、手順に従って国または地域を選択し、必要に応じてネットワーク設定を構成します。
2. **リモート管理**プロファイルをダウンロードするように求められます。これにより、Microsoft Intune の構成設定をデバイスに適用できます。 **[続行]** を選択し、管理プロファイルのダウンロードの承認を求められたら Microsoft Entra ID 資格情報を入力します。

    [Image: [リモート管理] ウィンドウのスクリーンショット。]
3. **Authenticator アプリ**に送信されたコードを入力するか (推奨)、別の MFA 方法を使用します。
4. ユーザー アカウントを作成するには、フル ネーム、アカウント名を入力し、ローカル アカウントのパスワードを作成します。 **[続行]** を選択すると、ホーム画面が表示されます。

    [Image: コンピューター アカウントの作成に使用されるウィンドウのスクリーンショット。ユーザーはここで自分の名前、アカウント名、ローカル パスワードを入力します。]

### 自動デバイス登録による登録

PSSO 登録には、次の 3 つの認証方法があります。

- **セキュア エンクレーブ**: ユーザーは、認証に Microsoft Entra ID を使用するアプリ間の SSO に使われるセキュア エンクレーブでサポートされる暗号化キーを持つデバイスにログオンします。 macOS のプラットフォーム資格情報とも呼ばれます。
- **スマート カード**: ユーザーは、外部スマート カードまたはスマート カード互換ハード トークンを使用してコンピューターにログインします。
- **パスワード**: ユーザーはローカル アカウントを使用してローカル デバイスにログオンし、Microsoft Entra ID パスワードを使用するように更新されます。 この方法では、フェデレーション ID 資格情報もサポートされます。

システム管理者が、セキュリティで保護されたエンクレーブまたはスマート カードを使用して Mac を登録していることを確認します。 これらの新しいパスワードレス機能は、PSSO でのみサポートされています。 続行する前に、管理者によって設定された認証方法を確認します。

## [セキュリティで保護されたエンクレーブ](#tab/secure-enclave)
1. 画面の右上にある **[登録が必要]** ポップアップに移動します。 ポップアップにカーソルを合わせ、**[登録]** を選択します。 macOS 14 Sonoma ユーザーの場合は、Microsoft Entra にデバイスを登録するためのプロンプトが表示されます。 macOS 13 Ventura の場合、このプロンプトは表示されません。

    [Image: 登録が必要という通知が選択された後に macOS 14 に表示される Microsoft Entra 登録プロンプトのスクリーンショット。]
2. ローカル アカウントのパスワードを入力するよう求めるプロンプトが表示されます。 パスワードを入力し、**[OK]** を選択します。
3. アカウントのロックが解除されたら、サインインするアカウントを選び、サインイン資格情報を入力して **[次へ]** を選択します。
4. このサインイン フローの一部として MFA が必要です。 **Authenticator アプリ**を開くか (推奨)、登録した他の MFA 方法を使用して、画面に表示される数字を入力して登録を完了します。
5. MFA フローが完了し、読み込み画面が消えたら、デバイスは PSSO に登録されるはずです。 PSSO を使用して Microsoft アプリ リソースにアクセスできるようになりました。

#### パスキーとして使用する macOS のプラットフォーム資格情報を有効にする

セキュア エンクレーブ方法を使ってデバイスを設定すると、Mac に保存された結果の資格情報をブラウザーのパスキーとして使用できます。 これを有効にするには:

1. **設定**アプリを開き、**[パスワード]**&gt;**[パスワード オプション]** に移動します。
2. **[パスワード オプション]** で、**[パスワードとパスキーの使用元]** を見つけ、トグル スイッチを使って **[ポータル サイト]** を有効にします。

    [Image: Company Portalからのパスワードとパスキーの使用がスイッチによって有効になっていることを示すパスワードオプションウィンドウのスクリーンショット。]

## [スマート カード](#tab/smart-card)
#### スマート カードをローカル アカウントとペアリングする

デバイスをスマート カードに登録する前に、 `sudo`を使用してスマート カードをローカル アカウントとペアリングする必要があります。 **ターミナル** アプリを開き、次の`sudo`コマンドを実行してスマート カード証明書の公開キー ハッシュを検索し、ローカル アカウントとペアリングしてから、成功したことを確認します。

```console
sc_auth identities
sudo sc_auth pair -h <HASH> -u <USERNAME>
sc_auth list
```

#### デバイスをスマート カードに登録する

1. 画面の右上にある **[登録が必要]** ポップアップに移動します。 ポップアップにカーソルを合わせ、**[登録]** を選択します。 スマート カードがローカル アカウントとペアリングされている場合は、スマート カードのピンを入力するように求めるメッセージが表示されます

    [Image: ユーザーにスマート カードの PIN の入力を求めるプラットフォーム SSO 登録のスクリーンショット。]
2. 管理者がデバイス登録フローに対して MFA を構成しているかどうかを確認します。 その場合は、モバイル デバイスで **Authenticator** アプリを開き、MFA フローを完了します。

    [Image: Microsoft でのサインインを求める登録ウィンドウのスクリーンショット。]
3. 証明書がローカル アカウントとまだペアリングされていない場合は、スマート カードを使用するように求めるメッセージが表示されます。 **[スマート カード]** を選択します。
4. スマート カードの PIN を入力するよう求められます。 PIN を入力し、**[スマート カードの PIN の入力]** を選択します。 正しい PIN を入力すると、スマート カード認証を使用した PSSO の登録が完了します。
5. PSSO を使用して Microsoft アプリ リソースにアクセスし、スマート カードの PIN でデバイスのロックを解除できるようになりました。 キーチェーン アクセスのロックを解除するには、再起動後にローカル パスワードを使用してサインインする必要があります。

## [Password](#tab/password)
1. 画面の右上にある **[登録が必要]** ポップアップに移動します。 ポップアップにカーソルを合わせ、**[登録]** を選択します。

    [Image: 画面の右上に [登録が必要] ポップアップが表示されたデスクトップ画面のスクリーンショット。]
2. ローカル アカウントのパスワードを入力するよう求めるプロンプトが表示されます。 パスワードを入力し、**[OK]** を選択します。
3. アカウントのロックが解除されたら、サインインするアカウントを選び、サインイン資格情報を入力して **[次へ]** を選択します。
4. このサインイン フローの一部として MFA が必要です。 **Authenticator アプリ**を開くか (推奨)、登録した他の MFA 方法を使用して、画面に表示される数字を入力して登録を完了します。
5. ローカル パスワードが Microsoft Entra ID パスワードと異なる場合は、画面の右上に **[認証が必要]** ポップアップが表示されます。 バナーにカーソルを合わせ、**[サインイン]** を選択します。
6. **Microsoft Entra** ウィンドウが表示されたら、Microsoft Entra ID パスワードを入力し、**[サインイン]** を選択します。

    [Image: Microsoft Entra サインイン ウィンドウのスクリーンショット。]
7. Mac のロックを解除した後、PSSO を使用して Microsoft アプリ リソースにアクセスできるようになりました。 この時点から、PSSO がデバイスで有効になっているため、古いパスワードは機能しません。

---

### デバイスの登録状態を確認する

上記の手順を完了したら、デバイスの登録状態を確認することをお勧めします。

1. 登録が正常に完了したことを確認するには、**[設定]** に移動し、**[ユーザーとグループ]** を選択します。
2. **[ネットワーク アカウント サーバー]** の横にある **[編集]** を選択し、**[プラットフォーム SSO]** が **[登録済み]** としてリストされていることを確認します。
3. 認証に使用する方法を確認するには、**[ユーザーとグループ]** ウィンドウでユーザー名に移動し、**[情報]** アイコンを選択します。 リストされている方法を確認します。これは、**セキュア エンクレーブ**、**スマート カード**、または**パスワード**である必要があります。

    Note

    **ターミナル** アプリを使用して登録状態を確認することもできます。 次のコマンドを実行して、デバイス登録の状態を確認します。 出力の下部に、SSO トークンが取得されていることが示されるはずです。 macOS 13 Ventura ユーザーの場合は、登録状態を確認するためにこのコマンドが必要です。

    ```console
    app-sso platform -s
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/device-join-macos-platform-single-sign-on-kerberos-configuration"} -->
## Kerberos SSO を有効にして、プラットフォーム SSO でオンプレミスの Active Directory や Microsoft Entra ID の Kerberos リソースに接続します。 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-macos-platform-single-sign-on-kerberos-configuration
- Service: entra-id / devices
- Article date: 2024-05-13
- Summary: 管理者が、オンプレミスのActive DirectoryおよびMicrosoft Entra IDのKerberos統合リソースに対してKerberos認証をサポートするようにmacOSプラットフォームのSingle Sign-onを設定する方法。

macOS プラットフォーム シングル サインオン (PSSO) は、[Microsoft Enterprise シングル サインオン拡張機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin)を使用して有効になっている macOS の機能です。 プラットフォーム SSO を使用すると、ユーザーは macOS デバイスに Entra を参加させ、PSSO プライマリ更新トークン (PRT) を使用してハードウェア バインド キー、スマート カード、またはMicrosoft Entra ID パスワードを使用してサインインできます。

PSSO PRT に加えて、Microsoft Entraはオンプレミスとクラウドベースの Kerberos チケット許可チケット (TGT) の両方も発行します。このチケットは、PSSO の TGT マッピングを介して macOS のネイティブ Kerberos スタックと共有されます。 お客様は、環境でこれらの TGT がどのように使用されているかを柔軟に判断でき、それに応じて Kerberos SSO 拡張ファイルを構成できます。 Apple が所有および管理する Kerberos SSO 拡張機能は、macOS 上の Kerberos ベースのリソースにシームレスなシングル サインオンを提供するように設計されています。 Kerberos SSO 拡張機能の構成で必要なヘルプについては、Apple に問い合わせてください。

このチュートリアルでは、プラットフォーム SSO TGT を利用して、Microsoft Entra IDへの SSO に加えて、オンプレミスおよびクラウド リソースに対する Kerberos ベースの SSO をサポートする方法について説明します。 Kerberos SSO はプラットフォーム SSO 内のオプションの機能ですが、ユーザーが Kerberos を使用して認証を行うオンプレミスの Active Directory のリソースにアクセスする必要がある場合に推奨されます。

### Kerberos TGT 設定をカスタマイズする

お客様は、SSO 拡張機能構成の拡張機能データ ディクショナリで次のキー/値を使用して、TGT マッピング設定をカスタマイズできます。 このオプションは、 **ポータル サイト バージョン 2508 以降でのみ有効です。**

- **キー**: `custom_tgt_setting`
- **型**: `Integer`

| 価値 | 説明 |
| --- | --- |
| `0` | **オンプレミス TGT とクラウド TGT の両方** – オンプレミスとクラウドの両方の TGT をマップします。 これが既定の構成です。 |
| `1` | **オンプレミス TGT のみ – オンプレミスの TGT** のみをマップします。 |
| `2` | **クラウド TGT のみ** – クラウドベースの TGT のみをマップします。 |
| `3` | **TGT なし** – TGT マッピングを完全に無効にします。 |

構成例:

[Image: Kerberos TGT SSO 設定のカスタマイズのスクリーンショット。]

### 前提条件

- **macOS 14.6 Sonoma の最小バージョン**。
- [Microsoft Intune Company Portal](https://learn.microsoft.com/ja-jp/mem/intune/apps/apps-company-portal-macos) バージョン 5.2408.0 以降
- モバイル デバイス管理 (MDM) に登録された Mac デバイス。
- 管理者によるプラットフォーム SSO 設定を使用して構成された SSO 拡張機能 MDM ペイロードは、すでにデバイスに展開されています。 Intune が MDM である場合は、[プラットフォーム SSO ドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/devices/macos-psso) または [Intune 展開ガイド](https://learn.microsoft.com/ja-jp/mem/intune/configuration/platform-sso-macos) をご覧ください。
- Microsoft Entra Kerberos をデプロイします。これは、オンプレミスの Active Directory でいくつかの Kerberos 機能に必要です。 詳細については、Windows Hello for BusinessCloud Kerberos 信頼展開ガイドを参照するか>Cloud Kerberos 信頼の構成手順を参照してセットアップを開始します。 Windowsの Cloud Kerberos 信頼またはパスワードなしのセキュリティ キーサインインを使用してWindows Hello for Businessを既に展開している場合、この手順は既に完了しています。

### macOS デバイスを設定する

プラットフォーム SSO の構成とデプロイについては、[Microsoft Entra ID macOS Platform SSO のドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/devices/macos-psso)を参照してください。 このガイドを使用して Kerberos SSO を展開するかどうかに関係なく、エンタープライズ管理の Mac にはプラットフォーム SSO を展開する必要があります。

### on-premises Active Directoryの Kerberos SSO MDM プロファイル構成

Microsoft Entra ID Cloud Kerberos 領域と on-premises Active Directory 領域の両方を使用する予定の場合は、個別の Kerberos SSO MDM プロファイルを構成する必要があります。 Microsoft Entra Cloud Kerberos TGT を使用する予定がない場合は、オンプレミスの Kerberos SSO プロファイルのみを構成する必要があります。

次の設定を使用してon-premises Active Directory プロファイルを構成し、**contoso.com** および **Contoso** へのすべての参照を環境に適した値に置き換えます。

| 構成キー | 推奨値 | 注 |
| --- | --- | --- |
| `Hosts` | `<string>.contoso.com</string>` | **contoso.com** をオンプレミスのドメイン/フォレスト名に置き換えます。 ドメイン/フォレスト名の前に前の `.` 文字を付けます |
| `Hosts` | `<string>contoso.com</string>` | **contoso.com** をオンプレミスのドメイン/フォレスト名に置き換える |
| `Realm` | `<string>CONTOSO.COM</string>` | **CONTOSO.COM** をオンプレミスの領域名に置き換えます。 値はすべて大文字にする必要があります。 |
| `PayloadOrganization` | `<string>Contoso</string>` | **Contoso**を組織名に置き換える |

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
<plist version="1.0">
<dict>
    <key>PayloadContent</key>
    <array>
        <dict>
            <key>ExtensionData</key>
            <dict>
                <key>allowPasswordChange</key>
                <true/>
                <key>allowPlatformSSOAuthFallback</key>
                <true/>
                <key>performKerberosOnly</key>
                <true/>
                <key>pwReqComplexity</key>
                <true/>
                <key>syncLocalPassword</key>
                <false/>
                <key>usePlatformSSOTGT</key>
                <true/>
            </dict>
            <key>ExtensionIdentifier</key>
            <string>com.apple.AppSSOKerberos.KerberosExtension</string>
            <key>Hosts</key>
            <array>
                <string>.contoso.com</string>
                <string>contoso.com</string>
            </array>
            <key>Realm</key>
            <string>CONTOSO.COM</string>
            <key>PayloadDisplayName</key>
            <string>Single Sign-On Extensions Payload for On-Premises</string>
            <key>PayloadIdentifier</key>
            <string>com.apple.extensiblesso.1aaaaaa1-2bb2-3cc3-4dd4-5eeeeeeeeee5</string>
            <key>PayloadType</key>
            <string>com.apple.extensiblesso</string>
            <key>PayloadUUID</key>
            <string>1aaaaaa1-2bb2-3cc3-4dd4-5eeeeeeeeee5</string>
            <key>TeamIdentifier</key>
            <string>apple</string>
            <key>Type</key>
            <string>Credential</string>
        </dict>
    </array>
    <key>PayloadDescription</key>
    <string></string>
    <key>PayloadDisplayName</key>
    <string>Kerberos SSO Extension for macOS for On-Premises</string>
    <key>PayloadEnabled</key>
    <true/>
    <key>PayloadIdentifier</key>
    <string>2bbbbbb2-3cc3-4dd4-5ee5-6ffffffffff6</string>
    <key>PayloadOrganization</key>
    <string>Contoso</string>
    <key>PayloadRemovalDisallowed</key>
    <true/>
    <key>PayloadScope</key>
    <string>System</string>
    <key>PayloadType</key>
    <string>Configuration</string>
    <key>PayloadUUID</key>
    <string>2bbbbbb2-3cc3-4dd4-5ee5-6ffffffffff6</string>
    <key>PayloadVersion</key>
    <integer>1</integer>
</dict>
</plist>
```

環境に適した値で構成を更新した後、 *mobileconfig* ファイル拡張子を持つテキスト エディター (たとえば、ファイルに *on-prem-kerberos.mobileconfig* という名前を付ける) を使用して構成を保存します。

### Microsoft Entra ID Cloud Kerberos の Kerberos SSO MDM プロファイル構成

Microsoft Entra ID Cloud Kerberos 領域と on-premises Active Directory 領域の両方を使用する予定の場合は、個別の Kerberos SSO MDM プロファイルを構成する必要があります。 Microsoft Entra ID Cloud Kerberos プロファイルの前にon-premises Active Directoryプロファイルをデプロイすることをお勧めします。

次の設定を使用して、Microsoft Entra ID Cloud Kerberos プロファイルを構成し、すべての参照をテナントの適切な値に置き換えます。

| 構成キー | 推奨値 | 注 |
| --- | --- | --- |
| `preferredKDCs` | `<string>kkdcp://login.microsoftonline.com/aaaabbbb-0000-cccc-1111-dddd2222eeee/kerberos</string>` | **aaaabbbb-0000-cccc-1111-dddd2222eeee** の値をテナントのテナント ID に置き換えます。これは、[Microsoft Entra管理センターの \[概要\] ページにあります](https://entra.microsoft.com) |
| `PayloadOrganization` | `<string>Contoso</string>` | **Contoso**を組織名に置き換える |
| `Hosts` | `<string>.windows.net</string>` |  |
| `Hosts` | `<string>windows.net</string>` |  |
| `Realm` | `<string>KERBEROS.MICROSOFTONLINE.COM</string>` | 値はすべて大文字にする必要があります。 |

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
<plist version="1.0">
<dict>
    <key>PayloadContent</key>
    <array>
        <dict>
            <key>ExtensionData</key>
            <dict>
                <key>usePlatformSSOTGT</key>
                <true/>
                <key>performKerberosOnly</key>
                <true/>
                <key>preferredKDCs</key>                         
                <array>
                <string>kkdcp://login.microsoftonline.com/aaaabbbb-0000-cccc-1111-dddd2222eeee/kerberos</string>
                </array>
            </dict>
            <key>ExtensionIdentifier</key>
            <string>com.apple.AppSSOKerberos.KerberosExtension</string>
            <key>Hosts</key>
            <array>
                <string>windows.net</string>
                <string>.windows.net</string>
            </array>
            <key>Realm</key>
            <string>KERBEROS.MICROSOFTONLINE.COM</string>
            <key>PayloadDisplayName</key>
            <string>Single Sign-On Extensions Payload for Microsoft Entra ID Cloud Kerberos</string>
            <key>PayloadIdentifier</key>
            <string>com.apple.extensiblesso.00aa00aa-bb11-cc22-dd33-44ee44ee44ee</string>
            <key>PayloadType</key>
            <string>com.apple.extensiblesso</string>
            <key>PayloadUUID</key>
            <string>00aa00aa-bb11-cc22-dd33-44ee44ee44ee</string>
            <key>TeamIdentifier</key>
            <string>apple</string>
            <key>Type</key>
            <string>Credential</string>
        </dict>
    </array>
    <key>PayloadDescription</key>
    <string></string>
    <key>PayloadDisplayName</key>
    <string>Kerberos SSO Extension for macOS for Microsoft Entra ID Cloud Kerberos</string>
    <key>PayloadEnabled</key>
    <true/>
    <key>PayloadIdentifier</key>
    <string>11bb11bb-cc22-dd33-ee44-55ff55ff55ff</string>
    <key>PayloadOrganization</key>
    <string>Contoso</string>
    <key>PayloadRemovalDisallowed</key>
    <true/>
    <key>PayloadScope</key>
    <string>System</string>
    <key>PayloadType</key>
    <string>Configuration</string>
    <key>PayloadUUID</key>
    <string>11bb11bb-cc22-dd33-ee44-55ff55ff55ff</string>
    <key>PayloadVersion</key>
    <integer>1</integer>
</dict>
</plist>
```

環境に適した値で構成を更新した後、 *mobileconfig* ファイル拡張子のテキスト エディター (たとえば、 *ファイルに cloud-kerberos.mobileconfig* という名前を付ける) を使用して構成を保存します。

注

「usePlatformSSOTGT キーと performKerberosOnly キーを必ず注意してください。」 usePlatformSSOTGT が true に設定されている場合、Kerberos 拡張機能は同じ領域でプラットフォーム SSO からの TGT を使用します。 既定値は false です。 performKerberosOnly が true に設定されている場合、Kerberos 拡張機能はパスワードの有効期限チェック、外部パスワード変更チェック、またはユーザーのホーム ディレクトリの取得を実行しません。 既定値は false です。 これは、オンプレミスとクラウドの両方の構成に適用できます。これらのキーは両方のプロファイルで構成する必要があります。

### Intune の構成手順

Intune を MDM として使用する場合は、次の手順を実行してプロファイルを展開できます。 contoso.com の値を組織の適切な値に置き換える方法については、**前の手順**に従ってください。

1. [Microsoft Intune管理センター](https://go.microsoft.com/fwlink/?linkid=2109431)にサインインします。
2. **[デバイス]**&gt; -**[構成]**&gt; -**[作成]**&gt; -**[新しいポリシー]**を選択します。
3. 次のプロパティを入力します:
    - **プラットフォーム**: **macOS** を選択します。
    - **プロファイルの種類**: **[テンプレート**] を選択します。
4. **カスタム** テンプレートを選択し、**[作成]**を選択します。
5. **[基本]**で、次のプロパティを入力します。
    - **名前**: ポリシーのわかりやすい名前を入力します。 後で簡単に識別できるように、ポリシーに名前を付けます。 たとえば、ポリシーに「**macOS - Platform SSO Kerberos**」という名前を付けます。
    - **説明**: ポリシーの説明を入力します。 この設定は省略可能ですが、設定することをお勧めします。
6. **次へ**を選択します。
7. **カスタム構成プロファイル名**ボックスに名前を入力します。
8. **デプロイ チャネル**を選択します。 デバイス チャネルをお勧めします。
9. フォルダーアイコンをクリックして、**構成プロファイルファイル**をアップロードします。 テンプレートをカスタマイズした後、以前に保存した *kerberos.mobileconfig* ファイルを選択します（[オンプレミス Active Directory 用の Kerberos SSO MDM プロファイル構成](#Kerberos SSO MDM profile configuration for on-premises Active Directory)）。
10. **次へ**を選択します。
11. **スコープ タグ** (オプション) で、`US-NC IT Team` や `JohnGlenn_ITDepartment` など、特定の IT グループにプロファイルをフィルター処理するためのタグを割り当てます。 **次へ**を選択します。
    - スコープ タグの詳細については、「[分散 IT に RBAC ロールとスコープのタグを使用する](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/scope-tags)」を参照してください。
12. **割り当て**で、プロファイルを受信するユーザーまたはユーザー グループを選択します。 プラットフォーム SSO ポリシーは、ユーザーベースのポリシーです。 プラットフォーム SSO ポリシーをデバイスに割り当てないでください。
    - プロファイルの割り当ての詳細については、[ユーザーおよびデバイス プロファイルの割り当て](https://learn.microsoft.com/ja-jp/mem/intune/configuration/device-profile-assign)に関するページを参照してください。
13. **次へ**を選択します。
14. **[確認と作成]** で、設定を確認します。 **[作成]** を選択すると、変更内容が保存され、プロファイルが割り当てられます。 ポリシーはプロファイルの一覧にも表示されます。
15. オンプレミスの Kerberos SSO と Microsoft Entra ID Cloud Kerberos の両方を使用するため、両方のプロファイルを展開する必要がある場合は、このプロセスを繰り返します。

次回、デバイスで構成の更新が確認されると、構成した設定が適用されます。

### Kerberos SSO のテスト

ユーザーがプラットフォーム SSO 登録を完了したら、ターミナル アプリで `app-sso platform -s` コマンドを実行して、デバイスに Kerberos チケットがあることを確認できます。

```console
app-sso platform -s
```

2 つの Kerberos チケットが必要です。1 つはオンプレミス AD の ticketKeyPath 値が **tgt\_ad** で、1 つは ticketKeyPath 値が **tgt\_cloud** のMicrosoft Entra ID テナント用です。 出力は次のようになります。

[Image: macOS ターミナル アプリでの app-sso platform -s の出力のスクリーンショット。]

適切な Kerberos 対応リソースを使用してテストすることで、構成が機能していることを確認します。

- Finder を使用してオンプレミスの AD 統合ファイル サーバーにアクセスするか、Safari を使用して Web アプリケーションにアクセスして、on-premises Active Directory機能をテストします。 ユーザーは、対話型の資格情報を要求されることなく、ファイル共有にアクセスできる必要があります。
- Microsoft Entra ID クラウド Kerberos を利用可能にした Azure Files 共有にアクセスして、Kerberos 機能をテストします。 ユーザーは、対話型の資格情報を要求されることなく、ファイル共有にアクセスできる必要があります。

注

Microsoft のプラットフォーム SSO 実装は、Kerberos TGT を発行し、macOS に配信して macOS にインポートできるようにする役割を担います。 `app-sso platform -s`の実行中に TGT が表示された場合、TGT は正常にインポートされています。 Kerberos 経由でのオンプレミス リソースへのアクセスの問題など、Kerberos の問題が進行中の場合は、Kerberos MDM プロファイルをさらに構成してサポートを受けるために Apple に連絡することをお勧めします。 macOS の Kerberos 実装では、Apple が提供するネイティブの Kerberos 機能が使用されます。

### Cloud Kerberos TGT を使用して Azure ファイル ストレージにアクセスする

プラットフォーム SSO を通じて発行されたクラウド TGT を使用すると、ユーザーに対話型資格情報の入力を求めることなく、Azureファイル共有にシームレスにアクセスできます。 PSSO Kerberos TGT 機能を使用したAzureファイル共有へのアクセスは、現在限定プレビュー段階であることに注意してください。 試してみる場合は、オンボード サポートの azurefiles@microsoft.com にお問い合わせください。 Azure Filesでのクラウド ファイル共有の構成に関するガイダンスが必要な場合は、[こちらのガイド](https://learn.microsoft.com/ja-jp/azure/storage/files/storage-files-identity-auth-hybrid-identities-enable)を参照してください。

注

SMB 経由でファイル共有をマウントする場合は、Azure ファイル共有のアプリ登録に関連付けられているマニフェスト ファイルに、`cifs` マッピングが **lowercase** に含まれていることを確認します。 この値を大文字の `CIFS`に設定すると、マウント プロセス中に問題が発生する可能性があります。

### 既知の問題

#### Kerberos SSO 機能拡張メニュー エクストラ

プラットフォーム SSO に対する Kerberos SSO のサポートをデプロイする場合、macOS の標準 Kerberos SSO 拡張機能は引き続き使用されます。 プラットフォーム SSO を使用しないネイティブ [Kerberos SSO 機能拡張](https://support.apple.com/guide/deployment/kerberos-sso-extension-depe6a1cda64/web)のデプロイと同様に、Kerberos SSO 機能拡張メニュー エクストラが macOS メニュー バー内に表示されます。

[Image: macOS Kerberos SSO 機能拡張メニュー エクストラのスクリーンショット。]

プラットフォーム SSO を使用して Kerberos サポートをデプロイする場合、Kerberos 機能を機能させるために、ユーザーは Kerberos SSO 拡張機能メニューを追加で操作する必要はありません。 Kerberos SSO 機能は、ユーザーがメニューバーの拡張機能にサインインせず、その拡張機能が「サインインしていない」という通知を出している場合でも動作します。 この記事に従って Platform SSO を使用してデプロイする場合は、ユーザーにメニュー バー エクストラを無視するように指示できます。 その代わり、この記事の「Kerberos SSO のテスト」セクションの中で説明しているように、メニュー バー エクストラを操作しなくても Kerberos 機能が想定どおりに動作することを必ず確認してください。

#### Kerberos SSO のブラウザー サポート

macOS デバイスで Platform SSO を使って Kerberos を有効にしている場合など、一部のブラウザーでは Kerberos SSO のサポートを有効にするために追加の構成が必要です。 macOS に Kerberos のサポートを展開するときは、使用するブラウザーごとに適切な設定を展開して、macOS の Kerberos SSO 機能と対話できることを確認します。

- Safari: 既定で Kerberos SSO をサポートします
- Microsoft Edge:
    - AuthNegotiateDelegateAllowlist 設定を構成して、オンプレミス Active Directory フォレスト情報 (AuthNegotiateDelegateAllowlist) に含めるよう設定してください。
    - **AuthServerAllowlist** 設定を構成して、オンプレミス Active Directory フォレスト情報 ([AuthServerAllowlist](https://learn.microsoft.com/ja-jp/DeployEdge/microsoft-edge-policies#authserverallowlist)) を含めます。
- Google Chrome
    - AuthNegotiateDelegateAllowlist 設定を構成して、オンプレミス Active Directory フォレスト情報 (AuthNegotiateDelegateAllowlist) に含めるよう設定してください。
    - **AuthServerAllowlist** 設定を構成して、オンプレミス Active Directory フォレスト情報 ([AuthServerAllowlist](https://chromeenterprise.google/policies/#AuthServerAllowlist)) を含めます。
- Mozilla Firefox
    - Kerberos SSO のサポートを有効にするには、Mozilla Firefox の設定 **network.negotiate-auth.trusted-uris** と **network.automatic-ntlm-auth.trusted-uris** を構成します
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/device-join-macos-platform-single-sign-on-multi-user-device"} -->
## Microsoft Entra ID を使用して Mac デバイスを参加させ、共有デバイス シナリオ用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-macos-platform-single-sign-on-multi-user-device
- Service: entra-id / devices
- Article date: 2026-02-23
- Summary: macOS プラットフォーム シングル サインオンを使用した共有デバイス シナリオで複数のユーザーをサポートする Microsoft Entra に参加する Mac をユーザーが設定する方法

このチュートリアルでは、モバイル デバイス管理 (MDM) を使用して、複数のユーザーをサポートするように Microsoft Entra に参加する Mac を構成する方法について説明します。 プラットフォーム SSO (PSSO) への Mac デバイスの登録には、セキュリティで保護されたエンクレーブ、スマート カード、パスワードの 3 つの方法があります。 最適なパスワードレス エクスペリエンスを実現するために、セキュリティで保護されたエンクレーブまたはスマート カードを使用することをお勧めしますが、共有またはマルチユーザーの Mac では、代わりにパスワード方式を使用するとメリットが得られる場合があります。 パスワードを使用する共有 Mac の一般的なシナリオは、学校または大学のコンピューター ラボです。 これらのシナリオでは、学生は複数のデバイスを使用し、複数の学生が同じデバイスを使用し、これらの学生はパスワードのみを把握していて、MFA またはパスワードレスの資格情報はありません。

### 前提条件

- macOS 14 Sonoma 以降の必要な最小バージョン。 macOS 13 Ventura はプラットフォーム SSO 全体でサポートされていますが、このガイドで説明するプラットフォーム SSO 共有 Mac シナリオに必要なツールは Sonoma のみがサポートしています。
- Microsoft Intune [ポータル サイト アプリ](https://learn.microsoft.com/ja-jp/mem/intune/apps/apps-company-portal-macos) バージョン 5.2404.0 以降。
- 管理者が MDM 内で構成したプラットフォームSSOのMDMペイロード。

### MDM の構成

共有デバイスでプラットフォーム SSO を構成する主な手順は 3 つあります。

1. **ポータル サイトを展開します。** 詳細については、[macOS 用ポータル サイト アプリの追加](https://learn.microsoft.com/ja-jp/mem/intune/apps/apps-company-portal-macos)に関する記事を参照してください。
2. **プラットフォーム SSO 構成を展開します。** 必要なプラットフォーム SSO 構成を使用して、設定カタログ プロファイルを作成して展開します。
3. **macOS ログイン画面の構成を展開します。** macOS ログイン画面の構成を変更して、新しいユーザーにログインを許可できます。

#### 共有デバイスの制限事項

共有 macOS デバイスに関する次の点に注意してください。

- ユーザー間で共有することを目的とした macOS デバイスは、ユーザーアフィニティなしで登録されていることを確認する必要があります ([ユーザー アフィニティなしで ADE に登録](https://learn.microsoft.com/ja-jp/intune/intune-service/enrollment/device-enrollment-program-enroll-macos)、[非 ADE 環境のための直接登録](https://learn.microsoft.com/ja-jp/intune/intune-service/enrollment/device-enrollment-direct-enroll-macos))。
- 条件付きアクセス ポリシーは、複数のユーザーと共有されている macOS デバイスではサポートされていません。

#### プラットフォーム SSO プロファイルの構成

プラットフォーム SSO MDM プロファイルでは、マルチユーザー デバイスをサポートするために次の構成を適用する必要があります。

| 構成パラメーター | 値（値複数可） | 注 |
| --- | --- | --- |
| 画面ロック動作 | 取り扱わない | 必須 |
| 登録トークン | {{DEVICEREGISTRATION}} | 登録のユーザー体験を最適にするための推奨事項 |
| 認証方法 | パスワード、スマートカード、セキュリティで保護されたエンクレーブ | ログイン画面で新しいユーザーを作成する際に、選択した認証方法に関係なく、ユーザー名とパスワードを指定する必要があります。 |
| 認可の有効化 | 有効化済み | 必須 |
| ログイン時のユーザー作成の有効化 | 有効化済み | 必須 |
| 新しいユーザーの認可モード | Standard | 推奨 |
| トークンからユーザーへのマッピング --&gt; アカウント名 | 希望ユーザー名 | 必須 |
| トークンからユーザーへのマッピング --&gt; フル ネーム | 名前 | 必須 |
| 共有デバイス キーの使用 | 有効化済み | 必須 |
| ユーザーの認可モード | Standard | 推奨 |
| チーム識別子 | UBF8T346G9 | 必須 |
| 拡張機能識別子 | com.microsoft.CompanyPortalMac.ssoextension | 必須 |
| タイプ | リダイレクト | 必須 |
| URL | `https://login.microsoftonline.com`､https://login.microsoft.com、`https://sts.windows.net`、https://login.partner.microsoftonline.cn、https://login.chinacloudapi.cn、https://login.microsoftonline.us、https://login-us.microsoftonline.com | 必須 |

選択した MDM として Intune を使用する場合、構成プロファイルの設定は次のように表示されます。

[Image: Intune のプラットフォーム SSO MDM プロファイルのスクリーンショット。]

#### macOS ログイン画面の構成

新しいユーザーが macOS ログイン画面からログオンして作成できるようにするには、次の 2 つの構成を使用できます。

- **管理対象の他のユーザーの表示**。 この構成では、macOS ログイン画面に、作成されたプロファイルの一覧と、ユーザー名とパスワードでログインするために使用できる "その他のユーザー" ボタンが表示されます。 ユーザーは、既存のプロファイルを選択してログインするか、Microsoft Entra ID ユーザー プリンシパル名 (UPN) でログインできます。
- **フル ネームの表示**。 この構成では、macOS ログイン画面が表示され、ユーザーの一覧のないユーザー名とパスワード フィールドが表示されます。 ユーザーは、自分の Microsoft Entra ID UPN を使用してログインできます。

これらの構成は、Intune 設定カタログの **[ログイン]**&gt;**[Login Window Behavior]\(ログイン ウィンドウの動作\)** にあります。

### デバイスの登録

Mac デバイスをプラットフォーム SSO に登録するには、デバイスを MDM に登録する必要があります。 共有デバイスの場合、デバイスを設定するユーザーは通常、管理者または技術者になります。このユーザーは、代わりのローカル管理者アカウントが作成されていない限り、ローカル管理者権限を持ちます。

注

自動デバイス登録を使用して登録する場合は、次のようにローカル アカウントを作成するようにデバイスを設定することをユーザーに勧めることもできます。

- **アカウント名:** Microsoft Entra ID ユーザー名 (例: user@domain.com)。
- **フル ネーム:** 名と姓。 これは、セットアップ アシスタント中に作成されたローカル アカウントが登録時に Microsoft Entra ID アカウントに関連付けられるためです。

共有デバイスでプラットフォーム SSO を設定するには、次の 3 つの大まかな手順があります。

1. IT 管理者または委任されたユーザーが Intune にデバイスを登録します。
2. IT 管理者または委任されたユーザーは、自分の資格情報を使用してデバイスを Microsoft Entra ID に登録します。
3. これで、新しいユーザーが Microsoft Entra ID ログイン画面からログインする準備がデバイスで整いました。

組織は、デバイスの所有権に応じてさまざまな方法を使用して、共有デバイスを Intune に登録できます。

| 登録方法 | デバイスの所有権 | 要件 |
| --- | --- | --- |
| [ユーザー特定なしでのデバイスの自動登録](https://learn.microsoft.com/ja-jp/mem/intune/enrollment/device-enrollment-program-enroll-ios) | 会社または学校所有 | ✔️Apple Business Manager での登録✔️ Intune で構成された自動デバイス登録 |
| [企業ポータル](https://learn.microsoft.com/ja-jp/mem/intune/user-help/enroll-your-device-in-intune-macos-cp) | 個人用 | なし |

#### プラットフォーム SSO の登録

デバイスが MDM に登録され、ポータル サイトがインストールされたら、デバイスをプラットフォーム SSO に登録する必要があります。 画面の右上に **[登録が必要]** ポップアップが表示されます。 ポップアップから、Microsoft Entra ID 資格情報を使用してデバイスをプラットフォーム SSO に登録します。

1. 画面の右上にある **[登録が必要]** ポップアップに移動します。 ポップアップにカーソルを合わせ、**[登録]** を選択します。

    [Image: 画面の右上に [登録が必要] ポップアップが表示されたデスクトップ画面のスクリーンショット。]
2. Microsoft Entra ID にデバイスを登録するよう求めるメッセージが表示されます。 サインイン資格情報を入力し、**[次へ]** を選択します。

    1. 管理者がデバイス登録フローに対して MFA を構成している可能性があります。 その場合は、モバイル デバイスで **Authenticator** アプリを開き、MFA フローを完了します。

    [Image: Microsoft でのサインインを求める登録ウィンドウのスクリーンショット。]
3. **[シングル サインオン]** ウィンドウが表示されたら、ローカル アカウントのパスワードを入力し、**[OK]** を選択します。

    [Image: ユーザーにローカル アカウントのパスワード入力を求めるシングル サインオン ウィンドウのスクリーンショット。]
4. ローカル パスワードが Microsoft Entra ID パスワードと異なる場合は、画面の右上に **[認証が必要]** ポップアップが表示されます。 バナーにカーソルを合わせ、**[サインイン]** を選択します。
5. **Microsoft Entra** ウィンドウが表示されたら、Microsoft Entra ID パスワードを入力し、**[サインイン]** を選択します。

    [Image: Microsoft Entra サインイン ウィンドウのスクリーンショット。]
6. Mac のロックを解除した後、プラットフォーム SSO を使用して Microsoft アプリ リソースにアクセスできるようになりました。 この時点から、プラットフォーム SSO がデバイスで有効になっているため、古いパスワードは機能しません。

### デバイスの登録状態を確認する

上記の手順を完了したら、デバイスの登録状態を確認することをお勧めします。

1. 登録が正常に完了したことを確認するには、**[設定]** に移動し、**[ユーザーとグループ]** を選択します。
2. **[ネットワーク アカウント サーバー]** の横にある **[編集]** を選択し、**[プラットフォーム SSO]** が **[登録済み]** としてリストされていることを確認します。
3. 認証に使用する方法を確認するには、**[ユーザーとグループ]** ウィンドウでユーザー名に移動し、**[情報]** アイコンを選択します。 リストされている方法を確認します。これは、**セキュア エンクレーブ**、**スマート カード**、または**パスワード**である必要があります。

    注

    **ターミナル** アプリを使用して登録状態を確認することもできます。 次のコマンドを実行して、デバイス登録の状態を確認します。 出力の下部に、SSO トークンが取得されていることが示されるはずです。 macOS 13 Ventura ユーザーの場合は、登録状態を確認するためにこのコマンドが必要です。

    ```console
    app-sso platform -s
    ```

### ログイン時にユーザーの作成を有効にする機能をテストする

次に、テナント内の他のユーザーがデバイスにログインする準備ができていることを検証する必要があります。

1. 初期セットアップを行うために使用したアカウントを使用して Mac からログアウトします。
2. ログイン画面で、**[その他...]** オプションを選択して、新しいユーザー アカウントでサインインします

[Image: macOS ログイン画面のスクリーンショット。]

1. ユーザーの Microsoft Entra ID ユーザー プリンシパル名とパスワードを入力します。

[Image: ユーザーが資格情報を入力した macOS ログイン ボックスのスクリーンショット。]

1. ユーザー プリンシパル名とパスワードが正しい場合、ユーザーはログインできます。 ユーザーは、既定で複数のセットアップ アシスタント ダイアログ画面を移動するように指示された後、macOS デスクトップに移動します。

### トラブルシューティング

ユーザーが正常にサインインできない場合は、次のリソースを使用してトラブルシューティングを行います。

1. [macOS プラットフォームのシングル サインオンの既知の問題とトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-macos-platform-single-sign-on-extension) ガイドを参照します
2. ユーザーが別のデバイス上のブラウザーでユーザー プリンシパル名とパスワードを使用して Microsoft Entra ID に正常にサインインできることを確認します。 ユーザーを https://myapps.microsoft.com などの Web アプリに移動させるとテストできます
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/device-join-microsoft-entra-company-portal"} -->
## Company Portalを使用して Mac デバイスをMicrosoft Entra IDに参加させる - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-microsoft-entra-company-portal
- Service: entra-id / devices
- Article date: 2024-12-19
- Summary: Company Portalを使用して、macOS Platform シングル サインオン拡張機能を使用して新しい macOS を設定する方法。

このチュートリアルでは、Company Portalを使用して macOS Platform Single Sign-on (PSSO) に Mac デバイスを登録する方法と、Microsoft Entra Join を使用した Intune MDM 登録を行う方法について説明します。 PSSO を使用して Mac デバイスを登録するには、Secure Enclave、スマート カード、またはパスワードの 3 つの方法があります。 最適なパスワードレス エクスペリエンスを実現するには、セキュリティで保護されたエンクレーブまたはスマート カードを使用することをお勧めしますが、この方法は、Microsoft Intuneを使用して会社の管理者によって事前設定されることに注意してください。

### Prerequisites

- macOS 14 Sonoma の推奨される最小バージョン。 macOS 13 Ventura はサポートされていますが、最適なエクスペリエンスを得るには macOS 14 Sonoma を使用することを強くお勧めします。
- Microsoft Intune [Company Portal app](https://learn.microsoft.com/ja-jp/mem/intune/apps/apps-company-portal-macos) バージョン 5.2404.0 以降
- [Microsoft Intune](https://learn.microsoft.com/ja-jp/mem/intune/user-help/enroll-your-device-in-intune-macos-cp) を使用してモバイル デバイス管理 (MDM) に登録されている Mac デバイス。
- 管理者が Intune で PSSO 設定を使用して構成した SSO 拡張機能 MDM ペイロード
- [Microsoft Authenticator](https://support.microsoft.com/account-billing/how-to-use-the-microsoft-authenticator-app-9783c865-0308-42fb-a519-8cf666fe0acc) (推奨)、デバイスの登録を完了するには、何らかの形式のMicrosoft Entra ID多要素認証 (MFA) にユーザーを登録する必要があります。
- スマート カードの設定の場合、構成されて有効になっている[証明書ベースの認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-certificate-based-authentication)。 証明書が読み込まれているスマートカードと、ローカルアカウントとペアリングされているスマートカード。
- ユーザーは、[登録し、デバイスを Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-macos-platform-single-sign-on-extension?tabs=macOS14#insufficient-permissions) に参加させる十分なアクセス許可を持っている必要があります。
- ネットワーク プロキシのフィルター処理または TLS 検査が環境内で有効になっている場合は、[プラットフォーム シングル Sign-On トラブルシューティング ガイド](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-macos-platform-single-sign-on-extension?tabs=macOS14#tls-inspection-urls-to-be-excluded-for-platform-sso)に記載されている推奨設定を確認してください

### Company Portal を使用した Intune MDM と Microsoft Entra の登録

MAC デバイスを PSSO に登録するには、まず、Company Portal アプリを使用してデバイスをMicrosoft Intuneに登録する必要があります。 登録後は、Secure Enclave、スマート カード、またはパスワードを使用して、PSSO にデバイスを登録できます。

1. **Company Portal** アプリを開き、**Sign in** を選択します。
2. Microsoft Entra ID資格情報を入力し、**Next** を選択します。
3. **{会社} アクセスを設定**するよう求められます。 "会社" のプレースホルダーは、設定によって異なります。 **[開始]** を選択し、次の画面で **[続行]** を選択します。

    [Image: ポータル サイト アクセス設定ウィンドウのスクリーンショット。]
4. 管理プロファイルをインストールする手順が表示されます。 管理プロファイルは、Microsoft Intuneを使用して管理者によって設定されている必要があります。 **[プロファイルのダウンロード]** を選択します。

    [Image: 管理プロファイルのダウンロードをユーザーに要求するCompany Portal ウィンドウのスクリーンショット。]
5. 自動的に表示されない場合は、**[設定]**&gt;**[プライバシーとセキュリティ]**&gt;**[プロファイル]** を開きます。 **[管理プロファイル]** を選択します。

    [Image: ダウンロードした管理プロファイルが表示されている設定アプリのプロファイルのスクリーンショット。]
6. **[インストール]** を選択して、会社のリソースにアクセスします。

    [Image: 設定で管理プロファイルをインストールするためのプロンプトのスクリーンショット。]
7. 表示された **[プロファイル]** ウィンドウにローカル デバイスのパスワードを入力し、**[登録]** を選択します。

    [Image: MDM サービスに登録するためのパスワードを要求する [プロファイル] ウィンドウのスクリーンショット。]
8. **Company Portal**にインストールが完了したことを示す通知が表示されます。 **完了**を選択します。

### プラットフォーム SSO の登録

デバイスがCompany Portalに準拠したら、デバイスを PSSO に登録する必要があります。 **Registration Required** ポップアップが画面の右上に表示され、Intune MDM と Microsoft Entra Join を使用した Company Portal の手順が正常に完了した後に表示されます。 タブを使用し、Secure Enclave、スマート カード、またはパスワードを使用してデバイスを PSSO に登録します。

ヒント

**[Registration Required]\(登録が必要**\) ポップアップが表示されない場合、または操作する前に非表示になる場合は、次の操作を行います。

- **約 10 分待ちます** 。登録の試行が失敗または失敗した後、ポップアップが自動的に再表示されます。
- **サインアウトして** Mac にサインインし直します。これにより、登録通知が再トリガーされます。
- **登録を修復する** (macOS 14 以降) - **設定**&gt;**ユーザーとグループ**&gt;**Network アカウント サーバー**&gt;**編集**&gt;**Repair** に移動して、登録フローを再起動します。
- 登録中に SSO 認証プロンプトを閉じた場合は、サインアウトして再度サインインすると、通知が復元されます。

トラブルシューティングの手順の詳細については、 [プラットフォーム SSO の既知の問題とトラブルシューティングに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-macos-platform-single-sign-on-extension)参照してください。

## [セキュリティで保護されたエンクレーブ](#tab/secure-enclave)
1. 画面の右上にある **[登録が必要]** ポップアップに移動します。 ポップアップにカーソルを合わせ、**[登録]** を選択します。 macOS 14 Sonoma ユーザーの場合、デバイスをMicrosoft Entraに登録するように求めるプロンプトが表示されます。 このプロンプトは macOS 13 Ventura では表示されません。

    [Image: 登録が必要な通知が選択された後に macOS 14 に表示されるMicrosoft Entra登録プロンプトのスクリーンショット。]
2. Touch ID またはパスワードでアカウントのロックを解除したら、サインインするアカウントを選択し、サインイン資格情報を入力して **[次へ]** を選択します。
3. このサインイン フローの一部として MFA が必要です。 **Authenticator アプリ**を開く (推奨) か、登録した他の MFA 方法を使用して、画面に表示される番号を入力して登録を完了します。
4. MFA フローが完了し、読み込み画面が消えたら、デバイスは PSSO に登録されているはずです。 PSSO を使用して Microsoft アプリ リソースにアクセスできるようになりました。

#### macOS のプラットフォーム認証情報をパスキーとして使用できるようにする

Secure Enclave 方法を使用してデバイスを設定すると、Mac に保存された認証情報をブラウザーのパスキーとして使用できるようになります。 有効にするには、次の手順を実行します。

1. **設定** アプリを開き、**[全般]**&gt;**[パスワード]**& オートフィルに移動します。
2. **オートフィルとパスワード** で **オートフィル元** を探し、**Company Portal** をトグルスイッチで有効にします。

    [パスワード オプション] ウィンドウのスクリーンショットは、Company Portalのパスワードとパスキーの使用がスイッチで有効になっていることを示しています。

## [スマート カード](#tab/smart-card)
#### スマート カードをローカル アカウントとペアリングする

デバイスをスマート カードに登録する前に、スマート カードをローカル アカウントとペアリングする必要があります。 **ターミナル** アプリを開き、次のコマンドを実行してスマート カード証明書の公開キー ハッシュを見つけ、ローカル アカウントとペアリングしてから、成功したことを確認します。 これは、`sudo` を使用して実行する必要があります。

```console
sc_auth identities
sudo sc_auth pair -h <HASH> -u <USERNAME>
sc_auth list
```

#### スマート カードにデバイスを登録する

1. 画面の右上にある **[登録が必要]** ポップアップに移動します。 ポップアップにカーソルを合わせ、**[登録]** を選択します。 スマート カードがローカル アカウントとペアリングされている場合は、スマート カードのピンを入力するように求めるメッセージが表示されます

    [Image: ユーザーにスマート カードの PIN を入力するよう求めるプラットフォーム SSO 登録のスクリーンショット。]
2. 管理者がデバイス登録フローに対して MFA を構成しているかどうかを確認します。 その場合は、モバイル デバイスで **Authenticator** アプリを開き、MFA フローを完了します。

    [Image: Microsoft へのサインインを求める登録ウィンドウのスクリーンショット。]
3. 証明書がローカル アカウントとまだペアリングされていない場合は、スマート カードを使用するように求めるメッセージが表示されます。 **[スマート カード]** を選択します。
4. スマート カードの PIN を入力するように求められます。 PIN を入力し、**[スマート カードの PIN の入力]** を選択します。 正しい PIN を入力すると、スマート カード認証による PSSO 登録が完了します。
5. PSSO を使用して Microsoft アプリのリソースにアクセスし、スマート カード PIN を使用してデバイスのロックを解除できるようになりました。 キーチェーン アクセスのロックを解除するには、再起動後にローカル パスワードを使用してログインする必要があります。

## [パスワード](#tab/password)
1. 画面の右上にある **[登録が必要]** ポップアップに移動します。 ポップアップにカーソルを合わせ、**[登録]** を選択します。

    - macOS 13 Ventura ユーザーの場合は、デバイスをMicrosoft Entra IDに登録するためのプロンプトが表示されます。 サインイン資格情報を入力して、**[次へ]** を選びます。

    [Image: 画面の右上に登録が必要なポップアップが表示されたデスクトップ画面のスクリーンショット。]
2. デバイスをMicrosoft Entra IDに登録するように求められます。 サインイン資格情報を入力して、**[次へ]** を選びます。

    1. 管理者がデバイス登録フローに対して MFA を構成しているかどうかを確認します。 その場合は、モバイル デバイスで **Authenticator** アプリを開き、MFA フローを完了します。

    [Image: Microsoft へのサインインを求める登録ウィンドウのスクリーンショット。]
3. **[シングル サインオン]** ウィンドウが表示されたら、ローカル アカウントのパスワードを入力して **[OK]** を選択します。 macOS 14 を使用している場合は、事前にローカル アカウントのロックを解除するように求められます。

    [Image: ユーザーにローカル アカウントのパスワードの入力を求めるシングル サインオン ウィンドウのスクリーンショット。]
4. ローカル パスワードがMicrosoft Entra IDパスワードと異なる場合は、画面の右上に **Authentication Required** ポップアップが表示されます。 バナーにカーソルを合わせ、**[サインイン]** を選択します。
5. **Microsoft Entra** ウィンドウが表示されたら、Microsoft Entra IDパスワードを入力し、**Sign In** を選択します。

    [Image: Microsoft Entra サインイン ウィンドウのスクリーンショット.]
6. Mac のロックを解除すると、PSSO を使用して Microsoft アプリのリソースにアクセスできるようになります。 この時点から、デバイスで PSSO が有効になるため、古いパスワードは機能しなくなります。

---

### デバイスの登録状態を確認する

上記の手順を完了したら、デバイスの登録状態を確認することをお勧めします。

1. 登録が正常に完了したことを確認するには、**[設定]** に移動して **[ユーザーとグループ]** を選択します。
2. **[ネットワーク アカウント サーバー]** の横にある **[編集]** を選択し、**[プラットフォーム SSO]** が **[登録済み]** として一覧表示されていることを確認します。
3. 認証に使用される方法を確認するには、**[ユーザーとグループ]** ウィンドウでユーザー名に移動し、**[情報]** アイコンを選択します。 一覧表示されている方法 (**Secure Enclave**、**スマート カード**、または **パスワード**) を確認します。

    Note

    **ターミナル** アプリを使用して登録状態を確認することもできます。 デバイスの登録状態を確認するには、次のコマンドを実行します。 出力の下部に、SSO トークンが取得されたことが表示されます。 macOS 13 Ventura ユーザーの場合、登録状態を確認するにはこのコマンドが必要です。

    ```console
    app-sso platform -s
    ```

### Mac デバイスを更新して PSSO を有効にする

デバイスが既に Company Portal に登録されている macOS ユーザーの場合、管理者はデバイスの SSO 拡張機能プロファイルを更新することで PSSO を有効にすることができます。 PSSO プロファイルがデバイスに展開されインストールされると、画面の右上にある **[登録が必要]** という通知を介してデバイスを PSSO に登録するように求められます。 これにより、新しい PSSO 登録の代わりに、デバイスから古い SSO 登録が削除されます。

すぐに行うことをお勧めしますが、これを選択して、都合の良いときにデバイスの登録を開始することもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/device-join-out-of-box"} -->
## OOBE 中に Windows 11 デバイスを Microsoft Entra ID に参加させる - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-out-of-box
- Service: entra-id / devices
- Article date: 2026-01-08
- Summary: OOBE 中に Windows 11 デバイスで Microsoft Entra 参加を設定し、組織のディレクトリとのシームレスな統合を確保する方法について説明します。

このチュートリアルでは、すぐに使用できるエクスペリエンス (OOBE) 中に新しい Windows 11 デバイスを Microsoft Entra ID に参加させる方法について説明します。 OOBE 中にデバイスに参加すると、デバイスは組織のディレクトリの一部になり、ポリシーに従って管理できます。

この機能は、 [Microsoft Intune](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/what-is-intune) などのモバイル デバイス管理プラットフォームや [Windows Autopilot](https://learn.microsoft.com/ja-jp/mem/autopilot/windows-autopilot) などのツールとうまく組み合わせて、デバイスが標準に従って構成されていることを確認します。

### [前提条件]

Microsoft Entra が Windows デバイスに参加するには、デバイスを登録できるようにデバイス登録サービスを構成する必要があります。 前提条件の詳細については、「 [方法: Microsoft Entra 参加の実装を計画する」](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-plan)を参照してください。

ヒント

Windows Home エディションでは、Microsoft Entra 参加はサポートされていません。 これらのエディションは、引き続き [Microsoft Entra 登録](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-device-registration)を使用して多くの特典にアクセスできます。

Windows デバイスで Microsoft Entra の登録を完了する方法については、サポート記事「 [職場または学校のネットワークに個人用デバイスを登録](https://support.microsoft.com/account-billing/register-your-personal-device-on-your-work-or-school-network-8803dd61-a613-45e3-ae6c-bd1ab25bf8a8)する」を参照してください。

### 新しい Windows 11 デバイスを Microsoft Entra ID に参加させる

デバイスは、セットアップ プロセスの一環として複数回再起動する場合があります。 Microsoft Entra 参加を完了するには、デバイスがインターネットに接続されている必要があります。

1. 新しいデバイスをオンにして、セットアップ プロセスを開始します。 画面の指示に従って、デバイスを設定します。
2. メッセージが表示されたら、 **このデバイスを設定する方法**を選択し、[ **職場または学校にセットアップ**] を選択します。 [Image: Windows 11 の初期設定体験を示すスクリーンショットで、職場または学校用に設定するオプションがあります。]
3. **[職場または学校の設定を行う**] ページで、組織が指定した資格情報を入力します。 これらの資格情報には、ユーザー名とパスワード、または [パスキー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-synced-passkeys)などのメソッドが含まれる場合があります。 ユーザーは、次のアニメーションに示すように、 [パスキー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-synced-passkeys)を含め、Microsoft Entra ID にサインインするために必要な資格情報を使用できる必要があります。

    [Image: パスキーを使用して Windows 11 にすぐにサインインする方法のアニメーション。]
4. 画面の指示に従ってデバイスをセットアップします。
5. Microsoft Entra ID は、モバイル デバイス管理の登録が必要かどうかを確認し、プロセスを開始します。

    1. Windows は、組織のディレクトリにデバイスを登録し、必要に応じてモバイル デバイス管理に登録します。
6. マネージド ユーザー アカウントでサインインすると、Windows は自動サインイン プロセスを通じてデスクトップに移動します。 フェデレーション ユーザーは、Windows サインイン画面に移動して資格情報を入力します。 [Image: Microsoft Entra に参加後、初回実行時のデスクトップでの Windows 11 のスクリーンショット。]

すぐに使用できるエクスペリエンスの詳細については、サポート記事「 [職場または学校のネットワークに職場デバイスを参加](https://support.microsoft.com/account-billing/join-your-work-device-to-your-work-or-school-network-ef4d6adb-5095-4e51-829e-5457430f3973)させる」を参照してください。

### 検証

デバイスが Microsoft Entra ID に参加しているかどうかを確認するには、[**設定]**&gt; にある Windows デバイスの [**職場または学校へのアクセス**] ダイアログを確認します。 ダイアログは、Microsoft Entra ID に接続されていることを示し、IT スタッフが管理する領域に関する情報を提供します。

[Image: 現在の接続を示す Windows 11 設定アプリのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/device-join-plan"} -->
## Microsoft Entra への参加の展開を計画する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-plan
- Service: entra-id / devices
- Article date: 2026-06-17
- Summary: 環境内に Microsoft Entra 参加済みデバイスを実装するために必要な手順について説明します。

ユーザーの生産性とセキュリティを維持しながら、オンプレミスの Active Directory に参加しなくても、デバイスを Microsoft Entra ID に直接参加させることができます。 Microsoft Entra への参加は、大規模展開と対象を限定した展開の両方で、エンタープライズ利用に対応しています。 オンプレミスのリソースへの シングル サインオン (SSO) アクセスは、Microsoft Entra 参加済みデバイスでも使用できます。 詳しくは、「[Microsoft Entra 参加済みデバイス上でオンプレミス リソースへの SSO が機能するしくみ](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-sso-to-on-premises-resources)」をご覧ください。

この記事では、Microsoft Entra 参加の実装を計画するために必要な情報を提供します。

### 前提条件

この記事では、[Microsoft Entra ID でのデバイス管理の概要](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)を理解していることを前提とします。

### 実装の計画

Microsoft Entra への参加の実装を計画するには、以下について理解を深めておく必要があります。

- シナリオをレビューする
- ID インフラストラクチャをレビューする
- デバイス管理を評価する
- アプリケーションとリソースに関する考慮事項を把握する
- プロビジョニングのオプションを把握する
- エンタープライズ状態ローミングを構成する
- 条件付きアクセスを構成する

### シナリオをレビューする

Microsoft Entra 参加により、Windows でのクラウド中心モデルに移行できます。 デバイス管理を最新化し、デバイス関連の IT コストを削減することを計画している場合、Microsoft Entra join は、これらの目標を達成するための優れた基盤を提供します。

目標が次の条件と一致する場合は、Microsoft Entra 参加を検討してください。

- ユーザー用の生産性スイートとして Microsoft 365 を採用している。
- クラウド デバイス管理ソリューションを使用してデバイスを管理したい。
- 地理的に分散したユーザーのデバイス プロビジョニングを簡略化したい。
- アプリケーション インフラストラクチャの最新化を計画している。

### ID インフラストラクチャをレビューする

Microsoft Entra 参加は、マネージド環境とフェデレーション環境の両方で動作します。 ほとんどの組織は、マネージド ドメインをデプロイします。 マネージド ドメインのシナリオでは、Active Directory フェデレーション サービス (AD FS) などのフェデレーション サーバーを構成および管理する必要はありません。

#### マネージド環境

マネージド環境は、[パスワード ハッシュ同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization)または[パススルー認証](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-quick-start)のいずれかとシームレス シングル サインオンを使用してデプロイできます。

#### フェデレーション環境

フェデレーション環境には、WS-Trust と Ws-Fed の両方のプロトコルをサポートしている ID プロバイダーが必要です。

- **WS-Fed:** このプロトコルは、デバイスを Microsoft Entra ID に参加させるために必要です。
- **WS-Trust:** このプロトコルは、Microsoft Entra 参加済みデバイスにサインインするために必要です。

AD FS を使用している場合は、次の WS-Trust エンドポイントを有効にする必要があります。`/adfs/services/trust/2005/usernamemixed``/adfs/services/trust/13/usernamemixed``/adfs/services/trust/2005/certificatemixed``/adfs/services/trust/13/certificatemixed`

ID プロバイダーによってこれらのプロトコルがサポートされていない場合、Microsoft Entra 参加はネイティブには機能しません。

注

現時点で、Microsoft Entra 参加は[プライマリ認証方法として外部認証プロバイダーで構成されている AD FS 2019](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/additional-authentication-methods-ad-fs#enable-external-authentication-methods-as-primary) では機能しません。 Microsoft Entra 参加は、プライマリ メソッドの既定値としてパスワード認証が設定され、その結果、このシナリオで認証エラーが発生します

#### ユーザー構成

ユーザーを以下の場所に作成する場合:

- **オンプレミスの Active Directory** では、[Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis) を使用して、それらを Microsoft Entra ID に同期する必要があります。
- **Microsoft Entra ID** では、追加のセットアップは必要ありません。

Microsoft Entra の UPN とは異なるオンプレミスのユーザー プリンシパル名 (UPN) は、Microsoft Entra 参加済みデバイスでサポートされていません。 お客様のユーザーがオンプレミスの UPN を使用している場合は、Microsoft Entra ID 内でプライマリ UPN を使用するように切り替えることを計画してください。

UPN の変更は、Windows 10 2004 Update 以降でのみサポートされます。 この更新プログラムがインストールされたデバイスのユーザーには、UPN の変更後も問題は発生しません。 Windows 10 2004 アップデート以前のデバイスの場合は、ユーザーのデバイスで SSO や条件付きアクセスに関する問題が発生する可能性があります。 この問題を解決するために、ユーザーは新しい UPN を使用して [他のユーザー] タイルから Windows にサインインする必要があります。

### デバイス管理を評価する

#### サポートされているデバイス

Microsoft Entra の結合:

- Windows 10 と Windows 11 のデバイスをサポートします。
- 以前のバージョンの Windows や他のオペレーティング システムではサポートされていません。 Windows 7 または 8.1 のデバイスを使用している場合は、Windows 10 以上にアップグレードして Microsoft Entra 参加をデプロイする必要があります。
- Federal Information Processing Standards (FIPS) 準拠のトラステッド プラットフォーム モジュール (TPM) 2.0 ではサポートされていますが、TPM 1.2 ではサポートされていません。 FIPS 準拠の TPM 1.2 がデバイスにある場合は、Microsoft Entra 参加を進める前に、それらを無効にする必要があります。 TPM の FIPS モードを無効にするためのツールは、TPM の製造元に依存するため、Microsoft では提供していません。 サポートが必要な場合は、お使いのハードウェアの OEM にお問い合わせください。

**推奨事項:** 更新された機能を利用するために、常に最新の Windows リリースを使用してください。

#### 管理プラットフォーム

Microsoft Entra 参加済みデバイスのデバイス管理は、Intune、MDM CSP などのモバイル デバイス管理 (MDM) プラットフォームに基づいています。 Windows 10 以降には、互換性のあるすべての MDM ソリューションで動作する MDM エージェントが組み込まれています。

注

グループ ポリシーは、オンプレミスの Active Directory に接続されていないため、Microsoft Entra 参加済みデバイスではサポートされません。 Microsoft Entra 参加済みデバイスの管理は、MDM 経由でのみ可能です。

Microsoft Entra 参加済みデバイスの管理には 2 つのアプローチがあります。

- **MDM のみ** - デバイスは、Intune などの MDM プロバイダーによってのみ管理されます。 すべてのポリシーは、MDM の登録プロセスの一環として配信されます。 Microsoft Entra ID P1 または P2、または EMS のお客様の場合、MDM 登録は、Microsoft Entra 参加の一部として自動的に行われる手順です。
- **共同管理** - デバイスは、MDM プロバイダーと Microsoft Configuration Manager によって管理されます。 このアプローチでは、Microsoft Configuration Manager エージェントは、MDM によって管理されるデバイスにインストールされて、特定の側面を管理します。

グループ ポリシーを使っている場合は、Microsoft Intune の[グループ ポリシー分析](https://learn.microsoft.com/ja-jp/mem/intune/configuration/group-policy-analytics)を使って、グループ ポリシー オブジェクト (GPO) と MDM ポリシーのパリティを評価します。

サポート対象のポリシーとサポート対象外のポリシーを確認して、グループ ポリシーの代わりに MDM ソリューションを使用できるかどうかを判断します。 サポート対象外のポリシーの場合は、以下を検討してください。

- Microsoft Entra 参加済みのデバイスまたはユーザーにサポート対象外のポリシーが必要かどうか。
- サポート対象外のポリシーはクラウド主導のデプロイに適用できるか?

MDM ソリューションを Microsoft Entra アプリ ギャラリーから入手できない場合は、「[Microsoft Entra と MDM の統合](https://learn.microsoft.com/ja-jp/windows/client-management/azure-active-directory-integration-with-mdm)」に概説されているプロセスに従って追加できます。

共同管理では、Microsoft Configuration Manager を使用してデバイスの特定の側面を管理しながら、MDM プラットフォームを通じてポリシーを配信できます。 Microsoft Intune は、Microsoft Configuration Manager との共同管理を可能にします。 Windows 10 以降のデバイスの共同管理については、「[共同管理とは](https://learn.microsoft.com/ja-jp/mem/configmgr/comanage/overview)」を参照してください。 Intune 以外の MDM 製品を使用する場合は、適用可能な共同管理のシナリオについて MDM のプロバイダーにお問い合わせください。

**推奨事項:** Microsoft Entra 参加済みデバイスについては MDM のみの管理をご検討ください。

### アプリケーションとリソースに関する考慮事項を把握する

ユーザー エクスペリエンスとアクセスの制御を向上させるために、アプリケーションをオンプレミスからクラウドに移行することをお勧めします。 Microsoft Entra 参加済みデバイスは、オンプレミスアプリケーションとクラウド アプリケーションの両方にシームレスにアクセスできます。 詳しくは、「[Microsoft Entra 参加済みデバイス上でオンプレミス リソースへの SSO が機能するしくみ](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-sso-to-on-premises-resources)」をご覧ください。

以下のセクションでは、さまざまな種類のアプリケーションとリソースに関する考慮事項を示します。

#### クラウドベース アプリケーション

Microsoft Entra アプリ ギャラリーにアプリケーションが追加されている場合、ユーザーは Microsoft Entra 参加済みデバイスから SSO を取得できます。 それ以上の構成は必要ありません。 ユーザーは、Microsoft Edge ブラウザーと Chrome ブラウザーの両方で SSO を取得します。 Chrome の場合は、[Windows 10 アカウントの拡張機能](https://chrome.google.com/webstore/detail/windows-10-accounts/ppnbnpeolgkicgegkbkbjmhlideopiji)をデプロイする必要があります。

次のすべての Win32 アプリケーション:

- トークン要求には Web Account Manager (WAM) を利用すると、Microsoft Entra に参加しているデバイスで SSO も利用できます。
- WAM に依存しないでください。ユーザーに認証を求める場合があります。

#### オンプレミスの Web アプリケーション

アプリがカスタム ビルドの場合、またはオンプレミスでホストされている場合は、以下の目的でそれらのアプリをブラウザーの信頼済みサイトに追加する必要があります。

- Windows 統合認証を動作させる
- プロンプトなしの SSO エクスペリエンスをユーザーに提供する。

AD FS を使用する場合は、「[AD FS によるシングル サインオンを確認および管理する](https://learn.microsoft.com/ja-jp/previous-versions/azure/azure-services/jj151809%28v%3dazure.100%29)」をご覧ください。

**推奨事項:** エクスペリエンスを向上させるために、クラウド (たとえば、Azure) にホストし、Microsoft Entra ID と統合することをご検討ください。

#### 従来のプロトコルに依存するオンプレミスのアプリケーション

デバイスがドメイン コントローラーへのアクセス権を持つ場合、ユーザーは Microsoft Entra 参加済みデバイスから SSO を取得します。

注

Microsoft Entra 参加済みデバイスは、オンプレミスアプリケーションとクラウド アプリケーションの両方にシームレスにアクセスできます。 詳しくは、「[Microsoft Entra 参加済みデバイス上でオンプレミス リソースへの SSO が機能するしくみ](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-sso-to-on-premises-resources)」をご覧ください。

**推奨事項:**[Microsoft Entra アプリのプロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy)をデプロイして、これらのアプリケーションへのアクセスをセキュリティで保護できるようにします。

#### オンプレミスのネットワーク共有

デバイスにオンプレミスのドメイン コントローラーへのアクセス権がある場合、ユーザーには Microsoft Entra 参加済みデバイスから SSO を取得します。 この動作のしくみは[こちら](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-sso-to-on-premises-resources)を参照してください

#### プリンター

オンプレミスの依存関係を持たないクラウドベースの印刷管理ソリューションを使用するには、[ユニバーサル印刷](https://learn.microsoft.com/ja-jp/universal-print/fundamentals/universal-print-whatis)をデプロイすることをお勧めします。

#### マシン認証に依存するオンプレミスのアプリケーション

Microsoft Entra 参加済みデバイスでは、マシンの認証に依存するオンプレミスのアプリケーションはサポートされていません。

**推奨事項:** これらのアプリケーションを廃止し、最新の代替アプリケーションに移行することをご検討ください。

#### リモート デスクトップ サービス

Microsoft Entra 参加済みデバイスへのリモート デスクトップ接続では、ホスト コンピューターが Microsoft Entra 参加済みであるか、Microsoft Entra ハイブリッド参加済みである必要があります。 参加していないデバイスまたは Windows 以外のデバイスからのリモート デスクトップはサポートされていません。 詳しくは、「[リモート Microsoft Entra 参加済み PC に接続する](https://learn.microsoft.com/ja-jp/windows/client-management/client-tools/connect-to-remote-aadj-pc)」をご覧ください

Windows 10 2004 更新プログラム以降、ユーザーは Microsoft Entra 登録済み Windows 10 以降のデバイスから別の Microsoft Entra 参加済みデバイスへのリモート デスクトップを使用できるようになります。

#### RADIUS と Wi-Fi 認証

現在、Microsoft Entra 参加済みデバイスは、Wi-Fi アクセス ポイントに接続するためのオンプレミス コンピューター オブジェクトと証明書を使った RADIUS 認証をサポートしていません。これは、このシナリオでは RADIUS がオンプレミス コンピューター オブジェクトの存在に依存しているためです。 代わりに、Intune 経由でプッシュされる証明書またはユーザー資格情報を使用して Wi-Fi に対する認証を行います。

### プロビジョニングのオプションを把握する

注

Microsoft Entra 参加済みデバイスは、システム準備ツール (Sysprep) または同様のイメージング ツールを使用してデプロイすることはできません。

次のアプローチを使用して Microsoft Entra 参加済みデバイスをプロビジョニングできます。

- **OOBE/設定でのセルフサービス** - セルフサービス モードでは、ユーザーは、Windows Out of Box Experience (OOBE) の実行中、または Windows 設定から、Microsoft Entra 参加のプロセスを実行します。 詳しくは、「[職場のデバイスを組織のネットワークに参加させる](https://support.microsoft.com/account-billing/join-your-work-device-to-your-work-or-school-network-ef4d6adb-5095-4e51-829e-5457430f3973)」をご覧ください。
- **Windows Autopilot** - Windows Autopilot では、OOBE での Microsoft Entra 参加エクスペリエンスを円滑にするためにデバイスの事前構成を行うことができます。 詳しくは、「[Windows Autopilot の概要](https://learn.microsoft.com/ja-jp/windows/deployment/windows-autopilot/windows-10-autopilot)」をご覧ください。
- **一括登録** - 一括登録により、一括プロビジョニング ツールを使用してデバイスを構成することで、管理者主導の Microsoft Entra 参加が可能になります。 詳しくは、「[Windows デバイスの一括登録](https://learn.microsoft.com/ja-jp/mem/intune/enrollment/windows-bulk-enroll)」をご覧ください。

これら 3 つのアプローチの比較を次に示します。

| 要素 | セルフサービス セットアップ | Windows オートパイロット | 一括登録 |
| --- | --- | --- | --- |
| セットアップにユーザーの操作が必要 | はい | はい | いいえ |
| IT 部門の作業が必要 | いいえ | はい | はい |
| 適用可能なフロー | OOBE と設定 | OOBE のみ | OOBE のみ |
| プライマリ ユーザーに対するローカル管理者権限 | 既定では、はい | 設定可能 | いいえ |
| デバイス OEM のサポートが必要 | いいえ | はい | いいえ |
| サポートされているバージョン | 1511+ | 1709+ | 1703+ |

注

セルフサービス セットアップでは、Microsoft Entra参加を完了したユーザーがデバイスのプライマリ ユーザーになり、既定でそのデバイスのローカル管理者になります。 このローカル管理者の状態は、デバイスにのみ適用されます。 Microsoft Entra ディレクトリ ロールは付与されません。 Windows Autopilotの場合、ユーザー アカウントの種類は構成可能です。 一括登録では、デバイスがローカル Administrators グループに参加した後にサインインするユーザーは追加されません。

前の表を確認し、どのアプローチを採用するかについて次の考慮事項を検討して、1 つまたは複数のデプロイ アプローチを選択します。

- ユーザーは技術的な知識に精通していてセットアップを自分で実行できるか?
    - これらのユーザーにはセルフサービスが最適です。 ユーザー エクスペリエンスを強化するために Windows Autopilot をご検討ください。
- ユーザーはリモートと企業プレミス内のどちらにいるか?
    - 手間をかけずにセットアップするために、リモート ユーザーにはセルフサービスまたは Autopilot が最適です。
- ユーザー主導の構成と管理者が管理する構成のどちらを優先するか?
    - 管理者主導のデプロイで、ユーザーに渡す前にデバイスをセットアップするには、一括登録が適しています。
- 1 社か 2 社の OEM からデバイスを購入するか、それとも広範な OEM のデバイスを使用するか?
    - Autopilot もサポートしている限られた OEM から購入する場合は、Autopilot とのより緊密な統合を活用できます。

### デバイス設定を構成する

[Microsoft Entra 管理センター](https://entra.microsoft.com) を使用すると、組織内の Microsoft Entra 参加済みデバイスのデプロイを制御できます。 関連する設定を構成するには、**Entra ID**&gt;**Devices**&gt;**All devices**&gt;**Device 設定**にアクセスします。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-device-identities)

#### ユーザーがデバイスを Microsoft Entra ID に参加させる可能性がある

デプロイのスコープと、どのユーザーに Microsoft Entra 参加済みデバイスをセットアップさせるかに基づいて、このオプションを **[すべて]** または **[選択]** に設定します。

[Image: ユーザーがデバイスを Microsoft Entra ID に参加させる可能性がある]

#### Microsoft Entra 参加済みデバイスにおけるローカル管理者の追加

**[選択]** を選んで、すべての Microsoft Entra 参加済みデバイス上のローカル管理者のグループに追加するユーザーを選びます。

[Image: Microsoft Entra 参加済みデバイスにおけるローカル管理者の追加]

#### デバイスを参加させるには多要素認証 (MFA) が必要

デバイスを Microsoft Entra ID に参加させるときにユーザーに MFA を要求する場合は、[ **はい** ] を選択します。

[Image: デバイスを参加させるには多要素認証が必要]

**推奨事項:** デバイスを参加させるために MFA を適用するには、条件付きアクセスでユーザー操作「[デバイスの登録または参加](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#user-actions)」を使用します。

### モビリティ設定の構成

モビリティ設定を構成するには、最初に MDM プロバイダーを追加することが必要な場合があります。

**MDM プロバイダーを追加するには**:

1. **[Microsoft Entra ID] ページ**の **[管理]** セクションで、`Mobility (MDM and MAM)` を選択します。
2. **[アプリケーションの追加]** をクリックします。
3. 一覧から、使用する MDM プロバイダーを選択します。

    [Image: Microsoft Entra ID の [アプリケーションの追加] ページを示すスクリーンショット。複数の MDM プロバイダーが一覧表示されている。]

MDM プロバイダーを選択して関連設定を構成します。

#### MDM ユーザーの範囲

デプロイのスコープに基づいて **[一部]** または **[すべて]** を選択します。

[Image: MDM ユーザー スコープ]

スコープに応じて、次のいずれかが発生します。

- **ユーザーが MDM スコープ内にある**: Microsoft Entra ID P1 または P2 サブスクリプションがある場合、MDM 登録は Microsoft Entra 参加と共に自動的に行われます。 スコープ内のすべてのユーザーには、MDM に対する適切なライセンスが必要です。 このシナリオで MDM の登録に失敗した場合は、Microsoft Entra 参加がロールバックされます。
- **ユーザーが MDM のスコープ内にない**: ユーザーが MDM のスコープ内にない場合、Microsoft Entra 参加は MDM 登録なしで完了します。 このスコープでは、アンマネージド デバイスが生じます。

#### MDM URL一覧

MDM の構成に関連する URL は 3 つあります。

- MDM 使用条件 URL
- MDM 検出 URL
- MDM 準拠 URL

[Image: Microsoft Entra MDM 構成セクションの一部に、MDM の使用条件、検出、コンプライアンスのための URL フィールドがあるスクリーンショット。]

各 URL には、定義済みの既定値があります。 これらのフィールドが空の場合、詳細については MDM プロバイダーに問い合わせてください。

#### MAM 設定

モバイル アプリケーション管理 (MAM) は、Microsoft Entra 参加には適用されません。

### エンタープライズ状態ローミングを構成する

Microsoft Entra ID に対して状態ローミングを有効にして、ユーザーがデバイス間で設定を同期できるようにするには、「[Microsoft Entra ID の Enterprise State Roaming を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/devices/enterprise-state-roaming-enable)」をご覧ください。

**推奨事項**: この設定は、Microsoft Entra ハイブリッド参加済みデバイスに対しても有効にしてください。

### 条件付きアクセスを構成する

Microsoft Entra 参加済みデバイスに対して MDM プロバイダーが構成されている場合、プロバイダーは、デバイスが管理下に入るとすぐにデバイスに準拠のフラグを設定します。

[Image: 準拠デバイス]

この実装を使用して、[条件付きアクセスを使用してクラウド アプリへのアクセスにマネージド デバイスを要求する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)ことができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/device-registration-how-it-works"} -->
## Microsoft Entra デバイス登録のしくみ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/device-registration-how-it-works
- Service: entra-id / devices
- Article date: 2025-06-27
- Summary: マネージドおよびフェデレーション ドメインのための Microsoft Entra デバイス登録フロー。

デバイス登録は、クラウドベース認証の前提条件です。 一般的に、デバイスは Microsoft Entra ID または Microsoft Entra ハイブリッドに連携してデバイスの登録を完了します。 この記事では、マネージドおよびフェデレーションの各環境での Microsoft Entra への参加と Microsoft Entra ハイブリッドへの参加のしくみについて詳しく説明します。 これらのデバイスでの Microsoft Entra 認証の動作の詳細については、[プライマリ更新トークン](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token#detailed-flows)に関する記事を参照してください。

### Microsoft Entra は管理環境に参加しました

[Image: マネージド環境での Microsoft Entra 参加デバイス フロー]

| フェーズ | 説明 |
| --- | --- |
| A | Microsoft Entra 参加デバイスの登録の最も一般的な方法は、OOBE (Out-Of-Box Experience) 中に、クラウド エクスペリエンス ホスト (CXH) アプリケーションに Microsoft Entra 参加 Web アプリケーションを読み込む方法です。 承認エンドポイントを検出するために、このアプリケーションから Microsoft Entra OpenID 構成エンドポイントに GET 要求が送信されます。 承認エンドポイントを含む OpenID 構成が、JSON ドキュメントとして Microsoft Entra ID からアプリケーションに返されます。 |
| B | アプリケーションで承認エンドポイント用のサインイン要求が作成され、ユーザーの資格情報が収集されます。 |
| C | ユーザーがユーザー プリンシパル名 (UPN) を指定すると、アプリケーションはユーザーの対応する領域情報を検出するために GET 要求を Microsoft Entra ID に送信します。 この情報により、環境がマネージドまたはフェデレーションのいずれであるかが判明します。 Microsoft Entra ID は、情報を JSON オブジェクトで返します。 アプリケーションでは、環境がマネージド (非フェデレーション) であると判断します。このフェーズの最後の手順では、アプリケーションで認証バッファーが作成され、OOBE 中の場合、OOBE の最後に自動サインインするために一時的にキャッシュされます。 アプリケーションが 資格情報を Microsoft Entra ID に POST し、そこで検証されます。 Microsoft Entra ID は、クレームを含む ID トークンを返します。 |
| D | アプリケーションは、モバイル デバイス管理 (MDM) の使用条件 (mdm\_tou\_url 要求) を検索します。 存在する場合、アプリケーションは要求の値から使用条件を取得して内容をユーザーに提示します。ユーザーが使用条件に同意するまで待機します。 この手順は省略可能であり、要求が存在しない場合、または要求値が空の場合はスキップされます。 |
| E | アプリケーションは、デバイス登録検出要求を Azure Device Registration Service (DRS) に送信します。 Azure DRS は、デバイス登録を完了するためのテナント固有の URI を返す探索データ ドキュメントを返します。 |
| F | アプリケーションでは、デバイス キー (dkpub/dkpriv) と呼ばれる TPM にバインドされた (推奨) RSA 2048 ビットのキー ペアが作成されます。 アプリケーションは、dkpub と公開キーを使用して証明書要求を作成し、dkpriv を使用して証明書要求に署名します。 次に、アプリケーションでは TPM のストレージ ルート キーから 2 番目のキー ペアが派生されます。 このキーはトランスポート キー (tkpub/tkpriv) です。 |
| G | アプリケーションは、ID トークン、証明書要求、tkpub、構成証明データを含むデバイス登録要求を Azure DRS に送信します。 Azure DRS では、ID トークンが検証され、デバイス ID が作成され、含まれている証明書要求に基づいて証明書が作成されます。 その後、Azure DRS はデバイス オブジェクトを Microsoft Entra ID に書き込み、デバイス ID とデバイス証明書をクライアントに送信します。 |
| H | Azure DRS からデバイス ID とデバイス証明書を受け取ることによってデバイス登録が完了します。 デバイス ID は将来の参照用に保存され (`dsregcmd.exe /status` から参照できます)、デバイス証明書はコンピューターの個人用ストアにインストールされます。 デバイスの登録が完了すると、プロセスは MDM 登録に進みます。 |

### フェデレーション環境での Microsoft Entra への参加

[Image: フェデレーション環境での Microsoft Entra 参加デバイス フロー]

| フェーズ | 説明 |
| --- | --- |
| A | Microsoft Entra 参加デバイスの登録の最も一般的な方法は、OOBE (Out-Of-Box Experience) 中に、クラウド エクスペリエンス ホスト (CXH) アプリケーションに Microsoft Entra 参加 Web アプリケーションを読み込む方法です。 承認エンドポイントを検出するために、このアプリケーションから Microsoft Entra OpenID 構成エンドポイントに GET 要求が送信されます。 承認エンドポイントを含む OpenID 構成が、JSON ドキュメントとして Microsoft Entra ID からアプリケーションに返されます。 |
| B | アプリケーションで承認エンドポイント用のサインイン要求が作成され、ユーザーの資格情報が収集されます。 |
| C | ユーザーがユーザー名を (UPN 形式で) 指定すると、アプリケーションはユーザーの対応する領域情報を検出するために GET 要求を Microsoft Entra ID に送信します。 この情報により、環境がマネージドまたはフェデレーションのいずれであるかが判明します。 Microsoft Entra ID は、情報を JSON オブジェクトで返します。 アプリケーションでは、環境がフェデレーションであると判断されます。アプリケーションは、返された JSON 領域オブジェクトの AuthURL 値 (オンプレミスの STS サインイン ページ) にリダイレクトします。 アプリケーションでは、STS Web ページにより資格情報が収集されます。 |
| D | アプリケーションは、資格情報をオンプレミスの STS に POST します。この場合、追加の認証要素が必要な場合があります。 オンプレミスの STS でユーザーが認証され、トークンが返されます。 アプリケーションは、認証のためにトークンを Microsoft Entra ID に POST します。 Microsoft Entra ID でトークンが検証され、クレームを含む ID トークンが返されます。 |
| E | アプリケーションは、MDM の使用条件 (mdm\_tou\_url 要求) を検索します。 存在する場合、アプリケーションは要求の値から使用条件を取得して内容をユーザーに提示します。ユーザーが使用条件に同意するまで待機します。 この手順は省略可能であり、要求が存在しない場合、または要求値が空の場合はスキップされます。 |
| F | アプリケーションは、デバイス登録検出要求を Azure Device Registration Service (DRS) に送信します。 Azure DRS は、デバイス登録を完了するためのテナント固有の URI を返す探索データ ドキュメントを返します。 |
| G | アプリケーションでは、デバイス キー (dkpub/dkpriv) と呼ばれる TPM にバインドされた (推奨) RSA 2048 ビットのキー ペアが作成されます。 アプリケーションは、dkpub と公開キーを使用して証明書要求を作成し、dkpriv を使用して証明書要求に署名します。 次に、アプリケーションでは TPM のストレージ ルート キーから 2 番目のキー ペアが派生されます。 このキーはトランスポート キー (tkpub/tkpriv) です。 |
| H | アプリケーションは、ID トークン、証明書要求、tkpub、構成証明データを含むデバイス登録要求を Azure DRS に送信します。 Azure DRS では、ID トークンが検証され、デバイス ID が作成され、含まれている証明書要求に基づいて証明書が作成されます。 その後、Azure DRS はデバイス オブジェクトを Microsoft Entra ID に書き込み、デバイス ID とデバイス証明書をクライアントに送信します。 |
| I | Azure DRS からデバイス ID とデバイス証明書を受け取ることによってデバイス登録が完了します。 デバイス ID は将来の参照用に保存され (`dsregcmd.exe /status` から参照できます)、デバイス証明書はコンピューターの個人用ストアにインストールされます。 デバイスの登録が完了すると、プロセスは MDM 登録に進みます。 |

### マネージド環境での Microsoft Entra ハイブリッドへの参加

[Image: マネージド環境での Microsoft Entra ハイブリッド参加のデバイス フローのスクリーンショット。]

| フェーズ | 説明 |
| --- | --- |
| A | ユーザーはドメイン資格情報を使用して、ドメイン参加済みの Windows 10 以降のコンピューターにサインインします。 この資格情報には、ユーザー名とパスワード、またはスマート カード認証を使用できます。 ユーザー サインインによって、デバイスの自動参加タスクがトリガーされます。 デバイスの自動参加タスクはドメイン参加時にトリガーされ、1 時間ごとに再試行されます。 これはユーザー サインインのみには依存しません。 |
| B | タスクは、Active Directory の構成パーティションに格納されているサービス接続ポイントのキーワード属性に対して LDAP プロトコルを使用して Active Directory にクエリを実行します (`CN=62a0ff2e-97b9-4513-943f-0d221bd30080,CN=Device Registration Configuration,CN=Services,CN=Configuration,DC=corp,DC=contoso,DC=com`)。 キーワード属性で返される値によって、デバイス登録の宛先が Azure Device Registration Service (DRS) かまたはオンプレミスでホストされているエンタープライズ デバイス登録サービスかが決定します。 |
| C | マネージド環境では、タスクにより自己署名証明書の形式で初期認証資格情報が作成されます。 このタスクでは、LDAP を使用して Active Directory の computer オブジェクトの userCertificate 属性に証明書を書き込みます。 |
| D | userCertificate 属性に証明書が含まれているコンピューターを表すデバイス オブジェクトが Microsoft Entra ID に作成されるまで、コンピューターは Azure DRS に対して認証を行うことができません。 Microsoft Entra Connect で、属性の変更が検出されます。 次の同期サイクルで、Microsoft Entra Connect は userCcertificate、オブジェクト GUID、コンピューター SID を Azure DRS に送信します。 Azure DRS は、属性情報を使用して Microsoft Entra ID にデバイス オブジェクトを作成します。 |
| E | デバイスの自動参加タスクは、ユーザーのサインインごと、または 1 時間ごとにトリガーされ、userCertificate 属性内の公開キーの対応する秘密キーを使用して Microsoft Entra ID でコンピューターの認証を試みます。 Microsoft Entra ではコンピューターが認証され、コンピューターに ID トークンが発行されます。 |
| F | タスクでは、デバイス キー (dkpub/dkpriv) と呼ばれる TPM にバインドされた (推奨) RSA 2048 ビットのキー ペアが作成されます。 アプリケーションは、dkpub と公開キーを使用して証明書要求を作成し、dkpriv を使用して証明書要求に署名します。 次に、アプリケーションでは TPM のストレージ ルート キーから 2 番目のキー ペアが派生されます。 このキーはトランスポート キー (tkpub/tkpriv) です。 |
| G | タスクでは、ID トークン、証明書要求、tkpub、構成証明データを含むデバイス登録要求が Azure DRS に送信されます。 Azure DRS では、ID トークンが検証され、デバイス ID が作成され、含まれている証明書要求に基づいて証明書が作成されます。 その後、Azure DRS はデバイス オブジェクトを Microsoft Entra ID で更新し、デバイス ID とデバイス証明書をクライアントに送信します。 |
| H | Azure DRS からデバイス ID とデバイス証明書を受け取ることによってデバイス登録が完了します。 デバイス ID は将来の参照用に保存され (`dsregcmd.exe /status` から参照できます)、デバイス証明書はコンピューターの個人用ストアにインストールされます。 デバイスの登録が完了すると、タスクは終了します。 |

### フェデレーション環境での Microsoft Entra ハイブリッドへの参加

[Image: マネージド環境での Microsoft Entra ハイブリッド参加デバイス フロー]

| フェーズ | 説明 |
| --- | --- |
| A | ユーザーはドメイン資格情報を使用して、ドメイン参加済みの Windows 10 以降のコンピューターにサインインします。 この資格情報には、ユーザー名とパスワード、またはスマート カード認証を使用できます。 ユーザー サインインによって、デバイスの自動参加タスクがトリガーされます。 デバイスの自動参加タスクはドメイン参加時にトリガーされ、1 時間ごとに再試行されます。 これはユーザー サインインのみには依存しません。 |
| B | タスクは、Active Directory の構成パーティションに格納されているサービス接続ポイントのキーワード属性に対して LDAP プロトコルを使用して Active Directory にクエリを実行します (`CN=62a0ff2e-97b9-4513-943f-0d221bd30080,CN=Device Registration Configuration,CN=Services,CN=Configuration,DC=corp,DC=contoso,DC=com`)。 キーワード属性で返される値によって、デバイス登録の宛先が Azure Device Registration Service (DRS) かまたはオンプレミスでホストされているエンタープライズ デバイス登録サービスかが決定します。 |
| C | フェデレーション環境の場合、コンピューターでは Windows 統合認証を使用してエンタープライズ デバイス登録エンドポイントが認証されます。 エンタープライズ デバイス登録サービスは、オブジェクト GUID、コンピューター SID、およびドメイン参加状態の要求を含むトークンを作成して返します。 タスクによりトークンとクレームが Microsoft Entra ID に送信され、そこで検証されます。 Microsoft Entra ID は実行中のタスクに ID トークンを返します。 |
| D | アプリケーションでは、デバイス キー (dkpub/dkpriv) と呼ばれる TPM にバインドされた (推奨) RSA 2048 ビットのキー ペアが作成されます。 アプリケーションは、dkpub と公開キーを使用して証明書要求を作成し、dkpriv を使用して証明書要求に署名します。 次に、アプリケーションでは TPM のストレージ ルート キーから 2 番目のキー ペアが派生されます。 このキーはトランスポート キー (tkpub/tkpriv) です。 |
| E | オンプレミスのフェデレーション アプリケーションに SSO を提供するために、タスクは、オンプレミス STS からエンタープライズ PRT を要求します。 Active Directory フェデレーション サービス (AD FS) ロールを実行する Windows Server 2016 で要求が検証され、実行中のタスクに返されます。 |
| F | タスクでは、ID トークン、証明書要求、tkpub、構成証明データを含むデバイス登録要求が Azure DRS に送信されます。 Azure DRS では、ID トークンが検証され、デバイス ID が作成され、含まれている証明書要求に基づいて証明書が作成されます。 その後、Azure DRS はデバイス オブジェクトを Microsoft Entra ID に書き込み、デバイス ID とデバイス証明書をクライアントに送信します。 Azure DRS からデバイス ID とデバイス証明書を受け取ることによってデバイス登録が完了します。 デバイス ID は将来の参照用に保存され (`dsregcmd.exe /status` から参照できます)、デバイス証明書はコンピューターの個人用ストアにインストールされます。 デバイスの登録が完了すると、タスクは終了します。 |
| G | Microsoft Entra Connect のデバイス ライトバックが有効になっている場合、Microsoft Entra Connect では次の同期サイクルで Microsoft Entra ID からの更新を要求します (証明書の信頼を使用するハイブリッド デプロイではデバイス ライトバックが必須です)。 Microsoft Entra ID では、デバイス オブジェクトを一致する同期されたコンピューター オブジェクトに関連付けます。 Microsoft Entra Connect は、オブジェクト GUID とコンピューター SID を含むデバイス オブジェクトを受信し、Active Directory にデバイス オブジェクトを書き込みます。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/device-sso-to-on-premises-resources"} -->
## Microsoft Entra 参加済みデバイス上でオンプレミス リソースへの SSO が機能するしくみ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/device-sso-to-on-premises-resources
- Service: entra-id / devices
- Article date: 2025-06-27
- Summary: Microsoft Entra ハイブリッド参加済みデバイスを構成して SSO エクスペリエンスを拡張します。

Microsoft Entra 参加済みデバイスによって、テナントのクラウド アプリへのシングル サインオン (SSO) エクスペリエンスがユーザーに提供されます。 現在の環境にオンプレミスの Active Directory Domain Services (AD DS) がある場合は、ユーザーは、オンプレミスの Active Directory Domain Services に依存するリソースとアプリケーションに SSO することもできます。

この記事では、この動作のしくみについて説明します。

### 前提条件

- [Microsoft Entra 登録済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)。
- オンプレミスの SSO には、オンプレミスの AD DS ドメイン コントローラーとの見通し内通信が必要です。 Microsoft Entra 参加済みデバイスが組織のネットワークに接続されていない場合は、VPN または他のネットワーク インフラストラクチャが必要です。
- Microsoft Entra Connect または Microsoft Entra Cloud Sync: SAM アカウント名、ドメイン名、UPN などの既定のユーザー属性を同期します。 詳細については、「[Microsoft Entra Connect によって同期される属性](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-sync-attributes-synchronized#windows-10)」を参照してください。

### 動作方法

ユーザーは Microsoft Entra 参加済みデバイスを使用して、ご利用の環境内のクラウド アプリへの SSO エクスペリエンスを既に手に入れています。 ご利用の環境に Microsoft Entra ID とオンプレミス AD DS がある場合は、SSO エクスペリエンスの範囲をオンプレミスの基幹業務 (LOB) アプリ、ファイル共有、プリンターにまで拡張することができます。

Microsoft Entra 参加済みデバイスには、オンプレミス AD DS 環境についての情報はありません (その環境に参加していないため)。 ただし、Microsoft Entra Connect を使用して、ご利用のオンプレミス AD に関する追加情報をこれらのデバイスに提供することができます。

Microsoft Entra Connect または Microsoft Entra Cloud Sync は、オンプレミスの ID 情報をクラウドに同期します。 同期プロセスの一環として、オンプレミスのユーザーとドメインの情報は、Microsoft Entra ID に同期されます。 ハイブリッド環境においてユーザーが Microsoft Entra 参加済みデバイスにサインインしたとき:

1. Microsoft Entra ID は、ユーザーのオンプレミス ドメインの詳細を[プライマリ更新トークン](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token)と共にデバイスに返送します
2. ローカル セキュリティ機関 (LSA) サービスによって、デバイス上の Kerberos および NTLM 認証が有効になります。

メモ

Microsoft Entra 参加済みデバイスに対するパスワードレス認証を使用する場合は、追加の構成が必要です。

FIDO2 セキュリティ キー ベースのパスワードレス認証および Windows Hello for Business ハイブリッド クラウド信頼の詳細については、「[Microsoft Entra ID を使用してオンプレミスのリソースへのパスワードレス セキュリティ キー サインインを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key-on-premises)」を参照してください。

Windows Hello for Business のクラウド Kerberos 信頼については、「[Windows Hello for Business - クラウド Kerberos 信頼の構成とプロビジョニング](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-hybrid-cloud-kerberos-trust-provision)」を参照してください。

Windows Hello for Business ハイブリッド キー信頼の詳細については、「[Windows Hello for Business を使用してオンプレミス シングル サインオン用に Microsoft Entra 参加済みデバイスを構成する](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-hybrid-aadj-sso)」を参照してください。

Windows Hello for Business ハイブリッド証明書の信頼については、「[AADJ オンプレミス シングル サインオンに証明書を使用する](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-hybrid-aadj-sso-cert)」を参照してください。

Kerberos または NTLM を要求しているオンプレミス リソースへのアクセスが試行されると、デバイスは次のようになります。

1. ユーザーを認証するために、見つかった DC にオンプレミス ドメインの情報とユーザーの資格情報を送信します。
2. オンプレミスのリソースまたはアプリケーションがサポートするプロトコルに基づいて、Kerberos の[チケット発行許諾チケット (TGT)](https://learn.microsoft.com/ja-jp/windows/win32/secauthn/ticket-granting-tickets) または NTLM トークンを受信します。 ドメインの Kerberos TGT または NTLM トークンを取得する試みが失敗した場合、資格情報マネージャー エントリが試みられるか、ユーザーがターゲット リソースの資格情報を要求する認証ポップアップを受け取る可能性があります。 このエラーは、DCLocator タイムアウトによって発生する遅延に関連している可能性があります。

**Windows 統合認証**の対象として構成されているすべてのアプリでは、ユーザーからのアクセスが試みられたときに、SSO がシームレスに適用されます。

### 受け取るもの

SSO を使用すると、Microsoft Entra 参加済みデバイスで次のことができます。

- AD のメンバー サーバー上の UNC パスへのアクセス
- Windows 統合セキュリティ用に構成された AD DS メンバーである Web サーバーへのアクセス

Windows デバイスからオンプレミス AD を管理する場合は、[リモート サーバー管理ツール](https://www.microsoft.com/download/details.aspx?id=45520)をインストールします。

使用できるもの:

- Active Directory ユーザーとコンピューター (ADUC) のスナップインを使用して、すべての AD オブジェクトを管理します。 ただし、手動で接続するドメインを指定する必要があります。
- AD 参加済み DHCP サーバーを管理するための、DHCP スナップイン。 ただし、DHCP サーバーの名前またはアドレスを指定する必要があります。

### 知っておくべきこと

- 複数のドメインがある場合に必要なドメインについてのデータが確実に同期されるように、Microsoft Entra Connect において[ドメイン ベースのフィルター処理](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-configure-filtering#domain-based-filtering)を調整することが必要になる場合があります。
- Microsoft Entra 参加済みデバイス上に AD DS 内のコンピューター オブジェクトがないため、Active Directory のコンピューター認証に依存するアプリとリソースは機能しません。
- Microsoft Entra 参加済みデバイス上でファイルを他のユーザーと共有することはできません。
- Microsoft Entra 参加済みデバイスで実行されているアプリケーションは、ユーザーを認証できます。 ドメインの FQDN 名をドメイン部分として使用する暗黙的な UPN または NT4 型の構文を使用する必要があります。たとえば user@contoso.corp.com、または contoso. corp. com\ user と指定します。
    - アプリケーションが NetBIOS 名または従来の名前 (contoso\user など) を使用している場合、アプリケーションが取得するエラーは、NT error STATUS\_BAD\_VALIDATION\_CLASS - 0xc00000a7、または Windows error ERROR\_BAD\_VALIDATION\_CLASS - 1348 "要求された検証情報クラスは無効です。" のどちらかとなります。このエラーは、従来のドメイン名を解決できる場合でも発生します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/enterprise-state-roaming-enable"} -->
## Microsoft Entra Enterprise State Roaming に移行する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/enterprise-state-roaming-enable
- Service: entra-id / devices
- Article date: 2026-09-14
- Summary: Enterprise State Roaming 管理を Microsoft Entra ID から Windows 設定のバックアップおよび復元ポリシーに移行する方法について説明します。

Enterprise State Roaming (ESR) を使用すると、ユーザーは、Microsoft Entra ID アカウントに関連付けられているデバイス間でサポートされているWindows設定を同期できます。 ESR 管理は、2026 年 7 月にMicrosoft Entra 管理センターからWindows設定のバックアップと復元に移行しました。 IT 管理者は、Microsoft Intune、別のモバイル デバイス管理 (MDM) プロバイダー、またはグループ ポリシーを使用してバックアップ ポリシーを構成するようになりました。

ESR 設定のセットは変更されません。 管理エクスペリエンスのみが変更されました。 サポートされている設定の現在の一覧については、 [Enterprise State Roaming 設定カタログを](https://learn.microsoft.com/ja-jp/windows/configuration/windows-backup/catalog-esr)参照してください。

ESR は、個人のMicrosoft アカウントを使用する[コンシューマー設定の同期](https://go.microsoft.com/fwlink/?linkid=2015135)とは別です。 2026 年 7 月より前は、[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)は[、Microsoft Entra 管理センター](https://entra.microsoft.com)の[デバイス設定](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-device-identities)を使用して ESR を構成できました。 そのポータル管理オプションは使用できなくなりました。

Important

Microsoft Entra 管理センターを使用して ESR を管理できなくなりました。 Windows設定のバックアップと復元のポリシーを構成して、ローミングでサポートされている設定を続行します。 アクションを実行しない場合、Windowsは既存の ESR およびグループ ポリシーまたは MDM ローミング コントロールを 1 年間優先し、グループ ポリシーまたは MDM に優先順位を付けます。 その期間が経過すると、バックアップポリシーと復元ポリシー Windows設定を構成するまで、ESR は機能しなくなります。

### Enterprise State Roaming の管理の移行

ESR 管理を移行するには、次の手順に従います。

1. ESR の現在の使用を確認し、設定のバックアップとローミングが必要なユーザーとデバイスを特定します。
2. デバイスがバックアップ要件を満たし、ユーザーが資格のあるライセンスを持っていることを確認します。 現在の要件とライセンス情報については、[Windows設定のバックアップと復元](https://learn.microsoft.com/ja-jp/windows/configuration/windows-backup/)、[Enterprise State Roaming 設定カタログ](https://learn.microsoft.com/ja-jp/windows/configuration/windows-backup/catalog-esr)、[および Microsoft Entra 製品ページ](https://azure.microsoft.com/services/active-directory)を参照してください。
3. Microsoft Intune、ポリシー構成サービス プロバイダー (CSP)、またはグループ ポリシーを使用して、Windows **バックアップを有効にする**ポリシーを構成します。
4. 設定のバックアップとローミングが必要なユーザーまたはデバイスにポリシーを割り当てます。
5. ポリシーが正常に適用されることを確認します。

メモ

グループ ポリシーまたは CSP を使用して、Windows設定のバックアップと復元を構成します。 設定が競合すると予期しない結果が発生する可能性があるため、両方のポリシー ソースを組み合わせないでください。

同期する設定を制限するレガシ コントロールの詳細については、「設定の同期の [グループ ポリシーと MDM 設定](https://learn.microsoft.com/ja-jp/entra/identity/devices/enterprise-state-roaming-group-policy-settings)」を参照してください。

バックアップは、対象となる Microsoft Entra 参加デバイスおよび[Microsoft Entra ハイブリッド参加デバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)でサポートされます。 現在のオペレーティング システムのバージョンとビルドの要件については、「[バックアップと復元のシステム要件Windows設定](https://learn.microsoft.com/ja-jp/windows/configuration/windows-backup/#system-requirements)」を参照してください。

Microsoft Edge同期は ESR とは別に管理されます。 詳細については、「[Microsoft Edge同期](https://learn.microsoft.com/ja-jp/deployedge/microsoft-edge-enterprise-sync)」を参照してください。従来の ESR 診断については、「[Enterprise State Roaming のトラブルシューティング」](https://learn.microsoft.com/ja-jp/entra/identity/devices/enterprise-state-roaming-troubleshooting)を参照してください。

### データの保存と保持

Windows設定のバックアップと復元では、ユーザー固有の設定が個人データとして扱われ、テナントのリージョンにデータが格納されます。 パブリック クラウドでは、テナントの作成時に選択された国またはリージョンが、Exchange Onlineの地理的な場所にマップされます。

既定では、アクティブなアカウントとデバイスに関連付けられている間、データは保持されます。 ストレージ、暗号化、コンプライアンス、保持に関する最新情報については、[Windows設定のバックアップと復元](https://learn.microsoft.com/ja-jp/windows/configuration/windows-backup/faq#data-storage-and-retention)に関する FAQ を参照してください。 従来の ESR 固有の質問については、 [設定とデータ ローミングに関する FAQ を](https://learn.microsoft.com/ja-jp/entra/identity/devices/enterprise-state-roaming-faqs)参照してください。 クラウドの場所に関する一般的な情報については、「[Azure リージョン」を](https://azure.microsoft.com/regions/)参照してください。 データの場所の決定に関するヘルプが必要な場合は、[Azure サポートオプションを](https://azure.microsoft.com/support/options/)確認するか、[Azure サポート](https://azure.microsoft.com/support/)にお問い合わせください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/enterprise-state-roaming-faqs"} -->
## Enterprise State Roaming に関する FAQ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/enterprise-state-roaming-faqs
- Service: entra-id / devices
- Article date: 2025-06-27
- Summary: ESR に関してよく寄せられる質問

この記事では、IT 管理者が設定やアプリ データの同期に関して抱く可能性のあるいくつかの疑問に答えます。

### 設定の同期に使用されるアカウントは

Windows 8.1 では常に、コンシューマーの Microsoft アカウントが設定の同期に使用されていました。 企業ユーザーは、その Active Directory ドメイン アカウントに Microsoft アカウントを接続することによって設定の同期を利用することができました。Windows 10 以降では、この Microsoft アカウントの接続機能が、プライマリ/セカンダリ アカウント フレームワークによって置き換えられています。

プライマリ アカウントは、Windows へのサインインに使用するアカウントとして定義されています。 Microsoft アカウントや Microsoft Entra アカウント、オンプレミスの Active Directory アカウント、ローカル アカウントが該当します。 Windows 10 以降のユーザーは、プライマリ アカウントに加え、1 つまたは複数のセカンダリ クラウド アカウントを各自のデバイスに追加することができます。 一般に、セカンダリ アカウントとは、Microsoft アカウントや Microsoft Entra アカウントのほか、Gmail や Facebook といったアカウントのことを指します。 セカンダリ アカウントで、シングル サインオンや Windows ストアなど他のサービスにアクセスすることはできますが、設定の同期機能にアクセスすることはできません。

デバイス上の異なるユーザー アカウントのデータがミックスされることはありません。 設定の同期には次の 2 つの規則があります。

- Windows の設定は常にプライマリ アカウントでローミングされます。
- アプリのデータは、そのアプリを取得する際に使われたアカウントでタグ付けされます。 同期されるのは、プライマリ アカウントでタグ付けされたアプリだけです。アプリの所有権タグは、Windows ストアやモバイル デバイス管理 (MDM) 経由でアプリをサイド ローディングしたときに決定されます。

アプリケーション所有者を特定できない場合は、プライマリ アカウントでローミングされます。 デバイスが Windows 8 や Windows 8.1 から Windows 10 以降にアップグレードされると、すべてのアプリが Microsoft アカウントで取得されたようにタグ付けされます。 これは、ほとんどのユーザーがアプリを Windows ストアを通じて取得し、Windows 10 以前の Microsoft Entra アカウントでは Windows ストアのサポートがなかったために起こります。 オフライン ライセンスでインストールされたアプリは、そのデバイス上のプライマリ アカウントでタグ付けされます。

注

Microsoft Entra ID に接続された Windows 10 以降の企業所有デバイスの Microsoft アカウントは、今後ドメイン アカウントに接続できません。 Microsoft アカウントをドメイン アカウントに接続してそのユーザーの全データを Microsoft アカウントに同期させる機能 (Microsoft アカウントと Active Directory との接続による Microsoft アカウントでのローミング機能) は、Active Directory と Microsoft Entra とが接続された環境に参加している Windows 10 以降のデバイスからは削除されます。

### Windows 8 における Microsoft アカウントでの設定の同期から Windows 10 以降における Microsoft Entra での設定の同期にアップグレードするとどうなりますか

Windows 10 以降へのアップグレード後も、ドメインに参加しているユーザーで Active Directory ドメインと Microsoft Entra ID が接続されていない場合は、引き続き Microsoft アカウントでユーザー設定を同期します。

一方、オンプレミスの Active Directory ドメインが Microsoft Entra ID と接続されている場合、お使いのデバイスは、接続されている Microsoft Entra アカウントを使用して設定を同期します。 Microsoft Entra 管理者によって Enterprise State Roaming が有効にされていない場合、接続されている Microsoft Entra アカウントは設定の同期を中止します。 Windows 10 以降を実行していて、Microsoft Entra の ID でサインインしている場合、管理者が Microsoft Entra ID を介した設定の同期を有効にするとすぐに Windows の設定の同期が開始されます。

個人データを会社のデバイスに保存している場合は、Windows OS とアプリケーションのデータが Microsoft Entra ID と同期されることを知っておいてください。 これには、次のような意味があります。

- 個人の Microsoft アカウントの設定が、職場または学校の Microsoft Entra アカウントの設定から変更されます。 これは、Microsoft アカウントと Microsoft Entra の設定の同期が別のアカウントを使用するようになるためです。
- 従来、Active Directory に接続された Microsoft アカウント経由で同期されていた個人データ (Wi-Fi のパスワード、Web の資格情報、Internet Explorer のお気に入りなど) は今後、Microsoft Entra ID 経由で同期されます。

### Microsoft アカウントと Microsoft Entra の Enterprise State Roaming の相互運用性について教えてください

November 2015 以降のリリースの Windows 10 では、Enterprise State Roaming が一度に 1 つのアカウントでのみサポートされます。 職場や学校の Microsoft Entra アカウントで Windows にサインインした場合は、すべてのデータが Microsoft Entra ID 経由で同期されます。 個人用 Microsoft アカウントで Windows にサインインした場合は、すべてのデータが Microsoft アカウント経由で同期されます。 ユニバーサル アプリ データはデバイスのプライマリ サインイン アカウントのみを使用してローミングされ、アプリのライセンスがプライマリ アカウントによって所有されている場合にのみローミングされます。 セカンダリ アカウントによって所有されているアプリのユニバーサル アプリ データは同期されません。

### 複数テナントの Microsoft Entra アカウントが存在する場合、設定は同期されますか

異なる Microsoft Entra テナントの複数の Microsoft Entra アカウントが同じデバイス上にある場合は、Microsoft Entra テナントごとに Azure Rights Management サービスと通信するようにデバイスのレジストリを更新する必要があります。

1. 各 Microsoft Entra テナントの GUID を見つけます。 [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインし、**Entra ID**&gt;**Overview**&gt;**Properties**&gt;**Tenant ID** に移動します。
2. GUID を取得したら、レジストリ キーHKEY\_LOCAL\_MACHINE\SOFTWARE\Microsoft\Windows\SettingSync\WinMSIPC&lt;** テナント ID GUID&gt;**を追加する必要があります。 **テナント ID GUID** キーから、**AllowedRMSServerUrls** という名前の新しい複数文字列値 (REG-MULTI-SZ) を作成します。 そのデータに対して、デバイスがアクセスする他の Azure テナントのライセンス配布ポイントの URL を指定します。
3. ライセンス配布ポイントの URL は、AADRM モジュールから **Get-AadrmConfiguration** コマンドレットを実行することで確認できます。 **LicensingIntranetDistributionPointUrl** と **LicensingExtranetDistributionPointUrl** の値が異なる場合は、両方の値を指定します。 同じ値である場合は、その値を一度指定してください。

### 同期された設定とデータをオンプレミスに保存することはできますか？

Enterprise State Roaming は同期されたすべてのデータを Microsoft クラウドに格納します。 オンプレミスのローミング ソリューションとしては UE-V をご利用ください。

### データはどのように保護されますか?

2022 年 11 月より前は、すべてのユーザー データが [Azure Rights Management](https://learn.microsoft.com/ja-jp/azure/information-protection/what-is-information-protection) を使用して保護されていました。

2022 年 11 月から、Microsoft ではすべてのデータ暗号化に Azure Rights Management を使用しなくなりました。 マイクロソフトは、顧客データの保護に積極的に取り組んでいます。 パスワードなどの特定の機密データは、セキュリティの追加レイヤーを確保するために、Microsoft Entra テナントから派生したキーを使用してクライアント側で暗号化されます。 すべてのユーザー データ (機密ではないデータを含む) は、転送中とクラウドでの保存時に暗号化されます。 ローミングされた機密データ項目と非センシティブ データ項目の一覧については、 [Enterprise State Roaming 設定カタログを](https://learn.microsoft.com/ja-jp/windows/configuration/windows-backup/catalog-esr)参照してください。

### 特定のアプリや設定の同期を管理することはできますか

Windows 10 以降では、管理者は MDM またはグループ ポリシーを使用して、マネージド デバイス上のすべての設定同期グループの同期を無効にすることができます。

### ローミングはどのようにして有効または無効にできますか

**設定**アプリで、[**アカウント**] に移動し&gt;**設定を同期します**。 このページから、設定のローミングに使用されているアカウントを確認したり、ローミング対象となる設定グループの有効と無効を個別に切り替えたりすることができます。

### Windows 10 以降におけるローミングの実現手段としてどのような方法が推奨されますか。

設定のローミングに関して、マイクロソフトでは UE-V や Enterprise State Roaming など、いくつかのソリューションを提供しています。 データをクラウドに移すことに関して勤務先が積極的でない、または準備が整っていない場合は、主要なローミング テクノロジとして UE-V を使用することをお勧めします。 既存の Windows デスクトップ アプリケーションのローミングに対応する必要がある一方で、クラウドへの移行を勤務先が積極的に推進している場合は、Enterprise State Roaming と UE-V を併用することをお勧めします。 UE-V と Enterprise State Roaming は類似したテクノロジですが、相互に排他的ではありません。 組織がユーザーが必要とするローミング サービスを確実に提供できるように、互いに補完します。

Enterprise State Roaming と UE-V の両方を使用している場合は、Enterprise State Roaming がデバイス上の主要なローミング エージェントになります。 UE-V は、Win32 アプリケーションを補うために使用されます。

- Enterprise State Roaming がデバイス上の主要なローミング エージェントになります。 UE-V は、"Win32 とのギャップ" を補うために使用されます。
- Windows の設定や UWP のモダン アプリのデータに対する UE-V ローミングは、UE-V のグループ ポリシーを使用する際には無効にする必要があります。 これらの設定は既に Enterprise State Roaming で補われています。

### 仮想デスクトップ インフラストラクチャ (VDI) に対する Enterprise State Roaming の対応について教えてください

Enterprise State Roaming は Windows 10 以降のクライアント SKU でサポートされていますが、サーバー SKU ではサポートされません。 ハイパーバイザー コンピューターでクライアント VM がホストされており、その仮想マシンにリモートでサインインしている場合、データはローミングされます。 複数のユーザーが同じ OS を共有しており、ユーザーがリモートからサーバーにログインしてデスクトップとまったく同じようにその環境を利用している場合、ローミングは正常に機能しない場合があります。 後者のセッション ベースのシナリオは公式対応していません。

### ユーザーごとのデバイス同期状態レポートはどうなりましたか?

ユーザーごとのデバイス同期状態レポートは Microsoft Entra 管理センターから削除されました。 このレポートは、サポートされていないバージョンの Windows の詳細しか提供しない状態だったため、削除されました。 Enterprise State Roaming には、Windows 11 または Windows 10 バージョン 21H2 以降が必要です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/enterprise-state-roaming-group-policy-settings"} -->
## ESR のグループ ポリシーと MDM 設定 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/enterprise-state-roaming-group-policy-settings
- Service: entra-id / devices
- Article date: 2025-06-27
- Summary: Enterprise State Roaming の管理設定

これらのポリシーはユーザーのデバイス全体に適用されるため、これらのグループ ポリシーとモバイル デバイス管理 (MDM) 設定は会社所有のデバイスでのみ使用します。 MDM ポリシーを適用して、個人のユーザー所有デバイスの設定同期を無効にすると、そのデバイスの使用に悪影響が及びます。 これらのポリシーは、デバイス上の他のユーザー アカウントに影響します。

個人 (管理されていない) デバイスのローミングを管理する企業は、グループ ポリシーや MDM を使用するのではなく、Microsoft Entra 管理センターを使用してローミングを有効または無効にすることができます。 次の表では、使用可能なポリシー設定について説明します。

手記

この記事は、2015 年 7 月に Windows 10 で起動された Microsoft Edge レガシ HTML ベースのブラウザーに適用されます。 この記事は、2020 年 1 月 15 日にリリースされた新しい Microsoft Edge Chromium ベースのブラウザーには適用されません。 新しい Microsoft Edge の同期動作の詳細については、Microsoft Edge Sync 記事を参照してください。

### MDM の設定

MDM ポリシー設定は、Windows 10 以降に適用されます。 Microsoft Entra ID ベースの同期でサポートされているデバイスの詳細については、 [Enterprise State Roaming 設定カタログ](https://learn.microsoft.com/ja-jp/windows/configuration/windows-backup/catalog-esr) を参照してください。

| 名前 | 形容 |
| --- | --- |
| Microsoft アカウント接続を許可する | ユーザーがデバイス上の Microsoft アカウントを使用して認証できるようにします |
| 設定の同期を許可する | ユーザーが Windows の設定とアプリ データをローミングできるようにします。このポリシーを無効にすると、モバイル デバイスでの同期とバックアップが無効になります |

### グループ ポリシーの設定

グループ ポリシー設定は、Active Directory ドメインに参加している Windows 10 以降のデバイスに適用されます。 この表には、同期設定を管理するために表示されるレガシ設定も含まれています。 Windows 10 以降の Enterprise State Roaming では機能しないレガシ設定については、説明に 「使用しない」と記載されています。

これらの設定は、グループ ポリシーの [**コンピューターの構成]**&gt;**[管理用テンプレート]**&gt;**[Windows コンポーネント]**&gt;**[設定**の同期] にあります。

| 名前 | 形容 |
| --- | --- |
| アカウント: Microsoft アカウントをブロックする | このポリシー設定により、ユーザーはこのコンピューターに新しい Microsoft アカウントを追加できなくなります |
| 同期しない | ユーザーが Windows の設定とアプリ データをローミングできないようにする |
| 個人用設定を同期しない | テーマ グループの同期を無効にします |
| ブラウザーの設定を同期しない | Internet Explorer グループの同期を無効にします |
| パスワードを同期しない | パスワード グループの同期を無効にします |
| 他の Windows 設定を同期しない | その他の Windows 設定グループの同期を無効にします |
| デスクトップの個人用設定を同期しない | 使用しないでください。影響なし |
| 従量制課金接続で同期しない | 携帯ネットワーク 3G などの従量制課金接続でのローミングを無効にします |
| アプリを同期しない | 使用しないでください。影響なし |
| アプリ設定を同期しない | アプリ データのローミングを無効にします |
| 開始設定を同期しない | 使用しないでください。影響なし |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/enterprise-state-roaming-troubleshooting"} -->
## Microsoft Entra ID の Enterprise State Roaming のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/enterprise-state-roaming-troubleshooting
- Service: entra-id / devices
- Article date: 2025-06-27
- Summary: 設定とアプリ データの同期に関する質問とその答えを IT 管理者向けに紹介しています。

この記事では、Enterprise State Roaming の問題のトラブルシューティングと診断の方法について説明するとともに、既知の問題の一覧を示します。

注

Azure を操作するには、Azure Az PowerShell モジュールを使用することをお勧めします。 作業を開始するには、 [Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell) に関するページを参照してください。 Az PowerShell モジュールに移行する方法については、「 [Azure PowerShell を AzureRM から Az に移行する」](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)を参照してください。

注

この記事は、2020 年 1 月 15 日にリリースされた新しい Microsoft Edge Chromium ベースのブラウザーには適用されません。 新しい Microsoft Edge の同期動作の詳細については、 [Microsoft Edge Sync](https://learn.microsoft.com/ja-jp/deployedge/microsoft-edge-enterprise-sync) に関する記事を参照してください。

### トラブルシューティングのための準備作業

トラブルシューティングを開始する前に、ユーザーとデバイスが適切に設定されており、Enterprise State Roaming のすべての要件が満たされていることを確認します。

1. 最新の更新プログラムが適用された Windows 10 以降と、最小バージョン 22H2 (OS ビルド 19045 以降) がデバイスにインストールされている。
2. デバイスは、Microsoft Entra 参加済みまたは Microsoft Entra ハイブリッド参加済みです。 詳細については、 [Microsoft Entra ID の制御下にあるデバイスを取得する方法](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)を参照してください。
3. **「Enterprise State Roaming** を有効にするには」の説明に従って、Microsoft Entra ID のテナントに対[して Enterprise State Roaming が有効](https://learn.microsoft.com/ja-jp/entra/identity/devices/enterprise-state-roaming-enable)になっていることを確認します。 すべてのユーザーまたは選択したグループのユーザーにだけローミングを有効にできます。
4. ユーザーには、Microsoft Entra ID P1 または P2 ライセンスが割り当てられています。
5. デバイスを再起動し、もう一度サインインして Enterprise State Roaming の機能にアクセスする必要があります。

### 問題のトラブルシューティングと診断

このセクションでは、Enterprise State Roaming に関連した問題のトラブルシューティングと診断の方法に関する推奨事項を示します。

### 同期、および "設定の同期" の設定ページを確認する

1. Enterprise State Roaming を許可するよう構成されたドメインに Windows 10 またはそれ以降の PC を参加させたら、職場アカウントを使ってサインオンします。 &gt;&gt; に移動し、同期と個々の設定がオンであること、および設定ページの上部に職場アカウントとの同期が示されていることを確認します。 同じアカウントが&gt;&gt;でアカウントとしても使用されていることを確認します。
2. "国またはリージョン" の変更やサポートされているその他の設定の使用など、元のコンピューターで一部の変更を行って、複数のマシン間で同期が機能することを確認します。 [Enterprise State Roaming 設定カタログを](https://learn.microsoft.com/ja-jp/windows/configuration/windows-backup/catalog-esr)参照してください。 5 分以内に、2 つ目のコンピューターに変更が反映されることを確認します。

    - 画面をロックまたはロック解除 (Win + L) すると、同期をトリガーできる場合があります。
    - Enterprise State Roaming はコンピューター アカウントではなくユーザー アカウントに関連付けられているため、同期が機能するには、両方の PC で同じアカウントでサインインする必要があります。

**潜在的な問題**: **[設定]** ページのコントロールが使用できない場合、"一部の Windows 機能は、Microsoft アカウントまたは職場アカウントを使用している場合にのみ使用できます" というメッセージが表示されます。この問題は、ドメインに参加して Microsoft Entra ID に登録するように設定されているが、デバイスがまだ Microsoft Entra ID に認証されていないデバイスで発生する可能性があります。 考えられる原因としては、デバイス ポリシーを適用する必要があるものの、アプリケーションが非同期的に動作して、数時間かかった可能性があります。

#### デバイスの登録状態を確認する

Enterprise State Roaming では、デバイスを Microsoft Entra ID に登録する必要があります。 Enterprise State Roaming に限ったことではありませんが、次の手順を行うと Windows 10 またはそれ以降のクライアントが登録されていることを確認できるほか、拇印、Microsoft Entra の設定 URL、NGC の状態、およびその他の情報を確認することができます。

1. 管理者特権を使用せずにコマンド プロンプトを開きます。 これを Windows で行う場合は、[ファイル名を指定して実行] \(Win + R) を開き、「cmd」と入力して開きます。
2. コマンド プロンプトが開いたら、「`*dsregcmd.exe /status*`」と入力します。
3. 予想される出力では、 **AzureAdJoined** フィールドの値を `YES`し、 **WamDefaultSet** フィールド値を `YES`し、 **WamDefaultGUID** フィールド値を最後に `(AzureAD)` GUID にする必要があります。

**潜在的な問題**: **WamDefaultSet** と **AzureAdJoined** の両方がフィールド値に "NO" を持ち、デバイスがドメインに参加し、Microsoft Entra ID に登録され、デバイスが同期しません。これが表示されている場合、デバイスはポリシーが適用されるのを待つ必要があるか、Microsoft Entra ID に接続するときにデバイスの認証が失敗する可能性があります。 ポリシーが適用されるまでには数時間かかる場合があります。 その他のトラブルシューティング手順としては、サインアウトしてからサインインし直してもう一度自動登録をしてみるか、タスク スケジューラでタスクを起動することなどが挙げられます。 場合によっては、管理者特権のコマンド プロンプト ウィンドウで "*dsregcmd.exe /leave*" を実行し、再起動して登録を再試行すると、この問題に役立つ場合があります。

**潜在的な問題**: **SettingsUrl** のフィールドが空で、デバイスが同期しません。Enterprise State Roaming が有効になる前に、ユーザーがデバイスに最後にログインした可能性があります。 デバイスを再起動し、ユーザーにサインインしてもらいます。 必要に応じて、ポータルの **[Entra ID]**&gt;**[デバイス]**&gt;**[概要]**&gt;**[Enterprise State Roaming]** に移動し、**[デバイス間での設定とアプリ データの同期が許可されるユーザー]** を無効にしてから再度有効にするよう、IT 管理者に依頼してください。 無効にするには、「なし」をクリックし、再び有効にするには、「すべて」または「選択」をクリックしてください。 その後、デバイスを再起動し、ユーザーにサインインしてもらいます。 これで問題が解決しない場合は、無効なデバイス証明書がある場合、 **SettingsUrl** が空になる可能性があります。 この場合、管理者特権のコマンド プロンプト ウィンドウで "*dsregcmd.exe /leave*" を実行し、再起動して登録を再試行すると、この問題に役立つ可能性があります。

### Enterprise State Roaming と多要素認証

Microsoft Entra 多要素認証が構成されている場合、特定の条件下で Enterprise State Roaming がデータの同期に失敗する可能性があります。 これらの現象の詳細については、サポート ドキュメント [のKB3193683](https://support.microsoft.com/kb/3193683)を参照してください。

**潜在的な問題**: Microsoft Entra 管理センターで多要素認証を必要とするようにデバイスが構成されている場合、パスワードを使用して Windows 10 以降のデバイスにサインインするときに設定の同期が失敗する可能性があります。 このタイプの多要素認証構成は、Azure 管理者アカウントの保護を意図したものです。 管理者ユーザーは、Windows Hello for Business の PIN を使用するか、他の Azure サービス (Microsoft 365 など) にアクセスしている状態で多要素認証を行い、Windows 10 またはそれ以降のデバイスにサインインすることで同期を実行できる場合があります。

**潜在的な問題**: 管理者が Active Directory フェデレーション サービスの多要素認証条件付きアクセス ポリシーを構成し、デバイス上のアクセス トークンの有効期限が切れると、同期が失敗する可能性があります。 Windows Hello for Business の PIN を使用してサインインおよびサインアウトするか、または他の Azure サービス (Microsoft 365 など) にアクセスしている状態で多要素認証を行ってください。

#### イベント ビューアー

高度なトラブルシューティング方法として、イベント ビューアーを使って特定のエラーの検出ができるものがあります。 イベントは、イベント ビューアー &gt;**アプリケーションとサービス ログ**&gt;**Microsoft**&gt;**Windows**&gt;**CloudStore** の下にあり、**アプリケーションとサービス ログ**の同期に関する ID 関連の問題&gt;**Microsoft**&gt;**Windows**&gt;**AAD** で確認できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/faq"} -->
## Microsoft Entra のデバイス管理に関する FAQ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/faq
- Service: entra-id / devices
- Article date: 2024-11-25
- Summary: Microsoft Entra のデバイス管理に関する FAQ。

### 一般的な FAQ

#### 最近、デバイスを登録しました。 ユーザー情報にデバイスが表示されないのはなぜですか? また、Microsoft Entra にハイブリッド参加済みデバイスのデバイス所有者が N/A とマークされるのはなぜですか?

Microsoft Entra ハイブリッド参加済みの Windows 10 以降のデバイスは、**[ユーザー デバイス]** には表示されません。 **[すべてのデバイス]** ビューを使用します。 PowerShell の [Get-MsolDevice](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdevice) コマンドレットを使用することもできます。

**[ユーザー デバイス]** には、次のデバイスだけが表示されます。

- Microsoft Entra にハイブリッド参加済みではないすべての個人用デバイス。
- Windows 10 以降でも Windows Server 2016 以降でもないすべてのデバイス。
- Windows 以外のすべてのデバイス。

#### クライアントのデバイスの登録状態はどうすればわかりますか?

**[すべてのデバイス]** に移動します。 デバイス ID を使用してデバイスを検索します。 [結合の種類] 列の値を確認します。 場合によっては、デバイスがリセットまたは再イメージ化されていることがあります。 そのため、デバイスでもデバイス登録状態を確認することが不可欠です。

- Windows 10 以降および Windows Server 2016 以降のデバイスの場合は、`dsregcmd.exe /status` を実行します。

トラブルシューティング情報については、次の記事をご覧ください。

- [dsregcmd コマンドを使用したデバイスのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-device-dsregcmd)
- [Microsoft Entra ハイブリッドに参加している Windows 10 および Windows Server 2016 デバイスに関するトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-hybrid-join-windows-current)

#### 組織のオンプレミス AD ユーザーは、Microsoft Entra ID で 2 つ以上の異なるテナントに分割されています。 クライアント マシン上のテナントごとに Windows PRT を取得できますか?

ユーザーとデバイスが同じテナントに属している場合、Windows クライアントは Microsoft Entra ID から PRT をフェッチします。 デバイスが登録されていない場合、またはユーザーがそこにメンバーでない場合、ユーザーは別のテナントの PRT を取得しません。 2 つのテナントが B2B 経由で相互に信頼している場合は、常にクロス テナント B2B アクセスを作成し、ホーム テナントからデバイス要求を信頼できます。

#### ユーザー情報にデバイス レコードが表示され、状態が登録済みとして表示されます。 条件付きアクセスを使用するように正しく設定されていますか?

**deviceID** に表示されたデバイスの参加状態は、Microsoft Entra ID での状態と一致しており、条件付きアクセスの評価基準を満たしている必要があります。 詳細については、[条件付きアクセスを使用してクラウド アプリへのアクセスにマネージド デバイスを要求する方法](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)に関するページを参照してください。

#### Windows 10/11 デバイスに "Your organization has deleted the device (組織がデバイスを削除しました)" または "Your organization has disabled the device (組織がデバイスを無効にしました)" というエラー メッセージが表示されるのはなぜですか?

Microsoft Entra ID に参加または登録した Windows 10/11 デバイスのユーザーには、シングル サインオンを有効にする[プライマリ更新トークン (PRT)](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token) が発行されます。 PRT の有効性は、デバイス自体の有効性に基づきます。 デバイス自体からアクションを開始せずに、Microsoft Entra ID でデバイスが削除または無効にされている場合に、このメッセージがユーザーに表示されます。 デバイスは、次のいずれかのシナリオで Microsoft Entra ID で削除または無効にすることができます。

- ユーザーが、マイ アプリ ポータルからデバイスを無効にする。
- 管理者 (またはユーザー) が、デバイスを削除または無効にする。
- Microsoft Entra にハイブリッド参加済みのみ: 管理者が同期スコープからデバイス OU を削除し、その結果、Microsoft Entra ID からデバイスが削除される。
- Microsoft Entra にハイブリッド参加済みのみ: 管理者がオンプレミスのコンピューター アカウントを無効にし、その結果、Microsoft Entra ID でデバイスが無効になる。

#### デバイスを無効にするか削除しましたが、デバイスのローカル状態に登録済みと表示されます。 どうすればよいですか。

この操作は設計によるものです。 この場合、デバイスは、クラウドのリソースにアクセスできません。 管理者は、不正なアクセスを防ぐため、古いデバイス、紛失したデバイス、または盗難されたデバイスに対してこのアクションを実行できます。 このアクションを意図せずに実行した場合は、次に示す手順を使用して、デバイスを再度有効にするか再登録する必要があります

- デバイスが Microsoft Entra ID で無効になっている場合は、十分な特権を持つ管理者が Microsoft Entra 管理センターでデバイスを有効にすることができます。

    注

    Microsoft Entra Connect を使用してデバイスを同期している場合、Microsoft Entra ハイブリッド参加済みデバイスは、次の同期サイクル中に自動的に再度有効になります。 そのため、Microsoft Entra にハイブリッド参加済みデバイスを無効にする必要がある場合は、オンプレミスの AD から無効にする必要があります。
- デバイスが Microsoft Entra ID で削除された場合は、デバイスを再登録する必要があります。 再登録するには、デバイスで手動操作を実行する必要があります。 デバイスの状態に基づいて再登録する手順については、次の手順を参照してください。

    Microsoft Entra ハイブリッド参加済み Windows 10/11 および Windows Server 2016/2019 デバイスを再登録するには、次の手順を実行します。

    1. 管理者としてコマンド プロンプトを開きます。
    2. 「`dsregcmd.exe /debug /leave`」と入力します。
    3. サインアウトしてからサインインして、デバイスを Microsoft Entra ID に再登録するスケジュール済みタスクをトリガーします。

    Microsoft Entra 参加済み Windows 10/11 デバイスの場合、次の手順を実行します。

    1. 管理者としてコマンド プロンプトを開きます。
    2. `dsregcmd /forcerecovery` を入力します (このアクションを実行するには、管理者である必要があります)。
    3. 開いたダイアログで "サインイン" をクリックし、サインイン プロセスを続行します。
    4. 回復を完了するには、サインアウトし、デバイスでもう一度サインインします。

    Microsoft Entra 登録済み Windows 10/11 デバイスの場合、次の手順を実行します。

    1. **[設定]**&gt;**[アカウント]**&gt;**[職場または学校にアクセスする]** に移動します。
    2. アカウントを選択し、 **[切断]** を選択します。
    3. "+ 接続" をクリックし、サインイン プロセスを実行してデバイスを再度登録します。

#### 重複するデバイス エントリが表示されるのはなぜですか?

- Windows 10 以降および Windows Server 2016 以降の場合、同じデバイスの参加を解除し、再度参加させる操作を繰り返すと、エントリの重複が発生することがあります。
- **[職場または学校アカウントを追加]** を使用する各 Windows ユーザーは、同じデバイス名で新しいデバイス レコードを作成します。
- Microsoft Entra 参加済みコンピューターをワイプして再インストールし、同じ名前で再度参加させると、同じデバイス名で別のレコードとして表示されます。

#### Microsoft Entra での Windows 10/11 デバイス登録では、FIPS モードの TPM はサポートされますか?

Windows 10/11 デバイス登録は FIPS 準拠の TPM 2.0 でのみサポートされ、TPM 1.2 ではサポートされていません。 FIPS に準拠している TPM 1.2 がデバイスにある場合は、Microsoft Entra 参加または Microsoft Entra ハイブリッド参加を進める前に、それらを無効にする必要があります。 TPM の FIPS モードを無効にするためのツールは、TPM の製造元に依存するため、Microsoft では提供していません。 サポートが必要な場合は、お使いのハードウェアの OEM にお問い合わせください。

#### 無効にしたデバイスからユーザーがリソースに引き続きアクセスできるのはなぜですか?

Microsoft Entra デバイスが無効とマークされた時点から取り消しが適用されるまで、最大で 1 時間かかります。

注

登録済みデバイスの場合は、ユーザーがリソースにアクセスできないように、デバイスのワイプを実行することをお勧めします。 詳細については、「[デバイス登録とは](https://learn.microsoft.com/ja-jp/mem/intune/user-help/use-managed-devices-to-get-work-done)」を参照してください。

#### Windows 10/11 デバイスの同じユーザー セッションに 3 つ以上の Microsoft Entra ユーザー アカウントを追加できないのはなぜですか?

Microsoft Entra ID には、Windows 10 1803 以降のリリースでの複数の Microsoft Entra アカウントのサポートが追加されました。 ただし、Windows 10/11 では、トークン要求のサイズを制限し、信頼性の高いシングルサインオン (SSO) を可能にするため、1 つのデバイス上での Microsoft Entra アカウントの数が 3 つに制限されます。 3 つのアカウントが追加されると、ユーザーに後続のアカウントのエラーが表示されます。 エラー画面の問題に関するその他の情報には、"Add account operation is blocked because accout limit is reached" (アカウントの上限に達したため、アカウントの追加操作がブロックされます) という理由を示すメッセージが表示されます。

#### Windows 10/11 デバイスに存在する MS-Organization-Access 証明書とは何ですか?

Microsoft Entra Device Registration Service は、デバイス登録プロセス中に MSOrganization-Access 証明書を発行します。 これらの証明書は、Windows でサポートされているすべての参加形態 (Microsoft Entra 参加済み、Microsoft Entra ハイブリッド参加済みデバイス、Microsoft Entra 登録済みデバイス) に発行されます。 発行後は、デバイスから Primary Refresh Token (PRT) を要求する認証プロセスの中で使用されます。 Microsoft Entra 参加済みデバイスと Microsoft Entra ハイブリッド参加済みデバイスの場合、この証明書は Local Computer\Personal\Certificates にありますが、Microsoft Entra 登録済みデバイスの場合、証明書は Current User\Personal\Certificates にあります。 すべての MS-Organization-Access 証明書の既定の有効期間は 10 年です。 これらの証明書は、デバイスが Microsoft Entra ID から登録解除されると、対応する証明書ストアから削除されます。 うっかりこの証明書を削除してしまうとユーザーの認証エラーが発生します。そのような場合は、デバイスの再登録が必要となります。

### Microsoft Entra への参加に関する FAQ

#### デバイス上の Microsoft Entra 参加済みデバイスをローカルで参加解除するにはどうすればよいですか?

純粋な Microsoft Entra 参加済みデバイスの場合、オフラインのローカル管理者アカウントを持っているか、作成する必要があります。 Microsoft Entra ユーザーの資格情報ではサインインできません。 次に、 **[設定]**&gt;**[アカウント]**&gt;**[職場または学校にアクセスする]** に移動します。 アカウントを選択し、 **[切断]** を選択します。 画面の指示に従い、入力を求められたらローカル管理者の資格情報を入力します。 デバイスを再起動して、参加の解除プロセスを完了します。

#### ユーザーは Microsoft Entra ID で削除されたか無効にされた Microsoft Entra 参加済みデバイスにサインインできますか?

はい。 Windows には、以前にサインインしたユーザーがネットワークに接続していなくてもすばやくデスクトップにアクセスできるようにする、キャッシュされたユーザー名とパスワードの機能があります。

Microsoft Entra ID でデバイスが削除または無効化されても、Windows デバイスにはわかりません。 そのため、以前にサインインしたユーザーは、引き続きキャッシュされたユーザー名とパスワードを使ってデスクトップにアクセスします。 しかし、デバイスは削除または無効化されているため、ユーザーはデバイスベースの条件付きアクセスによって保護されているリソースにアクセスできません。

以前にサインインしたことのないユーザーは、デバイスにはアクセスできません。 そのようなユーザーには、キャッシュされたユーザー名とパスワードが有効になっていません。

#### 無効にされたか削除されたユーザーは、Microsoft Entra 参加済みデバイスにサインインできますか?

はい、ただし限られた期間だけです。 Microsoft Entra ID でユーザーが削除または無効化されても、Windows デバイスはそのことをすぐには認識しません。 そのため、以前にサインインしたユーザーは、キャッシュされたユーザー名とパスワードを使ってデスクトップにアクセスできます。

通常、デバイスが認識するユーザーの状態は、4 時間以内の状態です。 その後は、Windows がそれらのユーザーのデスクトップへのアクセスをブロックします。 ユーザーが Microsoft Entra ID で削除または無効化されると、すべてのトークンが取り消されます。 そのため、ユーザーはリソースにアクセスできなくなります。

削除または無効化されたユーザーのうち、以前にサインインしたことのないユーザーは、デバイスにはアクセスできません。 そのようなユーザーには、キャッシュされたユーザー名とパスワードが有効になっていません。

#### ゲスト ユーザーは、Microsoft Entra 参加済みデバイスにサインインできますか?

いいえ。現在、ゲスト ユーザーは Microsoft Entra 参加済みデバイスにサインインできません。

#### ユーザーが Microsoft Entra 参加済みデバイスからプリンターを検索できません。 どうすればそれらのデバイスからの印刷を有効にできますか?

組織は、[事前認証を使用して Windows Server ハイブリッド クラウドプリントをデプロイする](https://learn.microsoft.com/ja-jp/universal-print/)か、Microsoft Entra 参加済みデバイスの[ユニバーサル印刷](https://learn.microsoft.com/ja-jp/universal-print/fundamentals/universal-print-whatis)をすることを選択できます。

#### Microsoft Entra に参加しているリモート デバイスに接続する方法はありますか?

[リモート Microsoft Entra 参加済み PC への接続](https://learn.microsoft.com/ja-jp/windows/client-management/client-tools/connect-to-remote-aadj-pc)に関するページを参照してください。

#### ユーザーに "ここからアクセスすることはできません" というメッセージが表示されるのはなぜですか?

特定のデバイスの状態を要求するように条件付きアクセス ポリシーを構成しましたか? デバイスが条件を満たしていない場合は、ユーザーがブロックされて、そのメッセージが表示されます。 条件付きアクセス ポリシーを評価します。 このメッセージが表示されないようにするには、デバイスが条件を満たしていることを確認してください。

#### Microsoft Entra に参加したばかりのデバイスに対して、"ユーザー名またはパスワードが正しくありません" というメッセージが表示されるのはなぜですか?

このシナリオの一般的な理由は次のとおりです。

- ユーザーの資格情報が有効ではなくなっています。
- コンピューターが Microsoft Entra ID と通信できません。 ネットワーク接続の問題を確認してください。
- フェデレーション サインインでは、有効になっていてアクセスできる WS-Trust エンドポイントがフェデレーション サーバーでサポートされている必要があります。
- パススルー認証が有効になっています。 そのため、サインインするときに一時パスワードを変更する必要があります。

#### Microsoft Entra 参加済みデバイスでユーザーが一時または期限切れのパスワードを変更するにはどうすればよいですか?

現在、Microsoft Entra 参加済みデバイスでは、ユーザーがロック画面でパスワード変更を求められることはありません。 そのため、一時または期限切れのパスワードを持つユーザーは、Windows にログインした後で、(Microsoft Entra トークンを必要とする) アプリケーションにアクセスしたときにのみ、パスワードを変更するように求められます。

#### 申し訳ありません。 エラーが発生しました PC に Microsoft Entra 参加を行おうとするとダイアログが表示されるのはなぜですか?

このエラーは、適切なライセンスを割り当てずに Intune を使って Microsoft Entra の自動登録を設定すると発生します。 Microsoft Entra への参加を試みているユーザーに、適切な Intune ライセンスが割り当てられていることを確認してください。 詳細については、「[Windows デバイスの登録をセットアップする](https://learn.microsoft.com/ja-jp/mem/intune/enrollment/windows-enroll)」をご覧ください。

#### エラー情報が表示されなかったにもかかわらず、PC を Microsoft Entra に参加させることができなかったのはなぜですか?

ユーザーがローカルのあらかじめ登録された Administrator アカウントを使用してデバイスにサインインしていることが原因と考えられます。 Microsoft Entra 参加を使用してセットアップを完了する前に、別のローカル アカウントを作成してください。

#### P2P Server アプリケーションとは何ですか? なぜテナントに登録されているのですか?

P2P Server アプリケーションは、テナント内の任意の Microsoft Entra 参加済みまたは Microsoft Entra ハイブリッド参加済み Windows デバイスへのリモート デスクトップ プロトコル (RDP) 接続を可能にするために、Microsoft Entra ID によって登録されたアプリケーションです。 このアプリケーションは、Microsoft Entra の証明機関から発行されたテナント全体の証明書を作成します。また、RDP 接続用の RDP デバイスおよびユーザー証明書を発行するために使われます。 これが正しいアプリケーションであることを確認するには、**Microsoft Entra 管理センター**&gt;&gt; で P2P サーバー アプリケーションの **[オブジェクト ID]** を確認します。 すべてのアプリケーションを表示するには、適用されている既定のフィルターを削除します。 Microsoft Graph API を使ってこの **[オブジェクト ID]** を比較し、[GET /servicePrincipals/{objectid}](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-get) を使って詳細のクエリを実行し、servicePrincipalNames プロパティが `urn:p2p_cert` であることを確認します。

#### Windows 10/11 デバイスに存在する MS-Organization-P2P-Access 証明書とは何ですか?

Microsoft Entra ID は、MICROSOFT Entra 参加済みデバイスと Microsoft Entra ハイブリッド参加済みデバイスに MS-Organization-P2P-Access 証明書を発行します。 これらの証明書を使用して、リモート デスクトップのシナリオで同じテナント内のデバイス間の信頼を可能にします。 1 つの証明書はデバイスに発行され、もう 1 つはユーザーに発行されます。 デバイス証明書は `Local Computer\Personal\Certificates` 内に存在し、1 日間有効です。 この証明書は、デバイスが Microsoft Entra ID でアクティブなままの場合、(新しい証明書を発行することで) 更新されます。 ユーザー証明書は永続的ではなく、1 時間有効であり、ユーザーが別の Microsoft Entra 参加済みデバイスへのリモート デスクトップ セッションを試みたときにオンデマンドで発行されます。 これは期限切れ時に更新されません。 これらの証明書は、両方とも、`Local Computer\AAD Token Issuer\Certificates` 内に存在する MS-Organization-P2P-Access を使用して発行されます。 Microsoft Entra ID は、デバイスの登録時にこの証明書を発行します。

#### Microsoft Entra 参加済みデバイスでユーザーのキャッシュされたログオンを無効にしたり、キャッシュ ログオンを期限切れにしたりするにはどうすればよいですか?

Microsoft Entra 参加済みデバイスで以前にキャッシュされたログオンを無効にしたり、期限切れにしたりすることはできません。

### Microsoft Entra ハイブリッド参加に関する FAQ

#### Microsoft Entra にハイブリッド参加済みのデバイスをデバイスのローカルで参加解除するにはどうすればよいですか?

Microsoft Entra ハイブリッド参加済みデバイスの場合は、[検証の制御](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-control)に関する記事を参照して、AD での自動登録を無効にしてください。 その後、スケジュールされたタスクはデバイスを再登録しません。 次に、管理者としてコマンド プロンプトを開き、「`dsregcmd.exe /debug /leave`」と入力します。 または、このコマンドを複数のデバイスに対するスクリプトとして実行し、一括で参加を解除します。

#### Microsoft Entra ハイブリッド参加機能のエラーの診断に関するトラブルシューティング情報はどこにありますか?

トラブルシューティング情報については、次の記事をご覧ください。

- [Microsoft Entra ハイブリッドに参加している Windows 10 および Windows Server 2016 デバイスに関するトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-hybrid-join-windows-current)

#### Microsoft Entra ハイブリッド参加済みの Windows 10/11 デバイスの Microsoft Entra 登録済みレコードが、Microsoft Entra デバイスの一覧に重複して表示されるのはなぜですか?

ユーザーがドメイン参加済みデバイス上のアプリに各自のアカウントを追加すると、**[アカウントを Windows に追加しますか?]** というプロンプトが表示されることがあります。プロンプトで "**はい**" と入力すると、デバイスが Microsoft Entra ID に登録されます。 信頼の種類は Microsoft Entra 登録済みとしてマークされます。 組織で Microsoft Entra ハイブリッド参加を有効にすると、デバイスも Microsoft Entra にハイブリッド参加済みとなります。 それにより、同じデバイスに対して、2 つのデバイスの状態が表示されます。

ほとんどの場合、Microsoft Entra ハイブリッド参加は Microsoft Entra 登録済み状態よりも優先されます。その結果、お客様のデバイスは、認証および条件付きアクセスの評価で Microsoft Entra ハイブリッド参加済みと見なされます。 しかし場合によっては、この二重状態が原因でデバイスの評価が非決定的になり、アクセスの問題が発生する可能性があります。 Microsoft Entra 登録済み状態が自動的にクリーンアップされる Windows 10 バージョン 1803 以降にアップグレードすることをお勧めします。 [Windows 10 マシンでこの二重状態を回避またはクリーンアップする](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan#review-things-you-should-know)方法を確認してください。

#### UPN の変更後、Microsoft Entra ハイブリッド参加済みの Windows 10 デバイスでユーザーに問題が生じるのはなぜですか?

UPN の変更は、Windows 10 2004 の更新プログラムでサポートされるほか、Windows 11 でも対応しています。 この更新プログラムがインストールされたデバイスのユーザーには、UPN の変更後も問題は発生しません。

以前のバージョンの Windows 10 での UPN の変更は、Microsoft Entra ハイブリッド参加済みデバイスでは完全にはサポートされていません。 UPN の変更後、ユーザーはデバイスにサインインして、オンプレミス アプリケーションにアクセスできますが、Microsoft Entra ID での認証は失敗します。 その結果、ユーザーのデバイスで SSO と条件付きアクセスの問題が生じます。 この問題を解決するには、Microsoft Entra ID からデバイスの参加を解除 (昇格された特権を使用して "dsregcmd /leave" を実行) し、再度参加する (自動的に行われる) 必要があります。

#### Microsoft Entra ハイリッド参加済みの Windows 10/11 デバイスには、クラウド リソースにアクセスするためのドメイン コントローラーへの通信経路が必要ですか?

ユーザーのパスワードが変更された場合を除いて、必要ありません。 Windows 10/11 Microsoft Entra ハイブリッド参加が完了し、ユーザーが少なくとも 1 回サインインした後、デバイスはクラウド リソースにアクセスするためにドメイン コントローラーへの視線を必要としません。 Windows 10/11 では、インターネット接続があればどこからでも Microsoft Entra アプリケーションへのシングル サインオンが実現できますが、パスワードが変更された場合は例外です。 Windows Hello for Business でサインインしているユーザーは、パスワード変更後、ドメイン コントローラーへの通信経路がなくても、引き続き Microsoft Entra アプリケーションへのシングル サインオンを利用できます。

#### ユーザーがパスワードを変更し、企業ネットワークの外部から Microsoft Entra ハイブリッド参加済みの Windows 10/11 デバイスにサインインしようとするとどうなりますか?

パスワードを企業ネットワークの外部で (たとえば、Microsoft Entra SSPR を使用して) 変更した場合、新しいパスワードを使用したユーザー サインインは失敗します。 Microsoft Entra ハイブリッド参加済みデバイスでは、オンプレミスの Active Directory がプライマリ機関です。 デバイスがドメイン コントローラーに対する通信経路を持たない場合、そのデバイスは新しいパスワードを検証できません。 このため、新しいパスワードでデバイスにサインインするためには、ユーザーはドメイン コントローラーとの接続を (VPN 経由または企業ネットワーク内のいずれかから) 確立する必要があります。 そうしないと、Windows でのキャッシュされたサインインの機能により、古いパスワードでしかサインインできません。 Microsoft Entra は、トークン要求中に古いパスワードを無効にします。 この無効化プロセスにより、シングル サインオンが防止され、ユーザーがアプリまたはブラウザーで新しいパスワードで認証されるまで、デバイスベースの条件付きアクセス ポリシーが失敗します。 Microsoft Entra 参加済みデバイスを使用している場合、この問題は発生しません。

### Microsoft Entra 登録に関する FAQ

#### デバイスの Microsoft Entra 登録済み状態をローカルで削除するにはどうすればよいですか?

- Windows 10/11 の Microsoft Entra 登録済みデバイスの場合、**[設定]**&gt;**[アカウント]**&gt;**[職場または学校にアクセスする]** に移動します。 アカウントを選択し、 **[切断]** を選択します。 デバイスの登録は、Windows 10/11 のユーザー プロファイルごとに行います。
- iOS および Android の場合は、Microsoft Authenticator アプリケーションの **[設定]**&gt;**[デバイスの登録]** で、 **[デバイスの登録を解除する]** を選択します。
- macOS の場合は、Microsoft Intune ポータル サイト アプリケーションを使用して、管理対象からデバイスを登録解除して、登録を削除することができます。

Windows 10 バージョン 2004 以降の場合、このプロセスは、[Workplace Join (WPJ) 削除ツール](https://download.microsoft.com/download/8/e/f/8ef13ae0-6aa8-48a2-8697-5b1711134730/WPJCleanUp.zip)を使用して自動化できます。

注

このツールでは、デバイス上のすべての SSO アカウントが削除されます。 この操作の後、すべてのアプリケーションは SSO 状態を失い、デバイスは管理ツール (MDM) から登録解除され、クラウドから登録解除されます。 次回アプリケーションでサインインが試みられたときに、アカウントを再度追加するように求められます。

#### ユーザーが会社の Windows 10/11 デバイスに職場アカウント (Microsoft Entra 登録済み) を追加できないようにするにはどうすればよいですか?

次のレジストリを有効にすると、会社のドメインに参加済み、Microsoft Entra 参加済み、または Microsoft Entra ハイブリッド参加済みの Windows 10/11 デバイスに、ユーザーが職場アカウントを追加できなくなります。 このポリシーを使用すると、ドメイン参加済みマシンを誤って同じユーザー アカウントで Microsoft Entra に登録するのを防ぐこともできます。

`HKLM\SOFTWARE\Policies\Microsoft\Windows\WorkplaceJoin, "BlockAADWorkplaceJoin"=dword:00000001`
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/how-to-hybrid-join"} -->
## Microsoft Entra ハイブリッド接続を設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join
- Service: entra-id / devices
- Article date: 2025-06-27
- Summary: Microsoft Entra ハイブリッド参加を構成する方法を説明します。

Microsoft Entra ID にデバイスを持ち込むことで、クラウドとオンプレミスのリソースにわたるシングル サインオン (SSO) を通して、ユーザーの生産性を最大化できます。 同時に、[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-alt-all-users-compliant-hybrid-or-mfa)を使用して、リソースへのアクセスを保護できます。

### 前提条件

- [Microsoft Entra Connect](https://www.microsoft.com/download/details.aspx?id=47594)バージョン 1.1.819.0 以降。
    - Microsoft Entra Connect 同期の構成から既定のデバイス属性を除外しないでください。 Microsoft Entra ID へ同期される既定のデバイス属性の詳細については、「[Microsoft Entra Connect によって同期される属性](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-sync-attributes-synchronized#windows-10)」を参照してください。
    - Microsoft Entra ハイブリッド参加にしたいデバイスのコンピューター オブジェクトが、特定の組織単位 (OU) に属している場合は、Microsoft Entra Connect で同期する正しい OU を構成します。 Microsoft Entra Connect を使用してコンピューター オブジェクトを同期する方法の詳細については、「[組織単位ベースのフィルタリング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-configure-filtering#organizational-unitbased-filtering)」を参照してください。
- Microsoft Entra テナントの[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)の資格情報。
- オンプレミスのActive Directory Domain Services のフォレストごとのエンタープライズ管理者の資格情報。
- (**フェデレーションドメインの場合**) 少なくとも Active Directory フェデレーションサービス (AD FS) がインストールされた Windows Server 2012 R2 以上。
- ユーザーは、Microsoft Entra ID を使用してデバイスを登録できます。 この設定の詳細情報については、**デバイス設定の構成**に関する記事の「[デバイス設定の構成](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-device-identities#configure-device-settings)」の見出しの下にあります。

#### ネットワーク接続の要件

Microsoft Entra ハイブリッドの参加では、デバイスが組織のネットワーク内から次の Microsoft リソースにアクセスできる必要があります。

- `https://enterpriseregistration.windows.net`
- `https://login.microsoftonline.com`
- `https://device.login.microsoftonline.com`
- `https://autologon.microsoftazuread-sso.com` (シームレス SSO を使用しているか、使用する予定の場合)
- 組織のセキュリティ トークン サービス (STS) (**フェデレーション ドメインの場合**)

警告

組織で、データ損失防止や Microsoft Entra テナント制限などのシナリオで SSL トラフィックをインターセプトするプロキシ サーバーを使用している場合は、 `https://device.login.microsoftonline.com` と `https://enterpriseregistration.windows.net` へのトラフィックが TLS の中断と検査から除外されていることを確認します。 これらの URL を除外しないと、クライアント証明書の認証に干渉したり、デバイスの登録に関する問題が発生したり、デバイスベースの条件付きアクセスが発生したりする可能性があります。

組織がアウトバウンド プロキシ経由でのインターネットへのアクセスを必要とする場合は、Windows 10 以降のコンピューターを Microsoft Entra ID にデバイス登録できるように、[Web プロキシ自動発見 (WPAD)](https://learn.microsoft.com/ja-jp/previous-versions/tn-archive/cc995261%28v=technet.10%29) を使用することができます。 WPAD の構成と管理の問題に対処するには、「[自動検出のトラブルシューティング](https://learn.microsoft.com/ja-jp/previous-versions/tn-archive/cc302643%28v=technet.10%29)」を参照してください。

WPAD を使用しない場合は、Windows 10 1709 で始まるグループ ポリシー オブジェクト (GPO) を使用して、コンピューターに WinHTTP プロキシ設定を構成できます。 詳細については、「[GPO によってデプロイされる WinHTTP プロキシ設定](https://learn.microsoft.com/ja-jp/archive/blogs/netgeeks/winhttp-proxy-settings-deployed-by-gpo)」を参照してください。

注

WinHTTP 設定を使用して自分のコンピューター上でプロキシ設定を構成すると、構成されたプロキシに接続できないコンピューターは、インターネットに接続できなくなります。

組織が認証されたアウトバウンド プロキシ経由でのインターネットへのアクセスを必要とする場合、お使いの Windows 10 以降のコンピューターがアウトバウンド プロキシに対して正常に認証されることを確認してください。 Windows 10 以降のコンピューターではマシン コンテキストを使用してデバイス登録が実行されるため、マシン コンテキストを使用してアウトバウンド プロキシ認証を構成します。 構成要件については、送信プロキシ プロバイダーに確認してください。

デバイス [登録接続のテスト](https://learn.microsoft.com/ja-jp/samples/azure-samples/testdeviceregconnectivity/testdeviceregconnectivity/) スクリプトを使用して、デバイスがシステムアカウントで必要な Microsoft リソースにアクセスできることを確認します。

### マネージド ドメイン

ほとんどの組織は、マネージド ドメインを使用して Microsoft Entra ハイブリッド参加をデプロイすると考えられます。 管理対象ドメインでは、[シームレスなシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs)による[パスワード ハッシュ同期 (phs)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta)または[パススルー認証 (pta)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso)が使用されます。 マネージド ドメインのシナリオでは、フェデレーションサーバーを構成する必要はありません。

以下のようにマネージド ドメインに対して Microsoft Entra Connect を使用することで Microsoft Entra ハイブリッド参加を構成します。

1. Microsoft Entra Connect を開き、**[構成]** を選択します。
2. **[追加のタスク]** で、 **[デバイス オプションの構成]** を選択し、 **[次へ]** を選択します。
3. **[概要]** で **[次へ]** を選択します。
4. **[Microsoft Entra ID に接続]** で、Microsoft Entra テナントの[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)の資格情報を入力します。
5. **[デバイス オプション]** で、**[Microsoft Entra ハイブリッド参加の構成]** を選択してから、**[次へ]** を選択します。
6. **[デバイスのオペレーティング システム]** で、Active Directory 環境内のデバイスで使用されているオペレーティング システムを選択し、 **[次へ]** を選択します。
7. **[SCP の構成]** で、Microsoft Entra Connect でサービス接続ポイント (SCP) を構成したいフォレストごとに以下の手順を実行した後、**[次へ]** を選びます。

    1. **フォレスト**を選択します。
    2. **認証サービス**を選択します。
    3. **[追加]** を選択して、エンタープライズ管理者の資格情報を入力します。

    [Image: Microsoft Entra Connect と、マネージド ドメインでの SCP 構成のオプションを示すスクリーンショット。]
8. **[構成の準備完了]** で、 **[構成]** を選択します。
9. **[構成が完了しました]** で、 **[終了]** を選択します。

### 連合ドメイン

フェデレーション環境には、以下の要件をサポートする ID プロバイダーが必要です。 Active Directory フェデレーション サービス (AD FS) を使用しているフェデレーション環境がある場合は、以下の要件は既にサポートされています。

- **WS-Trust プロトコル:**このプロトコルは、Microsoft Entra ハイブリッド参加済みデバイスを Microsoft Entra ID で認証するために必要です。 AD FS を使用している場合は、次の WS-Trust エンドポイントを有効にする必要があります。
    - `/adfs/services/trust/2005/windowstransport`
    - `/adfs/services/trust/13/windowstransport`
    - `/adfs/services/trust/2005/usernamemixed`
    - `/adfs/services/trust/13/usernamemixed`
    - `/adfs/services/trust/2005/certificatemixed`
    - `/adfs/services/trust/13/certificatemixed`

警告

**adfs/services/trust/2005/windowstransport** と **adfs/services/trust/13/windowstransport** はどちらも、イントラネットに接続するエンドポイントとしてのみ有効にする必要があります。Web アプリケーション プロキシを介してエクストラネットに接続するエンドポイントとしては公開しないでください。 WS-Trust WIndows エンドポイントを無効にする方法の詳細については、[プロキシの WS-Trust Windows エンドポイントを無効にする方法](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/deployment/best-practices-securing-ad-fs#disable-ws-trust-windows-endpoints-on-the-proxy-ie-from-extranet)に関するセクションを参照してください。 どのエンドポイントが有効になっているかは、AD FS 管理コンソールの **[サービス]**&gt;**[エンドポイント]** で確認できます。

以下のようにフェデレーション環境に対して Microsoft Entra Connect を使用することで Microsoft Entra ハイブリッド参加を構成します。

1. Microsoft Entra Connect を開き、**[構成]** を選択します。
2. **[追加のタスク]** ページで、 **[デバイス オプションの構成]** を選択し、 **[次へ]** を選択します。
3. **[概要]** ページで、 **[次へ]** を選択します。
4. **[Microsoft Entra ID に接続]** ページで、Microsoft Entra テナントの[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)の資格情報を入力してから、**[次へ]** を選択します。
5. **[デバイス オプション]** ページで、**[Microsoft Entra ハイブリッド参加の構成]** を選択してから、**[次へ]** を選択します。
6. **[SCP]** ページで、次の手順を実行し、 **[次へ]** を選択します。

    1. [フォレスト] を選択します。
    2. [認証サービス] を選択します。 組織が Windows 10 以降のクライアントのみを使用していて、ユーザーがコンピューターまたはデバイスの同期を構成済みか組織でシームレス SSO が使用されている場合を除き、**[AD FS サーバー]** を選択する必要があります。
    3. **[追加]** を選択して、エンタープライズ管理者の資格情報を入力します。

    [Image: Microsoft Entra Connect と、フェデレーション ドメインでの SCP 構成のオプションを示すスクリーンショット。]
7. **[デバイスのオペレーティング システム]** ページで、対象の Active Directory 環境内のデバイスで使用されているオペレーティング システムを選択し、 **[次へ]** を選択します。
8. **[フェデレーション構成]** ページで、ご自身の AD FS 管理者の資格情報を入力し、 **[次へ]** を選択します。
9. **[構成の準備完了]** ページで、 **[構成]** を選択します。
10. **[構成が完了しました]** ページで、 **[終了]** を選択します。

#### フェデレーションに関する注意事項

Windows 10 1803 以降では、フェデレーション サービスを使用するフェデレーション環境の即時 Microsoft Entra ハイブリッド参加が失敗した場合、Microsoft Entra Connect に依存して Microsoft Entra ID のコンピューター オブジェクトを同期し、Microsoft Entra ハイブリッド参加のデバイス登録を完了します。

### その他のシナリオ

組織は、完全なロールアウトの前に、環境のサブセット上で Microsoft Entra ハイブリッド参加をテストできます。 対象となるデプロイを完了する手順については、「[Microsoft Entra ハイブリッド参加の対象となるデプロイ](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-control)」の記事で確認できます。 組織には、このパイロットグループ内のさまざまなロールやプロファイルのユーザーのサンプルを含める必要があります。 対象を絞ってロールアウトすると、組織全体に対して有効にする前に、計画で対処されていない可能性のある問題を特定するのに役立ちます。

Microsoft Entra Connect を使用して AD FS を構成できない組織があるかもしれません。 要求を手動で構成する手順については、「[Microsoft Entra ハイブリッド参加の手動構成](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-manual)」の記事で確認できます。

#### 米国政府機関向けクラウド (GCCHigh と DoD を含む)

[Azure Government](https://azure.microsoft.com/global-infrastructure/government/) の組織の場合、Microsoft Entra ハイブリッド参加では、デバイスが組織のネットワーク内から以下の Microsoft リソースにアクセスできる必要があります。

- `https://enterpriseregistration.windows.net`**および**`https://enterpriseregistration.microsoftonline.us`
- `https://login.microsoftonline.us`
- `https://device.login.microsoftonline.us`
- `https://autologon.microsoft.us` (シームレス SSO を使用しているか、使用する予定の場合)

### Microsoft Entra ハイブリッドジョインのトラブルシューティング

ドメイン参加済み Windows デバイスの Microsoft Entra ハイブリッド参加を行うときに問題が発生した場合は、以下を参照してください。

- [dsregcmd コマンドを使用したデバイスのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-device-dsregcmd)
- [Windows 最新デバイスの Microsoft Entra ハイブリッド参加のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-hybrid-join-windows-current)
- [Windows ダウンレベル デバイスの Microsoft Entra ハイブリッド参加のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-hybrid-join-windows-legacy)
- [保留中のデバイス状態のトラブルシューティング](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/pending-devices)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/how-to-hybrid-join-using-microsoft-entra-kerberos"} -->
## Microsoft Entra Kerberos を使用した Microsoft Entra ハイブリッド結合 (プレビュー) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join-using-microsoft-entra-kerberos
- Service: entra-id / devices
- Article date: 2026-02-24
- Summary: Microsoft Entra Kerberos を使用して Microsoft Entra ハイブリッド結合を設定するための前提条件と手順について説明します。

Microsoft Entra Kerberos を使用すると、Active Directory フェデレーション サービス (AD FS) または Microsoft Entra Connect 同期を必要とせずに、デバイスに対して Microsoft Entra ハイブリッド参加を実行できます。AD FS のセットアップなしで、Microsoft Entra ハイブリッド参加の動作をすぐに取得できます。

### 活用事例

プレビューでは、次のユース ケースが有効になっています。

- Microsoft Entra ハイブリッド参加を使用して非永続的仮想デスクトップ インフラストラクチャ (VDI) をマネージド環境にデプロイします。
- Microsoft Entra Cloud Sync を使用中、または使用を希望する顧客向けに Microsoft Entra ハイブリッド結合を展開します。
- Azure Virtual Desktop と Windows 365 ハイブリッドデプロイのプロビジョニング エクスペリエンスを向上させます。
- Microsoft Entra Connect Sync を使用できない、切断されたフォレストのセットアップ用に Microsoft Entra ハイブリッド参加を展開します。

### [前提条件]

Microsoft Entra Kerberos を使用して Microsoft Entra ハイブリッド参加を実行するように、次のアクセス許可と構成が設定されていることを確認します。 ライセンス要件はありません。

- **ロールの要件**: Entra Kerberos Trusted Domain Object を作成して構成するユーザーには、次のロールが必要です。

    - オンプレミスの Active Directory Domain Services の Domain Admins グループと Enterprise Admins グループのメンバー
    - Microsoft Entra ID のハイブリッドアイデンティティ管理者

    詳細については、「 [Microsoft Entra Kerberos Trusted Domain Object の作成と構成](https://learn.microsoft.com/ja-jp/azure/azure-sql/managed-instance/winauth-azuread-setup-incoming-trust-based-flow#permissions)」を参照してください。
- **キー配布センター (KDC) プロキシ サーバーグループ ポリシー オブジェクト (GPO) を構成**する: この前提条件は、KDC プロキシ サーバー GPO をクライアント コンピューターに展開した場合にのみ必要です。 GPO を構成するユーザーは、ドメイン管理者であるか、GPO を構成するための委任されたアクセス許可である必要があります。
- **Microsoft Entra Device Registration Service Principal の構成**: Microsoft Entra デバイス登録サービス プリンシパルに Kerberos エントリを追加します。 サービス プリンシパルを構成するユーザーには、 [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) ロールが必要です。
- **Windows Server 2025 を実行するドメイン コントローラーを展開**する: Active Directory ドメインに Windows Server 2025 [ビルド 26100.6905](https://support.microsoft.com/topic/october-23-2025-kb5070881-os-build-26100-6905-out-of-band-8e7ac742-6785-4677-87e4-b73dd8ac0122) 以降を実行するドメイン コントローラーを少なくとも 1 つインストールします。
- **Entra Kerberos ベースの参加用にクライアント コンピューターを構成する: Entra Kerberos** を使用して Entra ハイブリッド参加として Entra に登録するクライアント コンピューターに [Windows 11 ビルド 26100.6584](https://support.microsoft.com/topic/september-9-2025-kb5065426-os-build-26100-6584-77a41d9b-1b7c-4198-b9a5-3c4b6706dea9) 以降を展開する必要があります。

    注

    クライアント コンピューターは、参加中に Windows Server 2025 を実行するドメイン コントローラーとのネットワーク接続が妨げされていない必要があります。
- **サービス接続ポイント (SCP) の構成:** PowerShell を使用して、Microsoft Entra Connect を使用するか、Active Directory Domain Services (AD DS) に書き込むことができます。 詳細については、「 [サービス接続ポイントの構成](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-manual#configure-a-service-connection-point)」を参照してください。

### Microsoft Entra Kerberos の信頼されたドメイン オブジェクトを作成して構成する

[「着信信頼ベースのフローを使用して Microsoft Entra ID の Windows 認証を設定する方法」の手順に](https://learn.microsoft.com/ja-jp/azure/azure-sql/managed-instance/winauth-azuread-setup-incoming-trust-based-flow#create-and-configure-the-microsoft-entra-kerberos-trusted-domain-object)従います。

### KDC プロキシ サーバー GPO を構成する

クライアント コンピューターに KDC プロキシ サーバー GPO を展開しなかった場合は、このセクションをスキップします。

1. [Microsoft Entra テナント ID を](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-to-find-tenant)見つけます。
2. 着信信頼ベースのフローを使用して、次のグループ ポリシー設定をクライアント コンピューターに展開します。
    1. 「**管理用テンプレート\システム\KERBEROS\Kerberos クライアントのKDC プロキシ サーバーの指定**」ポリシー設定を編集します。

        - ポリシーが **未構成**の場合は、手順 e に進みます。
        - ポリシーが **[無効] の**場合は、[ **有効]** を選択します。
    2. [ **オプション] で** 、[ **表示]..**. を選択します。[コンテンツの **表示** ] ダイアログ ボックスが開きます。

        [Image: Kerberos クライアントの KDC プロキシ サーバーの指定を有効にするダイアログ ボックスのスクリーンショット。]
    3. 次のマッピングを使用して、KDC プロキシ サーバーの設定を定義します。 `your_Microsoft Entra_tenant_id`を Microsoft Entra テナント ID に置き換えます。 **空白は、httpsの後と値マッピングの末尾の / の前に表示されます**。

        | **値の名前** | **価値** |
        | --- | --- |
        | KERBEROS.MICROSOFTONLINE.COM | `https login.microsoftonline.com:443:your_Microsoft Entra_tenant_id/kerberos /` |

        [Image: [KDC プロキシ サーバー設定の定義] ダイアログ ボックスのスクリーンショット。]
    4. [ **OK] を** 選択して、[ **コンテンツの表示** ] ダイアログ ボックスを閉じます。
    5. [ **Kerberos クライアントの KDC プロキシ サーバーの指定** ] ダイアログ ボックスで、[ **適用**] を選択します。

#### Microsoft Entra デバイス登録サービス プリンシパルの構成

1. [Microsoft Entra PowerShell をインストールします](https://learn.microsoft.com/ja-jp/powershell/entra-powershell/installation)。
2. 管理者特権の PowerShell セッションを開くには、 **管理者として実行**を選択します。
3. 次の PowerShell コマンドを実行し、 [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) ロールを持つ Microsoft Entra アカウントを使用して認証します。

    ```powershell
    Connect-Entra -Environment ‘Global’ -Scopes "Application.ReadWrite.All"
    ```

    注

    Azure for US Government と Microsoft ソブリン クラウドには、異なる環境名が必要です。 `Get-EntraEnvironment`を実行して、定義済みの環境の一覧を取得します。
4. 次のコマンドを実行して、サービス プリンシパルの設定を確認します。

    ```powershell
    $drsSP = Get-EntraServicePrincipal -Filter "AppId eq '01cb2876-7ebd-4aa4-9cc9-d28bd4d359a9'"
    $drsSP.ServicePrincipalNames
    ```
5. 表示されているサービス プリンシパル名を確認します。 `adrs/enterpriseregistration.windows.net`一覧にない場合は、次のコマンドを実行して追加します。

    ```powershell
    $spns = [System.Collections.Generic.List[string]]::new($drsSP.ServicePrincipalNames)
    $kerbSpn = "adrs/enterpriseregistration.windows.net"
    $spns.Add($kerbSpn)
    Set-EntraServicePrincipal -ObjectId $drsSp.ObjectId -ServicePrincipalNames $spns
    $drsSP.ServicePrincipalNames
    ```

    注

    表示されたサービス プリンシパル名をもう一度確認します。 `adrs/enterpriseregistration.windows.net` サービス プリンシパル名は一覧されるべきです。 Azure for US Government と Microsoft ソブリン クラウドのサービス プリンシパル名は異なります。
6. 次のコマンドを実行して、サービス プリンシパルのタグを確認します。

    ```powershell
    $drsSP.Tags
    ```
7. 表示されているタグを確認します。 `KerberosPolicy:ExchangeForJwt`が表示されない場合は、次のコマンドを実行して追加します。

    ```powershell
    $tags = [System.Collections.Generic.List[string]]::new($drsSP.Tags)  
    $tags.Add("KerberosPolicy:ExchangeForJwt")
    Set-EntraServicePrincipal -ObjectId $drsSP.ObjectId -Tags $tags
    ```

または、次のスクリプトを実行できます。

```powershell
# Use specific environment for your sovereign cloud
# Run Get-EntraEnvironment to retrieve the list of predefined environments

Connect-Entra -Environment 'Global' -Scopes "Application.ReadWrite.All"

# Get the service principal

$drsSP = Get-EntraServicePrincipal -Filter "AppId eq '01cb2876-7ebd-4aa4-9cc9-d28bd4d359a9'"

# Prepare both updates

$needsUpdate = $false
$spns = [System.Collections.Generic.List[string]]::new($drsSP.ServicePrincipalNames)
$tags = [System.Collections.Generic.List[string]]::new($drsSP.Tags)

# Configure Kerberos SPN

$kerbSpn = "adrs/enterpriseregistration.windows.net"
if ($drsSP.ServicePrincipalNames -notcontains $kerbSpn) {
Write-Host "Kerberos SPN needs to be added"
$spns.Add($kerbSpn)
$needsUpdate = $true

}

# Configure Kerberos policy tag

$kerberosTag = "KerberosPolicy:ExchangeForJwt"
if ($drsSP.Tags -notcontains $kerberosTag) {
Write-Host "Kerberos policy tag needs to be added"
$tags.Add($kerberosTag)
$needsUpdate = $true

}

# Single update operation

if ($needsUpdate) {
Write-Host "Updating service principal configuration..."
Set-EntraServicePrincipal -ObjectId $drsSP.Id -ServicePrincipalNames $spns -Tags $tags
Write-Host "Service principal configuration updated successfully"
} else {
Write-Host "Service principal already configured correctly"

}

# Display final configuration

$drsSP = Get-EntraServicePrincipal -Filter "AppId eq '01cb2876-7ebd-4aa4-9cc9-d28bd4d359a9'"
Write-Host "`nFinal Configuration:"
Write-Host "SPNs:"
$drsSP.ServicePrincipalNames | ForEach-Object { Write-Host " $_" }
Write-Host "Tags:"
$drsSP.Tags | ForEach-Object { Write-Host " $_" }
```

### Windows Server 2025 を実行するドメイン コントローラーを展開する

ドメイン コントローラーをドメインに展開するには、次の [手順](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/deploy/upgrade-domain-controllers) に従います。 ドメイン コントローラーで Windows Server 2025 [ビルド 26100.6905 以降が](https://support.microsoft.com/topic/october-23-2025-kb5070881-os-build-26100-6905-out-of-band-8e7ac742-6785-4677-87e4-b73dd8ac0122) 実行されていることを確認します。

注

Microsoft Entra Kerberos を使用して Microsoft Entra ハイブリッド参加を実行するすべてのドメインに、Windows Server 2025 を実行するドメイン コントローラーをインストールする必要があります。

#### Microsoft Entra Kerberos 参加用にクライアント コンピューターを構成する

1. クライアント コンピューターに [Windows 11 ビルド 26100.6584](https://support.microsoft.com/topic/september-9-2025-kb5065426-os-build-26100-6584-77a41d9b-1b7c-4198-b9a5-3c4b6706dea9) 以降を展開するか、Windows Update を使用して既存の Windows 11 クライアント コンピューターを更新します。
2. クライアント コンピューターを Active Directory ドメインに参加させ、再起動します。

### FAQ

**質問**: Entra Kerberos を使用した Microsoft Entra ハイブリッド結合は失敗しますか?

**回答**: 参加中に Windows Server 2025 を実行するドメイン コントローラーとのネットワーク接続が妨げられ、その他の前提条件が不完全な場合、エラーが発生 する 可能性があります。

### トラブルシューティング

エラーのトラブルシューティングについては、次の記事を参照してください。

- [ハイブリッド参加済みデバイスのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-hybrid-join-windows-current)
- [Kerberos イベント ログを有効にする](https://learn.microsoft.com/ja-jp/troubleshoot/windows-server/active-directory/enable-kerberos-event-logging)

Kerberos ログを収集するには、次の手順に従います。

1. https://aka.ms/authscriptsからログ スクリプトをダウンロードして解凍します。
2. Windows Server 2025 を実行するドメイン コントローラーが複数ある場合は、1 つを除くすべてのドメイン コントローラーで KDC サービスを無効にします。 次のコマンドを実行して、ドメイン コントローラー上の KDC サービスを停止します。

    ```
    net stop kdc
    ```
3. KDC サービスが実行されている Windows Server 2025 を実行する残りのドメイン コントローラーで、管理者特権で PowerShell ウィンドウを開き、次のスクリプトを実行します。

    ```powershell
    start-auth.ps1
    ```
4. クライアント コンピューターで、管理者特権で PowerShell ウィンドウを開き、次のスクリプトを実行します。

    ```powershell
    start-auth.ps1
    ```
5. クライアント コンピューターで、管理者特権で cmd ウィンドウを開き、次のコマンドを実行します。

    ```
    dsregcmd /join
    ```
6. クライアント コンピューターの PowerShell ウィンドウで、次のスクリプトを実行します。

    ```powershell
    stop-auth.ps1
    ```
7. KDC サービスが実行されている Windows Server 2025 を実行しているドメイン コントローラーの PowerShell ウィンドウで、次のスクリプトを実行します。

    ```powershell
    stop-auth.ps1
    ```
8. Windows Server 2025 を実行する追加のドメイン コントローラーで KDC サービスを再起動できます。 次のコマンドを実行して、ドメイン コントローラーで KDC サービスを開始します。

    ```
    net start kdc
    ```

### Kerberos エラー

| **エラー コード** | **説明** | **理由** | **Resolution** |
| --- | --- | --- | --- |
| DSREG\_TOKEN\_MISSING\_ON\_PREM\_ID(0x801c0095) | トークンにはオンプレミス ID が含まれません。 | オンプレミスの Kerberos 機関からの Kerberos チケットには、Microsoft Entra ID に必要な情報が含まれません。 | Active Directory ドメイン内で Windows Server 2025 を実行するすべてのドメイン コントローラーで、[ビルド 26100.6905 以降が](https://support.microsoft.com/topic/october-23-2025-kb5070881-os-build-26100-6905-out-of-band-8e7ac742-6785-4677-87e4-b73dd8ac0122)実行されていることを確認します。 |
| SEC\_E\_NO\_AUTHENTICATING\_AUTHORITY(0x80090311) | 認証用の機関に接続できませんでした。 | Windows Server 2025 を実行する機能ドメイン コントローラーに接続することはできません。 | Active Directory ドメインWindows Server [2025 ビルド 26100.6905](https://support.microsoft.com/topic/october-23-2025-kb5070881-os-build-26100-6905-out-of-band-8e7ac742-6785-4677-87e4-b73dd8ac0122) 以降を実行するドメイン コントローラーを少なくとも 1 つインストールします。 KDC サービスがこのドメイン コントローラーで実行されており、このドメイン コントローラーに、ハイブリッド参加を実行しようとしているコンピューターから接続できることを確認します。 ドメイン コントローラーで dcdiag.exe を実行し、ドメイン コントローラー自体がアドバタイズされていることを確認します。 |
| Kerberos イベント ログのSEC\_E\_TARGET\_UNKNOWN (0x80090303) エラー コード: KDC\_ERR\_S\_PRINCIPAL\_UNKNOWN (0x7) | 指定されたターゲットが不明または到達不能です。 |  |  |
| Kerberos イベント ログのSEC\_E\_LOGON\_DENIED (0x8009030c) エラー コード: KDC\_ERR\_NULL\_KEY (0x9) | ログオンの試行に失敗しました。 Kerberos エラー: `No KerberosKeyInformation Keys found.` | Microsoft Entra デバイス登録サービス プリンシパルの Kerberos キーが見つかりません。 | **KerberosPolicy:ExchangeForJwt** タグをサービス プリンシパルに追加します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/how-to-hybrid-join-verify"} -->
## Microsoft Entra ハイブリッド結合の状態を確認する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join-verify
- Service: entra-id / devices
- Article date: 2024-11-25
- Summary: Microsoft Entra ハイブリッド参加済みデバイスの構成を確認する

この記事では、Microsoft Entra ハイブリッド参加済みデバイスの状態を見つけて確認する 3 つの方法について説明します。

### 前提条件

なし

### デバイス上でローカルに

次の手順に従います。

1. Windows PowerShell を開きます。
2. 「`dsregcmd /status`」と入力します。
3. **AzureAdJoined と DomainJoined の**両方が **YES** に設定されていることを確認します。
4. **DeviceId** を使用し、Microsoft Entra 管理センターまたは PowerShell を使用してサービスの状態を比較できます。

### Microsoft Entra 管理センターの使用

次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド デバイス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-device-administrator)としてサインインします。
2. **Entra ID**&gt;**Devices**&gt;**All デバイス**を参照します。
3. **[登録済み**] 列に **[保留中]** と表示されている場合、Microsoft Entra ハイブリッド結合は完了していません。 フェデレーション環境では、この状態は登録に失敗し、Microsoft Entra Connect がデバイスを同期するように構成されている場合にのみ発生します。 Microsoft Entra Connect が同期サイクルを完了するまで待ちます。
4. **登録済み列に** **日付/時刻**が含まれている場合、Microsoft Entra ハイブリッド結合は完了しています。

### PowerShell の使用

[Get-MgDevice](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdevice) を使用して、Azure テナントのデバイス登録状態を確認します。 このコマンドレットは、Microsoft Graph PowerShell SDK にあります。

**Get-MgDevice** コマンドレットを使用してサービスの詳細を確認する場合:

- Windows クライアントの ID と一致する **デバイス ID** を持つオブジェクトが存在する必要があります。
- **DeviceTrustType** の値は**ドメイン参加済みです**。 この設定は、Microsoft Entra 管理センターの **[デバイス**] ページの Microsoft **Entra ハイブリッド参加**状態と同じです。
- 条件付きアクセスで使用されるデバイスの場合、[ **有効]** の値は **True** で **、DeviceTrustLevel** は **[管理] です**。

1. 管理者として Windows PowerShell を開きます。
2. Connect-MgGraph  入力して、Azure テナントに接続します。

#### Microsoft Entra ハイブリッド参加済みデバイスをすべてカウントする (保留中の状態 **を** 除く)

    ```powershell
    (Get-MgDevice -All | where {($_.TrustType -eq 'ServerAd') -and ($_.ProfileType -eq 'RegisteredDevice')}).count
    ```

#### **保留中**の状態のすべての Microsoft Entra ハイブリッド参加済みデバイスをカウントする

    ```powershell
    (Get-MgDevice -All | where {($_.TrustType -eq 'ServerAd') -and ($_.ProfileType -ne 'RegisteredDevice')}).count
    ```

#### Microsoft Entra ハイブリッド参加済みデバイスをすべて一覧表示する

    ```powershell
    Get-MgDevice -All | where {($_.TrustType -eq 'ServerAd') -and ($_.ProfileType -eq 'RegisteredDevice')}
    ```

#### **保留中**の状態のすべての Microsoft Entra ハイブリッド参加済みデバイスを一覧表示する

    ```powershell
    Get-MgDevice -All | where {($_.TrustType -eq 'ServerAd') -and ($_.ProfileType -ne 'RegisteredDevice')}
    ```

#### 1 つのデバイスの詳細を一覧表示します。

    1. 次のコマンドを入力します。 デバイス上でローカルにデバイス ID を取得します。

    ```powershell
    $Device = Get-MgDevice -DeviceId <ObjectId>
    ```

    1. `AccountEnabled` が `True`に設定されていることを確認します。

    ```powershell
    $Device.AccountEnabled
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/howto-arc-sign-in-windows"} -->
## Microsoft Entra IDを使用してAzure Arc対応サーバーにサインインする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-arc-sign-in-windows
- Service: entra-id / devices
- Article date: 2026-03-01
- Summary: Microsoft Entra 認証を使用して、Windows を実行しているAzure arc対応サーバーにサインインする方法について説明します。

組織は、Microsoft Entra 認証と統合することで、オンプレミスのAzure Arc対応サーバーのセキュリティを向上させることができます。 Microsoft Entra ID を、Windows Server 2025 以降および Windows 11 24H2 以降へのリモート デスクトップ プロトコル (RDP) のコア認証プラットフォームとして使用できるようになりました。 その後、サーバーへのaccessを許可または拒否Azureロールベースのaccess control ポリシーを一元的に制御および適用できます。

この記事では、Azure Arc対応 Windows Server を作成して構成し、Microsoft Entra ID ベースの認証を使用してサインインする方法について説明します。

Microsoft Entra ID ベースの認証を使用して、Azure Arcを使用して接続された Windows Server マシンにサインインすると、多くのセキュリティ上の利点があります。次のものが含まれます。

- Windows Server マシンにサインインするには、パスワードレスを含む Microsoft Entra 認証を使用します。 ローカル管理者アカウントへの依存度を下げる。
- Microsoft Entra ID用に構成するパスワードの複雑さとパスワードの有効期間ポリシーを使用すると、Windows Server マシンのセキュリティ保護にも役立ちます。
- Azureのロールベースアクセス制御を使用します。
    - 仮想マシン管理者ログインロールと仮想マシン ユーザー ログイン ロールを使用して、通常のユーザーまたは管理者特権でサインインできるユーザーを指定します。
    - ユーザーがチームに参加または退出するときに、Azure のロールベースのアクセス制御ポリシーを更新して、必要に応じてアクセスを付与できます。
    - 従業員が組織を離れ、ユーザー アカウントが無効になったり、Microsoft Entra IDから削除されたりすると、リソースにaccessできなくなります。
- Azure Policyを使用して、Windows Server マシンに Microsoft Entra のサインインを要求するポリシーを展開および監査し、コンピューターで未承認のローカル アカウントの使用にフラグを設定します。
- セキュリティ要件と Windows Server のバージョンに応じて、パスワードレス認証方法とパスワードベースの認証をサポートします。

Important

この機能を有効にすると、Arc 対応マシンが Microsoft Entra に参加します。 このシナリオは、オンプレミスの Active DirectoryやMicrosoft Entra Domain Servicesなど、別のドメインに参加しないように計画されているマシンを対象とします。 必要な場合は、拡張機能をアンインストールしてデバイスをMicrosoft Entraから切断し、代わりに [Entra ハイブリッド参加](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join)ソリューションを使用します。 さらに、サポートされているゴールデン イメージをデプロイする場合は、拡張機能をインストールしてMicrosoft Entra ID認証を有効にすることができます。 条件付きAccessは、Azure Arc対応サーバーの Microsoft Entra 参加拡張機能を使用する Windows Server ではサポートされていません。

### Requirements

#### サポートされているAzureリージョンと Windows ディストリビューション

この機能は現在、次の Windows Server ディストリビューションをサポートしています。

- Windows 11 24H2 以降がインストールされています。
- デスクトップ エクスペリエンスと共にインストールされた Windows Server 2025 以降。

この機能は、次のAzure クラウドで使用できるようになりました。

- Azure グローバル
- Azure Government
- 21Vianet が運営する Microsoft Azure

注

CIS 強化イメージは、Microsoft Windows Enterprise および Microsoft Windows Server オファリングのMicrosoft Entra ID認証をサポートします。 詳細については、「 [Microsoft Windows Enterprise の CIS セキュリティ強化イメージ」を](https://azuremarketplace.microsoft.com/marketplace/apps/center-for-internet-security-inc.cis-windows-server)参照してください。

#### ネットワークの要件

Arc 対応 Windows Server に対して Microsoft Entra 認証を有効にするには、ネットワーク構成で TCP ポート 443 経由で次のエンドポイントへの送信accessが許可されていることを確認する必要があります。

Azure グローバル:

- `https://enterpriseregistration.windows.net`: デバイスの登録。
- `http://localhost:40342`: Arc Instance Metadata Service エンドポイント。
- `https://login.microsoftonline.com`: 認証フロー。
- `https://pas.windows.net`: Azure のロールベースのアクセスコントロールフロー。

Azure Government:

- `https://enterpriseregistration.microsoftonline.us`: デバイスの登録。
- `http://localhost:40342`: Arc Instance Metadata Service エンドポイント。
- `https://login.microsoftonline.us`: 認証フロー。
- `https://pasff.usgovcloudapi.net`: Azure のロールベースのアクセスコントロールフロー。

21Vianet が運営する Microsoft Azure:

- `https://enterpriseregistration.partner.microsoftonline.cn`: デバイスの登録。
- `http://localhost:40342`: Arc Instance Metadata Service エンドポイント。
- `https://login.chinacloudapi.cn`: 認証フロー。
- `https://pas.chinacloudapi.cn`: Azure のロールベースのアクセスコントロールフロー。

その他のネットワーク要件については、[Arc に接続されたサーバーのドキュメント](https://learn.microsoft.com/ja-jp/azure/azure-arc/servers/network-requirements)を参照してください。

Azure Arc対応の Windows Server については、[Arc に接続されたサーバーのドキュメント](https://learn.microsoft.com/ja-jp/azure/azure-arc/servers/network-requirements)で、より多くのネットワーク要件が提供されています。

#### 認証の要件

Microsoft Entra IDを使用してAzure Arc対応 Windows Server に正常に認証してサインインするには、次の要件を満たす必要があります。

- **User アカウントの種類**: Azure Arc対応サーバーと同じテナントの標準の Microsoft Entra ユーザー アカウントのみがサポートされます。 [ゲスト アカウント (B2B ユーザー)](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) を認証に使用することはできません。
- **ロールの割り当て**: ユーザーには、次のいずれかのAzureロールが割り当てられている必要があります。

    - **仮想マシン管理者ログイン**: サーバーに対する管理者特権を付与します。
    - **仮想マシン ユーザー ログイン**: サーバーに対する標準ユーザー特権を付与します。
        注

        ローカル管理者グループのメンバーにユーザーを追加するか、コマンドを実行してユーザーをデバイスのローカル管理者に手動で昇格 `net localgroup administrators /add "AzureAD\UserUpn"` サポートされていません。 サインインを承認するには、Azureでロールを使用する必要があります。

注

所有者ロールまたは共同作成者ロールが割り当てられているAzure ユーザーは、デバイスにサインインする権限を自動的に持ちません。 その理由は、仮想マシンを制御するユーザーのグループと、仮想マシンにアクセスすることができるユーザーのグループの間で、監査された分離を提供するためです。

- **認証方法**: 次の認証方法がサポートされています。

    - **パスワードレス認証**: パスキー、FIDO2 セキュリティ キー、その他のパスワードレスメソッドなど、Microsoft Entra 資格情報を使用した Web アカウントサインインをサポートします。
    - **パスワードベースの認証**: Microsoft Entra 登録済みデバイスのユーザーは、 `AzureAD\UPN` 形式 ( `AzureAD\john@contoso.com` など) を使用する必要があります。
- **クライアント デバイスの要件**: RDP 接続を開始するデバイスは次の条件を満たす必要があります。

    - Microsoft Entra が Arc 対応サーバーと同じディレクトリに参加しているか、または
    - 同じディレクトリに参加している Microsoft Entra ハイブリッド、または
    - Microsoft Entra が同じディレクトリに登録されました (Windows 10 20H1 以降)。
- **Password の制限**: 一時パスワードをremote desktop接続に使用することはできません。 一時パスワードを持つユーザーは、接続を試みる前に、別の方法 (Azure portal など) を使用してパスワードを変更する必要があります。

### Arc 対応 Windows Server の Microsoft Entra サインインを有効にする

Arc 対応 Windows Server に Microsoft Entra サインインを使用するには、次の手順を実行する必要があります。

1. デバイスの Microsoft Entra サインイン拡張機能を有効にします。
2. ユーザーに対する Azure ロールの割り当てを構成します。

#### Microsoft Entra サインイン拡張機能を有効にする

Arc 対応 Windows サーバーの使い方とその他の例については、「[Arc 対応 Windows Server](https://learn.microsoft.com/ja-jp/azure/azure-arc/servers/manage-vm-extensions)を参照してください。

Microsoft Entra サインイン仮想マシン拡張機能をインストールする前に、Arc 対応 Windows Server でシステム割り当てマネージド ID を有効にする必要があります。 マネージド ID は 1 つの Microsoft Entra テナントに格納され、現在、クロス ディレクトリ シナリオはサポートされていません。

次の例では、Arc 対応 Windows Server 拡張機能のAzure テンプレートを示します。

```json
{
  "$schema": "https://schema.management.azure.com/schemas/2019-04-01/deploymentTemplate.json#",
  "contentVersion": "1.0.0.0",
  "parameters": {
    "vmName": {
      "type": "string"
    },
    "location": {
      "type": "string"
    }
  },
  "resources": [
    {
      "name": "[concat(parameters('vmName'),'/AADLogin')]",
      "type": "Microsoft.HybridCompute/machines/extensions",
      "location": "[parameters('location')]",
      "apiVersion": "2024-07-10",
      "properties": {
        "publisher": "Microsoft.Azure.ActiveDirectory",
        "type": "AADLoginForWindows",
        "typeHandlerVersion": "2.1.0.0",
        "autoUpgradeMinorVersion": true,
        "settings": {
          "mdmId": ""
        }
      }
    }
  ]
}
```

注

Arc 対応 Windows Server の Microsoft Entra サインイン拡張機能には、`mdmId`内に入れ子になった `settings` プロパティが必要です。 プロパティの値は空の文字列として残すことができます。

拡張機能がデバイスにインストールされると、 `provisioningState` に `Succeeded`が表示されます。

### ロールの割り当てを構成する

ユーザーが Arc に接続された Windows Server へのサインインを許可される前に、microsoft Entra のユーザー アカウントを Azure のロールの割り当てに追加する必要があります。

次のドキュメントでは、Azureのロールの割り当てにユーザー アカウントを追加する手順について説明します。

- [Azure ポータルを使用して Azure ロールを割り当てる](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal)
- [Azure CLI を使用して Azure のロールを割り当てる](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-cli)
- [Azure PowerShell を使用して Azure ロールを割り当てる](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-powershell)

### Arc 対応 Windows Server に Microsoft Entra 資格情報を使用してサインインする

RDP 経由のサインインには、以下の 2 つの方法があります。

- パスワードレス (サポートされている Microsoft Entra 資格情報のいずれかを使用) (推奨)
- 証明書信頼モデルを使用してデプロイされた Windows Hello for Business を使用したパスワード/パスワードレス

#### Microsoft Entra IDでパスワードレス認証を使用してサインインする

Arc 対応 Windows Server にパスワードレス認証を使用するには、サーバーで Windows Server 2025 以降が実行されている必要があります。

注

**web アカウントを使用してリモート コンピューター** オプションにサインインする場合、ローカル デバイスをドメインまたはMicrosoft Entra IDに参加させる必要はありません。

リモート コンピュータに接続するには:

- Windows Search から **リモート デスクトップ Connection** を起動するか、`mstsc.exe` を実行します。
- **[詳細設定]** タブの **[Web アカウントを使用してリモート コンピューターにサインインする]** オプションを選択します。このオプションは `enablerdsaadauth` RDP プロパティに相当します。 詳細については、[リモート デスクトップ サービスでサポートされている RDP プロパティ](https://learn.microsoft.com/ja-jp/windows-server/remote/remote-desktop-services/clients/rdp-files)を参照してください。
- リモート コンピューターの名前を指定し、**[接続]** を選択します。

Important

IP アドレスは、[ **Web アカウントを使用してリモート コンピューターにサインインする** ] オプションでは使用できません。 名前は、Microsoft Entra ID内のリモート デバイスのホスト名と一致し、リモート デバイスの IP アドレスに解決して、ネットワーク アドレス指定可能である必要があります。

- 資格情報の入力を求められたら、ユーザー名を `user@domain.com` 形式で指定します。
- その後、新しい PC に接続するときに、remote desktop接続を許可するように求められます。 Microsoft Entra では、このプロンプトが再び表示されるまでの 30 日間にわたって、最大 15 個のホストが記憶されます。 このダイアログが表示された場合は、**[はい]** を選択して接続します。

注

リモート セッションの Windows ロック画面では、Microsoft Entra 認証トークンや、FIDO キーなどのパスワードレス認証方法はサポートされていません。 これらの認証方法がサポートされていないということは、ユーザーがリモート セッションで画面のロックを解除できないことを意味します。 ユーザー アクションまたはシステム ポリシーを使用してリモート セッションをロックしようとすると、セッションは切断され、サービスはユーザーにメッセージを送信します。 セッションを切断すると、非アクティブな期間が経過した後に接続が再開されるときに、Microsoft Entra ID が適用可能な条件付きアクセス ポリシーを再評価します。

#### Microsoft Entra IDでパスワード/パスワードレス認証を使用してサインインする

Arc 対応 Windows Server へのサインインでは、[パスワードベース](https://learn.microsoft.com/ja-jp/entra/architecture/auth-password-based-sso)[の認証とパスワードレス認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)の両方がサポートされています。

Important

Microsoft Entra IDに参加している Arc 対応サーバーへのリモート接続は、Microsoft Entra 登録済みまたは Microsoft Entra 参加済み、または Microsoft Entra ハイブリッドがサーバーとして *same* ディレクトリに参加しているクライアント デバイスからのみ許可されます。 さらに、Microsoft Entra 資格情報を使用して RDP を実行するには、仮想マシン管理者ログインまたは仮想マシン ユーザー ログインという 2 つのAzureロールのいずれかにユーザーが属している必要があります。

Microsoft Entra 登録済みクライアント デバイスを使用している場合は、 `AzureAD\UPN` 形式 ( `AzureAD\john@contoso.com` など) で資格情報を入力する必要があります。 現在は、Azure Bastion を使用して、[Azure CLI やネイティブ RDP クライアント mstsc](https://learn.microsoft.com/ja-jp/azure/bastion/native-client) を介して Microsoft Entra でサインインできます。

Microsoft Entra ID を使用して Arc 対応 Windows Server にサインインするには:

1. Windows Search から **リモート デスクトップ Connection** を起動するか、`mstsc.exe` を実行します。
2. Arc 対応サーバーの名前を指定し、[ **接続**] を選択します。
3. メッセージが表示されたら、 `AzureAD\UPN` 形式 ( `AzureAD\john@contoso.com` など) で資格情報を入力します。
4. Microsoft Entra 資格情報を使用してサインインします。

これで、VM ユーザーや VM 管理者など、ロールのアクセス許可が割り当てられた Arc 対応 Windows Server にサインインします。

### Azure Policyを使用して標準を満たし、コンプライアンスを評価する

Azure Policyを使用して次の手順を実行します。

- 新規および既存の Arc 対応 Windows Server に対して Microsoft Entra サインインが有効になっていることを確認します。
- コンプライアンス ダッシュボードで環境のコンプライアンスを大規模に評価する。

この機能により、さまざまなレベルの適用を使用できます。 Microsoft Entra サインインが有効になっていない環境内の新規および既存の Arc 対応 Windows Server にフラグを設定できます。 Azure Policyを使用して、Microsoft Entra 拡張機能を Arc 対応 Windows Server にデプロイすることもできます。

これらの機能に加えて、Azure Policyを使用して、デバイスで未承認のローカル アカウントが作成された Windows マシンを検出してフラグを設定できます。 詳細については、[Azure Policy](https://learn.microsoft.com/ja-jp/azure/governance/policy/overview)を確認してください。

### デプロイの問題に関するトラブルシューティング

デバイスが Microsoft Entra 参加プロセスを完了するには、AADLoginForWindows 拡張機能が正常にインストールされている必要があります。 拡張機能が正しくインストールされない場合は、次の手順を実行します。

1. デバイスに接続し、*C:\WindowsAzure\Logs\Plugins\Microsoft.Azure.ActiveDirectory.AADLoginForWindows\1.0.0.1* の下にある *CommandExecution.log* ファイルを調べます。

    最初の失敗後に拡張機能を再起動すると、デプロイ エラーを含むログが、*CommandExecution\_YYYYMMDDHHMMSSSSS.log* として保存されます。
2. デバイスで PowerShell ウィンドウを開きます。 ホスト上で実行されている Azure インスタンス メタデータ サービス エンドポイントに対する次のクエリで、期待される出力が返されることを確認します。

    Arc 対応 Windows サーバーの場合:

    | 実行するコマンド | 想定される出力 |
    | --- | --- |
    | `curl.exe -H Metadata:true "http://localhost:40342/metadata/instance?api-version=2017-08-01"` | Azure Arc対応 Windows Server に関する正しい情報 |
    | `curl.exe -H Metadata:true "http://localhost:40342/metadata/identity/info?api-version=2018-02-01"` | Azure サブスクリプションに関連付けられている有効なテナント ID |
    | `curl.exe -H Metadata:true "http://localhost:40342/metadata/identity/oauth2/token?resource=urn:ms-drs:enterpriseregistration.windows.net&api-version=2018-02-01"` | このAzure Arc 対応の Windows Server に割り当てられているマネージド ID に対して、Microsoft Entra ID によって発行された有効なアクセストークン |

    access トークンは、https://jwt.ms/ などのツールを使用してデコードできます。 access トークンの `oid` 値がデバイスのマネージド ID と一致することを確認します。
3. PowerShell を使用して、必要なエンドポイントにデバイスからアクセスできることを確認します。

    - `curl.exe https://login.microsoftonline.com/ -D -`
    - `curl.exe https://login.microsoftonline.com/<TenantID>/ -D -`
    - `curl.exe https://enterpriseregistration.windows.net/ -D -`
    - `curl.exe https://device.login.microsoftonline.com/ -D -`
    - `curl.exe https://pas.windows.net/ -D -`

    `<TenantID>` を、Azure サブスクリプションに関連付けられている Microsoft Entra テナント ID に置き換えます。 `login.microsoftonline.com/<TenantID>`、`enterpriseregistration.windows.net`、および `pas.windows.net` は、予期される動作である 404 Not Found を返す必要があります。
4. `dsregcmd /status` を実行してデバイスの状態を表示します。 目標は、デバイスの状態で `AzureAdJoined : YES` と表示されることです。

    Microsoft Entra の参加操作は、イベント ビューアー の *User Device Registration\Admin* ログ、*イベント ビューアー (local)\Applications* および *Services Logs\Microsoft\Windows\User Device Registration\Admin* でキャプチャされます。

AADLoginForWindows 拡張機能がエラー コードで失敗した場合、次の手順を行うことができます。

#### ターミナル エラー コード 1007 と終了コード -2145648574

ターミナル エラー コード 1007 と終了コード -2145648574 は `DSREG_E_MSI_TENANTID_UNAVAILABLE` に変換されます。 この拡張機能は、Microsoft Entra テナント情報に対してクエリを実行できません。

ローカル管理者としてデバイスに接続し、エンドポイントがAzureインスタンス メタデータ サービスから有効なテナント IDを返すことを確認します。 デバイスの管理者特権の PowerShell ウィンドウから次のコマンドを実行します。

`curl -H Metadata:true http://localhost:40342/metadata/identity/info?api-version=2018-02-01`

この問題は、管理者が AADLoginForWindows 拡張機能のインストールを試みたが、デバイスにシステム割り当てマネージド ID がない場合にも発生する可能性があります。 その場合は、デバイスの **[ID** ] ウィンドウに移動します。 **[システム割り当て済み]** タブで、**[状態]** トグルが **[オン]** に設定されていることを確認します。

#### 終了コード -2145648607

終了コード -2145648607 は `DSREG_AUTOJOIN_DISC_FAILED` に変換されます。 拡張機能が `https://enterpriseregistration.windows.net` エンドポイントに到達できません。

1. PowerShell を使用して、必要なエンドポイントにデバイスからアクセス可能であることを確認します。

    - `curl https://login.microsoftonline.com/ -D -`
    - `curl https://login.microsoftonline.com/<TenantID>/ -D -`
    - `curl https://enterpriseregistration.windows.net/ -D -`
    - `curl https://device.login.microsoftonline.com/ -D -`
    - `curl https://pas.windows.net/ -D -`

    `<TenantID>` を、Azure サブスクリプションの Microsoft Entra テナント ID に置き換えます。 テナント ID を見つける必要がある場合は、アカウント名にカーソルを合わせるか、**Entra ID**&gt;**Overview**&gt;**Properties**&gt;**Tenant ID** を選択します。

    `enterpriseregistration.windows.net` に接続しようとすると、予期される動作である 404 Not Found が返される場合があります。 `pas.windows.net` への接続を試みると、PIN 資格情報の入力が求められる場合や、404 Not Found が返される場合があります。 (PIN を入力する必要はありません)。URL に到達可能であることを確認するには、いずれか 1 つで十分です。
2. いずれかのコマンドが "ホスト `<URL>`を解決できませんでした" で失敗した場合は、次のコマンドを実行して、Windows が使用している DNS サーバーを特定してみてください。

    `nslookup <URL>`

    `<URL>` を、エンドポイントで使用される完全修飾ドメイン名 (`login.microsoftonline.com` など) に置き換えます。
3. パブリック DNS サーバーを指定してコマンドを正常に実行できるかどうかを確認します。

    `nslookup <URL> 208.67.222.222`
4. 必要に応じて、デバイスが属するネットワーク セキュリティ グループに割り当てられている DNS サーバーを変更します。

#### 終了コード 51

終了コード 51 は、"この拡張機能は、このオペレーティング システムではサポートされていません" に変換されます。

AADLoginForWindows 拡張機能は、Arc 対応 Windows Server 上の Windows Server 2025 または Windows 11 24H2 の Arc 対応 Windows Server にのみインストールすることを目的としています。 Windows Server のバージョンがサポートされていることを確認します。 サポートされていない場合は、拡張機能をアンインストールします。

### サインインに関する問題のトラブルシューティング

次の情報を使用して、サインインの問題を修正してください。

`dsregcmd /status` を実行すると、デバイスとシングル サインオン (SSO) の状態を表示できます。 目標は、デバイスの状態が `AzureAdJoined : YES` として表示され、SSO の状態が `AzureAdPrt : YES` と表示されることです。

Microsoft Entra アカウント経由の RDP サインインは、*Applications and Services Logs\Microsoft\Windows\AAD\Operational* イベント ログのイベント ビューアーにキャプチャされます。

#### Azure ロールが割り当てられない

デバイスへのリモート デスクトップ接続を開始する際、次のエラーメッセージが表示されることがあります。「お使いのアカウントは、このデバイスの使用を防ぐように構成されています。」 詳細については、システム管理者にお問い合わせください。"

アカウントがこのデバイスを使用できないように構成されていることを示すメッセージのスクリーンショット

Azureロールベースのaccess control ポリシーを確認します。このポリシーによって、ユーザーに仮想マシン管理者ログインロールまたは仮想マシン ユーザー ログイン ロールが付与されます。

Azureロールの割り当てに問題がある場合は、「Azure ロールベースのアクセス制御のトラブルシューティング」を参照してください。

#### 承認されていないクライアントまたはパスワードの変更が必要

デバイスへのremote desktop接続を開始すると、"*資格情報が機能しませんでした。*" というエラー メッセージが表示されることがあります。

[Image: 資格情報が機能しなかったことを示すメッセージのスクリーンショット。]

次の解決策をお試しください。

- remote desktop接続を開始するために使用するクライアント デバイスは、Microsoft Entra に参加しているか、Microsoft Entra ハイブリッドが同じ Microsoft Entra ディレクトリに参加している必要があります。 デバイス ID の詳細については、「[デバイス ID とは](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)」の記事を参照してください。

    Arc 対応 Windows Server への RDP 接続を開始するために、Microsoft Entra 登録済みクライアント デバイスもサポートされています。 (Microsoft Entra 参加済みまたは Microsoft Entra ハイブリッド参加済みではない) クライアント デバイスを RDP クライアントとして使用してサーバーへの接続を開始する場合は、 `AzureAD\UPN` 形式 ( `AzureAD\john@contoso.com` など) で資格情報を入力する必要があります。

    Microsoft Entra への参加が完了した後に AADLoginForWindows 拡張機能がアンインストールされていないことを確認します。

    また、サーバーとクライアントの "両方" で、セキュリティ ポリシー **ネットワーク セキュリティ: このコンピューターに対する PKU2U 認証要求でオンライン ID を使用することを許可する** が有効になっていることを確認します。
- ユーザーが使用しているパスワードが一時パスワードではないことを確認してください。 一時パスワードを使用してremote desktop接続にサインインすることはできません。

    Web ブラウザーでユーザー アカウントを使用してサインインします。 たとえば、プライベートブラウズ ウィンドウで [Azure portal](https://portal.azure.com) にサインインします。 パスワードの変更を求めるメッセージが表示されたら、新しいパスワードを設定します。 その後、接続を再試行してください。

#### MFA サインイン方法が必要

デバイスへのremote desktop接続を開始すると、次のエラー メッセージが表示されることがあります。"使用しようとしているサインイン方法は許可されていません。 別のサインイン方法を試すか、システム管理者に問い合わせてください。"

[Image: 使用しようとしているサインイン方法が許可されていないことを示すメッセージのスクリーンショット。]

MFA を必要とする条件付きAccess ポリシーを構成する場合は、接続を開始するデバイスがWindows Helloなどの強力な認証を使用していることを確認する必要があります。

もう 1 つの MFA 関連のエラー メッセージは、前述の「資格情報が機能しませんでした」です。

[Image: 資格情報が機能しなかったことを示すメッセージのスクリーンショット。]

従来のユーザーごとの **有効/強制 Microsoft Entra 多要素認証** 設定を構成し、エラーが表示された場合は、ユーザーごとの MFA 設定を削除することで問題を解決できます。 詳細については、「[ユーザーごとのMicrosoft Entra 多要素認証イベントを有効にしてサインイン イベントをセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-userstates)」に関する記事を参照してください。

Windows Hello for Business がオプションでない場合は、Microsoft Azure Windows 仮想マシン サインイン アプリを除外する条件付きAccess ポリシーを構成します。 Windows Hello for Business の詳細については、「[Windows Hello for Business の概要](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-identity-verification)を参照してください。

RDP を使用した生体認証のサポートは、Windows 10 バージョン 1809 で追加されました。 証明書信頼モデルまたはキー信頼モデルを使用するデプロイでは、RDP 中に Windows Hello for Business 認証を使用できます。

この機能に関するフィードバックを共有するか、[Microsoft Entra フィードバック フォーラム](https://feedback.azure.com/d365community/forum/22920db1-ad25-ec11-b6e6-000d3a4f0789)で問題を報告してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/howto-device-identity-virtual-desktop-infrastructure"} -->
## デバイス ID とデスクトップ仮想化 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-device-identity-virtual-desktop-infrastructure
- Service: entra-id / devices
- Article date: 2026-06-04
- Summary: 仮想デスクトップ インフラストラクチャ (VDI) 環境でMicrosoft Entraデバイス ID を管理して、古いデバイスとテナント クォータの消費を防ぐ方法について説明します。

管理者は、通常、組織でオペレーティング システムWindowsホストする仮想デスクトップ インフラストラクチャ (VDI) プラットフォームを展開します。 VDI は次のことに役立ちます。

- 管理の合理化。
- リソースの統合と一元化によるコストの削減。
- エンドユーザーのモビリティと、いつでも、どこからでも、任意のデバイス上の仮想デスクトップにアクセスする自由を提供します。

仮想デスクトップには 2 つのバージョンがあります。 これらの名前は、基になる仮想マシン (VM) のライフサイクルではなく、ユーザー セッションとプロファイル エクスペリエンスを指します。

| 仮想デスクトップの種類 | Description | テナント クォータへの影響 |
| --- | --- | --- |
| 永続的 | ユーザーまたはユーザーのプールごとに一意のデスクトップ イメージを使用します。 これらのデスクトップは、将来の使用のためにカスタマイズして保存できます。 | デバイスは 1 回登録され、ディレクトリに残ります。 古いデバイスは、VM がクリーンアップなしで定期的にリセットされる場合にのみ蓄積されます。 |
| 非永続的 | ユーザーが必要に応じてアクセスするデスクトップのコレクションを使用します。 これらのデスクトップは、VM のシャットダウン、再起動、または OS のリセット後に元の状態に戻ります。 | リセットするたびに新しいデバイス登録がトリガーされ、古いデバイス レコードが急速に増加し、テナント クォータが消費されます。 |

永続的バージョンは、ユーザーまたはユーザーのプールごとに一意のデスクトップ イメージを使用します。 これらの一意のデスクトップは、将来使用するためにカスタマイズして保存できます。

プールされたホスト プールと個人用ホスト プールの両方のセッション ホスト VM は、標準Azure仮想マシンであり、既定では永続的です。 Azure Virtual Desktopは、自動化またはサード パーティ製のツールを明示的に実装しない限り、これらの VM を自動的に削除、リセット、または再作成することはありません。これにより、デバイスまたは ID レベルで非永続的な動作が発生する可能性があります。

非永続的バージョンでは、ユーザーが必要に応じてaccessできるデスクトップのコレクションが使用されます。 これらの非永続的デスクトップは、仮想マシンがシャットダウン、再起動、または OS リセット プロセスを通過すると、元の状態に戻ります。

重要

古くなったデバイスでは、テナント クォータの使用量が増加します。 非永続的 VDI 環境をデプロイするときに古いデバイスからの消費の増加を回避するには、「 非永続的 vdi」を参照してください。

一部のシナリオでは、ディレクトリ内に一意のデバイス名が必要です。 これは、古いデバイスを適切に管理することによって実現できます。または、デバイスの名前付けに何らかのパターンを使用して、デバイス名の一意性を保証することもできます。

この記事では、VDI 環境でデバイス ID を管理するためのガイダンスを提供します。 デバイスID の詳細については、「[デバイス ID とは](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)」の記事を参照してください。

### サポートされるシナリオ

VDI 環境のMicrosoft Entra IDでデバイス ID を構成する前に、サポートされているシナリオを理解しておいてください。 次の表に、サポートされているプロビジョニング シナリオを示します。 このコンテキストでのプロビジョニングとは、管理者がエンドユーザーの操作を必要とせずに、デバイス ID を大規模に構成できることを意味します。

**Windows current** デバイスは、Windows 10以降、Windows Server 2016 v1803 以降、および Windows Server 2019 以降を表します。

| デバイス ID の種類 | ID インフラストラクチャ | Windows デバイス | VDI プラットフォームのバージョン | サポート |
| --- | --- | --- | --- | --- |
| Microsoft Entra ハイブリッド参加済み | フェデレーテッド^1^ | 最新の Windows | 永続的 | あり |
|  |  | 最新の Windows | 非永続的 | はい^2^ |
|  | マネージド^3^ | 最新の Windows | 永続的 | あり |
|  |  | 最新の Windows | 非永続的 | 制限^付き 4^ |
| Microsoft Entra 参加済み | フェデレーション | 最新の Windows | 永続的 | 制限あり |
|  |  |  | 非永続的 | いいえ |
|  | 管理対象 | 最新の Windows | 永続的 | 制限付き^5^ |
|  |  |  | 非永続的 | いいえ |
| Microsoft Entra 登録済み | フェデレーション/マネージド | 最新の Windows | 永続的/非永続的 | 該当なし |

重要

VDI ファーム (永続的または非永続的) をデプロイする場合、お客様は [Entra デバイス操作の調整制限](https://learn.microsoft.com/ja-jp/graph/throttling-limits#identity-and-access-device-operation-service-limits)を考慮する必要があります。 Microsoft では、デバイス登録要求を、2 分と 30 秒間隔ごとに 500 要求の割合でステージングすることをお勧めします。 このような要求をステージングしないと、調整エラーが発生し、デバイスの登録エラーが発生し、デバイスの登録が成功するまでの遅延が長くなる可能性があります。

^1^**フェデレーション** ID インフラストラクチャ環境は、AD FS やその他の Microsoft IdP 以外の ID プロバイダー (IdP) を持つ環境を表します。 フェデレーション ID インフラストラクチャ環境では、コンピューターは、Microsoft Windows Server Active Directory サービス接続ポイント (SCP) の設定に基づいて、federated デバイス登録フローに従います。

^2^**Windows の現在の非永続化サポート** には、ガイダンス セクションに記載されているその他の考慮事項が必要です。 このシナリオでは、Windows 10 バージョン 1803 以降、Windows Server 2019、またはバージョン 1803 以降の Windows Server（半期チャンネル）が必要です。

^3^**マネージド** ID インフラストラクチャ環境は、Microsoft Entra ID が ID プロバイダーとしてデプロイされていて、[パスワード ハッシュ同期 (PHS)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs) または[パススルー認証 (PTA)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta) のいずれかが、[シームレス シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso)と共に使用される環境を表します。

^4^ マネージド ID インフラストラクチャ環境での **Windows の非永続化サポート**は、次のベンダーでのみ利用できます。

- [Omnissa Horizon 8 オンプレミスのカスタマー マネージド](https://docs.omnissa.com/bundle/Horizon8InstallUpgrade/page/SupportforAzureActiveDirectory.html)[環境と Horizon Cloud Service マネージド環境](https://docs.omnissa.com/bundle/UsingManagingHorizonCloud/page/CreateaPool.html)。 サポート関連のクエリについては、 [Omnissa サポート](https://kb.omnissa.com/s/article/6000005) に直接お問い合わせください。
- Citrix [オンプレミスの顧客管理](https://docs.citrix.com/en-us/citrix-virtual-apps-desktops/install-configure/machine-identities/hybrid-azure-active-directory-joined)と [クラウドサービス管理](https://docs.citrix.com/en-us/citrix-daas/install-configure/machine-identities/hybrid-azure-active-directory-joined)。 サポート関連のクエリについては、[Citrix のサポート](https://www.citrix.com/support/)に直接お問い合わせください。

^5^**Microsoft Entra join support**、[Azure Virtual Desktop](https://learn.microsoft.com/ja-jp/azure/virtual-desktop/)、[Windows 365](https://www.microsoft.com/windows-365) および [Amazon WorkSpaces](https://docs.aws.amazon.com/workspaces/latest/adminguide/launch-workspaces-tutorials.html#launch-entra-id) で使用できます。 Amazon WorkSpaces と Microsoft Entra の統合におけるサポート関連クエリについては、[Amazon のサポート](https://aws.amazon.com/contact-us/)に直接お問い合わせください。

### Microsoft のガイダンス

管理者は、ID インフラストラクチャに基づいて次の記事を参照して、Microsoft Entra ハイブリッド参加を構成する方法を理解してください。

- [Microsoft Entra ハイブリッド参加をフェデレーション環境用向けに構成する](https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join)
- 「[Microsoft Entra ハイブリッド参加をマネージド環境用向けに構成する](https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join)」を参照してください。

#### 非永続的 VDI

非永続的 VDI をデプロイする場合は、Microsoft次のガイダンスをお勧めします。 これらの手順に従わないと、ディレクトリに非永続的 VDI プラットフォームから古いMicrosoft Entraハイブリッド参加済みデバイスが蓄積されます。

- システム準備ツール (sysprep.exe) に依存していて、インストールにWindows 10 1809 より前のイメージを使用している場合は、Microsoft Entra ハイブリッド参加済みとしてMicrosoft Entra IDに既に登録されているデバイスからイメージが取得されていないことを確認します。
- 仮想マシン (VM) スナップショットに依存して VM をさらに作成する場合は、Microsoft Entra ハイブリッド参加としてMicrosoft Entra IDに既に登録されている VM からのスナップショットではないことを確認してください。
- Active Directory フェデレーション サービス (AD FS) (AD FS) では、非永続的 VDI と Microsoft Entra ハイブリッド参加のインスタント参加がサポートされます。
- デスクトップが非永続的な VDI ベースであることを示す、コンピューターの表示名のプレフィックス (例: NPVDI-) を作成して使用します。
- フェデレーション環境の Windows デバイス (AD FS など) の場合:
    - ユーザーがサインインする前に、VM ブート シーケンス/順序の一部として、**dsregcmd/join** を実行します。
    - VM のシャットダウン/再起動プロセスの一部として dsregcmd /leave を実行**しない**でください。
- [古いデバイスの管理](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-stale-devices)のプロセスを定義して実装します。
    - 非永続的な Microsoft Entra ハイブリッド参加済みデバイスを識別する方法 (コンピューター表示名のプレフィックスなど) を用意したら、ディレクトリが多くの古くなったデバイスで占められないように、これらのデバイスのクリーンアップをより積極的に行う必要があります。
    - 非永続的 VDI のデプロイでは、**ApproximateLastLogonTimestamp** が 15 日より古いデバイスを削除する必要があります。

注

非永続的 VDI を使用する場合、職場または学校アカウントの追加を回避するには、次のレジストリ キーが設定されていることを確認します。`HKLM\SOFTWARE\Policies\Microsoft\Windows\WorkplaceJoin: "BlockAADWorkplaceJoin"=dword:00000001`

バージョン 1803 以降Windows 10実行していることを確認します。

パス `%localappdata%` にあるデータのローミングはサポートされていません。 `%localappdata%` にあるコンテンツを移動する場合は、次のフォルダーとレジストリキーの内容がどのような状況でもデバイスを離れることが**ない**ようにしてください。 たとえば、プロファイル移行ツールでは、次のフォルダーとキーをスキップする必要があります。

- `%localappdata%\Packages\Microsoft.AAD.BrokerPlugin_cw5n1h2txyewy`
- `%localappdata%\Packages\Microsoft.Windows.CloudExperienceHost_cw5n1h2txyewy`
- `%localappdata%\Packages\<any app package>\AC\TokenBroker`
- `%localappdata%\Microsoft\TokenBroker`
- `%localappdata%\Microsoft\OneAuth`
- `%localappdata%\Microsoft\IdentityCache`
- `HKEY_CURRENT_USER\SOFTWARE\Microsoft\IdentityCRL`
- `HKEY_CURRENT_USER\SOFTWARE\Microsoft\Windows\CurrentVersion\AAD`
- `HKEY_CURRENT_USER\SOFTWARE\Microsoft\Windows NT\CurrentVersion\WorkplaceJoin`
- `HKEY_CURRENT_USER\SOFTWARE\Microsoft\Windows NT\CurrentVersion\TokenBroker`

職場アカウントのデバイス証明書のローミングはサポートされていません。 "MS-Organization-Access" によって発行された証明書は、現在のユーザーとローカル コンピューターの個人用 (MY) 証明書ストアに格納されます。

#### 永続的 VDI

永続的な VDI をデプロイする場合は、Microsoft次のガイダンスをお勧めします。 これらの手順に従わないと、デプロイと認証の問題が発生します。

- システム準備ツール (sysprep.exe) に依存していて、インストールにWindows 10 1809 より前のイメージを使用している場合は、Microsoft Entra ハイブリッド参加済みとしてMicrosoft Entra IDに既に登録されているデバイスからイメージが取得されていないことを確認します。
- 仮想マシン (VM) スナップショットに依存して VM をさらに作成する場合は、Microsoft Entra ハイブリッド参加としてMicrosoft Entra IDに既に登録されている VM からのスナップショットではないことを確認してください。

[古いデバイスを管理](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-stale-devices)するプロセスを実装することをお勧めします。 このプロセスにより、VM を定期的にリセットしても、ディレクトリに古いデバイスが蓄積されなくなります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/howto-manage-local-admin-passwords"} -->
## Microsoft Entra ID で Windows Local Administrator Password Solution (LAPS) を使う - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-manage-local-admin-passwords
- Service: entra-id / devices
- Article date: 2024-01-16
- Summary: Microsoft Entra LAPS を使用して、デバイスのローカル管理者パスワードを管理します。

すべての Windows デバイスには、Pass-the-Hash (PtH) 攻撃と横方向トラバーサル攻撃を軽減するためにセキュリティで保護する必要がある、組み込みのローカル管理者アカウントが付属しています。 多くのお客様は、ドメインに参加している Windows マシンのローカル管理者パスワード管理のために、スタンドアロンのオンプレミス [Local Administrator Password Solution (LAPS)](https://www.microsoft.com/download/details.aspx?id=46899) 製品を使用しています。 Windows LAPS の Microsoft Entra サポートでは、Microsoft Entra 参加済みデバイスと Microsoft Entra ハイブリッド参加済みデバイスの両方に一貫したエクスペリエンスが提供されます。

LAPS の Microsoft Entra サポートには、以下の機能が含まれています。

- **Microsoft Entra ID を使用した Windows LAPS の有効化** - テナント全体のポリシーとクライアント側ポリシーを有効にして、ローカル管理者パスワードを Microsoft Entra ID にバックアップします。
- **ローカル管理者パスワードの管理** - クライアント側ポリシーを構成して、アカウント名、パスワードの有効期間、長さ、複雑さ、手動によるパスワード リセットなどを設定します。
- **ローカル管理者パスワードの回復** - ローカル管理者のパスワード回復のための API/ポータル エクスペリエンスを使用します。
- **すべての Windows LAPS 有効デバイスの列挙** - API/Portal エクスペリエンスを使用して、Windows LAPS が有効になっている Microsoft Entra ID 内のすべての Windows デバイスを列挙します。
- **ローカル管理者パスワード回復の承認** - カスタム ロールと管理単位でロールベースのアクセス制御 (RBAC) ポリシーを使用します。
- **ローカル管理者パスワードの更新と回復の監査** - 監査ログ API/ポータル エクスペリエンスを使用して、パスワードの更新と回復のイベントを監視します。
- **ローカル管理者パスワードの回復の条件付きアクセス ポリシー** - パスワード回復が認可されているディレクトリ ロールに対して条件付きアクセス ポリシーを構成します。

注

Microsoft Entra ID を使用した Windows LAPS は、[Microsoft Entra 登録済み](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-device-registration) Windows デバイスではサポートされていません。

Local Administrator Password Solution は、Windows 以外のプラットフォームではサポートされていません。

Windows LAPS の詳細については、Windows ドキュメントの次の記事から始めます。

- [Windows LAPS とは](https://learn.microsoft.com/ja-jp/windows-server/identity/laps/laps-scenarios-azure-active-directory) – Windows LAPS の概要と Windows LAPS のドキュメント セット。
- [Windows LAPS CSP](https://learn.microsoft.com/ja-jp/windows/client-management/mdm/laps-csp) – LAPS の設定とオプションの詳細を表示します。 LAPS の Intune ポリシーではこれらの設定を使用して、デバイスの LAPS CSP を構成します。
- [Windows LAPS の Microsoft Intune サポート](https://learn.microsoft.com/ja-jp/mem/intune/protect/windows-laps-overview)
- [Windows LAPS のアーキテクチャ](https://learn.microsoft.com/ja-jp/windows-server/identity/laps/laps-concepts-overview#windows-laps-architecture)

### 必要条件

#### サポートされる Azure リージョンと Windows ディストリビューション

この機能は、次の Azure クラウドで使用できるようになりました。

- Azure グローバル
- Azure Government（アジュール・ガバメント）
- 21Vianet が運用する Microsoft Azure

#### オペレーティング システムの更新プログラム

この機能は、指定した更新プログラム以降がインストールされた次の Windows OS プラットフォームで使用できるようになりました。

- [Windows 11 22H2 - 2023 年 4 月 11 日の更新プログラム](https://support.microsoft.com/help/5025239)
- [Windows 11 21H2 - 2023 年 4 月 11 日の更新プログラム](https://support.microsoft.com/help/5025224)
- [Windows 10 20H2、21H2、22H2 - 2023 年 4 月 11 日の更新プログラム](https://support.microsoft.com/help/5025221)
- [Windows Server 2022 - 2023 年 4 月 11 日の更新プログラム](https://support.microsoft.com/help/5025230)
- [Windows Server 2019 - 11 2023 11、2023 11 2023 更新プログラム](https://support.microsoft.com/help/5025229)

#### 結合の種類

LAPS がサポートされているのは、Microsoft Entra 参加済みデバイスまたは Microsoft Entra ハイブリッド参加済みデバイス上のみです。 Microsoft Entra 登録済みデバイスはサポートされていません。

#### ライセンス要件

LAPS は、Microsoft Entra ID Free 以上のライセンスを持つすべてのお客様が利用できます。 管理単位、カスタム ロール、条件付きアクセス、Intune などのその他の関連機能には、他のライセンス要件があります。

#### 必要なロールまたはアクセス許可

[device.LocalCredentials.Read.All](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-device-administrator) が付与されている[クラウド デバイス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#intune-administrator)と *Intune 管理者*などの組み込み Microsoft Entra ロール以外では、[Microsoft Entra カスタム ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create)または管理単位を使用して、ローカル管理者パスワードの回復を承認できます。 次に例を示します。

- ローカル管理者パスワードの回復を承認するには、カスタム ロールに *microsoft.directory/deviceLocalCredentials/password/read* アクセス許可を割り当てる必要があります。 [カスタム ロールを作成](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create)し、[Microsoft Entra 管理センター](https://entra.microsoft.com)、Microsoft Graph API、または PowerShell を使ってアクセス許可を付与できます。 カスタム ロールを作成すると、ユーザーに割り当てることができます。
- また、Microsoft Entra ID の[管理単位](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/administrative-units)を作成し、デバイスを追加し、管理単位をスコープとするクラウド デバイス管理者ロールを割り当てて、ローカル管理者パスワードの回復を承認することもできます。

### Microsoft Entra ID を使用した Windows LAPS の有効化

Microsoft Entra ID を使用した Windows LAPS を有効にするには、Microsoft Entra ID と管理したいデバイスでアクションを実行する必要があります。 組織では、[Microsoft Intune を使用して Windows LAPS を管理する](https://learn.microsoft.com/ja-jp/mem/intune/protect/windows-laps-policy)ことをお勧めします。 デバイスが Microsoft Entra に参加済であるが、Microsoft Intune を使用していない、またはサポートしていない場合でも、Microsoft Entra ID 用の Windows LAPS を手動で展開できます。 詳細については、「[Windows LAPS のポリシー設定を構成する](https://learn.microsoft.com/ja-jp/windows-server/identity/laps/laps-management-policy-settings)」の記事を参照してください。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com) に少なくとも [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-device-administrator) としてサインインします。
2. **Entra ID**&gt;**デバイス**&gt;**概要**&gt;**デバイス設定**に移動します
3. **ローカル管理者パスワード ソリューション (LAPS) を有効にする**設定で **[はい]** を選択し、**[保存]** を選択します。 Microsoft Graph API の [Update deviceRegistrationPolicy](https://learn.microsoft.com/ja-jp/graph/api/deviceregistrationpolicy-update?view=graph-rest-beta&preserve-view=true) を使用して、このタスクを完了することもできます。
4. クライアント側ポリシーを構成し、**BackUpDirectory** を Microsoft Entra ID に設定します。

    - Microsoft Intune を使用してクライアント側ポリシーを管理する場合は、[Microsoft Intune を使用して Windows LAPS を管理する](https://learn.microsoft.com/ja-jp/mem/intune/protect/windows-laps-policy)ことに関する記事を参照してください。
    - グループ ポリシー オブジェクト (GPO) を使用してクライアント側ポリシーを管理する場合は、「[Windows LAPS グループ ポリシー](https://learn.microsoft.com/ja-jp/windows-server/identity/laps/laps-management-policy-settings#windows-laps-group-policy)」を参照してください

### ローカル管理者のパスワードとパスワード メタデータの復旧

Microsoft Entra ID に参加済みの Windows デバイスのローカル管理者パスワードを表示するには、*microsoft.directory/deviceLocalCredentials/password/read* アクションが付与されている必要があります。

Microsoft Entra ID に参加済みの Windows デバイスのローカル管理者パスワード メタデータを表示するには、*microsoft.directory/deviceLocalCredentials/standard/read* アクションが付与されている必要があります。

次の組み込みロールには、既定でこれらのアクションが付与されています。

| 組み込みのロール | microsoft.directory/deviceLocalCredentials/standard/read および microsoft.directory/deviceLocalCredentials/password/read | microsoft.directory/deviceLocalCredentials/standard/read (英語) |
| --- | --- | --- |
| [クラウド デバイス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-device-administrator) | はい | はい |
| [Intune サービス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#intune-administrator) | はい | はい |
| [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator) | いいえ | はい |
| [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) | いいえ | はい |
| [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) | いいえ | はい |

一覧にないロールには、どちらのアクションも付与されていません。

Microsoft Graph API の [Get deviceLocalCredentialInfo](https://learn.microsoft.com/ja-jp/graph/api/devicelocalcredentialinfo-get?view=graph-rest-beta&preserve-view=true) を使用して、ローカル管理パスワードを回復することもできます。 Microsoft Graph API を使用する場合、返されるパスワードは Base64 でエンコードされた値であり、使用する前にデコードする必要があります。

### すべての Windows LAPS 対応デバイスを一覧表示する

Windows LAPS 対応のすべてのデバイスを一覧表示するには、 **Entra ID**&gt;**Devices**&gt;**Overview**&gt;**ローカル管理者パスワード回復** に移動するか、Microsoft Graph API を使用します。

### ローカル管理者パスワードの更新と回復の監査

監査イベントを表示するには、**Entra ID**&gt;**Devices**&gt;**Overview**&gt;**Audit ログ**を参照し、**アクティビティ** フィルターを使用してデバイス**のローカル管理者パスワードの更新またはデバイス のローカル管理者パスワード** **の回復**を検索して監査イベントを表示できます。

### ローカル管理者パスワード回復のための条件付きアクセス ポリシー

条件付きアクセス ポリシーのスコープを組み込みのロールに設定して、ローカル管理者のパスワードを回復するためのアクセスを保護できます。 多要素認証を必要とするポリシーの例については、「[一般的な条件付きアクセス ポリシー: 管理者に対して MFA を要求する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-old-require-mfa-admin)」の記事を参照してください。

注

管理単位スコープのロールやカスタム ロールなど、その他のロールの種類はサポートされていません。

### よく寄せられる質問

#### Microsoft Entra 管理構成を使用した Windows LAPS では、グループ ポリシー オブジェクト (GPO) の使用がサポートされていますか?

はい。対象は [Microsoft Entra ハイブリッド参加済み](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join)デバイスのみです。 「[Windows LAPS グループ ポリシー](https://learn.microsoft.com/ja-jp/windows-server/identity/laps/laps-management-policy-settings#windows-laps-group-policy)」を参照してください。

#### Microsoft Entra 管理構成を使用した Windows LAPS では、MDM の使用がサポートされていますか?

はい。対象は [Microsoft Entra 参加](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)/[Microsoft Entra ハイブリッド参加](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join) ([共同管理](https://learn.microsoft.com/ja-jp/mem/configmgr/comanage/overview)) デバイスです。 お客様は [Microsoft Intune](https://learn.microsoft.com/ja-jp/mem/intune/protect/windows-laps-overview) または他の任意のサード パーティ製モバイル デバイス管理 (MDM) を選んで使うことができます。

#### Microsoft Entra ID でデバイスが削除されるとどうなりますか?

Microsoft Entra ID でデバイスが削除されると、そのデバイスに関連付けられていた LAPS 資格情報が失われ、Microsoft Entra ID に保存されているパスワードが失われます。 LAPS パスワードを取得して外部に保存するカスタム ワークフローがない限り、Microsoft Entra ID には、削除されたデバイスの LAPS マネージド パスワードを回復する方法はありません。

#### LAPS パスワードを回復するために必要なロールは何ですか?

次の組み込みの Microsoft Entra ロールには、LAPS パスワードを回復するためのアクセス許可があります: クラウド デバイス管理者、Intune 管理者。

#### LAPS のメタデータを読み取るために必要なロールは何ですか?

LAPS に関するメタデータ (デバイス名、最後のパスワード ローテーション、次回のパスワード ローテーションなど) を表示するためにサポートされている組み込みロールは、クラウド デバイス管理者、Intune 管理者、ヘルプデスク管理者、セキュリティ閲覧者、セキュリティ管理者です。

#### カスタム ロールはサポートされていますか?

はい。 Microsoft Entra ID P1 または P2 を持っている場合は、以下の RBAC アクセス許可を持つカスタム ロールを作成できます。

- LAPS メタデータを読み取る場合: *microsoft.directory/deviceLocalCredentials/standard/read*
- LAPS パスワードを読み取る場合: *microsoft.directory/deviceLocalCredentials/password/read*

#### ポリシーで指定されたローカル管理者アカウントが変更されるとどうなりますか?

Windows LAPS ではデバイス上で一度に 1 つのローカル管理者アカウントのみを管理できるため、元のアカウントは LAPS ポリシーによって管理されなくなります。 ポリシーによってデバイスでそのアカウントがバックアップされた場合、新しいアカウントがバックアップされ、以前のアカウントの詳細は、Intune 管理センター内から、またはアカウント情報を格納するために指定されたディレクトリから得られなくなります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/howto-vm-sign-in-azure-ad-linux"} -->
## Microsoft Entra ID と OpenSSH を使用して Azure の Linux 仮想マシンにサインインする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-vm-sign-in-azure-ad-linux
- Service: entra-id / devices
- Article date: 2025-06-27
- Summary: Microsoft Entra ID と OpenSSH による証明書ベースの認証を使用して、Linux が実行されている Azure VM にサインインする方法について説明します。

Azure Linux 仮想マシン (VM) または Azure Arc 対応 Linux サーバーのセキュリティを強化するために、Microsoft Entra 認証と統合できます。 コア認証プラットフォームと証明機関として Microsoft Entra ID を使用し、Microsoft Entra ID および OpenSSH 証明書ベースの認証によって Linux VM に SSH 接続できるようになりました。 この機能により、組織は、Azure ロールベースのアクセス制御 (RBAC) ポリシーと条件付きアクセス ポリシーを使用して、VM へのアクセスを管理できます。

この記事では、OpenSSH 証明書ベースの認証を使用して Linux VM を作成および構成し、Microsoft Entra ID でログインする方法について説明します。

Microsoft Entra ID と OpenSSH による証明書ベースの認証を使用して Azure の Linux VM にサインインすると、セキュリティ上、次のような数多くの利点があります。 具体的な内容を次に示します。

- Microsoft Entra 資格情報を使用して、Azure Linux VM にサインインできます。
- SSH キーをユーザーに配布したり、デプロイする Azure Linux VM で SSH 公開キーをプロビジョニングしたりする必要なく、SSH キー ベースの認証を取得します。 このエクスペリエンスは、未認可のアクセスの原因となる可能性がある古い SSH 公開キーの無秩序な広がりについて心配しなければならないことよりもはるかに単純です。
- ローカル管理者アカウントへの依存、資格情報の盗難、脆弱な資格情報を削減します。
- Microsoft Entra ID のパスワードの複雑さとパスワードの有効期間ポリシーを構成することによって、Linux VM のセキュリティが向上します。
- RBAC で、通常のユーザーとして、または管理者特権を使用して VM にサインインできるユーザーを指定できます。 ユーザーがチームに参加する場合は、適切なアクセス権が付与されるよう VM の Azure RBAC ポリシーを更新できます。 従業員が退職し、そのユーザー アカウントが無効化または Microsoft Entra ID から削除されると、リソースにアクセスできなくなります。
- 条件付きアクセスを使用して、Linux VM に SSH 接続する前に、多要素認証を要求したり、クライアント デバイスをマネージド (準拠デバイスや Microsoft Entra ハイブリッド参加済みデバイスなど) にすることを要求したりするポリシーを構成します。
- Azure のデプロイおよび監査ポリシーを使用して Linux VM への Microsoft Entra ログインを要求し、承認されていないローカル アカウントにフラグを設定します。

Microsoft Entra ID による Linux VM へのサインインは、Active Directory フェデレーション サービス (AD FS) を使用するお客様にも機能します。

### サポートされている Linux ディストリビューションと Azure リージョン

サポート対象リージョンでは現在、次の Linux ディストリビューションのデプロイがサポートされています。

| ディストリビューション | バージョン |
| --- | --- |
| アルマリナックス | AlmaLinux 8、AlmaLinux 9 |
| Azure Linux (旧称 Common Base Linux Mariner) | CBL-Mariner 2.0、Azure Linux 3.0 |
| Debian | Debian 9、Debian 10、Debian 11、Debian 12 |
| openSUSE | openSUSE Leap 42.3、openSUSE Leap 15.1 から 15.5、openSUSE Leap 15.6 以降 |
| オラクル | Oracle Linux 8、Oracle Linux 9 |
| Red Hat Enterprise Linux (RHEL) | RHEL 7.4 から RHEL 7.9、RHEL 8.3 以降、RHEL 9.0 以降、RHEL 10.1 |
| ロッキー | Rocky 8、Rocky 9 |
| SUSE Linux Enterprise Server(SLES) | SLES 12、SLES 15.1 から 15.5、SLES 15.6 以降 |
| ウブントゥ | Ubuntu 16.04、Ubuntu 24.04 |

メモ

SUSE は、以前のバージョンと互換性のないバージョン 15.6 で破壊的変更を行いました。 Microsoft Entra ログイン VM 拡張機能は常に最新のパッケージをインストールするため、これは古い SUSE バージョンでは機能しません。 古い SUSE バージョンの packages.microsoft.com から aadsshlogin パッケージをインストールできます。 リポジトリを追加した後、次のコマンドを使用して手動でインストールできます: `sudo zypper install aadsshlogin=1.0.027980001`。

この機能では、次の Azure リージョンが現在サポートされています。

- Azure グローバル
- Azure Government
- 21Vianet によって運営される Microsoft Azure

Azure Kubernetes Service (AKS) クラスターでの Azure CLI 用 SSH 拡張機能の使用はサポートされていません。 詳細については、「 [AKS のサポート ポリシー」を](https://learn.microsoft.com/ja-jp/azure/aks/support-policies)参照してください。

Azure CLI をローカルにインストールして使用する場合は、Azure CLI バージョン 2.22.1 以降である必要があります。 バージョンを確認するには、`az --version` を実行します。 インストールまたはアップグレードする必要がある場合は、「 [Azure CLI のインストール」を](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli)参照してください。

メモ

この機能は、 [Azure Arc 対応サーバー](https://learn.microsoft.com/ja-jp/azure/azure-arc/servers/ssh-arc-overview)でも使用できます。

### OpenSSH 証明書ベースの認証を使用した Microsoft Entra ID によるログインの要件を満たす

Azure の Linux VM に対して SSH 証明書ベースの認証を使用した Microsoft Entra ログインを有効にするには、次のネットワーク、仮想マシン、クライアント (SSH クライアント) の要件を満たす必要があります。

#### ネットワーク

VM ネットワーク構成では、TCP ポート 443 経由の次のエンドポイントへの発信アクセスが許可されている必要があります。

Azure グローバル:

- `https://packages.microsoft.com`: パッケージのインストールとアップグレード用。
- `http://169.254.169.254`: Azure Instance Metadata Service エンドポイント。
- `https://login.microsoftonline.com`: PAM ベース (プラグ可能な認証モジュール) の認証フロー用。
- `https://pas.windows.net`: Azure RBAC フロー用。

Azure Government:

- `https://packages.microsoft.com`: パッケージのインストールとアップグレード用。
- `http://169.254.169.254`: Azure Instance Metadata Service エンドポイント。
- `https://login.microsoftonline.us`: PAM ベースの認証フロー用。
- `https://pasff.usgovcloudapi.net`: Azure RBAC フロー用。

21Vianet によって運営される Microsoft Azure:

- `https://packages.microsoft.com`: パッケージのインストールとアップグレード用。
- `http://169.254.169.254`: Azure Instance Metadata Service エンドポイント。
- `https://login.chinacloudapi.cn`: PAM ベースの認証フロー用。
- `https://pas.chinacloudapi.cn`: Azure RBAC フロー用。

#### 仮想マシン

次の機能で VM が構成されていることを確認します。

- システム割り当てマネージド ID。 Azure portal を使用して VM を作成し、Microsoft Entra ログイン オプションを選択すると、このオプションが自動的に選択されます。 Azure CLI を使用して、新規または既存の VM でシステム割り当てマネージド ID を有効にすることもできます。
- `aadsshlogin` および `aadsshlogin-selinux` (必要に応じて)。 これらのパッケージは、AADSSHLoginForLinux VM 拡張機能によってインストールされます。 拡張機能は、Azure portal または Azure CLI を使用して VM を作成し、Microsoft Entra ログイン (**[管理** ] タブ) を有効にする場合にインストールされます。

#### クライアント

クライアントが次の要件を満たしていることを確認してください。

- SSH クライアントでは、認証のために OpenSSH ベースの証明書をサポートしている必要があります。 この要件を満たすには、Azure CLI (2.21.1 以降) と OpenSSH (Windows 10 バージョン 1803 またはそれ以降に含まれています)、または Azure Cloud Shell を使用することができます。
- Azure CLI 用 SSH 拡張機能。 この拡張機能は、`az extension add --name ssh` を使用してインストールできます。 Azure Cloud Shell を使用している場合、この拡張機能はプレインストールされているため、インストールする必要はありません。

    OpenSSH 証明書をサポートする Azure CLI または Azure Cloud Shell 以外の SSH クライアントを使用している場合でも、SSH 拡張機能を備えた Azure CLI を使用して、エフェメラル SSH 証明書と必要に応じて構成ファイルを取得する必要があります。 その構成ファイルを SSH クライアントで使用することができます。
- クライアントから VM のパブリックまたはプライベート IP アドレスへの TCP 接続。 (接続可能なマシンへの SSH 転送または ProxyCommand も機能します。)

重要

PuTTY に基づく SSH クライアントで OpenSSH 証明書がサポートされるようになり、Microsoft Entra OpenSSH 証明書ベースの認証でのログインに使用できるようになりました。

### Azure 内の Linux VM に対して Microsoft Entra ログインを有効にする

Azure 内の Linux VM に Microsoft Entra ログインを使用するには、最初にお使いの Linux VM で Microsoft Entra ログイン オプションを有効にする必要があります。 次に、VM へのサインインが許可されているユーザーに対して Azure ロールの割り当てを構成します。 最後に、Azure CLI や Azure Cloud Shell など、OpenSSH をサポートする SSH クライアントを使用して Linux VM に SSH 接続します。

Linux VM で Microsoft Entra ログインを有効にするには、次の 2 つの方法があります。

- Linux VM の作成時の Azure portal エクスペリエンス
- Linux VM の作成時または既存の VM の使用時の Azure Cloud Shell エクスペリエンス

#### Azure portal

Azure portal を使用して、 サポートされている任意の Linux ディストリビューション に対して Microsoft Entra ログインを有効にすることができます。

たとえば、Microsoft Entra ログインによって、Azure に Ubuntu Server 18.04 長期サポート (LTS) VM を作成するには、次の手順を行います。

1. VM を作成するためのアクセス権を持つアカウントを使用して [Azure portal](https://portal.azure.com) にサインインし、[ **+ リソースの作成**] を選択します。
2. **[人気順]** ビューの **[Ubuntu Server 18.04 LTS]** の下の **[作成]** を選びます。
3. [管理] タブで、次の **手順を実行**します。
    1. [ **Microsoft Entra ID でログイン** ] チェック ボックスをオンにします。
    2. **[システム割り当てマネージド ID**] チェック ボックスがオンになっていることを確認します。
4. 仮想マシンの作成エクスペリエンスの残りの部分に移動します。 ユーザー名とパスワードまたは SSH 公開キーで管理者アカウントを作成する必要があります。

#### Azure Cloud Shell

Azure Cloud Shell は無料のインタラクティブ シェルです。この記事の手順は、Azure Cloud Shell を使って実行することができます。 Cloud Shell には一般的な Azure ツールが事前にインストールされており、アカウントで使用できるように構成されています。 **[コピー**] ボタンを選択してコードをコピーし、Cloud Shell に貼り付け、Enter キーを押して実行するだけです。

Cloud Shell は、次のようにいくつかの方法で開くことができます。

- コード ブロックの右上隅にある [ **試** してみる] を選択します。
- ブラウザーで Cloud Shell を開きます。
- Azure portal の右上隅にあるメニューの [Cloud Shell] ボタンを選択します。

Azure CLI をローカルにインストールして使用する場合、この記事では、バージョン 2.22.1 以降を使用する必要があります。 バージョンを確認するには、`az --version` を実行します。 インストールまたはアップグレードする必要がある場合は、「 [Azure CLI のインストール」を](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli)参照してください。

1. az group create を実行してリソース [グループを作成](https://learn.microsoft.com/ja-jp/cli/azure/group#az-group-create)します。
2. [az vm create を実行して VM を作成](https://learn.microsoft.com/ja-jp/cli/azure/vm#az-vm-create)します。 サポート対象リージョンでサポートされているディストリビューションを使用します。
3. [az vm extension set](https://learn.microsoft.com/ja-jp/cli/azure/vm/extension#az-vm-extension-set) を使用して、Microsoft Entra ログイン VM 拡張機能をインストールします。

次の例では、VM をデプロイし、拡張機能をインストールして、Linux VM に対する Microsoft Entra ログインを有効にします。 VM 拡張機能は、Azure 仮想マシン でのデプロイ後の構成と自動タスクを提供する小さなアプリケーションです。 テスト要件をサポートするように、必要に応じてこの例をカスタマイズしてください。

```azurecli
az group create --name AzureADLinuxVM --location southcentralus
az vm create \
    --resource-group AzureADLinuxVM \
    --name myVM \	
    --image Ubuntu2204 \
    --assign-identity \
    --admin-username azureuser \
    --generate-ssh-keys
az vm extension set \
    --publisher Microsoft.Azure.ActiveDirectory \
    --name AADSSHLoginForLinux \
    --resource-group AzureADLinuxVM \
    --vm-name myVM
```

VM とサポートするリソースを作成するには数分かかります。

AADSSHLoginForLinux 拡張機能は、実行中の VM エージェントによって、既存の (サポートされているディストリビューション) Linux VM にインストールして、Microsoft Entra 認証を有効にすることができます。 以前に作成した VM にこの拡張機能をデプロイする場合、VM には少なくとも 1 GB のメモリが割り当てられている必要があります。割り当てられていない場合、インストールは失敗します。

拡張機能が VM に正常にインストールされると、`provisioningState` という `Succeeded` 値が表示されます。 拡張機能をインストールするには、VM に実行中の [VM エージェント](https://learn.microsoft.com/ja-jp/azure/virtual-machines/extensions/agent-linux) が必要です。

### Azure Arc 対応 Linux サーバーに対して Microsoft Entra ログインを有効にする

[Azure Arc 対応サーバーへの SSH アクセス](https://learn.microsoft.com/ja-jp/azure/azure-arc/servers/ssh-arc-overview)に関する関連する詳細を確認できます

### 仮想マシン ロールの割り当てを構成する

VM を作成したので、VM にサインインできるユーザーを決定するため、次のいずれかの Azure ロールを割り当てる必要があります。 これらのロールを割り当てるには、[仮想マシン データ アクセス管理者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-data-access-administrator-preview)ロール、またはロール ベースのアクセス`Microsoft.Authorization/roleAssignments/write`管理者ロールなどのアクションを含むロールが必要です。 ただし、仮想マシン データ アクセス管理者とは異なるロールを使用する場合は、 [ロールの割り当てを作成するアクセス許可を減らす条件を追加](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/delegate-role-assignments-overview)することをお勧めします。

- **仮想マシン管理者ログイン:** このロールが割り当てられているユーザーは、管理者特権で Azure 仮想マシンにサインインできます。
- **仮想マシン ユーザー ログイン:** このロールが割り当てられているユーザーは、通常のユーザー特権で Azure 仮想マシンにサインインできます。

ユーザーが SSH を介して VM にサインインできるようにするには、VM とそれに関連付けられた仮想ネットワーク、ネットワーク インターフェイス、パブリック IP アドレス、またはロード バランサー リソースを含むリソース グループに仮想マシン管理者ログインまたは仮想マシン ユーザー ログインのいずれかのロールを割り当てる必要があります。

VM の所有者または共同作成者のロールが割り当てられた Azure ユーザーには、SSH を介して Microsoft Entra で VM にサインインする権限は自動的に付与されません。 これは、仮想マシンを管理するユーザーと仮想マシンにアクセスできるユーザーを意図的に (なおかつ監査上) 分離するためです。

VM のロールの割り当てを構成するには、次の 2 つの方法があります。

- Azure portal での操作
- Azure Cloud Shell エクスペリエンス

メモ

仮想マシンの管理者ログインおよび仮想マシンのユーザー ログインのロールでは `dataActions` を使用し、管理グループ、サブスクリプション、リソース グループ、またはリソースのスコープで割り当てることができます。 ロールの割り当ては、個々の VM レベルにではなく、管理グループ、サブスクリプション、またはリソースのグループ レベルで行なうことをお勧めします。 この方法では、サブスクリプションあたりの [Azure ロールの割り当て制限](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/troubleshoot-limits) に達するリスクを回避できます。

#### Azure portal

Microsoft Entra ID 対応 Linux VM のロールの割り当てを構成するには:

1. **リソース グループ**の場合は、VM とそれに関連付けられている仮想ネットワーク、ネットワーク インターフェイス、パブリック IP アドレス、またはロード バランサー リソースを含むリソース グループを選択します。
2. **[アクセス制御 (IAM)]** を選択します。
3. [&gt;] を選択して、[**ロールの割り当ての追加]** ページを開きます。
4. 次のロールを割り当てます。 詳細な手順については、 [Azure portal を使用した Azure ロールの割り当てに関するページを](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal)参照してください。

    | 設定 | 値 |
    | --- | --- |
    | 役割 | **仮想マシン管理者ログイン** または **仮想マシン ユーザー ログイン** |
    | アクセスの割り当て先 | ユーザー、グループ、サービス プリンシパル、またはマネージド ID |

    [Image: ロールの割り当てを追加するためのページを示すスクリーンショット。]

しばらくすると、セキュリティ プリンシパルに選択されたスコープのロールが割り当てられます。

#### Azure Cloud Shell

次の例では、 [az role assignment create](https://learn.microsoft.com/ja-jp/cli/azure/role/assignment#az-role-assignment-create) を使用して、現在の Azure ユーザーの VM に仮想マシン管理者ログイン ロールを割り当てます。 [az account show](https://learn.microsoft.com/ja-jp/cli/azure/account#az-account-show) を使用して現在の Azure アカウントのユーザー名を取得し、az [vm show](https://learn.microsoft.com/ja-jp/cli/azure/vm#az-vm-show) を使用して前の手順で作成した VM にスコープを設定します。

リソース グループまたはサブスクリプション レベルでスコープを割り当てることもできます。 Azure RBAC における通常の継承アクセス許可が適用されます。

```azurecli
username=$(az account show --query user.name --output tsv)
rg=$(az group show --resource-group myResourceGroup --query id -o tsv)

az role assignment create \
    --role "Virtual Machine Administrator Login" \
    --assignee $username \
    --scope $rg
```

メモ

Microsoft Entra ドメインとログイン ユーザー名ドメインが一致しない場合は、`--assignee-object-id` のユーザー名だけでなく、`--assignee` を使用してユーザー アカウントのオブジェクト ID を指定する必要があります。 [az ad user list](https://learn.microsoft.com/ja-jp/cli/azure/ad/user#az-ad-user-list) を使用して、ユーザー アカウントのオブジェクト ID を取得できます。

Azure RBAC を使用して Azure サブスクリプション リソースへのアクセスを管理する方法の詳細については、「Azure [ロールを割り当てる手順](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-steps)」を参照してください。

### Azure CLI 用 SSH 拡張機能をインストールする

Azure Cloud Shell を使用している場合は、Azure CLI の最低限必要なバージョンと Azure CLI 用 SSH 拡張機能の両方が既に Cloud Shell 環境に含まれているため、他のセットアップは必要ありません。

次のコマンドを実行して、Azure CLI 用 SSH 拡張機能を追加します。

```azurecli
az extension add --name ssh
```

拡張機能に必要な最小バージョンは 0.1.4 です。 次のコマンドを使ってインストール済みバージョンを確認します。

```azurecli
az extension show --name ssh
```

### 条件付きアクセス ポリシーを適用する

Microsoft Entra ログインで有効になる条件付きアクセス ポリシーを適用できます。たとえば、

- 多要素認証を要求する、
- SSH クライアントを実行するデバイスに対して準拠デバイスまたは Microsoft Entra ハイブリッド参加済みデバイスを要求する。
- リスクを確認する、といったポリシーを適用したうえで、Azure 内の Linux VM へのアクセスを認可することが考えられます。

条件付きアクセス ポリシーに表示されるアプリケーションは、"Azure Linux VM Sign-In" という名前です。

メモ

SSH クライアントを実行しているデバイスに対して準拠デバイスまたは Microsoft Entra ハイブリッド参加済みデバイスを要求する条件付きアクセス ポリシーの適用は、Windows および macOS で実行されている Azure CLI でのみ機能します。 Linux や Azure Cloud Shell から Azure CLI を使用している場合はサポートされません。

#### アプリケーションが見つかりません

Azure Linux VM サインイン アプリケーションが条件付きアクセスに含まれていない場合は、アプリケーションがテナントに存在しないことを確認します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**に移動します。
3. フィルターを削除してすべてのアプリケーションを表示し、 **仮想マシン**を検索します。 結果に Microsoft Azure Linux 仮想マシン サインインが表示されない場合は、テナントにサービス プリンシパルは含まれていません。

### Microsoft Entra ユーザー アカウントを使用して Linux VM に SSH 接続し、ログインする

#### Azure CLI を使用してログインする

「`az login`」と入力します。 このコマンドによりブラウザー ウィンドウが開き、Microsoft Entra アカウントを使用してサインインできます。

```azurecli
az login 
```

次に「`az ssh vm`」と入力します。 次の例では、VM の適切な IP アドレスが自動的に解決されます。

```azurecli
az ssh vm -n myVM -g AzureADLinuxVM
```

メッセージが表示されたら、ログイン ページで Microsoft Entra ログイン資格情報を入力し、多要素認証を実行するか、デバイス チェックを満たします。 Azure CLI セッションが必要な条件付きアクセス基準をまだ満たしていない場合にのみ、メッセージが表示されます。 ブラウザー ウィンドウを閉じ、SSH プロンプトに戻ると、VM に自動的に接続されます。

これで、VM ユーザーや VM 管理者などのロールのアクセス許可が割り当てられた Linux 仮想マシンにサインインしました。 自分のユーザー アカウントに仮想マシンの管理者ログイン ロールが割り当てられている場合は、sudo を使用してルート権限を必要とするコマンドを実行できます。

#### Azure Cloud Shell を使用してログインする

クライアント コンピューターにローカルに何もインストールする必要なく、Azure Cloud Shell を使用して VM に接続できます。 Azure portal の右上隅にあるシェル アイコンを選択して、Cloud Shell を起動します。

Cloud Shell によって、サインイン済みユーザーのコンテキストでセッションに自動的に接続します。 再度 `az login` を実行し、対話型サインイン フローを実行します。

```azurecli
az login
```

それにより、通常の `az ssh vm` コマンドを実行して、VM の名前とリソース グループまたは IP アドレスを使用して接続できます。

```azurecli
az ssh vm -n myVM -g AzureADLinuxVM
```

メモ

Azure Cloud Shell を使用する場合は、準拠デバイスまたは Microsoft Entra ハイブリッド参加済みデバイスを要求する条件付きアクセス ポリシーの適用はサポートされません。

### Microsoft Entra サービス プリンシパルを使用して SSH 接続し、Linux VM にログインする

Azure CLI では、ユーザー アカウントではなく、サービス プリンシパルによる認証がサポートされています。 サービス プリンシパルは特定のユーザーに関連付けられていないため、お客様は、それらを使用して、VM に SSH 接続して、目的の自動化シナリオをサポートできます。 サービス プリンシパルには、VM 管理者または VM ユーザー権限が割り当てられている必要があります。 サブスクリプションまたはリソース グループ レベルでアクセス許可を割り当てます。

次の例では、リソース グループ レベルで、サービス プリンシパルに VM 管理者権限を割り当てます。 サービス プリンシパル オブジェクト ID、サブスクリプション ID、およびリソース グループ名を適切なプレースホルダーに置き換えてください。

```azurecli
az role assignment create \
    --role "Virtual Machine Administrator Login" \
    --assignee-object-id <service-principal-objectid> \
    --assignee-principal-type ServicePrincipal \
    --scope "/subscriptions/<subscription-id>/resourceGroups/<resourcegroup-name>"
```

次の例では、サービス プリンシパルを使用して Azure CLI に対して認証を行います。 詳細については、サービス プリンシパルを使用 [した Azure CLI へのサインインに関する記事を参照してください](https://learn.microsoft.com/ja-jp/cli/azure/authenticate-azure-cli#sign-in-with-a-service-principal)。

```azurecli
az login --service-principal -u <sp-app-id> -p <password-or-cert> --tenant <tenant-id>
```

サービス プリンシパルによる認証が完了したら、通常の Azure CLI SSH コマンドを使用して VM に接続します。

```azurecli
az ssh vm -n myVM -g AzureADLinuxVM
```

### OpenSSH をサポートする SSH クライアントで使用するための SSH 構成のエクスポート

Microsoft Entra ID を使用して Azure Linux VM にサインインすると、OpenSSH 証明書と構成をエクスポートできます。 つまり、OpenSSH ベースの証明書をサポートする任意の SSH クライアントを使用して、Microsoft Entra ID 経由でサインインできます。 次の例では、VM に割り当てられているすべての IP アドレスの構成をエクスポートしています。

```azurecli
az ssh config --file ~/.ssh/config -n myVM -g AzureADLinuxVM
```

または、IP アドレスだけを指定して、構成をエクスポートすることもできます。 次の例の IP アドレスをお使いの VM のパブリックまたはプライベート IP アドレスに置き換えます (プライベート IP には独自の接続を使用する必要があります)。このコマンドのヘルプについては、「`az ssh config -h`」と入力してください。

```azurecli
az ssh config --file ~/.ssh/config --ip 10.11.123.456
```

その後、通常の OpenSSH の使用方法によって VM に接続できます。 接続は、OpenSSH を使用する任意の SSH クライアント経由で行うことができます。

### Microsoft Entra ログインを使用して sudo を実行する

VM 管理者ロールが割り当てられたユーザーが、Linux VM への SSH 接続に成功すると、他の操作や認証要件なしで sudo を実行できるようになります。 VM ユーザー ロールが割り当てられたユーザーは sudo を実行できません。

### 仮想マシン スケール セット内の VM に接続する

仮想マシン スケール セットはサポートされていますが、仮想マシン スケールセット内の VM の有効化と接続の手順は若干異なります。

1. 仮想マシン スケール セットを作成するか、既に存在するものを選択します。 仮想マシン スケール セットに対して、システムによって割り当てられたマネージド ID を有効にします。

    ```azurecli
    az vmss identity assign --name myVMSS --resource-group AzureADLinuxVM
    ```
2. 仮想マシン スケール セットに Microsoft Entra 拡張機能をインストールします。

    ```azurecli
    az vmss extension set --publisher Microsoft.Azure.ActiveDirectory --name AADSSHLoginForLinux --resource-group AzureADLinuxVM --vmss-name myVMSS
    ```

通常、仮想マシン スケール セットには、パブリック IP アドレスは含まれません。 Azure 仮想ネットワークに到達できる別のマシンから接続する必要があります。 この例では、仮想マシン スケール セット内の VM のプライベート IP を使用して、同じ仮想ネットワーク内のマシンから接続する方法を示しています。

```azurecli
az ssh vm --ip 10.11.123.456
```

メモ

`--resource-group` および `--name` スイッチを使用して、仮想マシン スケール セット VM の IP アドレスを自動的に決定することはできません。

### 以前の (プレビュー) バージョンから移行する

デバイス コード フローに基づいた以前のバージョンの Linux 用 Microsoft Entra ログインを使用している場合は、Azure CLI を使用して次の手順を実行してください。

1. VM の AADLoginForLinux 拡張機能をアンインストールします。

    ```azurecli
    az vm extension delete -g MyResourceGroup --vm-name MyVm -n AADLoginForLinux
    ```

    メモ

    VM に現在ログインしている Microsoft Entra ユーザーがいる場合、拡張機能のアンインストールは失敗する可能性があります。 すべてのユーザーが最初にログアウトするようにしてください。
2. VM でシステム割り当てマネージド ID を有効にします。

    ```azurecli
    az vm identity assign -g myResourceGroup -n myVm
    ```
3. VM に AADLoginForLinux 拡張機能をインストールします。

    ```azurecli
    az vm extension set \
        --publisher Microsoft.Azure.ActiveDirectory \
        --name AADSSHLoginForLinux \
        --resource-group myResourceGroup \
        --vm-name myVM
    ```

### Azure Policy を使用して、標準および評価コンプライアンスを満たす

Azure Policy を使用して次のことを行います。

- 新規および既存の Linux 仮想マシンに対して Microsoft Entra ログインを有効にする。
- コンプライアンス ダッシュボードで環境のコンプライアンスを大規模に評価する。

この機能により、さまざまなレベルの適用を使用できます。 Microsoft Entra ログインが有効になっていない環境内の新規および既存の Linux VM にフラグを設定できます。 さらに、Azure Policy を使用して、Microsoft Entra ログインが有効になっていない新しい Linux VM に Microsoft Entra 拡張機能をデプロイできるほか、既存の Linux VM を同じ標準に修復することもできます。

これらの機能に加え、Azure Policy を使用して、承認されていないローカル アカウントが作成されている Linux VM を検出してフラグを設定することもできます。 詳細については、 [Azure Policy](https://learn.microsoft.com/ja-jp/azure/governance/policy/overview) を確認してください。

### サインアップに関する問題のトラブルシューティング

Microsoft Entra の資格情報を使用して SSH 接続しようとしたときによく発生するエラーは、以降のセクションを使用して解決してください。

#### ローカル キャッシュからトークンを取得できない

トークンをローカル キャッシュから取得できないというメッセージが表示された場合は、もう一度 `az login` を実行し、対話型サインイン フローを実行する必要があります。 Azure Cloud Shell を使用したログインに関するセクションを確認します。

#### アクセスが拒否されました:Azure ロールが割り当てられていません

SSH プロンプトで "Azure ロールが割り当てられていない" という内容のエラーが表示された場合は、仮想マシンの管理者ログイン ロールまたは仮想マシンのユーザー ログイン ロールのいずれかをユーザーに付与する VM 用 Azure RBAC ポリシーが構成済みであることを確認してください。 Azure ロールの割り当てに問題がある場合は、 [Azure RBAC のトラブルシューティング](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/troubleshooting)に関する記事を参照してください。

#### 使用していない (AADLoginForLinux) 拡張機能の削除に関する問題

アンインストール スクリプトが失敗した場合、拡張機能が移行中状態で停止する可能性があります。 これが発生した場合、拡張機能の削除プロセス中にアンインストールされるはずのパッケージが残る可能性があります。 このような場合は、使用していないパッケージを手動でアンインストールしてから、`az vm extension delete` コマンドの実行を試すことをお勧めします。

古いパッケージをアンインストールするには:

1. 管理特権を持つローカル ユーザーとしてログインします。
2. ログインしている Microsoft Entra ユーザーがいないことを確認します。 `who -u` コマンドを呼び出して、ログインしているユーザーを確認します。 次に、前のコマンドによって報告されたすべてのセッション プロセスについて `sudo kill <pid>` を実行します。
3. `sudo apt remove --purge aadlogin` (Ubuntu または Debian)、`sudo yum remove aadlogin` (RHEL)、または `sudo zypper remove aadlogin` (openSUSE または SLES) を実行します。
4. コマンドが失敗した場合は、スクリプトを無効にして下位レベルのツールを試してください。
    1. Ubuntu または Deian の場合、`sudo dpkg --purge aadlogin` を実行します。 スクリプトが理由でまだ失敗する場合は、`/var/lib/dpkg/info/aadlogin.prerm` ファイルを削除して、再度試してください。
    2. 他のすべての場合は、`rpm -e --noscripts aadlogin` を実行します。
5. パッケージ `aadlogin-selinux` に対して手順 3 から 4 を繰り返します。

#### 拡張機能のインストール エラー

既存のコンピューターへの AADSSHLoginForLinux VM 拡張機能のインストールが、次のいずれかの既知のエラー コードで失敗する場合があります。

##### ゼロ以外の終了コード 22

終了コード 22 が表示された場合、AADSSHLoginForLinux VM 拡張機能の状態はポータルで **[移行中** ] と表示されます。

このエラーは、システム割り当てマネージド ID が必要であるために発生します。

解決策は次のとおりです。

1. 失敗した拡張機能をアンインストールします。
2. Azure VM でシステム割り当てマネージド ID を有効にします。
3. 拡張機能のインストール コマンドを再度実行します。

##### ゼロ以外の終了コード 23

終了コード 23 が表示された場合、AADSSHLoginForLinux VM 拡張機能の状態はポータルで **[移行中** ] と表示されます。

このエラーは、古い AADLoginForLinux VM 拡張機能がまだインストールされていると発生します。

VM から古い AADLoginForLinux VM 拡張機能をアンインストールすることが解決策となります。 新しい AADSSHLoginForLinux VM 拡張機能の状態は、ポータルで [ **プロビジョニングに成功しました** ] に変わります。

##### HTTP プロキシを使用するときのインストールの失敗

この拡張機能では、パッケージをインストールし、システム ID の存在を確認するために HTTP 接続が必要です。 これは `walinuxagent.service` のコンテキストで実行され、エージェントがプロキシ設定について知ることができるようにするための変更を必要とします。 ターゲット マシンで `/lib/systemd/system/walinuxagent.service` ファイルを開き、`[Service]` の後に次の行を追加します。

```
[Service]
Environment="http_proxy=http://proxy.example.com:80/"
Environment="https_proxy=http://proxy.example.com:80/"
Environment="no_proxy=169.254.169.254"
```

エージェントを再起動します (`sudo systemctl restart walinuxagent`)。 もう一度やり直してください。

##### az ssh vm コマンドが KeyError access\_token で失敗する

古いバージョンの Azure CLI クライアントを使用していると `az ssh vm` コマンドが失敗します。

Azure CLI クライアントをバージョン 2.21.0 以降にアップグレードすることが解決策となります。

##### SSH 接続が閉じられる

ユーザーが `az login` を使用して正常にサインインした後、`az ssh vm -ip <address>` または `az ssh vm --name <vm_name> -g <resource_group>` を使用した VM への接続が、" &lt;ip\_address&gt; ポート 22 によって接続が閉じられました" で失敗する場合があります。

このエラーの原因の 1 つは、ユーザーが、この VM のスコープ内で仮想マシンの管理者ログインと仮想マシンのユーザー ログインのいずれのロールにも割り当てられていないことです。 その場合は、この VM のスコープ内で、そのどちらかの Azure RBAC ロールにユーザーを追加することが解決策となります。

また、このエラーは、必要な Azure RBAC ロールにユーザーが含まれているものの、その VM でシステム割り当てマネージド ID が無効になっている場合にも発生します。 その場合は、次のアクションを実行します。

1. VM でシステム割り当てマネージド ID を有効にします。
2. 数分待ってから、`az ssh vm --ip <ip_address>` を使用して接続を試みます。

#### 仮想マシン スケール セットの接続に関する問題

仮想マシン スケール セット インスタンスで古いモデルが実行されている場合、スケール セットの VM 接続が失敗することがあります。

特に Microsoft Entra ログイン拡張機能のインストール後アップグレードが実行されていなかった場合、スケール セット インスタンスを最新のモデルにアップグレードすると、問題が解決する可能性があります。 インスタンスをアップグレードすると、標準のスケール セット構成が個々のインスタンスに適用されます。

#### sshd\_config に AllowGroups ステートメントまたは DenyGroups ステートメントが含まれていると、Microsoft Entra ユーザーの最初のサインインが失敗する

 と `AllowGroups` のいずれかのステートメントが `DenyGroups` に含まれている場合、Microsoft Entra ユーザーによる最初のログインは失敗します。 ステートメントが追加されたのが、既にユーザーがログインに成功した後であった場合はログイン可能です。

1 つの解決策は、sshd\_configから `AllowGroups` ステートメントと `DenyGroups` ステートメントを削除 *することです*。

もう 1 つの解決策は、`AllowGroups` と `DenyGroups` を `match user` の  セクションに移動することです。 一致テンプレートに Microsoft Entra ユーザーが含まれていないことを確認してください。

#### Azure Shell から Linux Red Hat/Oracle 7.X VM に接続しようとすると、"アクセス許可が拒否されました" と表示される。

ターゲット VM 7.4 の OpenSSH サーバー バージョンが古すぎます。 OpenSSH クライアント バージョン 8.8 と互換性のないバージョンです。 詳細については、「 [RSA SHA256 証明書が機能しなくなった](https://bugzilla.mindrot.org/show_bug.cgi?id=3351) 」を参照してください。

対応策 :

- `"PubkeyAcceptedKeyTypes= +ssh-rsa-cert-v01@openssh.com"` コマンドにオプション `az ssh vm` を追加します。

```azurecli
az ssh vm -n myVM -g MyResourceGroup -- -A -o "PubkeyAcceptedKeyTypes= +ssh-rsa-cert-v01@openssh.com"
```

- `"PubkeyAcceptedKeyTypes= +ssh-rsa-cert-v01@openssh.com"` に オプション `/home/<user>/.ssh/config file` を追加します。

`"PubkeyAcceptedKeyTypes +ssh-rsa-cert-v01@openssh.com"` をクライアント構成ファイルに追加します。

```config
Host *
PubkeyAcceptedKeyTypes +ssh-rsa-cert-v01@openssh.com
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/howto-vm-sign-in-azure-ad-windows"} -->
## Microsoft Entra ID を使用してAzureで Windows 仮想マシンにサインインする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-vm-sign-in-azure-ad-windows
- Service: entra-id / devices
- Article date: 2025-06-27
- Summary: Microsoft Entra 認証を使用して Windows を実行しているAzure VM にサインインする方法について説明します。

組織は、Microsoft Entra 認証と統合することで、Azureでの Windows Server virtual machinesのセキュリティを向上させることができます。 Microsoft Entra IDを主要な認証プラットフォームとして使用し、サポートされているバージョンのWindows ServerにRDPを使用してリモートデスクトップ接続できるようになりました。 その後、Azureのロールベースのアクセス コントロール (RBAC) ポリシーと条件付きアクセス ポリシーを一元的に制御および適用し、virtual machinesへのアクセスを許可または拒否することができます。

この記事では、Windows Server 仮想マシンを作成して構成し、Microsoft Entra ID ベースの認証を使用してサインインする方法について説明します。

Microsoft Entra ID ベースの認証を使用して、Azureで Windows Server virtual machinesにサインインすると、多くのセキュリティ上の利点があります。 これには次のようなものがあります。

- Windows Server virtual machinesにサインインするには、パスワードレスを含む Microsoft Entra 認証を使用します。 ローカル管理者アカウントへの依存度を下げる。
- Microsoft Entra ID用に構成するパスワードの複雑さとパスワードの有効期間ポリシーを使用すると、Windows Server virtual machinesのセキュリティ保護にも役立ちます。
- Azureのロールベースアクセス制御を使用します。
    - 通常のユーザーまたは管理者特権でサインインできるユーザーを指定します。
    - ユーザーがチームに参加または退出するときに、Azure のロールベースのアクセス制御ポリシーを更新して、必要に応じてアクセスを付与できます。
    - 従業員が組織を離れ、ユーザー アカウントが無効になったり、Microsoft Entra IDから削除されたりすると、リソースにaccessできなくなります。
- 条件付きアクセス ポリシー「フィッシング耐性 MFA」と、ユーザーサインイン リスクなどのその他のシグナルを使用します。
- Azure Policyを使用して、Windows Server virtual machinesに Microsoft Entra のサインインを要求するポリシーを展開および監査し、virtual machinesで未承認のローカル アカウントの使用にフラグを設定します。
- Intune を使用して、仮想デスクトップ インフラストラクチャ (VDI) 展開の一部である Azure Windows VM のモバイルデバイス管理 (MDM) 自動登録により、Microsoft Entra 参加を自動化し、スケーリングします。 MDM 自動登録には P1 ライセンスMicrosoft Entra ID必要があります。

MDM 自動登録には P1 ライセンスMicrosoft Entra ID必要があります。 Windows Server VM は MDM 登録をサポートしていません。

重要

この機能を有効にすると、Azure仮想マシンが Microsoft Entra に参加します。 オンプレミスの Active Directoryや Microsoft Entra Domain Services などの別のドメインに参加することはできません。 必要であれば、拡張機能をアンインストールして、Microsoft Entra からデバイスを切断します。 さらに、サポートされているゴールデン イメージをデプロイする場合は、拡張機能をインストールしてMicrosoft Entra ID認証を有効にすることができます。 条件付きAccessは、Azure Windows virtual machines の Microsoft Entra 参加拡張機能を使用した Windows Server ではサポートされていません。

### 必要条件

#### サポートされているAzureリージョンと Windows ディストリビューション

この機能は現在、次の Windows Server ディストリビューションをサポートしています。

- Windows 11 24H2 以降がインストールされています。
- Windows Server 2022、2025 以降がデスクトップ エクスペリエンスと共にインストールされます。

この機能は、次のAzure クラウドで使用できるようになりました。

- Azure グローバル
- Azure Government
- 21Vianet が運営する Microsoft Azure

注意

CIS 強化イメージは、Microsoft Windows Enterprise および Microsoft Windows Server オファリングのMicrosoft Entra ID認証をサポートします。 詳細については、「 [Microsoft Windows Enterprise の CIS セキュリティ強化イメージ」を](https://azuremarketplace.microsoft.com/marketplace/apps/center-for-internet-security-inc.cis-windows-server)参照してください。

#### ネットワークの要件

Azure で Microsoft Entra 認証を仮想マシンで有効にするには、ネットワーク構成で TCP ポート 443 経由で次のエンドポイントへの送信アクセスが許可されていることを確認する必要があります。

Azure グローバル:

- `https://enterpriseregistration.windows.net`: デバイスの登録。
- `http://169.254.169.254`：Azure インスタンス メタデータ サービス エンドポイント。
- `https://login.microsoftonline.com`: 認証フロー。
- `https://pas.windows.net`: Azure のロールベースのアクセス制御フロー。

Azure Government:

- `https://enterpriseregistration.microsoftonline.us`: デバイスの登録。
- `http://169.254.169.254`：Azure インスタンス メタデータ サービス エンドポイント。
- `https://login.microsoftonline.us`: 認証フロー。
- `https://pasff.usgovcloudapi.net`: Azure のロールベースのアクセス制御フロー。

21Vianet が運営する Microsoft Azure:

- `https://enterpriseregistration.partner.microsoftonline.cn`: デバイスの登録。
- `http://169.254.169.254`：Azure インスタンス メタデータ サービス エンドポイント。
- `https://login.chinacloudapi.cn`: 認証フロー。
- `https://pas.chinacloudapi.cn`: Azure のロールベースのアクセス制御フロー。

#### 認証の要件

- **User アカウントの種類**: azure Windows 仮想マシンと同じテナントの標準の Microsoft Entra ユーザー アカウントのみがサポートされます。 [ゲスト アカウント (B2B ユーザー)](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) を認証に使用することはできません。
- **管理 ID**: Microsoft Entra サインイン仮想マシン拡張機能をインストールする前に、Azure仮想マシンでシステム割り当てマネージド ID を有効にする必要があります。 マネージド ID は単一の Microsoft Entra テナントに格納され、現在、クロス ディレクトリ シナリオはサポートされていません。
- **ロールの割り当て**: VM にサインインするには、 **仮想マシン管理者ログイン** ロールまたは **仮想マシン ユーザー ログイン** ロールをユーザーに割り当てる必要があります。 所有者ロールまたは共同作成者ロールだけでは、サインイン特権は付与されません。
- **仮想マシン管理者ログイン:** このロールが割り当てられているユーザーは、管理者特権でAzure仮想マシンにサインインできます。
- **仮想マシン ユーザー ログイン:** このロールが割り当てられているユーザーは、通常のユーザー特権でAzure仮想マシンにサインインできます。
- **認証方法**:

    - [パスワードレス認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2) が推奨され、Microsoft Entra 資格情報を使用してサポートされます
    - [パスワードベースの認証](https://learn.microsoft.com/ja-jp/entra/architecture/auth-password-based-sso) は、適切なロールの割り当てでサポートされています
    - [Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/) は、証明書信頼モデルのみを使用してサポートされます (キー信頼モデルではありません)
    - ローカル グループにユーザーを追加して手動で作成されたローカル管理者アカウントはサポートされていません

### Azure で Windows 仮想マシンの Microsoft Entra サインインを有効にする

Windows 仮想マシンに Microsoft Entra サインインを使用するには、次の手順を実行する必要があります。

1. デバイスの Microsoft Entra サインイン拡張機能を有効にします。
2. ユーザーに対する Azure ロールの割り当てを構成します。

#### Microsoft Entra サインイン拡張機能を有効にする

- [Azure Windows を実行している仮想マシン](https://learn.microsoft.com/ja-jp/azure/virtual-machines/extensions/features-windows#run-vm-extensions)

Microsoft Entra サインイン仮想マシン拡張機能をインストールする前に、Azure仮想マシンでシステム割り当てマネージド ID を有効にする必要があります。 マネージド ID は単一の Microsoft Entra テナントに格納され、現在、クロス ディレクトリ シナリオはサポートされていません。

次の例では、Azure仮想マシン拡張機能のAzure テンプレートを示します。

```json
{
  "$schema": "http://schema.management.azure.com/schemas/2015-01-01/deploymentTemplate.json#",
  "contentVersion": "1.0.0.0",
  "parameters": {
    "vmName": {
      "type": "string"
    },
    "location": {
      "type": "string"
    }
  },
  "resources": [
    {
      "name": "[concat(parameters('vmName'),'/AADLogin')]",
      "type": "Microsoft.Compute/virtualMachines/extensions",
      "location": "[parameters('location')]",
      "apiVersion": "2015-06-15",
      "properties": {
        "publisher": "Microsoft.Azure.ActiveDirectory",
        "type": "AADLoginForWindows",
        "typeHandlerVersion": "1.0",
        "autoUpgradeMinorVersion": true
      }
    }
  ]
}
```

### ロールの割り当てを構成する

ユーザー ロールを割り当てるには、[Virtual Machine Data Access Administrator](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-data-access-administrator-preview) ロール、または `Microsoft.Authorization/roleAssignments/write` ロールなどの  アクションを含むロールが必要です。 ただし、Virtual Machine Data Access Administrator とは異なるロールを使用する場合は、[ロールの割り当てを作成するアクセス許可を減らす条件を追加することをお勧めします](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/delegate-role-assignments-overview)。

- **仮想マシン管理者ログイン:** このロールが割り当てられているユーザーは、管理者特権でAzure仮想マシンにサインインできます。
- **仮想マシン ユーザー ログイン:** このロールが割り当てられているユーザーは、通常のユーザー特権でAzure仮想マシンにサインインできます。

注意

ローカル管理者グループのメンバーにユーザーを追加するか、コマンドを実行してユーザーをデバイスのローカル管理者に手動で昇格 `net localgroup administrators /add "AzureAD\UserUpn"` サポートされていません。 サインインを承認するには、Azureでロールを使用する必要があります。

注意

所有者ロールまたは共同作成者ロールが割り当てられているAzure ユーザーは、デバイスにサインインする権限を自動的に持ちません。 その理由は、仮想マシンを制御するユーザーのグループと、仮想マシンにアクセスすることができるユーザーのグループの間で、監査された分離を提供するためです。

次のドキュメントでは、Azureのロールの割り当てにユーザー アカウントを追加する手順について説明します。

- [Azure ポータルを使用して Azure ロールを割り当てる](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal)
- [Azure CLI を使用して Azure のロールを割り当てる](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-cli)
- [Azure PowerShell を使用して Azure ロールを割り当てる](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-powershell)

### Microsoft Entra 資格情報を使用して Windows VM にサインインする

RDP 経由のサインインには、以下の 2 つの方法があります。

- パスワードレス (サポートされている Microsoft Entra 資格情報のいずれかを使用) (推奨)
- 証明書信頼モデルを使用してデプロイされた Windows Hello for Business を使用したパスワード/パスワードレス

#### Microsoft Entra IDでパスワードレス認証を使用してサインインする

Azureの Windows Server VM にパスワードレス認証を使用するには、セッション ホスト (VM) が実行されている必要があります。

- Windows 11 では、[Windows 11 用の 2022-10 累積的な更新プログラム (KB5018418)](https://support.microsoft.com/kb/KB5018418) またはそれ以降のバージョンがインストールされていること。
- [Windows 10 用の 2022-10 累積的な更新プログラム (KB5018410)](https://support.microsoft.com/kb/KB5018410) 以降がインストールされた Windows 10 バージョン 20H2 以降。
- [Microsoft サーバー オペレーティング システム用の 2022-10 累積的な更新プログラム (KB5018421)](https://support.microsoft.com/kb/KB5018421) 以降がインストールされた Windows Server 2022。
- Windows Server 2025 以降がインストールされています。

注意

**web アカウントを使用してリモート コンピューター** オプションにサインインする場合、ローカル デバイスをドメインまたはMicrosoft Entra IDに参加させる必要はありません。

リモート コンピュータに接続するには:

- Windows Search から **リモート デスクトップ Connection** を起動するか、`mstsc.exe` を実行します。
- **[詳細設定]** タブの **[Web アカウントを使用してリモート コンピューターにサインインする]** オプションを選択します。このオプションは `enablerdsaadauth` RDP プロパティに相当します。 詳細については、[リモート デスクトップ サービスでサポートされている RDP プロパティ](https://learn.microsoft.com/ja-jp/windows-server/remote/remote-desktop-services/clients/rdp-files)を参照してください。
- リモート コンピューターの名前を指定し、**[接続]** を選択します。

重要

IP アドレスは、[ **Web アカウントを使用してリモート コンピューターにサインインする** ] オプションでは使用できません。 名前は、Microsoft Entra ID内のリモート デバイスのホスト名と一致し、リモート デバイスの IP アドレスに解決して、ネットワーク アドレス指定可能である必要があります。

- 資格情報の入力を求められたら、ユーザー名を `user@domain.com` 形式で指定します。
- その後、新しい PC に接続するときに、remote desktop接続を許可するように求められます。 Microsoft Entra では、このプロンプトが再び表示されるまでの 30 日間にわたって、最大 15 個のホストが記憶されます。 このダイアログが表示された場合は、**[はい]** を選択して接続します。

重要

組織が [Microsoft Entra 条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) を使用している場合、デバイスはリモート コンピューターへの接続を許可するために条件付きAccess要件を満たす必要があります。 条件付きアクセス ポリシーは、制御されたアクセスのために、アプリケーション **Microsoft リモート デスクトップ (a4a365df-50f1-4397-bc59-1a1564b8bb9c)** に適用される場合があります。

注意

リモート セッションの Windows ロック画面では、Microsoft Entra 認証トークンや、FIDO キーなどのパスワードレス認証方法はサポートされていません。 これらの認証方法がサポートされていないということは、ユーザーがリモート セッションで画面のロックを解除できないことを意味します。 ユーザー アクションまたはシステム ポリシーを使用してリモート セッションをロックしようとすると、セッションは切断され、サービスはユーザーにメッセージを送信します。 セッションを切断すると、非アクティブな期間が経過した後に接続が再開されるときに、Microsoft Entra ID が適用可能な条件付きアクセス ポリシーを再評価します。

#### Microsoft Entra IDでパスワード/パスワードレス認証を使用してサインインする

[Password ベースの認証](https://learn.microsoft.com/ja-jp/entra/architecture/auth-password-based-sso)、[Passwordless 認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)の両方がWindows virtual machinesにサインインするためにサポートされています。

重要

Microsoft Entra ID に参加している VM へのリモート接続は、Microsoft Entra 登録済み (最小必要ビルドは 20H1) または Microsoft Entra 参加済みまたは Microsoft Entra ハイブリッドが VM と *同じ* ディレクトリに参加している Windows 10 以降の PC からのみ許可されます。 さらに、Microsoft Entra 資格情報を使用して RDP を実行するには、仮想マシン管理者ログインまたは仮想マシン ユーザー ログインという 2 つのAzureロールのいずれかにユーザーが属している必要があります。

Microsoft Entra 登録済み Windows 10 以降の PC を使用している場合は、 `AzureAD\UPN` 形式 ( `AzureAD\john@contoso.com` など) で資格情報を入力する必要があります。 現時点では、Azure Bastionを使用し、Microsoft Entra認証を介して[Azure CLIおよびネイティブのRDPクライアントmstsc](https://learn.microsoft.com/ja-jp/azure/bastion/native-client)を用いてサインインできます。

Microsoft Entra IDを使用して Windows Server 2019 仮想マシンにサインインするには:

1. Microsoft Entra サインインで有効になっている仮想マシンの概要ページに移動します。
2. **[接続]** を選び、**[仮想マシンに接続する]** ペインを開きます。
3. **[RDP ファイルのダウンロード]** を選択します。
4. **Open** を選択して、リモート デスクトップ接続クライアントを開きます。
5. [ **接続** ] を選択して、Windows サインイン ダイアログを開きます。
6. Microsoft Entra 資格情報を使用してサインインします。

これで、VM ユーザーや VM 管理者など、ロールのアクセス許可が割り当てられた Windows Server 2019 Azure仮想マシンにサインインします。

注意

Azure portalの仮想マシンの概要ページに移動して接続オプションを使用する代わりに、`.RDP` ファイルをコンピューターにローカルに保存して、仮想マシンへの今後のremote desktop接続を開始できます。

### 条件付きAccess ポリシーを適用する

ユーザーがAzureで Windows Server virtual machinesをアクセスする前に、"[フィッシング耐性 MFA](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-admin-phish-resistant-mfa)" や [ユーザー サインイン リスク チェック](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength) などの条件付きアクセス ポリシーを適用できます。 条件付きAccess ポリシーを適用するには、クラウド アプリまたはアクションの割り当てオプションから **Microsoft Azure Windows Virtual Machine サインイン** アプリを選択する必要があります。

Windows Server デバイスから接続する場合、デバイス構成規則を使用してサインインを制限する条件付きAccess ポリシーはサポートされません。 条件付きaccess ポリシーの設定に関するガイドについては、「[Tutorial: Microsoft Entra 多要素認証を使用してユーザー サインイン イベントをセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-azure-mfa)を参照してください。

注意

コントロールとして MFA が必要な場合は、ターゲットの Windows Server 仮想マシンへの RDP セッションを開始するクライアントの一部として MFA 要求を指定する必要があります。 リモート デスクトップ アプリケーションは条件付きAccess ポリシーをサポートしていますが、Web サインインを使用している場合は、ビジネス PIN または生体認証にWindows Helloを使用する必要があります。 生体認証のサポートは、Windows 10 バージョン 1809 で RDP クライアントに追加されています。 Windows Hello for Business 認証を使用するRemote desktopは、証明書信頼モデルを使用するデプロイでのみ使用でき、キー信頼モデルでは使用できません。

### Azure Policyを使用して標準を満たし、コンプライアンスを評価する

Azure Policyを使用して次の手順を実行します。

- 新規および既存の Windows Server virtual machinesに対して Microsoft Entra サインインが有効になっていることを確認します。
- コンプライアンス ダッシュボードで環境のコンプライアンスを大規模に評価する。

この機能により、さまざまなレベルの適用を使用できます。 Microsoft Entra サインインが有効になっていない環境内の新規および既存の Windows Server virtual machinesにフラグを設定できます。 Azure Policyを使用して、Microsoft Entra 拡張機能を Azure のWindows virtual machinesに展開することもできます。

これらの機能に加えて、Azure Policyを使用して、デバイスで未承認のローカル アカウントが作成された Windows マシンを検出してフラグを設定できます。 詳細については、[Azure Policy](https://learn.microsoft.com/ja-jp/azure/governance/policy/overview)を確認してください。

### デプロイの問題に関するトラブルシューティング

デバイスが Microsoft Entra 参加プロセスを完了するには、AADLoginForWindows 拡張機能が正常にインストールされている必要があります。 拡張機能が正しくインストールされない場合は、次の手順を実行します。

1. デバイスに接続し、*C:\WindowsAzure\Logs\Plugins\Microsoft.Azure.ActiveDirectory.AADLoginForWindows\1.0.0.1* の下にある *CommandExecution.log* ファイルを調べます。

    最初の失敗後に拡張機能を再起動すると、デプロイ エラーを含むログが、*CommandExecution\_YYYYMMDDHHMMSSSSS.log* として保存されます。
2. デバイスで PowerShell ウィンドウを開きます。 ホスト上で実行されている Azure インスタンス メタデータ サービス エンドポイントに対する次のクエリで、期待される出力が返されることを確認します。

    Azure virtual machinesの場合:

    | 実行するコマンド | 想定される出力 |
    | --- | --- |
    | `curl.exe -H Metadata:true "http://169.254.169.254/metadata/instance?api-version=2017-08-01"` | Azure仮想マシンに関する正しい情報 |
    | `curl.exe -H Metadata:true "http://169.254.169.254/metadata/identity/info?api-version=2018-02-01"` | Azure サブスクリプションに関連付けられている有効なテナント ID |
    | `curl.exe -H Metadata:true "http://169.254.169.254/metadata/identity/oauth2/token?resource=urn:ms-drs:enterpriseregistration.windows.net&api-version=2018-02-01"` | この仮想マシンに割り当てられたマネージド ID に対して、Microsoft Entra ID によって発行された有効なアクセス トークン |
3. PowerShell を使用して、必要なエンドポイントにデバイスからアクセスできることを確認します。

    - `curl.exe https://login.microsoftonline.com/ -D -`
    - `curl.exe https://login.microsoftonline.com/<TenantID>/ -D -`
    - `curl.exe https://enterpriseregistration.windows.net/ -D -`
    - `curl.exe https://device.login.microsoftonline.com/ -D -`
    - `curl.exe https://pas.windows.net/ -D -`

    `<TenantID>` を、Azure サブスクリプションに関連付けられている Microsoft Entra テナント ID に置き換えます。 `login.microsoftonline.com/<TenantID>`、`enterpriseregistration.windows.net`、および `pas.windows.net` は、予期される動作である 404 Not Found を返す必要があります。
4. `dsregcmd /status` を実行してデバイスの状態を表示します。 目標は、デバイスの状態で `AzureAdJoined : YES` と表示されることです。

    Microsoft Entra の参加操作は、イベント ビューアー の *User Device Registration\Admin* ログ、*イベント ビューアー (local)\Applications* および *Services Logs\Microsoft\Windows\User Device Registration\Admin* でキャプチャされます。

AADLoginForWindows 拡張機能がエラー コードで失敗した場合、次の手順を行うことができます。

#### デバイス名は既に存在します

Azure仮想マシンのホスト名と同じ displayName を持つデバイス オブジェクトが存在する場合、デバイスはホスト名の重複エラーで Microsoft Entra に参加できません。 [ホスト名](https://learn.microsoft.com/ja-jp/azure/virtual-network/virtual-networks-viewing-and-modifying-hostnames#modify-a-hostname)を変更して重複を回避します。

#### ターミナル エラー コード 1007 と終了コード -2145648574

ターミナル エラー コード 1007 と終了コード -2145648574 は `DSREG_E_MSI_TENANTID_UNAVAILABLE` に変換されます。 この拡張機能は、Microsoft Entra テナント情報に対してクエリを実行できません。

ローカル管理者としてデバイスに接続し、エンドポイントがAzureインスタンス メタデータ サービスから有効なテナント IDを返すことを確認します。 デバイスの管理者特権の PowerShell ウィンドウから次のコマンドを実行します。

`curl -H Metadata:true http://169.254.169.254/metadata/identity/info?api-version=2018-02-01`

この問題は、管理者が AADLoginForWindows 拡張機能のインストールを試みたが、デバイスにシステム割り当てマネージド ID がない場合にも発生する可能性があります。 その場合は、デバイスの **[ID** ] ウィンドウに移動します。 **[システム割り当て済み]** タブで、**[状態]** トグルが **[オン]** に設定されていることを確認します。

#### 終了コード -2145648607

終了コード -2145648607 は `DSREG_AUTOJOIN_DISC_FAILED` に変換されます。 拡張機能が `https://enterpriseregistration.windows.net` エンドポイントに到達できません。

1. PowerShell を使用して、必要なエンドポイントにデバイスからアクセス可能であることを確認します。

    - `curl https://login.microsoftonline.com/ -D -`
    - `curl https://login.microsoftonline.com/<TenantID>/ -D -`
    - `curl https://enterpriseregistration.windows.net/ -D -`
    - `curl https://device.login.microsoftonline.com/ -D -`
    - `curl https://pas.windows.net/ -D -`

    `<TenantID>` を、Azure サブスクリプションの Microsoft Entra テナント ID に置き換えます。 テナント ID を見つける必要がある場合は、アカウント名にカーソルを合わせるか、**Entra ID**&gt;**Overview**&gt;**Properties**&gt;**Tenant ID** を選択します。

    `enterpriseregistration.windows.net` に接続しようとすると、予期される動作である 404 Not Found が返される場合があります。 `pas.windows.net` への接続を試みると、PIN 資格情報の入力が求められる場合や、404 Not Found が返される場合があります。 (PIN を入力する必要はありません)。URL に到達可能であることを確認するには、いずれか 1 つで十分です。
2. いずれかのコマンドが "ホスト `<URL>`を解決できませんでした" で失敗した場合は、次のコマンドを実行して、Windows が使用している DNS サーバーを特定してみてください。

    `nslookup <URL>`

    `<URL>` を、エンドポイントで使用される完全修飾ドメイン名 (`login.microsoftonline.com` など) に置き換えます。
3. パブリック DNS サーバーを指定してコマンドを正常に実行できるかどうかを確認します。

    `nslookup <URL> 208.67.222.222`
4. 必要に応じて、デバイスが属するネットワーク セキュリティ グループに割り当てられている DNS サーバーを変更します。

#### 終了コード 51

終了コード 51 は、"この拡張機能は、このオペレーティング システムではサポートされていません" に変換されます。

AADLoginForWindows 拡張機能は、Windows Server 2022 または Windows 10 1809 以降の Azure 仮想マシンにのみインストールすることを目的としています。 Windows Server のバージョンがサポートされていることを確認します。 サポートされていない場合は、拡張機能をアンインストールします。

### サインインに関する問題のトラブルシューティング

次の情報を使用して、サインインの問題を修正してください。

`dsregcmd /status` を実行すると、デバイスとシングル サインオン (SSO) の状態を表示できます。 目標は、デバイスの状態が `AzureAdJoined : YES` として表示され、SSO の状態が `AzureAdPrt : YES` と表示されることです。

Microsoft Entra アカウント経由の RDP サインインは、*Applications and Services Logs\Microsoft\Windows\AAD\Operational* イベント ログのイベント ビューアーにキャプチャされます。

#### Azure ロールが割り当てられない

デバイスへのリモート デスクトップ接続を開始する際、次のエラーメッセージが表示されることがあります。「お使いのアカウントは、このデバイスの使用を防ぐように構成されています。」 詳細については、システム管理者にお問い合わせください。"

アカウントがこのデバイスを使用できないように構成されていることを示すメッセージのスクリーンショット

Azureロールベースのaccess control ポリシーを確認します。このポリシーによって、ユーザーに仮想マシン管理者ログインロールまたは仮想マシン ユーザー ログイン ロールが付与されます。

Azureロールの割り当てに問題がある場合は、「Azure ロールベースのアクセス制御のトラブルシューティング」を参照してください。

#### 承認されていないクライアントまたはパスワードの変更が必要

デバイスへのremote desktop接続を開始すると、"資格情報が機能しませんでした" というエラー メッセージが表示されることがあります。

[Image: 資格情報が機能しなかったことを示すメッセージのスクリーンショット。]

次の解決策をお試しください。

- リモート デスクトップ接続を開始するために使用している Windows 10 以降の PC は、同じ Microsoft Entra ディレクトリに Microsoft Entra 参加済み、または Microsoft Entra ハイブリッド参加済みである必要があります。 デバイス ID の詳細については、「[デバイス ID とは](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)」の記事を参照してください。

    Windows 10 ビルド 20H1 では、仮想マシンへの RDP 接続を開始するための Microsoft Entra 登録済み PC のサポートが追加されました。 仮想マシンへの接続を開始するために Microsoft Entra が登録されている (Microsoft Entra 参加済みまたは Microsoft Entra ハイブリッド参加済みではない) クライアント デバイスを RDP クライアントとして使用する場合は、 `AzureAD\UPN` 形式で資格情報を入力する必要があります (たとえば、 `AzureAD\john@contoso.com`)。

    Microsoft Entra への参加が完了した後に AADLoginForWindows 拡張機能がアンインストールされていないことを確認します。

    また、サーバーとクライアントの "両方" で、セキュリティ ポリシー **ネットワーク セキュリティ: このコンピューターに対する PKU2U 認証要求でオンライン ID を使用することを許可する** が有効になっていることを確認します。
- ユーザーが使用しているパスワードが一時パスワードではないことを確認してください。 一時パスワードを使用してremote desktop接続にサインインすることはできません。

    Web ブラウザーでユーザー アカウントを使用してサインインします。 たとえば、プライベートブラウズ ウィンドウで [Azure portal](https://portal.azure.com) にサインインします。 パスワードの変更を求めるメッセージが表示されたら、新しいパスワードを設定します。 その後、接続を再試行してください。

#### AADSTS293004: 要求 xxx のターゲット デバイス識別子がテナント xxx に見つかりませんでした

原因:

mstsc に入力されたコンピューター名が、ターゲット AADJ デバイスの "hostnames" 属性と一致しません。 たとえば、AADJ デバイスのホスト名は device\_1 のような短い名前ですが、mstsc に入力されたコンピューター名は、device\_1.contoso.comのような FQDN です。

次の解決策をお試しください。

この問題を解決するには、複数の方法があります。

1. クライアント コンピューターの HOSTS エントリを変更し、ターゲット マシンの IP に正しいデバイス名 (AAD デバイス レコードから確認) をポイントする DNS レコードを追加します。 mstsc でそのデバイス名を使用します。
2. ターゲット マシンが管理されているかどうかを確認し、ホスト名が DNS クライアント PrimaryDnsSuffix 値 [ADMX_DnsClient Policy CSP を介してGroup Policyまたは MDM によって設定されているかどうかを確認します。 |Microsoft Learn](https://learn.microsoft.com/ja-jp/windows/client-management/mdm/policy-csp-admx-dnsclient#dns_primarydnssuffix)。 これが設定されていて正しくない場合は、削除するか、正しく設定する必要があります。
3. お客様が FQDN を使用して接続する必要があるが、AAD デバイス名が短い名前の場合は、ローカル管理者を介してターゲット コンピューターにログインし、ドメイン サフィックスに "**プライマリ DNS サフィックス**" を追加します。 詳細な手順:

- **[システムの詳細設定]/[システムのプロパティ**] -&gt;\*\* [コンピューター名] タブに移動&gt;、[**変更**] ボタンを選択してコンピューターの名前を変更&gt;[**詳細**]を選択します。既存のコンピューター名 -&gt; ドメイン名を入力し、[**OK]** -&gt; [保存して再起動] を選択します。
- 完了したら、FQDN を直接使用して RDP を実行でき、HOSTS エントリを変更する必要はありません。

注意

このような場合、プライマリ DNS サフィックスが追加されると、Device-Sync スケジュールされたタスクは、FQDN を AAD デバイスの "ホスト名" 属性に追加するときにトリガーされます。 これが問題を解決する理由です。

#### MFA サインイン方法が必要

デバイスへのremote desktop接続を開始すると、次のエラー メッセージが表示されることがあります。"使用しようとしているサインイン方法は許可されていません。 別のサインイン方法を試すか、システム管理者に問い合わせてください。"

[Image: 使用しようとしているサインイン方法が許可されていないことを示すメッセージのスクリーンショット。]

MFA を必要とする条件付きAccess ポリシーを構成する場合は、接続を開始するデバイスがWindows Helloなどの強力な認証を使用していることを確認する必要があります。

もう 1 つの MFA 関連のエラー メッセージは、前述の「資格情報が機能しませんでした」です。

[Image: 資格情報が機能しなかったことを示すメッセージのスクリーンショット。]

従来のユーザーごとの **有効/強制 Microsoft Entra 多要素認証** 設定を構成し、エラーが表示された場合は、ユーザーごとの MFA 設定を削除することで問題を解決できます。 詳細については、「[ユーザーごとのMicrosoft Entra 多要素認証イベントを有効にしてサインイン イベントをセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-userstates)」に関する記事を参照してください。

Windows Hello for Business がオプションでない場合は、Microsoft Azure Windows 仮想マシン サインイン アプリを除外する条件付きAccess ポリシーを構成します。 Windows Hello for Business の詳細については、「[Windows Hello for Business の概要](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-identity-verification)を参照してください。

RDP を使用した生体認証のサポートは、Windows 10 バージョン 1809 で追加されました。 証明書信頼モデルまたはキー信頼モデルを使用するデプロイでは、RDP 中に Windows Hello for Business 認証を使用できます。

この機能に関するフィードバックを共有するか、[Microsoft Entra フィードバック フォーラム](https://feedback.azure.com/d365community/forum/22920db1-ad25-ec11-b6e6-000d3a4f0789)で問題を報告してください。

#### アプリケーションが見つかりません

Microsoft Azure Windows 仮想マシンサインイン アプリケーションが条件付きAccessに存在しない場合は、アプリケーションがテナントにあることを確認します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
3. フィルターを削除してすべてのアプリケーションを表示し、**VM** を検索します。 結果として **Microsoft Azure Windows 仮想マシンのサインイン**が表示されない場合、サービス プリンシパルがテナントにありません。

それを確認するもう 1 つの方法は、Graph PowerShell を使用することです。

1. [Graph PowerShell SDK をインストール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)します。
2. `Connect-MgGraph -Scopes "ServicePrincipalEndpoint.ReadWrite.All"`を実行し、その後に`"Application.ReadWrite.All"`を実行します。
3. Global Administrator アカウントでサインインします。
4. アクセス許可プロンプトに同意します。
5. `Get-MgServicePrincipal -ConsistencyLevel eventual -Search '"DisplayName:Azure Windows VM Sign-In"'`を実行します。
    - このコマンドで出力が行われず、PowerShell プロンプトに戻る場合は、次の Graph PowerShell コマンドを使用してサービス プリンシパルを作成できます。

        `New-MgServicePrincipal -AppId 372140e0-b3b7-4226-8ef9-d57986796201`
    - 正常な出力は、Microsoft Azure Windows 仮想マシン サインイン アプリとその ID が作成されたことを示しています。
6. `Disconnect-MgGraph` コマンドを使用して Graph PowerShell からサインアウトします。

一部のテナントでは、Microsoft Azure Windows 仮想マシン サインインの代わりに、Azure Windows VM サインインという名前のアプリケーションが表示される場合があります。 アプリケーションのアプリケーション ID は 372140e0-b3b7-4226-8ef9-d57986796201 と同じです。

#### Azure Windows VM のサインイン リソースに準拠したデバイスの条件付きアクセス ポリシーが適用されている場合、Windows Server デバイスからの接続ではこの機能は使用できません。

条件付きAccess ポリシーの Windows Server デバイス コンプライアンス構成はサポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/hybrid-join-control"} -->
## Microsoft Entra ハイブリッド参加の対象となるデプロイ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-control
- Service: entra-id / devices
- Article date: 2024-06-27
- Summary: Microsoft Entra ハイブリッド参加を対象とするデプロイを、組織全体で一度に有効にする前に実行する方法を確認します。

対象指定デプロイを使用することで、ハイブリッド Microsoft Entra 参加済みデバイスの[計画と前提条件](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)を検証してから、組織全体でそれを有効にすることができます。 この記事では、Microsoft Entra ハイブリッド参加の対象指定デプロイを実行する方法について説明します。

注意事項

Active Directory の値を変更する場合は注意が必要です。 確立された環境に変更を加えた場合、意図しない結果になる可能性があります。

### Windows デバイスでの Microsoft Entra ハイブリッドジョインの選択的な展開

Windows 10 を実行するデバイスの場合、Hybrid Join の実行がサポートされる最小バージョンは Windows 10 (バージョン 1607) です。 ベスト プラクティスとして、Windows 10 または 11 の最新バージョンにアップグレードしてください。

Windows デバイスで Microsoft Entra ハイブリッド参加のターゲット展開を行うには、次の操作を行う必要があります。

1. Windows Server Active Directory からサービス接続ポイント (SCP) エントリが存在する場合はクリアします。
2. グループ ポリシー オブジェクト (GPO) を使用して、ドメインに参加しているコンピューターで SCP のクライアント側レジストリ設定を構成します。
3. Active Directory フェデレーション サービス (AD FS) を使用している場合は、GPO を 使用して AD FS サーバー上の SCP のクライアント側レジストリ設定も構成する必要があります。
4. デバイスの同期を有効にするには、Microsoft Entra Connect の [同期オプションをカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-post-installation#additional-tasks-available-in-azure-ad-connect) する必要がある場合があります。

ヒント

SCP は、特定の状況でデバイスのレジストリでローカルに構成される場合があります。 デバイスがその構成を使用するレジストリ内の値を見つけた場合、それ以外の場合は SCP のディレクトリに対してクエリを実行し、ハイブリッド結合を試みます。

#### Microsoft Windows Server Active Directory から SCP をクリアする

Active Directory サービス インターフェイス エディター (ADSI 編集) を使用して、Microsoft Windows Server Active Directory の SCP オブジェクトを変更します。

1. **ADSI Edit** デスクトップ アプリケーションは、エンタープライズ管理者として、管理ワークステーションまたはドメイン コントローラーから起動します。
2. ドメインの**構成名前付けコンテキスト**に接続します。
3. **CN=Configuration,DC=contoso,DC=com**&gt;**CN=Services**&gt;**CN=Device Registration Configuration** を参照します。
4. リーフ オブジェクト **CN=62a0ff2e-97b9-4513-943f-0d221bd30080** を右クリックし、**[プロパティ]**を選択します。
    1. **[属性エディター]** ウィンドウから**キーワード**を選択し、**[編集]** を選択します。
    2. **azureADId** と **azureADName** の値を選択し (一度に 1 つずつ)、**[削除]** を選択します。
5. **[ADSI エディター]** を閉じます。

#### SCP のクライアント側レジストリ設定を構成する

次の例を使用して、デバイスのレジストリ内の SCP エントリを構成するレジストリ設定をデプロイするためのグループ ポリシー オブジェクト (GPO) を作成します。

1. グループ ポリシー管理コンソールを開き、ドメインに新しいグループ ポリシー オブジェクトを作成します。
    1. 新しく作成した GPO に名前 (ClientSideSCP など) を付けます。
2. GPO を編集して、次のパスを参照します。**[コンピューターの構成]**&gt;**[基本設定]**&gt;**[Windows 設定]**&gt;**[レジストリ]**。
3. [レジストリ] を右クリックして、**[新規]**&gt;**[レジストリ項目]**を選択します。
    1. **[全般]**タブで、次を構成します。
        1. アクション: **更新**。
        2. ハイブ: **HKEY\_LOCAL\_MACHINE**。
        3. キーのパス: **SOFTWARE\Microsoft\Windows\CurrentVersion\CDJ\AAD**。
        4. 値の名前: **TenantId**。
        5. 値の種類: **REG\_SZ**。
        6. 値データ: Microsoft Entra テナントのグローバル一意識別子 (GUID) または **テナント ID** です。**[Entra ID]**&gt;**[概要]**&gt;**[プロパティ]**&gt;**[テナント ID]** で確認できます。
    2. **[OK] を選択**.
4. [レジストリ] を右クリックして、**[新規]**&gt;**[レジストリ項目]**を選択します。
    1. **[全般]**タブで、次を構成します。
        1. アクション: **更新**。
        2. ハイブ: **HKEY\_LOCAL\_MACHINE**。
        3. キーのパス: **SOFTWARE\Microsoft\Windows\CurrentVersion\CDJ\AAD**。
        4. 値の名前: **TenantName**。
        5. 値の種類: **REG\_SZ**。
        6. 値のデータ: AD FS などのフェデレーション環境を使用している場合は、確認済みの**ドメイン名**。 マネージド環境を使用している場合は、確認済みの**ドメイン名**または onmicrosoft.com のドメイン名 (たとえば、`contoso.onmicrosoft.com`)。
    2. **[OK] を選択**.
5. 新しく作成された GPO のエディターを閉じます。
6. 新しく作成した GPO を、制御されたロールアウトの対象となるドメイン参加済みコンピューターを含む適切な組織単位 (OU) にリンクします。

#### AD FS 設定を構成する

Microsoft Entra ID で AD FS を使用している場合は、まず、上記の手順を使用して、GPO を AD FS サーバーにリンクすることで、クライアント側の SCP を構成する必要があります。 SCP オブジェクトで、デバイス オブジェクトの権限のソースを定義します。 オンプレミスまたは Microsoft Entra ID を指定できます。 クライアント側 SCP が AD FS 用に構成されている場合、デバイス オブジェクトのソースは Microsoft Entra ID として確立されます。

注

AD FS サーバー上でクライアント側の SCP を構成できなかった場合、デバイス ID のソースはオンプレミスと見なされます。 Active Directory フェデレーション サービス (AD FS) は、ADFS デバイスの登録の属性 「MaximumInactiveDays」 で定義された期間が経過した後、オンプレミスのディレクトリからデバイス オブジェクトの削除を開始します。 Active Directory フェデレーション サービス (AD FS)デバイスの登録オブジェクトは、[Get-AdfsDeviceRegistration コマンドレット](https://learn.microsoft.com/ja-jp/powershell/module/adfs/get-adfsdeviceregistration)を使用して見つけることができます。

### デバイスが保留中の状態になる理由

オンプレミスデバイスの Microsoft Entra Connect 同期で **Microsoft Entra ハイブリッド参加**タスクを構成すると、タスクはデバイス オブジェクトを Microsoft Entra ID に同期し、デバイスがデバイスの登録を完了する前に、デバイスの登録済み状態を一時的に「保留中」に設定します。 この保留中状態は、デバイスを登録する前に、Microsoft Entra ディレクトリにデバイスを追加する必要があるためです。 デバイス登録プロセスの詳細については、 「[動作のしくみ：デバイス登録](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-registration-how-it-works#hybrid-azure-ad-joined-in-managed-environments)」を参照してください。

### 検証完了

すべてが期待どおりに動作することを確認したら、残りの Windows デバイスを Microsoft Entra ID に自動的に登録できます。 [Microsoft Entra Connect を使用して SCP を構成して](https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join)、Microsoft Entra ハイブリッド参加を自動化します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/hybrid-join-manual"} -->
## Microsoft Entra ハイブリッド参加の手動構成 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-manual
- Service: entra-id / devices
- Article date: 2025-06-27
- Summary: Microsoft Entra ハイブリッド参加デバイスを手動で構成する方法をご確認ください。

Microsoft Entra Connect の使用がオプションの場合は、「[Microsoft Entra ハイブリッド参加を構成する](https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join)」のガイダンスを参照してください。 Microsoft Entra Connect で自動化を使用すると、Microsoft Entra ハイブリッド参加の構成が大幅に簡略化されます。

この記事では、マネージドおよびフェデレーション ドメインのための手順を含め、Microsoft Entra ハイブリッド参加の要件の手動構成について説明します。

### 前提条件

- [Microsoft Entra Connect](https://www.microsoft.com/download/details.aspx?id=47594)
    - デバイス登録構成の過程でデバイス登録の同期参加を成功させるには、Microsoft Entra Connect 同期構成から既定のデバイスの属性を除外しないでください。 Microsoft Entra ID と同期される既定のデバイス属性について詳しくは、[Microsoft Entra Connect によって同期される属性](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-sync-attributes-synchronized#windows-10)に関するセクションを参照してください。
    - Microsoft Entra ハイブリッド参加にしたいデバイスのコンピューター オブジェクトが、特定の組織単位 (OU) に属している場合は、Microsoft Entra Connect で同期する正しい OU を構成します。 Microsoft Entra Connect を使用してコンピューター オブジェクトを同期する方法の詳細については、「[組織単位ベースのフィルタリング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-configure-filtering#organizational-unitbased-filtering)」を参照してください。
- オンプレミスの Active Directory Domain Services フォレストごとの、エンタープライズ管理者の資格情報。
- (**フェデレーション ドメインの場合**) Active Directory フェデレーション サービス (AD FS) がインストールされた Windows Server。
- ユーザーは、Microsoft Entra ID を使用してデバイスを登録できます。 この設定の詳細情報については、**デバイス設定の構成**に関する記事の「[デバイス設定の構成](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-device-identities#configure-device-settings)」の見出しの下にあります。

Microsoft Entra ハイブリッドの参加では、デバイスが組織のネットワーク内から次の Microsoft リソースにアクセスできる必要があります。

- `https://enterpriseregistration.windows.net`
- `https://login.microsoftonline.com`
- `https://device.login.microsoftonline.com`
- `https://autologon.microsoftazuread-sso.com` (シームレス シングル サインオンを使用しているか、使用する予定の場合)
- 組織のセキュリティ トークン サービス (STS) (**フェデレーション ドメインの場合**)

警告

データ損失防止や Microsoft Entra テナントの制限などのシナリオで SSL トラフィックを傍受するプロキシ サーバーを組織で使用している場合、それらの URL へのトラフィックが TLS の中断と検査から除外されていることを確認してください。 これらの URL を除外しないと、クライアント証明書の認証に干渉し、デバイス登録とデバイスベースの条件付きアクセスに問題が発生する可能性があります。

組織がアウトバウンド プロキシ経由でのインターネットへのアクセスを必要とする場合は、Windows 10 以降のコンピューターを Microsoft Entra ID にデバイス登録できるように、[Web プロキシ自動発見 (WPAD)](https://learn.microsoft.com/ja-jp/previous-versions/tn-archive/cc995261%28v=technet.10%29) を使用することができます。 WPAD の構成と管理の問題に対処するには、「[自動検出のトラブルシューティング](https://learn.microsoft.com/ja-jp/previous-versions/tn-archive/cc302643%28v=technet.10%29)」を参照してください。

WPAD を使用しない場合は、Windows 10 1709 以降のコンピューター上で WinHTTP プロキシ設定を構成できます。 詳細については、[グループ ポリシー オブジェクト (GPO) によってデプロイされる WinHTTP プロキシ設定](https://learn.microsoft.com/ja-jp/archive/blogs/netgeeks/winhttp-proxy-settings-deployed-by-gpo)に関する記事を参照してください。

注

WinHTTP 設定を使用して自分のコンピューター上でプロキシ設定を構成すると、構成されたプロキシに接続できないコンピューターは、インターネットに接続できなくなります。

組織が認証されたアウトバウンド プロキシ経由でのインターネットへのアクセスを必要とする場合、お使いの Windows 10 以降のコンピューターがアウトバウンド プロキシに対して正常に認証されることを確認してください。 Windows 10 以降のコンピューターではマシン コンテキストを使用してデバイス登録が実行されるため、マシン コンテキストを使用してアウトバウンド プロキシ認証を構成します。 構成要件については、送信プロキシ プロバイダーに確認してください。

[デバイス登録接続のテスト](https://learn.microsoft.com/ja-jp/samples/azure-samples/testdeviceregconnectivity/testdeviceregconnectivity/)のスクリプトを使用して、デバイスがシステム アカウントで必要な Microsoft リソースにアクセスできることを確認します。

### 構成

Microsoft Entra ハイブリッド参加済みデバイスは、さまざまな種類の Windows デバイス プラットフォームで構成できます。

- マネージドおよびフェデレーション ドメインの場合は、サービス接続ポイント (SCP) を構成する必要があります。
- フェデレーション ドメインの場合、適切な要求を発行するようにフェデレーション サービスが構成されていることを確認する必要があります。

これらの構成が完了したら、ガイダンスに従って[登録を確認します](https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join-verify)。

#### サービス接続ポイントの構成

デバイスでは、登録中に Microsoft Entra テナント情報を検出するために、サービス接続ポイント (SCP) オブジェクトを使用します。 オンプレミスの Active Directory インスタンスでは、Microsoft Entra ハイブリッド参加済みデバイスの SCP オブジェクトが、コンピューターのフォレストの構成名前付けコンテキストのパーティションに存在する必要があります。 各フォレストには、構成名前付けコンテキストが 1 つだけあります。 複数フォレストの Active Directory 構成では、ドメイン参加済みコンピューターが含まれているすべてのフォレストにサービス接続ポイントが存在している必要があります。

SCP オブジェクトには、2 つのキーワード値 `azureADid:<TenantID>` と `azureADName:<verified domain>` が含まれています。 `<verified domain>` キーワードの `azureADName` 値は、オンプレミスの Active Directory インスタンスから SCP 値を読み取った後にデバイスが従うデバイス登録フローの種類 (フェデレーションまたはマネージド) を指定します。 マネージド フローとフェデレーション フローの詳細については、[Microsoft Entra デバイスの登録のしくみ](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-registration-how-it-works)に関する記事を参照してください。

フォレストの構成名前付けコンテキストを取得するには、[**Get-ADRootDSE**](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2008-R2-and-2008/ee617246%28v=technet.10%29) コマンドレットを使用することができます。

Active Directory ドメイン名が *fabrikam.com* であるフォレストの場合、構成名前付けコンテキストは次のようになります。

`CN=Configuration,DC=fabrikam,DC=com`

フォレストで、ドメイン参加済みデバイスの自動登録の SCP オブジェクトは、次の場所にあります。

`CN=62a0ff2e-97b9-4513-943f-0d221bd30080,CN=Device Registration Configuration,CN=Services,[Your Configuration Naming Context]`

Microsoft Entra Connect のデプロイ方法によっては、SCP オブジェクトが既に構成されている場合があります。 次の PowerShell スクリプトを使って、オブジェクトの存在を確認し、検出値を取得することができます。

```powershell
$scp = New-Object System.DirectoryServices.DirectoryEntry;

$scp.Path = "LDAP://CN=62a0ff2e-97b9-4513-943f-0d221bd30080,CN=Device Registration Configuration,CN=Services,CN=Configuration,DC=fabrikam,DC=com";

$scp.Keywords;
```

**$scp.Keywords** の出力は、Microsoft Entra テナント情報を示します。 次に例を示します。

```powershell
azureADName:microsoft.com
azureADId:a0a0a0a0-bbbb-cccc-dddd-e1e1e1e1e1e1
```

#### 要求の発行の設定

Microsoft Entra のフェデレーション構成では、Microsoft Entra ID に対するデバイスの認証に AD FS または Microsoft パートナーが提供するオンプレミスのフェデレーション サーバーが使用されます。 デバイスは、Microsoft Entra Device Registration Service (Azure DRS) に登録するためのアクセス トークンを取得するために、認証を行います。

Windows デバイスは、オンプレミスのフェデレーション サービスによってホストされているアクティブな WS-Trust エンドポイント (1.3 または 2005 バージョン) に対する統合 Windows 認証を使用して認証します。

AD FS を使用している場合は、次の WS-Trust エンドポイントを有効にする必要があります。

- `/adfs/services/trust/2005/windowstransport`
- `/adfs/services/trust/13/windowstransport`
- `/adfs/services/trust/2005/usernamemixed`
- `/adfs/services/trust/13/usernamemixed`
- `/adfs/services/trust/2005/certificatemixed`
- `/adfs/services/trust/13/certificatemixed`

警告

**adfs/services/trust/2005/windowstransport** と **adfs/services/trust/13/windowstransport** はどちらも、イントラネットに接続するエンドポイントとしてのみ有効にする必要があります。Web アプリケーション プロキシを介してエクストラネットに接続するエンドポイントとしては公開しないでください。 WS-Trust WIndows エンドポイントを無効にする方法の詳細については、[プロキシの WS-Trust Windows エンドポイントを無効にする方法](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/deployment/best-practices-securing-ad-fs#disable-ws-trust-windows-endpoints-on-the-proxy-ie-from-extranet)に関するセクションを参照してください。 どのエンドポイントが有効になっているかは、AD FS 管理コンソールの **[サービス]**&gt;**[エンドポイント]** で確認できます。

注

オンプレミス フェデレーション サービスとして AD FS を使用していない場合は、ベンダーの指示に従って、WS-Trust 1.3 または 2005 のエンドポイントがサポートされていることと、それらが Metadata Exchange ファイル (MEX) を通じて公開されていることを確認してください。

デバイス登録を完了するには、Azure DRS で受信されるトークンに次の要求が存在する必要があります。 Azure DRS は、この情報の一部を使用して Microsoft Entra ID にデバイス オブジェクトを作成します。 その後、この情報は、新たに作成されたデバイス オブジェクトをオンプレミスのコンピューター アカウントに関連付けるために、Microsoft Entra Connect によって使用されます。

- `http://schemas.microsoft.com/ws/2012/01/accounttype`
- `http://schemas.microsoft.com/identity/claims/onpremobjectguid`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/primarysid`

複数の検証済みドメイン名が必要な場合は、コンピューターに次の要求を提供する必要があります。

- `http://schemas.microsoft.com/ws/2008/06/identity/claims/issuerid`

既に ImmutableID 要求 (たとえば、ImmutableID のソース値として `mS-DS-ConsistencyGuid` または別の属性を使用して) を発行している場合は、コンピューターに対応する要求を 1 つ提供する必要があります。

- `http://schemas.microsoft.com/LiveID/Federation/2008/05/ImmutableID`

以降のセクションでは、以下のものについて説明します。

- 各要求に必要な値。
- AD FS で定義がどのように表示されるか。

定義によって、値が存在しているかどうかや、値を作成する必要があるかどうかを確認することができます。

注

オンプレミスのフェデレーション サーバーに AD FS を使用しない場合は、ベンダーの指示に従って、これらの要求を発行するための適切な構成を作成してください。

##### アカウントの種類の要求を発行する

`http://schemas.microsoft.com/ws/2012/01/accounttype` 要求には、**DJ** という値が含まれている必要があります。この値により、デバイスがドメイン参加済みコンピューターと識別されます。 AD FS では、次のような発行変換規則を追加できます。

```
@RuleName = "Issue account type for domain-joined computers"
c:[
   Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid",
   Value =~ "-515$",
   Issuer =~ "^(AD AUTHORITY|SELF AUTHORITY|LOCAL AUTHORITY)$"
]
=> issue(
   Type = "http://schemas.microsoft.com/ws/2012/01/accounttype",
   Value = "DJ"
);
```

##### オンプレミスのコンピューター アカウントの objectGUID を発行する

`http://schemas.microsoft.com/identity/claims/onpremobjectguid` 要求には、オンプレミスのコンピューター アカウントの **objectGUID** 値が含まれている必要があります。 AD FS では、次のような発行変換規則を追加できます。

```
@RuleName = "Issue object GUID for domain-joined computers"
c1:[
   Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid",
   Value =~ "-515$", 
   Issuer =~ "^(AD AUTHORITY|SELF AUTHORITY|LOCAL AUTHORITY)$"
]
&&
c2:[
   Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/windowsaccountname",
   Issuer =~ "^(AD AUTHORITY|SELF AUTHORITY|LOCAL AUTHORITY)$"
]
=> issue(
   store = "Active Directory",
   types = ("http://schemas.microsoft.com/identity/claims/onpremobjectguid"),
   query = ";objectguid;{0}",
   param = c2.Value
);
```

##### オンプレミスのコンピューター アカウントの objectSid を発行する

`http://schemas.microsoft.com/ws/2008/06/identity/claims/primarysid` 要求には、オンプレミスのコンピューター アカウントの **objectSid** 値が含まれている必要があります。 AD FS では、次のような発行変換規則を追加できます。

```
@RuleName = "Issue objectSID for domain-joined computers"
c1:[
   Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid",
   Value =~ "-515$",
   Issuer =~ "^(AD AUTHORITY|SELF AUTHORITY|LOCAL AUTHORITY)$"
]
&&
c2:[
   Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/primarysid",
   Issuer =~ "^(AD AUTHORITY|SELF AUTHORITY|LOCAL AUTHORITY)$"
]
=> issue(claim = c2);
```

##### Microsoft Entra ID に複数の検証済みドメイン名があるときにコンピューターの issuerID を発行する

`http://schemas.microsoft.com/ws/2008/06/identity/claims/issuerid` 要求には、トークンを発行しているオンプレミスのフェデレーション サービス (AD FS またはパートナー) と接続するいずれかの検証済みドメイン名の Uniform Resource Identifier (URI) が含まれている必要があります。 AD FS では、次のような発行変換規則を、上記の規則の後に特定の順序で追加できます。 ユーザーに対する規則を明示的に発行する、1 つの規則が必要です。 次の規則では、コンピューターの認証ではなく、ユーザーを識別する最初の規則が追加されます。

```
@RuleName = "Issue account type with the value User when its not a computer"
NOT EXISTS(
[
   Type == "http://schemas.microsoft.com/ws/2012/01/accounttype",
   Value == "DJ"
]
)
=> add(
   Type = "http://schemas.microsoft.com/ws/2012/01/accounttype",
   Value = "User"
);

@RuleName = "Capture UPN when AccountType is User and issue the IssuerID"
c1:[
   Type == "http://schemas.xmlsoap.org/claims/UPN"
]
&&
c2:[
   Type == "http://schemas.microsoft.com/ws/2012/01/accounttype",
   Value == "User"
]
=> issue(
   Type = "http://schemas.microsoft.com/ws/2008/06/identity/claims/issuerid",
   Value = regexreplace(
   c1.Value,
   ".+@(?<domain>.+)",
   "http://${domain}/adfs/services/trust/"
   )
);

@RuleName = "Issue issuerID for domain-joined computers"
c:[
   Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid",
   Value =~ "-515$",
   Issuer =~ "^(AD AUTHORITY|SELF AUTHORITY|LOCAL AUTHORITY)$"
]
=> issue(
   Type = "http://schemas.microsoft.com/ws/2008/06/identity/claims/issuerid",
   Value = "http://<verified-domain-name>/adfs/services/trust/"
);
```

上記の要求にある `<verified-domain-name>` はプレースホルダーです。 これを、Microsoft Entra ID の検証済みドメイン名のいずれかに置き換えます。 たとえば、 `Value = "http://contoso.com/adfs/services/trust/"`を使用します。

検証済みドメイン名の詳細については、「[Microsoft Entra ID へのカスタム ドメイン名の追加](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)」を参照してください。

確認済みの会社のドメインの一覧を取得するには、[Get-MgDomain](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdomain) コマンドレットを使用できます。

[Image: 会社のドメインの一覧]

##### ユーザーの ImmutableID が存在する場合は、コンピューターの ImmutableID を発行します (たとえば、mS-DS-ConsistencyGuid を ImmutableID のソースとして使用します)

`http://schemas.microsoft.com/LiveID/Federation/2008/05/ImmutableID` 要求には、コンピューターに有効な値が含まれている必要があります。 AD FS では、次のような発行変換規則を作成することができます。

```
@RuleName = "Issue ImmutableID for computers"
c1:[
   Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid",
   Value =~ "-515$",
   Issuer =~ "^(AD AUTHORITY|SELF AUTHORITY|LOCAL AUTHORITY)$"
]
&&
c2:[
   Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/windowsaccountname",
   Issuer =~ "^(AD AUTHORITY|SELF AUTHORITY|LOCAL AUTHORITY)$"
]
=> issue(
   store = "Active Directory",
   types = ("http://schemas.microsoft.com/LiveID/Federation/2008/05/ImmutableID"),
   query = ";objectguid;{0}",
   param = c2.Value
);
```

##### AD FS 発行変換規則を作成するヘルパー スクリプト

次のスクリプトは、前に説明した発行変換規則の作成に役立ちます。

```
$multipleVerifiedDomainNames = $false
$immutableIDAlreadyIssuedforUsers = $false
$oneOfVerifiedDomainNames = 'example.com'   # Replace example.com with one of your verified domains

$rule1 = '@RuleName = "Issue account type for domain-joined computers"
c:[
   Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid",
   Value =~ "-515$",
   Issuer =~ "^(AD AUTHORITY|SELF AUTHORITY|LOCAL AUTHORITY)$"
]
=> issue(
   Type = "http://schemas.microsoft.com/ws/2012/01/accounttype",
   Value = "DJ"
);'

$rule2 = '@RuleName = "Issue object GUID for domain-joined computers"
c1:[
   Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid",
   Value =~ "-515$",
   Issuer =~ "^(AD AUTHORITY|SELF AUTHORITY|LOCAL AUTHORITY)$"
]
&&
c2:[
   Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/windowsaccountname",
   Issuer =~ "^(AD AUTHORITY|SELF AUTHORITY|LOCAL AUTHORITY)$"
]
=> issue(
   store = "Active Directory",
   types = ("http://schemas.microsoft.com/identity/claims/onpremobjectguid"),
   query = ";objectguid;{0}",
   param = c2.Value
);'

$rule3 = '@RuleName = "Issue objectSID for domain-joined computers"
c1:[
   Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid",
   Value =~ "-515$",
   Issuer =~ "^(AD AUTHORITY|SELF AUTHORITY|LOCAL AUTHORITY)$"
]
&&
c2:[
   Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/primarysid",
   Issuer =~ "^(AD AUTHORITY|SELF AUTHORITY|LOCAL AUTHORITY)$"
]
=> issue(claim = c2);'

$rule4 = ''
if ($multipleVerifiedDomainNames -eq $true) {
$rule4 = '@RuleName = "Issue account type with the value User when it is not a computer"
NOT EXISTS(
[
   Type == "http://schemas.microsoft.com/ws/2012/01/accounttype",
   Value == "DJ"
]
)
=> add(
   Type = "http://schemas.microsoft.com/ws/2012/01/accounttype",
   Value = "User"
);

@RuleName = "Capture UPN when AccountType is User and issue the IssuerID"
c1:[
   Type == "http://schemas.xmlsoap.org/claims/UPN"
]
&&
c2:[
   Type == "http://schemas.microsoft.com/ws/2012/01/accounttype",
   Value == "User"
]
=> issue(
   Type = "http://schemas.microsoft.com/ws/2008/06/identity/claims/issuerid",
   Value = regexreplace(
   c1.Value,
   ".+@(?<domain>.+)",
   "http://${domain}/adfs/services/trust/"
   )
);

@RuleName = "Issue issuerID for domain-joined computers"
c:[
   Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid",
   Value =~ "-515$",
   Issuer =~ "^(AD AUTHORITY|SELF AUTHORITY|LOCAL AUTHORITY)$"
]
=> issue(
   Type = "http://schemas.microsoft.com/ws/2008/06/identity/claims/issuerid",
   Value = "http://' + $oneOfVerifiedDomainNames + '/adfs/services/trust/"
);'
}

$rule5 = ''
if ($immutableIDAlreadyIssuedforUsers -eq $true) {
$rule5 = '@RuleName = "Issue ImmutableID for computers"
c1:[
   Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid",
   Value =~ "-515$",
   Issuer =~ "^(AD AUTHORITY|SELF AUTHORITY|LOCAL AUTHORITY)$"
]
&&
c2:[
   Type == "http://schemas.microsoft.com/ws/2008/06/identity/claims/windowsaccountname",
   Issuer =~ "^(AD AUTHORITY|SELF AUTHORITY|LOCAL AUTHORITY)$"
]
=> issue(
   store = "Active Directory",
   types = ("http://schemas.microsoft.com/LiveID/Federation/2008/05/ImmutableID"),
   query = ";objectguid;{0}",
   param = c2.Value
);'
}

$existingRules = (Get-ADFSRelyingPartyTrust -Identifier urn:federation:MicrosoftOnline).IssuanceTransformRules

$updatedRules = $existingRules + $rule1 + $rule2 + $rule3 + $rule4 + $rule5

$crSet = New-ADFSClaimRuleSet -ClaimRule $updatedRules

Set-AdfsRelyingPartyTrust -TargetIdentifier urn:federation:MicrosoftOnline -IssuanceTransformRules $crSet.ClaimRulesString
```

##### 解説

- このスクリプトは、既存の規則に規則を追加します。 このスクリプトは 2 回実行しないでください。規則のセットが 2 回追加されてしまいます。 スクリプトを再実行する前に、これらの要求に対応する規則が (対応する条件の下に) ないことを確認してください。
- 複数の検証済みドメイン名がある場合は、スクリプトで **$multipleVerifiedDomainNames** の値を **$true** に設定します。 また、Microsoft Entra Connect やその他の方法で作成された既存の **issuerid** 要求を必ず削除します。 この規則の例を次に示します。

    ```
    c:[Type == "http://schemas.xmlsoap.org/claims/UPN"]
    => issue(Type = "http://schemas.microsoft.com/ws/2008/06/identity/claims/issuerid", Value = regexreplace(c.Value, ".+@(?<domain>.+)",  "http://${domain}/adfs/services/trust/")); 
    ```

ユーザー アカウントに対して **ImmutableID** 要求を発行する場合は、スクリプト内の **$immutableIDAlreadyIssuedforUsers** の値を **$true**に設定します。

### 実装のトラブルシューティング

ドメイン参加済み Windows デバイスの Microsoft Entra ハイブリッド参加を行うときに問題が発生した場合は、以下を参照してください。

- [dsregcmd コマンドを使用したデバイスのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-device-dsregcmd)
- [Microsoft Entra ハイブリッド参加済みデバイスのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-hybrid-join-windows-current)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/hybrid-join-plan"} -->
## Microsoft Entra ハイブリッド結合の展開を計画する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan
- Service: entra-id / devices
- Article date: 2025-06-27
- Summary: 環境内で Microsoft Entra ハイブリッド参加済みデバイスを実装するために必要な手順について説明します。

オンプレミスの Active Directory Domain Services (AD DS) 環境があり、AD DS ドメインに参加しているコンピューターを Microsoft Entra ID に参加させる場合は、Microsoft Entra ハイブリッド参加を実行してこのタスクを実行できます。

ヒント

オンプレミスのリソースへのシングル サインオン (SSO) アクセスは、Microsoft Entra に参加しているデバイスでも使用できます。 詳しくは、「[Microsoft Entra 参加済みデバイス上でオンプレミス リソースへの SSO が機能するしくみ](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-sso-to-on-premises-resources)」をご覧ください。

### [前提条件]

この記事では、 [Microsoft Entra ID でのデバイス ID 管理の概要について](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)理解していることを前提としています。

注

Windows 10 以降の Microsoft Entra ハイブリッド参加に必要な最小ドメイン コントローラー (DC) バージョンは、Windows Server 2008 R2 です。

Microsoft Entra ハイブリッド参加済みデバイスには、ドメイン コントローラーへの定期的なネットワーク通信経路が必要です。 この接続がない場合、デバイスは使用できなくなります。

ドメイン コントローラーが見えない状態で中断するシナリオを次に示します。

- デバイスのパスワードの変更
- ユーザー パスワードの変更 (キャッシュされた資格情報)
- トラステッド プラットフォーム モジュール (TPM) のリセット

### 実装の計画

ハイブリッド Microsoft Entra の実装を計画するには、次の点を理解してください。

- サポートされているデバイスを確認する
- 知っておくべきことを確認する
- Microsoft Entra ハイブリッド参加の対象となる展開を確認する
- ID インフラストラクチャに基づいてシナリオを選択する
- Microsoft Entra のハイブリッド参加のために、オンプレミスの Microsoft Windows Server Active Directory のユーザー プリンシパル名 (UPN) がサポートされているか確認する

### サポートされているデバイスを確認する

Microsoft Entra ハイブリッド参加は、幅広い Windows デバイスをサポートしています。

- ウィンドウズ11
- ウィンドウズ10
- Windows Server 2016
    - **手記：** Azure National クラウドのお客様にはバージョン 1803 が必要です
- Windows Server 2019
- Windows Server 2022

ベスト プラクティスとして、最新バージョンの Windows にアップグレードすることをお勧めします。

### 知っておくべきことを確認する

#### サポートされていないシナリオ

- ドメイン コントローラー (DC) ロールを実行している Windows Server では、Microsoft Entra ハイブリッド参加はサポートされていません。
- Server Core OS では、どの種類のデバイス登録もサポートされていません。
- ユーザー状態移行ツール (USMT) は、デバイスの登録では機能しません。

#### OS イメージングに関する考慮事項

- システム準備ツール (Sysprep) に依存していて、 **Windows 10 1809 より前** のイメージをインストールに使用している場合は、Microsoft Entra ハイブリッド参加済みとして Microsoft Entra ID に既に登録されているデバイスのイメージではないことを確認してください。
- 仮想マシン (VM) スナップショットに依存して VM をさらに作成する場合は、Microsoft Entra ハイブリッド参加済みとして Microsoft Entra ID に既に登録されている VM からのスナップショットではないことを確認してください。
- 再起動時にディスクの変更をクリアする [統合書き込みフィルター](https://learn.microsoft.com/ja-jp/windows/iot/iot-enterprise/customize/unified-write-filter) と同様のテクノロジを使用している場合は、デバイスが Microsoft Entra ハイブリッドに参加した後に適用する必要があります。 Microsoft Entra ハイブリッド結合が完了する前にこのようなテクノロジを有効にすると、毎回の再起動時にデバイスが結合から外れてしまいます。

#### Microsoft Entra 登録済み状態のデバイスの処理

Windows 10 以降のドメイン参加済みデバイスが [Microsoft Entra](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-device-registration) にテナントに登録されている場合、Microsoft Entra ハイブリッド参加済みデバイスと Microsoft Entra 登録済みデバイスのデュアル状態になる可能性があります。 このシナリオに自動的に対処するには、Windows 10 1803 (KB4489894が適用された状態) 以降にアップグレードすることをお勧めします。 1803 より前のリリースでは、Microsoft Entra ハイブリッド参加を有効にする前に、Microsoft Entra 登録済み状態を手動で削除する必要があります。 1803 以降のリリースでは、このデュアル状態を回避するために次の変更が行われました。

- ユーザーの既存の Microsoft Entra 登録済み状態は、 *デバイスが Microsoft Entra ハイブリッドに参加し、同じユーザーがログインした後に自動的に*削除されます。 たとえば、ユーザー A がデバイスに Microsoft Entra 登録済み状態を保持していた場合、ユーザー A がデバイスにログインしたときにのみ、ユーザー A の二重状態はクリーンアップされます。 同じデバイスに複数のユーザーが存在する場合、それらのユーザーがサインインすると、デュアル状態が個別にクリーンアップされます。 管理者が Microsoft Entra 登録済み状態を削除すると、登録が自動登録による Microsoft Entra 登録の一部として行われる場合、Windows 10 は Intune またはその他のモバイル デバイス管理 (MDM) からデバイスの登録を解除します。
- デバイス上のローカル アカウントの Microsoft Entra 登録済み状態は、この変更の影響を受けません。 ドメイン アカウントにのみ適用されます。 ローカル アカウントの Microsoft Entra 登録済み状態は、ユーザーがドメイン ユーザーではないので、ユーザーログオン後も自動的に削除されません。
- HKLM\SOFTWARE\Policies\Microsoft\Windows\WorkplaceJoin: "BlockAADWorkplaceJoin"=dword:00000001 にレジストリ値を追加することで、ドメインに参加しているデバイスが Microsoft Entra に登録されないようにすることができます。
- Windows 10 1803 では、Windows Hello for Business が構成されている場合、ユーザーはデュアル状態のクリーンアップ後に Windows Hello for Business を再構成する必要があります。 この問題は、KB4512509で解決されます。

注

Windows 10 と Windows 11 は Microsoft Entra 登録済み状態をローカルで自動的に削除しますが、Intune で管理されている場合、Microsoft Entra ID のデバイス オブジェクトはすぐには削除されません。 `dsregcmd /status`を実行して、Microsoft Entra 登録済み状態の削除を検証できます。

#### 単一フォレスト、複数 Microsoft Entra テナントに対する Microsoft Entra ハイブリッド参加

デバイスを Microsoft Entra ハイブリッド参加として各テナントに登録するには、組織は、Microsoft Windows Server Active Directory ではなく、デバイスでサービス接続ポイント (SCP) 構成が行われるようにする必要があります。 このタスクを実行する方法の詳細については、 [Microsoft Entra ハイブリッド参加の対象展開に関](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-control)する記事を参照してください。 組織は、特定の Microsoft Entra 機能が 1 つのフォレスト(複数の Microsoft Entra テナント構成)では機能しないことを理解することが重要です。

- [デバイス書き戻し](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-device-writeback)は機能しません。 この構成は、 [AD FS を使用してフェデレーションされているオンプレミス アプリのデバイス ベースの条件付きアクセスに影響します](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configure-device-based-conditional-access-on-premises)。 この構成は、 [ハイブリッド証明書信頼モデルを使用する場合の Windows Hello for Business の展開](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-hybrid-cert-trust)にも影響します。
- [グループの書き戻し](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-group-writeback-v2)は機能しない。 この構成は、Exchange がインストールされているフォレストへの Office 365 グループの書き戻しに影響します。
- [シームレス SSO](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso) は機能しません。 この構成は、Windows 10 拡張機能のない Firefox、Safari、Chrome で iOS や Linux などのブラウザー プラットフォームを使用している組織の SSO シナリオに影響します。
- [オンプレミスの Microsoft Entra Password Protection](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-on-premises) は機能しません。 この構成は、Microsoft Entra ID に格納されているのと同じグローバルおよびカスタム禁止パスワード リストを使用して、オンプレミスの Active Directory Domain Services (AD DS) ドメイン コントローラーに対してパスワード変更とパスワード リセット イベントを実行する機能に影響します。

#### その他の考慮事項

- 環境で仮想デスクトップ インフラストラクチャ (VDI) を使用している場合は、「 [デバイス ID とデスクトップ仮想化」を](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-device-identity-virtual-desktop-infrastructure)参照してください。
- Microsoft Entra ハイブリッド参加は、Federal Information Processing Standard (FIPS) 準拠の TPM 2.0 でサポートされており、TPM 1.2 ではサポートされていません。 デバイスに FIPS 準拠の TPM 1.2 がある場合は、Microsoft Entra ハイブリッド参加に進む前に無効にする必要があります。 TPM の FIPS モードを無効にするためのツールは、TPM の製造元に依存するため、Microsoft では提供していません。 サポートが必要な場合は、お使いのハードウェアの OEM にお問い合わせください。
- Windows 10 1903 リリース以降、TPM バージョン 1.2 は Microsoft Entra ハイブリッド参加では使用されず、これらの TPM を持つデバイスは TPM を持たないかのように扱われます。
- UPN の変更は、Windows 10 2004 Update 以降でのみサポートされます。 Windows 10 2004 更新プログラムより前のデバイスの場合、ユーザーはデバイスで SSO と条件付きアクセスの問題を抱えている可能性があります。 この問題を解決するには、Microsoft Entra ID からデバイスの参加を解除し (管理者特権で "dsregcmd/leave" を実行)、再参加 (自動的に行われます) する必要があります。 ただし、Windows Hello for Business でサインインするユーザーは、この問題に直面しません。

### 対象となる Microsoft Entra ハイブリッド参加を確認する

組織は、全社的に有効化する前に、Microsoft Entra ハイブリッド参加を計画的に段階的に展開することを検討することをお勧めします。 [Microsoft Entra ハイブリッド参加の対象となる展開に関](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-control)する記事を確認して、それを実現する方法を理解してください。

Warnung

組織には、パイロット グループ内のさまざまなロールやプロファイルからのユーザーのサンプルを含める必要があります。 対象を絞ってロールアウトすると、組織全体に対して有効にする前に、計画で対処されていない可能性のある問題を特定するのに役立ちます。

### ID インフラストラクチャに基づいてシナリオを選択する

Microsoft Entra ハイブリッド参加は、UPN がルーティング可能であるかルーティング不可能であるかに応じて、マネージド環境とフェデレーション環境の両方で機能します。 サポートされているシナリオの表については、ページの下部を参照してください。

#### マネージド環境

マネージド環境は、 [パスワード ハッシュ同期 (PHS)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs) または [パススルー認証 (PTA) を使用](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta) して [シームレス シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso)を使用してデプロイできます。

これらのシナリオでは、認証 (AuthN) 用にフェデレーション サーバーを構成する必要はありません。

注

[段階的ロールアウトを使用したクラウド認証](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout) は、Windows 10 1903 更新プログラム以降でのみサポートされます。

#### フェデレーション環境

フェデレーション環境には、以下の要件をサポートする ID プロバイダーが必要です。 Active Directory フェデレーション サービス (AD FS) を使用しているフェデレーション環境がある場合は、以下の要件は既にサポートされています。

**WS-Trust プロトコル:** このプロトコルは、Microsoft Entra ID を使用して Microsoft Entra ハイブリッド参加済み Windows デバイスを認証するために必要です。 AD FS を使用している場合は、次の WS-Trust エンドポイントを有効にする必要があります。

`/adfs/services/trust/2005/windowstransport``/adfs/services/trust/13/windowstransport``/adfs/services/trust/2005/usernamemixed``/adfs/services/trust/13/usernamemixed``/adfs/services/trust/2005/certificatemixed``/adfs/services/trust/13/certificatemixed`

Warnung

**adfs/services/trust/2005/windowstransport** または **adfs/services/trust/13/windowstransport** はどちらもイントラネットに接続するエンドポイントとしてのみ有効にする必要があり、Web アプリケーション プロキシ経由でエクストラネットに接続するエンドポイントとして公開しないでください。 WS-Trust WIndows エンドポイントを無効にする方法の詳細については、[プロキシの WS-Trust Windows エンドポイントを無効にする方法](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/deployment/best-practices-securing-ad-fs#disable-ws-trust-windows-endpoints-on-the-proxy-ie-from-extranet)に関するセクションを参照してください。 どのエンドポイントが有効になっているかは、AD FS 管理コンソールの **[サービス]**&gt;**[エンドポイント]** で確認できます。

バージョン 1.1.819.0 以降、Microsoft Entra Connect では、Microsoft Entra ハイブリッド参加を構成するためのウィザードが提供されます。 ウィザードを使用すると、構成プロセスを大幅に簡略化できます。 必要なバージョンの Microsoft Entra Connect をインストールするオプションがない場合は、「 [デバイスの登録を手動で構成する方法](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-manual)」を参照してください。 contoso.com が確認済みのカスタム ドメインとして登録されている場合、同期されたオンプレミス AD DS UPN サフィックスが test.contoso.com のようなサブドメイン内にある場合でも、ユーザーは PRT を取得できます。

### オンプレミスの Microsoft Windows Server Active Directory ユーザーの Microsoft Entra ハイブリッド参加の UPN サポートを確認する

- ルーティング可能なユーザー UPN: ルーティング可能な UPN には、ドメイン レジストラーに登録されている有効な検証済みドメインがあります。 たとえば、contoso.com が Microsoft Entra ID のプライマリ ドメインである場合、contoso.org は Contoso が所有し、 [Microsoft Entra ID で検証された](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)オンプレミス AD のプライマリ ドメインです。
- ルーティング不可能なユーザー UPN: ルーティング不可能な UPN には検証済みのドメインがないため、組織のプライベート ネットワーク内でのみ適用されます。 たとえば、contoso.com が Microsoft Entra ID のプライマリ ドメインであり、contoso.local がオンプレミス AD のプライマリ ドメインである場合、インターネットでは検証可能なドメインではなく、Contoso のネットワーク内でのみ使用されます。

注

このセクションの情報は、オンプレミス ユーザー UPN にのみ適用されます。 オンプレミスのコンピューター ドメイン サフィックス (例: computer1.contoso.local) には適用されません。

次の表は、Windows 10 Microsoft Entra ハイブリッド参加におけるこれらのオンプレミスの Microsoft Windows Server Active Directory UPN のサポートの詳細を示しています。

| オンプレミスの Microsoft Windows Server Active Directory UPN の種類 | ドメインの種類 | Windows 10 のバージョン | 説明 |
| --- | --- | --- | --- |
| ルーティング可能 | フェデレーション | 1703 リリースから | 一般公開 |
| ルーティング不可 | フェデレーション | 1803 リリースから | 一般公開 |
| ルーティング可能 | マネージド | 1803 リリースから | 一般提供されている Windows ロック画面の Microsoft Entra SSPR は、オンプレミスの UPN が Microsoft Entra UPN と異なる環境ではサポートされていません。 オンプレミスの UPN を Microsoft Entra ID の `onPremisesUserPrincipalName` 属性に同期する必要があります |
| ルーティング不可 | マネージド | サポートされていません |  |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/macos-get-started"} -->
## 職場または学校アカウントを macOS デバイスに追加する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/macos-get-started
- Service: entra-id / devices
- Article date: 2026-01-22
- Summary: 組織のリソース、強化されたセキュリティ、デバイス管理にシームレスにアクセスするために、職場または学校アカウントを macOS デバイスに追加する方法について説明します。

macOS への職場または学校アカウントの追加は、組織のリソースとサービスへのアクセスを強化する簡単なプロセスです。 この記事では、Microsoft Edge や Google Chrome などのブラウザーを使用して macOS デバイスに職場または学校アカウントを追加する方法についてよく寄せられる質問 (FAQ) の概要と回答について説明します。

Important

この機能は、ブラウザーベースのシナリオでのみ機能します。 Microsoft Edge や Google Chrome などのブラウザーを使用して組織のリソースにアクセスすると、macOS デバイスに職場または学校アカウントを追加するように求められます。 これはスタンドアロン デスクトップ アプリケーションには適用されません。

### 概要

ブラウザーを使用して職場または学校アカウントをデバイスに追加すると、Microsoft Entra アカウント登録ページと呼ばれるページが表示されます。 ページの内容は、組織がデバイス管理ソリューションを実装したかどうかによって異なります。

- 標準フローでは、Microsoft Edge や Google Chrome などのブラウザーを使用して職場または学校アカウントを追加します。 このプロセスでは、組織アカウントの資格情報を入力し、多要素認証を完了する場合があります [Image: macOS デバイスでのサインインのスクリーンショット。]
- 組織がデバイス管理ソリューションを使用している場合、このプロセスには、デバイスが会社のポリシーに準拠していることを確認するための追加の手順が含まれます。 このセットアップにより、デバイスが暗号化やデータ保護などのセキュリティ要件を満たし、IT 管理者がデバイスをリモートで管理およびセキュリティで保護できるようになります。

### よく寄せられる質問

#### Microsoft Entra アカウント登録ページとは

ブラウザーを使用して保護されたリソースにアクセスしようとすると、Microsoft Entra アカウントの登録ページが表示されます。 このページでは、デバイスにアカウントを追加するかどうかを決定します。 Microsoft Edge や Google Chrome などのブラウザーが保護されたリソースにアクセスしようとすると、Microsoft Entra アカウント登録ページが表示され、デバイスにアカウントを追加するよう要求されます。 デバイスにアカウントを追加すると、ブラウザーベースのすべてのアプリにシームレスにサインインできます。 また、より多くのセキュリティ機能も提供します。

#### Microsoft Entra アカウント登録ページで [はい] を選択すると、何が行われますか?

サインインに職場または学校アカウントを使用するブラウザーを介してアクセスする Web アプリケーションからアカウントにアクセスできるようになります。 多くの場合、これらのアプリケーションでは、操作を必要とせずに職場または学校アカウントが自動的に使用されるため、資格情報にアクセスするときに資格情報を入力する必要はありません。

このデバイスは、組織のディレクトリにも登録されます。 このデバイスが登録されると、デバイスに関する基本情報が組織内の他のユーザーに公開されます。

注

職場または学校アカウントを利用するバックグラウンド アクティビティは、多くの場合、さまざまなサービスを提供するためにデバイス上で発生します。 このアクティビティは、アプリケーション、サービス、スケジュールされたタスクから発生する可能性があります。

#### 自分のアカウントをデバイスに追加すると、組織はどのような情報を確認できますか?

デバイスに関する次の情報は、組織のディレクトリに公開されます。 組織内の他のユーザーには、次の情報が表示される場合があります。

- デバイスの名前
- デバイスが有効になっている場合
- オペレーティング システムとバージョン番号
- 結合の種類。 例: Microsoft Entra 登録済み
- デバイス所有者の名前
- デバイス管理の構成
- セキュリティ設定の管理
- デバイス コンプライアンスの状態
- デバイス登録日
- 最後のアクティブ

#### デバイス管理とは

デバイス管理ソリューションを使用すると、組織の IT 部門が個人または会社のデバイスでセキュリティ設定とアプリケーションを管理できます。 組織では、最新の状態で、必要なセキュリティ ポリシーを使用して構成されたデバイスのみがアプリやリソースにアクセスできるようにする方法です。

#### デバイス管理に登録した場合、管理者はどうすればよいですか?

デバイス管理に登録すると、管理者は次のようなさまざまな操作を実行できます。

- デバイスにアプリケーションをインストールする
- 特定のオペレーティング システムへのアクセスを制限する
- ソフトウェアの展開と更新
- デバイス設定を構成する
- セキュリティ ポリシーを適用する
- 個人デバイスのブロック
- 紛失または盗難にあったデバイスからデータを削除する
- デバイス上のデータを保護する

#### Microsoft Entra アカウントの登録ページが表示される理由

ブラウザーを使用してサインインするときに、すべての組織アカウントにユーザー アカウントをデバイスに追加するように求められます。 このソリューションにより、利便性とセキュリティが向上します。 このページでは、デバイスにアカウントを追加するかどうかを決定します。 管理者がデバイス管理を必要とする場合は、このページでもデバイス管理に登録するかどうかを決定できます。

#### デバイス上のブラウザーを使用してサインインする利点は何ですか?

組織のアカウントを使用してブラウザーを介してアクセスする Web アプリケーションに自動的にサインインします。 組織のポリシーに準拠しており、より多くのセキュリティ機能を利用できます。

#### ここでの選択は、サインインしている他のデバイスに適用されますか?

いいえ。Microsoft Entra アカウント登録ページでの選択は、このデバイスにのみ適用されます。

#### デスクトップ アプリと Web サイトの違いは何ですか?

- デスクトップ アプリは、コンピューター上で直接アクセスし、コンピューター上でローカルに実行されるソフトウェア プログラムです。
- Web サイトは、ブラウザー経由でアクセスするページの組み合わせであり、クラウドとブラウザーの両方で実行されます。 Web サイトには、複数のページ間を移動するのに役立つリンクがあります。

### 管理者リファレンス

[条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) は、リソースを保護するために管理者によって使用されます。

[Windows Web アカウント マネージャー](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-wam)認証ブローカーと連携するように構成されたアプリケーションでは、SSO やその[他のセキュリティ機能](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-token-protection)が提供されます。

Microsoft Entra のすべてのお客様は、アプリとオペレーティング システムでサポートされている場合は、Web アカウント マネージャーを使用してサインインするように求められます。

[Microsoft Intune を使用したデバイス管理](https://learn.microsoft.com/ja-jp/intune/intune-service/fundamentals/what-is-device-management)の詳細について説明します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/macos-manage-preferences"} -->
## macOS の Microsoft シングル サインオンの基本設定を管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/macos-manage-preferences
- Service: entra-id / devices
- Article date: 2026-06-30
- Summary: この記事は、macOS アプリの Microsoft Single Sign-On 内の基本設定と、各設定が何を行うかをユーザーが理解するのに役立ちます

Microsoft Single Sign-on for macOS におけるシングル サインオンとアプリ内データ収集の設定を選択してください。 ユーザー設定にアクセスするには:

1. Microsoft シングル サインオン for macOS アプリを開く。
2. メニュー バーに移動し、[詳細設定] を選択 **します**。

### 単一サインイン

シングル サインオン (SSO) では、職場または学校アカウントが構成され、クラウドベースのすべての仕事用アプリとサービスにアクセスするために 1 回だけ認証する必要があります。 基本設定には、次のものが含まれます。

- **デバイスの登録**: デバイスを登録して SSO を有効にし、保護されたリソースにアクセスします。 この設定は、プラットフォーム SSO が有効になっているデバイスでのみ使用できます。
- **登録解除**: デバイスの登録を削除し、SSO を無効にします。 このデバイスで保護されたリソースに再度アクセスするには、再登録する必要があります。 この設定は、プラットフォーム SSO が有効になっているデバイスでのみ使用できます。
- **[このデバイスからアカウントを削除**する]: 職場または学校のアカウントと、デバイスから SSO 認証トークンを削除します。

Mac で SSO をオプトアウトするには、[ **このデバイスのシングル サインオンでサインインするように求めない**] の横にあるチェック ボックスをオンにします。

### 使用状況データを Microsoft に送信する

この設定により、Microsoft はIntune ポータル サイト使用状況に関するデータを収集できます。 このチェック ボックスをオンにすると、アプリ内のパフォーマンスと使用状況データが自動的に匿名化され、Microsoft と共有され、製品の信頼性とパフォーマンスが向上します。 お客様の組織には、このデータの収集を管理する権限がないため、設定を変更することはできません。

ポータル サイトでデータ収集をオフにするには、[**Microsoft による使用状況データの収集を許可**する] の横にあるチェック ボックスをオフにします。

### 高度なログ記録

トラブルシューティングに使用する詳細ログを有効にするには、Microsoft Single Sign-on for macOS および MSAL の **Turn on advanced logging** の横にあるチェック ボックスをオンにします。 macOS 用 Microsoft シングル サインオンは、高度なログ記録が有効になっている場合に、証明書の使用状況とネットワーク応答を記録します。 既定では、高度なログ記録はオフになっています。 organizationの IT 管理者から特に指示がない限り、この設定はオフのままにします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/macos-psso"} -->
## macOS Platform Single Sign-on (PSSO) の概要 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/macos-psso
- Service: entra-id / devices
- Article date: 2025-06-12
- Summary: Microsoft Entra ID 登録済みデバイスの macOS プラットフォーム シングル サインオン (PSSO) の概要。

macOS プラットフォーム シングル サインオン (PSSO) は、ユーザーが Microsoft Entra ID 資格情報を使用して Mac デバイスにサインインできるようにする、Microsoft の Enterprise SSO プラグイン、macOS のプラットフォーム資格情報を利用する新機能です。 この機能では、ユーザーのサインイン プロセスを簡略化し、覚えておく必要があるパスワードの数を減らすことで、管理者に利点を提供します。 また、ユーザーはスマート カードまたはハードウェア バインド キーを使用して Microsoft Entra ID で認証することもできます。 この機能では、2 つの個別のパスワードを覚えておく必要がなくなることで、エンド ユーザー エクスペリエンスが向上し、管理者はローカル アカウントのパスワードを管理する必要がなくなります。

エンド ユーザー エクスペリエンスを決定する 3 つの異なる認証方法があります。

- **macOS のプラットフォーム資格情報**: 認証に Microsoft Entra ID を使用するアプリ間での SSO に使用される、セキュア エンクレーブでサポートされたハードウェア バインド暗号化キーをプロビジョニングします。 ユーザーのローカル アカウントのパスワードは影響を受けず、Mac にサインインする必要があります。
- **スマート カード**: ユーザーは、外部スマート カード、またはスマート カードと互換性のあるハード トークン (Yubikey など) を使用してマシンにサインインします。 デバイスのロックが解除されると、スマート カードが Microsoft Entra ID と共に使用され、認証に Microsoft Entra ID を使うアプリ間での SSO が許可されます。
- **認証方法としてのパスワード**: ユーザーの Microsoft Entra ID パスワードをローカル アカウントと同期し、認証に Microsoft Entra ID を使用するアプリ間での SSO を有効にします。

Apple デバイスで Microsoft Enterprise SSO プラグインを活用する PSSO では、

- ユーザーが Touch ID を使用して、パスワードレスを実現できるようにします。
- Windows Hello for Business テクノロジに基づいて、フィッシングに対する耐性のある資格情報を使用します。
- セキュリティ キーの必要性をなくすことで、顧客組織のコストを節約します。
- セキュア エンクレーブとの統合を使用して、ゼロ トラスト目標を進めます。

これを有効にするには、管理者が Microsoft Intune またはその他のサポートされている MDM を使用して PSSO を構成する必要があります。 デバイスの構成方法に応じて、エンド ユーザーは、セキュリティで保護されたエンクレーブ、スマート カード、またはパスワード ベースの認証方法を使用して、PSSO を使用してデバイスを設定できます。

### 要件

macOS のプラットフォーム SSO をデプロイするには、次の最小要件を満たす必要があります。

- macOS 14 Sonoma の推奨される最小バージョン。 macOS 13 Ventura はサポートされていますが、最適なエクスペリエンスを得るために macOS 14 Sonoma の使用を強くお勧めします。
- [Microsoft Authenticator](https://support.microsoft.com/account-billing/how-to-use-the-microsoft-authenticator-app-9783c865-0308-42fb-a519-8cf666fe0acc)
- Microsoft Intune [ポータル サイト アプリ](https://learn.microsoft.com/ja-jp/mem/intune/apps/apps-company-portal-macos) バージョン 5.2404.0 以降がインストールされている。 このバージョンは、ユーザーが PSSO の対象となる前に必要になります。
- ユーザーは、 [デバイスを登録して Microsoft Entra ID に参加するための](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-macos-platform-single-sign-on-extension?tabs=macOS14#insufficient-permissions)十分なアクセス許可を持っている必要があります。

### 構成

設定方法の詳細情報と手順については、次の記事をご覧ください。

- [Microsoft Intune で macOS デバイスのプラットフォーム SSO を構成する](https://learn.microsoft.com/ja-jp/mem/intune/configuration/platform-sso-macos)

注

サード パーティの MDM を使用して macOS デバイスのプラットフォーム SSO を構成する場合は、プラットフォーム SSO の構成方法に関する具体的な手順については、MDM ベンダーが提供するドキュメントを参照してください。

サード パーティの MDM ソリューションの開発者の場合は、PSSO を MDM ソリューションに統合する方法の詳細については、「 [MACOS Platform Single Sign On (PSSO) を MDM ソリューション](https://learn.microsoft.com/ja-jp/entra/identity/devices/macos-psso-integration-guide) に統合する」ガイドを参照してください。

### 導入

macOS のプラットフォーム SSO をデプロイする方法の詳細と手順については、以下の記事を参照してください。

- [初期設定の間に Mac デバイスを Microsoft Entra ID に接続する](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-macos-platform-single-sign-on)
- [ポータル サイトを使用して Mac デバイスを Microsoft Entra ID に参加させる](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-microsoft-entra-company-portal)

### パスワードレスの認証

パスワードは、悪意のあるアクターの主要な攻撃ベクトルです。 ソーシャル エンジニアリング、フィッシング、スプレー攻撃を使用してパスワードを侵害します。 パスワードレス認証戦略により、これらの攻撃のリスクを軽減できます。

macOS のプラットフォーム SSO を使用して、組織のパスワードレス認証を有効にする方法について学習します。

- [Microsoft Entra ID のパスキー (FIDO2) 認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)
- [Microsoft Entra ID でのパスワードレス認証のデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-deployment)

macOS 用のプラットフォーム資格情報は、WebAuthn チャレンジ (ブラウザーの再認証シナリオを含む) で使用するためのフィッシング耐性資格情報としても使用できます。 FIDO ポリシーでキー制限を使用する場合は、許可されている AAGUID の一覧に macOS プラットフォーム資格情報の AAGUID を追加する必要があります。 `7FD635B3-2EF9-4542-8D9D-164F2C771EFC`

#### Microsoft Platform SSO: UserSecureEnclaveKeyBiometricPolicy（ユーザーセキュアエンクレーブキー生体認証ポリシー）

Microsoft Platform SSO では、 [UserSecureEnclaveKey 認証](https://developer.apple.com/documentation/authenticationservices/asauthorizationproviderextensionloginconfiguration/usersecureenclavekeybiometricpolicy) 方法でプラットフォーム SSO を使用する場合、UserSecureEnclaveKeyBiometricPolicy オプションがサポートされます。 このポリシーは、ユーザー セキュリティで保護されたエンクレーブ キーにアクセスする必要がある場合は常に、ユーザーに Touch ID による認証を要求することで、セキュリティを強化します。

- このポリシーが有効になっている場合、ユーザーセキュリティで保護されたエンクレーブ キーにアクセスするたびに、ユーザーは Touch ID 認証を求められます。 PSSO の登録、ユーザー キーをパスキーとして使用するブラウザーの再認証シナリオ、および PSSO トークンを取得するためのサインイン時に認証を求めるメッセージが表示されます。
- このポリシーを有効にするには、デバイスが Touch ID 生体認証をサポートしている必要があります。 ユーザーは、PSSO 登録を続行するために Touch ID を構成する必要があります。 管理者は、このポリシーを有効にする前に、ユーザーに生体認証がサポートされているデバイスまたは Touch ID をサポートする外部キーボードがあることを確認する必要があります。

注

UserSecureEnclaveKeyBiometricPolicy が有効になっている場合、ユーザー セキュリティで保護されたエンクレーブ キーを使用した認証中にパスワード フォールバックを行うオプションはありません。 そのため、ユーザーが Touch ID 生体認証を利用できない場合、ユーザーは Microsoft Entra ID に対して認証を行うことはできません。

##### UserSecureEnclaveKeyBiometricPolicy の要件

- オペレーティング システム: macOS 14.6 以降
- ポータル サイトのバージョン: 2504 以降

    Von Bedeutung

    PSSO 登録が完了した後にこの機能を有効にした場合、ポリシーを有効にするには、すべてのユーザーが完全な PSSO 再登録プロセスを受ける必要があります。 ユーザーに再登録プロンプトが表示されないため、この再登録プロセスは管理者主導である必要があります。 管理者は、このポリシーを有効にするかどうかを慎重に検討し、それに応じて PSSO の展開を計画する必要があります。

##### UserSecureEnclaveKeyBiometricPolicy を有効にする方法

高セキュリティのお客様は、SSO 拡張機能のデータ ディクショナリにフラグを設定することで、この機能を有効にすることを選択できます。

- キー名: enable\_se\_key\_biometric\_policy
- 値: true

[Image: Microsoft Intune の UserSecureEnclaveKeyBiometricPolicy 構成のスクリーンショット。]

##### UserSecureEnclaveKeyBiometricPolicy の利点

- セキュリティ強化: ユーザー セキュア エンクレーブ キー アクセスはハードウェアで保護されており、タッチ ID 認証が成功した後にのみアクセスできるため、セキュリティが強化されます。

##### UserSecureEnclaveKeyBiometricPolicy の欠点

- その他のプロンプト: プロセス中にキーに複数回アクセスされるため、PSSO 登録中に追加のプロンプトが表示されます。
- Biometric-Only アクセス: PSSO パスキーには生体認証でのみアクセスできます。 パスワードフォールバックはありません。 デバイスがパスワードでロック解除されている場合でも、ユーザーは PSSO トークンを取得するための生体認証を求められます。

### オンプレミスの Active Directory および Microsoft Entra ID Kerberos リソースに対する Kerberos SSO

macOS を使用すると、ユーザーは、Microsoft Entra ID への SSO に加えて、オンプレミスおよびクラウド リソースに対する Kerberos ベースの SSO をサポートするようにプラットフォーム SSO を構成できます。 Kerberos SSO はプラットフォーム SSO 内のオプションの機能ですが、ユーザーが認証に Kerberos を使用するオンプレミスの Active Directory リソースに引き続きアクセスする必要がある場合にお勧めします。

詳細については、 [オンプレミスの Active Directory および Microsoft Entra ID Kerberos リソースに対する Kerberos SSO](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-macos-platform-single-sign-on-kerberos-configuration) に関するページを参照してください。

### Graph API のサポート

Microsoft Graph API を使用して、PlatformCredential 認証方法を管理できます。

次の API を使用できます。

- [platformCredentialAuthenticationMethod リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/platformcredentialauthenticationmethod?preserveview=graph-rest-1.0)。
- [platformCredentialAuthenticationMethods を一覧表示します](https://learn.microsoft.com/ja-jp/graph/api/platformcredentialauthenticationmethod-list?preserveview=graph-rest-1.0)。
- [platformCredentialAuthenticationMethod を削除します](https://learn.microsoft.com/ja-jp/graph/api/platformcredentialauthenticationmethod-delete?preserveview=graph-rest-1.0)。

### 米国標準技術局 (NIST)

米国国立標準技術研究所 (NIST) は、米国商務省内の非規制連邦機関です。 NIST では、連邦機関の情報および情報システムを保護するためのコスト効率の高いプログラムの管理を支援するために、標準、ガイドライン、およびその他の出版物を策定して発行しています。

macOS プラットフォーム SSO を使用して NIST の要件を満たす方法の詳細については、以下の記事を参照してください。

- [NIST認証保証レベルを満たすようMicrosoft Entra IDを構成します](https://learn.microsoft.com/ja-jp/entra/standards/nist-overview)。
- [NIST 認証子の種類と対応する Microsoft Entra のメソッド](https://learn.microsoft.com/ja-jp/entra/standards/nist-authenticator-types)。
- [Microsoft Entra ID を使用した NIST Authenticator Assurance レベル 3](https://learn.microsoft.com/ja-jp/entra/standards/nist-authenticator-assurance-level-3)

### EnableRegistrationDuringSetup を使用するプラットフォームのシングルサインオン (PSSO)

EnableRegistrationDuringSetup を使用したプラットフォーム シングル Sign-On (PSSO) を使用すると、macOS デバイスは自動デバイス登録 (ADE) 中に PSSO 登録を自動的に完了できます。

この機能を有効にするには、管理者は、Microsoft Intuneまたはその他のサポートされている MDM を使用して PSSO を構成する必要があります。 デバイスの構成方法に応じて、エンド ユーザーは、セキュリティで保護されたエンクレーブ、スマート カード、またはパスワード ベースの認証方法を使用して、PSSO を使用してデバイスを設定できます。

先進認証と ポータル サイト SSO 拡張機能を使用してセットアップ アシスタントと組み合わせると、Intune は登録フローの早い段階で ID ブートストラップとデバイスの登録を完了できます。プロンプトが減り、デバイスがデスクトップに到達するとすぐに使用できるようになります。

Intune で EnableRegistrationDuringSetup を使用して PSSO を設定する手順については、[macOS デバイスの自動デバイス登録中にプラットフォーム シングル Sign-On (PSSO) を構成](https://learn.microsoft.com/ja-jp/mem/intune/device-configuration/settings-catalog/configure-platform-sso-during-enrollment)する方法に関するドキュメントを参照してください

注

セットアップ アシスタントでのスマート カードによる認証はサポートされていません。 認証方法としてスマート カードを使用する場合は、セットアップ アシスタントの完了後に PSSO 登録を完了する必要があります。

### トラブルシューティング

macOS プラットフォーム SSO の実装時に問題が発生した場合は、[macOS プラットフォームのシングル サインオンの既知の問題とトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-macos-platform-single-sign-on-extension)に関するドキュメント 参照してください
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/macos-psso-deployment-eocustomers"} -->
## NIST SP 800-63 および EO 14028 の要件を満たすように macOS Platform SSO (PSSO) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/macos-psso-deployment-eocustomers
- Service: entra-id / devices
- Article date: 2025-05-01
- Summary: nIST SP 800-63 および EO 14028 の要件を満たすために macOS Platform Single Sign On (PSSO) を展開する方法。

このドキュメントでは、米国政府機関による macOS Platform Single Sign-On (PSSO) の展開に関する包括的なガイダンスを提供し、米国国立標準技術研究所 (NIST) 特別出版物 (SP) 800-63 リビジョン 4 およびエグゼクティブ オーダー (EO) 14028 の要件を満たします。 このドキュメントで説明されている手順とベスト プラクティスに従うことで、組織は macOS ユーザーにシームレスで安全な SSO エクスペリエンスを確保できます。

### [前提条件]

- macOS 13 Ventura の最小バージョン (macOS 14 Sonoma 以降を推奨)
- ユーザーは、Entra ID でサポートされている MFA メソッドのいずれかを使用して、登録中に多要素認証を実行できる必要があります。
    - [Microsoft Entra MFA](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)
    - [フェデレーション MFA](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configure-ad-fs-and-azure-mfa)
    - [外部認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-external-method-manage)
- [Microsoft Intune ポータル サイト アプリ バージョン 5.2408.0](https://learn.microsoft.com/ja-jp/mem/intune/apps/apps-company-portal-macos) 以降がインストールされています。 このバージョンは、ユーザーが PSSO を対象とする前に必要です。
- (強くお勧めします)ユーザーは、[モバイル デバイスにパスキーを登録](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-register-passkey)することをお勧めします。

### 認証方法の選択

米国政府機関は、デバイスにデプロイされたプラットフォーム SSO 構成でフィッシングに強い認証方法を使用する必要があります。

macOS 14 Sonoma 以降では、Apple Platform SSO フレームワークで次の 2 つのフィッシングに耐性のある方法が提供されています。

1. [セキュリティで保護されたエンクレーブ](https://learn.microsoft.com/ja-jp/mem/intune/configuration/platform-sso-macos#secure-enclave) (推奨)。
2. [スマート カード](https://learn.microsoft.com/ja-jp/mem/intune/configuration/platform-sso-macos#smart-card)。

使用可能な認証方法とプラットフォーム SSO の比較の詳細については、「 [認証方法の決定](https://learn.microsoft.com/ja-jp/mem/intune/configuration/platform-sso-macos#step-1---decide-the-authentication-method)」を参照してください。

### Microsoft Intune の構成

[Microsoft Intune での macOS デバイスのプラットフォーム SSO の構成に関するページを](https://learn.microsoft.com/ja-jp/mem/intune/configuration/platform-sso-macos)参照してください。

### その他のMDMの構成

macOS のプラットフォーム SSO のサポートとプロビジョニングについては、MDM プロバイダーのドキュメントを参照してください。

### Microsoft Authentication Library (MSAL) を使用しないアプリケーションの SSO を有効にする

[MSAL を使用しないアプリの SSO の有効化に関する](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin)ページを参照してください。

### Kerberos SSO 統合の構成

[Platform SSO でオンプレミスの Active Directory および Microsoft Entra ID Kerberos リソースに対する Kerberos SSO を有効にする方法](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-macos-platform-single-sign-on-kerberos-configuration)に関するページを参照してください。

### アカウント管理

#### アカウントの作成と準備

Platform SSO を使用するには、デバイスが MDM に登録されている必要があります。 Intune を使用する場合は、次のいずれかの方法を使用します。

- 組織所有のデバイスの場合は、次のことができます。

    - Apple Business Manager を使用して [、自動デバイス登録](https://learn.microsoft.com/ja-jp/mem/intune/enrollment/device-enrollment-program-enroll-macos) ポリシーを作成します。
    - Apple Configurator を使用して [直接登録](https://learn.microsoft.com/ja-jp/mem/intune/enrollment/device-enrollment-direct-enroll-macos) ポリシーを作成します。
- 個人所有のデバイスの場合は、 [デバイス登録](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/deployment-guide-enrollment-macos#byod-device-enrollment) ポリシーを作成します。 この登録方法では、エンド ユーザーはポータル サイト アプリを開き、Microsoft Entra ID ユーザー アカウントでサインインします。 正常にサインインすると、登録ポリシーが適用されます。

新しいデバイスの場合は、登録ポリシーを含め、必要なすべてのポリシーを事前に作成して構成することをお勧めします。 その後、デバイスがIntuneに登録されると、ポリシーが自動的に適用されます。

Intune に既に登録されている既存のデバイスの場合は、プラットフォーム SSO ポリシーをユーザーまたはユーザー グループに割り当てます。 デバイスが次回 Intune と同期またはチェックインすると、ユーザーは作成したプラットフォーム SSO ポリシー設定を受け取ります。

#### デバイス コンプライアンス ポリシーを作成する

Microsoft Intune のデバイス コンプライアンス ポリシーを使用すると、管理者は登録されたデバイスが組織のセキュリティ標準を満たし、正しく構成されていることを確認できます。 これらのポリシーは、暗号化、オペレーティング システムのバージョン、パスワード強度などのセキュリティ対策などの要件を適用することで、企業データを保護するのに役立ちます。 デバイス コンプライアンス ポリシーの構成の詳細については、「 [Intune の macOS 設定のデバイス コンプライアンス設定](https://learn.microsoft.com/ja-jp/mem/intune/protect/compliance-policy-create-mac-os)」を参照してください。

#### アカウントのプロビジョニング解除またはオフボード

##### テナントからのユーザーの削除

[ユーザーを作成、招待、削除する方法](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users)を参照してください。

##### ユーザーのアクセスを取り消す

[「Microsoft Entra ID でのユーザー アクセスの取り消し」を](https://learn.microsoft.com/ja-jp/entra/identity/users/users-revoke-access)参照してください。

##### デバイスを Intune でリモートからロックする

[「Intune を使用してデバイスをリモートでロックする」を](https://learn.microsoft.com/ja-jp/mem/intune/remote-actions/device-remote-lock)参照してください。

#### MDM の登録解除

##### 手順 1 - 構成プロファイルの割り当てから PSSO 対象ユーザーを削除する

1. Microsoft Intune 管理センターで、[ **ホーム**] をクリックします。
2. **[デバイス**]、[**構成プロファイル]** の順に移動します。
3. 編集する構成プロファイルを一覧から選択します。
4. **割り当て**の横にある**編集**を選択します。
5. 割り当てを削除するには、[削除] を選択 **します**。
6. [ **確認と保存] を** 選択して変更を完了します。

##### 手順 2 - 一括デバイス アクションを実行してポリシーの削除を同期する

1. Microsoft エンドポイント管理センターで、[ **デバイス**] を選択します。
2. [ **すべてのデバイス** ] を選択し、[ **デバイスの一括操作**] を選択します。
3. [ **デバイスの一括操作]** ページで、OS として *macOS* を選択し、デバイス アクションとして *[同期]* を選択します。
4. [ **次へ** ] を選択し、アクションがサポートするデバイスの最大数を選択し、[ **次へ**] を選択します。
5. [ **確認と作成** ] ページで、[ **作成** ] を選択してアクションを実行します。

ヒント

特定のデバイスを同期する場合は、「デバイスの同期」を参照 [して、Intune で最新のポリシーとアクションを取得](https://learn.microsoft.com/ja-jp/mem/intune/remote-actions/device-sync#sync-a-device)してください。

##### Microsoft Entra ID と Intune で古いデバイスを検出して削除する

「 [方法: Microsoft Entra ID で古いデバイスを管理](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-stale-devices) し、 [Intune 管理センターからデバイスを削除](https://learn.microsoft.com/ja-jp/mem/intune/remote-actions/devices-wipe#delete-devices-from-the-intune-admin-center)する」を参照してください。

### アプリケーションを SSO Broker と統合する

[Apple デバイスバージョン](https://github.com/AzureAD/microsoft-authentication-library-for-objc) 1.1.0 以降の MSAL では、職場および学校アカウント用の Apple デバイス用の Microsoft Enterprise SSO プラグインがネイティブでサポートされています。

[「クイック スタート: iOS または macOS アプリからユーザーをサインインして Microsoft Graph を呼び出し](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-ios-sign-in)、[既定のリダイレクト URI 形式](https://learn.microsoft.com/ja-jp/mem/intune/remote-actions/devices-wipe#delete-devices-from-the-intune-admin-center)を使用する」に従った場合は、特別な構成は必要ありません。

SSO プラグインがあるデバイスでは、すべての対話型およびサイレントなトークン要求に対して、このプラグインが MSAL によって自動的に呼び出されます。 これは、アカウント列挙およびアカウント削除作業に対しても呼び出されます。 MSAL では、お客様の操作に依存するネイティブ SSO プラグイン プロトコルが実装されているため、このセットアップにより、エンド ユーザーに最もスムーズなネイティブ エクスペリエンスが提供されます。

### ベスト プラクティス

#### スマート カード証明書のピン留め

スマート カード ベースの認証を使用する場合は、スマート カード証明書の信頼評価に使用する証明書発行元機関を指定することを強くお勧めします。 この信頼は、証明書の信頼設定と組み合わせて機能し、証明書のピン留めと呼ばれます。 詳細については、「 [Mac のスマート カードの詳細オプション」を](https://support.apple.com/guide/deployment/advanced-smart-card-options-dep7b2ede1e3/web#:%7E:text=Certificate%20pinning,%3C/plist%3E)参照してください。

##### 構成例

```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" http://www.apple.com/DTDs/PropertyList-1.0.dtd>
    <plist version="1.0">
    <dict>
        <key>AttributeMapping</key>
        <dict>
            <key>dsAttributeString</key
            <string>dsAttrTypeStandard:AltSecurityIdentities</string>
            <key>fields</key>
            <array>
                <string>NT Principal Name</string>
            </array>
            <key>formatString</key>
            <string>PlatformSSO:$1</string>
        </dict>
        <key>TrustedAuthorities</key>
        <array>
    <string>SHA256_HASH_OF_CERTDOMAIN_1,SHA256_HASH_OF_CERTDOMAIN_2</string>
        </array>
    </dict>
    </plist>
```

### トラブルシューティング

macOS Platform SSO のデプロイ時に問題が発生する場合は、 [macOS Platform Single Sign-on の既知の問題とトラブルシューティングに](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-macos-platform-single-sign-on-extension)関するドキュメントを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/macos-psso-integration-guide"} -->
## macOS Platform Single Sign-On (PSSO) を MDM ソリューションに統合する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/macos-psso-integration-guide
- Service: entra-id / devices
- Article date: 2025-07-23
- Summary: macOS Platform Single Sign-On (PSSO) を MDM ソリューションに統合する方法について説明します。

macOS デバイス用のプラットフォーム シングル Sign-On (PSSO) は、ユーザーが Microsoft Entra 資格情報を使用して macOS デバイスにサインインできるようにする機能です。 この機能は、ユーザーにシームレスなサインイン エクスペリエンスを提供し、組織が macOS デバイス上のリソースへのアクセスを管理するのに役立ちます。

このガイドでは、macOS Platform Single Sign-On (PSSO) を MDM ソリューションに統合する方法について説明します。 このガイドは、macOS デバイス用の PSSO をサポートするサード パーティ MDM ソリューションの開発者を対象としています。

### [前提条件]

作業を開始する前に、次の記事を理解することをお勧めします。

- パートナーマネージド デバイス コンプライアンス統合 API 用に Intune から提供されたすべてのドキュメント
- [Apple デバイス用の Microsoft Enterprise Single Sign-On (SSO) プラグイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin)。
- [macOS Platform シングル サインオンの概要](https://learn.microsoft.com/ja-jp/entra/identity/devices/macos-psso)。

### 必要な最小ペイロード プロパティ

[Apple デバイス用の Microsoft Enterprise Single Sign-On (SSO) プラグイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin)で使用するには、次の設定とペイロードのプロパティが必要です。 これらの設定が次の値で構成されていることを確認し、必要に応じて他の設定を追加して、アプリに適切な SSO を確保します。

| **設定** | **値** |
| --- | --- |
| 拡張機能識別子 | com.microsoft.CompanyPortalMac.ssoextension |
| チーム識別子 | UBF8T346G9 |
| 認証方法 (非推奨) [OS13 デバイスに必要] | その 1 つ...パスワード  UserSecureEnclaveKey |
| 認証方法 (OS14 以降のデバイス) | その 1 つ...パスワード  UserSecureEnclaveKey  スマートカード (macOS 14 以降) |
| 画面ロック動作 | 処理しない |
| タイプ | リダイレクト |
| URL | 次の URL を指定します `https://login.microsoftonline.com`https://login.microsoft.com`https://sts.windows.net`https://login.partner.microsoftonline.cnhttps://login.chinacloudapi.cnhttps://login.microsoftonline.ushttps://login-us.microsoftonline.com |
| 共有デバイス キーを使用する | 最適な PSSO エクスペリエンスを実現し、後で有効にした場合は不要な再登録エクスペリエンスを回避するために、[共有デバイス キーの使用] を有効にします。 |

### イベント通知

イベントの状態に応じて、さまざまな PSSO イベントの完了時に他のアクションを実行する必要がある場合があります。 次の一覧には、さまざまな PSSO イベントに関する Entra ID SSO 拡張機能によって投稿された通知が含まれています。 MVM は、これらの通知をリッスンし、適切なアクションを実行することを選択できます。

#### イベント

これらのイベントを使用して、エラーや成功事例のテレメトリまたは監視を記録することを検討してください。 デバイスとユーザーの登録が完了したら、コンプライアンス API サービスを使用してデバイスに準拠をマークする必要があります。

| 通知名 | トリガー |
| --- | --- |
| Microsoft.PlatformSSO.DeviceRegistration.Started | デバイスの登録が開始されました |
| Microsoft.PlatformSSO.DeviceRegistration.Succeeded | デバイスの登録が完了しました |
| Microsoft.PlatformSSO.DeviceRegistration.Failed | デバイスの登録に失敗しました |
| Microsoft.PlatformSSO.UserRegistration.Started | ユーザー登録が開始されました |
| Microsoft.PlatformSSO.UserRegistration.Succeeded | ユーザー登録が完了しました |
| Microsoft.PlatformSSO.UserRegistration.Failed | ユーザー登録に失敗しました |
| Microsoft.PlatformSSO.Registration.Succeeded | デバイスとユーザーの両方の登録が完了しました |
| Microsoft.PlatformSSO.Registration.Failed | デバイスまたはユーザーの登録に失敗しました |
| Microsoft.PlatformSSO.Registration.Removed | プラットフォーム SSO の登録が削除されました |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/manage-device-identities"} -->
## Microsoft Entra 管理センターを使用してMicrosoft Entra IDでデバイスを管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-device-identities
- Service: entra-id / devices
- Article date: 2026-06-17
- Summary: この記事では、Microsoft Entra 管理センターを使用してデバイスの ID を管理し、関連するイベント情報を監視する方法について説明します。

Microsoft Entra IDは、デバイス ID を管理し、関連するイベント情報を監視するための中心的な場所を提供します。

[Image: デバイスの概要を示すスクリーンショット.]

次の手順を実行して、デバイスの概要をaccessできます。

1. 少なくとも[既定のユーザーのアクセス許可](https://entra.microsoft.com)を持つユーザーとして、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions)にサインインします。
2. **Entra ID**&gt;**Devices**&gt;**Overview** に移動します。

デバイスの概要から、すべてのデバイス、古くなったデバイス、非準拠デバイス、およびアンマネージド デバイスの数を確認できます。 Intune、条件付きAccess、BitLocker キー、および基本的な監視へのリンクが提供されます。 条件付きAccessやMicrosoft Intuneなどのその他の機能には、追加のロールの割り当てが必要です

概要ページのデバイス数はリアルタイムでは更新されません。 変更は数時間ごとに反映されます。

そこから、 **[すべてのデバイス]** に移動すると、以下を実行することができます。

- 次のようなデバイスを識別する。
    - Microsoft Entra IDに参加または登録されているデバイス。
    - [Windows Autopilot](https://learn.microsoft.com/ja-jp/autopilot/windows-autopilot)経由で展開されたデバイス。
    - [ユニバーサル プリント](https://learn.microsoft.com/ja-jp/universal-print/fundamentals/universal-print-getting-started) を使用するプリンター。
- 有効化、無効化、削除、管理などのデバイス ID 管理タスクを実行する。
    - [Printers](https://learn.microsoft.com/ja-jp/universal-print/fundamentals/) および [Windows Autopilot](https://learn.microsoft.com/ja-jp/autopilot/windows-autopilot) の管理オプションは、Microsoft Entra IDでは制限されています。 これらのデバイスは、それぞれの管理インターフェイスから管理する必要があります。
- デバイス ID 設定を構成する。
- Enterprise State Roaming を有効または無効にする。
- デバイス関連の監査ログを確認する。
- デバイスをダウンロードする。

[Image: すべてのデバイスビューを示すスクリーンショット]

ヒント

- プライマリ ユーザーが Microsoft Intune で設定されていない限り、Microsoft Entra ハイブリッド参加済みWindows 10以降のデバイスには所有者がありません。 所有者でデバイスを検索しても見つからなかった場合は、デバイス ID で検索してください。
- **[Microsoft Entra ハイブリッド参加済み]** のデバイスが **[登録済み]** 列で状態が **[保留中]** として表示されている場合、そのデバイスは Microsoft Entra Connect から同期されており、クライアントからの登録の完了を待機していることを示します。 「[Microsoft Entra ハイブリッド参加の実装を計画する方法](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)」を参照してください。 詳細については、「[Device managementよく寄せられる質問](https://learn.microsoft.com/ja-jp/entra/identity/devices/faq)を参照してください。
- 一部の iOS デバイスでは、アポストロフィーを含むデバイス名に、アポストロフィーに似た別の文字が使われていることがあります。 そのため、そのようなデバイスの検索には少し注意が必要になります。 正しい検索結果が表示されない場合は、検索文字列に一致するアポストロフィ文字が含まれていることを確認してください。

### Intune デバイスの管理

Intune でデバイスを管理する権限がある場合は、モバイル device managementが **Microsoft Intune**として表示されているデバイスを管理できます。 デバイスが Microsoft Intune に登録されていない場合、**Manage** オプションは使用できません。

### Microsoft Entra デバイスを有効または無効にする

デバイスを有効または無効にするには、次の 2 つの方法があります。

- 1 つまたは複数のデバイスを選択した後の、 **[すべてのデバイス]** ページのツールバー。
- 特定のデバイスを詳細に表示した後のツールバー。

重要

- デバイスを有効または無効にするには、Intune 管理者またはクラウド デバイス管理者である必要があります。
- デバイスを無効にすると、Microsoft Entra ID経由で認証できなくなります。 これにより、デバイス ベースの条件付きAccessによって保護されている Microsoft Entra リソースにアクセスしたり、Windows Hello for Business 資格情報を使用したりできなくなります。
- デバイスを無効にすると、デバイス上のプライマリ更新トークン (PRT) とすべての更新トークンが取り消されます。
- Microsoft Entra IDでプリンターを有効または無効にすることはできません。

### Microsoft Entra デバイスを削除する

デバイスを削除する方法は 2 つあります。

- 1 つまたは複数のデバイスを選択した後の、 **[すべてのデバイス]** ページのツールバー。
- 特定のデバイスを詳細に表示した後のツールバー。

重要

- デバイスを削除するには、クラウド デバイス管理者、Intune 管理者、またはWindows 365管理者である必要があります。
- プリンターは、ユニバーサル プリントから削除される前に削除できません。
- Windows Autopilotデバイスは、Intune から削除する前に削除できません。
- デバイスを削除する場合、以下の点に注意してください。
    - Microsoft Entra リソースにアクセスできなくなります。
    - デバイスに関連付けられているすべての詳細が削除されます。 たとえば、Windows デバイスの BitLocker キーなどです。
    - これは、回復不可能なアクティビティです。 必要でない限り、それはお勧めしません。

デバイスが別の管理機関またはサービス (Microsoft Intune など) で管理されている場合は、削除する前に、デバイスがリセットされ、処分されていることを確認してください。 デバイスを削除する前に、[古くなったデバイスを管理する](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-stale-devices)方法に関するページを参照してください。

### デバイス ID を表示またはコピーする

デバイス ID を使うと、デバイス上で、または PowerShell を使用してトラブルシューティングを行う際に、デバイス ID の詳細を確認することができます。 コピーオプションにアクセスするには、デバイスを選択します。

[Image: デバイス ID とコピー ボタンを示すスクリーンショット。]

### BitLocker キーを表示またはコピーする

BitLocker キーを表示およびコピーして、暗号化されたドライブをユーザーが復旧できるようにすることができます。 これらのキーは、暗号化され、Microsoft Entra IDにキーが格納されている Windows デバイスでのみ使用できます。 デバイスの詳細を表示するときにこれらのキーを見つけられるようにするには、 **[回復キーの表示]** を選択します。 **[回復キーを表示する]** を選ぶと、監査ログ エントリが生成されます。これは、`KeyManagement` カテゴリで見つかります。

[Image: BitLocker キーを表示する方法を示すスクリーンショット。]

BitLocker キーを表示またはコピーするには、デバイスの所有者であるか、次のロールのいずれかが割り当てられている必要があります。

- [Cloud デバイス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-device-administrator)
- [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator)
- [Intune 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#intune-administrator)
- [セキュリティアドミニストレーター (Security Administrator)](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)
- [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)

注

[Windows Autopilot](https://learn.microsoft.com/ja-jp/mem/autopilot/windows-autopilot)を利用するデバイスが再利用されると、**新しいデバイス所有者**が存在する場合、そのデバイスの BitLocker 回復キーを取得するには、新しいデバイス所有者が管理者に連絡する必要があります。 カスタム ロールまたは管理単位スコープの管理者は、新しいデバイス所有者がカスタム ロールまたは管理単位スコープに属していない限り、デバイスの所有権の変更が行われるデバイスの BitLocker 回復キーに引き続きaccess。 このような場合、ユーザーは回復キーについて他のスコープ管理者に問い合わせる必要があります。 詳細については、「[Intune デバイスのプライマリ ユーザーを検索する](https://learn.microsoft.com/ja-jp/mem/intune/remote-actions/find-primary-user#change-a-devices-primary-user)」の記事を参照してください。

### デバイスの表示とフィルター処理

デバイスの一覧を次の属性でフィルター処理できます。

- 有効な状態
- 準拠状態
- 結合の種類 (Microsoft Entra 参加済み、Microsoft Entra ハイブリッド参加済み、Microsoft Entra 登録済み)
- アクティビティ タイムスタンプ
- OS の種類と OS のバージョン
    - Windows は、(KB5006738を使用して) Windows 11およびWindows 10デバイス用に表示されます。
    - [Microsoft Defender for Endpoint](https://learn.microsoft.com/ja-jp/mem/intune/protect/mde-security-integration#supported-platforms) で管理されているサポートされているバージョンには、Windows Server が表示されます。
- デバイスの種類 (プリンター、セキュリティで保護された VM、共有デバイス、登録済みデバイス)
- MDM
- オートパイロット
- 拡張属性
- 管理単位
- 所有者

### デバイスのダウンロード

クラウド デバイス管理者と Intune 管理者は、**[デバイスのダウンロード]** オプションを使用して、デバイスが一覧表示された CSV ファイルをエクスポートできます。 フィルターを適用して、一覧表示するデバイスを決定できます。 フィルターを適用しないと、すべてのデバイスが一覧表示されます。 エクスポートされた一覧には、次のデバイス ID 属性が含まれています。

`id,deviceId,isManaged,profileType,systemLabels,model,displayName,accountEnabled,operatingSystem,operatingSystemVersion,trustType(joinType),mdm,securitySettingsManagement,isCompliant,registrationDateTime,approximateLastSignInDateTime,owner,upnName`

注

trustType は、JoinType を決定できる場所です。 一般的な翻訳は次のとおりです。

- AzureAD -&gt; Microsoft Entra 参加済み
- Workplace -&gt; Microsoft Entra 登録済み
- ServerAD -&gt; Microsoft Entra ハイブリッドに参加済み

エクスポート タスクに次のフィルターを適用できます。

- 有効な状態
- 準拠状態
- 結合の種類
- アクティビティ タイムスタンプ
- OS の種類
- デバイスの種類

さらに、[ **ビューの管理] &gt; [列** ] を選択して、エクスポートする列を切り替えることで、列を管理できます。

注

**所有者**または**ユーザー プリンシパル名**を選択すると、処理に時間がかかる場合があります。 より高速な結果が必要な場合は、これらのオプションをオフのままにします。追加の情報が必要な場合は、それらを有効にします。

### デバイス設定の構成

Microsoft Entra 管理センターを使用してデバイス ID を管理する場合は、デバイスをMicrosoft Entra IDに[登録または参加](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)する必要があります。 管理者は、次のデバイスの設定を構成することによって、デバイスの登録および参加のプロセスを制御できます。

デバイス設定を読み取ったり変更したりするには、次のいずれかのロールが割り当てられている必要があります。

- [Cloud デバイス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-device-administrator) (読み取りと変更)
- [Intune Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#intune-administrator) (読み取り専用)
- [Windows 365 Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#windows-365-administrator) (読み取り専用)

ヒント

ユーザーがデバイスの登録または参加を試みたときにエラーが発生した場合は、サポートされている OS プラットフォームに対して **[、登録](https://learn.microsoft.com/ja-jp/editor/MicrosoftDocs/entra-docs-pr/docs%2Fidentity%2Fdevices%2Fmanage-device-identities.md/main/295390ca-aab1-5f76-adce-5faa90dc55b0/concept-device-registration.md)** または **[参加](https://learn.microsoft.com/ja-jp/editor/MicrosoftDocs/entra-docs-pr/docs%2Fidentity%2Fdevices%2Fmanage-device-identities.md/main/295390ca-aab1-5f76-adce-5faa90dc55b0/concept-directory-join.md)** の設定が適切に構成されていることを確認します。 異なるオペレーティング システムでは、それぞれ登録または参加がサポートされます。

[Image: Microsoft Entra ID に関連するデバイス設定を示すスクリーンショット]

- ユーザーは、デバイスを Microsoft Entra ID: この設定を使用すると、デバイスを Microsoft Entra 参加済みデバイスとして登録できるユーザーを選択できます。 既定値は **[すべて]** です。

    注

    **ユーザーは、Microsoft Entra ID設定にデバイスを参加させる場合があります**。これは、Windows 10以降、macOS、または Linux での参加Microsoft Entraにのみ適用されます。 この設定は、Microsoft Entra ハイブリッド参加済みデバイス、[Microsoft Entra 参加済み VM (Azure](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-vm-sign-in-azure-ad-windows#enable-microsoft-entra-sign-in-for-a-windows-virtual-machine-in-azure) の Microsoft Entra 参加済みデバイス、または [Windows Autopilot 自己展開モード](https://learn.microsoft.com/ja-jp/autopilot/self-deploying) を使用する Microsoft Entra 参加済みデバイスには適用されません。これらの方法はユーザーレス コンテキストで動作するためです。
- **ユーザーは、デバイスを Microsoft Entra ID** に登録できます。この設定を構成して、Windows 10以降の個人、iOS、Android、macOS デバイスをMicrosoft Entra IDに登録できるようにする必要があります。 **None** を選択した場合、デバイスはMicrosoft Entra IDに登録できません。 Microsoft 365用のMicrosoft Intuneまたはモバイルデバイス管理の登録には、登録が必要です。 これらのサービスのいずれかを構成した場合は、**[すべて]** が選択され、**[なし]** は選択できなくなります。
- **デバイスを登録または参加させる際に Microsoft Entra ID で多要素認証を必要とする**

    - 多要素認証を適用するには、条件付きアクセスで [「デバイスの登録または参加」のユーザー操作](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#user-actions) を活用することを推奨します。 Conditional Access ポリシーを使用して多要素認証を要求する場合は>このトグルを No に構成する必要があります。
    - この設定では、ユーザーがデバイスをMicrosoft Entra IDに参加または登録するために別の認証要素を指定する必要があるかどうかを指定できます。 既定値は **No** です。 デバイスを登録または参加させる場合は、多要素認証を要求することをお勧めします。 このサービスの多要素認証を有効にする前に、デバイスを登録するユーザーに対して多要素認証が構成されていることを確認する必要があります。 Microsoft Entra 多要素認証サービスの詳細については、「Microsoft Entra 多要素認証の導入」を参照してください。 この設定は、サードパーティの ID プロバイダーでは機能しない場合があります。

    注

    Microsoft Entra ID設定は、Microsoft Entra 参加済み (一部の例外あり) または Microsoft Entra 登録済みのデバイスに適用されます。 この設定は、Microsoft Entra ハイブリッド参加済みデバイス、[Microsoft Entra 参加済み VM (Azure](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-vm-sign-in-azure-ad-windows#enable-microsoft-entra-sign-in-for-a-windows-virtual-machine-in-azure) 内の Microsoft Entra 参加済み VM、または [Windows Autopilot 自己展開モード](https://learn.microsoft.com/ja-jp/autopilot/self-deploying) を使用する Microsoft Entra 参加済みデバイスには適用されません。
- **デバイスの最大数**: この設定を使用すると、ユーザーがMicrosoft Entra IDに持つ Microsoft Entra 参加済みデバイスまたは Microsoft Entra 登録済みデバイスの最大数を選択できます。 ユーザーがこの制限に達した場合、1 つ以上の既存のデバイスを削除するまでデバイスを追加できなくなります。 既定値は **50**です。 値は、最大 100 まで増やすことができます。 100 を超える値を入力すると、Microsoft Entra ID 100 に設定されます。 また、 **[無制限]** を使用すると、既存のクォータ制限以外に制限をかけないようにすることができます。

    注

    **[デバイスの最大数]** 設定は、Microsoft Entra 参加済みまたは Microsoft Entra 登録済みデバイスに適用されます。 この設定は、Microsoft Entra ハイブリッド参加済みデバイスには適用されません。
- **Microsoft Entra 参加済みデバイス上の追加のローカル管理者を管理**: この設定では、テナント内のすべての Microsoft Entra 参加済みデバイスでローカル管理者権限を付与するユーザーを選択できます。 これらのユーザーは、テナント全体に適用される Microsoft Entra 参加済みデバイスのローカル管理者ロールに割り当てられます。
- **登録ユーザーは、Microsoft Entra参加中にデバイスのローカル管理者として追加**されます。この設定では、Microsoft Entra参加を実行するユーザーを、参加するデバイスのローカル Administrators グループに追加するかどうかを制御します。 この設定は、ローカル デバイス管理者のメンバーシップにのみ影響します。 グローバル管理者などのMicrosoft Entra ディレクトリ ロールは割り当てられません。また、Microsoft Entra参加済みデバイスローカル管理者ロールにはユーザーを追加しません。 結果を確認するには、デバイスのローカル Administrators グループを確認するか、デバイス管理ツールを使用します。 Microsoft Graphでは、この設定は`azureADJoin.localAdmins.registeringUsers`の  プロパティによって表されます。
- **Microsoft Entra ローカル管理者パスワード ソリューション (LAPS) (プレビュー) を有効にする**: LAPS は、Windows デバイスでローカル アカウント パスワードを管理するものです。 LAPS には、組み込みのローカル管理者パスワードを安全に管理および取得するためのソリューションが用意されています。 クラウド バージョンの LAPS を使用すると、Microsoft Entra IDと Microsoft Entra ハイブリッド参加デバイスの両方に対してローカル管理者パスワードの格納とローテーションを行うことができます。 Microsoft Entra IDで LAPS を管理する方法については、[概要記事](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-manage-local-admin-passwords)を参照してください。
- **管理者以外のユーザーが所有するデバイスの BitLocker キーを回復できないようにします**: 管理者は、デバイスの登録済み所有者に対するセルフサービス BitLocker キーのaccessをブロックできます。 BitLocker の読み取りアクセス許可を持たない既定のユーザーは、所有しているデバイスの BitLocker キーを表示またはコピーできません。 この設定を更新するには、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)である必要があります。
- **Enterprise State Roaming**: この設定の詳細については、[概要に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/devices/enterprise-state-roaming-enable)を参照してください。

### 監査ログ

デバイス アクティビティは、アクティビティ ログに表示されます。 これらのログには、デバイス登録サービスおよびユーザーによってトリガーされる、次のようなアクティビティが含まれます。

- デバイスの作成と、デバイスへの所有者/ユーザーの追加
- デバイス設定への変更
- デバイスの削除や更新などのデバイス操作
- すべてのデバイスのダウンロードなどの一括操作

注

インポートや作成などの一括操作を実行するときに、一括操作が 1 時間以内に完了しない場合に問題が発生する可能性があります。 この問題を回避するには、バッチごとに処理されるレコードの数を分割することをお勧めします。 たとえば、エクスポートの開始前に、グループの種類またはユーザー名でフィルター処理して結果セットを制限し、結果のサイズを小さくすることができます。 フィルターを絞り込むことで、基本的に一括操作によって返されるデータを制限します。 詳しくは、[一括操作サービスの制限](https://learn.microsoft.com/ja-jp/entra/fundamentals/bulk-operations-service-limitations)に関する記事をご覧ください。

監査データへのエントリ ポイントは、 **[デバイス]** ページの **[アクティビティ]** セクションの **[監査ログ]** です。

監査ログには、次のものを示す既定のリスト ビューがあります。

- 発生の日付と時刻。
- ターゲット。
- アクティビティのイニシエーターまたはアクター。
- アクティビティ。

[Image: [デバイス] ページの [アクティビティ] セクションにあるテーブルのスクリーンショット。表には、4 つの監査ログの日付、ターゲット、アクター、およびアクティビティが一覧表示されています。]

ツール バーの **[列]** を選択することで、リスト ビューをカスタマイズできます。

[Image: [デバイス] ページのツール バーを示しているスクリーンショット。]

報告されたデータを自分に合ったレベルに減らすには、次のフィールドを使用してフィルター処理を行います。

- **カテゴリ**
- **アクティビティのリソースの種類**
- **アクティビティ**
- **日付範囲**
- **ターゲット**
- **開始者 (アクター)**

特定のエントリを検索することもできます。

[Image: 監査データのフィルター処理コントロールを示すスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/manage-stale-devices"} -->
## Microsoft Entra ID で古くなったデバイスを管理する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-stale-devices
- Service: entra-id / devices
- Article date: 2025-06-27
- Summary: Microsoft Entra ID の登録済みデバイスのデータベースから古くなったデバイスを削除する方法について学習します。

ライフサイクルを完了するためには、不要になった時点で登録済みデバイスを登録解除するのが理想的です。 デバイスの紛失、盗難、破損、OS の再インストールなどの理由で、環境には古くなったデバイスが存在しているのが一般的です。 実際に管理が必要なデバイスの管理に IT 管理者がリソースを集中できるよう、古いデバイスを削除する方法が必要です。

この記事では、環境内の古いデバイスを効率的に管理する方法について説明します。

### 古いデバイスとは?

古くなったデバイスとは、特定の期間あらゆるクラウド アプリにアクセスしていない Microsoft Entra ID に登録されたデバイスです。 古いデバイスは、次の理由により、テナント内のデバイスとユーザーを管理およびサポートする能力に影響を及ぼします。

- デバイスが重複していると、どのデバイスが現在アクティブであるかをヘルプデスクのスタッフが識別することが難しくなる可能性があります。
- デバイス数が増えると、不要なデバイス ライトバックが増加し、Microsoft Entra Connect の同期にかかる時間が長くなります。
- 一般的な衛生管理として、またコンプライアンス要件を満たすために、初期化されたデバイス一式を用意しておくとよいでしょう。

Microsoft Entra ID 内の古いデバイスは、組織内のデバイスの一般的なライフサイクル ポリシーに干渉する可能性があります。

### 古いデバイスの検出

一定期間にわたってクラウド アプリへのアクセスに使用されていない登録済みデバイス、というのが古いデバイスの定義であるため、古いデバイスの検出にはタイムスタンプ関連のプロパティが必要です。 Microsoft Entra ID では、このプロパティは **ApproximateLastSignInDateTime** または**アクティビティ タイムスタンプ**と呼ばれます。 現在の時間と**アクティビティ タイムスタンプ**の値の差が、アクティブなデバイスの基準として定義されている期間を超えている場合、デバイスは無効と見なされます。

### アクティビティ タイムスタンプの値の管理のしくみは?

アクティビティ タイムスタンプの評価は、デバイスの認証試行によって発動します。 Microsoft Entra ID は、次の場合にアクティビティ タイムスタンプを評価します。

- [マネージド デバイス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)または[承認されたクライアント アプリ](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-device-compliance)を要求する条件付きアクセス ポリシーがトリガーされている。
- Microsoft Entra 参加済みまたは Microsoft Entra ハイブリッド参加済みを使用した Windows 10 以降のデバイスがネットワーク上でアクティブである。
- Intune マネージド デバイスがサービスにチェックイン済みである。

アクティビティ タイムスタンプの既存の値と現在の値の差が 14 日を超えている場合 (+/-5 日の差異)、既存の値は新しい値に置き換えられます。

### アクティビティ タイムスタンプを取得する方法は?

アクティビティ タイムスタンプの値を取得する方法には、次の 2 つがあります。

- すべてのデバイス ページの **[アクティビティ]** 列。

    [Image: デバイスの名前、所有者、およびその他の情報が一覧表示されたスクリーンショット。1 つの列には、アクティビティのタイム スタンプがリストされている。]
- [Get-MgDevice](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdevice) コマンドレット。

    [Image: コマンド ライン出力のスクリーンショット。強調表示された行に ApproximateLastSignInDateTime 値のタイム スタンプが表示されています。]

### 古いデバイスのクリーンアップを計画する

環境内の古いデバイスを効率的にクリーンアップするには、関連するポリシーを定義する必要があります。 このポリシーは、古いデバイスに関連するすべての考慮事項を確実に把握するために役立ちます。 以下のセクションでは、一般的なポリシーの考慮事項の例を示します。

#### デバイスのライフサイクル コンテキストについて

古いデバイスをクリーンアップする前に、各デバイスが ID ライフサイクル内のどこにあるかを確認します。 デバイス ID は、プロビジョニング、登録、信頼の確立、管理への接続、安定運用、復旧、廃止、および別の参加モデルへの移行を経ることができます。 古いMicrosoft Entraデバイス オブジェクトは、デバイスが再構築または復旧されたとき、Microsoft Entraハイブリッド結合の信頼が切断されたとき、または登録が完了しない場合に表示される可能性があります。

一般的な兆候は次のとおりです。

- OS の再インストール、再イメージ化、または回復後に重複するデバイス オブジェクト。
- 再登録が必要な **保留中** 状態のデバイス オブジェクト。
- オンプレミスの AD またはデバイス管理ツールにまだ存在する最近のMicrosoft Entra アクティビティがないデバイス。

注意事項

組織で BitLocker ドライブ暗号化を使用している場合、デバイスを削除する前に、BitLocker 回復キーがバックアップされているか、不要になっていることを確認する必要があります。 これを行わないと、データが失われる可能性があります。

[Autopilot](https://learn.microsoft.com/ja-jp/autopilot/registration-overview#deregister-from-autopilot-using-intune) や[ユニバーサル プリント](https://learn.microsoft.com/ja-jp/universal-print/reference/portal/register-unregister-printers)などの機能を使用する場合は、それらのデバイスをそれぞれの管理ポータルでクリーンする必要があります。

#### アカウントを整理

Microsoft Entra ID でデバイスを更新するには、次のいずれかのロールが割り当てられているアカウントが必要です。

- [クラウド デバイス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-device-administrator)
- [Intune 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#intune-administrator)

クリーンアップ ポリシーで、必要なロールが割り当てられたアカウントを選択します。

#### 期間

デバイスを古い状態と見なすまでの期間を設定します。 期間の値を定義するときは、アクティビティ タイムスタンプの更新に関して言及されている期間を考慮に入れてください。 たとえば、21 日 (差異を含む) よりも短いタイムスタンプを古いデバイスの指標と見なさないでください。 デバイスが古いように見えるが実際はそうではないシナリオが存在します。 たとえば、影響を受けるデバイスの所有者が、古いデバイスの期間を超えて休暇中または病欠中である可能性があります。

#### デバイスの無効化

誤検出が発生した場合に削除を元に戻すことができないため、古いと思われるデバイスをすぐに削除することはお勧めできません。 削除前に猶予期間を設け、その間はデバイスを無効にするのがベスト プラクティスです。 ポリシーで、削除前にデバイスを無効にする期間を定義します。

#### MDM 制御デバイス

デバイスが Intune またはその他のモバイル デバイス管理 (MDM) ソリューションの制御下にある場合、デバイスを無効化または削除する前に、管理システムでデバイスをインベントリから削除してください。 詳細については、「[ワイプ、インベントリからの削除、デバイス登録の手動解除を使用し、デバイスを削除する](https://learn.microsoft.com/ja-jp/mem/intune/remote-actions/devices-wipe)」を参照してください。

#### システム管理デバイス

システム管理デバイスは削除しないでください。 これらのデバイスは一般に、オートパイロットなどのデバイスです。 一度削除すると、これらのデバイスは再プロビジョニングできません。

#### Microsoft Entra ハイブリッド参加済みのデバイス

お使いの Microsoft Entra ハイブリッド参加デバイスは、オンプレミスの古くなったデバイスの管理に関するポリシーに従う必要があります。

Microsoft Entra ID をクリーンアップするには:

- **Windows 10 以降のデバイス** - オンプレミス AD で Windows 10 以降のデバイスを無効化または削除し、Microsoft Entra Connect によって、変更されたデバイス ステータスを Microsoft Entra ID に同期します。
- **Windows 7/8** - 最初にオンプレミス AD で Windows 7/8 デバイスを無効化または削除します。 Microsoft Entra Connect を使用して、Microsoft Entra ID で Windows 7/8 デバイスを無効化または削除することはできません。 代わりに、オンプレミスで変更を行う場合は、Microsoft Entra ID で無効化または削除する必要があります。

注

- オンプレミス Active Directory または Microsoft Entra ID でデバイスを削除しても、クライアント上の登録は削除されません。 デバイスを ID として使ってリソースにアクセスすること (条件付きアクセスなど) ができなくなるだけです。 [クライアントで登録を解除する](https://learn.microsoft.com/ja-jp/entra/identity/devices/faq)方法の詳細については、こちらをご覧ください。
- Microsoft Entra ID でのみ Windows 10 以降のデバイスを削除すると、Microsoft Entra Connect を使用してオンプレミスのデバイスと再同期されますが、新しいオブジェクトとして "保留中" 状態になります。 このデバイスで再登録が必要です。
- Windows 10 以降または Server 2016 デバイスの同期スコープからデバイスを削除すると、Microsoft Entra デバイスが削除されます。 それを同期スコープに追加しなおすと、新しいオブジェクトが "保留中" 状態になります。 このデバイスの再登録が必要です。
- Windows 10 以降のデバイスの同期に Microsoft Entra Connect を使用していない場合 (たとえば、登録に AD FS のみを使用している場合) は、Windows 7/8 デバイスと同様のライフサイクルを管理する必要があります。

#### Microsoft Entra 参加済みデバイス

Microsoft Entra ID 内の Microsoft Entra 参加済みデバイスを無効化または削除します。

注

- Microsoft Entra デバイスを削除しても、クライアント上の登録は削除されません。 デバイスを ID として使ってリソースにアクセスすること (条件付きアクセスなど) ができなくなるだけです。
- [Microsoft Entra ID で参加解除する方法](https://learn.microsoft.com/ja-jp/entra/identity/devices/faq)の詳細をご覧ください

#### Microsoft Entra の登録デバイス

Microsoft Entra ID 内の Microsoft Entra 登録済みデバイスを無効化または削除します。

注

- Microsoft Entra ID で Microsoft Entra 登録済みデバイスを削除しても、クライアントでの登録は削除されません。 デバイスを ID として使ってリソースにアクセスすること (条件付きアクセスなど) ができなくなるだけです。
- [クライアント上の登録を削除する方法](https://learn.microsoft.com/ja-jp/entra/identity/devices/faq)の詳細をご覧ください

### 古いデバイスをクリーンする

Microsoft Entra 管理センターで古いデバイスをクリーンアップすることはできますが、PowerShell スクリプトを使ってこのプロセスを処理した方が効率的です。 最新の PowerShell V2 モジュールを使い、タイムスタンプ フィルターを使用して Autopilot などのシステム管理デバイスを除外します。

一般的なルーチンは次の手順で構成されます。

1. [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands) コマンドレットを使用して Microsoft Entra ID に接続する
2. デバイスの一覧を取得します。
3. [Update-MgDevice](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/update-mgdevice) コマンドレットを使用してデバイスを無効にします (-AccountEnabled オプションを使用して無効にします)。
4. デバイスが削除されるまでの猶予期間として設けた日数が経過するのを待ちます。
5. [Remove-MgDevice](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/remove-mgdevice) コマンドレットを使用してデバイスを削除します。

#### デバイスの一覧を取得する

すべてのデバイスを取得し、返されたデータを CSV ファイルに保存するには:

```PowerShell
Get-MgDevice -All | select-object -Property AccountEnabled, DeviceId, OperatingSystem, OperatingSystemVersion, DisplayName, TrustType, ApproximateLastSignInDateTime | export-csv devicelist-summary.csv -NoTypeInformation
```

ディレクトリ内のデバイスが多い場合、タイムスタンプ フィルターを使用して返されたデバイスの数を絞り込みます。 90 日以内にログオンしていないすべてのデバイスを取得し、返されたデータを CSV ファイルに格納するには、次のようにします。

```PowerShell
$dt = (Get-Date).AddDays(-90)
Get-MgDevice -All | Where {$_.ApproximateLastSignInDateTime -le $dt} | select-object -Property AccountEnabled, DeviceId, OperatingSystem, OperatingSystemVersion, DisplayName, TrustType, ApproximateLastSignInDateTime | export-csv devicelist-olderthan-90days-summary.csv -NoTypeInformation
```

警告

一部のアクティブなデバイスには、空白のタイム スタンプが設定されている場合があります。

##### デバイスを無効に設定する

同じコマンドを使用して、出力を set コマンドにパイプして、特定の期間にわたってデバイスを無効にすることができます。

```powershell
$dt = (Get-Date).AddDays(-90)
$params = @{accountEnabled = $false
}

$Devices = Get-MgDevice -All | Where {$_.ApproximateLastSignInDateTime -le $dt}
foreach ($Device in $Devices) { 
   Update-MgDevice -DeviceId $Device.Id -BodyParameter $params 
}
```

#### デバイスの削除

注意事項

コマンドレット `Remove-MgDevice` は警告を提供しません。 このコマンドを実行すると、プロンプトを表示せずにデバイスが削除されます。 **削除されたデバイスを回復する方法はありません**。

管理者がデバイスを削除する前に、将来必要になる可能性がある BitLocker 回復キーをバックアップします。 関連付けられているデバイスを削除した後、BitLocker 回復キーを回復する方法はありません。

デバイスの無効化の例を基に、無効になっているデバイスを探し、120 日間非アクティブにし、出力を `Remove-MgDevice` へパイプしてそれらのデバイスを削除します。

```powershell
$dt = (Get-Date).AddDays(-120)
$Devices = Get-MgDevice -All | Where {($_.ApproximateLastSignInDateTime -le $dt) -and ($_.AccountEnabled -eq $false)}
foreach ($Device in $Devices) {
   Remove-MgDevice -DeviceId $Device.Id
}
```

### 知っておくべきこと

#### どうしてもっと頻繁にタイムスタンプが更新されないのですか?

タイムスタンプは、デバイスのライフサイクルのシナリオをサポートするために更新されます。 この属性は監査ではありません。 デバイスのより頻繁な更新には、サインイン監査ログを使用します。 一部のアクティブなデバイスには、空白のタイム スタンプが設定されている場合があります。

#### BitLocker キーに気を付ける必要があるのはなぜですか?

構成されていると、Windows 10 以降のデバイスの BitLocker キーは Microsoft Entra ID 内のデバイス オブジェクトに格納されます。 古いデバイスを削除する場合、デバイスに保存されている BitLocker キーも削除します。 古いデバイスを削除する前に、クリーンアップ ポリシーがデバイスの実際のライフサイクルと整合していることを確認してください。

#### Windows Autopilot デバイスに気を付ける必要があるのはなぜですか?

Windows Autopilot オブジェクトに関連付けられていた Microsoft Entra デバイスを削除した場合、デバイスが将来再利用される場合に、次の 3 つのシナリオが発生する可能性があります。

- 事前プロビジョニングを使用しない Windows Autopilot のユーザー主導型のデプロイを実行すると、新しい Microsoft Entra デバイスが作成されますが、ZTDID にタグ付けされません。
- Windows Autopilot の自己デプロイ モードのデプロイでは、Microsoft Entra デバイスの関連付けが見つからないため、これらのデプロイは失敗します。 (このエラーは、"なりすまし" デバイスが資格情報を使用せずに Microsoft Entra ID への参加を試行しないようにするためのセキュリティ メカニズムです。)この失敗は、ZTDID の不一致を示します。
- Windows Autopilot の事前プロビジョニング デプロイの場合、Microsoft Entra デバイスの関連付けが見つからないため、これらのデプロイは失敗します。 (バックグラウンドでは、事前プロビジョニング デプロイで同じ自己デプロイ モード プロセスが使用されるため、同じセキュリティ メカニズムが適用されます。)

[Get-MgDeviceManagementWindowsAutopilotDeviceIdentity](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.devicemanagement.enrollment/get-mgdevicemanagementwindowsautopilotdeviceidentity) を使用すると、組織内の Windows Autopilot デバイスの一覧を取得し、クリーンアップするデバイスの一覧と比較できます。

#### すべての参加済みデバイスのタイプを知るにはどうすればよいですか?

さまざまなタイプの詳細については、[device management overview](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)(デバイス管理の概要) を参照してください。

#### デバイスを無効にするとどうなりますか?

Microsoft Entra ID への認証にデバイスが使用されているすべての認証は拒否されます。 一般的な例を次に示します。

- **Microsoft Entra Hybrid 参加済みデバイス** - ユーザーはデバイスを使用してオンプレミス ドメインにサインインできます。 ただし、Microsoft 365 などの Microsoft Entra リソースにはアクセスできません。
- **Microsoft Entra 参加済みデバイス** - ユーザーはデバイスを使用してサインインできません。
- **モバイル デバイス** - ユーザーは Microsoft 365 などの Microsoft Entra リソースにアクセスできません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/overview"} -->
## Microsoft Entra ID のデバイス ID とは - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/overview
- Service: entra-id / devices
- Article date: 2025-06-27
- Summary: デバイス ID とそのユース ケース

[デバイス ID](https://learn.microsoft.com/ja-jp/graph/api/resources/device) は、Microsoft Entra ID のオブジェクトです。 このデバイス オブジェクトは、ユーザー、グループ、またはアプリケーションに似ています。 デバイス ID は、管理者がアクセスまたは構成の決定を行うときに使用できる情報を提供します。

[Image: [Microsoft Entra Devices](Microsoft Entra デバイス) ブレードに表示されるデバイス]

デバイス ID を取得するには、次の 3 つの方法があります。

- Microsoft Entra の登録
- Microsoft Entra に参加
- Microsoft Entra ハイブリッド参加

デバイス ID は、デバイス [ベースの条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant) や [、Microsoft Intune 製品ファミリを使用したモバイル デバイス管理](https://learn.microsoft.com/ja-jp/mem/endpoint-manager-overview)などのシナリオの前提条件です。

### 最新のデバイス シナリオ

最新のデバイス シナリオでは、次の 2 つの方法に重点を置いています。

- [Microsoft Entra の登録](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-device-registration)
    - Bring Your Own Device (BYOD)
    - モバイル デバイス (携帯電話とタブレット)
- [Microsoft Entra 参加](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)
    - 組織が所有する Windows 11 および Windows 10 デバイス
    - [Azure で VM として実行されている組織内の Windows Server 2019 以降のサーバー](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-vm-sign-in-azure-ad-windows)

[Microsoft Entra ハイブリッド参加](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join) は、Microsoft Entra 参加への道のりの中間ステップと見なされます。 3 つのシナリオはすべて、1 つの組織で共存できます。

### リソース アクセス

デバイスを Microsoft Entra ID に登録して参加すると、ユーザーはクラウドベースのリソースにシームレス サインオン (SSO) できるようになります。

Microsoft Entra に参加しているデバイスは、 [組織のオンプレミス リソースに対する SSO](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-sso-to-on-premises-resources) の恩恵を受けます。

### プロビジョニング

Microsoft Entra ID へのデバイスの取り込みは、セルフサービス方式または管理者が管理する制御されたプロセスで行うことができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/plan-device-deployment"} -->
## Microsoft Entra デバイスの展開を計画する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/plan-device-deployment
- Service: entra-id / devices
- Article date: 2024-11-25
- Summary: 組織のニーズに合った Microsoft Entra デバイスの統合戦略を選択します。

この記事は、デバイスを Microsoft Entra ID と統合する方法を評価し、実装計画を選択するのに役立ちます。また、サポートされているデバイス管理ツールへの主要なリンクを掲載しています。

ユーザーのデバイスの状況は常に拡大しています。 組織は、デスクトップ、ノート PC、電話、タブレット、その他のデバイスを提供する場合があります。 ユーザーは、独自のデバイスの配列を持ち込み、さまざまな場所から情報にアクセスする場合があります。 このような環境で管理者としての職務は、すべてのデバイスで組織のリソースを安全な状態に保つことです。

Microsoft Entra ID を使用することで、組織はデバイス ID 管理によってこれらの目標を達成できます。 デバイスを Microsoft Entra ID に登録し、[Microsoft Entra 管理センター](https://entra.microsoft.com)から一元的に制御できるようになります。 このプロセスにより統合されたエクスペリエンスと強化されたセキュリティが得られ、新しいデバイスの構成に必要な時間が短縮されます。

デバイスを Microsoft Entra ID に統合するには、複数の方法があります。 これらの方法は、オペレーティング システムと要件に基づいて個別に、またはまとめて動作できます。

- Microsoft Entra ID で[デバイスを登録](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-device-registration)できます。
- デバイスを Microsoft Entra ID に[参加](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)させます (クラウドのみ)。
- [Microsoft Entra ハイブリッド参加](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join)により、デバイスをオンプレミスの Active Directory ドメインと Microsoft Entra ID に参加させます。

### Learn

始める前に、[デバイス ID 管理の概要](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)について理解しておいてください。

#### Benefits

デバイスに Microsoft Entra ID を付与する主な利点は次のとおりです。

- 生産性の向上 – ユーザーは、オンプレミスとクラウドのリソースへの [シームレスなサインオン (SSO)](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-sso-to-on-premises-resources) を実行して、どこにいても生産性を高めることができます。
- セキュリティの強化 – デバイスまたはユーザーの ID に基づいて、リソースに[条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)を適用できます。 デバイスを Microsoft Entra ID に参加させることは、[パスワードレス](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)戦略を使用してセキュリティを強化するための前提条件です。
- ユーザー エクスペリエンスの向上 – ユーザーが個人のデバイスと会社のデバイスの両方から組織のクラウドベースのリソースに簡単にアクセスできます。 管理者は、すべての Windows デバイスで統一されたエクスペリエンスを実現するために [Enterprise State Roaming](https://learn.microsoft.com/ja-jp/entra/identity/devices/enterprise-state-roaming-enable) を有効にすることができます。
- デプロイと管理の簡素化 - [Windows オートパイロット](https://learn.microsoft.com/ja-jp/windows/deployment/windows-autopilot/windows-10-autopilot)、[一括プロビジョニング](https://learn.microsoft.com/ja-jp/mem/intune/enrollment/windows-bulk-enroll)、[セルフサービス: Out of Box Experience (OOBE)](https://support.microsoft.com/account-billing/join-your-work-device-to-your-work-or-school-network-ef4d6adb-5095-4e51-829e-5457430f3973) を使用して、デバイスを Microsoft Entra ID に取り込むプロセスを簡素化できます。 [Microsoft Intune](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/what-is-intune) などのモバイル デバイス管理 (MDM) ツールと、[Microsoft Entra 管理センター](https://entra.microsoft.com) でのそれらの ID を使用して、デバイスを管理します。

### デプロイ プロジェクトを計画する

お客様の環境でこのデプロイの戦略を決定するときは、お客様の組織のニーズを考慮してください。

#### 適切な関係者を関わらせる

テクノロジ プロジェクトが失敗した場合、通常その原因は、影響、結果、責任に関する想定が一致しないことです。 これらの落とし穴を回避するには、[適切な利害関係者が担当していることを確認](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans)し、プロジェクトにおけるその利害関係者の役割がよく理解されていることを確認します。

このプランでは、次の利害関係者をリストに追加します。

| Role | Description |
| --- | --- |
| デバイス管理者 | プランが組織のデバイス要件を満たしていることを確認できるデバイス チームの担当者。 |
| ネットワーク管理者 | ネットワーク要件を満たしていることを確認できるネットワーク チームの代表。 |
| デバイス管理チーム | デバイスのインベントリを管理するチーム。 |
| OS 固有の管理チーム | 特定の OS バージョンをサポートおよび管理するチーム。 たとえば、Mac または iOS に重点を置いたチームがあるとします。 |

#### 連絡を計画する

通信は、新しいサービスの成功に不可欠です。 ユーザー エクスペリエンスがどのように変わるのか、いつ変わるのか、および問題が発生したときにサポートを受ける方法について、ユーザーに事前に連絡します。

#### パイロットを計画する

統合方法の初期構成は、テスト環境で行うか、小さいグループのテスト用デバイスで行うことをお勧めします。 「[パイロットのベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans)」を参照してください。

組織全体で有効にする前に、 [Microsoft Entra ハイブリッド参加の対象となる展開](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-control) を行う必要がある場合があります。

Warning

組織には、パイロット グループ内のさまざまなロールやプロファイルからのユーザーのサンプルを含める必要があります。 対象を絞ってロールアウトすると、組織全体に対してを有効にする前に、計画で対処されていない可能性のある問題を特定するのに役立ちます。

#### Microsoft Entra を使用したデバイス登録のネットワーク要件

TLS インターセプトまたはネットワーク プロキシ フィルタリングを使用して登録が意図したとおりに動作するようにする場合は、次のサイトを除外する必要があります。

- `enterpriseregistration.windows.net`
- `certauth.enterpriseregistration.windows.net`
- `enterpriseregistration.partner.microsoftonline.cn` (\*\*)
- `certauth.enterpriseregistration.partner.microsoftonline.cn`(\*\*)
- `enterpriseregistration.microsoftonline.us`(\*\*)
- `certauth.enterpriseregistration.microsoftonline.us`(\*\*)

( \*\* ) ソブリンクラウドドメインに依存している場合にのみ、それらを許可する必要があります。

Note

これらのエンドポイントを除外しないと、デバイス登録フローが失敗する可能性があります。

### 統合方法を選択する

組織は、1 つの Microsoft Entra テナントでデバイスの統合方法を複数使用できます。 目標は、デバイスを Microsoft Entra ID で安全に管理するために適した 1 つ以上の方法を選択することです。 所有権、デバイスの種類、主要な対象ユーザー、組織のインフラストラクチャなど、この決定を行う多くのパラメーターがあります。

使用する統合方法を決定する際には、次の情報を参考にしてください。

#### デバイス統合のデシジョン ツリー

組織が所有するデバイスのオプションを決定するには、このツリーを使用します。

Note

この図では、個人のデバイスや持ち込みのデバイス (BYOD) のシナリオは示していません。 それらについては常に Microsoft Entra 登録が使用されます。

[Image: 決定木]

#### 比較マトリックス

iOS および Android デバイスは、Microsoft Entra のみが登録されています。 次の表に、Windows クライアント デバイスに関する大まかな考慮事項を示します。 これを概要として使用し、その後さまざまな統合方法について詳しく説明します。

| Consideration | Microsoft Entra 登録済み | Microsoft Entra 参加済み | Microsoft Entra ハイブリッド参加済み |
| --- | --- | --- | --- |
| **クライアント オペレーティング システム** |  |  |  |
| Windows 11 または Windows 10 デバイス | [Image: これらの値にチェックマーク。] | [Image: これらの値にチェックマーク。] | [Image: これらの値にチェックマーク。] |
| Linux Desktop - Ubuntu 20.04/22.04/24.04、RHEL 8/9 | [Image: これらの値にチェックマーク。] |  |  |
| **サインイン オプション** |  |  |  |
| エンドユーザーのローカル資格情報 | [Image: これらの値にチェックマーク。] |  |  |
| Password | [Image: これらの値にチェックマーク。] | [Image: これらの値にチェックマーク。] | [Image: これらの値にチェックマーク。] |
| 装置 PIN | [Image: これらの値にチェックマーク。] |  |  |
| Windows Hello | [Image: これらの値にチェックマーク。] |  |  |
| Windows Hello for Business |  | [Image: これらの値にチェックマーク。] | [Image: これらの値にチェックマーク。] |
| FIDO 2.0 セキュリティ キー |  | [Image: これらの値にチェックマーク。] | [Image: これらの値にチェックマーク。] |
| Microsoft Authenticator アプリ (パスワードレス) | [Image: これらの値にチェックマーク。] | [Image: これらの値にチェックマーク。] | [Image: これらの値にチェックマーク。] |
| **主な機能** |  |  |  |
| クラウド リソースへの SSO | [Image: これらの値にチェックマーク。] | [Image: これらの値にチェックマーク。] | [Image: これらの値にチェックマーク。] |
| オンプレミスのリソースへの SSO |  | [Image: これらの値にチェックマーク。] | [Image: これらの値にチェックマーク。] |
| 条件付きアクセス  (デバイスは準拠としてマーク済みであることが必要)  (MDM による管理が必要) | [Image: これらの値にチェックマーク。] | [Image: これらの値にチェックマーク。] | [Image: これらの値にチェックマーク。] |
| 条件付きアクセス (Microsoft Entra ハイブリッド参加済みデバイスが必要) |  |  | [Image: これらの値にチェックマーク。] |
| Windows のログイン画面からのセルフサービス パスワード リセット |  | [Image: これらの値にチェックマーク。] | [Image: これらの値にチェックマーク。] |
| Windows Hello の PIN のリセット |  | [Image: これらの値にチェックマーク。] | [Image: これらの値にチェックマーク。] |

### Microsoft Entra 登録

登録されたデバイスは、多くの場合 [Microsoft Intune](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/deployment-guide-enrollment) で管理されます。 デバイスは、オペレーティング システムに応じて、いくつかの方法で Intune に登録されます。

Microsoft Entra 登録済みデバイスでは、持ち込みのデバイス (BYOD) と会社所有のデバイスによるクラウド リソースへの SSO がサポートされています。 リソースへのアクセスは、デバイスとユーザーに適用された Microsoft Entra の[条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)に基づいています。

#### デバイスの登録

登録されたデバイスは、多くの場合 [Microsoft Intune](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/deployment-guide-enrollment) で管理されます。 デバイスは、オペレーティング システムに応じて、いくつかの方法で Intune に登録されます。

ユーザーはポータル サイト アプリをインストールして、BYOD と企業所有のモバイル デバイスを登録します。

- [iOS](https://learn.microsoft.com/ja-jp/mem/intune/user-help/sign-in-to-the-company-portal)
- [Android](https://learn.microsoft.com/ja-jp/mem/intune/user-help/enroll-device-android-company-portal)
- [Windows 10 以降](https://learn.microsoft.com/ja-jp/mem/intune/user-help/enroll-windows-10-device)
- [macOS](https://learn.microsoft.com/ja-jp/mem/intune/user-help/enroll-your-device-in-intune-macos-cp)
- [Linux デスクトップ](https://learn.microsoft.com/ja-jp/mem/intune/user-help/enroll-device-linux)

組織に最適なオプションがデバイスの登録である場合は、次のリソースを参照してください。

- [Microsoft Entra 登録済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-device-registration)の概要。
- このエンドユーザー向けの、[組織のネットワークへの個人のデバイスの登録](https://support.microsoft.com/account-billing/register-your-personal-device-on-your-work-or-school-network-8803dd61-a613-45e3-ae6c-bd1ab25bf8a8)に関するドキュメント。

### Microsoft Entra に参加

Microsoft Entra 参加により、Windows を使用するクラウド中心のモデルに移行できます。 デバイス管理の最新化とデバイス関連の IT コストの削減を計画している場合は、これにより優れた基盤が提供されます。 Microsoft Entra 参加は、Windows 10 以降のデバイスのみで機能します。 新しいデバイスの最初の選択肢として検討してください。

 オンプレミスのリソースが組織のネットワーク上にある場合、[Microsoft Entra 参加済みデバイスではオンプレミスのリソースへの SSO を使用でき](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-sso-to-on-premises-resources)、ファイル、印刷、その他のアプリケーションなどのオンプレミスのサービスに対して認証できます。

このオプションが組織に最適な場合は、次のリソースを参照してください。

- [Microsoft Entra 参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)の概要。
- [Microsoft Entra 参加の実装計画](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-plan)について理解を深めます。

#### Microsoft Entra 参加済みデバイスのプロビジョニング

デバイスの Microsoft Entra 参加をプロビジョニングするには、次の方法があります。

- セルフサービス: [Windows 10 の最初の実行エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-out-of-box)

Windows 10 Professional または Windows 10 Enterprise のいずれかがデバイスにインストールされている場合、既定のエクスペリエンスは、会社所有のデバイスのセットアップ プロセスになります。

- [Windows の Out of Box Experience (OOBE) または Windows の設定から](https://support.microsoft.com/account-billing/join-your-work-device-to-your-work-or-school-network-ef4d6adb-5095-4e51-829e-5457430f3973)
- [Windows Autopilot](https://learn.microsoft.com/ja-jp/windows/deployment/windows-autopilot/windows-autopilot)
- [一括登録](https://learn.microsoft.com/ja-jp/mem/intune/enrollment/windows-bulk-enroll)

慎重に[これらのアプローチを比較](https://learn.microsoft.com/ja-jp/entra/identity/devices/device-join-plan)した後に、デプロイ手順を選択してください。

Microsoft Entra 参加が、別の状態のデバイスに最適なソリューションであると判断する場合があります。 次の表は、デバイスの状態を変更する方法を示しています。

| 現在のデバイスの状態 | 目的のデバイスの状態 | How-to |
| --- | --- | --- |
| オンプレミスのドメイン参加済み | Microsoft Entra 参加済み | Microsoft Entra ID に参加する前に、オンプレミスのドメインからデバイスの参加を解除します。 |
| Microsoft Entra ハイブリッド参加済み | Microsoft Entra 参加済み | Microsoft Entra ID に参加する前に、オンプレミス ドメインと Microsoft Entra ID からデバイスの参加を解除します。 |
| Microsoft Entra 登録済み | Microsoft Entra 参加済み | Microsoft Entra ID に参加する前に、デバイスの登録を解除します。 |

### Microsoft Entra ハイブリッド参加

オンプレミスの Active Directory 環境があるときに、既存のドメイン参加済みコンピューターを Microsoft Entra ID に参加させたい場合は、Microsoft Entra ハイブリッド参加を使用してこのタスクを実現できます。 [幅広い Windows デバイスを](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)サポートしています。

ほとんどの組織には、ドメイン参加済みデバイスが既にあり、グループ ポリシーまたは System Center Configuration Manager (SCCM) を使用して管理されています。 その場合、Microsoft Entra ハイブリッド参加を、既存の投資を活用しながら利点を得られるように構成することをお勧めします。

Microsoft Entra ハイブリッド参加が組織に最適なオプションの場合は、次のリソースを参照してください。

- [Microsoft Entra ハイブリッド参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join)の概要。
- [Microsoft Entra ハイブリッド参加の実装](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)計画について理解を深めます。

#### デバイスの Microsoft Entra ハイブリッド参加のプロビジョニング

[ID インフラストラクチャを確認](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)します。 Microsoft Entra Connect には、Microsoft Entra ハイブリッド参加を構成するための次のウィザードが用意されています。

- [マネージド ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join#managed-domains)
- [フェデレーション ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join#federated-domains)

必要なバージョンの Microsoft Entra Connect をインストールできない場合は、[Microsoft Entra ハイブリッド参加を手動で構成する方法](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-manual)に関するページを参照してください。

Note

オンプレミスのドメインに参加している Windows 10 以降のデバイスは、既定で Microsoft Entra ハイブリッド参加済みになるように、Microsoft Entra ID への自動参加を試行します。 これは、適切な環境を設定した場合にのみ成功します。

それとは異なるの状態のデバイスについては、Microsoft Entra ハイブリッド参加が最適なソリューションかどうかを判断する必要があります。 次の表は、デバイスの状態を変更する方法を示しています。

| 現在のデバイスの状態 | 目的のデバイスの状態 | How-to |
| --- | --- | --- |
| オンプレミスのドメイン参加済み | Microsoft Entra ハイブリッド参加済み | Microsoft Entra Connect または AD FS を使用して Azure に参加します。 |
| オンプレミスのワークグループ参加済みまたは新規 | Microsoft Entra ハイブリッド参加済み | [Windows Autopilot](https://learn.microsoft.com/ja-jp/windows/deployment/windows-autopilot/windows-autopilot) でサポートされています。 それ以外の場合、デバイスは Microsoft Entra ハイブリッド参加の前にオンプレミスのドメインに参加済みである必要があります。 |
| Microsoft Entra 参加済み | Microsoft Entra ハイブリッド参加済み | Microsoft Entra ID から参加を解除します。これにより、オンプレミスのワークグループまたは新しい状態になります。 |
| Microsoft Entra 登録済み | Microsoft Entra ハイブリッド参加済み | Windows のバージョンによって異なります。 [これらの考慮事項を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)。 |

### デバイスを管理する

デバイスを Microsoft Entra ID に登録または参加させたら、デバイス ID を一元的に管理するために [Microsoft Entra 管理センター](https://entra.microsoft.com)を使用します。 Microsoft Entra のデバイス ページでは、次の操作を実行できます。

- [デバイス設定を構成する](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-device-identities#configure-device-settings)。
- Windows デバイスを管理するには、ローカル管理者である必要があります。 [Microsoft Entra ID は、Microsoft Entra 参加済みデバイスのこのメンバーシップを更新](https://learn.microsoft.com/ja-jp/entra/identity/devices/assign-local-admin)し、デバイス マネージャーのロールを持つユーザーを、すべての参加済みデバイスに管理者として自動的に追加します。

[古いデバイスを管理する](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-stale-devices)ことで環境をクリーンな状態に保って、現在のデバイスの管理に関するリソースに集中するようにしてください。

- [デバイス関連の監査ログを確認する](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-device-identities#audit-logs)

#### サポートされているデバイス管理ツール

管理者は、他のデバイス管理ツールを使用して、登録済みと参加済みのデバイスをセキュリティで保護し、さらに制御することができます。 これらのツールを使用すると、ストレージの暗号化、パスワードの複雑さ、ソフトウェアのインストール、ソフトウェア更新プログラムの要求などの構成を適用できます。

統合デバイスでサポートされているプラットフォームとサポートされていないプラットフォームを確認します。

| デバイス管理ツール | Microsoft Entra 登録済み | Microsoft Entra 参加済み | Microsoft Entra ハイブリッド参加済み |
| --- | --- | --- | --- |
| [モバイル デバイス管理 (MDM)](https://learn.microsoft.com/ja-jp/windows/client-management/azure-active-directory-integration-with-mdm)例:Microsoft Intune | [Image: これらの値にチェックマーク。] | [Image: これらの値にチェックマーク。] | [Image: これらの値にチェックマーク。] |
| [Microsoft Intune と Microsoft Configuration Manager による共同管理](https://learn.microsoft.com/ja-jp/mem/configmgr/comanage/overview)(Windows 10 以降) |  | [Image: これらの値にチェックマーク。] | [Image: これらの値にチェックマーク。] |
| [グループ ポリシー](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2012-R2-and-2012/hh831791%28v=ws.11%29)(Windows のみ) |  |  | [Image: これらの値にチェックマーク。] |

登録済みの iOS または Android デバイスのデバイス管理の有無にかかわらず、[Microsoft Intune のモバイル アプリケーション管理 (MAM)](https://learn.microsoft.com/ja-jp/mem/intune/apps/app-management) を検討することをお勧めします。

また、管理者は、組織内の Windows オペレーティング システムをホストする[仮想デスクトップ インフラストラクチャ (VDI) プラットフォームをデプロイ](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-device-identity-virtual-desktop-infrastructure)して、管理を効率化し、リソースの統合と一元化によってコストを削減することもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/protecting-tokens-microsoft-entra-id"} -->
## Microsoft Entra IDでのトークンの保護 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/protecting-tokens-microsoft-entra-id
- Service: entra-id / devices
- Article date: 2025-04-24
- Summary: トークンの盗難やリプレイ攻撃から保護するには、Microsoft Entraで使用されるトークンの種類と認証におけるその役割を調べる必要があります。

この記事は、[Microsoft Entra ID におけるトークンの理解](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-tokens-microsoft-entra-id) の続きです。 この記事では、Microsoft Entra IDのトークンについて理解していることを前提とし、環境内でトークンの盗難/リプレイ攻撃が成功するリスクを軽減するために実行できる具体的な手順について説明します。

この記事の推奨事項は、さまざまなライセンス要件を持つ複数の Microsoft テクノロジ ソリューションにまたがっています。 次の目的で適切なライセンスが付与されていることを確認します。

- [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview#license-requirements)
- Microsoft サービス向けの[Microsoft Entra Internet Access](https://learn.microsoft.com/ja-jp/entra/global-secure-access/overview-what-is-global-secure-access#licensing-overview)
- [Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection#license-requirements)
- [トークン保護](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-token-protection)
- [Microsoft Intune (最小プラン 1)](https://learn.microsoft.com/ja-jp/intune//intune-service/fundamentals/licenses#microsoft-intune-plan-1)
- [Microsoft Defender for Endpoint XDR](https://learn.microsoft.com/ja-jp/defender-xdr/prerequisites#licensing-requirements)

### トークンの盗難に対する多層防御戦略

攻撃対象領域を減らし、トークン侵害が成功するリスクを軽減するために有効にできる機能がいくつかあります。 次のセクションでは、次の 3 つのカテゴリのいずれかに分類される多くの Microsoft セキュリティ機能について説明します。

- **リスクを最小限に抑える**: 攻撃面を強化または減少させ、トークンの盗難を成功させるのがより困難になります。
- **検出 + 軽減**: 成功したトークンの盗難を検出し、可能であれば自動軽減策を構成します。
- **再生からの保護**: 再生をブロックするか、トークンの盗難が成功した場合の影響を軽減します。

次に、トークン盗難防止戦略の一環として組織が重点を置く必要がある主要な領域を把握した概要を示します。

[Image: トークンの盗難に対する防御戦略を示す図。]

### トークン盗難 – リスクを最小限に抑える

トークン盗難のインシデントを未然に防ぐことは、組織を守るための最も効果的な方法です。 組織は、Microsoft Defender for EndpointとMicrosoft Intuneを使用して、デバイス ベースのトークン流出方法に対してデバイスを強化する必要があります。 また、組織は、ユーザーがインターネット上の悪意のある、または危険な宛先にアクセスするのを防ぐために、コントロールを展開する必要があります。

#### デバイスを強化する

次の構成と展開を実行して、すべてのデバイス/エンドポイントをマルウェアベースのトークン盗難に対する防御の最前線として強化します。 開始する前に、デバイスが Intune に登録されていること、および [Microsoft Defender for Endpoint](https://learn.microsoft.com/ja-jp/defender-endpoint/mde-planning-guide) が展開されていることを確認します。

| コントロール | Windows 10/11 | macOS | Linux |
| --- | --- | --- | --- |
| [Microsoft Defenderウイルス対策の常時オン保護](https://learn.microsoft.com/ja-jp/defender-endpoint/configure-real-time-protection-microsoft-defender-antivirus)を有効にして、既知の不審なアクティビティや悪意のあるアクティビティに基づいてマルウェアを特定するためのリアルタイムの保護、動作の監視、ヒューリスティックを実現します。 | X | X | X |
| [エンドポイント上およびネットワーク経由でのマルウェアからの保護に役立つMicrosoft Defenderウイルス対策クラウド保護](https://learn.microsoft.com/ja-jp/defender-endpoint/enable-cloud-protection-microsoft-defender-antivirus)を有効にします。 | X | X | X |
| 悪意のあるサイトや疑わしいサイトへの接続を防ぐことで、特定のインターネット ベースのイベントからデバイスを保護するために> Microsoft Defender for Endpoint | X | X | X |
| Microsoft Defender for Endpointウイルスや脅威の防止などの特定のセキュリティ設定が無効または変更されないように保護します。 | X | X | - |
| [Intuneにおいて、Microsoft Defender for Endpointによってマシンのリスクレベルを*low*または*clear*と指定することを要求するデバイスコンプライアンスポリシーを作成します。](https://learn.microsoft.com/ja-jp/intune/intune-service/protect/advanced-threat-protection-configure) | X | X | - |

デバイスのセキュリティ強化ポリシーが適用されている場合でも、組織は、ユーザーが[準拠しているデバイス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-device-compliance)を使用してすべてのリソースにアクセスすることを要求する**条件付きアクセス ポリシーを作成**する必要があります。 これにより、デバイスがデバイスのセキュリティを強化する構成が正常に展開され、ユーザーがアンマネージド デバイスまたは安全でないデバイスからアプリケーションやリソースにアクセスできなくなります。

#### Windowsのその他の構成

- [Credential Guard を構成](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/credential-guard/configure) してローカル セキュリティ機関を分離し、メモリからの資格情報の盗難から保護します。
- [Windows登録証明](https://learn.microsoft.com/ja-jp/intune/intune-service/enrollment/windows-enrollment-attestation) レポートを確認します。 Windows デバイスが TPM 要件を満たしていることを検証します。 TPM 構成証明に失敗したデバイスで是正措置を講じてください。

#### macOS のその他の構成

- [Microsoft Intune](https://learn.microsoft.com/ja-jp/intune/intune-service/configuration/device-restrictions-macos#settings-apply-to-all-enrollment-types-1) と iCloud キーチェーンの同期を解除して、キーチェーンに格納される可能性のある Entra トークンの同期を防止します。
- [Apple デバイス用の Microsoft Enterprise SSO プラグイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin) を有効にして、Enterprise Apps が認証にプライマリ更新トークン (PRT) を利用できるようにします。
- [macOS デバイス](https://learn.microsoft.com/ja-jp/intune/intune-service/configuration/platform-sso-macos) (セキュリティで保護されたエンクレーブ) のプラットフォーム SSO を構成し、ハードウェア バインド暗号化キーを使用して Mac デバイスに対してセキュリティで保護されたフィッシング対策認証を提供します。

#### モバイル デバイスを強化する

iOS や Android などのモバイル デバイスは、 [モバイル脅威防御](https://learn.microsoft.com/ja-jp/defender-business/mdb-mtd)を使用して強化できます。 モバイル脅威防御には、侵害されたデバイスから保護できるさまざまな機能と、最初にマルウェアがインストールされるのを妨げる可能性がある Web 脅威が含まれており、キル チェーンの早い段階でトークン流出 (およびその他の脅威) を防ぐことができます。

#### Microsoft Defender XDR攻撃の中断

敵による中間者攻撃 (AiTM) は、Microsoft Defender XDR の攻撃妨害機能において扱われるシナリオであり、攻撃の初期段階で連携された脅威防御を提供します。 すべての Defender XDR ワークロード (Defender for Identity、Defender for Office、Defender for Cloud Apps) を展開し、文書化されているすべての[前提条件と構成](https://learn.microsoft.com/ja-jp/defender-xdr/configure-attack-disruption)に従って、Microsoft Defender XDRで攻撃の中断が構成されていることを確認します。 攻撃の中断は、初期段階で AiTM 攻撃を検出し、エンドポイントと ID にセキュリティ制御を自動的に軽減することで攻撃を中断します。

#### インターネットの脅威に対するセキュリティ強化

Microsoft Edgeを使用する組織では、[Microsoft Defender SmartScreen](https://learn.microsoft.com/ja-jp/deployedge/microsoft-edge-security-smartscreen) を有効にする必要があります。 Microsoft Defender SmartScreen は、フィッシング攻撃に関与したり、焦点を絞った攻撃を通じてマルウェアを配布しようとする可能性がある Web サイトに対する早期警告システムを提供します。

Microsoft Entra Internet Accessは、インターネット全体をカバーするより多くの保護を提供します。 組織は、グローバル セキュア アクセス (GSA) クライアントを管理対象デバイスに展開し、 [Web コンテンツ フィルタリング](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-web-content-filtering)を使用して悪意のある Web コンテンツや承認されていない Web コンテンツをブロックできます。 これにより、ユーザーが悪意のある Web サイトに移動する可能性が低くなります。これにより、マルウェアがインストールされたり、デバイスが侵害されたりする可能性があります。 管理者は、少なくとも *、違法なソフトウェア* カテゴリをブロックする必要がありますが、すべての [責任 Web カテゴリ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/reference-web-content-filtering-categories#liability)のブロックも確認して検討する必要があります。

#### デバイス コード フローの使用を制限する

デバイス コード フローは、入力機能が制限されているデバイスや Web ブラウザーがないデバイスに特に役立ちます。 ただし、デバイス コード フローは、フィッシング攻撃の一部として、またはアンマネージド デバイス上の企業リソースにアクセスするために使用できます。 デバイス コード フロー制御は、条件付きアクセス ポリシーの他の制御と共に構成することができます。 たとえば、デバイス コード フローが Android ベースの会議室デバイスに使用されている場合は、特定のネットワークの場所にある Android デバイスを除くすべての場所でデバイス コード フローをブロックすることを選択できます。

デバイス コード フローを許可するのは必要な場所においてだけにする必要があります。 Microsoft は、可能な限りデバイス コード フローをブロックすることを推奨しています。

[条件付きアクセス認証フローの詳細を確認します](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-authentication-flows#device-code-flow)。

### トークンの盗難 - 検出と軽減

組織は、成功または試行されたトークン盗難攻撃を積極的に監視する必要があります。 トークンの盗難やアカウント侵害の可能性を示すさまざまな Microsoft 製品から生成されるアラートが多数あります。 これらの検出の概要を次に示します。 SIEM を使用して特定されたトークンの盗難を監視、検出、対応する方法の詳細なガイドについては、 [トークン盗難プレイブック](https://learn.microsoft.com/ja-jp/security/operations/token-theft-playbook)を参照してください。

#### 条件付きアクセス ポリシー

組織では、次の条件付きアクセス ポリシーを構成する必要があります。

- 機密性の高い操作 (認証コンテキスト) に対話型の再認証を要求する
- 危険なサインインに対話型認証を要求する
- リスクの高いユーザーを検出して修復する

これらの条件付きアクセス ポリシーは、より自動化されたトークン盗難の修復を提供したり、トークンベースの攻撃で使用できる他の脅威ベクトルに対処したりします。

**機密性の高い操作に対話型の再認証を要求する**

組織は、認証コンテキストを使用して特定のアクションを構成して、通常の認証フロー以外の条件付きアクセス ポリシーの評価をトリガーできます。 たとえば、管理者が Privileged Identity Management (PIM) でロールをアクティブ化したとき、またはユーザーがアプリケーション内で特定のアクションを実行したときに評価するように条件付きアクセス ポリシーを構成できます。 管理者は、機密性が高いと見なされる認証コンテキスト アクションに対して、対話型のフィッシング耐性認証 (サインイン頻度を毎回設定) を必要とする条件付きアクセス ポリシーを構成する必要があります。 攻撃者が再認証できない場合、アクセスは拒否され、盗まれたサインイン セッションが機密性の高い操作を完了するために使用されるのを防ぎます。

[条件付きアクセスで認証コンテキストを構成する方法について説明します](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#authentication-context)。

[アプリケーションで認証コンテキストを使用する方法について説明します (開発者向けガイダンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-guide-conditional-access-authentication-context))。

**危険なサインインに対話型認証を要求する**

Entra ID Identity Protection を使用すると、Microsoft Defender for Endpointからのより多くの検出によって強化され、Entra ID は疑わしいサインイン試行をリアルタイムで検出できます。 たとえば、攻撃者が更新トークンを盗んで再生しようとした場合、Entra ID Identity Protection はサインインに未知のプロパティがあることを識別し、このイベントのサインイン リスク レベルを高める可能性があります。 管理者は、中以上のサインイン リスク レベルに対して対話型のフィッシング耐性認証 (サインイン頻度を毎回設定) を必要とする条件付きアクセス ポリシーを構成する必要があります。 攻撃者が再認証できない場合、アクセスは拒否され、盗まれたサインイン セッションが不正アクセスの取得または拡張に使用されるのを防ぎます。

[リスクに基づく条件付きアクセス ポリシーを構成する方法について学びます](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)。

**リスクの高いユーザーを検出して修復する**

Entra ID Identity Protection を使用すると、Microsoft Defender for Endpointからのより多くの検出によって強化され、Entra ID はすべてのアカウントに対してユーザー リスク スコアを生成し、アカウントが侵害されたかどうかに関する確実性のレベルを示します。 Entra ID または Microsoft Defender for Endpoint がトークンの盗難に成功した兆候を検出した場合、ユーザーのリスク スコアが *High* に設定される可能性が高くなります。 これが発生した場合、アカウントを自動的にブロックまたは修復し (セキュリティで保護されたパスワードの変更など)、敵対者が未承認のアクセスをこれ以上悪用できないようにすることができます。

継続的アクセス評価をサポートするアプリケーションは、ユーザー リスクが高い場合にほぼリアルタイムでアクセスを自動的に取り消し、再認証と再認証のために Entra ID にリダイレクトを発行します。

[リスクに基づく条件付きアクセス ポリシーを構成する方法について学びます](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-risk-policies)。

#### Microsoft Defender XDR

Defender XDR ワークロードを展開して、トークンの盗難に関する疑わしい動作や異常な動作に関するアラートを生成します。

- [Defender for Office 365](https://www.microsoft.com/en-us/security/business/siem-and-xdr/microsoft-defender-office-365) を使用して、悪意のあるメール、リンク、ファイルを検出してブロックします
- Microsoft Defender for Cloud Apps [connectors](https://learn.microsoft.com/ja-jp/defender-cloud-apps/enable-instant-visibility-protection-and-governance-actions-for-your-apps) と Microsoft Defender XDR を使用して、複数のシナリオで AiTM 関連のアラートが発生します。 Microsoft Edgeを使用している Entra ID のお客様の場合、[Office 365](https://learn.microsoft.com/ja-jp/defender-cloud-apps/connect-office-365) および [Azure](https://learn.microsoft.com/ja-jp/defender-cloud-apps/connect-azure) の Defender for Cloud Apps コネクタによって、セッション Cookie を再生してクラウド アプリケーションにアクセスしようとする攻撃者の試行が検出されます。

Defender for Cloud Apps コネクタと Defender for Endpoint を使用する場合のMicrosoft Defender XDRは、次のアラートを発生させることができます。

- 盗まれたセッション Cookie が使用されました
- AiTM フィッシングの試行の可能性

#### その他の検出

[**Entra ID Protection のリスク検出**](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)

- 異常なトークン
- 中間攻撃者
- 見慣れないサインイン プロパティ

[**Microsoft Defender for Office 365検出結果**](https://learn.microsoft.com/ja-jp/defender-xdr/alert-policies#threat-management-alert-policies)

- 配信後に削除された悪意のあるファイルを含む電子メール メッセージ
- 配信後に削除されたキャンペーンからのメール メッセージ
- 悪意のある可能性がある URL のクリックが検出された
- ユーザーが悪意のある可能性がある URL をクリックしました。

[**Microsoft Defender for Cloud Apps異常検出**](https://learn.microsoft.com/ja-jp/defender-cloud-apps/anomaly-detection-policy)

- あり得ない旅行活動
- 頻度の低い国からのアクティビティ

[**Microsoft Defender XDR ビジネスメール詐欺対策**](https://www.microsoft.com/en-us/security/blog/2023/06/08/detecting-and-mitigating-a-multi-stage-aitm-phishing-and-bec-campaign/)

- Business Email Compromise (BEC) 関連の資格情報収集攻撃
- BEC 関連ユーザーから送信された疑わしいフィッシングメール

### トークン盗難 - リプレイから保護

敵対者がトークンを盗むことに成功した場合、組織は特定の機能を有効にして、自動的に盗まれたトークンの使用を防ぎ、攻撃を阻止することができます。 これらの機能には、次のものが含まれます。

- 条件付きアクセスでのトークン保護をセキュリティで保護されたサインイン セッションに適用する
- アクセスの強制は、セキュリティで保護されたネットワーク経由でのみ許可されます

#### トークン保護を適用する

**Entra プライマリ更新トークン**

Entra 参加済みまたは Entra 登録済みのデバイスの場合、Entra ID は、アプリケーション SSO (プライマリ更新トークン [(PRT)](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token) とも呼ばれます) に使用されるマルチアプリケーション更新トークンを生成します。

プライマリ更新トークン (PRT) は、PRT と PRT が発行されるデバイス (クライアント シークレット) との間の暗号化によってセキュリティで保護されます。 Windowsデバイスでは、クライアント シークレットはトラステッド プラットフォーム モジュール (TPM) などのプラットフォーム固有のハードウェアに安全に格納されます。 現在、Windows以外のデバイスでは、シークレットがソフトウェアに格納されています。

**条件付きアクセスでのトークン保護**

条件付きアクセスでトークン保護を適用すると、デバイスに暗号化的にバインドされた更新トークンのみが使用されます。 任意のデバイスから使用できるベアラー更新トークンは自動的に拒否されます。 このメソッドは、最初に発行されたデバイスからのみトークンを使用できるため、サインイン セッションを保護するための最高レベルのセキュリティを提供します。 この投稿の公開時点では、条件付きアクセスのトークン保護は、Microsoft Teams、SharePoint、Exchange に接続するネイティブ アプリケーションWindows使用できます。 プラットフォーム、アプリケーション、リソースの追加サポートを追加することで、トークン保護の範囲を拡大するために継続的に取り組んでいます。 サポートされているアプリとリソースの更新された一覧については、この記事を参照してください。 [Microsoft Entra 条件付きアクセスにおけるトークン保護](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-token-protection)。

組織は、サポートされているすべてのアプリケーション、デバイス、プラットフォームにトークン保護をパイロットして展開することをお勧めします。 トークン保護をサポートしていないアプリケーションは、ネットワーク ベースのポリシーなどの他のポリシーで保護する必要があります。

詳細については、次の記事を参照してください。デプロイ ガイダンスを取得します。 [トークン保護を構成する方法について説明](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-token-protection)します。

注

条件付きアクセスでのトークン保護には、PRT の使用が必要です。 登録されていないデバイスの使用などのシナリオは、これらのデバイスに PRT がないため利用できません。

注

Entra Token Protection は、デバイスにサインインしたユーザーにのみ適用されます。 たとえば、標準アカウントを使用してWindows デバイスのロックを解除した後、別のアカウントで認証されているリソースにアクセスする場合、有効な PRT がないため、後者の ID を Entra Token Protection で保護することはできません。

#### ネットワークベースの制御を実装する

Entra Token Protection はサインイン セッション トークンを保護する最も安全な方法ですが、アプリケーションの対象範囲は制限されており、デバイスにサインインしたユーザーにのみ適用されます。 攻撃対象領域をさらに減らすために、組織はネットワークベースの適用ポリシーを実装できます。これにより、多くの場合、すべてのエンタープライズ アプリを対象とする、より広範なアプリケーションをカバーできます。 ネットワーク ベースのポリシーでは、デバイスにサインインしているユーザー以外の追加の ID もカバーできます。

ネットワークベースのポリシーにより、サインイン セッションのアーティファクト (更新トークンなど) が指定されたネットワークの外部で再生されるのを防ぎ、トークンの盗難や、組織の境界を超えてサインイン セッションを流出させるリプレイ攻撃を効果的に防ぎます。 内部の脅威ベクトルは、同じネットワークへのアクセスによって引き続きリスクを引き起こす可能性がある一方で、脅威アクターが組織の境界内で動作することを強制すると、他のセキュリティ制御によって脅威を検出して軽減する可能性が大幅に高まります。

さらに、継続的アクセス評価をサポートするアプリケーションなどの特定のシナリオでは、これらの対策は、アクセス トークンのようなアプリケーション セッション トークンの盗難や再生を軽減する効果的な方法にもなります。

**グローバル セキュリティで保護されたアクセスを使用してサインイン セッションを保護する**

組織は、グローバル セキュリティで保護されたアクセスを展開して、クライアント デバイスとリソース (準拠ネットワークとも呼ばれます) の間にセキュリティで保護されたネットワーク接続を確立する必要があります。 管理者は、Entra ID と統合されたエンタープライズ アプリにアクセスするための準拠ネットワークの使用を義務付ける条件付きアクセス ポリシーを作成できます。 このメジャーにより、組織で管理されていないデバイスからのサインイン セッション成果物の再生が防止されます。

**従来のネットワーク 制御を使用してサインイン セッションを保護する**

準拠しているネットワーク チェックの代わりに、組織は VPN などの従来のネットワーク ソリューションを利用してサインイン セッションを保護できます。 管理者は、認証の試行を特定のエグレス IP アドレスに制限する場所ベースの条件付きアクセス ポリシーを作成できます。 ただし、組織は、企業ネットワークを介したトラフィックのルーティングに関連するパフォーマンスへの影響とコストを考慮する必要があります。 そのため、Microsoft では、完全にセキュリティで保護されたグローバル分散 Security Service Edge ソリューションである Global Secure Access を使用することをお勧めします。

[Entra ID を使用して場所ベースの条件付きアクセス ポリシーを構成する方法について説明します](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-block-by-location)。

**ネットワーク ベースの適用によるアプリ セッションの保護**

特定のエグレス IP アドレスへのアクセスを制限する場所ベースの条件付きアクセス ポリシーを作成することで、組織はアプリ セッションの一部を保護することもできます。 SharePoint Online や Exchange Online などの Microsoft アプリケーションのサブセットは、[Continuous Access Evaluation](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation) (CAE) プロトコルを使用します。 CAE 対応アプリは、ネットワーク ベースの適用を評価し、信頼されたネットワークの外部で再生されたアプリ セッション成果物をほぼリアルタイムで取り消します。 組織は、CAE 対応アプリのトラフィックに信頼されたネットワークからのみアクセスできるように [、CAE で厳密な場所ポリシーを構成](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation-strict-enforcement) することで、IP ベースのネットワークの適用をさらに強化できます。

CAE 対応ではないアプリケーションの場合、組織はアプリケーション側で使用可能なコントロールを使用してアプリ セッションを保護できます。 たとえば、一部のアプリケーションでは、ID プロバイダー (IdP) によって適用されるアプリケーションレイヤーに加えて、IP ベースの適用がサポートされています。 その後、アプリケーションは、信頼されたネットワークの外部で使用されているアプリ セッション成果物の使用を拒否します。 会社所有のネットワークを介したアプリ固有のトラフィックのトンネリングは、グローバル セキュア アクセスを使用したソース IP アンカーと、VPN などの他の従来のネットワーク ソリューションを使用して実現できます。

[グローバル セキュリティで保護されたアクセスを使用したソース IP アンカーについて説明します](https://learn.microsoft.com/ja-jp/entra/global-secure-access/source-ip-anchoring)。

### トークン保護戦略の概要

要約すると、Microsoft Entraでトークンを保護するには、トークンの盗難やリプレイ攻撃から保護するための多層防御戦略が含まれます。 これには、マルウェアに対するデバイスの強化、デバイスベースおよびリスクベースの条件付きアクセスの活用、デバイス バインド トークンの適用、ネットワーク ベースの強制の実装が含まれます。 さらに、組織はフィッシングに強い多要素認証を展開し、疑わしいサインイン試行を監視し、機密性の高い操作の再認証を要求するように条件付きアクセス ポリシーを構成する必要があります。 これらのガイドラインに従うことで、組織は不正アクセスのリスクを大幅に軽減し、サインイン セッションとアプリ セッションのセキュリティを確保できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/reference-device-registration-tls-1-2"} -->
## TLS 1.2 の適用 - Microsoft Entra Registration Service - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/reference-device-registration-tls-1-2
- Service: entra-id / devices
- Article date: 2025-06-27
- Summary: Microsoft Entra Device Registration Service の TLS 1.0 と 1.1 のサポートを削除する

Microsoft Entra Device Registration Service は、デバイス ID を使用してデバイスをクラウドに接続するために使用されます。 現在、Microsoft Entra Device Registration Service では、Azure との通信にトランスポート層セキュリティ (TLS) 1.2 の使用がサポートされています。 セキュリティとクラス最高の暗号化を確保するために、Microsoft では TLS 1.0 と 1.1 を無効にすることをお勧めします。 このドキュメントでは、登録を完了し、Microsoft Entra Device Registration Service と通信するために使用されるマシンが TLS 1.2 を使用していることを確認する方法について説明します。

TLS プロトコル バージョン 1.2 は、セキュリティで保護された通信を提供するように設計された暗号化プロトコルです。 TLS プロトコルは、主にプライバシーとデータの整合性を提供することを目的としています。 TLS では、RFC 5246 (外部リンク) [でバージョン 1.2 が定義](https://tools.ietf.org/html/rfc5246)多くのイテレーションが行われています。

現在の接続の分析では、TLS 1.1 と 1.0 の使用状況はほとんど示されていませんが、TLS 1.1 と 1.0 のサポートが終了する前に、必要に応じて影響を受けるすべてのクライアントまたはサーバーを更新できるように、この情報を提供しています。 ハイブリッド シナリオまたは Active Directory フェデレーション サービス (AD FS) にオンプレミスインフラストラクチャを使用している場合は、TLS 1.2 を使用する受信接続と送信接続の両方をインフラストラクチャでサポートできることを確認してください。

### Windows サーバーを更新する

Microsoft Entra Device Registration Service を使用するかプロキシとして機能する Windows サーバーの場合は、次の手順を使用して TLS 1.2 が有効になっていることを確認します。

大事な

レジストリを更新したら、Windows サーバーを再起動して変更を有効にする必要があります。

#### TLS 1.2 を有効にする

次のレジストリ文字列が次のように構成されていることを確認します。

- HKEY\_LOCAL\_MACHINE\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Client
    - "DisabledByDefault"=dword:00000000
    - "Enabled"=dword:00000001
- HKEY\_LOCAL\_MACHINE\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Server
    - "DisabledByDefault"=dword:00000000
    - "Enabled"=dword:00000001
- HKEY\_LOCAL\_MACHINE\SOFTWARE\Microsoft.NETFramework\v4.0.30319
    - "SchUseStrongCrypto"=dword:00000001

### Windows 以外のプロキシを更新する

デバイスと Microsoft Entra Device Registration Service の間のプロキシとして機能するすべてのマシンは、TLS 1.2 が有効になっていることを確認する必要があります。 サポートを確保するには、ベンダーのガイダンスに従ってください。

### AD FS サーバーを更新する

Microsoft Entra Device Registration Service との通信に使用される AD FS サーバーは、TLS 1.2 が有効になっていることを確認する必要があります。 この構成を有効または確認する方法については、AD FS [の SSL/TLS プロトコルと暗号スイートの管理に関する](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/manage-ssl-protocols-in-ad-fs) を参照してください。

### クライアントの更新

すべてのクライアントとサーバーとブラウザーとサーバーの組み合わせは、MICROSOFT Entra Device Registration Service に接続するために TLS 1.2 を使用する必要があるため、これらのデバイスの更新が必要になる場合があります。

次のクライアントは、TLS 1.2 をサポートできないことがわかされています。 クライアントを更新して、アクセスが中断されないようにします。

- Android バージョン 4.3 以前
- Firefox バージョン 5.0 以前
- Windows 7 以前の Internet Explorer バージョン 8 から 10
- Windows Phone 8.0 の Internet Explorer 10
- OS X 10.8.4 以前の Safari バージョン 6.0.4
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/sso-admin-control"} -->
## SSO プロンプトの管理者制御  - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/sso-admin-control
- Service: entra-id / devices
- Article date: 2026-07-14
- Summary: IT 管理者は、サポートされているレジストリ設定を使用して、マネージド Windows デバイスに対する SSO アクセス許可を自動的に受け入れることができるようになりました。

IT 管理者は、サポートされているレジストリ設定を使用して、マネージド Windows デバイスに対する **SSO アクセス許可を自動的に受け入れ**ることができるようになりました。 このコンテキストでは、SSO またはシングル サインオンとは、ユーザーのWindows サインインからのMicrosoft資格情報を使用して、プロンプトなしで他のMicrosoftアプリやサービスにアクセスすることを指します。 この新機能は、**2026 年 7 月の Windows 11** バージョン 24H2 および 25H2 用の月次セキュリティ更新プログラム ([2026 年 2026 年KB5101650](https://support.microsoft.com/en-us/servicing/os/windows-11/2026/07/july-14-2026-kb5101650-os-builds-26200-8875-and-26100-8875) セキュリティ更新プログラム) から入手できます。

Important

- **対象:**✅**Windows** **管理対象のエンタープライズ デバイス**（Microsoft Entra ID アカウントを使用するもの）にのみ適用されます
- **個人用アカウント:**❌管理者制御を使用できません - 個人用Microsoft アカウント (MSA) のプロンプトが残ります
- **アンマネージド デバイス:**❌ 管理者制御は使用できません。ポリシーが制御されていない環境の場合はプロンプトが表示されます
- **サポートされている OS:** Windows 11バージョン 24H2 および 25H2 ([2026 KB5101650](https://support.microsoft.com/en-us/servicing/os/windows-11/2026/07/july-14-2026-kb5101650-os-builds-26200-8875-and-26100-8875) セキュリティ更新プログラムを使用)

### 背景: 変更点とその理由

欧州経済地域 (EEA) では、Microsoft Windowsサインイン エクスペリエンスが更新され、ユーザーがWindowsにサインインした後に他のMicrosoftアプリケーションやサービスに自動的にサインインしないようにします。 代わりに、Windowsは、同じ資格情報を使用して追加のアプリまたはサービスにサインインするかどうかをユーザーに求めます。ユーザーは、Windows アカウントをサインインに使用する方法を選択できます。 参考までに、元の [ブログ投稿](https://techcommunity.microsoft.com/blog/windows-itpro-blog/upcoming-changes-to-windows-single-sign-on/4008151)を確認してください。

この通知は、Windowsにサインインした後、個人のMicrosoft アカウントまたは職場または学校のEntra IDを使用してサインインできるアプリをユーザーが初めて使用するときに表示されます。 ユーザーがWindowsへのサインインに使用したのと同じ資格情報を使用することを選択した場合、この通知は再び表示されません。

[Image: 画像は Microsoft Word を背景としており、その前面に拡大表示されたダイアログが重なって表示されています。ダイアログの上部には Microsoft ロゴがあり、2 行目には]

管理対象のエンタープライズ環境では、組織が既にサインイン ポリシーと信頼関係を管理しているデバイスで SSO プロンプト エクスペリエンスを管理する柔軟性を高めたいと考えていました。 これらのシナリオをサポートするために、IT 管理者が対象のマネージド Windows デバイスに対する SSO アクセス許可を自動的に受け入れることができるレジストリ ベースの制御を開発しました。

### サインイン動作のエンタープライズ管理者制御

IT 管理者は、Windows 11 バージョン 24H2 および 25H2 のセキュリティ更新プログラム (前述) 以降、次のレジストリ ポリシーを展開して、マネージド デバイスに対する SSO アクセス許可を自動的に受け入れることができます。

>
> **レジストリ パス:**HKLM\SOFTWARE\Policies\Microsoft\Windows\AAD**値：** AutoAcceptSsoPermission (DWORD) = 1

[Image: この画像には、Windows のレジストリ エディターが表示されています。最初の行には、]

### 作業の開始

SSO プロンプトに管理者コントロールを使用するには:

1. サポートされているWindowsバージョンがデバイスで実行されており、上記のセキュリティ更新プログラムが適用されていることを確認します。
2. このポリシーは、次の方法で展開できます。
    - グループ ポリシー (GPO)
    - Microsoft Intuneまたは同様のモバイル デバイス管理 (MDM) ツール
    - Microsoft Configuration Manager
    - レジストリ ポリシーの展開をサポートする任意の管理ツール
3. マネージド デバイスフリート全体の SSO 動作を検証します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/sso-linux"} -->
## Linux 用 Microsoft シングル サインオン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/sso-linux
- Service: entra-id / devices
- Article date: 2026-02-03
- Summary: Microsoft Entra ID の統合とシームレスな認証を可能にする Linux 用 Microsoft シングル サインオンの概要。

Linux 用 Microsoft シングル サインオン (SSO) は、Linux デバイスと Microsoft Entra ID を統合するソフトウェア コンポーネントである Microsoft Identity Broker を利用します。 このソリューションを使用すると、ユーザーは Microsoft Entra ID 資格情報を使用して 1 回認証し、認証プロンプトを繰り返さずに複数のアプリケーションとリソースにアクセスできます。 この機能により、ユーザーのサインイン プロセスが簡略化され、管理者のパスワード管理のオーバーヘッドが軽減されます。

### Features

この機能を使用すると、Linux デスクトップ クライアントのユーザーは、Microsoft Entra IDを使用してデバイスを参加させ、Intune 管理に登録し、企業リソースにアクセスするときにデバイスベースの条件付きアクセス ポリシーを満たすことができます。

- Microsoft Entra ID の登録と Linux デスクトップの登録を提供します
- ネイティブ アプリケーションと Web アプリケーション (Azure CLI、Microsoft Edge、Teams PWA など) が Microsoft 365 および Azure で保護されたリソースにアクセスするための SSO 機能を提供します
- MSAL for .NET または MSAL for Python を使用するアプリケーション間で Microsoft Entra アカウントに SSO を提供し、お客様が Microsoft Authentication Library (MSAL) を使用して SSO をカスタム アプリに統合できるようにします。
- Microsoft Edge 経由で Web アプリケーションを保護する条件付きアクセス ポリシーを有効にします
- 標準の Intune コンプライアンス ポリシーを有効にする
- カスタム コンプライアンス ポリシーの Bash スクリプトのサポートを有効にします

Teams Web アプリケーションと Linux 用プログレッシブ Web アプリ (PWA) は、Microsoft Intune を通じて適用される条件付きアクセス構成を使用して、Linux ユーザーが Microsoft Edge を使用して Teams にアクセスできるようにします。

### [前提条件]

#### サポートされているオペレーティングシステム

Linux 用 Microsoft シングル サインオンは、次のオペレーティング システム (x86/64 CPU を搭載した物理マシンまたは Hyper-V コンピューター) でサポートされています。

- Ubuntu Desktop 26.04 LTS (長期サポート)
- Ubuntu Desktop 24.04 LTS (長期サポート)
- Red Hat Enterprise Linux 9 (長期サポート)
- Red Hat Enterprise Linux 10 (長期サポート)

#### システム要件

- パッケージのインストールと Microsoft Entra ID 通信のためのインターネット接続
- インストールの管理特権
- デスクトップ環境 ([GNOME](https://www.gnome.org/)、 [KDE](https://kde.org/) など)

#### Microsoft Entra ID の要件

- Microsoft Entra ID テナント
- Microsoft Entra ID で同期または作成されたユーザー アカウント
- 条件付きアクセス ポリシーの適切なライセンス (該当する場合)
- Entra ポータルの `Device settings` では、ターゲット ユーザーに対して `Users may join devices to Microsoft Entra` を構成している必要があります。

[Image: ユーザーが Microsoft Entra を使用してデバイスに参加する場合がある、ユーザーに必要な設定に焦点を当てた Entra ポータルの [デバイス設定] ウィンドウのスクリーンショット。]

Important

Microsoft Linux バージョン 2.0.2 以降のシングル サインオンでは、デバイスの信頼にMicrosoft Entra参加が使用されます。 以前のバージョンでは、Microsoft Entra の登録を使用していました。

- その結果、バージョン 2.0.2 以降にアップグレードする組織は、影響を受けるユーザーが Microsoft Entra 管理センター &gt; Devices &gt; Device 設定の`Users may join devices to Microsoft Entra`設定で許可されていることを確認する必要があります。 これらのデバイスでは、 `Users may register their devices with Microsoft Entra` 設定では十分ではなくなりました。
- 詳細については、「Microsoft Entra IDでの[デバイス ID の管理」を](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-device-identities)参照してください。

### シングル サインオン (SSO) エクスペリエンス

次のアニメーションは、Linux 上のブローカー フローのサインイン エクスペリエンスを示しています。

## [パスワード認証](#tab/password-auth)
次のアニメーションに示すように、Linux でのパスワード認証の使用。

[Image: PRMFA での Linux サインイン エクスペリエンスのデモ。]

## [フィッシング対策MFA](#tab/prmfa-auth)
次のアニメーションに示すように、Linux で SmartCard 経由で PRMFA 認証を使用すると、シームレスなサインイン エクスペリエンスが提供されます。

[Image: Linux サインイン エクスペリエンスのデモ。]

---

注

`microsoft-identity-broker` バージョン 3.0.x 以前のバージョンでは、現在 [FIPS コンプライアンス](https://www.nist.gov/standardsgov/compliance-faqs-federal-information-processing-standards-fips)はサポートされていません。

### Installation

Microsoft シングル サインオン for Linux アプリ パッケージは、https://packages.microsoft.com/ で入手できます。 Microsoft製品の Linux ソフトウェア パッケージの使用方法、インストール方法、構成方法の詳細については、Microsoft製品の [Linux ソフトウェア リポジトリ](https://learn.microsoft.com/ja-jp/windows-server/administration/linux-package-repository-for-microsoft-software) を参照してください。

Linux 用の Microsoft Single Sign-On をインストールするサンプル スクリプトIntune アプリとそのデバイスへの依存関係は、[GitHub](https://go.microsoft.com/fwlink/?linkid=2358529) で利用できます。 インストールする前に、手順をよく確認してください。

コマンド ラインで次のコマンドを実行して、Microsoft シングル サインオン (microsoft-identity-broker) とその依存関係をデバイスに手動でインストールします。

## [Ubuntu](#tab/debian-install)
1. Curl をインストールします。

    ```bash
    sudo apt install curl gpg
    ```
2. Microsoft パッケージ署名キーをインストールします。

    ```bash
    # Ubuntu 26.04+ repos are signed with a newer Microsoft GPG key
    if dpkg --compare-versions "$RELEASE" ge "26.04"; then
        MS_GPG_KEY_URL="https://packages.microsoft.com/keys/microsoft-2025.asc"
    else
        MS_GPG_KEY_URL="https://packages.microsoft.com/keys/microsoft.asc"
    fi
    
    # Import Microsoft GPG key (always refresh to pick up key rotations)
    curl -fsSL "$MS_GPG_KEY_URL" | gpg --dearmor > "$HOME/microsoft.gpg"
    sudo install -o root -g root -m 644 "$HOME/microsoft.gpg" /usr/share/keyrings/
    rm -f "$HOME/microsoft.gpg"
    ```
3. システム リポジトリの一覧に Microsoft Linux リポジトリを追加して更新します。

    ```bash
    sudo sh -c 'echo "deb [arch=amd64 signed-by=/usr/share/keyrings/microsoft.gpg] https://packages.microsoft.com/ubuntu/$(lsb_release -rs)/prod $(lsb_release -cs) main" >> /etc/apt/sources.list.d/microsoft-ubuntu-$(lsb_release -cs)-prod.list'
    sudo apt update
    ```
4. Microsoft シングル サインオン (microsoft-identity-broker) アプリをインストールします。

    ```bash
    sudo apt install microsoft-identity-broker
    ```
5. デバイスを再起動します。

## [RHEL 8/9](#tab/redhat89-install-prod)
1. システム構成に応じて、前提条件となる可能性があります。

    ```bash
    # This fixes gpg key issues
    sudo dnf update redhat-release
    
    # Update to the latest release
    sudo dnf upgrade -y
    
    # RHEL 10 does not ship `webkitgtk6.0` in its default repos. You must enable EPEL first:
    sudo dnf install -y https://dl.fedoraproject.org/pub/epel/epel-release-latest-10.noarch.rpm
    ```
2. Microsoft リポジトリを追加します。

    ```bash
    sudo rpm --import https://packages.microsoft.com/keys/microsoft.asc
    sudo dnf install -y dnf-plugins-core
    sudo dnf config-manager --add-repo https://packages.microsoft.com/yumrepos/microsoft-rhel$(rpm -E %rhel).0-prod
    ```
3. Microsoft シングル サインオン (microsoft-identity-broker) アプリをインストールします。

    ```bash
    sudo dnf install microsoft-identity-broker
    ```
4. Microsoft シングル サインオン (microsoft-identity-broker) アプリをインストールします。

    ```bash
    sudo dnf update
    sudo dnf install microsoft-identity-broker
    ```
5. デバイスを再起動します。

## [RHEL 10](#tab/redhat10-install-prod)
1. システムの構成に応じて考えられる前提条件。

    ```bash
    # This fixes any gpg key issues
    sudo dnf update redhat-release
    
    # Update to the latest release
    sudo dnf upgrade -y
    
    # RHEL 10 does not ship `webkitgtk6.0` in its default repos. You must enable EPEL first:
    sudo dnf install -y https://dl.fedoraproject.org/pub/epel/epel-release-latest-10.noarch.rpm
    ```
2. Microsoft 運用パッケージ署名キーをインストールします。 RHEL 10 パッケージは、RHEL 8/9 に使用される `microsoft.asc` キーとは異なる新しい Microsoft GPG キー (RSA-4096) で署名されます。

    ```bash
    # Legacy key (needed for Edge)
    sudo rpm --import https://packages.microsoft.com/keys/microsoft.asc
    
    # New key for RHEL 10 packages
    sudo rpm --import https://packages.microsoft.com/rhel/10/prod/repodata/repomd.xml.key
    ```
3. 次に、次の内容を含む新しいリポジトリ ファイルを `/etc/yum.repos.d/` の下に作成して、リポジトリを追加します。

    ```bash
    sudo tee /etc/yum.repos.d/microsoft-prod.repo > /dev/null <<EOF
    [microsoft-prod]
    name=Microsoft prod - RHEL 10
    baseurl=https://packages.microsoft.com/rhel/10/prod
    enabled=1
    gpgcheck=1
    gpgkey=https://packages.microsoft.com/rhel/10/prod/repodata/repomd.xml.key
    EOF
    ```

---

### Microsoft Identity Broker を更新する

次のコマンドを実行して、Microsoft Identity Broker を手動で更新します。

## [Ubuntu](#tab/debian-update)
1. パッケージ リポジトリとメタデータを更新します。

    ```bash
    sudo apt update
    ```
2. Microsoft Identity Broker パッケージをアップグレードします。

    ```bash
    sudo apt upgrade microsoft-identity-broker
    ```

## [Red Hat Enterprise Linux](#tab/redhat-update)
次のいずれかのコマンドを実行して、Microsoft Identity Broker を更新します。

- 最新の Microsoft Identity Broker パッケージを含む最新リリースに更新します。

    ```bash
    sudo dnf update
    ```
- Microsoft Identity Broker パッケージのみをアップグレードします。

    ```bash
    sudo dnf update microsoft-identity-broker
    ```

---

### Microsoft Identity Broker をアンインストールする

次のコマンドを実行して、Microsoft Identity Broker をアンインストールし、ローカル構成データを削除します。

## [Ubuntu](#tab/debian-uninstall)
1. システムから Microsoft Identity Broker を削除します。

    ```bash
    sudo apt remove microsoft-identity-broker
    ```
2. ローカル構成データを削除します。

    ```bash
    sudo apt purge intune-portal
    sudo apt purge microsoft-identity-broker
    ```

## [Red Hat Enterprise Linux](#tab/redhat-uninstall)
次のコマンドを実行して、Microsoft Identity Broker をアンインストールし、Red Hat Enterprise Linux 上のローカル登録データを削除します。

1. Microsoft Identity Broker パッケージを削除します。

    ```bash
    sudo dnf remove microsoft-identity-broker
    ```
2. ローカル登録データを削除します。

    ```bash
    sudo rm -rf /var/opt/microsoft/identity-broker
    sudo rm -rf /etc/opt/microsoft/identity-broker
    sudo rm -rf /opt/microsoft/identity-broker
    ```

---

Warnung

Microsoft Identity Broker をアンインストールしても、デバイスがMicrosoft Entra IDから自動的に参加解除されることはなく、Intune 管理からデバイスの登録が解除されることはありません。 デバイスの登録を削除するには、 [dsregcmd ツール](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-device-registration-tool-linux) を使用するか、Microsoft Entra ID ポータルからデバイスを削除します。

### dsregc を使用してデバイスの参加を解除する

`microsoft-identity-broker`の 2.5.x のリリースでは、Microsoft Entra ID を使用してデバイスの登録を管理できる、`dsreg` ツールと呼ばれる新しいユーティリティが追加されました。

`dsreg` ツールを使用してデバイスの Microsoft Entra ID への参加を解除するには、ターミナルで次のコマンドを実行します。その際、`<tenant-guid>` は Microsoft Entra ID テナントの GUID に置き換えてください。

```bash
sudo dsreg --tenant-id <tenant-guid> --unregister
```

システムの状態が悪くなり、すべてのローカル登録データとキー マテリアルをクリーンアップする場合は、`--cleanup` ツールで `dsreg` オプションを使用できます。 このユーティリティ モードは、Microsoft Identity Broker のすべてのローカル トレースをデバイスから確実に削除する必要があるシナリオ (新しいユーザーのトラブルシューティングや準備時など) に役立ちます。

dsreg ツールを使用してキー マテリアルの結合を解除して削除するには、ターミナルで次のコマンドを実行します。

```bash
# Clean broker state including certificates (requires sudo)
sudo dsreg --cleanup
```

Warnung

`--cleanup` オプションは元に戻すことができません。デバイスからすべてのキー マテリアルが削除されます。 注意して使用してください。

### Linux デバイスでの Phish-Resistant MFA (PRMFA) の有効化

microsoft-identity-broker のバージョン `2.0.2` 以降では、次を使用して Linux デバイスで Phish-Resistant MFA (PRMFA) がサポートされています。

- SmartCard
- 証明書ベースの認証 (CBA)
- PIV/スマートカード アプレットを含む USB トークン

スマート カード統合は、次のディストリビューションでのみサポートされています。

- Ubuntu Desktop 24.04 LTS (長期サポート)
- Ubuntu Desktop 22.04 LTS (長期サポート)
- Red Hat Enterprise Linux 10 (長期サポート)

証明書ベースのクライアント認証は、Secure Sockets Layer (TLS/SSL) プロトコルを使用して実装されます。 このプロセスでは、クライアントは秘密キーを使用してランダムに生成されたデータ ブロックに署名し、証明書と署名されたデータの両方をサーバーに送信します。 サーバーは署名を確認し、アクセス権を付与する前に証明書を検証します。

Certificate-Based 認証 (CBA) を構成する最も簡単な方法は、Linux デバイスにユーザー証明書を発行する秘密キー 基盤 (PKI) ソリューションを使用することです。 これらの証明書は、Microsoft Entra ID に対する認証に使用できます。 これらの証明書を認証用に受け入れるように Linux を構成するには、通常、適切な証明書ストアを設定し、これらの証明書を使用するようにシステムの認証メカニズムが構成されていることを確認する必要があります。

#### スマート カード認証

スマート カード認証は、ユーザー証明書を格納する物理トークンを導入することで、証明書ベースの方法を拡張します。 カードがリーダーに挿入されると、システムは証明書を取得して検証を実行します。

SmartCard のサポートを構成するには、物理トークンを使用して証明書ベースの認証を有効にするために必要なライブラリとモジュールを設定する必要があります。 YubiKey など、さまざまな Linux ディストリビューションと統合できるさまざまな SmartCard ソリューションがあります。 サポートされている 2 つのプラットフォームの手順については、ディストリビューションのドキュメントを参照してください。

- [Ubuntu SmartCard の構成](https://documentation.ubuntu.com/server/how-to/security/smart-card-authentication/)
- [Red Hat Enterprise Linux SmartCard の構成](https://docs.redhat.com/en/documentation/red_hat_enterprise_linux/9/html/managing_smart_card_authentication/index)
- [YubiKey SmartCard の構成](https://developers.yubico.com/pam-u2f/)
- [OpenSC SmartCard の構成](https://github.com/OpenSC/OpenSC/wiki)
- [PKCS#11 構成リファレンス](https://p11-glue.github.io/p11-glue/p11-kit/manual/pkcs11-conf.html)

#### スマート カード構成の例

次の手順では、YubiKey/Edge ブリッジ統合を使用する参照例を構成しますが、他のスマート カード プロバイダーも同様に構成できます。 これは単なる構成の例であり、構成はプロバイダーによって異なる場合があります。 特定の構成手順については、スマート カード プロバイダーのドキュメントを参照してください。

## [Ubuntu](#tab/debian-sc-example)
1. スマート カード ドライバーと YubiKey のサポートをインストールします。

    ```bash
    sudo apt install pcscd yubikey-manager
    ```
2. YubiKey/Edge Bridge コンポーネントをインストールします。

    ```bash
    sudo apt install opensc libnss3-tools openssl
    ```
3. 現在のユーザーのネットワーク セキュリティ サービス (NSS) データベースを構成します。

    ```bash
    mkdir -p $HOME/.pki/nssdb
    chmod 700 $HOME/.pki
    chmod 700 $HOME/.pki/nssdb
    modutil -force -create -dbdir sql:$HOME/.pki/nssdb
    modutil -force -dbdir sql:$HOME/.pki/nssdb -add 'SC Module' -libfile /usr/lib/x86_64-linux-gnu/pkcs11/opensc-pkcs11.so
    ```

## [RHEL 10](#tab/rhel-sc-example)
1. スマート カード ドライバーのサポートをインストールします。 注: これらのパッケージのほとんどは、microsoft-identity-broker の依存関係として既にインストールされている可能性がありますが、インストールされていない場合は、次のコマンドを使用してインストールできます。

    ```bash
    sudo dnf install -y pcsc-lite pcsc-lite-libs opensc nss-tools p11-kit p11-kit-devel
    ```
2. pcscd サービスがリーダーからスマート カードを読み取り、現在のユーザーのネットワーク セキュリティ サービス (NSS) データベースを初期化できるようにします。

    ```bash
    sudo systemctl enable --now pcscd
    
    mkdir -p $HOME/.pki/nssdb
    certutil -d sql:$HOME/.pki/nssdb -N --empty-password
    ```

---

Important

Microsoftでは、サポートされているバージョンのMicrosoft Authentication Library (MSAL) を通じて、`Microsoft Single Sign-on for Linux`とのアプリケーション統合をサポートしています。

MSAL をバイパスし、ブローカー実装インターフェイスまたは内部通信チャネルと直接通信するアプリケーションはサポートされていません。 これらのインターフェイスは実装の詳細であり、リリース間で予告なしに変更される可能性があります。

詳細については、[Microsoft Authentication Library (MSAL) の概要](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-overview)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/troubleshoot-device-dsregcmd"} -->
## dsregcmd コマンドを使用したデバイスのトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-device-dsregcmd
- Service: entra-id / devices
- Article date: 2025-06-27
- Summary: この記事では、dsregcmd コマンドからの出力を使用して、Microsoft Entra ID 内のデバイスの状態を理解する方法について説明します。

この記事では、 `dsregcmd` コマンドからの出力を使用して、Microsoft Entra ID 内のデバイスの状態を理解する方法について説明します。 `dsregcmd /status` ユーティリティをドメイン ユーザー アカウントとして実行します。

### デバイスの状態

このセクションでは、デバイス結合状態パラメーターの一覧を示します。 デバイスがさまざまな結合状態にするために必要な条件を次の表に示します。

| AzureAdJoined | EnterpriseJoined | DomainJoined | デバイスの状態 |
| --- | --- | --- | --- |
| はい | いいえ | いいえ | Microsoft Entra 参加済み |
| いいえ | いいえ | はい | ドメイン参加済み |
| はい | いいえ | はい | Microsoft Entra ハイブリッド参加済み |
| いいえ | はい | はい | オンプレミス DRS 参加済み |

注

社内参加済み (Microsoft Entra 登録済み) の状態は、[User State] セクションに表示されます。

- **AzureAdJoined**: デバイスが Microsoft Entra ID に参加している場合は、状態を *YES* に設定します。 それ以外の場合は、状態を *NO* に設定します。
- **EnterpriseJoined**: デバイスがオンプレミス Active Directory (AD) に参加している場合は、状態を YES に設定します。 デバイスを EnterpriseJoined と AzureAdJoined の両方にすることはできません。
- **DomainJoined**: デバイスがドメイン (Active Directory) に参加している場合は、状態を *YES* に設定します。
- **DomainName**: デバイスがドメインに参加している場合は、状態をドメインの名前に設定します。

#### デバイスの状態出力のサンプル

```
+----------------------------------------------------------------------+
| Device State                                                         |
+----------------------------------------------------------------------+
             AzureAdJoined : YES
          EnterpriseJoined : NO
              DomainJoined : YES
                DomainName : HYBRIDADFS
+----------------------------------------------------------------------+
```

### デバイスの詳細

状態は、デバイスが Microsoft Entra 参加済みまたは Microsoft Entra ハイブリッド参加済みであり、Microsoft Entra が登録されていない場合にのみ表示されます。 このセクションでは、Microsoft Entra ID に格納されているデバイス識別の詳細を一覧表示します。

- **DeviceId**: Microsoft Entra テナント内のデバイスの一意の ID。
- **拇印**: デバイス証明書の拇印。
- **DeviceCertificateValidity**: デバイス証明書の有効性の状態。
- **KeyContainerId**: デバイス証明書に関連付けられているデバイス秘密キーの containerId。
- **KeyProvider**: デバイスの秘密キーを格納するために使用される KeyProvider (ハードウェア/ソフトウェア)。
- **TpmProtected**: デバイスの秘密キーがハードウェアのトラステッド プラットフォーム モジュール (TPM) に格納されている場合、状態は *YES* に設定されます。
- **DeviceAuthStatus**: Microsoft Entra ID でデバイスの正常性を判断するためのチェックを実行します。 正常性状態は次のとおりです。
    - デバイスが存在し、Microsoft Entra ID で有効になっている場合は *SUCCESS*。
    - *失敗 しました。 デバイスが無効または削除されている* デバイスが無効または削除されている場合は〘。 この問題について詳しくは、「[Microsoft Entra デバイス管理の FAQ](https://learn.microsoft.com/ja-jp/entra/identity/devices/faq#why-do-my-users-see-an-error-message-saying--your-organization-has-deleted-the-device--or--your-organization-has-disabled-the-device--on-their-windows-10-11-devices)」を参照してください。
    - *失敗 しました。 エラー* テストを実行できなかった場合。 このテストでは、システム コンテキストの下で Microsoft Entra ID へのネットワーク接続が必要です。

    注

    **DeviceAuthStatus** フィールドは、Windows 10 May 2021 更新プログラム (バージョン 21H1) に追加されました。
- **仮想デスクトップ**: この行が表示されるケースは 3 つあります。
    - NOT SET - VDI デバイス メタデータがデバイスに存在しません。
    - YES - VDI デバイス メタデータが存在し、dsregcmd は次のような関連メタデータを出力します。
        - プロバイダー: VDI ベンダーの名前。
        - 種類: 永続的なVDIまたは非永続的なVDI。
        - ユーザー モード: シングル ユーザーまたはマルチユーザー。
        - 拡張機能: オプションのベンダー固有のメタデータ内のキー値ペアの数と、その後に続くキー値ペア。
    - INVALID - VDI デバイス メタデータは存在しますが、正しく設定されていません。 この場合、dsregcmd は正しくないメタデータを出力します。

#### デバイスの詳細出力のサンプル

```
+----------------------------------------------------------------------+
| Device Details                                                       |
+----------------------------------------------------------------------+

                  DeviceId : 00aa00aa-bb11-cc22-dd33-44ee44ee44ee
                Thumbprint : AA11BB22CC33DD44EE55FF66AA77BB88CC99DD00
 DeviceCertificateValidity : [ 2019-01-11 21:02:50.000 UTC -- 2029-01-11 21:32:50.000 UTC ]
            KeyContainerId : 00aa00aa-bb11-cc22-dd33-44ee44ee44ee
               KeyProvider : Microsoft Software Key Storage Provider
              TpmProtected : NO
          DeviceAuthStatus : SUCCESS
+----------------------------------------------------------------------+
```

### テナントの詳細

テナントの詳細は、デバイスが Microsoft Entra 参加済みまたは Microsoft Entra ハイブリッド参加済みであり、Microsoft Entra が登録されていない場合にのみ表示されます。 このセクションでは、デバイスが Microsoft Entra ID に参加したときに表示される一般的なテナントの詳細を示します。

注

このセクションのモバイル デバイス管理 (MDM) URL フィールドが空の場合は、MDM が構成されていないか、現在のユーザーが MDM 登録のスコープ内にないことを示します。 Microsoft Entra ID のモビリティ設定を確認して、MDM の構成を確認します。

MDM URL が存在しても、デバイスが MDM によって管理されるとは限りません。 デバイス自体が管理されていない場合でも、テナントに自動登録用の MDM 構成がある場合は、情報が表示されます。

#### テナントの詳細の出力例

```
+----------------------------------------------------------------------+
| Tenant Details                                                       |
+----------------------------------------------------------------------+

                TenantName : HybridADFS
                  TenantId : aaaabbbb-0000-cccc-1111-dddd2222eeee
                       Idp : login.windows.net
               AuthCodeUrl : https://login.microsoftonline.com/aaaabbbb-0000-cccc-1111-dddd2222eeee/oauth2/authorize
            AccessTokenUrl : https://login.microsoftonline.com/aaaabbbb-0000-cccc-1111-dddd2222eeee/oauth2/token
                    MdmUrl : https://enrollment.manage-beta.microsoft.com/EnrollmentServer/Discovery.svc
                 MdmTouUrl : https://portal.manage-beta.microsoft.com/TermsOfUse.aspx
          MdmComplianceUrl : https://portal.manage-beta.microsoft.com/?portalAction=Compliance
               SettingsUrl : eyJVx{lots of characters}xxxx==
            JoinSrvVersion : 1.0
                JoinSrvUrl : https://enterpriseregistration.windows.net/EnrollmentServer/device/
                 JoinSrvId : urn:ms-drs:enterpriseregistration.windows.net
             KeySrvVersion : 1.0
                 KeySrvUrl : https://enterpriseregistration.windows.net/EnrollmentServer/key/
                  KeySrvId : urn:ms-drs:enterpriseregistration.windows.net
        WebAuthNSrvVersion : 1.0
            WebAuthNSrvUrl : https://enterpriseregistration.windows.net/webauthn/aaaabbbb-0000-cccc-1111-dddd2222eeee/
             WebAuthNSrvId : urn:ms-drs:enterpriseregistration.windows.net
    DeviceManagementSrvVer : 1.0
    DeviceManagementSrvUrl : https://enterpriseregistration.windows.net/manage/aaaabbbb-0000-cccc-1111-dddd2222eeee/
     DeviceManagementSrvId : urn:ms-drs:enterpriseregistration.windows.net
+----------------------------------------------------------------------+
```

### ユーザー状態

このセクションでは、現在デバイスにログインしているユーザーのさまざまな属性の状態を示します。

注

有効な状態を取得するには、コマンドをユーザー コンテキストで実行する必要があります。

- **NgcSet**: 現在ログインしているユーザーに Windows Hello キーが設定されている場合は、状態を *YES* に設定します。
- **NgcKeyId**: 現在ログインしているユーザーに対して Windows Hello キーが設定されている場合の ID。
- **CanReset**: ユーザーが Windows Hello キーをリセットできるかどうかを示します。
- **指定できる値**: DestructiveOnly、NonDestructiveOnly、DestructiveAndNonDestructive、またはエラーの場合はUnknown。
- **WorkplaceJoined**: Microsoft Entra 登録済みアカウントが現在の NTUSER コンテキストでデバイスに追加されている場合は、状態を *YES* に設定します。
- **WamDefaultSet**: ログインしているユーザーに対して Web アカウント マネージャー (WAM) の既定の WebAccount が作成された場合、状態を *YES* に設定します。 管理者特権でのコマンド プロンプトから `dsregcmd /status` を実行すると、このフィールドにエラーが表示される可能性があります。
- **WamDefaultAuthority**: Microsoft Entra ID の *組織* に状態を設定します。
- **WamDefaultId**: Microsoft Entra ID には常に *https://login.microsoft.com* を使用します。
- **WamDefaultGUID**: 既定の WAM WebAccount の WAM プロバイダー (Microsoft Entra ID/Microsoft アカウント) GUID。

#### ユーザー状態の出力のサンプル

```
+----------------------------------------------------------------------+
| User State                                                           |
+----------------------------------------------------------------------+

                    NgcSet : YES
                  NgcKeyId : {aaaaaaaa-0b0b-1c1c-2d2d-333333333333}
                  CanReset : DestructiveAndNonDestructive
           WorkplaceJoined : NO
             WamDefaultSet : YES
       WamDefaultAuthority : organizations
              WamDefaultId : https://login.microsoft.com
            WamDefaultGUID : { B16898C6-A148-4967-9171-64D755DA8520 } (AzureAd)

+----------------------------------------------------------------------+
```

### SSO の状態

Microsoft Entra 登録済みデバイスの場合、このセクションは無視してかまいません。

注

このコマンドは、そのユーザーの有効な状態を取得するために、ユーザー コンテキストで実行する必要があります。

- **AzureAdPrt**: ログインしているユーザーのデバイスにプライマリ更新トークン (PRT) が存在する場合は、状態を *YES* に設定します。
- **AzureAdPrtUpdateTime**: [PRT が最後に更新された](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token#how-is-a-prt-renewed)時刻 (協定世界時 (UTC) で状態を設定します。
- **AzureAdPrtExpiryTime**: PRT が更新されない場合に有効期限が切れる時刻 (UTC) に状態を設定します。
- **AzureAdPrtAuthority**: Microsoft Entra 機関の URL
- **EnterprisePrt**: デバイスにオンプレミスの Active Directory フェデレーション サービス (AD FS) からの PRT がある場合は、状態を *YES* に設定します。 Microsoft Entra ハイブリッド参加済みデバイスの場合、デバイスは Microsoft Entra ID とオンプレミス Active Directory の両方から同時に PRT を持つことができます。 オンプレミスの参加済みデバイスでは、エンタープライズ PRT のみ指定されています。
- **EnterprisePrtUpdateTime**: Enterprise PRT が最後に更新された時刻 (UTC) に状態を設定します。
- **EnterprisePrtExpiryTime**: PRT が更新されない場合に有効期限が切れる時刻 (UTC) に状態を設定します。
- **EnterprisePrtAuthority**: AD FS 機関の URL

注

Windows 10 May 2021 更新プログラム (バージョン 21H1) では、次の PRT 診断フィールドが追加されました。

- **AzureAdPrt** フィールドに表示される診断情報は Microsoft Entra PRT の取得または更新用であり、[**EnterprisePrt**] フィールドに表示される診断情報は Enterprise PRT の取得または更新用です。
- 診断情報は、前回成功した PRT 更新時刻 (AzureAdPrtUpdateTime/EnterprisePrtUpdateTime) の後に取得または更新エラーが発生した場合にのみ表示されます。 共有デバイスでは、この診断情報は別のユーザーのログイン試行から取得される可能性があります。

- **AcquirePrtDiagnostics**: 取得した PRT 診断情報がログに存在する場合は、状態を *PRESENT*に設定します。
    - 診断情報が使用できない場合、このフィールドはスキップされます。
- **前の Prt 試行**: 失敗した PRT 試行が発生した現地時刻 (UTC)。
- **試行の状態**: 返されるクライアント エラー コード (HRESULT)。
- **ユーザー ID**: PRT 試行が発生したユーザーの UPN。
- **資格情報の種類**: PRT の取得または更新に使用される資格情報。 一般的な資格情報の種類は、パスワードと次世代資格情報 (NGC) です (Windows Hello の場合)。
- **関連付け ID**: 失敗した PRT 試行に対してサーバーによって送信された関連付け ID。
- **エンドポイント URI**: 失敗前にアクセスされた最後のエンドポイント。
- **HTTP メソッド**: エンドポイントへのアクセスに使用される HTTP メソッド。
- **HTTP エラー**: WinHttp トランスポート エラー コード。 その他の [ネットワーク エラー コード](https://learn.microsoft.com/ja-jp/windows/win32/winhttp/error-messages)を取得します。
- **HTTP 状態**: エンドポイントによって返される HTTP 状態。
- **サーバー エラー コード**: サーバーからのエラー コード。
- **サーバー エラーの説明**: サーバーからのエラー メッセージ。
- **RefreshPrtDiagnostics**: 取得した PRT 診断情報がログに存在する場合は、状態を *PRESENT*に設定します。
    - 診断情報が使用できない場合、このフィールドはスキップされます。
    - 診断情報フィールドは **AcquirePrtDiagnostics** と同じです。

注

Windows 11 (バージョン 21H2) の元のリリースでは、次の Cloud Kerberos 診断フィールドが追加されました。

- **OnPremTgt**: オンプレミス リソースにアクセスするための Cloud Kerberos チケットが、ログインユーザーのデバイスに存在する場合は、状態を *YES* に設定します。
- **CloudTgt**: クラウド リソースにアクセスするための Cloud Kerberos チケットがログイン ユーザーのデバイスに存在する場合は、状態を *YES* に設定します。
- **KerbTopLevelNames**: Cloud Kerberos の最上位レベルの Kerberos 領域名の一覧。

#### SSO 状態の出力のサンプル

```
+----------------------------------------------------------------------+
| SSO State                                                            |
+----------------------------------------------------------------------+

                AzureAdPrt : NO
       AzureAdPrtAuthority : https://login.microsoftonline.com/aaaabbbb-0000-cccc-1111-dddd2222eeee
     AcquirePrtDiagnostics : PRESENT
      Previous Prt Attempt : 2020-07-18 20:10:33.789 UTC
            Attempt Status : 0xc000006d
             User Identity : john@contoso.com
           Credential Type : Password
            Correlation ID : aaaa0000-bb11-2222-33cc-444444dddddd
              Endpoint URI : https://login.microsoftonline.com/aaaabbbb-0000-cccc-1111-dddd2222eeee/oauth2/token/
               HTTP Method : POST
                HTTP Error : 0x0
               HTTP status : 400
         Server Error Code : invalid_grant
  Server Error Description : AADSTS50126: Error validating credentials due to invalid username or password.
             EnterprisePrt : YES
   EnterprisePrtUpdateTime : 2019-01-24 19:15:33.000 UTC
   EnterprisePrtExpiryTime : 2019-02-07 19:15:33.000 UTC
    EnterprisePrtAuthority : https://fs.hybridadfs.nttest.microsoft.com:443/adfs
                 OnPremTgt : YES
                  CloudTgt : YES
         KerbTopLevelNames : .windows.net,.windows.net:1433,.windows.net:3342,.azure.net,.azure.net:1433,.azure.net:3342

+----------------------------------------------------------------------+
```

### 診断データ

#### 参加前の診断

この診断セクションは、デバイスがドメインに参加していて、Microsoft Entra ハイブリッド参加ができない場合にのみ表示されます。

このセクションでは、結合エラーの診断に役立つさまざまなテストを実行します。 情報には、エラー フェーズ、エラー コード、サーバー要求 ID、サーバー応答 HTTP 状態、サーバー応答エラー メッセージが含まれます。

- **ユーザー コンテキスト**: 診断が実行されるコンテキスト。 使用可能な値: SYSTEM、UN-ELEVATED User、ELEVATED User。

    注

    実際の結合は SYSTEM コンテキストで実行されるため、SYSTEM コンテキストでの診断の実行は、実際の結合シナリオに最も近い方法です。 SYSTEM コンテキストで診断を実行するには、 `dsregcmd /status` コマンドを管理者特権のコマンド プロンプトから実行する必要があります。
- **クライアント時刻**: システム時刻 (UTC)。
- **AD 接続テスト**: このテストでは、ドメイン コントローラーへの接続テストを実行します。 このテストでエラーが発生すると、事前チェック フェーズで結合エラーが発生する可能性があります。
- **AD 構成テスト**: このテストでは、サービス接続ポイント (SCP) オブジェクトがオンプレミスの Active Directory フォレストで正しく構成されているかどうかを読み取り、検証します。 このテストでエラーが発生すると、検出フェーズで結合エラーが発生し、エラー コードが0x801c001d可能性があります。
- **DRS 検出テスト**: このテストでは、検出メタデータ エンドポイントから DRS エンドポイントを取得し、ユーザー領域要求を実行します。 このテストでエラーが発生すると、検出フェーズで結合エラーが発生する可能性があります。
- **DRS 接続テスト**: このテストでは、DRS エンドポイントへの基本的な接続テストを実行します。
- **トークン取得テスト**: このテストでは、ユーザー テナントがフェデレーションされている場合に Microsoft Entra 認証トークンを取得しようとします。 このテストでエラーが発生すると、認証フェーズで結合エラーが発生する可能性があります。 認証に失敗した場合、次のレジストリ キー設定でフォールバックが明示的に無効にされていない限り、同期結合はフォールバックとして試行されます。

    ```
    Keyname: Computer\HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\CDJ
    Value: FallbackToSyncJoin
    Type:  REG_DWORD
    Value: 0x0 -> Disabled
    Value: 0x1 -> Enabled
    Default (No Key): Enabled
    ```
- **Sync-Joinへのフォールバック**: 認証エラーで同期結合へのフォールバックを防ぐための前のレジストリキーが*存在しない*場合は、状態を*有効*に設定します。 このオプションは、Windows 10 1803 以降から使用できます。
- **前回の登録**: 前回の結合試行が発生した時刻。 失敗した結合の試行のみがログに記録されます。
- **エラー フェーズ**: 中止された結合のステージ。 使用可能な値は、 *事前チェック*、 *検出*、 *認証*、 *結合です*。
- **クライアント エラー コード**: 返されたクライアント エラー コード (HRESULT)。
- **サーバー エラー コード**: サーバーに要求が送信され、サーバーがエラー コードで応答した場合に表示されるサーバー エラー コード。
- **サーバー メッセージ**: エラー コードと共に返されたサーバー メッセージ。
- **Https 状態**: サーバーによって返される HTTP 状態。
- **要求 ID**: クライアントのrequestIdがサーバーに送信されました。 要求 ID は、サーバー側のログと関連付けるのに役立ちます。

#### 結合前診断出力のサンプル

次の例は、検出エラーで失敗した診断テストを示しています。

```
+----------------------------------------------------------------------+
| Diagnostic Data                                                       |
+----------------------------------------------------------------------+

     Diagnostics Reference : www.microsoft.com/aadjerrors
              User Context : SYSTEM
               Client Time : 2019-01-31 09:25:31.000 UTC
      AD Connectivity Test : PASS
     AD Configuration Test : PASS
        DRS Discovery Test : FAIL [0x801c0021/0x801c000c]
     DRS Connectivity Test : SKIPPED
    Token acquisition Test : SKIPPED
     Fallback to Sync-Join : ENABLED

     Previous Registration : 2019-01-31 09:23:30.000 UTC
               Error Phase : discover
          Client ErrorCode : 0x801c0021

+----------------------------------------------------------------------+
```

次の例は、診断テストは成功しているが、登録の試行がディレクトリ エラーで失敗したことを示しています。これは同期結合で想定されています。 Microsoft Entra Connect 同期ジョブが完了すると、デバイスは参加できるようになります。

```
+----------------------------------------------------------------------+
| Diagnostic Data                                                       |
+----------------------------------------------------------------------+

     Diagnostics Reference : www.microsoft.com/aadjerrors
              User Context : SYSTEM
               Client Time : 2019-01-31 09:16:50.000 UTC
      AD Connectivity Test : PASS
     AD Configuration Test : PASS
        DRS Discovery Test : PASS
     DRS Connectivity Test : PASS
    Token acquisition Test : PASS
     Fallback to Sync-Join : ENABLED

     Previous Registration : 2019-01-31 09:16:43.000 UTC
         Registration Type : sync
               Error Phase : join
          Client ErrorCode : 0x801c03f2
          Server ErrorCode : DirectoryError
            Server Message : The device object by the given id (aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb) isn't found.
              Https Status : 400
                Request Id : 6bff0bd9-820b-484b-ab20-2a4f7b76c58e

+----------------------------------------------------------------------+
```

#### 結合後の診断

この診断セクションには、クラウドに参加しているデバイスで実行されたチェックの出力が表示されます。

- **AadRecoveryEnabled**: 値が *YES* の場合、デバイスに格納されているキーは使用できず、デバイスは復旧対象としてマークされます。 次のサインインによって回復フローがトリガーされ、デバイスが再登録されます。
- **KeySignTest**: 値が *PASSED の*場合、デバイス キーの正常性は良好です。 KeySignTest が失敗した場合、通常、デバイスは復旧のマークが付けられます。 次のサインインによって回復フローがトリガーされ、デバイスが再登録されます。 Microsoft Entra ハイブリッド参加済みデバイスの場合、復旧はサイレントです。 デバイスは Microsoft Entra に参加しているか、Microsoft Entra に登録されていますが、必要に応じて、デバイスを回復して再登録するためのユーザー認証を求められます。
    注

    KeySignTest には昇格された特権が必要です。

##### 結合後の診断出力のサンプル

```
+----------------------------------------------------------------------+
| Diagnostic Data                                                      |
+----------------------------------------------------------------------+

         AadRecoveryEnabled: NO
               KeySignTest : PASSED
+----------------------------------------------------------------------+
```

### NGC の前提条件の確認

この診断セクションでは、Windows Hello for Business (WHFB) を設定するための前提条件チェックを実行します。

注

ユーザーが WHFB を正常に構成した場合、 `dsregcmd /status` に NGC の前提条件チェックの詳細が表示されない場合があります。

- **IsDeviceJoined**: デバイスが Microsoft Entra ID に参加している場合は、状態を *YES* に設定します。
- **IsUserAzureAD**: ログインしているユーザーが Microsoft Entra ID に存在する場合は、状態を *YES* に設定します。
- **PolicyEnabled**: デバイスで WHFB ポリシーが有効になっている場合は、状態を *YES* に設定します。
- **PostLogonEnabled**: WHFB 登録がプラットフォームによってネイティブにトリガーされる場合、状態を *YES* に設定します。 状態が *NO* に設定されている場合は、Windows Hello for Business 登録がカスタム メカニズムによってトリガーされることを示します。
- **DeviceEligible: デバイス**が WHFB に登録するためのハードウェア要件を満たしている場合は、状態を *YES* に設定します。
- **SessionIsNotRemote**: 現在のユーザーがリモートではなくデバイスに直接ログインしている場合は、状態を *YES* に設定します。
- **CertEnrollment**: この設定は WHFB 証明書信頼の展開に固有であり、WHFB の証明書登録機関を示します。 WHFB ポリシーのソースがグループ ポリシーの場合は状態を *登録機関* に設定し、ソースが MDM の場合は *モバイル デバイス管理* に設定します。 どちらのソースも適用されない場合は、状態を *none* に設定します。
- **AdfsRefreshToken**: この設定は WHFB 証明書信頼の展開に固有であり、CertEnrollment 状態が *登録機関*の場合にのみ表示されます。 この設定は、デバイスにユーザーのエンタープライズ PRT があるかどうかを示します。
- **AdfsRaIsReady**: この設定は WHFB 証明書信頼の展開に固有であり、CertEnrollment 状態が *登録機関*の場合にのみ表示されます。 AD FS が検出メタデータで WHFB をサポートし、ログオン証明書テンプレートを使用できる場合は*、*状態を *YES* に設定します。
- **LogonCertTemplateReady**: この設定は WHFB 証明書信頼の展開に固有であり、CertEnrollment 状態が *登録機関*の場合にのみ表示されます。 ログイン証明書テンプレートの状態が有効であり、AD FS 登録機関 (RA) のトラブルシューティングに役立つ場合は、状態を *YES* に設定します。
- **PreReqResult**: すべての WHFB 前提条件の評価結果を提供します。 ユーザーが次回サインインするときに、WHFB 登録がログイン後タスクとして起動される場合は、状態を *Will Provision* に設定します。

注

Windows 10 May 2021 更新プログラム (バージョン 21H1) では、次の Cloud Kerberos 診断フィールドが追加されました。

Windows 11 バージョン 23H2 より前では、 **OnPremTGT** の設定の名前は **CloudTGT** でした。

- **OnPremTGT**: この設定は Cloud Kerberos の信頼の展開に固有であり、CertEnrollment 状態が *none* の場合にのみ表示されます。 デバイスにオンプレミス リソースにアクセスするための Cloud Kerberos チケットがある場合は、状態を *YES* に設定します。 Windows 11 バージョン 23H2 より前のバージョンでは、この設定の名前は **CloudTGT** でした。

#### NGC の前提条件チェック出力のサンプル

```
+----------------------------------------------------------------------+
| Ngc Prerequisite Check                                               |
+----------------------------------------------------------------------+

            IsDeviceJoined : YES
             IsUserAzureAD : YES
             PolicyEnabled : YES
          PostLogonEnabled : YES
            DeviceEligible : YES
        SessionIsNotRemote : YES
            CertEnrollment : enrollment authority
          AdfsRefreshToken : YES
             AdfsRaIsReady : YES
    LogonCertTemplateReady : YES ( StateReady )
              PreReqResult : WillProvision
+----------------------------------------------------------------------+
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/troubleshoot-device-registration-tool-linux"} -->
## Linux でのデバイス登録コマンド ツールのトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-device-registration-tool-linux
- Service: entra-id / devices
- Article date: 2026-01-07
- Summary: この記事では、Linux 上の Device Registration コマンド ツールからの出力を使用して、Microsoft Entra ID のデバイスの状態を理解する方法について説明します。

デバイスの登録状態、PRT (プライマリ更新トークン) 情報、および Microsoft Identity Broker からのブローカー構成を照会する Windows `dsregcmd` に似た Linux コマンド ライン ツール。

### 概要

`dsreg` は、Linux システム上のデバイス登録状態に関する詳細情報を管理者と開発者に提供します。 Microsoft Identity Broker に対してクエリを実行して、次の情報を取得します。

- デバイス登録の状態
- デバイス ID とテナント情報
- PRT (プライマリ更新トークン) の状態と更新時間
- 証明書情報
- セッション キーの詳細
- ブローカーのバージョンと構成

### Installation

このツールは Microsoft Identity Broker パッケージに含まれており、ブローカーのインストール時に `/usr/bin/dsreg` にインストールされます。

#### サポートされている Linux ディストリビューション

- **Ubuntu/Debian** - DEB パッケージ (`microsoft-identity-broker_*.deb`)
- **RHEL/Rocky Linux/AlmaLinux** - RPM パッケージ (`microsoft-identity-broker-*.rpm`)

## [Ubuntu](#tab/debian-dsreginstall)
```bash
# Ubuntu/Debian
sudo apt install microsoft-identity-broker
```

## [RHEL/Rocky Linux/AlmaLinux](#tab/redhat-dsreginstall)
```bash
# RHEL/Rocky Linux/AlmaLinux
sudo dnf install microsoft-identity-broker
# or
sudo yum install microsoft-identity-broker
```

---

注

`microsoft-identity-broker`をインストールすると、システム全体で dsreg ツールを使用できるようになります。

```bash
dsreg --help
```

### Usage

```bash
# Display device registration status
dsreg
dsreg --status

# Display help
dsreg --help

# Query specific tenant
dsreg --tenant-id <tenant-guid>

# Get DRS access token
dsreg --getdrstoken

# Unregister device (requires sudo)
sudo dsreg --tenant-id <tenant-guid> --unregister

# Clean broker state (prompts for confirmation)
dsreg --cleanup

# Clean broker state including certificates (requires sudo)
sudo dsreg --cleanup
```

### コマンド オプション

| Option | Description |
| --- | --- |
| `--status` | 包括的なデバイス登録と PRT の状態を表示する (既定) |
| `--help` | ヘルプ情報を表示する |
| `--tenant-id <id>` | 特定のテナント GUID に対するデバイス登録を照会する |
| `--getdrstoken` | デバイス操作用の DRS (Device Registration Service) アクセス トークンを取得する |
| `--unregister` | Entra ID テナントからデバイスの登録を解除する (sudo と DRS トークンが**必要** ) |
| `--cleanup` | 確認プロンプトを使用して、すべてのローカル ブローカーの状態をクリーンアップします。**フェーズ 1 (常に)** : ユーザー レベルのキャッシュ (アカウント、トークン、資格情報) をクリアします**フェーズ 2 (ルートのみ)**: /etc/microsoft/identity-broker/private および /etc/microsoft/identity-broker/certs からデバイスの秘密キーと証明書 (drs\_\* およびstk\_\* ファイルを削除します)**注**: Entra ID からデバイスの登録を解除しません ( `--unregister` を個別に使用します) |

### 出力情報

#### デバイスの状態

| フィールド | Description |
| --- | --- |
| **デバイス登録の状態** | (登録済み/未登録) |
| **デバイス ID** | Microsoft Entra テナント内のデバイスの一意の ID。 |
| **テナント ID** | デバイスが Microsoft Entra ID に参加したときに表示されるテナントの詳細。 |
| **テナント名/ドメイン** | デバイスが Microsoft Entra ID に参加したときに表示されるテナントの詳細。 |
| **証明書の拇印** | デバイス証明書の拇印。 |

#### プライマリ更新トークン (PRT)

| フィールド | Description |
| --- | --- |
| **PRT present** | (はい|いいえ) ログイン ユーザーのデバイスにプライマリ更新トークン (PRT) が存在する場合は *YES* 。 |
| **PRT キャッシュ時間** | PRT がデバイスに最後にキャッシュされた時刻 (UTC)。 |
| **PRT の有効期限** | PRT が更新されない場合に有効期限が切れる時刻 (UTC)。 |
| **最後の PRT 更新時刻** | Enterprise PRT が最後に更新された時刻 (UTC)。 |
| **PRT の年齢 (時間)** |  |
| **セッション キー プロトコルのバージョン** | PRT に関連付けられているセッション キーに使用されるプロトコル のバージョン。 |

#### ブローカー情報

| フィールド | Description |
| --- | --- |
| **ブローカーのバージョン** | デバイスにインストールされている現在の `microsoft-identity-broker` のバージョン。 |
| **ブローカー サービス名** | ブローカーの systemd サービスの名前。 |
| **ブローカーのバイナリ パスとインストールのタイムスタンプ** | ブローカー バイナリのファイル パスとインストール タイムスタンプ。 |
| **デバイス モード** | (排他/共有) |

### 出力例

```
Device State
-----------------------------------------------------------------
Device Registration Status   : Registered
Device ID                    : a1b2c3d4-e5f6-g7h8-i9j0-k1l2m3n4o5p6
Tenant ID                    : aaaabbbb-0000-cccc-1111-dddd2222eeee
Tenant Name                  : contoso.com

Primary Refresh Token
-----------------------------------------------------------------
PRT Present                  : YES
PRT Cached At                : 2025-12-11 08:30:15 UTC
PRT Expires On               : 2025-12-25 08:30:15 UTC
PRT Age                      : 4.25 hours
Session Key Protocol         : 3.1

Broker Information
-----------------------------------------------------------------
Broker Version               : 2.0.123.456
Broker Service Name          : microsoft-identity-device-broker.service
Broker Binary Path           : /opt/microsoft/identity-broker/bin/microsoft-identity-broker
Broker Binary Timestamp      : 2025-12-19 14:30:22 UTC
```

### クリーンアップ操作

`--cleanup` コマンドは安全に実行します。 ユーザー確認を使用した 2 段階のクリーンアップ:

#### 確認プロンプト

続行する前に、dsreg に次の情報が表示されます。

```bash
WARNING: This operation will permanently remove all local accounts, credentials, and cached tokens.
         Running as root will also remove device certificates and private keys from:
           - /etc/microsoft/identity-broker/certs (certificates)
           - /etc/microsoft/identity-broker/private (private keys)

This is an irreversible operation.
Do you want to continue? (Y/N):
```

続行するには **、Y** または **y を** 入力する必要があります。 その他の入力では、操作が取り消されます。

#### フェーズ 1: User-Level クリーンアップ (常に実行)

- キャッシュされたすべてのアカウントをクリアします
- すべての認証トークンを削除します
- 保存されている資格情報を削除します
- PRT キャッシュをクリアします
- ユーザー キャッシュ ディレクトリを削除します。
    - `~/.cache/intune-portal`
    - `~/.cache/Microsoft`
    - `~/.cache/microsoft-edge`
    - `~/.config/intune`
    - `~/.config/microsoft-identity-broker`
    - `~/.local/share/intune-portal`

#### フェーズ 2: デバイス キーと証明書のクリーンアップ (ルートのみ)

ルート (`sudo dsreg --cleanup`) として実行されている場合、さらに以下も削除されます。

- `microsoft-identity-device-broker` サービスを停止します
- `/etc/microsoft/identity-broker/private/`からのデバイスの秘密キー
- デバイス証明書`/etc/microsoft/identity-broker/certs/`から
- `/var/lib/microsoft-identity-device-broker`からのシステムレベルのブローカーデータ
- 証明書ディレクトリの先頭に `drs_*` または `stk_*` が付いたファイルのみをタッチします
- 安全: 他のキーや証明書を変更しない

#### 重要な注意事項

- **クリーンアップで Entra ID からデバイスの登録が解除されない**
- デバイスを完全に削除するには、 `sudo dsreg --tenant-id <id> --unregister` を個別に実行します
- クリーンアップ後、ユーザーは再びサインインして PRT を復元する必要があります
- sudo なしで実行すると、証明書ではなくユーザー キャッシュのみがクリアされます

### トラブルシューティング

#### "ブローカー インスタンスを取得できませんでした"

**原因：** Broker が実行されていないか、インストールされていない **ソリューション:**

```bash
# Check if broker is running
systemctl status microsoft-identity-device-broker

# Start broker if needed
systemctl start microsoft-identity-device-broker
```

#### "デバイス登録状態: 未登録"

**原因：** デバイスが Entra ID ソリューションに登録されていません **。**

1. Entra ID へのネットワーク接続を確認する
2. デバイスが登録要件を満たしていることを確認する
3. 組織の登録プロセスを実行する

#### PRTの有無: いいえ

**原因：** サインインしているユーザーがいないか、PRT が期限切れになっている **ソリューション:**

1. Entra ID アカウントでサインインする
2. PRT 更新のネットワーク接続を確認する
3. 時刻同期の確認 (PRT には正確な時刻が必要)

### 終了コード

| Code | Meaning |
| --- | --- |
| 0 | 成功 (登録されていない場合や PRT がない場合でも) |
| 1 | 無効なコマンド ライン引数 |
| 2 | 初期化に失敗しました（ブローカーが利用できません） |
| 3 | エラーでクエリが失敗しました |
| 4 | アクセス許可が不十分 (sudo が必要) |
| 5 | 操作に必要な DRS トークン |
| 6 | デバイスの登録解除に失敗しました |
| 7 | クリーンアップ操作に失敗しました |
| 八 | ユーザーがクリーンアップ操作を取り消しました |

### セキュリティ アーキテクチャ

#### 特権の分離

このツールは、懸念事項を適切に分離してセキュリティを実装します。

- **ビジネス ロジック レイヤー** (`DeviceBrokerImpl`): 特権チェックなし、テスト可能なロジックのみ
- **IPC 境界** (`DBusDispatcher`): `callerUid` 検証によるセキュリティの適用
- **ルートを必要とする操作**: ビジネス ロジックではなく、D-Bus 境界で適用されます

#### クリーンアップの安全性

証明書のクリーンアップは安全に設計されています。

- `drs_*`または`stk_*`プレフィックスを持つファイルのみを削除します`/etc/microsoft/identity-broker/private`
- システム証明書またはユーザー証明書に触れることはありません
- 破壊的操作の前に確認プロンプトを表示します

### アーキテクチャ

このツールでは、既存のブローカー コンポーネントが使用されます。

- **DeviceBroker** - デバイス登録クエリと特権操作の場合
- **BrokerCore** - PRT と認証状態の場合
- **DeviceInfoAccessor** - デバイス情報の取得用
- **CacheManager** - キャッシュされた資格情報を読み取る場合
- **D-Bus IPC** - ブローカー サービスとの安全な通信用

ブローカーと同じコンポーネントを共有し、一貫性のある結果を確保します。

### Windows dsregcmd との比較

| 特徴 | Windows dsregcmd | Linux dsreg |
| --- | --- | --- |
| デバイスの参加状態 | ✓ | ✓ |
| デバイス識別子 | ✓ | ✓ |
| テナント ID | ✓ | ✓ |
| PRT の状態 | ✓ | ✓ |
| PRT タイムスタンプ | ✓ | ✓ |
| 証明書の拇印 | ✓ | ✓ |
| 色分けされた出力 | ✗ | ✓ |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/troubleshoot-device-windows-joined"} -->
## 登録済み、ハイブリッド、および Microsoft Entra 参加済みの Windows マシンのトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-device-windows-joined
- Service: entra-id / devices
- Article date: 2025-07-27
- Summary: この記事では、Windows 10 および Windows 11 デバイスに参加している Microsoft Entra ハイブリッドのトラブルシューティングについて説明します。

Microsoft Entra ID で正しく動作しない Windows 11 または Windows 10 デバイスがある場合は、こちらのトラブルシューティングを開始してください。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)以上としてサインインします。
2. **[Entra ID]**&gt;**[デバイス]**&gt;**[すべてのデバイス]**&gt;**[診断と問題解決]** に移動します。
3. **Windows 10+ 関連する問題**のトラブルシューティング ツールの下にある **[トラブルシューティング]** を選択します。 [Image: [診断と解決] ウィンドウにある Windows トラブルシューティング ツールを示すスクリーンショット。]
4. **[手順]** を選択し、トラブルシューティング ツールで分析するために必要なログをダウンロード、実行、収集する手順に従います。
5. `authlogs` フォルダーとコンテンツを収集して zip 圧縮したら、Microsoft Entra 管理センターに戻ります。
6. **[参照]** を選択し、アップロードする zip ファイルを選択します。 [Image: 前の手順で収集したログを参照して選択し、トラブルシューティング ツールで推奨事項を作成できるようにする方法を示すスクリーンショット。]

トラブルシューティング ツールでは、アップロードしたファイルの内容を確認し、推奨される次の手順を提供します。 これらの次の手順には、ドキュメントへのリンクや、サポートに問い合わせて追加のサポートを得るなどがあります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/troubleshoot-hybrid-join-windows-current"} -->
## Microsoft Entra ハイブリッド参加済みデバイスのトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-hybrid-join-windows-current
- Service: entra-id / devices
- Article date: 2026-09-01
- Summary: この記事では、Windows 10 以降または Windows Server 2016 以降を実行している Microsoft Entra ハイブリッド参加済みデバイスのトラブルシューティング ガイダンスを提供します。

この記事では、Windows 10 以降または Windows Server 2016 以降を実行しているデバイスの潜在的な問題を解決するのに役立つトラブルシューティング ガイダンスを提供します。

Microsoft Entra ハイブリッド参加では、Windows 10 の 2015 年 11 月以降の更新プログラムがサポートされます。

この記事では、次のシナリオをサポートする[Microsoft Entra ハイブリッド参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)を使用していることを前提としています。

- デバイスベースの条件付きアクセス
- [Enterprise State Roaming](https://learn.microsoft.com/ja-jp/entra/identity/devices/enterprise-state-roaming-enable)
- [Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/hello-identity-verification)

Note

デバイス登録に関するよくある問題のトラブルシューティングを行うには、[デバイス登録のトラブルシューティング ツール](https://learn.microsoft.com/ja-jp/samples/azure-samples/dsregtool/dsregtool/)を使用してください。

### 参加エラーのトラブルシューティング

#### 手順 1:参加状態を取得する

1. 管理者としてコマンド プロンプト ウィンドウを開きます。
2. 「`dsregcmd /status`」と入力します。

```
+----------------------------------------------------------------------+
| Device State                                                         |
+----------------------------------------------------------------------+

    AzureAdJoined: YES
 EnterpriseJoined: NO
         DeviceId: 5820fbe9-60c8-43b0-bb11-44aee233e4e7
       Thumbprint: AA11BB22CC33DD44EE55FF66AA77BB88CC99DD00
   KeyContainerId: bae6a60b-1d2f-4d2a-a298-33385f6d05e9
      KeyProvider: Microsoft Platform Crypto Provider
     TpmProtected: YES
     KeySignTest: : MUST Run elevated to test.
              Idp: login.windows.net
         TenantId: aaaabbbb-0000-cccc-1111-dddd2222eeee
       TenantName: Contoso
      AuthCodeUrl: https://login.microsoftonline.com/msitsupp.microsoft.com/oauth2/authorize
   AccessTokenUrl: https://login.microsoftonline.com/msitsupp.microsoft.com/oauth2/token
           MdmUrl: https://enrollment.manage-beta.microsoft.com/EnrollmentServer/Discovery.svc
        MdmTouUrl: https://portal.manage-beta.microsoft.com/TermsOfUse.aspx
  dmComplianceUrl: https://portal.manage-beta.microsoft.com/?portalAction=Compliance
      SettingsUrl: eyJVc{lots of characters}JdfQ==
   JoinSrvVersion: 1.0
       JoinSrvUrl: https://enterpriseregistration.windows.net/EnrollmentServer/device/
        JoinSrvId: urn:ms-drs:enterpriseregistration.windows.net
    KeySrvVersion: 1.0
        KeySrvUrl: https://enterpriseregistration.windows.net/EnrollmentServer/key/
         KeySrvId: urn:ms-drs:enterpriseregistration.windows.net
     DomainJoined: YES
       DomainName: CONTOSO

+----------------------------------------------------------------------+
| User State                                                           |
+----------------------------------------------------------------------+

             NgcSet: YES
           NgcKeyId: {aaaaaaaa-0b0b-1c1c-2d2d-333333333333}
    WorkplaceJoined: NO
      WamDefaultSet: YES
WamDefaultAuthority: organizations
       WamDefaultId: https://login.microsoft.com
     WamDefaultGUID: {B16898C6-A148-4967-9171-64D755DA8520} (AzureAd)
         AzureAdPrt: YES
```

#### 手順 2:参加状態を評価する

次の表のフィールドを確認し、必要な値が設定されていることを確認します。

| フィールド | 必要な値 | 説明 |
| --- | --- | --- |
| DomainJoined | はい | このフィールドは、デバイスが Windows Server Active Directory に参加しているかどうかを示します。 この値が *NO* である場合、デバイスは Microsoft Entra ハイブリッド参加を実行できません。 |
| WorkplaceJoined | NO | このフィールドは、デバイスが個人所有のデバイスとして Microsoft Entra ID に登録されているかどうかを示します (*”ワークプレースに参加済み"* と表示されます)。 ドメイン参加済みで、かつ Microsoft Entra ハイブリッドにも参加済みのコンピューターの場合、この値は *NO* になります。 この値が *YES* の場合、Microsoft Entra ハイブリッド参加が完了する前に、職場または学校アカウントが追加されています。 この場合に Windows 10 バージョン 1607 以降を使用していると、アカウントは無視されます。 |
| AzureAdJoined (英語) | はい | このフィールドは、デバイスが参加しているかどうかを示します。 デバイスが Microsoft Entra 参加済みデバイスまたは Microsoft Entra ハイブリッド参加済みデバイスである場合、値は *YES* になります。 この値が *NO* の場合、Microsoft Entra ID への参加はまだ完了していません。 |

詳細なトラブルシューティングを行うには、次の手順に進みます。

#### 手順 3: 参加がエラーになったフェーズとエラー コードを見つける

**Windows 10 バージョン 1803 以降の場合**

参加状態の出力の "Diagnostic Data" セクションで "Previous Registration" サブセクションを探します。 このセクションは、デバイスがドメイン参加済みで、Microsoft Entra ハイブリッド参加ができない場合にのみ表示されます。

"Error Phase" フィールドは参加エラーのフェーズを示し、"Client ErrorCode" は参加操作のエラー コードを示します。

```
+----------------------------------------------------------------------+
     Previous Registration : 2019-01-31 09:16:43.000 UTC
         Registration Type : sync
               Error Phase : join
          Client ErrorCode : 0x801c03f2
          Server ErrorCode : DirectoryError
            Server Message : The device object by the given id (e92325d0-xxxx-xxxx-xxxx-94ae875d5245) isn't found.
              Https Status : 400
                Request Id : 6bff0bd9-820b-484b-ab20-2a4f7b76c58e
+----------------------------------------------------------------------+
```

**Windows 10 の以前のバージョンの場合**

イベント ビューアーのログで、参加エラーのフェーズとエラー コードを確認します。

1. イベント ビューアーで **ユーザー デバイス登録**イベント ログを開きます。 これらは、**[アプリケーションとサービス ログ]**&gt;**[Microsoft]**&gt;**[Windows]**&gt;**[ユーザー デバイス登録]** の下に格納されています。
2. イベント ID が 304、305、307 のイベントを探します。

[Image: イベント ID 304 が選択されており、その情報が表示され、エラー コードとフェーズが強調表示されているイベント ビューアーのスクリーンショット。]

[Image: イベント ID 305 が選択されており、その情報が表示され、エラー コードが強調表示されているイベント ビューアーのスクリーンショット。]

#### 手順 4: 考えられる原因と解決策を確認する

##### 事前チェック フェーズ

考えられるエラーの原因:

- デバイスがドメイン コントローラーを認識していない。
    - デバイスは、組織の内部ネットワークに配置するか、オンプレミスの Active Directory ドメイン コントローラーをネットワークで認識する仮想プライベート ネットワークに配置する必要があります。

##### 検出フェーズ

考えられるエラーの原因:

- サービス接続ポイント オブジェクトが正しく構成されていないか、ドメイン コントローラーから読み取ることができない。
    - デバイスが属している AD フォレストに、Microsoft Entra ID 内の検証済みドメイン名を指す有効なサービス接続ポイント オブジェクトが必要です。
    - 詳細については、「[チュートリアル: フェデレーション ドメイン用の Microsoft Entra ハイブリッド参加の構成](https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join)」のサービス接続ポイントの構成に関するセクションを参照してください。
- 検出エンドポイントから検出メタデータに接続してフェッチできない。
    - 登録エンドポイントと承認エンドポイントを検出するには、デバイスがシステム コンテキストで `https://enterpriseregistration.windows.net` にアクセスできる必要があります。
    - オンプレミス環境に送信プロキシが必要である場合は、デバイスのコンピューター アカウントが送信プロキシに対する検出とサイレント認証を実行できることを IT 管理者が確認する必要があります。
- ユーザー領域エンドポイントに接続して領域の検出を実行できない (Windows 10 バージョン 1809 以降のみ)。
    - 検証済みドメインの領域検出を実行してドメインの種類 (マネージドまたはフェデレーション) を判別するには、デバイスがシステム コンテキストで `https://login.microsoftonline.com` にアクセスできる必要があります。
    - オンプレミス環境に送信プロキシが必要である場合、IT 管理者は、デバイスのシステム コンテキストで送信プロキシに対する検出とサイレント認証を確実に実行できるようにする必要があります。

**一般的なエラー コード:**

| エラー コード | 理由 | 解像度 |
| --- | --- | --- |
| **DSREG\_AUTOJOIN\_ADCONFIG\_READ\_FAILED** (0x801c001d/-2145648611) | サービス接続ポイント (SCP) オブジェクトを読み取ることができず、Microsoft Entra テナント情報を取得できません。 | [サービス接続ポイントの構成](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-manual#configure-a-service-connection-point)に関するセクションを参照してください。 |
| **DSREG\_AUTOJOIN\_DISC\_FAILED** (0x801c0021/-2145648607) | 一般的な検出エラー。 データ レプリケーション サービス (DRS) から検出メタデータを取得できませんでした。 | さらに調査するには、次のセクションでサブエラーを確認してください。 |
| **DSREG\_AUTOJOIN\_DISC\_WAIT\_TIMEOUT** (0x801c001f/-2145648609) | 検出の実行中に操作がタイムアウトになりました。 | システム コンテキストで `https://enterpriseregistration.windows.net` にアクセスできるようにします。 詳細については、[ネットワーク接続の要件](https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join#prerequisites)に関するセクションを参照してください。 |
| **DSREG\_AUTOJOIN\_USERREALM\_DISCOVERY\_FAILED** (0x801c003d/-2145648579) | 一般的な領域検出エラー。 STS からドメインの種類 (管理対象/フェデレーション) を判別できませんでした。 | さらに調査するには、次のセクションでサブエラーを確認してください。 |

**一般的なサブエラー コード:**

検出エラー コードのサブエラー コードを確認するには、次のいずれかの方法を使用します。

###### Windows 10 バージョン 1803 以降

参加状態の出力の "Diagnostic Data" セクションで "DRS Discovery Test" を探します。 このセクションは、デバイスがドメイン参加済みで、Microsoft Entra ハイブリッド参加ができない場合にのみ表示されます。

```
+----------------------------------------------------------------------+
| Diagnostic Data                                                      |
+----------------------------------------------------------------------+

     Diagnostics Reference : www.microsoft.com/aadjerrors
              User Context : UN-ELEVATED User
               Client Time : 2019-06-05 08:25:29.000 UTC
      AD Connectivity Test : PASS
     AD Configuration Test : PASS
        DRS Discovery Test : FAIL [0x801c0021/0x80072ee2]
     DRS Connectivity Test : SKIPPED
    Token acquisition Test : SKIPPED
     Fallback to Sync-Join : ENABLED

+----------------------------------------------------------------------+
```

###### Windows 10 の以前のバージョンの場合

イベント ビューアーのログを使用して、参加エラーのフェーズとエラー コードを探します。

1. イベント ビューアーで **ユーザー デバイス登録**イベント ログを開きます。 これらは、**[アプリケーションとサービス ログ]**&gt;**[Microsoft]**&gt;**[Windows]**&gt;**[ユーザー デバイス登録]** の下に格納されています。
2. イベント ID 201 を探します。

[Image: イベント ID 201 が選択されており、その情報が表示され、エラー コードが強調表示されているイベント ビューアーのスクリーンショット。]

**ネットワーク エラー**:

| エラー コード | 理由 | 解像度 |
| --- | --- | --- |
| **WININET\_E\_CANNOT\_CONNECT** (0x80072efd/-2147012867) | サーバーとの接続を確立できませんでした。 | 必要な Microsoft リソースとのネットワーク接続を確認します。 詳細については、[ネットワーク接続の要件](https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join#prerequisites)に関するページを参照してください。 |
| **WININET\_E\_TIMEOUT** (0x80072ee2/-2147012894) | 一般的なネットワーク タイムアウト。 | 必要な Microsoft リソースとのネットワーク接続を確認します。 詳細については、[ネットワーク接続の要件](https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join#prerequisites)に関するページを参照してください。 |
| **WININET\_E\_DECODING\_FAILED** (0x80072f8f/-2147012721) | ネットワーク スタックがサーバーからの応答をデコードできませんでした。 | ネットワーク プロキシがサーバーの応答を妨害したり変更したりしていないようにします。 |

**HTTP エラー**:

| エラー コード | 理由 | 解像度 |
| --- | --- | --- |
| **DSREG\_DISCOVERY\_TENANT\_NOT\_FOUND** (0x801c003a/-2145648582) | サービス接続ポイント オブジェクトが不正なテナント ID で構成されているか、テナント内でアクティブなサブスクリプションが見つかりませんでした。 | サービス接続ポイント オブジェクトが正しい Microsoft Entra テナント ID と有効なサブスクリプションで構成されているか、またサービスがテナント内に存在していることを確認します。 |
| **DSREG\_SERVER\_BUSY** (0x801c0025/-2145648603) | DRS サーバーからの HTTP 503。 | サーバーは現在使用できません。 サーバーがオンラインに戻ると、以降の参加の試行は成功する可能性があります。 |

**その他のエラー**:

| エラー コード | 理由 | 解像度 |
| --- | --- | --- |
| **E\_INVALIDDATA** (0x8007000d/-2147024883) | サーバー応答 JSON を解析できませんでした。HTML 認証ページでプロキシが HTTP 200 を返していることが原因と考えられます。 | オンプレミス環境に送信プロキシが必要である場合、IT 管理者は、デバイスのシステム コンテキストで送信プロキシに対する検出とサイレント認証を確実に実行できるようにする必要があります。 |

##### 認証フェーズ

この内容は、フェデレーション ドメイン アカウントにのみ適用されます。

エラーの原因:

- DRS リソースのアクセス トークンをサイレントに取得できません。
    - Windows 10 および Windows 11 デバイスでは、アクティブな WS-Trust エンドポイントに対する統合 Windows 認証を使用して、フェデレーション サービスから認証トークンを取得します。 詳細については、[フェデレーション サービスの構成](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-manual#set-up-issuance-of-claims)に関する記事をご覧ください。

**一般的なエラー コード**:

イベント ビューアーのログで、エラー コード、サブエラー コード、サーバー エラー コード、およびサーバー エラー メッセージを確認します。

1. イベント ビューアーで **ユーザー デバイス登録**イベント ログを開きます。 これらは、**[アプリケーションとサービス ログ]**&gt;**[Microsoft]**&gt;**[Windows]**&gt;**[ユーザー デバイス登録]** の下に格納されています。
2. イベント ID 305 を探します。

[Image: イベント ID 305 が選択されており、その情報が表示され、ADAL エラー コードと状態が強調表示されているイベント ビューアーのスクリーンショット。]

**構成エラー**:

| エラー コード | 理由 | 解像度 |
| --- | --- | --- |
| **ERROR\_ADAL\_PROTOCOL\_NOT\_SUPPORTED** (0xcaa90017/-894894057) | Azure AD Authentication Library (ADAL) 認証プロトコルが WS-Trust ではありません。 | オンプレミスの ID プロバイダーが WS-Trust をサポートしている必要があります。 |
| **ERROR\_ADAL\_FAILED\_TO\_PARSE\_XML**（XML 解析に失敗しました）(0xcaa9002c/-894894036) | オンプレミスのフェデレーション サービスから XML 応答が返されませんでした。 | メタデータ交換 (MEX) エンドポイントから有効な XML が返されていることを確認します。 プロキシが妨害を行ったり XML 以外の応答を返したりしていないことを確認します。 |
| **ERROR\_ADAL\_COULDNOT\_DISCOVER\_USERNAME\_PASSWORD\_ENDPOINT** (0xcaa90023/-894894045) | ユーザー名/パスワード認証のエンドポイントを検出できませんでした。 | オンプレミスの ID プロバイダーの設定を確認します。 WS-Trust エンドポイントが有効になっており、MEX 応答にこれらの正しいエンドポイントが含まれていることを確認します。 |

**ネットワーク エラー**:

| エラー コード | 理由 | 解像度 |
| --- | --- | --- |
| **ERROR\_ADAL\_INTERNET\_TIMEOUT** (0xcaa82ee2/-894947614) | 一般的なネットワーク タイムアウト。 | システム コンテキストで `https://login.microsoftonline.com` にアクセスできるようにします。 システム コンテキストでオンプレミスの ID プロバイダーにアクセスできるようにします。 詳細については、[ネットワーク接続の要件](https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join#prerequisites)に関するページを参照してください。 |
| **ERROR\_ADAL\_INTERNET\_CONNECTION\_ABORTED** (0xcaa82efe/-894947586) | 承認エンドポイントとの接続が中止されました。 | しばらくしてからやり直すか、または別の安定したネットワークの場所から参加を試みます。 |
| **ERROR\_ADAL\_INTERNET\_SECURE\_FAILURE** (0xcaa82f8f/-894947441) | サーバーによって送信されたトランスポート層セキュリティ (TLS) 証明書 (以前は Secure Sockets Layer (SSL) 証明書と呼ばれていました)、を検証できませんでした。 | クライアント時間のずれを確認します。 しばらくしてからやり直すか、または別の安定したネットワークの場所から参加を試みます。 |
| **ERROR\_ADAL\_INTERNET\_CANNOT\_CONNECT** (0xcaa82efd/-894947587) | `https://login.microsoftonline.com` への接続に失敗しました。 | `https://login.microsoftonline.com`へのネットワーク接続を確認します。 |

**その他のエラー**:

| エラー コード | 理由 | 解像度 |
| --- | --- | --- |
| **ERROR\_ADAL\_SERVER\_ERROR\_INVALID\_GRANT** (0xcaa20003/-895352829) | オンプレミスの ID プロバイダーからの SAML トークンが Microsoft Entra ID で受け付けられませんでした。 | フェデレーション サーバーの設定を確認します。 認証ログでサーバー エラー コードを探します。 |
| **ERROR\_ADAL\_WSTRUST\_REQUEST\_SECURITYTOKEN\_FAILED** (0xcaa90014/-894894060) | サーバーの WS-Trust 応答でエラー例外が報告され、アサーションの取得に失敗しました。 | フェデレーション サーバーの設定を確認します。 認証ログでサーバー エラー コードを探します。 |
| **ERROR\_ADAL\_WSTRUST\_TOKEN\_REQUEST\_FAIL** (0xcaa90006/-894894074) | トークン エンドポイントからアクセス トークンを取得するときにエラーが発生しました。 | ADAL ログで根本的なエラーを探します。 |
| **ERROR\_ADAL\_OPERATION\_PENDING** (0xcaa1002d/-895418323) | 一般的な ADAL エラー。 | 認証ログで、サブエラー コードまたはサーバー エラー コードを探します。 |

##### 参加フェーズ

エラーの原因:

お使いの Windows 10 のバージョンに応じて、次の表で登録の種類とエラー コードを探します。

##### Windows 10 バージョン 1803 以降

参加状態の出力の "Diagnostic Data" セクションで "Previous Registration" サブセクションを探します。 このセクションは、デバイスがドメイン参加済みで、Microsoft Entra ハイブリッド参加ができない場合にのみ表示されます。

"Registration Type" フィールドは参加の種類を表します。

```
+----------------------------------------------------------------------+
     Previous Registration : 2019-01-31 09:16:43.000 UTC
         Registration Type : sync
               Error Phase : join
          Client ErrorCode : 0x801c03f2
          Server ErrorCode : DirectoryError
            Server Message : The device object by the given id (aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb) is not found.
              Https Status : 400
                Request Id : 6bff0bd9-820b-484b-ab20-2a4f7b76c58e
+----------------------------------------------------------------------+
```

##### Windows 10 の以前のバージョンの場合

イベント ビューアーのログで、参加エラーのフェーズとエラー コードを確認します。

1. イベント ビューアーで **ユーザー デバイス登録**イベント ログを開きます。 これらは、**[アプリケーションとサービス ログ]**&gt;**[Microsoft]**&gt;**[Windows]**&gt;**[ユーザー デバイス登録]** の下に格納されています。
2. イベント ID 204 を探します。

[Image: イベント ID 204 が選択されており、そのエラー コード、HTTP の状態、メッセージが強調表示されているイベント ビューアーのスクリーンショット。]

**DRS サーバーから返される HTTP エラー**:

| エラー コード | 理由 | 解像度 |
| --- | --- | --- |
| **DSREG\_E\_DIRECTORY\_FAILURE** (0x801c03f2/-2145647630) | DRS から、ErrorCode が "DirectoryError" のエラー応答を受け取りました。 | 考えられる原因と解決策については、サーバー エラー コードを参照してください。 |
| **DSREG\_E\_DEVICE\_AUTHENTICATION\_ERROR** (0x801c0002/-2145648638) | DRS から、ErrorCode が "AuthenticationError" で、ErrorSubCode が "DeviceNotFound" "*以外*" のエラー応答を受け取りました。 | 考えられる原因と解決策については、サーバー エラー コードを参照してください。 |
| **DSREG\_E\_DEVICE\_INTERNALSERVICE\_ERROR** (0x801c0006/-2145648634) | DRS から、ErrorCode が "DirectoryError" のエラー応答を受け取りました。 | 考えられる原因と解決策については、サーバー エラー コードを参照してください。 |

**TPM エラー**:

| エラー コード | 理由 | 解像度 |
| --- | --- | --- |
| **NTE\_BAD\_KEYSET** (0x80090016/-2146893802) | トラステッド プラットフォーム モジュール (TPM) 操作が失敗したか、または無効でした。 | このエラーは、キー セットが存在しないことを示しています。 このエラーは、システムで TPM がクリアされたとき、または不適切な sysprep イメージがある場合に発生します。  BIOS または Windows の設定で TPM をクリアしないでください。 TPM がクリアされると、問題を解決するためにユーザーがアカウントを削除および再追加して復旧する必要が生じます。特に、WAM アカウントが複数存在する場合はご注意ください。 sysprep イメージの作成元のマシンが、Microsoft Entra に参加済みでなく、Microsoft Entra ハイブリッドに参加しておらず、かつ Microsoft Entra 登録されていないことを確認してください。 |
| **TPM\_E\_PCP\_INTERNAL\_ERROR** (0x80290407/-2144795641) | 一般的な TPM エラー。 | このエラーが発生したデバイスで TPM を無効にします。 Windows 10 バージョン1809 以降では、TPM エラーが自動的に検知され、TPM を使用することなく Microsoft Entra ハイブリッドの参加が完了します。 |
| **TPM\_E\_NOTFIPS** (0x80280036/-2144862154) | FIPS モードの TPM は現在サポートされていません。 | このエラーが発生したデバイスで TPM を無効にします。 Windows 10 バージョン1809 以降では、TPM エラーが自動的に検知され、TPM を使用することなく Microsoft Entra ハイブリッドの参加が完了します。 |
| **NTE\_AUTHENTICATION\_IGNORED** (0x80090031/-2146893775) | TPM がロックアウトされています。 | 一時的なエラーです。 クールダウン時間が終わるまで待ちます。 しばらくしてから参加を試みると成功します。 詳細については、「[TPM の基本事項](https://learn.microsoft.com/ja-jp/windows/security/hardware-security/tpm/tpm-fundamentals#anti-hammering)」を参照してください。 |

**ネットワーク エラー**:

| エラー コード | 理由 | 解像度 |
| --- | --- | --- |
| **WININET\_E\_TIMEOUT** (0x80072ee2/-2147012894) | DRS でデバイスを登録するときの一般的なネットワーク タイムアウトです。 | `https://enterpriseregistration.windows.net` とのネットワーク接続を確認します。 |
| **WININET\_E\_NAME\_NOT\_RESOLVED** (0x80072ee7/-2147012889) | サーバーの名前またはアドレスを解決できませんでした。 | `https://enterpriseregistration.windows.net` とのネットワーク接続を確認します。 |
| **WININET\_E\_CONNECTION\_ABORTED** (0x80072efe/-2147012866) | サーバーとの接続が異常終了しました。 | しばらくしてからやり直すか、または別の安定したネットワークの場所から参加を試みます。 |

**その他のエラー**:

| エラー コード | 理由 | 解像度 |
| --- | --- | --- |
| **DSREG\_AUTOJOIN\_ADCONFIG\_READ\_FAILED** (0x801c001d/-2145648611) | ユーザー デバイス登録イベント ログにイベント ID 220 が存在します。 Windows で、Active Directory 内のコンピューター オブジェクトにアクセスできません。 イベントに Windows エラー コードが含まれているおそれがあります。 エラー コード ERROR\_NO\_SUCH\_LOGON\_SESSION (1312) および ERROR\_NO\_SUCH\_USER (1317) は、オンプレミスの Active Directory のレプリケーション問題に関連しています。 | Active Directory のレプリケーションの問題のトラブルシューティングを行います。 これらのレプリケーションの問題は一時的である場合があり、しばらくすると解消される可能性があります。 |

**サーバーのフェデレーション参加エラー**:

| サーバー エラー コード | サーバー エラー メッセージ | 考えられる原因 | 解像度 |
| --- | --- | --- | --- |
| ディレクトリエラー | Your request is throttled temporarily.(要求は一時的に調整されました。) Please try after 300 seconds.(300 秒後にもう一度お試しください。) | これは予期されるエラーです。複数の登録要求が立て続けに行われたことが原因と考えられます。 | クールダウン時間が終わったら参加を再試行します |

**サーバーの同期参加エラー**:

| サーバー エラー コード | サーバー エラー メッセージ | 考えられる原因 | 解像度 |
| --- | --- | --- | --- |
| ディレクトリエラー | AADSTS90002:Tenant `UUID` not found.(テナントが見つかりません)。 This error might happen if there are no active subscriptions for the tenant.(このエラーは、テナントにアクティブなサブスクリプションがない場合に発生することがあります。) Check with your subscription administrator.(サブスクリプション管理者にご確認ください。) | サービス接続ポイント オブジェクト内のテナント ID が正しくありません。 | サービス接続ポイント オブジェクトが正しい Microsoft Entra テナント ID と有効なサブスクリプションで構成されているか、またサービスがテナント内に存在していることを確認します。 |
| ディレクトリエラー | The device object by the given ID isn't found.(指定された ID のデバイス オブジェクトが見つかりませんでした。) | このエラーは、同期参加の場合に発生します。 デバイス オブジェクトが AD から Microsoft Entra ID に同期されていません | Microsoft Entra Connect の同期が完了するのを待ち、同期完了後に次の参加を試みると問題が解決します。 |
| AuthenticationError | The verification of the target computer's SID(ターゲット コンピューターの SID の検証) | Microsoft Entra デバイスの証明書が、同期参加時に BLOB にサインインするために使用された証明書と一致しません。 このエラーは、通常、同期がまだ完了していないことを意味します。 | Microsoft Entra Connect の同期が完了するのを待ち、同期完了後に次の参加を試みると問題が解決します。 |

#### 手順 5:ログを収集して Microsoft サポートに問い合わせる

1. [*Auth.zip* ファイルをダウンロードします](https://aka.ms/authscripts)。
2. ファイルを *c:\temp* などのフォルダーに解凍し、そのフォルダーに移動します。
3. 管理者特権の Azure PowerShell セッションから `.\start-auth.ps1 -vAuth -accepteula` を実行します。
4. **[アカウントの切り替え]** を選択して、問題のあるユーザーとの別のセッションに切り替えます。
5. 問題を再現します。
6. **[アカウントの切り替え]** を選択して、トレースを実行している管理者セッションに戻ります。
7. 管理者特権の PowerShell セッションから `.\stop-auth.ps1` を実行します。
8. スクリプトが実行されたフォルダーから *Authlogs* フォルダーを Zip (圧縮) 形式で送信します。

### 参加後の認証の問題のトラブルシューティング

#### 手順 1: `dsregcmd /status` を使用して PRT の状態を取得する

1. コマンド プロンプト ウィンドウを開きます。

    Note

    プライマリ更新トークン (PRT) の状態を取得するには、ログイン ユーザーのコンテキストでコマンド プロンプト ウィンドウを開きます。
2. `dsregcmd /status` を実行します。

    "SSO state" セクションに、現在の PRT の状態が表示されます。

    AzureAdPrt フィールドが *NO* の場合、Microsoft Entra ID からの PRT の取得中にエラーが発生しています。
3. AzureAdPrtUpdateTime が 4 時間を超える場合、PRT の更新中に問題が発生したと考えられます。 デバイスをロックした後、ロックを解除し、PRT の更新を強制的に実行して、時間が更新されるか確認します。

```
+----------------------------------------------------------------------+
| SSO State                                                            |
+----------------------------------------------------------------------+

                AzureAdPrt : YES
      AzureAdPrtUpdateTime : 2020-07-12 22:57:53.000 UTC
      AzureAdPrtExpiryTime : 2019-07-26 22:58:35.000 UTC
       AzureAdPrtAuthority : https://login.microsoftonline.com/aaaabbbb-0000-cccc-1111-dddd2222eeee
             EnterprisePrt : YES
   EnterprisePrtUpdateTime : 2020-07-12 22:57:54.000 UTC
   EnterprisePrtExpiryTime : 2020-07-26 22:57:54.000 UTC
    EnterprisePrtAuthority : https://corp.hybridadfs.contoso.com:443/adfs

+----------------------------------------------------------------------+
```

#### 手順 2: エラーコードを見つける

**`dsregcmd` の出力から**

Note

この出力は、Windows 10 の 2021 年 5 月の更新プログラム (バージョン 21H1) 以降で利用できます。

"AzureAdPrt" フィールドの下の "Attempt Status" フィールドに、前回の PRT 試行の状態が、その他必要なデバッグ情報と共に表示されます。 以前のバージョンの Windows では、この情報を [Microsoft Entra の分析ログと操作ログ](https://learn.microsoft.com/ja-jp/troubleshoot/windows-server/networking/diagnostic-logging-troubleshoot-workplace-join-issues#enable-workplace-join-debug-logging-by-using-event-viewer)から抽出します。

```
+----------------------------------------------------------------------+
| SSO State                                                            |
+----------------------------------------------------------------------+

                AzureAdPrt : NO
       AzureAdPrtAuthority : https://login.microsoftonline.com/aaaabbbb-0000-cccc-1111-dddd2222eeee
     AcquirePrtDiagnostics : PRESENT
      Previous Prt Attempt : 2020-07-18 20:10:33.789 UTC
            Attempt Status : 0xc000006d
             User Identity : john@contoso.com
           Credential Type : Password
            Correlation ID : aaaa0000-bb11-2222-33cc-444444dddddd
              Endpoint URI : https://login.microsoftonline.com/aaaabbbb-0000-cccc-1111-dddd2222eeee/oauth2/token/
               HTTP Method : POST
                HTTP Error : 0x0
               HTTP status : 400
         Server Error Code : invalid_grant
  Server Error Description : AADSTS50126: Error validating credentials due to invalid username or password.
```

**Microsoft Entra 分析ログと操作ログから**

イベント ビューアーを使用して、PRT の取得時に Microsoft Entra CloudAP プラグインによって記録されたログ エントリを探します。

1. イベント ビューアーで、Microsoft Entra の操作イベント ログを開きます。 これらは、**[アプリケーションとサービス ログ]**&gt;**[Microsoft]**&gt;**[Windows]**&gt;**[AAD]** の下に格納されています。

Note

CloudAP プラグインでは、エラー イベントが操作ログに、情報イベントが分析ログに記録されます。 問題のトラブルシューティングを行うには、分析ログと操作ログの両方のイベントが必要です。

1. 分析ログ内のイベント 1006 は PRT 取得フローの開始を示し、分析ログ内のイベント 1007 は PRT 取得フローの終了を示します。 Microsoft Entra ログ (分析ログと操作ログ) でイベント 1006 からイベント 1007 までの間に記録されているイベントはすべて、PRT 取得フローの過程で記録されたものです。
2. 最終的なエラー コードはイベント 1007 に記録されます。

[Image: イベント ID 1006 および 1007 が選択されており、最後のエラー コードが強調表示されているイベント ビューアーのスクリーンショット。]

#### 手順 3: 見つかったエラー コードに応じてさらにトラブルシューティングを行う

| エラー コード | 理由 | 解像度 |
| --- | --- | --- |
| **STATUS\_LOGON\_FAILURE** (-1073741715/0xc000006d)**STATUS\_WRONG\_PASSWORD** (-1073741718/0xc000006a) | - デバイスが Microsoft Entra 認証サービスに接続できません。<br>- Microsoft Entra 認証サービスまたは WS-Trust エンドポイントからエラー応答 (HTTP 400) を受け取りました。**注意**: フェデレーション認証には WS-Trust が必要です。 | - オンプレミス環境に送信プロキシが必要である場合は、デバイスのコンピューター アカウントが送信プロキシに対する検出とサイレント認証を実行できることを IT 管理者が確認する必要があります。<br>- イベント 1081 とイベント 1088 (Microsoft Entra 操作ログ) には、Microsoft Entra 認証サービスから発生したエラーのサーバー エラー コードと WS-Trust エンドポイントから発生したエラーの説明が含まれます。 一般的なサーバー エラー コードとその解決策は、次のセクションに記載されています。 イベント 1081 またはイベント 1088 に先行するイベント 1022 (Microsoft Entra 分析ログ) の最初のインスタンスには、アクセスしている URL が格納されています。 |
| **STATUS\_REQUEST\_NOT\_ACCEPTED** (-1073741616/0xc00000d0) | Microsoft Entra 認証サービスまたは WS-Trust エンドポイントからエラー応答 (HTTP 400) を受け取りました。**注意**: フェデレーション認証には WS-Trust が必要です。 | イベント 1081 とイベント 1088 (Microsoft Entra 操作ログ) にはそれぞれ、Microsoft Entra 認証サービスおよび WS-Trust エンドポイントから発生したエラーのサーバー エラー コードとエラーの説明が含まれます。 一般的なサーバー エラー コードとその解決策は、次のセクションに記載されています。 イベント 1081 またはイベント 1088 に先行するイベント 1022 (Microsoft Entra 分析ログ) の最初のインスタンスには、アクセスしている URL が格納されています。 |
| **STATUS\_NETWORK\_UNREACHABLE** (-1073741252/0xc000023c)**STATUS\_BAD\_NETWORK\_PATH** (-1073741634/0xc00000be)**STATUS\_UNEXPECTED\_NETWORK\_ERROR** (-1073741628/0xc00000c4) | - Microsoft Entra 認証サービスまたは WS-Trust エンドポイントからエラー応答 (HTTP&gt; 400) を受け取りました。**注意**: フェデレーション認証には WS-Trust が必要です。<br>- 必要なエンドポイントへのネットワーク接続の問題。 | - サーバー エラーの場合、イベント 1081 とイベント 1088 (Microsoft Entra 操作ログ) には、Microsoft Entra 認証サービスのエラー コードと WS-Trust エンドポイントからのエラーの説明が含まれます。 一般的なサーバー エラー コードとその解決策は、次のセクションに記載されています。<br>- 接続の問題の場合、イベント 1022 (Microsoft Entra 分析ログ) に、アクセスされている URL が含まれ、イベント 1084 (Microsoft Entra 操作ログ) に、ネットワーク スタックからのサブエラー コードが含まれます。 |
| **STATUS\_NO\_SUCH\_LOGON\_SESSION** (-1073741729/0xc000005f) | Microsoft Entra 認証サービスでユーザーのドメインが見つからなかったため、ユーザー領域の検出に失敗しました。 | - Microsoft Entra ID に、ユーザーの UPN のドメインが Custom Domain として追加されている必要があります。 イベント 1144 (Microsoft Entra 分析ログ) には、指定された UPN が含まれます。<br>- オンプレミスのドメイン名がルーティング不可能な場合 (jdoe@contoso.local)、代替ログイン ID (AltID) を構成します。 参照: 「[前提条件](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)」、「[代替ログイン ID を構成する](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configuring-alternate-login-id)」。 |
| **AAD\_CLOUDAP\_E\_OAUTH\_USERNAME\_IS\_MALFORMED** (-1073445812/0xc004844c) | ユーザーの UPN の形式が、予期された形式ではありません。**注**:- Microsoft Entra 参加済みデバイスの場合、UPN は、ユーザーが LoginUI に入力したテキストです。<br>- Microsoft Entra ハイブリッド参加済みデバイスの場合は、UPN は、ログイン プロセス中にドメイン コントローラーから返されます。 | - ユーザーの UPN は、インターネット標準 RFC 822 に基づいて、インターネット スタイルのログイン名にする必要があります。 イベント 1144 (Microsoft Entra 分析ログ) には、指定された UPN が含まれます。<br>- ハイブリッド参加済みデバイスの場合は必ず、正しい形式で UPN を返すようにドメイン コントローラーを構成します。 ドメイン コントローラーでは、構成済みの UPN が `whoami /upn` で表示されます。<br>- オンプレミスのドメイン名がルーティング不可能である場合 (jdoe@contoso.local)、代替ログイン ID (AltID) を構成します。 参照: 「[前提条件](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)」、「[代替ログイン ID を構成する](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configuring-alternate-login-id)」。 |
| **AAD\_CLOUDAP\_E\_OAUTH\_USER\_SID\_IS\_EMPTY** (-1073445822/0xc0048442) | Microsoft Entra 認証サービスから返された ID トークンにユーザーの SID がありません。 | ネットワーク プロキシがサーバーの応答を妨害したり変更したりしていないことを確認します。 |
| **AAD\_CLOUDAP\_E\_WSTRUST\_SAML\_TOKENS\_ARE\_EMPTY** (--1073445695/0xc00484c1) | WS-Trust エンドポイントからエラーが返されました。**注意**: フェデレーション認証には WS-Trust が必要です。 | - ネットワーク プロキシが WS-Trust の応答を妨害したり変更したりしていないことを確認します。<br>- イベント 1088 (Microsoft Entra 操作ログ) には、WS-Trust エンドポイントからのサーバー エラー コードとエラーの説明が含まれます。 一般的なサーバー エラー コードとその解決策は、次のセクションに記載されています。 |
| **AAD\_CLOUDAP\_E\_HTTP\_PASSWORD\_URI\_IS\_EMPTY** (-1073445749/0xc004848b) | MEX エンドポイントが正しく構成されていません。 MEX 応答に、パスワードの URL が含まれていません。 | - ネットワーク プロキシがサーバーの応答を妨害したり変更したりしていないことを確認します。<br>- 有効な URL が応答で返されるように、MEX の構成を修正してください。 |
| **AAD\_CLOUDAP\_E\_HTTP\_CERTIFICATE\_URI\_IS\_EMPTY** (-1073445748/0xc004848C) | MEX エンドポイントが正しく構成されていません。 MEX 応答には、証明書エンドポイント URL が含まれていません。 | - ネットワーク プロキシがサーバーの応答を妨害したり変更したりしていないことを確認します。<br>- ID プロバイダーの MEX 構成を修正して、有効な証明書 URL を応答として返します。 |
| **WC\_E\_DTDPROHIBITED** (-1072894385/0xc00cee4f) | WS-Trust エンドポイントからの XML 応答に、ドキュメント型定義 (DTD) が含まれていました。 XML 応答に DTD は想定されておらず、DTD が含まれていると応答の解析に失敗します。**注意**: フェデレーション認証には WS-Trust が必要です。 | - XML 応答で DTD が送信されないように ID プロバイダーの構成を修正します。<br>- イベント 1022 (Microsoft Entra 分析ログ) には、アクセスされ、DTD を含む XML 応答を返す URL が含まれます。 |

##### 一般的なサーバー エラー コード

| エラー コード | 理由 | 解像度 |
| --- | --- | --- |
| **AADSTS50155: デバイス認証に失敗しました** | - Microsoft Entra ID で、PRT を発行するデバイスを認証できません。<br>- デバイスが削除または無効化されていないことを確認します。 この問題について詳しくは、「[Microsoft Entra デバイス管理の FAQ](https://learn.microsoft.com/ja-jp/entra/identity/devices/faq#why-do-my-users-see-an-error-message-saying--your-organization-has-deleted-the-device--or--your-organization-has-disabled-the-device--on-their-windows-10-11-devices)」を参照してください。 | 「[Microsoft Entra デバイス管理の FAQ](https://learn.microsoft.com/ja-jp/entra/identity/devices/faq#i-disabled-or-deleted-my-device--but-the-local-state-on-the-device-says-it-s-registered--what-should-i-do)」にあるこの問題に関する手順に従い、デバイスの参加の種類に基づいてデバイスを再登録します。 |
| **AADSTS50034: ユーザー アカウント `Account` が `tenant id` ディレクトリに存在しない** | Microsoft Entra ID で、テナント内のユーザー アカウントを検出できません。 | - ユーザーが正しい UPN を入力していることを確認します。<br>- オンプレミスのユーザー アカウントが Microsoft Entra ID と同期されていることを確認します。<br>- イベント 1144 (Microsoft Entra 分析ログ) には、指定された UPN が含まれます。 このエラーは、Windows クライアントのSTATUS\_ACCOUNT\_DISABLEDにマップされることがあります。 ただし、アカウントは実際には無効になりません。 これは通常、Windows クライアントが適切に書式設定された UPN ではなく、SAM アカウント名を Entra STS に送信する場合に発生します。 この問題は、通常、ハイブリッド展開でオンプレミスのActive Directory ドメイン コントローラーとの通信が不足している場合に発生します。 Entra ネイティブデプロイでは、この問題は発生しません。 |
| **AADSTS50126: 無効なユーザー名またはパスワードにより、資格情報の検証でエラーが発生しました。** | - Windows の LoginUI でユーザーが入力したユーザー名とパスワードが正しくありません。<br>- テナントでパスワード ハッシュの同期が有効になっており、デバイスがハイブリッド参加済みで、ユーザーがパスワードを変更した直後である場合、新しいパスワードが Microsoft Entra ID と同期されていない可能性があります。 | 新しい証明書を使用して新しい PRT を取得するには、Microsoft Entra パスワード同期が完了するまで待ちます。 |

##### 一般的なネットワーク エラー コード

| エラー コード | 理由 | 解像度 |
| --- | --- | --- |
| **ERROR\_WINHTTP\_TIMEOUT** (12002)**ERROR\_WINHTTP\_NAME\_NOT\_RESOLVED** (12007)**ERROR\_WINHTTP\_CANNOT\_CONNECT** (12029)**ERROR\_WINHTTP\_CONNECTION\_ERROR** (12030) | ネットワーク全般に関連した一般的な問題です。 | - イベント 1022 (Microsoft Entra 分析ログ) と 1084 (Microsoft Entra 操作ログ) には、アクセスされている URL が含まれます。<br>- オンプレミス環境に送信プロキシが必要である場合は、デバイスのコンピューター アカウントが送信プロキシに対する検出とサイレント認証を実行できることを IT 管理者が確認する必要があります。[ネットワーク エラー コードをさらに取得します](https://learn.microsoft.com/ja-jp/windows/win32/winhttp/error-messages)。 |

#### 手順 4: ログを収集する

##### 通常のログ

1. https://aka.ms/icesdptool にアクセスすると、診断ツールを含む *.cab* ファイルが自動的にダウンロードされます。
2. ツールを実行し、シナリオを再現します。
3. Fiddler トレースの場合、ポップアップ表示された証明書の要求を受け入れます。
4. トレース ファイルを保護するためのパスワードを入力するように求められます。 パスワードを指定します。
5. 最後に、収集したすべてのログが格納されているフォルダー (たとえば、 *%LOCALAPPDATA%\ElevatedDiagnostics\numbers* など) を開きます。
6. 最新の *.cab* の内容については、サポートにお問い合わせください。

##### ネットワーク トレース

Note

ネットワーク トレースを収集する場合、再現中、Fiddler を使用 "*しない*" ことが重要です。

1. `netsh trace start scenario=internetClient_dbg capture=yes persistent=yes` を実行します。
2. デバイスをロックした後、ロックを解除します。 ハイブリッド参加済みデバイスの場合、1 分以上待って、PRT 取得タスクを終了させてください。
3. `netsh trace stop` を実行します。
4. *nettrace.cab* ファイルをサポートと共有します。

### 既知の問題

モバイル ホットスポットまたは外部 WiFi ネットワークに接続し、**[設定]**&gt;**[アカウント]**&gt;**[職場または学校にアクセスする] **の順に移動すると、Microsoft Entra ハイブリッド参加済みデバイスに 2 つの異なるアカウント (Microsoft Entra ID 用に 1 つとオンプレミス AD 用に 1 つ) が表示される場合があります。 この UI の問題は、機能には影響を与えません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/troubleshoot-hybrid-join-windows-legacy"} -->
## レガシ Microsoft Entra ハイブリッド参加済みデバイスのトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-hybrid-join-windows-legacy
- Service: entra-id / devices
- Article date: 2025-06-27
- Summary: Microsoft Entra ハイブリッド参加済みダウンレベル デバイスのトラブルシューティング。

この記事は、次のデバイスにのみ適用されます。

- Windows 7
- Windows 8.1
- Windows Server 2008 R2
- Windows Server 2012
- Windows Server 2012 R2

以前のバージョンの OS のサポートに関する重要な情報については、「サポートされているバージョンの Windows クライアント」および「Windows 8.1 および Windows Server 2012 R2の既知の問題と通知 」を参照してください。

Windows 10 以降または Windows Server 2016 については、[Microsoft Entra ハイブリッド参加済みの Windows 10 および Windows Server 2016 デバイスのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-hybrid-join-windows-current)を参照してください。

この記事では、次のシナリオをサポートするように[構成された Microsoft Entra ハイブリッド参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)があることを前提としています。

- デバイスベースの条件付きアクセス

この記事では、考えられる問題の解決方法について、トラブルシューティングのガイダンスを示します。

**知っておくべきこと**

- ダウンレベルの Windows デバイス用の Microsoft Entra ハイブリッド参加の動作は、Windows 10 以降での動作とは異なります。 多くのお客様は、AD FS (フェデレーション ドメインの場合) または構成済みのシームレス SSO (マネージド ドメインの場合) が必要であることを認識していません。
- シームレス SSO は、Firefox および Microsoft Edge ブラウザーのプライベート ブラウズ モードでは動作しません。 拡張保護モードで実行されているか、拡張セキュリティ構成を有効にしている場合は、Internet Explorer ブラウザーでも機能しません。
- フェデレーション ドメインのあるお客様では、サービス接続ポイント (SCP) がマネージド ドメイン名をポイントするように構成されている場合 (たとえば、contoso.com ではなく contoso.onmicrosoft.com)、ダウンレベルの Windows デバイス用の Microsoft Entra ハイブリッド参加は機能しません。
- 複数のドメイン ユーザーが Microsoft Entra ハイブリッド参加済みのダウンレベル デバイスにサインインすると、同じ物理デバイスが Microsoft Entra ID に複数回表示されます。 たとえば、*Person1* と *Person2* がデバイスにサインインすると、**USER** 情報タブにそれぞれの登録 (DeviceID) が作成されます。
- オペレーティング システムの再インストールまたは手動の再登録なので、ユーザー情報タブでデバイスの複数のエントリを取得することもできます。
- デバイスの初回登録/参加は、サインインまたはロック/ロック解除のいずれかのタイミングで試行するように構成されています。 タスク スケジューラのタスクによってトリガーされる 5 分間の待ち時間が生じる場合があります。
- Windows 7 SP1 または Windows Server 2008 R2 SP1 に [KB4284842](https://support.microsoft.com/help/4284842) がインストールされていることを確認してください。 この更新によって、今後、パスワードの変更後、保護されているキーにお客様がアクセスできなくなったことによる認証エラーを防止できます。
- ユーザーが UPN を変更した後に、シームレス SSO の認証プロセスが中断され、Microsoft Entra ハイブリッド参加が失敗する可能性があります。 参加プロセス中に、ブラウザー セッションのクッキーが消去されるか、ユーザーが明示的にサインアウトして古い UPN を削除しない限り、古い UPN が Microsoft Entra ID に送信されていることが確認される可能性があります。

### 手順 1: 登録状態を取得する

**登録状態を確認するには:**

1. Microsoft Entra ハイブリッド参加を実行したユーザー アカウントでサインオンします。
2. コマンド プロンプトを開きます
3. 「`"%programFiles%\Microsoft Workplace Join\autoworkplace.exe" /i`」と入力します

このコマンドにより、参加状態の詳細を示すダイアログ ボックスが表示されます。

[Image: [Workplace Join for Windows] ダイアログ ボックスのスクリーンショット。特定のデバイスがワークプレースに参加していることを示す、メール アドレスが含まれるテキスト。]

### ステップ 2: Microsoft Entra ハイブリッド参加の状態を評価する

デバイスが Microsoft Entra ハイブリッド参加済みではなかった場合は、"参加" ボタンをクリックすることで Microsoft Entra ハイブリッド参加を試みることができます。 Microsoft Entra ハイブリッド参加の試みに失敗した場合は、エラーの詳細が表示されます。

**最も一般的な問題:**

- AD FS または Microsoft Entra ID が正しく構成されていない問題、またはネットワークの問題

    [Image: [Workplace Join for Windows] ダイアログ ボックスのスクリーンショット。アカウントの認証中にエラーが発生したことがテキストで報告されています。]

    - Autoworkplace.exe は Microsoft Entra ID または AD FS に対してサイレント認証を行うことができません。 この問題は、AD FS が見つからないか正しく構成されていない (フェデレーション ドメインの場合)、Microsoft Entra シームレス シングル サインオンが見つからないか正しく構成されていない (マネージド ドメインの場合)、またはネットワークの問題によって発生する可能性があります。
    - 多要素認証 (MFA) がユーザーに対して有効化/構成され、WIAORMULTIAUTHN が AD FS サーバーで構成されていない可能性があります。
    - また、ホーム領域検出 (HRD) ページがユーザーの操作を待っているため、**autoworkplace.exe** では、トークンのサイレント要求ができなくなっていることも考えられます。
    - AD FS と Microsoft Entra の URL がクライアントの IE のイントラネット ゾーンにない可能性があります。
    - ネットワーク接続の問題により、**autoworkplace.exe** で AD FS または Microsoft Entra の URL にアクセスできない場合があります。
    - **Autoworkplace.exe** は、クライアントに、クライアントから組織のオンプレミスの AD ドメイン コントローラーまでの直接の見通し線があることを必要とします。つまり、Microsoft Entra ハイブリッド参加は、クライアントが組織のイントラネットに接続されている場合にのみ成功します。
    - 組織で Microsoft Entra シームレス シングル サインオンを使用している場合、`https://autologon.microsoftazuread-sso.com` はデバイスの IE イントラネット設定に表示されません。
    - インターネット設定 `Do not save encrypted pages to disk` がオンになっています。
- ドメイン ユーザーとしてサインオンしていない

    [Image: [Workplace Join for Windows] ダイアログ ボックスのスクリーンショット。アカウントの検証中にエラーが発生したことがテキストで報告されています。]

    この問題が発生する理由はいくつかあります。

    - サインインしているユーザーがドメイン ユーザーでない場合 (ローカル ユーザーなど)。 ダウンレベルのデバイスでの Microsoft Entra ハイブリッド参加は、ドメイン ユーザーに対してのみサポートされています。
    - クライアントはドメイン コントローラーに接続できません。
- クォータに達している

    [Image: [Workplace Join for Windows] ダイアログ ボックスのスクリーンショット。ユーザーの参加済みデバイスの最大数に達したため、エラーがテキストで報告されています。]
- サービスが応答していない

    [Image: [Workplace Join for Windows] ダイアログ ボックスのスクリーンショット。サーバーが応答しなかったことが原因でエラーが発生したことがテキストで報告されています。]

状態に関する情報は、**Applications and Services Log\Microsoft-Workplace Join** のイベント ログで確認することもできます。

**Microsoft Entra ハイブリッド参加に失敗する最も一般的な原因:**

- コンピューターが、組織の内部ネットワーク、またはオンプレミスの AD ドメイン コントローラーに接続している VPN と接続していない。
- ローカル コンピューター アカウントでコンピューターにログオンしている。
- 次のようなサービス構成の問題がある。
    - AD FS サーバーが **WIAORMULTIAUTHN** をサポートするように構成されていません。
    - コンピューターのフォレストに、Microsoft Entra ID の検証済みドメイン名を指すサービス接続ポイント オブジェクトがない
    - または、ドメインが管理されている場合は、シームレス SSO が構成されていないか機能していない。
    - ユーザーがデバイスの上限に達している。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/devices/troubleshoot-intermittent-status-account-disabled"} -->
## Microsoft EntraでのSTATUS_ACCOUNT_DISABLEDのトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-intermittent-status-account-disabled
- Service: entra-id / devices
- Article date: 2026-09-01
- Summary: Microsoft Entra ハイブリッド参加済み Windows デバイスで断続的に発生する STATUS_ACCOUNT_DISABLED エラーを診断し、ユーザーのサインインを復元する方法について説明します。

この記事では、Microsoft Entra ハイブリッド参加している Windows デバイスで、ユーザーがサインインまたはデバイスのロック解除を試みる際に STATUS\_ACCOUNT\_DISABLED エラーが断続的に発生する場合の、問題の解決に役立つトラブルシューティング ガイダンスを提供します。

### Symptoms

Microsoft Entraハイブリッド参加環境では、デバイスのサインインまたはロック解除を試みると、STATUS\_ACCOUNT\_DISABLED ('ユーザーは無効です') というエラー メッセージが表示されることがあります。

ただし、対象のユーザー アカウントは無効ではありません。

この問題は通常、数分待ってロックを解除しようとした後、または再起動した後、または完全にサインアウトして、ロック解除を試みるのではなく、再びサインインした後に自己解決されます。

さらに、イベント ログには次のイベントが表示されます。

```text
Log:  Microsoft-Windows-AAD/Operational
Event ID 1081
Level: Error

OAuth response error: invalid_grant
Error description: AADSTS50034: The user account {user} does not exist in the
{guid} directory. To sign into this application, the account
must be added to the directory.
```

```text
Log:  Microsoft-Windows-Hello-for-Business/Operational
Event ID 7001

A user failed to sign into the device with the following information:
Username: SYSTEM  User SID: S-1-5-18
Credential Type: Software Key  Deployment Type: Cloud Trust
Software Lockout Counter: 0
Authentication Error Status: 0xC000006D  Authentication Error Substatus: 0xC0000072

 0xC000006D / 0xC0000072  = STATUS_LOGON_FAILURE / STATUS_ACCOUNT_DISABLED.
```

この問題は、Entra ネイティブデプロイでは発生しません。

### 原因

ハイブリッド結合Microsoft Entraは、一部のユーザー データをWindows クライアント上でローカルにキャッシュします。 プライマリ更新トークン (PRT) の有効期限や VSM セッション キーのロールオーバーなどのイベントによって、このキャッシュが古くなる可能性があります。 キャッシュを再構築するには、オンプレミスの Active Directory ドメイン コントローラーとの通信が必要です。 その通信は、次の場合に失敗する可能性があります。

- コンピューターは最近休止状態から再開されました。
- コンピューターは最近、ディープ スリープ状態から再開しました。
- クライアントがオンプレミスのドメイン コントローラーと通信しようとすると、仮想プライベート ネットワーク (VPN) またはネットワーク アダプターの接続の準備ができていない。
- VPN では、デバイス VPN ではなくユーザー VPN を使用する場合など、ユーザーが接続する前にサインインする必要があります。

この通信が失敗すると、Windows クライアントは、完全なユーザー プリンシパル名 (UPN) ではなく、ユーザーのセキュリティ アカウント マネージャー (SAM) アカウント名を Microsoft Entra ID に送信します。 Microsoft Entra IDはドメインのない SAM アカウント名を認識しないため、サインインの試行は失敗します。 その後、Windows クライアントは Microsoft Entra エラー AADSTS50034（ユーザーが見つかりません）を STATUS\_ACCOUNT\_DISABLED に対応付けるため、混乱を招く可能性があります。

Microsoft将来の更新でこの動作が改善される可能性がありますが、現在は設計上のものとして見なされています。 Microsoftでは、今後のすべてのデプロイを Entra ネイティブにすることをお勧めします。この問題は存在しません。
<!-- /MSL-PAGE -->
